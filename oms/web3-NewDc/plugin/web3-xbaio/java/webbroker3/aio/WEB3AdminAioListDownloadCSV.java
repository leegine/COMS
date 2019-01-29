head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.16.02.35.19;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	6a04d801c24738b;
filename	WEB3AdminAioListDownloadCSV.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name　@　@　@　@: 出金申込問合せダウンロードCSV (WEB3AdminAioListDownloadCSV.java)
Author Name      : Daiwa Institute of Research
Revision History : 2007/02/02 何文敏 (中訊) 新規作成 仕様変更モデル No.694、697、698、700
Revision History : 2007/02/19 長瀬亜紀 (SCS) 仕様変更モデル No.708、実装の問題 No.007
Revision History : 2009/03/18 王志葵 (中訊) 仕様変更モデル No.1155
*/

package webbroker3.aio;

import java.text.SimpleDateFormat;
import java.util.Date;

import com.fitechlabs.xtrade.plugin.tc.gentrade.GtlUtils;
import com.fitechlabs.xtrade.plugin.tc.gentrade.OrderStatusEnum;
import com.fitechlabs.xtrade.plugin.tc.gentrade.OrderTypeEnum;

import webbroker3.aio.define.WEB3AIOAccountTypeValueDef;
import webbroker3.aio.define.WEB3AdminAioDivDef;
import webbroker3.common.define.WEB3FinSaveDivDef;
import webbroker3.common.define.WEB3ModifyCancelTypeDef;
import webbroker3.common.define.WEB3OrderRootDivDef;
import webbroker3.gentrade.WEB3GentradeCsvColumnModel;
import webbroker3.gentrade.WEB3GentradeCsvDataModel;
import webbroker3.util.WEB3DateUtility;
import webbroker3.util.WEB3LogUtility;
import webbroker3.util.WEB3StringTypeUtility;

/**
 * (管理者入出金一覧ダウンロードCSV)<BR>
 * 管理者入出金一覧ダウンロードCSVファ@イルデータクラス<BR>
 *
 * @@author 何文敏 (中訊)
 * @@version 1.0
 */
public class WEB3AdminAioListDownloadCSV extends WEB3GentradeCsvDataModel
{
    /**
     * ログ出力オブジェクト。<BR>
     */
    private static WEB3LogUtility log =
       WEB3LogUtility.getInstance(WEB3AdminAioListDownloadCSV.class);

    /**
     * (注文種別ラベル)<BR>
     * 注文種別ベル<BR>
     */
    private static final String ORDER_TYPE_LABEL = "注文種別";

    /**
     * (受渡日ラベル)<BR>
     * 受渡日ラベル<BR>
     */
    private static final String DELIVERY_DATE_LABEL = "受渡日";

    /**
     * (部店コードラベル)<BR>
     * 部店コードラベル<BR>
     */
    private static final String BRANCH_CODE_LABEL = "部店コード";

    /**
     * (顧客コードラベル)<BR>
     * 顧客コードラベル<BR>
     */
    private static final String ACCOUNT_CODE_LABEL = "顧客コード";

    /**
     * (顧客名ラベル)<BR>
     * 顧客名ラベル<BR>
     */
    private static final String ACCOUNT_NAME_LABEL = "顧客名";

    /**
     * (注文日時ラベル)<BR>
     * 注文日時ラベル<BR>
     */
    private static final String ORDER_DATE_LABEL = "注文日時";

    /**
     * (ステータスラベル)<BR>
     * ステータスラベル<BR>
     */
    private static final String STATUS_LABEL = "ステータス";

    /**
     * (入金金額ラベル)<BR>
     * 入金金額ラベル<BR>
     */
    private static final String CASHINAMT_LABEL = "入金金額";

    /**
     * (出金金額ラベル)<BR>
     * 出金金額ラベル<BR>
     */
    private static final String CASHOUTAMT_LABEL = "出金金額";

    /**
     * (入力経路ラベル)<BR>
     * 入力経路ラベル<BR>
     */
    private static final String INPUT_ROOT_LABEL = "入力経路";

    /**
     * (入力者ラベル)<BR>
     * 入力者ラベル<BR>
     */
    private static final String TRADER_LABEL = "入力者";

    /**
     * (口座情報ラベル)<BR>
     * 口座情報ラベル<BR>
     */
    private static final String ACCOUNT_INFO_LABEL = "口座情報";

    /**
     * (注文種別_SONAR入金表示)<BR>
     * 定数定義プロパティ<BR>
     * 注文種別_SONAR入金表示<BR>
     */
    private static final String SONAR_CASHIN = "SONAR入金";

    /**
     * (注文種別_バーチャル入金表示)<BR>
     * 定数定義プロパティ<BR>
     * 注文種別_バーチャル入金表示<BR>
     */
    private static final String VIRTUAL_CASHIN = "バーチャル入金";

    /**
     * (注文種別_ネット入金表示)<BR>
     * 定数定義プロパティ<BR>
     * 注文種別_ネット入金表示<BR>
     */
    private static final String NET_CASHIN = "ネット入金";

    /**
     * (注文種別_振替(株先証拠金から預り金)表示)<BR>
     * 定数定義プロパティ<BR>
     * 注文種別_振替(株先証拠金から預り金)表示<BR>
     */
    private static final String MARGIN_FROM_DEPOSIT_AMOUNT = "振替(株先証拠金から預り金)";

    /**
     * (注文種別_為替保証金振替(為替保証金から預り金)表示)<BR>
     * 定数定義プロパティ<BR>
     * 注文種別_為替保証金振替(為替保証金から預り金)表示<BR>
     */
    private static final String DEPOSIT_AMOUNT_FROM_FX_GUARANTEE = "為替保証金振替(為替保証金から預り金)";

    /**
     * (注文種別_その他振替(Xから預り金)表示)<BR>
     * 定数定義プロパティ<BR>
     * 注文種別_その他振替(Xから預り金)表示<BR>
     */
    private static final String FROM_OTHER_TRANSFER = "その他振替(Xから預り金)";

    /**
     * (注文種別_出金表示)<BR>
     * 定数定義プロパティ<BR>
     * 注文種別_出金表示<BR>
     */
    private static final String CASHOUT = "出金";

    /**
     * (注文種別_振替(預り金から株先証拠金)表示)<BR>
     * 定数定義プロパティ<BR>
     * 注文種別_振替(預り金から株先証拠金)表示<BR>
     */
    private static final String FROM_DEPOSIT_AMOUNT_MARGIN = "振替(預り金から株先証拠金)";

    /**
     * (注文種別_為替保証金振替(預り金から為替保証金)表示)<BR>
     * 定数定義プロパティ<BR>
     * 注文種別_為替保証金振替(預り金から為替保証金)表示<BR>
     */
    private static final String FX_GUARANTEE_FROM_DEPOSIT_AMOUNT = "為替保証金振替(預り金から為替保証金)";

    /**
     * (注文種別_その他振替(預り金からX)表示)<BR>
     * 定数定義プロパティ<BR>
     * 注文種別_その他振替(預り金からX)表示<BR>
     */
    private static final String TO_OTHER_TRANSFER = "その他振替(預り金からX)";

    /**
     * (ステータス_完了表示)<BR>
     * 定数定義プロパティ<BR>
     * ステータス_完了表示<BR>
     */
    private static final String ORDER_STATUS_COMPLETE = "完了";

    /**
     * (ステータス_未処理表示)<BR>
     * 定数定義プロパティ<BR>
     * ステータス_未処理表示<BR>
     */
    private static final String ORDER_STATUS_NOT_TRANSACTION = "未処理";

    /**
     * (ステータス_エラー表示)<BR>
     * 定数定義プロパティ<BR>
     * ステータス_エラー表示<BR>
     */
    private static final String ORDER_STATUS_ACCEPTED_ERROR = "エラー";

