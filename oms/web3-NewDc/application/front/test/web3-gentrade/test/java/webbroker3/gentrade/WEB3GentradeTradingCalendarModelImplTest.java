head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.04.07.01.22.32;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	5444d9d078c63f4;
filename	WEB3GentradeTradingCalendarModelImplTest.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@package webbroker3.gentrade;

import java.util.Date;

import com.fitechlabs.xtrade.kernel.util.ThreadLocalSystemAttributesRegistry;
import com.fitechlabs.xtrade.plugin.tc.gentrade.GtlUtils;
import com.fitechlabs.xtrade.plugin.tc.gentrade.data.MarketParams;
import com.fitechlabs.xtrade.plugin.tc.gentrade.data.MarketPreferencesParams;
import com.fitechlabs.xtrade.plugin.tc.gentrade.data.TradedProductParams;

import test.util.TestDBUtility;

import webbroker3.gentrade.data.TradingTimeParams;
import webbroker3.mock.TestBaseForMock;
import webbroker3.util.WEB3DateUtility;
import webbroker3.util.WEB3LogUtility;

public class WEB3GentradeTradingCalendarModelImplTest extends TestBaseForMock
{

    /**
     * ログユーティリティ<BR>
     */
    private static WEB3LogUtility log =
        WEB3LogUtility.getInstance(WEB3GentradeTradingCalendarModelImplTest.class);

    public WEB3GentradeTradingCalendarModelImplTest(String arg0)
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

    public void testGetCurrentBizDate()
    {
        final String STR_METHOD_NAME = "testGetCurrentBizDate()";
        log.entering(STR_METHOD_NAME);

        TestBaseForMock.MOCK_MANAGER.setIsMockUsed(true);

        try
        {
            WEB3GentradeTradingTimeManagementForMock.mockGetOrderBizDate(
                WEB3DateUtility.getDate("20071219","yyyyMMdd"));

            TestDBUtility.deleteAll(TradedProductParams.TYPE);
            TradedProductParams l_tradedProductParams = TestDBUtility.getTradedProductRow();
            l_tradedProductParams.setTradedProductId(1001L);
            l_tradedProductParams.setMarketId(1002L);
            TestDBUtility.insertWithDel(l_tradedProductParams);

            TestDBUtility.deleteAll(MarketParams.TYPE);
            MarketParams l_marketParams = TestDBUtility.getMarketRow();
            l_marketParams.setMarketId(1002L);
            TestDBUtility.insertWithDel(l_marketParams);

            TestDBUtility.deleteAll(MarketPreferencesParams.TYPE);

            WEB3GentradeTradingCalendarModelImpl l_impl = new WEB3GentradeTradingCalendarModelImpl();
            Date l_datTradingCalendarDetails = l_impl.getCurrentBizDate(1001L);
            assertEquals(WEB3DateUtility.getDate("20071219", "yyyyMMdd"), l_datTradingCalendarDetails);
        }
        catch (Exception l_ex)
        {
            log.error(STR_METHOD_NAME, l_ex);
            log.exiting(STR_METHOD_NAME);
            fail();
        }

        log.exiting(STR_METHOD_NAME);
    }

    public void testGetCurrentBizDate1()
    {
        final String STR_METHOD_NAME = "testGetCurrentBizDate1()";
        log.entering(STR_METHOD_NAME);

        TestBaseForMock.MOCK_MANAGER.setIsMockUsed(true);

        WEB3GentradeTradingTimeManagementForMock.setSystemAttributes();
        WEB3GentradeTradingClendarContext l_clendarContext =
            (WEB3GentradeTradingClendarContext)
            ThreadLocalSystemAttributesRegistry.getAttribute("web3.tradingcalendarcontext");
        l_clendarContext.setBizdateCalcParameter("0");
        l_clendarContext.setBizDateType("1");
        ThreadLocalSystemAttributesRegistry.setAttribute("web3.tradingcalendarcontext", l_clendarContext);
        try
        {
            TestDBUtility.deleteAll(TradedProductParams.TYPE);
            TradedProductParams l_tradedProductParams = TestDBUtility.getTradedProductRow();
            l_tradedProductParams.setTradedProductId(1001L);
            l_tradedProductParams.setMarketId(1002L);
            TestDBUtility.insertWithDel(l_tradedProductParams);

            TestDBUtility.deleteAll(MarketParams.TYPE);
            MarketParams l_marketParams = TestDBUtility.getMarketRow();
            l_marketParams.setMarketId(1002L);
            TestDBUtility.insertWithDel(l_marketParams);

            TestDBUtility.deleteAll(MarketPreferencesParams.TYPE);
            MarketPreferencesParams l_marketPreferencesParams = TestDBUtility.getMarketPreferencesRow();
            l_marketPreferencesParams.setMarketId(1002L);
            l_marketPreferencesParams.setName("equity.pts.market.div");
            l_marketPreferencesParams.setNameSerialNo(1);
            l_marketPreferencesParams.setValue("1");
            TestDBUtility.insertWithDel(l_marketPreferencesParams);

            TestDBUtility.deleteAll(TradingTimeParams.TYPE);
            TradingTimeParams l_tradingTimeParams = TestDBUtility.getTradingTimeRow();
            l_tradingTimeParams.setBizDateType("1");
            l_tradingTimeParams.setProductCode("0");
            l_tradingTimeParams.setTradingTimeType("01");
            l_tradingTimeParams.setMarketCode("N1");
            l_tradingTimeParams.setBranchCode("123");
            l_tradingTimeParams.setInstitutionCode("0D");
            l_tradingTimeParams.setBizdateCalcParameter("0");
            TestDBUtility.insertWithDel(l_tradingTimeParams);

            l_tradingTimeParams.setBizDateType("5");
            l_tradingTimeParams.setProductCode("0");
            l_tradingTimeParams.setTradingTimeType("01");
            l_tradingTimeParams.setMarketCode("N1");
            l_tradingTimeParams.setBranchCode("123");
            l_tradingTimeParams.setInstitutionCode("0D");
            l_tradingTimeParams.setBizdateCalcParameter("0");
            TestDBUtility.insertWithDel(l_tradingTimeParams);

            l_tradingTimeParams.setBizDateType("4");
            l_tradingTimeParams.setProductCode("0");
            l_tradingTimeParams.setTradingTimeType("01");
            l_tradingTimeParams.setMarketCode("N1");
            l_tradingTimeParams.setBranchCode("123");
            l_tradingTimeParams.setInstitutionCode("0D");
            l_tradingTimeParams.setBizdateCalcParameter("0");
            TestDBUtility.insertWithDel(l_tradingTimeParams);

            WEB3GentradeTradingCalendarModelImpl l_impl = new WEB3GentradeTradingCalendarModelImpl();
            Date l_datTradingCalendarDetails = l_impl.getCurrentBizDate(1001L);
            assertEquals(
                WEB3DateUtility.formatDate(GtlUtils.getSystemTimestamp(), "yyyyMMdd"),
                WEB3DateUtility.formatDate(l_datTradingCalendarDetails, "yyyyMMdd"));
        }
        catch (Exception l_ex)
        {
            log.error(STR_METHOD_NAME, l_ex);
            log.exiting(STR_METHOD_NAME);
            fail();
        }

        log.exiting(STR_METHOD_NAME);
    }

}
@
