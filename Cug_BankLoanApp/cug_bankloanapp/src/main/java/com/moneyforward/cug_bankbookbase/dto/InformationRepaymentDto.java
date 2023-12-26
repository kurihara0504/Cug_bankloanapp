package com.moneyforward.cug_bankbookbase.dto;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

/**
 * ご返済のご案内の詳細.
 *
 * 業務API「ご返済のご案内」からのレスポンス
 */
@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
  "repaymentNotificationsId",
  "displayDocumentName",
  "displayProductName",
  "applicantName",
  "borrowingDate",
  "borrowingAmount",
  "monthlyBorrowingAmount",
  "increasedBorrowingAmount",
  "interestRate",
  "subject",
  "handlingNumber",
  "notificationsClass",
  "maturityDate",
  "repaymentCycle",
  "repaymentDate",
  "increasedRepaymentMonth1",
  "increasedRepaymentMonth2",
  "InterestRateEndDate",
  "newInterestRate",
  "transferBranchNumber",
  "transferSubject",
  "transferAccountNumber",
  "nextNotifications",
  "loanName",
  "remarks",
  "guidance1",
  "guidance2",
  "explanation1",
  "explanation2",
  "explanation3",
  "explanation4",
  "explanation5",
  "issuedDate",
  "branchName",
  "branchPhoneNumber",
  "details"
})
public class InformationRepaymentDto {
  /**返済案内識別子*/
  @JsonProperty("repaymentNotificationsId")
  private String repaymentNotificationsId;
  
  /**通知または書類名*/
  @JsonProperty("displayDocumentName")
  private String displayDocumentName;

  /**商品名*/
  @JsonProperty("displayProductName")
  private String displayProductName;

  /**名義人*/
  @JsonProperty("applicantName")
  private String applicantName;

  /**借入日*/
  @JsonProperty("borrowingDate")
  private String borrowingDate;

  /**借入額*/
  @JsonProperty("borrowingAmount")
  private Long borrowingAmount;

  /**内毎回分借入額*/
  @JsonProperty("monthlyBorrowingAmount")
  private Long monthlyBorrowingAmount;

  /**内増額分借入額*/
  @JsonProperty("increasedBorrowingAmount")
  private Long increasedBorrowingAmount;

  /**適用利率*/
  @JsonProperty("interestRate")
  private String interestRate;

  /**科目*/
  @JsonProperty("subject")
  private String subject;

  /**取扱番号*/
  @JsonProperty("handlingNumber")
  private  String handlingNumber;

  /**案内区分*/
  @JsonProperty("notificationsClass")
  private String notificationsClass;

  /**最終期限*/
  @JsonProperty("maturityDate")
  private String maturityDate;

  /**返済日（周期）*/
  @JsonProperty("repaymentCycle")
  private Long repaymentCycle;

  /**返済日（返済日）*/
  @JsonProperty("repaymentDate")
  private Long repaymentDate;

  /**増額分支払月1*/
  @JsonProperty("increasedRepaymentMonth1")
  private Long increasedRepaymentMonth1;

  /**増額分支払月2*/
  @JsonProperty("increasedRepaymentMonth2")
  private Long increasedRepaymentMonth2;

  /**適用利率終了日*/
  @JsonProperty("InterestRateEndDate")
  private String InterestRateEndDate;

  /**新適用利率*/
  @JsonProperty("newInterestRate")
  private String newInterestRate;

  /**自振店番号*/
  @JsonProperty("transferBranchNumber")
  private String transferBranchNumber;

  /**自振科目名*/
  @JsonProperty("transferSubject")
  private String transferSubject;

  /**自振口座番号*/
  @JsonProperty("transferAccountNumber")
  private String transferAccountNumber;

  /**次回案内予定*/
  @JsonProperty("nextNotifications")
  private String nextNotifications;

  /**制度融資名*/
  @JsonProperty("loanName")
  private String loanName;

