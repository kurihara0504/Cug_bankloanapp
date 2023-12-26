package com.moneyforward.cug_bankbookbase.service;

import static org.hamcrest.CoreMatchers.instanceOf;
import static org.hamcrest.CoreMatchers.is;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.hamcrest.MatcherAssert;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import org.junit.runner.RunWith;
import org.mockito.ArgumentMatchers;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.core.io.ResourceLoader;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.util.ReflectionTestUtils;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.moneyforward.cug_bankbookbase.dto.ContractTerminationDto;
import com.moneyforward.cug_bankbookbase.logic.ContractTerminationApiLogic;
import com.moneyforward.bankbookbase.repository.ReportDefinitionRepository;
import com.moneyforward.bankbookbase.repository.StoredDataHistoryRepository;
import com.moneyforward.bankbookbase.repository.entity.ReportDefinition;
import com.moneyforward.bankbookbase.service.PdfService;

/**
 * 契約終了通知書取得サービス　テストクラス.
 */
@ContextConfiguration
@RunWith(SpringRunner.class)
@SpringBootTest
public class ContractTerminationServiceTest {

  /** テスト対象クラス. */
  @Autowired
  private ContractTerminationService contractTerminationService;

  /** テスト実行時の共通処理の設定. */
  @Rule
  public ExpectedException exception = ExpectedException.none();

  /** Mock化対象クラス. */
  @Mock
  private ContractTerminationApiLogic contractTerminationApiLogic;
  @Mock
  private PdfService pdfService;
  @Mock
  private StoredDataHistoryRepository storedDataHistoryRepository;
  @Mock
  private ReportDefinitionRepository reportDefinitionRepository;
  @Mock
  private ResourceLoader resource;

  /** 初期処理. */
  @Before
  public void before() {
    ReflectionTestUtils.setField(contractTerminationService, "contractTerminationApiLogic", contractTerminationApiLogic);
    ReflectionTestUtils.setField(contractTerminationService, "pdfService", pdfService);
  }

  /** 最後処理. */
  @After
  public void after() {
    // 初期化
    Mockito.reset(contractTerminationApiLogic);
    Mockito.reset(pdfService);
    Mockito.reset(storedDataHistoryRepository);
    Mockito.reset(reportDefinitionRepository);
    Mockito.reset(resource);
  }

  // 契約終了通知書　必須項目：有 非必須項目:有
  private String contractTerminationDtoJson1 = "{\"endLoanContractId\":\"rid0000000000000001\",\"applicantName\":\"テスト　太郎\",\"contractDate\":\"2022-01-01\",\"branchName\":\"テスト支店\",\"loanHandlingNumber\":\"123456\",\"contractSubstance\":\"テスト融資\",\"contractAmount\":\"1234567890\",\"contractEndDate\":\"2023-01-02\",\"issuedDate\":\"2023-01-02\",\"branchPhoneNumber\":\"0000-1111-222\"}";
  // 契約終了通知書　必須項目：有 非必須項目:無（null）
  private String contractTerminationDtoJson2 = "{\"endLoanContractId\":\"rid0000000000000002\",\"applicantName\":null,\"contractDate\":null,\"branchName\":null,\"loanHandlingNumber\":null,\"contractSubstance\":null,\"contractAmount\":null,\"contractEndDate\":null,\"issuedDate\":\"2023-01-02\",\"branchPhoneNumber\":null}";
  // 契約終了通知書　必須項目：有 非必須項目:無（空文字）
  private String contractTerminationDtoJson3 = "{\"endLoanContractId\":\"rid0000000000000003\",\"applicantName\":\"\",\"contractDate\":\"\",\"branchName\":\"\",\"loanHandlingNumber\":\"\",\"contractSubstance\":\"\",\"contractAmount\":\"\",\"contractEndDate\":\"\",\"issuedDate\":\"2023-01-02\",\"branchPhoneNumber\":\"\"}";



