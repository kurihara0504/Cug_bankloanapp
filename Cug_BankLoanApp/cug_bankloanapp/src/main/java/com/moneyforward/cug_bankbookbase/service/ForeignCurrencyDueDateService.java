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
import com.moneyforward.cug_bankbookbase.dto.ForeignCurrencyDueDateDto;
import com.moneyforward.cug_bankbookbase.logic.ForeignCurrencyDueDateApiLogic;

/**
 * 外貨定期預金期日のご案内取得サービス
 *
 * 選択した外貨定期預金期日のご案内取得を行うサービスを呼び出すクラス
 */
@Service
@Transactional


public class ForeignCurrencyDueDateService {

  /** 蓄積データ参照履歴情報. */
  @Autowired
  private StoredDataHistoryRepository storedDataHistoryRepository;

  /** 定数取得クラス. */
  @Autowired
  private BankBookConstantValues bankBookConstantValues;

  /** 業務API「外貨定期預金期日案内照会」呼び出し. */
  @Autowired
  private ForeignCurrencyDueDateApiLogic foreignCurrencyDueDateApiLogic;

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
   * 外貨定期預金期日のご案内取得を行う.
   *
   * 外貨定期預金期日のご案内のバイナリファイルを取得し、蓄積データ参照履歴情報テーブルに登録する
   *
   * @param userId ユーザ識別子
   * @param accountId 口座識別子
   * @param reportId 外貨定期預金期日案内識別子
   * @param type 科目コード
   * @return バイナリファイル
   * @throws ParseException 外貨定期預金期日のご案内取得にエラーが発生した場合
   */
  public byte[] getForeignCurrencyDueDateService(String userId, String accountId, String reportId, String type) throws ParseException {

    // 業務API「外貨定期預金期日案内照会」呼出
    ForeignCurrencyDueDateDto foreignCurrencyDueDateDto = foreignCurrencyDueDateApiLogic.callForeignCurrencyDueDateLogic(userId, accountId, reportId);

    // Parameterの定義
    HashMap<String, Object> param = new HashMap<String, Object>();

    // 作成日
    param.put("issuedDate",commonUtil.parseDateFormat(foreignCurrencyDueDateDto.getIssuedDate()));

    // 名義人
    if (StringUtils.isNotEmpty(foreignCurrencyDueDateDto.getApplicantName())) {
      param.put("applicantName",foreignCurrencyDueDateDto.getApplicantName() + BankBookCommonConstants.HONORIFIC);
    }

    // 満期日
    if (StringUtils.isNotEmpty(foreignCurrencyDueDateDto.getMaturityDate())) {
      param.put("maturityDate",commonUtil.parseDateFormat(foreignCurrencyDueDateDto.getMaturityDate()));
    }

    // お預かり番号
    if (StringUtils.isNotEmpty(foreignCurrencyDueDateDto.getDepositNumber())) {
      param.put("depositNumber",foreignCurrencyDueDateDto.getDepositNumber());
    }

    // 通貨
    if (StringUtils.isNotEmpty(foreignCurrencyDueDateDto.getCurrencyCode())) {
      param.put("currencyCode",foreignCurrencyDueDateDto.getCurrencyCode());
    }

    // お預かり金額
    if (StringUtils.isNotEmpty(foreignCurrencyDueDateDto.getDepositAmount())) {
      param.put("depositAmount",CommonNumberUtil.addCommaDecimal(foreignCurrencyDueDateDto.getDepositAmount()));
    }

    // 満期時の取扱い
    if (StringUtils.isNotEmpty(foreignCurrencyDueDateDto.getAfterMaturityDate())) {
      param.put("afterMaturityDate",foreignCurrencyDueDateDto.getAfterMaturityDate());
    }

    // 利率
    if (StringUtils.isNotEmpty(foreignCurrencyDueDateDto.getInterestRate())) {
      param.put("interestRate",foreignCurrencyDueDateDto.getInterestRate() + BankBookCommonConstants.PERCENT);
    }
    
    // 期間
     if (StringUtils.isNotEmpty(foreignCurrencyDueDateDto.getPeriod())) {
     param.put("period",foreignCurrencyDueDateDto.getPeriod());
    }

    // 利息
     if (StringUtils.isBlank(foreignCurrencyDueDateDto.getInterest())) {
      param.put("interest","＊＊・・・＊＊");
    }else{
      param.put("interest",CommonNumberUtil.addCommaDecimal(foreignCurrencyDueDateDto.getInterest()));
    }

    // 税区分
     if (StringUtils.isNotEmpty(foreignCurrencyDueDateDto.getTaxClass())) {
     param.put("taxClass",foreignCurrencyDueDateDto.getTaxClass());
    }

    // 国税
     if (StringUtils.isBlank(foreignCurrencyDueDateDto.getNationalTax())) {
      param.put("nationalTax","＊＊・・・＊＊");
    }else{
      param.put("nationalTax",CommonNumberUtil.addCommaDecimal(foreignCurrencyDueDateDto.getNationalTax()));
    }

    // 地方税
     if (StringUtils.isBlank(foreignCurrencyDueDateDto.getLocalTax())) {
      param.put("localTax","＊＊・・・＊＊");
    }else{
      param.put("localTax",CommonNumberUtil.addCommaDecimal(foreignCurrencyDueDateDto.getLocalTax()));
    }

    // 差引支払利息
     if (StringUtils.isBlank(foreignCurrencyDueDateDto.getDeductionPaymentInterest())) {
      param.put("deductionPaymentInterest","＊＊・・・＊＊");
    }else{
      param.put("deductionPaymentInterest",CommonNumberUtil.addCommaDecimal(foreignCurrencyDueDateDto.getDeductionPaymentInterest()));
    }

    // 利息の取扱い
     if (StringUtils.isNotEmpty(foreignCurrencyDueDateDto.getInterestHandling())) {
     param.put("interestHandling",foreignCurrencyDueDateDto.getInterestHandling());
    }

    // 新定期金額
     if (StringUtils.isNotEmpty(foreignCurrencyDueDateDto.getNewTimeDepositAmount())) {
     param.put("newTimeDepositAmount",CommonNumberUtil.addCommaDecimal(foreignCurrencyDueDateDto.getNewTimeDepositAmount()));
    }

    // 取扱店名
     if (StringUtils.isNotEmpty(foreignCurrencyDueDateDto.getBranchName())) {
     param.put("branchName",foreignCurrencyDueDateDto.getBranchName());
    }

    // 取扱店電話番号
     if (StringUtils.isNotEmpty(foreignCurrencyDueDateDto.getBranchPhoneNumber())) {
     param.put("branchPhoneNumber",foreignCurrencyDueDateDto.getBranchPhoneNumber());
    }

     
    SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");

    // template path取得
    List<ReportDefinition> reportDefinitionList = reportDefinitionRepository.findByStartDateLessThanEqual(sdf.parse(foreignCurrencyDueDateDto.getIssuedDate()));

    String ForeignCurrencyDueDateReporId = bankBookConstantValues
        .findByKeyName(CugBankBookConstants.FOREIGN_CURRENCY_DUE_DATE_ID);

    // 帳票定義情報テーブル
    ReportDefinition reportDefinition = reportDefinitionList.stream()
        .filter(x -> ForeignCurrencyDueDateReporId.equals(x.getReportTypeId()))
        .sorted(Comparator.comparing(ReportDefinition::getReportVersion, Comparator.reverseOrder())).findFirst()
        .get();

    // pdfServiceに渡すListの処理の実装
    List<ForeignCurrencyDueDateDto> foreignCurrencyDueDateList = new ArrayList<ForeignCurrencyDueDateDto>();
    foreignCurrencyDueDateList.add(foreignCurrencyDueDateDto);

    // PDF作成サービスクラス呼び出し
    byte[] bytes = pdfService.pdfExport(reportDefinition.getReportFormatFilePath(), foreignCurrencyDueDateList, param);

    StoredDataHistory storedDataHistory = new StoredDataHistory();
    storedDataHistory.setUserId(userId);
    storedDataHistory.setAccountId(accountId);
    String dataSection = CugCommonConstants.FOREIGN_CURRENCY_DUE_DATE_REPORT_TYPE_ID + "_" + type;
    storedDataHistory.setDataSection(dataSection);
    storedDataHistory.setReportId(reportId);
    storedDataHistory.setReferenceDate(foreignCurrencyDueDateDto.getIssuedDate());
    storedDataHistory.setReportVersion(reportDefinition.getReportVersion());
    storedDataHistory.setReportTypeId(ForeignCurrencyDueDateReporId);

    // 蓄積データ参照履歴情報テーブルに登録する
    storedDataHistoryRepository.save(storedDataHistory);

    return bytes;

  }

}