    /**
     * (入力経路_WEB表示)<BR>
     * 定数定義プロパティ<BR>
     * 入力経路_WEB表示<BR>
     */
    private static final String INPUT_ROOT_WEB = "WEB";
    
    /**
     * (入力経路_SONAR表示)<BR>
     * 定数定義プロパティ<BR>
     * 入力経路_SONAR表示<BR>
     */
    private static final String INPUT_ROOT_SONAR = "SONAR";

    /**
     * (入力経路_コール表示)<BR>
     * 定数定義プロパティ<BR>
     * 入力経路_コール表示<BR>
     */
    private static final String INPUT_ROOT_CALL = "コール";

    /**
     * (入力経路_管理者表示)<BR>
     * 定数定義プロパティ<BR>
     * 入力経路_管理者表示<BR>
     */
    private static final String INPUT_ROOT_ADMIN = "管理者";

    /**
     * (預金区分_普通預金表示)<BR>
     * 定数定義プロパティ<BR>
     * 預金区分_普通預金表示<BR>
     */
    private static final String DEPOSIT_DIV_GENERAL = "普通預金";

    /**
     * (預金区分_当座預金表示)<BR>
     * 定数定義プロパティ<BR>
     * 預金区分_当座預金表示<BR>
     */
    private static final String DEPOSIT_DIV_ACCOUNT = "当座預金";

    /**
     * (預金区分_その他表示)<BR>
     * 定数定義プロパティ<BR>
     * 預金区分_その他表示<BR>
     */
    private static final String DEPOSIT_DIV_OTHER = "その他";

    /**
     * (預金区分_貯蓄預金表示)<BR>
     * 定数定義プロパティ<BR>
     * 預金区分_貯蓄預金表示<BR>
     */
    private static final String DEPOSIT_DIV_SAVING = "貯蓄預金";

    /**
     * (入出金一覧ダウンロードCSV)<BR>
     * コンストラクタ。 <BR>
     * <BR>
     * インスタンスを生成し、ヘッダ情報をセットする。 <BR>
     * 　@－super()にてインスタンスを生成する。 <BR>
     * 　@－createキーヘッダ()をコールし、キーヘッダ情報を作成する。 <BR>
     * 　@－createカラムヘッダ()をコールし、カラムヘッダ情報を作成する。 <BR>
     * @@roseuid 45B9A47A0348
     */
    public WEB3AdminAioListDownloadCSV()
    {
        // super()にてインスタンスを生成する。
        super();

        // createキーヘッダ()をコールし、キーヘッダ情報を作成する。
        this.createKeyHeader();

        // createカラムヘッダ()をコールし、カラムヘッダ情報を作成する。
        this.createColumnHeader();
    }

    /**
     * (createカラムヘッダ)<BR>
     * カラムヘッダをセットする。 <BR>
     * <BR>
     * 以下の通りCSVカラムモデルの配列を生成し、setカラムヘッダ()にてインスタンスにセッ
     * トする。 <BR>
     * <BR>
     * [カラムヘッダ配列] <BR>
     * <BR>
     * －　@index = 0 <BR>
     * 　@[CSVカラムモデル コンストラクタの引数] <BR>
     * 　@項目ラベル：　@入出金一覧ダウンロードCSV.注文種別ラベル<BR>
     * 　@カラム番号： 0 <BR>
     * 　@項目型：　@CSVカラムモデル.項目型_文字列 <BR>
     * 　@日付フォーマット：　@null <BR>
     * <BR>
     * －　@index = 1 <BR>
     * 　@[CSVカラムモデル コンストラクタの引数] <BR>
     * 　@項目ラベル：　@入出金一覧ダウンロードCSV.受渡日ラベル<BR>
     * 　@カラム番号： 1 <BR>
     * 　@項目型：　@CSVカラムモデル.項目型_日付 <BR>
     * 　@日付フォーマット：　@new SimpleDateFormat("yyyy/MM/dd")<BR>
     * <BR>
     * －　@index = 2 <BR>
     * 　@[CSVカラムモデル コンストラクタの引数] <BR>
     * 　@項目ラベル：　@入出金一覧ダウンロードCSV.部店コードラベル<BR>
     * 　@カラム番号： 2 <BR>
     * 　@項目型：　@CSVカラムモデル.項目型_文字列 <BR>
     * 　@日付フォーマット：　@null <BR>
     * <BR>
     * －　@index = 3 <BR>
     * 　@[CSVカラムモデル コンストラクタの引数] <BR>
     * 　@項目ラベル：　@入出金一覧ダウンロードCSV.顧客コードラベル<BR>
     * 　@カラム番号： 3 <BR>
     * 　@項目型：　@CSVカラムモデル.項目型_文字列 <BR>
     * 　@日付フォーマット：　@null <BR>
     * <BR>
     * －　@index = 4 <BR>
     * 　@[CSVカラムモデル コンストラクタの引数] <BR>
     * 　@項目ラベル：　@入出金一覧ダウンロードCSV.顧客氏名ラベル<BR>
     * 　@カラム番号： 4 <BR>
     * 　@項目型：　@CSVカラムモデル.項目型_文字列 <BR>
     * 　@日付フォーマット：　@null <BR>
     * <BR>
     * －　@index = 5 <BR>
     * 　@[CSVカラムモデル コンストラクタの引数] <BR>
     * 　@項目ラベル：　@入出金一覧ダウンロードCSV.注文日時ラベル<BR>
     * 　@カラム番号： 5 <BR>
     * 　@項目型：　@CSVカラムモデル.項目型_日付時間<BR>
     * 　@日付フォーマット：　@new SimpleDateFormat("yyyy/MM/dd HH:mm:ss") <BR>
     * <BR>
     * －　@index = 6 <BR>
     * 　@[CSVカラムモデル コンストラクタの引数] <BR>
     * 　@項目ラベル：　@入出金一覧ダウンロードCSV.ステータスラベル<BR>
     * 　@カラム番号： 6 <BR>
     * 　@項目型：　@CSVカラムモデル.項目型_文字列 <BR>
     * 　@日付フォーマット：　@null <BR>
     * <BR>
     * －　@index = 7 <BR>
     * 　@[CSVカラムモデル コンストラクタの引数] <BR>
     * 　@項目ラベル：　@入出金一覧ダウンロードCSV.入金金額ラベル<BR>
     * 　@カラム番号： 7 <BR>
     * 　@項目型：　@CSVカラムモデル.項目型_文字列 <BR>
     * 　@日付フォーマット：　@null <BR>
     * <BR>
     * －　@index = 8 <BR>
     * 　@[CSVカラムモデル コンストラクタの引数] <BR>
     * 　@項目ラベル：　@入出金一覧ダウンロードCSV.出金金額ラベル<BR>
     * 　@カラム番号： 8 <BR>
     * 　@項目型：　@CSVカラムモデル.項目型_文字列 <BR>
     * 　@日付フォーマット：　@null <BR>
     * <BR>
     * －　@index = 9 <BR>
     * 　@[CSVカラムモデル コンストラクタの引数] <BR>
     * 　@項目ラベル：　@入出金一覧ダウンロードCSV.入力経路ラベル<BR>
     * 　@カラム番号： 9 <BR>
     * 　@項目型：　@CSVカラムモデル.項目型_文字列 <BR>
     * 　@日付フォーマット：　@null <BR>
     * <BR>
     * －　@index = 10 <BR>
     * 　@[CSVカラムモデル コンストラクタの引数] <BR>
     * 　@項目ラベル：　@入出金一覧ダウンロードCSV.入力者ラベル<BR>
     * 　@カラム番号： 10 <BR>
     * 　@項目型：　@CSVカラムモデル.項目型_文字列 <BR>
     * 　@日付フォーマット：　@null <BR>
     * <BR>
     * －　@index = 11 <BR>
     * 　@[CSVカラムモデル コンストラクタの引数] <BR>
     * 　@項目ラベル：　@入出金一覧ダウンロードCSV.口座情報ラベル<BR>
     * 　@カラム番号： 11 <BR>
     * 　@項目型：　@CSVカラムモデル.項目型_文字列 <BR>
     * 　@日付フォーマット：　@null <BR>
     * <BR>
     * @@roseuid 45B9A49800F7
     */
    private void createColumnHeader()
    {
        final String STR_METHOD_NAME = " createColumnHeader()";
        log.entering(STR_METHOD_NAME );

        final int COLUMN_MAX = 12;

        WEB3GentradeCsvColumnModel[] l_models = new WEB3GentradeCsvColumnModel[COLUMN_MAX];

        //index = 0
        l_models[0] =
            new WEB3GentradeCsvColumnModel(
                WEB3AdminAioListDownloadCSV.ORDER_TYPE_LABEL,
                0,
                WEB3GentradeCsvColumnModel.STRINGTYPE,
                null);

        //index = 1
        l_models[1] =
            new WEB3GentradeCsvColumnModel(
                WEB3AdminAioListDownloadCSV.DELIVERY_DATE_LABEL,
                1,
                WEB3GentradeCsvColumnModel.DATETYPE,
                new SimpleDateFormat("yyyy/MM/dd"));

        //index = 2
        l_models[2] =
            new WEB3GentradeCsvColumnModel(
                WEB3AdminAioListDownloadCSV.BRANCH_CODE_LABEL,
                2,
                WEB3GentradeCsvColumnModel.STRINGTYPE,
                null);

        //index = 3
        l_models[3] =
            new WEB3GentradeCsvColumnModel(
                WEB3AdminAioListDownloadCSV.ACCOUNT_CODE_LABEL,
                3,
                WEB3GentradeCsvColumnModel.STRINGTYPE,
                null);

        //index = 4
        l_models[4] =
            new WEB3GentradeCsvColumnModel(
                WEB3AdminAioListDownloadCSV.ACCOUNT_NAME_LABEL,
                4,
                WEB3GentradeCsvColumnModel.STRINGTYPE,
                null);

        //index = 5
        l_models[5] =
            new WEB3GentradeCsvColumnModel(
                WEB3AdminAioListDownloadCSV.ORDER_DATE_LABEL,
                5,
                WEB3GentradeCsvColumnModel.TIMESTAMPTYPE,
                new SimpleDateFormat("yyyy/MM/dd HH:mm:ss"));

        //index = 6
        l_models[6] =
            new WEB3GentradeCsvColumnModel(
                WEB3AdminAioListDownloadCSV.STATUS_LABEL,
                6,
                WEB3GentradeCsvColumnModel.STRINGTYPE,
                null);

        //index = 7
        l_models[7] =
            new WEB3GentradeCsvColumnModel(
                WEB3AdminAioListDownloadCSV.CASHINAMT_LABEL,
                7,
                WEB3GentradeCsvColumnModel.STRINGTYPE,
                null);

        //index = 8
        l_models[8] =
            new WEB3GentradeCsvColumnModel(
                WEB3AdminAioListDownloadCSV.CASHOUTAMT_LABEL,
                8,
                WEB3GentradeCsvColumnModel.STRINGTYPE,
                null);

        //index = 9
        l_models[9] =
            new WEB3GentradeCsvColumnModel(
                WEB3AdminAioListDownloadCSV.INPUT_ROOT_LABEL,
                9,
                WEB3GentradeCsvColumnModel.STRINGTYPE,
                null);

        //index = 10
        l_models[10] =
            new WEB3GentradeCsvColumnModel(
                WEB3AdminAioListDownloadCSV.TRADER_LABEL,
                10,
                WEB3GentradeCsvColumnModel.STRINGTYPE,
                null);

        //index = 11
        l_models[11] =
            new WEB3GentradeCsvColumnModel(
                WEB3AdminAioListDownloadCSV.ACCOUNT_INFO_LABEL,
                11,
                WEB3GentradeCsvColumnModel.STRINGTYPE,
                null);

        this.setColumnHeader(l_models);

        log.exiting(STR_METHOD_NAME );
    }

