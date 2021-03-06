head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.14.02.57.16;	author che-jin;	state Exp;
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
@/*
 Copyright        : ()åa¤ Ø\[VVXeæñ
 File Name        : ¿æ¾vÁ¿À(WEB3EqTypeProductFakeImpl.java)
 Author Name      : Daiwa Institute of Research
 Revesion History : 2009/02/03  (FLJ)VKì¬
 */
package webbroker3.quotecheck.service.delegate.stdimpls;

import java.util.*;

import com.fitechlabs.xtrade.plugin.tc.eqtype.*;
import com.fitechlabs.xtrade.plugin.tc.eqtype.data.*;
import com.fitechlabs.xtrade.plugin.tc.gentrade.*;
import com.fitechlabs.xtrade.plugin.tc.gentrade.data.*;

/**
 * i¿æ¾vÁ¿ÀjB
 * @@version 1.0
 */
public class WEB3EqTypeProductFakeImpl
    implements EqTypeProduct
{
    /**
     * Á¿Row
     */
    private ProductRow m_prow;

    /**
     * ®Á¿Row
     */
    private EqtypeProductRow m_eqprow;

    /**
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
