head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.04.07.01.22.37;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	5444d9d078c63f4;
filename	WEB3GentradeHandlingOrderCondTest.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : 取扱可能注文条件テスト(WEB3GentradeHandlingOrderCondTest.java)
Author Name      : Daiwa Institute of Research
Revision History : 2007/06/19  栄イ (中訊) 新規作成
*/
package webbroker3.gentrade;

import java.sql.Timestamp;
import java.util.Date;

import com.fitechlabs.xtrade.kernel.util.ThreadLocalSystemAttributesRegistry;
import com.fitechlabs.xtrade.plugin.tc.gentrade.ProductTypeEnum;

import test.util.TestDBUtility;

import webbroker3.common.WEB3BusinessLayerException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.gentrade.data.EnableOrderConditionParams;
import webbroker3.gentrade.data.EnableOrderConditionRow;
import webbroker3.gentrade.data.TradingTimeParams;
import webbroker3.gentrade.data.TradingTimeRow;
import webbroker3.mock.TestBaseForMock;
import webbroker3.util.WEB3DateUtility;
import webbroker3.util.WEB3LogUtility;

/**
 * (取扱可能注文条件テスト)<BR>
 * 
 * @@author 栄イ (中訊)
 * @@version 1.0
 */
public class WEB3GentradeHandlingOrderCondTest extends TestBaseForMock
{
    /**
     * ログユーティリティ<BR>
     */
    private static WEB3LogUtility log = WEB3LogUtility.getInstance(
        WEB3GentradeHandlingOrderCondTest.class);

    public WEB3GentradeHandlingOrderCondTest(String arg0)
    {
        super(arg0);
    }

    protected void setUp() throws Exception
    {
        super.setUp();
        TestDBUtility.deleteAll(EnableOrderConditionRow.TYPE);
        TestDBUtility.deleteAll(TradingTimeRow.TYPE);
    }

    protected void tearDown() throws Exception
    {
        super.tearDown();
        TestDBUtility.deleteAll(EnableOrderConditionRow.TYPE);
        TestDBUtility.deleteAll(TradingTimeRow.TYPE);
    }

    /*
     * testGetHandlingPossibleExpirationDateType_Case0001
     */
    public void testGetHandlingPossibleExpirationDateType_Case0001()
    {
        final String STR_METHOD_NAME = " testGetHandlingPossibleExpirationDateType_Case0001()";
        log.entering(TEST_START + STR_METHOD_NAME);

        TestBaseForMock.MOCK_MANAGER.setIsMockUsed(false);
        try
        {
            ThreadLocalSystemAttributesRegistry.setAttribute(
                "xblocks.gtl.attributes.systemtimestamp",
                new Timestamp(WEB3DateUtility.getDate("20040714110000","yyyyMMddHHmmss").getTime()));
            WEB3GentradeTradingClendarContext l_context = new WEB3GentradeTradingClendarContext();
            l_context.setInstitutionCode("0D");
            l_context.setBranchCode("381");
            l_context.setTradingTimeType("26");
            l_context.setMarketCode("SP");
            l_context.setProductCode("0");
            ThreadLocalSystemAttributesRegistry.setAttribute("web3.tradingcalendarcontext", l_context);
            ThreadLocalSystemAttributesRegistry.setAttribute(
                "web3.attributes.basetimestampfororderbizdate",
                new Timestamp(WEB3DateUtility.getDate("20040714110000","yyyyMMddHHmmss").getTime()));

            TradingTimeParams l_tradingTimeParams = new TradingTimeParams(TestDBUtility.getTradingTimeRow());
            l_tradingTimeParams.setBizDateType("1");
            l_tradingTimeParams.setStartTime("100000");
            l_tradingTimeParams.setEndTime("150000");
            l_tradingTimeParams.setBizdateCalcParameter("0");
            l_tradingTimeParams.setSubmitMarketTrigger("1");
            l_tradingTimeParams.setSessionType("2");
            TestDBUtility.insertWithDel(l_tradingTimeParams);
            EnableOrderConditionParams l_enableOrderConditionParams = TestDBUtility.getEnableOrderConditionParamsRow();
            l_enableOrderConditionParams.setCarriedOrder("1");
            l_enableOrderConditionParams.setEveningSessionOrder(null);
            l_enableOrderConditionParams.setProductType(ProductTypeEnum.IFO);
            TestDBUtility.insertWithDel(l_enableOrderConditionParams);

            WEB3GentradeHandlingOrderCond l_handlingOrderCond = new WEB3GentradeHandlingOrderCond(
                "0D",
                ProductTypeEnum.IFO,
                "0",
                "0",
                "0");
            l_handlingOrderCond.setTradingEndDate(WEB3DateUtility.getDate("20040715","yyyyMMdd"));
            String[]  l_strResults = l_handlingOrderCond.getHandlingPossibleExpirationDateType();
            assertEquals(2, l_strResults.length);
            assertEquals("1", l_strResults[0]);
            assertEquals("2", l_strResults[1]);
        }
        catch (Exception l_ex)
        {
            log.error("ERROR:", l_ex);
            log.exiting(TEST_END + STR_METHOD_NAME);
            fail();
        }
        log.exiting(TEST_END + STR_METHOD_NAME);
    }

    /*
     * testGetHandlingPossibleExpirationDateType_Case0002
     */
    public void testGetHandlingPossibleExpirationDateType_Case0002()
    {
        final String STR_METHOD_NAME = " testGetHandlingPossibleExpirationDateType_Case0002()";
        log.entering(TEST_START + STR_METHOD_NAME);

        TestBaseForMock.MOCK_MANAGER.setIsMockUsed(false);
        try
        {
            ThreadLocalSystemAttributesRegistry.setAttribute(
                "xblocks.gtl.attributes.systemtimestamp",
                new Timestamp(WEB3DateUtility.getDate("20040714110000","yyyyMMddHHmmss").getTime()));
            WEB3GentradeTradingClendarContext l_context = new WEB3GentradeTradingClendarContext();
            l_context.setInstitutionCode("0D");
            l_context.setBranchCode("381");
            l_context.setTradingTimeType("26");
            l_context.setMarketCode("SP");
            l_context.setProductCode("0");
            ThreadLocalSystemAttributesRegistry.setAttribute("web3.tradingcalendarcontext", l_context);
            ThreadLocalSystemAttributesRegistry.setAttribute(
                "web3.attributes.basetimestampfororderbizdate",
                new Timestamp(WEB3DateUtility.getDate("20040714110000","yyyyMMddHHmmss").getTime()));

            TradingTimeParams l_tradingTimeParams = new TradingTimeParams(TestDBUtility.getTradingTimeRow());
            l_tradingTimeParams.setBizDateType("1");
            l_tradingTimeParams.setStartTime("100000");
            l_tradingTimeParams.setEndTime("150000");
            l_tradingTimeParams.setBizdateCalcParameter("0");
            l_tradingTimeParams.setSubmitMarketTrigger("1");
            l_tradingTimeParams.setSessionType("2");
            TestDBUtility.insertWithDel(l_tradingTimeParams);
            EnableOrderConditionParams l_enableOrderConditionParams = TestDBUtility.getEnableOrderConditionParamsRow();
            l_enableOrderConditionParams.setCarriedOrder("0");
            l_enableOrderConditionParams.setEveningSessionOrder("1");
            l_enableOrderConditionParams.setProductType(ProductTypeEnum.IFO);
            TestDBUtility.insertWithDel(l_enableOrderConditionParams);

            WEB3GentradeHandlingOrderCond l_handlingOrderCond = new WEB3GentradeHandlingOrderCond(
                "0D",
                ProductTypeEnum.IFO,
                "0",
                "0",
                "0");
            l_handlingOrderCond.setTradingEndDate(WEB3DateUtility.getDate("20040715","yyyyMMdd"));
            String[]  l_strResults = l_handlingOrderCond.getHandlingPossibleExpirationDateType();
            assertEquals(2, l_strResults.length);
            assertEquals("1", l_strResults[0]);
            assertEquals("3", l_strResults[1]);
        }
        catch (Exception l_ex)
        {
            log.error("ERROR:", l_ex);
            log.exiting(TEST_END + STR_METHOD_NAME);
            fail();
        }
        log.exiting(TEST_END + STR_METHOD_NAME);
    }

    /*
     * testGetHandlingPossibleExpirationDateType_Case0003
     */
    public void testGetHandlingPossibleExpirationDateType_Case0003()
    {
        final String STR_METHOD_NAME = " testGetHandlingPossibleExpirationDateType_Case0003()";
        log.entering(TEST_START + STR_METHOD_NAME);

        TestBaseForMock.MOCK_MANAGER.setIsMockUsed(false);
        try
        {
            ThreadLocalSystemAttributesRegistry.setAttribute(
                "xblocks.gtl.attributes.systemtimestamp",
                new Timestamp(WEB3DateUtility.getDate("20040714110000","yyyyMMddHHmmss").getTime()));
            WEB3GentradeTradingClendarContext l_context = new WEB3GentradeTradingClendarContext();
            l_context.setInstitutionCode("0D");
            l_context.setBranchCode("381");
            l_context.setTradingTimeType("26");
            l_context.setMarketCode("SP");
            l_context.setProductCode("0");
            ThreadLocalSystemAttributesRegistry.setAttribute("web3.tradingcalendarcontext", l_context);
            ThreadLocalSystemAttributesRegistry.setAttribute(
                "web3.attributes.basetimestampfororderbizdate",
                new Timestamp(WEB3DateUtility.getDate("20040714110000","yyyyMMddHHmmss").getTime()));

            TradingTimeParams l_tradingTimeParams = new TradingTimeParams(TestDBUtility.getTradingTimeRow());
            l_tradingTimeParams.setBizDateType("1");
            l_tradingTimeParams.setStartTime("100000");
            l_tradingTimeParams.setEndTime("150000");
            l_tradingTimeParams.setBizdateCalcParameter("0");
            l_tradingTimeParams.setSubmitMarketTrigger("1");
            l_tradingTimeParams.setSessionType("1");
            TestDBUtility.insertWithDel(l_tradingTimeParams);
            EnableOrderConditionParams l_enableOrderConditionParams = TestDBUtility.getEnableOrderConditionParamsRow();
            l_enableOrderConditionParams.setCarriedOrder("0");
            l_enableOrderConditionParams.setEveningSessionOrder("1");
            l_enableOrderConditionParams.setProductType(ProductTypeEnum.IFO);
            TestDBUtility.insertWithDel(l_enableOrderConditionParams);

            WEB3GentradeHandlingOrderCond l_handlingOrderCond = new WEB3GentradeHandlingOrderCond(
                "0D",
                ProductTypeEnum.IFO,
                "0",
                "0",
                "0");
            l_handlingOrderCond.setTradingEndDate(WEB3DateUtility.getDate("20040715","yyyyMMdd"));
            String[]  l_strResults = l_handlingOrderCond.getHandlingPossibleExpirationDateType();
            assertEquals(1, l_strResults.length);
            assertEquals("1", l_strResults[0]);
        }
        catch (Exception l_ex)
        {
            log.error("ERROR:", l_ex);
            log.exiting(TEST_END + STR_METHOD_NAME);
            fail();
        }
        log.exiting(TEST_END + STR_METHOD_NAME);
    }

