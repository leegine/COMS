head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.04.07.01.02.40;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	5444d9d078c63f4;
filename	WEB3BondProductManagerTest.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@package webbroker3.bd;

import java.sql.Timestamp;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import com.fitechlabs.xtrade.kernel.data.DataFindException;
import com.fitechlabs.xtrade.kernel.data.DataNetworkException;
import com.fitechlabs.xtrade.kernel.data.DataQueryException;
import com.fitechlabs.xtrade.kernel.data.Processors;
import com.fitechlabs.xtrade.kernel.data.QueryProcessor;
import com.fitechlabs.xtrade.kernel.util.ThreadLocalSystemAttributesRegistry;
import com.fitechlabs.xtrade.plugin.tc.gentrade.GtlUtils;
import com.fitechlabs.xtrade.plugin.tc.gentrade.Institution;
import com.fitechlabs.xtrade.plugin.tc.gentrade.ProductTypeEnum;
import com.fitechlabs.xtrade.plugin.tc.gentrade.data.BranchParams;
import com.fitechlabs.xtrade.plugin.tc.gentrade.data.InstitutionParams;
import com.fitechlabs.xtrade.plugin.tc.gentrade.data.ProductParams;
import com.fitechlabs.xtrade.plugin.tc.gentrade.data.ProductRow;
import com.fitechlabs.xtrade.plugin.tc.gentrade.stdimpls.InstitutionImpl;
import com.fitechlabs.xtrade.plugin.tc.xbbd.data.BondProductParams;

import test.util.TestDBUtility;

import webbroker3.bd.data.BondBranchRecruitLimitParams;
import webbroker3.bd.data.BondBranchRecruitLimitRow;
import webbroker3.bd.data.BondIssueCouponTypeParams;
import webbroker3.bd.data.BondOrderAcceptActionParams;
import webbroker3.bd.message.WEB3AdminBondProductUpdateInfo;
import webbroker3.bd.message.WEB3BondDomesticBranchRecruitLimitInfo;
import webbroker3.bd.message.WEB3BondDomesticProductUpdateInfo;

import webbroker3.bond.data.BondProductRow;
import webbroker3.common.WEB3BaseException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.common.WEB3Exception;
import webbroker3.common.WEB3SystemLayerException;
import webbroker3.common.define.WEB3BondTradeTypeDef;
import webbroker3.gentrade.WEB3GentradeBizDate;
import webbroker3.gentrade.WEB3GentradeTradingClendarContext;
import webbroker3.gentrade.WEB3GentradeTradingTimeManagement;
import webbroker3.gentrade.WEB3GentradeTradingTimeManagementForMock;
import webbroker3.gentrade.data.CalendarParams;
import webbroker3.gentrade.data.TradingTimeParams;
import webbroker3.gentrade.data.TradingTimeRow;
import webbroker3.mock.TestBaseForMock;
import webbroker3.util.WEB3DateUtility;
import webbroker3.util.WEB3LogUtility;


/**
 * (債券プロダクトマネージャ)<BR>
 * 債券プロダクトマネージャクラス。 <BR>
 *
 * @@author 何文敏
 * @@version 1.0
 */
public class WEB3BondProductManagerTest extends TestBaseForMock
{
    /**
     *
     */
    private static WEB3LogUtility log =
        WEB3LogUtility.getInstance(WEB3BondProductManagerTest.class);

    /**
     *
     */
    WEB3BondProductManager l_manager = new WEB3BondProductManager();

    /**
     *
     * @@param arg0
     */
    public WEB3BondProductManagerTest(String arg0)
    {
        super(arg0);
    }

    /**
     *
     */
    protected void setUp() throws Exception
    {
        super.setUp();
        MOCK_MANAGER.setIsMockUsed(true);
    }

    /**
     *
     */
    protected void tearDown() throws Exception
    {
        super.tearDown();
    }

    public void testUpdateBondProductContent()
    {
        final String STR_METHOD_NAME = "testUpdateBondProductContent()";
        log.entering(STR_METHOD_NAME);

        try
        {
            TestDBUtility.deleteAll(BondProductRow.TYPE);
            BondProductParams l_bondProductParams = TestDBUtility.getBondProductRow();
            TestDBUtility.insertWithDel(l_bondProductParams);

            TestDBUtility.deleteAll(ProductRow.TYPE);
            ProductParams l_productParams = TestDBUtility.getProductRow();
            l_productParams.setProductId(3304140763000L);
            l_productParams.setProductType(ProductTypeEnum.BOND);
            TestDBUtility.insertWithDel(l_productParams);

            TestDBUtility.deleteAll(TradingTimeRow.TYPE);
            TradingTimeParams l_tradingTimeParams = TestDBUtility.getTradingTimeRow();
            l_tradingTimeParams.setBizDateType("1");
            l_tradingTimeParams.setStartTime("020701");
            TestDBUtility.insertWithDel(l_tradingTimeParams);

            WEB3GentradeTradingClendarContext l_clendarContext =
                new WEB3GentradeTradingClendarContext();
            l_clendarContext.setInstitutionCode("0D");
            l_clendarContext.setBranchCode("381");
            l_clendarContext.setMarketCode("SP");
            l_clendarContext.setTradingTimeType("26");
            l_clendarContext.setProductCode("0");
            ThreadLocalSystemAttributesRegistry.setAttribute(
                    WEB3GentradeTradingTimeManagement.TRADING_CAL_CONTEXT_PATH,
                    l_clendarContext);

            WEB3BondDomesticProductUpdateInfo l_bondDomesticProductUpdateInfo =
                new WEB3BondDomesticProductUpdateInfo();
            l_bondDomesticProductUpdateInfo.applyUnit = "1";
            l_bondDomesticProductUpdateInfo.dealingType = "2";
            l_bondDomesticProductUpdateInfo.deliveryDate = WEB3DateUtility.getDate("070201","yyyyMMdd");
            l_bondDomesticProductUpdateInfo.maxFaceAmount = "10";
            l_bondDomesticProductUpdateInfo.minFaceAmount = "1";
            l_bondDomesticProductUpdateInfo.productNameWEB3 = "sdfsdf";
            l_bondDomesticProductUpdateInfo.prospectusCheckDiv = "1";
            l_bondDomesticProductUpdateInfo.recruitEndDateInterNet = WEB3DateUtility.getDate("20070201","yyyyMMdd");
            l_bondDomesticProductUpdateInfo.recruitEndDateWEB3 = WEB3DateUtility.getDate("20070201","yyyyMMdd");
            l_bondDomesticProductUpdateInfo.recruitStartDateInterNet = WEB3DateUtility.getDate("20070201","yyyyMMdd");
            l_bondDomesticProductUpdateInfo.recruitStartDateWEB3 = WEB3DateUtility.getDate("20070201","yyyyMMdd");
            l_bondDomesticProductUpdateInfo.tradeHandleDiv = "0";

            Timestamp l_tsOrderAcceptDate = WEB3GentradeTradingTimeManagementForMock.getTimestampTag();
            String l_strExpectValue = "1";
            WEB3GentradeTradingTimeManagementForMock.mockGetBizDateType(l_tsOrderAcceptDate, l_strExpectValue);

            l_manager.updateBondProductContent("3304140763000", l_bondDomesticProductUpdateInfo, "0012");
        }
        catch (Exception l_ex)
        {
            log.error(l_ex.getMessage(), l_ex);
            fail();
        }
        log.exiting(STR_METHOD_NAME);
    }
    
    public void testUpdateBondProductContent_case0001()
    {
        final String STR_METHOD_NAME = "testUpdateBondProductContent_case0001()";
        log.entering(STR_METHOD_NAME);

        try
        {
            WEB3BondDomesticProductUpdateInfo l_bondDomesticProductUpdateInfo = null;

            l_manager.updateBondProductContent(null, l_bondDomesticProductUpdateInfo, "0012");
        }
        catch (WEB3BaseException l_ex)
        {
            log.error(l_ex.getMessage(), l_ex);
            assertEquals(l_ex.getErrorInfo(), WEB3ErrorCatalog.SYSTEM_ERROR_80017);
        }
        catch (Exception l_ex)
        {
            log.error(l_ex.getMessage(), l_ex);
            fail();
        }
        log.exiting(STR_METHOD_NAME);
    }

    public void testGetIssueCouponTypeT_01()
    {
        final String STR_METHOD_NAME = "testGetIssueCouponTypeT_01()";
        log.entering(STR_METHOD_NAME);
        try
        {
            //BondIssueCouponTypeRow
            TestDBUtility.deleteAll(BondIssueCouponTypeParams.TYPE);

            String l_strProductID = "123456789";
            String[] l_strResult = l_manager.getIssueCouponType(l_strProductID);
            assertEquals(0, l_strResult.length);
        }
        catch(Exception l_exc)
        {
            log.exiting(STR_METHOD_NAME);
            fail();
        }
        log.exiting(STR_METHOD_NAME);
    }

    public void testGetIssueCouponTypeT_02()
    {
        final String STR_METHOD_NAME = "testGetIssueCouponTypeT_02()";
        log.entering(STR_METHOD_NAME);
        try
        {
            //BondIssueCouponTypeRow
            TestDBUtility.deleteAll(BondIssueCouponTypeParams.TYPE);
            BondIssueCouponTypeParams l_couponTypeParams =
                this.getBondIssueCouponTypeParams();
            TestDBUtility.insertWithDel(l_couponTypeParams);

            String l_strProductID = "123456789";
            String[] l_strResult = l_manager.getIssueCouponType(l_strProductID);
            assertEquals(1, l_strResult.length);
            assertEquals("1", l_strResult[0]);
        }
        catch(Exception l_exc)
        {
            log.exiting(STR_METHOD_NAME);
            fail();
        }
        log.exiting(STR_METHOD_NAME);
    }

    public void testGetIssueCouponTypeT_03()
    {
        final String STR_METHOD_NAME = "testGetIssueCouponTypeT_03()";
        log.entering(STR_METHOD_NAME);
        try
        {
            //BondIssueCouponTypeRow  111
            TestDBUtility.deleteAll(BondIssueCouponTypeParams.TYPE);
            BondIssueCouponTypeParams l_couponTypeParams =
                this.getBondIssueCouponTypeParams();
            l_couponTypeParams.setIssueCouponType("5");
            TestDBUtility.insertWithDel(l_couponTypeParams);

            QueryProcessor l_queryProcessor = Processors.getDefaultProcessor();
            //22222
            l_couponTypeParams.setIssueCouponType("2");
            l_queryProcessor.doInsertQuery(l_couponTypeParams);

            //3333
            l_couponTypeParams.setIssueCouponType("4");
            l_queryProcessor.doInsertQuery(l_couponTypeParams);

            String l_strProductID = "123456789";
            String[] l_strResult = l_manager.getIssueCouponType(l_strProductID);
            assertEquals(3, l_strResult.length);
            assertEquals("2", l_strResult[0]);
            assertEquals("4", l_strResult[1]);
            assertEquals("5", l_strResult[2]);
        }
        catch(Exception l_exc)
        {
            log.exiting(STR_METHOD_NAME);
            fail();
        }
        log.exiting(STR_METHOD_NAME);
    }

    public BondIssueCouponTypeParams getBondIssueCouponTypeParams()
    {
        BondIssueCouponTypeParams l_params = new BondIssueCouponTypeParams();

        l_params.setProductId(123456789l);
        l_params.setIssueCouponType("1");
        l_params.setLastUpdater("20060203");
        Timestamp l_time = GtlUtils.getSystemTimestamp();
        l_params.setLastUpdatedTimestamp(l_time);
        l_params.setCreatedTimestamp(l_time);
        return l_params;
    }

    /**
     *
     */
    public void testValidateProductSpec_case0001()
    {
        final String STR_METHOD_NAME = " testValidateProductSpec_case0001()";
        log.entering(STR_METHOD_NAME);

        WEB3BondProductManager l_bondProductManager = new WEB3BondProductManager();

        String l_strProductId = null;
        WEB3BondDomesticProductUpdateInfo l_bondDomesticProductUpdateInfo =
            new WEB3BondDomesticProductUpdateInfo();

        try
        {
            l_bondProductManager.validateProductSpec(
                l_strProductId, l_bondDomesticProductUpdateInfo);

            log.exiting(STR_METHOD_NAME);
            fail();
        }
        catch (WEB3BaseException l_exBE)
        {
            assertEquals(WEB3ErrorCatalog.SYSTEM_ERROR_80017, l_exBE.getErrorInfo());

            log.error(l_exBE.getMessage(), l_exBE);
            log.exiting(STR_METHOD_NAME);
        }
        catch (Exception l_exE)
        {
            log.error(l_exE.getMessage(), l_exE);
            log.exiting(STR_METHOD_NAME);

            fail();
        }

        log.exiting(STR_METHOD_NAME);
    }

    /**
     *
     */
    public void testValidateProductSpec_case0002()
    {
        final String STR_METHOD_NAME = " testValidateProductSpec_case0002()";
        log.entering(STR_METHOD_NAME);

        WEB3BondProductManager l_bondProductManager = new WEB3BondProductManager();

        String l_strProductId = "";
        WEB3BondDomesticProductUpdateInfo l_bondDomesticProductUpdateInfo = null;

        try
        {
            l_bondProductManager.validateProductSpec(
                l_strProductId, l_bondDomesticProductUpdateInfo);

            log.exiting(STR_METHOD_NAME);
            fail();
        }
        catch (WEB3BaseException l_exBE)
        {
            assertEquals(WEB3ErrorCatalog.SYSTEM_ERROR_80017, l_exBE.getErrorInfo());

            log.error(l_exBE.getMessage(), l_exBE);
            log.exiting(STR_METHOD_NAME);
        }
        catch (Exception l_exE)
        {
            log.error(l_exE.getMessage(), l_exE);
            log.exiting(STR_METHOD_NAME);

            fail();
        }

        log.exiting(STR_METHOD_NAME);
    }

    /**
     *
     */
    public void testValidateProductSpec_case0003()
    {
        final String STR_METHOD_NAME = " testValidateProductSpec_case0003()";
        log.entering(STR_METHOD_NAME);

        try
        {
            TestDBUtility.deleteAllAndCommit(BondProductParams.TYPE);
        }
        catch (Exception l_exE)
        {
            log.error(l_exE.getMessage(), l_exE);
            log.exiting(STR_METHOD_NAME);

            fail();
        }

        try
        {
            BondProductParams l_bondProductParams = TestDBUtility.getBondProductRow();
            TestDBUtility.insertWithDelAndCommit(l_bondProductParams);
        }
        catch (Exception l_exE)
        {
            log.error(l_exE.getMessage(), l_exE);
            log.exiting(STR_METHOD_NAME);

            fail();
        }

        WEB3BondProductManager l_bondProductManager = new WEB3BondProductManager();

        String l_strProductId = String.valueOf("123");

        WEB3BondDomesticProductUpdateInfo l_bondDomesticProductUpdateInfo =
            new WEB3BondDomesticProductUpdateInfo();

        try
        {
            l_bondProductManager.validateProductSpec(
                l_strProductId, l_bondDomesticProductUpdateInfo);

            log.exiting(STR_METHOD_NAME);
            fail();
        }
        catch (WEB3BaseException l_exBE)
        {
            assertEquals(WEB3ErrorCatalog.SYSTEM_ERROR_80005, l_exBE.getErrorInfo());

            log.error(l_exBE.getMessage(), l_exBE);
            log.exiting(STR_METHOD_NAME);
        }
        catch (Exception l_exE)
        {
            log.error(l_exE.getMessage(), l_exE);
            log.exiting(STR_METHOD_NAME);

            fail();
        }
        finally
        {
            try
            {
                TestDBUtility.deleteAllAndCommit(BondProductParams.TYPE);
            }
            catch (Exception l_exE)
            {
                log.error(l_exE.getMessage(), l_exE);
                log.exiting(STR_METHOD_NAME);

                fail();
            }
        }
    }

