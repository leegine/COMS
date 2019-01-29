head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.04.07.00.57.54;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	5444d9d078c63f4;
filename	WEB3IfoTradedProductImplTest.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : 先物OP取引銘柄オブジェクトTest(WEB3IfoTradedProductImplTest.java)
Author Name      : Daiwa Institute of Research
Revision History : 2007/06/14 張騰宇 (中訊) 新規作成
*/
package webbroker3.ifo;

import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import com.fitechlabs.xtrade.kernel.data.DataFindException;
import com.fitechlabs.xtrade.kernel.data.DataNetworkException;
import com.fitechlabs.xtrade.kernel.data.DataQueryException;
import com.fitechlabs.xtrade.kernel.util.ThreadLocalSystemAttributesRegistry;
import com.fitechlabs.xtrade.plugin.tc.gentrade.GtlUtils;
import com.fitechlabs.xtrade.plugin.tc.gentrade.ProductTypeEnum;
import com.fitechlabs.xtrade.plugin.tc.gentrade.data.MarketParams;
import com.fitechlabs.xtrade.plugin.tc.gentrade.data.MarketRow;
import com.fitechlabs.xtrade.plugin.tc.gentrade.data.ProductParams;
import com.fitechlabs.xtrade.plugin.tc.gentrade.data.ProductRow;
import com.fitechlabs.xtrade.plugin.tc.gentrade.data.TradedProductParams;
import com.fitechlabs.xtrade.plugin.tc.gentrade.data.TradedProductRow;
import com.fitechlabs.xtrade.plugin.tc.xbifo.data.IfoProductParams;
import com.fitechlabs.xtrade.plugin.tc.xbifo.data.IfoProductRow;
import com.fitechlabs.xtrade.plugin.tc.xbifo.data.IfoTradedProductParams;
import com.fitechlabs.xtrade.plugin.tc.xbifo.data.IfoTradedProductRow;
import com.fitechlabs.xtrade.plugin.tc.xbifo.data.IfoTradedProductUpdqParams;
import com.fitechlabs.xtrade.plugin.tc.xbifo.data.IfoTradedProductUpdqRow;

import test.util.TestDBUtility;

import webbroker3.common.WEB3BaseException;
import webbroker3.common.WEB3BaseRuntimeException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.gentrade.WEB3GentradeBizDate;
import webbroker3.gentrade.WEB3GentradeTradingClendarContext;
import webbroker3.gentrade.WEB3GentradeTradingTimeManagement;
import webbroker3.gentrade.WEB3GentradeTradingTimeManagementForMock;
import webbroker3.gentrade.data.TradingTimeParams;
import webbroker3.gentrade.data.TradingTimeRow;
import webbroker3.mock.TestBaseForMock;
import webbroker3.util.WEB3DateUtility;
import webbroker3.util.WEB3LogUtility;

public class WEB3IfoTradedProductImplTest extends TestBaseForMock
{
    /**
     * ログユーティリティ
     */
    private static WEB3LogUtility log = WEB3LogUtility.getInstance(WEB3IfoTradedProductImplTest.class);
    public WEB3IfoTradedProductImplTest(String arg0)
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
     * Test method for 'webbroker3.ifo.WEB3IfoTradedProductImpl.getDailyDeliveryDate()'
     */
    public void testGetDailyDeliveryDateCase1()
    {
        final String STR_METHOD_NAME = "testGetDailyDeliveryDateCase1()";
        log.entering(STR_METHOD_NAME);
        
        TradingTimeParams l_tradingTimeParams = TestDBUtility.getTradingTimeRow();
        l_tradingTimeParams.setInstitutionCode("0D");
        l_tradingTimeParams.setBranchCode("123");
        l_tradingTimeParams.setMarketCode("N1");
        l_tradingTimeParams.setTradingTimeType("01");
        l_tradingTimeParams.setProductCode("0");
        l_tradingTimeParams.setBizDateType("1");
        l_tradingTimeParams.setSessionType("1");
        
        IfoTradedProductParams l_ifoTradedProductParams =
            TestDBUtility.getIfoTradedProductRow();
        l_ifoTradedProductParams.setTradedProductId(1006160060005L);
        l_ifoTradedProductParams.setProductId(1006160060005L);
        l_ifoTradedProductParams.setMarketId(3303L);
        Calendar l_date =  Calendar.getInstance();
        l_date.add(Calendar.DATE, 1);
        Date l_dat = l_date.getTime();
        l_ifoTradedProductParams.setValidForBizDate(WEB3DateUtility.formatDate(l_dat, "yyyyMMdd"));
        
        IfoTradedProductUpdqParams l_ifoTradedProductUpdqParams = TestDBUtility.getIfoTradedProductUpdqRow();
        l_ifoTradedProductUpdqParams.setTradedProductId(1006160060005L);
        l_ifoTradedProductUpdqParams.setMarketId(3303L);
        l_ifoTradedProductUpdqParams.setProductId(1006160060005L);
        

        
        TradedProductParams l_tradedProductParams = TestDBUtility.getTradedProductRow();
        l_tradedProductParams.setProductId(1006160060005L);
        l_tradedProductParams.setMarketId(3303L);
        l_tradedProductParams.setTradedProductId(1006160060005L);
        l_tradedProductParams.setDailyDeliveryDate(WEB3DateUtility.getDate("20070617","yyyyMMdd"));//friday
        
        IfoProductParams l_ifoProductParams = TestDBUtility.getIfoProductRow();
        l_ifoProductParams.setProductId(1006160060005L);
        l_ifoProductParams.setTargetMarketId(3303L);
        l_ifoProductParams.setInstitutionCode("0D");
        
        ProductParams l_productParams = TestDBUtility.getProductRow();
        l_productParams.setProductId(1006160060005L);
        
        MarketParams l_marketParams = TestDBUtility.getMarketRow();
        l_marketParams.setMarketId(3303L);
        try
        {
            TestDBUtility.deleteAll(ProductRow.TYPE);
            TestDBUtility.insertWithDel(l_productParams);
            
            TestDBUtility.deleteAll(IfoTradedProductUpdqRow.TYPE);
            
            
            SimpleDateFormat l_format = GtlUtils.getThreadSafeSimpleDateFormat("yyyyMMdd");
            Date l_datBizDate = GtlUtils.getTradingSystem().getSystemTimestamp();
            Date l_datpreBizDate = new WEB3GentradeBizDate(
                new Timestamp(l_datBizDate.getTime())).roll(1);
            String l_strCreateDate = l_format.format(l_datpreBizDate);
            
            l_ifoTradedProductUpdqParams.setValidForBizDate(l_strCreateDate);
            
            TestDBUtility.insertWithDel(l_ifoTradedProductUpdqParams);
            
            TestDBUtility.deleteAll(IfoProductRow.TYPE);
            TestDBUtility.insertWithDel(l_ifoProductParams);
            
            TestDBUtility.deleteAll(MarketRow.TYPE);
            TestDBUtility.insertWithDel(l_marketParams);
            
            TestDBUtility.deleteAll(IfoTradedProductRow.TYPE);
            TestDBUtility.insertWithDel(l_ifoTradedProductParams);
            
            TestDBUtility.deleteAll(TradedProductRow.TYPE);
            TestDBUtility.insertWithDel(l_tradedProductParams);
            
            TestDBUtility.deleteAll(TradingTimeRow.TYPE);
            TestDBUtility.insertWithDel(l_tradingTimeParams);
        }
        catch (Exception l_ex)
        {
            log.error(l_ex.getMessage(), l_ex);
            fail();
        }
        try
        {
            WEB3IfoTradedProductImpl l_tradedProductImpl = new WEB3IfoTradedProductImpl(1006160060005L);
            Date l_datDeliveryDate = l_tradedProductImpl.getDailyDeliveryDate();
            assertEquals("20070618", WEB3DateUtility.formatDate(l_datDeliveryDate, "yyyyMMdd"));
        }
        catch (Exception l_ex)
        {
            log.error(l_ex.getMessage(), l_ex);
            fail();
        }
        log.exiting(STR_METHOD_NAME);
    }
    
    //取引時間管理.is夕場時間帯() == falseの場合
    public void testGetDailyDeliveryDateCase2()
    {
        final String STR_METHOD_NAME = "testGetDailyDeliveryDateCase2()";
        log.entering(STR_METHOD_NAME);
        
        TradingTimeParams l_tradingTimeParams = TestDBUtility.getTradingTimeRow();
        l_tradingTimeParams.setInstitutionCode("0D");
        l_tradingTimeParams.setBranchCode("123");
        l_tradingTimeParams.setMarketCode("N1");
        l_tradingTimeParams.setTradingTimeType("01");
        l_tradingTimeParams.setProductCode("0");
        l_tradingTimeParams.setBizDateType("1");
        l_tradingTimeParams.setSessionType(null);
        
        
        IfoTradedProductParams l_ifoTradedProductParams =
            TestDBUtility.getIfoTradedProductRow();
        l_ifoTradedProductParams.setTradedProductId(1006160060005L);
        l_ifoTradedProductParams.setProductId(1006160060005L);
        l_ifoTradedProductParams.setMarketId(3303L);
        Calendar l_date =  Calendar.getInstance();
        l_date.add(Calendar.DATE, 1);
        Date l_dat = l_date.getTime();
        l_ifoTradedProductParams.setValidForBizDate(WEB3DateUtility.formatDate(l_dat, "yyyyMMdd"));
        
        TradedProductParams l_tradedProductParams = TestDBUtility.getTradedProductRow();
        l_tradedProductParams.setProductId(1006160060005L);
        l_tradedProductParams.setMarketId(3303L);
        l_tradedProductParams.setTradedProductId(1006160060005L);
        l_tradedProductParams.setDailyDeliveryDate(WEB3DateUtility.getDate("20070615","yyyyMMdd"));//friday
        
        IfoProductParams l_ifoProductParams = TestDBUtility.getIfoProductRow();
        l_ifoProductParams.setProductId(1006160060005L);
        l_ifoProductParams.setTargetMarketId(3303L);
        l_ifoProductParams.setInstitutionCode("0D");
        
        ProductParams l_productParams = TestDBUtility.getProductRow();
        l_productParams.setProductId(1006160060005L);
        
        MarketParams l_marketParams = TestDBUtility.getMarketRow();
        l_marketParams.setMarketId(3303L);
        
        IfoTradedProductUpdqParams l_ifoTradedProductUpdqParams = TestDBUtility.getIfoTradedProductUpdqRow();
        l_ifoTradedProductUpdqParams.setTradedProductId(1006160060005L);
        l_ifoTradedProductUpdqParams.setMarketId(3303L);
        l_ifoTradedProductUpdqParams.setProductId(1006160060005L);
        try
        {
            TestDBUtility.deleteAll(IfoTradedProductUpdqRow.TYPE);
            
            SimpleDateFormat l_format = GtlUtils.getThreadSafeSimpleDateFormat("yyyyMMdd");
            
            Date l_datBizDate = GtlUtils.getTradingSystem().getSystemTimestamp();
            Date l_datpreBizDate = new WEB3GentradeBizDate(
                new Timestamp(l_datBizDate.getTime())).roll(1);
            String l_strCreateDate = l_format.format(l_datpreBizDate);
            
            l_ifoTradedProductUpdqParams.setValidForBizDate(l_strCreateDate);
            
            TestDBUtility.insertWithDel(l_ifoTradedProductUpdqParams);
            
            TestDBUtility.deleteAll(ProductRow.TYPE);
            TestDBUtility.insertWithDel(l_productParams);
            
            TestDBUtility.deleteAll(IfoProductRow.TYPE);
            TestDBUtility.insertWithDel(l_ifoProductParams);
            
            TestDBUtility.deleteAll(MarketRow.TYPE);
            TestDBUtility.insertWithDel(l_marketParams);
            
            TestDBUtility.deleteAll(IfoTradedProductRow.TYPE);
            TestDBUtility.insertWithDel(l_ifoTradedProductParams);
            
            TestDBUtility.deleteAll(TradedProductRow.TYPE);
            TestDBUtility.insertWithDel(l_tradedProductParams);
            
            TestDBUtility.deleteAll(TradingTimeRow.TYPE);
            TestDBUtility.insertWithDel(l_tradingTimeParams);
        }
        catch (Exception l_ex)
        {
            log.error(l_ex.getMessage(), l_ex);
            fail();
        }
        try
        {
            WEB3IfoTradedProductImpl l_tradedProductImpl = new WEB3IfoTradedProductImpl(1006160060005L);
            Date l_datDeliveryDate = l_tradedProductImpl.getDailyDeliveryDate();
            assertEquals("20070615", WEB3DateUtility.formatDate(l_datDeliveryDate, "yyyyMMdd"));
        }
        catch (Exception l_ex)
        {
            log.error(l_ex.getMessage(), l_ex);
            fail();
        }
        log.exiting(STR_METHOD_NAME);
    }
    
