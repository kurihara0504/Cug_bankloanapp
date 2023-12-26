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
import com.moneyforward.cug_bankbookbase.dto.InformationSelectingRateDto;

/**
 * 業務API「金利見直し方法選択のご案内」呼出しテストクラス.
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class InformationSelectingRateLogicTest {

  /** テスト対象クラス.*/
  @InjectMocks
  private InformationSelectingRateLogic informationSelectingRateLogic;

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
    // 金利見直し方法選択のご案内識別子
    String reviewInterestRateId = "012345678901234";

    // MOCKのリターンオブジェクト
    InformationSelectingRateDto informationSelectingRateDto = new InformationSelectingRateDto();

    // MOCK化
    Mockito.when(bankService.request(ArgumentMatchers.any(), ArgumentMatchers.any(), ArgumentMatchers.any(), ArgumentMatchers.any())
        .registAppException(400).registAppException(404).done(ArgumentMatchers.any())).thenReturn(informationSelectingRateDto);

    // テスト対象のクラスにセットする
    ReflectionTestUtils.setField(informationSelectingRateLogic, "bankBookConstantValues", bankBookConstantValues);
    ReflectionTestUtils.setField(informationSelectingRateLogic, "bankService", bankService);

    // stubを呼び出す
    InformationSelectingRateDto retInformationSelectingRateDto = informationSelectingRateLogic.callInformationSelectingRateApi(userId, accountId, reviewInterestRateId);

    // データ検証
    MatcherAssert.assertThat(retInformationSelectingRateDto, is(informationSelectingRateDto));

  }
}
