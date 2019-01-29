head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.04.07.02.38.17;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	5444d9d078c63f4;
filename	WEB3ProtoQuoteDataSupplierServiceForMock.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : WEB3ProtoQuoteDataSupplierServiceForMock.java
Author Name      : Daiwa Institute of Research
Revision History : 2007/06/15 金傑 (中訊) 新規作成
*/
package webbroker3.quoteadaptor.prototype;


import com.fitechlabs.xtrade.plugin.tc.gentrade.NotFoundException;
import com.fitechlabs.xtrade.plugin.tc.gentrade.QuoteData;
import com.fitechlabs.xtrade.plugin.tc.gentrade.TradedProduct;
import com.fitechlabs.xtrade.plugin.tc.xbifo.IfoTradedProduct;

import webbroker3.mock.TestBaseForMock;
import webbroker3.quoteadaptor.RealType;
import webbroker3.quoteadaptor.WEB3IfoQuoteData;
import webbroker3.util.WEB3LogUtility;

public class WEB3ProtoQuoteDataSupplierServiceForMock extends WEB3ProtoQuoteDataSupplierService
{
    /**
     * ログユーティリティ<BR>
     */
    private static WEB3LogUtility log =
        WEB3LogUtility.getInstance(WEB3ProtoQuoteDataSupplierServiceForMock.class);
    
    public WEB3IfoQuoteData getIfoQuote(IfoTradedProduct tradedProduct, RealType realType)
    throws NotFoundException
    {
        TestBaseForMock.MOCK_MANAGER.setMethodParamsValue(
                "webbroker3.quoteadaptor.prototype.WEB3ProtoQuoteDataSupplierService", 
                "getIfoQuote",
                new Class[]
                {IfoTradedProduct.class,RealType.class}, 
                    new Object[]
                {tradedProduct,realType});
        if(TestBaseForMock.MOCK_MANAGER.isMockUsed(
                "webbroker3.quoteadaptor.prototype.WEB3ProtoQuoteDataSupplierService", 
                "getIfoQuote",
                new Class[]
                {IfoTradedProduct.class,RealType.class}))
        {
            log.debug("webbroker3.quoteadaptor.prototype.WEB3ProtoQuoteDataSupplierServiceForMock.getIfoQuote");
            
            return(WEB3IfoQuoteData)TestBaseForMock.MOCK_MANAGER.getMethodReturnValue(
                    "webbroker3.quoteadaptor.prototype.WEB3ProtoQuoteDataSupplierService", 
                    "getIfoQuote",
                    new Class[]
                    {IfoTradedProduct.class,RealType.class}).asObject();
        }
        return super.getIfoQuote(tradedProduct, realType);
    }
    
    public QuoteData getQuote(
            TradedProduct l_tradedProductt,
            RealType l_realType
            )throws NotFoundException 
            {
        TestBaseForMock.MOCK_MANAGER.setMethodParamsValue(
                "webbroker3.quoteadaptor.prototype.WEB3ProtoQuoteDataSupplierService", "getQuote", new Class[]
                { TradedProduct.class, RealType.class }, new Object[]
                { l_tradedProductt, l_realType });
        if (TestBaseForMock.MOCK_MANAGER.isMockUsed(
                "webbroker3.quoteadaptor.prototype.WEB3ProtoQuoteDataSupplierService", "getQuote", new Class[]
                { TradedProduct.class, RealType.class }))
        {
            log.debug("webbroker3.quoteadaptor.prototype.WEB3ProtoQuoteDataSupplierServiceForMock.getQuote()");

            return (QuoteData) TestBaseForMock.MOCK_MANAGER.getMethodReturnValue(
                    "webbroker3.quoteadaptor.prototype.WEB3ProtoQuoteDataSupplierService", "getQuote", new Class[]
                    { TradedProduct.class, RealType.class }).asObject();
        }
        return super.getQuote(l_tradedProductt, l_realType);
    }
}
@
