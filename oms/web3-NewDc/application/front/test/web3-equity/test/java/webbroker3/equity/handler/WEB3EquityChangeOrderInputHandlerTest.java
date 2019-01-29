head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.04.07.01.54.03;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	5444d9d078c63f4;
filename	WEB3EquityChangeOrderInputHandlerTest.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : WEB3EquityChangeOrderInputHandlerTest.java
Author Name      : Daiwa Institute of Research
Revision History : 2007/12/25 于瀟（中訊）新規作成
*/
package webbroker3.equity.handler;

import test.util.TestDBUtility;
import webbroker3.common.WEB3BaseException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.common.define.WEB3MarketPreferencesNameDef;
import webbroker3.common.message.WEB3GenRequest;
import webbroker3.equity.message.WEB3EquityChangeInputRequest;
import webbroker3.equity.message.WEB3EquityChangeInputResponse;
import webbroker3.mock.TestBaseForMock;
import webbroker3.util.WEB3LogUtility;

import com.fitechlabs.xtrade.plugin.tc.eqtype.data.EqtypeOrderUnitParams;
import com.fitechlabs.xtrade.plugin.tc.eqtype.data.EqtypeOrderUnitRow;
import com.fitechlabs.xtrade.plugin.tc.gentrade.data.MarketParams;
import com.fitechlabs.xtrade.plugin.tc.gentrade.data.MarketPreferencesParams;
import com.fitechlabs.xtrade.plugin.tc.gentrade.data.MarketPreferencesRow;
import com.fitechlabs.xtrade.plugin.tc.gentrade.data.MarketRow;

public class WEB3EquityChangeOrderInputHandlerTest extends TestBaseForMock
{
    /**
     * ログ出力ユーティリティ。<BR>
     */
    private static WEB3LogUtility log = WEB3LogUtility.getInstance(
            WEB3EquityChangeOrderInputHandlerTest.class);

    public WEB3EquityChangeOrderInputHandlerTest(String arg0)
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
    public void testEquityChangeOrderInputRequest_C0001()
    {
        final String STR_METHOD_NAME = "testEquityChangeOrderInputRequest_C0001()";
        log.entering(TEST_START + STR_METHOD_NAME);
        
        WEB3EquityChangeOrderInputHandler l_changeOrderInputHandler =
            new WEB3EquityChangeOrderInputHandler();
        
        WEB3EquityChangeInputRequest l_request = new WEB3EquityChangeInputRequest();
        l_request.id = null;
        
        assertEquals(
            WEB3ErrorCatalog.BUSINESS_ERROR_00600,
            l_changeOrderInputHandler.equityChangeOrderInputRequest(l_request).errorInfo);
        
        log.exiting(STR_METHOD_NAME);
    }
    
