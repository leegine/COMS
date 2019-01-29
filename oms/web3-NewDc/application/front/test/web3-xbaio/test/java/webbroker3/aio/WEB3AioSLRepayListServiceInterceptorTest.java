head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.04.07.01.34.14;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	5444d9d078c63f4;
filename	WEB3AioSLRepayListServiceInterceptorTest.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright           : (株)大和総研 証券ソリューションシステム第二部
File Name           : 証券担保ローン返済一覧サービスインタセプタのテストクラス(WEB3AioSLRepayListHandlerTest.java)
Author Name         : Daiwa Institute of Research
Revision History    : 2007/09/25 周墨洋 (中訊) 新規作成 仕様変更・モデルNo.757
*/

package webbroker3.aio;

import java.util.Calendar;

import com.fitechlabs.xtrade.kernel.boot.Services;
import com.fitechlabs.xtrade.kernel.util.ThreadLocalSystemAttributesRegistry;
import com.fitechlabs.xtrade.plugin.tc.gentrade.FinApp;
import com.fitechlabs.xtrade.plugin.tc.gentrade.TradingSystem;
import com.fitechlabs.xtrade.plugin.tc.gentrade.data.BranchParams;
import com.fitechlabs.xtrade.plugin.tc.gentrade.data.InstitutionParams;
import com.fitechlabs.xtrade.plugin.tc.gentrade.data.MainAccountParams;

import test.util.TestDBUtility;
import test.util.TestSpecialClassUtility;
import webbroker3.common.WEB3BaseRuntimeException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.gentrade.WEB3GentradeTradingClendarContext;
import webbroker3.gentrade.WEB3GentradeTradingTimeManagement;
import webbroker3.gentrade.WEB3GentradeTradingTimeManagementForMock;
import webbroker3.gentrade.data.TradingTimeParams;
import webbroker3.mock.TestBaseForMock;
import webbroker3.util.WEB3LogUtility;

/**
 * (証券担保ローン返済一覧サービスインタセプタ)<BR>
 * 証券担保ローン返済一覧サービスインタセプタのテストクラス<BR>
 *
 * @@author 周墨洋
 * @@version 1.0
 */
public class WEB3AioSLRepayListServiceInterceptorTest extends TestBaseForMock
{

    /**
     * (ログ出力ユーティリティ)
     */
    private static WEB3LogUtility log =
        WEB3LogUtility.getInstance(
            WEB3AioSLRepayListServiceInterceptorTest.class);

    /**
     * 証券担保ローン返済一覧サービスインタセプタ
     */
    private WEB3AioSLRepayListServiceInterceptor l_interceptor = null;

    /**
     * @@param arg0
     */
    public WEB3AioSLRepayListServiceInterceptorTest(String arg0)
    {
        super(arg0);
    }

    /**
     *
     */
    protected void setUp() throws Exception
    {
        super.setUp();
    }

    /**
     *
     */
    protected void tearDown() throws Exception
    {
        super.tearDown();
    }

