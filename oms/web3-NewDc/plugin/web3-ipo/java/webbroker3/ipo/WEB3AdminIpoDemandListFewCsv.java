head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.15.05.43.07;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	6f44d7efa104045;
filename	WEB3AdminIpoDemandListFewCsv.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : ブックビルディング状況fewCSV(WEB3AdminIpoDemandListFewCsv.java)
Author Name      : Daiwa Institute of Research
Revision History : 2006/11/09 齊珂 (中訊) 新規作成
                   2006/11/13 齊珂 (中訊) 仕様変更・モデル161、モデル163
*/

package webbroker3.ipo;

import java.sql.Timestamp;
import java.text.SimpleDateFormat;

import com.fitechlabs.xtrade.kernel.boot.Services;
import com.fitechlabs.xtrade.plugin.tc.gentrade.Branch;
import com.fitechlabs.xtrade.plugin.tc.gentrade.FinApp;
import com.fitechlabs.xtrade.plugin.tc.gentrade.MainAccount;
import com.fitechlabs.xtrade.plugin.tc.gentrade.NotFoundException;
import com.fitechlabs.xtrade.plugin.tc.gentrade.OrderStatusEnum;
import com.fitechlabs.xtrade.plugin.tc.gentrade.TradingSystem;
import com.fitechlabs.xtrade.plugin.tc.gentrade.data.MainAccountRow;

import webbroker3.common.WEB3BaseException;
import webbroker3.common.WEB3BaseRuntimeException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.common.WEB3SystemLayerException;
import webbroker3.gentrade.WEB3GentradeCsvColumnModel;
import webbroker3.gentrade.WEB3GentradeCsvDataModel;
import webbroker3.ipo.data.IpoProductRow;
import webbroker3.util.WEB3DateUtility;
import webbroker3.util.WEB3LogUtility;
import webbroker3.util.WEB3StringTypeUtility;

/**
 * (ブックビルディング状況fewCSV)<BR>
 * ブックビルディング状況ダウンロードで作成するCSVファ@イルデータクラス <BR>
 * （扱者コードなし）<BR>
 *
 * @@author 齊珂
 * @@version 1.0
 */
public class WEB3AdminIpoDemandListFewCsv extends WEB3GentradeCsvDataModel
{
    /**
     * ログ出力ユーティリティ。<BR>
     */
    private static WEB3LogUtility log = WEB3LogUtility.getInstance(WEB3AdminIpoDemandListFewCsv.class);

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
    public static final String ACCOUNT_NAME_LABEL = "顧客名";

    /**
     * (新規申告日時ラベル)<BR>
     * 定数定義プロパティ　@”新規申告日時”<BR>
     */
    public static final String BOOKBUILDING_CREATED_TIMESTAMP = "新規申告日時";

    /**
     * (申告数量ラベル)<BR>
     * 定数定義プロパティ　@”申告数量”<BR>
     */
    public static final String ORDER_QUANTITY_LABEL = "申告数量";

    /**
     * (申告価格ラベル)<BR>
     * 定数定義プロパティ　@”申告価格”<BR>
     */
    public static final String ORDER_PRICE_LABEL = "申告価格";

    /**
     * (指値／成行ラベル)<BR>
     * 定数定義プロパティ　@”指値／成行”<BR>
     */
    public static final String LIMIT_PRICE_MARKET_ORDER_LABEL = "指値／成行";

    /**
     * (基準値ラベル)<BR>
     * 定数定義プロパティ　@”基準値”<BR>
     */
    public static final String BASE_PRICE_LABEL = "基準値";

    /**
     * (申告相当額ラベル)<BR>
     * 定数定義プロパティ　@”申告相当額”<BR>
     */
    public static final String BOOKBUILDING_PRICE_LABEL = "申告相当額";

    /**
     * (出金余力ラベル)<BR>
     * 定数定義プロパティ　@”出金余力”<BR>
     */
    public static final String TRADING_POWER_LABEL = "出金余力";

    /**
     * (申告額保持者ラベル)<BR>
     * 定数定義プロパティ　@”申告額保持者”<BR>
     */
    public static final String TRADING_POWER_CHECK = "申告額保持者";

    /**
     * (申告状態ラベル)<BR>
     * 定数定義プロパティ　@”申告状態”<BR>
     */
    public static final String ORDER_STATUS_LABEL = "申告状態";

    /**
     * (指値表示)<BR>
     * 定数定義プロパティ　@指値<BR>
     */
    public static final String LIMIT_PRICE_INDICATION = "指値";

    /**
     * (成行表示)<BR>
     * 定数定義プロパティ　@成行<BR>
     */
    public static final String MARKET_ORDER_INDICATION = "成行";

    /**
     * (申告額保持者_OK表示)<BR>
     * 定数定義プロパティ　@申告額保持者表示 OK<BR>
     */
    public static final String TRADING_POWER_CHECK_OK = "○";

    /**
     * (申告額保持者_NG表示)<BR>
     * 定数定義プロパティ　@申告額保持者表示 NG<BR>
     */
    public static final String TRADING_POWER_CHECK_NG = "×";

