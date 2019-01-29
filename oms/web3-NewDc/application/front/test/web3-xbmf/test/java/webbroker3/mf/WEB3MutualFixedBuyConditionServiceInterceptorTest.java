head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.04.07.02.14.59;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	5444d9d078c63f4;
filename	WEB3MutualFixedBuyConditionServiceInterceptorTest.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : (WEB3MutualFixedBuyConditionServiceInterceptorTest.java)
Author Name      : Daiwa Institute of Research
Revision History : 2008/07/17 安陽(中訊) 新規作成
*/
package webbroker3.mf;


import com.fitechlabs.xtrade.kernel.util.ThreadLocalSystemAttributesRegistry;
import com.fitechlabs.xtrade.plugin.tc.gentrade.data.BranchParams;
import com.fitechlabs.xtrade.plugin.tc.gentrade.data.BranchRow;
import com.fitechlabs.xtrade.plugin.tc.gentrade.data.InstitutionParams;
import com.fitechlabs.xtrade.plugin.tc.gentrade.data.InstitutionRow;
import com.fitechlabs.xtrade.plugin.tc.gentrade.data.MainAccountParams;
import com.fitechlabs.xtrade.plugin.tc.gentrade.data.MainAccountRow;

import test.util.TestDBUtility;
import test.util.TestSpecialClassUtility;

import webbroker3.common.WEB3BaseRuntimeException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.gentrade.WEB3GentradeTradingClendarContext;
import webbroker3.gentrade.WEB3GentradeTradingTimeManagement;
import webbroker3.gentrade.data.TradingTimeParams;
import webbroker3.gentrade.data.TradingTimeRow;
import webbroker3.mock.TestBaseForMock;
import webbroker3.util.WEB3LogUtility;

public class WEB3MutualFixedBuyConditionServiceInterceptorTest extends TestBaseForMock
{
    /**
     * ログ出力ユーティリティ。
     */
    private static WEB3LogUtility log =
        WEB3LogUtility.getInstance(WEB3MutualFixedBuyConditionServiceInterceptorTest.class);
    
    WEB3MutualFixedBuyConditionServiceInterceptor l_interceptor = null;

    public WEB3MutualFixedBuyConditionServiceInterceptorTest(String arg0)
    {
        super(arg0);
    }

    protected void setUp() throws Exception
    {
        super.setUp();
        MOCK_MANAGER.setIsMockUsed(true);
        l_interceptor = new WEB3MutualFixedBuyConditionServiceInterceptor();
    }

    protected void tearDown() throws Exception
    {
        l_interceptor = null;
        super.tearDown();
    }

    /*
     * Test method for 'webbroker3.mf.WEB3MutualFixedBuyConditionServiceInterceptor.onCall(Method, Object[])'
     */
    
    //テーブルに該当するデータがありません。
    public void testOnCall_C0001()
    {
        final String STR_METHOD_NAME = "testOnCall_C0001()";
        log.entering(TEST_START + STR_METHOD_NAME);
        
        try
        {
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                    "com.fitechlabs.xtrade.plugin.security.oplogin.impl.OpLoginSecurityServiceImpl",
                    "getAccountId",
                    new Class[] {},
                    new Long(123456789L));
            
            TestDBUtility.deleteAll(MainAccountRow.TYPE);
            MainAccountParams l_mainAccountParams = TestDBUtility.getMainAccountRow();
            l_mainAccountParams.setAccountId(5555555L);
            TestDBUtility.insertWithDel(l_mainAccountParams);
            
            l_interceptor.onCall(null, null);
            fail();
        }
        catch (WEB3BaseRuntimeException l_ex)
        {
            log.debug(STR_METHOD_NAME, l_ex);
            assertEquals(WEB3ErrorCatalog.SYSTEM_ERROR_80005,l_ex.getErrorInfo());
        }
        catch (Exception l_ex)
        {
            log.debug(STR_METHOD_NAME, l_ex);
            fail();
        }
        
