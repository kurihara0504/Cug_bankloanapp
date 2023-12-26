package com.moneyforward.cug_bankbookbase.service;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ResourceLoader;
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
import com.moneyforward.cug_bankbookbase.dto.NoticeFloatingRateDto;
import com.moneyforward.cug_bankbookbase.logic.NoticeFloatingRateApiLogic;

/**
 * 変動金利定期預金のお知らせ取得サービス
 *
 * 選択した変動金利定期預金のお知らせ取得を行うサービスを呼び出すクラス
 */
@Service
@Transactional


public class NoticeFloatingRateService {

  /** 蓄積データ参照履歴情報. */
  @Autowired
  private StoredDataHistoryRepository storedDataHistoryRepository;

  /** 定数取得クラス. */
  @Autowired
  private BankBookConstantValues bankBookConstantValues;

  /** 業務API「変動金利定期預金通知照会」呼び出し. */
  @Autowired
  private NoticeFloatingRateApiLogic noticeFloatingRateApiLogic;

  /** 帳票定義情報テーブルのインターフェース. */
  @Autowired
  private ReportDefinitionRepository reportDefinitionRepository;

  /** PDF情報取得サービスクラス. */
  @Autowired
  private PdfService pdfService;

  /** リソースを取得するためのインターフェース. */
  @Autowired
  ResourceLoader resource;

  /** 共通処理クラス. */
  @Autowired
  CommonUtil commonUtil;
  

