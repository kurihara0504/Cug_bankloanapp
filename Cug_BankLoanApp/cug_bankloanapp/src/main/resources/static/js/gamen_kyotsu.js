// 口座科目の定数化
const TYPE_CODE = {
  // 定期預金
  'teiki' : '006',
  // カードローン
  'card_loan' : '020',
  // 外貨定期預金(米ドル)
  'foreign_deposit_usa' : '060',
  // 外貨定期預金(ユーロ)
  'foreign_deposit_eu' : '061',
  // 外貨定期預金(豪ドル)
  'foreign_deposit_aus' : '062',
  // 外貨定期預金(ニュージーランドドル)
  'foreign_deposit_nzl' : '063',
  // 外貨定期預金(人民元)
  'foreign_deposit_cn' : '064',
  // 教育ローン(当座貸越)
  'education_loan_overdraft' : '090',
  // 住宅ローン
  'housing_loan': '101',
  // 教育ローン
  'education_loan' : '111',
  // その他ローン
  'other_loan' : '150', 
};
// 口座科目の定数化を変更禁止
Object.freeze(TYPE_CODE);

// 口座科目のリスト
const TYPE_CODE_LIST = [
  TYPE_CODE.teiki,
  TYPE_CODE.card_loan,
  TYPE_CODE.foreign_deposit_usa,
  TYPE_CODE.foreign_deposit_eu,
  TYPE_CODE.foreign_deposit_aus,
  TYPE_CODE.foreign_deposit_nzl,
  TYPE_CODE.foreign_deposit_cn,
  TYPE_CODE.education_loan_overdraft,
  TYPE_CODE.housing_loan,
  TYPE_CODE.education_loan,
  TYPE_CODE.other_loan, 
];

// 取得した科目コードと口座科目のマッチング
function type_match(type) {
  var is_defined = false;
  $.each(TYPE_CODE_LIST, function (index, value) {
    if (value === type) {
      is_defined = true;
    };
  });
  return is_defined;
};

// タブの選択肢（種類）
const SELECT_TYPE_CODE = {
  'all_type': '全て',
};
// タブの選択肢（種類）を変更禁止
Object.freeze(SELECT_TYPE_CODE);

// 口座科目変換
function get_type_val(typeCd) {

  if (typeCd == TYPE_CODE.teiki){
	  return "預金";
  } else if (typeCd == TYPE_CODE.card_loan || typeCd == TYPE_CODE.education_loan_overdraft || typeCd == TYPE_CODE.housing_loan ||
             typeCd == TYPE_CODE.education_loan || typeCd == TYPE_CODE.other_loan) {
	  return "ローン";
  } else if (typeCd == TYPE_CODE.foreign_deposit_usa || typeCd == TYPE_CODE.foreign_deposit_eu || typeCd == TYPE_CODE.foreign_deposit_aus ||
             typeCd == TYPE_CODE.foreign_deposit_nzl || typeCd == TYPE_CODE.foreign_deposit_cn){
	  return "外貨";
  }; 
};

// 画面切り替え
function login() {
  show("page_login");
};

function logout() {
  show("page_logout");
};

function syoukai() {
  show("page_syoukai");
};

function syoukai_nasi() {
  show("page_syoukai_nasi");
};

// 画面切り替え
function show(slide) {
  var page_lg = document.getElementById("page_login");
  var page_lo = document.getElementById("page_logout");
  var page_sy = document.getElementById("page_syoukai");
  var page_sn = document.getElementById("page_syoukai_nasi");
  if (slide == "page_login") {
    page_lg.style.display = "block";
    page_lo.style.display = "none";
    page_sy.style.display = "none";
    page_sn.style.display = "none";
    $(window).scrollTop(0);
  } else if (slide == "page_logout") {
    page_lg.style.display = "none";
    page_lo.style.display = "block";
    page_sy.style.display = "none";
    page_sn.style.display = "none";
    $(window).scrollTop(0);
  } else if (slide == "page_syoukai") {
    page_lg.style.display = "none";
    page_lo.style.display = "none";
    page_sy.style.display = "block";
    page_sn.style.display = "none";
    $(window).scrollTop(0);
  } else if (slide == "page_syoukai_nasi") {
    page_lg.style.display = "none";
    page_lo.style.display = "none";
    page_sy.style.display = "none";
    page_sn.style.display = "block";
    $(window).scrollTop(0);
  };
};

