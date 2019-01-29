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
filename	WEB3FeqOpenAtOrderDLCSV.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : 寄付注文ダウンロードCSV(WEB3FeqOpenAtOrderDLCSV.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2005/07/20 戴義波 (中訊) 新規作成
                   2005/08/03 黄建(中訊) レビュー     
*/

package webbroker3.feq;

import java.util.ArrayList;
import java.util.List;

import com.fitechlabs.xtrade.kernel.boot.Services;
import com.fitechlabs.xtrade.plugin.tc.gentrade.FinApp;
import com.fitechlabs.xtrade.plugin.tc.gentrade.NotFoundException;
import com.fitechlabs.xtrade.plugin.tc.gentrade.ProductTypeEnum;

import webbroker3.common.WEB3BaseException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.common.WEB3SystemLayerException;
import webbroker3.feq.define.WEB3FeqOpenAtOrderDLDef;
import webbroker3.gentrade.WEB3GentradeCsvColumnModel;
import webbroker3.gentrade.WEB3GentradeCsvDataModel;
import webbroker3.util.WEB3LogUtility;
import webbroker3.util.WEB3StringTypeUtility;


/**
 * (寄付注文ダウンロードCSV)<BR>
 * 寄付注文ダウンロードCSVクラス<BR>
 *   
 * @@author 戴義波
 * @@version 1.0
 */
public class WEB3FeqOpenAtOrderDLCSV extends WEB3GentradeCsvDataModel 
{
    
    /**
     * ログ出力ユーティリティ。
     */
    private static WEB3LogUtility log =
            WEB3LogUtility.getInstance(WEB3FeqOpenAtOrderDLCSV.class);
    
    /**
     * (運用コードラベル)<BR>
     * 運用コードラベル<BR>
     */
    public String ORDER_EMP_CODE_LABEL = "運用コード";
    
    /**
     * (現地銘柄コードラベル)<BR>
     * 現地銘柄コードラベル<BR>
     */
    public String OFFSHORE_PRODUCT_CODE_LABEL = "現地銘柄コード";
    
    /**
     * (銘柄名ラベル)<BR>
     * 銘柄名ラベル<BR>
     */
    public String PRODUCT_NAME_LABEL = "銘柄名";
    
    /**
     * (売買区分ラベル)<BR>
     * 売買区分ラベル<BR>
     * <BR>
     * ＜設定値の説明＞<BR>
     * S：売付<BR>
     * B：買付<BR>
     */
    public String TRADE_DIV_LABEL = "売買区分";
    
    /**
     * (注文株数ラベル)<BR>
     * 注文株数ラベル<BR>
     */
    public String ORDER_QUANTITY_LABEL = "注文株数";
    
    /**
     * (約定方法@ラベル)<BR>
     * 約定方法@ラベル<BR>
     * <BR>
     * ※設定値は、"L"固定<BR>
     */
    public String EXEC_METHOD_LABEL = "約定方法@";
    
    /**
     * (注文単価ラベル)<BR>
     * 注文単価ラベル<BR>
     */
    public String ORDER_PRICE_LABEL = "注文単価";
    
    /**
     * (有効期限ラベル)<BR>
     * 有効期限ラベル<BR>
     * <BR>
     * ※設定値は、null固定<BR>
     */
    public String EXPIRATION_DATE_LABEL = "有効期限";
    
    /**
     * (寄付注文ダウンロードCSV)<BR>
     * コンストラクタ<BR>
     * <BR>
     * インスタンスを生成し、ヘッダ情報をセットする。<BR>
     * <BR>
     * １）super()をコールし、インスタンスを生成する。<BR>
     * <BR>
     * ２）createカラムヘッダ()をコールし、カラムヘッダ情報を生成する。<BR>
     * @@roseuid 42AFFE710176
     */
    public WEB3FeqOpenAtOrderDLCSV() 
    {
        //インスタンスを生成し、ヘッダ情報をセットする。 
        //１）super()をコールし、インスタンスを生成する。
        super();
        
        //２）createカラムヘッダ()をコールし、カラムヘッダ情報を生成する。
        createColumnHeader();
    }
    
    /**
     * (createカラムヘッダ)<BR>
     * カラムヘッダをセットする。<BR>
     * <BR>
     * 以下のとおりにCSVカラムモデルの配列を生成し、<BR>
     * setカラムヘッダ()にてインスタンスにセットする。<BR>
     * <BR>
     * １）運用コード<BR>
     * <BR>
     *    [CSVカラムモデルのコンストラクタの引数]<BR>
     *    項目ラベル： 寄付注文ダウンロードCSV.運用コードラベル<BR>
     *    カラム番号： 0<BR>
     *    項目型： CSVカラムモデル.項目型_文字列<BR>
     *    日付フォーマット： null<BR>
     * <BR>
     * ２）発注銘柄コード<BR>
     * <BR>
     *    [CSVカラムモデルのコンストラクタの引数]<BR>
     *    項目ラベル： 寄付注文ダウンロードCSV.発注銘柄コードラベル<BR>
     *    カラム番号： 1<BR>
     *    項目型： CSVカラムモデル.項目型_文字列<BR>
     *    日付フォーマット： null<BR>
     * <BR>
     * ３）銘柄名<BR>
     * <BR>
     *    [CSVカラムモデルのコンストラクタの引数]<BR>
     *    項目ラベル： 寄付注文ダウンロードCSV.銘柄名ラベル<BR>
     *    カラム番号： 2<BR>
     *    項目型： CSVカラムモデル.項目型_文字列<BR>
     *    日付フォーマット： null<BR>
     * <BR>
     * ４）売買区分<BR>
     * <BR>
     *    [CSVカラムモデルのコンストラクタの引数]<BR>
     *    項目ラベル： 寄付注文ダウンロードCSV.売買区分ラベル<BR>
     *    カラム番号： 3<BR>
     *    項目型： CSVカラムモデル.項目型_文字列<BR>
     *    日付フォーマット： null<BR>
     * <BR>
     * ５）注文株数<BR>
     * <BR>
     *    [CSVカラムモデルのコンストラクタの引数]<BR>
     *    項目ラベル： 寄付注文ダウンロードCSV.注文株数ラベル<BR>
     *    カラム番号： 4<BR>
     *    項目型： CSVカラムモデル.項目型_数値（int）<BR>
     *    日付フォーマット： null<BR>
     * <BR>
     * ６）約定方法@<BR>
     * <BR>
     *    [CSVカラムモデルのコンストラクタの引数]<BR>
     *    項目ラベル： 寄付注文ダウンロードCSV.約定方法@ラベル<BR>
     *    カラム番号： 5<BR>
     *    項目型： CSVカラムモデル.項目型_文字列<BR>
     *    日付フォーマット： null<BR>
     * <BR>
     * ７）注文単価<BR>
     * <BR>
     *    [CSVカラムモデルのコンストラクタの引数]<BR>
     *    項目ラベル： 寄付注文ダウンロードCSV.注文単価ラベル<BR>
     *    カラム番号： 6<BR>
     *    項目型： CSVカラムモデル.項目型_数値（double）<BR>
     *    日付フォーマット： null<BR>
     * <BR>
     * ８）有効期限<BR>
     * <BR>
     *    [CSVカラムモデルのコンストラクタの引数]<BR>
     *    項目ラベル： 寄付注文ダウンロードCSV.有効期限ラベル<BR>
     *    カラム番号： 7<BR>
     *    項目型： CSVカラムモデル.項目型_文字列<BR>
     *    日付フォーマット： null<BR>
     * @@roseuid 42AFFE710186
     */
    protected void createColumnHeader() 
    {
        final String STR_METHOD_NAME = " createColumnHeader()";
        log.entering(STR_METHOD_NAME);

        final int COLUMN_MAX = 8;
        WEB3GentradeCsvColumnModel[] l_models = new WEB3GentradeCsvColumnModel[COLUMN_MAX];
        //以下のとおりにCSVカラムモデルの配列を生成し、setカラムヘッダ()にてインスタンスにセットする。 
        //１）運用コード 
        //
        //   [CSVカラムモデルのコンストラクタの引数] 
        //   項目ラベル： 寄付注文ダウンロードCSV.運用コードラベル 
        //   カラム番号： 0 
        //   項目型： CSVカラムモデル.項目型_文字列 
        //   日付フォーマット： null 
        l_models[0] = new WEB3GentradeCsvColumnModel(
            this.ORDER_EMP_CODE_LABEL,
            0,
            WEB3GentradeCsvColumnModel.STRINGTYPE,
            null);
            
        //２）発注銘柄コード 
        //
        //   [CSVカラムモデルのコンストラクタの引数] 
        //   項目ラベル： 寄付注文ダウンロードCSV.発注銘柄コードラベル 
        //   カラム番号： 1 
        //   項目型： CSVカラムモデル.項目型_文字列 
        //   日付フォーマット： null 
        l_models[1] = new WEB3GentradeCsvColumnModel(
            this.OFFSHORE_PRODUCT_CODE_LABEL,
            1,
            WEB3GentradeCsvColumnModel.STRINGTYPE,
            null);
            
        //３）銘柄名 
        //
        //   [CSVカラムモデルのコンストラクタの引数] 
        //   項目ラベル： 寄付注文ダウンロードCSV.銘柄名ラベル 
        //   カラム番号： 2 
        //   項目型： CSVカラムモデル.項目型_文字列 
        //   日付フォーマット： null 
        l_models[2] = new WEB3GentradeCsvColumnModel(
            this.PRODUCT_NAME_LABEL,
            2,
            WEB3GentradeCsvColumnModel.STRINGTYPE,
            null);
            
        //４）売買区分 
        //
        //   [CSVカラムモデルのコンストラクタの引数] 
        //   項目ラベル： 寄付注文ダウンロードCSV.売買区分ラベル 
        //   カラム番号： 3 
        //   項目型： CSVカラムモデル.項目型_文字列 
        //   日付フォーマット： null 
        l_models[3] = new WEB3GentradeCsvColumnModel(
            this.TRADE_DIV_LABEL,
            3,
            WEB3GentradeCsvColumnModel.STRINGTYPE,
            null);
            
        //５）注文株数 
        //
        //   [CSVカラムモデルのコンストラクタの引数] 
        //   項目ラベル： 寄付注文ダウンロードCSV.注文株数ラベル 
        //   カラム番号： 4 
        //   項目型： CSVカラムモデル.項目型_数値（int） 
        //   日付フォーマット： null 
        l_models[4] = new WEB3GentradeCsvColumnModel(
            this.ORDER_QUANTITY_LABEL,
            4,
            WEB3GentradeCsvColumnModel.INTEGERTYPE,
            null);
            
        //６）約定方法@ 
        //
        //   [CSVカラムモデルのコンストラクタの引数] 
        //   項目ラベル： 寄付注文ダウンロードCSV.約定方法@ラベル 
        //   カラム番号： 5 
        //   項目型： CSVカラムモデル.項目型_文字列 
        //   日付フォーマット： null 
        l_models[5] = new WEB3GentradeCsvColumnModel(
            this.EXEC_METHOD_LABEL,
            5,
            WEB3GentradeCsvColumnModel.STRINGTYPE,
            null);
            
        //７）注文単価 
        //
        //   [CSVカラムモデルのコンストラクタの引数] 
        //   項目ラベル： 寄付注文ダウンロードCSV.注文単価ラベル 
        //   カラム番号： 6 
        //   項目型： CSVカラムモデル.項目型_数値（double） 
        //   日付フォーマット： null 
        l_models[6] = new WEB3GentradeCsvColumnModel(
            this.ORDER_PRICE_LABEL,
            6,
            WEB3GentradeCsvColumnModel.DOUBLETYPE,
            null);
            
        //８）有効期限 
        //
        //   [CSVカラムモデルのコンストラクタの引数] 
        //   項目ラベル： 寄付注文ダウンロードCSV.有効期限ラベル 
        //   カラム番号： 7 
        //   項目型： CSVカラムモデル.項目型_文字列 
        //   日付フォーマット： null 
        l_models[7] = new WEB3GentradeCsvColumnModel(
            this.EXPIRATION_DATE_LABEL,
            7,
            WEB3GentradeCsvColumnModel.STRINGTYPE,
            null);
        
        setColumnHeader(l_models);    
        
        log.exiting(STR_METHOD_NAME);
    }

    /**
     * (set運用コード)<BR>
     * １）カラムモデル取得<BR>
     * <BR>
     *    this.getカラムモデル()にてCSVカラムモデルを取得する。<BR>
     * <BR>
     *    [引数]<BR>
     *    項目ラベル： 寄付注文ダウンロードCSV.運用コードラベル<BR>
     * <BR>
     * ２）値セット<BR>
     * <BR>
     *    this.set項目値()にて項目値をセットする。<BR>
     * <BR>
     *    [引数]<BR>
     *    行番号： 引数の行番号<BR>
     *    カラム： １）で取得したカラムモデル<BR>
     *    値： 引数.運用コード<BR>
     * @@param l_intLineNumber - (行番号)<BR>
     * 行番号<BR>
     * 
     * @@param l_strOrderEmpCode - (運用コード)<BR>
     * 運用コード<BR>
     * @@roseuid 42AFF2CA005D
     */
    public void setOrderEmpCode(int l_intLineNumber, String l_strOrderEmpCode) 
    {
        //    １）カラムモデル取得 
        //    this.getカラムモデル()にてCSVカラムモデルを取得する。 
        //    [引数] 
        //    項目ラベル： 寄付注文ダウンロードCSV.運用コードラベル 
        // ２）値セット 
        //    this.set項目値()にて項目値をセットする。 
        //    [引数] 
        //    行番号： 引数の行番号 
        //    カラム： １）で取得したカラムモデル 
        //    値： 引数.運用コード      
        this.setValue(
            l_intLineNumber,
            getColumnModel(this.ORDER_EMP_CODE_LABEL),
            l_strOrderEmpCode);
    }
    
    /**
     * (set現地銘柄コード)<BR>
     * １）カラムモデル取得<BR>
     * <BR>
     *    this.getカラムモデル()にてCSVカラムモデルを取得する。<BR>
     * <BR>
     *    [引数]<BR>
     *    項目ラベル： 寄付注文ダウンロードCSV.現地銘柄コードラベル<BR>
     * <BR>
     * ２）値セット<BR>
     * <BR>
     *    this.set項目値()にて項目値をセットする。<BR>
     * <BR>
     *    [引数]<BR>
     *    行番号： 引数の行番号<BR>
     *    カラム： １）で取得したカラムモデル<BR>
     *    値： 引数.銘柄IDに該当する外国株式銘柄.get現地銘柄コード()の戻り値<BR>
     * @@param l_intLineNumber - (行番号)<BR>
     * 行番号<BR>
     * 
     * @@param l_lngProductId - (銘柄ID)<BR>
     * 銘柄ID<BR>
     * @@throws WEB3BaseException
     * @@roseuid 42AFF2D40260
     */
    public void setOffshoreProductCode(int l_intLineNumber, long l_lngProductId) 
        throws WEB3BaseException
    {
        final String STR_METHOD_NAME = "setOffshoreProductCode()";
        log.entering(STR_METHOD_NAME);

        //    １）カラムモデル取得 
        //    this.getカラムモデル()にてCSVカラムモデルを取得する。 
        //    [引数] 
        //    項目ラベル： 寄付注文ダウンロードCSV.現地銘柄コードラベル 
        // ２）値セット 
        //    this.set項目値()にて項目値をセットする。 
        //    [引数] 
        //    行番号： 引数の行番号 
        //    カラム： １）で取得したカラムモデル 
        //    値： 引数.銘柄IDに該当する外国株式銘柄.get現地銘柄コード()の戻り値
        FinApp l_finApp = (FinApp) Services.getService(FinApp.class);
        WEB3FeqProductManager l_productManager = 
            (WEB3FeqProductManager) l_finApp.getTradingModule(
                ProductTypeEnum.FOREIGN_EQUITY).getProductManager();
                
        WEB3FeqProduct l_feqProduct = null;

        try 
        {
            l_feqProduct = (WEB3FeqProduct) l_productManager.getProduct(l_lngProductId);
        } 
        catch (NotFoundException l_ex) 
        {
            String l_strMessage = "部店インスタンスを取得しない";
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80005, 
                this.getClass().getName() + "." + STR_METHOD_NAME, 
                l_strMessage, 
                l_ex);
            
        }

        this.setValue(
            l_intLineNumber,
            getColumnModel(this.OFFSHORE_PRODUCT_CODE_LABEL),
            l_feqProduct.getOffshoreProductCode());

        log.exiting(STR_METHOD_NAME);
    }
    
    /**
     * (set銘柄名)<BR>
     * １）カラムモデル取得<BR>
     * <BR>
     *    this.getカラムモデル()にてCSVカラムモデルを取得する。<BR>
     * <BR>
     *    [引数]<BR>
     *    項目ラベル： 寄付注文ダウンロードCSV.銘柄名ラベル<BR>
     * <BR>
     * ２）値セット<BR>
     * <BR>
     *    this.set項目値()にて項目値をセットする。<BR>
     * <BR>
     *    [引数]<BR>
     *    行番号： 引数の行番号<BR>
     *    カラム： １）で取得したカラムモデル<BR>
     *    値： null<BR>
     * @@param l_intLineNumber - (行番号)<BR>
     * 行番号<BR>
     * @@roseuid 42AFF2DA035A
     */
    public void setProductName(int l_intLineNumber) 
    {
        //    １）カラムモデル取得 
        //    this.getカラムモデル()にてCSVカラムモデルを取得する。 
        //    [引数] 
        //    項目ラベル： 寄付注文ダウンロードCSV.銘柄名ラベル 
        // ２）値セット 
        //    this.set項目値()にて項目値をセットする。 
        //    [引数] 
        //    行番号： 引数の行番号 
        //    カラム： １）で取得したカラムモデル 
        //    値： null
        this.setValue(
            l_intLineNumber,
            getColumnModel(this.PRODUCT_NAME_LABEL),
            null);
    }
    
    /**
     * (set売買区分)<BR>
     * １）カラムモデル取得<BR>
     * <BR>
     *    this.getカラムモデル()にてCSVカラムモデルを取得する。<BR>
     * <BR>
     *    [引数]<BR>
     *    項目ラベル： 寄付注文ダウンロードCSV.売買区分ラベル<BR>
     * <BR>
     * ２）値セット<BR>
     * <BR>
     *    this.set項目値()にて項目値をセットする。<BR>
     * <BR>
     *    [引数]<BR>
     *    行番号： 引数の行番号<BR>
     *    カラム： １）で取得したカラムモデル<BR>
     *    値： 引数.is買付 == true の場合、"B"<BR>
     *          引数.is買付 == false の場合、"S"<BR>
     * @@param l_intLineNumber - (行番号)<BR>
     * 行番号<BR>
     * 
     * @@param l_blnIsBuy - (is買付)<BR>
     * 買付注文かどうかのフラグ<BR>
     * @@roseuid 42AFF2E002BE
     */
    public void setOrderType(int l_intLineNumber, boolean l_blnIsBuy) 
    {
        //    １）カラムモデル取得 
        //    this.getカラムモデル()にてCSVカラムモデルを取得する。 
        //    [引数] 
        //    項目ラベル： 寄付注文ダウンロードCSV.売買区分ラベル 
        // ２）値セット 
        //    this.set項目値()にて項目値をセットする。 
        //    [引数] 
        //    行番号： 引数の行番号 
        //    カラム： １）で取得したカラムモデル 
        //    値： 引数.is買付 == true の場合、"B" 
        //          引数.is買付 == false の場合、"S" 
        this.setValue(
            l_intLineNumber,
            getColumnModel(this.TRADE_DIV_LABEL),
            l_blnIsBuy ? WEB3FeqOpenAtOrderDLDef.BUY : WEB3FeqOpenAtOrderDLDef.SELL);
    }
    
    /**
     * (set注文株数)<BR>
     * １）カラムモデル取得<BR>
     * <BR>
     *    this.getカラムモデル()にてCSVカラムモデルを取得する。<BR>
     * <BR>
     *    [引数]<BR>
     *    項目ラベル： 寄付注文ダウンロードCSV.注文株数ラベル<BR>
     * <BR>
     * ２）値セット<BR>
     * <BR>
     *    this.set項目値()にて項目値をセットする。<BR>
     * <BR>
     *    [引数]<BR>
     *    行番号： 引数の行番号<BR>
     *    カラム： １）で取得したカラムモデル<BR>
     *    値： 引数.注文株数<BR>
     * @@param l_intLineNumber - (行番号)<BR>
     * 行番号<BR>
     * 
     * @@param l_lngOrderQuantity - (注文株数)<BR>
     * 注文株数<BR>
     * @@roseuid 42AFF2FA01F3
     */
    public void setOrderQuantity(int l_intLineNumber, long l_lngOrderQuantity) 
    {
        //    １）カラムモデル取得 
        //    this.getカラムモデル()にてCSVカラムモデルを取得する。 
        //    [引数] 
        //    項目ラベル： 寄付注文ダウンロードCSV.注文株数ラベル 
        // ２）値セット 
        //    this.set項目値()にて項目値をセットする。 
        //    [引数] 
        //    行番号： 引数の行番号 
        //    カラム： １）で取得したカラムモデル 
        //    値： 引数.注文株数 
        this.setValue(
            l_intLineNumber,
            getColumnModel(this.ORDER_QUANTITY_LABEL),
            new Long(l_lngOrderQuantity));
    }
    
    /**
     * (set約定方法@)<BR>
     * １）カラムモデル取得<BR>
     * <BR>
     *    this.getカラムモデル()にてCSVカラムモデルを取得する。<BR>
     * <BR>
     *    [引数]<BR>
     *    項目ラベル： 寄付注文ダウンロードCSV.約定方法@ラベル<BR>
     * <BR>
     * ２）値セット<BR>
     * <BR>
     *    this.set項目値()にて項目値をセットする。<BR>
     * <BR>
     *    [引数]<BR>
     *    行番号： 引数の行番号<BR>
     *    カラム： １）で取得したカラムモデル<BR>
     *    値： "L"<BR>
     * @@param l_intLineNumber - (行番号)<BR>
     * 行番号<BR>
     * @@roseuid 42AFF30E033B
     */
    public void setExecMethod(int l_intLineNumber) 
    {
        //    １）カラムモデル取得 
        //    this.getカラムモデル()にてCSVカラムモデルを取得する。 
        //    [引数] 
        //    項目ラベル： 寄付注文ダウンロードCSV.約定方法@ラベル 
        // ２）値セット 
        //    this.set項目値()にて項目値をセットする。 
        //    [引数] 
        //    行番号： 引数の行番号 
        //    カラム： １）で取得したカラムモデル 
        //    値： "L" 
        this.setValue(
            l_intLineNumber,
            getColumnModel(this.EXEC_METHOD_LABEL),
            WEB3FeqOpenAtOrderDLDef.EXECUTE_METHOD);
    }
    
    /**
     * (set注文単価)<BR>
     * １）カラムモデル取得<BR>
     * <BR>
     *    this.getカラムモデル()にてCSVカラムモデルを取得する。<BR>
     * <BR>
     *    [引数]<BR>
     *    項目ラベル： 寄付注文ダウンロードCSV.注文単価ラベル<BR>
     * <BR>
     * ２）値セット<BR>
     * <BR>
     *    this.set項目値()にて項目値をセットする。<BR>
     * <BR>
     *    [引数]<BR>
     *    行番号： 引数の行番号<BR>
     *    カラム： １）で取得したカラムモデル<BR>
     *    値： 引数.指値<BR>
     * @@param l_intLineNumber - (行番号)<BR>
     * 行番号<BR>
     * 
     * @@param l_lngLimitPrice - (指値)<BR>
     * 指値<BR>
     * @@roseuid 42AFF301028F
     */
    public void setOrderPrice(int l_intLineNumber, double l_lngLimitPrice) 
    {
        //    １）カラムモデル取得 
        //    this.getカラムモデル()にてCSVカラムモデルを取得する。 
        //    [引数] 
        //    項目ラベル： 寄付注文ダウンロードCSV.注文単価ラベル 
        // ２）値セット 
        //    this.set項目値()にて項目値をセットする。 
        //    [引数] 
        //    行番号： 引数の行番号 
        //    カラム： １）で取得したカラムモデル 
        //    値： 引数.指値 
        this.setValue(
            l_intLineNumber,
            getColumnModel(this.ORDER_PRICE_LABEL),
            new Double(l_lngLimitPrice));
    }
    
    /**
     * (set有効期限)<BR>
     * １）カラムモデル取得<BR>
     * <BR>
     *    this.getカラムモデル()にてCSVカラムモデルを取得する。<BR>
     * <BR>
     *    [引数]<BR>
     *    項目ラベル： 寄付注文ダウンロードCSV.有効期限ラベル<BR>
     * <BR>
     * ２）値セット<BR>
     * <BR>
     *    this.set項目値()にて項目値をセットする。<BR>
     * <BR>
     *    [引数]<BR>
     *    行番号： 引数の行番号<BR>
     *    カラム： １）で取得したカラムモデル<BR>
     *    値： null<BR>
     * @@param l_intLineNumber - (行番号)<BR>
     * 行番号<BR>
     * @@roseuid 42AFF3340118
     */
    public void setExpirationDate(int l_intLineNumber) 
    {
        //    １）カラムモデル取得 
        //    this.getカラムモデル()にてCSVカラムモデルを取得する。 
        //    [引数] 
        //    項目ラベル： 寄付注文ダウンロードCSV.有効期限ラベル 
        // ２）値セット 
        //    this.set項目値()にて項目値をセットする。 
        //    [引数] 
        //    行番号： 引数の行番号 
        //    カラム： １）で取得したカラムモデル 
        //    値： null 
        this.setValue(
            l_intLineNumber,
            getColumnModel(this.EXPIRATION_DATE_LABEL),
            null);
    }
    
    /**
     * (getCSVファ@イル行)<BR>
     * （getCSVファ@イル行のオーバーライド）<BR>
     * <BR>
     * CSVファ@イルに出力するデータを、行毎の配列にて返却する。<BR>
     * <BR>
     * １）スーパークラスのgetCSVファ@イル行()メソッドをコールする。<BR>
     * <BR>
     * ２）取得した配列から空行、ブランクのみの行を除外する。<BR>
     * <BR>
     * ３）２）の結果を返却する。<BR>
     * @@return String[]
     * @@roseuid 42AFF37103E7
     */
    public String[] getCsvFileLines() 
    {
        final String STR_METHOD_NAME = "getCsvFileLines()";
        log.entering(STR_METHOD_NAME);
        
        //CSVファ@イルに出力するデータを、行毎の配列にて返却する。 
        //１）スーパークラスのgetCSVファ@イル行()メソッドをコールする。 
        String[] l_csvFileLines = super.getCsvFileLines();
        
        //２）取得した配列から空行、ブランクのみの行を除外する。
        List l_result = new ArrayList();
        
        for (int i = 0; i < l_csvFileLines.length; i++) 
        {
            if (!WEB3StringTypeUtility.isEmptyOrBlank(l_csvFileLines[i])) 
            {
                l_result.add(l_csvFileLines[i]);
            }
        }
        //３）２）の結果を返却する。 
        String[] l_strResult = new String[l_result.size()];
        l_result.toArray(l_strResult);
        
        log.exiting(STR_METHOD_NAME);
        
        return l_strResult;
    }
    
    /**
     * (isカラムヘッダ行出力)<BR>
     * CSVファ@イルにカラムヘッダ行の出力要否を判定する。<BR>
     * （オーバーライドメソッド）<BR>
     * <BR>
     * falseを返却する。<BR>
     * @@return boolean
     * @@roseuid 42CBA573008A
     */
    public boolean isColumnHeadOutput() 
    {
        return false;
    }
}
@
