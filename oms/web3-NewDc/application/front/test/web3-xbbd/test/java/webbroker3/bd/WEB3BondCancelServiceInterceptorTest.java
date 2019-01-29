head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.04.07.01.02.57;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	5444d9d078c63f4;
filename	WEB3BondCancelServiceInterceptorTest.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@package webbroker3.bd;

import com.fitechlabs.xtrade.kernel.util.ThreadLocalSystemAttributesRegistry;
import com.fitechlabs.xtrade.plugin.tc.gentrade.data.BranchParams;
import com.fitechlabs.xtrade.plugin.tc.gentrade.data.InstitutionParams;
import com.fitechlabs.xtrade.plugin.tc.gentrade.data.MainAccountParams;
import com.fitechlabs.xtrade.plugin.tc.xbbd.BondTypeEnum;
import com.fitechlabs.xtrade.plugin.tc.xbbd.data.BondOrderParams;
import com.fitechlabs.xtrade.plugin.tc.xbbd.data.BondOrderUnitParams;

import test.util.TestDBUtility;

import webbroker3.bd.message.WEB3BondCancelCompleteRequest;
import webbroker3.bd.message.WEB3BondCancelConfirmRequest;
import webbroker3.gentrade.WEB3GentradeTradingClendarContext;
import webbroker3.gentrade.WEB3GentradeTradingTimeManagement;
import webbroker3.gentrade.data.TradingTimeParams;
import webbroker3.mock.TestBaseForMock;
import webbroker3.util.WEB3DateUtility;
import webbroker3.util.WEB3LogUtility;

/**
 * 債券取消サービスインタセプタ
 * @@author xie-xuan
 * @@version 1.0
 */
public class WEB3BondCancelServiceInterceptorTest extends TestBaseForMock
{
    /**
     * ログ出力ユーティリティ。<BR>
     */
    private static WEB3LogUtility log =
        WEB3LogUtility.getInstance(
            WEB3BondCancelServiceInterceptorTest.class);

    /**
     * 債券取消サービスインタセプタ
     */
    WEB3BondCancelServiceInterceptor l_interceptor =null;

