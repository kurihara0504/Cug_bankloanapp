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
import com.moneyforward.cug_bankbookbase.dto.NoticeFloatingRateDto;
import com.moneyforward.cug_bankbookbase.logic.NoticeFloatingRateApiLogic;

/**
 * 変動金利定期預金のお知らせ取得サービス　テストクラス.
 */
@ContextConfiguration
@RunWith(SpringRunner.class)
@SpringBootTest
public class NoticeFloatingRateServiceTest {

  /** テスト対象クラス. */
  @Autowired
  private NoticeFloatingRateService noticeFloatingRateService;

  /** テスト実行時の共通処理の設定. */
  @Rule
  public ExpectedException exception = ExpectedException.none();

  /** Mock化対象クラス. */
  @Mock
  private NoticeFloatingRateApiLogic noticeFloatingRateApiLogic;
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
    ReflectionTestUtils.setField(noticeFloatingRateService, "noticeFloatingRateApiLogic", noticeFloatingRateApiLogic);
    ReflectionTestUtils.setField(noticeFloatingRateService, "pdfService", pdfService);
  }

  /** 最後処理. */
  @After
  public void after() {
    // 初期化
    Mockito.reset(noticeFloatingRateApiLogic);
    Mockito.reset(pdfService);
    Mockito.reset(storedDataHistoryRepository);
    Mockito.reset(reportDefinitionRepository);
    Mockito.reset(resource);
  }

  // 変動金利定期預金のお知らせ　必須項目：有 非必須項目:有
  private String NoticeFloatingRateDtoJson1 = "{\"noticeFloatingInterestRateTimeDepositId\": \"rid0999002\",\"accountNumber\": \"0012345\",\"displayDocumentName\":\"変動金利定期預金のご案内\",\"displayProductName\":\"定期預金\",\"applicantName\": \"中国　太郎\",\"depositDate\": \"2023-01-01\",\"maturityDate\": \"2023-01-01\",\"depositNumber\": \"123\",\"depositAmount\": \"1234567890\",\"taxClass\": \"総合課税\",\"simpleOrCompoundInterest\": \"複利型\",\"periodDays\": \"20\",\"interestRate\": \"12.34\",\"interimInterestPaymentDate\": \"2023-01-01\",\"interimPaymentInterestRate\": \"12.34\",\"interimPaymentInterest\": 123456789,\"nationalTax\": 123456789,\"localTax\": 123456789,\"deductionInterimPaymentInterest\": 123456789012345,\"newInterestRateStartDate\": \"2023-01-01\",\"newInterestRate\": \"12.3456\",\"transferDesignationAccount\": \"普通-101-1234567\",\"standardBenchmarkInterestRate\": \"12.3455\",\"additionalInterestRate\": \"12.3455\",\"applyNewInterestRate\": \"12.3455\",\"taxableInterest\": 1234567,\"taxableDays\": \"20\",\"issuedDate\": \"2023-01-02\",\"branchName\": \"中央支店\",\"branchPhoneNumber\": \"0000-1111-2222\"}";
  // 変動金利定期預金のお知らせ　必須項目：有 非必須項目:無（null）
  private String NoticeFloatingRateDtoJson2 = "{\"noticeFloatingInterestRateTimeDepositId\":  \"rid0999002\",\"accountNumber\": null,\"displayDocumentName\":null,\"displayProductName\":null,\"applicantName\": null,\"depositDate\": null,\"maturityDate\": null,\"depositNumber\": null,\"depositAmount\": null,\"taxClass\": null,\"simpleOrCompoundInterest\": null,\"periodDays\": null,\"interestRate\":null,\"interimInterestPaymentDate\": null,\"interimPaymentInterestRate\": null,\"interimPaymentInterest\": null,\"nationalTax\": null,\"localTax\": null,\"deductionInterimPaymentInterest\": null,\"newInterestRateStartDate\": null,\"newInterestRate\": null,\"transferDesignationAccount\": null,\"standardBenchmarkInterestRate\":null,\"additionalInterestRate\": null,\"applyNewInterestRate\": null,\"taxableInterest\": null,\"taxableDays\": null,\"issuedDate\": \"2023-01-02\",\"branchName\": null,\"branchPhoneNumber\": null}";
  // 変動金利定期預金のお知らせ　必須項目：有 非必須項目:無（空文字）
  private String NoticeFloatingRateDtoJson3 = "{\"noticeFloatingInterestRateTimeDepositId\":  \"rid0999002\",\"accountNumber\": \"\",\"displayDocumentName\":\"\",\"displayProductName\":\"\",\"applicantName\": \"\",\"depositDate\": \"\",\"maturityDate\": \"\",\"depositNumber\": \"\",\"depositAmount\": \"\",\"taxClass\": \"\",\"simpleOrCompoundInterest\": \"\",\"periodDays\": \"\",\"interestRate\":\"\",\"interimInterestPaymentDate\": \"\",\"interimPaymentInterestRate\": \"\",\"interimPaymentInterest\": \"\",\"nationalTax\": \"\",\"localTax\": \"\",\"deductionInterimPaymentInterest\": \"\",\"newInterestRateStartDate\": \"\",\"newInterestRate\": \"\",\"transferDesignationAccount\": \"\",\"standardBenchmarkInterestRate\":\"\",\"additionalInterestRate\": \"\",\"applyNewInterestRate\": \"\",\"taxableInterest\": \"\",\"taxableDays\": \"\",\"issuedDate\": \"2023-01-02\",\"branchName\": \"\",\"branchPhoneNumber\": \"\"}";



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
    NoticeFloatingRateDto NoticeFloatingRateDto = objectMapper.readValue(NoticeFloatingRateDtoJson1, NoticeFloatingRateDto.class);

    // Mock化
    // メソッドの戻り値を設定
    Mockito.when(noticeFloatingRateApiLogic.callNoticeFloatingRateLogic(ArgumentMatchers.any(),ArgumentMatchers.any(),ArgumentMatchers.any())).thenReturn(NoticeFloatingRateDto);
    Mockito.when(pdfService.pdfExport(ArgumentMatchers.any(), ArgumentMatchers.any(), ArgumentMatchers.any())).thenReturn(array);
    Mockito.when(storedDataHistoryRepository.save(ArgumentMatchers.any())).thenReturn(true);
    Mockito.when(reportDefinitionRepository
        .findByStartDateLessThanEqual(ArgumentMatchers.any())).thenReturn(reportDefinitionList);

    // ユーザ識別子
    String userId = "01234567890123456789012345678901";
    // 口座識別子
    String accountId = "01234567890123456789";
    // 変動金利定期預金のお知らせ識別子
    String reportId = "rid0000000000000001";
    

    byte[] result = noticeFloatingRateService.getNoticeFloatingRateService(userId, accountId, reportId);
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
    NoticeFloatingRateDto NoticeFloatingRateDto = objectMapper.readValue(NoticeFloatingRateDtoJson2, NoticeFloatingRateDto.class);

    // Mock化
    // メソッドの戻り値を設定
    Mockito.when(noticeFloatingRateApiLogic.callNoticeFloatingRateLogic(ArgumentMatchers.any(),ArgumentMatchers.any(),ArgumentMatchers.any())).thenReturn(NoticeFloatingRateDto);
    Mockito.when(pdfService.pdfExport(ArgumentMatchers.any(), ArgumentMatchers.any(), ArgumentMatchers.any())).thenReturn(array);
    Mockito.when(storedDataHistoryRepository.save(ArgumentMatchers.any())).thenReturn(true);
    Mockito.when(reportDefinitionRepository
        .findByStartDateLessThanEqual(ArgumentMatchers.any())).thenReturn(reportDefinitionList);

    // ユーザ識別子
    String userId = "1";
    // 口座識別子
    String accountId = "1";
    // 変動金利定期預金のお知らせ識別子
    String reportId = "1";

   

    byte[] result = noticeFloatingRateService.getNoticeFloatingRateService(userId, accountId, reportId);
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
    NoticeFloatingRateDto NoticeFloatingRateDto = objectMapper.readValue(NoticeFloatingRateDtoJson3, NoticeFloatingRateDto.class);

    // Mock化
    // メソッドの戻り値を設定
    Mockito.when(noticeFloatingRateApiLogic.callNoticeFloatingRateLogic(ArgumentMatchers.any(),ArgumentMatchers.any(),ArgumentMatchers.any())).thenReturn(NoticeFloatingRateDto);
    Mockito.when(pdfService.pdfExport(ArgumentMatchers.any(), ArgumentMatchers.any(), ArgumentMatchers.any())).thenReturn(array);
    Mockito.when(storedDataHistoryRepository.save(ArgumentMatchers.any())).thenReturn(true);
    Mockito.when(reportDefinitionRepository
        .findByStartDateLessThanEqual(ArgumentMatchers.any())).thenReturn(reportDefinitionList);

    // ユーザ識別子
    String userId = "1";
    // 口座識別子
    String accountId = "1";
    // 変動金利定期預金のお知らせ識別子
    String reportId = "1";
    
   

    byte[] result = noticeFloatingRateService.getNoticeFloatingRateService(userId, accountId, reportId);
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
    NoticeFloatingRateDto NoticeFloatingRateDto = objectMapper.readValue(NoticeFloatingRateDtoJson1, NoticeFloatingRateDto.class);

    // Mock化
    // メソッドの戻り値を設定
    Mockito.when(noticeFloatingRateApiLogic.callNoticeFloatingRateLogic(ArgumentMatchers.any(),ArgumentMatchers.any(),ArgumentMatchers.any())).thenReturn(NoticeFloatingRateDto);
    Mockito.when(pdfService.pdfExport(ArgumentMatchers.any(), ArgumentMatchers.any(), ArgumentMatchers.any())).thenReturn(array);
    Mockito.when(storedDataHistoryRepository.save(ArgumentMatchers.any())).thenReturn(true);
    Mockito.when(reportDefinitionRepository
        .findByStartDateLessThanEqual(ArgumentMatchers.any())).thenReturn(reportDefinitionList);

    // ユーザ識別子
    String userId = "1";
    // 口座識別子
    String accountId = "1";
    // 変動金利定期預金のお知らせ識別子
    String reportId = null;
    
    Exception result = new Exception();

    try {
    	noticeFloatingRateService.getNoticeFloatingRateService(userId, accountId, reportId);
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
    NoticeFloatingRateDto NoticeFloatingRateDto = objectMapper.readValue(NoticeFloatingRateDtoJson1, NoticeFloatingRateDto.class);

    // Mock化
    // メソッドの戻り値を設定
    Mockito.when(noticeFloatingRateApiLogic.callNoticeFloatingRateLogic(ArgumentMatchers.any(),ArgumentMatchers.any(),ArgumentMatchers.any())).thenReturn(NoticeFloatingRateDto);
    Mockito.when(pdfService.pdfExport(ArgumentMatchers.any(), ArgumentMatchers.any(), ArgumentMatchers.any())).thenReturn(array);
    Mockito.when(storedDataHistoryRepository.save(ArgumentMatchers.any())).thenReturn(true);
    Mockito.when(reportDefinitionRepository
        .findByStartDateLessThanEqual(ArgumentMatchers.any())).thenReturn(reportDefinitionList);

    // ユーザ識別子
    String userId = "1";
    // 口座識別子
    String accountId = null;
    // 変動金利定期預金のお知らせ識別子
    String reportId = "1";
    
    Exception result = new Exception();

    try {
    	noticeFloatingRateService.getNoticeFloatingRateService(userId, accountId, reportId);
    } catch (Exception e) {
      result = e;
    }

    MatcherAssert.assertThat(result, is(instanceOf(Exception.class)));
  }

 
}