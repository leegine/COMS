head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.04.07.01.53.54;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	5444d9d078c63f4;
filename	WEB3EquityChangeOrderHandlerTest.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : WEB3EquityChangeOrderHandlerTest.java
Author Name      : Daiwa Institute of Research
Revision History : 2007/12/31 于瀟（中訊）新規作成
*/
package webbroker3.equity.handler;

import test.util.TestDBUtility;
import webbroker3.common.WEB3BaseException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.common.define.WEB3MarketPreferencesNameDef;
import webbroker3.common.message.WEB3GenRequest;
import webbroker3.equity.message.WEB3EquityChangeCompleteRequest;
import webbroker3.equity.message.WEB3EquityChangeCompleteResponse;
import webbroker3.equity.message.WEB3EquityChangeConfirmRequest;
import webbroker3.equity.message.WEB3EquityChangeConfirmResponse;
import webbroker3.mock.TestBaseForMock;
import webbroker3.util.WEB3LogUtility;

import com.fitechlabs.xtrade.plugin.tc.eqtype.data.EqtypeOrderUnitParams;
import com.fitechlabs.xtrade.plugin.tc.eqtype.data.EqtypeOrderUnitRow;
import com.fitechlabs.xtrade.plugin.tc.gentrade.data.MarketParams;
import com.fitechlabs.xtrade.plugin.tc.gentrade.data.MarketPreferencesParams;
import com.fitechlabs.xtrade.plugin.tc.gentrade.data.MarketPreferencesRow;
import com.fitechlabs.xtrade.plugin.tc.gentrade.data.MarketRow;

public class WEB3EquityChangeOrderHandlerTest extends TestBaseForMock
{
    /**
     * ログ出力ユーティリティ。<BR>
     */
    private static WEB3LogUtility log = WEB3LogUtility.getInstance(
            WEB3EquityChangeOrderHandlerTest.class);

    public WEB3EquityChangeOrderHandlerTest(String arg0)
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

    /*
     * リクエストデータ.ID ＝ nullの場合
     * 
     * 返回?正確:BUSINESS_ERROR_00600
     */          
    public void testEquityChangeOrderConfirmRequest_C0001()
    {
        final String STR_METHOD_NAME = "testEquityChangeOrderConfirmRequest_C0001()";
        log.entering(TEST_START + STR_METHOD_NAME);
        
        WEB3EquityChangeOrderHandler l_changeOrderHandler =
            new WEB3EquityChangeOrderHandler();
        
        WEB3EquityChangeConfirmRequest l_request = new WEB3EquityChangeConfirmRequest();
        l_request.id = null;
        
        assertEquals(
            WEB3ErrorCatalog.BUSINESS_ERROR_00600,
            l_changeOrderHandler.equityChangeOrderConfirmRequest(l_request).errorInfo);
        
        log.exiting(STR_METHOD_NAME);
    }
    
