package com.moneyforward.cug_bankbookbase.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.moneyforward.bankbookbase.base.exception.BaseAppException;
import com.moneyforward.bankbookbase.dto.LoginDetailDto;
import com.moneyforward.bankbookbase.dto.SsoDitailDto;
import com.moneyforward.bankbookbase.dto.SsoDto;
import com.moneyforward.bankbookbase.dto.UserIdDto;
import com.moneyforward.bankbookbase.dto.UserJsonDto;
import com.moneyforward.bankbookbase.logic.CallAuthApiLogic;
import com.moneyforward.bankbookbase.logic.CallSsoVerificationApiLogic;
import com.moneyforward.cug_bankbookbase.dto.AccountListDto;
import com.moneyforward.cug_bankbookbase.dto.AccountsListDto;
import com.moneyforward.cug_bankbookbase.dto.CugReturnReportsDto;
import com.moneyforward.cug_bankbookbase.logic.CallGetReportInfoLogic;
import com.moneyforward.cug_bankbookbase.service.CugLoginService;

import static org.hamcrest.CoreMatchers.instanceOf;
import static org.hamcrest.CoreMatchers.is;
import java.util.ArrayList;
import java.util.List;

import org.hamcrest.MatcherAssert;
import org.hamcrest.beans.SamePropertyValuesAs;
import org.junit.After;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import org.junit.runner.RunWith;
import org.mockito.ArgumentMatchers;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.util.ReflectionTestUtils;

/**
 * ログインサービステストクラス.
 */
@ContextConfiguration
@RunWith(SpringRunner.class)
@SpringBootTest
public class CugLoginServiceTest {

  /** テスト対象クラス. */
  @Autowired
  public CugLoginService loginService;

  /** テスト実行時の共通処理の設定. */
  @Rule
  public ExpectedException exception = ExpectedException.none();

  /** Mock化対象クラス. */
  /** 業務API「ユーザ認証」呼び出しクラス. */
  @Mock
  private CallAuthApiLogic callAuthApiLogic;
  /** 業務API「SSO検証」呼び出しクラス. */
  @Mock
  private CallSsoVerificationApiLogic callSsoVerificationApiLogic;
  /** 業務API「利用口座情報取得(一覧)」呼び出しクラス. */
  @Mock
  private CallGetReportInfoLogic callGetReportInfoLogic;

  /** 最後の処理. */
  @After
  public void doAfter() {
    // 初期化
    Mockito.reset(callAuthApiLogic);
    Mockito.reset(callSsoVerificationApiLogic);
    Mockito.reset(callGetReportInfoLogic);
  }

  /**
   * 正常系　doLoginメソッド　最大桁（ユーザ識別子・パスワード）
   */
  @Test
  public void testNormalUseIdPasswordMaxDoLogin() throws Exception {

    LoginDetailDto loginDetailDto = getMaxLoginDetailDto();

    UserJsonDto userJsonDto = new UserJsonDto();
    userJsonDto.setUserId("1111");

    ReflectionTestUtils.setField(loginService, "callAuthApiLogic", callAuthApiLogic);

    // Mock化
    // メソッドの戻り値を設定
    Mockito.when(callAuthApiLogic.doLogic(ArgumentMatchers.any())).thenReturn(userJsonDto);

    UserIdDto userIdDto = loginService.doLogin(loginDetailDto);

    MatcherAssert.assertThat(userIdDto.getUserId(), is(SamePropertyValuesAs.samePropertyValuesAs(userJsonDto.getUserId())));

  }

