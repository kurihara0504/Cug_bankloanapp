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
import com.moneyforward.cug_bankbookbase.dto.CardLoanTransactionDto;
import com.moneyforward.cug_bankbookbase.logic.CardLoanTransactionApiLogic;

/**
 * カードローンお取引内容のお知らせ　Serviceクラスのテスト.
 */
@ContextConfiguration
@RunWith(SpringRunner.class)
@SpringBootTest
public class CardLoanTransactionServiceTest {

  /** テスト対象クラス. */
  @Autowired
  CardLoanTransactionService cardLoanTransactionService;

  /** テスト実行時の共通処理の設定. */
  @Rule
  public ExpectedException exception = ExpectedException.none();

  /** 蓄積データ参照履歴情報. */
  @Mock
  private StoredDataHistoryRepository storedDataHistoryRepository;

  /** Mock化対象クラス. */
  @Mock
  private CardLoanTransactionApiLogic cardLoanTransactionApiLogic;
  @Mock
  private ReportDefinitionRepository reportDefinitionRepository;
  @Mock
  private PdfService pdfService;
  @Mock
  ResourceLoader resource;

  /** 初期処理. */
  @Before
  public void before() {
    ReflectionTestUtils.setField(cardLoanTransactionService, "cardLoanTransactionApiLogic", cardLoanTransactionApiLogic);
    ReflectionTestUtils.setField(cardLoanTransactionService, "pdfService", pdfService);
  }

  /** 最後の処理. */
  @After
  public void doAfter() {
    // 初期化
    Mockito.reset(resource);
    Mockito.reset(storedDataHistoryRepository);
    Mockito.reset(cardLoanTransactionApiLogic);
    Mockito.reset(pdfService);
    Mockito.reset(reportDefinitionRepository);
  }

  

  // カードローン取引内容通知照会のレスポンス（非必須項目：有）
  private String cardLoanTransactionDtoJson1 = "{\"noticeCardLoanTransactionId\":\"rid0999005\",\"displayDocumentName\":\"カードローンお取引内容のお知らせ\",\"displayProductName\": \"カードローン\",\"applicantName\": \"中国太郎\",\"issuedDate\": \"2022-01-15\",\"productName\": \"カードローンミニ\",\"accountNumber\": \"1234567\",\"repaymentAccountNumber\": \"1234567\",\"loanLimitAmount\":1234567890,\"loanInterestRate\": \"123.456\",\"branchName\": \"中国支店\",\"branchPhoneNumber\": \"012-111-2222\",\"details\": [{\"transactionDate\": \"2020-01-01\",\"loanAmount\": 1234567890,\"repaymentAmount\": 1234567890,\"description\": \"ううううううううううううううううう\",\"loanBalance\": 111111111012}]}";
  // カードローン取引内容通知照会のレスポンス（非必須項目：無（null））
  private String cardLoanTransactionDtoJson2 = "{\"noticeCardLoanTransactionId\":\"rid0999005\",\"displayDocumentName\":null,\"displayProductName\":null,\"applicantName\":null,\"issuedDate\": \"2022-01-15\",\"productName\":null,\"accountNumber\":null,\"repaymentAccountNumber\":null,\"loanLimitAmount\":null,\"loanInterestRate\":null,\"branchName\":null,\"branchPhoneNumber\":null,\"details\": [{\"transactionDate\":null,\"loanAmount\":null,\"repaymentAmount\":null,\"description\":null,\"loanBalance\":null}]}";
  // カードローン取引内容通知照会のレスポンス（非必須項目：無（空文字））
  private String cardLoanTransactionDtoJson3 = "{\"noticeCardLoanTransactionId\":\"rid0999005\",\"displayDocumentName\":\"\",\"displayProductName\": \"\",\"applicantName\": \"\",\"issuedDate\": \"2022-01-15\",\"productName\": \"\",\"accountNumber\": \"\",\"repaymentAccountNumber\":\"\",\"loanLimitAmount\":\"\",\"loanInterestRate\": \"\",\"branchName\": \"\",\"branchPhoneNumber\":\"\",\"details\": [{\"transactionDate\":\"\",\"loanAmount\":\"\",\"repaymentAmount\":\"\",\"description\":\"\",\"loanBalance\":\"\"}]}";

