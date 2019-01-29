head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.04.07.02.15.17;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	5444d9d078c63f4;
filename	WEB3MutualFundTradingTimeManagementTest.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@package webbroker3.mf;

import java.sql.Timestamp;
import java.util.Calendar;

import com.fitechlabs.xtrade.kernel.util.ThreadLocalSystemAttributesRegistry;
import com.fitechlabs.xtrade.plugin.tc.gentrade.Institution;
import com.fitechlabs.xtrade.plugin.tc.gentrade.ProductTypeEnum;
import com.fitechlabs.xtrade.plugin.tc.gentrade.data.InstitutionParams;
import com.fitechlabs.xtrade.plugin.tc.gentrade.data.InstitutionRow;
import com.fitechlabs.xtrade.plugin.tc.gentrade.data.ProductParams;
import com.fitechlabs.xtrade.plugin.tc.gentrade.data.ProductRow;
import com.fitechlabs.xtrade.plugin.tc.xbmf.MutualFundTypeEnum;
import com.fitechlabs.xtrade.plugin.tc.xbmf.data.MutualFundProductDao;
import com.fitechlabs.xtrade.plugin.tc.xbmf.data.MutualFundProductParams;
import com.fitechlabs.xtrade.plugin.tc.xbmf.data.MutualFundProductRow;

import test.util.TestDBUtility;

import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.common.WEB3SystemLayerException;
import webbroker3.gentrade.WEB3GentradeTradingClendarContext;
import webbroker3.gentrade.WEB3GentradeTradingTimeManagement;
import webbroker3.gentrade.data.CalendarParams;
import webbroker3.gentrade.data.CalendarRow;
import webbroker3.mock.TestBaseForMock;
import webbroker3.util.WEB3DateUtility;
import webbroker3.util.WEB3LogUtility;

/**
 * 
 * @@author tang-xingfeng
 *
 */
public class WEB3MutualFundTradingTimeManagementTest extends TestBaseForMock
{
    /**
     * ログ出力ユーティリティ。
     */
    private static WEB3LogUtility log =
        WEB3LogUtility.getInstance(WEB3MutualFundTradingTimeManagementTest.class);
    WEB3MutualFundTradingTimeManagement l_timeManagement =
        new WEB3MutualFundTradingTimeManagement();

    public WEB3MutualFundTradingTimeManagementTest(String arg0)
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
     * Test method for 'webbroker3.mf.WEB3MutualFundTradingTimeManagement.isSubmitMarketTrigger(String)'
     */
    public void testIsSubmitMarketTrigger_case1()
    {
        final String STR_METHOD_NAME = "testIsSubmitMarketTrigger_case1()";
        log.entering(STR_METHOD_NAME);

        try
        {            
            Timestamp l_timestamp =
                new Timestamp(WEB3DateUtility.getDate("20070213","yyyyMMdd").getTime());
            WEB3GentradeTradingClendarContext l_clendarContext =
                new WEB3GentradeTradingClendarContext();
            l_clendarContext.setInstitutionCode("6D");
            l_clendarContext.setProductCode("0001000");

            ThreadLocalSystemAttributesRegistry.setAttribute(
                WEB3GentradeTradingTimeManagement.TIMESTAMP_TAG, l_timestamp);
            ThreadLocalSystemAttributesRegistry.setAttribute(
                WEB3GentradeTradingTimeManagement.TRADING_CAL_CONTEXT_PATH,
                l_clendarContext);

            MutualFundProductParams l_mutualFundProductParams =
                TestDBUtility.getMutualFundProductRow();
            l_mutualFundProductParams.setInstitutionCode("40");

            InstitutionParams l_institutionParams =
                TestDBUtility.getInstitutionRow();
            l_institutionParams.setInstitutionCode("6D");
            l_institutionParams.setInstitutionId(63);

            TestDBUtility.deleteAll(MutualFundProductRow.TYPE);
            TestDBUtility.deleteAll(InstitutionRow.TYPE);

            TestDBUtility.insertWithDel(l_mutualFundProductParams);
            TestDBUtility.insertWithDel(l_institutionParams);

            l_timeManagement.isSubmitMarketTrigger("1");
        }
        catch (WEB3SystemLayerException l_sle)
        {
            log.debug(STR_METHOD_NAME, l_sle);
            assertEquals(l_sle.getErrorInfo(),WEB3ErrorCatalog.SYSTEM_ERROR_80005);
            log.info(STR_METHOD_NAME + ">>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>test pass !!");
        }
        catch (Exception l_ex)
        {
            log.error(l_ex.getMessage(), l_ex);
            fail();
        }

        log.exiting(STR_METHOD_NAME);
    }