  /**
   * 正常系　doReportメソッド
   */
  @Test
  public void testNormalDoReport() throws Exception {

    String userID = "1111";
    ReflectionTestUtils.setField(loginService, "callGetReportInfoLogic", callGetReportInfoLogic);

    ObjectMapper mapper = new ObjectMapper();

    AccountListDto accountListDto = mapper.readValue(accountListJson, AccountListDto.class);

    // Mock化
    // メソッドの戻り値を設定
    Mockito.when(callGetReportInfoLogic.doLogic(ArgumentMatchers.any())).thenReturn(accountListDto);

    CugReturnReportsDto returnReportsDto = (CugReturnReportsDto) loginService.doReport(userID);

    String data = 
        "{\"reports\":"
        + "["
        // 定期預金期日のご案内・変動金利定期預金のお知らせ(口座番号あり)
        +    "{\"report_id\":\"rid006001\",\"sub_account_id\":\"aid006001\",\"product_name\":\"定期預金\",\"report_name\":\"定期預金期日のご案内\",\"number\":\"中国支店／1234567\",\"type\":\"006\",\"date_of_issue\":\"2024-12-31\",\"report_distinction_flag\":\"11\"},"
        +    "{\"report_id\":\"rid006002\",\"sub_account_id\":\"aid006001\",\"product_name\":\"定期預金\",\"report_name\":\"変動金利定期預金のお知らせ\",\"number\":\"中国支店／1234567\",\"type\":\"006\",\"date_of_issue\":\"2024-12-30\",\"report_distinction_flag\":\"18\"},"
        // 定期預金期日のご案内・変動金利定期預金のお知らせ(口座番号なし)
        +    "{\"report_id\":\"rid006003\",\"sub_account_id\":\"aid006002\",\"product_name\":\"定期預金\",\"report_name\":\"定期預金期日のご案内\",\"number\":\"中国支店\",\"type\":\"006\",\"date_of_issue\":\"2024-12-28\",\"report_distinction_flag\":\"11\"},"
        +    "{\"report_id\":\"rid006004\",\"sub_account_id\":\"aid006002\",\"product_name\":\"定期預金\",\"report_name\":\"変動金利定期預金のお知らせ\",\"number\":\"中国支店\",\"type\":\"006\",\"date_of_issue\":\"2024-12-27\",\"report_distinction_flag\":\"18\"},"
        // カードローンお取引のご案内(口座番号あり)
        +    "{\"report_id\":\"rid020001\",\"sub_account_id\":\"aid020001\",\"product_name\":\"カードローン\",\"report_name\":\"カードローンお取引内容のお知らせ\",\"number\":\"中国支店／1234567\",\"type\":\"020\",\"date_of_issue\":\"2024-12-26\",\"report_distinction_flag\":\"13\"},"
        // カードローンお取引のご案内(口座番号なし)
        +    "{\"report_id\":\"rid020002\",\"sub_account_id\":\"aid020002\",\"product_name\":\"カードローン\",\"report_name\":\"カードローンお取引内容のお知らせ\",\"number\":\"中国支店\",\"type\":\"020\",\"date_of_issue\":\"2024-12-25\",\"report_distinction_flag\":\"13\"},"
        // 外貨定期預金預入のご案内・外貨定期預金期日のご案内(口座番号あり)
        +    "{\"report_id\":\"rid060001\",\"sub_account_id\":\"aid060001\",\"product_name\":\"外貨定期預金\",\"report_name\":\"外貨定期預金預入のご案内\",\"number\":\"中国支店／1234567\",\"type\":\"060\",\"date_of_issue\":\"2024-12-24\",\"report_distinction_flag\":\"15\"},"
        +    "{\"report_id\":\"rid060002\",\"sub_account_id\":\"aid060001\",\"product_name\":\"外貨定期預金\",\"report_name\":\"外貨定期預金期日のご案内\",\"number\":\"中国支店／1234567\",\"type\":\"060\",\"date_of_issue\":\"2024-12-23\",\"report_distinction_flag\":\"16\"},"
        // 外貨定期預金預入のご案内・外貨定期預金期日のご案内(口座番号なし)
        +    "{\"report_id\":\"rid060003\",\"sub_account_id\":\"aid060002\",\"product_name\":\"外貨定期預金\",\"report_name\":\"外貨定期預金預入のご案内\",\"number\":\"中国支店\",\"type\":\"060\",\"date_of_issue\":\"2024-12-22\",\"report_distinction_flag\":\"15\"},"
        +    "{\"report_id\":\"rid060004\",\"sub_account_id\":\"aid060002\",\"product_name\":\"外貨定期預金\",\"report_name\":\"外貨定期預金期日のご案内\",\"number\":\"中国支店\",\"type\":\"060\",\"date_of_issue\":\"2024-12-21\",\"report_distinction_flag\":\"16\"},"
        // ご返済のご案内・金利見直し方法選択のご案内(口座番号・CIF番号・ローン取扱番号あり）
        +    "{\"report_id\":\"rid101001\",\"sub_account_id\":\"aid101001\",\"product_name\":\"住宅ローン\",\"report_name\":\"ご返済のご案内\",\"number\":\"中国支店／12345\",\"type\":\"101\",\"date_of_issue\":\"2024-12-02\",\"report_distinction_flag\":\"12\"},"
        +    "{\"report_id\":\"rid101002\",\"sub_account_id\":\"aid101001\",\"product_name\":\"住宅ローン\",\"report_name\":\"金利見直し方法選択のご案内\",\"number\":\"中国支店／12345\",\"type\":\"101\",\"date_of_issue\":\"2024-12-01\",\"report_distinction_flag\":\"14\"},"
        // ご返済のご案内・金利見直し方法選択のご案内(口座番号・CIF番号あり、ローン取扱番号なし）
        +    "{\"report_id\":\"rid101004\",\"sub_account_id\":\"aid101002\",\"product_name\":\"住宅ローン\",\"report_name\":\"ご返済のご案内\",\"number\":\"中国支店／123456\",\"type\":\"101\",\"date_of_issue\":\"2024-11-29\",\"report_distinction_flag\":\"12\"},"
        +    "{\"report_id\":\"rid101005\",\"sub_account_id\":\"aid101002\",\"product_name\":\"住宅ローン\",\"report_name\":\"金利見直し方法選択のご案内\",\"number\":\"中国支店／123456\",\"type\":\"101\",\"date_of_issue\":\"2024-11-28\",\"report_distinction_flag\":\"14\"},"
        // ご返済のご案内・金利見直し方法選択のご案内(口座番号あり、CIF番号・ローン取扱番号なし）
        +    "{\"report_id\":\"rid101007\",\"sub_account_id\":\"aid101003\",\"product_name\":\"住宅ローン\",\"report_name\":\"ご返済のご案内\",\"number\":\"中国支店／1234567\",\"type\":\"101\",\"date_of_issue\":\"2024-11-26\",\"report_distinction_flag\":\"12\"},"
        +    "{\"report_id\":\"rid101008\",\"sub_account_id\":\"aid101003\",\"product_name\":\"住宅ローン\",\"report_name\":\"金利見直し方法選択のご案内\",\"number\":\"中国支店／1234567\",\"type\":\"101\",\"date_of_issue\":\"2024-11-25\",\"report_distinction_flag\":\"14\"},"
        // ご返済のご案内・金利見直し方法選択のご案内(口座番号・CIF番号・ローン取扱番号なし）
        +    "{\"report_id\":\"rid101010\",\"sub_account_id\":\"aid101004\",\"product_name\":\"住宅ローン\",\"report_name\":\"ご返済のご案内\",\"number\":\"中国支店\",\"type\":\"101\",\"date_of_issue\":\"2024-11-23\",\"report_distinction_flag\":\"12\"},"
        +    "{\"report_id\":\"rid101011\",\"sub_account_id\":\"aid101004\",\"product_name\":\"住宅ローン\",\"report_name\":\"金利見直し方法選択のご案内\",\"number\":\"中国支店\",\"type\":\"101\",\"date_of_issue\":\"2024-11-22\",\"report_distinction_flag\":\"14\"},"
        // 契約終了通知書(取扱番号あり) 
        +    "{\"report_id\":\"rid101012\",\"sub_account_id\":\"aid101005\",\"product_name\":\"住宅ローン\",\"report_name\":\"契約終了通知書\",\"number\":\"中国支店／1234567\",\"type\":\"101\",\"date_of_issue\":\"2024-11-21\",\"report_distinction_flag\":\"17\"},"
        // 契約終了通知書（取扱番号なし）
        +    "{\"report_id\":\"rid101013\",\"sub_account_id\":\"aid101006\",\"product_name\":\"住宅ローン\",\"report_name\":\"契約終了通知書\",\"number\":\"中国支店\",\"type\":\"101\",\"date_of_issue\":\"2024-11-21\",\"report_distinction_flag\":\"17\"}"
        
        + "]}";

    String json = mapper.writeValueAsString(returnReportsDto);

    MatcherAssert.assertThat(json, is(data));
  }

