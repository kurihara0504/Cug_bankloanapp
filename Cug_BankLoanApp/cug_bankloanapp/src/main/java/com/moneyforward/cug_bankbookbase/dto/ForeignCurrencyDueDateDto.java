package com.moneyforward.cug_bankbookbase.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

/**
 * 外貨定期預金期日のご案内の詳細.
 *
 * 業務API「外貨定期預金期日案内照会」からのレスポンス
 */
@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
  "foreignTimeDepositMaturityNotificationsId",
  "displayDocumentName",
  "displayProductName",
  "applicantName",
  "maturityDate",
  "depositNumber",
  "currencyCode",
  "depositAmount",
  "afterMaturityDate",
  "interestRate",
  "period",
  "interest",
  "taxClass",
  "nationalTax",
  "localTax",
  "deductionPaymentInterest",
  "interestHandling",
  "newTimeDepositAmount",
  "issuedDate",
  "branchName",
  "branchPhoneNumber"
})

public class ForeignCurrencyDueDateDto {
	
	// ! 外貨定期預金期日案内識別子
	@JsonProperty("foreignTimeDepositMaturityNotificationsId")
	private String foreignTimeDepositMaturityNotificationsId;
	//! 通知または書類名
	@JsonProperty("displayDocumentName")
	private String displayDocumentName;
	//! 商品名（表示用）
	@JsonProperty("displayProductName")
	private String displayProductName;
	// ! 名義人
	@JsonProperty("applicantName")
	private String applicantName;
	// ! 満期日
	@JsonProperty("maturityDate")
	private String maturityDate;
	// ! お預かり番号
	@JsonProperty("depositNumber")
	private String depositNumber;
	// ! 通貨
	@JsonProperty("currencyCode")
	private String currencyCode;
	// ! お預かり金額
	@JsonProperty("depositAmount")
	private String depositAmount;
	// ! 満期時の取扱い
	@JsonProperty("afterMaturityDate")
	private String afterMaturityDate;  
	// ! 利率
	@JsonProperty("interestRate")
	private String interestRate;
	// ! 期間
	@JsonProperty("period")
	private String period;
	// ! 利息
	@JsonProperty("interest")
	private String interest;   
	// ! 税区分
	@JsonProperty("taxClass")
	private String taxClass;
	// ! 国税
	@JsonProperty("nationalTax")
	private String nationalTax;
	// ! 地方税
	@JsonProperty("localTax")
	private String localTax;
	// ! 差し引き支払利息
	@JsonProperty("deductionPaymentInterest")
	private String deductionPaymentInterest;
	// ! 利息の取扱い
	@JsonProperty("interestHandling")
	private String interestHandling;
	// ! 新規定期金額
	@JsonProperty("newTimeDepositAmount")
	private String newTimeDepositAmount;
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
	 * 外貨定期預金期日案内識別子を取得する.
	 * 
	 * 外貨定期預金期日案内識別子を呼び出し元に返却する
	 * @return 外貨定期預金期日案内識別子
	 */
	public String getForeignTimeDepositMaturityNotificationsId() {
		return foreignTimeDepositMaturityNotificationsId;
	}
	/**
	 * 外貨定期預金期日案内識別子を設定する.
	 * 
	 * 受け取った外貨定期預金期日案内識別子を設定する
	 * @param foreignTimeDepositMaturityNotificationsId 外貨定期預金期日案内識別子
	 */
	public void setForeignTimeDepositMaturityNotificationsId(String foreignTimeDepositMaturityNotificationsId) {
		this.foreignTimeDepositMaturityNotificationsId = foreignTimeDepositMaturityNotificationsId;
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
	 * 通貨を取得する.
	 * 
	 * 通貨を呼び出し元に返却する
	 * @return 通貨
	 */
	public String getCurrencyCode() {
		return currencyCode;
	}
	/**
	 * 通貨を設定する.
	 * 
	 * 受け取った通貨を設定する
	 * @param currencyCode 通貨
	 */ 
	public void setCurrencyCode(String currencyCode) {
		this.currencyCode = currencyCode;
	}
	
	/**
	 * お預かり金額を取得する.
	 * 
	 * お預かり金額を呼び出し元に返却する
	 * @return お預かり金額
	 */
	public String getDepositAmount() {
		return depositAmount;
	}
	/**
	 * お預かり金額を設定する.
	 * 
	 * 受け取ったお預かり金額を設定する
	 * @param depositAmount お預かり金額
	 */ 
	public void setDepositAmount(String depositAmount) {
		this.depositAmount = depositAmount;
	}
	
	/**
	 * 満期時の取扱いを取得する.
	 * 
	 * 満期時の取扱いを呼び出し元に返却する
	 * @return 満期時の取扱い
	 */
	public String getAfterMaturityDate() {
		return afterMaturityDate;
	}
	/**
	 * 満期時の取扱いを設定する.
	 * 
	 * 受け取った満期時の取扱いを設定する
	 * @param afterMaturityDate 満期時の取扱い
	 */
	public void setAfterMaturityDate(String afterMaturityDate) {
		this.afterMaturityDate = afterMaturityDate;
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
	 * 期間を設定する
	 * @param period 期間
	 */
	public void setPeriod(String period) {
		this.period = period;
	}
	
	/**
	 * 利息を取得する.
	 * 
	 * 期間を呼び出し元に返却する
	 * @return 期間
	 */
	public String getInterest() {
		return interest;
	}
	/**
	 * 利息を設定する.
	 * 
	 * 利息を設定する
	 * @param interest 利息
	 */
	public void setInterest(String interest) {
		this.interest = interest;
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
	 * 国税を取得する.
	 * 
	 * 国税を呼び出し元に返却する
	 * @return 国税
	 */
	public String getNationalTax() {
		return nationalTax;
	}
	/**
	 * 国税を設定する.
	 * 
	 * 国税を設定する
	 * @param nationalTax 国税
	 */
	public void setNationalTax(String nationalTax) {
		this.nationalTax = nationalTax;
	}
	
	/**
	 * 地方税を取得する.
	 * 
	 * 地方税を呼び出し元に返却する
	 * @return 地方税
	 */
	public String getLocalTax() {
		return localTax;
	}
	/**
	 * 地方税を設定する.
	 * 
	 * 地方税を設定する
	 * @param localTax 地方税
	 */
	public void setLocalTax(String localTax) {
		this.localTax = localTax;
	}
	
	/**
	 * 差引支払利息を取得する.
	 * 
	 * 差引支払利息を呼び出し元に返却する
	 * @return 差引支払利息
	 */
	public String getDeductionPaymentInterest() {
		return deductionPaymentInterest;
	}
	/**
	 * 差引支払利息を設定する.
	 * 
	 * 差引支払利息を設定する
	 * @param deductionPaymentInterest 差引支払利息
	 */
	public void setDeductionPaymentInterest(String deductionPaymentInterest) {
		this.deductionPaymentInterest = deductionPaymentInterest;
	}
	
	/**
	 * 利息の取扱いを取得する.
	 * 
	 * 利息の取扱いを呼び出し元に返却する
	 * @return 利息の取扱い
	 */
	public String getInterestHandling() {
		return interestHandling;
	}
	/**
	 * 利息の取扱いを設定する.
	 * 
	 * 利息の取扱いを設定する
	 * @param interestHandling 利息の取扱い
	 */
	public void setInterestHandling(String interestHandling) {
		this.interestHandling = interestHandling;
	}
	
	/**
	 * 新定期金額を取得する.
	 * 
	 * 新定期金額を呼び出し元に返却する
	 * @return 新定期金額
	 */
	public String getNewTimeDepositAmount() {
		return newTimeDepositAmount;
	}
	/**
	 * 新定期金額を設定する.
	 * 
	 * 新定期金額を設定する
	 * @param newTimeDepositAmount 新定期金額
	 */
	public void setNewTimeDepositAmount(String newTimeDepositAmount) {
		this.newTimeDepositAmount = newTimeDepositAmount;
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