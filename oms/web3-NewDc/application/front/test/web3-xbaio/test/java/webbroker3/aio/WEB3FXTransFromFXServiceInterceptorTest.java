head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.04.07.01.33.03;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	5444d9d078c63f4;
filename	WEB3FXTransFromFXServiceInterceptorTest.java;


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
import webbroker3.aio.message.WEB3FXTransFromFXAskingRequest;
import webbroker3.aio.message.WEB3FXTransFromFXCompleteRequest;
import webbroker3.aio.message.WEB3FXTransFromFXCompleteSoapRequest;
import webbroker3.aio.message.WEB3FXTransFromFXConfirmRequest;
import webbroker3.aio.message.WEB3FXTransToFXConfirmRequest;
import webbroker3.common.WEB3BaseRuntimeException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.gentrade.WEB3GentradeTradingClendarContext;
import webbroker3.gentrade.WEB3GentradeTradingTimeManagement;
import webbroker3.gentrade.data.TradingTimeParams;
import webbroker3.mock.TestBaseForMock;
import webbroker3.util.WEB3LogUtility;

public class WEB3FXTransFromFXServiceInterceptorTest extends TestBaseForMock {

    private static WEB3LogUtility log = 
        WEB3LogUtility.getInstance(WEB3FXTransFromFXServiceInterceptorTest.class);
    
    
    public WEB3FXTransFromFXServiceInterceptorTest(String arg0) {
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

    WEB3FXTransFromFXServiceInterceptor l_interceptor =  new WEB3FXTransFromFXServiceInterceptor();
    
    /*
     * Test method for 'webbroker3.aio.WEB3FXTransFromFXServiceInterceptor.onCall(Method, Object[])'
     */
    public void testOnCall_T01() 
    {
        final String STR_METHOD_NAME = "testOnCall_T01";
        log.entering(STR_METHOD_NAME);

        
        Method l_method = null;
        Object[] l_serviceParam = new Object[]{};
        
        try
        {
            l_interceptor.onCall(l_method, l_serviceParam);            
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
        WEB3FXTransFromFXConfirmRequest l_request = new WEB3FXTransFromFXConfirmRequest();
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
        WEB3FXTransFromFXCompleteSoapRequest l_request = new WEB3FXTransFromFXCompleteSoapRequest();
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
    
    public void testOnCall_T04()
    {
        final String STR_METHOD_NAME = "testOnCall_T04";
        log.entering(STR_METHOD_NAME);

       
        Method l_method = null;
        WEB3FXTransFromFXAskingRequest l_request = new WEB3FXTransFromFXAskingRequest();
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
    
    public void testOnCall_T05()
    {
        final String STR_METHOD_NAME = "testOnCall_T05";
        log.entering(STR_METHOD_NAME);

        Method l_method = null;
        WEB3FXTransFromFXCompleteRequest l_request = new WEB3FXTransFromFXCompleteRequest();
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
        
        WEB3FXTransFromFXConfirmRequest l_request = new WEB3FXTransFromFXConfirmRequest();
        l_request.fxSystemCode = "1";
        
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
            l_tradingTimeParams.setTradingTimeType("40");
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
            l_compFxConditionParams.setTradingTimeType("40");
            TestDBUtility.insertWithDel(l_compFxConditionParams);
 
            l_interceptor.onCall(l_method,l_serviceParam);
            WEB3GentradeTradingClendarContext l_context =
                (WEB3GentradeTradingClendarContext)ThreadLocalSystemAttributesRegistry.getAttribute(
                    WEB3GentradeTradingTimeManagement.TRADING_CAL_CONTEXT_PATH);
            assertEquals(l_context.getTradingTimeType(),"40");
            assertEquals(l_context.getOrderAcceptProduct(),"40");
            
        }
        catch (WEB3BaseRuntimeException l_ex)
        {
            log.debug(l_ex.getMessage(), l_ex);
            fail();
        }
        catch (Exception l_ex)
        {
            log.debug(l_ex.getMessage(), l_ex);
            fail();
        }
        log.exiting(STR_METHOD_NAME);
    }
    

    public void testOnCall_T07()
    {
        final String STR_METHOD_NAME = "testOnCall_T07";
        log.entering(STR_METHOD_NAME);

        Method l_method = null;
        
        WEB3FXTransFromFXConfirmRequest l_request = new WEB3FXTransFromFXConfirmRequest();
        l_request.fxSystemCode = "1";
        
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
            l_tradingTimeParams.setTradingTimeType("39");
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
            l_compFxConditionParams.setTradingTimeType("39");
            TestDBUtility.insertWithDel(l_compFxConditionParams);
 
            l_interceptor.onCall(l_method,l_serviceParam);
            WEB3GentradeTradingClendarContext l_context =
                (WEB3GentradeTradingClendarContext)ThreadLocalSystemAttributesRegistry.getAttribute(
                    WEB3GentradeTradingTimeManagement.TRADING_CAL_CONTEXT_PATH);
            assertEquals(l_context.getTradingTimeType(),"39");
            assertEquals(l_context.getOrderAcceptProduct(),"39");
            
        }
        catch (WEB3BaseRuntimeException l_ex)
        {
            log.debug(l_ex.getMessage(), l_ex);
            fail();
        }
        catch (Exception l_ex)
        {
            log.debug(l_ex.getMessage(), l_ex);
            fail();
        }
        log.exiting(STR_METHOD_NAME);
    }
    
    public void testOnCall_T08()
    {
        final String STR_METHOD_NAME = "testOnCall_T08";
        log.entering(STR_METHOD_NAME);

       
        Method l_method = null;
        WEB3FXTransToFXConfirmRequest l_request = new WEB3FXTransToFXConfirmRequest();
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

    public void testOnCall_T09()
    {
        final String STR_METHOD_NAME = "testOnCall_T09";
        log.entering(STR_METHOD_NAME);

        Method l_method = null;
        
        WEB3FXTransFromFXConfirmRequest l_request = new WEB3FXTransFromFXConfirmRequest();
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
