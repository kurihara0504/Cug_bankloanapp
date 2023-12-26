
package com.moneyforward.cug_bankbookbase.dto;

import java.util.ArrayList;
import java.util.List;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

/**
 * 口座情報に紐づく帳票のリスト（業務APIレスポンス）.
 *
 *　業務API「口座帳票一覧照会」からのレスポンス
 */

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
  "timeDepositMaturityNotifications",
  "repaymentNotifications",
  "noticeCardLoanTransaction",
  "reviewInterestRateNotifications",
  "foreignTimeDepositNotificationsOpen",
  "foreignTimeDepositMaturityNotifications",
  "endLoanContract",
  "noticeFloatingInterestRateTimeDeposit",
})
public class CugReportDto {

  // ! 定期預金期日のご案内のリスト
  @JsonProperty("timeDepositMaturityNotifications")
  private List<ReportInformationDepositDto> informationDeposit = new ArrayList<ReportInformationDepositDto>();
  // ! 返済のご案内のリスト
  @JsonProperty("repaymentNotifications")
  private List<ReportInformationRepaymentDto> informationRepayment = new ArrayList<ReportInformationRepaymentDto>();
  // ! カードローンお取り引き内容のお知らせのリスト
  @JsonProperty("noticeCardLoanTransaction")
  private List<ReportCardLoanTransactionDto> cardLoanTransaction = new ArrayList<ReportCardLoanTransactionDto>();
  // ! 金利見直し方法選択のご案内のリスト
  @JsonProperty("reviewInterestRateNotifications")
  private List<ReportInformationSelectingRateDto> informationSelectingRate = new ArrayList<ReportInformationSelectingRateDto>();
  // ! 外貨定期預金預入のご案内のリスト
  @JsonProperty("foreignTimeDepositNotificationsOpen")
  private List<ReportForeignCurrencyDepositOpenDto> foreignCurrencyDepositOpen = new ArrayList<ReportForeignCurrencyDepositOpenDto>();
  // ! 外貨定期預金期日のご案内のリスト
  @JsonProperty("foreignTimeDepositMaturityNotifications")
  private List<ReportForeignCurrencyDueDateDto> foreignCurrencyDueDate = new ArrayList<ReportForeignCurrencyDueDateDto>();
  //! 契約終了通知書のリスト
  @JsonProperty("endLoanContract")
  private List<ReportContractTerminationDto> contractTermination = new ArrayList<ReportContractTerminationDto>();
  // ! 変動金利定期預金のお知らせのリスト
  @JsonProperty("noticeFloatingInterestRateTimeDeposit")
  private List<ReportNoticeFloatingRateDto> noticeFloatingRate = new ArrayList<ReportNoticeFloatingRateDto>();

  /**
   * 定期預金満期案内のリストを取得する.
   *
   * 定期預金満期案内のリストを呼び出し元に返却する
   * @return 定期預金満期案内のリスト
   */
  public List<ReportInformationDepositDto> getInformationDeposit() {
    return informationDeposit;
  }

  /**
   * 定期預金満期案内のリストを設定する.
   *
   * 受け取った定期預金満期案内のリストを設定する
   * @param timeDepositMaturityNotifications 定期預金満期案内のリスト
   */
  public void setInformationDeposit(List<ReportInformationDepositDto> informationDeposit) {
    this.informationDeposit = informationDeposit;
  }

  /**
   * 返済のご案内のリストを取得する.
   *
   * 返済のご案内のリストを呼び出し元に返却する
   * @return 返済のご案内のリスト
   */
  public List<ReportInformationRepaymentDto> getInformationRepayment() {
  return informationRepayment;
  }

  /**
   * 返済のご案内のリストを設定する.
   *
   * 受け取った返済のご案内のリストを設定する
   * @param timeDepositMaturityNotifications 返済のご案内のリスト
   */
  public void setInformationRepayment(List<ReportInformationRepaymentDto> informationRepayment) {
    this.informationRepayment = informationRepayment;
  }

  /**
   * カードローンお取り引き内容のお知らせのリストを取得する.
   *
   * カードローンお取り引き内容のお知らせのリストを呼び出し元に返却する
   * @return カードローンお取り引き内容のお知らせのリスト
   */
  public List<ReportCardLoanTransactionDto> getCardLoanTransaction() {
    return cardLoanTransaction;
  }

