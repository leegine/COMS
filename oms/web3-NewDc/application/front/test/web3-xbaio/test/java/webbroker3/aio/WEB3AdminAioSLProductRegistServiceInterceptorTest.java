head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.04.07.01.33.13;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	5444d9d078c63f4;
filename	WEB3AdminAioSLProductRegistServiceInterceptorTest.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : WEB3AdminAioSLProductRegistServiceInterceptor(WEB3AdminAioSLProductRegistServiceInterceptorTest.java)
Author Name      : Daiwa Institute of Research
Revision History : 2007/09/27 趙林鵬(中訊) 新規作成
*/

package webbroker3.aio;

import java.lang.reflect.Method;

import com.fitechlabs.xtrade.kernel.boot.Services;
import com.fitechlabs.xtrade.kernel.util.ThreadLocalSystemAttributesRegistry;
import com.fitechlabs.xtrade.plugin.security.oplogin.OpLoginAdminService;
import com.fitechlabs.xtrade.plugin.security.oplogin.OpLoginSecurityService;
import com.fitechlabs.xtrade.plugin.security.oplogin.impl.LoginInfoImplForMock;
import com.fitechlabs.xtrade.plugin.security.oplogin.impl.OpLoginAdminServiceImplForMock;
import com.fitechlabs.xtrade.plugin.security.oplogin.impl.OpLoginSecurityServiceImplForMock;

import test.util.TestDBUtility;

import webbroker3.common.WEB3BaseRuntimeException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.gentrade.WEB3GentradeTradingTimeManagement;
import webbroker3.gentrade.data.AdministratorParams;
import webbroker3.gentrade.data.AdministratorRow;
import webbroker3.gentrade.data.TradingTimeParams;
import webbroker3.gentrade.data.TradingTimeRow;
import webbroker3.mock.TestBaseForMock;
import webbroker3.util.WEB3LogUtility;

public class WEB3AdminAioSLProductRegistServiceInterceptorTest extends TestBaseForMock
{
    /**
     * ログ出力ユーティリティ。
     */
    private static WEB3LogUtility log =
        WEB3LogUtility.getInstance(WEB3AdminAioSLProductRegistServiceInterceptorTest.class);

    public WEB3AdminAioSLProductRegistServiceInterceptorTest(String arg0)
    {
        super(arg0);
    }

    protected void setUp() throws Exception
    {
        super.setUp();
        Services.overrideService(OpLoginSecurityService.class, new OpLoginSecurityServiceImplForMock());
        Services.overrideService(OpLoginAdminService.class, new OpLoginAdminServiceImplForMock());
        TestBaseForMock.MOCK_MANAGER.setIsMockUsed(true);
    }

    protected void tearDown() throws Exception
    {
        super.tearDown();
    }
    
    /**
     * 受付日時、日付ロールをセットする。 
     * －取引時間管理.setTimestamp()をコールする。
     * 抛出異常 
     */
    public void testOnCallCase0001()
    {
        final String STR_METHOD_NAME = "testOnCallCase0001()";
        log.entering(TEST_START + STR_METHOD_NAME);
        
        try
        {
            
            LoginInfoImplForMock l_loginInfo = new LoginInfoImplForMock();
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                    "com.fitechlabs.xtrade.plugin.security.oplogin.impl.OpLoginSecurityServiceImpl", 
                    "getLoginInfo",
                    new Class[] {},
                    l_loginInfo);
            
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                    "com.fitechlabs.xtrade.plugin.security.oplogin.impl.LoginInfoImpl",
                    "getLoginId",
                    new Class[] {},
                    new Long(12345));
            