    public void testGetDailyDeliveryDateCase3()
    {
        final String STR_METHOD_NAME = "testGetDailyDeliveryDateCase3()";
        log.entering(STR_METHOD_NAME);
        
        IfoTradedProductParams l_ifoTradedProductParams =
            TestDBUtility.getIfoTradedProductRow();
        l_ifoTradedProductParams.setTradedProductId(1006160060005L);
        l_ifoTradedProductParams.setProductId(1006160060005L);
        l_ifoTradedProductParams.setMarketId(3303L);
        Calendar l_date =  Calendar.getInstance();
        l_date.add(Calendar.DATE, 1);
        Date l_dat = l_date.getTime();
        l_ifoTradedProductParams.setValidForBizDate(WEB3DateUtility.formatDate(l_dat, "yyyyMMdd"));
        
        TradedProductParams l_tradedProductParams = TestDBUtility.getTradedProductRow();
        l_tradedProductParams.setProductId(1006160060005L);
        l_tradedProductParams.setMarketId(3303L);
        l_tradedProductParams.setTradedProductId(1006160060005L);
        l_tradedProductParams.setDailyDeliveryDate(WEB3DateUtility.getDate("20070615","yyyyMMdd"));//friday
        
        IfoProductParams l_ifoProductParams = TestDBUtility.getIfoProductRow();
        l_ifoProductParams.setProductId(1006160060005L);
        l_ifoProductParams.setTargetMarketId(3303L);
        l_ifoProductParams.setInstitutionCode("0D");
        
        ProductParams l_productParams = TestDBUtility.getProductRow();
        l_productParams.setProductId(1006160060005L);
        
        MarketParams l_marketParams = TestDBUtility.getMarketRow();
        l_marketParams.setMarketId(3303L);
        
        IfoTradedProductUpdqParams l_ifoTradedProductUpdqParams = TestDBUtility.getIfoTradedProductUpdqRow();
        l_ifoTradedProductUpdqParams.setTradedProductId(1006160060005L);
        l_ifoTradedProductUpdqParams.setMarketId(3303L);
        l_ifoTradedProductUpdqParams.setProductId(1006160060005L);
        try
        {
            TestDBUtility.deleteAll(IfoTradedProductUpdqRow.TYPE);
            
            SimpleDateFormat l_format = GtlUtils.getThreadSafeSimpleDateFormat("yyyyMMdd");
            
            Date l_datBizDate = GtlUtils.getTradingSystem().getSystemTimestamp();
            Date l_datpreBizDate = new WEB3GentradeBizDate(
                new Timestamp(l_datBizDate.getTime())).roll(1);
            String l_strCreateDate = l_format.format(l_datpreBizDate);
            
            l_ifoTradedProductUpdqParams.setValidForBizDate(l_strCreateDate);
            
            TestDBUtility.insertWithDel(l_ifoTradedProductUpdqParams);
            
            TestDBUtility.deleteAll(ProductRow.TYPE);
            TestDBUtility.insertWithDel(l_productParams);
            
            TestDBUtility.deleteAll(IfoProductRow.TYPE);
            TestDBUtility.insertWithDel(l_ifoProductParams);
            
            TestDBUtility.deleteAll(MarketRow.TYPE);
            TestDBUtility.insertWithDel(l_marketParams);
            
            TestDBUtility.deleteAll(IfoTradedProductRow.TYPE);
            TestDBUtility.insertWithDel(l_ifoTradedProductParams);
            
            TestDBUtility.deleteAll(IfoTradedProductRow.TYPE);
            TestDBUtility.insertWithDel(l_ifoTradedProductParams);
            
            TestDBUtility.deleteAll(TradedProductRow.TYPE);
            TestDBUtility.insertWithDel(l_tradedProductParams);
            
            TestDBUtility.deleteAll(TradingTimeRow.TYPE);
        }
        catch (Exception l_ex)
        {
            log.error(l_ex.getMessage(), l_ex);
            fail();
        }
        try
        {
            WEB3IfoTradedProductImpl l_tradedProductImpl = new WEB3IfoTradedProductImpl(1006160060005L);
            l_tradedProductImpl.getDailyDeliveryDate();
            fail();
        }
        catch (WEB3BaseRuntimeException l_ex)
        {
            log.error(l_ex.getMessage(), l_ex);
            assertEquals(l_ex.getErrorInfo(), WEB3ErrorCatalog.SYSTEM_ERROR_80006);
        } 
        catch (Exception l_ex)
        {
            log.error(l_ex.getMessage(), l_ex);
            fail();
        }
        log.exiting(STR_METHOD_NAME);
    }
    
    public void testGetIfoStopHighPriceCase0001()
    {
        final String STR_METHOD_NAME = "testGetIfoStopHighPriceCase0001()";
        log.entering(STR_METHOD_NAME);

        //夕場時間帯（取引時間管理.is夕場時間帯 ()＝true）の場合
        TradingTimeParams l_tradingTimeParams = TestDBUtility.getTradingTimeRow();
        l_tradingTimeParams.setInstitutionCode("0D");
        l_tradingTimeParams.setBranchCode("123");
        l_tradingTimeParams.setMarketCode("N1");
        l_tradingTimeParams.setTradingTimeType("01");
        l_tradingTimeParams.setProductCode("0");
        l_tradingTimeParams.setBizDateType("1");
        l_tradingTimeParams.setSessionType("1");
        
        IfoTradedProductParams l_ifoTradedProductParams =
            TestDBUtility.getIfoTradedProductRow();
        l_ifoTradedProductParams.setTradedProductId(1006160060009L);
        l_ifoTradedProductParams.setProductId(1006160060005L);
        l_ifoTradedProductParams.setMarketId(3303L);
        l_ifoTradedProductParams.setValidForBizDate("20070626");
        //値幅の上限
        l_ifoTradedProductParams.setStopHighPrice(100);
        
        TradedProductParams l_tradedProductParams =
            TestDBUtility.getTradedProductRow();
        l_tradedProductParams.setProductId(1006160060005L);
        l_tradedProductParams.setMarketId(3303L);
        l_tradedProductParams.setTradedProductId(1006160060009L);
        
        IfoProductParams l_ifoProductParams =
            TestDBUtility.getIfoProductRow();
        l_ifoProductParams.setProductId(1006160060005L);
        l_ifoProductParams.setTargetMarketId(3303L);
        l_ifoProductParams.setInstitutionCode("0D");

        IfoTradedProductUpdqParams l_ifoTradedProductUpdqParams =
            TestDBUtility.getIfoTradedProductUpdqRow();
        //取引銘柄ＩＤ
        l_ifoTradedProductUpdqParams.setTradedProductId(1006160060009L);
        l_ifoTradedProductUpdqParams.setMarketId(3303L);
        //有効日の翌営業日
        l_ifoTradedProductUpdqParams.setValidForBizDate("20070627");
        l_ifoTradedProductUpdqParams.setProductId(1006160060005L);
        //値幅の上限
        l_ifoTradedProductUpdqParams.setStopHighPrice(200);

        Calendar ca =  Calendar.getInstance();
        ca.set(2007,6-1,26);
        ca.set(Calendar.HOUR_OF_DAY, 0);
        ca.set(Calendar.MINUTE, 0);
        ca.set(Calendar.SECOND, 0);
        ca.set(Calendar.MILLISECOND, 0);
        Date date = ca.getTime();
                     
        TestBaseForMock.MOCK_MANAGER.setIsMockUsed(true);
        
        TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
            "com.fitechlabs.xtrade.plugin.tc.gentrade.stdimpls.TradingSystemImpl",
            "getBizDate",
            new Class[] {},
            new Timestamp(WEB3DateUtility.getDate("20070721", "yyyyMMdd").getTime()));
        
        TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                "webbroker3.gentrade.WEB3GentradeTradingCalendarModelImpl",
                "getCurrentBizDate",
                new Class[] {long.class},
                new Timestamp(WEB3DateUtility.getDate("20070626", "yyyyMMdd").getTime()));
        
        try
        {
            TestDBUtility.deleteAll(TradedProductRow.TYPE);
            TestDBUtility.insertWithDel(l_ifoTradedProductParams);
            
            TestDBUtility.deleteAll(TradedProductRow.TYPE);
            TestDBUtility.insertWithDel(l_tradedProductParams);
            
            TestDBUtility.deleteAll(IfoProductRow.TYPE);
            TestDBUtility.insertWithDel(l_ifoProductParams);
            
            WEB3GentradeTradingTimeManagementForMock.mockGetBizDateType(
                new Timestamp(date.getTime()),
                l_tradingTimeParams.getBizDateType());
            TestDBUtility.deleteAll(TradingTimeRow.TYPE);
            TestDBUtility.insertWithDel(l_tradingTimeParams);
            ThreadLocalSystemAttributesRegistry.setAttribute(
                WEB3GentradeTradingTimeManagement.BASE_TIMESTAMP_FOR_ORDER_BIZ_DATE,
                new Timestamp(date.getTime()));  

            TestDBUtility.deleteAll(IfoTradedProductUpdqRow.TYPE);
            TestDBUtility.insertWithDel(l_ifoTradedProductUpdqParams);
            
        }
        catch (WEB3BaseException l_ex)
        {
            log.error(l_ex.getMessage(), l_ex);
            fail();
        }
        
        try
        {
            WEB3IfoTradedProductImpl l_tradedProductImpl =
                new WEB3IfoTradedProductImpl(1006160060009L);
            assertEquals("200.0",
                "" + l_tradedProductImpl.getIfoStopHighPrice());
        }
        catch (WEB3BaseException l_ex)
        {
            log.error(l_ex.getMessage(), l_ex);
            fail();
        }
        catch (DataFindException l_ex)
        {
            log.error(l_ex.getMessage(), l_ex);
            fail();
        }
        catch (DataQueryException l_ex)
        {
            log.error(l_ex.getMessage(), l_ex);
            fail();
        }
        catch (DataNetworkException l_ex)
        {
            log.error(l_ex.getMessage(), l_ex);
            fail();
        }
        
        log.exiting(STR_METHOD_NAME);
    }

    public void testGetIfoStopHighPriceCase0002()
    {
        final String STR_METHOD_NAME = "testGetIfoStopHighPriceCase0002()";
        log.entering(STR_METHOD_NAME);

        //夕場時間帯（取引時間管理.is夕場時間帯 ()＝false）の場合
        TradingTimeParams l_tradingTimeParams = TestDBUtility.getTradingTimeRow();
        l_tradingTimeParams.setInstitutionCode("0D");
        l_tradingTimeParams.setBranchCode("123");
        l_tradingTimeParams.setMarketCode("N1");
        l_tradingTimeParams.setTradingTimeType("01");
        l_tradingTimeParams.setProductCode("0");
        l_tradingTimeParams.setBizDateType("1");
        l_tradingTimeParams.setSessionType(null);
        
        IfoTradedProductParams l_ifoTradedProductParams =
            TestDBUtility.getIfoTradedProductRow();
        l_ifoTradedProductParams.setTradedProductId(1006160060009L);
        l_ifoTradedProductParams.setProductId(1006160060005L);
        l_ifoTradedProductParams.setMarketId(3303L);
        l_ifoTradedProductParams.setValidForBizDate("20070626");
        //値幅の上限
        l_ifoTradedProductParams.setStopHighPrice(100);
        
        TradedProductParams l_tradedProductParams =
            TestDBUtility.getTradedProductRow();
        l_tradedProductParams.setProductId(1006160060005L);
        l_tradedProductParams.setMarketId(3303L);
        l_tradedProductParams.setTradedProductId(1006160060009L);
        
        IfoProductParams l_ifoProductParams =
            TestDBUtility.getIfoProductRow();
        l_ifoProductParams.setProductId(1006160060005L);
        l_ifoProductParams.setTargetMarketId(3303L);
        l_ifoProductParams.setInstitutionCode("0D");

        IfoTradedProductUpdqParams l_ifoTradedProductUpdqParams =
            TestDBUtility.getIfoTradedProductUpdqRow();
        //取引銘柄ＩＤ
        l_ifoTradedProductUpdqParams.setTradedProductId(1006160060009L);
        l_ifoTradedProductUpdqParams.setMarketId(3303L);
        //有効日の翌営業日
        l_ifoTradedProductUpdqParams.setValidForBizDate("20070626");
        l_ifoTradedProductUpdqParams.setProductId(1006160060005L);
        //値幅の上限
        l_ifoTradedProductUpdqParams.setStopHighPrice(200);

        Calendar ca =  Calendar.getInstance();
        ca.set(2007,6-1,26);
        ca.set(Calendar.HOUR_OF_DAY, 0);
        ca.set(Calendar.MINUTE, 0);
        ca.set(Calendar.SECOND, 0);
        ca.set(Calendar.MILLISECOND, 0);
        Date date = ca.getTime();
                     
        TestBaseForMock.MOCK_MANAGER.setIsMockUsed(true);
        
        TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
            "com.fitechlabs.xtrade.plugin.tc.gentrade.stdimpls.TradingSystemImpl",
            "getBizDate",
            new Class[] {},
            new Timestamp(WEB3DateUtility.getDate("20070721", "yyyyMMdd").getTime()));
        
        TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
            "webbroker3.gentrade.WEB3GentradeTradingCalendarModelImpl",
            "getCurrentBizDate",
            new Class[] {long.class},
            new Timestamp(WEB3DateUtility.getDate("20070626", "yyyyMMdd").getTime()));
        
        try
        {
            TestDBUtility.deleteAll(TradedProductRow.TYPE);
            TestDBUtility.insertWithDel(l_ifoTradedProductParams);
            
            TestDBUtility.deleteAll(TradedProductRow.TYPE);
            TestDBUtility.insertWithDel(l_tradedProductParams);
            
            TestDBUtility.deleteAll(IfoProductRow.TYPE);
            TestDBUtility.insertWithDel(l_ifoProductParams);
            
            WEB3GentradeTradingTimeManagementForMock.mockGetBizDateType(
                new Timestamp(date.getTime()),
                l_tradingTimeParams.getBizDateType());
            TestDBUtility.deleteAll(TradingTimeRow.TYPE);
            TestDBUtility.insertWithDel(l_tradingTimeParams);
            ThreadLocalSystemAttributesRegistry.setAttribute(
                WEB3GentradeTradingTimeManagement.BASE_TIMESTAMP_FOR_ORDER_BIZ_DATE,
                new Timestamp(date.getTime()));  

            TestDBUtility.deleteAll(IfoTradedProductUpdqRow.TYPE);
            TestDBUtility.insertWithDel(l_ifoTradedProductUpdqParams);
            
        }
        catch (WEB3BaseException l_ex)
        {
            log.error(l_ex.getMessage(), l_ex);
            fail();
        }
        
        try
        {
            WEB3IfoTradedProductImpl l_tradedProductImpl =
                new WEB3IfoTradedProductImpl(1006160060009L);
            assertEquals("100.0",
                "" + l_tradedProductImpl.getIfoStopHighPrice());
        }
        catch (WEB3BaseException l_ex)
        {
            log.error(l_ex.getMessage(), l_ex);
            fail();
        }
        catch (DataFindException l_ex)
        {
            log.error(l_ex.getMessage(), l_ex);
            fail();
        }
        catch (DataQueryException l_ex)
        {
            log.error(l_ex.getMessage(), l_ex);
            fail();
        }
        catch (DataNetworkException l_ex)
        {
            log.error(l_ex.getMessage(), l_ex);
            fail();
        }
        
        log.exiting(STR_METHOD_NAME);
    }
    
    public void testGetIfoStopLowPriceCase0001()
    {
        final String STR_METHOD_NAME = "testGetIfoStopLowPriceCase0001()";
        log.entering(STR_METHOD_NAME);
        

        //夕場時間帯（取引時間管理.is夕場時間帯 ()＝true）の場合
        TradingTimeParams l_tradingTimeParams = TestDBUtility.getTradingTimeRow();
        l_tradingTimeParams.setInstitutionCode("0D");
        l_tradingTimeParams.setBranchCode("123");
        l_tradingTimeParams.setMarketCode("N1");
        l_tradingTimeParams.setTradingTimeType("01");
        l_tradingTimeParams.setProductCode("0");
        l_tradingTimeParams.setBizDateType("1");
        l_tradingTimeParams.setSessionType("1");
        
        IfoTradedProductParams l_ifoTradedProductParams =
            TestDBUtility.getIfoTradedProductRow();
        l_ifoTradedProductParams.setTradedProductId(1006160060009L);
        l_ifoTradedProductParams.setProductId(1006160060005L);
        l_ifoTradedProductParams.setMarketId(3303L);
        l_ifoTradedProductParams.setValidForBizDate("20070626");
        //値幅の下限
        l_ifoTradedProductParams.setStopLowPrice(100);
        
        TradedProductParams l_tradedProductParams =
            TestDBUtility.getTradedProductRow();
        l_tradedProductParams.setProductId(1006160060005L);
        l_tradedProductParams.setMarketId(3303L);
        l_tradedProductParams.setTradedProductId(1006160060009L);
        
        IfoProductParams l_ifoProductParams =
            TestDBUtility.getIfoProductRow();
        l_ifoProductParams.setProductId(1006160060005L);
        l_ifoProductParams.setTargetMarketId(3303L);
        l_ifoProductParams.setInstitutionCode("0D");

        IfoTradedProductUpdqParams l_ifoTradedProductUpdqParams =
            TestDBUtility.getIfoTradedProductUpdqRow();
        //取引銘柄ＩＤ
        l_ifoTradedProductUpdqParams.setTradedProductId(1006160060009L);
        l_ifoTradedProductUpdqParams.setMarketId(3303L);
        //有効日の翌営業日
        l_ifoTradedProductUpdqParams.setValidForBizDate("20070627");
        l_ifoTradedProductUpdqParams.setProductId(1006160060005L);
        //値幅の下限
        l_ifoTradedProductUpdqParams.setStopLowPrice(200);

        Calendar ca =  Calendar.getInstance();
        ca.set(2007,6-1,26);
        ca.set(Calendar.HOUR_OF_DAY, 0);
        ca.set(Calendar.MINUTE, 0);
        ca.set(Calendar.SECOND, 0);
        ca.set(Calendar.MILLISECOND, 0);
        Date date = ca.getTime();
                     
        TestBaseForMock.MOCK_MANAGER.setIsMockUsed(true);
        
        TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
            "com.fitechlabs.xtrade.plugin.tc.gentrade.stdimpls.TradingSystemImpl",
            "getBizDate",
            new Class[] {},
            new Timestamp(WEB3DateUtility.getDate("20070721", "yyyyMMdd").getTime()));
        
        TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                "webbroker3.gentrade.WEB3GentradeTradingCalendarModelImpl",
                "getCurrentBizDate",
                new Class[] {long.class},
                new Timestamp(WEB3DateUtility.getDate("20070626", "yyyyMMdd").getTime()));
        
        try
        {
            TestDBUtility.deleteAll(TradedProductRow.TYPE);
            TestDBUtility.insertWithDel(l_ifoTradedProductParams);
            
            TestDBUtility.deleteAll(TradedProductRow.TYPE);
            TestDBUtility.insertWithDel(l_tradedProductParams);
            
            TestDBUtility.deleteAll(IfoProductRow.TYPE);
            TestDBUtility.insertWithDel(l_ifoProductParams);
            
            WEB3GentradeTradingTimeManagementForMock.mockGetBizDateType(
                new Timestamp(date.getTime()),
                l_tradingTimeParams.getBizDateType());
            TestDBUtility.deleteAll(TradingTimeRow.TYPE);
            TestDBUtility.insertWithDel(l_tradingTimeParams);
            ThreadLocalSystemAttributesRegistry.setAttribute(
                WEB3GentradeTradingTimeManagement.BASE_TIMESTAMP_FOR_ORDER_BIZ_DATE,
                new Timestamp(date.getTime()));  

            TestDBUtility.deleteAll(IfoTradedProductUpdqRow.TYPE);
            TestDBUtility.insertWithDel(l_ifoTradedProductUpdqParams);
            
        }
        catch (WEB3BaseException l_ex)
        {
            log.error(l_ex.getMessage(), l_ex);
            fail();
        }
        
        try
        {
            WEB3IfoTradedProductImpl l_tradedProductImpl =
                new WEB3IfoTradedProductImpl(1006160060009L);
            assertEquals("200.0",
                "" + l_tradedProductImpl.getIfoStopLowPrice());
        }
        catch (WEB3BaseException l_ex)
        {
            log.error(l_ex.getMessage(), l_ex);
            fail();
        }
        catch (DataFindException l_ex)
        {
            log.error(l_ex.getMessage(), l_ex);
            fail();
        }
        catch (DataQueryException l_ex)
        {
            log.error(l_ex.getMessage(), l_ex);
            fail();
        }
        catch (DataNetworkException l_ex)
        {
            log.error(l_ex.getMessage(), l_ex);
            fail();
        }
        
        log.exiting(STR_METHOD_NAME);
    }
 
    public void testGetIfoStopLowPriceCase0002()
    {
        final String STR_METHOD_NAME = "testGetIfoStopLowPriceCase0002()";
        log.entering(STR_METHOD_NAME);

        //夕場時間帯（取引時間管理.is夕場時間帯 ()＝false）の場合
        TradingTimeParams l_tradingTimeParams = TestDBUtility.getTradingTimeRow();
        l_tradingTimeParams.setInstitutionCode("0D");
        l_tradingTimeParams.setBranchCode("123");
        l_tradingTimeParams.setMarketCode("N1");
        l_tradingTimeParams.setTradingTimeType("01");
        l_tradingTimeParams.setProductCode("0");
        l_tradingTimeParams.setBizDateType("1");
        l_tradingTimeParams.setSessionType(null);
        
        IfoTradedProductParams l_ifoTradedProductParams =
            TestDBUtility.getIfoTradedProductRow();
        l_ifoTradedProductParams.setTradedProductId(1006160060009L);
        l_ifoTradedProductParams.setProductId(1006160060005L);
        l_ifoTradedProductParams.setMarketId(3303L);
        l_ifoTradedProductParams.setValidForBizDate("20070626");
        //値幅の下限
        l_ifoTradedProductParams.setStopLowPrice(100);
        
        TradedProductParams l_tradedProductParams =
            TestDBUtility.getTradedProductRow();
        l_tradedProductParams.setProductId(1006160060005L);
        l_tradedProductParams.setMarketId(3303L);
        l_tradedProductParams.setTradedProductId(1006160060009L);
        
        IfoProductParams l_ifoProductParams =
            TestDBUtility.getIfoProductRow();
        l_ifoProductParams.setProductId(1006160060005L);
        l_ifoProductParams.setTargetMarketId(3303L);
        l_ifoProductParams.setInstitutionCode("0D");

        IfoTradedProductUpdqParams l_ifoTradedProductUpdqParams =
            TestDBUtility.getIfoTradedProductUpdqRow();
        //取引銘柄ＩＤ
        l_ifoTradedProductUpdqParams.setTradedProductId(1006160060009L);
        l_ifoTradedProductUpdqParams.setMarketId(3303L);
        //有効日の翌営業日
        l_ifoTradedProductUpdqParams.setValidForBizDate("20070626");
        l_ifoTradedProductUpdqParams.setProductId(1006160060005L);
        //値幅の下限
        l_ifoTradedProductUpdqParams.setStopLowPrice(200);

        Calendar ca =  Calendar.getInstance();
        ca.set(2007,6-1,26);
        ca.set(Calendar.HOUR_OF_DAY, 0);
        ca.set(Calendar.MINUTE, 0);
        ca.set(Calendar.SECOND, 0);
        ca.set(Calendar.MILLISECOND, 0);
        Date date = ca.getTime();
                     
        TestBaseForMock.MOCK_MANAGER.setIsMockUsed(true);
        
        TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
            "com.fitechlabs.xtrade.plugin.tc.gentrade.stdimpls.TradingSystemImpl",
            "getBizDate",
            new Class[] {},
            new Timestamp(WEB3DateUtility.getDate("20070721", "yyyyMMdd").getTime()));
        
        TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
            "webbroker3.gentrade.WEB3GentradeTradingCalendarModelImpl",
            "getCurrentBizDate",
            new Class[] {long.class},
            new Timestamp(WEB3DateUtility.getDate("20070626", "yyyyMMdd").getTime()));
        
        try
        {
            TestDBUtility.deleteAll(TradedProductRow.TYPE);
            TestDBUtility.insertWithDel(l_ifoTradedProductParams);
            
            TestDBUtility.deleteAll(TradedProductRow.TYPE);
            TestDBUtility.insertWithDel(l_tradedProductParams);
            
            TestDBUtility.deleteAll(IfoProductRow.TYPE);
            TestDBUtility.insertWithDel(l_ifoProductParams);
            
            WEB3GentradeTradingTimeManagementForMock.mockGetBizDateType(
                new Timestamp(date.getTime()),
                l_tradingTimeParams.getBizDateType());
            TestDBUtility.deleteAll(TradingTimeRow.TYPE);
            TestDBUtility.insertWithDel(l_tradingTimeParams);
            ThreadLocalSystemAttributesRegistry.setAttribute(
                WEB3GentradeTradingTimeManagement.BASE_TIMESTAMP_FOR_ORDER_BIZ_DATE,
                new Timestamp(date.getTime()));  

            TestDBUtility.deleteAll(IfoTradedProductUpdqRow.TYPE);
            TestDBUtility.insertWithDel(l_ifoTradedProductUpdqParams);
            
        }
        catch (WEB3BaseException l_ex)
        {
            log.error(l_ex.getMessage(), l_ex);
            fail();
        }
        
        try
        {
            WEB3IfoTradedProductImpl l_tradedProductImpl =
                new WEB3IfoTradedProductImpl(1006160060009L);
            assertEquals("100.0",
                "" + l_tradedProductImpl.getIfoStopLowPrice());
        }
        catch (WEB3BaseException l_ex)
        {
            log.error(l_ex.getMessage(), l_ex);
            fail();
        }
        catch (DataFindException l_ex)
        {
            log.error(l_ex.getMessage(), l_ex);
            fail();
        }
        catch (DataQueryException l_ex)
        {
            log.error(l_ex.getMessage(), l_ex);
            fail();
        }
        catch (DataNetworkException l_ex)
        {
            log.error(l_ex.getMessage(), l_ex);
            fail();
        }
        
        log.exiting(STR_METHOD_NAME);
    }
    
    public void testIsPriceRangeCheckedCase0001()
    {
        final String STR_METHOD_NAME = "testIsPriceRangeCheckedCase0001()";
        log.entering(STR_METHOD_NAME);

        //夕場時間帯（取引時間管理.is夕場時間帯 ()＝true）の場合
        TradingTimeParams l_tradingTimeParams = TestDBUtility.getTradingTimeRow();
        l_tradingTimeParams.setInstitutionCode("0D");
        l_tradingTimeParams.setBranchCode("123");
        l_tradingTimeParams.setMarketCode("N1");
        l_tradingTimeParams.setTradingTimeType("01");
        l_tradingTimeParams.setProductCode("0");
        l_tradingTimeParams.setBizDateType("1");
        l_tradingTimeParams.setSessionType("1");
        
        IfoTradedProductParams l_ifoTradedProductParams =
            TestDBUtility.getIfoTradedProductRow();
        l_ifoTradedProductParams.setTradedProductId(1006160060009L);
        l_ifoTradedProductParams.setProductId(1006160060005L);
        l_ifoTradedProductParams.setMarketId(3303L);
        l_ifoTradedProductParams.setValidForBizDate("20070626");
        //値幅チェック区分
        l_ifoTradedProductParams.setPriceRangeType("1");
        
        TradedProductParams l_tradedProductParams =
            TestDBUtility.getTradedProductRow();
        l_tradedProductParams.setProductId(1006160060005L);
        l_tradedProductParams.setMarketId(3303L);
        l_tradedProductParams.setTradedProductId(1006160060009L);
        
        IfoProductParams l_ifoProductParams =
            TestDBUtility.getIfoProductRow();
        l_ifoProductParams.setProductId(1006160060005L);
        l_ifoProductParams.setTargetMarketId(3303L);
        l_ifoProductParams.setInstitutionCode("0D");

        IfoTradedProductUpdqParams l_ifoTradedProductUpdqParams =
            TestDBUtility.getIfoTradedProductUpdqRow();
        l_ifoTradedProductUpdqParams.setTradedProductId(1006160060009L);
        l_ifoTradedProductUpdqParams.setMarketId(3303L);
        l_ifoTradedProductUpdqParams.setValidForBizDate("20070627");
        l_ifoTradedProductUpdqParams.setProductId(1006160060005L);
        //値幅チェック区分
        l_ifoTradedProductUpdqParams.setPriceRangeType("0");

        Calendar ca =  Calendar.getInstance();
        ca.set(2007,6-1,26);
        ca.set(Calendar.HOUR_OF_DAY, 0);
        ca.set(Calendar.MINUTE, 0);
        ca.set(Calendar.SECOND, 0);
        ca.set(Calendar.MILLISECOND, 0);
        Date date = ca.getTime();
                     
        TestBaseForMock.MOCK_MANAGER.setIsMockUsed(true);
        
        TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
            "com.fitechlabs.xtrade.plugin.tc.gentrade.stdimpls.TradingSystemImpl",
            "getBizDate",
            new Class[] {},
            new Timestamp(WEB3DateUtility.getDate("20070721", "yyyyMMdd").getTime()));
        
        TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                "webbroker3.gentrade.WEB3GentradeTradingCalendarModelImpl",
                "getCurrentBizDate",
                new Class[] {long.class},
                new Timestamp(WEB3DateUtility.getDate("20070626", "yyyyMMdd").getTime()));
        
        try
        {
            TestDBUtility.deleteAll(TradedProductRow.TYPE);
            TestDBUtility.insertWithDel(l_ifoTradedProductParams);
            
            TestDBUtility.deleteAll(TradedProductRow.TYPE);
            TestDBUtility.insertWithDel(l_tradedProductParams);
            
            TestDBUtility.deleteAll(IfoProductRow.TYPE);
            TestDBUtility.insertWithDel(l_ifoProductParams);
            
            WEB3GentradeTradingTimeManagementForMock.mockGetBizDateType(
                new Timestamp(date.getTime()),
                l_tradingTimeParams.getBizDateType());
            TestDBUtility.deleteAll(TradingTimeRow.TYPE);
            TestDBUtility.insertWithDel(l_tradingTimeParams);
            ThreadLocalSystemAttributesRegistry.setAttribute(
                WEB3GentradeTradingTimeManagement.BASE_TIMESTAMP_FOR_ORDER_BIZ_DATE,
                new Timestamp(date.getTime()));  

            TestDBUtility.deleteAll(IfoTradedProductUpdqRow.TYPE);
            TestDBUtility.insertWithDel(l_ifoTradedProductUpdqParams);
            
        }
        catch (WEB3BaseException l_ex)
        {
            log.error(l_ex.getMessage(), l_ex);
            fail();
        }
        
        try
        {
            WEB3IfoTradedProductImpl l_tradedProductImpl =
                new WEB3IfoTradedProductImpl(1006160060009L);
            assertEquals(false, l_tradedProductImpl.isPriceRangeChecked());
        }
        catch (WEB3BaseException l_ex)
        {
            log.error(l_ex.getMessage(), l_ex);
            fail();
        }
        catch (DataFindException l_ex)
        {
            log.error(l_ex.getMessage(), l_ex);
            fail();
        }
        catch (DataQueryException l_ex)
        {
            log.error(l_ex.getMessage(), l_ex);
            fail();
        }
        catch (DataNetworkException l_ex)
        {
            log.error(l_ex.getMessage(), l_ex);
            fail();
        }
        
        log.exiting(STR_METHOD_NAME);
    }
    
    public void testIsPriceRangeCheckedCase0002()
    {
        final String STR_METHOD_NAME = "testIsPriceRangeCheckedCase0002()";
        log.entering(STR_METHOD_NAME);

        //夕場時間帯（取引時間管理.is夕場時間帯 ()＝true）の場合
        TradingTimeParams l_tradingTimeParams = TestDBUtility.getTradingTimeRow();
        l_tradingTimeParams.setInstitutionCode("0D");
        l_tradingTimeParams.setBranchCode("123");
        l_tradingTimeParams.setMarketCode("N1");
        l_tradingTimeParams.setTradingTimeType("01");
        l_tradingTimeParams.setProductCode("0");
        l_tradingTimeParams.setBizDateType("1");
        l_tradingTimeParams.setSessionType("1");
        
        IfoTradedProductParams l_ifoTradedProductParams =
            TestDBUtility.getIfoTradedProductRow();
        l_ifoTradedProductParams.setTradedProductId(1006160060009L);
        l_ifoTradedProductParams.setProductId(1006160060005L);
        l_ifoTradedProductParams.setMarketId(3303L);
        l_ifoTradedProductParams.setValidForBizDate("20070626");
        //値幅チェック区分
        l_ifoTradedProductParams.setPriceRangeType("1");
        
        TradedProductParams l_tradedProductParams =
            TestDBUtility.getTradedProductRow();
        l_tradedProductParams.setProductId(1006160060005L);
        l_tradedProductParams.setMarketId(3303L);
        l_tradedProductParams.setTradedProductId(1006160060009L);
        
        IfoProductParams l_ifoProductParams =
            TestDBUtility.getIfoProductRow();
        l_ifoProductParams.setProductId(1006160060005L);
        l_ifoProductParams.setTargetMarketId(3303L);
        l_ifoProductParams.setInstitutionCode("0D");

        IfoTradedProductUpdqParams l_ifoTradedProductUpdqParams =
            TestDBUtility.getIfoTradedProductUpdqRow();
        l_ifoTradedProductUpdqParams.setTradedProductId(1006160060009L);
        l_ifoTradedProductUpdqParams.setMarketId(3303L);
        l_ifoTradedProductUpdqParams.setValidForBizDate("20070627");
        l_ifoTradedProductUpdqParams.setProductId(1006160060005L);
        //値幅チェック区分
        l_ifoTradedProductUpdqParams.setPriceRangeType(null);

        Calendar ca =  Calendar.getInstance();
        ca.set(2007,6-1,26);
        ca.set(Calendar.HOUR_OF_DAY, 0);
        ca.set(Calendar.MINUTE, 0);
        ca.set(Calendar.SECOND, 0);
        ca.set(Calendar.MILLISECOND, 0);
        Date date = ca.getTime();
                     
        TestBaseForMock.MOCK_MANAGER.setIsMockUsed(true);
        
        TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
            "com.fitechlabs.xtrade.plugin.tc.gentrade.stdimpls.TradingSystemImpl",
            "getBizDate",
            new Class[] {},
            new Timestamp(WEB3DateUtility.getDate("20070721", "yyyyMMdd").getTime()));
        
        TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                "webbroker3.gentrade.WEB3GentradeTradingCalendarModelImpl",
                "getCurrentBizDate",
                new Class[] {long.class},
                new Timestamp(WEB3DateUtility.getDate("20070626", "yyyyMMdd").getTime()));
        
        try
        {
            TestDBUtility.deleteAll(TradedProductRow.TYPE);
            TestDBUtility.insertWithDel(l_ifoTradedProductParams);
            
            TestDBUtility.deleteAll(TradedProductRow.TYPE);
            TestDBUtility.insertWithDel(l_tradedProductParams);
            
            TestDBUtility.deleteAll(IfoProductRow.TYPE);
            TestDBUtility.insertWithDel(l_ifoProductParams);
            
            WEB3GentradeTradingTimeManagementForMock.mockGetBizDateType(
                new Timestamp(date.getTime()),
                l_tradingTimeParams.getBizDateType());
            TestDBUtility.deleteAll(TradingTimeRow.TYPE);
            TestDBUtility.insertWithDel(l_tradingTimeParams);
            ThreadLocalSystemAttributesRegistry.setAttribute(
                WEB3GentradeTradingTimeManagement.BASE_TIMESTAMP_FOR_ORDER_BIZ_DATE,
                new Timestamp(date.getTime()));  

            TestDBUtility.deleteAll(IfoTradedProductUpdqRow.TYPE);
            TestDBUtility.insertWithDel(l_ifoTradedProductUpdqParams);
            
        }
        catch (WEB3BaseException l_ex)
        {
            log.error(l_ex.getMessage(), l_ex);
            fail();
        }
        
        try
        {
            WEB3IfoTradedProductImpl l_tradedProductImpl =
                new WEB3IfoTradedProductImpl(1006160060009L);
            assertEquals(false, l_tradedProductImpl.isPriceRangeChecked());
        }
        catch (WEB3BaseException l_ex)
        {
            log.error(l_ex.getMessage(), l_ex);
            fail();
        }
        catch (DataFindException l_ex)
        {
            log.error(l_ex.getMessage(), l_ex);
            fail();
        }
        catch (DataQueryException l_ex)
        {
            log.error(l_ex.getMessage(), l_ex);
            fail();
        }
        catch (DataNetworkException l_ex)
        {
            log.error(l_ex.getMessage(), l_ex);
            fail();
        }
        
        log.exiting(STR_METHOD_NAME);
    }
    
    public void testIsPriceRangeCheckedCase0003()
    {
        final String STR_METHOD_NAME = "testGetIfoStopHighPriceCase0003()";
        log.entering(STR_METHOD_NAME);

        //夕場時間帯（取引時間管理.is夕場時間帯 ()＝false）の場合
        TradingTimeParams l_tradingTimeParams = TestDBUtility.getTradingTimeRow();
        l_tradingTimeParams.setInstitutionCode("0D");
        l_tradingTimeParams.setBranchCode("123");
        l_tradingTimeParams.setMarketCode("N1");
        l_tradingTimeParams.setTradingTimeType("01");
        l_tradingTimeParams.setProductCode("0");
        l_tradingTimeParams.setBizDateType("1");
        l_tradingTimeParams.setSessionType(null);
        
        IfoTradedProductParams l_ifoTradedProductParams =
            TestDBUtility.getIfoTradedProductRow();
        l_ifoTradedProductParams.setTradedProductId(1006160060009L);
        l_ifoTradedProductParams.setProductId(1006160060005L);
        l_ifoTradedProductParams.setMarketId(3303L);
        l_ifoTradedProductParams.setValidForBizDate("20070626");
        //値幅チェック区分
        l_ifoTradedProductParams.setPriceRangeType("0");
        
        TradedProductParams l_tradedProductParams =
            TestDBUtility.getTradedProductRow();
        l_tradedProductParams.setProductId(1006160060005L);
        l_tradedProductParams.setMarketId(3303L);
        l_tradedProductParams.setTradedProductId(1006160060009L);
        
        IfoProductParams l_ifoProductParams =
            TestDBUtility.getIfoProductRow();
        l_ifoProductParams.setProductId(1006160060005L);
        l_ifoProductParams.setTargetMarketId(3303L);
        l_ifoProductParams.setInstitutionCode("0D");

        IfoTradedProductUpdqParams l_ifoTradedProductUpdqParams =
            TestDBUtility.getIfoTradedProductUpdqRow();
        l_ifoTradedProductUpdqParams.setTradedProductId(1006160060009L);
        l_ifoTradedProductUpdqParams.setMarketId(3303L);
        l_ifoTradedProductUpdqParams.setValidForBizDate("20070626");
        l_ifoTradedProductUpdqParams.setProductId(1006160060005L);
        //値幅チェック区分
        l_ifoTradedProductUpdqParams.setPriceRangeType("1");

        Calendar ca =  Calendar.getInstance();
        ca.set(2007,6-1,26);
        ca.set(Calendar.HOUR_OF_DAY, 0);
        ca.set(Calendar.MINUTE, 0);
        ca.set(Calendar.SECOND, 0);
        ca.set(Calendar.MILLISECOND, 0);
        Date date = ca.getTime();
                     
        TestBaseForMock.MOCK_MANAGER.setIsMockUsed(true);
        
        TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
            "com.fitechlabs.xtrade.plugin.tc.gentrade.stdimpls.TradingSystemImpl",
            "getBizDate",
            new Class[] {},
            new Timestamp(WEB3DateUtility.getDate("20070721", "yyyyMMdd").getTime()));
        
        TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
            "webbroker3.gentrade.WEB3GentradeTradingCalendarModelImpl",
            "getCurrentBizDate",
            new Class[] {long.class},
            new Timestamp(WEB3DateUtility.getDate("20070626", "yyyyMMdd").getTime()));
        
        try
        {
            TestDBUtility.deleteAll(TradedProductRow.TYPE);
            TestDBUtility.insertWithDel(l_ifoTradedProductParams);
            
            TestDBUtility.deleteAll(TradedProductRow.TYPE);
            TestDBUtility.insertWithDel(l_tradedProductParams);
            
            TestDBUtility.deleteAll(IfoProductRow.TYPE);
            TestDBUtility.insertWithDel(l_ifoProductParams);
            
            WEB3GentradeTradingTimeManagementForMock.mockGetBizDateType(
                new Timestamp(date.getTime()),
                l_tradingTimeParams.getBizDateType());
            TestDBUtility.deleteAll(TradingTimeRow.TYPE);
            TestDBUtility.insertWithDel(l_tradingTimeParams);
            ThreadLocalSystemAttributesRegistry.setAttribute(
                WEB3GentradeTradingTimeManagement.BASE_TIMESTAMP_FOR_ORDER_BIZ_DATE,
                new Timestamp(date.getTime()));  

            TestDBUtility.deleteAll(IfoTradedProductUpdqRow.TYPE);
            TestDBUtility.insertWithDel(l_ifoTradedProductUpdqParams);
            
        }
        catch (WEB3BaseException l_ex)
        {
            log.error(l_ex.getMessage(), l_ex);
            fail();
        }
        
        try
        {
            WEB3IfoTradedProductImpl l_tradedProductImpl =
                new WEB3IfoTradedProductImpl(1006160060009L);
            assertEquals(false, l_tradedProductImpl.isPriceRangeChecked());
        }
        catch (WEB3BaseException l_ex)
        {
            log.error(l_ex.getMessage(), l_ex);
            fail();
        }
        catch (DataFindException l_ex)
        {
            log.error(l_ex.getMessage(), l_ex);
            fail();
        }
        catch (DataQueryException l_ex)
        {
            log.error(l_ex.getMessage(), l_ex);
            fail();
        }
        catch (DataNetworkException l_ex)
        {
            log.error(l_ex.getMessage(), l_ex);
            fail();
        }
        
        log.exiting(STR_METHOD_NAME);
    }
    
    public void testIsPriceRangeCheckedCase0004()
    {
        final String STR_METHOD_NAME = "testGetIfoStopHighPriceCase0004()";
        log.entering(STR_METHOD_NAME);

        //夕場時間帯（取引時間管理.is夕場時間帯 ()＝false）の場合
        TradingTimeParams l_tradingTimeParams = TestDBUtility.getTradingTimeRow();
        l_tradingTimeParams.setInstitutionCode("0D");
        l_tradingTimeParams.setBranchCode("123");
        l_tradingTimeParams.setMarketCode("N1");
        l_tradingTimeParams.setTradingTimeType("01");
        l_tradingTimeParams.setProductCode("0");
        l_tradingTimeParams.setBizDateType("1");
        l_tradingTimeParams.setSessionType(null);
        
        IfoTradedProductParams l_ifoTradedProductParams =
            TestDBUtility.getIfoTradedProductRow();
        l_ifoTradedProductParams.setTradedProductId(1006160060009L);
        l_ifoTradedProductParams.setProductId(1006160060005L);
        l_ifoTradedProductParams.setMarketId(3303L);
        l_ifoTradedProductParams.setValidForBizDate("20070626");
        //値幅チェック区分
        l_ifoTradedProductParams.setPriceRangeType(null);
        
        TradedProductParams l_tradedProductParams =
            TestDBUtility.getTradedProductRow();
        l_tradedProductParams.setProductId(1006160060005L);
        l_tradedProductParams.setMarketId(3303L);
        l_tradedProductParams.setTradedProductId(1006160060009L);
        
        IfoProductParams l_ifoProductParams =
            TestDBUtility.getIfoProductRow();
        l_ifoProductParams.setProductId(1006160060005L);
        l_ifoProductParams.setTargetMarketId(3303L);
        l_ifoProductParams.setInstitutionCode("0D");

        IfoTradedProductUpdqParams l_ifoTradedProductUpdqParams =
            TestDBUtility.getIfoTradedProductUpdqRow();
        l_ifoTradedProductUpdqParams.setTradedProductId(1006160060009L);
        l_ifoTradedProductUpdqParams.setMarketId(3303L);
        l_ifoTradedProductUpdqParams.setValidForBizDate("20070626");
        l_ifoTradedProductUpdqParams.setProductId(1006160060005L);
        //値幅チェック区分
        l_ifoTradedProductUpdqParams.setPriceRangeType("1");

        Calendar ca =  Calendar.getInstance();
        ca.set(2007,6-1,26);
        ca.set(Calendar.HOUR_OF_DAY, 0);
        ca.set(Calendar.MINUTE, 0);
        ca.set(Calendar.SECOND, 0);
        ca.set(Calendar.MILLISECOND, 0);
        Date date = ca.getTime();
                     
        TestBaseForMock.MOCK_MANAGER.setIsMockUsed(true);
        
        TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
            "com.fitechlabs.xtrade.plugin.tc.gentrade.stdimpls.TradingSystemImpl",
            "getBizDate",
            new Class[] {},
            new Timestamp(WEB3DateUtility.getDate("20070721", "yyyyMMdd").getTime()));
        
        TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
            "webbroker3.gentrade.WEB3GentradeTradingCalendarModelImpl",
            "getCurrentBizDate",
            new Class[] {long.class},
            new Timestamp(WEB3DateUtility.getDate("20070626", "yyyyMMdd").getTime()));
        
        try
        {
            TestDBUtility.deleteAll(TradedProductRow.TYPE);
            TestDBUtility.insertWithDel(l_ifoTradedProductParams);
            
            TestDBUtility.deleteAll(TradedProductRow.TYPE);
            TestDBUtility.insertWithDel(l_tradedProductParams);
            
            TestDBUtility.deleteAll(IfoProductRow.TYPE);
            TestDBUtility.insertWithDel(l_ifoProductParams);
            
            WEB3GentradeTradingTimeManagementForMock.mockGetBizDateType(
                new Timestamp(date.getTime()),
                l_tradingTimeParams.getBizDateType());
            TestDBUtility.deleteAll(TradingTimeRow.TYPE);
            TestDBUtility.insertWithDel(l_tradingTimeParams);
            ThreadLocalSystemAttributesRegistry.setAttribute(
                WEB3GentradeTradingTimeManagement.BASE_TIMESTAMP_FOR_ORDER_BIZ_DATE,
                new Timestamp(date.getTime()));  

            TestDBUtility.deleteAll(IfoTradedProductUpdqRow.TYPE);
            TestDBUtility.insertWithDel(l_ifoTradedProductUpdqParams);
            
        }
        catch (WEB3BaseException l_ex)
        {
            log.error(l_ex.getMessage(), l_ex);
            fail();
        }
        
        try
        {
            WEB3IfoTradedProductImpl l_tradedProductImpl =
                new WEB3IfoTradedProductImpl(1006160060009L);
            assertEquals(false, l_tradedProductImpl.isPriceRangeChecked());
        }
        catch (WEB3BaseException l_ex)
        {
            log.error(l_ex.getMessage(), l_ex);
            fail();
        }
        catch (DataFindException l_ex)
        {
            log.error(l_ex.getMessage(), l_ex);
            fail();
        }
        catch (DataQueryException l_ex)
        {
            log.error(l_ex.getMessage(), l_ex);
            fail();
        }
        catch (DataNetworkException l_ex)
        {
            log.error(l_ex.getMessage(), l_ex);
            fail();
        }
        
        log.exiting(STR_METHOD_NAME);
    }
    
    public void testIsPriceRangeCheckedCase0005()
    {
        final String STR_METHOD_NAME = "testIsPriceRangeCheckedCase0005()";
        log.entering(STR_METHOD_NAME);

        //夕場時間帯（取引時間管理.is夕場時間帯 ()＝true）の場合
        TradingTimeParams l_tradingTimeParams = TestDBUtility.getTradingTimeRow();
        l_tradingTimeParams.setInstitutionCode("0D");
        l_tradingTimeParams.setBranchCode("123");
        l_tradingTimeParams.setMarketCode("N1");
        l_tradingTimeParams.setTradingTimeType("01");
        l_tradingTimeParams.setProductCode("0");
        l_tradingTimeParams.setBizDateType("1");
        l_tradingTimeParams.setSessionType("1");
        
        IfoTradedProductParams l_ifoTradedProductParams =
            TestDBUtility.getIfoTradedProductRow();
        l_ifoTradedProductParams.setTradedProductId(1006160060009L);
        l_ifoTradedProductParams.setProductId(1006160060005L);
        l_ifoTradedProductParams.setMarketId(3303L);
        l_ifoTradedProductParams.setValidForBizDate("20070626");
        //値幅チェック区分
        l_ifoTradedProductParams.setPriceRangeType("0");
        
        TradedProductParams l_tradedProductParams =
            TestDBUtility.getTradedProductRow();
        l_tradedProductParams.setProductId(1006160060005L);
        l_tradedProductParams.setMarketId(3303L);
        l_tradedProductParams.setTradedProductId(1006160060009L);
        
        IfoProductParams l_ifoProductParams =
            TestDBUtility.getIfoProductRow();
        l_ifoProductParams.setProductId(1006160060005L);
        l_ifoProductParams.setTargetMarketId(3303L);
        l_ifoProductParams.setInstitutionCode("0D");

        IfoTradedProductUpdqParams l_ifoTradedProductUpdqParams =
            TestDBUtility.getIfoTradedProductUpdqRow();
        l_ifoTradedProductUpdqParams.setTradedProductId(1006160060009L);
        l_ifoTradedProductUpdqParams.setMarketId(3303L);
        l_ifoTradedProductUpdqParams.setValidForBizDate("20070627");
        l_ifoTradedProductUpdqParams.setProductId(1006160060005L);
        //値幅チェック区分
        l_ifoTradedProductUpdqParams.setPriceRangeType("1");

        Calendar ca =  Calendar.getInstance();
        ca.set(2007,6-1,26);
        ca.set(Calendar.HOUR_OF_DAY, 0);
        ca.set(Calendar.MINUTE, 0);
        ca.set(Calendar.SECOND, 0);
        ca.set(Calendar.MILLISECOND, 0);
        Date date = ca.getTime();
                     
        TestBaseForMock.MOCK_MANAGER.setIsMockUsed(true);
        
        TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
            "com.fitechlabs.xtrade.plugin.tc.gentrade.stdimpls.TradingSystemImpl",
            "getBizDate",
            new Class[] {},
            new Timestamp(WEB3DateUtility.getDate("20070721", "yyyyMMdd").getTime()));
        
        TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
            "webbroker3.gentrade.WEB3GentradeTradingCalendarModelImpl",
            "getCurrentBizDate",
            new Class[] {long.class},
            new Timestamp(WEB3DateUtility.getDate("20070626", "yyyyMMdd").getTime()));
        
        TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
            "webbroker3.ifo.WEB3IfoProductManagerImpl",
            "isIfoBasePriceMail",
            new Class[] { String.class },
            Boolean.TRUE);
        
        try
        {
            TestDBUtility.deleteAll(TradedProductRow.TYPE);
            TestDBUtility.insertWithDel(l_ifoTradedProductParams);
            
            TestDBUtility.deleteAll(TradedProductRow.TYPE);
            TestDBUtility.insertWithDel(l_tradedProductParams);
            
            TestDBUtility.deleteAll(IfoProductRow.TYPE);
            TestDBUtility.insertWithDel(l_ifoProductParams);
            
            WEB3GentradeTradingTimeManagementForMock.mockGetBizDateType(
                new Timestamp(date.getTime()),
                l_tradingTimeParams.getBizDateType());
            TestDBUtility.deleteAll(TradingTimeRow.TYPE);
            TestDBUtility.insertWithDel(l_tradingTimeParams);
            ThreadLocalSystemAttributesRegistry.setAttribute(
                WEB3GentradeTradingTimeManagement.BASE_TIMESTAMP_FOR_ORDER_BIZ_DATE,
                new Timestamp(date.getTime()));  

            TestDBUtility.deleteAll(IfoTradedProductUpdqRow.TYPE);
            TestDBUtility.insertWithDel(l_ifoTradedProductUpdqParams);
            
        }
        catch (WEB3BaseException l_ex)
        {
            log.error(l_ex.getMessage(), l_ex);
            fail();
        }
        
        try
        {
            WEB3IfoTradedProductImpl l_tradedProductImpl =
                new WEB3IfoTradedProductImpl(1006160060009L);
            assertEquals(true, l_tradedProductImpl.isPriceRangeChecked());
        }
        catch (WEB3BaseException l_ex)
        {
            log.error(l_ex.getMessage(), l_ex);
            fail();
        }
        catch (DataFindException l_ex)
        {
            log.error(l_ex.getMessage(), l_ex);
            fail();
        }
        catch (DataQueryException l_ex)
        {
            log.error(l_ex.getMessage(), l_ex);
            fail();
        }
        catch (DataNetworkException l_ex)
        {
            log.error(l_ex.getMessage(), l_ex);
            fail();
        }
        
        log.exiting(STR_METHOD_NAME);
    }
    
    public void testIsPriceRangeCheckedCase0006()
    {
        final String STR_METHOD_NAME = "testIsPriceRangeCheckedCase0006()";
        log.entering(STR_METHOD_NAME);

        //夕場時間帯（取引時間管理.is夕場時間帯 ()＝true）の場合
        TradingTimeParams l_tradingTimeParams = TestDBUtility.getTradingTimeRow();
        l_tradingTimeParams.setInstitutionCode("0D");
        l_tradingTimeParams.setBranchCode("123");
        l_tradingTimeParams.setMarketCode("N1");
        l_tradingTimeParams.setTradingTimeType("01");
        l_tradingTimeParams.setProductCode("0");
        l_tradingTimeParams.setBizDateType("1");
        l_tradingTimeParams.setSessionType("1");
        
        IfoTradedProductParams l_ifoTradedProductParams =
            TestDBUtility.getIfoTradedProductRow();
        l_ifoTradedProductParams.setTradedProductId(1006160060009L);
        l_ifoTradedProductParams.setProductId(1006160060005L);
        l_ifoTradedProductParams.setMarketId(3303L);
        l_ifoTradedProductParams.setValidForBizDate("20070626");
        //値幅チェック区分
        l_ifoTradedProductParams.setPriceRangeType("0");
        
        TradedProductParams l_tradedProductParams =
            TestDBUtility.getTradedProductRow();
        l_tradedProductParams.setProductId(1006160060005L);
        l_tradedProductParams.setMarketId(3303L);
        l_tradedProductParams.setTradedProductId(1006160060009L);
        
        IfoProductParams l_ifoProductParams =
            TestDBUtility.getIfoProductRow();
        l_ifoProductParams.setProductId(1006160060005L);
        l_ifoProductParams.setTargetMarketId(3303L);
        l_ifoProductParams.setInstitutionCode("0D");

        IfoTradedProductUpdqParams l_ifoTradedProductUpdqParams =
            TestDBUtility.getIfoTradedProductUpdqRow();
        l_ifoTradedProductUpdqParams.setTradedProductId(1006160060009L);
        l_ifoTradedProductUpdqParams.setMarketId(3303L);
        l_ifoTradedProductUpdqParams.setValidForBizDate("20070627");
        l_ifoTradedProductUpdqParams.setProductId(1006160060005L);
        //値幅チェック区分
        l_ifoTradedProductUpdqParams.setPriceRangeType("1");

        Calendar ca =  Calendar.getInstance();
        ca.set(2007,6-1,26);
        ca.set(Calendar.HOUR_OF_DAY, 0);
        ca.set(Calendar.MINUTE, 0);
        ca.set(Calendar.SECOND, 0);
        ca.set(Calendar.MILLISECOND, 0);
        Date date = ca.getTime();
                     
        TestBaseForMock.MOCK_MANAGER.setIsMockUsed(true);
        
        TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
            "com.fitechlabs.xtrade.plugin.tc.gentrade.stdimpls.TradingSystemImpl",
            "getBizDate",
            new Class[] {},
            new Timestamp(WEB3DateUtility.getDate("20070721", "yyyyMMdd").getTime()));
        
        TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
            "webbroker3.gentrade.WEB3GentradeTradingCalendarModelImpl",
            "getCurrentBizDate",
            new Class[] {long.class},
            new Timestamp(WEB3DateUtility.getDate("20070626", "yyyyMMdd").getTime()));
        
        TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
            "webbroker3.ifo.WEB3IfoProductManagerImpl",
            "isIfoBasePriceMail",
            new Class[] { String.class },
            Boolean.FALSE);
        
        try
        {
            TestDBUtility.deleteAll(TradedProductRow.TYPE);
            TestDBUtility.insertWithDel(l_ifoTradedProductParams);
            
            TestDBUtility.deleteAll(TradedProductRow.TYPE);
            TestDBUtility.insertWithDel(l_tradedProductParams);
            
            TestDBUtility.deleteAll(IfoProductRow.TYPE);
            TestDBUtility.insertWithDel(l_ifoProductParams);
            
            WEB3GentradeTradingTimeManagementForMock.mockGetBizDateType(
                new Timestamp(date.getTime()),
                l_tradingTimeParams.getBizDateType());
            TestDBUtility.deleteAll(TradingTimeRow.TYPE);
            TestDBUtility.insertWithDel(l_tradingTimeParams);
            ThreadLocalSystemAttributesRegistry.setAttribute(
                WEB3GentradeTradingTimeManagement.BASE_TIMESTAMP_FOR_ORDER_BIZ_DATE,
                new Timestamp(date.getTime()));  

            TestDBUtility.deleteAll(IfoTradedProductUpdqRow.TYPE);
            TestDBUtility.insertWithDel(l_ifoTradedProductUpdqParams);
            
        }
        catch (WEB3BaseException l_ex)
        {
            log.error(l_ex.getMessage(), l_ex);
            fail();
        }
        
        try
        {
            WEB3IfoTradedProductImpl l_tradedProductImpl =
                new WEB3IfoTradedProductImpl(1006160060009L);
            assertEquals(false, l_tradedProductImpl.isPriceRangeChecked());
        }
        catch (WEB3BaseException l_ex)
        {
            log.error(l_ex.getMessage(), l_ex);
            fail();
        }
        catch (DataFindException l_ex)
        {
            log.error(l_ex.getMessage(), l_ex);
            fail();
        }
        catch (DataQueryException l_ex)
        {
            log.error(l_ex.getMessage(), l_ex);
            fail();
        }
        catch (DataNetworkException l_ex)
        {
            log.error(l_ex.getMessage(), l_ex);
            fail();
        }
        
        log.exiting(STR_METHOD_NAME);
    }
    
    public void testIsPriceRangeCheckedCase0007()
    {
        final String STR_METHOD_NAME = "testGetIfoStopHighPriceCase0007()";
        log.entering(STR_METHOD_NAME);

        //夕場時間帯（取引時間管理.is夕場時間帯 ()＝false）の場合
        TradingTimeParams l_tradingTimeParams = TestDBUtility.getTradingTimeRow();
        l_tradingTimeParams.setInstitutionCode("0D");
        l_tradingTimeParams.setBranchCode("123");
        l_tradingTimeParams.setMarketCode("N1");
        l_tradingTimeParams.setTradingTimeType("01");
        l_tradingTimeParams.setProductCode("0");
        l_tradingTimeParams.setBizDateType("1");
        l_tradingTimeParams.setSessionType(null);
        
        IfoTradedProductParams l_ifoTradedProductParams =
            TestDBUtility.getIfoTradedProductRow();
        l_ifoTradedProductParams.setTradedProductId(1006160060009L);
        l_ifoTradedProductParams.setProductId(1006160060005L);
        l_ifoTradedProductParams.setMarketId(3303L);
        l_ifoTradedProductParams.setValidForBizDate("20070626");
        //値幅チェック区分
        l_ifoTradedProductParams.setPriceRangeType("1");
        
        TradedProductParams l_tradedProductParams =
            TestDBUtility.getTradedProductRow();
        l_tradedProductParams.setProductId(1006160060005L);
        l_tradedProductParams.setMarketId(3303L);
        l_tradedProductParams.setTradedProductId(1006160060009L);
        
        IfoProductParams l_ifoProductParams =
            TestDBUtility.getIfoProductRow();
        l_ifoProductParams.setProductId(1006160060005L);
        l_ifoProductParams.setTargetMarketId(3303L);
        l_ifoProductParams.setInstitutionCode("0D");

        IfoTradedProductUpdqParams l_ifoTradedProductUpdqParams =
            TestDBUtility.getIfoTradedProductUpdqRow();
        l_ifoTradedProductUpdqParams.setTradedProductId(1006160060009L);
        l_ifoTradedProductUpdqParams.setMarketId(3303L);
        l_ifoTradedProductUpdqParams.setValidForBizDate("20070626");
        l_ifoTradedProductUpdqParams.setProductId(1006160060005L);
        //値幅チェック区分
        l_ifoTradedProductUpdqParams.setPriceRangeType("0");

        Calendar ca =  Calendar.getInstance();
        ca.set(2007,6-1,26);
        ca.set(Calendar.HOUR_OF_DAY, 0);
        ca.set(Calendar.MINUTE, 0);
        ca.set(Calendar.SECOND, 0);
        ca.set(Calendar.MILLISECOND, 0);
        Date date = ca.getTime();
                     
        TestBaseForMock.MOCK_MANAGER.setIsMockUsed(true);
        
        TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
            "com.fitechlabs.xtrade.plugin.tc.gentrade.stdimpls.TradingSystemImpl",
            "getBizDate",
            new Class[] {},
            new Timestamp(WEB3DateUtility.getDate("20070626", "yyyyMMdd").getTime()));
        
        TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
            "webbroker3.gentrade.WEB3GentradeTradingCalendarModelImpl",
            "getCurrentBizDate",
            new Class[] {long.class},
            new Timestamp(WEB3DateUtility.getDate("20070626", "yyyyMMdd").getTime()));
        
        try
        {
            TestDBUtility.deleteAll(TradedProductRow.TYPE);
            TestDBUtility.insertWithDel(l_ifoTradedProductParams);
            
            TestDBUtility.deleteAll(TradedProductRow.TYPE);
            TestDBUtility.insertWithDel(l_tradedProductParams);
            
            TestDBUtility.deleteAll(IfoProductRow.TYPE);
            TestDBUtility.insertWithDel(l_ifoProductParams);
            
            WEB3GentradeTradingTimeManagementForMock.mockGetBizDateType(
                new Timestamp(date.getTime()),
                l_tradingTimeParams.getBizDateType());
            TestDBUtility.deleteAll(TradingTimeRow.TYPE);
            TestDBUtility.insertWithDel(l_tradingTimeParams);
            ThreadLocalSystemAttributesRegistry.setAttribute(
                WEB3GentradeTradingTimeManagement.BASE_TIMESTAMP_FOR_ORDER_BIZ_DATE,
                new Timestamp(date.getTime()));  

            TestDBUtility.deleteAll(IfoTradedProductUpdqRow.TYPE);
            TestDBUtility.insertWithDel(l_ifoTradedProductUpdqParams);
            
        }
        catch (WEB3BaseException l_ex)
        {
            log.error(l_ex.getMessage(), l_ex);
            fail();
        }
        
        try
        {
            WEB3IfoTradedProductImpl l_tradedProductImpl =
                new WEB3IfoTradedProductImpl(1006160060009L);
            assertEquals(true, l_tradedProductImpl.isPriceRangeChecked());
        }
        catch (WEB3BaseException l_ex)
        {
            log.error(l_ex.getMessage(), l_ex);
            fail();
        }
        catch (DataFindException l_ex)
        {
            log.error(l_ex.getMessage(), l_ex);
            fail();
        }
        catch (DataQueryException l_ex)
        {
            log.error(l_ex.getMessage(), l_ex);
            fail();
        }
        catch (DataNetworkException l_ex)
        {
            log.error(l_ex.getMessage(), l_ex);
            fail();
        }
        
        log.exiting(STR_METHOD_NAME);
    }
    
    public void testIsPriceRangeCheckedCase0008()
    {
        final String STR_METHOD_NAME = "testGetIfoStopHighPriceCase0008()";
        log.entering(STR_METHOD_NAME);

        //夕場時間帯（取引時間管理.is夕場時間帯 ()＝false）の場合
        TradingTimeParams l_tradingTimeParams = TestDBUtility.getTradingTimeRow();
        l_tradingTimeParams.setInstitutionCode("0D");
        l_tradingTimeParams.setBranchCode("123");
        l_tradingTimeParams.setMarketCode("N1");
        l_tradingTimeParams.setTradingTimeType("01");
        l_tradingTimeParams.setProductCode("0");
        l_tradingTimeParams.setBizDateType("1");
        l_tradingTimeParams.setSessionType(null);
        
        IfoTradedProductParams l_ifoTradedProductParams =
            TestDBUtility.getIfoTradedProductRow();
        l_ifoTradedProductParams.setTradedProductId(1006160060009L);
        l_ifoTradedProductParams.setProductId(1006160060005L);
        l_ifoTradedProductParams.setMarketId(3303L);
        l_ifoTradedProductParams.setValidForBizDate("20070626");
        //値幅チェック区分
        l_ifoTradedProductParams.setPriceRangeType("1");
        
        TradedProductParams l_tradedProductParams =
            TestDBUtility.getTradedProductRow();
        l_tradedProductParams.setProductId(1006160060005L);
        l_tradedProductParams.setMarketId(3303L);
        l_tradedProductParams.setTradedProductId(1006160060009L);
        
        IfoProductParams l_ifoProductParams =
            TestDBUtility.getIfoProductRow();
        l_ifoProductParams.setProductId(1006160060005L);
        l_ifoProductParams.setTargetMarketId(3303L);
        l_ifoProductParams.setInstitutionCode("0D");

        IfoTradedProductUpdqParams l_ifoTradedProductUpdqParams =
            TestDBUtility.getIfoTradedProductUpdqRow();
        l_ifoTradedProductUpdqParams.setTradedProductId(1006160060009L);
        l_ifoTradedProductUpdqParams.setMarketId(3303L);
        l_ifoTradedProductUpdqParams.setValidForBizDate("20070626");
        l_ifoTradedProductUpdqParams.setProductId(1006160060005L);
        //値幅チェック区分
        l_ifoTradedProductUpdqParams.setPriceRangeType("0");

        Calendar ca =  Calendar.getInstance();
        ca.set(2007,6-1,26);
        ca.set(Calendar.HOUR_OF_DAY, 0);
        ca.set(Calendar.MINUTE, 0);
        ca.set(Calendar.SECOND, 0);
        ca.set(Calendar.MILLISECOND, 0);
        Date date = ca.getTime();
                     
        TestBaseForMock.MOCK_MANAGER.setIsMockUsed(true);
        
        TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
            "com.fitechlabs.xtrade.plugin.tc.gentrade.stdimpls.TradingSystemImpl",
            "getBizDate",
            new Class[] {},
            new Timestamp(WEB3DateUtility.getDate("20070721", "yyyyMMdd").getTime()));
        
        TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
            "webbroker3.gentrade.WEB3GentradeTradingCalendarModelImpl",
            "getCurrentBizDate",
            new Class[] {long.class},
            new Timestamp(WEB3DateUtility.getDate("20070626", "yyyyMMdd").getTime()));
        
        TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
            "webbroker3.ifo.WEB3IfoProductManagerImpl",
            "isIfoBasePriceMail",
            new Class[] { String.class },
            Boolean.TRUE);
        
        try
        {
            TestDBUtility.deleteAll(TradedProductRow.TYPE);
            TestDBUtility.insertWithDel(l_ifoTradedProductParams);
            
            TestDBUtility.deleteAll(TradedProductRow.TYPE);
            TestDBUtility.insertWithDel(l_tradedProductParams);
            
            TestDBUtility.deleteAll(IfoProductRow.TYPE);
            TestDBUtility.insertWithDel(l_ifoProductParams);
            
            WEB3GentradeTradingTimeManagementForMock.mockGetBizDateType(
                new Timestamp(date.getTime()),
                l_tradingTimeParams.getBizDateType());
            TestDBUtility.deleteAll(TradingTimeRow.TYPE);
            TestDBUtility.insertWithDel(l_tradingTimeParams);
            ThreadLocalSystemAttributesRegistry.setAttribute(
                WEB3GentradeTradingTimeManagement.BASE_TIMESTAMP_FOR_ORDER_BIZ_DATE,
                new Timestamp(date.getTime()));  

            TestDBUtility.deleteAll(IfoTradedProductUpdqRow.TYPE);
            TestDBUtility.insertWithDel(l_ifoTradedProductUpdqParams);
            
        }
        catch (WEB3BaseException l_ex)
        {
            log.error(l_ex.getMessage(), l_ex);
            fail();
        }
        
        try
        {
            WEB3IfoTradedProductImpl l_tradedProductImpl =
                new WEB3IfoTradedProductImpl(1006160060009L);
            assertEquals(true, l_tradedProductImpl.isPriceRangeChecked());
        }
        catch (WEB3BaseException l_ex)
        {
            log.error(l_ex.getMessage(), l_ex);
            fail();
        }
        catch (DataFindException l_ex)
        {
            log.error(l_ex.getMessage(), l_ex);
            fail();
        }
        catch (DataQueryException l_ex)
        {
            log.error(l_ex.getMessage(), l_ex);
            fail();
        }
        catch (DataNetworkException l_ex)
        {
            log.error(l_ex.getMessage(), l_ex);
            fail();
        }
        
        log.exiting(STR_METHOD_NAME);
    }
    
    public void testIsPriceRangeCheckedCase0009()
    {
        final String STR_METHOD_NAME = "testGetIfoStopHighPriceCase0009()";
        log.entering(STR_METHOD_NAME);

        //夕場時間帯（取引時間管理.is夕場時間帯 ()＝false）の場合
        TradingTimeParams l_tradingTimeParams = TestDBUtility.getTradingTimeRow();
        l_tradingTimeParams.setInstitutionCode("0D");
        l_tradingTimeParams.setBranchCode("123");
        l_tradingTimeParams.setMarketCode("N1");
        l_tradingTimeParams.setTradingTimeType("01");
        l_tradingTimeParams.setProductCode("0");
        l_tradingTimeParams.setBizDateType("1");
        l_tradingTimeParams.setSessionType(null);
        
        IfoTradedProductParams l_ifoTradedProductParams =
            TestDBUtility.getIfoTradedProductRow();
        l_ifoTradedProductParams.setTradedProductId(1006160060009L);
        l_ifoTradedProductParams.setProductId(1006160060005L);
        l_ifoTradedProductParams.setMarketId(3303L);
        l_ifoTradedProductParams.setValidForBizDate("20070626");
        //値幅チェック区分
        l_ifoTradedProductParams.setPriceRangeType("1");
        
        TradedProductParams l_tradedProductParams =
            TestDBUtility.getTradedProductRow();
        l_tradedProductParams.setProductId(1006160060005L);
        l_tradedProductParams.setMarketId(3303L);
        l_tradedProductParams.setTradedProductId(1006160060009L);
        
        IfoProductParams l_ifoProductParams =
            TestDBUtility.getIfoProductRow();
        l_ifoProductParams.setProductId(1006160060005L);
        l_ifoProductParams.setTargetMarketId(3303L);
        l_ifoProductParams.setInstitutionCode("0D");

        IfoTradedProductUpdqParams l_ifoTradedProductUpdqParams =
            TestDBUtility.getIfoTradedProductUpdqRow();
        l_ifoTradedProductUpdqParams.setTradedProductId(1006160060009L);
        l_ifoTradedProductUpdqParams.setMarketId(3303L);
        l_ifoTradedProductUpdqParams.setValidForBizDate("20070626");
        l_ifoTradedProductUpdqParams.setProductId(1006160060005L);
        //値幅チェック区分
        l_ifoTradedProductUpdqParams.setPriceRangeType("0");

        Calendar ca =  Calendar.getInstance();
        ca.set(2007,6-1,26);
        ca.set(Calendar.HOUR_OF_DAY, 0);
        ca.set(Calendar.MINUTE, 0);
        ca.set(Calendar.SECOND, 0);
        ca.set(Calendar.MILLISECOND, 0);
        Date date = ca.getTime();
                     
        TestBaseForMock.MOCK_MANAGER.setIsMockUsed(true);
        
        TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
            "com.fitechlabs.xtrade.plugin.tc.gentrade.stdimpls.TradingSystemImpl",
            "getBizDate",
            new Class[] {},
            new Timestamp(WEB3DateUtility.getDate("20070721", "yyyyMMdd").getTime()));
        
        TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
            "webbroker3.gentrade.WEB3GentradeTradingCalendarModelImpl",
            "getCurrentBizDate",
            new Class[] {long.class},
            new Timestamp(WEB3DateUtility.getDate("20070626", "yyyyMMdd").getTime()));
        
        TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
            "webbroker3.ifo.WEB3IfoProductManagerImpl",
            "isIfoBasePriceMail",
            new Class[] { String.class },
            Boolean.FALSE);
        
        try
        {
            TestDBUtility.deleteAll(TradedProductRow.TYPE);
            TestDBUtility.insertWithDel(l_ifoTradedProductParams);
            
            TestDBUtility.deleteAll(TradedProductRow.TYPE);
            TestDBUtility.insertWithDel(l_tradedProductParams);
            
            TestDBUtility.deleteAll(IfoProductRow.TYPE);
            TestDBUtility.insertWithDel(l_ifoProductParams);
            
            WEB3GentradeTradingTimeManagementForMock.mockGetBizDateType(
                new Timestamp(date.getTime()),
                l_tradingTimeParams.getBizDateType());
            TestDBUtility.deleteAll(TradingTimeRow.TYPE);
            TestDBUtility.insertWithDel(l_tradingTimeParams);
            ThreadLocalSystemAttributesRegistry.setAttribute(
                WEB3GentradeTradingTimeManagement.BASE_TIMESTAMP_FOR_ORDER_BIZ_DATE,
                new Timestamp(date.getTime()));  

            TestDBUtility.deleteAll(IfoTradedProductUpdqRow.TYPE);
            TestDBUtility.insertWithDel(l_ifoTradedProductUpdqParams);
            
        }
        catch (WEB3BaseException l_ex)
        {
            log.error(l_ex.getMessage(), l_ex);
            fail();
        }
        
        try
        {
            WEB3IfoTradedProductImpl l_tradedProductImpl =
                new WEB3IfoTradedProductImpl(1006160060009L);
            assertEquals(false, l_tradedProductImpl.isPriceRangeChecked());
        }
        catch (WEB3BaseException l_ex)
        {
            log.error(l_ex.getMessage(), l_ex);
            fail();
        }
        catch (DataFindException l_ex)
        {
            log.error(l_ex.getMessage(), l_ex);
            fail();
        }
        catch (DataQueryException l_ex)
        {
            log.error(l_ex.getMessage(), l_ex);
            fail();
        }
        catch (DataNetworkException l_ex)
        {
            log.error(l_ex.getMessage(), l_ex);
            fail();
        }
        
        log.exiting(STR_METHOD_NAME);
    }
    
    public void testGetIfoTradedProductCase0001()
    {
        final String STR_METHOD_NAME = "testGetIfoTradedProductCase0001()";
        log.entering(STR_METHOD_NAME);

        ProductParams l_productParams =
            TestDBUtility.getProductRow();
        l_productParams.setProductId(1006160060005L);
        l_productParams.setInstitutionCode("0D");
        l_productParams.setProductType(ProductTypeEnum.IFO);
        
        IfoTradedProductParams l_ifoTradedProductParams =
            TestDBUtility.getIfoTradedProductRow();
        l_ifoTradedProductParams.setTradedProductId(1006160060009L);
        l_ifoTradedProductParams.setProductId(1006160060005L);
        l_ifoTradedProductParams.setMarketId(3303L);
        l_ifoTradedProductParams.setValidForBizDate("20070626");
        
        TradedProductParams l_tradedProductParams =
            TestDBUtility.getTradedProductRow();
        l_tradedProductParams.setProductId(1006160060005L);
        l_tradedProductParams.setMarketId(3303L);
        l_tradedProductParams.setTradedProductId(1006160060009L);
        
        IfoProductParams l_ifoProductParams =
            TestDBUtility.getIfoProductRow();
        l_ifoProductParams.setProductId(1006160060005L);
        l_ifoProductParams.setTargetMarketId(3303L);
        l_ifoProductParams.setInstitutionCode("0D");
        
        IfoTradedProductUpdqParams l_ifoTradedProductUpdqParams =
            TestDBUtility.getIfoTradedProductUpdqRow();
        //取引銘柄ＩＤ
        l_ifoTradedProductUpdqParams.setTradedProductId(1006160060009L);
        l_ifoTradedProductUpdqParams.setMarketId(3303L);
        //有効日の翌営業日
        l_ifoTradedProductUpdqParams.setValidForBizDate("20070627");
        l_ifoTradedProductUpdqParams.setProductId(1006160060005L);

        Calendar ca =  Calendar.getInstance();
        ca.set(2007,6-1,26);
        ca.set(Calendar.HOUR_OF_DAY, 0);
        ca.set(Calendar.MINUTE, 0);
        ca.set(Calendar.SECOND, 0);
        ca.set(Calendar.MILLISECOND, 0);
        Date date = ca.getTime();
        
        ThreadLocalSystemAttributesRegistry.setAttribute
            (WEB3GentradeTradingTimeManagementForMock.TIMESTAMP_TAG,new Timestamp(date.getTime()));
        
        TestBaseForMock.MOCK_MANAGER.setIsMockUsed(true);
        
        TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
            "com.fitechlabs.xtrade.plugin.tc.gentrade.stdimpls.TradingSystemImpl",
            "getBizDate",
            new Class[] {},
            new Timestamp(WEB3DateUtility.getDate("20070721", "yyyyMMdd").getTime()));

        try
        {
            TestDBUtility.deleteAll(ProductRow.TYPE);
            TestDBUtility.insertWithDel(l_productParams);
            
            TestDBUtility.deleteAll(TradedProductRow.TYPE);
            TestDBUtility.insertWithDel(l_tradedProductParams);
            
            TestDBUtility.deleteAll(IfoProductRow.TYPE);
            TestDBUtility.insertWithDel(l_ifoProductParams);
            
            TestDBUtility.deleteAll(IfoTradedProductRow.TYPE);
            TestDBUtility.insertWithDel(l_ifoTradedProductParams);
            
            TestDBUtility.deleteAll(TradingTimeRow.TYPE);
            WEB3GentradeTradingTimeManagementForMock.mockGetOrderBizDate(date);
            
            TestDBUtility.deleteAll(IfoTradedProductUpdqRow.TYPE);
            TestDBUtility.insertWithDel(l_ifoTradedProductUpdqParams);
        }
        catch (WEB3BaseException l_ex)
        {
            log.error(l_ex.getMessage(), l_ex);
            fail();
        }

        try
        {
            WEB3IfoTradedProductImpl l_tradedProductImpl =
                new WEB3IfoTradedProductImpl(1006160060009L);
            
            IfoTradedProductUpdqRow l_outIfoTradedProductUpdqRow =
                l_tradedProductImpl.getIfoTradedProduct();
            
            assertNotNull(l_outIfoTradedProductUpdqRow);
        }
        catch (DataFindException l_ex)
        {
            log.error(l_ex.getMessage(), l_ex);
            fail();
        }
        catch (DataQueryException l_ex)
        {
            log.error(l_ex.getMessage(), l_ex);
            fail();
        }
        catch (DataNetworkException l_ex)
        {
            log.error(l_ex.getMessage(), l_ex);
            fail();
        }
        catch (WEB3BaseException l_ex)
        {
            log.error(l_ex.getMessage(), l_ex);
            fail();
        }
        log.exiting(STR_METHOD_NAME);
    }
    
    public void testGetIfoTradedProductCase0002()
    {
        final String STR_METHOD_NAME = "testGetIfoTradedProductCase0002()";
        log.entering(STR_METHOD_NAME);

        ProductParams l_productParams =
            TestDBUtility.getProductRow();
        l_productParams.setProductId(1006160060005L);
        l_productParams.setInstitutionCode("0D");
        l_productParams.setProductType(ProductTypeEnum.IFO);
        
        IfoTradedProductParams l_ifoTradedProductParams =
            TestDBUtility.getIfoTradedProductRow();
        l_ifoTradedProductParams.setTradedProductId(1006160060009L);
        l_ifoTradedProductParams.setProductId(1006160060005L);
        l_ifoTradedProductParams.setMarketId(3303L);
        l_ifoTradedProductParams.setValidForBizDate("20070626");
        
        TradedProductParams l_tradedProductParams =
            TestDBUtility.getTradedProductRow();
        l_tradedProductParams.setProductId(1006160060005L);
        l_tradedProductParams.setMarketId(3303L);
        l_tradedProductParams.setTradedProductId(1006160060009L);
        
        IfoProductParams l_ifoProductParams =
            TestDBUtility.getIfoProductRow();
        l_ifoProductParams.setProductId(1006160060005L);
        l_ifoProductParams.setTargetMarketId(3303L);
        l_ifoProductParams.setInstitutionCode("0D");
        
        IfoTradedProductUpdqParams l_ifoTradedProductUpdqParams =
            TestDBUtility.getIfoTradedProductUpdqRow();
        //取引銘柄ＩＤ
        l_ifoTradedProductUpdqParams.setTradedProductId(1006160060009L);
        l_ifoTradedProductUpdqParams.setMarketId(3303L);
        //有効日の翌営業日
        l_ifoTradedProductUpdqParams.setValidForBizDate("20070626");
        l_ifoTradedProductUpdqParams.setProductId(1006160060005L);

        Calendar ca =  Calendar.getInstance();
        ca.set(2007,6-1,26);
        ca.set(Calendar.HOUR_OF_DAY, 0);
        ca.set(Calendar.MINUTE, 0);
        ca.set(Calendar.SECOND, 0);
        ca.set(Calendar.MILLISECOND, 0);
        Date date = ca.getTime();
        
        ThreadLocalSystemAttributesRegistry.setAttribute
            (WEB3GentradeTradingTimeManagementForMock.TIMESTAMP_TAG,new Timestamp(date.getTime()));
        
        TestBaseForMock.MOCK_MANAGER.setIsMockUsed(true);
        
        TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
            "com.fitechlabs.xtrade.plugin.tc.gentrade.stdimpls.TradingSystemImpl",
            "getBizDate",
            new Class[] {},
            new Timestamp(WEB3DateUtility.getDate("20070721", "yyyyMMdd").getTime()));

        try
        {
            TestDBUtility.deleteAll(ProductRow.TYPE);
            TestDBUtility.insertWithDel(l_productParams);
            
            TestDBUtility.deleteAll(TradedProductRow.TYPE);
            TestDBUtility.insertWithDel(l_tradedProductParams);
            
            TestDBUtility.deleteAll(IfoProductRow.TYPE);
            TestDBUtility.insertWithDel(l_ifoProductParams);
            
            TestDBUtility.deleteAll(IfoTradedProductRow.TYPE);
            TestDBUtility.insertWithDel(l_ifoTradedProductParams);
            
            TestDBUtility.deleteAll(TradingTimeRow.TYPE);
            WEB3GentradeTradingTimeManagementForMock.mockGetOrderBizDate(date);
            
            TestDBUtility.deleteAll(IfoTradedProductUpdqRow.TYPE);
            TestDBUtility.insertWithDel(l_ifoTradedProductUpdqParams);
        }
        catch (WEB3BaseException l_ex)
        {
            log.error(l_ex.getMessage(), l_ex);
            fail();
        }

        try
        {
            WEB3IfoTradedProductImpl l_tradedProductImpl =
                new WEB3IfoTradedProductImpl(1006160060009L);
            
            IfoTradedProductUpdqRow l_outIfoTradedProductUpdqRow =
                l_tradedProductImpl.getIfoTradedProduct();
            
        }
        catch (WEB3BaseException l_ex)
        {
            assertEquals(WEB3ErrorCatalog.SYSTEM_ERROR_80005, l_ex.getErrorInfo());
            log.exiting(STR_METHOD_NAME);
        }
        catch (Exception l_ex)
        {
            log.error(l_ex.getMessage(), l_ex);
            fail();
        }
        log.exiting(STR_METHOD_NAME);
    }
}
@
