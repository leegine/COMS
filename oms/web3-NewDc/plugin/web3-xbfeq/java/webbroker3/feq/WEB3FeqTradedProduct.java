head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.16.03.46.05;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	6004d80209d0226;
filename	WEB3FeqTradedProduct.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : 外国株式取引銘柄(WEB3FeqTradedProduct)
Author Name      : Daiwa Institute of Research
Revesion History : 2005/07/13  艾興(中訊) 新規作成
Revesion History : 2007/11/19 馮海涛(中訊) モデルNo.362
*/
package webbroker3.feq;

import java.sql.Timestamp;
import java.util.Date;

import com.fitechlabs.xtrade.kernel.data.DataFindException;
import com.fitechlabs.xtrade.plugin.tc.gentrade.data.TradedProductRow;
import com.fitechlabs.xtrade.plugin.tc.xbfeq.data.FeqTradedProductRow;
import com.fitechlabs.xtrade.plugin.tc.xbfeq.stdimpls.FeqTradedProductImpl;

import webbroker3.common.WEB3BaseException;
import webbroker3.common.WEB3BaseRuntimeException;
import webbroker3.common.WEB3SystemLayerException;
import webbroker3.common.define.WEB3TradeStopDef;
import webbroker3.gentrade.WEB3GentradeBizDate;
import webbroker3.gentrade.WEB3GentradeTradingTimeManagement;
import webbroker3.util.WEB3DateUtility;
import webbroker3.util.WEB3LogUtility;


/**
 * (外国株式取引銘柄) <BR>
 * 外国株式取引銘柄
 *
 * @@author 艾興
 * @@version 1.0
 */
public class WEB3FeqTradedProduct extends FeqTradedProductImpl
{
    /**
     * ログユーティリティ<BR>
     */
     private static WEB3LogUtility log =
         WEB3LogUtility.getInstance(WEB3FeqTradedProduct.class);

     FeqTradedProductRow l_feqTradedProductRow =
        (FeqTradedProductRow)this.getDataSourceObject();

    /**
     * コンストラクタ<BR>
     */
    public WEB3FeqTradedProduct(TradedProductRow l_trow)
        throws DataFindException
    {
        super(l_trow);
    }

    /**
     * コンストラクタ<BR>
     */
    public WEB3FeqTradedProduct(FeqTradedProductRow l_row)
        throws DataFindException
    {
        super(l_row);
    }

    /**
     * (is上場期間内) <BR>
     * （isOpen） <BR>
     *  <BR>
     * 上場期間内かを判定する。 <BR>
     *  <BR>
     * 上場期間内であればtrueを返却する。以外、falseを返却する。 <BR>
     *  <BR>
     * [上場期間内の条件] <BR>
     * this.外株取引銘柄Params.上場（登録）日 <= 　@<BR>
     * 　@　@　@*発注日 < this.外株取引銘柄Params.上場(登録）廃止日 <BR>
     *  <BR>
     * 発注日：　@取引時間管理.get発注日()の戻り値。 <BR>
     * @@return boolean
     * @@throws WEB3BaseException
     * @@roseuid 428051EB02A8
     */
    public boolean isOpen() throws WEB3BaseException
    {
        final String STR_METHOD_NAME = "isOpen() ";
        log.entering(STR_METHOD_NAME);

        Date l_datBizDate = WEB3GentradeTradingTimeManagement.getOrderBizDate();

        if ((WEB3DateUtility.compare(l_feqTradedProductRow.getListedDate(), l_datBizDate) <= 0)
            && (WEB3DateUtility.compare(l_datBizDate, l_feqTradedProductRow.getUnlistedDate()) < 0))
        {
            log.exiting(STR_METHOD_NAME);
            return true;
        }
        else
        {
            log.exiting(STR_METHOD_NAME);
            return false;
        }


    }

    //修正劉
    public static boolean isOpen( Date l_datBizDate,FeqTradedProductRow l_feqTradedProductRow ) throws WEB3BaseException
    {
        final String STR_METHOD_NAME = "isOpen() ";
        log.entering(STR_METHOD_NAME);

        if ((WEB3DateUtility.compare(l_feqTradedProductRow.getListedDate(), l_datBizDate) <= 0)
            && (WEB3DateUtility.compare(l_datBizDate, l_feqTradedProductRow.getUnlistedDate()) < 0))
        {
            log.exiting(STR_METHOD_NAME);
            return true;
        }
        else
        {
            log.exiting(STR_METHOD_NAME);
            return false;
        }
    }