    /*
     * 市場オブジェクトを取得ないの場合
     * 
     * ?出異常信息:SYSTEM_ERROR_80005
     */
    public void testEquityChangeOrderInputRequest_C0002()
    {
        final String STR_METHOD_NAME = "testEquityChangeOrderInputRequest_C0002()";
        log.entering(TEST_START + STR_METHOD_NAME);
        
        WEB3EquityChangeOrderInputHandler l_changeOrderInputHandler =
            new WEB3EquityChangeOrderInputHandler();
        
        WEB3EquityChangeInputRequest l_request = new WEB3EquityChangeInputRequest();
        l_request.id = "123456789";
        
        try
        {
            TestDBUtility.deleteAll(EqtypeOrderUnitRow.TYPE);
            EqtypeOrderUnitParams l_eqtypeOrderUnitParams = TestDBUtility.getEqtypeOrderUnitRow();
            TestDBUtility.insertWithDel(l_eqtypeOrderUnitParams);
            TestDBUtility.deleteAll(MarketRow.TYPE);
            assertEquals(WEB3ErrorCatalog.SYSTEM_ERROR_80005,
                l_changeOrderInputHandler.equityChangeOrderInputRequest(l_request).errorInfo);

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
    public void testEquityChangeOrderInputRequest_C0003()
    {
        final String STR_METHOD_NAME = "testEquityChangeOrderInputRequest_C0003()";
        log.entering(TEST_START + STR_METHOD_NAME);
        
        WEB3EquityChangeOrderInputHandler l_changeOrderInputHandler =
            new WEB3EquityChangeOrderInputHandler();
        
        WEB3EquityChangeInputRequest l_request = new WEB3EquityChangeInputRequest();
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
            
            WEB3EquityChangeInputResponse l_changeInputResponse =
                new WEB3EquityChangeInputResponse();
            l_changeInputResponse.marketCode = "aaa";
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                    "webbroker3.equity.service.delegate.stdimpls.WEB3EquityPTSChangeOrderInputServiceImpl", "execute",
                    new Class[]
                    { WEB3GenRequest.class },
                    l_changeInputResponse);
            assertEquals("aaa", l_changeOrderInputHandler.equityChangeOrderInputRequest(l_request).marketCode);
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
    public void testEquityChangeOrderInputRequest_C0004()
    {
        final String STR_METHOD_NAME = "testEquityChangeOrderInputRequest_C0004()";
        log.entering(TEST_START + STR_METHOD_NAME);
        
        WEB3EquityChangeOrderInputHandler l_changeOrderInputHandler =
            new WEB3EquityChangeOrderInputHandler();
        
        WEB3EquityChangeInputRequest l_request = new WEB3EquityChangeInputRequest();
        l_request.id = "123456789";
        
        MOCK_MANAGER.setIsMockUsed(true);
        try
        {
            TestDBUtility.deleteAll(EqtypeOrderUnitRow.TYPE);
            EqtypeOrderUnitParams l_eqtypeOrderUnitParams = TestDBUtility.getEqtypeOrderUnitRow();
            TestDBUtility.insertWithDel(l_eqtypeOrderUnitParams);
            
            TestDBUtility.deleteAll(MarketPreferencesRow.TYPE);
            
            WEB3EquityChangeInputResponse l_changeInputResponse =
                new WEB3EquityChangeInputResponse();
            l_changeInputResponse.marketCode = "bbb";
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                    "webbroker3.equity.service.delegate.stdimpls.WEB3EquityChangeOrderInputServiceImpl", "execute",
                    new Class[]
                    { WEB3GenRequest.class },
                    l_changeInputResponse);
            
            assertEquals("bbb", l_changeOrderInputHandler.equityChangeOrderInputRequest(l_request).marketCode);
        }
        catch(Exception l_ex)
        {
            log.error("", l_ex);
            log.exiting(TEST_END + STR_METHOD_NAME);
            fail();
        }
        
        assertEquals("bbb", l_changeOrderInputHandler.equityChangeOrderInputRequest(l_request).marketCode);
        
        log.exiting(STR_METHOD_NAME);
    }
    
    /*
     * PTS市場の場合
     * 
     * ?出異常信息:BUSINESS_ERROR_00600
     */
    public void testEquityChangeOrderInputRequest_C0005()
    {
        final String STR_METHOD_NAME = "testEquityChangeOrderInputRequest_C0005()";
        log.entering(TEST_START + STR_METHOD_NAME);
        
        WEB3EquityChangeOrderInputHandler l_changeOrderInputHandler =
            new WEB3EquityChangeOrderInputHandler();
        
        WEB3EquityChangeInputRequest l_request = new WEB3EquityChangeInputRequest();
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
                    "webbroker3.equity.service.delegate.stdimpls.WEB3EquityPTSChangeOrderInputServiceImpl", "execute",
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
        
        assertEquals(WEB3ErrorCatalog.BUSINESS_ERROR_00600, l_changeOrderInputHandler.equityChangeOrderInputRequest(l_request).errorInfo);
        
        log.exiting(STR_METHOD_NAME);
    }
    
    //追加case 20080125
    //equityChangeOrderInputRequest
    //２）　@取消対象注文単位を取得する
    //拡張株式注文マネージャ.getOrderUnits(リクエストデータ.ID)
    //沒有輿 リクエストデータ.ID 想對應的注文単位
    public void testEquityChangeOrderInputRequest_C0006()
    {
        final String STR_METHOD_NAME = "testEquityChangeOrderInputRequest_C0006()";
        log.entering(TEST_START + STR_METHOD_NAME);
        
        WEB3EquityChangeOrderInputHandler l_changeOrderInputHandler =
            new WEB3EquityChangeOrderInputHandler();
        
        WEB3EquityChangeInputRequest l_request = new WEB3EquityChangeInputRequest();
        l_request.id = "12345689";
        try
        {
            TestDBUtility.deleteAll(EqtypeOrderUnitRow.TYPE);
            EqtypeOrderUnitParams l_eqtypeOrderUnitParams = TestDBUtility.getEqtypeOrderUnitRow();
            TestDBUtility.insertWithDel(l_eqtypeOrderUnitParams);
            
            WEB3EquityChangeInputResponse l_response =
                l_changeOrderInputHandler.equityChangeOrderInputRequest(l_request);
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
