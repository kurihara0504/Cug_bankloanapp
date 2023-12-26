package com.moneyforward.cug_bankbookbase.rest_controller;

import java.net.URLEncoder;
import java.util.ArrayList;

import jakarta.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.RequestAttributes;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;
import org.springframework.web.servlet.ModelAndView;

import com.moneyforward.bankbookbase.base.constance.BankBookCommonConstants;
import com.moneyforward.bankbookbase.base.logging.BaseLogging;
import com.moneyforward.bankbookbase.base.request.BaseRequest;
import com.moneyforward.bankbookbase.base.session.BaseSession;
import com.moneyforward.bankbookbase.base.util.CommonUtil;
import com.moneyforward.bankbookbase.dto.RequestHeaderDto;
import com.moneyforward.cug_bankbookbase.base.constance.CugCommonConstants;
import com.moneyforward.cug_bankbookbase.service.CardLoanTransactionService;

/**
 * カードローン取引内容通知取得要求コントローラ
 *
 * カードローン取引内容通知の取得要求をコントロールするクラス.
 *
 * @startuml
 * title カードローン取引内容通知表取得要求
 * ->CardLoanTransactionController:@PathVariable("account_id"),@RequestParam(name = "report_id", defaultValue = "")
 * CardLoanTransactionController->CardLoanTransactionService:BaseSession.SessionDto.userId,@PathVariable("account_id"),@RequestParam(name = "report_id", defaultValue = "")
 * CardLoanTransactionService->CardLoanTransactionApiLogic:BaseSession.SessionDto.userId,@PathVariable("account_id"),@RequestParam(name = "report_id", defaultValue = "")
 * CardLoanTransactionApiLogic->業務API_カードローン取引内容通知照会:BaseSession.SessionDto.userId,@PathVariable("account_id"),@RequestParam(name = "report_id", defaultValue = "")
 * CardLoanTransactionApiLogic<-業務API_カードローン取引内容通知照会:CardLoanTransactionDto
 * CardLoanTransactionService<-CardLoanTransactionApiLogic:CardLoanTransactionDto
 * CardLoanTransactionService->PdfService:ReportDefinition.reportFormatFilePath,List<CardLoanTransactionDto>,HashMap<"param名","paramの値">
 * CardLoanTransactionService<-PdfService:byte[]
 * CardLoanTransactionController<-CardLoanTransactionService:byte[]
 * <-CardLoanTransactionController:ResponseEntity<byte[]>
 * @enduml
 */
@RestController
public class CardLoanTransactionController {

  /** カードローン取引内容通知サービスクラス. */
  @Autowired
  private CardLoanTransactionService cardLoanTransactionService;

  /** ベースセッションクラス. */
  @Autowired
  private BaseSession baseSession;

  /** ログ出力クラス. */
  private BaseLogging baseLogging = new BaseLogging(CardLoanTransactionController.class);

  /** セッションクラス. */
  @Value("${session.domain}")
  private String contextPath;

  /** 共通処理. */
  @Autowired
  private CommonUtil commonUtil;

  /**
   * カードローン取引内容通知取得要求コントロール.
   *
   * カードローン取引内容通知を取得するサービスを呼び出す
   * 例外が発生した場合はHTMLを返却する
   *
   * @param accountId 口座識別子
   * @param reportId カードローン取引内容通知識別子
   * @param requestHeaderDto HTTPリクエストヘッダ
   * @return　作成したPDFのバイナリファイル、または例外が発生した場合のHTML
   */
  @RequestMapping(value = "/loan/accounts/{account_id}/card_loan_transaction_report", method = RequestMethod.GET)
  @ResponseBody
  public Object CardLoanTransactionReport(
      @PathVariable("account_id") String accountId,
      @RequestParam(name = "report_id", defaultValue = "") String reportId,
      @RequestParam(name = "type", defaultValue = "") String type,
      @BaseRequest RequestHeaderDto requestHeaderDto) {

    try {
      // 送信元ページが正しいか確認する
      commonUtil.refererCheck(requestHeaderDto.getReferer());
      // 口座識別子・帳票識別子が正しいか確認する
      commonUtil.validate(accountId, reportId);

      // カードローン取引内容通知表サービスクラスのカードローン用メソッドの呼び出し
      byte[] data = cardLoanTransactionService.getCardLoanTransactionService(baseSession.get().getUserId(), accountId, reportId, type);

      // HTTPレスポンスヘッダの付与
      HttpHeaders header = commonUtil.addHttpHeadersForPdf();
      header.add(BankBookCommonConstants.HEADER_CONTENT_DISPOSITION, 
              BankBookCommonConstants.HEADER_INLINE + BankBookCommonConstants.HEADER_FILENAME + URLEncoder.encode(CugCommonConstants.CARD_LOAN_TRANSACTION_PDF_NAME, BankBookCommonConstants.UTF8));

      return new ResponseEntity<byte[]>(data, header, HttpStatus.OK);
    } catch (Exception e) {
      if(!e.getMessage().contains(BankBookCommonConstants.NO_SESSION)) {
        RequestAttributes attributes = RequestContextHolder.getRequestAttributes();
        ServletRequestAttributes servletRequestAttributes = (ServletRequestAttributes) attributes;
        HttpServletRequest request = servletRequestAttributes.getRequest();
        if (request.getAttribute(BankBookCommonConstants.API_DATA) != null) {
          @SuppressWarnings("unchecked")
          ArrayList<String> logList = (ArrayList<String>) request.getAttribute(BankBookCommonConstants.API_DATA);
          logList.stream().forEachOrdered(x -> baseLogging.error(x.toString()));
        }
        baseLogging.stackTrace(e.getMessage(), e.getStackTrace());
      }

      return new ModelAndView(BankBookCommonConstants.REDIRECT_HTTPS + contextPath + CugCommonConstants.ERROR_HTML);
    }

  }

}