package com.moneyforward.cug_bankbookbase.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

/**
 * 帳票情報に紐づく変動金利定期預金のお知らせのリスト内部
 *
 *　業務API「変動金利定期預金通知照会」からのレスポンス
 */
@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
  "id",
  "issuedDate",
  "displayDocumentName",
  "displayProductName"
})
public class ReportNoticeFloatingRateDto {

  // ! 変動金利定期預金のお知らせ識別子
  @JsonProperty("id")
  private String id;
  // ! 発行日
  @JsonProperty("issuedDate")
  private String issuedDate;
  //! 通知または書類名
  @JsonProperty("displayDocumentName")
  private String displayDocumentName;
  //! 商品名
  @JsonProperty("displayProductName")
  private String displayProductName;

  /**
   * 変動金利定期預金のお知らせ識別子を取得する.
   *
   * 変動金利定期預金のお知らせ識別子を呼び出し元に返却する
   * @return 変動金利定期預金のお知らせ識別子
   */
  public String getId() {
    return id;
  }
  /**
   * 変動金利定期預金のお知らせ識別子を設定する.
   *
   * 受け取った変動金利定期預金のお知らせ識別子を設定する
   * @param id 変動金利定期預金のお知らせ識別子
   */
  public void setId(String id) {
    this.id = id;
  }
  /**
   * 発行日を取得する.
   *
   * 発行日を呼び出し元に返却する
   * @return 発行日
   */
  public String getIssuedDate() {
    return issuedDate;
  }
  /**
   * 発行日を設定する.
   *
   * 受け取った発行日を設定する
   * @param issuedDate 発行日
   */
  public void setIssuedDate(String issuedDate) {
    this.issuedDate = issuedDate;
  }
  
  /**
   * 通知または書類名を取得する.
   *
   * 通知または書類名を呼び出し元に返却する
   * @return 発行日
   */
  public String getDisplayDocumentName() {
    return displayDocumentName;
  }
  
  /**
   * 通知または書類名を設定する.
   *
   * 受け取った通知または書類名を設定する
   * @param issuedDate 発行日
   */
  public void setDisplayDocumentName(String displayDocumentName) {
    this.displayDocumentName = displayDocumentName;
  }
  
  /**
   * 商品名を取得する.
   *
   * 商品名を呼び出し元に返却する
   * @return 発行日
   */
  public String getDisplayProductName() {
    return displayProductName;
  }
  
  /**
   * 商品名を設定する.
   *
   * 受け取った商品名を設定する
   * @param issuedDate 発行日
   */
  public void setDisplayProductName(String displayProductName) {
    this.displayProductName = displayProductName;
  }

}