  /**
   * 正常系　引数：最大桁　必須項目：有　非必須項目：有
   */
  @Test
  public void testNormalMax() throws Exception {

    List<ReportDefinition> reportDefinitionList = new ArrayList<ReportDefinition>();
    ReportDefinition reportDefinition = new ReportDefinition();

    reportDefinition.setId(2L);
    reportDefinition.setReportFormatFilePath("fullpath");
    reportDefinition.setReportTypeId("17");
    reportDefinition.setReportVersion("1");
    reportDefinition.setStartDate(new Date());

    byte[] array = new byte[5];

    ObjectMapper objectMapper = new ObjectMapper();
    ContractTerminationDto contractTerminationDto = objectMapper.readValue(contractTerminationDtoJson1, ContractTerminationDto.class);

    // Mock化
    // メソッドの戻り値を設定
    Mockito.when(contractTerminationApiLogic.callContractTerminationLogic(ArgumentMatchers.any(),ArgumentMatchers.any(),ArgumentMatchers.any())).thenReturn(contractTerminationDto);
    Mockito.when(pdfService.pdfExport(ArgumentMatchers.any(), ArgumentMatchers.any(), ArgumentMatchers.any())).thenReturn(array);
    Mockito.when(storedDataHistoryRepository.save(ArgumentMatchers.any())).thenReturn(true);
    Mockito.when(reportDefinitionRepository
        .findByStartDateLessThanEqual(ArgumentMatchers.any())).thenReturn(reportDefinitionList);

    // ユーザ識別子
    String userId = "01234567890123456789012345678901";
    // 口座識別子
    String accountId = "01234567890123456789";
    // 契約終了通知書識別子
    String contractId = "rid0000000000000001";
    // 科目コード
    String type = "111";
    

    byte[] result = contractTerminationService.getContractTerminationService(userId, accountId, contractId, type);
    Assert.assertArrayEquals(result, array);

  }

  /**
   * 正常系　引数：最小桁　必須項目：有　非必須項目：null
   */
  @Test
  public void testNormalMinHasNull() throws Exception {

    List<ReportDefinition> reportDefinitionList = new ArrayList<ReportDefinition>();
    ReportDefinition reportDefinition = new ReportDefinition();

    reportDefinition.setId(2L);
    reportDefinition.setReportFormatFilePath("fullpath");
    reportDefinition.setReportTypeId("6");
    reportDefinition.setReportVersion("1");
    reportDefinition.setStartDate(new Date());

    byte[] array = new byte[5];

    ObjectMapper objectMapper = new ObjectMapper();
    ContractTerminationDto contractTerminationDto = objectMapper.readValue(contractTerminationDtoJson2, ContractTerminationDto.class);

    // Mock化
    // メソッドの戻り値を設定
    Mockito.when(contractTerminationApiLogic.callContractTerminationLogic(ArgumentMatchers.any(),ArgumentMatchers.any(),ArgumentMatchers.any())).thenReturn(contractTerminationDto);
    Mockito.when(pdfService.pdfExport(ArgumentMatchers.any(), ArgumentMatchers.any(), ArgumentMatchers.any())).thenReturn(array);
    Mockito.when(storedDataHistoryRepository.save(ArgumentMatchers.any())).thenReturn(true);
    Mockito.when(reportDefinitionRepository
        .findByStartDateLessThanEqual(ArgumentMatchers.any())).thenReturn(reportDefinitionList);

    // ユーザ識別子
    String userId = "1";
    // 口座識別子
    String accountId = "1";
    // 契約終了通知書識別子
    String contractId = "1";
    // 科目コード
    String type = "111";
   

    byte[] result = contractTerminationService.getContractTerminationService(userId, accountId, contractId, type);
    Assert.assertArrayEquals(result, array);

  }


  /**
   * 正常系　引数：最小桁　必須項目：有　非必須項目：空文字
   */
  @Test
  public void testNormalMinHasEmpty() throws Exception {

    List<ReportDefinition> reportDefinitionList = new ArrayList<ReportDefinition>();
    ReportDefinition reportDefinition = new ReportDefinition();

    reportDefinition.setId(2L);
    reportDefinition.setReportFormatFilePath("fullpath");
    reportDefinition.setReportTypeId("17");
    reportDefinition.setReportVersion("1");
    reportDefinition.setStartDate(new Date());

    byte[] array = new byte[5];

    ObjectMapper objectMapper = new ObjectMapper();
    ContractTerminationDto contractTerminationDto = objectMapper.readValue(contractTerminationDtoJson3, ContractTerminationDto.class);

    // Mock化
    // メソッドの戻り値を設定
    Mockito.when(contractTerminationApiLogic.callContractTerminationLogic(ArgumentMatchers.any(),ArgumentMatchers.any(),ArgumentMatchers.any())).thenReturn(contractTerminationDto);
    Mockito.when(pdfService.pdfExport(ArgumentMatchers.any(), ArgumentMatchers.any(), ArgumentMatchers.any())).thenReturn(array);
    Mockito.when(storedDataHistoryRepository.save(ArgumentMatchers.any())).thenReturn(true);
    Mockito.when(reportDefinitionRepository
        .findByStartDateLessThanEqual(ArgumentMatchers.any())).thenReturn(reportDefinitionList);

    // ユーザ識別子
    String userId = "1";
    // 口座識別子
    String accountId = "1";
    // 契約終了通知書識別子
    String contractId = "1";
    // 科目コード
    String type = "111";
   

    byte[] result = contractTerminationService.getContractTerminationService(userId, accountId, contractId, type);
    Assert.assertArrayEquals(result, array);

  }

