head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.04.07.01.55.14;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	5444d9d078c63f4;
filename	WEB3FeqMarketRequestSenderServiceTest.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@package webbroker3.feq;

import com.fitechlabs.xtrade.plugin.tc.gentrade.DefaultMarketRequestSendResult;
import com.fitechlabs.xtrade.plugin.tc.gentrade.data.MarketParams;
import com.fitechlabs.xtrade.plugin.tc.gentrade.data.ProductParams;
import com.fitechlabs.xtrade.plugin.tc.gentrade.market.messages.CancelOrderMarketRequestMessage;
import com.fitechlabs.xtrade.plugin.tc.gentrade.market.messages.DefaultCancelOrderMarketRequestMessage;
import com.fitechlabs.xtrade.plugin.tc.xbfeq.data.FeqOrderUnitParams;
import com.fitechlabs.xtrade.plugin.tc.xbfeq.data.FeqProductParams;
import com.fitechlabs.xtrade.plugin.tc.xbfeq.market.messages.DefaultFeqChangeOrderMarketRequestMessage;
import com.fitechlabs.xtrade.plugin.tc.xbfeq.market.messages.FeqChangeOrderMarketRequestMessage;

import test.util.TestDBUtility;

import webbroker3.common.WEB3BaseException;
import webbroker3.common.WEB3BusinessLayerException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.gentrade.WEB3GentradeTradingTimeManagementForMock;
import webbroker3.mock.TestBaseForMock;
import webbroker3.slebase.data.SleSendQRow;
import webbroker3.util.WEB3LogUtility;

public class WEB3FeqMarketRequestSenderServiceTest extends TestBaseForMock
{
    /**
     * (ログ出力ユーティリティ。)<BR>
     */
    private static WEB3LogUtility log =
        WEB3LogUtility.getInstance(WEB3FeqMarketRequestSenderServiceTest.class);

    public WEB3FeqMarketRequestSenderServiceTest(String arg0)
    {
        super(arg0);
    }

    protected void setUp() throws Exception
    {
        super.setUp();
    }

    protected void tearDown() throws Exception
    {
        super.tearDown();
    }
    
