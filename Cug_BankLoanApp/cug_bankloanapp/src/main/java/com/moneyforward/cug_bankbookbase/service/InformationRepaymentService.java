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
import com.moneyforward.cug_bankbookbase.dto.InformationRepaymentDetailDto;
import com.moneyforward.cug_bankbookbase.dto.InformationRepaymentDto;
import com.moneyforward.cug_bankbookbase.dto.InformationRepaymentPdfAdaptDto;
import com.moneyforward.cug_bankbookbase.logic.InformationRepaymentLogic;

/**
 * ご返済のご案内取得サービスクラス.
 *
 * 選択した「ご返済のご案内」取得を行うサービスを呼び出すクラス
 */
@Service
@Transactional
public class InformationRepaymentService {
  /** 蓄積データ参照履歴情報. */
  @Autowired
  private StoredDataHistoryRepository storedDataHistoryRepository;

  /** 定数取得クラス. */
  @Autowired
  private BankBookConstantValues bankBookConstantValues;

  /** 業務API「ご返済のご案内」. */
  @Autowired
  private InformationRepaymentLogic informationRepaymentLogic;

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
   * ご返済のご案内取得を行う.
   *
   * ご返済のご案内のバイナリファイルを取得し、蓄積データ参照履歴情報テーブルに登録する
   *
   * @param userId ユーザ識別子
   * @param accountId 口座識別子
   * @param reportId ご返済のご案内識別子
   * @return バイナリファイル
   * @throws ParseException ご返済のご案内取得にエラーが発生した場合
   */
  public byte[] getInformationRepayment(String userId, String accountId, String reportId, String type) throws ParseException {
    // 業務API「ご返済のご案内」呼出
    InformationRepaymentDto informationRepaymentDto = informationRepaymentLogic.callInformationRepaymentApi(userId, accountId, reportId);

    // Parameterの定義
    HashMap<String, Object> param = new HashMap<String, Object>();
    
    //名義人 
    if(StringUtils.isNotEmpty(informationRepaymentDto.getApplicantName())) {
      param.put("applicantName", informationRepaymentDto.getApplicantName() + BankBookCommonConstants.HONORIFIC);      
    }
    // 作成日
      param.put("issuedDate", commonUtil.parseDateFormat(informationRepaymentDto.getIssuedDate()));      
    // 取扱店名
    if(StringUtils.isNotEmpty(informationRepaymentDto.getBranchName())) {
      param.put("branchName", informationRepaymentDto.getBranchName());          
    }
    // 取扱店電話番号
    if(StringUtils.isNotEmpty(informationRepaymentDto.getBranchPhoneNumber())) {
      param.put("branchPhoneNumber", informationRepaymentDto.getBranchPhoneNumber());      
    }
    // 借入日
    if(StringUtils.isNotEmpty(informationRepaymentDto.getBorrowingDate())) {
      param.put("borrowingDate", commonUtil.parseDateFormat(informationRepaymentDto.getBorrowingDate()));      
    }
    // 借入額
    if(informationRepaymentDto.getBorrowingAmount() != null) {
      param.put("borrowingAmount", CommonNumberUtil.addComma(informationRepaymentDto.getBorrowingAmount()));      
    }
    // 内毎回分借入額
    if(informationRepaymentDto.getMonthlyBorrowingAmount() != null) {
      param.put("monthlyBorrowingAmount", CommonNumberUtil.addComma(informationRepaymentDto.getMonthlyBorrowingAmount()));      
    }
    // 内増額分借入額
    if(informationRepaymentDto.getIncreasedBorrowingAmount() != null) {
      param.put("increasedBorrowingAmount", CommonNumberUtil.addComma(informationRepaymentDto.getIncreasedBorrowingAmount()));      
    }
    // 適用利率
    if(StringUtils.isNotEmpty(informationRepaymentDto.getInterestRate())) {
      param.put("interestRate", informationRepaymentDto.getInterestRate() + BankBookCommonConstants.PERCENT);      
    }
    // 科目
    if(StringUtils.isNotEmpty(informationRepaymentDto.getSubject())) {
      param.put("subject", informationRepaymentDto.getSubject());      
    }
    // 取扱番号
    if(informationRepaymentDto.getHandlingNumber() != null) {
      param.put("handlingNumber", CommonUtil.frontZeroSuppress(informationRepaymentDto.getHandlingNumber()));
    }
    // 案内区分
    if(StringUtils.isNotEmpty(informationRepaymentDto.getNotificationsClass())) {
      param.put("notificationsClass", informationRepaymentDto.getNotificationsClass());
    }
    // 最終期限
    param.put("maturityDate", commonUtil.parseDateFormat(informationRepaymentDto.getMaturityDate()));
    // 返済日（周期）
    if(informationRepaymentDto.getRepaymentCycle() != null && informationRepaymentDto.getRepaymentDate() != null) {
      param.put("repaymentCycleDate", informationRepaymentDto.getRepaymentCycle() + "か月ごと" + informationRepaymentDto.getRepaymentDate() + "日");
    } else if(informationRepaymentDto.getRepaymentCycle() == null && informationRepaymentDto.getRepaymentDate() != null) {
      param.put("repaymentCycleDate", informationRepaymentDto.getRepaymentDate() + "日");
    } else if(informationRepaymentDto.getRepaymentCycle() != null && informationRepaymentDto.getRepaymentDate() == null) {
      param.put("repaymentCycleDate", informationRepaymentDto.getRepaymentCycle() + "か月ごと");
    }
    // 増額分支払月
    if(informationRepaymentDto.getIncreasedRepaymentMonth1() != null && informationRepaymentDto.getIncreasedRepaymentMonth2() != null) {
      param.put("increasedRepaymentMonth", informationRepaymentDto.getIncreasedRepaymentMonth1() + "月、" 
          + informationRepaymentDto.getIncreasedRepaymentMonth2() + "月");
    } else if(informationRepaymentDto.getIncreasedRepaymentMonth2() == null && informationRepaymentDto.getIncreasedRepaymentMonth1() != null){
      param.put("increasedRepaymentMonth", informationRepaymentDto.getIncreasedRepaymentMonth1() + "月"); 
    } else if(informationRepaymentDto.getIncreasedRepaymentMonth2() != null && informationRepaymentDto.getIncreasedRepaymentMonth1() == null ){
      param.put("increasedRepaymentMonth", informationRepaymentDto.getIncreasedRepaymentMonth2() + "月"); 
     } 
    // 適用利率終了日
    if(StringUtils.isNotEmpty(informationRepaymentDto.getInterestRateEndDate())) {
      param.put("interestRateEndDate", commonUtil.parseDateFormat(informationRepaymentDto.getInterestRateEndDate()));      
    }
    // 新適用利率
    if(StringUtils.isNotEmpty(informationRepaymentDto.getNewInterestRate())) {
      param.put("newInterestRate", informationRepaymentDto.getNewInterestRate() + BankBookCommonConstants.PERCENT);      
    }
    // 自振店番号
    if(StringUtils.isNotEmpty(informationRepaymentDto.getTransferBranchNumber())) {
      param.put("transferBranchNumber", informationRepaymentDto.getTransferBranchNumber());      
    }
    // 自振科目名
    if(StringUtils.isNotEmpty(informationRepaymentDto.getTransferSubject())) {
      param.put("transferSubject", informationRepaymentDto.getTransferSubject());
    }
    // 自振口座番号
    if(StringUtils.isNotEmpty(informationRepaymentDto.getTransferAccountNumber())) {
      param.put("transferAccountNumber", CommonUtil.frontZeroSuppress(informationRepaymentDto.getTransferAccountNumber()));
    }
    // 次回案内予定
    if(StringUtils.isNotEmpty(informationRepaymentDto.getNextNotifications())) {
      param.put("nextNotifications", commonUtil.parseYearMonthFormat(informationRepaymentDto.getNextNotifications()) + "ごろ");      
    }
    // 制度融資名
    if(StringUtils.isNotEmpty(informationRepaymentDto.getLoanName())) {
      param.put("loanName", informationRepaymentDto.getLoanName());      
    }
    // 備考
    if(StringUtils.isNotEmpty(informationRepaymentDto.getRemarks())) {
      param.put("remarks", informationRepaymentDto.getRemarks());
    }
    // ガイダンス1
    if(StringUtils.isNotEmpty(informationRepaymentDto.getGuidance1())) {
      param.put("guidance1", informationRepaymentDto.getGuidance1());
    }
    // ガイダンス2
    if(StringUtils.isNotEmpty(informationRepaymentDto.getGuidance2())) {
      param.put("guidance2", informationRepaymentDto.getGuidance2());
    }
    // 説明文言1
    if(StringUtils.isNotEmpty(informationRepaymentDto.getExplanation1())) {
      param.put("explanation1", informationRepaymentDto.getExplanation1());
    }
    // 説明文言2
    if(StringUtils.isNotEmpty(informationRepaymentDto.getExplanation2())) {
      param.put("explanation2", informationRepaymentDto.getExplanation2());
    }
    // 説明文言3
    if(StringUtils.isNotEmpty(informationRepaymentDto.getExplanation3())) {
      param.put("explanation3", informationRepaymentDto.getExplanation3());      
    }
    // 説明文言4
    if(StringUtils.isNotEmpty(informationRepaymentDto.getExplanation4())) {
      param.put("explanation4", informationRepaymentDto.getExplanation4());
    }
    // 説明文言5
    if(StringUtils.isNotEmpty(informationRepaymentDto.getExplanation5())) {
      param.put("explanation5", informationRepaymentDto.getExplanation5());
    }
    // 取扱店
    if(StringUtils.isNotEmpty(informationRepaymentDto.getBranchName())) {
      param.put("branchName", informationRepaymentDto.getBranchName());      
    }
    // 取扱店電話番号
    if(StringUtils.isNotEmpty(informationRepaymentDto.getBranchPhoneNumber())) {
      param.put("branchPhoneNumber", informationRepaymentDto.getBranchPhoneNumber());      
    }
    
    List<InformationRepaymentDetailDto> list = informationRepaymentDto.getDetails();
    
    List<InformationRepaymentPdfAdaptDto> infomationRepaymentPdfAdaptList = new ArrayList<>();
    
    list.forEach(detail -> {
      InformationRepaymentPdfAdaptDto infomationRepaymentPdfAdaptDto = new InformationRepaymentPdfAdaptDto();
      
      // 返済日又は利払日
      if(StringUtils.isNotEmpty(detail.getRepaymentDate())) {
        infomationRepaymentPdfAdaptDto.setRepaymentDate(commonUtil.parseDateFormat(detail.getRepaymentDate()));        
      }
      // 返済元利金合計
      if(detail.getPrincipalAndInterest() != null) {
        infomationRepaymentPdfAdaptDto.setPrincipalAndInterest(CommonNumberUtil.addComma(detail.getPrincipalAndInterest()));        
      }
      // 毎回分利息計算日数
      if(detail.getMonthlyInterestCalcDate() != null) {
        infomationRepaymentPdfAdaptDto.setMonthlyInterestCalcDate(detail.getMonthlyInterestCalcDate() + "日");        
      }
      // 毎回分利息額
      if(detail.getMonthlyInterest() != null) {
        infomationRepaymentPdfAdaptDto.setMonthlyInterest(CommonNumberUtil.addComma(detail.getMonthlyInterest()));        
      }
      // 毎回分返済元金
      if(detail.getMonthlyPrincipal() != null) {
        infomationRepaymentPdfAdaptDto.setMonthlyPrincipal(CommonNumberUtil.addComma(detail.getMonthlyPrincipal()));        
      }
      // 増額分利息額
      if(detail.getIncreasedInterest() != null) {
        infomationRepaymentPdfAdaptDto.setIncreasedInterest(CommonNumberUtil.addComma(detail.getIncreasedInterest()));        
      }
      // 増額分返済元金
      if(detail.getIncreasedPrincipal() != null) {
        infomationRepaymentPdfAdaptDto.setIncreasedPrincipal(CommonNumberUtil.addComma(detail.getIncreasedPrincipal()));        
      }
      // 返済後残高
      if(detail.getAfterRepaymentBalance() != null) {
        infomationRepaymentPdfAdaptDto.setAfterRepaymentBalance(CommonNumberUtil.addComma(detail.getAfterRepaymentBalance()));        
      }
      // 未収利息
      if(detail.getAccruedInterest() != null) {
        infomationRepaymentPdfAdaptDto.setAccruedInterest(CommonNumberUtil.addComma(detail.getAccruedInterest()));        
      }
      
      infomationRepaymentPdfAdaptList.add(infomationRepaymentPdfAdaptDto);
    });

    SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");

    // template path取得
    List<ReportDefinition> reportDefinitionList = reportDefinitionRepository.findByStartDateLessThanEqual(sdf.parse(informationRepaymentDto.getIssuedDate()));

    // 帳票種類ID取得
    String infomationRepaymentId = bankBookConstantValues.findByKeyName(CugBankBookConstants.INFORMATION_REPAYMENT_ID);

    // 帳票定義情報テーブル
    ReportDefinition reportDefinition = reportDefinitionList.stream()
        .filter(x -> infomationRepaymentId.equals(x.getReportTypeId()))
        .sorted(Comparator.comparing(ReportDefinition::getReportVersion, Comparator.reverseOrder())).findFirst()
        .get();

    // PDF作成サービスクラス呼び出し
    byte[] bytes = pdfService.pdfExport(reportDefinition.getReportFormatFilePath(), infomationRepaymentPdfAdaptList, param);

    StoredDataHistory storedDataHistory = new StoredDataHistory();
    storedDataHistory.setUserId(userId);
    storedDataHistory.setAccountId(accountId);
    storedDataHistory.setDataSection(CugCommonConstants.INFORMATION_REPAYMENT_REPORT_TYPE_ID + "_" + type);
    storedDataHistory.setReportId(reportId);
    storedDataHistory.setReferenceDate(informationRepaymentDto.getIssuedDate());
    storedDataHistory.setReportVersion(reportDefinition.getReportVersion());
    storedDataHistory.setReportTypeId(infomationRepaymentId);

    // 蓄積データ参照履歴情報テーブルに登録する
    storedDataHistoryRepository.save(storedDataHistory);
    
    return bytes;
  }
}
