package com.moneyforward.cug_bankbookbase.logic;

import static org.hamcrest.CoreMatchers.is;

import org.hamcrest.MatcherAssert;
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

import com.moneyforward.bankbookbase.base.constance.BankBookConstantValues;
import com.moneyforward.bankbookbase.base.rest.bank.service.BankService;
import com.moneyforward.cug_bankbookbase.dto.ForeignCurrencyDueDateDto;

/**
 * 業務API「外貨定期預金期日のご案内照会」呼出しテストクラス.
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class ForeignCurrencyDueDateApiLogicTest {

  /** テスト対象クラス.*/
  @InjectMocks
  private ForeignCurrencyDueDateApiLogic foreignCurrencyDueDateApiLogic;

  /** Mock化対象クラス.*/
  @Mock(answer = Answers.RETURNS_DEEP_STUBS)
  private BankService bankService;

  @Autowired
  private BankBookConstantValues bankBookConstantValues;

  /**
   * 正常系
   */
  @Test
  public void testNormal() {

    // ユーザ識別子
    String userId = "012345678";
    // 口座識別子
    String accountId = "01234567890123456789";
    // 外貨定期預金期日案内識別子
    String foreignTimeDepositMaturityNotificationsId = "012345678901234";

    // MOCKのリターンオブジェクト
    ForeignCurrencyDueDateDto foreignCurrencyDueDateDto = new ForeignCurrencyDueDateDto();

    // MOCK化
    Mockito.when(bankService.request(ArgumentMatchers.any(), ArgumentMatchers.any(), ArgumentMatchers.any(), ArgumentMatchers.any())
        .registAppException(400).registAppException(404).done(ArgumentMatchers.any())).thenReturn(foreignCurrencyDueDateDto);

    // テスト対象のクラスにセットする
    ReflectionTestUtils.setField(foreignCurrencyDueDateApiLogic, "bankBookConstantValues", bankBookConstantValues);
    ReflectionTestUtils.setField(foreignCurrencyDueDateApiLogic, "bankService", bankService);

    // stubを呼び出す
    ForeignCurrencyDueDateDto retForeignCurrencyDueDateDto = foreignCurrencyDueDateApiLogic.callForeignCurrencyDueDateLogic(userId, accountId, foreignTimeDepositMaturityNotificationsId);

    // データ検証
    MatcherAssert.assertThat(retForeignCurrencyDueDateDto, is(foreignCurrencyDueDateDto));

  }
}