    /**
     * (createキーヘッダ)<BR>
     * キーヘッダをセットする。 <BR>
     * <BR>
     * 　@以下の通り文字列の配列を生成し、setキーヘッダ()にてインスタンスにセットする。<BR>
     * <BR>
     * [キーヘッダ配列]<BR>
     * <BR>
     * －　@index = 0 <BR>
     * 　@現在日時を"yyyy/MM/dd HH:mm:ss"の形式にformatした文字列。  <BR>
     * <BR>
     * (*1) 現在日時<BR>
     * TradingSystem.getSystemTimestamp() <BR>
     * @@roseuid 45B9A68F0116
     */
    private void createKeyHeader()
    {
        final String STR_METHOD_NAME = " createKeyHeader()";
        log.entering(STR_METHOD_NAME);

        String[] l_strKeyHeader = new String[1];

        l_strKeyHeader[0] =
            WEB3DateUtility.formatDate(GtlUtils.getSystemTimestamp(), "yyyy/MM/dd HH:mm:ss");

        this.setKeyHeader(l_strKeyHeader);

        log.exiting(STR_METHOD_NAME);
    }

    /**
     * (set注文種別)<BR>
     * 注文種別をセットする。<BR>
     * <BR>
     * １）カラムモデル取得<BR>
     *    this.getカラムモデル()にて、CSVカラムモデルを取得する。<BR>
     * <BR>
     *    [引数]<BR>
     *    項目ラベル： 入出金一覧ダウンロードCSV.注文種別ラベル<BR>
     * <BR>
     * ２）値セット<BR>
     *    this.set項目値()にて、値をセットする。<BR>
     * <BR>
     *    [引数]<BR>
     *    行番号： （引数）行番号<BR>
     *    カラム： １）で取得したカラムモデル<BR>
     *    値：<BR>
     * 　@　@(引数）注文種別 == "1002(入金注文)" 且つ、（引数）注文経路区分 == "9(HOST)"
     * の場合<BR>
     * 　@　@－管理者入出金一覧ダウンロードCSV.注文種別_SONAR入金表示。<BR>
     * <BR>
     * 　@　@(引数）注文種別 == "1002(入金注文)" 且つ、（引数）注文経路区分 ==
     * "D(入金連携)" の場合<BR>
     * 　@　@－管理者入出金一覧ダウンロードCSV.注文種別_バーチャル入金表示。<BR>
     * <BR>
     * 　@　@(引数）注文種別 == "1002(入金注文)" 且つ、（引数）.comデビット決済取引番号
     * is not null の場合<BR>
     * 　@　@－管理者入出金一覧ダウンロードCSV.注文種別_ネット入金表示。<BR>
     * <BR>
     * 　@　@(引数）注文種別 == "1008(振替(株先証拠金から預り金))" の場合<BR>
     * 　@　@－ 管理者入出金一覧ダウンロードCSV.注文種別_振替(株先証拠金から預り金)表示。 <BR>
     * <BR>
     * 　@　@(引数）注文種別 == "1012(為替保証金振替(為替保証金から預り金))" の場合<BR>
     * 　@　@－
     * 管理者入出金一覧ダウンロードCSV.注文種別_為替保証金振替(為替保証金から預り金)表示。 <BR>
     * <BR>
     * 　@　@(引数）注文種別 == "1018(その他振替注文(Xから預り金))" の場合<BR>
     * 　@　@－ 管理者入出金一覧ダウンロードCSV.注文種別_その他振替(Xから預り金)表示。 <BR>
     * <BR>
     * 　@　@(引数）注文種別 == "1001(出金注文)" の場合<BR>
     * 　@　@－ 管理者入出金一覧ダウンロードCSV.注文種別_出金表示。 <BR>
     * <BR>
     * 　@　@(引数）注文種別 == "1007(振替注文(預り金から株先証拠金))" の場合<BR>
     * 　@　@－ 管理者入出金一覧ダウンロードCSV.注文種別_振替(預り金から株先証拠金)表示。 <BR>
     * <BR>
     * 　@　@(引数）注文種別 == "1011(為替保証金振替注文(預り金から為替保証金))"
     * の場合<BR>
     * 　@　@－
     * 管理者入出金一覧ダウンロードCSV.注文種別_為替保証金振替(預り金から為替保証金)表示。 <BR>
     * <BR>
     * 　@　@(引数）注文種別 == "1017(その他振替注文(預り金からX))" の場合<BR>
     * 　@　@－ 管理者入出金一覧ダウンロードCSV.注文種別_その他振替(預り金からX)表示。 <BR>
     * @@param l_intLineNumber - (行番号)<BR>
     * 行番号<BR>
     * @@param l_strOrderType - (注文種別)<BR>
     * 注文種別<BR>
     * @@param l_strOrderRootDiv - (注文経路区分)<BR>
     * 注文経路区分<BR>
     * @@param l_strComdebitNumber - (.comデビット決済取引番号)<BR>
     * .comデビット決済取引番号<BR>
     * @@roseuid 45B9A6B0022F
     */
    public void setOrderType(int l_intLineNumber,
        String l_strOrderType, String l_strOrderRootDiv, String l_strComdebitNumber)
    {
        final String STR_METHOD_NAME = " setOrderType(int, String, String, String)";
        log.entering(STR_METHOD_NAME);

        //１）カラムモデル取得
        //   this.getカラムモデル()にて、CSVカラムモデルを取得する。
        //   [引数]
        //   項目ラベル： 入出金一覧ダウンロードCSV.注文種別ラベル
        WEB3GentradeCsvColumnModel l_gentradeCsvColumnModel =
            this.getColumnModel(WEB3AdminAioListDownloadCSV.ORDER_TYPE_LABEL);

        //２）値セット
        //   this.set項目値()にて、値をセットする。
        //   [引数]
        //   行番号： （引数）行番号
        //   カラム： １）で取得したカラムモデル
        //   値：
        String l_strValue = null;
        //　@　@(引数）注文種別 == "1002(入金注文)" 且つ、（引数）注文経路区分 == "9(HOST)" の場合
        //　@　@－管理者入出金一覧ダウンロードCSV.注文種別_SONAR入金表示。
        if (String.valueOf(OrderTypeEnum.CASH_IN.intValue()).equals(l_strOrderType)
            && WEB3OrderRootDivDef.HOST.equals(l_strOrderRootDiv))
        {
            l_strValue = WEB3AdminAioListDownloadCSV.SONAR_CASHIN;
        }
        //　@　@(引数）注文種別 == "1002(入金注文)" 且つ、（引数）注文経路区分 == "D(入金連携)" の場合
        //　@　@－管理者入出金一覧ダウンロードCSV.注文種別_バーチャル入金表示。
        else if (String.valueOf(OrderTypeEnum.CASH_IN.intValue()).equals(l_strOrderType)
            && WEB3OrderRootDivDef.CASH_IN_COOPERATION.equals(l_strOrderRootDiv))
        {
            l_strValue = WEB3AdminAioListDownloadCSV.VIRTUAL_CASHIN;
        }
        //　@　@(引数）注文種別 == "1002(入金注文)" 且つ、（引数）.comデビット決済取引番号 is not null の場合
        //　@　@－管理者入出金一覧ダウンロードCSV.注文種別_ネット入金表示。
        else if (String.valueOf(OrderTypeEnum.CASH_IN.intValue()).equals(l_strOrderType)
            && l_strComdebitNumber != null)
        {
            l_strValue = WEB3AdminAioListDownloadCSV.NET_CASHIN;
        }
        //　@　@(引数）注文種別 == "1008(振替(株先証拠金から預り金))" の場合
        //　@　@－ 管理者入出金一覧ダウンロードCSV.注文種別_振替(株先証拠金から預り金)表示。
        else if (String.valueOf(
            OrderTypeEnum.MARGIN_FROM_DEPOSIT_AMOUNT.intValue()).equals(l_strOrderType))
        {
            l_strValue = WEB3AdminAioListDownloadCSV.MARGIN_FROM_DEPOSIT_AMOUNT;
        }
        //　@　@(引数）注文種別 == "1012(為替保証金振替(為替保証金から預り金))" の場合
        //　@　@－ 管理者入出金一覧ダウンロードCSV.注文種別_為替保証金振替(為替保証金から預り金)表示。
        else if (String.valueOf(
            OrderTypeEnum.DEPOSIT_AMOUNT_FROM_FX_GUARANTEE.intValue()).equals(l_strOrderType))
        {
            l_strValue = WEB3AdminAioListDownloadCSV.DEPOSIT_AMOUNT_FROM_FX_GUARANTEE;
        }
        //　@　@(引数）注文種別 == "1018(その他振替注文(Xから預り金))" の場合
        //　@　@－ 管理者入出金一覧ダウンロードCSV.注文種別_その他振替(Xから預り金)表示。
        else if (String.valueOf(OrderTypeEnum.FROM_OTHER_TRANSFER.intValue()).equals(l_strOrderType))
        {
            l_strValue = WEB3AdminAioListDownloadCSV.FROM_OTHER_TRANSFER;
        }
        //　@　@(引数）注文種別 == "1001(出金注文)" の場合
        //　@　@－ 管理者入出金一覧ダウンロードCSV.注文種別_出金表示。
        else if (String.valueOf(OrderTypeEnum.CASH_OUT.intValue()).equals(l_strOrderType))
        {
            l_strValue = WEB3AdminAioListDownloadCSV.CASHOUT;
        }
        //　@　@(引数）注文種別 == "1007(振替注文(預り金から株先証拠金))" の場合
        //　@　@－ 管理者入出金一覧ダウンロードCSV.注文種別_振替(預り金から株先証拠金)表示。
        else if (String.valueOf(
            OrderTypeEnum.FROM_DEPOSIT_AMOUNT_MARGIN.intValue()).equals(l_strOrderType))
        {
            l_strValue = WEB3AdminAioListDownloadCSV.FROM_DEPOSIT_AMOUNT_MARGIN;
        }
        //　@　@(引数）注文種別 == "1011(為替保証金振替注文(預り金から為替保証金))" の場合
        //　@　@－ 管理者入出金一覧ダウンロードCSV.注文種別_為替保証金振替(預り金から為替保証金)表示。
        else if (String.valueOf(
            OrderTypeEnum.FX_GUARANTEE_FROM_DEPOSIT_AMOUNT.intValue()).equals(l_strOrderType))
        {
            l_strValue = WEB3AdminAioListDownloadCSV.FX_GUARANTEE_FROM_DEPOSIT_AMOUNT;
        }
        //　@　@(引数）注文種別 == "1017(その他振替注文(預り金からX))" の場合
        //　@　@－ 管理者入出金一覧ダウンロードCSV.注文種別_その他振替(預り金からX)表示。
        else if (String.valueOf(OrderTypeEnum.TO_OTHER_TRANSFER.intValue()).equals(l_strOrderType))
        {
            l_strValue = WEB3AdminAioListDownloadCSV.TO_OTHER_TRANSFER;
        }

        this.setValue(l_intLineNumber, l_gentradeCsvColumnModel, l_strValue);
        log.exiting(STR_METHOD_NAME);
    }

