package com.moneyforward.cug_bankbookbase.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

/**
 * 金利見直し方法選択のご案内の詳細.
 *
 * 業務API「金利見直し方法選択のご案内」からのレスポンス
 */
@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
  "reviewInterestRateNotificationsId",
  "displayDocumentName",
  "displayProductName",  
  "applicantName",
  "applicationDeadline",
  "substance1",
  "substance2",
  "substance3",
  "handlingNumber",
  "loanName",
  "loanAmount",
  "currentAmount",
  "repaymentStartDate",
  "repaymentEndDate",
  "interestRateReviewMethod",
  "interestRate",
  "monthlyRepaymentAmount",
  "bonusRepaymentAmount",
  "floatingInterestRate",
  "floatingMonthlyRepaymentAmount",
  "floatingBonusRepaymentAmount",
  "threeYearsFixedInterestRate",
  "threeMonthlyRepaymentAmount",
  "threeyearsfixedmonthlyrepaymentamount",
  "fiveYearsFixedInterestRate",
  "fiveYearsFixedMonthlyRepaymentAmount",
  "fiveYearsFixedBonusRepaymentAmount",
  "tenYearsFixedInterestRate",
  "tenYearsFixedMonthlyRepaymentAmount",
  "tenYearsFixedBonusRepaymentAmount",
  "remarks1",
  "remarks2",
  "remarks3",
  "remarks4",
  "remarks5",
  "issuedDate",
  "branchName",
  "branchPhoneNumber"
})
public class InformationSelectingRateDto {

  /** 金利見直し方法選択案内識別子 */
  @JsonProperty("reviewInterestRateNotificationsId")
  private String reviewInterestRateNotificationsId;

  /** 通知または書類名 */
  @JsonProperty("displayDocumentName")
  private String displayDocumentName;

  /** 商品名（表示用） */
  @JsonProperty("displayProductName")
  private String displayProductName;

  /** 名義人 */
  @JsonProperty("applicantName")
  private String applicantName;
  
  /** 申し込み期限 */
  @JsonProperty("applicationDeadline")
  private String applicationDeadline;
  
  /** インバン内容1 */
  @JsonProperty("internetBankingInfo1")
  private String internetBankingInfo1;
  
  /** インバン内容2 */
  @JsonProperty("internetBankingInfo2")
  private String internetBankingInfo2;
  
  /** インバン内容3 */
  @JsonProperty("internetBankingInfo3")
  private String internetBankingInfo3;
  
  /** 取扱番号 */
  @JsonProperty("handlingNumber")
  private String handlingNumber;
  
  /** 制度融資名 */
  @JsonProperty("loanName")
  private String loanName;
  
  /** 融資額 */
  @JsonProperty("loanAmount")
  private Long loanAmount;
  
  /** 現在残高 */
  @JsonProperty("currentAmount")
  private Long currentAmount;
  
  /** 適用返済日 */
  @JsonProperty("repaymentStartDate")
  private String repaymentStartDate;
  
  /** 最終返済日 */
  @JsonProperty("repaymentEndDate")
  private String repaymentEndDate;
  
  /** 現在金利見直し方法 */
  @JsonProperty("interestRateReviewMethod")
  private String interestRateReviewMethod;
  
  /** 現在適用利率 */
  @JsonProperty("interestRate")
  private String interestRate;
  
  /** 現在毎月返済額 */
  @JsonProperty("monthlyRepaymentAmount")
  private Long monthlyRepaymentAmount;
  
  /** 現在ボーナス月返済額 */
  @JsonProperty("bonusRepaymentAmount")
  private Long bonusRepaymentAmount;
  
  /** 変動適用利率 */
  @JsonProperty("floatingInterestRate")
  private String floatingInterestRate;
  
  /** 変動毎月返済額 */
  @JsonProperty("floatingMonthlyRepaymentAmount")
  private Long floatingMonthlyRepaymentAmount;
  
  /** 変動ボーナス月返済額 */
  @JsonProperty("floatingBonusRepaymentAmount")
  private Long floatingBonusRepaymentAmount;
  
  /** 3年固定適用利率 */
  @JsonProperty("threeYearsFixedInterestRate")
  private String threeYearsFixedInterestRate;
  
