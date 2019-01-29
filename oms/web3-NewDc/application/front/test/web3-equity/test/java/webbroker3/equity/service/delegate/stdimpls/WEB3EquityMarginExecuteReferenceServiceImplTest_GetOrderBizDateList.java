head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.04.07.00.57.28;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	5444d9d078c63f4;
filename	WEB3EquityMarginExecuteReferenceServiceImplTest_GetOrderBizDateList.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@package webbroker3.equity.service.delegate.stdimpls;

import java.sql.Timestamp;
import java.util.Calendar;
import java.util.Date;

import com.fitechlabs.xtrade.plugin.tc.eqtype.data.EqtypeOrderUnitParams;
import com.fitechlabs.xtrade.plugin.tc.gentrade.OrderCategEnum;
import com.fitechlabs.xtrade.plugin.tc.gentrade.ProductTypeEnum;

import test.util.TestDBUtility;

import webbroker3.gentrade.WEB3GentradeTradingClendarContext;
import webbroker3.gentrade.WEB3GentradeTradingTimeManagementForMock;
import webbroker3.mock.TestBaseForMock;
import webbroker3.util.WEB3DateUtility;
import webbroker3.util.WEB3LogUtility;

public class WEB3EquityMarginExecuteReferenceServiceImplTest_GetOrderBizDateList extends TestBaseForMock
{
    /**
     * ログ出力ユーティリティ。<BR>
     */
    private static WEB3LogUtility log =
        WEB3LogUtility.getInstance(WEB3EquityMarginExecuteReferenceServiceImplTest_GetOrderBizDateList.class);
    WEB3EquityMarginExecuteReferenceServiceImpl impl =
        new WEB3EquityMarginExecuteReferenceServiceImpl();

    public WEB3EquityMarginExecuteReferenceServiceImplTest_GetOrderBizDateList(String arg0)
    {
        super(arg0);
    }

    protected void setUp() throws Exception
    {
        super.setUp();
        TestBaseForMock.MOCK_MANAGER.setIsMockUsed(true);
    }

    protected void tearDown() throws Exception
    {
        super.tearDown();
        
    }
    
    /*
     * Test method for 'webbroker3.equity.service.delegate.stdimpls.WEB3EquityMarginExecuteReferenceServiceImpl.
     * GetOrderBizDateList(String[] l_strMarketCodeList)'
     */
    
    public void testGetOrderBizDateList_C0001()
    {
        final String STR_METHOD_NAME = "testGetOrderBizDateList_C0001()";
        log.entering(STR_METHOD_NAME);

        Date l_datExceptedValuePro = WEB3DateUtility.getDate("20061229","yyyyMMdd");
        Date l_datExceptedValue = WEB3DateUtility.getDate("20070101","yyyyMMdd");
        TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                "com.fitechlabs.xtrade.plugin.tc.gentrade.stdimpls.TradingSystemImpl", 
                "getBizDate",
                new Class[] {}, 
                l_datExceptedValue);
        
        try
        {
            String[] l_strMarketCodeList = new String[]{};
            assertEquals(2,impl.getOrderBizDateList(l_strMarketCodeList).length);
            assertEquals(0,WEB3DateUtility.compareToDay(
                    impl.getOrderBizDateList(l_strMarketCodeList)[0],l_datExceptedValuePro));
            assertEquals(0,WEB3DateUtility.compareToDay(
                    impl.getOrderBizDateList(l_strMarketCodeList)[1],l_datExceptedValue));
        }
        catch (Exception l_ex)
        {
            log.error(STR_METHOD_NAME, l_ex);
            fail();
        }