    /**
     * (申告状態_新規表示)<BR>
     * 定数定義プロパティ　@申告状態_新規表示<BR>
     */
    public static final String ORDER_STATUS_ORDERED = "新規";

    /**
     * (申告状態_訂正表示)<BR>
     * 定数定義プロパティ　@申告状態_訂正表示<BR>
     */
    public static final String ORDER_STATUS_MODIFIED = "訂正";

    /**
     * (申告状態_取消表示)<BR>
     * 定数定義プロパティ　@申告状態_取消表示<BR>
     */
    public static final String ORDER_STATUS_CANCELLED = "取消";

    /**
     * (余力なしの表示)<BR>
     * 定数定義プロパティ　@余力なしの表示<BR>
     */
    public static final String TRADING_POWER_HYPHEN = "－";

    /**
     * (ブックビルディング状況fewCSV)<BR>
     * コンストラクタ。<BR>
     * <BR>
     * インスタンスを生成し、ヘッダ情報をセットする。<BR>
     * 　@－super()にてインスタンスを生成する。 <BR>
     * 　@－createキーヘッダ(IPO銘柄)をコールし、キーヘッダ情報を作成する。<BR>
     * 　@－createカラムヘッダ()をコールし、カラムヘッダ情報を作成する。<BR>
     * <BR>
     * @@param l_ipoProduct - (IPO銘柄)<BR>
     * IPO銘柄オブジェクト
     * @@roseuid 40E27ACA0335
     */
    public WEB3AdminIpoDemandListFewCsv(WEB3IpoProductImpl l_ipoProduct)
    {
        //super()にてインスタンスを生成する。
        super();

        final String STR_METHOD_NAME = " WEB3AdminIpoDemandListFewCsv(WEB3IpoProductImpl)";
        log.entering(STR_METHOD_NAME);

        //createキーヘッダ(IPO銘柄)をコールし、キーヘッダ情報を作成する。
        this.createKeyHeader(l_ipoProduct);

        //createカラムヘッダ()をコールし、カラムヘッダ情報を作成する。
        this.createColumnHeader();

        log.exiting(STR_METHOD_NAME);
    }

