package com.moneyforward.cug_bankbookbase.rest_controller;

import java.net.URLEncoder;
import java.util.ArrayList;

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
import com.moneyforward.cug_bankbookbase.service.NoticeFloatingRateService;

import jakarta.servlet.http.HttpServletRequest;

/**
 * 変動金利定期預金のお知らせ取得要求コントローラ
 *
 * 変動金利定期預金のお知らせの取得要求をコントロールするクラス.
 *
 * @startuml
 * title 変動金利定期預金のお知らせ
 * ->NoticeFloatingRateController:@PathVariable("account_id"),@RequestParam(name = "report_id", defaultValue = ""),@RequestParam(name = "type", defaultValue = "")
 * NoticeFloatingRateController->NoticeFloatingRateService:BaseSession.SessionDto.userId,@PathVariable("account_id"),@RequestParam(name = "report_id", defaultValue = "")
 * NoticeFloatingRateService->NoticeFloatingRateLogic:BaseSession.SessionDto.userId,@PathVariable("account_id"),@RequestParam(name = "report_id", defaultValue = ""),@RequestParam(name = "type", defaultValue = "")
 * NoticeFloatingRateLogic->業務API_変動金利定期預金のお知らせ照会:BaseSession.SessionDto.userId,@PathVariable("account_id"),@RequestParam(name = "report_id", defaultValue = ""),@RequestParam(name = "type", defaultValue = "")
 * NoticeFloatingRateLogic<-業務API_変動金利定期預金のお知らせ照会:NoticeFloatingRateDto
 * NoticeFloatingRateService<-NoticeFloatingRateLogic:NoticeFloatingRateDto
 * NoticeFloatingRateService->PdfService:ReportDefinition.reportFormatFilePath,List<NoticeFloatingRateDto>,HashMap<"param名","paramの値">
 * NoticeFloatingRateService<-PdfService:byte[]
 * NoticeFloatingRateController<-NoticeFloatingRateService:byte[]
 * <-NoticeFloatingRateController:ResponseEntity<byte[]> * 
 * @enduml
 **/

@RestController
public class NoticeFloatingRateController {

  /** 変動金利定期預金のお知らせサービスクラス. */
  @Autowired
  private NoticeFloatingRateService noticeFloatingRateService;

  /** ベースセッションクラス. */
  @Autowired
  private BaseSession baseSession;

  /** ログ出力クラス. */
  private BaseLogging baseLogging = new BaseLogging(NoticeFloatingRateController.class);

  /** セッションクラス. */
  @Value("${session.domain}")
  private String contextPath;

  /** 共通処理. */
  @Autowired
  private CommonUtil commonUtil;

 // 変動金利定期預金のお知らせ取得要求コントロール.
  @RequestMapping(value = "/loan/accounts/{account_id}/notice_floating_rate_report", method = RequestMethod.GET)
  @ResponseBody
  public Object getNoticeFloatingRateReport(
    @PathVariable("account_id") String accountId,
    @RequestParam(name = "report_id", defaultValue = "") String reportId,
    @BaseRequest RequestHeaderDto requestHeaderDto) {

     try {
      // 送信元ページが正しいか確認する
      commonUtil.refererCheck(requestHeaderDto.getReferer());
      // 口座識別子・帳票識別子が正しいか確認する
      commonUtil.validate(accountId,reportId);

      byte[] data = noticeFloatingRateService.getNoticeFloatingRateService(baseSession.get().getUserId(), accountId, reportId);
      
      HttpHeaders header = commonUtil.addHttpHeadersForPdf();
      header.add(BankBookCommonConstants.HEADER_CONTENT_DISPOSITION,BankBookCommonConstants.HEADER_INLINE + BankBookCommonConstants.HEADER_FILENAME + URLEncoder.encode(CugCommonConstants.NOTICE_FLOATING_RATE_PDF_NAME, BankBookCommonConstants.UTF8));
      
      return new ResponseEntity<byte[]>(data, header, HttpStatus.OK);
     }catch (Exception e) {
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