    /**
     *
     */
    public void testValidateProductSpec_case0004()
    {
        final String STR_METHOD_NAME = " testValidateProductSpec_case0004()";
        log.entering(STR_METHOD_NAME);

        CalendarParams l_calendarParams = new CalendarParams();
        l_calendarParams.setHoliday(WEB3DateUtility.getDate("20040710", "yyyyMMdd"));
        l_calendarParams.setBizDateType("0");
        l_calendarParams.setCreatedTimestamp(WEB3DateUtility.getDate("20040709", "yyyyMMdd"));
        l_calendarParams.setLastUpdatedTimestamp(WEB3DateUtility.getDate("20040709", "yyyyMMdd"));

        try
        {
            TestDBUtility.deleteAllAndCommit(BondProductParams.TYPE);
            TestDBUtility.deleteAllAndCommit(CalendarParams.TYPE);

            TestDBUtility.insertWithDelAndCommit(l_calendarParams);
        }
        catch (Exception l_exE)
        {
            log.error(l_exE.getMessage(), l_exE);
            log.exiting(STR_METHOD_NAME);

            fail();
        }

        try
        {
            BondProductParams l_bondProductParams = TestDBUtility.getBondProductRow();
            TestDBUtility.insertWithDelAndCommit(l_bondProductParams);
        }
        catch (Exception l_exE)
        {
            log.error(l_exE.getMessage(), l_exE);
            log.exiting(STR_METHOD_NAME);

            fail();
        }

        WEB3BondProductManager l_bondProductManager = new WEB3BondProductManager();

        String l_strProductId =
            String.valueOf(TestDBUtility.getBondProductRow().getProductId());

        WEB3BondDomesticProductUpdateInfo l_bondDomesticProductUpdateInfo =
            new WEB3BondDomesticProductUpdateInfo();
        l_bondDomesticProductUpdateInfo.recruitStartDateWEB3 =
            WEB3DateUtility.getDate("20040710", "yyyyMMdd");

        try
        {
            l_bondProductManager.validateProductSpec(
                l_strProductId, l_bondDomesticProductUpdateInfo);

            log.exiting(STR_METHOD_NAME);
            fail();
        }
        catch (WEB3BaseException l_exBE)
        {
            assertEquals(WEB3ErrorCatalog.BUSINESS_ERROR_02856, l_exBE.getErrorInfo());

            log.error(l_exBE.getMessage(), l_exBE);
            log.exiting(STR_METHOD_NAME);
        }
        catch (Exception l_exE)
        {
            log.error(l_exE.getMessage(), l_exE);
            log.exiting(STR_METHOD_NAME);

            fail();
        }
        finally
        {
            try
            {
                TestDBUtility.deleteAllAndCommit(BondProductParams.TYPE);
                TestDBUtility.deleteAllAndCommit(CalendarParams.TYPE);
            }
            catch (Exception l_exE)
            {
                log.error(l_exE.getMessage(), l_exE);
                log.exiting(STR_METHOD_NAME);

                fail();
            }
        }
    }

    /**
     *
     */
    public void testValidateProductSpec_case0005()
    {
        final String STR_METHOD_NAME = " testValidateProductSpec_case0005()";
        log.entering(STR_METHOD_NAME);

        try
        {
            TestDBUtility.deleteAllAndCommit(BondProductParams.TYPE);
        }
        catch (Exception l_exE)
        {
            log.error(l_exE.getMessage(), l_exE);
            log.exiting(STR_METHOD_NAME);

            fail();
        }

        try
        {
            BondProductParams l_bondProductParams = TestDBUtility.getBondProductRow();
            TestDBUtility.insertWithDelAndCommit(l_bondProductParams);
        }
        catch (Exception l_exE)
        {
            log.error(l_exE.getMessage(), l_exE);
            log.exiting(STR_METHOD_NAME);

            fail();
        }

        WEB3BondProductManager l_bondProductManager = new WEB3BondProductManager();

        String l_strProductId =
            String.valueOf(TestDBUtility.getBondProductRow().getProductId());

        WEB3BondDomesticProductUpdateInfo l_bondDomesticProductUpdateInfo =
            new WEB3BondDomesticProductUpdateInfo();
        l_bondDomesticProductUpdateInfo.recruitStartDateWEB3 =
            WEB3DateUtility.getDate("20040711", "yyyyMMdd");

        try
        {
            l_bondProductManager.validateProductSpec(
                l_strProductId, l_bondDomesticProductUpdateInfo);

            log.exiting(STR_METHOD_NAME);
            fail();
        }
        catch (WEB3BaseException l_exBE)
        {
            assertEquals(WEB3ErrorCatalog.BUSINESS_ERROR_02856, l_exBE.getErrorInfo());

            log.error(l_exBE.getMessage(), l_exBE);
            log.exiting(STR_METHOD_NAME);
        }
        catch (Exception l_exE)
        {
            log.error(l_exE.getMessage(), l_exE);
            log.exiting(STR_METHOD_NAME);

            fail();
        }
        finally
        {
            try
            {
                TestDBUtility.deleteAllAndCommit(BondProductParams.TYPE);
            }
            catch (Exception l_exE)
            {
                log.error(l_exE.getMessage(), l_exE);
                log.exiting(STR_METHOD_NAME);

                fail();
            }
        }
    }

    /**
     *
     */
    public void testValidateProductSpec_case0006()
    {
        final String STR_METHOD_NAME = " testValidateProductSpec_case0006()";
        log.entering(STR_METHOD_NAME);

        try
        {
            TestDBUtility.deleteAllAndCommit(BondProductParams.TYPE);
        }
        catch (Exception l_exE)
        {
            log.error(l_exE.getMessage(), l_exE);
            log.exiting(STR_METHOD_NAME);

            fail();
        }

        try
        {
            BondProductParams l_bondProductParams = TestDBUtility.getBondProductRow();
            l_bondProductParams.setHostRecruitStartDate(
                WEB3DateUtility.getDate("20040713", "yyyyMMdd"));
            TestDBUtility.insertWithDelAndCommit(l_bondProductParams);
        }
        catch (Exception l_exE)
        {
            log.error(l_exE.getMessage(), l_exE);
            log.exiting(STR_METHOD_NAME);

            fail();
        }

        WEB3BondProductManager l_bondProductManager = new WEB3BondProductManager();

        String l_strProductId =
            String.valueOf(TestDBUtility.getBondProductRow().getProductId());

        WEB3BondDomesticProductUpdateInfo l_bondDomesticProductUpdateInfo =
            new WEB3BondDomesticProductUpdateInfo();
        l_bondDomesticProductUpdateInfo.recruitStartDateWEB3 =
            WEB3DateUtility.getDate("20040709", "yyyyMMdd");

        try
        {
            l_bondProductManager.validateProductSpec(
                l_strProductId, l_bondDomesticProductUpdateInfo);

            log.exiting(STR_METHOD_NAME);
            fail();
        }
        catch (WEB3BaseException l_exBE)
        {
            assertEquals(WEB3ErrorCatalog.BUSINESS_ERROR_02857, l_exBE.getErrorInfo());

            log.error(l_exBE.getMessage(), l_exBE);
            log.exiting(STR_METHOD_NAME);
        }
        catch (Exception l_exE)
        {
            log.error(l_exE.getMessage(), l_exE);
            log.exiting(STR_METHOD_NAME);

            fail();
        }
        finally
        {
            try
            {
                TestDBUtility.deleteAllAndCommit(BondProductParams.TYPE);
            }
            catch (Exception l_exE)
            {
                log.error(l_exE.getMessage(), l_exE);
                log.exiting(STR_METHOD_NAME);

                fail();
            }
        }
    }

    /**
     *
     */
    public void testValidateProductSpec_case0007()
    {
        final String STR_METHOD_NAME = " testValidateProductSpec_case0007()";
        log.entering(STR_METHOD_NAME);

        try
        {
            TestDBUtility.deleteAllAndCommit(BondProductParams.TYPE);
        }
        catch (Exception l_exE)
        {
            log.error(l_exE.getMessage(), l_exE);
            log.exiting(STR_METHOD_NAME);

            fail();
        }

        try
        {
            BondProductParams l_bondProductParams = TestDBUtility.getBondProductRow();
            l_bondProductParams.setHostRecruitStartDate(
                WEB3DateUtility.getDate("20040709", "yyyyMMdd"));
            TestDBUtility.insertWithDelAndCommit(l_bondProductParams);
        }
        catch (Exception l_exE)
        {
            log.error(l_exE.getMessage(), l_exE);
            log.exiting(STR_METHOD_NAME);

            fail();
        }

        WEB3BondProductManager l_bondProductManager = new WEB3BondProductManager();

        String l_strProductId =
            String.valueOf(TestDBUtility.getBondProductRow().getProductId());

        WEB3BondDomesticProductUpdateInfo l_bondDomesticProductUpdateInfo =
            new WEB3BondDomesticProductUpdateInfo();
        l_bondDomesticProductUpdateInfo.recruitStartDateWEB3 =
            WEB3DateUtility.getDate("20040709", "yyyyMMdd");
        l_bondDomesticProductUpdateInfo.recruitEndDateWEB3 =
            WEB3DateUtility.getDate("20040710", "yyyyMMdd");

        try
        {
            l_bondProductManager.validateProductSpec(
                l_strProductId, l_bondDomesticProductUpdateInfo);

            log.exiting(STR_METHOD_NAME);
            fail();
        }
        catch (WEB3BaseException l_exBE)
        {
            assertEquals(WEB3ErrorCatalog.BUSINESS_ERROR_02858, l_exBE.getErrorInfo());

            log.error(l_exBE.getMessage(), l_exBE);
            log.exiting(STR_METHOD_NAME);
        }
        catch (Exception l_exE)
        {
            log.error(l_exE.getMessage(), l_exE);
            log.exiting(STR_METHOD_NAME);

            fail();
        }
        finally
        {
            try
            {
                TestDBUtility.deleteAllAndCommit(BondProductParams.TYPE);
            }
            catch (Exception l_exE)
            {
                log.error(l_exE.getMessage(), l_exE);
                log.exiting(STR_METHOD_NAME);

                fail();
            }
        }
    }

    /**
     *
     */
    public void testValidateProductSpec_case0008()
    {
        final String STR_METHOD_NAME = " testValidateProductSpec_case0008()";
        log.entering(STR_METHOD_NAME);

        try
        {
            TestDBUtility.deleteAllAndCommit(BondProductParams.TYPE);
        }
        catch (Exception l_exE)
        {
            log.error(l_exE.getMessage(), l_exE);
            log.exiting(STR_METHOD_NAME);

            fail();
        }

        try
        {
            BondProductParams l_bondProductParams = TestDBUtility.getBondProductRow();
            l_bondProductParams.setHostRecruitStartDate(
                WEB3DateUtility.getDate("20040709", "yyyyMMdd"));
            l_bondProductParams.setHostRecruitEndDate(
                WEB3DateUtility.getDate("20040713", "yyyyMMdd"));
            TestDBUtility.insertWithDelAndCommit(l_bondProductParams);
        }
        catch (Exception l_exE)
        {
            log.error(l_exE.getMessage(), l_exE);
            log.exiting(STR_METHOD_NAME);

            fail();
        }

        WEB3BondProductManager l_bondProductManager = new WEB3BondProductManager();

        String l_strProductId =
            String.valueOf(TestDBUtility.getBondProductRow().getProductId());

        WEB3BondDomesticProductUpdateInfo l_bondDomesticProductUpdateInfo =
            new WEB3BondDomesticProductUpdateInfo();
        l_bondDomesticProductUpdateInfo.recruitStartDateWEB3 =
            WEB3DateUtility.getDate("20040709", "yyyyMMdd");
        l_bondDomesticProductUpdateInfo.recruitEndDateWEB3 =
            WEB3DateUtility.getDate("20040713", "yyyyMMdd");

        try
        {
            l_bondProductManager.validateProductSpec(
                l_strProductId, l_bondDomesticProductUpdateInfo);

            log.exiting(STR_METHOD_NAME);
            fail();
        }
        catch (WEB3BaseException l_exBE)
        {
            assertEquals(WEB3ErrorCatalog.BUSINESS_ERROR_02859, l_exBE.getErrorInfo());

            log.error(l_exBE.getMessage(), l_exBE);
            log.exiting(STR_METHOD_NAME);
        }
        catch (Exception l_exE)
        {
            log.error(l_exE.getMessage(), l_exE);
            log.exiting(STR_METHOD_NAME);

            fail();
        }
        finally
        {
            try
            {
                TestDBUtility.deleteAllAndCommit(BondProductParams.TYPE);
            }
            catch (Exception l_exE)
            {
                log.error(l_exE.getMessage(), l_exE);
                log.exiting(STR_METHOD_NAME);

                fail();
            }
        }
    }

    /**
     *
     */
    public void testValidateProductSpec_case0009()
    {
        final String STR_METHOD_NAME = " testValidateProductSpec_case0009()";
        log.entering(STR_METHOD_NAME);

        try
        {
            TestDBUtility.deleteAllAndCommit(BondProductParams.TYPE);
        }
        catch (Exception l_exE)
        {
            log.error(l_exE.getMessage(), l_exE);
            log.exiting(STR_METHOD_NAME);

            fail();
        }

        try
        {
            BondProductParams l_bondProductParams = TestDBUtility.getBondProductRow();
            l_bondProductParams.setHostRecruitStartDate(
                WEB3DateUtility.getDate("20040708", "yyyyMMdd"));
            l_bondProductParams.setHostRecruitEndDate(
                WEB3DateUtility.getDate("20040714", "yyyyMMdd"));
            TestDBUtility.insertWithDelAndCommit(l_bondProductParams);
        }
        catch (Exception l_exE)
        {
            log.error(l_exE.getMessage(), l_exE);
            log.exiting(STR_METHOD_NAME);

            fail();
        }

        WEB3BondProductManager l_bondProductManager = new WEB3BondProductManager();

        String l_strProductId =
            String.valueOf(TestDBUtility.getBondProductRow().getProductId());

        WEB3BondDomesticProductUpdateInfo l_bondDomesticProductUpdateInfo =
            new WEB3BondDomesticProductUpdateInfo();
        l_bondDomesticProductUpdateInfo.recruitStartDateWEB3 =
            WEB3DateUtility.getDate("20040709", "yyyyMMdd");
        l_bondDomesticProductUpdateInfo.recruitEndDateWEB3 =
            WEB3DateUtility.getDate("20040715", "yyyyMMdd");

        try
        {
            l_bondProductManager.validateProductSpec(
                l_strProductId, l_bondDomesticProductUpdateInfo);

            log.exiting(STR_METHOD_NAME);
            fail();
        }
        catch (WEB3BaseException l_exBE)
        {
            assertEquals(WEB3ErrorCatalog.BUSINESS_ERROR_02859, l_exBE.getErrorInfo());

            log.error(l_exBE.getMessage(), l_exBE);
            log.exiting(STR_METHOD_NAME);
        }
        catch (Exception l_exE)
        {
            log.error(l_exE.getMessage(), l_exE);
            log.exiting(STR_METHOD_NAME);

            fail();
        }
        finally
        {
            try
            {
                TestDBUtility.deleteAllAndCommit(BondProductParams.TYPE);
            }
            catch (Exception l_exE)
            {
                log.error(l_exE.getMessage(), l_exE);
                log.exiting(STR_METHOD_NAME);

                fail();
            }
        }
    }