    public void testIsSubmitMarketTrigger_case2()
    {
        final String STR_METHOD_NAME = "testIsSubmitMarketTrigger_case2()";
        log.entering(STR_METHOD_NAME);

        try
        {            
            Timestamp l_timestamp =
                new Timestamp(WEB3DateUtility.getDate("20090214100000","yyyyMMddHHmmss").getTime());
            WEB3GentradeTradingClendarContext l_clendarContext =
                new WEB3GentradeTradingClendarContext();
            l_clendarContext.setInstitutionCode("6D");
            l_clendarContext.setProductCode("0001000");

            ThreadLocalSystemAttributesRegistry.setAttribute(
                WEB3GentradeTradingTimeManagement.TIMESTAMP_TAG, l_timestamp);
            ThreadLocalSystemAttributesRegistry.setAttribute(
                WEB3GentradeTradingTimeManagement.TRADING_CAL_CONTEXT_PATH,
                l_clendarContext);

            MutualFundProductParams l_mutualFundProductParams =
                TestDBUtility.getMutualFundProductRow();
            l_mutualFundProductParams.setProductId(3304148080000L);
            l_mutualFundProductParams.setInstitutionCode("6D");
            l_mutualFundProductParams.setFundType(MutualFundTypeEnum.OTHER);
            l_mutualFundProductParams.setCurrencyCode("A3");

            InstitutionParams l_institutionParams =
                TestDBUtility.getInstitutionRow();
            l_institutionParams.setInstitutionCode("6D");
            l_institutionParams.setInstitutionId(63);

            ProductParams l_productParams =
                TestDBUtility.getProductRow();
            l_productParams.setProductId(3304148080000L);
            l_productParams.setInstitutionCode("6D");
            l_productParams.setProductType(ProductTypeEnum.MUTUAL_FUND);

            CalendarParams l_calendarParams =
                this.getCalendarRow();

            TestDBUtility.deleteAll(MutualFundProductRow.TYPE);
            TestDBUtility.deleteAll(InstitutionRow.TYPE);
            TestDBUtility.deleteAll(ProductRow.TYPE);
            TestDBUtility.deleteAll(CalendarRow.TYPE);

            TestDBUtility.insertWithDel(l_mutualFundProductParams);
            TestDBUtility.insertWithDel(l_institutionParams);
            TestDBUtility.insertWithDel(l_productParams);
            TestDBUtility.insertWithDel(l_calendarParams);

            MutualFundProductRow l_mutualFundProductRow =
                MutualFundProductDao.findRowByPk(3304148080000L);
                
            WEB3MutualFundProduct l_mutualFundProduct =
                new WEB3MutualFundProduct(l_mutualFundProductRow);
            MOCK_MANAGER.setIsMockUsed(true);
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                "webbroker3.mf.WEB3MutualFundProductManager",
                "getMutualFundProduct",
                new Class[] {Institution.class, String.class, String.class},
                l_mutualFundProduct);
            boolean l_blnReturn = l_timeManagement.isSubmitMarketTrigger("1");
            assertFalse(l_blnReturn);
        }
        catch (Exception l_ex)
        {
            log.error(l_ex.getMessage(), l_ex);
            fail();
        }

