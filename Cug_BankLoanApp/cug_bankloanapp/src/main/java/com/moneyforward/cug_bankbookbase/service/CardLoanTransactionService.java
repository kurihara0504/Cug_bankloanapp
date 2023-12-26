package com.moneyforward.cug_bankbookbase.service;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.moneyforward.bankbookbase.base.constance.BankBookCommonConstants;
import com.moneyforward.bankbookbase.base.constance.BankBookConstantValues;
import com.moneyforward.bankbookbase.base.util.CommonNumberUtil;
import com.moneyforward.bankbookbase.base.util.CommonUtil;
import com.moneyforward.bankbookbase.repository.ReportDefinitionRepository;
import com.moneyforward.bankbookbase.repository.StoredDataHistoryRepository;
import com.moneyforward.bankbookbase.repository.entity.ReportDefinition;
import com.moneyforward.bankbookbase.repository.entity.StoredDataHistory;
import com.moneyforward.bankbookbase.service.PdfService;
import com.moneyforward.cug_bankbookbase.base.constance.CugBankBookConstants;
import com.moneyforward.cug_bankbookbase.base.constance.CugCommonConstants;
import com.moneyforward.cug_bankbookbase.dto.CardLoanTransactionDetailsDto;
import com.moneyforward.cug_bankbookbase.dto.CardLoanTransactionDto;
import com.moneyforward.cug_bankbookbase.dto.CardLoanTransactionPdfAdaptDto;
import com.moneyforward.cug_bankbookbase.logic.CardLoanTransactionApiLogic;

import io.micrometer.common.util.StringUtils;

/**
 * カードローン取引内容通知書取得サービス
 *
 * 選択したカードローン取引内容通知書取得を行うサービスを呼び出すクラス
 */
@Service
@Transactional
public class CardLoanTransactionService {

  /** 蓄積データ参照履歴情報. */
  @Autowired
  private StoredDataHistoryRepository storedDataHistoryRepository;

  /** 定数取得クラス. */
  @Autowired
  private BankBookConstantValues bankBookConstantValues;

  /** 業務API「カードローン取引内容通知照会」呼び出し. */
  @Autowired
  private CardLoanTransactionApiLogic cardLoanTransactionApiLogic;

  /** 帳票定義情報テーブルのインターフェース. */
  @Autowired
  private ReportDefinitionRepository reportDefinitionRepository;

  /** PDF情報取得サービスクラス. */
  @Autowired
  private PdfService pdfService;
  
  /** 共通処理クラス. */
  @Autowired
  CommonUtil commonUtil;
  
  /** 数値共通処理クラス. */
  @Autowired
  CommonNumberUtil commonNumberUtil;

