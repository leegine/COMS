head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.04.07.01.33.59;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	5444d9d078c63f4;
filename	WEB3FXTransFromFXInputServiceInterceptorTest.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@package webbroker3.aio;

import java.lang.reflect.Method;

import com.fitechlabs.xtrade.kernel.boot.Services;
import com.fitechlabs.xtrade.kernel.util.ThreadLocalSystemAttributesRegistry;
import com.fitechlabs.xtrade.plugin.security.oplogin.OpLoginSecurityService;
import com.fitechlabs.xtrade.plugin.security.oplogin.impl.OpLoginSecurityServiceImplForMock;
import com.fitechlabs.xtrade.plugin.tc.gentrade.data.BranchParams;
import com.fitechlabs.xtrade.plugin.tc.gentrade.data.InstitutionParams;
import com.fitechlabs.xtrade.plugin.tc.gentrade.data.MainAccountParams;

import test.util.TestDBUtility;

import webbroker3.aio.data.CompFxConditionParams;
import webbroker3.aio.message.WEB3FXTransFromFXInputRequest;
import webbroker3.aio.message.WEB3FXTransToFXInputRequest;
import webbroker3.common.WEB3BaseRuntimeException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.gentrade.WEB3GentradeTradingClendarContext;
import webbroker3.gentrade.WEB3GentradeTradingTimeManagement;
import webbroker3.gentrade.WEB3GentradeTradingTimeManagementForMock;
import webbroker3.gentrade.data.TradingTimeParams;
import webbroker3.mock.TestBaseForMock;
import webbroker3.util.WEB3LogUtility;