  /**備考*/
  @JsonProperty("remarks")
  private String remarks;

  /**ガイダンス1*/
  @JsonProperty("guidance1")
  private String guidance1;

  /**ガイダンス2*/
  @JsonProperty("guidance2")
  private String guidance2;

  /**説明文言1*/
  @JsonProperty("explanation1")
  private String explanation1;

  /**説明文言2*/
  @JsonProperty("explanation2")
  private String explanation2;

  /**説明文言3*/
  @JsonProperty("explanation3")
  private String explanation3;

  /**説明文言4*/
  @JsonProperty("explanation4")
  private String explanation4;

  /**説明文言5*/
  @JsonProperty("explanation5")
  private String explanation5;

  /**作成基準日*/
  @JsonProperty("issuedDate")
  private String issuedDate;

  /**取扱店*/
  @JsonProperty("branchName")
  private String branchName;

  /**取扱店電話番号*/
  @JsonProperty("branchPhoneNumber")
  private String branchPhoneNumber;
  
  /**明細一覧*/
  @JsonProperty("details")
  private List<InformationRepaymentDetailDto> details;

  /**
   * 返済案内識別子を取得する.
   * 
   * 返済案内識別子を呼び出し元に返却する
   * @return 返済案内識別子
   */
  public String getRepaymentNotificationsId() {
    return repaymentNotificationsId;
  }

