head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.14.03.25.02;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	4584d7d7e626866;
filename	WEB3EqTypeTradedProductFakeImpl.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/*Copyright        : (株)大和総研 証券ソリューションシステム第二部
 File Name        : 取引銘柄(WEB3EqTypeTradedProductFakeImpl.java)
 Author Name      : Daiwa Institute of Research
 Revesion History : 2006/11/10 劉(FLJ) 新規作成
 */
package webbroker3.quoteprice.service.delegate.stdimpls;

import java.util.*;

import com.fitechlabs.xtrade.plugin.tc.eqtype.*;
import com.fitechlabs.xtrade.plugin.tc.gentrade.*;

/**
 * （時価取得要取引銘柄実装）。
 * @@version 1.0
 */
public class WEB3EqTypeTradedProductFakeImpl
    implements EqTypeTradedProduct
{
    /**
     * 銘柄Row
     */
    private Product m_product;

    /**
     * 市場Row
     */
    private Market m_market;

    /**
     * WEB3EqTypeTradedProductFakeImpl
     *
     * @@param l_p Product
     * @@param l_m Market
     */
    public WEB3EqTypeTradedProductFakeImpl(Product l_p, Market l_m)
    {
        this.m_product = l_p;
        this.m_market = l_m;
    }

    /**
     * getDataSourceObject
     *
     * @@return Object
     */
    public Object getDataSourceObject()
    {
        return "";
    }

    /**
     * getLastClosingPrice
     *
     * @@return double
     */
    public double getLastClosingPrice()
    {
        return 0.0;
    }

    /**
     * getDailyStopHigh
     *
     * @@return double
     */
    public double getDailyStopHigh()
    {
        return 0.0;
    }

    /**
     * getDailyStopLow
     *
     * @@return double
     */
    public double getDailyStopLow()
    {
        return 0.0;
    }

    /**
     * getStopHighPrice
     *
     * @@return double
     */
    public double getStopHighPrice()
    {
        return 0.0;
    }

    /**
     * getStopLowPrice
     *
     * @@return double
     */
    public double getStopLowPrice()
    {
        return 0.0;
    }

    /**
     * isMarginable
     *
     * @@return boolean
     */
    public boolean isMarginable()
    {
        return false;
    }

    /**
     * isShortable
     *
     * @@return boolean
     */
    public boolean isShortable()
    {
        return false;
    }

    /**
     * getLastUpdatedTimestamp
     *
     * @@return Date
     */
    public Date getLastUpdatedTimestamp()
    {
        return null;
    }

    /**
     * getTickValueUnit
     *
     * @@return double
     */
    public double getTickValueUnit()
    {
        return 0.0;
    }

    /**
     * getTickValueUnit
     *
     * @@param double0 double
     * @@return double
     */
    public double getTickValueUnit(double double0)
    {
        return 0.0;
    }

    /**
     * isValidPriceAsPerTickValue
     *
     * @@param double0 double
     * @@return boolean
     */
    public boolean isValidPriceAsPerTickValue(double double0)
    {
        return false;
    }

    /**
     * getListedDate
     *
     * @@return Date
     */
    public Date getListedDate()
    {
        return null;
    }

    /**
     * getUnlistedDate
     *
     * @@return Date
     */
    public Date getUnlistedDate()
    {
        return null;
    }

    /**
     * isListedCurrently
     *
     * @@return boolean
     */
    public boolean isListedCurrently()
    {
        return false;
    }

    /**
     * isMiniStockTradable
     *
     * @@return boolean
     */
    public boolean isMiniStockTradable()
    {
        return false;
    }

    /**
     * getTradedProductId
     *
     * @@return long
     */
    public long getTradedProductId()
    {
        return 0L;
    }

    /**
     * getProduct
     *
     * @@return Product
     */
    public Product getProduct()
    {
        return m_product;
    }

    /**
     * getMarket
     *
     * @@return Market
     */
    public Market getMarket()
    {
        return m_market;
    }

    /**
     * isTradingSuspended
     *
     * @@return boolean
     */
    public boolean isTradingSuspended()
    {
        return false;
    }

    /**
     * getMarginRatio
     *
     * @@return double
     */
    public double getMarginRatio()
    {
        return 0.0;
    }

    /**
     * getBaseDate
     *
     * @@return Date
     */
    public Date getBaseDate()
    {
        return null;
    }

    /**
     * getDailyDeliveryDate
     *
     * @@return Date
     */
    public Date getDailyDeliveryDate()
    {
        return null;
    }

    /**
     * isCollateralizable
     *
     * @@return boolean
     */
    public boolean isCollateralizable()
    {
        return false;
    }

    /**
     * getInstitution
     *
     * @@return Institution
     */
    public Institution getInstitution()
    {
        return null;
    }

}
@
