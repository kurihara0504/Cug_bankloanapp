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
import com.moneyforward.cug_bankbookbase.dto.InformationRepaymentDto;
import com.moneyforward.cug_bankbookbase.logic.InformationRepaymentLogic;

/**
 * ご返済のご案内取得サービス　テストクラス.
 */
@ContextConfiguration
@RunWith(SpringRunner.class)
@SpringBootTest
public class InformationRepaymentServiceTest {

  /** テスト対象クラス. */
  @Autowired
  private InformationRepaymentService informationRepaymentService;

  /** テスト実行時の共通処理の設定. */
  @Rule
  public ExpectedException exception = ExpectedException.none();

  /** Mock化対象クラス. */
  @Mock
  private InformationRepaymentLogic informationRepaymentLogic;
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
    ReflectionTestUtils.setField(informationRepaymentService, "informationRepaymentLogic", informationRepaymentLogic);
    ReflectionTestUtils.setField(informationRepaymentService, "pdfService", pdfService);
  }

  /** 最後処理. */
  @After
  public void after() {
    // 初期化
    Mockito.reset(informationRepaymentLogic);
    Mockito.reset(pdfService);
    Mockito.reset(storedDataHistoryRepository);
    Mockito.reset(reportDefinitionRepository);
    Mockito.reset(resource);
  }
  
  // 全項目有
  private String json1 = "{\"repaymentNotificationsId\":\"12345\",\"displayDocumentName\":\"ご返済のご案内\",\"displayProductName\":\"ご返済のご案内\",\"applicantName\":\"rid09991003\",\"borrowingDate\":\"2023-01-02\",\"borrowingAmount\":\"123456789\",\"monthlyBorrowingAmount\":\"123456789\",\"increasedBorrowingAmount\":\"123456789\",\"interestRate\":\"0.12345\",\"subject\":\"科目名\",\"handlingNumber\":\"123\",\"notificationsClass\":\"普通\",\"maturityDate\":\"2023-01-02\",\"repaymentCycle\":\"11\",\"repaymentDate\":\"22\",\"increasedRepaymentMonth1\":\"01\",\"increasedRepaymentMonth2\":\"12\",\"interestRateEndDate\":\"2023-01-02\",\"newInterestRate\":\"0.22222\",\"transferBranchNumber\":\"123\",\"transferSubject\":\"普通\",\"transferAccountNumber\":\"1234567\",\"nextNotifications\":\"2023-11\",\"loanName\":\"サステナブルローン\",\"remarks\":\"備考\",\"guidance1\":\"ああああ\",\"guidance2\":\"いいいい\",\"explanation1\":\"説明１\",\"explanation2\":\"説明２\",\"explanation3\":\"説明３\",\"explanation4\":\"説明４\",\"explanation5\":\"説明５\",\"issuedDate\":\"2023-01-02\",\"branchName\":\"中央支店\",\"branchPhoneNumber\":\"0000-1111-2222\",\"details\":[{\"repaymentDate\":\"2023-01-02\",\"principalAndInterest\":\"11111111\",\"monthlyInterestCalcDate\":\"22222222\",\"monthlyInterest\":\"33333333\",\"monthlyPrincipal\":\"44444444\",\"increasedInterest\":\"55555555\",\"increasedPrincipal\":\"66666666\",\"afterRepaymentBalance\":\"77777777\",\"accruedInterest\":\"88888888\"}]}";
  // 必須のみ
  private String json2 = "{\"repaymentNotificationsId\":\"\",\"displayDocumentName\":\"\",\"displayProductName\":\"\",\"applicantName\":\"\",\"borrowingDate\":\"\",\"borrowingAmount\":null,\"monthlyBorrowingAmount\":null,\"increasedBorrowingAmount\":null,\"interestRate\":\"\",\"subject\":\"\",\"handlingNumber\":null,\"notificationsClass\":\"\",\"maturityDate\":\"2023-01-02\",\"repaymentCycle\":null,\"repaymentDate\":null,\"increasedRepaymentMonth1\":null,\"increasedRepaymentMonth2\":null,\"interestRateEndDate\":\"\",\"newInterestRate\":\"\",\"transferBranchNumber\":\"\",\"transferSubject\":\"\",\"transferAccountNumber\":\"\",\"nextNotifications\":\"\",\"loanName\":\"\",\"remarks\":\"\",\"guidance1\":\"\",\"guidance2\":\"\",\"explanation1\":\"\",\"explanation2\":\"\",\"explanation3\":\"\",\"explanation4\":\"\",\"explanation5\":\"\",\"issuedDate\":\"2023-01-02\",\"branchName\":\"\",\"branchPhoneNumber\":\"\",\"details\":[{\"repaymentDate\":\"\",\"principalAndInterest\":null,\"monthlyInterestCalcDate\":null,\"monthlyInterest\":null,\"monthlyPrincipal\":null,\"increasedInterest\":null,\"increasedPrincipal\":null,\"afterRepaymentBalance\":null,\"accruedInterest\":null}]}";
  // 全項目最大桁
  private String json3 = "{\"repaymentNotificationsId\":\"1234567890123456789\",\"displayDocumentName\":\"ご返済のご案内あいうえおあいうえおあいうえおあいうえおあいう\",\"displayProductName\":\"ご返済のご案内あいうえおあいうえおあいうえおあいうえおあいう\",\"applicantName\":\"あいうえおあいうえおあいうえおあいうえおあいうえおあいうえおあいうえおあいうえおあいうえおあいうえおあいうえおあいうえおあいうえおあいうえおあいうえおあいうえおあいうえおあいうえおあいうえおあいうえお\",\"borrowingDate\":\"2023-01-02\",\"borrowingAmount\":\"123456789012\",\"monthlyBorrowingAmount\":\"12345678901\",\"increasedBorrowingAmount\":\"12345678901\",\"interestRate\":\"12.12345\",\"subject\":\"科目名\",\"handlingNumber\":\"123456\",\"notificationsClass\":\"普通\",\"maturityDate\":\"2023-01-02\",\"repaymentCycle\":\"11\",\"repaymentDate\":\"22\",\"increasedRepaymentMonth1\":\"01\",\"increasedRepaymentMonth2\":\"12\",\"interestRateEndDate\":\"2023-01-02\",\"newInterestRate\":\"12.12345\",\"transferBranchNumber\":\"123\",\"transferSubject\":\"普通\",\"transferAccountNumber\":\"1234567\",\"nextNotifications\":\"2023-11\",\"loanName\":\"サステナブルローン\",\"remarks\":\"備考\",\"guidance1\":\"ああああ\",\"guidance2\":\"いいいい\",\"explanation1\":\"説明１あいうえおあいうえおあいうえおあいうえおあいうえおあいうえおあいうえおあいうえおあいうえおあいうえおあいうえおあいうえおあいうえおあいうえおあいうえ\",\"explanation2\":\"説明２あいうえおあいうえおあいうえおあいうえおあいうえおあいうえおあいうえおあいうえおあいうえおあいうえおあいうえおあいうえおあいうえおあいうえおあいうえ\",\"explanation3\":\"説明３あいうえおあいうえおあいうえおあいうえおあいうえおあいうえおあいうえおあいうえおあいうえおあいうえおあいうえおあいうえおあいうえおあいうえおあいうえ\",\"explanation4\":\"説明４あいうえおあいうえおあいうえおあいうえおあいうえおあいうえおあいうえおあいうえおあいうえおあいうえおあいうえおあいうえおあいうえおあいうえおあいうえ\",\"explanation5\":\"説明５あいうえおあいうえおあいうえおあいうえおあいうえおあいうえおあいうえおあいうえおあいうえおあいうえおあいうえおあいうえおあいうえおあいうえおあいうえ\",\"issuedDate\":\"2023-01-02\",\"branchName\":\"中央支店あいうえおあいうえおあいうえおあいうえおあいうえおあいうえおあいうえおあいうえおあいうえおあいうえおあいうえおあいうえおあいうえおあいうえおあいうえおあいうえおあいうえおあいうえおあいうえおあいうえおあいうえおあいうえおあいうえおあいうえおあいうえおあいうえおあいうえおあいうえおあいうえおあいうえおあいうえおあいうえおあいうえおあいうえおあいうえおあいうえおあいうえおあいうえおあいうえおあ\",\"branchPhoneNumber\":\"0000-11111-22222\",\"details\":[{\"repaymentDate\":\"2023-01-02\",\"principalAndInterest\":\"111111111111\",\"monthlyInterestCalcDate\":\"22222222222\",\"monthlyInterest\":\"33333333333\",\"monthlyPrincipal\":\"44444444444\",\"increasedInterest\":\"55555555555\",\"increasedPrincipal\":\"66666666666\",\"afterRepaymentBalance\":\"77777777777\",\"accruedInterest\":\"88888888888\"}]}";
  // 増額分支払月分岐パターン
  private String json4 = "{\"repaymentNotificationsId\":\"12345\",\"displayDocumentName\":\"ご返済のご案内\",\"displayProductName\":\"ご返済のご案内\",\"applicantName\":\"rid09991003\",\"borrowingDate\":\"2023-01-02\",\"borrowingAmount\":\"123456789\",\"monthlyBorrowingAmount\":\"123456789\",\"increasedBorrowingAmount\":\"123456789\",\"interestRate\":\"0.12345\",\"subject\":\"科目名\",\"handlingNumber\":\"123\",\"notificationsClass\":\"普通\",\"maturityDate\":\"2023-01-02\",\"repaymentCycle\":\"11\",\"repaymentDate\":null,\"increasedRepaymentMonth1\":\"01\",\"increasedRepaymentMonth2\":null,\"interestRateEndDate\":\"2023-01-02\",\"newInterestRate\":\"0.22222\",\"transferBranchNumber\":\"123\",\"transferSubject\":\"普通\",\"transferAccountNumber\":\"1234567\",\"nextNotifications\":\"2023-11\",\"loanName\":\"サステナブルローン\",\"remarks\":\"備考\",\"guidance1\":\"ああああ\",\"guidance2\":\"いいいい\",\"explanation1\":\"説明１\",\"explanation2\":\"説明２\",\"explanation3\":\"説明３\",\"explanation4\":\"説明４\",\"explanation5\":\"説明５\",\"issuedDate\":\"2023-01-02\",\"branchName\":\"中央支店\",\"branchPhoneNumber\":\"0000-1111-2222\",\"details\":[{\"repaymentDate\":\"2023-01-02\",\"principalAndInterest\":\"11111111\",\"monthlyInterestCalcDate\":\"22222222\",\"monthlyInterest\":\"33333333\",\"monthlyPrincipal\":\"44444444\",\"increasedInterest\":\"55555555\",\"increasedPrincipal\":\"66666666\",\"afterRepaymentBalance\":\"77777777\",\"accruedInterest\":\"88888888\"}]}";
  //増額分支払月分岐パターン2
  private String json6 = "{\"repaymentNotificationsId\":\"12345\",\"displayDocumentName\":\"ご返済のご案内\",\"displayProductName\":\"ご返済のご案内\",\"applicantName\":\"rid09991003\",\"borrowingDate\":\"2023-01-02\",\"borrowingAmount\":\"123456789\",\"monthlyBorrowingAmount\":\"123456789\",\"increasedBorrowingAmount\":\"123456789\",\"interestRate\":\"0.12345\",\"subject\":\"科目名\",\"handlingNumber\":\"123\",\"notificationsClass\":\"普通\",\"maturityDate\":\"2023-01-02\",\"repaymentCycle\":\"11\",\"repaymentDate\":null,\"increasedRepaymentMonth1\":null,\"increasedRepaymentMonth2\":\"06\",\"interestRateEndDate\":\"2023-01-02\",\"newInterestRate\":\"0.22222\",\"transferBranchNumber\":\"123\",\"transferSubject\":\"普通\",\"transferAccountNumber\":\"1234567\",\"nextNotifications\":\"2023-11\",\"loanName\":\"サステナブルローン\",\"remarks\":\"備考\",\"guidance1\":\"ああああ\",\"guidance2\":\"いいいい\",\"explanation1\":\"説明１\",\"explanation2\":\"説明２\",\"explanation3\":\"説明３\",\"explanation4\":\"説明４\",\"explanation5\":\"説明５\",\"issuedDate\":\"2023-01-02\",\"branchName\":\"中央支店\",\"branchPhoneNumber\":\"0000-1111-2222\",\"details\":[{\"repaymentDate\":\"2023-01-02\",\"principalAndInterest\":\"11111111\",\"monthlyInterestCalcDate\":\"22222222\",\"monthlyInterest\":\"33333333\",\"monthlyPrincipal\":\"44444444\",\"increasedInterest\":\"55555555\",\"increasedPrincipal\":\"66666666\",\"afterRepaymentBalance\":\"77777777\",\"accruedInterest\":\"88888888\"}]}";
  // 返済日（周期）null分岐パターン
  private String json5 = "{\"repaymentNotificationsId\":\"12345\",\"displayDocumentName\":\"ご返済のご案内\",\"displayProductName\":\"ご返済のご案内\",\"applicantName\":\"rid09991003\",\"borrowingDate\":\"2023-01-02\",\"borrowingAmount\":\"123456789\",\"monthlyBorrowingAmount\":\"123456789\",\"increasedBorrowingAmount\":\"123456789\",\"interestRate\":\"0.12345\",\"subject\":\"科目名\",\"handlingNumber\":\"123\",\"notificationsClass\":\"普通\",\"maturityDate\":\"2023-01-02\",\"repaymentCycle\":null,\"repaymentDate\":22,\"increasedRepaymentMonth1\":\"01\",\"increasedRepaymentMonth2\":null,\"interestRateEndDate\":\"2023-01-02\",\"newInterestRate\":\"0.22222\",\"transferBranchNumber\":\"123\",\"transferSubject\":\"普通\",\"transferAccountNumber\":\"1234567\",\"nextNotifications\":\"2023-11\",\"loanName\":\"サステナブルローン\",\"remarks\":\"備考\",\"guidance1\":\"ああああ\",\"guidance2\":\"いいいい\",\"explanation1\":\"説明１\",\"explanation2\":\"説明２\",\"explanation3\":\"説明３\",\"explanation4\":\"説明４\",\"explanation5\":\"説明５\",\"issuedDate\":\"2023-01-02\",\"branchName\":\"中央支店\",\"branchPhoneNumber\":\"0000-1111-2222\",\"details\":[{\"repaymentDate\":\"2023-01-02\",\"principalAndInterest\":\"11111111\",\"monthlyInterestCalcDate\":\"22222222\",\"monthlyInterest\":\"33333333\",\"monthlyPrincipal\":\"44444444\",\"increasedInterest\":\"55555555\",\"increasedPrincipal\":\"66666666\",\"afterRepaymentBalance\":\"77777777\",\"accruedInterest\":\"88888888\"}]}";

  /**
   * 正常系
   */
  @Test
  public void testGetInfomationRepaymentOK() throws Exception {

    List<ReportDefinition> reportDefinitionList = new ArrayList<ReportDefinition>();
    ReportDefinition reportDefinition = new ReportDefinition();

    reportDefinition.setId(1L);
    reportDefinition.setReportFormatFilePath("fullpath");
    reportDefinition.setReportTypeId("12");
    reportDefinition.setReportVersion("1");
    reportDefinition.setStartDate(new Date());
    reportDefinitionList.add(reportDefinition);

    ObjectMapper objectMapper = new ObjectMapper();
    InformationRepaymentDto informationRepaymentDto = objectMapper.readValue(json1, InformationRepaymentDto.class);

    byte[] array = new byte[5];

    // Mock化
    // メソッドの戻り値を設定
    Mockito.when(informationRepaymentLogic.callInformationRepaymentApi(
        ArgumentMatchers.any(),ArgumentMatchers.any(),ArgumentMatchers.any())).thenReturn(informationRepaymentDto);
    Mockito.when(pdfService.pdfExport(ArgumentMatchers.any(), ArgumentMatchers.any(), ArgumentMatchers.any())).thenReturn(array);
    Mockito.when(storedDataHistoryRepository.save(ArgumentMatchers.any())).thenReturn(true);
    Mockito.when(reportDefinitionRepository.findByStartDateLessThanEqual(ArgumentMatchers.any())).thenReturn(reportDefinitionList);

    // ユーザ識別子
    String userId = "01234567890123456789012345678901";
    // 口座識別子
    String accountId = "01234567890123456789";
    // 帳票識別子
    String reportId = "0123";
    // 科目コード
    String type = "101";

    byte[] result = informationRepaymentService.getInformationRepayment(userId, accountId, reportId, type);

    Assert.assertArrayEquals(result, array);
  }

  /**
   * 必須項目のみ
   */
  @Test
  public void testGetInfomationRepaymentOnlyRequired() throws Exception {

    List<ReportDefinition> reportDefinitionList = new ArrayList<ReportDefinition>();
    ReportDefinition reportDefinition = new ReportDefinition();

    reportDefinition.setId(1L);
    reportDefinition.setReportFormatFilePath("fullpath");
    reportDefinition.setReportTypeId("12");
    reportDefinition.setReportVersion("1");
    reportDefinition.setStartDate(new Date());
    reportDefinitionList.add(reportDefinition);

    ObjectMapper objectMapper = new ObjectMapper();
    InformationRepaymentDto informationRepaymentDto = objectMapper.readValue(json2, InformationRepaymentDto.class);

    byte[] array = new byte[5];

    // Mock化
    // メソッドの戻り値を設定
    Mockito.when(informationRepaymentLogic.callInformationRepaymentApi(
        ArgumentMatchers.any(),ArgumentMatchers.any(),ArgumentMatchers.any())).thenReturn(informationRepaymentDto);
    Mockito.when(pdfService.pdfExport(ArgumentMatchers.any(), ArgumentMatchers.any(), ArgumentMatchers.any())).thenReturn(array);
    Mockito.when(storedDataHistoryRepository.save(ArgumentMatchers.any())).thenReturn(true);
    Mockito.when(reportDefinitionRepository.findByStartDateLessThanEqual(ArgumentMatchers.any())).thenReturn(reportDefinitionList);

    // ユーザ識別子
    String userId = "01234567890123456789012345678901";
    // 口座識別子
    String accountId = "01234567890123456789";
    // 帳票識別子
    String reportId = "0123";
    // 科目コード
    String type = "101";

    byte[] result = informationRepaymentService.getInformationRepayment(userId, accountId, reportId, type);

    Assert.assertArrayEquals(result, array);
  }
  
  /**
   * 全項目最大文字数
   */
  @Test
  public void testGetInfomationRepaymentMax() throws Exception {

    List<ReportDefinition> reportDefinitionList = new ArrayList<ReportDefinition>();
    ReportDefinition reportDefinition = new ReportDefinition();

    reportDefinition.setId(1L);
    reportDefinition.setReportFormatFilePath("fullpath");
    reportDefinition.setReportTypeId("12");
    reportDefinition.setReportVersion("1");
    reportDefinition.setStartDate(new Date());
    reportDefinitionList.add(reportDefinition);

    ObjectMapper objectMapper = new ObjectMapper();
    InformationRepaymentDto informationRepaymentDto = objectMapper.readValue(json3, InformationRepaymentDto.class);

    byte[] array = new byte[5];

    // Mock化
    // メソッドの戻り値を設定
    Mockito.when(informationRepaymentLogic.callInformationRepaymentApi(
        ArgumentMatchers.any(),ArgumentMatchers.any(),ArgumentMatchers.any())).thenReturn(informationRepaymentDto);
    Mockito.when(pdfService.pdfExport(ArgumentMatchers.any(), ArgumentMatchers.any(), ArgumentMatchers.any())).thenReturn(array);
    Mockito.when(storedDataHistoryRepository.save(ArgumentMatchers.any())).thenReturn(true);
    Mockito.when(reportDefinitionRepository.findByStartDateLessThanEqual(ArgumentMatchers.any())).thenReturn(reportDefinitionList);

    // ユーザ識別子
    String userId = "01234567890123456789012345678901";
    // 口座識別子
    String accountId = "01234567890123456789";
    // 帳票識別子
    String reportId = "0123";
    // 科目コード
    String type = "101";

    byte[] result = informationRepaymentService.getInformationRepayment(userId, accountId, reportId, type);

    Assert.assertArrayEquals(result, array);
  }

  /**
   * 増額分支払月分岐パターン
   */
  @Test
  public void testGetInfomationRepaymentIncreasedRepaymentMonthPattern() throws Exception {

    List<ReportDefinition> reportDefinitionList = new ArrayList<ReportDefinition>();
    ReportDefinition reportDefinition = new ReportDefinition();

    reportDefinition.setId(1L);
    reportDefinition.setReportFormatFilePath("fullpath");
    reportDefinition.setReportTypeId("12");
    reportDefinition.setReportVersion("1");
    reportDefinition.setStartDate(new Date());
    reportDefinitionList.add(reportDefinition);

    ObjectMapper objectMapper = new ObjectMapper();
    InformationRepaymentDto informationRepaymentDto = objectMapper.readValue(json4, InformationRepaymentDto.class);

    byte[] array = new byte[5];

    // Mock化
    // メソッドの戻り値を設定
    Mockito.when(informationRepaymentLogic.callInformationRepaymentApi(
        ArgumentMatchers.any(),ArgumentMatchers.any(),ArgumentMatchers.any())).thenReturn(informationRepaymentDto);
    Mockito.when(pdfService.pdfExport(ArgumentMatchers.any(), ArgumentMatchers.any(), ArgumentMatchers.any())).thenReturn(array);
    Mockito.when(storedDataHistoryRepository.save(ArgumentMatchers.any())).thenReturn(true);
    Mockito.when(reportDefinitionRepository.findByStartDateLessThanEqual(ArgumentMatchers.any())).thenReturn(reportDefinitionList);

    // ユーザ識別子
    String userId = "01234567890123456789012345678901";
    // 口座識別子
    String accountId = "01234567890123456789";
    // 帳票識別子
    String reportId = "0123";
    // 科目コード
    String type = "101";

    byte[] result = informationRepaymentService.getInformationRepayment(userId, accountId, reportId, type);

    Assert.assertArrayEquals(result, array);
  }
  /**
   * 増額分支払月分岐パターン2
   */
  @Test
  public void testGetInfomationRepaymentIncreasedRepaymentMonthPattern2() throws Exception {

    List<ReportDefinition> reportDefinitionList = new ArrayList<ReportDefinition>();
    ReportDefinition reportDefinition = new ReportDefinition();

    reportDefinition.setId(1L);
    reportDefinition.setReportFormatFilePath("fullpath");
    reportDefinition.setReportTypeId("12");
    reportDefinition.setReportVersion("1");
    reportDefinition.setStartDate(new Date());
    reportDefinitionList.add(reportDefinition);

    ObjectMapper objectMapper = new ObjectMapper();
    InformationRepaymentDto informationRepaymentDto = objectMapper.readValue(json6, InformationRepaymentDto.class);

    byte[] array = new byte[5];

    // Mock化
    // メソッドの戻り値を設定
    Mockito.when(informationRepaymentLogic.callInformationRepaymentApi(
        ArgumentMatchers.any(),ArgumentMatchers.any(),ArgumentMatchers.any())).thenReturn(informationRepaymentDto);
    Mockito.when(pdfService.pdfExport(ArgumentMatchers.any(), ArgumentMatchers.any(), ArgumentMatchers.any())).thenReturn(array);
    Mockito.when(storedDataHistoryRepository.save(ArgumentMatchers.any())).thenReturn(true);
    Mockito.when(reportDefinitionRepository.findByStartDateLessThanEqual(ArgumentMatchers.any())).thenReturn(reportDefinitionList);

    // ユーザ識別子
    String userId = "01234567890123456789012345678901";
    // 口座識別子
    String accountId = "01234567890123456789";
    // 帳票識別子
    String reportId = "0123";
    // 科目コード
    String type = "101";

    byte[] result = informationRepaymentService.getInformationRepayment(userId, accountId, reportId, type);

    Assert.assertArrayEquals(result, array);
  }

  /**
   * 返済日分岐パターン
   */
  @Test
  public void testGetInfomationRepaymentRepaymentCycleNullPattern() throws Exception {

    List<ReportDefinition> reportDefinitionList = new ArrayList<ReportDefinition>();
    ReportDefinition reportDefinition = new ReportDefinition();

    reportDefinition.setId(1L);
    reportDefinition.setReportFormatFilePath("fullpath");
    reportDefinition.setReportTypeId("12");
    reportDefinition.setReportVersion("1");
    reportDefinition.setStartDate(new Date());
    reportDefinitionList.add(reportDefinition);

    ObjectMapper objectMapper = new ObjectMapper();
    InformationRepaymentDto informationRepaymentDto = objectMapper.readValue(json5, InformationRepaymentDto.class);

    byte[] array = new byte[5];

    // Mock化
    // メソッドの戻り値を設定
    Mockito.when(informationRepaymentLogic.callInformationRepaymentApi(
        ArgumentMatchers.any(),ArgumentMatchers.any(),ArgumentMatchers.any())).thenReturn(informationRepaymentDto);
    Mockito.when(pdfService.pdfExport(ArgumentMatchers.any(), ArgumentMatchers.any(), ArgumentMatchers.any())).thenReturn(array);
    Mockito.when(storedDataHistoryRepository.save(ArgumentMatchers.any())).thenReturn(true);
    Mockito.when(reportDefinitionRepository.findByStartDateLessThanEqual(ArgumentMatchers.any())).thenReturn(reportDefinitionList);

    // ユーザ識別子
    String userId = "01234567890123456789012345678901";
    // 口座識別子
    String accountId = "01234567890123456789";
    // 帳票識別子
    String reportId = "0123";
    // 科目コード
    String type = "101";

    byte[] result = informationRepaymentService.getInformationRepayment(userId, accountId, reportId, type);

    Assert.assertArrayEquals(result, array);
  }
}