    /*
     * testGetHandlingPossibleExpirationDateType_Case0004
     */
    public void testGetHandlingPossibleExpirationDateType_Case0004()
    {
        final String STR_METHOD_NAME = " testGetHandlingPossibleExpirationDateType_Case0004()";
        log.entering(TEST_START + STR_METHOD_NAME);

        TestBaseForMock.MOCK_MANAGER.setIsMockUsed(false);
        try
        {
            EnableOrderConditionParams l_enableOrderConditionParams = TestDBUtility.getEnableOrderConditionParamsRow();
            l_enableOrderConditionParams.setCarriedOrder("0");
            TestDBUtility.insertWithDel(l_enableOrderConditionParams);

            WEB3GentradeHandlingOrderCond l_handlingOrderCond = new WEB3GentradeHandlingOrderCond(
                "0D",
                ProductTypeEnum.FOREIGN_EQUITY,
                "0",
                "0",
                "0");
            String[]  l_strResults = l_handlingOrderCond.getHandlingPossibleExpirationDateType();
            assertEquals(1, l_strResults.length);
            assertEquals("1", l_strResults[0]);
        }
        catch (Exception l_ex)
        {
            log.error("ERROR:", l_ex);
            log.exiting(TEST_END + STR_METHOD_NAME);
            fail();
        }
        log.exiting(TEST_END + STR_METHOD_NAME);
    }

    /*
     * testGetHandlingPossibleExpirationDateType_Case0005
     */
    public void testGetHandlingPossibleExpirationDateType_Case0005()
    {
        final String STR_METHOD_NAME = " testGetHandlingPossibleExpirationDateType_Case0005()";
        log.entering(TEST_START + STR_METHOD_NAME);

        TestBaseForMock.MOCK_MANAGER.setIsMockUsed(false);
        try
        {
            EnableOrderConditionParams l_enableOrderConditionParams = TestDBUtility.getEnableOrderConditionParamsRow();
            l_enableOrderConditionParams.setCarriedOrder("9");
            TestDBUtility.insertWithDel(l_enableOrderConditionParams);

            WEB3GentradeHandlingOrderCond l_handlingOrderCond = new WEB3GentradeHandlingOrderCond(
                "0D",
                ProductTypeEnum.FOREIGN_EQUITY,
                "0",
                "0",
                "0");
            String[]  l_strResults = l_handlingOrderCond.getHandlingPossibleExpirationDateType();
            assertEquals(2, l_strResults.length);
            assertEquals("1", l_strResults[0]);
            assertEquals("2", l_strResults[1]);
        }
        catch (Exception l_ex)
        {
            log.error("ERROR:", l_ex);
            log.exiting(TEST_END + STR_METHOD_NAME);
            fail();
        }
        log.exiting(TEST_END + STR_METHOD_NAME);
    }

    /*
     * testIsOrderUntilDeadLinePossibleHandlingTradingEndDateConsidering_Case0001
     */
    public void testIsOrderUntilDeadLinePossibleHandlingTradingEndDateConsidering_Case0001()
    {
        final String STR_METHOD_NAME = " testIsOrderUntilDeadLinePossibleHandlingTradingEndDateConsidering_Case0001()";
        log.entering(TEST_START + STR_METHOD_NAME);

        TestBaseForMock.MOCK_MANAGER.setIsMockUsed(false);
        try
        {
            EnableOrderConditionParams l_enableOrderConditionParams = TestDBUtility.getEnableOrderConditionParamsRow();
            l_enableOrderConditionParams.setCarriedOrder("0");
            TestDBUtility.insertWithDel(l_enableOrderConditionParams);

            WEB3GentradeHandlingOrderCond l_handlingOrderCond = new WEB3GentradeHandlingOrderCond(
                "0D",
                ProductTypeEnum.FOREIGN_EQUITY,
                "0",
                "0",
                "0");
            boolean l_blnResult = l_handlingOrderCond.isOrderUntilDeadLinePossibleHandlingTradingEndDateConsidering();
            assertEquals(false, l_blnResult);
        }
        catch (Exception l_ex)
        {
            log.error("ERROR:", l_ex);
            log.exiting(TEST_END + STR_METHOD_NAME);
            fail();
        }
        log.exiting(TEST_END + STR_METHOD_NAME);
    }

    /*
     * testIsOrderUntilDeadLinePossibleHandlingTradingEndDateConsidering_Case0002
     */
    public void testIsOrderUntilDeadLinePossibleHandlingTradingEndDateConsidering_Case0002()
    {
        final String STR_METHOD_NAME = " testIsOrderUntilDeadLinePossibleHandlingTradingEndDateConsidering_Case0002()";
        log.entering(TEST_START + STR_METHOD_NAME);

        TestBaseForMock.MOCK_MANAGER.setIsMockUsed(false);
        try
        {
            ThreadLocalSystemAttributesRegistry.setAttribute(
                "xblocks.gtl.attributes.systemtimestamp",
                new Timestamp(WEB3DateUtility.getDate("20040715110000","yyyyMMddHHmmss").getTime()));
            WEB3GentradeTradingClendarContext l_context = new WEB3GentradeTradingClendarContext();
            l_context.setInstitutionCode("0D");
            l_context.setBranchCode("381");
            l_context.setTradingTimeType("26");
            l_context.setMarketCode("SP");
            l_context.setProductCode("0");
            ThreadLocalSystemAttributesRegistry.setAttribute("web3.tradingcalendarcontext", l_context);
            ThreadLocalSystemAttributesRegistry.setAttribute(
                "web3.attributes.basetimestampfororderbizdate",
                new Timestamp(WEB3DateUtility.getDate("20040715110000","yyyyMMddHHmmss").getTime()));

            TradingTimeParams l_tradingTimeParams = new TradingTimeParams(TestDBUtility.getTradingTimeRow());
            l_tradingTimeParams.setBizDateType("1");
            l_tradingTimeParams.setStartTime("100000");
            l_tradingTimeParams.setEndTime("150000");
            l_tradingTimeParams.setBizdateCalcParameter("0");
            l_tradingTimeParams.setSubmitMarketTrigger("0");
            l_tradingTimeParams.setSessionType("1");
            TestDBUtility.insertWithDel(l_tradingTimeParams);
            EnableOrderConditionParams l_enableOrderConditionParams = TestDBUtility.getEnableOrderConditionParamsRow();
            l_enableOrderConditionParams.setCarriedOrder("1");
            TestDBUtility.insertWithDel(l_enableOrderConditionParams);

            WEB3GentradeHandlingOrderCond l_handlingOrderCond = new WEB3GentradeHandlingOrderCond(
                "0D",
                ProductTypeEnum.FOREIGN_EQUITY,
                "0",
                "0",
                "0");
            l_handlingOrderCond.setTradingEndDate(WEB3DateUtility.getDate("20040711","yyyyMMdd"));
            boolean l_blnResult = l_handlingOrderCond.isOrderUntilDeadLinePossibleHandlingTradingEndDateConsidering();
            assertEquals(false, l_blnResult);
        }
        catch (Exception l_ex)
        {
            log.error("ERROR:", l_ex);
            log.exiting(TEST_END + STR_METHOD_NAME);
            fail();
        }
        log.exiting(TEST_END + STR_METHOD_NAME);
    }

    /*
     * testIsOrderUntilDeadLinePossibleHandlingTradingEndDateConsidering_Case0003
     */
    public void testIsOrderUntilDeadLinePossibleHandlingTradingEndDateConsidering_Case0003()
    {
        final String STR_METHOD_NAME = " testIsOrderUntilDeadLinePossibleHandlingTradingEndDateConsidering_Case0003()";
        log.entering(TEST_START + STR_METHOD_NAME);

        TestBaseForMock.MOCK_MANAGER.setIsMockUsed(false);
        try
        {
            ThreadLocalSystemAttributesRegistry.setAttribute(
                "xblocks.gtl.attributes.systemtimestamp",
                new Timestamp(WEB3DateUtility.getDate("20040715110000","yyyyMMddHHmmss").getTime()));
            WEB3GentradeTradingClendarContext l_context = new WEB3GentradeTradingClendarContext();
            l_context.setInstitutionCode("0D");
            l_context.setBranchCode("381");
            l_context.setTradingTimeType("26");
            l_context.setMarketCode("SP");
            l_context.setProductCode("0");
            ThreadLocalSystemAttributesRegistry.setAttribute("web3.tradingcalendarcontext", l_context);
            ThreadLocalSystemAttributesRegistry.setAttribute(
                "web3.attributes.basetimestampfororderbizdate",
                new Timestamp(WEB3DateUtility.getDate("20040715110000","yyyyMMddHHmmss").getTime()));

            TradingTimeParams l_tradingTimeParams = new TradingTimeParams(TestDBUtility.getTradingTimeRow());
            l_tradingTimeParams.setBizDateType("1");
            l_tradingTimeParams.setStartTime("100000");
            l_tradingTimeParams.setEndTime("150000");
            l_tradingTimeParams.setBizdateCalcParameter("0");
            l_tradingTimeParams.setSubmitMarketTrigger("0");
            l_tradingTimeParams.setSessionType("1");
            TestDBUtility.insertWithDel(l_tradingTimeParams);
            EnableOrderConditionParams l_enableOrderConditionParams = TestDBUtility.getEnableOrderConditionParamsRow();
            l_enableOrderConditionParams.setCarriedOrder("1");
            TestDBUtility.insertWithDel(l_enableOrderConditionParams);

            WEB3GentradeHandlingOrderCond l_handlingOrderCond = new WEB3GentradeHandlingOrderCond(
                "0D",
                ProductTypeEnum.FOREIGN_EQUITY,
                "0",
                "0",
                "0");
            l_handlingOrderCond.setTradingEndDate(WEB3DateUtility.getDate("20040715","yyyyMMdd"));
            boolean l_blnResult = l_handlingOrderCond.isOrderUntilDeadLinePossibleHandlingTradingEndDateConsidering();
            assertEquals(false, l_blnResult);
        }
        catch (Exception l_ex)
        {
            log.error("ERROR:", l_ex);
            log.exiting(TEST_END + STR_METHOD_NAME);
            fail();
        }
        log.exiting(TEST_END + STR_METHOD_NAME);
    }

    /*
     * testIsOrderUntilDeadLinePossibleHandlingTradingEndDateConsidering_Case0004
     */
    public void testIsOrderUntilDeadLinePossibleHandlingTradingEndDateConsidering_Case0004()
    {
        final String STR_METHOD_NAME = " testIsOrderUntilDeadLinePossibleHandlingTradingEndDateConsidering_Case0004()";
        log.entering(TEST_START + STR_METHOD_NAME);

        TestBaseForMock.MOCK_MANAGER.setIsMockUsed(false);
        try
        {
            ThreadLocalSystemAttributesRegistry.setAttribute(
                "xblocks.gtl.attributes.systemtimestamp",
                new Timestamp(WEB3DateUtility.getDate("20040715110000","yyyyMMddHHmmss").getTime()));
            WEB3GentradeTradingClendarContext l_context = new WEB3GentradeTradingClendarContext();
            l_context.setInstitutionCode("0D");
            l_context.setBranchCode("381");
            l_context.setTradingTimeType("26");
            l_context.setMarketCode("SP");
            l_context.setProductCode("0");
            ThreadLocalSystemAttributesRegistry.setAttribute("web3.tradingcalendarcontext", l_context);
            ThreadLocalSystemAttributesRegistry.setAttribute(
                "web3.attributes.basetimestampfororderbizdate",
                new Timestamp(WEB3DateUtility.getDate("20040715110000","yyyyMMddHHmmss").getTime()));

            TradingTimeParams l_tradingTimeParams = new TradingTimeParams(TestDBUtility.getTradingTimeRow());
            l_tradingTimeParams.setBizDateType("1");
            l_tradingTimeParams.setStartTime("100000");
            l_tradingTimeParams.setEndTime("150000");
            l_tradingTimeParams.setBizdateCalcParameter("0");
            l_tradingTimeParams.setSubmitMarketTrigger("0");
            l_tradingTimeParams.setSessionType("1");
            TestDBUtility.insertWithDel(l_tradingTimeParams);
            EnableOrderConditionParams l_enableOrderConditionParams = TestDBUtility.getEnableOrderConditionParamsRow();
            l_enableOrderConditionParams.setCarriedOrder("1");
            TestDBUtility.insertWithDel(l_enableOrderConditionParams);

            WEB3GentradeHandlingOrderCond l_handlingOrderCond = new WEB3GentradeHandlingOrderCond(
                "0D",
                ProductTypeEnum.FOREIGN_EQUITY,
                "0",
                "0",
                "0");
            l_handlingOrderCond.setTradingEndDate(null);
            boolean l_blnResult = l_handlingOrderCond.isOrderUntilDeadLinePossibleHandlingTradingEndDateConsidering();
            assertEquals(true, l_blnResult);
        }
        catch (Exception l_ex)
        {
            log.error("ERROR:", l_ex);
            log.exiting(TEST_END + STR_METHOD_NAME);
            fail();
        }
        log.exiting(TEST_END + STR_METHOD_NAME);
    }

