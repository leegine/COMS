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
filename	WEB3FeqOrderAcceptResultUploadCSV.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : 外国株式注文結果一括入力CSV(WEB3FeqOrderAcceptResultUploadCSV.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2005/07/21 鄭海良(中訊) 新規作成
                   2005/08/03 黄建(中訊) レビュー 
                   2006/09/18 徐大方(中訊) 仕様変更・モデル245,273    
Revesion History : 2009/08/03 車進(中訊) 仕様変更・モデル516
*/

package webbroker3.feq;

import java.util.Date;

import com.fitechlabs.xtrade.kernel.boot.Services;
import com.fitechlabs.xtrade.plugin.tc.gentrade.FinApp;
import com.fitechlabs.xtrade.plugin.tc.gentrade.OrderStatusEnum;
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
import webbroker3.feq.define.WEB3FeqAcceptTypeDef;
import webbroker3.gentrade.WEB3GentradeCsvColumnModel;
import webbroker3.gentrade.WEB3GentradeCsvUploadDataModel;
import webbroker3.util.WEB3DateUtility;
import webbroker3.util.WEB3LogUtility;
import webbroker3.util.WEB3StringTypeUtility;

/**
 * (外国株式注文結果一括入力CSV)<BR>
 * 外国株式注文結果一括入力CSV<BR>
 *
 * @@author 鄭海良
 * @@version 1.0
 */
public class WEB3FeqOrderAcceptResultUploadCSV extends WEB3GentradeCsvUploadDataModel 
{
    
    /**
     * ログ出力ユーティリティ。
     */
    private static WEB3LogUtility log =
            WEB3LogUtility.getInstance(WEB3FeqOrderAcceptResultUploadCSV.class);
    
    /**
     * (運用コードラベル)<BR>
     * 運用コードラベル<BR>
     */
    private static  String EMP_CODE_LABEL = "運用コード";
    
    /**
     * (銘柄コードラベル)<BR>
     * 銘柄コードラベル<BR>
     */
    private static  String PRODUCT_CODE_LABEL = "銘柄コード";
    
    /**
     * (銘柄名ラベル)<BR>
     * 銘柄名ラベル<BR>
     */
    private static  String PRODUCT_NAME_LABEL = "銘柄名";
    
    /**
     * (売買ラベル)<BR>
     * 売買ラベル<BR>
     */
    private static  String TRADE_LABEL = "売買";
    
    /**
     * (注文株数ラベル)<BR>
     * 注文株数ラベル<BR>
     */
    private static  String ORDER_QUANTITY_LABEL = "注文株数";
    
    /**
     * (約定方法@ラベル)<BR>
     * 約定方法@ラベル<BR>
     */
    private static  String EXEC_METHOD_LABEL = "約定方法@";
    
    /**
     * (注文単価ラベル)<BR>
     * 注文単価ラベル<BR>
     */
    private static  String ORDER_PRICE_LABEL = "注文定単価";
    
	/**
	 * (最終項目カンマラベル)<BR>
	 * 最終項目カンマラベル<BR>
	 */
	private static  String LAST_COMMA_LABEL = "最終項目カンマ";

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
     * (約定方法@_L)<BR>
     * 約定方法@_L<BR>
     */
    private static  String EXEC_METHOD_L = "L";
    
    /**
     * (注文単価エラー)<BR>
     * 注文単価エラー（呼び値無効）<BR>
     */
    private static  String ORDER_PRICE_ERROR = "呼び値無効";
    
    /**
     * (注文株数エラー)<BR>
     * 注文株数エラー（端株あり）<BR>
     */
    private static  String ORDER_QUANTITY_ERROR = "端株あり";
    
    /**
     * (アップロードファ@イルＩＤ)<BR>
     * アップロードファ@イルＩＤ<BR>
     */
    private String UPLOAD_FILE_ID = "外国株式注文結果一括入力";
    
    /**
     * (外国株式注文結果一括入力CSV)<BR>
     * コンストラクタ <BR>
     * ※　@アップロード中止の場合に使用する。 <BR>
     * <BR>
     * －引数のアップロードIDをプロパティにセットする。<BR>
     * @@param l_lngUploadId - (アップロードID)<BR>
     * アップロードID<BR>
     * @@roseuid 429FE01C0243
     */
    public WEB3FeqOrderAcceptResultUploadCSV(long l_lngUploadId) 
    {
        this.administratorUploadId = l_lngUploadId;     
    }
    
    /**
     * (外国株式注文結果一括入力CSV)<BR>
     * コンストラクタ <BR>
     * <BR>
     * －this.createカラムヘッダ()をコールする。 <BR>
     * @@roseuid 429FE01C0242
     */
    public WEB3FeqOrderAcceptResultUploadCSV() 
    {
        this.createColumnHeader();     
    }
    
    /**
     * (createカラムヘッダ ())<BR>
     * カラムヘッダをセットする。 <BR>
     * <BR>
     * 　@以下の通りCSVカラムモデルの配列を生成し、setカラムヘッダ()にてインスタンスにセットする。<BR> 
     * <BR>
     * [カラムヘッダ配列] <BR>
     * <BR>
     * －　@index = 0　@※運用コード <BR>
     * 　@[CSVカラムモデル コンストラクタの引数] <BR>
     * 　@項目ラベル：　@外国株式注文結果一括入力CSV.運用コードラベル <BR>
     * 　@カラム番号： 0 <BR>
     * 　@項目型：　@CSVカラムモデル.項目型_文字列 <BR>
     * 　@日付フォーマット：　@null <BR>
     * <BR>
     * －　@index = 1　@※銘柄コード <BR>
     * 　@[CSVカラムモデル コンストラクタの引数] <BR>
     * 　@項目ラベル：　@外国株式注文結果一括入力CSV.銘柄コードラベル <BR>
     * 　@カラム番号： 1 <BR>
     * 　@項目型：　@CSVカラムモデル.項目型_文字列 <BR>
     * 　@日付フォーマット：　@null <BR>
     * <BR>
     * －　@index = 2　@※銘柄名<BR>
     * 　@[CSVカラムモデル コンストラクタの引数] <BR>
     * 　@項目ラベル：　@外国株式注文結果一括入力CSV.銘柄名ラベル <BR>
     * 　@カラム番号： 2<BR>
     * 　@項目型：　@CSVカラムモデル.項目型_文字列 <BR>
     * 　@日付フォーマット：　@null <BR>
     * <BR>
     * －　@index = 3　@※売買 <BR>
     * 　@[CSVカラムモデル コンストラクタの引数] <BR>
     * 　@項目ラベル：　@外国株式注文結果一括入力CSV.売買ラベル <BR>
     * 　@カラム番号： 3 <BR>
     * 　@項目型：　@CSVカラムモデル.項目型_文字列 <BR>
     * 　@日付フォーマット：　@null <BR>
     * <BR>
     * －　@index = 4　@※注文株数<BR>
     * 　@[CSVカラムモデル コンストラクタの引数] <BR>
     * 　@項目ラベル：　@外国株式注文結果一括入力CSV.t注文株数ラベル <BR>
     * 　@カラム番号： 4<BR>
     * 　@項目型：　@CSVカラムモデル.項目型_文字列 <BR>
     * 　@日付フォーマット：　@null <BR>
     * <BR>
     * －　@index = 5　@※約定方法@<BR>
     * 　@[CSVカラムモデル コンストラクタの引数] <BR>
     * 　@項目ラベル：　@外国株式注文結果一括入力CSV.約定方法@ラベル <BR>
     * 　@カラム番号： 5 <BR>
     * 　@項目型：　@CSVカラムモデル.項目型_文字列<BR>
     * 　@日付フォーマット：　@null <BR>
     * <BR>
     * －　@index = 6　@※約定単価<BR>
     * 　@[CSVカラムモデル コンストラクタの引数] <BR>
     * 　@項目ラベル：　@外国株式注文結果一括入力CSV.約定単価ラベル <BR>
     * 　@カラム番号： 6<BR>
     * 　@項目型：　@CSVカラムモデル.項目型_文字列 <BR>
     * 　@日付フォーマット：　@null <BR>
     * <BR>
     * －　@index = 7　@※最終項目カンマ<BR>
     * 　@[CSVカラムモデル コンストラクタの引数] <BR>
     * 　@項目ラベル：　@外国株式注文結果一括入力CSV.最終項目カンマラベル <BR>
     * 　@カラム番号： 7<BR>
     * 　@項目型：　@CSVカラムモデル.項目型_文字列 <BR>
     * 　@日付フォーマット：　@null <BR>
     * @@roseuid 429FE01C0252
     */
    protected void createColumnHeader() 
    {
        final String STR_METHOD_NAME = " createColumnHeader()";
        log.entering(STR_METHOD_NAME);
        
        final int COLUMN_MAX = 8;
        WEB3GentradeCsvColumnModel[] l_models = new WEB3GentradeCsvColumnModel[COLUMN_MAX];

        //index 0 運用コード
        l_models[0] = new WEB3GentradeCsvColumnModel(
            EMP_CODE_LABEL,
            0,
            WEB3GentradeCsvColumnModel.STRINGTYPE,
            null);
        
        //index 1 銘柄コード
        l_models[1] = new WEB3GentradeCsvColumnModel(
            PRODUCT_CODE_LABEL,
            1,
            WEB3GentradeCsvColumnModel.STRINGTYPE,
            null);
        
        //index 2 銘柄名
        l_models[2] = new WEB3GentradeCsvColumnModel(
            PRODUCT_NAME_LABEL,
            2,
            WEB3GentradeCsvColumnModel.STRINGTYPE,
            null);
        
        //index 3 売買
        l_models[3] = new WEB3GentradeCsvColumnModel(
            TRADE_LABEL,
            3,
            WEB3GentradeCsvColumnModel.STRINGTYPE,
            null);
        
        //index 4 注文株数
        l_models[4] = new WEB3GentradeCsvColumnModel(
            ORDER_QUANTITY_LABEL,
            4,
            WEB3GentradeCsvColumnModel.STRINGTYPE,
            null);
        
        //index 5 約定方法@
        l_models[5] = new WEB3GentradeCsvColumnModel(
            EXEC_METHOD_LABEL,
            5,
            WEB3GentradeCsvColumnModel.STRINGTYPE,
            null);
        
        //index 6 約定単価
        l_models[6] = new WEB3GentradeCsvColumnModel(
            ORDER_PRICE_LABEL,
            6,
            WEB3GentradeCsvColumnModel.STRINGTYPE,
            null);

		//index 7 最終項目カンマ
		l_models[7] = new WEB3GentradeCsvColumnModel(
			LAST_COMMA_LABEL,
			7,
			WEB3GentradeCsvColumnModel.  STRINGTYPE,
			null);
        
        this.setColumnHeader(l_models);
        
        log.exiting(STR_METHOD_NAME);     
    }
    
    /**
     * (getアップロードファ@イルＩＤ)<BR>
     * アップロードファ@イルＩＤを返却する。<BR>
     * <BR>
     * 外国株式注文結果一括入力.アップロードファ@イルＩＤを返却する。<BR>
     * @@return String
     * @@roseuid 429FE01C0253
     */
    public String getUploadFileId() 
    {
        return this.UPLOAD_FILE_ID;
    }
    
    /**
     * (get銘柄タイプ)<BR>
     * 銘柄タイプ.外国株式を返却する。<BR>
     * @@return com.fitechlabs.xtrade.plugin.tc.gentrade.ProductTypeEnum
     * @@roseuid 42C4E176008F
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
     * カラム：　@getカラムモデル(外国株式注文結果一括入力CSV.運用コードラベル)の戻り値。<BR>
     * @@param l_intLineNumber - (行番号)<BR>
     * 行番号<BR>
     * @@return String
     * @@roseuid 429FE01C0254
     */
    public String getEmpCode(int l_intLineNumber) 
    {
        String l_strEmpCode = 
            (String)this.getValue(l_intLineNumber, this.getColumnModel(EMP_CODE_LABEL));
        return l_strEmpCode;
    }
    
    /**
     * (get銘柄コード)<BR>
     * 行番号に対応する明細行の銘柄コードを取得する。 <BR>
     * <BR>
     * this.get項目値()にて銘柄コードを取得し返却する。<BR> 
     * <BR>
     * [get項目値()に指定する引数] <BR>
     * 行番号：　@行番号 <BR>
     * カラム：　@getカラムモデル(外国株式注文結果一括入力CSV.銘柄コードラベル)の戻り値。<BR>
     * @@param l_intLineNumber - (行番号)<BR>
     * 行番号<BR>
     * @@return String
     * @@roseuid 429FE01C0256
     */
    public String getProductCode(int l_intLineNumber) 
    {
        String l_strProductCode = 
            (String)this.getValue(l_intLineNumber, this.getColumnModel(PRODUCT_CODE_LABEL));
        return l_strProductCode;
    }
    
    /**
     * (get銘柄名)<BR>
     * 行番号に対応する明細行の銘柄コードを取得する。 <BR>
     * <BR>
     * this.get項目値()にて銘柄名を取得し返却する。 <BR>
     * <BR>
     * [get項目値()に指定する引数] <BR>
     * 行番号：　@行番号 <BR>
     * カラム：　@getカラムモデル(外国株式注文結果一括入力CSV.銘柄コードラベル)の戻り値。<BR>
     * @@param l_intLineNumber - (行番号)<BR>
     * 行番号<BR>
     * @@return String
     * @@roseuid 429FE1AE008D
     */
    public String getProductName(int l_intLineNumber) 
    {
        String l_strProductName = 
            (String)this.getValue(l_intLineNumber, this.getColumnModel(PRODUCT_NAME_LABEL));
        return l_strProductName;
    }
    
    /**
     * (get売買)<BR>
     * 行番号に対応する明細行の売買を取得する。 <BR>
     * <BR>
     * this.get項目値()にて売買を取得し返却する。 <BR>
     * <BR>
     * [get項目値()に指定する引数] <BR>
     * 行番号：　@行番号 <BR>
     * カラム：　@getカラムモデル(外国株式注文結果一括入力CSV.売買ラベル)の戻り値。<BR>
     * @@param l_intLineNumber - (行番号)<BR>
     * 行番号<BR>
     * @@return String
     * @@roseuid 429FE01C0258
     */
    public String getTrade(int l_intLineNumber) 
    {
        String l_strTrade = 
            (String)this.getValue(l_intLineNumber, this.getColumnModel(TRADE_LABEL));
        return l_strTrade;
    }
    
    /**
     * (get注文株数)<BR>
     * 行番号に対応する明細行の注文株数を取得する。 <BR>
     * <BR>
     * this.get項目値()にて注文株数を取得し返却する。 <BR>
     * <BR>
     * [get項目値()に指定する引数] <BR>
     * 行番号：　@行番号 <BR>
     * カラム：　@getカラムモデル(外国株式注文結果一括入力CSV.注文株数ラベル)の戻り値。<BR>
     * @@param l_intLineNumber - (行番号)<BR>
     * 行番号<BR>
     * @@return String
     * @@throws WEB3BaseException
     * @@roseuid 429FE01C0261
     */
    public String getOrderQuantity(int l_intLineNumber) throws WEB3BaseException
    {
        String l_strOrderQuantity = 
            (String)this.getValue(l_intLineNumber, this.getColumnModel(ORDER_QUANTITY_LABEL));
        return l_strOrderQuantity;
    }
    
    /**
     * (get約定方法@)<BR>
     * 行番号に対応する明細行の約定方法@を取得する。 <BR>
     * <BR>
     * this.get項目値()にて約定方法@を取得する。<BR> 
     * <BR>
     * [get項目値()に指定する引数] <BR>
     * 行番号：　@行番号 <BR>
     * カラム：　@getカラムモデル(外国株式注文結果一括入力CSV.約定方法@ラベル)の戻り値。<BR>
     * @@param l_intLineNumber - (行番号)<BR>
     * 行番号<BR>
     * @@return String
     * @@roseuid 429FE01C0265
     */
    public String getExecMethod(int l_intLineNumber) 
    {
        String l_strExecMethod = 
            (String)this.getValue(l_intLineNumber, this.getColumnModel(EXEC_METHOD_LABEL));
        return l_strExecMethod;
    }
    
    /**
     * (get注文単価)<BR>
     * 行番号に対応する明細行の注文単価を取得する。 <BR>
     * <BR>
     * this.get項目値()にて注文単価を取得し返却する。 <BR>
     * <BR>
     * [get項目値()に指定する引数] <BR>
     * 行番号：　@行番号 <BR>
     * カラム：　@getカラムモデル(外国株式注文結果一括入力CSV.注文単価ラベル)の戻り値。<BR>
     * @@param l_intLineNumber - (行番号)<BR>
     * 行番号<BR>
     * @@return String
     * @@throws WEB3BaseException
     * @@roseuid 429FE01C0263
     */
    public String getOrderPrice(int l_intLineNumber) throws WEB3BaseException
    {
        String l_strOrderPrice = 
            (String)this.getValue(l_intLineNumber, this.getColumnModel(ORDER_PRICE_LABEL));
        return l_strOrderPrice;
    }
    
    /**
     * (validate明細行)<BR>
     * 明細行のチェックを行う。<BR>
     * <BR>
     * １）　@運用コード，注文単位のチェック <BR>
     * 　@get運用コード()にて、指定行番号のデータを取得しチェックを行う。 <BR>
     * <BR>
     * 　@[get運用コード()に指定する引数] <BR>
     * 　@行番号：　@行番号 <BR>
     * <BR>
     * 　@１－１）　@運用コードが取得できない場合（get運用コード() == null）、例外をスローする。 <BR>
     *   class: WEB3BusinessLayerException<BR>
     *   tag:  BUSINESS_ERROR_02032<BR>
     * <BR>
     * 　@１－２）　@７桁の半角英数字でない場合、例外をスローする。<BR>
     *   class: WEB3BusinessLayerException<BR>
     *   tag:  BUSINESS_ERROR_02033<BR>
     * <BR>
     * 　@１－３）　@左2byteが引数.外国株式運用コード採番区分でない場合、例外をスローする。<BR>
     *   class: WEB3BusinessLayerException<BR>
     *   tag:  BUSINESS_ERROR_03164<BR>
     * <BR>　@
     *   １－４）　@右5byteが数字でない場合、例外をスローする。<BR>
     *   class: WEB3BusinessLayerException<BR>
     *   tag:  BUSINESS_ERROR_02035<BR>
     * <BR>
     * 　@１－５）　@this.get注文単位()　@にて外国株式注文単位オブジェクトを取得する。<BR>
     * 　@　@　@　@　@　@取得できなかった場合、例外をスローする。<BR>
     *   class: WEB3BusinessLayerException<BR>
     *   tag:  BUSINESS_ERROR_02165<BR>
     * <BR>
     * <BR>
     * 　@[get注文単位()に指定する引数]<BR>
     * 　@行番号：　@行番号 <BR>
     * <BR>
     * 　@１－６）　@注文単位のチェック<BR>
     * 　@　@　@出来終了後の注文の場合（１－５）で取得した注文単位.is出来終了() == true）、<BR>
     * 例外をスローする。<BR>
     * <BR>
     *   class: WEB3BusinessLayerException<BR>
     *   tag:  BUSINESS_ERROR_02166<BR>
     * <BR>
     * ２）　@銘柄コードのチェック<BR>
     * 　@get銘柄コード()にて、指定行番号のデータを取得し、<BR>
     * 銘柄コードが取得できた場合のみ以下のチェックを行う。<BR>
     * <BR>
     * 　@[get銘柄コード()に指定する引数]<BR>
     * 　@行番号：　@行番号<BR>
     * <BR>
     * 　@２－１）　@文字数が9byteより大きい場合（銘柄コード.length > 9）は、例外をスローする。 <BR>
     *   class: WEB3BusinessLayerException<BR>
     *   tag:  BUSINESS_ERROR_00439<BR>
     * <BR>
     * 　@２－２）　@注文単位の銘柄と一致しない場合（以下の条件に当てはならない場合）、<BR>
     * 例外をスローする。<BR>
     * <BR>
     *   class: WEB3BusinessLayerException<BR>
     *   tag:  BUSINESS_ERROR_00301<BR>
     * <BR>
     * 　@[注文単位の銘柄と一致する条件]<BR>
     * 　@注文単位.getProduct().銘柄コード == 銘柄コード　@または、<BR>
     * 　@注文単位.getProduct().現地銘柄コード == 銘柄コード<BR>
     * <BR>
     * ３）　@銘柄名のチェック<BR>
     * 　@get銘柄名()にて、指定行番号のデータを取得し、銘柄名が取得できた場合のみ以下のチェックを行う。<BR>
     * <BR>
     * 　@[get銘柄名()に指定する引数]<BR>
     * 　@行番号：　@行番号<BR>
     * <BR>
     * 　@３－１）　@文字数が50byteより大きい場合（銘柄名.length > 50）は、例外をスローする。 <BR>
     * <BR>
     *   class: WEB3BusinessLayerException<BR>
     *   tag:  BUSINESS_ERROR_00441<BR>
     * <BR>
     * ４）　@売買のチェック<BR>
     * 　@get売買()にて、指定行番号のデータを取得し、売買が取得できた場合のみ以下のチェックを行う。<BR>
     * <BR>
     * 　@[get売買()に指定する引数]<BR>
     * 　@行番号：　@行番号 <BR>
     * <BR>
     * 　@４－１）　@売買が、有効なコード値でない場合、例外をスローする。<BR>
     *   class: WEB3BusinessLayerException<BR>
     *   tag:  BUSINESS_ERROR_01403<BR>
     * <BR>
     * 　@[有効コード値]<BR>
     * 　@外国株式約定結果一括入力CSV.売買_売（"S"）または、<BR>
     * 　@外国株式約定結果一括入力CSV.売買_買（"B"）<BR>
     * <BR>
     * 　@４－２）　@注文単位の注文種別と対応する値でない場合、例外をスローする。<BR>
     *   class: WEB3BusinessLayerException<BR>
     *   tag:  BUSINESS_ERROR_02167<BR>
     * <BR>
     * 　@[対応する値]<BR>
     * 　@外国株式約定結果一括入力CSV.売買_買（"B"）⇒注文種別.701：外株買い<BR>
     * 　@外国株式約定結果一括入力CSV.売買_売（"S"）⇒注文種別.702：外株売り<BR>
     * <BR>
     * ５）　@注文株数のチェック<BR>
     * 　@get注文株数()にて、指定行番号のデータを取得しチェックを行う。 <BR>
     * <BR>
     * 　@[get注文株数()に指定する引数]<BR>
     * 　@行番号：　@行番号 <BR>
     * <BR>
     * 　@５－１）　@未入力の場合、例外をスローする。<BR>
     *   class: WEB3BusinessLayerException<BR>
     *   tag:  BUSINESS_ERROR_00126<BR>
     * <BR>
     * 　@５－２）　@（数値の場合）9桁以内の整数値でない場合、例外をスローする。<BR>
     *   class: WEB3BusinessLayerException<BR>
     *   tag:  BUSINESS_ERROR_00903<BR>
     * <BR>
     * 　@５－３）　@（数値でない場合）外国株式注文結果一括入力CSV.注文株数エラーと一致しない場合、<BR>
     * 例外をスローする。<BR>
     * <BR>
     *   class: WEB3BusinessLayerException<BR>
     *   tag:  BUSINESS_ERROR_00901<BR>
     * <BR>
     * ６）　@約定方法@のチェック<BR>
     * 　@get約定方法@()にて、指定行番号のデータを取得し、約定方法@が取得できた場合<BR>
     * のみ以下のチェックを行う。<BR>
     * <BR>
     * 　@[get約定方法@()に指定する引数]<BR>
     * 　@行番号：　@行番号 <BR>
     * <BR>
     * 　@６－１）　@約定方法@が外国株式注文結果一括入力CSV.約定方法@_Lと一致しない場合、<BR>
     * 例外をスローする。<BR>
     * <BR>
     *   class: WEB3BusinessLayerException<BR>
     *   tag:  BUSINESS_ERROR_02168<BR>
     * <BR>
     * ７）　@注文単価のチェック<BR>
     * 　@get注文単価()にて、指定行番号のデータを取得しチェックを行う。 <BR>
     * <BR>
     * 　@[get注文単価()に指定する引数]<BR>
     * 　@行番号：　@行番号 <BR>
     * <BR>
     * 　@７－１）　@未入力の場合、例外をスローする。<BR>
     *   class: WEB3BusinessLayerException<BR>
     *   tag:  BUSINESS_ERROR_02118<BR>
     * <BR>
     * 　@７－２）　@（数値の場合）マイナス値の場合、例外をスローする。<BR>
     *   class: WEB3BusinessLayerException<BR>
     *   tag:  BUSINESS_ERROR_02120<BR>
     * <BR>
     * 　@７－３）　@（数値の場合）数値に変換した時の有効桁数が、整数部６桁，小数部５桁の範囲外であれば、<BR>
     * 例外をスローする。<BR>
     *   class: WEB3BusinessLayerException<BR>
     *   tag:  BUSINESS_ERROR_02093<BR>
     * <BR>
     * 　@７－４）　@（数値でない場合）外国株式注文結果一括入力CSV.注文単価エラーと一致しない場合、<BR>
     * 例外をスローする。<BR>
     *   class: WEB3BusinessLayerException<BR>
     *   tag:  BUSINESS_ERROR_02119<BR>
     * 　@８）　@オンライン中の訂正注文はアップロード対象外
     * 　@　@　@　@注文状態が７：受付済（訂正注文)、８：発注中（訂正注文）、１０：発注済（訂正注文）の場合
     * 例外をスローする。
     *   class: WEB3BusinessLayerException<BR>
     *   tag:  BUSINESS_ERROR_02178
     * <BR>
     * @@param l_intLineNumber - (行番号)<BR>
     * 行番号<BR>
     * @@param l_strFeqOrderEmpCodeManageDiv - (外国株式運用コード採番区分)<BR>
     * 外国株式運用コード採番区分<BR>
     * @@throws WEB3BaseException
     * @@roseuid 429FE01C0267
     */
    public void validateDetailLine(int l_intLineNumber, 
        String l_strFeqOrderEmpCodeManageDiv) throws WEB3BaseException 
    {
        final String STR_METHOD_NAME = " validateDetailLine(int, String)";
        log.entering(STR_METHOD_NAME);
        
        //１）　@運用コード，注文単位のチェック 
        //　@get運用コード()にて、指定行番号のデータを取得しチェックを行う。 
        String l_strEmpCode = this.getEmpCode(l_intLineNumber); 

        //　@１－１）　@運用コードが取得できない場合（get運用コード() == null）、例外をスローする。 
        if (WEB3StringTypeUtility.isEmpty(l_strEmpCode))
        {
            String l_strMessage = "運用コードが未入力です。";
            log.debug(l_strMessage);
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_02032,
                this.getClass().getName() + STR_METHOD_NAME,
                l_strMessage);
        }
        
        //　@１－２）　@７桁の半角英数字でない場合、例外をスローする。
        final int MANAGEMENTCODE_LEN = 7;
        if (!WEB3StringTypeUtility.isLetterAndDigit(l_strEmpCode) 
            || l_strEmpCode.length() != MANAGEMENTCODE_LEN)
        {
            log.debug("運用コード = " + l_strEmpCode);
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_02033,
                getClass().getName() + STR_METHOD_NAME,
                "運用コードが７桁の半角英数字ではありません。");
        }

        //　@１－３）　@左2byteが引数.外国株式運用コード採番区分でない場合、例外をスローする。
        final int LEFT_LEN = 2;
        String l_strLeft2Byte = l_strEmpCode.substring(0, LEFT_LEN);        
        if (!l_strLeft2Byte.equals(l_strFeqOrderEmpCodeManageDiv))
        {
            log.debug("運用コード = " + l_strEmpCode);
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_03164,
                getClass().getName() + STR_METHOD_NAME,
                "運用コードの左2byteが引数.外国株式運用コード採番区分ではありません。");
        }

        //  １－４）　@右5byteが数字でない場合、例外をスローする。
        final int RIGHT_LEN = 5;
        String l_strRight5Byte = l_strEmpCode.substring(
            l_strEmpCode.length() - RIGHT_LEN);
        if (!WEB3StringTypeUtility.isDigit(l_strRight5Byte))
        {
            log.debug("運用コード = " + l_strEmpCode);
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_02035,
                getClass().getName() + STR_METHOD_NAME,
                "運用コードの右5byteが数字ではありません。");
        }

        //　@１－５）　@this.get注文単位()　@にて外国株式注文単位オブジェクトを取得する。
        //　@　@　@　@　@　@取得できなかった場合、例外をスローする。
        WEB3FeqOrderUnit l_feqOrderUnit = this.getOrderUnit(l_intLineNumber);
        if (l_feqOrderUnit == null)
        {
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_02165,
                this.getClass().getName() + STR_METHOD_NAME,
                "外国株式注文単位オブジェクトが取得できません。");
        }

        //　@１－６）　@注文単位のチェック
        //　@　@　@出来終了後の注文の場合（１－５）で取得した注文単位.is出来終了() == true）、
        //例外をスローする。
        if (l_feqOrderUnit.isExecEnd())
        {
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_02166,
                getClass().getName() + STR_METHOD_NAME,
                "出来終了後の注文は、注文受付結果アップロード不可です。");
        }

        //２）　@銘柄コードのチェック
        //　@get銘柄コード()にて、指定行番号のデータを取得し、
        //銘柄コードが取得できた場合のみ以下のチェックを行う。
        String l_strProductCode = this.getProductCode(l_intLineNumber);
        if (!WEB3StringTypeUtility.isEmpty(l_strProductCode))
        {
            //　@２－１）　@文字数が9byteより大きい場合（銘柄コード.length > 9）は、例外をスローする。 
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
            
            //　@２－２）　@注文単位の銘柄と一致しない場合（以下の条件に当てはならない場合）、
            //例外をスローする。
            FeqProduct l_feqProduct = (FeqProduct)l_feqOrderUnit.getProduct();
            if (l_feqProduct == null)
            {
                String l_strMessage = "外国株式銘柄が存在しない。";
                log.debug(l_strMessage);
                log.exiting(STR_METHOD_NAME);
                throw new WEB3SystemLayerException(
                    WEB3ErrorCatalog.SYSTEM_ERROR_80005,
                    this.getClass().getName() + STR_METHOD_NAME,
                    l_strMessage);
            }
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

        //３）　@銘柄名のチェック
        //　@get銘柄名()にて、指定行番号のデータを取得し、銘柄名が取得できた場合のみ以下のチェックを行う。
        String l_strProductName = this.getProductName(l_intLineNumber);
        if (l_strProductName != null)
        {
            //　@３－１）　@文字数が50byteより大きい場合（銘柄名.length > 50）は、例外をスローする。
            if (WEB3StringTypeUtility.getByteLength(l_strProductName) > 50)
            { 
                String l_strMessage = "銘柄名エラー「" + l_strProductName + "」";
                log.debug(l_strMessage);
                log.exiting(STR_METHOD_NAME);
                throw new WEB3BusinessLayerException(
                    WEB3ErrorCatalog.BUSINESS_ERROR_00441,
                    this.getClass().getName() + STR_METHOD_NAME,
                    l_strMessage);
            }
        }
        
        //４）　@売買のチェック
        //　@get売買()にて、指定行番号のデータを取得し、売買が取得できた場合のみ以下のチェックを行う。
        String l_strTrade = this.getTrade(l_intLineNumber);
        if (l_strTrade != null)
        {
            //　@４－１）　@売買が、有効なコード値でない場合、例外をスローする。
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

            //　@４－２）　@注文単位の注文種別と対応する値でない場合、例外をスローする。
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
        
        //５）　@注文株数のチェック
        //　@get注文株数()にて、指定行番号のデータを取得しチェックを行う。 
        String l_strOrderQuantity = this.getOrderQuantity(l_intLineNumber);

        //　@５－１）　@未入力の場合、例外をスローする。
        if (WEB3StringTypeUtility.isEmpty(l_strOrderQuantity))
        {
            String l_strMessage = "注文株数エラー「" + l_strOrderQuantity + "」";
            log.debug(l_strMessage);
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_00126,
                this.getClass().getName() + STR_METHOD_NAME,
                l_strMessage);
        }
        
        if (WEB3StringTypeUtility.isNumber(l_strOrderQuantity))
        {
            //　@５－２）　@（数値の場合）9桁以内の整数値でない場合、例外をスローする。
            double l_dblOrderQuantity = Double.parseDouble(l_strOrderQuantity);
            if (!WEB3StringTypeUtility.isInteger(l_strOrderQuantity)
                || l_dblOrderQuantity > 999999999)
            {
                String l_strMessage = "注文株数エラー「" + l_dblOrderQuantity + "」";
                log.debug(l_strMessage);
                log.exiting(STR_METHOD_NAME);
                throw new WEB3BusinessLayerException(
                    WEB3ErrorCatalog.BUSINESS_ERROR_00903,
                    this.getClass().getName() + STR_METHOD_NAME,
                    l_strMessage);
            }
            // ０以下の場合
			else if (!WEB3StringTypeUtility.isInteger(l_strOrderQuantity)
				|| l_dblOrderQuantity <= 0)
			{
				String l_strMessage = "注文株数エラー「" + l_dblOrderQuantity + "」";
				log.debug(l_strMessage);
				log.exiting(STR_METHOD_NAME);
				throw new WEB3BusinessLayerException(
					WEB3ErrorCatalog.BUSINESS_ERROR_00709,
					this.getClass().getName() + STR_METHOD_NAME,
					l_strMessage);
			}

        }
        else
        {
            //　@５－３）　@（数値でない場合）外国株式注文結果一括入力CSV.注文株数エラーと一致しない場合、
            //例外をスローする。
            if (!ORDER_QUANTITY_ERROR.equals(l_strOrderQuantity))
            {
                String l_strMessage = "注文株数か数値以外の値です。「" + l_strOrderQuantity + "」";
                log.debug(l_strMessage);
                log.exiting(STR_METHOD_NAME);
                throw new WEB3BusinessLayerException(
                    WEB3ErrorCatalog.BUSINESS_ERROR_00901,
                    this.getClass().getName() + STR_METHOD_NAME,
                    l_strMessage);
            }
        }

        //６）　@約定方法@のチェック
        //　@get約定方法@()にて、指定行番号のデータを取得し、約定方法@が取得できた場合
        //のみ以下のチェックを行う。
        String l_strExecMethod = this.getExecMethod(l_intLineNumber);
        
        if (l_strExecMethod != null)
        {
            //　@６－１）　@約定方法@が外国株式注文結果一括入力CSV.約定方法@_Lと一致しない場合、
            //例外をスローする。
            if (!EXEC_METHOD_L.equals(l_strExecMethod))
            {
                String l_strMessage = "約定方法@エラー。「" + l_strExecMethod + "」";
                log.debug(l_strMessage);
                log.exiting(STR_METHOD_NAME);
                throw new WEB3BusinessLayerException(
                    WEB3ErrorCatalog.BUSINESS_ERROR_02168,
                    this.getClass().getName() + STR_METHOD_NAME,
                    l_strMessage);
            }
        }
        
        //７）　@注文単価のチェック
        //　@get注文単価()にて、指定行番号のデータを取得しチェックを行う。
        String l_strOrderPrice = this.getOrderPrice(l_intLineNumber);
         
        //　@７－１）　@未入力の場合、例外をスローする。
        if (WEB3StringTypeUtility.isEmpty(l_strOrderPrice))
        {
            String l_strMessage = "注文単価が未入力です";
            log.debug(l_strMessage);
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_02118,
                this.getClass().getName() + STR_METHOD_NAME,
                l_strMessage);
        }
        
        if (WEB3StringTypeUtility.isNumber(l_strOrderPrice))
        {
            //　@７－２）　@（数値の場合）マイナス値の場合、例外をスローする。
            if (WEB3StringTypeUtility.isMinus(l_strOrderPrice))
            {
                String l_strMessage = "注文単価が0以下の値です。";
                log.debug(l_strMessage);
                log.exiting(STR_METHOD_NAME);
                throw new WEB3BusinessLayerException(
                    WEB3ErrorCatalog.BUSINESS_ERROR_02120,
                    this.getClass().getName() + STR_METHOD_NAME,
                    l_strMessage);
            }
            
            //　@７－３）　@（数値の場合）数値に変換した時の有効桁数が、整数部６桁，小数部５桁の範囲外であれば、
            //例外をスローする。
            if (WEB3StringTypeUtility.getIntegerDigits(l_strOrderPrice) > 6 
                || WEB3StringTypeUtility.getFractionDigits(l_strOrderPrice) > 5)
            {
                String l_strMessage = "注文単価エラー。";
                log.debug(l_strMessage);
                log.exiting(STR_METHOD_NAME);
                throw new WEB3BusinessLayerException(
                    WEB3ErrorCatalog.BUSINESS_ERROR_02093,
                    this.getClass().getName() + STR_METHOD_NAME,
                    l_strMessage);
            }
        }
        else
        {
            //　@７－４）　@（数値でない場合）外国株式注文結果一括入力CSV.注文単価エラーと一致しない場合、
            //例外をスローする。
            if (!ORDER_PRICE_ERROR.equals(l_strOrderPrice))
            {
                String l_strMessage = "注文単価エラー「" + l_strOrderPrice + "」。";
                log.debug(l_strMessage);
                log.exiting(STR_METHOD_NAME);
                throw new WEB3BusinessLayerException(
                    WEB3ErrorCatalog.BUSINESS_ERROR_02119,
                    this.getClass().getName() + STR_METHOD_NAME,
                    l_strMessage);
            }
        }
        
        // ８）オンライン中の訂正注文は対象外
        OrderStatusEnum l_orderStatus = l_feqOrderUnit.getOrderStatus();
        FeqOrderUnitImpl l_feqOrderUnitI = l_feqOrderUnit;
        boolean l_confirmedPrice = l_feqOrderUnitI.isConfirmedPriceMarketOrder();
        if (OrderStatusEnum.MODIFY_ACCEPTED.equals(l_orderStatus) |
            OrderStatusEnum.MODIFYING.equals(l_orderStatus) |
           (OrderStatusEnum.MODIFIED.equals(l_orderStatus) &&
            !l_confirmedPrice))
        {
            log.debug("該当する注文IDデータは対象外です。");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_02178,
                this.getClass().getName() + "." + STR_METHOD_NAME);
        }
        
        log.exiting(STR_METHOD_NAME);     
    }
    
    /**
     * (get注文単位)<BR>
     * 運用コードに該当する注文単位オブジェクトを取得する。<BR>
     * <BR>
     * 外国株式注文マネージャ.get有効注文単位By運用コード()にて、<BR>
     * 注文単位オブジェクトを取得する。<BR>
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
     * @@roseuid 429FE01C0269
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
            (WEB3FeqOrderUnit)l_orderManager.getValidOrderUnitByOrderEmpCode(
                l_datBizDate, 
                this.getEmpCode(l_intLineNumber));//WEB3BaseException
        if (l_feqOrderUnit == null)
        {
            String l_strMessage = "get有効注文単位By運用コード(" 
                + l_datBizDate 
                + ", " 
                + this.getEmpCode(l_intLineNumber) 
                + ")がnull。";
            log.debug(l_strMessage);
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_02165,
                this.getClass().getName() + STR_METHOD_NAME,
                l_strMessage);
        }
        
        log.exiting(STR_METHOD_NAME);
        return l_feqOrderUnit;
    }   

	/**
	 * (add明細行) <BR>
	 * （addRow）<BR>
	 * <BR>
	 * 引数の明細行文字列が全て空文字かチェックし、空文字の場合は-1を返却し、
	 * 空文字でない場合はsuper.add明細行をコールし、その結果を返却する。
	 * <BR>
	 * 1）明細行解析<BR>
	 * 　@明細行文字列.split(CSVデータモデル.区切り文字)にて、<BR>
	 * 区切り文字ごとのtoken[]に分割する。<BR>
	 * <BR>
	 * 　@token.lengthが0の場合は-1を返却する。<BR>
	 * <BR>
	 * 　@token.length!=0の場合、super.add明細行をコールする。<BR>
	 * @@param l_rowString - 明細行文字列<BR>
	 * <BR>
	 * @@return int
	 * @@roseuid 40F4EFBD0354
	 */
	public int addRow(String l_rowString) throws WEB3SystemLayerException
	{
		final String STR_METHOD_NAME = "addRow(String)";
		log.entering(STR_METHOD_NAME);       
        
		//1）　@明細行解析
		//明細行文字列.split(CSVデータモデル.区切り文字)にて、
		//区切り文字ごとのtoken[]に分割する。
		String[] token = l_rowString.split(regex);
		// 明細行の項目全てが空の場合　@-1を返却する。
		if (token.length == 0)
		{
				return -1;
		}
		else
		{
			 int l_intLineNumbersuper = super.addRow(l_rowString);
			 return l_intLineNumbersuper;
		}
	}
    
    /**
     * (get受付区分) <BR>
     * 受付区分を返却する。<BR>
     * <BR>
     * １）単価エラー（this.get注文単価(行番号) == 外国株式注文結果一括入力CSV.注文単価エラー）または、 
     * 株数エラー（this.get注文株数(行番号) == 外国株式注文結果一括入力CSV.注文株数エラー）の場合 
     * <BR>
     * 　@02（注文受付エラー）を返却する。<BR>
     * <BR>
     * ２）上記以外の場合、<BR>
     * <BR>
     * 　@01（注文受付済）を返却する。<BR>
     * <BR>
     * @@param l_int - (行番号)<BR>
     * 行番号<BR>
     * @@return String
     * @@throws WEB3BaseException 
     * @@roseuid 40F4EFBD0354
     */
    public String getAcceptDiv(int l_intLineNo) throws WEB3BaseException
    {
        final String STR_METHOD_NAME = "getAcceptDiv(int)";
        log.entering(STR_METHOD_NAME);       

        if (ORDER_PRICE_ERROR.equals(this.getOrderPrice(l_intLineNo)) 
            || ORDER_QUANTITY_ERROR.equals(this.getOrderQuantity(l_intLineNo)))
        {
            log.exiting(STR_METHOD_NAME);
            return WEB3FeqAcceptTypeDef.ORDER_ACCEPT_ERROR;
        }
        else
        {
            log.exiting(STR_METHOD_NAME);
            return WEB3FeqAcceptTypeDef.ORDER_ACCEPT_COMPLETE;
        }
    }
}
@