  /**
   * 変動金利定期預金のお知らせ取得を行う.
   *
   * 変動金利定期預金のお知らせのバイナリファイルを取得し、蓄積データ参照履歴情報テーブルに登録する
   *
   * @param userId ユーザ識別子
   * @param accountId 口座識別子
   * @param reportId 変動金利定期預金のお知らせ識別子
   * @return バイナリファイル
   * @throws ParseException 変動金利定期預金のお知らせ取得にエラーが発生した場合
   */
  public byte[] getNoticeFloatingRateService(String userId, String accountId, String reportId) throws ParseException {

    // 業務API「変動金利定期預金通知照会」呼出
    NoticeFloatingRateDto noticeFloatingRateDto = noticeFloatingRateApiLogic.callNoticeFloatingRateLogic(userId, accountId, reportId);

    // Parameterの定義
    HashMap<String, Object> param = new HashMap<String, Object>();

    // 作成日
    param.put("issuedDate",commonUtil.parseDateFormat(noticeFloatingRateDto.getIssuedDate()));

    // 口座番号
    if (StringUtils.isNotEmpty(noticeFloatingRateDto.getAccountNumber())) {
      param.put("accountNumber",CommonUtil.frontZeroSuppress(noticeFloatingRateDto.getAccountNumber()) );
    }

    // 名義人
    if (StringUtils.isNotEmpty(noticeFloatingRateDto.getApplicantName())) {
      param.put("applicantName",noticeFloatingRateDto.getApplicantName()+ BankBookCommonConstants.HONORIFIC);
    }

    // 預入日
    if (StringUtils.isNotEmpty(noticeFloatingRateDto.getDepositDate())) {
      param.put("depositDate",commonUtil.parseDateFormat(noticeFloatingRateDto.getDepositDate()));
    }

    // 満期日
    if (StringUtils.isNotEmpty(noticeFloatingRateDto.getMaturityDate())) {
      param.put("maturityDate",commonUtil.parseDateFormat(noticeFloatingRateDto.getMaturityDate()));
    }

    // お預かり金額
    if (noticeFloatingRateDto.getDepositAmount() != null) {
      param.put("depositAmount",CommonNumberUtil.addComma(noticeFloatingRateDto.getDepositAmount()));
    }

    // お預かり番号
    if (StringUtils.isNotEmpty(noticeFloatingRateDto.getDepositNumber())) {
      param.put("depositNumber",noticeFloatingRateDto.getDepositNumber());
    }

    // 税区分
    if (StringUtils.isNotEmpty(noticeFloatingRateDto.getTaxClass())) {
      param.put("taxClass",noticeFloatingRateDto.getTaxClass());
    }
    
    // 単利/複利
     if (StringUtils.isNotEmpty(noticeFloatingRateDto.getSimpleOrCompoundInterest())) {
     param.put("simpleOrCompoundInterest",noticeFloatingRateDto.getSimpleOrCompoundInterest());
    }

    // 日数
     if (noticeFloatingRateDto.getPeriodDays() != null) {
     param.put("periodDays",noticeFloatingRateDto.getPeriodDays() + BankBookCommonConstants.DAY);
    }

    // 利率
     if (StringUtils.isNotEmpty(noticeFloatingRateDto.getInterestRate())) {
     param.put("interestRate",noticeFloatingRateDto.getInterestRate() + BankBookCommonConstants.PERCENT);
    }

    // 中間利払日
     if (StringUtils.isNotEmpty(noticeFloatingRateDto.getInterimInterestPaymentDate())) {
     param.put("interimInterestPaymentDate",commonUtil.parseDateFormat(noticeFloatingRateDto.getInterimInterestPaymentDate()));
    }

    // 中間利払利率
     if (StringUtils.isNotEmpty(noticeFloatingRateDto.getInterimPaymentInterestRate())) {
     param.put("interimPaymentInterestRate",noticeFloatingRateDto.getInterimPaymentInterestRate() + BankBookCommonConstants.PERCENT);
    }

    // 中間払い利息
     if (noticeFloatingRateDto.getInterimPaymentInterest() != null) {
     param.put("interimPaymentInterest",CommonNumberUtil.addComma(noticeFloatingRateDto.getInterimPaymentInterest()));
    }

    // 税金（国税）
     if (noticeFloatingRateDto.getNationalTax() != null) {
     param.put("nationalTax",CommonNumberUtil.addComma(noticeFloatingRateDto.getNationalTax()));
    }

    // 税金（地方税）
     if (noticeFloatingRateDto.getLocalTax() != null) {
     param.put("localTax",CommonNumberUtil.addComma(noticeFloatingRateDto.getLocalTax()));
    }

    // 税引後中間払い利息
     if (noticeFloatingRateDto.getDeductionInterimPaymentInterest() != null) {
     param.put("deductionInterimPaymentInterest",CommonNumberUtil.addComma(noticeFloatingRateDto.getDeductionInterimPaymentInterest()));
    }

    // 新利率適用日
     if (StringUtils.isNotEmpty(noticeFloatingRateDto.getNewInterestRateStartDate())) {
     param.put("newInterestRateStartDate",commonUtil.parseDateFormat(noticeFloatingRateDto.getNewInterestRateStartDate()));
    }

    // 新利率
     if (StringUtils.isNotEmpty(noticeFloatingRateDto.getNewInterestRate())) {
     param.put("newInterestRate",noticeFloatingRateDto.getNewInterestRate() + BankBookCommonConstants.PERCENT);
    }

    // 税引後中間払い利息の取扱
     if (StringUtils.isNotEmpty(noticeFloatingRateDto.getTransferDesignationAccount())) {
     param.put("transferDesignationAccount",noticeFloatingRateDto.getTransferDesignationAccount() + "　へご入金");
    }

    // 課税対象利息
     if (noticeFloatingRateDto.getTaxableInterest() != null) {
     param.put("taxableInterest",CommonNumberUtil.addComma(noticeFloatingRateDto.getTaxableInterest()));
    }

    // 課税対象日数
     if (noticeFloatingRateDto.getTaxableDays() != null) {
     param.put("taxableDays",noticeFloatingRateDto.getTaxableDays() + BankBookCommonConstants.DAY);
    }

    // 基準となる指標金利
     if (StringUtils.isNotEmpty(noticeFloatingRateDto.getStandardBenchmarkInterestRate())) {
     param.put("standardBenchmarkInterestRate",noticeFloatingRateDto.getStandardBenchmarkInterestRate() + BankBookCommonConstants.PERCENT);
    }

    // 上乗せ利率
     if (StringUtils.isNotEmpty(noticeFloatingRateDto.getAdditionalInterestRate())) {
     param.put("additionalInterestRate",noticeFloatingRateDto.getAdditionalInterestRate() + BankBookCommonConstants.PERCENT);
    }

    // 適用新利率
     if (StringUtils.isNotEmpty(noticeFloatingRateDto.getApplyNewInterestRate())) {
     param.put("applyNewInterestRate",noticeFloatingRateDto.getApplyNewInterestRate() + BankBookCommonConstants.PERCENT);
    }

    // 取扱店
     if (StringUtils.isNotEmpty(noticeFloatingRateDto.getBranchName())) {
     param.put("branchName",noticeFloatingRateDto.getBranchName());
    }

    // 電話番号
     if (StringUtils.isNotEmpty(noticeFloatingRateDto.getBranchPhoneNumber())) {
     param.put("branchPhoneNumber",noticeFloatingRateDto.getBranchPhoneNumber());
    }

     
    SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");

    // template path取得
    List<ReportDefinition> reportDefinitionList = reportDefinitionRepository.findByStartDateLessThanEqual(sdf.parse(noticeFloatingRateDto.getIssuedDate()));

    String NoticeFloatingRateReporId = bankBookConstantValues
        .findByKeyName(CugBankBookConstants.NOTICE_FLOATING_RATE_ID);

    // 帳票定義情報テーブル
    ReportDefinition reportDefinition = reportDefinitionList.stream()
        .filter(x -> NoticeFloatingRateReporId.equals(x.getReportTypeId()))
        .sorted(Comparator.comparing(ReportDefinition::getReportVersion, Comparator.reverseOrder())).findFirst()
        .get();

    // pdfServiceに渡すListの処理の実装
    List<NoticeFloatingRateDto> NoticeFloatingRateList = new ArrayList<NoticeFloatingRateDto>();
    NoticeFloatingRateList.add(noticeFloatingRateDto);

    // PDF作成サービスクラス呼び出し
    byte[] bytes = pdfService.pdfExport(reportDefinition.getReportFormatFilePath(), NoticeFloatingRateList, param);

    StoredDataHistory storedDataHistory = new StoredDataHistory();
    storedDataHistory.setUserId(userId);
    storedDataHistory.setAccountId(accountId);
    String dataSection = CugCommonConstants.NOTICE_FLOATING_RATE_REPORT_TYPE_ID + "_" + CugCommonConstants.TYPE_006;
    storedDataHistory.setDataSection(dataSection);
    storedDataHistory.setReportId(reportId);
    storedDataHistory.setReferenceDate(noticeFloatingRateDto.getIssuedDate());
    storedDataHistory.setReportVersion(reportDefinition.getReportVersion());
    storedDataHistory.setReportTypeId(NoticeFloatingRateReporId);

    // 蓄積データ参照履歴情報テーブルに登録する
    storedDataHistoryRepository.save(storedDataHistory);

    return bytes;

  }

}