// セッション切れで再表示
function reloadlogin(message) {
  nidoBoushi(true);
  $("#fixed-tab-2").removeClass("is-active");
  $("#fixed-tab-1").addClass("is-active");
  $("#mdl-tabs__tab-2").removeClass("is-active");
  $('#mdl-tabs__tab-1').addClass("is-active");
  clearSelectAccountData();
  // login画面に遷移
  show("page_login");
  $(".mdl-textfield__input").val("");
  $("#login_user_name").removeClass("is-dirty");
  $("#login_password1").removeClass("is-dirty");
  $("#login_branch_number").removeClass("is-dirty");
  $("#login_account_number").removeClass("is-dirty");
  $("#login_password2").removeClass("is-dirty");
  outputErrMsg(message);
};
function reloadlogout(message) {
  clearSelectAccountData();
  // logout画面に遷移
  show("page_logout");
  $(".mdl-textfield__input").val("");
  $("#login_user_name").removeClass("is-dirty");
  $("#login_password1").removeClass("is-dirty");
  $("#login_branch_number").removeClass("is-dirty");
  $("#login_account_number").removeClass("is-dirty");
  $("#login_password2").removeClass("is-dirty");
  outputErrMsg(message);
};
// パスワード表示・非表示(ユーザータブ)
$("#pass_hyoji1").click(function () {
  classVal = $("#pass_hyoji1").attr('class');
  pass = document.getElementById("login_pass1");
  if (classVal == "icon-pass gray") {
    pass.setAttribute("type", "text");
    $(this).attr('class', 'icon-pass black');
  } else {
    pass.setAttribute("type", "password");
    $(this).attr('class', 'icon-pass gray');
  };
});
// パスワード表示・非表示(店番号・口座番号タブ)
$("#pass_hyoji2").click(function () {
  classVal = $("#pass_hyoji2").attr('class');
  pass = document.getElementById("login_pass2");
  if (classVal == "icon-pass gray") {
    pass.setAttribute("type", "text");
    $(this).attr('class', 'icon-pass black');
  } else {
    pass.setAttribute("type", "password");
    $(this).attr('class', 'icon-pass gray');
  };
});
// ログイン画面入力チェック
$('#user_name').keyup(function () {
  inputCheckUser();
});
$('#login_pass1').keyup(function () {
  inputCheckUser();
});
$('#branch_number').keyup(function () {
  inputCheckMise();
});
$('#account_number').keyup(function () {
  inputCheckMise();
});
$('#login_pass2').keyup(function () {
  inputCheckMise();
});
$('#user_name').keydown(function () {
  inputCheckUser();
});
$('#login_pass1').keydown(function () {
  inputCheckUser();
});
$('#branch_number').keydown(function () {
  inputCheckMise();
});
$('#account_number').keydown(function () {
  inputCheckMise();
});
$('#login_pass2').keydown(function () {
  inputCheckMise();
});
// ユーザー名タブのログインボタン活性非活性チェック
function inputCheckUser() {
  if ($("#user_name").val() == "" || $("#login_pass1").val() == "") {
    // 未入力あり ログインボタン非活性
    $("#btnLogin1").prop("disabled", true);
  } else {
    // 未入力なし ログインボタン活性
    $("#btnLogin1").prop("disabled", false);
  };
};
// 店番号・口座番号タブのログインボタン活性非活性チェック
function inputCheckMise() {
  if ($("#branch_number").val() == "" || $("#account_number").val() == "" || $("#login_pass2").val() == "") {
    // 未入力あり ログインボタン非活性
    $("#btnLogin2").prop("disabled", true);
  } else {
    // 未入力なし ログインボタン活性
    $("#btnLogin2").prop("disabled", false);
  };
};
// "初めての方は「ちゅうぎん電子交付サービス」利用規定をお読みください"ボタン押下で利用規約へ遷移
$('.btnPolicy').on('click', function () {
  window.open("https://www.chugin.co.jp/personal/service/convenience/denshikouhu/denshikouhu_reg.pdf", "", "");
});
// ダイアログの登録
var dialog = document.querySelector('dialog');
if (!dialog.showModal) {
  dialogPolyfill.registerDialog(dialog);
};
// パスワードを忘れた方押下でダイアログ表示
$('.show-dialog').on('click', function () {
  dialog.showModal();
});
// 設定しないボタン押下でダイアログを閉じる
$('#btnClose').on('click', function () {
  dialog.close();
});
// 設定するボタン押下でパスワードの再設定画面へ遷移とダイアログを閉じる
$('#btnResetPass').on('click', function (evt) {
  window.open("https://auth.chugin.co.jp/chugoku/user/#!/usermenu/SUM650", "", "");
  dialog.close();
});
// ログインでお困りの方ボタン押下でよくあるご質問へ遷移  
$('.btnFaq').on('click', function () {
  window.open("https://www.chugin.co.jp/faq/3/53/300/", "", "");
});
// ちゅうぎんIDをお持ちでない方ボタン押下で新規ユーザー登録画面へ遷移 
$('.btnId').on('click', function () {
  window.open("https://auth.chugin.co.jp/chugoku/user/#!/usermenu/SUM500", "", "");
});
// ログインボタン押下(ユーザー名タブ)
$(document).on('click', '#btnLogin1', function () {
  outputErrMsg("");
  var validate = false;
  if (checkUserName($("#user_name").val())) {
    // ユーザー名バリデーションチェック
    validate = true;
  };
  if (checkPassword($("#login_pass1").val())) {
    // パスワードバリデーションチェック
    validate = true;
  };
  if (validate) {
    $('main').scrollTop(0);
    return;
  };
  nidoBoushi(true);
  // 認証要求API、帳票の一覧画面へ切替
  var user_data = {
    sign_in_session_service: {
      user_name: $("#user_name").val(),
      password: $("#login_pass1").val(),
      branch_number: null,
      account_number: null
    }
  };
  doPostAjax(user_data, "sessions");
});
// ログインボタン押下(店番号・口座番号タブ)
$(document).on('click', '#btnLogin2', function () {
  outputErrMsg("");
  var validate = false;
  if (checkBranch($("#branch_number").val())) {
    // 店番号バリデーションチェック
    validate = true;
  };
  if (checkAccNum($("#account_number").val())) {
    // 口座番号バリデーションチェック
    validate = true;
  };
  if (checkPassword($("#login_pass2").val())) {
    // パスワードバリデーションチェック
    validate = true;
  };
  if (validate) {
    $('main').scrollTop(0);
    return;
  };
  nidoBoushi(true);
  // 認証要求API、帳票の一覧画面へ切替
  var user_data = {
    sign_in_session_service: {
      user_name: null,
      password: $("#login_pass2").val(),
      branch_number: $("#branch_number").val(),
      account_number: $("#account_number").val()
    }
  };
  doPostAjax(user_data, "sessions");
});

