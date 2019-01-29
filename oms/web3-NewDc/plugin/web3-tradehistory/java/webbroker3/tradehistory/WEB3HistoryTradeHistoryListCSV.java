head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.15.01.03.14;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	86c4d7eb25155fb;
filename	WEB3HistoryTradeHistoryListCSV.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : 取引履歴一覧CSV(WEB3HistoryTradeHistoryListCSV.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2005/11/30 凌建平(中訊) 新規作成
                   2006/01/23 李志強(日本中訊) 仕様変更・モデル042
                   2006/01/23 李志強(日本中訊) 仕様変更・モデル043
*/

package webbroker3.tradehistory;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import com.fitechlabs.xtrade.kernel.data.DataFindException;
import com.fitechlabs.xtrade.kernel.data.DataNetworkException;
import com.fitechlabs.xtrade.kernel.data.DataQueryException;
import com.fitechlabs.xtrade.kernel.data.Processors;
import com.fitechlabs.xtrade.kernel.data.QueryProcessor;
import com.fitechlabs.xtrade.plugin.tc.gentrade.GtlUtils;

import webbroker3.common.WEB3BaseException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.common.WEB3SystemLayerException;
import webbroker3.common.define.WEB3AccountDivDef;
import webbroker3.gentrade.WEB3GentradeCsvColumnModel;
import webbroker3.gentrade.WEB3GentradeCsvDataModel;
import webbroker3.gentrade.data.CodeTranslationRow;
import webbroker3.tradehistory.define.WEB3PlsBvsProductCodeDef;
import webbroker3.tradehistory.define.WEB3TradeHistoryRemarkCodeDef;
import webbroker3.tradehistory.define.WEB3TradeHistoryTradeCodeDef;
import webbroker3.util.WEB3DateUtility;
import webbroker3.util.WEB3LogUtility;

/**
 * (取引履歴一覧CSV)<BR>
 * 取引履歴一覧CSV<BR>
 *
 * @@author 凌建平
 * @@version 1.0
 */
public class WEB3HistoryTradeHistoryListCSV extends WEB3GentradeCsvDataModel 
{
    
    /**
     * ログ出力ユーティリティ。
     */
    private static WEB3LogUtility log =
        WEB3LogUtility.getInstance(
            WEB3HistoryTradeHistoryListCSV.class);
    
    /**
     * (受渡日ラベル)<BR>
     * 受渡日ラベル<BR>
     */
    private static  String DELIVERY_DATE_LABEL = "受渡日";
    
    /**
     * (約定日ラベル)<BR>
     * 約定日ラベル<BR>
     */
    private static  String EXECUTION_DATE_LABEL = "約定日";
    
    /**
     * (商品コード名称ラベル)<BR>
     * 商品コード名称ラベル<BR>
     */
    private static  String COMMODITY_NAME_LABEL = "商品";
    
    /**
     * (銘柄コードラベル)<BR>
     * 銘柄コードラベル<BR>
     */
    private static  String PRODUCT_CODE_LABEL = "銘柄コード";
    
    /**
     * (銘柄摘要名ラベル)<BR>
     * 銘柄摘要名ラベル<BR>
     */
    private static  String PRODUCT_NAME_LABEL = "銘柄摘要";
    
    /**
     * (口座区分名称ラベル)<BR>
     * 口座区分名称ラベル<BR>
     */
    private static  String ACCOUNT_TYPE_LABEL = "口座";
    
    /**
     * (翻訳摘要名ラベル)<BR>
     * 翻訳摘要名ラベル<BR>
     */
    private static  String REMARK_NAME_LABEL = "取引";
    
    /**
     * (数量ラベル)<BR>
     * 数量ラベル<BR>
     */
    private static  String QUANTITY_LABEL = "数量";
    
    /**
     * (単価ラベル)<BR>
     * 単価ラベル<BR>
     */
    private static  String PRICE_LABEL = "単価";
    
    /**
     * (通貨単位名称ラベル)<BR>
     * 通貨単位名称ラベル<BR>
     */
    private static  String MONETARY_UNIT_LABEL = "通貨";
    
    /**
     * (受渡金額ラベル)<BR>
     * 受渡金額ラベル<BR>
     */
    private static  String NET_AMOUNT_LABEL = "受渡金額";
    
    /**
     * (譲渡損益ラベル)<BR>
     * 譲渡損益ラベル<BR>
     */
    private static  String CAPITAL_GAIN_LABEL = "譲渡損益";
    
    /**
     * (口座残高ラベル)<BR>
     * 口座残高ラベル<BR>
     */
    private static String ACCOUNT_BALANCE_LABEL = "口座残高";
    
    /**
     * (口座_一般表示)<BR>
     * 口座_一般表示<BR>
     */
    private static String ACCOUNT_NORMAL_LABEL = "一般";
    
    /**
     * (口座_特定表示)<BR>
     * 口座_特定表示<BR>
     */
    private static String ACCOUNT_SPECIAL_LABEL = "特定";

    /**
     * (wk通貨単位)<BR>
     * wk通貨単位<BR>
     * "T0"（円）<BR>
     */
    private static String WK_MONETARY_UNIT_TO = "T0";

    /**
     * (wkコード種別)<BR>
     * wkコード種別<BR>
     * "th_repdv"（弁済区分）<BR>
     */
    private static String WK_CODE_TYPE_REPDV = "th_repdv";

    /**
     * (wkコード種別)<BR>
     * wkコード種別<BR>
     * "th_cmcd"（商品コード）<BR>
     */
    private static String WK_CODE_TYPE_CMCD = "th_cmcd";

    /**
     * (コード種別)<BR>
     * コード種別<BR>
     * "th_exchg"<BR>
     */
    private static String CODE_TYPE_EXCHG = "th_exchg";

    /**
     * コンストラクタ。<BR>
     * <BR>
     * インスタンスを生成し、ヘッダ情報をセットする。<BR>
     * 　@－super()にてインスタンスを生成する。<BR>
     * 　@－createキーヘッダ()をコールし、キーヘッダ情報を作成する。<BR>
     * 　@－createカラムヘッダ()をコールし、カラムヘッダ情報を作成する。<BR>
     * @@roseuid 429FE01C0243
     */
    public WEB3HistoryTradeHistoryListCSV() 
    {
        super();
        this.createKeyHeader();
        this.createColumnHeader();     
    }
    
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
     * 　@項目ラベル：　@取引履歴一覧CSV.受渡日ラベル<BR>
     * 　@カラム番号： 0<BR>
     * 　@項目型：　@CSVカラムモデル.項目型_日付型<BR>
     * 　@日付フォーマット：<BR>
     * 　@　@new SimpleDateFormat("yyyy/MM/dd")<BR>
     * <BR>
     * －　@index = 1<BR>
     * 　@[CSVカラムモデル コンストラクタの引数]<BR>
     * 　@項目ラベル：　@取引履歴一覧CSV.約定日ラベル<BR>
     * 　@カラム番号： 1<BR>
     * 　@項目型：　@CSVカラムモデル.項目型_日付型<BR>
     * 　@日付フォーマット：<BR>
     * 　@　@new SimpleDateFormat("yyyy/MM/dd")<BR>
     * <BR>
     * －　@index = 2<BR>
     * 　@[CSVカラムモデル コンストラクタの引数]<BR>
     * 　@項目ラベル：　@取引履歴一覧CSV.商品コード名称ラベル<BR>
     * 　@カラム番号： 2<BR>
     * 　@項目型：　@CSVカラムモデル.項目型_文字列<BR>
     * 　@日付フォーマット：　@null<BR>
     * <BR>
     * －　@index = 3<BR>
     * 　@[CSVカラムモデル コンストラクタの引数]<BR>
     * 　@項目ラベル：　@取引履歴一覧CSV.銘柄コードラベル<BR>
     * 　@カラム番号： 3<BR>
     * 　@項目型：　@CSVカラムモデル.項目型_文字列<BR>
     * 　@日付フォーマット：　@null<BR>
     * <BR>
     * －　@index = 4<BR>
     * 　@[CSVカラムモデル コンストラクタの引数]<BR>
     * 　@項目ラベル：　@取引履歴一覧CSV.銘柄摘要名ラベル<BR>
     * 　@カラム番号： 4<BR>
     * 　@項目型：　@CSVカラムモデル.項目型_文字列<BR>
     * 　@日付フォーマット：　@null<BR>
     * <BR>
     * －　@index = 5<BR>
     * 　@[CSVカラムモデル コンストラクタの引数]<BR>
     * 　@項目ラベル：　@取引履歴一覧CSV.口座区分名称ラベル<BR>
     * 　@カラム番号： 5<BR>
     * 　@項目型：　@CSVカラムモデル.項目型_文字列<BR>
     * 　@日付フォーマット：　@null<BR>
     * <BR>
     * －　@index = 6<BR>
     * 　@[CSVカラムモデル コンストラクタの引数]<BR>
     * 　@項目ラベル：　@取引履歴一覧CSV.翻訳摘要名ラベル<BR>
     * 　@カラム番号： 6<BR>
     * 　@項目型：　@CSVカラムモデル.項目型_文字列<BR>
     * 　@日付フォーマット：　@null<BR>
     * <BR>
     * －　@index = 7<BR>
     * 　@[CSVカラムモデル コンストラクタの引数]<BR>
     * 　@項目ラベル：　@取引履歴一覧CSV.数量ラベル<BR>
     * 　@カラム番号： 7<BR>
     * 　@項目型：　@CSVカラムモデル.項目型_文字列<BR>
     * 　@日付フォーマット：　@null<BR>
     * <BR>
     * －　@index = 8<BR>
     * 　@[CSVカラムモデル コンストラクタの引数]<BR>
     * 　@項目ラベル：　@取引履歴一覧CSV.単価ラベル<BR>
     * 　@カラム番号： 8<BR>
     * 　@項目型：　@CSVカラムモデル.項目型_文字列<BR>
     * 　@日付フォーマット：　@null<BR>
     * <BR>
     * －　@index = 9<BR>
     * 　@[CSVカラムモデル コンストラクタの引数]<BR>
     * 　@項目ラベル：　@取引履歴一覧CSV.通貨単位ラベル<BR>
     * 　@カラム番号： 9<BR>
     * 　@項目型：　@CSVカラムモデル.項目型_文字列<BR>
     * 　@日付フォーマット：　@null<BR>
     * <BR>
     * －　@index = 10<BR>
     * 　@[CSVカラムモデル コンストラクタの引数]<BR>
     * 　@項目ラベル：　@取引履歴一覧CSV.受渡金額ラベル<BR>
     * 　@カラム番号： 10<BR>
     * 　@項目型：　@CSVカラムモデル.項目型_文字列<BR>
     * 　@日付フォーマット：　@null<BR>
     * <BR>
     * －　@index = 11<BR>
     * 　@[CSVカラムモデル コンストラクタの引数]<BR>
     * 　@項目ラベル：　@取引履歴一覧CSV.譲渡損益ラベル<BR>
     * 　@カラム番号： 11<BR>
     * 　@項目型：　@CSVカラムモデル.項目型_文字列<BR>
     * 　@日付フォーマット：　@null<BR>
     * <BR>
     * －　@index = 12<BR>
     * 　@[CSVカラムモデル コンストラクタの引数]<BR>
     * 　@項目ラベル：　@取引履歴一覧CSV.口座残高ラベル<BR>
     * 　@カラム番号： 12<BR>
     * 　@項目型：　@CSVカラムモデル.項目型_文字列<BR>
     * 　@日付フォーマット：　@null<BR>
     * @@roseuid 429FE01C0252
     */
    public void createColumnHeader() 
    {
        final String STR_METHOD_NAME = " createColumnHeader()";
        log.entering(STR_METHOD_NAME);
        
        //以下の通りCSVカラムモデルの配列を生成し、setカラムヘッダ()にてインスタンスにセットする。
        final int COLUMN_MAX = 13;
        WEB3GentradeCsvColumnModel[] l_models = new WEB3GentradeCsvColumnModel[COLUMN_MAX];

        //－　@index = 0
        //[CSVカラムモデル コンストラクタの引数]
        //  項目ラベル：　@取引履歴一覧CSV.受渡日ラベル
        //  カラム番号： 0
        //  項目型：　@CSVカラムモデル.項目型_日付型
        //  日付フォーマット：
        //  new SimpleDateFormat("yyyy/MM/dd")
        l_models[0] = new WEB3GentradeCsvColumnModel(
            WEB3HistoryTradeHistoryListCSV.DELIVERY_DATE_LABEL,
            0,
            WEB3GentradeCsvColumnModel.DATETYPE,
            new SimpleDateFormat("yyyy/MM/dd"));
        
        //－　@index = 1
        //[CSVカラムモデル コンストラクタの引数]
        //  項目ラベル：　@取引履歴一覧CSV.約定日ラベル
        //  カラム番号： 1
        //  項目型：　@CSVカラムモデル.項目型_日付型
        //  日付フォーマット：
        //  new SimpleDateFormat("yyyy/MM/dd")
        l_models[1] = new WEB3GentradeCsvColumnModel(
            WEB3HistoryTradeHistoryListCSV.EXECUTION_DATE_LABEL,
            1,
            WEB3GentradeCsvColumnModel.DATETYPE,
            new SimpleDateFormat("yyyy/MM/dd"));

        //－　@index = 2
        //[CSVカラムモデル コンストラクタの引数]
        //  項目ラベル：　@取引履歴一覧CSV.商品コード名称ラベル
        //  カラム番号： 2
        //  項目型：　@CSVカラムモデル.項目型_文字列
        //  日付フォーマット：　@null
        l_models[2] = new WEB3GentradeCsvColumnModel(
            WEB3HistoryTradeHistoryListCSV.COMMODITY_NAME_LABEL,
            2,
            WEB3GentradeCsvColumnModel.STRINGTYPE,
            null);

        //－　@index = 3
        //[CSVカラムモデル コンストラクタの引数]
        //  項目ラベル：　@取引履歴一覧CSV.銘柄コードラベル
        //  カラム番号： 3
        //  項目型：　@CSVカラムモデル.項目型_文字列
        //  日付フォーマット：　@null
        l_models[3] = new WEB3GentradeCsvColumnModel(
            WEB3HistoryTradeHistoryListCSV.PRODUCT_CODE_LABEL,
            3,
            WEB3GentradeCsvColumnModel.STRINGTYPE,
            null);
            
        //－　@index = 4
        //[CSVカラムモデル コンストラクタの引数]
        //  項目ラベル：　@取引履歴一覧CSV.銘柄摘要名ラベル
        //  カラム番号： 4
        //  項目型：　@CSVカラムモデル.項目型_文字列
        //  日付フォーマット：　@null
        l_models[4] = new WEB3GentradeCsvColumnModel(
            WEB3HistoryTradeHistoryListCSV.PRODUCT_NAME_LABEL,
            4,
            WEB3GentradeCsvColumnModel.STRINGTYPE,
            null);

        //－　@index = 5
        //[CSVカラムモデル コンストラクタの引数]
        //  項目ラベル：　@取引履歴一覧CSV.口座区分名称ラベル
        //  カラム番号： 5
        //　@項目型：　@CSVカラムモデル.項目型_文字列
        //　@日付フォーマット：　@null
        l_models[5] = new WEB3GentradeCsvColumnModel(
            WEB3HistoryTradeHistoryListCSV.ACCOUNT_TYPE_LABEL,
            5,
            WEB3GentradeCsvColumnModel.STRINGTYPE,
            null);

        //－　@index = 6
        //[CSVカラムモデル コンストラクタの引数]
        //  項目ラベル：　@取引履歴一覧CSV.翻訳摘要名ラベル
        //　@カラム番号： 6
        //　@項目型：　@CSVカラムモデル.項目型_文字列
        //　@日付フォーマット：　@null
        l_models[6] = new WEB3GentradeCsvColumnModel(
            WEB3HistoryTradeHistoryListCSV.REMARK_NAME_LABEL,
            6,
            WEB3GentradeCsvColumnModel.STRINGTYPE,
            null);

        //－　@index = 7
        //[CSVカラムモデル コンストラクタの引数]
        //  項目ラベル：　@取引履歴一覧CSV.数量ラベル
        //  カラム番号： 7
        //  項目型：　@CSVカラムモデル.項目型_文字列
        //  日付フォーマット：　@null
        l_models[7] = new WEB3GentradeCsvColumnModel(
            WEB3HistoryTradeHistoryListCSV.QUANTITY_LABEL,
            7,
            WEB3GentradeCsvColumnModel.STRINGTYPE,
            null);

        //－　@index = 8
        //[CSVカラムモデル コンストラクタの引数]
        //  項目ラベル：　@取引履歴一覧CSV.単価ラベル
        //　@カラム番号： 8
        //　@項目型：　@CSVカラムモデル.項目型_文字列
        //　@日付フォーマット：　@null
        l_models[8] = new WEB3GentradeCsvColumnModel(
            WEB3HistoryTradeHistoryListCSV.PRICE_LABEL,
            8,
            WEB3GentradeCsvColumnModel.STRINGTYPE,
            null);

        //－　@index = 9
        //[CSVカラムモデル コンストラクタの引数]
        //  項目ラベル：　@取引履歴一覧CSV.通貨単位ラベル
        //　@カラム番号： 9
        //　@項目型：　@CSVカラムモデル.項目型_文字列
        //　@日付フォーマット：　@null
        l_models[9] = new WEB3GentradeCsvColumnModel(
            WEB3HistoryTradeHistoryListCSV.MONETARY_UNIT_LABEL,
            9,
            WEB3GentradeCsvColumnModel.STRINGTYPE,
            null);

        //－　@index = 10
        //[CSVカラムモデル コンストラクタの引数]
        //  項目ラベル：　@取引履歴一覧CSV.受渡金額ラベル
        //  カラム番号： 10
        //　@項目型：　@CSVカラムモデル.項目型_文字列
        //　@日付フォーマット：　@null
        l_models[10] = new WEB3GentradeCsvColumnModel(
            WEB3HistoryTradeHistoryListCSV.NET_AMOUNT_LABEL,
            10,
            WEB3GentradeCsvColumnModel.STRINGTYPE,
            null);

        //－　@index = 11
        //[CSVカラムモデル コンストラクタの引数]
        //  項目ラベル：　@取引履歴一覧CSV.譲渡損益ラベル
        //　@カラム番号： 11
        //　@項目型：　@CSVカラムモデル.項目型_文字列
        //　@日付フォーマット：　@null
        l_models[11] = new WEB3GentradeCsvColumnModel(
            WEB3HistoryTradeHistoryListCSV.CAPITAL_GAIN_LABEL,
            11,
            WEB3GentradeCsvColumnModel.STRINGTYPE,
            null);

        //－　@index = 12
        //[CSVカラムモデル コンストラクタの引数]
        //  項目ラベル：　@取引履歴一覧CSV.口座残高ラベル
        //　@カラム番号： 12
        //　@項目型：　@CSVカラムモデル.項目型_文字列
        //　@日付フォーマット：　@null
        l_models[12] = new WEB3GentradeCsvColumnModel(
            WEB3HistoryTradeHistoryListCSV.ACCOUNT_BALANCE_LABEL,
            12,
            WEB3GentradeCsvColumnModel.STRINGTYPE,
            null);
        
        this.setColumnHeader(l_models);
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
     * @@roseuid 429FE01C0253
     */
    public void createKeyHeader() 
    {
        final String STR_METHOD_NAME = " createKeyHeader()"; 
        log.entering(STR_METHOD_NAME);
        
        //以下の通り文字列の配列を生成し、setキーヘッダ()にてインスタンスにセットする。
        final int COLUMN_MAX = 1;
        String[] l_strKeyHeader = new String[COLUMN_MAX];
        
        //－　@index = 0
        //現在日時を"yyyy/MM/dd HH:mm:ss"の形式にformatした文字列。
        l_strKeyHeader[0] = WEB3DateUtility.formatDate(
            GtlUtils.getTradingSystem().getSystemTimestamp(),
            "yyyy/MM/dd HH:mm:ss");
        
        this.setKeyHeader(l_strKeyHeader);
        log.exiting(STR_METHOD_NAME);
    }
    
    /**
     * (set受渡日)<BR>
     * 受渡日をセットする。<BR>
     * <BR>
     * １）　@カラムモデル取得<BR>
     * 　@this.getカラムモデル()にてCSVカラムモデルを取得する。<BR>
     * <BR>
     * 　@[getカラムモデル()に指定する引数]<BR>
     * 　@項目ラベル：　@取引履歴CSV.受渡日ラベル<BR>
     * <BR>
     * ２）　@値セット<BR>
     * 　@this.set項目値()にて項目値をセットする。<BR>
     * <BR>
     * 　@[set項目値()に指定する引数]<BR>
     * 　@行番号：　@引数の行番号<BR>
     * 　@カラム：　@１）で取得したカラムモデル<BR>
     * 　@値：　@パラメータ.受渡日<BR>
     * @@param l_intLineNumber - (行番号)<BR>
     * 行番号<BR>
     * @@param l_datDeliveryDate - (受渡日)<BR>
     * 受渡日<BR>
     * @@roseuid 429FE01C0253
     */
    public void setDeliveryDate(int l_intLineNumber, Date l_datDeliveryDate) 
    {
        final String STR_METHOD_NAME = " setDeliveryDate(int, Date)"; 
        log.entering(STR_METHOD_NAME);  

        //１）　@カラムモデル取得 
        // this.getカラムモデル()にてCSVカラムモデルを取得する。 
        WEB3GentradeCsvColumnModel l_columnModel = this.getColumnModel(
            WEB3HistoryTradeHistoryListCSV.DELIVERY_DATE_LABEL);

        //２）　@値セット
        //　@this.set項目値()にて項目値をセットする。
        this.setValue(
            l_intLineNumber,
            l_columnModel,
            l_datDeliveryDate);

        log.exiting(STR_METHOD_NAME);  
    }
    
    /**
     * (set約定日)<BR>
     * 約定日をセットする。<BR>
     * <BR>
     * １）　@カラムモデル取得<BR>
     * 　@this.getカラムモデル()にてCSVカラムモデルを取得する。<BR>
     * <BR>
     * 　@[getカラムモデル()に指定する引数]<BR>
     * 　@項目ラベル：　@取引履歴CSV.約定日ラベル<BR>
     * <BR>
     * ２）　@値セット<BR>
     * 　@this.set項目値()にて項目値をセットする。<BR>
     * <BR>
     * 　@[set項目値()に指定する引数]<BR>
     * 　@行番号：　@引数の行番号<BR>
     * 　@カラム：　@１）で取得したカラムモデル<BR>
     * 　@値：　@パラメータ.約定日<BR>
     * @@param l_intLineNumber - (行番号)<BR>
     * 行番号<BR>
     * @@param l_datExecutionDate - (約定日)<BR>
     * 約定日<BR>
     * @@roseuid 42C4E176008F
     */
    public void setExecutionDate(int l_intLineNumber, Date l_datExecutionDate) 
    {
        final String STR_METHOD_NAME = " setExecutionDate(int, Date)"; 
        log.entering(STR_METHOD_NAME);  

        //１）　@カラムモデル取得 
        // this.getカラムモデル()にてCSVカラムモデルを取得する。 
        WEB3GentradeCsvColumnModel l_columnModel = this.getColumnModel(
            WEB3HistoryTradeHistoryListCSV.EXECUTION_DATE_LABEL);

        //２）　@値セット
        //　@this.set項目値()にて項目値をセットする。
        this.setValue(
            l_intLineNumber,
            l_columnModel,
            l_datExecutionDate);

        log.exiting(STR_METHOD_NAME);  
    }
    
    /**
     * (set商品コード名称)<BR>
     * 商品コード名称をセットする。<BR>
     * <BR>
     * １）　@カラムモデル取得<BR>
     * 　@this.getカラムモデル()にてCSVカラムモデルを取得する。<BR>
     * <BR>
     * 　@[getカラムモデル()に指定する引数]<BR>
     * 　@項目ラベル：　@取引履歴CSV.商品コード名称ラベル<BR>
     * <BR>
     * ２）　@検索条件値の設定<BR>
     * <BR>
     * 　@（１）　@商品コード<BR>
     * 　@　@　@・パラメータ.商品コードがnullのとき<BR>
     * 　@　@　@　@wk商品コードに"99"（金銭）をセットする<BR>
     * 　@　@　@・上記以外<BR>
     * 　@　@　@　@wk商品コードにパラメータ.商品コードをセットする<BR>
     * <BR>
     * 　@（２）　@コード種別<BR>
     * 　@　@　@・wk商品コードが11：信用且つ、パラメータ.弁済区分がnullでないとき<BR>
     * 　@　@　@　@wkコード種別に固定文言"th_repdv"（弁済区分）をセットする<BR>
     * 　@　@　@・上記以外<BR>
     * 　@　@　@　@wkコード種別に固定文言"th_cmcd"（商品コード）をセットする<BR>
     * <BR>
     * 　@（３）　@コード<BR>
     * 　@　@　@・wk商品コードが11：信用且つ、パラメータ.弁済区分がnullでないとき<BR>
     * 　@　@　@　@wkコードにパラメータ.弁済区分をセットする<BR>
     * 　@　@　@・上記以外<BR>
     * 　@　@　@　@wkコードにwk商品コードをセットする<BR>
     * <BR>
     * ３）　@表示メッセージの取得<BR>
     * <BR>
     * 　@QueryProcessor.doFindAllQuery()をコールする。<BR>
     * <BR>
     * 　@[doFindAllQuery()にセットするパラメータ]<BR>
     * 　@arg0：　@コード翻訳テーブル(code_translation)<BR>
     * 　@arg1：　@オプショナル文字列(*1）<BR>
     * 　@arg2：　@オブジェクト配列（*2）<BR>
     * <BR>
     * 　@※取得できない場合、nullを返却する<BR>
     * <BR>
     * 　@（*1）オプショナル文字列<BR>
     * <BR>
     * 　@　@　@以下の文字列を作成する。<BR>
     * 　@　@　@　@"code_type = ?"<BR>
     * 　@　@　@＋" and institution_code = ?"<BR>
     * 　@　@　@＋" and code = ?"<BR>
     * <BR>
     * 　@（*2）オブジェクト配列<BR>
     * 
     * 　@　@　@以下の順でArrayListを作成する。<BR>
     * 　@　@　@・wkコード種別<BR>
     * 　@　@　@・パラメータ.会社コード<BR>
     * 　@　@　@・wkコード<BR>
     * <BR>
     * ４）　@値セット<BR>
     * 　@this.set項目値()にて項目値をセットする。<BR>
     * <BR>
     * 　@[set項目値()に指定する引数]<BR>
     * 　@行番号：　@引数の行番号<BR>
     * 　@カラム：　@１）で取得したカラムモデル<BR>
     * 　@値：　@３）で取得した表示メッセージ<BR>
     * @@param l_intLineNumber - (行番号)<BR>
     * 行番号<BR>
     * @@param l_strInstitutionCode - (会社コード)<BR>
     * 会社コード<BR>
     * @@param l_strCommodityCode - (商品コード)<BR>
     * 商品コード<BR>
     * @@param l_strRepaymentType - (弁済区分)<BR>
     * 弁済区分<BR>
     * @@roseuid 42C4E176008F
     */
    public void setCommodityCode(
        int l_intLineNumber,
        String l_strInstitutionCode,
        String l_strCommodityCode,
        String l_strRepaymentType) 
        throws WEB3BaseException
    {
        final String STR_METHOD_NAME = " setCommodityCode(int, String, String, String)"; 
        log.entering(STR_METHOD_NAME);

        //１）　@カラムモデル取得 
        // this.getカラムモデル()にてCSVカラムモデルを取得する。 
        WEB3GentradeCsvColumnModel l_columnModel = this.getColumnModel(
            WEB3HistoryTradeHistoryListCSV.COMMODITY_NAME_LABEL);

        //２）　@検索条件値の設定
        //  （１）　@商品コード<BR>
        //　@　@・パラメータ.商品コードがnullのとき<BR>
        //　@　@　@wk商品コードに"99"（金銭）をセットする<BR>
        //  　@・上記以外<BR>
        //  　@　@wk商品コードにパラメータ.商品コードをセットする<BR>
        String l_strWKCommodityCode = null;
        if (l_strCommodityCode == null)
        {
            l_strWKCommodityCode = WEB3PlsBvsProductCodeDef.MONEY;
        }
        else
        {
            l_strWKCommodityCode = l_strCommodityCode;
        }

        // （２）　@コード種別<BR>
        //　@　@・wk商品コードが11：信用且つ、パラメータ.弁済区分がnullでないとき<BR>
        //　@　@　@wkコード種別に固定文言"th_repdv"（弁済区分）をセットする<BR>
        //　@　@・上記以外<BR>
        //　@　@　@wkコード種別に固定文言"th_cmcd"（商品コード）をセットする<BR>
        // （３）　@コード<BR>
        //　@　@・wk商品コードが11：信用且つ、パラメータ.弁済区分がnullでないとき<BR>
        //　@　@　@wkコードにパラメータ.弁済区分をセットする<BR>
        //　@　@・上記以外<BR>
        //　@　@　@wkコードにwk商品コードをセットする<BR>
        String l_strWKCodeType = null;
        String l_strWKCode = null;
        if (WEB3PlsBvsProductCodeDef.MARGIN.equals(l_strWKCommodityCode)
            && l_strRepaymentType != null)
        {
            l_strWKCodeType = WEB3HistoryTradeHistoryListCSV.WK_CODE_TYPE_REPDV;
            l_strWKCode = l_strRepaymentType;
        }
        else
        {
            l_strWKCodeType = WEB3HistoryTradeHistoryListCSV.WK_CODE_TYPE_CMCD;
            l_strWKCode = l_strWKCommodityCode;
        }

        //３）　@表示メッセージの取得
        StringBuffer l_strWhere = new StringBuffer();
        l_strWhere.append(" code_type = ? ");
        l_strWhere.append(" and institution_code = ? ");
        l_strWhere.append(" and code = ? ");
        
        Object[] l_objWhere = {
            l_strWKCodeType,
            l_strInstitutionCode,
            l_strWKCode};

        List l_lisRecords = null;
        try
        {
            QueryProcessor l_QueryProcessor = Processors.getDefaultProcessor();
            l_lisRecords = l_QueryProcessor.doFindAllQuery(
                CodeTranslationRow.TYPE,
                l_strWhere.toString(),
                l_objWhere);
        }
        catch (DataFindException l_dfe)
        {
            log.error(STR_METHOD_NAME, l_dfe);

            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80005,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                l_dfe.getMessage(),
                l_dfe);
        }
        catch (DataQueryException l_dqe)
        {
            log.error(STR_METHOD_NAME, l_dqe);

            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                l_dqe.getMessage(),
                l_dqe);
        }
        catch (DataNetworkException l_dne)
        {
            log.error(STR_METHOD_NAME, l_dne);

            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                l_dne.getMessage(),
                l_dne);
        }

        //※取得できない場合、nullを返却する
        String l_strMessage = null;
        if (l_lisRecords == null || l_lisRecords.size() == 0)
        {
            l_strMessage = null;
        }
        else
        {
            CodeTranslationRow l_translationRow = (CodeTranslationRow) l_lisRecords.get(0);
            l_strMessage = l_translationRow.getDisplayMessage();
        }

        //４）　@値セット
        //　@this.set項目値()にて項目値をセットする。
        this.setValue(
            l_intLineNumber,
            l_columnModel,
            l_strMessage);

        log.exiting(STR_METHOD_NAME);  
    }

    /**
     * (set銘柄)<BR>
     * 銘柄コードと銘柄摘要名をセットする。<BR>
     * <BR>
     * １）　@パラメータ.商品コードが10：株式、11：信用、40：外株<BR>
     *      且つパラメータ.銘柄コード!= nullのとき<BR>
     * <BR>
     * 　@（１）　@銘柄コードの取得<BR>
     * <BR>
     * 　@　@　@・パラメータ.商品コードが10：株式、11：信用<BR>
     * 　@　@　@　@- パラメータ.銘柄コードの5桁目が0のとき<BR>
     *          wk銘柄コードにパラメータ.銘柄コードの上4桁をセット<BR>
     * 　@　@　@　@- 上記以外<BR>
     *          wk銘柄コードにパラメータ.銘柄コードの上5桁をセット<BR>
     * 　@　@　@・上記以外<BR>
     * 　@　@　@　@wk銘柄コードにパラメータ.銘柄コードの上5桁をセット<BR>
     * <BR>
     * 　@（２）　@銘柄コードのカラムモデル取得<BR>
     * 　@　@　@this.getカラムモデル()にてCSVカラムモデルを取得する。<BR>
     * <BR>
     * 　@　@　@　@[getカラムモデル()に指定する引数]<BR>
     * 　@　@　@　@項目ラベル：　@取引履歴CSV.銘柄コードラベル<BR>
     * <BR>
     * 　@（３）　@銘柄コードの値セット<BR>
     * 　@　@　@this.set項目値()にて項目値をセットする。<BR>
     * <BR>
     * 　@　@　@[set項目値()に指定する引数]<BR>
     * 　@　@　@行番号：　@引数の行番号<BR>
     * 　@　@　@カラム：　@（２）で取得したカラムモデル<BR>
     * 　@　@　@値：（１）で取得した銘柄コード（wk銘柄コード）<BR>
     * <BR>
     * ２）　@銘柄摘要名のカラムモデル取得<BR>
     * 　@this.getカラムモデル()にてCSVカラムモデルを取得する。<BR>
     * <BR>
     * 　@[getカラムモデル()に指定する引数]<BR>
     * 　@項目ラベル：　@取引履歴CSV.銘柄摘要名ラベル<BR>
     * <BR>
     * ３）　@銘柄摘要名の値セット<BR>
     * 　@this.set項目値()にて項目値をセットする。<BR>
     * <BR>
     * 　@[set項目値()に指定する引数]<BR>
     * 　@行番号：　@引数の行番号<BR>
     * 　@カラム：　@２）で取得したカラムモデル<BR>
     * 　@値：パラメータ.銘柄摘要名をセット<BR>
     * @@param l_intLineNumber - (行番号)<BR>
     * 行番号<BR>
     * @@param l_strCommodityCode - (商品コード)<BR>
     * 商品コード<BR>
     * @@param l_strProductCode - (銘柄コード)<BR>
     * 銘柄コード<BR>
     * @@param l_strProductName - (銘柄摘要名)<BR>
     * 銘柄摘要名<BR>
     * @@roseuid 42C4E176008F
     */
    public void setProduct(
        int l_intLineNumber,
        String l_strCommodityCode,
        String l_strProductCode,
        String l_strProductName) 
    {
        final String STR_METHOD_NAME = " setProduct(int, String, String, String)"; 
        log.entering(STR_METHOD_NAME);

        // １）　@パラメータ.商品コードが10：株式、11：信用、40：外株<BR>
        //       且つパラメータ.銘柄コード!= nullのと<BR>
        WEB3GentradeCsvColumnModel l_columnModel = null;
        if ((WEB3PlsBvsProductCodeDef.EQUITY.equals(l_strCommodityCode)
            || WEB3PlsBvsProductCodeDef.MARGIN.equals(l_strCommodityCode)
            || WEB3PlsBvsProductCodeDef.FOREIGN_STOCK.equals(l_strCommodityCode))
            && l_strProductCode != null)
        {
            // 　@（１）　@銘柄コードの取得<BR>
            // 　@　@　@・	- パラメータ.銘柄コードの5桁目が0のとき<BR>
            //	          wk銘柄コードにパラメータ.銘柄コードの上4桁をセット<BR>
            //	        - 上記以外<BR>
            //            wk銘柄コードにパラメータ.銘柄コードの上5桁をセット<BR>
            // 　@　@　@・上記以外<BR>
            // 　@　@　@　@wk銘柄コードにパラメータ.銘柄コードの上5桁をセット<BR>
            String l_strWKProductCode = null;
            if (WEB3PlsBvsProductCodeDef.EQUITY.equals(l_strCommodityCode)
                || WEB3PlsBvsProductCodeDef.MARGIN.equals(l_strCommodityCode))
            {
            	if (l_strProductCode.charAt(4) == '0')
            	{
					l_strWKProductCode = l_strProductCode.substring(0, 4);
            	}
            	else
            	{
					l_strWKProductCode = l_strProductCode.substring(0, 5);
            	}                
            }
            else
            {
                l_strWKProductCode = l_strProductCode.substring(0, 5);
            }

            // 　@（２）　@銘柄コードのカラムモデル取得<BR>
            // 　@　@　@this.getカラムモデル()にてCSVカラムモデルを取得する。<BR>
            // 　@　@　@　@[getカラムモデル()に指定する引数]<BR>
            // 　@　@　@　@項目ラベル：　@取引履歴CSV.銘柄コードラベル<BR>
            l_columnModel = this.getColumnModel(
                WEB3HistoryTradeHistoryListCSV.PRODUCT_CODE_LABEL);

            // 　@（３）　@銘柄コードの値セット<BR>
            // 　@　@　@this.set項目値()にて項目値をセットする。<BR>
            // 　@　@　@[set項目値()に指定する引数]<BR>
            // 　@　@　@行番号：　@引数の行番号<BR>
            // 　@　@　@カラム：　@（２）で取得したカラムモデル<BR>
            // 　@　@　@値：（１）で取得した銘柄コード（wk銘柄コード）<BR>
            this.setValue(
                l_intLineNumber,
                l_columnModel,
                l_strWKProductCode);
        }

        // ２）　@銘柄摘要名のカラムモデル取得<BR>
        // 　@this.getカラムモデル()にてCSVカラムモデルを取得する。<BR>
        // 　@[getカラムモデル()に指定する引数]<BR>
        // 　@項目ラベル：　@取引履歴CSV.銘柄摘要名ラベル<BR>
        l_columnModel = this.getColumnModel(
            WEB3HistoryTradeHistoryListCSV.PRODUCT_NAME_LABEL);

        // ３）　@銘柄摘要名の値セット<BR>
        // 　@this.set項目値()にて項目値をセットする。<BR>
        // 　@[set項目値()に指定する引数]<BR>
        // 　@行番号：　@引数の行番号<BR>
        // 　@カラム：　@２）で取得したカラムモデル<BR>
        // 　@値：パラメータ.銘柄摘要名をセット<BR>
        this.setValue(
            l_intLineNumber,
            l_columnModel,
            l_strProductName);

        log.exiting(STR_METHOD_NAME);  
    }

    /**
     * (set口座区分名称)<BR>
     * 口座区分名称をセットする。<BR>
     * <BR>
     * １）　@パラメータ.商品コードが<BR>
     * 　@　@　@　@10：株式、11：信用、30：債券、40：外株、20：投信、21：外投<BR>
     * 　@　@　@且つ、パラメータ.摘要コードが権利落差（A206且つ、A213）以外のとき<BR>
     *      且つ、<BR>
     *        パラメータ.商品コードが<BR>
     * 　@　@　@　@  20：投信、21：外投<BR>
     *        且つ、パラメータ.取引コードがA3<BR>
     *        且つ、パラメータ.摘要コードがE103以外のとき<BR>
     * <BR>
     * 　@（１）　@口座区分名称の取得<BR>
     * <BR>
     * 　@　@　@・パラメータ.口座区分が0：一般のとき<BR>
     * 　@　@　@　@wk口座区分名称に取引履歴一覧CSV.口座_一般表示をセット<BR>
     * 　@　@　@・パラメータ.口座区分が1：特定のとき<BR>
     * 　@　@　@　@　@wk口座区分名称に取引履歴一覧CSV.口座_特定表示をセット<BR>
     * <BR>
     * 　@（２）　@カラムモデル取得<BR>
     * 　@　@　@this.getカラムモデル()にてCSVカラムモデルを取得する。<BR>
     * <BR>
     * 　@　@　@[getカラムモデル()に指定する引数]<BR>
     * 　@　@　@項目ラベル：　@取引履歴CSV.口座区分名称ラベル<BR>
     * <BR>
     * 　@（３）　@値セット<BR>
     * 　@　@　@this.set項目値()にて項目値をセットする。<BR>
     * <BR>
     * 　@　@　@[set項目値()に指定する引数]<BR>
     * 　@　@　@行番号：　@引数の行番号<BR>
     * 　@　@　@カラム：　@（２）で取得したカラムモデル<BR>
     * 　@　@　@値：（１）で取得した口座区分名称（wk口座区分名称）<BR>
     * @@param l_intLineNumber - (行番号)<BR>
     * 行番号<BR>
     * @@param l_strCommodityCode - (商品コード)<BR>
     * 商品コード<BR>
     * @@param l_strAccountType - (口座区分)<BR>
     * 口座区分<BR>
     * @@param l_strRemarkCode - (摘要コード)<BR>
     * 摘要コード<BR>
     * @@param l_strTradeCode - (取引コード)<BR>
     * 取引コード<BR>
     * @@roseuid 42C4E176008F
     */
    public void setAccountType(
        int l_intLineNumber,
        String l_strCommodityCode,
        String l_strAccountType,
        String l_strRemarkCode,
        String l_strTradeCode)
    {
        final String STR_METHOD_NAME = " setAccountType(int, String, String, String, String)"; 
        log.entering(STR_METHOD_NAME);

        // １）　@パラメータ.商品コードが<BR>
        //　@　@　@10：株式、11：信用、30：債券、40：外株、20：投信、21：外投<BR>
        //　@　@　@且つ、パラメータ.摘要コードが権利落差（A206且つ、A213）以外のとき<BR>
        //      且つ、パラメータ.商品コードが20：投信、21：外投<BR>
        //            且つ、パラメータ.取引コードがA3<BR>
        //            且つ、パラメータ.摘要コードがE103以外のとき<BR>
        if ((WEB3PlsBvsProductCodeDef.EQUITY.equals(l_strCommodityCode)
            || WEB3PlsBvsProductCodeDef.MARGIN.equals(l_strCommodityCode)
            || WEB3PlsBvsProductCodeDef.BOND.equals(l_strCommodityCode)
            || WEB3PlsBvsProductCodeDef.FOREIGN_STOCK.equals(l_strCommodityCode)
            || WEB3PlsBvsProductCodeDef.MUTUAL.equals(l_strCommodityCode)
            || WEB3PlsBvsProductCodeDef.FOREIGN_MUTUAL.equals(l_strCommodityCode))
            && !WEB3TradeHistoryRemarkCodeDef.REMARK_CODE_A206.equals(l_strRemarkCode)
            && !WEB3TradeHistoryRemarkCodeDef.REMARK_CODE_A213.equals(l_strRemarkCode)
            && !((WEB3PlsBvsProductCodeDef.MUTUAL.equals(l_strCommodityCode) 
                  || WEB3PlsBvsProductCodeDef.FOREIGN_MUTUAL.equals(l_strCommodityCode))
                  && WEB3TradeHistoryTradeCodeDef.TRADE_CODE_A3.equals(l_strTradeCode)
                  && WEB3TradeHistoryRemarkCodeDef.REMARK_CODE_E103.equals(l_strRemarkCode)))
        {
            //　@（１）　@口座区分名称の取得<BR>
            // 　@　@　@・パラメータ.口座区分が0：一般のとき<BR>
            // 　@　@　@　@wk口座区分名称に取引履歴一覧CSV.口座_一般表示をセット<BR>
            // 　@　@　@・パラメータ.口座区分が1：特定のとき<BR>
            // 　@　@　@　@　@wk口座区分名称に取引履歴一覧CSV.口座_特定表示をセット<BR>
            String l_strWKAccountType = null;
            if (WEB3AccountDivDef.NORMAL.equals(l_strAccountType))
            {
                l_strWKAccountType = WEB3HistoryTradeHistoryListCSV.ACCOUNT_NORMAL_LABEL;
            }
            else if (WEB3AccountDivDef.SPECIAL.equals(l_strAccountType))
            {
                l_strWKAccountType = WEB3HistoryTradeHistoryListCSV.ACCOUNT_SPECIAL_LABEL;
            }

            // 　@（２）　@銘柄コードのカラムモデル取得<BR>
            // 　@　@　@this.getカラムモデル()にてCSVカラムモデルを取得する。<BR>
            // 　@　@　@　@[getカラムモデル()に指定する引数]<BR>
            // 　@　@　@　@項目ラベル：　@取引履歴CSV.口座区分名称ラベル<BR>
            WEB3GentradeCsvColumnModel l_columnModel = this.getColumnModel(
                WEB3HistoryTradeHistoryListCSV.ACCOUNT_TYPE_LABEL);

            // 　@（３）　@銘柄コードの値セット<BR>
            // 　@　@　@this.set項目値()にて項目値をセットする。<BR>
            // 　@　@　@[set項目値()に指定する引数]<BR>
            // 　@　@　@行番号：　@引数の行番号<BR>
            // 　@　@　@カラム：　@（２）で取得したカラムモデル<BR>
            // 　@　@　@値：（１）で取得した口座区分名称（wk口座区分名称）<BR>
            this.setValue(
                l_intLineNumber,
                l_columnModel,
                l_strWKAccountType);
        }

        log.exiting(STR_METHOD_NAME);  
    }

    /**
     * (set翻訳摘要名)<BR>
     * 翻訳摘要名をセットする。<BR>
     * <BR>
     * １）　@パラメータ.商品コードが99：金銭以外のとき<BR>
     * <BR>
     * 　@（１）　@カラムモデル取得<BR>
     * 　@　@　@this.getカラムモデル()にてCSVカラムモデルを取得する。<BR>
     * <BR>
     * 　@　@　@[getカラムモデル()に指定する引数]<BR>
     * 　@　@　@項目ラベル：　@取引履歴CSV.翻訳摘要名ラベル<BR>
     * <BR>
     * 　@（２）　@値セット<BR>
     * 　@　@　@this.set項目値()にて項目値をセットする。<BR>
     * <BR>
     * 　@　@　@[set項目値()に指定する引数]<BR>
     * 　@　@　@行番号：　@引数の行番号<BR>
     * 　@　@　@カラム：　@（１）で取得したカラムモデル<BR>
     * 　@　@　@値：パラメータ.翻訳摘要名<BR>
     * @@param l_intLineNumber - (行番号)<BR>
     * 行番号<BR>
     * @@param l_strCommodityCode - (商品コード)<BR>
     * 商品コード<BR>
     * @@param l_strRemarkName - (翻訳摘要名)<BR>
     * 翻訳摘要名<BR>
     * @@roseuid 42C4E176008F
     */
    public void setRemarkName(
        int l_intLineNumber,
        String l_strCommodityCode,
        String l_strRemarkName)
    {
        final String STR_METHOD_NAME = " setRemarkName(int, String, String)"; 
        log.entering(STR_METHOD_NAME);

        //１)　@パラメータ.商品コードが99：金銭以外のとき
        if (!WEB3PlsBvsProductCodeDef.MONEY.equals(l_strCommodityCode))
        {
            //(１）　@カラムモデル取得 
            // this.getカラムモデル()にてCSVカラムモデルを取得する。 
            WEB3GentradeCsvColumnModel l_columnModel = this.getColumnModel(
                WEB3HistoryTradeHistoryListCSV.REMARK_NAME_LABEL);

            //(２）　@値セット
            //　@this.set項目値()にて項目値をセットする。
            this.setValue(
                l_intLineNumber,
                l_columnModel,
                l_strRemarkName);
        }

        log.exiting(STR_METHOD_NAME);  
    }

    /**
     * (set数量)<BR>
     * 数量をセットする。<BR>
     * <BR>
     * １）　@パラメータ.数量>0のとき<BR>
     * 　@（１）　@カラムモデル取得<BR>
     * 　@  this.getカラムモデル()にてCSVカラムモデルを取得する。<BR>
     * <BR>
     * 　@  [getカラムモデル()に指定する引数]<BR>
     * 　@  項目ラベル：　@取引履歴CSV.数量ラベル<BR>
     * <BR>
     * 　@（２）　@値セット<BR>
     * 　@  this.set項目値()にて項目値をセットする。<BR>
     * <BR>
     * 　@  [set項目値()に指定する引数]<BR>
     * 　@  行番号：　@引数の行番号<BR>
     * 　@  カラム：　@１）で取得したカラムモデル<BR>
     * 　@  値：パラメータ.数量<BR>
     * @@param l_intLineNumber - (行番号)<BR>
     * 行番号<BR>
     * @@param l_strQuantity - (数量)<BR>
     * 数量<BR>
     * @@roseuid 42C4E176008F
     */
    public void setQuantity(
        int l_intLineNumber,
        String l_strQuantity)
    {
        final String STR_METHOD_NAME = " setQuantity(int, String)"; 
        log.entering(STR_METHOD_NAME);  

        //１）　@パラメータ.数量>0のとき<BR>
        if (l_strQuantity != null 
             && Double.parseDouble(l_strQuantity) > 0)
		{
            //（１）　@カラムモデル取得 
            // this.getカラムモデル()にてCSVカラムモデルを取得する。 
            WEB3GentradeCsvColumnModel l_columnModel = this.getColumnModel(
                WEB3HistoryTradeHistoryListCSV.QUANTITY_LABEL);

            //（２）　@値セット
            //　@this.set項目値()にて項目値をセットする。
            this.setValue(
                l_intLineNumber,
                l_columnModel,
                l_strQuantity);			
		}

        log.exiting(STR_METHOD_NAME);  
    }

    /**
     * (set単価)<BR>
     * 単価をセットする。<BR>
     * <BR>
     * １）　@パラメータ.単価>0のとき<BR>
     * <BR>
     * 　@（１）　@カラムモデル取得<BR>
     * 　@　@　@this.getカラムモデル()にてCSVカラムモデルを取得する。<BR>
     * <BR>
     * 　@　@　@[getカラムモデル()に指定する引数]<BR>
     * 　@　@　@項目ラベル：　@取引履歴CSV.単価ラベル<BR>
     * <BR>
     * 　@（２）　@値セット<BR>
     * 　@　@　@this.set項目値()にて項目値をセットする。<BR>
     * <BR>
     * 　@　@　@[set項目値()に指定する引数]<BR>
     * 　@　@　@行番号：　@引数の行番号<BR>
     * 　@　@　@カラム：（１）で取得したカラムモデル<BR>
     * 　@　@　@値：パラメータ.単価<BR>
     * @@param l_intLineNumber - (行番号)<BR>
     * 行番号<BR>
     * @@param l_strPrice - (単価)<BR>
     * 単価<BR>
     * @@roseuid 42C4E176008F
     */
    public void setPrice(
        int l_intLineNumber,
        String l_strPrice)
    {
        final String STR_METHOD_NAME = " setPrice(int, String)"; 
        log.entering(STR_METHOD_NAME);

        //１)　@パラメータ.単価>0のとき
        if (l_strPrice != null 
            && Double.parseDouble(l_strPrice) > 0)
        {
            //(１）　@カラムモデル取得 
            // this.getカラムモデル()にてCSVカラムモデルを取得する。 
            WEB3GentradeCsvColumnModel l_columnModel = this.getColumnModel(
                WEB3HistoryTradeHistoryListCSV.PRICE_LABEL);

            //(２）　@値セット
            //　@this.set項目値()にて項目値をセットする。
            this.setValue(
                l_intLineNumber,
                l_columnModel,
                l_strPrice);
        }

        log.exiting(STR_METHOD_NAME);  
    }

    /**
     * (set通貨単位名称)<BR>
     * 通貨単位名称をセットする。<BR>
     * <BR>
     * １）　@カラムモデル取得<BR>
     * 　@this.getカラムモデル()にてCSVカラムモデルを取得する。<BR>
     * <BR>
     * 　@[getカラムモデル()に指定する引数]<BR>
     * 　@項目ラベル：　@取引履歴CSV.通貨単位名称ラベル<BR>
     * <BR>
     * ２）　@検索条件値の設定<BR>
     * <BR>
     * 　@（１）　@通貨単位<BR>
     * 　@　@　@・パラメータ.通貨単位がスペースorスペース×2のとき<BR>
     * 　@　@　@　@wk通貨単位に"T0"（円）をセットする<BR>
     * 　@　@　@・上記以外<BR>
     * 　@　@　@　@wk通貨単位にパラメータ.通貨単位をセットする<BR>
     * <BR>
     * ３）　@表示メッセージの取得<BR>
     * <BR>
     * 　@QueryProcessor.doFindAllQuery()をコールする。<BR>
     * <BR>
     * 　@[doFindAllQuery()にセットするパラメータ]<BR>
     * 　@arg0：　@コード翻訳テーブル(code_translation)<BR>
     * 　@arg1：　@オプショナル文字列(*1）<BR>
     * 　@arg2：　@オブジェクト配列（*2）<BR>
     * <BR>
     * 　@※取得できない場合、nullを返却する<BR>
     * <BR>
     * 　@（*1）オプショナル文字列<BR>
     * <BR>
     * 　@　@　@以下の文字列を作成する。<BR>
     * 　@　@　@　@"code_type = ?"<BR>
     * 　@　@　@＋" and institution_code = ?"<BR>
     * 　@　@　@＋" and code = ?"<BR>
     * <BR>
     * 　@（*2）オブジェクト配列<BR>
     * <BR>
     * 　@　@　@以下の順でArrayListを作成する。<BR>
     * 　@　@　@・"th_exchg"<BR>
     * 　@　@　@・パラメータ.会社コード<BR>
     * 　@　@　@・wk通貨単位<BR>
     * <BR>
     * ４）　@値セット<BR>
     * 　@this.set項目値()にて項目値をセットする。<BR>
     * <BR>
     * 　@[set項目値()に指定する引数]<BR>
     * 　@行番号：　@引数の行番号<BR>
     * 　@カラム：　@１）で取得したカラムモデル<BR>
     * 　@値：　@３）で取得した表示メッセージ<BR>
     * @@param l_intLineNumber - (行番号)<BR>
     * 行番号<BR>
     * @@param l_strInstitutionCode - (会社コード)<BR>
     * 会社コード<BR> 
     * @@param l_strMonetaryUnit - (通貨単位)<BR>
     * 通貨単位<BR> 
     * @@roseuid 42C4E176008F
     */
    public void setMonetaryUnit(
        int l_intLineNumber,
        String l_strInstitutionCode,
        String l_strMonetaryUnit)
        throws WEB3BaseException
    {
        final String STR_METHOD_NAME = " setMonetaryUnit(int, String, String)"; 
        log.entering(STR_METHOD_NAME);

        //１）　@カラムモデル取得 
        // this.getカラムモデル()にてCSVカラムモデルを取得する。 
        WEB3GentradeCsvColumnModel l_columnModel = this.getColumnModel(
            WEB3HistoryTradeHistoryListCSV.MONETARY_UNIT_LABEL);

        //２）　@検索条件値の設定
        //  （１）　@通貨単位
        //      ・パラメータ.通貨単位がスペースorスペース×2のとき
        //        wk通貨単位に"T0"（円）をセットする
        //      ・上記以外
        //        wk通貨単位にパラメータ.通貨単位をセットする
        String l_strWKMonetaryUnit = null;
        if (" ".equals(l_strMonetaryUnit) || "  ".equals(l_strMonetaryUnit))
        {
            l_strWKMonetaryUnit = WEB3HistoryTradeHistoryListCSV.WK_MONETARY_UNIT_TO;
        }
        else
        {
            l_strWKMonetaryUnit = l_strMonetaryUnit;
        }

        //３）　@表示メッセージの取得
        StringBuffer l_strWhere = new StringBuffer();
        l_strWhere.append(" code_type = ? ");
        l_strWhere.append(" and institution_code = ? ");
        l_strWhere.append(" and code = ? ");
        
        Object[] l_objWhere = {
            WEB3HistoryTradeHistoryListCSV.CODE_TYPE_EXCHG,
            l_strInstitutionCode,
            l_strWKMonetaryUnit};

        List l_lisRecords = null;
        try
        {
            QueryProcessor l_QueryProcessor = Processors.getDefaultProcessor();
            l_lisRecords = l_QueryProcessor.doFindAllQuery(
                CodeTranslationRow.TYPE,
                l_strWhere.toString(),
                l_objWhere);
        }
        catch (DataFindException l_dfe)
        {
            log.error(STR_METHOD_NAME, l_dfe);

            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80005,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                l_dfe.getMessage(),
                l_dfe);
        }
        catch (DataQueryException l_dqe)
        {
            log.error(STR_METHOD_NAME, l_dqe);

            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                l_dqe.getMessage(),
                l_dqe);
        }
        catch (DataNetworkException l_dne)
        {
            log.error(STR_METHOD_NAME, l_dne);

            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                l_dne.getMessage(),
                l_dne);
        }

        //※取得できない場合、nullを返却する
        String l_strMessage = null;
        if (l_lisRecords == null || l_lisRecords.size() == 0)
        {
            l_strMessage = null;
        }
        else
        {
            CodeTranslationRow l_translationRow = (CodeTranslationRow) l_lisRecords.get(0);
            l_strMessage = l_translationRow.getDisplayMessage();
        }

        //４）　@値セット
        //　@this.set項目値()にて項目値をセットする。
        this.setValue(
            l_intLineNumber,
            l_columnModel,
            l_strMessage);

        log.exiting(STR_METHOD_NAME);  
    }

    /**
     * (set受渡金額)<BR>
     * 受渡金額をセットする。<BR>
     * <BR>
     * １）　@カラムモデル取得<BR>
     * 　@this.getカラムモデル()にてCSVカラムモデルを取得する。<BR>
     * <BR>
     * 　@[getカラムモデル()に指定する引数]<BR>
     * 　@項目ラベル：　@取引履歴CSV.受渡金額ラベル<BR>
     * <BR>
     * ２）　@値セット<BR>
     * 　@this.set項目値()にて項目値をセットする。<BR>
     * <BR>
     * 　@[set項目値()に指定する引数]<BR>
     * 　@行番号：　@引数の行番号<BR>
     * 　@カラム：　@１）で取得したカラムモデル<BR>
     * 　@値：パラメータ.受渡金額<BR>
     * @@param l_intLineNumber - (行番号)<BR>
     * 行番号<BR>
     * @@param l_strNetAmount - (受渡金額)<BR>
     * 受渡金額<BR>
     * @@roseuid 42C4E176008F
     */
    public void setNetAmount(
        int l_intLineNumber,
        String l_strNetAmount)
    {
        final String STR_METHOD_NAME = " setNetAmount(int, String)"; 
        log.entering(STR_METHOD_NAME);  

        //１）　@カラムモデル取得 
        // this.getカラムモデル()にてCSVカラムモデルを取得する。 
        WEB3GentradeCsvColumnModel l_columnModel = this.getColumnModel(
            WEB3HistoryTradeHistoryListCSV.NET_AMOUNT_LABEL);

        //２）　@値セット
        //　@this.set項目値()にて項目値をセットする。
        this.setValue(
            l_intLineNumber,
            l_columnModel,
            l_strNetAmount);

        log.exiting(STR_METHOD_NAME);  
    }

    /**
     * (set譲渡損益)<BR>
     * 譲渡損益をセットする。<BR>
     * <BR>
     * １）　@カラムモデル取得<BR>
     * 　@this.getカラムモデル()にてCSVカラムモデルを取得する。<BR>
     * <BR>
     * 　@[getカラムモデル()に指定する引数]<BR>
     * 　@項目ラベル：　@取引履歴CSV.譲渡損益ラベル<BR>
     * <BR>
     * ２）　@値セット<BR>
     * 　@this.set項目値()にて項目値をセットする。<BR>
     * <BR>
     * 　@[set項目値()に指定する引数]<BR>
     * 　@行番号：　@引数の行番号<BR>
     * 　@カラム：　@１）で取得したカラムモデル<BR>
     * 　@値：パラメータ.譲渡損益<BR>
     * @@param l_intLineNumber - (行番号)<BR>
     * 行番号<BR>
     * @@param l_strCapitalGain - (譲渡損益)<BR>
     * 譲渡損益<BR>
     * @@roseuid 42C4E176008F
     */
    public void setCapitalGain(
        int l_intLineNumber,
        String l_strCapitalGain)
    {
        final String STR_METHOD_NAME = " setCapitalGain(int, String)"; 
        log.entering(STR_METHOD_NAME);  

        //１）　@カラムモデル取得 
        // this.getカラムモデル()にてCSVカラムモデルを取得する。 
        WEB3GentradeCsvColumnModel l_columnModel = this.getColumnModel(
            WEB3HistoryTradeHistoryListCSV.CAPITAL_GAIN_LABEL);

        //２）　@値セット
        //　@this.set項目値()にて項目値をセットする。
        this.setValue(
            l_intLineNumber,
            l_columnModel,
            l_strCapitalGain);

        log.exiting(STR_METHOD_NAME);  
    }
    
    /**
     * (set口座残高)<BR>
     * 口座残高をセットする。<BR>
     * <BR>
     * １）　@カラムモデル取得<BR>
     * 　@this.getカラムモデル()にてCSVカラムモデルを取得する。<BR>
     * <BR>
     * 　@[getカラムモデル()に指定する引数]<BR>
     * 　@項目ラベル：　@取引履歴CSV.口座残高ラベル<BR>
     * <BR>
     * ２）　@値セット<BR>
     * 　@this.set項目値()にて項目値をセットする。<BR>
     * <BR>
     * 　@[set項目値()に指定する引数]<BR>
     * 　@行番号：　@引数の行番号<BR>
     * 　@カラム：　@１）で取得したカラムモデル<BR>
     * 　@値：パラメータ.口座残高<BR>
     * @@param l_intLineNumber - (行番号)<BR>
     * 行番号<BR>
     * @@param l_strAccountBalance - (口座残高)<BR>
     * 口座残高<BR>
     * @@roseuid 42C4E176008F
     */
    public void setAccountBalance(
        int l_intLineNumber,
        String l_strAccountBalance)
    {
        final String STR_METHOD_NAME = " setAccountBalance(int, String)"; 
        log.entering(STR_METHOD_NAME);  

        //１）　@カラムモデル取得 
        // this.getカラムモデル()にてCSVカラムモデルを取得する。 
        WEB3GentradeCsvColumnModel l_columnModel = this.getColumnModel(
            WEB3HistoryTradeHistoryListCSV.ACCOUNT_BALANCE_LABEL);

        //２）　@値セット
        //　@this.set項目値()にて項目値をセットする。
        this.setValue(
            l_intLineNumber,
            l_columnModel,
            l_strAccountBalance);

        log.exiting(STR_METHOD_NAME);  
    }
}
@