    /**
     *
     */
    public void testValidateProductSpec_case0010()
    {
        final String STR_METHOD_NAME = " testValidateProductSpec_case0010()";
        log.entering(STR_METHOD_NAME);

        try
        {
            TestDBUtility.deleteAllAndCommit(BondProductParams.TYPE);
        }
        catch (Exception l_exE)
        {
            log.error(l_exE.getMessage(), l_exE);
            log.exiting(STR_METHOD_NAME);

            fail();
        }

        try
        {
            BondProductParams l_bondProductParams = TestDBUtility.getBondProductRow();
            l_bondProductParams.setHostRecruitStartDate(
                WEB3DateUtility.getDate("20040708", "yyyyMMdd"));
            l_bondProductParams.setHostRecruitEndDate(
                WEB3DateUtility.getDate("20040714", "yyyyMMdd"));
            TestDBUtility.insertWithDelAndCommit(l_bondProductParams);
        }
        catch (Exception l_exE)
        {
            log.error(l_exE.getMessage(), l_exE);
            log.exiting(STR_METHOD_NAME);

            fail();
        }

        WEB3BondProductManager l_bondProductManager = new WEB3BondProductManager();

        String l_strProductId =
            String.valueOf(TestDBUtility.getBondProductRow().getProductId());

        WEB3BondDomesticProductUpdateInfo l_bondDomesticProductUpdateInfo =
            new WEB3BondDomesticProductUpdateInfo();
        l_bondDomesticProductUpdateInfo.recruitStartDateWEB3 =
            WEB3DateUtility.getDate("20040715", "yyyyMMdd");
        l_bondDomesticProductUpdateInfo.recruitEndDateWEB3 =
            WEB3DateUtility.getDate("20040709", "yyyyMMdd");

        try
        {
            l_bondProductManager.validateProductSpec(
                l_strProductId, l_bondDomesticProductUpdateInfo);

            log.exiting(STR_METHOD_NAME);
            fail();
        }
        catch (WEB3BaseException l_exBE)
        {
            assertEquals(WEB3ErrorCatalog.BUSINESS_ERROR_02860, l_exBE.getErrorInfo());

            log.error(l_exBE.getMessage(), l_exBE);
            log.exiting(STR_METHOD_NAME);
        }
        catch (Exception l_exE)
        {
            log.error(l_exE.getMessage(), l_exE);
            log.exiting(STR_METHOD_NAME);

            fail();
        }
        finally
        {
            try
            {
                TestDBUtility.deleteAllAndCommit(BondProductParams.TYPE);
            }
            catch (Exception l_exE)
            {
                log.error(l_exE.getMessage(), l_exE);
                log.exiting(STR_METHOD_NAME);

                fail();
            }
        }
    }

    /**
     *
     */
    public void testValidateProductSpec_case0011()
    {
        final String STR_METHOD_NAME = " testValidateProductSpec_case0011()";
        log.entering(STR_METHOD_NAME);

        try
        {
            TestDBUtility.deleteAllAndCommit(BondProductParams.TYPE);
        }
        catch (Exception l_exE)
        {
            log.error(l_exE.getMessage(), l_exE);
            log.exiting(STR_METHOD_NAME);

            fail();
        }

        try
        {
            BondProductParams l_bondProductParams = TestDBUtility.getBondProductRow();
            l_bondProductParams.setHostRecruitStartDate(
                WEB3DateUtility.getDate("20040708", "yyyyMMdd"));
            l_bondProductParams.setHostRecruitEndDate(
                WEB3DateUtility.getDate("20040714", "yyyyMMdd"));
            TestDBUtility.insertWithDelAndCommit(l_bondProductParams);
        }
        catch (Exception l_exE)
        {
            log.error(l_exE.getMessage(), l_exE);
            log.exiting(STR_METHOD_NAME);

            fail();
        }

        WEB3BondProductManager l_bondProductManager = new WEB3BondProductManager();

        String l_strProductId =
            String.valueOf(TestDBUtility.getBondProductRow().getProductId());

        WEB3BondDomesticProductUpdateInfo l_bondDomesticProductUpdateInfo =
            new WEB3BondDomesticProductUpdateInfo();
        l_bondDomesticProductUpdateInfo.recruitStartDateWEB3 =
            WEB3DateUtility.getDate("20040709", "yyyyMMdd");
        l_bondDomesticProductUpdateInfo.recruitEndDateWEB3 =
            WEB3DateUtility.getDate("20040713", "yyyyMMdd");
        l_bondDomesticProductUpdateInfo.recruitStartDateInterNet =
            WEB3DateUtility.getDate("20040710", "yyyyMMdd");

        try
        {
            l_bondProductManager.validateProductSpec(
                l_strProductId, l_bondDomesticProductUpdateInfo);

            log.exiting(STR_METHOD_NAME);
            fail();
        }
        catch (WEB3BaseException l_exBE)
        {
            assertEquals(WEB3ErrorCatalog.BUSINESS_ERROR_02861, l_exBE.getErrorInfo());

            log.error(l_exBE.getMessage(), l_exBE);
            log.exiting(STR_METHOD_NAME);
        }
        catch (Exception l_exE)
        {
            log.error(l_exE.getMessage(), l_exE);
            log.exiting(STR_METHOD_NAME);

            fail();
        }
        finally
        {
            try
            {
                TestDBUtility.deleteAllAndCommit(BondProductParams.TYPE);
            }
            catch (Exception l_exE)
            {
                log.error(l_exE.getMessage(), l_exE);
                log.exiting(STR_METHOD_NAME);

                fail();
            }
        }
    }

    /**
     *
     */
    public void testValidateProductSpec_case0012()
    {
        final String STR_METHOD_NAME = " testValidateProductSpec_case0012()";
        log.entering(STR_METHOD_NAME);

        try
        {
            TestDBUtility.deleteAllAndCommit(BondProductParams.TYPE);
        }
        catch (Exception l_exE)
        {
            log.error(l_exE.getMessage(), l_exE);
            log.exiting(STR_METHOD_NAME);

            fail();
        }

        try
        {
            BondProductParams l_bondProductParams = TestDBUtility.getBondProductRow();
            l_bondProductParams.setHostRecruitStartDate(
                WEB3DateUtility.getDate("20040708", "yyyyMMdd"));
            l_bondProductParams.setHostRecruitEndDate(
                WEB3DateUtility.getDate("20040714", "yyyyMMdd"));
            TestDBUtility.insertWithDelAndCommit(l_bondProductParams);
        }
        catch (Exception l_exE)
        {
            log.error(l_exE.getMessage(), l_exE);
            log.exiting(STR_METHOD_NAME);

            fail();
        }

        WEB3BondProductManager l_bondProductManager = new WEB3BondProductManager();

        String l_strProductId =
            String.valueOf(TestDBUtility.getBondProductRow().getProductId());

        WEB3BondDomesticProductUpdateInfo l_bondDomesticProductUpdateInfo =
            new WEB3BondDomesticProductUpdateInfo();
        l_bondDomesticProductUpdateInfo.recruitStartDateWEB3 =
            WEB3DateUtility.getDate("20040709", "yyyyMMdd");
        l_bondDomesticProductUpdateInfo.recruitEndDateWEB3 =
            WEB3DateUtility.getDate("20040713", "yyyyMMdd");
        l_bondDomesticProductUpdateInfo.recruitStartDateInterNet =
            WEB3DateUtility.getDate("20040706", "yyyyMMdd");

        try
        {
            l_bondProductManager.validateProductSpec(
                l_strProductId, l_bondDomesticProductUpdateInfo);

            log.exiting(STR_METHOD_NAME);
            fail();
        }
        catch (WEB3BaseException l_exBE)
        {
            assertEquals(WEB3ErrorCatalog.BUSINESS_ERROR_02862, l_exBE.getErrorInfo());

            log.error(l_exBE.getMessage(), l_exBE);
            log.exiting(STR_METHOD_NAME);
        }
        catch (Exception l_exE)
        {
            log.error(l_exE.getMessage(), l_exE);
            log.exiting(STR_METHOD_NAME);

            fail();
        }
        finally
        {
            try
            {
                TestDBUtility.deleteAllAndCommit(BondProductParams.TYPE);
            }
            catch (Exception l_exE)
            {
                log.error(l_exE.getMessage(), l_exE);
                log.exiting(STR_METHOD_NAME);

                fail();
            }
        }
    }

    /**
     *
     */
    public void testValidateProductSpec_case0013()
    {
        final String STR_METHOD_NAME = " testValidateProductSpec_case0013()";
        log.entering(STR_METHOD_NAME);

        try
        {
            TestDBUtility.deleteAllAndCommit(BondProductParams.TYPE);
        }
        catch (Exception l_exE)
        {
            log.error(l_exE.getMessage(), l_exE);
            log.exiting(STR_METHOD_NAME);

            fail();
        }

        try
        {
            BondProductParams l_bondProductParams = TestDBUtility.getBondProductRow();
            l_bondProductParams.setHostRecruitStartDate(
                WEB3DateUtility.getDate("20040708", "yyyyMMdd"));
            l_bondProductParams.setHostRecruitEndDate(
                WEB3DateUtility.getDate("20040714", "yyyyMMdd"));
            TestDBUtility.insertWithDelAndCommit(l_bondProductParams);
        }
        catch (Exception l_exE)
        {
            log.error(l_exE.getMessage(), l_exE);
            log.exiting(STR_METHOD_NAME);

            fail();
        }

        WEB3BondProductManager l_bondProductManager = new WEB3BondProductManager();

        String l_strProductId =
            String.valueOf(TestDBUtility.getBondProductRow().getProductId());

        WEB3BondDomesticProductUpdateInfo l_bondDomesticProductUpdateInfo =
            new WEB3BondDomesticProductUpdateInfo();
        l_bondDomesticProductUpdateInfo.recruitStartDateWEB3 =
            WEB3DateUtility.getDate("20040709", "yyyyMMdd");
        l_bondDomesticProductUpdateInfo.recruitEndDateWEB3 =
            WEB3DateUtility.getDate("20040713", "yyyyMMdd");
        l_bondDomesticProductUpdateInfo.recruitStartDateInterNet =
            WEB3DateUtility.getDate("20040709", "yyyyMMdd");
        l_bondDomesticProductUpdateInfo.recruitEndDateInterNet =
            WEB3DateUtility.getDate("20040710", "yyyyMMdd");

        try
        {
            l_bondProductManager.validateProductSpec(
                l_strProductId, l_bondDomesticProductUpdateInfo);

            log.exiting(STR_METHOD_NAME);
            fail();
        }
        catch (WEB3BaseException l_exBE)
        {
            assertEquals(WEB3ErrorCatalog.BUSINESS_ERROR_02863, l_exBE.getErrorInfo());

            log.error(l_exBE.getMessage(), l_exBE);
            log.exiting(STR_METHOD_NAME);
        }
        catch (Exception l_exE)
        {
            log.error(l_exE.getMessage(), l_exE);
            log.exiting(STR_METHOD_NAME);

            fail();
        }
        finally
        {
            try
            {
                TestDBUtility.deleteAllAndCommit(BondProductParams.TYPE);
            }
            catch (Exception l_exE)
            {
                log.error(l_exE.getMessage(), l_exE);
                log.exiting(STR_METHOD_NAME);

                fail();
            }
        }
    }

    /**
     *
     */
    public void testValidateProductSpec_case0014()
    {
        final String STR_METHOD_NAME = " testValidateProductSpec_case0014()";
        log.entering(STR_METHOD_NAME);

        try
        {
            TestDBUtility.deleteAllAndCommit(BondProductParams.TYPE);
        }
        catch (Exception l_exE)
        {
            log.error(l_exE.getMessage(), l_exE);
            log.exiting(STR_METHOD_NAME);

            fail();
        }

        try
        {
            BondProductParams l_bondProductParams = TestDBUtility.getBondProductRow();
            l_bondProductParams.setHostRecruitStartDate(
                WEB3DateUtility.getDate("20040708", "yyyyMMdd"));
            l_bondProductParams.setHostRecruitEndDate(
                WEB3DateUtility.getDate("20040714", "yyyyMMdd"));
            TestDBUtility.insertWithDelAndCommit(l_bondProductParams);
        }
        catch (Exception l_exE)
        {
            log.error(l_exE.getMessage(), l_exE);
            log.exiting(STR_METHOD_NAME);

            fail();
        }

        WEB3BondProductManager l_bondProductManager = new WEB3BondProductManager();

        String l_strProductId =
            String.valueOf(TestDBUtility.getBondProductRow().getProductId());

        WEB3BondDomesticProductUpdateInfo l_bondDomesticProductUpdateInfo =
            new WEB3BondDomesticProductUpdateInfo();
        l_bondDomesticProductUpdateInfo.recruitStartDateWEB3 =
            WEB3DateUtility.getDate("20040709", "yyyyMMdd");
        l_bondDomesticProductUpdateInfo.recruitEndDateWEB3 =
            WEB3DateUtility.getDate("20040713", "yyyyMMdd");
        l_bondDomesticProductUpdateInfo.recruitStartDateInterNet =
            WEB3DateUtility.getDate("20040712", "yyyyMMdd");
        l_bondDomesticProductUpdateInfo.recruitEndDateInterNet =
            WEB3DateUtility.getDate("20040714", "yyyyMMdd");

        try
        {
            l_bondProductManager.validateProductSpec(
                l_strProductId, l_bondDomesticProductUpdateInfo);

            log.exiting(STR_METHOD_NAME);
            fail();
        }
        catch (WEB3BaseException l_exBE)
        {
            assertEquals(WEB3ErrorCatalog.BUSINESS_ERROR_02864, l_exBE.getErrorInfo());

            log.error(l_exBE.getMessage(), l_exBE);
            log.exiting(STR_METHOD_NAME);
        }
        catch (Exception l_exE)
        {
            log.error(l_exE.getMessage(), l_exE);
            log.exiting(STR_METHOD_NAME);

            fail();
        }
        finally
        {
            try
            {
                TestDBUtility.deleteAllAndCommit(BondProductParams.TYPE);
            }
            catch (Exception l_exE)
            {
                log.error(l_exE.getMessage(), l_exE);
                log.exiting(STR_METHOD_NAME);

                fail();
            }
        }
    }

    /**
     *
     */
    public void testValidateProductSpec_case0015()
    {
        final String STR_METHOD_NAME = " testValidateProductSpec_case0015()";
        log.entering(STR_METHOD_NAME);

        try
        {
            TestDBUtility.deleteAllAndCommit(BondProductParams.TYPE);
        }
        catch (Exception l_exE)
        {
            log.error(l_exE.getMessage(), l_exE);
            log.exiting(STR_METHOD_NAME);

            fail();
        }

        try
        {
            BondProductParams l_bondProductParams = TestDBUtility.getBondProductRow();
            l_bondProductParams.setHostRecruitStartDate(
                WEB3DateUtility.getDate("20040708", "yyyyMMdd"));
            l_bondProductParams.setHostRecruitEndDate(
                WEB3DateUtility.getDate("20040714", "yyyyMMdd"));
            TestDBUtility.insertWithDelAndCommit(l_bondProductParams);
        }
        catch (Exception l_exE)
        {
            log.error(l_exE.getMessage(), l_exE);
            log.exiting(STR_METHOD_NAME);

            fail();
        }

        WEB3BondProductManager l_bondProductManager = new WEB3BondProductManager();

        String l_strProductId =
            String.valueOf(TestDBUtility.getBondProductRow().getProductId());

        WEB3BondDomesticProductUpdateInfo l_bondDomesticProductUpdateInfo =
            new WEB3BondDomesticProductUpdateInfo();
        l_bondDomesticProductUpdateInfo.recruitStartDateWEB3 =
            WEB3DateUtility.getDate("20040709", "yyyyMMdd");
        l_bondDomesticProductUpdateInfo.recruitEndDateWEB3 =
            WEB3DateUtility.getDate("20040713", "yyyyMMdd");
        l_bondDomesticProductUpdateInfo.recruitStartDateInterNet =
            WEB3DateUtility.getDate("20040715", "yyyyMMdd");
        l_bondDomesticProductUpdateInfo.recruitEndDateInterNet =
            WEB3DateUtility.getDate("20040709", "yyyyMMdd");

        try
        {
            l_bondProductManager.validateProductSpec(
                l_strProductId, l_bondDomesticProductUpdateInfo);

            log.exiting(STR_METHOD_NAME);
            fail();
        }
        catch (WEB3BaseException l_exBE)
        {
            assertEquals(WEB3ErrorCatalog.BUSINESS_ERROR_02868, l_exBE.getErrorInfo());

            log.error(l_exBE.getMessage(), l_exBE);
            log.exiting(STR_METHOD_NAME);
        }
        catch (Exception l_exE)
        {
            log.error(l_exE.getMessage(), l_exE);
            log.exiting(STR_METHOD_NAME);

            fail();
        }
        finally
        {
            try
            {
                TestDBUtility.deleteAllAndCommit(BondProductParams.TYPE);
            }
            catch (Exception l_exE)
            {
                log.error(l_exE.getMessage(), l_exE);
                log.exiting(STR_METHOD_NAME);

                fail();
            }
        }
    }

