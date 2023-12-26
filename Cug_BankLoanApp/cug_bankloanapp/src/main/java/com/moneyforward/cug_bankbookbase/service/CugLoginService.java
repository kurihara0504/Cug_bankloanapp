package com.moneyforward.cug_bankbookbase.service;

import com.moneyforward.bankbookbase.base.constance.message.BankBookMessageConstants;
import com.moneyforward.bankbookbase.base.exception.BaseAppException;
import com.moneyforward.bankbookbase.base.exception.BaseErrorLogging;
import com.moneyforward.bankbookbase.base.util.CommonUtil;
import com.moneyforward.bankbookbase.dto.ReturnReportsDto;
import com.moneyforward.bankbookbase.service.LoginService;
import com.moneyforward.cug_bankbookbase.base.constance.CugCommonConstants;
import com.moneyforward.cug_bankbookbase.dto.AccountListDto;
import com.moneyforward.cug_bankbookbase.dto.CugReportDto;
import com.moneyforward.cug_bankbookbase.dto.CugReturnReportsDto;
import com.moneyforward.cug_bankbookbase.dto.ReturnReportDto;
import com.moneyforward.cug_bankbookbase.logic.CallGetReportInfoLogic;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;

/**
 * ログインサービスクラス
 *
 * 認証を行うサービスを呼び出す.
 */
@Service
@Primary
public class CugLoginService extends LoginService {

  /** 業務API「口座帳票一覧照会 」呼び出しクラス. */
  @Autowired
  private CallGetReportInfoLogic callGetReportInfoLogic;