public class WEB3FXTransFromFXInputServiceInterceptorTest extends
		TestBaseForMock {

	private static WEB3LogUtility log = 
		WEB3LogUtility.getInstance(WEB3FXTransFromFXInputServiceInterceptorTest.class);
	
	public WEB3FXTransFromFXInputServiceInterceptorTest(String arg0) {
		super(arg0);
	}

	protected void setUp() throws Exception {
		super.setUp();
          MOCK_MANAGER.setIsMockUsed(true);
            Services.overrideService(OpLoginSecurityService.class,
                new OpLoginSecurityServiceImplForMock());
	}

	protected void tearDown() throws Exception {
		super.tearDown();
	}

    WEB3FXTransFromFXInputServiceInterceptor l_interceptor = 
        new WEB3FXTransFromFXInputServiceInterceptor();
	/*
	 * Test method for 'webbroker3.aio.WEB3FXTransFromFXInputServiceInterceptor.onCall(Method, Object[])'
	 */
	public void testOnCall_T01()
	{
		final String STR_METHOD_NAME = "testOnCall_T01";
		log.entering(STR_METHOD_NAME);

		Method l_method = null;
		Object[] l_serviceParam = new Object[]{};

		try
		{
			l_interceptor.onCall(l_method,l_serviceParam);
		}
		catch (WEB3BaseRuntimeException l_ex)
        {
            log.debug(l_ex.getMessage(), l_ex);
            assertEquals(WEB3ErrorCatalog.SYSTEM_ERROR_80017, l_ex.getErrorInfo());
        }
        catch (Exception l_ex)
        {
            log.debug(l_ex.getMessage(), l_ex);
            fail();
        }

        log.exiting(STR_METHOD_NAME);
		
	}
    
    public void testOnCall_T02()
    {
        final String STR_METHOD_NAME = "testOnCall_T02";
        log.entering(STR_METHOD_NAME);


        Method l_method = null;
        
        WEB3FXTransFromFXInputRequest l_request = new WEB3FXTransFromFXInputRequest();
        l_request.fxSystemCode = "1";
        
        Object[] l_serviceParam = new Object[]{l_request};

        try
        {
            
            TestDBUtility.deleteAll(MainAccountParams.TYPE);
            MainAccountParams l_mainAccountParams = TestDBUtility.getMainAccountRow();
            l_mainAccountParams.setAccountId(123);
            TestDBUtility.insertWithDel(l_mainAccountParams);
            

            InstitutionParams l_institutionParams = TestDBUtility.getInstitutionRow();
            l_institutionParams.setInstitutionId(33L);
            TestDBUtility.deleteAll(l_institutionParams.getRowType());
            TestDBUtility.insertWithDel(l_institutionParams);

            BranchParams l_breanch = TestDBUtility.getBranchRow();
            TestDBUtility.deleteAll(BranchParams.TYPE);
            TestDBUtility.insertWithDel(l_breanch);
            
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                "com.fitechlabs.xtrade.plugin.security.oplogin.impl.OpLoginSecurityServiceImpl",
                "getAccountId",
                new Class[] {},
                new Long(123));
            
            TestDBUtility.deleteAll(CompFxConditionParams.TYPE);
            
            l_interceptor.onCall(l_method,l_serviceParam);
            fail();
            
            
        }
        catch (WEB3BaseRuntimeException l_ex)
        {
            log.debug(l_ex.getMessage(), l_ex);
            assertEquals(WEB3ErrorCatalog.SYSTEM_ERROR_80005, l_ex.getErrorInfo());
        }
        catch (Exception l_ex)
        {
            log.debug(l_ex.getMessage(), l_ex);
            fail();
        }

        log.exiting(STR_METHOD_NAME);
        
    }

    public void testOnCall_T03()
    {
        final String STR_METHOD_NAME = "testOnCall_T03";
        log.entering(STR_METHOD_NAME);

        Method l_method = null;
        
        WEB3FXTransFromFXInputRequest l_request = new WEB3FXTransFromFXInputRequest();
        l_request.fxSystemCode = "1";
        
        Object[] l_serviceParam = new Object[]{l_request};

        try
        {
            WEB3GentradeTradingTimeManagementForMock.mockSetTimestamp();

            TestDBUtility.deleteAll(MainAccountParams.TYPE);
            MainAccountParams l_mainAccountParams = TestDBUtility.getMainAccountRow();
            l_mainAccountParams.setAccountId(123);
            TestDBUtility.insertWithDel(l_mainAccountParams);
            
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                    "com.fitechlabs.xtrade.plugin.security.oplogin.impl.OpLoginSecurityServiceImpl",
                    "getAccountId",
                    new Class[] {},
                    new Long(123));
            
            TestDBUtility.deleteAll(TradingTimeParams.TYPE);
            TradingTimeParams l_tradingTimeParams = TestDBUtility.getTradingTimeRow();
            l_tradingTimeParams.setInstitutionCode("0D");
            l_tradingTimeParams.setBranchCode("381");
            l_tradingTimeParams.setTradingTimeType("01");
            l_tradingTimeParams.setBizDateType("1");
            l_tradingTimeParams.setMarketCode("0");
            l_tradingTimeParams.setProductCode("03");
            TestDBUtility.insertWithDel(l_tradingTimeParams);
            
            InstitutionParams l_institutionParams = TestDBUtility.getInstitutionRow();
            l_institutionParams.setInstitutionId(33L);
            TestDBUtility.deleteAll(l_institutionParams.getRowType());
            TestDBUtility.insertWithDel(l_institutionParams);

            BranchParams l_breanch = TestDBUtility.getBranchRow();
            TestDBUtility.deleteAll(BranchParams.TYPE);
            TestDBUtility.insertWithDel(l_breanch);
            
            TestDBUtility.deleteAll(CompFxConditionParams.TYPE);
            CompFxConditionParams l_compFxConditionParams = TestDBUtility.getCompFxConditionRow();
            l_compFxConditionParams.setInstitutionCode(l_mainAccountParams.getInstitutionCode());
            l_compFxConditionParams.setBranchCode(l_mainAccountParams.getBranchCode());
            l_compFxConditionParams.setFxSystemCode(l_request.fxSystemCode);
            l_compFxConditionParams.setFxSystemDiv("2");
            TestDBUtility.insertWithDel(l_compFxConditionParams);
 
            l_interceptor.onCall(l_method,l_serviceParam);

            WEB3GentradeTradingClendarContext l_context =
                (WEB3GentradeTradingClendarContext)ThreadLocalSystemAttributesRegistry.getAttribute(
                    WEB3GentradeTradingTimeManagement.TRADING_CAL_CONTEXT_PATH);

            assertEquals("0D", l_context.getInstitutionCode());
            assertEquals("381", l_context.getBranchCode());
            assertEquals("0", l_context.getMarketCode());
            assertEquals("03", l_context.getProductCode());
            assertEquals("09", l_context.getOrderAcceptTransaction());
            assertEquals(l_compFxConditionParams.getTradingTimeType(), l_context.getTradingTimeType());
            assertEquals(l_compFxConditionParams.getTradingTimeType(), l_context.getOrderAcceptProduct());
        }
        catch (Exception l_ex)
        {
            log.debug(l_ex.getMessage(), l_ex);
            fail();
        }
        log.exiting(STR_METHOD_NAME);
    }
    
    public void testOnCall_T04()
    {
        final String STR_METHOD_NAME = "testOnCall_T04";
        log.entering(STR_METHOD_NAME);

        Method l_method = null;
        
        WEB3FXTransFromFXInputRequest l_request = new WEB3FXTransFromFXInputRequest();
        l_request.fxSystemCode = "1";
        
        Object[] l_serviceParam = new Object[]{l_request};

        try
        {
            WEB3GentradeTradingTimeManagementForMock.mockSetTimestamp();

            TestDBUtility.deleteAll(MainAccountParams.TYPE);
            MainAccountParams l_mainAccountParams = TestDBUtility.getMainAccountRow();
            l_mainAccountParams.setAccountId(123);
            TestDBUtility.insertWithDel(l_mainAccountParams);
            
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                    "com.fitechlabs.xtrade.plugin.security.oplogin.impl.OpLoginSecurityServiceImpl",
                    "getAccountId",
                    new Class[] {},
                    new Long(123));
            
            TestDBUtility.deleteAll(TradingTimeParams.TYPE);
            TradingTimeParams l_tradingTimeParams = TestDBUtility.getTradingTimeRow();
            l_tradingTimeParams.setInstitutionCode("0D");
            l_tradingTimeParams.setBranchCode("381");
            l_tradingTimeParams.setTradingTimeType("01");
            l_tradingTimeParams.setBizDateType("1");
            l_tradingTimeParams.setMarketCode("0");
            l_tradingTimeParams.setProductCode("03");
            TestDBUtility.insertWithDel(l_tradingTimeParams);
            
            InstitutionParams l_institutionParams = TestDBUtility.getInstitutionRow();
            l_institutionParams.setInstitutionId(33L);
            TestDBUtility.deleteAll(l_institutionParams.getRowType());
            TestDBUtility.insertWithDel(l_institutionParams);

            BranchParams l_breanch = TestDBUtility.getBranchRow();
            TestDBUtility.deleteAll(BranchParams.TYPE);
            TestDBUtility.insertWithDel(l_breanch);
            
            TestDBUtility.deleteAll(CompFxConditionParams.TYPE);
            CompFxConditionParams l_compFxConditionParams = TestDBUtility.getCompFxConditionRow();
            l_compFxConditionParams.setInstitutionCode(l_mainAccountParams.getInstitutionCode());
            l_compFxConditionParams.setBranchCode(l_mainAccountParams.getBranchCode());
            l_compFxConditionParams.setFxSystemCode(l_request.fxSystemCode);
            l_compFxConditionParams.setFxSystemDiv("1");
            TestDBUtility.insertWithDel(l_compFxConditionParams);
 
            l_interceptor.onCall(l_method,l_serviceParam);

            WEB3GentradeTradingClendarContext l_context =
                (WEB3GentradeTradingClendarContext)ThreadLocalSystemAttributesRegistry.getAttribute(
                    WEB3GentradeTradingTimeManagement.TRADING_CAL_CONTEXT_PATH);

            assertEquals("0D", l_context.getInstitutionCode());
            assertEquals("381", l_context.getBranchCode());
            assertEquals("0", l_context.getMarketCode());
            assertEquals("03", l_context.getProductCode());
            assertEquals("09", l_context.getOrderAcceptTransaction());
            assertEquals(l_compFxConditionParams.getTradingTimeType(), l_context.getTradingTimeType());
            assertEquals(l_compFxConditionParams.getTradingTimeType(), l_context.getOrderAcceptProduct());
        }
        catch (Exception l_ex)
        {
            log.debug(l_ex.getMessage(), l_ex);
            fail();
        }
        log.exiting(STR_METHOD_NAME);
    }
    
    public void testOnCall_T05()
    {
        final String STR_METHOD_NAME = "testOnCall_T05";
        log.entering(STR_METHOD_NAME);

        Method l_method = null;
        
        WEB3FXTransToFXInputRequest l_request = new WEB3FXTransToFXInputRequest();
        l_request.fxSystemCode = "1";
        
        Object[] l_serviceParam = new Object[]{l_request};

        try
        {
            
            TestDBUtility.deleteAll(MainAccountParams.TYPE);
            MainAccountParams l_mainAccountParams = TestDBUtility.getMainAccountRow();
            l_mainAccountParams.setAccountId(123);
            TestDBUtility.insertWithDel(l_mainAccountParams);
            

            InstitutionParams l_institutionParams = TestDBUtility.getInstitutionRow();
            l_institutionParams.setInstitutionId(33L);
            TestDBUtility.deleteAll(l_institutionParams.getRowType());
            TestDBUtility.insertWithDel(l_institutionParams);

            BranchParams l_breanch = TestDBUtility.getBranchRow();
            TestDBUtility.deleteAll(BranchParams.TYPE);
            TestDBUtility.insertWithDel(l_breanch);
            
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                "com.fitechlabs.xtrade.plugin.security.oplogin.impl.OpLoginSecurityServiceImpl",
                "getAccountId",
                new Class[] {},
                new Long(123));
            
            TestDBUtility.deleteAll(CompFxConditionParams.TYPE);
            
            l_interceptor.onCall(l_method,l_serviceParam);
            fail();
            
            
        }
        catch (WEB3BaseRuntimeException l_ex)
        {
            log.debug(l_ex.getMessage(), l_ex);
            assertEquals(WEB3ErrorCatalog.SYSTEM_ERROR_80005, l_ex.getErrorInfo());
        }
        catch (Exception l_ex)
        {
            log.debug(l_ex.getMessage(), l_ex);
            fail();
        }

        log.exiting(STR_METHOD_NAME);
        
    }

    public void testOnCall_T06()
    {
        final String STR_METHOD_NAME = "testOnCall_T06";
        log.entering(STR_METHOD_NAME);

        Method l_method = null;
        
        WEB3FXTransFromFXInputRequest l_request = new WEB3FXTransFromFXInputRequest();
        l_request.fxSystemCode = null;
        
        Object[] l_serviceParam = new Object[]{l_request};

        try
        {
            
            TestDBUtility.deleteAll(MainAccountParams.TYPE);
            MainAccountParams l_mainAccountParams = TestDBUtility.getMainAccountRow();
            l_mainAccountParams.setAccountId(123);
            TestDBUtility.insertWithDel(l_mainAccountParams);
            
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                    "com.fitechlabs.xtrade.plugin.security.oplogin.impl.OpLoginSecurityServiceImpl",
                    "getAccountId",
                    new Class[] {},
                    new Long(123));
            
            TestDBUtility.deleteAll(TradingTimeParams.TYPE);
            TradingTimeParams l_tradingTimeParams = TestDBUtility.getTradingTimeRow();
            l_tradingTimeParams.setInstitutionCode("0D");
            l_tradingTimeParams.setBranchCode("381");
            l_tradingTimeParams.setTradingTimeType("23");
            l_tradingTimeParams.setBizDateType("1");
            l_tradingTimeParams.setMarketCode("0");
            l_tradingTimeParams.setProductCode("03");
            TestDBUtility.insertWithDel(l_tradingTimeParams);
            
            InstitutionParams l_institutionParams = TestDBUtility.getInstitutionRow();
            l_institutionParams.setInstitutionId(33L);
            TestDBUtility.deleteAll(l_institutionParams.getRowType());
            TestDBUtility.insertWithDel(l_institutionParams);

            BranchParams l_breanch = TestDBUtility.getBranchRow();
            TestDBUtility.deleteAll(BranchParams.TYPE);
            TestDBUtility.insertWithDel(l_breanch);
            
            TestDBUtility.deleteAll(CompFxConditionParams.TYPE);
            CompFxConditionParams l_compFxConditionParams = TestDBUtility.getCompFxConditionRow();
            l_compFxConditionParams.setInstitutionCode(l_mainAccountParams.getInstitutionCode());
            l_compFxConditionParams.setBranchCode(l_mainAccountParams.getBranchCode());
            l_compFxConditionParams.setFxSystemCode("11");
            l_compFxConditionParams.setFxSystemDiv("1");
            TestDBUtility.insertWithDel(l_compFxConditionParams);
            l_compFxConditionParams = TestDBUtility.getCompFxConditionRow();
            l_compFxConditionParams.setInstitutionCode(l_mainAccountParams.getInstitutionCode());
            l_compFxConditionParams.setBranchCode(l_mainAccountParams.getBranchCode());
            l_compFxConditionParams.setFxSystemCode("12");
            l_compFxConditionParams.setFxSystemDiv("1");
            TestDBUtility.insertWithDel(l_compFxConditionParams);
 
            l_interceptor.onCall(l_method,l_serviceParam);
            fail();
            
        }
        catch (WEB3BaseRuntimeException l_ex)
        {
            assertEquals(WEB3ErrorCatalog.SYSTEM_ERROR_80006, l_ex.getErrorInfo());
        }
        catch (Exception l_ex)
        {
            log.debug(l_ex.getMessage(), l_ex);
            fail();
        }
        log.exiting(STR_METHOD_NAME);
    }
}
@
