head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.04.07.01.53.58;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	5444d9d078c63f4;
filename	WEB3EquityCancelOrderHandlerTest.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : WEB3EquityCancelOrderHandlerTest.java
Author Name      : Daiwa Institute of Research
Revision History : 2007/12/30 于瀟（中訊）新規作成
*/
package webbroker3.equity.handler;

import test.util.TestDBUtility;
import webbroker3.common.WEB3BaseException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.common.define.WEB3MarketPreferencesNameDef;
import webbroker3.common.message.WEB3GenRequest;
import webbroker3.equity.message.WEB3EquityCancelCompleteRequest;
import webbroker3.equity.message.WEB3EquityCancelCompleteResponse;
import webbroker3.equity.message.WEB3EquityCancelConfirmRequest;
import webbroker3.equity.message.WEB3EquityCancelConfirmResponse;
import webbroker3.mock.TestBaseForMock;
import webbroker3.util.WEB3LogUtility;

import com.fitechlabs.xtrade.plugin.tc.eqtype.data.EqtypeOrderUnitParams;
import com.fitechlabs.xtrade.plugin.tc.eqtype.data.EqtypeOrderUnitRow;
import com.fitechlabs.xtrade.plugin.tc.gentrade.data.MarketParams;
import com.fitechlabs.xtrade.plugin.tc.gentrade.data.MarketPreferencesParams;
import com.fitechlabs.xtrade.plugin.tc.gentrade.data.MarketPreferencesRow;
import com.fitechlabs.xtrade.plugin.tc.gentrade.data.MarketRow;

public class WEB3EquityCancelOrderHandlerTest extends TestBaseForMock
{
    /**
     * ログ出力ユーティリティ。<BR>
     */
    private static WEB3LogUtility log = WEB3LogUtility.getInstance(
            WEB3EquityCancelOrderHandlerTest.class);

    public WEB3EquityCancelOrderHandlerTest(String arg0)
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
     * ?出異常信息:BUSINESS_ERROR_00600
     */
    public void testEquityCancelConfirmRequest_C0001()
    {
        final String STR_METHOD_NAME = "testEquityCancelConfirmRequest_C0001()";
        log.entering(TEST_START + STR_METHOD_NAME);
        
        WEB3EquityCancelOrderHandler l_cancelOrderHandler =
            new WEB3EquityCancelOrderHandler();
        
        WEB3EquityCancelConfirmRequest l_cancelConfirmRequest =
            new WEB3EquityCancelConfirmRequest();
        
        l_cancelConfirmRequest.id = null;
        
        assertEquals(
                WEB3ErrorCatalog.BUSINESS_ERROR_00600,
                l_cancelOrderHandler.equityCancelConfirmRequest(l_cancelConfirmRequest).errorInfo);
            
        log.exiting(STR_METHOD_NAME);
    }
    
