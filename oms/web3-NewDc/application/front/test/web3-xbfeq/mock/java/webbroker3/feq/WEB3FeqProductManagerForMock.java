head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.04.07.02.19.31;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	5444d9d078c63f4;
filename	WEB3FeqProductManagerForMock.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@package webbroker3.feq;

import com.fitechlabs.dbind.Row;
import com.fitechlabs.xtrade.kernel.data.DataException;
import com.fitechlabs.xtrade.plugin.tc.gentrade.Product;
import com.fitechlabs.xtrade.plugin.tc.gentrade.TradedProduct;
import com.fitechlabs.xtrade.plugin.tc.gentrade.data.ProductRow;
import com.fitechlabs.xtrade.plugin.tc.gentrade.data.TradedProductRow;
import com.fitechlabs.xtrade.plugin.tc.xbfeq.data.FeqProductRow;
import com.fitechlabs.xtrade.plugin.tc.xbfeq.data.FeqTradedProductRow;

import webbroker3.common.WEB3BaseRuntimeException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.gentrade.WEB3GentradeBranch;
import webbroker3.gentrade.WEB3GentradeSubAccount;
import webbroker3.mock.TestBaseForMock;
import webbroker3.util.WEB3LogUtility;

public class WEB3FeqProductManagerForMock extends WEB3FeqProductManager{

    /**
     * ログ出力ユーティリティ。<BR>
     */
    private static WEB3LogUtility log = WEB3LogUtility.getInstance(
        WEB3FeqProductManagerForMock.class);
    
    public WEB3FeqProductManagerForMock()
    {
        super();
    }
    
    public WEB3FeqProductQuote getIndicationCurrentPriceUnit(
    		WEB3FeqTradedProduct l_feqTradedProduct, WEB3GentradeSubAccount l_subAccount)
    {
        final String STR_METHOD_NAME = "getIndicationCurrentPriceUnit";
        log.entering(STR_METHOD_NAME);
        
        if (TestBaseForMock.MOCK_MANAGER.isMockUsed(
                "webbroker3.feq.WEB3FeqProductManager",
                "getIndicationCurrentPriceUnit",
                new Class[] {WEB3FeqTradedProduct.class, WEB3GentradeSubAccount.class}))
            {
                log.debug("webbroker3.gentrade.WEB3FeqProductManager.getIndicationCurrentPriceUnit" +
                		"(WEB3FeqTradedProduct, WEB3GentradeSubAccount)" + 
                    "l_feqTradedProduct = " + l_feqTradedProduct);
                
                return (WEB3FeqProductQuote)TestBaseForMock.MOCK_MANAGER.getMethodReturnValue(
                    "webbroker3.feq.WEB3FeqProductManager",
                    "getIndicationCurrentPriceUnit",
                    new Class[] {WEB3FeqTradedProduct.class, WEB3GentradeSubAccount.class}).asObject();
            }
	    
        return super.getIndicationCurrentPriceUnit(l_feqTradedProduct, l_subAccount);
    }
    
    public TradedProduct toTradedProduct(Row l_tprow)
    {
        try
        {
            if (l_tprow instanceof TradedProductRow)
            {
                return new WEB3FeqTradedProductForMock((TradedProductRow)l_tprow);
            }
            else
            {
                return new WEB3FeqTradedProductForMock((FeqTradedProductRow)l_tprow);                
            }
        }
        catch (DataException l_dfex)
        {
            String l_strMsg = "Exception when creating FeqTradedProduct for traded product id: " + ((TradedProductRow)l_tprow).getTradedProductId();
            log.error(l_strMsg, l_dfex);
            throw new WEB3BaseRuntimeException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80002,
                l_strMsg,
                l_dfex);            
        }

    }
    
    public Product toProduct(Row l_prow)
    {
        try
        {
            if (l_prow instanceof ProductRow)
            {
                return new WEB3FeqProductForMock((ProductRow)l_prow);                
            }
            else
            {
                return new WEB3FeqProductForMock((FeqProductRow)l_prow);
            }
        }
        catch (DataException l_dfex)
        {
            String l_strMsg = "Exception when creating WEB3FeqProduct for product id: " + ((ProductRow)l_prow).getProductId();
            log.error(l_strMsg, l_dfex);
            throw new WEB3BaseRuntimeException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80002,
                l_strMsg,
                l_dfex);            
        }

    }
}
@