  /**
   * カードローンお取り引き内容のお知らせのリストを設定する.
   *
   * 受け取ったカードローンお取り引き内容のお知らせのリストを設定する
   * @param cardLoanTransaction カードローンお取り引き内容のお知らせのリスト
   */
  public void setCardLoanTransaction(List<ReportCardLoanTransactionDto> cardLoanTransaction) {
    this.cardLoanTransaction = cardLoanTransaction;
  }

  /**
   * 金利見直し方法選択のご案内のリストを取得する.
   *
   * 金利見直し方法選択のご案内のリストを呼び出し元に返却する
   * @return 金利見直し方法選択のご案内のリスト
   */
  public List<ReportInformationSelectingRateDto> getInformationSelectingRate() {
    return informationSelectingRate;
  }

  /**
   * 金利見直し方法選択のご案内のリストを設定する.
   *
   * 受け取った金利見直し方法選択のご案内のリストを設定する
   * @param informationSelectingRate 金利見直し方法選択のご案内のリスト
   */
  public void setInformationSelectingRate(List<ReportInformationSelectingRateDto> informationSelectingRate) {
    this.informationSelectingRate = informationSelectingRate;
  }

  /**
   * 外貨定期預金預入のご案内のリストを取得する.
   *
   * 外貨定期預金預入のご案内のリストを呼び出し元に返却する
   * @return 外貨定期預金預入のご案内のリスト
   */
  public List<ReportForeignCurrencyDepositOpenDto> getForeignCurrencyDepositOpen() {
    return foreignCurrencyDepositOpen;
  }

  /**
   * 外貨定期預金預入のご案内のリストを設定する.
   *
   * 受け取った外貨定期預金預入のご案内のリストを設定する
   * @param foreignCurrencyDepositOpen 外貨定期預金預入のご案内のリスト
   */
  public void setForeignCurrencyDepositOpen(List<ReportForeignCurrencyDepositOpenDto> foreignCurrencyDepositOpen) {
    this.foreignCurrencyDepositOpen = foreignCurrencyDepositOpen;
  }

  /**
   * 外貨定期預金期日のご案内のリストを取得する.
   *
   * 外貨定期預金期日のご案内のリストを呼び出し元に返却する
   * @return 外貨定期預金期日のご案内のリスト
   */
  public List<ReportForeignCurrencyDueDateDto> getForeignCurrencyDueDate() {
    return foreignCurrencyDueDate;
  }

  /**
   * 外貨定期預金期日のご案内のリストを設定する.
   *
   * 受け取った外貨定期預金期日のご案内のリストを設定する
   * @param foreignCurrencyDueDate 外貨定期預金期日のご案内のリスト
   */
  public void setForeignCurrencyDueDate(List<ReportForeignCurrencyDueDateDto> foreignCurrencyDueDate) {
    this.foreignCurrencyDueDate = foreignCurrencyDueDate;
  }

  /**
   * 契約終了通知書のリストを取得する.
   *
   * 契約終了通知書のリストを呼び出し元に返却する
   * @return 契約終了通知書のリスト
   */
  public List<ReportContractTerminationDto> getContractTermination() {
    return contractTermination;
  }

  /**
   * 契約終了通知書のリストを設定する.
   *
   * 受け取った契約終了通知書のリストを設定する
   * @param contractTermination 契約終了通知書のリスト
   */
  public void setContractTermination(List<ReportContractTerminationDto> contractTermination) {
    this.contractTermination = contractTermination;
  }

  /**
   * 変動金利定期預金のお知らせのリストのリストを取得する.
   *
   * 変動金利定期預金のお知らせのリストのリストを呼び出し元に返却する
   * @return 変動金利定期預金のお知らせのリストのリスト
   */
  public List<ReportNoticeFloatingRateDto> getNoticeFloatingRate() {
    return noticeFloatingRate;
  }

  /**
   * 変動金利定期預金のお知らせのリストのリストを設定する.
   *
   * 受け取った変動金利定期預金のお知らせのリストのリストを設定する
   * @param noticeFloatingRate 変動金利定期預金のお知らせのリストのリスト
   */
  public void setNoticeFloatingRate(List<ReportNoticeFloatingRateDto> noticeFloatingRate) {
    this.noticeFloatingRate = noticeFloatingRate;
  }

}