    /*
     * testIsOrderUntilDeadLinePossibleHandlingTradingEndDateConsidering_Case0005
     */
    public void testIsOrderUntilDeadLinePossibleHandlingTradingEndDateConsidering_Case0005()
    {
        final String STR_METHOD_NAME = " testIsOrderUntilDeadLinePossibleHandlingTradingEndDateConsidering_Case0005()";
        log.entering(TEST_START + STR_METHOD_NAME);

        TestBaseForMock.MOCK_MANAGER.setIsMockUsed(false);
        try
        {
            ThreadLocalSystemAttributesRegistry.setAttribute(
                "xblocks.gtl.attributes.systemtimestamp",
                new Timestamp(WEB3DateUtility.getDate("20040714110000","yyyyMMddHHmmss").getTime()));
            WEB3GentradeTradingClendarContext l_context = new WEB3GentradeTradingClendarContext();
            l_context.setInstitutionCode("0D");
            l_context.setBranchCode("381");
            l_context.setTradingTimeType("26");
            l_context.setMarketCode("SP");
            l_context.setProductCode("0");
            ThreadLocalSystemAttributesRegistry.setAttribute("web3.tradingcalendarcontext", l_context);
            ThreadLocalSystemAttributesRegistry.setAttribute(
                "web3.attributes.basetimestampfororderbizdate",
                new Timestamp(WEB3DateUtility.getDate("20040714110000","yyyyMMddHHmmss").getTime()));

            TradingTimeParams l_tradingTimeParams = new TradingTimeParams(TestDBUtility.getTradingTimeRow());
            l_tradingTimeParams.setBizDateType("1");
            l_tradingTimeParams.setStartTime("100000");
            l_tradingTimeParams.setEndTime("150000");
            l_tradingTimeParams.setBizdateCalcParameter("0");
            l_tradingTimeParams.setSubmitMarketTrigger("0");
            l_tradingTimeParams.setSessionType("1");
            TestDBUtility.insertWithDel(l_tradingTimeParams);
            EnableOrderConditionParams l_enableOrderConditionParams = TestDBUtility.getEnableOrderConditionParamsRow();
            l_enableOrderConditionParams.setCarriedOrder("1");
            TestDBUtility.insertWithDel(l_enableOrderConditionParams);

            WEB3GentradeHandlingOrderCond l_handlingOrderCond = new WEB3GentradeHandlingOrderCond(
                "0D",
                ProductTypeEnum.FOREIGN_EQUITY,
                "0",
                "0",
                "0");
            l_handlingOrderCond.setTradingEndDate(WEB3DateUtility.getDate("20040715","yyyyMMdd"));
            boolean l_blnResult = l_handlingOrderCond.isOrderUntilDeadLinePossibleHandlingTradingEndDateConsidering();
            assertEquals(true, l_blnResult);
        }
        catch (Exception l_ex)
        {
            log.error("ERROR:", l_ex);
            log.exiting(TEST_END + STR_METHOD_NAME);
            fail();
        }
        log.exiting(TEST_END + STR_METHOD_NAME);
    }

    /*
     * testIsEveningSessionOrderPossibleHandling_Case0001
     */
    public void testIsEveningSessionOrderPossibleHandling_Case0001()
    {
        final String STR_METHOD_NAME = " testIsEveningSessionOrderPossibleHandling_Case0001()";
        log.entering(TEST_START + STR_METHOD_NAME);

        TestBaseForMock.MOCK_MANAGER.setIsMockUsed(false);
        try
        {
            EnableOrderConditionParams l_enableOrderConditionParams = TestDBUtility.getEnableOrderConditionParamsRow();
            l_enableOrderConditionParams.setEveningSessionOrder("1");
            TestDBUtility.insertWithDel(l_enableOrderConditionParams);

            WEB3GentradeHandlingOrderCond l_handlingOrderCond = new WEB3GentradeHandlingOrderCond(
                "0D",
                ProductTypeEnum.FOREIGN_EQUITY,
                "0",
                "0",
                "0");
            boolean l_blnResult = l_handlingOrderCond.isEveningSessionOrderPossibleHandling();
            assertEquals(true, l_blnResult);
        }
        catch (Exception l_ex)
        {
            log.error("ERROR:", l_ex);
            log.exiting(TEST_END + STR_METHOD_NAME);
            fail();
        }
        log.exiting(TEST_END + STR_METHOD_NAME);
    }

    /*
     * testIsEveningSessionOrderPossibleHandling_Case0002
     */
    public void testIsEveningSessionOrderPossibleHandling_Case0002()
    {
        final String STR_METHOD_NAME = " testIsEveningSessionOrderPossibleHandling_Case0002()";
        log.entering(TEST_START + STR_METHOD_NAME);

        TestBaseForMock.MOCK_MANAGER.setIsMockUsed(false);
        try
        {
            EnableOrderConditionParams l_enableOrderConditionParams = TestDBUtility.getEnableOrderConditionParamsRow();
            l_enableOrderConditionParams.setEveningSessionOrder(null);
            TestDBUtility.insertWithDel(l_enableOrderConditionParams);

            WEB3GentradeHandlingOrderCond l_handlingOrderCond = new WEB3GentradeHandlingOrderCond(
                "0D",
                ProductTypeEnum.FOREIGN_EQUITY,
                "0",
                "0",
                "0");
            boolean l_blnResult = l_handlingOrderCond.isEveningSessionOrderPossibleHandling();
            assertEquals(false, l_blnResult);
        }
        catch (Exception l_ex)
        {
            log.error("ERROR:", l_ex);
            log.exiting(TEST_END + STR_METHOD_NAME);
            fail();
        }
        log.exiting(TEST_END + STR_METHOD_NAME);
    }

    /*
     * testIsEveningSessionOrderPossibleHandlingTradingEndDateConsidering_Case0001
     */
    public void testIsEveningSessionOrderPossibleHandlingTradingEndDateConsidering_Case0001()
    {
        final String STR_METHOD_NAME = " testIsEveningSessionOrderPossibleHandlingTradingEndDateConsidering_Case0001()";
        log.entering(TEST_START + STR_METHOD_NAME);

        TestBaseForMock.MOCK_MANAGER.setIsMockUsed(false);
        try
        {
            EnableOrderConditionParams l_enableOrderConditionParams = TestDBUtility.getEnableOrderConditionParamsRow();
            l_enableOrderConditionParams.setEveningSessionOrder(null);
            TestDBUtility.insertWithDel(l_enableOrderConditionParams);

            WEB3GentradeHandlingOrderCond l_handlingOrderCond = new WEB3GentradeHandlingOrderCond(
                "0D",
                ProductTypeEnum.FOREIGN_EQUITY,
                "0",
                "0",
                "0");
            boolean l_blnResult = l_handlingOrderCond.isEveningSessionOrderPossibleHandlingTradingEndDateConsidering();
            assertEquals(false, l_blnResult);
        }
        catch (Exception l_ex)
        {
            log.error("ERROR:", l_ex);
            log.exiting(TEST_END + STR_METHOD_NAME);
            fail();
        }
        log.exiting(TEST_END + STR_METHOD_NAME);
    }

    /*
     * testIsEveningSessionOrderPossibleHandlingTradingEndDateConsidering_Case0002
     */
    public void testIsEveningSessionOrderPossibleHandlingTradingEndDateConsidering_Case0002()
    {
        final String STR_METHOD_NAME = " testIsEveningSessionOrderPossibleHandlingTradingEndDateConsidering_Case0001()";
        log.entering(TEST_START + STR_METHOD_NAME);

        TestBaseForMock.MOCK_MANAGER.setIsMockUsed(false);
        try
        {
            ThreadLocalSystemAttributesRegistry.setAttribute(
                "xblocks.gtl.attributes.systemtimestamp",
                new Timestamp(WEB3DateUtility.getDate("20040713110000","yyyyMMddHHmmss").getTime()));
            WEB3GentradeTradingClendarContext l_context = new WEB3GentradeTradingClendarContext();
            l_context.setInstitutionCode("0D");
            l_context.setBranchCode("381");
            l_context.setTradingTimeType("26");
            l_context.setMarketCode("SP");
            l_context.setProductCode("0");
            ThreadLocalSystemAttributesRegistry.setAttribute("web3.tradingcalendarcontext", l_context);
            ThreadLocalSystemAttributesRegistry.setAttribute(
                "web3.attributes.basetimestampfororderbizdate",
                new Timestamp(WEB3DateUtility.getDate("20040713110000","yyyyMMddHHmmss").getTime()));

            TradingTimeParams l_tradingTimeParams = new TradingTimeParams(TestDBUtility.getTradingTimeRow());
            l_tradingTimeParams.setBizDateType("1");
            l_tradingTimeParams.setStartTime("100000");
            l_tradingTimeParams.setEndTime("150000");
            l_tradingTimeParams.setBizdateCalcParameter("0");
            l_tradingTimeParams.setSubmitMarketTrigger("0");
            l_tradingTimeParams.setSessionType("1");
            TestDBUtility.insertWithDel(l_tradingTimeParams);
            EnableOrderConditionParams l_enableOrderConditionParams = TestDBUtility.getEnableOrderConditionParamsRow();
            l_enableOrderConditionParams.setEveningSessionOrder("1");
            TestDBUtility.insertWithDel(l_enableOrderConditionParams);

            WEB3GentradeHandlingOrderCond l_handlingOrderCond = new WEB3GentradeHandlingOrderCond(
                "0D",
                ProductTypeEnum.FOREIGN_EQUITY,
                "0",
                "0",
                "0");
            l_handlingOrderCond.setTradingEndDate(WEB3DateUtility.getDate("20040711","yyyyMMdd"));
            boolean l_blnResult = l_handlingOrderCond.isEveningSessionOrderPossibleHandlingTradingEndDateConsidering();
            assertEquals(false, l_blnResult);
        }
        catch (Exception l_ex)
        {
            log.error("ERROR:", l_ex);
            log.exiting(TEST_END + STR_METHOD_NAME);
            fail();
        }
        log.exiting(TEST_END + STR_METHOD_NAME);
    }