    public static boolean isBuyStop(FeqTradedProductRow l_feqTradedProductRow)
    {
        final String STR_METHOD_NAME = "isBuyStop() ";
        log.entering(STR_METHOD_NAME);
         // 　@－this.外株取引銘柄Params.売買停止 == 1:停止中(取引所規制)，
         // 　@　@または 2:停止中（当社規制）の場合、trueを返却する。
         //

        if (WEB3TradeStopDef.STOP_MARKET_DEREG.equals(Integer.toString(l_feqTradedProductRow.getTradeStop()))
            || WEB3TradeStopDef.STOP_COMPANY_DEREG.equals(Integer.toString(l_feqTradedProductRow.getTradeStop())))
        {
            log.exiting(STR_METHOD_NAME);
            return true;
        }

         // 　@－this.外株取引銘柄Params.買付停止 == 1:停止中(取引所規制)，
         // 　@　@または 2:停止中（当社規制）の場合、trueを返却する。
         //
        else if (WEB3TradeStopDef.STOP_COMPANY_DEREG.equals(Integer.toString(l_feqTradedProductRow.getBuyStop()))
            || WEB3TradeStopDef.STOP_MARKET_DEREG.equals(Integer.toString(l_feqTradedProductRow.getBuyStop())))
        {
            log.exiting(STR_METHOD_NAME);
            return true;
        }
        else
        {
           // 　@－以外、falseを返却する。
           log.exiting(STR_METHOD_NAME);
           return false;
        }
    }

    public static boolean isSellStop(FeqTradedProductRow l_feqTradedProductRow)
    {
        final String STR_METHOD_NAME = "isSellStop() ";
        log.entering(STR_METHOD_NAME);
        // 　@－this.外株取引銘柄Params.売買停止 == 1:停止中(取引所規制)，
        // 　@　@または 2:停止中（当社規制）の場合、trueを返却する。
        //
        if (WEB3TradeStopDef.STOP_MARKET_DEREG.equals(Integer.toString(l_feqTradedProductRow.getTradeStop()))
            || WEB3TradeStopDef.STOP_COMPANY_DEREG.equals(Integer.toString(l_feqTradedProductRow.getTradeStop())))
        {
            log.exiting(STR_METHOD_NAME);
            return true;
        }
        // 　@－this.外株取引銘柄Params.売付停止 == 1:停止中(取引所規制)，
        // 　@　@または 2:停止中（当社規制）の場合、trueを返却する。
        //
        else if (WEB3TradeStopDef.STOP_COMPANY_DEREG.equals(Integer.toString(l_feqTradedProductRow.getSellStop()))
            || WEB3TradeStopDef.STOP_MARKET_DEREG.equals(Integer.toString(l_feqTradedProductRow.getSellStop())))
        {
            log.exiting(STR_METHOD_NAME);
            return true;
        }
        else
        {
           // 　@以外、falseを返却する。
           log.exiting(STR_METHOD_NAME);
           return false;
        }

    }
    /**
     * (is取引規制) <BR>
     * （isTradingSuspended） <BR>
     *  <BR>
     * 取引規制中かを判定する。trueの場合、取引規制中， <BR>
     * 　@　@　@　@　@　@　@　@　@　@　@　@　@　@　@　@falseの場合取引可能。 <BR>
     *  <BR>
     * 買付の場合（is買付 == true）、is買停止()の戻り値を返却する。 <BR>
     * 売付の場合（is買付 == false）、is売停止()の戻り値を返却する。 <BR>
     * @@param l_blnIsBuy - (is買付) <BR>
     * 買付かの判定 <BR>
     *  <BR>
     * 買付：　@true <BR>
     * 売付：　@false <BR>
     *
     * @@return boolean
     * @@roseuid 42B11DCC0191
     */
    public boolean isTradingSuspended(boolean l_blnIsBuy)
    {
        final String STR_METHOD_NAME = "isTradingSuspended(boolean l_blnIsBuy)";
        log.entering(STR_METHOD_NAME);
        if (l_blnIsBuy)
        {
            log.exiting(STR_METHOD_NAME);
            return this.isBuyStop();
        }
        else
        {
            log.exiting(STR_METHOD_NAME);
            return this.isSellStop();
        }
    }