// 帳票の口座科目を集計する
var type_array = [SELECT_TYPE_CODE.all_type];
var reports = "";
var accounts_list = "";

// 帳票一覧or帳票一覧なし画面への切り替え
function to_accountSelect(fromLogin) {
  // 帳票一覧初期化
	$('#report').empty();
  if (reports !== undefined && reports != 0) {
    // テーブル繰り返し
    $.each(reports, function (i, value) {
      // 任意項目を格納する
      var product_name = "";
      var classification = "";
      var type = "";
      var number = "";
      var date_of_issue = "";
      var report_distinction_flag = "";
      var report_name = "";
      // 任意項目のnullチェック
      if (value.product_name != null) {
        product_name = value.product_name;
      };
      if (value.classification != null) {
        classification = value.classification;
      };
      if (value.type != null) {
        type = value.type;
        // タブの中に科目が存在しない場合
        if (type_array.indexOf(get_type_val(value.type)) == -1) {
          // 存在しない場合追加
          type_array.push(get_type_val(value.type));
        };
      };
      if (value.number != null) {
        number = value.number;
      };
      if (value.date_of_issue != null) {
        date_of_issue = value.date_of_issue;
      };
      if (value.report_distinction_flag != null) {
        report_distinction_flag = value.report_distinction_flag;
      };
      if (value.report_name != null) {
      	report_name = value.report_name;
      };
      var th = "<tr id=\"reports" + i + "\" class=\"reportcss\">";
      $('#report').append(th);
      // 取得した科目コードが定義されているか確認
      if (type_match(type)) {
        $('#reports' + i).append('<td><p><span class="phone_area">種類：</span>' + get_type_val(type) + '</p></td>');
      } else {
        $('#reports' + i).append('<td><p><span class="phone_area">種類：</span>' + "" + '</p></td>');
      };
      // 発行日
      if (i == 0) {
        $('#first_formatTradingDay').append(formatDateToJapanese(new Date(formatTradingDay(date_of_issue)))+'時点');
      };
      $('#reports' + i).append('<td><p><span class="phone_area">発行日：</span>' + formatTradingDay(date_of_issue) + '</p></td>');
      // 商品名
        $('#reports' + i).append('<td><p><span class="phone_area">商品名：</span>' + product_name + '</p></td>');
      // 通知または書類名
        $('#reports' + i).append('<td><p><span class="phone_area">通知または書類名：</span>' + report_name + '</p></td>');
      // 店名/口座番号
      $('#reports' + i).append('<td><p><span class="phone_area">店名／口座番号（取扱番号）：</span>' + number + '</p></td>');
      // 「PDF」ボタン
      if (report_distinction_flag == 11) {
        //  定期預金期日のご案内
        $('#reports' + i).append('<th class="button-th"><span class="phone_area"></span>' + "<button id='report_pdf_flag" + i + "' class=\"pdf-button information_deposit\" data-report=" + i + ">PDF</button></th>");
      } else if (report_distinction_flag == 12) {
        // ご返済のご案内
        $('#reports' + i).append('<th class="button-th"><span class="phone_area"></span>' + "<button id='report_pdf_flag" + i + "' class=\"pdf-button information_repayment\" data-report=" + i + ">PDF</button></th>");
      } else if (report_distinction_flag == 13) {
        // カードローンお取引内容のお知らせ
        $('#reports' + i).append('<th class="button-th"><span class="phone_area"></span>' + "<button id='report_pdf_flag" + i + "' class=\"pdf-button card_loan_transaction\" data-report=" + i + ">PDF</button></th>");
      } else if (report_distinction_flag == 14) {
        // 金利見直し方法選択のご案内
        $('#reports' + i).append('<th class="button-th"><span class="phone_area"></span>' + "<button id='report_pdf_flag" + i + "' class=\"pdf-button information_selecting_rate\" data-report=" + i + ">PDF</button></th>");
      } else if (report_distinction_flag == 15) {
        // 外貨定期預金預入のご案内
        $('#reports' + i).append('<th class="button-th"><span class="phone_area"></span>' + "<button id='report_pdf_flag" + i + "' class=\"pdf-button foreign_currency_deposit_open\" data-report=" + i + ">PDF</button></th>");
      } else if (report_distinction_flag == 16) {
        // 外貨定期預金期日のご案内
        $('#reports' + i).append('<th class="button-th"><span class="phone_area"></span>' + "<button id='report_pdf_flag" + i + "' class=\"pdf-button foreign_currency_due_date\" data-report=" + i + ">PDF</button></th>");
      } else if (report_distinction_flag == 17) {
        // 契約終了通知書
        $('#reports' + i).append('<th class="button-th"><span class="phone_area"></span>' + "<button id='report_pdf_flag" + i + "' class=\"pdf-button contract_termination\" data-report=" + i + ">PDF</button></th>");
      } else if (report_distinction_flag == 18) {
        // 変動金利定期預金のお知らせ
        $('#reports' + i).append('<th class="button-th"><span class="phone_area"></span>' + "<button id='report_pdf_flag" + i + "' class=\"pdf-button notice_floating_rate\" data-report=" + i + ">PDF</button></th>");
      }
    });

    // タブ初期化
    $('.swiper-wrapper > div').remove();
    // タブに必要な科目を追加する
    $.each(type_array, function (i, value) {
      if(value == "全て"){
        // 最初は「全て」タブを選択状態にする
        $('.swiper-wrapper').append($('<div>').html(value).addClass("swiper-slide is-active"));
      }else{
        $('.swiper-wrapper').append($('<div>').html(value).addClass("swiper-slide"));
      }
    });
    
    // バナー情報(遷移先URL・画像ソース)を外部Jsonファイルから取得する
    getBannerInfo();

    // エラーメッセージクリア、帳票一覧画面表示
    outputErrMsg("");
    $('main').scrollTop(0);
    show("page_syoukai");
  } else {
    // 帳票一覧なし画面へ遷移
    show("page_syoukai_nasi");
  };
};

