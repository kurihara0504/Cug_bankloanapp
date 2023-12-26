package com.moneyforward.cug_bankbookbase.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

/**
 * カードローン取引内容通知のリスト詳細.
 *
 * 業務API「カードローン取引内容通知照会」からのレスポンス
 */
@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
  "transactionDate",
  "loanAmount",
  "repaymentAmount",
  "description",
  "loanBalance"
})
public class CardLoanTransactionDetailsDto {

  // ! 取引日
  @JsonProperty("transactionDate")
  private String transactionDate;
  // ! 借入額
  @JsonProperty("loanAmount")
  private Long loanAmount;
  // ! 返済額
  @JsonProperty("repaymentAmount")
  private Long repaymentAmount;
  // ! 摘要
  @JsonProperty("description")
  private String description;
  // ! 借入残高
  @JsonProperty("loanBalance")
  private Long loanBalance;
  
  /**
   * 取引日を取得する.
   *
   * 取引日を呼び出し元に返却する
   * @return 取引日
   */
  public String getTransactionDate() {
    return transactionDate;
  }
  /**
   * 取引日を設定する.
   *
   * 受け取った取引日を設定する
   * @param transactionDate 取引日
   */
  public void setTransactionDate(String transactionDate) {
    this.transactionDate = transactionDate;
  }
  /**
   * 借入額を取得する.
   *
   * 借入額を呼び出し元に返却する
   * @return 借入額
   */
  public Long getLoanAmount() {
    return loanAmount;
  }
  /**
   * 借入額を設定する.
   *
   * 受け取った借入額を設定する
   * @param loanAmount 借入額
   */
  public void setLoanAmount(Long loanAmount) {
    this.loanAmount = loanAmount;
  }
  /**
   * 返済額を取得する.
   *
   * 返済額を呼び出し元に返却する
   * @return 返済額
   */
  public Long getRepaymentAmount() {
    return repaymentAmount;
  }
  /**
   * 返済額を設定する.
   *
   * 受け取った返済額を設定する
   * @param repaymentAmount 返済額
   */
  public void setRepaymentAmount(Long repaymentAmount) {
    this.repaymentAmount = repaymentAmount;
  }
  /**
   * 摘要を取得する.
   *
   * 摘要を呼び出し元に返却する
   * @return 摘要
   */
  public String getDescription() {
    return description;
  }
  /**
   * 摘要を設定する.
   *
   * 受け取った摘要を設定する
   * @param description 摘要
   */
  public void setDescription(String description) {
    this.description = description;
  }
  /**
   * 借入残高を取得する.
   *
   * 借入残高を呼び出し元に返却する
   * @return 借入残高
   */
  public Long getLoanBalance() {
    return loanBalance;
  }
  /**
   * 借入残高を設定する.
   *
   * 受け取った借入残高を設定する
   * @param loanBalance 借入残高
   */
  public void setLoanBalance(Long loanBalance) {
    this.loanBalance = loanBalance;
  }
  

}