    /**
     * (set受渡日)<BR>
     * 受渡日をセットする。<BR>
     * <BR>
     * １）カラムモデル取得<BR>
     *    this.getカラムモデル()にて、CSVカラムモデルを取得する。<BR>
     * <BR>
     *    [引数]<BR>
     *    項目ラベル： 入出金一覧ダウンロードCSV.受渡日ラベル<BR>
     * <BR>
     * ２）値セット<BR>
     *    this.set項目値()にて、値をセットする。<BR>
     * <BR>
     *    [引数]<BR>
     *    行番号： （引数）行番号<BR>
     *    カラム： １）で取得したカラムモデル<BR>
     *    値： （引数）受渡日<BR>
     * @@param l_intLineNumber - (行番号)<BR>
     * 行番号<BR>
     * @@param l_datDeliveryDate - (受渡日)<BR>
     * 受渡日<BR>
     * @@roseuid 45B9A6BA027D
     */
    public void setDeliveryDate(int l_intLineNumber, Date l_datDeliveryDate)
    {
        final String STR_METHOD_NAME = "setDeliveryDate(int, Date)";
        log.entering(STR_METHOD_NAME);

        //１）カラムモデル取得
        //this.getカラムモデル()にて、CSVカラムモデルを取得する。
        //[引数]
        //項目ラベル： 入出金一覧ダウンロードCSV.受渡日ラベル
        WEB3GentradeCsvColumnModel l_gentradeCsvColumnModel =
            this.getColumnModel(WEB3AdminAioListDownloadCSV.DELIVERY_DATE_LABEL);

        //２）値セット
        //this.set項目値()にて、値をセットする。
        //[引数]
        //行番号： 引数.行番号
        //カラム： １）で取得したカラムモデル
        //値： （引数）受渡日
        this.setValue(l_intLineNumber, l_gentradeCsvColumnModel, l_datDeliveryDate);

        log.exiting(STR_METHOD_NAME);
    }