        log.exiting(TEST_END + STR_METHOD_NAME);
    }
    
    //DBへのアクセスに失敗しました。
    public void testOnCall_C0002()
    {
        final String STR_METHOD_NAME = "testOnCall_C0002()";
        log.entering(TEST_START + STR_METHOD_NAME);

        try
        {
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                    "com.fitechlabs.xtrade.plugin.security.oplogin.impl.OpLoginSecurityServiceImpl",
                    "getAccountId",
                    new Class[] {},
                    new Long(333812512246L));
            
            TestDBUtility.deleteAll(MainAccountRow.TYPE);
            MainAccountParams l_mainAccountParams = TestDBUtility.getMainAccountRow();
            TestDBUtility.insertWithDel(l_mainAccountParams);
            
            TestDBUtility.deleteAll(InstitutionRow.TYPE);
            InstitutionParams l_institutionParams = TestDBUtility.getInstitutionRow();
            TestDBUtility.insertWithDel(l_institutionParams);
            
            TestDBUtility.deleteAll(BranchRow.TYPE);
            BranchParams l_branchParams = TestDBUtility.getBranchRow();
            TestDBUtility.insertWithDel(l_branchParams);
 
            TestDBUtility.deleteAll(TradingTimeRow.TYPE);
            
            l_interceptor.onCall(null, null);
            fail();
        }
        catch (WEB3BaseRuntimeException l_ex)
        {
            log.debug(STR_METHOD_NAME, l_ex);
            assertEquals(WEB3ErrorCatalog.SYSTEM_ERROR_80005,l_ex.getErrorInfo());
        }
        catch (Exception l_ex)
        {
            log.error(STR_METHOD_NAME, l_ex);
            fail();
        }
    }
    
    //正常終了
    public void testOnCall_C0003()
    {
        final String STR_METHOD_NAME = "testOnCall_C0003()";
        log.entering(TEST_START + STR_METHOD_NAME);

        try
        {
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                    "com.fitechlabs.xtrade.plugin.security.oplogin.impl.OpLoginSecurityServiceImpl",
                    "getAccountId",
                    new Class[] {},
                    new Long(333812512246L));
            
            TestDBUtility.deleteAll(MainAccountRow.TYPE);
            MainAccountParams l_mainAccountParams = TestDBUtility.getMainAccountRow();
            TestDBUtility.insertWithDel(l_mainAccountParams);
            
            TestDBUtility.deleteAll(InstitutionRow.TYPE);
            InstitutionParams l_institutionParams = TestDBUtility.getInstitutionRow();
            TestDBUtility.insertWithDel(l_institutionParams);
            
            TestDBUtility.deleteAll(BranchRow.TYPE);
            BranchParams l_branchParams = TestDBUtility.getBranchRow();
            TestDBUtility.insertWithDel(l_branchParams);
            
            TestDBUtility.deleteAll(TradingTimeRow.TYPE);
            
            TradingTimeParams l_tradingTimeParams = TestDBUtility.getTradingTimeRow();
            l_tradingTimeParams.setInstitutionCode("0D");
            l_tradingTimeParams.setBranchCode("381");
            l_tradingTimeParams.setTradingTimeType("33");
            l_tradingTimeParams.setBizDateType("1");
            l_tradingTimeParams.setMarketCode("0");
            l_tradingTimeParams.setProductCode("0");
            
            TestDBUtility.insertWithDel(l_tradingTimeParams);

            l_interceptor.onCall(null, null);
            
            Object l_object = ThreadLocalSystemAttributesRegistry.getAttribute(
                WEB3GentradeTradingTimeManagement.TRADING_CAL_CONTEXT_PATH);
            
            WEB3GentradeTradingClendarContext l_context = (WEB3GentradeTradingClendarContext)l_object;
            
            assertEquals("0D",l_context.getInstitutionCode());
            assertEquals("381",l_context.getBranchCode());
            assertEquals("0",l_context.getMarketCode());
            assertEquals("0",l_context.getProductCode());
            assertEquals("33",l_context.getTradingTimeType());
            assertEquals("07",l_context.getOrderAcceptProduct());
            assertEquals("12",l_context.getOrderAcceptTransaction());
        }
        catch (Exception l_ex)
        {
            log.error(STR_METHOD_NAME, l_ex);
            fail();
        }
    }

    
    /*
     * Test method for 'webbroker3.mf.WEB3MutualFixedBuyConditionServiceInterceptor.onReturn(Object, Object)'
     */
    public void testOnReturn()
    {
        final String STR_METHOD_NAME = "testOnReturn()";
        log.entering(TEST_START + STR_METHOD_NAME);

        try
        {
            TestSpecialClassUtility.testServiceInterceptor(l_interceptor);
            assertNull(ThreadLocalSystemAttributesRegistry.getAttribute(
                WEB3GentradeTradingTimeManagement.TIMESTAMP_TAG));
            assertNull(ThreadLocalSystemAttributesRegistry.getAttribute(
                WEB3GentradeTradingTimeManagement.OFFSET_TAG));
            assertNull(ThreadLocalSystemAttributesRegistry.getAttribute(
                WEB3GentradeTradingTimeManagement.TRADING_CAL_CONTEXT_PATH));
        }
        catch (Exception l_ex)
        {
            log.error(l_ex.getMessage(), l_ex);
            fail();
        }

        log.exiting(TEST_END + STR_METHOD_NAME);
    }

    
    /*
     * Test method for 'webbroker3.mf.WEB3MutualFixedBuyConditionServiceInterceptor.onThrowable(Object, Throwable)'
     */
    public void testOnThrowable()
    {
        final String STR_METHOD_NAME = "testOnThrowable()";
        log.entering(TEST_START + STR_METHOD_NAME);

        try
        {
            TestSpecialClassUtility.testServiceInterceptor(l_interceptor);
            assertNull(ThreadLocalSystemAttributesRegistry.getAttribute(
                WEB3GentradeTradingTimeManagement.TIMESTAMP_TAG));
            assertNull(ThreadLocalSystemAttributesRegistry.getAttribute(
                WEB3GentradeTradingTimeManagement.OFFSET_TAG));
            assertNull(ThreadLocalSystemAttributesRegistry.getAttribute(
                WEB3GentradeTradingTimeManagement.TRADING_CAL_CONTEXT_PATH));
        }
        catch (Exception l_ex)
        {
            log.error(l_ex.getMessage(), l_ex);
            fail();
        }

        log.exiting(TEST_END + STR_METHOD_NAME);
    }

}
@
