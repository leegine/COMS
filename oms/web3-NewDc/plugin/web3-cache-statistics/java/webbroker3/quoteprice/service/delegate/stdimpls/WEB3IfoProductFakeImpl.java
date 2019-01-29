head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.14.03.25.15;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	4584d7d7e626866;
filename	WEB3IfoProductFakeImpl.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/*Copyright        : (株)大和総研 証券ソリューションシステム第二部
 File Name        : 先物OP銘柄時価取得用実装(WEB3IfoProductFakeImpl.java)
 Author Name      : Daiwa Institute of Research
 Revesion History : 2007/05/24 孫(FLJ) 新規作成
 */
package webbroker3.quoteprice.service.delegate.stdimpls;

import java.util.*;

//import com.fitechlabs.xtrade.plugin.tc.eqtype.*;
//import com.fitechlabs.xtrade.plugin.tc.eqtype.data.*;
import com.fitechlabs.xtrade.plugin.tc.xbifo.*;
import com.fitechlabs.xtrade.plugin.tc.xbifo.data.*;
import com.fitechlabs.xtrade.plugin.tc.gentrade.*;
import com.fitechlabs.xtrade.plugin.tc.gentrade.data.*;

/**
 * （先物OP銘柄時価取得用実装）。
 * @@version 1.0
 */
public class WEB3IfoProductFakeImpl
    implements IfoProduct
{
    /**
     * 銘柄Row
     */
    private ProductRow m_prow;

    /**
     * 先物OP銘柄Row
     */
    private IfoProductRow m_ifoprow;

    /**
     * @@roseuid 40AC7C5103A5
     * @@param l_prow ProductRow
     * @@param l_eqprow IfoProductRow
     */
    public WEB3IfoProductFakeImpl(ProductRow l_prow, IfoProductRow l_ifoprow)
    {
        this.m_prow = l_prow;
        this.m_ifoprow = l_ifoprow;
    }

    /**
     * getDataSourceObject
     *
     * @@return Object
     */
    public Object getDataSourceObject()
    {
        return m_ifoprow;
    }

    /**
     * getProductId
     *
     * @@return long
     */
    public long getProductId()
    {
        return m_ifoprow.getProductId();
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
        return m_ifoprow.getProductType();
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
        return m_ifoprow.getProductCode();
    }

    /* (非 Javadoc)
	 * @@see com.fitechlabs.xtrade.plugin.tc.xbifo.IfoProduct#getUnderlyingProductCode()
	 */
	public String getUnderlyingProductCode() {
		return m_ifoprow.getUnderlyingProductCode();
	}

	/* (非 Javadoc)
	 * @@see com.fitechlabs.xtrade.plugin.tc.xbifo.IfoProduct#getDerivativeType()
	 */
	public IfoDerivativeTypeEnum getDerivativeType() {
		return m_ifoprow.getDerivativeType();
	}

	/* (非 Javadoc)
	 * @@see com.fitechlabs.xtrade.plugin.tc.xbifo.IfoProduct#getStrikePrice()
	 */
	public double getStrikePrice() {
		return m_ifoprow.getStrikePrice();
	}

	/* (非 Javadoc)
	 * @@see com.fitechlabs.xtrade.plugin.tc.xbifo.IfoProduct#getMonthOfDelivery()
	 */
	public String getMonthOfDelivery() {
		return m_ifoprow.getMonthOfDelivery();
	}

	/* (非 Javadoc)
	 * @@see com.fitechlabs.xtrade.plugin.tc.xbifo.IfoProduct#getExerciseDate()
	 */
	public Date getExerciseDate() {
		return null;
	}

}
@
