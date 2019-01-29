head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.15.00.59.40;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	86c4d7eb25155fb;
filename	WEB3TPUnitPriceStandardCallback.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : 評価単価<標準>Callback(WEB3TPStandardUnitPriceCallback.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2005/02/04 齋藤　@栄三 (FLJ) 新規作成
Revesion History : 2008/01/22 張騰宇(中訊) モデルNo.236、248
Revesion History : 2009/09/22 車進 (中訊) モデル No.393
Revesion History : 2009/10/02 肖志偉 (中訊) モデル No.396,399
*/
package webbroker3.tradingpower.updtpower;

import java.util.Date;

import webbroker3.common.WEB3BaseException;
import webbroker3.common.WEB3BaseRuntimeException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.tradingpower.WEB3TPCalcCondition;
import webbroker3.tradingpower.define.WEB3TPSpecifiedPointDef;
import webbroker3.tradingpower.updtpower.asset.WEB3TPSecurityValuationProduct;
import webbroker3.tradingpower.updtpower.contract.WEB3TPTargetContractDetail;
import webbroker3.util.WEB3DateUtility;
import webbroker3.util.WEB3LogUtility;

import com.fitechlabs.xtrade.plugin.tc.eqtype.data.EqtypeProductRow;
import com.fitechlabs.xtrade.plugin.tc.gentrade.BooleanEnum;
import com.fitechlabs.xtrade.plugin.tc.gentrade.ProductTypeEnum;
import com.fitechlabs.xtrade.plugin.tc.gentrade.data.ProductRow;

/**
 * (評価単価<標準>Callback)
 */
public class WEB3TPUnitPriceStandardCallback implements WEB3TPUnitPriceCallback
{
    /**
     * ログ出力ユーティリティ。
     */
    private static WEB3LogUtility log =
        WEB3LogUtility.getInstance(WEB3TPUnitPriceStandardCallback.class);
    protected WEB3TPCalcCondition calcCondition;
    
    /**
     * (評価単価<標準>Callback)<BR>
     * コンストラクタ<BR>
     * @@param l_calcCondition
     */
    public WEB3TPUnitPriceStandardCallback(WEB3TPCalcCondition l_calcCondition)
    {
        calcCondition = l_calcCondition;
    }

    /**
     * (get評価単価<対象銘柄>)<BR>
     * 当日終値が存在する場合は当日終値を返す。<BR>
     * 当日終値が存在しない場合は前日終値を返す。<BR>
     * @@param l_productRow - (銘柄Row)
     * @@return double
     */
    public double getUnitPrice(ProductRow l_productRow) 
    {
        //プロダクトタイプを取得
        ProductTypeEnum l_productTypeEnum = l_productRow.getProductType();

        //評価単価
        double l_dblUnitPrice = 0.0;
        
        //株式の場合
        if(ProductTypeEnum.EQUITY.equals(l_productTypeEnum))
        {
            //営業日(T+0)を取得
            Date l_datBizDate0 = calcCondition.getBizDate(WEB3TPSpecifiedPointDef.T_0);
            
            //銘柄IDを取得
            long l_productId = l_productRow.getProductId();
            
            //市場IDを取得
            long l_marketId = l_productRow.getPrimaryMarketId();
            
            //終値を取得
            l_dblUnitPrice = calcCondition.getClosingPrice(l_productId, l_datBizDate0, l_marketId, true);
        }        

        //銘柄タイプが外国株の場合
        else if (ProductTypeEnum.FOREIGN_EQUITY.equals(l_productTypeEnum))
        {
            //getProductId
            long l_lngProdcutId = l_productRow.getProductId();

            //get外株終値
            //[引数]
            //銘柄ID = getProductId()の戻り値
            double l_dblFeqLastClosingPrice = this.calcCondition.getFeqLastClosingPrice(l_lngProdcutId);

            //評価単価Temp = get外株終値
            l_dblUnitPrice = l_dblFeqLastClosingPrice;

            //評価単価Tempを返却する
            return l_dblUnitPrice;
        }

        //評価単価の値に変更がない場合(株式以外 又は 株式(ミニ株含む)で終値を取得できない場合)
        if(l_dblUnitPrice == 0.0)
        {
            //銘柄Rowの評価単価をセット
            l_dblUnitPrice = l_productRow.getEstimationPrice();
        }
        
        return l_dblUnitPrice;
    }

    /**
     * (get評価単価<比較>)<BR>
     * 引数.数値と対象銘柄の評価単価比較して小さい方を返す。<BR>
     * @@param l_double - (数値)
     * @@param l_product - (対象銘柄)
     * @@return double
     */
    public double getUnitPrice(double l_dblComp, WEB3TPSecurityValuationProduct l_product) 
    {
        return Math.min(l_dblComp, l_product.getUnitPrice());
    }

    /**
     * (get評価単価<比較不要>)<BR>
     * 引数.数値を返す。<BR>
     * @@param l_double - (数値)
     * @@param l_product - (対象銘柄)
     * @@return double
     */
    public double getUnitPriceNotCompare(double l_dblComp, WEB3TPSecurityValuationProduct l_product) 
    {
        return l_dblComp;
    }

    /**
     * (get評価単価<建株>)<BR>
     * 確定建株の場合<BR>
     * ・当日権利落ち日の場合は前日終値を返す。<BR>
     * ・当日終値が存在する場合は当日終値を返す。<BR>
     * ・当日終値が存在しない場合は前日終値を返す。<BR>
     * 当日建株の場合<BR>
     * ・当日終値を返す。<BR>
     * @@param l_targetContractDetail - (対象建玉詳細)
     * @@return double
     */
    public double getUnitPrice(WEB3TPTargetContractDetail l_targetContractDetail) 
    {
        final String STR_METHOD_NAME = " getUnitPrice(WEB3TPTargetContractDetail) ";

        //営業日(T+0)を取得
        Date l_datBizDate0 = calcCondition.getBizDate(WEB3TPSpecifiedPointDef.T_0);
        
        //建日を取得
        Date l_datOpenDate = l_targetContractDetail.getOpenDate();
        
        //当初建日を取得
        Date l_datFirstOpenDate = l_targetContractDetail.getFirstOpenDate();
        
        //評価単価
        double l_dblUnitPrice = 0.0;
        
        //銘柄IDを取得
        long l_productId = l_targetContractDetail.getProductId();
        
        //市場IDを取得
        long l_marketId = l_targetContractDetail.getMarketId();
        
        //権利落ち日を取得
        Date l_datRightsOffDate = calcCondition.getRightsOffDate(l_productId);

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

        //確定建株または、分割新建株の場合
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
                //基準値(前日終値)を採用
                l_dblUnitPrice = l_targetContractDetail.getLastClosingPrice();
            }
            else
            {
                //当日終値を取得
                l_dblUnitPrice = calcCondition.getClosingPrice(l_productId, l_datBizDate0, l_marketId, false);
                
                //当日終値=0の場合(テーブルにレコードがない or 列に0が入ってた時)
                if(l_dblUnitPrice == 0.0)
                {
                    //基準値(前日終値)を採用
                    l_dblUnitPrice = l_targetContractDetail.getLastClosingPrice();
                }
            }
        }
        //当日建株の場合
        else
        {
            //権利落ち日<>営業日(T+0)の場合(権利落ち日の時は、初期値0が評価単価になる)
            if(WEB3DateUtility.compareToDay(l_datBizDate0, l_datRightsOffDate) != 0
                || (WEB3DateUtility.compareToDay(l_datBizDate0, l_datRightsOffDate) == 0 && !l_blnIsDecimalDevide))
            {
                //当日終値を評価単価にセットする(終値がない場合は0になる)
                l_dblUnitPrice = calcCondition.getClosingPrice(l_productId, l_datBizDate0, l_marketId, false);
            }
        }
        
        return l_dblUnitPrice;
    }

    /**
     * (is小数倍分割)<BR>
     * 小数倍分割かどうか判別する。<BR> 
     *<BR>
     * １）　@DB検索<BR> 　@ 
     * 　@余力データソースアクセス管理.get株式銘柄コールする。<BR> 
     *<BR>
     *　@[条件]<BR> 
     *　@銘柄ＩD = 引数. 銘柄ID<BR> 
     *<BR>
     * ２）　@小数倍分割かどうか判別する。<BR>　@ 
     * 　@２-１）で取得した株式銘柄Rowの「小数倍分割フラグ」が”１：小数倍分割”の場合は、<BR> 
     * 　@ｔrueを返却する。 <BR>
     *<BR>
     *　@２-２）　@上記以外の場合は、<BR> 
     *　@falseを返却する。 <BR>
     * @@param l_lngProductId - (銘柄ID)
     * 銘柄ID
     * @@return boolean
     * @@throws WEB3BaseException
     */	
    protected boolean isDecimalDevide(long l_lngProductId) throws WEB3BaseException
    {

    	EqtypeProductRow l_eqtypeProductRow =
            WEB3TPPersistentDataManager.getInstance().getEqtypeProduct(l_lngProductId);

        if (l_eqtypeProductRow != null)
        {
            //１）で取得した「小数倍分割フラグ」が”１：小数倍分割”の場合は
            //ｔrueを返却する。
            if (BooleanEnum.TRUE.equals(l_eqtypeProductRow.getDecimalDevideFlag()))
            {
                return true;
            }
        }

        return false;
    }
}
@