            TestDBUtility.deleteAll(TradingTimeRow.TYPE);
            Method l_method = null;
            Object[] l_serviceParam = null;
            WEB3AdminAioSLProductRegistServiceInterceptor l_interceptor =
                new WEB3AdminAioSLProductRegistServiceInterceptor();
            l_interceptor.onCall(l_method, l_serviceParam);
            fail();
        }
        catch(WEB3BaseRuntimeException l_ex)
        {
            log.debug(STR_METHOD_NAME, l_ex);
            assertEquals(l_ex.getErrorInfo(),WEB3ErrorCatalog.SYSTEM_ERROR_80003);
            log.info(STR_METHOD_NAME + ">>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>test pass !!");
        }
        catch (Exception l_ex)
        {
            log.error(ERROR + l_ex.getMessage(), l_ex);
            fail();
        }
    }
    
    /**
     * 受付日時、日付ロールをセットする。 
     * 取引時間管理.setTimestamp()をコールする。
     * 正常通過。
     */
    public void testOnCallCase0002()
    {
        final String STR_METHOD_NAME = "testOnCallCase0002()";
        log.entering(TEST_START + STR_METHOD_NAME);
        
        try
        {
            
            LoginInfoImplForMock l_loginInfo = new LoginInfoImplForMock();
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                    "com.fitechlabs.xtrade.plugin.security.oplogin.impl.OpLoginSecurityServiceImpl", 
                    "getLoginInfo",
                    new Class[] {},
                    l_loginInfo);
            
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                    "com.fitechlabs.xtrade.plugin.security.oplogin.impl.LoginInfoImpl",
                    "getLoginId",
                    new Class[] {},
                    new Long(12345));

            TestDBUtility.deleteAll(TradingTimeRow.TYPE);
            TradingTimeParams l_tradingTimeParams = TestDBUtility.getTradingTimeRow();
            l_tradingTimeParams.setInstitutionCode("0D");
            l_tradingTimeParams.setBranchCode("381");
            l_tradingTimeParams.setTradingTimeType("00");
            l_tradingTimeParams.setBizDateType("1");
            l_tradingTimeParams.setMarketCode("0");
            l_tradingTimeParams.setProductCode("0");
            
            TestDBUtility.insertWithDel(l_tradingTimeParams);
            
            // AdministratorDao
            TestDBUtility.deleteAll(AdministratorRow.TYPE);
            AdministratorParams l_administratorParams = TestDBUtility.getAdministratorRow();
            l_administratorParams.setLoginId(12345);
            l_administratorParams.setBranchCode("381");
            TestDBUtility.insertWithDel(l_administratorParams);

            Method l_method = null;
            Object[] l_serviceParam = null;
            WEB3AdminAioSLProductRegistServiceInterceptor l_interceptor =
                new WEB3AdminAioSLProductRegistServiceInterceptor();
            l_interceptor.onCall(l_method, l_serviceParam);
        }
        catch (Exception l_ex)
        {
            log.error(ERROR + l_ex.getMessage(), l_ex);
            fail();
        }
        log.info(STR_METHOD_NAME + ">>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>test pass !!");
    }
    
    /**
     * onReturn
     */
    public void testOnReturnCase0001()
    {
        final String STR_METHOD_NAME = "testOnCallCase0001()";
        log.entering(TEST_START + STR_METHOD_NAME);
        
        try
        {
            Object l_context = null;
            Object l_returnValue = null;

            WEB3AdminAioSLProductRegistServiceInterceptor l_interceptor =
                new WEB3AdminAioSLProductRegistServiceInterceptor();
            l_interceptor.onReturn(l_context, l_returnValue);
            
            
            assertNull(ThreadLocalSystemAttributesRegistry.getAttribute(
                WEB3GentradeTradingTimeManagement.TIMESTAMP_TAG));
            assertNull(ThreadLocalSystemAttributesRegistry.getAttribute(
                WEB3GentradeTradingTimeManagement.OFFSET_TAG));
        }
        catch (Exception l_ex)
        {
            log.error(ERROR + l_ex.getMessage(), l_ex);
            fail();
        }
        log.info(STR_METHOD_NAME + ">>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>test pass !!");
    }
    
    /**
     * onThrowable
     */
    public void testOnThrowableCase0001()
    {
        final String STR_METHOD_NAME = "testOnThrowableCase0001()";
        log.entering(TEST_START + STR_METHOD_NAME);
        
        try
        {
            Object l_obj = null;
            Throwable l_throwable = null;

            WEB3AdminAioSLProductRegistServiceInterceptor l_interceptor =
                new WEB3AdminAioSLProductRegistServiceInterceptor();
            l_interceptor.onThrowable(l_obj, l_throwable);

            assertNull(ThreadLocalSystemAttributesRegistry.getAttribute(
                WEB3GentradeTradingTimeManagement.TIMESTAMP_TAG));
            assertNull(ThreadLocalSystemAttributesRegistry.getAttribute(
                WEB3GentradeTradingTimeManagement.OFFSET_TAG));
        }
        catch (Exception l_ex)
        {
            log.error(ERROR + l_ex.getMessage(), l_ex);
            fail();
        }
        log.info(STR_METHOD_NAME + ">>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>test pass !!");
    }  
}
@
