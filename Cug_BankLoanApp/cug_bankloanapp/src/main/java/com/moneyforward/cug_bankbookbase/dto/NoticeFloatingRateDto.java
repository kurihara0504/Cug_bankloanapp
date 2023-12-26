package com.moneyforward.cug_bankbookbase.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

/**
 * 変動金利定期預金のお知らせの詳細.
 *
 * 業務API「変動金利定期預金通知照会」からのレスポンス
 */
@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
	"noticeFloatingInterestRateTimeDepositId",
	"displayDocumentName",
	"displayProductName",
	"applicantName",
	"accountNumber",
	"depositDate",
	"maturityDate",
	"depositNumber",
	"depositAmount",
	"taxClass",
	"simpleOrCompoundInterest",
	"periodDays",
	"interestRate",
	"interimInterestPaymentDate",
	"interimPaymentInterestRate",
	"interimPaymentInterest",
	"nationalTax",
	"localTax",
	"deductionInterimPaymentInterest",
	"newInterestRateStartDate",
	"newInterestRate",
	"transferDesignationAccount",
	"standardBenchmarkInterestRate",
	"additionalInterestRate",
	"applyNewInterestRate",
	"taxableInterest",
	"taxableDays",
	"issuedDate",
	"branchName",
	"branchPhoneNumber"
})

public class NoticeFloatingRateDto {

// ! 変動金利定期預金のお知らせ識別子
 @JsonProperty("noticeFloatingInterestRateTimeDepositId")
 private String noticeFloatingInterestRateTimeDepositId;
//! 口座番号
 @JsonProperty("accountNumber")
 private String accountNumber;
//! 通知または書類名
 @JsonProperty("displayDocumentName")
 private String displayDocumentName;
//! 商品名（表示用）
 @JsonProperty("displayProductName")
 private String displayProductName;
// ! 名義人
 @JsonProperty("applicantName")
 private String applicantName;
// ! 預入日
 @JsonProperty("depositDate")
 private String depositDate;
// ! 満期日
 @JsonProperty("maturityDate")
 private String maturityDate;
// ! お預かり番号
 @JsonProperty("depositNumber")
 private String depositNumber;
// ! お預かり金額
 @JsonProperty("depositAmount")
 private Long depositAmount;
// ! 税区分
 @JsonProperty("taxClass")
 private String taxClass;
// ! 単利/複利
 @JsonProperty("simpleOrCompoundInterest")
 private String simpleOrCompoundInterest;
// ! 日数
 @JsonProperty("periodDays")
 private Long periodDays;  
// ! 利率
 @JsonProperty("interestRate")
 private String interestRate;
// ! 中間利払日
 @JsonProperty("interimInterestPaymentDate")
 private String interimInterestPaymentDate;
// ! 中間利払利率
 @JsonProperty("interimPaymentInterestRate")
 private String interimPaymentInterestRate;
// ! 中間利払利息
 @JsonProperty("interimPaymentInterest")
 private Long interimPaymentInterest;   
// ! 国税
 @JsonProperty("nationalTax")
 private Long nationalTax;
// ! 地方税
 @JsonProperty("localTax")
 private Long localTax;
// ! 税引後中間利払利息
 @JsonProperty("deductionInterimPaymentInterest")
 private Long deductionInterimPaymentInterest;
// ! 新利率適用日
 @JsonProperty("newInterestRateStartDate")
 private String newInterestRateStartDate;
// ! 新利率
 @JsonProperty("newInterestRate")
 private String newInterestRate;
// ! 振替指定口座
 @JsonProperty("transferDesignationAccount")
 private String transferDesignationAccount;
// ! 基準指標金利
 @JsonProperty("standardBenchmarkInterestRate")
 private String standardBenchmarkInterestRate;
// ! 上乗せ金利
 @JsonProperty("additionalInterestRate")
 private String additionalInterestRate;
// ! 適用新利率
 @JsonProperty("applyNewInterestRate")
 private String applyNewInterestRate;
// ! 課税対象利息
 @JsonProperty("taxableInterest")
 private Long taxableInterest;
// ! 課税対象日数
 @JsonProperty("taxableDays")
 private Long taxableDays;
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
  * 変動金利定期預金のお知らせ識別子を取得する.
  * 
  * 変動金利定期預金のお知らせ識別子を呼び出し元に返却する
  * @return 変動金利定期預金のお知らせ識別子
  */
 public String getNoticeFloatingInterestRateTimeDepositId() {
 	return noticeFloatingInterestRateTimeDepositId;
 }
 /**
  * 変動金利定期預金のお知らせ識別子を設定する.
  * 
  * 受け取った変動金利定期預金のお知らせ識別子を設定する
  * @param noticeFloatingInterestRateTimeDepositId 変動金利定期預金のお知らせ識別子
  */
 public void setNoticeFloatingInterestRateTimeDepositId(String noticeFloatingInterestRateTimeDepositId) {
 	this.noticeFloatingInterestRateTimeDepositId = noticeFloatingInterestRateTimeDepositId;
 }
 
