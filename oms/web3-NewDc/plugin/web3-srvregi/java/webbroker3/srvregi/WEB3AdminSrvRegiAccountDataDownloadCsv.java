head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.14.06.40.34;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	7984d7db5d91d86;
filename	WEB3AdminSrvRegiAccountDataDownloadCsv.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : 顧客データダウンロードCSV(WEB3AdminSrvRegiAccountDataDownloadCsv.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2004/10/18 鄭海良(中訊) 新規作成
Revesion History : 2007/04/06 齊珂  (中訊) 仕様変更モデル 235
Revesion History : 2007/04/09 齊珂  (中訊) 仕様変更モデル 236
Revesion History : 2007/04/12 CInomata(SCS) 仕様変更モデル 237
*/

package webbroker3.srvregi;

import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.Date;

import com.fitechlabs.xtrade.kernel.boot.Services;
import com.fitechlabs.xtrade.kernel.data.DataCallbackException;
import com.fitechlabs.xtrade.kernel.data.DataException;
import com.fitechlabs.xtrade.kernel.data.DataNetworkException;
import com.fitechlabs.xtrade.kernel.data.DataQueryException;
import com.fitechlabs.xtrade.kernel.data.Processors;
import com.fitechlabs.xtrade.kernel.data.QueryProcessor;
import com.fitechlabs.xtrade.kernel.data.TransactionCallback;
import com.fitechlabs.xtrade.plugin.tc.gentrade.FinApp;
import com.fitechlabs.xtrade.plugin.tc.gentrade.GtlUtils;
import com.fitechlabs.xtrade.plugin.tc.gentrade.NotFoundException;
import com.fitechlabs.xtrade.plugin.tc.gentrade.SubAccount;
import com.fitechlabs.xtrade.plugin.tc.gentrade.SubAccountTypeEnum;

import webbroker3.common.WEB3BaseException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.common.WEB3SystemLayerException;
import webbroker3.common.define.WEB3SrvRegiCancelDivDef;
import webbroker3.gentrade.WEB3GentradeAccountManager;
import webbroker3.gentrade.WEB3GentradeCsvColumnModel;
import webbroker3.gentrade.WEB3GentradeCsvDataModel;
import webbroker3.gentrade.WEB3GentradeMainAccount;
import webbroker3.gentrade.WEB3GentradeSubAccount;
import webbroker3.srvregi.define.WEB3SrvRegiAppliLotDivDef;
import webbroker3.tradingpower.WEB3TPTradingPowerService;
import webbroker3.util.WEB3DateUtility;
import webbroker3.util.WEB3LogUtility;
import webbroker3.util.WEB3StringTypeUtility;


/**
 * (顧客データダウンロードCSV)<BR>
 *  
 * @@author 鄭海良
 * @@version 1.0  
 */
public class WEB3AdminSrvRegiAccountDataDownloadCsv extends WEB3GentradeCsvDataModel 
{
    
    /**
     * (ログユーティリティ)<BR>
     */
    private static WEB3LogUtility log =
        WEB3LogUtility.getInstance(
            WEB3AdminSrvRegiAccountDataDownloadCsv.class);
    
    /**
     * (申込登録IDラベル)<BR>
     * 定数定義プロパティ　@”申込登録ID” <BR>
     */
    public static  String REGIST_ID_LABEL = "申込登録ID";
    
    /**
     * (証券会社コードラベル)<BR>
     * 定数定義プロパティ　@”証券会社コード” <BR>
     */
    public static  String INSTITUTION_CODE_LABEL = "証券会社コード";
    
    /**
     * (部店コードラベル)<BR>
     * 定数定義プロパティ　@”部店コード” <BR>
     */
    public static  String BRANCH_CODE_LABEL = "部店コード";
    
    /**
     * (サービス区分ラベル)<BR>
     * 定数定義プロパティ　@”サービス区分” <BR>
     */
    public static  String SRV_DIV_LABEL = "サービス区分";
    
    /**
     * (顧客コードラベル)<BR>
     * 定数定義プロパティ　@”顧客コード” <BR>
     */
    public static  String ACCOUNT_CODE_LABEL = "顧客コード";
    
    /**
     * (顧客名ラベル)<BR>
     * 定数定義プロパティ　@”顧客名” <BR>
     */
    public static  String ACCOUNT_NAME_LABEL = "顧客名";
    
    /**
     * (登録区分ラベル)<BR>
     * 定数定義プロパティ　@"登録区分"<BR>
     */
    public static  String PAYMENT_DIV_LABEL = "登録区分";
    
    /**
     * (申込抽選区分ラベル)<BR>
     * 定数定義プロパティ　@"申込抽選区分"<BR>
     */
    public static  String APPLI_LOT_DIV_LABEL = "申込抽選区分";
    
    /**
     * (申込日ラベル)<BR>
     * 定数定義プロパティ　@"申込日"<BR>
     */
    public static  String APPLI_DATE_LABEL = "申込日";
    
    /**
     * (適用開始日ラベル)<BR>
     * 定数定義プロパティ　@"適用開始日"<BR>
     */
    public static  String APPLI_START_DATE_LABEL = "適用開始日";
    
    /**
     * (適用終了日ラベル)<BR>
     * 定数定義プロパティ　@"適用終了日"<BR>
     */
    public static  String APPLI_END_DATE_LABEL = "適用終了日";
    
    /**
     * (利用料金ラベル)<BR>
     * 定数定義プロパティ　@"利用料金"<BR>
     */
    public static  String USE_AMT_LABEL = "利用料金";
    
    /**
     * (出金余力ラベル)<BR>
     * 定数定義プロパティ　@"出金余力"<BR>
     */
    public static  String PAYMENT_POWER_LABEL = "出金余力";
    
    /**
     * (出金日ラベル)<BR>
     * 定数定義プロパティ　@"出金日"<BR>
     */
    public static  String PAYMENT_DATE_LABEL = "出金日";
    
    /**
     * (顧客データCSV)
     * コンストラクタ<BR>
     * <BR>
     * インスタンスを生成し、ヘッダ情報をセットする。 <BR>
     * 　@－super()にてインスタンスを生成する。 <BR>
     * 　@－createキーヘッダ()をコールし、キーヘッダ情報を作成する。 <BR>
     * 　@－createカラムヘッダ()をコールし、カラムヘッダ情報を作成する。 <BR>
     * @@roseuid 4104E458009C
     */
    public WEB3AdminSrvRegiAccountDataDownloadCsv() 
    {
        super();
        createKeyHeader();
        createColumnHeader(); 
    }
    
    /**
     * (createカラムヘッダ())<BR>
     * カラムヘッダをセットする。<BR>
     * <BR>
     * 　@以下の通りCSVカラムモデルの配列を生成し、setカラムヘッダ()にてインスタンスにセットする。 <BR>
     * <BR>
     * [カラムヘッダ配列] <BR>
     * <BR>
     * －　@index = 0 <BR>
     * 　@[CSVカラムモデル コンストラクタの引数] <BR>
     * 　@項目ラベル：　@顧客データCSV.申込登録IDラベル <BR>
     * 　@カラム番号： 0 <BR>
     * 　@項目型：　@CSVカラムモデル.項目型_文字列 <BR>
     * 　@日付フォーマット：　@null <BR>
     * －　@index = 1 <BR>
     * 　@[CSVカラムモデル コンストラクタの引数] <BR>
     * 　@項目ラベル：　@顧客データCSV.証券会社コードラベル <BR>
     * 　@カラム番号： 1 <BR>
     * 　@項目型：　@CSVカラムモデル.項目型_文字列 <BR>
     * 　@日付フォーマット：　@null <BR>
     * <BR>
     * －　@index = 2 <BR>
     * 　@[CSVカラムモデル コンストラクタの引数] <BR>
     * 　@項目ラベル：　@顧客データCSV.部店コードラベル <BR>
     * 　@カラム番号： 2 <BR>
     * 　@項目型：　@CSVカラムモデル.項目型_文字列 <BR>
     * 　@日付フォーマット：　@null <BR>
     * <BR>
     * －　@index = 3 <BR>
     * 　@[CSVカラムモデル コンストラクタの引数] <BR>
     * 　@項目ラベル：　@顧客データCSV.サービス区分ラベル<BR>
     * 　@カラム番号： 3 <BR>
     * 　@項目型：　@CSVカラムモデル.項目型_文字列 <BR>
     * 　@日付フォーマット：　@null <BR>
     * <BR>
     * －　@index = 4 <BR>
     * 　@[CSVカラムモデル コンストラクタの引数] <BR>
     * 　@項目ラベル：　@顧客データCSV.顧客コードラベル <BR>
     * 　@カラム番号： 4 <BR>
     * 　@項目型：　@CSVカラムモデル.項目型_文字列 <BR>
     * 　@日付フォーマット：　@null <BR>
     * <BR>
     * －　@index = 5 <BR>
     * 　@[CSVカラムモデル コンストラクタの引数] <BR>
     * 　@項目ラベル：　@顧客データCSV.顧客名ラベル <BR>
     * 　@カラム番号： 5<BR>
     * 　@項目型：　@CSVカラムモデル.項目型_文字列 <BR>
     * 　@日付フォーマット：　@null <BR>
     * <BR>
     * －　@index = 6<BR>
     * 　@[CSVカラムモデル コンストラクタの引数] <BR>
     * 　@項目ラベル：　@顧客データCSV.申込抽選区分ラベル <BR>
     * 　@カラム番号： 6<BR>
     * 　@項目型：　@CSVカラムモデル.項目型_文字列 <BR>
     * 　@日付フォーマット：　@null<BR>
     * <BR>
     * －　@index = 7<BR>
     * 　@[CSVカラムモデル コンストラクタの引数] <BR>
     * 　@項目ラベル：　@顧客データCSV.申込日ラベル <BR>
     * 　@カラム番号： 7<BR>
     * 　@項目型：　@CSVカラムモデル.項目型_日付時間 <BR>
     * 　@日付フォーマット：　@new SimpleDateFormat("yyyyMMdd")<BR>
     * <BR>
     * －　@index = 8<BR>
     * 　@[CSVカラムモデル コンストラクタの引数] <BR>
     * 　@項目ラベル：　@顧客データCSV.適用開始日ラベル <BR>
     * 　@カラム番号： 8<BR>
     * 　@項目型：　@CSVカラムモデル.項目型_日付時間 <BR>
     * 　@日付フォーマット：　@new SimpleDateFormat("yyyyMMdd")<BR>
     * <BR>
     * －　@index = 9<BR>
     * 　@[CSVカラムモデル コンストラクタの引数] <BR>
     * 　@項目ラベル：　@顧客データCSV.適用終了日ラベル <BR>
     * 　@カラム番号： 9<BR>
     * 　@項目型：　@CSVカラムモデル.項目型_日付時間 <BR>
     * 　@日付フォーマット：　@new SimpleDateFormat("yyyyMMdd")<BR>
     * <BR>
     * －　@index = 10<BR>
     * 　@[CSVカラムモデル コンストラクタの引数] <BR>
     * 　@項目ラベル：　@顧客データCSV.登録区分ラベル <BR>
     * 　@カラム番号： 10<BR>
     * 　@項目型：　@CSVカラムモデル.項目型_文字列 <BR>
     * 　@日付フォーマット：　@null<BR>
     * <BR>
     * －　@index = 11<BR>
     * 　@[CSVカラムモデル コンストラクタの引数] <BR>
     * 　@項目ラベル：　@顧客データCSV.利用料金ラベル <BR>
     * 　@カラム番号： 11<BR>
     * 　@項目型：　@CSVカラムモデル.項目型_数値（double） <BR>
     * 　@日付フォーマット：　@null<BR>
     * <BR>
     * －　@index = 12<BR>
     * 　@[CSVカラムモデル コンストラクタの引数] <BR>
     * 　@項目ラベル：　@顧客データCSV.出金余力ラベル <BR>
     * 　@カラム番号： 12<BR>
     * 　@項目型：　@CSVカラムモデル.項目型_数値（double） <BR>
     * 　@日付フォーマット：　@null<BR>
     * <BR>
     * －　@index = 13<BR>
     * 　@[CSVカラムモデル コンストラクタの引数] <BR>
     * 　@項目ラベル：　@顧客データCSV.出金日ラベル <BR>
     * 　@カラム番号： 13<BR>
     * 　@項目型：　@CSVカラムモデル.項目型_日付時間 <BR>
     * 　@日付フォーマット：　@new SimpleDateFormat("yyyyMMdd")<BR>
     * @@roseuid 4104DB280158
     */
    private void createColumnHeader() 
    {
        final String STR_METHOD_NAME = " createColumnHeader()";
        log.entering(STR_METHOD_NAME );

        final int COLUMN_MAX = 14;
        
        WEB3GentradeCsvColumnModel[] l_models = new WEB3GentradeCsvColumnModel[COLUMN_MAX];
        
        //index 0
        l_models[0] =
            new WEB3GentradeCsvColumnModel(
                WEB3AdminSrvRegiAccountDataDownloadCsv.REGIST_ID_LABEL,
                0,
                WEB3GentradeCsvColumnModel.STRINGTYPE,
                null);

        //index 1
        l_models[1] =
            new WEB3GentradeCsvColumnModel(
                WEB3AdminSrvRegiAccountDataDownloadCsv.INSTITUTION_CODE_LABEL,
                1,
                WEB3GentradeCsvColumnModel.STRINGTYPE,
                null);

        //index 2
        l_models[2] =
            new WEB3GentradeCsvColumnModel(
                WEB3AdminSrvRegiAccountDataDownloadCsv.BRANCH_CODE_LABEL,
                2,
                WEB3GentradeCsvColumnModel.STRINGTYPE,
                null);

        //index 3
        l_models[3] =
            new WEB3GentradeCsvColumnModel(
                WEB3AdminSrvRegiAccountDataDownloadCsv.SRV_DIV_LABEL,
                3,
                WEB3GentradeCsvColumnModel.STRINGTYPE,
                null);

        //index 4
        l_models[4] =
            new WEB3GentradeCsvColumnModel(
                WEB3AdminSrvRegiAccountDataDownloadCsv.ACCOUNT_CODE_LABEL,
                4,
                WEB3GentradeCsvColumnModel.STRINGTYPE,
                null);

        //index 5
        l_models[5] =
            new WEB3GentradeCsvColumnModel(
                WEB3AdminSrvRegiAccountDataDownloadCsv.ACCOUNT_NAME_LABEL,
                5,
                WEB3GentradeCsvColumnModel.STRINGTYPE,
                null);

        //index 6
        l_models[6] =
            new WEB3GentradeCsvColumnModel(
                WEB3AdminSrvRegiAccountDataDownloadCsv.APPLI_LOT_DIV_LABEL,
                6,
                WEB3GentradeCsvColumnModel.STRINGTYPE,
                null);

        //index 7
        l_models[7] =
            new WEB3GentradeCsvColumnModel(
                WEB3AdminSrvRegiAccountDataDownloadCsv.APPLI_DATE_LABEL,
                7,
                WEB3GentradeCsvColumnModel.TIMESTAMPTYPE,
                new SimpleDateFormat("yyyyMMdd"));

        //index 8
        l_models[8] =
            new WEB3GentradeCsvColumnModel(
                WEB3AdminSrvRegiAccountDataDownloadCsv.APPLI_START_DATE_LABEL,
                8,
                WEB3GentradeCsvColumnModel.TIMESTAMPTYPE,
                new SimpleDateFormat("yyyyMMdd"));

        //index 9
        l_models[9] =
            new WEB3GentradeCsvColumnModel(
                WEB3AdminSrvRegiAccountDataDownloadCsv.APPLI_END_DATE_LABEL,
                9,
                WEB3GentradeCsvColumnModel.TIMESTAMPTYPE,
                new SimpleDateFormat("yyyyMMdd"));

        //index 10
        l_models[10] =
            new WEB3GentradeCsvColumnModel(
                WEB3AdminSrvRegiAccountDataDownloadCsv.PAYMENT_DIV_LABEL,
                10,
                WEB3GentradeCsvColumnModel.STRINGTYPE,
                null);

        //index 11
        l_models[11] =
            new WEB3GentradeCsvColumnModel(
                WEB3AdminSrvRegiAccountDataDownloadCsv.USE_AMT_LABEL,
                11,
                WEB3GentradeCsvColumnModel.DOUBLETYPE,
                null);

        //index 12
        l_models[12] =
            new WEB3GentradeCsvColumnModel(
                WEB3AdminSrvRegiAccountDataDownloadCsv.PAYMENT_POWER_LABEL,
                12,
                WEB3GentradeCsvColumnModel.DOUBLETYPE,
                null);

        //index 13
        l_models[13] =
            new WEB3GentradeCsvColumnModel(
                WEB3AdminSrvRegiAccountDataDownloadCsv.PAYMENT_DATE_LABEL,
                13,
                WEB3GentradeCsvColumnModel.TIMESTAMPTYPE,
                new SimpleDateFormat("yyyyMMdd"));
        
        this.setColumnHeader(l_models);
        
        log.exiting(STR_METHOD_NAME); 
    }
    
    /**
     * (createキーヘッダ())<BR>
     * キーヘッダをセットする。 <BR>
     * <BR>
     * 　@以下の通り文字列の配列を生成し、setキーヘッダ()にてインスタンスにセットする。 <BR>
     * <BR>
     * [キーヘッダ配列] <BR>
     * <BR>
     * －　@index = 0 <BR>
     * 　@現在日時を"yyyy/MM/dd HH:mm:ss"の形式にformatした文字列。 <BR>
     * <BR>
     * (*1) 現在日時 <BR>
     * TradingSystem.getSystemTimestamp() <BR>
     * @@roseuid 4104DBE602EE
     */
    private void createKeyHeader() 
    {
        final String STR_METHOD_NAME = " createKeyHeader()";
        log.entering(STR_METHOD_NAME );
        
        Timestamp l_tsSysDate = GtlUtils.getTradingSystem().getSystemTimestamp();
        String l_strCurrentTime = WEB3DateUtility.formatDate(l_tsSysDate, "yyyy/MM/dd HH:mm:ss");
        String[] l_strKeyHeaders = {l_strCurrentTime};
        
        this.setKeyHeader(l_strKeyHeaders);
        
        log.exiting(STR_METHOD_NAME); 
    }
    
    /**
     * (set申込登録ＩＤ)<BR>
     * 証券会社コードをセットする。<BR>
     * <BR>
     * １）　@カラムモデル取得 <BR>
     * 　@this.getカラムモデル()にてCSVカラムモデルを取得する。 <BR>
     * <BR>
     * 　@[getカラムモデル()に指定する引数] <BR>
     * 　@項目ラベル：　@顧客データCSV.申込登録IDラベル <BR>
     * <BR>
     * ２）　@値セット <BR>
     * 　@this.set項目値()にて項目値をセットする。 <BR>
     * <BR>
     * 　@[set項目値()に指定する引数] <BR>
     * 　@行番号：　@引数の行番号 <BR>
     * 　@カラム：　@１）で取得したカラムモデル <BR>
     * 　@値：　@引数の申込登録ID <BR>
     * @@param l_intLineNumber - (行番号)<BR>
     * 行番号を指定する。<BR>
     * @@param l_strRegistId - (申込登録ID)<BR>
     * 申込登録IDを指定する。<BR>
     * @@roseuid 41201E740154
     */
    public void setRegistId(int l_intLineNumber, String l_strRegistId) 
    {
        final String STR_METHOD_NAME = " setRegistId(int , String ) ";
        log.entering(STR_METHOD_NAME );
        
        //１）　@カラムモデル取得<BR>
        WEB3GentradeCsvColumnModel l_model =
            this.getColumnModel(WEB3AdminSrvRegiAccountDataDownloadCsv.REGIST_ID_LABEL);
        
        //２）　@値セット
        this.setValue(l_intLineNumber, l_model, l_strRegistId);
        
        log.exiting(STR_METHOD_NAME); 
    }
    
    /**
     * (set証券会社コード)<BR>
     * 証券会社コードをセットする。<BR>
     * <BR>
     * １）　@カラムモデル取得 <BR>
     * 　@this.getカラムモデル()にてCSVカラムモデルを取得する。 <BR>
     * <BR>
     * 　@[getカラムモデル()に指定する引数] <BR>
     * 　@項目ラベル：　@顧客データCSV.証券会社コードラベル <BR>
     * <BR>
     * ２）　@値セット <BR>
     * 　@this.set項目値()にて項目値をセットする。 <BR>
     * <BR>
     * 　@[set項目値()に指定する引数] <BR>
     * 　@行番号：　@引数の行番号 <BR>
     * 　@カラム：　@１）で取得したカラムモデル <BR>
     * 　@値：　@引数の証券会社コード <BR>
     * @@param l_intLineNumber - (行番号)<BR>
     * 行番号を指定する。<BR>
     * @@param l_strInstitutionCode - (証券会社コード)<BR>
     * 証券会社コードを指定する。<BR>
     * @@roseuid 4104D9BF00BC
     */
    public void setInstitutionCode(int l_intLineNumber, String l_strInstitutionCode) 
    {
        final String STR_METHOD_NAME = " setInstitutionCode(int , String )";
        log.entering(STR_METHOD_NAME );
       
        //１）　@カラムモデル取得<BR>
        WEB3GentradeCsvColumnModel l_model =
            this.getColumnModel(WEB3AdminSrvRegiAccountDataDownloadCsv.INSTITUTION_CODE_LABEL);
        
        //２）　@値セット
        this.setValue(l_intLineNumber, l_model, l_strInstitutionCode);
               
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
     * 　@項目ラベル：　@顧客データCSV.部店コードラベル <BR>
     * <BR>
     * ２）　@値セット <BR>
     * 　@this.set項目値()にて項目値をセットする。 <BR>
     * <BR>
     * 　@[set項目値()に指定する引数] <BR>
     * 　@行番号：　@引数の行番号 <BR>
     * 　@カラム：　@１）で取得したカラムモデル <BR>
     * 　@値：　@サービス申込登録テーブルの部店コード <BR>
     * @@param l_intLineNumber - (行番号)<BR>
     * 行番号を指定する。<BR>
     * @@param l_strBranchCode - (部店コード)<BR>
     * 部店コードを指定する。<BR>
     * @@roseuid 4104D9F3035B
     */
    public void setBranchCode(int l_intLineNumber, String l_strBranchCode) 
    {
        final String STR_METHOD_NAME = " setBranchCode(int , String )";
        log.entering(STR_METHOD_NAME );
       
        //１）　@カラムモデル取得<BR>
        WEB3GentradeCsvColumnModel l_model =
            this.getColumnModel(WEB3AdminSrvRegiAccountDataDownloadCsv.BRANCH_CODE_LABEL);
        
        //２）　@値セット
        this.setValue(l_intLineNumber, l_model, l_strBranchCode);
        
        log.exiting(STR_METHOD_NAME); 
    }
    
    /**
     * (setサービス区分)<BR>
     * サービス区分をセットする。 <BR>
     * <BR>
     * １）　@カラムモデル取得 <BR>
     * 　@this.getカラムモデル()にてCSVカラムモデルを取得する。 <BR>
     * <BR>
     * 　@[getカラムモデル()に指定する引数] <BR>
     * 　@項目ラベル：　@顧客データCSV.サービス区分ラベル <BR>
     * <BR>
     * ２）　@値セット <BR>
     * 　@this.set項目値()にて項目値をセットする。 <BR>
     * <BR>
     * 　@[set項目値()に指定する引数] <BR>
     * 　@行番号：　@引数の行番号 <BR>
     * 　@カラム：　@１）で取得したカラムモデル <BR>
     * 　@値：　@サービス申込登録テーブルのサービス区分<BR>
     * @@param l_intLineNumber - (行番号)<BR>
     * 行番号を指定する。<BR>
     * @@param l_strSrvDiv - (サービス区分)<BR>
     * サービス区分を指定する。<BR>
     * @@roseuid 4104DA000196
     */
    public void setSrvDiv(int l_intLineNumber, String l_strSrvDiv) 
    {
        final String STR_METHOD_NAME = " setSrvDiv(int , String )";
        log.entering(STR_METHOD_NAME );

        //１）　@カラムモデル取得<BR>
        WEB3GentradeCsvColumnModel l_model =
            this.getColumnModel(WEB3AdminSrvRegiAccountDataDownloadCsv.SRV_DIV_LABEL);
        
        //２）　@値セット
        this.setValue(l_intLineNumber, l_model, l_strSrvDiv);
        
        log.exiting(STR_METHOD_NAME); 
    }
    
    /**
     * (set顧客)<BR>
     * 顧客コード、顧客名をセットする。 <BR>
     * <BR>
     * １）　@顧客オブジェクト取得 <BR>
     * 　@口座コードに該当する顧客オブジェクトを取得する。 <BR>
     * <BR>
     * 　@　@③ 顧客オブジェクトを取得する。<BR>
     * 　@　@　@　@アカウントマネージャ.get顧客（証券会社コード、部店コード、口座コード）<BR>
     * <BR>
     * ２）　@（顧客コード）カラムモデル取得 <BR>
     * 　@this.getカラムモデル()にてCSVカラムモデルを取得する。 <BR>
     * <BR>
     * 　@[getカラムモデル()に指定する引数] <BR>
     * 　@項目ラベル：　@顧客データCSV.顧客コードラベル <BR>
     * <BR>
     * ３）　@（顧客コード）値セット <BR>
     * 　@this.set項目値()にて項目値をセットする。 <BR>
     * <BR>
     * 　@[set項目値()に指定する引数] <BR>
     * 　@行番号：　@引数の行番号 <BR>
     * 　@カラム：　@２）で取得したカラムモデル <BR>
     * 　@値：　@取得した顧客オブジェクト.get表示顧客コード()の戻り値<BR>
     * <BR>
     * ４）　@（顧客名）カラムモデル取得 <BR>
     * 　@this.getカラムモデル()にてCSVカラムモデルを取得する。 <BR>
     * <BR>
     * 　@[getカラムモデル()に指定する引数] <BR>
     * 　@項目ラベル：　@顧客データCSV.顧客名ラベル <BR>
     * <BR>
     * ５）　@（顧客名）値セット <BR>
     * 　@this.set項目値()にて項目値をセットする。 <BR>
     * <BR>
     * 　@[set項目値()に指定する引数] <BR>
     * 　@行番号：　@引数の行番号 <BR>
     * 　@カラム：　@４）で取得したカラムモデル <BR>
     * 　@値：　@顧客.get顧客表示名() <BR>
     * @@param l_intLineNumber - (行番号)<BR>
     * 行番号を指定する。<BR>
     * @@param l_strInstitutionCode - (証券会社コード)<BR>
     * 証券会社コードを指定する。<BR>
     * @@param l_strBranchCode - (部店コード)<BR>
     * 部店コードを指定する。<BR>
     * @@param l_strAccountCode - (口座コード)<BR>
     * 口座コードを指定する。<BR>
     * @@roseuid 4104DA1D0129
     */
    public void setAccount(int l_intLineNumber, String l_strInstitutionCode, String l_strBranchCode, String l_strAccountCode) 
        throws WEB3BaseException
    {
        final String STR_METHOD_NAME = " setAccount(int , String , String , String )";
        log.entering(STR_METHOD_NAME );

        FinApp l_finApp = (FinApp) Services.getService(FinApp.class);
        WEB3GentradeAccountManager l_accountManager = 
            (WEB3GentradeAccountManager) l_finApp.getAccountManager();
            //１）　@顧客オブジェクト取得
            //③ 顧客オブジェクトを取得する。
            WEB3GentradeMainAccount l_mainAccount = (WEB3GentradeMainAccount)l_accountManager.getMainAccount(
                l_strInstitutionCode,
                l_strBranchCode, 
                l_strAccountCode);//NotFoundException

            //２）　@（顧客コード）カラムモデル取得
            WEB3GentradeCsvColumnModel l_model =
                this.getColumnModel(ACCOUNT_CODE_LABEL);
            
            //３）　@（顧客コード）値セット
            this.setValue(l_intLineNumber,
                l_model,
                l_mainAccount.getDisplayAccountCode());
            
            //４）　@（顧客名）カラムモデル取得
            l_model =
                this.getColumnModel(ACCOUNT_NAME_LABEL);
                
            //５）　@（顧客名）値セット
            this.setValue(l_intLineNumber,
                l_model,
                l_mainAccount.getDisplayAccountName());
                
        log.exiting(STR_METHOD_NAME); 
    }
    
    /**
     * (set登録区分)<BR>
     * 登録区分をセットする。<BR>
     * <BR>
     * <BR>
     * １）　@カラムモデル取得<BR>
     * 　@this.getカラムモデル()にてCSVカラムモデルを取得する。 <BR>
     * <BR>
     * 　@[getカラムモデル()に指定する引数] <BR>
     * 　@項目ラベル：　@顧客データCSV.登録区分ラベル <BR>
     * <BR>
     * ２）　@値セット <BR>
     * 　@this.set項目値()にて項目値をセットする。 <BR>
     * <BR>
     * 　@[set項目値()に指定する引数] <BR>
     * 　@行番号：　@引数の行番号 <BR>
     * 　@カラム：　@１）で取得したカラムモデル <BR>
     * 　@値：　@登録区分 <BR>
     * @@param l_intLineNumber - (行番号)<BR>
     * 行番号を指定する。<BR>
     * @@param l_strPaymentDiv - (登録区分)<BR>
     * 登録区分を指定する。<BR>
     * @@roseuid 4104DA62001F
     */
    public void setPaymentDiv(int l_intLineNumber, String l_strPaymentDiv) 
    {
        final String STR_METHOD_NAME = " setPaymentDiv(int , String )";
        log.entering(STR_METHOD_NAME );

        //１）　@カラムモデル取得<BR>
        WEB3GentradeCsvColumnModel l_model =
            this.getColumnModel(WEB3AdminSrvRegiAccountDataDownloadCsv.PAYMENT_DIV_LABEL);
        
        //２）　@値セット
        this.setValue(l_intLineNumber, l_model, l_strPaymentDiv);
        
        log.exiting(STR_METHOD_NAME); 
    }
    
    /**
     * (set申込抽選区分)<BR>
     * 申込・抽選区分をセットする。<BR>
     * 　@申込・抽選区分をセットする。<BR>
     * 　@<BR>
     * 　@１）　@カラムモデル取得 <BR>
     * 　@　@this.getカラムモデル()にてCSVカラムモデルを取得する。 <BR>
     * 　@<BR>
     * 　@　@[getカラムモデル()に指定する引数] <BR>
     * 　@　@項目ラベル：　@顧客データCSV.申込抽選区分ラベル <BR>
     * 　@<BR>
     * 　@２）申込抽選区分の判定<BR>
     * 　@　@this.set項目値()に渡す申込抽選区分を判定する。<BR>
     * 　@　@－引数.取消区分＝“取消”の場合、申込抽選区分を“取消”と判定する<BR>
     * 　@<BR>
     * 　@　@－引数.申込抽選区分＝“自動当選” && <BR>
     * 　@　@　@引数.自動当選取消期限日＞現在日付の場合、申込抽選区分を“申込”と判定する。<BR>
     * 　@<BR>
     * 　@　@－引数.申込抽選区分＝“自動当選” && <BR>
     * 　@　@　@引数.自動当選取消期限日≦現在日付の場合、申込抽選区分を“当選（本申込）”と判定する。<BR>
     * 　@<BR>
     * 　@　@－上記以外の場合、引数.申込抽選区分のままとする。<BR>
     * 　@<BR>
     * 　@３）　@値セット <BR>
     * 　@　@this.set項目値()にて項目値をセットする。 <BR>
     * 　@<BR>
     * 　@　@[set項目値()に指定する引数] <BR>
     * 　@　@行番号：　@引数の行番号 <BR>
     * 　@　@カラム：　@１）で取得したカラムモデル <BR>
     * 　@　@値：　@２）で判定した申込抽選区分<BR>
     * @@param l_intLineNumber - (行番号)<BR>
     * 行番号を指定する。<BR>
     * @@param l_strAppliLotDiv - (申込抽選区分)<BR>
     * 申込抽選区分を指定する。<BR>
     * @@param l_strCancelDiv - (取消区分)<BR>
     * 取消区分を指定する。<BR>
     * @@param l_datCancelLimitDate - (自動当選取消期限日)<BR>
     * 自動当選取消期限日を指定する。<BR>
     * @@roseuid 41064353010C
     */
    public void setAppliLotDiv(
    	int l_intLineNumber,
    	String l_strAppliLotDiv,
    	String l_strCancelDiv,
    	Date l_datCancelLimitDate) 
    {
        final String STR_METHOD_NAME = " setAppliLotDiv(int , String ) ";
        log.entering(STR_METHOD_NAME );

		String l_strSetAppliLotDiv;

        //１）　@カラムモデル取得<BR>
        WEB3GentradeCsvColumnModel l_model =
            this.getColumnModel(WEB3AdminSrvRegiAccountDataDownloadCsv.APPLI_LOT_DIV_LABEL);

        //２）申込抽選区分の判定
        // 現在日付取得
        Date l_currentTimestamp = GtlUtils.getTradingSystem().getSystemTimestamp();

		// this.set項目値()に渡す申込抽選区分を判定する。
		// －引数.取消区分＝“取消”の場合、申込抽選区分を“取消”と判定する
		if (l_strCancelDiv.equals(WEB3SrvRegiCancelDivDef.CANCEL))
		{
			l_strSetAppliLotDiv = WEB3SrvRegiAppliLotDivDef.CANCEL;
		}
		// －引数.申込抽選区分＝“自動当選” && 
		// 	 引数.自動当選取消期限日＞現在日付の場合、申込抽選区分を“申込”と判定する。
		else if((l_strAppliLotDiv.equals(WEB3SrvRegiAppliLotDivDef.AUTO_ELECTION)) &&
				(l_datCancelLimitDate.compareTo(l_currentTimestamp) > 0))
		{
			l_strSetAppliLotDiv = WEB3SrvRegiAppliLotDivDef.APPLI;
		}
		// －引数.申込抽選区分＝“自動当選” && 
		// 	 引数.自動当選取消期限日≦現在日付の場合、申込抽選区分を“当選（本申込）”と判定する。
		else if((l_strAppliLotDiv.equals(WEB3SrvRegiAppliLotDivDef.AUTO_ELECTION)) &&
				(l_datCancelLimitDate.compareTo(l_currentTimestamp) <= 0))
		{
			l_strSetAppliLotDiv = WEB3SrvRegiAppliLotDivDef.ELECTION_FORMAL_APPLI;
		}
		// －上記以外の場合、引数.申込抽選区分のままとする。
		else
		{
			l_strSetAppliLotDiv = l_strAppliLotDiv;
		}

        //３）　@値セット
        this.setValue(l_intLineNumber, l_model, l_strSetAppliLotDiv);
        
        log.exiting(STR_METHOD_NAME); 
    }
    
    /**
     * (set申込日)<BR>
     * 申込日をセットする。<BR>
     * <BR>
     * <BR>
     * １）　@カラムモデル取得<BR>
     * 　@this.getカラムモデル()にてCSVカラムモデルを取得する。 <BR>
     * <BR>
     * 　@[getカラムモデル()に指定する引数] <BR>
     * 　@項目ラベル：　@顧客データCSV.申込日ラベル <BR>
     * <BR>
     * ２）　@値セット <BR>
     * 　@this.set項目値()にて項目値をセットする。 <BR>
     * <BR>
     * 　@[set項目値()に指定する引数] <BR>
     * 　@行番号：　@引数の行番号 <BR>
     * 　@カラム：　@１）で取得したカラムモデル <BR>
     * 　@値：　@申込日 <BR>
     * @@param l_intLineNumber - (行番号)<BR>
     * 行番号を指定する。<BR>
     * @@param l_datAppliDate - (申込日)<BR>
     * 申込日を指定する。<BR>
     * @@roseuid 4104DA830252
     */
    public void setAppliDate(int l_intLineNumber, Date l_datAppliDate) 
    {
        final String STR_METHOD_NAME = " setAppliDate(int , Date ) ";
        log.entering(STR_METHOD_NAME );

        //１）　@カラムモデル取得<BR>
        WEB3GentradeCsvColumnModel l_model =
            this.getColumnModel(WEB3AdminSrvRegiAccountDataDownloadCsv.APPLI_DATE_LABEL);
        
        //２）　@値セット
        this.setValue(l_intLineNumber, l_model, l_datAppliDate);
        
        log.exiting(STR_METHOD_NAME); 
    }
    
    /**
     * (set適用開始日)<BR>
     * 適用開始日をセットする。<BR>
     * <BR>
     * <BR>
     * １）　@カラムモデル取得<BR>
     * 　@this.getカラムモデル()にてCSVカラムモデルを取得する。 <BR>
     * <BR>
     * 　@[getカラムモデル()に指定する引数] <BR>
     * 　@項目ラベル：　@顧客データCSV.適用開始日ラベル <BR>
     * <BR>
     * ２）　@値セット <BR>
     * 　@this.set項目値()にて項目値をセットする。 <BR>
     * <BR>
     * 　@[set項目値()に指定する引数] <BR>
     * 　@行番号：　@引数の行番号 <BR>
     * 　@カラム：　@１）で取得したカラムモデル <BR>
     * 　@値：　@適用開始日 <BR>
     * @@param l_intLineNumber - (行番号)<BR>
     * 行番号を指定する。<BR>
     * @@param l_datAppliStartDate - (適用開始日)<BR>
     * 適用開始日を指定する。<BR>
     * @@roseuid 4104DA94006D
     */
    public void setAppliStartDate(int l_intLineNumber, Date l_datAppliStartDate) 
    {
        final String STR_METHOD_NAME = " setAppliStartDate(int , Date )";
        log.entering(STR_METHOD_NAME );

        //１）　@カラムモデル取得<BR>
        WEB3GentradeCsvColumnModel l_model =
            this.getColumnModel(WEB3AdminSrvRegiAccountDataDownloadCsv.APPLI_START_DATE_LABEL);
        
        //２）　@値セット
        this.setValue(l_intLineNumber, l_model, l_datAppliStartDate);
        
        log.exiting(STR_METHOD_NAME); 
    }
    
    /**
     * (set適用終了日)<BR>
     * 適用終了日をセットする。<BR>
     * <BR>
     * <BR>
     * １）　@カラムモデル取得<BR>
     * 　@this.getカラムモデル()にてCSVカラムモデルを取得する。 <BR>
     * <BR>
     * 　@[getカラムモデル()に指定する引数] <BR>
     * 　@項目ラベル：　@顧客データCSV.適用終了日ラベル <BR>
     * <BR>
     * ２）　@値セット <BR>
     * 　@this.set項目値()にて項目値をセットする。 <BR>
     * <BR>
     * 　@[set項目値()に指定する引数] <BR>
     * 　@行番号：　@引数の行番号 <BR>
     * 　@カラム：　@１）で取得したカラムモデル <BR>
     * 　@値：　@適用終了日 <BR>
     * @@param l_intLineNumber - (行番号)<BR>
     * 行番号を指定する。<BR>
     * @@param l_datAppliEndDate - (適用終了日)<BR>
     * 適用終了日を指定する。<BR>
     * @@roseuid 4104DAA0001F
     */
    public void setAppliEndDate(int l_intLineNumber, Date l_datAppliEndDate) 
    {
        final String STR_METHOD_NAME = " setAppliEndDate(int , Date )";
        log.entering(STR_METHOD_NAME );
        
        //１）　@カラムモデル取得<BR>
        WEB3GentradeCsvColumnModel l_model =
            this.getColumnModel(WEB3AdminSrvRegiAccountDataDownloadCsv.APPLI_END_DATE_LABEL);
        
        //２）　@値セット
        this.setValue(l_intLineNumber, l_model, l_datAppliEndDate);
        
        log.exiting(STR_METHOD_NAME); 
    }
    
    /**
     * (set利用料金)<BR>
     * 利用料金をセットする。<BR>
     * <BR>
     * <BR>
     * １）　@カラムモデル取得<BR>
     * 　@this.getカラムモデル()にてCSVカラムモデルを取得する。 <BR>
     * <BR>
     * 　@[getカラムモデル()に指定する引数] <BR>
     * 　@項目ラベル：　@顧客データCSV.利用料金ラベル <BR>
     * <BR>
     * ２）　@値セット <BR>
     * 　@this.set項目値()にて項目値をセットする。 <BR>
     * <BR>
     * 　@[set項目値()に指定する引数] <BR>
     * 　@行番号：　@引数の行番号 <BR>
     * 　@カラム：　@１）で取得したカラムモデル <BR>
     * 　@値：　@利用料金 <BR>
     * @@param l_intLineNumber - (行番号)<BR>
     * 行番号を指定する。<BR>
     * @@param l_dblUseAmt - (利用料金)<BR>
     * 利用料金を指定する。<BR>
     * @@roseuid 4104DAAB0223
     */
	public void setUseAmt(int l_intLineNumber, double l_dblUseAmt)
    {
        final String STR_METHOD_NAME = " setUseAmt(int , double )";
        log.entering(STR_METHOD_NAME );
        
        //１）　@カラムモデル取得<BR>
        WEB3GentradeCsvColumnModel l_model =
            this.getColumnModel(WEB3AdminSrvRegiAccountDataDownloadCsv.USE_AMT_LABEL);
        
	//障害対応 NO_2116
		//２）　@値セット
		String l_strUseAmt = new String(WEB3StringTypeUtility.formatNumber(l_dblUseAmt));
        
        this.setValue(l_intLineNumber, l_model, l_strUseAmt);
        log.debug("【l_strUseAmt】 = "+l_strUseAmt);
                
        log.exiting(STR_METHOD_NAME); 
    }
    
    /**
     * (set出金余力)<BR>
     * 出金余力をセットする。<BR>
     * <BR>
     *   １-１） －顧客オブジェクトを取得する。<BR> 
     * <BR>
     *       [拡張アカウントマネージャ.get顧客の引数] <BR>
     *          証券会社コード=サービス申込登録Params[index].get証券会社コード()<BR>
     *          部店コード=サービス申込登録Params[index].get部店コード()<BR>  
     *          口座コードサービス申込登録Params[index].get口座コード()<BR>  
     * <BR>
     *   １-２） －補助口座オブジェクトの取得。<BR> 
     * <BR>
     *       [顧客オブジェクト.getSubAccountの引数]<BR> 
     *          補助口座タイプ="株式取引口座（預り金）"]<BR> 
     * <BR>
     *   １-３） －顧客データダウンロードTransactionCallbackを生成<BR> 
     *       [顧客データダウンロードTransactionCallback()に指定する引数]<BR>  
     *          補助口座： 顧客オブジェクト.getSubAccount()の戻り値<BR>  
     *          引渡日：   取引時間管理.get発注日()の戻り値<BR>
     * <BR>
     *   １-４） 顧客データダウンロードTransactionCallBackを実行<BR>  
     *       [doTransaction()に指定する引数]<BR>  
     *          arg0： QueryProcessor.TX_CREATE_NEW<BR> 
     *          arg1： 顧客データダウンロードTransactionCallBack<BR> 
     * <BR>
     * ２）　@カラムモデル取得<BR> 
     * 　@this.getカラムモデル()にてCSVカラムモデルを取得する。<BR>  
     * <BR>
     * 　@[getカラムモデル()に指定する引数]<BR>
     * 　@項目ラベル：　@顧客データCSV.出金余力ラベル<BR> 
     * <BR>
     * ３）　@値セット<BR> 
     * 　@this.set項目値()にて項目値をセットする。<BR>
     * 　@[set項目値()に指定する引数]<BR>  
     * 　@行番号：　@引数の行番号<BR>  
     * 　@カラム：　@１）で取得したカラムモデル<BR>  
     * 　@値：　@取得した出金余力<BR> 
     * @@param l_intLineNumber - (行番号)<BR>
     * 行番号を指定する。<BR>
     * @@param l_strInstitutionCode - (証券会社コード)<BR>
     * 証券会社コードを指定する。<BR>
     * @@param l_strBranchCode - (部店コード)<BR>
     * 部店コードを指定する。<BR>
     * @@param l_strAccountCode - (口座コード)<BR>
     * 口座コードを指定する。<BR>
     * @@throws WEB3BaseException
     * @@roseuid 4104DAB7005E
     */
    public void setPaymentPower(
        int l_intLineNumber, 
        String l_strInstitutionCode, 
        String l_strBranchCode, 
        String l_strAccountCode)
        throws WEB3BaseException 
    {
        final String STR_METHOD_NAME = " setPaymentPower(int , String , String , String )";
        log.entering(STR_METHOD_NAME );
        
        try
        {
            Object l_obj = null;

            //１）　@出金余力を取得する
            FinApp l_finApp = (FinApp) Services.getService(FinApp.class);
            WEB3GentradeAccountManager l_accountManager = 
                (WEB3GentradeAccountManager) l_finApp.getAccountManager();
            //－拡張アカウントマネージャ.get顧客( )から顧客オブジェクトを取得。
            WEB3GentradeMainAccount l_mainAccount = l_accountManager.getMainAccount(
                l_strInstitutionCode,
                l_strBranchCode, 
                l_strAccountCode);//WEB3SystemLayerException
            SubAccount l_subAccount =
                l_mainAccount.getSubAccount(SubAccountTypeEnum.EQUITY_SUB_ACCOUNT);//NotFoundException

            Date l_bizDate = WEB3SrvRegiTradingTimeManagement.getOrderBizDate();

            try
            {
                //DefaultProcessorを取得する。
                QueryProcessor l_qp = Processors.getDefaultProcessor();

                //顧客データダウンロードTransactionCallbackクラスを生成する。
                WEB3AdminSrvRegiAccountDataTransactionCallback l_callBack =
                    new WEB3AdminSrvRegiAccountDataTransactionCallback(l_subAccount, l_bizDate);

                //顧客データダウンロードTransactionCallBackを実行する。
                //[doTransaction()に指定する引数]
                //arg0： QueryProcessor.TX_CREATE_NEW
                //arg1： 顧客データダウンロードTransactionCallBack
                l_obj = l_qp.doTransaction(QueryProcessor.TX_CREATE_NEW, l_callBack);
            }
            catch (DataException l_ex)
            {
                log.error(STR_METHOD_NAME, l_ex);
                throw new WEB3SystemLayerException(
                    WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                    this.getClass().getName() + "." + STR_METHOD_NAME,
                    l_ex.getMessage(),
                    l_ex);
            }

            WEB3GentradeCsvColumnModel l_model =
                this.getColumnModel(WEB3AdminSrvRegiAccountDataDownloadCsv.PAYMENT_DATE_LABEL);

            Double l_dblObj = (Double)l_obj;
            String l_strPaymentPower = WEB3StringTypeUtility.formatNumber(l_dblObj.doubleValue());

            log.debug("【l_strPaymentPower】 = " + l_strPaymentPower);

            //2）　@カラムモデル取得<BR>
            l_model =
                this.getColumnModel(WEB3AdminSrvRegiAccountDataDownloadCsv.PAYMENT_POWER_LABEL);

            //3）　@値セット
            this.setValue(l_intLineNumber, l_model, l_strPaymentPower);
        }
        catch (NotFoundException l_e)
        {
            WEB3BaseException l_webe = 
                new WEB3SystemLayerException(
                    WEB3ErrorCatalog.BUSINESS_ERROR_01026,
                    this.getClass().getName() + STR_METHOD_NAME,
                    l_e.getMessage(),
                    l_e);
            log.error("登録顧客エラー.", l_e);
            log.exiting(STR_METHOD_NAME);
            throw l_webe;
        }
        
        log.exiting(STR_METHOD_NAME); 
    }
    
    /**
     * (set出金日)<BR>
     * 出金日をセットする。<BR>
     * <BR>
     * <BR>
     * １）　@カラムモデル取得<BR>
     * 　@this.getカラムモデル()にてCSVカラムモデルを取得する。 <BR>
     * <BR>
     * 　@[getカラムモデル()に指定する引数] <BR>
     * 　@項目ラベル：　@顧客データCSV.出金日ラベル <BR>
     * <BR>
     * ２）　@値セット <BR>
     * 　@this.set項目値()にて項目値をセットする。 <BR>
     * <BR>
     * 　@[set項目値()に指定する引数] <BR>
     * 　@行番号：　@引数の行番号 <BR>
     * 　@カラム：　@１）で取得したカラムモデル <BR>
     * 　@値：　@出金日 <BR>
     * @@param l_intLineNumber - (行番号)<BR>
     * 行番号を指定する。<BR>
     * @@param l_datPaymentDate - (出金日)<BR>
     * 出金日を指定する。<BR>
     * @@roseuid 4104DACB02BF
     */
    public void setPaymentDate(int l_intLineNumber, Date l_datPaymentDate) 
    {
        final String STR_METHOD_NAME = " setPaymentDate(int , Date )";
        log.entering(STR_METHOD_NAME );
        
        //１）　@カラムモデル取得<BR>
        WEB3GentradeCsvColumnModel l_model =
            this.getColumnModel(WEB3AdminSrvRegiAccountDataDownloadCsv.PAYMENT_DATE_LABEL);

        //２）　@値セット <BR>
        this.setValue(l_intLineNumber, l_model, l_datPaymentDate);
        
        log.exiting(STR_METHOD_NAME); 
    }
    
    /**
     * (顧客データダウンロードTransactionCallback)<BR>
     * 顧客データダウンロードTransactionCallback<BR>
     */
    private class WEB3AdminSrvRegiAccountDataTransactionCallback
        implements TransactionCallback
    {
        /**
         * 補助口座<BR>
         * (補助口座オブジェクト)<BR>
         */
        private SubAccount subAccount;

        /**
         * 受渡日<BR>
         * (受渡日)<BR>
         */
        private Date deliveryDate;

        /**
         * (顧客データダウンロードTransactionCallback)<BR>
         * <BR>
         * コンストラクタ。<BR>
         * <BR>
         * this.補助口座 = （引数）補助口座 <BR>
         * this.受渡日 = （引数）受渡日<BR>
         * @@param l_subAccount - (補助口座)<BR>
         * 補助口座オブジェクト<BR>
         * @@param l_datDeliveryDate - (受渡日)<BR>
         * 受渡日<BR>
         */
        public WEB3AdminSrvRegiAccountDataTransactionCallback(
            SubAccount l_subAccount,
            Date l_datDeliveryDate)
        {
            this.subAccount = l_subAccount;
            this.deliveryDate = l_datDeliveryDate;
        }

        /**
         * トランザクション処理を実施する。<BR>
         * <BR>
         * 取引余力サービスImpl.get出金可能額()をコールし、出金余力を取得する。<BR>
         * <BR>
         * １） 取引余力サービスImpl.get出金可能額～0補正有り～(補助口座,受渡日)をコールする。<BR>
         * <BR>
         *     [get出金可能額～補正有り～()に指定する引数]<BR>
         *           補助口座：this.補助口座<BR>
         *           受渡日：this.受渡日<BR>
         * <BR>
         * ２） １）の戻り値をObjectに変換し返却する。<BR>
         * @@throws DataNetworkException, DataQueryException, DataCallbackException
         */
        public Object process()
            throws DataNetworkException, DataQueryException, DataCallbackException
        {
            final String STR_METHOD_NAME = "process()";
            log.entering(STR_METHOD_NAME);

            double l_dblPaymentTradingPower = 0D;

            //１） 取引余力サービスImpl.get出金可能額～0補正有り～(補助口座,受渡日)をコールする。
            //    [get出金可能額～補正有り～()に指定する引数]
            //      補助口座：this.補助口座
            //      受渡日：this.受渡日
            WEB3TPTradingPowerService l_tradingPowerService =
                (WEB3TPTradingPowerService)Services.getService(WEB3TPTradingPowerService.class);

            try
            {
                l_dblPaymentTradingPower =
                    l_tradingPowerService.getPaymentTradingPower(
                        (WEB3GentradeSubAccount)this.subAccount,
                        this.deliveryDate);
            }
            catch (WEB3BaseException l_ex)
            {
                log.debug(STR_METHOD_NAME, l_ex);
                log.exiting(STR_METHOD_NAME);
                throw new DataCallbackException(
                    l_ex.getErrorMessage(),
                    l_ex.getErrorInfo());
            }

            //２） １）の戻り値をObjectに変換し返却する。
            Double l_paymentTradingPower = new Double(l_dblPaymentTradingPower);

            log.exiting(STR_METHOD_NAME);
            return l_paymentTradingPower;
        }
    }
}
@
