package com.moneyforward.cug_bankbookbase.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

/**
 * 契約終了通知書の詳細.
 *
 * 業務API「契約終了通知書照会」からのレスポンス
 */
@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
  "endLoanContractId",
  "displayDocumentName",
  "displayProductName",
  "applicantName",
  "loanSubject",
  "contractDate",
  "branchName",
  "loanHandlingNumber",
  "contractSubstance",
  "contractAmount",
  "contractEndDate",
  "issuedDate",
  "branchPhoneNumber"
})
public class ContractTerminationDto {

  // ! 契約終了通知書識別子
  @JsonProperty("endLoanContractId")
  private String endLoanContractId;
  //! 通知または書類名
  @JsonProperty("displayDocumentName")
  private String displayDocumentName; 
  //! 商品名（表示用）
  @JsonProperty("displayProductName")
  private String displayProductName;
  // ! 名義人
  @JsonProperty("applicantName")
  private String applicantName;
  // ! ローン科目
  @JsonProperty("loanSubject")
  private String loanSubject;
  // ! 契約日
  @JsonProperty("contractDate")
  private String contractDate;
  // ! 取引店名
  @JsonProperty("branchName")
  private String branchName;
  // ! 取扱番号(口座番号)
  @JsonProperty("loanHandlingNumber")
  private String loanHandlingNumber;
  // !　契約内容
  @JsonProperty("contractSubstance")
  private String contractSubstance;
  // ! 契約金額
  @JsonProperty("contractAmount")
  private Long contractAmount;
  // ! 契約終了日
  @JsonProperty("contractEndDate")
  private String contractEndDate;
  // ! 作成日
  @JsonProperty("issuedDate")
  private String issuedDate;
  // ! 取扱店電話番号
  @JsonProperty("branchPhoneNumber")
  private String branchPhoneNumber;
  

  /**
   * 契約終了通知書識別子を取得する.
   *
   * 契約終了通知書識別子を呼び出し元に返却する
   * @return 契約終了通知書識別子
   */
  public String getEndLoanContractId() {
    return endLoanContractId;
  }
  /**
   * 契約終了通知書識別子を設定する.
   *
   * 受け取った契約終了通知書識別子を設定する
   * @param endLoanContractId 契約終了通知書識別子
   */
  public void setEndLoanContractId(String endLoanContractId) {
    this.endLoanContractId = endLoanContractId;
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
   *  名義人を取得する.
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
   * ローン科目を取得する.
   *
   * ローン科目を呼び出し元に返却する
   * @return ローン科目
   */
  public String getLoanSubject() {
  	return loanSubject;
  }
  /**
   * ローン科目を設定する.
   *
   * 受け取ったローン科目を設定する
   * @param loanSubject ローン科目
   */
  public void setLoanSubject(String loanSubject) {
  	this.loanSubject = loanSubject;
  }
  
  /**
   * 契約日を取得する.
   *
   * 契約日を呼び出し元に返却する
   * @return 契約日
   */
  public String getContractDate() {
    return contractDate;
  }
  /**
   * 契約日を設定する.
   *
   * 受け取った契約日を設定する
   * @param contractDate 契約日
   */
  public void setContractDate(String contractDate) {
    this.contractDate = contractDate;
  }


  /**
   * 取引店名を取得する.
   *
   * 取引店名を呼び出し元に返却する
   * @return 取引店名
   */
  public String getBranchName() {
    return branchName;
  }
  /**
   * 取引店名を設定する.
   *
   * 受け取った取引店名を設定する
   * @param branchName 取引店名
   */
  public void setBranchName(String branchName) {
    this.branchName = branchName;
  }


  /**
   * 取扱番号(口座番号)を取得する.
   *
   * 取扱番号(口座番号)を呼び出し元に返却する
   * @return 取扱番号(口座番号)
   */
  public String getLoanHandlingNumber() {
    return loanHandlingNumber;
  }
  /**
   * 取扱番号(口座番号)を設定する.
   *
   * 受け取った取扱番号(口座番号)を設定する
   * @param loanHandlingNumber 取扱番号(口座番号)
   */
  public void setLoanHandlingNumber(String loanHandlingNumber) {
    this.loanHandlingNumber = loanHandlingNumber;
  }


  /**
   * 契約内容を取得する.
   *
   * 契約内容を呼び出し元に返却する
   * @return 契約内容
   */
  public String getContractSubstance() {
    return contractSubstance;
  }
  /**
   * 契約内容を設定する.
   *
   * 受け取った契約内容を設定する
   * @param contractSubstance 契約内容
   */
  public void setContractSubstance(String contractSubstance) {
    this.contractSubstance= contractSubstance;
  }


  /**
   * 契約金額を取得する.
   *
   * 契約金額を呼び出し元に返却する
   * @return 契約金額
   */
  public Long getContractAmount() {
    return contractAmount;
  }
  /**
   * 契約金額を設定する.
   *
   * 受け取った契約金額を設定する
   * @param contractAmount 契約金額
   */
  public void setContractAmount(Long contractAmount) {
    this.contractAmount = contractAmount;
  }


  /**
   * 契約終了日を取得する.
   *
   * 契約終了日を呼び出し元に返却する
   * @return 契約終了日
   */
  public String getContractEndDate() {
    return contractEndDate;
  }
  /**
   * 契約終了日を設定する.
   *
   * 受け取った契約終了日を設定する
   * @param contractEndDate 契約終了日
   */
  public void setContractEndDate(String contractEndDate) {
    this.contractEndDate = contractEndDate;
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
   * @param issueDate 作成日
   */
  public void setIssuedDate(String issuedDate) {
    this.issuedDate = issuedDate;
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