  /**
   * カードローンお取引内容のお知らせ
   * 正常系 引数：最大桁 非必須項目：有
   */
  @Test
  public void testNormalCardLoanTransactionMax() throws Exception {

    List<ReportDefinition> reportDefinitionList = new ArrayList<ReportDefinition>();
    ReportDefinition reportDefinition = new ReportDefinition();

    reportDefinition.setId(2L);
    reportDefinition.setReportFormatFilePath("fullpath");
    reportDefinition.setReportTypeId("13");
    reportDefinition.setReportVersion("1");
    reportDefinition.setStartDate(new Date());

    byte[] array = new byte[5];

    ObjectMapper objectMapper = new ObjectMapper();
    CardLoanTransactionDto cardLoanTransactionsDto = objectMapper.readValue(cardLoanTransactionDtoJson1, CardLoanTransactionDto.class);

    // Mock化
    // メソッドの戻り値を設定
    Mockito.when(cardLoanTransactionApiLogic.callCardLoanTransactionLogic(ArgumentMatchers.any(),ArgumentMatchers.any(),ArgumentMatchers.any())).thenReturn(cardLoanTransactionsDto);
    Mockito.when(pdfService.pdfExport(ArgumentMatchers.any(), ArgumentMatchers.any(), ArgumentMatchers.any())).thenReturn(array);
    Mockito.when(storedDataHistoryRepository.save(ArgumentMatchers.any())).thenReturn(true);
    Mockito.when(reportDefinitionRepository.findByStartDateLessThanEqual(ArgumentMatchers.any())).thenReturn(reportDefinitionList);

    // ユーザ識別子
    String userId = "01234567890123456789012345678901";
    // 口座識別子
    String accountId = "01234567890123456789";
    // カードローンお取引内容のお知らせ識別子
    String reportId = "0123456789012345678";
    // 科目コード
    String type = "020";

    byte[] result = cardLoanTransactionService.getCardLoanTransactionService(userId, accountId, reportId, type);
    Assert.assertArrayEquals(result, array);
   

  }

  /**
   * カードローンお取引内容のお知らせ
   * 正常系  非必須項目：無（null）
   */
  @Test
  public void testNormalCardLoanTransactionNull() throws Exception {

    List<ReportDefinition> reportDefinitionList = new ArrayList<ReportDefinition>();
    ReportDefinition reportDefinition = new ReportDefinition();

    reportDefinition.setId(2L);
    reportDefinition.setReportFormatFilePath("fullpath");
    reportDefinition.setReportTypeId("13");
    reportDefinition.setReportVersion("1");
    reportDefinition.setStartDate(new Date());

    byte[] array = new byte[5];

    ObjectMapper objectMapper = new ObjectMapper();
    CardLoanTransactionDto cardLoanTransactionsDto = objectMapper.readValue(cardLoanTransactionDtoJson2, CardLoanTransactionDto.class);

    // Mock化
    // メソッドの戻り値を設定
    Mockito.when(cardLoanTransactionApiLogic.callCardLoanTransactionLogic(ArgumentMatchers.any(),ArgumentMatchers.any(),ArgumentMatchers.any())).thenReturn(cardLoanTransactionsDto);
    Mockito.when(pdfService.pdfExport(ArgumentMatchers.any(), ArgumentMatchers.any(), ArgumentMatchers.any())).thenReturn(array);
    Mockito.when(storedDataHistoryRepository.save(ArgumentMatchers.any())).thenReturn(true);
    Mockito.when(reportDefinitionRepository.findByStartDateLessThanEqual(ArgumentMatchers.any())).thenReturn(reportDefinitionList);

    // ユーザ識別子
    String userId = "1";
    // 口座識別子
    String accountId = "1";
    // カードローンお取引内容のお知らせ識別子
    String reportId = "1";
    // 科目コード
    String type = "020";

    byte[] result = cardLoanTransactionService.getCardLoanTransactionService(userId, accountId, reportId, type);
    Assert.assertArrayEquals(result, array);
   

  }

  /**
   * カードローンお取引内容のお知らせ
   * 正常系 非必須項目：無（空文字）
   */
  @Test
  public void testNormalCardLoanTransactionBlank() throws Exception {

    List<ReportDefinition> reportDefinitionList = new ArrayList<ReportDefinition>();
    ReportDefinition reportDefinition = new ReportDefinition();

    reportDefinition.setId(2L);
    reportDefinition.setReportFormatFilePath("fullpath");
    reportDefinition.setReportTypeId("15");
    reportDefinition.setReportVersion("1");
    reportDefinition.setStartDate(new Date());

    byte[] array = new byte[5];

    ObjectMapper objectMapper = new ObjectMapper();
    CardLoanTransactionDto cardLoanTransactionsDto = objectMapper.readValue(cardLoanTransactionDtoJson3, CardLoanTransactionDto.class);

    // Mock化
    // メソッドの戻り値を設定
    Mockito.when(cardLoanTransactionApiLogic.callCardLoanTransactionLogic(ArgumentMatchers.any(),ArgumentMatchers.any(),ArgumentMatchers.any())).thenReturn(cardLoanTransactionsDto);
    Mockito.when(pdfService.pdfExport(ArgumentMatchers.any(), ArgumentMatchers.any(), ArgumentMatchers.any())).thenReturn(array);
    Mockito.when(storedDataHistoryRepository.save(ArgumentMatchers.any())).thenReturn(true);
    Mockito.when(reportDefinitionRepository.findByStartDateLessThanEqual(ArgumentMatchers.any())).thenReturn(reportDefinitionList);

    // ユーザ識別子
    String userId = "1";
    // 口座識別子
    String accountId = "1";
    // カードローンお取引内容のお知らせ識別子
    String reportId = "1";
    // 科目コード
    String type = "020";

    byte[] result = cardLoanTransactionService.getCardLoanTransactionService(userId, accountId, reportId, type);
    Assert.assertArrayEquals(result, array);
   

  }



