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
import com.moneyforward.cug_bankbookbase.dto.CardLoanTransactionDto;

/**
 * 業務API「カードローン取引内容通知照会」呼出しテストクラス.
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class CardLoanTransactionApiLogicTest {

  /** テスト対象クラス.*/
  @InjectMocks
  private CardLoanTransactionApiLogic cardLoanTransactionApiLogic;

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
    // カードローン取引内容通知識別子
    String noticeCardLoanTransactionId = "012345678901234";

    // MOCKのリターンオブジェクト
    CardLoanTransactionDto cardLoanTransactionDto = new CardLoanTransactionDto();

    // MOCK化
    Mockito.when(bankService.request(ArgumentMatchers.any(), ArgumentMatchers.any(), ArgumentMatchers.any(), ArgumentMatchers.any())
        .registAppException(400).registAppException(404).done(ArgumentMatchers.any())).thenReturn(cardLoanTransactionDto);

    // テスト対象のクラスにセットする
    ReflectionTestUtils.setField(cardLoanTransactionApiLogic, "bankBookConstantValues", bankBookConstantValues);
    ReflectionTestUtils.setField(cardLoanTransactionApiLogic, "bankService", bankService);

    // stubを呼び出す
    CardLoanTransactionDto retCardLoanTransactionDto = cardLoanTransactionApiLogic.callCardLoanTransactionLogic(userId, accountId, noticeCardLoanTransactionId);

    // データ検証
    MatcherAssert.assertThat(retCardLoanTransactionDto, is(cardLoanTransactionDto));

  }
}