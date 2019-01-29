head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.04.07.02.36.49;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	5444d9d078c63f4;
filename	WEB3GentradeTradingTimeManagementForMockTest.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@package webbroker3.gentrade;

import java.sql.Timestamp;
import java.util.Calendar;
import java.util.Date;

import com.fitechlabs.xtrade.plugin.tc.gentrade.ProductTypeEnum;

import test.util.TestDBUtility;

import webbroker3.common.WEB3BaseException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.common.WEB3SystemLayerException;
import webbroker3.gentrade.data.ProductEstimationRatioDao;
import webbroker3.mock.TestBaseForMock;
import webbroker3.util.WEB3DateUtility;
import webbroker3.util.WEB3LogUtility;

public class WEB3GentradeTradingTimeManagementForMockTest extends TestBaseForMock
{

    /**
     * Logger
     */
   private static WEB3LogUtility log =
       WEB3LogUtility.getInstance(WEB3GentradeTradingTimeManagementForMockTest.class);
   
    public WEB3GentradeTradingTimeManagementForMockTest(String arg0)
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

    /*
     * Test method for 'webbroker3.gentrade.WEB3GentradeTradingTimeManagementForMock.mockGetBizDateType(Timestamp, String)'
     */
    public void testMockGetBizDateType()
    {
        final String STR_METHOD_NAME = "testMockGetBizDateType";
        log.entering(super.TEST_START + STR_METHOD_NAME);
        Timestamp l_bizDate = new Timestamp(Calendar.getInstance().getTime().getTime());
        
        try
        {
            WEB3GentradeTradingTimeManagementForMock.mockGetBizDateType(l_bizDate, "2");
            assertEquals("2", WEB3GentradeTradingTimeManagementForMock.getBizDateType(l_bizDate));
            
            WEB3GentradeTradingTimeManagementForMock.mockGetBizDateType(l_bizDate, "3");
            assertEquals("3", WEB3GentradeTradingTimeManagementForMock.getBizDateType(l_bizDate));
            
        } catch (WEB3BaseException l_ex)
        {
            log.error("", l_ex);
            fail();
        }
        log.exiting(super.TEST_END + STR_METHOD_NAME);
    }

    /*
     * Test method for 'webbroker3.gentrade.WEB3GentradeTradingTimeManagementForMock.mockGetFeqBizDateType(String, String, Timestamp, String)'
     */
    public void testMockGetFeqBizDateType()
    {
        final String STR_METHOD_NAME = "testMockGetFeqBizDateType";
        log.entering(super.TEST_START + STR_METHOD_NAME);
        
        Timestamp l_bizDate = new Timestamp(Calendar.getInstance().getTime().getTime());
        
        try
        {
            WEB3GentradeTradingTimeManagementForMock.mockGetFeqBizDateType("0D","00",l_bizDate,"2");
            assertEquals("2", WEB3GentradeTradingTimeManagementForMock.getFeqBizDateType("0D","00",l_bizDate));
            
            WEB3GentradeTradingTimeManagementForMock.mockGetFeqBizDateType("0D","00",l_bizDate,"3");
            assertEquals("3", WEB3GentradeTradingTimeManagementForMock.getFeqBizDateType("0D","00",l_bizDate));
            
            WEB3GentradeTradingTimeManagementForMock.mockGetFeqBizDateType(l_bizDate,"4");
            assertEquals("4", WEB3GentradeTradingTimeManagementForMock.getFeqBizDateType(l_bizDate));
            
            WEB3GentradeTradingTimeManagementForMock.mockGetFeqBizDateType(l_bizDate,"5");
            assertEquals("5", WEB3GentradeTradingTimeManagementForMock.getFeqBizDateType(l_bizDate));
            
        } 
        catch (WEB3BaseException l_ex)
        {
            log.error("", l_ex);
            fail();
        }
        log.exiting(super.TEST_END + STR_METHOD_NAME);
    }

    public void testMockGetTradeCloseTime()
    {
        final String STR_METHOD_NAME = "testMockGetTradeCloseTime";
        log.entering(super.TEST_START + STR_METHOD_NAME);
        try
        {
            WEB3GentradeTradingTimeManagementForMock.mockGetTradeCloseTime("N1","0","150001");
            assertEquals("150001", WEB3GentradeTradingTimeManagement.getTradeCloseTime("N1","0"));
        } 
        catch (WEB3BaseException l_ex)
        {
            log.error("", l_ex);
            fail();
        }
        log.exiting(super.TEST_END + STR_METHOD_NAME);
    }
    
