head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.15.05.44.18;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	6f44d7efa104045;
filename	WEB3AdminIpoInvalidOperationFewCsv.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : 無効オペレーション履歴fewCSV(WEB3AdminIpoInvalidOperationFewCsv.java)
Author Name      : Daiwa Institute of Research
Revision History : 2006/11/09 齊珂 (中訊) 新規作成　@仕様変更・モデル161
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
 * (ブックビルディング状況ダウンロードで作成する )<BR>
 * 無効オペレーション履歴CSVファ@イルデータクラス <BR>
 * （扱者コードなし）<BR>
 *
 * @@author 齊珂
 * @@version 1.0
 */
public class WEB3AdminIpoInvalidOperationFewCsv extends WEB3GentradeCsvDataModel
{
    /**
     * ログ出力ユーティリティ。<BR>
     */
    private static WEB3LogUtility log = WEB3LogUtility.getInstance(WEB3AdminIpoInvalidOperationFewCsv.class);

    /**
     * (部店コードラベル)<BR>
     * 定数定義プロパティ　@”部店コード”
     */
    public static final String BRANCH_CODE_LABEL = "部店コード";

    /**
     * (顧客コードラベル)<BR>
     * 定数定義プロパティ　@”顧客コード”
     */
    public static final String ACCOUNT_CODE_LABEL = "顧客コード";

    /**
     * (顧客名ラベル)<BR>
     * 定数定義プロパティ　@”顧客名”
     */
    public static final String ACCOUNT_NAME_LABEL = "顧客名";

    /**
     * (受付日時ラベル)<BR>
     * 定数定義プロパティ　@”受付日時”
     */
    public static final String RECEPTION_DATE_LABEL = "受付日時";

    /**
     * (処理内容ラベル)<BR>
     * 定数定義プロパティ　@”処理内容”
     */
    public static final String PROCESSING_LABEL = "処理内容";

    /**
     * (申告数量ラベル)<BR>
     * 定数定義プロパティ　@”申告数量”
     */
    public static final String ORDER_QUNTITY_LABEL = "申告数量";

    /**
     * (申告価格ラベル)<BR>
     * 定数定義プロパティ　@”申告価格”
     */
    public static final String ORDER_PRICE_LABEL = "申告価格";

    /**
     * (指値／成行ラベル)<BR>
     * 定数定義プロパティ　@”指値／成行”
     */
    public static final String LIMIT_PRICE_MARKET_ORDER_LABEL = "指値／成行";

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
     * (処理内容_新規表示)<BR>
     * 定数定義プロパティ　@処理内容_新規表示
     */
    public static final String PROCESSING_ORDERED_INDICATION = "新規";

    /**
     * (処理内容_訂正表示)<BR>
     * 定数定義プロパティ　@処理内容_訂正表示
     */
    public static final String PROCESSING_CHANGE_INDICATION = "訂正";

    /**
     * (処理内容_取消表示)<BR>
     * 定数定義プロパティ　@処理内容_取消表示
     */
    public static final String PROCESSING_CANCEL_INDICATION = "取消";

    /**
     * コンストラクタ。<BR>
     * <BR>
     * インスタンスを生成し、ヘッダ情報をセットする。<BR>
     * 　@－super()にてインスタンスを生成する。<BR>
     * 　@－createキーヘッダ(IPO銘柄)をコールし、キーヘッダ情報を作成する。<BR>
     * 　@－createカラムヘッダ()をコールし、カラムヘッダ情報を作成する。<BR>
     * @@param l_ipoProduct - (IPO銘柄)<BR>
     * IPO銘柄オブジェクト<BR>
     * @@roseuid 40F21E4701F5
     */
    public WEB3AdminIpoInvalidOperationFewCsv(WEB3IpoProductImpl l_ipoProduct) throws WEB3BaseException
    {
        //super()にてインスタンスを生成する。
        super();

        final String STR_METHOD_NAME = " WEB3AdminIpoInvalidOperationFewCsv(WEB3IpoProductImpl)";
        log.entering(STR_METHOD_NAME);

        //createキーヘッダ(IPO銘柄)をコールし、キーヘッダ情報を作成する。
        this.createKeyHeader(l_ipoProduct);

        //createカラムヘッダ()をコールし、カラムヘッダ情報を作成する。
        this.createColumnHeader();

        log.exiting(STR_METHOD_NAME);
    }