 /**
  * 口座番号を取得する.
  * 
  * 口座番号を呼び出し元に返却する
  * @return 口座番号
  */
 public String getAccountNumber() {
	 return accountNumber;
 }
 /**
  * 口座番号を設定する.
  * 
  * 受け取った口座番号を設定する
  * @param accountNumber 口座番号
  */
 public void setAccountNumber(String accountNumber) {
	 this.accountNumber = accountNumber;
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
  * 名義人を設定する
  * @param applicantName 名義人
  */
 public void setApplicantName(String applicantName) {
	 this.applicantName = applicantName;
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
  * 預入日を設定する
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
  * お預かり番号を取得する.
  * 
  * お預かり番号を呼び出し元に返却する
  * @return お預かり番号
  */
 public String getDepositNumber() {
	 return depositNumber;
 }
 /**
  * お預かり番号を設定する.
  * 
  * 受け取ったお預かり番号を設定する
  * @param depositNumber お預かり番号
  */ 
 public void setDepositNumber(String depositNumber) {
	 this.depositNumber = depositNumber;
 }

 /**
  * お預かり金額を取得する.
  * 
  * お預かり金額を呼び出し元に返却する
  * @return お預かり金額
  */
 public Long getDepositAmount() {
	 return depositAmount;
 }
 /**
  * お預かり金額を設定する.
  * 
  * 受け取ったお預かり金額を設定する
  * @param depositAmount お預かり金額
  */ 
 public void setDepositAmount(Long depositAmount) {
	 this.depositAmount = depositAmount;
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
  * 税区分を設定する
  * @param taxClass 税区分
  */
 public void setTaxClass(String taxClass) {
	 this.taxClass = taxClass;
 }

 /**
  * 単利/複利を取得する.
  * 
  * 単利/複利を呼び出し元に返却する
  * @return 単利/複利
  */
 public String getSimpleOrCompoundInterest() {
	 return simpleOrCompoundInterest;
 }
 /**
  * 単利/複利を設定する.
  * 
  * 受け取った単利/複利を設定する
  * @param simpleOrCompoundInterest 単利/複利
  */
 public void setSimpleOrCompoundInterest(String simpleOrCompoundInterest) {
	 this.simpleOrCompoundInterest = simpleOrCompoundInterest;
 }

 /**
  * 日数を取得する.
  * 
  * 日数を呼び出し元に返却する
  * @return 日数
  */
 public Long getPeriodDays() {
	 return periodDays;
 }
 /**
  * 日数を設定する.
  * 
  * 受け取った日数を設定する
  * @param periodDays 日数
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
  * 利率を設定する
  * @param interestRate 利率
  */
 public void setInterestRate(String interestRate) {
	 this.interestRate = interestRate;
 }
 
 /**
  * 中間利払日を取得する.
  * 
  * 中間利払日を呼び出し元に返却する
  * @return 中間利払日
  */
 public String getInterimInterestPaymentDate() {
	 return interimInterestPaymentDate;
 }
 /**
  * 中間利払日を設定する.
  * 
  * 中間利払日を設定する
  * @param interimInterestPaymentDate 中間利払日
  */
 public void setInterimInterestPaymentDate(String interimInterestPaymentDate) {
	 this.interimInterestPaymentDate = interimInterestPaymentDate;
 }

 /**
  * 中間利払利率を取得する.
  * 
  * 中間利払利率を呼び出し元に返却する
  * @return 中間利払利率
  */
 public String getInterimPaymentInterestRate() {
	 return interimPaymentInterestRate;
 }
 /**
  * 中間利払利率を設定する.
  * 
  * 中間利払利率を設定する
  * @param interimPaymentInterestRate 中間利払利率
  */
 public void setInterimPaymentInterestRate(String interimPaymentInterestRate) {
	 this.interimPaymentInterestRate = interimPaymentInterestRate;
 }
 
 /**
  * 中間利払利息を取得する.
  * 
  * 中間利払利息を呼び出し元に返却する
  * @return 中間利払利息
  */
 public Long getInterimPaymentInterest() {
	 return interimPaymentInterest;
 }
 /**
  * 中間利払利息を設定する.
  * 
  * 中間利払利息を設定する
  * @param middlePaymentInterest 中間利払利息
  */
 public void setInterimPaymentInterest(Long interimPaymentInterest) {
	 this.interimPaymentInterest = interimPaymentInterest;
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
  * 国税を設定する
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
  * 地方税を設定する
  * @param localTax 地方税
  */
 public void setLocalTax(Long localTax) {
	 this.localTax = localTax;
 }

 /**
  * 税引後中間利払利息を取得する.
  * 
  * 税引後中間利払利息を呼び出し元に返却する
  * @return 税引後中間利払利息
  */
 public Long getDeductionInterimPaymentInterest() {
	 return deductionInterimPaymentInterest;
 }
 /**
  * 税引後中間利払利息を設定する.
  * 
  * 税引後中間利払利息を設定する
  * @param deductionInterimPaymentInterest 税引後中間利払利息
  */
 public void setDeductionInterimPaymentInterest(Long deductionInterimPaymentInterest) {
	 this.deductionInterimPaymentInterest = deductionInterimPaymentInterest;
 }
 
 /**
  * 新利率適用日を取得する.
  * 
  * 新利率適用日を呼び出し元に返却する
  * @return 新利率適用日
  */
 public String getNewInterestRateStartDate() {
	 return newInterestRateStartDate;
 }
 /**
  * 新利率適用日を設定する.
  * 
  * 新利率適用日を設定する
  * @param newInterestRateStartDate 新利率適用日
  */
 public void setNewInterestRateStartDate(String newInterestRateStartDate) {
	 this.newInterestRateStartDate = newInterestRateStartDate;
 }
 
 /**
  * 新利率を取得する.
  * 
  * 新利率を呼び出し元に返却する
  * @return 新利率
  */
 public String getNewInterestRate() {
	 return newInterestRate;
 }
 /**
  * 新利率を設定する.
  * 
  * 新利率を設定する
  * @param newInterestRate 新利率
  */
 public void setNewInterestRate(String newInterestRate) {
	 this.newInterestRate = newInterestRate;
 }
 
 /**
  * 振替指定口座を取得する.
  * 
  * 振替指定口座を呼び出し元に返却する
  * @return 振替指定口座
  */
 public String getTransferDesignationAccount() {
	 return transferDesignationAccount;
 }
 /**
  * 振替指定口座を設定する.
  * 
  * 振替指定口座を設定する
  * @param transferDesignationAccount 振替指定口座
  */
 public void setTransferDesignationAccount(String transferDesignationAccount) {
	 this.transferDesignationAccount = transferDesignationAccount;
 }
 
 /**
  * 基準指標金利を取得する.
  * 
  * 基準指標金利を呼び出し元に返却する
  * @return 基準指標金利
  */
 public String getStandardBenchmarkInterestRate() {
	 return standardBenchmarkInterestRate;
 }
 /**
  * 基準指標金利を設定する.
  * 
  * 基準指標金利を設定する
  * @param standardBenchmarkInterestRate 基準指標金利
  */
 public void setStandardBenchmarkInterestRate(String standardBenchmarkInterestRate) {
	 this.standardBenchmarkInterestRate = standardBenchmarkInterestRate;
 }
 
 /**
  * 上乗せ金利を取得する.
  * 
  * 上乗せ金利を呼び出し元に返却する
  * @return 上乗せ金利
  */
 public String getAdditionalInterestRate() {
	 return additionalInterestRate;
 }
 /**
  * 上乗せ金利を設定する.
  * 
  * 上乗せ金利を設定する
  * @param additionalInterestRate 上乗せ金利
  */
 public void setAdditionalInterestRate(String additionalInterestRate) {
	 this.additionalInterestRate = additionalInterestRate;
 }

 /**
  * 適用新利率を取得する.
  * 
  * 適用新利率を呼び出し元に返却する
  * @return 適用新利率
  */
 public String getApplyNewInterestRate() {
	 return applyNewInterestRate;
 }
 /**
  * 適用新利率を設定する.
  * 
  * 適用新利率を設定する
  * @param applyNewInterestRate 適用新利率
  */
 public void setApplyNewInterestRate(String applyNewInterestRate) {
	 this.applyNewInterestRate = applyNewInterestRate;
 }

 /**
  * 課税対象利息を取得する.
  * 
  * 課税対象利息を呼び出し元に返却する
  * @return 課税対象利息
  */
 public Long getTaxableInterest() {
	 return taxableInterest;
 }
 /**
  * 課税対象利息を設定する.
  * 
  * 課税対象利息を設定する
  * @param taxableInterest 課税対象利息
  */
 public void setTaxableInterest(Long taxableInterest) {
	 this.taxableInterest = taxableInterest;
 }
 
 /**
  * 課税対象日数を取得する.
  * 
  * 課税対象日数を呼び出し元に返却する
  * @return 課税対象日数
  */
 public Long getTaxableDays() {
	 return taxableDays;
 }
 /**
  * 課税対象日数を設定する.
  * 
  * 課税対象日数を設定する
  * @param taxableDays 課税対象日数
  */
 public void setTaxableDays(Long taxableDays) {
	 this.taxableDays = taxableDays;
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
  * 作成日を設定する
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
  * 取扱店名を設定する
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
  * 取扱店電話番号を設定する
  * @param branchPhoneNumber 取扱店電話番号
  */
 public void setBranchPhoneNumber(String branchPhoneNumber) {
	 this.branchPhoneNumber = branchPhoneNumber;
 }

}