// ドロワーを閉じる
function closeDrawer() {
  // ドロワーを閉じる
  $('.mdl-layout__drawer').removeClass('is-visible');
  // 網掛け部を非表示
  $('.mdl-layout__obfuscator').removeClass('is-visible');
};
// ログアウト
function logout() {
  doLogoutAjax("logout");
};
// エラーメッセージ表示・非表示
function outputErrMsg(err_msg) {
  $(".err_msg").text(err_msg);
  $('.err_msg').css('color', 'red');
  if (err_msg != "") {
    $('main').scrollTop(0);
  };
};
// インフォメーションメッセージ表示・非表示
function outputInfoMsg(err_msg) {
  $(".err_msg").text(err_msg);
  $('.err_msg').css('color', 'black');
};
// 入力チェックエラーメッセージ表示・非表示
function outputValErrMsg(err_msg) {
  if ($(".err_msg").text() != "") {
    $(".err_msg").append("<br>");
  };
  $(".err_msg").append(err_msg);
  $('.err_msg').css('color', 'red');
};
// ボタン活性・非活性(二度押し防止)
function nidoBoushi(disabled) {
  $("#btnLogin1").prop("disabled", disabled);
  $("#btnLogin2").prop("disabled", disabled);
  $(".pdf-button").prop("disabled", disabled);
};
// 日付をフォーマットし変換する
function formatDate(date) {
  format = 'YYYY-MM-DD';
  format = format.replace(/YYYY/g, date.getFullYear());
  format = format.replace(/MM/g, ('0' + (date.getMonth() + 1)).slice(-2));
  format = format.replace(/DD/g, ('0' + date.getDate()).slice(-2));
  return format;
};
// 日付をフォーマットし変換する(日本語)
function formatDateToJapanese(date) {
  format = 'YYYY年MM月DD日';
  format = format.replace(/YYYY/, date.getFullYear());
  format = format.replace(/MM/, date.getMonth() + 1);
  format = format.replace(/DD/, date.getDate());
  return format;
};
// 口座選択画面表示情報初期化
function clearSelectAccountData() {
  $('#subaccount_type').empty();
  $('#subaccount_user_name').empty();
  $('#subaccount_branch_number').empty();
  $('#subaccount_branch').empty();
  $('#subaccount_number').empty();
};

// ちゅうぎん電子交付サービスとはボタン押下でちゅうぎん電子交付サービスとはへ遷移
$('.btnMypost').on('click', function () {
  window.open("https://www.chugin.co.jp/personal/service/convenience/denshikouhu/", "", "");
});
// ちゅうぎん電子交付サービス利用規定押下でちゅうぎん電子交付サービス利用規定へ遷移
$('.btnRiyou').on('click', function () {
  window.open("https://www.chugin.co.jp/personal/service/convenience/denshikouhu/denshikouhu_reg.pdf", "", "");
});
// ちゅうぎんIDボタン押下 
$('.btnThibaId').on('click', function () {
  window.open("https://www.chugin.co.jp/personal/service/convenience/api/", "", "");
});
// ちゅうぎんトップボタン押下
$('#btnTop').on('click', function () {
  window.open("https://www.chugin.co.jp/", "", "");
});

// 遷移してきたウィンドウを閉じる 
$('#window_close').on('click', function () {
  window.open('about:blank','_self').close();
});

// ブラウザ別対応
var userAgent = window.navigator.userAgent.toLowerCase();

if(userAgent.indexOf('msie') != -1 ||
   userAgent.indexOf('trident') != -1) {
  // Internet Explorer
  pass_hyoji1.style.display = "none";
  pass_hyoji2.style.display = "none";
} else if(userAgent.indexOf('edge') != -1) {
  // Edge
  pass_hyoji1.style.display = "none";
  pass_hyoji2.style.display = "none";
  $('.sticky').css('background-image', 'none');
} else if(userAgent.indexOf('chrome') != -1) {
  // Google Chrome

} else if(userAgent.indexOf('safari') != -1) {
  // Safari
  $('.icon-pass').css('left', '22px');
  $('.sticky').css('background-image', 'none');
} else if(userAgent.indexOf('firefox') != -1) {
  // FireFox
};

// 端末によるhtmlのmetaタグの設定を分ける処理
var getDevice = (function(){
if(userAgent.indexOf('iphone') >=0 ||userAgent.indexOf('ipad') >=0 || userAgent.indexOf('ipod') >=0){
　return 'iOS';
}
})();
// iOSの場合htmlのmetaタグにmaximum-scale=1.0を設定
var viewportContent;
if( getDevice == 'iOS' ){
 viewportContent = 'width=device-width,initial-scale=1,maximum-scale=1.0';
 document.querySelector("meta[name='viewport']").setAttribute("content", viewportContent);
}