    public void testMockGetTradeCloseFeqMarket()
    {
        final String STR_METHOD_NAME = "testMockGetTradeCloseFeqMarket";
        log.entering(super.TEST_START + STR_METHOD_NAME);
        try
        {
            TestDBUtility.insertWithDel(TestDBUtility.getBranchRow());
            
            WEB3GentradeBranch l_branch = new WEB3GentradeBranchForMock(33381);
            
            WEB3GentradeTradingTimeManagementForMock.mockGetTradeCloseFeqMarket(l_branch, new String[] {"01","02"});
            String[] l_tradeCloseFeqMarket = WEB3GentradeTradingTimeManagement.getTradeCloseFeqMarket(l_branch);
            
            assertEquals("01", l_tradeCloseFeqMarket[0]);
            assertEquals("02", l_tradeCloseFeqMarket[1]);
            
            WEB3GentradeTradingTimeManagementForMock.mockGetTradeCloseFeqMarket(l_branch, new String[] {"1","3","5"});
            l_tradeCloseFeqMarket = WEB3GentradeTradingTimeManagement.getTradeCloseFeqMarket(l_branch);
            
            assertEquals("1", l_tradeCloseFeqMarket[0]);
            assertEquals("3", l_tradeCloseFeqMarket[1]);
            assertEquals("5", l_tradeCloseFeqMarket[2]);
        } 
        catch (Exception l_ex)
        {
            log.error("", l_ex);
            fail();
        }
        log.exiting(super.TEST_END + STR_METHOD_NAME);
    }
    
    public void testMockGetOrderBizDate()
    {
        final String STR_METHOD_NAME = "testMockGetOrderBizDate";
        log.entering(super.TEST_START + STR_METHOD_NAME);
        try
        {
            Date l_datExpect = WEB3DateUtility.getDate("20061225","yyyyMMdd");
            WEB3GentradeTradingTimeManagementForMock.mockGetOrderBizDate(l_datExpect);
            assertEquals(l_datExpect, WEB3GentradeTradingTimeManagement.getOrderBizDate());
            
            l_datExpect = WEB3DateUtility.getDate("20061226","yyyyMMdd");
            WEB3GentradeTradingTimeManagementForMock.mockGetOrderBizDate(l_datExpect);
            assertEquals(l_datExpect, WEB3GentradeTradingTimeManagement.getOrderBizDate());
                                    
        }
        catch (Exception l_ex)
        {
            log.error("", l_ex);
            fail();
        }
        log.exiting(super.TEST_END + STR_METHOD_NAME);
    }
    
    public void testMockSetTimestamp()
    {
        final String STR_METHOD_NAME = "testMockSetTimestamp";
        log.entering(super.TEST_START + STR_METHOD_NAME);
        try
        {
            WEB3GentradeTradingTimeManagementForMock.mockSetTimestamp();
            WEB3GentradeTradingTimeManagement.setTimestamp();
        }
        catch (Exception l_ex)
        {
            log.error("", l_ex);
            fail();
        }
        log.exiting(super.TEST_END + STR_METHOD_NAME);
    }
    
    public void testSetClendarContext()
    {
        final String STR_METHOD_NAME = "testSetClendarContext";
        log.entering(TEST_START + STR_METHOD_NAME);
        WEB3GentradeTradingClendarContext l_clendarContext = 
            new WEB3GentradeTradingClendarContext();
        l_clendarContext.setInstitutionCode("0D");
        l_clendarContext.setBranchCode("381");
        l_clendarContext.setProductCode("N8080");
        l_clendarContext.setMarketCode("SP");
        l_clendarContext.setTradingTimeType("26");
        l_clendarContext.setBizDateType("1");
        WEB3GentradeTradingTimeManagementForMock.setClendarContext(l_clendarContext);
        boolean l_runFlag = false;
        if (!l_runFlag)
        {
            this.run();
            l_runFlag = true;
        }
        log.exiting(TEST_END + STR_METHOD_NAME);
    }
    
