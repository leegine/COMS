head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.16.03.46.26;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	6004d80209d0226;
filename	WEB3BondBizLogicProvider.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
 Copyright        : (株)大和総研 証券ソリューションシステム第二部
 File Name        : 債券計算サービス(WEB3BondBizLogicProvider.java)
 Author Name      : Daiwa Institute of Research
 Revesion History : 2006/08/17  齊珂 (中訊) 新規作成
                    2006/10/08  肖志偉 (中訊) 仕様変更 計算式書013
                    2006/10/12  徐宏偉 (中訊) WEBⅢ開発標準の見直しの対応（new BigDecimal部分）
 Revesion History : 2007/07/23  謝旋 (中訊) 仕様変更モデル228 、計算式書016
 Revesion History : 2007/08/29  柴双紅(中訊) 計算式書017
 */

package webbroker3.bd;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;

import com.fitechlabs.xtrade.kernel.data.DataNetworkException;
import com.fitechlabs.xtrade.kernel.data.DataQueryException;
import com.fitechlabs.xtrade.kernel.data.Processors;
import com.fitechlabs.xtrade.kernel.data.QueryProcessor;
import com.fitechlabs.xtrade.plugin.tc.gentrade.GlobalBizLogicProvider;
import com.fitechlabs.xtrade.plugin.tc.gentrade.OrderExecution;
import com.fitechlabs.xtrade.plugin.tc.gentrade.OrderSpec;
import com.fitechlabs.xtrade.plugin.tc.gentrade.ProductTypeEnum;
import com.fitechlabs.xtrade.plugin.tc.gentrade.QuantityTypeEnum;
import com.fitechlabs.xtrade.plugin.tc.gentrade.SubAccount;
import com.fitechlabs.xtrade.plugin.tc.gentrade.TaxTypeEnum;
import com.fitechlabs.xtrade.plugin.tc.gentrade.ordersubmitter.io.OrderValidationResult;
import com.fitechlabs.xtrade.plugin.tc.xbbd.BondBizLogicProvider;
import com.fitechlabs.xtrade.plugin.tc.xbbd.CouponTypeEnum;
import com.fitechlabs.xtrade.plugin.tc.xbbd.data.BondProductRow;

import webbroker3.bd.data.BondProductCouponRow;
import webbroker3.bd.define.WEB3BondWarningDivDef;
import webbroker3.common.WEB3BaseException;
import webbroker3.common.WEB3BaseRuntimeException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.common.WEB3SystemLayerException;
import webbroker3.common.define.WEB3AccruedInterestCalcTypeDef;
import webbroker3.common.define.WEB3BaseDateDivDef;
import webbroker3.common.define.WEB3BaseDaysDivDef;
import webbroker3.common.define.WEB3BondCategCodeDef;
import webbroker3.common.define.WEB3CalcBaseFormDef;
import webbroker3.common.define.WEB3ChangeRoundDivDef;
import webbroker3.common.define.WEB3DutyTypeDef;
import webbroker3.common.define.WEB3ElapsedDaysDivDef;
import webbroker3.common.define.WEB3RoundDivDef;
import webbroker3.common.define.WEB3YearlyInterestPaymentsDef;
import webbroker3.gentrade.WEB3GentradeBizLogicProvider;
import webbroker3.gentrade.WEB3GentradeCurrency;
import webbroker3.gentrade.WEB3GentradeTaxRate;
import webbroker3.gentrade.define.WEB3GentradeCurrencyCodeDef;
import webbroker3.gentrade.define.WEB3GentradeTimeDef;
import webbroker3.util.WEB3DateUtility;
import webbroker3.util.WEB3LogUtility;

/**
 * (債券計算サービス)<BR>
 * 債券計算サービス<BR>
 * 
 * @@author 齊珂
 * @@version 1.0
 */