    /**
     * (createカラムヘッダ)<BR>
     * カラムヘッダをセットする。<BR>
     * <BR>
     * 　@以下の通りCSVカラムモデルの配列を生成し、<BR>
     * setカラムヘッダ()にてインスタンスにセットする。<BR>
     * <BR>
     * [カラムヘッダ配列]<BR>
     * <BR>
     * －　@index = 0<BR>
     * 　@[CSVカラムモデル コンストラクタの引数]<BR>
     * 　@項目ラベル：　@無効オペレーション履歴fewCSV.部店コードラベル<BR>
     * 　@カラム番号： 0<BR>
     * 　@項目型：　@CSVカラムモデル.項目型_文字列<BR>
     * 　@日付フォーマット：　@null<BR>
     * <BR>
     * －　@index = 1<BR>
     * 　@[CSVカラムモデル コンストラクタの引数]<BR>
     * 　@項目ラベル：　@無効オペレーション履歴fewCSV.顧客コードラベル<BR>
     * 　@カラム番号： 1<BR>
     * 　@項目型：　@CSVカラムモデル.項目型_文字列<BR>
     * 　@日付フォーマット：　@null<BR>
     * <BR>
     * －　@index = 2<BR>
     * 　@[CSVカラムモデル コンストラクタの引数]<BR>
     * 　@項目ラベル：　@無効オペレーション履歴fewCSV.顧客名ラベル<BR>
     * 　@カラム番号： 2<BR>
     * 　@項目型：　@CSVカラムモデル.項目型_文字列<BR>
     * 　@日付フォーマット：　@null<BR>
     * <BR>
     * －　@index = 3<BR>
     * 　@[CSVカラムモデル コンストラクタの引数]<BR>
     * 　@項目ラベル：　@無効オペレーション履歴fewCSV.受付日時ラベル<BR>
     * 　@カラム番号： 3<BR>
     * 　@項目型：　@CSVカラムモデル.項目型_日付時間<BR>
     * 　@日付フォーマット：　@<BR>
     * 　@　@new SimpleDateFormat("yyyy/MM/dd HH:mm:ss")<BR>
     * <BR>
     * －　@index = 4<BR>
     * 　@[CSVカラムモデル コンストラクタの引数]<BR>
     * 　@項目ラベル：　@無効オペレーション履歴fewCSV.処理内容ラベル<BR>
     * 　@カラム番号： 4<BR>
     * 　@項目型：　@CSVカラムモデル.項目型_文字列<BR>
     * 　@日付フォーマット：　@null<BR>
     * <BR>
     * －　@index = 5<BR>
     * 　@[CSVカラムモデル コンストラクタの引数]<BR>
     * 　@項目ラベル：　@無効オペレーション履歴fewCSV.申告数量ラベル<BR>
     * 　@カラム番号： 5<BR>
     * 　@項目型：　@CSVカラムモデル.項目型_数値（double）<BR>
     * 　@日付フォーマット：　@null<BR>
     * <BR>
     * －　@index = 6<BR>
     * 　@[CSVカラムモデル コンストラクタの引数]<BR>
     * 　@項目ラベル：　@無効オペレーション履歴fewCSV.申告価格ラベル<BR>
     * 　@カラム番号： 6<BR>
     * 　@項目型：　@CSVカラムモデル.項目型_数値（double）<BR>
     * 　@日付フォーマット：　@null<BR>
     * <BR>
     * －　@index = 7<BR>
     * 　@[CSVカラムモデル コンストラクタの引数]<BR>
     * 　@項目ラベル：　@無効オペレーション履歴fewCSV.指値／成行ラベル<BR>
     * 　@カラム番号： 7<BR>
     * 　@項目型：　@CSVカラムモデル.項目型_文字列<BR>
     * 　@日付フォーマット：　@null<BR>
     * <BR>
     * @@roseuid 40F21E4701F7
     */
    private void createColumnHeader()
    {
        final String STR_METHOD_NAME = " createColumnHeader()";
        log.entering(STR_METHOD_NAME);

        WEB3GentradeCsvColumnModel[] l_columnHeader = new WEB3GentradeCsvColumnModel[8];

        //無効オペレーション履歴fewCSV.部店コードラベル
        l_columnHeader[0] =
            new WEB3GentradeCsvColumnModel(BRANCH_CODE_LABEL, 0, WEB3GentradeCsvColumnModel.STRINGTYPE, null);

        //無効オペレーション履歴fewCSV.顧客コードラベル
        l_columnHeader[1] =
            new WEB3GentradeCsvColumnModel(ACCOUNT_CODE_LABEL, 1, WEB3GentradeCsvColumnModel.STRINGTYPE, null);

        //無効オペレーション履歴fewCSV.顧客名ラベル
        l_columnHeader[2] =
            new WEB3GentradeCsvColumnModel(ACCOUNT_NAME_LABEL, 2, WEB3GentradeCsvColumnModel.STRINGTYPE, null);

        //無効オペレーション履歴fewCSV.受付日時ラベル
        l_columnHeader[3] =
            new WEB3GentradeCsvColumnModel(
                RECEPTION_DATE_LABEL,
                3,
                WEB3GentradeCsvColumnModel.TIMESTAMPTYPE,
                new SimpleDateFormat("yyyy/MM/dd HH:mm:ss"));

        //無効オペレーション履歴fewCSV.処理内容ラベル
        l_columnHeader[4] =
            new WEB3GentradeCsvColumnModel(PROCESSING_LABEL, 4, WEB3GentradeCsvColumnModel.STRINGTYPE, null);

        //無効オペレーション履歴fewCSV.申告数量ラベル
        l_columnHeader[5] =
            new WEB3GentradeCsvColumnModel(ORDER_QUNTITY_LABEL, 5, WEB3GentradeCsvColumnModel.DOUBLETYPE, null);

        //無効オペレーション履歴fewCSV.申告価格ラベル
        l_columnHeader[6] =
            new WEB3GentradeCsvColumnModel(ORDER_PRICE_LABEL, 6, WEB3GentradeCsvColumnModel.DOUBLETYPE, null);

        //無効オペレーション履歴fewCSV.指値／成行ラベル
        l_columnHeader[7] =
            new WEB3GentradeCsvColumnModel(
                LIMIT_PRICE_MARKET_ORDER_LABEL,
                7,
                WEB3GentradeCsvColumnModel.STRINGTYPE,
                null);

        setColumnHeader(l_columnHeader);

        log.exiting(STR_METHOD_NAME);
    }

