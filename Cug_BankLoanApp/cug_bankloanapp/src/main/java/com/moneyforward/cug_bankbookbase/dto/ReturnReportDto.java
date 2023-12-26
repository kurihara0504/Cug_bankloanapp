
package com.moneyforward.cug_bankbookbase.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

/**
 * 帳票情報の詳細（内部APIレスポンス）.
 *
 *　DTO「帳票情報一覧(内部API)」のリスト内部
 */

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
  "report_id",
  "sub_account_id",
  "product_name",
  "report_name",
  "classification",
  "number",
  "type",
  "date_of_issue",
  "report_distinction_flag"
})
public class ReturnReportDto {

  // ! 帳票識別子
  @JsonProperty("report_id")
  private String reportId;
  // ! 口座識別子
  @JsonProperty("sub_account_id")
  private String subAccountId;
  // ! 商品名
  @JsonProperty("product_name")
  private String productName;
  // ! 書類
  @JsonProperty("report_name")
  private String reportName;
  // ! 照会帳票取得_種類コード
  @JsonProperty("classification")
  private String classification;
  // ! 口座番号
  @JsonProperty("number")
  private String number;
  // ! 利用口座取得_科目コード
  @JsonProperty("type")
  private String type;
  // ! 発行日
  @JsonProperty("date_of_issue")
  private String dateOfIssue;
  // ! 帳票判別フラグ　定期預金期日のご案内:11,ご返済のご案内:12,カードローンお取引内容のお知らせ：13,金利見直し方法選択のご案内:14,外貨定期預金預入のご案内:15,外貨定期預金期日のご案内:16,契約終了通知書:17,変動金利定期預金のお知らせ:18
  @JsonProperty("report_distinction_flag")
  private String reportDistinctionFlag;

  /**
   * 帳票識別子を取得する.
   *
   * 帳票識別子を呼び出し元に返却する
   * @return 帳票識別子
   */
  @JsonProperty("report_id")
  public String getReportId() {
    return reportId;
  }

  /**
   * 帳票識別子を設定する.
   *
   * 受け取った帳票識別子を設定する
   * @param reportId 帳票識別子
   */
  @JsonProperty("report_id")
  public void setReportId(String reportId) {
    this.reportId = reportId;
  }

  public ReturnReportDto withReportId(String reportId) {
    this.reportId = reportId;
    return this;
  }

  /**
   * 口座識別子を取得する.
   *
   * 口座識別子を呼び出し元に返却する
   * @return 口座識別子
   */
  @JsonProperty("sub_account_id")
  public String getSubAccountId() {
    return subAccountId;
  }

  /**
   * 口座識別子を設定する.
   *
   * 受け取った口座識別子を設定する
   * @param subAccountId 口座識別子
   */
  @JsonProperty("sub_account_id")
  public void setSubAccountId(String subAccountId) {
    this.subAccountId = subAccountId;
  }

  public ReturnReportDto withSubAccountId(String subAccountId) {
    this.subAccountId = subAccountId;
    return this;
  }

  /**
   * 商品名を取得する.
   *
   * 商品名を呼び出し元に返却する
   * @return 商品名
   */
  @JsonProperty("product_name")
  public String getProductName() {
    return productName;
  }

  /**
   * 商品名を設定する.
   *
   * 受け取った商品名を設定する
   * @param productName 商品名
   */
  @JsonProperty("product_name")
  public void setProductName(String productName) {
    this.productName = productName;
  }

  public ReturnReportDto withProductName(String productName) {
    this.productName = productName;
    return this;
  }

  /**
   * 書類を取得する.
   *
   * 書類を呼び出し元に返却する
   * @return 書類
   */
  @JsonProperty("report_name")
  public String getReportName() {
    return reportName;
  }

  /**
   * 書類を設定する.
   *
   * 受け取った書類を設定する
   * @param reportName 書類
   */
  @JsonProperty("report_name")
  public void setReportName(String reportName) {
    this.reportName = reportName;
  }

  public ReturnReportDto withReportName(String reportName) {
    this.reportName = reportName;
    return this;
  }

  /**
   * 照会帳票取得_種類コードを取得する.
   *
   * 照会帳票取得_種類コードを呼び出し元に返却する
   * @return 照会帳票取得_種類コード
   */
  @JsonProperty("classification")
  public String getClassification() {
    return classification;
  }
  /**
   * 照会帳票取得_種類コードを設定する.
   *
   * 受け取った照会帳票取得_種類コードを設定する
   * @param classification 照会帳票取得_種類コード
   */
  @JsonProperty("classification")
  public void setClassification(String classification) {
    this.classification = classification;
  }