// 日付をフォーマット変換する
// 文字列(YYYY-MM-DD)->文字列(YYYY/MM/DD)
function formatTradingDay(date) {
  return date.replace(/-/g, '/');
};

// 全角チェック
function checkZenkaku(txt) {

  var i = txt.length, escapeTxt;

  while (i--) {
    escapeTxt = escape(txt.substring(i, i + 1));
    if (escapeTxt.length >= 6) {
      return true;
    };
  };
  return false;
};

// 桁数チェック
function checkKeta(txt, min, max) {

  if (txt.length < min || txt.length > max) {
    return true;
  };
  return false;
};

// パスワード入力チェック
function checkPassword(txt) {

  var chk = false;

  // 全角NG
  if (checkZenkaku(txt)) {
    chk = true;
  };

  // 桁数NG
  if (checkKeta(txt, 8, 20)) {
    chk = true;
  };

  if (chk) {
    outputValErrMsg("パスワードは8～20文字以内の半角英数字で入力してください。");
  };

  return chk;

};

// ユーザー名入力チェック(半角6桁以上253桁以下)
function checkUserName(txt) {

  var chk = false;

  // 全角NG
  if (checkZenkaku(txt)) {
    chk = true;
  };

  // 桁数NG
  if (checkKeta(txt, 6, 253)) {
    chk = true;
  };

  if (chk) {
    outputValErrMsg("ユーザー名は6文字以上の半角英数字・記号で入力してください。");
  };

  return chk;
};

