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
import com.moneyforward.cug_bankbookbase.dto.ContractTerminationDto;

/**
 * 業務API「契約終了通知書」呼出しテストクラス.
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class ContractTerminationApiLogicTest {

  /** テスト対象クラス.*/
  @InjectMocks
  private ContractTerminationApiLogic contractTerminationApiLogic;

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
    // 契約終了通知書識別子
    String contractTerminationId = "012345678901234";

    // MOCKのリターンオブジェクト
    ContractTerminationDto contractTerminationDto = new ContractTerminationDto();

    // MOCK化
    Mockito.when(bankService.request(ArgumentMatchers.any(), ArgumentMatchers.any(), ArgumentMatchers.any(), ArgumentMatchers.any())
        .registAppException(400).registAppException(404).done(ArgumentMatchers.any())).thenReturn(contractTerminationDto);

    // テスト対象のクラスにセットする
    ReflectionTestUtils.setField(contractTerminationApiLogic, "bankBookConstantValues", bankBookConstantValues);
    ReflectionTestUtils.setField(contractTerminationApiLogic, "bankService", bankService);

    // stubを呼び出す
    ContractTerminationDto retContractTerminationDto = contractTerminationApiLogic.callContractTerminationLogic(userId, accountId, contractTerminationId);

    // データ検証
    MatcherAssert.assertThat(retContractTerminationDto, is(contractTerminationDto));

  }
}