    /**
     * (set部店コード)<BR>
     * 部店コードをセットする。 <BR>
     * <BR>
     * １）　@カラムモデル取得 <BR>
     * 　@this.getカラムモデル()にてCSVカラムモデルを取得する。 <BR>
     * <BR>
     * 　@[getカラムモデル()に指定する引数] <BR>
     * 　@項目ラベル：　@入出金一覧ダウンロードCSV.部店コードラベル <BR>
     * <BR>
     * ２）　@値セット <BR>
     * 　@this.set項目値()にて項目値をセットする。 <BR>
     * <BR>
     * 　@[set項目値()に指定する引数] <BR>
     * 　@行番号：　@（引数）行番号 <BR>
     * 　@カラム：　@１）で取得したカラムモデル <BR>
     * 　@値：　@（引数）部店コード<BR>
     * <BR>
     * @@param l_intLineNumber - (行番号)<BR>
     * 行番号<BR>
     * @@param l_strBranchCode - (部店コード)<BR>
     * 部店コード<BR>
     * @@roseuid 45B9A6C50164
     */
    public void setBranchCode(int l_intLineNumber, String l_strBranchCode)
    {
        final String STR_METHOD_NAME = "setBranchCode(int, String)";
        log.entering(STR_METHOD_NAME);

        //１）カラムモデル取得
        //this.getカラムモデル()にて、CSVカラムモデルを取得する。
        //[引数]
        //項目ラベル：　@入出金一覧ダウンロードCSV.部店コードラベル
        WEB3GentradeCsvColumnModel l_gentradeCsvColumnModel =
            this.getColumnModel(WEB3AdminAioListDownloadCSV.BRANCH_CODE_LABEL);

        //２）値セット
        //this.set項目値()にて、値をセットする。
        //[引数]
        //行番号： 引数.行番号
        //カラム： １）で取得したカラムモデル
        //値：　@（引数）部店コード
        this.setValue(l_intLineNumber, l_gentradeCsvColumnModel, l_strBranchCode);

        log.exiting(STR_METHOD_NAME);
    }

    /**
     * (set顧客コード)<BR>
     * 顧客コードをセットする。  <BR>
     * <BR>
     * １）　@カラムモデル取得 <BR>
     * 　@this.getカラムモデル()にてCSVカラムモデルを取得する。 <BR>
     * <BR>
     * 　@[getカラムモデル()に指定する引数] <BR>
     * 　@項目ラベル：　@入出金一覧ダウンロードCSV.顧客コードラベル <BR>
     * <BR>
     * ２）　@値セット <BR>
     * 　@this.set項目値()にて項目値をセットする。 <BR>
     * <BR>
     * 　@[set項目値()に指定する引数] <BR>
     * 　@行番号：　@（引数）行番号 <BR>
     * 　@カラム：　@１）で取得したカラムモデル <BR>
     * 　@値：　@（引数）顧客コード<BR>
     * <BR>
     * @@param l_intLineNumber - (行番号)<BR>
     * 行番号<BR>
     * @@param l_strAccountCode - (口座コード)<BR>
     * 口座コード<BR>
     * @@roseuid 45BEFDF300D3
     */
    public void setAccountCode(int l_intLineNumber, String l_strAccountCode)
    {
        final String STR_METHOD_NAME = "setAccountCode(int, String)";
        log.entering(STR_METHOD_NAME);

        //１）カラムモデル取得
        //this.getカラムモデル()にて、CSVカラムモデルを取得する。
        //[引数]
        //項目ラベル：　@入出金一覧ダウンロードCSV.顧客コードラベル
        WEB3GentradeCsvColumnModel l_gentradeCsvColumnModel =
            this.getColumnModel(WEB3AdminAioListDownloadCSV.ACCOUNT_CODE_LABEL);

        //２）値セット
        //this.set項目値()にて、値をセットする。
        //[引数]
        //行番号： 引数.行番号
        //カラム： １）で取得したカラムモデル
        //値：　@（引数）顧客コード
        this.setValue(l_intLineNumber, l_gentradeCsvColumnModel, l_strAccountCode);

        log.exiting(STR_METHOD_NAME);
    }

    /**
     * (set顧客名)<BR>
     * 顧客名をセットする。<BR>
     * <BR>
     * １）　@カラムモデル取得 <BR>
     * 　@this.getカラムモデル()にてCSVカラムモデルを取得する。 <BR>
     * <BR>
     * 　@[getカラムモデル()に指定する引数] <BR>
     * 　@項目ラベル：　@入出金一覧ダウンロードCSV.顧客名ラベル <BR>
     * <BR>
     * ２）　@値セット <BR>
     * 　@this.set項目値()にて項目値をセットする。 <BR>
     * <BR>
     * 　@[set項目値()に指定する引数] <BR>
     * 　@行番号：　@（引数）行番号 <BR>
     * 　@カラム：　@１）で取得したカラムモデル
     * 　@値：　@（引数）顧客名<BR>
     * <BR>
     * @@param l_intLineNumber - (行番号)<BR>
     * 行番号<BR>
     * @@param l_strAccountName - (顧客名)<BR>
     * 顧客名<BR>
     * @@roseuid 45B9A6D0000C
     */
    public void setAccountName(int l_intLineNumber, String l_strAccountName)
    {
        final String STR_METHOD_NAME = "setAccountName(int, String)";
        log.entering(STR_METHOD_NAME);

        //１）カラムモデル取得
        //this.getカラムモデル()にて、CSVカラムモデルを取得する。
        //[引数]
        //項目ラベル：　@入出金一覧ダウンロードCSV.顧客名ラベル
        WEB3GentradeCsvColumnModel l_gentradeCsvColumnModel =
            this.getColumnModel(WEB3AdminAioListDownloadCSV.ACCOUNT_NAME_LABEL);

        //２）値セット
        //this.set項目値()にて、値をセットする。
        //[引数]
        //行番号： 引数.行番号
        //カラム： １）で取得したカラムモデル
        //値：　@（引数）顧客名
        this.setValue(l_intLineNumber, l_gentradeCsvColumnModel, l_strAccountName);

        log.exiting(STR_METHOD_NAME);
    }

