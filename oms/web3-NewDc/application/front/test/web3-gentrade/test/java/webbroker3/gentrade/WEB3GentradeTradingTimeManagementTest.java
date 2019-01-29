head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.04.07.01.22.22;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	5444d9d078c63f4;
filename	WEB3GentradeTradingTimeManagementTest.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (äî)ëÂòaëçå§ èÿåîÉ\ÉäÉÖÅ[ÉVÉáÉìÉVÉXÉeÉÄëÊìÒïî
File Name        : éÊà¯éûä‘ä«óùÉeÉXÉg(WEB3GentradeTradingTimeManagementTest.java)
Author Name      : Daiwa Institute of Research
Revision History : 2007/06/18  âhÉC (íÜêu) êVãKçÏê¨
*/
package webbroker3.gentrade;

import java.sql.Timestamp;
import java.util.Date;

import com.fitechlabs.xtrade.kernel.util.ThreadLocalSystemAttributesRegistry;
import com.fitechlabs.xtrade.plugin.tc.gentrade.ProductTypeEnum;
import com.fitechlabs.xtrade.plugin.tc.gentrade.data.BranchParams;
import com.fitechlabs.xtrade.plugin.tc.gentrade.data.BranchRow;
import com.fitechlabs.xtrade.plugin.tc.gentrade.data.InstitutionParams;

import test.util.TestDBUtility;

import webbroker3.common.WEB3BaseException;
import webbroker3.common.WEB3BusinessLayerException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.common.WEB3SystemLayerException;
import webbroker3.gentrade.data.BranchPreferencesParams;
import webbroker3.gentrade.data.BranchPreferencesRow;
import webbroker3.gentrade.data.CalendarParams;
import webbroker3.gentrade.data.CalendarRow;
import webbroker3.gentrade.data.FeqCalendarParams;
import webbroker3.gentrade.data.FeqCalendarRow;
import webbroker3.gentrade.data.OrderAcceptStatusParams;
import webbroker3.gentrade.data.OrderAcceptStatusRow;
import webbroker3.gentrade.data.OrderexecutionEndParams;
import webbroker3.gentrade.data.OrderexecutionEndRow;
import webbroker3.gentrade.data.TradingTimeParams;
import webbroker3.gentrade.data.TradingTimeRow;
import webbroker3.mock.TestBaseForMock;
import webbroker3.util.WEB3DateUtility;
import webbroker3.util.WEB3LogUtility;

/**
 * (éÊà¯éûä‘ä«óùÉeÉXÉg)<BR>
 * 
 * @@author âhÉC (íÜêu)
 * @@version 1.0
 */
public class WEB3GentradeTradingTimeManagementTest extends TestBaseForMock
{
    /**
     * ÉçÉOÉÜÅ[ÉeÉBÉäÉeÉB<BR>
     */
    private static WEB3LogUtility log = WEB3LogUtility.getInstance(
            WEB3GentradeTradingTimeManagementTest.class);

    public WEB3GentradeTradingTimeManagementTest(String arg0)
    {
        super(arg0);
    }

    protected void setUp() throws Exception
    {
        super.setUp();
        TestDBUtility.deleteAll(TradingTimeRow.TYPE);
        TestDBUtility.deleteAll(CalendarRow.TYPE);
        TestDBUtility.deleteAll(FeqCalendarRow.TYPE);
        TestDBUtility.deleteAll(BranchRow.TYPE);
        TestDBUtility.deleteAll(BranchPreferencesRow.TYPE);
        TestDBUtility.deleteAll(OrderexecutionEndRow.TYPE);
        TestDBUtility.deleteAll(OrderAcceptStatusRow.TYPE);
    }

    protected void tearDown() throws Exception
    {
        super.tearDown();
        TestDBUtility.deleteAll(TradingTimeRow.TYPE);
        TestDBUtility.deleteAll(CalendarRow.TYPE);
        TestDBUtility.deleteAll(FeqCalendarRow.TYPE);
        TestDBUtility.deleteAll(BranchRow.TYPE);
        TestDBUtility.deleteAll(BranchPreferencesRow.TYPE);
        TestDBUtility.deleteAll(OrderexecutionEndRow.TYPE);
        TestDBUtility.deleteAll(OrderAcceptStatusRow.TYPE);
    }

    /*
     * testGetTradeCloseTimeSessionTimeZone_Case0001
     */
   public void testGetTradeCloseTimeSessionTimeZone_Case0001()
    {
        final String STR_METHOD_NAME = " testGetTradeCloseTimeSessionTimeZone_Case0001()";
        log.entering(TEST_START + STR_METHOD_NAME);

        TestBaseForMock.MOCK_MANAGER.setIsMockUsed(false);
        try
        {
            ThreadLocalSystemAttributesRegistry.setAttribute(
                "xblocks.gtl.attributes.systemtimestamp",
                new Timestamp(WEB3DateUtility.getDate("20040717110000","yyyyMMddHHmmss").getTime()));
            WEB3GentradeTradingClendarContext l_context = new WEB3GentradeTradingClendarContext();
            l_context.setInstitutionCode("0D");
            l_context.setBranchCode("381");
            l_context.setTradingTimeType("26");
            l_context.setMarketCode("SP");
            l_context.setProductCode("0");
            ThreadLocalSystemAttributesRegistry.setAttribute("web3.tradingcalendarcontext", l_context);

            TradingTimeParams l_tradingTimeParams = new TradingTimeParams(TestDBUtility.getTradingTimeRow());
            l_tradingTimeParams.setBizDateType("0");
            l_tradingTimeParams.setStartTime("100000");
            l_tradingTimeParams.setEndTime("150000");
            l_tradingTimeParams.setBizdateCalcParameter("0");
            l_tradingTimeParams.setSubmitMarketTrigger("1");
            l_tradingTimeParams.setSessionType("1");
            TestDBUtility.insertWithDel(l_tradingTimeParams);

            String l_strEndTime = WEB3GentradeTradingTimeManagement.getTradeCloseTimeSessionTimeZone("SP", "0");
            assertEquals("150000", l_strEndTime);
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
     * testGetTradeCloseTimeSessionTimeZone_Case0002
     */
    public void testGetTradeCloseTimeSessionTimeZone_Case0002()
    {
        final String STR_METHOD_NAME = " testGetTradeCloseTimeSessionTimeZone_Case0002()";
        log.entering(TEST_START + STR_METHOD_NAME);

        TestBaseForMock.MOCK_MANAGER.setIsMockUsed(false);
        try
        {
            ThreadLocalSystemAttributesRegistry.setAttribute(
                "xblocks.gtl.attributes.systemtimestamp",
                new Timestamp(WEB3DateUtility.getDate("20040718110000","yyyyMMddHHmmss").getTime()));
            WEB3GentradeTradingClendarContext l_context = new WEB3GentradeTradingClendarContext();
            l_context.setInstitutionCode("0D");
            l_context.setBranchCode("381");
            l_context.setTradingTimeType("03");
            l_context.setMarketCode("SP");
            l_context.setProductCode("0");
            ThreadLocalSystemAttributesRegistry.setAttribute("web3.tradingcalendarcontext", l_context);

            TradingTimeParams l_tradingTimeParams = new TradingTimeParams(TestDBUtility.getTradingTimeRow());
            l_tradingTimeParams.setBizDateType("0");
            l_tradingTimeParams.setStartTime("100000");
            l_tradingTimeParams.setEndTime("150000");
            l_tradingTimeParams.setBizdateCalcParameter("0");
            l_tradingTimeParams.setTradingTimeType("03");
            l_tradingTimeParams.setSubmitMarketTrigger("0");
            l_tradingTimeParams.setSessionType("1");
            TestDBUtility.insertWithDel(l_tradingTimeParams);

            String l_strEndTime = WEB3GentradeTradingTimeManagement.getTradeCloseTimeSessionTimeZone("SP", "0");
            assertEquals("150000", l_strEndTime);
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
     * testGetTradeCloseTimeSessionTimeZone_Case0003
     */
    public void testGetTradeCloseTimeSessionTimeZone_Case0003()
    {
        final String STR_METHOD_NAME = " testGetTradeCloseTimeSessionTimeZone_Case0003()";
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

            TradingTimeParams l_tradingTimeParams = new TradingTimeParams(TestDBUtility.getTradingTimeRow());
            l_tradingTimeParams.setBizDateType("2");
            l_tradingTimeParams.setStartTime("100000");
            l_tradingTimeParams.setEndTime("150000");
            l_tradingTimeParams.setBizdateCalcParameter("0");
            l_tradingTimeParams.setSubmitMarketTrigger("1");
            l_tradingTimeParams.setSessionType("1");
            TestDBUtility.insertWithDel(l_tradingTimeParams);
            l_tradingTimeParams.setStartTime("090000");
            l_tradingTimeParams.setEndTime("160000");
            TestDBUtility.insertWithDel(l_tradingTimeParams);
            l_tradingTimeParams.setStartTime("103000");
            l_tradingTimeParams.setEndTime("153000");
            TestDBUtility.insertWithDel(l_tradingTimeParams);
            CalendarParams l_calendarParams = new CalendarParams();
            l_calendarParams.setHoliday(new Timestamp(WEB3DateUtility.getDate("20040715","yyyyMMdd").getTime()));
            l_calendarParams.setBizDateType("2");
            l_calendarParams.setCreatedTimestamp(WEB3DateUtility.getDate("20040707","yyyyMMdd"));
            l_calendarParams.setLastUpdatedTimestamp(WEB3DateUtility.getDate("20040707","yyyyMMdd"));
            TestDBUtility.insertWithDel(l_calendarParams);

            String l_strEndTime = WEB3GentradeTradingTimeManagement.getTradeCloseTimeSessionTimeZone("SP", "0");
            assertEquals("153000", l_strEndTime);
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
     * testGetTradeCloseTimeSessionTimeZone_Case0004
     */
    public void testGetTradeCloseTimeSessionTimeZone_Case0004()
    {
        final String STR_METHOD_NAME = " testGetTradeCloseTimeSessionTimeZone_Case0004()";
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

            TradingTimeParams l_tradingTimeParams = new TradingTimeParams(TestDBUtility.getTradingTimeRow());
            l_tradingTimeParams.setBizDateType("1");
            l_tradingTimeParams.setStartTime("100000");
            l_tradingTimeParams.setEndTime("160000");
            l_tradingTimeParams.setBizdateCalcParameter("0");
            l_tradingTimeParams.setSubmitMarketTrigger("1");
            l_tradingTimeParams.setSessionType("1");
            TestDBUtility.insertWithDel(l_tradingTimeParams);

            String l_strEndTime = WEB3GentradeTradingTimeManagement.getTradeCloseTimeSessionTimeZone("SP", "0");
            assertEquals("160000", l_strEndTime);
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
     * testGetTradeCloseTimeSessionTimeZone_Case0005
     */
    public void testGetTradeCloseTimeSessionTimeZone_Case0005()
    {
        final String STR_METHOD_NAME = " testGetTradeCloseTimeSessionTimeZone_Case0005()";
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

            TradingTimeParams l_tradingTimeParams = new TradingTimeParams(TestDBUtility.getTradingTimeRow());
            l_tradingTimeParams.setBizDateType("1");
            l_tradingTimeParams.setStartTime("100000");
            l_tradingTimeParams.setEndTime("160000");
            l_tradingTimeParams.setBizdateCalcParameter("0");
            l_tradingTimeParams.setSubmitMarketTrigger("0");
            l_tradingTimeParams.setSessionType("1");
            TestDBUtility.insertWithDel(l_tradingTimeParams);

            String l_strEndTime = WEB3GentradeTradingTimeManagement.getTradeCloseTimeSessionTimeZone("SP", "0");
            assertEquals("235959", l_strEndTime);
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
     * testGetTradeCloseTimeSessionTimeZone_Case0006
     */
    public void testGetTradeCloseTimeSessionTimeZone_Case0006()
    {
        final String STR_METHOD_NAME = " testGetTradeCloseTimeSessionTimeZone_Case0006()";
        log.entering(TEST_START + STR_METHOD_NAME);

        TestBaseForMock.MOCK_MANAGER.setIsMockUsed(false);
        try
        {
            ThreadLocalSystemAttributesRegistry.setAttribute(
                "xblocks.gtl.attributes.systemtimestamp",
                new Timestamp(WEB3DateUtility.getDate("20040717110000","yyyyMMddHHmmss").getTime()));
            WEB3GentradeTradingClendarContext l_context = new WEB3GentradeTradingClendarContext();
            l_context.setInstitutionCode("0D");
            l_context.setBranchCode("381");
            l_context.setTradingTimeType("26");
            l_context.setMarketCode("SP");
            l_context.setProductCode("0");
            ThreadLocalSystemAttributesRegistry.setAttribute("web3.tradingcalendarcontext", l_context);

            TradingTimeParams l_tradingTimeParams = new TradingTimeParams(TestDBUtility.getTradingTimeRow());
            l_tradingTimeParams.setBizDateType("0");
            l_tradingTimeParams.setStartTime("100000");
            l_tradingTimeParams.setEndTime("150000");
            l_tradingTimeParams.setBizdateCalcParameter("0");
            l_tradingTimeParams.setSubmitMarketTrigger("1");
            l_tradingTimeParams.setSessionType(null);
            TestDBUtility.insertWithDel(l_tradingTimeParams);

            String l_strEndTime = WEB3GentradeTradingTimeManagement.getTradeCloseTimeSessionTimeZone("SP", "0");
            assertEquals("150000", l_strEndTime);
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
     * testGetTradeCloseTimeSessionTimeZone_Case0007
     */
    public void testGetTradeCloseTimeSessionTimeZone_Case0007()
    {
        final String STR_METHOD_NAME = " testGetTradeCloseTimeSessionTimeZone_Case0007()";
        log.entering(TEST_START + STR_METHOD_NAME);

        TestBaseForMock.MOCK_MANAGER.setIsMockUsed(false);
        try
        {
            ThreadLocalSystemAttributesRegistry.setAttribute(
                "xblocks.gtl.attributes.systemtimestamp",
                new Timestamp(WEB3DateUtility.getDate("20040717110000","yyyyMMddHHmmss").getTime()));
            WEB3GentradeTradingClendarContext l_context = new WEB3GentradeTradingClendarContext();
            l_context.setInstitutionCode("0D");
            l_context.setBranchCode("381");
            l_context.setTradingTimeType("26");
            l_context.setMarketCode("ma");
            l_context.setProductCode("7");
            ThreadLocalSystemAttributesRegistry.setAttribute("web3.tradingcalendarcontext", l_context);

            TradingTimeParams l_tradingTimeParams = new TradingTimeParams(TestDBUtility.getTradingTimeRow());
            l_tradingTimeParams.setBizDateType("0");
            l_tradingTimeParams.setStartTime("100000");
            l_tradingTimeParams.setEndTime("150000");
            l_tradingTimeParams.setBizdateCalcParameter("0");
            l_tradingTimeParams.setSubmitMarketTrigger("1");
            l_tradingTimeParams.setSessionType("1");
            TestDBUtility.insertWithDel(l_tradingTimeParams);

            String l_strEndTime = WEB3GentradeTradingTimeManagement.getTradeCloseTimeSessionTimeZone("SP", "0");
            assertEquals("150000", l_strEndTime);
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
     * testIsEveningSessionTimeZone_Case0001
     */
    public void testIsEveningSessionTimeZone_Case0001()
    {
        final String STR_METHOD_NAME = " testIsEveningSessionTimeZone_Case0001()";
        log.entering(TEST_START + STR_METHOD_NAME);

        TestBaseForMock.MOCK_MANAGER.setIsMockUsed(false);
        try
        {
            ThreadLocalSystemAttributesRegistry.setAttribute(
                "xblocks.gtl.attributes.systemtimestamp",
                new Timestamp(WEB3DateUtility.getDate("20040717110000","yyyyMMddHHmmss").getTime()));
            WEB3GentradeTradingClendarContext l_context = new WEB3GentradeTradingClendarContext();
            l_context.setInstitutionCode("0D");
            l_context.setBranchCode("381");
            l_context.setTradingTimeType("26");
            l_context.setMarketCode("SP");
            l_context.setProductCode("0");
            ThreadLocalSystemAttributesRegistry.setAttribute("web3.tradingcalendarcontext", l_context);

            TradingTimeParams l_tradingTimeParams = new TradingTimeParams(TestDBUtility.getTradingTimeRow());
            l_tradingTimeParams.setBizDateType("0");
            l_tradingTimeParams.setStartTime("100000");
            l_tradingTimeParams.setEndTime("150000");
            l_tradingTimeParams.setBizdateCalcParameter("0");
            l_tradingTimeParams.setSubmitMarketTrigger("1");
            l_tradingTimeParams.setSessionType("1");
            TestDBUtility.insertWithDel(l_tradingTimeParams);

            boolean l_blnSessionType = WEB3GentradeTradingTimeManagement.isEveningSessionTimeZone();
            assertEquals(true, l_blnSessionType);
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
     * testIsEveningSessionTimeZone_Case0002
     */
    public void testIsEveningSessionTimeZone_Case0002()
    {
        final String STR_METHOD_NAME = " testIsEveningSessionTimeZone_Case0002()";
        log.entering(TEST_START + STR_METHOD_NAME);

        TestBaseForMock.MOCK_MANAGER.setIsMockUsed(false);
        try
        {
            ThreadLocalSystemAttributesRegistry.setAttribute(
                "xblocks.gtl.attributes.systemtimestamp",
                new Timestamp(WEB3DateUtility.getDate("20040717110000","yyyyMMddHHmmss").getTime()));
            WEB3GentradeTradingClendarContext l_context = new WEB3GentradeTradingClendarContext();
            l_context.setInstitutionCode("0D");
            l_context.setBranchCode("381");
            l_context.setTradingTimeType("26");
            l_context.setMarketCode("SP");
            l_context.setProductCode("0");
            ThreadLocalSystemAttributesRegistry.setAttribute("web3.tradingcalendarcontext", l_context);

            TradingTimeParams l_tradingTimeParams = new TradingTimeParams(TestDBUtility.getTradingTimeRow());
            l_tradingTimeParams.setBizDateType("0");
            l_tradingTimeParams.setStartTime("100000");
            l_tradingTimeParams.setEndTime("150000");
            l_tradingTimeParams.setBizdateCalcParameter("0");
            l_tradingTimeParams.setSubmitMarketTrigger("1");
            l_tradingTimeParams.setSessionType("2");
            TestDBUtility.insertWithDel(l_tradingTimeParams);

            boolean l_blnSessionType = WEB3GentradeTradingTimeManagement.isEveningSessionTimeZone();
            assertEquals(false, l_blnSessionType);
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
     * testGetSessionType_Case0001
     */
    public void testGetSessionType_Case0001()
    {
        final String STR_METHOD_NAME = " testGetSessionType_Case0001()";
        log.entering(TEST_START + STR_METHOD_NAME);

        TestBaseForMock.MOCK_MANAGER.setIsMockUsed(false);
        try
        {
            ThreadLocalSystemAttributesRegistry.setAttribute(
                "xblocks.gtl.attributes.systemtimestamp",
                new Timestamp(WEB3DateUtility.getDate("20040717110000","yyyyMMddHHmmss").getTime()));
            WEB3GentradeTradingClendarContext l_context = new WEB3GentradeTradingClendarContext();
            l_context.setInstitutionCode("0D");
            l_context.setBranchCode("381");
            l_context.setTradingTimeType("26");
            l_context.setMarketCode("SP");
            l_context.setProductCode("0");
            ThreadLocalSystemAttributesRegistry.setAttribute("web3.tradingcalendarcontext", l_context);

            TradingTimeParams l_tradingTimeParams = new TradingTimeParams(TestDBUtility.getTradingTimeRow());
            l_tradingTimeParams.setBizDateType("0");
            l_tradingTimeParams.setStartTime("100000");
            l_tradingTimeParams.setEndTime("150000");
            l_tradingTimeParams.setBizdateCalcParameter("0");
            l_tradingTimeParams.setSubmitMarketTrigger("1");
            l_tradingTimeParams.setSessionType("1");
            TestDBUtility.insertWithDel(l_tradingTimeParams);

            String l_strSessionType = WEB3GentradeTradingTimeManagement.getSessionType();
            assertEquals("1", l_strSessionType);
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
     * testGetSessionType_Case0002
     */
    public void testGetSessionType_Case0002()
    {
        final String STR_METHOD_NAME = " testGetSessionType_Case0002()";
        log.entering(TEST_START + STR_METHOD_NAME);

        TestBaseForMock.MOCK_MANAGER.setIsMockUsed(false);
        try
        {
            ThreadLocalSystemAttributesRegistry.setAttribute(
                "xblocks.gtl.attributes.systemtimestamp",
                new Timestamp(WEB3DateUtility.getDate("20040718110000","yyyyMMddHHmmss").getTime()));
            WEB3GentradeTradingClendarContext l_context = new WEB3GentradeTradingClendarContext();
            l_context.setInstitutionCode("0D");
            l_context.setBranchCode("381");
            l_context.setTradingTimeType("26");
            l_context.setMarketCode("SP");
            l_context.setProductCode("0");
            ThreadLocalSystemAttributesRegistry.setAttribute("web3.tradingcalendarcontext", l_context);

            TradingTimeParams l_tradingTimeParams = new TradingTimeParams(TestDBUtility.getTradingTimeRow());
            l_tradingTimeParams.setBizDateType("0");
            l_tradingTimeParams.setStartTime("100000");
            l_tradingTimeParams.setEndTime("150000");
            l_tradingTimeParams.setBizdateCalcParameter("0");
            l_tradingTimeParams.setSubmitMarketTrigger("1");
            l_tradingTimeParams.setSessionType("1");
            TestDBUtility.insertWithDel(l_tradingTimeParams);

            String l_strSessionType = WEB3GentradeTradingTimeManagement.getSessionType();
            assertEquals("1", l_strSessionType);
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
     * testGetSessionType_Case0003
     */
    public void testGetSessionType_Case0003()
    {
        final String STR_METHOD_NAME = " testGetSessionType_Case0003()";
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
            l_context.setTradingTimeType("10");
            l_context.setMarketCode("SP");
            l_context.setProductCode("0");
            ThreadLocalSystemAttributesRegistry.setAttribute("web3.tradingcalendarcontext", l_context);

            TradingTimeParams l_tradingTimeParams = new TradingTimeParams(TestDBUtility.getTradingTimeRow());
            l_tradingTimeParams.setBizDateType("2");
            l_tradingTimeParams.setStartTime("100000");
            l_tradingTimeParams.setEndTime("150000");
            l_tradingTimeParams.setBizdateCalcParameter("0");
            l_tradingTimeParams.setTradingTimeType("10");
            l_tradingTimeParams.setSubmitMarketTrigger("1");
            l_tradingTimeParams.setSessionType("1");
            TestDBUtility.insertWithDel(l_tradingTimeParams);
            CalendarParams l_calendarParams = new CalendarParams();
            l_calendarParams.setHoliday(new Timestamp(WEB3DateUtility.getDate("20040715","yyyyMMdd").getTime()));
            l_calendarParams.setBizDateType("2");
            l_calendarParams.setCreatedTimestamp(WEB3DateUtility.getDate("20040707","yyyyMMdd"));
            l_calendarParams.setLastUpdatedTimestamp(WEB3DateUtility.getDate("20040707","yyyyMMdd"));
            TestDBUtility.insertWithDel(l_calendarParams);

            String l_strSessionType = WEB3GentradeTradingTimeManagement.getSessionType();
            assertEquals("1", l_strSessionType);
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
     * testGetSessionType_Case0004
     */
    public void testGetSessionType_Case0004()
    {
        final String STR_METHOD_NAME = " testGetSessionType_Case0004()";
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
            l_context.setTradingTimeType("10");
            l_context.setMarketCode("SP");
            l_context.setProductCode("0");
            ThreadLocalSystemAttributesRegistry.setAttribute("web3.tradingcalendarcontext", l_context);

            TradingTimeParams l_tradingTimeParams = new TradingTimeParams(TestDBUtility.getTradingTimeRow());
            l_tradingTimeParams.setBizDateType("1");
            l_tradingTimeParams.setStartTime("100000");
            l_tradingTimeParams.setEndTime("150000");
            l_tradingTimeParams.setBizdateCalcParameter("0");
            l_tradingTimeParams.setTradingTimeType("10");
            l_tradingTimeParams.setSubmitMarketTrigger("1");
            l_tradingTimeParams.setSessionType("1");
            TestDBUtility.insertWithDel(l_tradingTimeParams);
            FeqCalendarParams l_feqCalendarParams = new FeqCalendarParams();
            l_feqCalendarParams.setInstitutionCode("0D");
            l_feqCalendarParams.setMarketCode("SP");
            l_feqCalendarParams.setBizDate(new Timestamp(WEB3DateUtility.getDate("20040715","yyyyMMdd").getTime()));
            l_feqCalendarParams.setBizDateType("2");
            l_feqCalendarParams.setCreatedTimestamp(WEB3DateUtility.getDate("20040707","yyyyMMdd"));
            l_feqCalendarParams.setLastUpdatedTimestamp(WEB3DateUtility.getDate("20040707","yyyyMMdd"));
            TestDBUtility.insertWithDel(l_feqCalendarParams);

            String l_strSessionType = WEB3GentradeTradingTimeManagement.getSessionType();
            assertEquals("1", l_strSessionType);
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
     * testGetSessionType_Case0005
     */
    public void testGetSessionType_Case0005()
    {
        final String STR_METHOD_NAME = " testGetSessionType_Case0005()";
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
            l_context.setTradingTimeType("10");
            l_context.setMarketCode("SP");
            l_context.setProductCode("0");
            ThreadLocalSystemAttributesRegistry.setAttribute("web3.tradingcalendarcontext", l_context);

            TradingTimeParams l_tradingTimeParams = new TradingTimeParams(TestDBUtility.getTradingTimeRow());
            l_tradingTimeParams.setBizDateType("0");
            l_tradingTimeParams.setStartTime("100000");
            l_tradingTimeParams.setEndTime("150000");
            l_tradingTimeParams.setBizdateCalcParameter("0");
            l_tradingTimeParams.setTradingTimeType("10");
            l_tradingTimeParams.setSubmitMarketTrigger("1");
            l_tradingTimeParams.setSessionType("1");
            TestDBUtility.insertWithDel(l_tradingTimeParams);
            CalendarParams l_calendarParams = new CalendarParams();
            l_calendarParams.setHoliday(new Timestamp(WEB3DateUtility.getDate("20040715","yyyyMMdd").getTime()));
            l_calendarParams.setBizDateType("2");
            l_calendarParams.setCreatedTimestamp(WEB3DateUtility.getDate("20040707","yyyyMMdd"));
            l_calendarParams.setLastUpdatedTimestamp(WEB3DateUtility.getDate("20040707","yyyyMMdd"));
            TestDBUtility.insertWithDel(l_calendarParams);
            FeqCalendarParams l_feqCalendarParams = new FeqCalendarParams();
            l_feqCalendarParams.setInstitutionCode("0D");
            l_feqCalendarParams.setMarketCode("SP");
            l_feqCalendarParams.setBizDate(new Timestamp(WEB3DateUtility.getDate("20040715","yyyyMMdd").getTime()));
            l_feqCalendarParams.setBizDateType("0");
            l_feqCalendarParams.setCreatedTimestamp(WEB3DateUtility.getDate("20040707","yyyyMMdd"));
            l_feqCalendarParams.setLastUpdatedTimestamp(WEB3DateUtility.getDate("20040707","yyyyMMdd"));
            TestDBUtility.insertWithDel(l_feqCalendarParams);

            String l_strSessionType = WEB3GentradeTradingTimeManagement.getSessionType();
            assertEquals("1", l_strSessionType);
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
     * testGetSessionType_Case0006
     */
    public void testGetSessionType_Case0006()
    {
        final String STR_METHOD_NAME = " testGetSessionType_Case0006()";
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
            l_context.setTradingTimeType("10");
            l_context.setMarketCode("SP");
            l_context.setProductCode("0");
            ThreadLocalSystemAttributesRegistry.setAttribute("web3.tradingcalendarcontext", l_context);

            TradingTimeParams l_tradingTimeParams = new TradingTimeParams(TestDBUtility.getTradingTimeRow());
            l_tradingTimeParams.setBizDateType("2");
            l_tradingTimeParams.setStartTime("100000");
            l_tradingTimeParams.setEndTime("150000");
            l_tradingTimeParams.setBizdateCalcParameter("0");
            l_tradingTimeParams.setTradingTimeType("10");
            l_tradingTimeParams.setSubmitMarketTrigger("1");
            l_tradingTimeParams.setSessionType("1");
            TestDBUtility.insertWithDel(l_tradingTimeParams);
            CalendarParams l_calendarParams = new CalendarParams();
            l_calendarParams.setHoliday(new Timestamp(WEB3DateUtility.getDate("20040715","yyyyMMdd").getTime()));
            l_calendarParams.setBizDateType("2");
            l_calendarParams.setCreatedTimestamp(WEB3DateUtility.getDate("20040707","yyyyMMdd"));
            l_calendarParams.setLastUpdatedTimestamp(WEB3DateUtility.getDate("20040707","yyyyMMdd"));
            TestDBUtility.insertWithDel(l_calendarParams);
            FeqCalendarParams l_feqCalendarParams = new FeqCalendarParams();
            l_feqCalendarParams.setInstitutionCode("0D");
            l_feqCalendarParams.setMarketCode("SP");
            l_feqCalendarParams.setBizDate(new Timestamp(WEB3DateUtility.getDate("20040715","yyyyMMdd").getTime()));
            l_feqCalendarParams.setBizDateType("0");
            l_feqCalendarParams.setCreatedTimestamp(WEB3DateUtility.getDate("20040707","yyyyMMdd"));
            l_feqCalendarParams.setLastUpdatedTimestamp(WEB3DateUtility.getDate("20040707","yyyyMMdd"));
            TestDBUtility.insertWithDel(l_feqCalendarParams);

            String l_strSessionType = WEB3GentradeTradingTimeManagement.getSessionType();
            fail();
        }
        catch (WEB3SystemLayerException l_sle)
        {
            assertEquals(WEB3ErrorCatalog.SYSTEM_ERROR_80006, l_sle.getErrorInfo());
            log.exiting(TEST_END + STR_METHOD_NAME);
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
     * testValidateTradeCloseChangeOrCancelProductTypeEnumStringWEB3GentradeBranchStringDate_Case0001
     */
    public void testValidateTradeCloseChangeOrCancelProductTypeEnumStringWEB3GentradeBranchStringDate_Case0001()
    {
        final String STR_METHOD_NAME = " testValidateTradeCloseChangeOrCancelProductTypeEnumStringWEB3GentradeBranchStringDate_Case0001()";
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

            TradingTimeParams l_tradingTimeParams = new TradingTimeParams(TestDBUtility.getTradingTimeRow());
            l_tradingTimeParams.setBizDateType("1");
            l_tradingTimeParams.setStartTime("100000");
            l_tradingTimeParams.setEndTime("150000");
            l_tradingTimeParams.setBizdateCalcParameter("0");
            l_tradingTimeParams.setSubmitMarketTrigger("0");
            l_tradingTimeParams.setSessionType("1");
            TestDBUtility.insertWithDel(l_tradingTimeParams);
            BranchParams l_branchParams = new BranchParams(TestDBUtility.getBranchRow());
            TestDBUtility.insertWithDel(l_branchParams);
            BranchPreferencesParams l_branchPreferencesParams =
                new BranchPreferencesParams(TestDBUtility.getBranchPreferencesRow());
            l_branchPreferencesParams.setBranchId(33381);
            l_branchPreferencesParams.setName("evening.session.div");
            l_branchPreferencesParams.setValue("1");
            l_branchPreferencesParams.setNameSerialNo(2);
            TestDBUtility.insertWithDel(l_branchPreferencesParams);

            ProductTypeEnum l_productType = new ProductTypeEnum(6, "IFO");

            OrderexecutionEndParams l_orderexecutionEndParams =
                new OrderexecutionEndParams(TestDBUtility.getOrderexecutionEndRow());
            l_orderexecutionEndParams.setInstitutionCode("0D");
            l_orderexecutionEndParams.setProductType(l_productType);
            l_orderexecutionEndParams.setFutureOptionDiv("0");
            l_orderexecutionEndParams.setOrderexecutionEndType("1");
            TestDBUtility.insertWithDel(l_orderexecutionEndParams);

            WEB3GentradeBranch l_branch = new WEB3GentradeBranch(33381);
            Date l_datBizDate = WEB3DateUtility.getDate("20040717","yyyyMMdd");
            WEB3GentradeTradingTimeManagement.validateTradeCloseChangeOrCancel(l_productType, "0", l_branch, "0", l_datBizDate);
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
     * testValidateTradeCloseChangeOrCancelProductTypeEnumStringWEB3GentradeBranchStringDate_Case0002
     */
    public void testValidateTradeCloseChangeOrCancelProductTypeEnumStringWEB3GentradeBranchStringDate_Case0002()
    {
        final String STR_METHOD_NAME = " testValidateTradeCloseChangeOrCancelProductTypeEnumStringWEB3GentradeBranchStringDate_Case0002()";
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

            TradingTimeParams l_tradingTimeParams = new TradingTimeParams(TestDBUtility.getTradingTimeRow());
            l_tradingTimeParams.setBizDateType("1");
            l_tradingTimeParams.setStartTime("100000");
            l_tradingTimeParams.setEndTime("150000");
            l_tradingTimeParams.setBizdateCalcParameter("0");
            l_tradingTimeParams.setSubmitMarketTrigger("0");
            l_tradingTimeParams.setSessionType("1");
            TestDBUtility.insertWithDel(l_tradingTimeParams);
            BranchParams l_branchParams = new BranchParams(TestDBUtility.getBranchRow());
            TestDBUtility.insertWithDel(l_branchParams);
            BranchPreferencesParams l_branchPreferencesParams =
                new BranchPreferencesParams(TestDBUtility.getBranchPreferencesRow());
            l_branchPreferencesParams.setBranchId(33381);
            l_branchPreferencesParams.setName("evening.session.div");
            l_branchPreferencesParams.setValue("1");
            l_branchPreferencesParams.setNameSerialNo(2);
            TestDBUtility.insertWithDel(l_branchPreferencesParams);

            ProductTypeEnum l_productType = new ProductTypeEnum(6, "IFO");

            OrderexecutionEndParams l_orderexecutionEndParams =
                new OrderexecutionEndParams(TestDBUtility.getOrderexecutionEndRow());
            l_orderexecutionEndParams.setInstitutionCode("0D");
            l_orderexecutionEndParams.setProductType(l_productType);
            l_orderexecutionEndParams.setFutureOptionDiv("0");
            l_orderexecutionEndParams.setOrderexecutionEndType("0");
            TestDBUtility.insertWithDel(l_orderexecutionEndParams);

            WEB3GentradeBranch l_branch = new WEB3GentradeBranch(33381);
            Date l_datBizDate = WEB3DateUtility.getDate("20040717","yyyyMMdd");
            WEB3GentradeTradingTimeManagement.validateTradeCloseChangeOrCancel(l_productType, "0", l_branch, "0", l_datBizDate);
            fail();
        }
        catch (WEB3BusinessLayerException l_ble)
        {
            assertEquals(WEB3ErrorCatalog.BUSINESS_ERROR_02824, l_ble.getErrorInfo());
            log.exiting(TEST_END + STR_METHOD_NAME);
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
     * testValidateTradeCloseChangeOrCancelProductTypeEnumStringWEB3GentradeBranchStringDate_Case0003
     */
    public void testValidateTradeCloseChangeOrCancelProductTypeEnumStringWEB3GentradeBranchStringDate_Case0003()
    {
        final String STR_METHOD_NAME = " testValidateTradeCloseChangeOrCancelProductTypeEnumStringWEB3GentradeBranchStringDate_Case0003()";
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
            BranchParams l_branchParams = new BranchParams(TestDBUtility.getBranchRow());
            TestDBUtility.insertWithDel(l_branchParams);
            BranchPreferencesParams l_branchPreferencesParams =
                new BranchPreferencesParams(TestDBUtility.getBranchPreferencesRow());
            l_branchPreferencesParams.setBranchId(33381);
            l_branchPreferencesParams.setName("evening.session.div");
            l_branchPreferencesParams.setValue("0");
            l_branchPreferencesParams.setNameSerialNo(2);
            TestDBUtility.insertWithDel(l_branchPreferencesParams);

            ProductTypeEnum l_productType = new ProductTypeEnum(6, "IFO");

            OrderexecutionEndParams l_orderexecutionEndParams =
                new OrderexecutionEndParams(TestDBUtility.getOrderexecutionEndRow());
            l_orderexecutionEndParams.setInstitutionCode("0D");
            l_orderexecutionEndParams.setProductType(l_productType);
            l_orderexecutionEndParams.setFutureOptionDiv("0");
            l_orderexecutionEndParams.setOrderexecutionEndType("0");
            TestDBUtility.insertWithDel(l_orderexecutionEndParams);

            WEB3GentradeBranch l_branch = new WEB3GentradeBranch(33381);
            Date l_datBizDate = WEB3DateUtility.getDate("20040714","yyyyMMdd");
            WEB3GentradeTradingTimeManagement.validateTradeCloseChangeOrCancel(l_productType, "0", l_branch, "0", l_datBizDate);
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
     * testValidateTradeCloseChangeOrCancelProductTypeEnumStringWEB3GentradeBranchStringDate_Case0004
     */
    public void testValidateTradeCloseChangeOrCancelProductTypeEnumStringWEB3GentradeBranchStringDate_Case0004()
    {
        final String STR_METHOD_NAME = " testValidateTradeCloseChangeOrCancelProductTypeEnumStringWEB3GentradeBranchStringDate_Case0004()";
        log.entering(TEST_START + STR_METHOD_NAME);

        TestBaseForMock.MOCK_MANAGER.setIsMockUsed(false);
        try
        {
            ThreadLocalSystemAttributesRegistry.setAttribute(
                "xblocks.gtl.attributes.systemtimestamp",
                new Timestamp(WEB3DateUtility.getDate("20040717110000","yyyyMMddHHmmss").getTime()));
            WEB3GentradeTradingClendarContext l_context = new WEB3GentradeTradingClendarContext();
            l_context.setInstitutionCode("0D");
            l_context.setBranchCode("381");
            l_context.setTradingTimeType("26");
            l_context.setMarketCode("SP");
            l_context.setProductCode("0");
            ThreadLocalSystemAttributesRegistry.setAttribute("web3.tradingcalendarcontext", l_context);
            ThreadLocalSystemAttributesRegistry.setAttribute(
                "web3.attributes.basetimestampfororderbizdate",
                new Timestamp(WEB3DateUtility.getDate("20040717110000","yyyyMMddHHmmss").getTime()));

            TradingTimeParams l_tradingTimeParams = new TradingTimeParams(TestDBUtility.getTradingTimeRow());
            l_tradingTimeParams.setBizDateType("0");
            l_tradingTimeParams.setStartTime("100000");
            l_tradingTimeParams.setEndTime("150000");
            l_tradingTimeParams.setBizdateCalcParameter("0");
            l_tradingTimeParams.setSubmitMarketTrigger("0");
            l_tradingTimeParams.setSessionType("1");
            TestDBUtility.insertWithDel(l_tradingTimeParams);
            BranchParams l_branchParams = new BranchParams(TestDBUtility.getBranchRow());
            TestDBUtility.insertWithDel(l_branchParams);
            BranchPreferencesParams l_branchPreferencesParams =
                new BranchPreferencesParams(TestDBUtility.getBranchPreferencesRow());
            l_branchPreferencesParams.setBranchId(33381);
            l_branchPreferencesParams.setName("evening.session.div");
            l_branchPreferencesParams.setValue("1");
            l_branchPreferencesParams.setNameSerialNo(2);
            TestDBUtility.insertWithDel(l_branchPreferencesParams);

            ProductTypeEnum l_productType = new ProductTypeEnum(6, "IFO");

            OrderexecutionEndParams l_orderexecutionEndParams =
                new OrderexecutionEndParams(TestDBUtility.getOrderexecutionEndRow());
            l_orderexecutionEndParams.setInstitutionCode("0D");
            l_orderexecutionEndParams.setProductType(l_productType);
            l_orderexecutionEndParams.setFutureOptionDiv("0");
            l_orderexecutionEndParams.setOrderexecutionEndType("1");
            TestDBUtility.insertWithDel(l_orderexecutionEndParams);

            WEB3GentradeBranch l_branch = new WEB3GentradeBranch(33381);
            Date l_datBizDate = WEB3DateUtility.getDate("20040714","yyyyMMdd");
            WEB3GentradeTradingTimeManagement.validateTradeCloseChangeOrCancel(l_productType, "0", l_branch, "0", l_datBizDate);
            fail();
        }
        catch (WEB3BusinessLayerException l_ble)
        {
            assertEquals(WEB3ErrorCatalog.BUSINESS_ERROR_00812, l_ble.getErrorInfo());
            log.exiting(TEST_END + STR_METHOD_NAME);
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
     * testValidateTradeCloseChangeOrCancelProductTypeEnumStringWEB3GentradeBranchStringDate_Case0005
     */
    public void testValidateTradeCloseChangeOrCancelProductTypeEnumStringWEB3GentradeBranchStringDate_Case0005()
    {
        final String STR_METHOD_NAME = " testValidateTradeCloseChangeOrCancelProductTypeEnumStringWEB3GentradeBranchStringDate_Case0005()";
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
            BranchParams l_branchParams = new BranchParams(TestDBUtility.getBranchRow());
            TestDBUtility.insertWithDel(l_branchParams);
            BranchPreferencesParams l_branchPreferencesParams =
                new BranchPreferencesParams(TestDBUtility.getBranchPreferencesRow());
            l_branchPreferencesParams.setBranchId(33381);
            l_branchPreferencesParams.setName("evening.session.div");
            l_branchPreferencesParams.setValue("1");
            l_branchPreferencesParams.setNameSerialNo(2);
            TestDBUtility.insertWithDel(l_branchPreferencesParams);

            ProductTypeEnum l_productType = new ProductTypeEnum(6, "IFO");

            OrderexecutionEndParams l_orderexecutionEndParams =
                new OrderexecutionEndParams(TestDBUtility.getOrderexecutionEndRow());
            l_orderexecutionEndParams.setInstitutionCode("0D");
            l_orderexecutionEndParams.setProductType(l_productType);
            l_orderexecutionEndParams.setFutureOptionDiv("0");
            l_orderexecutionEndParams.setOrderexecutionEndType("1");
            TestDBUtility.insertWithDel(l_orderexecutionEndParams);

            WEB3GentradeBranch l_branch = new WEB3GentradeBranch(33381);
            Date l_datBizDate = WEB3DateUtility.getDate("20040722","yyyyMMdd");
            WEB3GentradeTradingTimeManagement.validateTradeCloseChangeOrCancel(l_productType, "0", l_branch, "1", l_datBizDate);
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
     * testValidateTradeCloseChangeOrCancelProductTypeEnumStringWEB3GentradeBranchStringDate_Case0006
     */
    public void testValidateTradeCloseChangeOrCancelProductTypeEnumStringWEB3GentradeBranchStringDate_Case0006()
    {
        final String STR_METHOD_NAME = " testValidateTradeCloseChangeOrCancelProductTypeEnumStringWEB3GentradeBranchStringDate_Case0006()";
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
            l_tradingTimeParams.setSessionType(null);
            TestDBUtility.insertWithDel(l_tradingTimeParams);
            BranchParams l_branchParams = new BranchParams(TestDBUtility.getBranchRow());
            TestDBUtility.insertWithDel(l_branchParams);
            BranchPreferencesParams l_branchPreferencesParams =
                new BranchPreferencesParams(TestDBUtility.getBranchPreferencesRow());
            l_branchPreferencesParams.setBranchId(33381);
            l_branchPreferencesParams.setName("evening.session.div");
            l_branchPreferencesParams.setValue("1");
            l_branchPreferencesParams.setNameSerialNo(2);
            TestDBUtility.insertWithDel(l_branchPreferencesParams);

            ProductTypeEnum l_productType = new ProductTypeEnum(6, "IFO");

            OrderexecutionEndParams l_orderexecutionEndParams =
                new OrderexecutionEndParams(TestDBUtility.getOrderexecutionEndRow());
            l_orderexecutionEndParams.setInstitutionCode("0D");
            l_orderexecutionEndParams.setProductType(l_productType);
            l_orderexecutionEndParams.setFutureOptionDiv("0");
            l_orderexecutionEndParams.setOrderexecutionEndType("1");
            TestDBUtility.insertWithDel(l_orderexecutionEndParams);

            WEB3GentradeBranch l_branch = new WEB3GentradeBranch(33381);
            Date l_datBizDate = WEB3DateUtility.getDate("20040722","yyyyMMdd");
            WEB3GentradeTradingTimeManagement.validateTradeCloseChangeOrCancel(l_productType, "0", l_branch, null, l_datBizDate);
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
     * testIsSessionTimeZone_Case0001
     */
    public void testIsSessionTimeZone_Case0001()
    {
        final String STR_METHOD_NAME = " testIsSessionTimeZone_Case0001()";
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
            l_context.setTradingTimeType("03");
            l_context.setMarketCode("SP");
            l_context.setProductCode("0");
            ThreadLocalSystemAttributesRegistry.setAttribute("web3.tradingcalendarcontext", l_context);

            TradingTimeParams l_tradingTimeParams = new TradingTimeParams(TestDBUtility.getTradingTimeRow());
            l_tradingTimeParams.setBizDateType("1");
            l_tradingTimeParams.setStartTime("100000");
            l_tradingTimeParams.setEndTime("150000");
            l_tradingTimeParams.setBizdateCalcParameter("0");
            l_tradingTimeParams.setTradingTimeType("03");
            l_tradingTimeParams.setSubmitMarketTrigger("1");
            l_tradingTimeParams.setSessionType("1");
            TestDBUtility.insertWithDel(l_tradingTimeParams);

            boolean l_blnSessionTimeZone = WEB3GentradeTradingTimeManagement.isSessionTimeZone();
            assertEquals(true, l_blnSessionTimeZone);
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
     * testIsSessionTimeZone_Case0002
     */
    public void testIsSessionTimeZone_Case0002()
    {
        final String STR_METHOD_NAME = " testIsSessionTimeZone_Case0002()";
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

            TradingTimeParams l_tradingTimeParams = new TradingTimeParams(TestDBUtility.getTradingTimeRow());
            l_tradingTimeParams.setBizDateType("1");
            l_tradingTimeParams.setStartTime("100000");
            l_tradingTimeParams.setEndTime("150000");
            l_tradingTimeParams.setBizdateCalcParameter("0");
            l_tradingTimeParams.setSubmitMarketTrigger("0");
            l_tradingTimeParams.setSessionType("0");
            TestDBUtility.insertWithDel(l_tradingTimeParams);

            boolean l_blnSessionTimeZone = WEB3GentradeTradingTimeManagement.isSessionTimeZone();
            assertEquals(true, l_blnSessionTimeZone);
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
     * testIsSessionTimeZone_Case0003
     */
    public void testIsSessionTimeZone_Case0003()
    {
        final String STR_METHOD_NAME = " testIsSessionTimeZone_Case0003()";
        log.entering(TEST_START + STR_METHOD_NAME);

        TestBaseForMock.MOCK_MANAGER.setIsMockUsed(false);
        try
        {
            ThreadLocalSystemAttributesRegistry.setAttribute(
                "xblocks.gtl.attributes.systemtimestamp",
                new Timestamp(WEB3DateUtility.getDate("20040717110000","yyyyMMddHHmmss").getTime()));
            WEB3GentradeTradingClendarContext l_context = new WEB3GentradeTradingClendarContext();
            l_context.setInstitutionCode("0D");
            l_context.setBranchCode("381");
            l_context.setTradingTimeType("26");
            l_context.setMarketCode("SP");
            l_context.setProductCode("0");
            ThreadLocalSystemAttributesRegistry.setAttribute("web3.tradingcalendarcontext", l_context);

            TradingTimeParams l_tradingTimeParams = new TradingTimeParams(TestDBUtility.getTradingTimeRow());
            l_tradingTimeParams.setBizDateType("0");
            l_tradingTimeParams.setStartTime("100000");
            l_tradingTimeParams.setEndTime("150000");
            l_tradingTimeParams.setBizdateCalcParameter("0");
            l_tradingTimeParams.setSubmitMarketTrigger("0");
            l_tradingTimeParams.setSessionType("0");
            TestDBUtility.insertWithDel(l_tradingTimeParams);

            boolean l_blnSessionTimeZone = WEB3GentradeTradingTimeManagement.isSessionTimeZone();
            assertEquals(false, l_blnSessionTimeZone);
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
     * testIsTradeCloseTimeZone_Case0001
     */
    public void testIsTradeCloseTimeZone_Case0001()
    {
        final String STR_METHOD_NAME = " testIsTradeCloseTimeZone_Case0001()";
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

            TradingTimeParams l_tradingTimeParams = new TradingTimeParams(TestDBUtility.getTradingTimeRow());
            l_tradingTimeParams.setBizDateType("1");
            l_tradingTimeParams.setStartTime("100000");
            l_tradingTimeParams.setEndTime("150000");
            l_tradingTimeParams.setBizdateCalcParameter("0");
            l_tradingTimeParams.setSubmitMarketTrigger("0");
            l_tradingTimeParams.setSessionType("1");
            TestDBUtility.insertWithDel(l_tradingTimeParams);

            boolean l_blnSessionTimeZone = WEB3GentradeTradingTimeManagement.isTradeCloseTimeZone();
            assertEquals(false, l_blnSessionTimeZone);
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
     * testValidateTradeCloseChangeOrCancelProductTypeEnumString_Case0001
     */
    public void testValidateTradeCloseChangeOrCancelProductTypeEnumString_Case0001()
    {
        final String STR_METHOD_NAME = " testValidateTradeCloseChangeOrCancelProductTypeEnumString_Case0001()";
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

            WEB3GentradeTradingTimeManagement.validateTradeCloseChangeOrCancel(
                ProductTypeEnum.IFO,
                "0");
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
     * testValidateTradeCloseChangeOrCancelProductTypeEnumString_Case0002
     */
    public void testValidateTradeCloseChangeOrCancelProductTypeEnumString_Case0002()
    {
        final String STR_METHOD_NAME = " testValidateTradeCloseChangeOrCancelProductTypeEnumString_Case0002()";
        log.entering(TEST_START + STR_METHOD_NAME);

        TestBaseForMock.MOCK_MANAGER.setIsMockUsed(false);
        try
        {
            ThreadLocalSystemAttributesRegistry.setAttribute(
                "xblocks.gtl.attributes.systemtimestamp",
            new Timestamp(WEB3DateUtility.getDate("20040718110000","yyyyMMddHHmmss").getTime()));
            WEB3GentradeTradingClendarContext l_context = new WEB3GentradeTradingClendarContext();
            l_context.setInstitutionCode("0D");
            l_context.setBranchCode("381");
            l_context.setTradingTimeType("26");
            l_context.setMarketCode("SP");
            l_context.setProductCode("0");
            ThreadLocalSystemAttributesRegistry.setAttribute("web3.tradingcalendarcontext", l_context);

            OrderexecutionEndParams l_orderexecutionEndParams =
                new OrderexecutionEndParams(TestDBUtility.getOrderexecutionEndRow());
            l_orderexecutionEndParams.setInstitutionCode("0D");
            l_orderexecutionEndParams.setProductType(ProductTypeEnum.IFO);
            l_orderexecutionEndParams.setFutureOptionDiv("0");
            l_orderexecutionEndParams.setOrderexecutionEndType("1");
            TestDBUtility.insertWithDel(l_orderexecutionEndParams);

            WEB3GentradeTradingTimeManagement.validateTradeCloseChangeOrCancel(
                ProductTypeEnum.IFO,
                "0");
            fail();
        }
        catch (WEB3BusinessLayerException l_ble)
        {
            log.exiting(TEST_END + STR_METHOD_NAME);
            assertEquals(WEB3ErrorCatalog.BUSINESS_ERROR_00812, l_ble.getErrorInfo());
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
     * testValidateTradeCloseChangeOrCancelProductTypeEnumString_Case0003
     */
    public void testValidateTradeCloseChangeOrCancelProductTypeEnumString_Case0003()
    {
        final String STR_METHOD_NAME = " testValidateTradeCloseChangeOrCancelProductTypeEnumString_Case0003()";
        log.entering(TEST_START + STR_METHOD_NAME);

        TestBaseForMock.MOCK_MANAGER.setIsMockUsed(false);
        try
        {
            ThreadLocalSystemAttributesRegistry.setAttribute(
                "xblocks.gtl.attributes.systemtimestamp",
            new Timestamp(WEB3DateUtility.getDate("20040718110000","yyyyMMddHHmmss").getTime()));
            WEB3GentradeTradingClendarContext l_context = new WEB3GentradeTradingClendarContext();
            l_context.setInstitutionCode("0D");
            l_context.setBranchCode("381");
            l_context.setTradingTimeType("26");
            l_context.setMarketCode("SP");
            l_context.setProductCode("0");
            ThreadLocalSystemAttributesRegistry.setAttribute("web3.tradingcalendarcontext", l_context);

            OrderexecutionEndParams l_orderexecutionEndParams =
                new OrderexecutionEndParams(TestDBUtility.getOrderexecutionEndRow());
            l_orderexecutionEndParams.setInstitutionCode("0D");
            l_orderexecutionEndParams.setProductType(ProductTypeEnum.IFO);
            l_orderexecutionEndParams.setFutureOptionDiv("0");
            l_orderexecutionEndParams.setOrderexecutionEndType("0");
            TestDBUtility.insertWithDel(l_orderexecutionEndParams);

            WEB3GentradeTradingTimeManagement.validateTradeCloseChangeOrCancel(
                ProductTypeEnum.IFO,
                "0");
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
     * testGetOrderBizDate_Date_String_Case0001
     */
    public void testGetOrderBizDate_Date_String_Case0001()
    {
        final String STR_METHOD_NAME = " testGetOrderBizDate_Date_String_Case0001()";
        log.entering(TEST_START + STR_METHOD_NAME);

        TestBaseForMock.MOCK_MANAGER.setIsMockUsed(false);
        try
        {
            ThreadLocalSystemAttributesRegistry.setAttribute(
                "xblocks.gtl.attributes.systemtimestamp",
            new Timestamp(WEB3DateUtility.getDate("20040718110000","yyyyMMddHHmmss").getTime()));
            WEB3GentradeTradingClendarContext l_context = new WEB3GentradeTradingClendarContext();
            l_context.setInstitutionCode("0D");
            l_context.setBranchCode("381");
            l_context.setTradingTimeType("26");
            l_context.setMarketCode("SP");
            l_context.setProductCode("0");
            ThreadLocalSystemAttributesRegistry.setAttribute("web3.tradingcalendarcontext", l_context);
            ThreadLocalSystemAttributesRegistry.setAttribute(
                "web3.attributes.basetimestampfororderbizdate",
                new Timestamp(WEB3DateUtility.getDate("20040718110000","yyyyMMddHHmmss").getTime()));

            TradingTimeParams l_tradingTimeParams = new TradingTimeParams(TestDBUtility.getTradingTimeRow());
            l_tradingTimeParams.setInstitutionCode("0D");
            l_tradingTimeParams.setBranchCode("381");
            l_tradingTimeParams.setMarketCode("SP");
            l_tradingTimeParams.setTradingTimeType("26");
            l_tradingTimeParams.setProductCode("0");
            l_tradingTimeParams.setBizDateType("0");         
            l_tradingTimeParams.setSessionType("1");
            TestDBUtility.insertWithDel(l_tradingTimeParams);

            Date l_bizDate = WEB3GentradeTradingTimeManagement.getOrderBizDate(WEB3DateUtility.getDate("20040719110000","yyyyMMddHHmmss"),"1");
            assertEquals(WEB3DateUtility.getDate("20040719110000","yyyyMMddHHmmss"), l_bizDate);
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
     * testGetOrderBizDate_Date_String_Case0002
     */
    public void testGetOrderBizDate_Date_String_Case0002()
    {
        final String STR_METHOD_NAME = " testGetOrderBizDate_Date_String_Case0002()";
        log.entering(TEST_START + STR_METHOD_NAME);

        TestBaseForMock.MOCK_MANAGER.setIsMockUsed(false);
        try
        {
            ThreadLocalSystemAttributesRegistry.setAttribute(
                "xblocks.gtl.attributes.systemtimestamp",
            new Timestamp(WEB3DateUtility.getDate("20040718110000","yyyyMMddHHmmss").getTime()));
            WEB3GentradeTradingClendarContext l_context = new WEB3GentradeTradingClendarContext();
            l_context.setInstitutionCode("0D");
            l_context.setBranchCode("381");
            l_context.setTradingTimeType("26");
            l_context.setMarketCode("SP");
            l_context.setProductCode("0");
            ThreadLocalSystemAttributesRegistry.setAttribute("web3.tradingcalendarcontext", l_context);
            ThreadLocalSystemAttributesRegistry.setAttribute(
                "web3.attributes.basetimestampfororderbizdate",
                new Timestamp(WEB3DateUtility.getDate("20040718110000","yyyyMMddHHmmss").getTime()));

            TradingTimeParams l_tradingTimeParams = new TradingTimeParams(TestDBUtility.getTradingTimeRow());
            l_tradingTimeParams.setInstitutionCode("0D");
            l_tradingTimeParams.setBranchCode("381");
            l_tradingTimeParams.setMarketCode("SP");
            l_tradingTimeParams.setTradingTimeType("26");
            l_tradingTimeParams.setProductCode("0");
            l_tradingTimeParams.setBizDateType("0");         
            l_tradingTimeParams.setSessionType("1");
            TestDBUtility.insertWithDel(l_tradingTimeParams);

            Date l_bizDate = WEB3GentradeTradingTimeManagement.getOrderBizDate(WEB3DateUtility.getDate("20040719110000","yyyyMMddHHmmss"),"0");
        }
      catch (WEB3BusinessLayerException l_ble)
      {
          log.exiting(TEST_END + STR_METHOD_NAME);
          assertEquals(WEB3ErrorCatalog.BUSINESS_ERROR_02842, l_ble.getErrorInfo());
      }

        catch (Exception l_ex)
        {
            log.error(STR_METHOD_NAME, l_ex);
            log.exiting(STR_METHOD_NAME);
            fail();
        }
        log.exiting(TEST_END + STR_METHOD_NAME);
    }

    /**
     * getPTSOrderBizDate
     */
    public void testGetPTSOrderBizDate_case0001()
    {
        final String STR_METHOD_NAME = " testGetPTSBizDateTypename_case0001()";
        log.entering(TEST_START + STR_METHOD_NAME);

        try
        {
            Date l_datDate =
                WEB3DateUtility.getDate("20040718110000", "yyyyMMddHHmmss");
            Timestamp l_tsDate = new Timestamp(l_datDate.getTime());

            ThreadLocalSystemAttributesRegistry.setAttribute(
                "xblocks.gtl.attributes.systemtimestamp",
                l_tsDate);
            WEB3GentradeTradingClendarContext l_context = null;

            ThreadLocalSystemAttributesRegistry.setAttribute(
                "web3.tradingcalendarcontext", l_context);
            ThreadLocalSystemAttributesRegistry.setAttribute(
                "web3.attributes.basetimestampfororderbizdate",
                l_tsDate);

            Date l_datPTSOrderBizDate =
                WEB3GentradeTradingTimeManagement.getPTSOrderBizDate();

            assertEquals(null, l_datPTSOrderBizDate);
        }
        catch (Exception l_ex)
        {
            log.error(STR_METHOD_NAME, l_ex);
            fail();
        }
        finally
        {
            log.exiting(STR_METHOD_NAME);
        }
    }

    /**
     * getPTSOrderBizDate
     */
    public void testGetPTSOrderBizDate_case0002()
    {
        final String STR_METHOD_NAME = " testGetPTSBizDateTypename_case0002()";
        log.entering(TEST_START + STR_METHOD_NAME);

        try
        {
            Date l_datDate =
                WEB3DateUtility.getDate("20040718110000", "yyyyMMddHHmmss");
            Timestamp l_tsDate = new Timestamp(l_datDate.getTime());

            ThreadLocalSystemAttributesRegistry.setAttribute(
                "xblocks.gtl.attributes.systemtimestamp",
                l_tsDate);
            WEB3GentradeTradingClendarContext l_context =
                new WEB3GentradeTradingClendarContext();
            l_context.setInstitutionCode(null);
            l_context.setBranchCode("381");
            l_context.setTradingTimeType("26");
            l_context.setMarketCode("SP");
            l_context.setProductCode("0");
            ThreadLocalSystemAttributesRegistry.setAttribute(
                "web3.tradingcalendarcontext", l_context);
            ThreadLocalSystemAttributesRegistry.setAttribute(
                "web3.attributes.basetimestampfororderbizdate",
                l_tsDate);

            TradingTimeParams l_tradingTimeParams =
                TestDBUtility.getTradingTimeRow();
            l_tradingTimeParams.setInstitutionCode("0D");
            l_tradingTimeParams.setBranchCode("381");
            l_tradingTimeParams.setMarketCode("SP");
            l_tradingTimeParams.setTradingTimeType("26");
            l_tradingTimeParams.setProductCode("0");
            l_tradingTimeParams.setBizDateType("0");
            l_tradingTimeParams.setSessionType("1");
            TestDBUtility.insertWithDel(l_tradingTimeParams);

            WEB3GentradeTradingTimeManagement.getPTSOrderBizDate();

            fail();
        }
        catch (WEB3BaseException l_ex)
        {
            log.error(STR_METHOD_NAME, l_ex);
            assertEquals(WEB3ErrorCatalog.SYSTEM_ERROR_80006, l_ex.getErrorInfo());
        }
        catch (Exception l_ex)
        {
            log.error(STR_METHOD_NAME, l_ex);
            fail();
        }
        finally
        {
            log.exiting(STR_METHOD_NAME);
        }
    }

    /**
     * getPTSOrderBizDate
     */
    public void testGetPTSOrderBizDate_case0003()
    {
        final String STR_METHOD_NAME = " testGetPTSBizDateTypename_case0003()";
        log.entering(TEST_START + STR_METHOD_NAME);

        try
        {
            Date l_datDate =
                WEB3DateUtility.getDate("20040718110000", "yyyyMMddHHmmss");
            Timestamp l_tsDate = new Timestamp(l_datDate.getTime());

            ThreadLocalSystemAttributesRegistry.setAttribute(
                "xblocks.gtl.attributes.systemtimestamp",
                l_tsDate);
            WEB3GentradeTradingClendarContext l_context =
                new WEB3GentradeTradingClendarContext();
            l_context.setInstitutionCode("0D");
            l_context.setBranchCode(null);
            l_context.setTradingTimeType("26");
            l_context.setMarketCode("SP");
            l_context.setProductCode("0");
            ThreadLocalSystemAttributesRegistry.setAttribute(
                "web3.tradingcalendarcontext", l_context);
            ThreadLocalSystemAttributesRegistry.setAttribute(
                "web3.attributes.basetimestampfororderbizdate",
                l_tsDate);

            TradingTimeParams l_tradingTimeParams =
                TestDBUtility.getTradingTimeRow();
            l_tradingTimeParams.setInstitutionCode("0D");
            l_tradingTimeParams.setBranchCode("381");
            l_tradingTimeParams.setMarketCode("SP");
            l_tradingTimeParams.setTradingTimeType("26");
            l_tradingTimeParams.setProductCode("0");
            l_tradingTimeParams.setBizDateType("0");
            l_tradingTimeParams.setSessionType("1");
            TestDBUtility.insertWithDel(l_tradingTimeParams);

            WEB3GentradeTradingTimeManagement.getPTSOrderBizDate();

            fail();
        }
        catch (WEB3BaseException l_ex)
        {
            log.error(STR_METHOD_NAME, l_ex);
            assertEquals(WEB3ErrorCatalog.SYSTEM_ERROR_80006, l_ex.getErrorInfo());
        }
        catch (Exception l_ex)
        {
            log.error(STR_METHOD_NAME, l_ex);
            fail();
        }
        finally
        {
            log.exiting(STR_METHOD_NAME);
        }
    }

    /**
     * getPTSOrderBizDate
     */
    public void testGetPTSOrderBizDate_case0004()
    {
        final String STR_METHOD_NAME = " testGetPTSBizDateTypename_case0004()";
        log.entering(TEST_START + STR_METHOD_NAME);

        try
        {
            Date l_datDate =
                WEB3DateUtility.getDate("20040718110000", "yyyyMMddHHmmss");
            Timestamp l_tsDate = new Timestamp(l_datDate.getTime());

            ThreadLocalSystemAttributesRegistry.setAttribute(
                "xblocks.gtl.attributes.systemtimestamp",
                l_tsDate);
            WEB3GentradeTradingClendarContext l_context =
                new WEB3GentradeTradingClendarContext();
            l_context.setInstitutionCode("0D");
            l_context.setBranchCode("381");
            l_context.setTradingTimeType("26");
            l_context.setMarketCode(null);
            l_context.setProductCode("0");
            ThreadLocalSystemAttributesRegistry.setAttribute(
                "web3.tradingcalendarcontext", l_context);
            ThreadLocalSystemAttributesRegistry.setAttribute(
                "web3.attributes.basetimestampfororderbizdate",
                l_tsDate);

            TradingTimeParams l_tradingTimeParams =
                TestDBUtility.getTradingTimeRow();
            l_tradingTimeParams.setInstitutionCode("0D");
            l_tradingTimeParams.setBranchCode("381");
            l_tradingTimeParams.setMarketCode("SP");
            l_tradingTimeParams.setTradingTimeType("26");
            l_tradingTimeParams.setProductCode("0");
            l_tradingTimeParams.setBizDateType("0");
            l_tradingTimeParams.setSessionType("1");
            TestDBUtility.insertWithDel(l_tradingTimeParams);

            WEB3GentradeTradingTimeManagement.getPTSOrderBizDate();

            fail();
        }
        catch (WEB3BaseException l_ex)
        {
            log.error(STR_METHOD_NAME, l_ex);
            assertEquals(WEB3ErrorCatalog.SYSTEM_ERROR_80006, l_ex.getErrorInfo());
        }
        catch (Exception l_ex)
        {
            log.error(STR_METHOD_NAME, l_ex);
            fail();
        }
        finally
        {
            log.exiting(STR_METHOD_NAME);
        }
    }

    /**
     * getPTSOrderBizDate
     */
    public void testGetPTSOrderBizDate_case0005()
    {
        final String STR_METHOD_NAME = " testGetPTSBizDateTypename_case0005()";
        log.entering(TEST_START + STR_METHOD_NAME);

        try
        {
            Date l_datDate =
                WEB3DateUtility.getDate("20040718110000", "yyyyMMddHHmmss");
            Timestamp l_tsDate = new Timestamp(l_datDate.getTime());

            ThreadLocalSystemAttributesRegistry.setAttribute(
                "xblocks.gtl.attributes.systemtimestamp",
                l_tsDate);
            WEB3GentradeTradingClendarContext l_context =
                new WEB3GentradeTradingClendarContext();
            l_context.setInstitutionCode("0D");
            l_context.setBranchCode("381");
            l_context.setTradingTimeType(null);
            l_context.setMarketCode("SP");
            l_context.setProductCode("0");
            ThreadLocalSystemAttributesRegistry.setAttribute(
                "web3.tradingcalendarcontext", l_context);
            ThreadLocalSystemAttributesRegistry.setAttribute(
                "web3.attributes.basetimestampfororderbizdate",
                l_tsDate);

            TradingTimeParams l_tradingTimeParams =
                TestDBUtility.getTradingTimeRow();
            l_tradingTimeParams.setInstitutionCode("0D");
            l_tradingTimeParams.setBranchCode("381");
            l_tradingTimeParams.setMarketCode("SP");
            l_tradingTimeParams.setTradingTimeType("26");
            l_tradingTimeParams.setProductCode("0");
            l_tradingTimeParams.setBizDateType("0");
            l_tradingTimeParams.setSessionType("1");
            TestDBUtility.insertWithDel(l_tradingTimeParams);

            WEB3GentradeTradingTimeManagement.getPTSOrderBizDate();

            fail();
        }
        catch (WEB3BaseException l_ex)
        {
            log.error(STR_METHOD_NAME, l_ex);
            assertEquals(WEB3ErrorCatalog.SYSTEM_ERROR_80006, l_ex.getErrorInfo());
        }
        catch (Exception l_ex)
        {
            log.error(STR_METHOD_NAME, l_ex);
            fail();
        }
        finally
        {
            log.exiting(STR_METHOD_NAME);
        }
    }

    /**
     * getPTSOrderBizDate
     */
    public void testGetPTSOrderBizDate_case0006()
    {
        final String STR_METHOD_NAME = " testGetPTSBizDateTypename_case0006()";
        log.entering(TEST_START + STR_METHOD_NAME);

        try
        {
            Date l_datDate =
                WEB3DateUtility.getDate("20040718110000", "yyyyMMddHHmmss");
            Timestamp l_tsDate = new Timestamp(l_datDate.getTime());

            ThreadLocalSystemAttributesRegistry.setAttribute(
                "xblocks.gtl.attributes.systemtimestamp",
                l_tsDate);
            WEB3GentradeTradingClendarContext l_context =
                new WEB3GentradeTradingClendarContext();
            l_context.setInstitutionCode("0D");
            l_context.setBranchCode("381");
            l_context.setTradingTimeType("26");
            l_context.setMarketCode("SP");
            l_context.setProductCode(null);
            ThreadLocalSystemAttributesRegistry.setAttribute(
                "web3.tradingcalendarcontext", l_context);
            ThreadLocalSystemAttributesRegistry.setAttribute(
                "web3.attributes.basetimestampfororderbizdate",
                l_tsDate);

            TradingTimeParams l_tradingTimeParams =
                TestDBUtility.getTradingTimeRow();
            l_tradingTimeParams.setInstitutionCode("0D");
            l_tradingTimeParams.setBranchCode("381");
            l_tradingTimeParams.setMarketCode("SP");
            l_tradingTimeParams.setTradingTimeType("26");
            l_tradingTimeParams.setProductCode("0");
            l_tradingTimeParams.setBizDateType("0");
            l_tradingTimeParams.setSessionType("1");
            TestDBUtility.insertWithDel(l_tradingTimeParams);

            WEB3GentradeTradingTimeManagement.getPTSOrderBizDate();

            fail();
        }
        catch (WEB3BaseException l_ex)
        {
            log.error(STR_METHOD_NAME, l_ex);
            assertEquals(WEB3ErrorCatalog.SYSTEM_ERROR_80006, l_ex.getErrorInfo());
        }
        catch (Exception l_ex)
        {
            log.error(STR_METHOD_NAME, l_ex);
            fail();
        }
        finally
        {
            log.exiting(STR_METHOD_NAME);
        }
    }

    /**
     * getPTSOrderBizDate
     */
    public void testGetPTSOrderBizDate_case0007()
    {
        final String STR_METHOD_NAME = " testGetPTSBizDateTypename_case0007()";
        log.entering(TEST_START + STR_METHOD_NAME);

        try
        {
            Date l_datDate =
                WEB3DateUtility.getDate("20040718110000", "yyyyMMddHHmmss");
            Timestamp l_tsDate = new Timestamp(l_datDate.getTime());

            ThreadLocalSystemAttributesRegistry.setAttribute(
                "xblocks.gtl.attributes.systemtimestamp",
                l_tsDate);
            WEB3GentradeTradingClendarContext l_context =
                new WEB3GentradeTradingClendarContext();
            l_context.setInstitutionCode("0D");
            l_context.setBranchCode("381");
            l_context.setTradingTimeType("26");
            l_context.setMarketCode("SP");
            l_context.setProductCode("0");
            ThreadLocalSystemAttributesRegistry.setAttribute(
                "web3.tradingcalendarcontext", l_context);
            ThreadLocalSystemAttributesRegistry.setAttribute(
                "web3.attributes.basetimestampfororderbizdate",
                l_tsDate);

            TradingTimeParams l_tradingTimeParams =
                TestDBUtility.getTradingTimeRow();
            l_tradingTimeParams.setInstitutionCode("0D");
            l_tradingTimeParams.setBranchCode("381");
            l_tradingTimeParams.setMarketCode("SP");
            l_tradingTimeParams.setTradingTimeType("26");
            l_tradingTimeParams.setProductCode("0");
            l_tradingTimeParams.setBizDateType("3");
            l_tradingTimeParams.setSessionType("1");
            TestDBUtility.insertWithDel(l_tradingTimeParams);

            Date l_datPTSOrderBizDate =
                WEB3GentradeTradingTimeManagement.getPTSOrderBizDate();
            assertEquals(null, l_datPTSOrderBizDate);
        }
        catch (WEB3BaseException l_ex)
        {
            log.error(STR_METHOD_NAME, l_ex);
            assertEquals(WEB3ErrorCatalog.SYSTEM_ERROR_80006, l_ex.getErrorInfo());
        }
        catch (Exception l_ex)
        {
            log.error(STR_METHOD_NAME, l_ex);
            fail();
        }
        finally
        {
            log.exiting(STR_METHOD_NAME);
        }
    }

    /**
     * getPTSOrderBizDate
     */
    public void testGetPTSOrderBizDate_case0008()
    {
        final String STR_METHOD_NAME = " testGetPTSBizDateTypename_case0008()";
        log.entering(TEST_START + STR_METHOD_NAME);

        try
        {
            Date l_datDate =
                WEB3DateUtility.getDate("20040720110000", "yyyyMMddHHmmss");
            Timestamp l_tsDate = new Timestamp(l_datDate.getTime());

            ThreadLocalSystemAttributesRegistry.setAttribute(
                "xblocks.gtl.attributes.systemtimestamp",
                l_tsDate);
            WEB3GentradeTradingClendarContext l_context =
                new WEB3GentradeTradingClendarContext();
            l_context.setInstitutionCode("0D");
            l_context.setBranchCode("381");
            l_context.setTradingTimeType("26");
            l_context.setMarketCode("SP");
            l_context.setProductCode("0");
            ThreadLocalSystemAttributesRegistry.setAttribute(
                "web3.tradingcalendarcontext", l_context);
            ThreadLocalSystemAttributesRegistry.setAttribute(
                "web3.attributes.basetimestampfororderbizdate",
                l_tsDate);

            TradingTimeParams l_tradingTimeParams =
                TestDBUtility.getTradingTimeRow();
            l_tradingTimeParams.setInstitutionCode("0D");
            l_tradingTimeParams.setBranchCode("381");
            l_tradingTimeParams.setMarketCode("SP");
            l_tradingTimeParams.setTradingTimeType("26");
            l_tradingTimeParams.setProductCode("0");
            l_tradingTimeParams.setBizDateType("1");
            l_tradingTimeParams.setSessionType("1");
            l_tradingTimeParams.setStartTime("093000");
            l_tradingTimeParams.setEndTime("100000");
            TestDBUtility.insertWithDel(l_tradingTimeParams);

            WEB3GentradeTradingTimeManagement.getPTSOrderBizDate();

            fail();
        }
        catch (WEB3BaseException l_ex)
        {
            log.error(STR_METHOD_NAME, l_ex);
            assertEquals(WEB3ErrorCatalog.SYSTEM_ERROR_80006, l_ex.getErrorInfo());
        }
        catch (Exception l_ex)
        {
            log.error(STR_METHOD_NAME, l_ex);
            fail();
        }
        finally
        {
            log.exiting(STR_METHOD_NAME);
        }
    }

    /**
     * getPTSOrderBizDate
     */
    public void testGetPTSOrderBizDate_case0009()
    {
        final String STR_METHOD_NAME = " testGetPTSBizDateTypename_case0009()";
        log.entering(TEST_START + STR_METHOD_NAME);

        try
        {
            Date l_datDate =
                WEB3DateUtility.getDate("20040720110000", "yyyyMMddHHmmss");
            Timestamp l_tsDate = new Timestamp(l_datDate.getTime());

            ThreadLocalSystemAttributesRegistry.setAttribute(
                "xblocks.gtl.attributes.systemtimestamp",
                l_tsDate);
            WEB3GentradeTradingClendarContext l_context =
                new WEB3GentradeTradingClendarContext();
            l_context.setInstitutionCode("0D");
            l_context.setBranchCode("381");
            l_context.setTradingTimeType("26");
            l_context.setMarketCode("SP");
            l_context.setProductCode("0");
            ThreadLocalSystemAttributesRegistry.setAttribute(
                "web3.tradingcalendarcontext", l_context);
            ThreadLocalSystemAttributesRegistry.setAttribute(
                "web3.attributes.basetimestampfororderbizdate",
                l_tsDate);

            TradingTimeParams l_tradingTimeParams =
                TestDBUtility.getTradingTimeRow();
            l_tradingTimeParams.setInstitutionCode("0D");
            l_tradingTimeParams.setBranchCode("381");
            l_tradingTimeParams.setMarketCode("SP");
            l_tradingTimeParams.setTradingTimeType("26");
            l_tradingTimeParams.setProductCode("0");
            l_tradingTimeParams.setBizDateType("1");
            l_tradingTimeParams.setSessionType("1");
            l_tradingTimeParams.setStartTime("113000");
            l_tradingTimeParams.setEndTime("120000");
            TestDBUtility.insertWithDel(l_tradingTimeParams);

            WEB3GentradeTradingTimeManagement.getPTSOrderBizDate();

            fail();
        }
        catch (WEB3BaseException l_ex)
        {
            log.error(STR_METHOD_NAME, l_ex);
            assertEquals(WEB3ErrorCatalog.SYSTEM_ERROR_80006, l_ex.getErrorInfo());
        }
        catch (Exception l_ex)
        {
            log.error(STR_METHOD_NAME, l_ex);
            fail();
        }
        finally
        {
            log.exiting(STR_METHOD_NAME);
        }
    }

    /**
     * getPTSOrderBizDate
     */
    public void testGetPTSOrderBizDate_case0010()
    {
        final String STR_METHOD_NAME = " testGetPTSBizDateTypename_case0010()";
        log.entering(TEST_START + STR_METHOD_NAME);

        try
        {
            Date l_datDate =
                WEB3DateUtility.getDate("20040720110000", "yyyyMMddHHmmss");
            Timestamp l_tsDate = new Timestamp(l_datDate.getTime());

            ThreadLocalSystemAttributesRegistry.setAttribute(
                "xblocks.gtl.attributes.systemtimestamp",
                l_tsDate);
            WEB3GentradeTradingClendarContext l_context =
                new WEB3GentradeTradingClendarContext();
            l_context.setInstitutionCode("0D");
            l_context.setBranchCode("381");
            l_context.setTradingTimeType("26");
            l_context.setMarketCode("SP");
            l_context.setProductCode("0");
            ThreadLocalSystemAttributesRegistry.setAttribute(
                "web3.tradingcalendarcontext", l_context);
            ThreadLocalSystemAttributesRegistry.setAttribute(
                "web3.attributes.basetimestampfororderbizdate",
                l_tsDate);

            TradingTimeParams l_tradingTimeParams =
                TestDBUtility.getTradingTimeRow();
            l_tradingTimeParams.setInstitutionCode("0D");
            l_tradingTimeParams.setBranchCode("381");
            l_tradingTimeParams.setMarketCode("SP");
            l_tradingTimeParams.setTradingTimeType("26");
            l_tradingTimeParams.setProductCode("0");
            l_tradingTimeParams.setBizDateType("1");
            l_tradingTimeParams.setSessionType("1");
            l_tradingTimeParams.setStartTime("100000");
            l_tradingTimeParams.setEndTime("093000");
            TestDBUtility.insertWithDel(l_tradingTimeParams);

            WEB3GentradeTradingTimeManagement.getPTSOrderBizDate();

            fail();
        }
        catch (WEB3BaseException l_ex)
        {
            log.error(STR_METHOD_NAME, l_ex);
            assertEquals(WEB3ErrorCatalog.SYSTEM_ERROR_80006, l_ex.getErrorInfo());
        }
        catch (Exception l_ex)
        {
            log.error(STR_METHOD_NAME, l_ex);
            fail();
        }
        finally
        {
            log.exiting(STR_METHOD_NAME);
        }
    }

    /**
     * getPTSOrderBizDate
     */
    public void testGetPTSOrderBizDate_case0011()
    {
        final String STR_METHOD_NAME = " testGetPTSBizDateTypename_case0011()";
        log.entering(TEST_START + STR_METHOD_NAME);

        try
        {
            Date l_datDate =
                WEB3DateUtility.getDate("20040720110000", "yyyyMMddHHmmss");
            Timestamp l_tsDate = new Timestamp(l_datDate.getTime());

            ThreadLocalSystemAttributesRegistry.setAttribute(
                "xblocks.gtl.attributes.systemtimestamp",
                l_tsDate);
            WEB3GentradeTradingClendarContext l_context =
                new WEB3GentradeTradingClendarContext();
            l_context.setInstitutionCode("0D");
            l_context.setBranchCode("381");
            l_context.setTradingTimeType("26");
            l_context.setMarketCode("SP");
            l_context.setProductCode("0");
            ThreadLocalSystemAttributesRegistry.setAttribute(
                "web3.tradingcalendarcontext", l_context);
            ThreadLocalSystemAttributesRegistry.setAttribute(
                "web3.attributes.basetimestampfororderbizdate",
                l_tsDate);

            TradingTimeParams l_tradingTimeParams =
                TestDBUtility.getTradingTimeRow();
            l_tradingTimeParams.setInstitutionCode("0D");
            l_tradingTimeParams.setBranchCode("381");
            l_tradingTimeParams.setMarketCode("SP");
            l_tradingTimeParams.setTradingTimeType("26");
            l_tradingTimeParams.setProductCode("0");
            l_tradingTimeParams.setBizDateType("1");
            l_tradingTimeParams.setSessionType("1");
            l_tradingTimeParams.setStartTime("093000");
            l_tradingTimeParams.setEndTime("110000");
            l_tradingTimeParams.setBizdateCalcParameter(null);
            TestDBUtility.insertWithDel(l_tradingTimeParams);

            Date l_datPTSOrderBizDate =
                WEB3GentradeTradingTimeManagement.getPTSOrderBizDate();

            assertNull(l_datPTSOrderBizDate);
        }
        catch (WEB3BaseException l_ex)
        {
            log.error(STR_METHOD_NAME, l_ex);
            fail();
        }
        catch (Exception l_ex)
        {
            log.error(STR_METHOD_NAME, l_ex);
            fail();
        }
        finally
        {
            log.exiting(STR_METHOD_NAME);
        }
    }

    /**
     * getPTSOrderBizDate
     */
    public void testGetPTSOrderBizDate_case0012()
    {
        final String STR_METHOD_NAME = " testGetPTSBizDateTypename_case0012()";
        log.entering(TEST_START + STR_METHOD_NAME);

        try
        {
            Date l_datDate =
                WEB3DateUtility.getDate("20040720110000", "yyyyMMddHHmmss");
            Timestamp l_tsDate = new Timestamp(l_datDate.getTime());

            ThreadLocalSystemAttributesRegistry.setAttribute(
                "xblocks.gtl.attributes.systemtimestamp",
                l_tsDate);
            WEB3GentradeTradingClendarContext l_context =
                new WEB3GentradeTradingClendarContext();
            l_context.setInstitutionCode("0D");
            l_context.setBranchCode("381");
            l_context.setTradingTimeType("26");
            l_context.setMarketCode("SP");
            l_context.setProductCode("0");
            ThreadLocalSystemAttributesRegistry.setAttribute(
                "web3.tradingcalendarcontext", l_context);
            ThreadLocalSystemAttributesRegistry.setAttribute(
                "web3.attributes.basetimestampfororderbizdate",
                l_tsDate);

            TradingTimeParams l_tradingTimeParams =
                TestDBUtility.getTradingTimeRow();
            l_tradingTimeParams.setInstitutionCode("0D");
            l_tradingTimeParams.setBranchCode("381");
            l_tradingTimeParams.setMarketCode("SP");
            l_tradingTimeParams.setTradingTimeType("26");
            l_tradingTimeParams.setProductCode("0");
            l_tradingTimeParams.setBizDateType("1");
            l_tradingTimeParams.setSessionType("1");
            l_tradingTimeParams.setStartTime("093000");
            l_tradingTimeParams.setEndTime("130000");
            l_tradingTimeParams.setBizdateCalcParameter(null);
            TestDBUtility.insertWithDel(l_tradingTimeParams);

            Date l_datPTSOrderBizDate =
                WEB3GentradeTradingTimeManagement.getPTSOrderBizDate();

            assertNull(l_datPTSOrderBizDate);
        }
        catch (WEB3BaseException l_ex)
        {
            log.error(STR_METHOD_NAME, l_ex);
            fail();
        }
        catch (Exception l_ex)
        {
            log.error(STR_METHOD_NAME, l_ex);
            fail();
        }
        finally
        {
            log.exiting(STR_METHOD_NAME);
        }
    }

    /**
     * getPTSOrderBizDate
     */
    public void testGetPTSOrderBizDate_case0013()
    {
        final String STR_METHOD_NAME = " testGetPTSBizDateTypename_case0013()";
        log.entering(TEST_START + STR_METHOD_NAME);

        try
        {
            Date l_datDate =
                WEB3DateUtility.getDate("20040720110000", "yyyyMMddHHmmss");
            Timestamp l_tsDate = new Timestamp(l_datDate.getTime());

            ThreadLocalSystemAttributesRegistry.setAttribute(
                "xblocks.gtl.attributes.systemtimestamp",
                l_tsDate);
            WEB3GentradeTradingClendarContext l_context =
                new WEB3GentradeTradingClendarContext();
            l_context.setInstitutionCode("0D");
            l_context.setBranchCode("381");
            l_context.setTradingTimeType("26");
            l_context.setMarketCode("SP");
            l_context.setProductCode("0");
            ThreadLocalSystemAttributesRegistry.setAttribute(
                "web3.tradingcalendarcontext", l_context);
            ThreadLocalSystemAttributesRegistry.setAttribute(
                "web3.attributes.basetimestampfororderbizdate",
                l_tsDate);

            TradingTimeParams l_tradingTimeParams =
                TestDBUtility.getTradingTimeRow();
            l_tradingTimeParams.setInstitutionCode("0D");
            l_tradingTimeParams.setBranchCode("381");
            l_tradingTimeParams.setMarketCode("SP");
            l_tradingTimeParams.setTradingTimeType("26");
            l_tradingTimeParams.setProductCode("0");
            l_tradingTimeParams.setBizDateType("1");
            l_tradingTimeParams.setSessionType("1");
            l_tradingTimeParams.setStartTime("110000");
            l_tradingTimeParams.setEndTime("130000");
            l_tradingTimeParams.setBizdateCalcParameter(null);
            TestDBUtility.insertWithDel(l_tradingTimeParams);

            Date l_datPTSOrderBizDate =
                WEB3GentradeTradingTimeManagement.getPTSOrderBizDate();

            assertNull(l_datPTSOrderBizDate);
        }
        catch (WEB3BaseException l_ex)
        {
            log.error(STR_METHOD_NAME, l_ex);
            fail();
        }
        catch (Exception l_ex)
        {
            log.error(STR_METHOD_NAME, l_ex);
            fail();
        }
        finally
        {
            log.exiting(STR_METHOD_NAME);
        }
    }

    /**
     * getPTSOrderBizDate
     */
    public void testGetPTSOrderBizDate_case0014()
    {
        final String STR_METHOD_NAME = " testGetPTSBizDateTypename_case0014()";
        log.entering(TEST_START + STR_METHOD_NAME);

        try
        {
            Date l_datDate =
                WEB3DateUtility.getDate("20040720110000", "yyyyMMddHHmmss");
            Timestamp l_tsDate = new Timestamp(l_datDate.getTime());

            ThreadLocalSystemAttributesRegistry.setAttribute(
                "xblocks.gtl.attributes.systemtimestamp",
                l_tsDate);
            WEB3GentradeTradingClendarContext l_context =
                new WEB3GentradeTradingClendarContext();
            l_context.setInstitutionCode("0D");
            l_context.setBranchCode("381");
            l_context.setTradingTimeType("26");
            l_context.setMarketCode("SP");
            l_context.setProductCode("0");
            ThreadLocalSystemAttributesRegistry.setAttribute(
                "web3.tradingcalendarcontext", l_context);
            ThreadLocalSystemAttributesRegistry.setAttribute(
                "web3.attributes.basetimestampfororderbizdate",
                l_tsDate);

            TradingTimeParams l_tradingTimeParams =
                TestDBUtility.getTradingTimeRow();
            l_tradingTimeParams.setInstitutionCode("0D");
            l_tradingTimeParams.setBranchCode("381");
            l_tradingTimeParams.setMarketCode("SP");
            l_tradingTimeParams.setTradingTimeType("26");
            l_tradingTimeParams.setProductCode("0");
            l_tradingTimeParams.setBizDateType("1");
            l_tradingTimeParams.setSessionType("1");
            l_tradingTimeParams.setStartTime("110000");
            l_tradingTimeParams.setEndTime("110000");
            l_tradingTimeParams.setBizdateCalcParameter(null);
            TestDBUtility.insertWithDel(l_tradingTimeParams);

            Date l_datPTSOrderBizDate =
                WEB3GentradeTradingTimeManagement.getPTSOrderBizDate();

            assertNull(l_datPTSOrderBizDate);
        }
        catch (WEB3BaseException l_ex)
        {
            log.error(STR_METHOD_NAME, l_ex);
            fail();
        }
        catch (Exception l_ex)
        {
            log.error(STR_METHOD_NAME, l_ex);
            fail();
        }
        finally
        {
            log.exiting(STR_METHOD_NAME);
        }
    }

    /**
     * getPTSOrderBizDate
     */
    public void testGetPTSOrderBizDate_case0015()
    {
        final String STR_METHOD_NAME = " testGetPTSBizDateTypename_case0015()";
        log.entering(TEST_START + STR_METHOD_NAME);

        try
        {
            Date l_datDate =
                WEB3DateUtility.getDate("20040720110000", "yyyyMMddHHmmss");
            Timestamp l_tsDate = new Timestamp(l_datDate.getTime());

            ThreadLocalSystemAttributesRegistry.setAttribute(
                "xblocks.gtl.attributes.systemtimestamp",
                l_tsDate);
            WEB3GentradeTradingClendarContext l_context =
                new WEB3GentradeTradingClendarContext();
            l_context.setInstitutionCode("0D");
            l_context.setBranchCode("381");
            l_context.setTradingTimeType("26");
            l_context.setMarketCode("SP");
            l_context.setProductCode("0");
            ThreadLocalSystemAttributesRegistry.setAttribute(
                "web3.tradingcalendarcontext", l_context);
            ThreadLocalSystemAttributesRegistry.setAttribute(
                "web3.attributes.basetimestampfororderbizdate",
                l_tsDate);

            TradingTimeParams l_tradingTimeParams =
                TestDBUtility.getTradingTimeRow();
            l_tradingTimeParams.setInstitutionCode("0D");
            l_tradingTimeParams.setBranchCode("381");
            l_tradingTimeParams.setMarketCode("SP");
            l_tradingTimeParams.setTradingTimeType("26");
            l_tradingTimeParams.setProductCode("0");
            l_tradingTimeParams.setBizDateType("1");
            l_tradingTimeParams.setSessionType("1");
            l_tradingTimeParams.setStartTime("110000");
            l_tradingTimeParams.setEndTime("110000");
            l_tradingTimeParams.setBizdateCalcParameter("3");
            TestDBUtility.insertWithDel(l_tradingTimeParams);

            Date l_datPTSOrderBizDate =
                WEB3GentradeTradingTimeManagement.getPTSOrderBizDate();

            assertEquals(
                WEB3DateUtility.getDate("20040719", "yyyyMMdd"),
                l_datPTSOrderBizDate);
        }
        catch (WEB3BaseException l_ex)
        {
            log.error(STR_METHOD_NAME, l_ex);
            fail();
        }
        catch (Exception l_ex)
        {
            log.error(STR_METHOD_NAME, l_ex);
            fail();
        }
        finally
        {
            log.exiting(STR_METHOD_NAME);
        }
    }

    /**
     * getPTSOrderBizDate
     */
    public void testGetPTSOrderBizDate_case0016()
    {
        final String STR_METHOD_NAME = " testGetPTSBizDateTypename_case0016()";
        log.entering(TEST_START + STR_METHOD_NAME);

        try
        {
            Date l_datDate =
                WEB3DateUtility.getDate("20040720110000", "yyyyMMddHHmmss");
            Timestamp l_tsDate = new Timestamp(l_datDate.getTime());

            ThreadLocalSystemAttributesRegistry.setAttribute(
                "xblocks.gtl.attributes.systemtimestamp",
                l_tsDate);
            WEB3GentradeTradingClendarContext l_context =
                new WEB3GentradeTradingClendarContext();
            l_context.setInstitutionCode("0D");
            l_context.setBranchCode("381");
            l_context.setTradingTimeType("26");
            l_context.setMarketCode("SP");
            l_context.setProductCode("0");
            ThreadLocalSystemAttributesRegistry.setAttribute(
                "web3.tradingcalendarcontext", l_context);
            ThreadLocalSystemAttributesRegistry.setAttribute(
                "web3.attributes.basetimestampfororderbizdate",
                l_tsDate);

            TradingTimeParams l_tradingTimeParams =
                TestDBUtility.getTradingTimeRow();
            l_tradingTimeParams.setInstitutionCode("0D");
            l_tradingTimeParams.setBranchCode("381");
            l_tradingTimeParams.setMarketCode("SP");
            l_tradingTimeParams.setTradingTimeType("26");
            l_tradingTimeParams.setProductCode("0");
            l_tradingTimeParams.setBizDateType("1");
            l_tradingTimeParams.setSessionType("1");
            l_tradingTimeParams.setStartTime("110000");
            l_tradingTimeParams.setEndTime("110000");
            l_tradingTimeParams.setBizdateCalcParameter("0");
            TestDBUtility.insertWithDel(l_tradingTimeParams);

            Date l_datPTSOrderBizDate =
                WEB3GentradeTradingTimeManagement.getPTSOrderBizDate();

            assertEquals(
                WEB3DateUtility.getDate("20040720", "yyyyMMdd"),
                l_datPTSOrderBizDate);
        }
        catch (WEB3BaseException l_ex)
        {
            log.error(STR_METHOD_NAME, l_ex);
            fail();
        }
        catch (Exception l_ex)
        {
            log.error(STR_METHOD_NAME, l_ex);
            fail();
        }
        finally
        {
            log.exiting(STR_METHOD_NAME);
        }
    }

    /**
     * getPTSOrderBizDate
     */
    public void testGetPTSOrderBizDate_case0017()
    {
        final String STR_METHOD_NAME = " testGetPTSBizDateTypename_case0017()";
        log.entering(TEST_START + STR_METHOD_NAME);

        try
        {
            Date l_datDate =
                WEB3DateUtility.getDate("20040720110000", "yyyyMMddHHmmss");
            Timestamp l_tsDate = new Timestamp(l_datDate.getTime());

            ThreadLocalSystemAttributesRegistry.setAttribute(
                "xblocks.gtl.attributes.systemtimestamp",
                l_tsDate);
            WEB3GentradeTradingClendarContext l_context =
                new WEB3GentradeTradingClendarContext();
            l_context.setInstitutionCode("0D");
            l_context.setBranchCode("381");
            l_context.setTradingTimeType("26");
            l_context.setMarketCode("SP");
            l_context.setProductCode("0");
            ThreadLocalSystemAttributesRegistry.setAttribute(
                "web3.tradingcalendarcontext", l_context);
            ThreadLocalSystemAttributesRegistry.setAttribute(
                "web3.attributes.basetimestampfororderbizdate",
                l_tsDate);

            TradingTimeParams l_tradingTimeParams =
                TestDBUtility.getTradingTimeRow();
            l_tradingTimeParams.setInstitutionCode("0D");
            l_tradingTimeParams.setBranchCode("381");
            l_tradingTimeParams.setMarketCode("SP");
            l_tradingTimeParams.setTradingTimeType("26");
            l_tradingTimeParams.setProductCode("0");
            l_tradingTimeParams.setBizDateType("1");
            l_tradingTimeParams.setSessionType("1");
            l_tradingTimeParams.setStartTime("110000");
            l_tradingTimeParams.setEndTime("110000");
            l_tradingTimeParams.setBizdateCalcParameter("1");
            TestDBUtility.insertWithDel(l_tradingTimeParams);

            Date l_datPTSOrderBizDate =
                WEB3GentradeTradingTimeManagement.getPTSOrderBizDate();

            assertEquals(
                WEB3DateUtility.getDate("20040721", "yyyyMMdd"),
                l_datPTSOrderBizDate);
        }
        catch (WEB3BaseException l_ex)
        {
            log.error(STR_METHOD_NAME, l_ex);
            fail();
        }
        catch (Exception l_ex)
        {
            log.error(STR_METHOD_NAME, l_ex);
            fail();
        }
        finally
        {
            log.exiting(STR_METHOD_NAME);
        }
    }

    /**
     * getPTSOrderBizDate
     */
    public void testGetPTSOrderBizDate_case0018()
    {
        final String STR_METHOD_NAME = " testGetPTSBizDateTypename_case0018()";
        log.entering(TEST_START + STR_METHOD_NAME);

        try
        {
            Date l_datDate =
                WEB3DateUtility.getDate("20040719110000", "yyyyMMddHHmmss");
            Timestamp l_tsDate = new Timestamp(l_datDate.getTime());

            ThreadLocalSystemAttributesRegistry.setAttribute(
                "xblocks.gtl.attributes.systemtimestamp",
                l_tsDate);
            WEB3GentradeTradingClendarContext l_context =
                new WEB3GentradeTradingClendarContext();
            l_context.setInstitutionCode("0D");
            l_context.setBranchCode("381");
            l_context.setTradingTimeType("26");
            l_context.setMarketCode("SP");
            l_context.setProductCode("0");
            ThreadLocalSystemAttributesRegistry.setAttribute(
                "web3.tradingcalendarcontext", l_context);
            ThreadLocalSystemAttributesRegistry.setAttribute(
                "web3.attributes.basetimestampfororderbizdate",
                l_tsDate);

            TradingTimeParams l_tradingTimeParams =
                TestDBUtility.getTradingTimeRow();
            l_tradingTimeParams.setInstitutionCode("0D");
            l_tradingTimeParams.setBranchCode("381");
            l_tradingTimeParams.setMarketCode("SP");
            l_tradingTimeParams.setTradingTimeType("26");
            l_tradingTimeParams.setProductCode("0");
            l_tradingTimeParams.setBizDateType("5");
            l_tradingTimeParams.setSessionType("1");
            l_tradingTimeParams.setStartTime("110000");
            l_tradingTimeParams.setEndTime("110000");
            l_tradingTimeParams.setBizdateCalcParameter("3");
            TestDBUtility.insertWithDel(l_tradingTimeParams);

            TestDBUtility.deleteAll(CalendarParams.TYPE);

            Date l_datPTSOrderBizDate =
                WEB3GentradeTradingTimeManagement.getPTSOrderBizDate();

            assertEquals(
                WEB3DateUtility.getDate("20040717", "yyyyMMdd"),
                l_datPTSOrderBizDate);
        }
        catch (WEB3BaseException l_ex)
        {
            log.error(STR_METHOD_NAME, l_ex);
            fail();
        }
        catch (Exception l_ex)
        {
            log.error(STR_METHOD_NAME, l_ex);
            fail();
        }
        finally
        {
            log.exiting(STR_METHOD_NAME);
        }
    }

    /**
     * getPTSOrderBizDate
     */
    public void testGetPTSOrderBizDate_case0019()
    {
        final String STR_METHOD_NAME = " testGetPTSBizDateTypename_case0019()";
        log.entering(TEST_START + STR_METHOD_NAME);

        try
        {
            Date l_datDate =
                WEB3DateUtility.getDate("20040716110000", "yyyyMMddHHmmss");
            Timestamp l_tsDate = new Timestamp(l_datDate.getTime());

            ThreadLocalSystemAttributesRegistry.setAttribute(
                "xblocks.gtl.attributes.systemtimestamp",
                l_tsDate);
            WEB3GentradeTradingClendarContext l_context =
                new WEB3GentradeTradingClendarContext();
            l_context.setInstitutionCode("0D");
            l_context.setBranchCode("381");
            l_context.setTradingTimeType("26");
            l_context.setMarketCode("SP");
            l_context.setProductCode("0");
            ThreadLocalSystemAttributesRegistry.setAttribute(
                "web3.tradingcalendarcontext", l_context);
            ThreadLocalSystemAttributesRegistry.setAttribute(
                "web3.attributes.basetimestampfororderbizdate",
                l_tsDate);

            TradingTimeParams l_tradingTimeParams =
                TestDBUtility.getTradingTimeRow();
            l_tradingTimeParams.setInstitutionCode("0D");
            l_tradingTimeParams.setBranchCode("381");
            l_tradingTimeParams.setMarketCode("SP");
            l_tradingTimeParams.setTradingTimeType("26");
            l_tradingTimeParams.setProductCode("0");
            l_tradingTimeParams.setBizDateType("1");
            l_tradingTimeParams.setSessionType("1");
            l_tradingTimeParams.setStartTime("110000");
            l_tradingTimeParams.setEndTime("110000");
            l_tradingTimeParams.setBizdateCalcParameter("1");
            TestDBUtility.insertWithDel(l_tradingTimeParams);

            Date l_datPTSOrderBizDate =
                WEB3GentradeTradingTimeManagement.getPTSOrderBizDate();

            assertEquals(
                WEB3DateUtility.getDate("20040717", "yyyyMMdd"),
                l_datPTSOrderBizDate);
        }
        catch (WEB3BaseException l_ex)
        {
            log.error(STR_METHOD_NAME, l_ex);
            fail();
        }
        catch (Exception l_ex)
        {
            log.error(STR_METHOD_NAME, l_ex);
            fail();
        }
        finally
        {
            log.exiting(STR_METHOD_NAME);
        }
    }

    /**
     * getPTSOrderBizDate
     */
    public void testGetPTSOrderBizDate_case0020()
    {
        final String STR_METHOD_NAME = " testGetPTSBizDateTypename_case0020()";
        log.entering(TEST_START + STR_METHOD_NAME);

        try
        {
            Date l_datDate =
                WEB3DateUtility.getDate("20040716110000", "yyyyMMddHHmmss");
            Timestamp l_tsDate = new Timestamp(l_datDate.getTime());

            ThreadLocalSystemAttributesRegistry.setAttribute(
                "xblocks.gtl.attributes.systemtimestamp",
                l_tsDate);
            WEB3GentradeTradingClendarContext l_context =
                new WEB3GentradeTradingClendarContext();
            l_context.setInstitutionCode("0D");
            l_context.setBranchCode("381");
            l_context.setTradingTimeType("26");
            l_context.setMarketCode("SP");
            l_context.setProductCode("0");
            ThreadLocalSystemAttributesRegistry.setAttribute(
                "web3.tradingcalendarcontext", l_context);
            ThreadLocalSystemAttributesRegistry.setAttribute(
                "web3.attributes.basetimestampfororderbizdate",
                l_tsDate);

            TradingTimeParams l_tradingTimeParams =
                TestDBUtility.getTradingTimeRow();
            l_tradingTimeParams.setInstitutionCode("0A");
            l_tradingTimeParams.setBranchCode("281");
            l_tradingTimeParams.setMarketCode("SP");
            l_tradingTimeParams.setTradingTimeType("26");
            l_tradingTimeParams.setProductCode("0");
            l_tradingTimeParams.setBizDateType("1");
            l_tradingTimeParams.setSessionType("1");
            l_tradingTimeParams.setStartTime("110000");
            l_tradingTimeParams.setEndTime("110000");
            l_tradingTimeParams.setBizdateCalcParameter("1");
            TestDBUtility.insertWithDel(l_tradingTimeParams);

            l_tradingTimeParams =
                TestDBUtility.getTradingTimeRow();
            l_tradingTimeParams.setInstitutionCode("0D");
            l_tradingTimeParams.setBranchCode("381");
            l_tradingTimeParams.setMarketCode("SP");
            l_tradingTimeParams.setTradingTimeType("26");
            l_tradingTimeParams.setProductCode("0");
            l_tradingTimeParams.setBizDateType("1");
            l_tradingTimeParams.setSessionType("1");
            l_tradingTimeParams.setStartTime("110000");
            l_tradingTimeParams.setEndTime("110000");
            TestDBUtility.insertWithDel(l_tradingTimeParams);

            Date l_datPTSOrderBizDate =
                WEB3GentradeTradingTimeManagement.getPTSOrderBizDate();

            assertEquals(
                WEB3DateUtility.getDate("20040717", "yyyyMMdd"),
                l_datPTSOrderBizDate);
        }
        catch (WEB3BaseException l_ex)
        {
            log.error(STR_METHOD_NAME, l_ex);
            fail();
        }
        catch (Exception l_ex)
        {
            log.error(STR_METHOD_NAME, l_ex);
            fail();
        }
        finally
        {
            log.exiting(STR_METHOD_NAME);
        }
    }

    /**
     * getPTSBizDateType
     */
    public void testGetPTSBizDateType_case0001()
    {
        final String STR_METHOD_NAME = " testGetPTSBizDateTypename_case0001()";
        log.entering(TEST_START + STR_METHOD_NAME);

        try
        {
            Date l_datDate = WEB3DateUtility.getDate("20071216", "yyyyMMdd");
            Timestamp l_tsDate = new Timestamp(l_datDate.getTime());
            String l_strPTSBizDateType =
                WEB3GentradeTradingTimeManagement.getPTSBizDateType(l_tsDate);

            assertEquals("0", l_strPTSBizDateType);
        }
        catch (WEB3BaseException l_ex)
        {
            log.error(STR_METHOD_NAME, l_ex);
            fail();
        }
        catch (Exception l_ex)
        {
            log.error(STR_METHOD_NAME, l_ex);
            fail();
        }
        finally
        {
            log.exiting(STR_METHOD_NAME);
        }
    }

    /**
     * getPTSBizDateType
     */
    public void testGetPTSBizDateType_case0002()
    {
        final String STR_METHOD_NAME = " testGetPTSBizDateTypename_case0002()";
        log.entering(TEST_START + STR_METHOD_NAME);

        try
        {
            TestDBUtility.deleteAll(CalendarParams.TYPE);
            CalendarParams l_calendarParams = new CalendarParams();
            l_calendarParams.setHoliday(WEB3DateUtility.getDate("20071214", "yyyyMMdd"));
            l_calendarParams.setBizDateType("1");
            l_calendarParams.setCreatedTimestamp(WEB3DateUtility.getDate("20071210", "yyyyMMdd"));
            l_calendarParams.setLastUpdatedTimestamp(WEB3DateUtility.getDate("20071210", "yyyyMMdd"));
            TestDBUtility.insertWithDel(l_calendarParams);

            Date l_datDate = WEB3DateUtility.getDate("20071215", "yyyyMMdd");
            Timestamp l_tsDate = new Timestamp(l_datDate.getTime());
            String l_strPTSBizDateType =
                WEB3GentradeTradingTimeManagement.getPTSBizDateType(l_tsDate);

            assertEquals("4", l_strPTSBizDateType);
        }
        catch (WEB3BaseException l_ex)
        {
            log.error(STR_METHOD_NAME, l_ex);
            fail();
        }
        catch (Exception l_ex)
        {
            log.error(STR_METHOD_NAME, l_ex);
            fail();
        }
        finally
        {
            log.exiting(STR_METHOD_NAME);
        }
    }

    /**
     * getPTSBizDateType
     */
    public void testGetPTSBizDateType_case0003()
    {
        final String STR_METHOD_NAME = " testGetPTSBizDateTypename_case0003()";
        log.entering(TEST_START + STR_METHOD_NAME);

        try
        {
            TestDBUtility.deleteAll(CalendarParams.TYPE);
            CalendarParams l_calendarParams = new CalendarParams();
            l_calendarParams.setHoliday(WEB3DateUtility.getDate("20071214", "yyyyMMdd"));
            l_calendarParams.setBizDateType("0");
            l_calendarParams.setCreatedTimestamp(WEB3DateUtility.getDate("20071210", "yyyyMMdd"));
            l_calendarParams.setLastUpdatedTimestamp(WEB3DateUtility.getDate("20071210", "yyyyMMdd"));
            TestDBUtility.insertWithDel(l_calendarParams);

            Date l_datDate = WEB3DateUtility.getDate("20071215", "yyyyMMdd");
            Timestamp l_tsDate = new Timestamp(l_datDate.getTime());
            String l_strPTSBizDateType =
                WEB3GentradeTradingTimeManagement.getPTSBizDateType(l_tsDate);

            assertEquals("0", l_strPTSBizDateType);
        }
        catch (WEB3BaseException l_ex)
        {
            log.error(STR_METHOD_NAME, l_ex);
            fail();
        }
        catch (Exception l_ex)
        {
            log.error(STR_METHOD_NAME, l_ex);
            fail();
        }
        finally
        {
            log.exiting(STR_METHOD_NAME);
        }
    }

    /**
     * getPTSBizDateType
     */
    public void testGetPTSBizDateType_case0004()
    {
        final String STR_METHOD_NAME = " testGetPTSBizDateTypename_case0004()";
        log.entering(TEST_START + STR_METHOD_NAME);

        try
        {
            TestDBUtility.deleteAll(CalendarParams.TYPE);
            CalendarParams l_calendarParams = new CalendarParams();
            l_calendarParams.setHoliday(WEB3DateUtility.getDate("20071214", "yyyyMMdd"));
            l_calendarParams.setBizDateType("2");
            l_calendarParams.setCreatedTimestamp(WEB3DateUtility.getDate("20071210", "yyyyMMdd"));
            l_calendarParams.setLastUpdatedTimestamp(WEB3DateUtility.getDate("20071210", "yyyyMMdd"));
            TestDBUtility.insertWithDel(l_calendarParams);

            Date l_datDate = WEB3DateUtility.getDate("20071215", "yyyyMMdd");
            Timestamp l_tsDate = new Timestamp(l_datDate.getTime());
            String l_strPTSBizDateType =
                WEB3GentradeTradingTimeManagement.getPTSBizDateType(l_tsDate);

            assertEquals("0", l_strPTSBizDateType);
        }
        catch (WEB3BaseException l_ex)
        {
            log.error(STR_METHOD_NAME, l_ex);
            fail();
        }
        catch (Exception l_ex)
        {
            log.error(STR_METHOD_NAME, l_ex);
            fail();
        }
        finally
        {
            log.exiting(STR_METHOD_NAME);
        }
    }

    /**
     * getPTSBizDateType
     */
    public void testGetPTSBizDateType_case0005()
    {
        final String STR_METHOD_NAME = " testGetPTSBizDateTypename_case0005()";
        log.entering(TEST_START + STR_METHOD_NAME);

        try
        {
            TestDBUtility.deleteAll(CalendarParams.TYPE);
            CalendarParams l_calendarParams = new CalendarParams();
            l_calendarParams.setHoliday(WEB3DateUtility.getDate("20071214", "yyyyMMdd"));
            l_calendarParams.setBizDateType("3");
            l_calendarParams.setCreatedTimestamp(WEB3DateUtility.getDate("20071210", "yyyyMMdd"));
            l_calendarParams.setLastUpdatedTimestamp(WEB3DateUtility.getDate("20071210", "yyyyMMdd"));
            TestDBUtility.insertWithDel(l_calendarParams);

            Date l_datDate = WEB3DateUtility.getDate("20071215", "yyyyMMdd");
            Timestamp l_tsDate = new Timestamp(l_datDate.getTime());
            String l_strPTSBizDateType =
                WEB3GentradeTradingTimeManagement.getPTSBizDateType(l_tsDate);

            assertEquals("0", l_strPTSBizDateType);
        }
        catch (WEB3BaseException l_ex)
        {
            log.error(STR_METHOD_NAME, l_ex);
            fail();
        }
        catch (Exception l_ex)
        {
            log.error(STR_METHOD_NAME, l_ex);
            fail();
        }
        finally
        {
            log.exiting(STR_METHOD_NAME);
        }
    }

    /**
     * getPTSBizDateType
     */
    public void testGetPTSBizDateType_case0006()
    {
        final String STR_METHOD_NAME = " testGetPTSBizDateTypename_case0006()";
        log.entering(TEST_START + STR_METHOD_NAME);

        try
        {
            TestDBUtility.deleteAll(CalendarParams.TYPE);
            CalendarParams l_calendarParams = new CalendarParams();
            l_calendarParams.setHoliday(WEB3DateUtility.getDate("20071219", "yyyyMMdd"));
            l_calendarParams.setBizDateType("0");
            l_calendarParams.setCreatedTimestamp(WEB3DateUtility.getDate("20071210", "yyyyMMdd"));
            l_calendarParams.setLastUpdatedTimestamp(WEB3DateUtility.getDate("20071210", "yyyyMMdd"));
            TestDBUtility.insertWithDel(l_calendarParams);

            Date l_datDate = WEB3DateUtility.getDate("20071219", "yyyyMMdd");
            Timestamp l_tsDate = new Timestamp(l_datDate.getTime());
            String l_strPTSBizDateType =
                WEB3GentradeTradingTimeManagement.getPTSBizDateType(l_tsDate);

            assertEquals("4", l_strPTSBizDateType);
        }
        catch (WEB3BaseException l_ex)
        {
            log.error(STR_METHOD_NAME, l_ex);
            fail();
        }
        catch (Exception l_ex)
        {
            log.error(STR_METHOD_NAME, l_ex);
            fail();
        }
        finally
        {
            log.exiting(STR_METHOD_NAME);
        }
    }

    /**
     * getPTSBizDateType
     */
    public void testGetPTSBizDateType_case0007()
    {
        final String STR_METHOD_NAME = " testGetPTSBizDateTypename_case0007()";
        log.entering(TEST_START + STR_METHOD_NAME);

        try
        {
            TestDBUtility.deleteAll(CalendarParams.TYPE);
            CalendarParams l_calendarParams = new CalendarParams();
            l_calendarParams.setHoliday(WEB3DateUtility.getDate("20071218", "yyyyMMdd"));
            l_calendarParams.setBizDateType("0");
            l_calendarParams.setCreatedTimestamp(WEB3DateUtility.getDate("20071210", "yyyyMMdd"));
            l_calendarParams.setLastUpdatedTimestamp(WEB3DateUtility.getDate("20071210", "yyyyMMdd"));
            TestDBUtility.insertWithDel(l_calendarParams);

            l_calendarParams = new CalendarParams();
            l_calendarParams.setHoliday(WEB3DateUtility.getDate("20071219", "yyyyMMdd"));
            l_calendarParams.setBizDateType("0");
            l_calendarParams.setCreatedTimestamp(WEB3DateUtility.getDate("20071210", "yyyyMMdd"));
            l_calendarParams.setLastUpdatedTimestamp(WEB3DateUtility.getDate("20071210", "yyyyMMdd"));
            TestDBUtility.insertWithDel(l_calendarParams);

            Date l_datDate = WEB3DateUtility.getDate("20071219", "yyyyMMdd");
            Timestamp l_tsDate = new Timestamp(l_datDate.getTime());
            String l_strPTSBizDateType =
                WEB3GentradeTradingTimeManagement.getPTSBizDateType(l_tsDate);

            assertEquals("0", l_strPTSBizDateType);
        }
        catch (WEB3BaseException l_ex)
        {
            log.error(STR_METHOD_NAME, l_ex);
            fail();
        }
        catch (Exception l_ex)
        {
            log.error(STR_METHOD_NAME, l_ex);
            fail();
        }
        finally
        {
            log.exiting(STR_METHOD_NAME);
        }
    }

    /**
     * getPTSBizDateType
     */
    public void testGetPTSBizDateType_case0008()
    {
        final String STR_METHOD_NAME = " testGetPTSBizDateTypename_case0008()";
        log.entering(TEST_START + STR_METHOD_NAME);

        try
        {
            TestDBUtility.deleteAll(CalendarParams.TYPE);
            CalendarParams l_calendarParams = new CalendarParams();
            l_calendarParams.setHoliday(WEB3DateUtility.getDate("20071218", "yyyyMMdd"));
            l_calendarParams.setBizDateType("2");
            l_calendarParams.setCreatedTimestamp(WEB3DateUtility.getDate("20071210", "yyyyMMdd"));
            l_calendarParams.setLastUpdatedTimestamp(WEB3DateUtility.getDate("20071210", "yyyyMMdd"));
            TestDBUtility.insertWithDel(l_calendarParams);

            l_calendarParams = new CalendarParams();
            l_calendarParams.setHoliday(WEB3DateUtility.getDate("20071219", "yyyyMMdd"));
            l_calendarParams.setBizDateType("0");
            l_calendarParams.setCreatedTimestamp(WEB3DateUtility.getDate("20071210", "yyyyMMdd"));
            l_calendarParams.setLastUpdatedTimestamp(WEB3DateUtility.getDate("20071210", "yyyyMMdd"));
            TestDBUtility.insertWithDel(l_calendarParams);

            Date l_datDate = WEB3DateUtility.getDate("20071219", "yyyyMMdd");
            Timestamp l_tsDate = new Timestamp(l_datDate.getTime());
            String l_strPTSBizDateType =
                WEB3GentradeTradingTimeManagement.getPTSBizDateType(l_tsDate);

            assertEquals("0", l_strPTSBizDateType);
        }
        catch (WEB3BaseException l_ex)
        {
            log.error(STR_METHOD_NAME, l_ex);
            fail();
        }
        catch (Exception l_ex)
        {
            log.error(STR_METHOD_NAME, l_ex);
            fail();
        }
        finally
        {
            log.exiting(STR_METHOD_NAME);
        }
    }

    /**
     * getPTSBizDateType
     */
    public void testGetPTSBizDateType_case0009()
    {
        final String STR_METHOD_NAME = " testGetPTSBizDateTypename_case0009()";
        log.entering(TEST_START + STR_METHOD_NAME);

        try
        {
            TestDBUtility.deleteAll(CalendarParams.TYPE);
            CalendarParams l_calendarParams = new CalendarParams();
            l_calendarParams.setHoliday(WEB3DateUtility.getDate("20071218", "yyyyMMdd"));
            l_calendarParams.setBizDateType("3");
            l_calendarParams.setCreatedTimestamp(WEB3DateUtility.getDate("20071210", "yyyyMMdd"));
            l_calendarParams.setLastUpdatedTimestamp(WEB3DateUtility.getDate("20071210", "yyyyMMdd"));
            TestDBUtility.insertWithDel(l_calendarParams);

            l_calendarParams = new CalendarParams();
            l_calendarParams.setHoliday(WEB3DateUtility.getDate("20071219", "yyyyMMdd"));
            l_calendarParams.setBizDateType("0");
            l_calendarParams.setCreatedTimestamp(WEB3DateUtility.getDate("20071210", "yyyyMMdd"));
            l_calendarParams.setLastUpdatedTimestamp(WEB3DateUtility.getDate("20071210", "yyyyMMdd"));
            TestDBUtility.insertWithDel(l_calendarParams);

            Date l_datDate = WEB3DateUtility.getDate("20071219", "yyyyMMdd");
            Timestamp l_tsDate = new Timestamp(l_datDate.getTime());
            String l_strPTSBizDateType =
                WEB3GentradeTradingTimeManagement.getPTSBizDateType(l_tsDate);

            assertEquals("0", l_strPTSBizDateType);
        }
        catch (WEB3BaseException l_ex)
        {
            log.error(STR_METHOD_NAME, l_ex);
            fail();
        }
        catch (Exception l_ex)
        {
            log.error(STR_METHOD_NAME, l_ex);
            fail();
        }
        finally
        {
            log.exiting(STR_METHOD_NAME);
        }
    }

    /**
     * getPTSBizDateType
     */
    public void testGetPTSBizDateType_case0010()
    {
        final String STR_METHOD_NAME = " testGetPTSBizDateTypename_case0010()";
        log.entering(TEST_START + STR_METHOD_NAME);

        try
        {
            TestDBUtility.deleteAll(CalendarParams.TYPE);
            CalendarParams l_calendarParams = new CalendarParams();
            l_calendarParams.setHoliday(WEB3DateUtility.getDate("20071216", "yyyyMMdd"));
            l_calendarParams.setBizDateType("1");
            l_calendarParams.setCreatedTimestamp(WEB3DateUtility.getDate("20071210", "yyyyMMdd"));
            l_calendarParams.setLastUpdatedTimestamp(WEB3DateUtility.getDate("20071210", "yyyyMMdd"));
            TestDBUtility.insertWithDel(l_calendarParams);

            Date l_datDate = WEB3DateUtility.getDate("20071217", "yyyyMMdd");
            Timestamp l_tsDate = new Timestamp(l_datDate.getTime());
            String l_strPTSBizDateType =
                WEB3GentradeTradingTimeManagement.getPTSBizDateType(l_tsDate);

            assertEquals("1", l_strPTSBizDateType);
        }
        catch (WEB3BaseException l_ex)
        {
            log.error(STR_METHOD_NAME, l_ex);
            fail();
        }
        catch (Exception l_ex)
        {
            log.error(STR_METHOD_NAME, l_ex);
            fail();
        }
        finally
        {
            log.exiting(STR_METHOD_NAME);
        }
    }

    /**
     * getPTSBizDateType
     */
    public void testGetPTSBizDateType_case0011()
    {
        final String STR_METHOD_NAME = " testGetPTSBizDateTypename_case0011()";
        log.entering(TEST_START + STR_METHOD_NAME);

        try
        {
            TestDBUtility.deleteAll(CalendarParams.TYPE);
            CalendarParams l_calendarParams = new CalendarParams();
            l_calendarParams.setHoliday(WEB3DateUtility.getDate("20071216", "yyyyMMdd"));
            l_calendarParams.setBizDateType("0");
            l_calendarParams.setCreatedTimestamp(WEB3DateUtility.getDate("20071210", "yyyyMMdd"));
            l_calendarParams.setLastUpdatedTimestamp(WEB3DateUtility.getDate("20071210", "yyyyMMdd"));
            TestDBUtility.insertWithDel(l_calendarParams);

            Date l_datDate = WEB3DateUtility.getDate("20071217", "yyyyMMdd");
            Timestamp l_tsDate = new Timestamp(l_datDate.getTime());
            String l_strPTSBizDateType =
                WEB3GentradeTradingTimeManagement.getPTSBizDateType(l_tsDate);

            assertEquals("5", l_strPTSBizDateType);
        }
        catch (WEB3BaseException l_ex)
        {
            log.error(STR_METHOD_NAME, l_ex);
            fail();
        }
        catch (Exception l_ex)
        {
            log.error(STR_METHOD_NAME, l_ex);
            fail();
        }
        finally
        {
            log.exiting(STR_METHOD_NAME);
        }
    }

    /**
     * getPTSBizDateType
     */
    public void testGetPTSBizDateType_case0012()
    {
        final String STR_METHOD_NAME = " testGetPTSBizDateTypename_case0012()";
        log.entering(TEST_START + STR_METHOD_NAME);

        try
        {
            TestDBUtility.deleteAll(CalendarParams.TYPE);
            CalendarParams l_calendarParams = new CalendarParams();
            l_calendarParams.setHoliday(WEB3DateUtility.getDate("20071216", "yyyyMMdd"));
            l_calendarParams.setBizDateType("2");
            l_calendarParams.setCreatedTimestamp(WEB3DateUtility.getDate("20071210", "yyyyMMdd"));
            l_calendarParams.setLastUpdatedTimestamp(WEB3DateUtility.getDate("20071210", "yyyyMMdd"));
            TestDBUtility.insertWithDel(l_calendarParams);

            Date l_datDate = WEB3DateUtility.getDate("20071217", "yyyyMMdd");
            Timestamp l_tsDate = new Timestamp(l_datDate.getTime());
            String l_strPTSBizDateType =
                WEB3GentradeTradingTimeManagement.getPTSBizDateType(l_tsDate);

            assertEquals("5", l_strPTSBizDateType);
        }
        catch (WEB3BaseException l_ex)
        {
            log.error(STR_METHOD_NAME, l_ex);
            fail();
        }
        catch (Exception l_ex)
        {
            log.error(STR_METHOD_NAME, l_ex);
            fail();
        }
        finally
        {
            log.exiting(STR_METHOD_NAME);
        }
    }

    /**
     * getPTSBizDateType
     */
    public void testGetPTSBizDateType_case0013()
    {
        final String STR_METHOD_NAME = " testGetPTSBizDateTypename_case0013()";
        log.entering(TEST_START + STR_METHOD_NAME);

        try
        {
            TestDBUtility.deleteAll(CalendarParams.TYPE);
            CalendarParams l_calendarParams = new CalendarParams();
            l_calendarParams.setHoliday(WEB3DateUtility.getDate("20071216", "yyyyMMdd"));
            l_calendarParams.setBizDateType("3");
            l_calendarParams.setCreatedTimestamp(WEB3DateUtility.getDate("20071210", "yyyyMMdd"));
            l_calendarParams.setLastUpdatedTimestamp(WEB3DateUtility.getDate("20071210", "yyyyMMdd"));
            TestDBUtility.insertWithDel(l_calendarParams);

            Date l_datDate = WEB3DateUtility.getDate("20071217", "yyyyMMdd");
            Timestamp l_tsDate = new Timestamp(l_datDate.getTime());
            String l_strPTSBizDateType =
                WEB3GentradeTradingTimeManagement.getPTSBizDateType(l_tsDate);

            assertEquals("5", l_strPTSBizDateType);
        }
        catch (WEB3BaseException l_ex)
        {
            log.error(STR_METHOD_NAME, l_ex);
            fail();
        }
        catch (Exception l_ex)
        {
            log.error(STR_METHOD_NAME, l_ex);
            fail();
        }
        finally
        {
            log.exiting(STR_METHOD_NAME);
        }
    }
    
  /*
   * testValidateOrderAcceptStatus_Case0001
   */
  public void testValidateOrderAcceptStatus_Case0001()
  {
      final String STR_METHOD_NAME = " testValidateOrderAcceptStatus_Case0001()";
      log.entering(TEST_START + STR_METHOD_NAME);

      TestBaseForMock.MOCK_MANAGER.setIsMockUsed(false);
      try
      {
          WEB3GentradeTradingClendarContext l_context = new WEB3GentradeTradingClendarContext();
          l_context.setInstitutionCode("0D");
          l_context.setBranchCode("381");
          l_context.setTradingTimeType("26");
          l_context.setOrderAcceptProduct("01");
          l_context.setOrderAcceptTransaction("01");
          ThreadLocalSystemAttributesRegistry.setAttribute("web3.tradingcalendarcontext", l_context);

          WEB3GentradeTradingTimeManagement.validateOrderAcceptStatus();
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
   * testValidateOrderAcceptStatus_Case0002
   */
  public void testValidateOrderAcceptStatus_Case0002()
  {
      final String STR_METHOD_NAME = " testValidateOrderAcceptStatus_Case0002()";
      log.entering(TEST_START + STR_METHOD_NAME);

      TestBaseForMock.MOCK_MANAGER.setIsMockUsed(false);
      try
      {
          WEB3GentradeTradingClendarContext l_context = new WEB3GentradeTradingClendarContext();
          l_context.setInstitutionCode("0D");
          l_context.setBranchCode("381");
          l_context.setTradingTimeType("26");
          l_context.setOrderAcceptProduct("01");
          l_context.setOrderAcceptTransaction("01");
          ThreadLocalSystemAttributesRegistry.setAttribute("web3.tradingcalendarcontext", l_context);

          OrderAcceptStatusParams l_orderAcceptStatusParams =
              TestDBUtility.getOrderAcceptStatusRow();
          l_orderAcceptStatusParams.setInstitutionCode("0D");
          l_orderAcceptStatusParams.setBranchCode("381");
          l_orderAcceptStatusParams.setOrderAccProduct("01");
          l_orderAcceptStatusParams.setOrderAccTransaction("01");
          l_orderAcceptStatusParams.setOrderAcceptStatus("0");
          TestDBUtility.insertWithDel(l_orderAcceptStatusParams);

          WEB3GentradeTradingTimeManagement.validateOrderAcceptStatus();
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
   * testValidateOrderAcceptStatus_Case0003
   */
  public void testValidateOrderAcceptStatus_Case0003()
  {
      final String STR_METHOD_NAME = " testValidateOrderAcceptStatus_Case0003()";
      log.entering(TEST_START + STR_METHOD_NAME);

      TestBaseForMock.MOCK_MANAGER.setIsMockUsed(false);
      try
      {
          WEB3GentradeTradingClendarContext l_context = new WEB3GentradeTradingClendarContext();
          l_context.setInstitutionCode("0D");
          l_context.setBranchCode("381");
          l_context.setTradingTimeType("26");
          l_context.setOrderAcceptProduct("01");
          l_context.setOrderAcceptTransaction("01");
          ThreadLocalSystemAttributesRegistry.setAttribute("web3.tradingcalendarcontext", l_context);

          OrderAcceptStatusParams l_orderAcceptStatusParams =
              TestDBUtility.getOrderAcceptStatusRow();
          l_orderAcceptStatusParams.setInstitutionCode("0D");
          l_orderAcceptStatusParams.setBranchCode("381");
          l_orderAcceptStatusParams.setOrderAccProduct("01");
          l_orderAcceptStatusParams.setOrderAccTransaction("01");
          l_orderAcceptStatusParams.setOrderAcceptStatus("1");
          TestDBUtility.insertWithDel(l_orderAcceptStatusParams);

          WEB3GentradeTradingTimeManagement.validateOrderAcceptStatus();
          fail();
      }
      catch (WEB3BusinessLayerException l_ble)
      {
          log.exiting(TEST_END + STR_METHOD_NAME);
          assertEquals(WEB3ErrorCatalog.BUSINESS_ERROR_00011, l_ble.getErrorInfo());
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
   * testValidateOrderAcceptStatus_Case0004
   */
  public void testValidateOrderAcceptStatus_Case0004()
  {
      final String STR_METHOD_NAME = " testValidateOrderAcceptStatus_Case0004()";
      log.entering(TEST_START + STR_METHOD_NAME);

      TestBaseForMock.MOCK_MANAGER.setIsMockUsed(false);
      try
      {
          WEB3GentradeTradingClendarContext l_context = new WEB3GentradeTradingClendarContext();
          l_context.setInstitutionCode("0D");
          l_context.setBranchCode("381");
          l_context.setTradingTimeType("26");
          l_context.setOrderAcceptProduct("01");
          l_context.setOrderAcceptTransaction("01");
          ThreadLocalSystemAttributesRegistry.setAttribute("web3.tradingcalendarcontext", l_context);

          OrderAcceptStatusParams l_orderAcceptStatusParams =
              TestDBUtility.getOrderAcceptStatusRow();
          l_orderAcceptStatusParams.setInstitutionCode("0D");
          l_orderAcceptStatusParams.setBranchCode("381");
          l_orderAcceptStatusParams.setOrderAccProduct("01");
          l_orderAcceptStatusParams.setOrderAccTransaction("01");
          l_orderAcceptStatusParams.setOrderAcceptStatus("2");
          TestDBUtility.insertWithDel(l_orderAcceptStatusParams);

          WEB3GentradeTradingTimeManagement.validateOrderAcceptStatus();
          fail();
      }
      catch (WEB3BusinessLayerException l_ble)
      {
          log.exiting(TEST_END + STR_METHOD_NAME);
          assertEquals(WEB3ErrorCatalog.BUSINESS_ERROR_00012, l_ble.getErrorInfo());
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
   * testValidateTriggerOrderAccept_Case0001
   */
  public void testValidateTriggerOrderAccept_Case0001()
  {
      final String STR_METHOD_NAME = " testValidateTriggerOrderAccept_Case0001()";
      log.entering(TEST_START + STR_METHOD_NAME);

      TestBaseForMock.MOCK_MANAGER.setIsMockUsed(false);
      try
      {
          //åªç›ì˙éû:20040718110000
          //isÉgÉäÉKî≠çs() == false
          ThreadLocalSystemAttributesRegistry.setAttribute(
              "xblocks.gtl.attributes.systemtimestamp",
          new Timestamp(WEB3DateUtility.getDate("20040718110000","yyyyMMddHHmmss").getTime()));
          WEB3GentradeTradingClendarContext l_context = new WEB3GentradeTradingClendarContext();
          l_context.setInstitutionCode("0D");
          l_context.setBranchCode("381");
          l_context.setTradingTimeType("26");
          l_context.setMarketCode("SP");
          l_context.setProductCode("0");
          ThreadLocalSystemAttributesRegistry.setAttribute("web3.tradingcalendarcontext", l_context);

          TradingTimeParams l_tradingTimeParams =
              TestDBUtility.getTradingTimeRow();
          l_tradingTimeParams.setInstitutionCode("0D");
          l_tradingTimeParams.setBranchCode("381");
          l_tradingTimeParams.setMarketCode("SP");
          l_tradingTimeParams.setTradingTimeType("26");
          l_tradingTimeParams.setProductCode("0");
          l_tradingTimeParams.setBizdateCalcParameter("0");
          l_tradingTimeParams.setSubmitMarketTrigger("1");
          l_tradingTimeParams.setBizDateType("0");
          //isó[èÍéûä‘ë—() == true
          l_tradingTimeParams.setSessionType("1");
          l_tradingTimeParams.setStartTime(100000 + "");
          //ç≈èIï¬ã«éûä‘:150000
          l_tradingTimeParams.setEndTime(150000 + "");
          TestDBUtility.insertWithDel(l_tradingTimeParams);

          BranchParams l_branchParams = new BranchParams(TestDBUtility.getBranchRow());
          TestDBUtility.insertWithDel(l_branchParams);

          BranchPreferencesParams l_branchPreferencesParams =
              new BranchPreferencesParams(TestDBUtility.getBranchPreferencesRow());
          l_branchPreferencesParams.setBranchId(33381);
          l_branchPreferencesParams.setName("evening.session.div");
          //isó[èÍé¿é{() == true
          l_branchPreferencesParams.setValue("1");
          l_branchPreferencesParams.setNameSerialNo(2);
          TestDBUtility.insertWithDel(l_branchPreferencesParams);

          InstitutionParams l_institutionParams =
              TestDBUtility.getInstitutionRow();
          l_institutionParams.setInstitutionCode("0D");
          TestDBUtility.insertWithDel(l_tradingTimeParams);

          OrderexecutionEndParams l_orderexecutionEndParams =
              new OrderexecutionEndParams(TestDBUtility.getOrderexecutionEndRow());
          l_orderexecutionEndParams.setInstitutionCode("0D");
          l_orderexecutionEndParams.setProductType(ProductTypeEnum.IFO);
          l_orderexecutionEndParams.setFutureOptionDiv("0");
          l_orderexecutionEndParams.setOrderexecutionEndType("1");
          //èàóùçœà»äO
          l_orderexecutionEndParams.setCarryoverEndType("0");
          TestDBUtility.insertWithDel(l_orderexecutionEndParams);

          WEB3GentradeTradingTimeManagement.validateTriggerOrderAccept(
              new WEB3GentradeInstitution(l_institutionParams),
              ProductTypeEnum.IFO,
              "0",
              "1");
          fail();
      }
      catch (WEB3BusinessLayerException l_ble)
      {
          log.exiting(TEST_END + STR_METHOD_NAME);
          assertEquals(WEB3ErrorCatalog.BUSINESS_ERROR_02240, l_ble.getErrorInfo());
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
   * testValidateTriggerOrderAccept_Case0002
   */
  public void testValidateTriggerOrderAccept_Case0002()
  {
      final String STR_METHOD_NAME = " testValidateTriggerOrderAccept_Case0002()";
      log.entering(TEST_START + STR_METHOD_NAME);

      TestBaseForMock.MOCK_MANAGER.setIsMockUsed(false);
      try
      {
          //åªç›ì˙éû:20040718110000
          //isÉgÉäÉKî≠çs() == false
          ThreadLocalSystemAttributesRegistry.setAttribute(
              "xblocks.gtl.attributes.systemtimestamp",
          new Timestamp(WEB3DateUtility.getDate("20040718160000","yyyyMMddHHmmss").getTime()));
          WEB3GentradeTradingClendarContext l_context = new WEB3GentradeTradingClendarContext();
          l_context.setInstitutionCode("0D");
          l_context.setBranchCode("381");
          l_context.setTradingTimeType("26");
          l_context.setMarketCode("SP");
          l_context.setProductCode("0");
          ThreadLocalSystemAttributesRegistry.setAttribute("web3.tradingcalendarcontext", l_context);

          TradingTimeParams l_tradingTimeParams =
              TestDBUtility.getTradingTimeRow();
          l_tradingTimeParams.setInstitutionCode("0D");
          l_tradingTimeParams.setBranchCode("381");
          l_tradingTimeParams.setMarketCode("SP");
          l_tradingTimeParams.setTradingTimeType("26");
          l_tradingTimeParams.setProductCode("0");
          l_tradingTimeParams.setBizdateCalcParameter("0");
          l_tradingTimeParams.setSubmitMarketTrigger("1");
          l_tradingTimeParams.setBizDateType("0");
          //isó[èÍéûä‘ë—() == true
          l_tradingTimeParams.setSessionType("1");
          l_tradingTimeParams.setStartTime(100000 + "");
          //ç≈èIï¬ã«éûä‘:150000
          l_tradingTimeParams.setEndTime(150000 + "");
          TestDBUtility.insertWithDel(l_tradingTimeParams);

          BranchParams l_branchParams = new BranchParams(TestDBUtility.getBranchRow());
          TestDBUtility.insertWithDel(l_branchParams);

          BranchPreferencesParams l_branchPreferencesParams =
              new BranchPreferencesParams(TestDBUtility.getBranchPreferencesRow());
          l_branchPreferencesParams.setBranchId(33381);
          l_branchPreferencesParams.setName("evening.session.div");
          //isó[èÍé¿é{() == false
          l_branchPreferencesParams.setValue("0");
          l_branchPreferencesParams.setNameSerialNo(2);
          TestDBUtility.insertWithDel(l_branchPreferencesParams);

          InstitutionParams l_institutionParams =
              TestDBUtility.getInstitutionRow();
          l_institutionParams.setInstitutionCode("0D");
          TestDBUtility.insertWithDel(l_tradingTimeParams);

          OrderexecutionEndParams l_orderexecutionEndParams =
              new OrderexecutionEndParams(TestDBUtility.getOrderexecutionEndRow());
          l_orderexecutionEndParams.setInstitutionCode("0D");
          l_orderexecutionEndParams.setProductType(ProductTypeEnum.IFO);
          l_orderexecutionEndParams.setFutureOptionDiv("0");
          l_orderexecutionEndParams.setOrderexecutionEndType("1");
          //èàóùçœà»äO
          l_orderexecutionEndParams.setCarryoverEndType("0");
          TestDBUtility.insertWithDel(l_orderexecutionEndParams);

          WEB3GentradeTradingTimeManagement.validateTriggerOrderAccept(
              new WEB3GentradeInstitution(l_institutionParams),
              ProductTypeEnum.IFO,
              "0",
              "1");
          fail();
      }
      catch (WEB3BusinessLayerException l_ble)
      {
          log.exiting(TEST_END + STR_METHOD_NAME);
          assertEquals(WEB3ErrorCatalog.BUSINESS_ERROR_02240, l_ble.getErrorInfo());
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
   * testValidateTriggerOrderAccept_Case0003
   */
  public void testValidateTriggerOrderAccept_Case0003()
  {
      final String STR_METHOD_NAME = " testValidateTriggerOrderAccept_Case0003()";
      log.entering(TEST_START + STR_METHOD_NAME);

      TestBaseForMock.MOCK_MANAGER.setIsMockUsed(false);
      try
      {
          //åªç›ì˙éû:20040718110000
          //isÉgÉäÉKî≠çs() == false
          ThreadLocalSystemAttributesRegistry.setAttribute(
              "xblocks.gtl.attributes.systemtimestamp",
          new Timestamp(WEB3DateUtility.getDate("20040718110000","yyyyMMddHHmmss").getTime()));
          WEB3GentradeTradingClendarContext l_context = new WEB3GentradeTradingClendarContext();
          l_context.setInstitutionCode("0D");
          l_context.setBranchCode("381");
          l_context.setTradingTimeType("26");
          l_context.setMarketCode("SP");
          l_context.setProductCode("0");
          ThreadLocalSystemAttributesRegistry.setAttribute("web3.tradingcalendarcontext", l_context);

          TradingTimeParams l_tradingTimeParams =
              TestDBUtility.getTradingTimeRow();
          l_tradingTimeParams.setInstitutionCode("0D");
          l_tradingTimeParams.setBranchCode("381");
          l_tradingTimeParams.setMarketCode("SP");
          l_tradingTimeParams.setTradingTimeType("26");
          l_tradingTimeParams.setProductCode("0");
          l_tradingTimeParams.setBizdateCalcParameter("0");
          l_tradingTimeParams.setSubmitMarketTrigger("1");
          l_tradingTimeParams.setBizDateType("0");
          //isó[èÍéûä‘ë—() == true
          l_tradingTimeParams.setSessionType("1");
          l_tradingTimeParams.setStartTime(100000 + "");
          //ç≈èIï¬ã«éûä‘:150000
          l_tradingTimeParams.setEndTime(150000 + "");
          TestDBUtility.insertWithDel(l_tradingTimeParams);

          BranchParams l_branchParams = new BranchParams(TestDBUtility.getBranchRow());
          TestDBUtility.insertWithDel(l_branchParams);

          BranchPreferencesParams l_branchPreferencesParams =
              new BranchPreferencesParams(TestDBUtility.getBranchPreferencesRow());
          l_branchPreferencesParams.setBranchId(33381);
          l_branchPreferencesParams.setName("evening.session.div");
          //isó[èÍé¿é{() == false
          l_branchPreferencesParams.setValue("0");
          l_branchPreferencesParams.setNameSerialNo(2);
          TestDBUtility.insertWithDel(l_branchPreferencesParams);

          InstitutionParams l_institutionParams =
              TestDBUtility.getInstitutionRow();
          l_institutionParams.setInstitutionCode("0D");
          TestDBUtility.insertWithDel(l_tradingTimeParams);

          OrderexecutionEndParams l_orderexecutionEndParams =
              new OrderexecutionEndParams(TestDBUtility.getOrderexecutionEndRow());
          l_orderexecutionEndParams.setInstitutionCode("0D");
          l_orderexecutionEndParams.setProductType(ProductTypeEnum.IFO);
          l_orderexecutionEndParams.setFutureOptionDiv("0");
          l_orderexecutionEndParams.setOrderexecutionEndType("1");
          //èàóùçœà»äO
          l_orderexecutionEndParams.setCarryoverEndType("0");
          TestDBUtility.insertWithDel(l_orderexecutionEndParams);

          WEB3GentradeTradingTimeManagement.validateTriggerOrderAccept(
              new WEB3GentradeInstitution(l_institutionParams),
              ProductTypeEnum.IFO,
              "0",
              "1");
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
   * testValidateTriggerOrderAccept_Case0004
   */
  public void testValidateTriggerOrderAccept_Case0004()
  {
      final String STR_METHOD_NAME = " testValidateTriggerOrderAccept_Case0004()";
      log.entering(TEST_START + STR_METHOD_NAME);

      TestBaseForMock.MOCK_MANAGER.setIsMockUsed(false);
      try
      {
          //åªç›ì˙éû:20040718110000
          //isÉgÉäÉKî≠çs() == false
          ThreadLocalSystemAttributesRegistry.setAttribute(
              "xblocks.gtl.attributes.systemtimestamp",
          new Timestamp(WEB3DateUtility.getDate("20040718110000","yyyyMMddHHmmss").getTime()));
          WEB3GentradeTradingClendarContext l_context = new WEB3GentradeTradingClendarContext();
          l_context.setInstitutionCode("0D");
          l_context.setBranchCode("381");
          l_context.setTradingTimeType("26");
          l_context.setMarketCode("SP");
          l_context.setProductCode("0");
          ThreadLocalSystemAttributesRegistry.setAttribute("web3.tradingcalendarcontext", l_context);

          TradingTimeParams l_tradingTimeParams =
              TestDBUtility.getTradingTimeRow();
          l_tradingTimeParams.setInstitutionCode("0D");
          l_tradingTimeParams.setBranchCode("381");
          l_tradingTimeParams.setMarketCode("SP");
          l_tradingTimeParams.setTradingTimeType("26");
          l_tradingTimeParams.setProductCode("0");
          l_tradingTimeParams.setBizdateCalcParameter("0");
          l_tradingTimeParams.setSubmitMarketTrigger("1");
          l_tradingTimeParams.setBizDateType("0");
          //isó[èÍéûä‘ë—() == false
          l_tradingTimeParams.setSessionType("0");
          l_tradingTimeParams.setStartTime(100000 + "");
          //ç≈èIï¬ã«éûä‘:150000
          l_tradingTimeParams.setEndTime(150000 + "");
          TestDBUtility.insertWithDel(l_tradingTimeParams);

          BranchParams l_branchParams = new BranchParams(TestDBUtility.getBranchRow());
          TestDBUtility.insertWithDel(l_branchParams);

          BranchPreferencesParams l_branchPreferencesParams =
              new BranchPreferencesParams(TestDBUtility.getBranchPreferencesRow());
          l_branchPreferencesParams.setBranchId(33381);
          l_branchPreferencesParams.setName("evening.session.div");
          //isó[èÍé¿é{() == true
          l_branchPreferencesParams.setValue("1");
          l_branchPreferencesParams.setNameSerialNo(2);
          TestDBUtility.insertWithDel(l_branchPreferencesParams);

          InstitutionParams l_institutionParams =
              TestDBUtility.getInstitutionRow();
          l_institutionParams.setInstitutionCode("0D");
          TestDBUtility.insertWithDel(l_tradingTimeParams);

          OrderexecutionEndParams l_orderexecutionEndParams =
              new OrderexecutionEndParams(TestDBUtility.getOrderexecutionEndRow());
          l_orderexecutionEndParams.setInstitutionCode("0D");
          l_orderexecutionEndParams.setProductType(ProductTypeEnum.IFO);
          l_orderexecutionEndParams.setFutureOptionDiv("0");
          l_orderexecutionEndParams.setOrderexecutionEndType("1");
          //èàóùçœà»äO
          l_orderexecutionEndParams.setCarryoverEndType("0");
          TestDBUtility.insertWithDel(l_orderexecutionEndParams);

          WEB3GentradeTradingTimeManagement.validateTriggerOrderAccept(
              new WEB3GentradeInstitution(l_institutionParams),
              ProductTypeEnum.IFO,
              "0",
              "1");
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
   * testValidateTriggerOrderAccept_Case0005
   */
  public void testValidateTriggerOrderAccept_Case0005()
  {
      final String STR_METHOD_NAME = " testValidateTriggerOrderAccept_Case0005()";
      log.entering(TEST_START + STR_METHOD_NAME);

      TestBaseForMock.MOCK_MANAGER.setIsMockUsed(false);
      try
      {
          //åªç›ì˙éû:20040718110000
          //isÉgÉäÉKî≠çs() == true
          ThreadLocalSystemAttributesRegistry.setAttribute(
              "xblocks.gtl.attributes.systemtimestamp",
          new Timestamp(WEB3DateUtility.getDate("20040719110000","yyyyMMddHHmmss").getTime()));
          WEB3GentradeTradingClendarContext l_context = new WEB3GentradeTradingClendarContext();
          l_context.setInstitutionCode("0D");
          l_context.setBranchCode("381");
          l_context.setTradingTimeType("26");
          l_context.setMarketCode("SP");
          l_context.setProductCode("0");
          ThreadLocalSystemAttributesRegistry.setAttribute("web3.tradingcalendarcontext", l_context);

          TradingTimeParams l_tradingTimeParams =
              TestDBUtility.getTradingTimeRow();
          l_tradingTimeParams.setInstitutionCode("0D");
          l_tradingTimeParams.setBranchCode("381");
          l_tradingTimeParams.setMarketCode("SP");
          l_tradingTimeParams.setTradingTimeType("26");
          l_tradingTimeParams.setProductCode("0");
          l_tradingTimeParams.setBizdateCalcParameter("0");
          //isÉgÉäÉKî≠çs() == true
          l_tradingTimeParams.setSubmitMarketTrigger("1");
          l_tradingTimeParams.setBizDateType("1");
          //isó[èÍéûä‘ë—() == true
          l_tradingTimeParams.setSessionType("1");
          l_tradingTimeParams.setStartTime(100000 + "");
          //ç≈èIï¬ã«éûä‘:150000
          l_tradingTimeParams.setEndTime(150000 + "");
          TestDBUtility.insertWithDel(l_tradingTimeParams);

          BranchParams l_branchParams = new BranchParams(TestDBUtility.getBranchRow());
          TestDBUtility.insertWithDel(l_branchParams);

          BranchPreferencesParams l_branchPreferencesParams =
              new BranchPreferencesParams(TestDBUtility.getBranchPreferencesRow());
          l_branchPreferencesParams.setBranchId(33381);
          l_branchPreferencesParams.setName("evening.session.div");
          //isó[èÍé¿é{() == true
          l_branchPreferencesParams.setValue("1");
          l_branchPreferencesParams.setNameSerialNo(2);
          TestDBUtility.insertWithDel(l_branchPreferencesParams);

          InstitutionParams l_institutionParams =
              TestDBUtility.getInstitutionRow();
          l_institutionParams.setInstitutionCode("0D");
          TestDBUtility.insertWithDel(l_tradingTimeParams);

          OrderexecutionEndParams l_orderexecutionEndParams =
              new OrderexecutionEndParams(TestDBUtility.getOrderexecutionEndRow());
          l_orderexecutionEndParams.setInstitutionCode("0D");
          l_orderexecutionEndParams.setProductType(ProductTypeEnum.IFO);
          l_orderexecutionEndParams.setFutureOptionDiv("0");
          l_orderexecutionEndParams.setOrderexecutionEndType("1");
          //èàóùçœà»äO
          l_orderexecutionEndParams.setCarryoverEndType("0");
          TestDBUtility.insertWithDel(l_orderexecutionEndParams);

          WEB3GentradeTradingTimeManagement.validateTriggerOrderAccept(
              new WEB3GentradeInstitution(l_institutionParams),
              ProductTypeEnum.IFO,
              "0",
              "1");
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
   * testValidateTriggerOrderAccept_Case0006
   */
  public void testValidateTriggerOrderAccept_Case0006()
  {
      final String STR_METHOD_NAME = " testValidateTriggerOrderAccept_Case0006()";
      log.entering(TEST_START + STR_METHOD_NAME);

      TestBaseForMock.MOCK_MANAGER.setIsMockUsed(false);
      try
      {
          //åªç›ì˙éû:20040718110000
          //isÉgÉäÉKî≠çs() == false
          ThreadLocalSystemAttributesRegistry.setAttribute(
              "xblocks.gtl.attributes.systemtimestamp",
          new Timestamp(WEB3DateUtility.getDate("20040718160000","yyyyMMddHHmmss").getTime()));
          WEB3GentradeTradingClendarContext l_context = new WEB3GentradeTradingClendarContext();
          l_context.setInstitutionCode("0D");
          l_context.setBranchCode("381");
          l_context.setTradingTimeType("26");
          l_context.setMarketCode("SP");
          l_context.setProductCode("0");
          ThreadLocalSystemAttributesRegistry.setAttribute("web3.tradingcalendarcontext", l_context);

          TradingTimeParams l_tradingTimeParams =
              TestDBUtility.getTradingTimeRow();
          l_tradingTimeParams.setInstitutionCode("0D");
          l_tradingTimeParams.setBranchCode("381");
          l_tradingTimeParams.setMarketCode("SP");
          l_tradingTimeParams.setTradingTimeType("26");
          l_tradingTimeParams.setProductCode("0");
          l_tradingTimeParams.setBizdateCalcParameter("0");
          l_tradingTimeParams.setSubmitMarketTrigger("1");
          l_tradingTimeParams.setBizDateType("0");
          //isó[èÍéûä‘ë—() == true
          l_tradingTimeParams.setSessionType("1");
          l_tradingTimeParams.setStartTime(100000 + "");
          //ç≈èIï¬ã«éûä‘:150000
          l_tradingTimeParams.setEndTime(150000 + "");
          TestDBUtility.insertWithDel(l_tradingTimeParams);

          BranchParams l_branchParams = new BranchParams(TestDBUtility.getBranchRow());
          TestDBUtility.insertWithDel(l_branchParams);

          BranchPreferencesParams l_branchPreferencesParams =
              new BranchPreferencesParams(TestDBUtility.getBranchPreferencesRow());
          l_branchPreferencesParams.setBranchId(33381);
          l_branchPreferencesParams.setName("evening.session.div");
          //isó[èÍé¿é{() == true
          l_branchPreferencesParams.setValue("1");
          l_branchPreferencesParams.setNameSerialNo(2);
          TestDBUtility.insertWithDel(l_branchPreferencesParams);

          InstitutionParams l_institutionParams =
              TestDBUtility.getInstitutionRow();
          l_institutionParams.setInstitutionCode("0D");
          TestDBUtility.insertWithDel(l_tradingTimeParams);

          OrderexecutionEndParams l_orderexecutionEndParams =
              new OrderexecutionEndParams(TestDBUtility.getOrderexecutionEndRow());
          l_orderexecutionEndParams.setInstitutionCode("0D");
          l_orderexecutionEndParams.setProductType(ProductTypeEnum.IFO);
          l_orderexecutionEndParams.setFutureOptionDiv("0");
          l_orderexecutionEndParams.setOrderexecutionEndType("1");
          //èàóùçœ
          l_orderexecutionEndParams.setCarryoverEndType("1");
          TestDBUtility.insertWithDel(l_orderexecutionEndParams);

          WEB3GentradeTradingTimeManagement.validateTriggerOrderAccept(
              new WEB3GentradeInstitution(l_institutionParams),
              ProductTypeEnum.IFO,
              "0",
              "1");
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
