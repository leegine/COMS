head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.04.07.02.14.55;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	5444d9d078c63f4;
filename	WEB3MutualFixedBuyCloseDateDrawDateCalcTest.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@package webbroker3.mf;

import java.util.Date;

import com.fitechlabs.xtrade.plugin.tc.gentrade.data.SystemPreferencesParams;

import test.util.TestDBUtility;

import webbroker3.common.define.WEB3BizDateCalcParameterDef;
import webbroker3.common.define.WEB3BizDateTypeDef;
import webbroker3.common.define.WEB3EnableOrderDef;
import webbroker3.common.define.WEB3MarketCodeDef;
import webbroker3.common.define.WEB3ProductCodeDef;
import webbroker3.common.define.WEB3SystemPreferencesNameDef;
import webbroker3.common.define.WEB3TradingTimeTypeDef;
import webbroker3.gentrade.data.TradingTimeParams;
import webbroker3.gentrade.define.WEB3GentradeTimeDef;
import webbroker3.mf.service.delegate.stdimpls.WEB3MutualFixedBuyConditionListServiceImplTest;
import webbroker3.mock.TestBaseForMock;
import webbroker3.util.WEB3DateUtility;
import webbroker3.util.WEB3LogUtility;

public class WEB3MutualFixedBuyCloseDateDrawDateCalcTest extends TestBaseForMock
{

    /**
     * ログ出力ユーティリティ。
     */
    private static WEB3LogUtility log =
        WEB3LogUtility.getInstance(WEB3MutualFixedBuyConditionListServiceImplTest.class);

    public WEB3MutualFixedBuyCloseDateDrawDateCalcTest(String name)
    {
        super(name);
    }

    protected void setUp() throws Exception
    {
        super.setUp();
    }

    protected void tearDown() throws Exception
    {
        super.tearDown();
    }

    public void testCalcUsuallyCloseDate_C0001()
    {
        final String STR_METHOD_NAME = "testCalcUsuallyCloseDate_C0001()";
        log.entering(TEST_START + STR_METHOD_NAME);

        Date l_datReturnDate = null;
        try
        {
            TestDBUtility.deleteAll(SystemPreferencesParams.TYPE);
            SystemPreferencesParams l_systemPreferencesParams =
                TestDBUtility.getSystemPreferencesRow();
            l_systemPreferencesParams.setName("0D" + WEB3SystemPreferencesNameDef.SBS_DRAW_DD);
            l_systemPreferencesParams.setValue("07");
            TestDBUtility.insertWithDel(l_systemPreferencesParams);
            l_systemPreferencesParams =
                TestDBUtility.getSystemPreferencesRow();
            l_systemPreferencesParams.setName("0D" + WEB3SystemPreferencesNameDef.SBS_DAY_COUNT);
            l_systemPreferencesParams.setValue("02");
            TestDBUtility.insertWithDel(l_systemPreferencesParams);
            l_systemPreferencesParams =
                TestDBUtility.getSystemPreferencesRow();
            l_systemPreferencesParams.setName("0D" + WEB3SystemPreferencesNameDef.SBS_DRAW_DD_BONUS);
            l_systemPreferencesParams.setValue("05");
            TestDBUtility.insertWithDel(l_systemPreferencesParams);
            l_systemPreferencesParams =
                TestDBUtility.getSystemPreferencesRow();
            l_systemPreferencesParams.setName("0D" + WEB3SystemPreferencesNameDef.SBS_DAY_COUNT_BONUS);
            l_systemPreferencesParams.setValue("05");
            TestDBUtility.insertWithDel(l_systemPreferencesParams);

            Date l_datSelectMY = WEB3DateUtility.getDate("20080703", WEB3GentradeTimeDef.DATE_FORMAT_YMD);
            WEB3MutualFixedBuyCloseDateDrawDateCalc l_DrawDateCalc =
                new WEB3MutualFixedBuyCloseDateDrawDateCalc("0D", "381");

            l_datReturnDate = l_DrawDateCalc.calcUsuallyCloseDate(l_datSelectMY);

            int l_intResult = WEB3DateUtility.compareToDay(
                l_datReturnDate,
                WEB3DateUtility.getDate("20080703", WEB3GentradeTimeDef.DATE_FORMAT_YMD));
            assertEquals(l_intResult, 0);
        }
        catch (Exception l_ex)
        {
            
        }
        log.exiting(TEST_END + STR_METHOD_NAME);
    }

    public void testCalcUsuallyCloseDate_C0002()
    {
        final String STR_METHOD_NAME = "testCalcUsuallyCloseDate_C0002()";
        log.entering(TEST_START + STR_METHOD_NAME);

        Date l_datReturnDate = null;
        try
        {
            TestDBUtility.deleteAll(SystemPreferencesParams.TYPE);
            SystemPreferencesParams l_systemPreferencesParams =
                TestDBUtility.getSystemPreferencesRow();
            l_systemPreferencesParams.setName("0D" + WEB3SystemPreferencesNameDef.SBS_DRAW_DD);
            l_systemPreferencesParams.setValue("05");
            TestDBUtility.insertWithDel(l_systemPreferencesParams);
            l_systemPreferencesParams =
                TestDBUtility.getSystemPreferencesRow();
            l_systemPreferencesParams.setName("0D" + WEB3SystemPreferencesNameDef.SBS_DAY_COUNT);
            l_systemPreferencesParams.setValue("02");
            TestDBUtility.insertWithDel(l_systemPreferencesParams);
            l_systemPreferencesParams =
                TestDBUtility.getSystemPreferencesRow();
            l_systemPreferencesParams.setName("0D" + WEB3SystemPreferencesNameDef.SBS_DRAW_DD_BONUS);
            l_systemPreferencesParams.setValue("05");
            TestDBUtility.insertWithDel(l_systemPreferencesParams);
            l_systemPreferencesParams =
                TestDBUtility.getSystemPreferencesRow();
            l_systemPreferencesParams.setName("0D" + WEB3SystemPreferencesNameDef.SBS_DAY_COUNT_BONUS);
            l_systemPreferencesParams.setValue("05");
            TestDBUtility.insertWithDel(l_systemPreferencesParams);

            Date l_datSelectMY = WEB3DateUtility.getDate("20080703", WEB3GentradeTimeDef.DATE_FORMAT_YMD);
            WEB3MutualFixedBuyCloseDateDrawDateCalc l_DrawDateCalc =
                new WEB3MutualFixedBuyCloseDateDrawDateCalc("0D", "381");

            l_datReturnDate = l_DrawDateCalc.calcUsuallyCloseDate(l_datSelectMY);

            int l_intResult = WEB3DateUtility.compareToDay(
                l_datReturnDate,
                WEB3DateUtility.getDate("20080703", WEB3GentradeTimeDef.DATE_FORMAT_YMD));
            assertEquals(l_intResult, 0);
        }
        catch (Exception l_ex)
        {
            
        }
        log.exiting(TEST_END + STR_METHOD_NAME);
    }

