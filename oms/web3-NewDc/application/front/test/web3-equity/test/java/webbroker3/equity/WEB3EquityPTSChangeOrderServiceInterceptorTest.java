head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.04.07.01.10.47;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	5444d9d078c63f4;
filename	WEB3EquityPTSChangeOrderServiceInterceptorTest.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : (PTS)現物株式注文訂正サービスインタセプタTest(WEB3EquityPTSChangeOrderServiceInterceptorTest.java)
Author Name      : Daiwa Institute of Research
Revision History : 2007/12/18 張騰宇 (中訊) 新規作成 モデル1241
*/
package webbroker3.equity;

import java.sql.Timestamp;
import java.util.Calendar;
import java.util.Date;

import com.fitechlabs.xtrade.kernel.util.ThreadLocalSystemAttributesRegistry;
import com.fitechlabs.xtrade.plugin.tc.eqtype.data.EqtypeOrderParams;
import com.fitechlabs.xtrade.plugin.tc.eqtype.data.EqtypeOrderUnitParams;
import com.fitechlabs.xtrade.plugin.tc.gentrade.OrderCategEnum;
import com.fitechlabs.xtrade.plugin.tc.gentrade.OrderOpenStatusEnum;
import com.fitechlabs.xtrade.plugin.tc.gentrade.OrderStatusEnum;
import com.fitechlabs.xtrade.plugin.tc.gentrade.OrderTypeEnum;
import com.fitechlabs.xtrade.plugin.tc.gentrade.ProductTypeEnum;
import com.fitechlabs.xtrade.plugin.tc.gentrade.data.BranchParams;
import com.fitechlabs.xtrade.plugin.tc.gentrade.data.InstitutionParams;
import com.fitechlabs.xtrade.plugin.tc.gentrade.data.MainAccountParams;
import com.fitechlabs.xtrade.plugin.tc.gentrade.data.MarketParams;
import com.fitechlabs.xtrade.plugin.tc.gentrade.data.SubAccountParams;

import test.util.TestDBUtility;
import test.util.TestSpecialClassUtility;

import webbroker3.common.WEB3BaseRuntimeException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.equity.message.WEB3EquityChangeCompleteRequest;
import webbroker3.equity.message.WEB3EquityChangeConfirmRequest;
import webbroker3.equity.message.WEB3EquityChangeInputRequest;
import webbroker3.gentrade.WEB3GentradeTradingClendarContext;
import webbroker3.gentrade.WEB3GentradeTradingTimeManagement;
import webbroker3.gentrade.data.TradingTimeParams;
import webbroker3.mock.TestBaseForMock;
import webbroker3.util.WEB3LogUtility;

public class WEB3EquityPTSChangeOrderServiceInterceptorTest extends TestBaseForMock
{
    /**
     * ログ出力ユーティリティ。
     */
    private static WEB3LogUtility log =
        WEB3LogUtility.getInstance(WEB3EquityPTSChangeOrderServiceInterceptorTest.class);

    WEB3EquityPTSChangeOrderServiceInterceptor l_interceptor = null;

    public WEB3EquityPTSChangeOrderServiceInterceptorTest(String arg0)
    {
        super(arg0);
    }

    protected void setUp() throws Exception
    {
        super.setUp();
        l_interceptor = new WEB3EquityPTSChangeOrderServiceInterceptor();
        Calendar ca =  Calendar.getInstance();
        ca.set(2007,6-1,12);// 注意月?要減1
        Date date = ca.getTime();
        Timestamp st = new Timestamp(date.getTime());
        TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
            "com.fitechlabs.xtrade.plugin.tc." + 
                "gentrade.stdimpls.TradingSystemImpl", 
            "getSystemTimestamp",
            new Class[]{}, 
            st);
        