    /**
     *
     */
    public void testValidateProductSpec_case0016()
    {
        final String STR_METHOD_NAME = " testValidateProductSpec_case0016()";
        log.entering(STR_METHOD_NAME);

        try
        {
            TestDBUtility.deleteAllAndCommit(BondProductParams.TYPE);
        }
        catch (Exception l_exE)
        {
            log.error(l_exE.getMessage(), l_exE);
            log.exiting(STR_METHOD_NAME);

            fail();
        }

        try
        {
            BondProductParams l_bondProductParams = TestDBUtility.getBondProductRow();
            l_bondProductParams.setHostRecruitStartDate(
                WEB3DateUtility.getDate("20040708", "yyyyMMdd"));
            l_bondProductParams.setHostRecruitEndDate(
                WEB3DateUtility.getDate("20040714", "yyyyMMdd"));
            TestDBUtility.insertWithDelAndCommit(l_bondProductParams);
        }
        catch (Exception l_exE)
        {
            log.error(l_exE.getMessage(), l_exE);
            log.exiting(STR_METHOD_NAME);

            fail();
        }

        WEB3BondProductManager l_bondProductManager = new WEB3BondProductManager();

        String l_strProductId =
            String.valueOf(TestDBUtility.getBondProductRow().getProductId());

        WEB3BondDomesticProductUpdateInfo l_bondDomesticProductUpdateInfo =
            new WEB3BondDomesticProductUpdateInfo();
        l_bondDomesticProductUpdateInfo.recruitStartDateWEB3 =
            WEB3DateUtility.getDate("20040709", "yyyyMMdd");
        l_bondDomesticProductUpdateInfo.recruitEndDateWEB3 =
            WEB3DateUtility.getDate("20040713", "yyyyMMdd");
        l_bondDomesticProductUpdateInfo.recruitStartDateInterNet =
            WEB3DateUtility.getDate("20040709", "yyyyMMdd");
        l_bondDomesticProductUpdateInfo.recruitEndDateInterNet =
            WEB3DateUtility.getDate("20040712", "yyyyMMdd");
        l_bondDomesticProductUpdateInfo.deliveryDate =
            WEB3DateUtility.getDate("20040710", "yyyyMMdd");

        try
        {
            l_bondProductManager.validateProductSpec(
                l_strProductId, l_bondDomesticProductUpdateInfo);

            log.exiting(STR_METHOD_NAME);
            fail();
        }
        catch (WEB3BaseException l_exBE)
        {
            assertEquals(WEB3ErrorCatalog.BUSINESS_ERROR_02336, l_exBE.getErrorInfo());

            log.error(l_exBE.getMessage(), l_exBE);
            log.exiting(STR_METHOD_NAME);
        }
        catch (Exception l_exE)
        {
            log.error(l_exE.getMessage(), l_exE);
            log.exiting(STR_METHOD_NAME);

            fail();
        }
        finally
        {
            try
            {
                TestDBUtility.deleteAllAndCommit(BondProductParams.TYPE);
            }
            catch (Exception l_exE)
            {
                log.error(l_exE.getMessage(), l_exE);
                log.exiting(STR_METHOD_NAME);

                fail();
            }
        }
    }

    /**
     *
     */
    public void testValidateProductSpec_case0017()
    {
        final String STR_METHOD_NAME = " testValidateProductSpec_case0017()";
        log.entering(STR_METHOD_NAME);

        try
        {
            TestDBUtility.deleteAllAndCommit(BondProductParams.TYPE);
        }
        catch (Exception l_exE)
        {
            log.error(l_exE.getMessage(), l_exE);
            log.exiting(STR_METHOD_NAME);

            fail();
        }

        try
        {
            BondProductParams l_bondProductParams = TestDBUtility.getBondProductRow();
            l_bondProductParams.setHostRecruitStartDate(
                WEB3DateUtility.getDate("20040708", "yyyyMMdd"));
            l_bondProductParams.setHostRecruitEndDate(
                WEB3DateUtility.getDate("20040714", "yyyyMMdd"));
            l_bondProductParams.setIssueDate(
                WEB3DateUtility.getDate("20040714", "yyyyMMdd"));
            l_bondProductParams.setMinIssueCouponType("100");
            TestDBUtility.insertWithDelAndCommit(l_bondProductParams);
        }
        catch (Exception l_exE)
        {
            log.error(l_exE.getMessage(), l_exE);
            log.exiting(STR_METHOD_NAME);

            fail();
        }

        WEB3BondProductManager l_bondProductManager = new WEB3BondProductManager();

        String l_strProductId =
            String.valueOf(TestDBUtility.getBondProductRow().getProductId());

        WEB3BondDomesticProductUpdateInfo l_bondDomesticProductUpdateInfo =
            new WEB3BondDomesticProductUpdateInfo();
        l_bondDomesticProductUpdateInfo.recruitStartDateWEB3 =
            WEB3DateUtility.getDate("20040709", "yyyyMMdd");
        l_bondDomesticProductUpdateInfo.recruitEndDateWEB3 =
            WEB3DateUtility.getDate("20040713", "yyyyMMdd");
        l_bondDomesticProductUpdateInfo.recruitStartDateInterNet =
            WEB3DateUtility.getDate("20040709", "yyyyMMdd");
        l_bondDomesticProductUpdateInfo.recruitEndDateInterNet =
            WEB3DateUtility.getDate("20040712", "yyyyMMdd");
        l_bondDomesticProductUpdateInfo.deliveryDate =
            WEB3DateUtility.getDate("20040709", "yyyyMMdd");
        l_bondDomesticProductUpdateInfo.applyUnit = "101";

        try
        {
            l_bondProductManager.validateProductSpec(
                l_strProductId, l_bondDomesticProductUpdateInfo);

            log.exiting(STR_METHOD_NAME);
            fail();
        }
        catch (WEB3BaseException l_exBE)
        {
            assertEquals(WEB3ErrorCatalog.BUSINESS_ERROR_02865, l_exBE.getErrorInfo());

            log.error(l_exBE.getMessage(), l_exBE);
            log.exiting(STR_METHOD_NAME);
        }
        catch (Exception l_exE)
        {
            log.error(l_exE.getMessage(), l_exE);
            log.exiting(STR_METHOD_NAME);

            fail();
        }
        finally
        {
            try
            {
                TestDBUtility.deleteAllAndCommit(BondProductParams.TYPE);
            }
            catch (Exception l_exE)
            {
                log.error(l_exE.getMessage(), l_exE);
                log.exiting(STR_METHOD_NAME);

                fail();
            }
        }
    }

    /**
     *
     */
    public void testValidateProductSpec_case0018()
    {
        final String STR_METHOD_NAME = " testValidateProductSpec_case0018()";
        log.entering(STR_METHOD_NAME);

        try
        {
            TestDBUtility.deleteAllAndCommit(BondProductParams.TYPE);
        }
        catch (Exception l_exE)
        {
            log.error(l_exE.getMessage(), l_exE);
            log.exiting(STR_METHOD_NAME);

            fail();
        }

        try
        {
            BondProductParams l_bondProductParams = TestDBUtility.getBondProductRow();
            l_bondProductParams.setHostRecruitStartDate(
                WEB3DateUtility.getDate("20040708", "yyyyMMdd"));
            l_bondProductParams.setHostRecruitEndDate(
                WEB3DateUtility.getDate("20040714", "yyyyMMdd"));
            l_bondProductParams.setIssueDate(
                WEB3DateUtility.getDate("20040714", "yyyyMMdd"));
            l_bondProductParams.setMinIssueCouponType("100");
            TestDBUtility.insertWithDelAndCommit(l_bondProductParams);
        }
        catch (Exception l_exE)
        {
            log.error(l_exE.getMessage(), l_exE);
            log.exiting(STR_METHOD_NAME);

            fail();
        }

        WEB3BondProductManager l_bondProductManager = new WEB3BondProductManager();

        String l_strProductId =
            String.valueOf(TestDBUtility.getBondProductRow().getProductId());

        WEB3BondDomesticProductUpdateInfo l_bondDomesticProductUpdateInfo =
            new WEB3BondDomesticProductUpdateInfo();
        l_bondDomesticProductUpdateInfo.recruitStartDateWEB3 =
            WEB3DateUtility.getDate("20040708", "yyyyMMdd");
        l_bondDomesticProductUpdateInfo.recruitEndDateWEB3 =
            WEB3DateUtility.getDate("20040709", "yyyyMMdd");
        l_bondDomesticProductUpdateInfo.recruitStartDateInterNet =
            WEB3DateUtility.getDate("20040708", "yyyyMMdd");
        l_bondDomesticProductUpdateInfo.recruitEndDateInterNet =
            WEB3DateUtility.getDate("20040709", "yyyyMMdd");
        l_bondDomesticProductUpdateInfo.deliveryDate =
            WEB3DateUtility.getDate("20040709", "yyyyMMdd");
        l_bondDomesticProductUpdateInfo.applyUnit = "101";

        try
        {
            l_bondProductManager.validateProductSpec(
                l_strProductId, l_bondDomesticProductUpdateInfo);

            log.exiting(STR_METHOD_NAME);
            fail();
        }
        catch (WEB3BaseException l_exBE)
        {
            assertEquals(WEB3ErrorCatalog.BUSINESS_ERROR_02865, l_exBE.getErrorInfo());

            log.error(l_exBE.getMessage(), l_exBE);
            log.exiting(STR_METHOD_NAME);
        }
        catch (Exception l_exE)
        {
            log.error(l_exE.getMessage(), l_exE);
            log.exiting(STR_METHOD_NAME);

            fail();
        }
        finally
        {
            try
            {
                TestDBUtility.deleteAllAndCommit(BondProductParams.TYPE);
            }
            catch (Exception l_exE)
            {
                log.error(l_exE.getMessage(), l_exE);
                log.exiting(STR_METHOD_NAME);

                fail();
            }
        }
    }

    /**
     *
     */
    public void testValidateProductSpec_case0019()
    {
        final String STR_METHOD_NAME = " testValidateProductSpec_case0019()";
        log.entering(STR_METHOD_NAME);

        try
        {
            TestDBUtility.deleteAllAndCommit(BondProductParams.TYPE);
        }
        catch (Exception l_exE)
        {
            log.error(l_exE.getMessage(), l_exE);
            log.exiting(STR_METHOD_NAME);

            fail();
        }

        try
        {
            BondProductParams l_bondProductParams = TestDBUtility.getBondProductRow();
            l_bondProductParams.setHostRecruitStartDate(
                WEB3DateUtility.getDate("20040708", "yyyyMMdd"));
            l_bondProductParams.setHostRecruitEndDate(
                WEB3DateUtility.getDate("20040714", "yyyyMMdd"));
            l_bondProductParams.setIssueDate(
                WEB3DateUtility.getDate("20040714", "yyyyMMdd"));
            l_bondProductParams.setMinIssueCouponType("100");
            TestDBUtility.insertWithDelAndCommit(l_bondProductParams);
        }
        catch (Exception l_exE)
        {
            log.error(l_exE.getMessage(), l_exE);
            log.exiting(STR_METHOD_NAME);

            fail();
        }

        WEB3BondProductManager l_bondProductManager = new WEB3BondProductManager();

        String l_strProductId =
            String.valueOf(TestDBUtility.getBondProductRow().getProductId());

        WEB3BondDomesticProductUpdateInfo l_bondDomesticProductUpdateInfo =
            new WEB3BondDomesticProductUpdateInfo();
        l_bondDomesticProductUpdateInfo.recruitStartDateWEB3 =
            WEB3DateUtility.getDate("20040708", "yyyyMMdd");
        l_bondDomesticProductUpdateInfo.recruitEndDateWEB3 =
            WEB3DateUtility.getDate("20040709", "yyyyMMdd");
        l_bondDomesticProductUpdateInfo.recruitStartDateInterNet =
            WEB3DateUtility.getDate("20040708", "yyyyMMdd");
        l_bondDomesticProductUpdateInfo.recruitEndDateInterNet =
            WEB3DateUtility.getDate("20040709", "yyyyMMdd");
        l_bondDomesticProductUpdateInfo.deliveryDate =
            WEB3DateUtility.getDate("20040712", "yyyyMMdd");
        l_bondDomesticProductUpdateInfo.applyUnit = "101";

        try
        {
            l_bondProductManager.validateProductSpec(
                l_strProductId, l_bondDomesticProductUpdateInfo);

            log.exiting(STR_METHOD_NAME);
            fail();
        }
        catch (WEB3BaseException l_exBE)
        {
            assertEquals(WEB3ErrorCatalog.BUSINESS_ERROR_02866, l_exBE.getErrorInfo());

            log.error(l_exBE.getMessage(), l_exBE);
            log.exiting(STR_METHOD_NAME);
        }
        catch (Exception l_exE)
        {
            log.error(l_exE.getMessage(), l_exE);
            log.exiting(STR_METHOD_NAME);

            fail();
        }
        finally
        {
            try
            {
                TestDBUtility.deleteAllAndCommit(BondProductParams.TYPE);
            }
            catch (Exception l_exE)
            {
                log.error(l_exE.getMessage(), l_exE);
                log.exiting(STR_METHOD_NAME);

                fail();
            }
        }
    }

    /**
     *
     */
    public void testValidateProductSpec_case0020()
    {
        final String STR_METHOD_NAME = " testValidateProductSpec_case0020()";
        log.entering(STR_METHOD_NAME);

        try
        {
            TestDBUtility.deleteAllAndCommit(BondProductParams.TYPE);
        }
        catch (Exception l_exE)
        {
            log.error(l_exE.getMessage(), l_exE);
            log.exiting(STR_METHOD_NAME);

            fail();
        }

        try
        {
            BondProductParams l_bondProductParams = TestDBUtility.getBondProductRow();
            l_bondProductParams.setHostRecruitStartDate(
                WEB3DateUtility.getDate("20040708", "yyyyMMdd"));
            l_bondProductParams.setHostRecruitEndDate(
                WEB3DateUtility.getDate("20040714", "yyyyMMdd"));
            l_bondProductParams.setIssueDate(
                WEB3DateUtility.getDate("20040714", "yyyyMMdd"));
            l_bondProductParams.setMinIssueCouponType("100");
            TestDBUtility.insertWithDelAndCommit(l_bondProductParams);
        }
        catch (Exception l_exE)
        {
            log.error(l_exE.getMessage(), l_exE);
            log.exiting(STR_METHOD_NAME);

            fail();
        }

        WEB3BondProductManager l_bondProductManager = new WEB3BondProductManager();

        String l_strProductId =
            String.valueOf(TestDBUtility.getBondProductRow().getProductId());

        WEB3BondDomesticProductUpdateInfo l_bondDomesticProductUpdateInfo =
            new WEB3BondDomesticProductUpdateInfo();
        l_bondDomesticProductUpdateInfo.recruitStartDateWEB3 =
            WEB3DateUtility.getDate("20040708", "yyyyMMdd");
        l_bondDomesticProductUpdateInfo.recruitEndDateWEB3 =
            WEB3DateUtility.getDate("20040709", "yyyyMMdd");
        l_bondDomesticProductUpdateInfo.recruitStartDateInterNet =
            WEB3DateUtility.getDate("20040708", "yyyyMMdd");
        l_bondDomesticProductUpdateInfo.recruitEndDateInterNet =
            WEB3DateUtility.getDate("20040709", "yyyyMMdd");
        l_bondDomesticProductUpdateInfo.deliveryDate =
            WEB3DateUtility.getDate("20040713", "yyyyMMdd");
        l_bondDomesticProductUpdateInfo.applyUnit = "101";

        try
        {
            l_bondProductManager.validateProductSpec(
                l_strProductId, l_bondDomesticProductUpdateInfo);

            log.exiting(STR_METHOD_NAME);
            fail();
        }
        catch (WEB3BaseException l_exBE)
        {
            assertEquals(WEB3ErrorCatalog.BUSINESS_ERROR_02866, l_exBE.getErrorInfo());

            log.error(l_exBE.getMessage(), l_exBE);
            log.exiting(STR_METHOD_NAME);
        }
        catch (Exception l_exE)
        {
            log.error(l_exE.getMessage(), l_exE);
            log.exiting(STR_METHOD_NAME);

            fail();
        }
        finally
        {
            try
            {
                TestDBUtility.deleteAllAndCommit(BondProductParams.TYPE);
            }
            catch (Exception l_exE)
            {
                log.error(l_exE.getMessage(), l_exE);
                log.exiting(STR_METHOD_NAME);

                fail();
            }
        }
    }

