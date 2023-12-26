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
import com.moneyforward.cug_bankbookbase.service.InformationDepositService;

import jakarta.servlet.http.HttpServletRequest;

/**
 * 定期預金期日のご案内要求コントローラクラス.
 *
 * 定期預金期日のご案内の要求をコントロールするクラス
 * 
 *  * @startuml
 * title 定期預金期日のご案内
 * ->InformationDepositController:@PathVariable("account_id"),@RequestParam(name = "report_id", defaultValue = "")
 * InformationDepositController->InformationDepositService:BaseSession.SessionDto.userId,@PathVariable("account_id"),@RequestParam(name = "report_id", defaultValue = "")
 * InformationDepositService->InformationDepositLogic:BaseSession.SessionDto.userId,@PathVariable("account_id"),@RequestParam(name = "report_id", defaultValue = "")
 * InformationDepositLogic->業務API_定期預金満期案内照会:BaseSession.SessionDto.userId,@PathVariable("account_id"),@RequestParam(name = "report_id", defaultValue = "")
 * InformationDepositLogic<-業務API_定期預金満期案内照会:InformationDepositDto
 * InformationDepositService<-InformationDepositLogic:InformationDepositDto
 * InformationDepositService->PdfService:ReportDefinition.reportFormatFilePath,List<InformationDepositDto>,HashMap<"param名","paramの値">
 * InformationDepositService<-PdfService:byte[]
 * InformationDepositController<-InformationDepositService:byte[]
 * <-InformationDepositController:ResponseEntity<byte[]> * 
 * @enduml
 **/
@RestController
public class InformationDepositController {

  /** 定期預金期日のご案内取得サービスクラス. */
  @Autowired
  private InformationDepositService informationDepositService;

  /** ベースセッションクラス. */
  @Autowired
  private BaseSession baseSession;

  /** ログ出力クラス. */
  private BaseLogging baseLogging = new BaseLogging(InformationDepositController.class);

  /** セッションクラス. */
  @Value("${session.domain}")
  private String contextPath;

  /** 共通処理. */
  @Autowired
  private CommonUtil commonUtil;

  /**
   * 定期預金期日のご案内取得要求コントロール.
   *
   * 定期預金期日のご案内を取得するサービスを呼び出す
   * 例外が発生した場合はHTMLを返却する
   *
   * @param accountId 口座識別子
   * @param reportId 定期預金期日のご案内識別子
   * @param requestHeaderDto HTTPリクエストヘッダ
   * @return 作成したPDFのバイナリファイル、または例外が発生した場合のHTML
   */
  @RequestMapping(value = "/loan/accounts/{account_id}/information_deposit_report", method = RequestMethod.GET)
  @ResponseBody
  public Object getInformationDepositReport(
      @PathVariable("account_id") String accountId,
      @RequestParam(name = "report_id", defaultValue = "") String reportId,
      @BaseRequest RequestHeaderDto requestHeaderDto) {
    
    try {
      // 送信元ページが正しいか確認する
      commonUtil.refererCheck(requestHeaderDto.getReferer());
      // 口座識別子・帳票識別子が正しいか確認する
      commonUtil.validate(accountId, reportId);

      byte[] data = informationDepositService.getInformationDeposit(baseSession.get().getUserId(), accountId, reportId);
      
      // HTTPレスポンスヘッダの付与
      HttpHeaders header = commonUtil.addHttpHeadersForPdf();
      header.add(BankBookCommonConstants.HEADER_CONTENT_DISPOSITION, 
          BankBookCommonConstants.HEADER_INLINE + BankBookCommonConstants.HEADER_FILENAME + URLEncoder.encode(CugCommonConstants.INFORMATION_DEPOSIT_DOCUMENT_NAME + ".pdf", BankBookCommonConstants.UTF8));

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