    /**
     * (is買停止) <BR>
     * 買付停止かを判定する。trueの場合、買停止中，falseの場合取引可能。 <BR>
     *  <BR>
     * 　@－this.外株取引銘柄Params.売買停止 == 1:停止中(取引所規制)， <BR>
     * 　@　@または 2:停止中（当社規制）の場合、trueを返却する。 <BR>
     *  <BR>
     * 　@－this.外株取引銘柄Params.買付停止 == 1:停止中(取引所規制)， <BR>
     * 　@　@または 2:停止中（当社規制）の場合、trueを返却する。 <BR>
     *  <BR>
     * 　@－以外、falseを返却する。 <BR>
     * @@return boolean
     * @@roseuid 42B11F520087
     */
    public boolean isBuyStop()
    {
        final String STR_METHOD_NAME = "isBuyStop() ";
        log.entering(STR_METHOD_NAME);
         // 　@－this.外株取引銘柄Params.売買停止 == 1:停止中(取引所規制)，
         // 　@　@または 2:停止中（当社規制）の場合、trueを返却する。
         //

        if (WEB3TradeStopDef.STOP_MARKET_DEREG.equals(Integer.toString(l_feqTradedProductRow.getTradeStop()))
            || WEB3TradeStopDef.STOP_COMPANY_DEREG.equals(Integer.toString(l_feqTradedProductRow.getTradeStop())))
        {
            log.exiting(STR_METHOD_NAME);
            return true;
        }

         // 　@－this.外株取引銘柄Params.買付停止 == 1:停止中(取引所規制)，
         // 　@　@または 2:停止中（当社規制）の場合、trueを返却する。
         //
        else if (WEB3TradeStopDef.STOP_COMPANY_DEREG.equals(Integer.toString(l_feqTradedProductRow.getBuyStop()))
            || WEB3TradeStopDef.STOP_MARKET_DEREG.equals(Integer.toString(l_feqTradedProductRow.getBuyStop())))
        {
            log.exiting(STR_METHOD_NAME);
            return true;
        }
        else
        {
           // 　@－以外、falseを返却する。
           log.exiting(STR_METHOD_NAME);
           return false;
        }
    }

    /**
     * (is売停止) <BR>
     * 売付停止かを判定する。trueの場合、売停止中，falseの場合取引可能。 <BR>
     *  <BR>
     * 　@－this.外株取引銘柄Params.売買停止 == 1:停止中(取引所規制)， <BR>
     * 　@　@または 2:停止中（当社規制）の場合、trueを返却する。  <BR>
     *  <BR>
     * 　@－this.外株取引銘柄Params.売付停止 == 1:停止中(取引所規制)， <BR>
     * 　@　@または 2:停止中（当社規制）の場合、trueを返却する。 <BR>
     *  <BR>
     * 　@－以外、falseを返却する。 <BR>
     * @@return boolean
     * @@roseuid 42B11EF90133
     */
    public boolean isSellStop()
    {
        final String STR_METHOD_NAME = "isSellStop() ";
        log.entering(STR_METHOD_NAME);
        // 　@－this.外株取引銘柄Params.売買停止 == 1:停止中(取引所規制)，
        // 　@　@または 2:停止中（当社規制）の場合、trueを返却する。
        //
        if (WEB3TradeStopDef.STOP_MARKET_DEREG.equals(Integer.toString(l_feqTradedProductRow.getTradeStop()))
            || WEB3TradeStopDef.STOP_COMPANY_DEREG.equals(Integer.toString(l_feqTradedProductRow.getTradeStop())))
        {
            log.exiting(STR_METHOD_NAME);
            return true;
        }
        // 　@－this.外株取引銘柄Params.売付停止 == 1:停止中(取引所規制)，
        // 　@　@または 2:停止中（当社規制）の場合、trueを返却する。
        //
        else if (WEB3TradeStopDef.STOP_COMPANY_DEREG.equals(Integer.toString(l_feqTradedProductRow.getSellStop()))
            || WEB3TradeStopDef.STOP_MARKET_DEREG.equals(Integer.toString(l_feqTradedProductRow.getSellStop())))
        {
            log.exiting(STR_METHOD_NAME);
            return true;
        }
        else
        {
           // 　@以外、falseを返却する。
           log.exiting(STR_METHOD_NAME);
           return false;
        }

    }

    /**
     * (get終値) <BR>
     * 終値を取得する。 <BR>
     *  <BR>
     * this.外株取引銘柄Params.基準値（終値）を返却する。 <BR>
     * @@return double
     * @@roseuid 4280755B017F
     */
    public double getLastClosingPrice()
    {
        return l_feqTradedProductRow.getLastClosingPrice();
    }