    /*
     * testIsEveningSessionOrderPossibleHandlingTradingEndDateConsidering_Case0003
     */
    public void testIsEveningSessionOrderPossibleHandlingTradingEndDateConsidering_Case0003()
    {
        final String STR_METHOD_NAME = " testIsEveningSessionOrderPossibleHandlingTradingEndDateConsidering_Case0003()";
        log.entering(TEST_START + STR_METHOD_NAME);

        TestBaseForMock.MOCK_MANAGER.setIsMockUsed(false);
        try
        {
            ThreadLocalSystemAttributesRegistry.setAttribute(
                "xblocks.gtl.attributes.systemtimestamp",
                new Timestamp(WEB3DateUtility.getDate("20040713110000","yyyyMMddHHmmss").getTime()));
            WEB3GentradeTradingClendarContext l_context = new WEB3GentradeTradingClendarContext();
            l_context.setInstitutionCode("0D");
            l_context.setBranchCode("381");
            l_context.setTradingTimeType("26");
            l_context.setMarketCode("SP");
            l_context.setProductCode("0");
            ThreadLocalSystemAttributesRegistry.setAttribute("web3.tradingcalendarcontext", l_context);
            ThreadLocalSystemAttributesRegistry.setAttribute(
                "web3.attributes.basetimestampfororderbizdate",
                new Timestamp(WEB3DateUtility.getDate("20040713110000","yyyyMMddHHmmss").getTime()));

            TradingTimeParams l_tradingTimeParams = new TradingTimeParams(TestDBUtility.getTradingTimeRow());
            l_tradingTimeParams.setBizDateType("1");
            l_tradingTimeParams.setStartTime("100000");
            l_tradingTimeParams.setEndTime("150000");
            l_tradingTimeParams.setBizdateCalcParameter("0");
            l_tradingTimeParams.setSubmitMarketTrigger("0");
            l_tradingTimeParams.setSessionType("1");
            TestDBUtility.insertWithDel(l_tradingTimeParams);
            EnableOrderConditionParams l_enableOrderConditionParams = TestDBUtility.getEnableOrderConditionParamsRow();
            l_enableOrderConditionParams.setEveningSessionOrder("1");
            TestDBUtility.insertWithDel(l_enableOrderConditionParams);

            WEB3GentradeHandlingOrderCond l_handlingOrderCond = new WEB3GentradeHandlingOrderCond(
                "0D",
                ProductTypeEnum.FOREIGN_EQUITY,
                "0",
                "0",
                "0");
            l_handlingOrderCond.setTradingEndDate(WEB3DateUtility.getDate("20040713","yyyyMMdd"));
            boolean l_blnResult = l_handlingOrderCond.isEveningSessionOrderPossibleHandlingTradingEndDateConsidering();
            assertEquals(false, l_blnResult);
        }
        catch (Exception l_ex)
        {
            log.error("ERROR:", l_ex);
            log.exiting(TEST_END + STR_METHOD_NAME);
            fail();
        }
        log.exiting(TEST_END + STR_METHOD_NAME);
    }

    /*
     * testIsEveningSessionOrderPossibleHandlingTradingEndDateConsidering_Case0004
     */
    public void testIsEveningSessionOrderPossibleHandlingTradingEndDateConsidering_Case0004()
    {
        final String STR_METHOD_NAME = " testIsEveningSessionOrderPossibleHandlingTradingEndDateConsidering_Case0004()";
        log.entering(TEST_START + STR_METHOD_NAME);

        TestBaseForMock.MOCK_MANAGER.setIsMockUsed(false);
        try
        {
            ThreadLocalSystemAttributesRegistry.setAttribute(
                "xblocks.gtl.attributes.systemtimestamp",
                new Timestamp(WEB3DateUtility.getDate("20040713110000","yyyyMMddHHmmss").getTime()));
            WEB3GentradeTradingClendarContext l_context = new WEB3GentradeTradingClendarContext();
            l_context.setInstitutionCode("0D");
            l_context.setBranchCode("381");
            l_context.setTradingTimeType("26");
            l_context.setMarketCode("SP");
            l_context.setProductCode("0");
            ThreadLocalSystemAttributesRegistry.setAttribute("web3.tradingcalendarcontext", l_context);
            ThreadLocalSystemAttributesRegistry.setAttribute(
                "web3.attributes.basetimestampfororderbizdate",
                new Timestamp(WEB3DateUtility.getDate("20040713110000","yyyyMMddHHmmss").getTime()));

            TradingTimeParams l_tradingTimeParams = new TradingTimeParams(TestDBUtility.getTradingTimeRow());
            l_tradingTimeParams.setBizDateType("1");
            l_tradingTimeParams.setStartTime("100000");
            l_tradingTimeParams.setEndTime("150000");
            l_tradingTimeParams.setBizdateCalcParameter("0");
            l_tradingTimeParams.setSubmitMarketTrigger("0");
            l_tradingTimeParams.setSessionType("1");
            TestDBUtility.insertWithDel(l_tradingTimeParams);
            EnableOrderConditionParams l_enableOrderConditionParams = TestDBUtility.getEnableOrderConditionParamsRow();
            l_enableOrderConditionParams.setEveningSessionOrder("1");
            TestDBUtility.insertWithDel(l_enableOrderConditionParams);

            WEB3GentradeHandlingOrderCond l_handlingOrderCond = new WEB3GentradeHandlingOrderCond(
                "0D",
                ProductTypeEnum.FOREIGN_EQUITY,
                "0",
                "0",
                "0");
            l_handlingOrderCond.setTradingEndDate(null);
            boolean l_blnResult = l_handlingOrderCond.isEveningSessionOrderPossibleHandlingTradingEndDateConsidering();
            assertEquals(true, l_blnResult);
        }
        catch (Exception l_ex)
        {
            log.error("ERROR:", l_ex);
            log.exiting(TEST_END + STR_METHOD_NAME);
            fail();
        }
        log.exiting(TEST_END + STR_METHOD_NAME);
    }

    /*
     * testIsEveningSessionOrderPossibleHandlingTradingEndDateConsidering_Case0005
     */
    public void testIsEveningSessionOrderPossibleHandlingTradingEndDateConsidering_Case0005()
    {
        final String STR_METHOD_NAME = " testIsEveningSessionOrderPossibleHandlingTradingEndDateConsidering_Case0005()";
        log.entering(TEST_START + STR_METHOD_NAME);

        TestBaseForMock.MOCK_MANAGER.setIsMockUsed(false);
        try
        {
            ThreadLocalSystemAttributesRegistry.setAttribute(
                "xblocks.gtl.attributes.systemtimestamp",
                new Timestamp(WEB3DateUtility.getDate("20040713110000","yyyyMMddHHmmss").getTime()));
            WEB3GentradeTradingClendarContext l_context = new WEB3GentradeTradingClendarContext();
            l_context.setInstitutionCode("0D");
            l_context.setBranchCode("381");
            l_context.setTradingTimeType("26");
            l_context.setMarketCode("SP");
            l_context.setProductCode("0");
            ThreadLocalSystemAttributesRegistry.setAttribute("web3.tradingcalendarcontext", l_context);
            ThreadLocalSystemAttributesRegistry.setAttribute(
                "web3.attributes.basetimestampfororderbizdate",
                new Timestamp(WEB3DateUtility.getDate("20040713110000","yyyyMMddHHmmss").getTime()));

            TradingTimeParams l_tradingTimeParams = new TradingTimeParams(TestDBUtility.getTradingTimeRow());
            l_tradingTimeParams.setBizDateType("1");
            l_tradingTimeParams.setStartTime("100000");
            l_tradingTimeParams.setEndTime("150000");
            l_tradingTimeParams.setBizdateCalcParameter("0");
            l_tradingTimeParams.setSubmitMarketTrigger("0");
            l_tradingTimeParams.setSessionType("1");
            TestDBUtility.insertWithDel(l_tradingTimeParams);
            EnableOrderConditionParams l_enableOrderConditionParams = TestDBUtility.getEnableOrderConditionParamsRow();
            l_enableOrderConditionParams.setEveningSessionOrder("1");
            TestDBUtility.insertWithDel(l_enableOrderConditionParams);

            WEB3GentradeHandlingOrderCond l_handlingOrderCond = new WEB3GentradeHandlingOrderCond(
                "0D",
                ProductTypeEnum.FOREIGN_EQUITY,
                "0",
                "0",
                "0");
            l_handlingOrderCond.setTradingEndDate(WEB3DateUtility.getDate("20040715","yyyyMMdd"));
            boolean l_blnResult = l_handlingOrderCond.isEveningSessionOrderPossibleHandlingTradingEndDateConsidering();
            assertEquals(true, l_blnResult);
        }
        catch (Exception l_ex)
        {
            log.error("ERROR:", l_ex);
            log.exiting(TEST_END + STR_METHOD_NAME);
            fail();
        }
        log.exiting(TEST_END + STR_METHOD_NAME);
    }

    /*
     * testGetOrderUntilDeadLineEndDayTradingEndDateConsidering_Case0001
     */
    public void testGetOrderUntilDeadLineEndDayTradingEndDateConsidering_Case0001()
    {
        final String STR_METHOD_NAME = " testGetOrderUntilDeadLineEndDayTradingEndDateConsidering_Case0001()";
        log.entering(TEST_START + STR_METHOD_NAME);

        TestBaseForMock.MOCK_MANAGER.setIsMockUsed(false);
        try
        {
            ThreadLocalSystemAttributesRegistry.setAttribute(
                "xblocks.gtl.attributes.systemtimestamp",
                new Timestamp(WEB3DateUtility.getDate("20040715110000","yyyyMMddHHmmss").getTime()));
            WEB3GentradeTradingClendarContext l_context = new WEB3GentradeTradingClendarContext();
            l_context.setInstitutionCode("0D");
            l_context.setBranchCode("381");
            l_context.setTradingTimeType("26");
            l_context.setMarketCode("SP");
            l_context.setProductCode("0");
            ThreadLocalSystemAttributesRegistry.setAttribute("web3.tradingcalendarcontext", l_context);
            ThreadLocalSystemAttributesRegistry.setAttribute(
                "web3.attributes.basetimestampfororderbizdate",
                new Timestamp(WEB3DateUtility.getDate("20040715110000","yyyyMMddHHmmss").getTime()));

            TradingTimeParams l_tradingTimeParams = new TradingTimeParams(TestDBUtility.getTradingTimeRow());
            l_tradingTimeParams.setBizDateType("1");
            l_tradingTimeParams.setStartTime("100000");
            l_tradingTimeParams.setEndTime("150000");
            l_tradingTimeParams.setBizdateCalcParameter("0");
            l_tradingTimeParams.setSubmitMarketTrigger("0");
            l_tradingTimeParams.setSessionType("1");
            TestDBUtility.insertWithDel(l_tradingTimeParams);
            EnableOrderConditionParams l_enableOrderConditionParams = TestDBUtility.getEnableOrderConditionParamsRow();
            l_enableOrderConditionParams.setCarriedOrder("0");
            TestDBUtility.insertWithDel(l_enableOrderConditionParams);

            WEB3GentradeHandlingOrderCond l_handlingOrderCond = new WEB3GentradeHandlingOrderCond(
                "0D",
                ProductTypeEnum.FOREIGN_EQUITY,
                "0",
                "0",
                "0");
            l_handlingOrderCond.setTradingEndDate(WEB3DateUtility.getDate("20040711","yyyyMMdd"));
            Date l_datResult = l_handlingOrderCond.getOrderUntilDeadLineEndDayTradingEndDateConsidering(null);
            assertEquals(null, l_datResult);
        }
        catch (Exception l_ex)
        {
            log.error("ERROR:", l_ex);
            log.exiting(TEST_END + STR_METHOD_NAME);
            fail();
        }
        log.exiting(TEST_END + STR_METHOD_NAME);
    }

    /*
     * testGetOrderUntilDeadLineEndDayTradingEndDateConsidering_Case0002
     */
    public void testGetOrderUntilDeadLineEndDayTradingEndDateConsidering_Case0002()
    {
        final String STR_METHOD_NAME = " testGetOrderUntilDeadLineEndDayTradingEndDateConsidering_Case0002()";
        log.entering(TEST_START + STR_METHOD_NAME);

        TestBaseForMock.MOCK_MANAGER.setIsMockUsed(false);
        try
        {
            EnableOrderConditionParams l_enableOrderConditionParams = TestDBUtility.getEnableOrderConditionParamsRow();
            l_enableOrderConditionParams.setCarriedOrder("1");
            TestDBUtility.insertWithDel(l_enableOrderConditionParams);

            WEB3GentradeHandlingOrderCond l_handlingOrderCond = new WEB3GentradeHandlingOrderCond(
                "0D",
                ProductTypeEnum.FOREIGN_EQUITY,
                "0",
                "0",
                "0");
            l_handlingOrderCond.setTradingEndDate(null);
            Date l_datResult = l_handlingOrderCond.getOrderUntilDeadLineEndDayTradingEndDateConsidering(
                WEB3DateUtility.getDate("20040714","yyyyMMdd"));
            assertEquals(WEB3DateUtility.getDate("20040716","yyyyMMdd"), l_datResult);
        }
        catch (Exception l_ex)
        {
            log.error("ERROR:", l_ex);
            log.exiting(TEST_END + STR_METHOD_NAME);
            fail();
        }
        log.exiting(TEST_END + STR_METHOD_NAME);
    }