    /**
     *
     */
    public void testValidateProductSpec_case0021()
    {
        final String STR_METHOD_NAME = " testValidateProductSpec_case0021()";
        log.entering(STR_METHOD_NAME);

        try
        {
            TestDBUtility.deleteAllAndCommit(BondProductParams.TYPE);
        }
        catch (Exception l_exE)
        {
            log.error(l_exE.getMessage(), l_exE);
            log.exiting(STR_METHOD_NAME);

            fail();
        }

        try
        {
            BondProductParams l_bondProductParams = TestDBUtility.getBondProductRow();
            l_bondProductParams.setHostRecruitStartDate(
                WEB3DateUtility.getDate("20040708", "yyyyMMdd"));
            l_bondProductParams.setHostRecruitEndDate(
                WEB3DateUtility.getDate("20040714", "yyyyMMdd"));
            l_bondProductParams.setIssueDate(
                WEB3DateUtility.getDate("20040713", "yyyyMMdd"));
            TestDBUtility.insertWithDelAndCommit(l_bondProductParams);
        }
        catch (Exception l_exE)
        {
            log.error(l_exE.getMessage(), l_exE);
            log.exiting(STR_METHOD_NAME);

            fail();
        }

        WEB3BondProductManager l_bondProductManager = new WEB3BondProductManager();

        String l_strProductId =
            String.valueOf(TestDBUtility.getBondProductRow().getProductId());

        WEB3BondDomesticProductUpdateInfo l_bondDomesticProductUpdateInfo =
            new WEB3BondDomesticProductUpdateInfo();
        l_bondDomesticProductUpdateInfo.recruitStartDateWEB3 =
            WEB3DateUtility.getDate("20040708", "yyyyMMdd");
        l_bondDomesticProductUpdateInfo.recruitEndDateWEB3 =
            WEB3DateUtility.getDate("20040709", "yyyyMMdd");
        l_bondDomesticProductUpdateInfo.recruitStartDateInterNet =
            WEB3DateUtility.getDate("20040708", "yyyyMMdd");
        l_bondDomesticProductUpdateInfo.recruitEndDateInterNet =
            WEB3DateUtility.getDate("20040709", "yyyyMMdd");
        l_bondDomesticProductUpdateInfo.deliveryDate =
            WEB3DateUtility.getDate("20040713", "yyyyMMdd");

        try
        {
            l_bondProductManager.validateProductSpec(
                l_strProductId, l_bondDomesticProductUpdateInfo);

            log.exiting(STR_METHOD_NAME);
            fail();
        }
        catch (WEB3BaseException l_exBE)
        {
            assertEquals(WEB3ErrorCatalog.BUSINESS_ERROR_02865, l_exBE.getErrorInfo());

            log.error(l_exBE.getMessage(), l_exBE);
            log.exiting(STR_METHOD_NAME);
        }
        catch (Exception l_exE)
        {
            log.error(l_exE.getMessage(), l_exE);
            log.exiting(STR_METHOD_NAME);

            fail();
        }
        finally
        {
            try
            {
                TestDBUtility.deleteAllAndCommit(BondProductParams.TYPE);
            }
            catch (Exception l_exE)
            {
                log.error(l_exE.getMessage(), l_exE);
                log.exiting(STR_METHOD_NAME);

                fail();
            }
        }
    }

    /**
     *
     */
    public void testValidateProductSpec_case0022()
    {
        final String STR_METHOD_NAME = " testValidateProductSpec_case0022()";
        log.entering(STR_METHOD_NAME);

        try
        {
            TestDBUtility.deleteAllAndCommit(BondProductParams.TYPE);
        }
        catch (Exception l_exE)
        {
            log.error(l_exE.getMessage(), l_exE);
            log.exiting(STR_METHOD_NAME);

            fail();
        }

        try
        {
            BondProductParams l_bondProductParams = TestDBUtility.getBondProductRow();
            l_bondProductParams.setHostRecruitStartDate(
                WEB3DateUtility.getDate("20040708", "yyyyMMdd"));
            l_bondProductParams.setHostRecruitEndDate(
                WEB3DateUtility.getDate("20040714", "yyyyMMdd"));
            l_bondProductParams.setIssueDate(
                WEB3DateUtility.getDate("20040712", "yyyyMMdd"));
            TestDBUtility.insertWithDelAndCommit(l_bondProductParams);
        }
        catch (Exception l_exE)
        {
            log.error(l_exE.getMessage(), l_exE);
            log.exiting(STR_METHOD_NAME);

            fail();
        }

        WEB3BondProductManager l_bondProductManager = new WEB3BondProductManager();

        String l_strProductId =
            String.valueOf(TestDBUtility.getBondProductRow().getProductId());

        WEB3BondDomesticProductUpdateInfo l_bondDomesticProductUpdateInfo =
            new WEB3BondDomesticProductUpdateInfo();
        l_bondDomesticProductUpdateInfo.recruitStartDateWEB3 =
            WEB3DateUtility.getDate("20040708", "yyyyMMdd");
        l_bondDomesticProductUpdateInfo.recruitEndDateWEB3 =
            WEB3DateUtility.getDate("20040709", "yyyyMMdd");
        l_bondDomesticProductUpdateInfo.recruitStartDateInterNet =
            WEB3DateUtility.getDate("20040708", "yyyyMMdd");
        l_bondDomesticProductUpdateInfo.recruitEndDateInterNet =
            WEB3DateUtility.getDate("20040709", "yyyyMMdd");
        l_bondDomesticProductUpdateInfo.deliveryDate =
            WEB3DateUtility.getDate("20040713", "yyyyMMdd");

        try
        {
            l_bondProductManager.validateProductSpec(
                l_strProductId, l_bondDomesticProductUpdateInfo);

            log.exiting(STR_METHOD_NAME);
            fail();
        }
        catch (WEB3BaseException l_exBE)
        {
            assertEquals(WEB3ErrorCatalog.BUSINESS_ERROR_02865, l_exBE.getErrorInfo());

            log.error(l_exBE.getMessage(), l_exBE);
            log.exiting(STR_METHOD_NAME);
        }
        catch (Exception l_exE)
        {
            log.error(l_exE.getMessage(), l_exE);
            log.exiting(STR_METHOD_NAME);

            fail();
        }
        finally
        {
            try
            {
                TestDBUtility.deleteAllAndCommit(BondProductParams.TYPE);
            }
            catch (Exception l_exE)
            {
                log.error(l_exE.getMessage(), l_exE);
                log.exiting(STR_METHOD_NAME);

                fail();
            }
        }
    }

    /**
     *
     */
    public void testValidateProductSpec_case0023()
    {
        final String STR_METHOD_NAME = " testValidateProductSpec_case0023()";
        log.entering(STR_METHOD_NAME);

        try
        {
            TestDBUtility.deleteAllAndCommit(BondProductParams.TYPE);
        }
        catch (Exception l_exE)
        {
            log.error(l_exE.getMessage(), l_exE);
            log.exiting(STR_METHOD_NAME);

            fail();
        }

        try
        {
            BondProductParams l_bondProductParams = TestDBUtility.getBondProductRow();
            l_bondProductParams.setHostRecruitStartDate(
                WEB3DateUtility.getDate("20040708", "yyyyMMdd"));
            l_bondProductParams.setHostRecruitEndDate(
                WEB3DateUtility.getDate("20040714", "yyyyMMdd"));
            l_bondProductParams.setIssueDate(
                WEB3DateUtility.getDate("20040714", "yyyyMMdd"));
            l_bondProductParams.setMinIssueCouponType("100");
            TestDBUtility.insertWithDelAndCommit(l_bondProductParams);
        }
        catch (Exception l_exE)
        {
            log.error(l_exE.getMessage(), l_exE);
            log.exiting(STR_METHOD_NAME);

            fail();
        }

        WEB3BondProductManager l_bondProductManager = new WEB3BondProductManager();

        String l_strProductId =
            String.valueOf(TestDBUtility.getBondProductRow().getProductId());

        WEB3BondDomesticProductUpdateInfo l_bondDomesticProductUpdateInfo =
            new WEB3BondDomesticProductUpdateInfo();
        l_bondDomesticProductUpdateInfo.recruitStartDateWEB3 =
            WEB3DateUtility.getDate("20040708", "yyyyMMdd");
        l_bondDomesticProductUpdateInfo.recruitEndDateWEB3 =
            WEB3DateUtility.getDate("20040709", "yyyyMMdd");
        l_bondDomesticProductUpdateInfo.recruitStartDateInterNet =
            WEB3DateUtility.getDate("20040708", "yyyyMMdd");
        l_bondDomesticProductUpdateInfo.recruitEndDateInterNet =
            WEB3DateUtility.getDate("20040709", "yyyyMMdd");
        l_bondDomesticProductUpdateInfo.deliveryDate =
            WEB3DateUtility.getDate("20040713", "yyyyMMdd");
        l_bondDomesticProductUpdateInfo.applyUnit = "101";

        try
        {
            l_bondProductManager.validateProductSpec(
                l_strProductId, l_bondDomesticProductUpdateInfo);

            log.exiting(STR_METHOD_NAME);
            fail();
        }
        catch (WEB3BaseException l_exBE)
        {
            assertEquals(WEB3ErrorCatalog.BUSINESS_ERROR_02866, l_exBE.getErrorInfo());

            log.error(l_exBE.getMessage(), l_exBE);
            log.exiting(STR_METHOD_NAME);
        }
        catch (Exception l_exE)
        {
            log.error(l_exE.getMessage(), l_exE);
            log.exiting(STR_METHOD_NAME);

            fail();
        }
        finally
        {
            try
            {
                TestDBUtility.deleteAllAndCommit(BondProductParams.TYPE);
            }
            catch (Exception l_exE)
            {
                log.error(l_exE.getMessage(), l_exE);
                log.exiting(STR_METHOD_NAME);

                fail();
            }
        }
    }

    /**
     *
     */
    public void testValidateProductSpec_case0024()
    {
        final String STR_METHOD_NAME = " testValidateProductSpec_case0024()";
        log.entering(STR_METHOD_NAME);

        try
        {
            TestDBUtility.deleteAllAndCommit(BondProductParams.TYPE);
        }
        catch (Exception l_exE)
        {
            log.error(l_exE.getMessage(), l_exE);
            log.exiting(STR_METHOD_NAME);

            fail();
        }

        try
        {
            BondProductParams l_bondProductParams = TestDBUtility.getBondProductRow();
            l_bondProductParams.setHostRecruitStartDate(
                WEB3DateUtility.getDate("20040708", "yyyyMMdd"));
            l_bondProductParams.setHostRecruitEndDate(
                WEB3DateUtility.getDate("20040714", "yyyyMMdd"));
            l_bondProductParams.setIssueDate(
                WEB3DateUtility.getDate("20040714", "yyyyMMdd"));
            l_bondProductParams.setMinIssueCouponType("100");
            TestDBUtility.insertWithDelAndCommit(l_bondProductParams);
        }
        catch (Exception l_exE)
        {
            log.error(l_exE.getMessage(), l_exE);
            log.exiting(STR_METHOD_NAME);

            fail();
        }

        WEB3BondProductManager l_bondProductManager = new WEB3BondProductManager();

        String l_strProductId =
            String.valueOf(TestDBUtility.getBondProductRow().getProductId());

        WEB3BondDomesticProductUpdateInfo l_bondDomesticProductUpdateInfo =
            new WEB3BondDomesticProductUpdateInfo();
        l_bondDomesticProductUpdateInfo.recruitStartDateWEB3 =
            WEB3DateUtility.getDate("20040708", "yyyyMMdd");
        l_bondDomesticProductUpdateInfo.recruitEndDateWEB3 =
            WEB3DateUtility.getDate("20040709", "yyyyMMdd");
        l_bondDomesticProductUpdateInfo.recruitStartDateInterNet =
            WEB3DateUtility.getDate("20040708", "yyyyMMdd");
        l_bondDomesticProductUpdateInfo.recruitEndDateInterNet =
            WEB3DateUtility.getDate("20040709", "yyyyMMdd");
        l_bondDomesticProductUpdateInfo.deliveryDate =
            WEB3DateUtility.getDate("20040713", "yyyyMMdd");
        l_bondDomesticProductUpdateInfo.applyUnit = "0";
        l_bondDomesticProductUpdateInfo.minFaceAmount = "50";

        try
        {
            l_bondProductManager.validateProductSpec(
                l_strProductId, l_bondDomesticProductUpdateInfo);

            log.exiting(STR_METHOD_NAME);
            fail();
        }
        catch (WEB3BaseException l_exBE)
        {
            assertEquals(WEB3ErrorCatalog.BUSINESS_ERROR_02867, l_exBE.getErrorInfo());

            log.error(l_exBE.getMessage(), l_exBE);
            log.exiting(STR_METHOD_NAME);
        }
        catch (Exception l_exE)
        {
            log.error(l_exE.getMessage(), l_exE);
            log.exiting(STR_METHOD_NAME);

            fail();
        }
        finally
        {
            try
            {
                TestDBUtility.deleteAllAndCommit(BondProductParams.TYPE);
            }
            catch (Exception l_exE)
            {
                log.error(l_exE.getMessage(), l_exE);
                log.exiting(STR_METHOD_NAME);

                fail();
            }
        }
    }

    /**
     *
     */
    public void testValidateProductSpec_case0025()
    {
        final String STR_METHOD_NAME = " testValidateProductSpec_case0025()";
        log.entering(STR_METHOD_NAME);

        try
        {
            TestDBUtility.deleteAllAndCommit(BondProductParams.TYPE);
        }
        catch (Exception l_exE)
        {
            log.error(l_exE.getMessage(), l_exE);
            log.exiting(STR_METHOD_NAME);

            fail();
        }

        try
        {
            BondProductParams l_bondProductParams = TestDBUtility.getBondProductRow();
            l_bondProductParams.setHostRecruitStartDate(
                WEB3DateUtility.getDate("20040708", "yyyyMMdd"));
            l_bondProductParams.setHostRecruitEndDate(
                WEB3DateUtility.getDate("20040714", "yyyyMMdd"));
            l_bondProductParams.setIssueDate(
                WEB3DateUtility.getDate("20040714", "yyyyMMdd"));
            l_bondProductParams.setMinIssueCouponType("0.01");
            TestDBUtility.insertWithDelAndCommit(l_bondProductParams);
        }
        catch (Exception l_exE)
        {
            log.error(l_exE.getMessage(), l_exE);
            log.exiting(STR_METHOD_NAME);

            fail();
        }

        WEB3BondProductManager l_bondProductManager = new WEB3BondProductManager();

        String l_strProductId =
            String.valueOf(TestDBUtility.getBondProductRow().getProductId());

        WEB3BondDomesticProductUpdateInfo l_bondDomesticProductUpdateInfo =
            new WEB3BondDomesticProductUpdateInfo();
        l_bondDomesticProductUpdateInfo.recruitStartDateWEB3 =
            WEB3DateUtility.getDate("20040708", "yyyyMMdd");
        l_bondDomesticProductUpdateInfo.recruitEndDateWEB3 =
            WEB3DateUtility.getDate("20040709", "yyyyMMdd");
        l_bondDomesticProductUpdateInfo.recruitStartDateInterNet =
            WEB3DateUtility.getDate("20040708", "yyyyMMdd");
        l_bondDomesticProductUpdateInfo.recruitEndDateInterNet =
            WEB3DateUtility.getDate("20040709", "yyyyMMdd");
        l_bondDomesticProductUpdateInfo.deliveryDate =
            WEB3DateUtility.getDate("20040713", "yyyyMMdd");
        l_bondDomesticProductUpdateInfo.applyUnit = "0";
        l_bondDomesticProductUpdateInfo.minFaceAmount = "100";
        l_bondDomesticProductUpdateInfo.maxFaceAmount = "90";

        try
        {
            l_bondProductManager.validateProductSpec(
                l_strProductId, l_bondDomesticProductUpdateInfo);

            log.exiting(STR_METHOD_NAME);
            fail();
        }
        catch (WEB3BaseException l_exBE)
        {
            assertEquals(WEB3ErrorCatalog.BUSINESS_ERROR_02625, l_exBE.getErrorInfo());

            log.error(l_exBE.getMessage(), l_exBE);
            log.exiting(STR_METHOD_NAME);
        }
        catch (Exception l_exE)
        {
            log.error(l_exE.getMessage(), l_exE);
            log.exiting(STR_METHOD_NAME);

            fail();
        }
        finally
        {
            try
            {
                TestDBUtility.deleteAllAndCommit(BondProductParams.TYPE);
            }
            catch (Exception l_exE)
            {
                log.error(l_exE.getMessage(), l_exE);
                log.exiting(STR_METHOD_NAME);

                fail();
            }
        }
    }


