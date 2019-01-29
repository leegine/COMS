head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.14.09.40.41;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	7a84d7de2372e68;
filename	WEB3IfoQuoteDataImpl.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
File Name        : �敨�E�I�v�V�����p�������N���X(WEB3IfoQuoteDataImpl.java)
Author Name      : Daiwa Institute of Research
Revision History : 2004/05/18 �R�c�@@��i(FLJ) �V�K�쐬
                   2009/04/20 ���@@�@@�@@�@@��(FLJ) �����V�X�e���֑ؑΉ�
*/
package webbroker3.quoteadaptor.stdimpls;

import java.sql.Timestamp;

import com.fitechlabs.xtrade.plugin.tc.gentrade.GtlUtils;
import com.fitechlabs.xtrade.plugin.tc.xbifo.IfoProduct;
import com.fitechlabs.xtrade.plugin.tc.xbifo.IfoTradedProduct;

import webbroker3.quoteadaptor.*;

/**
 * �敨�E�I�v�V�����p�������N���X<br>
 * 
 * @@author Takuji Yamada
 * @@version 1.0
 */
class WEB3IfoQuoteDataImpl
    extends AbstractWEB3QuoteData
    implements WEB3IfoQuoteData
{

    /**
     * �R���X�g���N�^
     */
    WEB3IfoQuoteDataImpl(IfoTradedProduct tradedProduct, RealType realType)
    {
        super();
        IfoProduct product = (IfoProduct) tradedProduct.getProduct();
        super.quoteDate = WEB3QuoteUtil.getSystemDate();
        super.dataType = DataType.getDataType(tradedProduct);
        super.realType = realType;
        super.marketCode = tradedProduct.getMarket().getMarketCode();
        super.productCode = product.getUnderlyingProductCode();
        super.monthOfDelivery = product.getMonthOfDelivery();
        if (DataType.INDEX_OPTION.equals(super.dataType))
        {
            super.putAndCall =
                PutAndCall.getPutAndCall(product.getDerivativeType());
            super.strikePrice = product.getStrikePrice();
        }
    }

    /**
     * @@see com.fitechlabs.xtrade.plugin.tc.xbifo.IfoQuoteData#getLastClosingPrice()
     */
    public double getLastClosingPrice()
    {
        return getBasePrice();
    }

    /**
     * @@see com.fitechlabs.xtrade.plugin.tc.gentrade.QuoteData#getQuoteTimestamp()
     */
    public Timestamp getQuoteTimestamp()
    {
        return getCurrentPriceTime();
    }

}
@
