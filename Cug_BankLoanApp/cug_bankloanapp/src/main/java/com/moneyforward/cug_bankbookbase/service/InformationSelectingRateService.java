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
import com.moneyforward.cug_bankbookbase.dto.InformationSelectingRateDto;
import com.moneyforward.cug_bankbookbase.logic.InformationSelectingRateLogic;

/**
 * 金利見直し方法選択のご案内取得サービスクラス.
 *
 * 選択した「金利見直し方法選択のご案内」取得を行うサービスを呼び出すクラス
 */
@Service
@Transactional
public class InformationSelectingRateService {
  /** 蓄積データ参照履歴情報. */
  @Autowired
  private StoredDataHistoryRepository storedDataHistoryRepository;

  /** 定数取得クラス. */
  @Autowired
  private BankBookConstantValues bankBookConstantValues;

  /** 業務API「金利見直し方法選択のご案内」. */
  @Autowired
  private InformationSelectingRateLogic informationSelectingRateLogic;

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
   * 金利見直し方法選択のご案内取得を行う.
   *
   * 金利見直し方法選択のご案内のバイナリファイルを取得し、蓄積データ参照履歴情報テーブルに登録する
   *
   * @param userId ユーザ識別子
   * @param accountId 口座識別子
   * @param reportId 金利見直し方法選択のご案内識別子
   * @return バイナリファイル
   * @throws ParseException 定期預金期日のご案内取得にエラーが発生した場合
   */
  public byte[] getInformationSelectingRate(String userId, String accountId, String reportId, String type) throws ParseException {
    // 業務API「ご返済のご案内」呼出
    InformationSelectingRateDto informationSelectingRateDto = informationSelectingRateLogic.callInformationSelectingRateApi(userId, accountId, reportId);

    // Parameterの定義
    HashMap<String, Object> param = new HashMap<String, Object>();
    
    // 名義人 
    if(StringUtils.isNotEmpty(informationSelectingRateDto.getApplicantName())) {
      param.put("applicantName", informationSelectingRateDto.getApplicantName() + BankBookCommonConstants.HONORIFIC);      
    }
    // 申し込み期限
    if(StringUtils.isNotEmpty(informationSelectingRateDto.getApplicationDeadline())) {
      param.put("applicationDeadline", commonUtil.parseDateFormat(informationSelectingRateDto.getApplicationDeadline()));      
    }
    // インバン内容1
    if(StringUtils.isNotEmpty(informationSelectingRateDto.getInternetBankingInfo1())) {
      param.put("internetBankingInfo1", informationSelectingRateDto.getInternetBankingInfo1());      
    }
    // インバン内容2
    if(StringUtils.isNotEmpty(informationSelectingRateDto.getInternetBankingInfo2())) {
      param.put("internetBankingInfo2", informationSelectingRateDto.getInternetBankingInfo2());
    }
    // インバン内容3
    if(StringUtils.isNotEmpty(informationSelectingRateDto.getInternetBankingInfo3())) {
      param.put("internetBankingInfo3", informationSelectingRateDto.getInternetBankingInfo3());
    }
    // 取扱番号
    if(informationSelectingRateDto.getHandlingNumber() != null) {
      param.put("handlingNumber", CommonUtil.frontZeroSuppress(informationSelectingRateDto.getHandlingNumber()));      
    }
    // 制度融資名
    if(StringUtils.isNotEmpty(informationSelectingRateDto.getLoanName())) {
      param.put("loanName", informationSelectingRateDto.getLoanName());
    }
    // 融資額
    if(informationSelectingRateDto.getLoanAmount() != null) {
      param.put("loanAmount", CommonNumberUtil.addComma(informationSelectingRateDto.getLoanAmount()));
    }
    // 現在残高
    if(informationSelectingRateDto.getCurrentAmount() != null) {
      param.put("currentAmount", CommonNumberUtil.addComma(informationSelectingRateDto.getCurrentAmount()));
    }
    // 適用返済日
    if(StringUtils.isNotEmpty(informationSelectingRateDto.getRepaymentStartDate())) {
      param.put("repaymentStartDate", commonUtil.parseDateFormat(informationSelectingRateDto.getRepaymentStartDate()));
    }
    // 最終返済日
    if(StringUtils.isNotEmpty(informationSelectingRateDto.getRepaymentEndDate())) {
      param.put("repaymentEndDate", commonUtil.parseDateFormat(informationSelectingRateDto.getRepaymentEndDate()));
    }
    // 現在金利見直し方法
    if(StringUtils.isNotEmpty(informationSelectingRateDto.getInterestRateReviewMethod())) {
      param.put("interestRateReviewMethod", informationSelectingRateDto.getInterestRateReviewMethod());
    }
    // 現在適用利率
    if(StringUtils.isNotEmpty(informationSelectingRateDto.getInterestRate())) {
      param.put("interestRate", informationSelectingRateDto.getInterestRate() + BankBookCommonConstants.PERCENT);
    }
    // 現在毎月返済額
    if(informationSelectingRateDto.getMonthlyRepaymentAmount() != null) {
      param.put("monthlyRepaymentAmount", CommonNumberUtil.addComma(informationSelectingRateDto.getMonthlyRepaymentAmount()));
    }
    // 現在ボーナス月返済額
    if(informationSelectingRateDto.getBonusRepaymentAmount() != null) {
      param.put("bonusRepaymentAmount", CommonNumberUtil.addComma(informationSelectingRateDto.getBonusRepaymentAmount()));
    }
    // 変動適用利率
    if(StringUtils.isNotEmpty(informationSelectingRateDto.getFloatingInterestRate())) {
      param.put("floatingInterestRate", informationSelectingRateDto.getFloatingInterestRate() + BankBookCommonConstants.PERCENT);
    }
    // 変動毎月返済額
    if(informationSelectingRateDto.getFloatingMonthlyRepaymentAmount() != null) {
      param.put("floatingMonthlyRepaymentAmount", CommonNumberUtil.addComma(informationSelectingRateDto.getFloatingMonthlyRepaymentAmount()));
    }
    // 変動ボーナス月返済額
    if(informationSelectingRateDto.getFloatingBonusRepaymentAmount() != null) {
      param.put("floatingBonusRepaymentAmount", CommonNumberUtil.addComma(informationSelectingRateDto.getFloatingBonusRepaymentAmount()));
    }
    // 3年固定適用利率
    if(StringUtils.isNotEmpty(informationSelectingRateDto.getThreeYearsFixedInterestRate())) {
      param.put("threeYearsFixedInterestRate", informationSelectingRateDto.getThreeYearsFixedInterestRate() + BankBookCommonConstants.PERCENT);
    }
    // 3年固定毎月返済額
    if(informationSelectingRateDto.getThreeYearsFixedMonthlyRepaymentAmount() != null) {
      param.put("threeYearsFixedMonthlyRepaymentAmount", CommonNumberUtil.addComma(informationSelectingRateDto.getThreeYearsFixedMonthlyRepaymentAmount()));
    }
    // 3年固定ボーナス月返済額
    if(informationSelectingRateDto.getThreeYearsFixedBonusRepaymentAmount() != null) {
      param.put("threeYearsFixedBonusRepaymentAmount", CommonNumberUtil.addComma(informationSelectingRateDto.getThreeYearsFixedBonusRepaymentAmount()));
    }
    // 5年固定適用利率
    if(StringUtils.isNotEmpty(informationSelectingRateDto.getFiveYearsFixedInterestRate())) {
      param.put("fiveYearsFixedInterestRate", informationSelectingRateDto.getFiveYearsFixedInterestRate() + BankBookCommonConstants.PERCENT);
    }
    // 5年固定毎月返済額
    if(informationSelectingRateDto.getFiveYearsFixedMonthlyRepaymentAmount() != null) {
      param.put("fiveYearsFixedMonthlyRepaymentAmount", CommonNumberUtil.addComma(informationSelectingRateDto.getFiveYearsFixedMonthlyRepaymentAmount()));
    }
    // 5年固定ボーナス月返済額
    if(informationSelectingRateDto.getFiveYearsFixedBonusRepaymentAmount() != null) {
      param.put("fiveYearsFixedBonusRepaymentAmount", CommonNumberUtil.addComma(informationSelectingRateDto.getFiveYearsFixedBonusRepaymentAmount()));
    }
    // 10年固定適用利率
    if(StringUtils.isNotEmpty(informationSelectingRateDto.getTenYearsFixedInterestRate())) {
      param.put("tenYearsFixedInterestRate", informationSelectingRateDto.getTenYearsFixedInterestRate() + BankBookCommonConstants.PERCENT);
    }
    // 10年固定毎月返済額
    if(informationSelectingRateDto.getTenYearsFixedMonthlyRepaymentAmount() != null) {
      param.put("tenYearsFixedMonthlyRepaymentAmount", CommonNumberUtil.addComma(informationSelectingRateDto.getTenYearsFixedMonthlyRepaymentAmount()));
    }
    // 10年固定ボーナス月返済額
    if(informationSelectingRateDto.getTenYearsFixedBonusRepaymentAmount() != null) {
      param.put("tenYearsFixedBonusRepaymentAmount", CommonNumberUtil.addComma(informationSelectingRateDto.getTenYearsFixedBonusRepaymentAmount()));
    }
    // 備考1
    if(StringUtils.isNotEmpty(informationSelectingRateDto.getRemarks1())) {
      param.put("remarks1", informationSelectingRateDto.getRemarks1());
    }
    // 備考2
    if(StringUtils.isNotEmpty(informationSelectingRateDto.getRemarks2())) {
      param.put("remarks2", informationSelectingRateDto.getRemarks2());
    }
    // 備考3
    if(StringUtils.isNotEmpty(informationSelectingRateDto.getRemarks3())) {
      param.put("remarks3", informationSelectingRateDto.getRemarks3());
    }
    // 備考4
    if(StringUtils.isNotEmpty(informationSelectingRateDto.getRemarks4())) {
      param.put("remarks4", informationSelectingRateDto.getRemarks4());
    }
    // 備考5
    if(StringUtils.isNotEmpty(informationSelectingRateDto.getRemarks5())) {
      param.put("remarks5", informationSelectingRateDto.getRemarks5());
    }
    // 作成日
    param.put("issuedDate", commonUtil.parseDateFormat(informationSelectingRateDto.getIssuedDate()));
    // 取扱店名
    if(StringUtils.isNotEmpty(informationSelectingRateDto.getBranchName())) {
      param.put("branchName", informationSelectingRateDto.getBranchName());    
    }
    // 取扱店電話番号
    if(StringUtils.isNotEmpty(informationSelectingRateDto.getBranchPhoneNumber())) {
      param.put("branchPhoneNumber", informationSelectingRateDto.getBranchPhoneNumber());
    }
    
    SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");

    // template path取得
    List<ReportDefinition> reportDefinitionList = reportDefinitionRepository.findByStartDateLessThanEqual(sdf.parse(informationSelectingRateDto.getIssuedDate()));

    String contractTerminationReporId = bankBookConstantValues
        .findByKeyName(CugBankBookConstants.INFORMATION_SELECTING_RATE_ID);

    // 帳票定義情報テーブル
    ReportDefinition reportDefinition = reportDefinitionList.stream()
        .filter(x -> contractTerminationReporId.equals(x.getReportTypeId()))
        .sorted(Comparator.comparing(ReportDefinition::getReportVersion, Comparator.reverseOrder())).findFirst()
        .get();

    // pdfServiceに渡すListの処理の実装
    List<InformationSelectingRateDto> informationSelectingRateList = new ArrayList<InformationSelectingRateDto>();
    informationSelectingRateList.add(informationSelectingRateDto);

    // PDF作成サービスクラス呼び出し
    byte[] bytes = pdfService.pdfExport(reportDefinition.getReportFormatFilePath(), informationSelectingRateList, param);

    StoredDataHistory storedDataHistory = new StoredDataHistory();
    storedDataHistory.setUserId(userId);
    storedDataHistory.setAccountId(accountId);
    // typeが空文字あり得るなら分岐が必要（要確認）
    String dataSection = CugCommonConstants.INFORMATION_SELECTING_RATE_REPORT_TYPE_ID + "_" + type;
    storedDataHistory.setDataSection(dataSection);
    storedDataHistory.setReportId(reportId);
    storedDataHistory.setReferenceDate(informationSelectingRateDto.getIssuedDate());
    storedDataHistory.setReportVersion(reportDefinition.getReportVersion());
    storedDataHistory.setReportTypeId(contractTerminationReporId);

    // 蓄積データ参照履歴情報テーブルに登録する
    storedDataHistoryRepository.save(storedDataHistory);

    return bytes;
  }
}