    public void testCalcUsuallyDrawDate_C0001()
    {
        final String STR_METHOD_NAME = "testCalcUsuallyDrawDate_C0001()";
        log.entering(TEST_START + STR_METHOD_NAME);

        Date l_datReturnDate = null;
        try
        {
            TestDBUtility.deleteAll(SystemPreferencesParams.TYPE);
            SystemPreferencesParams l_systemPreferencesParams =
                TestDBUtility.getSystemPreferencesRow();
            l_systemPreferencesParams.setName("0D" + WEB3SystemPreferencesNameDef.SBS_DRAW_DD);
            l_systemPreferencesParams.setValue("08");
            TestDBUtility.insertWithDel(l_systemPreferencesParams);
            l_systemPreferencesParams =
                TestDBUtility.getSystemPreferencesRow();
            l_systemPreferencesParams.setName("0D" + WEB3SystemPreferencesNameDef.SBS_DAY_COUNT);
            l_systemPreferencesParams.setValue("05");
            TestDBUtility.insertWithDel(l_systemPreferencesParams);
            l_systemPreferencesParams =
                TestDBUtility.getSystemPreferencesRow();
            l_systemPreferencesParams.setName("0D" + WEB3SystemPreferencesNameDef.SBS_DRAW_DD_BONUS);
            l_systemPreferencesParams.setValue("05");
            TestDBUtility.insertWithDel(l_systemPreferencesParams);
            l_systemPreferencesParams =
                TestDBUtility.getSystemPreferencesRow();
            l_systemPreferencesParams.setName("0D" + WEB3SystemPreferencesNameDef.SBS_DAY_COUNT_BONUS);
            l_systemPreferencesParams.setValue("05");
            TestDBUtility.insertWithDel(l_systemPreferencesParams);

            TestDBUtility.deleteAll(TradingTimeParams.TYPE);
            TradingTimeParams l_tradingTimeParams = TestDBUtility.getTradingTimeRow();
            l_tradingTimeParams.setMarketCode(WEB3MarketCodeDef.DEFAULT);
            l_tradingTimeParams.setTradingTimeType(WEB3TradingTimeTypeDef.MF_FIXED_BUYING);
            l_tradingTimeParams.setProductCode(WEB3ProductCodeDef.DEFAULT);
            l_tradingTimeParams.setBizDateType(WEB3BizDateTypeDef.BIZ_DATE);
            l_tradingTimeParams.setEnableOrder(WEB3EnableOrderDef.ENABLE);
            l_tradingTimeParams.setBizdateCalcParameter(WEB3BizDateCalcParameterDef.NEXT_BIZ_DATE);
            TestDBUtility.insertWithDel(l_tradingTimeParams);

            Date l_datSelectMY = WEB3DateUtility.getDate("20080703", WEB3GentradeTimeDef.DATE_FORMAT_YMD);

            WEB3MutualFixedBuyCloseDateDrawDateCalc l_DrawDateCalc =
                new WEB3MutualFixedBuyCloseDateDrawDateCalc("0D", "381");

            l_datReturnDate = l_DrawDateCalc.calcUsuallyDrawDate(l_datSelectMY);

            int l_intResult = WEB3DateUtility.compareToDay(
                l_datReturnDate,
                WEB3DateUtility.getDate("20080708", WEB3GentradeTimeDef.DATE_FORMAT_YMD));
            assertEquals(l_intResult, 0);
        }
        catch (Exception l_ex)
        {
            log.error(l_ex.getMessage(), l_ex);
            fail();
        }

        log.exiting(TEST_END + STR_METHOD_NAME);
    }

    public void testCalcUsuallyDrawDate_C0002()
    {
        final String STR_METHOD_NAME = "testCalcUsuallyDrawDate_C0002()";
        log.entering(TEST_START + STR_METHOD_NAME);

        Date l_datReturnDate = null;
        try
        {
            TestDBUtility.deleteAll(SystemPreferencesParams.TYPE);
            SystemPreferencesParams l_systemPreferencesParams =
                TestDBUtility.getSystemPreferencesRow();
            l_systemPreferencesParams.setName("0D" + WEB3SystemPreferencesNameDef.SBS_DRAW_DD);
            l_systemPreferencesParams.setValue("05");
            TestDBUtility.insertWithDel(l_systemPreferencesParams);
            l_systemPreferencesParams =
                TestDBUtility.getSystemPreferencesRow();
            l_systemPreferencesParams.setName("0D" + WEB3SystemPreferencesNameDef.SBS_DAY_COUNT);
            l_systemPreferencesParams.setValue("05");
            TestDBUtility.insertWithDel(l_systemPreferencesParams);
            l_systemPreferencesParams =
                TestDBUtility.getSystemPreferencesRow();
            l_systemPreferencesParams.setName("0D" + WEB3SystemPreferencesNameDef.SBS_DRAW_DD_BONUS);
            l_systemPreferencesParams.setValue("05");
            TestDBUtility.insertWithDel(l_systemPreferencesParams);
            l_systemPreferencesParams =
                TestDBUtility.getSystemPreferencesRow();
            l_systemPreferencesParams.setName("0D" + WEB3SystemPreferencesNameDef.SBS_DAY_COUNT_BONUS);
            l_systemPreferencesParams.setValue("05");
            TestDBUtility.insertWithDel(l_systemPreferencesParams);

            TestDBUtility.deleteAll(TradingTimeParams.TYPE);
            TradingTimeParams l_tradingTimeParams = TestDBUtility.getTradingTimeRow();
            l_tradingTimeParams.setMarketCode(WEB3MarketCodeDef.DEFAULT);
            l_tradingTimeParams.setTradingTimeType(WEB3TradingTimeTypeDef.MF_FIXED_BUYING);
            l_tradingTimeParams.setProductCode(WEB3ProductCodeDef.DEFAULT);
            l_tradingTimeParams.setBizDateType(WEB3BizDateTypeDef.BIZ_DATE);
            l_tradingTimeParams.setEnableOrder(WEB3EnableOrderDef.ENABLE);
            l_tradingTimeParams.setBizdateCalcParameter(WEB3BizDateCalcParameterDef.NEXT_BIZ_DATE);
            TestDBUtility.insertWithDel(l_tradingTimeParams);

            Date l_datSelectMY = WEB3DateUtility.getDate("20080703", WEB3GentradeTimeDef.DATE_FORMAT_YMD);

            WEB3MutualFixedBuyCloseDateDrawDateCalc l_DrawDateCalc =
                new WEB3MutualFixedBuyCloseDateDrawDateCalc("0D", "381");

            l_datReturnDate = l_DrawDateCalc.calcUsuallyDrawDate(l_datSelectMY);

            int l_intResult = WEB3DateUtility.compareToDay(
                l_datReturnDate,
                WEB3DateUtility.getDate("20080707", WEB3GentradeTimeDef.DATE_FORMAT_YMD));
            assertEquals(l_intResult, 0);
        }
        catch (Exception l_ex)
        {
            log.error(l_ex.getMessage(), l_ex);
            fail();
        }

        log.exiting(TEST_END + STR_METHOD_NAME);
    }