    /**
     * (createキーヘッダ)<BR>
     * キーヘッダをセットする。<BR>
     * <BR>
     * 　@以下の通り文字列の配列を生成し、<BR>
     * 　@setキーヘッダ()にてインスタンスにセットする。<BR>
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
     * @@roseuid 40F21E4701F8
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
     * 　@項目ラベル：　@無効オペレーション履歴fewCSV.部店コードラベル <BR>
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
     * 部店ＩＤ<BR>
     * @@throws WEB3BaseException
     * @@roseuid 40F21E4701FA
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
            String l_strCode = l_branch.getBranchCode();

            //値セット
            this.setValue(l_intLineNumber, l_csvDataModel, l_strCode);
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
     * 　@項目ラベル：　@無効オペレーション履歴fewCSV顧客コードラベル<BR>
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
     * 　@項目ラベル：　@無効オペレーション履歴fewCSV顧客名ラベル<BR>
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
     * 行番号を指定する。<BR>
     * @@param l_lngAccountId - (口座ＩＤ)<BR>
     * 口座ＩＤ<BR>
     * @@throws WEB3BaseException
     * @@roseuid 40F21E470205
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
            this.setValue(l_intLineNumber, l_csvDataModelAccountCode, l_strCode.substring(0,6));

            //（顧客名）カラムモデル取得
            WEB3GentradeCsvColumnModel l_csvDataModelAccountName = this.getColumnModel(ACCOUNT_NAME_LABEL);

            //（顧客名）値セット
            String l_strName = l_mainAccountRow.getFamilyName();
            this.setValue(l_intLineNumber, l_csvDataModelAccountName, l_strName);

        }
        catch (NotFoundException l_ex)
        {
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(WEB3ErrorCatalog.SYSTEM_ERROR_80006,
                this.getClass().getName() + STR_METHOD_NAME, l_ex.getMessage(), l_ex);
        }

        log.exiting(STR_METHOD_NAME);
    }

    /**
     * (set受付日時)<BR>
     * 受付日時をセットする。<BR>
     * <BR>
     * １）　@カラムモデル取得<BR>
     * 　@this.getカラムモデル()にてCSVカラムモデルを取得する。<BR>
     * <BR>
     * 　@[getカラムモデル()に指定する引数]<BR>
     * 　@項目ラベル：　@無効オペレーション履歴fewCSV受付日時ラベル<BR>
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
     * 行番号を指定する。<BR>
     * @@param l_tsCreatedTimestamp - (作成日時)<BR>
     * 作成日時<BR>
     * @@roseuid 40F21E470208
     */
    public void setReceptionDate(int l_intLineNumber, Timestamp l_tsCreatedTimestamp)
    {
        final String STR_METHOD_NAME = " setReceptionDate(int, Timestamp)";
        log.entering(STR_METHOD_NAME);

        //カラムモデル取得
        WEB3GentradeCsvColumnModel l_csvDataModel = this.getColumnModel(RECEPTION_DATE_LABEL);

        //値セット
        this.setValue(l_intLineNumber, l_csvDataModel, l_tsCreatedTimestamp);

        log.exiting(STR_METHOD_NAME);
    }