    /*
     * 市場オブジェクトを取得ないの場合
     * 
     * 返回?正確:SYSTEM_ERROR_80005
     */
    public void testEquityChangeOrderConfirmRequest_C0002()
    {
        final String STR_METHOD_NAME = "testEquityChangeOrderConfirmRequest_C0002()";
        log.entering(TEST_START + STR_METHOD_NAME);
        
        WEB3EquityChangeOrderHandler l_changeOrderHandler =
            new WEB3EquityChangeOrderHandler();
        
        WEB3EquityChangeConfirmRequest l_request = new WEB3EquityChangeConfirmRequest();
        l_request.id = "123456789";
        
        try
        {
            TestDBUtility.deleteAll(EqtypeOrderUnitRow.TYPE);
            EqtypeOrderUnitParams l_eqtypeOrderUnitParams = TestDBUtility.getEqtypeOrderUnitRow();
            TestDBUtility.insertWithDel(l_eqtypeOrderUnitParams);
            TestDBUtility.deleteAll(MarketRow.TYPE);
            assertEquals(WEB3ErrorCatalog.SYSTEM_ERROR_80005,
                l_changeOrderHandler.equityChangeOrderConfirmRequest(l_request).errorInfo);

        }
        catch(Exception l_ex)
        {
            log.error("", l_ex);
            log.exiting(TEST_END + STR_METHOD_NAME);
            fail();
        }
        

        
        log.exiting(STR_METHOD_NAME);
    }
    //
    /*
     * PTS市場の場合
     * 
     * 正常通過
     */
    public void testEquityChangeOrderConfirmRequest_C0003()
    {
        final String STR_METHOD_NAME = "testEquityChangeOrderConfirmRequest_C0003()";
        log.entering(TEST_START + STR_METHOD_NAME);
        
        WEB3EquityChangeOrderHandler l_changeOrderHandler =
            new WEB3EquityChangeOrderHandler();
        
        WEB3EquityChangeConfirmRequest l_request = new WEB3EquityChangeConfirmRequest();
        
        l_request.id = "123456789";
        
        MOCK_MANAGER.setIsMockUsed(true);
        
        try
        {
            TestDBUtility.deleteAll(EqtypeOrderUnitRow.TYPE);
            EqtypeOrderUnitParams l_eqtypeOrderUnitParams = TestDBUtility.getEqtypeOrderUnitRow();
            TestDBUtility.insertWithDel(l_eqtypeOrderUnitParams);
            
            TestDBUtility.deleteAll(MarketRow.TYPE);
            MarketParams l_marketParams = TestDBUtility.getMarketRow();
            TestDBUtility.insertWithDel(l_marketParams);
            
            TestDBUtility.deleteAll(MarketPreferencesRow.TYPE);
            MarketPreferencesParams l_marketPreferencesParams = TestDBUtility.getMarketPreferencesRow();
            l_marketPreferencesParams.setName(WEB3MarketPreferencesNameDef.EQUITY_PTS_MARKET_DIV);
            l_marketPreferencesParams.setValue("1");
            TestDBUtility.insertWithDel(l_marketPreferencesParams);
            
            WEB3EquityChangeConfirmResponse l_changeConfirmResponse =
                new WEB3EquityChangeConfirmResponse();
            l_changeConfirmResponse.checkPrice = "aaa";
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                    "webbroker3.equity.service.delegate.stdimpls.WEB3EquityPTSChangeOrderServiceImpl", "execute",
                    new Class[]
                    { WEB3GenRequest.class },
                    l_changeConfirmResponse);
            assertEquals("aaa", l_changeOrderHandler.equityChangeOrderConfirmRequest(l_request).checkPrice);
        }
        catch(Exception l_ex)
        {
            log.error("", l_ex);
            log.exiting(TEST_END + STR_METHOD_NAME);
            fail();
        }
        log.exiting(STR_METHOD_NAME);
    }
    
    /*
     * PTS市場でない場合
     * 
     * 正常通過
     */
    public void testEquityChangeOrderConfirmRequest_C0004()
    {
        final String STR_METHOD_NAME = "testEquityChangeOrderConfirmRequest_C0004()";
        log.entering(TEST_START + STR_METHOD_NAME);
        
        WEB3EquityChangeOrderHandler l_changeOrderHandler =
            new WEB3EquityChangeOrderHandler();
        
        WEB3EquityChangeConfirmRequest l_request = new WEB3EquityChangeConfirmRequest();
        
        l_request.id = "123456789";
        
        MOCK_MANAGER.setIsMockUsed(true);
        try
        {
            TestDBUtility.deleteAll(EqtypeOrderUnitRow.TYPE);
            EqtypeOrderUnitParams l_eqtypeOrderUnitParams = TestDBUtility.getEqtypeOrderUnitRow();
            TestDBUtility.insertWithDel(l_eqtypeOrderUnitParams);
            
            TestDBUtility.deleteAll(MarketPreferencesRow.TYPE);
            
            WEB3EquityChangeConfirmResponse l_changeConfirmResponse =
                new WEB3EquityChangeConfirmResponse();
            l_changeConfirmResponse.checkPrice = "bbb";
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                    "webbroker3.equity.service.delegate.stdimpls.WEB3EquityChangeOrderServiceImpl", "execute",
                    new Class[]
                    { WEB3GenRequest.class },
                    l_changeConfirmResponse);
            
            assertEquals("bbb", l_changeOrderHandler.equityChangeOrderConfirmRequest(l_request).checkPrice);
        }
        catch(Exception l_ex)
        {
            log.error("", l_ex);
            log.exiting(TEST_END + STR_METHOD_NAME);
            fail();
        }
        
        log.exiting(STR_METHOD_NAME);
    }
    
    /*
     * PTS市場の場合
     * 
     * 返回?正確:BUSINESS_ERROR_00600
     */
    public void testEquityChangeOrderConfirmRequest_C0005()
    {
        final String STR_METHOD_NAME = "testEquityChangeOrderConfirmRequest_C0005()";
        log.entering(TEST_START + STR_METHOD_NAME);
        
        WEB3EquityChangeOrderHandler l_changeOrderHandler =
            new WEB3EquityChangeOrderHandler();
        
        WEB3EquityChangeConfirmRequest l_request = new WEB3EquityChangeConfirmRequest();
        
        l_request.id = "123456789";
        
        MOCK_MANAGER.setIsMockUsed(true);
        try
        {
            TestDBUtility.deleteAll(EqtypeOrderUnitRow.TYPE);
            EqtypeOrderUnitParams l_eqtypeOrderUnitParams = TestDBUtility.getEqtypeOrderUnitRow();
            TestDBUtility.insertWithDel(l_eqtypeOrderUnitParams);
            
            TestDBUtility.deleteAll(MarketRow.TYPE);
            MarketParams l_marketParams = TestDBUtility.getMarketRow();
            TestDBUtility.insertWithDel(l_marketParams);
            
            TestDBUtility.deleteAll(MarketPreferencesRow.TYPE);
            MarketPreferencesParams l_marketPreferencesParams = TestDBUtility.getMarketPreferencesRow();
            l_marketPreferencesParams.setName(WEB3MarketPreferencesNameDef.EQUITY_PTS_MARKET_DIV);
            l_marketPreferencesParams.setValue("1");
            TestDBUtility.insertWithDel(l_marketPreferencesParams);
            
            WEB3BaseException l_exception =
                new WEB3BaseException(WEB3ErrorCatalog.BUSINESS_ERROR_00600, "");
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(   
                    "webbroker3.equity.service.delegate.stdimpls.WEB3EquityPTSChangeOrderServiceImpl", "execute",
                    new Class[]
                    { WEB3GenRequest.class },
                    l_exception);
        }
        catch(Exception l_ex)
        {
            log.error("", l_ex);
            log.exiting(TEST_END + STR_METHOD_NAME);
            fail();
        }
        
        assertEquals(WEB3ErrorCatalog.BUSINESS_ERROR_00600, l_changeOrderHandler.equityChangeOrderConfirmRequest(l_request).errorInfo);
        
        log.exiting(STR_METHOD_NAME);
    }

    /*
     * リクエストデータ.ID ＝ nullの場合
     * 
     * 返回?正確:BUSINESS_ERROR_00600
     */          
    public void testEquityChangeOrderCompleteRequest_C0001()
    {
        final String STR_METHOD_NAME = "testEquityChangeOrderCompleteRequest_C0001()";
        log.entering(TEST_START + STR_METHOD_NAME);
        
        WEB3EquityChangeOrderHandler l_changeOrderHandler =
            new WEB3EquityChangeOrderHandler();
        
        WEB3EquityChangeCompleteRequest l_request = new WEB3EquityChangeCompleteRequest();
        l_request.id = null;
        
        assertEquals(
            WEB3ErrorCatalog.BUSINESS_ERROR_00600,
            l_changeOrderHandler.equityChangeOrderCompleteRequest(l_request).errorInfo);
        
        log.exiting(STR_METHOD_NAME);
    }

    /*
     * 市場オブジェクトを取得ないの場合
     * 
     * 返回?正確:SYSTEM_ERROR_80005
     */
    public void testEquityChangeOrderCompleteRequest_C0002()
    {
        final String STR_METHOD_NAME = "testEquityChangeOrderCompleteRequest_C0002()";
        log.entering(TEST_START + STR_METHOD_NAME);
        
        WEB3EquityChangeOrderHandler l_changeOrderHandler =
            new WEB3EquityChangeOrderHandler();
        
        WEB3EquityChangeCompleteRequest l_request = new WEB3EquityChangeCompleteRequest();
        l_request.id = "123456789";
        
        try
        {
            TestDBUtility.deleteAll(EqtypeOrderUnitRow.TYPE);
            EqtypeOrderUnitParams l_eqtypeOrderUnitParams = TestDBUtility.getEqtypeOrderUnitRow();
            TestDBUtility.insertWithDel(l_eqtypeOrderUnitParams);
            TestDBUtility.deleteAll(MarketRow.TYPE);
            assertEquals(WEB3ErrorCatalog.SYSTEM_ERROR_80005,
                l_changeOrderHandler.equityChangeOrderCompleteRequest(l_request).errorInfo);

        }
        catch(Exception l_ex)
        {
            log.error("", l_ex);
            log.exiting(TEST_END + STR_METHOD_NAME);
            fail();
        }
        
        log.exiting(STR_METHOD_NAME);
    }
    
    
    /*
     * PTS市場の場合
     * 
     * 正常通過
     */
    public void testEquityChangeOrderCompleteRequest_C0003()
    {
        final String STR_METHOD_NAME = "testEquityChangeOrderCompleteRequest_C0003()";
        log.entering(TEST_START + STR_METHOD_NAME);
        
        WEB3EquityChangeOrderHandler l_changeOrderHandler =
            new WEB3EquityChangeOrderHandler();
        
        WEB3EquityChangeCompleteRequest l_request = new WEB3EquityChangeCompleteRequest();
        
        l_request.id = "123456789";
        
        MOCK_MANAGER.setIsMockUsed(true);
        
        try
        {
            TestDBUtility.deleteAll(EqtypeOrderUnitRow.TYPE);
            EqtypeOrderUnitParams l_eqtypeOrderUnitParams = TestDBUtility.getEqtypeOrderUnitRow();
            TestDBUtility.insertWithDel(l_eqtypeOrderUnitParams);
            
            TestDBUtility.deleteAll(MarketRow.TYPE);
            MarketParams l_marketParams = TestDBUtility.getMarketRow();
            TestDBUtility.insertWithDel(l_marketParams);
            
            TestDBUtility.deleteAll(MarketPreferencesRow.TYPE);
            MarketPreferencesParams l_marketPreferencesParams = TestDBUtility.getMarketPreferencesRow();
            l_marketPreferencesParams.setName(WEB3MarketPreferencesNameDef.EQUITY_PTS_MARKET_DIV);
            l_marketPreferencesParams.setValue("1");
            TestDBUtility.insertWithDel(l_marketPreferencesParams);
            
            WEB3EquityChangeCompleteResponse l_changeCompleteResponse =
                new WEB3EquityChangeCompleteResponse();
            l_changeCompleteResponse.orderActionId = "aaa";
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                    "webbroker3.equity.service.delegate.stdimpls.WEB3EquityPTSChangeOrderServiceImpl", "execute",
                    new Class[]
                    { WEB3GenRequest.class },
                    l_changeCompleteResponse);
            assertEquals("aaa", l_changeOrderHandler.equityChangeOrderCompleteRequest(l_request).orderActionId);
        }
        catch(Exception l_ex)
        {
            log.error("", l_ex);
            log.exiting(TEST_END + STR_METHOD_NAME);
            fail();
        }
        log.exiting(STR_METHOD_NAME);
    }
    
    /*
     * PTS市場でない場合
     * 
     * 正常通過
     */
    public void testEquityChangeOrderCompleteRequest_C0004()
    {
        final String STR_METHOD_NAME = "testEquityChangeOrderCompleteRequest_C0004()";
        log.entering(TEST_START + STR_METHOD_NAME);
        
        WEB3EquityChangeOrderHandler l_changeOrderHandler =
            new WEB3EquityChangeOrderHandler();
        
        WEB3EquityChangeCompleteRequest l_request = new WEB3EquityChangeCompleteRequest();
        
        l_request.id = "123456789";
        
        MOCK_MANAGER.setIsMockUsed(true);
        try
        {
            TestDBUtility.deleteAll(EqtypeOrderUnitRow.TYPE);
            EqtypeOrderUnitParams l_eqtypeOrderUnitParams = TestDBUtility.getEqtypeOrderUnitRow();
            TestDBUtility.insertWithDel(l_eqtypeOrderUnitParams);
            
            TestDBUtility.deleteAll(MarketPreferencesRow.TYPE);
            
            WEB3EquityChangeCompleteResponse l_changeCompleteResponse =
                new WEB3EquityChangeCompleteResponse();
            l_changeCompleteResponse.orderActionId = "bbb";
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                    "webbroker3.equity.service.delegate.stdimpls.WEB3EquityChangeOrderServiceImpl", "execute",
                    new Class[]
                    { WEB3GenRequest.class },
                    l_changeCompleteResponse);
            
            assertEquals("bbb", l_changeOrderHandler.equityChangeOrderCompleteRequest(l_request).orderActionId);
        }
        catch(Exception l_ex)
        {
            log.error("", l_ex);
            log.exiting(TEST_END + STR_METHOD_NAME);
            fail();
        }
        
        log.exiting(STR_METHOD_NAME);
    }
    
    /*
     * PTS市場の場合
     * 
     * 返回?正確:BUSINESS_ERROR_00600
     */
    public void testEquityChangeOrderCompleteRequest_C0005()
    {
        final String STR_METHOD_NAME = "testEquityChangeOrderCompleteRequest_C0005()";
        log.entering(TEST_START + STR_METHOD_NAME);
        
        WEB3EquityChangeOrderHandler l_changeOrderHandler =
            new WEB3EquityChangeOrderHandler();
        
        WEB3EquityChangeCompleteRequest l_request = new WEB3EquityChangeCompleteRequest();
         
        l_request.id = "123456789";
        
        MOCK_MANAGER.setIsMockUsed(true);
        try
        {
            TestDBUtility.deleteAll(EqtypeOrderUnitRow.TYPE);
            EqtypeOrderUnitParams l_eqtypeOrderUnitParams = TestDBUtility.getEqtypeOrderUnitRow();
            TestDBUtility.insertWithDel(l_eqtypeOrderUnitParams);
            
            TestDBUtility.deleteAll(MarketRow.TYPE);
            MarketParams l_marketParams = TestDBUtility.getMarketRow();
            TestDBUtility.insertWithDel(l_marketParams);
            
            TestDBUtility.deleteAll(MarketPreferencesRow.TYPE);
            MarketPreferencesParams l_marketPreferencesParams = TestDBUtility.getMarketPreferencesRow();
            l_marketPreferencesParams.setName(WEB3MarketPreferencesNameDef.EQUITY_PTS_MARKET_DIV);
            l_marketPreferencesParams.setValue("1");
            TestDBUtility.insertWithDel(l_marketPreferencesParams);
            
            WEB3BaseException l_exception =
                new WEB3BaseException(WEB3ErrorCatalog.BUSINESS_ERROR_00600, "");
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(   
                    "webbroker3.equity.service.delegate.stdimpls.WEB3EquityPTSChangeOrderServiceImpl", "execute",
                    new Class[]
                    { WEB3GenRequest.class },
                    l_exception);
        }
        catch(Exception l_ex)
        {
            log.error("", l_ex);
            log.exiting(TEST_END + STR_METHOD_NAME);
            fail();
        }
        
        assertEquals(WEB3ErrorCatalog.BUSINESS_ERROR_00600, l_changeOrderHandler.equityChangeOrderCompleteRequest(l_request).errorInfo);
        
        log.exiting(STR_METHOD_NAME);
    }
    
    //追加case 20080125
    //equityChangeOrderConfirmRequest
    //２）　@取消対象注文単位を取得する
    //拡張株式注文マネージャ.getOrderUnits(リクエストデータ.ID)
    //沒有輿 リクエストデータ.ID 想對應的注文単位
    public void testEquityChangeOrderConfirmRequest_C0006()
    {
        final String STR_METHOD_NAME = "testEquityChangeOrderConfirmRequest_C0006()";
        log.entering(TEST_START + STR_METHOD_NAME);
        
        WEB3EquityChangeOrderHandler l_changeOrderHandler =
            new WEB3EquityChangeOrderHandler();
        
        WEB3EquityChangeConfirmRequest l_request = new WEB3EquityChangeConfirmRequest();
        
        l_request.id = "12345689";
        try
        {
            TestDBUtility.deleteAll(EqtypeOrderUnitRow.TYPE);
            EqtypeOrderUnitParams l_eqtypeOrderUnitParams = TestDBUtility.getEqtypeOrderUnitRow();
            TestDBUtility.insertWithDel(l_eqtypeOrderUnitParams);
            
            WEB3EquityChangeConfirmResponse l_response =
                l_changeOrderHandler.equityChangeOrderConfirmRequest(l_request);
                
            assertEquals(WEB3ErrorCatalog.SYSTEM_ERROR_80005, l_response.errorInfo);
        }
        catch(Exception l_ex)
        {
            log.error("", l_ex);
            log.exiting(TEST_END + STR_METHOD_NAME);
            fail();
        }
        log.exiting(STR_METHOD_NAME);
    }
    
    //equityChangeOrderCompleteRequest
    //２）　@取消対象注文単位を取得する
    //拡張株式注文マネージャ.getOrderUnits(リクエストデータ.ID)
    //沒有輿 リクエストデータ.ID 想對應的注文単位
    public void testEquityChangeOrderCompleteRequest_C0006()
    {
        final String STR_METHOD_NAME = "testEquityChangeOrderCompleteRequest_C0006()";
        log.entering(TEST_START + STR_METHOD_NAME);
        
        WEB3EquityChangeOrderHandler l_changeOrderHandler =
            new WEB3EquityChangeOrderHandler();
        
        WEB3EquityChangeCompleteRequest l_request = new WEB3EquityChangeCompleteRequest();
         
        l_request.id = "12345679";
        
        try
        {
            TestDBUtility.deleteAll(EqtypeOrderUnitRow.TYPE);
            EqtypeOrderUnitParams l_eqtypeOrderUnitParams = TestDBUtility.getEqtypeOrderUnitRow();
            TestDBUtility.insertWithDel(l_eqtypeOrderUnitParams);
            
            WEB3EquityChangeCompleteResponse l_response =
                l_changeOrderHandler.equityChangeOrderCompleteRequest(l_request);
            assertEquals(WEB3ErrorCatalog.SYSTEM_ERROR_80005, l_response.errorInfo);
        }
        catch(Exception l_ex)
        {
            log.error("", l_ex);
            log.exiting(TEST_END + STR_METHOD_NAME);
            fail();
        }
        log.exiting(STR_METHOD_NAME);
    }
}

@
