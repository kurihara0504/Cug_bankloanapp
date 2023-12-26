package com.moneyforward.cug_bankbookbase.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

/**
 * 外貨定期預金預入のご案内の詳細.
 *
 * 業務API「外貨定期預金預入案内（オープン式)照会」からのレスポンス
 */
@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
  "foreignTimeDepositNotificationsOpenId",
  "displayDocumentName",
  "displayProductName",
  "applicantName",
  "productName",
  "transactionName",
  "reportType",
  "continuationType",
  "depositNumber",
  "currencyCode",
  "depositAmount",
  "depositDate",
  "maturityDate",
  "periodMonths",
  "periodDays",
  "interestRate",
  "taxClass",
  "principalAndInterestHandling1",
  "principalAndInterestHandling2",
  "issuedDate",
  "branchName",
  "branchPhoneNumber"
})
public class ForeignCurrencyDepositOpenDto {

  // ! 外貨定期預金預入案内（オープン式）識別子
  @JsonProperty("foreignTimeDepositNotificationsOpenId")
  private String foreignTimeDepositNotificationsOpenId;
  // ! 通知または書類名
  @JsonProperty("displayDocumentName")
  private String displayDocumentName;
  // ! 商品名（表示用）
  @JsonProperty("displayProductName")
  private String displayProductName;
  // ! 名義人
  @JsonProperty("applicantName")
  private String applicantName;
  // ! 商品名
  @JsonProperty("productName")
  private String productName;
  // ! 取引名
  @JsonProperty("transactionName")
  private String transactionName;
  // ! 種類
  @JsonProperty("reportType")
  private String reportType;
  // ! 継続区分
  @JsonProperty("continuationType")
  private String continuationType;
  // ! お預り番号
  @JsonProperty("depositNumber")
  private String depositNumber;
  // ! 幣種
  @JsonProperty("currencyCode")
  private String currencyCode;
  // ! お預り金額
  @JsonProperty("depositAmount")
  private String depositAmount;
  // ! 預入日
  @JsonProperty("depositDate")
  private String depositDate;
 // ! 満期日
  @JsonProperty("maturityDate")
  private String maturityDate;
  // ! 期間（月数）
  @JsonProperty("periodMonths")
  private Long periodMonths;
  // ! 期間（日数）
  @JsonProperty("periodDays")
  private Long periodDays;
  // ! 利率
@JsonProperty("interestRate")
  private String interestRate;
  // ! 税区分
  @JsonProperty("taxClass")
  private String taxClass;
  // ! 元金・利息のお取り扱い1
  @JsonProperty("principalAndInterestHandling1")
  private String principalAndInterestHandling1;
  // ! 元金・利息のお取り扱い2
  @JsonProperty("principalAndInterestHandling2")
  private String principalAndInterestHandling2;
  // ! 作成日
  @JsonProperty("issuedDate")
  private String issuedDate;
  // ! 取扱店名
  @JsonProperty("branchName")
  private String branchName;
  // ! 取扱店電話番号
  @JsonProperty("branchPhoneNumber")
  private String branchPhoneNumber;