    /*
     * testGetOrderUntilDeadLineEndDayTradingEndDateConsidering_Case0003
     */
    public void testGetOrderUntilDeadLineEndDayTradingEndDateConsidering_Case0003()
    {
        final String STR_METHOD_NAME = " testGetOrderUntilDeadLineEndDayTradingEndDateConsidering_Case0003()";
        log.entering(TEST_START + STR_METHOD_NAME);

        TestBaseForMock.MOCK_MANAGER.setIsMockUsed(false);
        try
        {
            EnableOrderConditionParams l_enableOrderConditionParams = TestDBUtility.getEnableOrderConditionParamsRow();
            l_enableOrderConditionParams.setCarriedOrder("1");
            TestDBUtility.insertWithDel(l_enableOrderConditionParams);

            WEB3GentradeHandlingOrderCond l_handlingOrderCond = new WEB3GentradeHandlingOrderCond(
                "0D",
                ProductTypeEnum.FOREIGN_EQUITY,
                "0",
                "0",
                "0");
            l_handlingOrderCond.setTradingEndDate(WEB3DateUtility.getDate("20040712","yyyyMMdd"));
            Date l_datResult = l_handlingOrderCond.getOrderUntilDeadLineEndDayTradingEndDateConsidering(
                WEB3DateUtility.getDate("20040714","yyyyMMdd"));
            assertEquals(WEB3DateUtility.getDate("20040712","yyyyMMdd"), l_datResult);
        }
        catch (Exception l_ex)
        {
            log.error("ERROR:", l_ex);
            log.exiting(TEST_END + STR_METHOD_NAME);
            fail();
        }
        log.exiting(TEST_END + STR_METHOD_NAME);
    }

    /*
     * testGetOrderUntilDeadLineEndDayTradingEndDateConsidering_Case0004
     */
    public void testGetOrderUntilDeadLineEndDayTradingEndDateConsidering_Case0004()
    {
        final String STR_METHOD_NAME = " testGetOrderUntilDeadLineEndDayTradingEndDateConsidering_Case0004()";
        log.entering(TEST_START + STR_METHOD_NAME);

        TestBaseForMock.MOCK_MANAGER.setIsMockUsed(false);
        try
        {
            EnableOrderConditionParams l_enableOrderConditionParams = TestDBUtility.getEnableOrderConditionParamsRow();
            l_enableOrderConditionParams.setCarriedOrder("1");
            TestDBUtility.insertWithDel(l_enableOrderConditionParams);

            WEB3GentradeHandlingOrderCond l_handlingOrderCond = new WEB3GentradeHandlingOrderCond(
                "0D",
                ProductTypeEnum.FOREIGN_EQUITY,
                "0",
                "0",
                "0");
            l_handlingOrderCond.setTradingEndDate(WEB3DateUtility.getDate("20040716","yyyyMMdd"));
            Date l_datResult = l_handlingOrderCond.getOrderUntilDeadLineEndDayTradingEndDateConsidering(
                WEB3DateUtility.getDate("20040714","yyyyMMdd"));
            assertEquals(WEB3DateUtility.getDate("20040716","yyyyMMdd"), l_datResult);
        }
        catch (Exception l_ex)
        {
            log.error("ERROR:", l_ex);
            log.exiting(TEST_END + STR_METHOD_NAME);
            fail();
        }
        log.exiting(TEST_END + STR_METHOD_NAME);
    }

    /*
     * testGetOrderUntilDeadLineEndDayTradingEndDateConsidering_Case0005
     */
    public void testGetOrderUntilDeadLineEndDayTradingEndDateConsidering_Case0005()
    {
        final String STR_METHOD_NAME = " testGetOrderUntilDeadLineEndDayTradingEndDateConsidering_Case0005()";
        log.entering(TEST_START + STR_METHOD_NAME);

        TestBaseForMock.MOCK_MANAGER.setIsMockUsed(false);
        try
        {
            EnableOrderConditionParams l_enableOrderConditionParams = TestDBUtility.getEnableOrderConditionParamsRow();
            l_enableOrderConditionParams.setCarriedOrder("1");
            TestDBUtility.insertWithDel(l_enableOrderConditionParams);

            WEB3GentradeHandlingOrderCond l_handlingOrderCond = new WEB3GentradeHandlingOrderCond(
                "0D",
                ProductTypeEnum.FOREIGN_EQUITY,
                "0",
                "0",
                "0");
            l_handlingOrderCond.setTradingEndDate(WEB3DateUtility.getDate("20040718","yyyyMMdd"));
            Date l_datResult = l_handlingOrderCond.getOrderUntilDeadLineEndDayTradingEndDateConsidering(
                WEB3DateUtility.getDate("20040714","yyyyMMdd"));
            assertEquals(WEB3DateUtility.getDate("20040716","yyyyMMdd"), l_datResult);
        }
        catch (Exception l_ex)
        {
            log.error("ERROR:", l_ex);
            log.exiting(TEST_END + STR_METHOD_NAME);
            fail();
        }
        log.exiting(TEST_END + STR_METHOD_NAME);
    }

    /*
     * testIsOrderUntilDeadLinePossibleDayDate_Case0001
     */
    public void testIsOrderUntilDeadLinePossibleDayDate_Case0001()
    {
        final String STR_METHOD_NAME = " testIsOrderUntilDeadLinePossibleDayDate_Case0001()";
        log.entering(TEST_START + STR_METHOD_NAME);

        TestBaseForMock.MOCK_MANAGER.setIsMockUsed(false);
        try
        {
            ThreadLocalSystemAttributesRegistry.setAttribute(
                "xblocks.gtl.attributes.systemtimestamp",
                new Timestamp(WEB3DateUtility.getDate("20040715110000","yyyyMMddHHmmss").getTime()));
            WEB3GentradeTradingClendarContext l_context = new WEB3GentradeTradingClendarContext();
            l_context.setInstitutionCode("0D");
            l_context.setBranchCode("381");
            l_context.setTradingTimeType("26");
            l_context.setMarketCode("SP");
            l_context.setProductCode("0");
            ThreadLocalSystemAttributesRegistry.setAttribute("web3.tradingcalendarcontext", l_context);
            ThreadLocalSystemAttributesRegistry.setAttribute(
                "web3.attributes.basetimestampfororderbizdate",
                new Timestamp(WEB3DateUtility.getDate("20040715110000","yyyyMMddHHmmss").getTime()));
            TradingTimeParams l_tradingTimeParams = new TradingTimeParams(TestDBUtility.getTradingTimeRow());
            l_tradingTimeParams.setBizDateType("1");
            l_tradingTimeParams.setStartTime("100000");
            l_tradingTimeParams.setEndTime("150000");
            l_tradingTimeParams.setBizdateCalcParameter("0");
            l_tradingTimeParams.setSubmitMarketTrigger("0");
            l_tradingTimeParams.setSessionType("1");
            TestDBUtility.insertWithDel(l_tradingTimeParams);
            EnableOrderConditionParams l_enableOrderConditionParams = TestDBUtility.getEnableOrderConditionParamsRow();
            l_enableOrderConditionParams.setCarriedOrder("0");
            l_enableOrderConditionParams.setCarriedOrderLapseDateSpec("1");
            TestDBUtility.insertWithDel(l_enableOrderConditionParams);

            WEB3GentradeHandlingOrderCond l_handlingOrderCond = new WEB3GentradeHandlingOrderCond(
                "0D",
                ProductTypeEnum.FOREIGN_EQUITY,
                "0",
                "0",
                "0");
            l_handlingOrderCond.setTradingEndDate(WEB3DateUtility.getDate("20040718","yyyyMMdd"));
            boolean l_blnResult = l_handlingOrderCond.isOrderUntilDeadLinePossibleDay(
                WEB3DateUtility.getDate("20040714","yyyyMMdd"));
            assertEquals(true, l_blnResult);
        }
        catch (Exception l_ex)
        {
            log.error("ERROR:", l_ex);
            log.exiting(TEST_END + STR_METHOD_NAME);
            fail();
        }
        log.exiting(TEST_END + STR_METHOD_NAME);
    }

    /*
     * testIsOrderUntilDeadLinePossibleDayDate_Case0002
     */
    public void testIsOrderUntilDeadLinePossibleDayDate_Case0002()
    {
        final String STR_METHOD_NAME = " testIsOrderUntilDeadLinePossibleDayDate_Case0002()";
        log.entering(TEST_START + STR_METHOD_NAME);

        TestBaseForMock.MOCK_MANAGER.setIsMockUsed(false);
        try
        {
            ThreadLocalSystemAttributesRegistry.setAttribute(
                "xblocks.gtl.attributes.systemtimestamp",
                new Timestamp(WEB3DateUtility.getDate("20040715110000","yyyyMMddHHmmss").getTime()));
            WEB3GentradeTradingClendarContext l_context = new WEB3GentradeTradingClendarContext();
            l_context.setInstitutionCode("0D");
            l_context.setBranchCode("381");
            l_context.setTradingTimeType("26");
            l_context.setMarketCode("SP");
            l_context.setProductCode("0");
            ThreadLocalSystemAttributesRegistry.setAttribute("web3.tradingcalendarcontext", l_context);
            ThreadLocalSystemAttributesRegistry.setAttribute(
                "web3.attributes.basetimestampfororderbizdate",
                new Timestamp(WEB3DateUtility.getDate("20040715110000","yyyyMMddHHmmss").getTime()));
            TradingTimeParams l_tradingTimeParams = new TradingTimeParams(TestDBUtility.getTradingTimeRow());
            l_tradingTimeParams.setBizDateType("1");
            l_tradingTimeParams.setStartTime("100000");
            l_tradingTimeParams.setEndTime("150000");
            l_tradingTimeParams.setBizdateCalcParameter("0");
            l_tradingTimeParams.setSubmitMarketTrigger("0");
            l_tradingTimeParams.setSessionType("1");
            TestDBUtility.insertWithDel(l_tradingTimeParams);
            EnableOrderConditionParams l_enableOrderConditionParams = TestDBUtility.getEnableOrderConditionParamsRow();
            l_enableOrderConditionParams.setCarriedOrder("0");
            l_enableOrderConditionParams.setCarriedOrderLapseDateSpec("1");
            TestDBUtility.insertWithDel(l_enableOrderConditionParams);

            WEB3GentradeHandlingOrderCond l_handlingOrderCond = new WEB3GentradeHandlingOrderCond(
                "0D",
                ProductTypeEnum.FOREIGN_EQUITY,
                "0",
                "0",
                "0");
            l_handlingOrderCond.setTradingEndDate(null);
            boolean l_blnResult = l_handlingOrderCond.isOrderUntilDeadLinePossibleDay(
                WEB3DateUtility.getDate("20040714","yyyyMMdd"));
            fail();
        }
        catch (WEB3BusinessLayerException l_ble)
        {
            assertEquals(WEB3ErrorCatalog.BUSINESS_ERROR_00413, l_ble.getErrorInfo());
        }
        catch (Exception l_ex)
        {
            log.error("ERROR:", l_ex);
            log.exiting(TEST_END + STR_METHOD_NAME);
            fail();
        }
        log.exiting(TEST_END + STR_METHOD_NAME);
    }