public class WEB3BondBizLogicProvider extends WEB3GentradeBizLogicProvider
    implements GlobalBizLogicProvider, BondBizLogicProvider
{
    /**
     * ログ出力ユーティリティ。
     */
    private static WEB3LogUtility log =
        WEB3LogUtility.getInstance(WEB3BondBizLogicProvider.class);
    
    /**
     * @@roseuid 44E352DD008C
     */
    public WEB3BondBizLogicProvider() 
    {
     
    }
    
    /**
     * (calc円貨換算)<BR>
     * 指定金額を指定レートで邦貨換算を行う。 <BR>
     * <BR>
     * １）計算に使用する為替レートを取得する。<BR>
     * <BR>
     * 　@１－１）引数の為替レート　@!=　@NULLの場合<BR>
     * 　@為替レート　@=　@引数の為替レート<BR>
     * <BR>
     * 　@１－２）引数の為替レート　@==　@NULLの場合<BR>
     * 　@為替レート　@=　@引数の通貨オブジェクト.get為替レート( )<BR>
     * <BR>
     * 　@[引数]<BR>
     * 　@is買付：引数のis買付<BR>
     * 　@is約定計算：引数のis約定計算<BR>
     * 　@入力為替レート：0<BR>
     * <BR>　@
     * ２）金額（円貨）　@=　@引数の金額　@*　@１）で取得した為替レート<BR>
     * <BR>
     * ３）端数処理を行なう。<BR>
     * 　@２）の計算結果の少数部を引数の通貨オブジェクト.円貨換算丸め方式で丸め処理を行なう。<BR>
     * <BR>
     * ４）３）の計算結果を返却する。<BR>
     * @@param l_bdForeignAmount - (金額（外貨）)<BR>
     * 金額（外貨）<BR>
     * @@param l_currency - (通貨)<BR>
     * 通貨オブジェクト<BR>
     * @@param l_blnIsBuy - (is買付)<BR>
     * 買付かの判定 <BR>
     * <BR>
     * true：買 <BR>
     * false：売 <BR>
     * @@param l_blnIsExecuteCalc - (is約定計算)<BR>
     * 約定計算かの判定 <BR>
     * <BR>
     * true：約定計算 <BR>
     * false：概算計算 <BR>
     * @@param l_bdFxRate - (為替レート)<BR>
     * 為替レート<BR>
     * @@return BigDecimal
     * @@roseuid 44BDCE6002C8
     */
    public BigDecimal calcJPYAmount(BigDecimal l_bdForeignAmount, 
        WEB3GentradeCurrency l_currency, 
        boolean l_blnIsBuy, 
        boolean l_blnIsExecuteCalc, 
        BigDecimal l_bdFxRate) 
    {
        
        final String STR_METHOD_NAME = " calcJPYAmount(BigDecimal,  " + 
        "WEB3GentradeCurrency, boolean, boolean, BigDecimal)";
        log.entering(STR_METHOD_NAME);       
        
        if (l_bdForeignAmount == null || l_currency == null)
        {
            log.debug("パラメータ値がNULL");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BaseRuntimeException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80017,
                this.getClass().getName() + "." + STR_METHOD_NAME);
        }  
        
        //　@１－１）引数の為替レート　@!=　@NULLの場合
        // 　@為替レート　@=　@引数の為替レート
        BigDecimal l_bdRate = null;
        BigDecimal l_bdEstimatePrice = null;
        if (l_bdFxRate != null)
        {
            l_bdRate = l_bdFxRate;
        }
        //　@１－２）引数の為替レート　@==　@NULLの場合
        //　@為替レート　@=　@引数の通貨オブジェクト.get為替レート( )        
        else
        {
            l_bdRate = new BigDecimal(String.valueOf(l_currency.getExchangeRate(l_blnIsBuy, l_blnIsExecuteCalc, 0)));
        }
        
        // ２）金額（円貨）　@=　@引数の金額　@*　@１）で取得した為替レート
        l_bdEstimatePrice = l_bdForeignAmount.multiply(l_bdRate);
        
        // ３）端数処理を行なう。
        // 　@２）の計算結果の少数部を引数の通貨オブジェクト.円貨換算丸め方式で丸め処理を行なう。
        l_bdEstimatePrice = 
            new BigDecimal(String.valueOf(l_bdEstimatePrice.setScale(
                0, this.getRoundingMode(l_currency.getChangeJpyRoundDiv()))));
        
        // ４）３）の計算結果を返却する。
        log.exiting(STR_METHOD_NAME);
        return l_bdEstimatePrice;

    }
    
    /**
     * (calc受渡代金)<BR>
     * calc受渡代金<BR>
     * <BR>
     * １）債券受渡代金計算結果を生成する。<BR>
     * 　@債券受渡代金計算結果.数量＝引数.数量<BR>
     * 　@債券受渡代金計算結果.単価＝引数.注文単価<BR>
     * 　@債券受渡代金計算結果.為替レート＝引数.為替レート<BR>
     * 　@債券受渡代金計算結果.債券注文種別判定＝引数.債券注文種別判定<BR>
     * <BR>
     * ２）this.calc売買代金(債券受渡代金計算結果, 債券銘柄)を呼ぶ<BR>
     * 　@[引数]<BR>
     *  　@債券受渡代金計算結果：生成した債券受渡代金計算結果<BR>
     * 　@ 債券銘柄：引数.債券銘柄<BR>
     * <BR>
     * ３）this.calc経過利子（債券受渡代金計算結果, 債券銘柄, 債券約定日情報）を呼ぶ<BR>
     * 　@[引数]<BR>
     *    債券受渡代金計算結果：生成した債券受渡代金計算結果<BR>
     * 　@ 債券銘柄：引数.債券銘柄<BR>
     * 　@ 債券約定日情報：引数.債券約定日情報<BR>
     * <BR>
     * ４）this.calc受渡代金(債券受渡代金計算結果, 債券銘柄)を呼ぶ<BR>
     * 　@[引数]<BR>
     *    債券受渡代金計算結果：生成した債券受渡代金計算結果<BR>
     * 　@ 債券銘柄：引数.債券銘柄<BR>
     * <BR>
     * ５）債券受渡代金計算結果を返す<BR>
     * @@param l_bondOrderTypeJudge - (債券注文種別判定)<BR>
     * 債券注文種別判定<BR>
     * @@param l_bdQuantity - (数量)<BR>
     * 数量<BR>
     * @@param l_bdBondOrderUnit - (注文単価)<BR>
     * 注文単価<BR>
     * @@param l_bdFxRate - (為替レート)<BR>
     * 為替レート<BR>
     * @@param l_bondProduct - (債券銘柄)<BR>
     * 債券銘柄<BR>
     * @@param l_bondExecuteDateInfo - (債券約定日情報)
     * 債券約定日情報
     * @@return WEB3BondEstimatedPriceCalcResult
     * @@throws WEB3BaseException 
     * @@roseuid 44C822B603C8
     */
    public WEB3BondEstimatedPriceCalcResult calcEstimatedPrice(
        WEB3BondOrderTypeJudge l_bondOrderTypeJudge, 
        BigDecimal l_bdQuantity,
        BigDecimal l_bdBondOrderUnit, 
        BigDecimal l_bdFxRate, 
        WEB3BondProduct l_bondProduct, 
        WEB3BondExecuteDateInfo l_bondExecuteDateInfo) 
        throws WEB3BaseException 
    {
        final String STR_METHOD_NAME = " calcEstimatedPrice(WEB3BondOrderTypeJudge,  "
            + "BigDecimal, "
            + "BigDecimal, " 
            + "BigDecimal, "
            + "WEB3BondProduct, "
            + "WEB3BondExecuteDateInfo)";
        log.entering(STR_METHOD_NAME);

        
        //１）債券受渡代金計算結果を生成する。
        WEB3BondEstimatedPriceCalcResult l_bondEstimatedPriceCalcResult = 
            new WEB3BondEstimatedPriceCalcResult();
        
        //債券受渡代金計算結果.数量＝引数.数量
        l_bondEstimatedPriceCalcResult.setQuantity(l_bdQuantity);
        
        //債券受渡代金計算結果.単価＝引数.注文単価
        l_bondEstimatedPriceCalcResult.setPrice(l_bdBondOrderUnit);
        
        //債券受渡代金計算結果.為替レート＝引数.為替レート
        l_bondEstimatedPriceCalcResult.setFxRate(l_bdFxRate);
        
        //債券受渡代金計算結果.債券注文種別判定＝引数.債券注文種別判定
        l_bondEstimatedPriceCalcResult.setBondOrderTypeJudge(l_bondOrderTypeJudge);
        
        //２）this.calc売買代金(債券受渡代金計算結果, 債券銘柄)を呼ぶ
        this.calcTradingPrice(l_bondEstimatedPriceCalcResult, l_bondProduct);
        
        //３）this.calc経過利子（債券受渡代金計算結果, 債券銘柄, 債券約定日情報）を呼ぶ
        this.calcAccruedInterest(l_bondEstimatedPriceCalcResult, l_bondProduct, l_bondExecuteDateInfo);
        
        //４）this.calc受渡代金(債券受渡代金計算結果, 債券銘柄)を呼ぶ
        this.calcEstimatedPrice(l_bondEstimatedPriceCalcResult, l_bondProduct);
        
        //５）債券受渡代金計算結果を返す
        log.exiting(STR_METHOD_NAME);
        return l_bondEstimatedPriceCalcResult;
    }
    
    /**
     * (calc概算評価額)<BR>
     * 債券概算評価額計算結果を作成する。<BR>
     * <BR>
     * １）債券概算評価額計算結果オブジェクトを生成する。<BR>
     * <BR>
     * ２）債券概算評価額計算結果.評価単価に引数の債券銘柄.売却単価をセットする。<BR>
     * <BR>
     * ３）外貨建債券（引数の債券銘柄.is外貨建==true）の場合、<BR>
     * 　@３－１）通貨オブジェクトを取得する。通貨オブジェクトを取得する。<BR>
     * <BR>
     * 　@（共通）通貨のコンストラクタをコール。<BR>
     * <BR>
     * 　@［引数］<BR>
     * 　@証券会社コード：引数の補助口座.証券会社コード<BR>
     * 　@通貨コード：引数の債券銘柄.通貨コード<BR>
     * <BR>
     * ３－２）this.calc概算評価額(外貨)( )をコール。<BR>
     * 　@［引数］ <BR>
     * 　@数量：引数の数量 <BR>
     * 　@債券銘柄：引数の債券銘柄 <BR>
     * 　@通貨：３－１）で取得した通貨オブジェクト <BR>
     * <BR>
     * 　@３－３）３－２）の計算結果を債券概算評価額計算結果.概算評価額（外貨）へセットする。<BR>
     * <BR>
     * 　@３－４）this.calc円貨換算( )<BR>
     * <BR>
     * 　@［引数］<BR>
     * 　@金額：４－１）の計算結果<BR>
     * 　@通貨：３）で取得した通貨オブジェクト<BR>
     * 　@is買付：引数のis買付<BR>
     * 　@is約定計算：引数のis約定計算<BR>
     *   為替レート：NULL
     * <BR>
     * 　@３－５）３－４）の計算結果を債券概算評価額計算結果.概算評価額（円貨）へセットする。<BR>
     * <BR>　@
     * ４）外貨建債券でない（引数の債券銘柄.is外貨建==false）の場合、<BR>
     * <BR>
     * 　@４－１）this.calc概算評価額(円貨)( )をコール。<BR>
     * <BR>
     * 　@［引数］<BR>
     * 　@数量：引数の数量<BR>
     * 　@債券銘柄：引数の債券銘柄<BR>
     * <BR>
     * 　@４－２）４－１）の計算結果を債券概算評価額計算結果.概算評価額（円貨）へセットする。<BR>
     * <BR>
     * 　@４－３）債券概算評価額計算結果.概算評価額（外貨）へNULLをセットする。<BR>
     * <BR>
     * ５）生成した債券概算評価額計算結果オブジェクトを返却する。<BR>
     * @@param l_subAccount - (補助口座)<BR>
     * 補助口座<BR>
     * @@param l_dblQuantity - (数量)<BR>
     * 数量<BR>
     * @@param l_bondProduct - (債券銘柄)<BR>
     * 債券銘柄<BR>
     * @@param l_blnIsBuy - (is買付)<BR>
     * 買付かどうか判定<BR>
     * <BR>
     * 買付：true<BR>
     * 売却：false<BR>
     * @@param l_blnIsExecuteCalc - (is約定計算)<BR>
     * 約定計算かの判定 <BR>
     * <BR>
     * true：約定計算 <BR>
     * false：概算計算 <BR>
     * @@return WEB3BondEstimatedAssetCalcResult
     * @@throws WEB3BaseException 
     * @@roseuid 44C95BE50071
     */
    public WEB3BondEstimatedAssetCalcResult calcEstimatedAsset(
        SubAccount l_subAccount, 
        double l_dblQuantity, 
        WEB3BondProduct l_bondProduct, 
        boolean l_blnIsBuy, 
        boolean l_blnIsExecuteCalc) 
        throws WEB3BaseException 
    {
        final String STR_METHOD_NAME = 
            " calcBondEstimatedAsset(SubAccount, double, WEB3BondProduct, boolean, boolean) ";
        log.entering(STR_METHOD_NAME);
        
        if (l_subAccount == null || l_bondProduct == null)
        {
            log.debug("パラメータ値がNULL");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80017,
                this.getClass().getName() + "." + STR_METHOD_NAME);
        }  
        
        BigDecimal l_bdForeignEstimatedAsset = null;
        BigDecimal l_bdEstimatedAsset = null;
        BigDecimal l_bdJPYAmount = null;
        
        //１）債券概算評価額計算結果オブジェクトを生成する。
        WEB3BondEstimatedAssetCalcResult l_bondEstimatedAssetCalcResult = 
            new WEB3BondEstimatedAssetCalcResult();
        
        //２）債券概算評価額計算結果.評価単価に引数の債券銘柄.売却単価をセットする。 
        l_bondEstimatedAssetCalcResult.setEstimatedPrice(
            new BigDecimal(String.valueOf(l_bondProduct.getSellPrice())));
        
        //３）３）外貨建債券（引数の債券銘柄.is外貨建==true）の場合
        if (l_bondProduct.isForeignCurrency())
        {
            //３－１）通貨オブジェクトを取得する。             
            WEB3GentradeCurrency l_currency = WEB3GentradeCurrency.genCurrency(
                l_subAccount.getInstitution().getInstitutionCode(),
                l_bondProduct.getCurrencyCode());
            
            //３－２）this.calc概算評価額(外貨)( )をコール。 
            l_bdForeignEstimatedAsset = 
                this.calcForeignEstimatedAsset(l_dblQuantity, l_bondProduct, l_currency);
            
            //３－３）３－２）の計算結果を債券概算評価額計算結果.概算評価額（外貨）へセットする。
            l_bondEstimatedAssetCalcResult.setForeignEstimatedAsset(l_bdForeignEstimatedAsset);
            
            //３－４）this.calc円貨換算( ) 
            l_bdJPYAmount = 
                this.calcJPYAmount(l_bdForeignEstimatedAsset, 
                    l_currency, 
                    l_blnIsBuy, 
                    l_blnIsExecuteCalc, 
                    null);
            
            //３－５）３－４）の計算結果を債券概算評価額計算結果.概算評価額（円貨）へセットする。 
            l_bondEstimatedAssetCalcResult.setEstimatedAsset(l_bdJPYAmount);
        }   
        //４）外貨建債券でない（引数の債券銘柄.is外貨建==false）の場合、  
        else
        {
            //４－１）this.calc概算評価額(円貨)( )をコール。 
            l_bdEstimatedAsset =  
                this.calcEstimatedAsset(l_dblQuantity, l_bondProduct);
           
            //４－２）４－１）の計算結果を債券概算評価額計算結果.概算評価額（円貨）へセットする。 
            l_bondEstimatedAssetCalcResult.setEstimatedAsset(l_bdEstimatedAsset);
           
            //４－３）債券概算評価額計算結果.概算評価額（外貨）へNULLをセットする。 
            l_bondEstimatedAssetCalcResult.setForeignEstimatedAsset(null);
        }
           
            //５）生成した債券概算評価額計算結果オブジェクトを返却する。 
            log.exiting(STR_METHOD_NAME);
            return l_bondEstimatedAssetCalcResult;
    }
    
    /**
     * (calc概算評価額（円貨）)<BR>
     * 概算評価額（円貨）を計算する。<BR>
     * <BR>
     * １）引数.債券銘柄.is外国債券()の戻り値 == trueの場合、<BR>
     * 概算評価額（円貨）＝引数.数量 * 引数.債券銘柄.売却単価 / 引数.債券銘柄.単位額面<BR>
     * <BR>
     * ※計算結果は小数点以下切り捨て処理を行なう。<BR>
     * <BR>
     * ２）引数.債券銘柄.is外国債券()の戻り値 == falseの場合、<BR>
     * 　@概算評価額（円貨）＝（引数.数量 * 1000） * 引数.債券銘柄.売却単価 / 引数.債券銘柄.単位額面<BR>
     * <BR>
     * ※計算結果は小数点以下切り捨て処理を行なう。<BR>
     *  <BR>
     * ３）計算結果を返却する。  <BR>
     * @@param l_dblQuantity - (数量)<BR>
     * 数量<BR>
     * @@param l_bondProduct - (債券銘柄)<BR>
     * 債券銘柄<BR>
     * @@return BicDecimal
     * @@throws WEB3BaseException 
     * @@roseuid 44C95CD9018B
     */
    protected BigDecimal calcEstimatedAsset(
        double l_dblQuantity, 
        WEB3BondProduct l_bondProduct) throws WEB3BaseException 
    {
        
        final String STR_METHOD_NAME = " calcEstimatedAsset(double, WEB3BondProduct) ";
        log.entering(STR_METHOD_NAME);

        if (l_bondProduct == null)
        {
            log.debug("パラメータ値がNULL");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80017,
                this.getClass().getName() + "." + STR_METHOD_NAME);
        }  
        
        BigDecimal l_bdEstimate = null;
        BigDecimal l_bdQuantity = null;
        BigDecimal l_bdTemp1 = null;
        BigDecimal l_bdTemp2 = null;
        BigDecimal l_bdTemp3 = null;
        
        
        //１）引数.債券銘柄.is外国債券()の戻り値 == trueの場合、
        if (l_bondProduct.isForeignBond())
        {
            //概算評価額（円貨）＝引数.数量 * 引数.債券銘柄.売却単価 / 引数.債券銘柄.単位額面
            //計算結果は小数点以下切り捨て処理を行なう。
            l_bdQuantity = new BigDecimal(String.valueOf(l_dblQuantity));
            l_bdTemp1 = l_bdQuantity.multiply(
                new BigDecimal(String.valueOf(l_bondProduct.getSellPrice())));
            l_bdTemp2 = l_bdTemp1.divide(
                new BigDecimal(String.valueOf(l_bondProduct.getParValue())), 0, BigDecimal.ROUND_DOWN);
            l_bdEstimate = l_bdTemp2;
        }
        // ２）引数.債券銘柄.is外国債券()の戻り値 == falseの場合、
        else
        {
            //概算評価額（円貨）＝（引数.数量 * 1000） * 引数.債券銘柄.売却単価 / 引数.債券銘柄.単位額面
            //計算結果は小数点以下切り捨て処理を行なう。
            l_bdQuantity = new BigDecimal(String.valueOf(l_dblQuantity));
            l_bdTemp1 = l_bdQuantity.multiply(new BigDecimal("1000"));
            l_bdTemp2 = l_bdTemp1.multiply(
                new BigDecimal(String.valueOf(l_bondProduct.getSellPrice())));
            l_bdTemp3 = l_bdTemp2.divide(
                new BigDecimal(String.valueOf(l_bondProduct.getParValue())), 0, BigDecimal.ROUND_DOWN);
            l_bdEstimate = l_bdTemp3;
        }

        // ３）計算結果を返却する。  
        log.exiting(STR_METHOD_NAME);
        return l_bdEstimate;

    }
    
    /**
     * (calc概算評価額（外貨）)<BR>
     * 概算評価額（外貨）を計算する。<BR>
     * <BR>
     * １）概算評価額（外貨）＝引数.数量 * 引数.債券銘柄.売却単価 / 引数.債券銘柄.単位額面<BR>
     * <BR>
     * ２）端数処理を行なう。<BR>
     * 　@各通貨の下位通貨単位（小数部桁数）以下を外貨換算丸め方式で丸め処理を行なう。<BR>
     * <BR>
     * ３）２）の計算結果を返却する。<BR>
     * @@param l_dblQuantity - (数量)<BR>
     * 数量<BR>
     * @@param l_bondProduct - (債券銘柄)<BR>
     * 債券銘柄<BR>
     * @@param l_currency - (通貨)<BR>
     * 通貨オブジェクト<BR>
     * @@return BicDecimal
     * @@throws WEB3BaseException 
     * @@roseuid 44C95D6C00CF
     */
    protected BigDecimal calcForeignEstimatedAsset(double l_dblQuantity, 
        WEB3BondProduct l_bondProduct, 
        WEB3GentradeCurrency l_currency) throws WEB3BaseException 
    {
        final String STR_METHOD_NAME = " calcForeignEstimatedAsset(double, "+
        "WEB3BondProduct, "+
        "WEB3GentradeCurrency ) ";
        
        log.entering(STR_METHOD_NAME);
        
        if (l_bondProduct == null || l_currency == null)
        {
            log.debug("パラメータ値がNULL");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80017,
                this.getClass().getName() + "." + STR_METHOD_NAME);
        }  
        
        BigDecimal l_bdForeignEstimate = null;
        
        // １）概算評価額（外貨）＝引数.数量 * 引数.債券銘柄.売却単価 / 引数.債券銘柄.単位額面
        // ２）端数処理を行なう。
        // 　@各通貨の下位通貨単位（少数部桁数）以下を外貨換算丸め方式で丸め処理を行なう。
        BigDecimal l_bdQuantity = 
            new BigDecimal(String.valueOf(l_dblQuantity));
        BigDecimal l_bdTemp = 
            l_bdQuantity.multiply(new BigDecimal(String.valueOf(l_bondProduct.getSellPrice())));
        l_bdForeignEstimate = 
            l_bdTemp.divide(new BigDecimal(String.valueOf(l_bondProduct.getParValue())),
                l_currency.getScale(), this.getRoundingMode(l_currency.getChangeFCcyRoundDiv()));
       
        // ３）２）の計算結果を返却する。  
        log.exiting(STR_METHOD_NAME);
        return l_bdForeignEstimate;
    }
    
    /**
     * (calc経過利子)<BR>
     * 経過利子を計算する。<BR>
     * <BR>
     * 引数の債券受渡代金計算結果.経過利子（円貨）、経過利子（外貨）に計算結果をセットする。<BR>
     * <BR>
     * <BR>
     * ｢基本設計計算式書（債券）.doc <BR>
     * １．calc経過利子()　@｣参照。<BR>
     * <BR>
     * @@param l_bondPriceCalcResult - (債券受渡代金計算結果)<BR>
     * 債券受渡代金計算結果<BR>
     * @@param l_bondProduct - (債券銘柄)<BR>
     * 債券銘柄オブジェクト<BR>
     * @@param l_bondExecuteDateInfo - (債券約定日情報)<BR>
     * 債券約定日情報<BR>
     * @@throws WEB3BaseException 
     * @@roseuid 44CDBC1D02CC
     */
    protected void calcAccruedInterest(WEB3BondEstimatedPriceCalcResult l_bondPriceCalcResult, 
        WEB3BondProduct l_bondProduct, 
        WEB3BondExecuteDateInfo l_bondExecuteDateInfo) throws WEB3BaseException 
    {
        final String STR_METHOD_NAME = " calcAccruedInterest(WEB3BondEstimatedPriceCalcResult, " + 
            "WEB3BondProduct, WEB3BondExecuteDateInfo) ";
        log.entering(STR_METHOD_NAME);
        
        //引数の債券銘柄.is外国債券()　@==　@true　@の場合
        if (l_bondProduct.isForeignBond())
        {
            //this.calc経過利子(外国債券)()　@をコール。
            this.calcAccruedInterestForForeignBond(
                l_bondPriceCalcResult,
                l_bondProduct,
                l_bondExecuteDateInfo);
            //引数の債券銘柄.is外貨建()　@==　@false　@の場合
            //引数の受渡代金計算結果.経過利子(外貨)はNULLをセット。
            if(!l_bondProduct.isForeignCurrency())
            {
                l_bondPriceCalcResult.setForeignAccruedInterest(null);
            }
        }
        
        //引数の債券銘柄.is外国債券()　@==　@false　@の場合
        else
        {
            //this.calc経過利子(国内債券)()をコール。
            this.calcAccruedInterestForDomesticBond(
                l_bondPriceCalcResult,
                l_bondProduct,
                l_bondExecuteDateInfo);
        }
        
        log.exiting(STR_METHOD_NAME);
    }
      
    /**
     * (calc売買代金)<BR>
     * calc売買代金<BR>
     * <BR>
     * 債券受渡代金計算結果をインターフェースとし、売買代金を計算する<BR>
     * <BR>
     * １）this.calc売買代金（数量, 単価, 債券銘柄）をコールして、売買代金を計算<BR>
     * 　@ [引数]<BR>
     * 　@　@　@数量：引数.債券受渡代金計算結果.数量<BR>
     * 　@　@　@　@単価：引数.債券受渡代金計算結果.単価<BR>
     * 　@　@　@　@債券銘柄：引数.債券銘柄<BR>
     * <BR>
     * ２）債券銘柄.is外貨建＝＝falseの場合 <BR>
     * 　@債券受渡代金計算結果.売買代金(円貨)　@=　@１)で計算した売買代金の値<BR>
     * <BR>
     * ３) 債券銘柄.is外貨建＝＝trueの場合 <BR>
     * 　@　@　@３－１）債券受渡代金計算結果.売買代金(外貨) = １)で計算した売買代金の値<BR>
     * 　@　@　@３－２）（共通）通貨.（共通）通貨（証券会社コード、通貨コード）で（共通）通貨を取得する。<BR>
     * 　@　@　@　@[引数]<BR>
     * 　@　@　@　@　@　@　@証券会社コード＝債券銘柄.get証券会社().get証券会社コード()<BR>
     * 　@　@　@　@　@　@　@通貨コード＝債券銘柄.get通貨コード()<BR>
     * 　@　@　@３－３）売買代金(外貨)を円貨換算する<BR>
     * 　@　@　@・this.円貨換算()コールして、円貨の売買代金を計算する<BR>
     * 　@　@　@ 　@[引数]<BR>
     * 　@　@　@　@　@     金額(外貨)：１）で計算した売買代金<BR>
     * 　@　@　@　@　@     通貨：(共通)通貨オブジェクト<BR>
     * 　@　@　@　@　@     is買付：true<BR>
     * 　@　@　@　@　@     is約定計算：true<BR>
     * 　@　@　@　@　@     為替レート：債券受渡代金計算結果.為替レート<BR>
     * 　@　@　@３－４）債券受渡代金計算結果.売買代金(円貨)　@= ３－３）で取得した円貨換算後の売買代金<BR>
     * @@param l_bondPriceCalcResult - (債券受渡代金計算結果)<BR>
     * 債券受渡代金計算結果<BR>
     * @@param l_bondProduct - (債券銘柄)<BR>
     * 債券銘柄オブジェクト<BR>
     * @@throws WEB3BaseException 
     * @@roseuid 44D09D250167
     */
    protected void calcTradingPrice(WEB3BondEstimatedPriceCalcResult l_bondPriceCalcResult, 
        WEB3BondProduct l_bondProduct) 
        throws WEB3BaseException 
    {
        final String STR_METHOD_NAME = " calcTradePrice(WEB3BondEstimatedPriceCalcResult, WEB3BondProduct) ";
        log.entering(STR_METHOD_NAME);
        
        if (l_bondProduct == null || l_bondPriceCalcResult == null)
        {
            log.debug("パラメータ値がNULL");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80017,
                this.getClass().getName() + "." + STR_METHOD_NAME);
        }  
        
        //１）this.calc売買代金（数量, 単価, 債券銘柄）をコールして、売買代金を計算
        //   数量：引数.債券受渡代金計算結果.数量<BR>
        //　@ 単価：引数.債券受渡代金計算結果.単価<BR>
        // 　@債券銘柄：引数.債券銘柄<BR>
        BigDecimal l_bdTradePrice = 
            this.calcTradingPrice(l_bondPriceCalcResult.getQuantity(), l_bondPriceCalcResult.getPrice(), l_bondProduct);
        
        //２）債券銘柄.is外貨建＝＝falseの場合
        //    債券受渡代金計算結果.売買代金(円貨)　@=　@１)で計算した売買代金の値
        if (!l_bondProduct.isForeignCurrency())
        {
            l_bondPriceCalcResult.setTradingPrice(l_bdTradePrice);
        }
        
        //３)債券銘柄.is外貨建＝＝trueの場合
        else
        {
            // ３－１）債券受渡代金計算結果.売買代金(外貨) = １)で計算した売買代金の値
            l_bondPriceCalcResult.setForeignTradePrice(l_bdTradePrice);
            
            // ３－２）（共通）通貨.（共通）通貨（証券会社コード、通貨コード）で（共通）通貨を取得する。
            //　@　@　@　@[引数]
            //　@　@　@　@　@　@　@証券会社コード＝債券銘柄.get証券会社().get証券会社コード()
            //　@　@　@　@　@　@　@通貨コード＝債券銘柄.get通貨コード()
            WEB3GentradeCurrency l_currency = WEB3GentradeCurrency.genCurrency(
                l_bondProduct.getInstitution().getInstitutionCode(),
                l_bondProduct.getCurrencyCode());
            
            // ３－３）売買代金(外貨)を円貨換算する
            BigDecimal l_bdJPYAmount = 
                this.calcJPYAmount(l_bdTradePrice, l_currency, true, true, l_bondPriceCalcResult.getFxRate());
            
            // 　@３－４）債券受渡代金計算結果.売買代金(円貨)　@= ３－３）で取得した円貨換算後の売買代金 
            l_bondPriceCalcResult.setTradingPrice(l_bdJPYAmount);
        }
        log.exiting(STR_METHOD_NAME);
     
    }
    
    /**
     * (calc受渡代金)<BR>
     * calc受渡代金<BR>
     * <BR>
     * １）受渡代金（円貨）を計算する。<BR>
     * 　@１－１）債券受渡代金計算結果.calc受渡代金（円貨）を呼ぶ<BR>
     * 　@１－２）債券受渡代金計算結果.calc受渡代金（円貨）を債券受渡代金計算結果.受渡代金（円貨）にセットする。<BR>
     * <BR>
     * ２）債券銘柄.is外貨建＝＝trueの場合 <BR>
     * 　@２－１）債券受渡代金計算結果.calc受渡代金（外貨）を呼ぶ<BR>
     * 　@２－２）債券受渡代金計算結果.calc受渡代金（外貨）を債券受渡代金計算結果.受渡代金（外貨）にセットする。<BR>
     * @@param l_bondPriceCalcResult - (債券受渡代金計算結果)<BR>
     * 債券受渡代金計算結果<BR>
     * @@param l_bondProduct - (債券銘柄)<BR>
     * 債券銘柄オブジェクト<BR>
     * @@throws WEB3BaseException 
     * @@roseuid 44D0A2A30157
     */
    protected void calcEstimatedPrice(WEB3BondEstimatedPriceCalcResult l_bondPriceCalcResult, 
        WEB3BondProduct l_bondProduct) throws WEB3BaseException 
    {
        
        final String STR_METHOD_NAME = " calcEstimatedPrice(WEB3BondEstimatedPriceCalcResult) ";
        log.entering(STR_METHOD_NAME);
        
        if (l_bondPriceCalcResult == null || l_bondProduct == null)
        {
            log.debug("パラメータ値がNULL");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80017,
                this.getClass().getName() + "." + STR_METHOD_NAME);
        }  
       
        //１）受渡代金（円貨）を計算する。
        //　@１－１）債券受渡代金計算結果.calc受渡代金（円貨）を呼ぶ
        BigDecimal l_bdEstimatedPrice = l_bondPriceCalcResult.calcEstimatedPrice();
        
        //１－２）債券受渡代金計算結果.calc受渡代金（円貨）を債券受渡代金計算結果.受渡代金（円貨）にセットする。
        l_bondPriceCalcResult.setEstimatedPrice(l_bdEstimatedPrice);
        
        //２）債券銘柄.is外貨建＝＝trueの場合
        if (l_bondProduct.isForeignCurrency())
        {
            //２－１）債券受渡代金計算結果.calc受渡代金（外貨）を呼ぶ
            BigDecimal l_bdForeignEstimatedPrice = l_bondPriceCalcResult.calcForeignEstimatedPrice();
            
            //２－２）債券受渡代金計算結果.calc受渡代金（外貨）を債券受渡代金計算結果.受渡代金（外貨）にセットする。
            l_bondPriceCalcResult.setForeignEstimatedPrice(l_bdForeignEstimatedPrice);
        }
        log.exiting(STR_METHOD_NAME);
    }
    
    /**
     * (calc売買代金)<BR>
     * 売買代金を計算し、計算した結果を返却<BR>
     * <BR>
     * １）単位額面を取得<BR>
     * 　@１－１）債券銘柄.getParValue()<BR>
     * 　@１－２）債券銘柄.getParValue()をBigDecimalにする<BR>
     * <BR>
     * ２）債券銘柄.is外貨建＝＝falseの場合  <BR>
     * 　@２－１）売買代金（円貨）を計算する<BR>
     * 　@　@　@　@売買代金（円貨）＝引数.数量 * 引数.単価 / 単位額面<BR>
     * 　@　@　@　@※小数点以下四捨五入<BR>
     * 　@２－２）計算した売買代金(円貨)を返却 <BR>
     * <BR>
     * ３）債券銘柄.is外貨建＝＝trueの場合<BR>
     * 　@３－１）（共通）通貨.（共通）通貨（証券会社コード、通貨コード）で（共通）通貨を取得する。<BR>
     * 　@　@　@　@[引数]<BR>
     * 　@　@　@　@　@　@　@証券会社コード＝債券銘柄.get証券会社().get証券会社コード()<BR>
     * 　@　@　@　@　@　@　@通貨コード＝債券銘柄.get通貨コード()<BR>
     * 　@３－２）売買代金（外貨）を計算する<BR>
     * 　@　@　@　@売買代金（外貨）＝数量 * 単価 / 単位額面<BR>
     * 　@　@　@　@　@（計算結果は、（共通）通貨の小数部桁数未満を、（共通）通貨の外貨換算丸め方式にて丸め処理を行う）<BR>
     * <BR>
     *   ３－３）計算した売買代(外貨)を返却<BR>
     * @@param l_quantity - (数量)<BR>
     * 数量<BR>
     * @@param l_price - (単価)<BR>
     * 単価<BR>
     * @@param l_bondProduct - (債券銘柄)<BR>
     * 債券銘柄オブジェクト<BR>
     * @@return BigDecimal
     * @@throws WEB3BaseException 
     * @@roseuid 44D42266017E
     */
    public BigDecimal calcTradingPrice(BigDecimal l_quantity, BigDecimal l_price, WEB3BondProduct l_bondProduct) 
        throws WEB3BaseException 
    {
        final String STR_METHOD_NAME = " calcTradingPrice(BigDecimal, BigDecimal, WEB3BondProduct) ";
        log.entering(STR_METHOD_NAME);
        
        if (l_bondProduct == null)
        {
            log.debug("パラメータ値がNULL");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80017,
                this.getClass().getName() + "." + STR_METHOD_NAME);
        }  
        
        //１）単位額面を取得
        //１－１）債券銘柄.getParValue()
        double l_dblParValue= l_bondProduct.getParValue();
        BigDecimal l_bdParValue = new BigDecimal(String.valueOf(l_dblParValue));
        BigDecimal l_bdTradePrice = null; 
        
        //２）債券銘柄.is外貨建＝＝falseの場合
        if (!l_bondProduct.isForeignCurrency())
        {
            //２－１）売買代金（円貨）を計算する
            //      売買代金（円貨）＝引数.数量 * 引数.単価 / 単位額面
            // 小数点以下四捨五入
            l_bdTradePrice = l_quantity.multiply(l_price);
            l_bdTradePrice = l_bdTradePrice.divide(l_bdParValue, 0, BigDecimal.ROUND_HALF_UP);
        }    
        
        // ３）債券銘柄.is外貨建＝＝trueの場合
        if (l_bondProduct.isForeignCurrency())
        {
            //３－１）（共通）通貨.（共通）通貨（証券会社コード、通貨コード）で（共通）通貨を取得する。
            WEB3GentradeCurrency l_currency = WEB3GentradeCurrency.genCurrency(
                l_bondProduct.getInstitution().getInstitutionCode(),
                l_bondProduct.getCurrencyCode());
            
            //３－２）売買代金（外貨）を計算する
            //        売買代金（外貨）＝数量 * 単価 / 単位額面
            //（計算結果は、（共通）通貨の小数部桁数未満を、（共通）通貨の外貨換算丸め方式にて丸め処理を行う）
            l_bdTradePrice = l_quantity.multiply(l_price);
            l_bdTradePrice = l_bdTradePrice.divide(l_bdParValue,
                l_currency.getScale(), this.getRoundingMode(l_currency.getChangeFCcyRoundDiv()));
        }    
          log.exiting(STR_METHOD_NAME);
        return l_bdTradePrice;
    }
    
    /**
     * (calc経過利子)<BR>
     * 経過利子を計算する。<BR>
     * <BR>
     * (calcAcrruedInterestのオーバーライド)<BR>
     * <BR>
     * return 0;<BR>
     * @@param l_lngBondProductId - (債券銘柄ID)<BR>
     * 債券銘柄ID<BR>
     * @@param l_datDeliveryDate - (受渡日)<BR>
     * 受渡日<BR>
     * @@param l_dblQuantity - (数量)<BR>
     * 数量<BR>
     * @@param l_dblTaxRate - (税率)<BR>
     * 税率<BR>
     * @@return double
     * @@roseuid 44D71F5D0241
     */
    public double calcAcrruedInterest(long l_lngBondProductId, 
        Date l_datDeliveryDate, 
        double l_dblQuantity, 
        double l_dblTaxRate) 
    {
        return 0;
    }
    
    /**
     * (calc約定代金)<BR>
     * 約定代金を計算する。<BR>
     * <BR>
     * (calcExecutionAmountのオーバーライド)<BR>
     * <BR>
     * return 0;<BR>
     * @@param l_productType - (銘柄タイプ)<BR>
     * 銘柄タイプ<BR>
     * @@param l_lngProductId - (銘柄ID)<BR>
     * 銘柄ID<BR>
     * @@param l_dblExecutedPrice - (約定単価)<BR>
     * 約定単価<BR>
     * @@param l_dblExecutedQuantity - (約定数量)<BR>
     * 約定数量<BR>
     * @@param l_quantityType - (数量タイプ)<BR>
     * 数量タイプ<BR>
     * @@return double
     * @@roseuid 44D7204201B6
     */
    public double calcExecutionAmount(ProductTypeEnum l_productType, 
        long l_lngProductId, 
        double l_dblExecutedPrice, 
        double l_dblExecutedQuantity, 
        QuantityTypeEnum l_quantityType) 
    {
        return 0;
    }
    
    /**
     * (check取引余力)<BR>
     * 余力チェックをする。<BR>
     * <BR>
     * (checkTradingPowerのオーバーライド)<BR>
     * <BR>
     * return OrderValidationResult.VALIDATION_OK_RESULT;<BR>
     * @@param l_subAccount - (補助口座)<BR>
     * 補助口座<BR>
     * @@param l_orderSpec - (注文内容)<BR>
     * 注文内容<BR>
     * @@return OrderValidationResult
     * @@roseuid 44D720C50187
     */
    public OrderValidationResult checkTradingPower(SubAccount l_subAccount, OrderSpec l_orderSpec) 
    {
        return OrderValidationResult.VALIDATION_OK_RESULT;
    }

    public double calcCommission(OrderExecution arg0)
    {
        // TODO Auto-generated method stub
        return 0;
    }

    public double calcCapitalGainTax(SubAccount arg0, TaxTypeEnum arg1, double arg2)
    {
        // TODO Auto-generated method stub
        return 0;
    }
    
    /**
     * (get丸めモード)<BR>
     * 引数の丸め方式に対応するBigDecimalの丸めモードを返却する。<BR>
     * <BR>
     * @@param l_strDealType 丸め方式
     * <BR>  
     * @@return 丸めモード
     */
    public int getRoundingMode(String l_strDealType)
    {   
        if (WEB3ChangeRoundDivDef.CUTTING_OFF.equals(l_strDealType))
        {
            return BigDecimal.ROUND_FLOOR;   
        }
        else if (WEB3ChangeRoundDivDef.RAISING_TO_A_UNIT.equals(l_strDealType))
        {
            return BigDecimal.ROUND_CEILING;
        }
        else if (WEB3ChangeRoundDivDef.ROUNDING_OFF.equals(l_strDealType))
        {
            return BigDecimal.ROUND_HALF_UP;
        }
        else
        {
            return BigDecimal.ROUND_UNNECESSARY;
        }
    }    
    /**
     * (calc経過利子(外国債券))<BR>
     * 外国債券の場合、calc経過利子()からコールされる。<BR>
     * <BR>
     * ｢基本設計計算式書（債券）.doc<BR>
     * ２．calc経過利子(外国債券)()　@｣参照。<BR>
     * @@param l_bondPriceCalcResult - (債券受渡代金計算結果)<BR>
     * 債券受渡代金計算結果<BR>
     * @@param l_bondProduct - (債券銘柄)<BR>
     * 債券銘柄オブジェクト<BR>
     * @@param l_bondExecuteDateInfo - (債券約定日情報)<BR>
     * 債券約定日情報<BR>
     * @@throws WEB3BaseException 
     * @@roseuid 44F2585303D8
     */
    protected void calcAccruedInterestForForeignBond(
        WEB3BondEstimatedPriceCalcResult l_bondPriceCalcResult, 
        WEB3BondProduct l_bondProduct, 
        WEB3BondExecuteDateInfo l_bondExecuteDateInfo) throws WEB3BaseException 
    {
        final String STR_METHOD_NAME = " calcAccruedInterestForForeignBond("
            + "WEB3BondEstimatedPriceCalcResult, WEB3BondProduct, WEB3BondExecuteDateInfo) ";
        log.entering(STR_METHOD_NAME);
        
        if (l_bondPriceCalcResult == null || l_bondProduct == null)
        {
            log.debug("パラメータ値不正。");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80017,
                this.getClass().getName() + "." + STR_METHOD_NAME);
        }
        //(1)　@注文種別による経過利子計算実施判定
        //引数の受渡代金計算結果.債券注文種別判定.is応募注文　@==　@true　@の場合
        if (l_bondPriceCalcResult.getBondOrderTypeJudge().isRecruitOrder())
        {
            //引数の受渡代金計算結果.経過利子(外貨)、経過利子(円貨)にZEROをセットし、return。
            l_bondPriceCalcResult.setAccruedInterest(new BigDecimal("0"));
            l_bondPriceCalcResult.setForeignAccruedInterest(new BigDecimal("0"));
            log.exiting(STR_METHOD_NAME);
            return;
        }
        
        //(2)　@利付タイプによる経過利子計算実施判定
        //引数の債券銘柄.利付タイプ　@==　@“割引債”　@の場合
        if (CouponTypeEnum.ZERO_COUPON.equals(l_bondProduct.getCouponType()))
        {
            //引数の受渡代金計算結果.経過利子(外貨)、経過利子(円貨)にZEROをセットし、return。
            l_bondPriceCalcResult.setAccruedInterest(new BigDecimal("0"));
            l_bondPriceCalcResult.setForeignAccruedInterest(new BigDecimal("0"));
            log.exiting(STR_METHOD_NAME);
            return;
        }
        
        //(3)　@マスタチェックによる経過利子計算実施判定
        //・引数の債券銘柄.償還日　@≦　@引数の債券銘柄.発行日　@の場合
        //・引数の債券銘柄.償還日　@≦　@引数の債券銘柄.経過利子起算日　@の場合
        if (WEB3DateUtility.compareToDay(
                l_bondProduct.getMaturityDate(), 
                l_bondProduct.getIssueDate()) <= 0
            || WEB3DateUtility.compareToDay(
                l_bondProduct.getMaturityDate(), 
                l_bondProduct.getAccruedInterestStartDay()) <= 0)
        {
            //上記［判定条件］のいずれかにあてはまる場合は
            //引数の受渡代金計算結果.経過利子(外貨)、経過利子(円貨)にZERO、
            //引数の受渡代金計算結果.警告区分一覧に"経過利子計算不可能"を追加し、return。
            l_bondPriceCalcResult.setAccruedInterest(new BigDecimal("0"));
            l_bondPriceCalcResult.setForeignAccruedInterest(new BigDecimal("0"));
            l_bondPriceCalcResult.addWarningDiv(
                WEB3BondWarningDivDef.ACCRUED_INTEREST_CANNOT_CALCULATION);
            log.exiting(STR_METHOD_NAME);
            return;
        }
        
        //(4)　@銘柄情報による経過利子計算警告判定　@(引数の債券銘柄.is経過利子計算警告判定())
        //引数の債券銘柄.is経過利子計算警告判定()の戻り値がfalseの場合、
        if (!l_bondProduct.isAccruedInterestCalcWarningJudge())
        {
            //引数の受渡代金計算結果.警告区分一覧に“経過利子が正確でない可能性”を追加する。
            l_bondPriceCalcResult.addWarningDiv(
                WEB3BondWarningDivDef.ACCRUED_INTEREST_WRONG_POSSIBLE);
        }
        
        //(5)　@経過利子計算条件取得
        //引数の債券銘柄.経過利子計算タイプをキーとして、
        //経過利子計算条件テーブルから経過利子計算条件を取得する。
        //(経過利子計算条件クラスのコンストラクタより取得)
        String l_strAccruedInterestCalcType = 
            l_bondProduct.getAccruedInterestCalcType();
        WEB3BondAccruedInterestCalcCondition l_accruedInterestCalcCondition = 
            new WEB3BondAccruedInterestCalcCondition(l_strAccruedInterestCalcType);
        
        //(6)　@経過利子計算必須条件項目の算出
        //(6-1)　@基準日算出　@(this.calc基準日())　@
        Date l_datBaseDate = this.calcBaseDate(
            l_bondExecuteDateInfo, 
            l_accruedInterestCalcCondition, 
            l_bondProduct,
            l_bondPriceCalcResult);
        
        List l_lisWarningDivList = l_bondPriceCalcResult.getWarningDivList();
        if (l_lisWarningDivList != null && l_lisWarningDivList.contains(
            WEB3BondWarningDivDef.ACCRUED_INTEREST_CANNOT_CALCULATION))
        {
            return;
        }
        
        //(6-2)　@前回利払日/次回利払日算出　@(this.calc利払日())
        WEB3BondInterestPaymentDay l_interestPaymentDay = this.calcInterestPaymentDay(
            l_bondPriceCalcResult, 
            l_bondProduct, 
            l_datBaseDate);
        
        l_lisWarningDivList = l_bondPriceCalcResult.getWarningDivList();
        if (l_lisWarningDivList != null && l_lisWarningDivList.contains(
            WEB3BondWarningDivDef.ACCRUED_INTEREST_CANNOT_CALCULATION))
        {
            return;
        }
        
        //(6-3)　@基準日数算出　@(this.calc基準日数())
        this.calcBaseDays(
            l_bondPriceCalcResult, 
            l_bondProduct, 
            l_accruedInterestCalcCondition, 
            l_datBaseDate,
            l_interestPaymentDay.getPreInterestPaymentDay(),
            l_interestPaymentDay.getNextInterestPaymentDay());
        
        l_lisWarningDivList = l_bondPriceCalcResult.getWarningDivList();
        if (l_lisWarningDivList != null && l_lisWarningDivList.contains(
            WEB3BondWarningDivDef.ACCRUED_INTEREST_CANNOT_CALCULATION))
        {
            return;
        }
        
        //(6-4)　@経過日数算出　@(this.calc経過日数())　@※経過日数MAXチェックも行なう。
        this.calcElapsedDays(
            l_bondPriceCalcResult, 
            l_accruedInterestCalcCondition, 
            l_datBaseDate,
            l_interestPaymentDay.getPreInterestPaymentDay());
        
        //(7)　@経過利子計算
        //経過利子
        BigDecimal l_bdAccruedInterest = null;
        BigDecimal l_bdAccruedInterestWork = null;

        //単価
        BigDecimal l_bdPrice = null;
        BigDecimal l_bdPriceWork = null;
                
        //引数の受渡代金計算結果.数量
        BigDecimal l_bdQuantity = l_bondPriceCalcResult.getQuantity();
        
        //引数の受渡代金計算結果.経過日数
        BigDecimal l_bdElapsedDays = 
            new BigDecimal(String.valueOf(l_bondPriceCalcResult.getElapsedDays().intValue()));
        
        //引数の受渡代金計算結果.基準日数
        BigDecimal l_bdCalcBaseDays = 
            new BigDecimal(String.valueOf(l_bondPriceCalcResult.getCalcBaseDays().intValue()));
        
        BigDecimal l_bdHundred = new BigDecimal("100");
        
        //経過利子計算条件.金額未満処理区分 
        String l_strAmountRoundDiv = l_accruedInterestCalcCondition.getAmountRoundDiv();
        
        //経過利子計算条件.単位未満処理区分
        String l_strUnitRoundDiv = l_accruedInterestCalcCondition.getUnitRoundDiv();
        
        //引数の債券銘柄．通貨コード
        int l_intPriceScale = 0;
        if (WEB3GentradeCurrencyCodeDef.JPY.equals(l_bondProduct.getCurrencyCode()))
        {
            l_intPriceScale = 0;
        }
        else
        {
            l_intPriceScale = 2;
        }
        
        //単価桁数
        int l_intUnitPriceScale = l_accruedInterestCalcCondition.getUnitPriceScale();
        
        //利率
        BigDecimal l_bdCoupon = l_interestPaymentDay.getCoupon();
        
        //(7-1)　@金額優先方式　@((5)で取得した経過利子計算条件の計算基準区分が"金額優先方式"の場合)
        if (WEB3CalcBaseFormDef.AMOUNT_PRIORITY_FORM.equals(l_accruedInterestCalcCondition.getCalcBaseForm())) 
        {
            //経過利子　@=　@ 引数の受渡代金計算結果.数量　@×
            //(　@(6-2)で取得した利率　@／　@100　@)　@×　@
            //(引数の受渡代金計算結果.経過日数　@／　@引数の受渡代金計算結果.基準日数)
            l_bdAccruedInterestWork = 
                l_bdQuantity.multiply(
                    l_bdCoupon.divide(l_bdHundred, 10, BigDecimal.ROUND_HALF_UP)).multiply(
                        l_bdElapsedDays.divide(l_bdCalcBaseDays, 10, BigDecimal.ROUND_HALF_UP));
            l_bdAccruedInterest = 
                getCutOutValueForAccruedInterest(l_bdAccruedInterestWork, l_intPriceScale, l_strAmountRoundDiv);
        }
        
        //(7-2)　@単価優先方式　@((5)で取得した経過利子計算条件の計算基準区分が"単価優先方式"の場合)
        else if (WEB3CalcBaseFormDef.UNIT_PRICE_PRIORITY_FORM.equals(l_accruedInterestCalcCondition.getCalcBaseForm()))
        {
            //単価　@=　@(6-2)で取得した利率×(引数の受渡代金計算結果.経過日数／引数の受渡代金計算結果.基準日数)
            l_bdPriceWork = 
                l_bdCoupon.multiply(
                    l_bdElapsedDays.divide(
                        l_bdCalcBaseDays, 10, BigDecimal.ROUND_HALF_UP));
            l_bdPrice = 
                getCutOutValueForAccruedInterest(l_bdPriceWork, l_intUnitPriceScale, l_strUnitRoundDiv);
            
            //経過利子　@=　@(算出した単価／100)×引数の受渡代金計算結果.数量
            l_bdAccruedInterestWork = 
                l_bdPrice.divide(
                    l_bdHundred, 10, BigDecimal.ROUND_HALF_UP).multiply(
                        l_bdQuantity);
            l_bdAccruedInterest = 
                getCutOutValueForAccruedInterest(l_bdAccruedInterestWork, l_intPriceScale, l_strUnitRoundDiv);
        }
        
        //(8)   経過利子MAXチェック
        //　@フローティングレート債（引数の債券銘柄.種別コード　@!=　@“外債フローティングレート債”)
        //　@&& (6-2)で取得した初回利払日　@<　@(6-1)で取得した基準日　@<　@(6-2)で取得した最終利払日
        // &&　@引数の債券銘柄.年間利払回数　@!=　@“不定”の場合
        int l_intYearlyInterestPayments = 
            l_bondProduct.getYearlyInterestPayments();
        BigDecimal l_bdYearlyInterestPayments = 
            new BigDecimal(String.valueOf(l_intYearlyInterestPayments));
        BigDecimal l_bdOne = new BigDecimal("1");
        
        boolean l_blnFlag = true;
        if (l_interestPaymentDay.getFirstInterestPaymentDay() != null
            && l_interestPaymentDay.getFinalInterestPaymentDay() != null)
        {
            if (!(WEB3DateUtility.compareToDay(
                    l_interestPaymentDay.getFirstInterestPaymentDay(), l_datBaseDate) < 0 
                && WEB3DateUtility.compareToDay(
                    l_datBaseDate, l_interestPaymentDay.getFinalInterestPaymentDay()) < 0))
            {
                l_blnFlag = false;
            }
        }
        if (!WEB3BondCategCodeDef.FLOATING_RATE_BOND.equals(l_bondProduct.getBondCategCode()) 
            && l_blnFlag
            && l_intYearlyInterestPayments != Integer.parseInt(WEB3YearlyInterestPaymentsDef.NO_FIXED_TIME))
        {
            BigDecimal l_bdAccruedInterestMax = null;
            BigDecimal l_bdAccruedInterestMaxWork = null;
            
            //(8-1)　@金額優先方式　@((5)で取得した経過利子計算条件の計算基準区分が"金額優先方式"の場合)
            if (WEB3CalcBaseFormDef.AMOUNT_PRIORITY_FORM.equals(
                l_accruedInterestCalcCondition.getCalcBaseForm()))
            {
                //引数の債券銘柄.年間利払回数がZEROの場合
                if (l_intYearlyInterestPayments == Integer.parseInt(WEB3YearlyInterestPaymentsDef.DEFAULT))
                {
                    //引数の受渡代金計算結果.経過利子(外貨)、経過利子(円貨)にZERO、
                    //引数の受渡代金計算結果.警告区分一覧に"経過利子計算不可能"を追加し、return。
                    l_bondPriceCalcResult.setAccruedInterest(new BigDecimal("0"));
                    l_bondPriceCalcResult.setForeignAccruedInterest(new BigDecimal("0"));
                    l_bondPriceCalcResult.addWarningDiv(
                        WEB3BondWarningDivDef.ACCRUED_INTEREST_CANNOT_CALCULATION);
                    log.exiting(STR_METHOD_NAME);
                    return;
                }
                
                //経過利子MAX　@=　@ 引数の受渡代金計算結果.数量　@×　@
                //(　@(6-2)で取得した利率　@／　@100)　@×　@
                //(　@1　@／　@引数の債券銘柄.年間利払回数(*注1)　@)
                l_bdAccruedInterestMaxWork = 
                    l_bdQuantity.multiply(
                        l_bdCoupon.divide(l_bdHundred, 10, BigDecimal.ROUND_HALF_UP)).multiply(
                            l_bdOne.divide(
                                l_bdYearlyInterestPayments, 10, BigDecimal.ROUND_HALF_UP));
                l_bdAccruedInterestMax = 
                    getCutOutValueForAccruedInterest(l_bdAccruedInterestMaxWork, l_intPriceScale, l_strAmountRoundDiv);
            }
            //(8-2)　@単価優先方式　@((5)で取得した経過利子計算条件の計算基準区分が"単価優先方式"の場合)
            else if (WEB3CalcBaseFormDef.UNIT_PRICE_PRIORITY_FORM.equals(
                l_accruedInterestCalcCondition.getCalcBaseForm()))
            {
                //引数の債券銘柄.年間利払回数がZEROの場合
                if (l_intYearlyInterestPayments == 
                    Integer.parseInt(WEB3YearlyInterestPaymentsDef.DEFAULT))
                {
                    //引数の受渡代金計算結果.経過利子(外貨)、経過利子(円貨)にZERO、
                    //引数の受渡代金計算結果.警告区分一覧に"経過利子計算不可能"を追加し、return。
                    l_bondPriceCalcResult.setAccruedInterest(new BigDecimal("0"));
                    l_bondPriceCalcResult.setForeignAccruedInterest(new BigDecimal("0"));
                    l_bondPriceCalcResult.addWarningDiv(
                        WEB3BondWarningDivDef.ACCRUED_INTEREST_CANNOT_CALCULATION);
                    log.exiting(STR_METHOD_NAME);
                    return;
                }
                
                //単価　@=　@(6-2)で取得した利率　@×　@(1／引数の債券銘柄.年間利払回数(*注1))
                l_bdPriceWork = 
                    l_bdCoupon.multiply(
                        l_bdOne.divide(
                            l_bdYearlyInterestPayments, 10, BigDecimal.ROUND_HALF_UP));
                l_bdPrice = 
                    getCutOutValueForAccruedInterest(l_bdPriceWork, l_intUnitPriceScale, l_strUnitRoundDiv);
                
                //経過利子MAX　@=　@(算出した単価／100)×引数の受渡代金計算結果.数量
                l_bdAccruedInterestMaxWork = 
                    l_bdPrice.divide(
                        l_bdHundred, 10, BigDecimal.ROUND_HALF_UP).multiply(
                            l_bdQuantity);
                l_bdAccruedInterestMax = 
                    getCutOutValueForAccruedInterest(l_bdAccruedInterestMaxWork, l_intPriceScale, l_strUnitRoundDiv);
            }
            
            //(8-3)　@経過利子MAXチェック
            //     (7)で算出した経過利子　@>　@算出した経過利子MAX　@の場合、　@
            if (l_bdAccruedInterest.compareTo(l_bdAccruedInterestMax) > 0)
            {
                //経過利子　@=　@算出した経過利子MAX
                l_bdAccruedInterest = l_bdAccruedInterestMax;
            }
        }
        
        //(9)　@計算結果セット
        //(9-1)　@引数の債券銘柄.is外貨建()　@==　@true　@の場合
        if (l_bondProduct.isForeignCurrency())
        {
            //引数の受渡代金計算結果.経過利子(外貨)　@=　@算出した経過利子
            l_bondPriceCalcResult.setForeignAccruedInterest(l_bdAccruedInterest);
            
            //引数の受渡代金計算結果.経過利子(円貨)　@=　@this.calc円貨換算()の戻り値
            
            //(共通)通貨
            WEB3GentradeCurrency l_currency = WEB3GentradeCurrency.genCurrency(
                l_bondProduct.getInstitution().getInstitutionCode(),
                l_bondProduct.getCurrencyCode());
            
            BigDecimal l_bdJPYAmount =
                this.calcJPYAmount(
                    l_bdAccruedInterest, l_currency, true, true, l_bondPriceCalcResult.getFxRate());
            l_bondPriceCalcResult.setAccruedInterest(l_bdJPYAmount);
        }
        
        //(9-2)　@引数の債券銘柄.is外貨建()　@==　@false　@の場合
        else
        {
            //引数の受渡代金計算結果.経過利子(外貨)　@=　@NULL
            l_bondPriceCalcResult.setForeignAccruedInterest(null);
            
            //引数の受渡代金計算結果.経過利子(円貨)　@=　@算出した経過利子
            l_bondPriceCalcResult.setAccruedInterest(l_bdAccruedInterest);
        }
        log.exiting(STR_METHOD_NAME);
    }
    
    /**
     * (calc基準日)<BR>
     * 経過利子計算必須条件項目である基準日を算出する。<BR>
     * <BR>
     * ｢基本設計計算式書（債券）.doc<BR>
     * ３．calc基準日()　@｣参照。<BR>
     * @@param l_bondExecuteDateInfo - (債券約定日情報)<BR>
     * 債券約定日情報<BR>
     * @@param l_accruedInterestCalcCondition - (経過利子計算条件)<BR>
     * 経過利子計算条件<BR>
     * @@param l_bondProduct - (債券銘柄)<BR>
     * 債券銘柄オブジェクト<BR>
     * @@param l_bondPriceCalcResult - (債券受渡代金計算結果)<BR>
     * 債券受渡代金計算結果<BR>
     * @@return Date
     * @@throws WEB3BaseException 
     * @@roseuid 44F25934006D
     */
    protected Date calcBaseDate(
        WEB3BondExecuteDateInfo l_bondExecuteDateInfo,
        WEB3BondAccruedInterestCalcCondition l_accruedInterestCalcCondition, 
        WEB3BondProduct l_bondProduct,
        WEB3BondEstimatedPriceCalcResult l_bondPriceCalcResult) throws WEB3BaseException 
    {
        final String STR_METHOD_NAME = " calcBaseDate("
            + "WEB3BondExecuteDateInfo, WEB3BondAccruedInterestCalcCondition, "
            + "WEB3BondProduct, WEB3BondEstimatedPriceCalcResult)";
        log.entering(STR_METHOD_NAME);
        
        if (l_bondExecuteDateInfo == null || l_bondProduct == null 
            || l_accruedInterestCalcCondition == null || l_bondPriceCalcResult == null)
        {
            log.debug("パラメータ値不正。");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80017,
                this.getClass().getName() + "." + STR_METHOD_NAME);
        }
        
        //(1)　@基準日を算出する。
        //(1-1)　@経過利子計算条件.基準日区分　@==　@"受渡日"　@の場合　@ 
        String l_strBaseDateDiv = 
            l_accruedInterestCalcCondition.getBaseDateDiv();
        Date l_datBaseDate = null;
        if (WEB3BaseDateDivDef.DELIVERY_DATE.equals(l_strBaseDateDiv))
        {
            //基準日　@=　@引数の債券約定日情報.現地受渡日
            l_datBaseDate = l_bondExecuteDateInfo.getForeignDeliveryDate();
        }
        
        //(1-2)　@経過利子計算条件.基準日区分　@==　@"約定日"　@の場合
        else if (WEB3BaseDateDivDef.EXECUTE_DATE.equals(l_strBaseDateDiv))
        {
            //基準日　@=　@引数の債券約定日情報.現地約定日
            l_datBaseDate = l_bondExecuteDateInfo.getForeignExecuteDate();
        }
        
        //(2)　@日付チェックを行なう。
        //
        //[判定条件]
        //・(1)で算出した基準日　@<　@引数の債券銘柄.発行日　@の場合  
        //・(1)で算出した基準日　@<　@引数の債券銘柄.経過利子起算日　@の場合
        //・(1)で算出した基準日　@≧　@引数の債券銘柄.償還日　@の場合
        //
        BondProductRow l_bondProductRow = (BondProductRow)l_bondProduct.getDataSourceObject();
        if (WEB3DateUtility.compareToDay(l_datBaseDate, l_bondProductRow.getIssueDate()) < 0
            || WEB3DateUtility.compareToDay(l_datBaseDate, l_bondProduct.getAccruedInterestStartDay()) < 0
            || WEB3DateUtility.compareToDay(l_datBaseDate, l_bondProductRow.getMaturityDate()) >= 0)
        {
            //引数の受渡代金計算結果.経過利子(外貨)、経過利子(円貨)にZERO、
            //引数の受渡代金計算結果.警告区分一覧に"経過利子計算不可能"を追加し、return
            l_bondPriceCalcResult.setAccruedInterest(new BigDecimal("0"));
            l_bondPriceCalcResult.setForeignAccruedInterest(new BigDecimal("0"));
            l_bondPriceCalcResult.addWarningDiv(
                WEB3BondWarningDivDef.ACCRUED_INTEREST_CANNOT_CALCULATION);
            return null;
        }
        
        //(3)　@(1)で算出した基準日を返却する。
        log.exiting(STR_METHOD_NAME);
        return l_datBaseDate;
    }
    
    /**
     * (calc利払日)<BR>
     * 経過利子計算必須条件項目である利払日を算出する。<BR>
     * <BR>
     * ｢基本設計計算式書（債券）.doc<BR>
     * ４．calc利払日()　@｣参照。<BR>
     * @@param l_bondPriceCalcResult - (受渡代金計算結果)<BR>
     * 受渡代金計算結果<BR>
     * @@param l_bondProduct - (債券銘柄)<BR>
     * 債券銘柄オブジェクト<BR>
     * @@param l_datBaseDate - (基準日)<BR>
     * 基準日<BR>
     * @@return InterestPaymentDay
     * @@throws WEB3BaseException 
     * @@roseuid 44F259F00251
     */
    protected WEB3BondInterestPaymentDay calcInterestPaymentDay(
        WEB3BondEstimatedPriceCalcResult l_bondPriceCalcResult, 
        WEB3BondProduct l_bondProduct, 
        Date l_datBaseDate) throws WEB3BaseException 
    {
        final String STR_METHOD_NAME = " calcInterestPaymentDay("
            + "WEB3BondEstimatedPriceCalcResult, WEB3BondProduct, Date) ";
        log.entering(STR_METHOD_NAME);
        
        if (l_bondProduct == null || l_bondPriceCalcResult == null)
        {
            log.debug("パラメータ値不正。");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80017,
                this.getClass().getName() + "." + STR_METHOD_NAME);
        }
        
        //(0) 利払日クラスのインスタンスを生成する。
        WEB3BondInterestPaymentDay l_interestPaymentDay = 
            new WEB3BondInterestPaymentDay();
        
        //(1)　@引数の債券銘柄.利付タイプ　@==　@“固定利付債”　@の場合
        if (CouponTypeEnum.COUPON.equals(l_bondProduct.getCouponType()))
        {
            //(1-1)　@利払日チェック
            String l_strZeroForMonthDay = "0000";
            String l_strZeroForMonth = "00";
            String l_strZeroForDay = "00";
            String l_strInterestPaymentDay1 = 
                l_bondProduct.getInterestPaymentDay1();
            String l_strInterestPaymentDay2 = 
                l_bondProduct.getInterestPaymentDay2();
            
            if (l_strZeroForMonthDay.equals(l_strInterestPaymentDay1)
                || (l_strZeroForMonth.equals(l_strInterestPaymentDay1.substring(0, 2))
                    && !l_strZeroForDay.equals(l_strInterestPaymentDay1.substring(2)))
                || (l_strZeroForMonth.equals(l_strInterestPaymentDay2.substring(0, 2))
                    && !l_strZeroForDay.equals(l_strInterestPaymentDay2.substring(2)))
                || (!l_strZeroForMonth.equals(l_strInterestPaymentDay1.substring(0, 2))
                    && l_strZeroForDay.equals(l_strInterestPaymentDay1.substring(2)))
                || (!l_strZeroForMonth.equals(l_strInterestPaymentDay2.substring(0, 2))
                    && l_strZeroForDay.equals(l_strInterestPaymentDay2.substring(2))))
            {
                //引数の受渡代金計算結果.経過利子(外貨)、経過利子(円貨)にZERO、
                //引数の受渡代金計算結果.警告区分一覧に“経過利子計算不可能”を追加し、return。
                l_bondPriceCalcResult.setAccruedInterest(new BigDecimal("0"));
                l_bondPriceCalcResult.setForeignAccruedInterest(new BigDecimal("0"));
                l_bondPriceCalcResult.addWarningDiv(
                    WEB3BondWarningDivDef.ACCRUED_INTEREST_CANNOT_CALCULATION);
                log.exiting(STR_METHOD_NAME);
                return null;
            }
            
            //(1-2)　@周期(月単位)を算出する。
            //周期　@=　@　@12　@／　@引数の債券銘柄.年間利払回数
            //・引数の債券銘柄.年間利払回数　@==　@ZERO　@の場合
            //・12　@％　@(引数の債券銘柄.年間利払回数)　@!=　@ZEROの場合
            int l_intYearlyInterestPayments = l_bondProduct.getYearlyInterestPayments();
            if (l_intYearlyInterestPayments == Integer.parseInt(WEB3YearlyInterestPaymentsDef.DEFAULT))
            {
                //引数の受渡代金計算結果.経過利子(外貨)、経過利子(円貨)にZERO、
                //引数の受渡代金計算結果.警告区分一覧に“経過利子計算不可能”を追加し、return。
                l_bondPriceCalcResult.setAccruedInterest(new BigDecimal("0"));
                l_bondPriceCalcResult.setForeignAccruedInterest(new BigDecimal("0"));
                l_bondPriceCalcResult.addWarningDiv(
                    WEB3BondWarningDivDef.ACCRUED_INTEREST_CANNOT_CALCULATION);
                log.exiting(STR_METHOD_NAME);
                return null;
            }
            
            BigDecimal l_bdtwelve = new BigDecimal("12");
            BigDecimal l_bdYearlyInterestPayments = 
                new BigDecimal(String.valueOf(l_intYearlyInterestPayments));
            BigDecimal l_bdPeriod = 
                l_bdtwelve.divide(l_bdYearlyInterestPayments, 0, BigDecimal.ROUND_DOWN);
            if (l_bdtwelve.compareTo(l_bdPeriod.multiply(l_bdYearlyInterestPayments)) != 0)
            {
                //引数の受渡代金計算結果.経過利子(外貨)、経過利子(円貨)にZERO、
                //引数の受渡代金計算結果.警告区分一覧に“経過利子計算不可能”を追加し、return。
                l_bondPriceCalcResult.setAccruedInterest(new BigDecimal("0"));
                l_bondPriceCalcResult.setForeignAccruedInterest(new BigDecimal("0"));
                l_bondPriceCalcResult.addWarningDiv(
                    WEB3BondWarningDivDef.ACCRUED_INTEREST_CANNOT_CALCULATION);
                log.exiting(STR_METHOD_NAME);
                return null;
            }
            
            //(1-3)　@初回利払日を算出する。
            //経過利子起算日
            Date l_datAccruedInterestStartDay = 
                l_bondProduct.getAccruedInterestStartDay();
            String l_strAccruedInterestStartDay = 
                WEB3DateUtility.formatDate(l_datAccruedInterestStartDay, "yyyyMMdd");
            
            //(1-3-1)　@初回利払日　@=　@引数の債券銘柄.経過利子起算日の年(YYYY)　@
            //                      +　@引数の債券銘柄.利払日１(MMDD)
            String l_strFirstInterestPaymentDay = 
                l_strAccruedInterestStartDay.substring(0, 4) + l_strInterestPaymentDay1;
            
            //(1-3-2)　@日付調整を行なう。(this.calc日付調整())
            String l_strFirstInterestPaymentDayAdjusted = 
                this.calcDateAdjustment(l_strFirstInterestPaymentDay, l_bondProduct);
            Date l_datFirstInterestPaymentDayAdjusted = 
                WEB3DateUtility.getDate(l_strFirstInterestPaymentDayAdjusted, "yyyyMMdd");
            //(1-3-3)　@引数の債券銘柄.経過利子起算日 (YYYYMMDD)　@≧　@初回利払日　@の場合
            while (WEB3DateUtility.compareToDay(
                l_datAccruedInterestStartDay, 
                l_datFirstInterestPaymentDayAdjusted) >= 0)
            {
                log.debug("経過利子起算日≧初回利払日の場合 周期を加算する。");
                l_datFirstInterestPaymentDayAdjusted = 
                    getAdjustedDate(
                        l_datFirstInterestPaymentDayAdjusted,
                        l_bdPeriod.intValue(),
                        l_bondProduct);
            }
            
            //(1-3-4)　@算出した初回利払日を利払日.初回利払日にセットする。
            l_interestPaymentDay.setFirstInterestPaymentDay(l_datFirstInterestPaymentDayAdjusted);
            
            //(1-4)　@最終利払日を算出する。
            //(1-4-1)　@最終利払日　@=　@(　@引数の債券銘柄.償還日の年(YYYY)　@+　@
            //引数の債券銘柄.利払日１(MMDD)　@)に1年加算した(ex1)日付。
            //償還日
            Date l_datMaturityDate = l_bondProduct.getMaturityDate();
            String l_strMaturityDate = 
                WEB3DateUtility.formatDate(l_datMaturityDate, "yyyyMMdd");
            
            int l_intMaturityDateYear = 
                Integer.parseInt(l_strMaturityDate.substring(0, 4));
            String l_strFinalInterestPaymentDay = 
                l_intMaturityDateYear + 1 + l_strInterestPaymentDay1;
            
            //(1-4-2)　@日付調整を行なう。(this.calc日付調整())
            String l_strFinalInterestPaymentDayAdjusted = 
                this.calcDateAdjustment(
                    l_strFinalInterestPaymentDay, 
                    l_bondProduct);
            Date l_datFinalInterestPaymentDayAdjusted = 
                WEB3DateUtility.getDate(l_strFinalInterestPaymentDayAdjusted, "yyyyMMdd");
            
            //(1-4-3)　@引数の債券銘柄.償還日 (YYYYMMDD)　@≦　@最終利払日　@の場合
            while (WEB3DateUtility.compareToDay(
                l_datMaturityDate, l_datFinalInterestPaymentDayAdjusted) <= 0)
            {
                log.debug("償還日≦最終利払日の場合 周期を減算する。");
                l_datFinalInterestPaymentDayAdjusted = 
                    getAdjustedDate(
                        l_datFinalInterestPaymentDayAdjusted,
                        -l_bdPeriod.intValue(),
                        l_bondProduct);
            }
            
            //(1-4-4)　@算出した最終利払日を利払日.最終利払日にセットする。
            l_interestPaymentDay.setFinalInterestPaymentDay(l_datFinalInterestPaymentDayAdjusted);
            
            //(1-5)　@前回利払日/次回利払日を算出する。
            //(1-5-1)　@引数の基準日 (YYYYMMDD)　@<　@(1-3)で算出した初回利払日　@の場合
            if (WEB3DateUtility.compareToDay(l_datBaseDate, l_datFirstInterestPaymentDayAdjusted) < 0)
            {
                //前回利払日　@=　@　@引数の債券銘柄.経過利子起算日(YYYYMMDD)
                l_interestPaymentDay.setPreInterestPaymentDay(l_datAccruedInterestStartDay);
                
                //次回利払日　@=　@　@(1-3)で算出した初回利払日
                l_interestPaymentDay.setNextInterestPaymentDay(l_datFirstInterestPaymentDayAdjusted);
            }
            
            //(1-5-2)   引数の基準日 (YYYYMMDD)　@<　@(1-4)で算出した最終利払日　@の場合
            else if (WEB3DateUtility.compareToDay(l_datBaseDate, l_datFinalInterestPaymentDayAdjusted) < 0)
            {
                //(1-5-2-1)　@利払日work　@=　@　@
                //引数の基準日の年(YYYY)　@+　@引数の債券銘柄.利払日１(MMDD)
                String l_strBaseDate = WEB3DateUtility.formatDate(l_datBaseDate, "yyyyMMdd");
                String l_strInterestPaymentDayWork = 
                    l_strBaseDate.substring(0, 4) + l_strInterestPaymentDay1;
                
                //(1-5-2-2)　@日付調整を行なう。(this.calc日付調整())
                String l_strInterestPaymentDayWorkAdjusted = 
                    this.calcDateAdjustment(
                        l_strInterestPaymentDayWork, 
                        l_bondProduct);
                Date l_datInterestPaymentDayWorkAdjusted = 
                    WEB3DateUtility.getDate(l_strInterestPaymentDayWorkAdjusted, "yyyyMMdd");
                //(1-5-2-3)　@引数の基準日　@≧　@利払日work　@の場合
                while (WEB3DateUtility.compareToDay(
                    l_datBaseDate, l_datInterestPaymentDayWorkAdjusted) >= 0)
                {
                    l_datInterestPaymentDayWorkAdjusted = 
                        getAdjustedDate(
                            l_datInterestPaymentDayWorkAdjusted,
                            l_bdPeriod.intValue(),
                            l_bondProduct);
                }
                
                //(1-5-2-4)　@次回利払日のセット
                l_interestPaymentDay.setNextInterestPaymentDay(l_datInterestPaymentDayWorkAdjusted);
                
                //(1-5-2-5)　@引数の基準日　@>　@利払日work　@となるまで以下処理を繰返す。
                while (WEB3DateUtility.compareToDay(
                        l_datBaseDate, l_datInterestPaymentDayWorkAdjusted) < 0)
                {
                    l_datInterestPaymentDayWorkAdjusted = 
                        getAdjustedDate(
                            l_datInterestPaymentDayWorkAdjusted,
                            -l_bdPeriod.intValue(),
                            l_bondProduct);
                }
                
                //(1-5-2-6)　@前回利払日　@=　@利払日work
                l_interestPaymentDay.setPreInterestPaymentDay(l_datInterestPaymentDayWorkAdjusted);
            }
            
            //(1-5-3)　@(1-4)で算出した最終利払日　@≦　@引数の基準日　@の場合
            else
            {
                //前回利払日　@=　@　@(1-4)で算出した最終利払日
                l_interestPaymentDay.setPreInterestPaymentDay(l_datFinalInterestPaymentDayAdjusted);
                
                //次回利払日　@=　@　@引数の債券銘柄.償還日 (YYYYMMDD)
                l_interestPaymentDay.setNextInterestPaymentDay(l_datMaturityDate);
            }
            
            //(1-6)　@利率をセットする。
            //   利率　@=　@引数の債券銘柄.利率
            l_interestPaymentDay.setCoupon(
                new BigDecimal(String.valueOf(l_bondProduct.getCoupon())));
        }
        
        //(2)　@引数の債券銘柄.利付タイプ　@==　@“変動利付債”　@の場合
        else if (CouponTypeEnum.FLOATING_COUPON.equals(l_bondProduct.getCouponType()))
        {
            //(2-1)　@債券銘柄利率テーブルからレコードを取得する。
            String l_strWhere = "product_id = ? ";
            Object[] l_objWhereValues = {new Long(l_bondProduct.getProductId())};
            String l_strOrderBy = "interest_payment_day asc";
            List l_lisRecords = null;
            try
            {
                QueryProcessor l_processor = Processors.getDefaultProcessor();
                l_lisRecords = l_processor.doFindAllQuery(
                    BondProductCouponRow.TYPE,
                    l_strWhere,
                    l_strOrderBy,
                    null,
                    l_objWhereValues);
            }
            catch (DataQueryException l_dqex)
            {
                log.error(STR_METHOD_NAME, l_dqex);
                throw new WEB3SystemLayerException(
                    WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                    getClass().getName() + STR_METHOD_NAME);
            }
            catch (DataNetworkException l_dnex)
            {
                log.error(STR_METHOD_NAME, l_dnex);
                throw new WEB3SystemLayerException(
                    WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                    getClass().getName() + STR_METHOD_NAME);
            }
            
            //・データが取得出来ない場合
            if (l_lisRecords == null || l_lisRecords.isEmpty())
            {
                //引数の受渡代金計算結果.経過利子(外貨)、経過利子(円貨)にZERO、
                //引数の受渡代金計算結果.警告区分一覧に“経過利子計算不可能”を追加し、return。
                l_bondPriceCalcResult.setAccruedInterest(new BigDecimal("0"));
                l_bondPriceCalcResult.setForeignAccruedInterest(new BigDecimal("0"));
                l_bondPriceCalcResult.addWarningDiv(
                    WEB3BondWarningDivDef.ACCRUED_INTEREST_CANNOT_CALCULATION);
                log.exiting(STR_METHOD_NAME);
                return null;
            }
            
            //・債券銘柄利率[n].利払日　@≦　@引数の基準日の場合
            int l_intLength = l_lisRecords.size();
            BondProductCouponRow l_bondProductCouponEndRow = 
                (BondProductCouponRow)l_lisRecords.get(l_intLength - 1);
            if (WEB3DateUtility.compareToDay(
                l_bondProductCouponEndRow.getInterestPaymentDay(), 
                l_datBaseDate) <= 0)
            {
                //引数の受渡代金計算結果.経過利子(外貨)、経過利子(円貨)にZERO、
                //引数の受渡代金計算結果.警告区分一覧に“経過利子計算不可能”を追加し、return。
                l_bondPriceCalcResult.setAccruedInterest(new BigDecimal("0"));
                l_bondPriceCalcResult.setForeignAccruedInterest(new BigDecimal("0"));
                l_bondPriceCalcResult.addWarningDiv(
                    WEB3BondWarningDivDef.ACCRUED_INTEREST_CANNOT_CALCULATION);
                log.exiting(STR_METHOD_NAME);
                return null;
            }
            
            
            //引数の債券銘柄.経過利子起算日(YYYYMMDD)
            Date l_datAccruedInterestStartDay = 
                l_bondProduct.getAccruedInterestStartDay();
            Date l_datInterestPaymentDay = null;
            int l_intIndex = 0;
            for (l_intIndex = 0; l_intIndex < l_intLength; l_intIndex++)
            {
                BondProductCouponRow l_bondProductCouponRow = 
                    (BondProductCouponRow)l_lisRecords.get(l_intIndex);
                l_datInterestPaymentDay = 
                    l_bondProductCouponRow.getInterestPaymentDay();
                
                //(2-2)　@前回利払日/次回利払日を算出する。
                //(2-2-1)　@引数の債券銘柄.経過利子起算日(YYYYMMDD)　@≧　@
                //         債券銘柄利率[0].利払日　@の場合
                if (WEB3DateUtility.compareToDay(
                    l_datAccruedInterestStartDay,
                    l_datInterestPaymentDay) < 0)
                {
                    break;
                }
            }  
            
            BondProductCouponRow l_bondProductCouponRow = 
                (BondProductCouponRow)l_lisRecords.get(l_intIndex);
            l_datInterestPaymentDay = 
                l_bondProductCouponRow.getInterestPaymentDay();
            
            //(2-2-2)　@引数の基準日　@<　@債券銘柄利率[i].利払日　@の場合
            if (WEB3DateUtility.compareToDay(l_datBaseDate, l_datInterestPaymentDay) < 0)
            {
                //引数の前回利払日　@=　@　@引数の債券銘柄.経過利子起算日(YYYYMMDD)
                l_interestPaymentDay.setPreInterestPaymentDay(l_datAccruedInterestStartDay);
               
                //引数の次回利払日　@=　@　@債券銘柄利率[i].利払日
                l_interestPaymentDay.setNextInterestPaymentDay(l_datInterestPaymentDay);
            }
            
            //(2-2-3)　@債券銘柄利率[i].利払日　@≦　@引数の基準日　@の場合
            else
            {
                //(2-2-3-1)　@債券銘柄利率[i].利払日　@>　@引数の基準日　@となるまで以下処理を繰返す。
                while (WEB3DateUtility.compareToDay(l_datBaseDate, l_datInterestPaymentDay) >= 0)
                {
                    l_intIndex++;
                    l_bondProductCouponRow = 
                        (BondProductCouponRow)l_lisRecords.get(l_intIndex);
                    l_datInterestPaymentDay = 
                        l_bondProductCouponRow.getInterestPaymentDay();
                }
                
                //(2-2-3-2)　@前回利払日/次回利払日をセットする。
                //前回利払日　@=　@　@債券銘柄利率[i-1].利払日
                BondProductCouponRow l_bondProductCouponRowPre = 
                    (BondProductCouponRow)l_lisRecords.get(l_intIndex - 1);
                l_interestPaymentDay.setPreInterestPaymentDay(
                        l_bondProductCouponRowPre.getInterestPaymentDay());
                
                //次回利払日　@=　@　@債券銘柄利率[i].利払日
                l_interestPaymentDay.setNextInterestPaymentDay(l_datInterestPaymentDay);
                 
            }
            
            //(2-3)　@利率をセットする。
            //引数の利率　@=　@債券銘柄利率[i].利率　@
            l_interestPaymentDay.setCoupon(
                new BigDecimal(String.valueOf(l_bondProductCouponRow.getCoupon())));
            
            //利率がZEROの場合
            if (l_interestPaymentDay.getCoupon().doubleValue() == 0D)
            {
                //引数の受渡代金計算結果.経過利子(外貨)、経過利子(円貨)にZERO、
                //引数の受渡代金計算結果.警告区分一覧に“経過利子計算不可能”を追加し、return。
                l_bondPriceCalcResult.setAccruedInterest(new BigDecimal("0"));
                l_bondPriceCalcResult.setForeignAccruedInterest(new BigDecimal("0"));
                l_bondPriceCalcResult.addWarningDiv(
                    WEB3BondWarningDivDef.ACCRUED_INTEREST_CANNOT_CALCULATION);
                return null;
            }
        }
        
        //(3) 利払日クラスを返却する。
        log.exiting(STR_METHOD_NAME);
        return l_interestPaymentDay;
    }
    
    /**
     * (calc基準日数)<BR>
     * 経過利子計算必須条件項目である基準日数を算出する。<BR>
     * <BR>
     * ｢基本設計計算式書（債券）.doc<BR>
     * ５．calc基準日数()　@｣参照。<BR>
     * @@param l_bondPriceCalcResult - (受渡代金計算結果)<BR>
     * 受渡代金計算結果<BR>
     * @@param l_bondProduct - (債券銘柄)<BR>
     * 債券銘柄オブジェクト<BR>
     * @@param l_accruedInterestCalcCondition - (経過利子計算条件)<BR>
     * 経過利子計算条件<BR>
     * @@param l_datBaseDate - (基準日)<BR>
     * 基準日<BR>
     * @@param l_datPreInterestPaymentDay - (前回利払日)<BR>
     * 前回利払日<BR>
     * @@param l_datNextInterestPaymentDay - (次回利払日)<BR>
     * 次回利払日<BR>
     * @@throws WEB3BaseException 
     * @@roseuid 44F25AD901D4
     */
    protected void calcBaseDays(
        WEB3BondEstimatedPriceCalcResult l_bondPriceCalcResult, 
        WEB3BondProduct l_bondProduct, 
        WEB3BondAccruedInterestCalcCondition l_accruedInterestCalcCondition, 
        Date l_datBaseDate, 
        Date l_datPreInterestPaymentDay, 
        Date l_datNextInterestPaymentDay) throws WEB3BaseException 
    {
        final String STR_METHOD_NAME = " calcBaseDays("
            + "WEB3BondEstimatedPriceCalcResult, WEB3BondProduct, " 
            + "WEB3BondAccruedInterestCalcCondition, Date, Date, Date)";
        log.entering(STR_METHOD_NAME);
        
        if (l_bondProduct == null || l_accruedInterestCalcCondition == null
            || l_bondPriceCalcResult == null)
        {
            log.debug("パラメータ値不正。");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80017,
                this.getClass().getName() + "." + STR_METHOD_NAME);
        }
        
        //(1)　@経過利子計算条件.基準日数区分　@==　@"基準日数360日"　@の場合、
        int l_intBaseDays = 0;
        if (WEB3BaseDaysDivDef.BASE_DAYS_360.equals(
            l_accruedInterestCalcCondition.getBaseDaysDiv()))
        {
            l_intBaseDays = 360;
        }
        
        //(2)   経過利子計算条件.基準日数区分　@==　@"基準日数365日"　@の場合、
        else if (WEB3BaseDaysDivDef.BASE_DAYS_365.equals(
            l_accruedInterestCalcCondition.getBaseDaysDiv()))
        {
            l_intBaseDays = 365;
        }
        //(3)　@経過利子計算条件.基準日数区分　@==　@"閏年考慮"　@の場合
        else if (WEB3BaseDaysDivDef.LEAP_YEAR_CONSIDERATION.equals(
            l_accruedInterestCalcCondition.getBaseDaysDiv()))
        {
            //(3-1)　@利払期間に閏月がある場合
            //((引数の前回利払日　@<　@2月末日　@≦　@引数の次回利払日)　@and　@
            //(2月末日　@==　@29日(閏年)))
            //基準日数　@=　@366
            l_intBaseDays = 365;
            Date l_datFebEnd = getFebEndDate(l_datPreInterestPaymentDay);
            String l_strFebEnd = WEB3DateUtility.formatDate(l_datFebEnd, "yyyyMMdd");
            int l_intFebEndDay = Integer.parseInt(l_strFebEnd.substring(6));
            if (WEB3DateUtility.compareToDay(l_datPreInterestPaymentDay, l_datFebEnd) < 0
                && WEB3DateUtility.compareToDay(l_datFebEnd, l_datNextInterestPaymentDay) <= 0
                && l_intFebEndDay == 29)
            {
                l_intBaseDays = 366;
            }
            else
            {
                l_datFebEnd = getFebEndDate(l_datNextInterestPaymentDay);
                l_strFebEnd = WEB3DateUtility.formatDate(l_datFebEnd, "yyyyMMdd");
                l_intFebEndDay = Integer.parseInt(l_strFebEnd.substring(6));
                if (WEB3DateUtility.compareToDay(l_datPreInterestPaymentDay, l_datFebEnd) < 0
                    && WEB3DateUtility.compareToDay(l_datFebEnd, l_datNextInterestPaymentDay) <= 0
                    && l_intFebEndDay == 29)
                {
                    l_intBaseDays = 366;
                }
            }
        }
        
        //(4)　@経過利子計算条件.基準日数区分　@==　@"利払期間考慮"　@の場合
        else if (WEB3BaseDaysDivDef.PAYMENT_PERIOD_CONSIDERATION.equals(
            l_accruedInterestCalcCondition.getBaseDaysDiv()))
        {
            //(4-1) 　@(経過利子計算条件.経過利子計算タイプ　@==　@"オーストラリア方式")　@and
            //(引数の前回利払日　@==　@引数の債券銘柄.経過利子起算日)の場合
            if (WEB3AccruedInterestCalcTypeDef.AUSTRALIA_FORM.equals(
                    l_accruedInterestCalcCondition.getAccruedInterestCalcType())
                && WEB3DateUtility.compareToDay(
                    l_datPreInterestPaymentDay, 
                    l_bondProduct.getAccruedInterestStartDay()) == 0)
            {
                //基準日数　@=　@365
                l_intBaseDays = 365;
            }
            //(4-2) (4-1)　@以外の場合
            else 
            {
                //(*1)引数の債券銘柄.年間利払回数がZERO　@または"不定"の場合  
                int l_intYearlyInterestPayments = l_bondProduct.getYearlyInterestPayments();
                if (l_intYearlyInterestPayments == Integer.parseInt(WEB3YearlyInterestPaymentsDef.DEFAULT) 
                    || l_intYearlyInterestPayments == Integer.parseInt(WEB3YearlyInterestPaymentsDef.NO_FIXED_TIME))
                {
                    //引数の受渡代金計算結果.経過利子(外貨)、経過利子(円貨)にZERO、
                    //引数の受渡代金計算結果.警告区分一覧に"経過利子計算不可能"を追加し、return
                    l_bondPriceCalcResult.setAccruedInterest(new BigDecimal("0"));
                    l_bondPriceCalcResult.setForeignAccruedInterest(new BigDecimal("0"));
                    l_bondPriceCalcResult.addWarningDiv(
                        WEB3BondWarningDivDef.ACCRUED_INTEREST_CANNOT_CALCULATION);
                    log.exiting(STR_METHOD_NAME);
                    return;
                }
                
                //基準日数　@=　@(引数の次回利払日　@-　@引数の前回利払日) ×　@引数の債券銘柄.年間利払回数(*1)
                int l_intDays = 
                    getDaysNum(l_datNextInterestPaymentDay, l_datPreInterestPaymentDay);
                l_intBaseDays = l_intDays * l_intYearlyInterestPayments;
            }
        }
        //(5)　@経過利子計算条件.基準日数区分　@==　@"利払回数考慮"　@の場合
        else if (WEB3BaseDaysDivDef.PAYMENT_FREQUENCY_CONSIDERATION.equals(
            l_accruedInterestCalcCondition.getBaseDaysDiv()))
        {
            //(*1)引数の債券銘柄.年間利払回数がZERO　@または"不定"の場合  
            int l_intYearlyInterestPayments = l_bondProduct.getYearlyInterestPayments();
            if (l_intYearlyInterestPayments == Integer.parseInt(WEB3YearlyInterestPaymentsDef.DEFAULT) 
                || l_intYearlyInterestPayments == Integer.parseInt(WEB3YearlyInterestPaymentsDef.NO_FIXED_TIME))
            {
                //引数の受渡代金計算結果.経過利子(外貨)、経過利子(円貨)にZERO、
                //引数の受渡代金計算結果.警告区分一覧に"経過利子計算不可能"を追加し、return
                l_bondPriceCalcResult.setAccruedInterest(new BigDecimal("0"));
                l_bondPriceCalcResult.setForeignAccruedInterest(new BigDecimal("0"));
                l_bondPriceCalcResult.addWarningDiv(
                    WEB3BondWarningDivDef.ACCRUED_INTEREST_CANNOT_CALCULATION);
                log.exiting(STR_METHOD_NAME);
                return;
            }
            
            BigDecimal l_bdYearlyInterestPayments = 
                new BigDecimal(String.valueOf(l_intYearlyInterestPayments));
            
            //基準日数　@=　@360　@／　@引数の債券銘柄.年間利払回数(*1)
            BigDecimal l_bdDaysForYear = new BigDecimal("360");
            BigDecimal l_bdBaseDays = 
                l_bdDaysForYear.divide(
                    l_bdYearlyInterestPayments, 0, BigDecimal.ROUND_DOWN);
            
            //　@・360　@%　@引数の債券銘柄.年間利払回数　@!=　@ZEROの場合
            if (l_bdDaysForYear.compareTo(l_bdYearlyInterestPayments.multiply(l_bdBaseDays)) != 0)
            {
                //引数の受渡代金計算結果.経過利子(外貨)、経過利子(円貨)にZERO、
                //引数の受渡代金計算結果.警告区分一覧に“経過利子計算不可能”を追加し、return。
                l_bondPriceCalcResult.setAccruedInterest(new BigDecimal("0"));
                l_bondPriceCalcResult.setForeignAccruedInterest(new BigDecimal("0"));
                l_bondPriceCalcResult.addWarningDiv(
                    WEB3BondWarningDivDef.ACCRUED_INTEREST_CANNOT_CALCULATION);
                log.exiting(STR_METHOD_NAME);
                return;
            }
            l_intBaseDays = l_bdBaseDays.intValue();
        }
       
        //(6)　@経過利子計算条件.基準日数区分　@==　@"基準日閏年"　@の場合
        else if (WEB3BaseDaysDivDef.BASE_DATE_LEAP_YEAR.equals(
            l_accruedInterestCalcCondition.getBaseDaysDiv()))
        {
            String l_strBaseDateYear = 
                WEB3DateUtility.formatDate(l_datBaseDate, "yyyyMMdd").substring(0, 4);
            GregorianCalendar l_gregorianCalendar = new GregorianCalendar();
            if (l_gregorianCalendar.isLeapYear(Integer.parseInt(l_strBaseDateYear)))
            {
                //(6-1)　@基準日が閏年の場合、基準日数　@=　@366
                l_intBaseDays = 366;
            }
            else
            {
                //(6-2)　@基準日が閏年でない場合、基準日数　@=　@365
                l_intBaseDays = 365;
            }
        }
        
        //(7)　@算出した基準日数を引数の受渡代金計算結果.基準日数にセットする。
        l_bondPriceCalcResult.setCalcBaseDays(new Integer(l_intBaseDays));
        
        log.exiting(STR_METHOD_NAME);
    }
    
    /**
     * (calc経過日数)<BR>
     * 経過利子計算必須条件項目である経過日数を算出する。<BR>
     * <BR>
     * ｢基本設計計算式書（債券）.doc<BR>
     * ６．calc経過日数()　@｣参照。<BR>
     * @@param l_bondPriceCalcResult - (受渡代金計算結果)<BR>
     * 受渡代金計算結果<BR>
     * @@param l_accruedInterestCalcCondition - (経過利子計算条件)<BR>
     * 経過利子計算条件<BR>
     * @@param l_datBaseDate - (基準日)<BR>
     * 基準日<BR>
     * @@param l_datPreInterestPaymentDay - (前回利払日)<BR>
     * 前回利払日<BR>
     * @@throws WEB3BaseException 
     * @@roseuid 44F25B35004E
     */
    protected void calcElapsedDays(
        WEB3BondEstimatedPriceCalcResult l_bondPriceCalcResult,
        WEB3BondAccruedInterestCalcCondition l_accruedInterestCalcCondition, 
        Date l_datBaseDate, 
        Date l_datPreInterestPaymentDay) throws WEB3BaseException 
    {
        final String STR_METHOD_NAME = " calcElapsedDays("
            + "WEB3BondEstimatedPriceCalcResult, " 
            + "WEB3BondAccruedInterestCalcCondition, Date, Date)";
        log.entering(STR_METHOD_NAME);
        
        if (l_bondPriceCalcResult == null || l_accruedInterestCalcCondition == null)
        {
            log.debug("パラメータ値不正。");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80017,
                this.getClass().getName() + "." + STR_METHOD_NAME);
        }
        
        //(1)　@経過日数を算出する。
        //(1-1)　@経過利子計算条件.経過日数区分　@==　@"暦日ベース片端"　@の場合
        int l_intElapsedDays = 0;
        if (WEB3ElapsedDaysDivDef.CALENDAR_BASE_END.equals(
            l_accruedInterestCalcCondition.getElapsedDaysDiv()))
        {
            //経過日数　@=　@(引数の基準日　@-　@引数の前回利払日)
            l_intElapsedDays = 
                getDaysNum(l_datBaseDate, l_datPreInterestPaymentDay);
        }
        
        //(1-2)　@経過利子計算条件.経過日数区分　@==　@"暦日ベース両端"　@の場合
        else if (WEB3ElapsedDaysDivDef.CALENDAR_BASE_BOTH.equals(
            l_accruedInterestCalcCondition.getElapsedDaysDiv()))
        {
            //経過日数　@=　@(引数の基準日　@-　@引数の前回利払日)　@＋　@1
            l_intElapsedDays = 
                getDaysNum(l_datBaseDate, l_datPreInterestPaymentDay) + 1;
        }
        
        //(1-3)　@経過利子計算条件.経過日数区分　@==　@　@"一ヶ月30日片端"　@or　@
        //"一ヶ月30日両端MAX30日"　@or　@
        //"一ヶ月30日片端月末30日"　@or　@
        //"一ヶ月30日片端MAX30日"　@の場合
        if (WEB3ElapsedDaysDivDef.A_MONTH_30_END.equals(l_accruedInterestCalcCondition.getElapsedDaysDiv())
            || WEB3ElapsedDaysDivDef.A_MONTH_30_BOTH_MAX_30.equals(l_accruedInterestCalcCondition.getElapsedDaysDiv())
            || WEB3ElapsedDaysDivDef.A_MONTH_30_END_END_30.equals(l_accruedInterestCalcCondition.getElapsedDaysDiv())
            || WEB3ElapsedDaysDivDef.A_MONTH_30_END_MAX_30.equals(l_accruedInterestCalcCondition.getElapsedDaysDiv()))
        {
            Date l_datFebEnd = this.getFebEndDate(l_datPreInterestPaymentDay);
            
            //(1-3-1)　@①@前回利払日の月の日数の計算
            //引数の前回利払日の月の全体日数
            int l_intPreInterestPaymentMonthDays = 0;
            if (!((WEB3ElapsedDaysDivDef.A_MONTH_30_END.equals(l_accruedInterestCalcCondition.getElapsedDaysDiv())
                    || WEB3ElapsedDaysDivDef.A_MONTH_30_END_END_30.equals(l_accruedInterestCalcCondition.getElapsedDaysDiv()))
                && WEB3DateUtility.compareToDay(l_datPreInterestPaymentDay, l_datFebEnd) == 0))
            {
                l_intPreInterestPaymentMonthDays = 30;
            }
            
            //①@前回利払日の月の日数　@=　@引数の前回利払日の月の全体日数　@-　@引数の前回利払日(DD)
            String l_strPreInterestPaymentDay = 
                WEB3DateUtility.formatDate(l_datPreInterestPaymentDay, "yyyyMMdd");
            int l_intPreInterestPaymentDayForMonth = 
                Integer.parseInt(l_strPreInterestPaymentDay.substring(6));
            int l_intPreInterestPaymentDays = 
                l_intPreInterestPaymentMonthDays - l_intPreInterestPaymentDayForMonth;
            //(1-3-1-2)　@①@の計算結果　@<　@ZERO　@の場合、　@①@　@=　@ZERO
            if (l_intPreInterestPaymentDays < 0)
            {
                l_intPreInterestPaymentDays = 0;
            }
            
            //(1-3-1-3)　@引数の基準日の年月(YYYYMM)　@=　@引数の前回利払日の年月(YYYYMM)　@の場合、　@①@　@=　@ZERO
            String l_strBaseDate = WEB3DateUtility.formatDate(l_datBaseDate, "yyyyMMdd");
            if (l_strBaseDate.substring(0, 6).compareTo(
                l_strPreInterestPaymentDay.substring(0, 6)) == 0)
            {
                l_intPreInterestPaymentDays = 0;
            }
            
            //(1-3-2)　@②前回利払日の次の月から基準日の前月までの日数の計算
            
            //年調整月　@=　@(　@引数の基準日の年(YYYY)　@-　@引数の前回利払日の年(YYYY)　@)　@×　@12
            int l_intBaseDateDayForYear = Integer.parseInt(l_strBaseDate.substring(0, 4));
            int l_intYears = 
                (l_intBaseDateDayForYear
                - Integer.parseInt(l_strPreInterestPaymentDay.substring(0, 4))) * 12;
            
            //②前回利払日の次の月から基準日の前月までの日数　@=　@
            //(引数の基準日の月(MM)　@+　@　@年調整月(*1)　@-　@引数の前回利払日の月(MM)　@-　@1)　@×　@30
            int l_intBaseDateMonth = 
                Integer.parseInt(l_strBaseDate.substring(4, 6));
            int l_intPreInterestPaymentDayMonth = 
                Integer.parseInt(l_strPreInterestPaymentDay.substring(4, 6));
            int l_intDays = 
                (l_intBaseDateMonth + l_intYears - l_intPreInterestPaymentDayMonth - 1) * 30;
            
            //(1-3-2-1)　@②の計算結果　@<　@ZERO　@の場合、　@②　@=　@ZERO
            if (l_intDays < 0)
            {
                l_intDays = 0;
            }
            
            //(1-3-3)　@③基準日の月の日数の計算方法@
            //③基準日の月の日数　@=　@引数の基準日の日(DD)
            //(1-3-3-1)　@(経過利子計算条件.経過日数区分　@==　@　@"一ヶ月30日両端MAX30日"　@or　@
            //"一ヶ月30日片端MAX30日"　@or
            //"一ヶ月30日片端月末30日"　@)　@and 
            //(引数の基準日の日(DD)　@==　@31　@)　@の場合、
            int l_intBaseDateMonthDays = 0;
            Date l_datFebEndForBaseDate = this.getFebEndDate(l_datBaseDate);
            int l_intBaseDateDayForMonth = Integer.parseInt(l_strBaseDate.substring(6));
            if ((WEB3ElapsedDaysDivDef.A_MONTH_30_BOTH_MAX_30.equals(l_accruedInterestCalcCondition.getElapsedDaysDiv())
                    || WEB3ElapsedDaysDivDef.A_MONTH_30_END_MAX_30.equals(l_accruedInterestCalcCondition.getElapsedDaysDiv())
                    || WEB3ElapsedDaysDivDef.A_MONTH_30_END_END_30.equals(l_accruedInterestCalcCondition.getElapsedDaysDiv()))
                && l_intBaseDateDayForMonth == 31)
            {
                //③基準日の月の日数　@=　@30
                l_intBaseDateMonthDays = 30;
            }
            
            //(1-3-3-2)　@(経過利子計算条件.経過日数区分　@==　@　@"一ヶ月30日片端月末30日"　@)　@and 
            //(引数の基準日　@==　@2月末日　@)　@の場合、
            else if (WEB3ElapsedDaysDivDef.A_MONTH_30_END_END_30.equals(
                    l_accruedInterestCalcCondition.getElapsedDaysDiv())
                && WEB3DateUtility.compareToDay(l_datBaseDate, l_datFebEndForBaseDate) == 0)
            {
                l_intBaseDateMonthDays = 30;
            }

            //(1-3-3-3)　@上記以外は③はカレンダーどおりの日数。
            else 
            {
                l_intBaseDateMonthDays = l_intBaseDateDayForMonth;
            }
            
            //(1-3-3-4)　@引数の基準日の年月(YYYYMM)　@==　@引数の前回利払日の年月(YYYYMM)　@の場合
            //           ③　@=　@上記で設定した③の値　@?　@引数の前回利払日の日数(DD)
            if (l_strBaseDate.substring(0, 6).compareTo(l_strPreInterestPaymentDay.substring(0, 6)) == 0)
            {
                l_intBaseDateMonthDays = 
                    l_intBaseDateMonthDays - l_intPreInterestPaymentDayForMonth;
            }
            
            //(1-3-3-5)　@③の計算結果　@<　@ZERO　@の場合、　@③　@=　@ZERO
            if(l_intBaseDateMonthDays < 0)
            {
                l_intBaseDateMonthDays = 0;
            }
            

            //経過日数　@=　@①@前回利払日の月の日数　@+
            //②前回利払日の次の月から基準日の前月までの日数　@+
            //③基準日の月の日数
            l_intElapsedDays =
                l_intPreInterestPaymentDays + l_intDays + l_intBaseDateMonthDays;

            //(1-3-4)　@経過利子計算条件.経過日数区分　@==　@　@"一ヶ月30日両端MAX30日"　@の場合
            if (WEB3ElapsedDaysDivDef.A_MONTH_30_BOTH_MAX_30.equals(
                l_accruedInterestCalcCondition.getElapsedDaysDiv()))
            {
                //経過日数　@=　@算出した経過日数　@+　@1
                l_intElapsedDays = l_intElapsedDays + 1;
            }
        }
       
        //(2)   経過日数MAX判定
        //   (1)で算出した経過日数　@>　@引数の受渡代金計算結果.基準日数　@の場合、
        int l_intCalcBaseDays = l_bondPriceCalcResult.getCalcBaseDays().intValue();
        if (l_intElapsedDays > l_intCalcBaseDays)
        {
            //経過日数　@=　@引数の受渡代金計算結果.基準日数
            l_intElapsedDays = l_intCalcBaseDays;
        }
        
        //(3)   算出した経過日数を引数の受渡代金計算結果.経過日数にセットする。
        l_bondPriceCalcResult.setElapsedDays(new Integer(l_intElapsedDays));
        log.exiting(STR_METHOD_NAME);
    }
    
    /**
     * (calc日付調整)<BR>
     * 経過利子計算必須条件項目である利払日を算出する為の日付調整を行なう。<BR>
     * 戻り値はString(日付(YYYYMMDD))。<BR>
     * <BR>
     * ｢基本設計計算式書（債券）.doc<BR>
     * ７．calc日付調整()　@｣参照。<BR>
     * @@param l_strDate - (日付)<BR>
     * 日付(YYYYMMDD)<BR>
     * @@param l_bondProduct - (債券銘柄)<BR>
     * 債券銘柄オブジェクト<BR>
     * @@return String
     * @@throws WEB3BaseException 
     * @@roseuid 44F25BA60109
     */
    protected String calcDateAdjustment(String l_strDate, WEB3BondProduct l_bondProduct) 
        throws WEB3BaseException 
    {
        final String STR_METHOD_NAME = " calcDateAdjustment(String, WEB3BondProduct)";
        log.entering(STR_METHOD_NAME);
        
        if (l_strDate == null || l_bondProduct == null)
        {
            log.debug("パラメータ値不正。");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80017,
                this.getClass().getName() + "." + STR_METHOD_NAME);
        }
        //(1)　@日付(YYYYMMDD)　@=　@引数の日付
        String l_strDateRet = l_strDate;
        
        //(2)　@日付の日(DD)　@!=　@引数の債券銘柄.利払日１の日(DD)の場合、
        String l_strDayForInterestPaymentDay1 = 
            l_bondProduct.getInterestPaymentDay1().substring(2);
        String l_strDayForDateRet = l_strDateRet.substring(6);
        String l_strYearMonthForDateRet = l_strDateRet.substring(0, 6);
        if (l_strDayForDateRet.compareTo(l_strDayForInterestPaymentDay1) != 0)
        {
            //　@　@　@日付の日(DD)　@=　@引数の債券銘柄.利払日１の日(DD)
            l_strDayForDateRet = l_strDayForInterestPaymentDay1;
        }
        
        Calendar l_calendar = Calendar.getInstance();
        Date l_datRet = WEB3DateUtility.getDate(l_strYearMonthForDateRet, "yyyyMM");
        l_calendar.setTime(l_datRet);
        int l_intMaxDayOfMonth = l_calendar.getActualMaximum(Calendar.DAY_OF_MONTH);
        //(3)　@引数の日付の日(DD)　@==　@"99"の場合、
        //日付の日(DD)　@=　@日付の月(MM)　@の末日
        if ("99".equals(l_strDate.substring(6))) 
        {
            l_strDayForDateRet = l_intMaxDayOfMonth + "";
        }
        
        //(4)　@引数の日付の月(MM)　@==　@引数の債券銘柄.利払日２の月(MM)の場合、
        //日付の日(DD)　@=　@引数の債券銘柄.利払日２の日(DD)。
        String l_strMonthForInterestPaymentDay2 = 
            l_bondProduct.getInterestPaymentDay2().substring(0, 2);
        String l_strDayForInterestPaymentDay2 = 
            l_bondProduct.getInterestPaymentDay2().substring(2);
        String l_strMonth = l_strDate.substring(4, 6);
        if (l_strMonth.compareTo(l_strMonthForInterestPaymentDay2) == 0)
        {
            l_strDayForDateRet = l_strDayForInterestPaymentDay2;
        }
        l_strDateRet = l_strYearMonthForDateRet + l_strDayForDateRet;
        
        //(5)　@日付(YYYYMMDD)がカレンダー上にない場合(※)
        //日付の日(DD)　@=　@日付の月(MM)　@の末日
        if (Integer.parseInt(l_strDayForDateRet) > l_intMaxDayOfMonth) 
        {
            l_strDateRet = l_strYearMonthForDateRet + l_intMaxDayOfMonth;
        }
        
        //(6)　@日付(YYYYMMDD)を返却する。
        log.exiting(STR_METHOD_NAME);
        return l_strDateRet;
    }
    
    /**
     * (calc経過利子(国内債券))<BR>
     * 国内債券の場合、calc経過利子()からコールされる。<BR>
     * <BR>
     * 引数の債券受渡代金計算結果.経過利子(外貨),経過利子(円貨)にZERO<BR>
     * をセットする。<BR>
     * @@param l_bondPriceCalcResult - (債券受渡代金計算結果)<BR>
     * 債券受渡代金計算結果<BR>
     * @@param l_bondProduct - (債券銘柄)<BR>
     * 債券銘柄オブジェクト<BR>
     * @@param l_bondExecuteDateInfo - (債券約定日情報)<BR>
     * 債券約定日情報<BR>
     * @@throws WEB3BaseException 
     * @@roseuid 44F2B0210203
     */
    protected void calcAccruedInterestForDomesticBond(
        WEB3BondEstimatedPriceCalcResult l_bondPriceCalcResult, 
        WEB3BondProduct l_bondProduct, 
        WEB3BondExecuteDateInfo l_bondExecuteDateInfo) 
        throws WEB3BaseException 
    {
        final String STR_METHOD_NAME = " calcAccruedInterestForDomesticBond(" 
            + "WEB3BondEstimatedPriceCalcResult, WEB3BondProduct, WEB3BondExecuteDateInfo)";
        log.entering(STR_METHOD_NAME);
        
        if (l_bondPriceCalcResult == null)
        {
            log.debug("パラメータ値不正。");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80017,
                this.getClass().getName() + "." + STR_METHOD_NAME);
        }
        
        //経過利子(円貨)
        l_bondPriceCalcResult.setAccruedInterest(new BigDecimal("0"));
        
        //経過利子(外貨)
        l_bondPriceCalcResult.setForeignAccruedInterest(new BigDecimal("0"));
        
        log.exiting(STR_METHOD_NAME);
    }
    
    /**
     * get the days number bewteen two date
     * @@param l_date1
     * @@param l_date2
     * @@return int
     */
    private int getDaysNum(Date l_date1, Date l_date2)
    {
        return (int)((l_date1.getTime() - l_date2.getTime())/86400000);
    }
    
    /**
     * 日付を調整する
     * @@param l_datForAdjusted - (調整する日付)
     * @@param l_intPeriod - (周期)
     * @@param l_bondProduct - (債券銘柄)
     * @@return Date
     * @@throws WEB3BaseException
     */
    private Date getAdjustedDate(
        Date l_datForAdjusted, 
        int l_intPeriod,
        WEB3BondProduct l_bondProduct) throws WEB3BaseException
    {
        
        //周期（月単位）を加算する。
        Calendar l_calendar = Calendar.getInstance();
        l_calendar.setTime(l_datForAdjusted);
        l_calendar.add(Calendar.MONTH, l_intPeriod);
        l_datForAdjusted = l_calendar.getTime();
        
        String l_strForAdjusted =
            WEB3DateUtility.formatDate(l_datForAdjusted, "yyyyMMdd");
         
        //日付調整を行なう。(this.calc日付調整())
        l_strForAdjusted = 
            this.calcDateAdjustment(
                l_strForAdjusted, 
                l_bondProduct);
         
        return  WEB3DateUtility.getDate(l_strForAdjusted, "yyyyMMdd");
    }
    
    /**
     * get2月末日
     * @@param l_date 日付
     * @@return　@Date
     */
    private Date getFebEndDate(Date l_date)
    {
        Calendar l_calendar = Calendar.getInstance();
        l_calendar.setTime(l_date);
        l_calendar.set(Calendar.DAY_OF_MONTH, 1);
        l_calendar.set(Calendar.MONTH, 1);
        int l_intActualMaxDayOfMonth = 
            l_calendar.getActualMaximum(Calendar.DAY_OF_MONTH);
        l_calendar.set(Calendar.DAY_OF_MONTH, l_intActualMaxDayOfMonth);
        return l_calendar.getTime();
    }
    
    /**
     * 丸め処理
     * @@param l_bdValueForCut 丸めされる値
     * @@param l_intSacle 有効桁数
     * @@param l_strRoundDiv 丸め方式
     * @@return BigDecimal
     */
    private BigDecimal getCutOutValueForAccruedInterest(
        BigDecimal l_bdValueForCut, 
        int l_intSacle, 
        String l_strRoundDiv)
    {
        BigDecimal l_bdResult = null;
        //  1:切り捨て
        if (WEB3RoundDivDef.CUTTING_OFF.equals(l_strRoundDiv))
        {
            l_bdResult = 
                l_bdValueForCut.setScale(l_intSacle, BigDecimal.ROUND_DOWN);
        }
        //　@2:四捨五入
        else if (WEB3RoundDivDef.ROUNDING_OFF.equals(l_strRoundDiv))
        {
            l_bdResult = 
                l_bdValueForCut.setScale(l_intSacle, BigDecimal.ROUND_HALF_UP);
        }
        //  3:切り上げ
        else if (WEB3RoundDivDef.RAISING_TO_A_UNIT.equals(l_strRoundDiv))
        {
            l_bdResult = 
                l_bdValueForCut.setScale(l_intSacle, BigDecimal.ROUND_CEILING);
        }
        //  4:スイス特殊方式
        else if (WEB3RoundDivDef.SWITZERLAND_SPECIALITY_FORM.equals(l_strRoundDiv))
        {
            l_bdValueForCut = l_bdValueForCut.movePointLeft(1);
            l_bdResult = 
                l_bdValueForCut.setScale(l_intSacle, BigDecimal.ROUND_DOWN);
            double l_dblOneFourth = 0.25D;
            double l_dblThreeFourth = 0.75D;
            double l_dblForCut = 
                l_bdValueForCut.subtract(l_bdResult).movePointRight(l_intSacle).doubleValue();
            BigDecimal l_bdForCut = new BigDecimal("0"); 
            if (l_dblForCut > 0D 
                && l_dblForCut <= l_dblOneFourth)
            {
                l_bdForCut = new BigDecimal("0");
            }
            else if (l_dblForCut > l_dblOneFourth
                && l_dblForCut <= l_dblThreeFourth)
            {
                l_bdForCut = new BigDecimal("0.5").movePointLeft(l_intSacle);
            }
            else if (l_dblForCut > l_dblThreeFourth
                && l_dblForCut < 1)
            {
                l_bdForCut = new BigDecimal("1").movePointLeft(l_intSacle);
            }
            l_bdResult = l_bdResult.add(l_bdForCut).movePointRight(1);
        }
        return l_bdResult;
    }    

    /**
     * (calc初回利子調整額)<BR>
     * 初回利子調整額計算を行う。 <BR>
     * 個人向け国債・応募時のみ計算する。 <BR>
     * <BR>
     * ｢基本設計計算式書（債券）.doc <BR>
     * ８．calc初回利子調整額()　@｣参照。<BR>
     * <BR>
     * @@param l_calcResult - (受渡代金計算結果)<BR>
     * 受渡代金計算結果<BR>
     * @@param l_product - (債券銘柄)<BR>
     * 債券銘柄<BR>
     * @@param l_executeDateInfo - (債券約定日情報)<BR>
     * 債券約定日情報<BR>
     * @@throws WEB3BaseException
     */
    protected void calcInitialInterestAdjustAmount(
        WEB3BondEstimatedPriceCalcResult l_calcResult,
        WEB3BondProduct l_product,
        WEB3BondExecuteDateInfo l_executeDateInfo) throws WEB3BaseException
    {
        final String STR_METHOD_NAME = " calcInitialInterestAdjustAmount("
            + "WEB3BondEstimatedPriceCalcResult, WEB3BondProduct, WEB3BondExecuteDateInfo)";
        log.entering(STR_METHOD_NAME);

        if (l_calcResult == null || l_product == null || l_executeDateInfo == null)
        {
            log.debug("パラメータ値不正。");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80017,
                this.getClass().getName() + STR_METHOD_NAME,
                "パラメータ値不正。");
        }

        //(1) 源泉税率の取得
        //税率テーブルより国内債券の源泉徴収率の値を取得する。
        //(1-1) 税率オブジェクトの生成をする。
        //コンストラクタの引数( 引数.債券銘柄.証券会社コード、
        //                     70: 国内債券源泉徴収率、
        //                     引数.債券約定日情報.get発注日）
        String l_strInstitutionCode = l_product.getInstitution().getInstitutionCode();
        Timestamp l_tsBizDate = new Timestamp(l_executeDateInfo.getBizDate().getTime());

        WEB3GentradeTaxRate l_taxTate =
            new WEB3GentradeTaxRate(
                l_strInstitutionCode,
                WEB3DutyTypeDef.DOMESTIC_BOND_WITHHOLDING_TAX,
                l_tsBizDate);

        //  (1-2) 源泉税率 = 税率オブジェクト.get税率()
        //　@　@　@　@　@※パーセントの値で取得される。20%の場合、”20”となる。
        double l_dblTaxRate = l_taxTate.getTaxRate();

        //(2) 経過日数の算出
        //銘柄の初回利払日を算出し、発行日から初回利払日の６ヶ月前の差を算出する。
        // (2-1) this.calc利払日をコールし、利払日オブジェクトを取得する。
        //  [calc利払日への引数]
        //   受渡代金計算結果：引数.受渡代金計算結果
        //   債券銘柄：引数.債券銘柄
        //   基準日：引数.債券銘柄.発行日
        WEB3BondInterestPaymentDay l_interesetPaymentDay =
            this.calcInterestPaymentDay(
                l_calcResult,
                l_product,
                l_product.getIssueDate());

        //(2-2) 初回利払日の6ヶ月前(YYYYMM) = 利払日オブジェクト.get初回利払日(YYYYMM) - 6ヶ月
        Date l_datFirstInterestPaymentDay = l_interesetPaymentDay.getFirstInterestPaymentDay();
        String l_strPaymentDay =
            WEB3DateUtility.formatDate(l_datFirstInterestPaymentDay,
                WEB3GentradeTimeDef.DATE_FORMAT_YMD);

        Date l_datBeforeSixMonth =
            WEB3DateUtility.addMonth(
                WEB3DateUtility.getDate(l_strPaymentDay.substring(0, 6),
                    WEB3GentradeTimeDef.DATE_FORMAT_YM), -6);
        String l_strBeforeSixMonth =
            WEB3DateUtility.formatDate(l_datBeforeSixMonth, WEB3GentradeTimeDef.DATE_FORMAT_YM);

        //(2-3) 初回利払日の6ヶ月前(DD)     = 利払日オブジェクト.get初回利払日(DD)
        String l_strDay = l_strPaymentDay.substring(6);

        //(2-4) 調整後の初回利払日の6ヶ月前 = this.calc日付調整()の戻り値
        //      [calc日付調整への引数]
        //      日付 : 初回利払日の6ヶ月前(YYYYMMDD)
        //      債券銘柄 ： 引数.債券銘柄
        String l_strChangedDay = this.calcDateAdjustment(
            l_strBeforeSixMonth + l_strDay,
            l_product);

        //(2-5) 経過日数 = 引数.債券銘柄.発行日 - 調整後の初回利払日の6ヶ月前
        Date l_datChangedDay =
            WEB3DateUtility.getDate(l_strChangedDay, WEB3GentradeTimeDef.DATE_FORMAT_YMD);
        int l_intDays = this.getDaysNum(l_product.getIssueDate(), l_datChangedDay);

        //(3) 単位経過利子の算出(小数点以下8桁目以降切捨て)
        // 単位経過利子 = 引数.債券銘柄.get利率() ×
        //  ( 1 - ( (1)で取得した源泉税率 × 0.01) ) × (2)で算出した経過日数  ／ 365
        BigDecimal l_bdResultHundred =
            new BigDecimal(String.valueOf(l_product.getCoupon()));

        BigDecimal l_bdResultTemp =
            (new BigDecimal("1")).subtract(
                (new BigDecimal("0.01")).multiply(
                    new BigDecimal(String.valueOf(l_dblTaxRate))));

        BigDecimal l_dbResultTemMul =
            (l_bdResultHundred.multiply(l_bdResultTemp)).multiply(
                new BigDecimal(l_intDays + ""));

        BigDecimal l_dbInterestAdjust =
            l_dbResultTemMul.divide(
                new BigDecimal("365"), 7, BigDecimal.ROUND_DOWN);

        //(4) 初回利子調整額の算出（小数点以下切捨て）
        // 初回利子調整額 = 単位経過利子 × 引数.受渡代金計算結果.数量 ／ 100
        BigDecimal l_bdPaymentAdjust =
            (l_dbInterestAdjust.multiply(l_calcResult.getQuantity())).divide(
                new BigDecimal("100"), 0, BigDecimal.ROUND_DOWN);

        //(5) 引数.受渡代金計算結果.経過利子(円貨)へ(4)の計算結果をセットする。
        l_calcResult.setAccruedInterest(l_bdPaymentAdjust);
        log.exiting(STR_METHOD_NAME);
    }

    /**
     * (calc国内債券受渡代金)<BR>
     * calc国内債券受渡代金 <BR>
     * <BR>
     * １）債券受渡代金計算結果を生成する。 <BR>
     * 　@債券受渡代金計算結果.数量＝引数.数量 <BR>
     * 　@債券受渡代金計算結果.単価＝引数.債券銘柄.買付単価 <BR>
     * 　@債券受渡代金計算結果.債券注文種別判定＝引数.債券注文種別判定 <BR>
     * <BR>
     * ２）this.calc売買代金(債券受渡代金計算結果, 債券銘柄)を呼ぶ <BR>
     * 　@[引数] <BR>
     *    債券受渡代金計算結果：生成した債券受渡代金計算結果 <BR>
     * 　@ 債券銘柄：引数.債券銘柄 <BR>
     * <BR>
     * ３-１）債券銘柄.is個人向け国債() == trueの場合 <BR>
     *   this.calc初回利子調整額（債券受渡代金計算結果, 債券銘柄, 債券約定日情報）を呼ぶ <BR>
     * 　@  [引数] <BR>
     *      債券受渡代金計算結果：生成した債券受渡代金計算結果 <BR>
     * 　@   債券銘柄：引数.債券銘柄 <BR>
     * 　@   債券約定日情報：引数.債券約定日情報 <BR>
     * <BR>
     * ３－２） それ以外の場合、 <BR>
     *   this.calc経過利子（債券受渡代金計算結果, 債券銘柄, 債券約定日情報）を呼ぶ <BR>
     * 　@  [引数] <BR>
     *      債券受渡代金計算結果：生成した債券受渡代金計算結果 <BR>
     * 　@   債券銘柄：引数.債券銘柄 <BR>
     * 　@   債券約定日情報：引数.債券約定日情報 <BR>
     * <BR>
     * ４）this.calc受渡代金(債券受渡代金計算結果, 債券銘柄)を呼ぶ <BR>
     * 　@[引数] <BR>
     *    債券受渡代金計算結果：生成した債券受渡代金計算結果 <BR>
     * 　@ 債券銘柄：引数.債券銘柄 <BR>
     * <BR>
     * ５）債券受渡代金計算結果を返す<BR>
     * <BR>
     * @@param l_orderTypeJudge - (債券注文種別判定)<BR>
     * 債券注文種別判定<BR>
     * @@param l_dbQuantity - (数量)<BR>
     * 数量<BR>
     * @@param l_bondProduct - (債券銘柄)<BR>
     * 債券銘柄<BR>
     * @@param l_bondExecuteDateInfo - (債券約定日情報)<BR>
     * 債券約定日情報<BR>
     * @@return WEB3BondEstimatedPriceCalcResult
     * @@throws WEB3BaseException
     */
    public WEB3BondEstimatedPriceCalcResult calcBondDomesticEstimatedPrice(
        WEB3BondOrderTypeJudge l_orderTypeJudge,
        BigDecimal l_dbQuantity,
        WEB3BondProduct l_bondProduct,
        WEB3BondExecuteDateInfo l_bondExecuteDateInfo) throws WEB3BaseException
    {
        final String STR_METHOD_NAME = " calcBondDomesticEstimatedPrice("
            + "WEB3BondOrderTypeJudge, BigDecimal, WEB3BondProduct, WEB3BondExecuteDateInfo)";
        log.entering(STR_METHOD_NAME);

        if (l_bondProduct == null)
        {
            log.debug("パラメータ値不正。");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80017,
                this.getClass().getName() + STR_METHOD_NAME,
                "パラメータ値不正。");
        }

        //１）債券受渡代金計算結果を生成する。
        WEB3BondEstimatedPriceCalcResult l_calcResult =
            new WEB3BondEstimatedPriceCalcResult();

        //債券受渡代金計算結果.数量＝引数.数量
        l_calcResult.setQuantity(l_dbQuantity);

        //債券受渡代金計算結果.単価＝引数.債券銘柄.買付単価
        l_calcResult.setPrice(
            new BigDecimal(String.valueOf(l_bondProduct.getBuyPrice())));

        //債券受渡代金計算結果.債券注文種別判定＝引数.債券注文種別判定
        l_calcResult.setBondOrderTypeJudge(l_orderTypeJudge);

        //２）this.calc売買代金(債券受渡代金計算結果, 債券銘柄)を呼ぶ
        //[引数]
        // 債券受渡代金計算結果：生成した債券受渡代金計算結果
        // 債券銘柄：引数.債券銘柄
        this.calcTradingPrice(l_calcResult, l_bondProduct);

        //３-１）債券銘柄.is個人向け国債() == trueの場合
        //this.calc初回利子調整額（債券受渡代金計算結果, 債券銘柄, 債券約定日情報）を呼ぶ
        //[引数]
        // 債券受渡代金計算結果：生成した債券受渡代金計算結果
        // 債券銘柄：引数.債券銘柄
        // 債券約定日情報：引数.債券約定日情報
        if (l_bondProduct.isIndividualGovernmentBond())
        {
            this.calcInitialInterestAdjustAmount(
                l_calcResult,
                l_bondProduct,
                l_bondExecuteDateInfo);
        }
        else
        {
            //３－２） それ以外の場合、
            //this.calc経過利子（債券受渡代金計算結果, 債券銘柄, 債券約定日情報）を呼ぶ
            //[引数]
            // 債券受渡代金計算結果：生成した債券受渡代金計算結果
            // 債券銘柄：引数.債券銘柄
            // 債券約定日情報：引数.債券約定日情報
            this.calcAccruedInterest(
                l_calcResult,
                l_bondProduct,
                l_bondExecuteDateInfo);
        }

        //４）this.calc受渡代金(債券受渡代金計算結果, 債券銘柄)を呼ぶ
        //[引数]
        // 債券受渡代金計算結果：生成した債券受渡代金計算結果
        // 債券銘柄：引数.債券銘柄
        this.calcEstimatedPrice(l_calcResult, l_bondProduct);

        //５）債券受渡代金計算結果を返す
        log.exiting(STR_METHOD_NAME);
        return l_calcResult;
    }
}
@
