package com.moneyforward.cug_bankbookbase.service;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ResourceLoader;
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
import com.moneyforward.cug_bankbookbase.dto.ContractTerminationDto;
import com.moneyforward.cug_bankbookbase.logic.ContractTerminationApiLogic;

/**
 * 契約終了通知書取得サービス
 *
 * 選択した契約終了通知書取得を行うサービスを呼び出すクラス
 */
@Service
@Transactional


public class ContractTerminationService {

  /** 蓄積データ参照履歴情報. */
  @Autowired
  private StoredDataHistoryRepository storedDataHistoryRepository;

  /** 定数取得クラス. */
  @Autowired
  private BankBookConstantValues bankBookConstantValues;

  /** 業務API「契約終了通知書照会」呼び出し. */
  @Autowired
  private ContractTerminationApiLogic contractTerminationApiLogic;

  /** 帳票定義情報テーブルのインターフェース. */
  @Autowired
  private ReportDefinitionRepository reportDefinitionRepository;

  /** PDF情報取得サービスクラス. */
  @Autowired
  private PdfService pdfService;

  /** リソースを取得するためのインターフェース. */
  @Autowired
  ResourceLoader resource;

  /** 共通処理クラス. */
  @Autowired
  CommonUtil commonUtil;
  

  /**
   * 契約終了通知書取得を行う.
   *
   * 契約終了通知書のバイナリファイルを取得し、蓄積データ参照履歴情報テーブルに登録する
   *
   * @param userId ユーザ識別子
   * @param accountId 口座識別子
   * @param contractId 契約終了通知書識別子
   * @param type 科目コード
   * @return バイナリファイル
   * @throws ParseException 契約終了通知書取得にエラーが発生した場合
   */
  public byte[] getContractTerminationService(String userId, String accountId, String contractId, String type) throws ParseException {

    // 業務API「契約終了通知書照会」呼出
    ContractTerminationDto contractTerminationDto = contractTerminationApiLogic.callContractTerminationLogic(userId, accountId, contractId);

    // Parameterの定義
    HashMap<String, Object> param = new HashMap<String, Object>();

    // 名義人
    if (StringUtils.isNotEmpty(contractTerminationDto.getApplicantName())) {
      param.put("applicantName",contractTerminationDto.getApplicantName() + BankBookCommonConstants.HONORIFIC);
    }
    //ご契約内容
    if (StringUtils.isNotEmpty(contractTerminationDto.getContractSubstance())) {
      param.put("contractSubstance",contractTerminationDto.getContractSubstance());
    }
    // ご契約日
    if (StringUtils.isNotEmpty(contractTerminationDto.getContractDate())) {
      param.put("contractDate",commonUtil.parseDateFormat(contractTerminationDto.getContractDate()));
    }
    // お取引店
    if (StringUtils.isNotEmpty(contractTerminationDto.getBranchName())) {
      param.put("branchName",contractTerminationDto.getBranchName());
    }
    // 取扱番号(口座番号)
    if (StringUtils.isNotEmpty(contractTerminationDto.getLoanHandlingNumber())) {
      param.put("loanHandlingNumber",CommonUtil.frontZeroSuppress(contractTerminationDto.getLoanHandlingNumber()));
    }
    // ご契約金額
    if (contractTerminationDto.getContractAmount() != null) {
      param.put("contractAmount",CommonNumberUtil.addComma(contractTerminationDto.getContractAmount()));
    }
    // 契約終了日
    if (StringUtils.isNotEmpty(contractTerminationDto.getContractEndDate())) {
      param.put("contractEndDate",commonUtil.parseDateFormat(contractTerminationDto.getContractEndDate()));
    }
    // 作成日
    param.put("issuedDate",commonUtil.parseDateFormat(contractTerminationDto.getIssuedDate()));

    // 電話番号
     if (StringUtils.isNotEmpty(contractTerminationDto.getBranchPhoneNumber())) {
     param.put("branchPhoneNumber",contractTerminationDto.getBranchPhoneNumber());
    }
     
    SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");

    // template path取得
    List<ReportDefinition> reportDefinitionList = reportDefinitionRepository.findByStartDateLessThanEqual(sdf.parse(contractTerminationDto.getIssuedDate()));

    String contractTerminationReporId = bankBookConstantValues
        .findByKeyName(CugBankBookConstants.CONTRACT_TERMINATION_ID);

    // 帳票定義情報テーブル
    ReportDefinition reportDefinition = reportDefinitionList.stream()
        .filter(x -> contractTerminationReporId.equals(x.getReportTypeId()))
        .sorted(Comparator.comparing(ReportDefinition::getReportVersion, Comparator.reverseOrder())).findFirst()
        .get();

    // pdfServiceに渡すListの処理の実装
    List<ContractTerminationDto> contractTerminationList = new ArrayList<ContractTerminationDto>();
    contractTerminationList.add(contractTerminationDto);

    // PDF作成サービスクラス呼び出し
    byte[] bytes = pdfService.pdfExport(reportDefinition.getReportFormatFilePath(), contractTerminationList, param);

    StoredDataHistory storedDataHistory = new StoredDataHistory();
    storedDataHistory.setUserId(userId);
    storedDataHistory.setAccountId(accountId);
    String dataSection = CugCommonConstants.CONTRACT_TERMINATION_REPORT_TYPE_ID + "_" + type;
    storedDataHistory.setDataSection(dataSection);
    storedDataHistory.setReportId(contractId);
    storedDataHistory.setReferenceDate(contractTerminationDto.getIssuedDate());
    storedDataHistory.setReportVersion(reportDefinition.getReportVersion());
    storedDataHistory.setReportTypeId(contractTerminationReporId);

    // 蓄積データ参照履歴情報テーブルに登録する
    storedDataHistoryRepository.save(storedDataHistory);

    return bytes;

  }

}