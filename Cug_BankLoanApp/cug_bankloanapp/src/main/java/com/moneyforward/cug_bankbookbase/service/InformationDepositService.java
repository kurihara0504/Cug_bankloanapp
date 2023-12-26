package com.moneyforward.cug_bankbookbase.service;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.moneyforward.bankbookbase.base.constance.BankBookCommonConstants;
import com.moneyforward.bankbookbase.base.constance.BankBookConstantValues;
import com.moneyforward.bankbookbase.base.util.CommonNumberUtil;
import com.moneyforward.bankbookbase.base.util.CommonUtil;
import com.moneyforward.bankbookbase.repository.ReportDefinitionRepository;
import com.moneyforward.bankbookbase.repository.StoredDataHistoryRepository;
import com.moneyforward.bankbookbase.repository.entity.ReportDefinition;
import com.moneyforward.bankbookbase.repository.entity.StoredDataHistory;
import com.moneyforward.bankbookbase.service.PdfService;
import com.moneyforward.cug_bankbookbase.base.constance.CugBankBookConstants;
import com.moneyforward.cug_bankbookbase.base.constance.CugCommonConstants;
import com.moneyforward.cug_bankbookbase.dto.InformationDepositDetailDto;
import com.moneyforward.cug_bankbookbase.dto.InformationDepositDto;
import com.moneyforward.cug_bankbookbase.dto.InformationDepositPdfAdaptDto;
import com.moneyforward.cug_bankbookbase.logic.InformationDepositLogic;

/**
 * 定期預金期日のご案内取得サービスクラス.
 *
 * 選択した「定期預金期日のご案内」取得を行うサービスを呼び出すクラス
 */
@Service
@Transactional
public class InformationDepositService {
  /** 蓄積データ参照履歴情報. */
  @Autowired
  private StoredDataHistoryRepository storedDataHistoryRepository;

  /** 定数取得クラス. */
  @Autowired
  private BankBookConstantValues bankBookConstantValues;

  /** 業務API「定期預金期日のご案内」. */
  @Autowired
  private InformationDepositLogic informationDepositLogic;

  /** 帳票定義情報テーブルのインターフェース. */
  @Autowired
  private ReportDefinitionRepository reportDefinitionRepository;
  
  /** 共通処理クラス. */
  @Autowired
  CommonUtil commonUtil;

  /** 数値共通処理クラス. */
  @Autowired
  CommonNumberUtil commonNumberUtil;
  
  /** PDF情報取得サービスクラス. */
  @Autowired
  private PdfService pdfService;