    //(訂正注文送信)
    public void testSend_case001() throws WEB3BaseException
    {
        final String STR_METHOD_NAME = "testSend_case001()";
        log.entering(STR_METHOD_NAME);
        
        TestBaseForMock.MOCK_MANAGER.setIsMockUsed(true);
        
        try{
            //FeqOrderUnitParams
            TestDBUtility.deleteAll(FeqOrderUnitParams.TYPE);
            FeqOrderUnitParams l_feqOrderUnit = TestDBUtility.getFeqOrderUnitRow();
            l_feqOrderUnit.setBranchId(33381L);
            l_feqOrderUnit.setOrderId(123456789L);
            TestDBUtility.insertWithDel(l_feqOrderUnit);
            
            //FeqProductParams
            TestDBUtility.deleteAll(FeqProductParams.TYPE);
            FeqProductParams l_feqProduct = TestDBUtility.getFeqProductRow();
            l_feqProduct.setProductId(400300090000000000L);
            TestDBUtility.insertWithDel(l_feqProduct);
            
            //ProductParams
            TestDBUtility.deleteAll(ProductParams.TYPE);
            //ProductParams l_Product = TestDBUtility.getProductRow();
            //l_Product.setProductId(3304148080000L);
            //TestDBUtility.insertWithDel(l_Product);
            
            FeqChangeOrderMarketRequestMessage l_feqChangeOrderRequest =
                new DefaultFeqChangeOrderMarketRequestMessage(null, 0, l_feqOrderUnit);
            boolean l_blnIsMarketNoSend = true;
            
            //Market
            TestDBUtility.deleteAll(MarketParams.TYPE);
            MarketParams l_marketParams = TestDBUtility.getMarketRow();
            l_marketParams.setMarketId(3303L);
            l_marketParams.setFeqOrderRequestDiv("1");
            TestDBUtility.insertWithDel(l_marketParams);
            
           //SleSendQRow
            TestDBUtility.deleteAll(SleSendQRow.TYPE);
            
            WEB3GentradeTradingTimeManagementForMock.mockIsSubmitMarketTrigger(true);
            
            WEB3FeqMarketRequestSenderService l_service = new WEB3FeqMarketRequestSenderService();
            DefaultMarketRequestSendResult l_defaultResult = (DefaultMarketRequestSendResult)l_service.send(l_feqChangeOrderRequest,l_blnIsMarketNoSend);
      
            assertEquals(WEB3ErrorCatalog.BUSINESS_ERROR_03104,l_defaultResult.getProcessingResult().getErrorInfo());
        }
        catch (WEB3BusinessLayerException l_ex)
        {
            log.error(l_ex.getMessage(), l_ex);
            log.exiting(STR_METHOD_NAME);
            fail();
        }
        catch (Exception l_ex)
        {
            log.error(STR_METHOD_NAME, l_ex);
            log.exiting(STR_METHOD_NAME);
            fail();
        }
        
        
        log.exiting(STR_METHOD_NAME);
    }
    
    
    //  (取消注文送信)
    public void testSend_case002() throws WEB3BaseException
    {
        final String STR_METHOD_NAME = "testSend_case002()";
        log.entering(STR_METHOD_NAME);
        
        TestBaseForMock.MOCK_MANAGER.setIsMockUsed(true);
        
        try{
            //FeqOrderUnitParams
            TestDBUtility.deleteAll(FeqOrderUnitParams.TYPE);
            FeqOrderUnitParams l_feqOrderUnit = TestDBUtility.getFeqOrderUnitRow();
            l_feqOrderUnit.setBranchId(33381L);
            l_feqOrderUnit.setOrderId(123456789L);
            TestDBUtility.insertWithDel(l_feqOrderUnit);
            
            //FeqProductParams
            TestDBUtility.deleteAll(FeqProductParams.TYPE);
            FeqProductParams l_feqProduct = TestDBUtility.getFeqProductRow();
            l_feqProduct.setProductId(400300090000000000L);
            TestDBUtility.insertWithDel(l_feqProduct);
            
            //ProductParams
            TestDBUtility.deleteAll(ProductParams.TYPE);
            //ProductParams l_Product = TestDBUtility.getProductRow();
            //l_Product.setProductId(3304148080000L);
            //TestDBUtility.insertWithDel(l_Product);
            
            CancelOrderMarketRequestMessage l_feqCancelOrderRequest =
                new DefaultCancelOrderMarketRequestMessage(null, 123456789L);
            boolean l_blnIsMarketNoSend = true;
            
            //Market
            TestDBUtility.deleteAll(MarketParams.TYPE);
            MarketParams l_marketParams = TestDBUtility.getMarketRow();
            l_marketParams.setMarketId(3303L);
            l_marketParams.setFeqOrderRequestDiv("1");
            TestDBUtility.insertWithDel(l_marketParams);
            
           //SleSendQRow
            TestDBUtility.deleteAll(SleSendQRow.TYPE);
            
            WEB3GentradeTradingTimeManagementForMock.mockIsSubmitMarketTrigger(true);
            
            WEB3FeqMarketRequestSenderService l_service = new WEB3FeqMarketRequestSenderService();
            DefaultMarketRequestSendResult l_defaultResult = (DefaultMarketRequestSendResult)l_service.send(l_feqCancelOrderRequest,l_blnIsMarketNoSend);
      
            assertEquals(WEB3ErrorCatalog.BUSINESS_ERROR_00394,l_defaultResult.getProcessingResult().getErrorInfo());
        }
        catch (WEB3BusinessLayerException l_ex)
        {
            log.error(l_ex.getMessage(), l_ex);
            log.exiting(STR_METHOD_NAME);
            fail();
        }
        catch (Exception l_ex)
        {
            log.error(STR_METHOD_NAME, l_ex);
            log.exiting(STR_METHOD_NAME);
            fail();
        }
        
        
        log.exiting(STR_METHOD_NAME);
    }
    

}
@
