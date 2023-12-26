package com.moneyforward.cug_bankbookbase.logic;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Component;

import com.moneyforward.bankbookbase.base.constance.BankBookConstantValues;
import com.moneyforward.bankbookbase.base.constance.BankBookConstants;
import com.moneyforward.bankbookbase.base.rest.bank.service.BankService;
import com.moneyforward.cug_bankbookbase.base.constance.CugBankBookConstants;
import com.moneyforward.cug_bankbookbase.dto.CardLoanTransactionDto;

/**
 * 業務API「カードローン取引内容通知照会」呼び出しクラス.
 *
 * 業務API「カードローン取引内容通知照会」を呼び出すクラス
 */
@Component
public class CardLoanTransactionApiLogic {

  /** 銀行サービス基盤API.*/
  @Autowired
  private BankService bankService;

  /** 定数取得クラス.*/
  @Autowired
  private BankBookConstantValues bankBookConstantValues;

  /**
   * 業務API「カードローン取引内容通知照会」を呼出す.
   *
   * 業務API「カードローン取引内容通知照会」のパラメータを構築し、API呼出しを行う。呼出し結果をDtoで返却する
   * @param userId ユーザ識別子
   * @param accountId 口座識別子
   * @param noticeCardLoanTransactionId カードローン取引内容通知識別子
   * @return 業務API「カードローン取引内容通知照会」呼出し結果
   * @throw BaseRestAppStatusException HTTPレスポンスステータスが404で返却された場合にスローする
   */
  public CardLoanTransactionDto callCardLoanTransactionLogic(String userId ,String accountId, String noticeCardLoanTransactionId) {
    // パスを生成する
    StringBuffer requestPath = new StringBuffer();
    requestPath.append(bankBookConstantValues
        .findByKeyName(BankBookConstants.ACCOUNT_PATH));
    requestPath.append(accountId);
    requestPath.append(bankBookConstantValues
        .findByKeyName(CugBankBookConstants.CARD_LOAN_TRANSACTION_API_PATH));
    requestPath.append(noticeCardLoanTransactionId);

    //　リクエストの変数に格納
    int status400 = Integer.parseInt(
        bankBookConstantValues.findByKeyName(BankBookConstants.STATUS_400));
    int status404 = Integer.parseInt(
        bankBookConstantValues.findByKeyName(BankBookConstants.STATUS_404));

    // 業務API「カードローン取引内容通知照会」を呼び出す
    return bankService.request(
        bankBookConstantValues.findByKeyName(CugBankBookConstants.CARD_LOAN_TRANSACTION_API_CODE),
        HttpMethod.GET,
        userId,
        requestPath.toString())
        .registAppException(status400)
        .registAppException(status404)
        .done(CardLoanTransactionDto.class);
  }
}