  /**
   * 異常系　帳票識別子がnull
   */
  @Test
  public void testErrorReportIdIsNull() throws Exception {

    List<ReportDefinition> reportDefinitionList = new ArrayList<ReportDefinition>();
    ReportDefinition reportDefinition = new ReportDefinition();

    reportDefinition.setId(2L);
    reportDefinition.setReportFormatFilePath("fullpath");
    reportDefinition.setReportTypeId("17");
    reportDefinition.setReportVersion("1");
    reportDefinition.setStartDate(new Date());

    byte[] array = new byte[5];

    ObjectMapper objectMapper = new ObjectMapper();
    ContractTerminationDto contractTerminationDto = objectMapper.readValue(contractTerminationDtoJson1, ContractTerminationDto.class);

    // Mock化
    // メソッドの戻り値を設定
    Mockito.when(contractTerminationApiLogic.callContractTerminationLogic(ArgumentMatchers.any(),ArgumentMatchers.any(),ArgumentMatchers.any())).thenReturn(contractTerminationDto);
    Mockito.when(pdfService.pdfExport(ArgumentMatchers.any(), ArgumentMatchers.any(), ArgumentMatchers.any())).thenReturn(array);
    Mockito.when(storedDataHistoryRepository.save(ArgumentMatchers.any())).thenReturn(true);
    Mockito.when(reportDefinitionRepository
        .findByStartDateLessThanEqual(ArgumentMatchers.any())).thenReturn(reportDefinitionList);

    // ユーザ識別子
    String userId = "1";
    // 口座識別子
    String accountId = "1";
    // 契約終了通知書識別子
    String contractId = null;
    // 科目コード
    String type = "111";
   
    Exception result = new Exception();

    try {
      contractTerminationService.getContractTerminationService(userId, accountId, contractId, type);
    } catch (Exception e) {
      result = e;
    }

    MatcherAssert.assertThat(result, is(instanceOf(Exception.class)));
  }

  /**
   * 異常系　口座識別子がnull
   */
  @Test
  public void testErrorAccountIdIsNull() throws Exception {

    List<ReportDefinition> reportDefinitionList = new ArrayList<ReportDefinition>();
    ReportDefinition reportDefinition = new ReportDefinition();

    reportDefinition.setId(2L);
    reportDefinition.setReportFormatFilePath("fullpath");
    reportDefinition.setReportTypeId("17");
    reportDefinition.setReportVersion("1");
    reportDefinition.setStartDate(new Date());

    byte[] array = new byte[5];

    ObjectMapper objectMapper = new ObjectMapper();
    ContractTerminationDto contractTerminationDto = objectMapper.readValue(contractTerminationDtoJson1, ContractTerminationDto.class);

    // Mock化
    // メソッドの戻り値を設定
    Mockito.when(contractTerminationApiLogic.callContractTerminationLogic(ArgumentMatchers.any(),ArgumentMatchers.any(),ArgumentMatchers.any())).thenReturn(contractTerminationDto);
    Mockito.when(pdfService.pdfExport(ArgumentMatchers.any(), ArgumentMatchers.any(), ArgumentMatchers.any())).thenReturn(array);
    Mockito.when(storedDataHistoryRepository.save(ArgumentMatchers.any())).thenReturn(true);
    Mockito.when(reportDefinitionRepository
        .findByStartDateLessThanEqual(ArgumentMatchers.any())).thenReturn(reportDefinitionList);

    // ユーザ識別子
    String userId = "1";
    // 口座識別子
    String accountId = null;
    // 契約終了通知書識別子
    String contractId = "1";
    // 科目コード
    String type = "111";
 

    Exception result = new Exception();

    try {
      contractTerminationService.getContractTerminationService(userId, accountId, contractId, type);
    } catch (Exception e) {
      result = e;
    }

    MatcherAssert.assertThat(result, is(instanceOf(Exception.class)));
  }

 
}