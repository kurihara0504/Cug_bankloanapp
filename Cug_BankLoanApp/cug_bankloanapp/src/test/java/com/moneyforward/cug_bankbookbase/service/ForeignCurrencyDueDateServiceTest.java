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
import com.moneyforward.bankbookbase.repository.ReportDefinitionRepository;
import com.moneyforward.bankbookbase.repository.StoredDataHistoryRepository;
import com.moneyforward.bankbookbase.repository.entity.ReportDefinition;
import com.moneyforward.bankbookbase.service.PdfService;
import com.moneyforward.cug_bankbookbase.dto.ForeignCurrencyDueDateDto;
import com.moneyforward.cug_bankbookbase.logic.ForeignCurrencyDueDateApiLogic;

/**
 * 外貨定期預金期日案内取得サービス　テストクラス.
 */
@ContextConfiguration
@RunWith(SpringRunner.class)
@SpringBootTest
public class ForeignCurrencyDueDateServiceTest {

  /** テスト対象クラス. */
  @Autowired
  private ForeignCurrencyDueDateService foreignCurrencyDueDateService;

  /** テスト実行時の共通処理の設定. */
  @Rule
  public ExpectedException exception = ExpectedException.none();

  /** Mock化対象クラス. */
  @Mock
  private ForeignCurrencyDueDateApiLogic foreignCurrencyDueDateApiLogic;
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
    ReflectionTestUtils.setField(foreignCurrencyDueDateService, "foreignCurrencyDueDateApiLogic", foreignCurrencyDueDateApiLogic);
    ReflectionTestUtils.setField(foreignCurrencyDueDateService, "pdfService", pdfService);
  }

  /** 最後処理. */
  @After
  public void after() {
    // 初期化
    Mockito.reset(foreignCurrencyDueDateApiLogic);
    Mockito.reset(pdfService);
    Mockito.reset(storedDataHistoryRepository);
    Mockito.reset(reportDefinitionRepository);
    Mockito.reset(resource);
  }

  // 外貨定期預金期日のご案内　必須項目：有 非必須項目:有
  private String foreignCurrencyDueDateDtoJson1 = "{\"foreignTimeDepositMaturityNotificationsId\":\"rid0999008\",\"applicantName\":\"あいうえおあいうえおあいうえおあいうえおあいうえおあいうえおあいうえおあいうえおあいうえおあいうえおあいうえおあいうえおあいうえおあいうえおあいうえおあいうえおあいうえおあいうえおあいうえおあいうえお\",\"maturityDate\":\"2023-10-17\",\"depositNumber\":\"1234567\",\"currencyCode\":\"US.$\",\"depositAmount\":\"12345678901234.56\",\"afterMaturityDate\":\"自動解約入金\",\"interestRate\":\"12.345\",\"period\":\"1年\",\"interest\":\"123456789999.99\",\"taxClass\":\"非課税\",\"nationalTax\":\"1234567890.00\",\"localTax\":\"1234567890.00\",\"deductionPaymentInterest\":\"123456789999.99\",\"interestHandling\":\"定期預金へお振替\",\"newTimeDepositAmount\":\"12345678901234.56\",\"issuedDate\":\"2023-07-07\",\"branchName\":\"あいうえおあいうえおあいう\",\"branchPhoneNumber\":\"0120-1234-5678\"}";
  // 外貨定期預金期日のご案内　必須項目：有 非必須項目:無（null）
  private String foreignCurrencyDueDateDtoJson2 = "{\"foreignTimeDepositMaturityNotificationsId\":\"rid0999008\",\"applicantName\":null,\"maturityDate\":null,\"depositNumber\":null,\"currencyCode\":null,\"depositAmount\":null,\"afterMaturityDate\":null,\"interestRate\":null,\"period\":null,\"interest\":null,\"taxClass\":null,\"nationalTax\":null,\"localTax\":null,\"deductionPaymentInterest\":null,\"interestHandling\":null,\"newTimeDepositAmount\":null,\"issuedDate\":\"2023-07-07\",\"branchName\":null,\"branchPhoneNumber\":null}";
  // 外貨定期預金期日のご案内　必須項目：有 非必須項目:無（空文字）
  private String foreignCurrencyDueDateDtoJson3 = "{\"foreignTimeDepositMaturityNotificationsId\":\"rid0999008\",\"applicantName\":\"\",\"maturityDate\":\"\",\"depositNumber\":\"\",\"currencyCode\":\"\",\"depositAmount\":\"\",\"afterMaturityDate\":\"\",\"interestRate\":\"\",\"period\":\"\",\"interest\":\"\",\"taxClass\":\"\",\"nationalTax\":\"\",\"localTax\":\"\",\"deductionPaymentInterest\":\"\",\"interestHandling\":\"\",\"newTimeDepositAmount\":\"\",\"issuedDate\":\"2023-07-07\",\"branchName\":\"\",\"branchPhoneNumber\":\"\"}";



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
    ForeignCurrencyDueDateDto foreignCurrencyDueDateDto = objectMapper.readValue(foreignCurrencyDueDateDtoJson1, ForeignCurrencyDueDateDto.class);

    // Mock化
    // メソッドの戻り値を設定
    Mockito.when(foreignCurrencyDueDateApiLogic.callForeignCurrencyDueDateLogic(ArgumentMatchers.any(),ArgumentMatchers.any(),ArgumentMatchers.any())).thenReturn(foreignCurrencyDueDateDto);
    Mockito.when(pdfService.pdfExport(ArgumentMatchers.any(), ArgumentMatchers.any(), ArgumentMatchers.any())).thenReturn(array);
    Mockito.when(storedDataHistoryRepository.save(ArgumentMatchers.any())).thenReturn(true);
    Mockito.when(reportDefinitionRepository
        .findByStartDateLessThanEqual(ArgumentMatchers.any())).thenReturn(reportDefinitionList);

    // ユーザ識別子
    String userId = "01234567890123456789012345678901";
    // 口座識別子
    String accountId = "01234567890123456789";
    // 外貨定期預金期日案内識別子
    String reportId = "rid0000000000000001";
    // 科目コード
    String type = "111";
    

    byte[] result = foreignCurrencyDueDateService.getForeignCurrencyDueDateService(userId, accountId, reportId, type);
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
    ForeignCurrencyDueDateDto foreignCurrencyDueDateDto = objectMapper.readValue(foreignCurrencyDueDateDtoJson2, ForeignCurrencyDueDateDto.class);

    // Mock化
    // メソッドの戻り値を設定
    Mockito.when(foreignCurrencyDueDateApiLogic.callForeignCurrencyDueDateLogic(ArgumentMatchers.any(),ArgumentMatchers.any(),ArgumentMatchers.any())).thenReturn(foreignCurrencyDueDateDto);
    Mockito.when(pdfService.pdfExport(ArgumentMatchers.any(), ArgumentMatchers.any(), ArgumentMatchers.any())).thenReturn(array);
    Mockito.when(storedDataHistoryRepository.save(ArgumentMatchers.any())).thenReturn(true);
    Mockito.when(reportDefinitionRepository
        .findByStartDateLessThanEqual(ArgumentMatchers.any())).thenReturn(reportDefinitionList);

    // ユーザ識別子
    String userId = "1";
    // 口座識別子
    String accountId = "1";
    // 外貨定期預金期日案内識別子
    String reportId = "1";
    // 科目コード
    String type = "111";
   

    byte[] result = foreignCurrencyDueDateService.getForeignCurrencyDueDateService(userId, accountId, reportId, type);
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
    ForeignCurrencyDueDateDto foreignCurrencyDueDateDto = objectMapper.readValue(foreignCurrencyDueDateDtoJson3, ForeignCurrencyDueDateDto.class);

    // Mock化
    // メソッドの戻り値を設定
    Mockito.when(foreignCurrencyDueDateApiLogic.callForeignCurrencyDueDateLogic(ArgumentMatchers.any(),ArgumentMatchers.any(),ArgumentMatchers.any())).thenReturn(foreignCurrencyDueDateDto);
    Mockito.when(pdfService.pdfExport(ArgumentMatchers.any(), ArgumentMatchers.any(), ArgumentMatchers.any())).thenReturn(array);
    Mockito.when(storedDataHistoryRepository.save(ArgumentMatchers.any())).thenReturn(true);
    Mockito.when(reportDefinitionRepository
        .findByStartDateLessThanEqual(ArgumentMatchers.any())).thenReturn(reportDefinitionList);

    // ユーザ識別子
    String userId = "1";
    // 口座識別子
    String accountId = "1";
    // 外貨定期預金期日案内識別子
    String reportId = "1";
    // 科目コード
    String type = "111";
   

    byte[] result = foreignCurrencyDueDateService.getForeignCurrencyDueDateService(userId, accountId, reportId, type);
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
    ForeignCurrencyDueDateDto foreignCurrencyDueDateDto = objectMapper.readValue(foreignCurrencyDueDateDtoJson1, ForeignCurrencyDueDateDto.class);

    // Mock化
    // メソッドの戻り値を設定
    Mockito.when(foreignCurrencyDueDateApiLogic.callForeignCurrencyDueDateLogic(ArgumentMatchers.any(),ArgumentMatchers.any(),ArgumentMatchers.any())).thenReturn(foreignCurrencyDueDateDto);
    Mockito.when(pdfService.pdfExport(ArgumentMatchers.any(), ArgumentMatchers.any(), ArgumentMatchers.any())).thenReturn(array);
    Mockito.when(storedDataHistoryRepository.save(ArgumentMatchers.any())).thenReturn(true);
    Mockito.when(reportDefinitionRepository
        .findByStartDateLessThanEqual(ArgumentMatchers.any())).thenReturn(reportDefinitionList);

    // ユーザ識別子
    String userId = "1";
    // 口座識別子
    String accountId = "1";
    // 外貨定期預金期日案内識別子
    String reportId = null;
    // 科目コード
    String type = "111";
   
    Exception result = new Exception();

    try {
    	foreignCurrencyDueDateService.getForeignCurrencyDueDateService(userId, accountId, reportId, type);
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
    ForeignCurrencyDueDateDto foreignCurrencyDueDateDto = objectMapper.readValue(foreignCurrencyDueDateDtoJson1, ForeignCurrencyDueDateDto.class);

    // Mock化
    // メソッドの戻り値を設定
    Mockito.when(foreignCurrencyDueDateApiLogic.callForeignCurrencyDueDateLogic(ArgumentMatchers.any(),ArgumentMatchers.any(),ArgumentMatchers.any())).thenReturn(foreignCurrencyDueDateDto);
    Mockito.when(pdfService.pdfExport(ArgumentMatchers.any(), ArgumentMatchers.any(), ArgumentMatchers.any())).thenReturn(array);
    Mockito.when(storedDataHistoryRepository.save(ArgumentMatchers.any())).thenReturn(true);
    Mockito.when(reportDefinitionRepository
        .findByStartDateLessThanEqual(ArgumentMatchers.any())).thenReturn(reportDefinitionList);

    // ユーザ識別子
    String userId = "1";
    // 口座識別子
    String accountId = null;
    // 外貨定期預金期日案内識別子
    String reportId = "1";
    // 科目コード
    String type = "111";
 

    Exception result = new Exception();

    try {
    	foreignCurrencyDueDateService.getForeignCurrencyDueDateService(userId, accountId, reportId, type);
    } catch (Exception e) {
      result = e;
    }

    MatcherAssert.assertThat(result, is(instanceOf(Exception.class)));
  }

 
}