    public void testCalcPrizeAndCloseDate_C0001()
    {
        final String STR_METHOD_NAME = "testCalcPrizeAndCloseDate_C0001()";
        log.entering(TEST_START + STR_METHOD_NAME);

        Date l_datReturnDate = null;
        try
        {
            TestDBUtility.deleteAll(SystemPreferencesParams.TYPE);
            SystemPreferencesParams l_systemPreferencesParams =
                TestDBUtility.getSystemPreferencesRow();
            l_systemPreferencesParams.setName("0D" + WEB3SystemPreferencesNameDef.SBS_DRAW_DD);
            l_systemPreferencesParams.setValue("05");
            TestDBUtility.insertWithDel(l_systemPreferencesParams);
            l_systemPreferencesParams =
                TestDBUtility.getSystemPreferencesRow();
            l_systemPreferencesParams.setName("0D" + WEB3SystemPreferencesNameDef.SBS_DAY_COUNT);
            l_systemPreferencesParams.setValue("05");
            TestDBUtility.insertWithDel(l_systemPreferencesParams);
            l_systemPreferencesParams =
                TestDBUtility.getSystemPreferencesRow();
            l_systemPreferencesParams.setName("0D" + WEB3SystemPreferencesNameDef.SBS_DRAW_DD_BONUS);
            l_systemPreferencesParams.setValue("07");
            TestDBUtility.insertWithDel(l_systemPreferencesParams);
            l_systemPreferencesParams =
                TestDBUtility.getSystemPreferencesRow();
            l_systemPreferencesParams.setName("0D" + WEB3SystemPreferencesNameDef.SBS_DAY_COUNT_BONUS);
            l_systemPreferencesParams.setValue("03");
            TestDBUtility.insertWithDel(l_systemPreferencesParams);

            TestDBUtility.deleteAll(TradingTimeParams.TYPE);
            TradingTimeParams l_tradingTimeParams = TestDBUtility.getTradingTimeRow();
            l_tradingTimeParams.setMarketCode(WEB3MarketCodeDef.DEFAULT);
            l_tradingTimeParams.setTradingTimeType(WEB3TradingTimeTypeDef.MF_FIXED_BUYING);
            l_tradingTimeParams.setProductCode(WEB3ProductCodeDef.DEFAULT);
            l_tradingTimeParams.setBizDateType(WEB3BizDateTypeDef.BIZ_DATE);
            l_tradingTimeParams.setEnableOrder(WEB3EnableOrderDef.ENABLE);
            l_tradingTimeParams.setBizdateCalcParameter(WEB3BizDateCalcParameterDef.NEXT_BIZ_DATE);
            TestDBUtility.insertWithDel(l_tradingTimeParams);

            Date l_datSelectMY = WEB3DateUtility.getDate("20080703", WEB3GentradeTimeDef.DATE_FORMAT_YMD);

            WEB3MutualFixedBuyCloseDateDrawDateCalc l_DrawDateCalc =
                new WEB3MutualFixedBuyCloseDateDrawDateCalc("0D", "381");

            l_datReturnDate = l_DrawDateCalc.calcPrizeAndCloseDate(l_datSelectMY);

            int l_intResult = WEB3DateUtility.compareToDay(
                l_datReturnDate,
                WEB3DateUtility.getDate("20080702", WEB3GentradeTimeDef.DATE_FORMAT_YMD));
            assertEquals(l_intResult, 0);
        }
        catch (Exception l_ex)
        {
            log.error(l_ex.getMessage(), l_ex);
            fail();
        }

        log.exiting(TEST_END + STR_METHOD_NAME);
    }

    public void testCalcPrizeAndCloseDate_C0002()
    {
        final String STR_METHOD_NAME = "testCalcPrizeAndCloseDate_C0002()";
        log.entering(TEST_START + STR_METHOD_NAME);

        Date l_datReturnDate = null;
        try
        {
            TestDBUtility.deleteAll(SystemPreferencesParams.TYPE);
            SystemPreferencesParams l_systemPreferencesParams =
                TestDBUtility.getSystemPreferencesRow();
            l_systemPreferencesParams.setName("0D" + WEB3SystemPreferencesNameDef.SBS_DRAW_DD);
            l_systemPreferencesParams.setValue("05");
            TestDBUtility.insertWithDel(l_systemPreferencesParams);
            l_systemPreferencesParams =
                TestDBUtility.getSystemPreferencesRow();
            l_systemPreferencesParams.setName("0D" + WEB3SystemPreferencesNameDef.SBS_DAY_COUNT);
            l_systemPreferencesParams.setValue("05");
            TestDBUtility.insertWithDel(l_systemPreferencesParams);
            l_systemPreferencesParams =
                TestDBUtility.getSystemPreferencesRow();
            l_systemPreferencesParams.setName("0D" + WEB3SystemPreferencesNameDef.SBS_DRAW_DD_BONUS);
            l_systemPreferencesParams.setValue("06");
            TestDBUtility.insertWithDel(l_systemPreferencesParams);
            l_systemPreferencesParams =
                TestDBUtility.getSystemPreferencesRow();
            l_systemPreferencesParams.setName("0D" + WEB3SystemPreferencesNameDef.SBS_DAY_COUNT_BONUS);
            l_systemPreferencesParams.setValue("03");
            TestDBUtility.insertWithDel(l_systemPreferencesParams);

            TestDBUtility.deleteAll(TradingTimeParams.TYPE);
            TradingTimeParams l_tradingTimeParams = TestDBUtility.getTradingTimeRow();
            l_tradingTimeParams.setMarketCode(WEB3MarketCodeDef.DEFAULT);
            l_tradingTimeParams.setTradingTimeType(WEB3TradingTimeTypeDef.MF_FIXED_BUYING);
            l_tradingTimeParams.setProductCode(WEB3ProductCodeDef.DEFAULT);
            l_tradingTimeParams.setBizDateType(WEB3BizDateTypeDef.BIZ_DATE);
            l_tradingTimeParams.setEnableOrder(WEB3EnableOrderDef.ENABLE);
            l_tradingTimeParams.setBizdateCalcParameter(WEB3BizDateCalcParameterDef.NEXT_BIZ_DATE);
            TestDBUtility.insertWithDel(l_tradingTimeParams);

            Date l_datSelectMY = WEB3DateUtility.getDate("20080703", WEB3GentradeTimeDef.DATE_FORMAT_YMD);

            WEB3MutualFixedBuyCloseDateDrawDateCalc l_DrawDateCalc =
                new WEB3MutualFixedBuyCloseDateDrawDateCalc("0D", "381");

            l_datReturnDate = l_DrawDateCalc.calcPrizeAndCloseDate(l_datSelectMY);

            int l_intResult = WEB3DateUtility.compareToDay(
                l_datReturnDate,
                WEB3DateUtility.getDate("20080702", WEB3GentradeTimeDef.DATE_FORMAT_YMD));
            assertEquals(l_intResult, 0);
        }
        catch (Exception l_ex)
        {
            log.error(l_ex.getMessage(), l_ex);
            fail();
        }

        log.exiting(TEST_END + STR_METHOD_NAME);
    }