    /*
     * testIsOrderUntilDeadLinePossibleDayDate_Case0003
     */
    public void testIsOrderUntilDeadLinePossibleDayDate_Case0003()
    {
        final String STR_METHOD_NAME = " testIsOrderUntilDeadLinePossibleDayDate_Case0003()";
        log.entering(TEST_START + STR_METHOD_NAME);

        TestBaseForMock.MOCK_MANAGER.setIsMockUsed(false);
        try
        {
            ThreadLocalSystemAttributesRegistry.setAttribute(
                "xblocks.gtl.attributes.systemtimestamp",
                new Timestamp(WEB3DateUtility.getDate("20040715110000","yyyyMMddHHmmss").getTime()));
            WEB3GentradeTradingClendarContext l_context = new WEB3GentradeTradingClendarContext();
            l_context.setInstitutionCode("0D");
            l_context.setBranchCode("381");
            l_context.setTradingTimeType("26");
            l_context.setMarketCode("SP");
            l_context.setProductCode("0");
            ThreadLocalSystemAttributesRegistry.setAttribute("web3.tradingcalendarcontext", l_context);
            ThreadLocalSystemAttributesRegistry.setAttribute(
                "web3.attributes.basetimestampfororderbizdate",
                new Timestamp(WEB3DateUtility.getDate("20040715110000","yyyyMMddHHmmss").getTime()));
            TradingTimeParams l_tradingTimeParams = new TradingTimeParams(TestDBUtility.getTradingTimeRow());
            l_tradingTimeParams.setBizDateType("1");
            l_tradingTimeParams.setStartTime("100000");
            l_tradingTimeParams.setEndTime("150000");
            l_tradingTimeParams.setBizdateCalcParameter("0");
            l_tradingTimeParams.setSubmitMarketTrigger("0");
            l_tradingTimeParams.setSessionType("1");
            TestDBUtility.insertWithDel(l_tradingTimeParams);
            EnableOrderConditionParams l_enableOrderConditionParams = TestDBUtility.getEnableOrderConditionParamsRow();
            l_enableOrderConditionParams.setCarriedOrder("0");
            l_enableOrderConditionParams.setCarriedOrderLapseDateSpec("0");
            TestDBUtility.insertWithDel(l_enableOrderConditionParams);

            WEB3GentradeHandlingOrderCond l_handlingOrderCond = new WEB3GentradeHandlingOrderCond(
                "0D",
                ProductTypeEnum.FOREIGN_EQUITY,
                "0",
                "0",
                "0");
            l_handlingOrderCond.setTradingEndDate(WEB3DateUtility.getDate("20040718","yyyyMMdd"));
            boolean l_blnResult = l_handlingOrderCond.isOrderUntilDeadLinePossibleDay(
                WEB3DateUtility.getDate("20040714","yyyyMMdd"));
            fail();
        }
        catch (WEB3BusinessLayerException l_ble)
        {
            assertEquals(WEB3ErrorCatalog.BUSINESS_ERROR_00413, l_ble.getErrorInfo());
        }
        catch (Exception l_ex)
        {
            log.error("ERROR:", l_ex);
            log.exiting(TEST_END + STR_METHOD_NAME);
            fail();
        }
        log.exiting(TEST_END + STR_METHOD_NAME);
    }

    /*
     * testIsOrderUntilDeadLinePossibleDayDateDate_Case0001
     */
    public void testIsOrderUntilDeadLinePossibleDayDateDate_Case0001()
    {
        final String STR_METHOD_NAME = " testIsOrderUntilDeadLinePossibleDayDateDate_Case0001()";
        log.entering(TEST_START + STR_METHOD_NAME);

        TestBaseForMock.MOCK_MANAGER.setIsMockUsed(false);
        try
        {
            EnableOrderConditionParams l_enableOrderConditionParams = TestDBUtility.getEnableOrderConditionParamsRow();
            l_enableOrderConditionParams.setCarriedOrder("0");
            l_enableOrderConditionParams.setCarriedOrderLapseDateSpec("1");
            TestDBUtility.insertWithDel(l_enableOrderConditionParams);

            WEB3GentradeHandlingOrderCond l_handlingOrderCond = new WEB3GentradeHandlingOrderCond(
                "0D",
                ProductTypeEnum.FOREIGN_EQUITY,
                "0",
                "0",
                "0");
            l_handlingOrderCond.setTradingEndDate(WEB3DateUtility.getDate("20040718","yyyyMMdd"));
            boolean l_blnResult = l_handlingOrderCond.isOrderUntilDeadLinePossibleDay(
                WEB3DateUtility.getDate("20040714","yyyyMMdd"),
                WEB3DateUtility.getDate("20040714","yyyyMMdd"));
            assertEquals(true, l_blnResult);
        }
        catch (Exception l_ex)
        {
            log.error("ERROR:", l_ex);
            log.exiting(TEST_END + STR_METHOD_NAME);
            fail();
        }
        log.exiting(TEST_END + STR_METHOD_NAME);
    }

    /*
     * testIsOrderUntilDeadLinePossibleDayDateDate_Case0002
     */
    public void testIsOrderUntilDeadLinePossibleDayDateDate_Case0002()
    {
        final String STR_METHOD_NAME = " testIsOrderUntilDeadLinePossibleDayDateDate_Case0002()";
        log.entering(TEST_START + STR_METHOD_NAME);

        TestBaseForMock.MOCK_MANAGER.setIsMockUsed(false);
        try
        {
            EnableOrderConditionParams l_enableOrderConditionParams = TestDBUtility.getEnableOrderConditionParamsRow();
            l_enableOrderConditionParams.setCarriedOrder("0");
            l_enableOrderConditionParams.setCarriedOrderLapseDateSpec("1");
            TestDBUtility.insertWithDel(l_enableOrderConditionParams);

            WEB3GentradeHandlingOrderCond l_handlingOrderCond = new WEB3GentradeHandlingOrderCond(
                "0D",
                ProductTypeEnum.FOREIGN_EQUITY,
                "0",
                "0",
                "0");
            l_handlingOrderCond.setTradingEndDate(null);
            boolean l_blnResult = l_handlingOrderCond.isOrderUntilDeadLinePossibleDay(
                WEB3DateUtility.getDate("20040714","yyyyMMdd"),
                WEB3DateUtility.getDate("20040714","yyyyMMdd"));
            fail();
        }
        catch (WEB3BusinessLayerException l_ble)
        {
            assertEquals(WEB3ErrorCatalog.BUSINESS_ERROR_00413, l_ble.getErrorInfo());
        }
        catch (Exception l_ex)
        {
            log.error("ERROR:", l_ex);
            log.exiting(TEST_END + STR_METHOD_NAME);
            fail();
        }
        log.exiting(TEST_END + STR_METHOD_NAME);
    }

    /*
     * testIsOrderUntilDeadLinePossibleDayDateDate_Case0003
     */
    public void testIsOrderUntilDeadLinePossibleDayDateDate_Case0003()
    {
        final String STR_METHOD_NAME = " testIsOrderUntilDeadLinePossibleDayDateDate_Case0003()";
        log.entering(TEST_START + STR_METHOD_NAME);

        TestBaseForMock.MOCK_MANAGER.setIsMockUsed(false);
        try
        {
            EnableOrderConditionParams l_enableOrderConditionParams = TestDBUtility.getEnableOrderConditionParamsRow();
            l_enableOrderConditionParams.setCarriedOrder("0");
            l_enableOrderConditionParams.setCarriedOrderLapseDateSpec("0");
            TestDBUtility.insertWithDel(l_enableOrderConditionParams);

            WEB3GentradeHandlingOrderCond l_handlingOrderCond = new WEB3GentradeHandlingOrderCond(
                "0D",
                ProductTypeEnum.FOREIGN_EQUITY,
                "0",
                "0",
                "0");
            l_handlingOrderCond.setTradingEndDate(WEB3DateUtility.getDate("20040718","yyyyMMdd"));
            boolean l_blnResult = l_handlingOrderCond.isOrderUntilDeadLinePossibleDay(
                WEB3DateUtility.getDate("20040714","yyyyMMdd"), 
                WEB3DateUtility.getDate("20040714","yyyyMMdd"));
            fail();
        }
        catch (WEB3BusinessLayerException l_ble)
        {
            assertEquals(WEB3ErrorCatalog.BUSINESS_ERROR_00413, l_ble.getErrorInfo());
        }
        catch (Exception l_ex)
        {
            log.error("ERROR:", l_ex);
            log.exiting(TEST_END + STR_METHOD_NAME);
            fail();
        }
        log.exiting(TEST_END + STR_METHOD_NAME);
    }

    /*
     * testGetHandlingPossibleExpirationDateTypes_Case0001
     */
    public void testGetHandlingPossibleExpirationDateTypes_Case0001()
    {
        final String STR_METHOD_NAME = " testGetHandlingPossibleExpirationDateTypes_Case0001()";
        log.entering(TEST_START + STR_METHOD_NAME);

        TestBaseForMock.MOCK_MANAGER.setIsMockUsed(false);
        try
        {
            ThreadLocalSystemAttributesRegistry.setAttribute(
                "xblocks.gtl.attributes.systemtimestamp",
                new Timestamp(WEB3DateUtility.getDate("20040714110000","yyyyMMddHHmmss").getTime()));
            WEB3GentradeTradingClendarContext l_context = new WEB3GentradeTradingClendarContext();
            l_context.setInstitutionCode("0D");
            l_context.setBranchCode("381");
            l_context.setTradingTimeType("26");
            l_context.setMarketCode("SP");
            l_context.setProductCode("0");
            ThreadLocalSystemAttributesRegistry.setAttribute("web3.tradingcalendarcontext", l_context);
            ThreadLocalSystemAttributesRegistry.setAttribute(
                "web3.attributes.basetimestampfororderbizdate",
                new Timestamp(WEB3DateUtility.getDate("20040714110000","yyyyMMddHHmmss").getTime()));

            TradingTimeParams l_tradingTimeParams = new TradingTimeParams(TestDBUtility.getTradingTimeRow());
            l_tradingTimeParams.setBizDateType("1");
            l_tradingTimeParams.setStartTime("100000");
            l_tradingTimeParams.setEndTime("150000");
            l_tradingTimeParams.setBizdateCalcParameter("0");
            l_tradingTimeParams.setSubmitMarketTrigger("1");
            l_tradingTimeParams.setSessionType("2");
            TestDBUtility.insertWithDel(l_tradingTimeParams);
            EnableOrderConditionParams l_enableOrderConditionParams = TestDBUtility.getEnableOrderConditionParamsRow();
            l_enableOrderConditionParams.setCarriedOrder("1");
            l_enableOrderConditionParams.setEveningSessionOrder("1");
            l_enableOrderConditionParams.setProductType(ProductTypeEnum.IFO);
            TestDBUtility.insertWithDel(l_enableOrderConditionParams);

            WEB3GentradeHandlingOrderCond l_handlingOrderCond = new WEB3GentradeHandlingOrderCond(
                "0D",
                ProductTypeEnum.IFO,
                "0",
                "0",
                "0");
            l_handlingOrderCond.setTradingEndDate(null);
            String[]  l_strResults = l_handlingOrderCond.getHandlingPossibleExpirationDateTypes();
            assertEquals(3, l_strResults.length);
            assertEquals("1", l_strResults[0]);
            assertEquals("2", l_strResults[1]);
            assertEquals("3", l_strResults[2]);
        }
        catch (Exception l_ex)
        {
            log.error("ERROR:", l_ex);
            log.exiting(TEST_END + STR_METHOD_NAME);
            fail();
        }
        log.exiting(TEST_END + STR_METHOD_NAME);
    }

