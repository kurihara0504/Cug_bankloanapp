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
import com.moneyforward.cug_bankbookbase.dto.InformationDepositDto;

@RunWith(SpringRunner.class)
@SpringBootTest
public class InformationDepositLogicTest {

  @InjectMocks
  private InformationDepositLogic informationDepositLogic;
  
  @Mock(answer = Answers.RETURNS_DEEP_STUBS)
  private BankService bankService;
  
  @Autowired
  private BankBookConstantValues bankBookConstantValues;
  
  /**
   * 正常テスト
   */
  @Test
  public void informationDepositLogicTest() {

    // MOCKのリターンオブジェクト
    InformationDepositDto informationDepositDto = new InformationDepositDto();
    informationDepositDto.setAccountNumber("123");

    // MOCK化
    Mockito.when(bankService.request(ArgumentMatchers.any(), ArgumentMatchers.any(), ArgumentMatchers.any(), ArgumentMatchers.any())
        .registAppException(400).registAppException(404).done(ArgumentMatchers.any())).thenReturn(informationDepositDto);

    // テスト対象のクラスにセットする
    ReflectionTestUtils.setField(informationDepositLogic, "bankBookConstantValues",
        bankBookConstantValues);
    ReflectionTestUtils.setField(informationDepositLogic, "bankService", bankService);

    // stubを呼び出す
    InformationDepositDto retInformationDepositDto = informationDepositLogic.CallInformationDepositApi("user", "123123", "aaaaa");

    // データ検証
    MatcherAssert.assertThat(retInformationDepositDto, is(informationDepositDto));

  }
}
