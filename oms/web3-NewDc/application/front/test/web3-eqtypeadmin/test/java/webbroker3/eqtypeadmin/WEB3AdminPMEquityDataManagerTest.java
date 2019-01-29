head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.04.07.02.28.04;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	5444d9d078c63f4;
filename	WEB3AdminPMEquityDataManagerTest.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : //TODO(WEB3EqtypeAdminAppPluginTest.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2007/04/28 武波(中訊) 新規作成
*/
package webbroker3.eqtypeadmin;

import java.sql.Timestamp;
import java.util.List;

import com.fitechlabs.xtrade.kernel.boot.Services;
import com.fitechlabs.xtrade.kernel.util.ThreadLocalSystemAttributesRegistry;
import com.fitechlabs.xtrade.plugin.tc.eqtype.EqTypeExecutionConditionType;
import com.fitechlabs.xtrade.plugin.tc.eqtype.EqTypeOrderUnit;
import com.fitechlabs.xtrade.plugin.tc.eqtype.data.EqtypeClosingContractSpecParams;
import com.fitechlabs.xtrade.plugin.tc.eqtype.data.EqtypeContractParams;
import com.fitechlabs.xtrade.plugin.tc.eqtype.data.EqtypeOrderUnitParams;
import com.fitechlabs.xtrade.plugin.tc.eqtype.data.EqtypeOrderUnitRow;
import com.fitechlabs.xtrade.plugin.tc.eqtype.data.EqtypeProductParams;
import com.fitechlabs.xtrade.plugin.tc.eqtype.data.EqtypeProductRow;
import com.fitechlabs.xtrade.plugin.tc.eqtype.data.EqtypeTradedProductParams;
import com.fitechlabs.xtrade.plugin.tc.eqtype.stdimpls.EqTypeOrderUnitImpl;
import com.fitechlabs.xtrade.plugin.tc.gentrade.Branch;
import com.fitechlabs.xtrade.plugin.tc.gentrade.ContractTypeEnum;
import com.fitechlabs.xtrade.plugin.tc.gentrade.FinApp;
import com.fitechlabs.xtrade.plugin.tc.gentrade.OrderCategEnum;
import com.fitechlabs.xtrade.plugin.tc.gentrade.OrderExpirationStatusEnum;
import com.fitechlabs.xtrade.plugin.tc.gentrade.OrderOpenStatusEnum;
import com.fitechlabs.xtrade.plugin.tc.gentrade.OrderStatusEnum;
import com.fitechlabs.xtrade.plugin.tc.gentrade.OrderTypeEnum;
import com.fitechlabs.xtrade.plugin.tc.gentrade.ProductTypeEnum;
import com.fitechlabs.xtrade.plugin.tc.gentrade.TaxTypeEnum;
import com.fitechlabs.xtrade.plugin.tc.gentrade.TradingModule;
import com.fitechlabs.xtrade.plugin.tc.gentrade.data.BranchParams;
import com.fitechlabs.xtrade.plugin.tc.gentrade.data.BranchRow;
import com.fitechlabs.xtrade.plugin.tc.gentrade.data.MainAccountParams;
import com.fitechlabs.xtrade.plugin.tc.gentrade.data.MainAccountRow;
import com.fitechlabs.xtrade.plugin.tc.gentrade.data.MarketParams;
import com.fitechlabs.xtrade.plugin.tc.gentrade.data.MarketRow;
import com.fitechlabs.xtrade.plugin.tc.gentrade.data.ProductParams;
import com.fitechlabs.xtrade.plugin.tc.gentrade.data.ProductRow;
import com.fitechlabs.xtrade.plugin.tc.gentrade.data.TradedProductParams;
import com.fitechlabs.xtrade.plugin.tc.gentrade.stdimpls.BranchImpl;

import test.util.TestDBUtility;

import webbroker3.common.WEB3BaseException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.common.define.WEB3ErrorReasonCodeDef;
import webbroker3.eqtypeadmin.data.AdminEqForcedSettleOrderParams;
import webbroker3.eqtypeadmin.message.WEB3AdminEquityPTSExecHistory;
import webbroker3.eqtypeadmin.message.WEB3AdminEquityPTSOrderDetailUnit;
import webbroker3.eqtypeadmin.message.WEB3AdminForcedSettleReasonUnit;
import webbroker3.eqtypeadmin.message.WEB3AdminForcedSettleTemporaryOrderUnit;
import webbroker3.equity.WEB3EquityOrderManager;
import webbroker3.equity.WEB3EquityPTSTradingTimeManagement;
import webbroker3.equity.WEB3EquityTradedProduct;
import webbroker3.equity.data.EquityLimitPriceRangeMstParams;
import webbroker3.equity.data.EquityTickValuesMstParams;
import webbroker3.equity.data.HostEquityOrderExecNotifyParams;
import webbroker3.equity.data.HostEquityOrderExecNotifyRow;
import webbroker3.gentrade.WEB3GentradeTradingClendarContext;
import webbroker3.gentrade.WEB3GentradeTradingTimeManagementForMock;
import webbroker3.gentrade.data.BranchPreferencesParams;
import webbroker3.gentrade.data.BranchPreferencesRow;
import webbroker3.gentrade.data.PtsOrderexecutionEndParams;
import webbroker3.gentrade.data.PtsOrderexecutionEndRow;
import webbroker3.gentrade.data.TradingTimeRow;
import webbroker3.mock.TestBaseForMock;
import webbroker3.triggerorder.base.data.RsvEqClosingContractSpecParams;
import webbroker3.triggerorder.base.data.RsvEqOrderUnitParams;
import webbroker3.triggerorder.base.data.RsvEqOrderUnitRow;
import webbroker3.util.WEB3DateUtility;
import webbroker3.util.WEB3LogUtility;

/**
 * 商品管理（株式）データマネージャのテスト<BR>
 * @@author 武波(中訊)
 * @@version 1.0
 */