    public void testMockGetTradeCloseSuspensionMarketRepayment()
    {
        final String STR_METHOD_NAME = "testMockGetTradeCloseSuspensionMarketRepayment";
        log.entering(TEST_START + STR_METHOD_NAME);
        
        try
        {
            TestDBUtility.insertWithDel(TestDBUtility.getBranchRow());
            
            WEB3GentradeBranch l_branch = new WEB3GentradeBranchForMock(33381);
            
            WEB3GentradeTradingTimeManagementForMock.mockGetTradeCloseSuspensionMarketRepayment(
                l_branch, 
                "1",
                null);
            
            assertNull(WEB3GentradeTradingTimeManagementForMock.getTradeCloseSuspensionMarketRepayment(
                l_branch,
                "1"));
                
            WEB3GentradeTradingTimeManagementForMock.mockGetTradeCloseSuspensionMarketRepayment(
                l_branch, 
                "1", 
                new String[]{"01","02","03"});
            
            String[] l_strActualValue = 
                WEB3GentradeTradingTimeManagementForMock.getTradeCloseSuspensionMarketRepayment(
                    l_branch, 
                    "1");
            assertEquals("01",l_strActualValue[0]);
            assertEquals("02",l_strActualValue[1]);
            assertEquals("03",l_strActualValue[2]);
            
            log.exiting(TEST_END + STR_METHOD_NAME);
        }
        catch (Exception l_ex)
        {
            log.error("", l_ex);
            fail();
        }
    }
    
    public void testMockGetTradeCloseMarket()
    {
        final String STR_METHOD_NAME = "testMockGetTradeCloseMarket";
        log.entering(TEST_START + STR_METHOD_NAME);
        
        try
        {
            TestDBUtility.insertWithDel(TestDBUtility.getBranchRow());
            
            WEB3GentradeBranch l_branch = new WEB3GentradeBranchForMock(33381);
            
            WEB3GentradeTradingTimeManagementForMock.mockGetTradeCloseMarket(
                l_branch,
                ProductTypeEnum.EQUITY,
                "1",
                null);
            
            assertNull(WEB3GentradeTradingTimeManagementForMock.getTradeCloseMarket(
                l_branch,
                ProductTypeEnum.EQUITY,
                "1"));
                
            WEB3GentradeTradingTimeManagementForMock.mockGetTradeCloseMarket(
                l_branch,
                ProductTypeEnum.EQUITY,
                "1", 
                new String[]{"01","02","03"});
            
            String[] l_strActualValue = 
                WEB3GentradeTradingTimeManagementForMock.getTradeCloseSuspensionMarketRepayment(
                    l_branch, 
                    "1");
            assertEquals("01",l_strActualValue[0]);
            assertEquals("02",l_strActualValue[1]);
            assertEquals("03",l_strActualValue[2]);
            
            log.exiting(TEST_END + STR_METHOD_NAME);
        }
        catch (Exception l_ex)
        {
            log.error("", l_ex);
            fail();
        }
    }

    public void testMockIsTradeOpenTimeZone()
    {
        final String STR_METHOD_NAME = "testMockIsTradeOpenTimeZone";
        log.entering(TEST_START + STR_METHOD_NAME);

        try
        {
            WEB3GentradeTradingTimeManagementForMock.mockIsTradeOpenTimeZone(false);
            assertTrue(!WEB3GentradeTradingTimeManagement.isTradeOpenTimeZone());

            WEB3GentradeTradingTimeManagementForMock.mockIsTradeOpenTimeZone(true);
            assertTrue(WEB3GentradeTradingTimeManagement.isTradeOpenTimeZone());
        }
        catch (Exception l_ex)
        {
            log.error("", l_ex);
            fail();
        }
        log.exiting(TEST_END + STR_METHOD_NAME);
    }
    
    /**
     * case: pass
     *
     */
    public void testMockValidateTradeCloseChangeOrCancel1()
    {
        final String STR_METHOD_NAME = "testMockValidateTradeCloseChangeOrCancel1()";
        log.entering(TEST_START + STR_METHOD_NAME);
        try
        {
            WEB3GentradeTradingTimeManagementForMock.mockValidateTradeCloseChangeOrCancel(true);
            WEB3GentradeTradingTimeManagement.validateTradeCloseChangeOrCancel(
                    ProductTypeEnum.EQUITY, "futer");
            log.info(STR_METHOD_NAME + "------------------>ok");
        }
        catch (Exception l_ex)
        {
            log.error("", l_ex);
            fail();
        }
        log.exiting(TEST_END + STR_METHOD_NAME);
    }
    
    /**
     * case: not pass
     *
     */
    public void testMockValidateTradeCloseChangeOrCancel2()
    {
        final String STR_METHOD_NAME = "testMockValidateTradeCloseChangeOrCancel2()";
        log.entering(TEST_START + STR_METHOD_NAME);
        try
        {
            WEB3GentradeTradingTimeManagementForMock.mockValidateTradeCloseChangeOrCancel(false);
            WEB3GentradeTradingTimeManagement.validateTradeCloseChangeOrCancel(
                    ProductTypeEnum.EQUITY, "futer");
            fail();
        }
        catch(WEB3BaseException l_exc)
        {
            log.error("", l_exc);
            log.info(STR_METHOD_NAME + "------------------>ok");
        }
        catch (Exception l_ex)
        {
            log.error("", l_ex);
            fail();
        }
        log.exiting(TEST_END + STR_METHOD_NAME);
    }

