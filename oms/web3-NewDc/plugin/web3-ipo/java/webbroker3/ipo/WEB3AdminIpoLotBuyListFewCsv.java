head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.15.05.42.41;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	6f44d7efa104045;
filename	WEB3AdminIpoLotBuyListFewCsv.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : 抽選結果・購入申込状況FewCSV(WEB3AdminIpoLotBuyListFewCsv.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2006/11/09 何文敏 (中訊) 新規作成 仕様変更No.162

*/
package webbroker3.ipo;

import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.Date;

import com.fitechlabs.xtrade.kernel.boot.Services;
import com.fitechlabs.xtrade.plugin.tc.gentrade.Branch;
import com.fitechlabs.xtrade.plugin.tc.gentrade.FinApp;
import com.fitechlabs.xtrade.plugin.tc.gentrade.MainAccount;
import com.fitechlabs.xtrade.plugin.tc.gentrade.NotFoundException;
import com.fitechlabs.xtrade.plugin.tc.gentrade.TradingSystem;
import com.fitechlabs.xtrade.plugin.tc.gentrade.data.MainAccountRow;

import webbroker3.common.WEB3BaseException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.common.WEB3SystemLayerException;
import webbroker3.common.define.WEB3IpoOrderAcceptStatusDef;
import webbroker3.common.define.WEB3OfferingDivDef;
import webbroker3.gentrade.WEB3GentradeCsvColumnModel;
import webbroker3.gentrade.WEB3GentradeCsvDataModel;
import webbroker3.ipo.data.IpoProductRow;
import webbroker3.ipo.define.WEB3LotResultDivDef;
import webbroker3.util.WEB3DateUtility;
import webbroker3.util.WEB3LogUtility;
import webbroker3.util.WEB3StringTypeUtility;

/**
 * (抽選結果・購入申込状況FewCSV)<BR>
 * 抽選結果・購入申込状況ダウンロードで作成するCSVファ@イルデータクラス<BR>
 * （扱者コード、信用区分、公開価格、抽選番号なし）<BR>
 *
 * @@author 何文敏
 * @@version 1.0
 */

public class WEB3AdminIpoLotBuyListFewCsv extends WEB3GentradeCsvDataModel
{
    /**
     * ログ出力オブジェクト。<BR>
     */
    private static WEB3LogUtility log =
        WEB3LogUtility.getInstance(WEB3AdminIpoLotBuyListFewCsv.class);

    /**
     * (部店コードラベル)<BR>
     * 定数定義プロパティ　@”部店コード”<BR>
     */
    public static final String BRANCH_CODE_LABEL = "部店コード";

    /**
     * (顧客コードラベル)<BR>
     * 定数定義プロパティ　@”顧客コード”<BR>
     */
    public static final String ACCOUNT_CODE_LABEL = "顧客コード";

    /**
     * (顧客名ラベル)<BR>
     * 定数定義プロパティ　@”顧客名”<BR>
     */
    public static final String  ACCOUNT_NAME_LABEL = "顧客名";

    /**
     * (抽選結果ラベル)<BR>
     * 定数定義プロパティ　@”抽選結果”<BR>
     */
    public static final String LOT_RESULT_LABEL = "抽選結果";

    /**
     * (当選数量ラベル)<BR>
     * 定数定義プロパティ　@”当選数量”<BR>
     */
    public static final String ELECTED_QUANTITY_LABEL = "当選数量";

    /**
     * (購入申込数量ラベル)<BR>
     * 定数定義プロパティ　@”購入申込数量”<BR>
     */
    public static final String APPLICATION_QUANTITY_LABEL = "購入申込数量";

    /**
     * (購入申込／辞退日時ラベル)<BR>
     * 定数定義プロパティ　@”購入申込／辞退日時”<BR>
     */
    public static final String OFFERING_TIMESTAMP_LABEL = "購入申込／辞退日時";

    /**
     * (購入申込状態ラベル)<BR>
     * 定数定義プロパティ　@”購入申込状態”<BR>
     */
    public static final String OFFER_STATE_LABEL = "購入申込状態";

    /**
     * (受付状態ラベル)<BR>
     * 定数定義プロパティ　@”受付状態”<BR>
     */
    public static final String ACCEPT_STATUS_LABEL = "受付状態";

    /**
     * (優先順位ラベル)<BR>
     * 定数定義プロパティ　@”優先順位”<BR>
     */
    public static final String SUBSTITUTE_PRIORITY = "優先順位";

    /**
     * (抽選結果_当選表示)<BR>
     * 定数定義プロパティ　@抽選結果_当選表示<BR>
     */
    public static final String LOT_RESULT_PRIZE_INDICATION = "当選";

    /**
     * (抽選結果_補欠表示)<BR>
     * 定数定義プロパティ　@抽選結果_補欠表示<BR>
     */
    public static final String LOT_RESULT_WAITING_INDICATION = "補欠";

    /**
     * (抽選結果_補欠当選表示)<BR>
     * 定数定義プロパティ　@抽選結果_補欠当選表示<BR>
     */
    public static final String LOT_RESULT_WAITING_PRIZE_INDICATION = "補欠当選";

    /**
     * (抽選結果_補欠落選表示)<BR>
     * 定数定義プロパティ　@抽選結果_補欠落選表示<BR>
     */
    public static final String LOT_RESULT_WAITING_REJECTED_INDICATION = "補欠落選";

    /**
     * (抽選結果_落選表示)<BR>
     * 定数定義プロパティ　@抽選結果_落選表示<BR>
     */
    public static final String LOT_RESULT_REJECTED_INDICATION = "落選";

    /**
     * (購入申込状態_申込表示)<BR>
     * 定数定義プロパティ　@購入申込状態_申込表示<BR>
     */
    public static final String OFFER_STATE_APPLICATION_INDICATION = "申込";

    /**
     * (購入申込状態_辞退表示)<BR>
     * 定数定義プロパティ　@購入申込状態_辞退表示<BR>
     */
    public static final String OFFER_STATE_CANCEL_INDICATION = "辞退";

    /**
     * (購入申込状態_なし表示)<BR>
     * 定数定義プロパティ　@購入申込状態_なし表示<BR>
     */
    public static final String OFFER_STATE_NO_INDICATION = "----";

    /**
     * (受付状態_受付中表示)<BR>
     * 定数定義プロパティ　@受付状態_受付中表示<BR>
     */
    public static final String RECEIVE_STATE_RECEIVE_INDICATION = "受付中";

    /**
     * (受付状態_受付済表示)<BR>
     * 定数定義プロパティ　@受付状態_受付済表示<BR>
     */
    public static final String RECEIVE_STATE_RECEIVE_END_INDICATION = "受付済";

    /**
     * (受付状態_なし表示)<BR>
     * 定数定義プロパティ　@受付状態_なし表示<BR>
     */
    public static final String RECEIVE_STATE_NO_INDICATION = "----";

    /**
     * (createカラムヘッダ)<BR>
     * カラムヘッダをセットする。<BR>
     * <BR>
     * 　@以下の通りCSVカラムモデルの配列を生成し、setカラムヘッダ()にてインスタンスにセットする。<BR>
     * <BR>
     * [カラムヘッダ配列]<BR>
     * <BR>
     * －　@index = 0<BR>
     * 　@[CSVカラムモデル コンストラクタの引数]<BR>
     * 　@項目ラベル：　@抽選結果・購入申込状況FewCSV.部店コードラベル<BR>
     * 　@カラム番号： 0<BR>
     * 　@項目型：　@CSVカラムモデル.項目型_文字列<BR>
     * 　@日付フォーマット：　@null<BR>
     * <BR>
     * －　@index = 1<BR>
     * 　@[CSVカラムモデル コンストラクタの引数]<BR>
     * 　@項目ラベル：　@抽選結果・購入申込状況FewCSV顧客コードラベル<BR>
     * 　@カラム番号： 1<BR>
     * 　@項目型：　@CSVカラムモデル.項目型_文字列<BR>
     * 　@日付フォーマット：　@null<BR>
     * <BR>
     * －　@index = 2<BR>
     * 　@[CSVカラムモデル コンストラクタの引数]<BR>
     * 　@項目ラベル：　@抽選結果・購入申込状況FewCSV顧客名ラベル<BR>
     * 　@カラム番号： 2<BR>
     * 　@項目型：　@CSVカラムモデル.項目型_文字列<BR>
     * 　@日付フォーマット：　@null<BR>
     * <BR>
     * －　@index = 3<BR>
     * 　@[CSVカラムモデル コンストラクタの引数]<BR>
     * 　@項目ラベル：　@抽選結果・購入申込状況FewCSV抽選結果ラベル<BR>
     * 　@カラム番号： 3<BR>
     * 　@項目型：　@CSVカラムモデル.項目型_文字列<BR>
     * 　@日付フォーマット：　@null<BR>
     * <BR>
     * －　@index = 4<BR>
     * 　@[CSVカラムモデル コンストラクタの引数]<BR>
     * 　@項目ラベル：　@抽選結果・購入申込状況FewCSV当選数量ラベル<BR>
     * 　@カラム番号： 4<BR>
     * 　@項目型：　@CSVカラムモデル.項目型_数値（double）<BR>
     * 　@日付フォーマット：　@null<BR>
     * <BR>
     * －　@index = 5<BR>
     * 　@[CSVカラムモデル コンストラクタの引数]<BR>
     * 　@項目ラベル：　@抽選結果・購入申込状況FewCSV購入申込数量ラベル<BR>
     * 　@カラム番号： 5<BR>
     * 　@項目型：　@CSVカラムモデル.項目型_数値（double）<BR>
     * 　@日付フォーマット：　@null<BR>
     * <BR>
     * －　@index = 6<BR>
     * 　@[CSVカラムモデル コンストラクタの引数]<BR>
     * 　@項目ラベル：　@抽選結果・購入申込状況FewCSV入申込／辞退日時ラベル<BR>
     * 　@カラム番号： 6<BR>
     * 　@項目型：　@CSVカラムモデル.項目型_日付時間<BR>
     * 　@日付フォーマット：<BR>
     * 　@　@new SimpleDateFormat("yyyy/MM/dd HH:mm:ss")<BR>
     * <BR>
     * －　@index = 7<BR>
     * 　@[CSVカラムモデル コンストラクタの引数]<BR>
     * 　@項目ラベル：　@抽選結果・購入申込状況FewCSV購入申込状態ラベル<BR>
     * 　@カラム番号： 7<BR>
     * 　@項目型：　@CSVカラムモデル.項目型_文字列<BR>
     * 　@日付フォーマット：　@null<BR>
     * <BR>
     * －　@index = 8<BR>
     * 　@[CSVカラムモデル コンストラクタの引数]<BR>
     * 　@項目ラベル：　@抽選結果・購入申込状況FewCSV受付状態ラベル<BR>
     * 　@カラム番号： 8<BR>
     * 　@項目型：　@CSVカラムモデル.項目型_文字列<BR>
     * 　@日付フォーマット：　@null<BR>
     * <BR>
     * －　@index = 9<BR>
     * 　@[CSVカラムモデル コンストラクタの引数]<BR>
     * 　@項目ラベル：　@抽選結果・購入申込状況FewCSV優先順位ラベル<BR>
     * 　@カラム番号： 9<BR>
     * 　@項目型：　@CSVカラムモデル.項目型_数値（long）<BR>
     * 　@日付フォーマット：　@null<BR>
     */
    private void createColumnHeader()
    {
        final String STR_METHOD_NAME = "createColumnHeader()";
        log.entering(STR_METHOD_NAME);

        final int COLUMN_MAX = 10;
        this.columnHeader = new WEB3GentradeCsvColumnModel[COLUMN_MAX];

        //  －　@index = 0
        //  [CSVカラムモデル コンストラクタの引数]
        //　@項目ラベル：　@抽選結果・購入申込状況FewCSV.部店コードラベル
        //　@カラム番号： 0
        //　@項目型：　@CSVカラムモデル.項目型_文字列
        //　@日付フォーマット：　@null
        this.columnHeader[0] = new WEB3GentradeCsvColumnModel(
                WEB3AdminIpoLotBuyListFewCsv.BRANCH_CODE_LABEL,
                0,
                WEB3GentradeCsvColumnModel.STRINGTYPE,
                null);

        //  －　@index = 1
        //  [CSVカラムモデル コンストラクタの引数]
        //　@項目ラベル：　@抽選結果・購入申込状況FewCSV顧客コードラベル
        // 　@カラム番号： 1
        // 　@項目型：　@CSVカラムモデル.項目型_文字列
        // 　@日付フォーマット：　@null
        this.columnHeader[1] = new WEB3GentradeCsvColumnModel(
                WEB3AdminIpoLotBuyListFewCsv.ACCOUNT_CODE_LABEL,
                1,
                WEB3GentradeCsvColumnModel.STRINGTYPE,
                null);

        // －　@index = 2
        //  [CSVカラムモデル コンストラクタの引数]
        //　@項目ラベル：　@抽選結果・購入申込状況FewCSV顧客名ラベル
        // 　@カラム番号： 2
        // 　@項目型：　@CSVカラムモデル.項目型_文字列
        // 　@日付フォーマット：　@null
        this.columnHeader[2] = new WEB3GentradeCsvColumnModel(
                WEB3AdminIpoLotBuyListFewCsv.ACCOUNT_NAME_LABEL,
                2,
                WEB3GentradeCsvColumnModel.STRINGTYPE,
                null);

        // －　@index = 3
        //  [CSVカラムモデル コンストラクタの引数]
        //　@項目ラベル：　@抽選結果・購入申込状況FewCSV抽選結果ラベル
        // 　@カラム番号： 3
        // 　@項目型：　@CSVカラムモデル.項目型_文字列
        // 　@日付フォーマット：　@null
        this.columnHeader[3] = new WEB3GentradeCsvColumnModel(
                WEB3AdminIpoLotBuyListFewCsv.LOT_RESULT_LABEL,
                3,
                WEB3GentradeCsvColumnModel.STRINGTYPE,
                null);

        // －　@index = 4
        //  [CSVカラムモデル コンストラクタの引数]
        //　@項目ラベル：　@抽選結果・購入申込状況FewCSV当選数量ラベル
        // 　@カラム番号： 4
        // 　@項目型：　@CSVカラムモデル.項目型_数値（double）
        // 　@日付フォーマット：　@null
        this.columnHeader[4] = new WEB3GentradeCsvColumnModel(
                WEB3AdminIpoLotBuyListFewCsv.ELECTED_QUANTITY_LABEL,
                4,
                WEB3GentradeCsvColumnModel.DOUBLETYPE,
                null);

        // －　@index = 5
        //  [CSVカラムモデル コンストラクタの引数]
        //　@項目ラベル：　@抽選結果・購入申込状況FewCSV購入申込数量ラベル
        // 　@カラム番号： 5
        // 　@項目型：　@CSVカラムモデル.項目型_数値（double）
        // 　@日付フォーマット：　@null
        this.columnHeader[5] = new WEB3GentradeCsvColumnModel(
                WEB3AdminIpoLotBuyListFewCsv.APPLICATION_QUANTITY_LABEL,
                5,
                WEB3GentradeCsvColumnModel.DOUBLETYPE,
                null);

        // －　@index = 6
        //  [CSVカラムモデル コンストラクタの引数]
        //　@項目ラベル：　@抽選結果・購入申込状況FewCSV購入申込／辞退日時ラベル
        // 　@カラム番号： 6
        // 　@項目型：　@CSVカラムモデル.項目型_日付時間
        // 　@日付フォーマット：
        // 　@　@new SimpleDateFormat("yyyy/MM/dd HH:mm:ss") 
        SimpleDateFormat l_simpleDateFormat =
            new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
        this.columnHeader[6] = new WEB3GentradeCsvColumnModel(
                WEB3AdminIpoLotBuyListFewCsv.OFFERING_TIMESTAMP_LABEL,
                6,
                WEB3GentradeCsvColumnModel.TIMESTAMPTYPE,
                l_simpleDateFormat);

        // －　@index = 7
        //  [CSVカラムモデル コンストラクタの引数]
        //　@項目ラベル：　@抽選結果・購入申込状況FewCSV購入申込状態ラベル
        // 　@カラム番号： 7
        // 　@項目型：　@CSVカラムモデル.項目型_文字列
        // 　@日付フォーマット：　@null
        this.columnHeader[7] = new WEB3GentradeCsvColumnModel(
                WEB3AdminIpoLotBuyListFewCsv.OFFER_STATE_LABEL,
                7,
                WEB3GentradeCsvColumnModel.STRINGTYPE,
                null);

        // －　@index = 8
        //  [CSVカラムモデル コンストラクタの引数]
        //　@項目ラベル：　@抽選結果・購入申込状況FewCSV受付状態ラベル
        // 　@カラム番号： 8
        // 　@項目型：　@CSVカラムモデル.項目型_文字列
        // 　@日付フォーマット：　@null
        this.columnHeader[8] = new WEB3GentradeCsvColumnModel(
                WEB3AdminIpoLotBuyListFewCsv.ACCEPT_STATUS_LABEL,
                8,
                WEB3GentradeCsvColumnModel.STRINGTYPE,
                null);

        // －　@index = 9
        //  [CSVカラムモデル コンストラクタの引数]
        //　@項目ラベル：　@抽選結果・購入申込状況FewCSV優先順位ラベル
        // 　@カラム番号： 9
        // 　@項目型：　@CSVカラムモデル.項目型_数値（long）
        // 　@日付フォーマット：　@null
        this.columnHeader[9] = new WEB3GentradeCsvColumnModel(
                WEB3AdminIpoLotBuyListFewCsv.SUBSTITUTE_PRIORITY,
                9,
                WEB3GentradeCsvColumnModel.LONGTYPE,
                null);

        log.exiting(STR_METHOD_NAME);
    }

    /**
     * (createキーヘッダ)<BR>
     * キーヘッダをセットする。<BR>
     * <BR>
     * 　@以下の通り文字列の配列を生成し、setキーヘッダ()にてインスタンスにセットする。<BR>
     * <BR>
     * [キーヘッダ配列]<BR>
     * <BR>
     * －　@index = 0<BR>
     * 　@現在日時を"yyyy/MM/dd HH:mm:ss"の形式にformatした文字列。<BR>
     * <BR>
     * (*1) 現在日時<BR>
     * TradingSystem.getSystemTimestamp()<BR>
     * <BR>
     * －　@index = 1<BR>
     * 　@IPO銘柄.銘柄コード<BR>
     * <BR>
     * －　@index = 2<BR>
     * 　@IPO銘柄.銘柄名<BR>
     * <BR>
     * @@param l_ipoProduct - (IPO銘柄)<BR>
     * IPO銘柄オブジェクト<BR>
     */
    private void createKeyHeader(WEB3IpoProductImpl l_ipoProduct)
    {
        final String STR_METHOD_NAME = " createKeysHeader()";
        log.entering(STR_METHOD_NAME);

        // 以下の通り文字列の配列を生成し、setキーヘッダ()にてインスタンスにセットする。
        String[] l_strKeyHeader = new String[3];

        FinApp l_finApp = (FinApp)Services.getService(FinApp.class);
        TradingSystem l_tradingSys = l_finApp.getTradingSystem();
        Timestamp l_processTime = l_tradingSys.getSystemTimestamp();

        // －　@index = 0
        // 　@現在日時を"yyyy/MM/dd HH:mm:ss"の形式にformatした文字列。
        // －　@index = 1
        // 　@IPO銘柄.銘柄コード
        // －　@index = 2
        //　@IPO銘柄.銘柄名
        l_strKeyHeader[0] = WEB3DateUtility.formatDate(l_processTime, "yyyy/MM/dd HH:mm:ss");
        l_strKeyHeader[1] = ((IpoProductRow)(l_ipoProduct.getDataSourceObject())).getProductCode();
        l_strKeyHeader[2] = ((IpoProductRow)(l_ipoProduct.getDataSourceObject())).getStandardName();

        setKeyHeader(l_strKeyHeader);

        log.exiting(STR_METHOD_NAME);
    }

    /**
     * (抽選結果・購入申込状況FewCSV)<BR>
     * コンストラクタ。<BR>
     * <BR>
     * インスタンスを生成し、ヘッダ情報をセットする。<BR>
     * 　@－super()にてインスタンスを生成する。<BR>
     * 　@－createキーヘッダ(IPO銘柄)をコールし、キーヘッダ情報を作成する。<BR>
     * 　@－createカラムヘッダ()をコールし、カラムヘッダ情報を作成する。<BR>
     * <BR>
     * @@param l_ipoProduct - (IPO銘柄)<BR>
     * IPO銘柄オブジェクト
     */
    public WEB3AdminIpoLotBuyListFewCsv(WEB3IpoProductImpl l_ipoProduct)
    {
        super();
        createKeyHeader(l_ipoProduct);
        createColumnHeader();
    }

    /**
     * (set部店コード)<BR>
     * 部店コードをセットする。<BR>
     * <BR>
     * １）　@カラムモデル取得<BR>
     * 　@this.getカラムモデル()にてCSVカラムモデルを取得する。<BR>
     * <BR>
     * 　@[getカラムモデル()に指定する引数]<BR>
     * 　@項目ラベル：　@抽選結果・購入申込状況FewCSV部店コードラベル<BR>
     * <BR>
     * ２）　@値セット<BR>
     * 　@this.set項目値()にて項目値をセットする。<BR>
     * <BR>
     * 　@[set項目値()に指定する引数]<BR>
     * 　@行番号：　@引数の行番号<BR>
     * 　@カラム：　@１）で取得したカラムモデル<BR>
     * 　@値：　@部店ＩＤに該当する部店オブジェクト.getBranchCode()<BR>
     * <BR>
     * @@param l_intLineNumber - (行番号)<BR>
     * 行番号を指定する。
     * @@param l_lngBranchID - (部店ＩＤ)<BR>
     * 部店ＩＤ
     * @@throws WEB3BaseException
     */
    public void setBranchCode(int l_intLineNumber, long l_lngBranchID) throws WEB3BaseException
    {
        final String STR_METHOD_NAME = " setBranchCode()";
        log.entering(STR_METHOD_NAME);
        // カラムモデル取得
        WEB3GentradeCsvColumnModel l_csvDataModel = this.getColumnModel(BRANCH_CODE_LABEL);

        // ２）　@値セット
        // this.set項目値()にて項目値をセットする。
        // [set項目値()に指定する引数]
        // 行番号：　@引数の行番号
        // カラム：　@１）で取得したカラムモデル
        // 値：　@部店ＩＤに該当する部店オブジェクト.getBranchCode()
        FinApp l_finApp = (FinApp)Services.getService(FinApp.class);
        Branch l_branch = null;
        try
        {
            l_branch = l_finApp.getAccountManager().getBranch(l_lngBranchID);
            String l_strValue = l_branch.getBranchCode();
            this.setValue(l_intLineNumber, l_csvDataModel, l_strValue);
        }
        catch (NotFoundException l_ex)
        {
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80006,
                this.getClass().getName() + STR_METHOD_NAME,
                l_ex.getMessage(),
                l_ex);
        }

        log.exiting(STR_METHOD_NAME);
    }

    /**
     * (set顧客)<BR>
     * 顧客コード、顧客名をセットする。<BR>
     * <BR>
     * １）　@顧客オブジェクト取得<BR>
     * 　@口座ＩＤに該当する顧客オブジェクトを取得する。<BR>
     * <BR>
     * ２）　@（顧客コード）カラムモデル取得<BR>
     * 　@this.getカラムモデル()にてCSVカラムモデルを取得する。<BR>
     * <BR>
     * 　@[getカラムモデル()に指定する引数]<BR>
     * 　@項目ラベル：　@抽選結果・購入申込状況FewCSV顧客コードラベル<BR>
     * <BR>
     * ３）　@（顧客コード）値セット<BR>
     * 　@this.set項目値()にて項目値をセットする。<BR>
     * <BR>
     * 　@[set項目値()に指定する引数]<BR>
     * 　@行番号：　@引数の行番号<BR>
     * 　@カラム：　@２）で取得したカラムモデル<BR>
     * 　@値：　@顧客.getAccountCode()の左6byte<BR>
     * <BR>
     * ４）　@（顧客名）カラムモデル取得<BR>
     * 　@this.getカラムモデル()にてCSVカラムモデルを取得する。<BR>
     * <BR>
     * 　@[getカラムモデル()に指定する引数]<BR>
     * 　@項目ラベル：　@抽選結果・購入申込状況FewCSV顧客名ラベル<BR>
     * <BR>
     * ５）　@（顧客名）値セット<BR>
     * 　@this.set項目値()にて項目値をセットする。<BR>
     * <BR>
     * 　@[set項目値()に指定する引数]<BR>
     * 　@行番号：　@引数の行番号<BR>
     * 　@カラム：　@４）で取得したカラムモデル<BR>
     * 　@値：　@顧客.get顧客表示名()<BR>
     * <BR>
     * @@param l_intLineNumber - (行番号)<BR>
     * 行番号を指定する。
     * @@param l_lngAccountId - (口座ＩＤ)<BR>
     * 口座ＩＤ
     * @@throws WEB3BaseException
     */
    public void setAccount(int l_intLineNumber, long l_lngAccountId) throws WEB3BaseException
    {
        final String STR_METHOD_NAME = " setAccount()";
        log.entering(STR_METHOD_NAME);

        try
        {
            // １）　@顧客オブジェクト取得
            // 口座ＩＤに該当する顧客オブジェクトを取得する。
            FinApp l_finApp = (FinApp)Services.getService(FinApp.class);

            MainAccount l_mainAccount =
                l_finApp.getAccountManager().getMainAccount(l_lngAccountId);
            MainAccountRow l_mainAccountRow =
                (MainAccountRow)l_mainAccount.getDataSourceObject();

            // ２）　@（顧客コード）カラムモデル取得
            // this.getカラムモデル()にてCSVカラムモデルを取得する。
            WEB3GentradeCsvColumnModel l_csvDataModelAccountCode =
                this.getColumnModel(ACCOUNT_CODE_LABEL);

            // ３）　@（顧客コード）値セット
            // this.set項目値()にて項目値をセットする。
            String l_strAccountCode = l_mainAccount.getAccountCode();
            this.setValue(l_intLineNumber,
                l_csvDataModelAccountCode,
                l_strAccountCode.substring(0, 6));

            // ４）　@（顧客名）カラムモデル取得
            //　@this.getカラムモデル()にてCSVカラムモデルを取得する
            WEB3GentradeCsvColumnModel l_csvDataModelAccountName =
                this.getColumnModel(ACCOUNT_NAME_LABEL);

            // ５）　@（顧客名）値セット
            //　@this.set項目値()にて項目値をセットする。
            String l_strAccountName = l_mainAccountRow.getFamilyName();
            this.setValue(l_intLineNumber, l_csvDataModelAccountName, l_strAccountName);
        }
        catch (NotFoundException l_ex)
        {
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80006,
                this.getClass().getName() + STR_METHOD_NAME,
                l_ex.getMessage(),
                l_ex);
        }

        log.exiting(STR_METHOD_NAME);
    }

    /**
     * (set抽選結果)<BR>
     * 抽選結果をセットする。<BR>
     * <BR>
     * １）　@カラムモデル取得<BR>
     * 　@this.getカラムモデル()にてCSVカラムモデルを取得する。<BR>
     * <BR>
     * 　@[getカラムモデル()に指定する引数]<BR>
     * 　@項目ラベル：　@抽選結果・購入申込状況FewCSV受付結果ラベル<BR>
     * <BR>
     * ２）　@値セット<BR>
     * 　@this.set項目値()にて項目値をセットする。<BR>
     * <BR>
     * 　@[set項目値()に指定する引数]<BR>
     * 　@行番号：　@引数の行番号<BR>
     * 　@カラム：　@１）で取得したカラムモデル<BR>
     * 　@値：<BR>
     * 　@　@（抽選結果 == ”当選”）の場合、<BR>
     * 　@　@　@－抽選結果・購入申込状況FewCSV抽選結果_当選表示。<BR>
     * <BR>
     * 　@　@（抽選結果 == ”補欠” && 抽選結果（繰上） == ”当選”） の場合、<BR>
     * 　@　@　@－抽選結果・購入申込状況FewCSV抽選結果_補欠当選表示。<BR>
     * <BR>
     * 　@　@（抽選結果 == ”補欠” && 抽選結果（繰上） == ”落選”） の場合、<BR>
     * 　@　@　@－抽選結果・購入申込状況FewCSV抽選結果_補欠落選表示。<BR>
     * <BR>
     * 　@　@（抽選結果 == ”補欠” && 抽選結果（繰上） == ”DEFAULT（未抽選）”） の場合、<BR>
     * 　@　@　@－抽選結果・購入申込状況FewCSV抽選結果_補欠表示。<BR>
     * <BR>
     * 　@　@（抽選結果 == ”落選”）の場合、<BR>
     * 　@　@　@－抽選結果・購入申込状況FewCSV抽選結果_落選表示。<BR>
     * <BR>
     * @@param l_intLineNumber - (行番号)<BR>
     * 行番号を指定する。
     * <BR>
     * @@param l_strLotResult - (抽選結果)<BR>
     * 抽選結果<BR>
     * <BR>
     * 0：　@DEFAULT（未抽選）<BR>
     * 1：　@当選<BR>
     * 2：　@補欠<BR>
     * 3：　@落選<BR>
     * @@param l_strLotResultRetry - (抽選結果（繰上）)<BR>
     * 抽選結果（繰上）<BR>
     * <BR>
     * 0：　@DEFAULT（未抽選）<BR>
     * 1：　@当選<BR>
     * 3：　@落選<BR>
     */
    public void setLotResult(
        int l_intLineNumber,
        String l_strLotResult,
        String l_strLotResultRetry)
    {
        final String STR_METHOD_NAME = " setLotResult()";
        log.entering(STR_METHOD_NAME);

        // １）　@カラムモデル取得
        //  this.getカラムモデル()にてCSVカラムモデルを取得する。
        WEB3GentradeCsvColumnModel l_csvDataModel = this.getColumnModel(LOT_RESULT_LABEL);

        // ２）　@値セット
        // this.set項目値()にて項目値をセットする。
        String l_strResult = null;

        //（抽選結果 == ”当選”）の場合、
        //　@－抽選結果・購入申込状況FewCSV抽選結果_当選表示。
        if (WEB3LotResultDivDef.ELECTION.equals(l_strLotResult))
        {
            l_strResult = LOT_RESULT_PRIZE_INDICATION;
        }
        // 抽選結果 == ”補欠” && 抽選結果（繰上） == ”当選”） の場合、
        //　@－抽選結果・購入申込状況FewCSV抽選結果_補欠当選表示。
        else if (WEB3LotResultDivDef.SUPPLEMENT.equals(l_strLotResult)
            && WEB3LotResultDivDef.ELECTION.equals(l_strLotResultRetry))
        {
            l_strResult = LOT_RESULT_WAITING_PRIZE_INDICATION;
        }
        // （抽選結果 == ”補欠” && 抽選結果（繰上） == ”落選”） の場合、
        //　@－抽選結果・購入申込状況FewCSV抽選結果_補欠落選表示。
        else if (WEB3LotResultDivDef.SUPPLEMENT.equals(l_strLotResult)
                && WEB3LotResultDivDef.DEFEAT.equals(l_strLotResultRetry))
        {
            l_strResult = LOT_RESULT_WAITING_REJECTED_INDICATION;
        }
        // (抽選結果 == ”補欠” && 抽選結果（繰上） == ”DEFAULT（未抽選）”） の場合、
        //　@－抽選結果・購入申込状況FewCSV抽選結果_補欠表示。
        else if (WEB3LotResultDivDef.SUPPLEMENT.equals(l_strLotResult)
                && WEB3LotResultDivDef.DEFAULT.equals(l_strLotResultRetry))
        {
            l_strResult = LOT_RESULT_WAITING_INDICATION;
        }
        // （抽選結果 == ”落選”）の場合、
        //　@－抽選結果・購入申込状況FewCSV抽選結果_落選表示。
        else if (WEB3LotResultDivDef.DEFEAT.equals(l_strLotResult))
        {
            l_strResult = LOT_RESULT_REJECTED_INDICATION;
        }

        // ２）　@値セット
        //　@this.set項目値()にて項目値をセットする。
        this.setValue(l_intLineNumber, l_csvDataModel, l_strResult);
        log.exiting(STR_METHOD_NAME);
    }

    /**
     * (set当選数量)<BR>
     * 当選数量をセットする。<BR>
     * <BR>
     * １）　@カラムモデル取得<BR>
     * 　@this.getカラムモデル()にてCSVカラムモデルを取得する。<BR>
     * <BR>
     * 　@[getカラムモデル()に指定する引数<BR>
     * 　@項目ラベル：　@抽選結果・購入申込状況FewCSV当選数量ラベル<BR>
     * <BR>
     * ２）　@値セット<BR>
     * 　@this.set項目値()にて項目値をセットする。<BR>
     * <BR>
     * 　@[set項目値()に指定する引数]<BR>
     * 　@行番号：　@引数の行番号<BR>
     * 　@カラム：　@１）で取得したカラムモデル<BR>
     * 　@値：　@当選数量<BR>
     * <BR>
     * @@param l_intLineNumber - (行番号)<BR>
     * 行番号を指定する。
     * @@param l_dblElectedQuantity - (当選数量)<BR>
     * 当選数量
     */
    public void setElectedQuantity(int l_intLineNumber, double l_dblElectedQuantity)
    {
        final String STR_METHOD_NAME = " setElectedQuantity()";
        log.entering(STR_METHOD_NAME);

        // １）　@カラムモデル取得
        //  this.getカラムモデル()にてCSVカラムモデルを取得する。
        WEB3GentradeCsvColumnModel l_csvDataModel =
            this.getColumnModel(ELECTED_QUANTITY_LABEL);

        // ２）　@値セット
        // 　@this.set項目値()にて項目値をセットする。
        this.setValue(l_intLineNumber, l_csvDataModel, WEB3StringTypeUtility.formatNumber(l_dblElectedQuantity));

        log.exiting(STR_METHOD_NAME);
    }

    /**
     * (set購入申込数量)<BR>
     * 購入申込数量をセットする。<BR>
     * <BR>
     * １）　@カラムモデル取得<BR>
     * 　@this.getカラムモデル()にてCSVカラムモデルを取得する。<BR>
     * <BR>
     * 　@[getカラムモデル()に指定する引数]<BR>
     * 　@項目ラベル：　@抽選結果・購入申込状況FewCSV購入申込数量ラベル<BR>
     * <BR>
     * ２）　@値セット<BR>
     * 　@this.set項目値()にて項目値をセットする。<BR>
     * <BR>
     * 　@[set項目値()に指定する引数]<BR>
     * 　@行番号：　@引数の行番号<BR>
     * 　@カラム：　@１）で取得したカラムモデル<BR>
     * 　@値：　@購入申込数量<BR>
     * <BR>
     * @@param l_intLineNumber - (行番号)<BR>
     * 行番号を指定する。
     * <BR>
     * @@param l_dblApplicationQuantity - (購入申込数量)<BR>
     * 購入申込数量
     */
    public void setApplicationQuantity(int l_intLineNumber, double l_dblApplicationQuantity)
    {
        final String STR_METHOD_NAME = " setApplicationQuantity()";
        log.entering(STR_METHOD_NAME);

        //１）　@カラムモデル取得
        //  this.getカラムモデル()にてCSVカラムモデルを取得する。
        WEB3GentradeCsvColumnModel l_csvDataModel =
            this.getColumnModel(APPLICATION_QUANTITY_LABEL);

        // ２）　@値セット
        // this.set項目値()にて項目値をセットする。
        this.setValue(l_intLineNumber, l_csvDataModel, WEB3StringTypeUtility.formatNumber(l_dblApplicationQuantity));

        log.exiting(STR_METHOD_NAME);
    }

    /**
     * (set購入申込／辞退日時)<BR>
     * 購入申込／辞退日時をセットする。<BR>
     * <BR>
     * １）　@カラムモデル取得<BR>
     * 　@this.getカラムモデル()にてCSVカラムモデルを取得する。<BR>
     * <BR>
     * 　@[getカラムモデル()に指定する引数]<BR>
     * 　@項目ラベル：　@抽選結果・購入申込状況FewCSV購入申込／辞退日時ラベル<BR>
     * <BR>
     * ２）　@値セット<BR>
     * 　@this.set項目値()にて項目値をセットする。<BR>
     * <BR>
     * 　@[set項目値()に指定する引数]<BR>
     * 　@行番号：　@引数の行番号<BR>
     * 　@カラム：　@１）で取得したカラムモデル<BR>
     * 　@値：　@購入申込／辞退日時 <BR>
     * <BR>
     * @@param l_intLineNumber - (行番号)<BR>
     * 行番号を指定する。
     * @@param l_datOfferingTimestamp - (購入申込／辞退日時)<BR>
     * 購入申込／辞退日時
     */
    public void setOfferingTimestamp(
        int l_intLineNumber,
        Date l_datOfferingTimestamp)
    {
        final String STR_METHOD_NAME = " setOfferingTimestamp()";
        log.entering(STR_METHOD_NAME);

        // １）　@カラムモデル取得
        //  this.getカラムモデル()にてCSVカラムモデルを取得する。
        WEB3GentradeCsvColumnModel l_csvDataModel =
            this.getColumnModel(OFFERING_TIMESTAMP_LABEL);

        // ２）　@値セット
        // 　@this.set項目値()にて項目値をセットする。
        this.setValue(l_intLineNumber, l_csvDataModel, l_datOfferingTimestamp);

        log.exiting(STR_METHOD_NAME);
    }

    /**
     * (set購入申込状態)<BR>
     * 購入申込状態をセットする。<BR>
     * <BR>
     * １）　@カラムモデル取得<BR>
     * 　@this.getカラムモデル()にてCSVカラムモデルを取得する。<BR>
     * <BR>
     * 　@[getカラムモデル()に指定する引数]<BR>
     * 　@項目ラベル：　@抽選結果・購入申込状況FewCSV購入申込状態ラベル<BR>
     * <BR>
     * ２）　@値セット<BR>
     * 　@this.set項目値()にて項目値をセットする。<BR>
     * <BR>
     * 　@[set項目値()に指定する引数]<BR>
     * 　@行番号：　@引数の行番号<BR>
     * 　@カラム：　@１）で取得したカラムモデル<BR>
     * 　@値：<BR>
     * 　@　@（購入申込区分 == ”DEFAULT（初期値）”）の場合、<BR>
     * 　@　@　@－抽選結果・購入申込状況FewCSV購入申込状態_なし表示。<BR>
     * <BR>
     * 　@　@（購入申込区分 == ”購入申込”）の場合、<BR>
     * 　@　@　@－抽選結果・購入申込状況FewCSV購入申込状態_申込表示。<BR>
     * <BR>
     * 　@　@（購入申込区分 == ”辞退”）の場合、<BR>
     * 　@　@　@－抽選結果・購入申込状況FewCSV購入申込状態_辞退表示。<BR>
     * <BR>
     * @@param l_intLineNumber - (行番号)<BR>
     * 行番号を指定する。
     * <BR>
     * @@param l_strOfferDiv - (購入申込区分)<BR>
     * 購入申込区分<BR>
     * <BR>
     * 0：　@DEFAULT（初期値）<BR>
     * 1：　@購入申込<BR>
     * 2：　@辞退<BR>
     */
    public void setOfferStatus(int l_intLineNumber, String l_strOfferDiv)
    {
        final String STR_METHOD_NAME = " setOfferStatus()";
        log.entering(STR_METHOD_NAME);

        //１）　@カラムモデル取得
        // 　@this.getカラムモデル()にてCSVカラムモデルを取得する。
        WEB3GentradeCsvColumnModel l_csvDataModel =
            this.getColumnModel(OFFER_STATE_LABEL);

        // (購入申込区分 == ”DEFAULT（初期値）”）の場合、
        // 　@－抽選結果・購入申込状況FewCSV購入申込状態_なし表示。
        String l_strOfferStatus = null;
        if (WEB3OfferingDivDef.DEFAULT.equals(l_strOfferDiv))
        {
            l_strOfferStatus = OFFER_STATE_NO_INDICATION;
        }
        // （購入申込区分 == ”購入申込”）の場合、
        //　@　@－抽選結果・購入申込状況FewCSV購入申込状態_申込表示。
        else if (WEB3OfferingDivDef.PURCHASE_APPLICATION.equals(l_strOfferDiv))
        {
            l_strOfferStatus = OFFER_STATE_APPLICATION_INDICATION;
        }
        // （購入申込区分 == ”辞退”）の場合、
        //　@－抽選結果・購入申込状況FewCSV購入申込状態_辞退表示。
        else if (WEB3OfferingDivDef.REFUSAL.equals(l_strOfferDiv))
        {
            l_strOfferStatus = OFFER_STATE_CANCEL_INDICATION;
        }

        // ２）　@値セット
        // this.set項目値()にて項目値をセットする。
        this.setValue(l_intLineNumber, l_csvDataModel, l_strOfferStatus);

        log.exiting(STR_METHOD_NAME);
    }

    /**
     * (set受付状態)<BR>
     * 受付状態をセットする。<BR>
     * <BR>
     * １）　@カラムモデル取得<BR>
     * 　@this.getカラムモデル()にてCSVカラムモデルを取得する。<BR>
     * <BR>
     * 　@[getカラムモデル()に指定する引数]<BR>
     * 　@項目ラベル：　@抽選結果・購入申込状況FewCSV受付状態ラベル<BR>
     * <BR>
     * ２）　@値セット<BR>
     * 　@this.set項目値()にて項目値をセットする。<BR>
     * <BR>
     * 　@[set項目値()に指定する引数]<BR>
     * 　@行番号：　@引数の行番号<BR>
     * 　@カラム：　@１）で取得したカラムモデル<BR>
     * 　@値：<BR>
     * 　@　@（受付状態 == ”DEFAULT（初期値）”）の場合、<BR>
     * 　@　@　@－抽選結果・購入申込状況FewCSV受付状態_なし表示。<BR>
     * <BR>
     * 　@　@（受付状態 == ”SONAR送信済”）の場合、<BR>
     * 　@　@　@－抽選結果・購入申込状況FewCSV受付状態_受付中表示。<BR>
     * <BR>
     * 　@　@（受付状態 == ”SONAR反映済”）の場合、<BR>
     * 　@　@　@－抽選結果・購入申込状況FewCSV受付状態_受付済表示。<BR>
     * <BR>
     * @@param l_intLineNumber - (行番号)<BR>
     * 行番号を指定する。
     * <BR>
     * @@param l_strAcceptStatus - (受付状態)<BR>
     * 受付状態<BR>
     * <BR>
     * 0：　@DEFAULT（初期値）<BR>
     * 1：　@SONAR送信済<BR>
     * 2：　@SONAR反映済<BR>
     */
    public void setAcceptStatus(int l_intLineNumber, String l_strAcceptStatus)
    {
        final String STR_METHOD_NAME = "setAcceptStatus()";
        log.entering(STR_METHOD_NAME);

        // １）　@カラムモデル取得
        // 　@this.getカラムモデル()にてCSVカラムモデルを取得する。
        WEB3GentradeCsvColumnModel l_csvDataModel =
            this.getColumnModel(ACCEPT_STATUS_LABEL);

        String l_strResult = null;
        // （受付状態 == ”DEFAULT（初期値）”）の場合、
        //　@－抽選結果・購入申込状況FewCSV受付状態_なし表示。
        if (WEB3IpoOrderAcceptStatusDef.DEFAULT.equals(l_strAcceptStatus))
        {
            l_strResult = RECEIVE_STATE_NO_INDICATION;
        }
        // （受付状態 == ”SONAR送信済”）の場合、
        //　@－抽選結果・購入申込状況FewCSV受付状態_受付中表示。
        else if (WEB3IpoOrderAcceptStatusDef.SONAR_MAIL_SENDED.equals(l_strAcceptStatus))
        {
            l_strResult = RECEIVE_STATE_RECEIVE_INDICATION;
        }
        // （受付状態 == ”SONAR反映済”）の場合、
        //　@－抽選結果・購入申込状況FewCSV受付状態_受付済表示。
        else if (WEB3IpoOrderAcceptStatusDef.SONAR_REFLECTED.equals(l_strAcceptStatus))
        {
            l_strResult = RECEIVE_STATE_RECEIVE_END_INDICATION;
        }

        // ２）　@値セット
        //this.set項目値()にて項目値をセットする。
        this.setValue(l_intLineNumber, l_csvDataModel, l_strResult);

        log.exiting(STR_METHOD_NAME);
    }

    /**
     * (set優先順位)<BR>
     * 優先順位をセットする。<BR>
     * <BR>
     * １）　@カラムモデル取得<BR>
     * 　@this.getカラムモデル()にてCSVカラムモデルを取得する。<BR>
     * <BR>
     * 　@[getカラムモデル()に指定する引数]<BR>
     * 　@項目ラベル：　@抽選結果・購入申込状況FewCSV優先順位ラベル<BR>
     * <BR>
     * ２）　@値セット<BR>
     * 　@this.set項目値()にて項目値をセットする。<BR>
     * <BR>
     * 　@[set項目値()に指定する引数]<BR>
     * 　@行番号：　@引数の行番号<BR>
     * 　@カラム：　@１）で取得したカラムモデル<BR>
     * 　@値：　@優先順位<BR>
     * <BR>
     * @@param l_intLineNumber - (行番号)<BR>
     * 行番号
     * @@param l_lngSubstitutePriority - (優先順位)<BR>
     * 優先順位
     */
    public void setSubstitutePriority(int l_intLineNumber, long l_lngSubstitutePriority)
    {
        final String STR_METHOD_NAME = " setSubstitutePriority()";
        log.entering(STR_METHOD_NAME);

        //  １）　@カラムモデル取得
        // 　@this.getカラムモデル()にてCSVカラムモデルを取得する。
        WEB3GentradeCsvColumnModel l_csvDataModel =
            this.getColumnModel(SUBSTITUTE_PRIORITY);

        // ２）　@値セット<BR>
        // 　@this.set項目値()にて項目値をセットする。
        Long l_lonSubstitutePriority = new Long(l_lngSubstitutePriority);
        this.setValue(l_intLineNumber, l_csvDataModel, l_lonSubstitutePriority);

        log.exiting(STR_METHOD_NAME);
    }
}
@