    /**
     *  (createカラムヘッダ) <BR>
     * カラムヘッダをセットする。<BR>
     * <BR>
     * 　@以下の通りCSVカラムモデルの配列を生成し、setカラムヘッダ()にてインスタンスにセットする。<BR>
     * <BR>
     * [カラムヘッダ配列] <BR>
     * <BR>
     * －　@index = 0 <BR>
     * 　@[CSVカラムモデル コンストラクタの引数] <BR>
     * 　@項目ラベル：　@ブックビルディング状況fewCSV.部店コードラベル <BR>
     * 　@カラム番号： 0 <BR>
     * 　@項目型：　@CSVカラムモデル.項目型_文字列 <BR>
     * 　@日付フォーマット：　@null <BR>
     * <BR>
     * －　@index = 1 <BR>
     * 　@[CSVカラムモデル コンストラクタの引数] <BR>
     * 　@項目ラベル：　@ブックビルディング状況fewCSV.顧客コードラベル <BR>
     * 　@カラム番号： 1<BR>
     * 　@項目型：　@CSVカラムモデル.項目型_文字列 <BR>
     * 　@日付フォーマット：　@null <BR>
     * <BR>
     * －　@index = 2 <BR>
     * 　@[CSVカラムモデル コンストラクタの引数] <BR>
     * 　@項目ラベル：　@ブックビルディング状況fewCSV.顧客名ラベル <BR>
     * 　@カラム番号： 2 <BR>
     * 　@項目型：　@CSVカラムモデル.項目型_文字列 <BR>
     * 　@日付フォーマット：　@null <BR>
     * <BR>
     * －　@index = 3 <BR>
     * 　@[CSVカラムモデル コンストラクタの引数] <BR>
     * 　@項目ラベル：　@ブックビルディング状況fewCSV.新規申告日時ラベル <BR>
     * 　@カラム番号： 3 <BR>
     * 　@項目型：　@CSVカラムモデル.項目型_日付時間 <BR>
     * 　@日付フォーマット：　@ <BR>
     * 　@　@new SimpleDateFormat("yyyy/MM/dd HH:mm:ss") <BR>
     * <BR>
     * －　@index = 4 <BR>
     * 　@[CSVカラムモデル コンストラクタの引数] <BR>
     * 　@項目ラベル：　@ブックビルディング状況fewCSV.申告数量ラベル <BR>
     * 　@カラム番号： 4 <BR>
     * 　@項目型：　@CSVカラムモデル.項目型_数値（double） <BR>
     * 　@日付フォーマット：　@null <BR>
     * <BR>
     * －　@index = 5 <BR>
     * 　@[CSVカラムモデル コンストラクタの引数] <BR>
     * 　@項目ラベル：　@ブックビルディング状況fewCSV.申告価格ラベル <BR>
     * 　@カラム番号： 5 <BR>
     * 　@項目型：　@CSVカラムモデル.項目型_数値（double） <BR>
     * 　@日付フォーマット：　@null <BR>
     * <BR>
     * －　@index = 6 <BR>
     * 　@[CSVカラムモデル コンストラクタの引数] <BR>
     * 　@項目ラベル：　@ブックビルディング状況fewCSV.指値／成行ラベル <BR>
     * 　@カラム番号： 6 <BR>
     * 　@項目型：　@CSVカラムモデル.項目型_文字列 <BR>
     * 　@日付フォーマット：　@null <BR>
     * <BR>
     * －　@index = 7 <BR>
     * 　@[CSVカラムモデル コンストラクタの引数] <BR>
     * 　@項目ラベル：　@ブックビルディング状況fewCSV.基準値ラベル <BR>
     * 　@カラム番号： 7 <BR>
     * 　@項目型：　@CSVカラムモデル.項目型_数値（double） <BR>
     * 　@日付フォーマット：　@null <BR>
     * <BR>
     * －　@index = 8 <BR>
     * 　@[CSVカラムモデル コンストラクタの引数] <BR>
     * 　@項目ラベル：　@ブックビルディング状況fewCSV.申告相当額ラベル <BR>
     * 　@カラム番号： 8 <BR>
     * 　@項目型：　@CSVカラムモデル.項目型_数値（double） <BR>
     * 　@日付フォーマット：　@null <BR>
     * <BR>
     * －　@index = 9 <BR>
     * 　@[CSVカラムモデル コンストラクタの引数] <BR>
     * 　@項目ラベル：　@ブックビルディング状況fewCSV.出金余力ラベル <BR>
     * 　@カラム番号： 9 <BR>
     * 　@項目型：　@CSVカラムモデル.項目型_数値（double） <BR>
     * 　@日付フォーマット：　@null <BR>
     * <BR>
     * －　@index = 10 <BR>
     * 　@[CSVカラムモデル コンストラクタの引数] <BR>
     * 　@項目ラベル：　@ブックビルディング状況fewCSV.申告額保持者ラベル <BR>
     * 　@カラム番号： 10 <BR>
     * 　@項目型：　@CSVカラムモデル.項目型_文字列 <BR>
     * 　@日付フォーマット：　@null <BR>
     * <BR>
     * －　@index = 11 <BR>
     * 　@[CSVカラムモデル コンストラクタの引数] <BR>
     * 　@項目ラベル：　@ブックビルディング状況fewCSV.申告状態ラベル <BR>
     * 　@カラム番号： 11 <BR>
     * 　@項目型：　@CSVカラムモデル.項目型_文字列 <BR>
     * 　@日付フォーマット：　@null <BR>
     */
    private void createColumnHeader()
    {
        final String STR_METHOD_NAME = " createColumnHeader()";
        log.entering(STR_METHOD_NAME);

        WEB3GentradeCsvColumnModel[] columnHeader = new WEB3GentradeCsvColumnModel[12];

        //ブックビルディング状況fewCSV.部店コードラベル
        columnHeader[0] =
            new WEB3GentradeCsvColumnModel(BRANCH_CODE_LABEL, 0, WEB3GentradeCsvColumnModel.STRINGTYPE, null);

        //ブックビルディング状況fewCSV.顧客コードラベル
        columnHeader[1] =
            new WEB3GentradeCsvColumnModel(ACCOUNT_CODE_LABEL, 1, WEB3GentradeCsvColumnModel.STRINGTYPE, null);

        //ブックビルディング状況fewCSV.顧客名ラベル
        columnHeader[2] =
            new WEB3GentradeCsvColumnModel(ACCOUNT_NAME_LABEL, 2, WEB3GentradeCsvColumnModel.STRINGTYPE, null);

        //ブックビルディング状況fewCSV.新規申告日時ラベル
        columnHeader[3] =
            new WEB3GentradeCsvColumnModel(
                BOOKBUILDING_CREATED_TIMESTAMP,
                3,
                WEB3GentradeCsvColumnModel.TIMESTAMPTYPE,
                new SimpleDateFormat("yyyy/MM/dd HH:mm:ss"));

        //ブックビルディング状況fewCSV.申告数量ラベル
        columnHeader[4] =
            new WEB3GentradeCsvColumnModel(ORDER_QUANTITY_LABEL, 4, WEB3GentradeCsvColumnModel.DOUBLETYPE, null);

        //ブックビルディング状況fewCSV.申告価格ラベル
        columnHeader[5] =
            new WEB3GentradeCsvColumnModel(ORDER_PRICE_LABEL, 5, WEB3GentradeCsvColumnModel.DOUBLETYPE, null);

        //ブックビルディング状況fewCSV.指値／成行ラベル
        columnHeader[6] =
            new WEB3GentradeCsvColumnModel(
                LIMIT_PRICE_MARKET_ORDER_LABEL,
                6,
                WEB3GentradeCsvColumnModel.STRINGTYPE,
                null);

        //ブックビルディング状況fewCSV.基準値ラベル
        columnHeader[7] =
            new WEB3GentradeCsvColumnModel(BASE_PRICE_LABEL, 7, WEB3GentradeCsvColumnModel.DOUBLETYPE, null);

        //ブックビルディング状況fewCSV.申告相当額ラベル
        columnHeader[8] =
            new WEB3GentradeCsvColumnModel(BOOKBUILDING_PRICE_LABEL, 8, WEB3GentradeCsvColumnModel.DOUBLETYPE, null);

        //ブックビルディング状況fewCSV.出金余力ラベル
        columnHeader[9] =
            new WEB3GentradeCsvColumnModel(TRADING_POWER_LABEL, 9, WEB3GentradeCsvColumnModel.DOUBLETYPE, null);

        //ブックビルディング状況fewCSV.申告額保持者ラベル
        columnHeader[10] =
            new WEB3GentradeCsvColumnModel(TRADING_POWER_CHECK, 10, WEB3GentradeCsvColumnModel.STRINGTYPE, null);

        //ブックビルディング状況fewCSV.申告状態ラベル
        columnHeader[11] =
            new WEB3GentradeCsvColumnModel(ORDER_STATUS_LABEL, 11, WEB3GentradeCsvColumnModel.STRINGTYPE, null);

        this.setColumnHeader(columnHeader);

        log.exiting(STR_METHOD_NAME);
    }