  public ReturnReportDto withClassification(String classification) {
    this.classification = classification;
    return this;
  }

  /**
   * 口座番号を取得する.
   *
   * 口座番号を呼び出し元に返却する
   * @return 口座番号
   */
  @JsonProperty("number")
  public String getNumber() {
    return number;
  }

  /**
   * 口座番号を設定する.
   *
   * 受け取った口座番号を設定する
   * @param number 口座番号
   */
  @JsonProperty("number")
  public void setNumber(String number) {
    this.number = number;
  }

  public ReturnReportDto withNumber(String number) {
    this.number = number;
    return this;
  }

  /**
   * 利用口座取得_科目コードを取得する.
   *
   * 利用口座取得_科目コードを呼び出し元に返却する
   * @return 利用口座取得_科目コード
   */
  @JsonProperty("type")
  public String getType() {
    return type;
  }

  /**
   * 利用口座取得_科目コードを設定する.
   *
   * 受け取った利用口座取得_科目コードを設定する
   * @param type 利用口座取得_科目コード
   */
  @JsonProperty("type")
  public void setType(String type) {
    this.type = type;
  }

  public ReturnReportDto withType(String type) {
    this.type = type;
    return this;
  }

  /**
   * 発行日を取得する.
   *
   * 発行日を呼び出し元に返却する
   * @return 発行日
   */
  @JsonProperty("date_of_issue")
  public String getDateOfIssue() {
    return dateOfIssue;
  }

  /**
   * 発行日を設定する.
   *
   * 受け取った発行日を設定する
   * @param dateOfIssue 発行日
   */
  @JsonProperty("date_of_issue")
  public void setDateOfIssue(String dateOfIssue) {
    this.dateOfIssue = dateOfIssue;
  }

  public ReturnReportDto withDateOfIssue(String dateOfIssue) {
    this.dateOfIssue = dateOfIssue;
    return this;
  }

  /**
   * 定期預金期日のご案内:11,ご返済のご案内:12,カードローンお取引内容のお知らせ：13,金利見直し方法選択のご案内:14,外貨定期預金預入のご案内:15,外貨定期預金期日のご案内:16,契約終了通知書:17,変動金利定期預金のお知らせ:18の判別フラグを取得する.
   *
   * 定期預金期日のご案内:11,ご返済のご案内:12,カードローンお取引内容のお知らせ：13,金利見直し方法選択のご案内:14,外貨定期預金預入のご案内:15,外貨定期預金期日のご案内:16,契約終了通知書:17,変動金利定期預金のお知らせ:18の判別フラグを呼び出し元に返却する
   * @return 定期預金期日のご案内:11,ご返済のご案内:12,カードローンお取引内容のお知らせ：13,金利見直し方法選択のご案内:14,外貨定期預金預入のご案内:15,外貨定期預金期日のご案内:16,契約終了通知書:17,変動金利定期預金のお知らせ:18の判別フラグ
   */
  @JsonProperty("report_distinction_flag")
  public String getReportDistinctionFlag() {
    return reportDistinctionFlag;
  }

  /**
   * 定期預金期日のご案内:11,ご返済のご案内:12,カードローンお取引内容のお知らせ：13,金利見直し方法選択のご案内:14,外貨定期預金預入のご案内:15,外貨定期預金期日のご案内:16,契約終了通知書:17,変動金利定期預金のお知らせ:18の判別フラグを設定する.
   *
   * 受け取った定期預金期日のご案内:11,ご返済のご案内:12,カードローンお取引内容のお知らせ：13,金利見直し方法選択のご案内:14,外貨定期預金預入のご案内:15,外貨定期預金期日のご案内:16,契約終了通知書:17,変動金利定期預金のお知らせ:18の判別フラグを設定する
   * @param reportDistinctionFlag 定期預金期日のご案内:11,ご返済のご案内:12,カードローンお取引内容のお知らせ：13,金利見直し方法選択のご案内:14,外貨定期預金預入のご案内:15,外貨定期預金期日のご案内:16,契約終了通知書:17,変動金利定期預金のお知らせ:18の判別フラグ
   */
  @JsonProperty("report_distinction_flag")
  public void setReportDistinctionFlag(String reportDistinctionFlag) {
    this.reportDistinctionFlag = reportDistinctionFlag;
  }

  public ReturnReportDto withReportDistinctionFlag(String reportDistinctionFlag) {
    this.reportDistinctionFlag = reportDistinctionFlag;
    return this;
  }

}