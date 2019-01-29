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
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : 先物・オプション用時価情報クラス(WEB3IfoQuoteDataImpl.java)
Author Name      : Daiwa Institute of Research
Revision History : 2004/05/18 山田　@卓司(FLJ) 新規作成
                   2009/04/20 許　@　@　@　@競(FLJ) 時価システム切替対応
*/
package webbroker3.quoteadaptor.stdimpls;

import java.sql.Timestamp;

import com.fitechlabs.xtrade.plugin.tc.gentrade.GtlUtils;
import com.fitechlabs.xtrade.plugin.tc.xbifo.IfoProduct;
import com.fitechlabs.xtrade.plugin.tc.xbifo.IfoTradedProduct;

import webbroker3.quoteadaptor.*;

/**
 * 先物・オプション用時価情報クラス<br>
 * 
 * @@author Takuji Yamada
 * @@version 1.0
 */
class WEB3IfoQuoteDataImpl
    extends AbstractWEB3QuoteData
    implements WEB3IfoQuoteData
{

    /**
     * コンストラクタ
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