    /**
     * (set処理内容)<BR>
     * 処理内容をセットする。<BR>
     * <BR>
     * １）　@カラムモデル取得<BR>
     * 　@this.getカラムモデル()にてCSVカラムモデルを取得する。<BR>
     * <BR>
     * 　@[getカラムモデル()に指定する引数]<BR>
     * 　@項目ラベル：　@無効オペレーション履歴fewCSV処理内容ラベル<BR>
     * <BR>
     * ２）　@値セット<BR>
     * 　@this.set項目値()にて項目値をセットする。<BR>
     * <BR>
     * 　@[set項目値()に指定する引数]<BR>
     * 　@行番号：　@引数の行番号<BR>
     * 　@カラム：　@１）で取得したカラムモデル<BR>
     * 　@値：　@<BR>
     * 　@　@（ブックビルディング申告状態 == 
     * OrderStatusEnum.ORDERED）の場合、無効オペレーション履歴fewCSV申告状態_新規表示。<BR>
     * 　@　@（ブックビルディング申告状態 == 
     * OrderStatusEnum.MODIFIED）の場合、無効オペレーション履歴fewCSV申告状態_訂正表示。<BR>
     * 　@　@（ブックビルディング申告状態 == 
     * OrderStatusEnum.CANCELLED）の場合、無効オペレーション履歴fewCSV申告状態_取消表示。<BR>
     * <BR>
     * @@param l_intLineNumber - (行番号)<BR>
     * 行番号を指定する。<BR>
     * @@param l_orderStatus - (ブックビルディング申告状態)<BR>
     * ブックビルディング申告状態<BR>
     * @@roseuid 40F21EF0031E
     */
    public void setProcessing(int l_intLineNumber, OrderStatusEnum l_orderStatus)
    {
        final String STR_METHOD_NAME = " setProcessing(int, OrderStatusEnum)";
        log.entering(STR_METHOD_NAME);

        if (l_orderStatus == null)
        {
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BaseRuntimeException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80017,
                this.getClass().getName() + STR_METHOD_NAME);
        }

        //カラムモデル取得
        WEB3GentradeCsvColumnModel l_csvDataModel = this.getColumnModel(PROCESSING_LABEL);

        //値セット
        String l_strProcessing = null;

        if ((OrderStatusEnum.ORDERED).equals(l_orderStatus))
        {
            l_strProcessing = PROCESSING_ORDERED_INDICATION;
        }
        else if ((OrderStatusEnum.MODIFIED).equals(l_orderStatus))
        {
            l_strProcessing = PROCESSING_CHANGE_INDICATION;
        }
        else if ((OrderStatusEnum.CANCELLED).equals(l_orderStatus))
        {
            l_strProcessing = PROCESSING_CANCEL_INDICATION;
        }

        this.setValue(l_intLineNumber, l_csvDataModel, l_strProcessing);

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
     * 　@項目ラベル：　@無効オペレーション履歴fewCSV申告数量ラベル<BR>
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
     * 行番号を指定する。<BR>
     * @@param l_dblOrderQuantity - (申告数量)<BR>
     * 申告数量 <BR>
     * @@roseuid 40F21E47020B
     */
    public void setOrderQuantity(int l_intLineNumber, double l_dblOrderQuantity)
    {
        final String STR_METHOD_NAME = " setOrderQuantity(int, double)";
        log.entering(STR_METHOD_NAME);

        //カラムモデル取得
        WEB3GentradeCsvColumnModel l_csvDataModel = this.getColumnModel(ORDER_QUNTITY_LABEL);

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
     * 　@項目ラベル：　@無効オペレーション履歴fewCSV申告価格ラベル<BR>
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
     * 行番号を指定する。<BR>
     * @@param l_dblOrderPrice - (申告価格)<BR>
     * 申告価格<BR>
     * @@roseuid 40F21E47020E
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
     * 　@項目ラベル：　@無効オペレーション履歴fewCSV指値／成行ラベル<BR>
     * <BR>
     * ２）　@値セット<BR>
     * 　@this.set項目値()にて項目値をセットする。<BR>
     * <BR>
     * 　@[set項目値()に指定する引数]<BR>
     * 　@行番号：　@引数の行番号<BR>
     * 　@カラム：　@１）で取得したカラムモデル<BR>
     * 　@値：　@<BR>
     * 　@　@（引数の指値 == 0）の場合、無効オペレーション履歴fewCSV成行表示。<BR>
     * 　@　@以外、無効オペレーション履歴fewCSV指値表示。<BR>
     * <BR>
     * @@param l_intLineNumber - (行番号)<BR>
     * 行番号を指定する。<BR>
     * @@param l_dblLimitPrice - (指値)<BR>
     * 指値。<BR>
     * @@roseuid 40F21E470211
     */
    public void setLimitPriceMarketOrder(int l_intLineNumber, double l_dblLimitPrice)
    {
        final String STR_METHOD_NAME = " setLimitPriceMarketOrder(int, double)";
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

        this.setValue(l_intLineNumber, l_csvDataModel, l_strIndication);

        log.exiting(STR_METHOD_NAME);
    }
}
@