    /*
     * testGetHandlingPossibleExpirationDateTypes_Case0002
     */
    public void testGetHandlingPossibleExpirationDateTypes_Case0002()
    {
        final String STR_METHOD_NAME = " testGetHandlingPossibleExpirationDateTypes_Case0002()";
        log.entering(TEST_START + STR_METHOD_NAME);

        TestBaseForMock.MOCK_MANAGER.setIsMockUsed(false);
        try
        {
            ThreadLocalSystemAttributesRegistry.setAttribute(
                "xblocks.gtl.attributes.systemtimestamp",
                new Timestamp(WEB3DateUtility.getDate("20040714110000","yyyyMMddHHmmss").getTime()));
            WEB3GentradeTradingClendarContext l_context = new WEB3GentradeTradingClendarContext();
            l_context.setInstitutionCode("0D");
            l_context.setBranchCode("381");
            l_context.setTradingTimeType("26");
            l_context.setMarketCode("SP");
            l_context.setProductCode("0");
            ThreadLocalSystemAttributesRegistry.setAttribute("web3.tradingcalendarcontext", l_context);
            ThreadLocalSystemAttributesRegistry.setAttribute(
                "web3.attributes.basetimestampfororderbizdate",
                new Timestamp(WEB3DateUtility.getDate("20040714110000","yyyyMMddHHmmss").getTime()));

            TradingTimeParams l_tradingTimeParams = new TradingTimeParams(TestDBUtility.getTradingTimeRow());
            l_tradingTimeParams.setBizDateType("1");
            l_tradingTimeParams.setStartTime("100000");
            l_tradingTimeParams.setEndTime("150000");
            l_tradingTimeParams.setBizdateCalcParameter("0");
            l_tradingTimeParams.setSubmitMarketTrigger("1");
            l_tradingTimeParams.setSessionType("2");
            TestDBUtility.insertWithDel(l_tradingTimeParams);
            EnableOrderConditionParams l_enableOrderConditionParams = TestDBUtility.getEnableOrderConditionParamsRow();
            l_enableOrderConditionParams.setCarriedOrder("0");
            l_enableOrderConditionParams.setEveningSessionOrder(null);
            l_enableOrderConditionParams.setProductType(ProductTypeEnum.IFO);
            TestDBUtility.insertWithDel(l_enableOrderConditionParams);

            WEB3GentradeHandlingOrderCond l_handlingOrderCond = new WEB3GentradeHandlingOrderCond(
                "0D",
                ProductTypeEnum.IFO,
                "0",
                "0",
                "0");
            l_handlingOrderCond.setTradingEndDate(null);
            String[]  l_strResults = l_handlingOrderCond.getHandlingPossibleExpirationDateTypes();
            assertEquals(1, l_strResults.length);
            assertEquals("1", l_strResults[0]);
        }
        catch (Exception l_ex)
        {
            log.error("ERROR:", l_ex);
            log.exiting(TEST_END + STR_METHOD_NAME);
            fail();
        }
        log.exiting(TEST_END + STR_METHOD_NAME);
    }

    /*
     * testGetHandlingPossibleExpirationDateTypes_Case0003
     */
    public void testGetHandlingPossibleExpirationDateTypes_Case0003()
    {
        final String STR_METHOD_NAME = " testGetHandlingPossibleExpirationDateTypes_Case0003()";
        log.entering(TEST_START + STR_METHOD_NAME);

        TestBaseForMock.MOCK_MANAGER.setIsMockUsed(false);
        try
        {
            ThreadLocalSystemAttributesRegistry.setAttribute(
                "xblocks.gtl.attributes.systemtimestamp",
                new Timestamp(WEB3DateUtility.getDate("20040714110000","yyyyMMddHHmmss").getTime()));
            WEB3GentradeTradingClendarContext l_context = new WEB3GentradeTradingClendarContext();
            l_context.setInstitutionCode("0D");
            l_context.setBranchCode("381");
            l_context.setTradingTimeType("26");
            l_context.setMarketCode("SP");
            l_context.setProductCode("0");
            ThreadLocalSystemAttributesRegistry.setAttribute("web3.tradingcalendarcontext", l_context);
            ThreadLocalSystemAttributesRegistry.setAttribute(
                "web3.attributes.basetimestampfororderbizdate",
                new Timestamp(WEB3DateUtility.getDate("20040714110000","yyyyMMddHHmmss").getTime()));

            TradingTimeParams l_tradingTimeParams = new TradingTimeParams(TestDBUtility.getTradingTimeRow());
            l_tradingTimeParams.setBizDateType("1");
            l_tradingTimeParams.setStartTime("100000");
            l_tradingTimeParams.setEndTime("150000");
            l_tradingTimeParams.setBizdateCalcParameter("0");
            l_tradingTimeParams.setSubmitMarketTrigger("1");
            l_tradingTimeParams.setSessionType("2");
            TestDBUtility.insertWithDel(l_tradingTimeParams);
            EnableOrderConditionParams l_enableOrderConditionParams = TestDBUtility.getEnableOrderConditionParamsRow();
            l_enableOrderConditionParams.setCarriedOrder("0");
            l_enableOrderConditionParams.setEveningSessionOrder(null);
            l_enableOrderConditionParams.setProductType(ProductTypeEnum.EQUITY);
            TestDBUtility.insertWithDel(l_enableOrderConditionParams);

            WEB3GentradeHandlingOrderCond l_handlingOrderCond = new WEB3GentradeHandlingOrderCond(
                "0D",
                ProductTypeEnum.EQUITY,
                "0",
                "0",
                "0");
            l_handlingOrderCond.setTradingEndDate(null);
            String[]  l_strResults = l_handlingOrderCond.getHandlingPossibleExpirationDateTypes();
            assertEquals(1, l_strResults.length);
            assertEquals("1", l_strResults[0]);
        }
        catch (Exception l_ex)
        {
            log.error("ERROR:", l_ex);
            log.exiting(TEST_END + STR_METHOD_NAME);
            fail();
        }
        log.exiting(TEST_END + STR_METHOD_NAME);
    }

    /*
     * testGetHandlingPossibleExpirationDateTypes_Case0004
     */
    public void testGetHandlingPossibleExpirationDateTypes_Case0004()
    {
        final String STR_METHOD_NAME = " testGetHandlingPossibleExpirationDateTypes_Case0004()";
        log.entering(TEST_START + STR_METHOD_NAME);

        TestBaseForMock.MOCK_MANAGER.setIsMockUsed(false);
        try
        {
            ThreadLocalSystemAttributesRegistry.setAttribute(
                "xblocks.gtl.attributes.systemtimestamp",
                new Timestamp(WEB3DateUtility.getDate("20040714110000","yyyyMMddHHmmss").getTime()));
            WEB3GentradeTradingClendarContext l_context = new WEB3GentradeTradingClendarContext();
            l_context.setInstitutionCode("0D");
            l_context.setBranchCode("381");
            l_context.setTradingTimeType("26");
            l_context.setMarketCode("SP");
            l_context.setProductCode("0");
            ThreadLocalSystemAttributesRegistry.setAttribute("web3.tradingcalendarcontext", l_context);
            ThreadLocalSystemAttributesRegistry.setAttribute(
                "web3.attributes.basetimestampfororderbizdate",
                new Timestamp(WEB3DateUtility.getDate("20040714110000","yyyyMMddHHmmss").getTime()));

            TradingTimeParams l_tradingTimeParams = new TradingTimeParams(TestDBUtility.getTradingTimeRow());
            l_tradingTimeParams.setBizDateType("1");
            l_tradingTimeParams.setStartTime("100000");
            l_tradingTimeParams.setEndTime("150000");
            l_tradingTimeParams.setBizdateCalcParameter("0");
            l_tradingTimeParams.setSubmitMarketTrigger("1");
            l_tradingTimeParams.setSessionType("2");
            TestDBUtility.insertWithDel(l_tradingTimeParams);
            EnableOrderConditionParams l_enableOrderConditionParams = TestDBUtility.getEnableOrderConditionParamsRow();
            l_enableOrderConditionParams.setCarriedOrder("1");
            l_enableOrderConditionParams.setEveningSessionOrder(null);
            l_enableOrderConditionParams.setProductType(ProductTypeEnum.EQUITY);
            TestDBUtility.insertWithDel(l_enableOrderConditionParams);

            WEB3GentradeHandlingOrderCond l_handlingOrderCond = new WEB3GentradeHandlingOrderCond(
                "0D",
                ProductTypeEnum.EQUITY,
                "0",
                "0",
                "0");
            l_handlingOrderCond.setTradingEndDate(null);
            String[]  l_strResults = l_handlingOrderCond.getHandlingPossibleExpirationDateTypes();
            assertEquals(2, l_strResults.length);
            assertEquals("1", l_strResults[0]);
            assertEquals("2", l_strResults[1]);
        }
        catch (Exception l_ex)
        {
            log.error("ERROR:", l_ex);
            log.exiting(TEST_END + STR_METHOD_NAME);
            fail();
        }
        log.exiting(TEST_END + STR_METHOD_NAME);
    }

    /*
     * testGetOrderUntilDeadLineStartDayTradingEndDateConsidering_Case0001
     */
    public void testGetOrderUntilDeadLineStartDayTradingEndDateConsidering_Case0001()
    {
        final String STR_METHOD_NAME = " testGetOrderUntilDeadLineStartDayTradingEndDateConsidering_Case0001()";
        log.entering(TEST_START + STR_METHOD_NAME);

        TestBaseForMock.MOCK_MANAGER.setIsMockUsed(false);
        try
        {
            ThreadLocalSystemAttributesRegistry.setAttribute(
                "xblocks.gtl.attributes.systemtimestamp",
                new Timestamp(WEB3DateUtility.getDate("20040714110000","yyyyMMddHHmmss").getTime()));
            WEB3GentradeTradingClendarContext l_context = new WEB3GentradeTradingClendarContext();
            l_context.setInstitutionCode("0D");
            l_context.setBranchCode("381");
            l_context.setTradingTimeType("26");
            l_context.setMarketCode("SP");
            l_context.setProductCode("0");
            ThreadLocalSystemAttributesRegistry.setAttribute("web3.tradingcalendarcontext", l_context);
            ThreadLocalSystemAttributesRegistry.setAttribute(
                "web3.attributes.basetimestampfororderbizdate",
                new Timestamp(WEB3DateUtility.getDate("20071120110000","yyyyMMddHHmmss").getTime()));

            TradingTimeParams l_tradingTimeParams = new TradingTimeParams(TestDBUtility.getTradingTimeRow());
            l_tradingTimeParams.setBizDateType("1");
            l_tradingTimeParams.setStartTime("100000");
            l_tradingTimeParams.setEndTime("150000");
            l_tradingTimeParams.setBizdateCalcParameter("0");
            l_tradingTimeParams.setSubmitMarketTrigger("1");
            l_tradingTimeParams.setSessionType("2");
            TestDBUtility.insertWithDel(l_tradingTimeParams);
            EnableOrderConditionParams l_enableOrderConditionParams = TestDBUtility.getEnableOrderConditionParamsRow();
            l_enableOrderConditionParams.setCarriedOrder("1");
            l_enableOrderConditionParams.setCarriedOrderLapseDateSpec("0");
            l_enableOrderConditionParams.setEveningSessionOrder(null);
            l_enableOrderConditionParams.setProductType(ProductTypeEnum.EQUITY);
            TestDBUtility.insertWithDel(l_enableOrderConditionParams);

            WEB3GentradeHandlingOrderCond l_handlingOrderCond = new WEB3GentradeHandlingOrderCond(
                "0D",
                ProductTypeEnum.EQUITY,
                "0",
                "0",
                "0");
            l_handlingOrderCond.setTradingEndDate(null);
            Date  l_datStartDay = l_handlingOrderCond.getOrderUntilDeadLineStartDayTradingEndDateConsidering(null);
            assertEquals(WEB3DateUtility.getDate("20071120000000","yyyyMMddHHmmss"), l_datStartDay);
        }
        catch (Exception l_ex)
        {
            log.error("ERROR:", l_ex);
            log.exiting(TEST_END + STR_METHOD_NAME);
            fail();
        }
        log.exiting(TEST_END + STR_METHOD_NAME);
    }

