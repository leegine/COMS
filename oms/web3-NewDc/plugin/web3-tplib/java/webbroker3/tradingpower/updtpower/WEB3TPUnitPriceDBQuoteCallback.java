head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.15.01.00.06;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	86c4d7eb25155fb;
filename	WEB3TPUnitPriceDBQuoteCallback.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : 評価単価&lt;DB時価&gt;Callback(WEB3TPUnitPriceDBQuoteCallback.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2006/11/13 齊珂(中訊) 新規作成 モデルNo.078 - 082
Revesion History : 2009/09/22 車進 (中訊) モデル No.392
Revesion History : 2009/10/02 肖志偉 (中訊) モデル No.398
*/

package webbroker3.tradingpower.updtpower;

import java.util.Date;

import com.fitechlabs.xtrade.plugin.tc.gentrade.ProductTypeEnum;
import com.fitechlabs.xtrade.plugin.tc.gentrade.data.ProductRow;

import webbroker3.common.WEB3BaseException;
import webbroker3.common.WEB3BaseRuntimeException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.tradingpower.WEB3TPCalcCondition;
import webbroker3.tradingpower.define.WEB3TPSpecifiedPointDef;
import webbroker3.tradingpower.updtpower.asset.WEB3TPSecurityValuationProduct;
import webbroker3.tradingpower.updtpower.contract.WEB3TPTargetContractDetail;
import webbroker3.util.WEB3DateUtility;
import webbroker3.util.WEB3LogUtility;

/**
 * (評価単価&lt;DB時価&gt;Callback)<BR>
 * 
 * @@author 齊珂
 * @@version 1.0
 */
public class WEB3TPUnitPriceDBQuoteCallback extends WEB3TPUnitPriceStandardCallback
{
    /**
     * ログ出力ユーティリティ。
     */
    private static WEB3LogUtility log =
        WEB3LogUtility.getInstance(WEB3TPUnitPriceDBQuoteCallback.class);

    
    /**
     * (評価単価&lt;DB時価&gt;Callback)<BR>
     * (コンストラクタ) <BR>
     * <BR>
     * 1) 親クラス（評価単価<標準>Callback）のコンストラクタをコールする。 <BR>
     * 　@　@-super(:余力計算条件)をコールする。 <BR>
     * <BR>
     * [引数] <BR>
     * 　@余力計算条件 = 引数.余力計算条件 <BR>
     * @@param l_calcCondition - (余力計算条件)<BR>
     */
    public WEB3TPUnitPriceDBQuoteCallback(WEB3TPCalcCondition l_calcCondition)
    {
        //親クラス（評価単価<標準>Callback）のコンストラクタをコールする。
        super(l_calcCondition);
    }
    
    /**
     * (get評価単価<対象銘柄>) <BR>
     * <BR>
     * ※シーケンス図「(評価単価&lt;DB時価&gt;Callback)get評価単価<対象銘柄>」参照<BR>
     * @@param l_productRow - (銘柄Row)<BR>
     * @@return double
     */
    public double getUnitPrice(ProductRow l_productRow) 
    {
        final String STR_METHOD_NAME = " getUnitPrice(ProductRow) ";
        log.entering(STR_METHOD_NAME);
        
        //評価単価Temp = 0　@をセット
        double l_dblUnitPriceTemp = 0.0;
        
        long l_lngPrimaryMarketId = 0;
        
        //プロダクトタイプを取得
        ProductTypeEnum l_productTypeEnum = l_productRow.getProductType();
        
        //株式の場合
        if (ProductTypeEnum.EQUITY.equals(l_productTypeEnum))
        {
            //get営業日
            //当日(T+0)営業日を取得  
            //[引数]  
            //int = 0 
            Date l_datBizDate = this.calcCondition.getBizDate(WEB3TPSpecifiedPointDef.T_0);
            
            //銘柄IDを取得
            long l_lngProdcutId = l_productRow.getProductId();
            
            //市場IDを取得
            l_lngPrimaryMarketId = l_productRow.getPrimaryMarketId();
                       
            //get終値<DB時価>
            // 終値を取得する。  
            // [引数]  
            // long = getProductId()の戻り値  
            // Date = get営業日()の戻り値  
            // long = getPrimaryMarketId()の戻り値  
            // boolean = true  
            l_dblUnitPriceTemp = this.calcCondition.getClosingPriceDBQuote(
                l_lngProdcutId, 
                l_datBizDate, 
                l_lngPrimaryMarketId, 
                true);
        }
        
        //評価単価Temp = 0 の場合
        if (l_dblUnitPriceTemp == 0)
        {
            //評価単価Temp = ProductRow.評価単価(前日終値)をセット
            l_dblUnitPriceTemp = l_productRow.getEstimationPrice();
           
        }
        
        //計算した評価単価Tempを返却する
        log.exiting(STR_METHOD_NAME);
        return l_dblUnitPriceTemp; 
    }
    
    /**
     * (get評価単価<比較>)<BR>
     * <BR>
     * ※シーケンス図「(評価単価&lt;DB時価&gt;Callback)get評価単価<比較>」参照<BR>
     * @@param l_dblPrice - (単価)<BR>
     * @@param l_product - (対象銘柄)<BR>
     * @@return double
     */
    public double getUnitPrice(double l_dblPrice, WEB3TPSecurityValuationProduct l_product) 
    {
        final String STR_METHOD_NAME = " getUnitPrice(double, WEB3TPSecurityValuationProduct) ";
        log.entering(STR_METHOD_NAME);
        
        double l_dblUnitPrice = l_product.getUnitPrice();
        
        //return MIN(引数.単価 , 引数.対象銘柄.get評価単価())
        double l_dblMin = Math.min(l_dblPrice, l_dblUnitPrice);
        
        log.exiting(STR_METHOD_NAME);
        return l_dblMin;
    }
    
    /**
     * (get評価単価<比較不要>)<BR>
     * <BR>
     * ※シーケンス図「(評価単価&lt;DB時価&gt;Callback)get評価単価<比較不要>」参照<BR>
     * @@param l_dblPrice - (単価)<BR>
     * @@param l_product - (対象銘柄)<BR>
     * @@return double
     */
    public double getUnitPriceNotCompare(double l_dblPrice, WEB3TPSecurityValuationProduct l_product) 
    {
        final String STR_METHOD_NAME = " getUnitPriceNotCompare(double, WEB3TPSecurityValuationProduct) ";
        log.entering(STR_METHOD_NAME);
        
        //return 引数.単価
        log.exiting(STR_METHOD_NAME);
        return l_dblPrice;
    }
    
    /**
     * (get評価単価<建株>))<BR>
     * <BR>
     * ※シーケンス図「(評価単価&lt;DB時価&gt;Callback)get評価単価<建株>」参照<BR>
     * @@param l_targetContractDetail - (対象建玉詳細)
     * @@return double
     */
    public double getUnitPrice(WEB3TPTargetContractDetail l_targetContractDetail) 
    {
        final String STR_METHOD_NAME = " getUnitPrice(WEB3TPTargetContractDetail) ";
        log.entering(STR_METHOD_NAME);
        
        //評価単価Temp = 0　@をセット
        double l_dblUnitPriceTemp = 0.0;
        double l_dblClosingPrice = 0.0;
        
        //get営業日
        Date l_datBizDate0 = this.calcCondition.getBizDate(WEB3TPSpecifiedPointDef.T_0);
        
        //get建日
        Date l_datOpenDate = l_targetContractDetail.getOpenDate();
        
        //get当初建日
        Date l_datFirstOpenDate = l_targetContractDetail.getFirstOpenDate();
        
        //get銘柄ID
        long l_productId = l_targetContractDetail.getProductId();
        
        //get市場ID
        long l_marketId = l_targetContractDetail.getMarketId();
        
        //get権利落ち日
        Date l_datRightsOffDate = this.calcCondition.getRightsOffDate(l_productId);

        boolean l_blnIsDecimalDevide = false;
        try
        {
            //小数倍分割の場合
            l_blnIsDecimalDevide = this.isDecimalDevide(l_productId);
        }
        catch (WEB3BaseException l_ex)
        {
            log.error(l_ex.getMessage(), l_ex);
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BaseRuntimeException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                STR_METHOD_NAME,
                l_ex.getMessage(),
                l_ex);
        }

        //(建日 < T+0 || (建日 == T+0 && 当初建日 != null && 建日 != 当初建日) )
        if(WEB3DateUtility.compare(l_datOpenDate, l_datBizDate0) < 0
            || (WEB3DateUtility.compare(l_datOpenDate, l_datBizDate0) == 0
                && l_datFirstOpenDate != null 
                && WEB3DateUtility.compare(l_datOpenDate,l_datFirstOpenDate) != 0))
        {
            //権利落ち日=営業日(T+0)の場合
            if(WEB3DateUtility.compareToDay(l_datBizDate0, l_datRightsOffDate) == 0
                && l_blnIsDecimalDevide)
            {
                //評価単価Temp = 引数.対象建玉詳細.get基準値<前日終値>
                l_dblUnitPriceTemp = l_targetContractDetail.getLastClosingPrice();
            }
            else
            {
                //終値を取得する。
                //long = get銘柄ID()の戻り値  
                //Date = get営業日()の戻り値  
                //long = get市場ID()の戻り値  
                //boolean = false 
                l_dblClosingPrice = this.calcCondition.getClosingPriceDBQuote(
                    l_productId, 
                    l_datBizDate0, 
                    l_marketId, 
                    false);
                
                //get終値=0の場合
                if(l_dblClosingPrice == 0)
                {
                    //評価単価Temp = 引数.対象建玉詳細.get基準値<前日終値>
                    l_dblUnitPriceTemp = l_targetContractDetail.getLastClosingPrice();
                }
                //get終値!=0の場合
                else
                {
                    //評価単価Temp = get終値<DB時価>()の戻り値
                    l_dblUnitPriceTemp = l_dblClosingPrice;
                }
            }
        }
        
        //以外((通常) 當日建玉)の場合
        else
        {
            //権利落ち日=営業日(T+0)の場合
            if(WEB3DateUtility.compareToDay(l_datBizDate0, l_datRightsOffDate) == 0 && l_blnIsDecimalDevide)
            {
                //評価単価Temp = 0
                l_dblUnitPriceTemp = 0.0;
            }
            //権利落ち日!=営業日(T+0)の場合
            else
            {
                //get終値<DB時価>
                //long = get銘柄ID()の戻り値  
                //Date = get営業日()の戻り値  
                //long = get市場ID()の戻り値  
                //boolean = false 
                l_dblClosingPrice = this.calcCondition.getClosingPriceDBQuote(
                    l_productId, 
                    l_datBizDate0, 
                    l_marketId, 
                    false);
                
                //get終値=0の場合
                if (l_dblClosingPrice == 0)
                {
                    //評価単価Temp = 0
                    l_dblUnitPriceTemp = 0;                  
                }
                //get終値!=0の場合
                else
                {
                    //評価単価Temp = get終値<DB時価>()の戻り値
                    l_dblUnitPriceTemp = l_dblClosingPrice;
                }
            }
        }
        log.exiting(STR_METHOD_NAME);
        return l_dblUnitPriceTemp;
    }
}
@