    public void testMockIsTradeCloseTimeZone()
    {
        final String STR_METHOD_NAME = "testMockIsTradeCloseTimeZone";
        log.entering(TEST_START + STR_METHOD_NAME);

        try
        {
            WEB3GentradeTradingTimeManagementForMock.mockIsTradeCloseTimeZone(false);
            assertTrue(!WEB3GentradeTradingTimeManagement.isTradeCloseTimeZone());

            WEB3GentradeTradingTimeManagementForMock.mockIsTradeCloseTimeZone(true);
            assertTrue(WEB3GentradeTradingTimeManagement.isTradeCloseTimeZone());
        }
        catch (Exception l_ex)
        {
            log.error("", l_ex);
            fail();
        }
        log.exiting(TEST_END + STR_METHOD_NAME);
    }
   
    /**
     * case : pass
     *
     */
    public void testMockValidateOrderAccept_T01()
    {
        final String STR_METHOD_NAME = "testMockValidateOrderAccept_T01()";
        log.entering(TEST_START + STR_METHOD_NAME);

        try
        {
            WEB3GentradeTradingTimeManagementForMock.mockValidateOrderAccept(true);
            WEB3GentradeTradingTimeManagement.validateOrderAccept();
            log.info(STR_METHOD_NAME + "--------------->ok");
        }
        catch (Exception l_ex)
        {
            log.error("", l_ex);
            fail();
        }
        log.exiting(TEST_END + STR_METHOD_NAME);
    }
    
    /**
     * case : throws Exception
     *
     */
    public void testMockValidateOrderAccept_T02()
    {
        final String STR_METHOD_NAME = "testMockValidateOrderAccept_T02()";
        log.entering(TEST_START + STR_METHOD_NAME);

        try
        {
            WEB3GentradeTradingTimeManagementForMock.mockValidateOrderAccept(false);
            WEB3GentradeTradingTimeManagement.validateOrderAccept();
        }
        catch(WEB3SystemLayerException l_exc)
        {
            log.exiting(STR_METHOD_NAME);
            log.error("", l_exc);
            assertEquals(WEB3ErrorCatalog.SYSTEM_ERROR_80006, l_exc.getErrorInfo());
            log.info(STR_METHOD_NAME + "--------------->ok");
        }
        catch (Exception l_ex)
        {
            log.error("", l_ex);
            fail();
        }
        log.exiting(TEST_END + STR_METHOD_NAME);
    }
    
    /**
     *
     */
    public void testMock_C0001()
    {
       
        final String STR_METHOD_NAME = "testMock_C0001";
        log.entering(super.TEST_START + STR_METHOD_NAME);
        Timestamp l_bizDate = new Timestamp(Calendar.getInstance().getTime().getTime());
        
        try
        {
            WEB3GentradeTradingTimeManagementForMock.mockGetBizDateType(l_bizDate, "2");
            WEB3GentradeTradingTimeManagementForMock.mockIsTradeCloseTimeZone(false);            
            assertTrue(!WEB3GentradeTradingTimeManagement.isTradeCloseTimeZone());            
            assertEquals("2", WEB3GentradeTradingTimeManagementForMock.getBizDateType(l_bizDate));
            
            
        } catch (WEB3BaseException l_ex)
        {
            log.error("", l_ex);
            fail();
        }
        log.exiting(super.TEST_END + STR_METHOD_NAME);
    }
    
    public void testResetProductCode()
    {
        final String STR_METHOD_NAME = "testResetProductCode";
        log.entering(super.TEST_START + STR_METHOD_NAME);
        Timestamp l_bizDate = new Timestamp(Calendar.getInstance().getTime().getTime());
        try
        {
            WEB3GentradeTradingTimeManagementForMock.mockGetOrderBizDate(l_bizDate);                                    
            assertEquals(0, WEB3DateUtility.compareToDay(l_bizDate, WEB3GentradeTradingTimeManagement.getOrderBizDate()));
            

            WEB3GentradeTradingTimeManagement.resetProductCode("456");
            try
            {
                WEB3GentradeTradingTimeManagement.getOrderBizDate();
                fail();
            }
            catch (WEB3BaseException l_ex)
            {
                log.error("", l_ex);
            }            
            WEB3GentradeTradingTimeManagementForMock.resetProductCode("456");
            WEB3GentradeTradingTimeManagementForMock.mockGetOrderBizDate(l_bizDate);           
            WEB3GentradeTradingTimeManagement.resetProductCode("456");
            assertEquals(0, WEB3DateUtility.compareToDay(l_bizDate, WEB3GentradeTradingTimeManagement.getOrderBizDate()));
                        
            
            WEB3GentradeTradingTimeManagementForMock.resetProductCode("789");
            WEB3GentradeTradingTimeManagementForMock.mockGetOrderBizDate(l_bizDate); 
            WEB3GentradeTradingTimeManagement.resetProductCode("789");
            assertEquals(0, WEB3DateUtility.compareToDay(l_bizDate, WEB3GentradeTradingTimeManagement.getOrderBizDate()));           
        } 
        catch (WEB3BaseException l_ex)
        {
            log.error("", l_ex);
            fail();
        }
        log.exiting(super.TEST_END + STR_METHOD_NAME);
    }
    
