head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.16.03.46.04;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	6004d80209d0226;
filename	WEB3FeqExecuteResultInputCSV.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : 外国株式約定結果一括入力CSV(WEB3FeqExecuteResultInputCSV.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2005/07/15 鄭海良(中訊) 新規作成
Revesion History : 2009/08/03 車進(中訊) 仕様変更・モデル514
*/

package webbroker3.feq;

import java.text.SimpleDateFormat;
import java.util.Date;

import com.fitechlabs.xtrade.kernel.boot.Services;
import com.fitechlabs.xtrade.plugin.tc.gentrade.FinApp;
import com.fitechlabs.xtrade.plugin.tc.gentrade.GtlUtils;
import com.fitechlabs.xtrade.plugin.tc.gentrade.OrderExecution;
import com.fitechlabs.xtrade.plugin.tc.gentrade.OrderTypeEnum;
import com.fitechlabs.xtrade.plugin.tc.gentrade.ProductTypeEnum;
import com.fitechlabs.xtrade.plugin.tc.gentrade.TradingModule;
import com.fitechlabs.xtrade.plugin.tc.gentrade.TradingSystem;
import com.fitechlabs.xtrade.plugin.tc.xbfeq.FeqProduct;
import com.fitechlabs.xtrade.plugin.tc.xbfeq.data.FeqProductRow;
import com.fitechlabs.xtrade.plugin.tc.xbfeq.stdimpls.FeqOrderUnitImpl;

import webbroker3.common.WEB3BaseException;
import webbroker3.common.WEB3BusinessLayerException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.common.WEB3SystemLayerException;
import webbroker3.gentrade.WEB3GentradeCsvColumnModel;
import webbroker3.gentrade.WEB3GentradeCsvUploadDataModel;
import webbroker3.util.WEB3DateUtility;
import webbroker3.util.WEB3LogUtility;
import webbroker3.util.WEB3StringTypeUtility;

/**
 * (外国株式約定結果一括入力CSV)<BR>
 * 外国株式約定結果一括入力CSV<BR>
 *
 * @@author 鄭海良
 * @@version 1.0
 */
public class WEB3FeqExecuteResultInputCSV extends WEB3GentradeCsvUploadDataModel 
{
    
    /**
     * ログ出力ユーティリティ。
     */
    private static WEB3LogUtility log =
            WEB3LogUtility.getInstance(WEB3FeqExecuteResultInputCSV.class);
    
    /**
     * (運用コードラベル)<BR>
     * 運用コードラベル<BR>
     */
    private static  String ORDER_EMP_CODE_LABEL = "運用コード";
    
    /**
     * (銘柄コードラベル)<BR>
     * 銘柄コードラベル<BR>
     */
    private static  String PRODUCT_CODE_LABEL = "銘柄コード";
    
    /**
     * (売買ラベル)<BR>
     * 売買ラベル<BR>
     */
    private static  String TRADE_LABEL = "売買";
    
    /**
     * (約定株数ラベル)<BR>
     * 約定株数ラベル<BR>
     */
    private static  String EXEC_QUANTITY_LABEL = "約定株数";
    
    /**
     * (約定単価ラベル)<BR>
     * 約定単価ラベル<BR>
     */
    private static  String EXEC_PRICE_LABEL = "約定単価";
    
    /**
     * (約定No.ラベル)<BR>
     * 約定No.ラベル<BR>
     */
    private static  String EXEC_NO_LABEL = "約定No.";
    
    /**
     * (FILLERラベル)<BR>
     * FILLERラベル<BR>
     */
    private static  String FILLER_LABEL = "FILLER";
    
    /**
     * (約定日時ラベル)<BR>
     * 約定日時ラベル<BR>
     */
    private static  String EXEC_TIMESTAMP_LABEL = "約定日時";
    
    /**
     * (売買_売)<BR>
     * 売買_売<BR>
     */
    private static  String TRADE_SELL = "S";
    
    /**
     * (売買_買)<BR>
     * 売買_買<BR>
     */
    private static  String TRADE_BUY = "B";
    
    /**
     * (アップロードファ@イルＩＤ)<BR>
     * アップロードファ@イルＩＤ<BR>
     */
    private String uploadFileId = "外国株式約定結果一括入力";
    
    /**
     * (外国株式約定結果一括入力CSV)<BR>
     * コンストラクタ <BR>
     * ※　@アップロード中止の場合に使用する。 <BR>
     * <BR>
     * －引数のアップロードIDをプロパティにセットする。 <BR>
     * @@param l_lngUploadId - (アップロードID)<BR>
     * アップロードID<BR>
     * @@roseuid 429D5FD7033B
     */
    public WEB3FeqExecuteResultInputCSV(long l_lngUploadId) 
    {
        this.administratorUploadId = l_lngUploadId;
    }
    
    /**
     * (外国株式約定結果一括入力CSV)<BR>
     * コンストラクタ <BR>
     * <BR>
     * －this.createカラムヘッダ()をコールする。 <BR>
     * @@roseuid 429D5FD5036A
     */
    public WEB3FeqExecuteResultInputCSV() 
    {
        this.createColumnHeader();     
    }
    
    /**
     * (createカラムヘッダ ())<BR>
     * カラムヘッダをセットする。 <BR>
     * <BR>
     * 　@以下の通りCSVカラムモデルの配列を生成し、setカラムヘッダ()<BR>
     * にてインスタンスにセットする。 <BR>
     * <BR>
     * [カラムヘッダ配列] <BR>
     * <BR>
     * －　@index = 0　@※運用コード <BR>
     * 　@[CSVカラムモデル コンストラクタの引数] <BR>
     * 　@項目ラベル：　@外国株式約定結果一括入力CSV.運用コードラベル <BR>
     * 　@カラム番号： 0 <BR>
     * 　@項目型：　@CSVカラムモデル.項目型_文字列 <BR>
     * 　@日付フォーマット：　@null <BR>
     * <BR>
     * －　@index = 1　@※銘柄コード <BR>
     * 　@[CSVカラムモデル コンストラクタの引数] <BR>
     * 　@項目ラベル：　@外国株式約定結果一括入力CSV.銘柄コードラベル <BR>
     * 　@カラム番号： 1 <BR>
     * 　@項目型：　@CSVカラムモデル.項目型_文字列 <BR>
     * 　@日付フォーマット：　@null <BR>
     * <BR>
     * －　@index = 2　@※売買 <BR>
     * 　@[CSVカラムモデル コンストラクタの引数] <BR>
     * 　@項目ラベル：　@外国株式約定結果一括入力CSV.売買ラベル <BR>
     * 　@カラム番号： 2 <BR>
     * 　@項目型：　@CSVカラムモデル.項目型_文字列 <BR>
     * 　@日付フォーマット：　@null <BR>
     * <BR>
     * －　@index = 3　@※約定株数<BR>
     * 　@[CSVカラムモデル コンストラクタの引数] <BR>
     * 　@項目ラベル：　@外国株式約定結果一括入力CSV.約定株数ラベル <BR>
     * 　@カラム番号： 3<BR>
     * 　@項目型：　@CSVカラムモデル.項目型_数値（double） <BR>
     * 　@日付フォーマット：　@null <BR>
     * <BR>
     * －　@index = 4　@※約定単価<BR>
     * 　@[CSVカラムモデル コンストラクタの引数] <BR>
     * 　@項目ラベル：　@外国株式約定結果一括入力CSV.約定単価ラベル <BR>
     * 　@カラム番号： 4<BR>
     * 　@項目型：　@CSVカラムモデル.項目型_数値（double） <BR>
     * 　@日付フォーマット：　@null <BR>
     * <BR>
     * －　@index = 5　@※約定No.<BR>
     * 　@[CSVカラムモデル コンストラクタの引数] <BR>
     * 　@項目ラベル：　@外国株式約定結果一括入力CSV.約定No.ラベル <BR>
     * 　@カラム番号： 5 <BR>
     * 　@項目型：　@CSVカラムモデル.項目型_数値（int） <BR>
     * 　@日付フォーマット：　@null <BR>
     * －　@index = 6　@※FILLER<BR>
     * 　@[CSVカラムモデル コンストラクタの引数]<BR>
     * 　@項目ラベル：　@外国株式約定結果一括入力CSV.FILLERラベル<BR>
     * 　@カラム番号： 6<BR>
     * 　@項目型：　@CSVカラムモデル.項目型_文字列<BR>
     * 付フォーマット：　@null<BR>
     * <BR>
     * －　@index = 7　@※約定日時<BR>
     * 　@[CSVカラムモデル コンストラクタの引数]<BR>
     * 　@項目ラベル：　@外国株式約定結果一括入力CSV.約定日時ラベル<BR>
     * 　@カラム番号： 7<BR>
     * 　@項目型：　@CSVカラムモデル.項目型_日付<BR>
     * 　@日付フォーマット：　@yyyyMMddHHmmss<BR>
     * @@roseuid 429D62440260
     */
    protected void createColumnHeader() 
    {
        final String STR_METHOD_NAME = " createColumnHeader()";
        log.entering(STR_METHOD_NAME);

        final int COLUMN_MAX = 8;
        WEB3GentradeCsvColumnModel[] l_models = new WEB3GentradeCsvColumnModel[COLUMN_MAX];

        //index 0運用コード
        l_models[0] = new WEB3GentradeCsvColumnModel(
            ORDER_EMP_CODE_LABEL,
            0,
            WEB3GentradeCsvColumnModel.STRINGTYPE,
            null);

        //index 1銘柄コード
        l_models[1] = new WEB3GentradeCsvColumnModel(
            PRODUCT_CODE_LABEL,
            1,
            WEB3GentradeCsvColumnModel.STRINGTYPE,
            null);
            
        //index 2売買
        l_models[2] = new WEB3GentradeCsvColumnModel(
            TRADE_LABEL,
            2,
            WEB3GentradeCsvColumnModel.STRINGTYPE,
            null);

        //index 3約定株数
        l_models[3] = new WEB3GentradeCsvColumnModel(
            EXEC_QUANTITY_LABEL,
            3,
            WEB3GentradeCsvColumnModel.DOUBLETYPE,
            null);
            
        //index 4約定単価
        l_models[4] = new WEB3GentradeCsvColumnModel(
            EXEC_PRICE_LABEL,
            4,
            WEB3GentradeCsvColumnModel.DOUBLETYPE,
            null);

        //index 5約定No.
        l_models[5] = new WEB3GentradeCsvColumnModel(
            EXEC_NO_LABEL,
            5,
            WEB3GentradeCsvColumnModel.INTEGERTYPE,
            null);
            
        //index 6FILLER
        l_models[6] = new WEB3GentradeCsvColumnModel(
            FILLER_LABEL,
            6,
            WEB3GentradeCsvColumnModel.STRINGTYPE,
            null);
            
        //index 7約定日時
        l_models[7] = new WEB3GentradeCsvColumnModel(
            EXEC_TIMESTAMP_LABEL,
            7,
            WEB3GentradeCsvColumnModel.DATETYPE,
            new SimpleDateFormat("yyyyMMddHHmmss"));
            
        this.setColumnHeader(l_models);
            
        log.exiting(STR_METHOD_NAME);
    }
    
    /**
     * (getアップロードファ@イルＩＤ)<BR>
     * アップロードファ@イルＩＤを返却する。<BR>
     * <BR>
     * 外国株式約定結果一括入力.アップロードファ@イルＩＤを返却する。<BR>
     * @@return String
     * @@roseuid 429D6A1C001E
     */
    public String getUploadFileId() 
    {
        return this.uploadFileId;
    }
    
    /**
     * (get銘柄タイプ)<BR>
     * 銘柄タイプ.外国株式を返却する。<BR>
     * @@return com.fitechlabs.xtrade.plugin.tc.gentrade.ProductTypeEnum
     * @@roseuid 42C4E14401B8
     */
    public ProductTypeEnum getProductType() 
    {
        return ProductTypeEnum.FOREIGN_EQUITY;
    }
    
    /**
     * (get運用コード)<BR>
     * 行番号に対応する明細行の運用コードを取得する。 <BR>
     * <BR>
     * this.get項目値()にて運用コードを取得し返却する。 <BR>
     * <BR>
     * [get項目値()に指定する引数] <BR>
     * 行番号：　@行番号 <BR>
     * カラム：　@getカラムモデル(外国株式約定結果一括入力CSV.運用コードラベル)の戻り値。<BR>
     * @@param l_intLineNumber - (行番号)<BR>
     * 行番号<BR>
     * @@return String
     * @@roseuid 429D6A5400AB
     */
    public String getOrderEmpCode(int l_intLineNumber) 
    {
        String l_strOrderEmpCode = 
            (String)this.getValue(l_intLineNumber, this.getColumnModel(ORDER_EMP_CODE_LABEL));
        return l_strOrderEmpCode;
    }
    
    /**
     * (get銘柄コード)<BR>
     * 行番号に対応する明細行の銘柄コードを取得する。 <BR>
     * <BR>
     * this.get項目値()にて銘柄コードを取得し返却する。 <BR>
     * <BR>
     * [get項目値()に指定する引数] <BR>
     * 行番号：　@行番号 <BR>
     * カラム：　@getカラムモデル(外国株式約定結果一括入力CSV.銘柄コードラベル)の戻り値。<BR>
     * @@param l_intLineNumber - (行番号)<BR>
     * 行番号<BR>
     * @@return String
     * @@roseuid 429D6A9D030C
     */
    public String getProductCode(int l_intLineNumber) 
    {
        String l_strProductCode = 
            (String)this.getValue(l_intLineNumber, this.getColumnModel(PRODUCT_CODE_LABEL));
        return l_strProductCode;
    }
    
    /**
     * (get売買)<BR>
     * 行番号に対応する明細行の売買を取得する。 <BR>
     * <BR>
     * this.get項目値()にて売買を取得し返却する。 <BR>
     * <BR>
     * [get項目値()に指定する引数] <BR>
     * 行番号：　@行番号 <BR>
     * カラム：　@getカラムモデル(外国株式約定結果一括入力CSV.売買ラベル)の戻り値。<BR>
     * @@param l_intLineNumber - (行番号)<BR>
     * 行番号<BR>
     * @@return String
     * @@roseuid 429D6AB40389
     */
    public String getTrade(int l_intLineNumber) 
    {
        String l_strTrade = 
            (String)this.getValue(l_intLineNumber, this.getColumnModel(TRADE_LABEL));
        return l_strTrade;
    }
    
    /**
     * (get約定株数)<BR>
     * 行番号に対応する明細行の約定株数を取得する。 <BR>
     * <BR>
     * this.get項目値()にて約定株数を取得し、標準データ型（double）<BR>
     * に変換して返却する。 <BR>
     * 型変換に失敗した場合は、数値以外がセットされていると判断し<BR>
     * 例外をスローする。<BR>
     *   class: WEB3BusinessLayerException<BR>
     *   tag:  BUSINESS_ERROR_02026<BR>
     * <BR>
     * [get項目値()に指定する引数] <BR>
     * 行番号：　@行番号 <BR>
     * カラム：　@getカラムモデル(外国株式約定結果一括入力CSV.約定株数ラベル)の戻り値。<BR>
     * @@param l_intLineNumber - (行番号)<BR>
     * 行番号<BR>
     * @@return double
     * @@throws WEB3BaseException
     * @@roseuid 429D6ADE005C
     */
    public double getExecQuantity(int l_intLineNumber) throws WEB3BaseException
    {
        final String STR_METHOD_NAME = " getExecQuantity(int)";
        log.entering(STR_METHOD_NAME);

        Object l_obj = this.getValue(l_intLineNumber, this.getColumnModel(EXEC_QUANTITY_LABEL));
        if (!(l_obj instanceof Double))
        {
            String l_strMessage = "約定数量が9桁以内の整数値ではありません。";
            log.debug(l_strMessage);
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_02026,
                this.getClass().getName() + STR_METHOD_NAME,
                l_strMessage);
        }
        
        log.exiting(STR_METHOD_NAME);     
        return ((Double)l_obj).doubleValue();
    }
    
    /**
     * (get約定単価)<BR>
     * 行番号に対応する明細行の約定単価を取得する。 <BR>
     * <BR>
     * this.get項目値()にて約定単価を取得し、標準データ型（double）に変換して返却する。 <BR>
     * 型変換に失敗した場合は、数値以外がセットされていると判断し例外をスローする。<BR>
     *   class: WEB3BusinessLayerException<BR>
     *   tag:  BUSINESS_ERROR_02022<BR>
     * <BR>
     * [get項目値()に指定する引数] <BR>
     * 行番号：　@行番号 <BR>
     * カラム：　@getカラムモデル(外国株式約定結果一括入力CSV.約定単価ラベル)の戻り値。<BR>
     * @@param l_intLineNumber - (行番号)<BR>
     * 行番号<BR>
     * @@return double
     * @@throws WEB3BaseException
     * @@roseuid 429D6B0C0118
     */
    public double getExecPrice(int l_intLineNumber) throws WEB3BaseException 
    {
        final String STR_METHOD_NAME = " getExecPrice(int)";
        log.entering(STR_METHOD_NAME);

        Object l_obj = this.getValue(l_intLineNumber, this.getColumnModel(EXEC_PRICE_LABEL));
        if (!(l_obj instanceof Double))
        {
            String l_strMessage = "約定単価が数値以外の値です。";
            log.debug(l_strMessage);
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_02022,
                this.getClass().getName() + STR_METHOD_NAME,
                l_strMessage);
        }

        double l_dblExecPrice = ((Double)l_obj).doubleValue();

        log.exiting(STR_METHOD_NAME);     
        return l_dblExecPrice;
    }
    
    /**
     * (get約定No.)<BR>
     * 行番号に対応する明細行の約定No.を取得する。 <BR>
     * <BR>
     * this.get項目値()にて約定No.を取得し、標準データ型（int）に変換して返却する。 <BR>
     * 型変換に失敗した場合は、数値以外がセットされていると判断し例外をスローする。<BR>
     *   class: WEB3BusinessLayerException<BR>
     *   tag:  BUSINESS_ERROR_02191<BR>
     * <BR>
     * [get項目値()に指定する引数] <BR>
     * 行番号：　@行番号 <BR>
     * カラム：　@getカラムモデル(外国株式約定結果一括入力CSV.約定No.ラベル)の戻り値。<BR>
     * @@param l_intLineNumber - (行番号)<BR>
     * 行番号<BR>
     * @@return int
     * @@throws WEB3BaseException
     * @@roseuid 429D6B1D035A
     */
    public int getExecNo(int l_intLineNumber) throws WEB3BaseException 
    {
        final String STR_METHOD_NAME = " getExecNo(int)";
        log.entering(STR_METHOD_NAME);

        Object l_obj = 
            this.getValue(l_intLineNumber, this.getColumnModel(EXEC_NO_LABEL));
        if (!(l_obj instanceof Integer))
        {
            String l_strMessage = "約定No.か数値以外の値です。";
            log.debug(l_strMessage);
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_02191,
                this.getClass().getName() + STR_METHOD_NAME,
                l_strMessage);
        }

        int l_intExecNo = ((Integer)l_obj).intValue();
            
        log.exiting(STR_METHOD_NAME);
        return l_intExecNo;
    }
    
    /**
     * (get約定日時)<BR>
     * 行番号に対応する明細行の約定日時を取得する。 <BR>
     * <BR>
     * this.get項目値()にて約定日時を取得し、Date型に変換して返却する。 <BR>
     * 型変換に失敗した場合は、フォーマットエラーと判断し例外をスローする。<BR>
     *   class: WEB3BusinessLayerException<BR>
     *   tag:  BUSINESS_ERROR_02192<BR>
     * ※約定日時のフォーマット：　@yyyyMMddHHmmss<BR>
     * <BR>
     * [get項目値()に指定する引数] <BR>
     * 行番号：　@行番号 <BR>
     * カラム：　@getカラムモデル(外国株式約定結果一括入力CSV.約定日時ラベル)の戻り値。<BR>
     * @@param l_intLineNumber - (行番号)<BR>
     * 行番号<BR>
     * @@return Date
     * @@throws WEB3BaseException
     * @@roseuid 42AFED1600DA
     */
    public Date getExecTimestamp(int l_intLineNumber) throws WEB3BaseException 
    {
        final String STR_METHOD_NAME = " getExecTimestamp(int)";
        log.entering(STR_METHOD_NAME);

        Object l_obj = this.getValue(l_intLineNumber, this.getColumnModel(EXEC_TIMESTAMP_LABEL));
        if (l_obj != null && !(l_obj instanceof Date))
        {
            String l_strMessage = "約定日時のフォーマットが不正です。";
            log.debug(l_strMessage);
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_02192,
                this.getClass().getName() + STR_METHOD_NAME,
                l_strMessage);
        }
        Date l_datExecTimestamp = (Date)l_obj;
        
        log.exiting(STR_METHOD_NAME);                
        return l_datExecTimestamp;
    }
    
    /**
     * (validate明細行)<BR>
     * 明細行のチェックを行う。<BR>
     * <BR>
     * １）　@運用コードのチェック <BR>
     * 　@get運用コード()にて、指定行番号のデータを取得しチェックを行う。 <BR>
     * <BR>
     * 　@[get運用コード()に指定する引数] <BR>
     * 　@行番号：　@行番号 <BR>
     * <BR>
     * 　@１－１）　@運用コードが取得できない場合（get運用コード() == null）、例外をスローする。 <BR>
     *   class: WEB3BusinessLayerException<BR>
     *   tag:  BUSINESS_ERROR_02032<BR>
     *  <BR>
     * 　@１－２）　@７桁の半角英数字でない場合、例外をスローする。<BR>
     *   class: WEB3BusinessLayerException<BR>
     *   tag:  BUSINESS_ERROR_02033<BR>
     *  <BR>
     * 　@１－３）　@左2byteが引数.外国株式運用コード採番区分でない場合、例外をスローする。<BR>
     *   class: WEB3BusinessLayerException<BR>
     *   tag:  BUSINESS_ERROR_03164<BR>
     *  <BR>
     * 　@１－４）　@右5byteが数字でない場合、例外をスローする。<BR>
     *   class: WEB3BusinessLayerException<BR>
     *   tag:  BUSINESS_ERROR_02035<BR>
     *  <BR>
     * 　@１－５）　@this.get注文単位()　@にて外国株式注文単位オブジェクトを取得する。<BR>
     * 　@　@　@　@　@　@取得できなかった場合、例外をスローする。<BR>
     *   class: WEB3BusinessLayerException<BR>
     *   tag: BUSINESS_ERROR_02165<BR>
     *  <BR>
     * <BR>
     * 　@[get注文単位()に指定する引数]<BR>
     * 　@行番号：　@行番号 <BR>
     * <BR>
     * 　@１－６）　@注文単位のチェック<BR>
     * 　@　@　@出来終了後の注文の場合（１－５）で取得した注文単位.is出来終了() == true）、<BR>
     * 例外をスローする。<BR>
     * <BR>
     *   class: WEB3BusinessLayerException<BR>
     *   tag:  BUSINESS_ERROR_02144<BR>
     *  <BR>
     * ２）　@既約定のチェック<BR>
     * 　@２－１）　@約定取得<BR>
     * 　@　@注文単位.getExecutions()にて、注文に関連する約定を取得する。<BR>
     * <BR>
     * 　@２－２）　@処理対象外（Skipデータ）の判定<BR>
     * 　@　@処理対象外の明細行の場合、-1を返却し処理を終了する。（return -1;）<BR>
     * <BR>
     * 　@　@[処理対象外の判定]<BR>
     * 　@　@getExecutions()で取得した約定に、以下の条件に当てはまる約定が存在する場合。<BR>
     * <BR>
     * 　@　@約定.約定通番 == get約定No.(index) And<BR>
     * 　@　@約定.約定単価 == get約定単価(index) And<BR>
     * 　@　@約定.約定株数 == get株数(index) And<BR>
     * 　@　@約定.約定経路区分 == 約定経路区分.2：約定結果一括入力<BR>
     * <BR>
     * 　@２－３）　@約定Ｎｏ．のチェック<BR>
     * 　@　@get約定No.()にて、指定行番号のデータを取得する。<BR>
     * <BR>
     * 　@　@[get約定No.()に指定する引数]<BR>
     * 　@　@行番号：　@行番号<BR>
     * <BR>
     * 　@　@get約定No.()にて、約定No.が取得できた場合のみ以下のチェックを行う。<BR>
     * <BR>
     * 　@　@２－３－１）　@取得した約定No.が、有効桁数3桁以内の正の整数値でない場合、例外<BR>
     * をスローする。
     * <BR>
     *   class: WEB3BusinessLayerException<BR>
     *   tag: BUSINESS_ERROR_02170<BR>
     *  <BR>
     * 　@　@２－３－２）　@別経路で登録された約定で同一約定通番が既に存在する場合（エラー条<BR>
     * 件に当てはまる場合）、例外をスローする。<BR>
     * <BR>
     *   class: WEB3BusinessLayerException<BR>
     *   tag:  BUSINESS_ERROR_02171<BR>
     *  <BR>
     * 　@　@[エラー条件]<BR>
     * 　@　@getExecutions()で取得した約定に、以下の条件に当てはまる約定が存在する場合。<BR>
     * 　@　@<BR>
     * 　@　@約定.約定通番 == get約定No.(index)
     * <BR>
     * 　@２－４）　@約定日時のチェック<BR>
     * 　@　@get約定日時()にて、指定行番号のデータを取得する。<BR>
     * 　@　@約定日時が取得できなかった場合（get約定日時() == null）、例外をスローする。<BR>
     * <BR>
     *   class: WEB3BusinessLayerException<BR>
     *   tag: BUSINESS_ERROR_02172<BR>
     *  <BR>
     * 　@　@[get約定日時()に指定する引数]<BR>
     * 　@　@行番号：　@行番号<BR>
     * <BR>
     * 　@　@外国株式注文マネージャ.validate約定日()をコールする。<BR>
     * <BR>
     * 　@　@[validate約定日()に指定する引数] <BR>
     * 　@　@注文単位：　@注文単位<BR>
     * 　@　@約定日：　@get約定日時()<BR>
     * <BR>
     * ３）　@銘柄コードのチェック<BR>
     * 　@get銘柄コード()にて、指定行番号のデータを取得し、銘柄コードが取得できた場合<BR>
     * のみ以下のチェックを行う。<BR>
     * <BR>
     * 　@[get銘柄コード()に指定する引数]<BR>
     * 　@行番号：　@行番号<BR>
     * <BR>
     * 　@３－１）　@文字数が9byteより大きい場合（銘柄コード.length > 9）は、例外をスローする。 <BR>
     *   class: WEB3BusinessLayerException<BR>
     *   tag:  BUSINESS_ERROR_00439<BR>
     *  <BR>　@
     * ３－２）　@注文単位の銘柄と一致しない場合（以下の条件に当てはならない場合）、<BR>
     * 例外をスローする。<BR>
     * <BR>
     *   class: WEB3BusinessLayerException<BR>
     *   tag:  BUSINESS_ERROR_00301<BR>
     *  <BR>
     * 　@[注文単位の銘柄と一致する条件]<BR>
     * 　@注文単位.getProduct().銘柄コード == 銘柄コード　@または、<BR>
     * 　@注文単位.getProduct().現地銘柄コード == 銘柄コード<BR>
     * <BR>
     * ４）　@売買のチェック<BR>
     * 　@get売買()にて、指定行番号のデータを取得し、売買が取得できた場合のみ以下の<BR>
     * チェックを行う。<BR>
     * <BR>
     * 　@[get売買()に指定する引数]<BR>
     * 　@行番号：　@行番号 <BR>
     * <BR>
     * 　@4－１）　@売買が、有効なコード値でない場合、例外をスローする。<BR>
     *   class: WEB3BusinessLayerException<BR>
     *   tag:  BUSINESS_ERROR_01403<BR>
     *  <BR>　@
     *  [有効コード値]<BR>
     * 　@外国株式約定結果一括入力CSV.売買_売（"S"）または、<BR>
     * 　@外国株式約定結果一括入力CSV.売買_買（"B"）<BR>
     * <BR>
     * 　@4－２）　@注文種別（注文単位.getOrderType()()）と対応する値でない場合、<BR>
     * 例外をスローする。<BR>
     *   class: WEB3BusinessLayerException<BR>
     *   tag: BUSINESS_ERROR_02167<BR>
     *  <BR>
     * 　@[対応する値]<BR>
     * 　@外国株式約定結果一括入力CSV.売買_買（"B"）⇒注文種別.701：外株買い<BR>
     * 　@外国株式約定結果一括入力CSV.売買_売（"S"）⇒注文種別.702：外株売り<BR>
     * <BR>
     * ５）　@約定株数のチェック<BR>
     * 　@get約定株数()にて、指定行番号のデータを取得しチェックを行う。 <BR>
     * <BR>
     * 　@[get約定株数()に指定する引数]<BR>
     * 　@行番号：　@行番号 <BR>
     *  <BR>
     * 　@5－１）　@9桁以内の整数値でない場合、例外をスローする。<BR>
     * <BR>
     *   class: WEB3BusinessLayerException<BR>
     *   tag:  BUSINESS_ERROR_02026<BR>
     *  <BR>
     * 　@５－２）　@数値に変換した値 <= 0の場合、例外をスローする。<BR>
     * <BR>
     *   class: WEB3BusinessLayerException<BR>
     *   tag:  BUSINESS_ERROR_02186<BR>
     * <BR>
     * ６）　@約定単価のチェック<BR>
     * 　@get約定単価()にて、指定行番号のデータを取得しチェックを行う。 <BR>
     * <BR>
     * 　@[get約定単価()に指定する引数]<BR>
     * 　@行番号：　@行番号 <BR>
     * <BR>
     * 　@６－１）　@get約定単価()の戻り値 <= 0の場合、例外をスローする。<BR>
     *   class: WEB3BusinessLayerException<BR>
     *   tag:  BUSINESS_ERROR_02023<BR>
     *  <BR>
     * 　@６－２）　@数値に変換した時の有効桁数が、整数部６桁，小数部５桁の範囲外であれば、<BR>
     * 例外をスローする。<BR>
     *   class: WEB3BusinessLayerException<BR>
     *   tag:  BUSINESS_ERROR_02024<BR>
     *  <BR>
     * 　@６－３）　@外国株式注文マネージャ.validate約定単価()にて、約定単価をチェックする。<BR>
     * <BR>
     * 　@　@[validate約定単価()に指定する引数]<BR>
     * 　@　@注文単位：　@注文単位<BR>
     * 　@　@約定数量：　@get約定単価()<BR>
     * <BR>
     * ７）　@正常終了（0）を返却する。（return 0;）<BR>
     * @@param l_intLineNumber - (行番号)<BR>
     * 行番号<BR>
     * @@param l_strFeqOrderEmpCodeManageDiv - (外国株式運用コード採番区分)<BR>
     * 外国株式運用コード採番区分<BR>
     * @@return int
     * @@throws WEB3BaseException
     * @@roseuid 429D6C780260
     */
    public int validateDetailLine(int l_intLineNumber,
        String l_strFeqOrderEmpCodeManageDiv) throws WEB3BaseException
    {
        final String STR_METHOD_NAME = " validateDetailLine(int, String)";
        log.entering(STR_METHOD_NAME);

        //１）　@運用コードのチェック 
        //　@get運用コード()にて、指定行番号のデータを取得しチェックを行う。 
        //　@[get運用コード()に指定する引数] 
        //　@行番号：　@行番号
        String l_strOrderEmpCode = this.getOrderEmpCode(l_intLineNumber); 
        
        //　@１－１）　@運用コードが取得できない場合（get運用コード() == null）、例外をスローする。 
        if (WEB3StringTypeUtility.isEmpty(l_strOrderEmpCode))
        {
            String l_strMessage = "運用コードが未入力です。";
            log.debug(l_strMessage);
            log.debug(l_strMessage);
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_02032,
                this.getClass().getName() + STR_METHOD_NAME,
                l_strMessage);
        }
        
        //　@１－２）　@７桁の半角英数字でない場合、例外をスローする。
        final int MANAGEMENTCODE_LEN = 7;
        if (!WEB3StringTypeUtility.isLetterAndDigit(l_strOrderEmpCode) 
            || l_strOrderEmpCode.length() != MANAGEMENTCODE_LEN)
        {
            log.debug("運用コード = " + l_strOrderEmpCode);
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_02033,
                getClass().getName() + STR_METHOD_NAME,
                "運用コードが７桁の半角英数字ではありません。");
        }

        //　@１－３）　@左2byteが引数.外国株式運用コード採番区分でない場合、例外をスローする。
        final int LEFT_LEN = 2;
        String l_strLeft2Byte = l_strOrderEmpCode.substring(0, LEFT_LEN);
        if (!l_strLeft2Byte.equals(l_strFeqOrderEmpCodeManageDiv))
        {
            log.debug("運用コード = " + l_strOrderEmpCode);
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_03164,
                getClass().getName() + STR_METHOD_NAME,
                "運用コードの左2byteが引数.外国株式運用コード採番区分ではありません。");
        }

        //　@１－４）　@右5byteが数字でない場合、例外をスローする。
        final int RIGHT_LEN = 5;
        String l_strRight5Byte = l_strOrderEmpCode.substring(
            l_strOrderEmpCode.length() - RIGHT_LEN);
        if (!WEB3StringTypeUtility.isDigit(l_strRight5Byte))
        {
            log.debug("運用コード = " + l_strOrderEmpCode);
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_02035,
                getClass().getName() + STR_METHOD_NAME,
                "運用コードの右5byteが数字ではありません。");
        }

        //　@１－５）　@this.get注文単位()　@にて外国株式注文単位オブジェクトを取得する。
        //　@　@　@　@　@　@取得できなかった場合、例外をスローする。
        WEB3FeqOrderUnit l_feqOrderUnit = this.getOrderUnit(l_intLineNumber);
        
        //　@１－６）　@注文単位のチェック
        //　@　@　@出来終了後の注文の場合（１－５）で取得した注文単位.is出来終了() == true）、
        //例外をスローする。
        if (l_feqOrderUnit.isExecEnd())
        {
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_02144,
                getClass().getName() + STR_METHOD_NAME,
                "出来終了処理済みなので、約定不可です。");
        }
        
        //２）　@既約定のチェック
        //　@２－１）　@約定取得
        //　@　@注文単位.getExecutions()にて、注文に関連する約定を取得する。
        OrderExecution[] l_orderExecs = l_feqOrderUnit.getExecutions();
        
        //　@２－２）　@処理対象外（Skipデータ）の判定
        //　@　@処理対象外の明細行の場合、-1を返却し処理を終了する。（return -1;）
        //　@　@約定.約定通番 == get約定No.(index) And
        //　@　@約定.約定単価 == get約定単価(index) And
        //　@　@約定.約定株数 == get株数(index) And
        int l_intCount = 0;
        if (l_orderExecs != null)
        {
            l_intCount = l_orderExecs.length;
        }
        for (int i = 0; i < l_intCount; i++)
        {
            OrderExecution l_orderExec = (OrderExecution)l_orderExecs[i];
            if (l_orderExec.getExecutionSerialNo() 
                    == this.getExecNo(l_intLineNumber)
                && l_orderExec.getExecutionPrice() 
                    == this.getExecPrice(l_intLineNumber)
                && l_orderExec.getExecutionQuantity() 
                    == this.getExecQuantity(l_intLineNumber))
            {
                return -1;
            }
        }
        
        //　@２－３）　@約定Ｎｏ．のチェック
        //　@　@get約定No.()にて、指定行番号のデータを取得する。
        int l_intExecNo = this.getExecNo(l_intLineNumber);

        //　@　@２－３－１）　@取得した約定No.が、有効桁数3桁以内の正の整数値でない場合、例外をスローする。
        if (l_intExecNo <= 0 || l_intExecNo >= 1000)
        {
            String l_strMessage = "約定Noエラー「" + l_intExecNo + "」";
            log.debug(l_strMessage);
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_02170,
                this.getClass().getName() + STR_METHOD_NAME,
                l_strMessage);
        }
        
        //　@２－４）　@約定日時のチェック
        //　@　@get約定日時()にて、指定行番号のデータを取得する。
        Date l_datExecDateTimestamp = this.getExecTimestamp(l_intLineNumber);
        
        //　@　@外国株式注文マネージャ.validate約定日()をコールする。
        FinApp l_finApp = (FinApp) Services.getService(FinApp.class);
        if (l_finApp == null)
        {
            String l_strMessage = "FinAppが存在しない。";
            log.debug(l_strMessage);
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80002,
                this.getClass().getName() + STR_METHOD_NAME,
                l_strMessage);
        }
        TradingModule l_tradingModule = l_finApp.getTradingModule(ProductTypeEnum.FOREIGN_EQUITY);//NotInstalledException
        if (l_tradingModule == null)
        {
            String l_strMessage = "TradingModuleが存在しない。";
            log.debug(l_strMessage);
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80002,
                this.getClass().getName() + STR_METHOD_NAME,
                l_strMessage);
        }
        WEB3FeqOrderManager l_orderManager = 
            (WEB3FeqOrderManager)l_tradingModule.getOrderManager();
        if (l_orderManager == null)
        {
            String l_strMessage = "外国株式注文マネージャが存在しない。";
            log.debug(l_strMessage);
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80002,
                this.getClass().getName() + STR_METHOD_NAME,
                l_strMessage);
        }
        
        // 約定日時が取得できた場合
        // validate約定日()をコールする。
        // [validate約定日()に指定する引数] 
        //    注文単位：　@注文単位 
        //    約定日：　@get約定日時() 
        if (l_datExecDateTimestamp != null)
        {
            l_orderManager.validateExecutionDate(l_feqOrderUnit, l_datExecDateTimestamp);            
        }
        // 約定日時が取得できなかった場合
        else
        {
            // カラムモデル取得
            WEB3GentradeCsvColumnModel l_model =
                this.getColumnModel(EXEC_TIMESTAMP_LABEL);
            // 値セット
            this.setValue(
                l_intLineNumber,
                l_model,
                GtlUtils.getSystemTimestamp());
        }
        //３）　@銘柄コードのチェック
        //　@get銘柄コード()にて、指定行番号のデータを取得し、銘柄コードが取得できた場合
        //のみ以下のチェックを行う。
        String l_strProductCode = this.getProductCode(l_intLineNumber);
        if (!WEB3StringTypeUtility.isEmpty(l_strProductCode))
        {
            //　@３－１）　@文字数が9byteより大きい場合（銘柄コード.length > 9）は、例外をスローする。 
            //  class: WEB3BusinessLayerException
            //  tag:  BUSINESS_ERROR_00439
            if (l_strProductCode.length() > 9)
            {
                String l_strMessage = "銘柄コードエラー「" + l_strProductCode + "」";
                log.debug(l_strMessage);
                log.exiting(STR_METHOD_NAME);
                throw new WEB3BusinessLayerException(
                    WEB3ErrorCatalog.BUSINESS_ERROR_00439,
                    this.getClass().getName() + STR_METHOD_NAME,
                    l_strMessage);
            }
            
            //３－２）　@注文単位の銘柄と一致しない場合（以下の条件に当てはならない場合）、
            //例外をスローする。
            FeqProduct l_feqProduct = (FeqProduct)l_feqOrderUnit.getProduct();
            FeqProductRow  l_feqProductRow = (FeqProductRow)l_feqProduct.getDataSourceObject();
            if (!l_strProductCode.equals(l_feqProductRow.getProductCode())
                && !l_strProductCode.equals(l_feqProductRow.getOffshoreProductCode()))
            {
                String l_strMessage = "銘柄コードエラー「" + l_strProductCode + "」";
                log.debug(l_strMessage);
                log.exiting(STR_METHOD_NAME);
                throw new WEB3BusinessLayerException(
                    WEB3ErrorCatalog.BUSINESS_ERROR_00301,
                    this.getClass().getName() + STR_METHOD_NAME,
                    l_strMessage);
            }
        }
        
        //４）　@売買のチェック
        //　@get売買()にて、指定行番号のデータを取得し、売買が取得できた場合のみ以下の
        //チェックを行う。
        String l_strTrade = this.getTrade(l_intLineNumber);
        if (!WEB3StringTypeUtility.isEmpty(l_strTrade))
        {
            //　@4－１）　@売買が、有効なコード値でない場合、例外をスローする。
            // [有効コード値]
            //　@外国株式約定結果一括入力CSV.売買_売（"S"）または、
            //　@外国株式約定結果一括入力CSV.売買_買（"B"）
            if (!TRADE_SELL.equals(l_strTrade) && !TRADE_BUY.equals(l_strTrade))
            {
                String l_strMessage = "売買エラー「" + l_strTrade + "」";
                log.debug(l_strMessage);
                log.exiting(STR_METHOD_NAME);
                throw new WEB3BusinessLayerException(
                    WEB3ErrorCatalog.BUSINESS_ERROR_01403,
                    this.getClass().getName() + STR_METHOD_NAME,
                    l_strMessage);
            }
            
            //　@4－２）　@注文種別（注文単位.getOrderType()()）と対応する値でない場合、
            //例外をスローする。
            OrderTypeEnum l_orderType = l_feqOrderUnit.getOrderType();
            if (TRADE_BUY.equals(l_strTrade) && !OrderTypeEnum.FEQ_BUY.equals(l_orderType))
            {
                String l_strMessage = "売買エラー「" + l_strTrade + "」";
                log.debug(l_strMessage);
                log.exiting(STR_METHOD_NAME);
                throw new WEB3BusinessLayerException(
                    WEB3ErrorCatalog.BUSINESS_ERROR_02167,
                    this.getClass().getName() + STR_METHOD_NAME,
                    l_strMessage);
            }
            else if (TRADE_SELL.equals(l_strTrade) && !OrderTypeEnum.FEQ_SELL.equals(l_orderType))
            {
                String l_strMessage = "売買エラー「" + l_strTrade + "」";
                log.debug(l_strMessage);
                log.exiting(STR_METHOD_NAME);
                throw new WEB3BusinessLayerException(
                    WEB3ErrorCatalog.BUSINESS_ERROR_02167,
                    this.getClass().getName() + STR_METHOD_NAME,
                    l_strMessage);
            }
        }
        
        //５）　@約定株数のチェック
        //　@get約定株数()にて、指定行番号のデータを取得しチェックを行う。 
        double l_dblExecQuantity = this.getExecQuantity(l_intLineNumber);

        //　@5－１）　@9桁以内の整数値でない場合、例外をスローする。
        String l_strExecQuantity = l_dblExecQuantity + "";
        if (!WEB3StringTypeUtility.isInteger(l_strExecQuantity) 
            || WEB3StringTypeUtility.getIntegerDigits(l_strExecQuantity) > 9)
        {
            String l_strMessage = "約定株数エラー「" + l_dblExecQuantity + "」";
            log.debug(l_strMessage);
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_02026,
                this.getClass().getName() + STR_METHOD_NAME,
                l_strMessage);
        }
        
        //５－２）　@数値に変換した値 <= 0の場合、例外をスローする。
        if (l_dblExecQuantity <= 0)
        {
            String l_strMessage = "約定株数エラー「" + l_dblExecQuantity + "」";
            log.debug(l_strMessage);
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_02186,
                this.getClass().getName() + STR_METHOD_NAME,
                l_strMessage);
        }

        //６）　@約定単価のチェック
        //　@get約定単価()にて、指定行番号のデータを取得しチェックを行う。 
        double l_dblExecPrice = this.getExecPrice(l_intLineNumber);
        
        //　@６－１）get約定単価()の戻り値 <= 0の場合、例外をスローする。
        if (l_dblExecPrice <= 0)
        { 
            String l_strMessage = "約定単価 <= 0。「" + l_dblExecPrice + "」";
            log.debug(l_strMessage);
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_02023,
                this.getClass().getName() + STR_METHOD_NAME,
                l_strMessage);
        }

        //　@６－２）　@数値に変換した時の有効桁数が、整数部６桁，小数部５桁の範囲外であれば、
        //例外をスローする。
        String l_strExecPrice = l_dblExecPrice + "";
        if (WEB3StringTypeUtility.getIntegerDigits(l_strExecPrice) > 6 
            || WEB3StringTypeUtility.getFractionDigits(l_strExecPrice) > 5)
        {
            String l_strMessage = "約定単価エラー「" + l_dblExecPrice + "」";
            log.debug(l_strMessage);
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_02024,
                this.getClass().getName() + STR_METHOD_NAME,
                l_strMessage);
        }

        //　@６－３）　@外国株式注文マネージャ.validate約定単価()にて、約定単価をチェックする。
        l_orderManager.validateExecutedPrice(l_feqOrderUnit, l_dblExecPrice);
        
        //７）　@正常終了（0）を返却する。（return 0;）
        return 0;
    }
    
    /**
     * (validate重複行)<BR>
     * 追加済みの明細行のチェックを行う。<BR>
     * <BR>
     * １）　@get運用コード(行番号)，get約定No.(行番号)にて、指定行番号のデータを取得する。<BR>
     * <BR>
     * ２）　@約定No.重複チェック，約定株数集計<BR>
     * 　@※CSVファ@イルに追加済みの明細行（0～（行番号 -1））の数分LOOP<BR>
     * <BR>
     * 　@２－１）　@約定No.重複チェック<BR>
     * 　@　@指定行番号の運用コードと約定No.と同じ値の行（運用コード == 運用コード && <BR>
     * 約定No. == 約定No.）が存在する場合、例外をスローする。<BR>
     * <BR>
     *   class: WEB3BusinessLayerException<BR>
     *   tag: BUSINESS_ERROR_02173<BR>
     * 　@２－２）　@約定株数チェック<BR>
     * 　@　@指定行番号の運用コードと同じ運用コードの行について、約定株数を集計（sum）する。<BR>
     * <BR>
     * ３）　@集計後の約定株数で、約定数量チェックを行う。<BR>
     * 　@外国株式注文マネージャ.validate約定数量()をコールする。<BR>
     * <BR>
     * 　@[validate約定数量()に指定する引数] <BR>
     * 　@注文単位：　@注文単位<BR>
     * 　@約定数量：　@get約定株数(行番号) + ２）で集計した約定株数（sum）<BR>
     * @@param l_intLineNumber - (行番号)<BR>
     * 行番号<BR>
     * @@return String
     * @@throws WEB3BaseException
     * @@roseuid 42C4CE38032F
     */
    public String validateRepeatLine(int l_intLineNumber) throws WEB3BaseException
    {
        final String STR_METHOD_NAME = " validateRepeatLine(int)";
        log.entering(STR_METHOD_NAME);
        
        //１）　@get運用コード(行番号)，get約定No.(行番号)にて、指定行番号のデータを取得する。
        String l_strOrderEmpCode = this.getOrderEmpCode(l_intLineNumber);
        int l_intExecNo = this.getExecNo(l_intLineNumber);
        
        //２）　@約定No.重複チェック，約定株数集計
        //　@２－１）　@約定No.重複チェック
        //　@　@指定行番号の運用コードと約定No.と同じ値の行（運用コード == 運用コード &&
        //　@　@約定No. == 約定No.）が存在する場合、例外をスローする。
        //　@２－２）　@約定株数チェック
        //　@　@指定行番号の運用コードと同じ運用コードの行について、約定株数を集計（sum）する。
        double l_dblExecQuantitySum = this.getExecQuantity(l_intLineNumber);
        for (int i = 0; i < l_intLineNumber; i++)
        {
            String l_strOrderEmpCodeTemp = this.getOrderEmpCode(i);
            int l_intExecNoTemp = this.getExecNo(i);
            if (l_strOrderEmpCode.equals(l_strOrderEmpCodeTemp))
            {
                if (l_intExecNo == l_intExecNoTemp)
                {
                    String l_strMessage = "指定行番号の運用コードが約定No.と同じ値の行が存在します。";
                    log.debug(l_strMessage);
                    log.exiting(STR_METHOD_NAME);
                    throw new WEB3BusinessLayerException(
                        WEB3ErrorCatalog.BUSINESS_ERROR_02173,
                        this.getClass().getName() + STR_METHOD_NAME,
                        l_strMessage);
                }else{
					l_dblExecQuantitySum += this.getExecQuantity(i);
                }
            }
        }
        
        //３）　@集計後の約定株数で、約定数量チェックを行う。
        //　@外国株式注文マネージャ.validate約定数量()をコールする。        
        FinApp l_finApp = (FinApp) Services.getService(FinApp.class);
        if (l_finApp == null)
        {
            String l_strMessage = "FinAppが存在しない。";
            log.debug(l_strMessage);
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80002,
                this.getClass().getName() + STR_METHOD_NAME,
                l_strMessage);
        }
        TradingModule l_tradingModule = l_finApp.getTradingModule(ProductTypeEnum.FOREIGN_EQUITY);//NotInstalledException
        if (l_tradingModule == null)
        {
            String l_strMessage = "TradingModuleが存在しない。";
            log.debug(l_strMessage);
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80002,
                this.getClass().getName() + STR_METHOD_NAME,
                l_strMessage);
        }
        WEB3FeqOrderManager l_orderManager = 
            (WEB3FeqOrderManager)l_tradingModule.getOrderManager();
        if (l_orderManager == null)
        {
            String l_strMessage = "外国株式注文マネージャが存在しない。";
            log.debug(l_strMessage);
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80002,
                this.getClass().getName() + STR_METHOD_NAME,
                l_strMessage);
        }
        l_orderManager.validateExecutedQuantity(this.getOrderUnit(l_intLineNumber), l_dblExecQuantitySum);
        
        log.exiting(STR_METHOD_NAME);
        return null;
    }
    
    /**
     * (get注文単位)<BR>
     * 運用コードに該当する注文単位オブジェクトを取得する。<BR>
     * <BR>
     * 外国株式注文マネージャ.get有効注文単位By運用コード()にて、注文単位オブジェクトを取得する。<BR>
     * <BR>
     * [get有効注文単位By運用コード()に指定する引数]<BR>
     * 発注日：　@TradingSystem.getSystemTimestamp()の日付<BR>
     * 運用コード：　@get運用コード(行番号)<BR>
     * <BR>
     * 取得できなかった場合は、例外をスローする。<BR>
     *   class: WEB3BusinessLayerException<BR>
     *   tag:  BUSINESS_ERROR_02165<BR>
     * @@param l_intLineNumber - (行番号)<BR>
     * 行番号<BR>
     * @@return webbroker3.feq.WEB3FeqOrderUnit
     * @@throws WEB3BaseException
     * @@roseuid 429D6C8C0176
     */
    public WEB3FeqOrderUnit getOrderUnit(int l_intLineNumber) throws WEB3BaseException
    {
        final String STR_METHOD_NAME = " getOrderUnit(int)";
        log.entering(STR_METHOD_NAME);
        
        WEB3FeqOrderUnit l_feqOrderUnit = null;
        
        //get外国株式注文マネージャ
        FinApp l_finApp = (FinApp) Services.getService(FinApp.class);
        if (l_finApp == null)
        {
            String l_strMessage = "FinAppが存在しない。";
            log.debug(l_strMessage);
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80002,
                this.getClass().getName() + STR_METHOD_NAME,
                l_strMessage);
        }
        TradingModule l_tradingModule = l_finApp.getTradingModule(ProductTypeEnum.FOREIGN_EQUITY);//NotInstalledException
        if (l_tradingModule == null)
        {
            String l_strMessage = "TradingModuleが存在しない。";
            log.debug(l_strMessage);
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80002,
                this.getClass().getName() + STR_METHOD_NAME,
                l_strMessage);
        }
        WEB3FeqOrderManager l_orderManager = 
            (WEB3FeqOrderManager)l_tradingModule.getOrderManager();
        if (l_orderManager == null)
        {
            String l_strMessage = "外国株式注文マネージャが存在しない。";
            log.debug(l_strMessage);
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80002,
                this.getClass().getName() + STR_METHOD_NAME,
                l_strMessage);
        }
        
        //外国株式注文マネージャ.get有効注文単位By運用コード()にて、注文単位オブジェクトを取得する。
        TradingSystem l_tradingSystem = l_finApp.getTradingSystem();
        Date l_datBizDate = WEB3DateUtility.toDay(l_tradingSystem.getSystemTimestamp());
        
        l_feqOrderUnit = 
            (WEB3FeqOrderUnit)l_orderManager.getOrderUnitByOrderEmpCode(
                l_datBizDate, 
                this.getOrderEmpCode(l_intLineNumber));//WEB3BaseException        
        
        if (l_feqOrderUnit == null)
        {
            String l_strMessage = "get有効注文単位By運用コード(" 
                + l_datBizDate 
                + ", " 
                + this.getOrderEmpCode(l_intLineNumber) 
                + ")がnull。";
            log.debug(l_strMessage);
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_02165,
                this.getClass().getName() + STR_METHOD_NAME,
                l_strMessage);
        }
        FeqOrderUnitImpl l_feqOrderUnitI = l_feqOrderUnit;
        boolean l_confirmedPrice = l_feqOrderUnitI.isConfirmedPriceMarketOrder();
        if (l_confirmedPrice)
        {
            //1.6.1 例外をスローする
             throw new WEB3BusinessLayerException(
                 WEB3ErrorCatalog.BUSINESS_ERROR_02143,
                 this.getClass().getName() + STR_METHOD_NAME,
                 "注文状態が" + l_feqOrderUnit.getOrderStatus() + "です");
        }
        
        
        log.exiting(STR_METHOD_NAME);
        return l_feqOrderUnit;
    }
}
@
