package com.moneyforward.cug_bankbookbase.service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

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
import com.moneyforward.cug_bankbookbase.dto.InformationDepositDto;
import com.moneyforward.cug_bankbookbase.logic.InformationDepositLogic;

/**
 * 定期預金期日のご案内取得サービス　テストクラス.
 */
@ContextConfiguration
@RunWith(SpringRunner.class)
@SpringBootTest
public class InformationDepositServiceTest {

  /** テスト対象クラス. */
  @Autowired
  private InformationDepositService informationDepositService;

  /** テスト実行時の共通処理の設定. */
  @Rule
  public ExpectedException exception = ExpectedException.none();

  /** Mock化対象クラス. */
  @Mock
  private InformationDepositLogic informationDepositLogic;
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
    ReflectionTestUtils.setField(informationDepositService, "informationDepositLogic", informationDepositLogic);
    ReflectionTestUtils.setField(informationDepositService, "pdfService", pdfService);
  }

  /** 最後処理. */
  @After
  public void after() {
    // 初期化
    Mockito.reset(informationDepositLogic);
    Mockito.reset(pdfService);
    Mockito.reset(storedDataHistoryRepository);
    Mockito.reset(reportDefinitionRepository);
    Mockito.reset(resource);
  }
  
  // 全項目有
  private String json1 = "{\"maturityNotificationId\":\"rid09991003\",\"accountNumber\":\"0123450\",\"issuedDate\":\"2022-01-15\",\"branchName\":\"中央支店\",\"branchPhoneNumber\":\"0120-11-2222\",\"informationLine1\":\"testInfo1\",\"informationLine2\":\"testInfo2\",\"informationLine3\":\"testInfo3\",\"informationLine4\":\"testInfo4\",\"informationLine5\":\"testInfo5\",\"applicantName\":\"あいうえおかきくけこさしすせそたちつてとなにぬねのはひふへほまみむめも\",\"list\":[{\"interestPaymentDate\":\"2020-01-01\",\"depositNumber\":\"010\",\"timeDepositType\":\"ああああああああああああああああああ\",\"handlingType\":\"\",\"depositAmount\":100000000,\"interest\":\"1.5\",\"period\":\"ああああああああああああああああああ\",\"interestAmount\":200000000,\"taxClass\":\"ああああああああああああああああああ\",\"nationalTax\":300000000,\"localTax\":400000000,\"deductionPaymentInterest\":500000000,\"renewalPrincipalValue\":600000000,\"interestHandling\":\"ああああああああああああああああああ\",\"transferDesignationAccount\":\"\",\"maruyuTaxableInterest\":10,\"maruyuTaxableDays\":123,\"remarks\":\"ああああああああああああああああああ\",\"interest2\":\"1.5\",\"period2\":\"いいい\",\"interestAmount2\":100,\"paymentRecord\":\"200\"}]}";
  // 二列表示下段のみInterest2がnull
  private String json2 = "{\"maturityNotificationId\":\"rid09991003\",\"accountNumber\":\"0123450\",\"issuedDate\":\"2022-01-15\",\"branchName\":\"中央支店\",\"branchPhoneNumber\":\"0120-11-2222\",\"informationLine1\":null,\"informationLine2\":null,\"informationLine3\":null,\"informationLine4\":null,\"informationLine5\":null,\"applicantName\":\"あいうえおかきくけこさしすせそたちつてとなにぬねのはひふへほまみむめも\",\"list\":[{\"interestPaymentDate\":\"2020-01-01\",\"depositNumber\":\"010\",\"timeDepositType\":\"ああああああああああああああああああ\",\"handlingType\":\"\",\"depositAmount\":100000000,\"interest\":\"1.5\",\"period\":\"ああああああああああああああああああ\",\"interestAmount\":200000000,\"taxClass\":\"ああああああああああああああああああ\",\"nationalTax\":300000000,\"localTax\":400000000,\"deductionPaymentInterest\":500000000,\"renewalPrincipalValue\":600000000,\"interestHandling\":\"ああああああああああああああああああ\",\"transferDesignationAccount\":\"\",\"maruyuTaxableInterest\":null,\"maruyuTaxableDays\":null,\"remarks\":null,\"interest2\":null,\"period2\":\"\",\"interestAmount2\":0,\"paymentRecord\":null}]}";
  // 二列表示下段のみnullバージョン
  private String json3 = "{\"maturityNotificationId\":\"rid09991003\",\"accountNumber\":\"0123450\",\"issuedDate\":\"2022-01-15\",\"branchName\":\"中央支店\",\"branchPhoneNumber\":\"0120-11-2222\",\"informationLine1\":\"testInfo1\",\"informationLine2\":\"testInfo2\",\"informationLine3\":\"testInfo3\",\"informationLine4\":\"testInfo4\",\"informationLine5\":\"testInfo5\",\"applicantName\":\"あいうえおかきくけこさしすせそたちつてとなにぬねのはひふへほまみむめも\",\"list\":[{\"interestPaymentDate\":\"2020-01-01\",\"depositNumber\":\"010\",\"timeDepositType\":\"ああああああああああああああああああ\",\"handlingType\":\"\",\"depositAmount\":100000000,\"interest\":\"1.5\",\"period\":\"ああああああああああああああああああ\",\"interestAmount\":200000000,\"taxClass\":\"ああああああああああああああああああ\",\"nationalTax\":300000000,\"localTax\":400000000,\"deductionPaymentInterest\":500000000,\"renewalPrincipalValue\":600000000,\"interestHandling\":\"ああああああああああああああああああ\",\"transferDesignationAccount\":\"\",\"maruyuTaxableInterest\":null,\"maruyuTaxableDays\":null,\"remarks\":\"ああああああああああああああああああ\",\"interest2\":\"\",\"period2\":\"\",\"interestAmount2\":null,\"paymentRecord\":\"200\"}]}";
  // 全項目最大桁
  private String json4 = "{\"maturityNotificationId\":\"rid09991003\",\"accountNumber\":\"1234567\",\"issuedDate\":\"2022-01-15\",\"branchName\":\"中央支店あ中央支店あ中央支店あ中央支店あ中央支店あ中央支店あ中央支店あ中央支店あ中央支店あ中央支店あ中央支店あ中央支店あ中央支店あ中央支店あ中央支店あ中央支店あ中央支店あ中央支店あ中央支店あ中央支店あ\",\"branchPhoneNumber\":\"0120-1111-2222\",\"informationLine1\":\"あいうえおかきくけこあいうえおかきくけこあいうえおかきくけこあいうえおかきくけこあいうえおかきくけこあいうえおかきくけこあいうえおかきくけこあいうえおかき\",\"informationLine2\":\"あいうえおかきくけこあいうえおかきくけこあいうえおかきくけこあいうえおかきくけこあいうえおかきくけこあいうえおかきくけこあいうえおかきくけこあいうえおかき\",\"informationLine3\":\"あいうえおかきくけこあいうえおかきくけこあいうえおかきくけこあいうえおかきくけこあいうえおかきくけこあいうえおかきくけこあいうえおかきくけこあいうえおかき\",\"informationLine4\":\"あいうえおかきくけこあいうえおかきくけこあいうえおかきくけこあいうえおかきくけこあいうえおかきくけこあいうえおかきくけこあいうえおかきくけこあいうえおかき\",\"informationLine5\":\"あいうえおかきくけこあいうえおかきくけこあいうえおかきくけこあいうえおかきくけこあいうえおかきくけこあいうえおかきくけこあいうえおかきくけこあいうえおかき\",\"applicantName\":\"あいうえおかきくけこさしすせそたちつてとなにぬねのはひふへほまみむめもあいうえおかきくけこさしすせそたちつてとなにぬねのはひふへほまみむめもあいうえおかきくけこさしすせそたちつてとなにぬねのはひふへほ\",\"list\":[{\"interestPaymentDate\":\"2020-01-01\",\"depositNumber\":\"010\",\"timeDepositType\":\"あああああああああ\",\"handlingType\":\"あいうえおあいうえ\",\"depositAmount\":123456789012,\"interest\":\"12.3456\",\"period\":\"1234\",\"interestAmount\":1234567890,\"taxClass\":\"あああ\",\"nationalTax\":12345678,\"localTax\":12345678,\"deductionPaymentInterest\":1234567890123456,\"renewalPrincipalValue\":123456789012,\"interestHandling\":\"あいうえおあいうえお\",\"transferDesignationAccount\":\"普通-123-1234567\",\"maruyuTaxableInterest\":123456789012,\"maruyuTaxableDays\":1234,\"remarks\":\"あいうえおあいうえおあ\",\"interest2\":\"12.3456\",\"period2\":\"1234\",\"interestAmount2\":1234567890,\"paymentRecord\":\"*\"}]}";
  // 二列表示下段のみ利率****バージョン
  private String json5 = "{\"maturityNotificationId\":\"rid09991003\",\"accountNumber\":\"0123450\",\"issuedDate\":\"2022-01-15\",\"branchName\":\"中央支店\",\"branchPhoneNumber\":\"0120-11-2222\",\"informationLine1\":\"testInfo1\",\"informationLine2\":\"testInfo2\",\"informationLine3\":\"testInfo3\",\"informationLine4\":\"testInfo4\",\"informationLine5\":\"testInfo5\",\"applicantName\":\"あいうえおかきくけこさしすせそたちつてとなにぬねのはひふへほまみむめも\",\"list\":[{\"interestPaymentDate\":\"2020-01-01\",\"depositNumber\":\"010\",\"timeDepositType\":\"ああああああああああああああああああ\",\"handlingType\":\"\",\"depositAmount\":100000000,\"interest\":null,\"period\":\"ああああああああああああああああああ\",\"interestAmount\":200000000,\"taxClass\":\"ああああああああああああああああああ\",\"nationalTax\":300000000,\"localTax\":400000000,\"deductionPaymentInterest\":500000000,\"renewalPrincipalValue\":600000000,\"interestHandling\":\"ああああああああああああああああああ\",\"transferDesignationAccount\":\"\",\"maruyuTaxableInterest\":null,\"maruyuTaxableDays\":null,\"remarks\":\"ああああああああああああああああああ\",\"interest2\":\"\",\"period2\":\"\",\"interestAmount2\":0,\"paymentRecord\":\"200\"}]}";
  // 二列表示下段のみInterestがnull
  private String json6 = "{\"maturityNotificationId\":\"rid09991003\",\"accountNumber\":\"0123450\",\"issuedDate\":\"2022-01-15\",\"branchName\":\"中央支店\",\"branchPhoneNumber\":\"0120-11-2222\",\"informationLine1\":\"testInfo1\",\"informationLine2\":\"testInfo2\",\"informationLine3\":\"testInfo3\",\"informationLine4\":\"testInfo4\",\"informationLine5\":\"testInfo5\",\"applicantName\":\"あいうえおかきくけこさしすせそたちつてとなにぬねのはひふへほまみむめも\",\"list\":[{\"interestPaymentDate\":\"2020-01-01\",\"depositNumber\":\"010\",\"timeDepositType\":\"ああああああああああああああああああ\",\"handlingType\":\"\",\"depositAmount\":100000000,\"interest\":null,\"period\":\"ああああああああああああああああああ\",\"interestAmount\":200000000,\"taxClass\":\"ああああああああああああああああああ\",\"nationalTax\":300000000,\"localTax\":400000000,\"deductionPaymentInterest\":500000000,\"renewalPrincipalValue\":600000000,\"interestHandling\":\"ああああああああああああああああああ\",\"transferDesignationAccount\":\"\",\"maruyuTaxableInterest\":null,\"maruyuTaxableDays\":null,\"remarks\":\"ああああああああああああああああああ\",\"interest2\":\"1.5\",\"period2\":\"\",\"interestAmount2\":0,\"paymentRecord\":\"200\"}]}";
  
  /**
   * 正常系
   */
  @Test
  public void testGetInformationDepositOK() throws Exception {

    List<ReportDefinition> reportDefinitionList = new ArrayList<ReportDefinition>();
    ReportDefinition reportDefinition = new ReportDefinition();

    reportDefinition.setId(1L);
    reportDefinition.setReportFormatFilePath("fullpath");
    reportDefinition.setReportTypeId("11");
    reportDefinition.setReportVersion("1");
    reportDefinition.setStartDate(new Date());
    reportDefinitionList.add(reportDefinition);

    ObjectMapper objectMapper = new ObjectMapper();
    InformationDepositDto informationDepositDto = objectMapper.readValue(json1, InformationDepositDto.class);

    byte[] array = new byte[5];

    // Mock化
    // メソッドの戻り値を設定
    Mockito.when(informationDepositLogic.CallInformationDepositApi(
        ArgumentMatchers.any(),ArgumentMatchers.any(),ArgumentMatchers.any())).thenReturn(informationDepositDto);
    Mockito.when(pdfService.pdfExport(ArgumentMatchers.any(), ArgumentMatchers.any(), ArgumentMatchers.any())).thenReturn(array);
    Mockito.when(storedDataHistoryRepository.save(ArgumentMatchers.any())).thenReturn(true);
    Mockito.when(reportDefinitionRepository.findByStartDateLessThanEqual(ArgumentMatchers.any())).thenReturn(reportDefinitionList);

    // ユーザ識別子
    String userId = "01234567890123456789012345678901";
    // 口座識別子
    String accountId = "01234567890123456789";
    // 帳票識別子
    String reportId = "0123";

    byte[] result = informationDepositService.getInformationDeposit(userId, accountId, reportId);

    Assert.assertArrayEquals(result, array);
  }

  /**
   * 正常系 二列表示下段のみ(Interest2がnull)
   */
  @Test
  public void testGetInfomationDepositOnlyLowRow() throws Exception {

    List<ReportDefinition> reportDefinitionList = new ArrayList<ReportDefinition>();
    ReportDefinition reportDefinition = new ReportDefinition();

    reportDefinition.setId(1L);
    reportDefinition.setReportFormatFilePath("fullpath");
    reportDefinition.setReportTypeId("11");
    reportDefinition.setReportVersion("1");
    reportDefinition.setStartDate(new Date());
    reportDefinitionList.add(reportDefinition);

    ObjectMapper objectMapper = new ObjectMapper();
    InformationDepositDto informationDepositDto = objectMapper.readValue(json2, InformationDepositDto.class);

    byte[] array = new byte[5];

    // Mock化
    // メソッドの戻り値を設定
    Mockito.when(informationDepositLogic.CallInformationDepositApi(
        ArgumentMatchers.any(),ArgumentMatchers.any(),ArgumentMatchers.any())).thenReturn(informationDepositDto);
    Mockito.when(pdfService.pdfExport(ArgumentMatchers.any(), ArgumentMatchers.any(), ArgumentMatchers.any())).thenReturn(array);
    Mockito.when(storedDataHistoryRepository.save(ArgumentMatchers.any())).thenReturn(true);
    Mockito.when(reportDefinitionRepository.findByStartDateLessThanEqual(ArgumentMatchers.any())).thenReturn(reportDefinitionList);

    // ユーザ識別子
    String userId = "01234567890123456789012345678901";
    // 口座識別子
    String accountId = "01234567890123456789";
    // 帳票識別子
    String reportId = "0123";

    byte[] result = informationDepositService.getInformationDeposit(userId, accountId, reportId);

    Assert.assertArrayEquals(result, array);
  }
  
  /**
   * 正常系 二列表示下段のみ
   */
  @Test
  public void testGetInfomationDepositOnlyLowRowNull() throws Exception {

    List<ReportDefinition> reportDefinitionList = new ArrayList<ReportDefinition>();
    ReportDefinition reportDefinition = new ReportDefinition();

    reportDefinition.setId(1L);
    reportDefinition.setReportFormatFilePath("fullpath");
    reportDefinition.setReportTypeId("11");
    reportDefinition.setReportVersion("1");
    reportDefinition.setStartDate(new Date());
    reportDefinitionList.add(reportDefinition);

    ObjectMapper objectMapper = new ObjectMapper();
    InformationDepositDto informationDepositDto = objectMapper.readValue(json3, InformationDepositDto.class);

    byte[] array = new byte[5];

    // Mock化
    // メソッドの戻り値を設定
    Mockito.when(informationDepositLogic.CallInformationDepositApi(
        ArgumentMatchers.any(),ArgumentMatchers.any(),ArgumentMatchers.any())).thenReturn(informationDepositDto);
    Mockito.when(pdfService.pdfExport(ArgumentMatchers.any(), ArgumentMatchers.any(), ArgumentMatchers.any())).thenReturn(array);
    Mockito.when(storedDataHistoryRepository.save(ArgumentMatchers.any())).thenReturn(true);
    Mockito.when(reportDefinitionRepository.findByStartDateLessThanEqual(ArgumentMatchers.any())).thenReturn(reportDefinitionList);

    // ユーザ識別子
    String userId = "01234567890123456789012345678901";
    // 口座識別子
    String accountId = "01234567890123456789";
    // 帳票識別子
    String reportId = "0123";

    byte[] result = informationDepositService.getInformationDeposit(userId, accountId, reportId);

    Assert.assertArrayEquals(result, array);
  }

  /**
   * 正常系 最大桁数
   */
  @Test
  public void testGetInfomationDepositMax() throws Exception {

    List<ReportDefinition> reportDefinitionList = new ArrayList<ReportDefinition>();
    ReportDefinition reportDefinition = new ReportDefinition();

    reportDefinition.setId(1L);
    reportDefinition.setReportFormatFilePath("fullpath");
    reportDefinition.setReportTypeId("11");
    reportDefinition.setReportVersion("1");
    reportDefinition.setStartDate(new Date());
    reportDefinitionList.add(reportDefinition);

    ObjectMapper objectMapper = new ObjectMapper();
    InformationDepositDto informationDepositDto = objectMapper.readValue(json4, InformationDepositDto.class);

    byte[] array = new byte[5];

    // Mock化
    // メソッドの戻り値を設定
    Mockito.when(informationDepositLogic.CallInformationDepositApi(
        ArgumentMatchers.any(),ArgumentMatchers.any(),ArgumentMatchers.any())).thenReturn(informationDepositDto);
    Mockito.when(pdfService.pdfExport(ArgumentMatchers.any(), ArgumentMatchers.any(), ArgumentMatchers.any())).thenReturn(array);
    Mockito.when(storedDataHistoryRepository.save(ArgumentMatchers.any())).thenReturn(true);
    Mockito.when(reportDefinitionRepository.findByStartDateLessThanEqual(ArgumentMatchers.any())).thenReturn(reportDefinitionList);

    // ユーザ識別子
    String userId = "01234567890123456789012345678901";
    // 口座識別子
    String accountId = "01234567890123456789";
    // 帳票識別子
    String reportId = "0123";

    byte[] result = informationDepositService.getInformationDeposit(userId, accountId, reportId);

    Assert.assertArrayEquals(result, array);
  }

  /**
   * 利率または中間利払利率がnull
   */
  @Test
  public void testGetInfomationDepositInterest2Null() throws Exception {

    List<ReportDefinition> reportDefinitionList = new ArrayList<ReportDefinition>();
    ReportDefinition reportDefinition = new ReportDefinition();

    reportDefinition.setId(1L);
    reportDefinition.setReportFormatFilePath("fullpath");
    reportDefinition.setReportTypeId("11");
    reportDefinition.setReportVersion("1");
    reportDefinition.setStartDate(new Date());
    reportDefinitionList.add(reportDefinition);

    ObjectMapper objectMapper = new ObjectMapper();
    InformationDepositDto informationDepositDto = objectMapper.readValue(json5, InformationDepositDto.class);

    byte[] array = new byte[5];

    // Mock化
    // メソッドの戻り値を設定
    Mockito.when(informationDepositLogic.CallInformationDepositApi(
        ArgumentMatchers.any(),ArgumentMatchers.any(),ArgumentMatchers.any())).thenReturn(informationDepositDto);
    Mockito.when(pdfService.pdfExport(ArgumentMatchers.any(), ArgumentMatchers.any(), ArgumentMatchers.any())).thenReturn(array);
    Mockito.when(storedDataHistoryRepository.save(ArgumentMatchers.any())).thenReturn(true);
    Mockito.when(reportDefinitionRepository.findByStartDateLessThanEqual(ArgumentMatchers.any())).thenReturn(reportDefinitionList);

    // ユーザ識別子
    String userId = "01234567890123456789012345678901";
    // 口座識別子
    String accountId = "01234567890123456789";
    // 帳票識別子
    String reportId = "0123";

    byte[] result = informationDepositService.getInformationDeposit(userId, accountId, reportId);

    Assert.assertArrayEquals(result, array);
  }

  /**
   * 利率または中間利払利率(Interest)がnull
   */
  @Test
  public void testGetInfomationDepositInterestNull() throws Exception {

    List<ReportDefinition> reportDefinitionList = new ArrayList<ReportDefinition>();
    ReportDefinition reportDefinition = new ReportDefinition();

    reportDefinition.setId(1L);
    reportDefinition.setReportFormatFilePath("fullpath");
    reportDefinition.setReportTypeId("11");
    reportDefinition.setReportVersion("1");
    reportDefinition.setStartDate(new Date());
    reportDefinitionList.add(reportDefinition);

    ObjectMapper objectMapper = new ObjectMapper();
    InformationDepositDto informationDepositDto = objectMapper.readValue(json6, InformationDepositDto.class);

    byte[] array = new byte[5];

    // Mock化
    // メソッドの戻り値を設定
    Mockito.when(informationDepositLogic.CallInformationDepositApi(
        ArgumentMatchers.any(),ArgumentMatchers.any(),ArgumentMatchers.any())).thenReturn(informationDepositDto);
    Mockito.when(pdfService.pdfExport(ArgumentMatchers.any(), ArgumentMatchers.any(), ArgumentMatchers.any())).thenReturn(array);
    Mockito.when(storedDataHistoryRepository.save(ArgumentMatchers.any())).thenReturn(true);
    Mockito.when(reportDefinitionRepository.findByStartDateLessThanEqual(ArgumentMatchers.any())).thenReturn(reportDefinitionList);

    // ユーザ識別子
    String userId = "01234567890123456789012345678901";
    // 口座識別子
    String accountId = "01234567890123456789";
    // 帳票識別子
    String reportId = "0123";

    byte[] result = informationDepositService.getInformationDeposit(userId, accountId, reportId);

    Assert.assertArrayEquals(result, array);
  }
}