    /**
     * (set注文日時)<BR>
     * 注文日時をセットする。<BR>
     * <BR>
     * １）カラムモデル取得<BR>
     *    this.getカラムモデル()にて、CSVカラムモデルを取得する。<BR>
     * <BR>
     *    [引数]<BR>
     *    項目ラベル： 入出金一覧ダウンロードCSV.注文日時ラベル<BR>
     * <BR>
     * ２）値セット<BR>
     *    this.set項目値()にて、値をセットする。<BR>
     * <BR>
     *    [引数]<BR>
     *    行番号： （引数）行番号<BR>
     *    カラム： １）で取得したカラムモデル<BR>
     *    値： （引数）受注日時<BR>
     * @@param l_intLineNumber - (行番号)<BR>
     * 行番号<BR>
     * @@param l_datReceivedDate - (受注日時)<BR>
     * 受注日時<BR>
     * @@roseuid 45B9AA7301E6
     */
    public void setOrderDate(int l_intLineNumber, Date l_datReceivedDate)
    {
        final String STR_METHOD_NAME = "setOrderDate(int, Date)";
        log.entering(STR_METHOD_NAME);

        //１）カラムモデル取得
        //this.getカラムモデル()にて、CSVカラムモデルを取得する。
        //[引数]
        //項目ラベル： 入出金一覧ダウンロードCSV.注文日時ラベル
        WEB3GentradeCsvColumnModel l_gentradeCsvColumnModel =
            this.getColumnModel(WEB3AdminAioListDownloadCSV.ORDER_DATE_LABEL);

        //２）値セット
        //this.set項目値()にて、値をセットする。
        //[引数]
        //行番号： 引数.行番号
        //カラム： １）で取得したカラムモデル
        //値： （引数）受注日時
        this.setValue(l_intLineNumber, l_gentradeCsvColumnModel, l_datReceivedDate);

        log.exiting(STR_METHOD_NAME);
    }

    /**
     * (setステータス)<BR>
     * ステータスをセットする。<BR>
     * <BR>
     * １）カラムモデル取得<BR>
     *    this.getカラムモデル()にて、CSVカラムモデルを取得する。<BR>
     * <BR>
     *    [引数]<BR>
     *    項目ラベル： 入出金一覧ダウンロードCSV.ステータスラベル<BR>
     * <BR>
     * ２）値セット<BR>
     *    this.set項目値()にて、値をセットする。<BR>
     * <BR>
     *    [引数]<BR>
     *    行番号： （引数）行番号<BR>
     *    カラム： １）で取得したカラムモデル<BR>
     *   値： 　@　@<BR>
     * 　@　@（引数.ステータス == "3(発注済)"、且つ、引数.注文取消区分
     * = "0(初期値)"）の場合<BR>
     * 　@　@－管理者入出金一覧ダウンロードCSV.ステータス_完了表示。<BR>
     * <BR>
     * 　@　@（引数.ステータス == ("1(受付済)"又は"2(発注中)")、且つ、引数.注文取消区分 =
     * "0(初期値)"）の場合<BR>
     * 　@　@－管理者入出金一覧ダウンロードCSV.ステータス_未処理表示。<BR>
     * <BR>
     *  　@　@（引数.ステータス == "6(発注失敗)"）の場合<BR>
     * 　@　@－管理者入出金一覧ダウンロードCSV.ステータス_エラー表示。<BR>
     * <BR>
     * @@param l_intLineNumber - (行番号)<BR>
     * 行番号<BR>
     * @@param l_strStatus - (ステータス)<BR>
     * ステータス<BR>
     * @@param l_strOrderCancelDiv - (注文取消区分)<BR>
     * 注文取消区分<BR>
     * @@roseuid 45B9AA8500CD
     */
    public void setStatus(int l_intLineNumber, String l_strStatus, String l_strOrderCancelDiv)
    {
        final String STR_METHOD_NAME = "setStatus(int, String, String)";
        log.entering(STR_METHOD_NAME);

        //１）カラムモデル取得
        //this.getカラムモデル()にて、CSVカラムモデルを取得する。
        //[引数]
        //項目ラベル： 入出金一覧ダウンロードCSV.ステータスラベル
        WEB3GentradeCsvColumnModel l_gentradeCsvColumnModel =
            this.getColumnModel(WEB3AdminAioListDownloadCSV.STATUS_LABEL);

        //２）値セット
        //this.set項目値()にて、値をセットする。
        //[引数]
        //行番号： 引数.行番号
        //カラム： １）で取得したカラムモデル
        //値： 引数.値
        String l_strValue = null;

        //（引数.ステータス == "3(発注済)、且つ、引数.注文取消区分
        // = "0(初期値)"）の場合
        //－管理者入出金一覧ダウンロードCSV.ステータス_完了表示。
        if (String.valueOf(OrderStatusEnum.ORDERED.intValue()).equals(l_strStatus)
            && WEB3ModifyCancelTypeDef.INITIAL_VALUE.equals(l_strOrderCancelDiv))
        {
            l_strValue = WEB3AdminAioListDownloadCSV.ORDER_STATUS_COMPLETE;
        }

        //（引数.ステータス == ("1(受付済)"又は"2(発注中)")、且つ、引数.注文取消区分 = "0(初期値)"）の場合
        //－管理者入出金一覧ダウンロードCSV.ステータス_未処理表示。
        else if ((String.valueOf(OrderStatusEnum.ACCEPTED.intValue()).equals(l_strStatus)
            || String.valueOf(OrderStatusEnum.ORDERING.intValue()).equals(l_strStatus))
            && WEB3ModifyCancelTypeDef.INITIAL_VALUE.equals(l_strOrderCancelDiv))
        {
            l_strValue = WEB3AdminAioListDownloadCSV.ORDER_STATUS_NOT_TRANSACTION;
        }

        //（引数.ステータス == "6(発注失敗)"）の場合
        //－管理者入出金一覧ダウンロードCSV.ステータス_エラー表示。
        else if (String.valueOf(OrderStatusEnum.NOT_ORDERED.intValue()).equals(l_strStatus))
        {
            l_strValue = WEB3AdminAioListDownloadCSV.ORDER_STATUS_ACCEPTED_ERROR;
        }

        this.setValue(l_intLineNumber, l_gentradeCsvColumnModel, l_strValue);

        log.exiting(STR_METHOD_NAME);
    }

