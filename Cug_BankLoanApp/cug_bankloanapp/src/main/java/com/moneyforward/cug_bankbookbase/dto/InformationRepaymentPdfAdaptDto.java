package com.moneyforward.cug_bankbookbase.dto;


/**
 * ご返済のご案内の明細部(PDF連携).
 *
 */
public class InformationRepaymentPdfAdaptDto {
  /**返済日又は利払日*/
  private String repaymentDate;

  /**返済元利金合計*/
  private String principalAndInterest;

  /**毎回分利息計算日数*/
  private String monthlyInterestCalcDate;

  /**毎回分利息額*/
  private String monthlyInterest;

  /**毎回分返済元金*/
  private String monthlyPrincipal;

  /**増額分利息額*/
  private String increasedInterest;

  /**増額分返済元金*/
  private String increasedPrincipal;

  /**返済後残高*/
  private String afterRepaymentBalance;

  /**未収利息*/
  private String accruedInterest;

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
  public String getPrincipalAndInterest() {
    return principalAndInterest;
  }

  /**
   * 返済元利金合計を設定する.
   * 
   * 受け取った返済元利金合計を設定する
   * @param principalAndInterest 返済元利金合計
   */
  public void setPrincipalAndInterest(String principalAndInterest) {
    this.principalAndInterest = principalAndInterest;
  }

  /**
   * 毎回分利息計算日数を取得する.
   * 
   * 毎回分利息計算日数を呼び出し元に返却する
   * @return 毎回分利息計算日数
   */
  public String getMonthlyInterestCalcDate() {
    return monthlyInterestCalcDate;
  }

  /**
   * 毎回分利息計算日数を設定する.
   * 
   * 受け取った毎回分利息計算日数を設定する
   * @param monthlyInterestCalcDate 毎回分利息計算日数
   */
  public void setMonthlyInterestCalcDate(String monthlyInterestCalcDate) {
    this.monthlyInterestCalcDate = monthlyInterestCalcDate;
  }

  /**
   * 毎回分利息額を取得する.
   * 
   * 毎回分利息額を呼び出し元に返却する
   * @return 毎回分利息額
   */
  public String getMonthlyInterest() {
    return monthlyInterest;
  }

  /**
   * 毎回分利息額を設定する.
   * 
   * 受け取った毎回分利息額を設定する
   * @param monthlyInterest 毎回分利息額
   */
  public void setMonthlyInterest(String monthlyInterest) {
    this.monthlyInterest = monthlyInterest;
  }

  /**
   * 毎回分返済元金を取得する.
   * 
   * 毎回分返済元金を呼び出し元に返却する
   * @return 毎回分返済元金
   */
  public String getMonthlyPrincipal() {
    return monthlyPrincipal;
  }

  /**
   * 毎回分返済元金を設定する.
   * 
   * 受け取った毎回分返済元金を設定する
   * @param monthlyPrincipal 毎回分返済元金
   */
  public void setMonthlyPrincipal(String monthlyPrincipal) {
    this.monthlyPrincipal = monthlyPrincipal;
  }

  /**
   * 増額分利息額を取得する.
   * 
   * 増額分利息額を呼び出し元に返却する
   * @return 増額分利息額
   */
  public String getIncreasedInterest() {
    return increasedInterest;
  }

  /**
   * 増額分利息額を設定する.
   * 
   * 受け取った増額分利息額を設定する
   * @param increasedInterest 増額分利息額
   */
  public void setIncreasedInterest(String increasedInterest) {
    this.increasedInterest = increasedInterest;
  }

  /**
   * 増額分返済元金を取得する.
   * 
   * 増額分返済元金を呼び出し元に返却する
   * @return 増額分返済元金
   */
  public String getIncreasedPrincipal() {
    return increasedPrincipal;
  }

  /**
   * 増額分返済元金を設定する.
   * 
   * 受け取った増額分返済元金を設定する
   * @param increasedPrincipal 増額分返済元金
   */
  public void setIncreasedPrincipal(String increasedPrincipal) {
    this.increasedPrincipal = increasedPrincipal;
  }

  /**
   * 返済後残高を取得する.
   * 
   * 返済後残高を呼び出し元に返却する
   * @return 返済後残高
   */
  public String getAfterRepaymentBalance() {
    return afterRepaymentBalance;
  }

  /**
   * 返済後残高を設定する.
   * 
   * 受け取った返済後残高を設定する
   * @param afterRepaymentBalance 返済後残高
   */
  public void setAfterRepaymentBalance(String afterRepaymentBalance) {
    this.afterRepaymentBalance = afterRepaymentBalance;
  }

  /**
   * 未収利息を取得する.
   * 
   * 未収利息を呼び出し元に返却する
   * @return 未収利息
   */
  public String getAccruedInterest() {
    return accruedInterest;
  }

  /**
   * 未収利息を設定する.
   * 
   * 受け取った未収利息を設定する
   * @param accruedInterest 未収利息
   */
  public void setAccruedInterest(String accruedInterest) {
    this.accruedInterest = accruedInterest;
  }

}
