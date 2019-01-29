head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.14.09.41.52;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	7a84d7de2372e68;
filename	WEB3IfoTradedProductFakeImpl.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/*Copyright       : (��)��a���� �،��\�����[�V�����V�X�e�����
 File Name        : �敨OP������������擾�p����(WEB3IfoTradedProductFakeImpl.java)
 Author Name      : Daiwa Institute of Research
 Revesion History : 2009/01/28 ���@@�@@��(FLJ) �V�K�쐬
 */
package webbroker3.quoteadaptor.stdimpls;

import java.util.Date;

import com.fitechlabs.xtrade.plugin.tc.gentrade.Institution;
import com.fitechlabs.xtrade.plugin.tc.gentrade.Market;
import com.fitechlabs.xtrade.plugin.tc.gentrade.Product;
import com.fitechlabs.xtrade.plugin.tc.xbifo.IfoTradedProduct;
import com.fitechlabs.xtrade.plugin.tc.xbifo.data.IfoProductRow;

/**
 * �i�敨OP������������擾�p�����j�B
 * @@version 1.0
 */
public class WEB3IfoTradedProductFakeImpl
    implements IfoTradedProduct
{
    /**
     * ����Row
     */
    private Product m_product;

    /**
     * �s��Row
     */
    private Market m_market;

    /**
     * WEB3IfoTradedProductFakeImpl
     *
     * @@param l_p Product
     * @@param l_m Market
     */
    public WEB3IfoTradedProductFakeImpl(Product l_p, Market l_m)
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
     * getTradedProductId
     *
     * @@return long
     */
    public long getTradedProductId()
    {
        return 0L;
    }
    
    /**
     * getIfoProductRow
     *
     * @@return IfoProductRow
     */
    public IfoProductRow getIfoProductRow()
    {
        return ((WEB3IfoProductFakeImpl)m_product).getIfoProductRow();
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
    
    ///////////////////////////////////////////////////////////////////////////////
    
    /**
     * getLastClosingPrice
     *
     * @@return double
     */
    public double getLastClosingPrice()
    {
        return 0.0;
    }
    
	/* (�� Javadoc)
	 * @@see com.fitechlabs.xtrade.plugin.tc.xbifo.IfoTradedProduct#getUnitSize()
	 */
	public long getUnitSize() {
		// TODO �����������ꂽ���\�b�h�E�X�^�u
		return 0;
	}

	/* (�� Javadoc)
	 * @@see com.fitechlabs.xtrade.plugin.tc.xbifo.IfoTradedProduct#getPerOrderMaxUnits()
	 */
	public long getPerOrderMaxUnits() {
		// TODO �����������ꂽ���\�b�h�E�X�^�u
		return 0;
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
    
	/* (�� Javadoc)
	 * @@see com.fitechlabs.xtrade.plugin.tc.xbifo.IfoTradedProduct#getStartTradingDate()
	 */
	public Date getStartTradingDate() {
		// TODO �����������ꂽ���\�b�h�E�X�^�u
		return null;
	}
    
	/* (�� Javadoc)
	 * @@see com.fitechlabs.xtrade.plugin.tc.xbifo.IfoTradedProduct#getLastTradingDate()
	 */
	public Date getLastTradingDate() {
		// TODO �����������ꂽ���\�b�h�E�X�^�u
		return null;
	}

	/* (�� Javadoc)
	 * @@see com.fitechlabs.xtrade.plugin.tc.xbifo.IfoTradedProduct#isTradingStopped()
	 */
	public boolean isTradingStopped() {
		// TODO �����������ꂽ���\�b�h�E�X�^�u
		return false;
	}

	/* (�� Javadoc)
	 * @@see com.fitechlabs.xtrade.plugin.tc.xbifo.IfoTradedProduct#isBuyToOpenOrdersStopped()
	 */
	public boolean isBuyToOpenOrdersStopped() {
		// TODO �����������ꂽ���\�b�h�E�X�^�u
		return false;
	}

	/* (�� Javadoc)
	 * @@see com.fitechlabs.xtrade.plugin.tc.xbifo.IfoTradedProduct#isSellToOpenOrdersStopped()
	 */
	public boolean isSellToOpenOrdersStopped() {
		// TODO �����������ꂽ���\�b�h�E�X�^�u
		return false;
	}

	/* (�� Javadoc)
	 * @@see com.fitechlabs.xtrade.plugin.tc.xbifo.IfoTradedProduct#isSellToCloseOrdersStopped()
	 */
	public boolean isSellToCloseOrdersStopped() {
		// TODO �����������ꂽ���\�b�h�E�X�^�u
		return false;
	}

	/* (�� Javadoc)
	 * @@see com.fitechlabs.xtrade.plugin.tc.xbifo.IfoTradedProduct#isBuyToCloseOrdersStopped()
	 */
	public boolean isBuyToCloseOrdersStopped() {
		// TODO �����������ꂽ���\�b�h�E�X�^�u
		return false;
	}

	/* (�� Javadoc)
	 * @@see com.fitechlabs.xtrade.plugin.tc.xbifo.IfoTradedProduct#getLimitPriceRange()
	 */
	public double getLimitPriceRange() {
		// TODO �����������ꂽ���\�b�h�E�X�^�u
		return 0;
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

}
@
