
package com.moneyforward.cug_bankbookbase.dto;

import java.util.List;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import com.moneyforward.bankbookbase.dto.ReturnReportsDto;

/**
 * 帳票情報一覧(内部API).
 * 
 * API「認証要求」の内部APIからのレスポンス
 */

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
    "reports"
})
public class CugReturnReportsDto extends ReturnReportsDto{

  // ! 帳票情報一覧のリスト
    @JsonProperty("reports")
    private List<ReturnReportDto> reports = null;

    /**
     * 帳票情報一覧を取得する.
     * 
     * 帳票情報一覧を呼び出し元に返却する
     * @return 帳票情報一覧
     */
    @JsonProperty("reports")
    public List<ReturnReportDto> getReports() {
        return reports;
    }

    /**
     * 帳票情報一覧を設定する.
     * 
     * 受け取った帳票情報一覧を設定する
     * @param reports 帳票情報一覧
     */
    @JsonProperty("reports")
    public void setReports(List<ReturnReportDto> reports) {
        this.reports = reports;
    }

}
