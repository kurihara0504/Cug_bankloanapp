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
import com.moneyforward.cug_bankbookbase.dto.ForeignCurrencyDepositOpenDto;
import com.moneyforward.cug_bankbookbase.logic.ForeignCurrencyDepositOpenApiLogic;
import com.moneyforward.bankbookbase.repository.ReportDefinitionRepository;
import com.moneyforward.bankbookbase.repository.StoredDataHistoryRepository;
import com.moneyforward.bankbookbase.repository.entity.ReportDefinition;
import com.moneyforward.bankbookbase.service.PdfService;

/**
 * 外貨定期預金預入のご案内取得サービス　テストクラス.
 */
@ContextConfiguration
@RunWith(SpringRunner.class)
@SpringBootTest
public class ForeignCurrencyDepositOpenServiceTest {

  /** テスト対象クラス. */
  @Autowired
  private ForeignCurrencyDepositOpenService foreignCurrencyDepositOpenService;

  /** テスト実行時の共通処理の設定. */
  @Rule
  public ExpectedException exception = ExpectedException.none();

  /** Mock化対象クラス. */
  @Mock
  private ForeignCurrencyDepositOpenApiLogic foreignCurrencyDepositOpenApiLogic;
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
    ReflectionTestUtils.setField(foreignCurrencyDepositOpenService, "foreignCurrencyDepositOpenApiLogic", foreignCurrencyDepositOpenApiLogic);
    ReflectionTestUtils.setField(foreignCurrencyDepositOpenService, "pdfService", pdfService);
  }

  /** 最後処理. */
  @After
  public void after() {
    // 初期化
    Mockito.reset(foreignCurrencyDepositOpenApiLogic);
    Mockito.reset(pdfService);
    Mockito.reset(storedDataHistoryRepository);
    Mockito.reset(reportDefinitionRepository);
    Mockito.reset(resource);
  }

  // 外貨定期預金預入のご案内　必須項目：有 非必須項目:有
  private String foreignCurrencyDepositOpenDtoJson1 = "{\"foreignTimeDepositNotificationsOpenId\":\"rid0000000000000001\",\"displayDocumentName\":\"外貨定期預金預入のご案内\",\"displayProductName\":\"外貨定期預金\",\"applicantName\":\"あいうえおかきくけこさしすせそたちつてとなにぬねのあいうえおかきくけこさしすせそたちつてとなにぬねのあいうえおかきくけこさしすせそたちつてとなにぬねのあいうえおかきくけこさしすせそたちつてとなにぬねの\",\"productName\":\"＜外貨定期預金＞\",\"transactionName\":\"お預入\",\"reportType\":\"通帳\",\"continuationType\":\"元利継続\",\"depositNumber\":\"1234567\",\"currencyCode\":\"NZL.$\",\"depositAmount\":\"1234567890123.45\",\"depositDate\":\"2024-01-01\",\"maturityDate\":\"2024-01-01\",\"periodMonths\":10,\"periodDays\":100,\"interestRate\":\"12.34567\",\"taxClass\":\"分離課税\",\"principalAndInterestHandling1\":\"元金は自動継続しますあいうえおかきくけ\",\"principalAndInterestHandling2\":\"利息は指定口座に入金しますあいうえおか\",\"issuedDate\":\"2024-01-01\",\"branchName\":\"中央支店あいうえおかきくけ\",\"branchPhoneNumber\":\"012-3456-7890\"}";
  // 外貨定期預金預入のご案内　必須項目：有 非必須項目:無（null）
  private String foreignCurrencyDepositOpenDtoJson2 = "{\"foreignTimeDepositNotificationsOpenId\":\"rid0000000000000002\",\"displayDocumentName\": null,\"displayProductName\":null,\"applicantName\": null,\"productName\": null,\"transactionName\": null,\"reportType\": null,\"continuationType\": null,\"depositNumber\":null,\"currencyCode\": null,\"depositAmount\": null,\"depositDate\":null,\"maturityDate\": null,\"periodMonths\":null,\"periodDays\": null,\"interestRate\":null,\"taxClass\": null,\"principalAndInterestHandling1\":null,\"principalAndInterestHandling2\":null,\"issuedDate\":\"2024-01-01\",\"branchName\":null,\"branchPhoneNumber\":null}";
  // 外貨定期預金預入のご案内　必須項目：有 非必須項目:無（空文字）
  private String foreignCurrencyDepositOpenDtoJson3 = "{\"foreignTimeDepositNotificationsOpenId\":\"rid0000000000000003\",\"displayDocumentName\":\"\",\"displayProductName\":\"\",\"applicantName\":\"\",\"productName\":\"\",\"transactionName\":\"\",\"reportType\":\"\",\"continuationType\":\"\",\"depositNumber\":\"\",\"currencyCode\":\"\",\"depositAmount\":\"\",\"depositDate\":\"\",\"maturityDate\":\"\",\"periodMonths\":\"\",\"periodDays\":\"\",\"interestRate\":\"\",\"taxClass\":\"\",\"principalAndInterestHandling1\":\"\",\"principalAndInterestHandling2\":\"\",\"issuedDate\":\"2024-01-01\",\"branchName\":\"\",\"branchPhoneNumber\":\"\"}";


  /**
   * 正常系　引数：最大桁　必須項目：有　非必須項目：有
   */
  @Test
  public void testNormalMax() throws Exception {

    List<ReportDefinition> reportDefinitionList = new ArrayList<ReportDefinition>();
    ReportDefinition reportDefinition = new ReportDefinition();

    reportDefinition.setId(2L);
    reportDefinition.setReportFormatFilePath("fullpath");
    reportDefinition.setReportTypeId("15");
    reportDefinition.setReportVersion("1");
    reportDefinition.setStartDate(new Date());

    byte[] array = new byte[5];

    ObjectMapper objectMapper = new ObjectMapper();
    ForeignCurrencyDepositOpenDto foreignCurrencyDepositOpenDto = objectMapper.readValue(foreignCurrencyDepositOpenDtoJson1, ForeignCurrencyDepositOpenDto.class);

    // Mock化
    // メソッドの戻り値を設定
    Mockito.when(foreignCurrencyDepositOpenApiLogic.callForeignCurrencyDepositOpenLogic(ArgumentMatchers.any(),ArgumentMatchers.any(),ArgumentMatchers.any())).thenReturn(foreignCurrencyDepositOpenDto);
    Mockito.when(pdfService.pdfExport(ArgumentMatchers.any(), ArgumentMatchers.any(), ArgumentMatchers.any())).thenReturn(array);
    Mockito.when(storedDataHistoryRepository.save(ArgumentMatchers.any())).thenReturn(true);
    Mockito.when(reportDefinitionRepository
        .findByStartDateLessThanEqual(ArgumentMatchers.any())).thenReturn(reportDefinitionList);

    // ユーザ識別子
    String userId = "01234567890123456789012345678901";
    // 口座識別子
    String accountId = "01234567890123456789";
    // 外貨定期預金預入のご案内帳票識別子
    String contractId = "rid0000000000000001";
    // 科目コード
    String type = "060";
    

    byte[] result = foreignCurrencyDepositOpenService.getForeignCurrencyDepositOpenService(userId, accountId, contractId, type);
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
    ForeignCurrencyDepositOpenDto foreignCurrencyDepositOpenDto = objectMapper.readValue(foreignCurrencyDepositOpenDtoJson2, ForeignCurrencyDepositOpenDto.class);

    // Mock化
    // メソッドの戻り値を設定
    Mockito.when(foreignCurrencyDepositOpenApiLogic.callForeignCurrencyDepositOpenLogic(ArgumentMatchers.any(),ArgumentMatchers.any(),ArgumentMatchers.any())).thenReturn(foreignCurrencyDepositOpenDto);
    Mockito.when(pdfService.pdfExport(ArgumentMatchers.any(), ArgumentMatchers.any(), ArgumentMatchers.any())).thenReturn(array);
    Mockito.when(storedDataHistoryRepository.save(ArgumentMatchers.any())).thenReturn(true);
    Mockito.when(reportDefinitionRepository
        .findByStartDateLessThanEqual(ArgumentMatchers.any())).thenReturn(reportDefinitionList);

    // ユーザ識別子
    String userId = "1";
    // 口座識別子
    String accountId = "1";
    // 外貨定期預金預入のご案内帳票識別子
    String contractId = "1";
    // 科目コード
    String type = "060";
   

    byte[] result = foreignCurrencyDepositOpenService.getForeignCurrencyDepositOpenService(userId, accountId, contractId, type);
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
    reportDefinition.setReportTypeId("15");
    reportDefinition.setReportVersion("1");
    reportDefinition.setStartDate(new Date());

    byte[] array = new byte[5];

    ObjectMapper objectMapper = new ObjectMapper();
    ForeignCurrencyDepositOpenDto foreignCurrencyDepositOpenDto = objectMapper.readValue(foreignCurrencyDepositOpenDtoJson3, ForeignCurrencyDepositOpenDto.class);

    // Mock化
    // メソッドの戻り値を設定
    Mockito.when(foreignCurrencyDepositOpenApiLogic.callForeignCurrencyDepositOpenLogic(ArgumentMatchers.any(),ArgumentMatchers.any(),ArgumentMatchers.any())).thenReturn(foreignCurrencyDepositOpenDto);
    Mockito.when(pdfService.pdfExport(ArgumentMatchers.any(), ArgumentMatchers.any(), ArgumentMatchers.any())).thenReturn(array);
    Mockito.when(storedDataHistoryRepository.save(ArgumentMatchers.any())).thenReturn(true);
    Mockito.when(reportDefinitionRepository
        .findByStartDateLessThanEqual(ArgumentMatchers.any())).thenReturn(reportDefinitionList);

    // ユーザ識別子
    String userId = "1";
    // 口座識別子
    String accountId = "1";
    // 外貨定期預金預入のご案内帳票識別子
    String contractId = "1";
    // 科目コード
    String type = "060";
   

    byte[] result = foreignCurrencyDepositOpenService.getForeignCurrencyDepositOpenService(userId, accountId, contractId, type);
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
    reportDefinition.setReportTypeId("15");
    reportDefinition.setReportVersion("1");
    reportDefinition.setStartDate(new Date());

    byte[] array = new byte[5];

    ObjectMapper objectMapper = new ObjectMapper();
    ForeignCurrencyDepositOpenDto foreignCurrencyDepositOpenDto = objectMapper.readValue(foreignCurrencyDepositOpenDtoJson1, ForeignCurrencyDepositOpenDto.class);

    // Mock化
    // メソッドの戻り値を設定
    Mockito.when(foreignCurrencyDepositOpenApiLogic.callForeignCurrencyDepositOpenLogic(ArgumentMatchers.any(),ArgumentMatchers.any(),ArgumentMatchers.any())).thenReturn(foreignCurrencyDepositOpenDto);
    Mockito.when(pdfService.pdfExport(ArgumentMatchers.any(), ArgumentMatchers.any(), ArgumentMatchers.any())).thenReturn(array);
    Mockito.when(storedDataHistoryRepository.save(ArgumentMatchers.any())).thenReturn(true);
    Mockito.when(reportDefinitionRepository
        .findByStartDateLessThanEqual(ArgumentMatchers.any())).thenReturn(reportDefinitionList);

    // ユーザ識別子
    String userId = "1";
    // 口座識別子
    String accountId = "1";
    // 外貨定期預金預入のご案内帳票識別子
    String contractId = null;
    // 科目コード
    String type = "060";
   
    Exception result = new Exception();

    try {
      foreignCurrencyDepositOpenService.getForeignCurrencyDepositOpenService(userId, accountId, contractId, type);
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
    reportDefinition.setReportTypeId("15");
    reportDefinition.setReportVersion("1");
    reportDefinition.setStartDate(new Date());

    byte[] array = new byte[5];

    ObjectMapper objectMapper = new ObjectMapper();
    ForeignCurrencyDepositOpenDto foreignCurrencyDepositOpenDto = objectMapper.readValue(foreignCurrencyDepositOpenDtoJson1, ForeignCurrencyDepositOpenDto.class);

    // Mock化
    // メソッドの戻り値を設定
    Mockito.when(foreignCurrencyDepositOpenApiLogic.callForeignCurrencyDepositOpenLogic(ArgumentMatchers.any(),ArgumentMatchers.any(),ArgumentMatchers.any())).thenReturn(foreignCurrencyDepositOpenDto);
    Mockito.when(pdfService.pdfExport(ArgumentMatchers.any(), ArgumentMatchers.any(), ArgumentMatchers.any())).thenReturn(array);
    Mockito.when(storedDataHistoryRepository.save(ArgumentMatchers.any())).thenReturn(true);
    Mockito.when(reportDefinitionRepository
        .findByStartDateLessThanEqual(ArgumentMatchers.any())).thenReturn(reportDefinitionList);

    // ユーザ識別子
    String userId = "1";
    // 口座識別子
    String accountId = null;
    // 外貨定期預金預入のご案内帳票識別子
    String contractId = "1";
    // 科目コード
    String type = "060";

    Exception result = new Exception();

    try {
      foreignCurrencyDepositOpenService.getForeignCurrencyDepositOpenService(userId, accountId, contractId, type);
    } catch (Exception e) {
      result = e;
    }

    MatcherAssert.assertThat(result, is(instanceOf(Exception.class)));
  }

 
}