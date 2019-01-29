head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.14.05.46.20;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	8884d7d97833a75;
filename	WEB3ErrorCatalog.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : エラー情報を管理するクラス(WEB3ErrorCatalog.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2004/01/26 中尾　@寿彦(SRA) 新規作成
*/
package webbroker3.common;

import com.fitechlabs.xtrade.kernel.error.ErrorManager;
import com.fitechlabs.xtrade.kernel.message.ErrorInfo;

/**
 * エラー情報を管理するクラス。<BR>
 *<BR>
 * @@author 中尾　@寿彦(SRA)
 * @@version 1.0
 */
public final class WEB3ErrorCatalog
{

    /**
     * エラーマネージャ。
     */
    private static ErrorManager errorMgr = ErrorManager.getInstance(WEB3ErrorCatalog.class);


    /**
     * 致命的なシステムエラー。<br>
     */
     public static final ErrorInfo SYSTEM_ERROR_80002 = errorMgr.defineErrorInfo(
            80002,
            "SYSTEM_ERROR_80002", 
            "予期しないシステムエラーが発生しました。");

    /**
     * DBアクセスエラー。<br>
     */
     public static final ErrorInfo SYSTEM_ERROR_80003 = errorMgr.defineErrorInfo(
            80003,
            "SYSTEM_ERROR_80003", 
            "DBへのアクセスに失敗しました。");

    /**
     * 該当データ重複。<br>
     */
     public static final ErrorInfo SYSTEM_ERROR_80004 = errorMgr.defineErrorInfo(
            80004,
            "SYSTEM_ERROR_80004", 
            "テーブルに重複する該当データが存在します。");

    /**
     * 該当データなし。<br>
     */
     public static final ErrorInfo SYSTEM_ERROR_80005 = errorMgr.defineErrorInfo(
            80005,
            "SYSTEM_ERROR_80005", 
            "テーブルに該当するデータがありません。");

    /**
     * データ不整合エラー。<br>
     */
     public static final ErrorInfo SYSTEM_ERROR_80006 = errorMgr.defineErrorInfo(
            80006,
            "SYSTEM_ERROR_80006", 
            "データ不整合エラー。");

    /**
     * バッチ処理中。<br>
     */
     public static final ErrorInfo SYSTEM_ERROR_80007 = errorMgr.defineErrorInfo(
            80007,
            "SYSTEM_ERROR_80007", 
            "バッチ処理中。");

    /**
     * 緊急停止中。<br>
     */
     public static final ErrorInfo SYSTEM_ERROR_80008 = errorMgr.defineErrorInfo(
            80008,
            "SYSTEM_ERROR_80008", 
            "緊急停止中。");

    /**
     * 取引時間外。<br>
     */
     public static final ErrorInfo SYSTEM_ERROR_80014 = errorMgr.defineErrorInfo(
            80014,
            "SYSTEM_ERROR_80014", 
            "取引時間外。");

    /**
     * 受付時間エラー。<br>
     */
     public static final ErrorInfo SYSTEM_ERROR_80015 = errorMgr.defineErrorInfo(
            80015,
            "SYSTEM_ERROR_80015", 
            "受付時間エラー。");

    /**
     *  システム取引停止エラー。<br>
     */
     public static final ErrorInfo SYSTEM_ERROR_80016 = errorMgr.defineErrorInfo(
            80016,
            "SYSTEM_ERROR_80016", 
            "システム取引停止エラー。");

    /**
     * パラメータ値不正。<br>
     */
     public static final ErrorInfo SYSTEM_ERROR_80017 = errorMgr.defineErrorInfo(
            80017,
            "SYSTEM_ERROR_80017", 
            "パラメータ値不正。");

    /**
     * パラメータタイプ不正。<br>
     */
     public static final ErrorInfo SYSTEM_ERROR_80018 = errorMgr.defineErrorInfo(
            80018,
            "SYSTEM_ERROR_80018", 
            "パラメータタイプ不正。");

    /**
     * 明細行文字列の要素数とカラムヘッダ[]の要素数が同じでない。<br>
     */
     public static final ErrorInfo SYSTEM_ERROR_80022 = errorMgr.defineErrorInfo(
            80022,
            "SYSTEM_ERROR_80022", 
            "明細行文字列の要素数とカラムヘッダ[]の要素数が同じでない。");

    /**
     * 項目の型が変換できない。<br>
     */
     public static final ErrorInfo SYSTEM_ERROR_80023 = errorMgr.defineErrorInfo(
            80023,
            "SYSTEM_ERROR_80023", 
            "項目の型が変換できない。");

    /**
     * 処理対象外。<br>
     */
     public static final ErrorInfo SYSTEM_ERROR_80025 = errorMgr.defineErrorInfo(
            80025,
            "SYSTEM_ERROR_80025", 
            "処理対象外。");

    /**
     * 日本側予めエラーメッセージ採番ナンバー。<br>
     */
     public static final ErrorInfo SYSTEM_ERROR_80026 = errorMgr.defineErrorInfo(
            80026,
            "SYSTEM_ERROR_80026", 
            "日本側予めエラーメッセージ採番ナンバー。");

    /**
     * 日本側予めエラーメッセージ採番ナンバー。<br>
     */
     public static final ErrorInfo SYSTEM_ERROR_80075 = errorMgr.defineErrorInfo(
            80075,
            "SYSTEM_ERROR_80075", 
            "日本側予めエラーメッセージ採番ナンバー。");

    /**
     * 口座ロック失敗。<br>
     */
     public static final ErrorInfo SYSTEM_ERROR_80076 = errorMgr.defineErrorInfo(
            80076,
            "SYSTEM_ERROR_80076", 
            "口座ロックに失敗しました。");

    /**
     * 失効通知処理エラー（システムエラー）。<br>
     */
     public static final ErrorInfo SYSTEM_ERROR_80077 = errorMgr.defineErrorInfo(
            80077,
            "SYSTEM_ERROR_80077", 
            "失効通知処理が失敗しました（システムエラー）。");

    /**
     * 取消通知処理エラー（システムエラー）。<br>
     */
     public static final ErrorInfo SYSTEM_ERROR_80078 = errorMgr.defineErrorInfo(
            80078,
            "SYSTEM_ERROR_80078", 
            "取消通知処理が失敗しました（システムエラー）。");

    /**
     * 該当市場なし。<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_00003 = errorMgr.defineErrorInfo(
            3,
            "BUSINESS_ERROR_00003", 
            "条件に該当する市場がみつかりませんでした。");

    /**
     * 売買規制中。<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_00004 = errorMgr.defineErrorInfo(
            4,
            "BUSINESS_ERROR_00004", 
            "入力された銘柄は現在、売買規制中となっております。");

    /**
     * 特定口座にて取扱不可。<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_00005 = errorMgr.defineErrorInfo(
            5,
            "BUSINESS_ERROR_00005", 
            "入力された銘柄は、特定口座でのお取扱はできません。");

    /**
     * マーケットメイク銘柄を取り扱えない部店。<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_00006 = errorMgr.defineErrorInfo(
            6,
            "BUSINESS_ERROR_00006", 
            "現在お取引されている部店では、マーケットメイク銘柄のお取扱はできません。");

    /**
     * 取引パスワード不正。<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_00009 = errorMgr.defineErrorInfo(
            9,
            "BUSINESS_ERROR_00009", 
            "取引パスワードが不正です。");

    /**
     * 致命的な業務エラー。<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_00010 = errorMgr.defineErrorInfo(
            10,
            "BUSINESS_ERROR_00010", 
            "予期しない業務エラーが発生しました。");

    /**
     * バッチ処理中。<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_00011 = errorMgr.defineErrorInfo(
            11,
            "BUSINESS_ERROR_00011", 
            "システムがバッチ処理中です。");

    /**
     * 緊急停止中。<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_00012 = errorMgr.defineErrorInfo(
            12,
            "BUSINESS_ERROR_00012", 
            "システムが緊急停止中です。");

    /**
     * 受付可能時間外。<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_00013 = errorMgr.defineErrorInfo(
            13,
            "BUSINESS_ERROR_00013", 
            "システムが受付可能時間外です。");

    /**
     * 停止中（当社規制）。<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_00014 = errorMgr.defineErrorInfo(
            14,
            "BUSINESS_ERROR_00014", 
            "入力された銘柄は、当社規制により売買停止中となっております。");

    /**
     * 停止中（取引所規制）。<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_00015 = errorMgr.defineErrorInfo(
            15,
            "BUSINESS_ERROR_00015", 
            "入力された銘柄は、取引所規制により売買停止中となっております。");

    /**
     * 執行条件の値が不正。<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_00019 = errorMgr.defineErrorInfo(
            19,
            "BUSINESS_ERROR_00019", 
            "執行条件の値が不正です。");

    /**
     * 特定口座を開設していない顧客が特定口座指定した場合のエラー。<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_00026 = errorMgr.defineErrorInfo(
            26,
            "BUSINESS_ERROR_00026", 
            "お客様は特定口座を開設されておりません。");

    /**
     * 呼値エラー。<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_00030 = errorMgr.defineErrorInfo(
            30,
            "BUSINESS_ERROR_00030", 
            "指定された呼値が正しくありません。");

    /**
     * 値幅エラー。<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_00031 = errorMgr.defineErrorInfo(
            31,
            "BUSINESS_ERROR_00031", 
            "入力された注文単価が値幅の範囲内にありません。");

    /**
     * 注文状態が適切ではない状態の場合の例外。<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_00032 = errorMgr.defineErrorInfo(
            32,
            "BUSINESS_ERROR_00032", 
            "注文を受付られる状態ではありません。");

    /**
     * 原注文の株数より訂正株数が多い場合の例外。<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_00036 = errorMgr.defineErrorInfo(
            36,
            "BUSINESS_ERROR_00036", 
            "お客様の入力された株数は、原注文の株数より多いため受け付けることが出来ません。");

    /**
     * 原注文が一部出来の状態で、訂正株数が約定数以下の場合。<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_00037 = errorMgr.defineErrorInfo(
            37,
            "BUSINESS_ERROR_00037", 
            "お客様の入力された株数は、原注文が一部出来の状態で、約定数以下のため受け付けることが出来ません。");

    /**
     * 株数と単価（指値）が同時に訂正されている場合の例外。<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_00038 = errorMgr.defineErrorInfo(
            38,
            "BUSINESS_ERROR_00038", 
            "当該市場では、株数と単価（指値）が同時に訂正することは出来ません。");

    /**
     * 原注文から何も訂正が入っていない場合の例外。<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_00039 = errorMgr.defineErrorInfo(
            39,
            "BUSINESS_ERROR_00039", 
            "訂正された項目が１つもありません。");

    /**
     * ソート項目が不正。<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_00055 = errorMgr.defineErrorInfo(
            55,
            "BUSINESS_ERROR_00055", 
            "ソート項目が不正です。");

    /**
     * 約定状態の値が未指定。<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_00062 = errorMgr.defineErrorInfo(
            62,
            "BUSINESS_ERROR_00062", 
            "約定状態の値が未指定です。");

    /**
     * 部店の管理銘柄取扱が取扱不可。<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_00063 = errorMgr.defineErrorInfo(
            63,
            "BUSINESS_ERROR_00063", 
            "部店の管理銘柄取扱が取扱不可です。");

    /**
     * 特定口座開設の税区分エラー。<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_00064 = errorMgr.defineErrorInfo(
            64,
            "BUSINESS_ERROR_00064", 
            "特定口座開設の税区分エラー。");

    /**
     * 受渡日の日付エラー。<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_00065 = errorMgr.defineErrorInfo(
            65,
            "BUSINESS_ERROR_00065", 
            "「特定口座開設チェックエラー。（基準日のYYYYが受渡日より未来になっている）");

    /**
     * 株式注文入力の売買区分。<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_00068 = errorMgr.defineErrorInfo(
            68,
            "BUSINESS_ERROR_00068", 
            "株式注文入力の売買区分エラー。");

    /**
     * 指値エラー。<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_00069 = errorMgr.defineErrorInfo(
            69,
            "BUSINESS_ERROR_00069", 
            "株式注文入力通知キューの指値の値のエラー。");

    /**
     * 原注文の指定が訂正入力の指定と不一致です。<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_00070 = errorMgr.defineErrorInfo(
            70,
            "BUSINESS_ERROR_00070", 
            "原注文の指定が訂正入力の指定と不一致です。");

    /**
     * 発注条件変更エラー。<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_00071 = errorMgr.defineErrorInfo(
            71,
            "BUSINESS_ERROR_00071", 
            "原注文の発注条件が訂正入力発注条件と不一致です。");

    /**
     * 注文は規制対象です。<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_00072 = errorMgr.defineErrorInfo(
            72,
            "BUSINESS_ERROR_00072", 
            "注文は規制対象です。");

    /**
     * 指定方法@の値エラー。<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_00073 = errorMgr.defineErrorInfo(
            73,
            "BUSINESS_ERROR_00073", 
            "指定方法@の値が存在しないコード値です。");

    /**
     * 注文数量が未指定です。<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_00074 = errorMgr.defineErrorInfo(
            74,
            "BUSINESS_ERROR_00074", 
            "注文数量が未指定です。");

    /**
     * 注文数量が数字以外。<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_00075 = errorMgr.defineErrorInfo(
            75,
            "BUSINESS_ERROR_00075", 
            "注文数量が数字以外の値です。");

    /**
     * 注文数量が0以下の値です。<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_00076 = errorMgr.defineErrorInfo(
            76,
            "BUSINESS_ERROR_00076", 
            "注文数量が0以下の値です。");

    /**
     * 注文数量のチェック。<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_00077 = errorMgr.defineErrorInfo(
            77,
            "BUSINESS_ERROR_00077", 
            "注文数量の桁数が不正です。");

    /**
     * 確認時発注日が入力されていません。<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_00078 = errorMgr.defineErrorInfo(
            78,
            "BUSINESS_ERROR_00078", 
            "確認時発注日が入力されていません。");

    /**
     * 銘柄コードが未指定です。<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_00079 = errorMgr.defineErrorInfo(
            79,
            "BUSINESS_ERROR_00079", 
            "銘柄コードが未指定です。");

    /**
     * ＩＤが未指定です。<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_00080 = errorMgr.defineErrorInfo(
            80,
            "BUSINESS_ERROR_00080", 
            "ＩＤが未指定です。");

    /**
     * 照会区分が未指定です。<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_00081 = errorMgr.defineErrorInfo(
            81,
            "BUSINESS_ERROR_00081", 
            "照会区分が未指定です。");

    /**
     * 照会区分の値が存在しないコード値です。<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_00082 = errorMgr.defineErrorInfo(
            82,
            "BUSINESS_ERROR_00082", 
            "照会区分の値が存在しないコード値です。");

    /**
     * ソートキーのキー項目が未指定です。<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_00085 = errorMgr.defineErrorInfo(
            85,
            "BUSINESS_ERROR_00085", 
            "ソートキーのキー項目が未指定です。");

    /**
     * キー項目に項目名以外の値が存在。追加文字列：[値1], [値2], [値3] ...。<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_00086 = errorMgr.defineErrorInfo(
            86,
            "BUSINESS_ERROR_00086", 
            "ソートキーのキー項目の値が存在しないコード値です。");

    /**
     * 昇順／降順が未指定です。<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_00087 = errorMgr.defineErrorInfo(
            87,
            "BUSINESS_ERROR_00087", 
            "昇順／降順が未指定です。");

    /**
     * 昇順／降順が”A：昇順”、”D：降順”の値以外。<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_00088 = errorMgr.defineErrorInfo(
            88,
            "BUSINESS_ERROR_00088", 
            "昇順／降順が”A：昇順”、”D：降順”以外の値です。");

    /**
     * 要求ページ番号が未指定です。<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_00089 = errorMgr.defineErrorInfo(
            89,
            "BUSINESS_ERROR_00089", 
            "要求ページ番号が未指定です。");

    /**
     * 要求ページ番号が数字以外。<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_00090 = errorMgr.defineErrorInfo(
            90,
            "BUSINESS_ERROR_00090", 
            "要求ページ番号が数字以外の値です。");

    /**
     * ページ内表示行数のチェック。<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_00091 = errorMgr.defineErrorInfo(
            91,
            "BUSINESS_ERROR_00091", 
            "ページ内表示行数の入力が不正です。");

    /**
     * ページ内表示行数が数字以外。<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_00092 = errorMgr.defineErrorInfo(
            92,
            "BUSINESS_ERROR_00092", 
            "ページ内表示行数が数字以外の値です。");

    /**
     * 指定方法@が“全部”の場合は、注文数量指定不可。<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_00093 = errorMgr.defineErrorInfo(
            93,
            "BUSINESS_ERROR_00093", 
            "指定方法@が“全部”の場合は、注文数量指定不可です。");

    /**
     * 注文数量の指定がありません。<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_00094 = errorMgr.defineErrorInfo(
            94,
            "BUSINESS_ERROR_00094", 
            "注文数量の指定がありません。");

    /**
     * 注文数量の指定に誤りがあります。（数値以外）。<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_00095 = errorMgr.defineErrorInfo(
            95,
            "BUSINESS_ERROR_00095", 
            "注文数量の指定に誤りがあります。（数値以外）");

    /**
     * 注文数量の指定に誤りがあります。（0以下）。<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_00096 = errorMgr.defineErrorInfo(
            96,
            "BUSINESS_ERROR_00096", 
            "注文数量の指定に誤りがあります。（0以下）");

    /**
     * 注文数量の指定に誤りがあります。（桁数）。<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_00097 = errorMgr.defineErrorInfo(
            97,
            "BUSINESS_ERROR_00097", 
            "注文数量の指定に誤りがあります。（桁数）");

    /**
     * 受渡方法@の値が存在しないコード値です。<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_00099 = errorMgr.defineErrorInfo(
            99,
            "BUSINESS_ERROR_00099", 
            "受渡方法@の値が存在しないコード値です。");

    /**
     * 注文単価・執行条件のチェック（不出来引け成行は「指値」のみ指定可能）エラー。<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_00114 = errorMgr.defineErrorInfo(
            114,
            "BUSINESS_ERROR_00114", 
            "執行条件が“7：不出来引け成行”の場合は、注文単価区分が“1：指値”になれません（不出来引け成行は「指値」のみ指定可能）");

    /**
     * 注文単価区分・単価 の整合性チェックエラー。<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_00116 = errorMgr.defineErrorInfo(
            116,
            "BUSINESS_ERROR_00116", 
            "注文単価区分が“0：成行”の場合は、注文単価指定不可です。");

    /**
     * 期限のチェックエラー。<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_00117 = errorMgr.defineErrorInfo(
            117,
            "BUSINESS_ERROR_00117", 
            "注文期限区分が“1：当日限り”の場合は、注文有効期限指定不可です。");

    /**
     * W指値用注文単価区分・W指値用注文単価 の整合性チェックエラー。<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_00124 = errorMgr.defineErrorInfo(
            124,
            "BUSINESS_ERROR_00124", 
            "発注条件区分が“2：W指値”、かつ、W指値用注文単価区分が“0：成行”の場合は、W指値用注文単価が指定不可です。");

    /**
     * 注文期限・執行条件のチェックエラー。<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_00125 = errorMgr.defineErrorInfo(
            125,
            "BUSINESS_ERROR_00125", 
            "注文期限区分が“2：出来るまで注文”の場合は、執行条件に“1：無条件”を設定して下さい。");

    /**
     * 注文株数が未指定です。<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_00126 = errorMgr.defineErrorInfo(
            126,
            "BUSINESS_ERROR_00126", 
            "注文株数が未指定です。");

    /**
     * 執行条件エラー。<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_00127 = errorMgr.defineErrorInfo(
            127,
            "BUSINESS_ERROR_00127", 
            "執行条件の値が存在しないコード値です。");

    /**
     * 取消時における、【訂正取消通知キューテーブル】の「訂正結果コード」の値の正当性チェックエラー。<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_00128 = errorMgr.defineErrorInfo(
            128,
            "BUSINESS_ERROR_00128", 
            "訂正結果コードの値が存在しないコード値です。（取消完了の場合）");

    /**
     * 訂正時における、【訂正取消通知キューテーブル】の「訂正結果コード」の値の正当性チェックエラー。<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_00129 = errorMgr.defineErrorInfo(
            129,
            "BUSINESS_ERROR_00129", 
            "訂正結果コードの値が存在しないコード値です。（訂正完了の場合）");

    /**
     * 失効通知区分エラー。<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_00130 = errorMgr.defineErrorInfo(
            130,
            "BUSINESS_ERROR_00130", 
            "失効通知区分の値が存在しないコード値です。");

    /**
     * 出来るまで注文の取扱可能エラー。<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_00131 = errorMgr.defineErrorInfo(
            131,
            "BUSINESS_ERROR_00131", 
            "出来るまで注文の取扱可能注文期限区分取得エラー。");

    /**
     * 特定口座開設チェック。<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_00136 = errorMgr.defineErrorInfo(
            136,
            "BUSINESS_ERROR_00136", 
            "特定口座開設チェックエラー。（基準日エラー）");

    /**
     * 発注済みになった逆指値注文の逆指値条件を訂正する場合の例外。<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_00141 = errorMgr.defineErrorInfo(
            141,
            "BUSINESS_ERROR_00141", 
            "発注済みになった逆指値注文の逆指値条件が修正不可です。");

    /**
     * 注文訂正数量チェック。<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_00142 = errorMgr.defineErrorInfo(
            142,
            "BUSINESS_ERROR_00142", 
            "訂正時の注文数量には、約定済数量より多い数量を指定してください。");

    /**
     * 注文訂正数量チェック。<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_00143 = errorMgr.defineErrorInfo(
            143,
            "BUSINESS_ERROR_00143", 
            "訂正前数量より多い数量への訂正はできません。");

    /**
     * 注文数量が上限数量を超える場合のエラー。<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_00144 = errorMgr.defineErrorInfo(
            144,
            "BUSINESS_ERROR_00144", 
            "注文数量が上限数量を超えました。");

    /**
     * 上場期間中のチェック。<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_00145 = errorMgr.defineErrorInfo(
            145,
            "BUSINESS_ERROR_00145", 
            "入力された銘柄は現在、上場期間中となっておりません。");

    /**
     * “訂正”と“取消”が共に受付不可です。<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_00146 = errorMgr.defineErrorInfo(
            146,
            "BUSINESS_ERROR_00146", 
            "“訂正”と“取消”が共に受付不可です。");

    /**
     * 取扱可能チェック。<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_00147 = errorMgr.defineErrorInfo(
            147,
            "BUSINESS_ERROR_00147", 
            "指定された指数は取扱い可能ではありません。");

    /**
     * （指値／刻み値）のチェック。<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_00148 = errorMgr.defineErrorInfo(
            148,
            "BUSINESS_ERROR_00148", 
            "指値は刻み値の整数倍ではありません。");

    /**
     * 口座タイプチェック。<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_00149 = errorMgr.defineErrorInfo(
            149,
            "BUSINESS_ERROR_00149", 
            "口座タイプが不正です。");

    /**
     * 執行条件のチェック。<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_00150 = errorMgr.defineErrorInfo(
            150,
            "BUSINESS_ERROR_00150", 
            "取扱可能な執行条件ではありません。");

    /**
     * 発注条件のチェック。<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_00151 = errorMgr.defineErrorInfo(
            151,
            "BUSINESS_ERROR_00151", 
            "取扱可能な発注条件ではありません。");

    /**
     * 失効日のチェック。<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_00152 = errorMgr.defineErrorInfo(
            152,
            "BUSINESS_ERROR_00152", 
            "失効日が正しく選択されていません。");

    /**
     * 出来るまで注文停止銘柄チェック。<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_00153 = errorMgr.defineErrorInfo(
            153,
            "BUSINESS_ERROR_00153", 
            "ご指定の銘柄は出来るまで注文停止銘柄です。");

    /**
     * 成行規制中。<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_00154 = errorMgr.defineErrorInfo(
            154,
            "BUSINESS_ERROR_00154", 
            "入力された銘柄は現在、成行規制中となっております。");

    /**
     * 注文取消のチェック。<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_00155 = errorMgr.defineErrorInfo(
            155,
            "BUSINESS_ERROR_00155", 
            "該当注文が取消不可です。");

    /**
     * 注文訂正のチェック。<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_00156 = errorMgr.defineErrorInfo(
            156,
            "BUSINESS_ERROR_00156", 
            "該当注文が訂正不可です。");

    /**
     * 発注条件がW指値ではありません。<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_00157 = errorMgr.defineErrorInfo(
            157,
            "BUSINESS_ERROR_00157", 
            "発注条件がW指値ではありません。");

    /**
     * 部店の取扱可能市場エラー。<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_00158 = errorMgr.defineErrorInfo(
            158,
            "BUSINESS_ERROR_00158", 
            "部店の取扱可能市場ではありません。");

    /**
     * 株数のチェックエラー。<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_00160 = errorMgr.defineErrorInfo(
            160,
            "BUSINESS_ERROR_00160", 
            "株数が売買上限株数を超えています。");

    /**
     * 取引可能上限金額エラー。<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_00161 = errorMgr.defineErrorInfo(
            161,
            "BUSINESS_ERROR_00161", 
            "取引可能上限値を超えています。");

    /**
     * 執行条件変更エラー。<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_00165 = errorMgr.defineErrorInfo(
            165,
            "BUSINESS_ERROR_00165", 
            "出来るまで注文の場合、執行条件は指定できません。");

    /**
     * 執行条件変更エラー。<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_00166 = errorMgr.defineErrorInfo(
            166,
            "BUSINESS_ERROR_00166", 
            "訂正入力執行条件が変更できません。");

    /**
     * 売付可能数量エラー。<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_00167 = errorMgr.defineErrorInfo(
            167,
            "BUSINESS_ERROR_00167", 
            "指定株数は売付可能株数を超えています。");

    /**
     * 逆指値基準値の変更エラー。<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_00168 = errorMgr.defineErrorInfo(
            168,
            "BUSINESS_ERROR_00168", 
            "逆指値注文は、市場発注後は、逆指値基準値を訂正できません。");

    /**
     * リクエストデータの型が不正です。<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_00170 = errorMgr.defineErrorInfo(
            170,
            "BUSINESS_ERROR_00170", 
            "リクエストデータの型が不正です。");

    /**
     * 発注審査チェックエラー。<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_00174 = errorMgr.defineErrorInfo(
            174,
            "BUSINESS_ERROR_00174", 
            "発注審査チェックエラー。");

    /**
     * 返済建玉チェック。<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_00178 = errorMgr.defineErrorInfo(
            178,
            "BUSINESS_ERROR_00178", 
            "返済建玉が未指定です。");

    /**
     * 決済順序チェック（指定値以外の場合）。<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_00179 = errorMgr.defineErrorInfo(
            179,
            "BUSINESS_ERROR_00179", 
            "決済順序の値が存在しないコード値です。");

    /**
     * 決済順位のチェック。<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_00180 = errorMgr.defineErrorInfo(
            180,
            "BUSINESS_ERROR_00180", 
            "ランダム指定の場合は、決済順位と明細の数量を入力してください。");

    /**
     * 決済順序チェック（重複の場合）。<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_00182 = errorMgr.defineErrorInfo(
            182,
            "BUSINESS_ERROR_00182", 
            "決済順位番号が重複しています。");

    /**
     * 決済順序のチェック。<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_00183 = errorMgr.defineErrorInfo(
            183,
            "BUSINESS_ERROR_00183", 
            "ランダム指定以外は、決済順位、明細の数量に入力できません。");

    /**
     * 注文単価区分チェック。<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_00184 = errorMgr.defineErrorInfo(
            184,
            "BUSINESS_ERROR_00184", 
            "注文単価区分が未指定です。");

    /**
     * 注文単価区分チェック（指定値以外の場合）。<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_00185 = errorMgr.defineErrorInfo(
            185,
            "BUSINESS_ERROR_00185", 
            "注文単価区分の値が存在しないコード値です。");

    /**
     * 注文単価チェック。<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_00187 = errorMgr.defineErrorInfo(
            187,
            "BUSINESS_ERROR_00187", 
            "注文単価区分が“1：指値”の場合は、注文単価が未指定です。");

    /**
     * 注文単価チェック。<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_00188 = errorMgr.defineErrorInfo(
            188,
            "BUSINESS_ERROR_00188", 
            "注文単価区分が“1：指値”の場合は、注文単価が数字以外の値です。");

    /**
     * 注文単価チェック。<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_00189 = errorMgr.defineErrorInfo(
            189,
            "BUSINESS_ERROR_00189", 
            "注文単価区分が“1：指値”の場合は、注文単価が0以下の値です。");

    /**
     * 注文単価チェック。<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_00190 = errorMgr.defineErrorInfo(
            190,
            "BUSINESS_ERROR_00190", 
            "注文単価区分が“1：指値”の場合は、注文単価のサイズが不正です。");

    /**
     * 新規注文失敗。<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_00191 = errorMgr.defineErrorInfo(
            191,
            "BUSINESS_ERROR_00191", 
            "新規注文失敗。");

    /**
     * 発注条件演算子の変更エラー。<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_00192 = errorMgr.defineErrorInfo(
            192,
            "BUSINESS_ERROR_00192", 
            "原注文の発注条件演算子と訂正入力発注条件演算子が一致していないです。");

    /**
     * 拡張累投銘柄がデータ不整合です。<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_00193 = errorMgr.defineErrorInfo(
            193,
            "BUSINESS_ERROR_00193", 
            "拡張累投銘柄がデータ不整合です。");

    /**
     * 累投MRF取消受付処理失敗。<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_00196 = errorMgr.defineErrorInfo(
            196,
            "BUSINESS_ERROR_00196", 
            "累投MRF取消受付処理失敗。");

    /**
     * 執行条件チェック。<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_00197 = errorMgr.defineErrorInfo(
            197,
            "BUSINESS_ERROR_00197", 
            "執行条件が未指定です。");

    /**
     * 中国F買付、中国F解約、MMF設定、MMF設定解約が受付出来ない状態です。<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_00199 = errorMgr.defineErrorInfo(
            199,
            "BUSINESS_ERROR_00199", 
            "中国F買付、中国F解約、MMF設定、MMF設定解約が受付出来ない状態です。");

    /**
     * 注文情報一覧を取得できません。<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_00200 = errorMgr.defineErrorInfo(
            200,
            "BUSINESS_ERROR_00200", 
            "注文情報一覧を取得できません。");

    /**
     * データ不整合。<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_00201 = errorMgr.defineErrorInfo(
            201,
            "BUSINESS_ERROR_00201", 
            "累投注文単位がデータ不整合です。");

    /**
     * 保有資産該当データなし。<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_00204 = errorMgr.defineErrorInfo(
            204,
            "BUSINESS_ERROR_00204", 
            "保有資産該当データなし。");

    /**
     * 発注日のチェック。<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_00205 = errorMgr.defineErrorInfo(
            205,
            "BUSINESS_ERROR_00205", 
            "発注日が変わりました。お手数ですが、もう一度入力し直してください。");

    /**
     * 確認時単価チェック。<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_00206 = errorMgr.defineErrorInfo(
            206,
            "BUSINESS_ERROR_00206", 
            "確認時単価が未指定です。");

    /**
     * 注文期限区分チェック。<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_00208 = errorMgr.defineErrorInfo(
            208,
            "BUSINESS_ERROR_00208", 
            "注文期限区分が未指定です。");

    /**
     * 注文期限区分チェック（指定値以外の場合）。<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_00209 = errorMgr.defineErrorInfo(
            209,
            "BUSINESS_ERROR_00209", 
            "注文期限区分の値が存在しないコード値です。");

    /**
     * 注文有効期限のチェック。<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_00210 = errorMgr.defineErrorInfo(
            210,
            "BUSINESS_ERROR_00210", 
            "出来るまで注文の場合は、注文有効期限を指定してください。");

    /**
     * 発注条件区分チェック。<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_00211 = errorMgr.defineErrorInfo(
            211,
            "BUSINESS_ERROR_00211", 
            "発注条件区分が未指定です。");

    /**
     * 発注条件区分チェック（指定値以外の場合）。<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_00212 = errorMgr.defineErrorInfo(
            212,
            "BUSINESS_ERROR_00212", 
            "発注条件区分の値が存在しないコード値です。");

    /**
     * プレミアム／原資産価格のチェック。<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_00213 = errorMgr.defineErrorInfo(
            213,
            "BUSINESS_ERROR_00213", 
            "プレミアム／原資産価格が未入力です（逆指値指定）。");

    /**
     * プレミアム／原資産価格のチェック。<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_00214 = errorMgr.defineErrorInfo(
            214,
            "BUSINESS_ERROR_00214", 
            "プレミアム／原資産価格が不正な値です（逆指値指定）。");

    /**
     * 発注条件単価チェック。<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_00215 = errorMgr.defineErrorInfo(
            215,
            "BUSINESS_ERROR_00215", 
            "発注条件単価が未入力です（逆指値指定）。");

    /**
     * 発注条件単価チェック。<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_00216 = errorMgr.defineErrorInfo(
            216,
            "BUSINESS_ERROR_00216", 
            "発注条件単価が不正な値です（逆指値指定）。");

    /**
     * 発注条件演算子のチェック。<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_00219 = errorMgr.defineErrorInfo(
            219,
            "BUSINESS_ERROR_00219", 
            "発注条件演算子が未入力です（逆指値指定）。");

    /**
     * 発注条件演算子のチェック。<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_00220 = errorMgr.defineErrorInfo(
            220,
            "BUSINESS_ERROR_00220", 
            "発注条件演算子が不正な値です（逆指値指定）。");

    /**
     * プレミアム／原資産価格のチェック。<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_00221 = errorMgr.defineErrorInfo(
            221,
            "BUSINESS_ERROR_00221", 
            "プレミアム／原資産価格が未入力です（W指値指定）。");

    /**
     * プレミアム／原資産価格のチェック。<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_00222 = errorMgr.defineErrorInfo(
            222,
            "BUSINESS_ERROR_00222", 
            "プレミアム／原資産価格が不正な値です（W指値指定）。");

    /**
     * 発注条件単価のチェック。<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_00223 = errorMgr.defineErrorInfo(
            223,
            "BUSINESS_ERROR_00223", 
            "発注条件単価が未入力です（W指値指定）。");

    /**
     * 発注条件単価のチェック。<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_00224 = errorMgr.defineErrorInfo(
            224,
            "BUSINESS_ERROR_00224", 
            "発注条件単価が不正な値です（W指値指定）。");

    /**
     * 発注条件演算子のチェック。<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_00227 = errorMgr.defineErrorInfo(
            227,
            "BUSINESS_ERROR_00227", 
            "発注条件演算子が未入力です（W指値指定）。");

    /**
     * 発注条件演算子のチェック。<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_00228 = errorMgr.defineErrorInfo(
            228,
            "BUSINESS_ERROR_00228", 
            "発注条件演算子が不正な値です（W指値指定）。");

    /**
     * 発注条件単価区分のチェック。<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_00229 = errorMgr.defineErrorInfo(
            229,
            "BUSINESS_ERROR_00229", 
            "発注条件単価区分が未入力です（W指値指定）。");

    /**
     * 発注条件単価区分のチェック。<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_00230 = errorMgr.defineErrorInfo(
            230,
            "BUSINESS_ERROR_00230", 
            "発注条件単価区分が不正な値です（W指値指定）。");

    /**
     * ソートキーが未指定です。<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_00231 = errorMgr.defineErrorInfo(
            231,
            "BUSINESS_ERROR_00231", 
            "ソートキーが未指定です。");

    /**
     * ソートキーの要素数が０です。<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_00232 = errorMgr.defineErrorInfo(
            232,
            "BUSINESS_ERROR_00232", 
            "ソートキーの要素数が０です。");

    /**
     * 決済状態区分のチェック（指定値以外の場合）。<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_00233 = errorMgr.defineErrorInfo(
            233,
            "BUSINESS_ERROR_00233", 
            "決済状態区分の値が存在しないコード値です。");

    /**
     * 新規注文市場メッセージ送信失敗場合。<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_00237 = errorMgr.defineErrorInfo(
            237,
            "BUSINESS_ERROR_00237", 
            "新規注文市場メッセージ送信失敗。");

    /**
     * 累投MRF注文受付失敗場合。<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_00238 = errorMgr.defineErrorInfo(
            238,
            "BUSINESS_ERROR_00238", 
            "累投MRF注文受付失敗。");

    /**
     * 累投注文受付失敗。<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_00239 = errorMgr.defineErrorInfo(
            239,
            "BUSINESS_ERROR_00239", 
            "累投注文受付失敗。");

    /**
     * 累投取消注文失敗。<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_00240 = errorMgr.defineErrorInfo(
            240,
            "BUSINESS_ERROR_00240", 
            "累投取消注文失敗。");

    /**
     * 数量のチェック。<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_00245 = errorMgr.defineErrorInfo(
            245,
            "BUSINESS_ERROR_00245", 
            "決済順序がランダム指定以外の場合、数量は必須入力項目です。");

    /**
     * 決済順位のチェック。<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_00246 = errorMgr.defineErrorInfo(
            246,
            "BUSINESS_ERROR_00246", 
            "決済順位が0未満の値です。");

    /**
     * 累積投資の指定方法@が合致していません。<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_00248 = errorMgr.defineErrorInfo(
            248,
            "BUSINESS_ERROR_00248", 
            "累積投資の指定方法@が合致していません。");

    /**
     * 累積投資取引口座チェック。<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_00249 = errorMgr.defineErrorInfo(
            249,
            "BUSINESS_ERROR_00249", 
            "累積投資取引口座チェックエラー。");

    /**
     * 拡張累投取引銘柄を取得できません。<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_00250 = errorMgr.defineErrorInfo(
            250,
            "BUSINESS_ERROR_00250", 
            "拡張累投取引銘柄を取得できません。");

    /**
     * 取引規制チェック。<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_00251 = errorMgr.defineErrorInfo(
            251,
            "BUSINESS_ERROR_00251", 
            "拡張累投取引銘柄が取引規制中です。");

    /**
     * 上限チェック。<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_00252 = errorMgr.defineErrorInfo(
            252,
            "BUSINESS_ERROR_00252", 
            "一回当たりの上限入金金額エラー。");

    /**
     * 下限チェック。<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_00253 = errorMgr.defineErrorInfo(
            253,
            "BUSINESS_ERROR_00253", 
            "一回当たりの下限入金金額エラー。");

    /**
     * 同一注文日の売買が既に注文されていないかチェック。<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_00254 = errorMgr.defineErrorInfo(
            254,
            "BUSINESS_ERROR_00254", 
            "同一注文日の売買が既に注文されています。");

    /**
     * 買付金額が買付可能額を超えています。<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_00255 = errorMgr.defineErrorInfo(
            255,
            "BUSINESS_ERROR_00255", 
            "買付金額が買付可能額を超えています。");

    /**
     * 累積投資口座が開設なし。<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_00256 = errorMgr.defineErrorInfo(
            256,
            "BUSINESS_ERROR_00256", 
            "累積投資口座が開設なし。");

    /**
     * 解約可能残高チェック。<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_00258 = errorMgr.defineErrorInfo(
            258,
            "BUSINESS_ERROR_00258", 
            "注文数量が解約可能残高を超えています。");

    /**
     * 累投注文単位.発注日と現在の発注日が等しいかチェック。<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_00259 = errorMgr.defineErrorInfo(
            259,
            "BUSINESS_ERROR_00259", 
            "累投注文単位の発注日と現在の発注日が一致していないです。");

    /**
     * 累投注文が発注済（新規注文）ではありません。<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_00260 = errorMgr.defineErrorInfo(
            260,
            "BUSINESS_ERROR_00260", 
            "累投注文が発注済（新規注文）ではありません。");

    /**
     * 指定された注文が既に取り消されています。<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_00261 = errorMgr.defineErrorInfo(
            261,
            "BUSINESS_ERROR_00261", 
            "指定された注文が既に取り消されています。");

    /**
     * 解約注文済エラー。<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_00262 = errorMgr.defineErrorInfo(
            262,
            "BUSINESS_ERROR_00262", 
            "解約注文済エラー。");

    /**
     * 建区分チェック。<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_00263 = errorMgr.defineErrorInfo(
            263,
            "BUSINESS_ERROR_00263", 
            "建区分が未指定です。");

    /**
     * 建区分チェック（指定値以外場合）。<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_00264 = errorMgr.defineErrorInfo(
            264,
            "BUSINESS_ERROR_00264", 
            "建区分の値が存在しないコード値です。");

    /**
     * 取引市場チェック。<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_00265 = errorMgr.defineErrorInfo(
            265,
            "BUSINESS_ERROR_00265", 
            "取引市場が未指定です。");

    /**
     * 指数種別チェック。<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_00266 = errorMgr.defineErrorInfo(
            266,
            "BUSINESS_ERROR_00266", 
            "指数種別が未指定です。");

    /**
     * 限月チェック。<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_00267 = errorMgr.defineErrorInfo(
            267,
            "BUSINESS_ERROR_00267", 
            "限月が未指定です。");

    /**
     * 限月チェック（日付形式エラーの場合）。<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_00268 = errorMgr.defineErrorInfo(
            268,
            "BUSINESS_ERROR_00268", 
            "限月がＹＹＹＹＭＭ形式で入力してください。");

    /**
     * オプション商品区分チェック。<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_00269 = errorMgr.defineErrorInfo(
            269,
            "BUSINESS_ERROR_00269", 
            "オプション商品区分が未指定です。");

    /**
     * オプション商品区分チェック（指定値以外の場合）。<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_00270 = errorMgr.defineErrorInfo(
            270,
            "BUSINESS_ERROR_00270", 
            "オプション商品区分の値が存在しないコード値です。");

    /**
     * 行使価格チェック。<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_00271 = errorMgr.defineErrorInfo(
            271,
            "BUSINESS_ERROR_00271", 
            "行使価格が未指定です。");

    /**
     * 行使価格チェック。<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_00272 = errorMgr.defineErrorInfo(
            272,
            "BUSINESS_ERROR_00272", 
            "行使価格が数字以外の値です。");

    /**
     * 行使価格チェック。<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_00273 = errorMgr.defineErrorInfo(
            273,
            "BUSINESS_ERROR_00273", 
            "行使価格が0以下の値です。");

    /**
     * 行使価格チェック。<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_00274 = errorMgr.defineErrorInfo(
            274,
            "BUSINESS_ERROR_00274", 
            "行使価格のサイズが不正です。");

    /**
     * 取引停止顧客エラー。<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_00275 = errorMgr.defineErrorInfo(
            275,
            "BUSINESS_ERROR_00275", 
            "取引停止顧客エラー。");

    /**
     * 管理ロック顧客エラー。<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_00276 = errorMgr.defineErrorInfo(
            276,
            "BUSINESS_ERROR_00276", 
            "管理ロック顧客エラー。");

    /**
     * IDの要素数チェック。<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_00282 = errorMgr.defineErrorInfo(
            282,
            "BUSINESS_ERROR_00282", 
            "IDの要素数が０です。");

    /**
     * 口座開設チェック（”オプション”の場合）。<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_00283 = errorMgr.defineErrorInfo(
            283,
            "BUSINESS_ERROR_00283", 
            "お客様のオプション口座が開設されておりません。");

    /**
     * 口座開設チェック（”先物”の場合）。<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_00284 = errorMgr.defineErrorInfo(
            284,
            "BUSINESS_ERROR_00284", 
            "お客様の先物口座が開設されておりません。");

    /**
     * 返済建玉要素内チェック。<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_00285 = errorMgr.defineErrorInfo(
            285,
            "BUSINESS_ERROR_00285", 
            "すべての返済建玉の数量が未指定です。");

    /**
     * 解約登録処理失敗。<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_00286 = errorMgr.defineErrorInfo(
            286,
            "BUSINESS_ERROR_00286", 
            "解約登録処理失敗。");

    /**
     * 出来るまで注文取扱可能の判定。<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_00289 = errorMgr.defineErrorInfo(
            289,
            "BUSINESS_ERROR_00289", 
            "出来るまで注文ではない場合、当日限り注文です。");

    /**
     * 現物株式特定口座開設の判定。<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_00292 = errorMgr.defineErrorInfo(
            292,
            "BUSINESS_ERROR_00292", 
            "お客様は現物株式特定口座を開設されておりません。");

    /**
     * 注文単価チェック。<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_00293 = errorMgr.defineErrorInfo(
            293,
            "BUSINESS_ERROR_00293", 
            "注文単価チェックエラー（指値が適切ではありません）。");

    /**
     * 指定条件に一致する注文の判定。<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_00295 = errorMgr.defineErrorInfo(
            295,
            "BUSINESS_ERROR_00295", 
            "SONARから通知された識別コードに該当する注文が複数件、不正に存在します。");

    /**
     * 注文数量が返済可能残高数量を超えのエラー。<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_00299 = errorMgr.defineErrorInfo(
            299,
            "BUSINESS_ERROR_00299", 
            "注文数量が返済可能残高数量を超えています。");

    /**
     * 約定数量より注文数量が多いであるがエラー。<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_00300 = errorMgr.defineErrorInfo(
            300,
            "BUSINESS_ERROR_00300", 
            "約定数量が注文数量を超えています。");

    /**
     * 銘柄コードチェックエラー。<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_00301 = errorMgr.defineErrorInfo(
            301,
            "BUSINESS_ERROR_00301", 
            "指定した銘柄コードに合致している銘柄が存在しません。");

    /**
     * 発注条件が取扱対象外です。<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_00302 = errorMgr.defineErrorInfo(
            302,
            "BUSINESS_ERROR_00302", 
            "発注条件が取扱対象外です。");

    /**
     * 返済可能数量チェック。<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_00303 = errorMgr.defineErrorInfo(
            303,
            "BUSINESS_ERROR_00303", 
            "注文数量が返済可能数量を超えています。");

    /**
     * 返済可能株数チェック。<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_00304 = errorMgr.defineErrorInfo(
            304,
            "BUSINESS_ERROR_00304", 
            "返済可能株数オーバー。");

    /**
     * 累投取消受付処理失敗。<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_00305 = errorMgr.defineErrorInfo(
            305,
            "BUSINESS_ERROR_00305", 
            "累投取消受付処理失敗。");

    /**
     * 解約可能対象明細なし。<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_00307 = errorMgr.defineErrorInfo(
            307,
            "BUSINESS_ERROR_00307", 
            "解約可能対象明細なし。");

    /**
     * Ｗ指値注文単価のチェック。<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_00313 = errorMgr.defineErrorInfo(
            313,
            "BUSINESS_ERROR_00313", 
            "W指値注文単価が未入力です（W指値指定）。");

    /**
     * Ｗ指値注文単価のチェック。<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_00314 = errorMgr.defineErrorInfo(
            314,
            "BUSINESS_ERROR_00314", 
            "W指値注文単価が不正な値です（W指値指定）。");

    /**
     * 銘柄コード、かつ、建区分が未指定です。<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_00320 = errorMgr.defineErrorInfo(
            320,
            "BUSINESS_ERROR_00320", 
            "銘柄コード、かつ、建区分が未指定です。");

    /**
     * 決済順序チェック（数字以外の値）。<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_00329 = errorMgr.defineErrorInfo(
            329,
            "BUSINESS_ERROR_00329", 
            "決済順位が数字以外の値です。");

    /**
     * 銘柄指定エラー。<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_00334 = errorMgr.defineErrorInfo(
            334,
            "BUSINESS_ERROR_00334", 
            "銘柄指定エラー。");

    /**
     * 買付開始日エラー。<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_00335 = errorMgr.defineErrorInfo(
            335,
            "BUSINESS_ERROR_00335", 
            "買付開始日エラー。");

    /**
     * 買付終了日エラー。<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_00336 = errorMgr.defineErrorInfo(
            336,
            "BUSINESS_ERROR_00336", 
            "買付終了日エラー。");

    /**
     * 解約／乗換開始日エラー。<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_00337 = errorMgr.defineErrorInfo(
            337,
            "BUSINESS_ERROR_00337", 
            "解約／乗換開始日エラー。");

    /**
     * 解約／乗換終了日エラー。<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_00338 = errorMgr.defineErrorInfo(
            338,
            "BUSINESS_ERROR_00338", 
            "解約／乗換終了日エラー。");

    /**
     * 買取請求開始日エラー。<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_00339 = errorMgr.defineErrorInfo(
            339,
            "BUSINESS_ERROR_00339", 
            "買取請求開始日エラー。");

    /**
     * 買取請求終了日エラー。<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_00340 = errorMgr.defineErrorInfo(
            340,
            "BUSINESS_ERROR_00340", 
            "買取請求終了日エラー。");

    /**
     * 最低口数（新規買付）エラー。<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_00341 = errorMgr.defineErrorInfo(
            341,
            "BUSINESS_ERROR_00341", 
            "最低口数（新規買付）エラー。");

    /**
     * 単位口数（新規買付）エラー。<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_00342 = errorMgr.defineErrorInfo(
            342,
            "BUSINESS_ERROR_00342", 
            "単位口数（新規買付）エラー。");

    /**
     * 最低金額（新規買付）エラー。<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_00343 = errorMgr.defineErrorInfo(
            343,
            "BUSINESS_ERROR_00343", 
            "最低金額（新規買付）エラー。");

    /**
     * 単位金額（新規買付）エラー。<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_00344 = errorMgr.defineErrorInfo(
            344,
            "BUSINESS_ERROR_00344", 
            "単位金額（新規買付）エラー。");

    /**
     * 最低口数（追加買付）エラー。<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_00345 = errorMgr.defineErrorInfo(
            345,
            "BUSINESS_ERROR_00345", 
            "最低口数（追加買付）エラー。");

    /**
     * 単位口数（追加買付）エラー。<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_00346 = errorMgr.defineErrorInfo(
            346,
            "BUSINESS_ERROR_00346", 
            "単位口数（追加買付）エラー。");

    /**
     * 最低金額（追加買付）エラー。<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_00347 = errorMgr.defineErrorInfo(
            347,
            "BUSINESS_ERROR_00347", 
            "最低金額（追加買付）エラー。");

    /**
     * 単位金額（追加買付）エラー。<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_00348 = errorMgr.defineErrorInfo(
            348,
            "BUSINESS_ERROR_00348", 
            "単位金額（追加買付）エラー。");

    /**
     * 最低口数（解約）エラー。<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_00349 = errorMgr.defineErrorInfo(
            349,
            "BUSINESS_ERROR_00349", 
            "最低口数（解約）エラー。");

    /**
     * 単位口数（解約）エラー。<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_00350 = errorMgr.defineErrorInfo(
            350,
            "BUSINESS_ERROR_00350", 
            "単位口数（解約）エラー。");

    /**
     * 最低金額（解約）エラー。<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_00351 = errorMgr.defineErrorInfo(
            351,
            "BUSINESS_ERROR_00351", 
            "最低金額（解約）エラー。");

    /**
     * 単位金額（解約）エラー。<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_00352 = errorMgr.defineErrorInfo(
            352,
            "BUSINESS_ERROR_00352", 
            "単位金額（解約）エラー。");

    /**
     * 最低口数（乗換）エラー。<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_00353 = errorMgr.defineErrorInfo(
            353,
            "BUSINESS_ERROR_00353", 
            "最低口数（乗換）エラー。");

    /**
     * 単位口数（乗換）エラー。<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_00354 = errorMgr.defineErrorInfo(
            354,
            "BUSINESS_ERROR_00354", 
            "単位口数（乗換）エラー。");

    /**
     * 最低金額（乗換）エラー。<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_00355 = errorMgr.defineErrorInfo(
            355,
            "BUSINESS_ERROR_00355", 
            "最低金額（乗換）エラー。");

    /**
     * 単位金額（乗換）エラー。<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_00356 = errorMgr.defineErrorInfo(
            356,
            "BUSINESS_ERROR_00356", 
            "単位金額（乗換）エラー。");

    /**
     * 買付最低数量エラー。<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_00360 = errorMgr.defineErrorInfo(
            360,
            "BUSINESS_ERROR_00360", 
            "買付最低数量エラー。");

    /**
     * 買付単位数量エラー。<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_00361 = errorMgr.defineErrorInfo(
            361,
            "BUSINESS_ERROR_00361", 
            "買付単位数量エラー。");

    /**
     * 取扱不可銘柄エラー。<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_00362 = errorMgr.defineErrorInfo(
            362,
            "BUSINESS_ERROR_00362", 
            "取扱不可銘柄エラー。");

    /**
     * 取引不可銘柄エラー。<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_00363 = errorMgr.defineErrorInfo(
            363,
            "BUSINESS_ERROR_00363", 
            "取引不可銘柄エラー。");

    /**
     * 振込先銀行口座チェック。<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_00366 = errorMgr.defineErrorInfo(
            366,
            "BUSINESS_ERROR_00366", 
            "振込先銀行口座チェックエラー。");

    /**
     * 金額指定解約ありエラー。<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_00367 = errorMgr.defineErrorInfo(
            367,
            "BUSINESS_ERROR_00367", 
            "金額指定解約ありエラー。");

    /**
     * 解約最低数量エラー。<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_00368 = errorMgr.defineErrorInfo(
            368,
            "BUSINESS_ERROR_00368", 
            "解約最低数量エラー。");

    /**
     * 解約単位数量エラー。<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_00369 = errorMgr.defineErrorInfo(
            369,
            "BUSINESS_ERROR_00369", 
            "解約単位数量エラー。");

    /**
     * 乗換最低数量エラー。<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_00370 = errorMgr.defineErrorInfo(
            370,
            "BUSINESS_ERROR_00370", 
            "乗換最低数量エラー。");

    /**
     * 乗換単位数量エラー。<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_00371 = errorMgr.defineErrorInfo(
            371,
            "BUSINESS_ERROR_00371", 
            "乗換単位数量エラー。");

    /**
     * 取引銘柄が登録されていません。<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_00372 = errorMgr.defineErrorInfo(
            372,
            "BUSINESS_ERROR_00372", 
            "取引銘柄が登録されていません。");

    /**
     * 買付可能銘柄ではありません。<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_00373 = errorMgr.defineErrorInfo(
            373,
            "BUSINESS_ERROR_00373", 
            "買付可能銘柄ではありません。");

    /**
     * 解約可能銘柄ではありません。<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_00374 = errorMgr.defineErrorInfo(
            374,
            "BUSINESS_ERROR_00374", 
            "解約可能銘柄ではありません。");

    /**
     * 乗換可能銘柄ではありません。<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_00375 = errorMgr.defineErrorInfo(
            375,
            "BUSINESS_ERROR_00375", 
            "乗換可能銘柄ではありません。");

    /**
     * 当該顧客が現在金額指定の解約を行っているかをチェック。<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_00376 = errorMgr.defineErrorInfo(
            376,
            "BUSINESS_ERROR_00376", 
            "当該顧客が現在金額指定の解約を行っています。");

    /**
     * 銘柄が登録されていません。<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_00377 = errorMgr.defineErrorInfo(
            377,
            "BUSINESS_ERROR_00377", 
            "銘柄が登録されていません。");

    /**
     * 当該取引の発注日と現在の発注日が一致しません。<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_00378 = errorMgr.defineErrorInfo(
            378,
            "BUSINESS_ERROR_00378", 
            "当該取引の発注日と現在の発注日が一致しません。");

    /**
     * 当該取引は約定中か約定済なので、取消可能な状態ではありません。<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_00379 = errorMgr.defineErrorInfo(
            379,
            "BUSINESS_ERROR_00379", 
            "当該取引は約定中か約定済なので、取消可能な状態ではありません。");

    /**
     * 当該取引は発注されていないため、取消可能な状態ではありません。<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_00380 = errorMgr.defineErrorInfo(
            380,
            "BUSINESS_ERROR_00380", 
            "当該取引は発注されていないため、取消可能な状態ではありません。");

    /**
     * 取引状態が有効でないため、取引可能な状態ではありません。<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_00382 = errorMgr.defineErrorInfo(
            382,
            "BUSINESS_ERROR_00382", 
            "取引状態が有効でないため、取引可能な状態ではありません。");

    /**
     * 表示可能な銘柄が登録されていません。<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_00383 = errorMgr.defineErrorInfo(
            383,
            "BUSINESS_ERROR_00383", 
            "表示可能な銘柄が登録されていません。");

    /**
     * 保有残高口数超過エラー。<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_00387 = errorMgr.defineErrorInfo(
            387,
            "BUSINESS_ERROR_00387", 
            "保有残高口数超過エラー。");

    /**
     * 当該銘柄は取引停止中です。<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_00389 = errorMgr.defineErrorInfo(
            389,
            "BUSINESS_ERROR_00389", 
            "当該銘柄は取引停止中です。");

    /**
     * 残高無しチェック。<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_00390 = errorMgr.defineErrorInfo(
            390,
            "BUSINESS_ERROR_00390", 
            "残高がありません。");

    /**
     * 該当銘柄無しチェック。<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_00391 = errorMgr.defineErrorInfo(
            391,
            "BUSINESS_ERROR_00391", 
            "該当する銘柄が存在しません。");

    /**
     * 投信解約乗換銘柄一覧行チェック。<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_00392 = errorMgr.defineErrorInfo(
            392,
            "BUSINESS_ERROR_00392", 
            "投信解約乗換銘柄一覧行を取得できません。");

    /**
     * 税区分が不正です。<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_00393 = errorMgr.defineErrorInfo(
            393,
            "BUSINESS_ERROR_00393", 
            "税区分が不正です。");

    /**
     * 注文取消失敗チェック。<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_00394 = errorMgr.defineErrorInfo(
            394,
            "BUSINESS_ERROR_00394", 
            "注文取消失敗。");

    /**
     * 注文受付処理失敗。<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_00395 = errorMgr.defineErrorInfo(
            395,
            "BUSINESS_ERROR_00395", 
            "注文受付処理失敗。");

    /**
     * 買付銘柄一覧を取得できません。<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_00397 = errorMgr.defineErrorInfo(
            397,
            "BUSINESS_ERROR_00397", 
            "買付銘柄一覧を取得できません。");

    /**
     * 該当するデータが存在しない。<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_00398 = errorMgr.defineErrorInfo(
            398,
            "BUSINESS_ERROR_00398", 
            "該当するデータが存在しません。");

    /**
     * 指定方法@が未指定です。<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_00400 = errorMgr.defineErrorInfo(
            400,
            "BUSINESS_ERROR_00400", 
            "指定方法@が未指定です。");

    /**
     * 請求方法@が未指定です。<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_00401 = errorMgr.defineErrorInfo(
            401,
            "BUSINESS_ERROR_00401", 
            "請求方法@が未指定です。");

    /**
     * 請求方法@チェック。<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_00402 = errorMgr.defineErrorInfo(
            402,
            "BUSINESS_ERROR_00402", 
            "請求方法@が存在しないコード値です。");

    /**
     * 決済方法@が未指定です。<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_00403 = errorMgr.defineErrorInfo(
            403,
            "BUSINESS_ERROR_00403", 
            "決済方法@が未指定です。");

    /**
     * 決済方法@が存在しないコード値です。<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_00404 = errorMgr.defineErrorInfo(
            404,
            "BUSINESS_ERROR_00404", 
            "決済方法@が存在しないコード値です。");

    /**
     * 受渡方法@が未指定です。<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_00405 = errorMgr.defineErrorInfo(
            405,
            "BUSINESS_ERROR_00405", 
            "受渡方法@が未指定です。");

    /**
     * 発注日が未指定です。<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_00406 = errorMgr.defineErrorInfo(
            406,
            "BUSINESS_ERROR_00406", 
            "発注日が未指定です。");

    /**
     * 登録更新情報が未指定です。<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_00411 = errorMgr.defineErrorInfo(
            411,
            "BUSINESS_ERROR_00411", 
            "登録更新情報が未指定です。");

    /**
     * 登録更新情報チェック。<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_00412 = errorMgr.defineErrorInfo(
            412,
            "BUSINESS_ERROR_00412", 
            "登録更新情報の要素数が０です。");

    /**
     * 出来るまで注文取扱不可。<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_00413 = errorMgr.defineErrorInfo(
            413,
            "BUSINESS_ERROR_00413", 
            "出来るまで注文取扱不可。");

    /**
     * 銘柄コード（乗換先）が未指定です。<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_00414 = errorMgr.defineErrorInfo(
            414,
            "BUSINESS_ERROR_00414", 
            "銘柄コード（乗換先）が未指定です。");

    /**
     * 買付口座区分が未指定です。<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_00415 = errorMgr.defineErrorInfo(
            415,
            "BUSINESS_ERROR_00415", 
            "買付口座区分が未指定です。");

    /**
     * 買付口座区分が指定値以外の場合。<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_00416 = errorMgr.defineErrorInfo(
            416,
            "BUSINESS_ERROR_00416", 
            "買付口座区分が存在しないコード値です。");

    /**
     * （買付）数量が未指定です。<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_00418 = errorMgr.defineErrorInfo(
            418,
            "BUSINESS_ERROR_00418", 
            "（買付）数量が未指定です。");

    /**
     * (買付)数量チェック（数量が数値以外の場合）。<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_00419 = errorMgr.defineErrorInfo(
            419,
            "BUSINESS_ERROR_00419", 
            "（買付）数量が数値以外の値です。");

    /**
     * (買付)数量チェック。<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_00420 = errorMgr.defineErrorInfo(
            420,
            "BUSINESS_ERROR_00420", 
            "（買付）数量が0以下の値です。");

    /**
     * (買付)数量チェック。<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_00421 = errorMgr.defineErrorInfo(
            421,
            "BUSINESS_ERROR_00421", 
            "（買付）数量のサイズが不正です。");

    /**
     * 検索年月が未指定です。<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_00429 = errorMgr.defineErrorInfo(
            429,
            "BUSINESS_ERROR_00429", 
            "検索年月が未指定です。");

    /**
     * 検索年月チェック。<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_00430 = errorMgr.defineErrorInfo(
            430,
            "BUSINESS_ERROR_00430", 
            "検索年月が数値以外の値です。");

    /**
     * 検索年月チェック。<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_00431 = errorMgr.defineErrorInfo(
            431,
            "BUSINESS_ERROR_00431", 
            "検索年月のサイズが不正です。");

    /**
     * 検索年月チェック。<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_00432 = errorMgr.defineErrorInfo(
            432,
            "BUSINESS_ERROR_00432", 
            "検索年月の月が01から12までの数値以外です。");

    /**
     * 営業日上限のチェック。<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_00433 = errorMgr.defineErrorInfo(
            433,
            "BUSINESS_ERROR_00433", 
            "営業日上限が数値以外の値です。");

    /**
     * IPO登録区分のチェック。<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_00434 = errorMgr.defineErrorInfo(
            434,
            "BUSINESS_ERROR_00434", 
            "IPO登録区分が未指定です。");

    /**
     * IPO登録区分詳細のチェック。<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_00435 = errorMgr.defineErrorInfo(
            435,
            "BUSINESS_ERROR_00435", 
            "IPO登録区分詳細が未指定です。");

    /**
     * IPO登録区分のチェック。<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_00436 = errorMgr.defineErrorInfo(
            436,
            "BUSINESS_ERROR_00436", 
            "IPO登録区分が存在しないコード値です。");

    /**
     * IPO登録区分詳細のチェック。<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_00437 = errorMgr.defineErrorInfo(
            437,
            "BUSINESS_ERROR_00437", 
            "IPO登録区分詳細が存在しないコード値です。");

    /**
     * 銘柄コードのチェック。<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_00439 = errorMgr.defineErrorInfo(
            439,
            "BUSINESS_ERROR_00439", 
            "銘柄コードのサイズが不正です。");

    /**
     * 銘柄名のチェック。<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_00440 = errorMgr.defineErrorInfo(
            440,
            "BUSINESS_ERROR_00440", 
            "新規上場銘柄、銘柄名は必須入力項目です。");

    /**
     * 銘柄名のチェック。<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_00441 = errorMgr.defineErrorInfo(
            441,
            "BUSINESS_ERROR_00441", 
            "銘柄名のサイズが不正です。");

    /**
     * 公開日のチェック。<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_00442 = errorMgr.defineErrorInfo(
            442,
            "BUSINESS_ERROR_00442", 
            "スケジュール決定済の場合、公開日は必須入力項目です。");

    /**
     * 市場コードのチェック。<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_00443 = errorMgr.defineErrorInfo(
            443,
            "BUSINESS_ERROR_00443", 
            "市場コードが未指定です。");

    /**
     * 仮条件区分のチェック。<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_00444 = errorMgr.defineErrorInfo(
            444,
            "BUSINESS_ERROR_00444", 
            "仮条件区分が未指定です。");

    /**
     * 仮条件区分のチェック。<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_00445 = errorMgr.defineErrorInfo(
            445,
            "BUSINESS_ERROR_00445", 
            "仮条件区分が存在しないコード値です。");

    /**
     * 仮条件下限値のチェック。<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_00446 = errorMgr.defineErrorInfo(
            446,
            "BUSINESS_ERROR_00446", 
            "仮条件下限値が数値ではありません。");

    /**
     * 仮条件下限値のチェック。<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_00447 = errorMgr.defineErrorInfo(
            447,
            "BUSINESS_ERROR_00447", 
            "仮条件区分が“実価格（円）”の場合は、仮条件下限値のサイズが不正です。");

    /**
     * 仮条件下限値のチェック。<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_00448 = errorMgr.defineErrorInfo(
            448,
            "BUSINESS_ERROR_00448", 
            "仮条件区分が“ディスカウント率（％）”の場合は、仮条件下限値のサイズが整数部2桁／少数部2桁以内のレンジではありません。");

    /**
     * 仮条件上限値のチェック。<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_00449 = errorMgr.defineErrorInfo(
            449,
            "BUSINESS_ERROR_00449", 
            "仮条件上限値が数値ではありません。");

    /**
     * 仮条件上限値のチェック。<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_00450 = errorMgr.defineErrorInfo(
            450,
            "BUSINESS_ERROR_00450", 
            "仮条件区分が“実価格（円）”の場合は、仮条件上限値のサイズが不正です。");

    /**
     * 仮条件上限値のチェック。<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_00451 = errorMgr.defineErrorInfo(
            451,
            "BUSINESS_ERROR_00451", 
            "仮条件区分が“ディスカウント率（％）”の場合は、仮条件上限値のサイズが整数部2桁／少数部2桁以内のレンジではありません。");

    /**
     * 仮条件提示日のチェック。<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_00452 = errorMgr.defineErrorInfo(
            452,
            "BUSINESS_ERROR_00452", 
            "仮条件提示日が未指定です。");

    /**
     * 公募数量のチェック。<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_00453 = errorMgr.defineErrorInfo(
            453,
            "BUSINESS_ERROR_00453", 
            "公募数量が数値以外の値です。");

    /**
     * 公募数量のチェック。<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_00454 = errorMgr.defineErrorInfo(
            454,
            "BUSINESS_ERROR_00454", 
            "公募数量のサイズが不正です。");

    /**
     * 売出数量のチェック。<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_00455 = errorMgr.defineErrorInfo(
            455,
            "BUSINESS_ERROR_00455", 
            "売出数量が数値以外の値です。");

    /**
     * 売出数量のチェック。<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_00456 = errorMgr.defineErrorInfo(
            456,
            "BUSINESS_ERROR_00456", 
            "売出数量のサイズが不正です。");

    /**
     * 当社取扱数量のチェック。<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_00457 = errorMgr.defineErrorInfo(
            457,
            "BUSINESS_ERROR_00457", 
            "当社取扱数量が数値以外の値です。");

    /**
     * 当社取扱数量のチェック。<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_00458 = errorMgr.defineErrorInfo(
            458,
            "BUSINESS_ERROR_00458", 
            "当社取扱数量のサイズが不正です。");

    /**
     * 購入申込単位のチェック。<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_00459 = errorMgr.defineErrorInfo(
            459,
            "BUSINESS_ERROR_00459", 
            "購入申込単位が数値以外の値です。");

    /**
     * 購入申込単位のチェック。<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_00460 = errorMgr.defineErrorInfo(
            460,
            "BUSINESS_ERROR_00460", 
            "購入申込単位のサイズが不正です。");

    /**
     * 当社取扱数量のチェック。<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_00461 = errorMgr.defineErrorInfo(
            461,
            "BUSINESS_ERROR_00461", 
            "当社取扱数量は、購入申込単位より大きい値を入力してください。");

    /**
     * 当社取扱数量のチェック。<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_00462 = errorMgr.defineErrorInfo(
            462,
            "BUSINESS_ERROR_00462", 
            "当社取扱数量は、購入申込単位の整数倍で入力してください。");

    /**
     * 売出数量のチェック。<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_00463 = errorMgr.defineErrorInfo(
            463,
            "BUSINESS_ERROR_00463", 
            "売出数量は、購入申込単位の整数倍で指定してください。");

    /**
     * 公募数量のチェック。<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_00464 = errorMgr.defineErrorInfo(
            464,
            "BUSINESS_ERROR_00464", 
            "公募数量は、購入申込単位の整数倍で入力してください。");

    /**
     * 刻みのチェック。<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_00465 = errorMgr.defineErrorInfo(
            465,
            "BUSINESS_ERROR_00465", 
            "刻みが数値以外の値です。");

    /**
     * 刻みのチェック。<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_00466 = errorMgr.defineErrorInfo(
            466,
            "BUSINESS_ERROR_00466", 
            "仮条件区分が“実価格（円）”の場合は、刻みのサイズが不正です。");

    /**
     * 刻みのチェック。<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_00467 = errorMgr.defineErrorInfo(
            467,
            "BUSINESS_ERROR_00467", 
            "仮条件区分が“実価格（円）”の場合は、刻みが整数値ではありません。");

    /**
     * 刻みのチェック。<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_00468 = errorMgr.defineErrorInfo(
            468,
            "BUSINESS_ERROR_00468", 
            "仮条件区分が“ディスカウント率（％）”の場合は、刻みのサイズが整数部2桁／少数部2桁以内の範囲外です。");

    /**
     * 表示用単位区分のチェック。<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_00469 = errorMgr.defineErrorInfo(
            469,
            "BUSINESS_ERROR_00469", 
            "表示用単位区分が未指定です。");

    /**
     * 表示用単位区分のチェック。<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_00470 = errorMgr.defineErrorInfo(
            470,
            "BUSINESS_ERROR_00470", 
            "表示用単位区分が存在しないコード値です。");

    /**
     * 主幹事証券会社名のチェック。<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_00471 = errorMgr.defineErrorInfo(
            471,
            "BUSINESS_ERROR_00471", 
            "主幹事証券会社名が未指定です。");

    /**
     * 主幹事証券会社名のチェック。<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_00472 = errorMgr.defineErrorInfo(
            472,
            "BUSINESS_ERROR_00472", 
            "主幹事証券会社名のサイズが不正です。");

    /**
     * ブックビルディング開始日時のチェック。<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_00473 = errorMgr.defineErrorInfo(
            473,
            "BUSINESS_ERROR_00473", 
            "ブックビルディング開始日時が未指定です。");

    /**
     * ブックビルディング終了日時のチェック。<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_00474 = errorMgr.defineErrorInfo(
            474,
            "BUSINESS_ERROR_00474", 
            "ブックビルディング終了日時が未指定です。");

    /**
     * 公開価格決定日のチェック。<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_00475 = errorMgr.defineErrorInfo(
            475,
            "BUSINESS_ERROR_00475", 
            "未定決定区分が“決定”の場合は、公開価格決定日の開始日時が未指定です。");

    /**
     * 公開価格のチェック。<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_00476 = errorMgr.defineErrorInfo(
            476,
            "BUSINESS_ERROR_00476", 
            "公開価格が数値以外の値です。");

    /**
     * 公開価格のチェック。<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_00477 = errorMgr.defineErrorInfo(
            477,
            "BUSINESS_ERROR_00477", 
            "公開価格のサイズが不正です。");

    /**
     * 公開価格ディスカウント率のチェック。<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_00478 = errorMgr.defineErrorInfo(
            478,
            "BUSINESS_ERROR_00478", 
            "公開価格ディスカウント率のサイズが整数部2桁／少数部2桁以内の範囲外です。");

    /**
     * 抽選日のチェック。<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_00479 = errorMgr.defineErrorInfo(
            479,
            "BUSINESS_ERROR_00479", 
            "スケジュール決定済の場合、抽選日は必須入力項目です。");

    /**
     * 購入申込期間（目論見書）のチェック。<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_00480 = errorMgr.defineErrorInfo(
            480,
            "BUSINESS_ERROR_00480", 
            "スケジュール決定済の場合、購入申込期間（目論見書）は必須入力項目です。");

    /**
     * 購入申込期間（当社指定）のチェック。<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_00482 = errorMgr.defineErrorInfo(
            482,
            "BUSINESS_ERROR_00482", 
            "スケジュール決定済の場合、購入申込期間（当社指定）は必須入力項目です。");

    /**
     * 発行会社ロゴファ@イルURLのチェック。<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_00484 = errorMgr.defineErrorInfo(
            484,
            "BUSINESS_ERROR_00484", 
            "発行会社ロゴファ@イルURLのサイズが不正です。");

    /**
     * 発行会社ウェブサイトURLのチェック。<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_00485 = errorMgr.defineErrorInfo(
            485,
            "BUSINESS_ERROR_00485", 
            "発行会社ウェブサイトURLのサイズが不正です。");

    /**
     * 発行会社概要のチェック。<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_00486 = errorMgr.defineErrorInfo(
            486,
            "BUSINESS_ERROR_00486", 
            "発行会社概要のサイズが不正です。");

    /**
     * 備考のチェック。<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_00487 = errorMgr.defineErrorInfo(
            487,
            "BUSINESS_ERROR_00487", 
            "備考のサイズが不正です。");

    /**
     * 仮条件下限値のチェック。<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_00488 = errorMgr.defineErrorInfo(
            488,
            "BUSINESS_ERROR_00488", 
            "仮条件区分が“実価格（円）”の場合は、仮条件下限値が整数値ではありません。");

    /**
     * 仮条件上限値のチェック。<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_00489 = errorMgr.defineErrorInfo(
            489,
            "BUSINESS_ERROR_00489", 
            "仮条件区分が“実価格（円）”の場合は、仮条件上限値が整数値ではありません。");

    /**
     * 営業日下限のチェック。<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_00490 = errorMgr.defineErrorInfo(
            490,
            "BUSINESS_ERROR_00490", 
            "営業日下限が数値以外の値です。");

    /**
     * 申告価格のチェック。<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_00491 = errorMgr.defineErrorInfo(
            491,
            "BUSINESS_ERROR_00491", 
            "申告価格が数値以外の値です。");

    /**
     * 申告価格区分のチェック。<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_00492 = errorMgr.defineErrorInfo(
            492,
            "BUSINESS_ERROR_00492", 
            "申告価格区分が“成行”の場合は、申告価格指定不可。");

    /**
     * 申告価格区分のチェック。<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_00493 = errorMgr.defineErrorInfo(
            493,
            "BUSINESS_ERROR_00493", 
            "指値でご申告される場合は、申告価格をご指定してください。");

    /**
     * 申告数量のチェック。<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_00494 = errorMgr.defineErrorInfo(
            494,
            "BUSINESS_ERROR_00494", 
            "申告数量が数値以外の値です。");

    /**
     * IPO登録区分が“既上場”の場合は、IPO銘柄を取得できません。<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_00495 = errorMgr.defineErrorInfo(
            495,
            "BUSINESS_ERROR_00495", 
            "IPO登録区分が“既上場”の場合は、IPO銘柄を取得できません。");

    /**
     * IPOスケジュール項目のチェック。<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_00496 = errorMgr.defineErrorInfo(
            496,
            "BUSINESS_ERROR_00496", 
            "IPOスケジュール項目の入力が不正です。（銘柄登録日，ブックビルディング開始日時）");

    /**
     * IPOスケジュール項目のチェック。<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_00497 = errorMgr.defineErrorInfo(
            497,
            "BUSINESS_ERROR_00497", 
            "IPOスケジュール項目の入力が不正です。（仮条件提示日，ブックビルディング開始日時）");

    /**
     * IPOスケジュール項目のチェック。<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_00498 = errorMgr.defineErrorInfo(
            498,
            "BUSINESS_ERROR_00498", 
            "IPOスケジュール項目の入力が不正です。（ブックビルディング開始日時，ブックビルディング終了日時）");

    /**
     * IPOスケジュール項目のチェック。<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_00499 = errorMgr.defineErrorInfo(
            499,
            "BUSINESS_ERROR_00499", 
            "IPOスケジュール項目の入力が不正です。（ブックビルディング終了日時，公開価格決定日）");

    /**
     * IPOスケジュール項目のチェック。<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_00500 = errorMgr.defineErrorInfo(
            500,
            "BUSINESS_ERROR_00500", 
            "IPOスケジュール項目の入力が不正です。（公開価格決定日，抽選日）");

    /**
     * IPOスケジュール項目のチェック。<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_00501 = errorMgr.defineErrorInfo(
            501,
            "BUSINESS_ERROR_00501", 
            "IPOスケジュール項目の入力が不正です。（抽選日，購入申込開始日（目論見書記載））");

    /**
     * IPOスケジュール項目のチェック。<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_00502 = errorMgr.defineErrorInfo(
            502,
            "BUSINESS_ERROR_00502", 
            "IPOスケジュール項目の入力が不正です。（購入申込開始日時（当社設定））");

    /**
     * IPOスケジュール項目のチェック。<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_00503 = errorMgr.defineErrorInfo(
            503,
            "BUSINESS_ERROR_00503", 
            "IPOスケジュール項目の入力が不正です。（購入申込終了日時（当社設定））");

    /**
     * IPOスケジュール項目のチェック。<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_00504 = errorMgr.defineErrorInfo(
            504,
            "BUSINESS_ERROR_00504", 
            "IPOスケジュール項目の入力が不正です。（購入申込終了日時（目論見書記載））");

    /**
     * IPOスケジュール項目のチェック。<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_00505 = errorMgr.defineErrorInfo(
            505,
            "BUSINESS_ERROR_00505", 
            "IPOスケジュール項目の入力が不正です。（公開日）");

    /**
     * 期間重複チェック。<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_00506 = errorMgr.defineErrorInfo(
            506,
            "BUSINESS_ERROR_00506", 
            "IPO期間（ブックビルディング開始日時―公開日）が重複している銘柄が既に登録されています。");

    /**
     * 申告済みの顧客ではありません。<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_00510 = errorMgr.defineErrorInfo(
            510,
            "BUSINESS_ERROR_00510", 
            "申告済みの顧客ではありません。");

    /**
     * 顧客に該当するIPO申告のチェック。<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_00511 = errorMgr.defineErrorInfo(
            511,
            "BUSINESS_ERROR_00511", 
            "IPO申告が有効でない場合、IPO申告（新規抽選）不可。");

    /**
     * 顧客に該当するIPO申告のチェック。<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_00512 = errorMgr.defineErrorInfo(
            512,
            "BUSINESS_ERROR_00512", 
            "IPO申告が抽選結果更新済みの場合、IPO申告（新規抽選）不可。");

    /**
     * 顧客に該当するIPO申告のチェック。<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_00513 = errorMgr.defineErrorInfo(
            513,
            "BUSINESS_ERROR_00513", 
            "当選数量が申告数量を超えている場合、IPO申告（新規抽選）不可。");

    /**
     * 顧客に該当するIPO申告のチェック。<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_00514 = errorMgr.defineErrorInfo(
            514,
            "BUSINESS_ERROR_00514", 
            "補欠当選でIPO申告が購入申込済みでない場合、IPO申告（繰上抽選）不可。");

    /**
     * 顧客に該当するIPO申告のチェック。<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_00515 = errorMgr.defineErrorInfo(
            515,
            "BUSINESS_ERROR_00515", 
            "既に繰上抽選結果が更新されている場合、IPO申告（繰上抽選）不可。");

    /**
     * 優先順位のチェック。<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_00516 = errorMgr.defineErrorInfo(
            516,
            "BUSINESS_ERROR_00516", 
            "優先順位チェックエラー。");

    /**
     * 重複顧客チェック。<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_00517 = errorMgr.defineErrorInfo(
            517,
            "BUSINESS_ERROR_00517", 
            "重複顧客チェックエラー（同じ値の行が存在する）。");

    /**
     * 当選数量が申告数量を超えていないかチェック。<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_00518 = errorMgr.defineErrorInfo(
            518,
            "BUSINESS_ERROR_00518", 
            "申告数量は当選数量より小さいです。");

    /**
     * 抽選結果の値が正しいかチェック。<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_00519 = errorMgr.defineErrorInfo(
            519,
            "BUSINESS_ERROR_00519", 
            "新規抽選の抽選結果の値が不正なコード値です。");

    /**
     * 抽選結果の値チェック。<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_00520 = errorMgr.defineErrorInfo(
            520,
            "BUSINESS_ERROR_00520", 
            "繰上抽選の抽選結果の値が不正なコード値です。");

    /**
     * 当選数量／抽選結果の関連チェック。<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_00521 = errorMgr.defineErrorInfo(
            521,
            "BUSINESS_ERROR_00521", 
            "繰上抽選の抽選結果の値が不正です。（補欠落選者に当選数量が発生している）");

    /**
     * キーヘッダ文字列の妥当性チェック。<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_00522 = errorMgr.defineErrorInfo(
            522,
            "BUSINESS_ERROR_00522", 
            "キーヘッダ文字列の妥当性チェックエラー。（ヘッダのフォーマットが不正です）");

    /**
     * キーヘッダ文字列の妥当性チェック。<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_00523 = errorMgr.defineErrorInfo(
            523,
            "BUSINESS_ERROR_00523", 
            "キーヘッダ文字列の妥当性チェックエラー。（銘柄コードチェックエラー）");

    /**
     * キーヘッダ文字列の妥当性チェック。<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_00524 = errorMgr.defineErrorInfo(
            524,
            "BUSINESS_ERROR_00524", 
            "キーヘッダ文字列の妥当性チェックエラー。（新規／抽選区分チェックエラー）");

    /**
     * アップロード期間のチェック。<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_00525 = errorMgr.defineErrorInfo(
            525,
            "BUSINESS_ERROR_00525", 
            "アップロード期間チェックエラー。（新規抽選：購入申込期間が終了している）");

    /**
     * アップロード期間のチェック。<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_00526 = errorMgr.defineErrorInfo(
            526,
            "BUSINESS_ERROR_00526", 
            "アップロード期間チェックエラー。（繰上抽選：購入申込期間が開始していない）");

    /**
     * 抽選結果アップロード可能状態のチェック。<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_00527 = errorMgr.defineErrorInfo(
            527,
            "BUSINESS_ERROR_00527", 
            "抽選結果アップロード可能状態のチェックエラー。（ブックビルディング期間未終了）");

    /**
     * 抽選結果アップロード可能状態のチェック。<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_00528 = errorMgr.defineErrorInfo(
            528,
            "BUSINESS_ERROR_00528", 
            "IPOのスケジュールが未定です。");

    /**
     * 抽選結果アップロード可能状態のチェック。<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_00529 = errorMgr.defineErrorInfo(
            529,
            "BUSINESS_ERROR_00529", 
            "抽選結果アップロード可能状態のチェックエラー。（公開価格が決定していない）");

    /**
     * アップロード期間のチェック。<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_00530 = errorMgr.defineErrorInfo(
            530,
            "BUSINESS_ERROR_00530", 
            "アップロード期間のチェックエラー。（新規抽選：新規抽選が終了している）");

    /**
     * 数量のチェック。<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_00531 = errorMgr.defineErrorInfo(
            531,
            "BUSINESS_ERROR_00531", 
            "引数の数量が0以下の値です。");

    /**
     * 単位数量チェック。<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_00532 = errorMgr.defineErrorInfo(
            532,
            "BUSINESS_ERROR_00532", 
            "引数の数量が購入申込単位の整数倍ではありません。");

    /**
     * 仮条件区分によるレンジチェック。<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_00533 = errorMgr.defineErrorInfo(
            533,
            "BUSINESS_ERROR_00533", 
            "仮条件区分が“実価格（円）”の場合は、引数の単価のサイズが不正です。");

    /**
     * 仮条件区分によるレンジチェック。<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_00534 = errorMgr.defineErrorInfo(
            534,
            "BUSINESS_ERROR_00534", 
            "仮条件区分が“実価格（円）”の場合は、引数の単価が整数値ではありません。");

    /**
     * 仮条件区分によるレンジチェック。<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_00535 = errorMgr.defineErrorInfo(
            535,
            "BUSINESS_ERROR_00535", 
            "仮条件区分が“ディスカウント率（％）”の場合は、引数の単価のサイズが整数部2桁／少数部2桁以内のレンジではありません。");

    /**
     * 仮条件上限／下限値のチェック。<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_00536 = errorMgr.defineErrorInfo(
            536,
            "BUSINESS_ERROR_00536", 
            "引数の単価は仮条件下限値から仮条件上限値の範囲ではありません。");

    /**
     * 刻みのチェック。<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_00537 = errorMgr.defineErrorInfo(
            537,
            "BUSINESS_ERROR_00537", 
            "単価と仮条件下限値との差額は刻みの整数倍ではありません。");

    /**
     * 二重申告判定。<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_00538 = errorMgr.defineErrorInfo(
            538,
            "BUSINESS_ERROR_00538", 
            "該当顧客は既にブックビルディング申告済です。");

    /**
     * IPO銘柄がブックビルディング取扱中であることのチェック。<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_00539 = errorMgr.defineErrorInfo(
            539,
            "BUSINESS_ERROR_00539", 
            "現在、当該IPO銘柄はブックビルディングを受付けておりません。");

    /**
     * 目論見書電子交付済チェック。<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_00540 = errorMgr.defineErrorInfo(
            540,
            "BUSINESS_ERROR_00540", 
            "目論見書電子交付未済です。");

    /**
     * 二重購入申込／辞退のチェック。<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_00541 = errorMgr.defineErrorInfo(
            541,
            "BUSINESS_ERROR_00541", 
            "該当IPO申告は二重購入申込／辞退です。");

    /**
     * 購入申込期間チェック。<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_00542 = errorMgr.defineErrorInfo(
            542,
            "BUSINESS_ERROR_00542", 
            "IPO銘柄が購入申込期間外です。");

    /**
     * 辞退可能期間チェック。<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_00543 = errorMgr.defineErrorInfo(
            543,
            "BUSINESS_ERROR_00543", 
            "IPO銘柄が辞退可能期間外です。");

    /**
     * 当選／補欠当選のチェック。<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_00544 = errorMgr.defineErrorInfo(
            544,
            "BUSINESS_ERROR_00544", 
            "該当IPO申告は当選／補欠当選でない場合、あるいは、該当顧客は繰上抽選で落選している場合、購入申込／辞退可能が不可です。");

    /**
     * 購入申込数量のチェック。<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_00546 = errorMgr.defineErrorInfo(
            546,
            "BUSINESS_ERROR_00546", 
            "購入申込数量は当選数量より大きいです。");

    /**
     * 訂正項目チェック。<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_00547 = errorMgr.defineErrorInfo(
            547,
            "BUSINESS_ERROR_00547", 
            "訂正された項目が一つもありません。（IPO銘柄訂正）");

    /**
     * 未定への変更チェック。<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_00548 = errorMgr.defineErrorInfo(
            548,
            "BUSINESS_ERROR_00548", 
            "決定済のスケジュールを未定に訂正不可です。");

    /**
     * ブックビルディング終了日時の訂正チェック。<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_00549 = errorMgr.defineErrorInfo(
            549,
            "BUSINESS_ERROR_00549", 
            "ブックビルディング終了日時が現在日時以降に訂正されていた場合、訂正後のIPO銘柄のスケジュールが未定です。");

    /**
     * 訂正可能期間が、ブックビルディング開始前までの項目のチェック。<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_00550 = errorMgr.defineErrorInfo(
            550,
            "BUSINESS_ERROR_00550", 
            "ブックビルディング期間が始まっている銘柄は、IPO登録区分を訂正できません。");

    /**
     * 訂正可能期間が、ブックビルディング開始前までの項目のチェック。<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_00551 = errorMgr.defineErrorInfo(
            551,
            "BUSINESS_ERROR_00551", 
            "ブックビルディング期間が始まっている銘柄は、IPO登録区分詳細を訂正できません。");

    /**
     * 訂正可能期間が、ブックビルディング開始前までの項目のチェック。<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_00552 = errorMgr.defineErrorInfo(
            552,
            "BUSINESS_ERROR_00552", 
            "ブックビルディング期間が始まっている銘柄は、銘柄名を訂正できません。");

    /**
     * 訂正可能期間が、ブックビルディング開始前までの項目のチェック。<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_00553 = errorMgr.defineErrorInfo(
            553,
            "BUSINESS_ERROR_00553", 
            "ブックビルディング期間が始まっている銘柄は、公開市場を訂正できません。");

    /**
     * 訂正可能期間が、ブックビルディング開始前までの項目のチェック。<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_00554 = errorMgr.defineErrorInfo(
            554,
            "BUSINESS_ERROR_00554", 
            "ブックビルディング期間が始まっている銘柄は、仮条件区分を訂正できません。");

    /**
     * 訂正可能期間が、ブックビルディング開始前までの項目のチェック。<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_00555 = errorMgr.defineErrorInfo(
            555,
            "BUSINESS_ERROR_00555", 
            "ブックビルディング期間が始まっている銘柄は、仮条件上限値を削除できません。");

    /**
     * 訂正可能期間が、ブックビルディング開始前までの項目のチェック。<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_00556 = errorMgr.defineErrorInfo(
            556,
            "BUSINESS_ERROR_00556", 
            "ブックビルディング期間が始まっている銘柄は、仮条件下限値を削除できません。");

    /**
     * 訂正可能期間が、ブックビルディング開始前までの項目のチェック。<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_00557 = errorMgr.defineErrorInfo(
            557,
            "BUSINESS_ERROR_00557", 
            "ブックビルディング期間が始まっている銘柄は、仮条件提示日を訂正できません。");

    /**
     * 訂正可能期間が、ブックビルディング開始前までの項目のチェック。<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_00558 = errorMgr.defineErrorInfo(
            558,
            "BUSINESS_ERROR_00558", 
            "ブックビルディング期間が始まっている銘柄は、購入申込単位を訂正できません。");

    /**
     * 訂正可能期間が、ブックビルディング開始前までの項目のチェック。<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_00559 = errorMgr.defineErrorInfo(
            559,
            "BUSINESS_ERROR_00559", 
            "ブックビルディング期間が始まっている銘柄は、刻みを訂正できません。");

    /**
     * アップロード期間のチェック。<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_00560 = errorMgr.defineErrorInfo(
            560,
            "BUSINESS_ERROR_00560", 
            "アップロード期間チェックエラー。（繰上抽選：公開日が過ぎている）");

    /**
     * 訂正可能期間が、ブックビルディング開始前までの項目のチェック。<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_00561 = errorMgr.defineErrorInfo(
            561,
            "BUSINESS_ERROR_00561", 
            "ブックビルディング期間が始まっている銘柄は、表示用単位区分を訂正できません。");

    /**
     * 訂正可能期間が、ブックビルディング開始前までの項目のチェック。<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_00562 = errorMgr.defineErrorInfo(
            562,
            "BUSINESS_ERROR_00562", 
            "ブックビルディング期間が始まっている銘柄は、主幹事証券会社名を訂正できません。");

    /**
     * 訂正可能期間が、ブックビルディング開始前までの項目のチェック。<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_00563 = errorMgr.defineErrorInfo(
            563,
            "BUSINESS_ERROR_00563", 
            "ブックビルディング期間が始まっている銘柄は、ブックビルディング開始日時を訂正できません。");

    /**
     * 訂正可能期間が、新規抽選終了までの項目のチェック。<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_00564 = errorMgr.defineErrorInfo(
            564,
            "BUSINESS_ERROR_00564", 
            "新規抽選終了日を過ぎている銘柄は、公開日を訂正できません。");

    /**
     * 訂正可能期間が、新規抽選終了までの項目のチェック。<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_00565 = errorMgr.defineErrorInfo(
            565,
            "BUSINESS_ERROR_00565", 
            "新規抽選終了日を過ぎている銘柄は、仮条件上限値を訂正できません。");

    /**
     * 訂正可能期間が、新規抽選終了までの項目のチェック。<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_00566 = errorMgr.defineErrorInfo(
            566,
            "BUSINESS_ERROR_00566", 
            "新規抽選終了日を過ぎている銘柄は、仮条件下限値を訂正できません。");

    /**
     * 訂正可能期間が、新規抽選終了までの項目のチェック。<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_00567 = errorMgr.defineErrorInfo(
            567,
            "BUSINESS_ERROR_00567", 
            "新規抽選終了日を過ぎている銘柄は、ブックビルディング終了日時を訂正できません。");

    /**
     * 訂正可能期間が、新規抽選終了までの項目のチェック。<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_00568 = errorMgr.defineErrorInfo(
            568,
            "BUSINESS_ERROR_00568", 
            "新規抽選終了日を過ぎている銘柄は、公開価格決定日を訂正できません。");

    /**
     * 訂正可能期間が、新規抽選終了までの項目のチェック。<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_00569 = errorMgr.defineErrorInfo(
            569,
            "BUSINESS_ERROR_00569", 
            "新規抽選終了日を過ぎている銘柄は、抽選日を訂正できません。");

    /**
     * 訂正可能期間が、公開日までの項目のチェック。<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_00570 = errorMgr.defineErrorInfo(
            570,
            "BUSINESS_ERROR_00570", 
            "公開日を過ぎている銘柄は、銘柄コードを訂正できません。");

    /**
     * 訂正可能期間が、公開日までの項目のチェック。<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_00571 = errorMgr.defineErrorInfo(
            571,
            "BUSINESS_ERROR_00571", 
            "公開日を過ぎている銘柄は、公募数量を訂正できません。");

    /**
     * 訂正可能期間が、公開日までの項目のチェック。<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_00572 = errorMgr.defineErrorInfo(
            572,
            "BUSINESS_ERROR_00572", 
            "公開日を過ぎている銘柄は、売出数量を訂正できません。");

    /**
     * 訂正可能期間が、公開日までの項目のチェック。<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_00573 = errorMgr.defineErrorInfo(
            573,
            "BUSINESS_ERROR_00573", 
            "公開日を過ぎている銘柄は、当社取扱数量を訂正できません。");

    /**
     * 訂正可能期間が、公開日までの項目のチェック。<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_00574 = errorMgr.defineErrorInfo(
            574,
            "BUSINESS_ERROR_00574", 
            "公開日を過ぎている銘柄は、発行会社ロゴファ@イルURLを訂正できません。");

    /**
     * 訂正可能期間が、公開日までの項目のチェック。<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_00575 = errorMgr.defineErrorInfo(
            575,
            "BUSINESS_ERROR_00575", 
            "公開日を過ぎている銘柄は、発行会社ウェブサイトURLを訂正できません。");

    /**
     * 訂正可能期間が、公開日までの項目のチェック。<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_00576 = errorMgr.defineErrorInfo(
            576,
            "BUSINESS_ERROR_00576", 
            "公開日を過ぎている銘柄は、発行会社概要を訂正できません。");

    /**
     * 訂正可能期間が、公開日までの項目のチェック。<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_00577 = errorMgr.defineErrorInfo(
            577,
            "BUSINESS_ERROR_00577", 
            "公開日を過ぎている銘柄は、備考を訂正できません。");

    /**
     * 訂正可能期間が、購入申込開始日（目論見書記載）までの項目のチェック。<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_00578 = errorMgr.defineErrorInfo(
            578,
            "BUSINESS_ERROR_00578", 
            "購入申込開始日（目論見書記載）を過ぎている銘柄は、購入申込開始日（目論見書記載）を訂正できません。");

    /**
     * 訂正可能期間が、購入申込終了日（目論見書記載）までの項目のチェック。<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_00579 = errorMgr.defineErrorInfo(
            579,
            "BUSINESS_ERROR_00579", 
            "購入申込終了日（目論見書記載）を過ぎている銘柄は、購入申込終了日（目論見書記載）を訂正できません。");

    /**
     * 訂正可能期間が、購入申込開始日時（当社設定）までの項目のチェック。<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_00580 = errorMgr.defineErrorInfo(
            580,
            "BUSINESS_ERROR_00580", 
            "購入申込開始日時（当社設定）を過ぎている銘柄は、購入申込開始日時（当社設定）を訂正できません。");

    /**
     * 訂正可能期間が、購入申込終了日時（当社設定）までの項目のチェック。<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_00581 = errorMgr.defineErrorInfo(
            581,
            "BUSINESS_ERROR_00581", 
            "購入申込終了日時（当社設定）を過ぎている銘柄は、購入申込終了日時（当社設定を訂正できません。");

    /**
     * 訂正可能期間が、（ブックビルディング開始日時―新規抽選終了）の項目のチェック。<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_00582 = errorMgr.defineErrorInfo(
            582,
            "BUSINESS_ERROR_00582", 
            "ブックビルディング開始日時―新規抽選終了日を過ぎている銘柄は、公開価格を訂正できません。");

    /**
     * 訂正可能期間が、（ブックビルディング開始日時―購入申込開始日時（当社設定））の項目のチェック。<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_00583 = errorMgr.defineErrorInfo(
            583,
            "BUSINESS_ERROR_00583", 
            "ブックビルディング開始日時―購入申込開始日時（当社設定）を過ぎている銘柄は、公開価格を訂正できません。");

    /**
     * 訂正可能期間が、（ブックビルディング開始日時―購入申込開始日時（当社設定））の項目のチェック。<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_00584 = errorMgr.defineErrorInfo(
            584,
            "BUSINESS_ERROR_00584", 
            "ブックビルディング開始日時―購入申込開始日時（当社設定）を過ぎている銘柄は、公開価格（ディスカウント率）を訂正できません。");

    /**
     * IPO中止可能状態のチェック。<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_00585 = errorMgr.defineErrorInfo(
            585,
            "BUSINESS_ERROR_00585", 
            "IPO中止可能状態のチェックエラー。（スケジュール未定で且つ、停止中でない）");

    /**
     * IPO中止可能状態のチェック。<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_00586 = errorMgr.defineErrorInfo(
            586,
            "BUSINESS_ERROR_00586", 
            "IPO中止可能状態のチェックエラー。（スケジュール決定済で且つ、購入申込を終了していないで且つ、停止中でない）");

    /**
     * ブックビルディングダウンロード可能状態のチェック。<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_00587 = errorMgr.defineErrorInfo(
            587,
            "BUSINESS_ERROR_00587", 
            "ブックビルディングダウンロード可能状態のチェックエラー。（ブックビルディング開始前）");

    /**
     * 削除銘柄。<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_00588 = errorMgr.defineErrorInfo(
            588,
            "BUSINESS_ERROR_00588", 
            "銘柄が削除されています。");

    /**
     * 募集中止銘柄です。<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_00589 = errorMgr.defineErrorInfo(
            589,
            "BUSINESS_ERROR_00589", 
            "募集中止銘柄です。");

    /**
     * 募集停止／再開可能状態のチェック。<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_00590 = errorMgr.defineErrorInfo(
            590,
            "BUSINESS_ERROR_00590", 
            "募集停止／再開可能状態のチェックエラー。（IPOスケジュール決定済且つ、購入申込期間が終了している）");

    /**
     * 成行可能のチェック。<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_00591 = errorMgr.defineErrorInfo(
            591,
            "BUSINESS_ERROR_00591", 
            "成行可能が未指定です。");

    /**
     * 成行可能のチェック。<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_00592 = errorMgr.defineErrorInfo(
            592,
            "BUSINESS_ERROR_00592", 
            "成行可能が存在しないコード値です。");

    /**
     * 未定決定区分のチェック。<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_00593 = errorMgr.defineErrorInfo(
            593,
            "BUSINESS_ERROR_00593", 
            "未定決定区分が未指定です。");

    /**
     * 未定決定区分のチェック。<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_00594 = errorMgr.defineErrorInfo(
            594,
            "BUSINESS_ERROR_00594", 
            "未定決定区分が存在しないコード値です。");

    /**
     * 訂正内容チェック。<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_00595 = errorMgr.defineErrorInfo(
            595,
            "BUSINESS_ERROR_00595", 
            "数量、単価に訂正がされていません。");

    /**
     * 未定スケジュールのチェック。<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_00596 = errorMgr.defineErrorInfo(
            596,
            "BUSINESS_ERROR_00596", 
            "未定の場合に登録される日数項目登録に矛盾があります。");

    /**
     * アップロード期間チェック。<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_00597 = errorMgr.defineErrorInfo(
            597,
            "BUSINESS_ERROR_00597", 
            "アップロード期間チェックエラー。（繰上抽選：新規抽選が終了していない）");

    /**
     * 当選数量合計値は当社取扱数量より大きいです。<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_00598 = errorMgr.defineErrorInfo(
            598,
            "BUSINESS_ERROR_00598", 
            "当選数量合計値は当社取扱数量より大きいです。");

    /**
     * 当選数量合計値　@＞　@割当可能数量となります。<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_00599 = errorMgr.defineErrorInfo(
            599,
            "BUSINESS_ERROR_00599", 
            "当選数量合計値　@＞　@割当可能数量となります。");

    /**
     * 注文ＩＤのＮＵＬＬチェック。<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_00600 = errorMgr.defineErrorInfo(
            600,
            "BUSINESS_ERROR_00600", 
            "注文IDが未指定です。");

    /**
     * 取引区分が未指定です。<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_00601 = errorMgr.defineErrorInfo(
            601,
            "BUSINESS_ERROR_00601", 
            "取引区分が未指定です。");

    /**
     * 取引区分が存在しないコード値です。<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_00602 = errorMgr.defineErrorInfo(
            602,
            "BUSINESS_ERROR_00602", 
            "取引区分が存在しないコード値です。");

    /**
     * 弁済チェック。<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_00603 = errorMgr.defineErrorInfo(
            603,
            "BUSINESS_ERROR_00603", 
            "弁済が未指定です。");

    /**
     * 口座区分が未指定です。<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_00604 = errorMgr.defineErrorInfo(
            604,
            "BUSINESS_ERROR_00604", 
            "口座区分が未指定です。");

    /**
     * 口座区分の値チェック。<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_00605 = errorMgr.defineErrorInfo(
            605,
            "BUSINESS_ERROR_00605", 
            "口座区分が存在しないコード値です。");

    /**
     * 市場コードのチェック。<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_00608 = errorMgr.defineErrorInfo(
            608,
            "BUSINESS_ERROR_00608", 
            "市場コードが存在しないコード値です。");

    /**
     * 決済建株一覧チェック。<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_00610 = errorMgr.defineErrorInfo(
            610,
            "BUSINESS_ERROR_00610", 
            "決済建株一覧が未指定です。");

    /**
     * 決済建株一覧チェック。<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_00611 = errorMgr.defineErrorInfo(
            611,
            "BUSINESS_ERROR_00611", 
            "決済建株一覧が0以下の値です。");

    /**
     * 決済建株一覧総株数チェック。<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_00612 = errorMgr.defineErrorInfo(
            612,
            "BUSINESS_ERROR_00612", 
            "決済建株一覧の要素数内の全ての注文株数が未指定です。");

    /**
     * 要求ページ番号チェック3（要求ページ番号が0以下）。<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_00616 = errorMgr.defineErrorInfo(
            616,
            "BUSINESS_ERROR_00616", 
            "要求ページ番号の値が0以下です。");

    /**
     * ページ内表示行数チェック3（ページ内表示行数が0以下）。<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_00617 = errorMgr.defineErrorInfo(
            617,
            "BUSINESS_ERROR_00617", 
            "ページ内表示行数の値が0以下です。");

    /**
     * 決済順序区分チェック。<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_00618 = errorMgr.defineErrorInfo(
            618,
            "BUSINESS_ERROR_00618", 
            "決済順序区分が存在しないコード値です。");

    /**
     * 決済順位チェック。<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_00619 = errorMgr.defineErrorInfo(
            619,
            "BUSINESS_ERROR_00619", 
            "決済順序区分が“0：ランダム”の場合は、決済建株一覧の要素数内の全ての決済順位が未指定です。");

    /**
     * 決済順位チェック。<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_00620 = errorMgr.defineErrorInfo(
            620,
            "BUSINESS_ERROR_00620", 
            "決済順序区分が“0：ランダム”の場合は、決済対象なし。");

    /**
     * 決済順位チェック。<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_00621 = errorMgr.defineErrorInfo(
            621,
            "BUSINESS_ERROR_00621", 
            "ランダム返済以外は注文株数、決済順位の指定不可。");

    /**
     * 現引現渡元口座区分チェック。<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_00622 = errorMgr.defineErrorInfo(
            622,
            "BUSINESS_ERROR_00622", 
            "現引先現渡元口座区分が未指定です。");

    /**
     * 現引現渡元口座区分チェック。<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_00623 = errorMgr.defineErrorInfo(
            623,
            "BUSINESS_ERROR_00623", 
            "現引先現渡元口座区分が存在しないコード値です。");

    /**
     * 決済順位チェック。<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_00624 = errorMgr.defineErrorInfo(
            624,
            "BUSINESS_ERROR_00624", 
            "決済順序区分がnullまたは、”1：単価益順”、”2：単価損順”、”3：建日順”の値の場合、注文株数が未指定です。");

    /**
     * 出金余力エラー。<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_00625 = errorMgr.defineErrorInfo(
            625,
            "BUSINESS_ERROR_00625", 
            "出金余力エラー。");

    /**
     * 約定状態区分チェック（約定状態区分が未定義の値）。<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_00626 = errorMgr.defineErrorInfo(
            626,
            "BUSINESS_ERROR_00626", 
            "約定状態区分の値が存在しないコード値です。");

    /**
     * 弁済区分チェック。<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_00628 = errorMgr.defineErrorInfo(
            628,
            "BUSINESS_ERROR_00628", 
            "弁済区分が未指定です。");

    /**
     * 弁済区分チェック。<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_00629 = errorMgr.defineErrorInfo(
            629,
            "BUSINESS_ERROR_00629", 
            "弁済区分の値が存在しないコード値です。");

    /**
     * 弁済期限値チェック。<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_00630 = errorMgr.defineErrorInfo(
            630,
            "BUSINESS_ERROR_00630", 
            "弁済期限値が未指定です。");

    /**
     * 弁済期限値チェック。<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_00631 = errorMgr.defineErrorInfo(
            631,
            "BUSINESS_ERROR_00631", 
            "弁済期限値が数字以外の値です。");

    /**
     * 弁済期限値チェック。<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_00632 = errorMgr.defineErrorInfo(
            632,
            "BUSINESS_ERROR_00632", 
            "弁済期限値が0以下の値です。");

    /**
     * 当該注文は既に全部約定済。<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_00634 = errorMgr.defineErrorInfo(
            634,
            "BUSINESS_ERROR_00634", 
            "当該注文は既に全部約定済。");

    /**
     * 合計約定数量が注文株数を超えていないかチェック。<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_00635 = errorMgr.defineErrorInfo(
            635,
            "BUSINESS_ERROR_00635", 
            "合計約定数量が注文数量を超えています。");

    /**
     * 該当注文は変更／取消の受付済または発注中の状態。<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_00636 = errorMgr.defineErrorInfo(
            636,
            "BUSINESS_ERROR_00636", 
            "該当注文は変更／取消の受付済または発注中の状態です。");

    /**
     * 現物特定口座未開設。<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_00637 = errorMgr.defineErrorInfo(
            637,
            "BUSINESS_ERROR_00637", 
            "現物の特定口座開設なし。");

    /**
     * 指定銘柄は指定市場での取扱不可。<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_00638 = errorMgr.defineErrorInfo(
            638,
            "BUSINESS_ERROR_00638", 
            "指定銘柄は指定市場での取扱不可。");

    /**
     * 取引区分の値が不正です。<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_00639 = errorMgr.defineErrorInfo(
            639,
            "BUSINESS_ERROR_00639", 
            "取引区分の値が不正です。（買建チェック）");

    /**
     * 取引区分の値が不正です。<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_00640 = errorMgr.defineErrorInfo(
            640,
            "BUSINESS_ERROR_00640", 
            "取引区分の値が不正です。（売建チェック）");

    /**
     * 不正な弁済区分（SONAR）。<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_00641 = errorMgr.defineErrorInfo(
            641,
            "BUSINESS_ERROR_00641", 
            "不正な弁済区分（SONAR）。");

    /**
     * 取扱可能市場なし。<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_00643 = errorMgr.defineErrorInfo(
            643,
            "BUSINESS_ERROR_00643", 
            "取扱可能市場が存在しない。");

    /**
     * 非取扱市場エラー。<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_00645 = errorMgr.defineErrorInfo(
            645,
            "BUSINESS_ERROR_00645", 
            "非取扱市場エラー。");

    /**
     * 取扱可能弁済なし。<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_00646 = errorMgr.defineErrorInfo(
            646,
            "BUSINESS_ERROR_00646", 
            "取扱可能弁済なし。");

    /**
     * 処理対象外。<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_00649 = errorMgr.defineErrorInfo(
            649,
            "BUSINESS_ERROR_00649", 
            "訂正取消通知区分の値が不正です。");

    /**
     * 注文株数のチェック。<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_00650 = errorMgr.defineErrorInfo(
            650,
            "BUSINESS_ERROR_00650", 
            "注文株数チェックエラー。");

    /**
     * 買建／売建決定不可。<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_00651 = errorMgr.defineErrorInfo(
            651,
            "BUSINESS_ERROR_00651", 
            "データ不整合による買建／売建決定不可。");

    /**
     * 注文カテゴリチェック。<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_00653 = errorMgr.defineErrorInfo(
            653,
            "BUSINESS_ERROR_00653", 
            "注文カテゴリの値が不正です。");

    /**
     * 決済可能残高のチェック。<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_00657 = errorMgr.defineErrorInfo(
            657,
            "BUSINESS_ERROR_00657", 
            "注文株数が決済可能残高より大きいです。");

    /**
     * 必須プロパティのチェック。<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_00658 = errorMgr.defineErrorInfo(
            658,
            "BUSINESS_ERROR_00658", 
            "株式出来通知キューの必須設定項目が未指定です。");

    /**
     * 注文単位IDに該当する建株返済指定情報を取得できません。<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_00659 = errorMgr.defineErrorInfo(
            659,
            "BUSINESS_ERROR_00659", 
            "注文単位IDに該当する建株返済指定情報を取得できません。");

    /**
     * 建株一覧を取得できません。<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_00660 = errorMgr.defineErrorInfo(
            660,
            "BUSINESS_ERROR_00660", 
            "建株一覧を取得できません。");

    /**
     * 弁済期限値特定不可。<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_00661 = errorMgr.defineErrorInfo(
            661,
            "BUSINESS_ERROR_00661", 
            "弁済期限値特定不可。");

    /**
     * 銘柄、取扱可能市場チェック（非取扱銘柄）。<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_00664 = errorMgr.defineErrorInfo(
            664,
            "BUSINESS_ERROR_00664", 
            "非取扱銘柄エラー。");

    /**
     * 取引余力不足。<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_00667 = errorMgr.defineErrorInfo(
            667,
            "BUSINESS_ERROR_00667", 
            "取引余力不足エラー。（信用新規建可能額不足）");

    /**
     * 取引可能額チェック。<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_00668 = errorMgr.defineErrorInfo(
            668,
            "BUSINESS_ERROR_00668", 
            "取引可能額不足エラー。");

    /**
     * 該当する補助口座なし。<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_00670 = errorMgr.defineErrorInfo(
            670,
            "BUSINESS_ERROR_00670", 
            "該当する補助口座なし。");

    /**
     * 取消済みのチェック。<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_00675 = errorMgr.defineErrorInfo(
            675,
            "BUSINESS_ERROR_00675", 
            "指定の注文が既に取消済みです。");

    /**
     * 約定なし。<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_00676 = errorMgr.defineErrorInfo(
            676,
            "BUSINESS_ERROR_00676", 
            "取消対象の約定データが存在しない。");

    /**
     * 取消対象約定データの特定不可エラー。<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_00677 = errorMgr.defineErrorInfo(
            677,
            "BUSINESS_ERROR_00677", 
            "取消対象約定データの特定不可エラー。");

    /**
     * 上限数量超過エラー。<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_00682 = errorMgr.defineErrorInfo(
            682,
            "BUSINESS_ERROR_00682", 
            "株数は指定可能株数上限値を越えています。");

    /**
     * 注文繰越スキップ銘柄チェック。<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_00684 = errorMgr.defineErrorInfo(
            684,
            "BUSINESS_ERROR_00684", 
            "注文繰越スキップ銘柄のチェックエラー。（注文繰越不可の銘柄）");

    /**
     * 複数項目同時訂正不可。<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_00687 = errorMgr.defineErrorInfo(
            687,
            "BUSINESS_ERROR_00687", 
            "複数項目同時訂正不可。");

    /**
     * 逆指値基準値、発注条件演算子変更チェック。<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_00690 = errorMgr.defineErrorInfo(
            690,
            "BUSINESS_ERROR_00690", 
            "市場発注済注文の逆指値基準値及び、発注条件演算子は訂正不可。");

    /**
     * 上場チェック。<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_00691 = errorMgr.defineErrorInfo(
            691,
            "BUSINESS_ERROR_00691", 
            "上場期間中のチェックエラー。");

    /**
     * 売建不可（売建不可銘柄）。<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_00692 = errorMgr.defineErrorInfo(
            692,
            "BUSINESS_ERROR_00692", 
            "売建不可（売建不可銘柄）。");

    /**
     * 一般信用の取扱不可銘柄。<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_00693 = errorMgr.defineErrorInfo(
            693,
            "BUSINESS_ERROR_00693", 
            "一般信用の取扱不可銘柄。");

    /**
     * 売建不可（非貸借銘柄）。<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_00694 = errorMgr.defineErrorInfo(
            694,
            "BUSINESS_ERROR_00694", 
            "売建不可（非貸借銘柄）。");

    /**
     * 非貸借銘柄の取扱不可。<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_00695 = errorMgr.defineErrorInfo(
            695,
            "BUSINESS_ERROR_00695", 
            "非貸借銘柄の取扱不可。");

    /**
     * 制度信用の取扱不可銘柄。<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_00696 = errorMgr.defineErrorInfo(
            696,
            "BUSINESS_ERROR_00696", 
            "制度信用の取扱不可銘柄。");

    /**
     * 取扱可能チェック（信用取引の取扱不可銘柄）。<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_00697 = errorMgr.defineErrorInfo(
            697,
            "BUSINESS_ERROR_00697", 
            "信用取引の取扱不可銘柄。");

    /**
     * 売買停止チェック。<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_00700 = errorMgr.defineErrorInfo(
            700,
            "BUSINESS_ERROR_00700", 
            "銘柄の売買停止中(取引所規制/当社規制)。");

    /**
     * 取扱可能市場チェックエラー(部店・弁済)。<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_00703 = errorMgr.defineErrorInfo(
            703,
            "BUSINESS_ERROR_00703", 
            "取扱可能市場チェックエラー(部店・弁済)。");

    /**
     * 出来るまで注文取扱のチェック。<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_00704 = errorMgr.defineErrorInfo(
            704,
            "BUSINESS_ERROR_00704", 
            "出来るまで注文取扱のチェックエラー。");

    /**
     * 適格機@関投資家の新規売建時の成行注文は不可。<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_00705 = errorMgr.defineErrorInfo(
            705,
            "BUSINESS_ERROR_00705", 
            "適格機@関投資家の新規売建時の成行注文は不可。");

    /**
     * HOSTが受付可能な株数上限値を超過。<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_00707 = errorMgr.defineErrorInfo(
            707,
            "BUSINESS_ERROR_00707", 
            "HOSTが受付可能な株数上限値を超えています。");

    /**
     * 株数が売買単位の整数倍ではありません。<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_00708 = errorMgr.defineErrorInfo(
            708,
            "BUSINESS_ERROR_00708", 
            "株数が売買単位の整数倍ではありません。");

    /**
     * 株数チェック。<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_00709 = errorMgr.defineErrorInfo(
            709,
            "BUSINESS_ERROR_00709", 
            "株数が0以下の値です。");

    /**
     * 一般口座の建株は特定口座への差し入れ不可。<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_00710 = errorMgr.defineErrorInfo(
            710,
            "BUSINESS_ERROR_00710", 
            "一般口座の建株は特定口座への差し入れ不可。");

    /**
     * 成行指定規制チェック。<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_00712 = errorMgr.defineErrorInfo(
            712,
            "BUSINESS_ERROR_00712", 
            "信用取引・成行指定規制中。");

    /**
     * JASDAQ銘柄成行指定チェック。<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_00713 = errorMgr.defineErrorInfo(
            713,
            "BUSINESS_ERROR_00713", 
            "JASDAQ銘柄は成行指定不可。");

    /**
     * 注文取消内容妥当性チェック。<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_00716 = errorMgr.defineErrorInfo(
            716,
            "BUSINESS_ERROR_00716", 
            "該当注文がSONAR送信未済です。");

    /**
     * ミニ株銘柄コードのチェック。<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_00717 = errorMgr.defineErrorInfo(
            717,
            "BUSINESS_ERROR_00717", 
            "当該銘柄はお取扱対象外です。");

    /**
     * ミニ株取引銘柄のチェック。<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_00718 = errorMgr.defineErrorInfo(
            718,
            "BUSINESS_ERROR_00718", 
            "当該銘柄は株式ミニ投資の対象外です。");

    /**
     * ミニ株売買が停止されていないことのチェック。<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_00719 = errorMgr.defineErrorInfo(
            719,
            "BUSINESS_ERROR_00719", 
            "当該銘柄は株式ミニ投資の取引停止中です。");

    /**
     * 上限数量チェック。<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_00720 = errorMgr.defineErrorInfo(
            720,
            "BUSINESS_ERROR_00720", 
            "ミニ株買付注文株数が上限株数を超えています。");

    /**
     * 売買単位チェック。<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_00721 = errorMgr.defineErrorInfo(
            721,
            "BUSINESS_ERROR_00721", 
            "ミニ株買付注文株数がミニ株売買単位の整数倍ではありません。");

    /**
     * 売買単位チェック。<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_00723 = errorMgr.defineErrorInfo(
            723,
            "BUSINESS_ERROR_00723", 
            "ミニ株売買単位が不正です。");

    /**
     * 株数チェック。<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_00724 = errorMgr.defineErrorInfo(
            724,
            "BUSINESS_ERROR_00724", 
            "買付制限期間中に単位株数以上注文されました。");

    /**
     * 株数チェック。<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_00725 = errorMgr.defineErrorInfo(
            725,
            "BUSINESS_ERROR_00725", 
            "売付制限期間中に単位株の売注文がされました。");

    /**
     * W指値注文でない。<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_00726 = errorMgr.defineErrorInfo(
            726,
            "BUSINESS_ERROR_00726", 
            "W指値注文ではありません。");

    /**
     * ミニ株は、同一日に同一銘柄を売買できないエラー。<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_00728 = errorMgr.defineErrorInfo(
            728,
            "BUSINESS_ERROR_00728", 
            "ミニ株の場合は、同一日に同一銘柄の注文が重複できません。");

    /**
     * 指定銘柄の空売り規制数量を超過エラー。<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_00734 = errorMgr.defineErrorInfo(
            734,
            "BUSINESS_ERROR_00734", 
            "指定銘柄の空売り規制数量を超えています。");

    /**
     * 取引銘柄取得できない。<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_00735 = errorMgr.defineErrorInfo(
            735,
            "BUSINESS_ERROR_00735", 
            "指定した市場、指数種別、限月、プット／コール、行使価格の組み合わせは取り扱っていません。");

    /**
     * STOP高考慮の場合は、手数料商品コードが指定必要です。<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_00736 = errorMgr.defineErrorInfo(
            736,
            "BUSINESS_ERROR_00736", 
            "STOP高考慮の場合は、手数料商品コードが指定必要です。");

    /**
     * 入出金注文単位がデータ不整合です。<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_00739 = errorMgr.defineErrorInfo(
            739,
            "BUSINESS_ERROR_00739", 
            "入出金注文単位がデータ不整合です。");

    /**
     * 振替可能回数を超えてないかチェック。<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_00740 = errorMgr.defineErrorInfo(
            740,
            "BUSINESS_ERROR_00740", 
            "振替回数が上限回数を超えています。");

    /**
     * 建代金の上限値超過(総)エラー。<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_00741 = errorMgr.defineErrorInfo(
            741,
            "BUSINESS_ERROR_00741", 
            "建代金の上限値超過(総)エラー。");

    /**
     * 建代金の上限値超過(銘柄単位)エラー。<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_00742 = errorMgr.defineErrorInfo(
            742,
            "BUSINESS_ERROR_00742", 
            "建代金の上限値超過(銘柄単位)エラー。");

    /**
     * 建代金の上限値超過(一日)エラー。<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_00743 = errorMgr.defineErrorInfo(
            743,
            "BUSINESS_ERROR_00743", 
            "建代金の上限値超過(一日)エラー。");

    /**
     * 現渡対象の保有資産の数量不足エラー。<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_00744 = errorMgr.defineErrorInfo(
            744,
            "BUSINESS_ERROR_00744", 
            "現渡対象の保有資産の数量不足エラー。");

    /**
     * 当該部店では、取扱が不可能です。<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_00746 = errorMgr.defineErrorInfo(
            746,
            "BUSINESS_ERROR_00746", 
            "当該部店では、取扱が不可能です。");

    /**
     * 当該顧客が、指定の信用取引口座を開設していません。<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_00747 = errorMgr.defineErrorInfo(
            747,
            "BUSINESS_ERROR_00747", 
            "当該顧客が、指定の信用取引口座を開設していません。");

    /**
     * 建株決済期日チェック。<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_00748 = errorMgr.defineErrorInfo(
            748,
            "BUSINESS_ERROR_00748", 
            "該当建株の発注日が決済期日を超えています。");

    /**
     * 決済機@関受付時間テーブルからのエラーをチェック。<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_00751 = errorMgr.defineErrorInfo(
            751,
            "BUSINESS_ERROR_00751", 
            "指定した決済機@関の受付時間に関する情報が取得できませんでした。");

    /**
     * 入金金額単位エラー。<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_00752 = errorMgr.defineErrorInfo(
            752,
            "BUSINESS_ERROR_00752", 
            "入金金額単位エラー。");

    /**
     * 1日当たりの入金回数が上限回数を越えてないかをチェック。<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_00753 = errorMgr.defineErrorInfo(
            753,
            "BUSINESS_ERROR_00753", 
            "一日の最大入金回数エラー。");

    /**
     * 1日当たりの入金総額を超えてないかをチェック。<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_00754 = errorMgr.defineErrorInfo(
            754,
            "BUSINESS_ERROR_00754", 
            "一日の最大入金金額エラー。");

    /**
     * 会社別取引金額データを取得できません。<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_00755 = errorMgr.defineErrorInfo(
            755,
            "BUSINESS_ERROR_00755", 
            "会社別取引金額データを取得できません。");

    /**
     * 出金取引金額範囲外エラー。<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_00756 = errorMgr.defineErrorInfo(
            756,
            "BUSINESS_ERROR_00756", 
            "出金取引金額範囲外エラー。");

    /**
     * 引数の受渡日と同じ日にすでに出金注文が出てないかどうかをチェック。<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_00757 = errorMgr.defineErrorInfo(
            757,
            "BUSINESS_ERROR_00757", 
            "指定したお振込予定日と同じ振込日の出金申込がすでに登録されています。");

    /**
     * サービス区分のチェック。<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_00758 = errorMgr.defineErrorInfo(
            758,
            "BUSINESS_ERROR_00758", 
            "サービス区分が未指定です。");

    /**
     * 振替金額が入力されていません。<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_00759 = errorMgr.defineErrorInfo(
            759,
            "BUSINESS_ERROR_00759", 
            "振替金額が入力されていません。");

    /**
     * 振替金額が可能額を超えています。<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_00761 = errorMgr.defineErrorInfo(
            761,
            "BUSINESS_ERROR_00761", 
            "振替金額が可能額を超えています。");

    /**
     * 決済機@関IDに該当する会社別決済機@関条件を取得できません。<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_00763 = errorMgr.defineErrorInfo(
            763,
            "BUSINESS_ERROR_00763", 
            "決済機@関IDに該当する会社別決済機@関条件を取得できません。");

    /**
     * 金融機@関入出金状況レコードのデータエラー。<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_00764 = errorMgr.defineErrorInfo(
            764,
            "BUSINESS_ERROR_00764", 
            "金融機@関入出金状況レコードのデータエラー。");

    /**
     * 入金金額チェック。<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_00766 = errorMgr.defineErrorInfo(
            766,
            "BUSINESS_ERROR_00766", 
            "入金金額入力値エラー。");

    /**
     * 決済機@関IDチェック。<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_00767 = errorMgr.defineErrorInfo(
            767,
            "BUSINESS_ERROR_00767", 
            "決済機@関IDの入力が不正です。");

    /**
     * AP層セッションIDチェック。<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_00769 = errorMgr.defineErrorInfo(
            769,
            "BUSINESS_ERROR_00769", 
            "AP層セッションIDが未指定です。");

    /**
     * 確認時注文IDチェック。<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_00770 = errorMgr.defineErrorInfo(
            770,
            "BUSINESS_ERROR_00770", 
            "確認時注文IDが未指定です。");

    /**
     * 出金金額入力値エラー。<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_00771 = errorMgr.defineErrorInfo(
            771,
            "BUSINESS_ERROR_00771", 
            "出金金額入力値エラー。");

    /**
     * 振込予定日チェック。<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_00772 = errorMgr.defineErrorInfo(
            772,
            "BUSINESS_ERROR_00772", 
            "振込予定日が未指定です。");

    /**
     * 直近振込日＞振込予定日となっています。<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_00773 = errorMgr.defineErrorInfo(
            773,
            "BUSINESS_ERROR_00773", 
            "直近振込日＞振込予定日となっています。");

    /**
     * 出金口座未開設エラー。<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_00774 = errorMgr.defineErrorInfo(
            774,
            "BUSINESS_ERROR_00774", 
            "出金口座未開設エラー。");

    /**
     * 振込日チェック。<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_00775 = errorMgr.defineErrorInfo(
            775,
            "BUSINESS_ERROR_00775", 
            "入金振込年月日入力値エラー。");

    /**
     * 振込先金融機@関コードチェック。<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_00776 = errorMgr.defineErrorInfo(
            776,
            "BUSINESS_ERROR_00776", 
            "入金金融機@関コード入力値エラー。");

    /**
     * メールアドレスチェックエラー。<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_00777 = errorMgr.defineErrorInfo(
            777,
            "BUSINESS_ERROR_00777", 
            "メールアドレスチェックエラー。");

    /**
     * 金融機@関を取得できません。<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_00778 = errorMgr.defineErrorInfo(
            778,
            "BUSINESS_ERROR_00778", 
            "振込先金融機@関コードに該当する金融機@関を取得できません。");

    /**
     * 部店コードチェック。<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_00779 = errorMgr.defineErrorInfo(
            779,
            "BUSINESS_ERROR_00779", 
            "部店コードの入力が不正です。");

    /**
     * 顧客コードチェック。<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_00780 = errorMgr.defineErrorInfo(
            780,
            "BUSINESS_ERROR_00780", 
            "顧客コードの入力が不正です。");

    /**
     * 注文日チェック。<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_00781 = errorMgr.defineErrorInfo(
            781,
            "BUSINESS_ERROR_00781", 
            "注文日（自）は注文日（至）より大きいです。");

    /**
     * .comデビット決済取引番号チェック。<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_00782 = errorMgr.defineErrorInfo(
            782,
            "BUSINESS_ERROR_00782", 
            ".comデビット決済取引番号（自）の入力が不正です。");

    /**
     * .comデビット決済取引番号チェック。<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_00783 = errorMgr.defineErrorInfo(
            783,
            "BUSINESS_ERROR_00783", 
            ".comデビット決済取引番号（自）は.comデビット決済取引番号（至）より大きいです。");

    /**
     * 処理状態チェック。<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_00784 = errorMgr.defineErrorInfo(
            784,
            "BUSINESS_ERROR_00784", 
            "処理状態が存在しないコード値です。");

    /**
     * 相互チェック。<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_00785 = errorMgr.defineErrorInfo(
            785,
            "BUSINESS_ERROR_00785", 
            "顧客コード、注文日（自）、注文日（至）、受渡日、.comデビット決済取引番号（自）のすべてが未指定です。");

    /**
     * 要求ページ番号チェック。<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_00786 = errorMgr.defineErrorInfo(
            786,
            "BUSINESS_ERROR_00786", 
            "要求ページ番号の入力が不正です。");

    /**
     * .comデビット決済取引番号のチェック。<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_00787 = errorMgr.defineErrorInfo(
            787,
            "BUSINESS_ERROR_00787", 
            ".comデビット決済取引番号（至）の入力が不正です。");

    /**
     * .comデビット決済取引番号のチェック。<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_00788 = errorMgr.defineErrorInfo(
            788,
            "BUSINESS_ERROR_00788", 
            ".comデビット決済取引番号（自）が未指定の場合、.comデビット決済取引番号（至）が指定不可です。");

    /**
     * 顧客コードチェック。<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_00789 = errorMgr.defineErrorInfo(
            789,
            "BUSINESS_ERROR_00789", 
            "顧客コード（自）の入力が不正です。");

    /**
     * 注文受付区分チェック。<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_00790 = errorMgr.defineErrorInfo(
            790,
            "BUSINESS_ERROR_00790", 
            "注文受付区分が存在しないコード値です。");

    /**
     * 指示リストチェック。<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_00793 = errorMgr.defineErrorInfo(
            793,
            "BUSINESS_ERROR_00793", 
            "指示リストの要素数が0です。");

    /**
     * 顧客コードチェック。<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_00794 = errorMgr.defineErrorInfo(
            794,
            "BUSINESS_ERROR_00794", 
            "顧客コード（至）の入力が不正です。");

    /**
     * 顧客コードチェック。<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_00795 = errorMgr.defineErrorInfo(
            795,
            "BUSINESS_ERROR_00795", 
            "顧客コード（自）は顧客コード（至）より大きいです。");

    /**
     * 連絡日時チェック.。<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_00796 = errorMgr.defineErrorInfo(
            796,
            "BUSINESS_ERROR_00796", 
            "連絡日時（自）は連絡日時（至）より大きいです。");

    /**
     * 連絡日時チェック.。<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_00797 = errorMgr.defineErrorInfo(
            797,
            "BUSINESS_ERROR_00797", 
            "連絡日時（自）が未指定の場合、連絡日時（至）が指定不可です。");

    /**
     * 振込日チェック。<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_00799 = errorMgr.defineErrorInfo(
            799,
            "BUSINESS_ERROR_00799", 
            "振込日（自）は振込日（至）より大きいです。");

    /**
     * 振込日チェック。<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_00800 = errorMgr.defineErrorInfo(
            800,
            "BUSINESS_ERROR_00800", 
            "振込日（自）が未指定の場合、振込日（至）が指定不可です。");

    /**
     * 出力区分チェック。<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_00801 = errorMgr.defineErrorInfo(
            801,
            "BUSINESS_ERROR_00801", 
            "出力区分が存在しないコード値です。");

    /**
     * 要求ページ番号チェック。<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_00802 = errorMgr.defineErrorInfo(
            802,
            "BUSINESS_ERROR_00802", 
            "出力区分が“一覧”の場合は、要求ページ番号の入力が不正です。");

    /**
     * 要求ページ番号チェック.。<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_00803 = errorMgr.defineErrorInfo(
            803,
            "BUSINESS_ERROR_00803", 
            "出力区分が“一覧”の場合は、要求ページ番号が数字以外の値です。");

    /**
     * ページ内表示行数チェック。<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_00804 = errorMgr.defineErrorInfo(
            804,
            "BUSINESS_ERROR_00804", 
            "出力区分が“一覧”の場合は、ページ内表示行数の入力が不正です。");

    /**
     * ページ内表示行数チェック。<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_00805 = errorMgr.defineErrorInfo(
            805,
            "BUSINESS_ERROR_00805", 
            "出力区分が“一覧”の場合は、ページ内表示行数が数字以外の値です。");

    /**
     * 出来るまで注文は執行条件の訂正不可。<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_00806 = errorMgr.defineErrorInfo(
            806,
            "BUSINESS_ERROR_00806", 
            "出来るまで注文は執行条件の訂正不可。");

    /**
     * 証券会社が取扱不可な執行条件。<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_00807 = errorMgr.defineErrorInfo(
            807,
            "BUSINESS_ERROR_00807", 
            "証券会社が取扱不可な執行条件。");

    /**
     * 建株残高不足エラー。<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_00808 = errorMgr.defineErrorInfo(
            808,
            "BUSINESS_ERROR_00808", 
            "建株残高不足エラー。");

    /**
     * 振替金額のチェック。<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_00809 = errorMgr.defineErrorInfo(
            809,
            "BUSINESS_ERROR_00809", 
            "振替金額が０またはマイナス値です。");

    /**
     * 振替金額のチェック。<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_00810 = errorMgr.defineErrorInfo(
            810,
            "BUSINESS_ERROR_00810", 
            "振替金額のサイズが不正です。");

    /**
     * 振替金額のチェック。<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_00811 = errorMgr.defineErrorInfo(
            811,
            "BUSINESS_ERROR_00811", 
            "振替金額に数字以外の文字が入力されています。");

    /**
     * 注文の訂正／取消の受付が可能かどうか判定する。<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_00812 = errorMgr.defineErrorInfo(
            812,
            "BUSINESS_ERROR_00812", 
            "市場閉局後―証券会社毎に通知を受けて行う出来終了通知終了までの間は、注文訂正／取消受付を不可とします。");

    /**
     * 銘柄コードが数字以外。<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_00815 = errorMgr.defineErrorInfo(
            815,
            "BUSINESS_ERROR_00815", 
            "銘柄コードが数字以外の値です。");

    /**
     * 注文状況区分のチェック。<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_00816 = errorMgr.defineErrorInfo(
            816,
            "BUSINESS_ERROR_00816", 
            "注文状況区分が存在しないコード値です。");

    /**
     * 執行条件変更チェック(JASDAQ銘柄は執行条件の訂正不可)。<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_00819 = errorMgr.defineErrorInfo(
            819,
            "BUSINESS_ERROR_00819", 
            "JASDAQ銘柄は執行条件の訂正不可。");

    /**
     * 注文有効状態のチェック。<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_00820 = errorMgr.defineErrorInfo(
            820,
            "BUSINESS_ERROR_00820", 
            "クローズした注文は当日約定の現引現渡注文を除いて取消できません。");

    /**
     * 注文の訂正が可能か注文状態であるかどうかをチェック。<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_00821 = errorMgr.defineErrorInfo(
            821,
            "BUSINESS_ERROR_00821", 
            "注文カテゴリが“現引現渡注文”の場合は、訂正不可。");

    /**
     * 決済数量が建株残数量を超過。<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_00824 = errorMgr.defineErrorInfo(
            824,
            "BUSINESS_ERROR_00824", 
            "決済数量が建株残数量を超過。");

    /**
     * 金融機@関受付時間チェックエラー。<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_00826 = errorMgr.defineErrorInfo(
            826,
            "BUSINESS_ERROR_00826", 
            "金融機@関受付時間チェックエラー。");

    /**
     * 証券会社コードが未指定です。<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_00827 = errorMgr.defineErrorInfo(
            827,
            "BUSINESS_ERROR_00827", 
            "証券会社コードが未指定です。");

    /**
     * 識別コードが未指定です。<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_00829 = errorMgr.defineErrorInfo(
            829,
            "BUSINESS_ERROR_00829", 
            "識別コードが未指定です。");

    /**
     * 入力パラメータチェック。<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_00830 = errorMgr.defineErrorInfo(
            830,
            "BUSINESS_ERROR_00830", 
            "入力パラメータチェックエラー。");

    /**
     * サービス区分のチェック。<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_00831 = errorMgr.defineErrorInfo(
            831,
            "BUSINESS_ERROR_00831", 
            "サービス区分の桁数が不正です。");

    /**
     * 申込登録IDが未指定です。<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_00832 = errorMgr.defineErrorInfo(
            832,
            "BUSINESS_ERROR_00832", 
            "申込登録IDが未指定です。");

    /**
     * 部店コードが未指定です。<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_00833 = errorMgr.defineErrorInfo(
            833,
            "BUSINESS_ERROR_00833", 
            "部店コードが未指定です。");

    /**
     * 部店コードのチェック。<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_00834 = errorMgr.defineErrorInfo(
            834,
            "BUSINESS_ERROR_00834", 
            "部店コードのサイズが不正です。");

    /**
     * 顧客コードが未指定です。<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_00835 = errorMgr.defineErrorInfo(
            835,
            "BUSINESS_ERROR_00835", 
            "顧客コードが未指定です。");

    /**
     * 顧客コードのサイズが不正です。<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_00836 = errorMgr.defineErrorInfo(
            836,
            "BUSINESS_ERROR_00836", 
            "顧客コードのサイズが不正です。");

    /**
     * 適用開始日のチェック。<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_00837 = errorMgr.defineErrorInfo(
            837,
            "BUSINESS_ERROR_00837", 
            "適用開始日が未指定です。");

    /**
     * 適用終了日のチェック。<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_00838 = errorMgr.defineErrorInfo(
            838,
            "BUSINESS_ERROR_00838", 
            "適用終了日が未指定です。");

    /**
     * 適用終了日不適切エラー。<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_00839 = errorMgr.defineErrorInfo(
            839,
            "BUSINESS_ERROR_00839", 
            "適用開始日が適用終了日より未来日付です。");

    /**
     * 適用開始日不適切エラー。<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_00840 = errorMgr.defineErrorInfo(
            840,
            "BUSINESS_ERROR_00840", 
            "申込日が適用開始日より未来日付です。");

    /**
     * 登録区分チェック。<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_00841 = errorMgr.defineErrorInfo(
            841,
            "BUSINESS_ERROR_00841", 
            "登録区分が存在しないコード値です。");

    /**
     * 利用料金のチェック。<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_00842 = errorMgr.defineErrorInfo(
            842,
            "BUSINESS_ERROR_00842", 
            "利用料金が数値以外の値です。");

    /**
     * 利用料金のチェック。<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_00843 = errorMgr.defineErrorInfo(
            843,
            "BUSINESS_ERROR_00843", 
            "利用料金のサイズが不正です。");

    /**
     * 抽選区分のチェック。<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_00844 = errorMgr.defineErrorInfo(
            844,
            "BUSINESS_ERROR_00844", 
            "抽選区分が存在しないコード値です。");

    /**
     * 申込抽選区分チェック。<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_00845 = errorMgr.defineErrorInfo(
            845,
            "BUSINESS_ERROR_00845", 
            "抽選区分は“無”でない場合、申込抽選区分が未指定です。");

    /**
     * 申込抽選区分チェック。<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_00846 = errorMgr.defineErrorInfo(
            846,
            "BUSINESS_ERROR_00846", 
            "抽選区分は“無”でない場合、申込抽選区分が存在しないコード値です。");

    /**
     * 申込日チェック。<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_00847 = errorMgr.defineErrorInfo(
            847,
            "BUSINESS_ERROR_00847", 
            "抽選区分は“無”でない場合、申込日が未指定です。");

    /**
     * 申込抽選区分のチェック。<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_00849 = errorMgr.defineErrorInfo(
            849,
            "BUSINESS_ERROR_00849", 
            "申込抽選区分が存在しないコード値です。");

    /**
     * 取引余力不足。<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_00855 = errorMgr.defineErrorInfo(
            855,
            "BUSINESS_ERROR_00855", 
            "取引余力不足エラー。（信用現引可能額不足）");

    /**
     * アップロードエラー。<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_00856 = errorMgr.defineErrorInfo(
            856,
            "BUSINESS_ERROR_00856", 
            "アップロードエラー。");

    /**
     * DIR管理者権限チェックエラー。<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_00857 = errorMgr.defineErrorInfo(
            857,
            "BUSINESS_ERROR_00857", 
            "DIR管理者権限チェックエラー。");

    /**
     * 注文有効状態がOPEN以外の場合は取消不可です。<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_00858 = errorMgr.defineErrorInfo(
            858,
            "BUSINESS_ERROR_00858", 
            "注文有効状態がOPEN以外の場合は取消不可です。");

    /**
     * データ不整合。<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_00859 = errorMgr.defineErrorInfo(
            859,
            "BUSINESS_ERROR_00859", 
            "メール情報がデータ不整合です。");

    /**
     * 送信メール区分・識別ID重複エラー。<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_00860 = errorMgr.defineErrorInfo(
            860,
            "BUSINESS_ERROR_00860", 
            "送信メール区分・識別ID重複エラー。");

    /**
     * 送信メール区分未入力エラー。<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_00862 = errorMgr.defineErrorInfo(
            862,
            "BUSINESS_ERROR_00862", 
            "送信メール区分未入力エラー。");

    /**
     * 送信メール区分文字数エラー。<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_00863 = errorMgr.defineErrorInfo(
            863,
            "BUSINESS_ERROR_00863", 
            "送信メール区分文字数エラー。");

    /**
     * 送信メール区分の値が数値ではありません。<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_00864 = errorMgr.defineErrorInfo(
            864,
            "BUSINESS_ERROR_00864", 
            "送信メール区分の値が数値ではありません。");

    /**
     * 識別ID未入力エラー。<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_00865 = errorMgr.defineErrorInfo(
            865,
            "BUSINESS_ERROR_00865", 
            "識別ID未入力エラー。");

    /**
     * 識別ID文字数エラー。<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_00866 = errorMgr.defineErrorInfo(
            866,
            "BUSINESS_ERROR_00866", 
            "識別ID文字数エラー。");

    /**
     * 識別IDの値が半角英数ではありません。<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_00867 = errorMgr.defineErrorInfo(
            867,
            "BUSINESS_ERROR_00867", 
            "識別IDの値が半角英数ではありません。");

    /**
     * メール名称文字数エラー。<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_00868 = errorMgr.defineErrorInfo(
            868,
            "BUSINESS_ERROR_00868", 
            "メール名称文字数エラー。");

    /**
     * 差出人文字数エラー。<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_00870 = errorMgr.defineErrorInfo(
            870,
            "BUSINESS_ERROR_00870", 
            "差出人文字数エラー。");

    /**
     * 件名未入力エラー。<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_00871 = errorMgr.defineErrorInfo(
            871,
            "BUSINESS_ERROR_00871", 
            "件名未入力エラー。");

    /**
     * 件名文字数エラー。<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_00872 = errorMgr.defineErrorInfo(
            872,
            "BUSINESS_ERROR_00872", 
            "件名文字数エラー。");

    /**
     * メールヘッダー、メール本文、メールフッター未入力エラー。<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_00873 = errorMgr.defineErrorInfo(
            873,
            "BUSINESS_ERROR_00873", 
            "メールヘッダー、メール本文、メールフッター未入力エラー。");

    /**
     * メールヘッダー文字数エラー。<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_00874 = errorMgr.defineErrorInfo(
            874,
            "BUSINESS_ERROR_00874", 
            "メールヘッダー文字数エラー。");

    /**
     * メール本文文字数エラー。<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_00875 = errorMgr.defineErrorInfo(
            875,
            "BUSINESS_ERROR_00875", 
            "メール本文文字数エラー。");

    /**
     * メールフッター文字数エラー。<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_00876 = errorMgr.defineErrorInfo(
            876,
            "BUSINESS_ERROR_00876", 
            "メールフッター文字数エラー。");

    /**
     * 送信先アドレス文字数エラー。<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_00877 = errorMgr.defineErrorInfo(
            877,
            "BUSINESS_ERROR_00877", 
            "送信先アドレス文字数エラー。");

    /**
     * 抽選設定が「無」の場合エラー。<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_00878 = errorMgr.defineErrorInfo(
            878,
            "BUSINESS_ERROR_00878", 
            "抽選の無いサービスは、利用申込の取消ができません。");

    /**
     * 出金日のチェック。<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_00879 = errorMgr.defineErrorInfo(
            879,
            "BUSINESS_ERROR_00879", 
            "有料サービスの場合は、出金日の指定は必須です。");

    /**
     * サービス区分の値は数値以外のチェック。<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_00882 = errorMgr.defineErrorInfo(
            882,
            "BUSINESS_ERROR_00882", 
            "サービス区分が数値以外の値です。");

    /**
     * サービス名称のチェック。<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_00883 = errorMgr.defineErrorInfo(
            883,
            "BUSINESS_ERROR_00883", 
            "サービス名称が未指定です。");

    /**
     * サービス名称チェック。<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_00884 = errorMgr.defineErrorInfo(
            884,
            "BUSINESS_ERROR_00884", 
            "サービス名称のサイズが不正です。");

    /**
     * 摘要のチェック。<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_00885 = errorMgr.defineErrorInfo(
            885,
            "BUSINESS_ERROR_00885", 
            "申込区分が“要”の場合は、摘要が未指定です。");

    /**
     * 摘要のチェック。<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_00886 = errorMgr.defineErrorInfo(
            886,
            "BUSINESS_ERROR_00886", 
            "申込区分が“不要”の場合は、摘要が指定不可です。");

    /**
     * 摘要のチェック。<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_00887 = errorMgr.defineErrorInfo(
            887,
            "BUSINESS_ERROR_00887", 
            "摘要の値に半角カナ以外の文字が含まれています。");

    /**
     * 摘要のチェック。<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_00888 = errorMgr.defineErrorInfo(
            888,
            "BUSINESS_ERROR_00888", 
            "摘要のサイズが不正です。");

    /**
     * ステータスのチェック。<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_00889 = errorMgr.defineErrorInfo(
            889,
            "BUSINESS_ERROR_00889", 
            "ステータスが未指定です。");

    /**
     * ステータスのチェック。<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_00890 = errorMgr.defineErrorInfo(
            890,
            "BUSINESS_ERROR_00890", 
            "ステータスが存在しないコード値です。");

    /**
     * 申込区分のチェック。<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_00891 = errorMgr.defineErrorInfo(
            891,
            "BUSINESS_ERROR_00891", 
            "申込区分が未指定です。");

    /**
     * 申込区分の値が違いのチェック。<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_00892 = errorMgr.defineErrorInfo(
            892,
            "BUSINESS_ERROR_00892", 
            "申込区分が存在しないコード値です。");

    /**
     * 変更顧客一覧のチェック。<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_00893 = errorMgr.defineErrorInfo(
            893,
            "BUSINESS_ERROR_00893", 
            "変更顧客一覧が未指定です。");

    /**
     * 2重登録エラー。<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_00894 = errorMgr.defineErrorInfo(
            894,
            "BUSINESS_ERROR_00894", 
            "二重登録エラー。（サービス申込登録）");

    /**
     * 抽選区分のチェック。<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_00895 = errorMgr.defineErrorInfo(
            895,
            "BUSINESS_ERROR_00895", 
            "申込が必要なサービスは、抽選区分の入力は必須です。");

    /**
     * 利用期間のチェック。<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_00897 = errorMgr.defineErrorInfo(
            897,
            "BUSINESS_ERROR_00897", 
            "利用期間の入力が不正です。");

    /**
     * 利用期間のチェック。<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_00898 = errorMgr.defineErrorInfo(
            898,
            "BUSINESS_ERROR_00898", 
            "利用期間の入力がありません。");

    /**
     * 利用期間のチェック。<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_00899 = errorMgr.defineErrorInfo(
            899,
            "BUSINESS_ERROR_00899", 
            "利用期間の桁数が0以下の値です。");

    /**
     * 銘柄コードチェック。<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_00900 = errorMgr.defineErrorInfo(
            900,
            "BUSINESS_ERROR_00900", 
            "銘柄コードが0以下の値です。");

    /**
     * 注文株数チェック。<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_00901 = errorMgr.defineErrorInfo(
            901,
            "BUSINESS_ERROR_00901", 
            "注文株数が数字以外の値です。");

    /**
     * 注文株数チェック。<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_00902 = errorMgr.defineErrorInfo(
            902,
            "BUSINESS_ERROR_00902", 
            "注文株数が0以下の値です。");

    /**
     * 注文株数チェック。<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_00903 = errorMgr.defineErrorInfo(
            903,
            "BUSINESS_ERROR_00903", 
            "注文株数のサイズが不正です。");

    /**
     * 利用期間のチェック。<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_00905 = errorMgr.defineErrorInfo(
            905,
            "BUSINESS_ERROR_00905", 
            "利用期間の桁数が制限を越えています。");

    /**
     * 利用料金のチェック。<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_00906 = errorMgr.defineErrorInfo(
            906,
            "BUSINESS_ERROR_00906", 
            "利用料金の入力がありません。");

    /**
     * 利用料金のチェック。<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_00907 = errorMgr.defineErrorInfo(
            907,
            "BUSINESS_ERROR_00907", 
            "利用料金の桁数が制限を越えています。");

    /**
     * 対象レコードなしエラー。<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_00908 = errorMgr.defineErrorInfo(
            908,
            "BUSINESS_ERROR_00908", 
            "サービス申込登録データを取得できません。");

    /**
     * 試用レコード変更不可エラー。<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_00909 = errorMgr.defineErrorInfo(
            909,
            "BUSINESS_ERROR_00909", 
            "試用レコード変更不可エラー。");

    /**
     * 試用期間のチェック。<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_00910 = errorMgr.defineErrorInfo(
            910,
            "BUSINESS_ERROR_00910", 
            "試用期間の指定に誤りがあります。");

    /**
     * 試用期間チェック。<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_00912 = errorMgr.defineErrorInfo(
            912,
            "BUSINESS_ERROR_00912", 
            "試用期間が数値以外の値です。");

    /**
     * 試用期間のチェック。<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_00913 = errorMgr.defineErrorInfo(
            913,
            "BUSINESS_ERROR_00913", 
            "試用期間が0以下の値です。");

    /**
     * 試用期間のチェック。<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_00914 = errorMgr.defineErrorInfo(
            914,
            "BUSINESS_ERROR_00914", 
            "試用期間の桁数が制限を越えています。");

    /**
     * 申込期間のチェック。<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_00916 = errorMgr.defineErrorInfo(
            916,
            "BUSINESS_ERROR_00916", 
            "申込期間の入力に誤りがあります。");

    /**
     * 申込可能期間（自）のチェック。<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_00919 = errorMgr.defineErrorInfo(
            919,
            "BUSINESS_ERROR_00919", 
            "申込可能期間（自）が数値以外の値です。");

    /**
     * 申込可能期間（至）のチェック。<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_00920 = errorMgr.defineErrorInfo(
            920,
            "BUSINESS_ERROR_00920", 
            "申込可能期間（至）が数値以外の値です。");

    /**
     * 申込可能期間（自）のチェック。<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_00921 = errorMgr.defineErrorInfo(
            921,
            "BUSINESS_ERROR_00921", 
            "申込可能期間（自）が0以下の値です。");

    /**
     * 申込可能期間（至）のチェック。<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_00922 = errorMgr.defineErrorInfo(
            922,
            "BUSINESS_ERROR_00922", 
            "申込可能期間（至）が0以下の値です。");

    /**
     * 同意書文言のチェック。<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_00923 = errorMgr.defineErrorInfo(
            923,
            "BUSINESS_ERROR_00923", 
            "申込が不要なサービスでは、同意書文言の指定はできません。");

    /**
     * サービス内容のチェック。<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_00924 = errorMgr.defineErrorInfo(
            924,
            "BUSINESS_ERROR_00924", 
            "申込が不要なサービスでは、サービス内容の指定はできません。");

    /**
     * サービス説明（URL）のチェック。<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_00925 = errorMgr.defineErrorInfo(
            925,
            "BUSINESS_ERROR_00925", 
            "申込が不要なサービスでは、サービス説明(URL)の指定はできません。");

    /**
     * メール区分（確認メール）のチェック。<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_00926 = errorMgr.defineErrorInfo(
            926,
            "BUSINESS_ERROR_00926", 
            "申込が必要なサービスでは、メール区分(確認メール)の指定は必須です。");

    /**
     * メール区分（確認メール）の値のチェック。<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_00927 = errorMgr.defineErrorInfo(
            927,
            "BUSINESS_ERROR_00927", 
            "メール区分（確認メール）が存在しないコード値です。");

    /**
     * メール区分（契約期限メール）のチェック。<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_00928 = errorMgr.defineErrorInfo(
            928,
            "BUSINESS_ERROR_00928", 
            "申込が必要なサービスでは、メール区分(契約期限メール)の指定は必須です。");

    /**
     * メール区分（契約期限メール）の値のチェック。<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_00929 = errorMgr.defineErrorInfo(
            929,
            "BUSINESS_ERROR_00929", 
            "メール区分（契約期限メール）が存在しないコード値です。");

    /**
     * メール送信日のチェック。<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_00930 = errorMgr.defineErrorInfo(
            930,
            "BUSINESS_ERROR_00930", 
            "メール送信日の指定がありません。");

    /**
     * 募集期間情報のチェック。<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_00931 = errorMgr.defineErrorInfo(
            931,
            "BUSINESS_ERROR_00931", 
            "抽選が無いサービスでは、募集期間に関する情報の指定はできません。");

    /**
     * 運用区分のチェック。<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_00932 = errorMgr.defineErrorInfo(
            932,
            "BUSINESS_ERROR_00932", 
            "運用区分が未指定です。");

    /**
     * 運用区分のチェック。<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_00933 = errorMgr.defineErrorInfo(
            933,
            "BUSINESS_ERROR_00933", 
            "運用区分が存在しないコード値です。");

    /**
     * 申込期間のチェック。<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_00934 = errorMgr.defineErrorInfo(
            934,
            "BUSINESS_ERROR_00934", 
            "申込期間の入力が不正です。");

    /**
     * 適用開始日のチェック。<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_00936 = errorMgr.defineErrorInfo(
            936,
            "BUSINESS_ERROR_00936", 
            "適用開始日の入力が不正です。");

    /**
     * 適用終了日のチェック。<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_00937 = errorMgr.defineErrorInfo(
            937,
            "BUSINESS_ERROR_00937", 
            "適用終了日の入力が不正です。");

    /**
     * 出金日のチェック。<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_00938 = errorMgr.defineErrorInfo(
            938,
            "BUSINESS_ERROR_00938", 
            "出金日の入力が不正です。");

    /**
     * 募集枠のチェック。<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_00939 = errorMgr.defineErrorInfo(
            939,
            "BUSINESS_ERROR_00939", 
            "募集枠が数値以外の値です。");

    /**
     * 募集枠のチェック。<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_00940 = errorMgr.defineErrorInfo(
            940,
            "BUSINESS_ERROR_00940", 
            "当サービスでは、募集枠の指定はできません。");

    /**
     * 募集枠のチェック。<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_00941 = errorMgr.defineErrorInfo(
            941,
            "BUSINESS_ERROR_00941", 
            "当サービスでは、募集枠の指定は必須です。");

    /**
     * 募集期間情報の利用料金のチェック。<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_00942 = errorMgr.defineErrorInfo(
            942,
            "BUSINESS_ERROR_00942", 
            "抽選が有るサービスでは、利用料金の指定は必須です。");

    /**
     * 募集期間情報の入札単位のチェック。<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_00943 = errorMgr.defineErrorInfo(
            943,
            "BUSINESS_ERROR_00943", 
            "当サービスでは、入札単位の指定はできません。");

    /**
     * 募集期間情報の入札単位のチェック。<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_00944 = errorMgr.defineErrorInfo(
            944,
            "BUSINESS_ERROR_00944", 
            "当サービスでは、入札単位の指定は必須です。");

    /**
     * 検索条件のチェック。<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_00945 = errorMgr.defineErrorInfo(
            945,
            "BUSINESS_ERROR_00945", 
            "検索条件が未指定です。");

    /**
     * 適用期間チェックエラー。<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_00946 = errorMgr.defineErrorInfo(
            946,
            "BUSINESS_ERROR_00946", 
            "適用期間チェックエラー。（サービス抽選情報チェック）");

    /**
     * 出金日、抽選日のチェック。<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_00947 = errorMgr.defineErrorInfo(
            947,
            "BUSINESS_ERROR_00947", 
            "出金日に抽選日より前の日付を指定した。");

    /**
     * 適用開始日、適用終了日のチェック。<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_00949 = errorMgr.defineErrorInfo(
            949,
            "BUSINESS_ERROR_00949", 
            "適用開始日と適用終了日の入力が不正です。");

    /**
     * 抽選日のチェック。<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_00950 = errorMgr.defineErrorInfo(
            950,
            "BUSINESS_ERROR_00950", 
            "抽選日の入力がありません。");

    /**
     * 申込期間、抽選日のチェック。<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_00951 = errorMgr.defineErrorInfo(
            951,
            "BUSINESS_ERROR_00951", 
            "申込期間と抽選日の入力が不正です。");

    /**
     * 適用開始日、抽選日のチェック。<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_00952 = errorMgr.defineErrorInfo(
            952,
            "BUSINESS_ERROR_00952", 
            "適用開始日と抽選日の入力が不正です。");

    /**
     * IDの桁数のチェック。<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_00954 = errorMgr.defineErrorInfo(
            954,
            "BUSINESS_ERROR_00954", 
            "IDのサイズが不正です。");

    /**
     * メール区分（確認メール）のチェック。<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_00956 = errorMgr.defineErrorInfo(
            956,
            "BUSINESS_ERROR_00956", 
            "メール区分（確認メール）が未指定です。");

    /**
     * 抽選情報IDのチェック。<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_00957 = errorMgr.defineErrorInfo(
            957,
            "BUSINESS_ERROR_00957", 
            "抽選情報IDが未指定です。");

    /**
     * 最高落札額のチェック。<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_00958 = errorMgr.defineErrorInfo(
            958,
            "BUSINESS_ERROR_00958", 
            "最高落札額が数値以外の値です。");

    /**
     * 最高落札額の桁数のチェック。<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_00959 = errorMgr.defineErrorInfo(
            959,
            "BUSINESS_ERROR_00959", 
            "最高落札額のサイズが不正です。");

    /**
     * 最低落札額のチェック。<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_00960 = errorMgr.defineErrorInfo(
            960,
            "BUSINESS_ERROR_00960", 
            "最低落札額が数値以外の値です。");

    /**
     * 最低落札額の桁数のチェック。<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_00961 = errorMgr.defineErrorInfo(
            961,
            "BUSINESS_ERROR_00961", 
            "最低落札額のサイズが不正です。");

    /**
     * 加重平均額のチェック。<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_00962 = errorMgr.defineErrorInfo(
            962,
            "BUSINESS_ERROR_00962", 
            "加重平均額が数値以外の値です。");

    /**
     * アップロードIDのチェック。<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_00973 = errorMgr.defineErrorInfo(
            973,
            "BUSINESS_ERROR_00973", 
            "アップロードIDが未指定です。");

    /**
     * 加重平均額の桁数のチェック。<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_00974 = errorMgr.defineErrorInfo(
            974,
            "BUSINESS_ERROR_00974", 
            "加重平均額のサイズが不正です。");

    /**
     * 最高落札額・最低落札額のチェック。<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_00975 = errorMgr.defineErrorInfo(
            975,
            "BUSINESS_ERROR_00975", 
            "最低落札額が最高落札額以上です。");

    /**
     * アップロードファ@イルのチェック。<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_00976 = errorMgr.defineErrorInfo(
            976,
            "BUSINESS_ERROR_00976", 
            "アップロードファ@イルが未指定です。");

    /**
     * 入札額のチェック。<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_00977 = errorMgr.defineErrorInfo(
            977,
            "BUSINESS_ERROR_00977", 
            "入札額が数値以外の値です。");

    /**
     * 入札額の桁数のチェック。<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_00978 = errorMgr.defineErrorInfo(
            978,
            "BUSINESS_ERROR_00978", 
            "入札額のサイズが不正です。");

    /**
     * 申込種別区分のチェック。<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_00979 = errorMgr.defineErrorInfo(
            979,
            "BUSINESS_ERROR_00979", 
            "申込種別区分が未指定です。");

    /**
     * 申込種別区分のチェック。<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_00980 = errorMgr.defineErrorInfo(
            980,
            "BUSINESS_ERROR_00980", 
            "申込種別区分が存在しないコード値です。");

    /**
     * サービスマスターデータを取得できません。<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_00982 = errorMgr.defineErrorInfo(
            982,
            "BUSINESS_ERROR_00982", 
            "サービスマスターデータを取得できません。");

    /**
     * 申込期間チェックエラー。<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_00983 = errorMgr.defineErrorInfo(
            983,
            "BUSINESS_ERROR_00983", 
            "申込期間チェックエラー。（サービス抽選情報チェック）");

    /**
     * 適用期間チェック。<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_00984 = errorMgr.defineErrorInfo(
            984,
            "BUSINESS_ERROR_00984", 
            "適用期間チェックエラー。（サービス申込登録チェック）");

    /**
     * 当機@能は、DIR管理者のみ実行できます。<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_00985 = errorMgr.defineErrorInfo(
            985,
            "BUSINESS_ERROR_00985", 
            "当機@能は、DIR管理者のみ実行できます。");

    /**
     * DIR管理者ではない場合、現在申込期間中の抽選情報の編集は不可。<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_00988 = errorMgr.defineErrorInfo(
            988,
            "BUSINESS_ERROR_00988", 
            "DIR管理者ではない場合、現在申込期間中の抽選情報の編集は不可。");

    /**
     * 過去の抽選情報の変更は不可。<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_00989 = errorMgr.defineErrorInfo(
            989,
            "BUSINESS_ERROR_00989", 
            "過去の抽選情報の変更は不可。");

    /**
     * 営業日ではない。<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_00990 = errorMgr.defineErrorInfo(
            990,
            "BUSINESS_ERROR_00990", 
            "出金日が営業日ではありません。");

    /**
     * サービス抽選情報履歴の取得できない。<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_00991 = errorMgr.defineErrorInfo(
            991,
            "BUSINESS_ERROR_00991", 
            "サービス抽選情報履歴データを取得できません。");

    /**
     * 明細行（アップロードデータ）を追加失敗。<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_00992 = errorMgr.defineErrorInfo(
            992,
            "BUSINESS_ERROR_00992", 
            "明細行（アップロードデータ）を追加失敗。");

    /**
     * 申込日設定エラー。<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_00993 = errorMgr.defineErrorInfo(
            993,
            "BUSINESS_ERROR_00993", 
            "申込日設定エラー。");

    /**
     * 申込抽選区分のチェック。<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_00995 = errorMgr.defineErrorInfo(
            995,
            "BUSINESS_ERROR_00995", 
            "申込・抽選区分の指定に誤りがあります。");

    /**
     * サービスが申込可能ではありません。<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_01000 = errorMgr.defineErrorInfo(
            1000,
            "BUSINESS_ERROR_01000", 
            "サービスが申込可能ではありません。");

    /**
     * 申込日のチェック。<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_01001 = errorMgr.defineErrorInfo(
            1001,
            "BUSINESS_ERROR_01001", 
            "抽選が無いサービスでは、申込日の指定はできません。");

    /**
     * 現在、有効なサービス登録がありません。<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_01002 = errorMgr.defineErrorInfo(
            1002,
            "BUSINESS_ERROR_01002", 
            "現在、有効なサービス登録がありません。");

    /**
     * 申込日が、抽選情報の申込期間内ではありません。<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_01003 = errorMgr.defineErrorInfo(
            1003,
            "BUSINESS_ERROR_01003", 
            "申込日が、抽選情報の申込期間内ではありません。");

    /**
     * 引数論理チェック。<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_01004 = errorMgr.defineErrorInfo(
            1004,
            "BUSINESS_ERROR_01004", 
            "引数論理チェックエラー。（注文内容エラー）");

    /**
     * 申込抽選区分のチェック。<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_01007 = errorMgr.defineErrorInfo(
            1007,
            "BUSINESS_ERROR_01007", 
            "サービス利用申込の申込・抽選区分に誤りがあります。");

    /**
     * サービス利用申込の取消が可能な申込情報がありません。<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_01009 = errorMgr.defineErrorInfo(
            1009,
            "BUSINESS_ERROR_01009", 
            "サービス利用申込の取消が可能な申込情報がありません。");

    /**
     * 申込取消不可エラー。<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_01010 = errorMgr.defineErrorInfo(
            1010,
            "BUSINESS_ERROR_01010", 
            "申込取消不可エラー。");

    /**
     * 利用不可エラー。<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_01011 = errorMgr.defineErrorInfo(
            1011,
            "BUSINESS_ERROR_01011", 
            "利用不可エラー。");

    /**
     * 既存申込チェック。<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_01012 = errorMgr.defineErrorInfo(
            1012,
            "BUSINESS_ERROR_01012", 
            "既存申込チェックエラー（申込種別区分：通常申込）。");

    /**
     * 既存申込チェック。<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_01013 = errorMgr.defineErrorInfo(
            1013,
            "BUSINESS_ERROR_01013", 
            "既存申込チェックエラー（申込種別区分：継続申込）。");

    /**
     * 既存申込チェック。<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_01015 = errorMgr.defineErrorInfo(
            1015,
            "BUSINESS_ERROR_01015", 
            "既存申込チェックエラー（申込種別区分：試用申込）。");

    /**
     * 試用申込不可エラー。<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_01016 = errorMgr.defineErrorInfo(
            1016,
            "BUSINESS_ERROR_01016", 
            "試用申込不可エラー。");

    /**
     * サービス申込登録データが既に存在する。<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_01017 = errorMgr.defineErrorInfo(
            1017,
            "BUSINESS_ERROR_01017", 
            "サービス申込登録データが既に存在する。");

    /**
     * サービス抽選情報データを取得できません。<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_01018 = errorMgr.defineErrorInfo(
            1018,
            "BUSINESS_ERROR_01018", 
            "サービス抽選情報データを取得できません。");

    /**
     * 入札額チェック。<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_01019 = errorMgr.defineErrorInfo(
            1019,
            "BUSINESS_ERROR_01019", 
            "入札額チェックエラー。");

    /**
     * アップロード区分エラー。<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_01020 = errorMgr.defineErrorInfo(
            1020,
            "BUSINESS_ERROR_01020", 
            "アップロード区分の値が不正です。");

    /**
     * 申込抽選区分エラー。<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_01022 = errorMgr.defineErrorInfo(
            1022,
            "BUSINESS_ERROR_01022", 
            "申込抽選区分エラー。");

    /**
     * 証券会社コードの値が不正です。<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_01023 = errorMgr.defineErrorInfo(
            1023,
            "BUSINESS_ERROR_01023", 
            "証券会社コードの値が不正です。");

    /**
     * 未登録顧客エラー。<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_01026 = errorMgr.defineErrorInfo(
            1026,
            "BUSINESS_ERROR_01026", 
            "未登録顧客エラー。");

    /**
     * 申込日エラー。<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_01027 = errorMgr.defineErrorInfo(
            1027,
            "BUSINESS_ERROR_01027", 
            "申込日の値が不正です。");

    /**
     * 適用開始日エラー。<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_01028 = errorMgr.defineErrorInfo(
            1028,
            "BUSINESS_ERROR_01028", 
            "適用開始日の値が不正です。");

    /**
     * 適用終了日エラー。<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_01029 = errorMgr.defineErrorInfo(
            1029,
            "BUSINESS_ERROR_01029", 
            "適用終了日の値が不正です。");

    /**
     * 利用料金未入力エラー。<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_01030 = errorMgr.defineErrorInfo(
            1030,
            "BUSINESS_ERROR_01030", 
            "利用料金未入力エラー。");

    /**
     * 出金余力エラー。<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_01031 = errorMgr.defineErrorInfo(
            1031,
            "BUSINESS_ERROR_01031", 
            "出金余力エラー。（出金余力は利用料金より小さいです）");

    /**
     * 出金日エラー。<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_01032 = errorMgr.defineErrorInfo(
            1032,
            "BUSINESS_ERROR_01032", 
            "出金日エラー。");

    /**
     * 振替可能回数チェック。<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_01033 = errorMgr.defineErrorInfo(
            1033,
            "BUSINESS_ERROR_01033", 
            "現時点での証券振替回数が、振替可能回数を超えています。");

    /**
     * 顧客存在チェック。<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_01035 = errorMgr.defineErrorInfo(
            1035,
            "BUSINESS_ERROR_01035", 
            "該当する顧客が存在しません。");

    /**
     * 表示条件設定なし。<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_01036 = errorMgr.defineErrorInfo(
            1036,
            "BUSINESS_ERROR_01036", 
            "表示条件設定が登録されていない。");

    /**
     * 該当データなし。<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_01037 = errorMgr.defineErrorInfo(
            1037,
            "BUSINESS_ERROR_01037", 
            "条件に該当するデータが存在しない。");

    /**
     * 有効/無効区分チエック。<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_01038 = errorMgr.defineErrorInfo(
            1038,
            "BUSINESS_ERROR_01038", 
            "有効／無効区分が不整合。");

    /**
     * 表示メッセージなし。<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_01039 = errorMgr.defineErrorInfo(
            1039,
            "BUSINESS_ERROR_01039", 
            "表示メッセージが存在しない。");

    /**
     * 表示内容IDチエック。<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_01040 = errorMgr.defineErrorInfo(
            1040,
            "BUSINESS_ERROR_01040", 
            "表示内容IDが未指定です。");

    /**
     * 有効/無効区分チェック。<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_01041 = errorMgr.defineErrorInfo(
            1041,
            "BUSINESS_ERROR_01041", 
            "有効/無効区分が未指定です。");

    /**
     * 顧客コードチェック。<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_01043 = errorMgr.defineErrorInfo(
            1043,
            "BUSINESS_ERROR_01043", 
            "顧客コードの値が数字以外の値です。");

    /**
     * 表示条件番号チェック。<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_01044 = errorMgr.defineErrorInfo(
            1044,
            "BUSINESS_ERROR_01044", 
            "表示条件番号が未指定です。");

    /**
     * 表示タイトルチェック。<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_01045 = errorMgr.defineErrorInfo(
            1045,
            "BUSINESS_ERROR_01045", 
            "表示タイトルの入力が不正です。");

    /**
     * 表示文章チェック。<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_01046 = errorMgr.defineErrorInfo(
            1046,
            "BUSINESS_ERROR_01046", 
            "表示文章の入力が不正です。");

    /**
     * 表示媒体チェック。<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_01047 = errorMgr.defineErrorInfo(
            1047,
            "BUSINESS_ERROR_01047", 
            "表示媒体が未指定です。");

    /**
     * 表示優先度チェック。<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_01048 = errorMgr.defineErrorInfo(
            1048,
            "BUSINESS_ERROR_01048", 
            "表示優先度チェックエラー。");

    /**
     * 表示期間(自)チェック。<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_01049 = errorMgr.defineErrorInfo(
            1049,
            "BUSINESS_ERROR_01049", 
            "表示期間(自)エラー。(存在しない日付か、現在時刻以前の日付が入力された。)");

    /**
     * 表示期間(至)チェック。<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_01050 = errorMgr.defineErrorInfo(
            1050,
            "BUSINESS_ERROR_01050", 
            "表示期間(至)エラー。(存在しない日付か、現在時刻以前の日付が入力された。)");

    /**
     * 表示期間(自～至)整合性チェック。<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_01051 = errorMgr.defineErrorInfo(
            1051,
            "BUSINESS_ERROR_01051", 
            "表示期間（自）は表示期間（至）より大きいです。");

    /**
     * 表示色チェック。<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_01052 = errorMgr.defineErrorInfo(
            1052,
            "BUSINESS_ERROR_01052", 
            "表示色チェックエラー。");

    /**
     * URLチェック。<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_01053 = errorMgr.defineErrorInfo(
            1053,
            "BUSINESS_ERROR_01053", 
            "URLチェックエラー。");

    /**
     * 表示メッセージ発生日チェック。<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_01054 = errorMgr.defineErrorInfo(
            1054,
            "BUSINESS_ERROR_01054", 
            "表示メッセージ発生日が未指定です。");

    /**
     * 返済注文数量が現在の返済可能建玉数量を超えています。<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_01055 = errorMgr.defineErrorInfo(
            1055,
            "BUSINESS_ERROR_01055", 
            "返済注文数量が現在の返済可能建玉数量を超えています。");

    /**
     * 管理者権限をチェック。<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_01056 = errorMgr.defineErrorInfo(
            1056,
            "BUSINESS_ERROR_01056", 
            "管理者権限チェックエラー。");

    /**
     * 権限エラー。<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_01057 = errorMgr.defineErrorInfo(
            1057,
            "BUSINESS_ERROR_01057", 
            "権限エラー。（サービス利用管理者サービス一覧照会処理）");

    /**
     * 明細行のチェック。<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_01058 = errorMgr.defineErrorInfo(
            1058,
            "BUSINESS_ERROR_01058", 
            "顧客ファ@イル内容エラー。");

    /**
     * 取引報告書未実施顧客エラー。<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_01059 = errorMgr.defineErrorInfo(
            1059,
            "BUSINESS_ERROR_01059", 
            "取引報告書未実施顧客エラー。");

    /**
     * 明細管理番号チェック。<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_01060 = errorMgr.defineErrorInfo(
            1060,
            "BUSINESS_ERROR_01060", 
            "明細管理番号が未指定です。");

    /**
     * 翻訳摘要名チェック。<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_01061 = errorMgr.defineErrorInfo(
            1061,
            "BUSINESS_ERROR_01061", 
            "翻訳摘要名が未指定です。");

    /**
     * 銘柄名チェック。<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_01062 = errorMgr.defineErrorInfo(
            1062,
            "BUSINESS_ERROR_01062", 
            "銘柄名が未指定です。");

    /**
     * 表示期間(自)日付エラー。<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_01065 = errorMgr.defineErrorInfo(
            1065,
            "BUSINESS_ERROR_01065", 
            "表示期間（自）日付フォーマットエラー。");

    /**
     * 表示期間(至)日付エラー。<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_01066 = errorMgr.defineErrorInfo(
            1066,
            "BUSINESS_ERROR_01066", 
            "表示期間（至）日付フォーマットエラー。");

    /**
     * 銘柄コードチェック。<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_01067 = errorMgr.defineErrorInfo(
            1067,
            "BUSINESS_ERROR_01067", 
            "銘柄コードの入力が不正です。");

    /**
     * 商品整合性エラー。<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_01068 = errorMgr.defineErrorInfo(
            1068,
            "BUSINESS_ERROR_01068", 
            "商品区分が存在しないコード値です。");

    /**
     * 条件該当取引未存在エラー。<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_01070 = errorMgr.defineErrorInfo(
            1070,
            "BUSINESS_ERROR_01070", 
            "条件該当取引未存在エラー。");

    /**
     * 数量チェック。<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_01071 = errorMgr.defineErrorInfo(
            1071,
            "BUSINESS_ERROR_01071", 
            "数量が未指定です。");

    /**
     * 当該管理者が、指定の部店を取り扱えるかをチェックする。<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_01074 = errorMgr.defineErrorInfo(
            1074,
            "BUSINESS_ERROR_01074", 
            "管理者は該当部店に対する権限がないです。");

    /**
     * 補足入力チェック。<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_01075 = errorMgr.defineErrorInfo(
            1075,
            "BUSINESS_ERROR_01075", 
            "抽選の無いサービスでは、利用期間IDは必須です。");

    /**
     * 補足入力チェック。<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_01076 = errorMgr.defineErrorInfo(
            1076,
            "BUSINESS_ERROR_01076", 
            "抽選の無いサービスでは、入札額の入力はできません。");

    /**
     * 補足入力チェック。<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_01077 = errorMgr.defineErrorInfo(
            1077,
            "BUSINESS_ERROR_01077", 
            "抽選が有るサービスでは、利用期間IDは入力できません。");

    /**
     * 受渡日チェック。<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_01079 = errorMgr.defineErrorInfo(
            1079,
            "BUSINESS_ERROR_01079", 
            "受渡日が未指定です。");

    /**
     * サービス利用顧客一覧変更照会明細一覧行を取得できません。<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_01080 = errorMgr.defineErrorInfo(
            1080,
            "BUSINESS_ERROR_01080", 
            "サービス利用顧客一覧変更照会明細一覧行を取得できません。");

    /**
     * 特定口座取引未存在エラー。<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_01081 = errorMgr.defineErrorInfo(
            1081,
            "BUSINESS_ERROR_01081", 
            "特定口座取引未存在エラー。");

    /**
     * 表示期間チェック。<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_01082 = errorMgr.defineErrorInfo(
            1082,
            "BUSINESS_ERROR_01082", 
            "表示期間が未指定です。");

    /**
     * 表示期間が未定義の値のチェック。<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_01083 = errorMgr.defineErrorInfo(
            1083,
            "BUSINESS_ERROR_01083", 
            "表示期間が存在しないコード値です。");

    /**
     * 商品コードチェック。<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_01084 = errorMgr.defineErrorInfo(
            1084,
            "BUSINESS_ERROR_01084", 
            "商品コードが未指定です。");

    /**
     * 商品コードが未定義の値のチェック。<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_01085 = errorMgr.defineErrorInfo(
            1085,
            "BUSINESS_ERROR_01085", 
            "商品コードが存在しないコード値です。");

    /**
     * 取引市場の値が存在しないコード値です。<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_01087 = errorMgr.defineErrorInfo(
            1087,
            "BUSINESS_ERROR_01087", 
            "取引市場の値が存在しないコード値です。");

    /**
     * 暗証番号のチェック。<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_01090 = errorMgr.defineErrorInfo(
            1090,
            "BUSINESS_ERROR_01090", 
            "暗証番号が未指定です。");

    /**
     * ログイン名のチェック。<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_01091 = errorMgr.defineErrorInfo(
            1091,
            "BUSINESS_ERROR_01091", 
            "ログイン名が未指定です。");

    /**
     * 指数種別の値が存在しないコード値です。<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_01092 = errorMgr.defineErrorInfo(
            1092,
            "BUSINESS_ERROR_01092", 
            "指数種別の値が存在しないコード値です。");

    /**
     * 新暗証番号１，新暗証番号２のチェック。<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_01093 = errorMgr.defineErrorInfo(
            1093,
            "BUSINESS_ERROR_01093", 
            "新暗証番号１或いは新暗証番号２が未指定です。");

    /**
     * 補助口座は先物証拠金口座の場合、売建注文が取扱不可です。<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_01094 = errorMgr.defineErrorInfo(
            1094,
            "BUSINESS_ERROR_01094", 
            "補助口座は先物証拠金口座の場合、売建注文が取扱不可です。");

    /**
     * 手数料コースのチェック。<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_01095 = errorMgr.defineErrorInfo(
            1095,
            "BUSINESS_ERROR_01095", 
            "手数料コースが未指定です。");

    /**
     * 手数料コースのコード値のチェック。<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_01096 = errorMgr.defineErrorInfo(
            1096,
            "BUSINESS_ERROR_01096", 
            "手数料コースが存在しないコード値です。");

    /**
     * 金融機@関名称が全角文字かどうかのチェック。<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_01097 = errorMgr.defineErrorInfo(
            1097,
            "BUSINESS_ERROR_01097", 
            "金融機@関名称が全角文字ではありません。");

    /**
     * 金融機@関名称の文字数のチェック。<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_01098 = errorMgr.defineErrorInfo(
            1098,
            "BUSINESS_ERROR_01098", 
            "金融機@関名称のサイズが不正です。");

    /**
     * 支店コードが半角数字かどうかのチェック。<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_01099 = errorMgr.defineErrorInfo(
            1099,
            "BUSINESS_ERROR_01099", 
            "支店コードが半角数字ではありません。");

    /**
     * 支店コードの文字数のチェック。<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_01100 = errorMgr.defineErrorInfo(
            1100,
            "BUSINESS_ERROR_01100", 
            "支店コードのサイズが不正です。");

    /**
     * 支店名のチェック。<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_01101 = errorMgr.defineErrorInfo(
            1101,
            "BUSINESS_ERROR_01101", 
            "支店名が全角文字ではありません。");

    /**
     * 支店名の文字数のチェック。<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_01102 = errorMgr.defineErrorInfo(
            1102,
            "BUSINESS_ERROR_01102", 
            "支店名のサイズが不正です。");

    /**
     * 口座種類名のチェック。<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_01103 = errorMgr.defineErrorInfo(
            1103,
            "BUSINESS_ERROR_01103", 
            "口座種類名が全角文字ではありません。");

    /**
     * 口座種類名の文字数のチェック。<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_01104 = errorMgr.defineErrorInfo(
            1104,
            "BUSINESS_ERROR_01104", 
            "口座種類名のサイズが不正です。");

    /**
     * 口座番号のチェック 。<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_01105 = errorMgr.defineErrorInfo(
            1105,
            "BUSINESS_ERROR_01105", 
            "口座番号の値が数字以外の値です。");

    /**
     * 口座番号の文字数のチェック。<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_01106 = errorMgr.defineErrorInfo(
            1106,
            "BUSINESS_ERROR_01106", 
            "口座番号のサイズが不正です。");

    /**
     * 口座名義人のチェック。<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_01107 = errorMgr.defineErrorInfo(
            1107,
            "BUSINESS_ERROR_01107", 
            "口座名義人が全角文字ではありません。");

    /**
     * 口座名義人の文字数のチェック。<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_01108 = errorMgr.defineErrorInfo(
            1108,
            "BUSINESS_ERROR_01108", 
            "口座名義人のサイズが不正です。");

    /**
     * 銘柄タイプ区分のチェック。<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_01109 = errorMgr.defineErrorInfo(
            1109,
            "BUSINESS_ERROR_01109", 
            "銘柄タイプ区分が未指定です。");

    /**
     * 銘柄タイプ区分のコード値のチェック。<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_01110 = errorMgr.defineErrorInfo(
            1110,
            "BUSINESS_ERROR_01110", 
            "銘柄タイプ区分が存在しないコード値です。");

    /**
     * 約定／未約定区分のチェック。<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_01111 = errorMgr.defineErrorInfo(
            1111,
            "BUSINESS_ERROR_01111", 
            "約定／未約定区分が未指定です。");

    /**
     * 約定／未約定区分のコード値のチェック。<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_01112 = errorMgr.defineErrorInfo(
            1112,
            "BUSINESS_ERROR_01112", 
            "約定／未約定区分が存在しないコード値です。");

    /**
     * 携帯番号が半角数字とハイフン文字（’-’）かどうかのチェック。<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_01113 = errorMgr.defineErrorInfo(
            1113,
            "BUSINESS_ERROR_01113", 
            "携帯番号が半角数字とハイフン文字（’-’）ではありません。");

    /**
     * 勤務先名称が全角文字かどうかのチェック。<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_01115 = errorMgr.defineErrorInfo(
            1115,
            "BUSINESS_ERROR_01115", 
            "勤務先名称が全角文字ではありません。");

    /**
     * 勤務先名称の文字数のチェック。<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_01116 = errorMgr.defineErrorInfo(
            1116,
            "BUSINESS_ERROR_01116", 
            "勤務先名称のサイズが不正です。");

    /**
     * 勤務先郵便番号のチェック。<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_01117 = errorMgr.defineErrorInfo(
            1117,
            "BUSINESS_ERROR_01117", 
            "勤務先郵便番号が7byte半角数字ではありません。");

    /**
     * 勤務先住所が全角文字かどうかのチェック。<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_01119 = errorMgr.defineErrorInfo(
            1119,
            "BUSINESS_ERROR_01119", 
            "勤務先住所が全角文字ではありません。");

    /**
     * 勤務先住所の文字数のチェック。<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_01120 = errorMgr.defineErrorInfo(
            1120,
            "BUSINESS_ERROR_01120", 
            "勤務先住所のサイズが不正です。");

    /**
     * 勤務先電話番号が半角数字とハイフン文字（’-’）かどうかのチェック。<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_01121 = errorMgr.defineErrorInfo(
            1121,
            "BUSINESS_ERROR_01121", 
            "勤務先電話番号が半角数字とハイフン文字（’-’）ではありません。");

    /**
     * 役職名が全角文字かどうかのチェック。<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_01123 = errorMgr.defineErrorInfo(
            1123,
            "BUSINESS_ERROR_01123", 
            "役職名が全角文字ではありません。");

    /**
     * 役職名の文字数のチェック。<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_01124 = errorMgr.defineErrorInfo(
            1124,
            "BUSINESS_ERROR_01124", 
            "役職名のサイズが不正です。");

    /**
     * Ｙ客区分のチェック。<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_01125 = errorMgr.defineErrorInfo(
            1125,
            "BUSINESS_ERROR_01125", 
            "Ｙ客区分が未指定です。");

    /**
     * Ｙ客区分のコード値のチェック。<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_01126 = errorMgr.defineErrorInfo(
            1126,
            "BUSINESS_ERROR_01126", 
            "Ｙ客区分が存在しないコード値です。");

    /**
     * 管理ロック区分のチェック。<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_01127 = errorMgr.defineErrorInfo(
            1127,
            "BUSINESS_ERROR_01127", 
            "管理ロック区分が未指定です。");

    /**
     * 管理ロック区分のコード値のチェック。<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_01128 = errorMgr.defineErrorInfo(
            1128,
            "BUSINESS_ERROR_01128", 
            "管理ロック区分が存在しないコード値です。");

    /**
     * 管理ロック解除開始日／管理ロック解除終了日のチェック。<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_01129 = errorMgr.defineErrorInfo(
            1129,
            "BUSINESS_ERROR_01129", 
            "管理ロック解除開始日と管理ロック解除終了日はいずれかが入力される時、残り一方も入力されるはずです。");

    /**
     * 管理ロック解除開始日、管理ロック解除終了日のチェック。<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_01130 = errorMgr.defineErrorInfo(
            1130,
            "BUSINESS_ERROR_01130", 
            "管理ロック解除開始日は管理ロック解除終了日より大きいです。");

    /**
     * 支店ロック区分のチェック。<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_01131 = errorMgr.defineErrorInfo(
            1131,
            "BUSINESS_ERROR_01131", 
            "支店ロック区分が未指定です。");

    /**
     * 支店ロック区分のコード値のチェック。<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_01132 = errorMgr.defineErrorInfo(
            1132,
            "BUSINESS_ERROR_01132", 
            "支店ロック区分が存在しないコード値です。");

    /**
     * 注文認可区分のチェック。<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_01133 = errorMgr.defineErrorInfo(
            1133,
            "BUSINESS_ERROR_01133", 
            "注文認可区分が未指定です。");

    /**
     * 注文認可区分のコード値のチェック。<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_01134 = errorMgr.defineErrorInfo(
            1134,
            "BUSINESS_ERROR_01134", 
            "注文認可区分が存在しないコード値です。");

    /**
     * 注文認可区分のチェック。<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_01135 = errorMgr.defineErrorInfo(
            1135,
            "BUSINESS_ERROR_01135", 
            "認可を行う際には、ロック中の状態でなければなりません。また認可中の状態にあるときには、ロック解除はできません。");

    /**
     * 登録状況区分のチェック。<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_01137 = errorMgr.defineErrorInfo(
            1137,
            "BUSINESS_ERROR_01137", 
            "登録状況区分が未指定です。");

    /**
     * 登録状況区分のコード値のチェック。<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_01138 = errorMgr.defineErrorInfo(
            1138,
            "BUSINESS_ERROR_01138", 
            "登録状況区分が存在しないコード値です。");

    /**
     * 手数料Ｎｏ．のチェック。<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_01141 = errorMgr.defineErrorInfo(
            1141,
            "BUSINESS_ERROR_01141", 
            "手数料Ｎｏ．が未指定です。");

    /**
     * 手数料Ｎｏ．が数字かどうかのチェック。<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_01142 = errorMgr.defineErrorInfo(
            1142,
            "BUSINESS_ERROR_01142", 
            "手数料Ｎｏ．が数字ではありません。");

    /**
     * 手数料Ｎｏ．のサイズのチェック。<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_01143 = errorMgr.defineErrorInfo(
            1143,
            "BUSINESS_ERROR_01143", 
            "手数料Ｎｏ．のサイズが不正です。");

    /**
     * 徴収率のチェック。<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_01144 = errorMgr.defineErrorInfo(
            1144,
            "BUSINESS_ERROR_01144", 
            "徴収率が未指定です。");

    /**
     * 徴収率が数字かどうかのチェック。<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_01145 = errorMgr.defineErrorInfo(
            1145,
            "BUSINESS_ERROR_01145", 
            "徴収率が数字ではありません。");

    /**
     * 徴収率の有効値のチェック。<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_01146 = errorMgr.defineErrorInfo(
            1146,
            "BUSINESS_ERROR_01146", 
            "徴収率の有効値が0―100の間ではありません。");

    /**
     * 開始日のチェック。<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_01149 = errorMgr.defineErrorInfo(
            1149,
            "BUSINESS_ERROR_01149", 
            "開始日が未指定です。");

    /**
     * 終了日のチェック。<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_01150 = errorMgr.defineErrorInfo(
            1150,
            "BUSINESS_ERROR_01150", 
            "終了日が未指定です。");

    /**
     * 開始日、終了日のチェック。<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_01151 = errorMgr.defineErrorInfo(
            1151,
            "BUSINESS_ERROR_01151", 
            "開始日は終了日より大きいです。");

    /**
     * 変更後メールアドレス、メールアドレス削除フラグのチェック。<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_01153 = errorMgr.defineErrorInfo(
            1153,
            "BUSINESS_ERROR_01153", 
            "メールアドレス変更の場合は、変更後メールアドレスが未指定です。");

    /**
     * 変更後メールアドレス、メールアドレス削除フラグのチェック。<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_01154 = errorMgr.defineErrorInfo(
            1154,
            "BUSINESS_ERROR_01154", 
            "メールアドレス削除の場合は、変更後メールアドレスが指定不可です。");

    /**
     * 判定結果区分のチェック。<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_01157 = errorMgr.defineErrorInfo(
            1157,
            "BUSINESS_ERROR_01157", 
            "判定結果区分が未指定です。");

    /**
     * 判定結果区分のコード値のチェック。<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_01158 = errorMgr.defineErrorInfo(
            1158,
            "BUSINESS_ERROR_01158", 
            "判定結果区分が存在しないコード値です。");

    /**
     * 検索条件項目のチェック。<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_01159 = errorMgr.defineErrorInfo(
            1159,
            "BUSINESS_ERROR_01159", 
            "顧客コード，開始日，終了日のすべてが未入力です。");

    /**
     * 検索条件項目のチェック。<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_01160 = errorMgr.defineErrorInfo(
            1160,
            "BUSINESS_ERROR_01160", 
            "開始日と終了日はいずれかが入力される時、残り一方も入力されるはずです。");

    /**
     * 変更後情報、専用振込先口座削除フラグのチェック1。<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_01162 = errorMgr.defineErrorInfo(
            1162,
            "BUSINESS_ERROR_01162", 
            "専用振込先口座変更の場合は、変更後情報が未指定です。");

    /**
     * 変更後情報、専用振込先口座削除フラグのチェック。<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_01163 = errorMgr.defineErrorInfo(
            1163,
            "BUSINESS_ERROR_01163", 
            "専用振込先口座削除の場合は、変更後情報が指定不可です。");

    /**
     * 停止情報のチェック。<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_01164 = errorMgr.defineErrorInfo(
            1164,
            "BUSINESS_ERROR_01164", 
            "停止情報が未指定です。");

    /**
     * 内部者情報が未指定です。<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_01165 = errorMgr.defineErrorInfo(
            1165,
            "BUSINESS_ERROR_01165", 
            "内部者情報が未指定です。");

    /**
     * 登録状況に変更がありません。<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_01166 = errorMgr.defineErrorInfo(
            1166,
            "BUSINESS_ERROR_01166", 
            "登録状況に変更がありません。");

    /**
     * 判定確認中のチェック。<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_01167 = errorMgr.defineErrorInfo(
            1167,
            "BUSINESS_ERROR_01167", 
            "判定確認中の場合、携帯番号・勤務先情報変更申込不可。");

    /**
     * 委託手数料コースマスタテーブルより検索エラー。<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_01168 = errorMgr.defineErrorInfo(
            1168,
            "BUSINESS_ERROR_01168", 
            "委託手数料コースマスタデータを取得できません。");

    /**
     * 携帯番号・勤務先情報変更申込がデータ不整合です。<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_01169 = errorMgr.defineErrorInfo(
            1169,
            "BUSINESS_ERROR_01169", 
            "携帯番号・勤務先情報変更申込がデータ不整合です。");

    /**
     * 専用振込先口座ファ@イル内容エラー。<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_01170 = errorMgr.defineErrorInfo(
            1170,
            "BUSINESS_ERROR_01170", 
            "専用振込先口座ファ@イル内容エラー。");

    /**
     * 管理ロック解除開始日：期間内であるかのチェック。<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_01171 = errorMgr.defineErrorInfo(
            1171,
            "BUSINESS_ERROR_01171", 
            "管理ロック解除開始日は指定期間にありません。");

    /**
     * 管理ロック解除開始日：営業日チェック。<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_01172 = errorMgr.defineErrorInfo(
            1172,
            "BUSINESS_ERROR_01172", 
            "管理ロック解除開始日は営業日ではありません。");

    /**
     * 管理ロック解除終了日：期間内であるかのチェック。<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_01173 = errorMgr.defineErrorInfo(
            1173,
            "BUSINESS_ERROR_01173", 
            "管理ロック解除終了日は指定期間にありません。");

    /**
     * 管理ロック解除終了日：営業日チェック。<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_01174 = errorMgr.defineErrorInfo(
            1174,
            "BUSINESS_ERROR_01174", 
            "管理ロック解除終了日は営業日ではありません。");

    /**
     * 管理ロック解除開始日／終了日の関連チェック。<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_01175 = errorMgr.defineErrorInfo(
            1175,
            "BUSINESS_ERROR_01175", 
            "管理ロック解除開始日と管理ロック解除終了日は月末日です。");

    /**
     * 停止情報：変更項目既存値チェック。<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_01176 = errorMgr.defineErrorInfo(
            1176,
            "BUSINESS_ERROR_01176", 
            "停止情報には、変更項目がありません。");

    /**
     * 当該顧客が無料申込可能の判定。<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_01177 = errorMgr.defineErrorInfo(
            1177,
            "BUSINESS_ERROR_01177", 
            "当該顧客が無料申込不可。");

    /**
     * 既存申込チェック。<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_01178 = errorMgr.defineErrorInfo(
            1178,
            "BUSINESS_ERROR_01178", 
            "既存申込チェックエラー。（申込種別区分が”無料申込”の場合、サービス申込登録成功の場合）");

    /**
     * 申込種別区分指定エラー。<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_01179 = errorMgr.defineErrorInfo(
            1179,
            "BUSINESS_ERROR_01179", 
            "申込種別区分指定エラー。");

    /**
     * 提供形式のチェック。<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_01180 = errorMgr.defineErrorInfo(
            1180,
            "BUSINESS_ERROR_01180", 
            "提供形式が存在しないコード値です。");

    /**
     * 手数料条件基準合計額のチェック。<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_01181 = errorMgr.defineErrorInfo(
            1181,
            "BUSINESS_ERROR_01181", 
            "手数料条件基準合計額が数値以外の値です。");

    /**
     * 手数料条件基準合計額のチェック。<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_01182 = errorMgr.defineErrorInfo(
            1182,
            "BUSINESS_ERROR_01182", 
            "サービスの無料提供条件を指定した場合は、手数料条件の合計額の入力が必要です。");

    /**
     * 手数料条件基準合計額のチェック。<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_01183 = errorMgr.defineErrorInfo(
            1183,
            "BUSINESS_ERROR_01183", 
            "サービスの無料提供条件を指定した場合は、手数料条件の合計額に1円以上の入力が必要です。");

    /**
     * 適用手数料条件のチェック。<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_01184 = errorMgr.defineErrorInfo(
            1184,
            "BUSINESS_ERROR_01184", 
            "サービスの無料提供条件を指定した場合は、適用する手数料の条件指定が必要です。");

    /**
     * 適用手数料条件のチェック。<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_01185 = errorMgr.defineErrorInfo(
            1185,
            "BUSINESS_ERROR_01185", 
            "サービスの無料提供条件を指定しない場合は、適用する手数料の条件指定はできません。");

    /**
     * 適用手数料条件の商品分類区分のチェック。<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_01186 = errorMgr.defineErrorInfo(
            1186,
            "BUSINESS_ERROR_01186", 
            "適用手数料条件の商品分類区分が未指定です。");

    /**
     * 余力残高エラー。<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_01187 = errorMgr.defineErrorInfo(
            1187,
            "BUSINESS_ERROR_01187", 
            "余力残高エラー。");

    /**
     * 訂正入力値が妥当であるかをチェック。<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_01188 = errorMgr.defineErrorInfo(
            1188,
            "BUSINESS_ERROR_01188", 
            "この市場では現在、数量・注文単価・執行条件の複数項目が同時訂正不可です。");

    /**
     * 扱者コード登録済みチェック。<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_01190 = errorMgr.defineErrorInfo(
            1190,
            "BUSINESS_ERROR_01190", 
            "指定の扱者コードが既に登録済みです。");

    /**
     * 扱者が存在しない場合のエラー。<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_01191 = errorMgr.defineErrorInfo(
            1191,
            "BUSINESS_ERROR_01191", 
            "扱者が存在しない。");

    /**
     * オペレータコードのチェック。<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_01192 = errorMgr.defineErrorInfo(
            1192,
            "BUSINESS_ERROR_01192", 
            "オペレータコードが未指定です。");

    /**
     * オペレータ名のチェック。<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_01193 = errorMgr.defineErrorInfo(
            1193,
            "BUSINESS_ERROR_01193", 
            "オペレータ名が未指定です。");

    /**
     * オペレータ名のチェック。<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_01194 = errorMgr.defineErrorInfo(
            1194,
            "BUSINESS_ERROR_01194", 
            "オペレータ名のサイズが不正です。");

    /**
     * 代行注文可能区分のチェック。<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_01195 = errorMgr.defineErrorInfo(
            1195,
            "BUSINESS_ERROR_01195", 
            "代行注文可能区分が未指定です。");

    /**
     * 代行注文可能区分のチェック。<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_01196 = errorMgr.defineErrorInfo(
            1196,
            "BUSINESS_ERROR_01196", 
            "代行注文可能区分が存在しないコード値です。");

    /**
     * 所属コードのチェック。<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_01197 = errorMgr.defineErrorInfo(
            1197,
            "BUSINESS_ERROR_01197", 
            "所属コードが数字以外の値です。");

    /**
     * 所属コードのチェック。<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_01198 = errorMgr.defineErrorInfo(
            1198,
            "BUSINESS_ERROR_01198", 
            "所属コードのサイズが不正です。");

    /**
     * 権限レベルコードのチェック。<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_01201 = errorMgr.defineErrorInfo(
            1201,
            "BUSINESS_ERROR_01201", 
            "権限レベルコードが未指定です。");

    /**
     * 権限レベルコードのチェック。<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_01202 = errorMgr.defineErrorInfo(
            1202,
            "BUSINESS_ERROR_01202", 
            "権限レベルコードのサイズが不正です。");

    /**
     * 権限レベルコードのチェック。<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_01203 = errorMgr.defineErrorInfo(
            1203,
            "BUSINESS_ERROR_01203", 
            "権限レベルコードが数字以外の値です。");

    /**
     * 権限レベル名称のチェック。<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_01204 = errorMgr.defineErrorInfo(
            1204,
            "BUSINESS_ERROR_01204", 
            "権限レベル名称が未指定です。");

    /**
     * 権限レベル名称のチェック。<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_01205 = errorMgr.defineErrorInfo(
            1205,
            "BUSINESS_ERROR_01205", 
            "権限レベル名称のサイズが不正です。");

    /**
     * DIR管理者フラグ、権限レベルコードのチェック。<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_01206 = errorMgr.defineErrorInfo(
            1206,
            "BUSINESS_ERROR_01206", 
            "DIR管理者の場合は、権限レベルコードは“9”で始まる文字列でなければならないです。");

    /**
     * DIR管理者フラグ、権限レベルコードのチェック。<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_01207 = errorMgr.defineErrorInfo(
            1207,
            "BUSINESS_ERROR_01207", 
            "通常管理者の場合は、“9”で始まる権限レベルコードは使用できない。");

    /**
     * ＤＩＲ管理者チェック。<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_01208 = errorMgr.defineErrorInfo(
            1208,
            "BUSINESS_ERROR_01208", 
            "通常管理者の場合は、“9”で始まる権限レベルコードは使用できないです。");

    /**
     * 管理者名のチェック。<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_01209 = errorMgr.defineErrorInfo(
            1209,
            "BUSINESS_ERROR_01209", 
            "管理者名のサイズが不正です。");

    /**
     * エラー回数のチェック。<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_01211 = errorMgr.defineErrorInfo(
            1211,
            "BUSINESS_ERROR_01211", 
            "エラー回数が数字以外の値です。");

    /**
     * エラー回数のチェック。<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_01212 = errorMgr.defineErrorInfo(
            1212,
            "BUSINESS_ERROR_01212", 
            "エラー回数の有効値が0―9の範囲内ではありません。");

    /**
     * 機@能カテゴリコード一覧のチェック。<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_01214 = errorMgr.defineErrorInfo(
            1214,
            "BUSINESS_ERROR_01214", 
            "機@能カテゴリコード一覧が未指定です。");

    /**
     * 管理者コードのチェック。<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_01215 = errorMgr.defineErrorInfo(
            1215,
            "BUSINESS_ERROR_01215", 
            "管理者コードが未指定です。");

    /**
     * 管理者タイプ情報のチェック。<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_01218 = errorMgr.defineErrorInfo(
            1218,
            "BUSINESS_ERROR_01218", 
            "管理者タイプ情報が未指定です。");

    /**
     * 管理者名のチェック。<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_01219 = errorMgr.defineErrorInfo(
            1219,
            "BUSINESS_ERROR_01219", 
            "管理者名が未指定です。");

    /**
     * 機@能カテゴリコードが未指定です。<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_01220 = errorMgr.defineErrorInfo(
            1220,
            "BUSINESS_ERROR_01220", 
            "機@能カテゴリコードが未指定です。");

    /**
     * 管理者コードに該当する管理者を取得できません。<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_01222 = errorMgr.defineErrorInfo(
            1222,
            "BUSINESS_ERROR_01222", 
            "管理者コードに該当する管理者を取得できません。");

    /**
     * 対象データがDIR管理者の場合のエラー。<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_01223 = errorMgr.defineErrorInfo(
            1223,
            "BUSINESS_ERROR_01223", 
            "対象データがDIR管理者の場合は、該当する処理不可。");

    /**
     * 通常管理者はDIR管理者を登録できません。<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_01225 = errorMgr.defineErrorInfo(
            1225,
            "BUSINESS_ERROR_01225", 
            "通常管理者はDIR管理者を登録できません。");

    /**
     * 管理者タイプデータが既に存在する。<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_01226 = errorMgr.defineErrorInfo(
            1226,
            "BUSINESS_ERROR_01226", 
            "管理者タイプデータが既に存在する。");

    /**
     * ログイン中の管理者の場合。<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_01227 = errorMgr.defineErrorInfo(
            1227,
            "BUSINESS_ERROR_01227", 
            "管理者は自分自身に対する変更、または削除が不可です。");

    /**
     * 管理者コードが既に存在する場合のエラー。<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_01229 = errorMgr.defineErrorInfo(
            1229,
            "BUSINESS_ERROR_01229", 
            "指定の管理者コードが既に登録済みです。");

    /**
     * 指定方法@（買付）エラー（投信銘柄条件登録審査）。<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_01231 = errorMgr.defineErrorInfo(
            1231,
            "BUSINESS_ERROR_01231", 
            "指定方法@（買付）エラー（投信銘柄条件登録審査）。");

    /**
     * 指定方法@（解約）エラー（投信銘柄条件登録審査）。<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_01232 = errorMgr.defineErrorInfo(
            1232,
            "BUSINESS_ERROR_01232", 
            "指定方法@（解約）エラー（投信銘柄条件登録審査）。");

    /**
     * 指定方法@（乗換）エラー（投信銘柄条件登録審査）。<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_01233 = errorMgr.defineErrorInfo(
            1233,
            "BUSINESS_ERROR_01233", 
            "指定方法@（乗換）エラー（投信銘柄条件登録審査）。");

    /**
     * 訂正可能期間が、ブックビルディング開始前までの項目のチェック。<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_01234 = errorMgr.defineErrorInfo(
            1234,
            "BUSINESS_ERROR_01234", 
            "「成行可能」が訂正されています。");

    /**
     * 銘柄更新情報が未指定です。<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_01235 = errorMgr.defineErrorInfo(
            1235,
            "BUSINESS_ERROR_01235", 
            "銘柄更新情報が未指定です。");

    /**
     * 銘柄更新情報の要素数が０です。<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_01236 = errorMgr.defineErrorInfo(
            1236,
            "BUSINESS_ERROR_01236", 
            "銘柄更新情報の要素数が０です。");

    /**
     * 銘柄更新情報の銘柄コードが未指定です。<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_01237 = errorMgr.defineErrorInfo(
            1237,
            "BUSINESS_ERROR_01237", 
            "銘柄更新情報の銘柄コードが未指定です。");

    /**
     * 銘柄更新情報の買付可能区分（翌日）が未指定です。<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_01238 = errorMgr.defineErrorInfo(
            1238,
            "BUSINESS_ERROR_01238", 
            "銘柄更新情報の買付可能区分（翌日）が未指定です。");

    /**
     * 銘柄更新情報の解約可能区分（翌日）が未指定です。<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_01239 = errorMgr.defineErrorInfo(
            1239,
            "BUSINESS_ERROR_01239", 
            "銘柄更新情報の解約可能区分（翌日）が未指定です。");

    /**
     * 銘柄更新情報の買付開始日は銘柄更新情報の買付終了日以上です。<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_01240 = errorMgr.defineErrorInfo(
            1240,
            "BUSINESS_ERROR_01240", 
            "銘柄更新情報の買付開始日は銘柄更新情報の買付終了日以上です。");

    /**
     * 銘柄更新情報の解約開始日は銘柄更新情報の解約終了日以上です。<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_01241 = errorMgr.defineErrorInfo(
            1241,
            "BUSINESS_ERROR_01241", 
            "銘柄更新情報の解約開始日は銘柄更新情報の解約終了日以上です。");

    /**
     * オペレーション日付が未指定です。<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_01242 = errorMgr.defineErrorInfo(
            1242,
            "BUSINESS_ERROR_01242", 
            "オペレーション日付が未指定です。");

    /**
     * 投信銘柄カテゴリーコードが未指定です。<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_01243 = errorMgr.defineErrorInfo(
            1243,
            "BUSINESS_ERROR_01243", 
            "投信銘柄カテゴリーコードが未指定です。");

    /**
     * 投信銘柄カテゴリーコードの桁数が制限を越えています。<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_01244 = errorMgr.defineErrorInfo(
            1244,
            "BUSINESS_ERROR_01244", 
            "投信銘柄カテゴリーコードの桁数が制限を越えています。");

    /**
     * 投信銘柄カテゴリー名称が未指定です。<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_01245 = errorMgr.defineErrorInfo(
            1245,
            "BUSINESS_ERROR_01245", 
            "投信銘柄カテゴリー名称が未指定です。");

    /**
     * 投信銘柄カテゴリー名の値に半角カナ文字が含まれている。<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_01246 = errorMgr.defineErrorInfo(
            1246,
            "BUSINESS_ERROR_01246", 
            "投信銘柄カテゴリー名の値に半角カナ文字が含まれています。");

    /**
     * 投信銘柄カテゴリー名称の値が制限を越えています。<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_01247 = errorMgr.defineErrorInfo(
            1247,
            "BUSINESS_ERROR_01247", 
            "投信銘柄カテゴリー名称の値が制限を越えています。");

    /**
     * 親カテゴリーコードの桁数が制限を越えています。<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_01248 = errorMgr.defineErrorInfo(
            1248,
            "BUSINESS_ERROR_01248", 
            "親カテゴリーコードの桁数が制限を越えています。");

    /**
     * 処理区分が未指定です。<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_01249 = errorMgr.defineErrorInfo(
            1249,
            "BUSINESS_ERROR_01249", 
            "処理区分が未指定です。");

    /**
     * 処理区分の値が存在しないコード値です。<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_01250 = errorMgr.defineErrorInfo(
            1250,
            "BUSINESS_ERROR_01250", 
            "処理区分の値が存在しないコード値です。");

    /**
     * 銘柄情報が未指定です。<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_01251 = errorMgr.defineErrorInfo(
            1251,
            "BUSINESS_ERROR_01251", 
            "銘柄情報が未指定です。");

    /**
     * 銘柄コードの指定がありません。<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_01252 = errorMgr.defineErrorInfo(
            1252,
            "BUSINESS_ERROR_01252", 
            "銘柄コードの指定がありません。");

    /**
     * 銘柄名（英名）の値に全角文字が含まれています。<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_01253 = errorMgr.defineErrorInfo(
            1253,
            "BUSINESS_ERROR_01253", 
            "銘柄名（英名）の値に全角文字が含まれています。");

    /**
     * 指定方法@（買付）の指定に誤りがあります。<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_01254 = errorMgr.defineErrorInfo(
            1254,
            "BUSINESS_ERROR_01254", 
            "指定方法@（買付）の指定に誤りがあります。");

    /**
     * 指定方法@（解約）の指定に誤りがあります。<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_01255 = errorMgr.defineErrorInfo(
            1255,
            "BUSINESS_ERROR_01255", 
            "指定方法@（解約）の指定に誤りがあります。");

    /**
     * 指定方法@（乗換）の指定に誤りがあります。<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_01256 = errorMgr.defineErrorInfo(
            1256,
            "BUSINESS_ERROR_01256", 
            "指定方法@（乗換）の指定に誤りがあります。");

    /**
     * 銘柄情報の全ての属性が未指定です。（銘柄コード以外）。<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_01257 = errorMgr.defineErrorInfo(
            1257,
            "BUSINESS_ERROR_01257", 
            "銘柄情報の全ての属性が未指定です。（銘柄コード以外）");

    /**
     * 買付可能区分（当日発注分）の指定に誤りがあります。<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_01258 = errorMgr.defineErrorInfo(
            1258,
            "BUSINESS_ERROR_01258", 
            "買付可能区分（当日発注分）の指定に誤りがあります。");

    /**
     * 解約可能区分（当日発注分）の指定に誤りがあります。<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_01259 = errorMgr.defineErrorInfo(
            1259,
            "BUSINESS_ERROR_01259", 
            "解約可能区分（当日発注分）の指定に誤りがあります。");

    /**
     * 乗換可能区分（当日発注分）の指定に誤りがあります。<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_01260 = errorMgr.defineErrorInfo(
            1260,
            "BUSINESS_ERROR_01260", 
            "乗換可能区分（当日発注分）の指定に誤りがあります。");

    /**
     * 買付可能区分（翌日発注分）の指定に誤りがあります。<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_01261 = errorMgr.defineErrorInfo(
            1261,
            "BUSINESS_ERROR_01261", 
            "買付可能区分（翌日発注分）の指定に誤りがあります。");

    /**
     * 解約可能区分（翌日発注分）の指定に誤りがあります。<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_01262 = errorMgr.defineErrorInfo(
            1262,
            "BUSINESS_ERROR_01262", 
            "解約可能区分（翌日発注分）の指定に誤りがあります。");

    /**
     * 乗換可能区分（翌日発注分）の指定に誤りがあります。<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_01263 = errorMgr.defineErrorInfo(
            1263,
            "BUSINESS_ERROR_01263", 
            "乗換可能区分（翌日発注分）の指定に誤りがあります。");

    /**
     * 注文受付停止開始時間（平日）が数字以外の値です。<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_01264 = errorMgr.defineErrorInfo(
            1264,
            "BUSINESS_ERROR_01264", 
            "注文受付停止開始時間（平日）が数字以外の値です。");

    /**
     * 注文受付停止終了時間（平日）が数字以外の値です。<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_01265 = errorMgr.defineErrorInfo(
            1265,
            "BUSINESS_ERROR_01265", 
            "注文受付停止終了時間（平日）が数字以外の値です。");

    /**
     * 注文受付停止開始時間（平日）が注文受付停止終了時間（平日）以上です。<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_01266 = errorMgr.defineErrorInfo(
            1266,
            "BUSINESS_ERROR_01266", 
            "注文受付停止開始時間（平日）が注文受付停止終了時間（平日）以上です。");

    /**
     * 注文受付停止終了時間（平日）の入力値が不正です。<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_01267 = errorMgr.defineErrorInfo(
            1267,
            "BUSINESS_ERROR_01267", 
            "注文受付停止終了時間（平日）の入力値が不正です。");

    /**
     * 注文受付停止開始時間（半日）が数字以外の値です。<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_01268 = errorMgr.defineErrorInfo(
            1268,
            "BUSINESS_ERROR_01268", 
            "注文受付停止開始時間（半日）が数字以外の値です。");

    /**
     * 注文受付停止終了時間（半日）が数字以外の値です。<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_01269 = errorMgr.defineErrorInfo(
            1269,
            "BUSINESS_ERROR_01269", 
            "注文受付停止終了時間（半日）が数字以外の値です。");

    /**
     * 注文受付停止開始時間（半日）が注文受付停止終了時間（半日）以上です。<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_01270 = errorMgr.defineErrorInfo(
            1270,
            "BUSINESS_ERROR_01270", 
            "注文受付停止開始時間（半日）が注文受付停止終了時間（半日）以上です。");

    /**
     * 注文受付停止終了時間（半日）の入力値が不正です。<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_01271 = errorMgr.defineErrorInfo(
            1271,
            "BUSINESS_ERROR_01271", 
            "注文受付停止終了時間（半日）の入力値が不正です。");

    /**
     * オペレーション日付が指定されていません。<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_01272 = errorMgr.defineErrorInfo(
            1272,
            "BUSINESS_ERROR_01272", 
            "オペレーション日付が指定されていません。");

    /**
     * 銘柄表示順序更新値一覧が未指定です。<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_01273 = errorMgr.defineErrorInfo(
            1273,
            "BUSINESS_ERROR_01273", 
            "銘柄表示順序更新値一覧が未指定です。");

    /**
     * 銘柄表示順序更新値一覧の要素数が０です。<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_01274 = errorMgr.defineErrorInfo(
            1274,
            "BUSINESS_ERROR_01274", 
            "銘柄表示順序更新値一覧の要素数が０です。");

    /**
     * 銘柄表示順序更新値の表示順が未指定です。<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_01275 = errorMgr.defineErrorInfo(
            1275,
            "BUSINESS_ERROR_01275", 
            "銘柄表示順序更新値の表示順が未指定です。");

    /**
     * 銘柄表示順序更新値の表示順が数値以外の値です。<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_01276 = errorMgr.defineErrorInfo(
            1276,
            "BUSINESS_ERROR_01276", 
            "銘柄表示順序更新値の表示順が数値以外の値です。");

    /**
     * 銘柄表示順序更新値の銘柄コードが未指定です。<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_01277 = errorMgr.defineErrorInfo(
            1277,
            "BUSINESS_ERROR_01277", 
            "銘柄表示順序更新値の銘柄コードが未指定です。");

    /**
     * カテゴリーコード重複エラー。<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_01278 = errorMgr.defineErrorInfo(
            1278,
            "BUSINESS_ERROR_01278", 
            "カテゴリーコード重複エラー。");

    /**
     * 検索結果なし。<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_01279 = errorMgr.defineErrorInfo(
            1279,
            "BUSINESS_ERROR_01279", 
            "検索結果なし。");

    /**
     * 削除と口座開設完了メールは同時に指定できない。<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_01280 = errorMgr.defineErrorInfo(
            1280,
            "BUSINESS_ERROR_01280", 
            "削除と口座開設完了メールは同時に指定できない。");

    /**
     * 親カテゴリーコードと投信銘柄カテゴリーコードは同じです。<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_01281 = errorMgr.defineErrorInfo(
            1281,
            "BUSINESS_ERROR_01281", 
            "親カテゴリーコードと投信銘柄カテゴリーコードは同じです。");

    /**
     * 登録中の管理者が使用している管理者タイプです。<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_01282 = errorMgr.defineErrorInfo(
            1282,
            "BUSINESS_ERROR_01282", 
            "登録中の管理者が使用している管理者タイプです。");

    /**
     * 手数料変更申込顧客ダウンロード機@能を取り扱っていない証券会社。<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_01283 = errorMgr.defineErrorInfo(
            1283,
            "BUSINESS_ERROR_01283", 
            "手数料変更申込顧客ダウンロード機@能を取り扱っていない証券会社。");

    /**
     * DIR管理者フラグが変更されている場合。<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_01284 = errorMgr.defineErrorInfo(
            1284,
            "BUSINESS_ERROR_01284", 
            "DIR管理者フラグは変更できない。");

    /**
     * ログイン中管理者の権限レベルが指定されている場合。<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_01285 = errorMgr.defineErrorInfo(
            1285,
            "BUSINESS_ERROR_01285", 
            "オペレータの権限レベルは変更できない。");

    /**
     * 入力された管理者タイプがDIR管理者の場合。<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_01286 = errorMgr.defineErrorInfo(
            1286,
            "BUSINESS_ERROR_01286", 
            "通常管理者は、DIR管理者を変更できない。");

    /**
     * 利用期間、利用料金が未入力です。<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_01287 = errorMgr.defineErrorInfo(
            1287,
            "BUSINESS_ERROR_01287", 
            "利用期間、利用料金が未入力です。");

    /**
     * 利用期間、利用料金の指定に誤りがあります。<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_01288 = errorMgr.defineErrorInfo(
            1288,
            "BUSINESS_ERROR_01288", 
            "利用期間、利用料金の指定に誤りがあります。");

    /**
     * 申込期間等の情報の指定に誤りがあります。<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_01289 = errorMgr.defineErrorInfo(
            1289,
            "BUSINESS_ERROR_01289", 
            "申込期間等の情報の指定に誤りがあります。");

    /**
     * ステータスが「停止中」ではない、かつ抽選区分が「有」の場合、募集期間情報の全ての無効区分が「無効」です。<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_01290 = errorMgr.defineErrorInfo(
            1290,
            "BUSINESS_ERROR_01290", 
            "ステータスが「停止中」ではない、かつ抽選区分が「有」の場合、募集期間情報の全ての無効区分が「無効」です。");

    /**
     * 識別コードプレフィクス一覧の指定なし。<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_01291 = errorMgr.defineErrorInfo(
            1291,
            "BUSINESS_ERROR_01291", 
            "識別コードプレフィクス一覧の指定なし。");

    /**
     * WEBBROKER3取扱状況の値が存在しないコード値です。<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_01292 = errorMgr.defineErrorInfo(
            1292,
            "BUSINESS_ERROR_01292", 
            "WEBBROKER3取扱状況の値が存在しないコード値です。");

    /**
     * 最大階層オーバーエラー。<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_01293 = errorMgr.defineErrorInfo(
            1293,
            "BUSINESS_ERROR_01293", 
            "最大階層オーバーエラー。");

    /**
     * 証拠金口座未開設。<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_01294 = errorMgr.defineErrorInfo(
            1294,
            "BUSINESS_ERROR_01294", 
            "証拠金口座未開設。");

    /**
     * 商品タイプの値が存在しないコード値です。<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_01295 = errorMgr.defineErrorInfo(
            1295,
            "BUSINESS_ERROR_01295", 
            "商品タイプの値が存在しないコード値です。");

    /**
     * 預り区分の値が存在しないコード値です。<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_01297 = errorMgr.defineErrorInfo(
            1297,
            "BUSINESS_ERROR_01297", 
            "預り区分の値が存在しないコード値です。");

    /**
     * 振替数量が未指定です。<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_01298 = errorMgr.defineErrorInfo(
            1298,
            "BUSINESS_ERROR_01298", 
            "振替数量が未指定です。");

    /**
     * 振替数量の値が0以下です。<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_01299 = errorMgr.defineErrorInfo(
            1299,
            "BUSINESS_ERROR_01299", 
            "振替数量の値が0以下です。");

    /**
     * 振替数量のサイズが不正です。<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_01300 = errorMgr.defineErrorInfo(
            1300,
            "BUSINESS_ERROR_01300", 
            "振替数量のサイズが不正です。");

    /**
     * 振替数量が数字以外の値です。<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_01301 = errorMgr.defineErrorInfo(
            1301,
            "BUSINESS_ERROR_01301", 
            "振替数量が数字以外の値です。");

    /**
     * 暗証番号の値に全角文字が含まれています。<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_01302 = errorMgr.defineErrorInfo(
            1302,
            "BUSINESS_ERROR_01302", 
            "暗証番号の値に全角文字が含まれています。");

    /**
     * 口座区分の値が存在しないコード値です。<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_01303 = errorMgr.defineErrorInfo(
            1303,
            "BUSINESS_ERROR_01303", 
            "口座区分の値が存在しないコード値です。");

    /**
     * 振替数量は、振替可能数量より大きいです。<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_01305 = errorMgr.defineErrorInfo(
            1305,
            "BUSINESS_ERROR_01305", 
            "振替数量は、振替可能数量より大きいです。");

    /**
     * 取引余力チェックエラー。<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_01306 = errorMgr.defineErrorInfo(
            1306,
            "BUSINESS_ERROR_01306", 
            "取引余力チェックエラー。");

    /**
     * 株式取引銘柄.上場区分チェック・エラー。<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_01307 = errorMgr.defineErrorInfo(
            1307,
            "BUSINESS_ERROR_01307", 
            "当該銘柄の上場区分が「非上場」です。");

    /**
     * 必須項目の値が入力されていません。追加文字列：[項目名]。<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_01309 = errorMgr.defineErrorInfo(
            1309,
            "BUSINESS_ERROR_01309", 
            "必須項目の値が入力されていません。");

    /**
     * 項目長さが有効な範囲内ではありません。追加文字列：[項目名]。<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_01310 = errorMgr.defineErrorInfo(
            1310,
            "BUSINESS_ERROR_01310", 
            "項目長さが有効な範囲内ではありません。");

    /**
     * チェック対象項目の値が存在しないコード値です。追加文字列：[項目名]。<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_01311 = errorMgr.defineErrorInfo(
            1311,
            "BUSINESS_ERROR_01311", 
            "チェック対象項目の値が存在しないコード値です。");

    /**
     * 項目が有効な値ではありません。追加文字列：[項目名]。<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_01312 = errorMgr.defineErrorInfo(
            1312,
            "BUSINESS_ERROR_01312", 
            "項目が有効な値ではありません。");

    /**
     * 顧客が重複で登録されています。<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_01313 = errorMgr.defineErrorInfo(
            1313,
            "BUSINESS_ERROR_01313", 
            "顧客が重複で登録されています。");

    /**
     * 金融機@関が存在しない。<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_01314 = errorMgr.defineErrorInfo(
            1314,
            "BUSINESS_ERROR_01314", 
            "金融機@関が存在しない。");

    /**
     * 口座開設見込客が更新不可です。<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_01315 = errorMgr.defineErrorInfo(
            1315,
            "BUSINESS_ERROR_01315", 
            "口座開設見込客が更新不可です。");

    /**
     * 口座開設見込客が伝票作成不可です。<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_01316 = errorMgr.defineErrorInfo(
            1316,
            "BUSINESS_ERROR_01316", 
            "口座開設見込客が伝票作成不可です。");

    /**
     * 不備項目が未完了です。<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_01317 = errorMgr.defineErrorInfo(
            1317,
            "BUSINESS_ERROR_01317", 
            "不備項目が未完了です。");

    /**
     * 口座開設見込客既存データが存在しない。<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_01318 = errorMgr.defineErrorInfo(
            1318,
            "BUSINESS_ERROR_01318", 
            "口座開設見込客既存データが存在しない。");

    /**
     * 口座開設見込客が取消不可です。<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_01319 = errorMgr.defineErrorInfo(
            1319,
            "BUSINESS_ERROR_01319", 
            "口座開設見込客が取消不可です。");

    /**
     * 照会時のステータスと現在のステータスが不一致です。<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_01320 = errorMgr.defineErrorInfo(
            1320,
            "BUSINESS_ERROR_01320", 
            "照会時のステータスと現在のステータスが不一致です。");

    /**
     * 該当行が存在しない。<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_01321 = errorMgr.defineErrorInfo(
            1321,
            "BUSINESS_ERROR_01321", 
            "該当行が存在しない。");

    /**
     * 部店コード配列と顧客コード配列で、要素数が違って入れてます。<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_01322 = errorMgr.defineErrorInfo(
            1322,
            "BUSINESS_ERROR_01322", 
            "部店コード配列と顧客コード配列で、要素数が違って入れてます。");

    /**
     * 表示行数が未指定です。<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_01323 = errorMgr.defineErrorInfo(
            1323,
            "BUSINESS_ERROR_01323", 
            "表示行数が未指定です。");

    /**
     * 表示行数の値が数字以外の値です。<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_01324 = errorMgr.defineErrorInfo(
            1324,
            "BUSINESS_ERROR_01324", 
            "表示行数の値が数字以外の値です。");

    /**
     * 表示行数の値がマイナス値です。<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_01325 = errorMgr.defineErrorInfo(
            1325,
            "BUSINESS_ERROR_01325", 
            "表示行数の値がマイナス値です。");

    /**
     * 資料請求日（自）は資料請求日（至）より大きいです。<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_01326 = errorMgr.defineErrorInfo(
            1326,
            "BUSINESS_ERROR_01326", 
            "資料請求日（自）は資料請求日（至）より大きいです。");

    /**
     * SONAR送信日（自）はSONAR送信日（至）より大きいです。<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_01327 = errorMgr.defineErrorInfo(
            1327,
            "BUSINESS_ERROR_01327", 
            "SONAR送信日（自）はSONAR送信日（至）より大きいです。");

    /**
     * 口座開設日（自）は口座開設日（至）より大きいです。<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_01328 = errorMgr.defineErrorInfo(
            1328,
            "BUSINESS_ERROR_01328", 
            "口座開設日（自）は口座開設日（至）より大きいです。");

    /**
     * 顧客コード（自）の値が数字以外の値です。<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_01330 = errorMgr.defineErrorInfo(
            1330,
            "BUSINESS_ERROR_01330", 
            "顧客コード（自）の値が数字以外の値です。");

    /**
     * 顧客コード（至）の値が数字以外の値です。<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_01331 = errorMgr.defineErrorInfo(
            1331,
            "BUSINESS_ERROR_01331", 
            "顧客コード（至）の値が数字以外の値です。");

    /**
     * 識別コード，顧客コードの両方が未入力の場合。<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_01332 = errorMgr.defineErrorInfo(
            1332,
            "BUSINESS_ERROR_01332", 
            "識別コード、顧客コードの両方が未指定です。");

    /**
     * 識別コード（自）は識別コード（至）より大きいです。<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_01333 = errorMgr.defineErrorInfo(
            1333,
            "BUSINESS_ERROR_01333", 
            "識別コード（自）は識別コード（至）より大きいです。");

    /**
     * 識別コード（自）の値が数字以外の値です。<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_01334 = errorMgr.defineErrorInfo(
            1334,
            "BUSINESS_ERROR_01334", 
            "識別コード（自）の値が数字以外の値です。");

    /**
     * 識別コード（至）の値が数字以外の値です。<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_01335 = errorMgr.defineErrorInfo(
            1335,
            "BUSINESS_ERROR_01335", 
            "識別コード（至）の値が数字以外の値です。");

    /**
     * 口座開設申込情報が未指定です。<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_01336 = errorMgr.defineErrorInfo(
            1336,
            "BUSINESS_ERROR_01336", 
            "口座開設申込情報が未指定です。");

    /**
     * 伝票作成情報が未指定です。<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_01337 = errorMgr.defineErrorInfo(
            1337,
            "BUSINESS_ERROR_01337", 
            "伝票作成情報が未指定です。");

    /**
     * 口座開設申込情報の証券会社コードが未指定です。<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_01338 = errorMgr.defineErrorInfo(
            1338,
            "BUSINESS_ERROR_01338", 
            "口座開設申込情報の証券会社コードが未指定です。");

    /**
     * 口座開設申込情報の部店コードが未指定です。<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_01339 = errorMgr.defineErrorInfo(
            1339,
            "BUSINESS_ERROR_01339", 
            "口座開設申込情報の部店コードが未指定です。");

    /**
     * 値段条件付き注文・値段未確定。<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_01340 = errorMgr.defineErrorInfo(
            1340,
            "BUSINESS_ERROR_01340", 
            "値段条件付き注文・値段未確定。");

    /**
     * 当該顧客は外国証券口座開設なし。<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_01341 = errorMgr.defineErrorInfo(
            1341,
            "BUSINESS_ERROR_01341", 
            "当該顧客は外国証券口座開設なし。");

    /**
     * 値段条件のチェック。<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_01342 = errorMgr.defineErrorInfo(
            1342,
            "BUSINESS_ERROR_01342", 
            "取扱可能な値段条件ではありません。");

    /**
     * 値段条件のNULLチェック。<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_01343 = errorMgr.defineErrorInfo(
            1343,
            "BUSINESS_ERROR_01343", 
            "値段条件が未指定です。");

    /**
     * 値段条件の値チェック。<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_01344 = errorMgr.defineErrorInfo(
            1344,
            "BUSINESS_ERROR_01344", 
            "値段条件が未定義の値です。");

    /**
     * 成行指定チェック（空売り規制数量超過時）。<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_01345 = errorMgr.defineErrorInfo(
            1345,
            "BUSINESS_ERROR_01345", 
            "成行指定不可（空売り規制数量超過時）。");

    /**
     * 執行条件チェック（空売り規制数量超過時）。<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_01346 = errorMgr.defineErrorInfo(
            1346,
            "BUSINESS_ERROR_01346", 
            "執行条件に”不出来引け成行”指定不可（空売り規制数量超過時）。");

    /**
     * 値段条件チェック（空売り規制数量超過時）。<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_01347 = errorMgr.defineErrorInfo(
            1347,
            "BUSINESS_ERROR_01347", 
            "値段条件に”優先値指値”、”成行残数指値”、”成行残数取消”指定不可（空売り規制数量超過時）。");

    /**
     * 値段条件・注文単価区分のチェック。<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_01348 = errorMgr.defineErrorInfo(
            1348,
            "BUSINESS_ERROR_01348", 
            "値段条件が指定なし以外の時は、注文単価区分を成行に設定して下さい。");

    /**
     * 値段条件・執行条件のチェック。<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_01349 = errorMgr.defineErrorInfo(
            1349,
            "BUSINESS_ERROR_01349", 
            "値段条件が成行残数指値注文又は、成行残数取消注文の時は、執行条件を無条件を設定して下さい。");

    /**
     * 値段条件・注文期限区分のチェック。<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_01350 = errorMgr.defineErrorInfo(
            1350,
            "BUSINESS_ERROR_01350", 
            "値段条件が指定なし以外の時は、注文期限区分を当日限りに設定して下さい。");

    /**
     * 値段条件・発注条件区分のチェック。<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_01351 = errorMgr.defineErrorInfo(
            1351,
            "BUSINESS_ERROR_01351", 
            "値段条件が指定なし以外の時は、発注条件区分を指定なしに設定して下さい。");

    /**
     * MM銘柄成行指定チェック。<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_01352 = errorMgr.defineErrorInfo(
            1352,
            "BUSINESS_ERROR_01352", 
            "MM銘柄は成行指定不可。");

    /**
     * オペレーション日付の指定に誤りがあります。<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_01353 = errorMgr.defineErrorInfo(
            1353,
            "BUSINESS_ERROR_01353", 
            "オペレーション日付の指定に誤りがあります。");

    /**
     * オペレーション日付が現在日付ではありません。<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_01354 = errorMgr.defineErrorInfo(
            1354,
            "BUSINESS_ERROR_01354", 
            "オペレーション日付が現在日付ではありません。");

    /**
     * 成行指定規制チェック（現物株式・成行指定規制中）。<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_01355 = errorMgr.defineErrorInfo(
            1355,
            "BUSINESS_ERROR_01355", 
            "現物株式・成行指定規制中。");

    /**
     * インサイダーチェックのため取引停止中です。<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_01356 = errorMgr.defineErrorInfo(
            1356,
            "BUSINESS_ERROR_01356", 
            "インサイダーチェックのため取引停止中です。");

    /**
     * 顧客は指定銘柄の該当取引停止中。<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_01357 = errorMgr.defineErrorInfo(
            1357,
            "BUSINESS_ERROR_01357", 
            "顧客は指定銘柄の該当取引停止中。");

    /**
     * 発注条件のチェック３（”2：W指値”）エラー。<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_01358 = errorMgr.defineErrorInfo(
            1358,
            "BUSINESS_ERROR_01358", 
            "発注条件区分が”W指値”の場合は、W指値用発注単価区分を定義が必要です。");

    /**
     * 発注条件のチェック３（”2：W指値”）エラー。<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_01359 = errorMgr.defineErrorInfo(
            1359,
            "BUSINESS_ERROR_01359", 
            "発注条件区分が”W指値”の場合は、W指値用発注単価区分に”0：成行”又は”1：指値”を設定して下さい。");

    /**
     * W指値用発注条件単価の桁数チェック。<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_01360 = errorMgr.defineErrorInfo(
            1360,
            "BUSINESS_ERROR_01360", 
            "W指値用発注条件単価が１以上にして下さい。");

    /**
     * W指値用発注条件単価の桁数チェック。<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_01361 = errorMgr.defineErrorInfo(
            1361,
            "BUSINESS_ERROR_01361", 
            "W指値用発注条件単価が８桁を超えています。");

    /**
     * W指値用発注条件単価の設定値チェック。<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_01362 = errorMgr.defineErrorInfo(
            1362,
            "BUSINESS_ERROR_01362", 
            "W指値用発注条件単価に、数値以外の文字が含まれています。");

    /**
     * 振込先区分が存在しないコード値です。<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_01363 = errorMgr.defineErrorInfo(
            1363,
            "BUSINESS_ERROR_01363", 
            "振込先区分が存在しないコード値です。");

    /**
     * 乗換可能銘柄なしエラー。<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_01364 = errorMgr.defineErrorInfo(
            1364,
            "BUSINESS_ERROR_01364", 
            "乗換可能銘柄なしエラー。");

    /**
     * 取扱市場なし。<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_01365 = errorMgr.defineErrorInfo(
            1365,
            "BUSINESS_ERROR_01365", 
            "取扱市場なし。");

    /**
     * 優先市場一覧に取扱市場なし。<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_01366 = errorMgr.defineErrorInfo(
            1366,
            "BUSINESS_ERROR_01366", 
            "優先市場一覧に取扱市場なし。");

    /**
     * 指定市場は非上場。<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_01367 = errorMgr.defineErrorInfo(
            1367,
            "BUSINESS_ERROR_01367", 
            "指定市場は非上場。");

    /**
     * 立会外分売可能レコードが特定不可。<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_01368 = errorMgr.defineErrorInfo(
            1368,
            "BUSINESS_ERROR_01368", 
            "立会外分売可能レコードが特定不可。");

    /**
     * 指定分売は受付可能時間外。<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_01369 = errorMgr.defineErrorInfo(
            1369,
            "BUSINESS_ERROR_01369", 
            "指定分売は受付可能時間外。");

    /**
     * 指定の立会外分売に対する注文が登録済。<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_01370 = errorMgr.defineErrorInfo(
            1370,
            "BUSINESS_ERROR_01370", 
            "指定の立会外分売に対する注文が登録済。");

    /**
     * 株数が立会外分売の一人当たり申込株数上限を超過。<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_01371 = errorMgr.defineErrorInfo(
            1371,
            "BUSINESS_ERROR_01371", 
            "株数が立会外分売の一人当たり申込株数上限を超過。");

    /**
     * 立会外分売注文内容チェック。<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_01372 = errorMgr.defineErrorInfo(
            1372,
            "BUSINESS_ERROR_01372", 
            "立会外分売は指値のみ指定可（成行指定不可）。");

    /**
     * 立会外分売注文内容チェック。<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_01373 = errorMgr.defineErrorInfo(
            1373,
            "BUSINESS_ERROR_01373", 
            "立会外分売は出来るまで注文指定不可。");

    /**
     * 立会外分売注文内容チェック。<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_01374 = errorMgr.defineErrorInfo(
            1374,
            "BUSINESS_ERROR_01374", 
            "立会外分売は執行条件指定不可。");

    /**
     * 立会外分売注文内容チェック。<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_01375 = errorMgr.defineErrorInfo(
            1375,
            "BUSINESS_ERROR_01375", 
            "立会外分売は発注条件指定不可。");

    /**
     * 立会外分売注文内容チェック。<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_01376 = errorMgr.defineErrorInfo(
            1376,
            "BUSINESS_ERROR_01376", 
            "立会外分売は値段条件指定不可。");

    /**
     * 立会外分売・銘柄コード指定チェック。<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_01377 = errorMgr.defineErrorInfo(
            1377,
            "BUSINESS_ERROR_01377", 
            "立会外分売で銘柄コード指定なし。");

    /**
     * 立会外分売・市場コード指定チェック。<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_01378 = errorMgr.defineErrorInfo(
            1378,
            "BUSINESS_ERROR_01378", 
            "立会外分売で市場コード指定なし。");

    /**
     * 案内メールがメールテーブルに存在しない。<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_01379 = errorMgr.defineErrorInfo(
            1379,
            "BUSINESS_ERROR_01379", 
            "案内メールがメールテーブルに存在しない。");

    /**
     * 全部店許可の管理者でない場合エラー。<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_01380 = errorMgr.defineErrorInfo(
            1380,
            "BUSINESS_ERROR_01380", 
            "全部店許可の管理者でない場合は、操作不可。");

    /**
     * 最新履歴が配信未済の場合は、配信指示不可。<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_01381 = errorMgr.defineErrorInfo(
            1381,
            "BUSINESS_ERROR_01381", 
            "最新履歴が配信未済の場合は、配信指示不可。");

    /**
     * 最新履歴が配信済の場合は、配信取消不可。<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_01382 = errorMgr.defineErrorInfo(
            1382,
            "BUSINESS_ERROR_01382", 
            "最新履歴が配信済の場合は、配信取消不可。");

    /**
     * 案内メールの値が存在しないコード値です。<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_01383 = errorMgr.defineErrorInfo(
            1383,
            "BUSINESS_ERROR_01383", 
            "案内メールの値が存在しないコード値です。");

    /**
     * 識別ＩＤがデフォルト値以外の値です。<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_01384 = errorMgr.defineErrorInfo(
            1384,
            "BUSINESS_ERROR_01384", 
            "識別ＩＤがデフォルト値以外の値です。");

    /**
     * 顧客コード（自）が未指定の場合、顧客コード（至）が指定不可。<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_01385 = errorMgr.defineErrorInfo(
            1385,
            "BUSINESS_ERROR_01385", 
            "顧客コード（自）が未指定の場合、顧客コード（至）が指定不可。");

    /**
     * 該当部店データなし。<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_01386 = errorMgr.defineErrorInfo(
            1386,
            "BUSINESS_ERROR_01386", 
            "該当部店データなし");

    /**
     * 該当顧客データなし。<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_01387 = errorMgr.defineErrorInfo(
            1387,
            "BUSINESS_ERROR_01387", 
            "該当顧客データなし");

    /**
     * is更新削除可能立会外分売銘柄( )==falseの場合（立会外分売銘柄が更新削除不可）。<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_01388 = errorMgr.defineErrorInfo(
            1388,
            "BUSINESS_ERROR_01388", 
            "指定された立会外分売銘柄データは更新／削除ができません。");

    /**
     * 受付開始日時がnull。<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_01389 = errorMgr.defineErrorInfo(
            1389,
            "BUSINESS_ERROR_01389", 
            "受付開始日時が未指定です。");

    /**
     * 買単価がnull。<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_01390 = errorMgr.defineErrorInfo(
            1390,
            "BUSINESS_ERROR_01390", 
            "単価が未指定です。");

    /**
     * 買単価が数字以外
売単価が数字以外。<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_01391 = errorMgr.defineErrorInfo(
            1391,
            "BUSINESS_ERROR_01391", 
            "単価が数字以外です。");

    /**
     * 買単価が0以下
売単価が0以下。<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_01392 = errorMgr.defineErrorInfo(
            1392,
            "BUSINESS_ERROR_01392", 
            "単価が0以下です。");

    /**
     * 買単価の桁数が8桁を超過
売単価の桁数が8桁を超過。<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_01393 = errorMgr.defineErrorInfo(
            1393,
            "BUSINESS_ERROR_01393", 
            "単価の桁数が8桁を超過です。");

    /**
     * 注文形態がnull。<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_01394 = errorMgr.defineErrorInfo(
            1394,
            "BUSINESS_ERROR_01394", 
            "注文形態が未指定です。");

    /**
     * 注文形態が未定義の値。<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_01395 = errorMgr.defineErrorInfo(
            1395,
            "BUSINESS_ERROR_01395", 
            "注文形態の値が存在しないコード値です。");

    /**
     * 現物／ミニ株区分がnull。<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_01396 = errorMgr.defineErrorInfo(
            1396,
            "BUSINESS_ERROR_01396", 
            "現物／ミニ株区分が未指定です。");

    /**
     * 現物／ミニ株区分が未定義の値。<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_01397 = errorMgr.defineErrorInfo(
            1397,
            "BUSINESS_ERROR_01397", 
            "現物／ミニ株区分の値が存在しないコード値です。");

    /**
     * 売買単位がnull。<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_01398 = errorMgr.defineErrorInfo(
            1398,
            "BUSINESS_ERROR_01398", 
            "売買単位が未指定です。");

    /**
     * 売買単位が数字以外。<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_01399 = errorMgr.defineErrorInfo(
            1399,
            "BUSINESS_ERROR_01399", 
            "売買単位が数字以外です。");

    /**
     * 売買単位が0以下。<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_01400 = errorMgr.defineErrorInfo(
            1400,
            "BUSINESS_ERROR_01400", 
            "売買単位が0以下です。");

    /**
     * 株数が売買単位の10分の1に対して非整数倍。<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_01401 = errorMgr.defineErrorInfo(
            1401,
            "BUSINESS_ERROR_01401", 
            "株数が売買単位の10分の1に対して非整数倍です。");

    /**
     * 売買区分がnull。<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_01402 = errorMgr.defineErrorInfo(
            1402,
            "BUSINESS_ERROR_01402", 
            "売買区分が未指定です。");

    /**
     * 売買区分が未定義の値。<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_01403 = errorMgr.defineErrorInfo(
            1403,
            "BUSINESS_ERROR_01403", 
            "売買区分の値が存在しないコード値です。");

    /**
     * 株単価が数字以外。<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_01404 = errorMgr.defineErrorInfo(
            1404,
            "BUSINESS_ERROR_01404", 
            "株単価が数字以外です。");

    /**
     * 株単価が0以下。<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_01405 = errorMgr.defineErrorInfo(
            1405,
            "BUSINESS_ERROR_01405", 
            "株単価が0以下です。");

    /**
     * 株単価の桁数が8桁を超過。<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_01406 = errorMgr.defineErrorInfo(
            1406,
            "BUSINESS_ERROR_01406", 
            "株単価の桁数が8桁を超過です。");

    /**
     * 商品別取扱状況一覧がnull。<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_01407 = errorMgr.defineErrorInfo(
            1407,
            "BUSINESS_ERROR_01407", 
            "商品別取扱状況一覧が未指定です。");

    /**
     * 商品別取扱状況一覧の要素数が0。<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_01408 = errorMgr.defineErrorInfo(
            1408,
            "BUSINESS_ERROR_01408", 
            "商品別取扱状況一覧の要素数が0です。");

    /**
     * ログイン許可区分エラー(未定義の値)。<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_01409 = errorMgr.defineErrorInfo(
            1409,
            "BUSINESS_ERROR_01409", 
            "ログイン許可区分の値が存在しないコード値です。");

    /**
     * 入力／特定区分がnull。<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_01410 = errorMgr.defineErrorInfo(
            1410,
            "BUSINESS_ERROR_01410", 
            "入力／特定区分が未指定です。");

    /**
     * 入力／特定区分が未定義の値。<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_01411 = errorMgr.defineErrorInfo(
            1411,
            "BUSINESS_ERROR_01411", 
            "入力／特定区分の値が存在しないコード値です。");

    /**
     * 評価単価が数字以外。<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_01412 = errorMgr.defineErrorInfo(
            1412,
            "BUSINESS_ERROR_01412", 
            "評価単価が数字以外です。");

    /**
     * 評価単価が0以下。<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_01413 = errorMgr.defineErrorInfo(
            1413,
            "BUSINESS_ERROR_01413", 
            "評価単価が0以下です。");

    /**
     * 評価単価の桁数が8桁を超過。<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_01414 = errorMgr.defineErrorInfo(
            1414,
            "BUSINESS_ERROR_01414", 
            "評価単価の桁数が8桁を超過です。");

    /**
     * 表示対象が未定義の値。<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_01415 = errorMgr.defineErrorInfo(
            1415,
            "BUSINESS_ERROR_01415", 
            "表示対象の値が存在しないコード値です。");

    /**
     * ログイン許可状況一覧がnull。<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_01416 = errorMgr.defineErrorInfo(
            1416,
            "BUSINESS_ERROR_01416", 
            "ログイン許可状況一覧が未指定です。");

    /**
     * ログイン許可状況一覧の要素数が0。<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_01417 = errorMgr.defineErrorInfo(
            1417,
            "BUSINESS_ERROR_01417", 
            "ログイン許可状況一覧の要素数が0です。");

    /**
     * ポートフォリオコードがnull。<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_01418 = errorMgr.defineErrorInfo(
            1418,
            "BUSINESS_ERROR_01418", 
            "ポートフォリオコードが未指定です。");

    /**
     * 対象明細の指定なし。<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_01419 = errorMgr.defineErrorInfo(
            1419,
            "BUSINESS_ERROR_01419", 
            "対象明細の指定なしです。");

    /**
     * 売単価がnull。<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_01420 = errorMgr.defineErrorInfo(
            1420,
            "BUSINESS_ERROR_01420", 
            "売単価が未指定です。");

    /**
     * 取引停止区分エラー(未定義の値)。<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_01421 = errorMgr.defineErrorInfo(
            1421,
            "BUSINESS_ERROR_01421", 
            "取引停止区分の値が存在しないコード値です。");

    /**
     * 市場別取引状況一覧がnull。<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_01422 = errorMgr.defineErrorInfo(
            1422,
            "BUSINESS_ERROR_01422", 
            "市場別取引状況一覧が未指定です。");

    /**
     * 市場別取引状況一覧の要素数が0。<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_01423 = errorMgr.defineErrorInfo(
            1423,
            "BUSINESS_ERROR_01423", 
            "市場別取引状況一覧の要素数が0です。");

    /**
     * 注文受付商品がnull。<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_01424 = errorMgr.defineErrorInfo(
            1424,
            "BUSINESS_ERROR_01424", 
            "注文受付商品が未指定です。");

    /**
     * 注文受付トランザクションがnull。<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_01425 = errorMgr.defineErrorInfo(
            1425,
            "BUSINESS_ERROR_01425", 
            "注文受付トランザクションが未指定です。");

    /**
     * 部店別取扱状況一覧の要素数が0。<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_01426 = errorMgr.defineErrorInfo(
            1426,
            "BUSINESS_ERROR_01426", 
            "部店別取扱状況一覧の要素数が0です。");

    /**
     * 部店別取扱状況一覧がnull。<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_01427 = errorMgr.defineErrorInfo(
            1427,
            "BUSINESS_ERROR_01427", 
            "部店別取扱状況一覧が未指定です。");

    /**
     * 顧客銘柄別取引停止情報がnull。<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_01428 = errorMgr.defineErrorInfo(
            1428,
            "BUSINESS_ERROR_01428", 
            "顧客銘柄別取引停止情報が未指定です。");

    /**
     * 部店コード一覧がnull。<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_01429 = errorMgr.defineErrorInfo(
            1429,
            "BUSINESS_ERROR_01429", 
            "部店コード一覧が未指定です。");

    /**
     * 有効期限Fromがnull。<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_01430 = errorMgr.defineErrorInfo(
            1430,
            "BUSINESS_ERROR_01430", 
            "有効期限Fromが未指定です。");

    /**
     * 有効期限Fromエラー(存在しない日付)。<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_01431 = errorMgr.defineErrorInfo(
            1431,
            "BUSINESS_ERROR_01431", 
            "有効期限Fromエラー(存在しない日付)");

    /**
     * 有効期限Toがnull。<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_01432 = errorMgr.defineErrorInfo(
            1432,
            "BUSINESS_ERROR_01432", 
            "有効期限Toが未指定です。");

    /**
     * 有効期限Toエラー(存在しない日付)。<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_01433 = errorMgr.defineErrorInfo(
            1433,
            "BUSINESS_ERROR_01433", 
            "有効期限Toエラー(存在しない日付)");

    /**
     * 有効期限整合性エラー。<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_01434 = errorMgr.defineErrorInfo(
            1434,
            "BUSINESS_ERROR_01434", 
            "有効期限整合性エラー");

    /**
     * 入力理由エラー(桁数超過)。<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_01435 = errorMgr.defineErrorInfo(
            1435,
            "BUSINESS_ERROR_01435", 
            "入力理由エラー(桁数超過)");

    /**
     * 顧客取引停止情報一覧がnull。<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_01436 = errorMgr.defineErrorInfo(
            1436,
            "BUSINESS_ERROR_01436", 
            "顧客取引停止情報一覧が未指定です。");

    /**
     * 顧客取引停止情報一覧の要素数が0。<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_01437 = errorMgr.defineErrorInfo(
            1437,
            "BUSINESS_ERROR_01437", 
            "顧客取引停止情報一覧の要素数が0です。");

    /**
     * 注文種別がnull。<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_01438 = errorMgr.defineErrorInfo(
            1438,
            "BUSINESS_ERROR_01438", 
            "注文種別が未指定です。");

    /**
     * 注文種別が未定義の値。<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_01439 = errorMgr.defineErrorInfo(
            1439,
            "BUSINESS_ERROR_01439", 
            "注文種別の値が存在しないコード値です。");

    /**
     * 確認内容がnull。<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_01440 = errorMgr.defineErrorInfo(
            1440,
            "BUSINESS_ERROR_01440", 
            "確認内容が未指定です。");

    /**
     * 入力数値エラー。<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_01441 = errorMgr.defineErrorInfo(
            1441,
            "BUSINESS_ERROR_01441", 
            "入力数値エラー");

    /**
     * 入力日付エラー(適用期間From)。<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_01442 = errorMgr.defineErrorInfo(
            1442,
            "BUSINESS_ERROR_01442", 
            "入力日付エラー(適用期間From)");

    /**
     * 入力日付エラー(適用期間To)。<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_01443 = errorMgr.defineErrorInfo(
            1443,
            "BUSINESS_ERROR_01443", 
            "入力日付エラー(適用期間To)");

    /**
     * 日付未入力エラー(適用期間From)。<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_01444 = errorMgr.defineErrorInfo(
            1444,
            "BUSINESS_ERROR_01444", 
            "日付未入力エラー(適用期間From)");

    /**
     * 入力日付エラー(適用期間Toが当日日付未満)。<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_01445 = errorMgr.defineErrorInfo(
            1445,
            "BUSINESS_ERROR_01445", 
            "入力日付エラー(適用期間Toが当日日付未満)");

    /**
     * 適用期間From/To整合性エラー。<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_01446 = errorMgr.defineErrorInfo(
            1446,
            "BUSINESS_ERROR_01446", 
            "適用期間From/To整合性エラー");

    /**
     * 大項目区分がnull。<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_01447 = errorMgr.defineErrorInfo(
            1447,
            "BUSINESS_ERROR_01447", 
            "大項目区分が未指定です。");

    /**
     * 大項目区分の要素数が0。<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_01448 = errorMgr.defineErrorInfo(
            1448,
            "BUSINESS_ERROR_01448", 
            "大項目区分の要素数が0です。");

    /**
     * 大項目区分が未定義の値。<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_01449 = errorMgr.defineErrorInfo(
            1449,
            "BUSINESS_ERROR_01449", 
            "大項目区分の値が存在しないコード値です。");

    /**
     * 小項目区分が未定義の値。<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_01450 = errorMgr.defineErrorInfo(
            1450,
            "BUSINESS_ERROR_01450", 
            "小項目区分の値が存在しないコード値です。");

    /**
     * 営業日がnull。<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_01451 = errorMgr.defineErrorInfo(
            1451,
            "BUSINESS_ERROR_01451", 
            "営業日が未指定です。");

    /**
     * 受付終了日時がnull。<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_01452 = errorMgr.defineErrorInfo(
            1452,
            "BUSINESS_ERROR_01452", 
            "受付終了日時が未指定です。");

    /**
     * 分売価格が数字以外。<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_01453 = errorMgr.defineErrorInfo(
            1453,
            "BUSINESS_ERROR_01453", 
            "分売価格が数字以外の値です。");

    /**
     * 分売価格が0以下。<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_01454 = errorMgr.defineErrorInfo(
            1454,
            "BUSINESS_ERROR_01454", 
            "分売価格が0以下の値です。");

    /**
     * 分売価格の桁数が9桁を超過。<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_01455 = errorMgr.defineErrorInfo(
            1455,
            "BUSINESS_ERROR_01455", 
            "分売価格のサイズが不正です。");

    /**
     * 申込株数上限が数字以外。<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_01456 = errorMgr.defineErrorInfo(
            1456,
            "BUSINESS_ERROR_01456", 
            "申込株数上限が数字以外の値です。");

    /**
     * 申込株数上限が0以下。<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_01457 = errorMgr.defineErrorInfo(
            1457,
            "BUSINESS_ERROR_01457", 
            "申込株数上限が0以下の値です。");

    /**
     * 申込株数上限の桁数が8桁を超過。<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_01458 = errorMgr.defineErrorInfo(
            1458,
            "BUSINESS_ERROR_01458", 
            "申込株数上限のサイズが不正です。");

    /**
     * 立会外分売銘柄・受付開始日時には、過去日時指定不可。<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_01459 = errorMgr.defineErrorInfo(
            1459,
            "BUSINESS_ERROR_01459", 
            "立会外分売銘柄・受付開始日時には、過去日時を指定できません。");

    /**
     * 立会外分売銘柄・受付開始日時には、証券会社.立会外分売受付開始時刻前は指定不可。<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_01460 = errorMgr.defineErrorInfo(
            1460,
            "BUSINESS_ERROR_01460", 
            "立会外分売銘柄・受付開始日時には、証券会社.立会外分売受付開始時刻前を指定できません。");

    /**
     * 重複する分売銘柄データが既に登録済の場合（重複する分売銘柄データが既に登録済）。<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_01461 = errorMgr.defineErrorInfo(
            1461,
            "BUSINESS_ERROR_01461", 
            "テーブルに重複する該当データが存在します。");

    /**
     * 商品区分一覧がnull。<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_01462 = errorMgr.defineErrorInfo(
            1462,
            "BUSINESS_ERROR_01462", 
            "商品区分一覧が未指定です。");

    /**
     * 商品区分一覧の要素数が0。<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_01463 = errorMgr.defineErrorInfo(
            1463,
            "BUSINESS_ERROR_01463", 
            "商品区分一覧の要素数が0");

    /**
     * 指数種別が未定義の値。<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_01464 = errorMgr.defineErrorInfo(
            1464,
            "BUSINESS_ERROR_01464", 
            "指数種別の値が存在しないコード値です。");

    /**
     * 集計区分がnull。<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_01465 = errorMgr.defineErrorInfo(
            1465,
            "BUSINESS_ERROR_01465", 
            "集計区分が未指定です。");

    /**
     * 集計区分が未定義の値。<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_01466 = errorMgr.defineErrorInfo(
            1466,
            "BUSINESS_ERROR_01466", 
            "集計区分の値が存在しないコード値です。");

    /**
     * 日別集計対象年月がnull。<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_01467 = errorMgr.defineErrorInfo(
            1467,
            "BUSINESS_ERROR_01467", 
            "日別集計対象年月が未指定です。");

    /**
     * 日別集計対象年月エラー。<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_01468 = errorMgr.defineErrorInfo(
            1468,
            "BUSINESS_ERROR_01468", 
            "日別集計対象年月エラー。");

    /**
     * 月別集計対象区分がnull。<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_01469 = errorMgr.defineErrorInfo(
            1469,
            "BUSINESS_ERROR_01469", 
            "月別集計対象区分が未指定です。");

    /**
     * 月別集計対象区分が未定義の値。<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_01470 = errorMgr.defineErrorInfo(
            1470,
            "BUSINESS_ERROR_01470", 
            "月別集計対象区分の値が存在しないコード値です。");

    /**
     * 注文経路表示区分がnull。<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_01471 = errorMgr.defineErrorInfo(
            1471,
            "BUSINESS_ERROR_01471", 
            "注文経路表示区分が未指定です。");

    /**
     * 注文経路表示区分が未定義の値。<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_01472 = errorMgr.defineErrorInfo(
            1472,
            "BUSINESS_ERROR_01472", 
            "注文経路表示区分の値が存在しないコード値です。");

    /**
     * 市場表示区分がnull。<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_01473 = errorMgr.defineErrorInfo(
            1473,
            "BUSINESS_ERROR_01473", 
            "市場表示区分が未指定です。");

    /**
     * 市場表示区分が未定義の値。<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_01474 = errorMgr.defineErrorInfo(
            1474,
            "BUSINESS_ERROR_01474", 
            "市場表示区分の値が存在しないコード値です。");

    /**
     * 行使価格エラー。<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_01475 = errorMgr.defineErrorInfo(
            1475,
            "BUSINESS_ERROR_01475", 
            "行使価格エラー。");

    /**
     * 注文IDが数字以外。<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_01476 = errorMgr.defineErrorInfo(
            1476,
            "BUSINESS_ERROR_01476", 
            "注文IDが数字以外です。");

    /**
     * 注文状態区分が未定義の値。<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_01477 = errorMgr.defineErrorInfo(
            1477,
            "BUSINESS_ERROR_01477", 
            "注文状態区分の値が存在しないコード値です。");

    /**
     * 訂正取消区分が未定義の値。<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_01478 = errorMgr.defineErrorInfo(
            1478,
            "BUSINESS_ERROR_01478", 
            "訂正取消区分の値が存在しないコード値です。");

    /**
     * 入力時間エラー(注文時間From)。<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_01479 = errorMgr.defineErrorInfo(
            1479,
            "BUSINESS_ERROR_01479", 
            "入力時間エラー(注文時間From)。");

    /**
     * 入力時間エラー(注文時間To)。<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_01480 = errorMgr.defineErrorInfo(
            1480,
            "BUSINESS_ERROR_01480", 
            "入力時間エラー(注文時間To）。");

    /**
     * 入力時間整合性エラー。<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_01481 = errorMgr.defineErrorInfo(
            1481,
            "BUSINESS_ERROR_01481", 
            "入力時間整合性エラー。");

    /**
     * 入力時間エラー(受渡日)。<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_01482 = errorMgr.defineErrorInfo(
            1482,
            "BUSINESS_ERROR_01482", 
            "入力時間エラー(受渡日)。");

    /**
     * 日本側予めエラーメッセージ採番ナンバー。<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_01685 = errorMgr.defineErrorInfo(
            1685,
            "BUSINESS_ERROR_01685", 
            "日本側予めエラーメッセージ採番ナンバー。");

    /**
     * 問合せ日（自）は問合せ日（至）より大きいです。<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_01686 = errorMgr.defineErrorInfo(
            1686,
            "BUSINESS_ERROR_01686", 
            "問合せ日（自）は問合せ日（至）より大きいです。");

    /**
     * 機@能ＩＤが未指定です。<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_01687 = errorMgr.defineErrorInfo(
            1687,
            "BUSINESS_ERROR_01687", 
            "機@能ＩＤが未指定です。");

    /**
     * 問合せコードが未指定です。<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_01688 = errorMgr.defineErrorInfo(
            1688,
            "BUSINESS_ERROR_01688", 
            "問合せコードが未指定です。");

    /**
     * 問合せ情報が未指定です。<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_01689 = errorMgr.defineErrorInfo(
            1689,
            "BUSINESS_ERROR_01689", 
            "問合せ情報が未指定です。");

    /**
     * 顧客名未指定です。<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_01690 = errorMgr.defineErrorInfo(
            1690,
            "BUSINESS_ERROR_01690", 
            "顧客名未指定です。");

    /**
     * 顧客名が全角文字ではありません。<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_01691 = errorMgr.defineErrorInfo(
            1691,
            "BUSINESS_ERROR_01691", 
            "顧客名が全角文字ではありません。");

    /**
     * 問合せ件名の桁数が制限を越えています。<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_01692 = errorMgr.defineErrorInfo(
            1692,
            "BUSINESS_ERROR_01692", 
            "問合せ件名の桁数が制限を越えています。");

    /**
     * メールアドレスの桁数が制限を越えています。<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_01693 = errorMgr.defineErrorInfo(
            1693,
            "BUSINESS_ERROR_01693", 
            "メールアドレスの桁数が制限を越えています。");

    /**
     * 問合せ内容が未指定です。<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_01694 = errorMgr.defineErrorInfo(
            1694,
            "BUSINESS_ERROR_01694", 
            "問合せ内容が未指定です。");

    /**
     * 問合せ内容の桁数が制限を越えています。<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_01695 = errorMgr.defineErrorInfo(
            1695,
            "BUSINESS_ERROR_01695", 
            "問合せ内容の桁数が制限を越えています。");

    /**
     * 問合せ内容が全角文字ではありません。<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_01696 = errorMgr.defineErrorInfo(
            1696,
            "BUSINESS_ERROR_01696", 
            "問合せ内容が全角文字ではありません。");

    /**
     * 約定状態区分チェック。<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_01697 = errorMgr.defineErrorInfo(
            1697,
            "BUSINESS_ERROR_01697", 
            "約定状態区分は、設定しないか『0:未約定』，『1:一部約定』，『2:全部約定』を設定して下さい。");

    /**
     * ソート・キーチェック（立会用）。<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_01698 = errorMgr.defineErrorInfo(
            1698,
            "BUSINESS_ERROR_01698", 
            "ソートキー.キー項目の値“銘柄コード”、“市場コード ”、“受付開始日時 ”、“受付終了日時 ”以外を、設定しないで下さい。");

    /**
     * 注文受付商品チェック。<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_01699 = errorMgr.defineErrorInfo(
            1699,
            "BUSINESS_ERROR_01699", 
            "注文受付商品は、設定しないか『01:株式』，『02:株式ミニ投資』，『03:信用取引』を設定して下さい。");

    /**
     * メールアドレスが未指定です。<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_01700 = errorMgr.defineErrorInfo(
            1700,
            "BUSINESS_ERROR_01700", 
            "メールアドレスが未指定です。");

    /**
     * 注文受付停止開始時間の指定可能時間の範囲内ではありません。<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_01701 = errorMgr.defineErrorInfo(
            1701,
            "BUSINESS_ERROR_01701", 
            "注文受付停止開始時間の指定可能時間の範囲内ではありません。");

    /**
     * 注文受付停止開始時間の指定がHOST登録時間より後の時間が指定されています。<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_01702 = errorMgr.defineErrorInfo(
            1702,
            "BUSINESS_ERROR_01702", 
            "注文受付停止開始時間の指定がHOST登録時間より後の時間が指定されています。");

    /**
     * 信用特定口座未開設。<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_01703 = errorMgr.defineErrorInfo(
            1703,
            "BUSINESS_ERROR_01703", 
            "信用の特定口座開設なし。");

    /**
     * カテゴリー名のチェック。<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_01705 = errorMgr.defineErrorInfo(
            1705,
            "BUSINESS_ERROR_01705", 
            "カテゴリー名の文字数が制限を越えています。");

    /**
     * 概要のチェック。<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_01706 = errorMgr.defineErrorInfo(
            1706,
            "BUSINESS_ERROR_01706", 
            "概要の文字数が制限を越えています。");

    /**
     * カテゴリー内容訂正箇所のチェック。<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_01707 = errorMgr.defineErrorInfo(
            1707,
            "BUSINESS_ERROR_01707", 
            "カテゴリー内容の訂正がされていません。");

    /**
     * 景品が取扱われているかどうかのチェック。<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_01708 = errorMgr.defineErrorInfo(
            1708,
            "BUSINESS_ERROR_01708", 
            "該当するカテゴリーで扱われている景品があります。");

    /**
     * 景品番号が重複してないかのチェック。<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_01709 = errorMgr.defineErrorInfo(
            1709,
            "BUSINESS_ERROR_01709", 
            "景品番号が重複となっております。");

    /**
     * 景品番号のチェック。<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_01710 = errorMgr.defineErrorInfo(
            1710,
            "BUSINESS_ERROR_01710", 
            "景品番号の桁数が制限を越えています。");

    /**
     * 景品番号のチェック。<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_01711 = errorMgr.defineErrorInfo(
            1711,
            "BUSINESS_ERROR_01711", 
            "景品番号に「半角英数字」、「-（ハイフン）」以外の文字が含まれています。");

    /**
     * 景品名のチェック。<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_01712 = errorMgr.defineErrorInfo(
            1712,
            "BUSINESS_ERROR_01712", 
            "景品名の文字数が制限を越えています。");

    /**
     * 必要ポイントのチェック。<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_01713 = errorMgr.defineErrorInfo(
            1713,
            "BUSINESS_ERROR_01713", 
            "必要ポイントの桁数が制限を越えています。");

    /**
     * 必要ポイントのチェック。<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_01714 = errorMgr.defineErrorInfo(
            1714,
            "BUSINESS_ERROR_01714", 
            "必要ポイントが0以下の値です。");

    /**
     * 必要ポイントのチェック。<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_01715 = errorMgr.defineErrorInfo(
            1715,
            "BUSINESS_ERROR_01715", 
            "必要ポイントの値が数値以外の値です。");

    /**
     * 提供期間チェック。<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_01716 = errorMgr.defineErrorInfo(
            1716,
            "BUSINESS_ERROR_01716", 
            "提供開始日時、または提供終了日時が未指定です。");

    /**
     * 提供期間チェック。<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_01717 = errorMgr.defineErrorInfo(
            1717,
            "BUSINESS_ERROR_01717", 
            "提供開始日時は提供終了日時より大きいです。");

    /**
     * 景品内容訂正箇所のチェック。<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_01718 = errorMgr.defineErrorInfo(
            1718,
            "BUSINESS_ERROR_01718", 
            "景品内容の訂正がされていません。");

    /**
     * 調整ポイントのチェック。<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_01719 = errorMgr.defineErrorInfo(
            1719,
            "BUSINESS_ERROR_01719", 
            "調整ポイントが未指定です。");

    /**
     * 調整ポイントのチェック。<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_01720 = errorMgr.defineErrorInfo(
            1720,
            "BUSINESS_ERROR_01720", 
            "調整ポイントが0以下の値です。");

    /**
     * 調整ポイントのチェック。<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_01721 = errorMgr.defineErrorInfo(
            1721,
            "BUSINESS_ERROR_01721", 
            "調整ポイントの値が数値以外の値です。");

    /**
     * 調整ポイントのチェック。<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_01722 = errorMgr.defineErrorInfo(
            1722,
            "BUSINESS_ERROR_01722", 
            "調整ポイントの桁数が制限を越えています。");

    /**
     * 調整後ポイントのチェック。<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_01724 = errorMgr.defineErrorInfo(
            1724,
            "BUSINESS_ERROR_01724", 
            "調整後の利用可能ポイントがマイナスになります。");

    /**
     * ポイント余力のチェック。<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_01725 = errorMgr.defineErrorInfo(
            1725,
            "BUSINESS_ERROR_01725", 
            "取消解除すると、お客様のご利用可能ポイントがマイナスとなります。");

    /**
     * 景品の提供期間チェック。<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_01726 = errorMgr.defineErrorInfo(
            1726,
            "BUSINESS_ERROR_01726", 
            "この景品は、現在取扱っておりません。");

    /**
     * 景品番号のチェック。<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_01727 = errorMgr.defineErrorInfo(
            1727,
            "BUSINESS_ERROR_01727", 
            "景品番号が未指定です。");

    /**
     * カテゴリー番号のチェック。<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_01728 = errorMgr.defineErrorInfo(
            1728,
            "BUSINESS_ERROR_01728", 
            "カテゴリー番号が未指定です。");

    /**
     * 部店コードの値のチェック。<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_01729 = errorMgr.defineErrorInfo(
            1729,
            "BUSINESS_ERROR_01729", 
            "部店コードが数値以外の値です。");

    /**
     * 確認時調整前利用可能ポイントのチェック。<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_01731 = errorMgr.defineErrorInfo(
            1731,
            "BUSINESS_ERROR_01731", 
            "確認時の利用可能ポイントと現在の利用可能ポイントが一致しません。");

    /**
     * 申込IDのチェック。<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_01732 = errorMgr.defineErrorInfo(
            1732,
            "BUSINESS_ERROR_01732", 
            "申込IDが未指定です。");

    /**
     * 申込IDのチェック。<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_01733 = errorMgr.defineErrorInfo(
            1733,
            "BUSINESS_ERROR_01733", 
            "申込IDのサイズが不正です。");

    /**
     * 該当注文は変更の受付済または発注中の状態。<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_01734 = errorMgr.defineErrorInfo(
            1734,
            "BUSINESS_ERROR_01734", 
            "該当注文は変更の受付済または発注中の状態。");

    /**
     * IPO：該当顧客が補欠者で既に辞退している場合のエラー。<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_01735 = errorMgr.defineErrorInfo(
            1735,
            "BUSINESS_ERROR_01735", 
            "該当顧客は補欠者で、既に辞退しています。");

    /**
     * 先物／オプション区分のチェック。<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_01736 = errorMgr.defineErrorInfo(
            1736,
            "BUSINESS_ERROR_01736", 
            "先物／オプション区分が未指定です。");

    /**
     * 先物／オプション区分のチェック。<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_01737 = errorMgr.defineErrorInfo(
            1737,
            "BUSINESS_ERROR_01737", 
            "先物／オプション区分の値が存在しないコード値です。");

    /**
     * 有効コード値チェック。<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_01738 = errorMgr.defineErrorInfo(
            1738,
            "BUSINESS_ERROR_01738", 
            "項目値が有効なコード値ではありません。");

    /**
     * PR層保持情報チェック。<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_01739 = errorMgr.defineErrorInfo(
            1739,
            "BUSINESS_ERROR_01739", 
            "PR層保持情報が未指定です。");

    /**
     * PR層保持情報チェック。<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_01740 = errorMgr.defineErrorInfo(
            1740,
            "BUSINESS_ERROR_01740", 
            "PR層保持情報の各属性が未指定です。");

    /**
     * IPO:（補欠落選の場合）辞退のチェック。<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_01741 = errorMgr.defineErrorInfo(
            1741,
            "BUSINESS_ERROR_01741", 
            "該当顧客は補欠落選者であり、IPO申告は辞退済になっています。");

    /**
     * IPO:validate項目レングス。<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_01742 = errorMgr.defineErrorInfo(
            1742,
            "BUSINESS_ERROR_01742", 
            "アップロードファ@イル内の項目長で最大値を超えているものがあります。");

    /**
     * IPO:繰上抽選で落選している場合のチェック。<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_01743 = errorMgr.defineErrorInfo(
            1743,
            "BUSINESS_ERROR_01743", 
            "該当顧客は繰上抽選で落選しています。");

    /**
     * IPO:購入申込終了(目論見書記載)のチェック。<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_01744 = errorMgr.defineErrorInfo(
            1744,
            "BUSINESS_ERROR_01744", 
            "購入申込（目論見書記載）は終了しています。");

    /**
     * IPO:新規申告日時がﾌﾞｯｸﾋﾞﾙﾃﾞｨﾝｸﾞ終了前でない場合のエラー。<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_01745 = errorMgr.defineErrorInfo(
            1745,
            "BUSINESS_ERROR_01745", 
            "該当ブックビルディング申告の新規申告日時はブックビルディングの終了日時を過ぎています。");

    /**
     * IPO:IPO申告が取消済みの場合のエラー。<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_01746 = errorMgr.defineErrorInfo(
            1746,
            "BUSINESS_ERROR_01746", 
            "該当ブックビルディング申告は既に申告取消されています。");

    /**
     * 市場コードチェック。<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_01747 = errorMgr.defineErrorInfo(
            1747,
            "BUSINESS_ERROR_01747", 
            "市場コードチェックエラー（市場取引停止中）。");

    /**
     * FX検索条件のチェック。<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_01748 = errorMgr.defineErrorInfo(
            1748,
            "BUSINESS_ERROR_01748", 
            "FX検索条件が未指定です。");

    /**
     * 更新後ステータス区分のチェック。<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_01749 = errorMgr.defineErrorInfo(
            1749,
            "BUSINESS_ERROR_01749", 
            "更新後ステータス区分が未指定です。");

    /**
     * 更新後ステータス区分のチェック。<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_01750 = errorMgr.defineErrorInfo(
            1750,
            "BUSINESS_ERROR_01750", 
            "更新後ステータス区分が存在しないコード値です。");

    /**
     * FX口座情報一覧のチェック。<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_01751 = errorMgr.defineErrorInfo(
            1751,
            "BUSINESS_ERROR_01751", 
            "FX口座情報一覧が未指定です。");

    /**
     * FX口座情報一覧のチェック。<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_01752 = errorMgr.defineErrorInfo(
            1752,
            "BUSINESS_ERROR_01752", 
            "FX口座情報一覧の要素数が０です。");

    /**
     * FX口座情報一覧のチェック。<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_01753 = errorMgr.defineErrorInfo(
            1753,
            "BUSINESS_ERROR_01753", 
            "FX口座情報のコース区分が未指定です。");

    /**
     * FX口座情報一覧のチェック。<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_01754 = errorMgr.defineErrorInfo(
            1754,
            "BUSINESS_ERROR_01754", 
            "FX口座情報のコース区分が存在しないコード値です。");

    /**
     * FX口座情報一覧のチェック。<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_01755 = errorMgr.defineErrorInfo(
            1755,
            "BUSINESS_ERROR_01755", 
            "更新後ステータスが”口座開設完了”の場合は、FX口座情報の口座番号が不正です。");

    /**
     * FX口座情報一覧のチェック。<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_01756 = errorMgr.defineErrorInfo(
            1756,
            "BUSINESS_ERROR_01756", 
            "更新後ステータスが”削除”の場合は、FX口座情報の口座番号が指定不可です。");

    /**
     * 部店コードの要素数が０です。<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_01757 = errorMgr.defineErrorInfo(
            1757,
            "BUSINESS_ERROR_01757", 
            "部店コードの要素数が０です。");

    /**
     * ステータス区分のチェック。<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_01758 = errorMgr.defineErrorInfo(
            1758,
            "BUSINESS_ERROR_01758", 
            "ステータス区分が存在しないコード値です。");

    /**
     * MRF口座状況区分のチェック。<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_01759 = errorMgr.defineErrorInfo(
            1759,
            "BUSINESS_ERROR_01759", 
            "MRF口座状況区分が存在しないコード値です。");

    /**
     * 申込日（自）のチェック。<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_01760 = errorMgr.defineErrorInfo(
            1760,
            "BUSINESS_ERROR_01760", 
            "申込日（自）の入力値が不正です。");

    /**
     * 申込日（至）のチェック。<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_01761 = errorMgr.defineErrorInfo(
            1761,
            "BUSINESS_ERROR_01761", 
            "申込日（至）の入力値が不正です。");

    /**
     * 申込日（自～至）整合性のチェック。<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_01762 = errorMgr.defineErrorInfo(
            1762,
            "BUSINESS_ERROR_01762", 
            "申込日（自）は申込日（至）より大きいです。");

    /**
     * 入力番号区分のチェック。<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_01763 = errorMgr.defineErrorInfo(
            1763,
            "BUSINESS_ERROR_01763", 
            "入力番号区分が未指定です。");

    /**
     * 入力番号区分のチェック。<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_01764 = errorMgr.defineErrorInfo(
            1764,
            "BUSINESS_ERROR_01764", 
            "入力番号区分が存在しないコード値です。");

    /**
     * 入力番号のチェック。<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_01765 = errorMgr.defineErrorInfo(
            1765,
            "BUSINESS_ERROR_01765", 
            "入力番号が未指定です。");

    /**
     * 入力番号のチェック。<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_01766 = errorMgr.defineErrorInfo(
            1766,
            "BUSINESS_ERROR_01766", 
            "入力番号が数値以外の値です。");

    /**
     * 更新後メールアドレスのチェック。<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_01767 = errorMgr.defineErrorInfo(
            1767,
            "BUSINESS_ERROR_01767", 
            "処理区分が”口座情報変更”の場合は、更新後メールアドレスが未指定です。");

    /**
     * 更新後FX口座情報一覧のチェック。<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_01768 = errorMgr.defineErrorInfo(
            1768,
            "BUSINESS_ERROR_01768", 
            "処理区分が”口座情報変更”の場合は、更新後FX口座情報一覧が未指定です。");

    /**
     * 更新後FX口座情報一覧のチェック。<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_01769 = errorMgr.defineErrorInfo(
            1769,
            "BUSINESS_ERROR_01769", 
            "処理区分が”口座情報変更”の場合は、更新後FX口座情報一覧の要素数が０です。");

    /**
     * 更新後口座開設状況区分のチェック。<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_01770 = errorMgr.defineErrorInfo(
            1770,
            "BUSINESS_ERROR_01770", 
            "処理区分が”口座開設状況変更”の場合は、更新後口座開設状況区分が未指定です。");

    /**
     * 更新後口座開設状況区分のチェック。<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_01771 = errorMgr.defineErrorInfo(
            1771,
            "BUSINESS_ERROR_01771", 
            "処理区分が”口座開設状況変更”の場合は、更新後口座開設状況区分が存在しないコード値です。");

    /**
     * 振替区分のチェック。<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_01772 = errorMgr.defineErrorInfo(
            1772,
            "BUSINESS_ERROR_01772", 
            "振替区分が存在しないコード値です。");

    /**
     * 受付日のチェック。<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_01773 = errorMgr.defineErrorInfo(
            1773,
            "BUSINESS_ERROR_01773", 
            "受付日（自）は受付日（至）以上です。");

    /**
     * FX検索条件一覧のチェック。<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_01774 = errorMgr.defineErrorInfo(
            1774,
            "BUSINESS_ERROR_01774", 
            "FX検索条件一覧が未指定です。");

    /**
     * 暗証番号チェックエラー。<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_01784 = errorMgr.defineErrorInfo(
            1784,
            "BUSINESS_ERROR_01784", 
            "暗証番号の桁数が正しくありません。");

    /**
     * メールアドレスチェックエラー。<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_01788 = errorMgr.defineErrorInfo(
            1788,
            "BUSINESS_ERROR_01788", 
            "確認用のメールアドレスが未入力です。");

    /**
     * メールアドレスチェックエラー。<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_01789 = errorMgr.defineErrorInfo(
            1789,
            "BUSINESS_ERROR_01789", 
            "メールアドレスが確認用のものと一致しておりません。");

    /**
     * 暗証番号チェックエラー。<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_01790 = errorMgr.defineErrorInfo(
            1790,
            "BUSINESS_ERROR_01790", 
            "確認用の暗証番号が未入力です。");

    /**
     * 暗証番号チェックエラー。<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_01791 = errorMgr.defineErrorInfo(
            1791,
            "BUSINESS_ERROR_01791", 
            "暗証番号が確認用のものと一致しておりません。");

    /**
     * コース区分チェック。<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_01792 = errorMgr.defineErrorInfo(
            1792,
            "BUSINESS_ERROR_01792", 
            "コース区分が未指定です。");

    /**
     * コース区分チェック。<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_01793 = errorMgr.defineErrorInfo(
            1793,
            "BUSINESS_ERROR_01793", 
            "コース区分が存在しないコード値です。");

    /**
     * 口座番号チェック。<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_01794 = errorMgr.defineErrorInfo(
            1794,
            "BUSINESS_ERROR_01794", 
            "口座番号が未指定です。");

    /**
     * 表示期間チェック。<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_01795 = errorMgr.defineErrorInfo(
            1795,
            "BUSINESS_ERROR_01795", 
            "表示期間（自）と表示期間（至）はいずれかが入力される時、残り一方も入力されるはずです。");

    /**
     * 取引同意質問回答整合性エラー。<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_01796 = errorMgr.defineErrorInfo(
            1796,
            "BUSINESS_ERROR_01796", 
            "取引同意質問に対する回答が正しくありません。");

    /**
     * 2重送信エラー。<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_01799 = errorMgr.defineErrorInfo(
            1799,
            "BUSINESS_ERROR_01799", 
            "2重送信エラー。");

    /**
     * その他のFXシステムエラー。<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_01800 = errorMgr.defineErrorInfo(
            1800,
            "BUSINESS_ERROR_01800", 
            "その他のFXシステムエラー。");

    /**
     * 受付時間外エラー。<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_01801 = errorMgr.defineErrorInfo(
            1801,
            "BUSINESS_ERROR_01801", 
            "受付時間外エラー。");

    /**
     * 通信エラー。<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_01802 = errorMgr.defineErrorInfo(
            1802,
            "BUSINESS_ERROR_01802", 
            "通信エラー。");

    /**
     * 残高不足エラー。<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_01803 = errorMgr.defineErrorInfo(
            1803,
            "BUSINESS_ERROR_01803", 
            "残高不足エラー。");

    /**
     * 口座開設中チェックエラー。<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_01807 = errorMgr.defineErrorInfo(
            1807,
            "BUSINESS_ERROR_01807", 
            "口座開設中チェックエラー。");

    /**
     * FX顧客を取得できません。<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_01808 = errorMgr.defineErrorInfo(
            1808,
            "BUSINESS_ERROR_01808", 
            "FX顧客を取得できません。");

    /**
     * 補欠者のデータが存在しません。<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_01809 = errorMgr.defineErrorInfo(
            1809,
            "BUSINESS_ERROR_01809", 
            "補欠者のデータが存在しません。");

    /**
     * 顧客に該当するIPO申告のチェック。<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_01810 = errorMgr.defineErrorInfo(
            1810,
            "BUSINESS_ERROR_01810", 
            "IPO申告が有効でない場合、IPO申告（繰上抽選）不可です。");

    /**
     * 顧客に該当するIPO申告のチェック。<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_01811 = errorMgr.defineErrorInfo(
            1811,
            "BUSINESS_ERROR_01811", 
            "当選数量が購入申込数量を超えている場合、IPO申告（繰上抽選）不可です。");

    /**
     * 銘柄コードチェック。<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_01814 = errorMgr.defineErrorInfo(
            1814,
            "BUSINESS_ERROR_01814", 
            "商品で”全商品”、”株式”以外が選択されている場合は、銘柄コードは指定できません。");

    /**
     * 出来るまで注文有効期限のチェック。<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_01815 = errorMgr.defineErrorInfo(
            1815,
            "BUSINESS_ERROR_01815", 
            "出来るまで注文注文有効期限のチェックエラー。");

    /**
     * 連絡情報チェック。<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_01816 = errorMgr.defineErrorInfo(
            1816,
            "BUSINESS_ERROR_01816", 
            "連絡情報が未指定です。");

    /**
     * 連絡種別チェック。<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_01817 = errorMgr.defineErrorInfo(
            1817,
            "BUSINESS_ERROR_01817", 
            "連絡種別が未指定です。");

    /**
     * 連絡種別チェック。<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_01818 = errorMgr.defineErrorInfo(
            1818,
            "BUSINESS_ERROR_01818", 
            "連絡種別が半角数字以外の値です。");

    /**
     * 連絡種別チェック。<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_01819 = errorMgr.defineErrorInfo(
            1819,
            "BUSINESS_ERROR_01819", 
            "連絡種別のサイズが不正です。");

    /**
     * 識別コードチェック。<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_01820 = errorMgr.defineErrorInfo(
            1820,
            "BUSINESS_ERROR_01820", 
            "識別コードの値が半角数字以外の値です。");

    /**
     * 識別コードチェック。<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_01821 = errorMgr.defineErrorInfo(
            1821,
            "BUSINESS_ERROR_01821", 
            "識別コードのサイズが不正です。");

    /**
     * 受付日時チェック。<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_01822 = errorMgr.defineErrorInfo(
            1822,
            "BUSINESS_ERROR_01822", 
            "受付日時（自）は受付日時（至）より大きいです。");

    /**
     * メール登録状況チェック。<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_01823 = errorMgr.defineErrorInfo(
            1823,
            "BUSINESS_ERROR_01823", 
            "確認メールの送信区分を「送信する」としてサービスの提供を開始しようとした場合、確認メールがメールテーブルに登録されていません。");

    /**
     * メール登録状況チェック。<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_01824 = errorMgr.defineErrorInfo(
            1824,
            "BUSINESS_ERROR_01824", 
            "契約期限メールの送信区分を「送信する」としてサービスの提供を開始しようとした場合、契約期限メールがメールテーブルに登録されていません。");

    /**
     * チェック対象に含まれる予約語のチェック。<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_01825 = errorMgr.defineErrorInfo(
            1825,
            "BUSINESS_ERROR_01825", 
            "予約語：入力区分と予約語：入力区分末尾の順序が不正です。");

    /**
     * チェック対象に含まれる予約語のチェック。<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_01826 = errorMgr.defineErrorInfo(
            1826,
            "BUSINESS_ERROR_01826", 
            "予約語：入力区分と予約語：入力区分末尾の間に数値以外のものがあります。");

    /**
     * チェック対象に含まれる予約語のチェック。<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_01827 = errorMgr.defineErrorInfo(
            1827,
            "BUSINESS_ERROR_01827", 
            "URLに含まれる予約語は「サービス利用予約語変換」で定義された予約語と異なります。");

    /**
     * チェック対象に含まれる予約語のチェック。<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_01828 = errorMgr.defineErrorInfo(
            1828,
            "BUSINESS_ERROR_01828", 
            "第２URLに含まれる予約語は「サービス利用予約語変換」で定義された予約語と異なります。");

    /**
     * チェック対象に含まれる予約語のチェック。<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_01829 = errorMgr.defineErrorInfo(
            1829,
            "BUSINESS_ERROR_01829", 
            "送信パラメータ一覧に含まれる予約語は「サービス利用予約語変換」で定義された予約語と異なります。");

    /**
     * 暗号化顧客コードのチェック。<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_01830 = errorMgr.defineErrorInfo(
            1830,
            "BUSINESS_ERROR_01830", 
            "送信パラメータに暗号化顧客コードの指定がありません。");

    /**
     * ハッシュ値の件数チェック。<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_01831 = errorMgr.defineErrorInfo(
            1831,
            "BUSINESS_ERROR_01831", 
            "ハッシュ計算用文字列の指定に誤りがあります。");

    /**
     * ハッシュ値の件数チェック。<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_01832 = errorMgr.defineErrorInfo(
            1832,
            "BUSINESS_ERROR_01832", 
            "ハッシュ計算用文字列の有効要素数が不正です。");

    /**
     * 第２URLのチェック。<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_01833 = errorMgr.defineErrorInfo(
            1833,
            "BUSINESS_ERROR_01833", 
            "第2URLが未入力です。");

    /**
     * 登録区分のチェック。<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_01834 = errorMgr.defineErrorInfo(
            1834,
            "BUSINESS_ERROR_01834", 
            "変更顧客一覧の登録区分が未指定です。");

    /**
     * 出金日のチェック。<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_01835 = errorMgr.defineErrorInfo(
            1835,
            "BUSINESS_ERROR_01835", 
            "出金日の指定に誤りがあります。");

    /**
     * 出金日のチェック。<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_01836 = errorMgr.defineErrorInfo(
            1836,
            "BUSINESS_ERROR_01836", 
            "現在時刻は16:00以上の場合、発注日の翌営業日は注文内容.出金日以上の場合、エラーになります。");

    /**
     * 信用口座区分のチェック。<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_01837 = errorMgr.defineErrorInfo(
            1837,
            "BUSINESS_ERROR_01837", 
            "信用口座区分が未指定です。");

    /**
     * 信用口座区分のチェック。<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_01838 = errorMgr.defineErrorInfo(
            1838,
            "BUSINESS_ERROR_01838", 
            "信用口座区分が存在しないコード値です。");

    /**
     * 先物OP口座区分（大証）のチェック。<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_01839 = errorMgr.defineErrorInfo(
            1839,
            "BUSINESS_ERROR_01839", 
            "先物OP口座区分（大証）が未指定です。");

    /**
     * 先物OP口座区分（大証）のチェック。<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_01840 = errorMgr.defineErrorInfo(
            1840,
            "BUSINESS_ERROR_01840", 
            "先物OP口座区分（大証）が存在しないコード値です。");

    /**
     * ハッシュ計算方式区分のチェック。<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_01841 = errorMgr.defineErrorInfo(
            1841,
            "BUSINESS_ERROR_01841", 
            "ハッシュ計算方式区分が未指定です。");

    /**
     * ハッシュ計算方式区分のチェック。<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_01842 = errorMgr.defineErrorInfo(
            1842,
            "BUSINESS_ERROR_01842", 
            "ハッシュ計算方式区分が存在しないコード値です。");

    /**
     * ハッシュ計算手順区分のチェック。<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_01843 = errorMgr.defineErrorInfo(
            1843,
            "BUSINESS_ERROR_01843", 
            "ハッシュ計算手順区分が未指定です。");

    /**
     * ハッシュ計算手順区分のチェック。<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_01844 = errorMgr.defineErrorInfo(
            1844,
            "BUSINESS_ERROR_01844", 
            "ハッシュ計算手順区分が存在しないコード値です。");

    /**
     * 送信方法@区分のチェック。<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_01845 = errorMgr.defineErrorInfo(
            1845,
            "BUSINESS_ERROR_01845", 
            "送信方法@区分が未指定です。");

    /**
     * 送信方法@区分のチェック。<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_01846 = errorMgr.defineErrorInfo(
            1846,
            "BUSINESS_ERROR_01846", 
            "送信方法@区分が存在しないコード値です。");

    /**
     * 送信方法@区分のチェック。<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_01847 = errorMgr.defineErrorInfo(
            1847,
            "BUSINESS_ERROR_01847", 
            "送信パラメータ区分が未指定です。");

    /**
     * 送信パラメータ区分のチェック。<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_01848 = errorMgr.defineErrorInfo(
            1848,
            "BUSINESS_ERROR_01848", 
            "送信パラメータ区分が存在しないコード値です。");

    /**
     * 暗号化顧客コード区分のチェック。<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_01849 = errorMgr.defineErrorInfo(
            1849,
            "BUSINESS_ERROR_01849", 
            "暗号化顧客コード区分が未指定です。");

    /**
     * 暗号化顧客コード区分のチェック。<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_01850 = errorMgr.defineErrorInfo(
            1850,
            "BUSINESS_ERROR_01850", 
            "暗号化顧客コード区分が存在しないコード値です。");

    /**
     * ハッシュ値一覧のチェック。<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_01851 = errorMgr.defineErrorInfo(
            1851,
            "BUSINESS_ERROR_01851", 
            "ハッシュ値一覧の利用キー種別区分が未指定です。");

    /**
     * ハッシュ値一覧のチェック。<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_01852 = errorMgr.defineErrorInfo(
            1852,
            "BUSINESS_ERROR_01852", 
            "ハッシュ値一覧の利用キーが未指定です。");

    /**
     * 送信パラメータ一覧のチェック。<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_01853 = errorMgr.defineErrorInfo(
            1853,
            "BUSINESS_ERROR_01853", 
            "送信パラメータ一覧の利用キー種別区分が未指定です。");

    /**
     * 送信パラメータ一覧のチェック。<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_01854 = errorMgr.defineErrorInfo(
            1854,
            "BUSINESS_ERROR_01854", 
            "送信パラメータ一覧の利用キーが未指定です。");

    /**
     * ハッシュ計算方式区分、ハッシュ計算手順区分のチェック。<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_01855 = errorMgr.defineErrorInfo(
            1855,
            "BUSINESS_ERROR_01855", 
            "ハッシュ計算方式区分あるいはハッシュ計算手順区分の指定に誤りがあります。");

    /**
     * ハッシュ値一覧のチェック。<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_01856 = errorMgr.defineErrorInfo(
            1856,
            "BUSINESS_ERROR_01856", 
            "ハッシュ計算用文字列が未入力です。");

    /**
     * ハッシュ値一覧のチェック。<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_01857 = errorMgr.defineErrorInfo(
            1857,
            "BUSINESS_ERROR_01857", 
            "ハッシュ計算用文字列の全てが無効となっています。");

    /**
     * 送信パラメータ一覧のチェック。<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_01858 = errorMgr.defineErrorInfo(
            1858,
            "BUSINESS_ERROR_01858", 
            "送信パラメータが未入力です。");

    /**
     * 送信パラメータ一覧のチェック。<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_01859 = errorMgr.defineErrorInfo(
            1859,
            "BUSINESS_ERROR_01859", 
            "送信パラメータの指定に誤りがあります。");

    /**
     * 同意書文言のチェック。<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_01860 = errorMgr.defineErrorInfo(
            1860,
            "BUSINESS_ERROR_01860", 
            "ステータスが「停止中」ではないの場合は、同意書文言が未指定です。");

    /**
     * 募集枠のチェック。<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_01861 = errorMgr.defineErrorInfo(
            1861,
            "BUSINESS_ERROR_01861", 
            "募集期間情報の募集枠のサイズが不正です。");

    /**
     * 補足入力チェック。<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_01862 = errorMgr.defineErrorInfo(
            1862,
            "BUSINESS_ERROR_01862", 
            "抽選設定が“有”の場合は、入札額が未指定です。");

    /**
     * 外部システムが受付可能時間外です。<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_01863 = errorMgr.defineErrorInfo(
            1863,
            "BUSINESS_ERROR_01863", 
            "外部システムが受付可能時間外です。");

    /**
     * FX口座開設チェックエラー。<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_01865 = errorMgr.defineErrorInfo(
            1865,
            "BUSINESS_ERROR_01865", 
            "為替保証金口座が既に開設済みです。");

    /**
     * FX口座開設チェックエラー。<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_01866 = errorMgr.defineErrorInfo(
            1866,
            "BUSINESS_ERROR_01866", 
            "為替保証金口座が未開設です。");

    /**
     * FX口座開設チェックエラー。<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_01867 = errorMgr.defineErrorInfo(
            1867,
            "BUSINESS_ERROR_01867", 
            "為替保証金口座が取引不可の状態です。");

    /**
     * MRF口座開設チェックエラー。<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_01868 = errorMgr.defineErrorInfo(
            1868,
            "BUSINESS_ERROR_01868", 
            "MRF口座が開設済みです。");

    /**
     * 銘柄チェック。<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_01869 = errorMgr.defineErrorInfo(
            1869,
            "BUSINESS_ERROR_01869", 
            "銘柄チェックエラー。");

    /**
     * CSVファ@イル行のチェック。<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_01870 = errorMgr.defineErrorInfo(
            1870,
            "BUSINESS_ERROR_01870", 
            "CSVファ@イル行の要素数が０です。");

    /**
     * CSVファ@イル行のチェック。<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_01871 = errorMgr.defineErrorInfo(
            1871,
            "BUSINESS_ERROR_01871", 
            "CSVファ@イル行は最大処理件数を越えています。");

    /**
     * 発注条件のチェック。<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_01872 = errorMgr.defineErrorInfo(
            1872,
            "BUSINESS_ERROR_01872", 
            "発注条件区分が“0：指定なし”の場合は、逆指値用発注条件単価が指定不可です。");

    /**
     * 発注条件のチェック。<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_01873 = errorMgr.defineErrorInfo(
            1873,
            "BUSINESS_ERROR_01873", 
            "発注条件区分が“0：指定なし”の場合は、逆指値用発注条件演算子が指定不可です。");

    /**
     * 発注条件のチェック。<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_01874 = errorMgr.defineErrorInfo(
            1874,
            "BUSINESS_ERROR_01874", 
            "発注条件区分が“0：指定なし”の場合は、W指値用発注条件単価が指定不可です。");

    /**
     * 発注条件のチェック。<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_01875 = errorMgr.defineErrorInfo(
            1875,
            "BUSINESS_ERROR_01875", 
            "発注条件区分が“0：指定なし”の場合は、W指値用発注条件演算子が指定不可です。");

    /**
     * 発注条件のチェック。<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_01876 = errorMgr.defineErrorInfo(
            1876,
            "BUSINESS_ERROR_01876", 
            "発注条件区分が“0：指定なし”の場合は、W指値用注文単価区分が指定不可です。");

    /**
     * 発注条件のチェック。<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_01877 = errorMgr.defineErrorInfo(
            1877,
            "BUSINESS_ERROR_01877", 
            "発注条件区分が“0：指定なし”の場合は、W指値用注文単価が指定不可です。");

    /**
     * 発注条件のチェック。<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_01878 = errorMgr.defineErrorInfo(
            1878,
            "BUSINESS_ERROR_01878", 
            "発注条件区分が“1：逆指値”の場合は、W指値用発注条件単価が指定不可です。");

    /**
     * 発注条件のチェック。<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_01879 = errorMgr.defineErrorInfo(
            1879,
            "BUSINESS_ERROR_01879", 
            "発注条件区分が“1：逆指値”の場合は、W指値用発注条件演算子が指定不可です。");

    /**
     * 発注条件のチェック。<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_01880 = errorMgr.defineErrorInfo(
            1880,
            "BUSINESS_ERROR_01880", 
            "発注条件区分が“1：逆指値”の場合は、W指値用注文単価区分が指定不可です。");

    /**
     * 発注条件のチェック。<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_01881 = errorMgr.defineErrorInfo(
            1881,
            "BUSINESS_ERROR_01881", 
            "発注条件区分が“1：逆指値”の場合は、W指値用注文単価が指定不可です。");

    /**
     * 発注条件のチェック。<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_01882 = errorMgr.defineErrorInfo(
            1882,
            "BUSINESS_ERROR_01882", 
            "発注条件区分が“2：W指値”の場合は、逆指値用発注条件単価が指定不可です。");

    /**
     * 発注条件のチェック。<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_01883 = errorMgr.defineErrorInfo(
            1883,
            "BUSINESS_ERROR_01883", 
            "発注条件区分が“2：W指値”の場合は、逆指値用発注条件演算子が指定不可です。");

    /**
     * カテゴリー番号チェック。<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_01884 = errorMgr.defineErrorInfo(
            1884,
            "BUSINESS_ERROR_01884", 
            "カテゴリー番号が数字以外の値です。");

    /**
     * 申込IDチェック。<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_01885 = errorMgr.defineErrorInfo(
            1885,
            "BUSINESS_ERROR_01885", 
            "申込IDが数字以外の値です。");

    /**
     * カテゴリー名チェック。<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_01886 = errorMgr.defineErrorInfo(
            1886,
            "BUSINESS_ERROR_01886", 
            "カテゴリー名が未指定です。");

    /**
     * 景品名チェック。<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_01887 = errorMgr.defineErrorInfo(
            1887,
            "BUSINESS_ERROR_01887", 
            "景品名が未指定です。");

    /**
     * 代用評価低下率数字チェックエラー。<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_01888 = errorMgr.defineErrorInfo(
            1888,
            "BUSINESS_ERROR_01888", 
            "代用評価低下率は数字を入力してください。");

    /**
     * 代用評価低下率範囲チェックエラー。<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_01889 = errorMgr.defineErrorInfo(
            1889,
            "BUSINESS_ERROR_01889", 
            "代用評価低下率は0以上100以下で入力してください。");

    /**
     * 代用評価低下率小数チェックエラー。<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_01890 = errorMgr.defineErrorInfo(
            1890,
            "BUSINESS_ERROR_01890", 
            "代用評価低下率は小数点第2位までで入力してください。");

    /**
     * 預り証券評価制区分未指定エラー。<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_01891 = errorMgr.defineErrorInfo(
            1891,
            "BUSINESS_ERROR_01891", 
            "預り証券評価制区分が未指定(null)です。");

    /**
     * 預り証券評価制変更顧客チェックエラー。<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_01892 = errorMgr.defineErrorInfo(
            1892,
            "BUSINESS_ERROR_01892", 
            "信用顧客は預り証券評価性に変更できません。");

    /**
     * 顧客余力条件ID未指定エラー。<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_01893 = errorMgr.defineErrorInfo(
            1893,
            "BUSINESS_ERROR_01893", 
            "顧客余力条件IDが未指定(null)または不正な値です。");

    /**
     * 取引停止区分ID未指定エラー。<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_01894 = errorMgr.defineErrorInfo(
            1894,
            "BUSINESS_ERROR_01894", 
            "取引停止区分が未指定(null)です。");

    /**
     * 信用新規建余力区分未指定エラー。<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_01895 = errorMgr.defineErrorInfo(
            1895,
            "BUSINESS_ERROR_01895", 
            "信用新規建余力区分が未指定(null)です。");

    /**
     * 先物OP新規建余力区分未指定エラー。<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_01896 = errorMgr.defineErrorInfo(
            1896,
            "BUSINESS_ERROR_01896", 
            "先物OP新規建余力区分が未指定(null)です。");

    /**
     * 出金余力区分未指定エラー。<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_01897 = errorMgr.defineErrorInfo(
            1897,
            "BUSINESS_ERROR_01897", 
            "出金余力区分が未指定(null)です。");

    /**
     * その他商品買付余力区分未指定エラー。<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_01898 = errorMgr.defineErrorInfo(
            1898,
            "BUSINESS_ERROR_01898", 
            "その他商品買付余力区分が未指定(null)です。");

    /**
     * 検索期間チェックエラー。<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_01899 = errorMgr.defineErrorInfo(
            1899,
            "BUSINESS_ERROR_01899", 
            "検索期間の指定が不正です。");

    /**
     * 停止開始日未指定エラー。<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_01900 = errorMgr.defineErrorInfo(
            1900,
            "BUSINESS_ERROR_01900", 
            "停止開始日が未指定(null)です。");

    /**
     * 停止終了日未指定エラー。<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_01901 = errorMgr.defineErrorInfo(
            1901,
            "BUSINESS_ERROR_01901", 
            "停止終了日が未指定(null)です。");

    /**
     * 停止期間チェックエラー。<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_01902 = errorMgr.defineErrorInfo(
            1902,
            "BUSINESS_ERROR_01902", 
            "停止期間の指定が不正です。");

    /**
     * 保証金自動振替停止ID数字チェックエラー。<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_01903 = errorMgr.defineErrorInfo(
            1903,
            "BUSINESS_ERROR_01903", 
            "保証金自動振替停止IDが未指定(null)または存在しない値です。");

    /**
     * 保証金自動振替停止登録済チェックエラー。<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_01904 = errorMgr.defineErrorInfo(
            1904,
            "BUSINESS_ERROR_01904", 
            "既に保証金自動振替停止登録済である。");

    /**
     * 申込抽選区分のチェック。<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_01905 = errorMgr.defineErrorInfo(
            1905,
            "BUSINESS_ERROR_01905", 
            "申込抽選区分が未指定です。");

    /**
     * 利用期間料金情報のチェック。<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_01906 = errorMgr.defineErrorInfo(
            1906,
            "BUSINESS_ERROR_01906", 
            "提供形式が無料提供の場合は、利用期間料金情報が指定不可です。");

    /**
     * 試用期間単位区分のチェック。<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_01908 = errorMgr.defineErrorInfo(
            1908,
            "BUSINESS_ERROR_01908", 
            "提供形式が無料提供の場合は、試用期間単位区分が指定不可です。");

    /**
     * 試用期間のチェック。<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_01909 = errorMgr.defineErrorInfo(
            1909,
            "BUSINESS_ERROR_01909", 
            "提供形式が無料提供の場合は、試用期間が指定不可です。");

    /**
     * パスワード１が未入力です。<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_01910 = errorMgr.defineErrorInfo(
            1910,
            "BUSINESS_ERROR_01910", 
            "パスワード１が未入力です。");

    /**
     * パスワード２が未入力です。<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_01911 = errorMgr.defineErrorInfo(
            1911,
            "BUSINESS_ERROR_01911", 
            "パスワード２が未入力です。");

    /**
     * コード（文字列）のチェック。<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_01912 = errorMgr.defineErrorInfo(
            1912,
            "BUSINESS_ERROR_01912", 
            "扱者コード（文字列）の長さが不正です。");

    /**
     * コード（文字列）のチェック。<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_01913 = errorMgr.defineErrorInfo(
            1913,
            "BUSINESS_ERROR_01913", 
            "扱者コード（文字列）の文字種が不正です。");

    /**
     * コード（文字列）のチェック。<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_01914 = errorMgr.defineErrorInfo(
            1914,
            "BUSINESS_ERROR_01914", 
            "コード（文字列）のチェック方式に定義されていないものが指定された。");

    /**
     * コード（文字列）のチェック。<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_01915 = errorMgr.defineErrorInfo(
            1915,
            "BUSINESS_ERROR_01915", 
            "パスワード（文字列）の長さが不正です。");

    /**
     * コード（文字列）のチェック。<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_01916 = errorMgr.defineErrorInfo(
            1916,
            "BUSINESS_ERROR_01916", 
            "パスワード（文字列）の文字種が不正です。");

    /**
     * コード（文字列）のチェック。<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_01917 = errorMgr.defineErrorInfo(
            1917,
            "BUSINESS_ERROR_01917", 
            "管理者コード（文字列）の長さが不正です。");

    /**
     * コード（文字列）のチェック。<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_01918 = errorMgr.defineErrorInfo(
            1918,
            "BUSINESS_ERROR_01918", 
            "管理者コード（文字列）の文字種が不正です。");

    /**
     * 保有資産IDチェックエラー。<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_01919 = errorMgr.defineErrorInfo(
            1919,
            "BUSINESS_ERROR_01919", 
            "保有資産IDが未指定(null)です。");

    /**
     * 変更後簿価単価チェックエラー。<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_01920 = errorMgr.defineErrorInfo(
            1920,
            "BUSINESS_ERROR_01920", 
            "入力された簿価単価が不正な値です。");

    /**
     * 計算後簿価チェックエラー。<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_01921 = errorMgr.defineErrorInfo(
            1921,
            "BUSINESS_ERROR_01921", 
            "入力した簿価単価の値が大きすぎます。(計算後の簿価の桁数が12桁以上)");

    /**
     * 逆指値用プレミアム／原資産価格のチェック。<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_01922 = errorMgr.defineErrorInfo(
            1922,
            "BUSINESS_ERROR_01922", 
            "発注条件区分が“0：指定なし”の場合は、逆指値用プレミアム／原資産価格が指定不可です。");

    /**
     * 逆指値用プレミアム／原資産価格のチェック。<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_01923 = errorMgr.defineErrorInfo(
            1923,
            "BUSINESS_ERROR_01923", 
            "発注条件区分が“2：W指値”の場合は、逆指値用プレミアム／原資産価格が指定不可です。");

    /**
     * Ｗ指値用プレミアム／原資産価格のチェック。<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_01924 = errorMgr.defineErrorInfo(
            1924,
            "BUSINESS_ERROR_01924", 
            "発注条件区分が“0：指定なし”の場合は、Ｗ指値用プレミアム／原資産価格が指定不可です。");

    /**
     * Ｗ指値用プレミアム／原資産価格のチェック。<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_01925 = errorMgr.defineErrorInfo(
            1925,
            "BUSINESS_ERROR_01925", 
            "発注条件区分が“1：逆指値”の場合は、Ｗ指値用プレミアム／原資産価格が指定不可です。");

    /**
     * 同一抽選情報への重複申込エラー。<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_01926 = errorMgr.defineErrorInfo(
            1926,
            "BUSINESS_ERROR_01926", 
            "同一の抽選情報（同じ申込期間）に二つ目の申込をしようとしている場合のエラー。");

    /**
     * 未提供サービス指定エラー。<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_01927 = errorMgr.defineErrorInfo(
            1927,
            "BUSINESS_ERROR_01927", 
            "サービスが停止中の時のエラー（サービス起動）。");

    /**
     * 二階建チェックエラー。<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_01928 = errorMgr.defineErrorInfo(
            1928,
            "BUSINESS_ERROR_01928", 
            "二階建チェックエラー。");

    /**
     * 買付預り金不足。<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_01929 = errorMgr.defineErrorInfo(
            1929,
            "BUSINESS_ERROR_01929", 
            "買付預り金不足。");

    /**
     * 売付預り金不足。<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_01930 = errorMgr.defineErrorInfo(
            1930,
            "BUSINESS_ERROR_01930", 
            "売付預り金不足。");

    /**
     * 保有資産残数量チェックエラー。<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_01931 = errorMgr.defineErrorInfo(
            1931,
            "BUSINESS_ERROR_01931", 
            "保有資産残数量チェックエラー。");

    /**
     * 総建株数チェックエラー。<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_01932 = errorMgr.defineErrorInfo(
            1932,
            "BUSINESS_ERROR_01932", 
            "注文株数が決済可能総建株数を超えました。");

    /**
     * 立会外分売注文訂正不可。<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_01933 = errorMgr.defineErrorInfo(
            1933,
            "BUSINESS_ERROR_01933", 
            "立会外分売注文は訂正できません。");

    /**
     * 建株残数量チェックエラー。<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_01934 = errorMgr.defineErrorInfo(
            1934,
            "BUSINESS_ERROR_01934", 
            "建株残数量チェックエラー。");

    /**
     * 新規建預り金不足。<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_01935 = errorMgr.defineErrorInfo(
            1935,
            "BUSINESS_ERROR_01935", 
            "新規建預り金不足。");

    /**
     * 顧客コード指定エラー。<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_01936 = errorMgr.defineErrorInfo(
            1936,
            "BUSINESS_ERROR_01936", 
            "指定された対象顧客種別の場合、顧客コードは入力できません。");

    /**
     * 振込先金融機@関が登録されていません。<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_01937 = errorMgr.defineErrorInfo(
            1937,
            "BUSINESS_ERROR_01937", 
            "振込先金融機@関が登録されていません。");

    /**
     * 注文状態のチェック 。<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_01938 = errorMgr.defineErrorInfo(
            1938,
            "BUSINESS_ERROR_01938", 
            "注文状態が受付済でない、かつ、約定区分が初期値でないの場合は、注文取消が不可です。");

    /**
     * 外株振替取消のチェック。<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_01940 = errorMgr.defineErrorInfo(
            1940,
            "BUSINESS_ERROR_01940", 
            "処理中の振替注文が存在するので、取消できません。");

    /**
     * 外株口座開設可能のチェック。<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_01941 = errorMgr.defineErrorInfo(
            1941,
            "BUSINESS_ERROR_01941", 
            "外国株式口座は既に開設されています。");

    /**
     * 外株口座開設可能のチェック。<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_01943 = errorMgr.defineErrorInfo(
            1943,
            "BUSINESS_ERROR_01943", 
            "顧客情報のメールアドレスが登録されていません。");

    /**
     * 外株振替可能のチェック。<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_01944 = errorMgr.defineErrorInfo(
            1944,
            "BUSINESS_ERROR_01944", 
            "外国株式口座が開設されていません。");

    /**
     * 初回振替のチェック。<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_01946 = errorMgr.defineErrorInfo(
            1946,
            "BUSINESS_ERROR_01946", 
            "初回振替の場合、振替金額が下限金額に達していません。");

    /**
     * 数値項目のチェック。<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_01947 = errorMgr.defineErrorInfo(
            1947,
            "BUSINESS_ERROR_01947", 
            "入力の数値項目が半角数字以外の値です。");

    /**
     * 一覧に表示するデータがありません。<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_01948 = errorMgr.defineErrorInfo(
            1948,
            "BUSINESS_ERROR_01948", 
            "一覧に表示するデータがありません。");

    /**
     * 外株口座開設質問に対する回答の整合性チェック。<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_01949 = errorMgr.defineErrorInfo(
            1949,
            "BUSINESS_ERROR_01949", 
            "同意されてない質問があります。");

    /**
     * ダウンロード件数のチェック。<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_01950 = errorMgr.defineErrorInfo(
            1950,
            "BUSINESS_ERROR_01950", 
            "ダウンロード件数が未入力です。");

    /**
     * ダウンロード件数のチェック。<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_01951 = errorMgr.defineErrorInfo(
            1951,
            "BUSINESS_ERROR_01951", 
            "ダウンロード件数が不正な値です。");

    /**
     * 顧客IDのチェック。<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_01952 = errorMgr.defineErrorInfo(
            1952,
            "BUSINESS_ERROR_01952", 
            "顧客IDが未入力です。");

    /**
     * 外株口座番号のチェック。<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_01953 = errorMgr.defineErrorInfo(
            1953,
            "BUSINESS_ERROR_01953", 
            "外株口座番号が未入力です。");

    /**
     * 外株口座番号のチェック。<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_01954 = errorMgr.defineErrorInfo(
            1954,
            "BUSINESS_ERROR_01954", 
            "外株口座番号が不正な値です。");

    /**
     * 処理区分チェック。<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_01955 = errorMgr.defineErrorInfo(
            1955,
            "BUSINESS_ERROR_01955", 
            "この注文は取消できません。");

    /**
     * 電子交付申請チェック。<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_01956 = errorMgr.defineErrorInfo(
            1956,
            "BUSINESS_ERROR_01956", 
            "電子交付の申請がされていません。");

    /**
     * 該当する件数がダウンロード件数を超えています。<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_01957 = errorMgr.defineErrorInfo(
            1957,
            "BUSINESS_ERROR_01957", 
            "該当する件数がダウンロード件数を超えています。");

    /**
     * 電子鳩エラー。<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_01959 = errorMgr.defineErrorInfo(
            1959,
            "BUSINESS_ERROR_01959", 
            "電子鳩システム呼出でエラーが発生しました。");

    /**
     * 申込受付区分が存在しないコード値です。<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_01960 = errorMgr.defineErrorInfo(
            1960,
            "BUSINESS_ERROR_01960", 
            "申込受付区分が存在しないコード値です。");

    /**
     * 失効通知処理エラー（業務エラー）。<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_01961 = errorMgr.defineErrorInfo(
            1961,
            "BUSINESS_ERROR_01961", 
            "失効通知処理が失敗しました（業務エラー）。");

    /**
     * 取消通知処理エラー（業務エラー）。<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_01962 = errorMgr.defineErrorInfo(
            1962,
            "BUSINESS_ERROR_01962", 
            "取消通知処理が失敗しました（業務エラー）。");

    /**
     * 外株用暗証番号のチェック。<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_01963 = errorMgr.defineErrorInfo(
            1963,
            "BUSINESS_ERROR_01963", 
            "入力された外株用暗証番号と外株用暗証番号（確認用）は一致しない。");

    /**
     * 為替保証金口座開設管理一覧画面のチェック。<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_01964 = errorMgr.defineErrorInfo(
            1964,
            "BUSINESS_ERROR_01964", 
            "選択されたMRF口座開設の条件に合致するデータが存在しません。");

    /**
     * 電子鳩エラー。<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_01965 = errorMgr.defineErrorInfo(
            1965,
            "BUSINESS_ERROR_01965", 
            "電子鳩未登録エラー");

    /**
     * 取引銘柄エラー。<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_01966 = errorMgr.defineErrorInfo(
            1966,
            "BUSINESS_ERROR_01966", 
            "指定した銘柄は上場されておりません。");

    /**
     * IDがnullでない場合のエラー。<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_01967 = errorMgr.defineErrorInfo(
            1967,
            "BUSINESS_ERROR_01967", 
            "IDが指定不可です。");

    /**
     * 電子鳩チェックフラグがnullの場合エラー。<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_01968 = errorMgr.defineErrorInfo(
            1968,
            "BUSINESS_ERROR_01968", 
            "電子鳩チェックフラグが未指定です。");

    /**
     * 他のアップロードプロセスが起動中。<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_01969 = errorMgr.defineErrorInfo(
            1969,
            "BUSINESS_ERROR_01969", 
            "他のアップロードプロセスが起動中。");

    /**
     * ポイントチェック。<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_01970 = errorMgr.defineErrorInfo(
            1970,
            "BUSINESS_ERROR_01970", 
            "ポイントが不足しています。");

    /**
     * 代用評価低下率範囲エラー。<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_01971 = errorMgr.defineErrorInfo(
            1971,
            "BUSINESS_ERROR_01971", 
            "代用評価低下率は、0から20の間で指定してください。");

    /**
     * 2重受信エラー。<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_01972 = errorMgr.defineErrorInfo(
            1972,
            "BUSINESS_ERROR_01972", 
            "2重受信エラー。");

    /**
     * スレッド番号の指定なし。<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_01974 = errorMgr.defineErrorInfo(
            1974,
            "BUSINESS_ERROR_01974", 
            "スレッド番号の指定なし。");

    /**
     * 該当注文は受付未済／変更の受付済／発注中の状態。<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_01975 = errorMgr.defineErrorInfo(
            1975,
            "BUSINESS_ERROR_01975", 
            "該当注文は受付未済／変更の受付済／発注中の状態。");

    /**
     * データ重複エラー（顧客銘柄別取引停止）。<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_01976 = errorMgr.defineErrorInfo(
            1976,
            "BUSINESS_ERROR_01976", 
            "データ重複エラー（顧客銘柄別取引停止）。");

    /**
     * 該当顧客銘柄別取引停止データなし。<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_01977 = errorMgr.defineErrorInfo(
            1977,
            "BUSINESS_ERROR_01977", 
            "該当顧客銘柄別取引停止データなし。");

    /**
     * 変更後の登録値(予定)が未入力。<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_01978 = errorMgr.defineErrorInfo(
            1978,
            "BUSINESS_ERROR_01978", 
            "変更後の登録値(予定)が未入力。");

    /**
     * 変更後の適用期間From/Toが未入力。<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_01979 = errorMgr.defineErrorInfo(
            1979,
            "BUSINESS_ERROR_01979", 
            "変更後の適用期間From/Toが未入力。");

    /**
     * 該当するIPO申告がありません。<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_01980 = errorMgr.defineErrorInfo(
            1980,
            "BUSINESS_ERROR_01980", 
            "該当するIPO申告がありません。");

    /**
     * 該当するIPO申告履歴がありません。<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_01981 = errorMgr.defineErrorInfo(
            1981,
            "BUSINESS_ERROR_01981", 
            "該当するIPO申告履歴がありません。");

    /**
     * 同一銘柄既存行のスケジュールが未定です。<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_01982 = errorMgr.defineErrorInfo(
            1982,
            "BUSINESS_ERROR_01982", 
            "同一銘柄既存行のスケジュールが未定です。");

    /**
     * 要求元区分が未定義の値。<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_01983 = errorMgr.defineErrorInfo(
            1983,
            "BUSINESS_ERROR_01983", 
            "要求元区分が未定義の値。");

    /**
     * 電子鳩システム障害中。<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_01984 = errorMgr.defineErrorInfo(
            1984,
            "BUSINESS_ERROR_01984", 
            "[電子鳩システム障害中]障害中注文不可。");

    /**
     * 電子鳩システム障害中。<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_01985 = errorMgr.defineErrorInfo(
            1985,
            "BUSINESS_ERROR_01985", 
            "[電子鳩システム障害中]障害中代理入力不可。");

    /**
     * 申告相当額の最大桁数チェック。<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_01986 = errorMgr.defineErrorInfo(
            1986,
            "BUSINESS_ERROR_01986", 
            "申告相当額の整数部が最大値（１２桁）を超えています。");

    /**
     * 顧客コード指定エラー。<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_01987 = errorMgr.defineErrorInfo(
            1987,
            "BUSINESS_ERROR_01987", 
            "顧客コードに対応する顧客は登録されていません。");

    /**
     * 電子鳩システム稼動中エラー。<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_01988 = errorMgr.defineErrorInfo(
            1988,
            "BUSINESS_ERROR_01988", 
            "[電子鳩システム稼動中]目論見書閲覧未済顧客の代理入力不可。");

    /**
     * サービス登録エラー。<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_01989 = errorMgr.defineErrorInfo(
            1989,
            "BUSINESS_ERROR_01989", 
            "指定のサービスは既に登録されています。");

    /**
     * 銘柄名入力エラー(桁数超過)。<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_01990 = errorMgr.defineErrorInfo(
            1990,
            "BUSINESS_ERROR_01990", 
            "銘柄名の入力可能長を超えました。");

    /**
     * IPO申告チェック。<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_01991 = errorMgr.defineErrorInfo(
            1991,
            "BUSINESS_ERROR_01991", 
            "当選数量が購入申込数量を超えている場合、IPO申告（繰上抽選）不可。");

    /**
     * 指定AP起動中（二重起動エラー）。<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_01992 = errorMgr.defineErrorInfo(
            1992,
            "BUSINESS_ERROR_01992", 
            "指定AP起動中（二重起動エラー）。");

    /**
     * CSVファ@イルの要素数が不正。<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_01993 = errorMgr.defineErrorInfo(
            1993,
            "BUSINESS_ERROR_01993", 
            "CSVファ@イルの要素数が不正。");

    /**
     * アップロードファ@イル内の項目の長のチェック。<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_01994 = errorMgr.defineErrorInfo(
            1994,
            "BUSINESS_ERROR_01994", 
            "アップロードファ@イル内の項目の長と要求されたレングスが違います。");

    /**
     * 繰上抽選対象者チェックエラー。<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_01995 = errorMgr.defineErrorInfo(
            1995,
            "BUSINESS_ERROR_01995", 
            "繰上抽選対象者の顧客ではありません。");

    /**
     * 銘柄名称チェックエラー。<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_01996 = errorMgr.defineErrorInfo(
            1996,
            "BUSINESS_ERROR_01996", 
            "銘柄名に半角カナ文字が含まれています。");

    /**
     * 時価取得エラー。<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_01997 = errorMgr.defineErrorInfo(
            1997,
            "BUSINESS_ERROR_01997", 
            "指定の銘柄は成行注文に必要な市場時価がないため、指値注文のみ受付可能です。");

    /**
     * 抽選区分チェック。<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_01998 = errorMgr.defineErrorInfo(
            1998,
            "BUSINESS_ERROR_01998", 
            "アップロードCSVにある抽選区分は選択された区分と不一致です。");

    /**
     * サービス登録エラー。<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_01999 = errorMgr.defineErrorInfo(
            1999,
            "BUSINESS_ERROR_01999", 
            "当該サービスへの申込登録が未済です。");

    /**
     * 投信乗換エラー。<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_02000 = errorMgr.defineErrorInfo(
            2000,
            "BUSINESS_ERROR_02000", 
            "買付可能口数が0口以下なので乗換ができません。");

    /**
     * 数量チェックエラー（買建）。<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_02001 = errorMgr.defineErrorInfo(
            2001,
            "BUSINESS_ERROR_02001", 
            "買建注文1回あたりの注文可能な数量の上限値を越えています。");

    /**
     * 数量チェックエラー（売建）。<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_02002 = errorMgr.defineErrorInfo(
            2002,
            "BUSINESS_ERROR_02002", 
            "売建注文1回あたりの注文可能な数量の上限値を越えています。");

    /**
     * 数量チェックエラー（買返済）。<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_02003 = errorMgr.defineErrorInfo(
            2003,
            "BUSINESS_ERROR_02003", 
            "買返済注文1回あたりの注文可能な数量の上限値を越えています。");

    /**
     * 数量チェックエラー（売返済）。<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_02004 = errorMgr.defineErrorInfo(
            2004,
            "BUSINESS_ERROR_02004", 
            "売返済注文1回あたりの注文可能な数量の上限値を越えています。");

    /**
     * 数量チェックエラー（総売建）。<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_02005 = errorMgr.defineErrorInfo(
            2005,
            "BUSINESS_ERROR_02005", 
            "総売建数量の上限値を超えています。");

    /**
     * 数量チェックエラー（総建玉）。<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_02006 = errorMgr.defineErrorInfo(
            2006,
            "BUSINESS_ERROR_02006", 
            "総建玉数量の上限値を超えています。");

    /**
     * 顧客コード（自）チェック。<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_02007 = errorMgr.defineErrorInfo(
            2007,
            "BUSINESS_ERROR_02007", 
            "顧客コード（自）が未入力です。");

    /**
     * 顧客コード（至）チェック。<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_02008 = errorMgr.defineErrorInfo(
            2008,
            "BUSINESS_ERROR_02008", 
            "顧客コード（至）が未入力です。");

    /**
     * 送信フラグチェック。<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_02009 = errorMgr.defineErrorInfo(
            2009,
            "BUSINESS_ERROR_02009", 
            "送信フラグが不正な値です。");

    /**
     * 注文チェックエラー。<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_02010 = errorMgr.defineErrorInfo(
            2010,
            "BUSINESS_ERROR_02010", 
            "同一発注日に口数指定解約と金額指定解約の両方の注文はできません。");

    /**
     * 注文チェック。<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_02011 = errorMgr.defineErrorInfo(
            2011,
            "BUSINESS_ERROR_02011", 
            "注文ＩＤに該当する注文単位が存在しません。");

    /**
     * 注文チェック。<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_02012 = errorMgr.defineErrorInfo(
            2012,
            "BUSINESS_ERROR_02012", 
            "注文ＩＤに該当する注文単位が２件以上あったので、エラーになります。");

    /**
     * 売付為替レートチェック。<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_02013 = errorMgr.defineErrorInfo(
            2013,
            "BUSINESS_ERROR_02013", 
            "売付為替レートが数値以外の値です。");

    /**
     * 売付基準為替エラー。<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_02014 = errorMgr.defineErrorInfo(
            2014,
            "BUSINESS_ERROR_02014", 
            "売付基準為替エラー。");

    /**
     * 売付約定為替エラー。<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_02015 = errorMgr.defineErrorInfo(
            2015,
            "BUSINESS_ERROR_02015", 
            "売付約定為替エラー。");

    /**
     * 買付為替レートチェック。<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_02016 = errorMgr.defineErrorInfo(
            2016,
            "BUSINESS_ERROR_02016", 
            "買付為替レートが数値以外の値です。");

    /**
     * 買付基準為替エラー。<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_02017 = errorMgr.defineErrorInfo(
            2017,
            "BUSINESS_ERROR_02017", 
            "買付基準為替エラー。");

    /**
     * 買付約定為替エラー。<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_02018 = errorMgr.defineErrorInfo(
            2018,
            "BUSINESS_ERROR_02018", 
            "買付約定為替エラー。");

    /**
     * 発注日チェック。<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_02019 = errorMgr.defineErrorInfo(
            2019,
            "BUSINESS_ERROR_02019", 
            "発注日は営業日ではありません。");

    /**
     * 変更後区分チェック。<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_02020 = errorMgr.defineErrorInfo(
            2020,
            "BUSINESS_ERROR_02020", 
            "入力された変更後区分が不正なコード値です。");

    /**
     * 約定単価チェック。<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_02021 = errorMgr.defineErrorInfo(
            2021,
            "BUSINESS_ERROR_02021", 
            "約定単価が未入力です。");

    /**
     * 約定単価チェック。<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_02022 = errorMgr.defineErrorInfo(
            2022,
            "BUSINESS_ERROR_02022", 
            "約定単価が数値以外の値です。");

    /**
     * 約定単価チェック。<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_02023 = errorMgr.defineErrorInfo(
            2023,
            "BUSINESS_ERROR_02023", 
            "約定単価が0以下の値です。");

    /**
     * 約定単価チェック。<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_02024 = errorMgr.defineErrorInfo(
            2024,
            "BUSINESS_ERROR_02024", 
            "約定単価は整数部６桁，小数部５桁の範囲外です。");

    /**
     * 約定数量チェック。<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_02025 = errorMgr.defineErrorInfo(
            2025,
            "BUSINESS_ERROR_02025", 
            "約定数量が未入力です。");

    /**
     * 約定数量チェック。<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_02026 = errorMgr.defineErrorInfo(
            2026,
            "BUSINESS_ERROR_02026", 
            "約定数量が9桁以内の整数値ではありません。");

    /**
     * 年月チェック。<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_02027 = errorMgr.defineErrorInfo(
            2027,
            "BUSINESS_ERROR_02027", 
            "年月が未入力です。");

    /**
     * 年月チェック。<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_02028 = errorMgr.defineErrorInfo(
            2028,
            "BUSINESS_ERROR_02028", 
            "年月が6桁以外です。");

    /**
     * 年月チェック。<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_02029 = errorMgr.defineErrorInfo(
            2029,
            "BUSINESS_ERROR_02029", 
            "年月が日付として有りえない値です。");

    /**
     * コスト区分チェック。<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_02030 = errorMgr.defineErrorInfo(
            2030,
            "BUSINESS_ERROR_02030", 
            "コスト区分が不正な値です。");

    /**
     * 出来終了実施一覧チェック。<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_02031 = errorMgr.defineErrorInfo(
            2031,
            "BUSINESS_ERROR_02031", 
            "出来終了実施一覧が存在しません。");

    /**
     * 運用コードチェック。<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_02032 = errorMgr.defineErrorInfo(
            2032,
            "BUSINESS_ERROR_02032", 
            "運用コードが未入力です。");

    /**
     * 運用コードチェック。<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_02033 = errorMgr.defineErrorInfo(
            2033,
            "BUSINESS_ERROR_02033", 
            "運用コードが７桁の半角英数字ではありません。");

    /**
     * 運用コードチェック。<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_02034 = errorMgr.defineErrorInfo(
            2034,
            "BUSINESS_ERROR_02034", 
            "運用コードの左2byteが”NW”ではありません。");

    /**
     * 運用コードチェック。<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_02035 = errorMgr.defineErrorInfo(
            2035,
            "BUSINESS_ERROR_02035", 
            "運用コードの右5byteが数字ではありません。");

    /**
     * 約定為替レートチェック。<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_02036 = errorMgr.defineErrorInfo(
            2036,
            "BUSINESS_ERROR_02036", 
            "約定為替レートが未指定です。");

    /**
     * 約定為替レートチェック。<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_02037 = errorMgr.defineErrorInfo(
            2037,
            "BUSINESS_ERROR_02037", 
            "約定為替レートの有効桁数が、整数部３桁，小数部４桁の範囲外です。");

    /**
     * 出来情報一覧チェック。<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_02038 = errorMgr.defineErrorInfo(
            2038,
            "BUSINESS_ERROR_02038", 
            "出来情報一覧が未入力です。");

    /**
     * 約定番号チェック。<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_02039 = errorMgr.defineErrorInfo(
            2039,
            "BUSINESS_ERROR_02039", 
            "約定番号が未入力です。");

    /**
     * 約定番号チェック。<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_02040 = errorMgr.defineErrorInfo(
            2040,
            "BUSINESS_ERROR_02040", 
            "約定番号が有効桁数3桁以内の正の整数値ではありません。");

    /**
     * 約定ＩＤチェック。<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_02041 = errorMgr.defineErrorInfo(
            2041,
            "BUSINESS_ERROR_02041", 
            "約定ＩＤが未入力です。");

    /**
     * 約定ＩＤチェック。<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_02042 = errorMgr.defineErrorInfo(
            2042,
            "BUSINESS_ERROR_02042", 
            "約定ＩＤが数値以外の値です。");

    /**
     * 注文受付取消一覧チェック。<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_02043 = errorMgr.defineErrorInfo(
            2043,
            "BUSINESS_ERROR_02043", 
            "注文受付取消一覧が未入力です。");

    /**
     * 市場未選択エラー。<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_02045 = errorMgr.defineErrorInfo(
            2045,
            "BUSINESS_ERROR_02045", 
            "市場未選択エラー。");

    /**
     * 市場コード未設定。<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_02046 = errorMgr.defineErrorInfo(
            2046,
            "BUSINESS_ERROR_02046", 
            "市場コード未設定エラー。");

    /**
     * 発注日条件設定エラー。<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_02047 = errorMgr.defineErrorInfo(
            2047,
            "BUSINESS_ERROR_02047", 
            "発注日条件設定エラー。");

    /**
     * エラー解除対象機@能区分チェック。<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_02048 = errorMgr.defineErrorInfo(
            2048,
            "BUSINESS_ERROR_02048", 
            "エラー解除対象機@能区分が未入力です。");

    /**
     * エラー解除対象機@能区分チェック。<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_02049 = errorMgr.defineErrorInfo(
            2049,
            "BUSINESS_ERROR_02049", 
            "エラー解除対象機@能区分が不正な値です。");

    /**
     * 銘柄名（漢字）チェック。<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_02050 = errorMgr.defineErrorInfo(
            2050,
            "BUSINESS_ERROR_02050", 
            "銘柄名（漢字）のバイト長が不正です。");

    /**
     * 買付停止区分チェック。<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_02051 = errorMgr.defineErrorInfo(
            2051,
            "BUSINESS_ERROR_02051", 
            "買付停止区分の値が不正です。");

    /**
     * 売付停止区分チェック。<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_02052 = errorMgr.defineErrorInfo(
            2052,
            "BUSINESS_ERROR_02052", 
            "売付停止区分の値が不正です。");

    /**
     * 現地銘柄コードチェック。<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_02053 = errorMgr.defineErrorInfo(
            2053,
            "BUSINESS_ERROR_02053", 
            "現地銘柄コードが未指定です。");

    /**
     * 現地銘柄コードチェック。<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_02054 = errorMgr.defineErrorInfo(
            2054,
            "BUSINESS_ERROR_02054", 
            "現地銘柄コードの入力可能長を超えました。");

    /**
     * 現地銘柄コードチェック。<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_02055 = errorMgr.defineErrorInfo(
            2055,
            "BUSINESS_ERROR_02055", 
            "現地銘柄コードが数値以外の値です。");

    /**
     * 買付単位チェック。<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_02056 = errorMgr.defineErrorInfo(
            2056,
            "BUSINESS_ERROR_02056", 
            "買付単位が未入力です。");

    /**
     * 買付単位チェック。<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_02057 = errorMgr.defineErrorInfo(
            2057,
            "BUSINESS_ERROR_02057", 
            "買付単位が数値以外の値です。");

    /**
     * 買付単位チェック。<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_02058 = errorMgr.defineErrorInfo(
            2058,
            "BUSINESS_ERROR_02058", 
            "買付単位の入力可能長を超えました。");

    /**
     * 買付単位チェック。<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_02059 = errorMgr.defineErrorInfo(
            2059,
            "BUSINESS_ERROR_02059", 
            "買付単位がマイナスの値です。");

    /**
     * 最低買付単位チェック。<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_02060 = errorMgr.defineErrorInfo(
            2060,
            "BUSINESS_ERROR_02060", 
            "最低買付単位が未入力です。");

    /**
     * 最低買付単位チェック。<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_02061 = errorMgr.defineErrorInfo(
            2061,
            "BUSINESS_ERROR_02061", 
            "最低買付単位が数値以外の値です。");

    /**
     * 最低買付単位チェック。<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_02062 = errorMgr.defineErrorInfo(
            2062,
            "BUSINESS_ERROR_02062", 
            "最低買付単位の入力可能長を超えました。");

    /**
     * 最低買付単位チェック。<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_02063 = errorMgr.defineErrorInfo(
            2063,
            "BUSINESS_ERROR_02063", 
            "最低買付単位がマイナスの値です。");

    /**
     * 売付単位チェック。<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_02064 = errorMgr.defineErrorInfo(
            2064,
            "BUSINESS_ERROR_02064", 
            "売付単位が未入力です。");

    /**
     * 売付単位チェック。<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_02065 = errorMgr.defineErrorInfo(
            2065,
            "BUSINESS_ERROR_02065", 
            "売付単位が数値以外の値です。");

    /**
     * 売付単位チェック。<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_02066 = errorMgr.defineErrorInfo(
            2066,
            "BUSINESS_ERROR_02066", 
            "売付単位の入力可能長を超えました。");

    /**
     * 売付単位チェック。<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_02067 = errorMgr.defineErrorInfo(
            2067,
            "BUSINESS_ERROR_02067", 
            "売付単位がマイナスの値です。");

    /**
     * 最低売付単位チェック。<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_02068 = errorMgr.defineErrorInfo(
            2068,
            "BUSINESS_ERROR_02068", 
            "最低売付単位が未入力です。");

    /**
     * 最低売付単位チェック。<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_02069 = errorMgr.defineErrorInfo(
            2069,
            "BUSINESS_ERROR_02069", 
            "最低売付単位が数値以外の値です。");

    /**
     * 最低売付単位チェック。<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_02070 = errorMgr.defineErrorInfo(
            2070,
            "BUSINESS_ERROR_02070", 
            "最低売付単位の入力可能長を超えました。");

    /**
     * 最低売付単位チェック。<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_02071 = errorMgr.defineErrorInfo(
            2071,
            "BUSINESS_ERROR_02071", 
            "最低売付単位がマイナスの値です。");

    /**
     * 約定入力情報チェック。<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_02072 = errorMgr.defineErrorInfo(
            2072,
            "BUSINESS_ERROR_02072", 
            "約定入力情報が未入力です。");

    /**
     * 適用期間（自）チェック。<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_02073 = errorMgr.defineErrorInfo(
            2073,
            "BUSINESS_ERROR_02073", 
            "適用期間（自）が未入力です。");

    /**
     * 取引金額（自）チェック。<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_02074 = errorMgr.defineErrorInfo(
            2074,
            "BUSINESS_ERROR_02074", 
            "取引金額（自）が未入力です。");

    /**
     * 取引金額（自）チェック。<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_02075 = errorMgr.defineErrorInfo(
            2075,
            "BUSINESS_ERROR_02075", 
            "取引金額（自）がマイナスの値です。");

    /**
     * 取引金額（自）チェック。<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_02076 = errorMgr.defineErrorInfo(
            2076,
            "BUSINESS_ERROR_02076", 
            "取引金額（自）の値が不正です。");

    /**
     * 取引金額（至）チェック。<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_02077 = errorMgr.defineErrorInfo(
            2077,
            "BUSINESS_ERROR_02077", 
            "取引金額（至）がマイナスの値です。");

    /**
     * 取引金額（至）チェック。<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_02078 = errorMgr.defineErrorInfo(
            2078,
            "BUSINESS_ERROR_02078", 
            "取引金額（至）の値が不正です。");

    /**
     * 取引金額（自）と取引金額（至）チェック。<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_02079 = errorMgr.defineErrorInfo(
            2079,
            "BUSINESS_ERROR_02079", 
            "取引金額（自）が取引金額（至）を超えました。");

    /**
     * 徴収率チェック。<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_02080 = errorMgr.defineErrorInfo(
            2080,
            "BUSINESS_ERROR_02080", 
            "徴収率が未入力です。");

    /**
     * 徴収率チェック。<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_02081 = errorMgr.defineErrorInfo(
            2081,
            "BUSINESS_ERROR_02081", 
            "徴収率がマイナスの値です。");

    /**
     * 徴収率チェック。<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_02082 = errorMgr.defineErrorInfo(
            2082,
            "BUSINESS_ERROR_02082", 
            "徴収率の値が不正です。");

    /**
     * 付加金額チェック。<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_02083 = errorMgr.defineErrorInfo(
            2083,
            "BUSINESS_ERROR_02083", 
            "付加金額が未入力です。");

    /**
     * 付加金額チェック。<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_02084 = errorMgr.defineErrorInfo(
            2084,
            "BUSINESS_ERROR_02084", 
            "付加金額がマイナスの値です。");

    /**
     * 付加金額チェック。<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_02085 = errorMgr.defineErrorInfo(
            2085,
            "BUSINESS_ERROR_02085", 
            "付加金額の値が不正です。");

    /**
     * 該当注文なし。<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_02086 = errorMgr.defineErrorInfo(
            2086,
            "BUSINESS_ERROR_02086", 
            "該当注文が存在しません。");

    /**
     * 取引規制中チェック。<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_02087 = errorMgr.defineErrorInfo(
            2087,
            "BUSINESS_ERROR_02087", 
            "外国株式取引銘柄が取引規制中です。");

    /**
     * 外国株式取引銘柄チェック。<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_02088 = errorMgr.defineErrorInfo(
            2088,
            "BUSINESS_ERROR_02088", 
            "外国株式取引銘柄が取得できません。");

    /**
     * 取引銘柄上場チェック。<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_02089 = errorMgr.defineErrorInfo(
            2089,
            "BUSINESS_ERROR_02089", 
            "該当取引銘柄が非上場です。");

    /**
     * 取引銘柄の上場期間チェック。<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_02090 = errorMgr.defineErrorInfo(
            2090,
            "BUSINESS_ERROR_02090", 
            "該当取引銘柄が取引可能な銘柄ではありません。");

    /**
     * 成行注文チェック。<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_02091 = errorMgr.defineErrorInfo(
            2091,
            "BUSINESS_ERROR_02091", 
            "成行注文で単価が指定不可です");

    /**
     * 指値注文チェック。<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_02092 = errorMgr.defineErrorInfo(
            2092,
            "BUSINESS_ERROR_02092", 
            "指値注文で単価が未指定です。");

    /**
     * 注文単価サイズチェック。<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_02093 = errorMgr.defineErrorInfo(
            2093,
            "BUSINESS_ERROR_02093", 
            "注文単価サイズが不正です。");

    /**
     * 注文株数チェック。<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_02094 = errorMgr.defineErrorInfo(
            2094,
            "BUSINESS_ERROR_02094", 
            "注文株数が最低注文株数未満です。");

    /**
     * 外貨決済不可能エラー。<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_02095 = errorMgr.defineErrorInfo(
            2095,
            "BUSINESS_ERROR_02095", 
            "外貨決済不可能エラー。");

    /**
     * 特定口座未開設エラー。<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_02096 = errorMgr.defineErrorInfo(
            2096,
            "BUSINESS_ERROR_02096", 
            "特定口座未開設エラー。");

    /**
     * 為替レート未登録エラー。<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_02097 = errorMgr.defineErrorInfo(
            2097,
            "BUSINESS_ERROR_02097", 
            "為替レート未登録エラー。");

    /**
     * 既約定日チェック。<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_02098 = errorMgr.defineErrorInfo(
            2098,
            "BUSINESS_ERROR_02098", 
            "約定日が既約定日と一致しません。");

    /**
     * 現地受渡日チェック。<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_02099 = errorMgr.defineErrorInfo(
            2099,
            "BUSINESS_ERROR_02099", 
            "各約定の現地受渡日と指定した現地受渡日を比較して、不一致な物が存在します。");

    /**
     * 約定単価チェック。<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_02100 = errorMgr.defineErrorInfo(
            2100,
            "BUSINESS_ERROR_02100", 
            "買注文で、約定単価が指値を超えています。");

    /**
     * 約定単価チェック。<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_02101 = errorMgr.defineErrorInfo(
            2101,
            "BUSINESS_ERROR_02101", 
            "売注文で、約定単価が指値より小さいです。");

    /**
     * 注文期限区分が一致しないため、訂正不可です。<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_02102 = errorMgr.defineErrorInfo(
            2102,
            "BUSINESS_ERROR_02102", 
            "注文期限区分が一致しないため、訂正不可です。");

    /**
     * 訂正入力なし。<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_02103 = errorMgr.defineErrorInfo(
            2103,
            "BUSINESS_ERROR_02103", 
            "訂正入力されていません。");

    /**
     * 約定数量チェック。<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_02104 = errorMgr.defineErrorInfo(
            2104,
            "BUSINESS_ERROR_02104", 
            "約定数量が訂正数量を超えています。");

    /**
     * 注文数量チェック。<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_02105 = errorMgr.defineErrorInfo(
            2105,
            "BUSINESS_ERROR_02105", 
            "注文数量が訂正数量より小さいです。");

    /**
     * 発注条件チェック。<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_02106 = errorMgr.defineErrorInfo(
            2106,
            "BUSINESS_ERROR_02106", 
            "発注条件が訂正不可です。");

    /**
     * 発注済みの逆指値注文チェック。<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_02107 = errorMgr.defineErrorInfo(
            2107,
            "BUSINESS_ERROR_02107", 
            "発注済みの逆指値注文なので、発注条件、発注条件演算子、訂正発注条件単価が訂正不可です。");

    /**
     * 市場取扱不可エラー。<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_02108 = errorMgr.defineErrorInfo(
            2108,
            "BUSINESS_ERROR_02108", 
            "市場取扱不可エラー。");

    /**
     * 売付可能数量チェック。<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_02109 = errorMgr.defineErrorInfo(
            2109,
            "BUSINESS_ERROR_02109", 
            "入力された数量が売付可能数量を超えています。");

    /**
     * 変更後概算簿価単価が不正な値。<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_02110 = errorMgr.defineErrorInfo(
            2110,
            "BUSINESS_ERROR_02110", 
            "変更後概算簿価単価が不正な値になっています。");

    /**
     * 決済区分がnullである。<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_02111 = errorMgr.defineErrorInfo(
            2111,
            "BUSINESS_ERROR_02111", 
            "決済区分がnullです。");

    /**
     * 決済区分チェック。<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_02112 = errorMgr.defineErrorInfo(
            2112,
            "BUSINESS_ERROR_02112", 
            "決済区分が未定義の値です。");

    /**
     * 特定口座区分チェック。<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_02113 = errorMgr.defineErrorInfo(
            2113,
            "BUSINESS_ERROR_02113", 
            "特定口座区分がnullです。");

    /**
     * 特定口座区分チェック。<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_02114 = errorMgr.defineErrorInfo(
            2114,
            "BUSINESS_ERROR_02114", 
            "特定口座区分が未定義の値です。");

    /**
     * 執行条件チェック。<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_02115 = errorMgr.defineErrorInfo(
            2115,
            "BUSINESS_ERROR_02115", 
            "執行条件が未定義の値です。");

    /**
     * 発注条件がnullである。<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_02116 = errorMgr.defineErrorInfo(
            2116,
            "BUSINESS_ERROR_02116", 
            "発注条件がnullです。");

    /**
     * 発注条件チェック。<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_02117 = errorMgr.defineErrorInfo(
            2117,
            "BUSINESS_ERROR_02117", 
            "発注条件が未定義の値です。");

    /**
     * 注文単価チェック。<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_02118 = errorMgr.defineErrorInfo(
            2118,
            "BUSINESS_ERROR_02118", 
            "注文単価が未指定です。");

    /**
     * 注文単価チェック。<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_02119 = errorMgr.defineErrorInfo(
            2119,
            "BUSINESS_ERROR_02119", 
            "注文単価が数値以外の値です。");

    /**
     * 注文単価チェック。<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_02120 = errorMgr.defineErrorInfo(
            2120,
            "BUSINESS_ERROR_02120", 
            "注文単価が0以下の値です。");

    /**
     * 発注条件区分が“逆指値”の場合は、逆指値用発注条件単価が未指定です。<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_02121 = errorMgr.defineErrorInfo(
            2121,
            "BUSINESS_ERROR_02121", 
            "発注条件区分が“逆指値”なのに、逆指値用発注条件単価が未指定です。");

    /**
     * 発注条件区分が“逆指値”の場合は、逆指値用発注条件単価が0以下の値です。<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_02122 = errorMgr.defineErrorInfo(
            2122,
            "BUSINESS_ERROR_02122", 
            "発注条件区分が“逆指値”で、逆指値用発注条件単価が0以下の値です。");

    /**
     * 発注条件区分が“逆指値”の場合は、逆指値用発注条件単価が数値以外の値です。<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_02123 = errorMgr.defineErrorInfo(
            2123,
            "BUSINESS_ERROR_02123", 
            "発注条件区分が“逆指値”で、逆指値用発注条件単価が数値以外の値です。");

    /**
     * 発注条件区分が“逆指値”の場合は、逆指値用発注条件単価のサイズが不正です。<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_02124 = errorMgr.defineErrorInfo(
            2124,
            "BUSINESS_ERROR_02124", 
            "発注条件区分が“逆指値”で、逆指値用発注条件単価のサイズが不正です。");

    /**
     * 発注条件区分が“逆指値”の場合は、逆指値用発注条件演算子が未指定です。<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_02125 = errorMgr.defineErrorInfo(
            2125,
            "BUSINESS_ERROR_02125", 
            "発注条件区分が“逆指値”で、逆指値用発注条件演算子が未指定です。");

    /**
     * 発注条件区分が“逆指値”の場合は、逆指値用発注条件演算子が未定義の値です。<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_02126 = errorMgr.defineErrorInfo(
            2126,
            "BUSINESS_ERROR_02126", 
            "発注条件区分が“逆指値”で、逆指値用発注条件演算子が未定義の値です。");

    /**
     * 発注条件区分が“W指値”の場合は、W指値用発注条件演算子が未指定です。<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_02127 = errorMgr.defineErrorInfo(
            2127,
            "BUSINESS_ERROR_02127", 
            "発注条件区分が“W指値”なのに、W指値用発注条件演算子が未指定です。");

    /**
     * 発注条件区分が“W指値”の場合は、W指値用発注条件演算子が未定義の値です。<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_02128 = errorMgr.defineErrorInfo(
            2128,
            "BUSINESS_ERROR_02128", 
            "発注条件区分が“W指値”で、W指値用発注条件演算子が未定義の値です。");

    /**
     * 発注条件区分が“W指値”の場合は、W指値用注文単価区分が未指定です。<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_02129 = errorMgr.defineErrorInfo(
            2129,
            "BUSINESS_ERROR_02129", 
            "発注条件区分が“W指値”なのに、W指値用注文単価区分が未指定です。");

    /**
     * 発注条件区分が“W指値”の場合は、W指値用注文単価区分未定義の値です。<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_02130 = errorMgr.defineErrorInfo(
            2130,
            "BUSINESS_ERROR_02130", 
            "発注条件区分が“W指値”で、W指値用注文単価区分が未定義の値です。");

    /**
     * 発注条件区分が“W指値”の場合は、W指値用発注条件単価が未指定です。<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_02131 = errorMgr.defineErrorInfo(
            2131,
            "BUSINESS_ERROR_02131", 
            "発注条件区分が“W指値”なのに、W指値用発注条件単価が未指定です。");

    /**
     * 発注条件区分が“W指値”の場合は、W指値用発注条件単価が数値以外の値です。<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_02132 = errorMgr.defineErrorInfo(
            2132,
            "BUSINESS_ERROR_02132", 
            "発注条件区分が“W指値”で、W指値用発注条件単価が数値以外の値です。");

    /**
     * 発注条件区分が“W指値”の場合は、W指値用発注条件単価が0以下の値です。<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_02133 = errorMgr.defineErrorInfo(
            2133,
            "BUSINESS_ERROR_02133", 
            "発注条件区分が“W指値”で、W指値用発注条件単価が0以下の値です。");

    /**
     * 発注条件区分が“W指値”の場合は、W指値用発注条件単価のサイズが不正です。<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_02134 = errorMgr.defineErrorInfo(
            2134,
            "BUSINESS_ERROR_02134", 
            "発注条件区分が“W指値”で、W指値用発注条件単価のサイズが不正です。");

    /**
     * W指値用注文単価区分が”指値” の場合は、W指値用注文単価が未指定です。<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_02135 = errorMgr.defineErrorInfo(
            2135,
            "BUSINESS_ERROR_02135", 
            "W指値用注文単価区分が”指値”なのに、W指値用注文単価が未指定です。");

    /**
     * W指値用注文単価区分が”指値” の場合は、W指値用注文単価が数値以外の値です。<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_02136 = errorMgr.defineErrorInfo(
            2136,
            "BUSINESS_ERROR_02136", 
            "W指値用注文単価区分が”指値”で、W指値用注文単価が数値以外の値です。");

    /**
     * W指値用注文単価区分が”指値” の場合は、W指値用注文単価が0以下の値です。<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_02137 = errorMgr.defineErrorInfo(
            2137,
            "BUSINESS_ERROR_02137", 
            "W指値用注文単価区分が”指値”で、W指値用注文単価が0以下の値です。");

    /**
     * W指値用注文単価区分が”指値” の場合は、W指値用注文単価のサイズが不正です。<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_02138 = errorMgr.defineErrorInfo(
            2138,
            "BUSINESS_ERROR_02138", 
            "W指値用注文単価区分が”指値” で、W指値用注文単価のサイズが不正です。");

    /**
     * W指値用注文単価区分が”成行” の場合は、W指値用注文単価が指定不可です。<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_02139 = errorMgr.defineErrorInfo(
            2139,
            "BUSINESS_ERROR_02139", 
            "W指値用注文単価区分が”成行” なので、W指値用注文単価が指定不可です。");

    /**
     * 証券会社コードチェック。<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_02141 = errorMgr.defineErrorInfo(
            2141,
            "BUSINESS_ERROR_02141", 
            "証券会社コードがマイナスの値です。");

    /**
     * 外国株式銘柄チェック。<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_02142 = errorMgr.defineErrorInfo(
            2142,
            "BUSINESS_ERROR_02142", 
            "外国株式銘柄が取得できません。");

    /**
     * 未発注の場合は、約定不可です。<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_02143 = errorMgr.defineErrorInfo(
            2143,
            "BUSINESS_ERROR_02143", 
            "未発注なものなので、約定不可です。");

    /**
     * 出来終了処理済みの場合は、約定不可です。<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_02144 = errorMgr.defineErrorInfo(
            2144,
            "BUSINESS_ERROR_02144", 
            "出来終了処理済みなので、約定不可です。");

    /**
     * 一部出来の場合は、約定不可です。<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_02145 = errorMgr.defineErrorInfo(
            2145,
            "BUSINESS_ERROR_02145", 
            "一部出来なので、約定不可です。");

    /**
     * 約定入力情報が同一でない場合注文データの場合は、変更不可です。<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_02146 = errorMgr.defineErrorInfo(
            2146,
            "BUSINESS_ERROR_02146", 
            "約定入力情報が同一ではない注文データなので、変更不可です。");

    /**
     * 約定入力情報の扱者コードと顧客.扱者コード（SONAR）が不一致です。<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_02147 = errorMgr.defineErrorInfo(
            2147,
            "BUSINESS_ERROR_02147", 
            "約定入力情報の扱者コードと顧客.扱者コード（SONAR）が不一致です。");

    /**
     * 約定入力情報.銘柄コードと現地銘柄コードの両方が未入力です。<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_02148 = errorMgr.defineErrorInfo(
            2148,
            "BUSINESS_ERROR_02148", 
            "約定入力情報.銘柄コードと現地銘柄コードの両方が未入力です。");

    /**
     * 約定日時の日付が営業日でない。<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_02149 = errorMgr.defineErrorInfo(
            2149,
            "BUSINESS_ERROR_02149", 
            "約定日時の日付が営業日ではありません。");

    /**
     * 発注日チェック。<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_02150 = errorMgr.defineErrorInfo(
            2150,
            "BUSINESS_ERROR_02150", 
            "発注日が３営業日前より前の日付です。");

    /**
     * 運用コード,伝票番号,注文数量,注文単価がシステム自動セット項目、入力不可です。<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_02151 = errorMgr.defineErrorInfo(
            2151,
            "BUSINESS_ERROR_02151", 
            "運用コード,伝票番号,注文数量,注文単価がシステム自動セット項目なので、入力不可です。");

    /**
     * 現地手数料チェック。<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_02152 = errorMgr.defineErrorInfo(
            2152,
            "BUSINESS_ERROR_02152", 
            "現地手数料が有効桁数範囲外です。");

    /**
     * 現地取引税チェック。<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_02153 = errorMgr.defineErrorInfo(
            2153,
            "BUSINESS_ERROR_02153", 
            "現地取引税が有効桁数範囲外です。");

    /**
     * その他コスト１チェック。<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_02154 = errorMgr.defineErrorInfo(
            2154,
            "BUSINESS_ERROR_02154", 
            "その他コスト１が有効桁数範囲外です。");

    /**
     * その他コスト２チェック。<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_02155 = errorMgr.defineErrorInfo(
            2155,
            "BUSINESS_ERROR_02155", 
            "その他コスト２が有効桁数範囲外です。");

    /**
     * カレンダー情報一覧チェック。<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_02156 = errorMgr.defineErrorInfo(
            2156,
            "BUSINESS_ERROR_02156", 
            "カレンダー情報一覧が存在しません。");

    /**
     * 日付或いは日付区分チェック。<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_02157 = errorMgr.defineErrorInfo(
            2157,
            "BUSINESS_ERROR_02157", 
            "日付或いは日付区分がnullです。");

    /**
     * 日付区分チェック。<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_02158 = errorMgr.defineErrorInfo(
            2158,
            "BUSINESS_ERROR_02158", 
            "日付区分が未定義の値です。");

    /**
     * 為替情報のレート区分チェック。<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_02159 = errorMgr.defineErrorInfo(
            2159,
            "BUSINESS_ERROR_02159", 
            "為替情報のレート区分が未定義の値です。");

    /**
     * 発注日エラー。<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_02160 = errorMgr.defineErrorInfo(
            2160,
            "BUSINESS_ERROR_02160", 
            "発注日エラー。");

    /**
     * 未発注の場合は、出来不可です。<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_02161 = errorMgr.defineErrorInfo(
            2161,
            "BUSINESS_ERROR_02161", 
            "未発注なので、出来不可です。");

    /**
     * 出来終了処理済みの場合は、出来不可です。<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_02162 = errorMgr.defineErrorInfo(
            2162,
            "BUSINESS_ERROR_02162", 
            "出来終了処理済みなので、出来不可です。");

    /**
     * HOST発注の場合は、出来不可です。<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_02163 = errorMgr.defineErrorInfo(
            2163,
            "BUSINESS_ERROR_02163", 
            "HOST発注なので、出来不可です。");

    /**
     * 出来終了処理済みの場合は、出来約定取消不可です。<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_02164 = errorMgr.defineErrorInfo(
            2164,
            "BUSINESS_ERROR_02164", 
            "出来終了処理済みなので、出来約定取消不可です。");

    /**
     * 外国株式注文単位オブジェクトが取得できない。<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_02165 = errorMgr.defineErrorInfo(
            2165,
            "BUSINESS_ERROR_02165", 
            "外国株式注文単位オブジェクトが取得できません。");

    /**
     * 出来終了後の注文の場合は、注文受付結果アップロード不可です。<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_02166 = errorMgr.defineErrorInfo(
            2166,
            "BUSINESS_ERROR_02166", 
            "出来終了後の注文は、注文受付結果アップロード不可です。");

    /**
     * 注文種別チェック。<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_02167 = errorMgr.defineErrorInfo(
            2167,
            "BUSINESS_ERROR_02167", 
            "注文種別が一致しないため、注文受付結果アップロード不可です。");

    /**
     * 約定方法@チェック。<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_02168 = errorMgr.defineErrorInfo(
            2168,
            "BUSINESS_ERROR_02168", 
            "約定方法@が外国株式注文結果一括入力CSV.約定方法@と一致しません。");

    /**
     * 入力行にエラーがある。<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_02169 = errorMgr.defineErrorInfo(
            2169,
            "BUSINESS_ERROR_02169", 
            "入力行にエラーがあります。");

    /**
     * 約定Ｎｏ．チェック。<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_02170 = errorMgr.defineErrorInfo(
            2170,
            "BUSINESS_ERROR_02170", 
            "約定Ｎｏ．が有効桁数範囲外です。");

    /**
     * 別経路で登録された約定で同一約定通番が既に存在する。<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_02171 = errorMgr.defineErrorInfo(
            2171,
            "BUSINESS_ERROR_02171", 
            "別経路で登録された約定で同一約定通番が既に存在します。");

    /**
     * 約定日時チェック。<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_02172 = errorMgr.defineErrorInfo(
            2172,
            "BUSINESS_ERROR_02172", 
            "約定日時が取得できません。");

    /**
     * 約定No.重複チェック。<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_02173 = errorMgr.defineErrorInfo(
            2173,
            "BUSINESS_ERROR_02173", 
            "指定行番号の運用コードが約定No.と同じ値の行が存在します。");

    /**
     * 部店コードチェック。<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_02174 = errorMgr.defineErrorInfo(
            2174,
            "BUSINESS_ERROR_02174", 
            "部店コードがnullです。");

    /**
     * 部店コードの要素数チェック。<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_02175 = errorMgr.defineErrorInfo(
            2175,
            "BUSINESS_ERROR_02175", 
            "部店コードの要素数が0です。");

    /**
     * 指定された検索条件は出来入力一覧では使用できません。<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_02176 = errorMgr.defineErrorInfo(
            2176,
            "BUSINESS_ERROR_02176", 
            "指定された検索条件は出来入力一覧では使用できません。");

    /**
     * 訂正取消不可。<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_02177 = errorMgr.defineErrorInfo(
            2177,
            "BUSINESS_ERROR_02177", 
            "訂正取消不可。");

    /**
     * 処理対象外データ。<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_02178 = errorMgr.defineErrorInfo(
            2178,
            "BUSINESS_ERROR_02178", 
            "処理対象外データ。");

    /**
     * 商品区分チェック。<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_02182 = errorMgr.defineErrorInfo(
            2182,
            "BUSINESS_ERROR_02182", 
            "商品区分が未指定です。");

    /**
     * 登録データ不整合（重複登録不可）。<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_02183 = errorMgr.defineErrorInfo(
            2183,
            "BUSINESS_ERROR_02183", 
            "登録データ不整合（重複登録不可）。");

    /**
     * 約定日時チェック。<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_02184 = errorMgr.defineErrorInfo(
            2184,
            "BUSINESS_ERROR_02184", 
            "約定日時が未入力です。");

    /**
     * 注文Rev.の値が最大桁数を超過。<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_02185 = errorMgr.defineErrorInfo(
            2185,
            "BUSINESS_ERROR_02185", 
            "注文Rev.の値が最大桁数を超過。");

    /**
     * 約定数量チェック。<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_02186 = errorMgr.defineErrorInfo(
            2186,
            "BUSINESS_ERROR_02186", 
            "約定数量が0以下の値です。");

    /**
     * 発注先切替テーブル設定不正（有効データが複数存在）。<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_02187 = errorMgr.defineErrorInfo(
            2187,
            "BUSINESS_ERROR_02187", 
            "発注先切替テーブル設定不正（有効データが複数存在）。");

    /**
     * ＵＬ対象行が存在しません。<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_02188 = errorMgr.defineErrorInfo(
            2188,
            "BUSINESS_ERROR_02188", 
            "ＵＬ対象行が存在しません。");

    /**
     * 国内手数料（円貨）チェック。<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_02189 = errorMgr.defineErrorInfo(
            2189,
            "BUSINESS_ERROR_02189", 
            "国内手数料（円貨）が有効桁数範囲外です。");

    /**
     * 消費税（円貨）チェック。<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_02190 = errorMgr.defineErrorInfo(
            2190,
            "BUSINESS_ERROR_02190", 
            "消費税（円貨）が有効桁数範囲外です。");

    /**
     * 約定No.が数値以外の値です。<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_02191 = errorMgr.defineErrorInfo(
            2191,
            "BUSINESS_ERROR_02191", 
            "約定No.が数値以外の値です。");

    /**
     * 約定日時のチェック。<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_02192 = errorMgr.defineErrorInfo(
            2192,
            "BUSINESS_ERROR_02192", 
            "約定日時のフォーマットが”yyyyMMddHHmmss”ではありません。");

    /**
     * 約定為替チェック。<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_02193 = errorMgr.defineErrorInfo(
            2193,
            "BUSINESS_ERROR_02193", 
            "約定為替が未指定です。");

    /**
     * 約定為替チェック。<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_02194 = errorMgr.defineErrorInfo(
            2194,
            "BUSINESS_ERROR_02194", 
            "約定為替の有効桁数が、整数部３桁，小数部４桁の範囲外です。");

    /**
     * 為替情報一覧チェック。<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_02195 = errorMgr.defineErrorInfo(
            2195,
            "BUSINESS_ERROR_02195", 
            "為替情報一覧が未入力です。");

    /**
     * 約定為替レートチェック。<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_02196 = errorMgr.defineErrorInfo(
            2196,
            "BUSINESS_ERROR_02196", 
            "約定為替レートが0以下の値です。");

    /**
     * 複数項目同時訂正不可（休憩時間帯）。<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_02197 = errorMgr.defineErrorInfo(
            2197,
            "BUSINESS_ERROR_02197", 
            "複数項目同時訂正不可（休憩時間帯）。");

    /**
     * 生年月日年号が未指定です。<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_02198 = errorMgr.defineErrorInfo(
            2198,
            "BUSINESS_ERROR_02198", 
            "生年月日年号が未指定です。");

    /**
     * 生年月日が未指定です。<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_02199 = errorMgr.defineErrorInfo(
            2199,
            "BUSINESS_ERROR_02199", 
            "生年月日が未指定です。");

    /**
     * 顧客の年齢がプリファ@レンスの値より小さいです。<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_02200 = errorMgr.defineErrorInfo(
            2200,
            "BUSINESS_ERROR_02200", 
            "顧客の年齢がプリファ@レンスの値より小さいです。");

    /**
     * 顧客の年齢がプリファ@レンスの値より小さくないです。<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_02201 = errorMgr.defineErrorInfo(
            2201,
            "BUSINESS_ERROR_02201", 
            "顧客の年齢がプリファ@レンスの値より小さくないです。");

    /**
     * 種別コードチェック。<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_02202 = errorMgr.defineErrorInfo(
            2202,
            "BUSINESS_ERROR_02202", 
            "種別コードが未指定です。");

    /**
     * 該当注文が訂正取消不可です。<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_02203 = errorMgr.defineErrorInfo(
            2203,
            "BUSINESS_ERROR_02203", 
            "該当注文が訂正取消不可です。");

    /**
     * 注文訂正不可の市場です。<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_02204 = errorMgr.defineErrorInfo(
            2204,
            "BUSINESS_ERROR_02204", 
            "注文訂正不可の市場です。");

    /**
     * 切替処理方式区分未指定エラー。<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_02205 = errorMgr.defineErrorInfo(
            2205,
            "BUSINESS_ERROR_02205", 
            "切替処理方式区分が未指定です。");

    /**
     * 切替起動区分進行中エラー。<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_02206 = errorMgr.defineErrorInfo(
            2206,
            "BUSINESS_ERROR_02206", 
            "切替処理進行中の為、切替不可。");

    /**
     * サービス起動区分未指定エラー。<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_02207 = errorMgr.defineErrorInfo(
            2207,
            "BUSINESS_ERROR_02207", 
            "サービス起動区分が未指定です。");

    /**
     * 全訂正処理起動済メッセージ。<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_02208 = errorMgr.defineErrorInfo(
            2208,
            "BUSINESS_ERROR_02208", 
            "既に切替処理起動済です。");

    /**
     * 変換市場コード未指定エラー。<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_02209 = errorMgr.defineErrorInfo(
            2209,
            "BUSINESS_ERROR_02209", 
            "変換市場コードが未指定です。");

    /**
     * 発注経路区分未指定エラー。<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_02210 = errorMgr.defineErrorInfo(
            2210,
            "BUSINESS_ERROR_02210", 
            "発注経路区分が未指定です。");

    /**
     * 切替起動区分未指定エラー。<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_02211 = errorMgr.defineErrorInfo(
            2211,
            "BUSINESS_ERROR_02211", 
            "切替起動区分が未指定です。");

    /**
     * フロント発注システム区分未指定エラー。<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_02212 = errorMgr.defineErrorInfo(
            2212,
            "BUSINESS_ERROR_02212", 
            "フロント発注システム区分が未指定です。");

    /**
     * SONAR全障害エラー。<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_02213 = errorMgr.defineErrorInfo(
            2213,
            "BUSINESS_ERROR_02213", 
            "SONAR全障害の為,切替処理を実行できません。");

    /**
     * 有効フラグ未指定エラー。<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_02214 = errorMgr.defineErrorInfo(
            2214,
            "BUSINESS_ERROR_02214", 
            "有効フラグが未指定です。");

    /**
     * 変更後有効フラグ未指定エラー。<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_02215 = errorMgr.defineErrorInfo(
            2215,
            "BUSINESS_ERROR_02215", 
            "変更後有効フラグが未指定です。");

    /**
     * 発注経路切替対象なしエラー。<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_02216 = errorMgr.defineErrorInfo(
            2216,
            "BUSINESS_ERROR_02216", 
            "発注経路切替対象が存在しません。");

    /**
     * 通知受信日付未指定エラー。<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_02217 = errorMgr.defineErrorInfo(
            2217,
            "BUSINESS_ERROR_02217", 
            "通知受信日付が未指定です。");

    /**
     * 通知受信時刻Fromエラー。<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_02218 = errorMgr.defineErrorInfo(
            2218,
            "BUSINESS_ERROR_02218", 
            "通知受信時刻Fromが不正です。");

    /**
     * 通知受信時刻Toエラー。<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_02219 = errorMgr.defineErrorInfo(
            2219,
            "BUSINESS_ERROR_02219", 
            "通知受信時刻Toが不正です。");

    /**
     * 約定為替レートのチェック。<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_02220 = errorMgr.defineErrorInfo(
            2220,
            "BUSINESS_ERROR_02220", 
            "約定為替レートが数値以外の値です。");

    /**
     * 登録日（自）と登録日チェック。<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_02221 = errorMgr.defineErrorInfo(
            2221,
            "BUSINESS_ERROR_02221", 
            "登録日（自）と登録日（至）両方を未入力か、同時に入力しなければならない。");

    /**
     * 登録日（自）が登録日（至）のチェック。<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_02222 = errorMgr.defineErrorInfo(
            2222,
            "BUSINESS_ERROR_02222", 
            "登録日（自）が登録日（至）を超えています。");

    /**
     * 検索条件区分チェック。<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_02223 = errorMgr.defineErrorInfo(
            2223,
            "BUSINESS_ERROR_02223", 
            "検索条件区分が不正なコード値です。");

    /**
     * ページ内表示行数が未入力です。<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_02224 = errorMgr.defineErrorInfo(
            2224,
            "BUSINESS_ERROR_02224", 
            "ページ内表示行数が未入力です。");

    /**
     * 停止状況登録理由チェック。<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_02225 = errorMgr.defineErrorInfo(
            2225,
            "BUSINESS_ERROR_02225", 
            "停止状況登録理由の文字数が40byteより大きかったです。");

    /**
     * 停止状況登録理由チェック。<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_02226 = errorMgr.defineErrorInfo(
            2226,
            "BUSINESS_ERROR_02226", 
            "停止状況登録理由が全角文字以外が含まれているです。");

    /**
     * 募集最低数量エラー。<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_02227 = errorMgr.defineErrorInfo(
            2227,
            "BUSINESS_ERROR_02227", 
            "募集最低数量エラー(注文数量 ＜ 募集最低数量)。");

    /**
     * 募集単位数量エラー。<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_02228 = errorMgr.defineErrorInfo(
            2228,
            "BUSINESS_ERROR_02228", 
            "募集単位数量エラー(注文数量が募集単位数量で割り切れない)。");

    /**
     * 銘柄IDチェック。<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_02229 = errorMgr.defineErrorInfo(
            2229,
            "BUSINESS_ERROR_02229", 
            "銘柄IDが未指定です。");

    /**
     * 募集数量チェック。<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_02230 = errorMgr.defineErrorInfo(
            2230,
            "BUSINESS_ERROR_02230", 
            "募集数量が未指定です。");

    /**
     * 募集数量チェック。<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_02231 = errorMgr.defineErrorInfo(
            2231,
            "BUSINESS_ERROR_02231", 
            "募集数量が数値以外の値です。");

    /**
     * 募集数量チェック。<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_02232 = errorMgr.defineErrorInfo(
            2232,
            "BUSINESS_ERROR_02232", 
            "募集数量が0以下の値である。");

    /**
     * 募集数量チェック。<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_02233 = errorMgr.defineErrorInfo(
            2233,
            "BUSINESS_ERROR_02233", 
            "募集数量が10桁を超えました。");

    /**
     * 連続注文は値段条件指定不可。<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_02234 = errorMgr.defineErrorInfo(
            2234,
            "BUSINESS_ERROR_02234", 
            "連続注文は値段条件指定不可。");

    /**
     * 連続注文は執行条件指定不可。<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_02235 = errorMgr.defineErrorInfo(
            2235,
            "BUSINESS_ERROR_02235", 
            "連続注文は執行条件指定不可。");

    /**
     * 連続注文は発注条件指定不可。<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_02236 = errorMgr.defineErrorInfo(
            2236,
            "BUSINESS_ERROR_02236", 
            "連続注文は発注条件指定不可。");

    /**
     * 停止状況登録理由変更不可エラー。<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_02237 = errorMgr.defineErrorInfo(
            2237,
            "BUSINESS_ERROR_02237", 
            "停止状況登録理由変更不可エラー。");

    /**
     * SONAR未送信データがあるため変更不可エラー。<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_02238 = errorMgr.defineErrorInfo(
            2238,
            "BUSINESS_ERROR_02238", 
            "SONAR未送信データがあるため変更不可エラー。");

    /**
     * 処理区分が“5：募集”と拡張投信取引銘柄が募集不可の場合は、取扱不可能です。<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_02239 = errorMgr.defineErrorInfo(
            2239,
            "BUSINESS_ERROR_02239", 
            "処理区分が“5：募集”と拡張投信取引銘柄が募集不可の場合は、取扱不可能です。");

    /**
     * 市場閉局後～注文繰越終了までの間は、連続注文受付を不可とする。<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_02240 = errorMgr.defineErrorInfo(
            2240,
            "BUSINESS_ERROR_02240", 
            "市場閉局後～注文繰越終了までの間は、連続注文受付を不可とする。");

    /**
     * 第一ソートキーは商品区分のみ指定可。<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_02241 = errorMgr.defineErrorInfo(
            2241,
            "BUSINESS_ERROR_02241", 
            "第一ソートキーは商品区分のみ指定可。");

    /**
     * 親注文が全部約定していません。（連続注文発注）。<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_02242 = errorMgr.defineErrorInfo(
            2242,
            "BUSINESS_ERROR_02242", 
            "親注文が全部約定していません。（連続注文発注）");

    /**
     * 符号が未指定です。<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_02243 = errorMgr.defineErrorInfo(
            2243,
            "BUSINESS_ERROR_02243", 
            "符号が未指定です。");

    /**
     * 符号の値が不正です。<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_02244 = errorMgr.defineErrorInfo(
            2244,
            "BUSINESS_ERROR_02244", 
            "符号の値が不正です。");

    /**
     * 指定の連続注文取引は、当該注文に設定不可です。<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_02245 = errorMgr.defineErrorInfo(
            2245,
            "BUSINESS_ERROR_02245", 
            "指定の連続注文取引は、当該注文に設定不可です。");

    /**
     * 連続注文最大設定数を超過しています。<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_02247 = errorMgr.defineErrorInfo(
            2247,
            "BUSINESS_ERROR_02247", 
            "連続注文最大設定数を超過しています。");

    /**
     * 連続注文取扱不可です。<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_02248 = errorMgr.defineErrorInfo(
            2248,
            "BUSINESS_ERROR_02248", 
            "連続注文取扱不可です。");

    /**
     * 親注文がクローズ済のためトリガー注文設定不可です。<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_02249 = errorMgr.defineErrorInfo(
            2249,
            "BUSINESS_ERROR_02249", 
            "親注文がクローズ済のためトリガー注文設定不可です。");

    /**
     * 反対取引時の銘柄指定が、親注文と不整合です。<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_02250 = errorMgr.defineErrorInfo(
            2250,
            "BUSINESS_ERROR_02250", 
            "反対取引時の銘柄指定が、親注文と不整合です。");

    /**
     * 連続注文共通情報が未指定です。<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_02251 = errorMgr.defineErrorInfo(
            2251,
            "BUSINESS_ERROR_02251", 
            "連続注文共通情報が未指定です。");

    /**
     * 連続注文取引区分の値が処理対象外です。<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_02252 = errorMgr.defineErrorInfo(
            2252,
            "BUSINESS_ERROR_02252", 
            "連続注文取引区分の値が処理対象外です。");

    /**
     * 連続注文取引区分が、連続注文の±指値指定不可の区分です。<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_02253 = errorMgr.defineErrorInfo(
            2253,
            "BUSINESS_ERROR_02253", 
            "連続注文取引区分が、連続注文の±指値指定不可の区分です。");

    /**
     * 単価調整値と注文単価区分の指定が不整合です。<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_02254 = errorMgr.defineErrorInfo(
            2254,
            "BUSINESS_ERROR_02254", 
            "単価調整値と注文単価区分の指定が不整合です。");

    /**
     * 取引区分が連続注文の処理対象外です。<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_02255 = errorMgr.defineErrorInfo(
            2255,
            "BUSINESS_ERROR_02255", 
            "取引区分が連続注文の処理対象外です。");

    /**
     * 反対取引時は銘柄コード指定は必須です。<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_02256 = errorMgr.defineErrorInfo(
            2256,
            "BUSINESS_ERROR_02256", 
            "反対取引時は銘柄コード指定は必須です。");

    /**
     * 反対取引時は市場コード指定は必須です。<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_02257 = errorMgr.defineErrorInfo(
            2257,
            "BUSINESS_ERROR_02257", 
            "反対取引時は市場コード指定は必須です。");

    /**
     * （親注文）注文IDが未指定です。<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_02258 = errorMgr.defineErrorInfo(
            2258,
            "BUSINESS_ERROR_02258", 
            "（親注文）注文IDが未指定です。");

    /**
     * （親注文）注文IDの値が不正です。<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_02259 = errorMgr.defineErrorInfo(
            2259,
            "BUSINESS_ERROR_02259", 
            "（親注文）注文IDの値が不正です。");

    /**
     * 単価調整値が未指定です。<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_02260 = errorMgr.defineErrorInfo(
            2260,
            "BUSINESS_ERROR_02260", 
            "単価調整値が未指定です。");

    /**
     * 単価調整値が不正です。<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_02261 = errorMgr.defineErrorInfo(
            2261,
            "BUSINESS_ERROR_02261", 
            "単価調整値が不正です。");

    /**
     * 連続注文取引区分が未指定です。<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_02262 = errorMgr.defineErrorInfo(
            2262,
            "BUSINESS_ERROR_02262", 
            "連続注文取引区分が未指定です。");

    /**
     * 連続注文取引区分が存在しないコード値です。<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_02263 = errorMgr.defineErrorInfo(
            2263,
            "BUSINESS_ERROR_02263", 
            "連続注文取引区分が存在しないコード値です。");

    /**
     * 指定方法@（募集）の指定に誤りがあります。<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_02264 = errorMgr.defineErrorInfo(
            2264,
            "BUSINESS_ERROR_02264", 
            "指定方法@（募集）の指定に誤りがあります。");

    /**
     * 募集可能区分（当日発注分）の指定に誤りがあります。<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_02265 = errorMgr.defineErrorInfo(
            2265,
            "BUSINESS_ERROR_02265", 
            "募集可能区分（当日発注分）の指定に誤りがあります。");

    /**
     * 募集可能区分（翌日発注分）の指定に誤りがあります。<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_02266 = errorMgr.defineErrorInfo(
            2266,
            "BUSINESS_ERROR_02266", 
            "募集可能区分（翌日発注分）の指定に誤りがあります。");

    /**
     * 買付制限区分の指定に誤りがあります。<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_02267 = errorMgr.defineErrorInfo(
            2267,
            "BUSINESS_ERROR_02267", 
            "買付制限区分の指定に誤りがあります。");

    /**
     * 乗換先銘柄IDが未指定です。<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_02268 = errorMgr.defineErrorInfo(
            2268,
            "BUSINESS_ERROR_02268", 
            "乗換先銘柄IDが未指定です。");

    /**
     * 解約口数拘束率を超過しています。<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_02269 = errorMgr.defineErrorInfo(
            2269,
            "BUSINESS_ERROR_02269", 
            "解約口数拘束率を超過しています");

    /**
     * 乗換可能残高口数不足エラー。<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_02270 = errorMgr.defineErrorInfo(
            2270,
            "BUSINESS_ERROR_02270", 
            "乗換可能残高口数不足エラー。");

    /**
     * 受渡方法@チェックエラー。<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_02271 = errorMgr.defineErrorInfo(
            2271,
            "BUSINESS_ERROR_02271", 
            "受渡方法@チェックエラー。");

    /**
     * 募集開始日エラー。<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_02272 = errorMgr.defineErrorInfo(
            2272,
            "BUSINESS_ERROR_02272", 
            "募集開始日エラー。");

    /**
     * 募集終了日エラー。<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_02273 = errorMgr.defineErrorInfo(
            2273,
            "BUSINESS_ERROR_02273", 
            "募集終了日エラー。");

    /**
     * 最低口数（募集）エラー。<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_02274 = errorMgr.defineErrorInfo(
            2274,
            "BUSINESS_ERROR_02274", 
            "最低口数（募集）エラー。");

    /**
     * 単位口数（募集）エラー。<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_02275 = errorMgr.defineErrorInfo(
            2275,
            "BUSINESS_ERROR_02275", 
            "単位口数（募集）エラー。");

    /**
     * 最低金額（募集）エラー。<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_02276 = errorMgr.defineErrorInfo(
            2276,
            "BUSINESS_ERROR_02276", 
            "最低金額（募集）エラー。");

    /**
     * 単位金額（募集）エラー。<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_02277 = errorMgr.defineErrorInfo(
            2277,
            "BUSINESS_ERROR_02277", 
            "単位金額（募集）エラー。");

    /**
     * 同日金額指定注文取消要求。<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_02278 = errorMgr.defineErrorInfo(
            2278,
            "BUSINESS_ERROR_02278", 
            "同日金額指定注文取消要求。");

    /**
     * 約定後再発注要求。<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_02279 = errorMgr.defineErrorInfo(
            2279,
            "BUSINESS_ERROR_02279", 
            "約定後再発注要求。");

    /**
     * 同日解約注文取消要求。<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_02280 = errorMgr.defineErrorInfo(
            2280,
            "BUSINESS_ERROR_02280", 
            "同日解約注文取消要求。");

    /**
     * 指定方法@（募集）エラー(”口数”を指定不可)。<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_02281 = errorMgr.defineErrorInfo(
            2281,
            "BUSINESS_ERROR_02281", 
            "指定方法@（募集）エラー(”口数”を指定不可)。");

    /**
     * 最低口数（募集）あるいは単位口数（募集）の指定に誤りがあります。<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_02282 = errorMgr.defineErrorInfo(
            2282,
            "BUSINESS_ERROR_02282", 
            "最低口数（募集）あるいは単位口数（募集）の指定に誤りがあります。");

    /**
     * 指定方法@（募集）エラー(”金額”を指定不可)。<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_02283 = errorMgr.defineErrorInfo(
            2283,
            "BUSINESS_ERROR_02283", 
            "指定方法@（募集）エラー(”金額”を指定不可)。");

    /**
     * 最低金額（募集）あるいは単位金額（募集）の指定に誤りがあります。<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_02284 = errorMgr.defineErrorInfo(
            2284,
            "BUSINESS_ERROR_02284", 
            "最低金額（募集）あるいは単位金額（募集）の指定に誤りがあります。");

    /**
     * 決済明細注文株数指定エラー。<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_02285 = errorMgr.defineErrorInfo(
            2285,
            "BUSINESS_ERROR_02285", 
            "決済明細の注文株数指定が不正。");

    /**
     * 受渡方法@が”銀行振込み”の場合、決済方法@は必ず”円貨”を選択してください。<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_02286 = errorMgr.defineErrorInfo(
            2286,
            "BUSINESS_ERROR_02286", 
            "受渡方法@が”銀行振込み”の場合、決済方法@は必ず”円貨”を選択してください。");

    /**
     * 指定の予約注文はクローズ済です。<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_02287 = errorMgr.defineErrorInfo(
            2287,
            "BUSINESS_ERROR_02287", 
            "指定の予約注文はクローズ済です。");

    /**
     * 指定の予約注文は発注日を超過しています。<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_02288 = errorMgr.defineErrorInfo(
            2288,
            "BUSINESS_ERROR_02288", 
            "指定の予約注文は発注日を超過しています。");

    /**
     * 予約決済対象建株は別注文により決済済です。<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_02289 = errorMgr.defineErrorInfo(
            2289,
            "BUSINESS_ERROR_02289", 
            "予約決済対象建株は別注文により決済済です。");

    /**
     * 注文株数が親注文の注文数量を超過しています。<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_02290 = errorMgr.defineErrorInfo(
            2290,
            "BUSINESS_ERROR_02290", 
            "注文株数が親注文の注文数量を超過しています。");

    /**
     * 親注文が現引現渡注文の場合、±指値は指定不可です。<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_02291 = errorMgr.defineErrorInfo(
            2291,
            "BUSINESS_ERROR_02291", 
            "親注文が現引現渡注文の場合、±指値は指定不可です。");

    /**
     * 確認時概算受渡代金の値が不正です。<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_02292 = errorMgr.defineErrorInfo(
            2292,
            "BUSINESS_ERROR_02292", 
            "確認時概算受渡代金の値が不正です。");

    /**
     * 売買代金チェックエラー。<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_02295 = errorMgr.defineErrorInfo(
            2295,
            "BUSINESS_ERROR_02295", 
            "現地売付代金が現地諸経費を下回っています。");

    /**
     * メールアドレス削除フラグ，案内メール送信フラグのチェック。<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_02296 = errorMgr.defineErrorInfo(
            2296,
            "BUSINESS_ERROR_02296", 
            "削除と案内メール送信（要）同時に指定できない。");

    /**
     * 指定方法@（募集）エラー(”選択指定”を指定不可)。<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_02297 = errorMgr.defineErrorInfo(
            2297,
            "BUSINESS_ERROR_02297", 
            "指定方法@（募集）エラー(”選択指定”を指定不可)。");

    /**
     * 執行単価が0以下です。<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_02298 = errorMgr.defineErrorInfo(
            2298,
            "BUSINESS_ERROR_02298", 
            "執行単価が0以下です。");

    /**
     * （増担保銘柄）預り金不足。<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_02299 = errorMgr.defineErrorInfo(
            2299,
            "BUSINESS_ERROR_02299", 
            "（増担保銘柄）預り金不足。");

    /**
     * （増担保銘柄）保証金不足。<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_02300 = errorMgr.defineErrorInfo(
            2300,
            "BUSINESS_ERROR_02300", 
            "（増担保銘柄）保証金不足。");

    /**
     * 非営業日エラー。<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_02301 = errorMgr.defineErrorInfo(
            2301,
            "BUSINESS_ERROR_02301", 
            "該当市場が休業日の為、注文できません。");

    /**
     * ダウンロード可能時間外エラー。<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_02302 = errorMgr.defineErrorInfo(
            2302,
            "BUSINESS_ERROR_02302", 
            "ダウンロード可能時間外です。");

    /**
     * 決済順序チェックエラー。<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_02304 = errorMgr.defineErrorInfo(
            2304,
            "BUSINESS_ERROR_02304", 
            "一括返済時、決済順序は指定してください。");

    /**
     * 連続注文・反対取引時の決済順序区分指定不正。<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_02306 = errorMgr.defineErrorInfo(
            2306,
            "BUSINESS_ERROR_02306", 
            "連続注文の反対取引指定時は、決済順序区分に（ランダム／単価益順／単価損順）のいずれかを指定してください。");

    /**
     * 抽選確定処理未処理エラー。<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_02307 = errorMgr.defineErrorInfo(
            2307,
            "BUSINESS_ERROR_02307", 
            "抽選確定処理がおこなわれていません。");

    /**
     * 抽選対象レコードなしエラー。<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_02308 = errorMgr.defineErrorInfo(
            2308,
            "BUSINESS_ERROR_02308", 
            "抽選レコードが存在しません。");

    /**
     * 抽選確定処理エラー。<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_02309 = errorMgr.defineErrorInfo(
            2309,
            "BUSINESS_ERROR_02309", 
            "抽選処理は結果確定中、もしくは結果確定が終了しています。");

    /**
     * 抽選処理中エラー。<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_02310 = errorMgr.defineErrorInfo(
            2310,
            "BUSINESS_ERROR_02310", 
            "抽選処理中にエラーが発生しました。");

    /**
     * 抽選割当期間エラー。<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_02311 = errorMgr.defineErrorInfo(
            2311,
            "BUSINESS_ERROR_02311", 
            "抽選・割当期間ではありません。");

    /**
     * 抽選対象顧客なしエラー。<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_02312 = errorMgr.defineErrorInfo(
            2312,
            "BUSINESS_ERROR_02312", 
            "抽選対象顧客が存在しません。");

    /**
     * 割当可能数量超過。<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_02313 = errorMgr.defineErrorInfo(
            2313,
            "BUSINESS_ERROR_02313", 
            "割当総枠数量が割当可能数量を超過しています。");

    /**
     * 割当総枠数量入力値チェック。<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_02314 = errorMgr.defineErrorInfo(
            2314,
            "BUSINESS_ERROR_02314", 
            "割当総枠数量が数値以外です。");

    /**
     * 割当上限数量入力値チェック。<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_02315 = errorMgr.defineErrorInfo(
            2315,
            "BUSINESS_ERROR_02315", 
            "割当上限数量が数値以外です。");

    /**
     * 補欠割当総枠数量入力値チェック。<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_02316 = errorMgr.defineErrorInfo(
            2316,
            "BUSINESS_ERROR_02316", 
            "補欠割当総枠数量が数値以外です。");

    /**
     * 補欠割当上限数量入力値チェック。<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_02317 = errorMgr.defineErrorInfo(
            2317,
            "BUSINESS_ERROR_02317", 
            "補欠割当上限数量が数値以外です。");

    /**
     * Batch処理制御チェック。<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_02318 = errorMgr.defineErrorInfo(
            2318,
            "BUSINESS_ERROR_02318", 
            "抽選処理実行中は、再抽選できません。");

    /**
     * 割当総枠数量整数倍チェック。<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_02319 = errorMgr.defineErrorInfo(
            2319,
            "BUSINESS_ERROR_02319", 
            "割当総枠数量は、購入申込単位の整数倍で入力してください。");

    /**
     * 割当上限数量整数倍チェック。<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_02320 = errorMgr.defineErrorInfo(
            2320,
            "BUSINESS_ERROR_02320", 
            "割当上限数量は、購入申込単位の整数倍で入力してください。");

    /**
     * 補欠割当総枠数量整数倍チェック。<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_02321 = errorMgr.defineErrorInfo(
            2321,
            "BUSINESS_ERROR_02321", 
            "補欠割当総枠数量は、購入申込単位の整数倍で入力してください。");

    /**
     * 補欠割当上限数量整数倍チェック。<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_02322 = errorMgr.defineErrorInfo(
            2322,
            "BUSINESS_ERROR_02322", 
            "補欠割当上限数量は、購入申込単位の整数倍で入力してください。");

    /**
     * 確定処理レコードなしチェック。<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_02323 = errorMgr.defineErrorInfo(
            2323,
            "BUSINESS_ERROR_02323", 
            "確定処理をおこなう情報がありません。");

    /**
     * 源泉徴収拘束金余力チェックエラー。<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_02324 = errorMgr.defineErrorInfo(
            2324,
            "BUSINESS_ERROR_02324", 
            "源泉徴収拘束金が不足しています。");

    /**
     * 画面区分が未指定です。<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_02325 = errorMgr.defineErrorInfo(
            2325,
            "BUSINESS_ERROR_02325", 
            "画面区分が未指定です。");

    /**
     * 抽選銘柄が削除銘柄です。<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_02326 = errorMgr.defineErrorInfo(
            2326,
            "BUSINESS_ERROR_02326", 
            "抽選銘柄が削除銘柄です。");

    /**
     * 抽選銘柄が中止銘柄です。<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_02327 = errorMgr.defineErrorInfo(
            2327,
            "BUSINESS_ERROR_02327", 
            "抽選銘柄が中止銘柄です。");

    /**
     * 抽選銘柄のブックビルディング期間が終了ではありません。<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_02328 = errorMgr.defineErrorInfo(
            2328,
            "BUSINESS_ERROR_02328", 
            "抽選銘柄のブックビルディング期間が終了ではありません。");

    /**
     * 抽選銘柄のスケジュール項目が不適切です。<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_02329 = errorMgr.defineErrorInfo(
            2329,
            "BUSINESS_ERROR_02329", 
            "抽選銘柄のスケジュール項目が不適切です。");

    /**
     * 抽選銘柄の公開価格が設定されていません。<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_02330 = errorMgr.defineErrorInfo(
            2330,
            "BUSINESS_ERROR_02330", 
            "抽選銘柄の公開価格が設定されていません。");

    /**
     * 抽選銘柄の取扱数量が 0です。<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_02331 = errorMgr.defineErrorInfo(
            2331,
            "BUSINESS_ERROR_02331", 
            "抽選銘柄の取扱数量が 0です。");

    /**
     * 抽選銘柄の購入申込単位が 0です。<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_02332 = errorMgr.defineErrorInfo(
            2332,
            "BUSINESS_ERROR_02332", 
            "抽選銘柄の購入申込単位が 0です。");

    /**
     * 補欠データが存在しません。<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_02333 = errorMgr.defineErrorInfo(
            2333,
            "BUSINESS_ERROR_02333", 
            "補欠データが存在しません。");

    /**
     * 精算金額が未指定です。<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_02334 = errorMgr.defineErrorInfo(
            2334,
            "BUSINESS_ERROR_02334", 
            "精算金額が未指定です。");

    /**
     * 精算金額が11桁を超えました。<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_02335 = errorMgr.defineErrorInfo(
            2335,
            "BUSINESS_ERROR_02335", 
            "精算金額が11桁を超えました。");

    /**
     * 受渡日が非営業日です。<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_02336 = errorMgr.defineErrorInfo(
            2336,
            "BUSINESS_ERROR_02336", 
            "受渡日が非営業日です。");

    /**
     * 乗換先買付最低金額（新規買付）チェックエラー。<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_02337 = errorMgr.defineErrorInfo(
            2337,
            "BUSINESS_ERROR_02337", 
            "乗換先銘柄の新規買付最低金額条件を満たしていません。");

    /**
     * 乗換先買付最低金額（追加買付）チェックエラー。<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_02338 = errorMgr.defineErrorInfo(
            2338,
            "BUSINESS_ERROR_02338", 
            "乗換先銘柄の追加買付最低金額条件を満たしていません。");

    /**
     * 連続注文・返済指定データ取得エラー。<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_02339 = errorMgr.defineErrorInfo(
            2339,
            "BUSINESS_ERROR_02339", 
            "前提注文による約定建の情報、または返済指定データが存在しません。");

    /**
     * 抽選確定対象チェック。<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_02340 = errorMgr.defineErrorInfo(
            2340,
            "BUSINESS_ERROR_02340", 
            "抽選確定対象の部店IDリストが取得できません。");

    /**
     * 金融機@關コードチェック。<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_02341 = errorMgr.defineErrorInfo(
            2341,
            "BUSINESS_ERROR_02341", 
            "金融機@關コードが数字以外の値です。");

    /**
     * 金融機@關コードチェック。<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_02342 = errorMgr.defineErrorInfo(
            2342,
            "BUSINESS_ERROR_02342", 
            "金融機@關コードの文字数が不正です。");

    /**
     * 親注文が取消中のためトリガー注文設定不可です。<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_02343 = errorMgr.defineErrorInfo(
            2343,
            "BUSINESS_ERROR_02343", 
            "親注文が取消中のためトリガー注文設定不可です。");

    /**
     * 居住者チェックエラー。<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_02344 = errorMgr.defineErrorInfo(
            2344,
            "BUSINESS_ERROR_02344", 
            "非居住者は買付できません。");

    /**
     * 入金通知テーブルID未指定エラー。<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_02345 = errorMgr.defineErrorInfo(
            2345,
            "BUSINESS_ERROR_02345", 
            "入金通知テーブルIDが未指定(null)または不正な値です。");

    /**
     * 銀行コード未入力エラー。<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_02346 = errorMgr.defineErrorInfo(
            2346,
            "BUSINESS_ERROR_02346", 
            "銀行コードが未入力です。");

    /**
     * 受渡日が入力日より前のエラー。<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_02347 = errorMgr.defineErrorInfo(
            2347,
            "BUSINESS_ERROR_02347", 
            "受渡日が入力日より前の日付です。");

    /**
     * 約諾書区分の値が不正です。<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_02348 = errorMgr.defineErrorInfo(
            2348,
            "BUSINESS_ERROR_02348", 
            "約諾書区分の値が不正です。");

    /**
     * FX口座情報の口座番号と更新後約諾書区分のチェック。<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_02349 = errorMgr.defineErrorInfo(
            2349,
            "BUSINESS_ERROR_02349", 
            "更新後ステータスが”削除”の場合は、FX口座情報の口座番号と更新後約諾書区分が同時に指定できない。");

    /**
     * 約諾書無しエラー。<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_02350 = errorMgr.defineErrorInfo(
            2350,
            "BUSINESS_ERROR_02350", 
            "約諾書無しエラー。");

    /**
     * 限月が数字以外。<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_02351 = errorMgr.defineErrorInfo(
            2351,
            "BUSINESS_ERROR_02351", 
            "限月が数字以外の値です。");

    /**
     * 発注状況が未定義の値。<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_02352 = errorMgr.defineErrorInfo(
            2352,
            "BUSINESS_ERROR_02352", 
            "発注状況の値が存在しないコード値です。");

    /**
     * 入力時間エラー(時価情報受信時間From)。<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_02354 = errorMgr.defineErrorInfo(
            2354,
            "BUSINESS_ERROR_02354", 
            "入力時間エラー(時価情報受信時間From)。");

    /**
     * 入力時間エラー(時価情報受信時間To)。<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_02355 = errorMgr.defineErrorInfo(
            2355,
            "BUSINESS_ERROR_02355", 
            "入力時間エラー(時価情報受信時間To)。");

    /**
     * 入力時間エラー(トリガー起動時間From)。<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_02356 = errorMgr.defineErrorInfo(
            2356,
            "BUSINESS_ERROR_02356", 
            "入力時間エラー(トリガー起動時間From)。");

    /**
     * 入力時間エラー(トリガー起動時間To)。<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_02357 = errorMgr.defineErrorInfo(
            2357,
            "BUSINESS_ERROR_02357", 
            "入力時間エラー(トリガー起動時間To)。");

    /**
     * 入力時間エラー(発注完了時間From)。<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_02358 = errorMgr.defineErrorInfo(
            2358,
            "BUSINESS_ERROR_02358", 
            "入力時間エラー(発注完了時間From)。");

    /**
     * 入力時間エラー(発注完了時間To)。<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_02359 = errorMgr.defineErrorInfo(
            2359,
            "BUSINESS_ERROR_02359", 
            "入力時間エラー(発注完了時間To)。");

    /**
     * 乖離時間が未指定です。<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_02360 = errorMgr.defineErrorInfo(
            2360,
            "BUSINESS_ERROR_02360", 
            "乖離時間が未指定です。");

    /**
     * 乖離時間エラー。<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_02361 = errorMgr.defineErrorInfo(
            2361,
            "BUSINESS_ERROR_02361", 
            "乖離時間の入力が不正です。");

    /**
     * 乖離時間Fromが未指定エラー。<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_02362 = errorMgr.defineErrorInfo(
            2362,
            "BUSINESS_ERROR_02362", 
            "乖離時間Fromが未指定です。");

    /**
     * 乖離時間Toが未指定エラー。<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_02363 = errorMgr.defineErrorInfo(
            2363,
            "BUSINESS_ERROR_02363", 
            "乖離時間Toが未指定です。");

    /**
     * 乖離時間整合性エラー。<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_02364 = errorMgr.defineErrorInfo(
            2364,
            "BUSINESS_ERROR_02364", 
            "乖離時間整合性エラー。");

    /**
     * 利用者コードが取得できません。<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_02366 = errorMgr.defineErrorInfo(
            2366,
            "BUSINESS_ERROR_02366", 
            "利用者コードが取得できません。");

    /**
     * 利用者コードの値が半角数字以外の値です。<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_02367 = errorMgr.defineErrorInfo(
            2367,
            "BUSINESS_ERROR_02367", 
            "利用者コードの値が半角数字以外の値です。");

    /**
     * 利用者コードの値が8byteではありません。<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_02368 = errorMgr.defineErrorInfo(
            2368,
            "BUSINESS_ERROR_02368", 
            "利用者コードの値が8byteではありません。");

    /**
     * 利用者名の文字数が120byteより大きいです。<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_02369 = errorMgr.defineErrorInfo(
            2369,
            "BUSINESS_ERROR_02369", 
            "利用者名の文字数が120byteより大きいです。");

    /**
     * ログインＩＤが取得できません。<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_02370 = errorMgr.defineErrorInfo(
            2370,
            "BUSINESS_ERROR_02370", 
            "ログインＩＤが取得できません。");

    /**
     * ログインＩＤの値が半角数字以外の値です。<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_02371 = errorMgr.defineErrorInfo(
            2371,
            "BUSINESS_ERROR_02371", 
            "ログインＩＤの値が半角数字以外の値です。");

    /**
     * ログインＩＤの値が8byteではありません。<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_02372 = errorMgr.defineErrorInfo(
            2372,
            "BUSINESS_ERROR_02372", 
            "ログインＩＤの値が8byteではありません。");

    /**
     * 自己受託区分の値が1byteより大きいです。<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_02373 = errorMgr.defineErrorInfo(
            2373,
            "BUSINESS_ERROR_02373", 
            "自己受託区分の値が1byteより大きいです。");

    /**
     * ロスカット区分の値が2byteより大きいです。<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_02374 = errorMgr.defineErrorInfo(
            2374,
            "BUSINESS_ERROR_02374", 
            "ロスカット区分の値が2byteより大きいです。");

    /**
     * 手数料区分の値が2byteより大きいです。<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_02375 = errorMgr.defineErrorInfo(
            2375,
            "BUSINESS_ERROR_02375", 
            "手数料区分の値が2byteより大きいです。");

    /**
     * 取引可能区分の値が1byteより大きいです。<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_02376 = errorMgr.defineErrorInfo(
            2376,
            "BUSINESS_ERROR_02376", 
            "取引可能区分の値が1byteより大きいです。");

    /**
     * 備考が取得できません。<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_02377 = errorMgr.defineErrorInfo(
            2377,
            "BUSINESS_ERROR_02377", 
            "備考が取得できません。");

    /**
     * 商品コードの値が7byteより大きいです。<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_02378 = errorMgr.defineErrorInfo(
            2378,
            "BUSINESS_ERROR_02378", 
            "商品コードの値が7byteより大きいです。");

    /**
     * 発注上限数量の値が半角数字以外の値です。<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_02379 = errorMgr.defineErrorInfo(
            2379,
            "BUSINESS_ERROR_02379", 
            "発注上限数量の値が半角数字以外の値です。");

    /**
     * 発注上限数量の値が15byteより大きいです。<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_02380 = errorMgr.defineErrorInfo(
            2380,
            "BUSINESS_ERROR_02380", 
            "発注上限数量の値が15byteより大きいです。");

    /**
     * 利用者コード重複エラー。<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_02381 = errorMgr.defineErrorInfo(
            2381,
            "BUSINESS_ERROR_02381", 
            "利用者コード重複エラー。");

    /**
     * 科目コードの値が半角数字以外の値です。<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_02382 = errorMgr.defineErrorInfo(
            2382,
            "BUSINESS_ERROR_02382", 
            "科目コードの値が半角数字以外の値です。");

    /**
     * 科目コードの値が1byteが数字ではありません。<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_02383 = errorMgr.defineErrorInfo(
            2383,
            "BUSINESS_ERROR_02383", 
            "科目コードの値が1byteが数字ではありません。");

    /**
     * 出金額の値が半角数字以外の値です。<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_02384 = errorMgr.defineErrorInfo(
            2384,
            "BUSINESS_ERROR_02384", 
            "出金額の値が半角数字以外の値です。");

    /**
     * 出金額の値が20byteより大きいです。<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_02385 = errorMgr.defineErrorInfo(
            2385,
            "BUSINESS_ERROR_02385", 
            "出金額の値が20byteより大きいです。");

    /**
     * 出金日の値が半角数字以外の値です。<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_02386 = errorMgr.defineErrorInfo(
            2386,
            "BUSINESS_ERROR_02386", 
            "出金日の値が半角数字以外の値です。");

    /**
     * 出金日の値が8byteより大きいです。<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_02387 = errorMgr.defineErrorInfo(
            2387,
            "BUSINESS_ERROR_02387", 
            "出金日の値が8byteより大きいです。");

    /**
     * 入出金番号の値が半角数字以外の値です。<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_02388 = errorMgr.defineErrorInfo(
            2388,
            "BUSINESS_ERROR_02388", 
            "入出金番号の値が半角数字以外の値です。");

    /**
     * 入出金番号の値が9byteより大きいです。<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_02389 = errorMgr.defineErrorInfo(
            2389,
            "BUSINESS_ERROR_02389", 
            "入出金番号の値が9byteより大きいです。");

    /**
     * 取得したデータが存在する場合、注文が重複して登録されています。<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_02390 = errorMgr.defineErrorInfo(
            2390,
            "BUSINESS_ERROR_02390", 
            "取得したデータが存在する場合、注文が重複して登録されています。");

    /**
     * 抽選レコードが存在します。<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_02391 = errorMgr.defineErrorInfo(
            2391,
            "BUSINESS_ERROR_02391", 
            "抽選レコードが存在します。");

    /**
     * 条件に該当するデータが存在しない、または複数存在します。<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_02392 = errorMgr.defineErrorInfo(
            2392,
            "BUSINESS_ERROR_02392", 
            "条件に該当するデータが存在しない、または複数存在します。");

    /**
     * 手動発注対象注文なし。<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_02393 = errorMgr.defineErrorInfo(
            2393,
            "BUSINESS_ERROR_02393", 
            "手動発注対象注文なし。");

    /**
     * 銘柄タイプが未指定です。<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_02394 = errorMgr.defineErrorInfo(
            2394,
            "BUSINESS_ERROR_02394", 
            "銘柄タイプが未指定です。");

    /**
     * 銘柄タイプが未定義の値です。<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_02395 = errorMgr.defineErrorInfo(
            2395,
            "BUSINESS_ERROR_02395", 
            "銘柄タイプが未定義の値です。");

    /**
     * 条件注文種別が未指定です。<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_02396 = errorMgr.defineErrorInfo(
            2396,
            "BUSINESS_ERROR_02396", 
            "条件注文種別が未指定です。");

    /**
     * 条件注文種別が未定義の値です。<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_02397 = errorMgr.defineErrorInfo(
            2397,
            "BUSINESS_ERROR_02397", 
            "条件注文種別が未定義の値です。");

    /**
     * 外部システム接続エラー。<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_02398 = errorMgr.defineErrorInfo(
            2398,
            "BUSINESS_ERROR_02398", 
            "外部システム接続エラー。");

    /**
     * 接続タイムアウトエラー。<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_02399 = errorMgr.defineErrorInfo(
            2399,
            "BUSINESS_ERROR_02399", 
            "接続タイムアウトエラー。");

    /**
     * 連絡先優先順位重複チェック。<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_02400 = errorMgr.defineErrorInfo(
            2400,
            "BUSINESS_ERROR_02400", 
            "連絡先優先順位1位～3位に重複している連絡先があります。");

    /**
     * 携帯番号が未入力です。<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_02401 = errorMgr.defineErrorInfo(
            2401,
            "BUSINESS_ERROR_02401", 
            "携帯番号が未入力です。");

    /**
     * 勤務先電話番号が未入力です。<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_02402 = errorMgr.defineErrorInfo(
            2402,
            "BUSINESS_ERROR_02402", 
            "勤務先電話番号が未入力です。");

    /**
     * 約諾書区分更新チェック。<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_02403 = errorMgr.defineErrorInfo(
            2403,
            "BUSINESS_ERROR_02403", 
            "現在の約諾書区分から指定の約諾書区分へ変更できません。");

    /**
     * FXログインID頭文字が取得できません。<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_02404 = errorMgr.defineErrorInfo(
            2404,
            "BUSINESS_ERROR_02404", 
            "FXログインID頭文字が取得できません。");

    /**
     * FXログインID頭文字が半角数字以外の値です。<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_02405 = errorMgr.defineErrorInfo(
            2405,
            "BUSINESS_ERROR_02405", 
            "FXログインID頭文字が半角数字以外の値です。");

    /**
     * FXログインID頭文字の値が2byteでないエラー。<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_02406 = errorMgr.defineErrorInfo(
            2406,
            "BUSINESS_ERROR_02406", 
            "FXログインID頭文字の値が2byteでないエラー。");

    /**
     * 顧客コードが取得できません。<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_02407 = errorMgr.defineErrorInfo(
            2407,
            "BUSINESS_ERROR_02407", 
            "顧客コードが取得できません。");

    /**
     * 該当する接続区分がないエラー。<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_02408 = errorMgr.defineErrorInfo(
            2408,
            "BUSINESS_ERROR_02408", 
            "該当する接続区分がないエラー。");

    /**
     * 連絡先優先順位整合性チェック（携帯・その他）。<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_02409 = errorMgr.defineErrorInfo(
            2409,
            "BUSINESS_ERROR_02409", 
            "携帯番号・その他連絡先が未入力のため、連絡先優先順位に”携帯・その他”を選択する事は出来ません。");

    /**
     * 連絡先優先順位整合性チェック（勤務先）。<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_02410 = errorMgr.defineErrorInfo(
            2410,
            "BUSINESS_ERROR_02410", 
            "勤務先電話番号が未入力のため、連絡先優先順位に”勤務先”を選択する事は出来ません。");

    /**
     * 連絡先優先順位整合性チェック（その他連絡先）。<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_02411 = errorMgr.defineErrorInfo(
            2411,
            "BUSINESS_ERROR_02411", 
            "その他連絡先が未入力のため、連絡先優先順位に”その他連絡先”を選択する事は出来ません。");

    /**
     * 連絡先優先順位整合性チェック（取引責任者勤務先）。<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_02412 = errorMgr.defineErrorInfo(
            2412,
            "BUSINESS_ERROR_02412", 
            "取引責任者勤務先が未入力のため、連絡先優先順位に”取引責任者勤務先”を選択する事は出来ません。");

    /**
     * 出金額の桁数が9桁より大きいです。<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_02413 = errorMgr.defineErrorInfo(
            2413,
            "BUSINESS_ERROR_02413", 
            "出金額の桁数が9桁より大きいです。");

    /**
     * 部店コード、顧客コードのチェック。<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_02414 = errorMgr.defineErrorInfo(
            2414,
            "BUSINESS_ERROR_02414", 
            "部店コード・顧客コードが不正です。");

    /**
     * メールアドレスのチェック。<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_02415 = errorMgr.defineErrorInfo(
            2415,
            "BUSINESS_ERROR_02415", 
            "メールアドレスが不正です。");

    /**
     * 案内メール送信フラグのチェック。<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_02416 = errorMgr.defineErrorInfo(
            2416,
            "BUSINESS_ERROR_02416", 
            "案内メール送信フラグが不正です。");

    /**
     * 削除区分のチェック。<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_02417 = errorMgr.defineErrorInfo(
            2417,
            "BUSINESS_ERROR_02417", 
            "削除区分が不正です。");

    /**
     * 明細行数チェック。<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_02418 = errorMgr.defineErrorInfo(
            2418,
            "BUSINESS_ERROR_02418", 
            "レコード件数が処理限界値を超えています。");

    /**
     * 注文未発注チェック。<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_02419 = errorMgr.defineErrorInfo(
            2419,
            "BUSINESS_ERROR_02419", 
            "指定された注文は手動失効処理対象外です。");

    /**
     * 失効対象注文条件のチェック。<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_02420 = errorMgr.defineErrorInfo(
            2420,
            "BUSINESS_ERROR_02420", 
            "失効対象注文条件が未入力です。");

    /**
     * From口座IDチェック。<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_02421 = errorMgr.defineErrorInfo(
            2421,
            "BUSINESS_ERROR_02421", 
            "From口座IDが未入力です。");

    /**
     * To口座IDチェック。<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_02422 = errorMgr.defineErrorInfo(
            2422,
            "BUSINESS_ERROR_02422", 
            "To口座ID（至）が未入力です。");

    /**
     * FX口座開設チェックエラー(口座開設処理中）。<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_02423 = errorMgr.defineErrorInfo(
            2423,
            "BUSINESS_ERROR_02423", 
            "現在、FX口座開設処理中です。");

    /**
     * テーブル名nullチェック。<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_02424 = errorMgr.defineErrorInfo(
            2424,
            "BUSINESS_ERROR_02424", 
            "テーブル名がnullです。");

    /**
     * テーブル物理名nullチェック。<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_02425 = errorMgr.defineErrorInfo(
            2425,
            "BUSINESS_ERROR_02425", 
            "テーブル物理名がnullです。");

    /**
     * 検索条件チェック。<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_02426 = errorMgr.defineErrorInfo(
            2426,
            "BUSINESS_ERROR_02426", 
            "検索条件には部店コードの他に、1つ以上入力してください。");

    /**
     * 更新後ステータスチェック。<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_02427 = errorMgr.defineErrorInfo(
            2427,
            "BUSINESS_ERROR_02427", 
            "更新後ステータスがnullです。");

    /**
     * 店頭公開区分チェック。<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_02428 = errorMgr.defineErrorInfo(
            2428,
            "BUSINESS_ERROR_02428", 
            "店頭公開区分が存在しないコード値です。");

    /**
     * 注文停止状況一覧チェック。<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_02429 = errorMgr.defineErrorInfo(
            2429,
            "BUSINESS_ERROR_02429", 
            "注文停止状況が未入力です。");

    /**
     * 特殊執行条件取扱停止IDチェック。<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_02430 = errorMgr.defineErrorInfo(
            2430,
            "BUSINESS_ERROR_02430", 
            "特殊執行条件取扱停止IDが未入力です。");

    /**
     * 取扱停止情報チェック。<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_02431 = errorMgr.defineErrorInfo(
            2431,
            "BUSINESS_ERROR_02431", 
            "取扱停止情報が未入力です。");

    /**
     * 検索結果が取得できた場合。<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_02432 = errorMgr.defineErrorInfo(
            2432,
            "BUSINESS_ERROR_02432", 
            "入力した取扱停止情報は既に登録済です。");

    /**
     * （商品別）特殊執行条件取扱停止チェック。<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_02433 = errorMgr.defineErrorInfo(
            2433,
            "BUSINESS_ERROR_02433", 
            "指定された条件付注文での商品は取扱停止中です。");

    /**
     * （市場別）特殊執行条件取扱停止チェック。<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_02434 = errorMgr.defineErrorInfo(
            2434,
            "BUSINESS_ERROR_02434", 
            "指定された条件付注文での市場は取扱停止中です。");

    /**
     * （銘柄別）特殊執行条件取扱停止チェック。<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_02435 = errorMgr.defineErrorInfo(
            2435,
            "BUSINESS_ERROR_02435", 
            "指定された条件付注文での銘柄は取扱停止中です。");

    /**
     * 証拠金口座未開設エラー。<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_02436 = errorMgr.defineErrorInfo(
            2436,
            "BUSINESS_ERROR_02436", 
            "該当する証拠金口座が存在しない。");

    /**
     * ファ@イル内容のチェック。<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_02437 = errorMgr.defineErrorInfo(
            2437,
            "BUSINESS_ERROR_02437", 
            "アップロードファ@イルの内容が不正です。");

    /**
     * データレコードチェック。<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_02438 = errorMgr.defineErrorInfo(
            2438,
            "BUSINESS_ERROR_02438", 
            "アップロード対象のデータ・レコードが存在しません。");

    /**
     * 入金デーモンチェック。<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_02439 = errorMgr.defineErrorInfo(
            2439,
            "BUSINESS_ERROR_02439", 
            "他の機@能にて入金処理が行われています。少し時間が経ってから再度「実行ボタン」を押して下さい。");

    /**
     * FX振替可能チェック。<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_02440 = errorMgr.defineErrorInfo(
            2440,
            "BUSINESS_ERROR_02440", 
            "振替停止中エラー。");

    /**
     * 指数種別チェック。<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_02441 = errorMgr.defineErrorInfo(
            2441,
            "BUSINESS_ERROR_02441", 
            "指数種別が数字以外の値です。");

    /**
     * 指数種別チェック。<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_02442 = errorMgr.defineErrorInfo(
            2442,
            "BUSINESS_ERROR_02442", 
            "指数種別のサイズが不正です。");

    /**
     * 重複アドレスチェック。<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_02443 = errorMgr.defineErrorInfo(
            2443,
            "BUSINESS_ERROR_02443", 
            "メールアドレスは既に登録済みです。");

    /**
     * 重複アドレスチェック。<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_02444 = errorMgr.defineErrorInfo(
            2444,
            "BUSINESS_ERROR_02444", 
            "メールアドレスは以下の顧客によって登録済みです。");

    /**
     * 弁済区分チェック。<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_02445 = errorMgr.defineErrorInfo(
            2445,
            "BUSINESS_ERROR_02445", 
            "現物株式取引は弁済区分の指定不可。");

    /**
     * 注文繰越処理中チェック。<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_02446 = errorMgr.defineErrorInfo(
            2446,
            "BUSINESS_ERROR_02446", 
            "注文繰越処理中の為、処理不可。");

    /**
     * 弁済区分チェック。<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_02447 = errorMgr.defineErrorInfo(
            2447,
            "BUSINESS_ERROR_02447", 
            "信用取引は弁済区分の指定必須。");

    /**
     * 承認可能チェック。<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_02448 = errorMgr.defineErrorInfo(
            2448,
            "BUSINESS_ERROR_02448", 
            "対象のデータは審査済です。");

    /**
     * 顧客レンジ未登録エラー。<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_02449 = errorMgr.defineErrorInfo(
            2449,
            "BUSINESS_ERROR_02449", 
            "顧客レンジが登録されていません。");

    /**
     * 需要予測状況表未作成エラー。<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_02450 = errorMgr.defineErrorInfo(
            2450,
            "BUSINESS_ERROR_02450", 
            "需要予測状況表が作成されていません。");

    /**
     * 需要予測状況表作成中エラー。<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_02451 = errorMgr.defineErrorInfo(
            2451,
            "BUSINESS_ERROR_02451", 
            "需要予測状況表は現在作成中です。");

    /**
     * レンジ名称．レンジ(自)．レンジ(至)入力値チェック。<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_02452 = errorMgr.defineErrorInfo(
            2452,
            "BUSINESS_ERROR_02452", 
            "レンジ名称．レンジ(自)．レンジ(至)全てが未入力です。");

    /**
     * レンジ名称チェック。<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_02453 = errorMgr.defineErrorInfo(
            2453,
            "BUSINESS_ERROR_02453", 
            "レンジ名称が全角文字ではありません。");

    /**
     * レンジ名称チェック。<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_02454 = errorMgr.defineErrorInfo(
            2454,
            "BUSINESS_ERROR_02454", 
            "レンジ名称の文字数が不正です。");

    /**
     * レンジ(自)チェック。<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_02455 = errorMgr.defineErrorInfo(
            2455,
            "BUSINESS_ERROR_02455", 
            "レンジ（自）が未指定です。");

    /**
     * レンジ(自)チェック。<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_02456 = errorMgr.defineErrorInfo(
            2456,
            "BUSINESS_ERROR_02456", 
            "レンジ（自）が数字ではありません。");

    /**
     * レンジ(自)チェック。<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_02457 = errorMgr.defineErrorInfo(
            2457,
            "BUSINESS_ERROR_02457", 
            "レンジ（自）が6桁ではありません。");

    /**
     * レンジ(至)チェック。<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_02458 = errorMgr.defineErrorInfo(
            2458,
            "BUSINESS_ERROR_02458", 
            "レンジ（至）が未指定です。");

    /**
     * レンジ(至)チェック。<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_02459 = errorMgr.defineErrorInfo(
            2459,
            "BUSINESS_ERROR_02459", 
            "レンジ（至）が数字ではありません。");

    /**
     * レンジ(至)チェック。<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_02460 = errorMgr.defineErrorInfo(
            2460,
            "BUSINESS_ERROR_02460", 
            "レンジ（至）が6桁ではありません。");

    /**
     * レンジ値整合性チェック。<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_02461 = errorMgr.defineErrorInfo(
            2461,
            "BUSINESS_ERROR_02461", 
            "レンジ（自）はレンジ（至）より大きいです。");

    /**
     * レンジ値重複チェック。<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_02462 = errorMgr.defineErrorInfo(
            2462,
            "BUSINESS_ERROR_02462", 
            "レンジ値が重複しています。");

    /**
     * 審査担当者コードのチェック。<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_02463 = errorMgr.defineErrorInfo(
            2463,
            "BUSINESS_ERROR_02463", 
            "審査担当者コードの値が半角英数字以外の値です。");

    /**
     * 発生日（自）、発生日（至）のチェック。<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_02464 = errorMgr.defineErrorInfo(
            2464,
            "BUSINESS_ERROR_02464", 
            "発生日（自）は発生日（至）より大きいです。");

    /**
     * 需要予測状況表作成可能状態のチェック。<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_02465 = errorMgr.defineErrorInfo(
            2465,
            "BUSINESS_ERROR_02465", 
            "需要予測状況表は作成可能状態がエラーです。");

    /**
     * レンジ値不整合チェック。<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_02466 = errorMgr.defineErrorInfo(
            2466,
            "BUSINESS_ERROR_02466", 
            "レンジ値が不整合です。");

    /**
     * ルールエンジン送信エラー。<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_02467 = errorMgr.defineErrorInfo(
            2467,
            "BUSINESS_ERROR_02467", 
            "ルールエンジンへの送信失敗。");

    /**
     * ルールエンジン送信エラー。<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_02468 = errorMgr.defineErrorInfo(
            2468,
            "BUSINESS_ERROR_02468", 
            "ルールエンジンへの送信失敗。ネットワークエラーです。");

    /**
     * ルールエンジン送信エラー。<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_02469 = errorMgr.defineErrorInfo(
            2469,
            "BUSINESS_ERROR_02469", 
            "ルールエンジンへの訂正、取消失敗。対応する注文IDがありません。");

    /**
     * ルールエンジン送信エラー。<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_02470 = errorMgr.defineErrorInfo(
            2470,
            "BUSINESS_ERROR_02470", 
            "ルールエンジンへの送信失敗。注文IDが重複してます。");

    /**
     * ルールエンジン送信エラー。<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_02471 = errorMgr.defineErrorInfo(
            2471,
            "BUSINESS_ERROR_02471", 
            "ルールエンジンへの訂正、取消失敗。既に発注済の可能性があります。");

    /**
     * 識別コードチェック。<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_02472 = errorMgr.defineErrorInfo(
            2472,
            "BUSINESS_ERROR_02472", 
            "識別コードの要素数が0です。");

    /**
     * 発注条件・執行条件のチェック。<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_02473 = errorMgr.defineErrorInfo(
            2473,
            "BUSINESS_ERROR_02473", 
            "発注条件区分が逆指値の場合は、執行条件に無条件を指定してください。");

    /**
     * 投信定時定額買付共通情報一覧チェック。<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_02474 = errorMgr.defineErrorInfo(
            2474,
            "BUSINESS_ERROR_02474", 
            "買付条件設定なしエラー。");

    /**
     * 買付金額チェック。<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_02475 = errorMgr.defineErrorInfo(
            2475,
            "BUSINESS_ERROR_02475", 
            "買付金額入力エラー。");

    /**
     * 買付金額チェック。<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_02476 = errorMgr.defineErrorInfo(
            2476,
            "BUSINESS_ERROR_02476", 
            "買付金額（月々）が数字以外の値です。");

    /**
     * 買付金額（月々）チェック。<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_02477 = errorMgr.defineErrorInfo(
            2477,
            "BUSINESS_ERROR_02477", 
            "買付金額（月々）桁数エラー。");

    /**
     * 買付金額（積み増し）チェック。<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_02478 = errorMgr.defineErrorInfo(
            2478,
            "BUSINESS_ERROR_02478", 
            "買付金額（積み増し）が数字以外の値です。");

    /**
     * 買付金額（積み増し）チェック。<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_02479 = errorMgr.defineErrorInfo(
            2479,
            "BUSINESS_ERROR_02479", 
            "買付金額（積み増し）桁数エラー。");

    /**
     * 定時定額買付チェック。<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_02480 = errorMgr.defineErrorInfo(
            2480,
            "BUSINESS_ERROR_02480", 
            "定時定額買付不可銘柄エラー。");

    /**
     * 定時定額買付最低金額チェック、単位金額チェック。<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_02481 = errorMgr.defineErrorInfo(
            2481,
            "BUSINESS_ERROR_02481", 
            "定時定額買付最低金額エラー。");

    /**
     * 個別割当期間チェック。<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_02482 = errorMgr.defineErrorInfo(
            2482,
            "BUSINESS_ERROR_02482", 
            "個別割当期間ではありません。");

    /**
     * IPO申告チェック。<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_02483 = errorMgr.defineErrorInfo(
            2483,
            "BUSINESS_ERROR_02483", 
            "対象顧客は当選且つ、購入申込済みです。");

    /**
     * IPO申告チェック。<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_02484 = errorMgr.defineErrorInfo(
            2484,
            "BUSINESS_ERROR_02484", 
            "対象顧客は繰上当選済みです。");

    /**
     * IPO申告チェック。<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_02485 = errorMgr.defineErrorInfo(
            2485,
            "BUSINESS_ERROR_02485", 
            "申告レコードはSONAR送信済みです。");

    /**
     * 申告IDチェック。<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_02486 = errorMgr.defineErrorInfo(
            2486,
            "BUSINESS_ERROR_02486", 
            "申告IDが未指定です。");

    /**
     * 税区分チェック。<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_02487 = errorMgr.defineErrorInfo(
            2487,
            "BUSINESS_ERROR_02487", 
            "税区分が未指定です。");

    /**
     * 申告レコードチェック。<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_02488 = errorMgr.defineErrorInfo(
            2488,
            "BUSINESS_ERROR_02488", 
            "該当顧客の申告レコードは存在しません。");

    /**
     * 個別割当チェック。<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_02489 = errorMgr.defineErrorInfo(
            2489,
            "BUSINESS_ERROR_02489", 
            "個別割当以外での当選は取消できません。");

    /**
     * 株数が割当可能数量チェック。<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_02490 = errorMgr.defineErrorInfo(
            2490,
            "BUSINESS_ERROR_02490", 
            "株数が割当可能数量を超過しています。");

    /**
     * 定時定額買付単位金額チェック。<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_02491 = errorMgr.defineErrorInfo(
            2491,
            "BUSINESS_ERROR_02491", 
            "定時定額買付単位金額エラー。");

    /**
     * 乖離値が刻み値*倍数以上かどうかチェック。<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_02492 = errorMgr.defineErrorInfo(
            2492,
            "BUSINESS_ERROR_02492", 
            "発注条件単価入力エラー（乖離値が指定の倍率未満）。");

    /**
     * 指値の値と、発注条件単価とで時価を挟み込んでいるかどうかチェック。<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_02493 = errorMgr.defineErrorInfo(
            2493,
            "BUSINESS_ERROR_02493", 
            "発注条件単価／注文単価入力エラー（時価の挟み込み不正）。");

    /**
     * （W指値）有効状態チェック。<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_02494 = errorMgr.defineErrorInfo(
            2494,
            "BUSINESS_ERROR_02494", 
            "訂正中にW指値注文有効状態が変更となった為、訂正不可。");

    /**
     * （W指値）執行条件取扱可能チェック。<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_02495 = errorMgr.defineErrorInfo(
            2495,
            "BUSINESS_ERROR_02495", 
            "入力された（W指値）執行条件は取扱不可。");

    /**
     * リミット注文・注文単価区分チェック。<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_02496 = errorMgr.defineErrorInfo(
            2496,
            "BUSINESS_ERROR_02496", 
            "Ｗ指値注文のリミット注文は成行指定不可。");

    /**
     * 指値と（W指値）訂正指値のチェック。<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_02498 = errorMgr.defineErrorInfo(
            2498,
            "BUSINESS_ERROR_02498", 
            "注文単価と（W指値）訂正指値が同値。");

    /**
     * Ｗ指値用執行条件チェック。<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_02499 = errorMgr.defineErrorInfo(
            2499,
            "BUSINESS_ERROR_02499", 
            "Ｗ指値用執行条件が未指定です。");

    /**
     * Ｗ指値用執行条件チェック。<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_02500 = errorMgr.defineErrorInfo(
            2500,
            "BUSINESS_ERROR_02500", 
            "W指値注文の執行条件は“無条件”、“不出来引け成行”以外指定不可。");

    /**
     * Ｗ指値用執行条件・注文単価区分チェック。<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_02501 = errorMgr.defineErrorInfo(
            2501,
            "BUSINESS_ERROR_02501", 
            "Ｗ指値用注文単価区分が”指値”以外の値です。");

    /**
     * Ｗ指値用有効状態区分チェック。<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_02502 = errorMgr.defineErrorInfo(
            2502,
            "BUSINESS_ERROR_02502", 
            "Ｗ指値有効状態区分の値が存在しないコード値です。");

    /**
     * Ｗ指値用執行条件・注文有効期限チェック。<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_02503 = errorMgr.defineErrorInfo(
            2503,
            "BUSINESS_ERROR_02503", 
            "Ｗ指値用執行条件が”無条件”以外の値です。");

    /**
     * 先物オプション注文単位がデータ不整合チェック。<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_02504 = errorMgr.defineErrorInfo(
            2504,
            "BUSINESS_ERROR_02504", 
            "ストップ注文が有効または失効済の注文は発注条件訂正不可。");

    /**
     * 注文単位IDチェック。<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_02505 = errorMgr.defineErrorInfo(
            2505,
            "BUSINESS_ERROR_02505", 
            "注文単位IDが未入力です。");

    /**
     * 注文単位IDチェック。<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_02506 = errorMgr.defineErrorInfo(
            2506,
            "BUSINESS_ERROR_02506", 
            "注文単位IDの桁数が不正です。");

    /**
     * 注文単位IDチェック。<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_02507 = errorMgr.defineErrorInfo(
            2507,
            "BUSINESS_ERROR_02507", 
            "注文単位IDが数字以外の値です。");

    /**
     * 注文状態チェック。<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_02508 = errorMgr.defineErrorInfo(
            2508,
            "BUSINESS_ERROR_02508", 
            "注文状態が数字以外の値です。");

    /**
     * 注文有効状態チェック。<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_02509 = errorMgr.defineErrorInfo(
            2509,
            "BUSINESS_ERROR_02509", 
            "注文有効状態が数字以外の値です。");

    /**
     * 暗証番号チェック。<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_02510 = errorMgr.defineErrorInfo(
            2510,
            "BUSINESS_ERROR_02510", 
            "暗証番号が未入力です。");

    /**
     * スレッド番号チェック。<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_02511 = errorMgr.defineErrorInfo(
            2511,
            "BUSINESS_ERROR_02511", 
            "スレッド番号が未指定です。");

    /**
     * スレッド番号チェック。<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_02512 = errorMgr.defineErrorInfo(
            2512,
            "BUSINESS_ERROR_02512", 
            "スレッド番号を半角数字で入力して下さい。");

    /**
     * スレッド番号チェック。<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_02513 = errorMgr.defineErrorInfo(
            2513,
            "BUSINESS_ERROR_02513", 
            "スレッド番号を18桁以下で入力して下さい。");

    /**
     * ステータスチェック。<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_02514 = errorMgr.defineErrorInfo(
            2514,
            "BUSINESS_ERROR_02514", 
            "ステータスを半角数字で入力して下さい。");

    /**
     * ステータスチェック。<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_02515 = errorMgr.defineErrorInfo(
            2515,
            "BUSINESS_ERROR_02515", 
            "ステータスを1桁で入力して下さい。");

    /**
     * 更新ステータスチェック。<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_02516 = errorMgr.defineErrorInfo(
            2516,
            "BUSINESS_ERROR_02516", 
            "更新ステータスが未指定です。");

    /**
     * 更新ステータスチェック。<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_02517 = errorMgr.defineErrorInfo(
            2517,
            "BUSINESS_ERROR_02517", 
            "更新ステータスを半角数字で入力して下さい。");

    /**
     * 更新ステータスチェック。<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_02518 = errorMgr.defineErrorInfo(
            2518,
            "BUSINESS_ERROR_02518", 
            "更新ステータスを1桁で入力して下さい。");

    /**
     * 注文状態更新情報チェック。<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_02519 = errorMgr.defineErrorInfo(
            2519,
            "BUSINESS_ERROR_02519", 
            "全ての変更項目が未指定です。");

    /**
     * 定時定額買付未実施チェック。<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_02520 = errorMgr.defineErrorInfo(
            2520,
            "BUSINESS_ERROR_02520", 
            "定時定額買付未実施エラー。");

    /**
     * 注文状態チェック。<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_02521 = errorMgr.defineErrorInfo(
            2521,
            "BUSINESS_ERROR_02521", 
            "受付中／訂正中／取消中の注文は切替処理不可。");

    /**
     * 入力区分チェック。<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_02522 = errorMgr.defineErrorInfo(
            2522,
            "BUSINESS_ERROR_02522", 
            "入力区分が存在しないコード値です。");

    /**
     * 更新対象レコード選択チェック。<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_02523 = errorMgr.defineErrorInfo(
            2523,
            "BUSINESS_ERROR_02523", 
            "更新対象レコードが選択されていません。");

    /**
     * 定時定額買付取引停止顧客エラー。<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_02524 = errorMgr.defineErrorInfo(
            2524,
            "BUSINESS_ERROR_02524", 
            "定時定額買付取引停止顧客エラー。");

    /**
     * 発注条件のチェック。<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_02525 = errorMgr.defineErrorInfo(
            2525,
            "BUSINESS_ERROR_02525", 
            "発注条件区分が“指定なし”の場合は、Ｗ指値用執行条件が指定不可です。");

    /**
     * 発注条件のチェック。<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_02526 = errorMgr.defineErrorInfo(
            2526,
            "BUSINESS_ERROR_02526", 
            "発注条件区分が“逆指値”の場合は、Ｗ指値用執行条件が指定不可です。");

    /**
     * チェック対象項目の値の未入力チェック。<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_02527 = errorMgr.defineErrorInfo(
            2527,
            "BUSINESS_ERROR_02527", 
            "自動採番指定時に、顧客コードが入力されています。");

    /**
     * 約定日との関連チェック。<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_02528 = errorMgr.defineErrorInfo(
            2528,
            "BUSINESS_ERROR_02528", 
            "約定日が受渡日を超えています。");

    /**
     * 現地約定日のチェック。<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_02529 = errorMgr.defineErrorInfo(
            2529,
            "BUSINESS_ERROR_02529", 
            "現地約定日が未指定です。");

    /**
     * 現地受渡日のチェック。<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_02530 = errorMgr.defineErrorInfo(
            2530,
            "BUSINESS_ERROR_02530", 
            "現地受渡日が未指定です。");

    /**
     * 現地約定日が現地受渡日を超えています。<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_02531 = errorMgr.defineErrorInfo(
            2531,
            "BUSINESS_ERROR_02531", 
            "現地約定日が現地受渡日を超えています。");

    /**
     * 約定取消可能期間が過ぎました。<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_02534 = errorMgr.defineErrorInfo(
            2534,
            "BUSINESS_ERROR_02534", 
            "約定取消可能期間が過ぎました。");

    /**
     * 注文ロック区分チェック。<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_02535 = errorMgr.defineErrorInfo(
            2535,
            "BUSINESS_ERROR_02535", 
            "注文ロック中です。注文ロック解除してから操作してください。");

    /**
     * 整合性チェック。<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_02539 = errorMgr.defineErrorInfo(
            2539,
            "BUSINESS_ERROR_02539", 
            "売買と取引が不正な関係です。");

    /**
     * 額面金額チェック。<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_02541 = errorMgr.defineErrorInfo(
            2541,
            "BUSINESS_ERROR_02541", 
            "額面金額が最低額面より小さいです。");

    /**
     * 額面金額チェック。<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_02542 = errorMgr.defineErrorInfo(
            2542,
            "BUSINESS_ERROR_02542", 
            "額面金額が最高額面を超えています。");

    /**
     * 額面金額チェック。<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_02543 = errorMgr.defineErrorInfo(
            2543,
            "BUSINESS_ERROR_02543", 
            "額面金額が申込単位の整数倍ではありません。");

    /**
     * 自動約定枠チェック。<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_02544 = errorMgr.defineErrorInfo(
            2544,
            "BUSINESS_ERROR_02544", 
            "すでに自動約定枠に達しています。");

    /**
     * 自動約定枠チェック。<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_02545 = errorMgr.defineErrorInfo(
            2545,
            "BUSINESS_ERROR_02545", 
            "自動約定枠を超過しています。");

    /**
     * 該当カストディアンが存在しません。<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_02547 = errorMgr.defineErrorInfo(
            2547,
            "BUSINESS_ERROR_02547", 
            "該当カストディアンが存在しません。");

    /**
     * 取引チェック。<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_02549 = errorMgr.defineErrorInfo(
            2549,
            "BUSINESS_ERROR_02549", 
            "取引が未指定です。");

    /**
     * 約定単価チェック。<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_02551 = errorMgr.defineErrorInfo(
            2551,
            "BUSINESS_ERROR_02551", 
            "約定単価は整数部４桁，小数部６桁の範囲外です。");

    /**
     * 約定数量チェック。<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_02554 = errorMgr.defineErrorInfo(
            2554,
            "BUSINESS_ERROR_02554", 
            "約定数量が11桁以内の整数値ではありません。");

    /**
     * 売買代金(外貨)チェック。<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_02555 = errorMgr.defineErrorInfo(
            2555,
            "BUSINESS_ERROR_02555", 
            "売買代金（外貨）の有効桁数が、整数部１２桁，小数部２桁の範囲外です。");

    /**
     * 売買代金(外貨)チェック。<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_02556 = errorMgr.defineErrorInfo(
            2556,
            "BUSINESS_ERROR_02556", 
            "売買代金（外貨）が数値以外の値です。");

    /**
     * 売買代金(外貨)チェック。<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_02557 = errorMgr.defineErrorInfo(
            2557,
            "BUSINESS_ERROR_02557", 
            "売買代金（外貨）が0以下の値です。");

    /**
     * 売買代金(円貨)チェック。<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_02558 = errorMgr.defineErrorInfo(
            2558,
            "BUSINESS_ERROR_02558", 
            "売買代金（円貨）のサイズが不正です。");

    /**
     * 売買代金(円貨)チェック。<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_02559 = errorMgr.defineErrorInfo(
            2559,
            "BUSINESS_ERROR_02559", 
            "売買代金（円貨）が整数以外の値です。");

    /**
     * 売買代金(円貨)チェック。<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_02560 = errorMgr.defineErrorInfo(
            2560,
            "BUSINESS_ERROR_02560", 
            "売買代金（円貨）が0以下の値です。");

    /**
     * 経過利子(外貨)チェック。<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_02561 = errorMgr.defineErrorInfo(
            2561,
            "BUSINESS_ERROR_02561", 
            "経過利子（外貨）の有効桁数が、整数部１２桁，小数部２桁の範囲外です。");

    /**
     * 経過利子(外貨)チェック。<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_02562 = errorMgr.defineErrorInfo(
            2562,
            "BUSINESS_ERROR_02562", 
            "経過利子（外貨）が数値以外の値です。");

    /**
     * 経過利子(外貨)チェック。<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_02563 = errorMgr.defineErrorInfo(
            2563,
            "BUSINESS_ERROR_02563", 
            "経過利子（外貨）が0より小さい値です。");

    /**
     * 経過利子(円貨)チェック。<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_02564 = errorMgr.defineErrorInfo(
            2564,
            "BUSINESS_ERROR_02564", 
            "経過利子（円貨）のサイズが不正です。");

    /**
     * 経過利子(円貨)チェック。<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_02565 = errorMgr.defineErrorInfo(
            2565,
            "BUSINESS_ERROR_02565", 
            "経過利子（円貨）が整数以外の値です。");

    /**
     * 経過利子(円貨)チェック。<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_02566 = errorMgr.defineErrorInfo(
            2566,
            "BUSINESS_ERROR_02566", 
            "経過利子（円貨）が0より小さい値です。");

    /**
     * 受渡代金(外貨)チェック。<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_02567 = errorMgr.defineErrorInfo(
            2567,
            "BUSINESS_ERROR_02567", 
            "受渡代金（外貨）の有効桁数が、整数部１１桁，小数部２桁の範囲外です。");

    /**
     * 受渡代金(外貨)チェック。<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_02568 = errorMgr.defineErrorInfo(
            2568,
            "BUSINESS_ERROR_02568", 
            "受渡代金（外貨）が数値以外の値です。");

    /**
     * 受渡代金(外貨)チェック。<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_02569 = errorMgr.defineErrorInfo(
            2569,
            "BUSINESS_ERROR_02569", 
            "受渡代金（外貨）が0以下の値です。");

    /**
     * 受渡代金(円貨)チェック。<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_02570 = errorMgr.defineErrorInfo(
            2570,
            "BUSINESS_ERROR_02570", 
            "受渡代金（円貨）のサイズが不正です。");

    /**
     * 受渡代金(円貨)チェック。<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_02571 = errorMgr.defineErrorInfo(
            2571,
            "BUSINESS_ERROR_02571", 
            "受渡代金（円貨）が整数以外の値です。");

    /**
     * 受渡代金(円貨)チェック。<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_02572 = errorMgr.defineErrorInfo(
            2572,
            "BUSINESS_ERROR_02572", 
            "受渡代金（円貨）が0以下の値です。");

    /**
     * 経過日数チェック。<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_02573 = errorMgr.defineErrorInfo(
            2573,
            "BUSINESS_ERROR_02573", 
            "経過日数のサイズが不正です。");

    /**
     * 経過日数チェック。<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_02574 = errorMgr.defineErrorInfo(
            2574,
            "BUSINESS_ERROR_02574", 
            "経過日数が整数以外の値です。");

    /**
     * 経過日数チェック。<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_02575 = errorMgr.defineErrorInfo(
            2575,
            "BUSINESS_ERROR_02575", 
            "経過日数が0より小さい値です。");

    /**
     * 基準日数チェック。<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_02576 = errorMgr.defineErrorInfo(
            2576,
            "BUSINESS_ERROR_02576", 
            "基準日数のサイズが不正です。");

    /**
     * 基準日数チェック。<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_02577 = errorMgr.defineErrorInfo(
            2577,
            "BUSINESS_ERROR_02577", 
            "基準日数が整数以外の値です。");

    /**
     * 基準日数チェック。<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_02578 = errorMgr.defineErrorInfo(
            2578,
            "BUSINESS_ERROR_02578", 
            "基準日数が0より小さい値です。");

    /**
     * 注文ロック区分チェック。<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_02580 = errorMgr.defineErrorInfo(
            2580,
            "BUSINESS_ERROR_02580", 
            "注文ロック区分が”ロック解除”、”ロック”以外の値です。");

    /**
     * 申込単位チェック。<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_02584 = errorMgr.defineErrorInfo(
            2584,
            "BUSINESS_ERROR_02584", 
            "申込単位のサイズが不正です。");

    /**
     * 申込単位チェック。<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_02585 = errorMgr.defineErrorInfo(
            2585,
            "BUSINESS_ERROR_02585", 
            "申込単位が整数以外の値です。");

    /**
     * 申込単位チェック。<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_02586 = errorMgr.defineErrorInfo(
            2586,
            "BUSINESS_ERROR_02586", 
            "申込単位が0以下の値です。");

    /**
     * 最低額面チェック。<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_02587 = errorMgr.defineErrorInfo(
            2587,
            "BUSINESS_ERROR_02587", 
            "最低額面のサイズが不正です。");

    /**
     * 最低額面チェック。<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_02588 = errorMgr.defineErrorInfo(
            2588,
            "BUSINESS_ERROR_02588", 
            "最低額面が整数以外の値です。");

    /**
     * 最低額面チェック。<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_02589 = errorMgr.defineErrorInfo(
            2589,
            "BUSINESS_ERROR_02589", 
            "最低額面が0より小さい値です。");

    /**
     * 最高額面チェック。<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_02590 = errorMgr.defineErrorInfo(
            2590,
            "BUSINESS_ERROR_02590", 
            "最高額面のサイズが不正です。");

    /**
     * 最高額面チェック。<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_02591 = errorMgr.defineErrorInfo(
            2591,
            "BUSINESS_ERROR_02591", 
            "最高額面が整数以外の値です。");

    /**
     * 最高額面チェック。<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_02592 = errorMgr.defineErrorInfo(
            2592,
            "BUSINESS_ERROR_02592", 
            "最高額面が0より小さい値です。");

    /**
     * 買付受渡日指定値チェック。<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_02593 = errorMgr.defineErrorInfo(
            2593,
            "BUSINESS_ERROR_02593", 
            "買付受渡日指定値のサイズが不正です。");

    /**
     * 買付受渡日指定値チェック。<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_02594 = errorMgr.defineErrorInfo(
            2594,
            "BUSINESS_ERROR_02594", 
            "買付受渡日指定値が整数以外の値です。");

    /**
     * 買付受渡日指定値チェック。<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_02595 = errorMgr.defineErrorInfo(
            2595,
            "BUSINESS_ERROR_02595", 
            "買付受渡日指定値が０より小さいです。");

    /**
     * 売却受渡日指定値チェック。<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_02596 = errorMgr.defineErrorInfo(
            2596,
            "BUSINESS_ERROR_02596", 
            "売却受渡日指定値のサイズが不正です。");

    /**
     * 売却受渡日指定値チェック。<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_02597 = errorMgr.defineErrorInfo(
            2597,
            "BUSINESS_ERROR_02597", 
            "売却受渡日指定値が整数以外の値です。");

    /**
     * 売却受渡日指定値チェック。<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_02598 = errorMgr.defineErrorInfo(
            2598,
            "BUSINESS_ERROR_02598", 
            "売却受渡日指定値が０より小さいです。");

    /**
     * 仲介手数料率チェック。<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_02599 = errorMgr.defineErrorInfo(
            2599,
            "BUSINESS_ERROR_02599", 
            "仲介手数料率の有効桁数が、整数部２桁，小数部５桁の範囲外です。");

    /**
     * 仲介手数料率チェック。<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_02600 = errorMgr.defineErrorInfo(
            2600,
            "BUSINESS_ERROR_02600", 
            "仲介手数料率が数値以外の値です。");

    /**
     * 仲介手数料率チェック。<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_02601 = errorMgr.defineErrorInfo(
            2601,
            "BUSINESS_ERROR_02601", 
            "仲介手数料率が０より小さいです。");

    /**
     * 自動約定枠チェック。<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_02602 = errorMgr.defineErrorInfo(
            2602,
            "BUSINESS_ERROR_02602", 
            "自動約定枠のサイズが不正です。");

    /**
     * 自動約定枠チェック。<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_02603 = errorMgr.defineErrorInfo(
            2603,
            "BUSINESS_ERROR_02603", 
            "自動約定枠が整数以外の値です。");

    /**
     * 自動約定枠チェック。<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_02604 = errorMgr.defineErrorInfo(
            2604,
            "BUSINESS_ERROR_02604", 
            "自動約定枠が0より小さい値です。");

    /**
     * 自動採番のデータが取得できませんでした。<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_02608 = errorMgr.defineErrorInfo(
            2608,
            "BUSINESS_ERROR_02608", 
            "自動採番のデータが取得できませんでした。");

    /**
     * 最大値を超えていないチェック。<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_02609 = errorMgr.defineErrorInfo(
            2609,
            "BUSINESS_ERROR_02609", 
            "最大値を超えたため、自動採番できませんでした。");

    /**
     * 自動採番選択時に、顧客が個人で国内居住者かチェック。<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_02610 = errorMgr.defineErrorInfo(
            2610,
            "BUSINESS_ERROR_02610", 
            "自動採番できません。");

    /**
     * 応募不可銘柄エラー。<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_02612 = errorMgr.defineErrorInfo(
            2612,
            "BUSINESS_ERROR_02612", 
            "応募不可銘柄エラー。");

    /**
     * 買付不可銘柄エラー。<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_02613 = errorMgr.defineErrorInfo(
            2613,
            "BUSINESS_ERROR_02613", 
            "買付不可銘柄エラー。");

    /**
     * 売却不可銘柄エラー。<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_02614 = errorMgr.defineErrorInfo(
            2614,
            "BUSINESS_ERROR_02614", 
            "売却不可銘柄エラー。");

    /**
     * 売買チェック。<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_02616 = errorMgr.defineErrorInfo(
            2616,
            "BUSINESS_ERROR_02616", 
            "売買が未指定です。");

    /**
     * 発行日前に売却することはできません。<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_02618 = errorMgr.defineErrorInfo(
            2618,
            "BUSINESS_ERROR_02618", 
            "発行日前に売却することはできません。");

    /**
     * 注文約定区分を判定。<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_02619 = errorMgr.defineErrorInfo(
            2619,
            "BUSINESS_ERROR_02619", 
            "対象注文は既に未約定ではありません。");

    /**
     * 部店プリファ@レンスを判定。<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_02620 = errorMgr.defineErrorInfo(
            2620,
            "BUSINESS_ERROR_02620", 
            "注文ロック区分を使用する部店ではありません。");

    /**
     * 取扱開始日時が取扱終了日時を超えています。<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_02621 = errorMgr.defineErrorInfo(
            2621,
            "BUSINESS_ERROR_02621", 
            "取扱開始日時が取扱終了日時を超えています。");

    /**
     * 応募開始日が応募終了日を超えています。<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_02622 = errorMgr.defineErrorInfo(
            2622,
            "BUSINESS_ERROR_02622", 
            "応募開始日が応募終了日を超えています。");

    /**
     * 応募開始日が応募開始日(SONAR)と応募終了日(SONAR)の範囲外です。<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_02623 = errorMgr.defineErrorInfo(
            2623,
            "BUSINESS_ERROR_02623", 
            "応募開始日が応募開始日(SONAR)と応募終了日(SONAR)の範囲外です。");

    /**
     * 応募終了日が応募開始日(SONAR)と応募終了日(SONAR)の範囲外です。<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_02624 = errorMgr.defineErrorInfo(
            2624,
            "BUSINESS_ERROR_02624", 
            "応募終了日が応募開始日(SONAR)と応募終了日(SONAR)の範囲外です。");

    /**
     * 最低額面が最高額面を超えています。<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_02625 = errorMgr.defineErrorInfo(
            2625,
            "BUSINESS_ERROR_02625", 
            "最低額面が最高額面を超えています。");

    /**
     * 発注状況区分チェック。<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_02626 = errorMgr.defineErrorInfo(
            2626,
            "BUSINESS_ERROR_02626", 
            "発注状況指定が不正。");

    /**
     * 歩み値照合時間Fromチェック。<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_02627 = errorMgr.defineErrorInfo(
            2627,
            "BUSINESS_ERROR_02627", 
            "入力時間エラー(歩み値照合時間From)。");

    /**
     * 歩み値照合時間Toチェック。<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_02628 = errorMgr.defineErrorInfo(
            2628,
            "BUSINESS_ERROR_02628", 
            "入力時間エラー(歩み値照合時間To)。");

    /**
     * 歩み値照合区分チェック。<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_02629 = errorMgr.defineErrorInfo(
            2629,
            "BUSINESS_ERROR_02629", 
            "歩み値照合区分が未定義の値。");

    /**
     * 口座区分一覧のチェック。<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_02630 = errorMgr.defineErrorInfo(
            2630,
            "BUSINESS_ERROR_02630", 
            "口座区分一覧の要素数が０です。");

    /**
     * 約定日が非営業日です。<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_02631 = errorMgr.defineErrorInfo(
            2631,
            "BUSINESS_ERROR_02631", 
            "約定日が非営業日です。");

    /**
     * 約定変更可能期間が過ぎました。<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_02632 = errorMgr.defineErrorInfo(
            2632,
            "BUSINESS_ERROR_02632", 
            "約定変更可能期間が過ぎました。");

    /**
     * 注文ロック区分チェック。<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_02633 = errorMgr.defineErrorInfo(
            2633,
            "BUSINESS_ERROR_02633", 
            "注文ロック解除中です。注文ロックしてから操作してください。");

    /**
     * 額面金額チェック。<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_02634 = errorMgr.defineErrorInfo(
            2634,
            "BUSINESS_ERROR_02634", 
            "額面金額が未指定です。");

    /**
     * 額面金額チェック。<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_02635 = errorMgr.defineErrorInfo(
            2635,
            "BUSINESS_ERROR_02635", 
            "額面金額のサイズが不正です。");

    /**
     * 額面金額チェック。<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_02636 = errorMgr.defineErrorInfo(
            2636,
            "BUSINESS_ERROR_02636", 
            "額面金額が0以下の値です。");

    /**
     * 約定日チェック。<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_02637 = errorMgr.defineErrorInfo(
            2637,
            "BUSINESS_ERROR_02637", 
            "約定日が未指定です。");

    /**
     * 単価チェック。<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_02638 = errorMgr.defineErrorInfo(
            2638,
            "BUSINESS_ERROR_02638", 
            "単価の有効桁数が整数部４桁，小数部６桁の範囲外です。");

    /**
     * 専用振込先口座変更未使用口座番号取得エラー。<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_02639 = errorMgr.defineErrorInfo(
            2639,
            "BUSINESS_ERROR_02639", 
            "未使用の専用振込先口座番号が取得できません。");

    /**
     * 専用振込先口座変更口座番号更新エラー。<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_02640 = errorMgr.defineErrorInfo(
            2640,
            "BUSINESS_ERROR_02640", 
            "口座番号は既に使用されています。");

    /**
     * 額面金額チェック。<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_02641 = errorMgr.defineErrorInfo(
            2641,
            "BUSINESS_ERROR_02641", 
            "額面金額が整数値ではありません。");

    /**
     * 取引チェック。<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_02642 = errorMgr.defineErrorInfo(
            2642,
            "BUSINESS_ERROR_02642", 
            "取引の値が存在しないコード値です。");

    /**
     * 為替レートチェック。<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_02643 = errorMgr.defineErrorInfo(
            2643,
            "BUSINESS_ERROR_02643", 
            "為替レートが未指定です。");

    /**
     * 専用振込先口座変更口座番号取得エラー。<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_02644 = errorMgr.defineErrorInfo(
            2644,
            "BUSINESS_ERROR_02644", 
            "専用振込先口座番号が存在しません。");

    /**
     * 申告上限株数が「公募数量 + 売出数量」の範囲内であるかチェック。<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_02645 = errorMgr.defineErrorInfo(
            2645,
            "BUSINESS_ERROR_02645", 
            "申告上限株数が「公募数量 + 売出数量」の範囲外です。");

    /**
     * 乗換以外の発注日チェック。<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_02646 = errorMgr.defineErrorInfo(
            2646,
            "BUSINESS_ERROR_02646", 
            "投信注文単位の発注日が現在の発注日より小さい値です。");

    /**
     * 乗換以外の発注日チェック。<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_02647 = errorMgr.defineErrorInfo(
            2647,
            "BUSINESS_ERROR_02647", 
            "投信注文単位の発注日と現在の発注日が一致していないです。");

    /**
     * 二重注文チェック。<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_02648 = errorMgr.defineErrorInfo(
            2648,
            "BUSINESS_ERROR_02648", 
            "二重注文エラー。");

    /**
     * 注文受付不可の状態。<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_02649 = errorMgr.defineErrorInfo(
            2649,
            "BUSINESS_ERROR_02649", 
            "注文受付不可の状態。");

    /**
     * 外国株式市場連動状況一覧チェック。<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_02650 = errorMgr.defineErrorInfo(
            2650,
            "BUSINESS_ERROR_02650", 
            "外国株式市場連動状況一覧が未指定です。");

    /**
     * 経路区分チェック。<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_02651 = errorMgr.defineErrorInfo(
            2651,
            "BUSINESS_ERROR_02651", 
            "経路区分が未定義の値。");

    /**
     * 経路区分と受付区分の組み合わせが不正。<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_02652 = errorMgr.defineErrorInfo(
            2652,
            "BUSINESS_ERROR_02652", 
            "経路区分と受付区分の組み合わせが不正。");

    /**
     * 外国株式市場連動区分チェック。<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_02653 = errorMgr.defineErrorInfo(
            2653,
            "BUSINESS_ERROR_02653", 
            "外国株式市場連動区分が未指定です。");

    /**
     * 外国株式市場連動区分チェック。<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_02654 = errorMgr.defineErrorInfo(
            2654,
            "BUSINESS_ERROR_02654", 
            "外国株式市場連動区分が未定義の値。");

    /**
     * キューID一覧チェック。<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_02656 = errorMgr.defineErrorInfo(
            2656,
            "BUSINESS_ERROR_02656", 
            "キューID一覧が未指定です。");

    /**
     * FAX番号半角数字チェック。<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_02658 = errorMgr.defineErrorInfo(
            2658,
            "BUSINESS_ERROR_02658", 
            "FAX番号が半角数字とハイフン文字（’-’）ではありません。");

    /**
     * 口座開設の動機@の詳細文字数チェック。<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_02659 = errorMgr.defineErrorInfo(
            2659,
            "BUSINESS_ERROR_02659", 
            "口座開設の動機@の詳細の文字数が25（50byte）より大きいです。");

    /**
     * 通貨コードチェック。<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_02660 = errorMgr.defineErrorInfo(
            2660,
            "BUSINESS_ERROR_02660", 
            "通貨コードが未指定(null)または不正な値です。");

    /**
     * 銘柄チェック。<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_02661 = errorMgr.defineErrorInfo(
            2661,
            "BUSINESS_ERROR_02661", 
            "顧客取扱不可銘柄エラー。");

    /**
     * 入金日チェック。<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_02662 = errorMgr.defineErrorInfo(
            2662,
            "BUSINESS_ERROR_02662", 
            "入金日が受渡日を超えています。");

    /**
     * 仕入時の為替レートチェック。<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_02663 = errorMgr.defineErrorInfo(
            2663,
            "BUSINESS_ERROR_02663", 
            "仕入時の為替レートの有効桁数が、整数部３桁，小数部４桁の範囲外です。");

    /**
     * 仕入時の為替レートチェック。<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_02664 = errorMgr.defineErrorInfo(
            2664,
            "BUSINESS_ERROR_02664", 
            "仕入時の為替レートが数値以外の値です。");

    /**
     * 仕入時の為替レートチェック。<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_02665 = errorMgr.defineErrorInfo(
            2665,
            "BUSINESS_ERROR_02665", 
            "仕入時の為替レートが0以下の値です。");

    /**
     * 損益明細データ未存在エラー。<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_02666 = errorMgr.defineErrorInfo(
            2666,
            "BUSINESS_ERROR_02666", 
            "検索条件に合致する損益明細データはございません。");

    /**
     * 海外市場営業日チェック。<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_02667 = errorMgr.defineErrorInfo(
            2667,
            "BUSINESS_ERROR_02667", 
            "発注日が海外市場営業日ではありません。");

    /**
     * 口座種別チェック。<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_02668 = errorMgr.defineErrorInfo(
            2668,
            "BUSINESS_ERROR_02668", 
            "口座種別が存在しないコード値です。");

    /**
     * データ内容チェック。<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_02669 = errorMgr.defineErrorInfo(
            2669,
            "BUSINESS_ERROR_02669", 
            "データ内容が存在しないコード値です。");

    /**
     * ログインロック区分チェック。<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_02670 = errorMgr.defineErrorInfo(
            2670,
            "BUSINESS_ERROR_02670", 
            "ログインロック区分が存在しないコード値です。");

    /**
     * FAX番号チェック。<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_02671 = errorMgr.defineErrorInfo(
            2671,
            "BUSINESS_ERROR_02671", 
            "ハイフンの数が２つではありません。");

    /**
     * 為替口座の純資産残高不足エラー。<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_02672 = errorMgr.defineErrorInfo(
            2672,
            "BUSINESS_ERROR_02672", 
            "純資産残高が不足しています。");

    /**
     * 為替口座の現金残高不足エラー。<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_02673 = errorMgr.defineErrorInfo(
            2673,
            "BUSINESS_ERROR_02673", 
            "円貨現金残高が不足しています。");

    /**
     * 為替口座にマイナス通貨ありエラー。<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_02674 = errorMgr.defineErrorInfo(
            2674,
            "BUSINESS_ERROR_02674", 
            "為替口座における通貨別現金残高で、マイナスの通貨があります。為替口座でコンバージョン後に再度振替を依頼してください。");

    /**
     * 該当する先OP証拠金口座が存在しないエラー。<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_02675 = errorMgr.defineErrorInfo(
            2675,
            "BUSINESS_ERROR_02675", 
            "為替口座への振替です。この機@能は為替口座を開設し、かつ振替同意書に記入・ご送付いただいたお客様しかご利用いただくことはできません。");

    /**
     * 【FX】上記以外でユーザーに起因するエラー。<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_02676 = errorMgr.defineErrorInfo(
            2676,
            "BUSINESS_ERROR_02676", 
            "振替に失敗しました。（システムエラー）");

    /**
     * 単価チェック。<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_02677 = errorMgr.defineErrorInfo(
            2677,
            "BUSINESS_ERROR_02677", 
            "外貨建の銘柄ではありません。単価を整数にして下さい。");

    /**
     * 為替レートチェック。<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_02678 = errorMgr.defineErrorInfo(
            2678,
            "BUSINESS_ERROR_02678", 
            "外貨建の銘柄ではありません。為替レートは入力不可です。");

    /**
     * 特定日取引銘柄区分のチェック。<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_02679 = errorMgr.defineErrorInfo(
            2679,
            "BUSINESS_ERROR_02679", 
            "特定日取引銘柄区分の値が存在しないコード値です。");

    /**
     * 変更項目なしエラー。<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_02680 = errorMgr.defineErrorInfo(
            2680,
            "BUSINESS_ERROR_02680", 
            "変更項目がありません。");

    /**
     * 入金日が非営業日です。<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_02681 = errorMgr.defineErrorInfo(
            2681,
            "BUSINESS_ERROR_02681", 
            "入金日が非営業日です。");

    /**
     * 取引責任者郵便番号のチェック。<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_02682 = errorMgr.defineErrorInfo(
            2682,
            "BUSINESS_ERROR_02682", 
            "取引責任者郵便番号が7byte半角数字ではありません。");

    /**
     * 取引責任者会社直通番号のチェック。<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_02683 = errorMgr.defineErrorInfo(
            2683,
            "BUSINESS_ERROR_02683", 
            "取引責任者会社直通番号が半角数字とハイフン文字（’-’）ではありません。");

    /**
     * 取引責任者会社直通番号のチェック。<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_02684 = errorMgr.defineErrorInfo(
            2684,
            "BUSINESS_ERROR_02684", 
            "ハイフンの数が２つではありません。");

    /**
     * その他連絡先(携帯、自宅等)番号のチェック。<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_02685 = errorMgr.defineErrorInfo(
            2685,
            "BUSINESS_ERROR_02685", 
            "その他連絡先(携帯、自宅等)番号が半角数字とハイフン文字（’-’）ではありません。");

    /**
     * その他連絡先(携帯、自宅等)番号のチェック。<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_02686 = errorMgr.defineErrorInfo(
            2686,
            "BUSINESS_ERROR_02686", 
            "ハイフンの数が２つではありません。");

    /**
     * 取引責任者年号+生年月日のチェック。<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_02687 = errorMgr.defineErrorInfo(
            2687,
            "BUSINESS_ERROR_02687", 
            "取引責任者 年号かつ取引責任者生年月日が未指定(null)または不正な値です。");

    /**
     * 変更項目無しエラー。<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_02688 = errorMgr.defineErrorInfo(
            2688,
            "BUSINESS_ERROR_02688", 
            "変更項目無しエラー。");

    /**
     * 受渡日の発注日以降チェック。<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_02689 = errorMgr.defineErrorInfo(
            2689,
            "BUSINESS_ERROR_02689", 
            "受渡日は発注日以降にして下さい。");

    /**
     * 入金日の当日以降チェック。<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_02690 = errorMgr.defineErrorInfo(
            2690,
            "BUSINESS_ERROR_02690", 
            "入金日は当日以降にして下さい。");

    /**
     * 該当注文は一部約定未済の状態。<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_02691 = errorMgr.defineErrorInfo(
            2691,
            "BUSINESS_ERROR_02691", 
            "該当注文は一部約定未済の状態。");

    /**
     * 市場配列が0件です。<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_02692 = errorMgr.defineErrorInfo(
            2692,
            "BUSINESS_ERROR_02692", 
            "市場配列が0件です。");

    /**
     * 約定日の当日以前チェック。<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_02693 = errorMgr.defineErrorInfo(
            2693,
            "BUSINESS_ERROR_02693", 
            "約定日が発注日を越えています。");

    /**
     * 目論見書閲覧区分のチェック。<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_02694 = errorMgr.defineErrorInfo(
            2694,
            "BUSINESS_ERROR_02694", 
            "目論見書閲覧区分が不正なコード値です。");

    /**
     * 未承認の申請存在チェック。<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_02695 = errorMgr.defineErrorInfo(
            2695,
            "BUSINESS_ERROR_02695", 
            "同一注文に対する未承認の申請が存在します。");

    /**
     * 既存画面からの承認者による完了リクエスト時チェック。<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_02696 = errorMgr.defineErrorInfo(
            2696,
            "BUSINESS_ERROR_02696", 
            "コンプライアンス審査状況照会画面から実行してください。");

    /**
     * 審査データの注文状態が取消済、承認済、否認済の場合エラー。<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_02697 = errorMgr.defineErrorInfo(
            2697,
            "BUSINESS_ERROR_02697", 
            "更新済みのため、受付できません。");

    /**
     * 審査データの注文状態と変更する注文状態が同一である場合エラー。<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_02698 = errorMgr.defineErrorInfo(
            2698,
            "BUSINESS_ERROR_02698", 
            "処理済みのため、受付できません。");

    /**
     * 承認者または申請者であることのチェック時。<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_02699 = errorMgr.defineErrorInfo(
            2699,
            "BUSINESS_ERROR_02699", 
            "コンプライアンス処理の権限がありません。");

    /**
     * 入金日の約定日以降チェック。<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_02700 = errorMgr.defineErrorInfo(
            2700,
            "BUSINESS_ERROR_02700", 
            "入金日は約定日以降にして下さい。");

    /**
     * 発注状況区分チェック。<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_02701 = errorMgr.defineErrorInfo(
            2701,
            "BUSINESS_ERROR_02701", 
            "W指値注文は発注状況区分：発注審査エラー指定不可。");

    /**
     * 取得した市場がnull。<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_02702 = errorMgr.defineErrorInfo(
            2702,
            "BUSINESS_ERROR_02702", 
            "優先市場が未指定です。");

    /**
     * 摘要の最大サイズチェック。<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_02703 = errorMgr.defineErrorInfo(
            2703,
            "BUSINESS_ERROR_02703", 
            "摘要が最大サイズを超えています。");

    /**
     * 注文単位テーブル区分チェック。<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_02704 = errorMgr.defineErrorInfo(
            2704,
            "BUSINESS_ERROR_02704", 
            "注文単位テーブルが未選択です。");

    /**
     * 注文単位テーブル区分チェック。<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_02705 = errorMgr.defineErrorInfo(
            2705,
            "BUSINESS_ERROR_02705", 
            "注文単位テーブルが存在しない区分です。");

    /**
     * 照会不可。<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_02706 = errorMgr.defineErrorInfo(
            2706,
            "BUSINESS_ERROR_02706", 
            "照会不可。");

    /**
     * 執行単価がnull。<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_02707 = errorMgr.defineErrorInfo(
            2707,
            "BUSINESS_ERROR_02707", 
            "執行単価がnull。");

    /**
     * 居住者申込みチェックエラー。<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_02708 = errorMgr.defineErrorInfo(
            2708,
            "BUSINESS_ERROR_02708", 
            "非居住者は申込むことができません。");

    /**
     * キャンペーン名称のチェック。<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_02709 = errorMgr.defineErrorInfo(
            2709,
            "BUSINESS_ERROR_02709", 
            "キャンペーン名称桁数エラー。");

    /**
     * 更新処理フラグのチェック。<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_02710 = errorMgr.defineErrorInfo(
            2710,
            "BUSINESS_ERROR_02710", 
            "更新処理フラグの値が存在しないコード値です。");

    /**
     * 登録タイプのチェック。<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_02711 = errorMgr.defineErrorInfo(
            2711,
            "BUSINESS_ERROR_02711", 
            "登録タイプが'1'(個別顧客指定) か '2'(強制個別顧客指定)以外の値です。");

    /**
     * キャンペーン名称のチェック。<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_02712 = errorMgr.defineErrorInfo(
            2712,
            "BUSINESS_ERROR_02712", 
            "キャンペーン名称未入力エラー。");

    /**
     * 対象期間From、Toのチェック。<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_02713 = errorMgr.defineErrorInfo(
            2713,
            "BUSINESS_ERROR_02713", 
            "対象期間未入力エラー。");

    /**
     * 対象期間From、Toのチェック。<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_02714 = errorMgr.defineErrorInfo(
            2714,
            "BUSINESS_ERROR_02714", 
            "対象期間日付未存在エラー。");

    /**
     * 対象期間From、Toのチェック。<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_02715 = errorMgr.defineErrorInfo(
            2715,
            "BUSINESS_ERROR_02715", 
            "対象期間エラー。");

    /**
     * 手数料割引キャンペーン条件IDのチェック。<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_02716 = errorMgr.defineErrorInfo(
            2716,
            "BUSINESS_ERROR_02716", 
            "手数料割引キャンペーン条件IDが未指定です。");

    /**
     * 対象期間Toのチェック。<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_02717 = errorMgr.defineErrorInfo(
            2717,
            "BUSINESS_ERROR_02717", 
            "対象期間Toの日付が現在の日付より過去日付です。");

    /**
     * 商品コードのチェック。<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_02718 = errorMgr.defineErrorInfo(
            2718,
            "BUSINESS_ERROR_02718", 
            "商品未選択エラー。");

    /**
     * 口座開設区分のチェック。<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_02719 = errorMgr.defineErrorInfo(
            2719,
            "BUSINESS_ERROR_02719", 
            "口座開設区分エラー。");

    /**
     * 口座開設日のチェック。<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_02720 = errorMgr.defineErrorInfo(
            2720,
            "BUSINESS_ERROR_02720", 
            "口座開設日エラー。");

    /**
     * 更新処理フラグのチェック。<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_02721 = errorMgr.defineErrorInfo(
            2721,
            "BUSINESS_ERROR_02721", 
            "更新処理フラグが'0' か '1'以外の値です。");

    /**
     * 登録タイプのチェック。<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_02722 = errorMgr.defineErrorInfo(
            2722,
            "BUSINESS_ERROR_02722", 
            "登録タイプが'0'以外の値です。");

    /**
     * 情報に変更がありません。<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_02723 = errorMgr.defineErrorInfo(
            2723,
            "BUSINESS_ERROR_02723", 
            "情報に変更がありません。");

    /**
     * キャンペーンが既に存在する。<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_02724 = errorMgr.defineErrorInfo(
            2724,
            "BUSINESS_ERROR_02724", 
            "キャンペーンが既に存在する。");

    /**
     * キャンペーン名称入力エラー。<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_02725 = errorMgr.defineErrorInfo(
            2725,
            "BUSINESS_ERROR_02725", 
            "キャンペーン名称入力エラー。");

    /**
     * キャンペーン条件チェック。<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_02726 = errorMgr.defineErrorInfo(
            2726,
            "BUSINESS_ERROR_02726", 
            "キャンペーン条件は既に登録済みです。");

    /**
     * キャンペーンは既に削除されています。<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_02727 = errorMgr.defineErrorInfo(
            2727,
            "BUSINESS_ERROR_02727", 
            "キャンペーンは既に削除されています。");

    /**
     * 部店コードの要素数チェック。<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_02728 = errorMgr.defineErrorInfo(
            2728,
            "BUSINESS_ERROR_02728", 
            "部店コードの要素数が1以外です。");

    /**
     * 受渡日チェック。<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_02729 = errorMgr.defineErrorInfo(
            2729,
            "BUSINESS_ERROR_02729", 
            "受渡日が数字以外の値です。");

    /**
     * 受渡日エラー(存在しない日付)。<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_02730 = errorMgr.defineErrorInfo(
            2730,
            "BUSINESS_ERROR_02730", 
            "受渡日エラー(存在しない日付)。");

    /**
     * （外）契約書未徴収エラー。<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_02731 = errorMgr.defineErrorInfo(
            2731,
            "BUSINESS_ERROR_02731", 
            "（外）契約書未徴収エラー。");

    /**
     * 外貨MMF受渡方法@チェックエラー。<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_02732 = errorMgr.defineErrorInfo(
            2732,
            "BUSINESS_ERROR_02732", 
            "外貨MMF受渡方法@チェックエラー。");

    /**
     * 外貨MMF二重注文エラー。<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_02733 = errorMgr.defineErrorInfo(
            2733,
            "BUSINESS_ERROR_02733", 
            "外貨MMF二重注文エラー。");

    /**
     * キャンペーン実施中開始日付変更不可。<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_02734 = errorMgr.defineErrorInfo(
            2734,
            "BUSINESS_ERROR_02734", 
            "キャンペーン実施中の対象期間From 及び 口座開設日Fromの変更はできません。");

    /**
     * 過去期間データ変更・削除不可。<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_02735 = errorMgr.defineErrorInfo(
            2735,
            "BUSINESS_ERROR_02735", 
            "過去期間のデータは変更・削除できません。");

    /**
     * 口座開設日To設定値エラー。<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_02736 = errorMgr.defineErrorInfo(
            2736,
            "BUSINESS_ERROR_02736", 
            "口座開設日Toが過去日付です。");

    /**
     * 口座開設日To設定値エラー。<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_02737 = errorMgr.defineErrorInfo(
            2737,
            "BUSINESS_ERROR_02737", 
            "口座開設日Toと口座開設経過期間を足した日付が適用開始日より過去の日付です。");

    /**
     * 口座開設日To設定値エラー。<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_02738 = errorMgr.defineErrorInfo(
            2738,
            "BUSINESS_ERROR_02738", 
            "口座開設日Toが適用開始日より過去の日付です。");

    /**
     * 口座開設日From設定値エラー。<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_02739 = errorMgr.defineErrorInfo(
            2739,
            "BUSINESS_ERROR_02739", 
            "口座開設日Fromと口座開設経過期間を足した日付が適用開始日以前の日付です。");

    /**
     * 期間指定To設定値エラー。<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_02740 = errorMgr.defineErrorInfo(
            2740,
            "BUSINESS_ERROR_02740", 
            "期間指定Toが適用開始日より過去の日付です。");

    /**
     * 期間指定To設定値エラー。<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_02741 = errorMgr.defineErrorInfo(
            2741,
            "BUSINESS_ERROR_02741", 
            "期間指定Toが口座開設日To以前の日付です。");

    /**
     * 口座開設日From設定値エラー。<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_02742 = errorMgr.defineErrorInfo(
            2742,
            "BUSINESS_ERROR_02742", 
            "口座開設日From は現在より過去日付には設定できません。");

    /**
     * 口座開設日From過去日エラー。<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_02744 = errorMgr.defineErrorInfo(
            2744,
            "BUSINESS_ERROR_02744", 
            "口座開設日Fromが過去日付のデータは変更できません。");

    /**
     * 外貨最低金額（新規買付）エラー。<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_02745 = errorMgr.defineErrorInfo(
            2745,
            "BUSINESS_ERROR_02745", 
            "外貨最低金額（新規買付）エラー。");

    /**
     * 外貨単位金額（新規買付）エラー。<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_02746 = errorMgr.defineErrorInfo(
            2746,
            "BUSINESS_ERROR_02746", 
            "外貨単位金額（新規買付）エラー。");

    /**
     * 外貨最低金額（追加買付）エラー。<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_02747 = errorMgr.defineErrorInfo(
            2747,
            "BUSINESS_ERROR_02747", 
            "外貨最低金額（追加買付）エラー。");

    /**
     * 外貨単位金額（追加買付）エラー。<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_02748 = errorMgr.defineErrorInfo(
            2748,
            "BUSINESS_ERROR_02748", 
            "外貨単位金額（追加買付）エラー。");

    /**
     * 外貨最低金額（解約）エラー。<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_02749 = errorMgr.defineErrorInfo(
            2749,
            "BUSINESS_ERROR_02749", 
            "外貨最低金額（解約）エラー。");

    /**
     * 外貨単位金額（解約）エラー。<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_02750 = errorMgr.defineErrorInfo(
            2750,
            "BUSINESS_ERROR_02750", 
            "外貨単位金額（解約）エラー。");

    /**
     * 振込先（銀行口座）登録チェック。<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_02751 = errorMgr.defineErrorInfo(
            2751,
            "BUSINESS_ERROR_02751", 
            "振込先（銀行口座）登録が円貨登録ではありません。");

    /**
     * 注文単位が取得出来ない。<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_02752 = errorMgr.defineErrorInfo(
            2752,
            "BUSINESS_ERROR_02752", 
            "注文単位が取得出来ない。");

    /**
     * 承認状態チェック。<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_02753 = errorMgr.defineErrorInfo(
            2753,
            "BUSINESS_ERROR_02753", 
            "承認状態が未定義の値。");

    /**
     * 作成日時From/To整合性チェック。<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_02754 = errorMgr.defineErrorInfo(
            2754,
            "BUSINESS_ERROR_02754", 
            "入力日付エラー（作成日時From）。");

    /**
     * 作成日時From/To整合性チェック。<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_02755 = errorMgr.defineErrorInfo(
            2755,
            "BUSINESS_ERROR_02755", 
            "入力日付エラー（作成日時To）。");

    /**
     * 承認日時From/To整合性チェック。<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_02756 = errorMgr.defineErrorInfo(
            2756,
            "BUSINESS_ERROR_02756", 
            "入力日付エラー（承認日時From）。");

    /**
     * 承認日時From/To整合性チェック。<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_02757 = errorMgr.defineErrorInfo(
            2757,
            "BUSINESS_ERROR_02757", 
            "入力日付エラー（承認日時To）。");

    /**
     * 強制決済理由チェック。<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_02758 = errorMgr.defineErrorInfo(
            2758,
            "BUSINESS_ERROR_02758", 
            "強制決済理由が未定義の値。");

    /**
     * 注文エラー理由コードチェック。<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_02759 = errorMgr.defineErrorInfo(
            2759,
            "BUSINESS_ERROR_02759", 
            "注文エラー理由コードが未定義の値。");

    /**
     * 承認区分チェック。<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_02760 = errorMgr.defineErrorInfo(
            2760,
            "BUSINESS_ERROR_02760", 
            "承認区分が未定義の値。");

    /**
     * 承認区分チェック。<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_02761 = errorMgr.defineErrorInfo(
            2761,
            "BUSINESS_ERROR_02761", 
            "承認区分がnull。");

    /**
     * 強制決済処理区分チェック。<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_02762 = errorMgr.defineErrorInfo(
            2762,
            "BUSINESS_ERROR_02762", 
            "強制決済処理区分がnull。");

    /**
     * 指値区分チェック。<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_02763 = errorMgr.defineErrorInfo(
            2763,
            "BUSINESS_ERROR_02763", 
            "指値区分の値が存在しないコード値です。");

    /**
     * 買付単価チェック。<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_02764 = errorMgr.defineErrorInfo(
            2764,
            "BUSINESS_ERROR_02764", 
            "買付単価が未指定です。");

    /**
     * 買付単価チェック。<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_02765 = errorMgr.defineErrorInfo(
            2765,
            "BUSINESS_ERROR_02765", 
            "買付単価の有効桁数が、整数部４桁，小数部６桁の範囲外です。");

    /**
     * 買付単価チェック。<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_02766 = errorMgr.defineErrorInfo(
            2766,
            "BUSINESS_ERROR_02766", 
            "買付単価が数値以外の値です。");

    /**
     * 買付単価チェック。<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_02767 = errorMgr.defineErrorInfo(
            2767,
            "BUSINESS_ERROR_02767", 
            "買付単価が0以下の値です。");

    /**
     * 推奨フラグチェック。<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_02768 = errorMgr.defineErrorInfo(
            2768,
            "BUSINESS_ERROR_02768", 
            "推奨フラグの値が存在しないコード値です。");

    /**
     * 為替リスクフラグチェック。<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_02769 = errorMgr.defineErrorInfo(
            2769,
            "BUSINESS_ERROR_02769", 
            "為替リスクフラグの値が存在しないコード値です。");

    /**
     * 売却単価チェック。<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_02770 = errorMgr.defineErrorInfo(
            2770,
            "BUSINESS_ERROR_02770", 
            "売却単価が未指定です。");

    /**
     * 売却単価チェック。<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_02771 = errorMgr.defineErrorInfo(
            2771,
            "BUSINESS_ERROR_02771", 
            "売却単価の有効桁数が、整数部４桁，小数部６桁の範囲外です。");

    /**
     * 売却単価チェック。<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_02772 = errorMgr.defineErrorInfo(
            2772,
            "BUSINESS_ERROR_02772", 
            "売却単価が数値以外の値です。");

    /**
     * 売却単価チェック。<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_02773 = errorMgr.defineErrorInfo(
            2773,
            "BUSINESS_ERROR_02773", 
            "売却単価が0以下の値です。");

    /**
     * 強制決済処理区分チェック。<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_02774 = errorMgr.defineErrorInfo(
            2774,
            "BUSINESS_ERROR_02774", 
            "強制決済処理区分が未定義の値。");

    /**
     * 証券会社コードチェック。<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_02775 = errorMgr.defineErrorInfo(
            2775,
            "BUSINESS_ERROR_02775", 
            "証券会社コードがnull。");

    /**
     * IDチェック。<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_02776 = errorMgr.defineErrorInfo(
            2776,
            "BUSINESS_ERROR_02776", 
            "IDがnull。");

    /**
     * 日付エラー。<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_02777 = errorMgr.defineErrorInfo(
            2777,
            "BUSINESS_ERROR_02777", 
            "日付エラー。");

    /**
     * 連絡種別チェック。<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_02778 = errorMgr.defineErrorInfo(
            2778,
            "BUSINESS_ERROR_02778", 
            "連絡種別が半角英数字以外の値です。");

    /**
     * リクエストが未指定(null)です。<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_02779 = errorMgr.defineErrorInfo(
            2779,
            "BUSINESS_ERROR_02779", 
            "リクエストが未指定(null)です。");

    /**
     * 注文状態の初期登録誤り。<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_02780 = errorMgr.defineErrorInfo(
            2780,
            "BUSINESS_ERROR_02780", 
            "注文状態の初期登録内容に誤りがあります。");

    /**
     * 審査サービスIDの初期登録誤り。<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_02781 = errorMgr.defineErrorInfo(
            2781,
            "BUSINESS_ERROR_02781", 
            "審査サービスIDの初期登録内容に誤りがあります。");

    /**
     * 扱者コードエラー。<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_02782 = errorMgr.defineErrorInfo(
            2782,
            "BUSINESS_ERROR_02782", 
            "扱者コードエラー。");

    /**
     * 顧客ダブリエラー。<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_02783 = errorMgr.defineErrorInfo(
            2783,
            "BUSINESS_ERROR_02783", 
            "顧客ダブリエラー。");

    /**
     * 削除該当レコードなし。<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_02784 = errorMgr.defineErrorInfo(
            2784,
            "BUSINESS_ERROR_02784", 
            "削除該当レコードなし。");

    /**
     * 銘柄コードエラー。<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_02785 = errorMgr.defineErrorInfo(
            2785,
            "BUSINESS_ERROR_02785", 
            "銘柄コードエラー。");

    /**
     * 既に伝票が作成済みのため、オンライン中は伝票作成が行えません。<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_02786 = errorMgr.defineErrorInfo(
            2786,
            "BUSINESS_ERROR_02786", 
            "既に伝票が作成済みのため、オンライン中は伝票作成が行えません。");

    /**
     * 伝票が処理済みのため伝票作成が行えません。<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_02787 = errorMgr.defineErrorInfo(
            2787,
            "BUSINESS_ERROR_02787", 
            "伝票が処理済みのため伝票作成が行えません。");

    /**
     * 金融機@関コード未指定エラー。<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_02788 = errorMgr.defineErrorInfo(
            2788,
            "BUSINESS_ERROR_02788", 
            "金融機@関コード未指定エラー。");

    /**
     * 支店コード未指定エラー。<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_02789 = errorMgr.defineErrorInfo(
            2789,
            "BUSINESS_ERROR_02789", 
            "支店コード未指定エラー。");

    /**
     * 商品区分エラー。<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_02790 = errorMgr.defineErrorInfo(
            2790,
            "BUSINESS_ERROR_02790", 
            "商品区分エラー。");

    /**
     * 振出範囲エラー。<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_02791 = errorMgr.defineErrorInfo(
            2791,
            "BUSINESS_ERROR_02791", 
            "振出範囲エラー。");

    /**
     * 商品のチェック。<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_02792 = errorMgr.defineErrorInfo(
            2792,
            "BUSINESS_ERROR_02792", 
            "商品が半角英数字以外の値です。");

    /**
     * 商品のチェック。<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_02793 = errorMgr.defineErrorInfo(
            2793,
            "BUSINESS_ERROR_02793", 
            "商品のサイズが不正です。");

    /**
     * 指定区分のチェック。<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_02794 = errorMgr.defineErrorInfo(
            2794,
            "BUSINESS_ERROR_02794", 
            "指定区分が半角英数字以外の値です。");

    /**
     * 指定区分のチェック。<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_02795 = errorMgr.defineErrorInfo(
            2795,
            "BUSINESS_ERROR_02795", 
            "指定区分のサイズが不正です。");

    /**
     * 投信銘柄を取得できません。<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_02796 = errorMgr.defineErrorInfo(
            2796,
            "BUSINESS_ERROR_02796", 
            "投信銘柄を取得できません。");

    /**
     * 債券銘柄を取得できません。<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_02797 = errorMgr.defineErrorInfo(
            2797,
            "BUSINESS_ERROR_02797", 
            "債券銘柄を取得できません。");

    /**
     * オンライン中または伝票未作成または伝票送信済みの場合、取消不可。<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_02798 = errorMgr.defineErrorInfo(
            2798,
            "BUSINESS_ERROR_02798", 
            "オンライン中または伝票未作成または伝票送信済みの場合、取消不可。");

    /**
     * 無料申込不可エラー。<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_02799 = errorMgr.defineErrorInfo(
            2799,
            "BUSINESS_ERROR_02799", 
            "当該サービスへの無料申込を行うことができません。");

    /**
     * 有料申込不可エラー。<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_02800 = errorMgr.defineErrorInfo(
            2800,
            "BUSINESS_ERROR_02800", 
            "只今の期間当該サービスへの無料申込が可能です。");

    /**
     * 申込不可エラー。<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_02801 = errorMgr.defineErrorInfo(
            2801,
            "BUSINESS_ERROR_02801", 
            "当該サービスへのお申込はできません。");

    /**
     * 無料対象期間のチェック。<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_02802 = errorMgr.defineErrorInfo(
            2802,
            "BUSINESS_ERROR_02802", 
            "無料対象期間が数値以外の値です。");

    /**
     * 無料対象期間のチェック。<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_02803 = errorMgr.defineErrorInfo(
            2803,
            "BUSINESS_ERROR_02803", 
            "無料対象期間のサイズが不正です。");

    /**
     * 適用終了日過去日付エラー。<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_02804 = errorMgr.defineErrorInfo(
            2804,
            "BUSINESS_ERROR_02804", 
            "適用終了日過去日付エラー。");

    /**
     * サービス申込属性情報のチェック。<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_02805 = errorMgr.defineErrorInfo(
            2805,
            "BUSINESS_ERROR_02805", 
            "サービス申込属性情報がnull、または申込属性区分が'2'(申込不可)です。");

    /**
     * 無料対象期間のチェック。<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_02806 = errorMgr.defineErrorInfo(
            2806,
            "BUSINESS_ERROR_02806", 
            "無料対象期間が未指定です。");

    /**
     * 申込属性区分のチェック。<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_02807 = errorMgr.defineErrorInfo(
            2807,
            "BUSINESS_ERROR_02807", 
            "申込属性区分が'1'(無料対象)、または'2'(申込不可)です。");

    /**
     * 親注文が強制決済注文のためトリガー注文設定不可。<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_02808 = errorMgr.defineErrorInfo(
            2808,
            "BUSINESS_ERROR_02808", 
            "親注文が強制決済注文のためトリガー注文設定不可。");

    /**
     * 扱者のチェック。<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_02809 = errorMgr.defineErrorInfo(
            2809,
            "BUSINESS_ERROR_02809", 
            "扱者以外は手動強制決済不可。");

    /**
     * 訂正可能チェック。<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_02810 = errorMgr.defineErrorInfo(
            2810,
            "BUSINESS_ERROR_02810", 
            "代理入力者は、未承認の強制決済注文は訂正できません。");

    /**
     * 訂正可能チェック。<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_02811 = errorMgr.defineErrorInfo(
            2811,
            "BUSINESS_ERROR_02811", 
            "強制決済注文は訂正できません。");

    /**
     * 手動強制決済フラグチェック。<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_02812 = errorMgr.defineErrorInfo(
            2812,
            "BUSINESS_ERROR_02812", 
            "手動強制決済は一括決済不可。");

    /**
     * 手動強制決済フラグチェック。<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_02813 = errorMgr.defineErrorInfo(
            2813,
            "BUSINESS_ERROR_02813", 
            "手動強制決済は出来るまで注文指定不可。");

    /**
     * 手動強制決済フラグチェック。<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_02814 = errorMgr.defineErrorInfo(
            2814,
            "BUSINESS_ERROR_02814", 
            "手動強制決済は発注条件指定不可。");

    /**
     * 商品の注文Noに対応する審査状況データの存在チェック。<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_02815 = errorMgr.defineErrorInfo(
            2815,
            "BUSINESS_ERROR_02815", 
            "商品の注文Noに対応する審査状況データが存在しません。");

    /**
     * 夕場まで注文のチェック。<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_02816 = errorMgr.defineErrorInfo(
            2816,
            "BUSINESS_ERROR_02816", 
            "夕場まで注文は取り扱えません。");

    /**
     * 注文期限・執行条件のチェックエラー。<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_02817 = errorMgr.defineErrorInfo(
            2817,
            "BUSINESS_ERROR_02817", 
            "注文期限区分が“3：夕場まで注文”の場合は、執行条件に“1：無条件”を設定して下さい。");

    /**
     * 期限のチェックエラー。<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_02818 = errorMgr.defineErrorInfo(
            2818,
            "BUSINESS_ERROR_02818", 
            "注文期限区分が“3：夕場まで注文”の場合は、注文有効期限指定不可です。");

    /**
     * お客様識別番号不正エラー。<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_02819 = errorMgr.defineErrorInfo(
            2819,
            "BUSINESS_ERROR_02819", 
            "お客様識別番号不正エラー。");

    /**
     * お客様識別番号相違エラー。<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_02820 = errorMgr.defineErrorInfo(
            2820,
            "BUSINESS_ERROR_02820", 
            "お客様識別番号相違エラー。");

    /**
     * 拒否IPエラー。<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_02821 = errorMgr.defineErrorInfo(
            2821,
            "BUSINESS_ERROR_02821", 
            "拒否IPエラー。");

    /**
     * 無料対象期間のチェック。<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_02822 = errorMgr.defineErrorInfo(
            2822,
            "BUSINESS_ERROR_02822", 
            "無料対象期間が0以下の値です。");

    /**
     * 注文の訂正／取消の受付が可能かどうか判定する。<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_02824 = errorMgr.defineErrorInfo(
            2824,
            "BUSINESS_ERROR_02824", 
            "後場閉局後―証券会社毎に通知を受けて行う後場閉局後の出来終了通知終了までの間は、注文訂正／取消受付を不可とします。");

    /**
     * 伝票作成状況チェック。<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_02825 = errorMgr.defineErrorInfo(
            2825,
            "BUSINESS_ERROR_02825", 
            "伝票作成状況を入力してください。");

    /**
     * エラー理由コードチェック。<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_02826 = errorMgr.defineErrorInfo(
            2826,
            "BUSINESS_ERROR_02826", 
            "エラー理由コードを入力してください。");

    /**
     * エラー理由コードチェック。<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_02827 = errorMgr.defineErrorInfo(
            2827,
            "BUSINESS_ERROR_02827", 
            "エラー理由コードが不正です。");

    /**
     * データコードチェック。<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_02828 = errorMgr.defineErrorInfo(
            2828,
            "BUSINESS_ERROR_02828", 
            "データコードが不正です。");

    /**
     * 識別コードチェック。<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_02829 = errorMgr.defineErrorInfo(
            2829,
            "BUSINESS_ERROR_02829", 
            "識別コードが不正です。");

    /**
     * 連絡種別チェック。<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_02830 = errorMgr.defineErrorInfo(
            2830,
            "BUSINESS_ERROR_02830", 
            "連絡種別が不正です。");

    /**
     * 伝票通番チェック。<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_02831 = errorMgr.defineErrorInfo(
            2831,
            "BUSINESS_ERROR_02831", 
            "伝票通番が不正です。");

    /**
     * 暗証番号チェック。<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_02832 = errorMgr.defineErrorInfo(
            2832,
            "BUSINESS_ERROR_02832", 
            "暗証番号が不正です。");

    /**
     * 検索必須項目がありません。<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_02833 = errorMgr.defineErrorInfo(
            2833,
            "BUSINESS_ERROR_02833", 
            "検索必須項目がありません。");

    /**
     * 伝票送信日チェック。<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_02834 = errorMgr.defineErrorInfo(
            2834,
            "BUSINESS_ERROR_02834", 
            "伝票送信日が不正です。");

    /**
     * 登録区分のチェック。<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_02835 = errorMgr.defineErrorInfo(
            2835,
            "BUSINESS_ERROR_02835", 
            "登録区分が未指定です。");

    /**
     * 権利付き最終日後の失効日指定不可。<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_02836 = errorMgr.defineErrorInfo(
            2836,
            "BUSINESS_ERROR_02836", 
            "権利付き最終日後の失効日指定不可。");

    /**
     * レコードが存在しません。<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_02837 = errorMgr.defineErrorInfo(
            2837,
            "BUSINESS_ERROR_02837", 
            "レコードが存在しません。");

    /**
     * 更新対象のレコードが不正です。<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_02838 = errorMgr.defineErrorInfo(
            2838,
            "BUSINESS_ERROR_02838", 
            "更新対象のレコードが不正です。");

    /**
     * 処理済みのレコードが存在します。もう一度検索して下さい。<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_02839 = errorMgr.defineErrorInfo(
            2839,
            "BUSINESS_ERROR_02839", 
            "処理済みのレコードが存在します。もう一度検索して下さい。");

    /**
     * 取消可能チェック。<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_02840 = errorMgr.defineErrorInfo(
            2840,
            "BUSINESS_ERROR_02840", 
            "代理入力者は、未承認の強制決済注文は取消できません。");

    /**
     * 取消可能チェック。<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_02841 = errorMgr.defineErrorInfo(
            2841,
            "BUSINESS_ERROR_02841", 
            "強制決済注文は取消できません。");

    /**
     * 立会時間のチェック。<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_02842 = errorMgr.defineErrorInfo(
            2842,
            "BUSINESS_ERROR_02842", 
            "立会時間が変わりました。お手数ですが、もう一度入力し直してください。");

    /**
     * 取扱区分チェック。<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_02843 = errorMgr.defineErrorInfo(
            2843,
            "BUSINESS_ERROR_02843", 
            "取扱区分が未指定です。");

    /**
     * 取扱区分チェック。<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_02844 = errorMgr.defineErrorInfo(
            2844,
            "BUSINESS_ERROR_02844", 
            "取扱区分の値が存在しないコード値です。");

    /**
     * 応募開始日（WEB3）チェック。<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_02845 = errorMgr.defineErrorInfo(
            2845,
            "BUSINESS_ERROR_02845", 
            "応募開始日（WEB3)が未指定です。");

    /**
     * 応募終了日（WEB3)チェック。<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_02846 = errorMgr.defineErrorInfo(
            2846,
            "BUSINESS_ERROR_02846", 
            "応募終了日（WEB3)が未指定です。");

    /**
     * 応募開始日（インターネット）チェック。<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_02847 = errorMgr.defineErrorInfo(
            2847,
            "BUSINESS_ERROR_02847", 
            "応募開始日（インターネット)が未指定です。");

    /**
     * 応募終了日（インターネット)チェック。<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_02848 = errorMgr.defineErrorInfo(
            2848,
            "BUSINESS_ERROR_02848", 
            "応募終了日（インターネット)が未指定です。");

    /**
     * 銘柄名（WEB3)チェック。<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_02849 = errorMgr.defineErrorInfo(
            2849,
            "BUSINESS_ERROR_02849", 
            "銘柄名（WEB3)が未指定です。");

    /**
     * 銘柄名（WEB3)チェック。<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_02850 = errorMgr.defineErrorInfo(
            2850,
            "BUSINESS_ERROR_02850", 
            "銘柄名（WEB3)のサイズが不正です。");

    /**
     * 申込単位チェック。<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_02851 = errorMgr.defineErrorInfo(
            2851,
            "BUSINESS_ERROR_02851", 
            "申込単位が未指定です。");

    /**
     * 最低額面チェック。<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_02852 = errorMgr.defineErrorInfo(
            2852,
            "BUSINESS_ERROR_02852", 
            "最低額面が未指定です。");

    /**
     * 最高額面チェック。<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_02853 = errorMgr.defineErrorInfo(
            2853,
            "BUSINESS_ERROR_02853", 
            "最高額面が未指定です。");

    /**
     * 目論見書閲覧チェック区分チェック。<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_02854 = errorMgr.defineErrorInfo(
            2854,
            "BUSINESS_ERROR_02854", 
            "目論見書閲覧チェック区分が未指定です。");

    /**
     * 目論見書閲覧チェック区分チェック。<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_02855 = errorMgr.defineErrorInfo(
            2855,
            "BUSINESS_ERROR_02855", 
            "目論見書閲覧チェック区分が存在しないコード値です。");

    /**
     * 応募開始日（WEB3）が非営業日です。<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_02856 = errorMgr.defineErrorInfo(
            2856,
            "BUSINESS_ERROR_02856", 
            "応募開始日（WEB3）が非営業日です。");

    /**
     * 応募開始日（SONAR)と応募開始日（WEB3)の関係が不正です。<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_02857 = errorMgr.defineErrorInfo(
            2857,
            "BUSINESS_ERROR_02857", 
            "応募開始日（SONAR)と応募開始日（WEB3)の関係が不正です。");

    /**
     * 応募終了日（WEB3)が非営業日です。<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_02858 = errorMgr.defineErrorInfo(
            2858,
            "BUSINESS_ERROR_02858", 
            "応募終了日（WEB3)が非営業日です。");

    /**
     * 応募終了日（SONAR)と応募終了日（WEB3)の関係が不正です。<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_02859 = errorMgr.defineErrorInfo(
            2859,
            "BUSINESS_ERROR_02859", 
            "応募終了日（SONAR)と応募終了日（WEB3)の関係が不正です。");

    /**
     * 応募開始日（WEB3)と応募終了日（WEB3）の関係が不正です。<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_02860 = errorMgr.defineErrorInfo(
            2860,
            "BUSINESS_ERROR_02860", 
            "応募開始日（WEB3)と応募終了日（WEB3）の関係が不正です。");

    /**
     * 応募開始日（インターネット）が非営業日です。<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_02861 = errorMgr.defineErrorInfo(
            2861,
            "BUSINESS_ERROR_02861", 
            "応募開始日（インターネット）が非営業日です。");

    /**
     * 応募開始日（WEB3)と応募開始日（インターネット）の関係が不正です。<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_02862 = errorMgr.defineErrorInfo(
            2862,
            "BUSINESS_ERROR_02862", 
            "応募開始日（WEB3)と応募開始日（インターネット）の関係が不正です。");

    /**
     * 応募終了日（インターネット）が非営業日です。<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_02863 = errorMgr.defineErrorInfo(
            2863,
            "BUSINESS_ERROR_02863", 
            "応募終了日（インターネット）が非営業日です。");

    /**
     * 応募終了日（WEB3)と応募終了日（インターネット）の関係が不正です。<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_02864 = errorMgr.defineErrorInfo(
            2864,
            "BUSINESS_ERROR_02864", 
            "応募終了日（WEB3)と応募終了日（インターネット）の関係が不正です。");

    /**
     * 受渡日が不正です。<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_02865 = errorMgr.defineErrorInfo(
            2865,
            "BUSINESS_ERROR_02865", 
            "受渡日が不正です。");

    /**
     * 申込単位が不正です。<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_02866 = errorMgr.defineErrorInfo(
            2866,
            "BUSINESS_ERROR_02866", 
            "申込単位が不正です。");

    /**
     * 最低額面が不正です。<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_02867 = errorMgr.defineErrorInfo(
            2867,
            "BUSINESS_ERROR_02867", 
            "最低額面が不正です。");

    /**
     * 応募開始日（インターネット)と応募終了日（インターネット）の関係が不正です。<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_02868 = errorMgr.defineErrorInfo(
            2868,
            "BUSINESS_ERROR_02868", 
            "応募開始日（インターネット)と応募終了日（インターネット）の関係が不正です。");

    /**
     * 債券タイプチェック。<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_02869 = errorMgr.defineErrorInfo(
            2869,
            "BUSINESS_ERROR_02869", 
            "債券タイプの値が存在しないコード値です。");

    /**
     * 建代金の上限値超過(市場銘柄単位)。<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_02870 = errorMgr.defineErrorInfo(
            2870,
            "BUSINESS_ERROR_02870", 
            "建代金の上限値超過(市場銘柄単位)エラー。");

    /**
     * 建株数の上限値超過(市場銘柄単位)。<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_02871 = errorMgr.defineErrorInfo(
            2871,
            "BUSINESS_ERROR_02871", 
            "建株数の上限値超過(市場銘柄単位)エラー。");

    /**
     * ビジネスエラー『取報・取残変更項目無しエラー』。<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_02872 = errorMgr.defineErrorInfo(
            2872,
            "BUSINESS_ERROR_02872", 
            "ビジネスエラー『取報・取残変更項目無しエラー』。");

    /**
     * ビジネスエラー『税区分変更項目無しエラー』。<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_02873 = errorMgr.defineErrorInfo(
            2873,
            "BUSINESS_ERROR_02873", 
            "ビジネスエラー『税区分変更項目無しエラー』。");

    /**
     * ビジネスエラー『信用取引税区分変更項目無しエラー』。<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_02874 = errorMgr.defineErrorInfo(
            2874,
            "BUSINESS_ERROR_02874", 
            "ビジネスエラー『信用取引税区分変更項目無しエラー』。");

    /**
     * ビジネスエラー『特定口座エラー』。<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_02875 = errorMgr.defineErrorInfo(
            2875,
            "BUSINESS_ERROR_02875", 
            "ビジネスエラー『特定口座エラー』。");

    /**
     * ビジネスエラー『特定管理口座エラー』。<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_02876 = errorMgr.defineErrorInfo(
            2876,
            "BUSINESS_ERROR_02876", 
            "ビジネスエラー『特定管理口座エラー』。");

    /**
     * ビジネスエラー『電子交付同意エラー』。<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_02877 = errorMgr.defineErrorInfo(
            2877,
            "BUSINESS_ERROR_02877", 
            "ビジネスエラー『電子交付同意エラー』。");

    /**
     * その他商品買付可能額（0補正無し）が0より小さい値です。<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_02878 = errorMgr.defineErrorInfo(
            2878,
            "BUSINESS_ERROR_02878", 
            "その他商品買付可能額（0補正無し）が0より小さい値です。");

    /**
     * その他商品買付可能額（0補正無し）が購入申込代金より小さいです。<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_02879 = errorMgr.defineErrorInfo(
            2879,
            "BUSINESS_ERROR_02879", 
            "その他商品買付可能額（0補正無し）が購入申込代金より小さいです。");

    /**
     * 申込金額チェック。<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_02880 = errorMgr.defineErrorInfo(
            2880,
            "BUSINESS_ERROR_02880", 
            "申込金額が未指定です。");

    /**
     * 申込金額チェック。<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_02881 = errorMgr.defineErrorInfo(
            2881,
            "BUSINESS_ERROR_02881", 
            "申込金額が整数以外の値です。");

    /**
     * 申込金額チェック。<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_02882 = errorMgr.defineErrorInfo(
            2882,
            "BUSINESS_ERROR_02882", 
            "申込金額が0以下の値です。");

    /**
     * 申込金額チェック。<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_02883 = errorMgr.defineErrorInfo(
            2883,
            "BUSINESS_ERROR_02883", 
            "申込金額のサイズが不正です。");

    /**
     * 法@人顧客エラー。<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_02884 = errorMgr.defineErrorInfo(
            2884,
            "BUSINESS_ERROR_02884", 
            "法@人顧客エラー。");

    /**
     * 注文金額合計未満を入力することができません。<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_02885 = errorMgr.defineErrorInfo(
            2885,
            "BUSINESS_ERROR_02885", 
            "注文金額合計未満を入力することができません。");

    /**
     * 部店別の応募枠合計が全部店の応募枠を超えています。<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_02886 = errorMgr.defineErrorInfo(
            2886,
            "BUSINESS_ERROR_02886", 
            "部店別の応募枠合計が全部店の応募枠を超えています。");

    /**
     * 信用口座未開設。<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_02887 = errorMgr.defineErrorInfo(
            2887,
            "BUSINESS_ERROR_02887", 
            "信用口座開設なし。");

    /**
     * すでに応募枠に達しています。<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_02888 = errorMgr.defineErrorInfo(
            2888,
            "BUSINESS_ERROR_02888", 
            "すでに応募枠に達しています。");

    /**
     * 応募枠を超過しています。<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_02889 = errorMgr.defineErrorInfo(
            2889,
            "BUSINESS_ERROR_02889", 
            "応募枠を超過しています。");

    /**
     * 約定データ重複。<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_02891 = errorMgr.defineErrorInfo(
            2891,
            "BUSINESS_ERROR_02891", 
            "約定データ重複。");

    /**
     * 基本メールアドレスが未指定です。<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_02892 = errorMgr.defineErrorInfo(
            2892,
            "BUSINESS_ERROR_02892", 
            "基本メールアドレスが未指定です。");

    /**
     * 約定通知送信フラグが存在しないコード値です。<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_02893 = errorMgr.defineErrorInfo(
            2893,
            "BUSINESS_ERROR_02893", 
            "約定通知送信フラグが存在しないコード値です。");

    /**
     * メールアドレス２が未指定です。<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_02894 = errorMgr.defineErrorInfo(
            2894,
            "BUSINESS_ERROR_02894", 
            "メールアドレス２が未指定です。");

    /**
     * メールアドレス３が未指定です。<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_02895 = errorMgr.defineErrorInfo(
            2895,
            "BUSINESS_ERROR_02895", 
            "メールアドレス３が未指定です。");

    /**
     * 未約定通知送信フラグが存在しないコード値です。<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_02896 = errorMgr.defineErrorInfo(
            2896,
            "BUSINESS_ERROR_02896", 
            "未約定通知送信フラグが存在しないコード値です。");

    /**
     * 重要連絡メール送信フラグが存在しないコード値です。<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_02897 = errorMgr.defineErrorInfo(
            2897,
            "BUSINESS_ERROR_02897", 
            "重要連絡メール送信フラグが存在しないコード値です。");

    /**
     * 案内メール２送信フラグが存在しないコード値です。<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_02898 = errorMgr.defineErrorInfo(
            2898,
            "BUSINESS_ERROR_02898", 
            "案内メール２送信フラグが存在しないコード値です。");

    /**
     * メールアドレス２削除の場合は、メールアドレス２が指定不可です。<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_02899 = errorMgr.defineErrorInfo(
            2899,
            "BUSINESS_ERROR_02899", 
            "メールアドレス２削除の場合は、メールアドレス２が指定不可です。");

    /**
     * メールアドレス３削除の場合は、メールアドレス３が指定不可です。<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_02900 = errorMgr.defineErrorInfo(
            2900,
            "BUSINESS_ERROR_02900", 
            "メールアドレス３削除の場合は、メールアドレス３が指定不可です。");

    /**
     * 応募枠変更可能期間外です。<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_02901 = errorMgr.defineErrorInfo(
            2901,
            "BUSINESS_ERROR_02901", 
            "応募枠変更可能期間外です。");

    /**
     * ストックローン口座番号のサイズが不正です。<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_02902 = errorMgr.defineErrorInfo(
            2902,
            "BUSINESS_ERROR_02902", 
            "ストックローン口座番号のサイズが不正です。");

    /**
     * ストックローン口座番号が半角数字以外の値です。<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_02903 = errorMgr.defineErrorInfo(
            2903,
            "BUSINESS_ERROR_02903", 
            "ストックローン口座番号が半角数字以外の値です。");

    /**
     * 申込状況の値が存在しないコード値です。<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_02904 = errorMgr.defineErrorInfo(
            2904,
            "BUSINESS_ERROR_02904", 
            "申込状況の値が存在しないコード値です。");

    /**
     * 成約日Fromは成約日Toより大きいです。<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_02905 = errorMgr.defineErrorInfo(
            2905,
            "BUSINESS_ERROR_02905", 
            "成約日Fromは成約日Toより大きいです。");

    /**
     * 解約日Fromは解約日Toより大きいです。<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_02906 = errorMgr.defineErrorInfo(
            2906,
            "BUSINESS_ERROR_02906", 
            "解約日Fromは解約日Toより大きいです。");

    /**
     * 閉鎖日Fromは閉鎖日Toより大きいです。<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_02907 = errorMgr.defineErrorInfo(
            2907,
            "BUSINESS_ERROR_02907", 
            "閉鎖日Fromは閉鎖日Toより大きいです。");

    /**
     * 担保ローン申込顧客明細一覧が未指定です。<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_02908 = errorMgr.defineErrorInfo(
            2908,
            "BUSINESS_ERROR_02908", 
            "担保ローン申込顧客明細一覧が未指定です。");

    /**
     * 返済額が数字以外の値です。<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_02909 = errorMgr.defineErrorInfo(
            2909,
            "BUSINESS_ERROR_02909", 
            "返済額が数字以外の値です。");

    /**
     * 返済額が未指定です。<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_02910 = errorMgr.defineErrorInfo(
            2910,
            "BUSINESS_ERROR_02910", 
            "返済額が未指定です。");

    /**
     * 返済額の値が0以下です。<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_02911 = errorMgr.defineErrorInfo(
            2911,
            "BUSINESS_ERROR_02911", 
            "返済額の値が0以下です。");

    /**
     * 返済額のサイズが不正です。<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_02912 = errorMgr.defineErrorInfo(
            2912,
            "BUSINESS_ERROR_02912", 
            "返済額のサイズが不正です。");

    /**
     * 返済予定日が未指定です。<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_02913 = errorMgr.defineErrorInfo(
            2913,
            "BUSINESS_ERROR_02913", 
            "返済予定日が未指定です。");

    /**
     * 証券担保ローン口座が未開設です。<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_02914 = errorMgr.defineErrorInfo(
            2914,
            "BUSINESS_ERROR_02914", 
            "証券担保ローン口座が未開設です。");

    /**
     * 直近振込日は返済予定日より大きいです。<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_02915 = errorMgr.defineErrorInfo(
            2915,
            "BUSINESS_ERROR_02915", 
            "直近振込日は返済予定日より大きいです。");

    /**
     * 銘柄タイプが半角数字以外の値です。<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_02916 = errorMgr.defineErrorInfo(
            2916,
            "BUSINESS_ERROR_02916", 
            "銘柄タイプが半角数字以外の値です。");

    /**
     * 担保銘柄検索キーが未指定です。<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_02917 = errorMgr.defineErrorInfo(
            2917,
            "BUSINESS_ERROR_02917", 
            "担保銘柄検索キーが未指定です。");

    /**
     * 出金停止区分のサイズが不正です。<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_02918 = errorMgr.defineErrorInfo(
            2918,
            "BUSINESS_ERROR_02918", 
            "出金停止区分のサイズが不正です。");

    /**
     * 出金停止区分が半角数字以外の値です。<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_02919 = errorMgr.defineErrorInfo(
            2919,
            "BUSINESS_ERROR_02919", 
            "出金停止区分が半角数字以外の値です。");

    /**
     * 口座IDが未指定です。<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_02920 = errorMgr.defineErrorInfo(
            2920,
            "BUSINESS_ERROR_02920", 
            "口座IDが未指定です。");

    /**
     * 口座IDのサイズが不正です。<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_02921 = errorMgr.defineErrorInfo(
            2921,
            "BUSINESS_ERROR_02921", 
            "口座IDのサイズが不正です。");

    /**
     * 口座IDが半角数字以外の値です。<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_02922 = errorMgr.defineErrorInfo(
            2922,
            "BUSINESS_ERROR_02922", 
            "口座IDが半角数字以外の値です。");

    /**
     * 変更後銘柄登録情報が未指定です。<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_02923 = errorMgr.defineErrorInfo(
            2923,
            "BUSINESS_ERROR_02923", 
            "変更後銘柄登録情報が未指定です。");

    /**
     * 掛目が半角数字以外の値です。<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_02924 = errorMgr.defineErrorInfo(
            2924,
            "BUSINESS_ERROR_02924", 
            "掛目が半角数字以外の値です。");

    /**
     * 適格区分が半角数字以外の値です。<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_02925 = errorMgr.defineErrorInfo(
            2925,
            "BUSINESS_ERROR_02925", 
            "適格区分が半角数字以外の値です。");

    /**
     * 理由のサイズが不正です。<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_02926 = errorMgr.defineErrorInfo(
            2926,
            "BUSINESS_ERROR_02926", 
            "理由のサイズが不正です。");

    /**
     * 同一銘柄、同一期間の担保銘柄登録が存在します。<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_02927 = errorMgr.defineErrorInfo(
            2927,
            "BUSINESS_ERROR_02927", 
            "同一銘柄、同一期間の担保銘柄登録が存在します。");

    /**
     * 株式銘柄オブジェクトが取得できません。<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_02928 = errorMgr.defineErrorInfo(
            2928,
            "BUSINESS_ERROR_02928", 
            "株式銘柄オブジェクトが取得できません。");

    /**
     * 適用期間fromは翌営業日より小さいです。<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_02929 = errorMgr.defineErrorInfo(
            2929,
            "BUSINESS_ERROR_02929", 
            "適用期間fromは翌営業日より小さいです。");

    /**
     * 適格区分が未指定です。<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_02930 = errorMgr.defineErrorInfo(
            2930,
            "BUSINESS_ERROR_02930", 
            "適格区分が未指定です。");

    /**
     * 適格区分のサイズが不正です。<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_02931 = errorMgr.defineErrorInfo(
            2931,
            "BUSINESS_ERROR_02931", 
            "適格区分のサイズが不正です。");

    /**
     * 適用期間区分が半角数字以外の値です。<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_02932 = errorMgr.defineErrorInfo(
            2932,
            "BUSINESS_ERROR_02932", 
            "適用期間区分が半角数字以外の値です。");

    /**
     * 適用期間区分のサイズが不正です。<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_02933 = errorMgr.defineErrorInfo(
            2933,
            "BUSINESS_ERROR_02933", 
            "適用期間区分のサイズが不正です。");

    /**
     * （法@人）口座開設不可エラー。<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_02934 = errorMgr.defineErrorInfo(
            2934,
            "BUSINESS_ERROR_02934", 
            "（法@人）口座開設不可エラー。");

    /**
     * （預り証券評価制）口座開設不可エラー。<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_02935 = errorMgr.defineErrorInfo(
            2935,
            "BUSINESS_ERROR_02935", 
            "（預り証券評価制）口座開設不可エラー。");

    /**
     * （信用）口座開設不可エラー。<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_02936 = errorMgr.defineErrorInfo(
            2936,
            "BUSINESS_ERROR_02936", 
            "（信用）口座開設不可エラー。");

    /**
     * （先OP）口座開設不可エラー。<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_02937 = errorMgr.defineErrorInfo(
            2937,
            "BUSINESS_ERROR_02937", 
            "（先OP）口座開設不可エラー。");

    /**
     * （信用、先OP）口座開設不可エラー。<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_02938 = errorMgr.defineErrorInfo(
            2938,
            "BUSINESS_ERROR_02938", 
            "（信用、先OP）口座開設不可エラー。");

    /**
     * 書面未交付エラー（ログイン時）。<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_02939 = errorMgr.defineErrorInfo(
            2939,
            "BUSINESS_ERROR_02939", 
            "書面の交付がされてないため、ログインできません。");

    /**
     * 書面未交付エラー。<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_02940 = errorMgr.defineErrorInfo(
            2940,
            "BUSINESS_ERROR_02940", 
            "書面の交付がされていません。");

    /**
     * 書面区分が半角数字以外の値です。<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_02941 = errorMgr.defineErrorInfo(
            2941,
            "BUSINESS_ERROR_02941", 
            "書面区分が半角数字以外の値です。");

    /**
     * 書面区分のサイズが不正です。<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_02942 = errorMgr.defineErrorInfo(
            2942,
            "BUSINESS_ERROR_02942", 
            "書面区分のサイズが不正です。");

    /**
     * 書面交付日が未指定です。<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_02943 = errorMgr.defineErrorInfo(
            2943,
            "BUSINESS_ERROR_02943", 
            "書面交付日が未指定です。");

    /**
     * 書面チェック区分が未指定です。<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_02944 = errorMgr.defineErrorInfo(
            2944,
            "BUSINESS_ERROR_02944", 
            "書面チェック区分が未指定です。");

    /**
     * 書面チェック区分が半角数字以外の値です。<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_02945 = errorMgr.defineErrorInfo(
            2945,
            "BUSINESS_ERROR_02945", 
            "書面チェック区分が半角数字以外の値です。");

    /**
     * 書面チェック区分のサイズが不正です。<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_02946 = errorMgr.defineErrorInfo(
            2946,
            "BUSINESS_ERROR_02946", 
            "書面チェック区分のサイズが不正です。");

    /**
     * 書面交付日From/To整合性エラー。<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_02947 = errorMgr.defineErrorInfo(
            2947,
            "BUSINESS_ERROR_02947", 
            "書面交付日From/To整合性エラー。");

    /**
     * 書面区分が未指定です。<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_02948 = errorMgr.defineErrorInfo(
            2948,
            "BUSINESS_ERROR_02948", 
            "書面区分が未指定です。");

    /**
     * 募集手数料区分が存在しないコード値です。<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_02949 = errorMgr.defineErrorInfo(
            2949,
            "BUSINESS_ERROR_02949", 
            "募集手数料区分が存在しないコード値です。");

    /**
     * 指定の顧客は既に登録されています。<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_02950 = errorMgr.defineErrorInfo(
            2950,
            "BUSINESS_ERROR_02950", 
            "指定の顧客は既に登録されています。");

    /**
     * 約定状態が数字以外の値です。<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_02951 = errorMgr.defineErrorInfo(
            2951,
            "BUSINESS_ERROR_02951", 
            "約定状態が数字以外の値です。");

    /**
     * 書面交付管理行が取得できない。<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_02952 = errorMgr.defineErrorInfo(
            2952,
            "BUSINESS_ERROR_02952", 
            "書面交付管理行が取得できない。");

    /**
     * 先物／オプション区分が不正です。<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_02953 = errorMgr.defineErrorInfo(
            2953,
            "BUSINESS_ERROR_02953", 
            "先物／オプション区分が不正です。");

    /**
     * 出来終了区分が不正です。<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_02954 = errorMgr.defineErrorInfo(
            2954,
            "BUSINESS_ERROR_02954", 
            "出来終了区分が不正です。");

    /**
     * 年齢下限値エラー。<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_02955 = errorMgr.defineErrorInfo(
            2955,
            "BUSINESS_ERROR_02955", 
            "年齢が年齢下限値に達していません。");

    /**
     * 年齢上限値エラー。<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_02956 = errorMgr.defineErrorInfo(
            2956,
            "BUSINESS_ERROR_02956", 
            "年齢が年齢上限値を超えています。");

    /**
     * 支店ロックエラー。<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_02957 = errorMgr.defineErrorInfo(
            2957,
            "BUSINESS_ERROR_02957", 
            "支店ロックエラー。");

    /**
     * 管理ロックエラー。<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_02958 = errorMgr.defineErrorInfo(
            2958,
            "BUSINESS_ERROR_02958", 
            "管理ロックエラー。");

    /**
     * 重複する顧客コード又は識別コードの申込みが存在します。<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_02959 = errorMgr.defineErrorInfo(
            2959,
            "BUSINESS_ERROR_02959", 
            "重複する顧客コード又は識別コードの申込みが存在します。");

    /**
     * 指定の顧客コード又は識別コードは既に口座開設見込客に登録されています。<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_02960 = errorMgr.defineErrorInfo(
            2960,
            "BUSINESS_ERROR_02960", 
            "指定の顧客コード又は識別コードは既に口座開設見込客に登録されています。");

    /**
     * 書面交付日が不正です。<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_02961 = errorMgr.defineErrorInfo(
            2961,
            "BUSINESS_ERROR_02961", 
            "書面交付日が不正です。");

    /**
     * 既に登録済みであった場合、登録アップロード処理不可。<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_02962 = errorMgr.defineErrorInfo(
            2962,
            "BUSINESS_ERROR_02962", 
            "既に登録済みであった場合、登録アップロード処理不可。");

    /**
     * 削除行が存在しない場合、削除アップロード処理不可。<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_02963 = errorMgr.defineErrorInfo(
            2963,
            "BUSINESS_ERROR_02963", 
            "削除行が存在しない場合、削除アップロード処理不可。");

    /**
     * 機@構預託に同意されていないため、取引できません。<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_02964 = errorMgr.defineErrorInfo(
            2964,
            "BUSINESS_ERROR_02964", 
            "機@構預託に同意されていないため、取引できません。");

    /**
     * 注文状態のチェック。<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_02965 = errorMgr.defineErrorInfo(
            2965,
            "BUSINESS_ERROR_02965", 
            "注文単位の発注日が現在の発注日より小さい値です。");

    /**
     * PTS口座開設なし。<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_02967 = errorMgr.defineErrorInfo(
            2967,
            "BUSINESS_ERROR_02967", 
            "PTS口座開設なし。");

    /**
     * 指定市場の取引可能上限金額を超えています。<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_02972 = errorMgr.defineErrorInfo(
            2972,
            "BUSINESS_ERROR_02972", 
            "指定市場の取引可能上限金額を超えています。");

    /**
     * PTS市場では、成行指定不可。<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_02974 = errorMgr.defineErrorInfo(
            2974,
            "BUSINESS_ERROR_02974", 
            "PTS市場では、成行指定不可。");

    /**
     * 非居住エラー。<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_02979 = errorMgr.defineErrorInfo(
            2979,
            "BUSINESS_ERROR_02979", 
            "非居住エラー。");

    /**
     * 必須項目未入力。<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_02980 = errorMgr.defineErrorInfo(
            2980,
            "BUSINESS_ERROR_02980", 
            "必須項目未入力。");

    /**
     * 計算用入力簿価金額が不正な値。<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_02981 = errorMgr.defineErrorInfo(
            2981,
            "BUSINESS_ERROR_02981", 
            "計算用入力簿価金額が不正な値。");

    /**
     * データ不整合エラー（残高株数データ不整合）。<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_02982 = errorMgr.defineErrorInfo(
            2982,
            "BUSINESS_ERROR_02982", 
            "データ不整合エラー（残高株数データ不整合）。");

    /**
     * 注文状態が出来入力不可。<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_02983 = errorMgr.defineErrorInfo(
            2983,
            "BUSINESS_ERROR_02983", 
            "注文状態が出来入力不可。");

    /**
     * 入力した約定日時が、受注日時より過去日時。<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_02984 = errorMgr.defineErrorInfo(
            2984,
            "BUSINESS_ERROR_02984", 
            "入力した約定日時が、受注日時より過去日時。");

    /**
     * 条件に該当する強制決済注文がありません。<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_02985 = errorMgr.defineErrorInfo(
            2985,
            "BUSINESS_ERROR_02985", 
            "条件に該当する強制決済注文がありません。");

    /**
     * 注文状態が出来取消不可。<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_02986 = errorMgr.defineErrorInfo(
            2986,
            "BUSINESS_ERROR_02986", 
            "注文状態が出来取消不可。");

    /**
     * 入力した約定株数が未約定株数を超えている。<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_02987 = errorMgr.defineErrorInfo(
            2987,
            "BUSINESS_ERROR_02987", 
            "入力した約定株数が未約定株数を超えている。");

    /**
     * 注文がPTS市場でないため、出来入力不可。<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_02988 = errorMgr.defineErrorInfo(
            2988,
            "BUSINESS_ERROR_02988", 
            "注文がPTS市場でないため、出来入力不可。");

    /**
     * 約定株数が未指定です。<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_02989 = errorMgr.defineErrorInfo(
            2989,
            "BUSINESS_ERROR_02989", 
            "約定株数が未指定です。");

    /**
     * 約定株数が数値以外の値です。<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_02990 = errorMgr.defineErrorInfo(
            2990,
            "BUSINESS_ERROR_02990", 
            "約定株数が数値以外の値です。");

    /**
     * 約定株数が0以下の値です。<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_02991 = errorMgr.defineErrorInfo(
            2991,
            "BUSINESS_ERROR_02991", 
            "約定株数が0以下の値です。");

    /**
     * 約定株数のサイズが不正です。<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_02992 = errorMgr.defineErrorInfo(
            2992,
            "BUSINESS_ERROR_02992", 
            "約定株数のサイズが不正です。");

    /**
     * 約定単価のサイズが不正です。<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_02993 = errorMgr.defineErrorInfo(
            2993,
            "BUSINESS_ERROR_02993", 
            "約定単価のサイズが不正です。");

    /**
     * データ変換エラー。<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_02994 = errorMgr.defineErrorInfo(
            2994,
            "BUSINESS_ERROR_02994", 
            "日付が不正です。");

    /**
     * 電子鳩銘柄コードチェックエラー。<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_02995 = errorMgr.defineErrorInfo(
            2995,
            "BUSINESS_ERROR_02995", 
            "電子鳩銘柄コードが不正です。");

    /**
     * 電子鳩銘柄コードチェックエラー。<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_02996 = errorMgr.defineErrorInfo(
            2996,
            "BUSINESS_ERROR_02996", 
            "指定した電子鳩銘柄コードが正しくありません （上3桁が不正）。");

    /**
     * 書面種類コードチェックエラー。<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_02997 = errorMgr.defineErrorInfo(
            2997,
            "BUSINESS_ERROR_02997", 
            "書面種類コードが不正です。");

    /**
     * 書面区分管理レコードチェック。<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_02998 = errorMgr.defineErrorInfo(
            2998,
            "BUSINESS_ERROR_02998", 
            "書面区分管理テーブルにレコードが存在しません。");

    /**
     * 電子鳩銘柄コード管理レコードチェックエラー。<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_02999 = errorMgr.defineErrorInfo(
            2999,
            "BUSINESS_ERROR_02999", 
            "電子鳩銘柄コード管理テーブルにレコードが存在しません。");

    /**
     * 書面種類管理レコードチェックエラー。<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_03000 = errorMgr.defineErrorInfo(
            3000,
            "BUSINESS_ERROR_03000", 
            "書面種類管理テーブルにレコードが存在しません。");

    /**
     * データ変換エラー。<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_03002 = errorMgr.defineErrorInfo(
            3002,
            "BUSINESS_ERROR_03002", 
            "数値が不正です。");

    /**
     * PTS市場でない場合は出来取消不可。<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_03003 = errorMgr.defineErrorInfo(
            3003,
            "BUSINESS_ERROR_03003", 
            "PTS市場でない場合は出来取消不可。");

    /**
     * 電子鳩銘柄コードが無効です。<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_03004 = errorMgr.defineErrorInfo(
            3004,
            "BUSINESS_ERROR_03004", 
            "電子鳩銘柄コードが無効です。");

    /**
     * 有効な電子鳩銘柄コードが複数存在します。<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_03005 = errorMgr.defineErrorInfo(
            3005,
            "BUSINESS_ERROR_03005", 
            "有効な電子鳩銘柄コードが複数存在します。");

    /**
     * 書面種類一覧が未指定です。<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_03006 = errorMgr.defineErrorInfo(
            3006,
            "BUSINESS_ERROR_03006", 
            "書面種類一覧が未指定です。");

    /**
     * 書面区分管理情報が未指定です。<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_03007 = errorMgr.defineErrorInfo(
            3007,
            "BUSINESS_ERROR_03007", 
            "書面区分管理情報が未指定です。");

    /**
     * 電子鳩銘柄コード管理情報が未指定です。<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_03008 = errorMgr.defineErrorInfo(
            3008,
            "BUSINESS_ERROR_03008", 
            "電子鳩銘柄コード管理情報が未指定です。");

    /**
     * 電子鳩銘柄コードが未指定です。<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_03009 = errorMgr.defineErrorInfo(
            3009,
            "BUSINESS_ERROR_03009", 
            "電子鳩銘柄コードが未指定です。");

    /**
     * 電子鳩銘柄コードは半角数字以外の値です。<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_03010 = errorMgr.defineErrorInfo(
            3010,
            "BUSINESS_ERROR_03010", 
            "電子鳩銘柄コードは半角数字以外の値です。");

    /**
     * 電子鳩銘柄コードのサイズが不正です。<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_03011 = errorMgr.defineErrorInfo(
            3011,
            "BUSINESS_ERROR_03011", 
            "電子鳩銘柄コードのサイズが不正です。");

    /**
     * 有効区分が未設定です。<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_03012 = errorMgr.defineErrorInfo(
            3012,
            "BUSINESS_ERROR_03012", 
            "有効区分が未設定です。");

    /**
     * 書面種類コードが未指定です。<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_03013 = errorMgr.defineErrorInfo(
            3013,
            "BUSINESS_ERROR_03013", 
            "書面種類コードが未指定です。");

    /**
     * 有効区分が未定義の値です。<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_03014 = errorMgr.defineErrorInfo(
            3014,
            "BUSINESS_ERROR_03014", 
            "有効区分が未定義の値です。");

    /**
     * 処理不可の時間帯。<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_03015 = errorMgr.defineErrorInfo(
            3015,
            "BUSINESS_ERROR_03015", 
            "処理不可の時間帯。");

    /**
     * 商品区分が“現物株式”または”信用取引”の場合、指数種別が指定不可です。<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_03018 = errorMgr.defineErrorInfo(
            3018,
            "BUSINESS_ERROR_03018", 
            "商品区分が“現物株式”または”信用取引”の場合、指数種別が指定不可です。");

    /**
     * 外部連携情報を取得できません。<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_03019 = errorMgr.defineErrorInfo(
            3019,
            "BUSINESS_ERROR_03019", 
            "外部連携情報を取得できません。");

    /**
     * PTS口座開設区分が未指定です。<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_03020 = errorMgr.defineErrorInfo(
            3020,
            "BUSINESS_ERROR_03020", 
            "PTS口座開設区分が未指定です。");

    /**
     * 質問番号が未指定です。<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_03021 = errorMgr.defineErrorInfo(
            3021,
            "BUSINESS_ERROR_03021", 
            "質問番号が未指定です。");

    /**
     * 質問回答が未指定です。<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_03022 = errorMgr.defineErrorInfo(
            3022,
            "BUSINESS_ERROR_03022", 
            "質問回答が未指定です。");

    /**
     * 銘柄コードの要素数が０です。<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_03023 = errorMgr.defineErrorInfo(
            3023,
            "BUSINESS_ERROR_03023", 
            "銘柄コードの要素数が０です。");

    /**
     * 既に口座開設済みです。<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_03024 = errorMgr.defineErrorInfo(
            3024,
            "BUSINESS_ERROR_03024", 
            "既に口座開設済みです。");

    /**
     * 口座開設の申込を受付できません。<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_03025 = errorMgr.defineErrorInfo(
            3025,
            "BUSINESS_ERROR_03025", 
            "口座開設の申込を受付できません。");

    /**
     * 質問回答は同意ではありません。<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_03026 = errorMgr.defineErrorInfo(
            3026,
            "BUSINESS_ERROR_03026", 
            "質問回答は同意ではありません。");

    /**
     * 外部連携情報を取得するはずがない。<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_03027 = errorMgr.defineErrorInfo(
            3027,
            "BUSINESS_ERROR_03027", 
            "外部連携情報を取得するはずがない。");

    /**
     * 新規登録件数が外部連携未使用件数を超えています。<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_03029 = errorMgr.defineErrorInfo(
            3029,
            "BUSINESS_ERROR_03029", 
            "新規登録件数が外部連携未使用件数を超えています。");

    /**
     * 更新処理が不明です。<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_03030 = errorMgr.defineErrorInfo(
            3030,
            "BUSINESS_ERROR_03030", 
            "更新処理が不明です。");

    /**
     * 更新情報が不正です。<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_03031 = errorMgr.defineErrorInfo(
            3031,
            "BUSINESS_ERROR_03031", 
            "更新情報が不正です。");

    /**
     * 書面通番が未設定です。<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_03032 = errorMgr.defineErrorInfo(
            3032,
            "BUSINESS_ERROR_03032", 
            "書面通番が未設定です。");

    /**
     * 書面通番が不正です。<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_03033 = errorMgr.defineErrorInfo(
            3033,
            "BUSINESS_ERROR_03033", 
            "書面通番が不正です。");

    /**
     * 摘要は全角文字で入力してください。<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_03034 = errorMgr.defineErrorInfo(
            3034,
            "BUSINESS_ERROR_03034", 
            "摘要は全角文字で入力してください。");

    /**
     * 摘要は100文字以内で設定してください。<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_03035 = errorMgr.defineErrorInfo(
            3035,
            "BUSINESS_ERROR_03035", 
            "摘要は100文字以内で設定してください。");

    /**
     * 有効区分が不正です。<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_03036 = errorMgr.defineErrorInfo(
            3036,
            "BUSINESS_ERROR_03036", 
            "有効区分が不正です。");

    /**
     * 作成中の書面種類の書面通番は既に登録されています。<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_03037 = errorMgr.defineErrorInfo(
            3037,
            "BUSINESS_ERROR_03037", 
            "作成中の書面種類の書面通番は既に登録されています。");

    /**
     * 更新・削除対象の書面種類の書面通番は既に削除されています。<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_03038 = errorMgr.defineErrorInfo(
            3038,
            "BUSINESS_ERROR_03038", 
            "更新・削除対象の書面種類の書面通番は既に削除されています。");

    /**
     * 有効な書面種類が存在する為、削除できません。<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_03039 = errorMgr.defineErrorInfo(
            3039,
            "BUSINESS_ERROR_03039", 
            "有効な書面種類が存在する為、削除できません。");

    /**
     * 交付履歴が存在する為、削除できません。<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_03040 = errorMgr.defineErrorInfo(
            3040,
            "BUSINESS_ERROR_03040", 
            "交付履歴が存在する為、削除できません。");

    /**
     * 申込日時（自）は申込日時（至） より大きいです。<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_03041 = errorMgr.defineErrorInfo(
            3041,
            "BUSINESS_ERROR_03041", 
            "申込日時（自）は申込日時（至） より大きいです。");

    /**
     * 申込区分は半角数字以外の値です。<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_03042 = errorMgr.defineErrorInfo(
            3042,
            "BUSINESS_ERROR_03042", 
            "申込区分は半角数字以外の値です。");

    /**
     * 変更後申込区分が未指定です。<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_03045 = errorMgr.defineErrorInfo(
            3045,
            "BUSINESS_ERROR_03045", 
            "変更後申込区分が未指定です。");

    /**
     * 申込区分に変更がありません。<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_03046 = errorMgr.defineErrorInfo(
            3046,
            "BUSINESS_ERROR_03046", 
            "申込区分に変更がありません。");

    /**
     * 電子鳩銘柄コード管理レコード登録・更新・削除チェックエラー。<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_03049 = errorMgr.defineErrorInfo(
            3049,
            "BUSINESS_ERROR_03049", 
            "同じ書面種類中に、有効な書面が複数存在します。");

    /**
     * ステータスの値が不正です。<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_03050 = errorMgr.defineErrorInfo(
            3050,
            "BUSINESS_ERROR_03050", 
            "ステータスの値が不正です。");

    /**
     * 重複通番チェック。<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_03051 = errorMgr.defineErrorInfo(
            3051,
            "BUSINESS_ERROR_03051", 
            "重複通番チェックエラー（同じ値の行が存在する）。");

    /**
     * 重複IDチェック。<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_03052 = errorMgr.defineErrorInfo(
            3052,
            "BUSINESS_ERROR_03052", 
            "重複IDチェックエラー（同じ値の行が存在する）。");

    /**
     * サービス利用管理者外部連携ID一覧照会明細行を取得できません。<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_03053 = errorMgr.defineErrorInfo(
            3053,
            "BUSINESS_ERROR_03053", 
            "サービス利用管理者外部連携ID一覧照会明細行を取得できません。");

    /**
     * 通番のチェック。<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_03054 = errorMgr.defineErrorInfo(
            3054,
            "BUSINESS_ERROR_03054", 
            "通番のサイズが不正です。");

    /**
     * 通番のチェック。<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_03055 = errorMgr.defineErrorInfo(
            3055,
            "BUSINESS_ERROR_03055", 
            "通番が数値以外の値です。");

    /**
     * アップロード区分のチェック。<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_03056 = errorMgr.defineErrorInfo(
            3056,
            "BUSINESS_ERROR_03056", 
            "アップロード区分が未指定です。");

    /**
     * IDチエック。<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_03057 = errorMgr.defineErrorInfo(
            3057,
            "BUSINESS_ERROR_03057", 
            "IDが半角英数字以外の値です。");

    /**
     * パスワードが未入力です。<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_03058 = errorMgr.defineErrorInfo(
            3058,
            "BUSINESS_ERROR_03058", 
            "パスワードが未入力です。");

    /**
     * 通番が0より小さい値です。<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_03059 = errorMgr.defineErrorInfo(
            3059,
            "BUSINESS_ERROR_03059", 
            "通番が0より小さい値です。");

    /**
     * 返済建玉の注文数量指定が不正。<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_03060 = errorMgr.defineErrorInfo(
            3060,
            "BUSINESS_ERROR_03060", 
            "返済建玉の注文数量指定が不正。");

    /**
     * 確認時概算建代金の値が不正です。<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_03061 = errorMgr.defineErrorInfo(
            3061,
            "BUSINESS_ERROR_03061", 
            "確認時概算建代金の値が不正です。");

    /**
     * 確認時概算決済損益の値が不正です。<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_03063 = errorMgr.defineErrorInfo(
            3063,
            "BUSINESS_ERROR_03063", 
            "確認時概算決済損益の値が不正です。");

    /**
     * 注文数量が親注文の注文数量を超過しています。<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_03065 = errorMgr.defineErrorInfo(
            3065,
            "BUSINESS_ERROR_03065", 
            "注文数量が親注文の注文数量を超過しています。");

    /**
     * 予約決済対象建玉は別注文により決済済です。<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_03066 = errorMgr.defineErrorInfo(
            3066,
            "BUSINESS_ERROR_03066", 
            "予約決済対象建玉は別注文により決済済です。");

    /**
     * 書面未承諾 強制ログアウト対象銘柄条件のチェックエラー。<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_03067 = errorMgr.defineErrorInfo(
            3067,
            "BUSINESS_ERROR_03067", 
            "書面未承諾 強制ログアウト対象銘柄条件が未入力です。");

    /**
     * 適用開始日（自）が適用開始日（至）より未来日付です。<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_03068 = errorMgr.defineErrorInfo(
            3068,
            "BUSINESS_ERROR_03068", 
            "適用開始日（自）が適用開始日（至）より未来日付です。");

    /**
     * PTS市場でストックオプション売注文不可。<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_03069 = errorMgr.defineErrorInfo(
            3069,
            "BUSINESS_ERROR_03069", 
            "PTS市場でストックオプション売注文不可。");

    /**
     * MQ_MESSAGE_ID_MAPPINGSテーブルにデータがありません。<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_03070 = errorMgr.defineErrorInfo(
            3070,
            "BUSINESS_ERROR_03070", 
            "MQ_MESSAGE_ID_MAPPINGSテーブルにデータがありません。");

    /**
     * シェル名称チェック。<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_03071 = errorMgr.defineErrorInfo(
            3071,
            "BUSINESS_ERROR_03071", 
            "シェル名称が未指定です。");

    /**
     * トリガー名称チェック。<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_03072 = errorMgr.defineErrorInfo(
            3072,
            "BUSINESS_ERROR_03072", 
            "トリガー名称が未指定です。");

    /**
     * 再発行可能フラグチェック。<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_03073 = errorMgr.defineErrorInfo(
            3073,
            "BUSINESS_ERROR_03073", 
            "再発行可能フラグが未指定です。");

    /**
     * ユーザーデータチェック。<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_03074 = errorMgr.defineErrorInfo(
            3074,
            "BUSINESS_ERROR_03074", 
            "ユーザーデータが未指定です。");

    /**
     * 外部システムSOAPプリファ@レンス（RPC形式）のレコードが取得できません。<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_03075 = errorMgr.defineErrorInfo(
            3075,
            "BUSINESS_ERROR_03075", 
            "外部システムSOAPプリファ@レンス（RPC形式）のレコードが取得できません。");

    /**
     * リクエストデータが未指定です。<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_03076 = errorMgr.defineErrorInfo(
            3076,
            "BUSINESS_ERROR_03076", 
            "リクエストデータが未指定です。");

    /**
     * リクエストデータの要素数が０です。<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_03077 = errorMgr.defineErrorInfo(
            3077,
            "BUSINESS_ERROR_03077", 
            "リクエストデータの要素数が０です。");

    /**
     * システム障害フラグが半角数字以外の値です。<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_03078 = errorMgr.defineErrorInfo(
            3078,
            "BUSINESS_ERROR_03078", 
            "システム障害フラグが半角数字以外の値です。");

    /**
     * システム障害フラグのサイズが不正です。<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_03079 = errorMgr.defineErrorInfo(
            3079,
            "BUSINESS_ERROR_03079", 
            "システム障害フラグのサイズが不正です。");

    /**
     * システム障害フラグが未指定です。<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_03080 = errorMgr.defineErrorInfo(
            3080,
            "BUSINESS_ERROR_03080", 
            "システム障害フラグが未指定です。");

    /**
     * 削除不可。<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_03081 = errorMgr.defineErrorInfo(
            3081,
            "BUSINESS_ERROR_03081", 
            "削除不可。");

    /**
     * 建玉残高不足エラー。<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_03082 = errorMgr.defineErrorInfo(
            3082,
            "BUSINESS_ERROR_03082", 
            "建玉残高不足エラー。");

    /**
     * 種別コードの桁数が3桁を越えています。<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_03083 = errorMgr.defineErrorInfo(
            3083,
            "BUSINESS_ERROR_03083", 
            "種別コードの桁数が3桁を越えています。");

    /**
     * 種別コードが半角数字以外の値です。<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_03084 = errorMgr.defineErrorInfo(
            3084,
            "BUSINESS_ERROR_03084", 
            "種別コードが半角数字以外の値です。");

    /**
     * 注文№がnull。<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_03085 = errorMgr.defineErrorInfo(
            3085,
            "BUSINESS_ERROR_03085", 
            "注文№が未指定です。");

    /**
     * 注文№の桁数が10桁を超えています。<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_03086 = errorMgr.defineErrorInfo(
            3086,
            "BUSINESS_ERROR_03086", 
            "注文№の桁数が10桁を超えています。");

    /**
     * 注文№が半角数字以外の値です。<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_03087 = errorMgr.defineErrorInfo(
            3087,
            "BUSINESS_ERROR_03087", 
            "注文№が半角数字以外の値です。");

    /**
     * 金額がnull。<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_03088 = errorMgr.defineErrorInfo(
            3088,
            "BUSINESS_ERROR_03088", 
            "金額が未指定です。");

    /**
     * 金額の桁数が12桁を超えています。<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_03089 = errorMgr.defineErrorInfo(
            3089,
            "BUSINESS_ERROR_03089", 
            "金額の桁数が12桁を超えています。");

    /**
     * 金額が半角数字以外の値です。<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_03090 = errorMgr.defineErrorInfo(
            3090,
            "BUSINESS_ERROR_03090", 
            "金額が半角数字以外の値です。");

    /**
     * 補助口座がnull。<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_03091 = errorMgr.defineErrorInfo(
            3091,
            "BUSINESS_ERROR_03091", 
            "補助口座が未指定です。");

    /**
     * 目論見書閲覧チェック結果が閲覧未済です。<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_03092 = errorMgr.defineErrorInfo(
            3092,
            "BUSINESS_ERROR_03092", 
            "目論見書閲覧チェック結果が閲覧未済です。");

    /**
     * ログイン属性が取得できません。<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_03094 = errorMgr.defineErrorInfo(
            3094,
            "BUSINESS_ERROR_03094", 
            "ログイン属性が取得できません。");

    /**
     * 注文No重複エラー。<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_03095 = errorMgr.defineErrorInfo(
            3095,
            "BUSINESS_ERROR_03095", 
            "注文No重複エラー。");

    /**
     * 取消データなしエラー。<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_03096 = errorMgr.defineErrorInfo(
            3096,
            "BUSINESS_ERROR_03096", 
            "取消データなしエラー。");

    /**
     * 約定数量と注文数量は同じです。<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_03098 = errorMgr.defineErrorInfo(
            3098,
            "BUSINESS_ERROR_03098", 
            "約定数量と注文数量は同じです。");

    /**
     * 定時定額買付引落口座未登録エラー。<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_03099 = errorMgr.defineErrorInfo(
            3099,
            "BUSINESS_ERROR_03099", 
            "定時定額買付引落口座未登録エラー。");

    /**
     * 初回表示フラグがnull。<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_03100 = errorMgr.defineErrorInfo(
            3100,
            "BUSINESS_ERROR_03100", 
            "初回表示フラグが未指定です。");

    /**
     * 初回表示フラグの値が不正です。<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_03101 = errorMgr.defineErrorInfo(
            3101,
            "BUSINESS_ERROR_03101", 
            "初回表示フラグの値が不正です。");

    /**
     * 変更区分未指定エラー。<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_03102 = errorMgr.defineErrorInfo(
            3102,
            "BUSINESS_ERROR_03102", 
            "変更区分未指定エラー。");

    /**
     * 適用開始年月未指定エラー。<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_03103 = errorMgr.defineErrorInfo(
            3103,
            "BUSINESS_ERROR_03103", 
            "適用開始年月未指定エラー。");

    /**
     * 注文訂正失敗。<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_03104 = errorMgr.defineErrorInfo(
            3104,
            "BUSINESS_ERROR_03104", 
            "注文訂正失敗。");

    /**
     * PTYPEがnull。<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_03105 = errorMgr.defineErrorInfo(
            3105,
            "BUSINESS_ERROR_03105", 
            "PTYPEが未指定です。");

    /**
     * 応募銘柄の場合、応募勧誘形式と応募引受け区分は必須入力です。<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_03106 = errorMgr.defineErrorInfo(
            3106,
            "BUSINESS_ERROR_03106", 
            "応募銘柄の場合、応募勧誘形式と応募引受け区分は必須入力です。");

    /**
     * 応募銘柄でない場合、応募勧誘形式と応募引受け区分は入力不可です。<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_03107 = errorMgr.defineErrorInfo(
            3107,
            "BUSINESS_ERROR_03107", 
            "応募銘柄でない場合、応募勧誘形式と応募引受け区分は入力不可です。");

    /**
     * 信用口座開設なし、かつ手数料区分は信用顧客です。<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_03108 = errorMgr.defineErrorInfo(
            3108,
            "BUSINESS_ERROR_03108", 
            "信用口座開設なし、かつ手数料区分は信用顧客です。");

    /**
     * 発注日が正しい日付ではありません。<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_03109 = errorMgr.defineErrorInfo(
            3109,
            "BUSINESS_ERROR_03109", 
            "発注日が正しい日付ではありません。");

    /**
     * 未来月適用開始の申込銘柄があるため、新規銘柄の追加申込はできません。<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_03110 = errorMgr.defineErrorInfo(
            3110,
            "BUSINESS_ERROR_03110", 
            "未来月適用開始の申込銘柄があるため、新規銘柄の追加申込はできません。");

    /**
     * 利用者属性の文字数が1byteより大きいです。<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_03111 = errorMgr.defineErrorInfo(
            3111,
            "BUSINESS_ERROR_03111", 
            "利用者属性の文字数が1byteより大きいです。");

    /**
     * 決済方法@の文字数が1byteより大きいです。<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_03112 = errorMgr.defineErrorInfo(
            3112,
            "BUSINESS_ERROR_03112", 
            "決済方法@の文字数が1byteより大きいです。");

    /**
     * 電子交付承諾日の文字数が8byteより大きいです。<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_03113 = errorMgr.defineErrorInfo(
            3113,
            "BUSINESS_ERROR_03113", 
            "電子交付承諾日の文字数が8byteより大きいです。");

    /**
     * 取引説明書確認日の文字数が8byteより大きいです。<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_03114 = errorMgr.defineErrorInfo(
            3114,
            "BUSINESS_ERROR_03114", 
            "取引説明書確認日の文字数が8byteより大きいです。");

    /**
     * 約諾書番号の文字数が10byteより大きいです。<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_03115 = errorMgr.defineErrorInfo(
            3115,
            "BUSINESS_ERROR_03115", 
            "約諾書番号の文字数が10byteより大きいです。");

    /**
     * ログイン拒否IDがnull。<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_03116 = errorMgr.defineErrorInfo(
            3116,
            "BUSINESS_ERROR_03116", 
            "ログイン拒否IDが未指定です。");

    /**
     * ログイン拒否IDが半角数字以外の値です。<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_03117 = errorMgr.defineErrorInfo(
            3117,
            "BUSINESS_ERROR_03117", 
            "ログイン拒否IDが半角数字以外の値です。");

    /**
     * 時間(自)がnullです。<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_03118 = errorMgr.defineErrorInfo(
            3118,
            "BUSINESS_ERROR_03118", 
            "時間(自)がnullです。");

    /**
     * 時間(至)がnullです。<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_03119 = errorMgr.defineErrorInfo(
            3119,
            "BUSINESS_ERROR_03119", 
            "時間(至)がnullです。");

    /**
     * 指定範囲は３時間以内で入力してください。<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_03120 = errorMgr.defineErrorInfo(
            3120,
            "BUSINESS_ERROR_03120", 
            "指定範囲は３時間以内で入力してください。");

    /**
     * IPアドレスの値が不正です。<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_03121 = errorMgr.defineErrorInfo(
            3121,
            "BUSINESS_ERROR_03121", 
            "IPアドレスの値が不正です。");

    /**
     * IPアドレスがnull。<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_03122 = errorMgr.defineErrorInfo(
            3122,
            "BUSINESS_ERROR_03122", 
            "IPアドレスが未指定です。");

    /**
     * ステータスが半角数字以外の値です。<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_03123 = errorMgr.defineErrorInfo(
            3123,
            "BUSINESS_ERROR_03123", 
            "ステータスが半角数字以外の値です。");

    /**
     * 適用開始日時が未指定です。<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_03124 = errorMgr.defineErrorInfo(
            3124,
            "BUSINESS_ERROR_03124", 
            "適用開始日時が未指定です。");

    /**
     * 適用終了日時が未指定です。<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_03125 = errorMgr.defineErrorInfo(
            3125,
            "BUSINESS_ERROR_03125", 
            "適用終了日時が未指定です。");

    /**
     * 適用終了日時エラー。<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_03126 = errorMgr.defineErrorInfo(
            3126,
            "BUSINESS_ERROR_03126", 
            "適用終了日時エラー(適用終了日時 ＜= 現在日時)。");

    /**
     * 適用開始日時は適用終了日時より大きいです。<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_03127 = errorMgr.defineErrorInfo(
            3127,
            "BUSINESS_ERROR_03127", 
            "適用開始日時は適用終了日時より大きいです。");

    /**
     * 指定範囲は１時間以内で入力してください。<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_03128 = errorMgr.defineErrorInfo(
            3128,
            "BUSINESS_ERROR_03128", 
            "指定範囲は１時間以内で入力してください。");

    /**
     * ランクが未入力です。<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_03129 = errorMgr.defineErrorInfo(
            3129,
            "BUSINESS_ERROR_03129", 
            "ランクが未入力です。");

    /**
     * ランクは半角数字で入力してください。<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_03130 = errorMgr.defineErrorInfo(
            3130,
            "BUSINESS_ERROR_03130", 
            "ランクは半角数字で入力してください。");

    /**
     * ランクは上位30位までの表示しかできません。<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_03131 = errorMgr.defineErrorInfo(
            3131,
            "BUSINESS_ERROR_03131", 
            "ランクは上位30位までの表示しかできません。");

    /**
     * 入力内容に変更がありません。<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_03132 = errorMgr.defineErrorInfo(
            3132,
            "BUSINESS_ERROR_03132", 
            "入力内容に変更がありません。");

    /**
     * CFD口座開設処理中です。<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_03133 = errorMgr.defineErrorInfo(
            3133,
            "BUSINESS_ERROR_03133", 
            "CFD口座開設処理中です。");

    /**
     * 約定処理中の場合、出来約定取消不可です。<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_03134 = errorMgr.defineErrorInfo(
            3134,
            "BUSINESS_ERROR_03134", 
            "約定処理中の場合、出来約定取消不可です。");

    /**
     * 外部接続システムコードの値がGFTとTFX以外の値です。<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_03135 = errorMgr.defineErrorInfo(
            3135,
            "BUSINESS_ERROR_03135", 
            "外部接続システムコードの値がGFTとTFX以外の値です。");

    /**
     * 請求事由がnullです。<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_03136 = errorMgr.defineErrorInfo(
            3136,
            "BUSINESS_ERROR_03136", 
            "請求事由がnullです。");

    /**
     * 立替金/特別立替金、不足金（当日）または指定なしが選択されている場合、日数が0ではない。<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_03138 = errorMgr.defineErrorInfo(
            3138,
            "BUSINESS_ERROR_03138", 
            "立替金/特別立替金、不足金（当日）または指定なしが選択されている場合、日数が0ではない。");

    /**
     * 顧客属性がnullです。<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_03140 = errorMgr.defineErrorInfo(
            3140,
            "BUSINESS_ERROR_03140", 
            "顧客属性がnullです。");

    /**
     * 確認フラグチェック。<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_03141 = errorMgr.defineErrorInfo(
            3141,
            "BUSINESS_ERROR_03141", 
            "確認フラグが未チェックです。");

    /**
     * 口座開設見込客データ削除不可。<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_03142 = errorMgr.defineErrorInfo(
            3142,
            "BUSINESS_ERROR_03142", 
            "口座開設見込客データは削除できません。");

    /**
     * 受入保証金占有率超過エラー。<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_03143 = errorMgr.defineErrorInfo(
            3143,
            "BUSINESS_ERROR_03143", 
            "受入保証金占有率超過エラー。");

    /**
     * 注意情報種別が未定義の値。<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_03147 = errorMgr.defineErrorInfo(
            3147,
            "BUSINESS_ERROR_03147", 
            "注意情報種別が未定義の値。");

    /**
     * 注意情報種別／注意情報区分指定が不整合。<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_03148 = errorMgr.defineErrorInfo(
            3148,
            "BUSINESS_ERROR_03148", 
            "注意情報種別／注意情報区分指定が不整合。");

    /**
     * 注意情報区分コードが未定義の値。<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_03149 = errorMgr.defineErrorInfo(
            3149,
            "BUSINESS_ERROR_03149", 
            "注意情報区分コードが未定義の値。");

    /**
     * 有効日エラー。<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_03150 = errorMgr.defineErrorInfo(
            3150,
            "BUSINESS_ERROR_03150", 
            "有効日エラー。");

    /**
     * 情報発生日時Fromエラー。<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_03151 = errorMgr.defineErrorInfo(
            3151,
            "BUSINESS_ERROR_03151", 
            "情報発生日時Fromエラー。");

    /**
     * 情報発生日時Toエラー。<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_03152 = errorMgr.defineErrorInfo(
            3152,
            "BUSINESS_ERROR_03152", 
            "情報発生日時Toエラー。");

    /**
     * 勘定日の指定が正しくありません。<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_03153 = errorMgr.defineErrorInfo(
            3153,
            "BUSINESS_ERROR_03153", 
            "勘定日の指定が正しくありません。");

    /**
     * 検索日付がnull。<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_03154 = errorMgr.defineErrorInfo(
            3154,
            "BUSINESS_ERROR_03154", 
            "検索日付がnull。");

    /**
     * 未解消客区分がnull。<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_03155 = errorMgr.defineErrorInfo(
            3155,
            "BUSINESS_ERROR_03155", 
            "未解消客区分がnull。");

    /**
     * 未解消客区分が未定義の値。<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_03156 = errorMgr.defineErrorInfo(
            3156,
            "BUSINESS_ERROR_03156", 
            "未解消客区分が未定義の値。");

    /**
     * 本日の証拠金不足はまだ確認していません。<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_03157 = errorMgr.defineErrorInfo(
            3157,
            "BUSINESS_ERROR_03157", 
            "本日の証拠金不足はまだ確認していません。");

    /**
     * 条件に該当する証拠金不足状況情報がありません。<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_03158 = errorMgr.defineErrorInfo(
            3158,
            "BUSINESS_ERROR_03158", 
            "条件に該当する証拠金不足状況情報がありません。");

    /**
     * 付加区分がnullです。<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_03160 = errorMgr.defineErrorInfo(
            3160,
            "BUSINESS_ERROR_03160", 
            "付加区分がnullです。");

    /**
     * 外部接続システムコードの値が不正です。<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_03161 = errorMgr.defineErrorInfo(
            3161,
            "BUSINESS_ERROR_03161", 
            "外部接続システムコードの値が不正です。");

    /**
     * 指定したコースの可能額が取得できません。<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_03162 = errorMgr.defineErrorInfo(
            3162,
            "BUSINESS_ERROR_03162", 
            "指定したコースの可能額が取得できません。");

    /**
     * 運用コードが5桁の半角数字ではありません。<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_03163 = errorMgr.defineErrorInfo(
            3163,
            "BUSINESS_ERROR_03163", 
            "運用コードが5桁の半角数字ではありません。");

    /**
     * 運用コードの左2byteが引数.外国株式運用コード採番区分ではありません。<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_03164 = errorMgr.defineErrorInfo(
            3164,
            "BUSINESS_ERROR_03164", 
            "運用コードの左2byteが引数.外国株式運用コード採番区分ではありません。");

    /**
     * メールアドレス登録IDとメールアドレスのチェック。<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_03165 = errorMgr.defineErrorInfo(
            3165,
            "BUSINESS_ERROR_03165", 
            "メールアドレス登録IDとメールアドレスは同時に入力される。");

    /**
     * 姓が未入力です。<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_03167 = errorMgr.defineErrorInfo(
            3167,
            "BUSINESS_ERROR_03167", 
            "姓が未入力です。");

    /**
     * 入力したメールアドレスが携帯メールアドレスにであるかチェックする。<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_03168 = errorMgr.defineErrorInfo(
            3168,
            "BUSINESS_ERROR_03168", 
            "入力されるメールアドレスは携帯メールアドレスである。");

    /**
     * 更新項目が未入力です。<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_03169 = errorMgr.defineErrorInfo(
            3169,
            "BUSINESS_ERROR_03169", 
            "更新項目が未入力です。");

    /**
     * 更新項目が存在しないコード値です。<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_03170 = errorMgr.defineErrorInfo(
            3170,
            "BUSINESS_ERROR_03170", 
            "更新項目が存在しないコード値です。");

    /**
     * 更新後状態が未入力です。<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_03171 = errorMgr.defineErrorInfo(
            3171,
            "BUSINESS_ERROR_03171", 
            "更新後状態が未入力です。");

    /**
     * 更新後状態が存在しないコード値です。<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_03172 = errorMgr.defineErrorInfo(
            3172,
            "BUSINESS_ERROR_03172", 
            "更新後状態が存在しないコード値です。");

    /**
     * 削除フラグが存在しないコード値です。<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_03173 = errorMgr.defineErrorInfo(
            3173,
            "BUSINESS_ERROR_03173", 
            "削除フラグが存在しないコード値です。");

    /**
     * 印刷フラグが存在しないコード値です。<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_03174 = errorMgr.defineErrorInfo(
            3174,
            "BUSINESS_ERROR_03174", 
            "印刷フラグが存在しないコード値です。");

    /**
     * 受領フラグが存在しないコード値です。<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_03175 = errorMgr.defineErrorInfo(
            3175,
            "BUSINESS_ERROR_03175", 
            "受領フラグが存在しないコード値です。");

    /**
     * 外国人フラグ存在しないコード値です。<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_03176 = errorMgr.defineErrorInfo(
            3176,
            "BUSINESS_ERROR_03176", 
            "外国人フラグ存在しないコード値です。");

    /**
     * 更新後状態の入力値が不正です。<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_03177 = errorMgr.defineErrorInfo(
            3177,
            "BUSINESS_ERROR_03177", 
            "更新後状態の入力値が不正です。");

    /**
     * 口座開設見込客が削除済です。<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_03178 = errorMgr.defineErrorInfo(
            3178,
            "BUSINESS_ERROR_03178", 
            "口座開設見込客が削除済です。");

    /**
     * 口座開設データ移管処理中。<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_03179 = errorMgr.defineErrorInfo(
            3179,
            "BUSINESS_ERROR_03179", 
            "口座開設データ移管処理中。");

    /**
     * 顧客区分が未入力です。<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_03180 = errorMgr.defineErrorInfo(
            3180,
            "BUSINESS_ERROR_03180", 
            "顧客区分が未入力です。");

    /**
     * 口座開設状況が未開設以外の場合、削除フラグ切替不可。<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_03181 = errorMgr.defineErrorInfo(
            3181,
            "BUSINESS_ERROR_03181", 
            "口座開設状況が未開設以外の場合、削除フラグ切替不可。");

    /**
     * 管理者区分存在しないコード値です。<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_03182 = errorMgr.defineErrorInfo(
            3182,
            "BUSINESS_ERROR_03182", 
            "管理者区分存在しないコード値です。");

    /**
     * メールアドレス登録ID 又はメールアドレスが未入力です。<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_03183 = errorMgr.defineErrorInfo(
            3183,
            "BUSINESS_ERROR_03183", 
            "メールアドレス登録ID 又はメールアドレスが未入力です。");

    /**
     * 処理区分が不正です。<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_03184 = errorMgr.defineErrorInfo(
            3184,
            "BUSINESS_ERROR_03184", 
            "処理区分が不正です。");

    /**
     * FX暗証番号２とFX暗証番号が一致です。<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_03185 = errorMgr.defineErrorInfo(
            3185,
            "BUSINESS_ERROR_03185", 
            "FX暗証番号２とFX暗証番号が一致です。");

    /**
     * 内部者登録区分が存在しないコード値です。<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_03186 = errorMgr.defineErrorInfo(
            3186,
            "BUSINESS_ERROR_03186", 
            "内部者登録区分が存在しないコード値です。");

    /**
     * 入力されたメールアドレスは携帯メールアドレスではありません。<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_03189 = errorMgr.defineErrorInfo(
            3189,
            "BUSINESS_ERROR_03189", 
            "入力されたメールアドレスは携帯メールアドレスではありません。");

    /**
     * メールアドレス番号が未入力です。<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_03190 = errorMgr.defineErrorInfo(
            3190,
            "BUSINESS_ERROR_03190", 
            "メールアドレス番号が未入力です。");

    /**
     * メールアドレス番号が数字以外の値です。<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_03191 = errorMgr.defineErrorInfo(
            3191,
            "BUSINESS_ERROR_03191", 
            "メールアドレス番号が数字以外の値です。");

    /**
     * メールアドレス番号の入力が不正です。<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_03192 = errorMgr.defineErrorInfo(
            3192,
            "BUSINESS_ERROR_03192", 
            "メールアドレス番号の入力が不正です。");

    /**
     * メールアドレス区分が未入力です。<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_03193 = errorMgr.defineErrorInfo(
            3193,
            "BUSINESS_ERROR_03193", 
            "メールアドレス区分が未入力です。");

    /**
     * メールアドレス番号がメールアドレス情報に存在しません。<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_03194 = errorMgr.defineErrorInfo(
            3194,
            "BUSINESS_ERROR_03194", 
            "メールアドレス番号がメールアドレス情報に存在しません。");

    /**
     * メール種別番号が未入力です。<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_03195 = errorMgr.defineErrorInfo(
            3195,
            "BUSINESS_ERROR_03195", 
            "メール種別番号が未入力です。");

    /**
     * メール種別番号の入力が不正です。<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_03196 = errorMgr.defineErrorInfo(
            3196,
            "BUSINESS_ERROR_03196", 
            "メール種別番号の入力が不正です。");

    /**
     * 選択したメールアドレスが未入力です。<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_03197 = errorMgr.defineErrorInfo(
            3197,
            "BUSINESS_ERROR_03197", 
            "選択したメールアドレスが未入力です。");

    /**
     * 電子交付メールはPCメールアドレスのみに指定可能です。<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_03198 = errorMgr.defineErrorInfo(
            3198,
            "BUSINESS_ERROR_03198", 
            "電子交付メールはPCメールアドレスのみに指定可能です。");

    /**
     * 成行注文の場合、期限付注文はお取扱いできません。<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_03199 = errorMgr.defineErrorInfo(
            3199,
            "BUSINESS_ERROR_03199", 
            "成行注文の場合、期限付注文はお取扱いできません。");

    /**
     * W指値注文のストップ注文は成行指定不可です。<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_03200 = errorMgr.defineErrorInfo(
            3200,
            "BUSINESS_ERROR_03200", 
            "W指値注文のストップ注文は成行指定不可です。");

    /**
     * 指値から成行へ、あるいは成行から指値への訂正は不可です。<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_03201 = errorMgr.defineErrorInfo(
            3201,
            "BUSINESS_ERROR_03201", 
            "指値から成行へ、あるいは成行から指値への訂正は不可です。");

    /**
     * 社内処理項目がnull。<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_03202 = errorMgr.defineErrorInfo(
            3202,
            "BUSINESS_ERROR_03202", 
            "社内処理項目がnullです。");

    /**
     * 市場未発注注文検索エラー。<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_03203 = errorMgr.defineErrorInfo(
            3203,
            "BUSINESS_ERROR_03203", 
            "条件に該当する市場未発注注文は存在しません。");

    /**
     * CSVフォーマット未登録エラー。<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_03204 = errorMgr.defineErrorInfo(
            3204,
            "BUSINESS_ERROR_03204", 
            "フォーマットが登録されていません。");

    /**
     * 社内処理項目エラー。<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_03205 = errorMgr.defineErrorInfo(
            3205,
            "BUSINESS_ERROR_03205", 
            "社内処理項目が不正です。");

    /**
     * 該当する社内項目の注文検索エラー。<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_03206 = errorMgr.defineErrorInfo(
            3206,
            "BUSINESS_ERROR_03206", 
            "該当する社内項目の注文はありません。");

    /**
     * 仮想サーバ情報エラー。<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_03207 = errorMgr.defineErrorInfo(
            3207,
            "BUSINESS_ERROR_03207", 
            "仮想サーバ情報がないです。");

    /**
     * 注文種別エラー。<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_03208 = errorMgr.defineErrorInfo(
            3208,
            "BUSINESS_ERROR_03208", 
            "注文種別が不正です。");

    /**
     * 同一区分の複数行のレコードが選択されました。<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_03209 = errorMgr.defineErrorInfo(
            3209,
            "BUSINESS_ERROR_03209", 
            "同一区分の複数行のレコードが選択されました。");

    /**
     * 電子交付サービス申込を行うことができません。<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_03210 = errorMgr.defineErrorInfo(
            3210,
            "BUSINESS_ERROR_03210", 
            "電子交付サービス申込を行うことができません。");

    /**
     * 取引報告書交付区分が未定義の値です。<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_03211 = errorMgr.defineErrorInfo(
            3211,
            "BUSINESS_ERROR_03211", 
            "取引報告書交付区分が未定義の値です。");

    /**
     * 取引残高報告書交付区分が未定義の値です。<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_03212 = errorMgr.defineErrorInfo(
            3212,
            "BUSINESS_ERROR_03212", 
            "取引残高報告書交付区分が未定義の値です。");

    /**
     * 運用報告書交付区分が未定義の値です。<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_03213 = errorMgr.defineErrorInfo(
            3213,
            "BUSINESS_ERROR_03213", 
            "運用報告書交付区分が未定義の値です。");

    /**
     * 約款・規定集報告書交付区分が未定義の値です。<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_03214 = errorMgr.defineErrorInfo(
            3214,
            "BUSINESS_ERROR_03214", 
            "約款・規定集報告書交付区分が未定義の値です。");

    /**
     * 書面１交付区分が未定義の値です。<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_03215 = errorMgr.defineErrorInfo(
            3215,
            "BUSINESS_ERROR_03215", 
            "書面１交付区分が未定義の値です。");

    /**
     * 書面２交付区分が未定義の値です。<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_03216 = errorMgr.defineErrorInfo(
            3216,
            "BUSINESS_ERROR_03216", 
            "書面２交付区分が未定義の値です。");

    /**
     * 書面３交付区分が未定義の値です。<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_03217 = errorMgr.defineErrorInfo(
            3217,
            "BUSINESS_ERROR_03217", 
            "書面３交付区分が未定義の値です。");

    /**
     * 書面４交付区分が未定義の値です。<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_03218 = errorMgr.defineErrorInfo(
            3218,
            "BUSINESS_ERROR_03218", 
            "書面４交付区分が未定義の値です。");

    /**
     * 書面５交付区分が未定義の値です。<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_03219 = errorMgr.defineErrorInfo(
            3219,
            "BUSINESS_ERROR_03219", 
            "書面５交付区分が未定義の値です。");

    /**
     * 申込対象書面が選択されていません。<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_03220 = errorMgr.defineErrorInfo(
            3220,
            "BUSINESS_ERROR_03220", 
            "申込対象書面が選択されていません。");

    /**
     * 電子交付申込フラグが未入力です。<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_03221 = errorMgr.defineErrorInfo(
            3221,
            "BUSINESS_ERROR_03221", 
            "電子交付申込フラグが未入力です。");

    /**
     * 電子交付申込フラグが未定義の値です。<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_03222 = errorMgr.defineErrorInfo(
            3222,
            "BUSINESS_ERROR_03222", 
            "電子交付申込フラグが未定義の値です。");

    /**
     * 交付対象が未入力です。<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_03223 = errorMgr.defineErrorInfo(
            3223,
            "BUSINESS_ERROR_03223", 
            "交付対象が未入力です。");

    /**
     * ログイン停止中。<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_90201 = errorMgr.defineErrorInfo(
            90201,
            "BUSINESS_ERROR_90201", 
            "ただいまメンテナンス中の為、ログインできません。");

    /**
     * 顧客ログインエラー回数上限超過。<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_90202 = errorMgr.defineErrorInfo(
            90202,
            "BUSINESS_ERROR_90202", 
            "申し訳ありませんが、現在ログインできない状態です。コールセンターにお問合せ下さい。");

    /**
     * パスワード値不正。<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_90203 = errorMgr.defineErrorInfo(
            90203,
            "BUSINESS_ERROR_90203", 
            "ログインできませんでした。パスワードをご確認の上、再度ログインして下さい。");

    /**
     * 指定顧客に成りすます権限なし。<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_90204 = errorMgr.defineErrorInfo(
            90204,
            "BUSINESS_ERROR_90204", 
            "指定顧客に成りすましできません。管理者にお問合せ下さい。");

    /**
     * 指定成りすまし顧客なし。<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_90205 = errorMgr.defineErrorInfo(
            90205,
            "BUSINESS_ERROR_90205", 
            "指定の顧客データが存在しない為、成りすましできません。");

    /**
     * CCオペレータログインエラー回数上限超過。<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_90206 = errorMgr.defineErrorInfo(
            90206,
            "BUSINESS_ERROR_90206", 
            "申し訳ありませんが、現在ログインできない状態です。管理者にお問合せ下さい。");

    /**
     * 成りすまし対象の顧客コード値不正。<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_90207 = errorMgr.defineErrorInfo(
            90207,
            "BUSINESS_ERROR_90207", 
            "顧客コード値が不正です。ご確認の上、再度成りすましを実行して下さい。");

    /**
     * 「下り」で受信したハッシュ値が計算値と一致しない。<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_90208 = errorMgr.defineErrorInfo(
            90208,
            "BUSINESS_ERROR_90208", 
            "大変申し訳ありませんが、取引が出来ない状態です。コールセンターにお問合せ下さい。");

    /**
     * スリングショットが利用可能でない。<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_90209 = errorMgr.defineErrorInfo(
            90209,
            "BUSINESS_ERROR_90209", 
            "そのサービスは現在利用可能になっておりません。有料情報登録を行った後に利用可能となります。");

    /**
     * パスワード変更時の旧パスワードが不正。<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_90210 = errorMgr.defineErrorInfo(
            90210,
            "BUSINESS_ERROR_90210", 
            "パスワードが変更できませんでした。コールセンターにお問合せ下さい。");

    /**
     * 新パスワードとして２度入力したものが一致しなかった。<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_90211 = errorMgr.defineErrorInfo(
            90211,
            "BUSINESS_ERROR_90211", 
            "新パスワードが確認用のものと一致しておりません。ご確認の上、再度入力して下さい。");

    /**
     * パスワード未変更を許可していない部店で前回と同じパスワードが入力された。<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_90212 = errorMgr.defineErrorInfo(
            90212,
            "BUSINESS_ERROR_90212", 
            "パスワードが変わっていません。違うパスワードを入力して下さい。");

    /**
     * 顧客成りすまし時にパスワードが一致しなかった。<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_90213 = errorMgr.defineErrorInfo(
            90213,
            "BUSINESS_ERROR_90213", 
            "パスワードが違う為、成りすましできません。ご確認の上、再度実行して下さい。");

    /**
     * （パスワード変更）新パスワード値が妥当でない。<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_90214 = errorMgr.defineErrorInfo(
            90214,
            "BUSINESS_ERROR_90214", 
            "新パスワードに許されていない文字が含まれています。ご確認の上、再度パスワード変更を行って下さい。");

    /**
     * 管理者ログインエラー回数上限超過。<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_90215 = errorMgr.defineErrorInfo(
            90215,
            "BUSINESS_ERROR_90215", 
            "申し訳ありませんが、現在ログインできない状態です。他の管理者にお問合せ下さい。");

    /**
     * （パスワード変更）新パスワード長が範囲外。<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_90216 = errorMgr.defineErrorInfo(
            90216,
            "BUSINESS_ERROR_90216", 
            "新パスワードの長さが不正です。ご確認の上、再度パスワード変更を行って下さい。");

    /**
     * （パスワード変更）新パスワードが現在パスワードと同じ。<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_90217 = errorMgr.defineErrorInfo(
            90217,
            "BUSINESS_ERROR_90217", 
            "パスワードが変わっていません。現在と違うパスワードを入力して下さい。");

    /**
     * （パスワード変更）新パスワードがログイン名と同じ。<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_90218 = errorMgr.defineErrorInfo(
            90218,
            "BUSINESS_ERROR_90218", 
            "パスワードがログイン名と同じです。ログイン名とは違うパスワードを入力して下さい。");

    /**
     * （パスワード変更）新パスワードが旧パスワード（３世代以前）と同じ。<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_90219 = errorMgr.defineErrorInfo(
            90219,
            "BUSINESS_ERROR_90219", 
            "このパスワードは過去３世代以内で使われています。過去３世代とは違うパスワードを入力して下さい。");

    /**
     * （パスワード変更）新パスワードが旧パスワード（３世代以前）と類似。<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_90220 = errorMgr.defineErrorInfo(
            90220,
            "BUSINESS_ERROR_90220", 
            "このパスワードは過去３世代以内のものと類似しています。過去３世代とは違うパスワードを入力して下さい。");

    /**
     * その他認証エラー。<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_90221 = errorMgr.defineErrorInfo(
            90221,
            "BUSINESS_ERROR_90221", 
            "ログインできませんでした。入力内容をご確認の上、再度ログインして下さい。");

    /**
     * 顧客コードの妥当性をチャック。<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_90222 = errorMgr.defineErrorInfo(
            90222,
            "BUSINESS_ERROR_90222", 
            "顧客コードの値が不正です。");

    /**
     * SHA1認証チェック。<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_90223 = errorMgr.defineErrorInfo(
            90223,
            "BUSINESS_ERROR_90223", 
            "SHA1認証キーが正しくありません。ログイン失敗しました。");

    /**
     * ハッシュ認証チェック。<br>
     */
     public static final ErrorInfo BUSINESS_ERROR_90224 = errorMgr.defineErrorInfo(
            90224,
            "BUSINESS_ERROR_90224", 
            "ハッシュ認証エラー。");

    /**
     * 顧客重複登録の可能性あり。<br>
     */
     public static final ErrorInfo WARNING_60001 = errorMgr.defineErrorInfo(
            60001,
            "WARNING_60001", 
            "顧客重複登録の可能性あり。");

    /**
     * Y客の可能性あり。<br>
     */
     public static final ErrorInfo WARNING_60002 = errorMgr.defineErrorInfo(
            60002,
            "WARNING_60002", 
            "Y客の可能性あり。");

    /**
     * 顧客名文字サイズが超過しているため、伝票上切り捨てられる。<br>
     */
     public static final ErrorInfo WARNING_60003 = errorMgr.defineErrorInfo(
            60003,
            "WARNING_60003", 
            "顧客名文字サイズが超過しているため、伝票上切り捨てられる。");

    /**
     * 住所１（カナ）文字サイズが超過しているため、伝票上切り捨てられる。<br>
     */
     public static final ErrorInfo WARNING_60004 = errorMgr.defineErrorInfo(
            60004,
            "WARNING_60004", 
            "住所１（カナ）文字サイズが超過しているため、伝票上切り捨てられる。");

    /**
     * 住所２（カナ）文字サイズが超過しているため、伝票上切り捨てられる。<br>
     */
     public static final ErrorInfo WARNING_60005 = errorMgr.defineErrorInfo(
            60005,
            "WARNING_60005", 
            "住所２（カナ）文字サイズが超過しているため、伝票上切り捨てられる。");

    /**
     * 住所３（カナ）文字サイズが超過しているため、伝票上切り捨てられる。<br>
     */
     public static final ErrorInfo WARNING_60006 = errorMgr.defineErrorInfo(
            60006,
            "WARNING_60006", 
            "住所３（カナ）文字サイズが超過しているため、伝票上切り捨てられる。");

    /**
     * 住所１文字サイズが超過しているため、伝票上切り捨てられる。<br>
     */
     public static final ErrorInfo WARNING_60007 = errorMgr.defineErrorInfo(
            60007,
            "WARNING_60007", 
            "住所１文字サイズが超過しているため、伝票上切り捨てられる。");

    /**
     * 住所２文字サイズが超過しているため、伝票上切り捨てられる。<br>
     */
     public static final ErrorInfo WARNING_60008 = errorMgr.defineErrorInfo(
            60008,
            "WARNING_60008", 
            "住所２文字サイズが超過しているため、伝票上切り捨てられる。");

    /**
     * 住所３文字サイズが超過しているため、伝票上切り捨てられる。<br>
     */
     public static final ErrorInfo WARNING_60009 = errorMgr.defineErrorInfo(
            60009,
            "WARNING_60009", 
            "住所３文字サイズが超過しているため、伝票上切り捨てられる。");

    /**
     * 住所文字サイズが超過しているため、伝票上切り捨てられる。<br>
     */
     public static final ErrorInfo WARNING_60010 = errorMgr.defineErrorInfo(
            60010,
            "WARNING_60010", 
            "住所文字サイズが超過しているため、伝票上切り捨てられる。");

    /**
     * メールアドレス重複。<br>
     */
     public static final ErrorInfo WARNING_60011 = errorMgr.defineErrorInfo(
            60011,
            "WARNING_60011", 
            "メールアドレス重複登録の可能性あり。");

    /**
     * 電話番号重複。<br>
     */
     public static final ErrorInfo WARNING_60012 = errorMgr.defineErrorInfo(
            60012,
            "WARNING_60012", 
            "電話番号重複登録の可能性あり。");

    /**
     * 携帯電話番号重複。<br>
     */
     public static final ErrorInfo WARNING_60013 = errorMgr.defineErrorInfo(
            60013,
            "WARNING_60013", 
            "携帯電話番号重複登録の可能性あり。");

            
    /**
     * コンストラクタ。
     */
    private WEB3ErrorCatalog()
    {
    }
}

@