    public void testValidateProductSpec_case0026()
    {
        final String STR_METHOD_NAME = " testValidateProductSpec_case0026()";
        log.entering(STR_METHOD_NAME);

        try
        {
            TestDBUtility.deleteAllAndCommit(BondProductParams.TYPE);
        }
        catch (Exception l_exE)
        {
            log.error(l_exE.getMessage(), l_exE);
            log.exiting(STR_METHOD_NAME);

            fail();
        }

        try
        {
            BondProductParams l_bondProductParams = TestDBUtility.getBondProductRow();
            l_bondProductParams.setHostRecruitStartDate(
                WEB3DateUtility.getDate("20040708", "yyyyMMdd"));
            l_bondProductParams.setHostRecruitEndDate(
                WEB3DateUtility.getDate("20040714", "yyyyMMdd"));
            l_bondProductParams.setIssueDate(
                WEB3DateUtility.getDate("20040714", "yyyyMMdd"));
            l_bondProductParams.setMinIssueCouponType("0.1");
            TestDBUtility.insertWithDelAndCommit(l_bondProductParams);
        }
        catch (Exception l_exE)
        {
            log.error(l_exE.getMessage(), l_exE);
            log.exiting(STR_METHOD_NAME);

            fail();
        }

        WEB3BondProductManager l_bondProductManager = new WEB3BondProductManager();

        String l_strProductId =
            String.valueOf(TestDBUtility.getBondProductRow().getProductId());

        WEB3BondDomesticProductUpdateInfo l_bondDomesticProductUpdateInfo =
            new WEB3BondDomesticProductUpdateInfo();
        l_bondDomesticProductUpdateInfo.recruitStartDateWEB3 =
            WEB3DateUtility.getDate("20040708", "yyyyMMdd");
        l_bondDomesticProductUpdateInfo.recruitEndDateWEB3 =
            WEB3DateUtility.getDate("20040709", "yyyyMMdd");
        l_bondDomesticProductUpdateInfo.recruitStartDateInterNet =
            WEB3DateUtility.getDate("20040708", "yyyyMMdd");
        l_bondDomesticProductUpdateInfo.recruitEndDateInterNet =
            WEB3DateUtility.getDate("20040709", "yyyyMMdd");
        l_bondDomesticProductUpdateInfo.deliveryDate =
            WEB3DateUtility.getDate("20040713", "yyyyMMdd");
        l_bondDomesticProductUpdateInfo.applyUnit = "0";
        l_bondDomesticProductUpdateInfo.minFaceAmount = "100";
        l_bondDomesticProductUpdateInfo.maxFaceAmount = "100";

        try
        {
            l_bondProductManager.validateProductSpec(
                l_strProductId, l_bondDomesticProductUpdateInfo);

            log.exiting(STR_METHOD_NAME);
            assertTrue(true);
        }
        catch (WEB3BaseException l_exBE)
        {
            log.error(l_exBE.getMessage(), l_exBE);
            log.exiting(STR_METHOD_NAME);

            fail();
        }
        catch (Exception l_exE)
        {
            log.error(l_exE.getMessage(), l_exE);
            log.exiting(STR_METHOD_NAME);

            fail();
        }
        finally
        {
            try
            {
                TestDBUtility.deleteAllAndCommit(BondProductParams.TYPE);
            }
            catch (Exception l_exE)
            {
                log.error(l_exE.getMessage(), l_exE);
                log.exiting(STR_METHOD_NAME);

                fail();
            }
        }
    }

    /**
     *
     */
    public void testValidateProductSpec_case0027()
    {
        final String STR_METHOD_NAME = " testValidateProductSpec_case0027()";
        log.entering(STR_METHOD_NAME);

        try
        {
            TestDBUtility.deleteAllAndCommit(BondProductParams.TYPE);
        }
        catch (Exception l_exE)
        {
            log.error(l_exE.getMessage(), l_exE);
            log.exiting(STR_METHOD_NAME);

            fail();
        }

        try
        {
            BondProductParams l_bondProductParams = TestDBUtility.getBondProductRow();
            l_bondProductParams.setHostRecruitStartDate(
                WEB3DateUtility.getDate("20040708", "yyyyMMdd"));
            l_bondProductParams.setHostRecruitEndDate(
                WEB3DateUtility.getDate("20040714", "yyyyMMdd"));
            l_bondProductParams.setIssueDate(
                WEB3DateUtility.getDate("20040714", "yyyyMMdd"));
            l_bondProductParams.setMinIssueCouponType("0.1");
            TestDBUtility.insertWithDelAndCommit(l_bondProductParams);
        }
        catch (Exception l_exE)
        {
            log.error(l_exE.getMessage(), l_exE);
            log.exiting(STR_METHOD_NAME);

            fail();
        }

        WEB3BondProductManager l_bondProductManager = new WEB3BondProductManager();

        String l_strProductId =
            String.valueOf(TestDBUtility.getBondProductRow().getProductId());

        WEB3BondDomesticProductUpdateInfo l_bondDomesticProductUpdateInfo =
            new WEB3BondDomesticProductUpdateInfo();
        l_bondDomesticProductUpdateInfo.recruitStartDateWEB3 =
            WEB3DateUtility.getDate("20040708", "yyyyMMdd");
        l_bondDomesticProductUpdateInfo.recruitEndDateWEB3 =
            WEB3DateUtility.getDate("20040709", "yyyyMMdd");
        l_bondDomesticProductUpdateInfo.recruitStartDateInterNet =
            WEB3DateUtility.getDate("20040708", "yyyyMMdd");
        l_bondDomesticProductUpdateInfo.recruitEndDateInterNet =
            WEB3DateUtility.getDate("20040709", "yyyyMMdd");
        l_bondDomesticProductUpdateInfo.deliveryDate =
            WEB3DateUtility.getDate("20040713", "yyyyMMdd");
        l_bondDomesticProductUpdateInfo.applyUnit = "400";
        l_bondDomesticProductUpdateInfo.minFaceAmount = "100";
        l_bondDomesticProductUpdateInfo.maxFaceAmount = "150";

        try
        {
            l_bondProductManager.validateProductSpec(
                l_strProductId, l_bondDomesticProductUpdateInfo);

            log.exiting(STR_METHOD_NAME);
            assertTrue(true);
        }
        catch (WEB3BaseException l_exBE)
        {
            log.error(l_exBE.getMessage(), l_exBE);
            log.exiting(STR_METHOD_NAME);

            fail();
        }
        catch (Exception l_exE)
        {
            log.error(l_exE.getMessage(), l_exE);
            log.exiting(STR_METHOD_NAME);

            fail();
        }
        finally
        {
            try
            {
                TestDBUtility.deleteAllAndCommit(BondProductParams.TYPE);
            }
            catch (Exception l_exE)
            {
                log.error(l_exE.getMessage(), l_exE);
                log.exiting(STR_METHOD_NAME);

                fail();
            }
        }
    }

    /**
     *
     */
    public void testValidateProductSpec_case0028()
    {
        final String STR_METHOD_NAME = " testValidateProductSpec_case0028()";
        log.entering(STR_METHOD_NAME);

        try
        {
            TestDBUtility.deleteAllAndCommit(BondProductParams.TYPE);
        }
        catch (Exception l_exE)
        {
            log.error(l_exE.getMessage(), l_exE);
            log.exiting(STR_METHOD_NAME);

            fail();
        }

        try
        {
            BondProductParams l_bondProductParams = TestDBUtility.getBondProductRow();
            l_bondProductParams.setHostRecruitStartDate(
                WEB3DateUtility.getDate("20040708", "yyyyMMdd"));
            l_bondProductParams.setHostRecruitEndDate(
                WEB3DateUtility.getDate("20040714", "yyyyMMdd"));
            l_bondProductParams.setIssueDate(
                WEB3DateUtility.getDate("20040714", "yyyyMMdd"));
            l_bondProductParams.setMinIssueCouponType(null);
            TestDBUtility.insertWithDelAndCommit(l_bondProductParams);
        }
        catch (Exception l_exE)
        {
            log.error(l_exE.getMessage(), l_exE);
            log.exiting(STR_METHOD_NAME);

            fail();
        }

        WEB3BondProductManager l_bondProductManager = new WEB3BondProductManager();

        String l_strProductId =
            String.valueOf(TestDBUtility.getBondProductRow().getProductId());

        WEB3BondDomesticProductUpdateInfo l_bondDomesticProductUpdateInfo =
            new WEB3BondDomesticProductUpdateInfo();
        l_bondDomesticProductUpdateInfo.recruitStartDateWEB3 =
            WEB3DateUtility.getDate("20040708", "yyyyMMdd");
        l_bondDomesticProductUpdateInfo.recruitEndDateWEB3 =
            WEB3DateUtility.getDate("20040709", "yyyyMMdd");
        l_bondDomesticProductUpdateInfo.recruitStartDateInterNet =
            WEB3DateUtility.getDate("20040708", "yyyyMMdd");
        l_bondDomesticProductUpdateInfo.recruitEndDateInterNet =
            WEB3DateUtility.getDate("20040709", "yyyyMMdd");
        l_bondDomesticProductUpdateInfo.deliveryDate =
            WEB3DateUtility.getDate("20040713", "yyyyMMdd");
        l_bondDomesticProductUpdateInfo.applyUnit = "101";
        l_bondDomesticProductUpdateInfo.minFaceAmount = "50";
        l_bondDomesticProductUpdateInfo.maxFaceAmount = "50";

        try
        {
            l_bondProductManager.validateProductSpec(
                l_strProductId, l_bondDomesticProductUpdateInfo);

            log.exiting(STR_METHOD_NAME);
            assertTrue(true);
        }
        catch (WEB3BaseException l_exBE)
        {
            log.error(l_exBE.getMessage(), l_exBE);
            log.exiting(STR_METHOD_NAME);

            fail();
        }
        catch (Exception l_exE)
        {
            log.error(l_exE.getMessage(), l_exE);
            log.exiting(STR_METHOD_NAME);

            fail();
        }
        finally
        {
            try
            {
                TestDBUtility.deleteAllAndCommit(BondProductParams.TYPE);
            }
            catch (Exception l_exE)
            {
                log.error(l_exE.getMessage(), l_exE);
                log.exiting(STR_METHOD_NAME);

                fail();
            }
        }
    }

    public void testValidateProductSpec_T001()
    {
        final String STR_METHOD_NAME = " testValidateProductSpec_T001()";
        log.entering(STR_METHOD_NAME);
        
        WEB3BondProductManager l_bondProductManager = new WEB3BondProductManager();
        Institution l_institution = null;
        WEB3AdminBondProductUpdateInfo l_productUpdateInfo =  new WEB3AdminBondProductUpdateInfo();
        l_productUpdateInfo.buySellDiv = WEB3BondTradeTypeDef.RECRUIT;

        try
        {
            BondProductParams l_bondProductParams = TestDBUtility.getBondProductRow();
            l_bondProductParams.setHostRecruitStartDate(
                WEB3DateUtility.getDate("20080708", "yyyyMMdd"));
            l_bondProductParams.setHostRecruitEndDate(
                WEB3DateUtility.getDate("20080714", "yyyyMMdd"));
            l_bondProductParams.setIssueDate(
                WEB3DateUtility.getDate("20080714", "yyyyMMdd"));
            l_bondProductParams.setMinIssueCouponType(null);
            TestDBUtility.insertWithDelAndCommit(l_bondProductParams);
            
            //
            TestDBUtility.deleteAll(InstitutionParams.TYPE);
            InstitutionParams l_InstitutionParams = TestDBUtility.getInstitutionRow();
            TestDBUtility.insertWithDel(l_InstitutionParams);
            l_institution = new InstitutionImpl(l_InstitutionParams);
       
            String l_strProductCode= l_bondProductParams.getProductCode();

            l_bondProductManager.validateProductSpec(l_institution,l_productUpdateInfo,l_strProductCode);
            fail();
        }
        catch(WEB3BaseException l_ex)
        {
            log.error(l_ex.getErrorMessage(),l_ex);
            assertEquals(l_ex.getErrorInfo(),WEB3ErrorCatalog.BUSINESS_ERROR_03106);
        }
        catch(Exception l_e)
        {
            log.entering(l_e.getMessage());
        }
    }
    
    public void testValidateProductSpec_T002()
    {
        final String STR_METHOD_NAME = " testValidateProductSpec_T002()";
        log.entering(STR_METHOD_NAME);
        
        WEB3BondProductManager l_bondProductManager = new WEB3BondProductManager();
        Institution l_institution = null;
        WEB3AdminBondProductUpdateInfo l_productUpdateInfo =  new WEB3AdminBondProductUpdateInfo();
        l_productUpdateInfo.recruitInvitationForm = "01";

        try
        {
            BondProductParams l_bondProductParams = TestDBUtility.getBondProductRow();
            l_bondProductParams.setHostRecruitStartDate(
                WEB3DateUtility.getDate("20080708", "yyyyMMdd"));
            l_bondProductParams.setHostRecruitEndDate(
                WEB3DateUtility.getDate("20080714", "yyyyMMdd"));
            l_bondProductParams.setIssueDate(
                WEB3DateUtility.getDate("20080714", "yyyyMMdd"));
            l_bondProductParams.setMinIssueCouponType(null);
            TestDBUtility.insertWithDelAndCommit(l_bondProductParams);

            //
            TestDBUtility.deleteAll(InstitutionParams.TYPE);
            InstitutionParams l_InstitutionParams = TestDBUtility.getInstitutionRow();
            TestDBUtility.insertWithDel(l_InstitutionParams);
            l_institution = new InstitutionImpl(l_InstitutionParams);
            String l_strProductCode= l_bondProductParams.getProductCode();
            
            l_bondProductManager.validateProductSpec(l_institution,l_productUpdateInfo,l_strProductCode);
            fail();
        }
        catch(WEB3BaseException l_ex)
        {
            log.error(l_ex.getErrorMessage(),l_ex);
            assertEquals(l_ex.getErrorInfo(),WEB3ErrorCatalog.BUSINESS_ERROR_03107);
        }
        catch(Exception l_e)
        {
            log.entering(l_e.getMessage());
        }
    }
    