    /**
     * (get買付単位) <BR>
     * （getBuyOrderLotSize） <BR>
     * this.外株取引銘柄Params.買付単位を返却する。 <BR>
     * @@return double
     * @@roseuid 42808EDD0076
     */
    public double getBuyOrderLotSize()
    {
        return l_feqTradedProductRow.getBuyLotSize();
    }

    /**
     * (get売付単位) <BR>
     * （getSellOrderLotSize） <BR>
     * this.外株取引銘柄Params.売付単位を返却する。 <BR>
     * @@return double
     * @@roseuid 42808F2D0289
     */
    public double getSellOrderLotSize()
    {
        return l_feqTradedProductRow.getSellLotSize();
    }

    /**
     * (get受渡日) <BR>
     * （getDailyDeliveryDateのオーバーライド） <BR>
     *  <BR>
     * （本取引銘柄の有効日==約定日）である注文の受渡日（国内）を返却する。 <BR>
     *  <BR>
     * １）　@権利落日取得 <BR>
     * 　@this.getProduct()にて、外国株式銘柄を取得する。 <BR>
     * 　@外国株式銘柄.get権利落日()にて権利落日を取得する。 <BR>
     *  <BR>
     * ２）　@国内カレンダで３営業日後取得 <BR>
     * 　@２－１）　@取引時間管理.get発注日()にて発注日を取得する。 <BR>
     *  <BR>
     * 　@２－２） <BR>
     * 　@　@営業日計算.calc営業日()にて、発注日の３営業日後を取得する。 <BR>
     * 　@　@発注日の３営業日後が権利落日の場合（権利落日 == 発注日の３営業日後）、<BR>
     * 　@　@発注日の４営業日後を受渡日とする。 <BR>
     * 　@　@以外、発注日の３営業日後を受渡日とする。 <BR>
     * 　@ <BR>
     * ３）　@受渡日返却 <BR>
     * 　@受渡日を返却する。 <BR>
     * @@return Date
     * @@roseuid 4282B7140245
     */
    public Date getDailyDeliveryDate()
    {
        final String STR_METHOD_NAME = "getDailyDeliveryDate()";
        log.entering(STR_METHOD_NAME);
        // １）　@権利落日取得
        // 　@this.getProduct()にて、外国株式銘柄を取得する。
        // 　@外国株式銘柄.get権利落日()にて権利落日を取得する。
        //
        WEB3FeqProduct l_product = (WEB3FeqProduct)this.getProduct();
        Date l_datExRightDate = l_product.getExRightDate();
        String l_strInstitutionCode = l_product.getInstitution().getInstitutionCode();
        String l_strMarketCode = l_product.getMarketCode();
        // ２）　@国内カレンダで３営業日後取得
        // 　@２－１）　@取引時間管理.get発注日()にて発注日を取得する。
        //
        Date l_datBizDate = null;
        Timestamp l_timeThreeBizDate = null;
        WEB3GentradeBizDate l_genBizDate = null;
        try
        {
            l_datBizDate = WEB3GentradeTradingTimeManagement.getOrderBizDate();
            // 　@２－２）
            // 　@　@営業日計算.calc営業日()にて、発注日の３営業日後を取得する。
            // 　@　@発注日の３営業日後が権利落日の場合（権利落日 == 発注日の３営業日後）、
            // 　@　@発注日の４営業日後を受渡日とする。
            // 　@　@以外、発注日の３営業日後を受渡日とする。
            //

            l_genBizDate = new WEB3GentradeBizDate(new Timestamp(l_datBizDate.getTime()));
            l_timeThreeBizDate = l_genBizDate.roll(3);
            if (WEB3DateUtility.compareToDay(l_datExRightDate, l_timeThreeBizDate)== 0)
            {
                // ３）　@受渡日返却
                // 　@受渡日を返却する。
                log.exiting(STR_METHOD_NAME);
                return l_genBizDate.roll(4);
            }
            else
            {
                // ３）　@受渡日返却
                // 　@受渡日を返却する。
                log.exiting(STR_METHOD_NAME);
                return l_timeThreeBizDate;
            }
        }
        catch(WEB3SystemLayerException l_ex)
        {
            log.error(l_ex.getMessage(), l_ex);
            throw new WEB3BaseRuntimeException(
                      l_ex.getErrorInfo(),
                      this.getClass().getName() + "." + STR_METHOD_NAME,
                      l_ex.getMessage(), l_ex);
        }
    }
}
@
