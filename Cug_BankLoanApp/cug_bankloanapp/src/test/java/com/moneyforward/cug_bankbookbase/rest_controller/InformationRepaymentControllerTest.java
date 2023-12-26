package com.moneyforward.cug_bankbookbase.rest_controller;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertTrue;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.hamcrest.MatcherAssert;
import org.hamcrest.beans.SamePropertyValuesAs;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import org.junit.runner.RunWith;
import org.mockito.Answers;
import org.mockito.ArgumentMatchers;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.mock.web.MockHttpSession;
import org.springframework.mock.web.MockServletContext;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.util.ReflectionTestUtils;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import com.moneyforward.bankbookbase.base.session.BaseSession;
import com.moneyforward.bankbookbase.dto.SessionDto;
import com.moneyforward.cug_bankbookbase.service.InformationRepaymentService;

import jakarta.servlet.ServletContext;
import jakarta.servlet.SessionCookieConfig;
import jakarta.servlet.http.HttpSession;

/**
 * ご返済のご案内コントローラ　テストクラス.
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class InformationRepaymentControllerTest {

  @Rule
  public ExpectedException exception = ExpectedException.none();

  /** テスト対象クラス. */
  @Autowired
  private InformationRepaymentController informationRepaymentController;

  /** セッションクラス. */
  @Autowired
  private BaseSession baseSession;

  /** Mock対象クラス. */
  @Mock(answer = Answers.RETURNS_DEEP_STUBS)
  private InformationRepaymentService informationRepaymentService;

  @Autowired
  private WebApplicationContext wac;

  @Value("${BANK_PRODUCTION_URL}")
  private String bankProductionUrl;

  private MockMvc mvc;

  private HttpSession session;

  private String sessionUser = "12345678901234567890123456789012";

  MockHttpSession mockSession = new MockHttpSession();

  /** 初期処理. */
  @Before
  public void before() throws Exception {
    mvc = MockMvcBuilders.webAppContextSetup(this.wac).build();
    ReflectionTestUtils.setField(informationRepaymentController, "baseSession", baseSession);
    ReflectionTestUtils.setField(informationRepaymentController, "informationRepaymentService", informationRepaymentService);

    // サーブレットコンテキストのモック化
    ServletContext servletContext = new MockServletContext();

    // サーブレットコンテキストからセッションクッキーコンフィグの取り出し
    SessionCookieConfig config = servletContext.getSessionCookieConfig();

    // 取り出したセッションクッキーコンフィグに値をセット
    config.setComment("test-comment");
    config.setDomain("test-domain");
    config.setHttpOnly(true);
    config.setMaxAge(1000);
    config.setName("test-name");
    config.setPath("test-path");
    config.setSecure(false);

    // セッションの生成（上記で作成したサーブレットコンテキストをセット）
    session = new MockHttpSession(servletContext, "test-session-id");

    // Dtoの作成とセッションへのセット
    SessionDto sessionDto = new SessionDto();
    sessionDto.setUserId(sessionUser);

    session.setAttribute("BaseSessionDto", sessionDto);
  }

  /**
   * ご返済のご案内取得要求　
   * 正常系　最大桁.
   */
  @Test
  public void testAccountIdReportIdMax() throws Exception {

    // Jsonデータ文字列を生成する
    String accountId = "01234567890123456789";
    String reportId = "0123456789012345678";
    String type = "1";
    String query = "?report_id=" + reportId + "&type=" + type;

    // stubを呼び出す
    MvcResult mvcResult = mvc.perform(
        get("/loan/accounts/"+ accountId + "/information_repayment_report" + query)
        .session((MockHttpSession) session)
        .header("X-session-user-identification-code", sessionUser)
        .header("Referer", bankProductionUrl)
        )
        .andExpect(status().is(200))
        .andReturn();

    // Mock化
    // メソッドの戻り値を設定
    byte[] array = new byte[5];
    Mockito.when(informationRepaymentService.getInformationRepayment(ArgumentMatchers.any(),ArgumentMatchers.any(),ArgumentMatchers.any(), ArgumentMatchers.any())).thenReturn(array);

    byte[] response = mvcResult.getResponse().getContentAsByteArray();

    MatcherAssert.assertThat(response, is(SamePropertyValuesAs.samePropertyValuesAs(array)));
  }

  /**
   * ご返済のご案内取得要求　
   * 正常系　最小桁.
   */
  @Test
  public void testAccountIdReportIdMin() throws Exception {

    // Jsonデータ文字列を生成する
    String accountId = "1";
    String reportId = "1";
    String type = "1";
    String query = "?report_id=" + reportId + "&type=" + type;

    // stubを呼び出す
    MvcResult mvcResult = mvc.perform(
        get("/loan/accounts/"+ accountId + "/information_repayment_report" + query)
        .session((MockHttpSession) session)
        .header("X-session-user-identification-code", sessionUser)
        .header("Referer", bankProductionUrl)
        )
        .andExpect(status().is(200))
        .andReturn();

    // Mock化
    // メソッドの戻り値を設定
    byte[] array = new byte[5];
    Mockito.when(informationRepaymentService.getInformationRepayment(ArgumentMatchers.any(),ArgumentMatchers.any(),ArgumentMatchers.any(),ArgumentMatchers.any())).thenReturn(array);

    byte[] response = mvcResult.getResponse().getContentAsByteArray();

    MatcherAssert.assertThat(response, is(SamePropertyValuesAs.samePropertyValuesAs(array)));
  }

  /**
   * ご返済のご案内取得要求　
   * 異常系　帳票識別子がnull.
   */
  @Test
  public void testReportIdIsNull() throws Exception {

    // Jsonデータ文字列を生成する
    String accountId = "1";
    String reportId = "";
    String type = "1";
    String query = "?report_id=" + reportId + "&type=" + type;

    // stubを呼び出す
    MvcResult mvcResult = mvc.perform(
        get("/loan/accounts/"+ accountId + "/information_repayment_report" + query)
        .session((MockHttpSession) session)
        .header("X-session-user-identification-code", sessionUser)
        .header("Referer", bankProductionUrl)
        )
        .andExpect(status().is(302))
        .andReturn();

    String response = mvcResult.getResponse().getRedirectedUrl();
    assertTrue(response.contains("app/denshikouhu/sp2/error.html"));
  }
  
  /**
   * 異常系　Refererがnull
   */
  @Test
  public void testErrorRefererIsNull() throws Exception {

    // Jsonデータ文字列を生成する
    String accountId = "123";
    String reportId = "12345";
    String type = "1";
    String query = "?report_id=" + reportId + "&type=" + type;
    
    // stubを呼び出す
    MvcResult mvcResult = mvc.perform(
        get("/loan/accounts/"+ accountId + "/information_repayment_report" + query)
        .session((MockHttpSession) session)
        .header("X-session-user-identification-code", sessionUser)
        )
        .andExpect(status().is(302))
        .andReturn();

    String response = mvcResult.getResponse().getRedirectedUrl();

    assertTrue(response.contains("app/denshikouhu/sp2/error.html"));
  }

  /**
   * 異常系　Refererが想定外
   */
  @Test
  public void testErrorRefererIsUnexpected() throws Exception {

    // Jsonデータ文字列を生成する
    String accountId = "123";
    String reportId = "12345";
    String type = "1";
    String query = "?report_id=" + reportId + "&type=" + type;

    // stubを呼び出す
    MvcResult mvcResult = mvc.perform(
        get("/loan/accounts/"+ accountId + "/information_repayment_report" + query)
        .session((MockHttpSession) session)
        .header("X-session-user-identification-code", sessionUser)
        .header("Referer", "hogehogehogehoge")
        )
        .andExpect(status().is(302))
        .andReturn();

    String response = mvcResult.getResponse().getRedirectedUrl();

    assertTrue(response.contains("app/denshikouhu/sp2/error.html"));
  }

}