    public void testResetMarketCode()
    {
        final String STR_METHOD_NAME = "testResetMarketCode";
        log.entering(super.TEST_START + STR_METHOD_NAME);
        Timestamp l_bizDate = new Timestamp(Calendar.getInstance().getTime().getTime());
        try
        {
            WEB3GentradeTradingTimeManagementForMock.mockGetOrderBizDate(l_bizDate);                                    
            assertEquals(0, WEB3DateUtility.compareToDay(l_bizDate, WEB3GentradeTradingTimeManagement.getOrderBizDate()));
            

            WEB3GentradeTradingTimeManagement.resetMarketCode("N0");
            try
            {
                WEB3GentradeTradingTimeManagement.getOrderBizDate();
                fail();
            }
            catch (WEB3BaseException l_ex)
            {
                log.error("", l_ex);
            }            
            WEB3GentradeTradingTimeManagementForMock.resetMarketCode("N0");
            WEB3GentradeTradingTimeManagementForMock.mockGetOrderBizDate(l_bizDate);           
            WEB3GentradeTradingTimeManagement.resetMarketCode("N0");
            assertEquals(0, WEB3DateUtility.compareToDay(l_bizDate, WEB3GentradeTradingTimeManagement.getOrderBizDate()));
                        
            
            WEB3GentradeTradingTimeManagementForMock.resetMarketCode("0K");
            WEB3GentradeTradingTimeManagementForMock.mockGetOrderBizDate(l_bizDate); 
            WEB3GentradeTradingTimeManagement.resetMarketCode("0K");
            assertEquals(0, WEB3DateUtility.compareToDay(l_bizDate, WEB3GentradeTradingTimeManagement.getOrderBizDate()));           
        } 
        catch (WEB3BaseException l_ex)
        {
            log.error("", l_ex);
            fail();
        }
        log.exiting(super.TEST_END + STR_METHOD_NAME);
    }
    
    public void testResetTradingTimeType()
    {
        final String STR_METHOD_NAME = "testResetTradingTimeType";
        log.entering(super.TEST_START + STR_METHOD_NAME);
        Timestamp l_bizDate = new Timestamp(Calendar.getInstance().getTime().getTime());
        try
        {
            WEB3GentradeTradingTimeManagementForMock.mockGetOrderBizDate(l_bizDate);                                    
            assertEquals(0, WEB3DateUtility.compareToDay(l_bizDate, WEB3GentradeTradingTimeManagement.getOrderBizDate()));
            

            WEB3GentradeTradingTimeManagement.resetTradingTimeType("3");
            try
            {
                WEB3GentradeTradingTimeManagement.getOrderBizDate();
                fail();
            }
            catch (WEB3BaseException l_ex)
            {
                log.error("", l_ex);
            }            
            WEB3GentradeTradingTimeManagementForMock.resetTradingTimeType("3");
            WEB3GentradeTradingTimeManagementForMock.mockGetOrderBizDate(l_bizDate);           
            WEB3GentradeTradingTimeManagement.resetTradingTimeType("3");
            assertEquals(0, WEB3DateUtility.compareToDay(l_bizDate, WEB3GentradeTradingTimeManagement.getOrderBizDate()));
                        
            
            WEB3GentradeTradingTimeManagementForMock.resetTradingTimeType("4");
            WEB3GentradeTradingTimeManagementForMock.mockGetOrderBizDate(l_bizDate); 
            WEB3GentradeTradingTimeManagement.resetTradingTimeType("4");
            assertEquals(0, WEB3DateUtility.compareToDay(l_bizDate, WEB3GentradeTradingTimeManagement.getOrderBizDate()));           
        } 
        catch (WEB3BaseException l_ex)
        {
            log.error("", l_ex);
            fail();
        }
        log.exiting(super.TEST_END + STR_METHOD_NAME);
    }
}
@