    /*
     * 市場オブジェクトを取得ないの場合
     * 
     * ?出異常信息:SYSTEM_ERROR_80005
     */
    public void testEquityCancelConfirmRequest_C0002()
    {
        final String STR_METHOD_NAME = "testEquityCancelConfirmRequest_C0001()";
        log.entering(TEST_START + STR_METHOD_NAME);
        
        WEB3EquityCancelOrderHandler l_cancelOrderHandler =
            new WEB3EquityCancelOrderHandler();
        
        WEB3EquityCancelConfirmRequest l_cancelConfirmRequest =
            new WEB3EquityCancelConfirmRequest();
        
        l_cancelConfirmRequest.id = "123456789";
        
        try
        {
            TestDBUtility.deleteAll(EqtypeOrderUnitRow.TYPE);
            EqtypeOrderUnitParams l_eqtypeOrderUnitParams = TestDBUtility.getEqtypeOrderUnitRow();
            TestDBUtility.insertWithDel(l_eqtypeOrderUnitParams);
            TestDBUtility.deleteAll(MarketRow.TYPE);
            
            assertEquals(
                    WEB3ErrorCatalog.SYSTEM_ERROR_80005,
                    l_cancelOrderHandler.equityCancelConfirmRequest(l_cancelConfirmRequest).errorInfo);
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
    public void testEquityCancelConfirmRequest_C0003()
    {
        final String STR_METHOD_NAME = "testEquityCancelConfirmRequest_C0003()";
        log.entering(TEST_START + STR_METHOD_NAME);
        
        WEB3EquityCancelOrderHandler l_cancelOrderHandler =
            new WEB3EquityCancelOrderHandler();
        
        WEB3EquityCancelConfirmRequest l_cancelConfirmRequest =
            new WEB3EquityCancelConfirmRequest();
        
        l_cancelConfirmRequest.id = "123456789";
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
            
            WEB3EquityCancelConfirmResponse l_response =
                new WEB3EquityCancelConfirmResponse();
            l_response.marketCode = "ABC";
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                    "webbroker3.equity.service.delegate.stdimpls.WEB3EquityPTSCancelOrderServiceImpl", "execute",
                    new Class[]
                    { WEB3GenRequest.class },
                    l_response);
            
            assertEquals("ABC", l_cancelOrderHandler.equityCancelConfirmRequest(l_cancelConfirmRequest).marketCode);
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
     * ?出異常信息:BUSINESS_ERROR_00600
     */
    public void testEquityCancelConfirmRequest_C0004()
    {
        final String STR_METHOD_NAME = "testEquityCancelConfirmRequest_C0004()";
        log.entering(TEST_START + STR_METHOD_NAME);
        
        WEB3EquityCancelOrderHandler l_cancelOrderHandler =
            new WEB3EquityCancelOrderHandler();
        
        WEB3EquityCancelConfirmRequest l_cancelConfirmRequest =
            new WEB3EquityCancelConfirmRequest();
        
        l_cancelConfirmRequest.id = "123456789";
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
                    "webbroker3.equity.service.delegate.stdimpls.WEB3EquityPTSCancelOrderServiceImpl", "execute",
                    new Class[]
                    { WEB3GenRequest.class },
                    l_exception);
            
            assertEquals(WEB3ErrorCatalog.BUSINESS_ERROR_00600, l_cancelOrderHandler.equityCancelConfirmRequest(l_cancelConfirmRequest).errorInfo);
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
    public void testEquityCancelConfirmRequest_C0005()
    {
        final String STR_METHOD_NAME = "testEquityCancelConfirmRequest_C0005()";
        log.entering(TEST_START + STR_METHOD_NAME);
        
        WEB3EquityCancelOrderHandler l_cancelOrderHandler =
            new WEB3EquityCancelOrderHandler();
        
        WEB3EquityCancelConfirmRequest l_cancelConfirmRequest =
            new WEB3EquityCancelConfirmRequest();
        
        l_cancelConfirmRequest.id = "123456789";
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
            
            WEB3EquityCancelConfirmResponse l_response =
                new WEB3EquityCancelConfirmResponse();
            l_response.marketCode = "qwe";
            
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                    "webbroker3.equity.service.delegate.stdimpls.WEB3EquityCancelOrderServiceImpl", "execute",
                    new Class[]
                    { WEB3GenRequest.class },
                    l_response);
            
            assertEquals("qwe", l_cancelOrderHandler.equityCancelConfirmRequest(l_cancelConfirmRequest).marketCode);
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
     * リクエストデータ.ID ＝ nullの場合
     * 
     * ?出異常信息:BUSINESS_ERROR_00600
     */
    public void testEquityCancelCompleteRequest_C0001()
    {
        final String STR_METHOD_NAME = "testEquityCancelCompleteRequest_C0001()";
        log.entering(TEST_START + STR_METHOD_NAME);
        
        WEB3EquityCancelOrderHandler l_cancelOrderHandler =
            new WEB3EquityCancelOrderHandler();
        
        WEB3EquityCancelCompleteRequest l_request =
            new WEB3EquityCancelCompleteRequest();
        
        l_request.id = null;
        
        assertEquals(
                WEB3ErrorCatalog.BUSINESS_ERROR_00600,
                l_cancelOrderHandler.equityCancelCompleteRequest(l_request).errorInfo);
            
        log.exiting(STR_METHOD_NAME);
    }
    
    /*
     * 市場オブジェクトを取得ないの場合
     * 
     * ?出異常信息:SYSTEM_ERROR_80005
     */
    public void testEquityCancelCompleteRequest_C0002()
    {
        final String STR_METHOD_NAME = "testEquityCancelCompleteRequest_C0002()";
        log.entering(TEST_START + STR_METHOD_NAME);
        
        WEB3EquityCancelOrderHandler l_cancelOrderHandler =
            new WEB3EquityCancelOrderHandler();
        
        WEB3EquityCancelCompleteRequest l_request =
            new WEB3EquityCancelCompleteRequest();
        
        l_request.id = "123456789";
        
        try
        {
            TestDBUtility.deleteAll(EqtypeOrderUnitRow.TYPE);
            EqtypeOrderUnitParams l_eqtypeOrderUnitParams = TestDBUtility.getEqtypeOrderUnitRow();
            TestDBUtility.insertWithDel(l_eqtypeOrderUnitParams);
            TestDBUtility.deleteAll(MarketRow.TYPE);
            
            assertEquals(
                    WEB3ErrorCatalog.SYSTEM_ERROR_80005,
                    l_cancelOrderHandler.equityCancelCompleteRequest(l_request).errorInfo);
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
    public void testEquityCancelCompleteRequest_C0003()
    {
        final String STR_METHOD_NAME = "testEquityCancelCompleteRequest_C0003()";
        log.entering(TEST_START + STR_METHOD_NAME);
        
        WEB3EquityCancelOrderHandler l_cancelOrderHandler =
            new WEB3EquityCancelOrderHandler();
        
        WEB3EquityCancelCompleteRequest l_request =
            new WEB3EquityCancelCompleteRequest();
        
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
            
            WEB3EquityCancelCompleteResponse l_response =
                new WEB3EquityCancelCompleteResponse();
            l_response.orderActionId = "ABC";
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                    "webbroker3.equity.service.delegate.stdimpls.WEB3EquityPTSCancelOrderServiceImpl", "execute",
                    new Class[]
                    { WEB3GenRequest.class },
                    l_response);
            
            assertEquals("ABC", l_cancelOrderHandler.equityCancelCompleteRequest(l_request).orderActionId);
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
     * ?出異常信息:BUSINESS_ERROR_00600
     */
    public void testEquityCancelCompleteRequest_C0004()
    {
        final String STR_METHOD_NAME = "testEquityCancelCompleteRequest_C0004()";
        log.entering(TEST_START + STR_METHOD_NAME);
        
        WEB3EquityCancelOrderHandler l_cancelOrderHandler =
            new WEB3EquityCancelOrderHandler();
        
        WEB3EquityCancelCompleteRequest l_request =
            new WEB3EquityCancelCompleteRequest();
        
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
                    "webbroker3.equity.service.delegate.stdimpls.WEB3EquityPTSCancelOrderServiceImpl", "execute",
                    new Class[]
                    { WEB3GenRequest.class },
                    l_exception);
            
            assertEquals(WEB3ErrorCatalog.BUSINESS_ERROR_00600, l_cancelOrderHandler.equityCancelCompleteRequest(l_request).errorInfo);
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
    public void testEquityCancelCompleteRequest_C0005()
    {
        final String STR_METHOD_NAME = "testEquityCancelCompleteRequest_C0005()";
        log.entering(TEST_START + STR_METHOD_NAME);
        
        WEB3EquityCancelOrderHandler l_cancelOrderHandler =
            new WEB3EquityCancelOrderHandler();
        
        WEB3EquityCancelCompleteRequest l_request =
            new WEB3EquityCancelCompleteRequest();
        
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
            
            WEB3EquityCancelCompleteResponse l_response =
                new WEB3EquityCancelCompleteResponse();
            l_response.orderActionId = "qwe";
            
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                    "webbroker3.equity.service.delegate.stdimpls.WEB3EquityCancelOrderServiceImpl", "execute",
                    new Class[]
                    { WEB3GenRequest.class },
                    l_response);
            
            assertEquals("qwe", l_cancelOrderHandler.equityCancelCompleteRequest(l_request).orderActionId);
        }
        catch(Exception l_ex)
        {
            log.error("", l_ex);
            log.exiting(TEST_END + STR_METHOD_NAME);
            fail();
        }
        log.exiting(STR_METHOD_NAME);
    }
    
    //追加case 20080125
    //equityCancelConfirmRequest
    //２）　@取消対象注文単位を取得する
    //拡張株式注文マネージャ.getOrderUnits(リクエストデータ.ID)
    //沒有輿 リクエストデータ.ID 想對應的注文単位
    public void testEquityCancelConfirmRequest_C0006()
    {
        final String STR_METHOD_NAME = "testEquityCancelConfirmRequest_C0006()";
        log.entering(TEST_START + STR_METHOD_NAME);
        
        WEB3EquityCancelOrderHandler l_cancelOrderHandler =
            new WEB3EquityCancelOrderHandler();
        
        WEB3EquityCancelConfirmRequest l_cancelConfirmRequest =
            new WEB3EquityCancelConfirmRequest();
        
        l_cancelConfirmRequest.id = "12345678";
        
        try
        {
            TestDBUtility.deleteAll(EqtypeOrderUnitRow.TYPE);
            EqtypeOrderUnitParams l_eqtypeOrderUnitParams = TestDBUtility.getEqtypeOrderUnitRow();
            TestDBUtility.insertWithDel(l_eqtypeOrderUnitParams);
            WEB3EquityCancelConfirmResponse l_response =
                l_cancelOrderHandler.equityCancelConfirmRequest(l_cancelConfirmRequest);

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
    
    //equityCancelCompleteRequest
    //２）　@取消対象注文単位を取得する
    //拡張株式注文マネージャ.getOrderUnits(リクエストデータ.ID)
    //沒有輿 リクエストデータ.ID 想對應的注文単位
    public void testEquityCancelCompleteRequest_C0006()
    {
        final String STR_METHOD_NAME = "testEquityCancelCompleteRequest_C0006()";
        log.entering(TEST_START + STR_METHOD_NAME);
        
        WEB3EquityCancelOrderHandler l_cancelOrderHandler =
            new WEB3EquityCancelOrderHandler();
        
        WEB3EquityCancelCompleteRequest l_request =
            new WEB3EquityCancelCompleteRequest();
        
        l_request.id = "123456789";

        try
        {
            TestDBUtility.deleteAll(EqtypeOrderUnitRow.TYPE);
            EqtypeOrderUnitParams l_eqtypeOrderUnitParams = TestDBUtility.getEqtypeOrderUnitRow();
            l_eqtypeOrderUnitParams.setOrderId(12345678);
            TestDBUtility.insertWithDel(l_eqtypeOrderUnitParams);

            WEB3EquityCancelCompleteResponse l_response =
                l_cancelOrderHandler.equityCancelCompleteRequest(l_request);
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