  /**
   * 外貨定期預金預入案内（オープン式）識別子.
   * 
   * 外貨定期預金預入案内（オープン式）識別子を呼び出し元に返却する
   * @return 外貨定期預金預入案内（オープン式）識別子
   */
  public String getForeignTimeDepositNotificationsOpenId() {
    return foreignTimeDepositNotificationsOpenId;
  }
  /**
   * 外貨定期預金預入案内（オープン式）識別子を設定する.
   * 
   * 受け取った外貨定期預金預入案内（オープン式）識別子を設定する
   * @param foreignTimeDepositNotificationsOpenId 外貨定期預金預入案内（オープン式）識別子
   */
  public void setForeignTimeDepositNotificationsOpenId(String foreignTimeDepositNotificationsOpenId) {
    this.foreignTimeDepositNotificationsOpenId = foreignTimeDepositNotificationsOpenId;
  }
  /**
   * 通知または書類名を取得する.
   * 
   * 通知または書類名を呼び出し元に返却する
   * @return 通知または書類名
   */
  public String getDisplayDocumentName() {
    return displayDocumentName;
  }
  /**
   * 通知または書類名を設定する.
   * 
   * 受け取った通知または書類名を設定する
   * @param displayDocumentName 通知または書類名
   */
  public void setDisplayDocumentName(String displayDocumentName) {
    this.displayDocumentName = displayDocumentName;
  }
  /**
   * 商品名（表示用）を取得する.
   * 
   * 商品名（表示用）を呼び出し元に返却する
   * @return 商品名（表示用）
   */
  public String getDisplayProductName() {
    return displayProductName;
  }
  /**
   * 商品名（表示用）を設定する.
   * 
   * 受け取った商品名（表示用）を設定する
   * @param displayProductName 商品名（表示用）
   */
  public void setDisplayProductName(String displayProductName) {
    this.displayProductName = displayProductName;
  }
  /**
   * 名義人を取得する.
   * 
   * 名義人を呼び出し元に返却する
   * @return 名義人
   */
  public String getApplicantName() {
    return applicantName;
  }
  /**
   * 名義人を設定する.
   * 
   * 受け取った名義人を設定する
   * @param applicantName 名義人
   */
  public void setApplicantName(String applicantName) {
    this.applicantName = applicantName;
  }
  /**
   * 商品名を取得する.
   * 
   * 商品名を呼び出し元に返却する
   * @return 商品名
   */
  public String getProductName() {
    return productName;
  }
  /**
   * 商品名を設定する.
   * 
   * 受け取った商品名を設定する
   * @param productName 商品名
   */
  public void setProductName(String productName) {
    this.productName = productName;
  }
  /**
   * 取引名を取得する.
   * 
   * 取引名を呼び出し元に返却する
   * @return 取引名
   */
  public String getTransactionName() {
    return transactionName;
  }
  /**
   * 取引名を設定する.
   *
   * 受け取った取引名を設定する 
   * @param transactionName 取引名
   */
  public void setTransactionName(String transactionName) {
    this.transactionName = transactionName;
  }
  /**
   * 種類を取得する.
   * 
   * 種類を呼び出し元に返却する
   * @return 種類
   */
  public String getReportType() {
    return reportType;
  }
  /**
   * 種類を設定する.
   * 
   * 受け取った種類を設定する
   * @param reportType 種類
   */
  public void setReportType(String reportType) {
    this.reportType = reportType;
  }
  /**
   * 継続区分を取得する.
   * 
   * 継続区分を呼び出し元に返却する
   * @return 継続区分
   */
  public String getContinuationType() {
    return continuationType;
  }
  /**
   * 継続区分を設定する.
   * 
   * 受け取った継続区分を設定する
   * @param continuationType 継続区分
   */
  public void setContinuationType(String continuationType) {
    this.continuationType = continuationType;
  }
  /**
   * お預り番号を取得する.
   * 
   * お預り番号を呼び出し元に返却する
   * @return お預り番号
   */
  public String getDepositNumber() {
    return depositNumber;
  }
  /**
   * お預り番号を設定する.
   * 
   * 受け取ったお預り番号を設定する
   * @param depositNumber お預り番号
   */
  public void setDepositNumber(String depositNumber) {
    this.depositNumber = depositNumber;
  }
  /**
   * 幣種を取得する.
   * 
   * 幣種を呼び出し元に返却する
   * @return 幣種
   */
  public String getCurrencyCode() {
    return currencyCode;
  }
  /**
   * 幣種を設定する.
   * 
   * 受け取った幣種を設定する
   * @param currencyCode 幣種
   */
  public void setCurrencyCode(String currencyCode) {
    this.currencyCode = currencyCode;
  }
  /**
   * お預り金額を取得する.
   * 
   * お預り金額を呼び出し元に返却する
   * @return お預り金額
   */
  public String getDepositAmount() {
    return depositAmount;
  }
  /**
   * お預り金額を設定する.
   * 
   * 受け取ったお預り金額を設定する
   * @param depositAmount お預り金額
   */
  public void setDepositAmount(String depositAmount) {
    this.depositAmount = depositAmount;
  }
  /**
   * 預入日を取得する.
   * 
   * 預入日を呼び出し元に返却する
   * @return 預入日
   */
  public String getDepositDate() {
    return depositDate;
  }
  /**
   * 預入日を設定する.
   * 
   * 受け取った預入日を設定する
   * @param depositDate 預入日
   */
  public void setDepositDate(String depositDate) {
    this.depositDate = depositDate;
  }
  /**
   * 満期日を取得する.
   * 
   * 満期日を呼び出し元に返却する
   * @return 満期日
   */
  public String getMaturityDate() {
    return maturityDate;
  }
  /**
   * 満期日を設定する.
   *
   * 受け取った満期日を設定する 
   * @param maturityDate 満期日
   */
  public void setMaturityDate(String maturityDate) {
    this.maturityDate = maturityDate;
  }
  /**
   * 期間（月数）を取得する.
   * 
   * 期間（月数）を呼び出し元に返却する
   * @return 期間（月数）
   */
  public Long getPeriodMonths() {
    return periodMonths;
  }
  /**
   * 期間（月数）を設定する.
   * 
   * 受け取った期間（月数）を設定する
   * @param periodMonths 期間（月数）
   */
  public void setPeriodMonths(Long periodMonths) {
    this.periodMonths = periodMonths;
  }
  /**
   * 期間（日数）を取得する.
   * 
   * 期間（日数）を呼び出し元に返却する
   * @return 期間（日数）
   */
  public Long getPeriodDays() {
    return periodDays;
  }
  /**
   * 期間（日数）を設定する.
   * 
   * 受け取った期間（日数）を設定する
   * @param periodDays 期間（日数）
   */
  public void setPeriodDays(Long periodDays) {
    this.periodDays = periodDays;
  }
  /**
   * 利率を取得する.
   * 
   * 利率を呼び出し元に返却する
   * @return 利率
   */
  public String getInterestRate() {
    return interestRate;
  }
  /**
   * 利率を設定する.
   * 
   * 受け取った利率を設定する
   * @param interestRate 利率
   */
  public void setInterestRate(String interestRate) {
    this.interestRate = interestRate;
  }
  /**
   * 税区分を取得する.
   * 
   * 税区分を呼び出し元に返却する
   * @return 税区分
   */
  public String getTaxClass() {
    return taxClass;
  }
  /**
   * 税区分を設定する.
   * 
   * 受け取った税区分を設定する
   * @param taxClass 税区分
   */
  public void setTaxClass(String taxClass) {
    this.taxClass = taxClass;
  }
  /**
   * 元金・利息のお取り扱い1を取得する.
   * 
   * 元金・利息のお取り扱い1を呼び出し元に返却する
   * @return 元金・利息のお取り扱い1
   */
  public String getPrincipalAndInterestHandling1() {
    return principalAndInterestHandling1;
  }
  /**
   * 元金・利息のお取り扱い1を設定する.
   * 
   * 受け取った元金・利息のお取り扱い1を設定する
   * @param principalAndInterestHandling1 元金・利息のお取り扱い1
   */
  public void setPrincipalAndInterestHandling1(String principalAndInterestHandling1) {
    this.principalAndInterestHandling1 = principalAndInterestHandling1;
  }
  /**
   * 元金・利息のお取り扱い2を取得する.
   * 
   * 元金・利息のお取り扱い2を呼び出し元に返却する
   * @return 元金・利息のお取り扱い2
   */
  public String getPrincipalAndInterestHandling2() {
    return principalAndInterestHandling2;
  }
  /**
   * 元金・利息のお取り扱い2を設定する.
   * 
   * 受け取った元金・利息のお取り扱い2を設定する
   * @param principalAndInterestHandling2 元金・利息のお取り扱い2
   */
  public void setPrincipalAndInterestHandling2(String principalAndInterestHandling2) {
    this.principalAndInterestHandling2 = principalAndInterestHandling2;
  }
  /**
   * 作成日を取得する.
   * 
   * 作成日を呼び出し元に返却する
   * @return 作成日
   */
  public String getIssuedDate() {
    return issuedDate;
  }
  /**
   * 作成日を設定する.
   * 
   * 受け取った作成日を設定する
   * @param issuedDate 作成日
   */
  public void setIssuedDate(String issuedDate) {
    this.issuedDate = issuedDate;
  }
  /**
   * 取扱店名を取得する.
   * 
   * 取扱店名を呼び出し元に返却する
   * @return 取扱店名
   */
  public String getBranchName() {
    return branchName;
  }
  /**
   * 取扱店名を設定する.
   *
   * 受け取った取扱店名を設定する 
   * @param branchName 取扱店名
   */
  public void setBranchName(String branchName) {
    this.branchName = branchName;
  }
  /**
   * 取扱店電話番号を取得する.
   * 
   * 取扱店電話番号を呼び出し元に返却する
   * @return 取扱店電話番号
   */
  public String getBranchPhoneNumber() {
    return branchPhoneNumber;
  }
  /**
   * 取扱店電話番号を設定する.
   * 
   * 受け取った取扱店電話番号を設定する
   * @param branchPhoneNumber 取扱店電話番号
   */
  public void setBranchPhoneNumber(String branchPhoneNumber) {
    this.branchPhoneNumber = branchPhoneNumber;
  }  
 
}
