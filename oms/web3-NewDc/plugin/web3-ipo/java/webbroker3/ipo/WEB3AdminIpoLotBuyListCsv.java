head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.15.05.45.17;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	6f44d7efa104045;
filename	WEB3AdminIpoLotBuyListCsv.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : 抽選結果・購入申込状況CSV(WEB3AdminIpoLotBuyListCsv.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2004/08/16 斉麟 (中訊) 新規作成
Revesion History : 2005/01/07 坂上(SRA) 残対応>>>071
Revesion History : 2005/01/21 坂上(SRA) 残対応>>>038
Revesion History : 2005/12/20 譚漢江 (中訊) 仕様変更No.104修正
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
import webbroker3.common.define.WEB3LotResultDef;
import webbroker3.common.define.WEB3LotResultRetryDef;
import webbroker3.common.define.WEB3OfferingDivDef;
import webbroker3.gentrade.WEB3GentradeCsvColumnModel;
import webbroker3.gentrade.WEB3GentradeCsvDataModel;
import webbroker3.gentrade.WEB3GentradeMainAccount;
import webbroker3.gentrade.define.WEB3GentradeRepaymentDivDef;
import webbroker3.ipo.data.IpoProductRow;
import webbroker3.util.WEB3DateUtility;
import webbroker3.util.WEB3LogUtility;
import webbroker3.util.WEB3StringTypeUtility;


/**
 * (抽選結果・購入申込状況CSV)<BR>
 * 抽選結果・購入申込状況ダウンロードで作成するCSVファ@イルデータクラス<BR>
 *                                                               
 * @@author 斉麟
 * @@version 1.0
 */
public class WEB3AdminIpoLotBuyListCsv extends WEB3GentradeCsvDataModel 
{
    /**
     * ログ出力ユーティリティ。<BR>
     */
    private static WEB3LogUtility log = WEB3LogUtility.getInstance(WEB3AdminIpoLotBuyListCsv.class);
    
    /**
     * 定数定義プロパティ　@”部店コード”
     */
    public static final String BRANCH_CODE_LABEL = "部店コード";
    
    /**
     * 定数定義プロパティ　@”顧客コード”
     */
    public static final String ACCOUNT_CODE_LABEL = "顧客コード";
    
    /**
     * 定数定義プロパティ　@”顧客名”
     */
    public static final String ACCOUNT_NAME_LABEL = "顧客名";
    
    /**
     * 定数定義プロパティ　@”抽選結果”
     */
    public static final String LOT_RESULT_LABEL = "抽選結果";
    
    /**
     * 定数定義プロパティ　@”当選数量”
     */
    public static final String ELECTED_QUANTITY_LABEL = "当選数量";
    
    /**
     * 定数定義プロパティ　@”購入申込数量”
     */
    public static final String APPLICATION_QUANTITY_LABEL = "購入申込数量";
    
    /**
     * 定数定義プロパティ　@”購入申込／辞退日時”
     */
    public static final String OFFERING_TIMESTAMP_LABEL = "購入申込／辞退日時";
    
    /**
     * 定数定義プロパティ　@”購入申込状態”
     */
    public static final String OFFER_STATE_LABEL = "購入申込状態";
    
    /**
     * 定数定義プロパティ　@”受付状態”
     */
    public static final String ACCEPT_STATUS_LABEL = "受付状態";
    
    /**
     * 定数定義プロパティ　@”優先順位”
     */
    public static final String SUBSTITUTE_PRIORITY = "優先順位";
    
    /**
     * 定数定義プロパティ　@抽選結果_当選表示
     */
    public static final String LOT_RESULT_PRIZE_INDICATION = "当選";
    
    /**
     * 定数定義プロパティ　@抽選結果_補欠表示
     */
    public static final String LOT_RESULT_WAITING_INDICATION = "補欠";
    
    /**
     * 定数定義プロパティ　@抽選結果_補欠当選表示
     */
    public static final String LOT_RESULT_WAITING_PRIZE_INDICATION = "補欠当選";
    
    /**
     * 定数定義プロパティ　@抽選結果_補欠落選表示
     */
    public static final String LOT_RESULT_WAITING_REJECTED_INDICATION = "補欠落選";
    
    /**
     * 定数定義プロパティ　@抽選結果_落選表示
     */
    public static final String LOT_RESULT_REJECTED_INDICATION = "落選";
    
    /**
     * 定数定義プロパティ　@購入申込状態_申込表示
     */
    public static final String OFFER_STATE_APPLICATION_INDICATION = "申込";
    
    /**
     * 定数定義プロパティ　@購入申込状態_辞退表示
     */
    public static final String OFFER_STATE_CANCEL_INDICATION = "辞退";
    
    /**
     * 定数定義プロパティ　@購入申込状態_なし表示
     */
    public static final String OFFER_STATE_NO_INDICATION = "----";
    
    /**
     * 定数定義プロパティ　@受付状態_受付中表示
     */
    public static final String RECEIVE_STATE_RECEIVE_INDICATION = "受付中";
    
    /**
     * 定数定義プロパティ　@受付状態_受付済表示
     */
    public static final String RECEIVE_STATE_RECEIVE_END_INDICATION = "受付済";
    
    /**
     * 定数定義プロパティ　@受付状態_なし表示
     */
    public static final String RECEIVE_STATE_NO_INDICATION = "----";
    
    /**
     * (扱者コードラベル)<BR>
     * 定数定義プロパティ　@”扱者コード”<BR>
     */
    public static final String TRADER_CODE_LABEL = "扱者コード";
    
    /**
     * (公開価格ラベル)<BR>
     * 定数定義プロパティ　@”公開価格”<BR>
     */
    public static final String PUBLIC_OFFERING_PRICE_LABEL = "公開価格";
    
    /**
     * (信用区分ラベル)<BR>
     * 定数定義プロパティ　@”信用区分”<BR>
     */
    public static final String MARGIN_DIV_LABEL = "信用区分";
    
    /**
     * 定数定義プロパティ　@信用口座開設表示
     */
    public static final String MARGIN_ACC_OPEN = "開設";
    
    /**
     * 定数定義プロパティ　@信用口座未開設表示
     */
    public static final String MARGIN_ACC_CLOSE = "----";
    
    /**
     * (抽選番号ラベル)<BR>
     * 定数定義プロパティ　@”抽選番号”<BR>
     */
    public static final String LOT_NUMBER_LABEL = "抽選番号";

    /**
     * コンストラクタ。<BR>
     * <BR>
     * インスタンスを生成し、ヘッダ情報をセットする。<BR>
     * 　@－super()にてインスタンスを生成する。<BR>
     * 　@－createキーヘッダ(IPO銘柄)をコールし、キーヘッダ情報を作成する。<BR>
     * 　@－createカラムヘッダ()をコールし、カラムヘッダ情報を作成する。<BR>
     * @@param l_ipoProduct - IPO銘柄オブジェクト
     * 
     * @@return webbroker3.ipo.WEB3AdminIpoLotBuyListCsv
     * @@roseuid 40ECA86302E8
     */
    public WEB3AdminIpoLotBuyListCsv(WEB3IpoProductImpl l_ipoProduct) throws WEB3BaseException
    {
        super();
        createKeyHeader(l_ipoProduct);
        createColumnHeader();
    }
    
    /**
     * (createカラムヘッダ)<BR>
     * カラムヘッダをセットする。<BR>
     * <BR>
     * 　@以下の通りCSVカラムモデルの配列を生成し、<BR>setカラムヘッダ()にてインスタンスにセットする。<BR>
     * <BR>
     * [カラムヘッダ配列]<BR>
     * <BR>
     * －　@index = 0<BR>
     * 　@[CSVカラムモデル コンストラクタの引数]<BR>
     * 　@項目ラベル：　@抽選結果・購入申込状況CSV.部店コードラベル<BR>
     * 　@カラム番号： 0<BR>
     * 　@項目型：　@CSVカラムモデル.項目型_文字列<BR>
     * 　@日付フォーマット：　@null<BR>
     * <BR>
     * －　@index = 1<BR>
     * 　@[CSVカラムモデル コンストラクタの引数]<BR>
     * 　@項目ラベル：　@抽選結果・購入申込状況CSV.扱者コードラベル<BR>
     * 　@カラム番号： 1<BR>
     * 　@項目型：　@CSVカラムモデル.項目型_文字列<BR>
     * 　@日付フォーマット：　@null<BR>
     * <BR>
     * －　@index = 2<BR>
     * 　@[CSVカラムモデル コンストラクタの引数]<BR>
     * 　@項目ラベル：　@抽選結果・購入申込状況CSV.顧客コードラベル<BR>
     * 　@カラム番号： 2<BR>
     * 　@項目型：　@CSVカラムモデル.項目型_文字列<BR>
     * 　@日付フォーマット：　@null<BR>
     * <BR>
     * －　@index = 3<BR>
     * 　@[CSVカラムモデル コンストラクタの引数]<BR>
     * 　@項目ラベル：　@抽選結果・購入申込状況CSV.顧客名ラベル<BR>
     * 　@カラム番号： 3<BR>
     * 　@項目型：　@CSVカラムモデル.項目型_文字列<BR>
     * 　@日付フォーマット：　@null<BR>
     * <BR>
     * －　@index = 4<BR>
     * 　@[CSVカラムモデル コンストラクタの引数]<BR>
     * 　@項目ラベル：　@抽選結果・購入申込状況CSV.公開価格ラベル<BR>
     * 　@カラム番号： 4<BR>
     * 　@項目型：　@CSVカラムモデル.項目型_数値（double）<BR>
     * 　@日付フォーマット：　@null<BR>
     * <BR>
     * －　@index = 5<BR>
     * 　@[CSVカラムモデル コンストラクタの引数]<BR>
     * 　@項目ラベル：　@抽選結果・購入申込状況CSV.抽選結果ラベル<BR>
     * 　@カラム番号： 5<BR>
     * 　@項目型：　@CSVカラムモデル.項目型_文字列<BR>
     * 　@日付フォーマット：　@null<BR>
     * <BR>
     * －　@index = 6<BR>
     * 　@[CSVカラムモデル コンストラクタの引数]<BR>
     * 　@項目ラベル：　@抽選結果・購入申込状況CSV.当選数量ラベル<BR>
     * 　@カラム番号： 6<BR>
     * 　@項目型：　@CSVカラムモデル.項目型_数値（double）<BR>
     * 　@日付フォーマット：　@null<BR>
     * <BR>
     * －　@index = 7<BR>
     * 　@[CSVカラムモデル コンストラクタの引数]<BR>
     * 　@項目ラベル：　@抽選結果・購入申込状況CSV.購入申込数量ラベル<BR>
     * 　@カラム番号： 7<BR>
     * 　@項目型：　@CSVカラムモデル.項目型_数値（double）<BR>
     * 　@日付フォーマット：　@null<BR>
     * <BR>
     * －　@index = 8<BR>
     * 　@[CSVカラムモデル コンストラクタの引数]<BR>
     * 　@項目ラベル：　@抽選結果・購入申込状況CSV.購入申込／辞退日時ラベル<BR>
     * 　@カラム番号： 8<BR>
     * 　@項目型：　@CSVカラムモデル.項目型_日付時間<BR>
     * 　@日付フォーマット：　@<BR>
     * 　@　@new SimpleDateFormat("yyyy/MM/dd HH:mm:ss")<BR>
     * <BR>
     * －　@index = 9<BR>
     * 　@[CSVカラムモデル コンストラクタの引数]<BR>
     * 　@項目ラベル：　@抽選結果・購入申込状況CSV.購入申込状態ラベル<BR>
     * 　@カラム番号： 9<BR>
     * 　@項目型：　@CSVカラムモデル.項目型_文字列<BR>
     * 　@日付フォーマット：　@null<BR>
     * <BR>
     * －　@index = 10<BR>
     * 　@[CSVカラムモデル コンストラクタの引数]<BR>
     * 　@項目ラベル：　@抽選結果・購入申込状況CSV.受付状態ラベル<BR>
     * 　@カラム番号： 10<BR>
     * 　@項目型：　@CSVカラムモデル.項目型_文字列<BR>
     * 　@日付フォーマット：　@null<BR>
     * <BR>
     * －　@index = 11<BR>
     * 　@[CSVカラムモデル コンストラクタの引数]<BR>
     * 　@項目ラベル：　@抽選結果・購入申込状況CSV.優先順位ラベル<BR>
     * 　@カラム番号： 11<BR>
     * 　@項目型：　@CSVカラムモデル.項目型_数値（long）<BR>
     * 　@日付フォーマット：　@null<BR>
     * <BR>
     * <BR>
     * @@roseuid 40ECA8630307
     */
    private void createColumnHeader() 
    {
        final String STR_METHOD_NAME = " createColumnHeader()";
        log.entering(STR_METHOD_NAME);
        
        WEB3GentradeCsvColumnModel[] columnHeader = new WEB3GentradeCsvColumnModel[14];
        columnHeader[0] = new WEB3GentradeCsvColumnModel(BRANCH_CODE_LABEL, 0, WEB3GentradeCsvColumnModel.STRINGTYPE, null);
        columnHeader[1] =
            new WEB3GentradeCsvColumnModel(TRADER_CODE_LABEL, 1, WEB3GentradeCsvColumnModel.STRINGTYPE, null);
        columnHeader[2] =
            new WEB3GentradeCsvColumnModel(ACCOUNT_CODE_LABEL, 2, WEB3GentradeCsvColumnModel.STRINGTYPE, null);
        columnHeader[3] =
            new WEB3GentradeCsvColumnModel(ACCOUNT_NAME_LABEL, 3, WEB3GentradeCsvColumnModel.STRINGTYPE, null);
            
        /**
         * 信用区分追加 2006.04.27
         * SCS佐藤
         */
        columnHeader[4] =
            new WEB3GentradeCsvColumnModel(
                MARGIN_DIV_LABEL, 
                4, 
                WEB3GentradeCsvColumnModel.STRINGTYPE, 
                null);            
   
        columnHeader[5] =
            new WEB3GentradeCsvColumnModel(
                PUBLIC_OFFERING_PRICE_LABEL, 
                5, 
                WEB3GentradeCsvColumnModel.DOUBLETYPE, 
                null);
        columnHeader[6] =
            new WEB3GentradeCsvColumnModel(LOT_RESULT_LABEL, 6, WEB3GentradeCsvColumnModel.STRINGTYPE, null);
        columnHeader[7] =
            new WEB3GentradeCsvColumnModel(ELECTED_QUANTITY_LABEL, 7, WEB3GentradeCsvColumnModel.DOUBLETYPE, null);
        columnHeader[8] =
            new WEB3GentradeCsvColumnModel(APPLICATION_QUANTITY_LABEL, 8, WEB3GentradeCsvColumnModel.DOUBLETYPE, null);
        columnHeader[9] =
            new WEB3GentradeCsvColumnModel(
                OFFERING_TIMESTAMP_LABEL, 
                9, 
                WEB3GentradeCsvColumnModel.TIMESTAMPTYPE, 
                new SimpleDateFormat("yyyy/MM/dd HH:mm:ss"));
        columnHeader[10] =
            new WEB3GentradeCsvColumnModel(OFFER_STATE_LABEL, 10, WEB3GentradeCsvColumnModel.STRINGTYPE, null);
        columnHeader[11] =
            new WEB3GentradeCsvColumnModel(ACCEPT_STATUS_LABEL, 11, WEB3GentradeCsvColumnModel.STRINGTYPE, null);
        columnHeader[12] =
            new WEB3GentradeCsvColumnModel(SUBSTITUTE_PRIORITY, 12, WEB3GentradeCsvColumnModel.LONGTYPE, null);
        
        /**
         * 抽選番号追加 2006.04.27
         * SCS佐藤
         */
        columnHeader[13] =
            new WEB3GentradeCsvColumnModel(LOT_NUMBER_LABEL, 13, WEB3GentradeCsvColumnModel.STRINGTYPE, null);
        
        setColumnHeader(columnHeader);
        
        log.exiting(STR_METHOD_NAME);
    }
    
    /**
     * (createキーヘッダ)<BR>
     * キーヘッダをセットする。<BR>
     * <BR>
     * 　@以下の通り文字列の配列を生成し、<BR>setキーヘッダ()にてインスタンスにセットする。<BR>
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
     * @@param l_ipoProduct - IPO銘柄オブジェクト
     * @@roseuid 40ECA8630326
     */
    private void createKeyHeader(WEB3IpoProductImpl l_ipoProduct) throws WEB3BaseException
    {
        final String STR_METHOD_NAME = " createKeyHeader(WEB3IpoProductImpl)";
        log.entering(STR_METHOD_NAME);
        
        String[] l_strKeyHeader = new String[3];
        
        FinApp l_finApp = (FinApp) Services.getService(FinApp.class);
        TradingSystem l_tradingSys = l_finApp.getTradingSystem();
        Timestamp l_processTime = l_tradingSys.getSystemTimestamp();
        
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
     * 　@項目ラベル：　@抽選結果・購入申込状況CSV.部店コードラベル<BR>
     * <BR>
     * ２）　@値セット<BR>
     * 　@this.set項目値()にて項目値をセットする。<BR>
     * <BR>
     * 　@[set項目値()に指定する引数]<BR>
     * 　@行番号：　@引数の行番号<BR>
     * 　@カラム：　@１）で取得したカラムモデル<BR>
     * 　@値：　@部店ＩＤに該当する部店オブジェクト.getBranchCode()<BR>
     * <BR>
     * <BR>
     * @@param l_intLineNumber - 行番号を指定する。
     * @@param l_lngBranchId - 部店ＩＤ
     * @@roseuid 40ECA8630346
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
            FinApp l_finApp = (FinApp) Services.getService(FinApp.class);
            Branch l_branch = l_finApp.getAccountManager().getBranch(l_lngBranchId); //throw NotFoundException
            String l_strValue = l_branch.getBranchCode();
            
            //値セット
            this.setValue(l_intLineNumber, l_csvDataModel, l_strValue);
        }
        catch (NotFoundException l_ex)
        {
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(WEB3ErrorCatalog.SYSTEM_ERROR_80006, this.getClass().getName() + STR_METHOD_NAME, l_ex.getMessage(), l_ex);
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
     * 　@項目ラベル：　@抽選結果・購入申込状況CSV.顧客コードラベル<BR>
     * <BR>
     * ３）　@（顧客コード）値セット<BR>
     * 　@this.set項目値()にて項目値をセットする。<BR>
     * <BR>
     * 　@[set項目値()に指定する引数]<BR>
     * 　@行番号：　@引数の行番号<BR>
     * 　@カラム：　@２）で取得したカラムモデル<BR>
     *   値：　@顧客.getAccountCode()の左6byte<BR>
     * <BR>
     * ４）　@（顧客名）カラムモデル取得<BR>
     * 　@this.getカラムモデル()にてCSVカラムモデルを取得する。<BR>
     * <BR>
     * 　@[getカラムモデル()に指定する引数]<BR>
     * 　@項目ラベル：　@抽選結果・購入申込状況CSV.顧客名ラベル<BR>
     * <BR>
     * ５）　@（顧客名）値セット<BR>
     * 　@this.set項目値()にて項目値をセットする。<BR>
     * <BR>
     * 　@[set項目値()に指定する引数]<BR>
     * 　@行番号：　@引数の行番号<BR>
     * 　@カラム：　@４）で取得したカラムモデル<BR>
     * 　@値：　@顧客.get顧客表示名()<BR>
     * <BR>
     * @@param l_intLineNumber - 行番号を指定する。
     * @@param l_lngAccountId - 口座ＩＤ
     * @@roseuid 40ECA8630355
     */
    public void setAccount(int l_intLineNumber, long l_lngAccountId) throws WEB3BaseException 
    {
        final String STR_METHOD_NAME = " setAccount(int, long)";
        log.entering(STR_METHOD_NAME);
        
        try
        {
            //顧客オブジェクト取得
            FinApp l_finApp = (FinApp) Services.getService(FinApp.class);
            MainAccount l_mainAccount= l_finApp.getAccountManager().getMainAccount(l_lngAccountId); //throw NotFoundException
            
            MainAccountRow l_mainAccountRow = (MainAccountRow)l_mainAccount.getDataSourceObject();
            
            //（顧客コード）カラムモデル取得
            WEB3GentradeCsvColumnModel l_csvDataModelAccountCode = this.getColumnModel(ACCOUNT_CODE_LABEL);
            
            //（顧客コード）値セット
            String l_strCode = l_mainAccount.getAccountCode();
            this.setValue(l_intLineNumber, l_csvDataModelAccountCode, l_strCode.substring(0,6));
            
            //（顧客名）カラムモデル取得
            WEB3GentradeCsvColumnModel l_csvDataModelAccountName = this.getColumnModel(ACCOUNT_NAME_LABEL);
            
            //（顧客名）値セット
			// 2004/12/10 障害管理票No.U00545 顧客名の値を「顧客の名前(苗字)＋ "　@" ＋名前(名前)」になるように修正。情野@@SRA START
			String l_strName = l_mainAccountRow.getFamilyName();
//            String l_strName = l_mainAccountRow.getFamilyName() + " " + l_mainAccountRow.getGivenName();
			// 2004/12/10 障害管理票No.U00545 顧客名の値を「顧客の名前(苗字)＋ "　@" ＋名前(名前)」になるように修正。情野@@SRA END
			
            this.setValue(l_intLineNumber, l_csvDataModelAccountName, l_strName);
            
        }
        catch (NotFoundException l_ex)
        {
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(WEB3ErrorCatalog.SYSTEM_ERROR_80006, this.getClass().getName() + STR_METHOD_NAME, l_ex.getMessage(), l_ex);
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
     * 　@項目ラベル：　@抽選結果・購入申込状況CSV.受付結果ラベル<BR>
     * <BR>
     * ２）　@値セット<BR>
     * 　@this.set項目値()にて項目値をセットする。<BR>
     * <BR>
     * 　@[set項目値()に指定する引数]<BR>
     * 　@行番号：　@引数の行番号<BR>
     * 　@カラム：　@１）で取得したカラムモデル<BR>
     * 　@値：　@<BR>
     * 　@　@（抽選結果 == ”当選”）の場合、<BR>
     * 　@　@　@－抽選結果・購入申込状況CSV.抽選結果_当選表示。<BR>
     * <BR>
     * 　@　@（抽選結果 == ”補欠” && 抽選結果（繰上） == ”当選”） の場合、<BR>
     * 　@　@　@－抽選結果・購入申込状況CSV.抽選結果_補欠当選表示。<BR>
     * <BR>
     * 　@　@（抽選結果 == ”補欠” && 抽選結果（繰上） == ”落選”） の場合、<BR>
     * 　@　@　@－抽選結果・購入申込状況CSV.抽選結果_補欠落選表示。<BR>
     * <BR>
     * 　@　@（抽選結果 == ”補欠” && 抽選結果（繰上） == ”DEFAULT（未抽選）”） の場合、<BR>
     * 　@　@　@－抽選結果・購入申込状況CSV.抽選結果_補欠表示。<BR>
     * <BR>
     * 　@　@（抽選結果 == ”落選”）の場合、<BR>
     * 　@　@　@－抽選結果・購入申込状況CSV.抽選結果_落選表示。<BR>
     * <BR>
     * @@param l_intLineNumber - 行番号を指定する。
     * @@param l_strLotResult - 抽選結果<BR>
     * <BR>
     * 0：　@DEFAULT（未抽選）<BR>
     * 1：　@当選<BR>
     * 2：　@補欠<BR>
     * 3：　@落選<BR>
     * 
     * @@param l_strLotResultRetry - 抽選結果（繰上）<BR>
     * <BR>
     * 0：　@DEFAULT（未抽選）<BR>
     * 1：　@当選<BR>
     * 3：　@落選<BR>
     * @@roseuid 40ECAD250077
     */
    public void setLotResult(int l_intLineNumber, String l_strLotResult, String l_strLotResultRetry) 
    {
        final String STR_METHOD_NAME = " setLotResult(int, String, String)";
        log.entering(STR_METHOD_NAME);
        
        //カラムモデル取得
        WEB3GentradeCsvColumnModel l_csvDataModel = this.getColumnModel(LOT_RESULT_LABEL);
        
        //値セット
        String l_strResult = null;
        if (WEB3LotResultDef.ELECTION.equals(l_strLotResult))
        {
            l_strResult = LOT_RESULT_PRIZE_INDICATION;
        }
        else if (WEB3LotResultDef.SUPPLEMENT.equals(l_strLotResult) && WEB3LotResultRetryDef.ELECTION.equals(l_strLotResultRetry))
        {
            l_strResult = LOT_RESULT_WAITING_PRIZE_INDICATION;
        }
        else if (WEB3LotResultDef.SUPPLEMENT.equals(l_strLotResult) && WEB3LotResultRetryDef.DEFEAT.equals(l_strLotResultRetry))
        {
            l_strResult = LOT_RESULT_WAITING_REJECTED_INDICATION;
        }
        else if (WEB3LotResultDef.SUPPLEMENT.equals(l_strLotResult) && WEB3LotResultRetryDef.DEFAULT.equals(l_strLotResultRetry))
        {
            l_strResult = LOT_RESULT_WAITING_INDICATION;
        }
        else if (WEB3LotResultDef.DEFEAT.equals(l_strLotResult))
        {
            l_strResult = LOT_RESULT_REJECTED_INDICATION;
        }
        
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
     * 　@[getカラムモデル()に指定する引数]<BR>
     * 　@項目ラベル：　@抽選結果・購入申込状況CSV.当選数量ラベル<BR>
     * <BR>
     * ２）　@値セット<BR>
     * 　@this.set項目値()にて項目値をセットする。<BR>
     * <BR>
     * 　@[set項目値()に指定する引数]<BR>
     * 　@行番号：　@引数の行番号<BR>
     * 　@カラム：　@１）で取得したカラムモデル<BR>
     * 　@値：　@当選数量<BR>
     * <BR>
     * @@param l_intLineNumber - 行番号を指定する。
     * @@param l_dblElectedQuantity - 当選数量
     * @@roseuid 40ECA8630394
     */
    public void setElectedQuantity(int l_intLineNumber, double l_dblElectedQuantity) 
    {
        final String STR_METHOD_NAME = " setElectedQuantity(int, double)";
        log.entering(STR_METHOD_NAME);
        
        //カラムモデル取得
        WEB3GentradeCsvColumnModel l_csvDataModel = this.getColumnModel(ELECTED_QUANTITY_LABEL);
        
        //値セット
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
     * 　@項目ラベル：　@抽選結果・購入申込状況CSV.購入申込数量ラベル<BR>
     * <BR>
     * ２）　@値セット<BR>
     * 　@this.set項目値()にて項目値をセットする。<BR>
     * <BR>
     * 　@[set項目値()に指定する引数]<BR>
     * 　@行番号：　@引数の行番号<BR>
     * 　@カラム：　@１）で取得したカラムモデル<BR>
     * 　@値：　@購入申込数量<BR>
     * <BR>
     * @@param l_intLineNumber - 行番号を指定する。
     * @@param l_dblApplicationQuantity - 購入申込数量
     * @@roseuid 40ECA86303B3
     */
    public void setApplicationQuantity(int l_intLineNumber, double l_dblApplicationQuantity) 
    {
        final String STR_METHOD_NAME = " setApplicationQuantity(int, double)";
        log.entering(STR_METHOD_NAME);
        
        //カラムモデル取得
        WEB3GentradeCsvColumnModel l_csvDataModel = this.getColumnModel(APPLICATION_QUANTITY_LABEL);
        
        //値セット
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
     * 　@項目ラベル：　@抽選結果・購入申込状況CSV.購入申込／辞退日時ラベル
     * <BR>
     * ２）　@値セット<BR>
     * 　@this.set項目値()にて項目値をセットする。<BR>
     * <BR>
     * 　@[set項目値()に指定する引数]<BR>
     * 　@行番号：　@引数の行番号<BR>
     * 　@カラム：　@１）で取得したカラムモデル<BR>
     * 　@値：　@購入申込／辞退日時<BR>
     * <BR>
     * @@param l_intLineNumber - 行番号を指定する。
     * @@param l_datOfferingTimestamp - 購入申込／辞退日時
     * @@roseuid 40ECB2E800E4
     */
    public void setOfferingTimestamp(int l_intLineNumber, Date l_datOfferingTimestamp) 
    {
        final String STR_METHOD_NAME = " setOfferingTimestamp(int, Date)";
        log.entering(STR_METHOD_NAME);
        
        //カラムモデル取得
        WEB3GentradeCsvColumnModel l_csvDataModel = this.getColumnModel(OFFERING_TIMESTAMP_LABEL);
        
        //値セット
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
     * 　@項目ラベル：　@抽選結果・購入申込状況CSV.購入申込状態ラベル<BR>
     * <BR>
     * ２）　@値セット<BR>
     * 　@this.set項目値()にて項目値をセットする。<BR>
     * <BR>
     * 　@[set項目値()に指定する引数]<BR>
     * 　@行番号：　@引数の行番号<BR>
     * 　@カラム：　@１）で取得したカラムモデル<BR>
     * 　@値：　@<BR>
     * 　@　@（購入申込区分 == ”DEFAULT（初期値）”）の場合、<BR>
     * 　@　@　@－抽選結果・購入申込状況CSV.購入申込状態_なし表示。<BR>
     * <BR>
     * 　@　@（購入申込区分 == ”購入申込”）の場合、<BR>
     * 　@　@　@－抽選結果・購入申込状況CSV.購入申込状態_申込表示。<BR>
     * <BR>
     * 　@　@（購入申込区分 == ”辞退”）の場合、<BR>
     * 　@　@　@－抽選結果・購入申込状況CSV.購入申込状態_辞退表示。<BR>
     * <BR>
     * @@param l_intLineNumber - 行番号を指定する。
     * @@param l_strOfferDiv - 購入申込区分<BR>
     * <BR>
     * 0：　@DEFAULT（初期値）<BR>
     * 1：　@購入申込<BR>
     * 2：　@辞退<BR>
     * @@roseuid 40ECB3130190
     */
    public void setOfferStatus(int l_intLineNumber, String l_strOfferDiv) 
    {
        final String STR_METHOD_NAME = " setOfferStatus(int, String)";
        log.entering(STR_METHOD_NAME);
        
        //カラムモデル取得
        WEB3GentradeCsvColumnModel l_csvDataModel = this.getColumnModel(OFFER_STATE_LABEL);
        
        //値セット
        String l_strOfferState = null;
        if (WEB3OfferingDivDef.DEFAULT.equals(l_strOfferDiv))
        {
            l_strOfferState = OFFER_STATE_NO_INDICATION;
        }
        else if (WEB3OfferingDivDef.PURCHASE_APPLICATION.equals(l_strOfferDiv))
        {
            l_strOfferState = OFFER_STATE_APPLICATION_INDICATION;
        }
        else if (WEB3OfferingDivDef.REFUSAL.equals(l_strOfferDiv))
        {
            l_strOfferState = OFFER_STATE_CANCEL_INDICATION;
        }
        
        this.setValue(l_intLineNumber, l_csvDataModel, l_strOfferState);
        
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
     * 　@項目ラベル：　@抽選結果・購入申込状況CSV.受付状態ラベル<BR>
     * <BR>
     * ２）　@値セット<BR>
     * 　@this.set項目値()にて項目値をセットする。<BR>
     * <BR>
     * 　@[set項目値()に指定する引数]<BR>
     * 　@行番号：　@引数の行番号<BR>
     * 　@カラム：　@１）で取得したカラムモデル<BR>
     * 　@値：　@<BR>
     * 　@　@（受付状態 == ”DEFAULT（初期値）”）の場合、<BR>
     * 　@　@　@－抽選結果・購入申込状況CSV.受付状態_なし表示。<BR>
     * <BR>
     * 　@　@（受付状態 == ”SONAR送信済”）の場合、<BR>
     * 　@　@　@－抽選結果・購入申込状況CSV.受付状態_受付中表示。<BR>
     * <BR>
     * 　@　@（受付状態 == ”SONAR反映済”）の場合、<BR>
     * 　@　@　@－抽選結果・購入申込状況CSV.受付状態_受付済表示。<BR>
     * <BR>
     * @@param l_intLineNumber - 行番号を指定する。
     * @@param l_strAcceptStatus - 受付状態<BR>
     * <BR>
     * 0：　@DEFAULT（初期値）<BR>
     * 1：　@SONAR送信済<BR>
     * 2：　@SONAR反映済<BR>
     * @@roseuid 40ECB3D2027B
     */
    public void setAcceptStatus(int l_intLineNumber, String l_strAcceptStatus) 
    {
        final String STR_METHOD_NAME = " setAcceptStatus(int, String)";
        log.entering(STR_METHOD_NAME);
        
        //カラムモデル取得
        WEB3GentradeCsvColumnModel l_csvDataModel = this.getColumnModel(ACCEPT_STATUS_LABEL);
        
        //値セット
        String l_strState = null;
        if (WEB3IpoOrderAcceptStatusDef.DEFAULT.equals(l_strAcceptStatus))
        {
            l_strState = RECEIVE_STATE_NO_INDICATION;
        }
        else if (WEB3IpoOrderAcceptStatusDef.SONAR_MAIL_SENDED.equals(l_strAcceptStatus))
        {
            l_strState = RECEIVE_STATE_RECEIVE_INDICATION;
        }
        else if (WEB3IpoOrderAcceptStatusDef.SONAR_REFLECTED.equals(l_strAcceptStatus))
        {
            l_strState = RECEIVE_STATE_RECEIVE_END_INDICATION;
        }
        
        this.setValue(l_intLineNumber, l_csvDataModel, l_strState);
        
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
     * 　@項目ラベル：　@抽選結果・購入申込状況CSV.優先順位ラベル<BR>
     * <BR>
     * ２）　@値セット<BR>
     * 　@this.set項目値()にて項目値をセットする。<BR>
     * <BR>
     * 　@[set項目値()に指定する引数]<BR>
     * 　@行番号：　@引数の行番号<BR>
     * 　@カラム：　@１）で取得したカラムモデル<BR>
     * 　@値：　@優先順位<BR>
     * <BR>
     * @@param l_intLineNumber - 行番号を指定する。
     * @@param l_lngSubstitutePriority - 優先順位
     * @@roseuid 40ECB47B0077
     */
    public void setSubstitutePriority(int l_intLineNumber, Long l_lngSubstitutePriority) 
    {
        final String STR_METHOD_NAME = " setSubstitutePriority(int, Long)";
        log.entering(STR_METHOD_NAME);
        
        //カラムモデル取得
        WEB3GentradeCsvColumnModel l_csvDataModel = this.getColumnModel(SUBSTITUTE_PRIORITY);
        
        //値セット
        this.setValue(l_intLineNumber, l_csvDataModel, l_lngSubstitutePriority);
        
        log.exiting(STR_METHOD_NAME);             
    }
    
    /**
     * (set扱者コード)<BR>
     * 扱者コードをセットする。<BR>
     * <BR>
     * １）　@顧客オブジェクト取得<BR>
     * 　@口座ＩＤに該当する顧客オブジェクトを取得する。<BR>
     * <BR>
     * ２）　@（扱者コード）カラムモデル取得<BR>
     * 　@this.getカラムモデル()にてCSVカラムモデルを取得する。<BR>
     * <BR>
     * 　@[getカラムモデル()に指定する引数]<BR>
     * 　@項目ラベル：　@　@抽選結果・購入申込状況CSV.扱者コードラベル<BR>
     * <BR>
     * ３）　@（扱者コード）値セット<BR>
     * 　@this.set項目値()にて項目値をセットする。<BR>
     * <BR>
     * 　@[set項目値()に指定する引数]<BR>
     * 　@行番号：　@引数の行番号<BR>
     * 　@カラム：　@２）で取得したカラムモデル<BR>
     * 　@値：　@顧客.getSonarTraderCode()<BR>
     * <BR>
     * @@param l_intLineNumber - 行番号を指定する。
     * @@param l_lngAccountId - 口座ID
     * @@throws WEB3BaseException
     */
    public void setTradeCode(int l_intLineNumber, long l_lngAccountId) throws WEB3BaseException 
    {
        final String STR_METHOD_NAME = " setTradeCode(int, long)";
        log.entering(STR_METHOD_NAME);
     
        //１）　@顧客オブジェクト取得
        MainAccount l_mainAccount = null;
        try
        {
            FinApp l_finApp = (FinApp) Services.getService(FinApp.class);
            l_mainAccount = l_finApp.getAccountManager().getMainAccount(l_lngAccountId);
        }
        catch (NotFoundException l_nfe)
        {
            log.error("テーブルに該当するデータがありません");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80005,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                l_nfe.getMessage(),
                l_nfe);
        }
        
        //２）　@（扱者コード）カラムモデル取得
        WEB3GentradeCsvColumnModel l_csvColumnModel = this.getColumnModel(TRADER_CODE_LABEL);
        
        //３）　@（扱者コード）値セット
        MainAccountRow l_mainAccountRow = (MainAccountRow) l_mainAccount.getDataSourceObject();
        this.setValue(l_intLineNumber, l_csvColumnModel, l_mainAccountRow.getSonarTraderCode());
        
        log.exiting(STR_METHOD_NAME);
    }    
    
    /**
     * (set公開価格)<BR>
     * 公開価格をセットする。<BR>
     * <BR>
     * １）　@カラムモデル取得<BR>
     * 　@this.getカラムモデル()にてCSVカラムモデルを取得する。<BR>
     * <BR>
     * 　@[getカラムモデル()に指定する引数]<BR>
     * 　@項目ラベル：　@　@抽選結果・購入申込状況CSV.公開価格ラベル<BR>
     * <BR>
     * ２）　@値セット<BR>
     * 　@this.set項目値()にて項目値をセットする。<BR>
     * <BR>
     * 　@[set項目値()に指定する引数]<BR>
     * 　@行番号：　@引数の行番号<BR>
     * 　@カラム：　@１）で取得したカラムモデル<BR>
     * 　@値：　@引数の公開価格<BR>
     * <BR>
     * @@param l_intLineNumber - 行番号を指定する。
     * @@param l_dblPublicOfferingPrice - 公開価格
     */
    public void setPublicOfferingPrice(int l_intLineNumber, Double l_dblPublicOfferingPrice)
    {
        final String STR_METHOD_NAME = " setPublicPrice(int, Double)";
        log.entering(STR_METHOD_NAME);
        
        //１）　@カラムモデル取得
        WEB3GentradeCsvColumnModel l_cvsColumnModel = this.getColumnModel(PUBLIC_OFFERING_PRICE_LABEL);
        
        //２）　@値セット
        this.setValue(l_intLineNumber, l_cvsColumnModel, l_dblPublicOfferingPrice);
        
        log.exiting(STR_METHOD_NAME);
    }
    
    /**
     * (set信用区分)<BR>
     * 信用区分をセットする。<BR>
     * <BR>
     * １）　@カラムモデル取得<BR>
     * 　@this.getカラムモデル()にてCSVカラムモデルを取得する。<BR>
     * <BR>
     * 　@[getカラムモデル()に指定する引数]<BR>
     * 　@項目ラベル：　@抽選結果・購入申込状況CSV.信用区分ラベル<BR>
     * <BR>
     * ２）　@値セット<BR>
     * 　@this.set項目値()にて項目値をセットする。<BR>
     * <BR>
     * 　@[set項目値()に指定する引数]<BR>
     * 　@行番号：　@引数の行番号<BR>
     * 　@カラム：　@１）で取得したカラムモデル<BR>
     * 　@値：　@<BR>
     * 　@　@引数：信用口座開設状況 = true の場合、<BR>
     * 　@　@　@－抽選結果・購入申込状況CSV.信用口座開設表示。<BR>
     * <BR>
     * 　@　@引数：信用口座開設状況 = false の場合、<BR>
     * 　@　@　@－抽選結果・購入申込状況CSV.信用口座未開設表示。<BR>
     * <BR>
     * @@param l_intLineNumber - 行番号を指定する。
     * @@param l_lngAccountId - 口座ID
     * @@author SCS佐藤 2006.04.27
     * <BR>
     */
    public void setMarginDiv(int l_intLineNumber, long l_lngAccountId) throws WEB3BaseException  
    {
        final String STR_METHOD_NAME = " setMarginDiv(int, long)";
        log.entering(STR_METHOD_NAME);
        
        //１）　@顧客オブジェクト取得
        WEB3GentradeMainAccount l_genMainAccount = null;
//        MainAccount l_mainAccount = null;
        try
        {
            FinApp l_finApp = (FinApp) Services.getService(FinApp.class);
            l_genMainAccount = (WEB3GentradeMainAccount)l_finApp.getAccountManager().getMainAccount(l_lngAccountId);
//            l_mainAccount = l_finApp.getAccountManager().getMainAccount(l_lngAccountId);
        }
        catch (NotFoundException l_nfe)
        {
            log.error("テーブルに該当するデータがありません");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80005,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                l_nfe.getMessage(),
                l_nfe);
        }
        
        //カラムモデル取得
        WEB3GentradeCsvColumnModel l_csvDataModel = this.getColumnModel(MARGIN_DIV_LABEL);
        
        //値セット
        String l_strState = null;
        if (l_genMainAccount.isMarginAccountEstablished(WEB3GentradeRepaymentDivDef.DEFAULT))
        {
            l_strState = MARGIN_ACC_OPEN;
        }
        else
        {
            l_strState = MARGIN_ACC_CLOSE;
        }
        
        this.setValue(l_intLineNumber, l_csvDataModel, l_strState);
        
        log.exiting(STR_METHOD_NAME);
    }
    
    /**
     * (set抽選番号)<BR>
     * 抽選番号をセットする。<BR>
     * <BR>
     * １）　@カラムモデル取得<BR>
     * 　@this.getカラムモデル()にてCSVカラムモデルを取得する。<BR>
     * <BR>
     * 　@[getカラムモデル()に指定する引数]<BR>
     * 　@項目ラベル：　@抽選結果・購入申込状況CSV.抽選番号ラベル<BR>
     * <BR>
     * ２）　@値セット<BR>
     * 　@this.set項目値()にて項目値をセットする。<BR>
     * <BR>
     * 　@[set項目値()に指定する引数]<BR>
     * 　@行番号：　@引数の行番号<BR>
     * 　@カラム：　@１）で取得したカラムモデル<BR>
     * 　@値：　@抽選番号<BR>
     * <BR>
     * @@param l_intLineNumber - 行番号を指定する。
     * @@param l_strLotNumber - 抽選番号
     * @@author SCS佐藤　@2006.04.27
     */
    public void setLotNumber(int l_intLineNumber, String l_strLotNumber) throws WEB3BaseException 
    {
        final String STR_METHOD_NAME = " setLotNumber(int, String)";
        log.entering(STR_METHOD_NAME);
                
        //１）カラムモデル取得
        WEB3GentradeCsvColumnModel l_csvDataModel = this.getColumnModel(LOT_NUMBER_LABEL);
        
        //２）（抽選番号）値セット
        this.setValue(l_intLineNumber, l_csvDataModel, l_strLotNumber);
        
        log.exiting(STR_METHOD_NAME);        
    }    
}
@