        ThreadLocalSystemAttributesRegistry.setAttribute(
                "xblocks.gtl.attributes.systemtimestamp",st);
    }

    protected void tearDown() throws Exception
    {
        l_interceptor = null;
        super.tearDown();
    }

    /*
     * Test method for 'webbroker3.equity.WEB3EquityPTSChangeOrderServiceInterceptor.onCall(Method, Object[])'
     */
    //リクエストデータの型が「現物株式注文訂正完了リクエスト」の場合のみ
    //口座をロックする 異常
    //現物株式注文訂正完了リクエスト1
    public void testOnCallCase1()
    {
        final String STR_METHOD_NAME = "testOnCallCase1()";
        log.entering(TEST_START + STR_METHOD_NAME);
        MOCK_MANAGER.setIsMockUsed(true);
        TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
            "com.fitechlabs.xtrade.plugin.security.oplogin.impl.OpLoginSecurityServiceImpl", "getAccountId",
             new Class[] {}, new Long(333812512246L));
        
        try
        {
            TestDBUtility.deleteAll(TradingTimeParams.TYPE);
            TestDBUtility.deleteAll(MainAccountParams.TYPE);
            TestDBUtility.deleteAll(SubAccountParams.TYPE);
            TestDBUtility.deleteAll(EqtypeOrderParams.TYPE);
            TestDBUtility.deleteAll(EqtypeOrderUnitParams.TYPE);
            TestDBUtility.deleteAll(BranchParams.TYPE);
            TestDBUtility.deleteAll(InstitutionParams.TYPE);
            TestDBUtility.deleteAll(MarketParams.TYPE);
            
            EqtypeOrderParams l_eqtypeOrderParams = TestDBUtility.getEqtypeOrderRow();
            l_eqtypeOrderParams.setOrderId(10L);
            TestDBUtility.insertWithDel(l_eqtypeOrderParams);
            
            EqtypeOrderUnitParams l_eqtypeOrderUnitParams = TestDBUtility.getEqtypeOrderUnitRow();
            l_eqtypeOrderUnitParams.setOrderStatus(OrderStatusEnum.NOT_CANCELLED);
            l_eqtypeOrderUnitParams.setOrderOpenStatus(OrderOpenStatusEnum.OPEN);
            l_eqtypeOrderUnitParams.setOrderId(10L);
            l_eqtypeOrderUnitParams.setProductId(3304148080001L);
            l_eqtypeOrderUnitParams.setAccountId(333812512246L);
            l_eqtypeOrderUnitParams.setOrderType(OrderTypeEnum.EQUITY_BUY);
            l_eqtypeOrderUnitParams.setOrderCateg(OrderCategEnum.ASSET);
            l_eqtypeOrderUnitParams.setProductType(ProductTypeEnum.EQUITY);
            l_eqtypeOrderUnitParams.setExecutedQuantity(90);
            TestDBUtility.insertWithDel(l_eqtypeOrderUnitParams);
            
            MarketParams l_marketParams = TestDBUtility.getMarketRow();
            l_marketParams.setMarketCode("0");
            TestDBUtility.insertWithDel(l_marketParams);
            
            MainAccountParams l_mainAccountParams = TestDBUtility.getMainAccountRow();
            l_mainAccountParams.setBranchCode("11");
            TestDBUtility.insertWithDel(l_mainAccountParams);
            
            InstitutionParams l_institutionParams = TestDBUtility.getInstitutionRow();
            TestDBUtility.insertWithDel(l_institutionParams);
            
            BranchParams l_branchParams = TestDBUtility.getBranchRow();
            TestDBUtility.insertWithDel(l_branchParams);
            
            TradingTimeParams l_tradingTimeParams = TestDBUtility.getTradingTimeRow();
            l_tradingTimeParams.setMarketCode("0");
            l_tradingTimeParams.setTradingTimeType("01");
            l_tradingTimeParams.setBizDateType("5");
            TestDBUtility.insertWithDel(l_tradingTimeParams);
            
            WEB3EquityChangeCompleteRequest l_request = new WEB3EquityChangeCompleteRequest();
            l_request.id = "10";
            Object[] l_obj = new Object[1];
            l_obj[0] = l_request;
            l_interceptor.onCall(null, l_obj);
            fail();
        }
        catch (WEB3BaseRuntimeException l_ex)
        {
            log.error(STR_METHOD_NAME, l_ex);
            assertEquals(WEB3ErrorCatalog.SYSTEM_ERROR_80005, l_ex.getErrorInfo());
        }
        catch (Exception l_ex)
        {
            log.error(STR_METHOD_NAME, l_ex);
            log.exiting(TEST_END + STR_METHOD_NAME);
            fail();
        }
        
        log.exiting(TEST_END + STR_METHOD_NAME);
    }
    
    //現物株式注文訂正確認リクエスト2
    public void testOnCallCase2()
    {
        final String STR_METHOD_NAME = "testOnCallCase2()";
        log.entering(TEST_START + STR_METHOD_NAME);
        MOCK_MANAGER.setIsMockUsed(true);
        TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
            "com.fitechlabs.xtrade.plugin.security.oplogin.impl.OpLoginSecurityServiceImpl", "getAccountId",
             new Class[] {}, new Long(333812512246L));
        
        try
        {
            TestDBUtility.deleteAll(TradingTimeParams.TYPE);
            TestDBUtility.deleteAll(MainAccountParams.TYPE);
            TestDBUtility.deleteAll(SubAccountParams.TYPE);
            TestDBUtility.deleteAll(EqtypeOrderParams.TYPE);
            TestDBUtility.deleteAll(EqtypeOrderUnitParams.TYPE);
            TestDBUtility.deleteAll(BranchParams.TYPE);
            TestDBUtility.deleteAll(InstitutionParams.TYPE);
            TestDBUtility.deleteAll(MarketParams.TYPE);
            
            EqtypeOrderParams l_eqtypeOrderParams = TestDBUtility.getEqtypeOrderRow();
            l_eqtypeOrderParams.setOrderId(10L);
            TestDBUtility.insertWithDel(l_eqtypeOrderParams);
            
            EqtypeOrderUnitParams l_eqtypeOrderUnitParams = TestDBUtility.getEqtypeOrderUnitRow();
            l_eqtypeOrderUnitParams.setOrderStatus(OrderStatusEnum.NOT_CANCELLED);
            l_eqtypeOrderUnitParams.setOrderOpenStatus(OrderOpenStatusEnum.OPEN);
            l_eqtypeOrderUnitParams.setOrderId(10L);
            l_eqtypeOrderUnitParams.setProductId(3304148080001L);
            l_eqtypeOrderUnitParams.setAccountId(333812512246L);
            l_eqtypeOrderUnitParams.setOrderType(OrderTypeEnum.EQUITY_BUY);
            l_eqtypeOrderUnitParams.setOrderCateg(OrderCategEnum.ASSET);
            l_eqtypeOrderUnitParams.setProductType(ProductTypeEnum.EQUITY);
            l_eqtypeOrderUnitParams.setExecutedQuantity(90);
            TestDBUtility.insertWithDel(l_eqtypeOrderUnitParams);
            
            MarketParams l_marketParams = TestDBUtility.getMarketRow();
            l_marketParams.setMarketCode("0");
            TestDBUtility.insertWithDel(l_marketParams);
            
            MainAccountParams l_mainAccountParams = TestDBUtility.getMainAccountRow();
            TestDBUtility.insertWithDel(l_mainAccountParams);
            
            InstitutionParams l_institutionParams = TestDBUtility.getInstitutionRow();
            TestDBUtility.insertWithDel(l_institutionParams);
            
            BranchParams l_branchParams = TestDBUtility.getBranchRow();
            TestDBUtility.insertWithDel(l_branchParams);
            
            TradingTimeParams l_tradingTimeParams = TestDBUtility.getTradingTimeRow();
            l_tradingTimeParams.setMarketCode("0");
            l_tradingTimeParams.setTradingTimeType("01");
            l_tradingTimeParams.setBizDateType("1");
            TestDBUtility.insertWithDel(l_tradingTimeParams);
            
            WEB3EquityChangeConfirmRequest l_request = new WEB3EquityChangeConfirmRequest();
            l_request.id = "10";
            Object[] l_obj = new Object[1];
            l_obj[0] = l_request;
            l_interceptor.onCall(null, l_obj);
            WEB3GentradeTradingClendarContext l_context = 
                (WEB3GentradeTradingClendarContext)ThreadLocalSystemAttributesRegistry.getAttribute(
                    WEB3GentradeTradingTimeManagement.TRADING_CAL_CONTEXT_PATH);
            assertEquals("0D", l_context.getInstitutionCode());
            assertEquals("381", l_context.getBranchCode());
            assertEquals("0", l_context.getMarketCode());
            assertEquals("01", l_context.getTradingTimeType());
            assertEquals("0", l_context.getProductCode());
            assertEquals("01", l_context.getOrderAcceptProduct());
            assertEquals("05", l_context.getOrderAcceptTransaction());
            assertEquals("1", l_context.getBizDateType());
        }
        catch (Exception l_ex)
        {
            log.error(STR_METHOD_NAME, l_ex);
            log.exiting(TEST_END + STR_METHOD_NAME);
            fail();
        }
        
        log.exiting(TEST_END + STR_METHOD_NAME);
    }
    
    //パラメータタイプ不正。 現物株式注文訂正入力完了リクエスト3
    public void testOnCallCase3()
    {
        final String STR_METHOD_NAME = "testOnCallCase3()";
        log.entering(TEST_START + STR_METHOD_NAME);
        MOCK_MANAGER.setIsMockUsed(true);
        TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
            "com.fitechlabs.xtrade.plugin.security.oplogin.impl.OpLoginSecurityServiceImpl", "getAccountId",
             new Class[] {}, new Long(333812512246L));
        
        try
        {
            TestDBUtility.deleteAll(TradingTimeParams.TYPE);
            TestDBUtility.deleteAll(MainAccountParams.TYPE);
            TestDBUtility.deleteAll(SubAccountParams.TYPE);
            TestDBUtility.deleteAll(EqtypeOrderParams.TYPE);
            TestDBUtility.deleteAll(EqtypeOrderUnitParams.TYPE);
            TestDBUtility.deleteAll(BranchParams.TYPE);
            TestDBUtility.deleteAll(InstitutionParams.TYPE);
            TestDBUtility.deleteAll(MarketParams.TYPE);
            
            EqtypeOrderParams l_eqtypeOrderParams = TestDBUtility.getEqtypeOrderRow();
            l_eqtypeOrderParams.setOrderId(10L);
            TestDBUtility.insertWithDel(l_eqtypeOrderParams);
            
            EqtypeOrderUnitParams l_eqtypeOrderUnitParams = TestDBUtility.getEqtypeOrderUnitRow();
            l_eqtypeOrderUnitParams.setOrderStatus(OrderStatusEnum.NOT_CANCELLED);
            l_eqtypeOrderUnitParams.setOrderOpenStatus(OrderOpenStatusEnum.OPEN);
            l_eqtypeOrderUnitParams.setOrderId(10L);
            l_eqtypeOrderUnitParams.setProductId(3304148080001L);
            l_eqtypeOrderUnitParams.setAccountId(333812512246L);
            l_eqtypeOrderUnitParams.setOrderType(OrderTypeEnum.EQUITY_BUY);
            l_eqtypeOrderUnitParams.setOrderCateg(OrderCategEnum.ASSET);
            l_eqtypeOrderUnitParams.setProductType(ProductTypeEnum.EQUITY);
            l_eqtypeOrderUnitParams.setExecutedQuantity(90);
            TestDBUtility.insertWithDel(l_eqtypeOrderUnitParams);
            
            MarketParams l_marketParams = TestDBUtility.getMarketRow();
            l_marketParams.setMarketCode("0");
            TestDBUtility.insertWithDel(l_marketParams);
            
            MainAccountParams l_mainAccountParams = TestDBUtility.getMainAccountRow();
            TestDBUtility.insertWithDel(l_mainAccountParams);
            
            InstitutionParams l_institutionParams = TestDBUtility.getInstitutionRow();
            TestDBUtility.insertWithDel(l_institutionParams);
            
            BranchParams l_branchParams = TestDBUtility.getBranchRow();
            TestDBUtility.insertWithDel(l_branchParams);
            
            TradingTimeParams l_tradingTimeParams = TestDBUtility.getTradingTimeRow();
            l_tradingTimeParams.setMarketCode("0");
            l_tradingTimeParams.setTradingTimeType("01");
            l_tradingTimeParams.setBizDateType("5");
            TestDBUtility.insertWithDel(l_tradingTimeParams);
            
            WEB3EquityChangeInputRequest l_request = new WEB3EquityChangeInputRequest();
            l_request.id = "10";
            Object[] l_obj = new Object[1];
            l_obj[0] = l_request;
            l_interceptor.onCall(null, l_obj);
            fail();
        }
        catch (WEB3BaseRuntimeException l_ex)
        {
            log.error(STR_METHOD_NAME, l_ex);
            assertEquals(WEB3ErrorCatalog.SYSTEM_ERROR_80018, l_ex.getErrorInfo());
        }
        catch (Exception l_ex)
        {
            log.error(STR_METHOD_NAME, l_ex);
            log.exiting(TEST_END + STR_METHOD_NAME);
            fail();
        }
        
        log.exiting(TEST_END + STR_METHOD_NAME);
    }
    
    //PTS取引時間管理.setTimestamp()をコールする
    public void testOnCallCase4()
    {
        final String STR_METHOD_NAME = "testOnCallCase4()";
        log.entering(TEST_START + STR_METHOD_NAME);
        MOCK_MANAGER.setIsMockUsed(true);
        TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
            "com.fitechlabs.xtrade.plugin.security.oplogin.impl.OpLoginSecurityServiceImpl", "getAccountId",
             new Class[] {}, new Long(333812512246L));
        
        try
        {
            TestDBUtility.deleteAll(TradingTimeParams.TYPE);
            TestDBUtility.deleteAll(MainAccountParams.TYPE);
            TestDBUtility.deleteAll(SubAccountParams.TYPE);
            TestDBUtility.deleteAll(EqtypeOrderParams.TYPE);
            TestDBUtility.deleteAll(EqtypeOrderUnitParams.TYPE);
            TestDBUtility.deleteAll(BranchParams.TYPE);
            TestDBUtility.deleteAll(InstitutionParams.TYPE);
            TestDBUtility.deleteAll(MarketParams.TYPE);
            
            EqtypeOrderParams l_eqtypeOrderParams = TestDBUtility.getEqtypeOrderRow();
            l_eqtypeOrderParams.setOrderId(10L);
            TestDBUtility.insertWithDel(l_eqtypeOrderParams);
            
            EqtypeOrderUnitParams l_eqtypeOrderUnitParams = TestDBUtility.getEqtypeOrderUnitRow();
            l_eqtypeOrderUnitParams.setOrderStatus(OrderStatusEnum.NOT_CANCELLED);
            l_eqtypeOrderUnitParams.setOrderOpenStatus(OrderOpenStatusEnum.OPEN);
            l_eqtypeOrderUnitParams.setOrderId(10L);
            l_eqtypeOrderUnitParams.setProductId(3304148080001L);
            l_eqtypeOrderUnitParams.setAccountId(333812512246L);
            l_eqtypeOrderUnitParams.setOrderType(OrderTypeEnum.EQUITY_BUY);
            l_eqtypeOrderUnitParams.setOrderCateg(OrderCategEnum.ASSET);
            l_eqtypeOrderUnitParams.setProductType(ProductTypeEnum.EQUITY);
            l_eqtypeOrderUnitParams.setExecutedQuantity(90);
            TestDBUtility.insertWithDel(l_eqtypeOrderUnitParams);
            
            MarketParams l_marketParams = TestDBUtility.getMarketRow();
            l_marketParams.setMarketCode("0");
            TestDBUtility.insertWithDel(l_marketParams);
            
            MainAccountParams l_mainAccountParams = TestDBUtility.getMainAccountRow();
            TestDBUtility.insertWithDel(l_mainAccountParams);
            
            InstitutionParams l_institutionParams = TestDBUtility.getInstitutionRow();
            TestDBUtility.insertWithDel(l_institutionParams);
            
            BranchParams l_branchParams = TestDBUtility.getBranchRow();
            TestDBUtility.insertWithDel(l_branchParams);
            
            TradingTimeParams l_tradingTimeParams = TestDBUtility.getTradingTimeRow();
            l_tradingTimeParams.setMarketCode("0");
            l_tradingTimeParams.setTradingTimeType("01");
            l_tradingTimeParams.setBizDateType("5");
            TestDBUtility.insertWithDel(l_tradingTimeParams);
            
            WEB3EquityChangeCompleteRequest l_request = new WEB3EquityChangeCompleteRequest();
            l_request.id = "10";
            Object[] l_obj = new Object[1];
            l_obj[0] = l_request;
            l_interceptor.onCall(null, l_obj);
            fail();
        }
        catch (WEB3BaseRuntimeException l_ex)
        {
            log.error(STR_METHOD_NAME, l_ex);
            assertEquals(WEB3ErrorCatalog.SYSTEM_ERROR_80005, l_ex.getErrorInfo());
        }
        catch (Exception l_ex)
        {
            log.error(STR_METHOD_NAME, l_ex);
            log.exiting(TEST_END + STR_METHOD_NAME);
            fail();
        }
        
        log.exiting(TEST_END + STR_METHOD_NAME);
    }
    
    //正常賦?
    //現物株式注文訂正完了リクエスト1
    public void testOnCallCase5()
    {
        final String STR_METHOD_NAME = "testOnCallCase5()";
        log.entering(TEST_START + STR_METHOD_NAME);
        MOCK_MANAGER.setIsMockUsed(true);
        TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
            "com.fitechlabs.xtrade.plugin.security.oplogin.impl.OpLoginSecurityServiceImpl", "getAccountId",
             new Class[] {}, new Long(333812512246L));
        
        try
        {
            TestDBUtility.deleteAll(TradingTimeParams.TYPE);
            TestDBUtility.deleteAll(MainAccountParams.TYPE);
            TestDBUtility.deleteAll(SubAccountParams.TYPE);
            TestDBUtility.deleteAll(EqtypeOrderParams.TYPE);
            TestDBUtility.deleteAll(EqtypeOrderUnitParams.TYPE);
            TestDBUtility.deleteAll(BranchParams.TYPE);
            TestDBUtility.deleteAll(InstitutionParams.TYPE);
            TestDBUtility.deleteAll(MarketParams.TYPE);
            
            EqtypeOrderParams l_eqtypeOrderParams = TestDBUtility.getEqtypeOrderRow();
            l_eqtypeOrderParams.setOrderId(10L);
            TestDBUtility.insertWithDel(l_eqtypeOrderParams);
            
            EqtypeOrderUnitParams l_eqtypeOrderUnitParams = TestDBUtility.getEqtypeOrderUnitRow();
            l_eqtypeOrderUnitParams.setOrderStatus(OrderStatusEnum.NOT_CANCELLED);
            l_eqtypeOrderUnitParams.setOrderOpenStatus(OrderOpenStatusEnum.OPEN);
            l_eqtypeOrderUnitParams.setOrderId(10L);
            l_eqtypeOrderUnitParams.setProductId(3304148080001L);
            l_eqtypeOrderUnitParams.setAccountId(333812512246L);
            l_eqtypeOrderUnitParams.setOrderType(OrderTypeEnum.EQUITY_BUY);
            l_eqtypeOrderUnitParams.setOrderCateg(OrderCategEnum.ASSET);
            l_eqtypeOrderUnitParams.setProductType(ProductTypeEnum.EQUITY);
            l_eqtypeOrderUnitParams.setExecutedQuantity(90);
            TestDBUtility.insertWithDel(l_eqtypeOrderUnitParams);
            
            MarketParams l_marketParams = TestDBUtility.getMarketRow();
            l_marketParams.setMarketCode("0");
            TestDBUtility.insertWithDel(l_marketParams);
            
            MainAccountParams l_mainAccountParams = TestDBUtility.getMainAccountRow();
            TestDBUtility.insertWithDel(l_mainAccountParams);
            
            InstitutionParams l_institutionParams = TestDBUtility.getInstitutionRow();
            TestDBUtility.insertWithDel(l_institutionParams);
            
            BranchParams l_branchParams = TestDBUtility.getBranchRow();
            TestDBUtility.insertWithDel(l_branchParams);
            
            TradingTimeParams l_tradingTimeParams = TestDBUtility.getTradingTimeRow();
            l_tradingTimeParams.setMarketCode("0");
            l_tradingTimeParams.setTradingTimeType("01");
            l_tradingTimeParams.setBizDateType("1");
            TestDBUtility.insertWithDel(l_tradingTimeParams);
            
            WEB3EquityChangeCompleteRequest l_request = new WEB3EquityChangeCompleteRequest();
            l_request.id = "10";
            Object[] l_obj = new Object[1];
            l_obj[0] = l_request;
            l_interceptor.onCall(null, l_obj);
            WEB3GentradeTradingClendarContext l_context = 
                (WEB3GentradeTradingClendarContext)ThreadLocalSystemAttributesRegistry.getAttribute(
                    WEB3GentradeTradingTimeManagement.TRADING_CAL_CONTEXT_PATH);
            assertEquals("0D", l_context.getInstitutionCode());
            assertEquals("381", l_context.getBranchCode());
            assertEquals("0", l_context.getMarketCode());
            assertEquals("01", l_context.getTradingTimeType());
            assertEquals("0", l_context.getProductCode());
            assertEquals("01", l_context.getOrderAcceptProduct());
            assertEquals("05", l_context.getOrderAcceptTransaction());
            assertEquals("1", l_context.getBizDateType());
        }
        catch (Exception l_ex)
        {
            log.error(STR_METHOD_NAME, l_ex);
            log.exiting(TEST_END + STR_METHOD_NAME);
            fail();
        }
        
        log.exiting(TEST_END + STR_METHOD_NAME);
    }

    /*
     * Test method for 'webbroker3.equity.WEB3EquityPTSChangeOrderInputServiceInterceptor.onReturn(Object, Object)'
     */
    public void testOnReturn()
    {
        final String STR_METHOD_NAME = "testOnReturn()";
        log.entering(TEST_START + STR_METHOD_NAME);
        
        TestSpecialClassUtility.testServiceInterceptor(l_interceptor);
        
        log.exiting(TEST_END + STR_METHOD_NAME);
    }

    /*
     * Test method for 'webbroker3.equity.WEB3EquityPTSChangeOrderInputServiceInterceptor.onThrowable(Object, Throwable)'
     */
    public void testOnThrowable()
    {
        final String STR_METHOD_NAME = "testOnThrowable()";
        log.entering(TEST_START + STR_METHOD_NAME);
        
        TestSpecialClassUtility.testServiceInterceptor(l_interceptor);
        
        log.exiting(TEST_END + STR_METHOD_NAME);
    }
}
@