    public void testValidateProductSpec_T003()
    {
        final String STR_METHOD_NAME = " testValidateProductSpec_T003()";
        log.entering(STR_METHOD_NAME);
        
        WEB3BondProductManager l_bondProductManager = new WEB3BondProductManager();
        Institution l_institution = null;
        WEB3AdminBondProductUpdateInfo l_productUpdateInfo =  new WEB3AdminBondProductUpdateInfo();
        l_productUpdateInfo.deliveryDate = WEB3DateUtility.getDate("20090726", "yyyyMMdd");

        try
        {
            BondProductParams l_bondProductParams = TestDBUtility.getBondProductRow();
            l_bondProductParams.setHostRecruitStartDate(
                WEB3DateUtility.getDate("20080708", "yyyyMMdd"));
            l_bondProductParams.setHostRecruitEndDate(
                WEB3DateUtility.getDate("20080714", "yyyyMMdd"));
            l_bondProductParams.setIssueDate(
                WEB3DateUtility.getDate("20080714", "yyyyMMdd"));
            l_bondProductParams.setMinIssueCouponType(null);
            l_bondProductParams.setDeliveryDate(WEB3DateUtility.getDate("20090725", "yyyyMMdd"));
            TestDBUtility.insertWithDelAndCommit(l_bondProductParams);

            //
            TestDBUtility.deleteAll(InstitutionParams.TYPE);
            InstitutionParams l_InstitutionParams = TestDBUtility.getInstitutionRow();
            TestDBUtility.insertWithDel(l_InstitutionParams);
            l_institution = new InstitutionImpl(l_InstitutionParams);
            String l_strProductCode= l_bondProductParams.getProductCode();
            
            l_bondProductManager.validateProductSpec(l_institution,l_productUpdateInfo,l_strProductCode);
            fail();
        }
        catch(WEB3BaseException l_ex)
        {
            log.error(l_ex.getErrorMessage(),l_ex);
            assertEquals(l_ex.getErrorInfo(),WEB3ErrorCatalog.BUSINESS_ERROR_02336);
        }
        catch(Exception l_e)
        {
            log.entering(l_e.getMessage());
        }
    }
    
    public void testValidateProductSpec_T004()
    {
        final String STR_METHOD_NAME = " testValidateProductSpec_T004()";
        log.entering(STR_METHOD_NAME);
        
        WEB3BondProductManager l_bondProductManager = new WEB3BondProductManager();
        Institution l_institution = null;
        WEB3AdminBondProductUpdateInfo l_productUpdateInfo =  new WEB3AdminBondProductUpdateInfo();
        l_productUpdateInfo.deliveryDate = WEB3DateUtility.getDate("20090727", "yyyyMMdd");

        try
        {
            BondProductParams l_bondProductParams = TestDBUtility.getBondProductRow();
            l_bondProductParams.setHostRecruitStartDate(
                WEB3DateUtility.getDate("20080708", "yyyyMMdd"));
            l_bondProductParams.setHostRecruitEndDate(
                WEB3DateUtility.getDate("20080714", "yyyyMMdd"));
            l_bondProductParams.setIssueDate(
                WEB3DateUtility.getDate("20080714", "yyyyMMdd"));
            l_bondProductParams.setMinIssueCouponType(null);
            l_bondProductParams.setDeliveryDate(WEB3DateUtility.getDate("20090726", "yyyyMMdd"));
            TestDBUtility.insertWithDelAndCommit(l_bondProductParams);

            //
            TestDBUtility.deleteAll(InstitutionParams.TYPE);
            InstitutionParams l_InstitutionParams = TestDBUtility.getInstitutionRow();
            TestDBUtility.insertWithDel(l_InstitutionParams);
            l_institution = new InstitutionImpl(l_InstitutionParams);
            String l_strProductCode= l_bondProductParams.getProductCode();
            
            l_bondProductManager.validateProductSpec(l_institution,l_productUpdateInfo,l_strProductCode);
            fail();
        }
        catch(WEB3BaseException l_ex)
        {
            log.error(l_ex.getErrorMessage(),l_ex);
            assertEquals(l_ex.getErrorInfo(),WEB3ErrorCatalog.BUSINESS_ERROR_02865);
        }
        catch(Exception l_e)
        {
            log.entering(l_e.getMessage());
        }
    }

    
    public void testValidateProductSpec_T005()
    {
        final String STR_METHOD_NAME = " testValidateProductSpec_T005()";
        log.entering(STR_METHOD_NAME);
        
        WEB3BondProductManager l_bondProductManager = new WEB3BondProductManager();
        Institution l_institution = null;
        WEB3AdminBondProductUpdateInfo l_productUpdateInfo =  new WEB3AdminBondProductUpdateInfo();
        WEB3GentradeBizDate l_bizDate = new WEB3GentradeBizDate(
                new Timestamp(WEB3DateUtility.toDay(GtlUtils.getSystemTimestamp()).getTime()));

        try
        {
            l_productUpdateInfo.deliveryDate = l_bizDate.roll(1);
            BondProductParams l_bondProductParams = TestDBUtility.getBondProductRow();
            l_bondProductParams.setHostRecruitStartDate(
                WEB3DateUtility.getDate("20080708", "yyyyMMdd"));
            l_bondProductParams.setHostRecruitEndDate(
                WEB3DateUtility.getDate("20080714", "yyyyMMdd"));
            l_bondProductParams.setIssueDate(
                WEB3DateUtility.getDate("20080714", "yyyyMMdd"));
            l_bondProductParams.setMinIssueCouponType(null);
            l_bondProductParams.setDeliveryDate(WEB3DateUtility.getDate("20090727", "yyyyMMdd"));
            TestDBUtility.insertWithDelAndCommit(l_bondProductParams);

            //
            TestDBUtility.deleteAll(InstitutionParams.TYPE);
            InstitutionParams l_InstitutionParams = TestDBUtility.getInstitutionRow();
            TestDBUtility.insertWithDel(l_InstitutionParams);
            l_institution = new InstitutionImpl(l_InstitutionParams);
            String l_strProductCode= l_bondProductParams.getProductCode();
            
            l_bondProductManager.validateProductSpec(l_institution,l_productUpdateInfo,l_strProductCode);
        }
        catch(Exception l_e)
        {
            log.entering(l_e.getMessage());
        }
    }
    
    public void testGetBondOrderReceiveHistoryList_case0001()
    {
        final String STR_METHOD_NAME = "testGetBondOrderReceiveHistoryList_case0001()";
        log.entering(STR_METHOD_NAME);
        
        try
        {
            TestDBUtility.deleteAll(BondOrderAcceptActionParams.TYPE);
            String l_strProductId = "1234";
            String l_strInstitutionCode = "0D";
            String l_strBranchCode = "381";
            List l_lis =
                l_manager.getBondOrderReceiveHistoryList(
                    l_strProductId,
                    l_strInstitutionCode,
                    l_strBranchCode);
            assertEquals(0, l_lis.size());
        }
        catch (Exception l_ex)
        {
            log.error(l_ex.getMessage(), l_ex);
            fail();
        }
        
        log.exiting(STR_METHOD_NAME);
    }

    public void testGetBondOrderReceiveHistoryList_case0002()
    {
        final String STR_METHOD_NAME = "testGetBondOrderReceiveHistoryList_case0002()";
        log.entering(STR_METHOD_NAME);
        
        try
        {
            TestDBUtility.deleteAll(BondOrderAcceptActionParams.TYPE);
            BondOrderAcceptActionParams l_bondOrderAcceptActionParams = new BondOrderAcceptActionParams();
            l_bondOrderAcceptActionParams.setInstitutionCode("0D");
            l_bondOrderAcceptActionParams.setBranchCode("381");
            l_bondOrderAcceptActionParams.setTotalOrderAmount(20);
            l_bondOrderAcceptActionParams.setProductId(1234);
            l_bondOrderAcceptActionParams.setOrderCount(10);
            l_bondOrderAcceptActionParams.setOrderAmount(10);
            l_bondOrderAcceptActionParams.setOrderAcceptDate(WEB3DateUtility.getDate("20070605", "yyyyMMdd"));
            l_bondOrderAcceptActionParams.setLastUpdatedTimestamp(WEB3DateUtility.getDate("20070605", "yyyyMMdd"));
            TestDBUtility.insertWithDel(l_bondOrderAcceptActionParams);
            
            String l_strProductId = "1234";
            String l_strInstitutionCode = "0D";
            String l_strBranchCode = "381";
            List l_lis =
                l_manager.getBondOrderReceiveHistoryList(
                    l_strProductId,
                    l_strInstitutionCode,
                    l_strBranchCode);
            assertEquals(1, l_lis.size());
        }
        catch (Exception l_ex)
        {
            log.error(l_ex.getMessage(), l_ex);
            fail();
        }
        
        log.exiting(STR_METHOD_NAME);
    }
    
    public void testGetBondOrderReceiveHistoryList_case0003()
    {
        final String STR_METHOD_NAME = "testGetBondOrderReceiveHistoryList_case0003()";
        log.entering(STR_METHOD_NAME);
        
        try
        {
            TestDBUtility.deleteAll(BondOrderAcceptActionParams.TYPE);
            BondOrderAcceptActionParams l_bondOrderAcceptActionParams = new BondOrderAcceptActionParams();
            l_bondOrderAcceptActionParams.setInstitutionCode("0D");
            l_bondOrderAcceptActionParams.setBranchCode("381");
            l_bondOrderAcceptActionParams.setTotalOrderAmount(20);
            l_bondOrderAcceptActionParams.setProductId(1234);
            l_bondOrderAcceptActionParams.setOrderCount(10);
            l_bondOrderAcceptActionParams.setOrderAmount(10);
            l_bondOrderAcceptActionParams.setOrderAcceptDate(WEB3DateUtility.getDate("20070605", "yyyyMMdd"));
            l_bondOrderAcceptActionParams.setLastUpdatedTimestamp(WEB3DateUtility.getDate("20070605", "yyyyMMdd"));
            TestDBUtility.insertWithDel(l_bondOrderAcceptActionParams);
            
            BondOrderAcceptActionParams l_bondOrderAcceptActionParams1 = new BondOrderAcceptActionParams();
            l_bondOrderAcceptActionParams1.setInstitutionCode("0D");
            l_bondOrderAcceptActionParams1.setBranchCode("381");
            l_bondOrderAcceptActionParams1.setTotalOrderAmount(20);
            l_bondOrderAcceptActionParams1.setProductId(1234);
            l_bondOrderAcceptActionParams1.setOrderCount(10);
            l_bondOrderAcceptActionParams1.setOrderAmount(10);
            l_bondOrderAcceptActionParams1.setOrderAcceptDate(WEB3DateUtility.getDate("20070505", "yyyyMMdd"));
            l_bondOrderAcceptActionParams1.setLastUpdatedTimestamp(WEB3DateUtility.getDate("20070605", "yyyyMMdd"));
            TestDBUtility.insertWithDel(l_bondOrderAcceptActionParams1);
            
            String l_strProductId = "1234";
            String l_strInstitutionCode = "0D";
            String l_strBranchCode = "381";
            List l_lis =
                l_manager.getBondOrderReceiveHistoryList(
                    l_strProductId,
                    l_strInstitutionCode,
                    l_strBranchCode);
            assertEquals(2, l_lis.size());
        }
        catch (Exception l_ex)
        {
            log.error(l_ex.getMessage(), l_ex);
            fail();
        }
        
        log.exiting(STR_METHOD_NAME);
    }

    public void testCreateAdminBondDomesticRecruitLimitInfo_case0001()
    {
        final String STR_METHOD_NAME = " testCreateAdminBondDomesticRecruitLimitInfo_case0001()";
        log.entering(STR_METHOD_NAME);
        
        try
        {
            TestDBUtility.deleteAll(BondBranchRecruitLimitRow.TYPE);
            TestDBUtility.deleteAll(BondOrderAcceptActionParams.TYPE);
            long l_lngProductId = 1234;
            String l_strInstitutionCode = "0D";
            String l_strBranchCode = "381";
            WEB3BondDomesticBranchRecruitLimitInfo[] l_infos =
                l_manager.createAdminBondDomesticRecruitLimitInfo(
                    l_lngProductId,
                    l_strInstitutionCode,
                    l_strBranchCode);
            assertEquals(0, l_infos.length);
        }
        catch (Exception l_ex)
        {
            log.error(l_ex.getMessage(), l_ex);
            fail();
        }
        
        log.exiting(STR_METHOD_NAME);
    }
    
    public void testCreateAdminBondDomesticRecruitLimitInfo_case0002()
    {
        final String STR_METHOD_NAME = " testCreateAdminBondDomesticRecruitLimitInfo_case0002()";
        log.entering(STR_METHOD_NAME);
        
        try
        {
            TestDBUtility.deleteAll(BondBranchRecruitLimitRow.TYPE);
            BondBranchRecruitLimitParams l_bondBranchRecruitLimitParams = new BondBranchRecruitLimitParams();
            l_bondBranchRecruitLimitParams.setBranchCode("381");
            l_bondBranchRecruitLimitParams.setProductId(1234);
            l_bondBranchRecruitLimitParams.setInstitutionCode("0D");
            l_bondBranchRecruitLimitParams.setLastUpdatedTimestamp(WEB3DateUtility.getDate("20070503", "yyyyMMdd"));
            l_bondBranchRecruitLimitParams.setRecruitLimit(10D);
            l_bondBranchRecruitLimitParams.setCreatedTimestamp(WEB3DateUtility.getDate("20070503", "yyyyMMdd"));
            l_bondBranchRecruitLimitParams.setRecruitLimit(10000000L);
            TestDBUtility.insertWithDel(l_bondBranchRecruitLimitParams);
            
            TestDBUtility.deleteAll(BondOrderAcceptActionParams.TYPE);
            BondOrderAcceptActionParams l_bondOrderAcceptActionParams = new BondOrderAcceptActionParams();
            l_bondOrderAcceptActionParams.setInstitutionCode("0D");
            l_bondOrderAcceptActionParams.setBranchCode("381");
            l_bondOrderAcceptActionParams.setTotalOrderAmount(20);
            l_bondOrderAcceptActionParams.setProductId(1234);
            l_bondOrderAcceptActionParams.setOrderCount(10);
            l_bondOrderAcceptActionParams.setOrderAmount(10);
            l_bondOrderAcceptActionParams.setOrderAcceptDate(WEB3DateUtility.getDate("20070605", "yyyyMMdd"));
            l_bondOrderAcceptActionParams.setLastUpdatedTimestamp(WEB3DateUtility.getDate("20070605", "yyyyMMdd"));
            TestDBUtility.insertWithDel(l_bondOrderAcceptActionParams);

            //InstitutionParams
            TestDBUtility.deleteAll(InstitutionParams.TYPE);
            InstitutionParams l_institutionParams = TestDBUtility.getInstitutionRow();
            l_institutionParams.setInstitutionCode("0D");
            
            TestDBUtility.insertWithDel(l_institutionParams);
            
            long l_lngProductId = 1234;
            String l_strInstitutionCode = "0D";
            String l_strBranchCode = null;

            Calendar l_calendar =  Calendar.getInstance();
            l_calendar.set(2007,6-1,12);
            Date date = l_calendar.getTime();
            Timestamp l_timestamp = new Timestamp(date.getTime());
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                "com.fitechlabs.xtrade.plugin.tc.gentrade.stdimpls.TradingSystemImpl", 
                "getSystemTimestamp",
                new Class[]{}, 
                l_timestamp);

            WEB3BondDomesticBranchRecruitLimitInfo[] l_infos =
                l_manager.createAdminBondDomesticRecruitLimitInfo(
                    l_lngProductId,
                    l_strInstitutionCode,
                    l_strBranchCode);
            assertEquals(1, l_infos.length);
            assertEquals("381",l_infos[0].branchCode);
            assertEquals("20", l_infos[0].orderAmountTotal);
            assertEquals("10000000", l_infos[0].web3RecruitLimit);
        }
        catch (Exception l_ex)
        {
            log.error(l_ex.getMessage(), l_ex);
            fail();
        }
        