    /**
     *
     */
//    public void testOnCall_case0001()
//    {
//        final String STR_METHOD_NAME = " testOnCall_case0001()";
//        log.entering(STR_METHOD_NAME);
//
//        l_interceptor = new WEB3AioSLRepayListServiceInterceptor();
//
//        MainAccountParams l_mainAccountParams = TestDBUtility.getMainAccountRow();
//
//        try
//        {
//            TestDBUtility.deleteAll(MainAccountParams.TYPE);
//            TestDBUtility.deleteAll(TradingTimeParams.TYPE);
//            TestDBUtility.insertWithDel(l_mainAccountParams);
//            TestDBUtility.insertWithDel(l_mainAccountParams);
//        }
//        catch (Exception l_ex)
//        {
//            log.debug(l_ex.getMessage(), l_ex);
//            log.exiting(TEST_END + STR_METHOD_NAME);
//            fail();
//        }
//
//        try
//        {
//            TestBaseForMock.MOCK_MANAGER.setIsMockUsed(true);
//            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
//                "com.fitechlabs.xtrade.plugin.security.oplogin.impl."
//                + "OpLoginSecurityServiceImpl",
//                "getAccountId",
//                new Class[] {},
//                new Long(l_mainAccountParams.getAccountId()));
//
//            l_interceptor.onCall(null, null);
//
//            fail();
//        }
//        catch (WEB3BaseRuntimeException l_ex)
//        {
//            log.debug(l_ex.getMessage(), l_ex);
//            assertEquals(WEB3ErrorCatalog.SYSTEM_ERROR_80005, l_ex.getErrorInfo());
//        }
//        catch (Exception l_exE)
//        {
//            log.debug(l_exE.getMessage(), l_exE);
//            fail();
//        }
//        finally
//        {
//            try
//            {
//                TestDBUtility.deleteAll(MainAccountParams.TYPE);
//                TestDBUtility.deleteAll(TradingTimeParams.TYPE);
//            }
//            catch (Exception l_ex)
//            {
//                log.debug(l_ex.getMessage(), l_ex);
//                log.exiting(TEST_END + STR_METHOD_NAME);
//                fail();
//            }
//        }
//
//        log.exiting(STR_METHOD_NAME);
//    }
//
//    /**
//     *
//     */
//    public void testOnCall_case0002()
//    {
//        final String STR_METHOD_NAME = " testOnCall_case0002()";
//        log.entering(STR_METHOD_NAME);
//
//        l_interceptor = new WEB3AioSLRepayListServiceInterceptor();
//
//        MainAccountParams l_mainAccountParams = TestDBUtility.getMainAccountRow();
//
//        try
//        {
//            TestDBUtility.deleteAll(MainAccountParams.TYPE);
//            TestDBUtility.deleteAll(TradingTimeParams.TYPE);
//        }
//        catch (Exception l_ex)
//        {
//            log.debug(l_ex.getMessage(), l_ex);
//            log.exiting(TEST_END + STR_METHOD_NAME);
//            fail();
//        }
//
//        try
//        {
//            TestBaseForMock.MOCK_MANAGER.setIsMockUsed(true);
//            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
//                "com.fitechlabs.xtrade.plugin.security.oplogin.impl."
//                + "OpLoginSecurityServiceImpl",
//                "getAccountId",
//                new Class[] {},
//                new Long(l_mainAccountParams.getAccountId()));
//
//            l_interceptor.onCall(null, new Object[]{});
//
//            fail();
//        }
//        catch (WEB3BaseRuntimeException l_ex)
//        {
//            log.debug(l_ex.getMessage(), l_ex);
//            assertEquals(WEB3ErrorCatalog.SYSTEM_ERROR_80017, l_ex.getErrorInfo());
//        }
//        catch (Exception l_exE)
//        {
//            log.debug(l_exE.getMessage(), l_exE);
//            fail();
//        }
//        finally
//        {
//            try
//            {
//                TestDBUtility.deleteAll(MainAccountParams.TYPE);
//                TestDBUtility.deleteAll(TradingTimeParams.TYPE);
//            }
//            catch (Exception l_ex)
//            {
//                log.debug(l_ex.getMessage(), l_ex);
//                log.exiting(TEST_END + STR_METHOD_NAME);
//                fail();
//            }
//        }
//
//        log.exiting(STR_METHOD_NAME);
//    }

    /**
     *
     */
    public void testOnCall_case0003()
    {
        final String STR_METHOD_NAME = " testOnCall_case0003()";
        log.entering(STR_METHOD_NAME);

        l_interceptor = new WEB3AioSLRepayListServiceInterceptor();

        MainAccountParams l_mainAccountParams = TestDBUtility.getMainAccountRow();

        try
        {
            TestDBUtility.deleteAll(MainAccountParams.TYPE);
            TestDBUtility.deleteAll(TradingTimeParams.TYPE);
        }
        catch (Exception l_ex)
        {
            log.debug(l_ex.getMessage(), l_ex);
            log.exiting(TEST_END + STR_METHOD_NAME);
            fail();
        }

        try
        {
            TestBaseForMock.MOCK_MANAGER.setIsMockUsed(true);
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                "com.fitechlabs.xtrade.plugin.security.oplogin.impl."
                + "OpLoginSecurityServiceImpl",
                "getAccountId",
                new Class[] {},
                new Long(l_mainAccountParams.getAccountId()));

            l_interceptor.onCall(null, new Object[]{""});

            fail();
        }
        catch (WEB3BaseRuntimeException l_ex)
        {
            log.debug(l_ex.getMessage(), l_ex);
            assertEquals(WEB3ErrorCatalog.SYSTEM_ERROR_80005, l_ex.getErrorInfo());
        }
        catch (Exception l_exE)
        {
            log.debug(l_exE.getMessage(), l_exE);
            fail();
        }
        finally
        {
            try
            {
                TestDBUtility.deleteAll(MainAccountParams.TYPE);
                TestDBUtility.deleteAll(TradingTimeParams.TYPE);
            }
            catch (Exception l_ex)
            {
                log.debug(l_ex.getMessage(), l_ex);
                log.exiting(TEST_END + STR_METHOD_NAME);
                fail();
            }
        }