  /**
   * 異常系　doReportメソッド　
   */
  @Test
  public void testErrorDoReport() throws Exception {

    String userID = "1111";
    ReflectionTestUtils.setField(loginService, "callGetReportInfoLogic", callGetReportInfoLogic);

    AccountListDto accountListDto = new AccountListDto();
    List<AccountsListDto> list = new ArrayList<AccountsListDto>();
    accountListDto.setAccountsList(list);

    // Mock化
    // メソッドの戻り値を設定
    Mockito.when(callGetReportInfoLogic.doLogic(ArgumentMatchers.any())).thenReturn(accountListDto);

    Exception result = new Exception();
    try {
      loginService.doReport(userID);
    } catch (Exception e) {
      result = e;
    }

    MatcherAssert.assertThat(result, is(instanceOf(BaseAppException.class)));

  }

  private String accountListJson = 
      "{\"accountsList\":"
          + "["
          // 定期預金期日のご案内・変動金利定期預金のお知らせ(口座番号あり)
          +  "{\"accountId\":\"aid006001\",\"accountName\":\"メイギニン999\",\"accountKanjiName\":\"名義人999\",\"bankCode\":\"1234\",\"branchNumber\":\"999\",\"accountType\":\"006\",\"accountNumber\":\"01234567\",\"branchName\":\"ちゅうごくしてん\",\"branchKanjiName\":\"中国支店\",\"currencyCode\":\"JPY\",\"additionalAccountType\":\"\","
          +  "\"report\":{"
          +    "\"timeDepositMaturityNotifications\":[{\"id\":\"rid006001\",\"issuedDate\":\"2024-12-31\"}],"
          +    "\"noticeFloatingInterestRateTimeDeposit\":[{\"id\":\"rid006002\",\"issuedDate\":\"2024-12-30\",\"displayProductName\":\"定期預金\",\"displayDocumentName\":\"変動金利定期預金のお知らせ\"}]}},"
          // 定期預金期日のご案内・変動金利定期預金のお知らせ(口座番号なし)
          +  "{\"accountId\":\"aid006002\",\"accountName\":\"メイギニン999\",\"accountKanjiName\":\"名義人999\",\"bankCode\":\"1234\",\"branchNumber\":\"999\",\"accountType\":\"006\",\"branchName\":\"ちゅうごくしてん\",\"branchKanjiName\":\"中国支店\",\"currencyCode\":\"JPY\",\"additionalAccountType\":\"\","
          +  "\"report\":{"
          +    "\"timeDepositMaturityNotifications\":[{\"id\":\"rid006003\",\"issuedDate\":\"2024-12-28\"}],"
          +    "\"noticeFloatingInterestRateTimeDeposit\":[{\"id\":\"rid006004\",\"issuedDate\":\"2024-12-27\",\"displayProductName\":\"定期預金\",\"displayDocumentName\":\"変動金利定期預金のお知らせ\"}]}},"
          // カードローンお取引のご案内(口座番号あり)
          +  "{\"accountId\":\"aid020001\",\"accountName\":\"メイギニン999\",\"accountKanjiName\":\"名義人999\",\"bankCode\":\"1234\",\"branchNumber\":\"999\",\"accountType\":\"020\",\"accountNumber\":\"01234567\",\"branchName\":\"ちゅうごくしてん\",\"branchKanjiName\":\"中国支店\",\"currencyCode\":\"JPY\",\"additionalAccountType\":\"\","
          +  "\"report\":{"
          +    "\"noticeCardLoanTransaction\":[{\"id\":\"rid020001\",\"issuedDate\":\"2024-12-26\",\"displayProductName\":\"カードローン\",\"displayDocumentName\":\"カードローンお取引内容のお知らせ\"}]}},"
          // カードローンお取引のご案内(口座番号なし)
          +  "{\"accountId\":\"aid020002\",\"accountName\":\"メイギニン999\",\"accountKanjiName\":\"名義人999\",\"bankCode\":\"1234\",\"branchNumber\":\"999\",\"accountType\":\"020\",\"branchName\":\"ちゅうごくしてん\",\"branchKanjiName\":\"中国支店\",\"currencyCode\":\"JPY\",\"additionalAccountType\":\"\","
          +  "\"report\":{"
          +    "\"noticeCardLoanTransaction\":[{\"id\":\"rid020002\",\"issuedDate\":\"2024-12-25\",\"displayProductName\":\"カードローン\",\"displayDocumentName\":\"カードローンお取引内容のお知らせ\"}]}},"
          // 外貨定期預金預入のご案内・外貨定期預金期日のご案内(口座番号あり)
          +  "{\"accountId\":\"aid060001\",\"accountName\":\"メイギニン999\",\"accountKanjiName\":\"名義人999\",\"bankCode\":\"1234\",\"branchNumber\":\"999\",\"accountType\":\"060\",\"accountNumber\":\"01234567\",\"branchName\":\"ちゅうごくしてん\",\"branchKanjiName\":\"中国支店\",\"currencyCode\":\"JPY\",\"additionalAccountType\":\"\","
          +  "\"report\":{"
          +    "\"foreignTimeDepositNotificationsOpen\":[{\"id\":\"rid060001\",\"issuedDate\":\"2024-12-24\",\"displayProductName\":\"外貨定期預金\",\"displayDocumentName\":\"外貨定期預金預入のご案内\"}],"
          +    "\"foreignTimeDepositMaturityNotifications\":[{\"id\":\"rid060002\",\"issuedDate\":\"2024-12-23\",\"displayProductName\":\"外貨定期預金\",\"displayDocumentName\":\"外貨定期預金期日のご案内\"}]}},"
          // 外貨定期預金預入のご案内・外貨定期預金期日のご案内(口座番号なし)
          +  "{\"accountId\":\"aid060002\",\"accountName\":\"メイギニン999\",\"accountKanjiName\":\"名義人999\",\"bankCode\":\"1234\",\"branchNumber\":\"999\",\"accountType\":\"060\",\"branchName\":\"ちゅうごくしてん\",\"branchKanjiName\":\"中国支店\",\"currencyCode\":\"JPY\",\"additionalAccountType\":\"\","
          +  "\"report\":{"
          +    "\"foreignTimeDepositNotificationsOpen\":[{\"id\":\"rid060003\",\"issuedDate\":\"2024-12-22\",\"displayProductName\":\"外貨定期預金\",\"displayDocumentName\":\"外貨定期預金預入のご案内\"}],"
          +    "\"foreignTimeDepositMaturityNotifications\":[{\"id\":\"rid060004\",\"issuedDate\":\"2024-12-21\",\"displayProductName\":\"外貨定期預金\",\"displayDocumentName\":\"外貨定期預金期日のご案内\"}]}},"
          // ご返済のご案内・金利見直し方法選択のご案内(口座番号・CIF番号・ローン取扱番号あり）
          +  "{\"accountId\":\"aid101001\",\"accountName\":\"メイギニン999\",\"accountKanjiName\":\"名義人999\",\"bankCode\":\"1234\",\"branchNumber\":\"999\",\"accountType\":\"101\",\"accountNumber\":\"01234567\",\"branchName\":\"ちゅうごくしてん\",\"branchKanjiName\":\"中国支店\",\"currencyCode\":\"JPY\",\"additionalAccountType\":\"\",\"cifNumber\":\"0123456\",\"loanHandlingNumber\":\"012345\","
          +  "\"report\":{"
          +    "\"repaymentNotifications\":[{\"id\":\"rid101001\",\"issuedDate\":\"2024-12-02\",\"displayProductName\":\"住宅ローン\",\"displayDocumentName\":\"ご返済のご案内\"}],"
          +    "\"reviewInterestRateNotifications\":[{\"id\":\"rid101002\",\"issuedDate\":\"2024-12-01\",\"displayProductName\":\"住宅ローン\",\"displayDocumentName\":\"金利見直し方法選択のご案内\"}]}},"
          // ご返済のご案内・金利見直し方法選択のご案内(口座番号・CIF番号あり、ローン取扱番号なし）
          +  "{\"accountId\":\"aid101002\",\"accountName\":\"メイギニン999\",\"accountKanjiName\":\"名義人999\",\"bankCode\":\"1234\",\"branchNumber\":\"999\",\"accountType\":\"101\",\"accountNumber\":\"01234567\",\"branchName\":\"ちゅうごくしてん\",\"branchKanjiName\":\"中国支店\",\"currencyCode\":\"JPY\",\"additionalAccountType\":\"\",\"cifNumber\":\"0123456\","
          +  "\"report\":{"
          +    "\"repaymentNotifications\":[{\"id\":\"rid101004\",\"issuedDate\":\"2024-11-29\",\"displayProductName\":\"住宅ローン\",\"displayDocumentName\":\"ご返済のご案内\"}],"
          +    "\"reviewInterestRateNotifications\":[{\"id\":\"rid101005\",\"issuedDate\":\"2024-11-28\",\"displayProductName\":\"住宅ローン\",\"displayDocumentName\":\"金利見直し方法選択のご案内\"}]}},"
          // ご返済のご案内・金利見直し方法選択のご案内(口座番号あり、CIF番号・ローン取扱番号あり）
          +  "{\"accountId\":\"aid101003\",\"accountName\":\"メイギニン999\",\"accountKanjiName\":\"名義人999\",\"bankCode\":\"1234\",\"branchNumber\":\"999\",\"accountType\":\"101\",\"accountNumber\":\"01234567\",\"branchName\":\"ちゅうごくしてん\",\"branchKanjiName\":\"中国支店\",\"currencyCode\":\"JPY\",\"additionalAccountType\":\"\","
          +  "\"report\":{"
          +    "\"repaymentNotifications\":[{\"id\":\"rid101007\",\"issuedDate\":\"2024-11-26\",\"displayProductName\":\"住宅ローン\",\"displayDocumentName\":\"ご返済のご案内\"}],"
          +    "\"reviewInterestRateNotifications\":[{\"id\":\"rid101008\",\"issuedDate\":\"2024-11-25\",\"displayProductName\":\"住宅ローン\",\"displayDocumentName\":\"金利見直し方法選択のご案内\"}]}},"
          // ご返済のご案内・金利見直し方法選択のご案内(口座番号・CIF番号・ローン取扱番号なし）
          +  "{\"accountId\":\"aid101004\",\"accountName\":\"メイギニン999\",\"accountKanjiName\":\"名義人999\",\"bankCode\":\"1234\",\"branchNumber\":\"999\",\"accountType\":\"101\",\"branchName\":\"ちゅうごくしてん\",\"branchKanjiName\":\"中国支店\",\"currencyCode\":\"JPY\",\"additionalAccountType\":\"\","
          +  "\"report\":{"
          +    "\"repaymentNotifications\":[{\"id\":\"rid101010\",\"issuedDate\":\"2024-11-23\",\"displayProductName\":\"住宅ローン\",\"displayDocumentName\":\"ご返済のご案内\"}],"
          +    "\"reviewInterestRateNotifications\":[{\"id\":\"rid101011\",\"issuedDate\":\"2024-11-22\",\"displayProductName\":\"住宅ローン\",\"displayDocumentName\":\"金利見直し方法選択のご案内\"}]}},"
          // 契約終了通知書（取扱番号あり）
          + "{\"accountId\":\"aid101005\",\"accountName\":\"メイギニン999\",\"accountKanjiName\":\"名義人999\",\"bankCode\":\"1234\",\"branchNumber\":\"999\",\"accountType\":\"001\",\"branchName\":\"ちゅうごくしてん\",\"branchKanjiName\":\"中国支店\",\"currencyCode\":\"JPY\",\"additionalAccountType\":\"\","
          +  "\"report\":{"
          +    "\"endLoanContract\":[{\"id\": \"rid101012\",\"issuedDate\": \"2024-11-21\",\"displayProductName\": \"住宅ローン\",\"displayDocumentName\": \"契約終了通知書\",\"loanSubject\": \"101\",\"loanHandlingNumber\":\"1234567\"}]}},"
          // 契約終了通知書（取扱番号なし）
          + "{\"accountId\":\"aid101006\",\"accountName\":\"メイギニン999\",\"accountKanjiName\":\"名義人999\",\"bankCode\":\"1234\",\"branchNumber\":\"999\",\"accountType\":\"001\",\"branchName\":\"ちゅうごくしてん\",\"branchKanjiName\":\"中国支店\",\"currencyCode\":\"JPY\",\"additionalAccountType\":\"\","
          +  "\"report\":{"
          +    "\"endLoanContract\":[{\"id\": \"rid101013\",\"issuedDate\": \"2024-11-21\",\"displayProductName\": \"住宅ローン\",\"displayDocumentName\": \"契約終了通知書\",\"loanSubject\": \"101\"}]}}"
          + "]"
          + "}";

  // ログインデータの最大値
  private LoginDetailDto getMaxLoginDetailDto() {
    // DTOのインスタンス化
    LoginDetailDto loginDetailDto = new LoginDetailDto();

    // 値のセット
    loginDetailDto.setUserName("012345678901234567890123456789012345678901234567890"
        + "1234567890123456789012345678901234567890123456789"
        + "012345678901234567890123456789012345678901234567890"
        + "1234567890123456789012345678901234567890123456789"
        + "01234567890123456789012345678901234567890123456789012345");
    loginDetailDto.setPassword("012345678901234567890123456789012345678901234567890"
        + "1234567890123456789012345678901234567890123456789"
        + "012345678901234567890123456789012345678901234567890"
        + "1234567890123456789012345678901234567890123456789"
        + "01234567890123456789012345678901234567890123456789012345");
    loginDetailDto.setAccountNumber("0123456");
    loginDetailDto.setBranchNumber("012");

    return loginDetailDto;
  }

}