  /**
   * カードローンお取引内容のお知らせ
   * 異常系 口座識別子がnull
   */
  @Test
  public void testNormalCardLoanTransactionAccountIdIsNull() throws Exception {

    List<ReportDefinition> reportDefinitionList = new ArrayList<ReportDefinition>();
    ReportDefinition reportDefinition = new ReportDefinition();

    reportDefinition.setId(2L);
    reportDefinition.setReportFormatFilePath("fullpath");
    reportDefinition.setReportTypeId("16");
    reportDefinition.setReportVersion("1");
    reportDefinition.setStartDate(new Date());

    byte[] array = new byte[5];

    ObjectMapper objectMapper = new ObjectMapper();
    CardLoanTransactionDto cardLoanTransactionsDto = objectMapper.readValue(cardLoanTransactionDtoJson1, CardLoanTransactionDto.class);

    // Mock化
    // メソッドの戻り値を設定
    Mockito.when(cardLoanTransactionApiLogic.callCardLoanTransactionLogic(ArgumentMatchers.any(),ArgumentMatchers.any(),ArgumentMatchers.any())).thenReturn(cardLoanTransactionsDto);
    Mockito.when(pdfService.pdfExport(ArgumentMatchers.any(), ArgumentMatchers.any(), ArgumentMatchers.any())).thenReturn(array);
    Mockito.when(storedDataHistoryRepository.save(ArgumentMatchers.any())).thenReturn(true);
    Mockito.when(reportDefinitionRepository
        .findByStartDateLessThanEqual(ArgumentMatchers.any())).thenReturn(reportDefinitionList);

    // ユーザ識別子
    String userId = "1";
    // 口座識別子
    String accountId = null;
    // カードローンお取引内容のお知らせ識別子
    String reportId = "1";
    // 科目コード
    String type = "020";

    Exception result = new Exception();

    try {
      cardLoanTransactionService.getCardLoanTransactionService(userId, accountId, reportId, type);
    } catch (Exception e) {
      result = e;
    }

    MatcherAssert.assertThat(result, is(instanceOf(Exception.class)));

  }

  /**
   * カードローンお取引内容のお知らせ
   * 異常系 帳票識別子がnull
   */
  @Test
  public void testNormalCardLoanTransactionIdIsNull() throws Exception {

    List<ReportDefinition> reportDefinitionList = new ArrayList<ReportDefinition>();
    ReportDefinition reportDefinition = new ReportDefinition();

    reportDefinition.setId(2L);
    reportDefinition.setReportFormatFilePath("fullpath");
    reportDefinition.setReportTypeId("16");
    reportDefinition.setReportVersion("1");
    reportDefinition.setStartDate(new Date());

    byte[] array = new byte[5];

    ObjectMapper objectMapper = new ObjectMapper();
    CardLoanTransactionDto cardLoanTransactionsDto = objectMapper.readValue(cardLoanTransactionDtoJson1, CardLoanTransactionDto.class);

    // Mock化
    // メソッドの戻り値を設定
    Mockito.when(cardLoanTransactionApiLogic.callCardLoanTransactionLogic(ArgumentMatchers.any(),ArgumentMatchers.any(),ArgumentMatchers.any())).thenReturn(cardLoanTransactionsDto);
    Mockito.when(pdfService.pdfExport(ArgumentMatchers.any(), ArgumentMatchers.any(), ArgumentMatchers.any())).thenReturn(array);
    Mockito.when(storedDataHistoryRepository.save(ArgumentMatchers.any())).thenReturn(true);
    Mockito.when(reportDefinitionRepository
        .findByStartDateLessThanEqual(ArgumentMatchers.any())).thenReturn(reportDefinitionList);

    // ユーザ識別子
    String userId = "1";
    // 口座識別子
    String accountId = "1";
    // カードローンお取引内容のお知らせ識別子
    String reportId = null;
    // 科目コード
    String type = "020";

    Exception result = new Exception();

    try {
      cardLoanTransactionService.getCardLoanTransactionService(userId, accountId, reportId, type);
    } catch (Exception e) {
      result = e;
    }

    MatcherAssert.assertThat(result, is(instanceOf(Exception.class)));

  }

}