    public void testCalcPrizeAndDrawDate_C0001()
    {
        final String STR_METHOD_NAME = "testCalcPrizeAndDrawDate_C0001()";
        log.entering(TEST_START + STR_METHOD_NAME);

        Date l_datReturnDate = null;
        try
        {
            TestDBUtility.deleteAll(SystemPreferencesParams.TYPE);
            SystemPreferencesParams l_systemPreferencesParams =
                TestDBUtility.getSystemPreferencesRow();
            l_systemPreferencesParams.setName("0D" + WEB3SystemPreferencesNameDef.SBS_DRAW_DD);
            l_systemPreferencesParams.setValue("05");
            TestDBUtility.insertWithDel(l_systemPreferencesParams);
            l_systemPreferencesParams =
                TestDBUtility.getSystemPreferencesRow();
            l_systemPreferencesParams.setName("0D" + WEB3SystemPreferencesNameDef.SBS_DAY_COUNT);
            l_systemPreferencesParams.setValue("05");
            TestDBUtility.insertWithDel(l_systemPreferencesParams);
            l_systemPreferencesParams =
                TestDBUtility.getSystemPreferencesRow();
            l_systemPreferencesParams.setName("0D" + WEB3SystemPreferencesNameDef.SBS_DRAW_DD_BONUS);
            l_systemPreferencesParams.setValue("08");
            TestDBUtility.insertWithDel(l_systemPreferencesParams);
            l_systemPreferencesParams =
                TestDBUtility.getSystemPreferencesRow();
            l_systemPreferencesParams.setName("0D" + WEB3SystemPreferencesNameDef.SBS_DAY_COUNT_BONUS);
            l_systemPreferencesParams.setValue("03");
            TestDBUtility.insertWithDel(l_systemPreferencesParams);

            TestDBUtility.deleteAll(TradingTimeParams.TYPE);
            TradingTimeParams l_tradingTimeParams = TestDBUtility.getTradingTimeRow();
            l_tradingTimeParams.setMarketCode(WEB3MarketCodeDef.DEFAULT);
            l_tradingTimeParams.setTradingTimeType(WEB3TradingTimeTypeDef.MF_FIXED_BUYING);
            l_tradingTimeParams.setProductCode(WEB3ProductCodeDef.DEFAULT);
            l_tradingTimeParams.setBizDateType(WEB3BizDateTypeDef.BIZ_DATE);
            l_tradingTimeParams.setEnableOrder(WEB3EnableOrderDef.ENABLE);
            l_tradingTimeParams.setBizdateCalcParameter(WEB3BizDateCalcParameterDef.NEXT_BIZ_DATE);
            TestDBUtility.insertWithDel(l_tradingTimeParams);

            Date l_datSelectMY = WEB3DateUtility.getDate("20080703", WEB3GentradeTimeDef.DATE_FORMAT_YMD);

            WEB3MutualFixedBuyCloseDateDrawDateCalc l_DrawDateCalc =
                new WEB3MutualFixedBuyCloseDateDrawDateCalc("0D", "381");

            l_datReturnDate = l_DrawDateCalc.calcPrizeAndDrawDate(l_datSelectMY);

            int l_intResult = WEB3DateUtility.compareToDay(
                l_datReturnDate,
                WEB3DateUtility.getDate("20080708", WEB3GentradeTimeDef.DATE_FORMAT_YMD));
            assertEquals(l_intResult, 0);
        }
        catch (Exception l_ex)
        {
            log.error(l_ex.getMessage(), l_ex);
            fail();
        }

        log.exiting(TEST_END + STR_METHOD_NAME);
    }

    public void testCalcPrizeAndDrawDate_C0002()
    {
        final String STR_METHOD_NAME = "testCalcPrizeAndDrawDate_C0002()";
        log.entering(TEST_START + STR_METHOD_NAME);

        Date l_datReturnDate = null;
        try
        {
            TestDBUtility.deleteAll(SystemPreferencesParams.TYPE);
            SystemPreferencesParams l_systemPreferencesParams =
                TestDBUtility.getSystemPreferencesRow();
            l_systemPreferencesParams.setName("0D" + WEB3SystemPreferencesNameDef.SBS_DRAW_DD);
            l_systemPreferencesParams.setValue("05");
            TestDBUtility.insertWithDel(l_systemPreferencesParams);
            l_systemPreferencesParams =
                TestDBUtility.getSystemPreferencesRow();
            l_systemPreferencesParams.setName("0D" + WEB3SystemPreferencesNameDef.SBS_DAY_COUNT);
            l_systemPreferencesParams.setValue("05");
            TestDBUtility.insertWithDel(l_systemPreferencesParams);
            l_systemPreferencesParams =
                TestDBUtility.getSystemPreferencesRow();
            l_systemPreferencesParams.setName("0D" + WEB3SystemPreferencesNameDef.SBS_DRAW_DD_BONUS);
            l_systemPreferencesParams.setValue("06");
            TestDBUtility.insertWithDel(l_systemPreferencesParams);
            l_systemPreferencesParams =
                TestDBUtility.getSystemPreferencesRow();
            l_systemPreferencesParams.setName("0D" + WEB3SystemPreferencesNameDef.SBS_DAY_COUNT_BONUS);
            l_systemPreferencesParams.setValue("03");
            TestDBUtility.insertWithDel(l_systemPreferencesParams);

            TestDBUtility.deleteAll(TradingTimeParams.TYPE);
            TradingTimeParams l_tradingTimeParams = TestDBUtility.getTradingTimeRow();
            l_tradingTimeParams.setMarketCode(WEB3MarketCodeDef.DEFAULT);
            l_tradingTimeParams.setTradingTimeType(WEB3TradingTimeTypeDef.MF_FIXED_BUYING);
            l_tradingTimeParams.setProductCode(WEB3ProductCodeDef.DEFAULT);
            l_tradingTimeParams.setBizDateType(WEB3BizDateTypeDef.BIZ_DATE);
            l_tradingTimeParams.setEnableOrder(WEB3EnableOrderDef.ENABLE);
            l_tradingTimeParams.setBizdateCalcParameter(WEB3BizDateCalcParameterDef.NEXT_BIZ_DATE);
            TestDBUtility.insertWithDel(l_tradingTimeParams);

            Date l_datSelectMY = WEB3DateUtility.getDate("20080703", WEB3GentradeTimeDef.DATE_FORMAT_YMD);

            WEB3MutualFixedBuyCloseDateDrawDateCalc l_DrawDateCalc =
                new WEB3MutualFixedBuyCloseDateDrawDateCalc("0D", "381");

            l_datReturnDate = l_DrawDateCalc.calcPrizeAndDrawDate(l_datSelectMY);

            int l_intResult = WEB3DateUtility.compareToDay(
                l_datReturnDate,
                WEB3DateUtility.getDate("20080707", WEB3GentradeTimeDef.DATE_FORMAT_YMD));
            assertEquals(l_intResult, 0);
        }
        catch (Exception l_ex)
        {
            log.error(l_ex.getMessage(), l_ex);
            fail();
        }

        log.exiting(TEST_END + STR_METHOD_NAME);
    }

