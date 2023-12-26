package com.moneyforward.cug_bankbookbase.logic;

import static org.hamcrest.CoreMatchers.is;
import org.hamcrest.MatcherAssert;

import com.moneyforward.bankbookbase.base.constance.BankBookConstantValues;
import com.moneyforward.bankbookbase.base.rest.bank.service.BankService;
import com.moneyforward.cug_bankbookbase.dto.AccountListDto;
import com.moneyforward.cug_bankbookbase.logic.CallGetReportInfoLogic;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Answers;
import org.mockito.ArgumentMatchers;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.util.ReflectionTestUtils;

@RunWith(SpringRunner.class)
@SpringBootTest
public class CallGetReportInfoLogicTest {

	@InjectMocks
	private CallGetReportInfoLogic callGetReportInfoLogic;

	@Mock(answer = Answers.RETURNS_DEEP_STUBS)
	private BankService bankService;

	@Autowired
	private BankBookConstantValues bankBookConstantValues;

	/**
	 * 正常テスト
	 */
	@Test
	public void testRepaymentScheduleLogic() {

		// MOCKのリターンオブジェクト
		AccountListDto accountListDto = new AccountListDto();

		// MOCK化
		Mockito.when(bankService
				.request(ArgumentMatchers.any(), ArgumentMatchers.any(), ArgumentMatchers.any(), ArgumentMatchers.any())
				.registAppException(404).done(ArgumentMatchers.any()))
				.thenReturn(accountListDto);

		// テスト対象のクラスにセットする
		ReflectionTestUtils.setField(callGetReportInfoLogic, "bankBookConstantValues",
				bankBookConstantValues);
		ReflectionTestUtils.setField(callGetReportInfoLogic, "bankService", bankService);

		// stubを呼び出す
		AccountListDto retAccountListDto = callGetReportInfoLogic.doLogic("user");

		// データ検証
		MatcherAssert.assertThat(retAccountListDto, is(accountListDto));

	}

}