  /** 3年固定毎月返済額 */
  @JsonProperty("threeYearsFixedMonthlyRepaymentAmount")
  private Long threeYearsFixedMonthlyRepaymentAmount;
  
  /** 3年固定ボーナス月返済額 */
  @JsonProperty("threeYearsFixedBonusRepaymentAmount")
  private Long threeYearsFixedBonusRepaymentAmount;
  
  /** 5年固定適用利率 */
  @JsonProperty("fiveYearsFixedInterestRate")
  private String fiveYearsFixedInterestRate;
  
  /** 5年固定毎月返済額 */
  @JsonProperty("fiveYearsFixedMonthlyRepaymentAmount")
  private Long fiveYearsFixedMonthlyRepaymentAmount;
  
  /** 5年固定ボーナス月返済額 */
  @JsonProperty("fiveYearsFixedBonusRepaymentAmount")
  private Long fiveYearsFixedBonusRepaymentAmount;
  
  /** 10年固定適用利率 */
  @JsonProperty("tenYearsFixedInterestRate")
  private String tenYearsFixedInterestRate;
  
  /** 10年固定毎月返済額 */
  @JsonProperty("tenYearsFixedMonthlyRepaymentAmount")
  private Long tenYearsFixedMonthlyRepaymentAmount;
  
  /** 10年固定ボーナス月返済額 */
  @JsonProperty("tenYearsFixedBonusRepaymentAmount")
  private Long tenYearsFixedBonusRepaymentAmount;
  
  /** 備考1 */
  @JsonProperty("remarks1")
  private String remarks1;
  
  /** 備考2 */
  @JsonProperty("remarks2")
  private String remarks2;
  
  /** 備考3 */
  @JsonProperty("remarks3")
  private String remarks3;
  
  /** 備考4 */
  @JsonProperty("remarks4")
  private String remarks4;
  
  /** 備考5 */
  @JsonProperty("remarks5")
  private String remarks5;
  
  /** 作成日 */
  @JsonProperty("issuedDate")
  private String issuedDate;
  
  /** 取扱店名 */
  @JsonProperty("branchName")
  private String branchName;
  
  /** 取扱店電話番号 */
  @JsonProperty("branchPhoneNumber")
  private String branchPhoneNumber;

  /**
   * 金利見直し方法選択案内識別子を取得する.
   * 
   * 金利見直し方法選択案内識別子を呼び出し元に返却する
   * @return 金利見直し方法選択案内識別子
   */
  public String getReviewInterestRateNotificationsId() {
    return reviewInterestRateNotificationsId;
  }

