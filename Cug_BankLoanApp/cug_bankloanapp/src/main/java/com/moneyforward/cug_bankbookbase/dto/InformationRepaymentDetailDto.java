package com.moneyforward.cug_bankbookbase.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

/**
 * ご返済のご案内の明細部の詳細.
 *
 * 業務API「ご返済のご案内の明細部」からのレスポンス
 */
@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
  "repaymentDate",
  "principalAndInterest",
  "monthlyInterestCalcDate",
  "monthlyInterest",
  "monthlyPrincipal",
  "increasedInterest",
  "increasedPrincipal",
  "afterRepaymentBalance",
  "accruedInterest"
})
public class InformationRepaymentDetailDto {
  /**返済日又は利払日*/
  @JsonProperty("repaymentDate")
  private String repaymentDate;

  /**返済元利金合計*/
  @JsonProperty("principalAndInterest")
  private Long principalAndInterest;

  /**毎回分利息計算日数*/
  @JsonProperty("monthlyInterestCalcDate")
  private Long monthlyInterestCalcDate;

  /**毎回分利息額*/
  @JsonProperty("monthlyInterest")
  private Long monthlyInterest;

  /**毎回分返済元金*/
  @JsonProperty("monthlyPrincipal")
  private Long monthlyPrincipal;

  /**増額分利息額*/
  @JsonProperty("increasedInterest")
  private Long increasedInterest;

  /**増額分返済元金*/
  @JsonProperty("increasedPrincipal")
  private Long increasedPrincipal;

  /**返済後残高*/
  @JsonProperty("afterRepaymentBalance")
  private Long afterRepaymentBalance;

  /**未収利息*/
  @JsonProperty("accruedInterest")
  private Long accruedInterest;

  /**
   * 返済日又は利払日を取得する.
   * 
   * 返済日又は利払日を呼び出し元に返却する
   * @return 返済日又は利払日
   */
  public String getRepaymentDate() {
    return repaymentDate;
  }

  /**
   * 返済日又は利払日を設定する.
   *
   * 受け取った返済日又は利払日を設定する 
   * @param repaymentDate 返済日又は利払日
   */
  public void setRepaymentDate(String repaymentDate) {
    this.repaymentDate = repaymentDate;
  }

  /**
   * 返済元利金合計を取得する.
   * 
   * 返済元利金合計を呼び出し元に返却する
   * @return 返済元利金合計
   */
  public Long getPrincipalAndInterest() {
    return principalAndInterest;
  }

  /**
   * 返済元利金合計を設定する.
   * 
   * 受け取った返済元利金合計を設定する
   * @param principalAndInterest 返済元利金合計
   */
  public void setPrincipalAndInterest(Long principalAndInterest) {
    this.principalAndInterest = principalAndInterest;
  }

  /**
   * 毎回分利息計算日数を取得する.
   * 
   * 毎回分利息計算日数を呼び出し元に返却する
   * @return 毎回分利息計算日数
   */
  public Long getMonthlyInterestCalcDate() {
    return monthlyInterestCalcDate;
  }

  /**
   * 毎回分利息計算日数を設定する.
   * 
   * 受け取った毎回分利息計算日数を設定する
   * @param monthlyInterestCalcDate 毎回分利息計算日数
   */
  public void setMonthlyInterestCalcDate(Long monthlyInterestCalcDate) {
    this.monthlyInterestCalcDate = monthlyInterestCalcDate;
  }

  /**
   * 毎回分利息額を取得する.
   * 
   * 毎回分利息額を呼び出し元に返却する
   * @return 毎回分利息額
   */
  public Long getMonthlyInterest() {
    return monthlyInterest;
  }

  /**
   * 毎回分利息額を設定する.
   * 
   * 受け取った毎回分利息額を設定する
   * @param monthlyInterest 毎回分利息額
   */
  public void setMonthlyInterest(Long monthlyInterest) {
    this.monthlyInterest = monthlyInterest;
  }

  /**
   * 毎回分返済元金を取得する.
   * 
   * 毎回分返済元金を呼び出し元に返却する
   * @return 毎回分返済元金
   */
  public Long getMonthlyPrincipal() {
    return monthlyPrincipal;
  }

  /**
   * 毎回分返済元金を設定する.
   * 
   * 受け取った毎回分返済元金を設定する
   * @param monthlyPrincipal 毎回分返済元金
   */
  public void setMonthlyPrincipal(Long monthlyPrincipal) {
    this.monthlyPrincipal = monthlyPrincipal;
  }

  /**
   * 増額分利息額を取得する.
   * 
   * 増額分利息額を呼び出し元に返却する
   * @return 増額分利息額
   */
  public Long getIncreasedInterest() {
    return increasedInterest;
  }

  /**
   * 増額分利息額を設定する.
   * 
   * 受け取った増額分利息額を設定する
   * @param increasedInterest 増額分利息額
   */
  public void setIncreasedInterest(Long increasedInterest) {
    this.increasedInterest = increasedInterest;
  }

  /**
   * 増額分返済元金を取得する.
   * 
   * 増額分返済元金を呼び出し元に返却する
   * @return 増額分返済元金
   */
  public Long getIncreasedPrincipal() {
    return increasedPrincipal;
  }

  /**
   * 増額分返済元金を設定する.
   * 
   * 受け取った増額分返済元金を設定する
   * @param increasedPrincipal 増額分返済元金
   */
  public void setIncreasedPrincipal(Long increasedPrincipal) {
    this.increasedPrincipal = increasedPrincipal;
  }

  /**
   * 返済後残高を取得する.
   * 
   * 返済後残高を呼び出し元に返却する
   * @return 返済後残高
   */
  public Long getAfterRepaymentBalance() {
    return afterRepaymentBalance;
  }

  /**
   * 返済後残高を設定する.
   * 
   * 受け取った返済後残高を設定する
   * @param afterRepaymentBalance 返済後残高
   */
  public void setAfterRepaymentBalance(Long afterRepaymentBalance) {
    this.afterRepaymentBalance = afterRepaymentBalance;
  }

  /**
   * 未収利息を取得する.
   * 
   * 未収利息を呼び出し元に返却する
   * @return 未収利息
   */
  public Long getAccruedInterest() {
    return accruedInterest;
  }

  /**
   * 未収利息を設定する.
   *
   * 受け取った未収利息を設定する 
   * @param accruedInterest 未収利息
   */
  public void setAccruedInterest(Long accruedInterest) {
    this.accruedInterest = accruedInterest;
  }

}
