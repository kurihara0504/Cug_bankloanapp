package com.moneyforward.cug_bankbookbase.logic;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Component;

import com.moneyforward.bankbookbase.base.constance.BankBookConstantValues;
import com.moneyforward.bankbookbase.base.constance.BankBookConstants;
import com.moneyforward.bankbookbase.base.rest.bank.service.BankService;
import com.moneyforward.cug_bankbookbase.base.constance.CugBankBookConstants;
import com.moneyforward.cug_bankbookbase.dto.ForeignCurrencyDepositOpenDto;



/**
 * 業務API「外貨定期預金預入案内（オープン式)照会」呼び出しクラス.
 *
 * 業務API「外貨定期預金預入案内（オープン式)照会」を呼び出すクラス
 */
@Component
public class ForeignCurrencyDepositOpenApiLogic {

  /** 銀行サービス基盤API.*/
  @Autowired
  private BankService bankService;

  /** 定数取得クラス.*/
  @Autowired
  private BankBookConstantValues bankBookConstantValues;

  /**
   * 業務API「外貨定期預金預入案内（オープン式)照会」を呼出す.
   *
   * 業務API「契約内容通知照会」のパラメータを構築し、API呼出しを行う。呼出し結果をDtoで返却する
   * @param userId ユーザ識別子
   * @param accountId 口座識別子
   * @param foreignTimeDepositNotificationsOpenId 外貨定期預金預入案内（オープン式）識別子
   * @return 業務API「外貨定期預金預入案内（オープン式)照会」呼出し結果
   * @throw BaseRestAppStatusException HTTPレスポンスステータスが404で返却された場合にスローする
   */
  public ForeignCurrencyDepositOpenDto callForeignCurrencyDepositOpenLogic(String userId ,String accountId, String foreignTimeDepositNotificationsOpenId) {
    // パスを生成する
    StringBuffer requestPath = new StringBuffer();
    requestPath.append(bankBookConstantValues
        .findByKeyName(BankBookConstants.ACCOUNT_PATH));
    requestPath.append(accountId);
    requestPath.append(bankBookConstantValues
        .findByKeyName(CugBankBookConstants.FOREIGN_CURRENCY_DEPOSIT_OPEN_API_PATH));
    requestPath.append(foreignTimeDepositNotificationsOpenId);

    //　リクエストの変数に格納
    int status400 = Integer.parseInt(
        bankBookConstantValues.findByKeyName(BankBookConstants.STATUS_400));
    int status404 = Integer.parseInt(
        bankBookConstantValues.findByKeyName(BankBookConstants.STATUS_404));

    // 業務API「外貨定期預金預入案内（オープン式)照会」を呼び出す
    return bankService.request(
        bankBookConstantValues.findByKeyName(CugBankBookConstants.FOREIGN_CURRENCY_DEPOSIT_OPEN_API_CODE),
        HttpMethod.GET,
        userId,
        requestPath.toString())
        .registAppException(status400)
        .registAppException(status404)
        .done(ForeignCurrencyDepositOpenDto.class);
  }
}