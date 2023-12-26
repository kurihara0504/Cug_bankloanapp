// PDFボタン押下
$(document).on('click', '.pdf-button', function () {
  nidoBoushi(true);
  // 押下した帳票の項番を取得する
  var report_index = $(this).data('report');
  // 口座識別子と帳票識別子と帳票判別フラグを取り出す
  var sub_account_id = reports[report_index].sub_account_id;
  var report_id = reports[report_index].report_id;
  // 科目コード
  var type = reports[report_index].type;
  // 照会帳票取得_種類コード
  var classification = reports[report_index].classification;

  // 各帳票情報取得要求APIへリクエストしてPDFをダウンロード
  var varUrl = "loan/accounts/";
  // 定期預金期日のご案内
  if ($(this).hasClass('information_deposit')) {
    var sendUrl = bankPath + varUrl + sub_account_id + "/information_deposit_report" + "?report_id=" + report_id;
  // 返済のご案内
  } else if ($(this).hasClass('information_repayment')) {
    var sendUrl = bankPath + varUrl + sub_account_id + "/information_repayment_report" + "?report_id=" + report_id + "&type=" + type;
  // カードローンお取引内容のお知らせ
  } else if ($(this).hasClass('card_loan_transaction')) {
    var sendUrl = bankPath + varUrl + sub_account_id + "/card_loan_transaction_report" + "?report_id=" + report_id + "&type=" + type;
  // 金利見直し方法選択のご案内
  } else if ($(this).hasClass('information_selecting_rate')) {
    var sendUrl = bankPath + varUrl + sub_account_id + "/information_selecting_rate_report" + "?report_id=" + report_id + "&type=" + type;
  // 外貨定期預金預入のご案内
  } else if ($(this).hasClass('foreign_currency_deposit_open')) {
    var sendUrl = bankPath + varUrl + sub_account_id + "/foreign_currency_deposit_open_report" + "?report_id=" + report_id + "&type=" + type;
  // 外貨定期預金期日のご案内
  } else if ($(this).hasClass('foreign_currency_due_date')) {
    var sendUrl = bankPath + varUrl + sub_account_id + "/foreign_currency_due_date_report" + "?report_id=" + report_id + "&type=" + type;
  // 契約終了通知書
  } else if ($(this).hasClass('contract_termination')) {
    var sendUrl = bankPath + varUrl + sub_account_id + "/contract_termination_report" + "?contract_id=" + report_id + "&type=" + type;
  // 変動金利定期預金のお知らせ
  } else if ($(this).hasClass('notice_floating_rate')) {
    var sendUrl = bankPath + varUrl + sub_account_id + "/notice_floating_rate_report" + "?report_id=" + report_id;
  };
  
  window.open(sendUrl);
  nidoBoushi(false);
});

// スワイパー初期化
const swiper = new Swiper(".swiper", {
  slidesPerView: 'auto',
  navigation: {
    prevEl: ".swiper-button-prev",
    nextEl: ".swiper-button-next",
  }
});

// 種類タブクリック
$(document).on('click','.swiper-slide', function(){
  // クリックしたタブのクラス名を保持
  var this_class = $(this).attr("class");	
  
  // is-activeクラスの更新
  $('.swiper-slide' + '.is-active').removeClass('is-active'); 
  $(this).addClass('is-active');
  
  // クリックしたタブのhtml要素を保持
  var active_type = $(this).html();
  
  $.each(reports, function (i, value) {
    if (active_type === SELECT_TYPE_CODE.all_type) {
      // 「全て」を選択した場合、全ての口座を表示
      $('#reports' + i).show();
    } else if (active_type === get_type_val(value.type)) {
      // 対象の口座の場合、表示
      $('#reports' + i).show();
    } else {
      // 対象でない口座の場合、非表示
      $('#reports' + i).hide();
    };
  });
  
  if(this_class.indexOf('is-active') == -1){
    // 選択中以外のタブをクリックした場合、ページの一番上部までスクロールする(正常動作のため遅延実行)
    window.setTimeout(scrollTop, 10);
  };
});

// ページの一番上部までスクロールする関数
function scrollTop(){
  window.scrollTo({
    top: 0,
    behavior: 'smooth'
  });
}