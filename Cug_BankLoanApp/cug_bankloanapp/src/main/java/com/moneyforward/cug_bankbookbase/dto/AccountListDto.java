
package com.moneyforward.cug_bankbookbase.dto;

import java.util.List;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

/**
 * 口座情報一覧（業務APIレスポンス）.
 * 
 * API「口座情報一覧詳細」の業務APIからのレスポンス
 */

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
    "accountsList"
})
public class AccountListDto{

	// ! 口座情報一覧のリスト
    @JsonProperty("accountsList")
    private List<AccountsListDto> accountsList = null;
    
    /**
     * 口座情報一覧を取得する.
     *
     * 口座情報一覧を呼び出し元に返却する
     * @return 口座情報一覧
     */
    @JsonProperty("accountsList")
    public List<AccountsListDto> getAccountsList() {
        return accountsList;
    }

    /**
     * 口座情報一覧を設定する.
     * 
     * 受け取った口座情報一覧を設定する
     * @param accountsList 口座情報一覧
     */
    @JsonProperty("accountsList")
    public void setAccountsList(List<AccountsListDto> accountsList) {
        this.accountsList = accountsList;
    }


}
