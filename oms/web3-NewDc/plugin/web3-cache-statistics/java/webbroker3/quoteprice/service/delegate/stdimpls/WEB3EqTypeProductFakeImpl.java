head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.14.03.24.54;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	4584d7d7e626866;
filename	WEB3EqTypeProductFakeImpl.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/*Copyright        : (株)大和総研 証券ソリューションシステム第二部
 File Name        : 時価取得要銘柄実装(WEB3EqTypeProductFakeImpl.java)
 Author Name      : Daiwa Institute of Research
 Revesion History : 2006/11/10 劉(FLJ) 新規作成
 */
package webbroker3.quoteprice.service.delegate.stdimpls;

import java.util.*;

import com.fitechlabs.xtrade.plugin.tc.eqtype.*;
import com.fitechlabs.xtrade.plugin.tc.eqtype.data.*;
import com.fitechlabs.xtrade.plugin.tc.gentrade.*;
import com.fitechlabs.xtrade.plugin.tc.gentrade.data.*;

/**
 * （時価取得要銘柄実装）。
 * @@version 1.0
 */
public class WEB3EqTypeProductFakeImpl
    implements EqTypeProduct
{
    /**
     * 銘柄Row
     */
    private ProductRow m_prow;

    /**
     * 株式銘柄Row
     */
    private EqtypeProductRow m_eqprow;

    /**
     * @@roseuid 40AC7C5103A5
     * @@param l_prow ProductRow
     * @@param l_eqprow EqtypeProductRow
     */
    public WEB3EqTypeProductFakeImpl(ProductRow l_prow, EqtypeProductRow l_eqprow)
    {
        this.m_prow = l_prow;
        this.m_eqprow = l_eqprow;
    }

    /**
     * getDataSourceObject
     *
     * @@return Object
     */
    public Object getDataSourceObject()
    {
        return m_eqprow;
    }

    /**
     * getProductId
     *
     * @@return long
     */
    public long getProductId()
    {
        return m_eqprow.getProductId();
    }

    /**
     * getTradedMarkets
     *
     * @@return Market[]
     */
    public Market[] getTradedMarkets()
    {
        return null;
    }

    /**
     * getPrimaryMarket
     *
     * @@return Market
     */
    public Market getPrimaryMarket()
    {
        return null;
    }

    /**
     * getPrimaryTradedProduct
     *
     * @@return TradedProduct
     */
    public TradedProduct getPrimaryTradedProduct()
    {
        return null;
    }

    /**
     * getProductType
     *
     * @@return ProductTypeEnum
     */
    public ProductTypeEnum getProductType()
    {
        return m_eqprow.getProductType();
    }

    /**
     * isTradedOnMarket
     *
     * @@param market Market
     * @@return boolean
     */
    public boolean isTradedOnMarket(Market market)
    {
        return false;
    }

    /**
     * getLotSize
     *
     * @@return double
     */
    public double getLotSize()
    {
        return 0.0;
    }

    /**
     * getStandardName
     *
     * @@return String
     */
    public String getStandardName()
    {
        return "";
    }

    /**
     * getNameAlt1
     *
     * @@return String
     */
    public String getNameAlt1()
    {
        return "";
    }

    /**
     * getNameAlt2
     *
     * @@return String
     */
    public String getNameAlt2()
    {
        return "";
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
     * getInstitution
     *
     * @@return Institution
     */
    public Institution getInstitution()
    {
        return null;
    }

    /**
     * getProductCode
     *
     * @@return String
     */
    public String getProductCode()
    {
        return m_eqprow.getProductCode();
    }

    /**
     * getYearlyBooksClosingDate
     *
     * @@return Date
     */
    public Date getYearlyBooksClosingDate()
    {
        return null;
    }

    /**
     * getMiniStockLotSize
     *
     * @@return double
     */
    public double getMiniStockLotSize()
    {
        return 0.0;
    }

}
@