    /*
     * testGetOrderUntilDeadLineStartDayTradingEndDateConsidering_Case0002
     */
    public void testGetOrderUntilDeadLineStartDayTradingEndDateConsidering_Case0002()
    {
        final String STR_METHOD_NAME = " testGetOrderUntilDeadLineStartDayTradingEndDateConsidering_Case0002()";
        log.entering(TEST_START + STR_METHOD_NAME);

        TestBaseForMock.MOCK_MANAGER.setIsMockUsed(false);
        try
        {
            EnableOrderConditionParams l_enableOrderConditionParams = TestDBUtility.getEnableOrderConditionParamsRow();
            l_enableOrderConditionParams.setCarriedOrder("1");
            l_enableOrderConditionParams.setCarriedOrderLapseDateSpec("0");
            l_enableOrderConditionParams.setProductType(ProductTypeEnum.EQUITY);
            TestDBUtility.insertWithDel(l_enableOrderConditionParams);

            WEB3GentradeHandlingOrderCond l_handlingOrderCond = new WEB3GentradeHandlingOrderCond(
                "0D",
                ProductTypeEnum.EQUITY,
                "0",
                "0",
                "0");
            l_handlingOrderCond.setTradingEndDate(null);
            Date  l_datStartDay = l_handlingOrderCond.getOrderUntilDeadLineStartDayTradingEndDateConsidering(
                WEB3DateUtility.getDate("20071120110000","yyyyMMddHHmmss"));
            assertEquals(WEB3DateUtility.getDate("20071120110000","yyyyMMddHHmmss"), l_datStartDay);
        }
        catch (Exception l_ex)
        {
            log.error("ERROR:", l_ex);
            log.exiting(TEST_END + STR_METHOD_NAME);
            fail();
        }
        log.exiting(TEST_END + STR_METHOD_NAME);
    }

    /*
     * testGetOrderUntilDeadLineStartDayTradingEndDateConsidering_Case0003
     */
    public void testGetOrderUntilDeadLineStartDayTradingEndDateConsidering_Case0003()
    {
        final String STR_METHOD_NAME = " testGetOrderUntilDeadLineStartDayTradingEndDateConsidering_Case0003()";
        log.entering(TEST_START + STR_METHOD_NAME);

        TestBaseForMock.MOCK_MANAGER.setIsMockUsed(false);
        try
        {
            EnableOrderConditionParams l_enableOrderConditionParams = TestDBUtility.getEnableOrderConditionParamsRow();
            l_enableOrderConditionParams.setCarriedOrder("0");
            l_enableOrderConditionParams.setCarriedOrderLapseDateSpec("0");
            l_enableOrderConditionParams.setProductType(ProductTypeEnum.EQUITY);
            TestDBUtility.insertWithDel(l_enableOrderConditionParams);

            WEB3GentradeHandlingOrderCond l_handlingOrderCond = new WEB3GentradeHandlingOrderCond(
                "0D",
                ProductTypeEnum.EQUITY,
                "0",
                "0",
                "0");
            l_handlingOrderCond.setTradingEndDate(null);
            Date  l_datStartDay = l_handlingOrderCond.getOrderUntilDeadLineStartDayTradingEndDateConsidering(null);
            assertEquals(null, l_datStartDay);
        }
        catch (Exception l_ex)
        {
            log.error("ERROR:", l_ex);
            log.exiting(TEST_END + STR_METHOD_NAME);
            fail();
        }
        log.exiting(TEST_END + STR_METHOD_NAME);
    }

    /*
     * testGetOrderUntilDeadLineStartDayTradingEndDateConsidering_Case0004
     */
    public void testGetOrderUntilDeadLineStartDayTradingEndDateConsidering_Case0004()
    {
        final String STR_METHOD_NAME = " testGetOrderUntilDeadLineStartDayTradingEndDateConsidering_Case0004()";
        log.entering(TEST_START + STR_METHOD_NAME);

        TestBaseForMock.MOCK_MANAGER.setIsMockUsed(false);
        try
        {
            EnableOrderConditionParams l_enableOrderConditionParams = TestDBUtility.getEnableOrderConditionParamsRow();
            l_enableOrderConditionParams.setCarriedOrder("1");
            l_enableOrderConditionParams.setCarriedOrderLapseDateSpec("0");
            l_enableOrderConditionParams.setProductType(ProductTypeEnum.EQUITY);
            TestDBUtility.insertWithDel(l_enableOrderConditionParams);

            WEB3GentradeHandlingOrderCond l_handlingOrderCond = new WEB3GentradeHandlingOrderCond(
                "0D",
                ProductTypeEnum.EQUITY,
                "0",
                "0",
                "0");
            l_handlingOrderCond.setTradingEndDate(WEB3DateUtility.getDate("20071121110000","yyyyMMddHHmmss"));
            Date  l_datStartDay = l_handlingOrderCond.getOrderUntilDeadLineStartDayTradingEndDateConsidering(
                WEB3DateUtility.getDate("20071120110000","yyyyMMddHHmmss"));
            assertEquals(WEB3DateUtility.getDate("20071120110000","yyyyMMddHHmmss"), l_datStartDay);
        }
        catch (Exception l_ex)
        {
            log.error("ERROR:", l_ex);
            log.exiting(TEST_END + STR_METHOD_NAME);
            fail();
        }
        log.exiting(TEST_END + STR_METHOD_NAME);
    }

    /*
     * testGetOrderUntilDeadLineStartDayTradingEndDateConsidering_Case0005
     */
    public void testGetOrderUntilDeadLineStartDayTradingEndDateConsidering_Case0005()
    {
        final String STR_METHOD_NAME = " testGetOrderUntilDeadLineStartDayTradingEndDateConsidering_Case0005()";
        log.entering(TEST_START + STR_METHOD_NAME);

        TestBaseForMock.MOCK_MANAGER.setIsMockUsed(false);
        try
        {
            EnableOrderConditionParams l_enableOrderConditionParams = TestDBUtility.getEnableOrderConditionParamsRow();
            l_enableOrderConditionParams.setCarriedOrder("1");
            l_enableOrderConditionParams.setCarriedOrderLapseDateSpec("0");
            l_enableOrderConditionParams.setProductType(ProductTypeEnum.EQUITY);
            TestDBUtility.insertWithDel(l_enableOrderConditionParams);

            WEB3GentradeHandlingOrderCond l_handlingOrderCond = new WEB3GentradeHandlingOrderCond(
                "0D",
                ProductTypeEnum.EQUITY,
                "0",
                "0",
                "0");
            l_handlingOrderCond.setTradingEndDate(WEB3DateUtility.getDate("20071120110000","yyyyMMddHHmmss"));
            Date  l_datStartDay = l_handlingOrderCond.getOrderUntilDeadLineStartDayTradingEndDateConsidering(
                WEB3DateUtility.getDate("20071120110000","yyyyMMddHHmmss"));
            assertEquals(WEB3DateUtility.getDate("20071120110000","yyyyMMddHHmmss"), l_datStartDay);
        }
        catch (Exception l_ex)
        {
            log.error("ERROR:", l_ex);
            log.exiting(TEST_END + STR_METHOD_NAME);
            fail();
        }
        log.exiting(TEST_END + STR_METHOD_NAME);
    }

    /*
     * testGetOrderUntilDeadLineStartDayTradingEndDateConsidering_Case0006
     */
    public void testGetOrderUntilDeadLineStartDayTradingEndDateConsidering_Case0006()
    {
        final String STR_METHOD_NAME = " testGetOrderUntilDeadLineStartDayTradingEndDateConsidering_Case0006()";
        log.entering(TEST_START + STR_METHOD_NAME);

        TestBaseForMock.MOCK_MANAGER.setIsMockUsed(false);
        try
        {
            EnableOrderConditionParams l_enableOrderConditionParams = TestDBUtility.getEnableOrderConditionParamsRow();
            l_enableOrderConditionParams.setCarriedOrder("1");
            l_enableOrderConditionParams.setCarriedOrderLapseDateSpec("0");
            l_enableOrderConditionParams.setProductType(ProductTypeEnum.EQUITY);
            TestDBUtility.insertWithDel(l_enableOrderConditionParams);

            WEB3GentradeHandlingOrderCond l_handlingOrderCond = new WEB3GentradeHandlingOrderCond(
                "0D",
                ProductTypeEnum.EQUITY,
                "0",
                "0",
                "0");
            l_handlingOrderCond.setTradingEndDate(WEB3DateUtility.getDate("20071119110000","yyyyMMddHHmmss"));
            Date  l_datStartDay = l_handlingOrderCond.getOrderUntilDeadLineStartDayTradingEndDateConsidering(
                WEB3DateUtility.getDate("20071120110000","yyyyMMddHHmmss"));
            assertEquals(WEB3DateUtility.getDate("20071119110000","yyyyMMddHHmmss"), l_datStartDay);
        }
        catch (Exception l_ex)
        {
            log.error("ERROR:", l_ex);
            log.exiting(TEST_END + STR_METHOD_NAME);
            fail();
        }
        log.exiting(TEST_END + STR_METHOD_NAME);
    }

    /*
     * testIsOrderUntilDeadLinePossibleNoHandlingTradingEndDateConsidering_Case0001
     */
    public void testIsOrderUntilDeadLinePossibleNoHandlingTradingEndDateConsidering_Case0001()
    {
        final String STR_METHOD_NAME = " testIsOrderUntilDeadLinePossibleNoHandlingTradingEndDateConsidering_Case0001()";
        log.entering(TEST_START + STR_METHOD_NAME);

        TestBaseForMock.MOCK_MANAGER.setIsMockUsed(false);
        try
        {
            EnableOrderConditionParams l_enableOrderConditionParams = TestDBUtility.getEnableOrderConditionParamsRow();
            l_enableOrderConditionParams.setCarriedOrder("0");
            l_enableOrderConditionParams.setCarriedOrderLapseDateSpec("0");
            l_enableOrderConditionParams.setProductType(ProductTypeEnum.EQUITY);
            TestDBUtility.insertWithDel(l_enableOrderConditionParams);

            WEB3GentradeHandlingOrderCond l_handlingOrderCond = new WEB3GentradeHandlingOrderCond(
                "0D",
                ProductTypeEnum.EQUITY,
                "0",
                "0",
                "0");
            boolean  l_blnResult = l_handlingOrderCond.isOrderUntilDeadLinePossibleNoHandlingTradingEndDateConsidering();
            assertEquals(false, l_blnResult);
        }
        catch (Exception l_ex)
        {
            log.error("ERROR:", l_ex);
            log.exiting(TEST_END + STR_METHOD_NAME);
            fail();
        }
        log.exiting(TEST_END + STR_METHOD_NAME);
    }

    /*
     * testIsOrderUntilDeadLinePossibleNoHandlingTradingEndDateConsidering_Case0002
     */
    public void testIsOrderUntilDeadLinePossibleNoHandlingTradingEndDateConsidering_Case0002()
    {
        final String STR_METHOD_NAME = " testIsOrderUntilDeadLinePossibleNoHandlingTradingEndDateConsidering_Case0002()";
        log.entering(TEST_START + STR_METHOD_NAME);

        TestBaseForMock.MOCK_MANAGER.setIsMockUsed(false);
        try
        {
            EnableOrderConditionParams l_enableOrderConditionParams = TestDBUtility.getEnableOrderConditionParamsRow();
            l_enableOrderConditionParams.setCarriedOrder("2");
            l_enableOrderConditionParams.setCarriedOrderLapseDateSpec("0");
            l_enableOrderConditionParams.setProductType(ProductTypeEnum.EQUITY);
            TestDBUtility.insertWithDel(l_enableOrderConditionParams);

            WEB3GentradeHandlingOrderCond l_handlingOrderCond = new WEB3GentradeHandlingOrderCond(
                "0D",
                ProductTypeEnum.EQUITY,
                "0",
                "0",
                "0");
            boolean  l_blnResult = l_handlingOrderCond.isOrderUntilDeadLinePossibleNoHandlingTradingEndDateConsidering();
            assertEquals(true, l_blnResult);
        }
        catch (Exception l_ex)
        {
            log.error("ERROR:", l_ex);
            log.exiting(TEST_END + STR_METHOD_NAME);
            fail();
        }
        log.exiting(TEST_END + STR_METHOD_NAME);
    }



}
@
