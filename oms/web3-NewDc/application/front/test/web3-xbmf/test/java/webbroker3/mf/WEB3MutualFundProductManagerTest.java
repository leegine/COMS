head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.04.07.02.15.08;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	5444d9d078c63f4;
filename	WEB3MutualFundProductManagerTest.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : 拡張投信銘柄マネージャクラス(WEB3MutualFundProductManagerTest.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2007/02/12 徐大方 (中訊) 新規作成 
*/

package webbroker3.mf;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import com.fitechlabs.xtrade.kernel.data.DataNetworkException;
import com.fitechlabs.xtrade.kernel.data.DataQueryException;
import com.fitechlabs.xtrade.kernel.util.ThreadLocalSystemAttributesRegistry;
import com.fitechlabs.xtrade.plugin.tc.gentrade.TradingSystem;
import com.fitechlabs.xtrade.plugin.tc.gentrade.data.InstitutionParams;
import com.fitechlabs.xtrade.plugin.tc.gentrade.data.InstitutionRow;
import com.fitechlabs.xtrade.plugin.tc.gentrade.data.MarketParams;
import com.fitechlabs.xtrade.plugin.tc.gentrade.data.MarketRow;
import com.fitechlabs.xtrade.plugin.tc.gentrade.data.ProductParams;
import com.fitechlabs.xtrade.plugin.tc.gentrade.data.ProductRow;
import com.fitechlabs.xtrade.plugin.tc.gentrade.data.TradedProductParams;
import com.fitechlabs.xtrade.plugin.tc.gentrade.data.TradedProductRow;
import com.fitechlabs.xtrade.plugin.tc.gentrade.stdimpls.InstitutionImpl;
import com.fitechlabs.xtrade.plugin.tc.xbmf.MutualFundTypeEnum;
import com.fitechlabs.xtrade.plugin.tc.xbmf.data.MutualFundProductParams;
import com.fitechlabs.xtrade.plugin.tc.xbmf.data.MutualFundProductRow;
import com.fitechlabs.xtrade.plugin.tc.xbmf.data.MutualFundTradedProductParams;

import test.util.TestDBUtility;
import webbroker3.common.WEB3BaseException;
import webbroker3.common.WEB3BusinessLayerException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.common.WEB3SystemLayerException;
import webbroker3.common.define.WEB3FixedBuyPossibleDivDef;
import webbroker3.gentrade.WEB3GentradeBizDate;
import webbroker3.gentrade.WEB3GentradeTradingClendarContext;
import webbroker3.gentrade.WEB3GentradeTradingTimeManagement;
import webbroker3.gentrade.data.TradingTimeParams;
import webbroker3.gentrade.data.TradingTimeRow;
import webbroker3.mf.data.MutualFund2ndProductSonarParams;
import webbroker3.mf.data.MutualFund2ndProductSonarRow;
import webbroker3.mf.data.MutualFundFrgncalParams;
import webbroker3.mf.data.MutualFundFrgncalRow;
import webbroker3.mf.data.MutualFundProductCategoryParams;
import webbroker3.mf.data.MutualFundProductCategoryRow;
import webbroker3.mock.TestBaseForMock;
import webbroker3.util.WEB3DateUtility;
import webbroker3.util.WEB3LogUtility;

/**
 * 拡張投信銘柄マネージャクラス
 * 
 * @@author 徐大方(中訊)
 * @@version 1.0
 */
public class WEB3MutualFundProductManagerTest extends TestBaseForMock
{
    /**
     * ログユーティリティ<BR>
     */
    private static WEB3LogUtility log =
        WEB3LogUtility.getInstance(WEB3MutualFundProductManagerTest.class);

    public WEB3MutualFundProductManagerTest(String arg0)
    {
        super(arg0);
    }

    protected void setUp() throws Exception
    {
        super.setUp();
        WEB3GentradeTradingClendarContext l_clendarContext = new WEB3GentradeTradingClendarContext();
        l_clendarContext.setInstitutionCode("0D");
        l_clendarContext.setBranchCode("381");
        l_clendarContext.setMarketCode("SP");
        l_clendarContext.setProductCode("12");
        l_clendarContext.setTradingTimeType("01");
        l_clendarContext.setOrderAcceptProduct("01");
        l_clendarContext.setBizDateType("1");

        ThreadLocalSystemAttributesRegistry.setAttribute(
            WEB3GentradeTradingTimeManagement.TRADING_CAL_CONTEXT_PATH,
            l_clendarContext);

        Calendar l_calendar = Calendar.getInstance();
        l_calendar.set(Calendar.HOUR_OF_DAY,15);
        l_calendar.set(Calendar.MINUTE,00);
        l_calendar.set(Calendar.SECOND,01);

        Timestamp l_tsBizDate = new Timestamp(l_calendar.getTimeInMillis());

        ThreadLocalSystemAttributesRegistry.setAttribute(
            WEB3GentradeTradingTimeManagement.TIMESTAMP_TAG, l_tsBizDate);

        TestDBUtility.deleteAll(MutualFundFrgncalRow.TYPE);
        TestDBUtility.deleteAll(MutualFundProductRow.TYPE);
        TestDBUtility.deleteAll(ProductRow.TYPE);
        TestDBUtility.deleteAll(InstitutionRow.TYPE);
        TestDBUtility.deleteAll(MarketRow.TYPE);
        TestDBUtility.deleteAll(TradedProductRow.TYPE);
        TestDBUtility.deleteAll(TradingTimeRow.TYPE);
        TestDBUtility.deleteAll(MutualFund2ndProductSonarRow.TYPE);
    }

    protected void tearDown() throws Exception
    {
        super.tearDown();
    }

    public void testGetDeliveryDateCase1()
    {
        final String STR_METHOD_NAME = "testGetDeliveryDateCase1()";
        log.entering(STR_METHOD_NAME);

        MutualFundProductParams l_mutualFundProductParams = TestDBUtility.getMutualFundProductRow();
        l_mutualFundProductParams.setInstitutionCode("0D");
        l_mutualFundProductParams.setProductCode("12");
        l_mutualFundProductParams.setFundType(MutualFundTypeEnum.FOREIGN_MMF);

        ProductParams l_productParams = TestDBUtility.getProductRow();
        l_productParams.setProductId(0100000L);

        InstitutionParams l_institutionParams = TestDBUtility.getInstitutionRow();
        MutualFundTradedProductParams l_mutualFundTradedProductParams = TestDBUtility.getMutualFundTradedProductRow();
        l_mutualFundTradedProductParams.setMarketId(3303L);
        l_mutualFundTradedProductParams.setInstitutionCode("0D");
        l_mutualFundTradedProductParams.setProductId(0100000L);

        MarketParams l_marketParams = TestDBUtility.getMarketRow();
        l_marketParams.setMarketCode("0");

        TradedProductParams l_tradedProductParams = TestDBUtility.getTradedProductRow();
        l_tradedProductParams.setTradedProductId(1006160060005L);

        TradingTimeParams l_tradingTimeParams = TestDBUtility.getTradingTimeRow();
        l_tradingTimeParams.setTradingTimeType("01");
        l_tradingTimeParams.setProductCode("12");
        l_tradingTimeParams.setBizDateType("1");

        MutualFundFrgncalParams l_mutualFundFrgncalParams = new MutualFundFrgncalParams();
        l_mutualFundFrgncalParams.setInstitutionCode("0D");
        l_mutualFundFrgncalParams.setProductCode("12");
        try
        {
            l_mutualFundFrgncalParams.setHoliday(new WEB3GentradeBizDate(new Timestamp(Calendar.getInstance().getTime().getTime())).roll(1));
        }
        catch (WEB3SystemLayerException l_ex)
        {
            log.error("", l_ex);
            log.exiting(STR_METHOD_NAME);
            fail();
        }
        l_mutualFundFrgncalParams.setLastUpdatedTimestamp(Calendar.getInstance().getTime());
        l_mutualFundFrgncalParams.setCreatedTimestamp(Calendar.getInstance().getTime());

        MutualFundFrgncalParams l_mutualFundFrgncalParams1 = new MutualFundFrgncalParams();
        l_mutualFundFrgncalParams1.setInstitutionCode("0D");
        l_mutualFundFrgncalParams1.setProductCode("12");
        l_mutualFundFrgncalParams1.setLastUpdatedTimestamp(Calendar.getInstance().getTime());
        l_mutualFundFrgncalParams1.setCreatedTimestamp(Calendar.getInstance().getTime());
        try
        {
            l_mutualFundFrgncalParams1.setHoliday(new WEB3GentradeBizDate(new Timestamp(Calendar.getInstance().getTime().getTime())).roll(2));
        }
        catch (WEB3SystemLayerException l_ex)
        {
            log.error("", l_ex);
            log.exiting(STR_METHOD_NAME);
            fail();
        }

        try
        {
            TestDBUtility.insertWithDel(l_mutualFundProductParams);
            TestDBUtility.insertWithDel(l_productParams);
            TestDBUtility.insertWithDel(l_institutionParams);
            TestDBUtility.insertWithDel(l_mutualFundTradedProductParams);
            TestDBUtility.insertWithDel(l_marketParams);
            TestDBUtility.insertWithDel(l_tradedProductParams);
            TestDBUtility.insertWithDel(l_tradingTimeParams);
            TestDBUtility.insertWithDel(l_mutualFundFrgncalParams);
            TestDBUtility.insertWithDel(l_mutualFundFrgncalParams1);
        }
        catch (WEB3BaseException l_ex)
        {
            log.error("", l_ex);
            log.exiting(STR_METHOD_NAME);
            fail();
        }
        InstitutionImpl l_institutionImpl = null;
        WEB3MutualFundProductManager l_mutualFundProductManager = new WEB3MutualFundProductManager();
        try
        {
            l_institutionImpl = new InstitutionImpl(33);
        }
        catch (DataQueryException l_ex)
        {
            log.error("", l_ex);
            log.exiting(STR_METHOD_NAME);
            fail();
        }
        catch (DataNetworkException l_ex)
        {
            log.error("", l_ex);
            log.exiting(STR_METHOD_NAME);
            fail();
        }
        try
        {
            Timestamp l_timestamp = l_mutualFundProductManager.getDeliveryDate(
                l_institutionImpl,
                "12",
                true);
            log.debug("l_timestamp="+l_timestamp);
            assertEquals(new WEB3GentradeBizDate(new Timestamp(Calendar.getInstance().getTime().getTime())).roll(6), l_timestamp);
        }
        catch (WEB3BaseException l_ex)
        {
            log.error("", l_ex);
            log.exiting(STR_METHOD_NAME);
            fail();
        }

        log.exiting(STR_METHOD_NAME);
    }

    public void testGetDeliveryDateCase2()
    {
        final String STR_METHOD_NAME = "testGetDeliveryDateCase2()";
        log.entering(STR_METHOD_NAME);

        MutualFundProductParams l_mutualFundProductParams = TestDBUtility.getMutualFundProductRow();
        l_mutualFundProductParams.setInstitutionCode("0D");
        l_mutualFundProductParams.setProductCode("0001000");
        l_mutualFundProductParams.setFundType(MutualFundTypeEnum.FOREIGN_MMF);

        ProductParams l_productParams = TestDBUtility.getProductRow();
        l_productParams.setProductId(0100000L);

        InstitutionParams l_institutionParams = TestDBUtility.getInstitutionRow();
        MutualFundTradedProductParams l_mutualFundTradedProductParams = TestDBUtility.getMutualFundTradedProductRow();
        l_mutualFundTradedProductParams.setMarketId(3303L);
        l_mutualFundTradedProductParams.setInstitutionCode("0D");
        l_mutualFundTradedProductParams.setProductId(0100000L);

        MarketParams l_marketParams = TestDBUtility.getMarketRow();
        l_marketParams.setMarketCode("0");

        TradedProductParams l_tradedProductParams = TestDBUtility.getTradedProductRow();
        l_tradedProductParams.setTradedProductId(1006160060005L);

        TradingTimeParams l_tradingTimeParams = TestDBUtility.getTradingTimeRow();
        l_tradingTimeParams.setTradingTimeType("01");
        l_tradingTimeParams.setProductCode("12");
        l_tradingTimeParams.setBizDateType("1");
        try
        {
            TestDBUtility.insertWithDel(l_mutualFundProductParams);
            TestDBUtility.insertWithDel(l_productParams);
            TestDBUtility.insertWithDel(l_institutionParams);
            TestDBUtility.insertWithDel(l_mutualFundTradedProductParams);
            TestDBUtility.insertWithDel(l_marketParams);
            TestDBUtility.insertWithDel(l_tradedProductParams);
            TestDBUtility.insertWithDel(l_tradingTimeParams);
        }
        catch (WEB3BaseException l_ex)
        {
            log.error("", l_ex);
            log.exiting(STR_METHOD_NAME);
            fail();
        }
        InstitutionImpl l_institutionImpl = null;
        WEB3MutualFundProductManager l_mutualFundProductManager = new WEB3MutualFundProductManager();
        try
        {
            l_institutionImpl = new InstitutionImpl(33);
        }
        catch (DataQueryException l_ex)
        {
            log.error("", l_ex);
            log.exiting(STR_METHOD_NAME);
            fail();
        }
        catch (DataNetworkException l_ex)
        {
            log.error("", l_ex);
            log.exiting(STR_METHOD_NAME);
            fail();
        }
        try
        {
            Timestamp l_timestamp = l_mutualFundProductManager.getDeliveryDate(
                l_institutionImpl,
                "0001000",
                true);
            log.debug("l_timestamp="+l_timestamp);
            assertEquals(new WEB3GentradeBizDate(new Timestamp(Calendar.getInstance().getTime().getTime())).roll(4), l_timestamp);
        }
        catch (WEB3BaseException l_ex)
        {
            log.error("", l_ex);
            log.exiting(STR_METHOD_NAME);
            fail();
        }

        log.exiting(STR_METHOD_NAME);
    }

    public void testGetDeliveryDateCase3()
    {
        final String STR_METHOD_NAME = "testGetDeliveryDateCase3()";
        log.entering(STR_METHOD_NAME);

        MutualFundProductParams l_mutualFundProductParams = TestDBUtility.getMutualFundProductRow();
        l_mutualFundProductParams.setInstitutionCode("0D");
        l_mutualFundProductParams.setProductCode("0001000");

        ProductParams l_productParams = TestDBUtility.getProductRow();
        l_productParams.setProductId(0100000L);

        InstitutionParams l_institutionParams = TestDBUtility.getInstitutionRow();
        MutualFundTradedProductParams l_mutualFundTradedProductParams = TestDBUtility.getMutualFundTradedProductRow();
        l_mutualFundTradedProductParams.setMarketId(3303L);
        l_mutualFundTradedProductParams.setInstitutionCode("0D");
        l_mutualFundTradedProductParams.setProductId(0100000L);

        MarketParams l_marketParams = TestDBUtility.getMarketRow();
        l_marketParams.setMarketCode("0");

        TradedProductParams l_tradedProductParams = TestDBUtility.getTradedProductRow();
        l_tradedProductParams.setTradedProductId(1006160060005L);

        TradingTimeParams l_tradingTimeParams = TestDBUtility.getTradingTimeRow();
        l_tradingTimeParams.setTradingTimeType("01");
        l_tradingTimeParams.setProductCode("12");
        l_tradingTimeParams.setBizDateType("1");
        try
        {
            TestDBUtility.insertWithDel(l_mutualFundProductParams);
            TestDBUtility.insertWithDel(l_productParams);
            TestDBUtility.insertWithDel(l_institutionParams);
            TestDBUtility.insertWithDel(l_mutualFundTradedProductParams);
            TestDBUtility.insertWithDel(l_marketParams);
            TestDBUtility.insertWithDel(l_tradedProductParams);
            TestDBUtility.insertWithDel(l_tradingTimeParams);
        }
        catch (WEB3BaseException l_ex)
        {
            log.error("", l_ex);
            log.exiting(STR_METHOD_NAME);
            fail();
        }
        InstitutionImpl l_institutionImpl = null;
        WEB3MutualFundProductManager l_mutualFundProductManager = new WEB3MutualFundProductManager();
        try
        {
            l_institutionImpl = new InstitutionImpl(33);
        }
        catch (DataQueryException l_ex)
        {
            log.error("", l_ex);
            log.exiting(STR_METHOD_NAME);
            fail();
        }
        catch (DataNetworkException l_ex)
        {
            log.error("", l_ex);
            log.exiting(STR_METHOD_NAME);
            fail();
        }
        try
        {
            Timestamp l_timestamp = l_mutualFundProductManager.getDeliveryDate(
                l_institutionImpl,
                "0001000",
                true);
            log.debug("l_timestamp="+l_timestamp);
            assertEquals(new WEB3GentradeBizDate(new Timestamp(Calendar.getInstance().getTime().getTime())).roll(4), l_timestamp);
        }
        catch (WEB3BaseException l_ex)
        {
            log.error("", l_ex);
            log.exiting(STR_METHOD_NAME);
            fail();
        }

        log.exiting(STR_METHOD_NAME);
    }

    public void testGetDeliveryDateCase4()
    {
        final String STR_METHOD_NAME = "testGetDeliveryDateCase4()";
        log.entering(STR_METHOD_NAME);

        MutualFundProductParams l_mutualFundProductParams = TestDBUtility.getMutualFundProductRow();
        l_mutualFundProductParams.setInstitutionCode("0D");
        l_mutualFundProductParams.setProductCode("0001000");

        ProductParams l_productParams = TestDBUtility.getProductRow();
        l_productParams.setProductId(0100000L);

        InstitutionParams l_institutionParams = TestDBUtility.getInstitutionRow();
        MutualFundTradedProductParams l_mutualFundTradedProductParams = TestDBUtility.getMutualFundTradedProductRow();
        l_mutualFundTradedProductParams.setMarketId(3303L);
        l_mutualFundTradedProductParams.setInstitutionCode("0D");
        l_mutualFundTradedProductParams.setProductId(11111);

        MarketParams l_marketParams = TestDBUtility.getMarketRow();
        l_marketParams.setMarketCode("0");

        TradedProductParams l_tradedProductParams = TestDBUtility.getTradedProductRow();
        l_tradedProductParams.setTradedProductId(1006160060005L);

        TradingTimeParams l_tradingTimeParams = TestDBUtility.getTradingTimeRow();
        l_tradingTimeParams.setTradingTimeType("01");
        l_tradingTimeParams.setProductCode("12");
        l_tradingTimeParams.setBizDateType("1");
        try
        {
            TestDBUtility.insertWithDel(l_mutualFundProductParams);
            TestDBUtility.insertWithDel(l_productParams);
            TestDBUtility.insertWithDel(l_institutionParams);
            TestDBUtility.insertWithDel(l_mutualFundTradedProductParams);
            TestDBUtility.insertWithDel(l_marketParams);
            TestDBUtility.insertWithDel(l_tradedProductParams);
            TestDBUtility.insertWithDel(l_tradingTimeParams);
        }
        catch (WEB3BaseException l_ex)
        {
            log.error("", l_ex);
            log.exiting(STR_METHOD_NAME);
            fail();
        }
        InstitutionImpl l_institutionImpl = null;
        WEB3MutualFundProductManager l_mutualFundProductManager = new WEB3MutualFundProductManager();
        try
        {
            l_institutionImpl = new InstitutionImpl(33);
        }
        catch (DataQueryException l_ex)
        {
            log.error("", l_ex);
            log.exiting(STR_METHOD_NAME);
            fail();
        }
        catch (DataNetworkException l_ex)
        {
            log.error("", l_ex);
            log.exiting(STR_METHOD_NAME);
            fail();
        }
        try
        {
            l_mutualFundProductManager.getDeliveryDate(
                l_institutionImpl,
                "0001000",
                true);
        }
        catch (WEB3BaseException l_ex)
        {
            log.error("", l_ex);
            log.exiting(STR_METHOD_NAME);
            assertEquals(WEB3ErrorCatalog.SYSTEM_ERROR_80005, l_ex.getErrorInfo());
        }

        log.exiting(STR_METHOD_NAME);
    }

    public void testGetDeliveryDateCase5()
    {
        final String STR_METHOD_NAME = "testGetDeliveryDateCase5()";
        log.entering(STR_METHOD_NAME);

        MutualFundProductParams l_mutualFundProductParams = TestDBUtility.getMutualFundProductRow();
        l_mutualFundProductParams.setInstitutionCode("0D");
        l_mutualFundProductParams.setProductCode("12");
        l_mutualFundProductParams.setFundType(MutualFundTypeEnum.FOREIGN_MMF);

        ProductParams l_productParams = TestDBUtility.getProductRow();
        l_productParams.setProductId(0100000L);

        InstitutionParams l_institutionParams = TestDBUtility.getInstitutionRow();
        MutualFundTradedProductParams l_mutualFundTradedProductParams = TestDBUtility.getMutualFundTradedProductRow();
        l_mutualFundTradedProductParams.setMarketId(3303L);
        l_mutualFundTradedProductParams.setInstitutionCode("0D");
        l_mutualFundTradedProductParams.setProductId(0100000L);

        MarketParams l_marketParams = TestDBUtility.getMarketRow();
        l_marketParams.setMarketCode("0");

        TradedProductParams l_tradedProductParams = TestDBUtility.getTradedProductRow();
        l_tradedProductParams.setTradedProductId(1006160060005L);

        TradingTimeParams l_tradingTimeParams = TestDBUtility.getTradingTimeRow();
        l_tradingTimeParams.setTradingTimeType("01");
        l_tradingTimeParams.setProductCode("12");
        l_tradingTimeParams.setBizDateType("1");

        MutualFundFrgncalParams l_mutualFundFrgncalParams = new MutualFundFrgncalParams();
        l_mutualFundFrgncalParams.setInstitutionCode("0D");
        l_mutualFundFrgncalParams.setProductCode("12");
        try
        {
            l_mutualFundFrgncalParams.setHoliday(new WEB3GentradeBizDate(new Timestamp(Calendar.getInstance().getTime().getTime())).roll(1));
        }
        catch (WEB3SystemLayerException l_ex)
        {
            log.error("", l_ex);
            log.exiting(STR_METHOD_NAME);
            fail();
        }
        l_mutualFundFrgncalParams.setLastUpdatedTimestamp(Calendar.getInstance().getTime());
        l_mutualFundFrgncalParams.setCreatedTimestamp(Calendar.getInstance().getTime());

        MutualFundFrgncalParams l_mutualFundFrgncalParams1 = new MutualFundFrgncalParams();
        l_mutualFundFrgncalParams1.setInstitutionCode("0D");
        l_mutualFundFrgncalParams1.setProductCode("12");
        l_mutualFundFrgncalParams1.setLastUpdatedTimestamp(Calendar.getInstance().getTime());
        l_mutualFundFrgncalParams1.setCreatedTimestamp(Calendar.getInstance().getTime());
        try
        {
            l_mutualFundFrgncalParams1.setHoliday(new WEB3GentradeBizDate(new Timestamp(Calendar.getInstance().getTime().getTime())).roll(2));
        }
        catch (WEB3SystemLayerException l_ex)
        {
            log.error("", l_ex);
            log.exiting(STR_METHOD_NAME);
            fail();
        }

        try
        {
            TestDBUtility.insertWithDel(l_mutualFundProductParams);
            TestDBUtility.insertWithDel(l_productParams);
            TestDBUtility.insertWithDel(l_institutionParams);
            TestDBUtility.insertWithDel(l_mutualFundTradedProductParams);
            TestDBUtility.insertWithDel(l_marketParams);
            TestDBUtility.insertWithDel(l_tradedProductParams);
            TestDBUtility.insertWithDel(l_tradingTimeParams);
            TestDBUtility.insertWithDel(l_mutualFundFrgncalParams);
            TestDBUtility.insertWithDel(l_mutualFundFrgncalParams1);
        }
        catch (WEB3BaseException l_ex)
        {
            log.error("", l_ex);
            log.exiting(STR_METHOD_NAME);
            fail();
        }
        InstitutionImpl l_institutionImpl = null;
        WEB3MutualFundProductManager l_mutualFundProductManager = new WEB3MutualFundProductManager();
        try
        {
            l_institutionImpl = new InstitutionImpl(33);
        }
        catch (DataQueryException l_ex)
        {
            log.error("", l_ex);
            log.exiting(STR_METHOD_NAME);
            fail();
        }
        catch (DataNetworkException l_ex)
        {
            log.error("", l_ex);
            log.exiting(STR_METHOD_NAME);
            fail();
        }
        try
        {
            Timestamp l_timestamp = l_mutualFundProductManager.getDeliveryDate(
                l_institutionImpl,
                "12",
                true,
                new WEB3GentradeBizDate(new Timestamp(Calendar.getInstance().getTime().getTime())).roll(3));
            log.debug("l_timestamp="+l_timestamp);
            assertEquals(new WEB3GentradeBizDate(new Timestamp(Calendar.getInstance().getTime().getTime())).roll(6), l_timestamp);
        }
        catch (WEB3BaseException l_ex)
        {
            log.error("", l_ex);
            log.exiting(STR_METHOD_NAME);
            fail();
        }

        log.exiting(STR_METHOD_NAME);
    }

    public void testGetDeliveryDateCase6()
    {
        final String STR_METHOD_NAME = "testGetDeliveryDateCase6()";
        log.entering(STR_METHOD_NAME);

        MutualFundProductParams l_mutualFundProductParams = TestDBUtility.getMutualFundProductRow();
        l_mutualFundProductParams.setInstitutionCode("0D");
        l_mutualFundProductParams.setProductCode("0001000");
        l_mutualFundProductParams.setFundType(MutualFundTypeEnum.FOREIGN_MMF);

        ProductParams l_productParams = TestDBUtility.getProductRow();
        l_productParams.setProductId(0100000L);

        InstitutionParams l_institutionParams = TestDBUtility.getInstitutionRow();
        MutualFundTradedProductParams l_mutualFundTradedProductParams = TestDBUtility.getMutualFundTradedProductRow();
        l_mutualFundTradedProductParams.setMarketId(3303L);
        l_mutualFundTradedProductParams.setInstitutionCode("0D");
        l_mutualFundTradedProductParams.setProductId(0100000L);

        MarketParams l_marketParams = TestDBUtility.getMarketRow();
        l_marketParams.setMarketCode("0");

        TradedProductParams l_tradedProductParams = TestDBUtility.getTradedProductRow();
        l_tradedProductParams.setTradedProductId(1006160060005L);

        TradingTimeParams l_tradingTimeParams = TestDBUtility.getTradingTimeRow();
        l_tradingTimeParams.setTradingTimeType("01");
        l_tradingTimeParams.setProductCode("12");
        l_tradingTimeParams.setBizDateType("1");
        try
        {
            TestDBUtility.insertWithDel(l_mutualFundProductParams);
            TestDBUtility.insertWithDel(l_productParams);
            TestDBUtility.insertWithDel(l_institutionParams);
            TestDBUtility.insertWithDel(l_mutualFundTradedProductParams);
            TestDBUtility.insertWithDel(l_marketParams);
            TestDBUtility.insertWithDel(l_tradedProductParams);
            TestDBUtility.insertWithDel(l_tradingTimeParams);
        }
        catch (WEB3BaseException l_ex)
        {
            log.error("", l_ex);
            log.exiting(STR_METHOD_NAME);
            fail();
        }
        InstitutionImpl l_institutionImpl = null;
        WEB3MutualFundProductManager l_mutualFundProductManager = new WEB3MutualFundProductManager();
        try
        {
            l_institutionImpl = new InstitutionImpl(33);
        }
        catch (DataQueryException l_ex)
        {
            log.error("", l_ex);
            log.exiting(STR_METHOD_NAME);
            fail();
        }
        catch (DataNetworkException l_ex)
        {
            log.error("", l_ex);
            log.exiting(STR_METHOD_NAME);
            fail();
        }
        try
        {
            Timestamp l_timestamp = l_mutualFundProductManager.getDeliveryDate(
                l_institutionImpl,
                "0001000",
                true,
                new WEB3GentradeBizDate(new Timestamp(Calendar.getInstance().getTime().getTime())).roll(1));
            log.debug("l_timestamp="+l_timestamp);
            assertEquals(new WEB3GentradeBizDate(new Timestamp(Calendar.getInstance().getTime().getTime())).roll(4), l_timestamp);
        }
        catch (WEB3BaseException l_ex)
        {
            log.error("", l_ex);
            log.exiting(STR_METHOD_NAME);
            fail();
        }

        log.exiting(STR_METHOD_NAME);
    }

    public void testGetDeliveryDateCase7()
    {
        final String STR_METHOD_NAME = "testGetDeliveryDateCase7()";
        log.entering(STR_METHOD_NAME);

        MutualFundProductParams l_mutualFundProductParams = TestDBUtility.getMutualFundProductRow();
        l_mutualFundProductParams.setInstitutionCode("0D");
        l_mutualFundProductParams.setProductCode("0001000");

        ProductParams l_productParams = TestDBUtility.getProductRow();
        l_productParams.setProductId(0100000L);

        InstitutionParams l_institutionParams = TestDBUtility.getInstitutionRow();
        MutualFundTradedProductParams l_mutualFundTradedProductParams = TestDBUtility.getMutualFundTradedProductRow();
        l_mutualFundTradedProductParams.setMarketId(3303L);
        l_mutualFundTradedProductParams.setInstitutionCode("0D");
        l_mutualFundTradedProductParams.setProductId(0100000L);

        MarketParams l_marketParams = TestDBUtility.getMarketRow();
        l_marketParams.setMarketCode("0");

        TradedProductParams l_tradedProductParams = TestDBUtility.getTradedProductRow();
        l_tradedProductParams.setTradedProductId(1006160060005L);

        TradingTimeParams l_tradingTimeParams = TestDBUtility.getTradingTimeRow();
        l_tradingTimeParams.setTradingTimeType("01");
        l_tradingTimeParams.setProductCode("12");
        l_tradingTimeParams.setBizDateType("1");
        try
        {
            TestDBUtility.insertWithDel(l_mutualFundProductParams);
            TestDBUtility.insertWithDel(l_productParams);
            TestDBUtility.insertWithDel(l_institutionParams);
            TestDBUtility.insertWithDel(l_mutualFundTradedProductParams);
            TestDBUtility.insertWithDel(l_marketParams);
            TestDBUtility.insertWithDel(l_tradedProductParams);
            TestDBUtility.insertWithDel(l_tradingTimeParams);
        }
        catch (WEB3BaseException l_ex)
        {
            log.error("", l_ex);
            log.exiting(STR_METHOD_NAME);
            fail();
        }
        InstitutionImpl l_institutionImpl = null;
        WEB3MutualFundProductManager l_mutualFundProductManager = new WEB3MutualFundProductManager();
        try
        {
            l_institutionImpl = new InstitutionImpl(33);
        }
        catch (DataQueryException l_ex)
        {
            log.error("", l_ex);
            log.exiting(STR_METHOD_NAME);
            fail();
        }
        catch (DataNetworkException l_ex)
        {
            log.error("", l_ex);
            log.exiting(STR_METHOD_NAME);
            fail();
        }
        try
        {
            Timestamp l_timestamp = l_mutualFundProductManager.getDeliveryDate(
                l_institutionImpl,
                "0001000",
                true,
                new WEB3GentradeBizDate(new Timestamp(Calendar.getInstance().getTime().getTime())).roll(2));
            log.debug("l_timestamp="+l_timestamp);
            assertEquals(new WEB3GentradeBizDate(new Timestamp(Calendar.getInstance().getTime().getTime())).roll(5), l_timestamp);
        }
        catch (WEB3BaseException l_ex)
        {
            log.error("", l_ex);
            log.exiting(STR_METHOD_NAME);
            fail();
        }

        log.exiting(STR_METHOD_NAME);
    }

    public void testGetDeliveryDateCase8()
    {
        final String STR_METHOD_NAME = "testGetDeliveryDateCase8()";
        log.entering(STR_METHOD_NAME);

        MutualFundProductParams l_mutualFundProductParams = TestDBUtility.getMutualFundProductRow();
        l_mutualFundProductParams.setInstitutionCode("0D");
        l_mutualFundProductParams.setProductCode("0001000");

        ProductParams l_productParams = TestDBUtility.getProductRow();
        l_productParams.setProductId(0100000L);

        InstitutionParams l_institutionParams = TestDBUtility.getInstitutionRow();
        MutualFundTradedProductParams l_mutualFundTradedProductParams = TestDBUtility.getMutualFundTradedProductRow();
        l_mutualFundTradedProductParams.setMarketId(3303L);
        l_mutualFundTradedProductParams.setInstitutionCode("0D");
        l_mutualFundTradedProductParams.setProductId(11111);

        MarketParams l_marketParams = TestDBUtility.getMarketRow();
        l_marketParams.setMarketCode("0");

        TradedProductParams l_tradedProductParams = TestDBUtility.getTradedProductRow();
        l_tradedProductParams.setTradedProductId(1006160060005L);

        TradingTimeParams l_tradingTimeParams = TestDBUtility.getTradingTimeRow();
        l_tradingTimeParams.setTradingTimeType("01");
        l_tradingTimeParams.setProductCode("12");
        l_tradingTimeParams.setBizDateType("1");
        try
        {
            TestDBUtility.insertWithDel(l_mutualFundProductParams);
            TestDBUtility.insertWithDel(l_productParams);
            TestDBUtility.insertWithDel(l_institutionParams);
            TestDBUtility.insertWithDel(l_mutualFundTradedProductParams);
            TestDBUtility.insertWithDel(l_marketParams);
            TestDBUtility.insertWithDel(l_tradedProductParams);
            TestDBUtility.insertWithDel(l_tradingTimeParams);
        }
        catch (WEB3BaseException l_ex)
        {
            log.error("", l_ex);
            log.exiting(STR_METHOD_NAME);
            fail();
        }
        InstitutionImpl l_institutionImpl = null;
        WEB3MutualFundProductManager l_mutualFundProductManager = new WEB3MutualFundProductManager();
        try
        {
            l_institutionImpl = new InstitutionImpl(33);
        }
        catch (DataQueryException l_ex)
        {
            log.error("", l_ex);
            log.exiting(STR_METHOD_NAME);
            fail();
        }
        catch (DataNetworkException l_ex)
        {
            log.error("", l_ex);
            log.exiting(STR_METHOD_NAME);
            fail();
        }
        try
        {
            l_mutualFundProductManager.getDeliveryDate(
                l_institutionImpl,
                "0001000",
                true,
                null);
        }
        catch (WEB3BaseException l_ex)
        {
            log.error("", l_ex);
            log.exiting(STR_METHOD_NAME);
            assertEquals(WEB3ErrorCatalog.SYSTEM_ERROR_80005, l_ex.getErrorInfo());
        }

        log.exiting(STR_METHOD_NAME);
    }

    public void testValidateProductCondCase1()
    {
        final String STR_METHOD_NAME = "testValidateProductCondCase1()";
        log.entering(STR_METHOD_NAME);

        MutualFundProductParams l_mutualFundProductParams = TestDBUtility.getMutualFundProductRow();
        l_mutualFundProductParams.setInstitutionCode("0D");
        l_mutualFundProductParams.setProductCode("12");
        l_mutualFundProductParams.setFundType(MutualFundTypeEnum.FOREIGN_MMF);
        l_mutualFundProductParams.setRedemptionDate(new Timestamp(WEB3DateUtility.getDate("20040715","yyyyMMdd").getTime()));

        ProductParams l_productParams = TestDBUtility.getProductRow();
        l_productParams.setProductId(0100000L);

        InstitutionParams l_institutionParams = TestDBUtility.getInstitutionRow();
        MutualFundTradedProductParams l_mutualFundTradedProductParams = TestDBUtility.getMutualFundTradedProductRow();
        l_mutualFundTradedProductParams.setMarketId(3303L);
        l_mutualFundTradedProductParams.setInstitutionCode("0D");
        l_mutualFundTradedProductParams.setProductId(0100000L);
        l_mutualFundTradedProductParams.setExecDateShiftdays(3);

        MarketParams l_marketParams = TestDBUtility.getMarketRow();
        l_marketParams.setMarketCode("0");

        TradedProductParams l_tradedProductParams = TestDBUtility.getTradedProductRow();
        l_tradedProductParams.setTradedProductId(1006160060005L);

        TradingTimeParams l_tradingTimeParams = TestDBUtility.getTradingTimeRow();
        l_tradingTimeParams.setTradingTimeType("01");
        l_tradingTimeParams.setProductCode("12");
        l_tradingTimeParams.setBizDateType("1");

        try
        {
            TestDBUtility.insertWithDel(l_mutualFundProductParams);
            TestDBUtility.insertWithDel(l_productParams);
            TestDBUtility.insertWithDel(l_institutionParams);
            TestDBUtility.insertWithDel(l_mutualFundTradedProductParams);
            TestDBUtility.insertWithDel(l_marketParams);
            TestDBUtility.insertWithDel(l_tradedProductParams);
            TestDBUtility.insertWithDel(l_tradingTimeParams);
        }
        catch (WEB3BaseException l_ex)
        {
            log.error("", l_ex);
            log.exiting(STR_METHOD_NAME);
            fail();
        }
        try
        {
        	InstitutionImpl l_institutionImpl = new InstitutionImpl(33);

        	WEB3MutualFundProductCondSpec l_productCondSpec = new WEB3MutualFundProductCondSpec();
        	l_productCondSpec.setMutualProductCode("12");
        	l_productCondSpec.setBuyEndDate(new Timestamp(WEB3DateUtility.getDate("20040712","yyyyMMdd").getTime()));

        	WEB3MutualFundProductManager l_mutualFundProductManager = new WEB3MutualFundProductManager();
            l_mutualFundProductManager.validateProductCond(
                l_institutionImpl,
                l_productCondSpec);
            fail();
        }
        catch (WEB3BusinessLayerException l_blex)
        {
            log.error("ERROR", l_blex);
            assertEquals(WEB3ErrorCatalog.BUSINESS_ERROR_00336, l_blex.getErrorInfo());
        }
        catch (Exception l_ex)
        {
            log.error("ERROR", l_ex);
            fail();
        }
        log.exiting(STR_METHOD_NAME);
    }

    public void testValidateProductCondCase2()
    {
        final String STR_METHOD_NAME = "testValidateProductCondCase2()";
        log.entering(STR_METHOD_NAME);

        MutualFundProductParams l_mutualFundProductParams = TestDBUtility.getMutualFundProductRow();
        l_mutualFundProductParams.setInstitutionCode("0D");
        l_mutualFundProductParams.setProductCode("12");
        l_mutualFundProductParams.setFundType(MutualFundTypeEnum.FOREIGN_MMF);
        l_mutualFundProductParams.setRedemptionDate(new Timestamp(WEB3DateUtility.getDate("20040715","yyyyMMdd").getTime()));

        ProductParams l_productParams = TestDBUtility.getProductRow();
        l_productParams.setProductId(0100000L);

        InstitutionParams l_institutionParams = TestDBUtility.getInstitutionRow();
        MutualFundTradedProductParams l_mutualFundTradedProductParams = TestDBUtility.getMutualFundTradedProductRow();
        l_mutualFundTradedProductParams.setMarketId(3303L);
        l_mutualFundTradedProductParams.setInstitutionCode("0D");
        l_mutualFundTradedProductParams.setProductId(0100000L);
        l_mutualFundTradedProductParams.setExecDateShiftdays(3);

        MarketParams l_marketParams = TestDBUtility.getMarketRow();
        l_marketParams.setMarketCode("0");

        TradedProductParams l_tradedProductParams = TestDBUtility.getTradedProductRow();
        l_tradedProductParams.setTradedProductId(1006160060005L);

        TradingTimeParams l_tradingTimeParams = TestDBUtility.getTradingTimeRow();
        l_tradingTimeParams.setTradingTimeType("01");
        l_tradingTimeParams.setProductCode("12");
        l_tradingTimeParams.setBizDateType("1");

        try
        {
            TestDBUtility.insertWithDel(l_mutualFundProductParams);
            TestDBUtility.insertWithDel(l_productParams);
            TestDBUtility.insertWithDel(l_institutionParams);
            TestDBUtility.insertWithDel(l_mutualFundTradedProductParams);
            TestDBUtility.insertWithDel(l_marketParams);
            TestDBUtility.insertWithDel(l_tradedProductParams);
            TestDBUtility.insertWithDel(l_tradingTimeParams);
        }
        catch (WEB3BaseException l_ex)
        {
            log.error("", l_ex);
            log.exiting(STR_METHOD_NAME);
            fail();
        }
        try
        {
        	InstitutionImpl l_institutionImpl = new InstitutionImpl(33);

        	WEB3MutualFundProductCondSpec l_productCondSpec = new WEB3MutualFundProductCondSpec();
        	l_productCondSpec.setMutualProductCode("12");
        	l_productCondSpec.setBuyEndDate(null);
        	l_productCondSpec.setSellSwitchingEndDate(new Timestamp(WEB3DateUtility.getDate("20040712","yyyyMMdd").getTime()));

        	WEB3MutualFundProductManager l_mutualFundProductManager = new WEB3MutualFundProductManager();
            l_mutualFundProductManager.validateProductCond(
                l_institutionImpl,
                l_productCondSpec);
            fail();
        }
        catch (WEB3BusinessLayerException l_blex)
        {
            log.error("ERROR", l_blex);
            assertEquals(WEB3ErrorCatalog.BUSINESS_ERROR_00338, l_blex.getErrorInfo());
        }
        catch (Exception l_ex)
        {
            log.error("ERROR", l_ex);
            fail();
        }
        log.exiting(STR_METHOD_NAME);
    }

    public void testValidateProductCondCase3()
    {
        final String STR_METHOD_NAME = "testValidateProductCondCase3()";
        log.entering(STR_METHOD_NAME);

        MutualFundProductParams l_mutualFundProductParams = TestDBUtility.getMutualFundProductRow();
        l_mutualFundProductParams.setInstitutionCode("0D");
        l_mutualFundProductParams.setProductCode("12");
        l_mutualFundProductParams.setFundType(MutualFundTypeEnum.FOREIGN_MMF);
        l_mutualFundProductParams.setRedemptionDate(new Timestamp(WEB3DateUtility.getDate("20040715","yyyyMMdd").getTime()));

        ProductParams l_productParams = TestDBUtility.getProductRow();
        l_productParams.setProductId(0100000L);

        InstitutionParams l_institutionParams = TestDBUtility.getInstitutionRow();
        MutualFundTradedProductParams l_mutualFundTradedProductParams = TestDBUtility.getMutualFundTradedProductRow();
        l_mutualFundTradedProductParams.setMarketId(3303L);
        l_mutualFundTradedProductParams.setInstitutionCode("0D");
        l_mutualFundTradedProductParams.setProductId(0100000L);
        l_mutualFundTradedProductParams.setExecDateShiftdays(3);

        MarketParams l_marketParams = TestDBUtility.getMarketRow();
        l_marketParams.setMarketCode("0");

        TradedProductParams l_tradedProductParams = TestDBUtility.getTradedProductRow();
        l_tradedProductParams.setTradedProductId(1006160060005L);

        TradingTimeParams l_tradingTimeParams = TestDBUtility.getTradingTimeRow();
        l_tradingTimeParams.setTradingTimeType("01");
        l_tradingTimeParams.setProductCode("12");
        l_tradingTimeParams.setBizDateType("1");

        MutualFund2ndProductSonarParams l_mutualFundProductSonar2Params = new MutualFund2ndProductSonarParams();
        l_mutualFundProductSonar2Params.setInstitutionCode("0D");
        l_mutualFundProductSonar2Params.setProductCode("12");
        l_mutualFundProductSonar2Params.setFrgnBuyMinAmt(10);

        try
        {
            TestDBUtility.insertWithDel(l_mutualFundProductParams);
            TestDBUtility.insertWithDel(l_productParams);
            TestDBUtility.insertWithDel(l_institutionParams);
            TestDBUtility.insertWithDel(l_mutualFundTradedProductParams);
            TestDBUtility.insertWithDel(l_marketParams);
            TestDBUtility.insertWithDel(l_tradedProductParams);
            TestDBUtility.insertWithDel(l_tradingTimeParams);
            TestDBUtility.insertWithDel(l_mutualFundProductSonar2Params);
        }
        catch (WEB3BaseException l_ex)
        {
            log.error("", l_ex);
            log.exiting(STR_METHOD_NAME);
            fail();
        }
        try
        {
        	InstitutionImpl l_institutionImpl = new InstitutionImpl(33);

        	WEB3MutualFundProductCondSpec l_productCondSpec = new WEB3MutualFundProductCondSpec();
        	l_productCondSpec.setMutualProductCode("12");
        	l_productCondSpec.setBuyEndDate(new Timestamp(WEB3DateUtility.getDate("20040708","yyyyMMdd").getTime()));
        	l_productCondSpec.setSellSwitchingEndDate(null);
        	l_productCondSpec.setBuyFrgnMinAmtBuy("9");

        	WEB3MutualFundProductManager l_mutualFundProductManager = new WEB3MutualFundProductManager();
            l_mutualFundProductManager.validateProductCond(
                l_institutionImpl,
                l_productCondSpec);
            fail();
        }
        catch (WEB3BusinessLayerException l_blex)
        {
            log.error("ERROR", l_blex);
            assertEquals(WEB3ErrorCatalog.BUSINESS_ERROR_02745, l_blex.getErrorInfo());
        }
        catch (Exception l_ex)
        {
            log.error("ERROR", l_ex);
            fail();
        }
        log.exiting(STR_METHOD_NAME);
    }

    public void testValidateProductCondCase4()
    {
        final String STR_METHOD_NAME = "testValidateProductCondCase4()";
        log.entering(STR_METHOD_NAME);

        MutualFundProductParams l_mutualFundProductParams = TestDBUtility.getMutualFundProductRow();
        l_mutualFundProductParams.setInstitutionCode("0D");
        l_mutualFundProductParams.setProductCode("12");
        l_mutualFundProductParams.setFundType(MutualFundTypeEnum.FOREIGN_MMF);
        l_mutualFundProductParams.setRedemptionDate(new Timestamp(WEB3DateUtility.getDate("20040715","yyyyMMdd").getTime()));

        ProductParams l_productParams = TestDBUtility.getProductRow();
        l_productParams.setProductId(0100000L);

        InstitutionParams l_institutionParams = TestDBUtility.getInstitutionRow();
        MutualFundTradedProductParams l_mutualFundTradedProductParams = TestDBUtility.getMutualFundTradedProductRow();
        l_mutualFundTradedProductParams.setMarketId(3303L);
        l_mutualFundTradedProductParams.setInstitutionCode("0D");
        l_mutualFundTradedProductParams.setProductId(0100000L);
        l_mutualFundTradedProductParams.setExecDateShiftdays(3);

        MarketParams l_marketParams = TestDBUtility.getMarketRow();
        l_marketParams.setMarketCode("0");

        TradedProductParams l_tradedProductParams = TestDBUtility.getTradedProductRow();
        l_tradedProductParams.setTradedProductId(1006160060005L);

        TradingTimeParams l_tradingTimeParams = TestDBUtility.getTradingTimeRow();
        l_tradingTimeParams.setTradingTimeType("01");
        l_tradingTimeParams.setProductCode("12");
        l_tradingTimeParams.setBizDateType("1");

        MutualFund2ndProductSonarParams l_mutualFundProductSonar2Params = new MutualFund2ndProductSonarParams();
        l_mutualFundProductSonar2Params.setInstitutionCode("0D");
        l_mutualFundProductSonar2Params.setProductCode("12");
        l_mutualFundProductSonar2Params.setFrgnBuyMinAmt(10);
        l_mutualFundProductSonar2Params.setFrgnBuyUnitAmt(9);

        try
        {
            TestDBUtility.insertWithDel(l_mutualFundProductParams);
            TestDBUtility.insertWithDel(l_productParams);
            TestDBUtility.insertWithDel(l_institutionParams);
            TestDBUtility.insertWithDel(l_mutualFundTradedProductParams);
            TestDBUtility.insertWithDel(l_marketParams);
            TestDBUtility.insertWithDel(l_tradedProductParams);
            TestDBUtility.insertWithDel(l_tradingTimeParams);
            TestDBUtility.insertWithDel(l_mutualFundProductSonar2Params);
        }
        catch (WEB3BaseException l_ex)
        {
            log.error("", l_ex);
            log.exiting(STR_METHOD_NAME);
            fail();
        }
        try
        {
        	InstitutionImpl l_institutionImpl = new InstitutionImpl(33);

        	WEB3MutualFundProductCondSpec l_productCondSpec = new WEB3MutualFundProductCondSpec();
        	l_productCondSpec.setMutualProductCode("12");
        	l_productCondSpec.setBuyEndDate(new Timestamp(WEB3DateUtility.getDate("20040709","yyyyMMdd").getTime()));
        	l_productCondSpec.setSellSwitchingEndDate(new Timestamp(WEB3DateUtility.getDate("20040708","yyyyMMdd").getTime()));
        	l_productCondSpec.setBuyFrgnMinAmtBuy(null);
        	l_productCondSpec.setBuyFrgnUnitAmtBuy("19");

        	WEB3MutualFundProductManager l_mutualFundProductManager = new WEB3MutualFundProductManager();
            l_mutualFundProductManager.validateProductCond(
                l_institutionImpl,
                l_productCondSpec);
            fail();
        }
        catch (WEB3BusinessLayerException l_blex)
        {
            log.error("ERROR", l_blex);
            assertEquals(WEB3ErrorCatalog.BUSINESS_ERROR_02746, l_blex.getErrorInfo());
        }
        catch (Exception l_ex)
        {
            log.error("ERROR", l_ex);
            fail();
        }
        log.exiting(STR_METHOD_NAME);
    }

    public void testValidateProductCondCase5()
    {
        final String STR_METHOD_NAME = "testValidateProductCondCase5()";
        log.entering(STR_METHOD_NAME);

        MutualFundProductParams l_mutualFundProductParams = TestDBUtility.getMutualFundProductRow();
        l_mutualFundProductParams.setInstitutionCode("0D");
        l_mutualFundProductParams.setProductCode("12");
        l_mutualFundProductParams.setFundType(MutualFundTypeEnum.FOREIGN_MMF);
        l_mutualFundProductParams.setRedemptionDate(new Timestamp(WEB3DateUtility.getDate("20040715","yyyyMMdd").getTime()));

        ProductParams l_productParams = TestDBUtility.getProductRow();
        l_productParams.setProductId(0100000L);

        InstitutionParams l_institutionParams = TestDBUtility.getInstitutionRow();
        MutualFundTradedProductParams l_mutualFundTradedProductParams = TestDBUtility.getMutualFundTradedProductRow();
        l_mutualFundTradedProductParams.setMarketId(3303L);
        l_mutualFundTradedProductParams.setInstitutionCode("0D");
        l_mutualFundTradedProductParams.setProductId(0100000L);
        l_mutualFundTradedProductParams.setExecDateShiftdays(3);

        MarketParams l_marketParams = TestDBUtility.getMarketRow();
        l_marketParams.setMarketCode("0");

        TradedProductParams l_tradedProductParams = TestDBUtility.getTradedProductRow();
        l_tradedProductParams.setTradedProductId(1006160060005L);

        TradingTimeParams l_tradingTimeParams = TestDBUtility.getTradingTimeRow();
        l_tradingTimeParams.setTradingTimeType("01");
        l_tradingTimeParams.setProductCode("12");
        l_tradingTimeParams.setBizDateType("1");

        MutualFund2ndProductSonarParams l_mutualFundProductSonar2Params = new MutualFund2ndProductSonarParams();
        l_mutualFundProductSonar2Params.setInstitutionCode("0D");
        l_mutualFundProductSonar2Params.setProductCode("12");
        l_mutualFundProductSonar2Params.setFrgnBuyMinAmt(10);
        l_mutualFundProductSonar2Params.setFrgnBuyUnitAmt(9);

        try
        {
            TestDBUtility.insertWithDel(l_mutualFundProductParams);
            TestDBUtility.insertWithDel(l_productParams);
            TestDBUtility.insertWithDel(l_institutionParams);
            TestDBUtility.insertWithDel(l_mutualFundTradedProductParams);
            TestDBUtility.insertWithDel(l_marketParams);
            TestDBUtility.insertWithDel(l_tradedProductParams);
            TestDBUtility.insertWithDel(l_tradingTimeParams);
            TestDBUtility.insertWithDel(l_mutualFundProductSonar2Params);
        }
        catch (WEB3BaseException l_ex)
        {
            log.error("", l_ex);
            log.exiting(STR_METHOD_NAME);
            fail();
        }
        try
        {
        	InstitutionImpl l_institutionImpl = new InstitutionImpl(33);

        	WEB3MutualFundProductCondSpec l_productCondSpec = new WEB3MutualFundProductCondSpec();
        	l_productCondSpec.setMutualProductCode("12");
        	l_productCondSpec.setBuyEndDate(new Timestamp(WEB3DateUtility.getDate("20040709","yyyyMMdd").getTime()));
        	l_productCondSpec.setSellSwitchingEndDate(new Timestamp(WEB3DateUtility.getDate("20040709","yyyyMMdd").getTime()));
        	l_productCondSpec.setBuyFrgnMinAmtBuy("11");
        	l_productCondSpec.setBuyFrgnUnitAmtBuy(null);
        	l_productCondSpec.setBuyFrgnMinAmtAdd("9");

        	WEB3MutualFundProductManager l_mutualFundProductManager = new WEB3MutualFundProductManager();
            l_mutualFundProductManager.validateProductCond(
                l_institutionImpl,
                l_productCondSpec);
            fail();
        }
        catch (WEB3BusinessLayerException l_blex)
        {
            log.error("ERROR", l_blex);
            assertEquals(WEB3ErrorCatalog.BUSINESS_ERROR_02747, l_blex.getErrorInfo());
        }
        catch (Exception l_ex)
        {
            log.error("ERROR", l_ex);
            fail();
        }
        log.exiting(STR_METHOD_NAME);
    }

    public void testValidateProductCondCase6()
    {
        final String STR_METHOD_NAME = "testValidateProductCondCase6()";
        log.entering(STR_METHOD_NAME);

        MutualFundProductParams l_mutualFundProductParams = TestDBUtility.getMutualFundProductRow();
        l_mutualFundProductParams.setInstitutionCode("0D");
        l_mutualFundProductParams.setProductCode("12");
        l_mutualFundProductParams.setFundType(MutualFundTypeEnum.FOREIGN_MMF);
        l_mutualFundProductParams.setRedemptionDate(new Timestamp(WEB3DateUtility.getDate("20040715","yyyyMMdd").getTime()));

        ProductParams l_productParams = TestDBUtility.getProductRow();
        l_productParams.setProductId(0100000L);

        InstitutionParams l_institutionParams = TestDBUtility.getInstitutionRow();
        MutualFundTradedProductParams l_mutualFundTradedProductParams = TestDBUtility.getMutualFundTradedProductRow();
        l_mutualFundTradedProductParams.setMarketId(3303L);
        l_mutualFundTradedProductParams.setInstitutionCode("0D");
        l_mutualFundTradedProductParams.setProductId(0100000L);
        l_mutualFundTradedProductParams.setExecDateShiftdays(3);

        MarketParams l_marketParams = TestDBUtility.getMarketRow();
        l_marketParams.setMarketCode("0");

        TradedProductParams l_tradedProductParams = TestDBUtility.getTradedProductRow();
        l_tradedProductParams.setTradedProductId(1006160060005L);

        TradingTimeParams l_tradingTimeParams = TestDBUtility.getTradingTimeRow();
        l_tradingTimeParams.setTradingTimeType("01");
        l_tradingTimeParams.setProductCode("12");
        l_tradingTimeParams.setBizDateType("1");

        MutualFund2ndProductSonarParams l_mutualFundProductSonar2Params = new MutualFund2ndProductSonarParams();
        l_mutualFundProductSonar2Params.setInstitutionCode("0D");
        l_mutualFundProductSonar2Params.setProductCode("12");
        l_mutualFundProductSonar2Params.setFrgnBuyMinAmt(10);
        l_mutualFundProductSonar2Params.setFrgnBuyUnitAmt(9);

        try
        {
            TestDBUtility.insertWithDel(l_mutualFundProductParams);
            TestDBUtility.insertWithDel(l_productParams);
            TestDBUtility.insertWithDel(l_institutionParams);
            TestDBUtility.insertWithDel(l_mutualFundTradedProductParams);
            TestDBUtility.insertWithDel(l_marketParams);
            TestDBUtility.insertWithDel(l_tradedProductParams);
            TestDBUtility.insertWithDel(l_tradingTimeParams);
            TestDBUtility.insertWithDel(l_mutualFundProductSonar2Params);
        }
        catch (WEB3BaseException l_ex)
        {
            log.error("", l_ex);
            log.exiting(STR_METHOD_NAME);
            fail();
        }
        try
        {
        	InstitutionImpl l_institutionImpl = new InstitutionImpl(33);

        	WEB3MutualFundProductCondSpec l_productCondSpec = new WEB3MutualFundProductCondSpec();
        	l_productCondSpec.setMutualProductCode("12");
        	l_productCondSpec.setBuyEndDate(new Timestamp(WEB3DateUtility.getDate("20040709","yyyyMMdd").getTime()));
        	l_productCondSpec.setSellSwitchingEndDate(new Timestamp(WEB3DateUtility.getDate("20040709","yyyyMMdd").getTime()));
        	l_productCondSpec.setBuyFrgnMinAmtBuy("10");
        	l_productCondSpec.setBuyFrgnUnitAmtBuy("18");
        	l_productCondSpec.setBuyFrgnMinAmtAdd(null);
        	l_productCondSpec.setBuyFrgnUnitAmtAdd("19");

        	WEB3MutualFundProductManager l_mutualFundProductManager = new WEB3MutualFundProductManager();
            l_mutualFundProductManager.validateProductCond(
                l_institutionImpl,
                l_productCondSpec);
            fail();
        }
        catch (WEB3BusinessLayerException l_blex)
        {
            log.error("ERROR", l_blex);
            assertEquals(WEB3ErrorCatalog.BUSINESS_ERROR_02748, l_blex.getErrorInfo());
        }
        catch (Exception l_ex)
        {
            log.error("ERROR", l_ex);
            fail();
        }
        log.exiting(STR_METHOD_NAME);
    }

    public void testValidateProductCondCase7()
    {
        final String STR_METHOD_NAME = "testValidateProductCondCase7()";
        log.entering(STR_METHOD_NAME);

        MutualFundProductParams l_mutualFundProductParams = TestDBUtility.getMutualFundProductRow();
        l_mutualFundProductParams.setInstitutionCode("0D");
        l_mutualFundProductParams.setProductCode("12");
        l_mutualFundProductParams.setFundType(MutualFundTypeEnum.FOREIGN_MMF);
        l_mutualFundProductParams.setRedemptionDate(new Timestamp(WEB3DateUtility.getDate("20040715","yyyyMMdd").getTime()));

        ProductParams l_productParams = TestDBUtility.getProductRow();
        l_productParams.setProductId(0100000L);

        InstitutionParams l_institutionParams = TestDBUtility.getInstitutionRow();
        MutualFundTradedProductParams l_mutualFundTradedProductParams = TestDBUtility.getMutualFundTradedProductRow();
        l_mutualFundTradedProductParams.setMarketId(3303L);
        l_mutualFundTradedProductParams.setInstitutionCode("0D");
        l_mutualFundTradedProductParams.setProductId(0100000L);
        l_mutualFundTradedProductParams.setExecDateShiftdays(3);

        MarketParams l_marketParams = TestDBUtility.getMarketRow();
        l_marketParams.setMarketCode("0");

        TradedProductParams l_tradedProductParams = TestDBUtility.getTradedProductRow();
        l_tradedProductParams.setTradedProductId(1006160060005L);

        TradingTimeParams l_tradingTimeParams = TestDBUtility.getTradingTimeRow();
        l_tradingTimeParams.setTradingTimeType("01");
        l_tradingTimeParams.setProductCode("12");
        l_tradingTimeParams.setBizDateType("1");

        MutualFund2ndProductSonarParams l_mutualFundProductSonar2Params = new MutualFund2ndProductSonarParams();
        l_mutualFundProductSonar2Params.setInstitutionCode("0D");
        l_mutualFundProductSonar2Params.setProductCode("12");
        l_mutualFundProductSonar2Params.setFrgnBuyMinAmt(10);
        l_mutualFundProductSonar2Params.setFrgnBuyUnitAmt(9);
        l_mutualFundProductSonar2Params.setFrgnSellMinAmt(13);

        try
        {
            TestDBUtility.insertWithDel(l_mutualFundProductParams);
            TestDBUtility.insertWithDel(l_productParams);
            TestDBUtility.insertWithDel(l_institutionParams);
            TestDBUtility.insertWithDel(l_mutualFundTradedProductParams);
            TestDBUtility.insertWithDel(l_marketParams);
            TestDBUtility.insertWithDel(l_tradedProductParams);
            TestDBUtility.insertWithDel(l_tradingTimeParams);
            TestDBUtility.insertWithDel(l_mutualFundProductSonar2Params);
        }
        catch (WEB3BaseException l_ex)
        {
            log.error("", l_ex);
            log.exiting(STR_METHOD_NAME);
            fail();
        }
        try
        {
        	InstitutionImpl l_institutionImpl = new InstitutionImpl(33);

        	WEB3MutualFundProductCondSpec l_productCondSpec = new WEB3MutualFundProductCondSpec();
        	l_productCondSpec.setMutualProductCode("12");
        	l_productCondSpec.setBuyEndDate(new Timestamp(WEB3DateUtility.getDate("20040709","yyyyMMdd").getTime()));
        	l_productCondSpec.setSellSwitchingEndDate(new Timestamp(WEB3DateUtility.getDate("20040709","yyyyMMdd").getTime()));
        	l_productCondSpec.setBuyFrgnMinAmtBuy("10");
        	l_productCondSpec.setBuyFrgnUnitAmtBuy("18");
        	l_productCondSpec.setBuyFrgnMinAmtAdd("11");
        	l_productCondSpec.setBuyFrgnUnitAmtAdd(null);
        	l_productCondSpec.setSellFrgnMinAmtSell("12");

        	WEB3MutualFundProductManager l_mutualFundProductManager = new WEB3MutualFundProductManager();
            l_mutualFundProductManager.validateProductCond(
                l_institutionImpl,
                l_productCondSpec);
            fail();
        }
        catch (WEB3BusinessLayerException l_blex)
        {
            log.error("ERROR", l_blex);
            assertEquals(WEB3ErrorCatalog.BUSINESS_ERROR_02749, l_blex.getErrorInfo());
        }
        catch (Exception l_ex)
        {
            log.error("ERROR", l_ex);
            fail();
        }
        log.exiting(STR_METHOD_NAME);
    }

    public void testValidateProductCondCase8()
    {
        final String STR_METHOD_NAME = "testValidateProductCondCase8()";
        log.entering(STR_METHOD_NAME);

        MutualFundProductParams l_mutualFundProductParams = TestDBUtility.getMutualFundProductRow();
        l_mutualFundProductParams.setInstitutionCode("0D");
        l_mutualFundProductParams.setProductCode("12");
        l_mutualFundProductParams.setFundType(MutualFundTypeEnum.FOREIGN_MMF);
        l_mutualFundProductParams.setRedemptionDate(new Timestamp(WEB3DateUtility.getDate("20040715","yyyyMMdd").getTime()));

        ProductParams l_productParams = TestDBUtility.getProductRow();
        l_productParams.setProductId(0100000L);

        InstitutionParams l_institutionParams = TestDBUtility.getInstitutionRow();
        MutualFundTradedProductParams l_mutualFundTradedProductParams = TestDBUtility.getMutualFundTradedProductRow();
        l_mutualFundTradedProductParams.setMarketId(3303L);
        l_mutualFundTradedProductParams.setInstitutionCode("0D");
        l_mutualFundTradedProductParams.setProductId(0100000L);
        l_mutualFundTradedProductParams.setExecDateShiftdays(3);

        MarketParams l_marketParams = TestDBUtility.getMarketRow();
        l_marketParams.setMarketCode("0");

        TradedProductParams l_tradedProductParams = TestDBUtility.getTradedProductRow();
        l_tradedProductParams.setTradedProductId(1006160060005L);

        TradingTimeParams l_tradingTimeParams = TestDBUtility.getTradingTimeRow();
        l_tradingTimeParams.setTradingTimeType("01");
        l_tradingTimeParams.setProductCode("12");
        l_tradingTimeParams.setBizDateType("1");

        MutualFund2ndProductSonarParams l_mutualFundProductSonar2Params = new MutualFund2ndProductSonarParams();
        l_mutualFundProductSonar2Params.setInstitutionCode("0D");
        l_mutualFundProductSonar2Params.setProductCode("12");
        l_mutualFundProductSonar2Params.setFrgnBuyMinAmt(10);
        l_mutualFundProductSonar2Params.setFrgnBuyUnitAmt(9);
        l_mutualFundProductSonar2Params.setFrgnSellMinAmt(13);
        l_mutualFundProductSonar2Params.setFrgnSellUnitAmt(15);

        try
        {
            TestDBUtility.insertWithDel(l_mutualFundProductParams);
            TestDBUtility.insertWithDel(l_productParams);
            TestDBUtility.insertWithDel(l_institutionParams);
            TestDBUtility.insertWithDel(l_mutualFundTradedProductParams);
            TestDBUtility.insertWithDel(l_marketParams);
            TestDBUtility.insertWithDel(l_tradedProductParams);
            TestDBUtility.insertWithDel(l_tradingTimeParams);
            TestDBUtility.insertWithDel(l_mutualFundProductSonar2Params);
        }
        catch (WEB3BaseException l_ex)
        {
            log.error("", l_ex);
            log.exiting(STR_METHOD_NAME);
            fail();
        }
        try
        {
        	InstitutionImpl l_institutionImpl = new InstitutionImpl(33);

        	WEB3MutualFundProductCondSpec l_productCondSpec = new WEB3MutualFundProductCondSpec();
        	l_productCondSpec.setMutualProductCode("12");
        	l_productCondSpec.setBuyEndDate(new Timestamp(WEB3DateUtility.getDate("20040709","yyyyMMdd").getTime()));
        	l_productCondSpec.setSellSwitchingEndDate(new Timestamp(WEB3DateUtility.getDate("20040709","yyyyMMdd").getTime()));
        	l_productCondSpec.setBuyFrgnMinAmtBuy("10");
        	l_productCondSpec.setBuyFrgnUnitAmtBuy("18");
        	l_productCondSpec.setBuyFrgnMinAmtAdd("10");
        	l_productCondSpec.setBuyFrgnUnitAmtAdd("36");
        	l_productCondSpec.setSellFrgnMinAmtSell(null);
        	l_productCondSpec.setSellFrgnUnitAmtSell("16");

        	WEB3MutualFundProductManager l_mutualFundProductManager = new WEB3MutualFundProductManager();
            l_mutualFundProductManager.validateProductCond(
                l_institutionImpl,
                l_productCondSpec);
            fail();
        }
        catch (WEB3BusinessLayerException l_blex)
        {
            log.error("ERROR", l_blex);
            assertEquals(WEB3ErrorCatalog.BUSINESS_ERROR_02750, l_blex.getErrorInfo());
        }
        catch (Exception l_ex)
        {
            log.error("ERROR", l_ex);
            fail();
        }
        log.exiting(STR_METHOD_NAME);
    }

    public void testValidateProductCondCase9()
    {
        final String STR_METHOD_NAME = "testValidateProductCondCase9()";
        log.entering(STR_METHOD_NAME);

        MutualFundProductParams l_mutualFundProductParams = TestDBUtility.getMutualFundProductRow();
        l_mutualFundProductParams.setInstitutionCode("0D");
        l_mutualFundProductParams.setProductCode("12");
        l_mutualFundProductParams.setFundType(MutualFundTypeEnum.FOREIGN_MMF);
        l_mutualFundProductParams.setRedemptionDate(new Timestamp(WEB3DateUtility.getDate("20040715","yyyyMMdd").getTime()));

        ProductParams l_productParams = TestDBUtility.getProductRow();
        l_productParams.setProductId(0100000L);

        InstitutionParams l_institutionParams = TestDBUtility.getInstitutionRow();
        MutualFundTradedProductParams l_mutualFundTradedProductParams = TestDBUtility.getMutualFundTradedProductRow();
        l_mutualFundTradedProductParams.setMarketId(3303L);
        l_mutualFundTradedProductParams.setInstitutionCode("0D");
        l_mutualFundTradedProductParams.setProductId(0100000L);
        l_mutualFundTradedProductParams.setExecDateShiftdays(3);

        MarketParams l_marketParams = TestDBUtility.getMarketRow();
        l_marketParams.setMarketCode("0");

        TradedProductParams l_tradedProductParams = TestDBUtility.getTradedProductRow();
        l_tradedProductParams.setTradedProductId(1006160060005L);

        TradingTimeParams l_tradingTimeParams = TestDBUtility.getTradingTimeRow();
        l_tradingTimeParams.setTradingTimeType("01");
        l_tradingTimeParams.setProductCode("12");
        l_tradingTimeParams.setBizDateType("1");

        MutualFund2ndProductSonarParams l_mutualFundProductSonar2Params = new MutualFund2ndProductSonarParams();
        l_mutualFundProductSonar2Params.setInstitutionCode("0D");
        l_mutualFundProductSonar2Params.setProductCode("12");
        l_mutualFundProductSonar2Params.setFrgnBuyMinAmt(10);
        l_mutualFundProductSonar2Params.setFrgnBuyUnitAmt(9);
        l_mutualFundProductSonar2Params.setFrgnSellMinAmt(13);
        l_mutualFundProductSonar2Params.setFrgnSellUnitAmt(15);

        try
        {
            TestDBUtility.insertWithDel(l_mutualFundProductParams);
            TestDBUtility.insertWithDel(l_productParams);
            TestDBUtility.insertWithDel(l_institutionParams);
            TestDBUtility.insertWithDel(l_mutualFundTradedProductParams);
            TestDBUtility.insertWithDel(l_marketParams);
            TestDBUtility.insertWithDel(l_tradedProductParams);
            TestDBUtility.insertWithDel(l_tradingTimeParams);
            TestDBUtility.insertWithDel(l_mutualFundProductSonar2Params);
        }
        catch (WEB3BaseException l_ex)
        {
            log.error("", l_ex);
            log.exiting(STR_METHOD_NAME);
            fail();
        }
        try
        {
        	InstitutionImpl l_institutionImpl = new InstitutionImpl(33);

        	WEB3MutualFundProductCondSpec l_productCondSpec = new WEB3MutualFundProductCondSpec();
        	l_productCondSpec.setMutualProductCode("12");
        	l_productCondSpec.setBuyEndDate(new Timestamp(WEB3DateUtility.getDate("20040709","yyyyMMdd").getTime()));
        	l_productCondSpec.setSellSwitchingEndDate(new Timestamp(WEB3DateUtility.getDate("20040709","yyyyMMdd").getTime()));
        	l_productCondSpec.setBuyFrgnMinAmtBuy("10");
        	l_productCondSpec.setBuyFrgnUnitAmtBuy("18");
        	l_productCondSpec.setBuyFrgnMinAmtAdd("10");
        	l_productCondSpec.setBuyFrgnUnitAmtAdd("36");
        	l_productCondSpec.setSellFrgnMinAmtSell("14");
        	l_productCondSpec.setSellFrgnUnitAmtSell(null);

        	WEB3MutualFundProductManager l_mutualFundProductManager = new WEB3MutualFundProductManager();
            l_mutualFundProductManager.validateProductCond(
                l_institutionImpl,
                l_productCondSpec);
        }
        catch (Exception l_ex)
        {
            log.error("ERROR", l_ex);
            fail();
        }
        log.exiting(STR_METHOD_NAME);
    }

    public void testValidateProductCondCase10()
    {
        final String STR_METHOD_NAME = "testValidateProductCondCase10()";
        log.entering(STR_METHOD_NAME);

        MutualFundProductParams l_mutualFundProductParams = TestDBUtility.getMutualFundProductRow();
        l_mutualFundProductParams.setInstitutionCode("0D");
        l_mutualFundProductParams.setProductCode("12");
        l_mutualFundProductParams.setFundType(MutualFundTypeEnum.FOREIGN_MMF);
        l_mutualFundProductParams.setRedemptionDate(new Timestamp(WEB3DateUtility.getDate("20040715","yyyyMMdd").getTime()));

        ProductParams l_productParams = TestDBUtility.getProductRow();
        l_productParams.setProductId(0100000L);

        InstitutionParams l_institutionParams = TestDBUtility.getInstitutionRow();
        MutualFundTradedProductParams l_mutualFundTradedProductParams = TestDBUtility.getMutualFundTradedProductRow();
        l_mutualFundTradedProductParams.setMarketId(3303L);
        l_mutualFundTradedProductParams.setInstitutionCode("0D");
        l_mutualFundTradedProductParams.setProductId(0100000L);
        l_mutualFundTradedProductParams.setExecDateShiftdays(3);

        MarketParams l_marketParams = TestDBUtility.getMarketRow();
        l_marketParams.setMarketCode("0");

        TradedProductParams l_tradedProductParams = TestDBUtility.getTradedProductRow();
        l_tradedProductParams.setTradedProductId(1006160060005L);

        TradingTimeParams l_tradingTimeParams = TestDBUtility.getTradingTimeRow();
        l_tradingTimeParams.setTradingTimeType("01");
        l_tradingTimeParams.setProductCode("12");
        l_tradingTimeParams.setBizDateType("1");

        MutualFund2ndProductSonarParams l_mutualFundProductSonar2Params = new MutualFund2ndProductSonarParams();
        l_mutualFundProductSonar2Params.setInstitutionCode("0D");
        l_mutualFundProductSonar2Params.setProductCode("12");
        l_mutualFundProductSonar2Params.setFrgnBuyMinAmt(10);
        l_mutualFundProductSonar2Params.setFrgnBuyUnitAmt(9);
        l_mutualFundProductSonar2Params.setFrgnSellMinAmt(13);
        l_mutualFundProductSonar2Params.setFrgnSellUnitAmt(15);

        try
        {
            TestDBUtility.insertWithDel(l_mutualFundProductParams);
            TestDBUtility.insertWithDel(l_productParams);
            TestDBUtility.insertWithDel(l_institutionParams);
            TestDBUtility.insertWithDel(l_mutualFundTradedProductParams);
            TestDBUtility.insertWithDel(l_marketParams);
            TestDBUtility.insertWithDel(l_tradedProductParams);
            TestDBUtility.insertWithDel(l_tradingTimeParams);
            TestDBUtility.insertWithDel(l_mutualFundProductSonar2Params);
        }
        catch (WEB3BaseException l_ex)
        {
            log.error("", l_ex);
            log.exiting(STR_METHOD_NAME);
            fail();
        }
        try
        {
        	InstitutionImpl l_institutionImpl = new InstitutionImpl(33);

        	WEB3MutualFundProductCondSpec l_productCondSpec = new WEB3MutualFundProductCondSpec();
        	l_productCondSpec.setMutualProductCode("12");
        	l_productCondSpec.setBuyEndDate(new Timestamp(WEB3DateUtility.getDate("20040709","yyyyMMdd").getTime()));
        	l_productCondSpec.setSellSwitchingEndDate(new Timestamp(WEB3DateUtility.getDate("20040709","yyyyMMdd").getTime()));
        	l_productCondSpec.setBuyFrgnMinAmtBuy("10");
        	l_productCondSpec.setBuyFrgnUnitAmtBuy("18");
        	l_productCondSpec.setBuyFrgnMinAmtAdd("10");
        	l_productCondSpec.setBuyFrgnUnitAmtAdd("36");
        	l_productCondSpec.setSellFrgnMinAmtSell("13");
        	l_productCondSpec.setSellFrgnUnitAmtSell("15");

        	WEB3MutualFundProductManager l_mutualFundProductManager = new WEB3MutualFundProductManager();
            l_mutualFundProductManager.validateProductCond(
                l_institutionImpl,
                l_productCondSpec);
        }
        catch (Exception l_ex)
        {
            log.error("ERROR", l_ex);
            fail();
        }
        log.exiting(STR_METHOD_NAME);
    }
    
    public void testValidateProductCondCase11()
    {
        final String STR_METHOD_NAME = "testValidateProductCondCase11()";
        log.entering(STR_METHOD_NAME);
        MutualFundProductParams l_mutualFundProductParams = TestDBUtility.getMutualFundProductRow();
        l_mutualFundProductParams.setInstitutionCode("0D");
        l_mutualFundProductParams.setProductCode("12");
        l_mutualFundProductParams.setFundType(MutualFundTypeEnum.FOREIGN_MMF);
        l_mutualFundProductParams.setRedemptionDate(new Timestamp(WEB3DateUtility.getDate("20040715","yyyyMMdd").getTime()));
        l_mutualFundProductParams.setSellBanDate(WEB3DateUtility.getDate("20040715","yyyyMMdd"));
        
        ProductParams l_productParams = TestDBUtility.getProductRow();
        l_productParams.setProductId(0100000L);

        InstitutionParams l_institutionParams = TestDBUtility.getInstitutionRow();
        MutualFundTradedProductParams l_mutualFundTradedProductParams = TestDBUtility.getMutualFundTradedProductRow();
        l_mutualFundTradedProductParams.setMarketId(3303L);
        l_mutualFundTradedProductParams.setInstitutionCode("0D");
        l_mutualFundTradedProductParams.setProductId(0100000L);
        l_mutualFundTradedProductParams.setExecDateShiftdays(3);

        MarketParams l_marketParams = TestDBUtility.getMarketRow();
        l_marketParams.setMarketCode("0");

        TradedProductParams l_tradedProductParams = TestDBUtility.getTradedProductRow();
        l_tradedProductParams.setTradedProductId(1006160060005L);

        TradingTimeParams l_tradingTimeParams = TestDBUtility.getTradingTimeRow();
        l_tradingTimeParams.setTradingTimeType("01");
        l_tradingTimeParams.setProductCode("12");
        l_tradingTimeParams.setBizDateType("1");

        MutualFund2ndProductSonarParams l_mutualFundProductSonar2Params = new MutualFund2ndProductSonarParams();
        l_mutualFundProductSonar2Params.setInstitutionCode("0D");
        l_mutualFundProductSonar2Params.setProductCode("12");
        l_mutualFundProductSonar2Params.setFrgnBuyMinAmt(10);
        l_mutualFundProductSonar2Params.setFrgnBuyUnitAmt(9);
        l_mutualFundProductSonar2Params.setFrgnSellMinAmt(13);
        l_mutualFundProductSonar2Params.setFrgnSellUnitAmt(15);

        try
        {
            TestDBUtility.insertWithDel(l_mutualFundProductParams);
            TestDBUtility.insertWithDel(l_productParams);
            TestDBUtility.insertWithDel(l_institutionParams);
            TestDBUtility.insertWithDel(l_mutualFundTradedProductParams);
            TestDBUtility.insertWithDel(l_marketParams);
            TestDBUtility.insertWithDel(l_tradedProductParams);
            TestDBUtility.insertWithDel(l_tradingTimeParams);
            TestDBUtility.insertWithDel(l_mutualFundProductSonar2Params);
        }
        catch (WEB3BaseException l_ex)
        {
            log.error("", l_ex);
            log.exiting(STR_METHOD_NAME);
            fail();
        }
        try
        {
            InstitutionImpl l_institutionImpl = new InstitutionImpl(33);

            WEB3MutualFundProductCondSpec l_productCondSpec = new WEB3MutualFundProductCondSpec();
            l_productCondSpec.setMutualProductCode("12");
            l_productCondSpec.setBuyEndDate(new Timestamp(WEB3DateUtility.getDate("20040709","yyyyMMdd").getTime()));
            l_productCondSpec.setSellSwitchingEndDate(new Timestamp(WEB3DateUtility.getDate("20040709","yyyyMMdd").getTime()));
            l_productCondSpec.setBuyFrgnMinAmtBuy("10");
            l_productCondSpec.setBuyFrgnUnitAmtBuy("18");
            l_productCondSpec.setBuyFrgnMinAmtAdd("10");
            l_productCondSpec.setBuyFrgnUnitAmtAdd("36");
            l_productCondSpec.setSellFrgnMinAmtSell("13");
            l_productCondSpec.setSellFrgnUnitAmtSell("15");
            l_productCondSpec.setSellSwitchingStartDate(WEB3DateUtility.getDate("20040709","yyyyMMdd"));

            WEB3MutualFundProductManager l_mutualFundProductManager = new WEB3MutualFundProductManager();
            l_mutualFundProductManager.validateProductCond(
                l_institutionImpl,
                l_productCondSpec);
            fail();
        }
        catch (WEB3BaseException l_ex)
        {
            log.error("", l_ex);
            assertEquals(WEB3ErrorCatalog.BUSINESS_ERROR_00337, l_ex.getErrorInfo());
        }
        catch (Exception l_ex)
        {
            log.error("ERROR", l_ex);
            fail();
        }
        log.exiting(STR_METHOD_NAME);
    }
    
    public void testValidateProductCondCase12()
    {
        final String STR_METHOD_NAME = "testValidateProductCondCase12()";
        log.entering(STR_METHOD_NAME);
        MutualFundProductParams l_mutualFundProductParams = TestDBUtility.getMutualFundProductRow();
        l_mutualFundProductParams.setInstitutionCode("0D");
        l_mutualFundProductParams.setProductCode("12");
        l_mutualFundProductParams.setFundType(MutualFundTypeEnum.FOREIGN_MMF);
        l_mutualFundProductParams.setRedemptionDate(new Timestamp(WEB3DateUtility.getDate("20040715","yyyyMMdd").getTime()));
        l_mutualFundProductParams.setSellBanDate(WEB3DateUtility.getDate("20040715","yyyyMMdd"));
        
        ProductParams l_productParams = TestDBUtility.getProductRow();
        l_productParams.setProductId(0100000L);

        InstitutionParams l_institutionParams = TestDBUtility.getInstitutionRow();
        MutualFundTradedProductParams l_mutualFundTradedProductParams = TestDBUtility.getMutualFundTradedProductRow();
        l_mutualFundTradedProductParams.setMarketId(3303L);
        l_mutualFundTradedProductParams.setInstitutionCode("0D");
        l_mutualFundTradedProductParams.setProductId(0100000L);
        l_mutualFundTradedProductParams.setExecDateShiftdays(3);

        MarketParams l_marketParams = TestDBUtility.getMarketRow();
        l_marketParams.setMarketCode("0");

        TradedProductParams l_tradedProductParams = TestDBUtility.getTradedProductRow();
        l_tradedProductParams.setTradedProductId(1006160060005L);

        TradingTimeParams l_tradingTimeParams = TestDBUtility.getTradingTimeRow();
        l_tradingTimeParams.setTradingTimeType("01");
        l_tradingTimeParams.setProductCode("12");
        l_tradingTimeParams.setBizDateType("1");

        MutualFund2ndProductSonarParams l_mutualFundProductSonar2Params = new MutualFund2ndProductSonarParams();
        l_mutualFundProductSonar2Params.setInstitutionCode("0D");
        l_mutualFundProductSonar2Params.setProductCode("12");
        l_mutualFundProductSonar2Params.setFrgnBuyMinAmt(10);
        l_mutualFundProductSonar2Params.setFrgnBuyUnitAmt(9);
        l_mutualFundProductSonar2Params.setFrgnSellMinAmt(13);
        l_mutualFundProductSonar2Params.setFrgnSellUnitAmt(15);

        try
        {
            TestDBUtility.insertWithDel(l_mutualFundProductParams);
            TestDBUtility.insertWithDel(l_productParams);
            TestDBUtility.insertWithDel(l_institutionParams);
            TestDBUtility.insertWithDel(l_mutualFundTradedProductParams);
            TestDBUtility.insertWithDel(l_marketParams);
            TestDBUtility.insertWithDel(l_tradedProductParams);
            TestDBUtility.insertWithDel(l_tradingTimeParams);
            TestDBUtility.insertWithDel(l_mutualFundProductSonar2Params);
        }
        catch (WEB3BaseException l_ex)
        {
            log.error("", l_ex);
            log.exiting(STR_METHOD_NAME);
            fail();
        }
        try
        {
            InstitutionImpl l_institutionImpl = new InstitutionImpl(33);

            WEB3MutualFundProductCondSpec l_productCondSpec = new WEB3MutualFundProductCondSpec();
            l_productCondSpec.setMutualProductCode("12");
            l_productCondSpec.setBuyEndDate(new Timestamp(WEB3DateUtility.getDate("20040709","yyyyMMdd").getTime()));
            l_productCondSpec.setSellSwitchingEndDate(new Timestamp(WEB3DateUtility.getDate("20040709","yyyyMMdd").getTime()));
            l_productCondSpec.setBuyFrgnMinAmtBuy("10");
            l_productCondSpec.setBuyFrgnUnitAmtBuy("18");
            l_productCondSpec.setBuyFrgnMinAmtAdd("10");
            l_productCondSpec.setBuyFrgnUnitAmtAdd("36");
            l_productCondSpec.setSellFrgnMinAmtSell("13");
            l_productCondSpec.setSellFrgnUnitAmtSell("15");
            l_productCondSpec.setSellSwitchingStartDate(WEB3DateUtility.getDate("20040715","yyyyMMdd"));

            WEB3MutualFundProductManager l_mutualFundProductManager = new WEB3MutualFundProductManager();
            l_mutualFundProductManager.validateProductCond(
                l_institutionImpl,
                l_productCondSpec);
        }
        catch (Exception l_ex)
        {
            log.error("ERROR", l_ex);
            fail();
        }
        log.exiting(STR_METHOD_NAME);
    }

    public void testValidateProductCondCase13()
    {
        final String STR_METHOD_NAME = "testValidateProductCondCase13()";
        log.entering(STR_METHOD_NAME);
        MutualFundProductParams l_mutualFundProductParams =
            TestDBUtility.getMutualFundProductRow();
        l_mutualFundProductParams.setInstitutionCode("0D");
        l_mutualFundProductParams.setProductCode("12");
        l_mutualFundProductParams.setFundType(MutualFundTypeEnum.FOREIGN_MMF);
        l_mutualFundProductParams.setRedemptionDate(
            new Timestamp(WEB3DateUtility.getDate("20040715","yyyyMMdd").getTime()));
        l_mutualFundProductParams.setSellBanDate(
            WEB3DateUtility.getDate("20040715","yyyyMMdd"));
        
        ProductParams l_productParams = TestDBUtility.getProductRow();
        l_productParams.setProductId(0100000L);

        InstitutionParams l_institutionParams = TestDBUtility.getInstitutionRow();
        MutualFundTradedProductParams l_mutualFundTradedProductParams =
            TestDBUtility.getMutualFundTradedProductRow();
        l_mutualFundTradedProductParams.setMarketId(3303L);
        l_mutualFundTradedProductParams.setInstitutionCode("0D");
        l_mutualFundTradedProductParams.setProductId(0100000L);
        l_mutualFundTradedProductParams.setExecDateShiftdays(3);

        MarketParams l_marketParams = TestDBUtility.getMarketRow();
        l_marketParams.setMarketCode("0");

        TradedProductParams l_tradedProductParams = TestDBUtility.getTradedProductRow();
        l_tradedProductParams.setTradedProductId(1006160060005L);

        TradingTimeParams l_tradingTimeParams = TestDBUtility.getTradingTimeRow();
        l_tradingTimeParams.setTradingTimeType("01");
        l_tradingTimeParams.setProductCode("12");
        l_tradingTimeParams.setBizDateType("1");

        MutualFund2ndProductSonarParams l_mutualFundProductSonar2Params = new MutualFund2ndProductSonarParams();
        l_mutualFundProductSonar2Params.setInstitutionCode("0D");
        l_mutualFundProductSonar2Params.setProductCode("12");
        l_mutualFundProductSonar2Params.setFrgnBuyMinAmt(10);
        l_mutualFundProductSonar2Params.setFrgnBuyUnitAmt(9);
        l_mutualFundProductSonar2Params.setFrgnSellMinAmt(13);
        l_mutualFundProductSonar2Params.setFrgnSellUnitAmt(15);
        l_mutualFundProductSonar2Params.setBuyUnitQty(0);

        try
        {
            TestDBUtility.insertWithDel(l_mutualFundProductParams);
            TestDBUtility.insertWithDel(l_productParams);
            TestDBUtility.insertWithDel(l_institutionParams);
            TestDBUtility.insertWithDel(l_mutualFundTradedProductParams);
            TestDBUtility.insertWithDel(l_marketParams);
            TestDBUtility.insertWithDel(l_tradedProductParams);
            TestDBUtility.insertWithDel(l_tradingTimeParams);
            TestDBUtility.insertWithDel(l_mutualFundProductSonar2Params);
        }
        catch (WEB3BaseException l_ex)
        {
            log.error("", l_ex);
            log.exiting(STR_METHOD_NAME);
            fail();
        }
        try
        {
            InstitutionImpl l_institutionImpl = new InstitutionImpl(33);

            WEB3MutualFundProductCondSpec l_productCondSpec = new WEB3MutualFundProductCondSpec();
            l_productCondSpec.setMutualProductCode("12");
            l_productCondSpec.setBuyEndDate(new Timestamp(WEB3DateUtility.getDate("20040709","yyyyMMdd").getTime()));
            l_productCondSpec.setSellSwitchingEndDate(new Timestamp(WEB3DateUtility.getDate("20040709","yyyyMMdd").getTime()));
            l_productCondSpec.setBuyFrgnMinAmtBuy("10");
            l_productCondSpec.setBuyFrgnUnitAmtBuy("18");
            l_productCondSpec.setBuyFrgnMinAmtAdd("10");
            l_productCondSpec.setBuyFrgnUnitAmtAdd("36");
            l_productCondSpec.setSellFrgnMinAmtSell("13");
            l_productCondSpec.setSellFrgnUnitAmtSell("15");
            l_productCondSpec.setNewBuyUnitQty("111");
            l_productCondSpec.setSellSwitchingStartDate(WEB3DateUtility.getDate("20040715","yyyyMMdd"));

            WEB3MutualFundProductManager l_mutualFundProductManager = new WEB3MutualFundProductManager();
            l_mutualFundProductManager.validateProductCond(
                l_institutionImpl,
                l_productCondSpec);
            fail();
        }
        catch (WEB3BaseException l_ex)
        {
            log.error("", l_ex);
            assertEquals(WEB3ErrorCatalog.BUSINESS_ERROR_00342, l_ex.getErrorInfo());
        }
        catch (Exception l_ex)
        {
            log.error("ERROR", l_ex);
            fail();
        }
        log.exiting(STR_METHOD_NAME);
    }

    public void testValidateProductCondCase14()
    {
        final String STR_METHOD_NAME = "testValidateProductCondCase14()";
        log.entering(STR_METHOD_NAME);
        MutualFundProductParams l_mutualFundProductParams =
            TestDBUtility.getMutualFundProductRow();
        l_mutualFundProductParams.setInstitutionCode("0D");
        l_mutualFundProductParams.setProductCode("12");
        l_mutualFundProductParams.setFundType(MutualFundTypeEnum.FOREIGN_MMF);
        l_mutualFundProductParams.setRedemptionDate(
            new Timestamp(WEB3DateUtility.getDate("20040715","yyyyMMdd").getTime()));
        l_mutualFundProductParams.setSellBanDate(
            WEB3DateUtility.getDate("20040715","yyyyMMdd"));
        
        ProductParams l_productParams = TestDBUtility.getProductRow();
        l_productParams.setProductId(0100000L);

        InstitutionParams l_institutionParams = TestDBUtility.getInstitutionRow();
        MutualFundTradedProductParams l_mutualFundTradedProductParams =
            TestDBUtility.getMutualFundTradedProductRow();
        l_mutualFundTradedProductParams.setMarketId(3303L);
        l_mutualFundTradedProductParams.setInstitutionCode("0D");
        l_mutualFundTradedProductParams.setProductId(0100000L);
        l_mutualFundTradedProductParams.setExecDateShiftdays(3);

        MarketParams l_marketParams = TestDBUtility.getMarketRow();
        l_marketParams.setMarketCode("0");

        TradedProductParams l_tradedProductParams = TestDBUtility.getTradedProductRow();
        l_tradedProductParams.setTradedProductId(1006160060005L);

        TradingTimeParams l_tradingTimeParams = TestDBUtility.getTradingTimeRow();
        l_tradingTimeParams.setTradingTimeType("01");
        l_tradingTimeParams.setProductCode("12");
        l_tradingTimeParams.setBizDateType("1");

        MutualFund2ndProductSonarParams l_mutualFundProductSonar2Params = new MutualFund2ndProductSonarParams();
        l_mutualFundProductSonar2Params.setInstitutionCode("0D");
        l_mutualFundProductSonar2Params.setProductCode("12");
        l_mutualFundProductSonar2Params.setFrgnBuyMinAmt(10);
        l_mutualFundProductSonar2Params.setFrgnBuyUnitAmt(9);
        l_mutualFundProductSonar2Params.setFrgnSellMinAmt(13);
        l_mutualFundProductSonar2Params.setFrgnSellUnitAmt(15);
        l_mutualFundProductSonar2Params.setBuyUnitAmt(0);
        try
        {
            TestDBUtility.insertWithDel(l_mutualFundProductParams);
            TestDBUtility.insertWithDel(l_productParams);
            TestDBUtility.insertWithDel(l_institutionParams);
            TestDBUtility.insertWithDel(l_mutualFundTradedProductParams);
            TestDBUtility.insertWithDel(l_marketParams);
            TestDBUtility.insertWithDel(l_tradedProductParams);
            TestDBUtility.insertWithDel(l_tradingTimeParams);
            TestDBUtility.insertWithDel(l_mutualFundProductSonar2Params);
        }
        catch (WEB3BaseException l_ex)
        {
            log.error("", l_ex);
            log.exiting(STR_METHOD_NAME);
            fail();
        }
        try
        {
            InstitutionImpl l_institutionImpl = new InstitutionImpl(33);

            WEB3MutualFundProductCondSpec l_productCondSpec = new WEB3MutualFundProductCondSpec();
            l_productCondSpec.setMutualProductCode("12");
            l_productCondSpec.setBuyEndDate(new Timestamp(WEB3DateUtility.getDate("20040709","yyyyMMdd").getTime()));
            l_productCondSpec.setSellSwitchingEndDate(new Timestamp(WEB3DateUtility.getDate("20040709","yyyyMMdd").getTime()));
            l_productCondSpec.setBuyFrgnMinAmtBuy("10");
            l_productCondSpec.setBuyFrgnUnitAmtBuy("18");
            l_productCondSpec.setBuyFrgnMinAmtAdd("10");
            l_productCondSpec.setBuyFrgnUnitAmtAdd("36");
            l_productCondSpec.setSellFrgnMinAmtSell("13");
            l_productCondSpec.setSellFrgnUnitAmtSell("15");
            l_productCondSpec.setNewBuyUnitAmt("111");
            l_productCondSpec.setSellSwitchingStartDate(WEB3DateUtility.getDate("20040715","yyyyMMdd"));

            WEB3MutualFundProductManager l_mutualFundProductManager = new WEB3MutualFundProductManager();
            l_mutualFundProductManager.validateProductCond(
                l_institutionImpl,
                l_productCondSpec);
            fail();
        }
        catch (WEB3BaseException l_ex)
        {
            log.error("", l_ex);
            assertEquals(WEB3ErrorCatalog.BUSINESS_ERROR_00344, l_ex.getErrorInfo());
        }
        catch (Exception l_ex)
        {
            log.error("ERROR", l_ex);
            fail();
        }
        log.exiting(STR_METHOD_NAME);
    }

    public void testValidateProductCondCase15()
    {
        final String STR_METHOD_NAME = "testValidateProductCondCase15()";
        log.entering(STR_METHOD_NAME);
        MutualFundProductParams l_mutualFundProductParams =
            TestDBUtility.getMutualFundProductRow();
        l_mutualFundProductParams.setInstitutionCode("0D");
        l_mutualFundProductParams.setProductCode("12");
        l_mutualFundProductParams.setFundType(MutualFundTypeEnum.FOREIGN_MMF);
        l_mutualFundProductParams.setRedemptionDate(
            new Timestamp(WEB3DateUtility.getDate("20040715","yyyyMMdd").getTime()));
        l_mutualFundProductParams.setSellBanDate(
            WEB3DateUtility.getDate("20040715","yyyyMMdd"));
        
        ProductParams l_productParams = TestDBUtility.getProductRow();
        l_productParams.setProductId(0100000L);

        InstitutionParams l_institutionParams = TestDBUtility.getInstitutionRow();
        MutualFundTradedProductParams l_mutualFundTradedProductParams =
            TestDBUtility.getMutualFundTradedProductRow();
        l_mutualFundTradedProductParams.setMarketId(3303L);
        l_mutualFundTradedProductParams.setInstitutionCode("0D");
        l_mutualFundTradedProductParams.setProductId(0100000L);
        l_mutualFundTradedProductParams.setExecDateShiftdays(3);

        MarketParams l_marketParams = TestDBUtility.getMarketRow();
        l_marketParams.setMarketCode("0");

        TradedProductParams l_tradedProductParams = TestDBUtility.getTradedProductRow();
        l_tradedProductParams.setTradedProductId(1006160060005L);

        TradingTimeParams l_tradingTimeParams = TestDBUtility.getTradingTimeRow();
        l_tradingTimeParams.setTradingTimeType("01");
        l_tradingTimeParams.setProductCode("12");
        l_tradingTimeParams.setBizDateType("1");

        MutualFund2ndProductSonarParams l_mutualFundProductSonar2Params = new MutualFund2ndProductSonarParams();
        l_mutualFundProductSonar2Params.setInstitutionCode("0D");
        l_mutualFundProductSonar2Params.setProductCode("12");
        l_mutualFundProductSonar2Params.setFrgnBuyMinAmt(10);
        l_mutualFundProductSonar2Params.setFrgnBuyUnitAmt(9);
        l_mutualFundProductSonar2Params.setFrgnSellMinAmt(13);
        l_mutualFundProductSonar2Params.setFrgnSellUnitAmt(15);
        l_mutualFundProductSonar2Params.setBuyUnitQty(0);
        try
        {
            TestDBUtility.insertWithDel(l_mutualFundProductParams);
            TestDBUtility.insertWithDel(l_productParams);
            TestDBUtility.insertWithDel(l_institutionParams);
            TestDBUtility.insertWithDel(l_mutualFundTradedProductParams);
            TestDBUtility.insertWithDel(l_marketParams);
            TestDBUtility.insertWithDel(l_tradedProductParams);
            TestDBUtility.insertWithDel(l_tradingTimeParams);
            TestDBUtility.insertWithDel(l_mutualFundProductSonar2Params);
        }
        catch (WEB3BaseException l_ex)
        {
            log.error("", l_ex);
            log.exiting(STR_METHOD_NAME);
            fail();
        }
        try
        {
            InstitutionImpl l_institutionImpl = new InstitutionImpl(33);

            WEB3MutualFundProductCondSpec l_productCondSpec = new WEB3MutualFundProductCondSpec();
            l_productCondSpec.setMutualProductCode("12");
            l_productCondSpec.setBuyEndDate(new Timestamp(WEB3DateUtility.getDate("20040709","yyyyMMdd").getTime()));
            l_productCondSpec.setSellSwitchingEndDate(new Timestamp(WEB3DateUtility.getDate("20040709","yyyyMMdd").getTime()));
            l_productCondSpec.setBuyFrgnMinAmtBuy("10");
            l_productCondSpec.setBuyFrgnUnitAmtBuy("18");
            l_productCondSpec.setBuyFrgnMinAmtAdd("10");
            l_productCondSpec.setBuyFrgnUnitAmtAdd("36");
            l_productCondSpec.setSellFrgnMinAmtSell("13");
            l_productCondSpec.setSellFrgnUnitAmtSell("15");
            l_productCondSpec.setAddBuyUnitQty("111");
            l_productCondSpec.setSellSwitchingStartDate(WEB3DateUtility.getDate("20040715","yyyyMMdd"));

            WEB3MutualFundProductManager l_mutualFundProductManager = new WEB3MutualFundProductManager();
            l_mutualFundProductManager.validateProductCond(
                l_institutionImpl,
                l_productCondSpec);
            fail();
        }
        catch (WEB3BaseException l_ex)
        {
            log.error("", l_ex);
            assertEquals(WEB3ErrorCatalog.BUSINESS_ERROR_00346, l_ex.getErrorInfo());
        }
        catch (Exception l_ex)
        {
            log.error("ERROR", l_ex);
            fail();
        }
        log.exiting(STR_METHOD_NAME);
    }

    public void testValidateProductCondCase16()
    {
        final String STR_METHOD_NAME = "testValidateProductCondCase16()";
        log.entering(STR_METHOD_NAME);
        MutualFundProductParams l_mutualFundProductParams =
            TestDBUtility.getMutualFundProductRow();
        l_mutualFundProductParams.setInstitutionCode("0D");
        l_mutualFundProductParams.setProductCode("12");
        l_mutualFundProductParams.setFundType(MutualFundTypeEnum.FOREIGN_MMF);
        l_mutualFundProductParams.setRedemptionDate(
            new Timestamp(WEB3DateUtility.getDate("20040715","yyyyMMdd").getTime()));
        l_mutualFundProductParams.setSellBanDate(
            WEB3DateUtility.getDate("20040715","yyyyMMdd"));
        
        ProductParams l_productParams = TestDBUtility.getProductRow();
        l_productParams.setProductId(0100000L);

        InstitutionParams l_institutionParams = TestDBUtility.getInstitutionRow();
        MutualFundTradedProductParams l_mutualFundTradedProductParams =
            TestDBUtility.getMutualFundTradedProductRow();
        l_mutualFundTradedProductParams.setMarketId(3303L);
        l_mutualFundTradedProductParams.setInstitutionCode("0D");
        l_mutualFundTradedProductParams.setProductId(0100000L);
        l_mutualFundTradedProductParams.setExecDateShiftdays(3);

        MarketParams l_marketParams = TestDBUtility.getMarketRow();
        l_marketParams.setMarketCode("0");

        TradedProductParams l_tradedProductParams = TestDBUtility.getTradedProductRow();
        l_tradedProductParams.setTradedProductId(1006160060005L);

        TradingTimeParams l_tradingTimeParams = TestDBUtility.getTradingTimeRow();
        l_tradingTimeParams.setTradingTimeType("01");
        l_tradingTimeParams.setProductCode("12");
        l_tradingTimeParams.setBizDateType("1");

        MutualFund2ndProductSonarParams l_mutualFundProductSonar2Params = new MutualFund2ndProductSonarParams();
        l_mutualFundProductSonar2Params.setInstitutionCode("0D");
        l_mutualFundProductSonar2Params.setProductCode("12");
        l_mutualFundProductSonar2Params.setFrgnBuyMinAmt(10);
        l_mutualFundProductSonar2Params.setFrgnBuyUnitAmt(9);
        l_mutualFundProductSonar2Params.setFrgnSellMinAmt(13);
        l_mutualFundProductSonar2Params.setFrgnSellUnitAmt(15);
        l_mutualFundProductSonar2Params.setBuyUnitAmt(0);
        try
        {
            TestDBUtility.insertWithDel(l_mutualFundProductParams);
            TestDBUtility.insertWithDel(l_productParams);
            TestDBUtility.insertWithDel(l_institutionParams);
            TestDBUtility.insertWithDel(l_mutualFundTradedProductParams);
            TestDBUtility.insertWithDel(l_marketParams);
            TestDBUtility.insertWithDel(l_tradedProductParams);
            TestDBUtility.insertWithDel(l_tradingTimeParams);
            TestDBUtility.insertWithDel(l_mutualFundProductSonar2Params);
        }
        catch (WEB3BaseException l_ex)
        {
            log.error("", l_ex);
            log.exiting(STR_METHOD_NAME);
            fail();
        }
        try
        {
            InstitutionImpl l_institutionImpl = new InstitutionImpl(33);

            WEB3MutualFundProductCondSpec l_productCondSpec = new WEB3MutualFundProductCondSpec();
            l_productCondSpec.setMutualProductCode("12");
            l_productCondSpec.setBuyEndDate(new Timestamp(WEB3DateUtility.getDate("20040709","yyyyMMdd").getTime()));
            l_productCondSpec.setSellSwitchingEndDate(new Timestamp(WEB3DateUtility.getDate("20040709","yyyyMMdd").getTime()));
            l_productCondSpec.setBuyFrgnMinAmtBuy("10");
            l_productCondSpec.setBuyFrgnUnitAmtBuy("18");
            l_productCondSpec.setBuyFrgnMinAmtAdd("10");
            l_productCondSpec.setBuyFrgnUnitAmtAdd("36");
            l_productCondSpec.setSellFrgnMinAmtSell("13");
            l_productCondSpec.setSellFrgnUnitAmtSell("15");
            l_productCondSpec.setAddBuyUnitAmt("111");
            l_productCondSpec.setSellSwitchingStartDate(WEB3DateUtility.getDate("20040715","yyyyMMdd"));

            WEB3MutualFundProductManager l_mutualFundProductManager = new WEB3MutualFundProductManager();
            l_mutualFundProductManager.validateProductCond(
                l_institutionImpl,
                l_productCondSpec);
            fail();
        }
        catch (WEB3BaseException l_ex)
        {
            log.error("", l_ex);
            assertEquals(WEB3ErrorCatalog.BUSINESS_ERROR_00348, l_ex.getErrorInfo());
        }
        catch (Exception l_ex)
        {
            log.error("ERROR", l_ex);
            fail();
        }
        log.exiting(STR_METHOD_NAME);
    }

    public void testValidateProductCondCase17()
    {
        final String STR_METHOD_NAME = "testValidateProductCondCase17()";
        log.entering(STR_METHOD_NAME);
        MutualFundProductParams l_mutualFundProductParams =
            TestDBUtility.getMutualFundProductRow();
        l_mutualFundProductParams.setInstitutionCode("0D");
        l_mutualFundProductParams.setProductCode("12");
        l_mutualFundProductParams.setFundType(MutualFundTypeEnum.FOREIGN_MMF);
        l_mutualFundProductParams.setRedemptionDate(
            new Timestamp(WEB3DateUtility.getDate("20040715","yyyyMMdd").getTime()));
        l_mutualFundProductParams.setSellBanDate(
            WEB3DateUtility.getDate("20040715","yyyyMMdd"));
        
        ProductParams l_productParams = TestDBUtility.getProductRow();
        l_productParams.setProductId(0100000L);

        InstitutionParams l_institutionParams = TestDBUtility.getInstitutionRow();
        MutualFundTradedProductParams l_mutualFundTradedProductParams =
            TestDBUtility.getMutualFundTradedProductRow();
        l_mutualFundTradedProductParams.setMarketId(3303L);
        l_mutualFundTradedProductParams.setInstitutionCode("0D");
        l_mutualFundTradedProductParams.setProductId(0100000L);
        l_mutualFundTradedProductParams.setExecDateShiftdays(3);

        MarketParams l_marketParams = TestDBUtility.getMarketRow();
        l_marketParams.setMarketCode("0");

        TradedProductParams l_tradedProductParams = TestDBUtility.getTradedProductRow();
        l_tradedProductParams.setTradedProductId(1006160060005L);

        TradingTimeParams l_tradingTimeParams = TestDBUtility.getTradingTimeRow();
        l_tradingTimeParams.setTradingTimeType("01");
        l_tradingTimeParams.setProductCode("12");
        l_tradingTimeParams.setBizDateType("1");

        MutualFund2ndProductSonarParams l_mutualFundProductSonar2Params = new MutualFund2ndProductSonarParams();
        l_mutualFundProductSonar2Params.setInstitutionCode("0D");
        l_mutualFundProductSonar2Params.setProductCode("12");
        l_mutualFundProductSonar2Params.setFrgnBuyMinAmt(10);
        l_mutualFundProductSonar2Params.setFrgnBuyUnitAmt(9);
        l_mutualFundProductSonar2Params.setFrgnSellMinAmt(13);
        l_mutualFundProductSonar2Params.setFrgnSellUnitAmt(15);
        l_mutualFundProductSonar2Params.setSellUnitQty(0);
        try
        {
            TestDBUtility.insertWithDel(l_mutualFundProductParams);
            TestDBUtility.insertWithDel(l_productParams);
            TestDBUtility.insertWithDel(l_institutionParams);
            TestDBUtility.insertWithDel(l_mutualFundTradedProductParams);
            TestDBUtility.insertWithDel(l_marketParams);
            TestDBUtility.insertWithDel(l_tradedProductParams);
            TestDBUtility.insertWithDel(l_tradingTimeParams);
            TestDBUtility.insertWithDel(l_mutualFundProductSonar2Params);
        }
        catch (WEB3BaseException l_ex)
        {
            log.error("", l_ex);
            log.exiting(STR_METHOD_NAME);
            fail();
        }
        try
        {
            InstitutionImpl l_institutionImpl = new InstitutionImpl(33);

            WEB3MutualFundProductCondSpec l_productCondSpec = new WEB3MutualFundProductCondSpec();
            l_productCondSpec.setMutualProductCode("12");
            l_productCondSpec.setBuyEndDate(new Timestamp(WEB3DateUtility.getDate("20040709","yyyyMMdd").getTime()));
            l_productCondSpec.setSellSwitchingEndDate(new Timestamp(WEB3DateUtility.getDate("20040709","yyyyMMdd").getTime()));
            l_productCondSpec.setBuyFrgnMinAmtBuy("10");
            l_productCondSpec.setBuyFrgnUnitAmtBuy("18");
            l_productCondSpec.setBuyFrgnMinAmtAdd("10");
            l_productCondSpec.setBuyFrgnUnitAmtAdd("36");
            l_productCondSpec.setSellFrgnMinAmtSell("13");
            l_productCondSpec.setSellFrgnUnitAmtSell("15");
            l_productCondSpec.setSellUnitQty("111");
            l_productCondSpec.setSellSwitchingStartDate(WEB3DateUtility.getDate("20040715","yyyyMMdd"));

            WEB3MutualFundProductManager l_mutualFundProductManager = new WEB3MutualFundProductManager();
            l_mutualFundProductManager.validateProductCond(
                l_institutionImpl,
                l_productCondSpec);
            fail();
        }
        catch (WEB3BaseException l_ex)
        {
            log.error("", l_ex);
            assertEquals(WEB3ErrorCatalog.BUSINESS_ERROR_00350, l_ex.getErrorInfo());
        }
        catch (Exception l_ex)
        {
            log.error("ERROR", l_ex);
            fail();
        }
        log.exiting(STR_METHOD_NAME);
    }

    public void testValidateProductCondCase18()
    {
        final String STR_METHOD_NAME = "testValidateProductCondCase18()";
        log.entering(STR_METHOD_NAME);
        MutualFundProductParams l_mutualFundProductParams =
            TestDBUtility.getMutualFundProductRow();
        l_mutualFundProductParams.setInstitutionCode("0D");
        l_mutualFundProductParams.setProductCode("12");
        l_mutualFundProductParams.setFundType(MutualFundTypeEnum.FOREIGN_MMF);
        l_mutualFundProductParams.setRedemptionDate(
            new Timestamp(WEB3DateUtility.getDate("20040715","yyyyMMdd").getTime()));
        l_mutualFundProductParams.setSellBanDate(
            WEB3DateUtility.getDate("20040715","yyyyMMdd"));
        
        ProductParams l_productParams = TestDBUtility.getProductRow();
        l_productParams.setProductId(0100000L);

        InstitutionParams l_institutionParams = TestDBUtility.getInstitutionRow();
        MutualFundTradedProductParams l_mutualFundTradedProductParams =
            TestDBUtility.getMutualFundTradedProductRow();
        l_mutualFundTradedProductParams.setMarketId(3303L);
        l_mutualFundTradedProductParams.setInstitutionCode("0D");
        l_mutualFundTradedProductParams.setProductId(0100000L);
        l_mutualFundTradedProductParams.setExecDateShiftdays(3);

        MarketParams l_marketParams = TestDBUtility.getMarketRow();
        l_marketParams.setMarketCode("0");

        TradedProductParams l_tradedProductParams = TestDBUtility.getTradedProductRow();
        l_tradedProductParams.setTradedProductId(1006160060005L);

        TradingTimeParams l_tradingTimeParams = TestDBUtility.getTradingTimeRow();
        l_tradingTimeParams.setTradingTimeType("01");
        l_tradingTimeParams.setProductCode("12");
        l_tradingTimeParams.setBizDateType("1");

        MutualFund2ndProductSonarParams l_mutualFundProductSonar2Params = new MutualFund2ndProductSonarParams();
        l_mutualFundProductSonar2Params.setInstitutionCode("0D");
        l_mutualFundProductSonar2Params.setProductCode("12");
        l_mutualFundProductSonar2Params.setFrgnBuyMinAmt(10);
        l_mutualFundProductSonar2Params.setFrgnBuyUnitAmt(9);
        l_mutualFundProductSonar2Params.setFrgnSellMinAmt(13);
        l_mutualFundProductSonar2Params.setFrgnSellUnitAmt(15);
        l_mutualFundProductSonar2Params.setSellUnitAmt(0);
        try
        {
            TestDBUtility.insertWithDel(l_mutualFundProductParams);
            TestDBUtility.insertWithDel(l_productParams);
            TestDBUtility.insertWithDel(l_institutionParams);
            TestDBUtility.insertWithDel(l_mutualFundTradedProductParams);
            TestDBUtility.insertWithDel(l_marketParams);
            TestDBUtility.insertWithDel(l_tradedProductParams);
            TestDBUtility.insertWithDel(l_tradingTimeParams);
            TestDBUtility.insertWithDel(l_mutualFundProductSonar2Params);
        }
        catch (WEB3BaseException l_ex)
        {
            log.error("", l_ex);
            log.exiting(STR_METHOD_NAME);
            fail();
        }
        try
        {
            InstitutionImpl l_institutionImpl = new InstitutionImpl(33);

            WEB3MutualFundProductCondSpec l_productCondSpec = new WEB3MutualFundProductCondSpec();
            l_productCondSpec.setMutualProductCode("12");
            l_productCondSpec.setBuyEndDate(new Timestamp(WEB3DateUtility.getDate("20040709","yyyyMMdd").getTime()));
            l_productCondSpec.setSellSwitchingEndDate(new Timestamp(WEB3DateUtility.getDate("20040709","yyyyMMdd").getTime()));
            l_productCondSpec.setBuyFrgnMinAmtBuy("10");
            l_productCondSpec.setBuyFrgnUnitAmtBuy("18");
            l_productCondSpec.setBuyFrgnMinAmtAdd("10");
            l_productCondSpec.setBuyFrgnUnitAmtAdd("36");
            l_productCondSpec.setSellFrgnMinAmtSell("13");
            l_productCondSpec.setSellFrgnUnitAmtSell("15");
            l_productCondSpec.setSellUnitAmt("111");
            l_productCondSpec.setSellSwitchingStartDate(WEB3DateUtility.getDate("20040715","yyyyMMdd"));

            WEB3MutualFundProductManager l_mutualFundProductManager = new WEB3MutualFundProductManager();
            l_mutualFundProductManager.validateProductCond(
                l_institutionImpl,
                l_productCondSpec);
            fail();
        }
        catch (WEB3BaseException l_ex)
        {
            log.error("", l_ex);
            assertEquals(WEB3ErrorCatalog.BUSINESS_ERROR_00352, l_ex.getErrorInfo());
        }
        catch (Exception l_ex)
        {
            log.error("ERROR", l_ex);
            fail();
        }
        log.exiting(STR_METHOD_NAME);
    }

    public void testValidateProductCondCase19()
    {
        final String STR_METHOD_NAME = "testValidateProductCondCase19()";
        log.entering(STR_METHOD_NAME);
        MutualFundProductParams l_mutualFundProductParams =
            TestDBUtility.getMutualFundProductRow();
        l_mutualFundProductParams.setInstitutionCode("0D");
        l_mutualFundProductParams.setProductCode("12");
        l_mutualFundProductParams.setFundType(MutualFundTypeEnum.FOREIGN_MMF);
        l_mutualFundProductParams.setRedemptionDate(
            new Timestamp(WEB3DateUtility.getDate("20040715","yyyyMMdd").getTime()));
        l_mutualFundProductParams.setSellBanDate(
            WEB3DateUtility.getDate("20040715","yyyyMMdd"));
        
        ProductParams l_productParams = TestDBUtility.getProductRow();
        l_productParams.setProductId(0100000L);

        InstitutionParams l_institutionParams = TestDBUtility.getInstitutionRow();
        MutualFundTradedProductParams l_mutualFundTradedProductParams =
            TestDBUtility.getMutualFundTradedProductRow();
        l_mutualFundTradedProductParams.setMarketId(3303L);
        l_mutualFundTradedProductParams.setInstitutionCode("0D");
        l_mutualFundTradedProductParams.setProductId(0100000L);
        l_mutualFundTradedProductParams.setExecDateShiftdays(3);

        MarketParams l_marketParams = TestDBUtility.getMarketRow();
        l_marketParams.setMarketCode("0");

        TradedProductParams l_tradedProductParams = TestDBUtility.getTradedProductRow();
        l_tradedProductParams.setTradedProductId(1006160060005L);

        TradingTimeParams l_tradingTimeParams = TestDBUtility.getTradingTimeRow();
        l_tradingTimeParams.setTradingTimeType("01");
        l_tradingTimeParams.setProductCode("12");
        l_tradingTimeParams.setBizDateType("1");

        MutualFund2ndProductSonarParams l_mutualFundProductSonar2Params = new MutualFund2ndProductSonarParams();
        l_mutualFundProductSonar2Params.setInstitutionCode("0D");
        l_mutualFundProductSonar2Params.setProductCode("12");
        l_mutualFundProductSonar2Params.setFrgnBuyMinAmt(10);
        l_mutualFundProductSonar2Params.setFrgnBuyUnitAmt(9);
        l_mutualFundProductSonar2Params.setFrgnSellMinAmt(13);
        l_mutualFundProductSonar2Params.setFrgnSellUnitAmt(15);
        l_mutualFundProductSonar2Params.setSwtUnitQty(0);
        try
        {
            TestDBUtility.insertWithDel(l_mutualFundProductParams);
            TestDBUtility.insertWithDel(l_productParams);
            TestDBUtility.insertWithDel(l_institutionParams);
            TestDBUtility.insertWithDel(l_mutualFundTradedProductParams);
            TestDBUtility.insertWithDel(l_marketParams);
            TestDBUtility.insertWithDel(l_tradedProductParams);
            TestDBUtility.insertWithDel(l_tradingTimeParams);
            TestDBUtility.insertWithDel(l_mutualFundProductSonar2Params);
        }
        catch (WEB3BaseException l_ex)
        {
            log.error("", l_ex);
            log.exiting(STR_METHOD_NAME);
            fail();
        }
        try
        {
            InstitutionImpl l_institutionImpl = new InstitutionImpl(33);

            WEB3MutualFundProductCondSpec l_productCondSpec = new WEB3MutualFundProductCondSpec();
            l_productCondSpec.setMutualProductCode("12");
            l_productCondSpec.setBuyEndDate(new Timestamp(WEB3DateUtility.getDate("20040709","yyyyMMdd").getTime()));
            l_productCondSpec.setSellSwitchingEndDate(new Timestamp(WEB3DateUtility.getDate("20040709","yyyyMMdd").getTime()));
            l_productCondSpec.setBuyFrgnMinAmtBuy("10");
            l_productCondSpec.setBuyFrgnUnitAmtBuy("18");
            l_productCondSpec.setBuyFrgnMinAmtAdd("10");
            l_productCondSpec.setBuyFrgnUnitAmtAdd("36");
            l_productCondSpec.setSellFrgnMinAmtSell("13");
            l_productCondSpec.setSellFrgnUnitAmtSell("15");
            l_productCondSpec.setSwitchingUnitQty("111");
            l_productCondSpec.setSellSwitchingStartDate(WEB3DateUtility.getDate("20040715","yyyyMMdd"));

            WEB3MutualFundProductManager l_mutualFundProductManager = new WEB3MutualFundProductManager();
            l_mutualFundProductManager.validateProductCond(
                l_institutionImpl,
                l_productCondSpec);
            fail();
        }
        catch (WEB3BaseException l_ex)
        {
            log.error("", l_ex);
            assertEquals(WEB3ErrorCatalog.BUSINESS_ERROR_00354, l_ex.getErrorInfo());
        }
        catch (Exception l_ex)
        {
            log.error("ERROR", l_ex);
            fail();
        }
        log.exiting(STR_METHOD_NAME);
    }

    public void testValidateProductCondCase20()
    {
        final String STR_METHOD_NAME = "testValidateProductCondCase20()";
        log.entering(STR_METHOD_NAME);
        MutualFundProductParams l_mutualFundProductParams =
            TestDBUtility.getMutualFundProductRow();
        l_mutualFundProductParams.setInstitutionCode("0D");
        l_mutualFundProductParams.setProductCode("12");
        l_mutualFundProductParams.setFundType(MutualFundTypeEnum.FOREIGN_MMF);
        l_mutualFundProductParams.setRedemptionDate(
            new Timestamp(WEB3DateUtility.getDate("20040715","yyyyMMdd").getTime()));
        l_mutualFundProductParams.setSellBanDate(
            WEB3DateUtility.getDate("20040715","yyyyMMdd"));
        
        ProductParams l_productParams = TestDBUtility.getProductRow();
        l_productParams.setProductId(0100000L);

        InstitutionParams l_institutionParams = TestDBUtility.getInstitutionRow();
        MutualFundTradedProductParams l_mutualFundTradedProductParams =
            TestDBUtility.getMutualFundTradedProductRow();
        l_mutualFundTradedProductParams.setMarketId(3303L);
        l_mutualFundTradedProductParams.setInstitutionCode("0D");
        l_mutualFundTradedProductParams.setProductId(0100000L);
        l_mutualFundTradedProductParams.setExecDateShiftdays(3);

        MarketParams l_marketParams = TestDBUtility.getMarketRow();
        l_marketParams.setMarketCode("0");

        TradedProductParams l_tradedProductParams = TestDBUtility.getTradedProductRow();
        l_tradedProductParams.setTradedProductId(1006160060005L);

        TradingTimeParams l_tradingTimeParams = TestDBUtility.getTradingTimeRow();
        l_tradingTimeParams.setTradingTimeType("01");
        l_tradingTimeParams.setProductCode("12");
        l_tradingTimeParams.setBizDateType("1");

        MutualFund2ndProductSonarParams l_mutualFundProductSonar2Params = new MutualFund2ndProductSonarParams();
        l_mutualFundProductSonar2Params.setInstitutionCode("0D");
        l_mutualFundProductSonar2Params.setProductCode("12");
        l_mutualFundProductSonar2Params.setFrgnBuyMinAmt(10);
        l_mutualFundProductSonar2Params.setFrgnBuyUnitAmt(9);
        l_mutualFundProductSonar2Params.setFrgnSellMinAmt(13);
        l_mutualFundProductSonar2Params.setFrgnSellUnitAmt(15);
        l_mutualFundProductSonar2Params.setSwtUnitAmt(0);
        try
        {
            TestDBUtility.insertWithDel(l_mutualFundProductParams);
            TestDBUtility.insertWithDel(l_productParams);
            TestDBUtility.insertWithDel(l_institutionParams);
            TestDBUtility.insertWithDel(l_mutualFundTradedProductParams);
            TestDBUtility.insertWithDel(l_marketParams);
            TestDBUtility.insertWithDel(l_tradedProductParams);
            TestDBUtility.insertWithDel(l_tradingTimeParams);
            TestDBUtility.insertWithDel(l_mutualFundProductSonar2Params);
        }
        catch (WEB3BaseException l_ex)
        {
            log.error("", l_ex);
            log.exiting(STR_METHOD_NAME);
            fail();
        }
        try
        {
            InstitutionImpl l_institutionImpl = new InstitutionImpl(33);

            WEB3MutualFundProductCondSpec l_productCondSpec = new WEB3MutualFundProductCondSpec();
            l_productCondSpec.setMutualProductCode("12");
            l_productCondSpec.setBuyEndDate(new Timestamp(WEB3DateUtility.getDate("20040709","yyyyMMdd").getTime()));
            l_productCondSpec.setSellSwitchingEndDate(new Timestamp(WEB3DateUtility.getDate("20040709","yyyyMMdd").getTime()));
            l_productCondSpec.setBuyFrgnMinAmtBuy("10");
            l_productCondSpec.setBuyFrgnUnitAmtBuy("18");
            l_productCondSpec.setBuyFrgnMinAmtAdd("10");
            l_productCondSpec.setBuyFrgnUnitAmtAdd("36");
            l_productCondSpec.setSellFrgnMinAmtSell("13");
            l_productCondSpec.setSellFrgnUnitAmtSell("15");
            l_productCondSpec.setSwitchingUnitAmt("111");
            l_productCondSpec.setSellSwitchingStartDate(WEB3DateUtility.getDate("20040715","yyyyMMdd"));

            WEB3MutualFundProductManager l_mutualFundProductManager = new WEB3MutualFundProductManager();
            l_mutualFundProductManager.validateProductCond(
                l_institutionImpl,
                l_productCondSpec);
            fail();
        }
        catch (WEB3BaseException l_ex)
        {
            log.error("", l_ex);
            assertEquals(WEB3ErrorCatalog.BUSINESS_ERROR_00356, l_ex.getErrorInfo());
        }
        catch (Exception l_ex)
        {
            log.error("ERROR", l_ex);
            fail();
        }
        log.exiting(STR_METHOD_NAME);
    }

    public void testValidateProductCondCase21()
    {
        final String STR_METHOD_NAME = "testValidateProductCondCase21()";
        log.entering(STR_METHOD_NAME);
        MutualFundProductParams l_mutualFundProductParams =
            TestDBUtility.getMutualFundProductRow();
        l_mutualFundProductParams.setInstitutionCode("0D");
        l_mutualFundProductParams.setProductCode("12");
        l_mutualFundProductParams.setFundType(MutualFundTypeEnum.FOREIGN_MMF);
        l_mutualFundProductParams.setRedemptionDate(
            new Timestamp(WEB3DateUtility.getDate("20040715","yyyyMMdd").getTime()));
        l_mutualFundProductParams.setSellBanDate(
            WEB3DateUtility.getDate("20040715","yyyyMMdd"));
        
        ProductParams l_productParams = TestDBUtility.getProductRow();
        l_productParams.setProductId(0100000L);

        InstitutionParams l_institutionParams = TestDBUtility.getInstitutionRow();
        MutualFundTradedProductParams l_mutualFundTradedProductParams =
            TestDBUtility.getMutualFundTradedProductRow();
        l_mutualFundTradedProductParams.setMarketId(3303L);
        l_mutualFundTradedProductParams.setInstitutionCode("0D");
        l_mutualFundTradedProductParams.setProductId(0100000L);
        l_mutualFundTradedProductParams.setExecDateShiftdays(3);

        MarketParams l_marketParams = TestDBUtility.getMarketRow();
        l_marketParams.setMarketCode("0");

        TradedProductParams l_tradedProductParams = TestDBUtility.getTradedProductRow();
        l_tradedProductParams.setTradedProductId(1006160060005L);

        TradingTimeParams l_tradingTimeParams = TestDBUtility.getTradingTimeRow();
        l_tradingTimeParams.setTradingTimeType("01");
        l_tradingTimeParams.setProductCode("12");
        l_tradingTimeParams.setBizDateType("1");

        MutualFund2ndProductSonarParams l_mutualFundProductSonar2Params = new MutualFund2ndProductSonarParams();
        l_mutualFundProductSonar2Params.setInstitutionCode("0D");
        l_mutualFundProductSonar2Params.setProductCode("12");
        l_mutualFundProductSonar2Params.setFrgnBuyMinAmt(10);
        l_mutualFundProductSonar2Params.setFrgnBuyUnitAmt(9);
        l_mutualFundProductSonar2Params.setFrgnSellMinAmt(13);
        l_mutualFundProductSonar2Params.setFrgnSellUnitAmt(15);
        l_mutualFundProductSonar2Params.setRecruitUnitQty(0);
        try
        {
            TestDBUtility.insertWithDel(l_mutualFundProductParams);
            TestDBUtility.insertWithDel(l_productParams);
            TestDBUtility.insertWithDel(l_institutionParams);
            TestDBUtility.insertWithDel(l_mutualFundTradedProductParams);
            TestDBUtility.insertWithDel(l_marketParams);
            TestDBUtility.insertWithDel(l_tradedProductParams);
            TestDBUtility.insertWithDel(l_tradingTimeParams);
            TestDBUtility.insertWithDel(l_mutualFundProductSonar2Params);
        }
        catch (WEB3BaseException l_ex)
        {
            log.error("", l_ex);
            log.exiting(STR_METHOD_NAME);
            fail();
        }
        try
        {
            InstitutionImpl l_institutionImpl = new InstitutionImpl(33);

            WEB3MutualFundProductCondSpec l_productCondSpec = new WEB3MutualFundProductCondSpec();
            l_productCondSpec.setMutualProductCode("12");
            l_productCondSpec.setBuyEndDate(new Timestamp(WEB3DateUtility.getDate("20040709","yyyyMMdd").getTime()));
            l_productCondSpec.setSellSwitchingEndDate(new Timestamp(WEB3DateUtility.getDate("20040709","yyyyMMdd").getTime()));
            l_productCondSpec.setBuyFrgnMinAmtBuy("10");
            l_productCondSpec.setBuyFrgnUnitAmtBuy("18");
            l_productCondSpec.setBuyFrgnMinAmtAdd("10");
            l_productCondSpec.setBuyFrgnUnitAmtAdd("36");
            l_productCondSpec.setSellFrgnMinAmtSell("13");
            l_productCondSpec.setSellFrgnUnitAmtSell("15");
            l_productCondSpec.setApplyUnitQty("111");
            l_productCondSpec.setSellSwitchingStartDate(WEB3DateUtility.getDate("20040715","yyyyMMdd"));

            WEB3MutualFundProductManager l_mutualFundProductManager = new WEB3MutualFundProductManager();
            l_mutualFundProductManager.validateProductCond(
                l_institutionImpl,
                l_productCondSpec);
            fail();
        }
        catch (WEB3BaseException l_ex)
        {
            log.error("", l_ex);
            assertEquals(WEB3ErrorCatalog.BUSINESS_ERROR_02275, l_ex.getErrorInfo());
        }
        catch (Exception l_ex)
        {
            log.error("ERROR", l_ex);
            fail();
        }
        log.exiting(STR_METHOD_NAME);
    }

    public void testValidateProductCondCase22()
    {
        final String STR_METHOD_NAME = "testValidateProductCondCase22()";
        log.entering(STR_METHOD_NAME);
        MutualFundProductParams l_mutualFundProductParams =
            TestDBUtility.getMutualFundProductRow();
        l_mutualFundProductParams.setInstitutionCode("0D");
        l_mutualFundProductParams.setProductCode("12");
        l_mutualFundProductParams.setFundType(MutualFundTypeEnum.FOREIGN_MMF);
        l_mutualFundProductParams.setRedemptionDate(
            new Timestamp(WEB3DateUtility.getDate("20040715","yyyyMMdd").getTime()));
        l_mutualFundProductParams.setSellBanDate(
            WEB3DateUtility.getDate("20040715","yyyyMMdd"));
        
        ProductParams l_productParams = TestDBUtility.getProductRow();
        l_productParams.setProductId(0100000L);

        InstitutionParams l_institutionParams = TestDBUtility.getInstitutionRow();
        MutualFundTradedProductParams l_mutualFundTradedProductParams =
            TestDBUtility.getMutualFundTradedProductRow();
        l_mutualFundTradedProductParams.setMarketId(3303L);
        l_mutualFundTradedProductParams.setInstitutionCode("0D");
        l_mutualFundTradedProductParams.setProductId(0100000L);
        l_mutualFundTradedProductParams.setExecDateShiftdays(3);

        MarketParams l_marketParams = TestDBUtility.getMarketRow();
        l_marketParams.setMarketCode("0");

        TradedProductParams l_tradedProductParams = TestDBUtility.getTradedProductRow();
        l_tradedProductParams.setTradedProductId(1006160060005L);

        TradingTimeParams l_tradingTimeParams = TestDBUtility.getTradingTimeRow();
        l_tradingTimeParams.setTradingTimeType("01");
        l_tradingTimeParams.setProductCode("12");
        l_tradingTimeParams.setBizDateType("1");

        MutualFund2ndProductSonarParams l_mutualFundProductSonar2Params = new MutualFund2ndProductSonarParams();
        l_mutualFundProductSonar2Params.setInstitutionCode("0D");
        l_mutualFundProductSonar2Params.setProductCode("12");
        l_mutualFundProductSonar2Params.setFrgnBuyMinAmt(10);
        l_mutualFundProductSonar2Params.setFrgnBuyUnitAmt(9);
        l_mutualFundProductSonar2Params.setFrgnSellMinAmt(13);
        l_mutualFundProductSonar2Params.setFrgnSellUnitAmt(15);
        l_mutualFundProductSonar2Params.setRecruitUnitAmt(0);
        try
        {
            TestDBUtility.insertWithDel(l_mutualFundProductParams);
            TestDBUtility.insertWithDel(l_productParams);
            TestDBUtility.insertWithDel(l_institutionParams);
            TestDBUtility.insertWithDel(l_mutualFundTradedProductParams);
            TestDBUtility.insertWithDel(l_marketParams);
            TestDBUtility.insertWithDel(l_tradedProductParams);
            TestDBUtility.insertWithDel(l_tradingTimeParams);
            TestDBUtility.insertWithDel(l_mutualFundProductSonar2Params);
        }
        catch (WEB3BaseException l_ex)
        {
            log.error("", l_ex);
            log.exiting(STR_METHOD_NAME);
            fail();
        }
        try
        {
            InstitutionImpl l_institutionImpl = new InstitutionImpl(33);

            WEB3MutualFundProductCondSpec l_productCondSpec = new WEB3MutualFundProductCondSpec();
            l_productCondSpec.setMutualProductCode("12");
            l_productCondSpec.setBuyEndDate(new Timestamp(WEB3DateUtility.getDate("20040709","yyyyMMdd").getTime()));
            l_productCondSpec.setSellSwitchingEndDate(new Timestamp(WEB3DateUtility.getDate("20040709","yyyyMMdd").getTime()));
            l_productCondSpec.setBuyFrgnMinAmtBuy("10");
            l_productCondSpec.setBuyFrgnUnitAmtBuy("18");
            l_productCondSpec.setBuyFrgnMinAmtAdd("10");
            l_productCondSpec.setBuyFrgnUnitAmtAdd("36");
            l_productCondSpec.setSellFrgnMinAmtSell("13");
            l_productCondSpec.setSellFrgnUnitAmtSell("15");
            l_productCondSpec.setApplyUnitAmt("111");
            l_productCondSpec.setSellSwitchingStartDate(WEB3DateUtility.getDate("20040715","yyyyMMdd"));

            WEB3MutualFundProductManager l_mutualFundProductManager = new WEB3MutualFundProductManager();
            l_mutualFundProductManager.validateProductCond(
                l_institutionImpl,
                l_productCondSpec);
            fail();
        }
        catch (WEB3BaseException l_ex)
        {
            log.error("", l_ex);
            assertEquals(WEB3ErrorCatalog.BUSINESS_ERROR_02277, l_ex.getErrorInfo());
        }
        catch (Exception l_ex)
        {
            log.error("ERROR", l_ex);
            fail();
        }
        log.exiting(STR_METHOD_NAME);
    }

    public void testValidateProductCondCase23()
    {
        final String STR_METHOD_NAME = "testValidateProductCondCase23()";
        log.entering(STR_METHOD_NAME);
        MutualFundProductParams l_mutualFundProductParams =
            TestDBUtility.getMutualFundProductRow();
        l_mutualFundProductParams.setInstitutionCode("0D");
        l_mutualFundProductParams.setProductCode("12");
        l_mutualFundProductParams.setFundType(MutualFundTypeEnum.FOREIGN_MMF);
        l_mutualFundProductParams.setRedemptionDate(
            new Timestamp(WEB3DateUtility.getDate("20040715","yyyyMMdd").getTime()));
        l_mutualFundProductParams.setSellBanDate(
            WEB3DateUtility.getDate("20040715","yyyyMMdd"));
        
        ProductParams l_productParams = TestDBUtility.getProductRow();
        l_productParams.setProductId(0100000L);

        InstitutionParams l_institutionParams = TestDBUtility.getInstitutionRow();
        MutualFundTradedProductParams l_mutualFundTradedProductParams =
            TestDBUtility.getMutualFundTradedProductRow();
        l_mutualFundTradedProductParams.setMarketId(3303L);
        l_mutualFundTradedProductParams.setInstitutionCode("0D");
        l_mutualFundTradedProductParams.setProductId(0100000L);
        l_mutualFundTradedProductParams.setExecDateShiftdays(3);

        MarketParams l_marketParams = TestDBUtility.getMarketRow();
        l_marketParams.setMarketCode("0");

        TradedProductParams l_tradedProductParams = TestDBUtility.getTradedProductRow();
        l_tradedProductParams.setTradedProductId(1006160060005L);

        TradingTimeParams l_tradingTimeParams = TestDBUtility.getTradingTimeRow();
        l_tradingTimeParams.setTradingTimeType("01");
        l_tradingTimeParams.setProductCode("12");
        l_tradingTimeParams.setBizDateType("1");

        MutualFund2ndProductSonarParams l_mutualFundProductSonar2Params = new MutualFund2ndProductSonarParams();
        l_mutualFundProductSonar2Params.setInstitutionCode("0D");
        l_mutualFundProductSonar2Params.setProductCode("12");
        l_mutualFundProductSonar2Params.setFrgnBuyMinAmt(10);
        l_mutualFundProductSonar2Params.setFrgnBuyUnitAmt(0);
        l_mutualFundProductSonar2Params.setFrgnSellMinAmt(13);
        l_mutualFundProductSonar2Params.setFrgnSellUnitAmt(15);
        try
        {
            TestDBUtility.insertWithDel(l_mutualFundProductParams);
            TestDBUtility.insertWithDel(l_productParams);
            TestDBUtility.insertWithDel(l_institutionParams);
            TestDBUtility.insertWithDel(l_mutualFundTradedProductParams);
            TestDBUtility.insertWithDel(l_marketParams);
            TestDBUtility.insertWithDel(l_tradedProductParams);
            TestDBUtility.insertWithDel(l_tradingTimeParams);
            TestDBUtility.insertWithDel(l_mutualFundProductSonar2Params);
        }
        catch (WEB3BaseException l_ex)
        {
            log.error("", l_ex);
            log.exiting(STR_METHOD_NAME);
            fail();
        }
        try
        {
            InstitutionImpl l_institutionImpl = new InstitutionImpl(33);

            WEB3MutualFundProductCondSpec l_productCondSpec = new WEB3MutualFundProductCondSpec();
            l_productCondSpec.setMutualProductCode("12");
            l_productCondSpec.setBuyEndDate(new Timestamp(WEB3DateUtility.getDate("20040709","yyyyMMdd").getTime()));
            l_productCondSpec.setSellSwitchingEndDate(new Timestamp(WEB3DateUtility.getDate("20040709","yyyyMMdd").getTime()));
            l_productCondSpec.setBuyFrgnMinAmtBuy("10");
            l_productCondSpec.setBuyFrgnUnitAmtBuy("18");
            l_productCondSpec.setBuyFrgnMinAmtAdd("10");
            l_productCondSpec.setBuyFrgnUnitAmtAdd("36");
            l_productCondSpec.setSellFrgnMinAmtSell("13");
            l_productCondSpec.setSellFrgnUnitAmtSell("15");
            l_productCondSpec.setBuyFrgnUnitAmtBuy("111");
            l_productCondSpec.setSellSwitchingStartDate(WEB3DateUtility.getDate("20040715","yyyyMMdd"));

            WEB3MutualFundProductManager l_mutualFundProductManager = new WEB3MutualFundProductManager();
            l_mutualFundProductManager.validateProductCond(
                l_institutionImpl,
                l_productCondSpec);
            fail();
        }
        catch (WEB3BaseException l_ex)
        {
            log.error("", l_ex);
            assertEquals(WEB3ErrorCatalog.BUSINESS_ERROR_02746, l_ex.getErrorInfo());
        }
        catch (Exception l_ex)
        {
            log.error("ERROR", l_ex);
            fail();
        }
        log.exiting(STR_METHOD_NAME);
    }

    public void testValidateProductCondCase24()
    {
        final String STR_METHOD_NAME = "testValidateProductCondCase24()";
        log.entering(STR_METHOD_NAME);
        MutualFundProductParams l_mutualFundProductParams =
            TestDBUtility.getMutualFundProductRow();
        l_mutualFundProductParams.setInstitutionCode("0D");
        l_mutualFundProductParams.setProductCode("12");
        l_mutualFundProductParams.setFundType(MutualFundTypeEnum.FOREIGN_MMF);
        l_mutualFundProductParams.setRedemptionDate(
            new Timestamp(WEB3DateUtility.getDate("20040715","yyyyMMdd").getTime()));
        l_mutualFundProductParams.setSellBanDate(
            WEB3DateUtility.getDate("20040715","yyyyMMdd"));
        
        ProductParams l_productParams = TestDBUtility.getProductRow();
        l_productParams.setProductId(0100000L);

        InstitutionParams l_institutionParams = TestDBUtility.getInstitutionRow();
        MutualFundTradedProductParams l_mutualFundTradedProductParams =
            TestDBUtility.getMutualFundTradedProductRow();
        l_mutualFundTradedProductParams.setMarketId(3303L);
        l_mutualFundTradedProductParams.setInstitutionCode("0D");
        l_mutualFundTradedProductParams.setProductId(0100000L);
        l_mutualFundTradedProductParams.setExecDateShiftdays(3);

        MarketParams l_marketParams = TestDBUtility.getMarketRow();
        l_marketParams.setMarketCode("0");

        TradedProductParams l_tradedProductParams = TestDBUtility.getTradedProductRow();
        l_tradedProductParams.setTradedProductId(1006160060005L);

        TradingTimeParams l_tradingTimeParams = TestDBUtility.getTradingTimeRow();
        l_tradingTimeParams.setTradingTimeType("01");
        l_tradingTimeParams.setProductCode("12");
        l_tradingTimeParams.setBizDateType("1");

        MutualFund2ndProductSonarParams l_mutualFundProductSonar2Params = new MutualFund2ndProductSonarParams();
        l_mutualFundProductSonar2Params.setInstitutionCode("0D");
        l_mutualFundProductSonar2Params.setProductCode("12");
        l_mutualFundProductSonar2Params.setFrgnBuyMinAmt(10);
        l_mutualFundProductSonar2Params.setFrgnBuyUnitAmt(0);
        l_mutualFundProductSonar2Params.setFrgnSellMinAmt(13);
        l_mutualFundProductSonar2Params.setFrgnSellUnitAmt(15);
        l_mutualFundProductSonar2Params.setFrgnBuyUnitAmt(0);
        try
        {
            TestDBUtility.insertWithDel(l_mutualFundProductParams);
            TestDBUtility.insertWithDel(l_productParams);
            TestDBUtility.insertWithDel(l_institutionParams);
            TestDBUtility.insertWithDel(l_mutualFundTradedProductParams);
            TestDBUtility.insertWithDel(l_marketParams);
            TestDBUtility.insertWithDel(l_tradedProductParams);
            TestDBUtility.insertWithDel(l_tradingTimeParams);
            TestDBUtility.insertWithDel(l_mutualFundProductSonar2Params);
        }
        catch (WEB3BaseException l_ex)
        {
            log.error("", l_ex);
            log.exiting(STR_METHOD_NAME);
            fail();
        }
        try
        {
            InstitutionImpl l_institutionImpl = new InstitutionImpl(33);

            WEB3MutualFundProductCondSpec l_productCondSpec = new WEB3MutualFundProductCondSpec();
            l_productCondSpec.setMutualProductCode("12");
            l_productCondSpec.setBuyEndDate(new Timestamp(WEB3DateUtility.getDate("20040709","yyyyMMdd").getTime()));
            l_productCondSpec.setSellSwitchingEndDate(new Timestamp(WEB3DateUtility.getDate("20040709","yyyyMMdd").getTime()));
            l_productCondSpec.setBuyFrgnMinAmtBuy("10");
            l_productCondSpec.setBuyFrgnMinAmtAdd("10");
            l_productCondSpec.setBuyFrgnUnitAmtAdd("36");
            l_productCondSpec.setSellFrgnMinAmtSell("13");
            l_productCondSpec.setSellFrgnUnitAmtSell("15");
            l_productCondSpec.setBuyFrgnUnitAmtAdd("111");
            l_productCondSpec.setSellSwitchingStartDate(WEB3DateUtility.getDate("20040715","yyyyMMdd"));

            WEB3MutualFundProductManager l_mutualFundProductManager = new WEB3MutualFundProductManager();
            l_mutualFundProductManager.validateProductCond(
                l_institutionImpl,
                l_productCondSpec);
            fail();
        }
        catch (WEB3BaseException l_ex)
        {
            log.error("", l_ex);
            assertEquals(WEB3ErrorCatalog.BUSINESS_ERROR_02748, l_ex.getErrorInfo());
        }
        catch (Exception l_ex)
        {
            log.error("ERROR", l_ex);
            fail();
        }
        log.exiting(STR_METHOD_NAME);
    }

    public void testValidateProductCondCase25()
    {
        final String STR_METHOD_NAME = "testValidateProductCondCase25()";
        log.entering(STR_METHOD_NAME);
        MutualFundProductParams l_mutualFundProductParams =
            TestDBUtility.getMutualFundProductRow();
        l_mutualFundProductParams.setInstitutionCode("0D");
        l_mutualFundProductParams.setProductCode("12");
        l_mutualFundProductParams.setFundType(MutualFundTypeEnum.FOREIGN_MMF);
        l_mutualFundProductParams.setRedemptionDate(
            new Timestamp(WEB3DateUtility.getDate("20040715","yyyyMMdd").getTime()));
        l_mutualFundProductParams.setSellBanDate(
            WEB3DateUtility.getDate("20040715","yyyyMMdd"));
        
        ProductParams l_productParams = TestDBUtility.getProductRow();
        l_productParams.setProductId(0100000L);

        InstitutionParams l_institutionParams = TestDBUtility.getInstitutionRow();
        MutualFundTradedProductParams l_mutualFundTradedProductParams =
            TestDBUtility.getMutualFundTradedProductRow();
        l_mutualFundTradedProductParams.setMarketId(3303L);
        l_mutualFundTradedProductParams.setInstitutionCode("0D");
        l_mutualFundTradedProductParams.setProductId(0100000L);
        l_mutualFundTradedProductParams.setExecDateShiftdays(3);

        MarketParams l_marketParams = TestDBUtility.getMarketRow();
        l_marketParams.setMarketCode("0");

        TradedProductParams l_tradedProductParams = TestDBUtility.getTradedProductRow();
        l_tradedProductParams.setTradedProductId(1006160060005L);

        TradingTimeParams l_tradingTimeParams = TestDBUtility.getTradingTimeRow();
        l_tradingTimeParams.setTradingTimeType("01");
        l_tradingTimeParams.setProductCode("12");
        l_tradingTimeParams.setBizDateType("1");

        MutualFund2ndProductSonarParams l_mutualFundProductSonar2Params = new MutualFund2ndProductSonarParams();
        l_mutualFundProductSonar2Params.setInstitutionCode("0D");
        l_mutualFundProductSonar2Params.setProductCode("12");
        l_mutualFundProductSonar2Params.setFrgnBuyMinAmt(10);
        l_mutualFundProductSonar2Params.setFrgnBuyUnitAmt(10);
        l_mutualFundProductSonar2Params.setFrgnSellMinAmt(13);
        l_mutualFundProductSonar2Params.setFrgnSellUnitAmt(15);
        l_mutualFundProductSonar2Params.setFrgnSellUnitAmt(0);
        try
        {
            TestDBUtility.insertWithDel(l_mutualFundProductParams);
            TestDBUtility.insertWithDel(l_productParams);
            TestDBUtility.insertWithDel(l_institutionParams);
            TestDBUtility.insertWithDel(l_mutualFundTradedProductParams);
            TestDBUtility.insertWithDel(l_marketParams);
            TestDBUtility.insertWithDel(l_tradedProductParams);
            TestDBUtility.insertWithDel(l_tradingTimeParams);
            TestDBUtility.insertWithDel(l_mutualFundProductSonar2Params);
        }
        catch (WEB3BaseException l_ex)
        {
            log.error("", l_ex);
            log.exiting(STR_METHOD_NAME);
            fail();
        }
        try
        {
            InstitutionImpl l_institutionImpl = new InstitutionImpl(33);

            WEB3MutualFundProductCondSpec l_productCondSpec = new WEB3MutualFundProductCondSpec();
            l_productCondSpec.setMutualProductCode("12");
            l_productCondSpec.setBuyEndDate(new Timestamp(WEB3DateUtility.getDate("20040709","yyyyMMdd").getTime()));
            l_productCondSpec.setSellSwitchingEndDate(new Timestamp(WEB3DateUtility.getDate("20040709","yyyyMMdd").getTime()));
            l_productCondSpec.setBuyFrgnMinAmtBuy("10");
            l_productCondSpec.setBuyFrgnMinAmtAdd("10");
            l_productCondSpec.setSellFrgnMinAmtSell("13");
            l_productCondSpec.setSellFrgnUnitAmtSell("15");
            l_productCondSpec.setSellFrgnUnitAmtSell("111");
            l_productCondSpec.setSellSwitchingStartDate(WEB3DateUtility.getDate("20040715","yyyyMMdd"));

            WEB3MutualFundProductManager l_mutualFundProductManager = new WEB3MutualFundProductManager();
            l_mutualFundProductManager.validateProductCond(
                l_institutionImpl,
                l_productCondSpec);
            fail();
        }
        catch (WEB3BaseException l_ex)
        {
            log.error("", l_ex);
            assertEquals(WEB3ErrorCatalog.BUSINESS_ERROR_02750, l_ex.getErrorInfo());
        }
        catch (Exception l_ex)
        {
            log.error("ERROR", l_ex);
            fail();
        }
        log.exiting(STR_METHOD_NAME);
    }

    public void testGetMutualFundProductC0001()
    {
        final String STR_METHOD_NAME = "testGetMutualFundProductC0001()";
        log.entering(STR_METHOD_NAME);

        WEB3MutualFundProductManager l_mutualFundProductManager =
            new WEB3MutualFundProductManager();
        try
        {
            TestDBUtility.deleteAll(MutualFundProductParams.TYPE);
            MutualFundProductParams l_mutualFundProductParams = TestDBUtility.getMutualFundProductRow();
            l_mutualFundProductParams.setInstitutionCode("0D");
            l_mutualFundProductParams.setProductCode("12");
            l_mutualFundProductParams.setCategoryCode("1");
            l_mutualFundProductParams.setFundType(MutualFundTypeEnum.FOREIGN_MMF);
            l_mutualFundProductParams.setRedemptionDate(new Timestamp(WEB3DateUtility.getDate("20040715","yyyyMMdd").getTime()));
            l_mutualFundProductParams.setSellBanDate(WEB3DateUtility.getDate("20040715","yyyyMMdd"));
            TestDBUtility.insertWithDel(l_mutualFundProductParams);
        }
        catch (WEB3BaseException l_ex)
        {
            log.error("", l_ex);
            log.exiting(STR_METHOD_NAME);
            fail();
        }

        List l_lisRows =null;
        try
        {
        l_lisRows = l_mutualFundProductManager.getMutualFundProduct("0D","2");
        }
        catch (WEB3BaseException l_ex)
        {
            log.error("", l_ex);
            log.exiting(STR_METHOD_NAME);
            fail();
        }
        assertNull(l_lisRows);
        
        log.exiting(STR_METHOD_NAME);
    }
    public void testGetMutualFundProductC0002()
    {
        final String STR_METHOD_NAME = "testGetMutualFundProductC0001()";
        log.entering(STR_METHOD_NAME);
        
        WEB3MutualFundProductManager l_mutualFundProductManager =
            new WEB3MutualFundProductManager();
        try
        {
            TestDBUtility.deleteAll(MutualFundProductParams.TYPE);
            MutualFundProductParams l_mutualFundProductParams = TestDBUtility.getMutualFundProductRow();
            l_mutualFundProductParams.setInstitutionCode("0D");
            l_mutualFundProductParams.setProductCode("12");
            l_mutualFundProductParams.setCategoryCode("1");
            l_mutualFundProductParams.setFundType(MutualFundTypeEnum.FOREIGN_MMF);
            l_mutualFundProductParams.setRedemptionDate(new Timestamp(WEB3DateUtility.getDate("20040715","yyyyMMdd").getTime()));
            l_mutualFundProductParams.setSellBanDate(WEB3DateUtility.getDate("20040715","yyyyMMdd"));
            TestDBUtility.insertWithDel(l_mutualFundProductParams);
        }
        catch (WEB3BaseException l_ex)
        {
            log.error("", l_ex);
            log.exiting(STR_METHOD_NAME);
            fail();
        }

        List l_lisRows =null;
        try
        {
        l_lisRows = l_mutualFundProductManager.getMutualFundProduct("0D","1");
        }
        catch (WEB3BaseException l_ex)
        {
            log.error("", l_ex);
            log.exiting(STR_METHOD_NAME);
            fail();
        }
        assertEquals(String.valueOf(l_lisRows.size()),"1");
        log.exiting(STR_METHOD_NAME);
    }

    public void testGetUpMutualFundProductCategoryCode_C0001()
    {
        final String STR_METHOD_NAME = "testGetMutualFundProductC0001()";
        log.entering(STR_METHOD_NAME);

        try
        {
            TestDBUtility.deleteAll(MutualFundProductCategoryParams.TYPE);
            MutualFundProductCategoryParams l_mutualFundProductCategoryParams =
                TestDBUtility.getMutualFundProductCategoryRow();
            l_mutualFundProductCategoryParams.setCategoryCode("13");
            TestDBUtility.insertWithDel(l_mutualFundProductCategoryParams);

            WEB3MutualFundProductManager l_mutualFundProductManager =
                new WEB3MutualFundProductManager();
            l_mutualFundProductManager.getUpMutualFundProductCategoryCode(
                "12", "0D");
            fail();
        }
        catch (WEB3BaseException l_ex)
        {
            assertEquals(WEB3ErrorCatalog.SYSTEM_ERROR_80005, l_ex.getErrorInfo());
        }

        log.exiting(STR_METHOD_NAME);
    }

    public void testGetUpMutualFundProductCategoryCode_C0002()
    {
        final String STR_METHOD_NAME = "testGetMutualFundProductC0002()";
        log.entering(STR_METHOD_NAME);

        try
        {
            TestDBUtility.deleteAll(MutualFundProductCategoryParams.TYPE);
            MutualFundProductCategoryParams l_mutualFundProductCategoryParams =
                TestDBUtility.getMutualFundProductCategoryRow();
            l_mutualFundProductCategoryParams.setCategoryCode("13");
            l_mutualFundProductCategoryParams.setParentCategoryCode(null);
            TestDBUtility.insertWithDel(l_mutualFundProductCategoryParams);

            WEB3MutualFundProductManager l_mutualFundProductManager =
                new WEB3MutualFundProductManager();
            assertEquals(l_mutualFundProductManager.getUpMutualFundProductCategoryCode(
                "13", "0D"), "13");
        }
        catch (WEB3BaseException l_ex)
        {
            log.error(l_ex.getMessage(), l_ex);
            log.exiting(STR_METHOD_NAME);
            fail();
        }

        log.exiting(STR_METHOD_NAME);
    }

    public void testGetUpMutualFundProductCategoryCode_C0003()
    {
        final String STR_METHOD_NAME = "testGetMutualFundProductC0003()";
        log.entering(STR_METHOD_NAME);

        try
        {
            TestDBUtility.deleteAll(MutualFundProductCategoryParams.TYPE);
            MutualFundProductCategoryParams l_mutualFundProductCategoryParams =
                TestDBUtility.getMutualFundProductCategoryRow();
            l_mutualFundProductCategoryParams.setCategoryCode("13");
            l_mutualFundProductCategoryParams.setParentCategoryCode("14");
            TestDBUtility.insertWithDel(l_mutualFundProductCategoryParams);

            WEB3MutualFundProductManager l_mutualFundProductManager =
                new WEB3MutualFundProductManager();
            l_mutualFundProductManager.getUpMutualFundProductCategoryCode(
                "13", "0D");

            fail();
        }
        catch (WEB3BaseException l_ex)
        {
            assertEquals(WEB3ErrorCatalog.SYSTEM_ERROR_80005, l_ex.getErrorInfo());
        }

        log.exiting(STR_METHOD_NAME);
    }

    public void testGetUpMutualFundProductCategoryCode_C0004()
    {
        final String STR_METHOD_NAME = "testGetMutualFundProductC0004()";
        log.entering(STR_METHOD_NAME);

        try
        {
            TestDBUtility.deleteAll(MutualFundProductCategoryParams.TYPE);
            MutualFundProductCategoryParams l_mutualFundProductCategoryParams =
                TestDBUtility.getMutualFundProductCategoryRow();
            l_mutualFundProductCategoryParams.setCategoryCode("13");
            l_mutualFundProductCategoryParams.setParentCategoryCode("14");
            TestDBUtility.insertWithDel(l_mutualFundProductCategoryParams);
            l_mutualFundProductCategoryParams =
                TestDBUtility.getMutualFundProductCategoryRow();
            l_mutualFundProductCategoryParams.setCategoryCode("14");
            l_mutualFundProductCategoryParams.setParentCategoryCode(null);
            TestDBUtility.insertWithDel(l_mutualFundProductCategoryParams);

            WEB3MutualFundProductManager l_mutualFundProductManager =
                new WEB3MutualFundProductManager();
            assertEquals(l_mutualFundProductManager.getUpMutualFundProductCategoryCode(
                "13", "0D"), "14");
        }
        catch (WEB3BaseException l_ex)
        {
            log.error(l_ex.getMessage(), l_ex);
            log.exiting(STR_METHOD_NAME);
            fail();
        }

        log.exiting(STR_METHOD_NAME);
    }

    public void testGetUpMutualFundProductCategoryCode_C0005()
    {
        final String STR_METHOD_NAME = "testGetMutualFundProductC0005()";
        log.entering(STR_METHOD_NAME);

        try
        {
            TestDBUtility.deleteAll(MutualFundProductCategoryParams.TYPE);
            MutualFundProductCategoryParams l_mutualFundProductCategoryParams =
                TestDBUtility.getMutualFundProductCategoryRow();
            l_mutualFundProductCategoryParams.setCategoryCode("13");
            l_mutualFundProductCategoryParams.setParentCategoryCode("14");
            TestDBUtility.insertWithDel(l_mutualFundProductCategoryParams);
            l_mutualFundProductCategoryParams =
                TestDBUtility.getMutualFundProductCategoryRow();
            l_mutualFundProductCategoryParams.setCategoryCode("14");
            l_mutualFundProductCategoryParams.setParentCategoryCode("15");
            TestDBUtility.insertWithDel(l_mutualFundProductCategoryParams);

            WEB3MutualFundProductManager l_mutualFundProductManager =
                new WEB3MutualFundProductManager();
            assertEquals(l_mutualFundProductManager.getUpMutualFundProductCategoryCode(
                "13", "0D"), "15");
        }
        catch (WEB3BaseException l_ex)
        {
            log.error(l_ex.getMessage(), l_ex);
            log.exiting(STR_METHOD_NAME);
            fail();
        }

        log.exiting(STR_METHOD_NAME);
    }

    public void testGetFixedBuyPossibleProductList_C0001()
    {
        final String STR_METHOD_NAME = "testGetFixedBuyPossibleProductList_C0001()";
        log.entering(STR_METHOD_NAME);

        try
        {
            Timestamp l_tmsSystemTimestamp =
                (Timestamp)ThreadLocalSystemAttributesRegistry.getAttribute(
                    TradingSystem.SYSTEM_TIMESTAMP_ATTRIBUTE_NAME);
            String l_strFormatTime = WEB3DateUtility.formatDate(l_tmsSystemTimestamp, "yyyyMMddHHmmss");
            String l_strFormatDate = WEB3DateUtility.formatDate(l_tmsSystemTimestamp, "yyyyMMdd");

            TestDBUtility.deleteAll(MutualFundProductParams.TYPE);
            MutualFundProductParams l_mutualFundProductParams =
                TestDBUtility.getMutualFundProductRow();
            l_mutualFundProductParams.setFixedBuyPossibleDiv(
                WEB3FixedBuyPossibleDivDef.FIXED_BUYING_POSSIBLE);
            l_mutualFundProductParams.setInstitutionCode("0D");
            l_mutualFundProductParams.setProductCode("123");
            l_mutualFundProductParams.setBuyStartDate(
                WEB3DateUtility.getDate(l_strFormatTime,"yyyyMMddHHmmss"));
            l_mutualFundProductParams.setBuyEndDate(
                WEB3DateUtility.getDate(l_strFormatDate,"yyyyMMdd"));
            l_mutualFundProductParams.setCategoryCode("04");
            l_mutualFundProductParams.setProductId(123);
            TestDBUtility.insertWithDel(l_mutualFundProductParams);

            TestDBUtility.deleteAll(ProductParams.TYPE);
            ProductParams l_productParams = TestDBUtility.getProductRow();
            l_productParams.setProductId(123);
            TestDBUtility.insertWithDel(l_productParams);

            TestDBUtility.deleteAll(MutualFundProductCategoryRow.TYPE);
            MutualFundProductCategoryParams l_mutualFundProductCategoryRow =
                TestDBUtility.getMutualFundProductCategoryRow();
            l_mutualFundProductCategoryRow.setCategoryCode("01");
            TestDBUtility.insertWithDel(l_mutualFundProductCategoryRow);
            MutualFundProductCategoryParams l_mutualFundProductCategoryRow1 =
                TestDBUtility.getMutualFundProductCategoryRow();
            l_mutualFundProductCategoryRow1.setCategoryCode("02");
            TestDBUtility.insertWithDel(l_mutualFundProductCategoryRow1);
            MutualFundProductCategoryParams l_mutualFundProductCategoryRow2 =
                TestDBUtility.getMutualFundProductCategoryRow();
            l_mutualFundProductCategoryRow2.setCategoryCode("03");
            TestDBUtility.insertWithDel(l_mutualFundProductCategoryRow2);
            List l_lisCategoryLists = new ArrayList();
            l_lisCategoryLists.add(l_mutualFundProductCategoryRow);
            l_lisCategoryLists.add(l_mutualFundProductCategoryRow1);
            l_lisCategoryLists.add(l_mutualFundProductCategoryRow2);
            WEB3MutualFundProductManager l_mutualFundProductManager =
                new WEB3MutualFundProductManager();
            List l_lisRetuenList =
                l_mutualFundProductManager.getFixedBuyPossibleProductList(
                    "0D", null, l_lisCategoryLists);

            assertEquals(0, l_lisRetuenList.size());
        }
        catch (Exception l_ex)
        {
            log.error(l_ex.getMessage(), l_ex);
            log.exiting(STR_METHOD_NAME);
            fail();
        }

        log.exiting(STR_METHOD_NAME);
    }

    public void testGetFixedBuyPossibleProductList_C0002()
    {
        final String STR_METHOD_NAME = "testGetFixedBuyPossibleProductList_C0002()";
        log.entering(STR_METHOD_NAME);

        try
        {
            Timestamp l_tmsSystemTimestamp =
                (Timestamp)ThreadLocalSystemAttributesRegistry.getAttribute(
                    TradingSystem.SYSTEM_TIMESTAMP_ATTRIBUTE_NAME);
            String l_strFormatTime = WEB3DateUtility.formatDate(l_tmsSystemTimestamp, "yyyyMMddHHmmss");
            String l_strFormatDate = WEB3DateUtility.formatDate(l_tmsSystemTimestamp, "yyyyMMdd");

            TestDBUtility.deleteAll(MutualFundProductParams.TYPE);
            MutualFundProductParams l_mutualFundProductParams =
                TestDBUtility.getMutualFundProductRow();
            l_mutualFundProductParams.setFixedBuyPossibleDiv(
                WEB3FixedBuyPossibleDivDef.FIXED_BUYING_POSSIBLE);
            l_mutualFundProductParams.setInstitutionCode("0D");
            l_mutualFundProductParams.setProductCode("123");
            l_mutualFundProductParams.setBuyStartDate(
                WEB3DateUtility.getDate(l_strFormatTime,"yyyyMMddHHmmss"));
            l_mutualFundProductParams.setBuyEndDate(
                WEB3DateUtility.getDate(l_strFormatDate,"yyyyMMdd"));
            l_mutualFundProductParams.setCategoryCode("01");
            l_mutualFundProductParams.setProductId(123);
            TestDBUtility.insertWithDel(l_mutualFundProductParams);

            TestDBUtility.deleteAll(ProductParams.TYPE);
            ProductParams l_productParams = TestDBUtility.getProductRow();
            l_productParams.setProductId(123);
            TestDBUtility.insertWithDel(l_productParams);

            String[] l_strProductCodes = null;
            TestDBUtility.deleteAll(MutualFundProductCategoryRow.TYPE);
            MutualFundProductCategoryParams l_mutualFundProductCategoryRow =
                TestDBUtility.getMutualFundProductCategoryRow();
            l_mutualFundProductCategoryRow.setCategoryCode("01");
            TestDBUtility.insertWithDel(l_mutualFundProductCategoryRow);
            MutualFundProductCategoryParams l_mutualFundProductCategoryRow1 =
                TestDBUtility.getMutualFundProductCategoryRow();
            l_mutualFundProductCategoryRow1.setCategoryCode("02");
            TestDBUtility.insertWithDel(l_mutualFundProductCategoryRow1);
            MutualFundProductCategoryParams l_mutualFundProductCategoryRow2 =
                TestDBUtility.getMutualFundProductCategoryRow();
            l_mutualFundProductCategoryRow2.setCategoryCode("03");
            TestDBUtility.insertWithDel(l_mutualFundProductCategoryRow2);
            List l_lisCategoryLists = new ArrayList();
            l_lisCategoryLists.add(l_mutualFundProductCategoryRow);
            l_lisCategoryLists.add(l_mutualFundProductCategoryRow1);
            l_lisCategoryLists.add(l_mutualFundProductCategoryRow2);
            WEB3MutualFundProductManager l_mutualFundProductManager =
                new WEB3MutualFundProductManager();
            List l_lisRetuenList =
                l_mutualFundProductManager.getFixedBuyPossibleProductList(
                    "0D", l_strProductCodes, l_lisCategoryLists);

            assertEquals(1, l_lisRetuenList.size());
        }
        catch (Exception l_ex)
        {
            log.error(l_ex.getMessage(), l_ex);
            log.exiting(STR_METHOD_NAME);
            fail();
        }

        log.exiting(STR_METHOD_NAME);
    }

    public void testGetFixedBuyPossibleProductList_C0003()
    {
        final String STR_METHOD_NAME = "testGetFixedBuyPossibleProductList_C0003()";
        log.entering(STR_METHOD_NAME);

        try
        {
            Timestamp l_tmsSystemTimestamp =
                (Timestamp)ThreadLocalSystemAttributesRegistry.getAttribute(
                    TradingSystem.SYSTEM_TIMESTAMP_ATTRIBUTE_NAME);
            String l_strFormatTime = WEB3DateUtility.formatDate(l_tmsSystemTimestamp, "yyyyMMddHHmmss");
            String l_strFormatDate = WEB3DateUtility.formatDate(l_tmsSystemTimestamp, "yyyyMMdd");

            TestDBUtility.deleteAll(MutualFundProductParams.TYPE);
            MutualFundProductParams l_mutualFundProductParams =
                TestDBUtility.getMutualFundProductRow();
            l_mutualFundProductParams.setFixedBuyPossibleDiv(
                WEB3FixedBuyPossibleDivDef.FIXED_BUYING_IMPOSSIBLE);
            l_mutualFundProductParams.setInstitutionCode("0D");
            l_mutualFundProductParams.setProductCode("125");
            l_mutualFundProductParams.setBuyStartDate(
                WEB3DateUtility.getDate(l_strFormatTime,"yyyyMMddHHmmss"));
            l_mutualFundProductParams.setBuyEndDate(
                WEB3DateUtility.getDate(l_strFormatDate,"yyyyMMdd"));
            l_mutualFundProductParams.setCategoryCode("01");
            l_mutualFundProductParams.setProductId(123);
            TestDBUtility.insertWithDel(l_mutualFundProductParams);

            TestDBUtility.deleteAll(ProductParams.TYPE);
            ProductParams l_productParams = TestDBUtility.getProductRow();
            l_productParams.setProductId(123);
            TestDBUtility.insertWithDel(l_productParams);

            String[] l_strProductCodes ={"120", "123", "112"};
            TestDBUtility.deleteAll(MutualFundProductCategoryRow.TYPE);
            MutualFundProductCategoryParams l_mutualFundProductCategoryRow =
                TestDBUtility.getMutualFundProductCategoryRow();
            l_mutualFundProductCategoryRow.setCategoryCode("01");
            TestDBUtility.insertWithDel(l_mutualFundProductCategoryRow);
            MutualFundProductCategoryParams l_mutualFundProductCategoryRow1 =
                TestDBUtility.getMutualFundProductCategoryRow();
            l_mutualFundProductCategoryRow1.setCategoryCode("02");
            TestDBUtility.insertWithDel(l_mutualFundProductCategoryRow1);
            MutualFundProductCategoryParams l_mutualFundProductCategoryRow2 =
                TestDBUtility.getMutualFundProductCategoryRow();
            l_mutualFundProductCategoryRow2.setCategoryCode("03");
            TestDBUtility.insertWithDel(l_mutualFundProductCategoryRow2);
            List l_lisCategoryLists = new ArrayList();
            l_lisCategoryLists.add(l_mutualFundProductCategoryRow);
            l_lisCategoryLists.add(l_mutualFundProductCategoryRow1);
            l_lisCategoryLists.add(l_mutualFundProductCategoryRow2);
            WEB3MutualFundProductManager l_mutualFundProductManager =
                new WEB3MutualFundProductManager();
            List l_lisRetuenList =
                l_mutualFundProductManager.getFixedBuyPossibleProductList(
                    "0D", l_strProductCodes, l_lisCategoryLists);

            assertEquals(0, l_lisRetuenList.size());
        }
        catch (Exception l_ex)
        {
            log.error(l_ex.getMessage(), l_ex);
            log.exiting(STR_METHOD_NAME);
            fail();
        }

        log.exiting(STR_METHOD_NAME);
    }

    public void testGetFixedBuyPossibleProductList_C0004()
    {
        final String STR_METHOD_NAME = "testGetFixedBuyPossibleProductList_C0004()";
        log.entering(STR_METHOD_NAME);

        try
        {
            Timestamp l_tmsSystemTimestamp =
                (Timestamp)ThreadLocalSystemAttributesRegistry.getAttribute(
                    TradingSystem.SYSTEM_TIMESTAMP_ATTRIBUTE_NAME);
            String l_strFormatTime = WEB3DateUtility.formatDate(l_tmsSystemTimestamp, "yyyyMMddHHmmss");
            String l_strFormatDate = WEB3DateUtility.formatDate(l_tmsSystemTimestamp, "yyyyMMdd");

            TestDBUtility.deleteAll(MutualFundProductParams.TYPE);
            MutualFundProductParams l_mutualFundProductParams =
                TestDBUtility.getMutualFundProductRow();
            l_mutualFundProductParams.setFixedBuyPossibleDiv(
                WEB3FixedBuyPossibleDivDef.FIXED_BUYING_POSSIBLE);
            l_mutualFundProductParams.setInstitutionCode("0D");
            l_mutualFundProductParams.setProductCode("123");
            l_mutualFundProductParams.setBuyStartDate(
                WEB3DateUtility.getDate(l_strFormatTime,"yyyyMMddHHmmss"));
            l_mutualFundProductParams.setBuyEndDate(
                WEB3DateUtility.getDate(l_strFormatDate,"yyyyMMdd"));
            l_mutualFundProductParams.setCategoryCode("05");
            l_mutualFundProductParams.setProductId(123);
            TestDBUtility.insertWithDel(l_mutualFundProductParams);

            TestDBUtility.deleteAll(ProductParams.TYPE);
            ProductParams l_productParams = TestDBUtility.getProductRow();
            l_productParams.setProductId(123);
            TestDBUtility.insertWithDel(l_productParams);

            String[] l_strProductCodes ={"120", "123", "112"};
            TestDBUtility.deleteAll(MutualFundProductCategoryRow.TYPE);
            MutualFundProductCategoryParams l_mutualFundProductCategoryRow =
                TestDBUtility.getMutualFundProductCategoryRow();
            l_mutualFundProductCategoryRow.setCategoryCode("01");
            TestDBUtility.insertWithDel(l_mutualFundProductCategoryRow);
            MutualFundProductCategoryParams l_mutualFundProductCategoryRow1 =
                TestDBUtility.getMutualFundProductCategoryRow();
            l_mutualFundProductCategoryRow1.setCategoryCode("02");
            TestDBUtility.insertWithDel(l_mutualFundProductCategoryRow1);
            MutualFundProductCategoryParams l_mutualFundProductCategoryRow2 =
                TestDBUtility.getMutualFundProductCategoryRow();
            l_mutualFundProductCategoryRow2.setCategoryCode("03");
            TestDBUtility.insertWithDel(l_mutualFundProductCategoryRow2);
            List l_lisCategoryLists = new ArrayList();
            l_lisCategoryLists.add(l_mutualFundProductCategoryRow);
            l_lisCategoryLists.add(l_mutualFundProductCategoryRow1);
            l_lisCategoryLists.add(l_mutualFundProductCategoryRow2);
            WEB3MutualFundProductManager l_mutualFundProductManager =
                new WEB3MutualFundProductManager();
            List l_lisRetuenList =
                l_mutualFundProductManager.getFixedBuyPossibleProductList(
                    "0D", l_strProductCodes, l_lisCategoryLists);

            assertEquals(0, l_lisRetuenList.size());
        }
        catch (Exception l_ex)
        {
            log.error(l_ex.getMessage(), l_ex);
            log.exiting(STR_METHOD_NAME);
            fail();
        }

        log.exiting(STR_METHOD_NAME);
    }

    public void testGetFixedBuyPossibleProductList_C0005()
    {
        final String STR_METHOD_NAME = "testGetFixedBuyPossibleProductList_C0005()";
        log.entering(STR_METHOD_NAME);

        try
        {
            Timestamp l_tmsSystemTimestamp =
                (Timestamp)ThreadLocalSystemAttributesRegistry.getAttribute(
                    TradingSystem.SYSTEM_TIMESTAMP_ATTRIBUTE_NAME);
            String l_strFormatTime = WEB3DateUtility.formatDate(l_tmsSystemTimestamp, "yyyyMMddHHmmss");
            String l_strFormatDate = WEB3DateUtility.formatDate(l_tmsSystemTimestamp, "yyyyMMdd");

            TestDBUtility.deleteAll(MutualFundProductParams.TYPE);
            MutualFundProductParams l_mutualFundProductParams =
                TestDBUtility.getMutualFundProductRow();
            l_mutualFundProductParams.setFixedBuyPossibleDiv(
                WEB3FixedBuyPossibleDivDef.FIXED_BUYING_IMPOSSIBLE);
            l_mutualFundProductParams.setInstitutionCode("0D");
            l_mutualFundProductParams.setProductCode("123");
            l_mutualFundProductParams.setBuyStartDate(
                WEB3DateUtility.getDate(l_strFormatTime,"yyyyMMddHHmmss"));
            l_mutualFundProductParams.setBuyEndDate(
                WEB3DateUtility.getDate(l_strFormatDate,"yyyyMMdd"));
            l_mutualFundProductParams.setCategoryCode("01");
            l_mutualFundProductParams.setProductId(123);
            TestDBUtility.insertWithDel(l_mutualFundProductParams);

            TestDBUtility.deleteAll(ProductParams.TYPE);
            ProductParams l_productParams = TestDBUtility.getProductRow();
            l_productParams.setProductId(123);
            TestDBUtility.insertWithDel(l_productParams);

            String[] l_strProductCodes ={"120", "123", "112"};
            List l_lisCategoryLists = null;
            WEB3MutualFundProductManager l_mutualFundProductManager =
                new WEB3MutualFundProductManager();
            List l_lisRetuenList =
                l_mutualFundProductManager.getFixedBuyPossibleProductList(
                    "0D", l_strProductCodes, l_lisCategoryLists);

            assertEquals(0, l_lisRetuenList.size());
        }
        catch (Exception l_ex)
        {
            log.error(l_ex.getMessage(), l_ex);
            log.exiting(STR_METHOD_NAME);
            fail();
        }

        log.exiting(STR_METHOD_NAME);
    }

    public void testGetFixedBuyPossibleProductList_C0006()
    {
        final String STR_METHOD_NAME = "testGetFixedBuyPossibleProductList_C0006()";
        log.entering(STR_METHOD_NAME);

        try
        {
            Timestamp l_tmsSystemTimestamp =
                (Timestamp)ThreadLocalSystemAttributesRegistry.getAttribute(
                    TradingSystem.SYSTEM_TIMESTAMP_ATTRIBUTE_NAME);
            String l_strFormatTime = WEB3DateUtility.formatDate(l_tmsSystemTimestamp, "yyyyMMddHHmmss");
            String l_strFormatDate = WEB3DateUtility.formatDate(l_tmsSystemTimestamp, "yyyyMMdd");

            TestDBUtility.deleteAll(MutualFundProductParams.TYPE);
            MutualFundProductParams l_mutualFundProductParams =
                TestDBUtility.getMutualFundProductRow();
            l_mutualFundProductParams.setFixedBuyPossibleDiv(
                WEB3FixedBuyPossibleDivDef.FIXED_BUYING_POSSIBLE);
            l_mutualFundProductParams.setInstitutionCode("0D");
            l_mutualFundProductParams.setProductCode("123");
            l_mutualFundProductParams.setBuyStartDate(
                WEB3DateUtility.getDate(l_strFormatTime,"yyyyMMddHHmmss"));
            l_mutualFundProductParams.setBuyEndDate(
                WEB3DateUtility.getDate(l_strFormatDate,"yyyyMMdd"));
            l_mutualFundProductParams.setCategoryCode("01");
            l_mutualFundProductParams.setProductId(123);
            TestDBUtility.insertWithDel(l_mutualFundProductParams);

            TestDBUtility.deleteAll(ProductParams.TYPE);
            ProductParams l_productParams = TestDBUtility.getProductRow();
            l_productParams.setProductId(123);
            TestDBUtility.insertWithDel(l_productParams);

            String[] l_strProductCodes ={"120", "123", "112"};
            TestDBUtility.deleteAll(MutualFundProductCategoryRow.TYPE);
            MutualFundProductCategoryParams l_mutualFundProductCategoryRow =
                TestDBUtility.getMutualFundProductCategoryRow();
            l_mutualFundProductCategoryRow.setCategoryCode("01");
            TestDBUtility.insertWithDel(l_mutualFundProductCategoryRow);
            MutualFundProductCategoryParams l_mutualFundProductCategoryRow1 =
                TestDBUtility.getMutualFundProductCategoryRow();
            l_mutualFundProductCategoryRow1.setCategoryCode("02");
            TestDBUtility.insertWithDel(l_mutualFundProductCategoryRow1);
            MutualFundProductCategoryParams l_mutualFundProductCategoryRow2 =
                TestDBUtility.getMutualFundProductCategoryRow();
            l_mutualFundProductCategoryRow2.setCategoryCode("03");
            TestDBUtility.insertWithDel(l_mutualFundProductCategoryRow2);
            List l_lisCategoryLists = new ArrayList();
            l_lisCategoryLists.add(l_mutualFundProductCategoryRow);
            l_lisCategoryLists.add(l_mutualFundProductCategoryRow1);
            l_lisCategoryLists.add(l_mutualFundProductCategoryRow2);
            WEB3MutualFundProductManager l_mutualFundProductManager =
                new WEB3MutualFundProductManager();
            List l_lisRetuenList =
                l_mutualFundProductManager.getFixedBuyPossibleProductList(
                    "0D", l_strProductCodes, l_lisCategoryLists);

            assertEquals(1, l_lisRetuenList.size());
        }
        catch (Exception l_ex)
        {
            log.error(l_ex.getMessage(), l_ex);
            log.exiting(STR_METHOD_NAME);
            fail();
        }

        log.exiting(STR_METHOD_NAME);
    }
}
@