    /**
     * (createキーヘッダ)<BR>
     * キーヘッダをセットする。<BR>
     * <BR>
     * 　@以下の通り文字列の配列を生成し、<BR>
     * setキーヘッダ()にてインスタンスにセットする。<BR>
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
     * IPO銘柄オブジェクト
     * @@roseuid 40E2587400E3
     */
    private void createKeyHeader(WEB3IpoProductImpl l_ipoProduct)
    {
        final String STR_METHOD_NAME = " createKeyHeader(WEB3IpoProductImpl)";
        log.entering(STR_METHOD_NAME);

        String[] l_strKeyHeader = new String[3];

        FinApp l_finApp = (FinApp)Services.getService(FinApp.class);
        TradingSystem l_tradingSystem = l_finApp.getTradingSystem();
        Timestamp l_processTime = l_tradingSystem.getSystemTimestamp();

        l_strKeyHeader[0] = WEB3DateUtility.formatDate(l_processTime, "yyyy/MM/dd HH:mm:ss");
        l_strKeyHeader[1] = ((IpoProductRow)(l_ipoProduct.getDataSourceObject())).getProductCode();
        l_strKeyHeader[2] = ((IpoProductRow)(l_ipoProduct.getDataSourceObject())).getStandardName();

        setKeyHeader(l_strKeyHeader);

        log.exiting(STR_METHOD_NAME);
    }