  /**
   * カードローン取引内容通知書取得を行う.
   *
   * カードローン取引内容通知書のバイナリファイルを取得し、蓄積データ参照履歴情報テーブルに登録する
   *
   * @param userId ユーザ識別子
   * @param accountId 口座識別子
   * @param reportId カードローン取引内容通知書識別子
   * @param type 科目コード
   * @return バイナリファイル
   * @throws ParseException カードローン取引内容通知書取得にエラーが発生した場合
   */
  public byte[] getCardLoanTransactionService(String userId, String accountId, String reportId, String type) throws ParseException {

    // 業務API「カードローン取引内容通知照会」呼出
    CardLoanTransactionDto cardLoanTransactionDto = cardLoanTransactionApiLogic.callCardLoanTransactionLogic(userId, accountId, reportId);

    // Parameterの定義
    HashMap<String, Object> param = new HashMap<String, Object>();


    // 明細一覧以外の情報を取得
    
    //作成日
    param.put("issuedDate",commonUtil.parseDateFormat(cardLoanTransactionDto.getIssuedDate()));

    // 名義人
    if (StringUtils.isNotEmpty(cardLoanTransactionDto.getApplicantName())) {
      param.put("applicantName", cardLoanTransactionDto.getApplicantName() + BankBookCommonConstants.HONORIFIC);
    }
    
    // 商品名
    if (StringUtils.isNotEmpty(cardLoanTransactionDto.getProductName())) {
    param.put("productName",cardLoanTransactionDto.getProductName());
    }
    
    // 返済用口座番号
    if (StringUtils.isNotEmpty(cardLoanTransactionDto.getRepaymentAccountNumber())) {
    param.put("repaymentAccountNumber",CommonUtil.frontZeroSuppress(cardLoanTransactionDto.getRepaymentAccountNumber()));
    }
    
    // ご融資利率
    if (StringUtils.isNotEmpty(cardLoanTransactionDto.getLoanInterestRate())) {
    param.put("loanInterestRate",cardLoanTransactionDto.getLoanInterestRate() + BankBookCommonConstants.PERCENT);
    }
    
    //貸越極度額
    if (cardLoanTransactionDto.getLoanLimitAmount() != null) {
    param.put("loanLimitAmount",CommonNumberUtil.addComma(cardLoanTransactionDto.getLoanLimitAmount()));
    }
    
    // 取扱店名
    if (StringUtils.isNotEmpty(cardLoanTransactionDto.getBranchName())) {
    param.put("branchName", cardLoanTransactionDto.getBranchName());
    }
    
     // 口座番号
    if (StringUtils.isNotEmpty(cardLoanTransactionDto.getAccountNumber())) {
    param.put("accountNumber", CommonUtil.frontZeroSuppress(cardLoanTransactionDto.getAccountNumber()));
    }
    
     // 取扱店名電話番号
    if (StringUtils.isNotEmpty(cardLoanTransactionDto.getBranchPhoneNumber())) {
    param.put("branchPhoneNumber", cardLoanTransactionDto.getBranchPhoneNumber());
    }

    
    // 明細一覧を取得
    List<CardLoanTransactionDetailsDto> details = cardLoanTransactionDto.getList();
    // PDF用にデータを詰め替え
    List<CardLoanTransactionPdfAdaptDto> cardLoanTransactionPdfAdaptList = new ArrayList<>();

    // Feild値の設定
    details.forEach(detail -> {
    // PDF用
      CardLoanTransactionPdfAdaptDto cardLoanTransactionPdfAdaptDto = new CardLoanTransactionPdfAdaptDto();

      // 取引日
      if (StringUtils.isNotEmpty(detail.getTransactionDate())) {
      cardLoanTransactionPdfAdaptDto.setTransactionDate(commonUtil.parseDateFormat(detail.getTransactionDate()));
      }
      // 借入額
      if (detail.getLoanAmount() != null) {
      cardLoanTransactionPdfAdaptDto.setLoanAmount(CommonNumberUtil.addComma(detail.getLoanAmount()));
      }
      // 返済額
      if (detail.getRepaymentAmount() != null) {
      cardLoanTransactionPdfAdaptDto.setRepaymentAmount(CommonNumberUtil.addComma(detail.getRepaymentAmount()));
      }
      // 摘要
      if (StringUtils.isNotEmpty(detail.getDescription())) {
      cardLoanTransactionPdfAdaptDto.setDescription(detail.getDescription());
      }
      // 借入残高
      if (detail.getLoanBalance() != null) {
      cardLoanTransactionPdfAdaptDto.setLoanBalance(CommonNumberUtil.addComma(detail.getLoanBalance()));
      }
  
      cardLoanTransactionPdfAdaptList.add(cardLoanTransactionPdfAdaptDto);

    });

    SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");

    // template path取得
    List<ReportDefinition> reportDefinitionList = reportDefinitionRepository.findByStartDateLessThanEqual(sdf.parse(cardLoanTransactionDto.getIssuedDate()));

    // 帳票種類ID取得
    String cardLoanTransactionReportId = bankBookConstantValues.findByKeyName(CugBankBookConstants.CARD_LOAN_TRANSACTION_ID);

    // 帳票定義情報テーブル
    ReportDefinition reportDefinition = reportDefinitionList.stream()
        .filter(x -> cardLoanTransactionReportId.equals(x.getReportTypeId()))
        .sorted(Comparator.comparing(ReportDefinition::getReportVersion, Comparator.reverseOrder())).findFirst()
        .get();

    // PDF作成サービスクラス呼び出し
    byte[] bytes = pdfService.pdfExport(reportDefinition.getReportFormatFilePath(), cardLoanTransactionPdfAdaptList, param);

    StoredDataHistory storedDataHistory = new StoredDataHistory();
    storedDataHistory.setUserId(userId);
    storedDataHistory.setAccountId(accountId);
    String dataSection = CugCommonConstants.CARD_LOAN_TRANSACTION_REPORT_TYPE_ID + "_" + type;
    storedDataHistory.setDataSection(dataSection);
    storedDataHistory.setReportId(reportId);
    storedDataHistory.setReferenceDate(cardLoanTransactionDto.getIssuedDate());
    storedDataHistory.setReportVersion(reportDefinition.getReportVersion());
    storedDataHistory.setReportTypeId(cardLoanTransactionReportId);

    // 蓄積データ参照履歴情報テーブルに登録する
    storedDataHistoryRepository.save(storedDataHistory);

    return bytes;

  }

}