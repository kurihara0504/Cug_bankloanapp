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
import com.moneyforward.cug_bankbookbase.dto.NoticeFloatingRateDto;

/**
 * 業務API「変動金利定期預金通知照会」呼出しテストクラス.
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class NoticeFloatingRateApiLogicTest {

  /** テスト対象クラス.*/
  @InjectMocks
  private NoticeFloatingRateApiLogic noticeFloatingRateApiLogic;

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
    // 変動金利定期預金通知照会識別子
    String noticeFloatingInterestRateTimeDepositId = "012345678901234";

    // MOCKのリターンオブジェクト
    NoticeFloatingRateDto noticeFloatingRateDto = new NoticeFloatingRateDto();

    // MOCK化
    Mockito.when(bankService.request(ArgumentMatchers.any(), ArgumentMatchers.any(), ArgumentMatchers.any(), ArgumentMatchers.any())
        .registAppException(400).registAppException(404).done(ArgumentMatchers.any())).thenReturn(noticeFloatingRateDto);

    // テスト対象のクラスにセットする
    ReflectionTestUtils.setField(noticeFloatingRateApiLogic, "bankBookConstantValues", bankBookConstantValues);
    ReflectionTestUtils.setField(noticeFloatingRateApiLogic, "bankService", bankService);

    // stubを呼び出す
    NoticeFloatingRateDto retNoticeFloatingRateDto = noticeFloatingRateApiLogic.callNoticeFloatingRateLogic(userId, accountId, noticeFloatingInterestRateTimeDepositId);

    // データ検証
    MatcherAssert.assertThat(retNoticeFloatingRateDto, is(noticeFloatingRateDto));

  }
}