    /**
     * @@param arg0
     */
    public WEB3BondCancelServiceInterceptorTest(String arg0)
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
    public void testOnCall_0001()
    {
        final String STR_METHOD_NAME = " testOnCall_0001()";
        log.entering(TEST_START + STR_METHOD_NAME);

        l_interceptor = new WEB3BondCancelServiceInterceptor();

        InstitutionParams l_institutionParams = TestDBUtility.getInstitutionRow();
        BranchParams l_branchParams = TestDBUtility.getBranchRow();
        MainAccountParams l_mainAccountParams = TestDBUtility.getMainAccountRow();

        BondOrderUnitParams l_bondOrderUnitParams =
            TestDBUtility.getBondOrderUnitRow();
        l_bondOrderUnitParams.setBondType(BondTypeEnum.FOREIGN_BOND);

        BondOrderParams l_bondOrderParams = new BondOrderParams();
        l_bondOrderParams.setOrderId(l_bondOrderUnitParams.getOrderId());
        l_bondOrderParams.setAccountId(l_mainAccountParams.getAccountId());
        l_bondOrderParams.setSubAccountId(2536472L);
        l_bondOrderParams.setCreatedTimestamp(
            WEB3DateUtility.getDate("20070801", "yyyyMMdd"));
        l_bondOrderParams.setLastUpdatedTimestamp(
            WEB3DateUtility.getDate("20070801", "yyyyMMdd"));

        TradingTimeParams l_tradingTimeParams = TestDBUtility.getTradingTimeRow();
        l_tradingTimeParams.setTradingTimeType("25");
        l_tradingTimeParams.setBizDateType("1");
        l_tradingTimeParams.setMarketCode("0");

        try
        {
            TestDBUtility.deleteAll(InstitutionParams.TYPE);
            TestDBUtility.deleteAll(BranchParams.TYPE);
            TestDBUtility.deleteAll(MainAccountParams.TYPE);
            TestDBUtility.deleteAll(BondOrderParams.TYPE);
            TestDBUtility.deleteAll(BondOrderUnitParams.TYPE);
            TestDBUtility.deleteAll(TradingTimeParams.TYPE);

            TestDBUtility.insertWithDel(l_institutionParams);
            TestDBUtility.insertWithDel(l_branchParams);
            TestDBUtility.insertWithDel(l_mainAccountParams);
            TestDBUtility.insertWithDel(l_bondOrderParams);
            TestDBUtility.insertWithDel(l_bondOrderUnitParams);
            TestDBUtility.insertWithDel(l_tradingTimeParams);
        }
        catch (Exception l_exE)
        {
            log.error(TEST_END + STR_METHOD_NAME, l_exE);
            log.exiting(TEST_END + STR_METHOD_NAME);

            fail();
        }

        try
        {
            TestBaseForMock.MOCK_MANAGER.setIsMockUsed(true);

            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                "com.fitechlabs.xtrade.plugin.security.oplogin.impl.OpLoginSecurityServiceImpl",
                "getAccountId",
                new Class[] {},
                new Long(l_mainAccountParams.getAccountId()));

            WEB3BondCancelConfirmRequest l_bondCancelConfirmRequest =
                new WEB3BondCancelConfirmRequest();

            l_bondCancelConfirmRequest.orderId =
                l_bondOrderUnitParams.getOrderId() + "";

            Object[] l_serviceParam = new Object[]{l_bondCancelConfirmRequest};

            l_interceptor.onCall(null, l_serviceParam);

            assertNotNull(ThreadLocalSystemAttributesRegistry.getAttribute(
                WEB3GentradeTradingTimeManagement.TRADING_CAL_CONTEXT_PATH));

            WEB3GentradeTradingClendarContext l_actualContext =
                (WEB3GentradeTradingClendarContext)ThreadLocalSystemAttributesRegistry.getAttribute(
                    WEB3GentradeTradingTimeManagement.TRADING_CAL_CONTEXT_PATH);

            assertEquals(WEB3GentradeTradingClendarContext.class, l_actualContext.getClass());
            assertEquals("25", l_actualContext.getTradingTimeType());
        }
        catch (Exception l_exE)
        {
            log.error(TEST_END + STR_METHOD_NAME, l_exE);
            log.exiting(TEST_END + STR_METHOD_NAME);
            fail();
        }
        finally
        {
            try
            {
                TestDBUtility.deleteAll(InstitutionParams.TYPE);
                TestDBUtility.deleteAll(BranchParams.TYPE);
                TestDBUtility.deleteAll(MainAccountParams.TYPE);
                TestDBUtility.deleteAll(BondOrderParams.TYPE);
                TestDBUtility.deleteAll(BondOrderUnitParams.TYPE);
                TestDBUtility.deleteAll(TradingTimeParams.TYPE);
            }
            catch (Exception l_exE)
            {
                log.error(TEST_END + STR_METHOD_NAME, l_exE);
                log.exiting(TEST_END + STR_METHOD_NAME);

                fail();
            }
        }
        log.exiting(TEST_END + STR_METHOD_NAME);
    }

    /**
     *
     */
    public void testOnCall_0002()
    {
        final String STR_METHOD_NAME = " testOnCall_0002()";
        log.entering(TEST_START + STR_METHOD_NAME);

        l_interceptor = new WEB3BondCancelServiceInterceptor();

        InstitutionParams l_institutionParams = TestDBUtility.getInstitutionRow();
        BranchParams l_branchParams = TestDBUtility.getBranchRow();
        MainAccountParams l_mainAccountParams = TestDBUtility.getMainAccountRow();

        BondOrderUnitParams l_bondOrderUnitParams =
            TestDBUtility.getBondOrderUnitRow();
        l_bondOrderUnitParams.setBondType(BondTypeEnum.DOMESTIC_BOND);

        BondOrderParams l_bondOrderParams = new BondOrderParams();
        l_bondOrderParams.setOrderId(l_bondOrderUnitParams.getOrderId());
        l_bondOrderParams.setAccountId(l_mainAccountParams.getAccountId());
        l_bondOrderParams.setSubAccountId(2536472L);
        l_bondOrderParams.setCreatedTimestamp(
            WEB3DateUtility.getDate("20070801", "yyyyMMdd"));
        l_bondOrderParams.setLastUpdatedTimestamp(
            WEB3DateUtility.getDate("20070801", "yyyyMMdd"));

        TradingTimeParams l_tradingTimeParams = TestDBUtility.getTradingTimeRow();
        l_tradingTimeParams.setTradingTimeType("36");
        l_tradingTimeParams.setBizDateType("1");
        l_tradingTimeParams.setMarketCode("0");

        try
        {
            TestDBUtility.deleteAll(InstitutionParams.TYPE);
            TestDBUtility.deleteAll(BranchParams.TYPE);
            TestDBUtility.deleteAll(MainAccountParams.TYPE);
            TestDBUtility.deleteAll(BondOrderParams.TYPE);
            TestDBUtility.deleteAll(BondOrderUnitParams.TYPE);
            TestDBUtility.deleteAll(TradingTimeParams.TYPE);

            TestDBUtility.insertWithDel(l_institutionParams);
            TestDBUtility.insertWithDel(l_branchParams);
            TestDBUtility.insertWithDel(l_mainAccountParams);
            TestDBUtility.insertWithDel(l_bondOrderParams);
            TestDBUtility.insertWithDel(l_bondOrderUnitParams);
            TestDBUtility.insertWithDel(l_tradingTimeParams);
        }
        catch (Exception l_exE)
        {
            log.error(TEST_END + STR_METHOD_NAME, l_exE);
            log.exiting(TEST_END + STR_METHOD_NAME);

            fail();
        }

        try
        {
            TestBaseForMock.MOCK_MANAGER.setIsMockUsed(true);

            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                "com.fitechlabs.xtrade.plugin.security.oplogin.impl.OpLoginSecurityServiceImpl",
                "getAccountId",
                new Class[] {},
                new Long(l_mainAccountParams.getAccountId()));

            WEB3BondCancelConfirmRequest l_bondCancelConfirmRequest =
                new WEB3BondCancelConfirmRequest();

            l_bondCancelConfirmRequest.orderId =
                l_bondOrderUnitParams.getOrderId() + "";

            Object[] l_serviceParam = new Object[]{l_bondCancelConfirmRequest};

            l_interceptor.onCall(null, l_serviceParam);

            assertNotNull(ThreadLocalSystemAttributesRegistry.getAttribute(
                WEB3GentradeTradingTimeManagement.TRADING_CAL_CONTEXT_PATH));

            WEB3GentradeTradingClendarContext l_actualContext =
                (WEB3GentradeTradingClendarContext)ThreadLocalSystemAttributesRegistry.getAttribute(
                    WEB3GentradeTradingTimeManagement.TRADING_CAL_CONTEXT_PATH);

            assertEquals(WEB3GentradeTradingClendarContext.class, l_actualContext.getClass());
            assertEquals("36", l_actualContext.getTradingTimeType());
        }
        catch (Exception l_exE)
        {
            log.error(TEST_END + STR_METHOD_NAME, l_exE);
            log.exiting(TEST_END + STR_METHOD_NAME);
            fail();
        }
        finally
        {
            try
            {
                TestDBUtility.deleteAll(InstitutionParams.TYPE);
                TestDBUtility.deleteAll(BranchParams.TYPE);
                TestDBUtility.deleteAll(MainAccountParams.TYPE);
                TestDBUtility.deleteAll(BondOrderParams.TYPE);
                TestDBUtility.deleteAll(BondOrderUnitParams.TYPE);
                TestDBUtility.deleteAll(TradingTimeParams.TYPE);
            }
            catch (Exception l_exE)
            {
                log.error(TEST_END + STR_METHOD_NAME, l_exE);
                log.exiting(TEST_END + STR_METHOD_NAME);

                fail();
            }
        }
        log.exiting(TEST_END + STR_METHOD_NAME);
    }

    /**
     *
     */
    public void testOnCall_0003()
    {
        final String STR_METHOD_NAME = " testOnCall_0003()";
        log.entering(TEST_START + STR_METHOD_NAME);

        l_interceptor = new WEB3BondCancelServiceInterceptor();

        InstitutionParams l_institutionParams = TestDBUtility.getInstitutionRow();
        BranchParams l_branchParams = TestDBUtility.getBranchRow();
        MainAccountParams l_mainAccountParams = TestDBUtility.getMainAccountRow();

        BondOrderUnitParams l_bondOrderUnitParams =
            TestDBUtility.getBondOrderUnitRow();
        l_bondOrderUnitParams.setBondType(null);

        BondOrderParams l_bondOrderParams = new BondOrderParams();
        l_bondOrderParams.setOrderId(l_bondOrderUnitParams.getOrderId());
        l_bondOrderParams.setAccountId(l_mainAccountParams.getAccountId());
        l_bondOrderParams.setSubAccountId(2536472L);
        l_bondOrderParams.setCreatedTimestamp(
            WEB3DateUtility.getDate("20070801", "yyyyMMdd"));
        l_bondOrderParams.setLastUpdatedTimestamp(
            WEB3DateUtility.getDate("20070801", "yyyyMMdd"));

        TradingTimeParams l_tradingTimeParams = TestDBUtility.getTradingTimeRow();
        l_tradingTimeParams.setTradingTimeType("36");
        l_tradingTimeParams.setBizDateType("1");
        l_tradingTimeParams.setMarketCode("0");

        try
        {
            TestDBUtility.deleteAll(InstitutionParams.TYPE);
            TestDBUtility.deleteAll(BranchParams.TYPE);
            TestDBUtility.deleteAll(MainAccountParams.TYPE);
            TestDBUtility.deleteAll(BondOrderParams.TYPE);
            TestDBUtility.deleteAll(BondOrderUnitParams.TYPE);
            TestDBUtility.deleteAll(TradingTimeParams.TYPE);

            TestDBUtility.insertWithDel(l_institutionParams);
            TestDBUtility.insertWithDel(l_branchParams);
            TestDBUtility.insertWithDel(l_mainAccountParams);
            TestDBUtility.insertWithDel(l_bondOrderParams);
            TestDBUtility.insertWithDel(l_bondOrderUnitParams);
            TestDBUtility.insertWithDel(l_tradingTimeParams);
        }
        catch (Exception l_exE)
        {
            log.error(TEST_END + STR_METHOD_NAME, l_exE);
            log.exiting(TEST_END + STR_METHOD_NAME);

            fail();
        }

        try
        {
            TestBaseForMock.MOCK_MANAGER.setIsMockUsed(true);

            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                "com.fitechlabs.xtrade.plugin.security.oplogin.impl.OpLoginSecurityServiceImpl",
                "getAccountId",
                new Class[] {},
                new Long(l_mainAccountParams.getAccountId()));

            WEB3BondCancelConfirmRequest l_bondCancelConfirmRequest =
                new WEB3BondCancelConfirmRequest();

            l_bondCancelConfirmRequest.orderId =
                l_bondOrderUnitParams.getOrderId() + "";

            Object[] l_serviceParam = new Object[]{l_bondCancelConfirmRequest};

            l_interceptor.onCall(null, l_serviceParam);

            assertNotNull(ThreadLocalSystemAttributesRegistry.getAttribute(
                WEB3GentradeTradingTimeManagement.TRADING_CAL_CONTEXT_PATH));

            WEB3GentradeTradingClendarContext l_actualContext =
                (WEB3GentradeTradingClendarContext)ThreadLocalSystemAttributesRegistry.getAttribute(
                    WEB3GentradeTradingTimeManagement.TRADING_CAL_CONTEXT_PATH);

            assertEquals(WEB3GentradeTradingClendarContext.class, l_actualContext.getClass());
            assertEquals("36", l_actualContext.getTradingTimeType());
        }
        catch (Exception l_exE)
        {
            log.error(TEST_END + STR_METHOD_NAME, l_exE);
            log.exiting(TEST_END + STR_METHOD_NAME);
            fail();
        }
        finally
        {
            try
            {
                TestDBUtility.deleteAll(InstitutionParams.TYPE);
                TestDBUtility.deleteAll(BranchParams.TYPE);
                TestDBUtility.deleteAll(MainAccountParams.TYPE);
                TestDBUtility.deleteAll(BondOrderParams.TYPE);
                TestDBUtility.deleteAll(BondOrderUnitParams.TYPE);
                TestDBUtility.deleteAll(TradingTimeParams.TYPE);
            }
            catch (Exception l_exE)
            {
                log.error(TEST_END + STR_METHOD_NAME, l_exE);
                log.exiting(TEST_END + STR_METHOD_NAME);

                fail();
            }
        }
        log.exiting(TEST_END + STR_METHOD_NAME);
    }

    /**
     *
     */
    public void testOnCall_0004()
    {
        final String STR_METHOD_NAME = " testOnCall_0004()";
        log.entering(TEST_START + STR_METHOD_NAME);

        l_interceptor = new WEB3BondCancelServiceInterceptor();

        InstitutionParams l_institutionParams = TestDBUtility.getInstitutionRow();
        BranchParams l_branchParams = TestDBUtility.getBranchRow();
        MainAccountParams l_mainAccountParams = TestDBUtility.getMainAccountRow();

        BondOrderUnitParams l_bondOrderUnitParams =
            TestDBUtility.getBondOrderUnitRow();
        l_bondOrderUnitParams.setBondType(BondTypeEnum.FOREIGN_BOND);

        BondOrderParams l_bondOrderParams = new BondOrderParams();
        l_bondOrderParams.setOrderId(l_bondOrderUnitParams.getOrderId());
        l_bondOrderParams.setAccountId(l_mainAccountParams.getAccountId());
        l_bondOrderParams.setSubAccountId(2536472L);
        l_bondOrderParams.setCreatedTimestamp(
            WEB3DateUtility.getDate("20070801", "yyyyMMdd"));
        l_bondOrderParams.setLastUpdatedTimestamp(
            WEB3DateUtility.getDate("20070801", "yyyyMMdd"));

        TradingTimeParams l_tradingTimeParams = TestDBUtility.getTradingTimeRow();
        l_tradingTimeParams.setTradingTimeType("25");
        l_tradingTimeParams.setBizDateType("1");
        l_tradingTimeParams.setMarketCode("0");

        try
        {
            TestDBUtility.deleteAll(InstitutionParams.TYPE);
            TestDBUtility.deleteAll(BranchParams.TYPE);
            TestDBUtility.deleteAll(MainAccountParams.TYPE);
            TestDBUtility.deleteAll(BondOrderParams.TYPE);
            TestDBUtility.deleteAll(BondOrderUnitParams.TYPE);
            TestDBUtility.deleteAll(TradingTimeParams.TYPE);

            TestDBUtility.insertWithDel(l_institutionParams);
            TestDBUtility.insertWithDel(l_branchParams);
            TestDBUtility.insertWithDel(l_mainAccountParams);
            TestDBUtility.insertWithDel(l_bondOrderParams);
            TestDBUtility.insertWithDel(l_bondOrderUnitParams);
            TestDBUtility.insertWithDel(l_tradingTimeParams);
        }
        catch (Exception l_exE)
        {
            log.error(TEST_END + STR_METHOD_NAME, l_exE);
            log.exiting(TEST_END + STR_METHOD_NAME);

            fail();
        }

        try
        {
            TestBaseForMock.MOCK_MANAGER.setIsMockUsed(true);

            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                "com.fitechlabs.xtrade.plugin.security.oplogin.impl.OpLoginSecurityServiceImpl",
                "getAccountId",
                new Class[] {},
                new Long(l_mainAccountParams.getAccountId()));

            WEB3BondCancelCompleteRequest l_bondCancelCompleteRequest =
                new WEB3BondCancelCompleteRequest();

            l_bondCancelCompleteRequest.orderId =
                l_bondOrderUnitParams.getOrderId() + "";

            Object[] l_serviceParam = new Object[]{l_bondCancelCompleteRequest};

            l_interceptor.onCall(null, l_serviceParam);

            assertNotNull(ThreadLocalSystemAttributesRegistry.getAttribute(
                WEB3GentradeTradingTimeManagement.TRADING_CAL_CONTEXT_PATH));

            WEB3GentradeTradingClendarContext l_actualContext =
                (WEB3GentradeTradingClendarContext)ThreadLocalSystemAttributesRegistry.getAttribute(
                    WEB3GentradeTradingTimeManagement.TRADING_CAL_CONTEXT_PATH);

            assertEquals(WEB3GentradeTradingClendarContext.class, l_actualContext.getClass());
            assertEquals("25", l_actualContext.getTradingTimeType());
        }
        catch (Exception l_exE)
        {
            log.error(TEST_END + STR_METHOD_NAME, l_exE);
            log.exiting(TEST_END + STR_METHOD_NAME);
            fail();
        }
        finally
        {
            try
            {
                TestDBUtility.deleteAll(InstitutionParams.TYPE);
                TestDBUtility.deleteAll(BranchParams.TYPE);
                TestDBUtility.deleteAll(MainAccountParams.TYPE);
                TestDBUtility.deleteAll(BondOrderParams.TYPE);
                TestDBUtility.deleteAll(BondOrderUnitParams.TYPE);
                TestDBUtility.deleteAll(TradingTimeParams.TYPE);
            }
            catch (Exception l_exE)
            {
                log.error(TEST_END + STR_METHOD_NAME, l_exE);
                log.exiting(TEST_END + STR_METHOD_NAME);

                fail();
            }
        }
        log.exiting(TEST_END + STR_METHOD_NAME);
    }

    /**
     *
     */
    public void testOnCall_0005()
    {
        final String STR_METHOD_NAME = " testOnCall_0005()";
        log.entering(TEST_START + STR_METHOD_NAME);

        l_interceptor = new WEB3BondCancelServiceInterceptor();

        InstitutionParams l_institutionParams = TestDBUtility.getInstitutionRow();
        BranchParams l_branchParams = TestDBUtility.getBranchRow();
        MainAccountParams l_mainAccountParams = TestDBUtility.getMainAccountRow();

        BondOrderUnitParams l_bondOrderUnitParams =
            TestDBUtility.getBondOrderUnitRow();
        l_bondOrderUnitParams.setBondType(BondTypeEnum.DOMESTIC_BOND);

        BondOrderParams l_bondOrderParams = new BondOrderParams();
        l_bondOrderParams.setOrderId(l_bondOrderUnitParams.getOrderId());
        l_bondOrderParams.setAccountId(l_mainAccountParams.getAccountId());
        l_bondOrderParams.setSubAccountId(2536472L);
        l_bondOrderParams.setCreatedTimestamp(
            WEB3DateUtility.getDate("20070801", "yyyyMMdd"));
        l_bondOrderParams.setLastUpdatedTimestamp(
            WEB3DateUtility.getDate("20070801", "yyyyMMdd"));

        TradingTimeParams l_tradingTimeParams = TestDBUtility.getTradingTimeRow();
        l_tradingTimeParams.setTradingTimeType("36");
        l_tradingTimeParams.setBizDateType("1");
        l_tradingTimeParams.setMarketCode("0");

        try
        {
            TestDBUtility.deleteAll(InstitutionParams.TYPE);
            TestDBUtility.deleteAll(BranchParams.TYPE);
            TestDBUtility.deleteAll(MainAccountParams.TYPE);
            TestDBUtility.deleteAll(BondOrderParams.TYPE);
            TestDBUtility.deleteAll(BondOrderUnitParams.TYPE);
            TestDBUtility.deleteAll(TradingTimeParams.TYPE);

            TestDBUtility.insertWithDel(l_institutionParams);
            TestDBUtility.insertWithDel(l_branchParams);
            TestDBUtility.insertWithDel(l_mainAccountParams);
            TestDBUtility.insertWithDel(l_bondOrderParams);
            TestDBUtility.insertWithDel(l_bondOrderUnitParams);
            TestDBUtility.insertWithDel(l_tradingTimeParams);
        }
        catch (Exception l_exE)
        {
            log.error(TEST_END + STR_METHOD_NAME, l_exE);
            log.exiting(TEST_END + STR_METHOD_NAME);

            fail();
        }

        try
        {
            TestBaseForMock.MOCK_MANAGER.setIsMockUsed(true);

            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                "com.fitechlabs.xtrade.plugin.security.oplogin.impl.OpLoginSecurityServiceImpl",
                "getAccountId",
                new Class[] {},
                new Long(l_mainAccountParams.getAccountId()));

            WEB3BondCancelCompleteRequest l_bondCancelCompleteRequest =
                new WEB3BondCancelCompleteRequest();

            l_bondCancelCompleteRequest.orderId =
                l_bondOrderUnitParams.getOrderId() + "";

            Object[] l_serviceParam = new Object[]{l_bondCancelCompleteRequest};

            l_interceptor.onCall(null, l_serviceParam);

            assertNotNull(ThreadLocalSystemAttributesRegistry.getAttribute(
                WEB3GentradeTradingTimeManagement.TRADING_CAL_CONTEXT_PATH));

            WEB3GentradeTradingClendarContext l_actualContext =
                (WEB3GentradeTradingClendarContext)ThreadLocalSystemAttributesRegistry.getAttribute(
                    WEB3GentradeTradingTimeManagement.TRADING_CAL_CONTEXT_PATH);

            assertEquals(WEB3GentradeTradingClendarContext.class, l_actualContext.getClass());
            assertEquals("36", l_actualContext.getTradingTimeType());
        }
        catch (Exception l_exE)
        {
            log.error(TEST_END + STR_METHOD_NAME, l_exE);
            log.exiting(TEST_END + STR_METHOD_NAME);
            fail();
        }
        finally
        {
            try
            {
                TestDBUtility.deleteAll(InstitutionParams.TYPE);
                TestDBUtility.deleteAll(BranchParams.TYPE);
                TestDBUtility.deleteAll(MainAccountParams.TYPE);
                TestDBUtility.deleteAll(BondOrderParams.TYPE);
                TestDBUtility.deleteAll(BondOrderUnitParams.TYPE);
                TestDBUtility.deleteAll(TradingTimeParams.TYPE);
            }
            catch (Exception l_exE)
            {
                log.error(TEST_END + STR_METHOD_NAME, l_exE);
                log.exiting(TEST_END + STR_METHOD_NAME);

                fail();
            }
        }
        log.exiting(TEST_END + STR_METHOD_NAME);
    }

    /**
     *
     */
    public void testOnCall_0006()
    {
        final String STR_METHOD_NAME = " testOnCall_0006()";
        log.entering(TEST_START + STR_METHOD_NAME);

        l_interceptor = new WEB3BondCancelServiceInterceptor();

        InstitutionParams l_institutionParams = TestDBUtility.getInstitutionRow();
        BranchParams l_branchParams = TestDBUtility.getBranchRow();
        MainAccountParams l_mainAccountParams = TestDBUtility.getMainAccountRow();

        BondOrderUnitParams l_bondOrderUnitParams =
            TestDBUtility.getBondOrderUnitRow();
        l_bondOrderUnitParams.setBondType(null);

        BondOrderParams l_bondOrderParams = new BondOrderParams();
        l_bondOrderParams.setOrderId(l_bondOrderUnitParams.getOrderId());
        l_bondOrderParams.setAccountId(l_mainAccountParams.getAccountId());
        l_bondOrderParams.setSubAccountId(2536472L);
        l_bondOrderParams.setCreatedTimestamp(
            WEB3DateUtility.getDate("20070801", "yyyyMMdd"));
        l_bondOrderParams.setLastUpdatedTimestamp(
            WEB3DateUtility.getDate("20070801", "yyyyMMdd"));

        TradingTimeParams l_tradingTimeParams = TestDBUtility.getTradingTimeRow();
        l_tradingTimeParams.setTradingTimeType("36");
        l_tradingTimeParams.setBizDateType("1");
        l_tradingTimeParams.setMarketCode("0");

        try
        {
            TestDBUtility.deleteAll(InstitutionParams.TYPE);
            TestDBUtility.deleteAll(BranchParams.TYPE);
            TestDBUtility.deleteAll(MainAccountParams.TYPE);
            TestDBUtility.deleteAll(BondOrderParams.TYPE);
            TestDBUtility.deleteAll(BondOrderUnitParams.TYPE);
            TestDBUtility.deleteAll(TradingTimeParams.TYPE);

            TestDBUtility.insertWithDel(l_institutionParams);
            TestDBUtility.insertWithDel(l_branchParams);
            TestDBUtility.insertWithDel(l_mainAccountParams);
            TestDBUtility.insertWithDel(l_bondOrderParams);
            TestDBUtility.insertWithDel(l_bondOrderUnitParams);
            TestDBUtility.insertWithDel(l_tradingTimeParams);
        }
        catch (Exception l_exE)
        {
            log.error(TEST_END + STR_METHOD_NAME, l_exE);
            log.exiting(TEST_END + STR_METHOD_NAME);

            fail();
        }

        try
        {
            TestBaseForMock.MOCK_MANAGER.setIsMockUsed(true);

            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                "com.fitechlabs.xtrade.plugin.security.oplogin.impl.OpLoginSecurityServiceImpl",
                "getAccountId",
                new Class[] {},
                new Long(l_mainAccountParams.getAccountId()));

            WEB3BondCancelCompleteRequest l_bondCancelCompleteRequest =
                new WEB3BondCancelCompleteRequest();

            l_bondCancelCompleteRequest.orderId =
                l_bondOrderUnitParams.getOrderId() + "";

            Object[] l_serviceParam = new Object[]{l_bondCancelCompleteRequest};

            l_interceptor.onCall(null, l_serviceParam);

            assertNotNull(ThreadLocalSystemAttributesRegistry.getAttribute(
                WEB3GentradeTradingTimeManagement.TRADING_CAL_CONTEXT_PATH));

            WEB3GentradeTradingClendarContext l_actualContext =
                (WEB3GentradeTradingClendarContext)ThreadLocalSystemAttributesRegistry.getAttribute(
                    WEB3GentradeTradingTimeManagement.TRADING_CAL_CONTEXT_PATH);

            assertEquals(WEB3GentradeTradingClendarContext.class, l_actualContext.getClass());
            assertEquals("36", l_actualContext.getTradingTimeType());
        }
        catch (Exception l_exE)
        {
            log.error(TEST_END + STR_METHOD_NAME, l_exE);
            log.exiting(TEST_END + STR_METHOD_NAME);
            fail();
        }
        finally
        {
            try
            {
                TestDBUtility.deleteAll(InstitutionParams.TYPE);
                TestDBUtility.deleteAll(BranchParams.TYPE);
                TestDBUtility.deleteAll(MainAccountParams.TYPE);
                TestDBUtility.deleteAll(BondOrderParams.TYPE);
                TestDBUtility.deleteAll(BondOrderUnitParams.TYPE);
                TestDBUtility.deleteAll(TradingTimeParams.TYPE);
            }
            catch (Exception l_exE)
            {
                log.error(TEST_END + STR_METHOD_NAME, l_exE);
                log.exiting(TEST_END + STR_METHOD_NAME);

                fail();
            }
        }
        log.exiting(TEST_END + STR_METHOD_NAME);
    }

}
@
