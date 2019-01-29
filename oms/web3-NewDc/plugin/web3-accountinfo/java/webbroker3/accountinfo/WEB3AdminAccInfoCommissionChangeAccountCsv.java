head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.10.02.24.57;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	8644d782f8a12fc;
filename	WEB3AdminAccInfoCommissionChangeAccountCsv.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : 手数料変更顧客CSV (WEB3AdminAccInfoCommissionChangeAccountCsv.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2004/11/10 彭巍 (中訊) 新規作成
*/

package webbroker3.accountinfo;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

import com.fitechlabs.xtrade.plugin.tc.gentrade.GtlUtils;

import webbroker3.gentrade.WEB3GentradeCsvColumnModel;
import webbroker3.gentrade.WEB3GentradeCsvDataModel;
import webbroker3.util.WEB3DateUtility;
import webbroker3.util.WEB3LogUtility;


/**
 * (手数料変更顧客CSV)<BR>
 * 手数料変更顧客ダウンロードで作成するCSVファ@イルデータクラス <BR>
 * 
 * 
 */
public class WEB3AdminAccInfoCommissionChangeAccountCsv extends WEB3GentradeCsvDataModel 
{
    
    /**
     * ログ出力ユーティリティ。
     */
    private static WEB3LogUtility log =
        WEB3LogUtility.getInstance(WEB3AdminAccInfoCommissionChangeAccountCsv.class); 
    
    /**
     * (部店コードラベル)<BR>
     * 定数定義プロパティ　@”部店コード” <BR>
     */
    public static  final String BRANCH_CODE_LABEL = "部店コード";
    
    /**
     * (顧客コードラベル)<BR>
     * 定数定義プロパティ　@”顧客コード” <BR>
     */
    public static  final String ACCOUNT_CODE_LABEL = "顧客コード";
    
    /**
     * (商品コードラベル)<BR>
     * 定数定義プロパティ　@”商品コード” <BR>
     */
    public static  final String PRODUCT_CODE_LABEL = "商品コード";
    
    /**
     * (適用開始日ラベル)<BR>
     * 定数定義プロパティ　@”適用開始日” <BR>
     */
    public static  final String APPLI_START_DATE_LABEL = "適用開始日";
    
    /**
     * (手数料No.ラベル)<BR>
     * 定数定義プロパティ　@”手数料No.” <BR>
     */
    public static  final String COMMISSION_NO_LABEL = "手数料No.";
    
    /**
     * (徴収率ラベル)<BR>
     * 定数定義プロパティ　@”徴収率” <BR>
     */
    public static  final String CHARGE_RATIO_LABEL = "徴収率";
    
    /**
     * (適用終了日ラベル)<BR>
     * 定数定義プロパティ　@”適用終了日” <BR>
     */
    public static  final String APPLI_END_DATE_LABEL = "適用終了日";
    
    /**
     * (商品コード_上場株式)<BR>
     * 定数定義プロパティ　@商品コード_上場株式<BR>
     */
    public static  final String PRODUCT_CODE_LISTING_STOCK = "10";
    
    /**
     * (商品コード_店頭株式)<BR>
     * 定数定義プロパティ　@商品コード_店頭株式<BR>
     */
    public static  final String PRODUCT_CODE_OTC_STOCK = "11";
    
    /**
     * (手数料変更顧客CSV)<BR>
     * コンストラクタ。 <BR>
     * <BR>
     * インスタンスを生成し、ヘッダ情報をセットする。 <BR>
     * 　@－super()にてインスタンスを生成する。 <BR>
     * 　@－createキーヘッダ()をコールし、キーヘッダ情報を作成する。 <BR>
     * 　@－createカラムヘッダ()をコールし、カラムヘッダ情報を作成する。 <BR>
     * @@return webbroker3.accountinfo.WEB3AdminAccInfoCommissionChangeAccountCsv
     * @@roseuid 4146CC7803BB
     */
    public WEB3AdminAccInfoCommissionChangeAccountCsv() 
    {
        super();
        this.createColumnHeader();
        this.createKeyHeader();
    }
    
    /**
     * (createカラムヘッダ)<BR>
     * 　@以下の通りCSVカラムモデルの配列を生成し、setカラムヘッダ()にて<BR>
     * インスタンスにセットする。 <BR>
     * <BR>
     * [カラムヘッダ配列] <BR>
     * <BR>
     * －　@index = 0 <BR>
     * 　@[CSVカラムモデル コンストラクタの引数] <BR>
     * 　@項目ラベル：　@手数料変更顧客CSV.部店コードラベル <BR>
     * 　@カラム番号： 0 <BR>
     * 　@項目型：　@CSVカラムモデル.項目型_文字列 <BR>
     * 　@日付フォーマット：　@null <BR>
     * <BR>
     * －　@index = 1 <BR>
     * 　@[CSVカラムモデル コンストラクタの引数] <BR>
     * 　@項目ラベル：　@手数料変更顧客CSV.顧客コードラベル <BR>
     * 　@カラム番号： 1 <BR>
     * 　@項目型：　@CSVカラムモデル.項目型_文字列 <BR>
     * 　@日付フォーマット：　@null <BR>
     * <BR>
     * －　@index = 2 <BR>
     * 　@[CSVカラムモデル コンストラクタの引数] <BR>
     * 　@項目ラベル：　@手数料変更顧客CSV.商品コードラベル <BR>
     * 　@カラム番号： 2 <BR>
     * 　@項目型：　@CSVカラムモデル.項目型_文字列 <BR>
     * 　@日付フォーマット：　@null <BR>
     * <BR>
     * －　@index = 3 <BR>
     * 　@[CSVカラムモデル コンストラクタの引数] <BR>
     * 　@項目ラベル：　@手数料変更顧客CSV.適用開始日ラベル <BR>
     * 　@カラム番号： 3 <BR>
     * 　@項目型：　@CSVカラムモデル.項目型_日付<BR>
     * 　@日付フォーマット：　@ <BR>
     * 　@　@new SimpleDateFormat("yyyyMMdd") <BR>
     * <BR>
     * －　@index = 4<BR>
     * 　@[CSVカラムモデル コンストラクタの引数] <BR>
     * 　@項目ラベル：　@手数料変更顧客CSV.手数料No.ラベル <BR>
     * 　@カラム番号： 4 <BR>
     * 　@項目型：　@CSVカラムモデル.項目型_文字列 <BR>
     * 　@日付フォーマット：　@null <BR>
     * <BR>
     * －　@index = 5<BR>
     * 　@[CSVカラムモデル コンストラクタの引数] <BR>
     * 　@項目ラベル：　@手数料変更顧客CSV.徴収率ラベル <BR>
     * 　@カラム番号：5<BR>
     * 　@項目型：　@CSVカラムモデル.項目型_文字列 <BR>
     * 　@日付フォーマット：　@null <BR>
     * <BR>
     * －　@index = 6<BR>
     * 　@[CSVカラムモデル コンストラクタの引数] <BR>
     * 　@項目ラベル：　@手数料変更顧客CSV.適用終了日ラベル <BR>
     * 　@カラム番号： 6 <BR>
     * 　@項目型：　@CSVカラムモデル.項目型_文字列<BR>
     * 　@日付フォーマット：　@ null<BR>
     * @@roseuid 4146CB420283
     */
    protected void createColumnHeader() 
    {
        final String STR_METHOD_NAME = " createColumnHeader()";
        log.entering(STR_METHOD_NAME);
        
        WEB3GentradeCsvColumnModel[] l_columnHeader = new WEB3GentradeCsvColumnModel[7];
        
        //部店コード        
        l_columnHeader[0] = new WEB3GentradeCsvColumnModel(BRANCH_CODE_LABEL, 0, WEB3GentradeCsvColumnModel.STRINGTYPE, null);
        
        //顧客コード        
        l_columnHeader[1] = new WEB3GentradeCsvColumnModel(ACCOUNT_CODE_LABEL, 1, WEB3GentradeCsvColumnModel.STRINGTYPE, null);
        
        //商品コード
        l_columnHeader[2] = new WEB3GentradeCsvColumnModel(PRODUCT_CODE_LABEL, 2, WEB3GentradeCsvColumnModel.STRINGTYPE, null);
        
        //適用開始日
        l_columnHeader[3] = new WEB3GentradeCsvColumnModel(APPLI_START_DATE_LABEL, 3, WEB3GentradeCsvColumnModel.DATETYPE, new SimpleDateFormat("yyyyMMdd"));
        
        //手数料No.
        l_columnHeader[4] = new WEB3GentradeCsvColumnModel(COMMISSION_NO_LABEL, 4, WEB3GentradeCsvColumnModel.STRINGTYPE, null);
        
        //徴収率
        l_columnHeader[5] = new WEB3GentradeCsvColumnModel(CHARGE_RATIO_LABEL, 5, WEB3GentradeCsvColumnModel.STRINGTYPE, null);
        
        //適用終了日
        l_columnHeader[6] = new WEB3GentradeCsvColumnModel(APPLI_END_DATE_LABEL, 6, WEB3GentradeCsvColumnModel.STRINGTYPE, null);
        
        setColumnHeader(l_columnHeader);
        
        log.exiting(STR_METHOD_NAME);
     
    }
    
    /**
     * (createキーヘッダ)<BR>
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
     * TradingSystem.getSystemTimestamp()<BR>
     * @@roseuid 4146CB48013B
     */
    protected void createKeyHeader() 
    {
        final String STR_METHOD_NAME = " createKeyHeader()";
        log.entering(STR_METHOD_NAME);
        
        String[] l_strKeyHeader = new String[1];
        
        l_strKeyHeader[0] = WEB3DateUtility.formatDate(GtlUtils.getSystemTimestamp(), "yyyy/MM/dd HH:mm:ss");
        
        setKeyHeader(l_strKeyHeader);
        
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
     * 　@項目ラベル：　@手数料変更顧客CSV.部店コードラベル <BR>
     * <BR>
     * ２）　@値セット <BR>
     * 　@this.set項目値()にて項目値をセットする。 <BR>
     * <BR>
     * 　@[set項目値()に指定する引数] <BR>
     * 　@行番号：　@引数の行番号 <BR>
     * 　@カラム：　@１）で取得したカラムモデル <BR>
     * 　@値：　@部店コード<BR>
     * @@param l_intLineNumber - 行番号
     * @@param l_strBranchCode - 部店コード
     * @@roseuid 4146CCB40216
     */
    public void setBranchCode(int l_intLineNumber, String l_strBranchCode) 
    {
        final String STR_METHOD_NAME = " setBranchCode(int, String)";
        log.entering(STR_METHOD_NAME);

        //カラムモデル取得
        WEB3GentradeCsvColumnModel l_columnModel = getColumnModel(BRANCH_CODE_LABEL);
        
        //値セット
        setValue(l_intLineNumber, l_columnModel, l_strBranchCode);
      
        log.exiting(STR_METHOD_NAME);     
    }
    
    
    /**
     * (set顧客コード)<BR>
     * 顧客コードをセットする。 <BR>
     * <BR>
     * １）　@カラムモデル取得 <BR>
     * 　@this.getカラムモデル()にてCSVカラムモデルを取得する。 <BR>
     * <BR>
     * 　@[getカラムモデル()に指定する引数] <BR>
     * 　@項目ラベル：　@手数料変更顧客CSV.顧客コードラベル <BR>
     * <BR>
     * ２）　@値セット <BR>
     * 　@this.set項目値()にて項目値をセットする。 <BR>
     * <BR>
     * 　@[set項目値()に指定する引数] <BR>
     * 　@行番号：　@引数の行番号 <BR>
     * 　@カラム：　@１）で取得したカラムモデル <BR>
     * 　@値：　@顧客コード<BR>
     * @@param l_intLineNumber - 行番号
     * @@param l_strAccountCode - 顧客コード
     * @@roseuid 4146CD17036D
     */
    public void setAccountCode(int l_intLineNumber, String l_strAccountCode) 
    {
        final String STR_METHOD_NAME = " setAccountCode(int, String)";
        log.entering(STR_METHOD_NAME);

        //カラムモデル取得
        WEB3GentradeCsvColumnModel l_columnModel = getColumnModel(ACCOUNT_CODE_LABEL);
        
        //値セット
        setValue(l_intLineNumber, l_columnModel, l_strAccountCode);
      
        log.exiting(STR_METHOD_NAME);     
    }
    
    /**
     * (set商品コード)<BR>
     * 商品コードをセットする。 <BR>
     * <BR>
     * １）　@カラムモデル取得 <BR>
     * 　@this.getカラムモデル()にてCSVカラムモデルを取得する。 <BR>
     * <BR>
     * 　@[getカラムモデル()に指定する引数] <BR>
     * 　@項目ラベル：　@手数料変更顧客CSV.商品コードラベル <BR>
     * <BR>
     * ２）　@値セット <BR>
     * 　@this.set項目値()にて項目値をセットする。 <BR>
     * <BR>
     * 　@[set項目値()に指定する引数] <BR>
     * 　@行番号：　@引数の行番号 <BR>
     * 　@カラム：　@１）で取得したカラムモデル <BR>
     * 　@値：　@商品コード<BR>
     * @@param l_intLineNumber - 行番号
     * @@param l_strProductCode - 商品コード
     * @@roseuid 4146CDAA0216
     */
    public void setProductCode(int l_intLineNumber, String l_strProductCode) 
    {
        final String STR_METHOD_NAME = " setAccountCode(int, String)";
        log.entering(STR_METHOD_NAME);

        //カラムモデル取得
        WEB3GentradeCsvColumnModel l_columnModel = getColumnModel(PRODUCT_CODE_LABEL);
        
        //値セット
        setValue(l_intLineNumber, l_columnModel, l_strProductCode);
      
        log.exiting(STR_METHOD_NAME);     
    }
    
    /**
     * (set適用開始日)<BR>
     * 適用開始日をセットする。 <BR>
     * <BR>
     * １）　@カラムモデル取得 <BR>
     * 　@this.getカラムモデル()にてCSVカラムモデルを取得する。 <BR>
     * <BR>
     * 　@[getカラムモデル()に指定する引数] <BR>
     * 　@項目ラベル：　@手数料変更顧客CSV.適用開始日ラベル <BR>
     * <BR>
     * ２）　@値セット <BR>
     * 　@this.set項目値()にて項目値をセットする。 <BR>
     * <BR>
     * 　@[set項目値()に指定する引数] <BR>
     * 　@行番号：　@引数の行番号 <BR>
     * 　@カラム：　@１）で取得したカラムモデル <BR>
     * 　@値：　@適用開始日<BR>
     * @@param l_intLineNumber - 行番号
     * @@param l_datAppliStartDate - 適用開始日
     * @@roseuid 4146CD4601F6
     */
    public void setAppliStartDate(int l_intLineNumber, Date l_datAppliStartDate) 
    {
        final String STR_METHOD_NAME = " setAccountCode(int, Date)";
        log.entering(STR_METHOD_NAME);

        //カラムモデル取得
        WEB3GentradeCsvColumnModel l_columnModel = getColumnModel(APPLI_START_DATE_LABEL);
        
        //値セット
        setValue(l_intLineNumber, l_columnModel, l_datAppliStartDate);
      
        log.exiting(STR_METHOD_NAME);     
    }
     
    
    /**
     * (set手数料No.)<BR>
     * 手数料No.をセットする。 <BR>
     * <BR>
     * １）　@カラムモデル取得 <BR>
     * 　@this.getカラムモデル()にてCSVカラムモデルを取得する。 <BR>
     * <BR>
     * 　@[getカラムモデル()に指定する引数] <BR>
     * 　@項目ラベル：　@手数料変更顧客CSV.手数料No.ラベル <BR>
     * <BR>
     * ２）　@値セット <BR>
     * 　@this.set項目値()にて項目値をセットする。 <BR>
     * <BR>
     * 　@[set項目値()に指定する引数] <BR>
     * 　@行番号：　@引数の行番号 <BR>
     * 　@カラム：　@１）で取得したカラムモデル <BR>
     * 　@値：　@手数料No.<BR>
     * @@param l_intLineNumber - 行番号
     * @@param l_strCommissionNo - 手数料No.
     * @@roseuid 4146CDD5012B
     */
    public void setCommissionNo(int l_intLineNumber, String l_strCommissionNo) 
    {
        final String STR_METHOD_NAME = " setAccountCode(int, String)";
        log.entering(STR_METHOD_NAME);

        //カラムモデル取得
        WEB3GentradeCsvColumnModel l_columnModel = getColumnModel(COMMISSION_NO_LABEL);
        
        //値セット
        setValue(l_intLineNumber, l_columnModel, l_strCommissionNo);
      
        log.exiting(STR_METHOD_NAME);     
    }
    
    /**
     * (set徴収率)<BR>
     * 徴収率をセットする。 <BR>
     * <BR>
     * １）　@カラムモデル取得 <BR>
     * 　@this.getカラムモデル()にてCSVカラムモデルを取得する。 <BR>
     * <BR>
     * 　@[getカラムモデル()に指定する引数] <BR>
     * 　@項目ラベル：　@手数料変更顧客CSV.徴収率ラベル <BR>
     * <BR>
     * ２）　@値セット <BR>
     * 　@this.set項目値()にて項目値をセットする。 <BR>
     * <BR>
     * 　@[set項目値()に指定する引数] <BR>
     * 　@行番号：　@引数の行番号 <BR>
     * 　@カラム：　@１）で取得したカラムモデル <BR>
     * 　@値：　@徴収率<BR>
     * @@param l_intLineNumber - 行番号
     * @@param l_strChargeRatio - 徴収率
     * @@roseuid 4146CDF00310
     */
    public void setChargeRatio(int l_intLineNumber, String l_strChargeRatio) 
    {
        final String STR_METHOD_NAME = " setAccountCode(int, String)";
        log.entering(STR_METHOD_NAME);

        //カラムモデル取得
        WEB3GentradeCsvColumnModel l_columnModel = getColumnModel(CHARGE_RATIO_LABEL);
        
        //値セット
        setValue(l_intLineNumber, l_columnModel, l_strChargeRatio);
      
        log.exiting(STR_METHOD_NAME);     
    }
    
    /**
     * (set適用終了日)<BR>
     * 適用終了日をセットする。 <BR>
     * <BR>
     * １）　@カラムモデル取得 <BR>
     * 　@this.getカラムモデル()にてCSVカラムモデルを取得する。 <BR>
     * <BR>
     * 　@[getカラムモデル()に指定する引数] <BR>
     * 　@項目ラベル：　@手数料変更顧客CSV.適用終了日ラベル <BR>
     * <BR>
     * ２）　@値セット <BR>
     * 　@this.set項目値()にて項目値をセットする。 <BR>
     * <BR>
     * 　@[set項目値()に指定する引数] <BR>
     * 　@行番号：　@引数の行番号 <BR>
     * 　@カラム：　@１）で取得したカラムモデル <BR>
     * 　@値：　@<BR>
     * 　@　@適用終了日を"yyyyMMdd"にフォーマットしたもの。<BR>
     * <BR>
     * 　@　@但し、適用年月日 == 日付最大値（HighValue：9999/12/31 00：00：00）<BR>
     * の場合は、<BR>
     * 　@　@”99999999”をセットする。<BR>
     * @@param l_intLineNumber - 行番号
     * @@param l_datAppliEndDate - 適用終了日
     * @@roseuid 4146CD7003BB
     */
    public void setAppliEndDate(int l_intLineNumber, Date l_datAppliEndDate) 
    {
        final String STR_METHOD_NAME = " setAccountCode(int, String)";
        log.entering(STR_METHOD_NAME);

        //カラムモデル取得
        WEB3GentradeCsvColumnModel l_columnModel = getColumnModel(APPLI_END_DATE_LABEL);
        
        //値セット
        Calendar l_calendar = new GregorianCalendar(9999,Calendar.DECEMBER, 31);
        Date l_datAppliEndDates = l_calendar.getTime();
     
        if (WEB3DateUtility.compareToDay(l_datAppliEndDate, l_datAppliEndDates) == 0)
        {
            setValue(l_intLineNumber, l_columnModel, WEB3DateUtility.formatDate(l_datAppliEndDate, "99999999"));
        
        }
        else
        {      
            setValue(l_intLineNumber, l_columnModel, WEB3DateUtility.formatDate(l_datAppliEndDate, "yyyyMMdd")); 
                      
        }  
        log.exiting(STR_METHOD_NAME);
    }
}
@
