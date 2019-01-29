head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.16.02.26.51;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	6a04d801c24738b;
filename	WEB3AdminAioCashoutInqDownloadCsv.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright           : (株)大和総研 証券ソリューションシステム第二部
File Name　@　@　@　@　@: 出金申込問合せダウンロードCSV (WEB3AdminAioCashoutInqDownloadCsv.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2006/07/28 齊珂 (中訊) 新規作成      
*/

package webbroker3.aio;

import java.text.SimpleDateFormat;
import java.util.Date;

import webbroker3.aio.define.WEB3AioCancelDivDef;
import webbroker3.aio.define.WEB3AioOrderAcceptedDivDef;
import webbroker3.common.WEB3BaseRuntimeException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.common.define.WEB3FinSaveDivDef;
import webbroker3.gentrade.WEB3GentradeCsvColumnModel;
import webbroker3.gentrade.WEB3GentradeCsvDataModel;
import webbroker3.gentrade.WEB3GentradeMainAccount;
import webbroker3.tradingpower.WEB3TPTradingPowerCalcEquity;
import webbroker3.tradingpower.WEB3TPTradingPowerCalcMargin;
import webbroker3.util.WEB3DateUtility;
import webbroker3.util.WEB3LogUtility;

import com.fitechlabs.xtrade.kernel.boot.Services;
import com.fitechlabs.xtrade.plugin.tc.gentrade.AccountManager;
import com.fitechlabs.xtrade.plugin.tc.gentrade.Branch;
import com.fitechlabs.xtrade.plugin.tc.gentrade.FinApp;
import com.fitechlabs.xtrade.plugin.tc.gentrade.GtlUtils;
import com.fitechlabs.xtrade.plugin.tc.gentrade.NotFoundException;

/**
 * (出金申込問合せダウンロードCSV)<BR>
 * 出金請求ファ@イルダウンロードで作成するCSVファ@イルデータクラス 
 *
 * @@author 齊珂 (中訊)
 * @@version 1.0
 */

public class WEB3AdminAioCashoutInqDownloadCsv extends WEB3GentradeCsvDataModel
{

    /**
     * ログ出力オブジェクト。<BR>
     */
    private static WEB3LogUtility log =
        WEB3LogUtility.getInstance(WEB3AdminAioVirtualAccCashinULCsv.class);
    
    /**
     * (部店ラベル)<BR>
     * 部店ラベル<BR>
     */
    public static final String BRANCH_LABEL = "部店";
    
    /**
     * (顧客コードラベル)<BR>
     * 顧客コードラベル<BR>
     */
    public static final String ACCOUNT_CODE_LABEL = "顧客コード";

    /**
     * (顧客名ラベル)<BR>
     * 顧客名ラベル<BR>
     */
    public static final String ACCOUNT_NAME_LABEL = "顧客名";
    
    /**
     * (注文日時ラベル)<BR>
     * 注文日時ラベル<BR>
     */
    public static final String ORDER_DATE_LABEL = "注文日時";
    
    /**
     * (受渡日ラベル)<BR>
     * 受渡日ラベル<BR>
     */
    public static final String DELIVERY_DATE_LABEL = "受渡日";

    /**
     * (出金額ラベル)<BR>
     * 出金額ラベル<BR>
     */
    public static final String CASHINGOUT_AMOUNT_LABEL = "出金額";
    
    /**
     * (注文状態ラベル)<BR>
     * 注文状態ラベル<BR>
     */
    public static final String ORDER_STATUS_LABEL = "注文状態";
    
    /**
     * (取消区分ラベル)<BR>
     * 取消区分ラベル<BR>
     */
    public static final String CANCEL_DIV_LABEL = "取消区分";
    
    /**
     * (取消日時ラベル)<BR>
     * 取消日時ラベル<BR>
     */
    public static final String CANCEL_DATE_LABEL = "取消日時";
    
    /**
     * (振込先銀行コードラベル)<BR>
     * 振込先銀行コードラベル<BR>
     */
    public static final String TRANSFER_BANK_CODE_LABEL = "振込先銀行コード";
    
    /**
     * (振込先支店コードラベル )<BR>
     * 振込先支店コードラベル<BR>
     */
    public static final String TRANSFER_BRANCH_CODE_LABEL = "振込先支店コード";
    
    /**
     * (預金区分ラベル)<BR>
     * 預金区分ラベル<BR>
     */
    public static final String DEPOSIT_DIV_LABEL = "預金区分";
    
    /**
     * (振込先口座番号ラベル)<BR>
     * 振込先口座番号ラベル<BR>
     */
    public static final String TRANSFER_ACCOUNT_NUMBER_LABEL = "振込先口座番号";
    
    /**
     * (振込先口座名義人ラベル)<BR>
     * 振込先口座名義人ラベル<BR>
     */
    public static final String TRANSFER_ACCOUNT_NAME_LABEL = "振込先口座名義人";
    
    /**
     * (出金可能額（当日）ラベル)<BR>
     * 出金可能額（当日）ラベル<BR>
     */
    public static final String CASHOUT_POSSIBLE_PRICE_TODAY_LABEL = "出金可能額（当日）";
    
    /**
     * (出金可能額（1営業日後）ラベル)<BR>
     * 出金可能額（1営業日後）ラベル<BR>
     */
    public static final String CASHOUT_POSSIBLE_PRICE_1DAYAFTER_LABEL = "出金可能額（１営業日後）";
    
    /**
     * (出金可能額（2営業日後）ラベル)<BR>
     * 出金可能額（2営業日後）ラベル<BR>
     */
    public static final String CASHOUT_POSSIBLE_PRICE_2DAYSAFTER_LABEL = "出金可能額（2営業日後）";
    
    /**
     * (出金可能額（3営業日後）ラベル)<BR>
     * 出金可能額（3営業日後）ラベル<BR>
     */
    public static  final String CASHOUT_POSSIBLE_PRICE_3DAYSAFTER_LABEL = "出金可能額（3営業日後）";
    
    /**
     * (出金可能額（4営業日後）ラベル)<BR>
     * 出金可能額（4営業日後）ラベル<BR>
     */
    public static final String CASHOUT_POSSIBLE_PRICE_4DAYSAFTER_LABEL = "出金可能額（4営業日後）";
    
    /**
     * (出金可能額（5営業日後）ラベル)<BR>
     * 出金可能額（5営業日後）ラベル<BR>
     */
    public static final String CASHOUT_POSSIBLE_PRICE_5DAYSAFTER_LABEL = "出金可能額（5営業日後）";
    
    /**
     * (注文状態_受付未済表示)<BR>
     * 定数定義プロパティ　@注文状態_受付未済表示<BR>
     */
    public static final String ORDER_STATUS_NOT_ACCEPTED = "受付未済";
    
    /**
     * (注文状態_受付済表示)<BR>
     * 定数定義プロパティ　@注文状態_受付済表示<BR>
     */
    public static final String ORDER_STATUS_ACCEPTED= "受付済";
    
    /**
     * (注文状態_受付エラー表示)<BR>
     * 定数定義プロパティ　@注文状態_受付エラー表示<BR>
     */
    public static final String ORDER_STATUS_ACCEPTED_ERROR = "受付エラー";
    
    /**
     * (注文状態_受付中表示)<BR>
     * 定数定義プロパティ　@注文状態_受付中表示<BR>
     */
    public static final String ORDER_STATUS_ACCEPTING = "受付中";
    
    /**
     * (取消区分_取消済表示)<BR>
     * 定数定義プロパティ　@取消区分_取消済表示<BR>
     */
    public static final String CANCEL_DIV_CANCELED = "取消済";
    
    /**
     * (預金区分_普通預金表示)<BR>
     * 定数定義プロパティ　@預金区分_普通預金表示<BR>
     */
    public static final String DEPOSIT_DIV_GENERAL = "普通預金";
    
    /**
     * (預金区分_当座預金表示)<BR>
     * 定数定義プロパティ　@預金区分_当座預金表示<BR>
     */
    public static final String DEPOSIT_DIV_ACCOUNT = "当座預金";
    
    /**
     * (預金区分_貯蓄預金表示)<BR>
     * 定数定義プロパティ　@預金区分_貯蓄預金表示<BR>
     */
    public static final String DEPOSIT_DIV_SAVING = "貯蓄預金";
    
    /**
     * (預金区分_その他表示)<BR>
     * 定数定義プロパティ　@預金区分_その他表示<BR>
     */
    public static final String DEPOSIT_DIV_OTHER = "その他";
    
    /**
     * (出金申込問合わせダウンロードCSV )<BR>
     * コンストラクタ <BR>
     * <BR>
     * インスタンスを生成し、ヘッダ情報をセットする。<BR>
     * 　@－super()にてインスタンスを生成する。 <BR>
     * 　@－createキーヘッダ()をコールし、キーヘッダ情報を作成する。 <BR>
     * 　@－createカラムヘッダ()をコールし、カラムヘッダ情報を作成する。 <BR>
     * @@roseuid 4456FB6A02CD
     */
    public WEB3AdminAioCashoutInqDownloadCsv () 
    {
        //super()にてインスタンスを生成する。
        super();
        
        //createキーヘッダ()をコールし、キーヘッダ情報を作成する。 
        this.createKeyHeader();
        
        //createカラムヘッダ()をコールし、カラムヘッダ情報を作成する。  
        this.createColumnHeader();

    }
    
    /**
     * (createカラムヘッダ)<BR>
     * カラムヘッダをセットする。<BR>
     * 以下の通りCSVカラムモデルの配列を生成し、<BR>
     * setカラムヘッダ()にてインスタンスにセットする。<BR>
     * 以下の通りCSVカラムモデルの配列を生成し、setカラムヘッダ()にてインスタンスにセットする。 <BR>
     * <BR>
     * [カラムヘッダ配列] <BR>
     * －　@index = 0 <BR>
     * 　@[CSVカラムモデル コンストラクタの引数] <BR>
     * 　@項目ラベル：　@出金申込問合せダウンロードCSV.部店ラベル  <BR>
     * 　@カラム番号： 0 <BR>
     * 　@項目型：　@CSVカラムモデル.項目型_文字列 <BR>
     * 　@日付フォーマット：　@null <BR>
     * <BR>
     * －　@index = 1 <BR>
     * 　@[CSVカラムモデル コンストラクタの引数] <BR>
     * 　@項目ラベル：　@出金申込問合せダウンロードCSV.顧客コードラベル  <BR>
     * 　@カラム番号： 1 <BR>
     * 　@項目型：　@CSVカラムモデル.項目型_文字列 <BR>
     * 　@日付フォーマット：　@null <BR>
     * <BR>
     * －　@index = 2<BR>
     * 　@[CSVカラムモデル コンストラクタの引数] <BR>
     * 　@項目ラベル：　@出金申込問合せダウンロードCSV.顧客名ラベル  <BR>
     * 　@カラム番号： 2<BR>
     * 　@項目型：　@CSVカラムモデル.項目型_文字列 <BR>
     * 　@日付フォーマット：　@null <BR>
     * <BR>
     * －　@index = 3<BR>
     * 　@[CSVカラムモデル コンストラクタの引数] <BR>
     * 　@項目ラベル：　@出金申込問合せダウンロードCSV.注文日時ラベル  <BR>
     * 　@カラム番号： 3<BR>
     * 　@項目型：　@CSVカラムモデル.項目型_日付時間  <BR>
     * 　@日付フォーマット：　@new SimpleDateFormat("yyyy/MM/dd HH:mm:ss")  <BR>
     * <BR>
     * －　@index = 4<BR>
     * 　@[CSVカラムモデル コンストラクタの引数] <BR>
     * 　@項目ラベル：　@出金申込問合せダウンロードCSV.受渡日ラベル <BR>
     * 　@カラム番号： 4<BR>
     * 　@項目型： CSVカラムモデル.項目型_日付 <BR>
     * 　@日付フォーマット：　@new SimpleDateFormat("yyyy/MM/dd") <BR>
     * <BR>
     * －　@index = 5<BR>
     * 　@[CSVカラムモデル コンストラクタの引数] <BR>
     * 　@項目ラベル：　@出金申込問合せダウンロードCSV.出金額ラベル <BR>
     * 　@カラム番号： 5 <BR>
     * 　@項目型：　@CSVカラムモデル.項目型_数値（double） <BR>
     * 　@日付フォーマット：　@null <BR>
     * <BR>
     * －　@index = 6 <BR>
     * 　@[CSVカラムモデル コンストラクタの引数] <BR>
     * 　@項目ラベル：　@出金申込問合せダウンロードCSV.注文状態ラベル  <BR>
     * 　@カラム番号： 6<BR>
     * 　@項目型：　@CSVカラムモデル.項目型_文字列 <BR>
     * 　@日付フォーマット：　@null <BR>
     * <BR>
     * －　@index = 7<BR>
     * 　@[CSVカラムモデル コンストラクタの引数] <BR>
     * 　@項目ラベル：　@出金申込問合せダウンロードCSV.取消区分ラベル <BR>
     * 　@カラム番号： 7<BR>
     * 　@項目型：　@CSVカラムモデル.項目型_文字列 <BR>
     * 　@日付フォーマット：　@null<BR>
     * <BR>
     * －　@index = 8<BR>
     * 　@[CSVカラムモデル コンストラクタの引数] <BR>
     * 　@項目ラベル：　@出金申込問合せダウンロードCSV.取消日時ラベル<BR>
     * 　@カラム番号： 8<BR>
     * 　@項目型：CSVカラムモデル.項目型_日付時間<BR>
     * 　@日付フォーマット：　@new SimpleDateFormat("yyyy/MM/dd HH:mm:ss")<BR>
     * <BR>
     * －　@index = 9<BR>
     * 　@[CSVカラムモデル コンストラクタの引数] <BR>
     * 　@項目ラベル：　@出金申込問合せダウンロードCSV.振込先銀行コードラベル  <BR>
     * 　@カラム番号： 9<BR>
     * 　@項目型：　@CSVカラムモデル.項目型_文字列<BR>
     * 　@日付フォーマット：　@null <BR>
     * <BR>
     * －　@index = 10<BR>
     * 　@[CSVカラムモデル コンストラクタの引数] <BR>
     * 　@項目ラベル：　@出金申込問合せダウンロードCSV.振込先支店コードラベル <BR>
     * 　@カラム番号： 10<BR>
     * 　@項目型：　@CSVカラムモデル.項目型_文字列<BR>
     * 　@日付フォーマット：　@null  <BR>
     * <BR>
     * －　@index = 11<BR>
     * 　@[CSVカラムモデル コンストラクタの引数] <BR>
     * 　@項目ラベル：　@出金申込問合せダウンロードCSV.預金区分ラベル  <BR>
     * 　@カラム番号： 11<BR>
     * 　@項目型：　@CSVカラムモデル.項目型_文字列  <BR>
     * 　@日付フォーマット：　@null<BR>
     * <BR>
     * －　@index = 12<BR>
     * 　@[CSVカラムモデル コンストラクタの引数] <BR>
     * 　@項目ラベル：　@出金申込問合せダウンロードCSV.振込先口座番号ラベル <BR>
     * 　@カラム番号： 12<BR>
     * 　@項目型：　@CSVカラムモデル.項目型_文字列<BR>
     * 　@日付フォーマット：　@null<BR>
     * <BR>
     * －　@index = 13<BR>
     * 　@[CSVカラムモデル コンストラクタの引数] <BR>
     * 　@項目ラベル：　@出金申込問合せダウンロードCSV.振込先口座名義人ラベル  <BR>
     * 　@カラム番号： 13<BR>
     * 　@項目型：　@CSVカラムモデル.項目型_文字列<BR>
     * 　@日付フォーマット：　@null  <BR>
     * <BR>
     * －　@index = 14<BR>
     * 　@[CSVカラムモデル コンストラクタの引数] <BR>
     * 　@項目ラベル：　@出金申込問合せダウンロードCSV.出金可能額（当日）ラベル  <BR>
     * 　@カラム番号： 14<BR>
     * 　@項目型：　@CSVカラムモデル.項目型_数値（double）<BR>
     * 　@日付フォーマット：　@null  <BR>
     * <BR>
     * －　@index = 15<BR>
     * 　@[CSVカラムモデル コンストラクタの引数] <BR>
     * 　@項目ラベル：　@出金申込問合せダウンロードCSV.出金可能額（１営業日後）ラベル   <BR>
     * 　@カラム番号： 15<BR>
     * 　@項目型：　@CSVカラムモデル.項目型_数値（double）<BR>
     * 　@日付フォーマット：　@null  <BR>
     * <BR>
     * －　@index = 16<BR>
     * 　@[CSVカラムモデル コンストラクタの引数] <BR>
     * 　@項目ラベル：　@出金申込問合せダウンロードCSV.出金可能額（2営業日後）ラベル   <BR>
     * 　@カラム番号： 16<BR>
     * 　@項目型：　@CSVカラムモデル.項目型_数値（double）<BR>
     * 　@日付フォーマット：　@null  <BR>
     * <BR>
     * －　@index = 16<BR>
     * 　@[CSVカラムモデル コンストラクタの引数] <BR>
     * 　@項目ラベル：　@出金申込問合せダウンロードCSV.出金可能額（2営業日後）ラベル   <BR>
     * 　@カラム番号： 16<BR>
     * 　@項目型：　@CSVカラムモデル.項目型_数値（double）<BR>
     * 　@日付フォーマット：　@null  <BR>
     * <BR>
     * －　@index = 17<BR>
     * 　@[CSVカラムモデル コンストラクタの引数] <BR>
     * 　@項目ラベル：　@出金申込問合せダウンロードCSV.出金可能額（3営業日後）ラベル   <BR>
     * 　@カラム番号： 17<BR>
     * 　@項目型：　@CSVカラムモデル.項目型_数値（double）<BR>
     * 　@日付フォーマット：　@null  <BR>
     * <BR>
     * －　@index = 18<BR>
     * 　@[CSVカラムモデル コンストラクタの引数] <BR>
     * 　@項目ラベル：　@出金申込問合せダウンロードCSV.出金可能額（4営業日後）ラベル   <BR>
     * 　@カラム番号： 18<BR>
     * 　@項目型：　@CSVカラムモデル.項目型_数値（double）<BR>
     * 　@日付フォーマット：　@null  <BR>
     * <BR>
     * －　@index = 19<BR>
     * 　@[CSVカラムモデル コンストラクタの引数] <BR>
     * 　@項目ラベル：　@出金申込問合せダウンロードCSV.出金可能額（5営業日後）ラベル   <BR>
     * 　@カラム番号： 19<BR>
     * 　@項目型：　@CSVカラムモデル.項目型_数値（double）<BR>
     * 　@日付フォーマット：　@null  <BR>
     * @@roseuid 4110C7E902F5
     */
    private void createColumnHeader()
    {

        final String STR_METHOD_NAME = " createColumnHeader()";
        log.entering(STR_METHOD_NAME );

        final int COLUMN_MAX = 20;

        WEB3GentradeCsvColumnModel[] l_models = new WEB3GentradeCsvColumnModel[COLUMN_MAX];

        //index 0部店ラベル 
        l_models[0] = new WEB3GentradeCsvColumnModel(
            WEB3AdminAioCashoutInqDownloadCsv.BRANCH_LABEL,
            0,
            WEB3GentradeCsvColumnModel.STRINGTYPE,
            null);

        //index 1顧客コードラベル 
        l_models[1] = new WEB3GentradeCsvColumnModel(
            WEB3AdminAioCashoutInqDownloadCsv.ACCOUNT_CODE_LABEL,
            1,
            WEB3GentradeCsvColumnModel.STRINGTYPE,
            null);

        //index 2顧客名ラベル 
        l_models[2] = new WEB3GentradeCsvColumnModel(
            WEB3AdminAioCashoutInqDownloadCsv.ACCOUNT_NAME_LABEL,
            2,
            WEB3GentradeCsvColumnModel.STRINGTYPE,
            null);

        //index 3注文日時ラベル	
        l_models[3] = new WEB3GentradeCsvColumnModel(
            WEB3AdminAioCashoutInqDownloadCsv.ORDER_DATE_LABEL	,
            3,
            WEB3GentradeCsvColumnModel.TIMESTAMPTYPE,
            new SimpleDateFormat("yyyy/MM/dd HH:mm:ss"));

        //index 4受渡日ラベル 
        l_models[4] = new WEB3GentradeCsvColumnModel(
            WEB3AdminAioCashoutInqDownloadCsv.DELIVERY_DATE_LABEL,
            4,
            WEB3GentradeCsvColumnModel.DATETYPE,
            new SimpleDateFormat("yyyy/MM/dd"));

        //index 5出金額ラベル
        l_models[5] = new WEB3GentradeCsvColumnModel(
            WEB3AdminAioCashoutInqDownloadCsv.CASHINGOUT_AMOUNT_LABEL,
            5,
            WEB3GentradeCsvColumnModel.DOUBLETYPE,
            null);

        //index 6注文状態ラベル 
        l_models[6] = new WEB3GentradeCsvColumnModel(
            WEB3AdminAioCashoutInqDownloadCsv.ORDER_STATUS_LABEL,
            6,
            WEB3GentradeCsvColumnModel.STRINGTYPE,
            null);

        //index 7取消区分ラベル 
        l_models[7] = new WEB3GentradeCsvColumnModel(
            WEB3AdminAioCashoutInqDownloadCsv.CANCEL_DIV_LABEL,
            7,
            WEB3GentradeCsvColumnModel.STRINGTYPE,
            null);

        //index 8取消日時ラベル      
        l_models[8] = new WEB3GentradeCsvColumnModel(
            WEB3AdminAioCashoutInqDownloadCsv.CANCEL_DATE_LABEL,
            8,
            WEB3GentradeCsvColumnModel.TIMESTAMPTYPE,
            new SimpleDateFormat("yyyy/MM/dd HH:mm:ss"));

        //index 9振込先銀行コードラベル
        l_models[9] = new WEB3GentradeCsvColumnModel(
            WEB3AdminAioCashoutInqDownloadCsv.TRANSFER_BANK_CODE_LABEL,
            9,
            WEB3GentradeCsvColumnModel.STRINGTYPE,
            null);

        //index 10 振込先支店コードラベル 
        l_models[10] = new WEB3GentradeCsvColumnModel(
            WEB3AdminAioCashoutInqDownloadCsv.TRANSFER_BRANCH_CODE_LABEL,
            10,
            WEB3GentradeCsvColumnModel.STRINGTYPE,
            null);

        //index 11預金区分ラベル 
        l_models[11] = new WEB3GentradeCsvColumnModel(
            WEB3AdminAioCashoutInqDownloadCsv.DEPOSIT_DIV_LABEL,
            11,
            WEB3GentradeCsvColumnModel.STRINGTYPE,
            null);

        //index 12振込先口座番号ラベル
        l_models[12] = new WEB3GentradeCsvColumnModel(
            WEB3AdminAioCashoutInqDownloadCsv.TRANSFER_ACCOUNT_NUMBER_LABEL,
            12,
            WEB3GentradeCsvColumnModel.STRINGTYPE,
            null);

        //index 13 振込先口座名義人ラベル 
        l_models[13] = new WEB3GentradeCsvColumnModel(
            WEB3AdminAioCashoutInqDownloadCsv.TRANSFER_ACCOUNT_NAME_LABEL,
            13,
            WEB3GentradeCsvColumnModel.STRINGTYPE,
            null);

        //index 14 出金可能額（当日）ラベル 
        l_models[14] = new WEB3GentradeCsvColumnModel(
            WEB3AdminAioCashoutInqDownloadCsv.CASHOUT_POSSIBLE_PRICE_TODAY_LABEL,
            14,
            WEB3GentradeCsvColumnModel.DOUBLETYPE,
            null);
        
        //index 15 出金可能額（１営業日後）ラベル
        l_models[15] = new WEB3GentradeCsvColumnModel(
            WEB3AdminAioCashoutInqDownloadCsv.CASHOUT_POSSIBLE_PRICE_1DAYAFTER_LABEL,
            15,
            WEB3GentradeCsvColumnModel.DOUBLETYPE,
            null);
        
        //index 16 出金可能額（2営業日後）ラベル
        l_models[16] = new WEB3GentradeCsvColumnModel(
            WEB3AdminAioCashoutInqDownloadCsv.CASHOUT_POSSIBLE_PRICE_2DAYSAFTER_LABEL,
            16,
            WEB3GentradeCsvColumnModel.DOUBLETYPE,
            null);
        
        //index 17 出金可能額（3営業日後）ラベル
        l_models[17] = new WEB3GentradeCsvColumnModel(
            WEB3AdminAioCashoutInqDownloadCsv.CASHOUT_POSSIBLE_PRICE_3DAYSAFTER_LABEL,
            17,
            WEB3GentradeCsvColumnModel.DOUBLETYPE,
            null);
        
        //index 18 出金可能額（4営業日後）ラベル
        l_models[18] = new WEB3GentradeCsvColumnModel(
            WEB3AdminAioCashoutInqDownloadCsv.CASHOUT_POSSIBLE_PRICE_4DAYSAFTER_LABEL,
            18,
            WEB3GentradeCsvColumnModel.DOUBLETYPE,
            null);
        
        //index 19 出金可能額（5営業日後）ラベル
        l_models[19] = new WEB3GentradeCsvColumnModel(
            WEB3AdminAioCashoutInqDownloadCsv.CASHOUT_POSSIBLE_PRICE_5DAYSAFTER_LABEL,
            19,
            WEB3GentradeCsvColumnModel.DOUBLETYPE,
            null);

        this.setColumnHeader(l_models);

        log.exiting(STR_METHOD_NAME);

    }
    
    /**
     * (createキーヘッダ)<BR>
     * キーヘッダをセットする。<BR>
     * 　@以下の通り文字列の配列を生成し、<BR>
     * 　@setキーヘッダ()にてインスタンスにセットする。<BR>
     * <BR>
     * [キーヘッダ配列]    <BR>
     * <BR>
     * －　@index = 0   <BR>
     * 　@現在日時を"yyyy/MM/dd HH:mm:ss"の形式にformatした文字列。<BR>
     * <BR>
     * (*1) 現在日時 <BR>
     * TradingSystem.getSystemTimestamp()   <BR>
     * @@roseuid 43D5DF890264
     */
    private void createKeyHeader() 
    {
        final String STR_METHOD_NAME = "createKeyHeader()";
        log.entering(STR_METHOD_NAME);
        
        String[] l_strKeyHeaders = new String[1];
        
        //現在日時を"yyyy/MM/dd HH:mm:ss"の形式にformatした文字列。   
        l_strKeyHeaders[0] = WEB3DateUtility.formatDate(
            GtlUtils.getSystemTimestamp(), "yyyy/MM/dd HH:mm:ss");
        
        //setキーヘッダ
        this.setKeyHeader(l_strKeyHeaders);
        
        log.exiting(STR_METHOD_NAME);

    }
    
    /**
     * (set部店)<BR>
     * 部店コードをセットする。<BR>
     * <BR>
     * １）カラムモデル取得<BR>
     * 　@this.getカラムモデル()にてCSVカラムモデルを取得する。<BR>
     * <BR>
     * 　@[getカラムモデル()に指定する引数]  <BR>
     * 　@項目ラベル：　@出金申込問合せダウンロードCSV.部店コードラベル  <BR>
     * <BR>
     * ２）値セット<BR>
     * 　@this.set項目値()にて項目値をセットする。<BR>
     * <BR>
     * 　@[set項目値()に指定する引数]  <BR>
     * 　@行番号：　@引数の行番号 <BR>
     * 　@カラム： １）で取得したカラムモデル<BR>
     * 　@値：　@部店ＩＤに該当する部店オブジェクト.getBranchCode() <BR>
     * @@param l_intLineNumber - (行番号)<BR>
     *              行番号<BR>
     * @@param l_strBranchId - (部店ID)<BR>
     *              部店ID
     * @@roseuid 43D5DF890264
     */
    public void setBranch(int l_intLineNumber, String l_strBranchId) 
    {
        final String STR_METHOD_NAME = "setBranch(int,string)";
        log.entering(STR_METHOD_NAME);
        Long l_lngBranchId = new Long(l_strBranchId);
        AccountManager l_accountManager = GtlUtils.getAccountManager();   
        try
		{
	        Branch l_branch = l_accountManager.getBranch(l_lngBranchId.longValue());
		    String l_strBranchCode = l_branch.getBranchCode();
		    //１）カラムモデル取得
		    //２）値セット
		    this.setValue(l_intLineNumber, this.getColumnModel(
		        WEB3AdminAioCashoutInqDownloadCsv.BRANCH_LABEL), l_strBranchCode);
		}
        catch (NotFoundException l_ex)
        {
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BaseRuntimeException(
	            WEB3ErrorCatalog.SYSTEM_ERROR_80005,
	            this.getClass().getName() + "." + STR_METHOD_NAME,
	            l_ex.getMessage(),
	            l_ex);
        }
       log.exiting(STR_METHOD_NAME);
    }
    
    /**
     * (set顧客)<BR>
     *<BR>
     *１）顧客オブジェクト取得 <BR>
     * 　@口座ＩＤに該当する顧客オブジェクトを取得する。<BR>
     *<BR>
     *２）（顧客コード）カラムモデル取得  <BR>
     * 　@this.getカラムモデル()にてCSVカラムモデルを取得する。<BR>
     *<BR>
     * 　@[getカラムモデル()に指定する引数]   <BR>
     * 　@項目ラベル：　@出金申込問合せダウンロードCSV.顧客コードラベル <BR>
     * ３）（顧客コード）値セット  <BR>
     * 　@this.set項目値()にて項目値をセットする。<BR>
     *<BR>   
     * 　@[set項目値()に指定する引数] <BR>
     * 　@行番号：　@引数の行番号  <BR>
     * 　@カラム：　@２）で取得したカラムモデル<BR>  
     * 　@値：　@顧客.getAccountCode()の左6byte<BR>
     *<BR>
     * ４）（顧客名）カラムモデル取得 <BR>
     * 　@this.getカラムモデル()にてCSVカラムモデルを取得する。<BR>
     *<BR>
     * 　@[getカラムモデル()に指定する引数]  <BR>
     * 　@項目ラベル：　@出金申込問合せダウンロードCSV.顧客名ラベル   <BR>
     *<BR>
     * ５）（顧客名）値セット  <BR>
     * 　@this.set項目値()にて項目値をセットする。<BR>
     *<BR>
     * 　@[set項目値()に指定する引数] <BR>
     * 　@行番号：　@引数の行番号 <BR>
     * 　@カラム：　@４）で取得したカラムモデル  <BR>
     * 　@値：　@顧客.get顧客表示名()  <BR>
     * @@param l_intLineNumber - (行番号)<BR>
     *              行番号<BR>
     * @@param l_lngAccountId - (口座ID)<BR>
     *              口座ID<BR>
     * @@roseuid 43D5DF890264
     */
    public void setAccount(int l_intLineNumber, long l_lngAccountId) 
    {
        final String STR_METHOD_NAME = "setAccount(int,long)";
        log.entering(STR_METHOD_NAME);

        // １）　@顧客オブジェクト取得  
        // 口座ＩＤに該当する顧客オブジェクトを取得する。  
        FinApp l_finApp = (FinApp)Services.getService(FinApp.class);
        AccountManager l_accountMgr = l_finApp.getAccountManager();      
        WEB3GentradeMainAccount l_mainAccount = null;
        
        try
        {
            l_mainAccount = (WEB3GentradeMainAccount)l_accountMgr.getMainAccount(l_lngAccountId);
        }
        catch (NotFoundException l_ex)
        {
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BaseRuntimeException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80005,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                l_ex.getMessage(),
                l_ex);
        }
        
        // [set項目値()に指定する引数]  
        // 行番号：　@引数の行番号  
        // カラム：　@２）で取得したカラムモデル  
        // 値：　@顧客.getAccountCode()の左6byte 
        this.setValue(l_intLineNumber, this.getColumnModel(
            WEB3AdminAioCashoutInqDownloadCsv.ACCOUNT_CODE_LABEL), 
            l_mainAccount.getAccountCode().substring(0, 6));
        
        //　@[set項目値()に指定する引数]  
        //　@行番号：　@引数の行番号  
        //　@カラム：　@４）で取得したカラムモデル  
        //　@値：　@顧客.get顧客表示名()  
        this.setValue(l_intLineNumber, this.getColumnModel(
            WEB3AdminAioCashoutInqDownloadCsv.ACCOUNT_NAME_LABEL),
            l_mainAccount.getDisplayAccountName());
        log.exiting(STR_METHOD_NAME);

    }
    
    /**
     * (set注文日時)<BR>
     * 注文日時をセットする。<BR>
     * <BR>
     * １）カラムモデル取得<BR>
     * 　@this.getカラムモデル()にてCSVカラムモデルを取得する。<BR>
     * <BR>
     * 　@[getカラムモデル()に指定する引数]  <BR>
     * 　@項目ラベル：　@出金申込問合せダウンロードCSV.注文日時ラベル  <BR>
     * <BR>
     * ２）値セット<BR>
     * 　@this.set項目値()にて項目値をセットする。<BR>
     * <BR>
     *   [引数] <BR>
     *   行番号： （引数）行番号  <BR>
     *   カラム： １）で取得したカラムモデル<BR>
     *   値： （引数）受注日時 <BR>
     * @@param l_intLineNumber - (行番号)<BR>
     *              行番号<BR>
     * @@param l_datReceivedDate - (受注日時)<BR>
     *              受注日時
     * @@roseuid 43D5DF890264
     */
    public void setOrderDate(int l_intLineNumber, Date l_datReceivedDate) 
    {
        final String STR_METHOD_NAME = "setOrderDate(int,date)";
        log.entering(STR_METHOD_NAME);

        //１）カラムモデル取得
        //２）値セット
        this.setValue(l_intLineNumber, this.getColumnModel(
            WEB3AdminAioCashoutInqDownloadCsv.ORDER_DATE_LABEL), l_datReceivedDate);
        
        log.exiting(STR_METHOD_NAME);

    }
    
    /**
     * (set受渡日)<BR>
     * 受渡日をセットする。<BR>
     * <BR>
     * １）カラムモデル取得<BR>
     * 　@this.getカラムモデル()にてCSVカラムモデルを取得する。<BR>
     * <BR>
     * 　@[getカラムモデル()に指定する引数]  <BR>
     * 　@項目ラベル：　@出金申込問合せダウンロードCSV.受渡日ラベル  <BR>
     * <BR>
     * ２）値セット<BR>
     * 　@this.set項目値()にて、値をセットする。<BR>
     * <BR>
     *   [引数] <BR>
     *   行番号： （引数）行番号  <BR>
     *   カラム： １）で取得したカラムモデル<BR>
     *   値： （引数）受渡日 <BR>
     * @@param l_intLineNumber - (行番号)<BR>
     *              行番号<BR>
     * @@param l_datDeliveryDate - (受渡日)<BR>
     *            　@受渡日
     * @@roseuid 43D5DF890264
     */
    public void setDeliveryDate(int l_intLineNumber, Date l_datDeliveryDate) 
    {
        final String STR_METHOD_NAME = "setDeliveryDate(int,date)";
        log.entering(STR_METHOD_NAME);

        //１）カラムモデル取得
        //２）値セット
        this.setValue(l_intLineNumber, this.getColumnModel(
            WEB3AdminAioCashoutInqDownloadCsv.DELIVERY_DATE_LABEL), l_datDeliveryDate);
        
        log.exiting(STR_METHOD_NAME);

    }
    
    /**
     * (set出金額)<BR>
     * 出金額をセットする。<BR>
     * <BR>
     * 　@１）カラムモデル取得<BR>
     * 　@this.getカラムモデル()にてCSVカラムモデルを取得する。<BR>
     * <BR>
     * 　@[getカラムモデル()に指定する引数]  <BR>
     * 　@項目ラベル：　@出金申込問合せダウンロードCSV.出金額ラベル  <BR>
     * <BR>
     * ２）値セット<BR>
     * 　@this.set項目値()にて、値をセットする。<BR>
     * <BR>
     * 　@[引数] <BR>
     * 　@行番号： （引数）行番号  <BR>
     * 　@カラム： １）で取得したカラムモデル<BR>
     * 　@値： （引数）注文数量の小数点以下を切り捨てた値 <BR>
     * @@param l_intLineNumber - (行番号)<BR>
     *              行番号<BR>
     * @@param l_dblOrderQuantity - (注文数量)<BR>
     *              注文数量
     * @@roseuid 43D5DF890264
     */
    public void setCashoutAmount(int l_intLineNumber, double l_dblOrderQuantity) 
    {
        final String STR_METHOD_NAME = "setCashoutAmount(int,double)";
        log.entering(STR_METHOD_NAME);
        long l_lngTemp =  (long) Math.floor(l_dblOrderQuantity);

        //１）カラムモデル取得
        //２）値セット
        this.setValue(l_intLineNumber, this.getColumnModel(
            WEB3AdminAioCashoutInqDownloadCsv.CASHINGOUT_AMOUNT_LABEL), new Long(l_lngTemp));
        
        log.exiting(STR_METHOD_NAME);

    }
    
    /**
     * (set注文状態)<BR>
     * 注文状態をセットする。<BR>
     * <BR>
     * １）カラムモデル取得<BR>
     * 　@this.getカラムモデル()にてCSVカラムモデルを取得する。<BR>
     * <BR>
     *   [引数]   <BR>
     * 　@項目ラベル： 出金申込問合せダウンロードCSV.注文状態ラベル   <BR>
     * <BR>
     * ２）値セット<BR>
     * 　@this.set項目値()にて、値をセットする。<BR>
     * <BR>
     * 　@[引数] <BR>
     *   行番号： （引数）行番号  <BR>
     *   カラム： １）で取得したカラムモデル<BR>
     *   値：  <BR>
     *   （引数:注文受付状態 == "受付未済"）の場合  <BR>
     *   －出金申込問合せダウンロードCSV.注文状態_受付未済表示。<BR>
     *<BR>
     *   （引数:注文受付状態 == "受付済"）の場合  <BR>
     *   －出金申込問合せダウンロードCSV.注文状態_受付済表示。<BR>
     *<BR>
     *   （引数:注文受付状態 == "受付エラー"）の場合  <BR>
     *   －出金申込問合せダウンロードCSV.注文状態_受付エラー表示。<BR>
     *<BR>
     *   （引数:注文受付状態 == "受付中"）の場合  <BR>
     *   －出金申込問合せダウンロードCSV.注文状態_受付中表示。<BR>
     * @@param l_intLineNumber - (行番号)<BR>
     *              行番号<BR>
     * @@param l_strOrderStatus - (注文受付状態 )<BR>
     *              注文受付状態<BR>
     *              0 ： 受付未済 <BR>
     *              1 ： 受付済 <BR>
     *              2 ： 受付エラー <BR>
     *              4 ： 受付中 <BR>
     * @@roseuid 43D5DF890264
     */
    public void setOrderStatus(int l_intLineNumber, String l_strOrderStatus) 
    {
        final String STR_METHOD_NAME = "setOrderStatus(int,String)";
        log.entering(STR_METHOD_NAME);

        //１）カラムモデル取得
        //２）値セット
        // 0 ： 受付未済
        if(WEB3AioOrderAcceptedDivDef.NOT_ACCEPTED.equals(l_strOrderStatus))
        {
            this.setValue(l_intLineNumber, 
                this.getColumnModel(WEB3AdminAioCashoutInqDownloadCsv.ORDER_STATUS_LABEL), 
                WEB3AdminAioCashoutInqDownloadCsv.ORDER_STATUS_NOT_ACCEPTED);
        }// 1 ： 受付済
        else if(WEB3AioOrderAcceptedDivDef.ACCEPTED.equals(l_strOrderStatus))
        {
            this.setValue(l_intLineNumber, 
                this.getColumnModel(WEB3AdminAioCashoutInqDownloadCsv.ORDER_STATUS_LABEL), 
                WEB3AdminAioCashoutInqDownloadCsv.ORDER_STATUS_ACCEPTED);
        }// 2 ： 受付エラー
        else if(WEB3AioOrderAcceptedDivDef.ACCEPT_ERROR.equals(l_strOrderStatus))
        {
            this.setValue(l_intLineNumber, 
                this.getColumnModel(WEB3AdminAioCashoutInqDownloadCsv.ORDER_STATUS_LABEL), 
                WEB3AdminAioCashoutInqDownloadCsv.ORDER_STATUS_ACCEPTED_ERROR);
        }// 4 ： 受付中
        else if(WEB3AioOrderAcceptedDivDef.ACCEPTING.equals(l_strOrderStatus))
        {
            this.setValue(l_intLineNumber, 
                this.getColumnModel(WEB3AdminAioCashoutInqDownloadCsv.ORDER_STATUS_LABEL), 
                WEB3AdminAioCashoutInqDownloadCsv.ORDER_STATUS_ACCEPTING);
        }
        log.exiting(STR_METHOD_NAME);
    }
    
    /**
     * (set取消区分)<BR>
     * 取消区分をセットする。<BR>
     * <BR>
     * １）カラムモデル取得<BR>
     * 　@this.getカラムモデル()にてCSVカラムモデルを取得する。<BR>
     * <BR>
     * 　@[引数]   <BR>
     * 　@項目ラベル： 出金申込問合せダウンロードCSV.取消区分ラベル	   <BR>
     * <BR>
     * ２）値セット<BR>
     * 　@this.set項目値()にて、値をセットする。<BR>
     * <BR>
     * 　@[引数] <BR>
     * 　@行番号： （引数）行番号  <BR>
     * 　@カラム： １）で取得したカラムモデル<BR>
     * 　@値：  <BR>
     * 　@（引数:取消区分 == "取消済"）の場合  <BR>
     * 　@－出金申込問合せダウンロードCSV.取消区分_取消済表示。  <BR>
     *<BR>
     * 　@（引数:取消区分 == "初期値"）の場合   <BR>
     * 　@－null<BR>
     * @@param l_intLineNumber - (行番号)<BR>
     *              行番号<BR>
     * @@param l_strCancelDiv - (取消区分)<BR>
     *              取消区分 <BR>
     *              2：取消済  <BR>
     *              0：初期値 <BR>
     * @@roseuid 43D5DF890264
     */
    public void setCancelDiv(int l_intLineNumber, String l_strCancelDiv) 
    {
        final String STR_METHOD_NAME = "setCancelDiv(int,String)";
        log.entering(STR_METHOD_NAME);

        //１）カラムモデル取得
        //２）値セット
        //2：取消済 
        if(WEB3AioCancelDivDef.CANCElED.equals(l_strCancelDiv))
        {
            this.setValue(l_intLineNumber, 
                this.getColumnModel(WEB3AdminAioCashoutInqDownloadCsv.CANCEL_DIV_LABEL), 
                WEB3AdminAioCashoutInqDownloadCsv.CANCEL_DIV_CANCELED);
        }//0：初期値
        else if(WEB3AioCancelDivDef.INITIAL_VALUE.equals(l_strCancelDiv))
        {
            this.setValue(l_intLineNumber, 
                this.getColumnModel(WEB3AdminAioCashoutInqDownloadCsv.CANCEL_DIV_LABEL), 
                null);
        }

        log.exiting(STR_METHOD_NAME);

    }
    
    /**
     * (set取消日時)<BR>
     * 取消日時をセットする。<BR>
     * <BR>
     * １）カラムモデル取得<BR>
     * 　@this.getカラムモデル()にてCSVカラムモデルを取得する。<BR>
     * <BR>
     *   [引数]   <BR>
     *   項目ラベル：　@出金申込問合せダウンロードCSV.取消日時ラベル   <BR>
     * <BR>
     * ２）値セット<BR>
     *   this.set項目値()にて、値をセットする。<BR>
     * <BR>
     *   [引数] <BR>
     *   行番号： （引数）行番号  <BR>
     *   カラム： １）で取得したカラムモデル<BR>
     *   値： （引数）取消日時 <BR>
     * @@param l_intLineNumber - (行番号)<BR>
     *              行番号<BR>
     * @@param l_datCancelDate - (取消日時)<BR>
     *              取消日時<BR>
     * @@roseuid 43D5DF890264
     */
    public void setCancelDate(int l_intLineNumber, Date l_datCancelDate) 
    {
        final String STR_METHOD_NAME = "setCancelDate(int,Date)";
        log.entering(STR_METHOD_NAME);

        //１）カラムモデル取得
        //２）値セット
        this.setValue(l_intLineNumber, this.getColumnModel(
            WEB3AdminAioCashoutInqDownloadCsv.CANCEL_DATE_LABEL),l_datCancelDate);
        
        log.exiting(STR_METHOD_NAME);

    }
    
    /**
     * (set振込先銀行コード )<BR>
     * 振込先銀行コードをセットする。 <BR>
     * <BR>
     * １）カラムモデル取得<BR>
     * 　@this.getカラムモデル()にてCSVカラムモデルを取得する。<BR>
     * <BR>
     * 　@[引数]   <BR>
     * 　@項目ラベル：　@出金申込問合せダウンロードCSV.振込先銀行コードラベル    <BR>
     * <BR>
     * ２）値セット<BR>
     * 　@this.set項目値()にて、値をセットする。<BR>
     * <BR>
     * 　@[引数] <BR>
     * 　@行番号： （引数）行番号  <BR>
     * 　@カラム： １）で取得したカラムモデル<BR>
     * 　@値： （引数）振込先銀行コード <BR>
     * @@param l_intLineNumber - (行番号)<BR>
     *              行番号<BR>
     * @@param l_strTransferBankCode - (振込先銀行コード)<BR>
     *              振込先銀行コード<BR>
     * @@roseuid 43D5DF890264
     */
    public void setTransferBankCode(int l_intLineNumber, String l_strTransferBankCode) 
    {
        final String STR_METHOD_NAME = "setCansetTransferBankCodecelDate(int,String)";
        log.entering(STR_METHOD_NAME);

        //１）カラムモデル取得
        //２）値セット
        this.setValue(l_intLineNumber, this.getColumnModel(
            WEB3AdminAioCashoutInqDownloadCsv.TRANSFER_BANK_CODE_LABEL),
            l_strTransferBankCode);
        
        log.exiting(STR_METHOD_NAME);

    }
    
    /**
     * (set振込先支店コード )<BR>
     * 振込先支店コードをセットする。 <BR>
     * <BR>
     * １）カラムモデル取得<BR>
     * 　@this.getカラムモデル()にてCSVカラムモデルを取得する。<BR>
     * <BR>
     *   [引数]   <BR>
     *   項目ラベル：　@出金申込問合せダウンロードCSV.振込先支店コードラベル    <BR>
     * <BR>
     * ２）値セット<BR>
     * 　@this.set項目値()にて、値をセットする。<BR>
     * <BR>
     *   [引数] <BR>
     *   行番号： （引数）行番号  <BR>
     *   カラム： １）で取得したカラムモデル<BR>
     *   値： （引数）支店コード <BR>
     * @@param l_intLineNumber - (行番号)<BR>
     *              行番号<BR>
     * @@param l_strTransferBranchCode - (支店コード)<BR>
     *              支店コード
     * @@roseuid 43D5DF890264
     */
    public void setTransferBranchCode(int l_intLineNumber, String l_strTransferBranchCode) 
    {
        final String STR_METHOD_NAME = "setTransferBranchCode(int,String)";
        log.entering(STR_METHOD_NAME);

        //１）カラムモデル取得
        //２）値セット
        this.setValue(l_intLineNumber, this.getColumnModel(
            WEB3AdminAioCashoutInqDownloadCsv.TRANSFER_BRANCH_CODE_LABEL),
            l_strTransferBranchCode);
        
        log.exiting(STR_METHOD_NAME);

    }
    
    /**
     * (set預金区分)<BR>
     * 注文状態をセットする。<BR>
     * <BR>
     * １）カラムモデル取得<BR>
     * 　@this.getカラムモデル()にてCSVカラムモデルを取得する。<BR>
     * <BR>
     *   [引数]   <BR>
     *   項目ラベル： 出金申込問合せダウンロードCSV.預金区分ラベル   <BR>
     * <BR>
     * ２）値セット<BR>
     * 　@this.set項目値()にて、値をセットする。<BR>
     * <BR>
     * 　@[引数] <BR>
     * 　@行番号： （引数）行番号  <BR>
     * 　@カラム： １）で取得したカラムモデル<BR>
     * 　@値：  <BR>
     * 　@（引数:預金区分 == "普通預金"）の場合  <BR>
     * 　@－出金申込問合せダウンロードCSV.預金区分_普通預金表示。 <BR>
     *<BR>
     * 　@（引数:預金区分 == "当座預金"）の場合  <BR>
     * 　@－出金申込問合せダウンロードCSV.預金区分_当座預金表示。<BR>
     *<BR>
     * 　@（引数:預金区分 == "その他"）の場合   <BR>
     * 　@－出金申込問合せダウンロードCSV.預金区分_その他表示。 <BR>
     *<BR>
     * 　@（引数:預金区分 == "貯蓄預金"）の場合   <BR>
     * 　@－出金申込問合せダウンロードCSV.預金区分_貯蓄預金表示。 <BR>
     * @@param l_intLineNumber - (行番号)<BR>
     *              行番号<BR>
     * @@param l_strDepositDiv - (預金区分)<BR>
     *              預金区分<BR>
     *              1： 普通預金  <BR>
     *              2： 当座預金 <BR>
     *              3： その他 <BR>
     *              4： 貯蓄預金 <BR>
     * @@roseuid 43D5DF890264
     */
    public void setDepositDiv(int l_intLineNumber, String l_strDepositDiv) 
    {
        final String STR_METHOD_NAME = "setDepositDiv(int,String)";
        log.entering(STR_METHOD_NAME);

        //１）カラムモデル取得
        //２）値セット
        // 1： 普通預金 
        if(WEB3FinSaveDivDef.GENERAL_FIN_SAVE.equals(l_strDepositDiv))
        {
            this.setValue(l_intLineNumber, 
                this.getColumnModel(WEB3AdminAioCashoutInqDownloadCsv.DEPOSIT_DIV_LABEL), 
                WEB3AdminAioCashoutInqDownloadCsv.DEPOSIT_DIV_GENERAL);
        }//  2： 当座預金 
        else if(WEB3FinSaveDivDef.ACCOUNT_FIN_SAVE.equals(l_strDepositDiv))
        {
            this.setValue(l_intLineNumber, 
                this.getColumnModel(WEB3AdminAioCashoutInqDownloadCsv.DEPOSIT_DIV_LABEL), 
                WEB3AdminAioCashoutInqDownloadCsv.DEPOSIT_DIV_ACCOUNT);
        }// 3： その他
        else if(WEB3FinSaveDivDef.OTHER.equals(l_strDepositDiv))
        {
            this.setValue(l_intLineNumber, 
                this.getColumnModel(WEB3AdminAioCashoutInqDownloadCsv.DEPOSIT_DIV_LABEL), 
                WEB3AdminAioCashoutInqDownloadCsv.DEPOSIT_DIV_OTHER);
        }//  4： 貯蓄預金 
        else if(WEB3FinSaveDivDef.SAVING_FIN_SAVE.equals(l_strDepositDiv))
        {
            this.setValue(l_intLineNumber, 
                this.getColumnModel(WEB3AdminAioCashoutInqDownloadCsv.DEPOSIT_DIV_LABEL), 
                WEB3AdminAioCashoutInqDownloadCsv.DEPOSIT_DIV_SAVING);
        }
        log.exiting(STR_METHOD_NAME);
    }
    
    /**
     * (set振込先口座番号 )<BR>
     * 振込先口座番号をセットする。 <BR>
     * <BR>
     * 　@１）カラムモデル取得<BR>
     * 　@this.getカラムモデル()にてCSVカラムモデルを取得する。<BR>
     * <BR>
     *   [引数]   <BR>
     *   項目ラベル：　@出金申込問合せダウンロードCSV.振込先口座番号ラベル   <BR>
     * <BR>
     * 　@２）値セット<BR>
     *   this.set項目値()にて、値をセットする。<BR>
     * <BR>
     *   [引数] <BR>
     *   行番号： （引数）行番号  <BR>
     *   カラム： １）で取得したカラムモデル<BR>
     *   値： （引数）口座番号 <BR>
     * @@param l_intLineNumber - (行番号)<BR>
     *              行番号<BR>
     * @@param l_strAccountNumber - (口座番号)<BR>
     *              口座番号
     * @@roseuid 43D5DF890264
     */
    public void setTransferAccountNumber(int l_intLineNumber, String l_strAccountNumber) 
    {
        final String STR_METHOD_NAME = "setTransferAccountNumber(int,String)";
        log.entering(STR_METHOD_NAME);

        //１）カラムモデル取得
        //２）値セット
        this.setValue(l_intLineNumber, this.getColumnModel(
            WEB3AdminAioCashoutInqDownloadCsv.TRANSFER_ACCOUNT_NUMBER_LABEL),
            l_strAccountNumber);
        
        log.exiting(STR_METHOD_NAME);

    }
    
    /**
     * (set振込先口座名義人)<BR>
     * 振込先名義人をセットする。 <BR>
     * <BR>
     * １）カラムモデル取得<BR>
     * 　@this.getカラムモデル()にてCSVカラムモデルを取得する。<BR>
     * <BR>
     * 　@[引数]   <BR>
     * 　@項目ラベル：　@出金申込問合せダウンロードCSV.振込先口座名義人ラベル 	   <BR>
     * <BR>
     * ２）値セット<BR>
     * 　@this.set項目値()にて、値をセットする。<BR>
     * <BR>
     * 　@[引数] <BR>
     * 　@行番号： （引数）行番号  <BR>
     * 　@カラム： １）で取得したカラムモデル<BR>
     * 　@値： （引数）口座名義人 <BR>
     * @@param l_intLineNumber - (行番号)<BR>
     *              行番号<BR>
     * @@param l_strAccountName - (口座名義人)<BR>
     *              口座名義人
     * @@roseuid 43D5DF890264
     */
    public void setTransferAccountName(int l_intLineNumber, String l_strAccountName) 
    {
        final String STR_METHOD_NAME = "setTransferAccountName(int,String)";
        log.entering(STR_METHOD_NAME);

        //１）カラムモデル取得
        //２）値セット
        this.setValue(l_intLineNumber, this.getColumnModel(
            WEB3AdminAioCashoutInqDownloadCsv.TRANSFER_ACCOUNT_NAME_LABEL),
            l_strAccountName);
        
        log.exiting(STR_METHOD_NAME);

    }
    
    /**
     * (set出金可能額（当日）)<BR>
     * 出金可能額（当日）をセットする。<BR>
     * <BR>
     * １）カラムモデル取得<BR>
     * 　@this.getカラムモデル()にてCSVカラムモデルを取得する。<BR>
     * <BR>
     * 　@[引数]   <BR>
     * 　@項目ラベル：　@出金申込問合せダウンロードCSV.出金可能額（当日）ラベル	 <BR>
     * <BR>
     * ２）値セット<BR>
     * 　@this.set項目値()にて、値をセットする。<BR>
     * <BR>
     * 　@[引数] <BR>
     * 　@行番号： （引数）行番号  <BR>
     * 　@カラム： １）で取得したカラムモデル<BR>
     * 　@値： （引数）出金可能額の小数点以下を切り捨てた値 <BR>
     * @@param l_intLineNumber - (行番号)<BR>
     *              行番号<BR>
     * @@param l_dblCashoutPossiblePrice - (出金可能額)<BR>
     *              出金可能額
     * @@roseuid 43D5DF890264
     */
    public void setCashoutPossiblePriceToday(int l_intLineNumber, double l_dblCashoutPossiblePrice) 
    {
        final String STR_METHOD_NAME = "setCashoutPossiblePriceToday(int,double)";
        log.entering(STR_METHOD_NAME);
        long l_lngTemp =  (long) Math.floor(l_dblCashoutPossiblePrice);

        //１）カラムモデル取得
        //２）値セット
        this.setValue(l_intLineNumber, this.getColumnModel(
            WEB3AdminAioCashoutInqDownloadCsv.CASHOUT_POSSIBLE_PRICE_TODAY_LABEL), 
            new Long(l_lngTemp));
        
        log.exiting(STR_METHOD_NAME);

    }
    
    /**
     * (set出金可能額（１営業日）)<BR>
     * 出金可能額（１営業日）をセットする。<BR>
     * <BR>
     * １）カラムモデル取得<BR>
     * 　@this.getカラムモデル()にてCSVカラムモデルを取得する。<BR>
     * <BR>
     * 　@[引数]   <BR>
     * 　@項目ラベル：　@出金申込問合せダウンロードCSV.出金可能額（１営業日）ラベル	 <BR>
     * <BR>
     * ２）値セット<BR>
     * 　@this.set項目値()にて、値をセットする。<BR>
     * <BR>
     * 　@[引数] <BR>
     * 　@行番号： （引数）行番号  <BR>
     * 　@カラム： １）で取得したカラムモデル<BR>
     * 　@値： （引数）出金可能額の小数点以下を切り捨てた値 <BR>
     * @@param l_intLineNumber - (行番号)<BR>
     *              行番号<BR>
     * @@param l_dblCashoutPossiblePrice - (出金可能額)<BR>
     *              出金可能額
     * @@roseuid 43D5DF890264
     */
    public void setCashoutPossiblePrice1DayAfter(int l_intLineNumber, double l_dblCashoutPossiblePrice) 
    {
        final String STR_METHOD_NAME = "setCashoutPossiblePrice1DayAfter(int,double)";
        log.entering(STR_METHOD_NAME);
        long l_lngTemp =  (long) Math.floor(l_dblCashoutPossiblePrice);

        //１）カラムモデル取得
        //２）値セット
        this.setValue(l_intLineNumber, this.getColumnModel(
            WEB3AdminAioCashoutInqDownloadCsv.CASHOUT_POSSIBLE_PRICE_1DAYAFTER_LABEL), 
            new Long(l_lngTemp));
        
        log.exiting(STR_METHOD_NAME);

    }
    
    /**
     * (set出金可能額（2営業日）)<BR>
     * 出金可能額（ 2営業日）をセットする。<BR>
     * <BR>
     * １）カラムモデル取得<BR>
     * 　@this.getカラムモデル()にてCSVカラムモデルを取得する。<BR>
     * <BR>
     * 　@[引数]   <BR>
     * 　@項目ラベル：　@出金申込問合せダウンロードCSV.出金可能額（2営業日）ラベル	 <BR>
     * <BR>
     * ２）値セット<BR>
     * 　@this.set項目値()にて、値をセットする。<BR>
     * <BR>
     * 　@[引数] <BR>
     * 　@行番号： （引数）行番号  <BR>
     * 　@カラム： １）で取得したカラムモデル<BR>
     * 　@値： （引数）出金可能額の小数点以下を切り捨てた値 <BR>
     * @@param l_intLineNumber - (行番号)<BR>
     *              行番号<BR>
     * @@param l_dblCashoutPossiblePrice - (出金可能額)<BR>
     *              出金可能額
     * @@roseuid 43D5DF890264
     */
    public void setCashoutPossiblePrice2DaysAfter(int l_intLineNumber, double l_dblCashoutPossiblePrice) 
    {
        final String STR_METHOD_NAME = "setCashoutPossiblePrice2DaysAfter(int,double)";
        log.entering(STR_METHOD_NAME);
        long l_lngTemp =  (long) Math.floor(l_dblCashoutPossiblePrice);

        //１）カラムモデル取得
        //２）値セット
        this.setValue(l_intLineNumber, this.getColumnModel(
            WEB3AdminAioCashoutInqDownloadCsv.CASHOUT_POSSIBLE_PRICE_2DAYSAFTER_LABEL), 
            new Long(l_lngTemp));
        
        log.exiting(STR_METHOD_NAME);

    }
     
    /**
     * (set出金可能額（3営業日）)<BR>
     * 出金可能額（ 3営業日）をセットする。<BR>
     * <BR>
     * １）カラムモデル取得<BR>
     * 　@this.getカラムモデル()にてCSVカラムモデルを取得する。<BR>
     * <BR>
     * 　@[引数]   <BR>
     * 　@項目ラベル：　@出金申込問合せダウンロードCSV.出金可能額（3営業日）ラベル	 <BR>
     * <BR>
     * ２）値セット<BR>
     * 　@this.set項目値()にて、値をセットする。<BR>
     * <BR>
     * 　@[引数] <BR>
     * 　@行番号： （引数）行番号  <BR>
     * 　@カラム： １）で取得したカラムモデル<BR>
     * 　@値： （引数）出金可能額の小数点以下を切り捨てた値 <BR>
     * @@param l_intLineNumber - (行番号)<BR>
     *              行番号<BR>
     * @@param l_dblCashoutPossiblePrice - (出金可能額)<BR>
     *              出金可能額
     * @@roseuid 43D5DF890264
     */
    public void setCashoutPossiblePrice3DaysAfter(int l_intLineNumber, double l_dblCashoutPossiblePrice) 
    {
        final String STR_METHOD_NAME = "setCashoutPossiblePrice3DaysAfter(int,double)";
        log.entering(STR_METHOD_NAME);
        long l_lngTemp =  (long) Math.floor(l_dblCashoutPossiblePrice);

        //１）カラムモデル取得
        //２）値セット
        this.setValue(l_intLineNumber, this.getColumnModel(
            WEB3AdminAioCashoutInqDownloadCsv.CASHOUT_POSSIBLE_PRICE_3DAYSAFTER_LABEL), 
            new Long(l_lngTemp));
        
        log.exiting(STR_METHOD_NAME);

    }
    
    /**
     * (set出金可能額（4営業日）)<BR>
     * 出金可能額（ 4営業日）をセットする。<BR>
     * <BR>
     * １）カラムモデル取得<BR>
     * 　@this.getカラムモデル()にてCSVカラムモデルを取得する。<BR>
     * <BR>
     * 　@[引数]   <BR>
     * 　@項目ラベル：　@出金申込問合せダウンロードCSV.出金可能額（4営業日）ラベル	 <BR>
     * <BR>
     * ２）値セット<BR>
     * 　@this.set項目値()にて、値をセットする。<BR>
     * <BR>
     * 　@[引数] <BR>
     * 　@行番号： （引数）行番号  <BR>
     * 　@カラム： １）で取得したカラムモデル<BR>
     * 　@値： （引数）出金可能額の小数点以下を切り捨てた値 <BR>
     * @@param l_intLineNumber - (行番号)<BR>
     *              行番号<BR>
     * @@param l_dblCashoutPossiblePrice - (出金可能額)<BR>
     *              出金可能額
     * @@roseuid 43D5DF890264
     */
    public void setCashoutPossiblePrice4DaysAfter(int l_intLineNumber, double l_dblCashoutPossiblePrice) 
    {
        final String STR_METHOD_NAME = "setCashoutPossiblePrice4DaysAfter(int,double)";
        log.entering(STR_METHOD_NAME);
        long l_lngTemp =  (long) Math.floor(l_dblCashoutPossiblePrice);

        //１）カラムモデル取得
        //２）値セット
        this.setValue(l_intLineNumber, this.getColumnModel(
            WEB3AdminAioCashoutInqDownloadCsv.CASHOUT_POSSIBLE_PRICE_4DAYSAFTER_LABEL), 
            new Long(l_lngTemp));
        
        log.exiting(STR_METHOD_NAME);

    }
    
    /**
     * (set出金可能額（5営業日）)<BR>
     * 出金可能額（5営業日）をセットする。<BR>
     * <BR>
     * １）カラムモデル取得<BR>
     * 　@this.getカラムモデル()にてCSVカラムモデルを取得する。<BR>
     * <BR>
     * 　@[引数]   <BR>
     * 　@項目ラベル：　@出金申込問合せダウンロードCSV.出金可能額（5営業日）ラベル	 <BR>
     * <BR>
     * ２）値セット<BR>
     * 　@this.set項目値()にて、値をセットする。<BR>
     * <BR>
     * 　@[引数] <BR>
     * 　@行番号： （引数）行番号  <BR>
     * 　@カラム： １）で取得したカラムモデル<BR> 
     * 　@値： （引数）出金可能額の小数点以下を切り捨てた値 <BR>
     * @@param l_intLineNumber - (行番号)<BR>
     *              行番号<BR>
     * @@param l_dblCashoutPossiblePrice - (出金可能額)<BR>
     *              出金可能額
     * @@roseuid 43D5DF890264
     */
    public void setCashoutPossiblePrice5DaysAfter(int l_intLineNumber, double l_dblCashoutPossiblePrice) 
    {
        final String STR_METHOD_NAME = "setCashoutPossiblePrice5DaysAfter(int,double)";
        log.entering(STR_METHOD_NAME);
        long l_lngTemp =  (long) Math.floor(l_dblCashoutPossiblePrice);

        //１）カラムモデル取得
        //２）値セット
        this.setValue(l_intLineNumber, this.getColumnModel(
            WEB3AdminAioCashoutInqDownloadCsv.CASHOUT_POSSIBLE_PRICE_5DAYSAFTER_LABEL), 
            new Long(l_lngTemp));
        
        log.exiting(STR_METHOD_NAME);

    }
    
    /**
     * (set出金可能額)<BR>
     * 計算結果をセットする。 <BR>
     * <BR>
     * １）引数:営業日区分 == 0 の場合、this.set出金可能額（当日）（引数:行番号 , 引数:計算結果）<BR>
     * <BR>
     * ２）引数:営業日区分 == 1 の場合、this.set出金可能額（1営業日）（引数:行番号 , 引数:計算結果） <BR>
     * <BR>
     * ３）引数:営業日区分 == 2 の場合、this.set出金可能額（2営業日）（引数:行番号 , 引数:計算結果） <BR>
     *<BR>
     * ４）引数:営業日区分 == 3 の場合、this.set出金可能額（3営業日）（引数:行番号 , 引数:計算結果） <BR>
     * <BR>
     * ５）引数:営業日区分 == 4 の場合、this.set出金可能額（4営業日）（引数:行番号 , 引数:計算結果） <BR> 
     * <BR>
     * ６）引数:営業日区分 == 5 の場合、this.set出金可能額（5営業日）（引数:行番号 , 引数:計算結果）  <BR>
     * <BR>
     * @@param l_dblResult - (計算結果)<BR>
     *              計算結果<BR>
     * @@param l_intLineNumber - (行番号)<BR>
     *              行番号
     * @@param l_intBizDateType - (営業日区分)<BR>
     *              営業日区分
     * @@roseuid 43D5DF890264
     */
    public void setCashoutPossiblePrice(double l_dblResult, int l_intLineNumber, int l_intBizDateType) 
    {
        final String STR_METHOD_NAME = "setCashoutPossiblePrice(double,int,int)";
        log.entering(STR_METHOD_NAME);
        //引数:営業日区分 == 0 の場合
        if(l_intBizDateType == 0)
        {
            this.setCashoutPossiblePriceToday(l_intLineNumber, l_dblResult);
        } 
        else if(1 == l_intBizDateType)
        {
            this.setCashoutPossiblePrice1DayAfter(l_intLineNumber, l_dblResult);
        }
        else if(2 == l_intBizDateType)
        {
            this.setCashoutPossiblePrice2DaysAfter(l_intLineNumber, l_dblResult);
        }
        else if(3 == l_intBizDateType)
        {
            this.setCashoutPossiblePrice3DaysAfter(l_intLineNumber, l_dblResult);
        }
        else if(4 == l_intBizDateType)
        {
            this.setCashoutPossiblePrice4DaysAfter(l_intLineNumber, l_dblResult);
        }
        else if(5 == l_intBizDateType)
        {
            this.setCashoutPossiblePrice5DaysAfter(l_intLineNumber, l_dblResult);
        }
        
        log.exiting(STR_METHOD_NAME);

    }
    
    /**
     * (isカラムヘッダ行出力)<BR>
     * （オーバーライドメソッド） <BR>
     * CSVファ@イルにカラムヘッダ行の出力要否を判定する。 <BR>
     * <BR>
     * trueを返却する。 <BR>
     * @@return boolean
     * @@roseuid 43D5F38F01B9
     */
    public boolean isColumnHeadOutput() 
    {
        return true;
    }
    
    /**
     * (set現物顧客余力情報)<BR>
     * 現物顧客の余力情報をセットする。 <BR>
     * <BR>
     * １）　@出金可能額（当日）～（5営業日）までLoop処理を行う。（0～5） <BR>
     * <BR>
     * 　@１－１）double 計算結果 = 引数:資産余力情報<現物顧客>オブジェクト. <BR>
     * 　@　@calcその他商品買付可能額<日計り拘束金考慮>（ i ）; <BR>
     * <BR>
     * 　@１－２）this.set出金可能額（ 計算結果 , 行番号 , i ）;<BR>
     * @@param l_tpTradingPowerCalcEquity - (資産余力情報<現物顧客>オブジェクト)<BR>
     *              資産余力情報<現物顧客>オブジェクト<BR>
     * @@param l_intLineNumber - (行番号)<BR>
     *              行番号
     * @@roseuid 43D5DF890264
     */   
    public void setTPTradingPowerCalcEquity(WEB3TPTradingPowerCalcEquity l_tpTradingPowerCalcEquity, 
        int l_intLineNumber) 
    {
        double l_dblResult = 0.0D;
        
        //１）　@出金可能額（当日）～（5営業日）までLoop処理を行う。（0～5） 
        for (int l_intCount = 0; l_intCount < 6; l_intCount ++)
        {
            //１－１）　@double 計算結果 = 引数:資産余力情報<現物顧客>オブジェクト. 
            // 　@calcその他商品買付可能額<日計り拘束金考慮>（ i ）; 
            if (l_tpTradingPowerCalcEquity != null)
            {
                l_dblResult = l_tpTradingPowerCalcEquity.calcOtherTradingPower(l_intCount);
            }

            //１－２）　@this.set出金可能額（ 計算結果 , 行番号 , i ）;
            this.setCashoutPossiblePrice(l_dblResult, l_intLineNumber, l_intCount);
        }
    }
    
    /**
     * (set信用顧客余力情報)<BR>
     * 信用顧客の余力情報をセットする。 <BR>
     * <BR>
     * １）　@出金可能額（当日）～（5営業日）までLoop処理を行う。（0～5） <BR>
     * <BR>
     * 　@１－１）　@double 計算結果 = 引数:資産余力情報<信用顧客>オブジェクト. <BR>
     * 　@　@calcその他商品買付可能額<日計り拘束金考慮>（ i ）; <BR>
     * <BR>
     * 　@１－２）　@this.set出金可能額（ 計算結果 , 行番号 , i ）;<BR>
     * @@param l_tpTradingPowerCalcMargin - (資産余力情報<信用顧客>オブジェクト)<BR>
     *              資産余力情報<信用顧客>オブジェクト<BR>
     * @@param l_intLineNumber - (行番号)<BR>
     *              行番号
     * @@roseuid 43D5DF890264
     */
    public void setTPTradingPowerCalcMargin(WEB3TPTradingPowerCalcMargin l_tpTradingPowerCalcMargin, 
        int l_intLineNumber) 
    {
        double l_dblResult = 0.0D;
        
        //１）　@出金可能額（当日）～（5営業日）までLoop処理を行う。（0～5） 
        for (int i = 0; i < 6; i++)
        {
            //１－１）　@double 計算結果 = 引数:資産余力情報<信用顧客>オブジェクト. 
            //　@　@calcその他商品買付可能額<日計り拘束金考慮>（ i ）; 
            if (l_tpTradingPowerCalcMargin != null)
            {
                l_dblResult = l_tpTradingPowerCalcMargin.calcOtherTradingPower(i);
            }
            
            //１－２）　@this.set出金可能額（ 計算結果 , 行番号 , i ）;
            this.setCashoutPossiblePrice(l_dblResult, l_intLineNumber, i);
        }
    }
}
@
