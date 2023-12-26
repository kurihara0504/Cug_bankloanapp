package com.moneyforward.cug_bankbookbase.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

/**
 * 帳票情報に紐づく契約終了通知書のリスト内部
 *
 * 業務API「契約終了通知照会」からのレスポンス
 */
@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
  "id",
  "issuedDate",
  "displayDocumentName",
  "displayProductName",
  "loanSubject",
  "loanHandlingNumber" 
})
public class ReportContractTerminationDto {

  // ! 契約終了通知書識別子
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
  //! ローン科目
  @JsonProperty("loanSubject")
  private String loanSubject;
  //! 取扱番号
  @JsonProperty("loanHandlingNumber")
  private String loanHandlingNumber;

  /**
   * 契約終了通知書識別子を取得する.
   *
   * 契約終了通知書識別子を呼び出し元に返却する
   * @return 契約終了通知書識別子
   */
  public String getId() {
    return id;
  }
  /**
   * 契約終了通知書識別子を設定する.
   *
   * 受け取った契約終了通知書識別子を設定する
   * @param id 契約終了通知書識別子
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
   * @return 通知または書類名
   */
  public String getDisplayDocumentName() {
    return displayDocumentName;
  }
  
  /**
   * 通知または書類名を設定する.
   *
   * 受け取った通知または書類名を設定する
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
   * ローン科目を取得する.
   * 
   * ローン科目を呼び出し元に返却する
   * @return ローン科目
   */
  public String getLoanSubject() {
    return loanSubject;
  }
  /**
   * ローン科目を設定する.
   * 
   * 受け取ったローン科目を設定する
   * @param loanSubject ローン科目
   */
  public void setLoanSubject(String loanSubject) {
    this.loanSubject = loanSubject;
  }
  /**
   * 取扱番号を取得する.
   * 
   * 取扱番号を呼び出し元に返却する
   * @return 取扱番号
   */
  public String getLoanHandlingNumber() {
    return loanHandlingNumber;
  }
  /**
   * 取扱番号を設定する.
   * 
   * 受け取った取扱番号を設定する
   * @param loanHandlingNumber 取扱番号
   */
  public void setLoanHandlingNumber(String loanHandlingNumber) {
    this.loanHandlingNumber = loanHandlingNumber;
  }

}