public class WEB3AdminPMEquityDataManagerTest
    extends TestBaseForMock
{
    /**
     * Log Variable.<BR>
     */
    private static WEB3LogUtility log =
        WEB3LogUtility.getInstance(WEB3AdminPMEquityDataManagerTest.class);

    public WEB3AdminPMEquityDataManagerTest(String name)
    {
        super(name);
        // TODO Auto-generated constructor stub
    }

    protected void setUp() throws Exception
    {
        super.setUp();
    }

    protected void tearDown() throws Exception
    {
        super.tearDown();
    }
//
//    /*
//     * 注文単位Rowを取得不到的場合<br>
//     * throw 異常<br>
//     */
//    
//    public void test_isApproveProcessObjectOrder_C0001()
//    {
//        final String STR_METHOD_NAME = "test_isApproveDealObjectOrder_C0001()";
//        log.entering(TEST_START + STR_METHOD_NAME);
//        try
//        {
//            EqtypeOrderUnitParams l_orderUnitParams = null;
//            WEB3AdminPMEquityDataManager.isApproveProcessObjectOrder(l_orderUnitParams);
//            fail();
//        }
//        catch (WEB3BaseException l_web3SystemException)
//        {
//            assertEquals(WEB3ErrorCatalog.SYSTEM_ERROR_80017,l_web3SystemException.getErrorInfo());
//        }
//        catch (Exception l_ex)
//        {
//            log.error(STR_METHOD_NAME, l_ex);
//            fail();
//        }
//        log.exiting(TEST_END + STR_METHOD_NAME);
//    }
//
//    /*
//     * 注文単位Rowを取得的場合
//     *パラメータ.注文単位Row.承認状態区分 != "未承認" <br>
//     *パラメータ.注文単位Row.注文有効状態 != "クローズ"<br>
//     *return false<br>
//     */
//    public void test_isApproveProcessObjectOrder_C0002()
//    {
//        final String STR_METHOD_NAME = "test_isApproveProcessObjectOrder_C0002()";
//        log.entering(TEST_START + STR_METHOD_NAME);
//        try
//        {
//            TestDBUtility.deleteAll(EqtypeOrderUnitParams.TYPE);
//            EqtypeOrderUnitParams l_eqtypeOrderUnitParams = TestDBUtility.getEqtypeOrderUnitRow();
//            l_eqtypeOrderUnitParams.setApproveStatusType("1");
//
//            boolean l_blnApproveDealObjectOrde = WEB3AdminPMEquityDataManager.isApproveProcessObjectOrder(l_eqtypeOrderUnitParams);
//            assertFalse(l_blnApproveDealObjectOrde);
//        }
//        catch (Exception l_ex)
//        {
//            log.error(STR_METHOD_NAME, l_ex);
//            fail();
//        }
//        log.exiting(TEST_END + STR_METHOD_NAME);
//    }
//
//    /*
//     * 注文単位Rowを取得的場合<br>
//     * パラメータ.注文単位Row.承認状態区分 == "未承認" <br>
//     * パラメータ.注文単位Row.注文有効状態 == "クローズ" <br>
//     * return false<br>
//     */
//    public void test_isApproveProcessObjectOrder_C0003()
//    {
//        final String STR_METHOD_NAME = "test_isApproveProcessObjectOrder_C0003()";
//        log.entering(TEST_START + STR_METHOD_NAME);
//        try
//        {
//            TestDBUtility.deleteAll(EqtypeOrderUnitParams.TYPE);
//            EqtypeOrderUnitParams l_orderUnitParam = TestDBUtility.getEqtypeOrderUnitRow();
//            l_orderUnitParam.setApproveStatusType("0");
//            l_orderUnitParam.setOrderOpenStatus(OrderOpenStatusEnum.CLOSED);
//
//            boolean l_blnApproveDealObjectOrde = WEB3AdminPMEquityDataManager.isApproveProcessObjectOrder(l_orderUnitParam);
//            assertFalse(l_blnApproveDealObjectOrde);
//        }
//        catch (Exception l_ex)
//        {
//            log.error(STR_METHOD_NAME, l_ex);
//            fail();
//        }
//        log.exiting(TEST_END + STR_METHOD_NAME);
//    }
//
//    /*
//     * 注文単位Rowを取得的場合<br>
//     * パラメータ.注文単位Row.承認状態区分 == "未承認"<br>
//     * パラメータ.注文単位Row.注文有効状態 == "クローズ" <br>
//     * 取引時間管理.get発注日(パラメータ.注文単位Row.発注日)が例外をスロー。 <br>
//     * return false<br>
//     */
//    public void test_isApproveProcessObjectOrder_C0004()
//    {
//        final String STR_METHOD_NAME = "test_isApproveProcessObjectOrder_C0004()";
//        log.entering(TEST_START + STR_METHOD_NAME);
//        try
//        {
//            TestDBUtility.deleteAll(EqtypeOrderUnitParams.TYPE);
//            EqtypeOrderUnitParams l_orderUnitParam = TestDBUtility.getEqtypeOrderUnitRow();
//            l_orderUnitParam.setApproveStatusType("0");
//            l_orderUnitParam.setOrderOpenStatus(OrderOpenStatusEnum.OPEN);
//            l_orderUnitParam.setBizDate("20070426");
//
//            boolean l_blnApproveDealObjectOrde = WEB3AdminPMEquityDataManager.isApproveProcessObjectOrder(l_orderUnitParam);
//            assertFalse(l_blnApproveDealObjectOrde);
//        }
//        catch (Exception l_ex)
//        {
//            log.error(STR_METHOD_NAME, l_ex);
//            fail();
//        }
//        log.exiting(TEST_END + STR_METHOD_NAME);
//    }
//
//    /*
//     * 注文単位Rowを取得的場合<br>
//     * パラメータ.注文単位Row.承認状態区分 == "未承認"<br>
//     * パラメータ.注文単位Row.注文有効状態 == "クローズ" <br>
//     * return true <br>
//     * 
//     */
//    public void test_isApproveProcessObjectOrder_C0005()
//    {
//        final String STR_METHOD_NAME = "test_isApproveProcessObjectOrder_C0005()";
//        log.entering(TEST_START + STR_METHOD_NAME);
//        try
//        {
//            TestDBUtility.deleteAll(EqtypeOrderUnitParams.TYPE);
//            EqtypeOrderUnitParams l_orderUnitParam = TestDBUtility.getEqtypeOrderUnitRow();
//            l_orderUnitParam.setBizDate("20070427");
//            Date l_bizDate = WEB3DateUtility.getDate(l_orderUnitParam.getBizDate(), "yyyyMMdd");
//            WEB3GentradeTradingTimeManagementForMock.mockGetOrderBizDate(l_bizDate);
//            l_orderUnitParam.setApproveStatusType("0");
//            l_orderUnitParam.setOrderOpenStatus(OrderOpenStatusEnum.OPEN);
//
//            boolean l_blnApproveDealObjectOrde = WEB3AdminPMEquityDataManager.isApproveProcessObjectOrder(l_orderUnitParam);
//            assertTrue(l_blnApproveDealObjectOrde);
//        }
//        catch (Exception l_ex)
//        {
//            log.error(STR_METHOD_NAME, l_ex);
//            fail();
//        }
//        log.exiting(TEST_END + STR_METHOD_NAME);
//    }

    /*
     * 取引銘柄 を取得不到的場合l<br>
     * throw 異常<br>
     */
    public void test_getForcedSettleHighPriceRange_C0006()
    {
        final String STR_METHOD_NAME = "test_getForcedSettleHighPriceRange_C0006()";
        log.entering(TEST_START + STR_METHOD_NAME);
        try
        {
            WEB3EquityTradedProduct l_tradedProduct = null;
            WEB3AdminPMEquityDataManager.getForcedSettleHighPriceRange(l_tradedProduct);
            fail();
        }
        catch (WEB3BaseException l_web3SystemException)
        {
            assertEquals(WEB3ErrorCatalog.SYSTEM_ERROR_80017,l_web3SystemException.getErrorInfo());
        }
        catch (Exception l_ex)
        {
            log.error(STR_METHOD_NAME, l_ex);
            fail();
        }
        log.exiting(TEST_END + STR_METHOD_NAME);
    }

    /*
     * 取引銘柄 を取得場合<br>
     * 基準値取得 == 260<br>
     * 値幅取得 == 100<br>
     * throw 異常<br>
     */
    public void test_getForcedSettleHighPriceRange_C0007()
    {
        final String STR_METHOD_NAME = "test_getForcedSettleHighPriceRange_C0007()";
        log.entering(TEST_START + STR_METHOD_NAME);
        try
        {

            TestDBUtility.deleteAll(EquityLimitPriceRangeMstParams.TYPE);
            EquityLimitPriceRangeMstParams l_equityLimitPriceRangeMstParams =
                TestDBUtility.getEquityLimitPriceRangeMstRow();
            l_equityLimitPriceRangeMstParams.setMarketCode("1");
            l_equityLimitPriceRangeMstParams.setLowPrice(200);
            l_equityLimitPriceRangeMstParams.setCapPrice(350);
            l_equityLimitPriceRangeMstParams.setRange(100);
            l_equityLimitPriceRangeMstParams.setCreatedTimestamp(WEB3DateUtility.getDate("20070101","yyyyMMdd"));
            l_equityLimitPriceRangeMstParams.setLastUpdatedTimestamp(WEB3DateUtility.getDate("20070101","yyyyMMdd"));
            TestDBUtility.insertWithDel(l_equityLimitPriceRangeMstParams);

            TestDBUtility.deleteAll(TradedProductParams.TYPE);
            TradedProductParams l_tradedProductParams = TestDBUtility.getTradedProductRow();
            l_tradedProductParams.setMarketId(1L);
            l_tradedProductParams.setProductId(3304148080000L);
            TestDBUtility.insertWithDel(l_tradedProductParams);

            TestDBUtility.deleteAll(ProductParams.TYPE);
            ProductParams l_productParams = TestDBUtility.getProductRow();
            l_productParams.setProductId(3304148080000L);
            TestDBUtility.insertWithDel(l_productParams);

            TestDBUtility.deleteAll(MarketParams.TYPE);
            MarketParams l_marketParams = TestDBUtility.getMarketRow();
            l_marketParams.setMarketId(1L);
            TestDBUtility.insertWithDel(l_marketParams);

            TestDBUtility.deleteAll(EqtypeTradedProductParams.TYPE);
            EqtypeTradedProductParams l_eqtypeTradedProductParams =
                TestDBUtility.getEqtypeTradedProductRow();
            l_eqtypeTradedProductParams.setProductId(3304148080000L);
            l_eqtypeTradedProductParams.setLastClosingPrice(260L);
            TestDBUtility.insertWithDel(l_eqtypeTradedProductParams);

            TestDBUtility.deleteAll(EqtypeProductParams.TYPE);
            EqtypeProductParams l_eqtypeProductParams = TestDBUtility.getEqtypeProductRow();
            l_eqtypeProductParams.setProductId(3304148080000L);
            TestDBUtility.insertWithDel(l_eqtypeProductParams);

            TestDBUtility.deleteAll(EquityTickValuesMstParams.TYPE);
            EquityTickValuesMstParams l_equityTickValuesMstParams =
                TestDBUtility.getEquityTickValuesMstRow();
            l_equityTickValuesMstParams.setMarketCode("1");
            l_equityTickValuesMstParams.setLowPrice(200);
            l_equityTickValuesMstParams.setCapPrice(350);
            l_equityTickValuesMstParams.setTickValue(100);
            l_equityTickValuesMstParams.setCreatedTimestamp(WEB3DateUtility.getDate("20070101","yyyyMMdd"));
            l_equityTickValuesMstParams.setLastUpdatedTimestamp(WEB3DateUtility.getDate("20070101","yyyyMMdd"));
            TestDBUtility.insertWithDel(l_equityTickValuesMstParams);

            WEB3EquityTradedProduct l_tradedProduct = new WEB3EquityTradedProduct(l_tradedProductParams);

            WEB3AdminPMEquityDataManager.getForcedSettleHighPriceRange(l_tradedProduct);
            fail();
        }
        catch (WEB3BaseException l_web3SystemException)
        {
            assertEquals(WEB3ErrorCatalog.SYSTEM_ERROR_80005,l_web3SystemException.getErrorInfo());
        }
        catch (Exception l_ex)
        {
            log.error(STR_METHOD_NAME, l_ex);
            fail();
        }
        log.exiting(TEST_END + STR_METHOD_NAME);
    }

    /*
     * 取引銘柄 を取得場合<br>
     * 基準値取得 == 240<br>
     * 値幅取得 == 100<br>
     * return 400<br>
     */
    public void test_getForcedSettleHighPriceRange_C0008()
    {
        final String STR_METHOD_NAME = "test_getForcedSettleHighPriceRange_C0008()";
        log.entering(TEST_START + STR_METHOD_NAME);
        try
        {
            TestDBUtility.deleteAll(TradedProductParams.TYPE);
            TradedProductParams l_tradedProductParams = TestDBUtility.getTradedProductRow();
            l_tradedProductParams.setProductId(3304148080000L);
            l_tradedProductParams.setMarketId(1L);
            TestDBUtility.insertWithDel(l_tradedProductParams);
            
            TestDBUtility.deleteAll(MarketParams.TYPE);
            MarketParams l_marketParams = TestDBUtility.getMarketRow();
            l_marketParams.setMarketId(1L);
            TestDBUtility.insertWithDel(l_marketParams);

            TestDBUtility.deleteAll(ProductParams.TYPE);
            ProductParams l_productParams = TestDBUtility.getProductRow();
            l_productParams.setProductId(3304148080000L);
            TestDBUtility.insertWithDel(l_productParams);

            TestDBUtility.deleteAll(EqtypeTradedProductParams.TYPE);
            EqtypeTradedProductParams l_eqtypeTradedProductParams =
                TestDBUtility.getEqtypeTradedProductRow();
            l_eqtypeTradedProductParams.setProductId(3304148080000L);
            l_eqtypeTradedProductParams.setLastClosingPrice(240L);
            TestDBUtility.insertWithDel(l_eqtypeTradedProductParams);

            TestDBUtility.deleteAll(EqtypeProductParams.TYPE);
            EqtypeProductParams l_eqtypeProductParams = TestDBUtility.getEqtypeProductRow();
            l_eqtypeProductParams.setProductId(3304148080000L);
            TestDBUtility.insertWithDel(l_eqtypeProductParams);

            TestDBUtility.deleteAll(EquityLimitPriceRangeMstParams.TYPE);
            EquityLimitPriceRangeMstParams l_equityLimitPriceRangeMstParams =
                TestDBUtility.getEquityLimitPriceRangeMstRow();
            l_equityLimitPriceRangeMstParams.setMarketCode("1");
            l_equityLimitPriceRangeMstParams.setLowPrice(200);
            l_equityLimitPriceRangeMstParams.setCapPrice(350);
            l_equityLimitPriceRangeMstParams.setRange(100);
            l_equityLimitPriceRangeMstParams.setCreatedTimestamp(WEB3DateUtility.getDate("20070101","yyyyMMdd"));
            l_equityLimitPriceRangeMstParams.setLastUpdatedTimestamp(WEB3DateUtility.getDate("20070101","yyyyMMdd"));
            TestDBUtility.insertWithDel(l_equityLimitPriceRangeMstParams);

            TestDBUtility.deleteAll(EquityTickValuesMstParams.TYPE);
            EquityTickValuesMstParams l_equityTickValuesMstParams =
                TestDBUtility.getEquityTickValuesMstRow();
            l_equityTickValuesMstParams.setMarketCode("1");
            l_equityTickValuesMstParams.setLowPrice(200);
            l_equityTickValuesMstParams.setCapPrice(350);
            l_equityTickValuesMstParams.setTickValue(100);
            l_equityTickValuesMstParams.setCreatedTimestamp(WEB3DateUtility.getDate("20070101","yyyyMMdd"));
            l_equityTickValuesMstParams.setLastUpdatedTimestamp(WEB3DateUtility.getDate("20070101","yyyyMMdd"));
            TestDBUtility.insertWithDel(l_equityTickValuesMstParams);

            WEB3EquityTradedProduct l_tradedProduct = new WEB3EquityTradedProduct(l_tradedProductParams);
            
            double l_dblForcedSettleHighPriceRange =
                WEB3AdminPMEquityDataManager.getForcedSettleHighPriceRange(l_tradedProduct);
            assertEquals(400D,l_dblForcedSettleHighPriceRange,0);

        }
        catch (Exception l_ex)
        {
            log.error(STR_METHOD_NAME, l_ex);
            fail();
        }
        log.exiting(TEST_END + STR_METHOD_NAME);
    }

    /*
     *取引銘柄 を取得不到的場合<br>
     * throw 異常<br>
     */
    public void test_getForcedSettleLowPriceRang_C0009()
    {
        final String STR_METHOD_NAME = "test_getForcedSettleLowPriceRang_C0009()";
        log.entering(TEST_START + STR_METHOD_NAME);
        try
        {
            WEB3EquityTradedProduct l_tradedProduct = null;
            WEB3AdminPMEquityDataManager.getForcedSettleLowPriceRange(l_tradedProduct);
            fail();
        }
        catch (WEB3BaseException l_web3SystemException)
        {
            assertEquals(WEB3ErrorCatalog.SYSTEM_ERROR_80017,l_web3SystemException.getErrorInfo());
        }
        catch (Exception l_ex)
        {
            log.error(STR_METHOD_NAME, l_ex);
            fail();
        }
        log.exiting(TEST_END + STR_METHOD_NAME);
    }

    /*
     * 取引銘柄 を取得場合<br>
     * 基準値 == 240<br>
     * 値幅 == 260<br>
     * return 1<br>
     */
    public void test_getForcedSettleLowPriceRange_C0010()
    {
        final String STR_METHOD_NAME = "test_getForcedSettleHighPriceRange_C0010()";
        log.entering(TEST_START + STR_METHOD_NAME);
        try
        {
            TestDBUtility.deleteAll(TradedProductParams.TYPE);
            TradedProductParams l_tradedProductParams = TestDBUtility.getTradedProductRow();
            l_tradedProductParams.setProductId(3304148080000L);
            l_tradedProductParams.setMarketId(1L);
            TestDBUtility.insertWithDel(l_tradedProductParams);

            TestDBUtility.deleteAll(ProductParams.TYPE);
            ProductParams l_productParams = TestDBUtility.getProductRow();
            l_productParams.setProductId(3304148080000L);
            TestDBUtility.insertWithDel(l_productParams);

            TestDBUtility.deleteAll(MarketParams.TYPE);
            MarketParams l_marketParams = TestDBUtility.getMarketRow();
            l_marketParams.setMarketId(1L);
            TestDBUtility.insertWithDel(l_marketParams);

            TestDBUtility.deleteAll(EqtypeTradedProductParams.TYPE);
            EqtypeTradedProductParams l_eqtypeTradedProductParams =
                TestDBUtility.getEqtypeTradedProductRow();
            l_eqtypeTradedProductParams.setProductId(3304148080000L);
            l_eqtypeTradedProductParams.setLastClosingPrice(240L);
            TestDBUtility.insertWithDel(l_eqtypeTradedProductParams);

            TestDBUtility.deleteAll(EqtypeProductParams.TYPE);
            EqtypeProductParams l_eqtypeProductParams = TestDBUtility.getEqtypeProductRow();
            l_eqtypeProductParams.setProductId(3304148080000L);
            TestDBUtility.insertWithDel(l_eqtypeProductParams);

            TestDBUtility.deleteAll(EquityLimitPriceRangeMstParams.TYPE);
            EquityLimitPriceRangeMstParams l_equityLimitPriceRangeMstParams =
                TestDBUtility.getEquityLimitPriceRangeMstRow();
            l_equityLimitPriceRangeMstParams.setMarketCode("1");
            l_equityLimitPriceRangeMstParams.setLowPrice(200);
            l_equityLimitPriceRangeMstParams.setCapPrice(350);
            l_equityLimitPriceRangeMstParams.setCreatedTimestamp(WEB3DateUtility.getDate("20070101","yyyyMMdd"));
            l_equityLimitPriceRangeMstParams.setLastUpdatedTimestamp(WEB3DateUtility.getDate("20070101","yyyyMMdd"));
            l_equityLimitPriceRangeMstParams.setRange(260D);
            TestDBUtility.insertWithDel(l_equityLimitPriceRangeMstParams);

            TestDBUtility.deleteAll(EquityTickValuesMstParams.TYPE);
            EquityTickValuesMstParams l_equityTickValuesMstParams =
                TestDBUtility.getEquityTickValuesMstRow();
            l_equityTickValuesMstParams.setMarketCode("1");
            l_equityTickValuesMstParams.setLowPrice(200);
            l_equityTickValuesMstParams.setCapPrice(350);
            l_equityTickValuesMstParams.setTickValue(100);
            l_equityTickValuesMstParams.setCreatedTimestamp(WEB3DateUtility.getDate("20070101","yyyyMMdd"));
            l_equityTickValuesMstParams.setLastUpdatedTimestamp(WEB3DateUtility.getDate("20070101","yyyyMMdd"));
            TestDBUtility.insertWithDel(l_equityTickValuesMstParams);

            WEB3EquityTradedProduct l_tradedProduct = new WEB3EquityTradedProduct(l_tradedProductParams);

            double l_dbLowPriceRange = WEB3AdminPMEquityDataManager.getForcedSettleLowPriceRange(l_tradedProduct);
            assertEquals(1D,l_dbLowPriceRange,0);
        }
        catch (Exception l_ex)
        {
            log.error(STR_METHOD_NAME, l_ex);
            fail();
        }
        log.exiting(TEST_END + STR_METHOD_NAME);
    }

    /*
     * 取引銘柄 を取得場合<br>
     * 基準値 == 240<br>
     * 値幅 == 100<br>
     * return 140<br>
     */
    public void test_getForcedSettleLowPriceRange_C0011()
    {
        final String STR_METHOD_NAME = "test_getForcedSettleHighPriceRange_C0011()";
        log.entering(TEST_START + STR_METHOD_NAME);
        try
        {
            TestDBUtility.deleteAll(MarketParams.TYPE);
            MarketParams l_marketParams = TestDBUtility.getMarketRow();
            l_marketParams.setMarketId(1L);
            TestDBUtility.insertWithDel(l_marketParams);

            TestDBUtility.deleteAll(TradedProductParams.TYPE);
            TradedProductParams l_tradedProductParams = TestDBUtility.getTradedProductRow();
            l_tradedProductParams.setProductId(3304148080000L);
            l_tradedProductParams.setMarketId(1L);
            TestDBUtility.insertWithDel(l_tradedProductParams);

            TestDBUtility.deleteAll(ProductParams.TYPE);
            ProductParams l_productParams = TestDBUtility.getProductRow();
            l_productParams.setProductId(3304148080000L);
            TestDBUtility.insertWithDel(l_productParams);

            TestDBUtility.deleteAll(EqtypeTradedProductParams.TYPE);
            EqtypeTradedProductParams l_eqtypeTradedProductParams =
                TestDBUtility.getEqtypeTradedProductRow();
            l_eqtypeTradedProductParams.setProductId(3304148080000L);
            l_eqtypeTradedProductParams.setLastClosingPrice(240L);
            TestDBUtility.insertWithDel(l_eqtypeTradedProductParams);

            TestDBUtility.deleteAll(EqtypeProductParams.TYPE);
            EqtypeProductParams l_eqtypeProductParams = TestDBUtility.getEqtypeProductRow();
            l_eqtypeProductParams.setProductId(3304148080000L);
            TestDBUtility.insertWithDel(l_eqtypeProductParams);

            TestDBUtility.deleteAll(EquityLimitPriceRangeMstParams.TYPE);
            EquityLimitPriceRangeMstParams l_equityLimitPriceRangeMstParams =
                TestDBUtility.getEquityLimitPriceRangeMstRow();
            l_equityLimitPriceRangeMstParams.setMarketCode("1");
            l_equityLimitPriceRangeMstParams.setLowPrice(200);
            l_equityLimitPriceRangeMstParams.setCapPrice(350);
            l_equityLimitPriceRangeMstParams.setRange(100);
            l_equityLimitPriceRangeMstParams.setCreatedTimestamp(WEB3DateUtility.getDate("20070101","yyyyMMdd"));
            l_equityLimitPriceRangeMstParams.setLastUpdatedTimestamp(WEB3DateUtility.getDate("20070101","yyyyMMdd"));
            TestDBUtility.insertWithDel(l_equityLimitPriceRangeMstParams);

            TestDBUtility.deleteAll(EquityTickValuesMstParams.TYPE);
            EquityTickValuesMstParams l_equityTickValuesMstParams =
                TestDBUtility.getEquityTickValuesMstRow();
            l_equityTickValuesMstParams.setMarketCode("1");
            l_equityTickValuesMstParams.setLowPrice(200);
            l_equityTickValuesMstParams.setCapPrice(350);
            l_equityTickValuesMstParams.setTickValue(100);
            l_equityTickValuesMstParams.setCreatedTimestamp(WEB3DateUtility.getDate("20070101","yyyyMMdd"));
            l_equityTickValuesMstParams.setLastUpdatedTimestamp(WEB3DateUtility.getDate("20070101","yyyyMMdd"));
            TestDBUtility.insertWithDel(l_equityTickValuesMstParams);

            WEB3EquityTradedProduct l_tradedProduct = new WEB3EquityTradedProduct(l_tradedProductParams);

            double l_dbLowPriceRange = WEB3AdminPMEquityDataManager.getForcedSettleLowPriceRange(l_tradedProduct);
            assertEquals(140D,l_dbLowPriceRange,0);
        }
        catch (Exception l_ex)
        {
            log.error(STR_METHOD_NAME, l_ex);
            fail();
        }
        log.exiting(TEST_END + STR_METHOD_NAME);
    }

    /*
     * 制限値幅を取得不到的場合<br>
     * throw 異常<br>
     * 引數.基準値=100<br>
     *市場コード=1001<br>
     *【値幅テーブル】中沒有符合 市場コード=1001記路<br>
     */
    public void test_calcPriceRange_C0012()
    {
        final String STR_METHOD_NAME = "test_getForcedSettleHighPriceRange_C0012()";
        log.entering(TEST_START + STR_METHOD_NAME);
        try
        {
            TestDBUtility.deleteAll(EquityLimitPriceRangeMstParams.TYPE);
            EquityLimitPriceRangeMstParams l_equityLimitPriceRangeMstParams =
                TestDBUtility.getEquityLimitPriceRangeMstRow();
            l_equityLimitPriceRangeMstParams.setMarketCode("1");
            l_equityLimitPriceRangeMstParams.setLowPrice(200);
            l_equityLimitPriceRangeMstParams.setCapPrice(350);
            l_equityLimitPriceRangeMstParams.setRange(100);
            l_equityLimitPriceRangeMstParams.setCreatedTimestamp(WEB3DateUtility.getDate("20070101","yyyyMMdd"));
            l_equityLimitPriceRangeMstParams.setLastUpdatedTimestamp(WEB3DateUtility.getDate("20070101","yyyyMMdd"));
            TestDBUtility.insertWithDel(l_equityLimitPriceRangeMstParams);
            WEB3AdminPMEquityDataManager.calcPriceRange("1001",100L);
            fail();
        }
        catch (WEB3BaseException l_web3SystemException)
        {
            assertEquals(WEB3ErrorCatalog.SYSTEM_ERROR_80005,l_web3SystemException.getErrorInfo());
        }
        catch (Exception l_ex)
        {
            log.error(STR_METHOD_NAME, l_ex);
            fail();
        }
        log.exiting(TEST_END + STR_METHOD_NAME);
    }

    /*
     * 制限値幅を取得的場合<br>
     * calc値幅 == 100<br>
     * return 100<br>
     */
    public void test_calcPriceRange_C0013()
    {
        final String STR_METHOD_NAME = "test_getForcedSettleHighPriceRange_C0013()";
        log.entering(TEST_START + STR_METHOD_NAME);
        try
        {
            TestDBUtility.deleteAll(MarketParams.TYPE);
            MarketParams l_marketParams = TestDBUtility.getMarketRow();
            l_marketParams.setMarketId(1L);
            TestDBUtility.insertWithDel(l_marketParams);

            TestDBUtility.deleteAll(TradedProductParams.TYPE);
            TradedProductParams l_tradedProductParams = TestDBUtility.getTradedProductRow();
            l_tradedProductParams.setProductId(3304148080000L);
            l_tradedProductParams.setMarketId(1L);
            TestDBUtility.insertWithDel(l_tradedProductParams);

            TestDBUtility.deleteAll(ProductParams.TYPE);
            ProductParams l_productParams = TestDBUtility.getProductRow();
            l_productParams.setProductId(3304148080000L);
            TestDBUtility.insertWithDel(l_productParams);

            TestDBUtility.deleteAll(EqtypeTradedProductParams.TYPE);
            EqtypeTradedProductParams l_eqtypeTradedProductParams =
                TestDBUtility.getEqtypeTradedProductRow();
            l_eqtypeTradedProductParams.setProductId(3304148080000L);
            l_eqtypeTradedProductParams.setLastClosingPrice(260L);
            TestDBUtility.insertWithDel(l_eqtypeTradedProductParams);

            TestDBUtility.deleteAll(EqtypeProductParams.TYPE);
            EqtypeProductParams l_eqtypeProductParams = TestDBUtility.getEqtypeProductRow();
            l_eqtypeProductParams.setProductId(3304148080000L);
            TestDBUtility.insertWithDel(l_eqtypeProductParams);

            TestDBUtility.deleteAll(EquityTickValuesMstParams.TYPE);
            EquityTickValuesMstParams l_equityTickValuesMstParams =
                TestDBUtility.getEquityTickValuesMstRow();
            l_equityTickValuesMstParams.setMarketCode("1");
            l_equityTickValuesMstParams.setLowPrice(200);
            l_equityTickValuesMstParams.setCapPrice(350);
            l_equityTickValuesMstParams.setTickValue(100);
            l_equityTickValuesMstParams.setCreatedTimestamp(WEB3DateUtility.getDate("20070101","yyyyMMdd"));
            l_equityTickValuesMstParams.setLastUpdatedTimestamp(WEB3DateUtility.getDate("20070101","yyyyMMdd"));
            TestDBUtility.insertWithDel(l_equityTickValuesMstParams);

            TestDBUtility.deleteAll(EquityLimitPriceRangeMstParams.TYPE);
            EquityLimitPriceRangeMstParams l_equityLimitPriceRangeMstParams =
                TestDBUtility.getEquityLimitPriceRangeMstRow();
            l_equityLimitPriceRangeMstParams.setMarketCode("1");
            l_equityLimitPriceRangeMstParams.setLowPrice(200);
            l_equityLimitPriceRangeMstParams.setCapPrice(350);
            l_equityLimitPriceRangeMstParams.setRange(100);
            l_equityLimitPriceRangeMstParams.setCreatedTimestamp(WEB3DateUtility.getDate("20070101","yyyyMMdd"));
            l_equityLimitPriceRangeMstParams.setLastUpdatedTimestamp(WEB3DateUtility.getDate("20070101","yyyyMMdd"));
            TestDBUtility.insertWithDel(l_equityLimitPriceRangeMstParams);

            double l_dblForcedSettleHighPriceRange =
                WEB3AdminPMEquityDataManager.calcPriceRange("1",240D);
            assertEquals(100D,l_dblForcedSettleHighPriceRange,0);

        }
        catch (Exception l_ex)
        {
            log.error(STR_METHOD_NAME, l_ex);
            fail();
        }
        log.exiting(TEST_END + STR_METHOD_NAME);
    }

    /*
     * 參數建株Row=null<br>
     * throw 異常<br>
     */
    public void test_getCloseOrderList_C00014()
    {
        final String STR_METHOD_NAME = "test_isApproveDealObjectOrder_C00014()";
        log.entering(TEST_START + STR_METHOD_NAME);
        try
        {
            WEB3AdminPMEquityDataManager.getCloseOrderList(null,false);
            fail();
        }
        catch (WEB3BaseException l_web3SystemException)
        {
            assertEquals(WEB3ErrorCatalog.SYSTEM_ERROR_80017,l_web3SystemException.getErrorInfo());
        }
        catch (Exception l_ex)
        {
            log.error(STR_METHOD_NAME, l_ex);
            fail();
        }
        log.exiting(TEST_END + STR_METHOD_NAME);
    }

    /*
     * 引数.建株Row！=null<br>
     * 引数.is予約注文考慮要 ＝ falseの場合<br>
     */
    public void test_getCloseOrderList_C00015()
    {
        final String STR_METHOD_NAME = "test_isApproveDealObjectOrder_C00015()";
        log.entering(TEST_START + STR_METHOD_NAME);
        try
        {
            TestDBUtility.deleteAll(EqtypeClosingContractSpecParams.TYPE);
            EqtypeClosingContractSpecParams l_eqtypeClosingContractSpecParams =
                TestDBUtility.getEqtypeClosingContractSpecRow();
            l_eqtypeClosingContractSpecParams.setContractId(1002L);
            TestDBUtility.insertWithDel(l_eqtypeClosingContractSpecParams);

            TestDBUtility.deleteAll(EqtypeClosingContractSpecParams.TYPE);
            EqtypeContractParams l_eqtypeContractParams = TestDBUtility.getEqtypeContractRow();
            l_eqtypeContractParams.setContractId(1002L);
            TestDBUtility.insertWithDel(l_eqtypeContractParams);

            List l_list = WEB3AdminPMEquityDataManager.getCloseOrderList(l_eqtypeContractParams,false);
            assertEquals(0,l_list.size());
        }
        catch (Exception l_ex)
        {
            log.error(STR_METHOD_NAME, l_ex);
            fail();
        }
        log.exiting(TEST_END + STR_METHOD_NAME);
    }

    /*
     * 引数.建株Row！=null<br>
     * 引数.is予約注文考慮要 ＝ falseの場合<br>
     */
    public void test_getCloseOrderList_C00016()
    {
        final String STR_METHOD_NAME = "test_isApproveDealObjectOrder_C00016()";
        log.entering(TEST_START + STR_METHOD_NAME);
        try
        {
            TestDBUtility.deleteAll(EqtypeContractParams.TYPE);
            EqtypeContractParams l_eqtypeContractParams = TestDBUtility.getEqtypeContractRow();
            l_eqtypeContractParams.setContractId(1001L);
            TestDBUtility.insertWithDel(l_eqtypeContractParams);
            l_eqtypeContractParams.setContractId(1001L);

            TestDBUtility.deleteAll(EqtypeClosingContractSpecParams.TYPE);
            EqtypeClosingContractSpecParams l_eqtypeClosingContractSpecParams =
                TestDBUtility.getEqtypeClosingContractSpecRow();
            l_eqtypeClosingContractSpecParams.setContractId(1001L);
            TestDBUtility.insertWithDel(l_eqtypeClosingContractSpecParams);

            TestDBUtility.deleteAll(EqtypeOrderUnitParams.TYPE);

            List l_list = WEB3AdminPMEquityDataManager.getCloseOrderList(l_eqtypeContractParams,false);
            assertEquals(0,l_list.size());
        }
        catch (Exception l_ex)
        {
            log.error(STR_METHOD_NAME, l_ex);
            fail();
        }
        log.exiting(TEST_END + STR_METHOD_NAME);
    }

    /*
     * 引数.建株Row！=null
     *注文単位を取得する。
     *注文単位テーブル存在一條
     *引数.is予約注文考慮要 ＝ trueの場合
     */
    public void test_getCloseOrderList_C00017()
    {
        final String STR_METHOD_NAME = "test_isApproveDealObjectOrder_C00017()";
        log.entering(TEST_START + STR_METHOD_NAME);
        try
        {
            TestDBUtility.deleteAll(EqtypeContractParams.TYPE);
            EqtypeContractParams l_eqtypeContractParams = TestDBUtility.getEqtypeContractRow();
            l_eqtypeContractParams.setContractId(1001L);
            TestDBUtility.insertWithDel(l_eqtypeContractParams);
            l_eqtypeContractParams.setContractId(1001L);

            TestDBUtility.deleteAll(EqtypeClosingContractSpecParams.TYPE);
            EqtypeClosingContractSpecParams l_eqtypeClosingContractSpecParams =
                TestDBUtility.getEqtypeClosingContractSpecRow();
            l_eqtypeClosingContractSpecParams.setContractId(1001L);
            l_eqtypeClosingContractSpecParams.setOrderId(1001L);
            TestDBUtility.insertWithDel(l_eqtypeClosingContractSpecParams);

            TestDBUtility.deleteAll(EqtypeOrderUnitParams.TYPE);
            EqtypeOrderUnitParams l_eqtypeOrderUnitParams = TestDBUtility.getEqtypeOrderUnitRow();
            l_eqtypeOrderUnitParams.setOrderId(1001L);
            l_eqtypeOrderUnitParams.setOrderOpenStatus(OrderOpenStatusEnum.OPEN);
            TestDBUtility.insertWithDel(l_eqtypeOrderUnitParams);

            TestDBUtility.deleteAll(RsvEqClosingContractSpecParams.TYPE);
            //RsvEqClosingContractSpecParams l_rsvEqClosingContractSpecParams = TestDBUtility.getRsvEqClosingContractSpecRow();

            List l_list = WEB3AdminPMEquityDataManager.getCloseOrderList(l_eqtypeContractParams,true);
            assertEquals(1,l_list.size());
            EqtypeOrderUnitRow l_eqtypeOrderUnitRow = (EqtypeOrderUnitRow)l_list.get(0);
            assertEquals(1001L,l_eqtypeOrderUnitRow.getOrderId());
            assertEquals(1,l_eqtypeOrderUnitRow.getOrderOpenStatus().intValue());
        }
        catch (Exception l_ex)
        {
            log.error(STR_METHOD_NAME, l_ex);
            fail();
        }
        log.exiting(TEST_END + STR_METHOD_NAME);
    }

    /*
     *引数.建株Row！=null
     *注文単位を取得する。
     *注文単位テーブル存在一條
     *引数.is予約注文考慮要 ＝ trueの場合
     *株式予約建株返済指定情報ﾃｰﾌﾞﾙの取得ある
     *株式予約注文単位テーブルの取得ありません
     */
    public void test_getCloseOrderList_C00018()
    {
        final String STR_METHOD_NAME = "test_isApproveDealObjectOrder_C00018()";
        log.entering(TEST_START + STR_METHOD_NAME);
        try
        {
            TestDBUtility.deleteAll(EqtypeContractParams.TYPE);
            EqtypeContractParams l_eqtypeContractParams = TestDBUtility.getEqtypeContractRow();
            l_eqtypeContractParams.setContractId(1001L);
            TestDBUtility.insertWithDel(l_eqtypeContractParams);
            l_eqtypeContractParams.setContractId(1001L);

            TestDBUtility.deleteAll(EqtypeClosingContractSpecParams.TYPE);
            EqtypeClosingContractSpecParams l_eqtypeClosingContractSpecParams =
                TestDBUtility.getEqtypeClosingContractSpecRow();
            l_eqtypeClosingContractSpecParams.setContractId(1001L);
            l_eqtypeClosingContractSpecParams.setOrderId(1001L);
            TestDBUtility.insertWithDel(l_eqtypeClosingContractSpecParams);

            TestDBUtility.deleteAll(EqtypeOrderUnitParams.TYPE);
            EqtypeOrderUnitParams l_eqtypeOrderUnitParams1 = TestDBUtility.getEqtypeOrderUnitRow();
            l_eqtypeOrderUnitParams1.setOrderId(1001L);
            l_eqtypeOrderUnitParams1.setOrderOpenStatus(OrderOpenStatusEnum.OPEN);
            TestDBUtility.insertWithDel(l_eqtypeOrderUnitParams1);

            TestDBUtility.deleteAll(RsvEqOrderUnitParams.TYPE); 

            TestDBUtility.deleteAll(RsvEqClosingContractSpecParams.TYPE);
            RsvEqClosingContractSpecParams l_rsvEqClosingContractSpecParams = TestDBUtility.getRsvEqClosingContractSpecRow();
            l_rsvEqClosingContractSpecParams.setContractId(1001L);
            TestDBUtility.insertWithDel(l_rsvEqClosingContractSpecParams);

            TestDBUtility.deleteAll(RsvEqOrderUnitParams.TYPE); 

            List l_list = WEB3AdminPMEquityDataManager.getCloseOrderList(l_eqtypeContractParams,true);
            assertEquals(1,l_list.size());
            EqtypeOrderUnitRow l_eqtypeOrderUnitRow = (EqtypeOrderUnitRow)l_list.get(0);
            assertEquals(1001L,l_eqtypeOrderUnitRow.getOrderId());
            assertEquals(1,l_eqtypeOrderUnitRow.getOrderOpenStatus().intValue());
        }
        catch (Exception l_ex)
        {
            log.error(STR_METHOD_NAME, l_ex);
            fail();
        }
        log.exiting(TEST_END + STR_METHOD_NAME);
    }

    /*
     * 引数.建株Row！=null
     *注文単位を取得する。
     *注文単位テーブル存在一條
     *引数.is予約注文考慮要 ＝ trueの場合
     *株式予約建株返済指定情報ﾃｰﾌﾞﾙの取得ある
     *株式予約注文単位テーブルの取得ある
     */
    public void test_getCloseOrderList_C00019()
    {
        final String STR_METHOD_NAME = "test_isApproveDealObjectOrder_C00019()";
        log.entering(TEST_START + STR_METHOD_NAME);
        try
        {
            TestDBUtility.deleteAll(EqtypeContractParams.TYPE);
            EqtypeContractParams l_eqtypeContractParams = TestDBUtility.getEqtypeContractRow();
            l_eqtypeContractParams.setContractId(1001L);
            TestDBUtility.insertWithDel(l_eqtypeContractParams);

            TestDBUtility.deleteAll(EqtypeClosingContractSpecParams.TYPE);
            EqtypeClosingContractSpecParams l_eqtypeClosingContractSpecParams1 =
                TestDBUtility.getEqtypeClosingContractSpecRow();
            l_eqtypeClosingContractSpecParams1.setContractId(1001L);
            l_eqtypeClosingContractSpecParams1.setOrderId(1002L);
            TestDBUtility.insertWithDel(l_eqtypeClosingContractSpecParams1);

            TestDBUtility.deleteAll(EqtypeOrderUnitParams.TYPE);
            EqtypeOrderUnitParams l_eqtypeOrderUnitParams1 = TestDBUtility.getEqtypeOrderUnitRow();
            l_eqtypeOrderUnitParams1.setOrderUnitId(3304148080001L);
            l_eqtypeOrderUnitParams1.setOrderId(1002L);
            l_eqtypeOrderUnitParams1.setOrderOpenStatus(OrderOpenStatusEnum.OPEN);
            TestDBUtility.insertWithDel(l_eqtypeOrderUnitParams1);

            TestDBUtility.deleteAll(RsvEqOrderUnitParams.TYPE); 
            RsvEqOrderUnitParams l_rsvEqOrderUnitParams = TestDBUtility.getRsvEqOrderUnitRow();
            l_rsvEqOrderUnitParams.setOrderUnitId(3304148080002L);
            l_rsvEqOrderUnitParams.setOrderId(1001L);
            l_rsvEqOrderUnitParams.setOrderOpenStatus(OrderOpenStatusEnum.OPEN);
            l_rsvEqOrderUnitParams.setParentOrderId(2001L);
            l_rsvEqOrderUnitParams.setParentOrderUnitId(2002L);
            l_rsvEqOrderUnitParams.setAccountId(2003L);
            TestDBUtility.insertWithDel(l_rsvEqOrderUnitParams);

            TestDBUtility.deleteAll(RsvEqClosingContractSpecParams.TYPE);
            RsvEqClosingContractSpecParams l_rsvEqClosingContractSpecParams = TestDBUtility.getRsvEqClosingContractSpecRow();
            l_rsvEqClosingContractSpecParams.setContractId(1001L);
            l_rsvEqClosingContractSpecParams.setOrderId(1001L);
            TestDBUtility.insertWithDel(l_rsvEqClosingContractSpecParams);

            List l_list = WEB3AdminPMEquityDataManager.getCloseOrderList(l_eqtypeContractParams,true);
            assertEquals(2,l_list.size());
            EqtypeOrderUnitRow l_eqtypeOrderUnitRow = (EqtypeOrderUnitRow)l_list.get(0);
            assertEquals(3304148080001L,l_eqtypeOrderUnitRow.getOrderUnitId());
            assertEquals(1002L,l_eqtypeOrderUnitRow.getOrderId());
            assertEquals(1,l_eqtypeOrderUnitRow.getOrderOpenStatus().intValue());
            
            RsvEqOrderUnitRow l_rsvEqOrderUnitRow = (RsvEqOrderUnitRow)l_list.get(1);
            assertEquals(3304148080002L,l_rsvEqOrderUnitRow.getOrderUnitId());
            assertEquals(1001L,l_rsvEqOrderUnitRow.getOrderId());
            assertEquals(1,l_rsvEqOrderUnitRow.getOrderOpenStatus().intValue());
        }
        catch (Exception l_ex)
        {
            log.error(STR_METHOD_NAME, l_ex);
            fail();
        }
        log.exiting(TEST_END + STR_METHOD_NAME);
    }

    /*
     * 引数.建株Row！=null
     *注文単位を取得する。
     *注文単位テーブル存在一條
     *引数.is予約注文考慮要 ＝ trueの場合
     *株式予約建株返済指定情報ﾃｰﾌﾞﾙの取得ある
     *株式予約注文単位テーブルの取得2條ある
     */
    public void test_getCloseOrderList_C00020()
    {
        final String STR_METHOD_NAME = "test_isApproveDealObjectOrder_C00020()";
        log.entering(TEST_START + STR_METHOD_NAME);
        try
        {
            TestDBUtility.deleteAll(EqtypeContractParams.TYPE);
            EqtypeContractParams l_eqtypeContractParams = TestDBUtility.getEqtypeContractRow();
            l_eqtypeContractParams.setContractId(1001L);
            TestDBUtility.insertWithDel(l_eqtypeContractParams);

            TestDBUtility.deleteAll(EqtypeClosingContractSpecParams.TYPE);
            EqtypeClosingContractSpecParams l_eqtypeClosingContractSpecParams1 =
                TestDBUtility.getEqtypeClosingContractSpecRow();
            l_eqtypeClosingContractSpecParams1.setContractId(1001L);
            l_eqtypeClosingContractSpecParams1.setOrderId(1002L);
            TestDBUtility.insertWithDel(l_eqtypeClosingContractSpecParams1);

            TestDBUtility.deleteAll(EqtypeOrderUnitParams.TYPE);
            EqtypeOrderUnitParams l_eqtypeOrderUnitParams1 = TestDBUtility.getEqtypeOrderUnitRow();
            l_eqtypeOrderUnitParams1.setOrderUnitId(3304148080001L);
            l_eqtypeOrderUnitParams1.setOrderId(1002L);
            l_eqtypeOrderUnitParams1.setOrderOpenStatus(OrderOpenStatusEnum.OPEN);
            TestDBUtility.insertWithDel(l_eqtypeOrderUnitParams1);

            EqtypeOrderUnitParams l_eqtypeOrderUnitParams2 = TestDBUtility.getEqtypeOrderUnitRow();
            l_eqtypeOrderUnitParams2.setOrderUnitId(3304148080002L);
            l_eqtypeOrderUnitParams2.setOrderId(1002L);
            l_eqtypeOrderUnitParams2.setOrderOpenStatus(OrderOpenStatusEnum.OPEN);
            TestDBUtility.insertWithDel(l_eqtypeOrderUnitParams2);

            TestDBUtility.deleteAll(RsvEqClosingContractSpecParams.TYPE);
            RsvEqClosingContractSpecParams l_rsvEqClosingContractSpecParams = TestDBUtility.getRsvEqClosingContractSpecRow();
            l_rsvEqClosingContractSpecParams.setContractId(1001L);
            l_rsvEqClosingContractSpecParams.setOrderId(1002L);
            l_rsvEqClosingContractSpecParams.setClosingSerialNo(123);
            TestDBUtility.insertWithDel(l_rsvEqClosingContractSpecParams);

            RsvEqClosingContractSpecParams l_rsvEqClosingContractSpecParams1 = TestDBUtility.getRsvEqClosingContractSpecRow();
            l_rsvEqClosingContractSpecParams1.setContractId(1001L);
            l_rsvEqClosingContractSpecParams1.setOrderId(1001L);
            l_rsvEqClosingContractSpecParams1.setClosingSerialNo(124);
            TestDBUtility.insertWithDel(l_rsvEqClosingContractSpecParams1);

            TestDBUtility.deleteAll(RsvEqOrderUnitParams.TYPE); 
            RsvEqOrderUnitParams l_rsvEqOrderUnitParams = TestDBUtility.getRsvEqOrderUnitRow();
            l_rsvEqOrderUnitParams.setOrderUnitId(3304148080001L);
            l_rsvEqOrderUnitParams.setOrderId(1001L);
            l_rsvEqOrderUnitParams.setOrderOpenStatus(OrderOpenStatusEnum.OPEN);
            l_rsvEqOrderUnitParams.setParentOrderId(2001L);
            l_rsvEqOrderUnitParams.setParentOrderUnitId(2002L);
            l_rsvEqOrderUnitParams.setAccountId(2003L);
            TestDBUtility.insertWithDel(l_rsvEqOrderUnitParams);

            RsvEqOrderUnitParams l_rsvEqOrderUnitParams1 = TestDBUtility.getRsvEqOrderUnitRow();
            l_rsvEqOrderUnitParams1.setOrderUnitId(3304148080002L);
            l_rsvEqOrderUnitParams1.setOrderId(1002L);
            l_rsvEqOrderUnitParams1.setOrderOpenStatus(OrderOpenStatusEnum.OPEN);
            l_rsvEqOrderUnitParams1.setParentOrderId(3001L);
            l_rsvEqOrderUnitParams1.setParentOrderUnitId(3002L);
            l_rsvEqOrderUnitParams1.setAccountId(3003L);
            TestDBUtility.insertWithDel(l_rsvEqOrderUnitParams1);

            List l_list = WEB3AdminPMEquityDataManager.getCloseOrderList(l_eqtypeContractParams,true);
            assertEquals(4,l_list.size());
            EqtypeOrderUnitRow l_eqtypeOrderUnitRow = (EqtypeOrderUnitRow)l_list.get(0);
            assertEquals(3304148080001L,l_eqtypeOrderUnitRow.getOrderUnitId());
            assertEquals(1002L,l_eqtypeOrderUnitRow.getOrderId());
            assertEquals(1,l_eqtypeOrderUnitRow.getOrderOpenStatus().intValue());
            
            EqtypeOrderUnitRow l_eqtypeOrderUnitRow1 = (EqtypeOrderUnitRow)l_list.get(1);
            assertEquals(3304148080002L,l_eqtypeOrderUnitRow1.getOrderUnitId());
            assertEquals(1002L,l_eqtypeOrderUnitRow1.getOrderId());
            assertEquals(1,l_eqtypeOrderUnitRow1.getOrderOpenStatus().intValue());

            RsvEqOrderUnitRow l_rsvEqOrderUnitRow = (RsvEqOrderUnitRow)l_list.get(2);
            assertEquals(3304148080001L,l_rsvEqOrderUnitRow.getOrderUnitId());
            assertEquals(1001L,l_rsvEqOrderUnitRow.getOrderId());
            assertEquals(1,l_rsvEqOrderUnitRow.getOrderOpenStatus().intValue());

            RsvEqOrderUnitRow l_rsvEqOrderUnitRow1 = (RsvEqOrderUnitRow)l_list.get(3);
            assertEquals(3304148080002L,l_rsvEqOrderUnitRow1.getOrderUnitId());
            assertEquals(1002L,l_rsvEqOrderUnitRow1.getOrderId());
            assertEquals(1,l_rsvEqOrderUnitRow1.getOrderOpenStatus().intValue());
        }
        catch (Exception l_ex)
        {
            log.error(STR_METHOD_NAME, l_ex);
            fail();
        }
        log.exiting(TEST_END + STR_METHOD_NAME);
    }
    
    public void testCreatForcedSettleReasonUnit_case0001()
    {
        final String STR_METHOD_NAME = "testCreatForcedSettleReasonUnit_case0001()";
        log.entering(STR_METHOD_NAME);
        
        try
        {

            TestDBUtility.deleteAll(BranchRow.TYPE);
            BranchParams l_branchParams = TestDBUtility.getBranchRow();
            l_branchParams.setBranchId(12345L);
            TestDBUtility.insertWithDel(l_branchParams);
            
            Branch l_branch = new BranchImpl(12345L);
            String l_strForcedSettleReasonDiv = "0";
            WEB3AdminForcedSettleReasonUnit l_unit =
                WEB3AdminPMEquityDataManager.creatForcedSettleReasonUnit(
                    l_strForcedSettleReasonDiv, l_branch);
            assertEquals("0", l_unit.forcedSettleReason);
            assertEquals(null,l_unit.marginMaintenanceRate);
            assertEquals(null,l_unit.additionalElapsedDaysUpperLimit);
            
        }
        catch (Exception l_ex)
        {
            log.debug(l_ex.getMessage(), l_ex);
            fail();
        }
        
        log.exiting(STR_METHOD_NAME);
    }
    
    public void testCreatForcedSettleReasonUnit_case0002()
    {
        final String STR_METHOD_NAME = "testCreatForcedSettleReasonUnit_case0002()";
        log.entering(STR_METHOD_NAME);
        
        try
        {
            TestDBUtility.deleteAll(BranchPreferencesRow.TYPE);
            BranchPreferencesParams l_branchPreferenceParams = TestDBUtility.getBranchPreferencesRow();
            l_branchPreferenceParams.setBranchId(12345);
            l_branchPreferenceParams.setName("first.deposit.rate1");
            TestDBUtility.insertWithDel(l_branchPreferenceParams);
            
            BranchPreferencesParams l_branchPreferenceParams1 = TestDBUtility.getBranchPreferencesRow();
            l_branchPreferenceParams1.setBranchId(12345);
            l_branchPreferenceParams1.setName("first.margin.pass.day1");
            TestDBUtility.insertWithDel(l_branchPreferenceParams1);

            TestDBUtility.deleteAll(BranchRow.TYPE);
            BranchParams l_branchParams = TestDBUtility.getBranchRow();
            l_branchParams.setBranchId(12345L);
            TestDBUtility.insertWithDel(l_branchParams);
            
            Branch l_branch = new BranchImpl(12345L);
            String l_strForcedSettleReasonDiv = "1";
            WEB3AdminForcedSettleReasonUnit l_unit =
                WEB3AdminPMEquityDataManager.creatForcedSettleReasonUnit(
                    l_strForcedSettleReasonDiv, l_branch);
            assertEquals("1", l_unit.forcedSettleReason);
            assertEquals("1", l_unit.marginMaintenanceRate);
            assertEquals("1", l_unit.additionalElapsedDaysUpperLimit);
            
        }
        catch (Exception l_ex)
        {
            log.debug(l_ex.getMessage(), l_ex);
            fail();
        }
        
        log.exiting(STR_METHOD_NAME);
    }
    
    public void testCreatForcedSettleReasonUnit_case0003()
    {
        final String STR_METHOD_NAME = "testCreatForcedSettleReasonUnit_case0003()";
        log.entering(STR_METHOD_NAME);
        
        try
        {
            TestDBUtility.deleteAll(BranchPreferencesRow.TYPE);

            TestDBUtility.deleteAll(BranchRow.TYPE);
            BranchParams l_branchParams = TestDBUtility.getBranchRow();
            l_branchParams.setBranchId(12345L);
            TestDBUtility.insertWithDel(l_branchParams);
            
            Branch l_branch = new BranchImpl(12345L);
            String l_strForcedSettleReasonDiv = "1";
            WEB3AdminForcedSettleReasonUnit l_unit =
                WEB3AdminPMEquityDataManager.creatForcedSettleReasonUnit(
                    l_strForcedSettleReasonDiv, l_branch);
            assertEquals(null, l_unit);
            
        }
        catch (Exception l_ex)
        {
            log.debug(l_ex.getMessage(), l_ex);
            fail();
        }
        
        log.exiting(STR_METHOD_NAME);
    }
    
    public void testCreatForcedSettleReasonUnit_case0004()
    {
        final String STR_METHOD_NAME = "testCreatForcedSettleReasonUnit_case0004()";
        log.entering(STR_METHOD_NAME);
        
        try
        {
            TestDBUtility.deleteAll(BranchPreferencesRow.TYPE);
            BranchPreferencesParams l_branchPreferenceParams = TestDBUtility.getBranchPreferencesRow();
            l_branchPreferenceParams.setBranchId(12345);
            l_branchPreferenceParams.setName("first.deposit.rate1");
            TestDBUtility.insertWithDel(l_branchPreferenceParams);

            TestDBUtility.deleteAll(BranchRow.TYPE);
            BranchParams l_branchParams = TestDBUtility.getBranchRow();
            l_branchParams.setBranchId(12345L);
            TestDBUtility.insertWithDel(l_branchParams);
            
            Branch l_branch = new BranchImpl(12345L);
            String l_strForcedSettleReasonDiv = "1";
            WEB3AdminForcedSettleReasonUnit l_unit =
                WEB3AdminPMEquityDataManager.creatForcedSettleReasonUnit(
                    l_strForcedSettleReasonDiv, l_branch);
            assertEquals(null, l_unit);
            
        }
        catch (Exception l_ex)
        {
            log.debug(l_ex.getMessage(), l_ex);
            fail();
        }
        
        log.exiting(STR_METHOD_NAME);
    }
    
    public void testCreatForcedSettleReasonUnit_case0005()
    {
        final String STR_METHOD_NAME = "testCreatForcedSettleReasonUnit_case0005()";
        log.entering(STR_METHOD_NAME);
        
        try
        {
            TestDBUtility.deleteAll(BranchPreferencesRow.TYPE);
            BranchPreferencesParams l_branchPreferenceParams = TestDBUtility.getBranchPreferencesRow();
            l_branchPreferenceParams.setBranchId(12345);
            l_branchPreferenceParams.setName("first.deposit.rate2");
            TestDBUtility.insertWithDel(l_branchPreferenceParams);
            
            BranchPreferencesParams l_branchPreferenceParams1 = TestDBUtility.getBranchPreferencesRow();
            l_branchPreferenceParams1.setBranchId(12345);
            l_branchPreferenceParams1.setName("first.margin.pass.day2");
            TestDBUtility.insertWithDel(l_branchPreferenceParams1);

            TestDBUtility.deleteAll(BranchRow.TYPE);
            BranchParams l_branchParams = TestDBUtility.getBranchRow();
            l_branchParams.setBranchId(12345L);
            TestDBUtility.insertWithDel(l_branchParams);
            
            Branch l_branch = new BranchImpl(12345L);
            String l_strForcedSettleReasonDiv = "2";
            WEB3AdminForcedSettleReasonUnit l_unit =
                WEB3AdminPMEquityDataManager.creatForcedSettleReasonUnit(
                    l_strForcedSettleReasonDiv, l_branch);
            assertEquals("2", l_unit.forcedSettleReason);
            assertEquals("1", l_unit.marginMaintenanceRate);
            assertEquals("1", l_unit.additionalElapsedDaysUpperLimit);
            
        }
        catch (Exception l_ex)
        {
            log.debug(l_ex.getMessage(), l_ex);
            fail();
        }
        
        log.exiting(STR_METHOD_NAME);
    }
    
    public void testCreatForcedSettleReasonUnit_case0006()
    {
        final String STR_METHOD_NAME = "testCreatForcedSettleReasonUnit_case0006()";
        log.entering(STR_METHOD_NAME);
        
        try
        {
            TestDBUtility.deleteAll(BranchPreferencesRow.TYPE);
            
            
            TestDBUtility.deleteAll(BranchRow.TYPE);
            BranchParams l_branchParams = TestDBUtility.getBranchRow();
            l_branchParams.setBranchId(12345L);
            TestDBUtility.insertWithDel(l_branchParams);
            
            Branch l_branch = new BranchImpl(12345L);
            String l_strForcedSettleReasonDiv = "2";
            WEB3AdminForcedSettleReasonUnit l_unit =
                WEB3AdminPMEquityDataManager.creatForcedSettleReasonUnit(
                    l_strForcedSettleReasonDiv, l_branch);
            assertEquals(null, l_unit);
            
        }
        catch (Exception l_ex)
        {
            log.debug(l_ex.getMessage(), l_ex);
            fail();
        }
        
        log.exiting(STR_METHOD_NAME);
    }
    
    public void testCreatForcedSettleReasonUnit_case0007()
    {
        final String STR_METHOD_NAME = "testCreatForcedSettleReasonUnit_case0007()";
        log.entering(STR_METHOD_NAME);
        
        try
        {
            TestDBUtility.deleteAll(BranchPreferencesRow.TYPE);
            BranchPreferencesParams l_branchPreferenceParams = TestDBUtility.getBranchPreferencesRow();
            l_branchPreferenceParams.setBranchId(12345);
            l_branchPreferenceParams.setName("first.deposit.rate2");
            TestDBUtility.insertWithDel(l_branchPreferenceParams);

            TestDBUtility.deleteAll(BranchRow.TYPE);
            BranchParams l_branchParams = TestDBUtility.getBranchRow();
            l_branchParams.setBranchId(12345L);
            TestDBUtility.insertWithDel(l_branchParams);
            
            Branch l_branch = new BranchImpl(12345L);
            String l_strForcedSettleReasonDiv = "2";
            WEB3AdminForcedSettleReasonUnit l_unit =
                WEB3AdminPMEquityDataManager.creatForcedSettleReasonUnit(
                    l_strForcedSettleReasonDiv, l_branch);
            assertEquals(null, l_unit);
            
        }
        catch (Exception l_ex)
        {
            log.debug(l_ex.getMessage(), l_ex);
            fail();
        }
        
        log.exiting(STR_METHOD_NAME);
    }
    
    /**
     * 引数.強制決済理由区分＝"保証金維持率割れ（場間）"
     *
     */
    public void testCreatForcedSettleReasonUnit_case0008()
    {
        final String STR_METHOD_NAME = "testCreatForcedSettleReasonUnit_case0008()";
        log.entering(STR_METHOD_NAME);
        
        try
        {

            TestDBUtility.deleteAll(BranchRow.TYPE);
            BranchParams l_branchParams = TestDBUtility.getBranchRow();
            l_branchParams.setBranchId(12345L);
            TestDBUtility.insertWithDel(l_branchParams);
            
            Branch l_branch = new BranchImpl(12345L);
            String l_strForcedSettleReasonDiv = "3";
            WEB3AdminForcedSettleReasonUnit l_unit =
                WEB3AdminPMEquityDataManager.creatForcedSettleReasonUnit(
                    l_strForcedSettleReasonDiv, l_branch);
            assertEquals("3", l_unit.forcedSettleReason);
            assertEquals(null,l_unit.marginMaintenanceRate);
            assertEquals(null,l_unit.additionalElapsedDaysUpperLimit);
            
        }
        catch (Exception l_ex)
        {
            log.debug(l_ex.getMessage(), l_ex);
            fail();
        }
        
        log.exiting(STR_METHOD_NAME);
    }
    
    public void testCreatForcedSettleReasonUnit_case0009()
    {
        final String STR_METHOD_NAME = "testCreatForcedSettleReasonUnit_case0009()";
        log.entering(STR_METHOD_NAME);
        
        try
        {
            TestDBUtility.deleteAll(BranchRow.TYPE);
            BranchParams l_branchParams = TestDBUtility.getBranchRow();
            l_branchParams.setBranchId(12345L);
            TestDBUtility.insertWithDel(l_branchParams);
            
            Branch l_branch = new BranchImpl(12345L);
            String l_strForcedSettleReasonDiv = "9";
            WEB3AdminForcedSettleReasonUnit l_unit =
                WEB3AdminPMEquityDataManager.creatForcedSettleReasonUnit(
                    l_strForcedSettleReasonDiv, l_branch);
            assertEquals("9", l_unit.forcedSettleReason);
            assertEquals(null,l_unit.marginMaintenanceRate);
            assertEquals(null,l_unit.additionalElapsedDaysUpperLimit);
            
        }
        catch (Exception l_ex)
        {
            log.debug(l_ex.getMessage(), l_ex);
            fail();
        }
        
        log.exiting(STR_METHOD_NAME);
    }
    
    public void testCreatForcedSettleReasonUnit_case0010()
    {
        final String STR_METHOD_NAME = "testCreatForcedSettleReasonUnit_case0010()";
        log.entering(STR_METHOD_NAME);
        
        try
        {
            TestDBUtility.deleteAll(BranchRow.TYPE);
            BranchParams l_branchParams = TestDBUtility.getBranchRow();
            l_branchParams.setBranchId(12345L);
            TestDBUtility.insertWithDel(l_branchParams);
            
            Branch l_branch = new BranchImpl(12345L);
            String l_strForcedSettleReasonDiv = "5";
            WEB3AdminForcedSettleReasonUnit l_unit =
                WEB3AdminPMEquityDataManager.creatForcedSettleReasonUnit(
                    l_strForcedSettleReasonDiv, l_branch);
            
        }
        catch (WEB3BaseException l_ex)
        {
            log.debug(l_ex.getMessage(), l_ex);
            assertEquals(WEB3ErrorCatalog.SYSTEM_ERROR_80017, l_ex.getErrorInfo());
        }
        catch (Exception l_ex)
        {
            log.debug(l_ex.getMessage(), l_ex);
            fail();
        }
        
        log.exiting(STR_METHOD_NAME);
    }
    public void testCreatForcedSettleReasonUnit_case0011()
    {
        final String STR_METHOD_NAME = "testCreatForcedSettleReasonUnit_case0011()";
        log.entering(STR_METHOD_NAME);
        
        try
        {
            Branch l_branch = null;
            String l_strForcedSettleReasonDiv = "5";
            WEB3AdminForcedSettleReasonUnit l_unit =
                WEB3AdminPMEquityDataManager.creatForcedSettleReasonUnit(
                    l_strForcedSettleReasonDiv, l_branch);
            
        }
        catch (WEB3BaseException l_ex)
        {
            log.debug(l_ex.getMessage(), l_ex);
            assertEquals(WEB3ErrorCatalog.SYSTEM_ERROR_80017, l_ex.getErrorInfo());
        }
        catch (Exception l_ex)
        {
            log.debug(l_ex.getMessage(), l_ex);
            fail();
        }
        
        log.exiting(STR_METHOD_NAME);
    }
    
    /**
     * 引数.強制決済理由区分＝"保証金維持率割れ（オンライン開始前・法@定）"
     *
     */
    public void testCreatForcedSettleReasonUnit_case0012()
    {
        final String STR_METHOD_NAME = "testCreatForcedSettleReasonUnit_case0012()";
        log.entering(STR_METHOD_NAME);
        
        try
        {

            TestDBUtility.deleteAll(BranchRow.TYPE);
            BranchParams l_branchParams = TestDBUtility.getBranchRow();
            l_branchParams.setBranchId(12345L);
            TestDBUtility.insertWithDel(l_branchParams);
            
            Branch l_branch = new BranchImpl(12345L);
            String l_strForcedSettleReasonDiv = "4";
            WEB3AdminForcedSettleReasonUnit l_unit =
                WEB3AdminPMEquityDataManager.creatForcedSettleReasonUnit(
                    l_strForcedSettleReasonDiv, l_branch);
            assertEquals("4", l_unit.forcedSettleReason);
            assertEquals(null,l_unit.marginMaintenanceRate);
            assertEquals(null,l_unit.additionalElapsedDaysUpperLimit);
            
        }
        catch (Exception l_ex)
        {
            log.debug(l_ex.getMessage(), l_ex);
            fail();
        }
        
        log.exiting(STR_METHOD_NAME);
    }
    
    public void testCreateForcedSettleOrderUnitList_case0001()
    {
        final String STR_METHOD_NAME = "testCreateForcedSettleOrderUnitList_case0001()";
        log.entering(STR_METHOD_NAME);
        
        try
        {
            TestDBUtility.deleteAll(BranchPreferencesRow.TYPE);
            BranchPreferencesParams l_branchPreferenceParams = TestDBUtility.getBranchPreferencesRow();
            l_branchPreferenceParams.setBranchId(12345);
            l_branchPreferenceParams.setName("margin.forcedsettleorder.basecontractamount");
            TestDBUtility.insertWithDel(l_branchPreferenceParams);
            
            TestDBUtility.deleteAll(BranchRow.TYPE);
            BranchParams l_branchParams = TestDBUtility.getBranchRow();
            l_branchParams.setBranchId(12345L);
            TestDBUtility.insertWithDel(l_branchParams);
       
            TestDBUtility.deleteAll(ProductParams.TYPE);
            ProductParams l_productParams =
                TestDBUtility.getProductRow();
            l_productParams.setProductId(123);
            l_productParams.setProductType(ProductTypeEnum.EQUITY);
            TestDBUtility.insertWithDel(l_productParams);
            
            TestDBUtility.deleteAll(EqtypeProductParams.TYPE);
            EqtypeProductParams l_eqtypeProductParams =
                TestDBUtility.getEqtypeProductRow();
            l_eqtypeProductParams.setProductId(123);
            l_eqtypeProductParams.setProductType(ProductTypeEnum.EQUITY);
            TestDBUtility.insertWithDel(l_eqtypeProductParams);
            
            TestDBUtility.deleteAll(MarketParams.TYPE);
            MarketParams l_marketParams =
                TestDBUtility.getMarketRow();
            l_marketParams.setMarketId(123);
            TestDBUtility.insertWithDel(l_marketParams);
            
            TestDBUtility.deleteAll(MainAccountParams.TYPE);
            MainAccountParams l_mainAccountParams =
                TestDBUtility.getMainAccountRow();
            l_mainAccountParams.setAccountId(123);
            TestDBUtility.insertWithDel(l_mainAccountParams);
            
            TestDBUtility.deleteAll(TradedProductParams.TYPE);
            TradedProductParams l_tradedProductParams =
                TestDBUtility.getTradedProductRow();
            l_tradedProductParams.setMarketId(123);
            l_tradedProductParams.setProductId(123);
            l_tradedProductParams.setProductType(ProductTypeEnum.EQUITY);
            TestDBUtility.insertWithDel(l_tradedProductParams);
            
            TestDBUtility.deleteAll(EqtypeTradedProductParams.TYPE);
            EqtypeTradedProductParams l_eqtypeTradedProductParams =
                TestDBUtility.getEqtypeTradedProductRow();
            l_eqtypeTradedProductParams.setMarketId(123);
            l_eqtypeTradedProductParams.setProductId(123);
            l_eqtypeTradedProductParams.setBasePrice(12345.5002D);
            TestDBUtility.insertWithDel(l_eqtypeTradedProductParams);
            
            AdminEqForcedSettleOrderParams[] l_orderRowList = new AdminEqForcedSettleOrderParams[1];
            l_orderRowList[0] = new AdminEqForcedSettleOrderParams();
            l_orderRowList[0].setBranchId(12345);
            l_orderRowList[0].setProductId(123);
            l_orderRowList[0].setMarketId(123);
            l_orderRowList[0].setMarginMaintenanceRate(12345.023D);
            l_orderRowList[0].setAdditionalMarginAccruedDays(12L);
            l_orderRowList[0].setQuantity(60D);
            l_orderRowList[0].setForcedSettleReasonType("0");
            l_orderRowList[0].setAccountId(123);
            l_orderRowList[0].setContractType(ContractTypeEnum.LONG);
            Branch l_branch = new BranchImpl(12345L);

            WEB3AdminForcedSettleTemporaryOrderUnit[] l_unit =
                WEB3AdminPMEquityDataManager.createForcedSettleOrderUnitList(l_orderRowList, "12");
            assertEquals("12345.023", l_unit[0].marginCollateralRate);
            assertEquals("12", l_unit[0].additionalElapsedDays);
            assertEquals(null, l_unit[0].forcedSettleReason.additionalElapsedDaysUpperLimit);
            assertEquals("0", l_unit[0].forcedSettleReason.forcedSettleReason);
            assertEquals("740730.012", l_unit[0].estimatedAsset);
            assertEquals(null, l_unit[0].forcedSettleReason.marginMaintenanceRate);
             
        }
        catch (Exception l_ex)
        {
            log.debug(l_ex.getMessage(), l_ex);
            fail();
        }
        
        log.exiting(STR_METHOD_NAME);
    }
    
    public void testCreateForcedSettleOrderUnitList_case0002()
    {
        final String STR_METHOD_NAME = "testCreateForcedSettleOrderUnitList_case0002()";
        log.entering(STR_METHOD_NAME);
        
        try
        {
            TestDBUtility.deleteAll(BranchPreferencesRow.TYPE);
            BranchPreferencesParams l_branchPreferenceParams = TestDBUtility.getBranchPreferencesRow();
            l_branchPreferenceParams.setBranchId(12345);
            l_branchPreferenceParams.setName("margin.forcedsettleorder.basecontractamount");
            TestDBUtility.insertWithDel(l_branchPreferenceParams);
            
            TestDBUtility.deleteAll(BranchRow.TYPE);
            BranchParams l_branchParams = TestDBUtility.getBranchRow();
            l_branchParams.setBranchId(12345L);
            TestDBUtility.insertWithDel(l_branchParams);
       
            TestDBUtility.deleteAll(ProductParams.TYPE);
            ProductParams l_productParams =
                TestDBUtility.getProductRow();
            l_productParams.setProductId(123);
            l_productParams.setProductType(ProductTypeEnum.EQUITY);
            TestDBUtility.insertWithDel(l_productParams);
            
            TestDBUtility.deleteAll(EqtypeProductParams.TYPE);
            EqtypeProductParams l_eqtypeProductParams =
                TestDBUtility.getEqtypeProductRow();
            l_eqtypeProductParams.setProductId(123);
            l_eqtypeProductParams.setProductType(ProductTypeEnum.EQUITY);
            TestDBUtility.insertWithDel(l_eqtypeProductParams);
            
            TestDBUtility.deleteAll(MarketParams.TYPE);
            MarketParams l_marketParams =
                TestDBUtility.getMarketRow();
            l_marketParams.setMarketId(123);
            TestDBUtility.insertWithDel(l_marketParams);
            
            TestDBUtility.deleteAll(MainAccountParams.TYPE);
            MainAccountParams l_mainAccountParams =
                TestDBUtility.getMainAccountRow();
            l_mainAccountParams.setAccountId(123);
            TestDBUtility.insertWithDel(l_mainAccountParams);
            
            TestDBUtility.deleteAll(TradedProductParams.TYPE);
            TradedProductParams l_tradedProductParams =
                TestDBUtility.getTradedProductRow();
            l_tradedProductParams.setMarketId(123);
            l_tradedProductParams.setProductId(123);
            l_tradedProductParams.setProductType(ProductTypeEnum.EQUITY);
            TestDBUtility.insertWithDel(l_tradedProductParams);
            
            TestDBUtility.deleteAll(EqtypeTradedProductParams.TYPE);
            EqtypeTradedProductParams l_eqtypeTradedProductParams =
                TestDBUtility.getEqtypeTradedProductRow();
            l_eqtypeTradedProductParams.setMarketId(123);
            l_eqtypeTradedProductParams.setProductId(123);
            l_eqtypeTradedProductParams.setBasePrice(12345.5002D);
            TestDBUtility.insertWithDel(l_eqtypeTradedProductParams);
            
            AdminEqForcedSettleOrderParams[] l_orderRowList = new AdminEqForcedSettleOrderParams[1];
            l_orderRowList[0] = new AdminEqForcedSettleOrderParams();
            l_orderRowList[0].setBranchId(12345);
            l_orderRowList[0].setProductId(123);
            l_orderRowList[0].setMarketId(123);
            l_orderRowList[0].setMarginMaintenanceRate(12345.023D);
            l_orderRowList[0].setAdditionalMarginAccruedDays(12L);
            l_orderRowList[0].setQuantity(60D);
            l_orderRowList[0].setForcedSettleReasonType("0");
            l_orderRowList[0].setAccountId(123);
            l_orderRowList[0].setContractType(ContractTypeEnum.LONG);
            Branch l_branch = new BranchImpl(12345L);

            WEB3AdminForcedSettleTemporaryOrderUnit[] l_unit =
                WEB3AdminPMEquityDataManager.createForcedSettleOrderUnitList(l_orderRowList, null);
            assertEquals("12345.023", l_unit[0].marginCollateralRate);
            assertEquals("12", l_unit[0].additionalElapsedDays);
            assertEquals(null, l_unit[0].forcedSettleReason.additionalElapsedDaysUpperLimit);
            assertEquals("0", l_unit[0].forcedSettleReason.forcedSettleReason);
            assertEquals(null, l_unit[0].estimatedAsset);
            assertEquals(null, l_unit[0].forcedSettleReason.marginMaintenanceRate);
             
        }
        catch (Exception l_ex)
        {
            log.debug(l_ex.getMessage(), l_ex);
            fail();
        }
        
        log.exiting(STR_METHOD_NAME);
    }


    public void testCreateForcedSettleOrderUnitList_case0003()
    {
        final String STR_METHOD_NAME = "testCreateForcedSettleOrderUnitList_case0003()";
        log.entering(STR_METHOD_NAME);
        
        try
        {
            TestDBUtility.deleteAll(BranchPreferencesRow.TYPE);
            BranchPreferencesParams l_branchPreferenceParams = TestDBUtility.getBranchPreferencesRow();
            l_branchPreferenceParams.setBranchId(12345);
            l_branchPreferenceParams.setName("margin.forcedsettleorder.basecontractamount");
            TestDBUtility.insertWithDel(l_branchPreferenceParams);
            
            TestDBUtility.deleteAll(BranchRow.TYPE);
            BranchParams l_branchParams = TestDBUtility.getBranchRow();
            l_branchParams.setBranchId(12345L);
            TestDBUtility.insertWithDel(l_branchParams);
       
            TestDBUtility.deleteAll(ProductParams.TYPE);
            ProductParams l_productParams =
                TestDBUtility.getProductRow();
            l_productParams.setProductId(123);
            l_productParams.setProductType(ProductTypeEnum.EQUITY);
            TestDBUtility.insertWithDel(l_productParams);
            
            TestDBUtility.deleteAll(EqtypeProductParams.TYPE);
            EqtypeProductParams l_eqtypeProductParams =
                TestDBUtility.getEqtypeProductRow();
            l_eqtypeProductParams.setProductId(123);
            l_eqtypeProductParams.setProductType(ProductTypeEnum.EQUITY);
            TestDBUtility.insertWithDel(l_eqtypeProductParams);
            
            TestDBUtility.deleteAll(MarketParams.TYPE);
            MarketParams l_marketParams =
                TestDBUtility.getMarketRow();
            l_marketParams.setMarketId(123);
            TestDBUtility.insertWithDel(l_marketParams);
            
            TestDBUtility.deleteAll(MainAccountParams.TYPE);
            MainAccountParams l_mainAccountParams =
                TestDBUtility.getMainAccountRow();
            l_mainAccountParams.setAccountId(123);
            TestDBUtility.insertWithDel(l_mainAccountParams);
            
            TestDBUtility.deleteAll(TradedProductParams.TYPE);
            TradedProductParams l_tradedProductParams =
                TestDBUtility.getTradedProductRow();
            l_tradedProductParams.setMarketId(123);
            l_tradedProductParams.setProductId(123);
            l_tradedProductParams.setProductType(ProductTypeEnum.EQUITY);
            TestDBUtility.insertWithDel(l_tradedProductParams);
            
            TestDBUtility.deleteAll(EqtypeTradedProductParams.TYPE);
            EqtypeTradedProductParams l_eqtypeTradedProductParams =
                TestDBUtility.getEqtypeTradedProductRow();
            l_eqtypeTradedProductParams.setMarketId(125);
            l_eqtypeTradedProductParams.setProductId(123);
            l_eqtypeTradedProductParams.setBasePrice(12345.5002D);
            TestDBUtility.insertWithDel(l_eqtypeTradedProductParams);
            
            AdminEqForcedSettleOrderParams[] l_orderRowList = new AdminEqForcedSettleOrderParams[1];
            l_orderRowList[0] = new AdminEqForcedSettleOrderParams();
            l_orderRowList[0].setBranchId(12345);
            l_orderRowList[0].setProductId(123);
            l_orderRowList[0].setMarketId(123);
            l_orderRowList[0].setMarginMaintenanceRate(12345.023D);
            l_orderRowList[0].setAdditionalMarginAccruedDays(12L);
            l_orderRowList[0].setQuantity(60D);
            l_orderRowList[0].setForcedSettleReasonType("0");
            l_orderRowList[0].setAccountId(123);
            l_orderRowList[0].setContractType(ContractTypeEnum.LONG);
            Branch l_branch = new BranchImpl(12345L);

            WEB3AdminForcedSettleTemporaryOrderUnit[] l_unit =
                WEB3AdminPMEquityDataManager.createForcedSettleOrderUnitList(l_orderRowList, null);

             
        }
        catch (WEB3BaseException l_ex)
        {
            log.debug(l_ex.getErrorMessage(), l_ex);
            assertEquals(WEB3ErrorCatalog.SYSTEM_ERROR_80005, l_ex.getErrorInfo());
        }
        catch (Exception l_ex)
        {
            log.debug(l_ex.getMessage(), l_ex);
            fail();
        }
        
        log.exiting(STR_METHOD_NAME);
    }
    
    public void testCreateForcedSettleOrderUnitList_case0004()
    {
        final String STR_METHOD_NAME = "testCreateForcedSettleOrderUnitList_case0004()";
        log.entering(STR_METHOD_NAME);
        
        try
        {
            TestDBUtility.deleteAll(BranchPreferencesRow.TYPE);
            BranchPreferencesParams l_branchPreferenceParams = TestDBUtility.getBranchPreferencesRow();
            l_branchPreferenceParams.setBranchId(12345);
            l_branchPreferenceParams.setName("margin.forcedsettleorder.basecontractamount");
            TestDBUtility.insertWithDel(l_branchPreferenceParams);
            
            TestDBUtility.deleteAll(BranchRow.TYPE);
            BranchParams l_branchParams = TestDBUtility.getBranchRow();
            l_branchParams.setBranchId(12345L);
            TestDBUtility.insertWithDel(l_branchParams);
       
            TestDBUtility.deleteAll(ProductParams.TYPE);
            ProductParams l_productParams =
                TestDBUtility.getProductRow();
            l_productParams.setProductId(123);
            l_productParams.setProductType(ProductTypeEnum.EQUITY);
            TestDBUtility.insertWithDel(l_productParams);
            
            TestDBUtility.deleteAll(EqtypeProductParams.TYPE);
            EqtypeProductParams l_eqtypeProductParams =
                TestDBUtility.getEqtypeProductRow();
            l_eqtypeProductParams.setProductId(123);
            l_eqtypeProductParams.setProductType(ProductTypeEnum.EQUITY);
            TestDBUtility.insertWithDel(l_eqtypeProductParams);
            
            TestDBUtility.deleteAll(MarketParams.TYPE);
            MarketParams l_marketParams =
                TestDBUtility.getMarketRow();
            l_marketParams.setMarketId(123);
            TestDBUtility.insertWithDel(l_marketParams);
            
            TestDBUtility.deleteAll(MainAccountParams.TYPE);
            MainAccountParams l_mainAccountParams =
                TestDBUtility.getMainAccountRow();
            l_mainAccountParams.setAccountId(123);
            TestDBUtility.insertWithDel(l_mainAccountParams);
            
            TestDBUtility.deleteAll(TradedProductParams.TYPE);
            TradedProductParams l_tradedProductParams =
                TestDBUtility.getTradedProductRow();
            l_tradedProductParams.setMarketId(123);
            l_tradedProductParams.setProductId(123);
            l_tradedProductParams.setProductType(ProductTypeEnum.EQUITY);
            TestDBUtility.insertWithDel(l_tradedProductParams);
            
            TestDBUtility.deleteAll(EqtypeTradedProductParams.TYPE);
            EqtypeTradedProductParams l_eqtypeTradedProductParams =
                TestDBUtility.getEqtypeTradedProductRow();
            l_eqtypeTradedProductParams.setMarketId(125);
            l_eqtypeTradedProductParams.setProductId(123);
            l_eqtypeTradedProductParams.setBasePrice(12345.5002D);
            TestDBUtility.insertWithDel(l_eqtypeTradedProductParams);
            
            AdminEqForcedSettleOrderParams[] l_orderRowList = new AdminEqForcedSettleOrderParams[1];
            l_orderRowList[0] = new AdminEqForcedSettleOrderParams();
            l_orderRowList[0].setBranchId(12345);
            l_orderRowList[0].setProductId(123);
            l_orderRowList[0].setMarketId(123);
            l_orderRowList[0].setMarginMaintenanceRate(12345.023D);
            l_orderRowList[0].setAdditionalMarginAccruedDays(12L);
            l_orderRowList[0].setQuantity(60D);
            l_orderRowList[0].setForcedSettleReasonType("0");
            l_orderRowList[0].setAccountId(123);
            l_orderRowList[0].setContractType(ContractTypeEnum.LONG);
            l_orderRowList[0].setForcedSettleReasonType("3");
            Branch l_branch = new BranchImpl(12345L);

            WEB3AdminForcedSettleTemporaryOrderUnit[] l_unit =
                WEB3AdminPMEquityDataManager.createForcedSettleOrderUnitList(l_orderRowList, null);

             
        }
        catch (WEB3BaseException l_ex)
        {
            log.debug(l_ex.getErrorMessage(), l_ex);
            assertEquals(WEB3ErrorCatalog.SYSTEM_ERROR_80006, l_ex.getErrorInfo());
        }
        catch (Exception l_ex)
        {
            log.debug(l_ex.getMessage(), l_ex);
            fail();
        }
        
        log.exiting(STR_METHOD_NAME);
    }
    
    /**
     * 管理者・株式（PTS）注文詳細Unitにプロパティを設定する。
     * 引数.注文単位.初回注文の注文単位ID != nullの場合
        引数.注文単位.isMarketOrder()の戻り値がtrueの場合
        引数.注文単位.isUnExecuted() == false
     * 
     */
    public void testGetOrderUnitDetailCase0001()
    {
        final String STR_METHOD_NAME = "testGetOrderUnitDetailCase0001()";
        log.entering(STR_METHOD_NAME);
        
        try
        {
            TestDBUtility.deleteAll(EqtypeOrderUnitParams.TYPE);
            EqtypeOrderUnitParams l_eqtypeOrderUnitParams = TestDBUtility.getEqtypeOrderUnitRow();
            l_eqtypeOrderUnitParams.setOrderId(1001L);
            l_eqtypeOrderUnitParams.setOrderOpenStatus(OrderOpenStatusEnum.OPEN);
            l_eqtypeOrderUnitParams.setOrderCateg(OrderCategEnum.ASSET);
            l_eqtypeOrderUnitParams.setOrderType(OrderTypeEnum.EQUITY_BUY);
            l_eqtypeOrderUnitParams.setExecutionConditionType(EqTypeExecutionConditionType.NONE);
            l_eqtypeOrderUnitParams.setTaxType(TaxTypeEnum.NORMAL);
            l_eqtypeOrderUnitParams.setRepaymentType("2");
            l_eqtypeOrderUnitParams.setFirstOrderUnitId(1002);
            l_eqtypeOrderUnitParams.setExpirationDate(WEB3DateUtility.getDate("20080130", "yyyyMMdd"));
            l_eqtypeOrderUnitParams.setPriceConditionType("5");
            l_eqtypeOrderUnitParams.setOrderConditionType("2");
            l_eqtypeOrderUnitParams.setQuantity(111);
            l_eqtypeOrderUnitParams.setPrice(null);
            l_eqtypeOrderUnitParams.setModifyCancelType("7");
            l_eqtypeOrderUnitParams.setReceivedDateTime(WEB3DateUtility.getDate("20080130100000", "yyyyMMddHHmmss"));
            l_eqtypeOrderUnitParams.setBizDate("20080129");
            l_eqtypeOrderUnitParams.setDeliveryDate(WEB3DateUtility.getDate("20080130", "yyyyMMdd"));
            l_eqtypeOrderUnitParams.setExecutedAmount(1000);
            l_eqtypeOrderUnitParams.setExecutedQuantity(400);
            TestDBUtility.insertWithDel(l_eqtypeOrderUnitParams);
            
            //          MarketParams
            TestDBUtility.deleteAll(MarketRow.TYPE);
            MarketParams l_marketParams = TestDBUtility.getMarketRow();
            TestDBUtility.insertWithDel(l_marketParams);
            
            //BranchParams
            TestDBUtility.deleteAll(BranchRow.TYPE);
            BranchParams l_branchParams = TestDBUtility.getBranchRow();
            TestDBUtility.insertWithDel(l_branchParams);
            
            //MainAccountParams
            TestDBUtility.deleteAll(MainAccountRow.TYPE);
            MainAccountParams l_mainAccountParams = TestDBUtility.getMainAccountRow();
            l_mainAccountParams.setAccountId(l_eqtypeOrderUnitParams.getAccountId());
            TestDBUtility.insertWithDel(l_mainAccountParams);
            
            //ProductParams
            TestDBUtility.deleteAll(ProductRow.TYPE);
            ProductParams l_productParams = TestDBUtility.getProductRow();
            l_productParams.setProductId(l_eqtypeOrderUnitParams.getProductId());
            l_productParams.setProductType(ProductTypeEnum.EQUITY);
            TestDBUtility.insertWithDel(l_productParams);
            
            TestDBUtility.deleteAll(EqtypeProductRow.TYPE);
            EqtypeProductParams l_EqtypeProductParams = TestDBUtility.getEqtypeProductRow();
            l_EqtypeProductParams.setProductId(l_eqtypeOrderUnitParams.getProductId());
            TestDBUtility.insertWithDel(l_EqtypeProductParams);
            
            FinApp l_finApp = (FinApp)Services.getService(FinApp.class);
            TradingModule l_tradingModule = l_finApp.getTradingModule(ProductTypeEnum.EQUITY);
            WEB3EquityOrderManager l_orderManager =
                (WEB3EquityOrderManager)l_tradingModule.getOrderManager();
            EqTypeOrderUnit l_orderUnit =
                (EqTypeOrderUnit)l_orderManager.getOrderUnit(l_eqtypeOrderUnitParams.getOrderUnitId());   

            WEB3AdminEquityPTSOrderDetailUnit l_unit =
                WEB3AdminPMEquityDataManager.getOrderUnitDetail(l_orderUnit);
            
            assertEquals("1001", l_unit.orderId);
            assertEquals("381", l_unit.branchCode);
            assertEquals("2512246", l_unit.accountCode);
            assertEquals("N8080", l_unit.productCode);
            assertEquals("SP", l_unit.marketCode);
            assertEquals("0", l_unit.taxType);
            assertEquals("1", l_unit.productDiv);
            assertEquals("1", l_unit.tradingType);
            assertEquals("2", l_unit.repaymentDiv);
            assertEquals("1", l_unit.execCondType);
            assertEquals("20080130", WEB3DateUtility.formatDate(l_unit.expirationDate, "yyyyMMdd"));
            assertEquals("5", l_unit.priceCondType);
            assertEquals("2", l_unit.orderCondType);
            assertEquals("111", l_unit.orderQuantity);
            assertEquals("0", l_unit.orderPriceDiv);
            assertNull(l_unit.limitPrice);
            assertEquals("7", l_unit.changeCancelDiv);
            assertEquals("400", l_unit.execQuantity);
            assertEquals("3", l_unit.execPrice);
            assertEquals("50", l_unit.orderState);
            assertEquals("0", l_unit.execType);
            assertEquals("20080130100000", WEB3DateUtility.formatDate(l_unit.orderDate, "yyyyMMddHHmmss"));
            assertEquals("20080129", WEB3DateUtility.formatDate(l_unit.orderBizDate, "yyyyMMdd"));
            assertEquals("20080130", WEB3DateUtility.formatDate(l_unit.deliveryDate, "yyyyMMdd"));
        }
        catch (Exception l_ex)
        {
            log.error(l_ex.getMessage(), l_ex);
            fail();
        }
        log.info(STR_METHOD_NAME + ">>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>test pass !!");
        log.exiting(TEST_END + STR_METHOD_NAME);
        
    }

    /**
     * 管理者・株式（PTS）注文詳細Unitにプロパティを設定する。
     * 引数.注文単位.初回注文の注文単位ID = nullの場合
        引数.注文単位.isMarketOrder()の戻り値がfalseの場合
     * 
     */
    public void testGetOrderUnitDetailCase0002()
    {
        final String STR_METHOD_NAME = "testGetOrderUnitDetailCase0002()";
        log.entering(STR_METHOD_NAME);
        
        try
        {
            TestDBUtility.deleteAll(EqtypeOrderUnitParams.TYPE);
            EqtypeOrderUnitParams l_eqtypeOrderUnitParams = TestDBUtility.getEqtypeOrderUnitRow();
            l_eqtypeOrderUnitParams.setOrderId(1001L);
            l_eqtypeOrderUnitParams.setOrderOpenStatus(OrderOpenStatusEnum.OPEN);
            l_eqtypeOrderUnitParams.setOrderCateg(OrderCategEnum.ASSET);
            l_eqtypeOrderUnitParams.setOrderType(OrderTypeEnum.EQUITY_BUY);
            l_eqtypeOrderUnitParams.setExecutionConditionType(EqTypeExecutionConditionType.NONE);
            l_eqtypeOrderUnitParams.setTaxType(TaxTypeEnum.NORMAL);
            l_eqtypeOrderUnitParams.setRepaymentType("2");
            l_eqtypeOrderUnitParams.setFirstOrderUnitId(null);
            l_eqtypeOrderUnitParams.setExpirationDate(WEB3DateUtility.getDate("20080130", "yyyyMMdd"));
            l_eqtypeOrderUnitParams.setPriceConditionType("5");
            l_eqtypeOrderUnitParams.setOrderConditionType("2");
            l_eqtypeOrderUnitParams.setQuantity(111);
            l_eqtypeOrderUnitParams.setPrice(100);
            l_eqtypeOrderUnitParams.setLimitPrice(123);
            l_eqtypeOrderUnitParams.setModifyCancelType("7");
            l_eqtypeOrderUnitParams.setReceivedDateTime(WEB3DateUtility.getDate("20080130100000", "yyyyMMddHHmmss"));
            l_eqtypeOrderUnitParams.setBizDate("20080129");
            l_eqtypeOrderUnitParams.setDeliveryDate(WEB3DateUtility.getDate("20080130", "yyyyMMdd"));
            l_eqtypeOrderUnitParams.setExecutedAmount(1000);
            l_eqtypeOrderUnitParams.setExecutedQuantity(400);
            TestDBUtility.insertWithDel(l_eqtypeOrderUnitParams);
            
            //          MarketParams
            TestDBUtility.deleteAll(MarketRow.TYPE);
            MarketParams l_marketParams = TestDBUtility.getMarketRow();
            TestDBUtility.insertWithDel(l_marketParams);
            
            //BranchParams
            TestDBUtility.deleteAll(BranchRow.TYPE);
            BranchParams l_branchParams = TestDBUtility.getBranchRow();
            TestDBUtility.insertWithDel(l_branchParams);
            
            //MainAccountParams
            TestDBUtility.deleteAll(MainAccountRow.TYPE);
            MainAccountParams l_mainAccountParams = TestDBUtility.getMainAccountRow();
            l_mainAccountParams.setAccountId(l_eqtypeOrderUnitParams.getAccountId());
            TestDBUtility.insertWithDel(l_mainAccountParams);
            
            //ProductParams
            TestDBUtility.deleteAll(ProductRow.TYPE);
            ProductParams l_productParams = TestDBUtility.getProductRow();
            l_productParams.setProductId(l_eqtypeOrderUnitParams.getProductId());
            l_productParams.setProductType(ProductTypeEnum.EQUITY);
            TestDBUtility.insertWithDel(l_productParams);
            
            TestDBUtility.deleteAll(EqtypeProductRow.TYPE);
            EqtypeProductParams l_EqtypeProductParams = TestDBUtility.getEqtypeProductRow();
            l_EqtypeProductParams.setProductId(l_eqtypeOrderUnitParams.getProductId());
            TestDBUtility.insertWithDel(l_EqtypeProductParams);
            
            FinApp l_finApp = (FinApp)Services.getService(FinApp.class);
            TradingModule l_tradingModule = l_finApp.getTradingModule(ProductTypeEnum.EQUITY);
            WEB3EquityOrderManager l_orderManager =
                (WEB3EquityOrderManager)l_tradingModule.getOrderManager();
            EqTypeOrderUnit l_orderUnit =
                (EqTypeOrderUnit)l_orderManager.getOrderUnit(l_eqtypeOrderUnitParams.getOrderUnitId());   

            WEB3AdminEquityPTSOrderDetailUnit l_unit =
                WEB3AdminPMEquityDataManager.getOrderUnitDetail(l_orderUnit);
            
            assertEquals("1001", l_unit.orderId);
            assertEquals("381", l_unit.branchCode);
            assertEquals("2512246", l_unit.accountCode);
            assertEquals("N8080", l_unit.productCode);
            assertEquals("SP", l_unit.marketCode);
            assertEquals("0", l_unit.taxType);
            assertEquals("1", l_unit.productDiv);
            assertEquals("1", l_unit.tradingType);
            assertEquals("2", l_unit.repaymentDiv);
            assertEquals("1", l_unit.execCondType);
            assertNull(l_unit.expirationDate);
            assertEquals("5", l_unit.priceCondType);
            assertEquals("2", l_unit.orderCondType);
            assertEquals("111", l_unit.orderQuantity);
            assertEquals("1", l_unit.orderPriceDiv);
            assertEquals("123", l_unit.limitPrice);
            assertEquals("7", l_unit.changeCancelDiv);
            assertEquals("400", l_unit.execQuantity);
            assertEquals("3", l_unit.execPrice);
            assertEquals("1", l_unit.orderState);
            assertEquals("0", l_unit.execType);
            assertEquals("20080130100000", WEB3DateUtility.formatDate(l_unit.orderDate, "yyyyMMddHHmmss"));
            assertEquals("20080129", WEB3DateUtility.formatDate(l_unit.orderBizDate, "yyyyMMdd"));
            assertEquals("20080130", WEB3DateUtility.formatDate(l_unit.deliveryDate, "yyyyMMdd"));
        }
        catch (Exception l_ex)
        {
            log.error(l_ex.getMessage(), l_ex);
            fail();
        }
        log.info(STR_METHOD_NAME + ">>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>test pass !!");
        log.exiting(TEST_END + STR_METHOD_NAME);
    }
    
    /**
     * 管理者・株式（PTS）注文詳細Unitにプロパティを設定する。
     * 參數注文単位為NULL
     */
    public void testGetOrderUnitDetailCase0003()
    {
        final String STR_METHOD_NAME = "testGetOrderUnitDetailCase0003()";
        log.entering(STR_METHOD_NAME);
        
        try
        {
            EqTypeOrderUnit l_orderUnit = null;  

            WEB3AdminPMEquityDataManager.getOrderUnitDetail(l_orderUnit);
            fail();
            
        }
        catch(WEB3BaseException l_ex)
        {
            log.debug(STR_METHOD_NAME, l_ex);
            assertEquals(l_ex.getErrorInfo(),WEB3ErrorCatalog.SYSTEM_ERROR_80017);
            log.info(STR_METHOD_NAME + ">>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>test pass !!");

        }
        catch (Exception l_ex)
        {
            log.error(ERROR + l_ex.getMessage(), l_ex);
            fail();
        }
    }
    
    /**
     * get約定履歴
     * 參數注文単位為NULL
     */
    public void testGetExecHistory0001()
    {
        final String STR_METHOD_NAME = "testGetExecHistory0001()";
        log.entering(STR_METHOD_NAME);
        
        try
        {
            EqTypeOrderUnit l_orderUnit = null;  

            WEB3AdminPMEquityDataManager.getExecHistory(l_orderUnit);
            fail();
            
        }
        catch(WEB3BaseException l_ex)
        {
            log.debug(STR_METHOD_NAME, l_ex);
            assertEquals(l_ex.getErrorInfo(),WEB3ErrorCatalog.SYSTEM_ERROR_80017);
            log.info(STR_METHOD_NAME + ">>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>test pass !!");

        }
        catch (Exception l_ex)
        {
            log.error(ERROR + l_ex.getMessage(), l_ex);
            fail();
        }
    }
    
    /**
     * get約定履歴
     * 株式出来通知キューテーブルから一覧を取得する。 
     * 条件に一致するデータが取得できない場合、nullを返却する。
     */
    public void testGetExecHistory0002()
    {
        final String STR_METHOD_NAME = "testGetExecHistory0002()";
        log.entering(STR_METHOD_NAME);
        
        try
        {
            TestDBUtility.deleteAll(EqtypeOrderUnitParams.TYPE);
            EqtypeOrderUnitParams l_eqtypeOrderUnitParams = TestDBUtility.getEqtypeOrderUnitRow();
            l_eqtypeOrderUnitParams.setOrderRequestNumber("1");
            TestDBUtility.insertWithDel(l_eqtypeOrderUnitParams);

            FinApp l_finApp = (FinApp)Services.getService(FinApp.class);
            TradingModule l_tradingModule = l_finApp.getTradingModule(ProductTypeEnum.EQUITY);
            WEB3EquityOrderManager l_orderManager =
                (WEB3EquityOrderManager)l_tradingModule.getOrderManager();
            EqTypeOrderUnit l_orderUnit =
                (EqTypeOrderUnit)l_orderManager.getOrderUnit(l_eqtypeOrderUnitParams.getOrderUnitId());   
            
            TestDBUtility.deleteAll(HostEquityOrderExecNotifyRow.TYPE);

            WEB3AdminEquityPTSExecHistory[] l_execHistorys =
                WEB3AdminPMEquityDataManager.getExecHistory(l_orderUnit);
            
            assertNull(l_execHistorys);
            
        }
        catch (Exception l_ex)
        {
            log.error(l_ex.getMessage(), l_ex);
            fail();
        }
        log.info(STR_METHOD_NAME + ">>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>test pass !!");
        log.exiting(TEST_END + STR_METHOD_NAME);
    }
    
    /**
     * get約定履歴
     * 株式出来通知キューテーブルから一覧を取得する。 
     * 管理者・株式（PTS）約定履歴インスタンスを生成する。
     * 株式出来通知キューテーブル符合條件1條數據。
     */
    public void testGetExecHistory0003()
    {
        final String STR_METHOD_NAME = "testGetExecHistory0003()";
        log.entering(STR_METHOD_NAME);
        
        try
        {
            TestDBUtility.deleteAll(EqtypeOrderUnitParams.TYPE);
            EqtypeOrderUnitParams l_eqtypeOrderUnitParams = TestDBUtility.getEqtypeOrderUnitRow();
            l_eqtypeOrderUnitParams.setOrderRequestNumber("1");
            TestDBUtility.insertWithDel(l_eqtypeOrderUnitParams);

            TestDBUtility.deleteAll(HostEquityOrderExecNotifyRow.TYPE);
            HostEquityOrderExecNotifyParams l_params = TestDBUtility.getHostEquityOrderExecNotifyRow();
            l_params.setOrderRequestNumber("1");
            l_params.setExecTimestamp(WEB3DateUtility.getDate("20080130", "yyyyMMdd"));
            l_params.setExecPrice(110);
            l_params.setExecQuantity(210);
            l_params.setDealedType("0");
            l_params.setLastUpdater("11");
            l_params.setStatus("1");
            TestDBUtility.insertWithDel(l_params);

            FinApp l_finApp = (FinApp)Services.getService(FinApp.class);
            TradingModule l_tradingModule = l_finApp.getTradingModule(ProductTypeEnum.EQUITY);
            WEB3EquityOrderManager l_orderManager =
                (WEB3EquityOrderManager)l_tradingModule.getOrderManager();
            EqTypeOrderUnit l_orderUnit =
                (EqTypeOrderUnit)l_orderManager.getOrderUnit(l_eqtypeOrderUnitParams.getOrderUnitId());   

            WEB3AdminEquityPTSExecHistory[] l_execHistorys =
                WEB3AdminPMEquityDataManager.getExecHistory(l_orderUnit);

            assertEquals(l_execHistorys.length, 1);
            assertFalse(l_execHistorys[0].cancelFlag);
            assertEquals(WEB3DateUtility.formatDate(
                l_execHistorys[0].executionTimeStamp, "yyyyMMdd"), "20080130");
            assertEquals(l_execHistorys[0].execQuantity, "210");
            assertEquals(l_execHistorys[0].execPrice, "110");
            assertEquals(l_execHistorys[0].updaterCode, "11");
            assertEquals(l_execHistorys[0].inputExecCancelExecDiv, "0");
            assertEquals(l_execHistorys[0].inputExecCancelExecProcDiv, "1"); 
        }
        catch (Exception l_ex)
        {
            log.error(l_ex.getMessage(), l_ex);
            fail();
        }
        log.info(STR_METHOD_NAME + ">>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>test pass !!");
        log.exiting(TEST_END + STR_METHOD_NAME);
    }
    
    /**
     * get約定履歴
     * 株式出来通知キューテーブルから一覧を取得する。 
     * 管理者・株式（PTS）約定履歴インスタンスを生成する。
     * 株式出来通知キューテーブル符合條件3條數據。
     */
    public void testGetExecHistory0004()
    {
        final String STR_METHOD_NAME = "testGetExecHistory0004()";
        log.entering(STR_METHOD_NAME);
        
        try
        {
            TestDBUtility.deleteAll(EqtypeOrderUnitParams.TYPE);
            EqtypeOrderUnitParams l_eqtypeOrderUnitParams = TestDBUtility.getEqtypeOrderUnitRow();
            l_eqtypeOrderUnitParams.setOrderRequestNumber("1");
            TestDBUtility.insertWithDel(l_eqtypeOrderUnitParams);

            TestDBUtility.deleteAll(HostEquityOrderExecNotifyRow.TYPE);
            HostEquityOrderExecNotifyParams l_params = TestDBUtility.getHostEquityOrderExecNotifyRow();
            l_params.setOrderRequestNumber("1");
            l_params.setExecTimestamp(WEB3DateUtility.getDate("20080130", "yyyyMMdd"));
            l_params.setExecPrice(110);
            l_params.setExecQuantity(210);
            l_params.setDealedType("0");
            l_params.setLastUpdater("11");
            l_params.setStatus("1");
            l_params.setCreatedTimestamp(WEB3DateUtility.getDate("20080128", "yyyyMMdd"));
            TestDBUtility.insertWithDel(l_params);

            HostEquityOrderExecNotifyParams l_params1 = TestDBUtility.getHostEquityOrderExecNotifyRow();
            l_params1.setOrderRequestNumber("1");
            l_params1.setExecTimestamp(WEB3DateUtility.getDate("20080130", "yyyyMMdd"));
            l_params1.setExecPrice(111);
            l_params1.setExecQuantity(211);
            l_params1.setDealedType("1");
            l_params1.setLastUpdater("22");
            l_params1.setStatus("2");
            l_params1.setCreatedTimestamp(WEB3DateUtility.getDate("20080129", "yyyyMMdd"));
            TestDBUtility.insertWithDel(l_params1);
            
            HostEquityOrderExecNotifyParams l_params2 = TestDBUtility.getHostEquityOrderExecNotifyRow();
            l_params2.setOrderRequestNumber("1");
            l_params2.setExecTimestamp(WEB3DateUtility.getDate("20080130", "yyyyMMdd"));
            l_params2.setExecPrice(112);
            l_params2.setExecQuantity(212);
            l_params2.setDealedType("3");
            l_params2.setLastUpdater("33");
            l_params2.setStatus("3");
            l_params2.setCreatedTimestamp(WEB3DateUtility.getDate("20080130", "yyyyMMdd"));
            TestDBUtility.insertWithDel(l_params2);
            
            FinApp l_finApp = (FinApp)Services.getService(FinApp.class);
            TradingModule l_tradingModule = l_finApp.getTradingModule(ProductTypeEnum.EQUITY);
            WEB3EquityOrderManager l_orderManager =
                (WEB3EquityOrderManager)l_tradingModule.getOrderManager();
            EqTypeOrderUnit l_orderUnit =
                (EqTypeOrderUnit)l_orderManager.getOrderUnit(l_eqtypeOrderUnitParams.getOrderUnitId());   

            WEB3AdminEquityPTSExecHistory[] l_execHistorys =
                WEB3AdminPMEquityDataManager.getExecHistory(l_orderUnit);

            assertEquals(l_execHistorys.length, 3);
            assertFalse(l_execHistorys[0].cancelFlag);
            assertEquals(WEB3DateUtility.formatDate(
                l_execHistorys[0].executionTimeStamp, "yyyyMMdd"), "20080130");
            assertEquals(l_execHistorys[0].execQuantity, "210");
            assertEquals(l_execHistorys[0].execPrice, "110");
            assertEquals(l_execHistorys[0].updaterCode, "11");
            assertEquals(l_execHistorys[0].inputExecCancelExecDiv, "0");
            assertEquals(l_execHistorys[0].inputExecCancelExecProcDiv, "1");

            assertFalse(l_execHistorys[1].cancelFlag);
            assertEquals(WEB3DateUtility.formatDate(
                l_execHistorys[1].executionTimeStamp, "yyyyMMdd"), "20080130");
            assertEquals(l_execHistorys[1].execQuantity, "211");
            assertEquals(l_execHistorys[1].execPrice, "111");
            assertEquals(l_execHistorys[1].updaterCode, "22");
            assertEquals(l_execHistorys[1].inputExecCancelExecDiv, "1");
            assertEquals(l_execHistorys[1].inputExecCancelExecProcDiv, "2");

            assertFalse(l_execHistorys[2].cancelFlag);
            assertEquals(WEB3DateUtility.formatDate(
                l_execHistorys[2].executionTimeStamp, "yyyyMMdd"), "20080130");
            assertEquals(l_execHistorys[2].execQuantity, "212");
            assertEquals(l_execHistorys[2].execPrice, "112");
            assertEquals(l_execHistorys[2].updaterCode, "33");
            assertEquals(l_execHistorys[2].inputExecCancelExecDiv, "3");
            assertEquals(l_execHistorys[2].inputExecCancelExecProcDiv, "3");
            
        }
        catch (Exception l_ex)
        {
            log.error(l_ex.getMessage(), l_ex);
            fail();
        }
        log.info(STR_METHOD_NAME + ">>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>test pass !!");
        log.exiting(TEST_END + STR_METHOD_NAME);
    }
    
    /**
     * validate出来入力出来取消可能時間帯
     * PTS市場が市場開局時間帯 
         （PTS取引時間管理.is市場開局時間帯()==true）の場合、 処理可能(正常終了)とする。 
     */
    public void testValidateInputCancelExecEnableTimeZoneCase0001()
    {
        final String STR_METHOD_NAME = "testValidateInputCancelExecEnableTimeZoneCase0001()";
        log.entering(STR_METHOD_NAME);
        
        try
        {
            MOCK_MANAGER.setIsMockUsed(true);
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                    "com.fitechlabs.xtrade.plugin.tc.gentrade.stdimpls.TradingSystemImpl",
                    "getBizDate",
                    new Class[] {},
                    WEB3DateUtility.getDate("20070126", "yyyyMMdd"));
            TestDBUtility.deleteAll(TradingTimeRow.TYPE);
            WEB3AdminPMEquityDataManager.validateInputCancelExecEnableTimeZone();
        }
        catch (Exception l_ex)
        {
            log.error(l_ex.getMessage(), l_ex);
            fail();
        }
        log.info(STR_METHOD_NAME + ">>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>test pass !!");
        log.exiting(TEST_END + STR_METHOD_NAME);
    }
    
    /**
     * validate出来入力出来取消可能時間帯
     * PTS市場が閉局（PTS取引時間管理.is市場開局時間帯()==false）の場合
        (PTS)出来終了テーブルを検索する
        出来終了レコードが存在しない　@かつ　@ 
        現在日付　@>　@業務日付の場合、 
        処理可能(正常終了)とする。  
     */
    public void testValidateInputCancelExecEnableTimeZoneCase0002()
    {
        final String STR_METHOD_NAME = "testValidateInputCancelExecEnableTimeZoneCase0002()";
        log.entering(STR_METHOD_NAME);

        MOCK_MANAGER.setIsMockUsed(true);
        try
        {
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                "com.fitechlabs.xtrade.plugin.tc.gentrade.stdimpls.TradingSystemImpl",
                "getBizDate",
                new Class[] {},
                WEB3DateUtility.getDate("20070126", "yyyyMMdd"));
            
            ThreadLocalSystemAttributesRegistry.setAttribute(WEB3EquityPTSTradingTimeManagement.TIMESTAMP_TAG,
                new Timestamp(WEB3DateUtility.getDate("20080127150000", "yyyyMMddHHmmss").getTime()));
            
            TestDBUtility.deleteAll(TradingTimeRow.TYPE);
            WEB3GentradeTradingClendarContext l_context = new WEB3GentradeTradingClendarContext();
            l_context.setInstitutionCode("0D");
            l_context.setBranchCode("381");
            l_context.setProductCode("N8080");
            l_context.setBizDateType("1");
            l_context.setMarketCode("1");
            l_context.setTradingTimeType("01");
            WEB3GentradeTradingTimeManagementForMock.setClendarContext(l_context);
            
            ThreadLocalSystemAttributesRegistry.setAttribute(
                WEB3EquityPTSTradingTimeManagement.TRADING_CAL_CONTEXT_PATH,
                l_context);
            
            TestDBUtility.deleteAll(PtsOrderexecutionEndRow.TYPE);

            WEB3AdminPMEquityDataManager.validateInputCancelExecEnableTimeZone();
        }
        catch (Exception l_ex)
        {
            log.error(l_ex.getMessage(), l_ex);
            fail();
        }
        log.info(STR_METHOD_NAME + ">>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>test pass !!");
        log.exiting(TEST_END + STR_METHOD_NAME);
    }
    
    /**
     * PTS市場が閉局（PTS取引時間管理.is市場開局時間帯()==false）の場合
        (PTS)出来終了テーブルを検索する
        出来終了レコードが存在　@
        処理可能(正常終了)とする。
     */
    public void testValidateInputCancelExecEnableTimeZoneCase0003()
    {
        final String STR_METHOD_NAME = "testValidateInputCancelExecEnableTimeZoneCase0003()";
        log.entering(STR_METHOD_NAME);
        
        try
        {
            ThreadLocalSystemAttributesRegistry.setAttribute(WEB3EquityPTSTradingTimeManagement.TIMESTAMP_TAG,
                new Timestamp(WEB3DateUtility.getDate("20080127150000", "yyyyMMddHHmmss").getTime()));
            
            TestDBUtility.deleteAll(TradingTimeRow.TYPE);
            WEB3GentradeTradingClendarContext l_context = new WEB3GentradeTradingClendarContext();
            l_context.setInstitutionCode("0D");
            l_context.setBranchCode("381");
            l_context.setProductCode("N8080");
            l_context.setBizDateType("1");
            l_context.setMarketCode("1");
            l_context.setTradingTimeType("01");
            WEB3GentradeTradingTimeManagementForMock.setClendarContext(l_context);
            
            ThreadLocalSystemAttributesRegistry.setAttribute(
                WEB3EquityPTSTradingTimeManagement.TRADING_CAL_CONTEXT_PATH,
                l_context);
            
            TestDBUtility.deleteAll(PtsOrderexecutionEndRow.TYPE);
            PtsOrderexecutionEndParams l_ptsOrderexecutionEndParams =
                TestDBUtility.getPtsOrderexecutionEndRow();
            TestDBUtility.insertWithDel(l_ptsOrderexecutionEndParams);

            WEB3AdminPMEquityDataManager.validateInputCancelExecEnableTimeZone();
            fail();
        }
        catch(WEB3BaseException l_ex)
        {
            log.debug(STR_METHOD_NAME, l_ex);
            assertEquals(l_ex.getErrorInfo(),WEB3ErrorCatalog.BUSINESS_ERROR_03015);
            log.info(STR_METHOD_NAME + ">>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>test pass !!");

        }
        catch (Exception l_ex)
        {
            log.error(ERROR + l_ex.getMessage(), l_ex);
            fail();
        }
    }
    
    /**
     * PTS市場が閉局（PTS取引時間管理.is市場開局時間帯()==false）の場合
        (PTS)出来終了テーブルを検索する
        出来終了レコードが存在しない　@かつ
        現在日付　@<　@業務日付の場合、 
     */
    public void testValidateInputCancelExecEnableTimeZoneCase0004()
    {
        final String STR_METHOD_NAME = "testValidateInputCancelExecEnableTimeZoneCase0004()";
        log.entering(STR_METHOD_NAME);
        
        MOCK_MANAGER.setIsMockUsed(true);
        try
        {
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                "com.fitechlabs.xtrade.plugin.tc.gentrade.stdimpls.TradingSystemImpl",
                "getBizDate",
                new Class[] {},
                WEB3DateUtility.getDate("20100128", "yyyyMMdd"));

            ThreadLocalSystemAttributesRegistry.setAttribute(WEB3EquityPTSTradingTimeManagement.TIMESTAMP_TAG,
                new Timestamp(WEB3DateUtility.getDate("20080127150000", "yyyyMMddHHmmss").getTime()));
            
            TestDBUtility.deleteAll(TradingTimeRow.TYPE);
            WEB3GentradeTradingClendarContext l_context = new WEB3GentradeTradingClendarContext();
            l_context.setInstitutionCode("0D");
            l_context.setBranchCode("381");
            l_context.setProductCode("N8080");
            l_context.setBizDateType("1");
            l_context.setMarketCode("1");
            l_context.setTradingTimeType("01");
            WEB3GentradeTradingTimeManagementForMock.setClendarContext(l_context);
            
            ThreadLocalSystemAttributesRegistry.setAttribute(
                WEB3EquityPTSTradingTimeManagement.TRADING_CAL_CONTEXT_PATH,
                l_context);
            
            TestDBUtility.deleteAll(PtsOrderexecutionEndRow.TYPE);

            WEB3AdminPMEquityDataManager.validateInputCancelExecEnableTimeZone();
            fail();
        }
        catch(WEB3BaseException l_ex)
        {
            log.debug(STR_METHOD_NAME, l_ex);
            assertEquals(l_ex.getErrorInfo(),WEB3ErrorCatalog.BUSINESS_ERROR_03015);
            log.info(STR_METHOD_NAME + ">>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>test pass !!");

        }
        catch (Exception l_ex)
        {
            log.error(ERROR + l_ex.getMessage(), l_ex);
            fail();
        }
    }  
    
    /**
     * 注文単位.注文有効状態 == CLOSED（クローズ） かつ  
　@　@　@注文単位.失効区分 == INVALIDATED_BY_MKT（マーケット拒否）かつ  
　@　@　@注文単位.注文エラー理由コード ==  
　@　@　@　@("W001:株式管理者手動失効済")の場合、  
　@　@　@　@"手動失効"を返却する。  
     */
    public void testGetOrderStateCase0001()
    {
        final String STR_METHOD_NAME = "testGetOrderStateCase0001()";
        log.entering(STR_METHOD_NAME);
        
        try
        {
            TestDBUtility.deleteAll(EqtypeOrderUnitRow.TYPE);
            EqtypeOrderUnitParams l_eqtypeOrderUnitParams = TestDBUtility.getEqtypeOrderUnitRow();
            l_eqtypeOrderUnitParams.setOrderOpenStatus(OrderOpenStatusEnum.CLOSED);
            l_eqtypeOrderUnitParams.setExpirationStatus(OrderExpirationStatusEnum.INVALIDATED_BY_MKT);
            l_eqtypeOrderUnitParams.setErrorReasonCode("W001");
            TestDBUtility.insertWithDel(l_eqtypeOrderUnitParams);
            
            EqTypeOrderUnit l_orderUnit = new EqTypeOrderUnitImpl(null, l_eqtypeOrderUnitParams);
            
            String l_strOrderState = WEB3AdminPMEquityDataManager.getOrderState(l_orderUnit);
            assertEquals("23", l_strOrderState);
        }
        catch (Exception l_ex)
        {
            log.error(ERROR + l_ex.getMessage(), l_ex);
            fail();
        }
        log.info(STR_METHOD_NAME + ">>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>test pass !!");
    }
    
    /**
     * 　@１－２）上記以外の場合、 
　@　@　@株式データアダプタ.get注文状態区分(引数.注文単位)メソッドを  
　@　@　@コールし、戻り値を返却する。  
     */
    public void testGetOrderStateCase0002()
    {
        final String STR_METHOD_NAME = "testGetOrderStateCase0002()";
        log.entering(STR_METHOD_NAME);
        
        try
        {
            TestDBUtility.deleteAll(EqtypeOrderUnitRow.TYPE);
            EqtypeOrderUnitParams l_eqtypeOrderUnitParams = TestDBUtility.getEqtypeOrderUnitRow();
            l_eqtypeOrderUnitParams.setOrderOpenStatus(OrderOpenStatusEnum.CLOSED);
            l_eqtypeOrderUnitParams.setExpirationStatus(OrderExpirationStatusEnum.INVALIDATED_BY_MKT);
            l_eqtypeOrderUnitParams.setErrorReasonCode("1001");
            l_eqtypeOrderUnitParams.setOrderStatus(OrderStatusEnum.ACCEPTED);
            TestDBUtility.insertWithDel(l_eqtypeOrderUnitParams);
            
            EqTypeOrderUnit l_orderUnit = new EqTypeOrderUnitImpl(null, l_eqtypeOrderUnitParams);
            
            String l_strOrderState = WEB3AdminPMEquityDataManager.getOrderState(l_orderUnit);
            assertEquals("1", l_strOrderState);
        }
        catch (Exception l_ex)
        {
            log.error(ERROR + l_ex.getMessage(), l_ex);
            fail();
        }
        log.info(STR_METHOD_NAME + ">>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>test pass !!");
    }
}
@