    /**
     * (set金額)<BR>
     * 入金金額、出金金額をセットする。<BR>
     * <BR>
     * １）(入金)カラムモデル取得<BR>
     *    this.getカラムモデル()にて、CSVカラムモデルを取得する。<BR>
     * <BR>
     *    [引数]<BR>
     *    項目ラベル： 入出金一覧ダウンロードCSV.入金金額ラベル<BR>
     * <BR>
     * ２）(入金)値セット<BR>
     *    this.set項目値()にて、値をセットする。<BR>
     * <BR>
     *    [引数]<BR>
     *    行番号： （引数）行番号<BR>
     *    カラム： １）で取得したカラムモデル<BR>
     *    値：    <BR>
     * 　@　@（引数）入出金区分 == "0(入金)"の場合<BR>
     * 　@　@－（引数）金額<BR>
     * <BR>
     * ３）(出金)カラムモデル取得<BR>
     *    this.getカラムモデル()にて、CSVカラムモデルを取得する。<BR>
     * <BR>
     *    [引数]<BR>
     *    項目ラベル： 入出金一覧ダウンロードCSV.出金金額ラベル<BR>
     * <BR>
     * ４）(出金)値セット<BR>
     *    this.set項目値()にて、値をセットする。<BR>
     * <BR>
     *    [引数]<BR>
     *    行番号： （引数）行番号<BR>
     *    カラム： ３）で取得したカラムモデル<BR>
     *      値：    <BR>
     *  　@　@（引数）入出金区分 == "1(出金)"の場合<BR>
     * 　@　@－（引数）金額<BR>
     * @@param l_intLineNumber - (行番号)<BR>
     * 行番号<BR>
     * @@param l_strCash - (金額)<BR>
     * 金額<BR>
     * @@param l_strAioDiv - (入出金区分)<BR>
     * 入出金区分<BR>
     * <BR>
     * 0：　@入金<BR>
     * 1：　@出金<BR>
     * @@roseuid 45B9AADA03BC
     */
    public void setCash(int l_intLineNumber, String l_strCash, String l_strAioDiv)
    {
        final String STR_METHOD_NAME = "setCash(int, String, String)";
        log.entering(STR_METHOD_NAME);

        WEB3GentradeCsvColumnModel l_gentradeCsvColumnModel = null;

        //(入金)カラムモデル取得
        //   this.getカラムモデル()にて、CSVカラムモデルを取得する。
        //   [引数]
        //   項目ラベル： 入出金一覧ダウンロードCSV.入金金額ラベル
        if (WEB3AdminAioDivDef.CASH_IN.equals(l_strAioDiv))
        {
            l_gentradeCsvColumnModel =
                this.getColumnModel(WEB3AdminAioListDownloadCSV.CASHINAMT_LABEL);
        }

        //(出金)カラムモデル取得
        //   this.getカラムモデル()にて、CSVカラムモデルを取得する。
        //   [引数]
        //   項目ラベル： 入出金一覧ダウンロードCSV.出金金額ラベル
        else if(WEB3AdminAioDivDef.CASH_OUT.equals(l_strAioDiv))
        {
            l_gentradeCsvColumnModel =
                this.getColumnModel(WEB3AdminAioListDownloadCSV.CASHOUTAMT_LABEL);
        }

        //値セット
        this.setValue(l_intLineNumber, l_gentradeCsvColumnModel, l_strCash);

        log.exiting(STR_METHOD_NAME);
    }

    /**
     * (set入力経路)<BR>
     * 入力経路をセットする。<BR>
     * <BR>
     * １）カラムモデル取得<BR>
     *    this.getカラムモデル()にて、CSVカラムモデルを取得する。<BR>
     * <BR>
     *    [引数]<BR>
     *    項目ラベル： 入出金一覧ダウンロードCSV.入力経路ラベル<BR>
     * <BR>
     * ２）値セット<BR>
     *    this.set項目値()にて、値をセットする。<BR>
     * <BR>
     *    [引数]<BR>
     *    行番号： （引数）行番号<BR>
     *    カラム： １）で取得したカラムモデル<BR>
     *  値：
     *　@　@（引数.入力経路 == "1(コールセンター)"）の場合
     *　@　@－ 管理者入出金一覧ダウンロードCSV.入力経路_コール表示。
     *　@　@（引数.入力経路 == "9(HOST)")の場合
     *　@　@－ 管理者入出金一覧ダウンロードCSV.入力経路_SONAR表示。
     *　@　@（引数.入力経路 == "A(管理者)"）の場合
     *　@　@－ 管理者入出金一覧ダウンロードCSV.入力経路_管理者表示。
     *　@　@（上記以外、且つ、引数.入力経路 != null）の場合
     *　@　@－ 管理者入出金一覧ダウンロードCSV.入力経路_WEB表示。
     * @@param l_intLineNumber - (行番号)<BR>
     * 行番号<BR>
     * @@param l_strInputRoot - (入力経路)<BR>
     * 入力経路<BR>
     * @@roseuid 45B9AB2E011C
     */
    public void setInputRoot(int l_intLineNumber, String l_strInputRoot)
    {
        final String STR_METHOD_NAME = "setInputRoot(int, String)";
        log.entering(STR_METHOD_NAME);

        //１）カラムモデル取得
        //this.getカラムモデル()にて、CSVカラムモデルを取得する。
        //[引数]
        //項目ラベル： 入出金一覧ダウンロードCSV.入力経路ラベル
        WEB3GentradeCsvColumnModel l_gentradeCsvColumnModel =
            this.getColumnModel(WEB3AdminAioListDownloadCSV.INPUT_ROOT_LABEL);

        //２）値セット
        //this.set項目値()にて、値をセットする。
        //[引数]
        //行番号： 引数.行番号
        //カラム： １）で取得したカラムモデル
        //値：
        String l_strValue = null;
		//　@（引数.入力経路 == "1(コールセンター)"）の場合
		//　@－ 管理者入出金一覧ダウンロードCSV.入力経路_コール表示。
		if (WEB3OrderRootDivDef.CALLCENTER.equals(l_strInputRoot))
		{
		    l_strValue = WEB3AdminAioListDownloadCSV.INPUT_ROOT_CALL;
		}
        //　@（引数.入力経路 == "9(HOST)")の場合
		//　@－ 管理者入出金一覧ダウンロードCSV.入力経路_SONAR表示。
        else if (WEB3OrderRootDivDef.HOST.equals(l_strInputRoot))
        {
            l_strValue = WEB3AdminAioListDownloadCSV.INPUT_ROOT_SONAR;
        }
		//　@（引数.入力経路 == "A(管理者)"）の場合
		//　@－ 管理者入出金一覧ダウンロードCSV.入力経路_管理者表示。
        else if (WEB3OrderRootDivDef.ADMIN.equals(l_strInputRoot))
        {
            l_strValue = WEB3AdminAioListDownloadCSV.INPUT_ROOT_ADMIN;
        }        
		//　@（上記以外、且つ、引数.入力経路 != null）の場合
		//　@－ 管理者入出金一覧ダウンロードCSV.入力経路_WEB表示。
        else if (WEB3StringTypeUtility.isNotEmpty(l_strInputRoot))
        {
            l_strValue = WEB3AdminAioListDownloadCSV.INPUT_ROOT_WEB;
        }  

		this.setValue(l_intLineNumber, l_gentradeCsvColumnModel, l_strValue);
        
        log.exiting(STR_METHOD_NAME);
    }

    /**
     * (set入力者)<BR>
     * 入力者をセットする。<BR>
     * <BR>
     * １）カラムモデル取得<BR>
     *    this.getカラムモデル()にて、CSVカラムモデルを取得する。<BR>
     * <BR>
     *    [引数]<BR>
     *    項目ラベル： 入出金一覧ダウンロードCSV.入力者ラベル<BR>
     * <BR>
     * ２）値セット<BR>
     *    this.set項目値()にて、値をセットする。<BR>
     * <BR>
     *    [引数]<BR>
     *    行番号： （引数）行番号<BR>
     *    カラム： １）で取得したカラムモデル<BR>
     *   値： （引数）入力者<BR>
     * @@param l_intLineNumber - (行番号)<BR>
     * 行番号<BR>
     * @@param l_strTrader - (入力者)<BR>
     * 入力者<BR>
     * @@roseuid 45B9AB56010D
     */
    public void setTrader(int l_intLineNumber, String l_strTrader)
    {
        final String STR_METHOD_NAME = "setTrader(int, String)";
        log.entering(STR_METHOD_NAME);

        //１）カラムモデル取得
        //this.getカラムモデル()にて、CSVカラムモデルを取得する。
        //[引数]
        //項目ラベル： 入出金一覧ダウンロードCSV.入力者ラベル
        WEB3GentradeCsvColumnModel l_gentradeCsvColumnModel =
            this.getColumnModel(WEB3AdminAioListDownloadCSV.TRADER_LABEL);

        //２）値セット
        //this.set項目値()にて、値をセットする。
        //[引数]
        //行番号： 引数.行番号
        //カラム： １）で取得したカラムモデル
        //値： 引数.値
        this.setValue(l_intLineNumber, l_gentradeCsvColumnModel, l_strTrader);

        log.exiting(STR_METHOD_NAME);
    }

