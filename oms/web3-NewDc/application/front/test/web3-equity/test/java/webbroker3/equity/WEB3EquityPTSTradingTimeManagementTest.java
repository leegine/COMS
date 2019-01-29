head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.04.07.01.11.35;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	5444d9d078c63f4;
filename	WEB3EquityPTSTradingTimeManagementTest.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (äî)ëÂòaëçå§ èÿåîÉ\ÉäÉÖÅ[ÉVÉáÉìÉVÉXÉeÉÄëÊìÒïî
File Name        : ÅiWEB3EquityPTSTradingTimeManagementTest.javaÅj
Author Name      : Daiwa Institute of Research
Revision History : 2007/12/24 ÉgÉEñNç| (íÜêu) êVãKçÏê¨
*/
package webbroker3.equity;

import java.sql.Timestamp;
import java.util.Date;

import com.fitechlabs.xtrade.kernel.util.ThreadLocalSystemAttributesRegistry;
import com.fitechlabs.xtrade.plugin.tc.gentrade.ProductTypeEnum;
import com.fitechlabs.xtrade.plugin.tc.gentrade.data.BranchParams;
import com.fitechlabs.xtrade.plugin.tc.gentrade.data.BranchRow;
import com.fitechlabs.xtrade.plugin.tc.gentrade.data.InstitutionParams;
import com.fitechlabs.xtrade.plugin.tc.gentrade.data.InstitutionRow;

import test.util.TestDBUtility;

import webbroker3.common.WEB3BaseException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.gentrade.WEB3GentradeBranch;
import webbroker3.gentrade.WEB3GentradeTradingClendarContext;
import webbroker3.gentrade.data.BranchMarketPtsDealtCondParams;
import webbroker3.gentrade.data.BranchMarketPtsDealtCondRow;
import webbroker3.gentrade.data.CalendarRow;
import webbroker3.gentrade.data.FeqCalendarParams;
import webbroker3.gentrade.data.FeqCalendarRow;
import webbroker3.gentrade.data.OrderAcceptStatusParams;
import webbroker3.gentrade.data.OrderAcceptStatusRow;
import webbroker3.gentrade.data.TradingTimeParams;
import webbroker3.gentrade.data.TradingTimeRow;
import webbroker3.mock.TestBaseForMock;
import webbroker3.util.WEB3DateUtility;
import webbroker3.util.WEB3LogUtility;

public class WEB3EquityPTSTradingTimeManagementTest extends TestBaseForMock
{
    
    private static WEB3LogUtility log =
        WEB3LogUtility.getInstance(WEB3EquityPTSTradingTimeManagementTest.class);

    public WEB3EquityPTSTradingTimeManagementTest(String arg0)
    {
        super(arg0);
    }

    protected void setUp() throws Exception
    {
        super.setUp();
        TestDBUtility.deleteAll(CalendarRow.TYPE);
    }

    protected void tearDown() throws Exception
    {
        super.tearDown();
    }

    /*
     * Test method for 'webbroker3.equity.WEB3EquityPTSTradingTimeManagement.validateOrderAccept()'
     */
    public void testValidateOrderAccept_0001()
    {
        final String STR_METHOD_NAME = "testValidateOrderAccept_0001()";
        log.entering(TEST_START + STR_METHOD_NAME);
        
        try
        {
            ThreadLocalSystemAttributesRegistry.setAttribute(
                "web3.tradingcalendarcontext",
                new WEB3GentradeTradingClendarContext());
            WEB3EquityPTSTradingTimeManagement.validateOrderAccept();
            fail();
        }
        catch (WEB3BaseException l_ex)
        {
            assertEquals(WEB3ErrorCatalog.SYSTEM_ERROR_80006, l_ex.getErrorInfo());
        }
        catch (Exception l_ex)
        {
            log.error(STR_METHOD_NAME, l_ex);
            log.exiting(TEST_END + STR_METHOD_NAME);
            fail();
        }
        
        log.exiting(TEST_END + STR_METHOD_NAME);
    }
    
    public void testValidateOrderAccept_0002()
    {
        final String STR_METHOD_NAME = "testValidateOrderAccept_0002()";
        log.entering(TEST_START + STR_METHOD_NAME);
        
        try
        {
            WEB3GentradeTradingClendarContext l_clendarContext =
                (WEB3GentradeTradingClendarContext)ThreadLocalSystemAttributesRegistry.getAttribute(
                    "web3.tradingcalendarcontext");
            
            l_clendarContext.setOrderAcceptProduct("01");
            l_clendarContext.setOrderAcceptTransaction("01");
            
            TestDBUtility.deleteAll(OrderAcceptStatusRow.TYPE);
            OrderAcceptStatusParams l_orderAcceptStatusParams = TestDBUtility.getOrderAcceptStatusRow();
            l_orderAcceptStatusParams.setBranchCode("123");
            l_orderAcceptStatusParams.setOrderAcceptStatus("1");
            TestDBUtility.insertWithDel(l_orderAcceptStatusParams);
            
            WEB3EquityPTSTradingTimeManagement.validateOrderAccept();
            fail();
        }
        catch (WEB3BaseException l_ex)
        {
            assertEquals(WEB3ErrorCatalog.BUSINESS_ERROR_00011, l_ex.getErrorInfo());
        }
        catch (Exception l_ex)
        {
            log.error(STR_METHOD_NAME, l_ex);
            log.exiting(TEST_END + STR_METHOD_NAME);
            fail();
        }
        
        log.exiting(TEST_END + STR_METHOD_NAME);
    }
    
    public void testValidateOrderAccept_0003()
    {
        final String STR_METHOD_NAME = "testValidateOrderAccept_0003()";
        log.entering(TEST_START + STR_METHOD_NAME);
        
        try
        {
            WEB3GentradeTradingClendarContext l_clendarContext =
                (WEB3GentradeTradingClendarContext)ThreadLocalSystemAttributesRegistry.getAttribute(
                    "web3.tradingcalendarcontext");
            
            l_clendarContext.setOrderAcceptProduct("01");
            l_clendarContext.setOrderAcceptTransaction("01");
            
            TestDBUtility.deleteAll(OrderAcceptStatusRow.TYPE);
            OrderAcceptStatusParams l_orderAcceptStatusParams = TestDBUtility.getOrderAcceptStatusRow();
            l_orderAcceptStatusParams.setBranchCode("123");
            l_orderAcceptStatusParams.setOrderAcceptStatus("2");
            TestDBUtility.insertWithDel(l_orderAcceptStatusParams);
            
            WEB3EquityPTSTradingTimeManagement.validateOrderAccept();
            fail();
        }
        catch (WEB3BaseException l_ex)
        {
            assertEquals(WEB3ErrorCatalog.BUSINESS_ERROR_00012, l_ex.getErrorInfo());
        }
        catch (Exception l_ex)
        {
            log.error(STR_METHOD_NAME, l_ex);
            log.exiting(TEST_END + STR_METHOD_NAME);
            fail();
        }
        
        log.exiting(TEST_END + STR_METHOD_NAME);
    }
    
    public void testValidateOrderAccept_0004()
    {
        final String STR_METHOD_NAME = "testValidateOrderAccept_0004()";
        log.entering(TEST_START + STR_METHOD_NAME);
        
        try
        {
            WEB3GentradeTradingClendarContext l_clendarContext =
                (WEB3GentradeTradingClendarContext)ThreadLocalSystemAttributesRegistry.getAttribute(
                    "web3.tradingcalendarcontext");
            
            l_clendarContext.setOrderAcceptProduct("01");
            l_clendarContext.setOrderAcceptTransaction("07");
            
            TestDBUtility.deleteAll(OrderAcceptStatusRow.TYPE);
            OrderAcceptStatusParams l_orderAcceptStatusParams = TestDBUtility.getOrderAcceptStatusRow();
            l_orderAcceptStatusParams.setBranchCode("123");
            l_orderAcceptStatusParams.setOrderAcceptStatus("0");
            l_orderAcceptStatusParams.setOrderAccTransaction("07");
            TestDBUtility.insertWithDel(l_orderAcceptStatusParams);
            
            TestDBUtility.deleteAll(TradingTimeRow.TYPE);
            
            WEB3EquityPTSTradingTimeManagement.validateOrderAccept();
            
            assertTrue(true);
        }
        catch (Exception l_ex)
        {
            log.error(STR_METHOD_NAME, l_ex);
            log.exiting(TEST_END + STR_METHOD_NAME);
            fail();
        }
        
        log.exiting(TEST_END + STR_METHOD_NAME);
    }

    public void testValidateOrderAccept_0005()
    {
        final String STR_METHOD_NAME = "testValidateOrderAccept_0005()";
        log.entering(TEST_START + STR_METHOD_NAME);
        
        try
        {
            WEB3GentradeTradingClendarContext l_clendarContext =
                (WEB3GentradeTradingClendarContext)ThreadLocalSystemAttributesRegistry.getAttribute(
                    "web3.tradingcalendarcontext");
            
            l_clendarContext.setOrderAcceptProduct("01");
            l_clendarContext.setOrderAcceptTransaction("01");
            
            TestDBUtility.deleteAll(OrderAcceptStatusRow.TYPE);
            OrderAcceptStatusParams l_orderAcceptStatusParams = TestDBUtility.getOrderAcceptStatusRow();
            l_orderAcceptStatusParams.setBranchCode("123");
            l_orderAcceptStatusParams.setOrderAcceptStatus("0");
            l_orderAcceptStatusParams.setOrderAccTransaction("01");
            TestDBUtility.insertWithDel(l_orderAcceptStatusParams);
            
            TestDBUtility.deleteAll(TradingTimeRow.TYPE);
            
            WEB3EquityPTSTradingTimeManagement.validateOrderAccept();
            fail();
        }
        catch (WEB3BaseException l_ex)
        {
            assertEquals(WEB3ErrorCatalog.SYSTEM_ERROR_80005, l_ex.getErrorInfo());
        }
        catch (Exception l_ex)
        {
            log.error(STR_METHOD_NAME, l_ex);
            log.exiting(TEST_END + STR_METHOD_NAME);
            fail();
        }
        
        log.exiting(TEST_END + STR_METHOD_NAME);
    }
    
    public void testValidateOrderAccept_0006()
    {
        final String STR_METHOD_NAME = "testValidateOrderAccept_0006()";
        log.entering(TEST_START + STR_METHOD_NAME);
        
        try
        {
            ThreadLocalSystemAttributesRegistry.setAttribute(
                    "xblocks.gtl.attributes.systemtimestamp",
                    new Timestamp(WEB3DateUtility.getDate("20071226111022","yyyyMMddHHmmss").getTime()));
            
            WEB3GentradeTradingClendarContext l_clendarContext =
                (WEB3GentradeTradingClendarContext)ThreadLocalSystemAttributesRegistry.getAttribute(
                    "web3.tradingcalendarcontext");
            
            l_clendarContext.setOrderAcceptProduct("01");
            l_clendarContext.setOrderAcceptTransaction("01");
            
            TestDBUtility.deleteAll(OrderAcceptStatusRow.TYPE);
            OrderAcceptStatusParams l_orderAcceptStatusParams = TestDBUtility.getOrderAcceptStatusRow();
            l_orderAcceptStatusParams.setBranchCode("123");
            l_orderAcceptStatusParams.setOrderAcceptStatus("0");
            l_orderAcceptStatusParams.setOrderAccTransaction("01");
            TestDBUtility.insertWithDel(l_orderAcceptStatusParams);
            
            TestDBUtility.deleteAll(TradingTimeRow.TYPE);
            TradingTimeParams l_tradingTimeParams = TestDBUtility.getTradingTimeRow();
            l_tradingTimeParams.setBranchCode("123");
            l_tradingTimeParams.setMarketCode("N1");
            l_tradingTimeParams.setTradingTimeType("01");
            l_tradingTimeParams.setBizDateType("1");
            l_tradingTimeParams.setStartTime("110000");
            l_tradingTimeParams.setEndTime("200000");
            TestDBUtility.insertWithDel(l_tradingTimeParams);
            
            TestDBUtility.deleteAll(CalendarRow.TYPE);
            
            WEB3EquityPTSTradingTimeManagement.validateOrderAccept();
            assertTrue(true);
        }
        catch (Exception l_ex)
        {
            log.error(STR_METHOD_NAME, l_ex);
            log.exiting(TEST_END + STR_METHOD_NAME);
            fail();
        }
        
        log.exiting(TEST_END + STR_METHOD_NAME);
    }
    
    public void testValidateOrderAccept_0007()
    {
        final String STR_METHOD_NAME = "testValidateOrderAccept_0007()";
        log.entering(TEST_START + STR_METHOD_NAME);
        
        try
        {
            ThreadLocalSystemAttributesRegistry.setAttribute(
                    "xblocks.gtl.attributes.systemtimestamp",
                    new Timestamp(WEB3DateUtility.getDate("20071226111022","yyyyMMddHHmmss").getTime()));
            
            WEB3GentradeTradingClendarContext l_clendarContext =
                (WEB3GentradeTradingClendarContext)ThreadLocalSystemAttributesRegistry.getAttribute(
                    "web3.tradingcalendarcontext");
            
            l_clendarContext.setOrderAcceptProduct("01");
            l_clendarContext.setOrderAcceptTransaction("01");
            
            TestDBUtility.deleteAll(OrderAcceptStatusRow.TYPE);
            
            TestDBUtility.deleteAll(TradingTimeRow.TYPE);
            TradingTimeParams l_tradingTimeParams = TestDBUtility.getTradingTimeRow();
            l_tradingTimeParams.setBranchCode("123");
            l_tradingTimeParams.setMarketCode("N1");
            l_tradingTimeParams.setTradingTimeType("01");
            l_tradingTimeParams.setBizDateType("1");
            l_tradingTimeParams.setStartTime("120000");
            l_tradingTimeParams.setEndTime("200000");
            TestDBUtility.insertWithDel(l_tradingTimeParams);
            
            WEB3EquityPTSTradingTimeManagement.validateOrderAccept();
            fail();
        }
        catch (WEB3BaseException l_ex)
        {
            assertEquals(WEB3ErrorCatalog.BUSINESS_ERROR_00013, l_ex.getErrorInfo());
        }
        catch (Exception l_ex)
        {
            log.error(STR_METHOD_NAME, l_ex);
            log.exiting(TEST_END + STR_METHOD_NAME);
            fail();
        }
        
        log.exiting(TEST_END + STR_METHOD_NAME);
    }
    

    /*
     * Test method for 'webbroker3.equity.WEB3EquityPTSTradingTimeManagement.setTimestamp()'
     */
    public void testSetTimestamp_0001()
    {
        final String STR_METHOD_NAME = "testSetTimestamp_0001()";
        log.entering(TEST_START + STR_METHOD_NAME);
        
        try
        {
            ThreadLocalSystemAttributesRegistry.setAttribute(
                "web3.tradingcalendarcontext",
                new WEB3GentradeTradingClendarContext());
            WEB3EquityPTSTradingTimeManagement.setTimestamp();
            fail();
        }
        catch (WEB3BaseException l_ex)
        {
            assertEquals(WEB3ErrorCatalog.SYSTEM_ERROR_80006, l_ex.getErrorInfo());
        }
        catch (Exception l_ex)
        {
            log.error(STR_METHOD_NAME, l_ex);
            log.exiting(TEST_END + STR_METHOD_NAME);
            fail();
        }
        
        log.exiting(TEST_END + STR_METHOD_NAME);
        
    }
    
    public void testSetTimestamp_0002()
    {
        final String STR_METHOD_NAME = "testSetTimestamp_0002()";
        log.entering(TEST_START + STR_METHOD_NAME);
        
        try
        {
            TestDBUtility.deleteAll(TradingTimeRow.TYPE);
            WEB3EquityPTSTradingTimeManagement.setTimestamp();
            fail();
        }
        catch (WEB3BaseException l_ex)
        {
            assertEquals(WEB3ErrorCatalog.SYSTEM_ERROR_80005, l_ex.getErrorInfo());
        }
        catch (Exception l_ex)
        {
            log.error(STR_METHOD_NAME, l_ex);
            log.exiting(TEST_END + STR_METHOD_NAME);
            fail();
        }
        
        log.exiting(TEST_END + STR_METHOD_NAME);
        
    }
    
    public void testSetTimestamp_0003()
    {
        final String STR_METHOD_NAME = "testSetTimestamp_0003()";
        log.entering(TEST_START + STR_METHOD_NAME);
        
        MOCK_MANAGER.setIsMockUsed(true);
        
        try
        {
            TestDBUtility.deleteAll(TradingTimeRow.TYPE);
            TradingTimeParams l_tradingTimeParams = TestDBUtility.getTradingTimeRow();
            l_tradingTimeParams.setBranchCode("123");
            l_tradingTimeParams.setMarketCode("N1");
            l_tradingTimeParams.setTradingTimeType("01");
            l_tradingTimeParams.setBizDateType("1");
            l_tradingTimeParams.setStartTime("235050");
            TestDBUtility.insertWithDel(l_tradingTimeParams);
            
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                "com.fitechlabs.xtrade.plugin.tc.gentrade.stdimpls.TradingSystemImpl",
                "getSystemTimestamp",
                new Class[] {},
                new Timestamp(WEB3DateUtility.getDate("20071225", "yyyyMMdd").getTime()));
            
            WEB3EquityPTSTradingTimeManagement.setTimestamp();
            fail();
        }
        catch (WEB3BaseException l_ex)
        {
            assertEquals(WEB3ErrorCatalog.SYSTEM_ERROR_80005, l_ex.getErrorInfo());
        }
        catch (Exception l_ex)
        {
            log.error(STR_METHOD_NAME, l_ex);
            log.exiting(TEST_END + STR_METHOD_NAME);
            fail();
        }
        
        log.exiting(TEST_END + STR_METHOD_NAME);
        
    }
    
    public void testSetTimestamp_0004()
    {
        final String STR_METHOD_NAME = "testSetTimestamp_0004()";
        log.entering(TEST_START + STR_METHOD_NAME);
        
        MOCK_MANAGER.setIsMockUsed(true);
        
        try
        {
            TestDBUtility.deleteAll(TradingTimeRow.TYPE);
            TradingTimeParams l_tradingTimeParams = TestDBUtility.getTradingTimeRow();
            l_tradingTimeParams.setBranchCode("123");
            l_tradingTimeParams.setMarketCode("N1");
            l_tradingTimeParams.setTradingTimeType("01");
            l_tradingTimeParams.setBizDateType("1");
            TestDBUtility.insertWithDel(l_tradingTimeParams);
            
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                "com.fitechlabs.xtrade.plugin.tc.gentrade.stdimpls.TradingSystemImpl",
                "getSystemTimestamp",
                new Class[] {},
                new Timestamp(WEB3DateUtility.getDate("20071225", "yyyyMMdd").getTime()));
            
            WEB3EquityPTSTradingTimeManagement.setTimestamp();
            
            WEB3GentradeTradingClendarContext l_clendarContext =
                (WEB3GentradeTradingClendarContext)
                    ThreadLocalSystemAttributesRegistry.getAttribute("web3.tradingcalendarcontext");
            
            assertEquals("1", l_clendarContext.getBizDateType());
            assertEquals("0", l_clendarContext.getSubmitMarketTrigger());
            assertEquals("1", l_clendarContext.getBizdateCalcParameter());
            assertEquals("0", l_clendarContext.getEnableOrder());
            Integer l_intOffset =
                (Integer)ThreadLocalSystemAttributesRegistry.getAttribute("xblocks.gtl.attributes.bizdate.offset");
            assertEquals("0", l_intOffset.intValue() + "");
        }
        catch (Exception l_ex)
        {
            log.error(STR_METHOD_NAME, l_ex);
            log.exiting(TEST_END + STR_METHOD_NAME);
            fail();
        }
        
        log.exiting(TEST_END + STR_METHOD_NAME);
        
    }
    
    public void testSetTimestamp_0005()
    {
        final String STR_METHOD_NAME = "testSetTimestamp_0005()";
        log.entering(TEST_START + STR_METHOD_NAME);
        
        MOCK_MANAGER.setIsMockUsed(true);
        
        try
        {
            TestDBUtility.deleteAll(TradingTimeRow.TYPE);
            TradingTimeParams l_tradingTimeParams = TestDBUtility.getTradingTimeRow();
            l_tradingTimeParams.setBranchCode("123");
            l_tradingTimeParams.setMarketCode("N1");
            l_tradingTimeParams.setTradingTimeType("01");
            l_tradingTimeParams.setBizDateType("1");
            l_tradingTimeParams.setEnableOrder(null);
            TestDBUtility.insertWithDel(l_tradingTimeParams);
            
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                "com.fitechlabs.xtrade.plugin.tc.gentrade.stdimpls.TradingSystemImpl",
                "getSystemTimestamp",
                new Class[] {},
                new Timestamp(WEB3DateUtility.getDate("20071225", "yyyyMMdd").getTime()));
            
            WEB3EquityPTSTradingTimeManagement.setTimestamp();
            
            WEB3GentradeTradingClendarContext l_clendarContext =
                (WEB3GentradeTradingClendarContext)
                    ThreadLocalSystemAttributesRegistry.getAttribute("web3.tradingcalendarcontext");
            
            assertEquals("1", l_clendarContext.getBizDateType());
            assertEquals("0", l_clendarContext.getSubmitMarketTrigger());
            assertEquals("1", l_clendarContext.getBizdateCalcParameter());
            assertEquals("1", l_clendarContext.getEnableOrder());
            Integer l_intOffset =
                (Integer)ThreadLocalSystemAttributesRegistry.getAttribute("xblocks.gtl.attributes.bizdate.offset");
            assertEquals("0", l_intOffset.intValue() + "");
        }
        catch (Exception l_ex)
        {
            log.error(STR_METHOD_NAME, l_ex);
            log.exiting(TEST_END + STR_METHOD_NAME);
            fail();
        }
        
        log.exiting(TEST_END + STR_METHOD_NAME);
        
    }

    /*
     * Test method for 'webbroker3.equity.WEB3EquityPTSTradingTimeManagement.getOrderBizDate(Date)'
     */
    public void testGetOrderBizDate_0001()
    {
        final String STR_METHOD_NAME = "testGetOrderBizDate_0001()";
        log.entering(TEST_START + STR_METHOD_NAME);
        
        try
        {
//            TestDBUtility.deleteAll(TradingTimeRow.TYPE);
//            TradingTimeParams l_tradingTimeParams = TestDBUtility.getTradingTimeRow();
//            l_tradingTimeParams.setBranchCode("123");
//            l_tradingTimeParams.setMarketCode("N1");
//            l_tradingTimeParams.setTradingTimeType("01");
//            l_tradingTimeParams.setBizDateType("5");
//            l_tradingTimeParams.setBizdateCalcParameter("2");
//            TestDBUtility.insertWithDel(l_tradingTimeParams);

//            ThreadLocalSystemAttributesRegistry.setAttribute(
//                "xblocks.gtl.attributes.systemtimestamp",
//                new Timestamp(WEB3DateUtility.getDate("20071225", "yyyyMMdd").getTime()));
            
            ThreadLocalSystemAttributesRegistry.setAttribute(
                "web3.tradingcalendarcontext",
                null);
            
            Date l_datBizDate = WEB3DateUtility.getDate("20071225", "yyyyMMdd");
            Date l_datResult = WEB3EquityPTSTradingTimeManagement.getOrderBizDate(l_datBizDate);
            
            assertEquals(l_datBizDate, l_datResult);
        }
        catch (Exception l_ex)
        {
            log.error(STR_METHOD_NAME, l_ex);
            log.exiting(TEST_END + STR_METHOD_NAME);
            fail();
        }
        
        log.exiting(TEST_END + STR_METHOD_NAME);
    }
    
    public void testGetOrderBizDate_0002()
    {
        final String STR_METHOD_NAME = "testGetOrderBizDate_0002()";
        log.entering(TEST_START + STR_METHOD_NAME);
        
        try
        {
            TestDBUtility.deleteAll(TradingTimeRow.TYPE);
            TradingTimeParams l_tradingTimeParams = TestDBUtility.getTradingTimeRow();
            l_tradingTimeParams.setBranchCode("123");
            l_tradingTimeParams.setMarketCode("N1");
            l_tradingTimeParams.setTradingTimeType("01");
            l_tradingTimeParams.setBizDateType("1");
            TestDBUtility.insertWithDel(l_tradingTimeParams);
            
          ThreadLocalSystemAttributesRegistry.setAttribute(
          "xblocks.gtl.attributes.systemtimestamp",
          new Timestamp(WEB3DateUtility.getDate("20071225", "yyyyMMdd").getTime()));
          
          ThreadLocalSystemAttributesRegistry.setAttribute(
              "web3.attributes.basetimestampfororderbizdate",
              null);
            
            Date l_datBizDate = WEB3DateUtility.getDate("20071224", "yyyyMMdd");
            WEB3EquityPTSTradingTimeManagement.getOrderBizDate(l_datBizDate);
            fail();
        }
        catch (WEB3BaseException l_ex)
        {
            assertEquals(WEB3ErrorCatalog.BUSINESS_ERROR_00205, l_ex.getErrorInfo());
        }
        catch (Exception l_ex)
        {
            log.error(STR_METHOD_NAME, l_ex);
            log.exiting(TEST_END + STR_METHOD_NAME);
            fail();
        }
        
        log.exiting(TEST_END + STR_METHOD_NAME);
    }
    
    public void testGetOrderBizDate_0003()
    {
        final String STR_METHOD_NAME = "testGetOrderBizDate_0003()";
        log.entering(TEST_START + STR_METHOD_NAME);
        
        try
        {
            TestDBUtility.deleteAll(TradingTimeRow.TYPE);
            TradingTimeParams l_tradingTimeParams = TestDBUtility.getTradingTimeRow();
            l_tradingTimeParams.setBranchCode("123");
            l_tradingTimeParams.setMarketCode("N1");
            l_tradingTimeParams.setTradingTimeType("01");
            l_tradingTimeParams.setBizDateType("1");
            l_tradingTimeParams.setBizdateCalcParameter("0");
            TestDBUtility.insertWithDel(l_tradingTimeParams);
            
            ThreadLocalSystemAttributesRegistry.setAttribute(
                "xblocks.gtl.attributes.systemtimestamp",
                new Timestamp(WEB3DateUtility.getDate("20071225", "yyyyMMdd").getTime()));
                    
            ThreadLocalSystemAttributesRegistry.setAttribute(
                "web3.attributes.basetimestampfororderbizdate",
                null);
            
            Date l_datBizDate = WEB3DateUtility.getDate("20071225", "yyyyMMdd");
            Date l_datResult = WEB3EquityPTSTradingTimeManagement.getOrderBizDate(l_datBizDate);
            
            assertEquals(l_datBizDate, l_datResult);
        }
        catch (Exception l_ex)
        {
            log.error(STR_METHOD_NAME, l_ex);
            log.exiting(TEST_END + STR_METHOD_NAME);
            fail();
        }
        
        log.exiting(TEST_END + STR_METHOD_NAME);
    }

    /*
     * Test method for 'webbroker3.equity.WEB3EquityPTSTradingTimeManagement.isTradeOpenTimeZone()'
     */
    public void testIsTradeOpenTimeZone_0001()
    {
        final String STR_METHOD_NAME = "testIsTradeOpenTimeZone_0001()";
        log.entering(TEST_START + STR_METHOD_NAME);
        
        try
        {
            ThreadLocalSystemAttributesRegistry.setAttribute(
                "web3.tradingcalendarcontext",
                new WEB3GentradeTradingClendarContext());
            WEB3EquityPTSTradingTimeManagement.isTradeOpenTimeZone();
            fail();
        }
        catch (WEB3BaseException l_ex)
        {
            assertEquals(WEB3ErrorCatalog.SYSTEM_ERROR_80006, l_ex.getErrorInfo());
        }
        catch (Exception l_ex)
        {
            log.error(STR_METHOD_NAME, l_ex);
            log.exiting(TEST_END + STR_METHOD_NAME);
            fail();
        }
        
        log.exiting(TEST_END + STR_METHOD_NAME);
    }

    public void testIsTradeOpenTimeZone_0002()
    {
        final String STR_METHOD_NAME = "testIsTradeOpenTimeZone_0002()";
        log.entering(TEST_START + STR_METHOD_NAME);
        
        try
        {
            ThreadLocalSystemAttributesRegistry.setAttribute(
                    "xblocks.gtl.attributes.systemtimestamp",
                    new Timestamp(WEB3DateUtility.getDate("20071223","yyyyMMdd").getTime()));
            
            WEB3GentradeTradingClendarContext l_clendarContext =
                (WEB3GentradeTradingClendarContext)
                    ThreadLocalSystemAttributesRegistry.getAttribute(
                        "web3.tradingcalendarcontext");
            l_clendarContext.setTradingTimeType("10");
            
            TestDBUtility.deleteAll(FeqCalendarRow.TYPE);
            FeqCalendarParams l_feqCalendarParams = new FeqCalendarParams();
            l_feqCalendarParams.setInstitutionCode("0D");
            l_feqCalendarParams.setMarketCode("N1");
            l_feqCalendarParams.setBizDateType("0");
            l_feqCalendarParams.setBizDate(WEB3DateUtility.getDate("20071223","yyyyMMdd"));
            TestDBUtility.insertWithDel(l_feqCalendarParams);
            
            assertFalse(WEB3EquityPTSTradingTimeManagement.isTradeOpenTimeZone());
        }
        catch (Exception l_ex)
        {
            log.error(STR_METHOD_NAME, l_ex);
            log.exiting(TEST_END + STR_METHOD_NAME);
            fail();
        }
        
        log.exiting(TEST_END + STR_METHOD_NAME);
    }
    
    public void testIsTradeOpenTimeZone_0003()
    {
        final String STR_METHOD_NAME = "testIsTradeOpenTimeZone_0003()";
        log.entering(TEST_START + STR_METHOD_NAME);
        
        try
        {
            ThreadLocalSystemAttributesRegistry.setAttribute(
                    "xblocks.gtl.attributes.systemtimestamp",
                    new Timestamp(WEB3DateUtility.getDate("20071226","yyyyMMdd").getTime()));
            
            WEB3GentradeTradingClendarContext l_clendarContext =
                (WEB3GentradeTradingClendarContext)
                    ThreadLocalSystemAttributesRegistry.getAttribute(
                        "web3.tradingcalendarcontext");
            l_clendarContext.setTradingTimeType("04");
            
            TestDBUtility.deleteAll(TradingTimeRow.TYPE);
            
            assertFalse(WEB3EquityPTSTradingTimeManagement.isTradeOpenTimeZone());
        }
        catch (Exception l_ex)
        {
            log.error(STR_METHOD_NAME, l_ex);
            log.exiting(TEST_END + STR_METHOD_NAME);
            fail();
        }
        
        log.exiting(TEST_END + STR_METHOD_NAME);
    }
    
    public void testIsTradeOpenTimeZone_0004()
    {
        final String STR_METHOD_NAME = "testIsTradeOpenTimeZone_0004()";
        log.entering(TEST_START + STR_METHOD_NAME);
        
        try
        {
            ThreadLocalSystemAttributesRegistry.setAttribute(
                    "xblocks.gtl.attributes.systemtimestamp",
                    new Timestamp(WEB3DateUtility.getDate("20071226","yyyyMMdd").getTime()));
            
            WEB3GentradeTradingClendarContext l_clendarContext =
                (WEB3GentradeTradingClendarContext)
                    ThreadLocalSystemAttributesRegistry.getAttribute(
                        "web3.tradingcalendarcontext");
            l_clendarContext.setTradingTimeType("04");
            
            TestDBUtility.deleteAll(TradingTimeRow.TYPE);
            TradingTimeParams l_tradingTimeParams = TestDBUtility.getTradingTimeRow();
            l_tradingTimeParams.setBranchCode("123");
            l_tradingTimeParams.setMarketCode("N1");
            l_tradingTimeParams.setTradingTimeType("04");
            l_tradingTimeParams.setBizDateType("1");
            l_tradingTimeParams.setSubmitMarketTrigger("1");
            TestDBUtility.insertWithDel(l_tradingTimeParams);
            
            assertTrue(WEB3EquityPTSTradingTimeManagement.isTradeOpenTimeZone());
        }
        catch (Exception l_ex)
        {
            log.error(STR_METHOD_NAME, l_ex);
            log.exiting(TEST_END + STR_METHOD_NAME);
            fail();
        }
        
        log.exiting(TEST_END + STR_METHOD_NAME);
    }
    
    public void testIsTradeOpenTimeZone_0005()
    {
        final String STR_METHOD_NAME = "testIsTradeOpenTimeZone_0005()";
        log.entering(TEST_START + STR_METHOD_NAME);
        
        try
        {
            ThreadLocalSystemAttributesRegistry.setAttribute(
                    "xblocks.gtl.attributes.systemtimestamp",
                    new Timestamp(WEB3DateUtility.getDate("20071226100100","yyyyMMddHHmmss").getTime()));
            
            WEB3GentradeTradingClendarContext l_clendarContext =
                (WEB3GentradeTradingClendarContext)
                    ThreadLocalSystemAttributesRegistry.getAttribute(
                        "web3.tradingcalendarcontext");
            l_clendarContext.setTradingTimeType("04");
            
            TestDBUtility.deleteAll(TradingTimeRow.TYPE);
            TradingTimeParams l_tradingTimeParams = TestDBUtility.getTradingTimeRow();
            l_tradingTimeParams.setBranchCode("123");
            l_tradingTimeParams.setMarketCode("N1");
            l_tradingTimeParams.setTradingTimeType("04");
            l_tradingTimeParams.setBizDateType("1");
            l_tradingTimeParams.setSubmitMarketTrigger("1");
            l_tradingTimeParams.setStartTime("110000");
            TestDBUtility.insertWithDel(l_tradingTimeParams);
            
            assertFalse(WEB3EquityPTSTradingTimeManagement.isTradeOpenTimeZone());
        }
        catch (Exception l_ex)
        {
            log.error(STR_METHOD_NAME, l_ex);
            log.exiting(TEST_END + STR_METHOD_NAME);
            fail();
        }
        
        log.exiting(TEST_END + STR_METHOD_NAME);
    }


    /*
     * Test method for 'webbroker3.equity.WEB3EquityPTSTradingTimeManagement.getTradeCloseTime(String, String)'
     */
    public void testGetTradeCloseTime_0001()
    {
        final String STR_METHOD_NAME = "testGetTradeCloseTime_0001()";
        log.entering(TEST_START + STR_METHOD_NAME);
        
        try
        {
            TestDBUtility.deleteAll(TradingTimeRow.TYPE);
            TradingTimeParams l_tradingTimeParams = TestDBUtility.getTradingTimeRow();
            l_tradingTimeParams.setBranchCode("123");
            l_tradingTimeParams.setMarketCode("N1");
            l_tradingTimeParams.setTradingTimeType("01");
            l_tradingTimeParams.setBizDateType("1");
            l_tradingTimeParams.setBizdateCalcParameter("3");
            l_tradingTimeParams.setSubmitMarketTrigger("1");
            l_tradingTimeParams.setEndTime("200000");
            l_tradingTimeParams.setStartTime("160000");
            TestDBUtility.insertWithDel(l_tradingTimeParams);
            l_tradingTimeParams.setStartTime("140000");
            l_tradingTimeParams.setEndTime("190000");
            TestDBUtility.insertWithDel(l_tradingTimeParams);
            
            String l_strMarketCode = "N1";
            String l_strProductCode = "0";
            
            String l_strTradeCloseTime =
                WEB3EquityPTSTradingTimeManagement.getTradeCloseTime(l_strMarketCode, l_strProductCode);
            
            assertEquals("200000", l_strTradeCloseTime);
        }
        catch (Exception l_ex)
        {
            log.error(STR_METHOD_NAME, l_ex);
            log.exiting(TEST_END + STR_METHOD_NAME);
            fail();
        }
        
        log.exiting(TEST_END + STR_METHOD_NAME);
    }
    
    public void testGetTradeCloseTime_0002()
    {
        final String STR_METHOD_NAME = "testGetTradeCloseTime_0002()";
        log.entering(TEST_START + STR_METHOD_NAME);
        
        try
        {
            TestDBUtility.deleteAll(TradingTimeRow.TYPE);
            TradingTimeParams l_tradingTimeParams = TestDBUtility.getTradingTimeRow();
            l_tradingTimeParams.setBranchCode("123");
            l_tradingTimeParams.setMarketCode("N1");
            l_tradingTimeParams.setTradingTimeType("01");
            l_tradingTimeParams.setBizDateType("1");
            l_tradingTimeParams.setBizdateCalcParameter("0");
            l_tradingTimeParams.setSubmitMarketTrigger("1");
            l_tradingTimeParams.setStartTime("160000");
            l_tradingTimeParams.setEndTime("210000");
            TestDBUtility.insertWithDel(l_tradingTimeParams);
            l_tradingTimeParams.setStartTime("140000");
            l_tradingTimeParams.setEndTime("190000");
            TestDBUtility.insertWithDel(l_tradingTimeParams);
            
            String l_strMarketCode = "N1";
            String l_strProductCode = "0";
            
            String l_strTradeCloseTime =
                WEB3EquityPTSTradingTimeManagement.getTradeCloseTime(l_strMarketCode, l_strProductCode);
            
            assertEquals("210000", l_strTradeCloseTime);
        }
        catch (Exception l_ex)
        {
            log.error(STR_METHOD_NAME, l_ex);
            log.exiting(TEST_END + STR_METHOD_NAME);
            fail();
        }
        
        log.exiting(TEST_END + STR_METHOD_NAME);
    }
    
    public void testGetTradeCloseTime_0003()
    {
        final String STR_METHOD_NAME = "testGetTradeCloseTime_0003()";
        log.entering(TEST_START + STR_METHOD_NAME);
        
        try
        {
            TestDBUtility.deleteAll(TradingTimeRow.TYPE);
            
            String l_strMarketCode = "N1";
            String l_strProductCode = "0";
            
            String l_strTradeCloseTime =
                WEB3EquityPTSTradingTimeManagement.getTradeCloseTime(l_strMarketCode, l_strProductCode);
            
            assertEquals("235959", l_strTradeCloseTime);
        }
        catch (Exception l_ex)
        {
            log.error(STR_METHOD_NAME, l_ex);
            log.exiting(TEST_END + STR_METHOD_NAME);
            fail();
        }
        
        log.exiting(TEST_END + STR_METHOD_NAME);
    }


    /*
     * Test method for 'webbroker3.equity.WEB3EquityPTSTradingTimeManagement.getTradeCloseMarket(WEB3GentradeBranch, ProductTypeEnum, String)'
     */
    /**
     * getâcã∆ì˙ãÊï™()ÇÃñﬂÇËílÇ™ÅhîÒâcã∆ì˙ÅhÇÃèÍçáÅAnullÇï‘ãpÇµèàóùÇèIóπÇ∑ÇÈÅB
     */
    public void testGetTradeCloseMarket_0001()
    {
        final String STR_METHOD_NAME = "testGetTradeCloseMarket_0001()";
        log.entering(TEST_START + STR_METHOD_NAME);
        
        try
        {
            ThreadLocalSystemAttributesRegistry.setAttribute(
                    WEB3EquityPTSTradingTimeManagement.TIMESTAMP_TAG,
                    new Timestamp(WEB3DateUtility.getDate("20071223","yyyyMMdd").getTime()));
            
            TestDBUtility.deleteAll(BranchRow.TYPE);
            BranchParams l_branchParams = TestDBUtility.getBranchRow();
            TestDBUtility.insertWithDel(l_branchParams);
            
            TestDBUtility.deleteAll(TradingTimeRow.TYPE);
            TradingTimeParams l_tradingTimeParams = TestDBUtility.getTradingTimeRow();
            l_tradingTimeParams.setBranchCode("123");
            l_tradingTimeParams.setMarketCode("N1");
            l_tradingTimeParams.setTradingTimeType("01");
            l_tradingTimeParams.setBizDateType("0");
            l_tradingTimeParams.setBizdateCalcParameter("0");
            TestDBUtility.insertWithDel(l_tradingTimeParams);
            
            WEB3GentradeBranch l_branch = new WEB3GentradeBranch(33381);
            assertNull(WEB3EquityPTSTradingTimeManagement.getTradeCloseMarket(
                l_branch, ProductTypeEnum.EQUITY, "1"));
        }
        catch (Exception l_ex)
        {
            log.error(STR_METHOD_NAME, l_ex);
            log.exiting(TEST_END + STR_METHOD_NAME);
            fail();
        }
        
        log.exiting(TEST_END + STR_METHOD_NAME);
    }
    
    /**
     * getésèÍåxçêï∂ï\é¶()ÇÃñﬂÇËílÇ™ 0 ÇÃèÍçáÅAéÊà¯èIóπåxçêï∂åæÇï\é¶ÇµÇ»Ç¢Ç∆îªífÇµÅA
     *Å@@nullÇï‘ãpÇµèàóùÇèIóπÇ∑ÇÈÅB
     */
    public void testGetTradeCloseMarket_0002()
    {
        final String STR_METHOD_NAME = "testGetTradeCloseMarket_0002()";
        log.entering(TEST_START + STR_METHOD_NAME);
        
        try
        {
            
            TestDBUtility.deleteAll(BranchRow.TYPE);
            BranchParams l_branchParams = TestDBUtility.getBranchRow();
            l_branchParams.setCloseWorngEquityMargin(0);
            TestDBUtility.insertWithDel(l_branchParams);
            
            TestDBUtility.deleteAll(TradingTimeRow.TYPE);
            TradingTimeParams l_tradingTimeParams = TestDBUtility.getTradingTimeRow();
            l_tradingTimeParams.setBranchCode("123");
            l_tradingTimeParams.setMarketCode("N1");
            l_tradingTimeParams.setTradingTimeType("01");
            l_tradingTimeParams.setBizDateType("0");
            TestDBUtility.insertWithDel(l_tradingTimeParams);
            
            WEB3GentradeBranch l_branch = new WEB3GentradeBranch(33381);
            assertNull(WEB3EquityPTSTradingTimeManagement.getTradeCloseMarket(
                l_branch, ProductTypeEnum.EQUITY, "1"));
        }
        catch (Exception l_ex)
        {
            log.error(STR_METHOD_NAME, l_ex);
            log.exiting(TEST_END + STR_METHOD_NAME);
            fail();
        }
        
        log.exiting(TEST_END + STR_METHOD_NAME);
    }
    
    /**
     * for() i = 0
     *
     */
    public void testGetTradeCloseMarket_0003()
    {
        final String STR_METHOD_NAME = "testGetTradeCloseMarket_0003()";
        log.entering(TEST_START + STR_METHOD_NAME);
        
        try
        {
            
            TestDBUtility.deleteAll(BranchRow.TYPE);
            BranchParams l_branchParams = TestDBUtility.getBranchRow();
            TestDBUtility.insertWithDel(l_branchParams);
            
            TestDBUtility.deleteAll(TradingTimeRow.TYPE);
            TradingTimeParams l_tradingTimeParams = TestDBUtility.getTradingTimeRow();
            l_tradingTimeParams.setBranchCode("123");
            l_tradingTimeParams.setMarketCode("N1");
            l_tradingTimeParams.setTradingTimeType("01");
            l_tradingTimeParams.setBizDateType("0");
            TestDBUtility.insertWithDel(l_tradingTimeParams);
            
            ThreadLocalSystemAttributesRegistry.setAttribute(
                    WEB3EquityPTSTradingTimeManagement.TIMESTAMP_TAG,
                    new Timestamp(WEB3DateUtility.getDate("20071225155500", "yyyyMMddHHmmss").getTime()));
            
            WEB3GentradeBranch l_branch = new WEB3GentradeBranch(33381);
            String[] l_strTradeCloseMarkets =
                WEB3EquityPTSTradingTimeManagement.getTradeCloseMarket(l_branch, ProductTypeEnum.EQUITY, "1");
            
            assertEquals("0", l_strTradeCloseMarkets.length + "");
        }
        catch (Exception l_ex)
        {
            log.error(STR_METHOD_NAME, l_ex);
            log.exiting(TEST_END + STR_METHOD_NAME);
            fail();
        }
        
        log.exiting(TEST_END + STR_METHOD_NAME);
    }
    
    /**
     * for() i = 1
     *
     */
    public void testGetTradeCloseMarket_0004()
    {
        final String STR_METHOD_NAME = "testGetTradeCloseMarket_0004()";
        log.entering(TEST_START + STR_METHOD_NAME);
        
        try
        {
            TestDBUtility.deleteAll(BranchMarketPtsDealtCondRow.TYPE);
            BranchMarketPtsDealtCondParams l_branchMarketPtsDealtCondParams =
                TestDBUtility.getBranchMarketPtsDealtCondRow();
            l_branchMarketPtsDealtCondParams.setMartCanDealtEquity("1");
            TestDBUtility.insertWithDel(l_branchMarketPtsDealtCondParams);
            
            TestDBUtility.deleteAll(BranchRow.TYPE);
            BranchParams l_branchParams = TestDBUtility.getBranchRow();
            TestDBUtility.insertWithDel(l_branchParams);
            
            TestDBUtility.deleteAll(InstitutionRow.TYPE);
            InstitutionParams l_institutionParams = TestDBUtility.getInstitutionRow();
            TestDBUtility.insertWithDel(l_institutionParams);
            
            TestDBUtility.deleteAll(TradingTimeRow.TYPE);
            TradingTimeParams l_tradingTimeParams = TestDBUtility.getTradingTimeRow();
            l_tradingTimeParams.setBranchCode("123");
            l_tradingTimeParams.setMarketCode("11");
            l_tradingTimeParams.setTradingTimeType("01");
            l_tradingTimeParams.setBizDateType("1");
            l_tradingTimeParams.setBizdateCalcParameter("3");
            l_tradingTimeParams.setSubmitMarketTrigger("1");
            l_tradingTimeParams.setEndTime("160000");
            TestDBUtility.insertWithDel(l_tradingTimeParams);
            
            ThreadLocalSystemAttributesRegistry.setAttribute(
                WEB3EquityPTSTradingTimeManagement.TIMESTAMP_TAG,
                new Timestamp(WEB3DateUtility.getDate("20071225155500", "yyyyMMddHHmmss").getTime()));
            
            WEB3GentradeBranch l_branch = new WEB3GentradeBranch(33381);
            String[] l_strTradeCloseMarkets =
                WEB3EquityPTSTradingTimeManagement.getTradeCloseMarket(l_branch, ProductTypeEnum.EQUITY, "1");
            
            assertEquals("11", l_strTradeCloseMarkets[0]);
        }
        catch (Exception l_ex)
        {
            log.error(STR_METHOD_NAME, l_ex);
            log.exiting(TEST_END + STR_METHOD_NAME);
            fail();
        }
        
        log.exiting(TEST_END + STR_METHOD_NAME);
    }
    
    /**
     * for() i = 3
     *
     */
    public void testGetTradeCloseMarket_0005()
    {
        final String STR_METHOD_NAME = "testGetTradeCloseMarket_0005()";
        log.entering(TEST_START + STR_METHOD_NAME);
        
        try
        {
            TestDBUtility.deleteAll(BranchMarketPtsDealtCondRow.TYPE);
            BranchMarketPtsDealtCondParams l_branchMarketPtsDealtCondParams =
                TestDBUtility.getBranchMarketPtsDealtCondRow();
            l_branchMarketPtsDealtCondParams.setMarketCode("11");
            TestDBUtility.insertWithDel(l_branchMarketPtsDealtCondParams);
            l_branchMarketPtsDealtCondParams.setMarketCode("1");
            TestDBUtility.insertWithDel(l_branchMarketPtsDealtCondParams);
            l_branchMarketPtsDealtCondParams.setMarketCode("5");
            TestDBUtility.insertWithDel(l_branchMarketPtsDealtCondParams);
            
            TestDBUtility.deleteAll(BranchRow.TYPE);
            BranchParams l_branchParams = TestDBUtility.getBranchRow();
            TestDBUtility.insertWithDel(l_branchParams);
            
            TestDBUtility.deleteAll(TradingTimeRow.TYPE);
            TradingTimeParams l_tradingTimeParams = TestDBUtility.getTradingTimeRow();
            l_tradingTimeParams.setBranchCode("123");
            l_tradingTimeParams.setMarketCode("11");
            l_tradingTimeParams.setTradingTimeType("01");
            l_tradingTimeParams.setBizDateType("1");
            l_tradingTimeParams.setBizdateCalcParameter("3");
            l_tradingTimeParams.setSubmitMarketTrigger("1");
            l_tradingTimeParams.setEndTime("160000");
            TestDBUtility.insertWithDel(l_tradingTimeParams);
            
            ThreadLocalSystemAttributesRegistry.setAttribute(
                WEB3EquityPTSTradingTimeManagement.TIMESTAMP_TAG,
                new Timestamp(WEB3DateUtility.getDate("20071225155500", "yyyyMMddHHmmss").getTime()));
            
            WEB3GentradeBranch l_branch = new WEB3GentradeBranch(33381);
            String[] l_strTradeCloseMarkets =
                WEB3EquityPTSTradingTimeManagement.getTradeCloseMarket(l_branch, ProductTypeEnum.EQUITY, "1");
            
            assertEquals("0", l_strTradeCloseMarkets.length + "");
        }
        catch (Exception l_ex)
        {
            log.error(STR_METHOD_NAME, l_ex);
            log.exiting(TEST_END + STR_METHOD_NAME);
            fail();
        }
        
        log.exiting(TEST_END + STR_METHOD_NAME);
    }
    
    public void testGetTradeCloseMarket_0006()
    {
        final String STR_METHOD_NAME = "testGetTradeCloseMarket_0006()";
        log.entering(TEST_START + STR_METHOD_NAME);
        
        try
        {
            TestDBUtility.deleteAll(BranchMarketPtsDealtCondRow.TYPE);
            BranchMarketPtsDealtCondParams l_branchMarketPtsDealtCondParams =
                TestDBUtility.getBranchMarketPtsDealtCondRow();
            l_branchMarketPtsDealtCondParams.setMartCanDealtEquity("1");
            TestDBUtility.insertWithDel(l_branchMarketPtsDealtCondParams);
            
            TestDBUtility.deleteAll(BranchRow.TYPE);
            BranchParams l_branchParams = TestDBUtility.getBranchRow();
            TestDBUtility.insertWithDel(l_branchParams);
            
            TestDBUtility.deleteAll(TradingTimeRow.TYPE);
            
            ThreadLocalSystemAttributesRegistry.setAttribute(
                WEB3EquityPTSTradingTimeManagement.TIMESTAMP_TAG,
                new Timestamp(WEB3DateUtility.getDate("20071222155500", "yyyyMMddHHmmss").getTime()));
            
            WEB3GentradeBranch l_branch = new WEB3GentradeBranch(33381);
            String[] l_strTradeCloseMarkets =
                WEB3EquityPTSTradingTimeManagement.getTradeCloseMarket(l_branch, ProductTypeEnum.EQUITY, "1");
            
            assertEquals("0", l_strTradeCloseMarkets.length + "");
        }
        catch (Exception l_ex)
        {
            log.error(STR_METHOD_NAME, l_ex);
            log.exiting(TEST_END + STR_METHOD_NAME);
            fail();
        }
        
        log.exiting(TEST_END + STR_METHOD_NAME);
    }
    
    public void testGetTradeCloseMarket_0007()
    {
        final String STR_METHOD_NAME = "testGetTradeCloseMarket_0007()";
        log.entering(TEST_START + STR_METHOD_NAME);
        
        try
        {
            WEB3EquityPTSTradingTimeManagement.getTradeCloseMarket(null, ProductTypeEnum.EQUITY, "1");
        }
        catch (WEB3BaseException l_ex)
        {
            assertEquals(WEB3ErrorCatalog.SYSTEM_ERROR_80017, l_ex.getErrorInfo());
        }
        catch (Exception l_ex)
        {
            log.error(STR_METHOD_NAME, l_ex);
            log.exiting(TEST_END + STR_METHOD_NAME);
            fail();
        }
        
        log.exiting(TEST_END + STR_METHOD_NAME);
    }

    /*
     * Test method for 'webbroker3.equity.WEB3EquityPTSTradingTimeManagement.isOverNightExecute(String)'
     */
    public void testIsOverNightExecute_0001()
    {
        final String STR_METHOD_NAME = "testIsOverNightExecute_0001()";
        log.entering(TEST_START + STR_METHOD_NAME);
        
        try
        {
            TestDBUtility.deleteAll(TradingTimeRow.TYPE);
            TradingTimeParams l_tradingTimeParams = TestDBUtility.getTradingTimeRow();
            l_tradingTimeParams.setBranchCode("123");
            l_tradingTimeParams.setMarketCode("N1");
            l_tradingTimeParams.setTradingTimeType("01");
            l_tradingTimeParams.setBizDateType("1");
            l_tradingTimeParams.setBizdateCalcParameter("3");
            l_tradingTimeParams.setSubmitMarketTrigger("1");
            TestDBUtility.insertWithDel(l_tradingTimeParams);
            
            String l_strMarketCode = "N1";
            boolean l_blnResult =
                WEB3EquityPTSTradingTimeManagement.isOverNightExecute(l_strMarketCode);
            
            assertTrue(l_blnResult);
        }
        catch (Exception l_ex)
        {
            log.error(STR_METHOD_NAME, l_ex);
            log.exiting(TEST_END + STR_METHOD_NAME);
            fail();
        }
        
        log.exiting(TEST_END + STR_METHOD_NAME);
    }
    
    public void testIsOverNightExecute_0002()
    {
        final String STR_METHOD_NAME = "testIsOverNightExecute_0002()";
        log.entering(TEST_START + STR_METHOD_NAME);
        
        try
        {
            TestDBUtility.deleteAll(TradingTimeRow.TYPE);

            String l_strMarketCode = "N1";
            boolean l_blnResult =
                WEB3EquityPTSTradingTimeManagement.isOverNightExecute(l_strMarketCode);
            
            assertFalse(l_blnResult);
        }
        catch (Exception l_ex)
        {
            log.error(STR_METHOD_NAME, l_ex);
            log.exiting(TEST_END + STR_METHOD_NAME);
            fail();
        }
        
        log.exiting(TEST_END + STR_METHOD_NAME);
    }

}

@