// 店番号入力チェック(半角数値3桁)
function checkBranch(txt) {

  if (!txt.match(/^[0-9]{3}$/)) {
    outputValErrMsg("店番号は半角数字3桁で入力してください。");
    return true;
  };

  return false;

};

// 口座番号入力チェック(半角数値7桁)
function checkAccNum(txt) {

  if (!txt.match(/^[0-9]{7}$/)) {
    outputValErrMsg("口座番号は半角数字7桁で入力してください。");
    return true;
  };

  return false;
};

// 口座番号フォーカスアウト(店番号・口座番号タブ)
document.getElementById("account_number").addEventListener('focusout', function() {
	var account_number = document.getElementById("account_number").value;
	// 1桁以上（空欄ではない）かつ6桁以下の場合、口座番号を0埋め
	if (!checkKeta(account_number,1, 6)) {
		document.getElementById("account_number").value = ('0000000' + account_number).slice(-7);
	};
});

// バナー情報(遷移先URL・画像ソース)を外部Jsonファイルから取得する
function getBannerInfo() {
	
	// バナーの遷移先URLと画像ソースが記載されたバナー情報ファイルのパス
	const bannerInfo = location.origin + '/app/denshikouhu/banner/banner_info.json';
	
	// バナー情報取得時にエラーが発生した場合のデフォルト値
	// TODO 連携されたら正式なURLを設定
	const defaultBannerUrl1 = 'https://www.chugin.co.jp/inbanhelp/lp/chuginapp/';
	const defaultBannerImg1 = './img/banner1.png';
	const defaultBannerUrl2 = 'https://www.chugin.co.jp/personal/service/card/dreame/';
	const defaultBannerImg2 = './img/banner2.png';
	
	// fetch実行
	fetch(bannerInfo)
		.then(response => response.json())
		.then(json => {
			// 取得したJSON内からURLを抽出
			json.forEach((banner, index) => {

				// 対応するhtmlタグのhref属性を設定
				const urlId = `banner_url${index + 1}`;
				const urlElement = document.getElementById(urlId);
				const imgId = `banner_img${index + 1}`;
				const imgElement = document.getElementById(imgId);
				
				// URLと画像いずれかが取得できなかった場合、デフォルト値を設定
				if (banner.url != null && banner.image != null) {
					urlElement.setAttribute('href', banner.url);
					imgElement.setAttribute('src', location.origin + banner.image);
				} else {
					if (index == 0) {
						urlElement.setAttribute('href', defaultBannerUrl1);
						imgElement.setAttribute('src',  defaultBannerImg1);
					} else {
						urlElement.setAttribute('href', defaultBannerUrl2);
						imgElement.setAttribute('src', defaultBannerImg2);
					}
				}
			});
		})
		.catch(() => {
			document.getElementById('banner_url1').setAttribute('href', defaultBannerUrl1);
			document.getElementById('banner_img1').setAttribute('src', defaultBannerImg1);
			document.getElementById('banner_url2').setAttribute('href', defaultBannerUrl2);
			document.getElementById('banner_img2').setAttribute('src', defaultBannerImg2);
		});
}
