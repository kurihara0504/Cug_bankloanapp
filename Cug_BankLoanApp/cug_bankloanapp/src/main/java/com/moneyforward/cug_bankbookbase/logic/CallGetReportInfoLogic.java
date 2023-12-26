package com.moneyforward.cug_bankbookbase.logic;

import com.moneyforward.bankbookbase.base.constance.BankBookConstantValues;
import com.moneyforward.bankbookbase.base.constance.BankBookConstants;
import com.moneyforward.bankbookbase.base.rest.bank.service.BankService;
import com.moneyforward.cug_bankbookbase.dto.AccountListDto;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Component;

/**
 * 口座帳票一覧照会API呼出しクラス.
 * 
 * 口座帳票一覧照会APIを呼び出すクラス
 */
@Component
public class CallGetReportInfoLogic {

	/** 銀行サービス基盤API. */
	@Autowired
	private BankService bankService;

	/** 定数取得クラス. */
	@Autowired
	private BankBookConstantValues bankBookConstantValues;

	/**
	 * 口座帳票一覧照会APIを呼び出す.
	 * 
	 * 口座帳票一覧照会APIのパラメータを構築し、API呼出しを行う。呼出し結果をDtoで返却する
	 * 
	 * @param userId ユーザ識別子
	 * @return 口座帳票一覧照会API呼出し結果
	 * @throw BaseRestAppStatusException HTTPレスポンスステータスが404で返却された場合にスローする
	 */
	public AccountListDto doLogic(String userId) {

		// パスを生成する
		StringBuffer requestPath = new StringBuffer();
		requestPath.append(bankBookConstantValues.findByKeyName(BankBookConstants.REPORTS_INFO_API_PATH));

		// リクエストの変数に格納
		int status404 = Integer.parseInt(bankBookConstantValues.findByKeyName(BankBookConstants.STATUS_404));

		// 取得(一覧)APIを呼び出す
		AccountListDto accountList = bankService
				.request(bankBookConstantValues.findByKeyName(BankBookConstants.REPORTS_INFO_API_NAME), HttpMethod.GET,
						userId, requestPath.toString())
				.registAppException(status404).done(AccountListDto.class);

		return accountList;
	}

}