        log.exiting(STR_METHOD_NAME);
    }

    /**
     *
     */
    public void testOnCall_case0004()
    {
        final String STR_METHOD_NAME = " testOnCall_case0004()";
        log.entering(STR_METHOD_NAME);

        l_interceptor = new WEB3AioSLRepayListServiceInterceptor();

        InstitutionParams l_institutionParams = TestDBUtility.getInstitutionRow();
        BranchParams l_branchParams = TestDBUtility.getBranchRow();
        MainAccountParams l_mainAccountParams = TestDBUtility.getMainAccountRow();

        try
        {
            TestDBUtility.deleteAll(InstitutionParams.TYPE);
            TestDBUtility.deleteAll(BranchParams.TYPE);
            TestDBUtility.deleteAll(MainAccountParams.TYPE);
            TestDBUtility.deleteAll(TradingTimeParams.TYPE);

            TestDBUtility.insertWithDel(l_institutionParams);
            TestDBUtility.insertWithDel(l_branchParams);
            TestDBUtility.insertWithDel(l_mainAccountParams);
        }
        catch (Exception l_ex)
        {
            log.debug(l_ex.getMessage(), l_ex);
            log.exiting(TEST_END + STR_METHOD_NAME);
            fail();
        }

        try
        {
            TestBaseForMock.MOCK_MANAGER.setIsMockUsed(true);
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                "com.fitechlabs.xtrade.plugin.security.oplogin.impl."
                + "OpLoginSecurityServiceImpl",
                "getAccountId",
                new Class[] {},
                new Long(l_mainAccountParams.getAccountId()));

            l_interceptor.onCall(null, new Object[]{""});

            fail();
        }
        catch (WEB3BaseRuntimeException l_ex)
        {
            log.debug(l_ex.getMessage(), l_ex);
            assertEquals(WEB3ErrorCatalog.SYSTEM_ERROR_80003, l_ex.getErrorInfo());
        }
        catch (Exception l_exE)
        {
            log.debug(l_exE.getMessage(), l_exE);
            fail();
        }
        finally
        {
            try
            {
                TestDBUtility.deleteAll(InstitutionParams.TYPE);
                TestDBUtility.deleteAll(BranchParams.TYPE);
                TestDBUtility.deleteAll(MainAccountParams.TYPE);
                TestDBUtility.deleteAll(TradingTimeParams.TYPE);
            }
            catch (Exception l_ex)
            {
                log.debug(l_ex.getMessage(), l_ex);
                log.exiting(TEST_END + STR_METHOD_NAME);
                fail();
            }
        }

        log.exiting(STR_METHOD_NAME);
    }

    /**
     *
     */
    public void testOnCall_case0005()
    {
        final String STR_METHOD_NAME = " testOnCall_case0005()";
        log.entering(STR_METHOD_NAME);

        l_interceptor = new WEB3AioSLRepayListServiceInterceptor();

        InstitutionParams l_institutionParams = TestDBUtility.getInstitutionRow();
        BranchParams l_branchParams = TestDBUtility.getBranchRow();
        MainAccountParams l_mainAccountParams = TestDBUtility.getMainAccountRow();
        TradingTimeParams l_tradingTimeParams = TestDBUtility.getTradingTimeRow();

        try
        {
            FinApp l_finApp = (FinApp)Services.getService(FinApp.class);
            TradingSystem l_tradingSystem = l_finApp.getTradingSystem();
            java.sql.Timestamp l_tsProcessTime = l_tradingSystem.getSystemTimestamp();

            l_tradingTimeParams.setInstitutionCode("0D");
            l_tradingTimeParams.setBranchCode("381");
            l_tradingTimeParams.setMarketCode("0");
            l_tradingTimeParams.setTradingTimeType("37");
            l_tradingTimeParams.setProductCode("0");
            l_tradingTimeParams.setBizDateType(
                WEB3GentradeTradingTimeManagement.getBizDateType(l_tsProcessTime));
            l_tradingTimeParams.setStartTime("000000");
            l_tradingTimeParams.setEndTime("235959");
            l_tradingTimeParams.setSubmitMarketTrigger("0");
            l_tradingTimeParams.setEnableOrder("0");
            l_tradingTimeParams.setBizdateCalcParameter("0");
            l_tradingTimeParams.setCreatedTimestamp(Calendar.getInstance().getTime());
            l_tradingTimeParams.setLastUpdatedTimestamp(Calendar .getInstance().getTime());

            TestDBUtility.deleteAll(InstitutionParams.TYPE);
            TestDBUtility.deleteAll(BranchParams.TYPE);
            TestDBUtility.deleteAll(MainAccountParams.TYPE);
            TestDBUtility.deleteAll(TradingTimeParams.TYPE);

            TestDBUtility.insertWithDel(l_institutionParams);
            TestDBUtility.insertWithDel(l_branchParams);
            TestDBUtility.insertWithDel(l_mainAccountParams);
            TestDBUtility.insertWithDel(l_tradingTimeParams);
        }
        catch (Exception l_ex)
        {
            log.debug(l_ex.getMessage(), l_ex);
            log.exiting(TEST_END + STR_METHOD_NAME);
            fail();
        }

        try
        {
            TestBaseForMock.MOCK_MANAGER.setIsMockUsed(true);
            WEB3GentradeTradingTimeManagementForMock.mockSetTimestamp();
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                "com.fitechlabs.xtrade.plugin.security.oplogin.impl."
                + "OpLoginSecurityServiceImpl",
                "getAccountId",
                new Class[] {},
                new Long(l_mainAccountParams.getAccountId()));

            WEB3GentradeTradingClendarContext l_gentradeTradingClendarContext;
            l_gentradeTradingClendarContext =
                (WEB3GentradeTradingClendarContext)l_interceptor.onCall(
                    null, new Object[]{""});

            assertNotNull(l_gentradeTradingClendarContext);
            assertEquals("0D", l_gentradeTradingClendarContext.getInstitutionCode());
            assertEquals("381", l_gentradeTradingClendarContext.getBranchCode());
            assertEquals("0", l_gentradeTradingClendarContext.getMarketCode());
            assertEquals("37", l_gentradeTradingClendarContext.getTradingTimeType());
            assertEquals("0", l_gentradeTradingClendarContext.getProductCode());
            assertEquals("14", l_gentradeTradingClendarContext.getOrderAcceptProduct());
            assertEquals("08", l_gentradeTradingClendarContext.getOrderAcceptTransaction());

            assertEquals(l_gentradeTradingClendarContext,
                ThreadLocalSystemAttributesRegistry.getAttribute(
                    WEB3GentradeTradingTimeManagement.TRADING_CAL_CONTEXT_PATH));
        }
        catch (WEB3BaseRuntimeException l_ex)
        {
            log.debug(l_ex.getMessage(), l_ex);
            fail();
        }
        catch (Exception l_exE)
        {
            log.debug(l_exE.getMessage(), l_exE);
            fail();
        }
        finally
        {
            try
            {
                TestDBUtility.deleteAll(InstitutionParams.TYPE);
                TestDBUtility.deleteAll(BranchParams.TYPE);
                TestDBUtility.deleteAll(MainAccountParams.TYPE);
                TestDBUtility.deleteAll(TradingTimeParams.TYPE);
            }
            catch (Exception l_ex)
            {
                log.debug(l_ex.getMessage(), l_ex);
                log.exiting(TEST_END + STR_METHOD_NAME);
                fail();
            }
        }

        log.exiting(STR_METHOD_NAME);
    }

    /**
     *
     */
    public void testOnReturn_case0001()
    {
        final String STR_METHOD_NAME = " testOnReturn_case0001()";
        log.entering(TEST_START + STR_METHOD_NAME);

        l_interceptor = new WEB3AioSLRepayListServiceInterceptor();

        try
        {
            Object l_context = new Object();
            Object l_returnValue = new Object();
            l_interceptor.onReturn(l_context, l_returnValue);

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

    /**
     *
     */
    public void testOnThrowable_case0001()
    {
        final String STR_METHOD_NAME = " testOnThrowable_case0001()";
        log.entering(TEST_START + STR_METHOD_NAME);

        l_interceptor = new WEB3AioSLRepayListServiceInterceptor();

        Object l_context = new Object();
        Throwable l_throwable = new Throwable();
        l_interceptor.onThrowable(l_context, l_throwable);

        TestSpecialClassUtility.testServiceInterceptor(l_interceptor);
        assertNull(ThreadLocalSystemAttributesRegistry.getAttribute(
            WEB3GentradeTradingTimeManagement.TIMESTAMP_TAG));
        assertNull(ThreadLocalSystemAttributesRegistry.getAttribute(
            WEB3GentradeTradingTimeManagement.OFFSET_TAG));
        assertNull(ThreadLocalSystemAttributesRegistry.getAttribute(
            WEB3GentradeTradingTimeManagement.TRADING_CAL_CONTEXT_PATH));

        log.exiting(TEST_END + STR_METHOD_NAME);
    }

}
@