        log.exiting(STR_METHOD_NAME);
    }
    
    public void testCreateAdminBondDomesticRecruitLimitInfo_case0003()
    {
        final String STR_METHOD_NAME = " testCreateAdminBondDomesticRecruitLimitInfo_case0003()";
        log.entering(STR_METHOD_NAME);
        
        try
        {
            TestDBUtility.deleteAll(BondBranchRecruitLimitRow.TYPE);
            BondBranchRecruitLimitParams l_bondBranchRecruitLimitParams = new BondBranchRecruitLimitParams();
            l_bondBranchRecruitLimitParams.setBranchCode("381");
            l_bondBranchRecruitLimitParams.setProductId(1234);
            l_bondBranchRecruitLimitParams.setInstitutionCode("0D");
            l_bondBranchRecruitLimitParams.setLastUpdatedTimestamp(WEB3DateUtility.getDate("20070503", "yyyyMMdd"));
            l_bondBranchRecruitLimitParams.setRecruitLimit(10D);
            l_bondBranchRecruitLimitParams.setCreatedTimestamp(WEB3DateUtility.getDate("20070503", "yyyyMMdd"));
            l_bondBranchRecruitLimitParams.setRecruitLimit(10000000L);
            TestDBUtility.insertWithDel(l_bondBranchRecruitLimitParams);
            
            TestDBUtility.deleteAll(BondOrderAcceptActionParams.TYPE);
            BondOrderAcceptActionParams l_bondOrderAcceptActionParams = new BondOrderAcceptActionParams();
            l_bondOrderAcceptActionParams.setInstitutionCode("0D");
            l_bondOrderAcceptActionParams.setBranchCode("381");
            l_bondOrderAcceptActionParams.setTotalOrderAmount(20);
            l_bondOrderAcceptActionParams.setProductId(1234);
            l_bondOrderAcceptActionParams.setOrderCount(10);
            l_bondOrderAcceptActionParams.setOrderAmount(10);
            l_bondOrderAcceptActionParams.setOrderAcceptDate(WEB3DateUtility.getDate("20070605", "yyyyMMdd"));
            l_bondOrderAcceptActionParams.setLastUpdatedTimestamp(WEB3DateUtility.getDate("20070605", "yyyyMMdd"));
            TestDBUtility.insertWithDel(l_bondOrderAcceptActionParams);

            long l_lngProductId = 1234;
            String l_strInstitutionCode = "0D";
            String l_strBranchCode = "381";
            WEB3BondDomesticBranchRecruitLimitInfo[] l_infos =
                l_manager.createAdminBondDomesticRecruitLimitInfo(
                    l_lngProductId,
                    l_strInstitutionCode,
                    l_strBranchCode);
            assertEquals(1, l_infos.length);
            assertEquals("381",l_infos[0].branchCode);
            assertEquals("20", l_infos[0].orderAmountTotal);
            assertEquals("10000000", l_infos[0].web3RecruitLimit);
        }
        catch (Exception l_ex)
        {
            log.error(l_ex.getMessage(), l_ex);
            fail();
        }
        
        log.exiting(STR_METHOD_NAME);
    }
    
    public void testCreateAdminBondDomesticRecruitLimitInfo_case0004()
    {
        final String STR_METHOD_NAME = " testCreateAdminBondDomesticRecruitLimitInfo_case0004()";
        log.entering(STR_METHOD_NAME);
        
        try
        {
            TestDBUtility.deleteAll(BondBranchRecruitLimitRow.TYPE);
            BondBranchRecruitLimitParams l_bondBranchRecruitLimitParams = new BondBranchRecruitLimitParams();
            l_bondBranchRecruitLimitParams.setBranchCode("381");
            l_bondBranchRecruitLimitParams.setProductId(1234);
            l_bondBranchRecruitLimitParams.setInstitutionCode("0D");
            l_bondBranchRecruitLimitParams.setLastUpdatedTimestamp(WEB3DateUtility.getDate("20070503", "yyyyMMdd"));
            l_bondBranchRecruitLimitParams.setRecruitLimit(10D);
            l_bondBranchRecruitLimitParams.setCreatedTimestamp(WEB3DateUtility.getDate("20070503", "yyyyMMdd"));
            l_bondBranchRecruitLimitParams.setRecruitLimit(10000000L);
            TestDBUtility.insertWithDel(l_bondBranchRecruitLimitParams);
            
            BondBranchRecruitLimitParams l_bondBranchRecruitLimitParams1 = new BondBranchRecruitLimitParams();
            l_bondBranchRecruitLimitParams1.setBranchCode("361");
            l_bondBranchRecruitLimitParams1.setProductId(1234);
            l_bondBranchRecruitLimitParams1.setInstitutionCode("0D");
            l_bondBranchRecruitLimitParams1.setLastUpdatedTimestamp(WEB3DateUtility.getDate("20070403", "yyyyMMdd"));
            l_bondBranchRecruitLimitParams1.setRecruitLimit(20D);
            l_bondBranchRecruitLimitParams1.setCreatedTimestamp(WEB3DateUtility.getDate("20070503", "yyyyMMdd"));
            l_bondBranchRecruitLimitParams1.setRecruitLimit(10000000L);
            TestDBUtility.insertWithDel(l_bondBranchRecruitLimitParams1);
            
            TestDBUtility.deleteAll(BondOrderAcceptActionParams.TYPE);
            BondOrderAcceptActionParams l_bondOrderAcceptActionParams = new BondOrderAcceptActionParams();
            l_bondOrderAcceptActionParams.setInstitutionCode("0D");
            l_bondOrderAcceptActionParams.setBranchCode("381");
            l_bondOrderAcceptActionParams.setTotalOrderAmount(20);
            l_bondOrderAcceptActionParams.setProductId(1234);
            l_bondOrderAcceptActionParams.setOrderCount(10);
            l_bondOrderAcceptActionParams.setOrderAmount(10);
            l_bondOrderAcceptActionParams.setOrderAcceptDate(WEB3DateUtility.getDate("20070605", "yyyyMMdd"));
            l_bondOrderAcceptActionParams.setLastUpdatedTimestamp(WEB3DateUtility.getDate("20070605", "yyyyMMdd"));
            TestDBUtility.insertWithDel(l_bondOrderAcceptActionParams);
            
            BondOrderAcceptActionParams l_bondOrderAcceptActionParams1 = new BondOrderAcceptActionParams();
            l_bondOrderAcceptActionParams1.setInstitutionCode("0D");
            l_bondOrderAcceptActionParams1.setBranchCode("361");
            l_bondOrderAcceptActionParams1.setTotalOrderAmount(20);
            l_bondOrderAcceptActionParams1.setProductId(1234);
            l_bondOrderAcceptActionParams1.setOrderCount(10);
            l_bondOrderAcceptActionParams1.setOrderAmount(10);
            l_bondOrderAcceptActionParams1.setOrderAcceptDate(WEB3DateUtility.getDate("20070405", "yyyyMMdd"));
            l_bondOrderAcceptActionParams1.setLastUpdatedTimestamp(WEB3DateUtility.getDate("20070605", "yyyyMMdd"));
            TestDBUtility.insertWithDel(l_bondOrderAcceptActionParams1);

            TestDBUtility.deleteAll(BranchParams.TYPE);
            BranchParams l_branchParams = TestDBUtility.getBranchRow();
            l_branchParams.setBranchCode("381");
            l_branchParams.setBranchId(123);
            TestDBUtility.insertWithDel(l_branchParams);
            
            BranchParams l_branchParams1 = TestDBUtility.getBranchRow();
            l_branchParams1.setBranchCode("361");
            l_branchParams1.setBranchId(124);
            TestDBUtility.insertWithDel(l_branchParams1);

            long l_lngProductId = 1234;
            String l_strInstitutionCode = "0D";
            String l_strBranchCode = null;
            WEB3BondDomesticBranchRecruitLimitInfo[] l_infos =
                l_manager.createAdminBondDomesticRecruitLimitInfo(
                    l_lngProductId,
                    l_strInstitutionCode,
                    l_strBranchCode);
            assertEquals(2, l_infos.length);
            assertEquals("361",l_infos[0].branchCode);
            assertEquals("20", l_infos[0].orderAmountTotal);
            assertEquals("10000000", l_infos[0].web3RecruitLimit);
            assertEquals("381",l_infos[1].branchCode);
            assertEquals("20", l_infos[1].orderAmountTotal);
            assertEquals("10000000", l_infos[1].web3RecruitLimit);
        }
        catch (Exception l_ex)
        {
            log.error(l_ex.getMessage(), l_ex);
            fail();
        }
        
        log.exiting(STR_METHOD_NAME);
    }

    public void testCreateAdminBondDomesticRecruitLimitInfo_case0005()
    {
        final String STR_METHOD_NAME = " testCreateAdminBondDomesticRecruitLimitInfo_case0005()";
        log.entering(STR_METHOD_NAME);

        try
        {
            TestDBUtility.deleteAll(BondBranchRecruitLimitRow.TYPE);
            TestDBUtility.deleteAll(BondOrderAcceptActionParams.TYPE);
            long l_lngProductId = 0;
            String l_strInstitutionCode = "0";
            String l_strBranchCode = "0";
            WEB3BondDomesticBranchRecruitLimitInfo[] l_infos =
                l_manager.createAdminBondDomesticRecruitLimitInfo(
                    l_lngProductId,
                    l_strInstitutionCode,
                    l_strBranchCode);
            assertEquals(0, l_infos.length);
        }
        catch (Exception l_ex)
        {
            log.error(l_ex.getMessage(), l_ex);
            fail();
        }
        log.exiting(STR_METHOD_NAME);
    }

    public void testUpdateBondProductSpec_T01()
    {
        final String STR_METHOD_NAME = " testUpdateBondProductSpec_T01()";
        log.entering(STR_METHOD_NAME);
        
        WEB3BondProductManager l_bondProductManager = new WEB3BondProductManager();
        
        Institution l_institution= null;
        String l_strProductCode="";
        WEB3AdminBondProductUpdateInfo l_productUpdateInfo = this.getWEB3AdminBondProductUpdateInfo();
        String l_strAdminCode= "123";
        
        try
        {
            BondProductParams l_bondProductParams = TestDBUtility.getBondProductRow();
            l_bondProductParams.setHostRecruitStartDate(
                WEB3DateUtility.getDate("20080708", "yyyyMMdd"));
            l_bondProductParams.setHostRecruitEndDate(
                WEB3DateUtility.getDate("20080714", "yyyyMMdd"));
            l_bondProductParams.setIssueDate(
                WEB3DateUtility.getDate("20080714", "yyyyMMdd"));
            l_bondProductParams.setMinIssueCouponType(null);
            TestDBUtility.insertWithDelAndCommit(l_bondProductParams);
            
            //
            TestDBUtility.deleteAll(InstitutionParams.TYPE);
            InstitutionParams l_InstitutionParams = TestDBUtility.getInstitutionRow();
            TestDBUtility.insertWithDel(l_InstitutionParams);
            l_institution = new InstitutionImpl(l_InstitutionParams);
            
            l_strProductCode = l_bondProductParams.getProductCode();
            
            l_bondProductManager.updateBondProductSpec(l_institution,l_strProductCode,l_productUpdateInfo,l_strAdminCode);
            
            
            try
            {
                QueryProcessor l_queryProcessor = Processors.getDefaultProcessor();
                List l_BondProductList = l_queryProcessor.doFindAllQuery(BondProductParams.TYPE);
                
                BondProductParams l_bondProduct = (BondProductParams)l_BondProductList.get(0);
                
                
                assertEquals(l_bondProduct.getRecruitAcceptDiv(),"4");
                assertEquals(l_bondProduct.getRecruitInvitationDiv(),"3");
                assertEquals(WEB3DateUtility.formatDate(l_bondProduct.getDeliveryDate(), "yyyyMMdd"),
                        WEB3DateUtility.formatDate(new Date(), "yyyyMMdd"));

            }
            catch (DataQueryException l_ex)
            {
                log.error("DBへのアクセスに失敗しました。", l_ex);
                log.exiting(STR_METHOD_NAME);
                throw new WEB3SystemLayerException(
                    WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                    this.getClass().getName() + STR_METHOD_NAME,
                    l_ex.getMessage(),
                    l_ex);
            }
            catch (DataNetworkException l_ex)
            {
                log.error("DBへのアクセスに失敗しました。", l_ex);
                log.exiting(STR_METHOD_NAME);
                throw new WEB3SystemLayerException(
                    WEB3ErrorCatalog.SYSTEM_ERROR_80003,
                    this.getClass().getName() + STR_METHOD_NAME,
                    l_ex.getMessage(),
                    l_ex);
            }
            
            
        }
        catch (WEB3BaseException l_ex)
        {
            log.error(l_ex.getErrorMessage(),l_ex);
            fail();
        }
        catch (Exception l_e)
        {
            log.error(l_e.getMessage());
            fail();
        }
        
        
    }
    
    public  WEB3AdminBondProductUpdateInfo getWEB3AdminBondProductUpdateInfo()
    {
        WEB3AdminBondProductUpdateInfo l_productUpdateInfo = new WEB3AdminBondProductUpdateInfo();
//      取扱区分　@　@　@　@　@　@： 債券銘柄更新情報.取扱区分
        l_productUpdateInfo.tradeHandleDiv = "2";
        //取扱開始日時　@　@　@　@： 債券銘柄更新情報.取扱開始日時
         l_productUpdateInfo.tradeStartDate = new Date();
        //取扱終了日時　@　@　@　@： 債券銘柄更新情報.取扱終了日時
        l_productUpdateInfo.tradeEndDate  = new Date();
        l_productUpdateInfo.deliveryDate =  new Date();
        //応募開始日　@　@　@　@　@： 債券銘柄更新情報.応募開始日
         l_productUpdateInfo.recruitStartDate  = new Date();
        //応募終了日　@　@　@　@　@： 債券銘柄更新情報.応募終了日
         l_productUpdateInfo.recruitEndDate = new Date();
        //売買区分　@　@　@　@　@　@： 債券銘柄更新情報.売買区分
         l_productUpdateInfo.buySellDiv ="1";
        //銘柄名　@　@　@　@　@： 債券銘柄更新情報.銘柄名
         l_productUpdateInfo.productName = "fff"; 
        //買付単価　@　@　@　@　@　@： 債券銘柄更新情報.買付単価
         l_productUpdateInfo.buyPrice="12";
        //売却単価　@　@　@　@　@　@： 債券銘柄更新情報.売却単価
         l_productUpdateInfo.sellPrice  ="13";
        //申込単位　@　@　@　@　@　@： 債券銘柄更新情報.申込単位
         l_productUpdateInfo.tradeUnit = "1";
        //最低額面　@　@　@　@　@　@： 債券銘柄更新情報.最低額面
         l_productUpdateInfo.minFaceAmount ="2";
        //最高額面　@　@　@　@　@　@： 債券銘柄更新情報.最高額面
         l_productUpdateInfo.maxFaceAmount ="100";
        //カレンダー連携市場コード　@： 債券銘柄更新情報.カレンダー連携市場コード
         l_productUpdateInfo.calendarLinkedDiv ="1";
        //買付受渡日移動日数　@： 債券銘柄更新情報.買付受渡日移動日数
         l_productUpdateInfo.buyDeliveryMove="2";
        //売却受渡日移動日数　@： 債券銘柄更新情報.売却受渡日移動日数       
         l_productUpdateInfo.sellDeliveryMove ="2";
        //自動約定区分　@　@　@　@： 債券銘柄更新情報.自動約定区分
         l_productUpdateInfo.autoExecDiv="2";
        //自動約定枠　@　@　@　@　@： 債券銘柄更新情報.自動約定枠
         l_productUpdateInfo.autoExecLimit="2";
        //カストディアンコード： 債券銘柄更新情報.カストディアンコード
         l_productUpdateInfo.custodianCode="2";
         l_productUpdateInfo.fxRateAtStock="2";
        //取引時間チェック区分： 債券銘柄更新情報.取引時間チェック区分
         l_productUpdateInfo.tradeTimeCheckDiv="2";
        //仲介手数料率　@　@　@　@： 債券銘柄更新情報.仲介手数料率
         l_productUpdateInfo.mediatorCommissionRate="2";
        //応募勧誘形式
         l_productUpdateInfo.recruitInvitationForm="3";
        //応募引受け区分
         l_productUpdateInfo.recruitAcceptDiv="4";
        return l_productUpdateInfo;
    }
}
@
