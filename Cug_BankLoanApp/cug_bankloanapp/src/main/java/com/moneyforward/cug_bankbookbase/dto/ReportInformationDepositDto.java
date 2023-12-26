package com.moneyforward.cug_bankbookbase.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

/**
 * 帳票情報に紐づく定期預金期日のご案内のリスト内部
 *
 *　業務API「定期預金満期案内照会」からのレスポンス
 */
@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
  "id",
  "issuedDate"
})
public class ReportInformationDepositDto {

  // ! 定期預金期日のご案内識別子
  @JsonProperty("id")
  private String id;
  // ! 発行日
  @JsonProperty("issuedDate")
  private String issuedDate;

  /**
   * 定期預金期日のご案内識別子を取得する.
   *
   * 定期預金期日のご案内識別子を呼び出し元に返却する
   * @return 定期預金期日のご案内識別子
   */
  public String getId() {
    return id;
  }
  /**
   * 定期預金期日のご案内識別子を設定する.
   *
   * 受け取った定期預金期日のご案内識別子を設定する
   * @param id 定期預金期日のご案内識別子
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

}