    public void testCalcUsuallyCloseDateTime_C0001()
    {
        final String STR_METHOD_NAME = "testCalcUsuallyCloseDateTime_C0001()";
        log.entering(TEST_START + STR_METHOD_NAME);

        Date l_datReturnDate = null;
        try
        {
            TestDBUtility.deleteAll(SystemPreferencesParams.TYPE);
            SystemPreferencesParams l_systemPreferencesParams =
                TestDBUtility.getSystemPreferencesRow();
            l_systemPreferencesParams.setName("0D" + WEB3SystemPreferencesNameDef.SBS_DRAW_DD);
            l_systemPreferencesParams.setValue("05");
            TestDBUtility.insertWithDel(l_systemPreferencesParams);
            l_systemPreferencesParams =
                TestDBUtility.getSystemPreferencesRow();
            l_systemPreferencesParams.setName("0D" + WEB3SystemPreferencesNameDef.SBS_DAY_COUNT);
            l_systemPreferencesParams.setValue("02");
            TestDBUtility.insertWithDel(l_systemPreferencesParams);
            l_systemPreferencesParams =
                TestDBUtility.getSystemPreferencesRow();
            l_systemPreferencesParams.setName("0D" + WEB3SystemPreferencesNameDef.SBS_DRAW_DD_BONUS);
            l_systemPreferencesParams.setValue("05");
            TestDBUtility.insertWithDel(l_systemPreferencesParams);
            l_systemPreferencesParams =
                TestDBUtility.getSystemPreferencesRow();
            l_systemPreferencesParams.setName("0D" + WEB3SystemPreferencesNameDef.SBS_DAY_COUNT_BONUS);
            l_systemPreferencesParams.setValue("05");
            TestDBUtility.insertWithDel(l_systemPreferencesParams);

            TestDBUtility.deleteAll(TradingTimeParams.TYPE);
            TradingTimeParams l_tradingTimeParams = TestDBUtility.getTradingTimeRow();
            l_tradingTimeParams.setMarketCode(WEB3MarketCodeDef.DEFAULT);
            l_tradingTimeParams.setTradingTimeType(WEB3TradingTimeTypeDef.MF_FIXED_BUYING);
            l_tradingTimeParams.setProductCode(WEB3ProductCodeDef.DEFAULT);
            l_tradingTimeParams.setBizDateType(WEB3BizDateTypeDef.BIZ_DATE);
            l_tradingTimeParams.setEnableOrder(WEB3EnableOrderDef.ENABLE);
            l_tradingTimeParams.setBizdateCalcParameter(WEB3BizDateCalcParameterDef.NEXT_BIZ_DATE);
            l_tradingTimeParams.setStartTime("050201");
            TestDBUtility.insertWithDel(l_tradingTimeParams);

            Date l_datSelectMY = WEB3DateUtility.getDate("20080703", WEB3GentradeTimeDef.DATE_FORMAT_YMD);

            WEB3MutualFixedBuyCloseDateDrawDateCalc l_DrawDateCalc =
                new WEB3MutualFixedBuyCloseDateDrawDateCalc("0D", "381");

            l_datReturnDate = l_DrawDateCalc.calcUsuallyCloseDateTime(l_datSelectMY);

            int l_intResult = WEB3DateUtility.compareToDay(
                l_datReturnDate,
                WEB3DateUtility.getDate("20080703050201",
                    WEB3GentradeTimeDef.DATE_FORMAT_YMD + WEB3GentradeTimeDef.TIME_FORMAT_HMS));
            assertEquals(l_intResult, 0);
        }
        catch (Exception l_ex)
        {
            log.error(l_ex.getMessage(), l_ex);
            fail();
        }

        log.exiting(TEST_END + STR_METHOD_NAME);
    }

    public void testCalcUsuallyCloseDateTime_C0002()
    {
        final String STR_METHOD_NAME = "testCalcUsuallyCloseDateTime_C0002()";
        log.entering(TEST_START + STR_METHOD_NAME);

        Date l_datReturnDate = null;
        try
        {
            TestDBUtility.deleteAll(SystemPreferencesParams.TYPE);
            SystemPreferencesParams l_systemPreferencesParams =
                TestDBUtility.getSystemPreferencesRow();
            l_systemPreferencesParams.setName("0D" + WEB3SystemPreferencesNameDef.SBS_DRAW_DD);
            l_systemPreferencesParams.setValue("05");
            TestDBUtility.insertWithDel(l_systemPreferencesParams);
            l_systemPreferencesParams =
                TestDBUtility.getSystemPreferencesRow();
            l_systemPreferencesParams.setName("0D" + WEB3SystemPreferencesNameDef.SBS_DAY_COUNT);
            l_systemPreferencesParams.setValue("02");
            TestDBUtility.insertWithDel(l_systemPreferencesParams);
            l_systemPreferencesParams =
                TestDBUtility.getSystemPreferencesRow();
            l_systemPreferencesParams.setName("0D" + WEB3SystemPreferencesNameDef.SBS_DRAW_DD_BONUS);
            l_systemPreferencesParams.setValue("05");
            TestDBUtility.insertWithDel(l_systemPreferencesParams);
            l_systemPreferencesParams =
                TestDBUtility.getSystemPreferencesRow();
            l_systemPreferencesParams.setName("0D" + WEB3SystemPreferencesNameDef.SBS_DAY_COUNT_BONUS);
            l_systemPreferencesParams.setValue("05");
            TestDBUtility.insertWithDel(l_systemPreferencesParams);

            TestDBUtility.deleteAll(TradingTimeParams.TYPE);
            TradingTimeParams l_tradingTimeParams = TestDBUtility.getTradingTimeRow();
            l_tradingTimeParams.setMarketCode(WEB3MarketCodeDef.DEFAULT);
            l_tradingTimeParams.setTradingTimeType(WEB3TradingTimeTypeDef.MF_FIXED_BUYING);
            l_tradingTimeParams.setProductCode(WEB3ProductCodeDef.DEFAULT);
            l_tradingTimeParams.setBizDateType(WEB3BizDateTypeDef.BIZ_DATE);
            l_tradingTimeParams.setEnableOrder(WEB3EnableOrderDef.ENABLE);
            l_tradingTimeParams.setBizdateCalcParameter(WEB3BizDateCalcParameterDef.NEXT_BIZ_DATE);
            l_tradingTimeParams.setStartTime("120210");
            TestDBUtility.insertWithDel(l_tradingTimeParams);

            Date l_datSelectMY = WEB3DateUtility.getDate("20080703", WEB3GentradeTimeDef.DATE_FORMAT_YMD);

            WEB3MutualFixedBuyCloseDateDrawDateCalc l_DrawDateCalc =
                new WEB3MutualFixedBuyCloseDateDrawDateCalc("0D", "381");

            l_datReturnDate = l_DrawDateCalc.calcUsuallyCloseDateTime(l_datSelectMY);

            int l_intResult = WEB3DateUtility.compareToDay(
                l_datReturnDate,
                WEB3DateUtility.getDate("20080703120210",
                    WEB3GentradeTimeDef.DATE_FORMAT_YMD + WEB3GentradeTimeDef.TIME_FORMAT_HMS));
            assertEquals(l_intResult, 0);
        }
        catch (Exception l_ex)
        {
            log.error(l_ex.getMessage(), l_ex);
            fail();
        }

        log.exiting(TEST_END + STR_METHOD_NAME);
    }

