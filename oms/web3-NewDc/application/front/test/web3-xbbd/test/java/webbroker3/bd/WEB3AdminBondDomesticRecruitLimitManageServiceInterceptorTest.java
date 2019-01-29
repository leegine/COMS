head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.04.07.01.02.49;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	5444d9d078c63f4;
filename	WEB3AdminBondDomesticRecruitLimitManageServiceInterceptorTest.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@package webbroker3.bd;

import com.fitechlabs.xtrade.kernel.util.ThreadLocalSystemAttributesRegistry;
import com.fitechlabs.xtrade.plugin.security.oplogin.impl.LoginInfoImplForMock;
import com.fitechlabs.xtrade.plugin.tc.gentrade.data.BranchParams;
import com.fitechlabs.xtrade.plugin.tc.gentrade.data.InstitutionParams;
import com.fitechlabs.xtrade.plugin.tc.gentrade.data.MainAccountParams;

import test.util.TestDBUtility;

import webbroker3.common.WEB3BaseRuntimeException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.gentrade.WEB3Administrator;
import webbroker3.gentrade.WEB3AdministratorForMock;
import webbroker3.gentrade.WEB3GentradeTradingClendarContext;
import webbroker3.gentrade.WEB3GentradeTradingTimeManagement;
import webbroker3.gentrade.data.AdministratorParams;
import webbroker3.gentrade.data.AdministratorRow;
import webbroker3.gentrade.data.TradingTimeParams;
import webbroker3.gentrade.data.TradingTimeRow;
import webbroker3.mock.TestBaseForMock;
import webbroker3.util.WEB3LogUtility;

public class WEB3AdminBondDomesticRecruitLimitManageServiceInterceptorTest extends TestBaseForMock
{
    /**
     * ログ出力ユーティリティ。
     */
    private static WEB3LogUtility log =
        WEB3LogUtility.getInstance(WEB3AdminBondDomesticRecruitLimitManageServiceInterceptorTest.class);
    WEB3AdminBondDomesticRecruitLimitManageServiceInterceptor l_interceptor;

    public WEB3AdminBondDomesticRecruitLimitManageServiceInterceptorTest(String arg0)
    {
        super(arg0);
    }

    protected void setUp() throws Exception
    {
        super.setUp();
        l_interceptor = new WEB3AdminBondDomesticRecruitLimitManageServiceInterceptor();
    }

    protected void tearDown() throws Exception
    {
        super.tearDown();
    }

    public void testOnCall_T01()
    {
        final String STR_METHOD_NAME = "testOnCall_T01()";
        log.entering(STR_METHOD_NAME);
        try
        {
            l_interceptor.onCall(null, null);
            fail();
        }
        catch(WEB3BaseRuntimeException l_ex)
        {
            log.exiting(STR_METHOD_NAME);
            assertEquals(WEB3ErrorCatalog.SYSTEM_ERROR_80017, l_ex.getErrorInfo());
        }
        catch(Exception l_ex)
        {
            log.exiting(STR_METHOD_NAME);
            fail();
        }
        log.exiting(STR_METHOD_NAME);
        
    }
    
    public void testOnCall_T02()
    {
        final String STR_METHOD_NAME = "testOnCall_T02()";
        log.entering(STR_METHOD_NAME);
        try
        {
            l_interceptor.onCall(null, new Object[0]);
            fail();
        }
        catch(WEB3BaseRuntimeException l_ex)
        {
            log.exiting(STR_METHOD_NAME);
            assertEquals(WEB3ErrorCatalog.SYSTEM_ERROR_80017, l_ex.getErrorInfo());
        }
        catch(Exception l_ex)
        {
            log.exiting(STR_METHOD_NAME);
            fail();
        }
        log.exiting(STR_METHOD_NAME);
        
    }
    
