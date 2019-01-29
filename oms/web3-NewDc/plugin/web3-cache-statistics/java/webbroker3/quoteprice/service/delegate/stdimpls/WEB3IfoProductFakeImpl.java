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
@/*Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
 File Name        : �敨OP���������擾�p����(WEB3IfoProductFakeImpl.java)
 Author Name      : Daiwa Institute of Research
 Revesion History : 2007/05/24 ��(FLJ) �V�K�쐬
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
 * �i�敨OP���������擾�p�����j�B
 * @@version 1.0
 */
public class WEB3IfoProductFakeImpl
    implements IfoProduct
{
    /**
     * ����Row
     */
    private ProductRow m_prow;

    /**
     * �敨OP����Row
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

    /* (�� Javadoc)
	 * @@see com.fitechlabs.xtrade.plugin.tc.xbifo.IfoProduct#getUnderlyingProductCode()
	 */
	public String getUnderlyingProductCode() {
		return m_ifoprow.getUnderlyingProductCode();
	}

	/* (�� Javadoc)
	 * @@see com.fitechlabs.xtrade.plugin.tc.xbifo.IfoProduct#getDerivativeType()
	 */
	public IfoDerivativeTypeEnum getDerivativeType() {
		return m_ifoprow.getDerivativeType();
	}

	/* (�� Javadoc)
	 * @@see com.fitechlabs.xtrade.plugin.tc.xbifo.IfoProduct#getStrikePrice()
	 */
	public double getStrikePrice() {
		return m_ifoprow.getStrikePrice();
	}

	/* (�� Javadoc)
	 * @@see com.fitechlabs.xtrade.plugin.tc.xbifo.IfoProduct#getMonthOfDelivery()
	 */
	public String getMonthOfDelivery() {
		return m_ifoprow.getMonthOfDelivery();
	}

	/* (�� Javadoc)
	 * @@see com.fitechlabs.xtrade.plugin.tc.xbifo.IfoProduct#getExerciseDate()
	 */
	public Date getExerciseDate() {
		return null;
	}

}
@