    /**
     * (set口座情報)<BR>
     * 口座情報をセットする。<BR>
     * <BR>
     * １）カラムモデル取得<BR>
     *    this.getカラムモデル()にて、CSVカラムモデルを取得する。<BR>
     * <BR>
     *    [引数]<BR>
     *    項目ラベル： 入出金一覧ダウンロードCSV.口座情報ラベル<BR>
     * <BR>
     * ２）値セット<BR>
     *    this.set項目値()にて、値をセットする。<BR>
     * <BR>
     *    [引数]<BR>
     *    行番号： （引数）行番号<BR>
     *    カラム： １）で取得したカラムモデル<BR>
     *   値：
     * （引数）銀行コード　@+　@（引数）支店コード　@+　@口座種別(*1)　@+　@（引数）口座番号<
     * BR>
     * <BR>
     * 　@(*1)口座種別を以下の通りセットする<BR>
     * 　@・（引数）口座種別 ==
     * "1"の場合、管理者入出金一覧ダウンロードCSV.預金区分_普通預金表示。<BR>
     * 　@・（引数）口座種別 ==
     * "2"の場合、管理者入出金一覧ダウンロードCSV.預金区分_当座預金表示。<BR>
     * 　@・（引数）口座種別 ==
     * "3"の場合、管理者入出金一覧ダウンロードCSV.預金区分_その他表示。<BR>
     * 　@・（引数）口座種別 ==
     * "4"の場合、管理者入出金一覧ダウンロードCSV.預金区分_貯蓄預金表示。<BR>
     * 　@※上記以外はnullをセットする<BR>
     * @@param l_intLineNumber - (行番号)<BR>
     * 行番号<BR>
     * @@param l_strBankCode - (銀行コード)<BR>
     * 銀行コード<BR>
     * @@param l_strBankBranchCode - (支店コード)<BR>
     * 支店コード<BR>
     * @@param l_strAccountType - (口座種別)<BR>
     * 口座種別<BR>
     * @@param l_strAccountCode - (口座番号)<BR>
     * 口座番号<BR>
     * @@roseuid 45B9AB79014B
     */
    public void setAccountInfo(int l_intLineNumber, String l_strBankCode,
        String l_strBankBranchCode, String l_strAccountType, String l_strAccountCode)
    {
        final String STR_METHOD_NAME = "setAccountInfo(int, String, String, String, String)";
        log.entering(STR_METHOD_NAME);

        //１）カラムモデル取得
        //   this.getカラムモデル()にて、CSVカラムモデルを取得する。
        //   [引数]
        //   項目ラベル： 入出金一覧ダウンロードCSV.口座情報ラベル
        WEB3GentradeCsvColumnModel l_gentradeCsvColumnModel =
            this.getColumnModel(WEB3AdminAioListDownloadCSV.ACCOUNT_INFO_LABEL);

        //　@(*1)口座種別を以下の通りセットする
        //　@・（引数）口座種別 == "1"の場合、管理者入出金一覧ダウンロードCSV.預金区分_普通預金表示。
        //　@・（引数）口座種別 == "2"の場合、管理者入出金一覧ダウンロードCSV.預金区分_当座預金表示。
        //　@・（引数）口座種別 == "3"の場合、管理者入出金一覧ダウンロードCSV.預金区分_その他表示。
        //　@・（引数）口座種別 == "4"の場合、管理者入出金一覧ダウンロードCSV.預金区分_貯蓄預金表示。
        //　@※上記以外はnullをセットする
        String l_strAccountTypeDeposit = null;
        StringBuffer l_strValue = new StringBuffer("");
        if (WEB3FinSaveDivDef.GENERAL_FIN_SAVE.equals(l_strAccountType))
        {
            l_strAccountTypeDeposit = WEB3AdminAioListDownloadCSV.DEPOSIT_DIV_GENERAL;
        }
        else if (WEB3FinSaveDivDef.ACCOUNT_FIN_SAVE.equals(l_strAccountType))
        {
            l_strAccountTypeDeposit = WEB3AdminAioListDownloadCSV.DEPOSIT_DIV_ACCOUNT;
        }
        else if (WEB3FinSaveDivDef.OTHER.equals(l_strAccountType))
        {
            l_strAccountTypeDeposit = WEB3AdminAioListDownloadCSV.DEPOSIT_DIV_OTHER;
        }
        else if (WEB3FinSaveDivDef.SAVING_FIN_SAVE.equals(l_strAccountType))
        {
            l_strAccountTypeDeposit = WEB3AdminAioListDownloadCSV.DEPOSIT_DIV_SAVING;
        }
        else
        {
            l_strAccountTypeDeposit = "";
        }

        //２）値セット
        //   this.set項目値()にて、値をセットする。
        //   [引数]
        //   行番号： （引数）行番号
        //   カラム： １）で取得したカラムモデル
        //  値： （引数）銀行コード　@+　@（引数）支店コード　@+　@口座種別(*1)　@+　@（引数）口座番号
        if (l_strBankCode != null)
        {
            l_strValue.append(l_strBankCode);
        }
        if (l_strBankBranchCode != null)
        {
            l_strValue.append(l_strBankBranchCode);
        }
        if ( !"".equals(l_strAccountTypeDeposit))
        {
            l_strValue.append(l_strAccountTypeDeposit);
        }
        if (l_strAccountCode != null)
        {
            l_strValue.append(l_strAccountCode);
        }

        this.setValue(l_intLineNumber, l_gentradeCsvColumnModel, l_strValue.toString());

        log.exiting(STR_METHOD_NAME);
    }

    /**
     * (set口座情報)<BR>
     * 口座情報をセットする。<BR>
     * <BR>
     * １）カラムモデル取得<BR>
     * this.getカラムモデル()にて、CSVカラムモデルを取得する。<BR>
     * <BR>
     * [引数]<BR>
     * 項目ラベル： 入出金一覧ダウンロードCSV.口座情報ラベル<BR>
     * <BR>
     * ２）値セット<BR>
     * this.set項目値()にて、値をセットする。<BR>
     * <BR>
     * [引数]<BR>
     * 行番号： （引数）行番号<BR>
     * カラム： １）で取得したカラムモデル<BR>
     * 値："大証FX"<BR>
     * @@param l_intLineNumber - (行番号)<BR>
     * 行番号<BR>
     */
    public void setAccountInfo(int l_intLineNumber)
    {
        final String STR_METHOD_NAME = "setAccountInfo(int)";
        log.entering(STR_METHOD_NAME);

        //１）カラムモデル取得
        //this.getカラムモデル()にて、CSVカラムモデルを取得する。
        //[引数]
        //項目ラベル： 入出金一覧ダウンロードCSV.口座情報ラベル
        WEB3GentradeCsvColumnModel l_gentradeCsvColumnModel =
            this.getColumnModel(WEB3AdminAioListDownloadCSV.ACCOUNT_INFO_LABEL);

        //２）値セット
        //this.set項目値()にて、値をセットする。
        //[引数]
        //行番号： （引数）行番号
        //カラム： １）で取得したカラムモデル
        //値："大証FX"
        this.setValue(
            l_intLineNumber,
            l_gentradeCsvColumnModel,
            WEB3AIOAccountTypeValueDef.OSAKA_FX);

        log.exiting(STR_METHOD_NAME);
    }
}
@
