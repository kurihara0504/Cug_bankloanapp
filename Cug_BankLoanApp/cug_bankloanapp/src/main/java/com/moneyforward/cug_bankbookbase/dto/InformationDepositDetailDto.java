package com.moneyforward.cug_bankbookbase.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

/**
 * 定期預金満期案内の明細部の詳細.
 *
 * 業務API「定期預金満期案内の明細部」からのレスポンス
 */
@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
  "interestPaymentDate",
  "depositNumber",
  "timeDepositType",
  "handlingType",
  "depositAmount",
  "interest",
  "period",
  "interestAmount",
  "taxClass",
  "nationalTax",
  "localTax",
  "deductionPaymentInterest",
  "renewalPrincipalValue",
  "interestHandling",
  "transferDesignationAccount",
  "maruyuTaxableInterest",
  "maruyuTaxableDays",
  "remarks",
  "interest2",
  "period2",
  "interestAmount2",
  "paymentRecord"
})
public class InformationDepositDetailDto {

  /** 期日・中間利払日 */
  @JsonProperty("interestPaymentDate")
  private String interestPaymentDate;

  /** お預り番号 */
  @JsonProperty("depositNumber")
  private String depositNumber;

  /** 定期預金種類 */
  @JsonProperty("timeDepositType")
  private String timeDepositType;

  /** 取扱内容等 */
  @JsonProperty("handlingType")
  private String handlingType;

  /** お預り金額（円） */
  @JsonProperty("depositAmount")
  private Long depositAmount;

  /** （中間利払）利率 */
  @JsonProperty("interest")
  private String interest;

  /** 期間 */
  @JsonProperty("period")
  private String period;

  /** お利息（円） */
  @JsonProperty("interestAmount")
  private Long interestAmount;

  /** 税区分 */
  @JsonProperty("taxClass")
  private String taxClass;

  /** 国税 */
  @JsonProperty("nationalTax")
  private Long nationalTax;

  /** 地方税 */
  @JsonProperty("localTax")
  private Long localTax;

  /** 差引お支払利息（円） */
  @JsonProperty("deductionPaymentInterest")
  private Long deductionPaymentInterest;

  /** ご継続後の新元金（円） */
  @JsonProperty("renewalPrincipalValue")
  private Long renewalPrincipalValue;

  /** お利息の取扱い */
  @JsonProperty("interestHandling")
  private String interestHandling;

  /** お振替指定口座 */
  @JsonProperty("transferDesignationAccount")
  private String transferDesignationAccount;

  /** （マル優）課税対象利息 */
  @JsonProperty("maruyuTaxableInterest")
  private Long maruyuTaxableInterest;

  /** （マル優）課税対象日数 */
  @JsonProperty("maruyuTaxableDays")
  private Long maruyuTaxableDays;

  /** 備考 */
  @JsonProperty("remarks")
  private String remarks;

  /** （中間利払）利率２ */
  @JsonProperty("interest2")
  private String interest2;

  /** 期間２ */
  @JsonProperty("period2")
  private String period2;

  /** お利息（円）２ */
  @JsonProperty("interestAmount2")
  private Long interestAmount2;

  /** 支払調書 */
  @JsonProperty("paymentRecord")
  private String paymentRecord;

  /**
   * 期日・中間利払日を取得する.
   * 
   * 期日・中間利払日を呼び出し元に返却する
   * @return 期日・中間利払日
   */
  public String getInterestPaymentDate() {
    return interestPaymentDate;
  }

