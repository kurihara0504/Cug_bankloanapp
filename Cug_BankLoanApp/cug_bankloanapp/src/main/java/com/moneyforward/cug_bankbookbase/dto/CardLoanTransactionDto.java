package com.moneyforward.cug_bankbookbase.dto;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

/**
 * カードローン取引内容通知の詳細.
 *
 * 業務API「カードローン取引内容通知照会」からのレスポンス
 */
@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
  "noticeCardLoanTransactionId",
  "displayDocumentName",
  "displayProductName",
  "applicantName",
  "issuedDate",
  "productName",
  "accountNumber",
  "repaymentAccountNumber",
  "loanLimitAmount",
  "loanInterestRate",
  "branchName",
  "branchPhoneNumber",
  "details"
 
  
  
})
public class CardLoanTransactionDto {

  // ! カードローン取引内容通知識別子
  @JsonProperty("noticeCardLoanTransactionId")
  private String noticeCardLoanTransactionId;
  // ! 通知名または書類名
  @JsonProperty("displayDocumentName")
  private String displayDocumentName;
  // ! 商品名
  @JsonProperty("displayProductName")
  private String displayProductName;
  // ! 名義人
  @JsonProperty("applicantName")
  private String applicantName;
  // ! 作成日
  @JsonProperty("issuedDate")
  private String issuedDate;
  // ! 商品名
  @JsonProperty("productName")
  private String productName;
  // ! 口座番号
  @JsonProperty("accountNumber")
  private String accountNumber;
  // ! 返済用口座番号
  @JsonProperty("repaymentAccountNumber")
  private String repaymentAccountNumber;
  // ! 貸越極度額
  @JsonProperty("loanLimitAmount")
  private Long loanLimitAmount;
  // ! 融資利率
  @JsonProperty("loanInterestRate")
  private String loanInterestRate;
  // ! 取扱店名
  @JsonProperty("branchName")
  private String branchName;
  // ! 取扱店電話番号
  @JsonProperty("branchPhoneNumber")
  private String branchPhoneNumber;
  // ! 明細一覧
  @JsonProperty("details")
  private List<CardLoanTransactionDetailsDto> list;
 
  

  /**
   * カードローン取引内容通知識別子を取得する.
   *
   * カードローン取引内容通知識別子を呼び出し元に返却する
   * @return カードローン取引内容通知識別子
   */
  public String getNoticeCardLoanTransactionId() {
    return noticeCardLoanTransactionId;
  }
  /**
   * カードローン取引内容通知識別子を設定する.
   *
   * 受け取ったカードローン取引内容通知識別子を設定する
   * @param noticeCardLoanTransactionId カードローン取引内容通知識別子
   */
  public void setNoticeCardLoanTransactionId(String noticeCardLoanTransactionId) {
    this.noticeCardLoanTransactionId = noticeCardLoanTransactionId;
  }
  /**
   * 通知名または書類名を取得する.
   *
   * 通知名または書類名を呼び出し元に返却する
   * @return 通知名または書類名
   */
  public String getDisplayDocumentName() {
    return displayDocumentName;
  }
  /**
   * 通知名または書類名を設定する.
   *
   * 受け取った通知名または書類名を設定する
   * @param displayDocumentName 通知名または書類名
   */
  public void setDisplayDocumentName(String displayDocumentName) {
    this.displayDocumentName = displayDocumentName;
  }
  /**
   * 商品名を取得する.
   *
   * 商品名を呼び出し元に返却する
   * @return 商品名
   */
  public String getDisplayProductName() {
    return displayProductName;
  }
  /**
   * 商品名を設定する.
   *
   * 受け取った商品名を設定する
   * @param displayProductName 商品名
   */
  public void setDisplayProductName(String displayProductName) {
    this.displayProductName = displayProductName;
  }
  
  
  
  /**
   * 口座番号を取得する.
   * 
   * 口座番号を呼び出し元に返却する
   * @return the accountNumber
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
   * 受け取った名義人を設定する
   * @param applicantName 名義人
   */
  public void setApplicantName(String applicantName) {
    this.applicantName = applicantName;
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
   * 返済用口座番号を取得する.
   *
   *返済用口座番号を呼び出し元に返却する
   * @return 返済用口座番号
   */
  public String getRepaymentAccountNumber() {
    return repaymentAccountNumber;
  }
  /**
   * 返済用口座番号を設定する.
   *
   * 受け取った返済用口座番号を設定する
   * @param repaymentAccountNumber 返済用口座番号
   */
  public void setRepaymentAccountNumber(String repaymentAccountNumber) {
    this.repaymentAccountNumber = repaymentAccountNumber;
  }
  
  /**
   * 貸越極度額を取得する.
   *
   * 貸越極度額を呼び出し元に返却する
   * @return 貸越極度額
   */
  public Long getLoanLimitAmount() {
    return loanLimitAmount;
  }
  /**
   * 貸越極度額を設定する.
   *
   * 受け取った貸越極度額を設定する
   * @param loanLimitAmount 貸越極度額
   */
  public void setLoanLimitAmount(Long loanLimitAmount) {
    this.loanLimitAmount = loanLimitAmount;
  }
  
  /**
   * 融資利率を取得する.
   *
   * 融資利率を呼び出し元に返却する
   * @return 融資利率
   */
  public String getLoanInterestRate() {
    return loanInterestRate;
  }
  /**
   * 融資利率を設定する.
   *
   * 受け取った融資利率を設定する
   * @param loanInterestRate 融資利率
   */
  public void setLoanInterestRate(String loanInterestRate) {
    this.loanInterestRate = loanInterestRate;
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
  
  /**
   * 明細一覧を取得する.
   *
   * 明細一覧を呼び出し元に返却する
   * @return 明細一覧
   */
	public List<CardLoanTransactionDetailsDto> getList() {
		return list;
	}  
  /**
   * 明細一覧を設定する.
   *
   * 受け取った明細一覧を設定する
   * @param list 明細一覧
   */
	public void setList(List<CardLoanTransactionDetailsDto> list) {
		this.list = list;
	}
  
  

}