  /**
   * 金利見直し方法選択案内識別子を設定する.
   * 
   * 受け取った金利見直し方法選択案内識別子を設定する
   * @param reviewInterestRateNotificationsId 金利見直し方法選択案内識別子
   */
  public void setReviewInterestRateNotificationsId(String reviewInterestRateNotificationsId) {
    this.reviewInterestRateNotificationsId = reviewInterestRateNotificationsId;
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
   * 
   * 受け取った通知または書類名を設定する
   * @param  displayDocumentName 通知または書類名
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
   * 
   * 受け取った商品名（表示用）を設定する
   * @param  displayProductName 商品名（表示用）
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
   * 
   * 受け取った名義人を設定する
   * @param  applicantName 名義人
   */
  public void setApplicantName(String applicantName) {
    this.applicantName = applicantName;
  }

  /**
   * 申し込み期限を取得する.
   * 
   * 申し込み期限を呼び出し元に返却する
   * @return 申し込み期限
   */
  public String getApplicationDeadline() {
    return applicationDeadline;
  }

  /**
   * 申し込み期限を設定する.
   * 
   * 
   * 受け取った申し込み期限を設定する
   * @param  applicationDeadline 申し込み期限
   */
  public void setApplicationDeadline(String applicationDeadline) {
    this.applicationDeadline = applicationDeadline;
  }

  /**
   * インバン内容1を取得する.
   * 
   * インバン内容1を呼び出し元に返却する
   * @return インバン内容1
   */
  public String getInternetBankingInfo1() {
    return internetBankingInfo1;
  }

  /**
   * インバン内容1を設定する.
   * 
   * 受け取ったインバン内容1を設定する
   * @param  internetBankingInfo1 インバン内容1
   */
  public void setInternetBankingInfo1(String internetBankingInfo1) {
    this.internetBankingInfo1 = internetBankingInfo1;
  }

  /**
   * インバン内容2を取得する.
   * 
   * インバン内容2を呼び出し元に返却する
   * @return インバン内容2
   */
  public String getInternetBankingInfo2() {
    return internetBankingInfo2;
  }

  /**
   * インバン内容2を設定する.
   * 
   * 受け取ったインバン内容2を設定する
   * @param  internetBankingInfo2 インバン内容2
   */
  public void setInternetBankingInfo2(String internetBankingInfo2) {
    this.internetBankingInfo2 = internetBankingInfo2;
  }

  /**
   * インバン内容3を取得する.
   * 
   * インバン内容3を呼び出し元に返却する
   * @return インバン内容3
   */
  public String getInternetBankingInfo3() {
    return internetBankingInfo3;
  }

  /**
   * インバン内容3を設定する.
   * 
   * 受け取ったインバン内容3を設定する
   * @param  internetBankingInfo3 インバン内容3
   */
  public void setInternetBankingInfo3(String internetBankingInfo3) {
    this.internetBankingInfo3 = internetBankingInfo3;
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
   * @param  handlingNumber 取扱番号
   */
  public void setHandlingNumber(String handlingNumber) {
    this.handlingNumber = handlingNumber;
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
   * @param  loanName 制度融資名
   */
  public void setLoanName(String loanName) {
    this.loanName = loanName;
  }

  /**
   * 融資額を取得する.
   * 
   * 融資額を呼び出し元に返却する
   * @return 融資額
   */
  public Long getLoanAmount() {
    return loanAmount;
  }

  /**
   * 融資額を設定する.
   * 
   * 受け取った融資額を設定する
   * @param  loanAmount 融資額
   */
  public void setLoanAmount(Long loanAmount) {
    this.loanAmount = loanAmount;
  }

  /**
   * 現在残高を取得する.
   * 
   * 現在残高を呼び出し元に返却する
   * @return 現在残高
   */
  public Long getCurrentAmount() {
    return currentAmount;
  }

  /**
   * 現在残高を設定する.
   * 
   * 受け取った現在残高を設定する
   * @param  currentAmount 現在残高
   */
  public void setCurrentAmount(Long currentAmount) {
    this.currentAmount = currentAmount;
  }

  /**
   * 適用返済日を取得する.
   * 
   * 適用返済日を呼び出し元に返却する
   * @return 適用返済日
   */
  public String getRepaymentStartDate() {
    return repaymentStartDate;
  }

  /**
   * 適用返済日を設定する.
   * 
   * 受け取った適用返済日を設定する
   * @param  repaymentStartDate 適用返済日
   */
  public void setRepaymentStartDate(String repaymentStartDate) {
    this.repaymentStartDate = repaymentStartDate;
  }

  /**
   * 最終返済日を取得する.
   * 
   * 最終返済日を呼び出し元に返却する
   * @return 最終返済日
   */
  public String getRepaymentEndDate() {
    return repaymentEndDate;
  }

  /**
   * 最終返済日を設定する.
   * 
   * 受け取った最終返済日を設定する
   * @param  repaymentEndDate 最終返済日
   */
  public void setRepaymentEndDate(String repaymentEndDate) {
    this.repaymentEndDate = repaymentEndDate;
  }

  /**
   * 現在金利見直し方法を取得する.
   * 
   * 現在金利見直し方法を呼び出し元に返却する
   * @return 現在金利見直し方法
   */
  public String getInterestRateReviewMethod() {
    return interestRateReviewMethod;
  }

  /**
   * 現在金利見直し方法を設定する.
   * 
   * 受け取った現在金利見直し方法を設定する
   * @param  interestRateReviewMethod 現在金利見直し方法
   */
  public void setInterestRateReviewMethod(String interestRateReviewMethod) {
    this.interestRateReviewMethod = interestRateReviewMethod;
  }

  /**
   * 現在適用利率を取得する.
   * 
   * 現在適用利率を呼び出し元に返却する
   * @return 現在適用利率
   */
  public String getInterestRate() {
    return interestRate;
  }

  /**
   * 現在適用利率を設定する.
   * 
   * 受け取った現在適用利率を設定する
   * @param  interestRate 現在適用利率
   */
  public void setInterestRate(String interestRate) {
    this.interestRate = interestRate;
  }

  /**
   * 現在毎月返済額を取得する.
   * 
   * 現在毎月返済額を呼び出し元に返却する
   * @return 現在毎月返済額
   */
  public Long getMonthlyRepaymentAmount() {
    return monthlyRepaymentAmount;
  }

  /**
   * 現在毎月返済額を設定する.
   * 
   * 受け取った現在毎月返済額を設定する
   * @param  monthlyRepaymentAmount 現在毎月返済額
   */
  public void setMonthlyRepaymentAmount(Long monthlyRepaymentAmount) {
    this.monthlyRepaymentAmount = monthlyRepaymentAmount;
  }

  /**
   * 現在ボーナス月返済額を取得する.
   * 
   * 現在ボーナス月返済額を呼び出し元に返却する
   * @return 現在ボーナス月返済額
   */
  public Long getBonusRepaymentAmount() {
    return bonusRepaymentAmount;
  }

  /**
   * 現在ボーナス月返済額を設定する.
   * 
   * 受け取った現在ボーナス月返済額を設定する
   * @param  bonusRepaymentAmount 現在ボーナス月返済額
   */
  public void setBonusRepaymentAmount(Long bonusRepaymentAmount) {
    this.bonusRepaymentAmount = bonusRepaymentAmount;
  }

  /**
   * 変動適用利率を取得する.
   * 
   * 変動適用利率を呼び出し元に返却する
   * @return 変動適用利率
   */
  public String getFloatingInterestRate() {
    return floatingInterestRate;
  }

  /**
   * 変動適用利率を設定する.
   * 
   * 受け取った変動適用利率を設定する
   * @param  floatingInterestRate 変動適用利率
   */
  public void setFloatingInterestRate(String floatingInterestRate) {
    this.floatingInterestRate = floatingInterestRate;
  }

  /**
   * 変動毎月返済額を取得する.
   * 
   * 変動毎月返済額を呼び出し元に返却する
   * @return 変動毎月返済額
   */
  public Long getFloatingMonthlyRepaymentAmount() {
    return floatingMonthlyRepaymentAmount;
  }

  /**
   * 変動毎月返済額を設定する.
   * 
   * 受け取った変動毎月返済額を設定する
   * @param  floatingMonthlyRepaymentAmount 変動毎月返済額
   */
  public void setFloatingMonthlyRepaymentAmount(Long floatingMonthlyRepaymentAmount) {
    this.floatingMonthlyRepaymentAmount = floatingMonthlyRepaymentAmount;
  }

  /**
   * 変動ボーナス月返済額を取得する.
   * 
   * 変動ボーナス月返済額を呼び出し元に返却する
   * @return 変動ボーナス月返済額
   */
  public Long getFloatingBonusRepaymentAmount() {
    return floatingBonusRepaymentAmount;
  }

  /**
   * 変動ボーナス月返済額を設定する.
   * 
   * 受け取った変動ボーナス月返済額を設定する
   * @param  floatingBonusRepaymentAmount 変動ボーナス月返済額
   */
  public void setFloatingBonusRepaymentAmount(Long floatingBonusRepaymentAmount) {
    this.floatingBonusRepaymentAmount = floatingBonusRepaymentAmount;
  }

  /**
   * 3年固定適用利率を取得する.
   * 
   * 3年固定適用利率を呼び出し元に返却する
   * @return 3年固定適用利率
   */
  public String getThreeYearsFixedInterestRate() {
    return threeYearsFixedInterestRate;
  }

  /**
   * 3年固定適用利率を設定する.
   * 
   * 受け取った3年固定適用利率を設定する
   * @param  threeYearsFixedInterestRate 3年固定適用利率
   */
  public void setThreeYearsFixedInterestRate(String threeYearsFixedInterestRate) {
    this.threeYearsFixedInterestRate = threeYearsFixedInterestRate;
  }

  /**
   * 3年固定毎月返済額を取得する.
   * 
   * 3年固定毎月返済額を呼び出し元に返却する
   * @return 3年固定毎月返済額
   */
  public Long getThreeYearsFixedMonthlyRepaymentAmount() {
    return threeYearsFixedMonthlyRepaymentAmount;
  }

  /**
   * 3年固定毎月返済額を設定する.
   * 
   * 受け取った3年固定毎月返済額を設定する
   * @param  threeYearsFixedMonthlyRepaymentAmount 3年固定毎月返済額
   */
  public void setThreeYearsFixedMonthlyRepaymentAmount(Long threeYearsFixedMonthlyRepaymentAmount) {
    this.threeYearsFixedMonthlyRepaymentAmount = threeYearsFixedMonthlyRepaymentAmount;
  }

  /**
   * 3年固定ボーナス月返済額を取得する.
   * 
   * 3年固定ボーナス月返済額を呼び出し元に返却する
   * @return 3年固定ボーナス月返済額
   */
  public Long getThreeYearsFixedBonusRepaymentAmount() {
    return threeYearsFixedBonusRepaymentAmount;
  }

  /**
   * 3年固定ボーナス月返済額を設定する.
   * 
   * 受け取った3年固定ボーナス月返済額を設定する
   * @param  threeYearsFixedBonusRepaymentAmount 3年固定ボーナス月返済額
   */
  public void setThreeYearsFixedBonusRepaymentAmount(Long threeYearsFixedBonusRepaymentAmount) {
    this.threeYearsFixedBonusRepaymentAmount = threeYearsFixedBonusRepaymentAmount;
  }

  /**
   * 5年固定適用利率を取得する.
   * 
   * 5年固定適用利率を呼び出し元に返却する
   * @return 5年固定適用利率
   */
  public String getFiveYearsFixedInterestRate() {
    return fiveYearsFixedInterestRate;
  }

  /**
   * 5年固定適用利率を設定する.
   * 
   * 受け取った5年固定適用利率を設定する
   * @param  fiveYearsFixedInterestRate 5年固定適用利率
   */
  public void setFiveYearsFixedInterestRate(String fiveYearsFixedInterestRate) {
    this.fiveYearsFixedInterestRate = fiveYearsFixedInterestRate;
  }

  /**
   * 5年固定毎月返済額を取得する.
   * 
   * 5年固定毎月返済額を呼び出し元に返却する
   * @return 5年固定毎月返済額
   */
  public Long getFiveYearsFixedMonthlyRepaymentAmount() {
    return fiveYearsFixedMonthlyRepaymentAmount;
  }

  /**
   * 5年固定毎月返済額を設定する.
   * 
   * 受け取った5年固定毎月返済額を設定する
   * @param  fiveYearsFixedMonthlyRepaymentAmount 5年固定毎月返済額
   */
  public void setFiveYearsFixedMonthlyRepaymentAmount(Long fiveYearsFixedMonthlyRepaymentAmount) {
    this.fiveYearsFixedMonthlyRepaymentAmount = fiveYearsFixedMonthlyRepaymentAmount;
  }

  /**
   * 5年固定ボーナス月返済額を取得する.
   * 
   * 5年固定ボーナス月返済額を呼び出し元に返却する
   * @return 5年固定ボーナス月返済額
   */
  public Long getFiveYearsFixedBonusRepaymentAmount() {
    return fiveYearsFixedBonusRepaymentAmount;
  }

  /**
   * 5年固定ボーナス月返済額を設定する.
   * 
   * 受け取った5年固定ボーナス月返済額を設定する
   * @param  fiveYearsFixedBonusRepaymentAmount 5年固定ボーナス月返済額
   */
  public void setFiveYearsFixedBonusRepaymentAmount(Long fiveYearsFixedBonusRepaymentAmount) {
    this.fiveYearsFixedBonusRepaymentAmount = fiveYearsFixedBonusRepaymentAmount;
  }

  /**
   * 10年固定適用利率を取得する.
   * 
   * 10年固定適用利率を呼び出し元に返却する
   * @return 10年固定適用利率
   */
  public String getTenYearsFixedInterestRate() {
    return tenYearsFixedInterestRate;
  }

  /**
   * 10年固定適用利率を設定する.
   * 
   * 受け取った10年固定適用利率を設定する
   * @param  tenYearsFixedInterestRate 10年固定適用利率
   */
  public void setTenYearsFixedInterestRate(String tenYearsFixedInterestRate) {
    this.tenYearsFixedInterestRate = tenYearsFixedInterestRate;
  }

  /**
   * 10年固定毎月返済額を取得する.
   * 
   * 10年固定毎月返済額を呼び出し元に返却する
   * @return 10年固定毎月返済額
   */
  public Long getTenYearsFixedMonthlyRepaymentAmount() {
    return tenYearsFixedMonthlyRepaymentAmount;
  }

  /**
   * 10年固定毎月返済額を設定する.
   * 
   * 受け取った10年固定毎月返済額を設定する
   * @param  tenYearsFixedMonthlyRepaymentAmount 10年固定毎月返済額
   */
  public void setTenYearsFixedMonthlyRepaymentAmount(Long tenYearsFixedMonthlyRepaymentAmount) {
    this.tenYearsFixedMonthlyRepaymentAmount = tenYearsFixedMonthlyRepaymentAmount;
  }

  /**
   * 10年固定ボーナス月返済額を取得する.
   * 
   * 10年固定ボーナス月返済額を呼び出し元に返却する
   * @return 10年固定ボーナス月返済額
   */
  public Long getTenYearsFixedBonusRepaymentAmount() {
    return tenYearsFixedBonusRepaymentAmount;
  }

  /**
   * 10年固定ボーナス月返済額を設定する.
   * 
   * 受け取った10年固定ボーナス月返済額を設定する
   * @param  tenYearsFixedBonusRepaymentAmount 10年固定ボーナス月返済額
   */
  public void setTenYearsFixedBonusRepaymentAmount(Long tenYearsFixedBonusRepaymentAmount) {
    this.tenYearsFixedBonusRepaymentAmount = tenYearsFixedBonusRepaymentAmount;
  }

  /**
   * 備考1を取得する.
   * 
   * 備考1を呼び出し元に返却する
   * @return 備考1
   */
  public String getRemarks1() {
    return remarks1;
  }

  /**
   * 備考1を設定する.
   * 
   * 受け取った備考1を設定する
   * @param  remarks1 備考1
   */
  public void setRemarks1(String remarks1) {
    this.remarks1 = remarks1;
  }

  /**
   * 備考2を取得する.
   * 
   * 備考2を呼び出し元に返却する
   * @return 備考2
   */
  public String getRemarks2() {
    return remarks2;
  }

  /**
   * 備考2を設定する.
   * 
   * 受け取った備考2を設定する
   * @param  remarks2 備考2
   */
  public void setRemarks2(String remarks2) {
    this.remarks2 = remarks2;
  }

  /**
   * 備考3を取得する.
   * 
   * 備考3を呼び出し元に返却する
   * @return 備考3
   */
  public String getRemarks3() {
    return remarks3;
  }

  /**
   * 備考3を設定する.
   * 
   * 受け取った備考3を設定する
   * @param  remarks3 備考3
   */
  public void setRemarks3(String remarks3) {
    this.remarks3 = remarks3;
  }

  /**
   * 備考4を取得する.
   * 
   * 備考4を呼び出し元に返却する
   * @return 備考4
   */
  public String getRemarks4() {
    return remarks4;
  }

  /**
   * 備考4を設定する.
   * 
   * 受け取った備考4を設定する
   * @param  remarks4 備考4
   */
  public void setRemarks4(String remarks4) {
    this.remarks4 = remarks4;
  }

  /**
   * 備考5を取得する.
   * 
   * 備考5を呼び出し元に返却する
   * @return 備考5
   */
  public String getRemarks5() {
    return remarks5;
  }

  /**
   * 備考5を設定する.
   * 
   * 受け取った備考5を設定する
   * @param  remarks5 備考5
   */
  public void setRemarks5(String remarks5) {
    this.remarks5 = remarks5;
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
   * @param  issuedDate 作成日
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
   * @param  branchName 取扱店名
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
   * @param  branchPhoneNumber 取扱店電話番号
   */
  public void setBranchPhoneNumber(String branchPhoneNumber) {
    this.branchPhoneNumber = branchPhoneNumber;
  }

}