  /**
   * 業務API「口座帳票一覧照会 」を呼び出し、利用口座情報取得を行う.
   *
   * 業務API「口座帳票一覧照会 」を呼び出し、利用口座情報があるかチェックする
   *
   * @param String ログインID
   * @return ReturnReportsDto 帳票情報一覧
   * @throws BaseAppException 口座情報一覧のリスト内の要素の数が０の場合スローする
   */
  @Override
  public ReturnReportsDto doReport(String userId) throws RuntimeException {

    // 業務API「口座帳票一覧照会 」呼出
    AccountListDto accountList = callGetReportInfoLogic.doLogic(userId);

    if (accountList.getAccountsList().size() == 0) {
      // エラーをスローする
      throw new BaseAppException(
          // エラーコード
          BankBookMessageConstants.BBMSG100011,
          // ログ出力メッセージ
          String.format("Invalid value for the UserId=[%s]", userId),
          // ロギング
          BaseErrorLogging.Error);
    }

    // 取得結果を返す
    CugReturnReportsDto reportsDto = new CugReturnReportsDto();
    List<ReturnReportDto> returnReportDtoList = new ArrayList<ReturnReportDto>();

    // 定期預金期日のご案内のリスト
    List<ReturnReportDto> informationDepositList = new ArrayList<ReturnReportDto>();
    // 返済のご案内のリスト
    List<ReturnReportDto> informationRepaymentList = new ArrayList<ReturnReportDto>();
    // カードローンお取引内容のお知らせのリスト
    List<ReturnReportDto> cardLoanTransactionList = new ArrayList<ReturnReportDto>();
    // 金利見直し方法選択のご案内のリスト
    List<ReturnReportDto> informationSelectingRateList = new ArrayList<ReturnReportDto>();
    // 外貨定期預金預入のご案内のリスト
    List<ReturnReportDto> foreignCurrencyDepositOpenList = new ArrayList<ReturnReportDto>();
    // 外貨定期預金期日のご案内のリスト
    List<ReturnReportDto> foreignCurrencyDueDateList = new ArrayList<ReturnReportDto>();
    // 契約終了通知書のリスト
    List<ReturnReportDto> contractTerminationList = new ArrayList<ReturnReportDto>();
    // 変動金利定期預金のお知らせのリスト
    List<ReturnReportDto> noticeFloatingRateList = new ArrayList<ReturnReportDto>();

    accountList.getAccountsList().forEach(account -> {
      
      // 口座番号・CIF番号・ローン取り扱い番号の先頭0を取り除く
      if (account.getAccountNumber() != null) {
        account.setAccountNumber(CommonUtil.frontZeroSuppress(account.getAccountNumber()));
      }
      if (account.getCifNumber() != null) {
        account.setCifNumber(CommonUtil.frontZeroSuppress(account.getCifNumber()));
      }
      if (account.getLoanHandlingNumber() != null) {
        account.setLoanHandlingNumber(CommonUtil.frontZeroSuppress(account.getLoanHandlingNumber()));
      }

      CugReportDto report = account.getReport();
      report.getInformationDeposit().forEach(informationDeposit -> {
        // 定期預金期日のご案内
        ReturnReportDto returnReportDto = new ReturnReportDto();
        returnReportDto.setReportId(informationDeposit.getId());
        returnReportDto.setSubAccountId(account.getAccountId());
        returnReportDto.setProductName(CugCommonConstants.INFORMATION_DEPOSIT_PRODUCT_NAME); 
        returnReportDto.setReportName(CugCommonConstants.INFORMATION_DEPOSIT_DOCUMENT_NAME);
        if (account.getAccountNumber() != null) {
          returnReportDto.setNumber(account.getBranchKanjiName() + CugCommonConstants.NUMBER_SLASH + account.getAccountNumber());
        } else {
          returnReportDto.setNumber(account.getBranchKanjiName());
        }
        returnReportDto.setType(account.getAccountType());
        returnReportDto.setDateOfIssue(informationDeposit.getIssuedDate());
        returnReportDto.setReportDistinctionFlag(CugCommonConstants.INFORMATION_DEPOSIT_FLAG);
        informationDepositList.add(returnReportDto);
      });

      report.getInformationRepayment().forEach(informationRepayment -> {
        // 返済のご案内
        ReturnReportDto returnReportDto = new ReturnReportDto();
        returnReportDto.setReportId(informationRepayment.getId());
        returnReportDto.setProductName(informationRepayment.getDisplayProductName());
        returnReportDto.setReportName(informationRepayment.getDisplayDocumentName());
        returnReportDto.setSubAccountId(account.getAccountId());
        if (account.getLoanHandlingNumber() != null) {
          returnReportDto.setNumber(
              account.getBranchKanjiName() + CugCommonConstants.NUMBER_SLASH + account.getLoanHandlingNumber());
        } else if (account.getCifNumber() != null) {
          returnReportDto
              .setNumber(account.getBranchKanjiName() + CugCommonConstants.NUMBER_SLASH + account.getCifNumber());
        } else if (account.getAccountNumber() != null) {
          returnReportDto
              .setNumber(account.getBranchKanjiName() + CugCommonConstants.NUMBER_SLASH + account.getAccountNumber());
        } else {
          returnReportDto.setNumber(account.getBranchKanjiName());
        }
        returnReportDto.setType(account.getAccountType());
        returnReportDto.setDateOfIssue(informationRepayment.getIssuedDate());
        returnReportDto.setReportDistinctionFlag(CugCommonConstants.INFORMATION_REPAYMENT_FLAG);
        informationRepaymentList.add(returnReportDto);
      });
      report.getCardLoanTransaction().forEach(cardLoanTransaction -> {
        // カードローンお取り引き内容のお知らせ
        ReturnReportDto returnReportDto = new ReturnReportDto();
        returnReportDto.setReportId(cardLoanTransaction.getId());
        returnReportDto.setSubAccountId(account.getAccountId());
        returnReportDto.setProductName(cardLoanTransaction.getDisplayProductName());
        returnReportDto.setReportName(cardLoanTransaction.getDisplayDocumentName());
        if (account.getAccountNumber() != null) {
          returnReportDto
              .setNumber(account.getBranchKanjiName() + CugCommonConstants.NUMBER_SLASH + account.getAccountNumber());
        } else {
          returnReportDto.setNumber(account.getBranchKanjiName());
        }
        returnReportDto.setType(account.getAccountType());
        returnReportDto.setDateOfIssue(cardLoanTransaction.getIssuedDate());
        returnReportDto.setReportDistinctionFlag(CugCommonConstants.CARD_LOAN_TRANSACTION_FLAG);
        cardLoanTransactionList.add(returnReportDto);
      });
      report.getInformationSelectingRate().forEach(informationSelectingRate -> {
        // 金利見直し方法選択のご案内
        ReturnReportDto returnReportDto = new ReturnReportDto();
        returnReportDto.setReportId(informationSelectingRate.getId());
        returnReportDto.setSubAccountId(account.getAccountId());
        returnReportDto.setProductName(informationSelectingRate.getDisplayProductName());
        returnReportDto.setReportName(informationSelectingRate.getDisplayDocumentName());
        if (account.getLoanHandlingNumber() != null) {
          returnReportDto.setNumber(
              account.getBranchKanjiName() + CugCommonConstants.NUMBER_SLASH + account.getLoanHandlingNumber());
        } else if (account.getCifNumber() != null) {
          returnReportDto
              .setNumber(account.getBranchKanjiName() + CugCommonConstants.NUMBER_SLASH + account.getCifNumber());
        } else if (account.getAccountNumber() != null) {
          returnReportDto
              .setNumber(account.getBranchKanjiName() + CugCommonConstants.NUMBER_SLASH + account.getAccountNumber());
        } else {
          returnReportDto.setNumber(account.getBranchKanjiName());
        }
        returnReportDto.setType(account.getAccountType());
        returnReportDto.setDateOfIssue(informationSelectingRate.getIssuedDate());
        returnReportDto.setReportDistinctionFlag(CugCommonConstants.INFORMATION_SELECTING_RATE_FLAG);
        informationSelectingRateList.add(returnReportDto);
      });
      report.getForeignCurrencyDepositOpen().forEach(foreignCurrencyDepositOpen -> {
        // 外貨定期預金預入のご案内
        ReturnReportDto returnReportDto = new ReturnReportDto();
        returnReportDto.setReportId(foreignCurrencyDepositOpen.getId());
        returnReportDto.setSubAccountId(account.getAccountId());
        returnReportDto.setProductName(foreignCurrencyDepositOpen.getDisplayProductName());
        returnReportDto.setReportName(foreignCurrencyDepositOpen.getDisplayDocumentName());
        if (account.getAccountNumber() != null) {
          returnReportDto
              .setNumber(account.getBranchKanjiName() + CugCommonConstants.NUMBER_SLASH + account.getAccountNumber());
        } else {
          returnReportDto.setNumber(account.getBranchKanjiName());
        }
        returnReportDto.setType(account.getAccountType());
        returnReportDto.setDateOfIssue(foreignCurrencyDepositOpen.getIssuedDate());
        returnReportDto.setReportDistinctionFlag(CugCommonConstants.FOREIGN_CURRENCY_DEPOSIT_OPEN_FLAG);
        foreignCurrencyDepositOpenList.add(returnReportDto);
      });
      // 外貨定期預金期日のご案内
      report.getForeignCurrencyDueDate().forEach(foreignCurrencyDueDate -> {
        ReturnReportDto returnReportDto = new ReturnReportDto();
        returnReportDto.setReportId(foreignCurrencyDueDate.getId());
        returnReportDto.setSubAccountId(account.getAccountId());
        returnReportDto.setProductName(foreignCurrencyDueDate.getDisplayProductName());
        returnReportDto.setReportName(foreignCurrencyDueDate.getDisplayDocumentName());
        if (account.getAccountNumber() != null) {
          returnReportDto
              .setNumber(account.getBranchKanjiName() + CugCommonConstants.NUMBER_SLASH + account.getAccountNumber());
        } else {
          returnReportDto.setNumber(account.getBranchKanjiName());
        }
        returnReportDto.setType(account.getAccountType());
        returnReportDto.setDateOfIssue(foreignCurrencyDueDate.getIssuedDate());
        returnReportDto.setReportDistinctionFlag(CugCommonConstants.FOREIGN_CURRENCY_DUE_DATE_FLAG);
        foreignCurrencyDueDateList.add(returnReportDto);
      });
      // 契約終了通知書 
      report.getContractTermination().forEach(contractTermination -> {
        ReturnReportDto returnReportDto = new ReturnReportDto();
        returnReportDto.setReportId(contractTermination.getId());
        returnReportDto.setSubAccountId(account.getAccountId());
        returnReportDto.setProductName(contractTermination.getDisplayProductName());
        returnReportDto.setReportName(contractTermination.getDisplayDocumentName());
        if (contractTermination.getLoanHandlingNumber() != null) {
          returnReportDto.setNumber(
              account.getBranchKanjiName() + CugCommonConstants.NUMBER_SLASH + CommonUtil.frontZeroSuppress(contractTermination.getLoanHandlingNumber()));
        } else {
          returnReportDto.setNumber(account.getBranchKanjiName());
        }
        returnReportDto.setType(contractTermination.getLoanSubject());
        returnReportDto.setDateOfIssue(contractTermination.getIssuedDate());
        returnReportDto.setReportDistinctionFlag(CugCommonConstants.CONTRACT_TERMINATION_FLAG);
        contractTerminationList.add(returnReportDto);
      });
      report.getNoticeFloatingRate().forEach(noticeFloatingRate -> {
        // 変動金利定期預金のお知らせ
        ReturnReportDto returnReportDto = new ReturnReportDto();
        returnReportDto.setReportId(noticeFloatingRate.getId());
        returnReportDto.setSubAccountId(account.getAccountId());
        returnReportDto.setProductName(noticeFloatingRate.getDisplayProductName());
        returnReportDto.setReportName(noticeFloatingRate.getDisplayDocumentName());
        if (account.getAccountNumber() != null) {
          returnReportDto.setNumber(account.getBranchKanjiName() + CugCommonConstants.NUMBER_SLASH + account.getAccountNumber());
        } else {
          returnReportDto.setNumber(account.getBranchKanjiName());
        }
        returnReportDto.setType(account.getAccountType());
        returnReportDto.setDateOfIssue(noticeFloatingRate.getIssuedDate());
        returnReportDto.setReportDistinctionFlag(CugCommonConstants.NOTICE_FLOATING_RATE_FLAG);
        noticeFloatingRateList.add(returnReportDto);
      });
    });

    /*
     * 以下発行日が同じ場合の表示順に各帳票をレスポンス用リストへ格納していく 1. 定期預金期日のご案内 2. 変動金利定期預金のお知らせ 3. 返済のご案内
     * 4. 金利見直し方法選択のご案内 5. カードローンお取引内容のお知らせ 6. 契約終了通知書 7. 外貨定期預金預入のご案内 8. 外貨定期預金期日のご案内
     */

    // 1. 定期預金期日のご案内
    informationDepositList.forEach(x -> {
      returnReportDtoList.add(x);
    });
    // 2. 変動金利定期預金のお知らせ
    noticeFloatingRateList.forEach(x -> {
      returnReportDtoList.add(x);
    });
    // 3. 返済のご案内
    informationRepaymentList.forEach(x -> {
      returnReportDtoList.add(x);
    });
    // 4. 金利見直し方法選択のご案内
    informationSelectingRateList.forEach(x -> {
      returnReportDtoList.add(x);
    });
    // 5. カードローンお取引内容のお知らせ
    cardLoanTransactionList.forEach(x -> {
      returnReportDtoList.add(x);
    });
    // 6. 契約終了通知書
    contractTerminationList.forEach(x -> {
      returnReportDtoList.add(x);
    });
    // 7. 外貨定期預金預入のご案内
    foreignCurrencyDepositOpenList.forEach(x -> {
      returnReportDtoList.add(x);
    });
    // 8. 外貨定期預金期日のご案内
    foreignCurrencyDueDateList.forEach(x -> {
      returnReportDtoList.add(x);
    });

    // 発行年月日の降順でソート
    reportsDto.setReports(returnReportDtoList.stream()
        .sorted(Comparator.comparing(ReturnReportDto::getDateOfIssue, Comparator.nullsLast(Comparator.reverseOrder())))
        .collect(Collectors.toList()));
    
    return reportsDto;
  }

}