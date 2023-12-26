
package com.moneyforward.cug_bankbookbase.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import com.moneyforward.cug_bankbookbase.dto.AccountsListDto;

/**
 * 口座情報の詳細(業務APIレスポンス).
 * 
 * DTO「口座情報一覧(業務APIレスポンス)」のリスト内部
 */

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
   "accountId",
   "accountName",
   "accountKanjiName",
   "bankCode",
   "branchNumber",
   "accountType",
   "accountNumber",
   "branchName",
   "branchKanjiName",
   "currencyCode",
   "additionalAccountType",
   "cifNumber",
   "loanHandlingNumber",
   "report"
})
public class AccountsListDto {
 
 // ! 口座の識別子
   @JsonProperty("accountId")
   private String accountId;
 // ! 口座名義人（カナ）
   @JsonProperty("accountName")
   private String accountName;
 // ! 口座名義人（漢字）
   @JsonProperty("accountKanjiName")
   private String accountKanjiName;
 // ! 金融機関コード
   @JsonProperty("bankCode")
   private String bankCode;
 // ! 店番
   @JsonProperty("branchNumber")
   private String branchNumber;
 // ! 科目コード
   @JsonProperty("accountType")
   private String accountType;
 // ! 口座番号
   @JsonProperty("accountNumber")
   private String accountNumber;
 // ! 店名（カナ）
   @JsonProperty("branchName")
   private String branchName;
 // ! 店名（漢字）
   @JsonProperty("branchKanjiName")
   private String branchKanjiName;
 // ! 通貨コード
   @JsonProperty("currencyCode")
   private String currencyCode;
 // ! 預金種目追加情報
   @JsonProperty("additionalAccountType")
   private String additionalAccountType;
 // ! CIF番号
   @JsonProperty("cifNumber")
   private String cifNumber;
 // ! ローン取り扱い番号
   @JsonProperty("loanHandlingNumber")
   private String loanHandlingNumber;
 // ! 帳票
   @JsonProperty("report")
   private CugReportDto report;
   
   /**
    * 口座の識別子を取得する.
    * 
    * 口座の識別子を呼び出し元に返却する
    * @return 口座の識別子
    */
   @JsonProperty("accountId")
   public String getAccountId() {
       return accountId;
   }

   /**
    * 口座の識別子を設定する.
    * 
    * 受け取った口座の識別子を設定する
    * @param accountId 口座の識別子
    */
   @JsonProperty("accountId")
   public void setAccountId(String accountId) {
       this.accountId = accountId;
   }

   /**
    * 口座名義人（カナ）を取得する.
    * 
    * 口座名義人（カナ）を呼び出し元に返却する
    * @return 口座名義人（カナ）
    */
   @JsonProperty("accountName")
   public String getAccountName() {
       return accountName;
   }

   /**
    * 口座名義人（カナ）を設定する.
    * 
    * 受け取った口座名義人（カナ）を設定する
    * @param accountName 口座名義人（カナ）
    */
   @JsonProperty("accountName")
   public void setAccountName(String accountName) {
       this.accountName = accountName;
   }

   public AccountsListDto withAccountName(String accountName) {
       this.accountName = accountName;
       return this;
   }

   /**
    * 口座名義人(漢字)を取得する.
    * 
    * 口座名義人(漢字)を呼び出し元に返却する
    * @return 口座名義人(漢字)
    */
   @JsonProperty("accountKanjiName")
   public String getAccountKanjiName() {
       return accountKanjiName;
   }

   /**
    * 口座名義人(漢字)を設定する.
    * 
    * 受け取った口座名義人(漢字)を設定する
    * @param accountKanjiName 口座名義人(漢字)
    */
   @JsonProperty("accountKanjiName")
   public void setAccountKanjiName(String accountKanjiName) {
       this.accountKanjiName = accountKanjiName;
   }

   /**
    * 金融機関コードを取得する.
    * 
    * 金融機関コードを呼び出し元に返却する
    * @return 金融機関コード
    */
   @JsonProperty("bankCode")
   public String getBankCode() {
       return bankCode;
   }

   /**
    * 金融機関コードを設定する.
    * 
    * 受け取った金融機関コードを設定する
    * @param bankCode 金融機関コード
    */
   @JsonProperty("bankCode")
   public void setBankCode(String bankCode) {
       this.bankCode = bankCode;
   }

   /**
    * 店番を取得する.
    * 
    * 店番を呼び出し元に返却する
    * @return 店番
    */
   @JsonProperty("branchNumber")
   public String getBranchNumber() {
       return branchNumber;
   }

   /**
    * 店番を設定する.
    * 
    * 受け取った店番を設定する
    * @param bankCode 店番
    */
   @JsonProperty("branchNumber")
   public void setBranchNumber(String branchNumber) {
       this.branchNumber = branchNumber;
   }

