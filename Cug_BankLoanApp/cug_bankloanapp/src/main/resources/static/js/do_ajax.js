var bankPath = "/app/denshikouhu/sp2/";
// AJAX通信 GET
function doGetAjax(varURL) {
  // プロトコル取得
  var sendURL = bankPath + varURL;
  var dfd = $.Deferred();

  $.ajax({
    // 送信方法
    type : "GET",
    // 送信先URL
    url : sendURL,

    // レスポンスをJSONとしてパースする
    dataType : "json",
    cache : false
  }).fail(function(jqXHR, textStatus, errorThrown) {
    // エラーメッセージのクリア
    outputErrMsg("");
    // エラーコード単位で処理を分岐する
    // 流量制限エラー発生時
    if(jqXHR.status == 502 || jqXHR.status == 504){
      // エラー出力箇所の横幅の設定
      $('.err_msg').width(400).css({textAlign:'left'});
      // エラーメッセージを画面表示
      reloadlogout("ただいま混み合っております。申し訳ございませんがしばらくしてから再度ご利用ください。");
    } else {
      // 返却値をJSONにパース
      var res = $.parseJSON(jqXHR.responseText);
      if(jqXHR.status == 503){
        // エラー出力箇所の横幅の設定
        $('.err_msg').width(400).css({textAlign:'left'});
        // エラーメッセージを画面表示
        outputErrMsg(res.message);
      };
    };
  }).done(function(json_data) {

    dfd.resolve(json_data);

  }).always(
  );
  return dfd.promise();
};

// AJAX通信 POST(認証要求API)
function doPostAjax(reqData, varURL) {
  var lprtcl = location.protocol;
  var sendURL = bankPath + varURL;
  var dfd = $.Deferred();

  $.ajax({
    type : "POST",
     url : sendURL,
    // リクエストJSONデータ本体
    data : JSON.stringify(reqData),
    // リクエストの Content-Type
    contentType : 'application/json',
    dataType : "json",
    cache : false
  }).fail(function(jqXHR, textStatus, errorThrown) {
    // エラーメッセージのクリア
    outputErrMsg("");
    // エラーコード単位で処理を分岐する
    // 流量制限エラー発生時
    if(jqXHR.status == 502 || jqXHR.status == 504){
      // エラー出力箇所の横幅の設定
      $('.err_msg').width(400).css({textAlign:'left'});
      // エラーメッセージを画面表示
      outputErrMsg("ただいま混み合っております。申し訳ございませんがしばらくしてから再度ご利用ください。");
    } else {
      // 返却値をJSONにパース
      var res = $.parseJSON(jqXHR.responseText);
      if(jqXHR.status == 503){
        // エラー出力箇所の横幅の設定
        $('.err_msg').width(400).css({textAlign:'left'});
        // エラーメッセージを画面表示
        outputErrMsg(res.message);
      }else{
    	// エラー出力箇所の横幅の設定
        $('.err_msg').width(400).css({textAlign:'left'});
        // エラーメッセージを画面表示
        outputErrMsg(res.message);
      };
    };
  }).done(
    function(json_data) {
      // 通信成功処理
      if (json_data.err_code != null || json_data.err_code != undefined) {
        // サーバー側エラー発生時、エラーメッセージを画面表示
        outputErrMsg(json_data.message);
      } else {
        // サーバー側正常終了
        // エラーメッセージクリア
        outputErrMsg("");
        if (json_data.reports == undefined
          || json_data.reports == null
          || json_data.reports[0] == undefined
          || json_data.reports[0] == null) {
            // 帳票一覧情報0件処理
            // 帳票一覧情報を0として格納
            reports = 0;
          } else {
            // reportsデータの配列が存在するか確認
            if (json_data.reports.length > 0) {
              // 帳票一覧情報をJSON文字列に変換して格納
              reports = json_data.reports;
            } else {
              // 帳票一覧情報を0として格納
              reports = 0;
            };
          };
          // 照会可能帳票一覧画面へ切替
          to_accountSelect(true);
      };
    }
  ).always(function(jqXHR, textStatus) {
    nidoBoushi(false);
  });
};

// AJAX通信 POST(ログアウトAPI)
function doLogoutAjax(varURL) {
  var lprtcl = location.protocol;
  var sendURL = bankPath + varURL;

  $.ajax({
    type : "POST",
    // 仮テスト内部API　GITに上げるときは元（下）に戻す
    url : sendURL,
    contentType : 'application/json',
    dataType : "json",
    cache : false
  }).fail(function(jqXHR, textStatus, errorThrown) {
    // エラーメッセージのクリア
    outputErrMsg("");
    // エラーコード単位で処理を分岐する
    // 流量制限エラー発生時
    if(jqXHR.status == 502 || jqXHR.status == 504){
      // エラー出力箇所の横幅の設定
      $('.err_msg').width(400).css({textAlign:'left'});
      // エラーメッセージを画面表示
      outputErrMsg("ただいま混み合っております。申し訳ございませんがしばらくしてから再度ご利用ください。");
    } else {
      // 返却値をJSONにパース
      var res = $.parseJSON(jqXHR.responseText);
      if(jqXHR.status == 503){
        // エラー出力箇所の横幅の設定
        $('.err_msg').width(400).css({textAlign:'left'});
        // エラーメッセージを画面表示
        outputErrMsg(res.message);
      };
    };
  }).done(function(json_data) {
	      show("page_logout");
	});
};