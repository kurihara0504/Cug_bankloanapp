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
import com.moneyforward.cug_bankbookbase.dto.InformationSelectingRateDto;
import com.moneyforward.cug_bankbookbase.logic.InformationSelectingRateLogic;

/**
 * 金利見直し方法選択のご案内取得サービス　テストクラス.
 */
@ContextConfiguration
@RunWith(SpringRunner.class)
@SpringBootTest
public class InformationSelectingRateServiceTest {

  /** テスト対象クラス. */
  @Autowired
  private InformationSelectingRateService informationSelectingRateService;

  /** テスト実行時の共通処理の設定. */
  @Rule
  public ExpectedException exception = ExpectedException.none();

  /** Mock化対象クラス. */
  @Mock
  private InformationSelectingRateLogic informationSelectingRateLogic;
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
    ReflectionTestUtils.setField(informationSelectingRateService, "informationSelectingRateLogic", informationSelectingRateLogic);
    ReflectionTestUtils.setField(informationSelectingRateService, "pdfService", pdfService);
  }

  /** 最後処理. */
  @After
  public void after() {
    // 初期化
    Mockito.reset(informationSelectingRateLogic);
    Mockito.reset(pdfService);
    Mockito.reset(storedDataHistoryRepository);
    Mockito.reset(reportDefinitionRepository);
    Mockito.reset(resource);
  }
  
  // 全項目有
  private String json1 = "{"
                  + " \"reviewInterestRateNotificationsId\": \"1234567890\","
                  + " \"displayDocumentName\": \"金利見直し方法選択のご案内ドキュメント\","
                  + " \"displayProductName\": \"金利見直し方法選択のご案内画面\","
                  + " \"applicantName\": \"中国　太郎\","
                  + " \"applicationDeadline\": \"2023-01-02\","
                  + " \"internetBankingInfo1\": \"インターネットバンキング文言１\","
                  + " \"internetBankingInfo2\": \"インターネットバンキング文言２\","
                  + " \"internetBankingInfo3\": \"インターネットバンキング文言３\","
                  + " \"handlingNumber\": \"123\","
                  + " \"loanName\": \"サステナブルローン\","
                  + " \"loanAmount\": 111111111,"
                  + " \"currentAmount\": 222222222,"
                  + " \"repaymentStartDate\": \"2023-01-03\","
                  + " \"repaymentEndDate\": \"2023-01-04\","
                  + " \"interestRateReviewMethod\": \"10年固定\","
                  + " \"interestRate\": \"11.1111\","
                  + " \"monthlyRepaymentAmount\": 333333333,"
                  + " \"bonusRepaymentAmount\": 444444444,"
                  + " \"floatingInterestRate\": \"22.222\","
                  + " \"floatingMonthlyRepaymentAmount\": 55555555,"
                  + " \"floatingBonusRepaymentAmount\": 66666666,"
                  + " \"threeYearsFixedInterestRate\": \"33.33\","
                  + " \"threeYearsFixedMonthlyRepaymentAmount\": 7777777,"
                  + " \"threeYearsFixedBonusRepaymentAmount\": 8888888,"
                  + " \"fiveYearsFixedInterestRate\": \"44.4\","
                  + " \"fiveYearsFixedMonthlyRepaymentAmount\": 999999,"
                  + " \"fiveYearsFixedBonusRepaymentAmount\": 111111,"
                  + " \"tenYearsFixedInterestRate\": \"5.5\","
                  + " \"tenYearsFixedMonthlyRepaymentAmount\": 22222,"
                  + " \"tenYearsFixedBonusRepaymentAmount\": 33333,"
                  + " \"remarks1\": \"備考1文言あああああ\","
                  + " \"remarks2\": \"備考2文言いいいいい\","
                  + " \"remarks3\": \"備考3文言ううううう\","
                  + " \"remarks4\": \"備考4文言えええええ\","
                  + " \"remarks5\": \"備考5文言おおおおお\","
                  + " \"issuedDate\": \"2023-01-05\","
                  + " \"branchName\": \"中央支店\","
                  + " \"branchPhoneNumber\": \"0000-1111-2222\""
                  + "}";

  // 必須のみ
  private String json2 = "{"
                  + " \"reviewInterestRateNotificationsId\": \"1234567890\","
                  + " \"displayDocumentName\": \"\","
                  + " \"displayProductName\": \"\","
                  + " \"applicantName\": \"\","
                  + " \"applicationDeadline\": \"\","
                  + " \"internetBankingInfo1\": \"\","
                  + " \"internetBankingInfo2\": \"\","
                  + " \"internetBankingInfo3\": \"\","
                  + " \"handlingNumber\": null,"
                  + " \"loanName\": \"\","
                  + " \"loanAmount\": null,"
                  + " \"currentAmount\": null,"
                  + " \"repaymentStartDate\": \"\","
                  + " \"repaymentEndDate\": \"\","
                  + " \"interestRateReviewMethod\": \"\","
                  + " \"interestRate\": \"\","
                  + " \"monthlyRepaymentAmount\": null,"
                  + " \"bonusRepaymentAmount\": null,"
                  + " \"floatingInterestRate\": \"\","
                  + " \"floatingMonthlyRepaymentAmount\": null,"
                  + " \"floatingBonusRepaymentAmount\": null,"
                  + " \"threeYearsFixedInterestRate\": \"\","
                  + " \"threeYearsFixedMonthlyRepaymentAmount\": null,"
                  + " \"threeYearsFixedBonusRepaymentAmount\": null,"
                  + " \"fiveYearsFixedInterestRate\": \"\","
                  + " \"fiveYearsFixedMonthlyRepaymentAmount\": null,"
                  + " \"fiveYearsFixedBonusRepaymentAmount\": null,"
                  + " \"tenYearsFixedInterestRate\": \"\","
                  + " \"tenYearsFixedMonthlyRepaymentAmount\": null,"
                  + " \"tenYearsFixedBonusRepaymentAmount\": null,"
                  + " \"remarks1\": \"\","
                  + " \"remarks2\": \"\","
                  + " \"remarks3\": \"\","
                  + " \"remarks4\": \"\","
                  + " \"remarks5\": \"\","
                  + " \"issuedDate\": \"2023-01-05\","
                  + " \"branchName\": \"\","
                  + " \"branchPhoneNumber\": \"\""
                  + "}";
  
  // 全項目最大桁
  private String json3 = "{"
                  + " \"reviewInterestRateNotificationsId\": \"1234567890\","
                  + " \"displayDocumentName\": \"金利見直し方法選択のご案内ドキュメント\","
                  + " \"displayProductName\": \"金利見直し方法選択のご案内画面\","
                  + " \"applicantName\": \"あいうえおあいうえおあいうえおあいうえおあいうえおあいうえおあいうえおあいうえおあいうえおあいうえおあいうえおあいうえおあいうえおあいうえおあいうえおあいうえおあいうえおあいうえおあいうえおあいうえお\","
                  + " \"applicationDeadline\": \"2023-01-02\","
                  + " \"internetBankingInfo1\": \"あいうえおあいうえおあいうえおあいうえおあいうえおあいうえおあいうえおあいうえおあいうえおあいうえおあ\","
                  + " \"internetBankingInfo2\": \"あいうえおあいうえおあいうえおあいうえおあいうえおあいうえおあいうえおあいうえおあいうえおあいうえおあ\","
                  + " \"internetBankingInfo3\": \"あいうえおあいうえおあいうえおあいうえおあいうえおあいうえおあいうえおあいうえおあいうえおあいうえおあ\","
                  + " \"handlingNumber\": \"123456\","
                  + " \"loanName\": \"サステナブルローンあ\","
                  + " \"loanAmount\": 111111111,"
                  + " \"currentAmount\": 222222222,"
                  + " \"repaymentStartDate\": \"2023-01-03\","
                  + " \"repaymentEndDate\": \"2023-01-04\","
                  + " \"interestRateReviewMethod\": \"10年固定\","
                  + " \"interestRate\": \"11.1111\","
                  + " \"monthlyRepaymentAmount\": 333333333,"
                  + " \"bonusRepaymentAmount\": 444444444,"
                  + " \"floatingInterestRate\": \"22.2222\","
                  + " \"floatingMonthlyRepaymentAmount\": 555555555,"
                  + " \"floatingBonusRepaymentAmount\": 666666666,"
                  + " \"threeYearsFixedInterestRate\": \"33.3333\","
                  + " \"threeYearsFixedMonthlyRepaymentAmount\": 777777777,"
                  + " \"threeYearsFixedBonusRepaymentAmount\": 888888888,"
                  + " \"fiveYearsFixedInterestRate\": \"44.4444\","
                  + " \"fiveYearsFixedMonthlyRepaymentAmount\": 999999999,"
                  + " \"fiveYearsFixedBonusRepaymentAmount\": 111111111,"
                  + " \"tenYearsFixedInterestRate\": \"55.5555\","
                  + " \"tenYearsFixedMonthlyRepaymentAmount\": 222222222,"
                  + " \"tenYearsFixedBonusRepaymentAmount\": 333333333,"
                  + " \"remarks1\": \"備考1文言あああああ備考1文言あああああ備考1文言あああああ備考1文言あああああ備考1文言ああああああ\","
                  + " \"remarks2\": \"備考2文言いいいいい備考2文言いいいいい備考2文言いいいいい備考2文言いいいいい備考2文言いいいいいい\","
                  + " \"remarks3\": \"備考3文言ううううう備考3文言ううううう備考3文言ううううう備考3文言ううううう備考3文言うううううう\","
                  + " \"remarks4\": \"備考4文言えええええ備考4文言えええええ備考4文言えええええ備考4文言えええええ備考4文言ええええええ\","
                  + " \"remarks5\": \"備考5文言おおおおお備考5文言おおおおお備考5文言おおおおお備考5文言おおおおお備考5文言おおおおおお\","
                  + " \"issuedDate\": \"2023-01-05\","
                  + " \"branchName\": \"あいうえおあいうえおあいうえおあいうえおあいうえおあいうえおあいうえおあいうえおあいうえおあいうえおあいうえおあいうえおあいうえおあいうえおあいうえおあいうえおあいうえおあいうえおあいうえおあいうえおあいうえおあいうえおあいうえおあいうえおあいうえおあいうえおあいうえおあいうえおあいうえおあいうえおあいうえおあいうえおあいうえおあいうえおあいうえおあいうえおあいうえおあいうえおあいうえおあいうえお\","
                  + " \"branchPhoneNumber\": \"0000-11111-22222\""
                  + "}";
  /**
   * 正常系
   */
  @Test
  public void testGetInfomationSelectingRateOK() throws Exception {

    List<ReportDefinition> reportDefinitionList = new ArrayList<ReportDefinition>();
    ReportDefinition reportDefinition = new ReportDefinition();

    reportDefinition.setId(1L);
    reportDefinition.setReportFormatFilePath("fullpath");
    reportDefinition.setReportTypeId("12");
    reportDefinition.setReportVersion("1");
    reportDefinition.setStartDate(new Date());
    reportDefinitionList.add(reportDefinition);

    ObjectMapper objectMapper = new ObjectMapper();
    InformationSelectingRateDto informationSelectingRateDto = objectMapper.readValue(json1, InformationSelectingRateDto.class);

    byte[] array = new byte[5];

    // Mock化
    // メソッドの戻り値を設定
    Mockito.when(informationSelectingRateLogic.callInformationSelectingRateApi(
        ArgumentMatchers.any(),ArgumentMatchers.any(),ArgumentMatchers.any())).thenReturn(informationSelectingRateDto);
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

    byte[] result = informationSelectingRateService.getInformationSelectingRate(userId, accountId, reportId, type);

    Assert.assertArrayEquals(result, array);
  }
  
  /**
   * 必須項目のみ
   */
  @Test
  public void testGetInfomationSelectingRateOnlyRequired() throws Exception {

    List<ReportDefinition> reportDefinitionList = new ArrayList<ReportDefinition>();
    ReportDefinition reportDefinition = new ReportDefinition();

    reportDefinition.setId(1L);
    reportDefinition.setReportFormatFilePath("fullpath");
    reportDefinition.setReportTypeId("12");
    reportDefinition.setReportVersion("1");
    reportDefinition.setStartDate(new Date());
    reportDefinitionList.add(reportDefinition);

    ObjectMapper objectMapper = new ObjectMapper();
    InformationSelectingRateDto informationSelectingRateDto = objectMapper.readValue(json2, InformationSelectingRateDto.class);

    byte[] array = new byte[5];

    // Mock化
    // メソッドの戻り値を設定
    Mockito.when(informationSelectingRateLogic.callInformationSelectingRateApi(
        ArgumentMatchers.any(),ArgumentMatchers.any(),ArgumentMatchers.any())).thenReturn(informationSelectingRateDto);
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

    byte[] result = informationSelectingRateService.getInformationSelectingRate(userId, accountId, reportId, type);

    Assert.assertArrayEquals(result, array);
  }

  /**
   * 全項目最大文字数
   */
  @Test
  public void testGetInfomationSelectingRateMax() throws Exception {

    List<ReportDefinition> reportDefinitionList = new ArrayList<ReportDefinition>();
    ReportDefinition reportDefinition = new ReportDefinition();

    reportDefinition.setId(1L);
    reportDefinition.setReportFormatFilePath("fullpath");
    reportDefinition.setReportTypeId("12");
    reportDefinition.setReportVersion("1");
    reportDefinition.setStartDate(new Date());
    reportDefinitionList.add(reportDefinition);

    ObjectMapper objectMapper = new ObjectMapper();
    InformationSelectingRateDto informationSelectingRateDto = objectMapper.readValue(json3, InformationSelectingRateDto.class);

    byte[] array = new byte[5];

    // Mock化
    // メソッドの戻り値を設定
    Mockito.when(informationSelectingRateLogic.callInformationSelectingRateApi(
        ArgumentMatchers.any(),ArgumentMatchers.any(),ArgumentMatchers.any())).thenReturn(informationSelectingRateDto);
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

    byte[] result = informationSelectingRateService.getInformationSelectingRate(userId, accountId, reportId, type);

    Assert.assertArrayEquals(result, array);
  }
}