  /**
   * 定期預金期日のご案内取得を行う.
   *
   * 定期預金期日のご案内のバイナリファイルを取得し、蓄積データ参照履歴情報テーブルに登録する
   *
   * @param userId ユーザ識別子
   * @param accountId 口座識別子
   * @param reportId 定期預金期日のご案内識別子
   * @return バイナリファイル
   * @throws ParseException 定期預金期日のご案内取得にエラーが発生した場合
   */
  public byte[] getInformationDeposit(String userId, String accountId, String reportId) throws ParseException {
    
    // 業務API「定期預金期日のご案内」呼出
    InformationDepositDto informationDepositDto = informationDepositLogic.CallInformationDepositApi(userId, accountId, reportId);
    
    // Parameterの定義
    HashMap<String, Object> param = new HashMap<String, Object>();
    
    // 口座番号
    param.put("accountNumber", CommonUtil.frontZeroSuppress(informationDepositDto.getAccountNumber()));
    //名義人 
    param.put("applicantName", informationDepositDto.getApplicantName() + BankBookCommonConstants.HONORIFIC);
    // 作成日
    param.put("issuedDate", commonUtil.parseDateFormat(informationDepositDto.getIssuedDate()));
    // 取扱店名
    param.put("branchName", informationDepositDto.getBranchName());    
    // 取扱店電話番号
    param.put("branchPhoneNumber", informationDepositDto.getBranchPhoneNumber());
    // お知らせ1
    if(StringUtils.isNotEmpty(informationDepositDto.getInformationLine1())) {
      param.put("informationLine1", informationDepositDto.getInformationLine1());
    }
    // お知らせ2
    if(StringUtils.isNotEmpty(informationDepositDto.getInformationLine2())) {
      param.put("informationLine2", informationDepositDto.getInformationLine2());
    }
    // お知らせ3
    if(StringUtils.isNotEmpty(informationDepositDto.getInformationLine3())) {
      param.put("informationLine3", informationDepositDto.getInformationLine3());
    }
    // お知らせ4
    if(StringUtils.isNotEmpty(informationDepositDto.getInformationLine4())) {
      param.put("informationLine4", informationDepositDto.getInformationLine4());
    }
    // お知らせ5
    if(StringUtils.isNotEmpty(informationDepositDto.getInformationLine5())) {
      param.put("informationLine5", informationDepositDto.getInformationLine5());
    }

    // 明細部リスト取得
    List<InformationDepositDetailDto> list = informationDepositDto.getList();
    
    List<InformationDepositPdfAdaptDto> informationDepositPdfAdaptList = new ArrayList<>();
    
    list.forEach(detail -> {
      // PDF用
      InformationDepositPdfAdaptDto informationDepositPdfAdaptDto = new InformationDepositPdfAdaptDto();
      
      // 期日・中間利払日
      informationDepositPdfAdaptDto.setInterestPaymentDate(commonUtil.parseDateFormat(detail.getInterestPaymentDate()));
      // お預り番号
      informationDepositPdfAdaptDto.setDepositNumber(detail.getDepositNumber());
      // 定期預金種類
      informationDepositPdfAdaptDto.setTimeDepositType(detail.getTimeDepositType());
      // 取扱内容等
      if(StringUtils.isNotEmpty(detail.getHandlingType())) {
        informationDepositPdfAdaptDto.setHandlingType(detail.getHandlingType());
      }
      // お預り金額（円）
      informationDepositPdfAdaptDto.setDepositAmount(CommonNumberUtil.addComma(detail.getDepositAmount()));
      
      // （中間利払）利率
      if(StringUtils.isEmpty(detail.getInterest()) && StringUtils.isEmpty(detail.getInterest2())) {
          // 上下段両方nullの場合は****を出力
          informationDepositPdfAdaptDto.setInterest2("****");
      } else if(StringUtils.isNotEmpty(detail.getInterest()) && StringUtils.isNotEmpty(detail.getInterest2())) {
        // 上下段両方値がある場合はそれぞれに出力
        informationDepositPdfAdaptDto.setInterest(detail.getInterest() + BankBookCommonConstants.PERCENT);         
        informationDepositPdfAdaptDto.setInterest2(detail.getInterest2() + BankBookCommonConstants.PERCENT);
      } else if(StringUtils.isNotEmpty(detail.getInterest()) && StringUtils.isEmpty(detail.getInterest2())) {
        // 上段だけの場合は下段に出力
        informationDepositPdfAdaptDto.setInterest2(detail.getInterest() + BankBookCommonConstants.PERCENT);                 
      } else if(StringUtils.isEmpty(detail.getInterest()) && StringUtils.isNotEmpty(detail.getInterest2())) {
        // 下段だけの場合は下段に出力
        informationDepositPdfAdaptDto.setInterest2(detail.getInterest2() + BankBookCommonConstants.PERCENT);                 
      }
      
      // 期間
      if(StringUtils.isEmpty(detail.getPeriod2())) {
        // 期間2が存在しない時は期間を下段に表示
        informationDepositPdfAdaptDto.setPeriod2(detail.getPeriod());
      } else {
        informationDepositPdfAdaptDto.setPeriod(detail.getPeriod());
        informationDepositPdfAdaptDto.setPeriod2(detail.getPeriod2());
      }
      
      // お利息（円）
      if(detail.getInterestAmount2() == null || detail.getInterestAmount2() == 0) {
        // お利息2が存在しない時はお利息を下段に表示
        informationDepositPdfAdaptDto.setInterestAmount2(CommonNumberUtil.addComma(detail.getInterestAmount()));
      } else {
        informationDepositPdfAdaptDto.setInterestAmount(CommonNumberUtil.addComma(detail.getInterestAmount()));
        informationDepositPdfAdaptDto.setInterestAmount2(CommonNumberUtil.addComma(detail.getInterestAmount2()));
      }
      
      // 税区分
      informationDepositPdfAdaptDto.setTaxClass(detail.getTaxClass());
      // 国税
      informationDepositPdfAdaptDto.setNationalTax(CommonNumberUtil.addComma(detail.getNationalTax()));
      // 地方税
      informationDepositPdfAdaptDto.setLocalTax(CommonNumberUtil.addComma(detail.getLocalTax()));
      // 差引お支払利息（円）
      informationDepositPdfAdaptDto.setDeductionPaymentInterest(CommonNumberUtil.addComma(detail.getDeductionPaymentInterest()));
      // ご継続後の新元金（円）
      informationDepositPdfAdaptDto.setRenewalPrincipalValue(CommonNumberUtil.addComma(detail.getRenewalPrincipalValue()));
      // お利息の取扱い
      informationDepositPdfAdaptDto.setInterestHandling(detail.getInterestHandling());
      // お振替指定口座
      if(StringUtils.isNotEmpty(detail.getTransferDesignationAccount())) {
        informationDepositPdfAdaptDto.setTransferDesignationAccount(detail.getTransferDesignationAccount());        
      }
      if(detail.getMaruyuTaxableInterest() != null) {
        // （マル優）課税対象利息
        informationDepositPdfAdaptDto.setMaruyuTaxableInterest(CommonNumberUtil.addComma(detail.getMaruyuTaxableInterest()));        
      }
      if(detail.getMaruyuTaxableDays() != null) {
        // （マル優）課税対象日数
        informationDepositPdfAdaptDto.setMaruyuTaxableDays(detail.getMaruyuTaxableDays() + "日");
      }
      // 備考
      if(StringUtils.isNotEmpty(detail.getRemarks())) {
        informationDepositPdfAdaptDto.setRemarks(detail.getRemarks());      
      }
      // 支払調書  
      if(StringUtils.isNotEmpty(detail.getPaymentRecord())) {
        informationDepositPdfAdaptDto.setPaymentRecord(detail.getPaymentRecord());
      }
      
      informationDepositPdfAdaptList.add(informationDepositPdfAdaptDto);
    });
    
    SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");

    // template path取得
    List<ReportDefinition> reportDefinitionList = reportDefinitionRepository.findByStartDateLessThanEqual(sdf.parse(informationDepositDto.getIssuedDate()));

    // 帳票種類ID取得
    String infomationDepositId = bankBookConstantValues.findByKeyName(CugBankBookConstants.INFORMATION_DEPOSIT_ID);

    // 帳票定義情報テーブル
    ReportDefinition reportDefinition = reportDefinitionList.stream()
        .filter(x -> infomationDepositId.equals(x.getReportTypeId()))
        .sorted(Comparator.comparing(ReportDefinition::getReportVersion, Comparator.reverseOrder())).findFirst()
        .get();

    // PDF作成サービスクラス呼び出し
    byte[] bytes = pdfService.pdfExport(reportDefinition.getReportFormatFilePath(), informationDepositPdfAdaptList, param);

    StoredDataHistory storedDataHistory = new StoredDataHistory();
    storedDataHistory.setUserId(userId);
    storedDataHistory.setAccountId(accountId);
    storedDataHistory.setDataSection(CugCommonConstants.INFORMATION_DEPOSIT_REPORT_TYPE_ID + "_" + CugCommonConstants.TYPE_006);
    storedDataHistory.setReportId(reportId);
    storedDataHistory.setReferenceDate(informationDepositDto.getIssuedDate());
    storedDataHistory.setReportVersion(reportDefinition.getReportVersion());
    storedDataHistory.setReportTypeId(infomationDepositId);

    // 蓄積データ参照履歴情報テーブルに登録する
    storedDataHistoryRepository.save(storedDataHistory);
    
    return bytes;
  }
}