    public void testCalcUsuallyCloseDateTime_C0003()
    {
        final String STR_METHOD_NAME = "testCalcUsuallyCloseDateTime_C0003()";
        log.entering(TEST_START + STR_METHOD_NAME);

        Date l_datReturnDate = null;
        try
        {
            TestDBUtility.deleteAll(SystemPreferencesParams.TYPE);
            SystemPreferencesParams l_systemPreferencesParams =
                TestDBUtility.getSystemPreferencesRow();
            l_systemPreferencesParams.setName("0D" + WEB3SystemPreferencesNameDef.SBS_DRAW_DD);
            l_systemPreferencesParams.setValue("09");
            TestDBUtility.insertWithDel(l_systemPreferencesParams);
            l_systemPreferencesParams =
                TestDBUtility.getSystemPreferencesRow();
            l_systemPreferencesParams.setName("0D" + WEB3SystemPreferencesNameDef.SBS_DAY_COUNT);
            l_systemPreferencesParams.setValue("02");
            TestDBUtility.insertWithDel(l_systemPreferencesParams);
            l_systemPreferencesParams =
                TestDBUtility.getSystemPreferencesRow();
            l_systemPreferencesParams.setName("0D" + WEB3SystemPreferencesNameDef.SBS_DRAW_DD_BONUS);
            l_systemPreferencesParams.setValue("05");
            TestDBUtility.insertWithDel(l_systemPreferencesParams);
            l_systemPreferencesParams =
                TestDBUtility.getSystemPreferencesRow();
            l_systemPreferencesParams.setName("0D" + WEB3SystemPreferencesNameDef.SBS_DAY_COUNT_BONUS);
            l_systemPreferencesParams.setValue("05");
            TestDBUtility.insertWithDel(l_systemPreferencesParams);

            TestDBUtility.deleteAll(TradingTimeParams.TYPE);
            TradingTimeParams l_tradingTimeParams = TestDBUtility.getTradingTimeRow();
            l_tradingTimeParams.setMarketCode(WEB3MarketCodeDef.DEFAULT);
            l_tradingTimeParams.setTradingTimeType(WEB3TradingTimeTypeDef.MF_FIXED_BUYING);
            l_tradingTimeParams.setProductCode(WEB3ProductCodeDef.DEFAULT);
            l_tradingTimeParams.setBizDateType(WEB3BizDateTypeDef.BIZ_DATE);
            l_tradingTimeParams.setEnableOrder(WEB3EnableOrderDef.ENABLE);
            l_tradingTimeParams.setBizdateCalcParameter(WEB3BizDateCalcParameterDef.NEXT_BIZ_DATE);
            l_tradingTimeParams.setStartTime("120210");
            TestDBUtility.insertWithDel(l_tradingTimeParams);

            Date l_datSelectMY = WEB3DateUtility.getDate("20080703", WEB3GentradeTimeDef.DATE_FORMAT_YMD);

            WEB3MutualFixedBuyCloseDateDrawDateCalc l_DrawDateCalc =
                new WEB3MutualFixedBuyCloseDateDrawDateCalc("0D", "381");

            l_datReturnDate = l_DrawDateCalc.calcUsuallyCloseDateTime(l_datSelectMY);

            int l_intResult = WEB3DateUtility.compareToDay(
                l_datReturnDate,
                WEB3DateUtility.getDate("20080707120210",
                    WEB3GentradeTimeDef.DATE_FORMAT_YMD + WEB3GentradeTimeDef.TIME_FORMAT_HMS));
            assertEquals(l_intResult, 0);
        }
        catch (Exception l_ex)
        {
            log.error(l_ex.getMessage(), l_ex);
            fail();
        }

        log.exiting(TEST_END + STR_METHOD_NAME);
    }

    public void testCalcUsuallyCloseDateTime_C0004()
    {
        final String STR_METHOD_NAME = "testCalcUsuallyCloseDateTime_C0004()";
        log.entering(TEST_START + STR_METHOD_NAME);

        Date l_datReturnDate = null;
        try
        {
            TestDBUtility.deleteAll(SystemPreferencesParams.TYPE);
            SystemPreferencesParams l_systemPreferencesParams =
                TestDBUtility.getSystemPreferencesRow();
            l_systemPreferencesParams.setName("0D" + WEB3SystemPreferencesNameDef.SBS_DRAW_DD);
            l_systemPreferencesParams.setValue("09");
            TestDBUtility.insertWithDel(l_systemPreferencesParams);
            l_systemPreferencesParams =
                TestDBUtility.getSystemPreferencesRow();
            l_systemPreferencesParams.setName("0D" + WEB3SystemPreferencesNameDef.SBS_DAY_COUNT);
            l_systemPreferencesParams.setValue("02");
            TestDBUtility.insertWithDel(l_systemPreferencesParams);
            l_systemPreferencesParams =
                TestDBUtility.getSystemPreferencesRow();
            l_systemPreferencesParams.setName("0D" + WEB3SystemPreferencesNameDef.SBS_DRAW_DD_BONUS);
            l_systemPreferencesParams.setValue("05");
            TestDBUtility.insertWithDel(l_systemPreferencesParams);
            l_systemPreferencesParams =
                TestDBUtility.getSystemPreferencesRow();
            l_systemPreferencesParams.setName("0D" + WEB3SystemPreferencesNameDef.SBS_DAY_COUNT_BONUS);
            l_systemPreferencesParams.setValue("05");
            TestDBUtility.insertWithDel(l_systemPreferencesParams);

            TestDBUtility.deleteAll(TradingTimeParams.TYPE);
            TradingTimeParams l_tradingTimeParams = TestDBUtility.getTradingTimeRow();
            l_tradingTimeParams.setMarketCode(WEB3MarketCodeDef.DEFAULT);
            l_tradingTimeParams.setTradingTimeType(WEB3TradingTimeTypeDef.MF_FIXED_BUYING);
            l_tradingTimeParams.setProductCode(WEB3ProductCodeDef.DEFAULT);
            l_tradingTimeParams.setBizDateType(WEB3BizDateTypeDef.BIZ_DATE);
            l_tradingTimeParams.setEnableOrder(WEB3EnableOrderDef.ENABLE);
            l_tradingTimeParams.setBizdateCalcParameter(WEB3BizDateCalcParameterDef.NEXT_BIZ_DATE);
            l_tradingTimeParams.setStartTime("020210");
            TestDBUtility.insertWithDel(l_tradingTimeParams);

            Date l_datSelectMY = WEB3DateUtility.getDate("20080703", WEB3GentradeTimeDef.DATE_FORMAT_YMD);

            WEB3MutualFixedBuyCloseDateDrawDateCalc l_DrawDateCalc =
                new WEB3MutualFixedBuyCloseDateDrawDateCalc("0D", "381");

            l_datReturnDate = l_DrawDateCalc.calcUsuallyCloseDateTime(l_datSelectMY);

            int l_intResult = WEB3DateUtility.compareToDay(
                l_datReturnDate,
                WEB3DateUtility.getDate("20080707020210",
                    WEB3GentradeTimeDef.DATE_FORMAT_YMD + WEB3GentradeTimeDef.TIME_FORMAT_HMS));
            assertEquals(l_intResult, 0);
        }
        catch (Exception l_ex)
        {
            log.error(l_ex.getMessage(), l_ex);
            fail();
        }

        log.exiting(TEST_END + STR_METHOD_NAME);
    }