  /**
   * 期日・中間利払日を設定する.
   * 
   * 受け取った期日・中間利払日を設定する
   * @param interestPaymentDate 期日・中間利払日
   */
  public void setInterestPaymentDate(String interestPaymentDate) {
    this.interestPaymentDate = interestPaymentDate;
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
   * 定期預金種類を取得する.
   * 
   * 定期預金種類を呼び出し元に返却する
   * @return 定期預金種類
   */
  public String getTimeDepositType() {
    return timeDepositType;
  }

  /**
   * 定期預金種類を設定する.
   * 
   * 受け取った定期預金種類を設定する
   * @param timeDepositType 定期預金種類
   */
  public void setTimeDepositType(String timeDepositType) {
    this.timeDepositType = timeDepositType;
  }

  /**
   * 取扱内容等を取得する.
   * 
   * 取扱内容等を呼び出し元に返却する
   * @return 取扱内容等
   */
  public String getHandlingType() {
    return handlingType;
  }

  /**
   * 取扱内容等を設定する.
   * 
   * 受け取った取扱内容等を設定する
   * @param handlingType 取扱内容等
   */
  public void setHandlingType(String handlingType) {
    this.handlingType = handlingType;
  }

  /**
   * お預り金額（円）を取得する.
   * 
   * お預り金額（円）を呼び出し元に返却する
   * @return お預り金額（円）
   */
  public Long getDepositAmount() {
    return depositAmount;
  }

  /**
   * お預り金額（円）を設定する.
   * 
   * 受け取ったお預り金額（円）を設定する
   * @param depositAmount お預り金額（円）
   */
  public void setDepositAmount(Long depositAmount) {
    this.depositAmount = depositAmount;
  }

  /**
   * （中間利払）利率を取得する.
   * 
   * （中間利払）利率を呼び出し元に返却する
   * @return （中間利払）利率
   */
  public String getInterest() {
    return interest;
  }

  /**
   * （中間利払）利率を設定する.
   * 
   * 受け取った（中間利払）利率を設定する
   * @param interest （中間利払）利率
   */
  public void setInterest(String interest) {
    this.interest = interest;
  }

  /**
   * 期間を取得する.
   * 
   * 期間を呼び出し元に返却する
   * @return 期間
   */
  public String getPeriod() {
    return period;
  }

  /**
   * 期間を設定する.
   * 
   * 受け取った期間を設定する
   * @param period 期間
   */
  public void setPeriod(String period) {
    this.period = period;
  }

  /**
   * お利息（円）を取得する.
   * 
   * お利息（円）を呼び出し元に返却する
   * @return お利息（円）
   */
  public Long getInterestAmount() {
    return interestAmount;
  }

  /**
   * お利息（円）を設定する.
   * 
   * 受け取ったお利息（円）を設定する
   * @param interestAmount お利息（円）
   */
  public void setInterestAmount(Long interestAmount) {
    this.interestAmount = interestAmount;
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
   * 国税を取得する.
   * 
   * 国税を呼び出し元に返却する
   * @return 国税
   */
  public Long getNationalTax() {
    return nationalTax;
  }

  /**
   * 国税を設定する.
   * 
   * 受け取った国税を設定する
   * @param nationalTax 国税
   */
  public void setNationalTax(Long nationalTax) {
    this.nationalTax = nationalTax;
  }

  /**
   * 地方税を取得する.
   * 
   * 地方税を呼び出し元に返却する
   * @return 地方税
   */
  public Long getLocalTax() {
    return localTax;
  }

  /**
   * 地方税を設定する.
   * 
   * 受け取った地方税を設定する
   * @param localTax 地方税
   */
  public void setLocalTax(Long localTax) {
    this.localTax = localTax;
  }

  /**
   * 差引お支払利息（円）を取得する.
   * 
   * 差引お支払利息（円）を呼び出し元に返却する
   * @return 差引お支払利息（円）
   */
  public Long getDeductionPaymentInterest() {
    return deductionPaymentInterest;
  }

  /**
   * 差引お支払利息（円）を設定する.
   * 
   * 受け取った差引お支払利息（円）を設定する
   * @param deductionPaymentInterest 差引お支払利息（円）
   */
  public void setDeductionPaymentInterest(Long deductionPaymentInterest) {
    this.deductionPaymentInterest = deductionPaymentInterest;
  }

  /**
   * ご継続後の新元金（円）を取得する.
   * 
   * ご継続後の新元金（円）を呼び出し元に返却する
   * @return ご継続後の新元金（円）
   */
  public Long getRenewalPrincipalValue() {
    return renewalPrincipalValue;
  }

  /**
   * ご継続後の新元金（円）を設定する.
   *
   * 受け取ったご継続後の新元金（円）を設定する 
   * @param renewalPrincipalValue ご継続後の新元金（円）
   */
  public void setRenewalPrincipalValue(Long renewalPrincipalValue) {
    this.renewalPrincipalValue = renewalPrincipalValue;
  }

  /**
   * お利息の取扱いを取得する.
   * 
   * お利息の取扱いを呼び出し元に返却する
   * @return お利息の取扱い
   */
  public String getInterestHandling() {
    return interestHandling;
  }

  /**
   * お利息の取扱いを設定する.
   * 
   * 受け取ったお利息の取扱いを設定する
   * @param interestHandling お利息の取扱い
   */
  public void setInterestHandling(String interestHandling) {
    this.interestHandling = interestHandling;
  }

  /**
   * お振替指定口座を取得する.
   * 
   * お振替指定口座を呼び出し元に返却する
   * @return お振替指定口座
   */
  public String getTransferDesignationAccount() {
    return transferDesignationAccount;
  }

  /**
   * お振替指定口座を設定する.
   * 
   * 受け取ったお振替指定口座を設定する
   * @param transferDesignationAccount お振替指定口座
   */
  public void setTransferDesignationAccount(String transferDesignationAccount) {
    this.transferDesignationAccount = transferDesignationAccount;
  }

  /**
   * （マル優）課税対象利息を取得する.
   * 
   * （マル優）課税対象利息を呼び出し元に返却する
   * @return （マル優）課税対象利息
   */
  public Long getMaruyuTaxableInterest() {
    return maruyuTaxableInterest;
  }

  /**
   * （マル優）課税対象利息を設定する.
   *
   * 受け取った（マル優）課税対象利息を設定する
   * @param maruyuTaxableInterest （マル優）課税対象利息
   */
  public void setMaruyuTaxableInterest(Long maruyuTaxableInterest) {
    this.maruyuTaxableInterest = maruyuTaxableInterest;
  }

  /**
   * （マル優）課税対象日数を取得する.
   * 
   * （マル優）課税対象日数を呼び出し元に返却する
   * @return （マル優）課税対象日数
   */
  public Long getMaruyuTaxableDays() {
    return maruyuTaxableDays;
  }

  /**
   * （マル優）課税対象日数を設定する.
   * 
   * 受け取った（マル優）課税対象日数を設定する
   * @param maruyuTaxableDays （マル優）課税対象日数
   */
  public void setMaruyuTaxableDays(Long maruyuTaxableDays) {
    this.maruyuTaxableDays = maruyuTaxableDays;
  }

  /**
   * 備考を取得する.
   * 
   * 備考を呼び出し元に返却する
   * @return 備考
   */
  public String getRemarks() {
    return remarks;
  }

  /**
   * 備考を設定する.
   * 
   * 受け取った備考を設定する
   * @param remarks 備考
   */
  public void setRemarks(String remarks) {
    this.remarks = remarks;
  }

  /**
   * （中間利払）利率２を取得する.
   * 
   * （中間利払）利率２を呼び出し元に返却する
   * @return （中間利払）利率２
   */
  public String getInterest2() {
    return interest2;
  }

  /**
   * （中間利払）利率２を設定する.
   * 
   * 受け取った（中間利払）利率２を設定する
   * @param interest2 （中間利払）利率２
   */
  public void setInterest2(String interest2) {
    this.interest2 = interest2;
  }

  /**
   * 期間２を取得する.
   * 
   * 期間２を呼び出し元に返却する
   * @return 期間２
   */
  public String getPeriod2() {
    return period2;
  }

  /**
   * 期間２を設定する.
   * 
   * 受け取った期間２を設定する
   * @param period2 期間２
   */
  public void setPeriod2(String period2) {
    this.period2 = period2;
  }

  /**
   * お利息（円）２を取得する.
   * 
   * お利息（円）２を呼び出し元に返却する
   * @return お利息（円）２
   */
  public Long getInterestAmount2() {
    return interestAmount2;
  }

  /**
   * お利息（円）２を設定する.
   *
   * 受け取ったお利息（円）２を設定する 
   * @param interestAmount2 お利息（円）２
   */
  public void setInterestAmount2(Long interestAmount2) {
    this.interestAmount2 = interestAmount2;
  }

  /**
   * 支払調書を取得する.
   * 
   * 支払調書を呼び出し元に返却する
   * @return 支払調書
   */
  public String getPaymentRecord() {
    return paymentRecord;
  }

  /**
   * 支払調書を設定する.
   * 
   * 受け取った支払調書を設定する
   * @param paymentRecord 支払調書
   */
  public void setPaymentRecord(String paymentRecord) {
    this.paymentRecord = paymentRecord;
  }

}