        log.exiting(STR_METHOD_NAME);
    }

    public void testIsSubmitMarketTrigger_case3()
    {
        final String STR_METHOD_NAME = "testIsSubmitMarketTrigger_case3()";
        log.entering(STR_METHOD_NAME);

        try
        {            
            Timestamp l_timestamp =
                new Timestamp(WEB3DateUtility.getDate("20070213100000","yyyyMMddHHmmss").getTime());
            WEB3GentradeTradingClendarContext l_clendarContext =
                new WEB3GentradeTradingClendarContext();
            l_clendarContext.setInstitutionCode("6D");
            l_clendarContext.setProductCode("0001000");

            ThreadLocalSystemAttributesRegistry.setAttribute(
                WEB3GentradeTradingTimeManagement.TIMESTAMP_TAG, l_timestamp);
            ThreadLocalSystemAttributesRegistry.setAttribute(
                WEB3GentradeTradingTimeManagement.TRADING_CAL_CONTEXT_PATH,
                l_clendarContext);

            MutualFundProductParams l_mutualFundProductParams =
                TestDBUtility.getMutualFundProductRow();
            l_mutualFundProductParams.setProductId(3304148080000L);
            l_mutualFundProductParams.setInstitutionCode("6D");
            l_mutualFundProductParams.setFundType(MutualFundTypeEnum.FOREIGN_MMF);

            InstitutionParams l_institutionParams =
                TestDBUtility.getInstitutionRow();
            l_institutionParams.setInstitutionCode("6D");
            l_institutionParams.setInstitutionId(63);

            ProductParams l_productParams =
                TestDBUtility.getProductRow();
            l_productParams.setProductId(3304148080000L);
            l_productParams.setInstitutionCode("6D");
            l_productParams.setProductType(ProductTypeEnum.MUTUAL_FUND);

            TestDBUtility.deleteAll(MutualFundProductRow.TYPE);
            TestDBUtility.deleteAll(InstitutionRow.TYPE);
            TestDBUtility.deleteAll(ProductRow.TYPE);

            TestDBUtility.insertWithDel(l_mutualFundProductParams);
            TestDBUtility.insertWithDel(l_institutionParams);
            TestDBUtility.insertWithDel(l_productParams);

            MutualFundProductRow l_mutualFundProductRow =
                MutualFundProductDao.findRowByPk(3304148080000L);
                
            WEB3MutualFundProduct l_mutualFundProduct =
                new WEB3MutualFundProduct(l_mutualFundProductRow);
            MOCK_MANAGER.setIsMockUsed(true);
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                "webbroker3.mf.WEB3MutualFundProductManager",
                "getMutualFundProduct",
                new Class[] {Institution.class, String.class, String.class},
                l_mutualFundProduct);
            boolean l_blnReturn = l_timeManagement.isSubmitMarketTrigger("1");
            assertFalse(l_blnReturn);
        }
        catch (Exception l_ex)
        {
            log.error(l_ex.getMessage(), l_ex);
            fail();
        }

        log.exiting(STR_METHOD_NAME);
    }

    public void testIsSubmitMarketTrigger_case4()
    {
        final String STR_METHOD_NAME = "testIsSubmitMarketTrigger_case4()";
        log.entering(STR_METHOD_NAME);

        try
        {            
            Timestamp l_timestamp =
                new Timestamp(WEB3DateUtility.getDate("20070213100000","yyyyMMddHHmmss").getTime());
            WEB3GentradeTradingClendarContext l_clendarContext =
                new WEB3GentradeTradingClendarContext();
            l_clendarContext.setInstitutionCode("6D");
            l_clendarContext.setProductCode("0001000");

            ThreadLocalSystemAttributesRegistry.setAttribute(
                WEB3GentradeTradingTimeManagement.TIMESTAMP_TAG, l_timestamp);
            ThreadLocalSystemAttributesRegistry.setAttribute(
                WEB3GentradeTradingTimeManagement.TRADING_CAL_CONTEXT_PATH,
                l_clendarContext);

            MutualFundProductParams l_mutualFundProductParams =
                TestDBUtility.getMutualFundProductRow();
            l_mutualFundProductParams.setProductId(3304148080000L);
            l_mutualFundProductParams.setInstitutionCode("6D");

            InstitutionParams l_institutionParams =
                TestDBUtility.getInstitutionRow();
            l_institutionParams.setInstitutionCode("6D");
            l_institutionParams.setInstitutionId(63);

            ProductParams l_productParams =
                TestDBUtility.getProductRow();
            l_productParams.setProductId(3304148080000L);
            l_productParams.setInstitutionCode("6D");
            l_productParams.setProductType(ProductTypeEnum.MUTUAL_FUND);

            TestDBUtility.deleteAll(MutualFundProductRow.TYPE);
            TestDBUtility.deleteAll(InstitutionRow.TYPE);
            TestDBUtility.deleteAll(ProductRow.TYPE);

            TestDBUtility.insertWithDel(l_mutualFundProductParams);
            TestDBUtility.insertWithDel(l_institutionParams);
            TestDBUtility.insertWithDel(l_productParams);

            MutualFundProductRow l_mutualFundProductRow =
                MutualFundProductDao.findRowByPk(3304148080000L);
                
            WEB3MutualFundProduct l_mutualFundProduct =
                new WEB3MutualFundProduct(l_mutualFundProductRow);
            MOCK_MANAGER.setIsMockUsed(true);
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                "webbroker3.mf.WEB3MutualFundProductManager",
                "getMutualFundProduct",
                new Class[] {Institution.class, String.class, String.class},
                l_mutualFundProduct);
            l_timeManagement.isSubmitMarketTrigger("1");
        }
        catch (WEB3SystemLayerException l_sle)
        {
            log.debug(STR_METHOD_NAME, l_sle);
            assertEquals(l_sle.getErrorInfo(),WEB3ErrorCatalog.SYSTEM_ERROR_80006);
            log.info(STR_METHOD_NAME + ">>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>test pass !!");
        }
        catch (Exception l_ex)
        {
            log.error(l_ex.getMessage(), l_ex);
            fail();
        }

        log.exiting(STR_METHOD_NAME);
    }

    public static CalendarParams getCalendarRow()
    {
        CalendarParams l_calendarParams = new CalendarParams();

        //日付,holiday,Notnull,
        l_calendarParams.setHoliday(WEB3DateUtility.getDate("20070213100000","yyyyMMddHHmmss"));
        //営業日区分,biz_date_type,1,Notnull,
        l_calendarParams.setBizDateType("2");
        //作成日付,created_timestamp,Notnull,
        l_calendarParams.setCreatedTimestamp(Calendar.getInstance().getTime());
        //更新日付,last_updated_timestamp,Notnull,
        l_calendarParams.setLastUpdatedTimestamp(Calendar.getInstance().getTime());

        return l_calendarParams;
    }
}
@