    public void testCalcPrizeAndCloseDateHour_C0001()
    {
        final String STR_METHOD_NAME = "testCalcPrizeAndCloseDateHour_C0001()";
        log.entering(TEST_START + STR_METHOD_NAME);

        Date l_datReturnDate = null;
        try
        {
            TestDBUtility.deleteAll(SystemPreferencesParams.TYPE);
            SystemPreferencesParams l_systemPreferencesParams =
                TestDBUtility.getSystemPreferencesRow();
            l_systemPreferencesParams.setName("0D" + WEB3SystemPreferencesNameDef.SBS_DRAW_DD);
            l_systemPreferencesParams.setValue("05");
            TestDBUtility.insertWithDel(l_systemPreferencesParams);
            l_systemPreferencesParams =
                TestDBUtility.getSystemPreferencesRow();
            l_systemPreferencesParams.setName("0D" + WEB3SystemPreferencesNameDef.SBS_DAY_COUNT);
            l_systemPreferencesParams.setValue("05");
            TestDBUtility.insertWithDel(l_systemPreferencesParams);
            l_systemPreferencesParams =
                TestDBUtility.getSystemPreferencesRow();
            l_systemPreferencesParams.setName("0D" + WEB3SystemPreferencesNameDef.SBS_DRAW_DD_BONUS);
            l_systemPreferencesParams.setValue("06");
            TestDBUtility.insertWithDel(l_systemPreferencesParams);
            l_systemPreferencesParams =
                TestDBUtility.getSystemPreferencesRow();
            l_systemPreferencesParams.setName("0D" + WEB3SystemPreferencesNameDef.SBS_DAY_COUNT_BONUS);
            l_systemPreferencesParams.setValue("04");
            TestDBUtility.insertWithDel(l_systemPreferencesParams);

            TestDBUtility.deleteAll(TradingTimeParams.TYPE);
            TradingTimeParams l_tradingTimeParams = TestDBUtility.getTradingTimeRow();
            l_tradingTimeParams.setMarketCode(WEB3MarketCodeDef.DEFAULT);
            l_tradingTimeParams.setTradingTimeType(WEB3TradingTimeTypeDef.MF_FIXED_BUYING);
            l_tradingTimeParams.setProductCode(WEB3ProductCodeDef.DEFAULT);
            l_tradingTimeParams.setBizDateType(WEB3BizDateTypeDef.BIZ_DATE);
            l_tradingTimeParams.setEnableOrder(WEB3EnableOrderDef.ENABLE);
            l_tradingTimeParams.setBizdateCalcParameter(WEB3BizDateCalcParameterDef.NEXT_BIZ_DATE);
            l_tradingTimeParams.setStartTime("040001");
            TestDBUtility.insertWithDel(l_tradingTimeParams);

            Date l_datSelectMY = WEB3DateUtility.getDate("20080703", WEB3GentradeTimeDef.DATE_FORMAT_YMD);

            WEB3MutualFixedBuyCloseDateDrawDateCalc l_DrawDateCalc =
                new WEB3MutualFixedBuyCloseDateDrawDateCalc("0D", "381");

            l_datReturnDate = l_DrawDateCalc.calcPrizeAndCloseDateHour(l_datSelectMY);

            int l_intResult = WEB3DateUtility.compareToDay(
                l_datReturnDate,
                WEB3DateUtility.getDate("20080701040001",
                    WEB3GentradeTimeDef.DATE_FORMAT_YMD + WEB3GentradeTimeDef.TIME_FORMAT_HMS));
            assertEquals(l_intResult, 0);
        }
        catch (Exception l_ex)
        {
            log.error(l_ex.getMessage(), l_ex);
            fail();
        }

        log.exiting(TEST_END + STR_METHOD_NAME);
    }

    public void testCalcPrizeAndCloseDateHour_C0002()
    {
        final String STR_METHOD_NAME = "testCalcPrizeAndCloseDateHour_C0002()";
        log.entering(TEST_START + STR_METHOD_NAME);

        Date l_datReturnDate = null;
        try
        {
            TestDBUtility.deleteAll(SystemPreferencesParams.TYPE);
            SystemPreferencesParams l_systemPreferencesParams =
                TestDBUtility.getSystemPreferencesRow();
            l_systemPreferencesParams.setName("0D" + WEB3SystemPreferencesNameDef.SBS_DRAW_DD);
            l_systemPreferencesParams.setValue("05");
            TestDBUtility.insertWithDel(l_systemPreferencesParams);
            l_systemPreferencesParams =
                TestDBUtility.getSystemPreferencesRow();
            l_systemPreferencesParams.setName("0D" + WEB3SystemPreferencesNameDef.SBS_DAY_COUNT);
            l_systemPreferencesParams.setValue("05");
            TestDBUtility.insertWithDel(l_systemPreferencesParams);
            l_systemPreferencesParams =
                TestDBUtility.getSystemPreferencesRow();
            l_systemPreferencesParams.setName("0D" + WEB3SystemPreferencesNameDef.SBS_DRAW_DD_BONUS);
            l_systemPreferencesParams.setValue("06");
            TestDBUtility.insertWithDel(l_systemPreferencesParams);
            l_systemPreferencesParams =
                TestDBUtility.getSystemPreferencesRow();
            l_systemPreferencesParams.setName("0D" + WEB3SystemPreferencesNameDef.SBS_DAY_COUNT_BONUS);
            l_systemPreferencesParams.setValue("04");
            TestDBUtility.insertWithDel(l_systemPreferencesParams);

            TestDBUtility.deleteAll(TradingTimeParams.TYPE);
            TradingTimeParams l_tradingTimeParams = TestDBUtility.getTradingTimeRow();
            l_tradingTimeParams.setMarketCode(WEB3MarketCodeDef.DEFAULT);
            l_tradingTimeParams.setTradingTimeType(WEB3TradingTimeTypeDef.MF_FIXED_BUYING);
            l_tradingTimeParams.setProductCode(WEB3ProductCodeDef.DEFAULT);
            l_tradingTimeParams.setBizDateType(WEB3BizDateTypeDef.BIZ_DATE);
            l_tradingTimeParams.setEnableOrder(WEB3EnableOrderDef.ENABLE);
            l_tradingTimeParams.setBizdateCalcParameter(WEB3BizDateCalcParameterDef.NEXT_BIZ_DATE);
            l_tradingTimeParams.setStartTime("120210");
            TestDBUtility.insertWithDel(l_tradingTimeParams);

            Date l_datSelectMY = WEB3DateUtility.getDate("20080703", WEB3GentradeTimeDef.DATE_FORMAT_YMD);

            WEB3MutualFixedBuyCloseDateDrawDateCalc l_DrawDateCalc =
                new WEB3MutualFixedBuyCloseDateDrawDateCalc("0D", "381");

            l_datReturnDate = l_DrawDateCalc.calcPrizeAndCloseDateHour(l_datSelectMY);

            int l_intResult = WEB3DateUtility.compareToDay(
                l_datReturnDate,
                WEB3DateUtility.getDate("20080701120210",
                    WEB3GentradeTimeDef.DATE_FORMAT_YMD + WEB3GentradeTimeDef.TIME_FORMAT_HMS));
            assertEquals(l_intResult, 0);
        }
        catch (Exception l_ex)
        {
            log.error(l_ex.getMessage(), l_ex);
            fail();
        }

        log.exiting(TEST_END + STR_METHOD_NAME);
    }