   /**
    * 科目コードを取得する.
    * 
    * 科目コードを呼び出し元に返却する
    * @return 科目コード
    */
   @JsonProperty("accountType")
   public String getAccountType() {
       return accountType;
   }

   /**
    * 科目コードを設定する.
    * 
    * 受け取った科目コードを設定する
    * @param accountType 科目コード
    */
   @JsonProperty("accountType")
   public void setAccountType(String accountType) {
       this.accountType = accountType;
   }

   /**
    * 口座番号を取得する.
    *
    * 口座番号を呼び出し元に返却する
    * @return 口座番号
    */
   @JsonProperty("accountNumber")
   public String getAccountNumber() {
         return accountNumber;
   }

   /**
    * 口座番号を設定する.
    *
    * 受け取った口座番号を設定する
    * @param accountNumber 口座番号
    */
   @JsonProperty("accountNumber")
   public void setAccountNumber(String accountNumber) {
       this.accountNumber = accountNumber;
   }

   /**
    * 店名(カナ)を取得する.
    * 
    * 店名(カナ)を呼び出し元に返却する
    * @return 店名(カナ)
    */
   @JsonProperty("branchName")
   public String getBranchName() {
       return branchName;
   }

   /**
    * 店名(カナ)を設定する.
    * 
    * 受け取った店名(カナ)を設定する
    * @param branchName 店名(カナ)
    */
   @JsonProperty("branchName")
   public void setBranchName(String branchName) {
       this.branchName = branchName;
   }

   /**
    * 店名(漢字)を取得する.
    * 
    * 店名(漢字)を呼び出し元に返却する
    * @return 店名(漢字)
    */
   @JsonProperty("branchKanjiName")
   public String getBranchKanjiName() {
       return branchKanjiName;
   }

   /**
    * 店名(漢字)を設定する.
    * 
    * 受け取った店名(漢字)を設定する
    * @param branchKanjiName 店名(漢字)
    */
   @JsonProperty("branchKanjiName")
   public void setBranchKanjiName(String branchKanjiName) {
       this.branchKanjiName = branchKanjiName;
   }

   /**
    * 通貨コードを取得する.
    * 
    * 通貨コードを呼び出し元に返却する
    * @return 通貨コード
    */
   @JsonProperty("currencyCode")
   public String getCurrencyCode() {
       return currencyCode;
   }

   /**
    * 通貨コードを設定する.
    * 
    * 受け取った通貨コードを設定する
    * @param currencyCode 通貨コード
    */
   @JsonProperty("currencyCode")
   public void setCurrencyCode(String currencyCode) {
       this.currencyCode = currencyCode;
   }

   /**
    * 預金種目追加情報を取得する.
    * 
    * 預金種目追加情報を呼び出し元に返却する
    * @return 預金種目追加情報
    */
   @JsonProperty("additionalAccountType")
   public String getAdditionalAccountType() {
       return additionalAccountType;
   }

   /**
    * 預金種目追加情報を設定する.
    * 
    * 受け取った預金種目追加情報を設定する
    * @param additionalAccountType 預金種目追加情報
    */
   @JsonProperty("additionalAccountType")
   public void setAdditionalAccountType(String additionalAccountType) {
       this.additionalAccountType = additionalAccountType;
   }

   /**
    * CIF番号を取得する.
    * 
    * CIF番号を呼び出し元に返却する
    * @return CIF番号
    */
   @JsonProperty("cifNumber")
   public String getCifNumber() {
       return cifNumber;
   }

   /**
    * CIF番号を設定する.
    * 
    * 受け取ったCIF番号を設定する
    * @param cifNumber CIF番号
    */
   @JsonProperty("cifNumber")
   public void setCifNumber(String cifNumber) {
       this.cifNumber = cifNumber;
   }

   /**
    * ローン取り扱い番号を取得する.
    *
    * ローン取り扱い番号を呼び出し元に返却する
    * @return ローン取り扱い番号
    */
   @JsonProperty("loanHandlingNumber")
   public String getLoanHandlingNumber() {
       return loanHandlingNumber;
   }

   /**
    * ローン取り扱い番号を設定する.
    *
    * 受け取ったローン取り扱い番号を設定する
    * @param loanHandlingNumber ローン取り扱い番号
    */
   @JsonProperty("loanHandlingNumber")
   public void setLoanHandlingNumber(String loanHandlingNumber) {
       this.loanHandlingNumber = loanHandlingNumber;
   }

   /**
    * 帳票を取得する.
    * 
    * 帳票を呼び出し元に返却する
    * @return 帳票
    */
   @JsonProperty("report")
   public CugReportDto getReport() {
       return report;
   }

   /**
    * 帳票を設定する.
    * 
    * 受け取った帳票を設定する
    * @param report 帳票
    */
   @JsonProperty("report")
   public void setReport(CugReportDto report) {
       this.report = report;
   }

}