    public void testOnCall_T03()
    {
        final String STR_METHOD_NAME = "testOnCall_T03()";
        log.entering(STR_METHOD_NAME);
        try
        {
            //AdministratorRow
            TestDBUtility.deleteAll(AdministratorRow.TYPE);
            
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                    "com.fitechlabs.xtrade.plugin.security.oplogin.impl.OpLoginSecurityServiceImpl", 
                    "getLoginInfo",
                    new Class[] {},
                    new LoginInfoImplForMock());
                    
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                "com.fitechlabs.xtrade.plugin.security.oplogin.impl.LoginInfoImpl",
                "getLoginId",
                new Class[] {},
                new Long(123l));
            TestBaseForMock.MOCK_MANAGER.setIsMockUsed(true);

            l_interceptor.onCall(null, new Object[]{"dsfdsf"});
            fail();
        }
        catch(WEB3BaseRuntimeException l_exc)
        {
            log.exiting(STR_METHOD_NAME);
            assertEquals(WEB3ErrorCatalog.SYSTEM_ERROR_80003, l_exc.getErrorInfo());
        }
        catch(Exception l_ex)
        {
            log.exiting(STR_METHOD_NAME);
            fail();
        }
        log.exiting(STR_METHOD_NAME);
        
    }
    
    
    public void testOnCall_T04()
    {
        final String STR_METHOD_NAME = "testOnCall_T04()";
        log.entering(STR_METHOD_NAME);
        try
        {
            //AdministratorParams
            TestDBUtility.deleteAll(AdministratorParams.TYPE);
            AdministratorParams l_administratorParams =
                TestDBUtility.getAdministratorRow();
            l_administratorParams.setInstitutionCode("0D");
            l_administratorParams.setBranchCode("381");
            TestDBUtility.insertWithDel(l_administratorParams);

            WEB3Administrator l_administrator =
                new WEB3Administrator(l_administratorParams);
            WEB3AdministratorForMock.mockGetInstanceFromLoginInfo(l_administrator);

            //TradingTimeRow
            TestDBUtility.deleteAll(TradingTimeRow.TYPE);

            l_interceptor.onCall(null, new Object[]{"dsfdsf"});

        }
        catch(WEB3BaseRuntimeException l_exc)
        {
            log.exiting(STR_METHOD_NAME);
            assertEquals(WEB3ErrorCatalog.SYSTEM_ERROR_80005, l_exc.getErrorInfo());
        }
        catch(Exception l_ex)
        {
            log.exiting(STR_METHOD_NAME);
            fail();
        }
        log.exiting(STR_METHOD_NAME);
        
    }
    
    public void testOnCall_T05()
    {
        final String STR_METHOD_NAME = "testOnCall_T05()";
        log.entering(STR_METHOD_NAME);
        try
        {
            //AdministratorParams
            TestDBUtility.deleteAll(AdministratorParams.TYPE);
            AdministratorParams l_administratorParams =
                TestDBUtility.getAdministratorRow();
            l_administratorParams.setInstitutionCode("0D");
            l_administratorParams.setBranchCode("381");
            TestDBUtility.insertWithDel(l_administratorParams);

            WEB3Administrator l_administrator =
                new WEB3Administrator(l_administratorParams);
            WEB3AdministratorForMock.mockGetInstanceFromLoginInfo(l_administrator);

            //TradingTimeParams
            TestDBUtility.deleteAll(TradingTimeParams.TYPE);
            TradingTimeParams l_tradingTimeParams =
                TestDBUtility.getTradingTimeRow();
            l_tradingTimeParams.setInstitutionCode(l_administratorParams.getInstitutionCode());
            l_tradingTimeParams.setMarketCode("0");
            l_tradingTimeParams.setProductCode("0");
            l_tradingTimeParams.setTradingTimeType("36");
            l_tradingTimeParams.setBizDateType("1");
            l_tradingTimeParams.setStartTime("000000");
            l_tradingTimeParams.setEndTime("240000");
            TestDBUtility.insertWithDel(l_tradingTimeParams);

            l_interceptor.onCall(null, new Object[]{"dsfdsf"});

            WEB3GentradeTradingClendarContext l_clendarContext =
                (WEB3GentradeTradingClendarContext)
                    ThreadLocalSystemAttributesRegistry.getAttribute(
                        WEB3GentradeTradingTimeManagement.TRADING_CAL_CONTEXT_PATH);

            assertEquals(l_administratorParams.getInstitutionCode(), l_clendarContext.getInstitutionCode());
            assertEquals(l_administratorParams.getBranchCode(), l_clendarContext.getBranchCode());
            assertEquals("0", l_clendarContext.getMarketCode());
            assertEquals("0", l_clendarContext.getProductCode());
            assertEquals("36", l_clendarContext.getTradingTimeType());
            assertEquals("28", l_clendarContext.getOrderAcceptProduct());
            assertEquals("00", l_clendarContext.getOrderAcceptTransaction());

        }
        catch(Exception l_ex)
        {
            log.exiting(STR_METHOD_NAME);
            fail();
        }
        log.exiting(STR_METHOD_NAME);
        
    }

    public void testOnReturn()
    {
        final String STR_METHOD_NAME = "testOnReturn()";
        log.entering(STR_METHOD_NAME);
        try
        {
            ThreadLocalSystemAttributesRegistry.setAttribute(
                    WEB3GentradeTradingTimeManagement.TIMESTAMP_TAG,
                    "123");

                ThreadLocalSystemAttributesRegistry.setAttribute(
                    WEB3GentradeTradingTimeManagement.OFFSET_TAG,
                    "456");

                ThreadLocalSystemAttributesRegistry.setAttribute(
                    WEB3GentradeTradingTimeManagement.TRADING_CAL_CONTEXT_PATH,
                    "789");
                
                l_interceptor.onReturn(null, null);
                
                Object l_result1 =
                    ThreadLocalSystemAttributesRegistry.getAttribute(
                        WEB3GentradeTradingTimeManagement.TIMESTAMP_TAG);
                
                Object l_result2 =
                    ThreadLocalSystemAttributesRegistry.getAttribute(
                        WEB3GentradeTradingTimeManagement.OFFSET_TAG);
                
                Object l_result3 =
                    ThreadLocalSystemAttributesRegistry.getAttribute(
                        WEB3GentradeTradingTimeManagement.TRADING_CAL_CONTEXT_PATH);
                assertNull(l_result1);
                assertNull(l_result2);
                assertNull(l_result3);
        }
        catch(Exception l_ex)
        {
            log.exiting(STR_METHOD_NAME);
            fail();
        }
        log.exiting(STR_METHOD_NAME);
        
    }

    public void testOnThrowable()
    {
        final String STR_METHOD_NAME = "testOnReturn()";
        log.entering(STR_METHOD_NAME);
        try
        {
            ThreadLocalSystemAttributesRegistry.setAttribute(
                WEB3GentradeTradingTimeManagement.TIMESTAMP_TAG,
                "123");

            ThreadLocalSystemAttributesRegistry.setAttribute(
                WEB3GentradeTradingTimeManagement.OFFSET_TAG,
                "456");

            ThreadLocalSystemAttributesRegistry.setAttribute(
                WEB3GentradeTradingTimeManagement.TRADING_CAL_CONTEXT_PATH,
                "789");

            l_interceptor.onThrowable(null, null);
            
            Object l_result1 =
                ThreadLocalSystemAttributesRegistry.getAttribute(
                    WEB3GentradeTradingTimeManagement.TIMESTAMP_TAG);
            
            Object l_result2 =
                ThreadLocalSystemAttributesRegistry.getAttribute(
                    WEB3GentradeTradingTimeManagement.OFFSET_TAG);
            
            Object l_result3 =
                ThreadLocalSystemAttributesRegistry.getAttribute(
                    WEB3GentradeTradingTimeManagement.TRADING_CAL_CONTEXT_PATH);
            assertNull(l_result1);
            assertNull(l_result2);
            assertNull(l_result3);
            
        }
        catch(Exception l_exc)
        {
            log.exiting(STR_METHOD_NAME);
            fail();
        }
        log.exiting(STR_METHOD_NAME);
    }
}
@