  /**
   * 返済案内識別子を設定する.
   * 
   * 受け取った返済案内識別子を設定する
   * @param repaymentNotificationsId 返済案内識別子
   */
  public void setRepaymentNotificationsId(String repaymentNotificationsId) {
    this.repaymentNotificationsId = repaymentNotificationsId;
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
   * 受け取ったを設定する
   * @param displayDocumentName 通知または書類名
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
   * 借入日を取得する.
   * 
   * 借入日を呼び出し元に返却する
   * @return 借入日
   */
  public String getBorrowingDate() {
    return borrowingDate;
  }

  /**
   * 借入日を設定する.
   * 
   * 受け取った借入日を設定する
   * @param borrowingDate 借入日
   */
  public void setBorrowingDate(String borrowingDate) {
    this.borrowingDate = borrowingDate;
  }

  /**
   * 借入額を取得する.
   * 
   * 借入額を呼び出し元に返却する
   * @return 借入額
   */
  public Long getBorrowingAmount() {
    return borrowingAmount;
  }

  /**
   * 借入額を設定する.
   * 
   * 受け取った借入額を設定する
   * @param borrowingAmount 借入額
   */
  public void setBorrowingAmount(Long borrowingAmount) {
    this.borrowingAmount = borrowingAmount;
  }

  /**
   * 内毎回分借入額を取得する.
   * 
   * 内毎回分借入額を呼び出し元に返却する
   * @return 内毎回分借入額
   */
  public Long getMonthlyBorrowingAmount() {
    return monthlyBorrowingAmount;
  }

  /**
   * 内毎回分借入額を設定する.
   * 
   * 受け取った内毎回分借入額を設定する
   * @param monthlyBorrowingAmount 内毎回分借入額
   */
  public void setMonthlyBorrowingAmount(Long monthlyBorrowingAmount) {
    this.monthlyBorrowingAmount = monthlyBorrowingAmount;
  }

  /**
   * 内増額分借入額を取得する.
   * 
   * 内増額分借入額を呼び出し元に返却する
   * @return 内増額分借入額
   */
  public Long getIncreasedBorrowingAmount() {
    return increasedBorrowingAmount;
  }

  /**
   * 内増額分借入額を設定する.
   * 
   * 受け取った内増額分借入額を設定する
   * @param increasedBorrowingAmount 内増額分借入額
   */
  public void setIncreasedBorrowingAmount(Long increasedBorrowingAmount) {
    this.increasedBorrowingAmount = increasedBorrowingAmount;
  }

  /**
   * 適用利率を取得する.
   * 
   * 適用利率を呼び出し元に返却する
   * @return 適用利率
   */
  public String getInterestRate() {
    return interestRate;
  }

  /**
   * 適用利率を設定する.
   * 
   * 受け取った適用利率を設定する
   * @param interestRate 適用利率
   */
  public void setInterestRate(String interestRate) {
    this.interestRate = interestRate;
  }

  /**
   * 科目を取得する.
   * 
   * 科目を呼び出し元に返却する
   * @return 科目
   */
  public String getSubject() {
    return subject;
  }

  /**
   * 科目を設定する.
   * 
   * 受け取った科目を設定する
   * @param subject 科目
   */
  public void setSubject(String subject) {
    this.subject = subject;
  }

  /**
   * 取扱番号を取得する.
   * 
   * 取扱番号を呼び出し元に返却する
   * @return 取扱番号
   */
  public String getHandlingNumber() {
    return handlingNumber;
  }

  /**
   * 取扱番号を設定する.
   * 
   * 受け取った取扱番号を設定する
   * @param handlingNumber 取扱番号
   */
  public void setHandlingNumber(String handlingNumber) {
    this.handlingNumber = handlingNumber;
  }

  /**
   * 案内区分を取得する.
   * 
   * 案内区分を呼び出し元に返却する
   * @return 案内区分
   */
  public String getNotificationsClass() {
    return notificationsClass;
  }

  /**
   * 案内区分を設定する.
   * 
   * 受け取った案内区分を設定する
   * @param notificationsClass 案内区分
   */
  public void setNotificationsClass(String notificationsClass) {
    this.notificationsClass = notificationsClass;
  }

  /**
   * 最終期限を取得する.
   * 
   * 最終期限を呼び出し元に返却する
   * @return 最終期限
   */
  public String getMaturityDate() {
    return maturityDate;
  }

  /**
   * 最終期限を設定する.
   * 
   * 受け取った最終期限を設定する
   * @param maturityDate 最終期限
   */
  public void setMaturityDate(String maturityDate) {
    this.maturityDate = maturityDate;
  }

  /**
   * 返済日（周期）を取得する.
   * 
   * 返済日（周期）を呼び出し元に返却する
   * @return 返済日（周期）
   */
  public Long getRepaymentCycle() {
    return repaymentCycle;
  }

  /**
   * 返済日（周期）を設定する.
   * 
   * 受け取った返済日（周期）を設定する
   * @param repaymentCycle 返済日（周期）
   */
  public void setRepaymentCycle(Long repaymentCycle) {
    this.repaymentCycle = repaymentCycle;
  }

  /**
   * 返済日（返済日）を取得する.
   * 
   * 返済日（返済日）を呼び出し元に返却する
   * @return 返済日（返済日）
   */
  public Long getRepaymentDate() {
    return repaymentDate;
  }

  /**
   * 返済日（返済日）を設定する.
   * 
   * 受け取った返済日（返済日）を設定する
   * @param repaymentDate 返済日（返済日）
   */
  public void setRepaymentDate(Long repaymentDate) {
    this.repaymentDate = repaymentDate;
  }

  /**
   * 増額分支払月1を取得する.
   * 
   * 増額分支払月1を呼び出し元に返却する
   * @return 増額分支払月1
   */
  public Long getIncreasedRepaymentMonth1() {
    return increasedRepaymentMonth1;
  }

  /**
   * 増額分支払月1を設定する.
   * 
   * 受け取った増額分支払月1を設定する
   * @param increasedRepaymentMonth1 増額分支払月1
   */
  public void setIncreasedRepaymentMonth1(Long increasedRepaymentMonth1) {
    this.increasedRepaymentMonth1 = increasedRepaymentMonth1;
  }

  /**
   * 増額分支払月2を取得する.
   * 
   * 増額分支払月2を呼び出し元に返却する
   * @return 増額分支払月2
   */
  public Long getIncreasedRepaymentMonth2() {
    return increasedRepaymentMonth2;
  }

  /**
   * 増額分支払月2を設定する.
   * 
   * 受け取った増額分支払月2を設定する
   * @param increasedRepaymentMonth2 増額分支払月2
   */
  public void setIncreasedRepaymentMonth2(Long increasedRepaymentMonth2) {
    this.increasedRepaymentMonth2 = increasedRepaymentMonth2;
  }

  /**
   * 適用利率終了日を取得する.
   * 
   * 適用利率終了日を呼び出し元に返却する
   * @return 適用利率終了日
   */
  public String getInterestRateEndDate() {
    return InterestRateEndDate;
  }

  /**
   * 適用利率終了日を設定する.
   * 
   * 受け取った適用利率終了日を設定する
   * @param interestRateEndDate 適用利率終了日
   */
  public void setInterestRateEndDate(String interestRateEndDate) {
    InterestRateEndDate = interestRateEndDate;
  }

  /**
   * 新適用利率を取得する.
   * 
   * 新適用利率を呼び出し元に返却する
   * @return 新適用利率
   */
  public String getNewInterestRate() {
    return newInterestRate;
  }

  /**
   * 新適用利率を設定する.
   * 
   * 受け取った新適用利率を設定する
   * @param newInterestRate 新適用利率
   */
  public void setNewInterestRate(String newInterestRate) {
    this.newInterestRate = newInterestRate;
  }

  /**
   * 自振店番号を取得する.
   * 
   * 自振店番号を呼び出し元に返却する
   * @return 自振店番号
   */
  public String getTransferBranchNumber() {
    return transferBranchNumber;
  }

  /**
   * 自振店番号を設定する.
   * 
   * 受け取った自振店番号を設定する
   * @param transferBranchNumber 自振店番号
   */
  public void setTransferBranchNumber(String transferBranchNumber) {
    this.transferBranchNumber = transferBranchNumber;
  }

  /**
   * 自振科目名を取得する.
   * 
   * 自振科目名を呼び出し元に返却する
   * @return 自振科目名
   */
  public String getTransferSubject() {
    return transferSubject;
  }

  /**
   * 自振科目名を設定する.
   * 
   * 受け取った自振科目名を設定する
   * @param transferSubject 自振科目名
   */
  public void setTransferSubject(String transferSubject) {
    this.transferSubject = transferSubject;
  }

  /**
   * 自振口座番号を取得する.
   * 
   * 自振口座番号を呼び出し元に返却する
   * @return 自振口座番号
   */
  public String getTransferAccountNumber() {
    return transferAccountNumber;
  }

  /**
   * 自振口座番号を設定する.
   * 
   * 受け取った自振口座番号を設定する
   * @param transferAccountNumber 自振口座番号
   */
  public void setTransferAccountNumber(String transferAccountNumber) {
    this.transferAccountNumber = transferAccountNumber;
  }

  /**
   * 次回案内予定を取得する.
   * 
   * 次回案内予定を呼び出し元に返却する
   * @return 次回案内予定
   */
  public String getNextNotifications() {
    return nextNotifications;
  }

  /**
   * 次回案内予定を設定する.
   * 
   * 受け取った次回案内予定を設定する
   * @param nextNotifications 次回案内予定
   */
  public void setNextNotifications(String nextNotifications) {
    this.nextNotifications = nextNotifications;
  }

  /**
   * 制度融資名を取得する.
   * 
   * 制度融資名を呼び出し元に返却する
   * @return 制度融資名
   */
  public String getLoanName() {
    return loanName;
  }

  /**
   * 制度融資名を設定する.
   * 
   * 受け取った制度融資名を設定する
   * @param loanName 制度融資名
   */
  public void setLoanName(String loanName) {
    this.loanName = loanName;
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
   * ガイダンス1を取得する.
   * 
   * ガイダンス1を呼び出し元に返却する
   * @return ガイダンス1
   */
  public String getGuidance1() {
    return guidance1;
  }

  /**
   * ガイダンス1を設定する.
   * 
   * 受け取ったガイダンス1を設定する
   * @param guidance1 ガイダンス1
   */
  public void setGuidance1(String guidance1) {
    this.guidance1 = guidance1;
  }

  /**
   * ガイダンス2を取得する.
   * 
   * ガイダンス2を呼び出し元に返却する
   * @return ガイダンス2
   */
  public String getGuidance2() {
    return guidance2;
  }

  /**
   * ガイダンス2を設定する.
   * 
   * 受け取ったガイダンス2を設定する
   * @param guidance2 ガイダンス2
   */
  public void setGuidance2(String guidance2) {
    this.guidance2 = guidance2;
  }

  /**
   * 説明文言1を取得する.
   * 
   * 説明文言1を呼び出し元に返却する
   * @return 説明文言1
   */
  public String getExplanation1() {
    return explanation1;
  }

  /**
   * 説明文言1を設定する.
   * 
   * 受け取った説明文言1を設定する
   * @param explanation1 説明文言1
   */
  public void setExplanation1(String explanation1) {
    this.explanation1 = explanation1;
  }

  /**
   * 説明文言2を取得する.
   * 
   * 説明文言2を呼び出し元に返却する
   * @return 説明文言2
   */
  public String getExplanation2() {
    return explanation2;
  }

  /**
   * 説明文言2を設定する.
   * 
   * 受け取った説明文言2を設定する
   * @param explanation2 説明文言2
   */
  public void setExplanation2(String explanation2) {
    this.explanation2 = explanation2;
  }

  /**
   * 説明文言3を取得する.
   * 
   * 説明文言3を呼び出し元に返却する
   * @return 説明文言3
   */
  public String getExplanation3() {
    return explanation3;
  }

  /**
   * 説明文言3を設定する.
   * 
   * 受け取った説明文言3を設定する
   * @param explanation3 説明文言3
   */
  public void setExplanation3(String explanation3) {
    this.explanation3 = explanation3;
  }

  /**
   * 説明文言4を取得する.
   * 
   * 説明文言4を呼び出し元に返却する
   * @return 説明文言4
   */
  public String getExplanation4() {
    return explanation4;
  }

  /**
   * 説明文言4を設定する.
   * 
   * 受け取った説明文言4を設定する
   * @param explanation4 説明文言4
   */
  public void setExplanation4(String explanation4) {
    this.explanation4 = explanation4;
  }

  /**
   * 説明文言5を取得する.
   * 
   * 説明文言5を呼び出し元に返却する
   * @return 説明文言5
   */
  public String getExplanation5() {
    return explanation5;
  }

  /**
   * 説明文言5を設定する.
   * 
   * 受け取った説明文言5を設定する
   * @param explanation5 説明文言5
   */
  public void setExplanation5(String explanation5) {
    this.explanation5 = explanation5;
  }

  /**
   * 作成基準日を取得する.
   * 
   * 作成基準日を呼び出し元に返却する
   * @return 作成基準日
   */
  public String getIssuedDate() {
    return issuedDate;
  }

  /**
   * 作成基準日を設定する.
   * 
   * 受け取った作成基準日を設定する
   * @param issuedDate 作成基準日
   */
  public void setIssuedDate(String issuedDate) {
    this.issuedDate = issuedDate;
  }

  /**
   * 取扱店を取得する.
   * 
   * 取扱店を呼び出し元に返却する
   * @return 取扱店
   */
  public String getBranchName() {
    return branchName;
  }

  /**
   * 取扱店を設定する.
   * 
   * 受け取った取扱店を設定する
   * @param branchName 取扱店
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
  public List<InformationRepaymentDetailDto> getDetails() {
    return details;
  }

  /**
   * 明細一覧を設定する.
   * 
   * 受け取った明細一覧を設定する
   * @param details 明細一覧
   */
  public void setDetails(List<InformationRepaymentDetailDto> details) {
    this.details = details;
  }  
}
