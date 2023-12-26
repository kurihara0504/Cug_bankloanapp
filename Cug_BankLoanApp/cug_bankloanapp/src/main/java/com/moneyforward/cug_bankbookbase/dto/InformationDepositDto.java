package com.moneyforward.cug_bankbookbase.dto;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

/**
 * 定期預金満期案内の詳細.
 *
 * 業務API「定期預金満期案内」からのレスポンス
 */
@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
  "maturityNotificationId",
  "accountNumber",
  "applicantName",
  "issuedDate",
  "branchName",
  "branchPhoneNumber",
  "information",
  "informationLine1",
  "informationLine2",
  "informationLine3",
  "informationLine4",
  "informationLine5",
  "list"
})
public class InformationDepositDto {

  /** 定期預金満期案内識別子 */
  @JsonProperty("maturityNotificationId")
  private String maturityNotificationId;
  
  /** 口座番号 */
  @JsonProperty("accountNumber")
  private String accountNumber;
  
  /** 名義人 */
  @JsonProperty("applicantName")
  private String applicantName;
  
  /** 作成日 */
  @JsonProperty("issuedDate")
  private String issuedDate;
  
  /** 取扱店名 */
  @JsonProperty("branchName")
  private String branchName;
  
  /** 取扱店電話番号 */
  @JsonProperty("branchPhoneNumber")
  private String branchPhoneNumber;

  /** お知らせ */
  @JsonProperty("information")
  private String information;
  
  /** お知らせ1 */
  @JsonProperty("informationLine1")
  private String informationLine1;
  
  /** お知らせ2 */
  @JsonProperty("informationLine2")
  private String informationLine2;
  
  /** お知らせ3 */
  @JsonProperty("informationLine3")
  private String informationLine3;
  
  /** お知らせ4 */
  @JsonProperty("informationLine4")
  private String informationLine4;
  
  /** お知らせ5 */
  @JsonProperty("informationLine5")
  private String informationLine5;
  
  /** 明細一覧 */
  @JsonProperty("list")
  private List<InformationDepositDetailDto> list;

  /**
   * 定期預金満期案内識別子を取得する.
   * 
   * 定期預金満期案内識別子を呼び出し元に返却する
   * @return 定期預金満期案内識別子
   */
  public String getMaturityNotificationId() {
    return maturityNotificationId;
  }

  /**
   * 定期預金満期案内識別子を設定する.
   *
   * 受け取った定期預金満期案内識別子を設定する 
   * @param maturityNotificationId 定期預金満期案内識別子
   */
  public void setMaturityNotificationId(String maturityNotificationId) {
    this.maturityNotificationId = maturityNotificationId;
  }

  /**
   * 口座番号を取得する.
   * 
   * 口座番号を呼び出し元に返却する
   * @return 口座番号
   */
  public String getAccountNumber() {
    return accountNumber;
  }

  /**
   * 口座番号を設定する.
   * 
   * 受け取った口座番号を設定する
   * @param accountNumber 口座番号
   */
  public void setAccountNumber(String accountNumber) {
    this.accountNumber = accountNumber;
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
   * @param issuedDate 作成日
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
   * @param branchName 取扱店名
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
   * お知らせを取得する.
   * 
   * お知らせを呼び出し元に返却する
   * @return お知らせ
   */
  public String getInformation() {
    return information;
  }

  /**
   * お知らせを設定する.
   * 
   * 受け取ったお知らせを設定する
   * @param information お知らせ
   */
  public void setInformation(String information) {
    this.information = information;
  }

  /**
   * お知らせ1を取得する.
   * 
   * お知らせ1を呼び出し元に返却する
   * @return お知らせ1
   */
  public String getInformationLine1() {
    return informationLine1;
  }

  /**
   * お知らせ1を設定する.
   * 
   * 受け取ったお知らせ1を設定する
   * @param informationLine1 お知らせ1
   */
  public void setInformationLine1(String informationLine1) {
    this.informationLine1 = informationLine1;
  }

  /**
   * お知らせ2を取得する.
   * 
   * お知らせ2を呼び出し元に返却する
   * @return お知らせ2
   */
  public String getInformationLine2() {
    return informationLine2;
  }

  /**
   * お知らせ2を設定する.
   * 
   * 受け取ったお知らせ2を設定する
   * @param informationLine2 お知らせ2
   */
  public void setInformationLine2(String informationLine2) {
    this.informationLine2 = informationLine2;
  }

  /**
   * お知らせ3を取得する.
   * 
   * お知らせ3を呼び出し元に返却する
   * @return お知らせ3
   */
  public String getInformationLine3() {
    return informationLine3;
  }

  /**
   * お知らせ3を設定する.
   * 
   * 受け取ったお知らせ3を設定する
   * @param informationLine3 お知らせ3
   */
  public void setInformationLine3(String informationLine3) {
    this.informationLine3 = informationLine3;
  }

  /**
   * お知らせ4を取得する.
   * 
   * お知らせ4を呼び出し元に返却する
   * @return お知らせ4
   */
  public String getInformationLine4() {
    return informationLine4;
  }

  /**
   * お知らせ4を設定する.
   * 
   * 受け取ったお知らせ4を設定する
   * @param informationLine4 お知らせ4
   */
  public void setInformationLine4(String informationLine4) {
    this.informationLine4 = informationLine4;
  }

  /**
   * お知らせ5を取得する.
   * 
   * お知らせ5を呼び出し元に返却する
   * @return お知らせ5
   */
  public String getInformationLine5() {
    return informationLine5;
  }

  /**
   * お知らせ5を設定する.
   * 
   * 受け取ったお知らせ5を設定する
   * @param informationLine5 お知らせ5
   */
  public void setInformationLine5(String informationLine5) {
    this.informationLine5 = informationLine5;
  }

  /**
   * 明細一覧を取得する.
   * 
   * 明細一覧を呼び出し元に返却する
   * @return 明細一覧
   */
  public List<InformationDepositDetailDto> getList() {
    return list;
  }

  /**
   * 明細一覧を設定する.
   * 
   * 受け取った明細一覧を設定する
   * @param list 明細一覧
   */
  public void setList(List<InformationDepositDetailDto> list) {
    this.list = list;
  }

  

}