    public void testCalcPrizeAndCloseDateHour_C0003()
    {
        final String STR_METHOD_NAME = "testCalcPrizeAndCloseDateHour_C0003()";
        log.entering(TEST_START + STR_METHOD_NAME);

        Date l_datReturnDate = null;
        try
        {
            TestDBUtility.deleteAll(SystemPreferencesParams.TYPE);
            SystemPreferencesParams l_systemPreferencesParams =
                TestDBUtility.getSystemPreferencesRow();
            l_systemPreferencesParams.setName("0D" + WEB3SystemPreferencesNameDef.SBS_DRAW_DD);
            l_systemPreferencesParams.setValue("05");
            TestDBUtility.insertWithDel(l_systemPreferencesParams);
            l_systemPreferencesParams =
                TestDBUtility.getSystemPreferencesRow();
            l_systemPreferencesParams.setName("0D" + WEB3SystemPreferencesNameDef.SBS_DAY_COUNT);
            l_systemPreferencesParams.setValue("05");
            TestDBUtility.insertWithDel(l_systemPreferencesParams);
            l_systemPreferencesParams =
                TestDBUtility.getSystemPreferencesRow();
            l_systemPreferencesParams.setName("0D" + WEB3SystemPreferencesNameDef.SBS_DRAW_DD_BONUS);
            l_systemPreferencesParams.setValue("07");
            TestDBUtility.insertWithDel(l_systemPreferencesParams);
            l_systemPreferencesParams =
                TestDBUtility.getSystemPreferencesRow();
            l_systemPreferencesParams.setName("0D" + WEB3SystemPreferencesNameDef.SBS_DAY_COUNT_BONUS);
            l_systemPreferencesParams.setValue("02");
            TestDBUtility.insertWithDel(l_systemPreferencesParams);

            TestDBUtility.deleteAll(TradingTimeParams.TYPE);
            TradingTimeParams l_tradingTimeParams = TestDBUtility.getTradingTimeRow();
            l_tradingTimeParams.setMarketCode(WEB3MarketCodeDef.DEFAULT);
            l_tradingTimeParams.setTradingTimeType(WEB3TradingTimeTypeDef.MF_FIXED_BUYING);
            l_tradingTimeParams.setProductCode(WEB3ProductCodeDef.DEFAULT);
            l_tradingTimeParams.setBizDateType(WEB3BizDateTypeDef.BIZ_DATE);
            l_tradingTimeParams.setEnableOrder(WEB3EnableOrderDef.ENABLE);
            l_tradingTimeParams.setBizdateCalcParameter(WEB3BizDateCalcParameterDef.NEXT_BIZ_DATE);
            l_tradingTimeParams.setStartTime("120210");
            TestDBUtility.insertWithDel(l_tradingTimeParams);

            Date l_datSelectMY = WEB3DateUtility.getDate("20080703", WEB3GentradeTimeDef.DATE_FORMAT_YMD);

            WEB3MutualFixedBuyCloseDateDrawDateCalc l_DrawDateCalc =
                new WEB3MutualFixedBuyCloseDateDrawDateCalc("0D", "381");

            l_datReturnDate = l_DrawDateCalc.calcPrizeAndCloseDateHour(l_datSelectMY);

            int l_intResult = WEB3DateUtility.compareToDay(
                l_datReturnDate,
                WEB3DateUtility.getDate("20080703120210",
                    WEB3GentradeTimeDef.DATE_FORMAT_YMD + WEB3GentradeTimeDef.TIME_FORMAT_HMS));
            assertEquals(l_intResult, 0);
        }
        catch (Exception l_ex)
        {
            log.error(l_ex.getMessage(), l_ex);
            fail();
        }

        log.exiting(TEST_END + STR_METHOD_NAME);
    }

    public void testCalcPrizeAndCloseDateHour_C0004()
    {
        final String STR_METHOD_NAME = "testCalcPrizeAndCloseDateHour_C0004()";
        log.entering(TEST_START + STR_METHOD_NAME);

        Date l_datReturnDate = null;
        try
        {
            TestDBUtility.deleteAll(SystemPreferencesParams.TYPE);
            SystemPreferencesParams l_systemPreferencesParams =
                TestDBUtility.getSystemPreferencesRow();
            l_systemPreferencesParams.setName("0D" + WEB3SystemPreferencesNameDef.SBS_DRAW_DD);
            l_systemPreferencesParams.setValue("05");
            TestDBUtility.insertWithDel(l_systemPreferencesParams);
            l_systemPreferencesParams =
                TestDBUtility.getSystemPreferencesRow();
            l_systemPreferencesParams.setName("0D" + WEB3SystemPreferencesNameDef.SBS_DAY_COUNT);
            l_systemPreferencesParams.setValue("05");
            TestDBUtility.insertWithDel(l_systemPreferencesParams);
            l_systemPreferencesParams =
                TestDBUtility.getSystemPreferencesRow();
            l_systemPreferencesParams.setName("0D" + WEB3SystemPreferencesNameDef.SBS_DRAW_DD_BONUS);
            l_systemPreferencesParams.setValue("07");
            TestDBUtility.insertWithDel(l_systemPreferencesParams);
            l_systemPreferencesParams =
                TestDBUtility.getSystemPreferencesRow();
            l_systemPreferencesParams.setName("0D" + WEB3SystemPreferencesNameDef.SBS_DAY_COUNT_BONUS);
            l_systemPreferencesParams.setValue("02");
            TestDBUtility.insertWithDel(l_systemPreferencesParams);

            TestDBUtility.deleteAll(TradingTimeParams.TYPE);
            TradingTimeParams l_tradingTimeParams = TestDBUtility.getTradingTimeRow();
            l_tradingTimeParams.setMarketCode(WEB3MarketCodeDef.DEFAULT);
            l_tradingTimeParams.setTradingTimeType(WEB3TradingTimeTypeDef.MF_FIXED_BUYING);
            l_tradingTimeParams.setProductCode(WEB3ProductCodeDef.DEFAULT);
            l_tradingTimeParams.setBizDateType(WEB3BizDateTypeDef.BIZ_DATE);
            l_tradingTimeParams.setEnableOrder(WEB3EnableOrderDef.ENABLE);
            l_tradingTimeParams.setBizdateCalcParameter(WEB3BizDateCalcParameterDef.NEXT_BIZ_DATE);
            l_tradingTimeParams.setStartTime("020210");
            TestDBUtility.insertWithDel(l_tradingTimeParams);

            Date l_datSelectMY = WEB3DateUtility.getDate("20080703", WEB3GentradeTimeDef.DATE_FORMAT_YMD);

            WEB3MutualFixedBuyCloseDateDrawDateCalc l_DrawDateCalc =
                new WEB3MutualFixedBuyCloseDateDrawDateCalc("0D", "381");

            l_datReturnDate = l_DrawDateCalc.calcPrizeAndCloseDateHour(l_datSelectMY);

            int l_intResult = WEB3DateUtility.compareToDay(
                l_datReturnDate,
                WEB3DateUtility.getDate("20080703020210",
                    WEB3GentradeTimeDef.DATE_FORMAT_YMD + WEB3GentradeTimeDef.TIME_FORMAT_HMS));
            assertEquals(l_intResult, 0);
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
