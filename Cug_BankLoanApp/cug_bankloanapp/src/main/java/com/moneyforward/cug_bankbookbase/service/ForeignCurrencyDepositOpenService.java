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
import com.moneyforward.cug_bankbookbase.dto.ForeignCurrencyDepositOpenDto;
import com.moneyforward.cug_bankbookbase.logic.ForeignCurrencyDepositOpenApiLogic;

/**
 * 外貨定期預金預入のご案内取得サービス
 *
 * 選択した外貨定期預金預入のご案内取得を行うサービスを呼び出すクラス
 */
@Service
@Transactional


public class ForeignCurrencyDepositOpenService {

  /** 蓄積データ参照履歴情報. */
  @Autowired
  private StoredDataHistoryRepository storedDataHistoryRepository;

  /** 定数取得クラス. */
  @Autowired
  private BankBookConstantValues bankBookConstantValues;

  /** 業務API「外貨定期預金預入のご案内照会」呼び出し. */
  @Autowired
  private ForeignCurrencyDepositOpenApiLogic foreignCurrencyDepositOpenApiLogic;

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
   * 外貨定期預金預入のご案内取得を行う.
   *
   * 外貨定期預金預入のご案内のバイナリファイルを取得し、蓄積データ参照履歴情報テーブルに登録する
   *
   * @param userId ユーザ識別子
   * @param accountId 口座識別子
   * @param reportId 外貨定期預金預入のご案内帳票識別子
   * @param type 科目コード
   * @return バイナリファイル
   * @throws ParseException 外貨定期預金預入のご案内取得にエラーが発生した場合
   */
  public byte[] getForeignCurrencyDepositOpenService(String userId, String accountId, String reportId, String type) throws ParseException {

    // 業務API「外貨定期預金預入案内(オープン式)照会」呼出
    ForeignCurrencyDepositOpenDto foreignCurrencyDepositOpenDto = foreignCurrencyDepositOpenApiLogic.callForeignCurrencyDepositOpenLogic(userId, accountId, reportId);

    // Parameterの定義
    HashMap<String, Object> param = new HashMap<String, Object>();

    // 名義人
    if (StringUtils.isNotEmpty(foreignCurrencyDepositOpenDto.getApplicantName())) {
      param.put("applicantName", foreignCurrencyDepositOpenDto.getApplicantName() + BankBookCommonConstants.HONORIFIC);
    }
    // 商品名
    if (StringUtils.isNotEmpty(foreignCurrencyDepositOpenDto.getProductName())) {
      param.put("productName", foreignCurrencyDepositOpenDto.getProductName());
    }
    // 取引名
    if (StringUtils.isNotEmpty(foreignCurrencyDepositOpenDto.getTransactionName())) {
      param.put("transactionName", foreignCurrencyDepositOpenDto.getTransactionName());
    }
    // 種類
    if (StringUtils.isNotEmpty(foreignCurrencyDepositOpenDto.getReportType())) {
      param.put("reportType", foreignCurrencyDepositOpenDto.getReportType());
    }
    // 継続区分
    if (StringUtils.isNotEmpty(foreignCurrencyDepositOpenDto.getContinuationType())) {
    	param.put("continuationType", foreignCurrencyDepositOpenDto.getContinuationType());
    }
    // お預り番号
    if (StringUtils.isNotEmpty(foreignCurrencyDepositOpenDto.getDepositNumber())) {
      param.put("depositNumber",foreignCurrencyDepositOpenDto.getDepositNumber());
    }
    // 幣種
    if (StringUtils.isNotEmpty(foreignCurrencyDepositOpenDto.getCurrencyCode())) {
      param.put("currencyCode", foreignCurrencyDepositOpenDto.getCurrencyCode());
    }
    // お預り金額
    if (StringUtils.isNotEmpty(foreignCurrencyDepositOpenDto.getDepositAmount())) {
      param.put("depositAmount", CommonNumberUtil.addCommaDecimal(foreignCurrencyDepositOpenDto.getDepositAmount()));
    }
    // 預入日
    if (StringUtils.isNotEmpty(foreignCurrencyDepositOpenDto.getDepositDate())) {
      param.put("depositDate",commonUtil.parseDateFormat(foreignCurrencyDepositOpenDto.getDepositDate()));    
    }
    // 満期日
    if (StringUtils.isNotEmpty(foreignCurrencyDepositOpenDto.getMaturityDate())) {
      param.put("maturityDate",commonUtil.parseDateFormat(foreignCurrencyDepositOpenDto.getMaturityDate()));    
    }
    // 期間（月数）
    if (foreignCurrencyDepositOpenDto.getPeriodMonths() != null) {
      param.put("periodMonths", foreignCurrencyDepositOpenDto.getPeriodMonths() + BankBookCommonConstants.MONTH);
    }
    // 期間（日数）
    if (foreignCurrencyDepositOpenDto.getPeriodDays() != null) {
      param.put("periodDays", foreignCurrencyDepositOpenDto.getPeriodDays() + BankBookCommonConstants.DAY);
    }
    // 利率
    if (StringUtils.isNotEmpty(foreignCurrencyDepositOpenDto.getInterestRate())) {
      param.put("interestRate", foreignCurrencyDepositOpenDto.getInterestRate() + BankBookCommonConstants.PERCENT);
    }
    // 税区分
    if (StringUtils.isNotEmpty(foreignCurrencyDepositOpenDto.getTaxClass())) {
      param.put("taxClass", foreignCurrencyDepositOpenDto.getTaxClass());
    }
    // 元金・利息のお取り扱い1
    if (StringUtils.isNotEmpty(foreignCurrencyDepositOpenDto.getPrincipalAndInterestHandling1())) {
      param.put("principalAndInterestHandling1", foreignCurrencyDepositOpenDto.getPrincipalAndInterestHandling1());
    }
    // 元金・利息のお取り扱い2
    if (StringUtils.isNotEmpty(foreignCurrencyDepositOpenDto.getPrincipalAndInterestHandling2())) {
      param.put("principalAndInterestHandling2", foreignCurrencyDepositOpenDto.getPrincipalAndInterestHandling2());
    }
    // 作成日
    param.put("issuedDate",commonUtil.parseDateFormat(foreignCurrencyDepositOpenDto.getIssuedDate()));    
    // 取扱店名
    if (StringUtils.isNotEmpty(foreignCurrencyDepositOpenDto.getBranchName())) {
      param.put("branchName",foreignCurrencyDepositOpenDto.getBranchName());
    }
    // 取扱店電話番号
    if (StringUtils.isNotEmpty(foreignCurrencyDepositOpenDto.getBranchPhoneNumber())) {
     param.put("branchPhoneNumber",foreignCurrencyDepositOpenDto.getBranchPhoneNumber());
     }
     
    SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");

    // template path取得
    List<ReportDefinition> reportDefinitionList = reportDefinitionRepository.findByStartDateLessThanEqual(sdf.parse(foreignCurrencyDepositOpenDto.getIssuedDate()));

    String foreignCurrencyDepositOpenReporId = bankBookConstantValues
        .findByKeyName(CugBankBookConstants.FOREIGN_CURRENCY_DEPOSIT_OPEN_ID);

    // 帳票定義情報テーブル
    ReportDefinition reportDefinition = reportDefinitionList.stream()
        .filter(x -> foreignCurrencyDepositOpenReporId.equals(x.getReportTypeId()))
        .sorted(Comparator.comparing(ReportDefinition::getReportVersion, Comparator.reverseOrder())).findFirst()
        .get();

    // pdfServiceに渡すListの処理の実装
    List<ForeignCurrencyDepositOpenDto> foreignCurrencyDepositOpenList = new ArrayList<ForeignCurrencyDepositOpenDto>();
    foreignCurrencyDepositOpenList.add(foreignCurrencyDepositOpenDto);

    // PDF作成サービスクラス呼び出し
    byte[] bytes = pdfService.pdfExport(reportDefinition.getReportFormatFilePath(), foreignCurrencyDepositOpenList, param);

    StoredDataHistory storedDataHistory = new StoredDataHistory();
    storedDataHistory.setUserId(userId);
    storedDataHistory.setAccountId(accountId);
    String dataSection = CugCommonConstants.FOREIGN_CURRENCY_DEPOSIT_OPEN_REPORT_TYPE_ID + "_" + type;
    storedDataHistory.setDataSection(dataSection);
    storedDataHistory.setReportId(reportId);
    storedDataHistory.setReferenceDate(foreignCurrencyDepositOpenDto.getIssuedDate());
    storedDataHistory.setReportVersion(reportDefinition.getReportVersion());
    storedDataHistory.setReportTypeId(foreignCurrencyDepositOpenReporId);

    // 蓄積データ参照履歴情報テーブルに登録する
    storedDataHistoryRepository.save(storedDataHistory);

    return bytes;

  }

}