        log.exiting(TEST_END + STR_METHOD_NAME);
    }
    
    public void testGetOrderBizDateList_C0002()
    {
        final String STR_METHOD_NAME = "testGetOrderBizDateList_C0002()";
        log.entering(STR_METHOD_NAME);

        Date l_datExceptedValuePro = WEB3DateUtility.getDate("20061229","yyyyMMdd");
        Date l_datExceptedValue = WEB3DateUtility.getDate("20070101","yyyyMMdd");
        Date l_datExceptedValue1 = WEB3DateUtility.getDate("20070101","yyyyMMdd");
        TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                "com.fitechlabs.xtrade.plugin.tc.gentrade.stdimpls.TradingSystemImpl", 
                "getBizDate",
                new Class[] {}, 
                l_datExceptedValue);
        try
        {
            WEB3GentradeTradingClendarContext l_context =
                new WEB3GentradeTradingClendarContext();
            l_context.setInstitutionCode("0D");
            l_context.setBranchCode("123");
            l_context.setProductCode("0");
            l_context.setBizDateType("1");
            l_context.setMarketCode("N2");
            l_context.setTradingTimeType("01");
            WEB3GentradeTradingTimeManagementForMock.setClendarContext(l_context);
            
            WEB3GentradeTradingTimeManagementForMock.mockGetOrderBizDate(l_datExceptedValue1);

            String[] l_strMarketCodeList = {"N2"};
            assertEquals(2,impl.getOrderBizDateList(l_strMarketCodeList).length);
            assertEquals(0,WEB3DateUtility.compareToDay(
                    impl.getOrderBizDateList(l_strMarketCodeList)[0],l_datExceptedValuePro));
            assertEquals(0,WEB3DateUtility.compareToDay(
                    impl.getOrderBizDateList(l_strMarketCodeList)[1],l_datExceptedValue));
        }
        catch (Exception l_ex)
        {
            log.error(STR_METHOD_NAME, l_ex);
            fail();
        }

        log.exiting(TEST_END + STR_METHOD_NAME);
    }
    
    public void testGetOrderBizDateList_C0003()
    {
        final String STR_METHOD_NAME = "testGetOrderBizDateList_C0003()";
        log.entering(STR_METHOD_NAME);

        Date l_datExceptedValuePro = WEB3DateUtility.getDate("20061229","yyyyMMdd");
        Date l_datExceptedValue = WEB3DateUtility.getDate("20070101","yyyyMMdd");
        Date l_datExceptedValue1 = WEB3DateUtility.getDate("20061229","yyyyMMdd");
        Date l_datExceptedValue2 = WEB3DateUtility.getDate("20070101","yyyyMMdd");
        Date l_datExceptedValue3 = WEB3DateUtility.getDate("20070102","yyyyMMdd");
        TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                "com.fitechlabs.xtrade.plugin.tc.gentrade.stdimpls.TradingSystemImpl", 
                "getBizDate",
                new Class[] {}, 
                l_datExceptedValue);
        try
        {
            WEB3GentradeTradingClendarContext l_context =
                new WEB3GentradeTradingClendarContext();
            l_context.setInstitutionCode("0D");
            l_context.setBranchCode("123");
            l_context.setProductCode("0");
            l_context.setBizDateType("1");
            l_context.setMarketCode("SP");
            l_context.setTradingTimeType("01");
            WEB3GentradeTradingTimeManagementForMock.setClendarContext(l_context);
            WEB3GentradeTradingTimeManagementForMock.mockGetOrderBizDate(l_datExceptedValue1);
            
            WEB3GentradeTradingClendarContext l_context1 =
                new WEB3GentradeTradingClendarContext();
            l_context1.setInstitutionCode("0D");
            l_context1.setBranchCode("123");
            l_context1.setProductCode("0");
            l_context1.setBizDateType("1");
            l_context1.setMarketCode("N1");
            l_context1.setTradingTimeType("01");
            
            WEB3GentradeTradingTimeManagementForMock.setClendarContext(l_context1);
            WEB3GentradeTradingTimeManagementForMock.mockGetOrderBizDate(l_datExceptedValue2);
            
            WEB3GentradeTradingClendarContext l_context2 =
                new WEB3GentradeTradingClendarContext();
            l_context2.setInstitutionCode("0D");
            l_context2.setBranchCode("123");
            l_context2.setProductCode("0");
            l_context2.setBizDateType("1");
            l_context2.setMarketCode("N2");
            l_context2.setTradingTimeType("01");
            WEB3GentradeTradingTimeManagementForMock.setClendarContext(l_context2);
            WEB3GentradeTradingTimeManagementForMock.mockGetOrderBizDate(l_datExceptedValue3);

            String[] l_strMarketCodeList = {"SP","N1","N2"};
            assertEquals(3,impl.getOrderBizDateList(l_strMarketCodeList).length);
            assertEquals(0,WEB3DateUtility.compareToDay(
                    impl.getOrderBizDateList(l_strMarketCodeList)[0],l_datExceptedValuePro));
            assertEquals(0,WEB3DateUtility.compareToDay(
                    impl.getOrderBizDateList(l_strMarketCodeList)[1],l_datExceptedValue));
            assertEquals(0,WEB3DateUtility.compareToDay(
                    impl.getOrderBizDateList(l_strMarketCodeList)[2],l_datExceptedValue3));
        }
        catch (Exception l_ex)
        {
            log.error(STR_METHOD_NAME, l_ex);
            fail();
        }

        log.exiting(TEST_END + STR_METHOD_NAME);
    }
    

}
@