    /**
     * (set部店コード)<BR>
     * 部店コードをセットする。<BR>
     * <BR>
     * １）　@カラムモデル取得<BR>
     * 　@this.getカラムモデル()にてCSVカラムモデルを取得する。<BR>
     * <BR>
     * 　@[getカラムモデル()に指定する引数]<BR>
     * 　@項目ラベル：　@ブックビルディング状況fewCSV.部店コードラベル<BR>
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
     * @@param l_lngBranchId - (部店ＩＤ)<BR>
     * 部店ＩＤ
     * @@throws WEB3BaseException
     * @@roseuid 40E2742302F6
     */
    public void setBranchCode(int l_intLineNumber, long l_lngBranchId) throws WEB3BaseException
    {
        final String STR_METHOD_NAME = " setBranchCode(int, long)";
        log.entering(STR_METHOD_NAME);

        try
        {
            //カラムモデル取得
            WEB3GentradeCsvColumnModel l_csvDataModel = this.getColumnModel(BRANCH_CODE_LABEL);

            //部店ＩＤに該当する部店オブジェクト取得
            FinApp l_finApp = (FinApp)Services.getService(FinApp.class);
            Branch l_branch = l_finApp.getAccountManager().getBranch(l_lngBranchId);
            String l_strValue = l_branch.getBranchCode();

            //値セット
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
     * 　@項目ラベル：　@ブックビルディング状況fewCSV.顧客コードラベル<BR>
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
     * 　@項目ラベル：　@ブックビルディング状況fewCSV.顧客名ラベル<BR>
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
     * @@roseuid 40E274AF0279
     */
    public void setAccount(int l_intLineNumber, long l_lngAccountId) throws WEB3BaseException
    {
        final String STR_METHOD_NAME = " setAccount(int, long)";
        log.entering(STR_METHOD_NAME);

        try
        {
            //顧客オブジェクト取得
            FinApp l_finApp = (FinApp)Services.getService(FinApp.class);
            MainAccount l_mainAccount = l_finApp.getAccountManager().getMainAccount(l_lngAccountId);

            MainAccountRow l_mainAccountRow = (MainAccountRow)l_mainAccount.getDataSourceObject();

            //（顧客コード）カラムモデル取得
            WEB3GentradeCsvColumnModel l_csvDataModelAccountCode = this.getColumnModel(ACCOUNT_CODE_LABEL);

            //（顧客コード）値セット
            String l_strCode = l_mainAccount.getAccountCode();
            this.setValue(l_intLineNumber, l_csvDataModelAccountCode, l_strCode.substring(0, 6));

            //（顧客名）カラムモデル取得
            WEB3GentradeCsvColumnModel l_csvDataModelAccountName = this.getColumnModel(ACCOUNT_NAME_LABEL);

            //（顧客名）値セット
            String l_strName = l_mainAccountRow.getFamilyName();
            this.setValue(l_intLineNumber, l_csvDataModelAccountName, l_strName);

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
     * (set新規申告日時)<BR>
     * 新規申告日時をセットする。<BR>
     * <BR>
     * １）　@カラムモデル取得<BR>
     * 　@this.getカラムモデル()にてCSVカラムモデルを取得する。<BR>
     * <BR>
     * 　@[getカラムモデル()に指定する引数]<BR>
     * 　@項目ラベル：　@ブックビルディング状況fewCSV.新規申告日時ラベル <BR>
     * <BR>
     * ２）　@値セット<BR>
     * 　@this.set項目値()にて項目値をセットする。<BR>
     * <BR>
     * 　@[set項目値()に指定する引数]<BR>
     * 　@行番号：　@引数の行番号<BR>
     * 　@カラム：　@１）で取得したカラムモデル<BR>
     * 　@値：　@作成日時<BR>
     * <BR>
     * @@param l_intLineNumber - (行番号)<BR>
     * 行番号を指定する。
     * @@param l_tsCreatedTimestamp - (作成日時)<BR>
     * 作成日時
     * @@roseuid 40E27ACA0335
     */
    public void setBookbuildingCreatedTimestamp(int l_intLineNumber, Timestamp l_tsCreatedTimestamp)
    {
        final String STR_METHOD_NAME = " setBookbuildingCreatedTimestamp(int, Timestamp)";
        log.entering(STR_METHOD_NAME);

        //カラムモデル取得
        WEB3GentradeCsvColumnModel l_csvDataModel = this.getColumnModel(BOOKBUILDING_CREATED_TIMESTAMP);

        //値セット
        this.setValue(l_intLineNumber, l_csvDataModel, l_tsCreatedTimestamp);

        log.exiting(STR_METHOD_NAME);
    }

    /**
     * (set申告数量)<BR>
     * 申告数量をセットする。<BR>
     * <BR>
     * １）　@カラムモデル取得<BR>
     * 　@this.getカラムモデル()にてCSVカラムモデルを取得する。<BR>
     * <BR>
     * 　@[getカラムモデル()に指定する引数]<BR>
     * 　@項目ラベル：　@ブックビルディング状況fewCSV.申告数量ラベル <BR>
     * <BR>
     * ２）　@値セット<BR>
     * 　@this.set項目値()にて項目値をセットする。<BR>
     * <BR>
     * 　@[set項目値()に指定する引数]<BR>
     * 　@行番号：　@引数の行番号<BR>
     * 　@カラム：　@１）で取得したカラムモデル<BR>
     * 　@値：　@申告数量<BR>
     * <BR>
     * @@param l_intLineNumber - (行番号)<BR>
     * 行番号を指定する。
     * @@param l_dblOrderQuantity - (申告数量)<BR>
     * 申告数量
     * @@roseuid 40E27B690335
     */
    public void setOrderQuantity(int l_intLineNumber, double l_dblOrderQuantity)
    {
        final String STR_METHOD_NAME = " setOrderQuantity(int, double)";
        log.entering(STR_METHOD_NAME);

        //カラムモデル取得
        WEB3GentradeCsvColumnModel l_csvDataModel = this.getColumnModel(ORDER_QUANTITY_LABEL);

        //値セット
        this.setValue(l_intLineNumber, l_csvDataModel, WEB3StringTypeUtility.formatNumber(l_dblOrderQuantity));

        log.exiting(STR_METHOD_NAME);
    }

    /**
     * (set申告価格)<BR>
     * 申告価格をセットする。<BR>
     * <BR>
     * １）　@カラムモデル取得<BR>
     * 　@this.getカラムモデル()にてCSVカラムモデルを取得する。<BR>
     * <BR>
     * 　@[getカラムモデル()に指定する引数]<BR>
     * 　@項目ラベル：　@ブックビルディング状況fewCSV.申告価格ラベル <BR>
     * <BR>
     * ２）　@値セット<BR>
     * 　@this.set項目値()にて項目値をセットする。<BR>
     * <BR>
     * 　@[set項目値()に指定する引数]<BR>
     * 　@行番号：　@引数の行番号<BR>
     * 　@カラム：　@１）で取得したカラムモデル<BR>
     * 　@値：　@申告価格<BR>
     * <BR>
     * @@param l_intLineNumber - (行番号)<BR>
     * 行番号を指定する。
     * @@param l_dblOrderPrice - (申告価格)<BR>
     * 申告価格
     * @@roseuid 40E27B9601DD
     */
    public void setOrderPrice(int l_intLineNumber, double l_dblOrderPrice)
    {
        final String STR_METHOD_NAME = " setOrderPrice(int, double)";
        log.entering(STR_METHOD_NAME);

        //カラムモデル取得
        WEB3GentradeCsvColumnModel l_csvDataModel = this.getColumnModel(ORDER_PRICE_LABEL);

        //値セット
        this.setValue(l_intLineNumber, l_csvDataModel, WEB3StringTypeUtility.formatNumber(l_dblOrderPrice));

        log.exiting(STR_METHOD_NAME);
    }

    /**
     * (set指値／成行値)<BR>
     * 指値／成行をセットする。<BR>
     * <BR>
     * １）　@カラムモデル取得<BR>
     * 　@this.getカラムモデル()にてCSVカラムモデルを取得する。<BR>
     * <BR>
     * 　@[getカラムモデル()に指定する引数]<BR>
     * 　@項目ラベル：　@ブックビルディング状況fewCSV.指値／成行ラベル <BR>
     * <BR>
     * ２）　@値セット<BR>
     * 　@this.set項目値()にて項目値をセットする。<BR>
     * <BR>
     * 　@[set項目値()に指定する引数]<BR>
     * 　@行番号：　@引数の行番号<BR>
     * 　@カラム：　@１）で取得したカラムモデル<BR>
     * 　@値：　@<BR>
     * 　@　@（引数の指値 == 0）の場合、ブックビルディング状況fewCSV.成行表示。 <BR>
     * 　@　@以外、ブックビルディング状況fewCSV.指値表示。 <BR>
     * <BR>
     * @@param l_intLineNumber - (行番号)<BR>
     * 行番号を指定する。
     * @@param l_dblLimitPrice - (指値)<BR>
     * 指値
     * @@roseuid 40E26E790066
     */
    public void setLimitPriceMarketOrderPrice(int l_intLineNumber, double l_dblLimitPrice)
    {
        final String STR_METHOD_NAME = " setLimitPriceMarketOrderPrice(int, double)";
        log.entering(STR_METHOD_NAME);

        //カラムモデル取得
        WEB3GentradeCsvColumnModel l_csvDataModel = this.getColumnModel(LIMIT_PRICE_MARKET_ORDER_LABEL);

        //値セット
        String l_strIndication = null;
        if (l_dblLimitPrice == 0)
        {
            l_strIndication = MARKET_ORDER_INDICATION;
        }
        else
        {
            l_strIndication = LIMIT_PRICE_INDICATION;
        }

        log.debug("l_strIndication = " + l_strIndication);

        this.setValue(l_intLineNumber, l_csvDataModel, l_strIndication);

        log.exiting(STR_METHOD_NAME);
    }

    /**
     * (set基準値)<BR>
     * 基準値をセットする。<BR>
     * <BR>
     * １）　@カラムモデル取得<BR>
     * 　@this.getカラムモデル()にてCSVカラムモデルを取得する。<BR>
     * <BR>
     * 　@[getカラムモデル()に指定する引数]<BR>
     * 　@項目ラベル：　@ブックビルディング状況fewCSV.基準値ラベル <BR>
     * <BR>
     * ２）　@値セット<BR>
     * 　@this.set項目値()にて項目値をセットする。<BR>
     * <BR>
     * 　@[set項目値()に指定する引数]<BR>
     * 　@行番号：　@引数の行番号<BR>
     * 　@カラム：　@１）で取得したカラムモデル<BR>
     * 　@値：　@基準値<BR>
     * <BR>
     * @@param l_intLineNumber - (行番号)<BR>
     * 行番号を指定する。
     * @@param l_dblBasePrice - (基準値)<BR>
     * 基準値
     * @@roseuid 40EE858E02EF
     */
    public void setBasePrice(int l_intLineNumber, double l_dblBasePrice)
    {
        final String STR_METHOD_NAME = " setBasePrice(int, double)";
        log.entering(STR_METHOD_NAME);

        //カラムモデル取得
        WEB3GentradeCsvColumnModel l_csvDataModel = this.getColumnModel(BASE_PRICE_LABEL);

        //値セット
        this.setValue(l_intLineNumber, l_csvDataModel, WEB3StringTypeUtility.formatNumber(l_dblBasePrice));

        log.exiting(STR_METHOD_NAME);
    }

    /**
     * (set申告相当額)<BR>
     * 申告相当額をセットする。<BR>
     * <BR>
     * １）　@カラムモデル取得<BR>
     * 　@this.getカラムモデル()にてCSVカラムモデルを取得する。<BR>
     * <BR>
     * 　@[getカラムモデル()に指定する引数]<BR>
     * 　@項目ラベル：　@ブックビルディング状況fewCSV.申告相当額ラベル <BR>
     * <BR>
     * ２）　@値セット<BR>
     * 　@this.set項目値()にて項目値をセットする。<BR>
     * <BR>
     * 　@[set項目値()に指定する引数]<BR>
     * 　@行番号：　@引数の行番号<BR>
     * 　@カラム：　@１）で取得したカラムモデル<BR>
     * 　@値：　@申告相当額<BR>
     * <BR>
     * @@param l_intLineNumber - (行番号)<BR>
     * 行番号を指定する。
     * @@param l_dblBookbuildingPrice - (申告相当額)<BR>
     * 申告相当額
     * @@roseuid 40E27BC10112
     */
    public void setBookbuildingPrice(int l_intLineNumber, double l_dblBookbuildingPrice)
    {
        final String STR_METHOD_NAME = " setBookbuildingPrice(int, double)";
        log.entering(STR_METHOD_NAME);

        //カラムモデル取得
        WEB3GentradeCsvColumnModel l_csvDataModel = this.getColumnModel(BOOKBUILDING_PRICE_LABEL);

        //値セット
        this.setValue(l_intLineNumber, l_csvDataModel, WEB3StringTypeUtility.formatNumber(l_dblBookbuildingPrice));

        log.exiting(STR_METHOD_NAME);
    }

    /**
     * (set出金余力)<BR>
     * 出金余力をセットする。<BR>
     * <BR>
     * １）　@カラムモデル取得<BR>
     * 　@this.getカラムモデル()にてCSVカラムモデルを取得する。<BR>
     * <BR>
     * 　@[getカラムモデル()に指定する引数]<BR>
     * 　@項目ラベル：　@ブックビルディング状況fewCSV.出金余力ラベル <BR>
     * <BR>
     * ２）　@値セット<BR>
     * 　@this.set項目値()にて項目値をセットする。<BR>
     * <BR>
     * 　@[set項目値()に指定する引数]<BR>
     * 　@行番号：　@引数の行番号<BR>
     * 　@カラム：　@１）で取得したカラムモデル<BR>
     * 　@値：　@出金余力<BR>
     * <BR>
     * @@param l_intLineNumber - (行番号)<BR>
     * 行番号を指定する。
     * @@param l_dblTradingPower - (出金余力)<BR>
     * 出金余力
     * @@roseuid 40E27BE50373
     */
    public void setTradingPower(int l_intLineNumber, double l_dblTradingPower)
    {
        final String STR_METHOD_NAME = " setTradingPower(int, double)";
        log.entering(STR_METHOD_NAME);

        //カラムモデル取得
        WEB3GentradeCsvColumnModel l_csvDataModel = this.getColumnModel(TRADING_POWER_LABEL);

        //値セット
        this.setValue(l_intLineNumber, l_csvDataModel, WEB3StringTypeUtility.formatNumber(l_dblTradingPower));

        log.exiting(STR_METHOD_NAME);
    }

    /**
     * (set申告額保持者)<BR>
     * 申告額保持者をセットする。<BR>
     * <BR>
     * １）　@カラムモデル取得<BR>
     * 　@this.getカラムモデル()にてCSVカラムモデルを取得する。<BR>
     * <BR>
     * 　@[getカラムモデル()に指定する引数]<BR>
     * 　@項目ラベル：　@ブックビルディング状況fewCSV.申告額保持者ラベル <BR>
     * <BR>
     * ２）　@値セット<BR>
     * 　@this.set項目値()にて項目値をセットする。<BR>
     * <BR>
     * 　@[set項目値()に指定する引数]<BR>
     * 　@行番号：　@引数の行番号<BR>
     * 　@カラム：　@１）で取得したカラムモデル<BR>
     * 　@値：　@<BR>
     * 　@　@（申告相当額 <= 出金余力）の場合、<BR>
     * 　@　@ブックビルディング状況fewCSV.申告額保持者_OK表示。 <BR>
     * 　@　@以外、ブックビルディング状況fewCSV.申告額保持者_NG表示。 <BR>
     * <BR>
     * @@param l_intLineNumber - (行番号)<BR>
     * 行番号を指定する。
     * @@param l_dblBookbuildingPrice - (申告相当額)<BR>
     * 申告相当額
     * @@param l_dblTradingPower - (出金余力)<BR>
     * 出金余力
     * @@roseuid 40E2720103A2
     */
    public void setTradingPowerCheck(int l_intLineNumber, double l_dblBookbuildingPrice, double l_dblTradingPower)
    {
        final String STR_METHOD_NAME = " setTradingPowerCheck(int, double, double)";
        log.entering(STR_METHOD_NAME);

        //カラムモデル取得
        WEB3GentradeCsvColumnModel l_csvDataModel = this.getColumnModel(TRADING_POWER_CHECK);

        //値セット
        String l_strTradingPowerCheck = null;
        if (l_dblBookbuildingPrice <= l_dblTradingPower)
        {
            l_strTradingPowerCheck = TRADING_POWER_CHECK_OK;
        }
        else
        {
            l_strTradingPowerCheck = TRADING_POWER_CHECK_NG;
        }

        this.setValue(l_intLineNumber, l_csvDataModel, l_strTradingPowerCheck);

        log.exiting(STR_METHOD_NAME);
    }

    /**
     * (set申告状態)<BR>
     * 申告状態をセットする。<BR>
     * <BR>
     * １）　@カラムモデル取得<BR>
     * 　@this.getカラムモデル()にてCSVカラムモデルを取得する。<BR>
     * <BR>
     * 　@[getカラムモデル()に指定する引数]<BR>
     * 　@項目ラベル：　@ブックビルディング状況fewCSV.申告状態ラベル <BR>
     * <BR>
     * ２）　@値セット<BR>
     * 　@this.set項目値()にて項目値をセットする。<BR>
     * <BR>
     * 　@[set項目値()に指定する引数]<BR>
     * 　@行番号：　@引数の行番号<BR>
     * 　@カラム：　@１）で取得したカラムモデル<BR>
     * 　@値：　@<BR>
     * 　@（ブックビルディング申告状態 == OrderStatusEnum.ORDERED）の場合、<BR>
     * 　@ブックビルディング状況fewCSV.申告状態_新規表示。<BR>
     * 　@（ブックビルディング申告状態 == OrderStatusEnum.MODIFIED）の場合、<BR>
     * 　@ブックビルディング状況fewCSV.申告状態_訂正表示。 <BR>
     * 　@（ブックビルディング申告状態 == OrderStatusEnum.CANCELLED）の場合、<BR>
     * 　@ブックビルディング状況fewCSV.申告状態_取消表示。　@<BR>
     * <BR>
     * @@param l_intLineNumber - (行番号)<BR>
     * 行番号を指定する。
     * @@param l_bookbuildingOrderStatus - (ブックビルディング申告状態)<BR>
     * ブックビルディング申告状態
     * @@roseuid 40EE84BF01A7
     */
    public void setOrderStatus(int l_intLineNumber, OrderStatusEnum l_bookbuildingOrderStatus)
    {
        final String STR_METHOD_NAME = " setOrderStatus(int, OrderStatusEnum)";
        log.entering(STR_METHOD_NAME);

        if (l_bookbuildingOrderStatus == null)
        {
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BaseRuntimeException(WEB3ErrorCatalog.SYSTEM_ERROR_80017,
                this.getClass().getName() + STR_METHOD_NAME);
        }

        //カラムモデル取得
        WEB3GentradeCsvColumnModel l_csvDataModel = this.getColumnModel(ORDER_STATUS_LABEL);

        //値セット
        String l_strOrderStatus = null;

        if ((OrderStatusEnum.ORDERED).equals(l_bookbuildingOrderStatus))
        {
            l_strOrderStatus = ORDER_STATUS_ORDERED;
        }
        else if ((OrderStatusEnum.MODIFIED).equals(l_bookbuildingOrderStatus))
        {
            l_strOrderStatus = ORDER_STATUS_MODIFIED;
        }
        else if ((OrderStatusEnum.CANCELLED).equals(l_bookbuildingOrderStatus))
        {
            l_strOrderStatus = ORDER_STATUS_CANCELLED;
        }

        this.setValue(l_intLineNumber, l_csvDataModel, l_strOrderStatus);

        log.exiting(STR_METHOD_NAME);
    }

    /**
     * (set出金余力－余力表示なし)<BR>
     * 項目「出金余力」に"－"（＝余力表示なし）をセットする。<BR>
     * <BR>
     * 　@this.set項目値()にて項目値をセットする。<BR>
     * <BR>
     * 　@[set項目値()に指定する引数]<BR>
     * 　@行番号：　@引数.行番号<BR>
     * 　@カラム：　@出金余力のカラムモデル<BR>
     * 　@値　@　@：　@"－"（ハイフン）をセット<BR>
     * <BR>
     * @@param l_intLineNumber - (行番号)<BR>
     * 行番号を指定する。
     */
    public void setTradingPowerWithoutIndicate(int l_intLineNumber)
    {
        final String STR_METHOD_NAME = " setTradingPowerWithoutIndicate(int)";
        log.entering(STR_METHOD_NAME);

        //カラムモデル取得
        WEB3GentradeCsvColumnModel l_csvDataModel = this.getColumnModel(TRADING_POWER_LABEL);

        this.setValue(l_intLineNumber, l_csvDataModel, TRADING_POWER_HYPHEN);

        log.exiting(STR_METHOD_NAME);
    }

    /**
     * (set申告額保持者－余力表示なし)<BR>
     * 項目「申告額保持者」に"－"（＝余力表示なし）をセットする。<BR>
     * <BR>
     * 　@this.set項目値()にて項目値をセットする。<BR>
     * <BR>
     * 　@[set項目値()に指定する引数]<BR>
     * 　@行番号：　@引数.行番号<BR>
     * 　@カラム：　@申告額保持者のカラムモデル<BR>
     * 　@値　@　@：　@"－"（ハイフン）をセット<BR>
     * <BR>
     * @@param l_intLineNumber - (行番号)<BR>
     * 行番号を指定する。
     */
    public void setTradingPowerCheckWithoutIndicate(int l_intLineNumber)
    {
        final String STR_METHOD_NAME = " setTradingPowerCheckWithoutIndicate(int)";
        log.entering(STR_METHOD_NAME);

        //カラムモデル取得
        WEB3GentradeCsvColumnModel l_csvDataModel = this.getColumnModel(TRADING_POWER_CHECK);

        this.setValue(l_intLineNumber, l_csvDataModel, TRADING_POWER_HYPHEN);

        log.exiting(STR_METHOD_NAME);
    }
}
@
