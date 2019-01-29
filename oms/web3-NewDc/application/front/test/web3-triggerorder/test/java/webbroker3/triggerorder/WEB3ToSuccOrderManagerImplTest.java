head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.04.07.00.42.15;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	5444d9d078c63f4;
filename	WEB3ToSuccOrderManagerImplTest.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@package webbroker3.triggerorder;

import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import com.fitechlabs.xtrade.kernel.boot.Services;
import com.fitechlabs.xtrade.plugin.tc.eqtype.EqTypeOrderUnit;
import com.fitechlabs.xtrade.plugin.tc.eqtype.data.EqtypeOrderUnitParams;
import com.fitechlabs.xtrade.plugin.tc.eqtype.data.EqtypeOrderUnitRow;
import com.fitechlabs.xtrade.plugin.tc.eqtype.stdimpls.EqTypeOrderUnitImpl;
import com.fitechlabs.xtrade.plugin.tc.gentrade.ContractTypeEnum;
import com.fitechlabs.xtrade.plugin.tc.gentrade.FinApp;
import com.fitechlabs.xtrade.plugin.tc.gentrade.GtlUtils;
import com.fitechlabs.xtrade.plugin.tc.gentrade.OrderCategEnum;
import com.fitechlabs.xtrade.plugin.tc.gentrade.OrderExpirationStatusEnum;
import com.fitechlabs.xtrade.plugin.tc.gentrade.OrderOpenStatusEnum;
import com.fitechlabs.xtrade.plugin.tc.gentrade.OrderStatusEnum;
import com.fitechlabs.xtrade.plugin.tc.gentrade.OrderTypeEnum;
import com.fitechlabs.xtrade.plugin.tc.gentrade.OrderUnit;
import com.fitechlabs.xtrade.plugin.tc.gentrade.ProcessingResult;
import com.fitechlabs.xtrade.plugin.tc.gentrade.ProductTypeEnum;
import com.fitechlabs.xtrade.plugin.tc.gentrade.SubAccount;
import com.fitechlabs.xtrade.plugin.tc.gentrade.TaxTypeEnum;
import com.fitechlabs.xtrade.plugin.tc.gentrade.Trader;
import com.fitechlabs.xtrade.plugin.tc.gentrade.data.BranchParams;
import com.fitechlabs.xtrade.plugin.tc.gentrade.data.BranchRow;
import com.fitechlabs.xtrade.plugin.tc.gentrade.data.InstitutionParams;
import com.fitechlabs.xtrade.plugin.tc.gentrade.data.InstitutionRow;
import com.fitechlabs.xtrade.plugin.tc.gentrade.data.MainAccountParams;
import com.fitechlabs.xtrade.plugin.tc.gentrade.data.MainAccountRow;
import com.fitechlabs.xtrade.plugin.tc.gentrade.data.MarketParams;
import com.fitechlabs.xtrade.plugin.tc.gentrade.data.MarketRow;
import com.fitechlabs.xtrade.plugin.tc.gentrade.data.ProductParams;
import com.fitechlabs.xtrade.plugin.tc.gentrade.data.ProductRow;
import com.fitechlabs.xtrade.plugin.tc.gentrade.data.SubAccountParams;
import com.fitechlabs.xtrade.plugin.tc.gentrade.data.SubAccountRow;
import com.fitechlabs.xtrade.plugin.tc.gentrade.data.TradedProductParams;
import com.fitechlabs.xtrade.plugin.tc.gentrade.data.TradedProductRow;
import com.fitechlabs.xtrade.plugin.tc.gentrade.data.TraderParams;
import com.fitechlabs.xtrade.plugin.tc.gentrade.data.TraderRow;
import com.fitechlabs.xtrade.plugin.tc.gentrade.ordersubmitter.io.OrderValidationResult;
import com.fitechlabs.xtrade.plugin.tc.gentrade.ordersubmitter.io.SettleContractEntry;
import com.fitechlabs.xtrade.plugin.tc.xbifo.IfoOrderExecutionConditionType;
import com.fitechlabs.xtrade.plugin.tc.xbifo.IfoOrderUnit;
import com.fitechlabs.xtrade.plugin.tc.xbifo.data.IfoContractParams;
import com.fitechlabs.xtrade.plugin.tc.xbifo.data.IfoContractRow;
import com.fitechlabs.xtrade.plugin.tc.xbifo.data.IfoOrderExecutionParams;
import com.fitechlabs.xtrade.plugin.tc.xbifo.data.IfoOrderExecutionRow;
import com.fitechlabs.xtrade.plugin.tc.xbifo.data.IfoOrderUnitDao;
import com.fitechlabs.xtrade.plugin.tc.xbifo.data.IfoOrderUnitParams;
import com.fitechlabs.xtrade.plugin.tc.xbifo.data.IfoOrderUnitRow;
import com.fitechlabs.xtrade.plugin.tc.xbifo.data.IfoProductParams;
import com.fitechlabs.xtrade.plugin.tc.xbifo.data.IfoProductRow;
import com.fitechlabs.xtrade.plugin.tc.xbifo.data.IfoTradedProductParams;
import com.fitechlabs.xtrade.plugin.tc.xbifo.data.IfoTradedProductRow;
import com.fitechlabs.xtrade.plugin.tc.xbifo.data.IfoTradedProductUpdqParams;
import com.fitechlabs.xtrade.plugin.tc.xbifo.data.IfoTradedProductUpdqRow;
import com.fitechlabs.xtrade.plugin.tc.xbifo.stdimpls.IfoContractOpenOrderUnitImpl;

import test.util.TestDBUtility;

import webbroker3.common.WEB3BaseException;
import webbroker3.common.WEB3BusinessLayerException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.gentrade.WEB3GentradeBizDate;
import webbroker3.gentrade.WEB3GentradeCommission;
import webbroker3.gentrade.WEB3GentradeMainAccount;
import webbroker3.gentrade.WEB3GentradeSubAccount;
import webbroker3.gentrade.WEB3GentradeTrader;
import webbroker3.gentrade.WEB3GentradeTradingTimeManagement;
import webbroker3.gentrade.WEB3GentradeTradingTimeManagementForMock;
import webbroker3.gentrade.data.BranchPreferencesParams;
import webbroker3.gentrade.data.BranchPreferencesRow;
import webbroker3.gentrade.data.EnableOrderConditionParams;
import webbroker3.gentrade.data.EnableOrderConditionRow;
import webbroker3.gentrade.data.TradingTimeParams;
import webbroker3.gentrade.data.TradingTimeRow;
import webbroker3.ifo.WEB3FuturesOrderManagerImpl;
import webbroker3.ifo.WEB3IfoContractImpl;
import webbroker3.ifo.WEB3IfoEstimateDeliveryAmountCalcResult;
import webbroker3.ifo.WEB3IfoOpenContractOrderSpec;
import webbroker3.ifo.WEB3IfoProductImpl;
import webbroker3.ifo.WEB3IfoSettleContractOrderSpec;
import webbroker3.ifo.WEB3IfoTradedProductImpl;
import webbroker3.ifo.message.WEB3FuturesContractReferenceUnit;
import webbroker3.ifo.message.WEB3FuturesOptionsCloseMarginContractUnit;
import webbroker3.ifo.message.WEB3FuturesOptionsContractUnit;
import webbroker3.mock.TestBaseForMock;
import webbroker3.triggerorder.base.data.RsvIfoClosingContractSpecDao;
import webbroker3.triggerorder.base.data.RsvIfoClosingContractSpecParams;
import webbroker3.triggerorder.base.data.RsvIfoClosingContractSpecRow;
import webbroker3.triggerorder.base.data.RsvIfoOrderActionRow;
import webbroker3.triggerorder.base.data.RsvIfoOrderUnitDao;
import webbroker3.triggerorder.base.data.RsvIfoOrderUnitParams;
import webbroker3.triggerorder.base.data.RsvIfoOrderUnitRow;
import webbroker3.triggerorder.util.WEB3ToSuccOrderManagerUtility;
import webbroker3.util.WEB3DateUtility;
import webbroker3.util.WEB3LogUtility;

public class WEB3ToSuccOrderManagerImplTest extends TestBaseForMock
{
    /**
     * ログ出力ユーティリティ。<BR>
     */
    private static WEB3LogUtility log = 
        WEB3LogUtility.getInstance(WEB3ToSuccOrderManagerImpl.class);

    WEB3ToSuccOrderManagerImpl l_impl;
    
    public WEB3ToSuccOrderManagerImplTest(String arg0)
    {
        super(arg0);
    }

    protected void setUp() throws Exception
    {
        l_impl = new WEB3ToSuccOrderManagerImpl();
        WEB3ToSuccOrderManagerUtility.changeOrderManager();
        super.setUp();
    }

    protected void tearDown() throws Exception
    {
        super.tearDown();
    }
//
//    public void testValidateTriggerOrderSettingToParentOrder_t01()
//    {
//        final String STR_METHOD_NAME = "testValidateTriggerOrderSettingToParentOrder_t01()";
//        log.entering(STR_METHOD_NAME);
//        try
//        {
//            TestDBUtility.deleteAll(EqtypeOrderUnitParams.TYPE);
//            EqtypeOrderUnitParams l_eqtypeOrderUnitParams =
//                TestDBUtility.getEqtypeOrderUnitRow();
//            l_eqtypeOrderUnitParams.setOrderOpenStatus(OrderOpenStatusEnum.OPEN);
//            l_eqtypeOrderUnitParams.setOrderCateg(OrderCategEnum.SWAP_MARGIN);
//            l_eqtypeOrderUnitParams.setOrderRequestNumber("1");
//            l_eqtypeOrderUnitParams.setSonarTradedCode("12");
//            l_eqtypeOrderUnitParams.setTraderId(null);
//            l_eqtypeOrderUnitParams.setForcedSettleReasonType("0");
//            l_eqtypeOrderUnitParams.setForcedExpireType("1");
//            l_eqtypeOrderUnitParams.setOrderStatus(OrderStatusEnum.ACCEPTED);
//            TestDBUtility.insertWithDel(l_eqtypeOrderUnitParams);
//            
//            //ProductParams
//            TestDBUtility.deleteAll(ProductParams.TYPE);
//            ProductParams l_productParams = TestDBUtility.getProductRow();
//            l_productParams.setProductId(l_eqtypeOrderUnitParams.getProductId());
//            l_productParams.setProductType(ProductTypeEnum.EQUITY);
//            TestDBUtility.insertWithDel(l_productParams);
//
//            //EqtypeProductParams
//            TestDBUtility.deleteAll(EqtypeProductParams.TYPE);
//            EqtypeProductParams l_eqtypeProductParams =
//                TestDBUtility.getEqtypeProductRow();
//            l_eqtypeProductParams.setProductId(l_eqtypeOrderUnitParams.getProductId());
//            TestDBUtility.insertWithDel(l_eqtypeProductParams);
//            
//            //MarketParams
//            TestDBUtility.deleteAll(MarketParams.TYPE);
//            MarketParams l_marketParams = TestDBUtility.getMarketRow();
//            l_marketParams.setMarketId(l_eqtypeOrderUnitParams.getMarketId());
//            TestDBUtility.insertWithDel(l_marketParams);
//            
//            FinApp l_finApp = (FinApp)Services.getService(FinApp.class);
//            TradingModule l_tradingModule = l_finApp.getTradingModule(ProductTypeEnum.EQUITY);
//            WEB3EquityOrderManager l_orderMgr =
//                (WEB3EquityOrderManager)l_tradingModule.getOrderManager();
//            EqTypeOrderUnit l_eqTypeOrderUnit =
//                (EqTypeOrderUnit)l_orderMgr.getOrderUnit(l_eqtypeOrderUnitParams.getOrderUnitId());
//            
//            l_impl.validateTriggerOrderSettingToParentOrder(l_eqTypeOrderUnit);
//            fail();
//        }
//        catch(WEB3BusinessLayerException l_bec)
//        {
//            log.exiting(STR_METHOD_NAME);
//            assertEquals(WEB3ErrorCatalog.BUSINESS_ERROR_02808, l_bec.getErrorInfo());
//            log.debug(STR_METHOD_NAME + "----------------->ok");
//        }
//        catch(Exception l_exc)
//        {
//            log.exiting(STR_METHOD_NAME);
//            fail();
//        }
//        log.exiting(STR_METHOD_NAME);
//    }
//
//    public void testValidateTriggerOrderSettingToParentOrder_t02()
//    {
//        final String STR_METHOD_NAME = "testValidateTriggerOrderSettingToParentOrder_t02()";
//        log.entering(STR_METHOD_NAME);
//        try
//        {
//            TestDBUtility.deleteAll(EqtypeOrderUnitParams.TYPE);
//            EqtypeOrderUnitParams l_eqtypeOrderUnitParams =
//                TestDBUtility.getEqtypeOrderUnitRow();
//            l_eqtypeOrderUnitParams.setOrderOpenStatus(OrderOpenStatusEnum.OPEN);
//            l_eqtypeOrderUnitParams.setOrderCateg(OrderCategEnum.SWAP_MARGIN);
//            l_eqtypeOrderUnitParams.setOrderRequestNumber("1");
//            l_eqtypeOrderUnitParams.setSonarTradedCode("12");
//            l_eqtypeOrderUnitParams.setTraderId(null);
//            l_eqtypeOrderUnitParams.setForcedSettleReasonType(null);
//            l_eqtypeOrderUnitParams.setForcedExpireType("1");
//            l_eqtypeOrderUnitParams.setOrderStatus(OrderStatusEnum.ACCEPTED);
//            TestDBUtility.insertWithDel(l_eqtypeOrderUnitParams);
//            
//            //ProductParams
//            TestDBUtility.deleteAll(ProductParams.TYPE);
//            ProductParams l_productParams = TestDBUtility.getProductRow();
//            l_productParams.setProductId(l_eqtypeOrderUnitParams.getProductId());
//            l_productParams.setProductType(ProductTypeEnum.EQUITY);
//            TestDBUtility.insertWithDel(l_productParams);
//
//            //EqtypeProductParams
//            TestDBUtility.deleteAll(EqtypeProductParams.TYPE);
//            EqtypeProductParams l_eqtypeProductParams =
//                TestDBUtility.getEqtypeProductRow();
//            l_eqtypeProductParams.setProductId(l_eqtypeOrderUnitParams.getProductId());
//            TestDBUtility.insertWithDel(l_eqtypeProductParams);
//            
//            //MarketParams
//            TestDBUtility.deleteAll(MarketParams.TYPE);
//            MarketParams l_marketParams = TestDBUtility.getMarketRow();
//            l_marketParams.setMarketId(l_eqtypeOrderUnitParams.getMarketId());
//            TestDBUtility.insertWithDel(l_marketParams);
//            
//            FinApp l_finApp = (FinApp)Services.getService(FinApp.class);
//            TradingModule l_tradingModule = l_finApp.getTradingModule(ProductTypeEnum.EQUITY);
//            WEB3EquityOrderManager l_orderMgr =
//                (WEB3EquityOrderManager)l_tradingModule.getOrderManager();
//            EqTypeOrderUnit l_eqTypeOrderUnit =
//                (EqTypeOrderUnit)l_orderMgr.getOrderUnit(l_eqtypeOrderUnitParams.getOrderUnitId());
//
//            l_impl.validateTriggerOrderSettingToParentOrder(l_eqTypeOrderUnit);
//            log.debug(STR_METHOD_NAME + "----------------->ok");
//        }
//        catch(Exception l_exc)
//        {
//            log.exiting(STR_METHOD_NAME);
//            fail();
//        }
//        log.exiting(STR_METHOD_NAME);
//    }
//    
    /**
     * get有効先物OP子注文単位一覧
     * 指定された親注文に紐付く、有効な先物OP予約注文単位オブジェクトの配列を返却する。
         有効な予約注文単位を取得1條
     */
    public void testGetOpenReserveIfoOrderUnitsCase0001()
    {
        final String STR_METHOD_NAME = "testGetOpenReserveIfoOrderUnitsCase0001()";
        log.entering(STR_METHOD_NAME);
        
        try
        {
            TestDBUtility.deleteAll(RsvIfoOrderUnitRow.TYPE);
            RsvIfoOrderUnitParams l_rsvIfoOrderUnitParams = TestDBUtility.getRsvIfoOrderUnitRow();
            l_rsvIfoOrderUnitParams.setOrderId(1002);
            l_rsvIfoOrderUnitParams.setParentOrderId(1001);
            TestDBUtility.insertWithDel(l_rsvIfoOrderUnitParams);
            
            WEB3ToSuccIfoOrderUnitImpl[] l_ifoOrderUnitImpls = l_impl.getOpenReserveIfoOrderUnits(1001);
            
            assertEquals(l_ifoOrderUnitImpls[0].getOrderId(), 1002);
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
     * get有効先物OP子注文単位一覧
     * 指定された親注文に紐付く、有効な先物OP予約注文単位オブジェクトの配列を返却する。
     有効な予約注文単位を取不到。
     */
    public void testGetOpenReserveIfoOrderUnitsCase0002()
    {
        final String STR_METHOD_NAME = "testGetOpenReserveIfoOrderUnitsCase0002()";
        log.entering(STR_METHOD_NAME);
        
        try
        {
            TestDBUtility.deleteAll(RsvIfoOrderUnitRow.TYPE);
            
            WEB3ToSuccIfoOrderUnitImpl[] l_ifoOrderUnitImpls = l_impl.getOpenReserveIfoOrderUnits(1001);
            
            assertNull(l_ifoOrderUnitImpls);
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
     * get有効先物OP子注文単位一覧
     * 指定された親注文に紐付く、有効な先物OP予約注文単位オブジェクトの配列を返却する。
     有効な予約注文単位を取得3條
     */
    public void testGetOpenReserveIfoOrderUnitsCase0003()
    {
        final String STR_METHOD_NAME = "testGetOpenReserveIfoOrderUnitsCase0003()";
        log.entering(STR_METHOD_NAME);
        
        try
        {
            TestDBUtility.deleteAll(RsvIfoOrderUnitRow.TYPE);
            RsvIfoOrderUnitParams l_rsvIfoOrderUnitParams = TestDBUtility.getRsvIfoOrderUnitRow();
            l_rsvIfoOrderUnitParams.setOrderId(1002);
            l_rsvIfoOrderUnitParams.setParentOrderId(1001);
            l_rsvIfoOrderUnitParams.setSerialNoInParent(1);
            TestDBUtility.insertWithDel(l_rsvIfoOrderUnitParams);
            
            RsvIfoOrderUnitParams l_rsvIfoOrderUnitParams1 = TestDBUtility.getRsvIfoOrderUnitRow();
            l_rsvIfoOrderUnitParams1.setOrderId(1003);
            l_rsvIfoOrderUnitParams1.setParentOrderId(1001);
            l_rsvIfoOrderUnitParams1.setSerialNoInParent(3);
            TestDBUtility.insertWithDel(l_rsvIfoOrderUnitParams1);
            
            RsvIfoOrderUnitParams l_rsvIfoOrderUnitParams2 = TestDBUtility.getRsvIfoOrderUnitRow();
            l_rsvIfoOrderUnitParams2.setOrderId(1004);
            l_rsvIfoOrderUnitParams2.setParentOrderId(1001);
            l_rsvIfoOrderUnitParams2.setSerialNoInParent(2);
            TestDBUtility.insertWithDel(l_rsvIfoOrderUnitParams2);
            WEB3ToSuccIfoOrderUnitImpl[] l_ifoOrderUnitImpls = l_impl.getOpenReserveIfoOrderUnits(1001);
            
            assertEquals(l_ifoOrderUnitImpls[0].getOrderId(), 1002);
            assertEquals(l_ifoOrderUnitImpls[1].getOrderId(), 1004);
            assertEquals(l_ifoOrderUnitImpls[2].getOrderId(), 1003);
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
     * get先物OP予約注文執行単価
     * 引数の単価調整値 == null の場合は、引数の指値をそのまま返却する。 
     */
    public void testGetReserveIfoOrderExecPriceCase0001()
    {
        final String STR_METHOD_NAME = "testGetReserveIfoOrderExecPriceCase0001()";
        log.entering(STR_METHOD_NAME);
        
        try
        {            
            TestDBUtility.deleteAll(IfoOrderUnitRow.TYPE);
            IfoOrderUnitParams l_ifoOrderUnitParams = TestDBUtility.getIfoOrderUnitRow();
            TestDBUtility.insertWithDel(l_ifoOrderUnitParams);
            IfoOrderUnit l_ifoOrderUnit = new IfoContractOpenOrderUnitImpl(1001);
            
            assertEquals("100.0", l_impl.getReserveIfoOrderExecPrice(l_ifoOrderUnit, 100, null, null) + "");
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
     * get先物OP予約注文執行単価
     * 親注文が全部約定している場合
        子注文がマイナス指定の場合
        親注文の注文単位.isFullyExecuted()==true
        引数の単価調整値の符号==マイナス
        執行単価を求める
     */
    public void testGetReserveIfoOrderExecPriceCase0002()
    {
        final String STR_METHOD_NAME = "testGetReserveIfoOrderExecPriceCase0002()";
        log.entering(STR_METHOD_NAME);
        
        try
        {            
            TestDBUtility.deleteAll(IfoOrderUnitRow.TYPE);
            IfoOrderUnitParams l_ifoOrderUnitParams = TestDBUtility.getIfoOrderUnitRow();
            l_ifoOrderUnitParams.setConfirmedQuantity(123);
            l_ifoOrderUnitParams.setExecutedQuantity(123);
            TestDBUtility.insertWithDel(l_ifoOrderUnitParams);
            IfoOrderUnit l_ifoOrderUnit = new IfoContractOpenOrderUnitImpl(1001);
            
            TestDBUtility.deleteAll(IfoOrderExecutionRow.TYPE);
            IfoOrderExecutionParams l_ifoOrderExecutionParams = TestDBUtility.getIfoOrderExecutionRow();
            l_ifoOrderExecutionParams.setOrderExecutionId(4567);
            l_ifoOrderExecutionParams.setExecPrice(200);
            l_ifoOrderExecutionParams.setExecSerialNo(1);
            TestDBUtility.insertWithDel(l_ifoOrderExecutionParams);
            
            IfoOrderExecutionParams l_ifoOrderExecutionParams1 = TestDBUtility.getIfoOrderExecutionRow();
            l_ifoOrderExecutionParams1.setOrderExecutionId(1234);
            l_ifoOrderExecutionParams1.setExecPrice(100);
            l_ifoOrderExecutionParams1.setExecSerialNo(3);
            TestDBUtility.insertWithDel(l_ifoOrderExecutionParams1);
            
            IfoOrderExecutionParams l_ifoOrderExecutionParams2 = TestDBUtility.getIfoOrderExecutionRow();
            l_ifoOrderExecutionParams2.setOrderExecutionId(2345);
            l_ifoOrderExecutionParams2.setExecPrice(300);
            l_ifoOrderExecutionParams2.setExecSerialNo(2);
            TestDBUtility.insertWithDel(l_ifoOrderExecutionParams2);
            
            Double l_priceAdjustValue = new Double(-10);

            assertEquals("90.0", l_impl.getReserveIfoOrderExecPrice(l_ifoOrderUnit, 100, l_priceAdjustValue, null) + "");
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
     * get先物OP予約注文執行単価
     * 親注文が全部約定している場合
        子注文がプラス指定の場合
        親注文の注文単位.isFullyExecuted()==true
        引数の単価調整値の符号≠マイナス
        執行単価を求める
     */
    public void testGetReserveIfoOrderExecPriceCase0003()
    {
        final String STR_METHOD_NAME = "testGetReserveIfoOrderExecPriceCase0003()";
        log.entering(STR_METHOD_NAME);
        
        try
        {            
            TestDBUtility.deleteAll(IfoOrderUnitRow.TYPE);
            IfoOrderUnitParams l_ifoOrderUnitParams = TestDBUtility.getIfoOrderUnitRow();
            l_ifoOrderUnitParams.setConfirmedQuantity(123);
            l_ifoOrderUnitParams.setExecutedQuantity(123);
            TestDBUtility.insertWithDel(l_ifoOrderUnitParams);
            IfoOrderUnit l_ifoOrderUnit = new IfoContractOpenOrderUnitImpl(1001);
            
            TestDBUtility.deleteAll(IfoOrderExecutionRow.TYPE);
            IfoOrderExecutionParams l_ifoOrderExecutionParams = TestDBUtility.getIfoOrderExecutionRow();
            l_ifoOrderExecutionParams.setOrderExecutionId(0001);
            l_ifoOrderExecutionParams.setExecPrice(200);
            l_ifoOrderExecutionParams.setExecSerialNo(1);
            TestDBUtility.insertWithDel(l_ifoOrderExecutionParams);
            
            IfoOrderExecutionParams l_ifoOrderExecutionParams1 = TestDBUtility.getIfoOrderExecutionRow();
            l_ifoOrderExecutionParams1.setOrderExecutionId(1234);
            l_ifoOrderExecutionParams1.setExecPrice(100);
            l_ifoOrderExecutionParams1.setExecSerialNo(3);
            TestDBUtility.insertWithDel(l_ifoOrderExecutionParams1);
            
            IfoOrderExecutionParams l_ifoOrderExecutionParams2 = TestDBUtility.getIfoOrderExecutionRow();
            l_ifoOrderExecutionParams2.setOrderExecutionId(2345);
            l_ifoOrderExecutionParams2.setExecPrice(300);
            l_ifoOrderExecutionParams2.setExecSerialNo(2);
            TestDBUtility.insertWithDel(l_ifoOrderExecutionParams2);
            
            Double l_priceAdjustValue = new Double(10);

            assertEquals("310.0", l_impl.getReserveIfoOrderExecPrice(l_ifoOrderUnit, 100, l_priceAdjustValue, null) + "");
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
     * get先物OP予約注文執行単価
     * 親注文が全部約定以外の場合
        親注文が指値注文の場合
        執行単価を求める
        親注文の注文単位.isFullyExecuted()==false
        親注文の注文単位.isLimitPrice()==true
     */
    public void testGetReserveIfoOrderExecPriceCase0004()
    {
        final String STR_METHOD_NAME = "testGetReserveIfoOrderExecPriceCase0004()";
        log.entering(STR_METHOD_NAME);
        
        try
        {            
            TestDBUtility.deleteAll(IfoOrderUnitRow.TYPE);
            IfoOrderUnitParams l_ifoOrderUnitParams = TestDBUtility.getIfoOrderUnitRow();
            l_ifoOrderUnitParams.setConfirmedQuantity(null);
            l_ifoOrderUnitParams.setExecutedQuantity(456);
            l_ifoOrderUnitParams.setLimitPrice(200);
            TestDBUtility.insertWithDel(l_ifoOrderUnitParams);
            IfoOrderUnit l_ifoOrderUnit = new IfoContractOpenOrderUnitImpl(1001);
            
            TestDBUtility.deleteAll(IfoOrderExecutionRow.TYPE);
            
            Double l_priceAdjustValue = new Double(10);

            assertEquals("210.0", l_impl.getReserveIfoOrderExecPrice(l_ifoOrderUnit, 100, l_priceAdjustValue, null) + "");
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
     * get先物OP予約注文執行単価
     * 親注文が全部約定以外の場合
        親注文が指値注文の場合
        執行単価を求める
        親注文の注文単位.isFullyExecuted()==false
        親注文の注文単位.isLimitPrice()==false
     */
    public void testGetReserveIfoOrderExecPriceCase0005()
    {
        final String STR_METHOD_NAME = "testGetReserveIfoOrderExecPriceCase0005()";
        log.entering(STR_METHOD_NAME);
        
        TestBaseForMock.MOCK_MANAGER.setIsMockUsed(true);
        try
        {            
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                "webbroker3.ifo.WEB3IfoProductManagerImpl",
                "getCurrentPrice",
                new Class[]{ WEB3IfoTradedProductImpl.class},
                new Double(100));

            TestDBUtility.deleteAll(IfoOrderUnitRow.TYPE);
            IfoOrderUnitParams l_ifoOrderUnitParams = TestDBUtility.getIfoOrderUnitRow();
            l_ifoOrderUnitParams.setConfirmedQuantity(null);
            l_ifoOrderUnitParams.setExecutedQuantity(456);
            l_ifoOrderUnitParams.setLimitPrice(null);
            TestDBUtility.insertWithDel(l_ifoOrderUnitParams);
            IfoOrderUnit l_ifoOrderUnit = new IfoContractOpenOrderUnitImpl(1001);
            
            Double l_priceAdjustValue = new Double(10);

            assertEquals("110.0", l_impl.getReserveIfoOrderExecPrice(l_ifoOrderUnit, 100, l_priceAdjustValue, null) + "");
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
     * get先物OP予約注文執行単価
     * 求めた執行単価が0以下の場合は、「執行単価が0以下」の例外をthrowする。
     */
    public void testGetReserveIfoOrderExecPriceCase0006()
    {
        final String STR_METHOD_NAME = "testGetReserveIfoOrderExecPriceCase0006()";
        log.entering(STR_METHOD_NAME);
        
        try
        {            
            TestDBUtility.deleteAll(IfoOrderUnitRow.TYPE);
            IfoOrderUnitParams l_ifoOrderUnitParams = TestDBUtility.getIfoOrderUnitRow();
            l_ifoOrderUnitParams.setConfirmedQuantity(null);
            l_ifoOrderUnitParams.setExecutedQuantity(456);
            l_ifoOrderUnitParams.setLimitPrice(200);
            TestDBUtility.insertWithDel(l_ifoOrderUnitParams);
            IfoOrderUnit l_ifoOrderUnit = new IfoContractOpenOrderUnitImpl(1001);
            
            TestDBUtility.deleteAll(IfoOrderExecutionRow.TYPE);
            
            Double l_priceAdjustValue = new Double(-310);

            l_impl.getReserveIfoOrderExecPrice(l_ifoOrderUnit, 100, l_priceAdjustValue, null);
            fail();
        }
        catch(WEB3BaseException l_ex)
        {
            log.debug(STR_METHOD_NAME, l_ex);
            assertEquals(l_ex.getErrorInfo(),WEB3ErrorCatalog.BUSINESS_ERROR_02298);
            log.info(STR_METHOD_NAME + ">>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>test pass !!");
            log.exiting(TEST_END + STR_METHOD_NAME);

        }
        catch (Exception l_ex)
        {
            log.error(ERROR + l_ex.getMessage(), l_ex);
            fail();
        }
    }
    
    /**
     * get先物OP親注文の注文単位
     * OP注文マネージャ.getOrderUnits(引数の（親注文）注文ID)をコールする。 
         戻り値の最初の要素を親注文の注文単位オブジェクトとして返却する。
         取到返回。
     */
    public void testGetIfoParentOrderUnitCase0001()
    {
        final String STR_METHOD_NAME = "testGetIfoParentOrderUnitCase0001()";
        log.entering(STR_METHOD_NAME);
        
        try
        {            
            TestDBUtility.deleteAll(IfoOrderUnitRow.TYPE);
            IfoOrderUnitParams l_ifoOrderUnitParams = TestDBUtility.getIfoOrderUnitRow();
            TestDBUtility.insertWithDel(l_ifoOrderUnitParams);

            IfoOrderUnit l_IfoOrderUnit = l_impl.getIfoParentOrderUnit(1001);
            
            assertEquals(1001, l_IfoOrderUnit.getOrderUnitId());

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
     * get先物OP親注文の注文単位
     * OP注文マネージャ.getOrderUnits(引数の（親注文）注文ID)をコールする。 
         戻り値の最初の要素を親注文の注文単位オブジェクトとして返却する。
         取不到返回null;
     */
    public void testGetIfoParentOrderUnitCase0002()
    {
        final String STR_METHOD_NAME = "testGetIfoParentOrderUnitCase0002()";
        log.entering(STR_METHOD_NAME);
        
        try
        {            
            TestDBUtility.deleteAll(IfoOrderUnitRow.TYPE);

            IfoOrderUnit l_IfoOrderUnit = l_impl.getIfoParentOrderUnit(1001);
            
            assertNull(l_IfoOrderUnit);

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
     * submit先物OP新規建新規予約注文
     * 注文チェック.validate取引パスワードisFailedResult
     */
    public void testSubmitIfoOpenContractNewOrderCase0001()
    {
        final String STR_METHOD_NAME = "testSubmitIfoOpenContractNewOrderCase0001()";
        log.entering(STR_METHOD_NAME);

        TestBaseForMock.MOCK_MANAGER.setIsMockUsed(true);
        try
        {
            WEB3GentradeTradingTimeManagementForMock.mockGetOrderBizDate(
                WEB3DateUtility.getDate("20070105", "yyyyMMdd"));
            
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                "webbroker3.gentrade.WEB3GentradeOrderValidator",
                "validateTradingPassword",
                new Class[] { Trader.class, SubAccount.class, String.class },
                new OrderValidationResult(ProcessingResult.newFailedResultInstance(
                    WEB3ErrorCatalog.SYSTEM_ERROR_80003)));

            //SubAccountParams
            TestDBUtility.deleteAll(SubAccountRow.TYPE);
            SubAccountParams l_subAccountParams = TestDBUtility.getSubAccountRow();
            l_subAccountParams.setAccountId(101001010010L);
            l_subAccountParams.setSubAccountId(10100101001007L);
            TestDBUtility.insertWithDel(l_subAccountParams);

            //ProductParams
            TestDBUtility.deleteAll(ProductRow.TYPE);
            ProductParams l_productParams = TestDBUtility.getProductRow();
            l_productParams.setProductId(1006169090018L);
            l_productParams.setProductType(ProductTypeEnum.IFO);
            TestDBUtility.insertWithDel(l_productParams);
            
            TestDBUtility.deleteAll(IfoProductRow.TYPE);
            IfoProductParams l_ifoProductParams = TestDBUtility.getIfoProductRow();
            l_ifoProductParams.setProductId(1006169090018L);
            TestDBUtility.insertWithDel(l_ifoProductParams);
            
            TestDBUtility.deleteAll(IfoOrderUnitRow.TYPE);
            IfoOrderUnitParams l_ifoOrderUnitParams = TestDBUtility.getIfoOrderUnitRow();
            l_ifoOrderUnitParams.setConfirmedQuantity(null);
            l_ifoOrderUnitParams.setExecutedQuantity(456);
            l_ifoOrderUnitParams.setLimitPrice(200);
            l_ifoOrderUnitParams.setOrderId(1004);
            TestDBUtility.insertWithDel(l_ifoOrderUnitParams);
            IfoOrderUnit l_ifoOrderUnit = new IfoContractOpenOrderUnitImpl(1001);
            
            WEB3GentradeSubAccount l_subAccount = new WEB3GentradeSubAccount(l_subAccountParams);
            
            WEB3IfoProductImpl l_product = new WEB3IfoProductImpl(l_ifoProductParams);
            WEB3IfoOpenContractOrderSpec l_ifoOpenContractOrderSpec = 
                WEB3IfoOpenContractOrderSpec.createOpenContractOrderSpec(
                    "0D", null, false, null, l_product,
                    100,  100, null, null, null, 100,
                    100, null, null, null, false);

            long l_lngOrderId = 1001;
            String l_strTradingPassword = null;
            String l_strRsvOrderTradingType = null;
            Double l_priceAdjustValue = null;

            WEB3IfoEstimateDeliveryAmountCalcResult l_ifoEstimateDeliveryAmountCalcResult =
                new WEB3IfoEstimateDeliveryAmountCalcResult();
            
            l_impl.submitIfoOpenContractNewOrder(l_subAccount,
                l_ifoOpenContractOrderSpec,
                l_lngOrderId,
                l_strTradingPassword,
                l_strRsvOrderTradingType,
                l_priceAdjustValue,
                l_ifoOrderUnit,
                l_ifoEstimateDeliveryAmountCalcResult);
            
            fail();
        }
        catch(WEB3BaseException l_ex)
        {
            log.debug(STR_METHOD_NAME, l_ex);
            assertEquals(l_ex.getErrorInfo(),WEB3ErrorCatalog.SYSTEM_ERROR_80003);
            log.info(STR_METHOD_NAME + ">>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>test pass !!");
            log.exiting(TEST_END + STR_METHOD_NAME);

        }
        catch (Exception l_ex)
        {
            log.error(ERROR + l_ex.getMessage(), l_ex);
            fail();
        }
    }
    

    /**
     * submit先物OP新規建新規予約注文
     * パラメータ.親注文の注文単位.先物／オプション区分が"先物"の場合 
         先物OP予約注文単位テーブルにレコードを登録する
         引数の注文内容.isBuyToOpenOrder() == true
         引数の単価調整値 == null
     */
    public void testSubmitIfoOpenContractNewOrderCase0002()
    {
        final String STR_METHOD_NAME = "testSubmitIfoOpenContractNewOrderCase0002()";
        log.entering(STR_METHOD_NAME);

        TestBaseForMock.MOCK_MANAGER.setIsMockUsed(true);
        try
        {
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                "com.fitechlabs.xtrade.plugin.security.oplogin.impl.OpLoginSecurityServiceImpl", 
                "getSessionProperty",
                new Class[] {String.class},
                new String("1"));
            
            WEB3GentradeTradingTimeManagementForMock.mockGetOrderBizDate(
                WEB3DateUtility.getDate("20070105", "yyyyMMdd"));

            //MainAccountParams
            TestDBUtility.deleteAll(MainAccountRow.TYPE);
            MainAccountParams l_mainAccountParams = TestDBUtility.getMainAccountRow();
            l_mainAccountParams.setAccountId(101001010010L);
            l_mainAccountParams.setSonarTraderCode("01");
            TestDBUtility.insertWithDel(l_mainAccountParams);
            
            //SubAccountParams
            TestDBUtility.deleteAll(SubAccountRow.TYPE);
            SubAccountParams l_subAccountParams = TestDBUtility.getSubAccountRow();
            l_subAccountParams.setAccountId(101001010010L);
            l_subAccountParams.setSubAccountId(10100101001007L);
            TestDBUtility.insertWithDel(l_subAccountParams);

            //MarketParams
            TestDBUtility.deleteAll(MarketRow.TYPE);
            MarketParams l_marketParams = TestDBUtility.getMarketRow();
            l_marketParams.setMarketCode("01");
            TestDBUtility.insertWithDel(l_marketParams);
            
            //BranchParams
            TestDBUtility.deleteAll(BranchRow.TYPE);
            BranchParams l_branchParams = TestDBUtility.getBranchRow();
            TestDBUtility.insertWithDel(l_branchParams);

            //InstitutionParams
            TestDBUtility.deleteAll(InstitutionRow.TYPE);
            InstitutionParams l_institutionParams = TestDBUtility.getInstitutionRow();
            TestDBUtility.insertWithDel(l_institutionParams);
            
            //ProductParams
            TestDBUtility.deleteAll(ProductRow.TYPE);
            ProductParams l_productParams = TestDBUtility.getProductRow();
            l_productParams.setProductId(1006169090018L);
            l_productParams.setProductType(ProductTypeEnum.IFO);
            TestDBUtility.insertWithDel(l_productParams);
            
            TestDBUtility.deleteAll(IfoProductRow.TYPE);
            IfoProductParams l_ifoProductParams = TestDBUtility.getIfoProductRow();
            l_ifoProductParams.setProductId(1006169090018L);
            l_ifoProductParams.setInstitutionCode("0D");
            TestDBUtility.insertWithDel(l_ifoProductParams);
            
            TestDBUtility.deleteAll(IfoOrderUnitRow.TYPE);
            IfoOrderUnitParams l_ifoOrderUnitParams = TestDBUtility.getIfoOrderUnitRow();
            l_ifoOrderUnitParams.setConfirmedQuantity(null);
            l_ifoOrderUnitParams.setExecutedQuantity(456);
            l_ifoOrderUnitParams.setLimitPrice(200);
            l_ifoOrderUnitParams.setOrderId(1004);
            TestDBUtility.insertWithDel(l_ifoOrderUnitParams);
            IfoOrderUnit l_ifoOrderUnit = new IfoContractOpenOrderUnitImpl(1001);
            
            WEB3GentradeSubAccount l_subAccount = new WEB3GentradeSubAccount(l_subAccountParams);
            
            WEB3IfoProductImpl l_product = new WEB3IfoProductImpl(l_ifoProductParams);
            WEB3IfoOpenContractOrderSpec l_ifoOpenContractOrderSpec = 
                WEB3IfoOpenContractOrderSpec.createOpenContractOrderSpec(
                    "0D", null, true, "01", l_product,
                    222,  333, null, null, null, 100,
                    100, null, "1", new Long(1002), false);
            l_ifoOpenContractOrderSpec.setProductCode("160030005");

            long l_lngOrderId = 1111;
            String l_strTradingPassword = null;
            String l_strRsvOrderTradingType = "11";
            Double l_priceAdjustValue = null;

            WEB3IfoEstimateDeliveryAmountCalcResult l_ifoEstimateDeliveryAmountCalcResult =
                new WEB3IfoEstimateDeliveryAmountCalcResult();
            l_ifoEstimateDeliveryAmountCalcResult.setCalcUnitPrice(123);
            l_ifoEstimateDeliveryAmountCalcResult.setEstimateDeliveryAmount(234);
            
            TestDBUtility.deleteAll(RsvIfoOrderUnitRow.TYPE);
            TestDBUtility.deleteAll(RsvIfoOrderActionRow.TYPE);
            
            l_impl.submitIfoOpenContractNewOrder(l_subAccount,
                l_ifoOpenContractOrderSpec,
                l_lngOrderId,
                l_strTradingPassword,
                l_strRsvOrderTradingType,
                l_priceAdjustValue,
                l_ifoOrderUnit,
                l_ifoEstimateDeliveryAmountCalcResult);
            
            RsvIfoOrderUnitRow l_rsvIfoOrderUnitRow = RsvIfoOrderUnitDao.findRowByPk(1111);
            assertTrue(l_rsvIfoOrderUnitRow.getOrderUnitIdIsNull());
            assertEquals(101001010010L, l_rsvIfoOrderUnitRow.getAccountId());
            assertEquals(10100101001007L, l_rsvIfoOrderUnitRow.getSubAccountId());
            assertEquals(33381, l_rsvIfoOrderUnitRow.getBranchId());
            assertTrue(l_rsvIfoOrderUnitRow.getTraderIdIsNull());
            assertEquals(601, l_rsvIfoOrderUnitRow.getOrderType().intValue());
            assertEquals(91, l_rsvIfoOrderUnitRow.getOrderCateg().intValue());
            assertEquals(1, l_rsvIfoOrderUnitRow.getLastOrderActionSerialNo());
            assertEquals("11", l_rsvIfoOrderUnitRow.getReserveOrderTradingType());
            assertEquals(6, l_rsvIfoOrderUnitRow.getProductType().intValue());
            assertEquals("1", l_rsvIfoOrderUnitRow.getFutureOptionDiv());
            assertEquals(3303, l_rsvIfoOrderUnitRow.getMarketId());
            assertEquals("222.0", l_rsvIfoOrderUnitRow.getQuantity() + "");
            assertEquals("333.0", l_rsvIfoOrderUnitRow.getLimitPrice() + "");
            assertTrue(l_rsvIfoOrderUnitRow.getPriceAdjustValueIsNull());
            assertEquals("20070105", WEB3DateUtility.formatDate(
                    l_rsvIfoOrderUnitRow.getExpirationDate(), "yyyyMMdd"));
            assertEquals(1, l_rsvIfoOrderUnitRow.getOrderStatus().intValue());
            assertEquals(1, l_rsvIfoOrderUnitRow.getOrderOpenStatus().intValue());
            assertEquals(1, l_rsvIfoOrderUnitRow.getExpirationStatus().intValue());
            assertEquals(0, l_rsvIfoOrderUnitRow.getTaxType().intValue());
            assertEquals("20070105", l_rsvIfoOrderUnitRow.getBizDate());
            assertEquals(1006169090018L, l_rsvIfoOrderUnitRow.getProductId());
            assertEquals("1", l_rsvIfoOrderUnitRow.getOrderChanel());
            assertEquals(0, WEB3DateUtility.compareToSecond(GtlUtils.getSystemTimestamp(),
                    l_rsvIfoOrderUnitRow.getReceivedDateTime()));
            assertEquals("01", l_rsvIfoOrderUnitRow.getSonarTraderCode());
            assertEquals("123.0", l_rsvIfoOrderUnitRow.getPrice() + "");
            assertEquals("234.0", l_rsvIfoOrderUnitRow.getEstimatedPrice() + "");
            assertEquals("1", l_rsvIfoOrderUnitRow.getOrderRootDiv());
            assertNull(l_rsvIfoOrderUnitRow.getClosingOrder());
            assertEquals("0000", l_rsvIfoOrderUnitRow.getErrorReasonCode());
            assertEquals(1002, l_rsvIfoOrderUnitRow.getFirstOrderUnitId());
            assertTrue(l_rsvIfoOrderUnitRow.getFirstOrderIdIsNull());
            assertNull(l_rsvIfoOrderUnitRow.getOrderErrorCode());
            assertEquals(1004, l_rsvIfoOrderUnitRow.getParentOrderId());
            assertEquals(1001, l_rsvIfoOrderUnitRow.getParentOrderUnitId());
            assertEquals(1, l_rsvIfoOrderUnitRow.getSerialNoInParent());
            assertEquals(0, l_rsvIfoOrderUnitRow.getEveningSessionCarryoverFlag().intValue());
            assertEquals(WEB3GentradeTradingTimeManagement.getSessionType(),
                    l_rsvIfoOrderUnitRow.getSessionType());
            assertEquals("1", l_rsvIfoOrderUnitRow.getExpirationDateType());
            assertEquals(0, WEB3DateUtility.compareToSecond(GtlUtils.getSystemTimestamp(),
                    l_rsvIfoOrderUnitRow.getCreatedTimestamp()));
            assertEquals(0, WEB3DateUtility.compareToSecond(GtlUtils.getSystemTimestamp(),
                    l_rsvIfoOrderUnitRow.getLastUpdatedTimestamp()));
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
     * submit先物OP新規建新規予約注文
     * パラメータ.親注文の注文単位.先物／オプション区分が"先物"の場合 
         先物OP予約注文単位テーブルにレコードを登録する
         引数の注文内容.isBuyToOpenOrder() != true
         引数の単価調整値 != null
     */
    public void testSubmitIfoOpenContractNewOrderCase0003()
    {
        final String STR_METHOD_NAME = "testSubmitIfoOpenContractNewOrderCase0003()";
        log.entering(STR_METHOD_NAME);

        TestBaseForMock.MOCK_MANAGER.setIsMockUsed(true);
        try
        {
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                "com.fitechlabs.xtrade.plugin.security.oplogin.impl.OpLoginSecurityServiceImpl", 
                "getSessionProperty",
                new Class[] {String.class},
                new String("1"));
            
            WEB3GentradeTradingTimeManagementForMock.mockGetOrderBizDate(
                WEB3DateUtility.getDate("20070105", "yyyyMMdd"));

            //MainAccountParams
            TestDBUtility.deleteAll(MainAccountRow.TYPE);
            MainAccountParams l_mainAccountParams = TestDBUtility.getMainAccountRow();
            l_mainAccountParams.setAccountId(101001010010L);
            l_mainAccountParams.setSonarTraderCode("01");
            TestDBUtility.insertWithDel(l_mainAccountParams);
            
            //SubAccountParams
            TestDBUtility.deleteAll(SubAccountRow.TYPE);
            SubAccountParams l_subAccountParams = TestDBUtility.getSubAccountRow();
            l_subAccountParams.setAccountId(101001010010L);
            l_subAccountParams.setSubAccountId(10100101001007L);
            TestDBUtility.insertWithDel(l_subAccountParams);

            //MarketParams
            TestDBUtility.deleteAll(MarketRow.TYPE);
            MarketParams l_marketParams = TestDBUtility.getMarketRow();
            l_marketParams.setMarketCode("01");
            TestDBUtility.insertWithDel(l_marketParams);
            
            //BranchParams
            TestDBUtility.deleteAll(BranchRow.TYPE);
            BranchParams l_branchParams = TestDBUtility.getBranchRow();
            TestDBUtility.insertWithDel(l_branchParams);

            //InstitutionParams
            TestDBUtility.deleteAll(InstitutionRow.TYPE);
            InstitutionParams l_institutionParams = TestDBUtility.getInstitutionRow();
            TestDBUtility.insertWithDel(l_institutionParams);
            
            //ProductParams
            TestDBUtility.deleteAll(ProductRow.TYPE);
            ProductParams l_productParams = TestDBUtility.getProductRow();
            l_productParams.setProductId(1006169090018L);
            l_productParams.setProductType(ProductTypeEnum.IFO);
            TestDBUtility.insertWithDel(l_productParams);
            
            TestDBUtility.deleteAll(IfoProductRow.TYPE);
            IfoProductParams l_ifoProductParams = TestDBUtility.getIfoProductRow();
            l_ifoProductParams.setProductId(1006169090018L);
            l_ifoProductParams.setInstitutionCode("0D");
            TestDBUtility.insertWithDel(l_ifoProductParams);
            
            TestDBUtility.deleteAll(IfoOrderUnitRow.TYPE);
            IfoOrderUnitParams l_ifoOrderUnitParams = TestDBUtility.getIfoOrderUnitRow();
            l_ifoOrderUnitParams.setConfirmedQuantity(null);
            l_ifoOrderUnitParams.setExecutedQuantity(456);
            l_ifoOrderUnitParams.setLimitPrice(200);
            l_ifoOrderUnitParams.setOrderId(1004);
            TestDBUtility.insertWithDel(l_ifoOrderUnitParams);
            IfoOrderUnit l_ifoOrderUnit = new IfoContractOpenOrderUnitImpl(1001);
            
            WEB3GentradeSubAccount l_subAccount = new WEB3GentradeSubAccount(l_subAccountParams);
            
            WEB3IfoProductImpl l_product = new WEB3IfoProductImpl(l_ifoProductParams);
            WEB3IfoOpenContractOrderSpec l_ifoOpenContractOrderSpec = 
                WEB3IfoOpenContractOrderSpec.createOpenContractOrderSpec(
                    "0D", null, false, "01", l_product,
                    222,  333, null, null, null, 100,
                    100, null, "1", new Long(1002), true);
            l_ifoOpenContractOrderSpec.setProductCode("160030005");

            long l_lngOrderId = 1111;
            String l_strTradingPassword = null;
            String l_strRsvOrderTradingType = "11";
            Double l_priceAdjustValue = new Double(100);

            WEB3IfoEstimateDeliveryAmountCalcResult l_ifoEstimateDeliveryAmountCalcResult =
                new WEB3IfoEstimateDeliveryAmountCalcResult();
            l_ifoEstimateDeliveryAmountCalcResult.setCalcUnitPrice(123);
            l_ifoEstimateDeliveryAmountCalcResult.setEstimateDeliveryAmount(234);
            
            TestDBUtility.deleteAll(RsvIfoOrderUnitRow.TYPE);
            TestDBUtility.deleteAll(RsvIfoOrderActionRow.TYPE);
            
            l_impl.submitIfoOpenContractNewOrder(l_subAccount,
                l_ifoOpenContractOrderSpec,
                l_lngOrderId,
                l_strTradingPassword,
                l_strRsvOrderTradingType,
                l_priceAdjustValue,
                l_ifoOrderUnit,
                l_ifoEstimateDeliveryAmountCalcResult);
            
            RsvIfoOrderUnitRow l_rsvIfoOrderUnitRow = RsvIfoOrderUnitDao.findRowByPk(1111);
            assertTrue(l_rsvIfoOrderUnitRow.getOrderUnitIdIsNull());
            assertEquals(101001010010L, l_rsvIfoOrderUnitRow.getAccountId());
            assertEquals(10100101001007L, l_rsvIfoOrderUnitRow.getSubAccountId());
            assertEquals(33381, l_rsvIfoOrderUnitRow.getBranchId());
            assertTrue(l_rsvIfoOrderUnitRow.getTraderIdIsNull());
            assertEquals(602, l_rsvIfoOrderUnitRow.getOrderType().intValue());
            assertEquals(91, l_rsvIfoOrderUnitRow.getOrderCateg().intValue());
            assertEquals(1, l_rsvIfoOrderUnitRow.getLastOrderActionSerialNo());
            assertEquals("11", l_rsvIfoOrderUnitRow.getReserveOrderTradingType());
            assertEquals(6, l_rsvIfoOrderUnitRow.getProductType().intValue());
            assertEquals("1", l_rsvIfoOrderUnitRow.getFutureOptionDiv());
            assertEquals(3303, l_rsvIfoOrderUnitRow.getMarketId());
            assertEquals("222.0", l_rsvIfoOrderUnitRow.getQuantity() + "");
            assertTrue(l_rsvIfoOrderUnitRow.getLimitPriceIsNull());
            assertEquals("100.0", l_rsvIfoOrderUnitRow.getPriceAdjustValue() + "");
            assertEquals("20070105", WEB3DateUtility.formatDate(
                    l_rsvIfoOrderUnitRow.getExpirationDate(), "yyyyMMdd"));
            assertEquals(1, l_rsvIfoOrderUnitRow.getOrderStatus().intValue());
            assertEquals(1, l_rsvIfoOrderUnitRow.getOrderOpenStatus().intValue());
            assertEquals(1, l_rsvIfoOrderUnitRow.getExpirationStatus().intValue());
            assertEquals(0, l_rsvIfoOrderUnitRow.getTaxType().intValue());
            assertEquals("20070105", l_rsvIfoOrderUnitRow.getBizDate());
            assertEquals(1006169090018L, l_rsvIfoOrderUnitRow.getProductId());
            assertEquals("1", l_rsvIfoOrderUnitRow.getOrderChanel());
            assertEquals(0, WEB3DateUtility.compareToSecond(GtlUtils.getSystemTimestamp(),
                    l_rsvIfoOrderUnitRow.getReceivedDateTime()));
            assertEquals("01", l_rsvIfoOrderUnitRow.getSonarTraderCode());
            assertEquals("123.0", l_rsvIfoOrderUnitRow.getPrice() + "");
            assertEquals("234.0", l_rsvIfoOrderUnitRow.getEstimatedPrice() + "");
            assertEquals("1", l_rsvIfoOrderUnitRow.getOrderRootDiv());
            assertNull(l_rsvIfoOrderUnitRow.getClosingOrder());
            assertEquals("0000", l_rsvIfoOrderUnitRow.getErrorReasonCode());
            assertEquals(1002, l_rsvIfoOrderUnitRow.getFirstOrderUnitId());
            assertTrue(l_rsvIfoOrderUnitRow.getFirstOrderIdIsNull());
            assertNull(l_rsvIfoOrderUnitRow.getOrderErrorCode());
            assertEquals(1004, l_rsvIfoOrderUnitRow.getParentOrderId());
            assertEquals(1001, l_rsvIfoOrderUnitRow.getParentOrderUnitId());
            assertEquals(1, l_rsvIfoOrderUnitRow.getSerialNoInParent());
            assertEquals(1, l_rsvIfoOrderUnitRow.getEveningSessionCarryoverFlag().intValue());
            assertEquals(WEB3GentradeTradingTimeManagement.getSessionType(),
                    l_rsvIfoOrderUnitRow.getSessionType());
            assertEquals("1", l_rsvIfoOrderUnitRow.getExpirationDateType());
            assertEquals(0, WEB3DateUtility.compareToSecond(GtlUtils.getSystemTimestamp(),
                    l_rsvIfoOrderUnitRow.getCreatedTimestamp()));
            assertEquals(0, WEB3DateUtility.compareToSecond(GtlUtils.getSystemTimestamp(),
                    l_rsvIfoOrderUnitRow.getLastUpdatedTimestamp()));
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
     * submit先物OP新規建新規予約注文
     * パラメータ.親注文の注文単位.先物／オプション区分が"オプション"の場合
     先物OP予約注文単位テーブルにレコードを登録する
         引数の注文内容.isBuyToOpenOrder() == true
         引数の単価調整値 == null
     */
    public void testSubmitIfoOpenContractNewOrderCase0004()
    {
        final String STR_METHOD_NAME = "testSubmitIfoOpenContractNewOrderCase0004()";
        log.entering(STR_METHOD_NAME);

        TestBaseForMock.MOCK_MANAGER.setIsMockUsed(true);
        try
        {
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                "com.fitechlabs.xtrade.plugin.security.oplogin.impl.OpLoginSecurityServiceImpl", 
                "getSessionProperty",
                new Class[] {String.class},
                new String("1"));
            
            WEB3GentradeTradingTimeManagementForMock.mockGetOrderBizDate(
                WEB3DateUtility.getDate("20070105", "yyyyMMdd"));

            //MainAccountParams
            TestDBUtility.deleteAll(MainAccountRow.TYPE);
            MainAccountParams l_mainAccountParams = TestDBUtility.getMainAccountRow();
            l_mainAccountParams.setAccountId(101001010010L);
            l_mainAccountParams.setSonarTraderCode("01");
            TestDBUtility.insertWithDel(l_mainAccountParams);
            
            //SubAccountParams
            TestDBUtility.deleteAll(SubAccountRow.TYPE);
            SubAccountParams l_subAccountParams = TestDBUtility.getSubAccountRow();
            l_subAccountParams.setAccountId(101001010010L);
            l_subAccountParams.setSubAccountId(10100101001007L);
            TestDBUtility.insertWithDel(l_subAccountParams);

            //MarketParams
            TestDBUtility.deleteAll(MarketRow.TYPE);
            MarketParams l_marketParams = TestDBUtility.getMarketRow();
            l_marketParams.setMarketCode("01");
            TestDBUtility.insertWithDel(l_marketParams);
            
            //BranchParams
            TestDBUtility.deleteAll(BranchRow.TYPE);
            BranchParams l_branchParams = TestDBUtility.getBranchRow();
            TestDBUtility.insertWithDel(l_branchParams);

            //InstitutionParams
            TestDBUtility.deleteAll(InstitutionRow.TYPE);
            InstitutionParams l_institutionParams = TestDBUtility.getInstitutionRow();
            TestDBUtility.insertWithDel(l_institutionParams);
            
            //ProductParams
            TestDBUtility.deleteAll(ProductRow.TYPE);
            ProductParams l_productParams = TestDBUtility.getProductRow();
            l_productParams.setProductId(1006169090018L);
            l_productParams.setProductType(ProductTypeEnum.IFO);
            TestDBUtility.insertWithDel(l_productParams);
            
            TestDBUtility.deleteAll(IfoProductRow.TYPE);
            IfoProductParams l_ifoProductParams = TestDBUtility.getIfoProductRow();
            l_ifoProductParams.setProductId(1006169090018L);
            l_ifoProductParams.setInstitutionCode("0D");
            TestDBUtility.insertWithDel(l_ifoProductParams);
            
            TestDBUtility.deleteAll(IfoOrderUnitRow.TYPE);
            IfoOrderUnitParams l_ifoOrderUnitParams = TestDBUtility.getIfoOrderUnitRow();
            l_ifoOrderUnitParams.setConfirmedQuantity(null);
            l_ifoOrderUnitParams.setExecutedQuantity(456);
            l_ifoOrderUnitParams.setLimitPrice(200);
            l_ifoOrderUnitParams.setOrderId(1004);
            l_ifoOrderUnitParams.setFutureOptionDiv("2");
            TestDBUtility.insertWithDel(l_ifoOrderUnitParams);
            IfoOrderUnit l_ifoOrderUnit = new IfoContractOpenOrderUnitImpl(1001);
            
            WEB3GentradeSubAccount l_subAccount = new WEB3GentradeSubAccount(l_subAccountParams);
            
            WEB3IfoProductImpl l_product = new WEB3IfoProductImpl(l_ifoProductParams);
            WEB3IfoOpenContractOrderSpec l_ifoOpenContractOrderSpec = 
                WEB3IfoOpenContractOrderSpec.createOpenContractOrderSpec(
                    "0D", null, true, "01", l_product,
                    222,  333, null, null, null, 100,
                    100, null, "1", new Long(1002), false);
            l_ifoOpenContractOrderSpec.setProductCode("160030005");

            long l_lngOrderId = 1111;
            String l_strTradingPassword = null;
            String l_strRsvOrderTradingType = "11";
            Double l_priceAdjustValue = null;

            WEB3IfoEstimateDeliveryAmountCalcResult l_ifoEstimateDeliveryAmountCalcResult =
                new WEB3IfoEstimateDeliveryAmountCalcResult();
            l_ifoEstimateDeliveryAmountCalcResult.setCalcUnitPrice(123);
            l_ifoEstimateDeliveryAmountCalcResult.setEstimateDeliveryAmount(234);
            
            TestDBUtility.deleteAll(RsvIfoOrderUnitRow.TYPE);
            TestDBUtility.deleteAll(RsvIfoOrderActionRow.TYPE);
            
            l_impl.submitIfoOpenContractNewOrder(l_subAccount,
                l_ifoOpenContractOrderSpec,
                l_lngOrderId,
                l_strTradingPassword,
                l_strRsvOrderTradingType,
                l_priceAdjustValue,
                l_ifoOrderUnit,
                l_ifoEstimateDeliveryAmountCalcResult);
            
            RsvIfoOrderUnitRow l_rsvIfoOrderUnitRow = RsvIfoOrderUnitDao.findRowByPk(1111);
            assertTrue(l_rsvIfoOrderUnitRow.getOrderUnitIdIsNull());
            assertEquals(101001010010L, l_rsvIfoOrderUnitRow.getAccountId());
            assertEquals(10100101001007L, l_rsvIfoOrderUnitRow.getSubAccountId());
            assertEquals(33381, l_rsvIfoOrderUnitRow.getBranchId());
            assertTrue(l_rsvIfoOrderUnitRow.getTraderIdIsNull());
            assertEquals(601, l_rsvIfoOrderUnitRow.getOrderType().intValue());
            assertEquals(91, l_rsvIfoOrderUnitRow.getOrderCateg().intValue());
            assertEquals(1, l_rsvIfoOrderUnitRow.getLastOrderActionSerialNo());
            assertEquals("11", l_rsvIfoOrderUnitRow.getReserveOrderTradingType());
            assertEquals(6, l_rsvIfoOrderUnitRow.getProductType().intValue());
            assertEquals("1", l_rsvIfoOrderUnitRow.getFutureOptionDiv());
            assertEquals(3303, l_rsvIfoOrderUnitRow.getMarketId());
            assertEquals("222.0", l_rsvIfoOrderUnitRow.getQuantity() + "");
            assertEquals("333.0", l_rsvIfoOrderUnitRow.getLimitPrice() + "");
            assertTrue(l_rsvIfoOrderUnitRow.getPriceAdjustValueIsNull());
            assertEquals("20070105", WEB3DateUtility.formatDate(
                    l_rsvIfoOrderUnitRow.getExpirationDate(), "yyyyMMdd"));
            assertEquals(1, l_rsvIfoOrderUnitRow.getOrderStatus().intValue());
            assertEquals(1, l_rsvIfoOrderUnitRow.getOrderOpenStatus().intValue());
            assertEquals(1, l_rsvIfoOrderUnitRow.getExpirationStatus().intValue());
            assertEquals(0, l_rsvIfoOrderUnitRow.getTaxType().intValue());
            assertEquals("20070105", l_rsvIfoOrderUnitRow.getBizDate());
            assertEquals(1006169090018L, l_rsvIfoOrderUnitRow.getProductId());
            assertEquals("1", l_rsvIfoOrderUnitRow.getOrderChanel());
            assertEquals(0, WEB3DateUtility.compareToSecond(GtlUtils.getSystemTimestamp(),
                    l_rsvIfoOrderUnitRow.getReceivedDateTime()));
            assertEquals("01", l_rsvIfoOrderUnitRow.getSonarTraderCode());
            assertEquals("123.0", l_rsvIfoOrderUnitRow.getPrice() + "");
            assertEquals("234.0", l_rsvIfoOrderUnitRow.getEstimatedPrice() + "");
            assertEquals("1", l_rsvIfoOrderUnitRow.getOrderRootDiv());
            assertNull(l_rsvIfoOrderUnitRow.getClosingOrder());
            assertEquals("0000", l_rsvIfoOrderUnitRow.getErrorReasonCode());
            assertEquals(1002, l_rsvIfoOrderUnitRow.getFirstOrderUnitId());
            assertTrue(l_rsvIfoOrderUnitRow.getFirstOrderIdIsNull());
            assertNull(l_rsvIfoOrderUnitRow.getOrderErrorCode());
            assertEquals(1004, l_rsvIfoOrderUnitRow.getParentOrderId());
            assertEquals(1001, l_rsvIfoOrderUnitRow.getParentOrderUnitId());
            assertEquals(1, l_rsvIfoOrderUnitRow.getSerialNoInParent());
            assertEquals(0, l_rsvIfoOrderUnitRow.getEveningSessionCarryoverFlag().intValue());
            assertEquals(WEB3GentradeTradingTimeManagement.getSessionType(),
                    l_rsvIfoOrderUnitRow.getSessionType());
            assertEquals("1", l_rsvIfoOrderUnitRow.getExpirationDateType());
            assertEquals(0, WEB3DateUtility.compareToSecond(GtlUtils.getSystemTimestamp(),
                    l_rsvIfoOrderUnitRow.getCreatedTimestamp()));
            assertEquals(0, WEB3DateUtility.compareToSecond(GtlUtils.getSystemTimestamp(),
                    l_rsvIfoOrderUnitRow.getLastUpdatedTimestamp()));
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
     * submit先物OP新規建新規予約注文
     * パラメータ.親注文の注文単位.先物／オプション区分が"オプション"の場合
         先物OP予約注文単位テーブルにレコードを登録する
         引数の注文内容.isBuyToOpenOrder() != true
         引数の単価調整値 != null
     */
    public void testSubmitIfoOpenContractNewOrderCase0005()
    {
        final String STR_METHOD_NAME = "testSubmitIfoOpenContractNewOrderCase0005()";
        log.entering(STR_METHOD_NAME);

        TestBaseForMock.MOCK_MANAGER.setIsMockUsed(true);
        try
        {
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                "com.fitechlabs.xtrade.plugin.security.oplogin.impl.OpLoginSecurityServiceImpl", 
                "getSessionProperty",
                new Class[] {String.class},
                new String("1"));
            
            WEB3GentradeTradingTimeManagementForMock.mockGetOrderBizDate(
                WEB3DateUtility.getDate("20070105", "yyyyMMdd"));

            //MainAccountParams
            TestDBUtility.deleteAll(MainAccountRow.TYPE);
            MainAccountParams l_mainAccountParams = TestDBUtility.getMainAccountRow();
            l_mainAccountParams.setAccountId(101001010010L);
            l_mainAccountParams.setSonarTraderCode("01");
            TestDBUtility.insertWithDel(l_mainAccountParams);
            
            //SubAccountParams
            TestDBUtility.deleteAll(SubAccountRow.TYPE);
            SubAccountParams l_subAccountParams = TestDBUtility.getSubAccountRow();
            l_subAccountParams.setAccountId(101001010010L);
            l_subAccountParams.setSubAccountId(10100101001007L);
            TestDBUtility.insertWithDel(l_subAccountParams);

            //MarketParams
            TestDBUtility.deleteAll(MarketRow.TYPE);
            MarketParams l_marketParams = TestDBUtility.getMarketRow();
            l_marketParams.setMarketCode("01");
            TestDBUtility.insertWithDel(l_marketParams);
            
            //BranchParams
            TestDBUtility.deleteAll(BranchRow.TYPE);
            BranchParams l_branchParams = TestDBUtility.getBranchRow();
            TestDBUtility.insertWithDel(l_branchParams);

            //InstitutionParams
            TestDBUtility.deleteAll(InstitutionRow.TYPE);
            InstitutionParams l_institutionParams = TestDBUtility.getInstitutionRow();
            TestDBUtility.insertWithDel(l_institutionParams);
            
            //ProductParams
            TestDBUtility.deleteAll(ProductRow.TYPE);
            ProductParams l_productParams = TestDBUtility.getProductRow();
            l_productParams.setProductId(1006169090018L);
            l_productParams.setProductType(ProductTypeEnum.IFO);
            TestDBUtility.insertWithDel(l_productParams);
            
            TestDBUtility.deleteAll(IfoProductRow.TYPE);
            IfoProductParams l_ifoProductParams = TestDBUtility.getIfoProductRow();
            l_ifoProductParams.setProductId(1006169090018L);
            l_ifoProductParams.setInstitutionCode("0D");
            TestDBUtility.insertWithDel(l_ifoProductParams);
            
            TestDBUtility.deleteAll(IfoOrderUnitRow.TYPE);
            IfoOrderUnitParams l_ifoOrderUnitParams = TestDBUtility.getIfoOrderUnitRow();
            l_ifoOrderUnitParams.setConfirmedQuantity(null);
            l_ifoOrderUnitParams.setExecutedQuantity(456);
            l_ifoOrderUnitParams.setLimitPrice(200);
            l_ifoOrderUnitParams.setOrderId(1004);
            l_ifoOrderUnitParams.setFutureOptionDiv("2");
            TestDBUtility.insertWithDel(l_ifoOrderUnitParams);
            IfoOrderUnit l_ifoOrderUnit = new IfoContractOpenOrderUnitImpl(1001);
            
            WEB3GentradeSubAccount l_subAccount = new WEB3GentradeSubAccount(l_subAccountParams);
            
            WEB3IfoProductImpl l_product = new WEB3IfoProductImpl(l_ifoProductParams);
            WEB3IfoOpenContractOrderSpec l_ifoOpenContractOrderSpec = 
                WEB3IfoOpenContractOrderSpec.createOpenContractOrderSpec(
                    "0D", null, false, "01", l_product,
                    222,  333, null, null, null, 100,
                    100, null, "1", new Long(1002), true);
            l_ifoOpenContractOrderSpec.setProductCode("160030005");

            long l_lngOrderId = 1111;
            String l_strTradingPassword = null;
            String l_strRsvOrderTradingType = "11";
            Double l_priceAdjustValue = new Double(100);

            WEB3IfoEstimateDeliveryAmountCalcResult l_ifoEstimateDeliveryAmountCalcResult =
                new WEB3IfoEstimateDeliveryAmountCalcResult();
            l_ifoEstimateDeliveryAmountCalcResult.setCalcUnitPrice(123);
            l_ifoEstimateDeliveryAmountCalcResult.setEstimateDeliveryAmount(234);
            
            TestDBUtility.deleteAll(RsvIfoOrderUnitRow.TYPE);
            TestDBUtility.deleteAll(RsvIfoOrderActionRow.TYPE);
            
            l_impl.submitIfoOpenContractNewOrder(l_subAccount,
                l_ifoOpenContractOrderSpec,
                l_lngOrderId,
                l_strTradingPassword,
                l_strRsvOrderTradingType,
                l_priceAdjustValue,
                l_ifoOrderUnit,
                l_ifoEstimateDeliveryAmountCalcResult);
            
            RsvIfoOrderUnitRow l_rsvIfoOrderUnitRow = RsvIfoOrderUnitDao.findRowByPk(1111);

            assertTrue(l_rsvIfoOrderUnitRow.getOrderUnitIdIsNull());
            assertEquals(101001010010L, l_rsvIfoOrderUnitRow.getAccountId());
            assertEquals(10100101001007L, l_rsvIfoOrderUnitRow.getSubAccountId());
            assertEquals(33381, l_rsvIfoOrderUnitRow.getBranchId());
            assertTrue(l_rsvIfoOrderUnitRow.getTraderIdIsNull());
            assertEquals(602, l_rsvIfoOrderUnitRow.getOrderType().intValue());
            assertEquals(91, l_rsvIfoOrderUnitRow.getOrderCateg().intValue());
            assertEquals(1, l_rsvIfoOrderUnitRow.getLastOrderActionSerialNo());
            assertEquals("11", l_rsvIfoOrderUnitRow.getReserveOrderTradingType());
            assertEquals(6, l_rsvIfoOrderUnitRow.getProductType().intValue());
            assertEquals("1", l_rsvIfoOrderUnitRow.getFutureOptionDiv());
            assertEquals(3303, l_rsvIfoOrderUnitRow.getMarketId());
            assertEquals("222.0", l_rsvIfoOrderUnitRow.getQuantity() + "");
            assertTrue(l_rsvIfoOrderUnitRow.getLimitPriceIsNull());
            assertEquals("100.0", l_rsvIfoOrderUnitRow.getPriceAdjustValue() + "");
            assertEquals("20070105", WEB3DateUtility.formatDate(
                    l_rsvIfoOrderUnitRow.getExpirationDate(), "yyyyMMdd"));
            assertEquals(1, l_rsvIfoOrderUnitRow.getOrderStatus().intValue());
            assertEquals(1, l_rsvIfoOrderUnitRow.getOrderOpenStatus().intValue());
            assertEquals(1, l_rsvIfoOrderUnitRow.getExpirationStatus().intValue());
            assertEquals(0, l_rsvIfoOrderUnitRow.getTaxType().intValue());
            assertEquals("20070105", l_rsvIfoOrderUnitRow.getBizDate());
            assertEquals(1006169090018L, l_rsvIfoOrderUnitRow.getProductId());
            assertEquals("1", l_rsvIfoOrderUnitRow.getOrderChanel());
            assertEquals(0, WEB3DateUtility.compareToSecond(GtlUtils.getSystemTimestamp(),
                    l_rsvIfoOrderUnitRow.getReceivedDateTime()));
            assertEquals("01", l_rsvIfoOrderUnitRow.getSonarTraderCode());
            assertEquals("123.0", l_rsvIfoOrderUnitRow.getPrice() + "");
            assertEquals("234.0", l_rsvIfoOrderUnitRow.getEstimatedPrice() + "");
            assertEquals("1", l_rsvIfoOrderUnitRow.getOrderRootDiv());
            assertNull(l_rsvIfoOrderUnitRow.getClosingOrder());
            assertEquals("0000", l_rsvIfoOrderUnitRow.getErrorReasonCode());
            assertEquals(1002, l_rsvIfoOrderUnitRow.getFirstOrderUnitId());
            assertTrue(l_rsvIfoOrderUnitRow.getFirstOrderIdIsNull());
            assertNull(l_rsvIfoOrderUnitRow.getOrderErrorCode());
            assertEquals(1004, l_rsvIfoOrderUnitRow.getParentOrderId());
            assertEquals(1001, l_rsvIfoOrderUnitRow.getParentOrderUnitId());
            assertEquals(1, l_rsvIfoOrderUnitRow.getSerialNoInParent());
            assertEquals(1, l_rsvIfoOrderUnitRow.getEveningSessionCarryoverFlag().intValue());
            assertEquals(WEB3GentradeTradingTimeManagement.getSessionType(),
                    l_rsvIfoOrderUnitRow.getSessionType());
            assertEquals("1", l_rsvIfoOrderUnitRow.getExpirationDateType());
            assertEquals(0, WEB3DateUtility.compareToSecond(GtlUtils.getSystemTimestamp(),
                    l_rsvIfoOrderUnitRow.getCreatedTimestamp()));
            assertEquals(0, WEB3DateUtility.compareToSecond(GtlUtils.getSystemTimestamp(),
                    l_rsvIfoOrderUnitRow.getLastUpdatedTimestamp()));
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
     * submit先物OP返済新規予約注文
     * 注文チェック.validate取引パスワードisFailedResult
     */
    public void testSubmitIfoCloseContractNewOrderCase0001()
    {
        final String STR_METHOD_NAME = "testSubmitIfoCloseContractNewOrderCase0001()";
        log.entering(STR_METHOD_NAME);

        TestBaseForMock.MOCK_MANAGER.setIsMockUsed(true);
        try
        {
            WEB3GentradeTradingTimeManagementForMock.mockGetOrderBizDate(
                WEB3DateUtility.getDate("20070105", "yyyyMMdd"));
            
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                "webbroker3.gentrade.WEB3GentradeOrderValidator",
                "validateTradingPassword",
                new Class[] { Trader.class, SubAccount.class, String.class },
                new OrderValidationResult(ProcessingResult.newFailedResultInstance(
                    WEB3ErrorCatalog.SYSTEM_ERROR_80003)));

            //SubAccountParams
            TestDBUtility.deleteAll(SubAccountRow.TYPE);
            SubAccountParams l_subAccountParams = TestDBUtility.getSubAccountRow();
            l_subAccountParams.setAccountId(101001010010L);
            l_subAccountParams.setSubAccountId(10100101001007L);
            TestDBUtility.insertWithDel(l_subAccountParams);

            //ProductParams
            TestDBUtility.deleteAll(ProductRow.TYPE);
            ProductParams l_productParams = TestDBUtility.getProductRow();
            l_productParams.setProductId(1006169090018L);
            l_productParams.setProductType(ProductTypeEnum.IFO);
            TestDBUtility.insertWithDel(l_productParams);
            
            TestDBUtility.deleteAll(IfoProductRow.TYPE);
            IfoProductParams l_ifoProductParams = TestDBUtility.getIfoProductRow();
            l_ifoProductParams.setProductId(1006169090018L);
            TestDBUtility.insertWithDel(l_ifoProductParams);
            
            TestDBUtility.deleteAll(IfoContractRow.TYPE);
            IfoContractParams l_ifoContractParams = TestDBUtility.getIfoContractRow();
            l_ifoContractParams.setAccountId(101001010010L);
            l_ifoContractParams.setSubAccountId(10100101001007L);
            l_ifoContractParams.setMarketId(3301);
            l_ifoContractParams.setProductId(1006169090018L);
            TestDBUtility.insertWithDel(l_ifoContractParams);
            
            TestDBUtility.deleteAll(IfoOrderUnitRow.TYPE);
            IfoOrderUnitParams l_ifoOrderUnitParams = TestDBUtility.getIfoOrderUnitRow();
            l_ifoOrderUnitParams.setConfirmedQuantity(null);
            l_ifoOrderUnitParams.setExecutedQuantity(456);
            l_ifoOrderUnitParams.setLimitPrice(200);
            l_ifoOrderUnitParams.setOrderId(1004);
            TestDBUtility.insertWithDel(l_ifoOrderUnitParams);
            IfoOrderUnit l_ifoOrderUnit = new IfoContractOpenOrderUnitImpl(1001);
            
            WEB3GentradeSubAccount l_subAccount = new WEB3GentradeSubAccount(l_subAccountParams);
            
            SettleContractEntry[] l_settleContractOrderEntry = new SettleContractEntry[1];
            WEB3IfoSettleContractOrderSpec l_ifoSettleContractOrderSpec = 
                WEB3IfoSettleContractOrderSpec.createSettleContractOrderSpec(
                   "0D", null,100D,IfoOrderExecutionConditionType.AT_MARKET_CLOSE,
                        null,l_settleContractOrderEntry,null,
                        123D, 456D,
                        IfoOrderExecutionConditionType.AT_MARKET_OPEN,
                        null, new Long(1001), false);

            WEB3IfoContractImpl l_ifoContractImpl = new WEB3IfoContractImpl(l_ifoContractParams);
 
            long l_lngOrderId = 1001;
            String l_strTradingPassword = null;
            String l_strRsvOrderTradingType = null;
            Double l_priceAdjustValue = null;
            String l_strClosingOrder = null;

            WEB3IfoEstimateDeliveryAmountCalcResult l_ifoEstimateDeliveryAmountCalcResult =
                new WEB3IfoEstimateDeliveryAmountCalcResult();

            l_impl.submitIfoCloseContractNewOrder(l_subAccount,
                l_ifoSettleContractOrderSpec,
                l_lngOrderId,
                l_strTradingPassword,
                l_strRsvOrderTradingType,
                l_priceAdjustValue,
                l_ifoOrderUnit,
                l_ifoEstimateDeliveryAmountCalcResult,
                l_ifoContractImpl,
                l_strClosingOrder);
            
            fail();
        }
        catch(WEB3BaseException l_ex)
        {
            log.debug(STR_METHOD_NAME, l_ex);
            assertEquals(l_ex.getErrorInfo(),WEB3ErrorCatalog.SYSTEM_ERROR_80003);
            log.info(STR_METHOD_NAME + ">>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>test pass !!");
            log.exiting(TEST_END + STR_METHOD_NAME);

        }
        catch (Exception l_ex)
        {
            log.error(ERROR + l_ex.getMessage(), l_ex);
            fail();
        }
    }
    
    /**
     * submit先物OP返済新規予約注文
     * パラメータ.親注文の注文単位.先物／オプション区分が"先物"の場合
        先物OP予約注文単位テーブルにレコードを登録する。
        反対売買でない場合
        先物OP予約建玉返済指定情報テーブルに 
        　@レコードを登録する。
        引数の建玉.isLong()==true
        引数の単価調整値 == null
     */
    public void testSubmitIfoCloseContractNewOrderCase0002()
    {
        final String STR_METHOD_NAME = "testSubmitIfoCloseContractNewOrderCase0002()";
        log.entering(STR_METHOD_NAME);

        TestBaseForMock.MOCK_MANAGER.setIsMockUsed(true);
        try
        {
            WEB3GentradeTradingTimeManagementForMock.mockGetOrderBizDate(
                WEB3DateUtility.getDate("20070105", "yyyyMMdd"));
            
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                    "com.fitechlabs.xtrade.plugin.security.oplogin.impl.OpLoginSecurityServiceImpl", 
                    "getSessionProperty",
                    new Class[] {String.class},
                    new String("1"));

            //MainAccountParams
            TestDBUtility.deleteAll(MainAccountRow.TYPE);
            MainAccountParams l_mainAccountParams = TestDBUtility.getMainAccountRow();
            l_mainAccountParams.setAccountId(101001010010L);
            l_mainAccountParams.setSonarTraderCode("01");
            TestDBUtility.insertWithDel(l_mainAccountParams);
            
            //SubAccountParams
            TestDBUtility.deleteAll(SubAccountRow.TYPE);
            SubAccountParams l_subAccountParams = TestDBUtility.getSubAccountRow();
            l_subAccountParams.setAccountId(101001010010L);
            l_subAccountParams.setSubAccountId(10100101001007L);
            TestDBUtility.insertWithDel(l_subAccountParams);

            //MarketParams
            TestDBUtility.deleteAll(MarketRow.TYPE);
            MarketParams l_marketParams = TestDBUtility.getMarketRow();
            l_marketParams.setMarketCode("01");
            TestDBUtility.insertWithDel(l_marketParams);
            
            //BranchParams
            TestDBUtility.deleteAll(BranchRow.TYPE);
            BranchParams l_branchParams = TestDBUtility.getBranchRow();
            TestDBUtility.insertWithDel(l_branchParams);

            //InstitutionParams
            TestDBUtility.deleteAll(InstitutionRow.TYPE);
            InstitutionParams l_institutionParams = TestDBUtility.getInstitutionRow();
            TestDBUtility.insertWithDel(l_institutionParams);
            
            //ProductParams
            TestDBUtility.deleteAll(ProductRow.TYPE);
            ProductParams l_productParams = TestDBUtility.getProductRow();
            l_productParams.setProductId(1006169090018L);
            l_productParams.setProductType(ProductTypeEnum.IFO);
            TestDBUtility.insertWithDel(l_productParams);
            
            TestDBUtility.deleteAll(IfoProductRow.TYPE);
            IfoProductParams l_ifoProductParams = TestDBUtility.getIfoProductRow();
            l_ifoProductParams.setProductId(1006169090018L);
            l_ifoProductParams.setInstitutionCode("0D");
            TestDBUtility.insertWithDel(l_ifoProductParams);
            
            TestDBUtility.deleteAll(IfoOrderUnitRow.TYPE);
            IfoOrderUnitParams l_ifoOrderUnitParams = TestDBUtility.getIfoOrderUnitRow();
            l_ifoOrderUnitParams.setConfirmedQuantity(null);
            l_ifoOrderUnitParams.setExecutedQuantity(456);
            l_ifoOrderUnitParams.setLimitPrice(200);
            l_ifoOrderUnitParams.setOrderId(1004);
            TestDBUtility.insertWithDel(l_ifoOrderUnitParams);
            IfoOrderUnit l_ifoOrderUnit = new IfoContractOpenOrderUnitImpl(1001);
            
            WEB3GentradeSubAccount l_subAccount = new WEB3GentradeSubAccount(l_subAccountParams);
            
            TestDBUtility.deleteAll(IfoContractRow.TYPE);
            IfoContractParams l_ifoContractParams = TestDBUtility.getIfoContractRow();
            l_ifoContractParams.setAccountId(101001010010L);
            l_ifoContractParams.setSubAccountId(10100101001007L);
            l_ifoContractParams.setMarketId(3303);
            l_ifoContractParams.setProductId(1006169090018L);
            TestDBUtility.insertWithDel(l_ifoContractParams);

            SettleContractEntry l_settleContractOrderEntry =
                new SettleContractEntry(l_ifoContractParams.getContractId(), 222);
            SettleContractEntry[] l_settleContractOrderEntrys = new SettleContractEntry[1];
            l_settleContractOrderEntrys[0] = l_settleContractOrderEntry;

            WEB3IfoSettleContractOrderSpec l_ifoSettleContractOrderSpec = 
                WEB3IfoSettleContractOrderSpec.createSettleContractOrderSpec(
                   "0D", null,333,IfoOrderExecutionConditionType.AT_MARKET_CLOSE,
                        null,l_settleContractOrderEntrys,null,
                        123D, 456D,
                        IfoOrderExecutionConditionType.AT_MARKET_OPEN,
                        "1", new Long(1002), false);

            WEB3IfoContractImpl l_ifoContractImpl = new WEB3IfoContractImpl(l_ifoContractParams);
 
            long l_lngOrderId = 1111;
            String l_strTradingPassword = null;
            String l_strRsvOrderTradingType = "13";
            Double l_priceAdjustValue = null;
            String l_strClosingOrder = "3";

            WEB3IfoEstimateDeliveryAmountCalcResult l_ifoEstimateDeliveryAmountCalcResult =
                new WEB3IfoEstimateDeliveryAmountCalcResult();
            l_ifoEstimateDeliveryAmountCalcResult.setCalcUnitPrice(123);
            l_ifoEstimateDeliveryAmountCalcResult.setEstimateDeliveryAmount(234);

            TestDBUtility.deleteAll(RsvIfoOrderUnitRow.TYPE);
            TestDBUtility.deleteAll(RsvIfoOrderActionRow.TYPE);
            TestDBUtility.deleteAll(RsvIfoClosingContractSpecRow.TYPE);
            
            l_impl = new WEB3ToSuccOrderManagerImplForTest();
            l_impl.submitIfoCloseContractNewOrder(l_subAccount,
                l_ifoSettleContractOrderSpec,
                l_lngOrderId,
                l_strTradingPassword,
                l_strRsvOrderTradingType,
                l_priceAdjustValue,
                l_ifoOrderUnit,
                l_ifoEstimateDeliveryAmountCalcResult,
                l_ifoContractImpl,
                l_strClosingOrder);
            
            RsvIfoOrderUnitRow l_rsvIfoOrderUnitRow = RsvIfoOrderUnitDao.findRowByPk(1111);
            assertTrue(l_rsvIfoOrderUnitRow.getOrderUnitIdIsNull());
            assertEquals(101001010010L, l_rsvIfoOrderUnitRow.getAccountId());
            assertEquals(10100101001007L, l_rsvIfoOrderUnitRow.getSubAccountId());
            assertEquals(33381, l_rsvIfoOrderUnitRow.getBranchId());
            assertTrue(l_rsvIfoOrderUnitRow.getTraderIdIsNull());
            assertEquals(604, l_rsvIfoOrderUnitRow.getOrderType().intValue());
            assertEquals(92, l_rsvIfoOrderUnitRow.getOrderCateg().intValue());
            assertEquals(1, l_rsvIfoOrderUnitRow.getLastOrderActionSerialNo());
            assertEquals("13", l_rsvIfoOrderUnitRow.getReserveOrderTradingType());
            assertEquals(6, l_rsvIfoOrderUnitRow.getProductType().intValue());
            assertEquals("1", l_rsvIfoOrderUnitRow.getFutureOptionDiv());
            assertEquals(3303, l_rsvIfoOrderUnitRow.getMarketId());
            assertEquals("222.0", l_rsvIfoOrderUnitRow.getQuantity() + "");
            assertEquals("333.0", l_rsvIfoOrderUnitRow.getLimitPrice() + "");
            assertTrue(l_rsvIfoOrderUnitRow.getPriceAdjustValueIsNull());
            assertEquals("20070105", WEB3DateUtility.formatDate(
                    l_rsvIfoOrderUnitRow.getExpirationDate(), "yyyyMMdd"));
            assertEquals(1, l_rsvIfoOrderUnitRow.getOrderStatus().intValue());
            assertEquals(1, l_rsvIfoOrderUnitRow.getOrderOpenStatus().intValue());
            assertEquals(1, l_rsvIfoOrderUnitRow.getExpirationStatus().intValue());
            assertEquals(0, l_rsvIfoOrderUnitRow.getTaxType().intValue());
            assertEquals("20070105", l_rsvIfoOrderUnitRow.getBizDate());
            assertEquals(1006169090018L, l_rsvIfoOrderUnitRow.getProductId());
            assertEquals("1", l_rsvIfoOrderUnitRow.getOrderChanel());
            assertEquals(0, WEB3DateUtility.compareToSecond(GtlUtils.getSystemTimestamp(),
                    l_rsvIfoOrderUnitRow.getReceivedDateTime()));
            assertEquals("01", l_rsvIfoOrderUnitRow.getSonarTraderCode());
            assertEquals("123.0", l_rsvIfoOrderUnitRow.getPrice() + "");
            assertEquals("234.0", l_rsvIfoOrderUnitRow.getEstimatedPrice() + "");
            assertEquals("1", l_rsvIfoOrderUnitRow.getOrderRootDiv());
            assertEquals("3", l_rsvIfoOrderUnitRow.getClosingOrder());
            assertEquals("0000", l_rsvIfoOrderUnitRow.getErrorReasonCode());
            assertEquals(1002, l_rsvIfoOrderUnitRow.getFirstOrderUnitId());
            assertTrue(l_rsvIfoOrderUnitRow.getFirstOrderIdIsNull());
            assertNull(l_rsvIfoOrderUnitRow.getOrderErrorCode());
            assertEquals(1004, l_rsvIfoOrderUnitRow.getParentOrderId());
            assertEquals(1001, l_rsvIfoOrderUnitRow.getParentOrderUnitId());
            assertEquals(1, l_rsvIfoOrderUnitRow.getSerialNoInParent());
            assertEquals(0, l_rsvIfoOrderUnitRow.getEveningSessionCarryoverFlag().intValue());
            assertEquals(WEB3GentradeTradingTimeManagement.getSessionType(),
                    l_rsvIfoOrderUnitRow.getSessionType());
            assertEquals("1", l_rsvIfoOrderUnitRow.getExpirationDateType());
            assertEquals(0, WEB3DateUtility.compareToSecond(GtlUtils.getSystemTimestamp(),
                    l_rsvIfoOrderUnitRow.getCreatedTimestamp()));
            assertEquals(0, WEB3DateUtility.compareToSecond(GtlUtils.getSystemTimestamp(),
                    l_rsvIfoOrderUnitRow.getLastUpdatedTimestamp()));
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
     * submit先物OP返済新規予約注文
     * パラメータ.親注文の注文単位.先物／オプション区分が"先物"の場合
        先物OP予約注文単位テーブルにレコードを登録する。
        反対売買でない場合
        先物OP予約建玉返済指定情報テーブルに 
        　@レコードを登録する。
        引数の建玉.isLong() ！=true
        引数の単価調整値 != null
     */
    public void testSubmitIfoCloseContractNewOrderCase0003()
    {
        final String STR_METHOD_NAME = "testSubmitIfoCloseContractNewOrderCase0003()";
        log.entering(STR_METHOD_NAME);

        TestBaseForMock.MOCK_MANAGER.setIsMockUsed(true);
        try
        {
            WEB3GentradeTradingTimeManagementForMock.mockGetOrderBizDate(
                WEB3DateUtility.getDate("20070105", "yyyyMMdd"));
            
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                    "com.fitechlabs.xtrade.plugin.security.oplogin.impl.OpLoginSecurityServiceImpl", 
                    "getSessionProperty",
                    new Class[] {String.class},
                    new String("1"));

            //MainAccountParams
            TestDBUtility.deleteAll(MainAccountRow.TYPE);
            MainAccountParams l_mainAccountParams = TestDBUtility.getMainAccountRow();
            l_mainAccountParams.setAccountId(101001010010L);
            l_mainAccountParams.setSonarTraderCode("01");
            TestDBUtility.insertWithDel(l_mainAccountParams);
            
            //SubAccountParams
            TestDBUtility.deleteAll(SubAccountRow.TYPE);
            SubAccountParams l_subAccountParams = TestDBUtility.getSubAccountRow();
            l_subAccountParams.setAccountId(101001010010L);
            l_subAccountParams.setSubAccountId(10100101001007L);
            TestDBUtility.insertWithDel(l_subAccountParams);

            //MarketParams
            TestDBUtility.deleteAll(MarketRow.TYPE);
            MarketParams l_marketParams = TestDBUtility.getMarketRow();
            l_marketParams.setMarketCode("01");
            TestDBUtility.insertWithDel(l_marketParams);
            
            //BranchParams
            TestDBUtility.deleteAll(BranchRow.TYPE);
            BranchParams l_branchParams = TestDBUtility.getBranchRow();
            TestDBUtility.insertWithDel(l_branchParams);

            //InstitutionParams
            TestDBUtility.deleteAll(InstitutionRow.TYPE);
            InstitutionParams l_institutionParams = TestDBUtility.getInstitutionRow();
            TestDBUtility.insertWithDel(l_institutionParams);
            
            //ProductParams
            TestDBUtility.deleteAll(ProductRow.TYPE);
            ProductParams l_productParams = TestDBUtility.getProductRow();
            l_productParams.setProductId(1006169090018L);
            l_productParams.setProductType(ProductTypeEnum.IFO);
            TestDBUtility.insertWithDel(l_productParams);
            
            TestDBUtility.deleteAll(IfoProductRow.TYPE);
            IfoProductParams l_ifoProductParams = TestDBUtility.getIfoProductRow();
            l_ifoProductParams.setProductId(1006169090018L);
            l_ifoProductParams.setInstitutionCode("0D");
            TestDBUtility.insertWithDel(l_ifoProductParams);
            
            TestDBUtility.deleteAll(IfoOrderUnitRow.TYPE);
            IfoOrderUnitParams l_ifoOrderUnitParams = TestDBUtility.getIfoOrderUnitRow();
            l_ifoOrderUnitParams.setConfirmedQuantity(null);
            l_ifoOrderUnitParams.setExecutedQuantity(456);
            l_ifoOrderUnitParams.setLimitPrice(200);
            l_ifoOrderUnitParams.setOrderId(1004);
            TestDBUtility.insertWithDel(l_ifoOrderUnitParams);
            IfoOrderUnit l_ifoOrderUnit = new IfoContractOpenOrderUnitImpl(1001);
            
            WEB3GentradeSubAccount l_subAccount = new WEB3GentradeSubAccount(l_subAccountParams);
            
            TestDBUtility.deleteAll(IfoContractRow.TYPE);
            IfoContractParams l_ifoContractParams = TestDBUtility.getIfoContractRow();
            l_ifoContractParams.setAccountId(101001010010L);
            l_ifoContractParams.setSubAccountId(10100101001007L);
            l_ifoContractParams.setMarketId(3303);
            l_ifoContractParams.setProductId(1006169090018L);
            l_ifoContractParams.setContractType(ContractTypeEnum.SHORT);
            TestDBUtility.insertWithDel(l_ifoContractParams);

            SettleContractEntry l_settleContractOrderEntry =
                new SettleContractEntry(l_ifoContractParams.getContractId(), 222);
            SettleContractEntry[] l_settleContractOrderEntrys = new SettleContractEntry[1];
            l_settleContractOrderEntrys[0] = l_settleContractOrderEntry;

            WEB3IfoSettleContractOrderSpec l_ifoSettleContractOrderSpec = 
                WEB3IfoSettleContractOrderSpec.createSettleContractOrderSpec(
                   "0D", null,333,IfoOrderExecutionConditionType.AT_MARKET_CLOSE,
                        null,l_settleContractOrderEntrys,null,
                        123D, 456D,
                        IfoOrderExecutionConditionType.AT_MARKET_OPEN,
                        "1", new Long(1002), false);

            WEB3IfoContractImpl l_ifoContractImpl = new WEB3IfoContractImpl(l_ifoContractParams);
 
            long l_lngOrderId = 1111;
            String l_strTradingPassword = null;
            String l_strRsvOrderTradingType = "13";
            Double l_priceAdjustValue = new Double(100);
            String l_strClosingOrder = null;

            WEB3IfoEstimateDeliveryAmountCalcResult l_ifoEstimateDeliveryAmountCalcResult =
                new WEB3IfoEstimateDeliveryAmountCalcResult();
            l_ifoEstimateDeliveryAmountCalcResult.setCalcUnitPrice(123);
            l_ifoEstimateDeliveryAmountCalcResult.setEstimateDeliveryAmount(234);

            TestDBUtility.deleteAll(RsvIfoOrderUnitRow.TYPE);
            TestDBUtility.deleteAll(RsvIfoOrderActionRow.TYPE);
            TestDBUtility.deleteAll(RsvIfoClosingContractSpecRow.TYPE);
            
            l_impl = new WEB3ToSuccOrderManagerImplForTest();
            l_impl.submitIfoCloseContractNewOrder(l_subAccount,
                l_ifoSettleContractOrderSpec,
                l_lngOrderId,
                l_strTradingPassword,
                l_strRsvOrderTradingType,
                l_priceAdjustValue,
                l_ifoOrderUnit,
                l_ifoEstimateDeliveryAmountCalcResult,
                l_ifoContractImpl,
                l_strClosingOrder);
            
            RsvIfoOrderUnitRow l_rsvIfoOrderUnitRow = RsvIfoOrderUnitDao.findRowByPk(1111);
            assertTrue(l_rsvIfoOrderUnitRow.getOrderUnitIdIsNull());
            assertEquals(101001010010L, l_rsvIfoOrderUnitRow.getAccountId());
            assertEquals(10100101001007L, l_rsvIfoOrderUnitRow.getSubAccountId());
            assertEquals(33381, l_rsvIfoOrderUnitRow.getBranchId());
            assertTrue(l_rsvIfoOrderUnitRow.getTraderIdIsNull());
            assertEquals(603, l_rsvIfoOrderUnitRow.getOrderType().intValue());
            assertEquals(92, l_rsvIfoOrderUnitRow.getOrderCateg().intValue());
            assertEquals(1, l_rsvIfoOrderUnitRow.getLastOrderActionSerialNo());
            assertEquals("13", l_rsvIfoOrderUnitRow.getReserveOrderTradingType());
            assertEquals(6, l_rsvIfoOrderUnitRow.getProductType().intValue());
            assertEquals("1", l_rsvIfoOrderUnitRow.getFutureOptionDiv());
            assertEquals(3303, l_rsvIfoOrderUnitRow.getMarketId());
            assertEquals("222.0", l_rsvIfoOrderUnitRow.getQuantity() + "");
            assertTrue(l_rsvIfoOrderUnitRow.getLimitPriceIsNull());
            assertEquals("100.0", l_rsvIfoOrderUnitRow.getPriceAdjustValue() + "");
            assertEquals("20070105", WEB3DateUtility.formatDate(
                    l_rsvIfoOrderUnitRow.getExpirationDate(), "yyyyMMdd"));
            assertEquals(1, l_rsvIfoOrderUnitRow.getOrderStatus().intValue());
            assertEquals(1, l_rsvIfoOrderUnitRow.getOrderOpenStatus().intValue());
            assertEquals(1, l_rsvIfoOrderUnitRow.getExpirationStatus().intValue());
            assertEquals(0, l_rsvIfoOrderUnitRow.getTaxType().intValue());
            assertEquals("20070105", l_rsvIfoOrderUnitRow.getBizDate());
            assertEquals(1006169090018L, l_rsvIfoOrderUnitRow.getProductId());
            assertEquals("1", l_rsvIfoOrderUnitRow.getOrderChanel());
            assertEquals(0, WEB3DateUtility.compareToSecond(GtlUtils.getSystemTimestamp(),
                    l_rsvIfoOrderUnitRow.getReceivedDateTime()));
            assertEquals("01", l_rsvIfoOrderUnitRow.getSonarTraderCode());
            assertEquals("123.0", l_rsvIfoOrderUnitRow.getPrice() + "");
            assertEquals("234.0", l_rsvIfoOrderUnitRow.getEstimatedPrice() + "");
            assertEquals("1", l_rsvIfoOrderUnitRow.getOrderRootDiv());
            assertNull(l_rsvIfoOrderUnitRow.getClosingOrder());
            assertEquals("0000", l_rsvIfoOrderUnitRow.getErrorReasonCode());
            assertEquals(1002, l_rsvIfoOrderUnitRow.getFirstOrderUnitId());
            assertTrue(l_rsvIfoOrderUnitRow.getFirstOrderIdIsNull());
            assertNull(l_rsvIfoOrderUnitRow.getOrderErrorCode());
            assertEquals(1004, l_rsvIfoOrderUnitRow.getParentOrderId());
            assertEquals(1001, l_rsvIfoOrderUnitRow.getParentOrderUnitId());
            assertEquals(1, l_rsvIfoOrderUnitRow.getSerialNoInParent());
            assertEquals(0, l_rsvIfoOrderUnitRow.getEveningSessionCarryoverFlag().intValue());
            assertEquals(WEB3GentradeTradingTimeManagement.getSessionType(),
                    l_rsvIfoOrderUnitRow.getSessionType());
            assertEquals("1", l_rsvIfoOrderUnitRow.getExpirationDateType());
            assertEquals(0, WEB3DateUtility.compareToSecond(GtlUtils.getSystemTimestamp(),
                    l_rsvIfoOrderUnitRow.getCreatedTimestamp()));
            assertEquals(0, WEB3DateUtility.compareToSecond(GtlUtils.getSystemTimestamp(),
                    l_rsvIfoOrderUnitRow.getLastUpdatedTimestamp()));
            
            RsvIfoClosingContractSpecRow l_rsvIfoClosingContractSpecRow =
                RsvIfoClosingContractSpecDao.findRowByOrderIdContractId(
                    1111, l_ifoContractParams.getContractId());
            
            assertNull(l_rsvIfoClosingContractSpecRow);
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
     * submit先物OP返済新規予約注文
     * パラメータ.親注文の注文単位.先物／オプション区分が"オプション"の場合
       先物OP予約注文単位テーブルにレコードを登録する。
        反対売買でない場合
        先物OP予約建玉返済指定情報テーブルに 
        　@レコードを登録する。
        引数の建玉.isLong()==true
        引数の単価調整値 == null
     */
    public void testSubmitIfoCloseContractNewOrderCase0004()
    {
        final String STR_METHOD_NAME = "testSubmitIfoCloseContractNewOrderCase0004()";
        log.entering(STR_METHOD_NAME);

        TestBaseForMock.MOCK_MANAGER.setIsMockUsed(true);
        try
        {
            WEB3GentradeTradingTimeManagementForMock.mockGetOrderBizDate(
                WEB3DateUtility.getDate("20070105", "yyyyMMdd"));
            
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                    "com.fitechlabs.xtrade.plugin.security.oplogin.impl.OpLoginSecurityServiceImpl", 
                    "getSessionProperty",
                    new Class[] {String.class},
                    new String("1"));

            //MainAccountParams
            TestDBUtility.deleteAll(MainAccountRow.TYPE);
            MainAccountParams l_mainAccountParams = TestDBUtility.getMainAccountRow();
            l_mainAccountParams.setAccountId(101001010010L);
            l_mainAccountParams.setSonarTraderCode("01");
            TestDBUtility.insertWithDel(l_mainAccountParams);
            
            //SubAccountParams
            TestDBUtility.deleteAll(SubAccountRow.TYPE);
            SubAccountParams l_subAccountParams = TestDBUtility.getSubAccountRow();
            l_subAccountParams.setAccountId(101001010010L);
            l_subAccountParams.setSubAccountId(10100101001007L);
            TestDBUtility.insertWithDel(l_subAccountParams);

            //MarketParams
            TestDBUtility.deleteAll(MarketRow.TYPE);
            MarketParams l_marketParams = TestDBUtility.getMarketRow();
            l_marketParams.setMarketCode("01");
            TestDBUtility.insertWithDel(l_marketParams);
            
            //BranchParams
            TestDBUtility.deleteAll(BranchRow.TYPE);
            BranchParams l_branchParams = TestDBUtility.getBranchRow();
            TestDBUtility.insertWithDel(l_branchParams);

            //InstitutionParams
            TestDBUtility.deleteAll(InstitutionRow.TYPE);
            InstitutionParams l_institutionParams = TestDBUtility.getInstitutionRow();
            TestDBUtility.insertWithDel(l_institutionParams);
            
            //ProductParams
            TestDBUtility.deleteAll(ProductRow.TYPE);
            ProductParams l_productParams = TestDBUtility.getProductRow();
            l_productParams.setProductId(1006169090018L);
            l_productParams.setProductType(ProductTypeEnum.IFO);
            TestDBUtility.insertWithDel(l_productParams);
            
            TestDBUtility.deleteAll(IfoProductRow.TYPE);
            IfoProductParams l_ifoProductParams = TestDBUtility.getIfoProductRow();
            l_ifoProductParams.setProductId(1006169090018L);
            l_ifoProductParams.setInstitutionCode("0D");
            TestDBUtility.insertWithDel(l_ifoProductParams);
            
            TestDBUtility.deleteAll(IfoOrderUnitRow.TYPE);
            IfoOrderUnitParams l_ifoOrderUnitParams = TestDBUtility.getIfoOrderUnitRow();
            l_ifoOrderUnitParams.setConfirmedQuantity(null);
            l_ifoOrderUnitParams.setExecutedQuantity(456);
            l_ifoOrderUnitParams.setLimitPrice(200);
            l_ifoOrderUnitParams.setOrderId(1004);
            l_ifoOrderUnitParams.setFutureOptionDiv("2");
            TestDBUtility.insertWithDel(l_ifoOrderUnitParams);
            IfoOrderUnit l_ifoOrderUnit = new IfoContractOpenOrderUnitImpl(1001);
            
            WEB3GentradeSubAccount l_subAccount = new WEB3GentradeSubAccount(l_subAccountParams);
            
            TestDBUtility.deleteAll(IfoContractRow.TYPE);
            IfoContractParams l_ifoContractParams = TestDBUtility.getIfoContractRow();
            l_ifoContractParams.setAccountId(101001010010L);
            l_ifoContractParams.setSubAccountId(10100101001007L);
            l_ifoContractParams.setMarketId(3303);
            l_ifoContractParams.setProductId(1006169090018L);
            TestDBUtility.insertWithDel(l_ifoContractParams);

            SettleContractEntry l_settleContractOrderEntry =
                new SettleContractEntry(l_ifoContractParams.getContractId(), 222);
            SettleContractEntry[] l_settleContractOrderEntrys = new SettleContractEntry[1];
            l_settleContractOrderEntrys[0] = l_settleContractOrderEntry;

            WEB3IfoSettleContractOrderSpec l_ifoSettleContractOrderSpec = 
                WEB3IfoSettleContractOrderSpec.createSettleContractOrderSpec(
                   "0D", null,333,IfoOrderExecutionConditionType.AT_MARKET_CLOSE,
                        null,l_settleContractOrderEntrys,null,
                        123D, 456D,
                        IfoOrderExecutionConditionType.AT_MARKET_OPEN,
                        "1", new Long(1002), false);

            WEB3IfoContractImpl l_ifoContractImpl = new WEB3IfoContractImpl(l_ifoContractParams);
 
            long l_lngOrderId = 1111;
            String l_strTradingPassword = null;
            String l_strRsvOrderTradingType = "13";
            Double l_priceAdjustValue = null;
            String l_strClosingOrder = null;

            WEB3IfoEstimateDeliveryAmountCalcResult l_ifoEstimateDeliveryAmountCalcResult =
                new WEB3IfoEstimateDeliveryAmountCalcResult();
            l_ifoEstimateDeliveryAmountCalcResult.setCalcUnitPrice(123);
            l_ifoEstimateDeliveryAmountCalcResult.setEstimateDeliveryAmount(234);

            TestDBUtility.deleteAll(RsvIfoOrderUnitRow.TYPE);
            TestDBUtility.deleteAll(RsvIfoOrderActionRow.TYPE);
            TestDBUtility.deleteAll(RsvIfoClosingContractSpecRow.TYPE);
            
            l_impl = new WEB3ToSuccOrderManagerImplForTest();
            l_impl.submitIfoCloseContractNewOrder(l_subAccount,
                l_ifoSettleContractOrderSpec,
                l_lngOrderId,
                l_strTradingPassword,
                l_strRsvOrderTradingType,
                l_priceAdjustValue,
                l_ifoOrderUnit,
                l_ifoEstimateDeliveryAmountCalcResult,
                l_ifoContractImpl,
                l_strClosingOrder);
            
            RsvIfoOrderUnitRow l_rsvIfoOrderUnitRow = RsvIfoOrderUnitDao.findRowByPk(1111);
            assertTrue(l_rsvIfoOrderUnitRow.getOrderUnitIdIsNull());
            assertEquals(101001010010L, l_rsvIfoOrderUnitRow.getAccountId());
            assertEquals(10100101001007L, l_rsvIfoOrderUnitRow.getSubAccountId());
            assertEquals(33381, l_rsvIfoOrderUnitRow.getBranchId());
            assertTrue(l_rsvIfoOrderUnitRow.getTraderIdIsNull());
            assertEquals(604, l_rsvIfoOrderUnitRow.getOrderType().intValue());
            assertEquals(92, l_rsvIfoOrderUnitRow.getOrderCateg().intValue());
            assertEquals(1, l_rsvIfoOrderUnitRow.getLastOrderActionSerialNo());
            assertEquals("13", l_rsvIfoOrderUnitRow.getReserveOrderTradingType());
            assertEquals(6, l_rsvIfoOrderUnitRow.getProductType().intValue());
            assertEquals("1", l_rsvIfoOrderUnitRow.getFutureOptionDiv());
            assertEquals(3303, l_rsvIfoOrderUnitRow.getMarketId());
            assertEquals("222.0", l_rsvIfoOrderUnitRow.getQuantity() + "");
            assertEquals("333.0", l_rsvIfoOrderUnitRow.getLimitPrice() + "");
            assertTrue(l_rsvIfoOrderUnitRow.getPriceAdjustValueIsNull());
            assertEquals("20070105", WEB3DateUtility.formatDate(
                    l_rsvIfoOrderUnitRow.getExpirationDate(), "yyyyMMdd"));
            assertEquals(1, l_rsvIfoOrderUnitRow.getOrderStatus().intValue());
            assertEquals(1, l_rsvIfoOrderUnitRow.getOrderOpenStatus().intValue());
            assertEquals(1, l_rsvIfoOrderUnitRow.getExpirationStatus().intValue());
            assertEquals(0, l_rsvIfoOrderUnitRow.getTaxType().intValue());
            assertEquals("20070105", l_rsvIfoOrderUnitRow.getBizDate());
            assertEquals(1006169090018L, l_rsvIfoOrderUnitRow.getProductId());
            assertEquals("1", l_rsvIfoOrderUnitRow.getOrderChanel());
            assertEquals(0, WEB3DateUtility.compareToSecond(GtlUtils.getSystemTimestamp(),
                    l_rsvIfoOrderUnitRow.getReceivedDateTime()));
            assertEquals("01", l_rsvIfoOrderUnitRow.getSonarTraderCode());
            assertEquals("123.0", l_rsvIfoOrderUnitRow.getPrice() + "");
            assertEquals("234.0", l_rsvIfoOrderUnitRow.getEstimatedPrice() + "");
            assertEquals("1", l_rsvIfoOrderUnitRow.getOrderRootDiv());
            assertNull(l_rsvIfoOrderUnitRow.getClosingOrder());
            assertEquals("0000", l_rsvIfoOrderUnitRow.getErrorReasonCode());
            assertEquals(1002, l_rsvIfoOrderUnitRow.getFirstOrderUnitId());
            assertTrue(l_rsvIfoOrderUnitRow.getFirstOrderIdIsNull());
            assertNull(l_rsvIfoOrderUnitRow.getOrderErrorCode());
            assertEquals(1004, l_rsvIfoOrderUnitRow.getParentOrderId());
            assertEquals(1001, l_rsvIfoOrderUnitRow.getParentOrderUnitId());
            assertEquals(1, l_rsvIfoOrderUnitRow.getSerialNoInParent());
            assertEquals(0, l_rsvIfoOrderUnitRow.getEveningSessionCarryoverFlag().intValue());
            assertEquals(WEB3GentradeTradingTimeManagement.getSessionType(),
                    l_rsvIfoOrderUnitRow.getSessionType());
            assertEquals("1", l_rsvIfoOrderUnitRow.getExpirationDateType());
            assertEquals(0, WEB3DateUtility.compareToSecond(GtlUtils.getSystemTimestamp(),
                    l_rsvIfoOrderUnitRow.getCreatedTimestamp()));
            assertEquals(0, WEB3DateUtility.compareToSecond(GtlUtils.getSystemTimestamp(),
                    l_rsvIfoOrderUnitRow.getLastUpdatedTimestamp()));
        }
        catch (Exception l_ex)
        {
            log.error(l_ex.getMessage(), l_ex);
            fail();
        }
        log.info(STR_METHOD_NAME + ">>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>test pass !!");
        log.exiting(TEST_END + STR_METHOD_NAME);
    }
    

    /** submit先物OP返済新規予約注文
    * パラメータ.親注文の注文単位.先物／オプション区分が"先物"の場合
       先物OP予約注文単位テーブルにレコードを登録する。
       反対売買でない場合
       先物OP予約建玉返済指定情報テーブルに 
       　@レコードを登録する。
       引数の建玉.isLong() ！=true
       引数の単価調整値 != null
    */
   public void testSubmitIfoCloseContractNewOrderCase0005()
   {
       final String STR_METHOD_NAME = "testSubmitIfoCloseContractNewOrderCase0005()";
       log.entering(STR_METHOD_NAME);

       TestBaseForMock.MOCK_MANAGER.setIsMockUsed(true);
       try
       {
           
           TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                   "com.fitechlabs.xtrade.plugin.security.oplogin.impl.OpLoginSecurityServiceImpl", 
                   "getSessionProperty",
                   new Class[] {String.class},
                   new String("1"));
               
           WEB3GentradeTradingTimeManagementForMock.mockGetOrderBizDate(
               WEB3DateUtility.getDate("20070105", "yyyyMMdd"));

           //MainAccountParams
           TestDBUtility.deleteAll(MainAccountRow.TYPE);
           MainAccountParams l_mainAccountParams = TestDBUtility.getMainAccountRow();
           l_mainAccountParams.setAccountId(101001010010L);
           l_mainAccountParams.setSonarTraderCode("01");
           TestDBUtility.insertWithDel(l_mainAccountParams);
           
           //SubAccountParams
           TestDBUtility.deleteAll(SubAccountRow.TYPE);
           SubAccountParams l_subAccountParams = TestDBUtility.getSubAccountRow();
           l_subAccountParams.setAccountId(101001010010L);
           l_subAccountParams.setSubAccountId(10100101001007L);
           TestDBUtility.insertWithDel(l_subAccountParams);

           //MarketParams
           TestDBUtility.deleteAll(MarketRow.TYPE);
           MarketParams l_marketParams = TestDBUtility.getMarketRow();
           l_marketParams.setMarketCode("01");
           TestDBUtility.insertWithDel(l_marketParams);
           
           //BranchParams
           TestDBUtility.deleteAll(BranchRow.TYPE);
           BranchParams l_branchParams = TestDBUtility.getBranchRow();
           TestDBUtility.insertWithDel(l_branchParams);

           //InstitutionParams
           TestDBUtility.deleteAll(InstitutionRow.TYPE);
           InstitutionParams l_institutionParams = TestDBUtility.getInstitutionRow();
           TestDBUtility.insertWithDel(l_institutionParams);
           
           //ProductParams
           TestDBUtility.deleteAll(ProductRow.TYPE);
           ProductParams l_productParams = TestDBUtility.getProductRow();
           l_productParams.setProductId(1006169090018L);
           l_productParams.setProductType(ProductTypeEnum.IFO);
           TestDBUtility.insertWithDel(l_productParams);
           
           TestDBUtility.deleteAll(IfoProductRow.TYPE);
           IfoProductParams l_ifoProductParams = TestDBUtility.getIfoProductRow();
           l_ifoProductParams.setProductId(1006169090018L);
           l_ifoProductParams.setInstitutionCode("0D");
           TestDBUtility.insertWithDel(l_ifoProductParams);
           
           TestDBUtility.deleteAll(IfoOrderUnitRow.TYPE);
           IfoOrderUnitParams l_ifoOrderUnitParams = TestDBUtility.getIfoOrderUnitRow();
           l_ifoOrderUnitParams.setConfirmedQuantity(null);
           l_ifoOrderUnitParams.setExecutedQuantity(456);
           l_ifoOrderUnitParams.setLimitPrice(200);
           l_ifoOrderUnitParams.setOrderId(1004);
           l_ifoOrderUnitParams.setFutureOptionDiv("2");
           TestDBUtility.insertWithDel(l_ifoOrderUnitParams);
           IfoOrderUnit l_ifoOrderUnit = new IfoContractOpenOrderUnitImpl(1001);
           
           WEB3GentradeSubAccount l_subAccount = new WEB3GentradeSubAccount(l_subAccountParams);
           
           TestDBUtility.deleteAll(IfoContractRow.TYPE);
           IfoContractParams l_ifoContractParams = TestDBUtility.getIfoContractRow();
           l_ifoContractParams.setAccountId(101001010010L);
           l_ifoContractParams.setSubAccountId(10100101001007L);
           l_ifoContractParams.setMarketId(3303);
           l_ifoContractParams.setProductId(1006169090018L);
           l_ifoContractParams.setContractType(ContractTypeEnum.SHORT);
           TestDBUtility.insertWithDel(l_ifoContractParams);           
           
           SettleContractEntry l_settleContractOrderEntry =
               new SettleContractEntry(l_ifoContractParams.getContractId(), 222);
           SettleContractEntry[] l_settleContractOrderEntrys = new SettleContractEntry[1];
           l_settleContractOrderEntrys[0] = l_settleContractOrderEntry;

           WEB3IfoSettleContractOrderSpec l_ifoSettleContractOrderSpec = 
               WEB3IfoSettleContractOrderSpec.createSettleContractOrderSpec(
                  "0D", null,333,IfoOrderExecutionConditionType.AT_MARKET_CLOSE,
                       null,l_settleContractOrderEntrys,null,
                       123D, 456D,
                       IfoOrderExecutionConditionType.AT_MARKET_OPEN,
                       "1", new Long(1002), false);

           WEB3IfoContractImpl l_ifoContractImpl = new WEB3IfoContractImpl(l_ifoContractParams);

           long l_lngOrderId = 1111;
           String l_strTradingPassword = null;
           String l_strRsvOrderTradingType = "13";
           Double l_priceAdjustValue = new Double(100);
           String l_strClosingOrder = "3";

           WEB3IfoEstimateDeliveryAmountCalcResult l_ifoEstimateDeliveryAmountCalcResult =
               new WEB3IfoEstimateDeliveryAmountCalcResult();
           l_ifoEstimateDeliveryAmountCalcResult.setCalcUnitPrice(123);
           l_ifoEstimateDeliveryAmountCalcResult.setEstimateDeliveryAmount(234);

           TestDBUtility.deleteAll(RsvIfoOrderUnitRow.TYPE);
           TestDBUtility.deleteAll(RsvIfoOrderActionRow.TYPE);
           TestDBUtility.deleteAll(RsvIfoClosingContractSpecRow.TYPE);
           
           l_impl = new WEB3ToSuccOrderManagerImplForTest();
           l_impl.submitIfoCloseContractNewOrder(l_subAccount,
               l_ifoSettleContractOrderSpec,
               l_lngOrderId,
               l_strTradingPassword,
               l_strRsvOrderTradingType,
               l_priceAdjustValue,
               l_ifoOrderUnit,
               l_ifoEstimateDeliveryAmountCalcResult,
               l_ifoContractImpl,
               l_strClosingOrder);
           
           RsvIfoOrderUnitRow l_rsvIfoOrderUnitRow = RsvIfoOrderUnitDao.findRowByPk(1111);
           assertTrue(l_rsvIfoOrderUnitRow.getOrderUnitIdIsNull());
           assertEquals(101001010010L, l_rsvIfoOrderUnitRow.getAccountId());
           assertEquals(10100101001007L, l_rsvIfoOrderUnitRow.getSubAccountId());
           assertEquals(33381, l_rsvIfoOrderUnitRow.getBranchId());
           assertTrue(l_rsvIfoOrderUnitRow.getTraderIdIsNull());
           assertEquals(603, l_rsvIfoOrderUnitRow.getOrderType().intValue());
           assertEquals(92, l_rsvIfoOrderUnitRow.getOrderCateg().intValue());
           assertEquals(1, l_rsvIfoOrderUnitRow.getLastOrderActionSerialNo());
           assertEquals("13", l_rsvIfoOrderUnitRow.getReserveOrderTradingType());
           assertEquals(6, l_rsvIfoOrderUnitRow.getProductType().intValue());
           assertEquals("1", l_rsvIfoOrderUnitRow.getFutureOptionDiv());
           assertEquals(3303, l_rsvIfoOrderUnitRow.getMarketId());
           assertEquals("222.0", l_rsvIfoOrderUnitRow.getQuantity() + "");
           assertTrue(l_rsvIfoOrderUnitRow.getLimitPriceIsNull());
           assertEquals("100.0", l_rsvIfoOrderUnitRow.getPriceAdjustValue() + "");
           assertEquals("20070105", WEB3DateUtility.formatDate(
                   l_rsvIfoOrderUnitRow.getExpirationDate(), "yyyyMMdd"));
           assertEquals(1, l_rsvIfoOrderUnitRow.getOrderStatus().intValue());
           assertEquals(1, l_rsvIfoOrderUnitRow.getOrderOpenStatus().intValue());
           assertEquals(1, l_rsvIfoOrderUnitRow.getExpirationStatus().intValue());
           assertEquals(0, l_rsvIfoOrderUnitRow.getTaxType().intValue());
           assertEquals("20070105", l_rsvIfoOrderUnitRow.getBizDate());
           assertEquals(1006169090018L, l_rsvIfoOrderUnitRow.getProductId());
           assertEquals("1", l_rsvIfoOrderUnitRow.getOrderChanel());
           assertEquals(0, WEB3DateUtility.compareToSecond(GtlUtils.getSystemTimestamp(),
                   l_rsvIfoOrderUnitRow.getReceivedDateTime()));
           assertEquals("01", l_rsvIfoOrderUnitRow.getSonarTraderCode());
           assertEquals("123.0", l_rsvIfoOrderUnitRow.getPrice() + "");
           assertEquals("234.0", l_rsvIfoOrderUnitRow.getEstimatedPrice() + "");
           assertEquals("1", l_rsvIfoOrderUnitRow.getOrderRootDiv());
           assertEquals("3", l_rsvIfoOrderUnitRow.getClosingOrder());
           assertEquals("0000", l_rsvIfoOrderUnitRow.getErrorReasonCode());
           assertEquals(1002, l_rsvIfoOrderUnitRow.getFirstOrderUnitId());
           assertTrue(l_rsvIfoOrderUnitRow.getFirstOrderIdIsNull());
           assertNull(l_rsvIfoOrderUnitRow.getOrderErrorCode());
           assertEquals(1004, l_rsvIfoOrderUnitRow.getParentOrderId());
           assertEquals(1001, l_rsvIfoOrderUnitRow.getParentOrderUnitId());
           assertEquals(1, l_rsvIfoOrderUnitRow.getSerialNoInParent());
           assertEquals(0, l_rsvIfoOrderUnitRow.getEveningSessionCarryoverFlag().intValue());
           assertEquals(WEB3GentradeTradingTimeManagement.getSessionType(),
                   l_rsvIfoOrderUnitRow.getSessionType());
           assertEquals("1", l_rsvIfoOrderUnitRow.getExpirationDateType());
           assertEquals(0, WEB3DateUtility.compareToSecond(GtlUtils.getSystemTimestamp(),
                   l_rsvIfoOrderUnitRow.getCreatedTimestamp()));
           assertEquals(0, WEB3DateUtility.compareToSecond(GtlUtils.getSystemTimestamp(),
                   l_rsvIfoOrderUnitRow.getLastUpdatedTimestamp()));
           
           RsvIfoClosingContractSpecRow l_rsvIfoClosingContractSpecRow =
               RsvIfoClosingContractSpecDao.findRowByOrderIdContractId(
                   1111, l_ifoContractParams.getContractId());
           
           assertNull(l_rsvIfoClosingContractSpecRow);
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
    * submit先物OP訂正予約新規建注文
    * 注文チェック.validate取引パスワードisFailedResult
    */
   public void testSubmitIfoChangeOpenContractOrderCase0001()
   {
       final String STR_METHOD_NAME = "testSubmitIfoChangeOpenContractOrderCase0001()";
       log.entering(STR_METHOD_NAME);

       TestBaseForMock.MOCK_MANAGER.setIsMockUsed(true);
       try
       {           
           TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
               "webbroker3.gentrade.WEB3GentradeOrderValidator",
               "validateTradingPassword",
               new Class[] { Trader.class, SubAccount.class, String.class },
               new OrderValidationResult(ProcessingResult.newFailedResultInstance(
                   WEB3ErrorCatalog.SYSTEM_ERROR_80003)));

           //SubAccountParams
           TestDBUtility.deleteAll(SubAccountRow.TYPE);
           SubAccountParams l_subAccountParams = TestDBUtility.getSubAccountRow();
           l_subAccountParams.setAccountId(101001010010L);
           l_subAccountParams.setSubAccountId(10100101001007L);
           TestDBUtility.insertWithDel(l_subAccountParams);
           
           TestDBUtility.deleteAll(RsvIfoOrderUnitRow.TYPE);
           RsvIfoOrderUnitParams l_RsvIfoOrderUnitParams = TestDBUtility.getRsvIfoOrderUnitRow();
           l_RsvIfoOrderUnitParams.setOrderUnitId(1111);
           TestDBUtility.insertWithDel(l_RsvIfoOrderUnitParams);
           
           WEB3GentradeSubAccount l_subAccount = new WEB3GentradeSubAccount(l_subAccountParams);

           WEB3ToSuccIfoChangeOpenContractOrderSpec l_ifoChangeOrderSpec =
               new WEB3ToSuccIfoChangeOpenContractOrderSpec(1001,1111,200,300);
           
           String l_strTradingPassword = "123";
           WEB3ToSuccIfoOrderUnitImpl l_changingBeforeRsvIfoOrderUnit =
               new WEB3ToSuccIfoOrderUnitImpl(l_RsvIfoOrderUnitParams);

           l_impl.submitIfoChangeOpenContractOrder(
               l_subAccount, l_ifoChangeOrderSpec, l_strTradingPassword, l_changingBeforeRsvIfoOrderUnit);
           fail();
       }
       catch(WEB3BaseException l_ex)
       {
           log.debug(STR_METHOD_NAME, l_ex);
           assertEquals(l_ex.getErrorInfo(),WEB3ErrorCatalog.SYSTEM_ERROR_80003);
           log.info(STR_METHOD_NAME + ">>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>test pass !!");
           log.exiting(TEST_END + STR_METHOD_NAME);

       }
       catch (Exception l_ex)
       {
           log.error(ERROR + l_ex.getMessage(), l_ex);
           fail();
       }
   }

    /**
     * submit先物OP訂正予約新規建注文
     * 先物OP予約注文単位テーブルのレコードを更新する。
     * 「（連続）新規建訂正_先物OP予約注文単位テーブル.xls」を参照。
     * get扱者()の戻り値 != nul
     * 引数の予約注文訂正内容.get訂正後単価調整値()==null
     */
   public void testSubmitIfoChangeOpenContractOrderCase0002()
   {
       final String STR_METHOD_NAME = "testSubmitIfoCloseContractNewOrderCase0002()";
       log.entering(STR_METHOD_NAME);

       TestBaseForMock.MOCK_MANAGER.setIsMockUsed(true);
       try
       {         
           TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                   "com.fitechlabs.xtrade.plugin.security.oplogin.impl.OpLoginSecurityServiceImpl", 
                   "getSessionProperty",
                   new Class[] {String.class},
                   new String("1"));

           TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                   "webbroker3.gentrade.WEB3GentradeOrderValidator",
                   "validateTradingPassword",
                   new Class[] { Trader.class, SubAccount.class, String.class },
                   OrderValidationResult.VALIDATION_OK_RESULT);
           
           //MainAccountParams
           TestDBUtility.deleteAll(MainAccountRow.TYPE);

           //SubAccountParams
           TestDBUtility.deleteAll(SubAccountRow.TYPE);
           SubAccountParams l_subAccountParams = TestDBUtility.getSubAccountRow();
           l_subAccountParams.setAccountId(101001010010L);
           l_subAccountParams.setSubAccountId(10100101001007L);
           TestDBUtility.insertWithDel(l_subAccountParams);

           //MarketParams
           TestDBUtility.deleteAll(MarketRow.TYPE);
           //ProductParams
           TestDBUtility.deleteAll(ProductRow.TYPE);;

           WEB3GentradeSubAccount l_subAccount = new WEB3GentradeSubAccount(l_subAccountParams);
           
           TestDBUtility.deleteAll(IfoContractRow.TYPE);
           
           TestDBUtility.deleteAll(TraderRow.TYPE);
           TraderParams l_TraderParams = TestDBUtility.getTraderRow();
           l_TraderParams.setTraderId(2222);
           l_TraderParams.setLoginId(1234);
           TestDBUtility.insertWithDel(l_TraderParams);
           
           
           TestDBUtility.deleteAll(RsvIfoOrderUnitRow.TYPE);
           RsvIfoOrderUnitParams l_RsvIfoOrderUnitParams = TestDBUtility.getRsvIfoOrderUnitRow();
           l_RsvIfoOrderUnitParams.setOrderId(1111);
           l_RsvIfoOrderUnitParams.setLastOrderActionSerialNo(1);
           TestDBUtility.insertWithDel(l_RsvIfoOrderUnitParams);

           WEB3GentradeTrader l_trader = new WEB3GentradeTrader(1234, true);
           WEB3ToSuccIfoChangeOpenContractOrderSpec l_ifoChangeOrderSpec =
               new WEB3ToSuccIfoChangeOpenContractOrderSpec(1111,1001,200,300);
           l_ifoChangeOrderSpec.setTrader(l_trader);
           l_ifoChangeOrderSpec.setModifiedPriceAdjustValue(null);
           l_ifoChangeOrderSpec.setModifiedExpirationDate(WEB3DateUtility.getDate("20070205", "yyyyMMdd"));
           l_ifoChangeOrderSpec.setModifiedCalcUnitPrice(123);
           l_ifoChangeOrderSpec.setModifiedEstimatedPrice(234);
           l_ifoChangeOrderSpec.setFirstOrderUnitId(new Long(1234));
           l_ifoChangeOrderSpec.setEveningSessionCarryOverFlag(false);
           l_ifoChangeOrderSpec.setExpirationDateType("2");

           String l_strTradingPassword = "123";
           WEB3ToSuccIfoOrderUnitImpl l_changingBeforeRsvIfoOrderUnit =
               new WEB3ToSuccIfoOrderUnitImpl(l_RsvIfoOrderUnitParams);

           TestDBUtility.deleteAll(RsvIfoOrderActionRow.TYPE);
           TestDBUtility.deleteAll(RsvIfoClosingContractSpecRow.TYPE);
           
           l_impl.submitIfoChangeOpenContractOrder(
                   l_subAccount, l_ifoChangeOrderSpec, l_strTradingPassword, l_changingBeforeRsvIfoOrderUnit);
           
           RsvIfoOrderUnitRow l_rsvIfoOrderUnitRow = RsvIfoOrderUnitDao.findRowByPk(1111);
           assertEquals(2222, l_rsvIfoOrderUnitRow.getTraderId());
           assertEquals(2, l_rsvIfoOrderUnitRow.getLastOrderActionSerialNo());
           assertEquals("200.0", l_rsvIfoOrderUnitRow.getQuantity() + "");
           assertEquals("300.0", l_rsvIfoOrderUnitRow.getLimitPrice() + "");
           assertTrue(l_rsvIfoOrderUnitRow.getPriceAdjustValueIsNull());
           assertEquals("20070205", WEB3DateUtility.formatDate(
               l_rsvIfoOrderUnitRow.getExpirationDate(), "yyyyMMdd"));
           assertEquals(10, l_rsvIfoOrderUnitRow.getOrderStatus().intValue());
           assertEquals("123.0", l_rsvIfoOrderUnitRow.getPrice() + "");
           assertEquals("234.0", l_rsvIfoOrderUnitRow.getEstimatedPrice() + "");
           assertEquals("1", l_rsvIfoOrderUnitRow.getOrderRootDiv());
           assertEquals(1234, l_rsvIfoOrderUnitRow.getFirstOrderUnitId());
           assertEquals(0, l_rsvIfoOrderUnitRow.getEveningSessionCarryoverFlag().intValue());
           assertEquals("2", l_rsvIfoOrderUnitRow.getExpirationDateType());
           assertEquals(0, WEB3DateUtility.compareToSecond(GtlUtils.getSystemTimestamp(),
                   l_rsvIfoOrderUnitRow.getLastUpdatedTimestamp()));
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
    * submit先物OP訂正予約新規建注文
    * 先物OP予約注文単位テーブルのレコードを更新する。
    * 「（連続）新規建訂正_先物OP予約注文単位テーブル.xls」を参照。
        get扱者()の戻り値 == nul
        引数の予約注文訂正内容.get訂正後単価調整値() !=null
    */
  public void testSubmitIfoChangeOpenContractOrderCase0003()
  {
      final String STR_METHOD_NAME = "testSubmitIfoCloseContractNewOrderCase0003()";
      log.entering(STR_METHOD_NAME);

      TestBaseForMock.MOCK_MANAGER.setIsMockUsed(true);
      try
      {         
          TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                  "com.fitechlabs.xtrade.plugin.security.oplogin.impl.OpLoginSecurityServiceImpl", 
                  "getSessionProperty",
                  new Class[] {String.class},
                  new String("1"));

          TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                  "webbroker3.gentrade.WEB3GentradeOrderValidator",
                  "validateTradingPassword",
                  new Class[] { Trader.class, SubAccount.class, String.class },
                  OrderValidationResult.VALIDATION_OK_RESULT);
          
          //MainAccountParams
          TestDBUtility.deleteAll(MainAccountRow.TYPE);

          //SubAccountParams
          TestDBUtility.deleteAll(SubAccountRow.TYPE);
          SubAccountParams l_subAccountParams = TestDBUtility.getSubAccountRow();
          l_subAccountParams.setAccountId(101001010010L);
          l_subAccountParams.setSubAccountId(10100101001007L);
          TestDBUtility.insertWithDel(l_subAccountParams);

          //MarketParams
          TestDBUtility.deleteAll(MarketRow.TYPE);
          //ProductParams
          TestDBUtility.deleteAll(ProductRow.TYPE);;

          WEB3GentradeSubAccount l_subAccount = new WEB3GentradeSubAccount(l_subAccountParams);
          
          TestDBUtility.deleteAll(IfoContractRow.TYPE);

          TestDBUtility.deleteAll(RsvIfoOrderUnitRow.TYPE);
          RsvIfoOrderUnitParams l_RsvIfoOrderUnitParams = TestDBUtility.getRsvIfoOrderUnitRow();
          l_RsvIfoOrderUnitParams.setOrderId(1111);
          l_RsvIfoOrderUnitParams.setLastOrderActionSerialNo(1);
          TestDBUtility.insertWithDel(l_RsvIfoOrderUnitParams);

          WEB3ToSuccIfoChangeOpenContractOrderSpec l_ifoChangeOrderSpec =
              new WEB3ToSuccIfoChangeOpenContractOrderSpec(1111,1001,200,300);
          l_ifoChangeOrderSpec.setTrader(null);
          l_ifoChangeOrderSpec.setModifiedPriceAdjustValue(new Double(100));
          l_ifoChangeOrderSpec.setModifiedExpirationDate(WEB3DateUtility.getDate("20070205", "yyyyMMdd"));
          l_ifoChangeOrderSpec.setModifiedCalcUnitPrice(123);
          l_ifoChangeOrderSpec.setModifiedEstimatedPrice(234);
          l_ifoChangeOrderSpec.setFirstOrderUnitId(new Long(1234));
          l_ifoChangeOrderSpec.setEveningSessionCarryOverFlag(false);
          l_ifoChangeOrderSpec.setExpirationDateType("2");

          String l_strTradingPassword = "123";
          WEB3ToSuccIfoOrderUnitImpl l_changingBeforeRsvIfoOrderUnit =
              new WEB3ToSuccIfoOrderUnitImpl(l_RsvIfoOrderUnitParams);

          TestDBUtility.deleteAll(RsvIfoOrderActionRow.TYPE);
          TestDBUtility.deleteAll(RsvIfoClosingContractSpecRow.TYPE);
          
          l_impl.submitIfoChangeOpenContractOrder(
                  l_subAccount, l_ifoChangeOrderSpec, l_strTradingPassword, l_changingBeforeRsvIfoOrderUnit);
          
          RsvIfoOrderUnitRow l_rsvIfoOrderUnitRow = RsvIfoOrderUnitDao.findRowByPk(1111);
          assertTrue(l_rsvIfoOrderUnitRow.getTraderIdIsNull());
          assertEquals(2, l_rsvIfoOrderUnitRow.getLastOrderActionSerialNo());
          assertEquals("200.0", l_rsvIfoOrderUnitRow.getQuantity() + "");
          assertTrue(l_rsvIfoOrderUnitRow.getLimitPriceIsNull());
          assertEquals("100.0", l_rsvIfoOrderUnitRow.getPriceAdjustValue() + "");
          assertEquals("20070205", WEB3DateUtility.formatDate(
              l_rsvIfoOrderUnitRow.getExpirationDate(), "yyyyMMdd"));
          assertEquals(10, l_rsvIfoOrderUnitRow.getOrderStatus().intValue());
          assertEquals("123.0", l_rsvIfoOrderUnitRow.getPrice() + "");
          assertEquals("234.0", l_rsvIfoOrderUnitRow.getEstimatedPrice() + "");
          assertEquals("1", l_rsvIfoOrderUnitRow.getOrderRootDiv());
          assertEquals(1234, l_rsvIfoOrderUnitRow.getFirstOrderUnitId());
          assertEquals(0, l_rsvIfoOrderUnitRow.getEveningSessionCarryoverFlag().intValue());
          assertEquals("2", l_rsvIfoOrderUnitRow.getExpirationDateType());
          assertEquals(0, WEB3DateUtility.compareToSecond(GtlUtils.getSystemTimestamp(),
                  l_rsvIfoOrderUnitRow.getLastUpdatedTimestamp()));
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
   * submit先物OP訂正予約新規建注文
   * 注文チェック.validate取引パスワードisFailedResult
   */
  public void testSubmitIfoChangeSettleContractOrderCase0001()
  {
      final String STR_METHOD_NAME = "testSubmitIfoChangeSettleContractOrderCase0001()";
      log.entering(STR_METHOD_NAME);

      TestBaseForMock.MOCK_MANAGER.setIsMockUsed(true);
      try
      {           
          TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
              "webbroker3.gentrade.WEB3GentradeOrderValidator",
              "validateTradingPassword",
              new Class[] { Trader.class, SubAccount.class, String.class },
              new OrderValidationResult(ProcessingResult.newFailedResultInstance(
                  WEB3ErrorCatalog.SYSTEM_ERROR_80003)));

          //SubAccountParams
          TestDBUtility.deleteAll(SubAccountRow.TYPE);
          SubAccountParams l_subAccountParams = TestDBUtility.getSubAccountRow();
          l_subAccountParams.setAccountId(101001010010L);
          l_subAccountParams.setSubAccountId(10100101001007L);
          TestDBUtility.insertWithDel(l_subAccountParams);
          
          TestDBUtility.deleteAll(RsvIfoOrderUnitRow.TYPE);
          RsvIfoOrderUnitParams l_RsvIfoOrderUnitParams = TestDBUtility.getRsvIfoOrderUnitRow();
          l_RsvIfoOrderUnitParams.setOrderUnitId(1111);
          TestDBUtility.insertWithDel(l_RsvIfoOrderUnitParams);
          
          WEB3GentradeSubAccount l_subAccount = new WEB3GentradeSubAccount(l_subAccountParams);
          
          SettleContractEntry[] l_newContractEntries = new  SettleContractEntry[1];
          SettleContractEntry l_l_newContractEntry = new SettleContractEntry(1001,100);
          l_newContractEntries[0] = l_l_newContractEntry;
          
          WEB3ToSuccIfoChangeSettleContractOrderSpec l_toSuccIfoChangeSettleContractOrderSpec =
              new WEB3ToSuccIfoChangeSettleContractOrderSpec(1001,1111,200,l_newContractEntries);
          
          String l_strTradingPassword = "123";
          WEB3ToSuccIfoOrderUnitImpl l_changingBeforeRsvIfoOrderUnit =
              new WEB3ToSuccIfoOrderUnitImpl(l_RsvIfoOrderUnitParams);

          l_impl.submitIfoChangeSettleContractOrder(
              l_subAccount, l_toSuccIfoChangeSettleContractOrderSpec, 
              l_strTradingPassword, l_changingBeforeRsvIfoOrderUnit);
          fail();
      }
      catch(WEB3BaseException l_ex)
      {
          log.debug(STR_METHOD_NAME, l_ex);
          assertEquals(l_ex.getErrorInfo(),WEB3ErrorCatalog.SYSTEM_ERROR_80003);
          log.info(STR_METHOD_NAME + ">>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>test pass !!");
          log.exiting(TEST_END + STR_METHOD_NAME);

      }
      catch (Exception l_ex)
      {
          log.error(ERROR + l_ex.getMessage(), l_ex);
          fail();
      }
  }
    
    /**
     * 
     * 先物OP予約注文単位テーブルのレコードを更新する。
    「（連続）返済注文訂正_先物OP予約注文単位テーブル.xls」を参照。
    （パラメータ.訂正前予約注文単位.is反対売買取引() == false） 
    　@先物OP予約建玉返済指定情報テーブルのレコードを更新する。 先物OP予約注文単位テーブルのレコードを更新する。
    「（連続）返済注文訂正_先物OP予約注文単位テーブル.xls」を参照。
    （パラメータ.訂正前予約注文単位.is反対売買取引() == false） 
    　@先物OP予約建玉返済指定情報テーブルのレコードを更新する。 
     *get扱者()の戻り値 != nul
        引数の予約注文訂正内容.get訂正後単価調整値()==null
        パラメータ.訂正前予約注文単位.is反対売買取引() == false
     */
  public void testSubmitIfoChangeSettleContractOrderCase0002()
  {
      final String STR_METHOD_NAME = "testSubmitIfoChangeSettleContractOrderCase0002()";
      log.entering(STR_METHOD_NAME);

      TestBaseForMock.MOCK_MANAGER.setIsMockUsed(true);
      try
      {         
          TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                  "com.fitechlabs.xtrade.plugin.security.oplogin.impl.OpLoginSecurityServiceImpl", 
                  "getSessionProperty",
                  new Class[] {String.class},
                  new String("1"));

          TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                  "webbroker3.gentrade.WEB3GentradeOrderValidator",
                  "validateTradingPassword",
                  new Class[] { Trader.class, SubAccount.class, String.class },
                  OrderValidationResult.VALIDATION_OK_RESULT);
          
          TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
              "webbroker3.triggerorder.WEB3ToSuccOrderManagerImpl",
              "isReversingTrade", 
              new Class[]{String.class, OrderUnit.class},
              new Boolean(false));

          //MainAccountParams
          TestDBUtility.deleteAll(MainAccountRow.TYPE);

          //SubAccountParams
          TestDBUtility.deleteAll(SubAccountRow.TYPE);
          SubAccountParams l_subAccountParams = TestDBUtility.getSubAccountRow();
          l_subAccountParams.setAccountId(101001010010L);
          l_subAccountParams.setSubAccountId(10100101001007L);
          TestDBUtility.insertWithDel(l_subAccountParams);

          //MarketParams
          TestDBUtility.deleteAll(MarketRow.TYPE);
          //ProductParams
          TestDBUtility.deleteAll(ProductRow.TYPE);;

          WEB3GentradeSubAccount l_subAccount = new WEB3GentradeSubAccount(l_subAccountParams);
          
          TestDBUtility.deleteAll(IfoContractRow.TYPE);
          
          TestDBUtility.deleteAll(TraderRow.TYPE);
          TraderParams l_TraderParams = TestDBUtility.getTraderRow();
          l_TraderParams.setTraderId(2222);
          l_TraderParams.setLoginId(1234);
          TestDBUtility.insertWithDel(l_TraderParams);

          TestDBUtility.deleteAll(RsvIfoOrderUnitRow.TYPE);
          RsvIfoOrderUnitParams l_RsvIfoOrderUnitParams = TestDBUtility.getRsvIfoOrderUnitRow();
          l_RsvIfoOrderUnitParams.setOrderId(1111);
          l_RsvIfoOrderUnitParams.setLastOrderActionSerialNo(1);
          l_RsvIfoOrderUnitParams.setReserveOrderTradingType("01");
          TestDBUtility.insertWithDel(l_RsvIfoOrderUnitParams);
          
          TestDBUtility.deleteAll(RsvIfoClosingContractSpecRow.TYPE);
          RsvIfoClosingContractSpecParams l_rsvIfoClosingContractSpecParams =
              TestDBUtility.getRsvIfoClosingContractSpecRow();
          l_rsvIfoClosingContractSpecParams.setContractId(1001);
          l_rsvIfoClosingContractSpecParams.setOrderId(1111);
          TestDBUtility.insertWithDel(l_rsvIfoClosingContractSpecParams);

          WEB3GentradeTrader l_trader = new WEB3GentradeTrader(1234, true);
          SettleContractEntry[] l_newContractEntries = new  SettleContractEntry[1];
          SettleContractEntry l_l_newContractEntry = new SettleContractEntry(1001,100);
          l_newContractEntries[0] = l_l_newContractEntry;
          
          WEB3ToSuccIfoChangeSettleContractOrderSpec l_toSuccIfoChangeSettleContractOrderSpec =
              new WEB3ToSuccIfoChangeSettleContractOrderSpec(1001,1111,200,l_newContractEntries);
          
          l_toSuccIfoChangeSettleContractOrderSpec.setTrader(l_trader);
          l_toSuccIfoChangeSettleContractOrderSpec.setModifiedPriceAdjustValue(null);
          l_toSuccIfoChangeSettleContractOrderSpec.setModifiedExpirationDate(WEB3DateUtility.getDate("20070205", "yyyyMMdd"));
          l_toSuccIfoChangeSettleContractOrderSpec.setModifiedCalcUnitPrice(123);
          l_toSuccIfoChangeSettleContractOrderSpec.setModifiedEstimatedPrice(234);
          l_toSuccIfoChangeSettleContractOrderSpec.setFirstOrderUnitId(new Long(1234));
          l_toSuccIfoChangeSettleContractOrderSpec.setEveningSessionCarryOverFlag(false);
          l_toSuccIfoChangeSettleContractOrderSpec.setExpirationDateType("2");

          String l_strTradingPassword = "123";
          WEB3ToSuccIfoOrderUnitImpl l_changingBeforeRsvIfoOrderUnit =
              new WEB3ToSuccIfoOrderUnitImpl(l_RsvIfoOrderUnitParams);

          TestDBUtility.deleteAll(RsvIfoOrderActionRow.TYPE);

          l_impl.submitIfoChangeSettleContractOrder(
                  l_subAccount, l_toSuccIfoChangeSettleContractOrderSpec, 
                  l_strTradingPassword, l_changingBeforeRsvIfoOrderUnit);
          
          RsvIfoOrderUnitRow l_rsvIfoOrderUnitRow = RsvIfoOrderUnitDao.findRowByPk(1111);
          assertEquals(2222, l_rsvIfoOrderUnitRow.getTraderId());
          assertEquals(2, l_rsvIfoOrderUnitRow.getLastOrderActionSerialNo());
          assertEquals("100.0", l_rsvIfoOrderUnitRow.getQuantity() + "");
          assertEquals("200.0", l_rsvIfoOrderUnitRow.getLimitPrice() + "");
          assertTrue(l_rsvIfoOrderUnitRow.getPriceAdjustValueIsNull());
          assertEquals("20070205", WEB3DateUtility.formatDate(
              l_rsvIfoOrderUnitRow.getExpirationDate(), "yyyyMMdd"));
          assertEquals(10, l_rsvIfoOrderUnitRow.getOrderStatus().intValue());
          assertEquals("123.0", l_rsvIfoOrderUnitRow.getPrice() + "");
          assertEquals("234.0", l_rsvIfoOrderUnitRow.getEstimatedPrice() + "");
          assertEquals("1", l_rsvIfoOrderUnitRow.getOrderRootDiv());
          assertEquals(1234, l_rsvIfoOrderUnitRow.getFirstOrderUnitId());
          assertEquals(0, l_rsvIfoOrderUnitRow.getEveningSessionCarryoverFlag().intValue());
          assertEquals("2", l_rsvIfoOrderUnitRow.getExpirationDateType());
          assertEquals(0, WEB3DateUtility.compareToSecond(GtlUtils.getSystemTimestamp(),
                  l_rsvIfoOrderUnitRow.getLastUpdatedTimestamp()));

          RsvIfoClosingContractSpecRow l_rsvIfoClosingContractSpecRow =
              RsvIfoClosingContractSpecDao.findRowByOrderIdContractId(1111, 1001);
          assertEquals("100.0", l_rsvIfoClosingContractSpecRow.getQuantity() + "");
          assertEquals(0, WEB3DateUtility.compareToSecond(GtlUtils.getSystemTimestamp(),
                  l_rsvIfoClosingContractSpecRow.getLastUpdatedTimestamp()));
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
   * 
   * 先物OP予約注文単位テーブルのレコードを更新する。
  「（連続）返済注文訂正_先物OP予約注文単位テーブル.xls」を参照。
    get扱者()の戻り値 == nul
    引数の予約注文訂正内容.get訂正後単価調整値() !=null
    パラメータ.訂正前予約注文単位.is反対売買取引() == true
   */
    public void testSubmitIfoChangeSettleContractOrderCase0003()
    {
        final String STR_METHOD_NAME = "testSubmitIfoChangeSettleContractOrderCase0003()";
        log.entering(STR_METHOD_NAME);
    
        TestBaseForMock.MOCK_MANAGER.setIsMockUsed(true);
        try
        {         
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                    "com.fitechlabs.xtrade.plugin.security.oplogin.impl.OpLoginSecurityServiceImpl", 
                    "getSessionProperty",
                    new Class[] {String.class},
                    new String("1"));
    
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                    "webbroker3.gentrade.WEB3GentradeOrderValidator",
                    "validateTradingPassword",
                    new Class[] { Trader.class, SubAccount.class, String.class },
                    OrderValidationResult.VALIDATION_OK_RESULT);
            
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                "webbroker3.triggerorder.WEB3ToSuccOrderManagerImpl",
                "isReversingTrade", 
                new Class[]{String.class, OrderUnit.class},
                new Boolean(true));
    
            //MainAccountParams
            TestDBUtility.deleteAll(MainAccountRow.TYPE);
    
            //SubAccountParams
            TestDBUtility.deleteAll(SubAccountRow.TYPE);
            SubAccountParams l_subAccountParams = TestDBUtility.getSubAccountRow();
            l_subAccountParams.setAccountId(101001010010L);
            l_subAccountParams.setSubAccountId(10100101001007L);
            TestDBUtility.insertWithDel(l_subAccountParams);
    
            //MarketParams
            TestDBUtility.deleteAll(MarketRow.TYPE);
            //ProductParams
            TestDBUtility.deleteAll(ProductRow.TYPE);;
    
            WEB3GentradeSubAccount l_subAccount = new WEB3GentradeSubAccount(l_subAccountParams);
            
            TestDBUtility.deleteAll(IfoContractRow.TYPE);
            
            TestDBUtility.deleteAll(TraderRow.TYPE);
            TraderParams l_TraderParams = TestDBUtility.getTraderRow();
            l_TraderParams.setTraderId(2222);
            l_TraderParams.setLoginId(1234);
            TestDBUtility.insertWithDel(l_TraderParams);
    
            TestDBUtility.deleteAll(RsvIfoOrderUnitRow.TYPE);
            RsvIfoOrderUnitParams l_RsvIfoOrderUnitParams = TestDBUtility.getRsvIfoOrderUnitRow();
            l_RsvIfoOrderUnitParams.setOrderId(1111);
            l_RsvIfoOrderUnitParams.setLastOrderActionSerialNo(1);
            l_RsvIfoOrderUnitParams.setReserveOrderTradingType("01");
            TestDBUtility.insertWithDel(l_RsvIfoOrderUnitParams);
            
            TestDBUtility.deleteAll(RsvIfoClosingContractSpecRow.TYPE);
            RsvIfoClosingContractSpecParams l_rsvIfoClosingContractSpecParams =
                TestDBUtility.getRsvIfoClosingContractSpecRow();
            l_rsvIfoClosingContractSpecParams.setContractId(1001);
            l_rsvIfoClosingContractSpecParams.setOrderId(1111);
            l_rsvIfoClosingContractSpecParams.setQuantity(500);
            TestDBUtility.insertWithDel(l_rsvIfoClosingContractSpecParams);
    
            SettleContractEntry[] l_newContractEntries = new  SettleContractEntry[1];
            SettleContractEntry l_l_newContractEntry = new SettleContractEntry(1001,100);
            l_newContractEntries[0] = l_l_newContractEntry;
            
            WEB3ToSuccIfoChangeSettleContractOrderSpec l_toSuccIfoChangeSettleContractOrderSpec =
                new WEB3ToSuccIfoChangeSettleContractOrderSpec(1001,1111,200,l_newContractEntries);
            
            l_toSuccIfoChangeSettleContractOrderSpec.setTrader(null);
            l_toSuccIfoChangeSettleContractOrderSpec.setModifiedPriceAdjustValue(new Double(100));
            l_toSuccIfoChangeSettleContractOrderSpec.setModifiedExpirationDate(WEB3DateUtility.getDate("20070205", "yyyyMMdd"));
            l_toSuccIfoChangeSettleContractOrderSpec.setModifiedCalcUnitPrice(123);
            l_toSuccIfoChangeSettleContractOrderSpec.setModifiedEstimatedPrice(234);
            l_toSuccIfoChangeSettleContractOrderSpec.setFirstOrderUnitId(new Long(1234));
            l_toSuccIfoChangeSettleContractOrderSpec.setEveningSessionCarryOverFlag(false);
            l_toSuccIfoChangeSettleContractOrderSpec.setExpirationDateType("2");
    
            String l_strTradingPassword = "123";
            WEB3ToSuccIfoOrderUnitImpl l_changingBeforeRsvIfoOrderUnit =
                new WEB3ToSuccIfoOrderUnitImpl(l_RsvIfoOrderUnitParams);
    
            TestDBUtility.deleteAll(RsvIfoOrderActionRow.TYPE);
    
            l_impl.submitIfoChangeSettleContractOrder(
                    l_subAccount, l_toSuccIfoChangeSettleContractOrderSpec, 
                    l_strTradingPassword, l_changingBeforeRsvIfoOrderUnit);
            
            RsvIfoOrderUnitRow l_rsvIfoOrderUnitRow = RsvIfoOrderUnitDao.findRowByPk(1111);
            assertTrue(l_rsvIfoOrderUnitRow.getTraderIdIsNull());
            assertEquals(2, l_rsvIfoOrderUnitRow.getLastOrderActionSerialNo());
            assertEquals("100.0", l_rsvIfoOrderUnitRow.getQuantity() + "");
            assertTrue(l_rsvIfoOrderUnitRow.getLimitPriceIsNull());
            assertEquals("100.0", l_rsvIfoOrderUnitRow.getPriceAdjustValue() + "");
            assertEquals("20070205", WEB3DateUtility.formatDate(
                l_rsvIfoOrderUnitRow.getExpirationDate(), "yyyyMMdd"));
            assertEquals(10, l_rsvIfoOrderUnitRow.getOrderStatus().intValue());
            assertEquals("123.0", l_rsvIfoOrderUnitRow.getPrice() + "");
            assertEquals("234.0", l_rsvIfoOrderUnitRow.getEstimatedPrice() + "");
            assertEquals("1", l_rsvIfoOrderUnitRow.getOrderRootDiv());
            assertEquals(1234, l_rsvIfoOrderUnitRow.getFirstOrderUnitId());
            assertEquals(0, l_rsvIfoOrderUnitRow.getEveningSessionCarryoverFlag().intValue());
            assertEquals("2", l_rsvIfoOrderUnitRow.getExpirationDateType());
            assertEquals(0, WEB3DateUtility.compareToSecond(GtlUtils.getSystemTimestamp(),
                    l_rsvIfoOrderUnitRow.getLastUpdatedTimestamp()));
    
            RsvIfoClosingContractSpecRow l_rsvIfoClosingContractSpecRow =
                RsvIfoClosingContractSpecDao.findRowByOrderIdContractId(1111, 1001);
            assertEquals("500.0", l_rsvIfoClosingContractSpecRow.getQuantity() + "");
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
     * submit先物OP予約注文取消
   * 注文チェック.validate取引パスワードisFailedResult
   */
  public void testSubmitIfoCancelOrderCase0001()
  {
      final String STR_METHOD_NAME = "testSubmitIfoCancelOrderCase0001()";
      log.entering(STR_METHOD_NAME);

      TestBaseForMock.MOCK_MANAGER.setIsMockUsed(true);
      try
      {           
          TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
              "webbroker3.gentrade.WEB3GentradeOrderValidator",
              "validateTradingPassword",
              new Class[] { Trader.class, SubAccount.class, String.class },
              new OrderValidationResult(ProcessingResult.newFailedResultInstance(
                  WEB3ErrorCatalog.SYSTEM_ERROR_80003)));

          //SubAccountParams
          TestDBUtility.deleteAll(SubAccountRow.TYPE);
          SubAccountParams l_subAccountParams = TestDBUtility.getSubAccountRow();
          l_subAccountParams.setAccountId(101001010010L);
          l_subAccountParams.setSubAccountId(10100101001007L);
          TestDBUtility.insertWithDel(l_subAccountParams);
          
          TestDBUtility.deleteAll(RsvIfoOrderUnitRow.TYPE);
          RsvIfoOrderUnitParams l_RsvIfoOrderUnitParams = TestDBUtility.getRsvIfoOrderUnitRow();
          l_RsvIfoOrderUnitParams.setOrderUnitId(1111);
          TestDBUtility.insertWithDel(l_RsvIfoOrderUnitParams);
          
          WEB3GentradeSubAccount l_subAccount = new WEB3GentradeSubAccount(l_subAccountParams);

          String l_strTradingPassword = "123";
          WEB3ToSuccIfoOrderUnitImpl l_toSuccIfoOrderUnitImpl =
              new WEB3ToSuccIfoOrderUnitImpl(l_RsvIfoOrderUnitParams);

          l_impl.submitIfoCancelOrder(
              l_subAccount, l_toSuccIfoOrderUnitImpl, 
              l_strTradingPassword);
          fail();
      }
      catch(WEB3BaseException l_ex)
      {
          log.debug(STR_METHOD_NAME, l_ex);
          assertEquals(l_ex.getErrorInfo(),WEB3ErrorCatalog.SYSTEM_ERROR_80003);
          log.info(STR_METHOD_NAME + ">>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>test pass !!");
          log.exiting(TEST_END + STR_METHOD_NAME);

      }
      catch (Exception l_ex)
      {
          log.error(ERROR + l_ex.getMessage(), l_ex);
          fail();
      }
  }
  
    /**
     * submit先物OP予約注文取消
     * 先物OP予約注文更新サービス.cancel予約注文単位(引数の予約注文単位)を 
　@       コールする。
     */
    public void testSubmitIfoCancelOrderCase0002()
    {
        final String STR_METHOD_NAME = "testSubmitIfoCancelOrderCase0002()";
        log.entering(STR_METHOD_NAME);
    
        TestBaseForMock.MOCK_MANAGER.setIsMockUsed(true);
        try
        {
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                    "webbroker3.gentrade.WEB3GentradeOrderValidator",
                    "validateTradingPassword",
                    new Class[] { Trader.class, SubAccount.class, String.class },
                    OrderValidationResult.VALIDATION_OK_RESULT);
            
            //SubAccountParams
            TestDBUtility.deleteAll(SubAccountRow.TYPE);
            SubAccountParams l_subAccountParams = TestDBUtility.getSubAccountRow();
            l_subAccountParams.setAccountId(101001010010L);
            l_subAccountParams.setSubAccountId(10100101001007L);
            TestDBUtility.insertWithDel(l_subAccountParams);
            
            TestDBUtility.deleteAll(RsvIfoOrderUnitRow.TYPE);
            RsvIfoOrderUnitParams l_RsvIfoOrderUnitParams = TestDBUtility.getRsvIfoOrderUnitRow();
            l_RsvIfoOrderUnitParams.setOrderUnitId(1111);
            TestDBUtility.insertWithDel(l_RsvIfoOrderUnitParams);
            
            WEB3GentradeSubAccount l_subAccount = new WEB3GentradeSubAccount(l_subAccountParams);
    
            String l_strTradingPassword = "123";
            WEB3ToSuccIfoOrderUnitImpl l_toSuccIfoOrderUnitImpl =
                new WEB3ToSuccIfoOrderUnitImpl(l_RsvIfoOrderUnitParams);
    
            l_impl.submitIfoCancelOrder(
                l_subAccount, l_toSuccIfoOrderUnitImpl, 
                l_strTradingPassword);
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
     * validate先物OP注文取消
     * validate注文抛異常
     */
    public void testValidateIfoCancelOrderCase0001()
    {
        final String STR_METHOD_NAME = "testValidateIfoCancelOrderCase0001()";
        log.entering(STR_METHOD_NAME);

        TestBaseForMock.MOCK_MANAGER.setIsMockUsed(true);
        try
        {           
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                "webbroker3.ifo.WEB3OptionOrderManagerImpl",
                "validateOrder",
                new Class[] {SubAccount.class, String.class},
                new WEB3BusinessLayerException(WEB3ErrorCatalog.BUSINESS_ERROR_02063, ""));
            
            //SubAccountParams
            TestDBUtility.deleteAll(SubAccountRow.TYPE);
            SubAccountParams l_subAccountParams = TestDBUtility.getSubAccountRow();
            l_subAccountParams.setAccountId(101001010010L);
            l_subAccountParams.setSubAccountId(10100101001007L);
            TestDBUtility.insertWithDel(l_subAccountParams);
            
            TestDBUtility.deleteAll(RsvIfoOrderUnitRow.TYPE);
            RsvIfoOrderUnitParams l_RsvIfoOrderUnitParams = TestDBUtility.getRsvIfoOrderUnitRow();
            l_RsvIfoOrderUnitParams.setOrderUnitId(1111);
            TestDBUtility.insertWithDel(l_RsvIfoOrderUnitParams);
            
            WEB3GentradeSubAccount l_subAccount = new WEB3GentradeSubAccount(l_subAccountParams);
            
            WEB3ToSuccIfoOrderUnitImpl l_toSuccIfoOrderUnitImpl =
                new WEB3ToSuccIfoOrderUnitImpl(l_RsvIfoOrderUnitParams);

            l_impl.validateIfoCancelOrder(
                l_subAccount, l_toSuccIfoOrderUnitImpl);
            fail();
        }
        catch(WEB3BaseException l_ex)
        {
            log.debug(STR_METHOD_NAME, l_ex);
            assertEquals(l_ex.getErrorInfo(),WEB3ErrorCatalog.BUSINESS_ERROR_02063);
            log.info(STR_METHOD_NAME + ">>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>test pass !!");
            log.exiting(TEST_END + STR_METHOD_NAME);

        }
        catch (Exception l_ex)
        {
            log.error(ERROR + l_ex.getMessage(), l_ex);
            fail();
        }
    }
    
    /**
     * validate先物OP注文取消
     * validate取消可能状態抛異常
     */
    public void testValidateIfoCancelOrderCase0002()
    {
        final String STR_METHOD_NAME = "testValidateIfoCancelOrderCase0002()";
        log.entering(STR_METHOD_NAME);

        TestBaseForMock.MOCK_MANAGER.setIsMockUsed(true);
        try
        {           
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                "webbroker3.ifo.WEB3OptionOrderManagerImpl",
                "validateOrder",
                new Class[] {SubAccount.class, String.class},
                null);
            
            //SubAccountParams
            TestDBUtility.deleteAll(SubAccountRow.TYPE);
            SubAccountParams l_subAccountParams = TestDBUtility.getSubAccountRow();
            l_subAccountParams.setAccountId(101001010010L);
            l_subAccountParams.setSubAccountId(10100101001007L);
            TestDBUtility.insertWithDel(l_subAccountParams);
            
            TestDBUtility.deleteAll(RsvIfoOrderUnitRow.TYPE);
            RsvIfoOrderUnitParams l_RsvIfoOrderUnitParams = TestDBUtility.getRsvIfoOrderUnitRow();
            l_RsvIfoOrderUnitParams.setOrderUnitId(1111);
            l_RsvIfoOrderUnitParams.setOrderOpenStatus(OrderOpenStatusEnum.CLOSED);
            TestDBUtility.insertWithDel(l_RsvIfoOrderUnitParams);
            
            WEB3GentradeSubAccount l_subAccount = new WEB3GentradeSubAccount(l_subAccountParams);
            
            WEB3ToSuccIfoOrderUnitImpl l_toSuccIfoOrderUnitImpl =
                new WEB3ToSuccIfoOrderUnitImpl(l_RsvIfoOrderUnitParams);

            l_impl.validateIfoCancelOrder(
                l_subAccount, l_toSuccIfoOrderUnitImpl);
            fail();
        }
        catch(WEB3BaseException l_ex)
        {
            log.debug(STR_METHOD_NAME, l_ex);
            assertEquals(l_ex.getErrorInfo(),WEB3ErrorCatalog.BUSINESS_ERROR_02287);
            log.info(STR_METHOD_NAME + ">>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>test pass !!");
            log.exiting(TEST_END + STR_METHOD_NAME);

        }
        catch (Exception l_ex)
        {
            log.error(ERROR + l_ex.getMessage(), l_ex);
            fail();
        }
    }
    
    /**
     * get先物OP親注文内連番
     * 先物OP予約注文単位テーブル】を検索する。
     先頭レコードの（親注文内連番＋１）した結果を、親注文内連番として返却する
     */
    public void testGetIfoSerialNoInParentCase0001()
    {
        final String STR_METHOD_NAME = "testGetIfoSerialNoInParentCase0001()";
        log.entering(STR_METHOD_NAME);
        try
        {          
            TestDBUtility.deleteAll(RsvIfoOrderUnitRow.TYPE);
            RsvIfoOrderUnitParams l_RsvIfoOrderUnitParams = TestDBUtility.getRsvIfoOrderUnitRow();
            l_RsvIfoOrderUnitParams.setParentOrderUnitId(1111);
            l_RsvIfoOrderUnitParams.setSerialNoInParent(2);
            TestDBUtility.insertWithDel(l_RsvIfoOrderUnitParams);

            long l_l_lngIfoSerialNoInParent = l_impl.getIfoSerialNoInParent(1111);
            assertEquals(3, l_l_lngIfoSerialNoInParent);
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
     * get先物OP親注文内連番
     * 先物OP予約注文単位テーブル】を検索する。
         該当レコードなしの場合は、１を返却する
     */
    public void testGetIfoSerialNoInParentCase0002()
    {
        final String STR_METHOD_NAME = "testGetIfoSerialNoInParentCase0002()";
        log.entering(STR_METHOD_NAME);
        try
        {          
            TestDBUtility.deleteAll(RsvIfoOrderUnitRow.TYPE);

            long l_l_lngIfoSerialNoInParent = l_impl.getIfoSerialNoInParent(1111);
            assertEquals(1, l_l_lngIfoSerialNoInParent);
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
     * set予約注文設定To先物OP親注文
     *引数の親注文の注文単位より、cloneオブジェクトを作成する。 
        生成したcloneオブジェクトに、以下のプロパティを再セットする。 
        予約注文設定フラグ：　@"設定の可能性あり" 
        更新日付：　@GtlUtils.getSystemTimestamp()
     */
    public void testSetReserveOrderSettingToIfoParentOrderCase0001()
    {
        final String STR_METHOD_NAME = "testSetReserveOrderSettingToIfoParentOrderCase0001()";
        log.entering(STR_METHOD_NAME);
        try
        {          
            TestDBUtility.deleteAll(IfoOrderUnitRow.TYPE);
            IfoOrderUnitParams l_ifoOrderUnitParams = TestDBUtility.getIfoOrderUnitRow();
            l_ifoOrderUnitParams.setOrderUnitId(1111);
            l_ifoOrderUnitParams.setReserveOrderExistFlag("2");
            l_ifoOrderUnitParams.setLastUpdatedTimestamp(WEB3DateUtility.getDate("20070102", "yyyyMMdd"));
            TestDBUtility.insertWithDel(l_ifoOrderUnitParams);
            
            IfoOrderUnit l_ifoOrderUnit = new IfoContractOpenOrderUnitImpl(1111);

            l_impl.setReserveOrderSettingToIfoParentOrder(l_ifoOrderUnit);
            
            IfoOrderUnitRow l_ifoOrderUnitRow = IfoOrderUnitDao.findRowByPk(1111);
            assertEquals("1", l_ifoOrderUnitRow.getReserveOrderExistFlag());
            assertEquals(0, WEB3DateUtility.compareToSecond(GtlUtils.getSystemTimestamp(),
                l_ifoOrderUnitRow.getLastUpdatedTimestamp())); 
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
     * create返済建玉エントリ 
     * SettleContractEntryインスタンスを 
         作成し、そのインスタンスのみを要素とする配列を返却する。
     */
    public void testCreateSettleContractEntryCase0001()
    {
        final String STR_METHOD_NAME = "testCreateSettleContractEntryCase0001()";
        log.entering(STR_METHOD_NAME);
        try
        {          
            WEB3FuturesOptionsCloseMarginContractUnit l_unit = new WEB3FuturesOptionsCloseMarginContractUnit();
            l_unit.contractOrderQuantity = "100";
            WEB3FuturesOptionsCloseMarginContractUnit[] l_units =
                new WEB3FuturesOptionsCloseMarginContractUnit[1];
            l_units[0] = l_unit;

            SettleContractEntry[] l_settleContractEntrys = l_impl.createSettleContractEntry(l_units);
            assertEquals(0, l_settleContractEntrys[0].getContractId());
            assertEquals("100.0", l_settleContractEntrys[0].getQuantity() + ""); 
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
     * create建玉明細
        注文単位より建玉明細を作成する
        生成したインスタンスに以下のプロパティをセットする。 
        以下の項目以外はnullをセットする。 
        建日 = 注文単位.発注日 
        建玉数 = 注文単位.注文数量 
     */
    public void testCreateContractUnitCase0001()
    {
        final String STR_METHOD_NAME = "testCreateContractUnitCase0001()";
        log.entering(STR_METHOD_NAME);
        try
        {         
            TestDBUtility.deleteAll(IfoOrderUnitRow.TYPE);
            IfoOrderUnitParams l_ifoOrderUnitParams = TestDBUtility.getIfoOrderUnitRow();
            l_ifoOrderUnitParams.setOrderUnitId(1111);
            l_ifoOrderUnitParams.setQuantity(200);
            l_ifoOrderUnitParams.setBizDate("20070102");
            TestDBUtility.insertWithDel(l_ifoOrderUnitParams);
            
            IfoOrderUnit l_ifoOrderUnit = new IfoContractOpenOrderUnitImpl(1111);

            WEB3FuturesOptionsContractUnit l_unit = l_impl.createContractUnit(l_ifoOrderUnit);
            assertEquals(WEB3DateUtility.getDate("20070102", "yyyyMMdd"), l_unit.openDate);
            assertEquals("200", l_unit.contractQuantity);
            
        }
        catch (Exception l_ex)
        {
            log.error(l_ex.getMessage(), l_ex);
            fail();
        }
        log.info(STR_METHOD_NAME + ">>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>test pass !!");
        log.exiting(TEST_END + STR_METHOD_NAME);
    }
    
//    /**
//     * create建玉照会明細
//        注文単位より建玉照会明細を作成する
//        生成したインスタンスに以下のプロパティをセットする。 
//        以下の項目以外はnullをセットする。 
//        指数種別 = 注文単位.先物OP銘柄.原資産銘柄コード 
//        限月 = 注文単位.先物OP銘柄.限月 
//        取引市場 = 注文単位.市場コード（SONAR） 
//        建区分 = 注文単位.getSide() == "買"の場合、"買建"
//        建日 = 注文単位.発注日 
//        建数量 = 注文単位.注文数量 
//     */
//    public void testCreateContractReferenceUnitCase0001()
//    {
//        final String STR_METHOD_NAME = "testCreateContractReferenceUnitCase0001()";
//        log.entering(STR_METHOD_NAME);
//        try
//        {         
//            TestDBUtility.deleteAll(IfoOrderUnitRow.TYPE);
//            IfoOrderUnitParams l_ifoOrderUnitParams = TestDBUtility.getIfoOrderUnitRow();
//            l_ifoOrderUnitParams.setOrderUnitId(1111);
//            l_ifoOrderUnitParams.setQuantity(200);
//            l_ifoOrderUnitParams.setBizDate("20070102");
//            l_ifoOrderUnitParams.setSonarMarketCode("2");
//            l_ifoOrderUnitParams.setOrderType(OrderTypeEnum.IDX_FUTURES_BUY_TO_OPEN);
//            l_ifoOrderUnitParams.setProductId(1006169090018L);
//            TestDBUtility.insertWithDel(l_ifoOrderUnitParams);
//            
//            //ProductParams
//            TestDBUtility.deleteAll(ProductRow.TYPE);
//            ProductParams l_productParams = TestDBUtility.getProductRow();
//            l_productParams.setProductId(1006169090018L);
//            l_productParams.setProductType(ProductTypeEnum.IFO);
//            TestDBUtility.insertWithDel(l_productParams);
//            
//            TestDBUtility.deleteAll(IfoProductRow.TYPE);
//            IfoProductParams l_ifoProductParams = TestDBUtility.getIfoProductRow();
//            l_ifoProductParams.setProductId(1006169090018L);
//            l_ifoProductParams.setUnderlyingProductCode("0016");
//            l_ifoProductParams.setMonthOfDelivery("200804");
//            TestDBUtility.insertWithDel(l_ifoProductParams);
//            
//            IfoOrderUnit l_ifoOrderUnit = new IfoContractOpenOrderUnitImpl(1111);
//
//            WEB3FuturesContractReferenceUnit l_unit = l_impl.createContractReferenceUnit(l_ifoOrderUnit);
//            assertEquals("0016", l_unit.targetProductCode);
//            assertEquals("200804", l_unit.delivaryMonth);
//            assertEquals("2", l_unit.marketCode);
//            assertEquals("1", l_unit.contractType);
//            assertEquals(WEB3DateUtility.getDate("20070102", "yyyyMMdd"), l_unit.openDate);
//            assertEquals("200", l_unit.contractOrderQuantity);
//            
//        }
//        catch (Exception l_ex)
//        {
//            log.error(l_ex.getMessage(), l_ex);
//            fail();
//        }
//        log.info(STR_METHOD_NAME + ">>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>test pass !!");
//        log.exiting(TEST_END + STR_METHOD_NAME);
//    }
    
//    /**
//     * create建玉照会明細
//        注文単位より建玉照会明細を作成する
//        生成したインスタンスに以下のプロパティをセットする。 
//        以下の項目以外はnullをセットする。 
//        指数種別 = 注文単位.先物OP銘柄.原資産銘柄コード 
//        限月 = 注文単位.先物OP銘柄.限月 
//        取引市場 = 注文単位.市場コード（SONAR） 
//        建区分 = 注文単位.getSide() != "買"の場合、"売建"
//        建日 = 注文単位.発注日 
//        建数量 = 注文単位.注文数量 
//     */
//    public void testCreateContractReferenceUnitCase0002()
//    {
//        final String STR_METHOD_NAME = "testCreateContractReferenceUnitCase0002()";
//        log.entering(STR_METHOD_NAME);
//        try
//        {         
//            TestDBUtility.deleteAll(IfoOrderUnitRow.TYPE);
//            IfoOrderUnitParams l_ifoOrderUnitParams = TestDBUtility.getIfoOrderUnitRow();
//            l_ifoOrderUnitParams.setOrderUnitId(1111);
//            l_ifoOrderUnitParams.setQuantity(200);
//            l_ifoOrderUnitParams.setBizDate("20070102");
//            l_ifoOrderUnitParams.setSonarMarketCode("2");
//            l_ifoOrderUnitParams.setOrderType(OrderTypeEnum.IDX_FUTURES_SELL_TO_CLOSE);
//            l_ifoOrderUnitParams.setProductId(1006169090018L);
//            TestDBUtility.insertWithDel(l_ifoOrderUnitParams);
//            
//            //ProductParams
//            TestDBUtility.deleteAll(ProductRow.TYPE);
//            ProductParams l_productParams = TestDBUtility.getProductRow();
//            l_productParams.setProductId(1006169090018L);
//            l_productParams.setProductType(ProductTypeEnum.IFO);
//            TestDBUtility.insertWithDel(l_productParams);
//            
//            TestDBUtility.deleteAll(IfoProductRow.TYPE);
//            IfoProductParams l_ifoProductParams = TestDBUtility.getIfoProductRow();
//            l_ifoProductParams.setProductId(1006169090018L);
//            l_ifoProductParams.setUnderlyingProductCode("0016");
//            l_ifoProductParams.setMonthOfDelivery("200804");
//            TestDBUtility.insertWithDel(l_ifoProductParams);
//            
//            IfoOrderUnit l_ifoOrderUnit = new IfoContractOpenOrderUnitImpl(1111);
//
//            WEB3FuturesContractReferenceUnit l_unit = l_impl.createContractReferenceUnit(l_ifoOrderUnit);
//            assertEquals("0016", l_unit.targetProductCode);
//            assertEquals("200804", l_unit.delivaryMonth);
//            assertEquals("2", l_unit.marketCode);
//            assertEquals("2", l_unit.contractType);
//            assertEquals(WEB3DateUtility.getDate("20070102", "yyyyMMdd"), l_unit.openDate);
//            assertEquals("200", l_unit.contractOrderQuantity);
//            
//        }
//        catch (Exception l_ex)
//        {
//            log.error(l_ex.getMessage(), l_ex);
//            fail();
//        }
//        log.info(STR_METHOD_NAME + ">>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>test pass !!");
//        log.exiting(TEST_END + STR_METHOD_NAME);
//    }
    
    /**
     * get連続注文取扱商品一覧
        券会社が取扱っている連続注文の商品一覧を返却する
        要素が"現物株式"or"信用取引"の場合
        validate連続注文取扱可能()が例外をスローする
        　@要素が"先物"or"オプション"の場合
        validate連続注文取扱可能()が例外をスローしない
        作成したArrayListを返す
     */
    public void testGetToSuccOrderDealtCommodityListCase0001()
    {
        final String STR_METHOD_NAME = "testGetToSuccOrderDealtCommodityListCase0001()";
        log.entering(STR_METHOD_NAME);
        try
        {         
            TestDBUtility.deleteAll(EnableOrderConditionRow.TYPE);
            EnableOrderConditionParams l_enableOrderConditionParams =
                TestDBUtility.getEnableOrderConditionParamsRow();
            l_enableOrderConditionParams.setMarketCode("0");
            l_enableOrderConditionParams.setFutureOptionDiv("1");
            l_enableOrderConditionParams.setMarginTradingDiv("0");
            l_enableOrderConditionParams.setProductType(ProductTypeEnum.IFO);
            TestDBUtility.insertWithDel(l_enableOrderConditionParams);
            
            EnableOrderConditionParams l_enableOrderConditionParams1 =
                TestDBUtility.getEnableOrderConditionParamsRow();
            l_enableOrderConditionParams1.setMarketCode("0");
            l_enableOrderConditionParams1.setFutureOptionDiv("2");
            l_enableOrderConditionParams1.setMarginTradingDiv("0");
            l_enableOrderConditionParams1.setProductType(ProductTypeEnum.IFO);
            TestDBUtility.insertWithDel(l_enableOrderConditionParams1);
            
            EnableOrderConditionParams l_enableOrderConditionParams2 =
                TestDBUtility.getEnableOrderConditionParamsRow();
            l_enableOrderConditionParams2.setMarketCode("0");
            l_enableOrderConditionParams2.setFutureOptionDiv("0");
            l_enableOrderConditionParams2.setMarginTradingDiv("0");
            l_enableOrderConditionParams2.setProductType(ProductTypeEnum.EQUITY);
            l_enableOrderConditionParams2.setChainOrder("0");
            TestDBUtility.insertWithDel(l_enableOrderConditionParams2);

            String[] l_strGetToSuccOrderDealtCommodityList =
                l_impl.getToSuccOrderDealtCommodityList(l_enableOrderConditionParams.getInstitutionCode());
            
            assertEquals(2, l_strGetToSuccOrderDealtCommodityList.length);
            assertEquals("3", l_strGetToSuccOrderDealtCommodityList[0]);
            assertEquals("4", l_strGetToSuccOrderDealtCommodityList[1]);
            
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
     * create先物OP注文単位
     * 先物OP注文単位Paramsインスタンスを生成する。 
     * 予約注文単位.注文カテゴリ＝先物新規建注文
         予約注文単位.先物／オプション区分＝先物
     */
    public void testCreateIfoOrderUnitCase0001()
    {
        final String STR_METHOD_NAME = "testCreateIfoOrderUnitCase0001()";
        log.entering(STR_METHOD_NAME);
        try
        { 
            TestDBUtility.deleteAll(RsvIfoOrderUnitRow.TYPE);
            RsvIfoOrderUnitParams l_rsvIfoOrderUnitParams = TestDBUtility.getRsvIfoOrderUnitRow();
            l_rsvIfoOrderUnitParams.setOrderId(1111);
            l_rsvIfoOrderUnitParams.setParentOrderId(2222);
            l_rsvIfoOrderUnitParams.setSerialNoInParent(1);
            l_rsvIfoOrderUnitParams.setProductId(1006169090018L);
            l_rsvIfoOrderUnitParams.setOrderCateg(OrderCategEnum.IDX_FUTURES_OPEN);
            l_rsvIfoOrderUnitParams.setFutureOptionDiv("1");
            l_rsvIfoOrderUnitParams.setMarketId(3303);
            l_rsvIfoOrderUnitParams.setTraderId(2222);
            l_rsvIfoOrderUnitParams.setLimitPrice(300);
            l_rsvIfoOrderUnitParams.setPrice(200);
            l_rsvIfoOrderUnitParams.setEstimatedPrice(400);
            l_rsvIfoOrderUnitParams.setFirstOrderUnitId(5555);
            TestDBUtility.insertWithDel(l_rsvIfoOrderUnitParams);
            
            //MarketParams
            TestDBUtility.deleteAll(MarketRow.TYPE);
            MarketParams l_marketParams = TestDBUtility.getMarketRow();
            l_marketParams.setMarketId(l_rsvIfoOrderUnitParams.getMarketId());
            l_marketParams.setSonarMarketCode("8");
            TestDBUtility.insertWithDel(l_marketParams);
            
            //ProductParams
            TestDBUtility.deleteAll(ProductRow.TYPE);
            ProductParams l_productParams = TestDBUtility.getProductRow();
            l_productParams.setProductId(1006169090018L);
            l_productParams.setProductType(ProductTypeEnum.IFO);
            TestDBUtility.insertWithDel(l_productParams);
            
            TestDBUtility.deleteAll(IfoProductRow.TYPE);
            IfoProductParams l_ifoProductParams = TestDBUtility.getIfoProductRow();
            l_ifoProductParams.setProductId(1006169090018L);
            TestDBUtility.insertWithDel(l_ifoProductParams);
            
            TestDBUtility.deleteAll(TradedProductRow.TYPE);
            TradedProductParams l_tradedProductParams = TestDBUtility.getTradedProductRow();
            l_tradedProductParams.setProductId(1006169090018L);
            l_tradedProductParams.setProductType(ProductTypeEnum.IFO);
            l_tradedProductParams.setDailyDeliveryDate(WEB3DateUtility.getDate("20080401", "yyyyMMdd"));
            l_tradedProductParams.setMarketId(l_rsvIfoOrderUnitParams.getMarketId());
            TestDBUtility.insertWithDel(l_tradedProductParams);
            
            TestDBUtility.deleteAll(IfoTradedProductRow.TYPE);
            IfoTradedProductParams l_ifoTradedProductParams = TestDBUtility.getIfoTradedProductRow();
            l_ifoTradedProductParams.setValidForBizDate(null);
            l_ifoTradedProductParams.setTradedProductId(l_tradedProductParams.getTradedProductId());
            l_ifoTradedProductParams.setProductId(1006169090018L);
            TestDBUtility.insertWithDel(l_ifoTradedProductParams);
            
            TestDBUtility.deleteAll(IfoTradedProductUpdqRow.TYPE);
            IfoTradedProductUpdqParams l_ifoTradedProductUpdqParams = TestDBUtility.getIfoTradedProductUpdqRow();
            l_ifoTradedProductUpdqParams.setProductId(1006169090018L);
            Date l_datBizDate = GtlUtils.getTradingSystem().getSystemTimestamp();
            Date l_datpreBizDate = new WEB3GentradeBizDate(
                new Timestamp(l_datBizDate.getTime())).roll(1);
            String l_strCreateDate = WEB3DateUtility.formatDate(l_datpreBizDate, "yyyyMMdd");
            l_ifoTradedProductUpdqParams.setValidForBizDate(l_strCreateDate); 
            TestDBUtility.insertWithDel(l_ifoTradedProductUpdqParams);
            
            WEB3ToSuccIfoOrderUnitImpl l_rsvIfoOrderUnit = new WEB3ToSuccIfoOrderUnitImpl(l_rsvIfoOrderUnitParams);
            IfoOrderUnit l_IfoOrderUnit = l_impl.createIfoOrderUnit(l_rsvIfoOrderUnit);
            
            IfoOrderUnitRow l_ifoOrderUnitRow = (IfoOrderUnitRow)l_IfoOrderUnit.getDataSourceObject();
            
            assertEquals(-1, l_ifoOrderUnitRow.getOrderUnitId());
            assertEquals(l_rsvIfoOrderUnitParams.getAccountId(), l_ifoOrderUnitRow.getAccountId());
            assertEquals(l_rsvIfoOrderUnitParams.getSubAccountId(), l_ifoOrderUnitRow.getSubAccountId());
            assertEquals(l_rsvIfoOrderUnitParams.getBranchId(), l_ifoOrderUnitRow.getBranchId());
            assertEquals(l_rsvIfoOrderUnitParams.getTraderId(), l_ifoOrderUnitRow.getTraderId());
            assertEquals(l_rsvIfoOrderUnitParams.getOrderId(), l_ifoOrderUnitRow.getOrderId());
            assertEquals(l_rsvIfoOrderUnitParams.getOrderType(), l_ifoOrderUnitRow.getOrderType());
            assertEquals(l_rsvIfoOrderUnitParams.getOrderCateg(), l_ifoOrderUnitRow.getOrderCateg());
            assertEquals(l_rsvIfoOrderUnitParams.getLastOrderActionSerialNo(),
                l_ifoOrderUnitRow.getLastOrderActionSerialNo());
            assertEquals(0, l_ifoOrderUnitRow.getLastExecutionSerialNo());
            assertEquals(l_rsvIfoOrderUnitParams.getProductType(), l_ifoOrderUnitRow.getProductType());
            assertEquals(l_rsvIfoOrderUnitParams.getFutureOptionDiv(), l_ifoOrderUnitRow.getFutureOptionDiv());
            assertEquals(l_rsvIfoOrderUnitParams.getMarketId(), l_ifoOrderUnitRow.getMarketId());
            assertEquals(l_rsvIfoOrderUnitParams.getQuantity() + "", l_ifoOrderUnitRow.getQuantity() + "");
            assertEquals(l_rsvIfoOrderUnitParams.getLimitPrice() + "", l_ifoOrderUnitRow.getLimitPrice() + "");
            assertEquals(1, l_ifoOrderUnitRow.getExecutionConditionType().intValue());
            assertEquals("0", l_ifoOrderUnitRow.getOrderConditionType());
            assertEquals("20080401", 
                WEB3DateUtility.formatDate(l_ifoOrderUnitRow.getDeliveryDate(), "yyyyMMdd"));
            assertEquals(l_rsvIfoOrderUnitParams.getExpirationDate(), l_ifoOrderUnitRow.getExpirationDate());
            assertEquals(l_rsvIfoOrderUnitParams.getOrderStatus(), l_ifoOrderUnitRow.getOrderStatus());
            assertEquals(l_rsvIfoOrderUnitParams.getOrderOpenStatus(), l_ifoOrderUnitRow.getOrderOpenStatus());
            assertEquals(l_rsvIfoOrderUnitParams.getExpirationStatus(), l_ifoOrderUnitRow.getExpirationStatus());
            assertEquals(l_rsvIfoOrderUnitParams.getTaxType(), l_ifoOrderUnitRow.getTaxType());
            assertEquals(l_rsvIfoOrderUnitParams.getBizDate(), l_ifoOrderUnitRow.getBizDate());
            assertEquals(l_rsvIfoOrderUnitParams.getProductId(), l_ifoOrderUnitRow.getProductId());
            assertEquals(l_rsvIfoOrderUnitParams.getOrderChanel(), l_ifoOrderUnitRow.getOrderChanel());
            assertEquals(l_rsvIfoOrderUnitParams.getReceivedDateTime(), l_ifoOrderUnitRow.getReceivedDateTime());
            assertEquals(l_rsvIfoOrderUnitParams.getSonarTraderCode(), l_ifoOrderUnitRow.getSonarTraderCode());
            assertEquals(l_rsvIfoOrderUnitParams.getPrice() + "", l_ifoOrderUnitRow.getPrice() + "");
            assertEquals(l_rsvIfoOrderUnitParams.getEstimatedPrice() + "", l_ifoOrderUnitRow.getEstimatedPrice() + "");
            assertEquals("51", l_ifoOrderUnitRow.getSonarTradedCode());
            assertEquals("8", l_ifoOrderUnitRow.getSonarMarketCode());
            assertEquals("50", l_ifoOrderUnitRow.getCommProductCode());
            assertEquals(l_rsvIfoOrderUnitParams.getOrderRootDiv(), l_ifoOrderUnitRow.getOrderRootDiv());
            assertEquals(l_rsvIfoOrderUnitParams.getClosingOrder(), l_ifoOrderUnitRow.getClosingOrder());
            assertEquals(l_rsvIfoOrderUnitParams.getFirstOrderUnitId(), l_ifoOrderUnitRow.getFirstOrderUnitId());
            assertEquals(l_rsvIfoOrderUnitParams.getCreatedTimestamp(), l_ifoOrderUnitRow.getCreatedTimestamp());
            assertEquals(l_rsvIfoOrderUnitParams.getLastUpdatedTimestamp(), l_ifoOrderUnitRow.getLastUpdatedTimestamp());
            assertEquals(l_rsvIfoOrderUnitParams.getEveningSessionCarryoverFlag(), 
                l_ifoOrderUnitRow.getEveningSessionCarryoverFlag());
            assertEquals(l_rsvIfoOrderUnitParams.getSessionType(), l_ifoOrderUnitRow.getSessionType());
            assertNull(l_ifoOrderUnitRow.getDayTradeType());
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
     * create先物OP注文単位
     * 先物OP注文単位Paramsインスタンスを生成する。 
     * 予約注文単位.注文カテゴリ＝先物返済注文
         予約注文単位.先物／オプション区分 !＝先物
     */
    public void testCreateIfoOrderUnitCase0002()
    {
        final String STR_METHOD_NAME = "testCreateIfoOrderUnitCase0002()";
        log.entering(STR_METHOD_NAME);
        try
        { 
            TestDBUtility.deleteAll(RsvIfoOrderUnitRow.TYPE);
            RsvIfoOrderUnitParams l_rsvIfoOrderUnitParams = TestDBUtility.getRsvIfoOrderUnitRow();
            l_rsvIfoOrderUnitParams.setOrderId(1111);
            l_rsvIfoOrderUnitParams.setParentOrderId(2222);
            l_rsvIfoOrderUnitParams.setSerialNoInParent(1);
            l_rsvIfoOrderUnitParams.setProductId(1006169090018L);
            l_rsvIfoOrderUnitParams.setOrderCateg(OrderCategEnum.IDX_FUTURES_CLOSE);
            l_rsvIfoOrderUnitParams.setFutureOptionDiv("2");
            l_rsvIfoOrderUnitParams.setMarketId(3303);
            TestDBUtility.insertWithDel(l_rsvIfoOrderUnitParams);
            
            //MarketParams
            TestDBUtility.deleteAll(MarketRow.TYPE);
            MarketParams l_marketParams = TestDBUtility.getMarketRow();
            l_marketParams.setMarketId(l_rsvIfoOrderUnitParams.getMarketId());
            l_marketParams.setSonarMarketCode("8");
            TestDBUtility.insertWithDel(l_marketParams);
            
            //ProductParams
            TestDBUtility.deleteAll(ProductRow.TYPE);
            ProductParams l_productParams = TestDBUtility.getProductRow();
            l_productParams.setProductId(1006169090018L);
            l_productParams.setProductType(ProductTypeEnum.IFO);
            TestDBUtility.insertWithDel(l_productParams);
            
            TestDBUtility.deleteAll(IfoProductRow.TYPE);
            IfoProductParams l_ifoProductParams = TestDBUtility.getIfoProductRow();
            l_ifoProductParams.setProductId(1006169090018L);
            TestDBUtility.insertWithDel(l_ifoProductParams);
            
            TestDBUtility.deleteAll(TradedProductRow.TYPE);
            TradedProductParams l_tradedProductParams = TestDBUtility.getTradedProductRow();
            l_tradedProductParams.setProductId(1006169090018L);
            l_tradedProductParams.setProductType(ProductTypeEnum.IFO);
            l_tradedProductParams.setDailyDeliveryDate(WEB3DateUtility.getDate("20080401", "yyyyMMdd"));
            l_tradedProductParams.setMarketId(l_rsvIfoOrderUnitParams.getMarketId());
            TestDBUtility.insertWithDel(l_tradedProductParams);
            
            TestDBUtility.deleteAll(IfoTradedProductRow.TYPE);
            IfoTradedProductParams l_ifoTradedProductParams = TestDBUtility.getIfoTradedProductRow();
            l_ifoTradedProductParams.setValidForBizDate(null);
            l_ifoTradedProductParams.setTradedProductId(l_tradedProductParams.getTradedProductId());
            l_ifoTradedProductParams.setProductId(1006169090018L);
            TestDBUtility.insertWithDel(l_ifoTradedProductParams);
            
            TestDBUtility.deleteAll(IfoTradedProductUpdqRow.TYPE);
            IfoTradedProductUpdqParams l_ifoTradedProductUpdqParams = TestDBUtility.getIfoTradedProductUpdqRow();
            l_ifoTradedProductUpdqParams.setProductId(1006169090018L);
            Date l_datBizDate = GtlUtils.getTradingSystem().getSystemTimestamp();
            Date l_datpreBizDate = new WEB3GentradeBizDate(
                new Timestamp(l_datBizDate.getTime())).roll(1);
            String l_strCreateDate = WEB3DateUtility.formatDate(l_datpreBizDate, "yyyyMMdd");
            l_ifoTradedProductUpdqParams.setValidForBizDate(l_strCreateDate); 
            TestDBUtility.insertWithDel(l_ifoTradedProductUpdqParams);
            
            WEB3ToSuccIfoOrderUnitImpl l_rsvIfoOrderUnit = new WEB3ToSuccIfoOrderUnitImpl(l_rsvIfoOrderUnitParams);
            IfoOrderUnit l_IfoOrderUnit = l_impl.createIfoOrderUnit(l_rsvIfoOrderUnit);
            
            IfoOrderUnitRow l_ifoOrderUnitRow = (IfoOrderUnitRow)l_IfoOrderUnit.getDataSourceObject();
            
            assertEquals("52", l_ifoOrderUnitRow.getSonarTradedCode());
            assertEquals("51", l_ifoOrderUnitRow.getCommProductCode());
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
     * create先物OP注文単位
     * 予約注文単位.注文カテゴリ！＝先物新規建注文、
     * 又はOP新規建注文又は先物返済注文、又はOP返済注文 の場合例外をthrowする。  
     */
    public void testCreateIfoOrderUnitCase0003()
    {
        final String STR_METHOD_NAME = "testCreateIfoOrderUnitCase0003()";
        log.entering(STR_METHOD_NAME);
        try
        { 
            TestDBUtility.deleteAll(RsvIfoOrderUnitRow.TYPE);
            RsvIfoOrderUnitParams l_rsvIfoOrderUnitParams = TestDBUtility.getRsvIfoOrderUnitRow();
            l_rsvIfoOrderUnitParams.setOrderId(1111);
            l_rsvIfoOrderUnitParams.setParentOrderId(2222);
            l_rsvIfoOrderUnitParams.setSerialNoInParent(1);
            l_rsvIfoOrderUnitParams.setProductId(1006169090018L);
            l_rsvIfoOrderUnitParams.setOrderCateg(OrderCategEnum.CASH_TRANSFER);
            l_rsvIfoOrderUnitParams.setFutureOptionDiv("2");
            l_rsvIfoOrderUnitParams.setMarketId(3303);
            TestDBUtility.insertWithDel(l_rsvIfoOrderUnitParams);
            
            //MarketParams
            TestDBUtility.deleteAll(MarketRow.TYPE);
            MarketParams l_marketParams = TestDBUtility.getMarketRow();
            l_marketParams.setMarketId(l_rsvIfoOrderUnitParams.getMarketId());
            l_marketParams.setSonarMarketCode("8");
            TestDBUtility.insertWithDel(l_marketParams);
            
            //ProductParams
            TestDBUtility.deleteAll(ProductRow.TYPE);
            ProductParams l_productParams = TestDBUtility.getProductRow();
            l_productParams.setProductId(1006169090018L);
            l_productParams.setProductType(ProductTypeEnum.IFO);
            TestDBUtility.insertWithDel(l_productParams);
            
            TestDBUtility.deleteAll(IfoProductRow.TYPE);
            IfoProductParams l_ifoProductParams = TestDBUtility.getIfoProductRow();
            l_ifoProductParams.setProductId(1006169090018L);
            TestDBUtility.insertWithDel(l_ifoProductParams);
            
            TestDBUtility.deleteAll(TradedProductRow.TYPE);
            TradedProductParams l_tradedProductParams = TestDBUtility.getTradedProductRow();
            l_tradedProductParams.setProductId(1006169090018L);
            l_tradedProductParams.setProductType(ProductTypeEnum.IFO);
            l_tradedProductParams.setDailyDeliveryDate(WEB3DateUtility.getDate("20080401", "yyyyMMdd"));
            l_tradedProductParams.setMarketId(l_rsvIfoOrderUnitParams.getMarketId());
            TestDBUtility.insertWithDel(l_tradedProductParams);
            
            TestDBUtility.deleteAll(IfoTradedProductRow.TYPE);
            IfoTradedProductParams l_ifoTradedProductParams = TestDBUtility.getIfoTradedProductRow();
            l_ifoTradedProductParams.setValidForBizDate(null);
            l_ifoTradedProductParams.setTradedProductId(l_tradedProductParams.getTradedProductId());
            l_ifoTradedProductParams.setProductId(1006169090018L);
            TestDBUtility.insertWithDel(l_ifoTradedProductParams);
            
            TestDBUtility.deleteAll(IfoTradedProductUpdqRow.TYPE);
            IfoTradedProductUpdqParams l_ifoTradedProductUpdqParams = TestDBUtility.getIfoTradedProductUpdqRow();
            l_ifoTradedProductUpdqParams.setProductId(1006169090018L);
            Date l_datBizDate = GtlUtils.getTradingSystem().getSystemTimestamp();
            Date l_datpreBizDate = new WEB3GentradeBizDate(
                new Timestamp(l_datBizDate.getTime())).roll(1);
            String l_strCreateDate = WEB3DateUtility.formatDate(l_datpreBizDate, "yyyyMMdd");
            l_ifoTradedProductUpdqParams.setValidForBizDate(l_strCreateDate); 
            TestDBUtility.insertWithDel(l_ifoTradedProductUpdqParams);
            
            WEB3ToSuccIfoOrderUnitImpl l_rsvIfoOrderUnit = new WEB3ToSuccIfoOrderUnitImpl(l_rsvIfoOrderUnitParams);
            l_impl.createIfoOrderUnit(l_rsvIfoOrderUnit);
            fail();
        }
        catch(WEB3BaseException l_ex)
        {
            log.debug(STR_METHOD_NAME, l_ex);
            assertEquals(l_ex.getErrorInfo(),WEB3ErrorCatalog.BUSINESS_ERROR_00653);
            log.info(STR_METHOD_NAME + ">>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>test pass !!");
            log.exiting(TEST_END + STR_METHOD_NAME);

        }
        catch (Exception l_ex)
        {
            log.error(ERROR + l_ex.getMessage(), l_ex);
            fail();
        }
    }
    
    /**
     * get先物OP予約注文単位
     * 【先物OP予約注文単位テーブル】を検索する。 
         先物OP予約注文単位オブジェクトを返却する。
     */
    public void testGetReserveIfoOrderUnitCase0001()
    {
        final String STR_METHOD_NAME = "testGetReserveIfoOrderUnitCase0001()";
        log.entering(STR_METHOD_NAME);
        try
        {
            TestDBUtility.deleteAll(RsvIfoOrderUnitRow.TYPE);
            RsvIfoOrderUnitParams l_rsvIfoOrderUnitParams = TestDBUtility.getRsvIfoOrderUnitRow();
            l_rsvIfoOrderUnitParams.setOrderId(1111);
            TestDBUtility.insertWithDel(l_rsvIfoOrderUnitParams);
            
            WEB3ToSuccIfoOrderUnitImpl l_ifoOrderUnitImpl =
                (WEB3ToSuccIfoOrderUnitImpl)l_impl.getReserveIfoOrderUnit(1111);
            assertEquals(1111, l_ifoOrderUnitImpl.getOrderId());
            
            
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
     * toOrderUnit 
        引数の注文単位Rowの型が先物OP予約注文単位Rowの場合
        引数の注文単位Rowオブジェクトを引数に指定して、 
        先物OP予約注文単位オブジェクトを生成する。 
        生成したオブジェクトを返却する。
     *
     */
    public void testToOrderUnitCase0001()
    {
        final String STR_METHOD_NAME = "testToOrderUnitCase0001()";
        log.entering(STR_METHOD_NAME);
        try
        {
            TestDBUtility.deleteAll(RsvIfoOrderUnitRow.TYPE);
            RsvIfoOrderUnitParams l_rsvIfoOrderUnitParams = TestDBUtility.getRsvIfoOrderUnitRow();
            l_rsvIfoOrderUnitParams.setOrderId(1111);
            TestDBUtility.insertWithDel(l_rsvIfoOrderUnitParams);
            
            
            OrderUnit l_orderUnit = l_impl.toOrderUnit(l_rsvIfoOrderUnitParams);
            WEB3ToSuccIfoOrderUnitImpl l_ifoOrderUnitImpl = (WEB3ToSuccIfoOrderUnitImpl)l_orderUnit;
            assertEquals(1111, l_ifoOrderUnitImpl.getOrderId());
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
     * validate連続注文最大設定数
     * 　@[先物OP注文単位の場合] 
         this.get有効先物OP子注文単位一覧()をコールする。 
     *
     */
    public void testVlidateSuccOrderMaxQuantityCase0001()
    {
        final String STR_METHOD_NAME = "testVlidateSuccOrderMaxQuantityCase0001()";
        log.entering(STR_METHOD_NAME);
        try
        {
            TestDBUtility.deleteAll(RsvIfoOrderUnitRow.TYPE);
            RsvIfoOrderUnitParams l_rsvIfoOrderUnitParams = TestDBUtility.getRsvIfoOrderUnitRow();
            l_rsvIfoOrderUnitParams.setOrderId(1111);
            TestDBUtility.insertWithDel(l_rsvIfoOrderUnitParams);
            
            TestDBUtility.deleteAll(IfoOrderUnitRow.TYPE);
            IfoOrderUnitParams l_ifoOrderUnitParams = TestDBUtility.getIfoOrderUnitRow();
            l_ifoOrderUnitParams.setOrderId(1111);
            TestDBUtility.insertWithDel(l_ifoOrderUnitParams);
            IfoOrderUnit l_ifoOrderUnit = new IfoContractOpenOrderUnitImpl(1001);
            
            l_impl = new WEB3ToSuccOrderManagerImplForTest();
            l_impl.validateSuccOrderMaxQuantity(l_ifoOrderUnit);

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
     * get連続注文最大設定件数
     * 部店プリファ@レンステーブルを以下の条件で 検索する。 
     * プリファ@レンス名の連番 = パラメータ.親注文の注文単位の型が、先物OP注文単位の場合、"2" 
     */
    public void testGetSuccOrderMaxQuantityCase0001()
    {
        final String STR_METHOD_NAME = "testGetSuccOrderMaxQuantityCase0001()";
        log.entering(STR_METHOD_NAME);
        try
        {
            TestDBUtility.deleteAll(IfoOrderUnitRow.TYPE);
            IfoOrderUnitParams l_ifoOrderUnitParams = TestDBUtility.getIfoOrderUnitRow();
            l_ifoOrderUnitParams.setOrderId(1111);
            TestDBUtility.insertWithDel(l_ifoOrderUnitParams);
            IfoOrderUnit l_ifoOrderUnit = new IfoContractOpenOrderUnitImpl(1001);
            
            TestDBUtility.deleteAll(BranchPreferencesRow.TYPE);
            BranchPreferencesParams l_branchPreferencesParams = TestDBUtility.getBranchPreferencesRow();
            l_branchPreferencesParams.setBranchId(33381);
            l_branchPreferencesParams.setName("triggerorder.sucorder.maxordercount");
            l_branchPreferencesParams.setNameSerialNo(2);
            l_branchPreferencesParams.setValue("6");
            TestDBUtility.insertWithDel(l_branchPreferencesParams);
            
            assertEquals("6.0", l_impl.getSuccOrderMaxQuantity(l_ifoOrderUnit) + "");

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
     * validateトリガー注文設定To親注文
        親注文の注文単位の型が、株式注文単位の場合　@ 
        親注文が強制決済注文(*3)の場合、 
        「親注文が強制決済注文のためトリガー注文設定不可」の 
        例外をthrowする。 
     */
    public void testValidateTriggerOrderSettingToParentOrderCase0001()
    {
        final String STR_METHOD_NAME = "testValidateTriggerOrderSettingToParentOrderCase0001()";
        log.entering(STR_METHOD_NAME);
        try
        {
            TestDBUtility.deleteAll(EqtypeOrderUnitRow.TYPE);
            EqtypeOrderUnitParams l_eqtypeOrderUnitParams = TestDBUtility.getEqtypeOrderUnitRow();
            l_eqtypeOrderUnitParams.setOrderId(1111);
            l_eqtypeOrderUnitParams.setForcedSettleReasonType("1");
            TestDBUtility.insertWithDel(l_eqtypeOrderUnitParams);
            
            EqTypeOrderUnit l_eqtypeOrderUnit = new EqTypeOrderUnitImpl(null, l_eqtypeOrderUnitParams);
            
            l_impl.validateTriggerOrderSettingToParentOrder(l_eqtypeOrderUnit);
            fail();
        }
        catch(WEB3BaseException l_ex)
        {
            log.debug(STR_METHOD_NAME, l_ex);
            assertEquals(l_ex.getErrorInfo(),WEB3ErrorCatalog.BUSINESS_ERROR_02808);
            log.info(STR_METHOD_NAME + ">>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>test pass !!");
            log.exiting(TEST_END + STR_METHOD_NAME);

        }
        catch (Exception l_ex)
        {
            log.error(ERROR + l_ex.getMessage(), l_ex);
            fail();
        }
    }
    
    /**
     * validateトリガー注文設定To親注文
     * 親注文の注文単位の型 !=株式注文単位の場合　@ 
     */
    public void testValidateTriggerOrderSettingToParentOrderCase0002()
    {
        final String STR_METHOD_NAME = "testValidateTriggerOrderSettingToParentOrderCase0002()";
        log.entering(STR_METHOD_NAME);
        try
        {
            TestDBUtility.deleteAll(IfoOrderUnitRow.TYPE);
            IfoOrderUnitParams l_ifoOrderUnitParams = TestDBUtility.getIfoOrderUnitRow();
            l_ifoOrderUnitParams.setOrderId(1111);
            TestDBUtility.insertWithDel(l_ifoOrderUnitParams);
            IfoOrderUnit l_ifoOrderUnit = new IfoContractOpenOrderUnitImpl(1001);

            l_impl.validateTriggerOrderSettingToParentOrder(l_ifoOrderUnit);

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
     * set発注済To予約注文単位 
     * 指定された予約注文単位オブジェクトを、発注済の状態に更新する。
        引数の銘柄タイプ !="株式"の場合
        this.set発注済To先物OP予約注文単位( １）で取得した先物OP予約注文単位)をコールする。
     *
     */
    public void testSetOrderedToOrderUnitCase0001()
    {
        final String STR_METHOD_NAME = "testSetOrderedToOrderUnitCase0001()";
        log.entering(STR_METHOD_NAME);
        try
        {
            TestDBUtility.deleteAll(RsvIfoOrderUnitRow.TYPE);
            RsvIfoOrderUnitParams l_rsvIfoOrderUnitParams = TestDBUtility.getRsvIfoOrderUnitRow();
            l_rsvIfoOrderUnitParams.setOrderId(1111);
            TestDBUtility.insertWithDel(l_rsvIfoOrderUnitParams);

            ProductTypeEnum l_productType = ProductTypeEnum.IFO;
            long l_lngOrderId = 1111;
            l_impl = new WEB3ToSuccOrderManagerImplForTest();
            l_impl.setOrderedToOrderUnit(l_productType, l_lngOrderId);

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
     * validate連続注文
     * 連続注文が受付可能な時間帯かどうかをチェックする。
     * 　@引数の銘柄タイプ == "株"の場合、"出来終了（最終）" 
     */
    public void testValidateSuccOrderCase0001()
    {
        final String STR_METHOD_NAME = "testValidateSuccOrderCase0001()";
        log.entering(STR_METHOD_NAME);
        try
        {
            //SubAccountParams
            TestDBUtility.deleteAll(SubAccountRow.TYPE);
            SubAccountParams l_subAccountParams = TestDBUtility.getSubAccountRow();
            l_subAccountParams.setAccountId(101001010010L);
            l_subAccountParams.setSubAccountId(10100101001007L);
            TestDBUtility.insertWithDel(l_subAccountParams);

            TestDBUtility.deleteAll(IfoOrderUnitRow.TYPE);
            IfoOrderUnitParams l_ifoOrderUnitParams = TestDBUtility.getIfoOrderUnitRow();
            TestDBUtility.insertWithDel(l_ifoOrderUnitParams);
            IfoOrderUnit l_ifoOrderUnit = new IfoContractOpenOrderUnitImpl(1001);
            
            //BranchParams
            TestDBUtility.deleteAll(BranchRow.TYPE);
            BranchParams l_branchParams = TestDBUtility.getBranchRow();
            l_branchParams.setBranchCode("123");
            l_branchParams.setInstitutionCode("0D");
            TestDBUtility.insertWithDel(l_branchParams);
            
            WEB3GentradeSubAccount l_subAccount = new WEB3GentradeSubAccount(l_subAccountParams);
            
            ProductTypeEnum l_productType = ProductTypeEnum.EQUITY;
            String l_strFutureOptionDiv = "1";
            String l_strRsvOrderTradingType = "1";
            
            l_impl = new WEB3ToSuccOrderManagerImplForTest();
            l_impl.validateSuccOrder(
                l_subAccount, l_productType, l_strFutureOptionDiv, l_strRsvOrderTradingType, l_ifoOrderUnit);
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
     * validate連続注文
     * 連続注文が受付可能な時間帯かどうかをチェックする。
     * 引数の補助口座.get取引店().is夕場実施() == true and 引数の親注文の注文単位.立会区分 == "その他"の場合、"夕場前出来終了" 
     */
    public void testValidateSuccOrderCase0002()
    {
        final String STR_METHOD_NAME = "testValidateSuccOrderCase0002()";
        log.entering(STR_METHOD_NAME);
        try
        {
            //MainAccountParams
            TestDBUtility.deleteAll(MainAccountRow.TYPE);
            MainAccountParams l_mainAccountParams = TestDBUtility.getMainAccountRow();
            l_mainAccountParams.setAccountId(101001010010L);
            TestDBUtility.insertWithDel(l_mainAccountParams);
            
            //SubAccountParams
            TestDBUtility.deleteAll(SubAccountRow.TYPE);
            SubAccountParams l_subAccountParams = TestDBUtility.getSubAccountRow();
            l_subAccountParams.setAccountId(101001010010L);
            l_subAccountParams.setSubAccountId(10100101001007L);
            TestDBUtility.insertWithDel(l_subAccountParams);

            TestDBUtility.deleteAll(IfoOrderUnitRow.TYPE);
            IfoOrderUnitParams l_ifoOrderUnitParams = TestDBUtility.getIfoOrderUnitRow();
            l_ifoOrderUnitParams.setSessionType(null);
            TestDBUtility.insertWithDel(l_ifoOrderUnitParams);
            IfoOrderUnit l_ifoOrderUnit = new IfoContractOpenOrderUnitImpl(1001);
            
            //BranchParams
            TestDBUtility.deleteAll(BranchRow.TYPE);
            BranchParams l_branchParams = TestDBUtility.getBranchRow();
            l_branchParams.setBranchCode("123");
            l_branchParams.setInstitutionCode("0D");
            TestDBUtility.insertWithDel(l_branchParams);
            
            TestDBUtility.deleteAll(BranchPreferencesRow.TYPE);
            BranchPreferencesParams l_branchPreferencesParams = TestDBUtility.getBranchPreferencesRow();
            l_branchPreferencesParams.setBranchId(33381);
            l_branchPreferencesParams.setName("evening.session.div");
            l_branchPreferencesParams.setNameSerialNo(2);
            l_branchPreferencesParams.setValue("1");
            TestDBUtility.insertWithDel(l_branchPreferencesParams);

            WEB3GentradeSubAccount l_subAccount = new WEB3GentradeSubAccount(l_subAccountParams);
            
            ProductTypeEnum l_productType = ProductTypeEnum.IFO;
            String l_strFutureOptionDiv = "1";
            String l_strRsvOrderTradingType = "1";
            
            l_impl = new WEB3ToSuccOrderManagerImplForTest();
            l_impl.validateSuccOrder(
                l_subAccount, l_productType, l_strFutureOptionDiv, l_strRsvOrderTradingType, l_ifoOrderUnit);
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
     * validate連続注文
     * 連続注文が受付可能な時間帯かどうかをチェックする。
     * 以外、"出来終了（最終）" 
     */
    public void testValidateSuccOrderCase0003()
    {
        final String STR_METHOD_NAME = "testValidateSuccOrderCase0003()";
        log.entering(STR_METHOD_NAME);
        try
        {
            //MainAccountParams
            TestDBUtility.deleteAll(MainAccountRow.TYPE);
            MainAccountParams l_mainAccountParams = TestDBUtility.getMainAccountRow();
            l_mainAccountParams.setAccountId(101001010010L);
            TestDBUtility.insertWithDel(l_mainAccountParams);
            
            //SubAccountParams
            TestDBUtility.deleteAll(SubAccountRow.TYPE);
            SubAccountParams l_subAccountParams = TestDBUtility.getSubAccountRow();
            l_subAccountParams.setAccountId(101001010010L);
            l_subAccountParams.setSubAccountId(10100101001007L);
            TestDBUtility.insertWithDel(l_subAccountParams);

            TestDBUtility.deleteAll(IfoOrderUnitRow.TYPE);
            IfoOrderUnitParams l_ifoOrderUnitParams = TestDBUtility.getIfoOrderUnitRow();
            l_ifoOrderUnitParams.setSessionType("1");
            TestDBUtility.insertWithDel(l_ifoOrderUnitParams);
            IfoOrderUnit l_ifoOrderUnit = new IfoContractOpenOrderUnitImpl(1001);
            
            //BranchParams
            TestDBUtility.deleteAll(BranchRow.TYPE);
            BranchParams l_branchParams = TestDBUtility.getBranchRow();
            l_branchParams.setBranchCode("123");
            l_branchParams.setInstitutionCode("0D");
            TestDBUtility.insertWithDel(l_branchParams);
            
            TestDBUtility.deleteAll(BranchPreferencesRow.TYPE);
            BranchPreferencesParams l_branchPreferencesParams = TestDBUtility.getBranchPreferencesRow();
            l_branchPreferencesParams.setBranchId(33381);
            l_branchPreferencesParams.setName("evening.session.div");
            l_branchPreferencesParams.setNameSerialNo(2);
            l_branchPreferencesParams.setValue("1");
            TestDBUtility.insertWithDel(l_branchPreferencesParams);

            WEB3GentradeSubAccount l_subAccount = new WEB3GentradeSubAccount(l_subAccountParams);
            
            ProductTypeEnum l_productType = ProductTypeEnum.IFO;
            String l_strFutureOptionDiv = "1";
            String l_strRsvOrderTradingType = "1";
            
            l_impl = new WEB3ToSuccOrderManagerImplForTest();
            l_impl.validateSuccOrder(
                l_subAccount, l_productType, l_strFutureOptionDiv, l_strRsvOrderTradingType, l_ifoOrderUnit);
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
     * get反対売買取引
        証拠金口座開設済の場合 かつ １）の戻り値に"先物"が含まれている場合 
        以下の分岐により取引を追加する。 
        ["先物新規買建注文" or 　@"先物新規売建注文"の場合] ・"先物返済（前提注文）" 
     */
    public void testGetReversingTradesCase0001()
    {
        final String STR_METHOD_NAME = "testValidateSuccOrderCase0001()";
        log.entering(STR_METHOD_NAME);
        try
        {
            //MainAccountParams
            TestDBUtility.deleteAll(MainAccountRow.TYPE);
            MainAccountParams l_mainAccountParams = TestDBUtility.getMainAccountRow();
            l_mainAccountParams.setAccountId(101001010010L);
            l_mainAccountParams.setIfoAccOpenDivTokyo("2");
            TestDBUtility.insertWithDel(l_mainAccountParams);
            
            
            WEB3GentradeMainAccount l_mainAccount = new WEB3GentradeMainAccount(l_mainAccountParams);
            OrderTypeEnum l_orderType = OrderTypeEnum.IDX_FUTURES_BUY_TO_OPEN;
            
            l_impl = new WEB3ToSuccOrderManagerImplForTest();
            String[] l_strReversingTrades = l_impl.getReversingTrades(l_mainAccount, l_orderType);
            assertEquals("13", l_strReversingTrades[0]);
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
     * get反対売買取引
        OP口座開設済(*3)の場合 かつ １）の戻り値に"オプション"が含まれている場合 
        以下の分岐により取引を追加する。 
        ["OP新規買建注文" or "OP新規売建注文"の場合] ・"OP返済（前提注文）" 
     */
    public void testGetReversingTradesCase0002()
    {
        final String STR_METHOD_NAME = "testValidateSuccOrderCase0002()";
        log.entering(STR_METHOD_NAME);
        try
        {
            //MainAccountParams
            TestDBUtility.deleteAll(MainAccountRow.TYPE);
            MainAccountParams l_mainAccountParams = TestDBUtility.getMainAccountRow();
            l_mainAccountParams.setAccountId(101001010010L);
            l_mainAccountParams.setIfoAccOpenDivTokyo("1");
            TestDBUtility.insertWithDel(l_mainAccountParams);

            WEB3GentradeMainAccount l_mainAccount = new WEB3GentradeMainAccount(l_mainAccountParams);
            OrderTypeEnum l_orderType = OrderTypeEnum.IDX_OPTIONS_BUY_TO_OPEN;
            
            l_impl = new WEB3ToSuccOrderManagerImplForTest();
            String[] l_strReversingTrades = l_impl.getReversingTrades(l_mainAccount, l_orderType);
            assertEquals("17", l_strReversingTrades[0]);
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
     * get連続注文取引一覧
        パラメータ.商品区分一覧に"先物"が 含まれる場合 かつ 先物口座開設済(*2)の場合 かつ 
        １）の戻り値に"先物"が含まれている場合、 
        先物の取引区分をArrayListに追加する。 
        "先物新規建" 
        "先物返済（既存残）" 
     */
    public void testGetSuccOrderTradeListCase0001()
    {
        final String STR_METHOD_NAME = "testGetSuccOrderTradeListCase0001()";
        log.entering(STR_METHOD_NAME);

        try
        {
            //MainAccountParams
            TestDBUtility.deleteAll(MainAccountRow.TYPE);
            MainAccountParams l_mainAccountParams = TestDBUtility.getMainAccountRow();
            l_mainAccountParams.setAccountId(101001010010L);
            l_mainAccountParams.setIfoAccOpenDivTokyo("2");
            TestDBUtility.insertWithDel(l_mainAccountParams);

            WEB3GentradeMainAccount l_mainAccount = new WEB3GentradeMainAccount(l_mainAccountParams);
            OrderTypeEnum l_orderType = OrderTypeEnum.IDX_FUTURES_BUY_TO_OPEN;
            String[] l_strProductDivList = new String[]{"3", "4"};
            l_impl = new WEB3ToSuccOrderManagerImplForTest();
            String[] l_strReversingTrades =
                l_impl.getSuccOrderTradeList(l_mainAccount, l_orderType, l_strProductDivList);
            
            assertEquals("12", l_strReversingTrades[0]);
            assertEquals("14", l_strReversingTrades[1]);
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
     * get連続注文取引一覧
        パラメータ.商品区分一覧に"オプション"が 
        含まれる場合 かつ OP口座開設済(*3)の場合 かつ 
        １）の戻り値に"オプション"が含まれている場合、 
        先物の取引区分をArrayListに追加する。 
        ・"OP新規建" 
        ・"OP返済（既存残）"  
     */
    public void testGetSuccOrderTradeListCase0002()
    {
        final String STR_METHOD_NAME = "testGetSuccOrderTradeListCase0002()";
        log.entering(STR_METHOD_NAME);

        try
        {
            //MainAccountParams
            TestDBUtility.deleteAll(MainAccountRow.TYPE);
            MainAccountParams l_mainAccountParams = TestDBUtility.getMainAccountRow();
            l_mainAccountParams.setAccountId(101001010010L);
            l_mainAccountParams.setIfoAccOpenDivTokyo("1");
            TestDBUtility.insertWithDel(l_mainAccountParams);

            WEB3GentradeMainAccount l_mainAccount = new WEB3GentradeMainAccount(l_mainAccountParams);
            OrderTypeEnum l_orderType = OrderTypeEnum.IDX_FUTURES_BUY_TO_OPEN;
            String[] l_strProductDivList = new String[]{"3", "4"};
            l_impl = new WEB3ToSuccOrderManagerImplForTest();
            String[] l_strReversingTrades =
                l_impl.getSuccOrderTradeList(l_mainAccount, l_orderType, l_strProductDivList);
            
            assertEquals("16", l_strReversingTrades[0]);
            assertEquals("18", l_strReversingTrades[1]);
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
     * create建玉
     * 建区分 = 注文単位.getSide() == "買"の場合、"買建"
     */
    public void testCreateIfoContractCase1()
    {
        final String STR_METHOD_NAME = "testCreateIfoContractCase1()";
        log.entering(STR_METHOD_NAME);
            try
            {
                TestBaseForMock.MOCK_MANAGER.setIsMockUsed(true);
                TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                    "webbroker3.ifo.WEB3IfoBizLogicProvider",
                    "calcCommission",
                    new Class[] { WEB3GentradeCommission.class, SubAccount.class },
                    null);
                
                TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                    "webbroker3.ifo.WEB3IfoBizLogicProvider",
                    "calcSalesTax",
                    new Class[] { double.class, Timestamp.class, SubAccount.class },
                    new Double(100.0));
                
                TestDBUtility.deleteAll(MainAccountRow.TYPE);
                MainAccountParams l_mainAccountParams = TestDBUtility.getMainAccountRow();
                l_mainAccountParams.setAccountId(101001010010L);
                l_mainAccountParams.setSonarTraderCode("01");
                TestDBUtility.insertWithDel(l_mainAccountParams);
                
                TestDBUtility.deleteAll(SubAccountRow.TYPE);
                SubAccountParams l_subAccountParams = TestDBUtility.getSubAccountRow();
                l_subAccountParams.setAccountId(101001010010L);
                l_subAccountParams.setSubAccountId(10100101001007L);
                TestDBUtility.insertWithDel(l_subAccountParams);
                
                TestDBUtility.deleteAll(ProductParams.TYPE);
                ProductParams l_productParams = TestDBUtility.getProductRow();
                l_productParams.setProductId(1006169090018L);
                l_productParams.setProductType(ProductTypeEnum.IFO);
                TestDBUtility.insertWithDel(l_productParams);
                
                TestDBUtility.deleteAll(IfoProductParams.TYPE);
                IfoProductParams l_ifoProductParams = TestDBUtility.getIfoProductRow();
                l_ifoProductParams.setProductId(1006169090018L);
                l_ifoProductParams.setProductType(ProductTypeEnum.IFO);
                TestDBUtility.insertWithDel(l_ifoProductParams);
                
                TestDBUtility.deleteAll(TradedProductParams.TYPE);
                TradedProductParams l_tradedProductParams = TestDBUtility.getTradedProductRow();
                l_tradedProductParams.setTradedProductId(330304148080000L);
                l_tradedProductParams.setProductId(1006169090018L);
                l_tradedProductParams.setMarketId(1002);
                l_tradedProductParams.setProductType(ProductTypeEnum.IFO);
                TestDBUtility.insertWithDel(l_tradedProductParams);
                
                TestDBUtility.deleteAll(IfoTradedProductParams.TYPE);
                IfoTradedProductParams l_ifoTradedProductParams = TestDBUtility.getIfoTradedProductRow();
                l_ifoTradedProductParams.setValidForBizDate(null);
                l_ifoTradedProductParams.setTradedProductId(330304148080000L);
                l_ifoTradedProductParams.setProductId(1006169090018L);
                l_ifoTradedProductParams.setUnitSize(10000L);
                l_ifoTradedProductParams.setMarketId(1002);
                l_ifoTradedProductParams.setLastTradingDate(WEB3DateUtility.getDate("20050101","yyyyMMdd"));
                TestDBUtility.insertWithDel(l_ifoTradedProductParams);
                
                TestDBUtility.deleteAll(IfoTradedProductUpdqParams.TYPE);
                IfoTradedProductUpdqParams l_ifoTradedProductUpdqParams = TestDBUtility.getIfoTradedProductUpdqRow();
                l_ifoTradedProductUpdqParams.setProductId(1006169090018L);
                l_ifoTradedProductUpdqParams.setInstitutionCode("0D");
                l_ifoTradedProductUpdqParams.setMarketId(1002);
                l_ifoTradedProductUpdqParams.setTradedProductId(330304148080000L);
                l_ifoTradedProductUpdqParams.setLastTradingDate(WEB3DateUtility.getDate("20050101","yyyyMMdd"));
                SimpleDateFormat l_format = GtlUtils.getThreadSafeSimpleDateFormat("yyyyMMdd");
                Date l_datBizDate = GtlUtils.getTradingSystem().getSystemTimestamp();
                Date l_datpreBizDate = new WEB3GentradeBizDate(
                    new Timestamp(l_datBizDate.getTime())).roll(1);
                String l_strCreateDate = l_format.format(l_datpreBizDate);
                l_ifoTradedProductUpdqParams.setValidForBizDate(l_strCreateDate);  
                TestDBUtility.insertWithDel(l_ifoTradedProductUpdqParams);
                
                TestDBUtility.deleteAll(MarketRow.TYPE);
                MarketParams l_marketParams = TestDBUtility.getMarketRow();
                l_marketParams.setMarketCode("01");
                l_marketParams.setMarketId(1002);
                TestDBUtility.insertWithDel(l_marketParams);
                
                TestDBUtility.deleteAll(IfoOrderUnitRow.TYPE);
                IfoOrderUnitParams l_ifoOrderUnitParams = TestDBUtility.getIfoOrderUnitRow();
                l_ifoOrderUnitParams.setAccountId(101001010010L);
                l_ifoOrderUnitParams.setSubAccountId(10100101001007L);
                l_ifoOrderUnitParams.setBranchId(33381);
                l_ifoOrderUnitParams.setTraderId(null);
                l_ifoOrderUnitParams.setOrderId(1001);
                l_ifoOrderUnitParams.setOrderType(OrderTypeEnum.IDX_FUTURES_BUY_TO_OPEN);
                l_ifoOrderUnitParams.setOrderCateg(OrderCategEnum.IDX_FUTURES_OPEN);
                l_ifoOrderUnitParams.setLastOrderActionSerialNo(1);
                l_ifoOrderUnitParams.setLastExecutionSerialNo(0);
                l_ifoOrderUnitParams.setProductType(ProductTypeEnum.IFO);
                l_ifoOrderUnitParams.setFutureOptionDiv("1");
                l_ifoOrderUnitParams.setMarketId(1002);
                l_ifoOrderUnitParams.setQuantity(100);
                l_ifoOrderUnitParams.setLimitPrice(200);
                l_ifoOrderUnitParams.setExecutionConditionType(IfoOrderExecutionConditionType.NONE);
                l_ifoOrderUnitParams.setOrderConditionType("0");
                l_ifoOrderUnitParams.setOrderCondOperator(null);
                l_ifoOrderUnitParams.setStopPriceType(null);
                l_ifoOrderUnitParams.setStopOrderPrice(null);
                l_ifoOrderUnitParams.setPrice(200);
                l_ifoOrderUnitParams.setWLimitPrice(null);
                l_ifoOrderUnitParams.setDeliveryDate(WEB3DateUtility.getDate("20040101","yyyyMMdd"));
                l_ifoOrderUnitParams.setOrderStatus(OrderStatusEnum.MODIFY_ACCEPTED);
                l_ifoOrderUnitParams.setOrderOpenStatus(OrderOpenStatusEnum.OPEN);
                l_ifoOrderUnitParams.setExpirationStatus(OrderExpirationStatusEnum.EXPIRED);
                l_ifoOrderUnitParams.setTaxType(TaxTypeEnum.NORMAL);
                l_ifoOrderUnitParams.setBizDate("20040101");
                l_ifoOrderUnitParams.setProductId(1006169090018L);
                l_ifoOrderUnitParams.setCreatedTimestamp(WEB3DateUtility.getDate("20040101","yyyyMMdd"));
                l_ifoOrderUnitParams.setLastUpdatedTimestamp(WEB3DateUtility.getDate("20040101","yyyyMMdd"));
                l_ifoOrderUnitParams.setOrderRequestNumber("000003006");
                l_ifoOrderUnitParams.setConfirmedOrderRev("2");
                l_ifoOrderUnitParams.setOrderRev("1");
                TestDBUtility.insertWithDel(l_ifoOrderUnitParams);
                IfoOrderUnit l_ifoOrderUnit = new IfoContractOpenOrderUnitImpl(1001);
                IfoContractParams l_contract =
                    (IfoContractParams)((WEB3IfoContractImpl)l_impl.createIfoContract(l_ifoOrderUnit)).getDataSourceObject();
                //建玉ID = 0 
                assertEquals("0", l_contract.getContractId() + "");
                //　@口座ID = 注文単位の同項目 
                assertEquals("101001010010", l_contract.getAccountId() + "");
                //　@補助口座ID = 注文単位の同項目 
                assertEquals("10100101001007", l_contract.getSubAccountId() + "");
                //　@市場ID = 注文単位の同項目 
                assertEquals("1002", l_contract.getMarketId() + "");
                //　@1単位当り乗数 = １）で取得した先物OP取引銘柄.getUnitSize() 
                assertEquals("10000.0", l_contract.getUnitSize() + "");
                //　@建玉元数量 = 注文単位.注文数量 
                assertEquals("100.0", l_contract.getOriginalQuantity() + "");
                //　@建玉数量 = 注文単位.注文数量 
                assertEquals("100.0", l_contract.getQuantity() + "");
                //　@元建単価 = 注文単位.注文単価 
                assertEquals("200.0", l_contract.getOriginalContractPrice() + "");
                //　@建単価 = 注文単位.注文単価 
                assertEquals("200.0", l_contract.getContractPrice() + "");
                //　@建区分 = 注文単位.getSide() == "買"の場合、"買建" 以外、"売建" 
                assertEquals("1", l_contract.getContractType().intValue() + "");
                //　@建日 = 注文単位.発注日 
                assertEquals("20040101", WEB3DateUtility.formatDate(l_contract.getOpenDate(), "yyyyMMdd"));
                //　@期日 = １）で取得した先物OP取引銘柄.getLastTradingDate() 
                assertEquals("20050101", WEB3DateUtility.formatDate(l_contract.getCloseDate(), "yyyyMMdd"));
                //　@建委託手数料 = (*1) 
                assertEquals("0.0", l_contract.getSetupFee() + "");
                //　@建委託手数料消費税 = (*1) 
                assertEquals("100.0", l_contract.getSetupFeeTax() + "");
                //　@管理費 = 0 
                assertEquals("0.0", l_contract.getManagementFee() + "");
                //　@管理費消費税 = 0 
                assertEquals("0.0", l_contract.getManagementFeeTax() + "");
                //　@利子 = 0 
                assertEquals("0.0", l_contract.getInterestFee() + "");
                //　@利子消費税 = 0 
                assertEquals("0.0", l_contract.getInterestFeeTax() + "");
                //　@銘柄ID = 注文単位の同項目 
                assertEquals("1006169090018", l_contract.getProductId() + "");
                //　@銘柄タイプ = 注文単位の同項目 
                assertEquals("6", l_contract.getProductType().intValue() + "");
                //　@作成日付 = GtlUtils.getSystemTimestamp() 
                //　@更新日付 = GtlUtils.getSystemTimestamp() 
                //　@受渡日 = 注文単位の同項目 
                assertEquals("20040101", WEB3DateUtility.formatDate(l_contract.getDeliveryDate(), "yyyyMMdd"));
                //　@立会区分 = 注文単位の同項目
                assertNull(l_contract.getSessionType());
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
     * 注文単位.getSide() == "買"の場合以外
     * 建区分 "売建"
     */
    public void testCreateIfoContractCase2()
    {
        final String STR_METHOD_NAME = "testCreateIfoContractCase3()";
        log.entering(STR_METHOD_NAME);
            try
            {
                TestBaseForMock.MOCK_MANAGER.setIsMockUsed(true);
                TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                    "webbroker3.ifo.WEB3IfoBizLogicProvider",
                    "calcCommission",
                    new Class[] { WEB3GentradeCommission.class, SubAccount.class },
                    null);
                
                TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                    "webbroker3.ifo.WEB3IfoBizLogicProvider",
                    "calcSalesTax",
                    new Class[] { double.class, Timestamp.class, SubAccount.class },
                    new Double(100.0));
                
                TestDBUtility.deleteAll(MainAccountRow.TYPE);
                MainAccountParams l_mainAccountParams = TestDBUtility.getMainAccountRow();
                l_mainAccountParams.setAccountId(101001010010L);
                l_mainAccountParams.setSonarTraderCode("01");
                TestDBUtility.insertWithDel(l_mainAccountParams);
                
                TestDBUtility.deleteAll(SubAccountRow.TYPE);
                SubAccountParams l_subAccountParams = TestDBUtility.getSubAccountRow();
                l_subAccountParams.setAccountId(101001010010L);
                l_subAccountParams.setSubAccountId(10100101001007L);
                TestDBUtility.insertWithDel(l_subAccountParams);
                
                TestDBUtility.deleteAll(ProductParams.TYPE);
                ProductParams l_productParams = TestDBUtility.getProductRow();
                l_productParams.setProductId(1006169090018L);
                l_productParams.setProductType(ProductTypeEnum.IFO);
                TestDBUtility.insertWithDel(l_productParams);
                
                TestDBUtility.deleteAll(IfoProductParams.TYPE);
                IfoProductParams l_ifoProductParams = TestDBUtility.getIfoProductRow();
                l_ifoProductParams.setProductId(1006169090018L);
                l_ifoProductParams.setProductType(ProductTypeEnum.IFO);
                TestDBUtility.insertWithDel(l_ifoProductParams);
                
                TestDBUtility.deleteAll(TradedProductParams.TYPE);
                TradedProductParams l_tradedProductParams = TestDBUtility.getTradedProductRow();
                l_tradedProductParams.setTradedProductId(330304148080000L);
                l_tradedProductParams.setProductId(1006169090018L);
                l_tradedProductParams.setMarketId(1002);
                l_tradedProductParams.setProductType(ProductTypeEnum.IFO);
                TestDBUtility.insertWithDel(l_tradedProductParams);
                
                TestDBUtility.deleteAll(IfoTradedProductParams.TYPE);
                IfoTradedProductParams l_ifoTradedProductParams = TestDBUtility.getIfoTradedProductRow();
                l_ifoTradedProductParams.setValidForBizDate(null);
                l_ifoTradedProductParams.setTradedProductId(330304148080000L);
                l_ifoTradedProductParams.setProductId(1006169090018L);
                l_ifoTradedProductParams.setUnitSize(10000L);
                l_ifoTradedProductParams.setMarketId(1002);
                l_ifoTradedProductParams.setLastTradingDate(WEB3DateUtility.getDate("20050101","yyyyMMdd"));
                TestDBUtility.insertWithDel(l_ifoTradedProductParams);
                
                TestDBUtility.deleteAll(IfoTradedProductUpdqParams.TYPE);
                IfoTradedProductUpdqParams l_ifoTradedProductUpdqParams = TestDBUtility.getIfoTradedProductUpdqRow();
                l_ifoTradedProductUpdqParams.setProductId(1006169090018L);
                l_ifoTradedProductUpdqParams.setInstitutionCode("0D");
                l_ifoTradedProductUpdqParams.setMarketId(1002);
                l_ifoTradedProductUpdqParams.setTradedProductId(330304148080000L);
                l_ifoTradedProductUpdqParams.setLastTradingDate(WEB3DateUtility.getDate("20050101","yyyyMMdd"));
                SimpleDateFormat l_format = GtlUtils.getThreadSafeSimpleDateFormat("yyyyMMdd");
                Date l_datBizDate = GtlUtils.getTradingSystem().getSystemTimestamp();
                Date l_datpreBizDate = new WEB3GentradeBizDate(
                    new Timestamp(l_datBizDate.getTime())).roll(1);
                String l_strCreateDate = l_format.format(l_datpreBizDate);
                l_ifoTradedProductUpdqParams.setValidForBizDate(l_strCreateDate);  
                TestDBUtility.insertWithDel(l_ifoTradedProductUpdqParams);
                
                TestDBUtility.deleteAll(MarketRow.TYPE);
                MarketParams l_marketParams = TestDBUtility.getMarketRow();
                l_marketParams.setMarketCode("01");
                l_marketParams.setMarketId(1002);
                TestDBUtility.insertWithDel(l_marketParams);
                
                TestDBUtility.deleteAll(IfoOrderUnitRow.TYPE);
                IfoOrderUnitParams l_ifoOrderUnitParams = TestDBUtility.getIfoOrderUnitRow();
                l_ifoOrderUnitParams.setAccountId(101001010010L);
                l_ifoOrderUnitParams.setSubAccountId(10100101001007L);
                l_ifoOrderUnitParams.setBranchId(33381);
                l_ifoOrderUnitParams.setTraderId(null);
                l_ifoOrderUnitParams.setOrderId(1001);
                l_ifoOrderUnitParams.setOrderType(OrderTypeEnum.IDX_OPTIONS_SELL_TO_CLOSE);
                l_ifoOrderUnitParams.setOrderCateg(OrderCategEnum.IDX_FUTURES_OPEN);
                l_ifoOrderUnitParams.setLastOrderActionSerialNo(1);
                l_ifoOrderUnitParams.setLastExecutionSerialNo(0);
                l_ifoOrderUnitParams.setProductType(ProductTypeEnum.IFO);
                l_ifoOrderUnitParams.setFutureOptionDiv("1");
                l_ifoOrderUnitParams.setMarketId(1002);
                l_ifoOrderUnitParams.setPrice(200);
                l_ifoOrderUnitParams.setQuantity(100);
                l_ifoOrderUnitParams.setLimitPrice(200);
                l_ifoOrderUnitParams.setExecutionConditionType(IfoOrderExecutionConditionType.NONE);
                l_ifoOrderUnitParams.setOrderConditionType("0");
                l_ifoOrderUnitParams.setOrderCondOperator(null);
                l_ifoOrderUnitParams.setStopPriceType(null);
                l_ifoOrderUnitParams.setStopOrderPrice(null);
                l_ifoOrderUnitParams.setWLimitPrice(null);
                l_ifoOrderUnitParams.setDeliveryDate(WEB3DateUtility.getDate("20040101","yyyyMMdd"));
                l_ifoOrderUnitParams.setOrderStatus(OrderStatusEnum.MODIFY_ACCEPTED);
                l_ifoOrderUnitParams.setOrderOpenStatus(OrderOpenStatusEnum.OPEN);
                l_ifoOrderUnitParams.setExpirationStatus(OrderExpirationStatusEnum.EXPIRED);
                l_ifoOrderUnitParams.setTaxType(TaxTypeEnum.NORMAL);
                l_ifoOrderUnitParams.setBizDate("20040101");
                l_ifoOrderUnitParams.setProductId(1006169090018L);
                l_ifoOrderUnitParams.setCreatedTimestamp(WEB3DateUtility.getDate("20040101","yyyyMMdd"));
                l_ifoOrderUnitParams.setLastUpdatedTimestamp(WEB3DateUtility.getDate("20040101","yyyyMMdd"));
                l_ifoOrderUnitParams.setOrderRequestNumber("000003006");
                l_ifoOrderUnitParams.setConfirmedOrderRev("2");
                l_ifoOrderUnitParams.setOrderRev("1");
                TestDBUtility.insertWithDel(l_ifoOrderUnitParams);
                IfoOrderUnit l_ifoOrderUnit = new IfoContractOpenOrderUnitImpl(1001);
                IfoContractParams l_contract =
                    (IfoContractParams)((WEB3IfoContractImpl)l_impl.createIfoContract(l_ifoOrderUnit)).getDataSourceObject();
                //建玉ID = 0 
                assertEquals("0", l_contract.getContractId() + "");
                //　@口座ID = 注文単位の同項目 
                assertEquals("101001010010", l_contract.getAccountId() + "");
                //　@補助口座ID = 注文単位の同項目 
                assertEquals("10100101001007", l_contract.getSubAccountId() + "");
                //　@市場ID = 注文単位の同項目 
                assertEquals("1002", l_contract.getMarketId() + "");
                //　@1単位当り乗数 = １）で取得した先物OP取引銘柄.getUnitSize() 
                assertEquals("10000.0", l_contract.getUnitSize() + "");
                //　@建玉元数量 = 注文単位.注文数量 
                assertEquals("100.0", l_contract.getOriginalQuantity() + "");
                //　@建玉数量 = 注文単位.注文数量 
                assertEquals("100.0", l_contract.getQuantity() + "");
                //　@元建単価 = 注文単位.注文単価 
                assertEquals("200.0", l_contract.getOriginalContractPrice() + "");
                //　@建単価 = 注文単位.注文単価 
                assertEquals("200.0", l_contract.getContractPrice() + "");
                //　@建区分 = 注文単位.getSide() == "買"の場合、"買建" 以外、"売建" 
                assertEquals("2", l_contract.getContractType().intValue() + "");
                //　@建日 = 注文単位.発注日 
                assertEquals("20040101", WEB3DateUtility.formatDate(l_contract.getOpenDate(), "yyyyMMdd"));
                //　@期日 = １）で取得した先物OP取引銘柄.getLastTradingDate() 
                assertEquals("20050101", WEB3DateUtility.formatDate(l_contract.getCloseDate(), "yyyyMMdd"));
                //　@建委託手数料 = (*1) 
                assertEquals("0.0", l_contract.getSetupFee() + "");
                //　@建委託手数料消費税 = (*1) 
                assertEquals("100.0", l_contract.getSetupFeeTax() + "");
                //　@管理費 = 0 
                assertEquals("0.0", l_contract.getManagementFee() + "");
                //　@管理費消費税 = 0 
                assertEquals("0.0", l_contract.getManagementFeeTax() + "");
                //　@利子 = 0 
                assertEquals("0.0", l_contract.getInterestFee() + "");
                //　@利子消費税 = 0 
                assertEquals("0.0", l_contract.getInterestFeeTax() + "");
                //　@銘柄ID = 注文単位の同項目 
                assertEquals("1006169090018", l_contract.getProductId() + "");
                //　@銘柄タイプ = 注文単位の同項目 
                assertEquals("6", l_contract.getProductType().intValue() + "");
                //　@作成日付 = GtlUtils.getSystemTimestamp() 
                //　@更新日付 = GtlUtils.getSystemTimestamp() 
                //　@受渡日 = 注文単位の同項目 
                assertEquals("20040101", WEB3DateUtility.formatDate(l_contract.getDeliveryDate(), "yyyyMMdd"));
                //　@立会区分 = 注文単位の同項目
                assertNull(l_contract.getSessionType());
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
     * 新規建注文（予約注文単位.注文カテゴリ == "先物新規建注文"）の場合
     * retrun null
     */
    public void testCreateIfoContractUnitByOrderCase0001()
    {
        final String STR_METHOD_NAME = "testCreateIfoContractUnitByOrderCase0001()";
        log.entering(STR_METHOD_NAME);
        try
        {           
            
            TestDBUtility.deleteAll(RsvIfoOrderUnitRow.TYPE);
            RsvIfoOrderUnitParams l_RsvIfoOrderUnitParams = TestDBUtility.getRsvIfoOrderUnitRow();
            l_RsvIfoOrderUnitParams.setOrderUnitId(1111);
            l_RsvIfoOrderUnitParams.setOrderCateg(OrderCategEnum.IDX_FUTURES_OPEN);
            TestDBUtility.insertWithDel(l_RsvIfoOrderUnitParams);
            
            WEB3ToSuccIfoOrderUnitImpl l_rsvIfoOrderUnit =
                new WEB3ToSuccIfoOrderUnitImpl(l_RsvIfoOrderUnitParams);

            WEB3FuturesOptionsContractUnit[] l_units = l_impl.createIfoContractUnitByOrder(l_rsvIfoOrderUnit);
            assertNull(l_units);
        }
        catch (Exception l_ex)
        {
            log.error(ERROR + l_ex.getMessage(), l_ex);
            fail();
        }
    }
    
    /**
     * 新規建注文（予約注文単位.注文カテゴリ == ""OP新規建注文"）の場合
     * return null
     */
    public void testCreateIfoContractUnitByOrderCase0002()
    {
        final String STR_METHOD_NAME = "testCreateIfoContractUnitByOrderCase0002()";
        log.entering(STR_METHOD_NAME);
        try
        {           
            
            TestDBUtility.deleteAll(RsvIfoOrderUnitRow.TYPE);
            RsvIfoOrderUnitParams l_RsvIfoOrderUnitParams = TestDBUtility.getRsvIfoOrderUnitRow();
            l_RsvIfoOrderUnitParams.setOrderUnitId(1111);
            l_RsvIfoOrderUnitParams.setOrderCateg(OrderCategEnum.IDX_OPTIONS_OPEN);
            TestDBUtility.insertWithDel(l_RsvIfoOrderUnitParams);
            
            WEB3ToSuccIfoOrderUnitImpl l_rsvIfoOrderUnit =
                new WEB3ToSuccIfoOrderUnitImpl(l_RsvIfoOrderUnitParams);

            WEB3FuturesOptionsContractUnit[] l_units = l_impl.createIfoContractUnitByOrder(l_rsvIfoOrderUnit);
            assertNull(l_units);
        }
        catch (Exception l_ex)
        {
            log.error(ERROR + l_ex.getMessage(), l_ex);
            fail();
        }
    }
    
    /**
     * 反対売買（is反対売買取引()の戻り値 == true）の場合
     * 予約注文単位.決済順序区分 == "ランダム"の場合
     */
    public void testCreateIfoContractUnitByOrderCase0003()
    {
        final String STR_METHOD_NAME = "testCreateIfoContractUnitByOrderCase0003()";
        log.entering(STR_METHOD_NAME);
        try
        {           
            
            TestDBUtility.deleteAll(RsvIfoOrderUnitRow.TYPE);
            RsvIfoOrderUnitParams l_RsvIfoOrderUnitParams = TestDBUtility.getRsvIfoOrderUnitRow();
            l_RsvIfoOrderUnitParams.setOrderUnitId(1111);
            l_RsvIfoOrderUnitParams.setOrderCateg(OrderCategEnum.IDX_FUTURES_CLOSE);
            l_RsvIfoOrderUnitParams.setClosingOrder("0");
            l_RsvIfoOrderUnitParams.setQuantity(25.0);
            l_RsvIfoOrderUnitParams.setAccountId(101001010010L);
            l_RsvIfoOrderUnitParams.setReserveOrderTradingType("13");
            l_RsvIfoOrderUnitParams.setOrderType(OrderTypeEnum.IDX_FUTURES_SELL_TO_OPEN);
            TestDBUtility.insertWithDel(l_RsvIfoOrderUnitParams);
            
            TestDBUtility.deleteAll(IfoOrderUnitRow.TYPE);
            IfoOrderUnitParams l_ifoOrderUnitParams = TestDBUtility.getIfoOrderUnitRow();
            l_ifoOrderUnitParams.setOrderUnitId(1111);
            l_ifoOrderUnitParams.setOrderCateg(OrderCategEnum.IDX_FUTURES_CLOSE);
            l_ifoOrderUnitParams.setClosingOrder("0");
            l_ifoOrderUnitParams.setQuantity(25.0);
            l_ifoOrderUnitParams.setAccountId(101001010010L);
            l_ifoOrderUnitParams.setOrderType(OrderTypeEnum.IDX_FUTURES_SELL_TO_OPEN);
            TestDBUtility.insertWithDel(l_ifoOrderUnitParams);
            
            TestDBUtility.deleteAll(MainAccountRow.TYPE);
            MainAccountParams l_mainAccountParams = TestDBUtility.getMainAccountRow();
            l_mainAccountParams.setAccountId(101001010010L);
            l_mainAccountParams.setSonarTraderCode("01");
            l_mainAccountParams.setIfoAccOpenDivNagoya("3");
            l_mainAccountParams.setInstitutionId(33);
            TestDBUtility.insertWithDel(l_mainAccountParams);
            
            TestDBUtility.deleteAll(InstitutionParams.TYPE);
            InstitutionParams l_institutionParams = TestDBUtility.getInstitutionRow();
            TestDBUtility.insertWithDel(l_institutionParams);
            
            TestDBUtility.deleteAll(EnableOrderConditionRow.TYPE);
            EnableOrderConditionParams l_enableOrderConditionParams = TestDBUtility.getEnableOrderConditionParamsRow();
            l_enableOrderConditionParams.setInstitutionCode("0D");
            l_enableOrderConditionParams.setProductType(ProductTypeEnum.EQUITY);
            l_enableOrderConditionParams.setMarketCode("0");
            l_enableOrderConditionParams.setFutureOptionDiv("0");
            l_enableOrderConditionParams.setMarginTradingDiv("0");
            TestDBUtility.insertWithDel(l_enableOrderConditionParams);
            EnableOrderConditionParams l_enableOrderConditionParams1 = TestDBUtility.getEnableOrderConditionParamsRow();
            l_enableOrderConditionParams1.setInstitutionCode("0D");
            l_enableOrderConditionParams1.setProductType(ProductTypeEnum.IFO);
            l_enableOrderConditionParams1.setMarketCode("0");
            l_enableOrderConditionParams1.setFutureOptionDiv("1");
            l_enableOrderConditionParams1.setMarginTradingDiv("0");
            TestDBUtility.insertWithDel(l_enableOrderConditionParams1);
            EnableOrderConditionParams l_enableOrderConditionParams2 = TestDBUtility.getEnableOrderConditionParamsRow();
            l_enableOrderConditionParams2.setInstitutionCode("0D");
            l_enableOrderConditionParams2.setProductType(ProductTypeEnum.IFO);
            l_enableOrderConditionParams2.setMarketCode("0");
            l_enableOrderConditionParams2.setFutureOptionDiv("2");
            l_enableOrderConditionParams2.setMarginTradingDiv("0");
            TestDBUtility.insertWithDel(l_enableOrderConditionParams2);
            WEB3ToSuccIfoOrderUnitImpl l_rsvIfoOrderUnit =
                new WEB3ToSuccIfoOrderUnitImpl(l_RsvIfoOrderUnitParams);

            WEB3FuturesOptionsContractUnit[] l_units = l_impl.createIfoContractUnitByOrder(l_rsvIfoOrderUnit);
            assertEquals("25", l_units[0].contractOrderQuantity);
        }
        catch (Exception l_ex)
        {
            log.error(ERROR + l_ex.getMessage(), l_ex);
            fail();
        }
    }
    
    /**
     * 反対売買（is反対売買取引()の戻り値 == true）の場合
     * 予約注文単位.決済順序区分 != "ランダム"の場合
     */
    public void testCreateIfoContractUnitByOrderCase0004()
    {
        final String STR_METHOD_NAME = "testCreateIfoContractUnitByOrderCase0004()";
        log.entering(STR_METHOD_NAME);
        try
        {           
            
            TestDBUtility.deleteAll(RsvIfoOrderUnitRow.TYPE);
            RsvIfoOrderUnitParams l_RsvIfoOrderUnitParams = TestDBUtility.getRsvIfoOrderUnitRow();
            l_RsvIfoOrderUnitParams.setOrderUnitId(1111);
            l_RsvIfoOrderUnitParams.setOrderCateg(OrderCategEnum.IDX_FUTURES_CLOSE);
            l_RsvIfoOrderUnitParams.setClosingOrder("1");
            l_RsvIfoOrderUnitParams.setQuantity(25.0);
            l_RsvIfoOrderUnitParams.setAccountId(101001010010L);
            l_RsvIfoOrderUnitParams.setReserveOrderTradingType("13");
            l_RsvIfoOrderUnitParams.setOrderType(OrderTypeEnum.IDX_FUTURES_SELL_TO_OPEN);
            TestDBUtility.insertWithDel(l_RsvIfoOrderUnitParams);
            
            TestDBUtility.deleteAll(IfoOrderUnitRow.TYPE);
            IfoOrderUnitParams l_ifoOrderUnitParams = TestDBUtility.getIfoOrderUnitRow();
            l_ifoOrderUnitParams.setOrderUnitId(1111);
            l_ifoOrderUnitParams.setOrderCateg(OrderCategEnum.IDX_FUTURES_CLOSE);
            l_ifoOrderUnitParams.setClosingOrder("0");
            l_ifoOrderUnitParams.setQuantity(25.0);
            l_ifoOrderUnitParams.setAccountId(101001010010L);
            l_ifoOrderUnitParams.setOrderType(OrderTypeEnum.IDX_FUTURES_SELL_TO_OPEN);
            TestDBUtility.insertWithDel(l_ifoOrderUnitParams);
            
            TestDBUtility.deleteAll(MainAccountRow.TYPE);
            MainAccountParams l_mainAccountParams = TestDBUtility.getMainAccountRow();
            l_mainAccountParams.setAccountId(101001010010L);
            l_mainAccountParams.setSonarTraderCode("01");
            l_mainAccountParams.setIfoAccOpenDivNagoya("3");
            l_mainAccountParams.setInstitutionId(33);
            TestDBUtility.insertWithDel(l_mainAccountParams);
            
            TestDBUtility.deleteAll(InstitutionParams.TYPE);
            InstitutionParams l_institutionParams = TestDBUtility.getInstitutionRow();
            TestDBUtility.insertWithDel(l_institutionParams);
            
            TestDBUtility.deleteAll(EnableOrderConditionRow.TYPE);
            EnableOrderConditionParams l_enableOrderConditionParams = TestDBUtility.getEnableOrderConditionParamsRow();
            l_enableOrderConditionParams.setInstitutionCode("0D");
            l_enableOrderConditionParams.setProductType(ProductTypeEnum.EQUITY);
            l_enableOrderConditionParams.setMarketCode("0");
            l_enableOrderConditionParams.setFutureOptionDiv("0");
            l_enableOrderConditionParams.setMarginTradingDiv("0");
            TestDBUtility.insertWithDel(l_enableOrderConditionParams);
            EnableOrderConditionParams l_enableOrderConditionParams1 = TestDBUtility.getEnableOrderConditionParamsRow();
            l_enableOrderConditionParams1.setInstitutionCode("0D");
            l_enableOrderConditionParams1.setProductType(ProductTypeEnum.IFO);
            l_enableOrderConditionParams1.setMarketCode("0");
            l_enableOrderConditionParams1.setFutureOptionDiv("1");
            l_enableOrderConditionParams1.setMarginTradingDiv("0");
            TestDBUtility.insertWithDel(l_enableOrderConditionParams1);
            EnableOrderConditionParams l_enableOrderConditionParams2 = TestDBUtility.getEnableOrderConditionParamsRow();
            l_enableOrderConditionParams2.setInstitutionCode("0D");
            l_enableOrderConditionParams2.setProductType(ProductTypeEnum.IFO);
            l_enableOrderConditionParams2.setMarketCode("0");
            l_enableOrderConditionParams2.setFutureOptionDiv("2");
            l_enableOrderConditionParams2.setMarginTradingDiv("0");
            TestDBUtility.insertWithDel(l_enableOrderConditionParams2);
            WEB3ToSuccIfoOrderUnitImpl l_rsvIfoOrderUnit =
                new WEB3ToSuccIfoOrderUnitImpl(l_RsvIfoOrderUnitParams);

            WEB3FuturesOptionsContractUnit[] l_units = l_impl.createIfoContractUnitByOrder(l_rsvIfoOrderUnit);
            assertNull(l_units[0].contractOrderQuantity);
            assertEquals("25", l_units[0].contractQuantity);
        }
        catch (Exception l_ex)
        {
            log.error(ERROR + l_ex.getMessage(), l_ex);
            fail();
        }
    }
    
    /**
     * 反対売買（is反対売買取引()の戻り値 == false）の場合反対売買でない場合
     * 建玉明細[] == null
     */
    public void testCreateIfoContractUnitByOrderCase0005()
    {
        final String STR_METHOD_NAME = "testCreateIfoContractUnitByOrderCase0005()";
        log.entering(STR_METHOD_NAME);
        try
        {           
            TestDBUtility.deleteAll(RsvIfoClosingContractSpecRow.TYPE);
            TestDBUtility.deleteAll(RsvIfoOrderUnitRow.TYPE);
            RsvIfoOrderUnitParams l_RsvIfoOrderUnitParams = TestDBUtility.getRsvIfoOrderUnitRow();
            l_RsvIfoOrderUnitParams.setOrderUnitId(1111);
            l_RsvIfoOrderUnitParams.setOrderCateg(OrderCategEnum.IDX_FUTURES_CLOSE);
            l_RsvIfoOrderUnitParams.setClosingOrder("0");
            l_RsvIfoOrderUnitParams.setQuantity(25.0);
            l_RsvIfoOrderUnitParams.setAccountId(101001010010L);
            TestDBUtility.insertWithDel(l_RsvIfoOrderUnitParams);
            
            TestDBUtility.deleteAll(MainAccountRow.TYPE);
            MainAccountParams l_mainAccountParams = TestDBUtility.getMainAccountRow();
            l_mainAccountParams.setAccountId(101001010010L);
            l_mainAccountParams.setSonarTraderCode("01");
            TestDBUtility.insertWithDel(l_mainAccountParams);
            
            TestDBUtility.deleteAll(EnableOrderConditionRow.TYPE);
            EnableOrderConditionParams l_enableOrderConditionParams = TestDBUtility.getEnableOrderConditionParamsRow();
            l_enableOrderConditionParams.setInstitutionCode("0D");
            l_enableOrderConditionParams.setProductType(ProductTypeEnum.EQUITY);
            l_enableOrderConditionParams.setMarketCode("0");
            l_enableOrderConditionParams.setFutureOptionDiv("0");
            l_enableOrderConditionParams.setMarginTradingDiv("0");
            TestDBUtility.insertWithDel(l_enableOrderConditionParams);
            EnableOrderConditionParams l_enableOrderConditionParams1 = TestDBUtility.getEnableOrderConditionParamsRow();
            l_enableOrderConditionParams1.setInstitutionCode("0D");
            l_enableOrderConditionParams1.setProductType(ProductTypeEnum.IFO);
            l_enableOrderConditionParams1.setMarketCode("0");
            l_enableOrderConditionParams1.setFutureOptionDiv("1");
            l_enableOrderConditionParams1.setMarginTradingDiv("0");
            TestDBUtility.insertWithDel(l_enableOrderConditionParams1);
            EnableOrderConditionParams l_enableOrderConditionParams2 = TestDBUtility.getEnableOrderConditionParamsRow();
            l_enableOrderConditionParams2.setInstitutionCode("0D");
            l_enableOrderConditionParams2.setProductType(ProductTypeEnum.IFO);
            l_enableOrderConditionParams2.setMarketCode("0");
            l_enableOrderConditionParams2.setFutureOptionDiv("2");
            l_enableOrderConditionParams2.setMarginTradingDiv("0");
            TestDBUtility.insertWithDel(l_enableOrderConditionParams2);
            WEB3ToSuccIfoOrderUnitImpl l_rsvIfoOrderUnit =
                new WEB3ToSuccIfoOrderUnitImpl(l_RsvIfoOrderUnitParams);

            WEB3FuturesOptionsContractUnit[] l_units = l_impl.createIfoContractUnitByOrder(l_rsvIfoOrderUnit);
            assertNull(l_units);
        }
        catch (Exception l_ex)
        {
            log.error(ERROR + l_ex.getMessage(), l_ex);
            fail();
        }
    }
    
    /**
     * 反対売買（is反対売買取引()の戻り値 == false）の場合反対売買でない場合
     * 先物OP建玉.建玉数量 < 処理対象の要素.返済注文数量の場合、nullを返却して終了する。
     */
    public void testCreateIfoContractUnitByOrderCase0006()
    {
        final String STR_METHOD_NAME = "testCreateIfoContractUnitByOrderCase0006()";
        log.entering(STR_METHOD_NAME);
        try
        {           
            TestDBUtility.deleteAll(RsvIfoClosingContractSpecRow.TYPE);
            RsvIfoClosingContractSpecParams l_contractSpecParams = TestDBUtility.getRsvIfoClosingContractSpecRow();
            l_contractSpecParams.setOrderId(1001);
            l_contractSpecParams.setContractId(1001);
            l_contractSpecParams.setQuantity(150);
            TestDBUtility.insertWithDel(l_contractSpecParams);
            
            TestDBUtility.deleteAll(IfoContractRow.TYPE);
            IfoContractParams l_ifoContractParams = TestDBUtility.getIfoContractRow();
            l_ifoContractParams.setAccountId(101001010010L);
            l_ifoContractParams.setSubAccountId(10100101001007L);
            l_ifoContractParams.setMarketId(33);
            l_ifoContractParams.setProductId(1006169090018L);
            l_ifoContractParams.setQuantity(100);
            TestDBUtility.insertWithDel(l_ifoContractParams);
            
            TestDBUtility.deleteAll(RsvIfoOrderUnitRow.TYPE);
            RsvIfoOrderUnitParams l_RsvIfoOrderUnitParams = TestDBUtility.getRsvIfoOrderUnitRow();
            l_RsvIfoOrderUnitParams.setOrderUnitId(1111);
            l_RsvIfoOrderUnitParams.setOrderCateg(OrderCategEnum.IDX_FUTURES_CLOSE);
            l_RsvIfoOrderUnitParams.setClosingOrder("0");
            l_RsvIfoOrderUnitParams.setQuantity(25.0);
            l_RsvIfoOrderUnitParams.setAccountId(101001010010L);
            TestDBUtility.insertWithDel(l_RsvIfoOrderUnitParams);
            
            TestDBUtility.deleteAll(MainAccountRow.TYPE);
            MainAccountParams l_mainAccountParams = TestDBUtility.getMainAccountRow();
            l_mainAccountParams.setAccountId(101001010010L);
            l_mainAccountParams.setSonarTraderCode("01");
            TestDBUtility.insertWithDel(l_mainAccountParams);
            
            TestDBUtility.deleteAll(EnableOrderConditionRow.TYPE);
            EnableOrderConditionParams l_enableOrderConditionParams = TestDBUtility.getEnableOrderConditionParamsRow();
            l_enableOrderConditionParams.setInstitutionCode("0D");
            l_enableOrderConditionParams.setProductType(ProductTypeEnum.EQUITY);
            l_enableOrderConditionParams.setMarketCode("0");
            l_enableOrderConditionParams.setFutureOptionDiv("0");
            l_enableOrderConditionParams.setMarginTradingDiv("0");
            TestDBUtility.insertWithDel(l_enableOrderConditionParams);
            EnableOrderConditionParams l_enableOrderConditionParams1 = TestDBUtility.getEnableOrderConditionParamsRow();
            l_enableOrderConditionParams1.setInstitutionCode("0D");
            l_enableOrderConditionParams1.setProductType(ProductTypeEnum.IFO);
            l_enableOrderConditionParams1.setMarketCode("0");
            l_enableOrderConditionParams1.setFutureOptionDiv("1");
            l_enableOrderConditionParams1.setMarginTradingDiv("0");
            TestDBUtility.insertWithDel(l_enableOrderConditionParams1);
            EnableOrderConditionParams l_enableOrderConditionParams2 = TestDBUtility.getEnableOrderConditionParamsRow();
            l_enableOrderConditionParams2.setInstitutionCode("0D");
            l_enableOrderConditionParams2.setProductType(ProductTypeEnum.IFO);
            l_enableOrderConditionParams2.setMarketCode("0");
            l_enableOrderConditionParams2.setFutureOptionDiv("2");
            l_enableOrderConditionParams2.setMarginTradingDiv("0");
            TestDBUtility.insertWithDel(l_enableOrderConditionParams2);
            WEB3ToSuccIfoOrderUnitImpl l_rsvIfoOrderUnit =
                new WEB3ToSuccIfoOrderUnitImpl(l_RsvIfoOrderUnitParams);

            WEB3FuturesOptionsContractUnit[] l_units = l_impl.createIfoContractUnitByOrder(l_rsvIfoOrderUnit);
            assertNull(l_units);
        }
        catch (Exception l_ex)
        {
            log.error(ERROR + l_ex.getMessage(), l_ex);
            fail();
        }
    }
    
    /**
     * 反対売買（is反対売買取引()の戻り値 == false）の場合反対売買でない場合
     * 先物OP建玉.建玉数量 > 処理対象の要素.返済注文数量の場合
     * プロパティセット
     * 返回建玉明細[]長度為1
     */
    public void testCreateIfoContractUnitByOrderCase0007()
    {
        final String STR_METHOD_NAME = "testCreateIfoContractUnitByOrderCase0007()";
        log.entering(STR_METHOD_NAME);
        try
        {           
            TestDBUtility.deleteAll(RsvIfoClosingContractSpecRow.TYPE);
            RsvIfoClosingContractSpecParams l_contractSpecParams = TestDBUtility.getRsvIfoClosingContractSpecRow();
            l_contractSpecParams.setOrderId(1001);
            l_contractSpecParams.setContractId(1001);
            l_contractSpecParams.setQuantity(150);
            l_contractSpecParams.setClosingSerialNo(5);
            TestDBUtility.insertWithDel(l_contractSpecParams);
            
            TestDBUtility.deleteAll(IfoContractRow.TYPE);
            IfoContractParams l_ifoContractParams = TestDBUtility.getIfoContractRow();
            l_ifoContractParams.setQuantity(200);
            l_ifoContractParams.setContractId(1001);
            l_ifoContractParams.setAccountId(101001010000L);
            l_ifoContractParams.setSubAccountId(10100101000007L);
            l_ifoContractParams.setMarketId(1002);
            l_ifoContractParams.setUnitSize(1000.0);
            l_ifoContractParams.setOriginalQuantity(1.0);
            l_ifoContractParams.setOriginalContractPrice(3720.0);
            l_ifoContractParams.setContractType(ContractTypeEnum.LONG);
            l_ifoContractParams.setContractPrice(3720.0);
            l_ifoContractParams.setOpenDate(WEB3DateUtility.getDate("20040702","yyyyMMdd"));
            l_ifoContractParams.setCloseDate(WEB3DateUtility.getDate("20040812","yyyyMMdd"));
            l_ifoContractParams.setSetupFee(2000.0);
            l_ifoContractParams.setSetupFeeTax(100.0);
            l_ifoContractParams.setManagementFee(.0);
            l_ifoContractParams.setManagementFeeTax(.0);
            l_ifoContractParams.setInterestFee(.0);
            l_ifoContractParams.setInterestFeeTax(.0);
            l_ifoContractParams.setProductId(1006169090018L);
            l_ifoContractParams.setProductType(ProductTypeEnum.IFO);
            l_ifoContractParams.setCreatedTimestamp(WEB3DateUtility.getDate("20040707","yyyyMMdd"));
            l_ifoContractParams.setLastUpdatedTimestamp(WEB3DateUtility.getDate("20040707","yyyyMMdd"));
            l_ifoContractParams.setDeliveryDate(Calendar.getInstance().getTime());
            l_ifoContractParams.setSessionType(null);
            l_ifoContractParams.setDeliveryDate(WEB3DateUtility.getDate("20040707","yyyyMMdd"));
            TestDBUtility.insertWithDel(l_ifoContractParams);
            
            TestDBUtility.deleteAll(RsvIfoOrderUnitRow.TYPE);
            RsvIfoOrderUnitParams l_RsvIfoOrderUnitParams = TestDBUtility.getRsvIfoOrderUnitRow();
            l_RsvIfoOrderUnitParams.setOrderUnitId(1111);
            l_RsvIfoOrderUnitParams.setOrderCateg(OrderCategEnum.IDX_FUTURES_CLOSE);
            l_RsvIfoOrderUnitParams.setClosingOrder("0");
            l_RsvIfoOrderUnitParams.setQuantity(25.0);
            l_RsvIfoOrderUnitParams.setProductId(1006169090018L);
            l_RsvIfoOrderUnitParams.setAccountId(101001010010L);
            TestDBUtility.insertWithDel(l_RsvIfoOrderUnitParams);
            
            TestDBUtility.deleteAll(MainAccountRow.TYPE);
            MainAccountParams l_mainAccountParams = TestDBUtility.getMainAccountRow();
            l_mainAccountParams.setAccountId(101001010010L);
            l_mainAccountParams.setSonarTraderCode("01");
            TestDBUtility.insertWithDel(l_mainAccountParams);
            
            TestDBUtility.deleteAll(EnableOrderConditionRow.TYPE);
            EnableOrderConditionParams l_enableOrderConditionParams = TestDBUtility.getEnableOrderConditionParamsRow();
            l_enableOrderConditionParams.setInstitutionCode("0D");
            l_enableOrderConditionParams.setProductType(ProductTypeEnum.EQUITY);
            l_enableOrderConditionParams.setMarketCode("0");
            l_enableOrderConditionParams.setFutureOptionDiv("0");
            l_enableOrderConditionParams.setMarginTradingDiv("0");
            TestDBUtility.insertWithDel(l_enableOrderConditionParams);
            EnableOrderConditionParams l_enableOrderConditionParams1 = TestDBUtility.getEnableOrderConditionParamsRow();
            l_enableOrderConditionParams1.setInstitutionCode("0D");
            l_enableOrderConditionParams1.setProductType(ProductTypeEnum.IFO);
            l_enableOrderConditionParams1.setMarketCode("0");
            l_enableOrderConditionParams1.setFutureOptionDiv("1");
            l_enableOrderConditionParams1.setMarginTradingDiv("0");
            TestDBUtility.insertWithDel(l_enableOrderConditionParams1);
            EnableOrderConditionParams l_enableOrderConditionParams2 = TestDBUtility.getEnableOrderConditionParamsRow();
            l_enableOrderConditionParams2.setInstitutionCode("0D");
            l_enableOrderConditionParams2.setProductType(ProductTypeEnum.IFO);
            l_enableOrderConditionParams2.setMarketCode("0");
            l_enableOrderConditionParams2.setFutureOptionDiv("2");
            l_enableOrderConditionParams2.setMarginTradingDiv("0");
            TestDBUtility.insertWithDel(l_enableOrderConditionParams2);
            
            TestDBUtility.deleteAll(MarketRow.TYPE);
            MarketParams l_marketParams = TestDBUtility.getMarketRow();
            l_marketParams.setMarketCode("0");
            l_marketParams.setMarketId(1002);
            TestDBUtility.insertWithDel(l_marketParams);
            
            TestDBUtility.deleteAll(ProductParams.TYPE);
            ProductParams l_productParams = TestDBUtility.getProductRow();
            l_productParams.setProductId(1006169090018L);
            l_productParams.setProductType(ProductTypeEnum.IFO);
            TestDBUtility.insertWithDel(l_productParams);
            
            TestDBUtility.deleteAll(IfoProductParams.TYPE);
            IfoProductParams l_ifoProductParams = TestDBUtility.getIfoProductRow();
            l_ifoProductParams.setProductId(1006169090018L);
            l_ifoProductParams.setProductType(ProductTypeEnum.IFO);
            TestDBUtility.insertWithDel(l_ifoProductParams);
            
            TestDBUtility.deleteAll(TradedProductParams.TYPE);
            TradedProductParams l_tradedProductParams = TestDBUtility.getTradedProductRow();
            l_tradedProductParams.setTradedProductId(330304148080000L);
            l_tradedProductParams.setProductId(1006169090018L);
            l_tradedProductParams.setMarketId(1002);
            l_tradedProductParams.setProductType(ProductTypeEnum.IFO);
            TestDBUtility.insertWithDel(l_tradedProductParams);
            
            TestDBUtility.deleteAll(IfoTradedProductParams.TYPE);
            IfoTradedProductParams l_ifoTradedProductParams = TestDBUtility.getIfoTradedProductRow();
            l_ifoTradedProductParams.setValidForBizDate(null);
            l_ifoTradedProductParams.setTradedProductId(330304148080000L);
            l_ifoTradedProductParams.setProductId(1006169090018L);
            l_ifoTradedProductParams.setUnitSize(10000L);
            l_ifoTradedProductParams.setMarketId(1002);
            l_ifoTradedProductParams.setLastTradingDate(WEB3DateUtility.getDate("20050101","yyyyMMdd"));
            TestDBUtility.insertWithDel(l_ifoTradedProductParams);
            
            TestDBUtility.deleteAll(IfoTradedProductUpdqParams.TYPE);
            IfoTradedProductUpdqParams l_ifoTradedProductUpdqParams = TestDBUtility.getIfoTradedProductUpdqRow();
            l_ifoTradedProductUpdqParams.setProductId(1006169090018L);
            l_ifoTradedProductUpdqParams.setInstitutionCode("0D");
            l_ifoTradedProductUpdqParams.setMarketId(1002);
            l_ifoTradedProductUpdqParams.setTradedProductId(330304148080000L);
            l_ifoTradedProductUpdqParams.setLastTradingDate(WEB3DateUtility.getDate("20050101","yyyyMMdd"));
            SimpleDateFormat l_format = GtlUtils.getThreadSafeSimpleDateFormat("yyyyMMdd");
            Date l_datBizDate = GtlUtils.getTradingSystem().getSystemTimestamp();
            Date l_datpreBizDate = new WEB3GentradeBizDate(
                new Timestamp(l_datBizDate.getTime())).roll(1);
            String l_strCreateDate = l_format.format(l_datpreBizDate);
            l_ifoTradedProductUpdqParams.setValidForBizDate(l_strCreateDate);  
            TestDBUtility.insertWithDel(l_ifoTradedProductUpdqParams);
            TestDBUtility.commit();
            WEB3ToSuccIfoOrderUnitImpl l_rsvIfoOrderUnit =
                new WEB3ToSuccIfoOrderUnitImpl(l_RsvIfoOrderUnitParams);

            WEB3FuturesOptionsContractUnit[] l_units = l_impl.createIfoContractUnitByOrder(l_rsvIfoOrderUnit);
            assertEquals(l_units.length, 1);
            //ID = 建玉.建玉ID
            assertEquals("1001", l_units[0].id);
            //建日 = 建玉.建日
            assertEquals("20040702", WEB3DateUtility.formatDate(l_units[0].openDate, "yyyyMMdd"));
            //建玉数 = 建玉.建玉数量
            assertEquals("200", l_units[0].contractQuantity);
            //建単価 = 建玉.建単価
            assertEquals("3720", l_units[0].contractPrice);
            //建約定金額 = get建約定代金の戻り値
            assertEquals("5580000000", l_units[0].contractExecPrice);
            //建手数料 = 建玉.get建手数料()の戻り値 + 建玉.get建手数料消費税()の戻り値
            assertEquals("1575", l_units[0].contractCommission);
            //損益 = 建玉.get評価損益()の戻り値
            assertEquals("-5568000000", l_units[0].income);
            //損益（諸経費込） = 建玉.get評価損益() - （建玉.get建手数料() + 建玉.get建手数料消費税())
            assertEquals("-5568001575", l_units[0].incomeCost);
            //返済数量 = 処理対象の要素.返済注文数量
            assertEquals("150", l_units[0].contractOrderQuantity);
            //返済約定数量 = null
            assertNull(l_units[0].contractExecQuantity);
            //決済順位 = 処理対象の要素.返済連番
            assertEquals("5", l_units[0].settlePriority);
            //立会区分 = 建玉.立会
            assertNull(l_units[0].sessionType);
        }
        catch (Exception l_ex)
        {
            log.error(ERROR + l_ex.getMessage(), l_ex);
            fail();
        }
    }
    
    /**
     * 反対売買（is反対売買取引()の戻り値 == false）の場合反対売買でない場合
     * 先物OP建玉.建玉数量 < 処理対象の要素.返済注文数量の場合、nullを返却して終了する。
     * 先物OP予約建玉返済指定情報一覧長度為3
     */
    public void testCreateIfoContractUnitByOrderCase0008()
    {
        final String STR_METHOD_NAME = "testCreateIfoContractUnitByOrderCase0008()";
        log.entering(STR_METHOD_NAME);
        try
        {           
            TestDBUtility.deleteAll(RsvIfoClosingContractSpecRow.TYPE);
            RsvIfoClosingContractSpecParams l_contractSpecParams = TestDBUtility.getRsvIfoClosingContractSpecRow();
            l_contractSpecParams.setOrderId(1001);
            l_contractSpecParams.setContractId(1001);
            l_contractSpecParams.setQuantity(150);
            l_contractSpecParams.setClosingSerialNo(1);
            TestDBUtility.insertWithDel(l_contractSpecParams);
            RsvIfoClosingContractSpecParams l_contractSpecParams1 = TestDBUtility.getRsvIfoClosingContractSpecRow();
            l_contractSpecParams1.setOrderId(1001);
            l_contractSpecParams1.setContractId(1002);
            l_contractSpecParams1.setQuantity(150);
            l_contractSpecParams1.setClosingSerialNo(2);
            TestDBUtility.insertWithDel(l_contractSpecParams1);
            RsvIfoClosingContractSpecParams l_contractSpecParams2 = TestDBUtility.getRsvIfoClosingContractSpecRow();
            l_contractSpecParams2.setOrderId(1001);
            l_contractSpecParams2.setContractId(1003);
            l_contractSpecParams2.setQuantity(150);
            l_contractSpecParams2.setClosingSerialNo(3);
            TestDBUtility.insertWithDel(l_contractSpecParams2);
            
            TestDBUtility.deleteAll(IfoContractRow.TYPE);
            IfoContractParams l_ifoContractParams = TestDBUtility.getIfoContractRow();
            l_ifoContractParams.setAccountId(101001010010L);
            l_ifoContractParams.setSubAccountId(10100101001007L);
            l_ifoContractParams.setMarketId(1002);
            l_ifoContractParams.setProductId(1006169090018L);
            l_ifoContractParams.setQuantity(200);
            l_ifoContractParams.setContractId(1001);
            TestDBUtility.insertWithDel(l_ifoContractParams);
            IfoContractParams l_ifoContractParams1 = TestDBUtility.getIfoContractRow();
            l_ifoContractParams1.setAccountId(101001010011L);
            l_ifoContractParams1.setSubAccountId(10100101001007L);
            l_ifoContractParams1.setMarketId(1002);
            l_ifoContractParams1.setProductId(1006169090018L);
            l_ifoContractParams1.setQuantity(200);
            l_ifoContractParams1.setContractId(1002);
            TestDBUtility.insertWithDel(l_ifoContractParams1);
            IfoContractParams l_ifoContractParams2 = TestDBUtility.getIfoContractRow();
            l_ifoContractParams2.setAccountId(101001010012L);
            l_ifoContractParams2.setSubAccountId(10100101001007L);
            l_ifoContractParams2.setMarketId(1002);
            l_ifoContractParams2.setProductId(1006169090018L);
            l_ifoContractParams2.setQuantity(200);
            l_ifoContractParams2.setContractId(1003);
            TestDBUtility.insertWithDel(l_ifoContractParams2);
            
            TestDBUtility.deleteAll(RsvIfoOrderUnitRow.TYPE);
            RsvIfoOrderUnitParams l_RsvIfoOrderUnitParams = TestDBUtility.getRsvIfoOrderUnitRow();
            l_RsvIfoOrderUnitParams.setOrderUnitId(1111);
            l_RsvIfoOrderUnitParams.setOrderCateg(OrderCategEnum.IDX_FUTURES_CLOSE);
            l_RsvIfoOrderUnitParams.setClosingOrder("0");
            l_RsvIfoOrderUnitParams.setQuantity(25.0);
            l_RsvIfoOrderUnitParams.setAccountId(101001010010L);
            TestDBUtility.insertWithDel(l_RsvIfoOrderUnitParams);
            
            TestDBUtility.deleteAll(MarketRow.TYPE);
            MarketParams l_marketParams = TestDBUtility.getMarketRow();
            l_marketParams.setMarketCode("0");
            l_marketParams.setMarketId(1002);
            TestDBUtility.insertWithDel(l_marketParams);
            
            TestDBUtility.deleteAll(MainAccountRow.TYPE);
            MainAccountParams l_mainAccountParams = TestDBUtility.getMainAccountRow();
            l_mainAccountParams.setAccountId(101001010010L);
            l_mainAccountParams.setSonarTraderCode("01");
            TestDBUtility.insertWithDel(l_mainAccountParams);
            
            TestDBUtility.deleteAll(EnableOrderConditionRow.TYPE);
            EnableOrderConditionParams l_enableOrderConditionParams = TestDBUtility.getEnableOrderConditionParamsRow();
            l_enableOrderConditionParams.setInstitutionCode("0D");
            l_enableOrderConditionParams.setProductType(ProductTypeEnum.EQUITY);
            l_enableOrderConditionParams.setMarketCode("0");
            l_enableOrderConditionParams.setFutureOptionDiv("0");
            l_enableOrderConditionParams.setMarginTradingDiv("0");
            TestDBUtility.insertWithDel(l_enableOrderConditionParams);
            EnableOrderConditionParams l_enableOrderConditionParams1 = TestDBUtility.getEnableOrderConditionParamsRow();
            l_enableOrderConditionParams1.setInstitutionCode("0D");
            l_enableOrderConditionParams1.setProductType(ProductTypeEnum.IFO);
            l_enableOrderConditionParams1.setMarketCode("0");
            l_enableOrderConditionParams1.setFutureOptionDiv("1");
            l_enableOrderConditionParams1.setMarginTradingDiv("0");
            TestDBUtility.insertWithDel(l_enableOrderConditionParams1);
            EnableOrderConditionParams l_enableOrderConditionParams2 = TestDBUtility.getEnableOrderConditionParamsRow();
            l_enableOrderConditionParams2.setInstitutionCode("0D");
            l_enableOrderConditionParams2.setProductType(ProductTypeEnum.IFO);
            l_enableOrderConditionParams2.setMarketCode("0");
            l_enableOrderConditionParams2.setFutureOptionDiv("2");
            l_enableOrderConditionParams2.setMarginTradingDiv("0");
            TestDBUtility.insertWithDel(l_enableOrderConditionParams2);
            WEB3ToSuccIfoOrderUnitImpl l_rsvIfoOrderUnit =
                new WEB3ToSuccIfoOrderUnitImpl(l_RsvIfoOrderUnitParams);

            WEB3FuturesOptionsContractUnit[] l_units = l_impl.createIfoContractUnitByOrder(l_rsvIfoOrderUnit);
            assertEquals(3, l_units.length);
            //ID = 建玉.建玉ID
            assertEquals("1001", l_units[0].id);
            //ID = 建玉.建玉ID
            assertEquals("1002", l_units[1].id);
            //ID = 建玉.建玉ID
            assertEquals("1003", l_units[2].id);
        }
        catch (Exception l_ex)
        {
            log.error(ERROR + l_ex.getMessage(), l_ex);
            fail();
        }
    }
    
//    /**
//     * set発注済To先物OP予約注文単位
//     * 指定された先物OP予約注文単位オブジェクトを、発注済の状態に更新する。
//         DB更新仕様 「連続注文発注（OK）_先物OP予約注文単位テーブル.xls」を参照。
//     */
//    public void testSetOrderedToIfoOrderUnitCase0001()
//    {
//        final String STR_METHOD_NAME = "testSetOrderedToIfoOrderUnitCase0001()";
//        log.entering(STR_METHOD_NAME);
//        try
//        {
//            TestDBUtility.deleteAll(IfoOrderUnitRow.TYPE);
//            IfoOrderUnitParams l_ifoOrderUnitParams = TestDBUtility.getIfoOrderUnitRow();
//            l_ifoOrderUnitParams.setOrderId(1001);
//            l_ifoOrderUnitParams.setOrderUnitId(1111);
//            TestDBUtility.insertWithDel(l_ifoOrderUnitParams);
//
//            TestDBUtility.deleteAll(RsvIfoOrderUnitRow.TYPE);
//            RsvIfoOrderUnitParams l_rsvIfoOrderUnitParams = TestDBUtility.getRsvIfoOrderUnitRow();
//            l_rsvIfoOrderUnitParams.setOrderId(1001);
//            l_rsvIfoOrderUnitParams.setLastOrderActionSerialNo(1);
//            TestDBUtility.insertWithDel(l_rsvIfoOrderUnitParams);
//            
//            WEB3ToSuccIfoOrderUnitImpl l_rsvIfoOrderUnit =
//                new WEB3ToSuccIfoOrderUnitImpl(l_rsvIfoOrderUnitParams);
//            
//            l_impl.setOrderedToIfoOrderUnit(l_rsvIfoOrderUnit);
//            
//            RsvIfoOrderUnitRow l_RsvIfoOrderUnitRow = RsvIfoOrderUnitDao.findRowByOrderId(1001);
//            assertEquals(1111, l_RsvIfoOrderUnitRow.getOrderUnitId());
//            assertEquals(2, l_RsvIfoOrderUnitRow.getLastOrderActionSerialNo());
//            assertEquals("3", l_RsvIfoOrderUnitRow.getOrderStatus().intValue() + "");
//            assertEquals("2", l_RsvIfoOrderUnitRow.getOrderOpenStatus().intValue() + "");
//            assertEquals("2", l_RsvIfoOrderUnitRow.getExpirationStatus().intValue() + "");
//            assertEquals(0, WEB3DateUtility.compareToSecond(GtlUtils.getSystemTimestamp(),
//                l_RsvIfoOrderUnitRow.getLastUpdatedTimestamp()));
//        }
//        catch (Exception l_ex)
//        {
//            log.error(l_ex.getMessage(), l_ex);
//            fail();
//        }
//        log.info(STR_METHOD_NAME + ">>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>test pass !!");
//        log.exiting(TEST_END + STR_METHOD_NAME);
//    }
    
    /**
     * パラメータ.注文単位 == 先物OP予約注文単位Implかつ、 
     * 　@　@　@　@夕場前注文繰越（get立会区分() == ”夕場”）の場合
     * （*1）日中登録した当日限り注文  
     * 　@　@　@　@パラメータ.先物OP予約注文単位Impl.get注文期限区分() == ”当日限り”かつ、  
     * 　@　@　@　@パラメータ.先物OP予約注文単位Impl.立会区分 == null 
     */
    public void testIsCarryoverReserveIfoOrderUnitCase1()
    {
        final String STR_METHOD_NAME = "testIsCarryoverReserveIfoOrderUnitCase1()";
        log.entering(STR_METHOD_NAME);
        try
        { 
            TestDBUtility.deleteAll(TradingTimeRow.TYPE);
            TradingTimeParams l_TradingTimeParams = TestDBUtility.getTradingTimeRow();
            l_TradingTimeParams.setInstitutionCode("0D");
            l_TradingTimeParams.setBranchCode("123");
            l_TradingTimeParams.setMarketCode("N1");
            l_TradingTimeParams.setTradingTimeType("01");
            l_TradingTimeParams.setProductCode("0");
            l_TradingTimeParams.setBizDateType("1");
            l_TradingTimeParams.setSessionType("1");
            TestDBUtility.insertWithDel(l_TradingTimeParams);
            
            TestDBUtility.deleteAll(RsvIfoOrderUnitRow.TYPE);
            RsvIfoOrderUnitParams l_RsvIfoOrderUnitParams = TestDBUtility.getRsvIfoOrderUnitRow();
            l_RsvIfoOrderUnitParams.setOrderUnitId(1111);
            l_RsvIfoOrderUnitParams.setOrderCateg(OrderCategEnum.IDX_FUTURES_CLOSE);
            l_RsvIfoOrderUnitParams.setClosingOrder("0");
            l_RsvIfoOrderUnitParams.setQuantity(25.0);
            l_RsvIfoOrderUnitParams.setAccountId(101001010010L);
            l_RsvIfoOrderUnitParams.setExpirationDateType("1");
            l_RsvIfoOrderUnitParams.setSessionType(null);
            TestDBUtility.insertWithDel(l_RsvIfoOrderUnitParams);
            WEB3ToSuccIfoOrderUnitImpl l_rsvIfoOrderUnit =
                new WEB3ToSuccIfoOrderUnitImpl(l_RsvIfoOrderUnitParams);
            boolean l_blnResult = l_impl.isCarryoverReserveIfoOrderUnit(l_rsvIfoOrderUnit);
            assertFalse(l_blnResult);
        }
        catch (Exception l_ex)
        {
            log.error(l_ex.getMessage(), l_ex);
            fail();
        }
        log.info(STR_METHOD_NAME + ">>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>test pass !!");
        log.exiting(TEST_END + STR_METHOD_NAME);
    }
    
    //夕場前注文繰越（get立会区分() != ”夕場”）
    //上記以外の場合 引数.注文単位.注文失効日 <= 業務日付
    public void testIsCarryoverReserveIfoOrderUnitCase2()
    {
        final String STR_METHOD_NAME = "testIsCarryoverReserveIfoOrderUnitCase2()";
        log.entering(STR_METHOD_NAME);
        try
        { 
            TestDBUtility.deleteAll(TradingTimeRow.TYPE);
            TradingTimeParams l_TradingTimeParams = TestDBUtility.getTradingTimeRow();
            l_TradingTimeParams.setInstitutionCode("0D");
            l_TradingTimeParams.setBranchCode("123");
            l_TradingTimeParams.setMarketCode("N1");
            l_TradingTimeParams.setTradingTimeType("01");
            l_TradingTimeParams.setProductCode("0");
            l_TradingTimeParams.setBizDateType("1");
            l_TradingTimeParams.setSessionType("0");
            TestDBUtility.insertWithDel(l_TradingTimeParams);

            TestDBUtility.deleteAll(RsvIfoOrderUnitRow.TYPE);
            RsvIfoOrderUnitParams l_RsvIfoOrderUnitParams = TestDBUtility.getRsvIfoOrderUnitRow();
            l_RsvIfoOrderUnitParams.setOrderUnitId(1111);
            l_RsvIfoOrderUnitParams.setOrderCateg(OrderCategEnum.IDX_FUTURES_CLOSE);
            l_RsvIfoOrderUnitParams.setClosingOrder("0");
            l_RsvIfoOrderUnitParams.setQuantity(25.0);
            l_RsvIfoOrderUnitParams.setAccountId(101001010010L);
            TestDBUtility.insertWithDel(l_RsvIfoOrderUnitParams);
            WEB3ToSuccIfoOrderUnitImpl l_rsvIfoOrderUnit =
                new WEB3ToSuccIfoOrderUnitImpl(l_RsvIfoOrderUnitParams);
            boolean l_blnResult = l_impl.isCarryoverReserveIfoOrderUnit(l_rsvIfoOrderUnit);
            assertFalse(l_blnResult);
        }
        catch (Exception l_ex)
        {
            log.error(l_ex.getMessage(), l_ex);
            fail();
        }
        log.info(STR_METHOD_NAME + ">>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>test pass !!");
        log.exiting(TEST_END + STR_METHOD_NAME);
    }
    
    //パラメータ.先物OP予約注文単位Impl.立会区分 != null
    //引数.注文単位.注文失効日 > 業務日付
    public void testIsCarryoverReserveIfoOrderUnitCase3()
    {
        final String STR_METHOD_NAME = "testIsCarryoverReserveIfoOrderUnitCase3()";
        log.entering(STR_METHOD_NAME);
        try
        { 
            TestDBUtility.deleteAll(TradingTimeRow.TYPE);
            TradingTimeParams l_TradingTimeParams = TestDBUtility.getTradingTimeRow();
            l_TradingTimeParams.setInstitutionCode("0D");
            l_TradingTimeParams.setBranchCode("123");
            l_TradingTimeParams.setMarketCode("N1");
            l_TradingTimeParams.setTradingTimeType("01");
            l_TradingTimeParams.setProductCode("0");
            l_TradingTimeParams.setBizDateType("1");
            l_TradingTimeParams.setSessionType("1");
            TestDBUtility.insertWithDel(l_TradingTimeParams);
            
            TestDBUtility.deleteAll(RsvIfoOrderUnitRow.TYPE);
            RsvIfoOrderUnitParams l_RsvIfoOrderUnitParams = TestDBUtility.getRsvIfoOrderUnitRow();
            l_RsvIfoOrderUnitParams.setOrderUnitId(1111);
            l_RsvIfoOrderUnitParams.setOrderCateg(OrderCategEnum.IDX_FUTURES_CLOSE);
            l_RsvIfoOrderUnitParams.setClosingOrder("0");
            l_RsvIfoOrderUnitParams.setQuantity(25.0);
            l_RsvIfoOrderUnitParams.setAccountId(101001010010L);
            l_RsvIfoOrderUnitParams.setSessionType("0");
            l_RsvIfoOrderUnitParams.setExpirationDate(WEB3DateUtility.getDate("20100521","yyyyMMdd"));
            TestDBUtility.insertWithDel(l_RsvIfoOrderUnitParams);
            WEB3ToSuccIfoOrderUnitImpl l_rsvIfoOrderUnit =
                new WEB3ToSuccIfoOrderUnitImpl(l_RsvIfoOrderUnitParams);
            boolean l_blnResult = l_impl.isCarryoverReserveIfoOrderUnit(l_rsvIfoOrderUnit);
            assertTrue(l_blnResult);
        }
        catch (Exception l_ex)
        {
            log.error(l_ex.getMessage(), l_ex);
            fail();
        }
        log.info(STR_METHOD_NAME + ">>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>test pass !!");
        log.exiting(TEST_END + STR_METHOD_NAME);
    }

    //submit先物OP新規建繰越予約注文
    public void testSubmitIfoOpenContractCarryReserveOrderCase1()
    {
        final String STR_METHOD_NAME = "testSubmitIfoOpenContractCarryReserveOrderCase1()";
        log.entering(STR_METHOD_NAME);
        try
        {
            TestDBUtility.deleteAll(RsvIfoOrderActionRow.TYPE);
            
            TestDBUtility.deleteAll(TradingTimeRow.TYPE);
            TradingTimeParams l_TradingTimeParams = TestDBUtility.getTradingTimeRow();
            l_TradingTimeParams.setInstitutionCode("0D");
            l_TradingTimeParams.setBranchCode("123");
            l_TradingTimeParams.setMarketCode("N1");
            l_TradingTimeParams.setTradingTimeType("01");
            l_TradingTimeParams.setProductCode("0");
            l_TradingTimeParams.setBizDateType("1");
            l_TradingTimeParams.setSessionType("1");
            TestDBUtility.insertWithDel(l_TradingTimeParams);
            
            TestDBUtility.deleteAll(RsvIfoOrderUnitRow.TYPE);
            RsvIfoOrderUnitParams l_RsvIfoOrderUnitParams = TestDBUtility.getRsvIfoOrderUnitRow();
            l_RsvIfoOrderUnitParams.setOrderUnitId(1001);
            l_RsvIfoOrderUnitParams.setOrderCateg(OrderCategEnum.IDX_FUTURES_CLOSE);
            l_RsvIfoOrderUnitParams.setClosingOrder("0");
            l_RsvIfoOrderUnitParams.setQuantity(25.0);
            l_RsvIfoOrderUnitParams.setAccountId(101001010010L);
            l_RsvIfoOrderUnitParams.setSessionType("0");
            l_RsvIfoOrderUnitParams.setAccountId(333812512203L);
            l_RsvIfoOrderUnitParams.setSerialNoInParent(1);
            l_RsvIfoOrderUnitParams.setExpirationDate(WEB3DateUtility.getDate("20100521","yyyyMMdd"));
            
            TestDBUtility.deleteAll(IfoOrderUnitRow.TYPE);
            IfoOrderUnitParams l_ifoOrderUnitParams = TestDBUtility.getIfoOrderUnitRow();
            l_ifoOrderUnitParams.setOrderUnitId(1001L);
            TestDBUtility.insertWithDel(l_ifoOrderUnitParams);
            
            WEB3ToSuccIfoOrderUnitImpl l_rsvIfoOrderUnit =
                new WEB3ToSuccIfoOrderUnitImpl(l_RsvIfoOrderUnitParams);
            
            FinApp l_finApp = (FinApp)Services.getService(FinApp.class);
            WEB3FuturesOrderManagerImpl l_orderMgr =
                (WEB3FuturesOrderManagerImpl)l_finApp.getTradingModule(ProductTypeEnum.IFO).getOrderManager();
            IfoOrderUnit l_orderUnit = (IfoOrderUnit)l_orderMgr.getOrderUnit(1001L);

            l_impl.submitIfoOpenContractCarryReserveOrder(1001L, l_rsvIfoOrderUnit, l_orderUnit);
            
            RsvIfoOrderUnitRow l_row =
                RsvIfoOrderUnitDao.findRowByAccountIdParentOrderIdSerialNoInParent(
                    333812512203L, 1001L, 1);
            
            //注文単位ＩＤ        null
            assertTrue(l_row.getOrderUnitIdIsNull());
            //注文ＩＤ          取得した注文ID（自動採番）
            assertEquals(1001, l_row.getOrderId());
            //注文履歴最終通番      1
            assertEquals(1, l_row.getLastOrderActionSerialNo());
            //注文状態          "1:受付済（新規注文）（OrderStatusEnumにて定義）"
            assertEquals(OrderStatusEnum.ACCEPTED, l_row.getOrderStatus());
            //注文有効状態        "1:オープン（OrderOpenStatusEnumにて定義）"
            assertEquals(OrderOpenStatusEnum.OPEN, l_row.getOrderOpenStatus());
            //失効区分          "1:オープン（OrderExpirationStatusEnumにて定義）"
            assertEquals(OrderExpirationStatusEnum.OPEN, l_row.getExpirationStatus());
            //注文エラー理由コード        0000：正常
            assertEquals("0000", l_row.getErrorReasonCode());
            //初回注文の注文ＩＤ     "繰越元予約注文単位.初回注文の注文ＩＤ = null の場合、
            //繰越元予約注文単位.注文ＩＤそれ以外の場合、繰越元予約注文単位の同項目"
            assertEquals(1001, l_row.getFirstOrderId());
            //発注エラーコード      null
            assertNull(l_row.getOrderErrorCode());
            //親注文の注文ＩＤ      引数.繰越後の親注文単位.注文ID
            assertEquals(1001, l_row.getParentOrderId());
            //親注文の注文単位ＩＤ    引数.繰越後の親注文単位.注文単位ID
            assertEquals(1001, l_row.getParentOrderUnitId());
            //立会区分          取引時間管理.get立会区分()
            assertEquals("1", l_row.getSessionType());
            
        }
        catch (Exception l_ex)
        {
            log.error(l_ex.getMessage(), l_ex);
            fail();
        }
        log.info(STR_METHOD_NAME + ">>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>test pass !!");
        log.exiting(TEST_END + STR_METHOD_NAME);
    }
    
    public void testSubmitIfoOpenContractCarryReserveOrderCase2()
    {
        final String STR_METHOD_NAME = "testSubmitIfoOpenContractCarryReserveOrderCase2()";
        log.entering(STR_METHOD_NAME);
        try
        {
            TestDBUtility.deleteAll(RsvIfoOrderActionRow.TYPE);
            
            TestDBUtility.deleteAll(TradingTimeRow.TYPE);
            TradingTimeParams l_TradingTimeParams = TestDBUtility.getTradingTimeRow();
            l_TradingTimeParams.setInstitutionCode("0D");
            l_TradingTimeParams.setBranchCode("123");
            l_TradingTimeParams.setMarketCode("N1");
            l_TradingTimeParams.setTradingTimeType("01");
            l_TradingTimeParams.setProductCode("0");
            l_TradingTimeParams.setBizDateType("1");
            l_TradingTimeParams.setSessionType("1");
            TestDBUtility.insertWithDel(l_TradingTimeParams);
            
            TestDBUtility.deleteAll(RsvIfoOrderUnitRow.TYPE);
            RsvIfoOrderUnitParams l_RsvIfoOrderUnitParams = TestDBUtility.getRsvIfoOrderUnitRow();
            l_RsvIfoOrderUnitParams.setOrderUnitId(1001);
            l_RsvIfoOrderUnitParams.setOrderCateg(OrderCategEnum.IDX_FUTURES_CLOSE);
            l_RsvIfoOrderUnitParams.setClosingOrder("0");
            l_RsvIfoOrderUnitParams.setQuantity(25.0);
            l_RsvIfoOrderUnitParams.setAccountId(101001010010L);
            l_RsvIfoOrderUnitParams.setSessionType("0");
            l_RsvIfoOrderUnitParams.setAccountId(333812512203L);
            l_RsvIfoOrderUnitParams.setFirstOrderId(1002);
            l_RsvIfoOrderUnitParams.setSerialNoInParent(1);
            l_RsvIfoOrderUnitParams.setExpirationDate(WEB3DateUtility.getDate("20100521","yyyyMMdd"));
            
            TestDBUtility.deleteAll(IfoOrderUnitRow.TYPE);
            IfoOrderUnitParams l_ifoOrderUnitParams = TestDBUtility.getIfoOrderUnitRow();
            l_ifoOrderUnitParams.setOrderUnitId(1001L);
            TestDBUtility.insertWithDel(l_ifoOrderUnitParams);
            
            WEB3ToSuccIfoOrderUnitImpl l_rsvIfoOrderUnit =
                new WEB3ToSuccIfoOrderUnitImpl(l_RsvIfoOrderUnitParams);
            
            FinApp l_finApp = (FinApp)Services.getService(FinApp.class);
            WEB3FuturesOrderManagerImpl l_orderMgr =
                (WEB3FuturesOrderManagerImpl)l_finApp.getTradingModule(ProductTypeEnum.IFO).getOrderManager();
            IfoOrderUnit l_orderUnit = (IfoOrderUnit)l_orderMgr.getOrderUnit(1001L);

            l_impl.submitIfoOpenContractCarryReserveOrder(1001L, l_rsvIfoOrderUnit, l_orderUnit);
            
            RsvIfoOrderUnitRow l_row =
                RsvIfoOrderUnitDao.findRowByAccountIdParentOrderIdSerialNoInParent(
                    333812512203L, 1001L, 1);
            
            //注文単位ＩＤ        null
            assertTrue(l_row.getOrderUnitIdIsNull());
            //注文ＩＤ          取得した注文ID（自動採番）
            assertEquals(1001, l_row.getOrderId());
            //注文履歴最終通番      1
            assertEquals(1, l_row.getLastOrderActionSerialNo());
            //注文状態          "1:受付済（新規注文）（OrderStatusEnumにて定義）"
            assertEquals(OrderStatusEnum.ACCEPTED, l_row.getOrderStatus());
            //注文有効状態        "1:オープン（OrderOpenStatusEnumにて定義）"
            assertEquals(OrderOpenStatusEnum.OPEN, l_row.getOrderOpenStatus());
            //失効区分          "1:オープン（OrderExpirationStatusEnumにて定義）"
            assertEquals(OrderExpirationStatusEnum.OPEN, l_row.getExpirationStatus());
            //注文エラー理由コード        0000：正常
            assertEquals("0000", l_row.getErrorReasonCode());
            //初回注文の注文ＩＤ     "繰越元予約注文単位.初回注文の注文ＩＤ = null の場合、
            //繰越元予約注文単位.注文ＩＤそれ以外の場合、繰越元予約注文単位の同項目"
            assertEquals(1002, l_row.getFirstOrderId());
            //発注エラーコード      null
            assertNull(l_row.getOrderErrorCode());
            //親注文の注文ＩＤ      引数.繰越後の親注文単位.注文ID
            assertEquals(1001, l_row.getParentOrderId());
            //親注文の注文単位ＩＤ    引数.繰越後の親注文単位.注文単位ID
            assertEquals(1001, l_row.getParentOrderUnitId());
            //立会区分          取引時間管理.get立会区分()
            assertEquals("1", l_row.getSessionType());
            
        }
        catch (Exception l_ex)
        {
            log.error(l_ex.getMessage(), l_ex);
            fail();
        }
        log.info(STR_METHOD_NAME + ">>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>test pass !!");
        log.exiting(TEST_END + STR_METHOD_NAME);
    }
    
    
    //submit先物OP返済繰越予約注文
    public void testSubmitIfoCloseContractCarryReserveOrderCase1()
    {
        final String STR_METHOD_NAME = "testSubmitIfoCloseContractCarryReserveOrderCase1()";
        log.entering(STR_METHOD_NAME);
        try
        {
            TestDBUtility.deleteAll(RsvIfoOrderActionRow.TYPE);
            TestDBUtility.deleteAll(RsvIfoClosingContractSpecRow.TYPE);
            
            TestDBUtility.deleteAll(TradingTimeRow.TYPE);
            TradingTimeParams l_TradingTimeParams = TestDBUtility.getTradingTimeRow();
            l_TradingTimeParams.setInstitutionCode("0D");
            l_TradingTimeParams.setBranchCode("123");
            l_TradingTimeParams.setMarketCode("N1");
            l_TradingTimeParams.setTradingTimeType("01");
            l_TradingTimeParams.setProductCode("0");
            l_TradingTimeParams.setBizDateType("1");
            l_TradingTimeParams.setSessionType("1");
            TestDBUtility.insertWithDel(l_TradingTimeParams);
            
            TestDBUtility.deleteAll(RsvIfoOrderUnitRow.TYPE);
            RsvIfoOrderUnitParams l_RsvIfoOrderUnitParams = TestDBUtility.getRsvIfoOrderUnitRow();
            l_RsvIfoOrderUnitParams.setOrderUnitId(1001);
            l_RsvIfoOrderUnitParams.setOrderCateg(OrderCategEnum.IDX_FUTURES_CLOSE);
            l_RsvIfoOrderUnitParams.setClosingOrder("0");
            l_RsvIfoOrderUnitParams.setQuantity(25.0);
            l_RsvIfoOrderUnitParams.setAccountId(101001010010L);
            l_RsvIfoOrderUnitParams.setSessionType("0");
            l_RsvIfoOrderUnitParams.setAccountId(333812512203L);
            l_RsvIfoOrderUnitParams.setFirstOrderId(1002);
            l_RsvIfoOrderUnitParams.setSerialNoInParent(1);
            l_RsvIfoOrderUnitParams.setExpirationDate(WEB3DateUtility.getDate("20100521","yyyyMMdd"));
            
            TestDBUtility.deleteAll(IfoOrderUnitRow.TYPE);
            IfoOrderUnitParams l_ifoOrderUnitParams = TestDBUtility.getIfoOrderUnitRow();
            l_ifoOrderUnitParams.setOrderUnitId(1001L);
            TestDBUtility.insertWithDel(l_ifoOrderUnitParams);
             
            WEB3ToSuccIfoOrderUnitImpl l_rsvIfoOrderUnit =
                new WEB3ToSuccIfoOrderUnitImpl(l_RsvIfoOrderUnitParams);
            
            FinApp l_finApp = (FinApp)Services.getService(FinApp.class);
            WEB3FuturesOrderManagerImpl l_orderMgr =
                (WEB3FuturesOrderManagerImpl)l_finApp.getTradingModule(ProductTypeEnum.IFO).getOrderManager();
            IfoOrderUnit l_orderUnit = (IfoOrderUnit)l_orderMgr.getOrderUnit(1001L);

            SettleContractEntry[] l_eqOrderEntry = new SettleContractEntry[1];
            l_eqOrderEntry[0] = new  SettleContractEntry(12345,54321);
            
            l_impl.submitIfoCloseContractCarryReserveOrder(1001L, l_rsvIfoOrderUnit, l_orderUnit, l_eqOrderEntry);
            
            RsvIfoOrderUnitRow l_row =
                RsvIfoOrderUnitDao.findRowByAccountIdParentOrderIdSerialNoInParent(
                    333812512203L, 1001L, 1);
            
            //注文単位ＩＤ        null
            assertTrue(l_row.getOrderUnitIdIsNull());
            //注文ＩＤ          取得した注文ID（自動採番）
            assertEquals(1001, l_row.getOrderId());
            //注文履歴最終通番      1
            assertEquals(1, l_row.getLastOrderActionSerialNo());
            //注文状態          "1:受付済（新規注文）（OrderStatusEnumにて定義）"
            assertEquals(OrderStatusEnum.ACCEPTED, l_row.getOrderStatus());
            //注文有効状態        "1:オープン（OrderOpenStatusEnumにて定義）"
            assertEquals(OrderOpenStatusEnum.OPEN, l_row.getOrderOpenStatus());
            //失効区分          "1:オープン（OrderExpirationStatusEnumにて定義）"
            assertEquals(OrderExpirationStatusEnum.OPEN, l_row.getExpirationStatus());
            //注文エラー理由コード        0000：正常
            assertEquals("0000", l_row.getErrorReasonCode());
            //初回注文の注文ＩＤ     "繰越元予約注文単位.初回注文の注文ＩＤ = null の場合、
            //繰越元予約注文単位.注文ＩＤそれ以外の場合、繰越元予約注文単位の同項目"
            assertEquals(1002, l_row.getFirstOrderId());
            //発注エラーコード      null
            assertNull(l_row.getOrderErrorCode());
            //親注文の注文ＩＤ      引数.繰越後の親注文単位.注文ID
            assertEquals(1001, l_row.getParentOrderId());
            //親注文の注文単位ＩＤ    引数.繰越後の親注文単位.注文単位ID
            assertEquals(1001, l_row.getParentOrderUnitId());
            //立会区分          取引時間管理.get立会区分()
            assertEquals("1", l_row.getSessionType());
            
            RsvIfoClosingContractSpecRow l_contractRow =
                RsvIfoClosingContractSpecDao.findRowByOrderIdContractId(1001, 12345);
            //引数の繰越元予約注文単位.口座ID      
            assertEquals(333812512203L, l_contractRow.getAccountId());
            //引数の繰越元予約注文単位.補助口座ID          
            assertEquals(33381251220301L, l_contractRow.getSubAccountId());
            //引数の注文ID             
            assertEquals(1001L, l_contractRow.getOrderId());
            //引数の返済建玉エントリ[index].getContractId()     
            assertEquals(12345, l_contractRow.getContractId());
            //index + 1  
            assertEquals(1, l_contractRow.getClosingSerialNo());
            //引数の返済建玉エントリ[index].getQuantity()
            assertEquals("54321.0", l_contractRow.getQuantity() + "");
        }
        catch (Exception l_ex)
        {
            log.error(l_ex.getMessage(), l_ex);
            fail();
        }
        log.info(STR_METHOD_NAME + ">>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>test pass !!");
        log.exiting(TEST_END + STR_METHOD_NAME);
    }
    
    public void testSubmitIfoCloseContractCarryReserveOrderCase2()
    {
        final String STR_METHOD_NAME = "testSubmitIfoCloseContractCarryReserveOrderCase2()";
        log.entering(STR_METHOD_NAME);
        try
        {
            TestDBUtility.deleteAll(RsvIfoOrderActionRow.TYPE);
            TestDBUtility.deleteAll(RsvIfoClosingContractSpecRow.TYPE);
            
            TestDBUtility.deleteAll(MainAccountRow.TYPE);
            MainAccountParams l_mainAccountParams = TestDBUtility.getMainAccountRow();
            l_mainAccountParams.setAccountId(333812512203L);
            l_mainAccountParams.setIfoAccOpenDivTokyo("2");
            TestDBUtility.insertWithDel(l_mainAccountParams);
            
            TestDBUtility.deleteAll(TradingTimeRow.TYPE);
            TradingTimeParams l_TradingTimeParams = TestDBUtility.getTradingTimeRow();
            l_TradingTimeParams.setInstitutionCode("0D");
            l_TradingTimeParams.setBranchCode("123");
            l_TradingTimeParams.setMarketCode("N1");
            l_TradingTimeParams.setTradingTimeType("01");
            l_TradingTimeParams.setProductCode("0");
            l_TradingTimeParams.setBizDateType("1");
            l_TradingTimeParams.setSessionType("1");
            TestDBUtility.insertWithDel(l_TradingTimeParams);
            
            TestDBUtility.deleteAll(RsvIfoOrderUnitRow.TYPE);
            RsvIfoOrderUnitParams l_RsvIfoOrderUnitParams = TestDBUtility.getRsvIfoOrderUnitRow();
            l_RsvIfoOrderUnitParams.setOrderUnitId(1001);
            l_RsvIfoOrderUnitParams.setOrderCateg(OrderCategEnum.IDX_FUTURES_CLOSE);
            l_RsvIfoOrderUnitParams.setClosingOrder("0");
            l_RsvIfoOrderUnitParams.setQuantity(25.0);
            l_RsvIfoOrderUnitParams.setSessionType("0");
            l_RsvIfoOrderUnitParams.setAccountId(333812512203L);
            l_RsvIfoOrderUnitParams.setFirstOrderId(1002);
            l_RsvIfoOrderUnitParams.setSerialNoInParent(1);
            l_RsvIfoOrderUnitParams.setReserveOrderTradingType("13");
            l_RsvIfoOrderUnitParams.setExpirationDate(WEB3DateUtility.getDate("20100521","yyyyMMdd"));
            
            TestDBUtility.deleteAll(IfoOrderUnitRow.TYPE);
            IfoOrderUnitParams l_ifoOrderUnitParams = TestDBUtility.getIfoOrderUnitRow();
            l_ifoOrderUnitParams.setOrderUnitId(1001L);
            l_ifoOrderUnitParams.setOrderType(OrderTypeEnum.IDX_FUTURES_BUY_TO_OPEN);
            l_ifoOrderUnitParams.setAccountId(333812512203L);
            TestDBUtility.insertWithDel(l_ifoOrderUnitParams);
             
            WEB3ToSuccIfoOrderUnitImpl l_rsvIfoOrderUnit =
                new WEB3ToSuccIfoOrderUnitImpl(l_RsvIfoOrderUnitParams);
            
            FinApp l_finApp = (FinApp)Services.getService(FinApp.class);
            WEB3FuturesOrderManagerImpl l_orderMgr =
                (WEB3FuturesOrderManagerImpl)l_finApp.getTradingModule(ProductTypeEnum.IFO).getOrderManager();
            IfoOrderUnit l_orderUnit = (IfoOrderUnit)l_orderMgr.getOrderUnit(1001L);

            SettleContractEntry[] l_eqOrderEntry = new SettleContractEntry[1];
            l_eqOrderEntry[0] = new  SettleContractEntry(12345,54321);
            
            l_impl.submitIfoCloseContractCarryReserveOrder(1001L, l_rsvIfoOrderUnit, l_orderUnit, l_eqOrderEntry);
            
            RsvIfoOrderUnitRow l_row =
                RsvIfoOrderUnitDao.findRowByAccountIdParentOrderIdSerialNoInParent(
                    333812512203L, 1001L, 1);
            
            //注文単位ＩＤ        null
            assertTrue(l_row.getOrderUnitIdIsNull());
            //注文ＩＤ          取得した注文ID（自動採番）
            assertEquals(1001, l_row.getOrderId());
            //注文履歴最終通番      1
            assertEquals(1, l_row.getLastOrderActionSerialNo());
            //注文状態          "1:受付済（新規注文）（OrderStatusEnumにて定義）"
            assertEquals(OrderStatusEnum.ACCEPTED, l_row.getOrderStatus());
            //注文有効状態        "1:オープン（OrderOpenStatusEnumにて定義）"
            assertEquals(OrderOpenStatusEnum.OPEN, l_row.getOrderOpenStatus());
            //失効区分          "1:オープン（OrderExpirationStatusEnumにて定義）"
            assertEquals(OrderExpirationStatusEnum.OPEN, l_row.getExpirationStatus());
            //注文エラー理由コード        0000：正常
            assertEquals("0000", l_row.getErrorReasonCode());
            //初回注文の注文ＩＤ     "繰越元予約注文単位.初回注文の注文ＩＤ = null の場合、
            //繰越元予約注文単位.注文ＩＤそれ以外の場合、繰越元予約注文単位の同項目"
            assertEquals(1002, l_row.getFirstOrderId());
            //発注エラーコード      null
            assertNull(l_row.getOrderErrorCode());
            //親注文の注文ＩＤ      引数.繰越後の親注文単位.注文ID
            assertEquals(1001, l_row.getParentOrderId());
            //親注文の注文単位ＩＤ    引数.繰越後の親注文単位.注文単位ID
            assertEquals(1001, l_row.getParentOrderUnitId());
            //立会区分          取引時間管理.get立会区分()
            assertEquals("1", l_row.getSessionType());
            
            RsvIfoClosingContractSpecRow l_contractRow =
                RsvIfoClosingContractSpecDao.findRowByOrderIdContractId(1001, 12345);
            assertNull(l_contractRow);
        }
        catch (Exception l_ex)
        {
            log.error(l_ex.getMessage(), l_ex);
            fail();
        }
        log.info(STR_METHOD_NAME + ">>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>test pass !!");
        log.exiting(TEST_END + STR_METHOD_NAME);
    }
    
    //validate夕場まで注文訂正可能
    public void testvalidateEveningSessionOrderPossibleChangeCase1()
    {
        final String STR_METHOD_NAME = "testvalidateEveningSessionOrderPossibleChangeCase1()";
        log.entering(STR_METHOD_NAME);
        try
        {
            TestDBUtility.deleteAll(RsvIfoOrderActionRow.TYPE);
            TestDBUtility.deleteAll(RsvIfoClosingContractSpecRow.TYPE);
            
            TestDBUtility.deleteAll(MainAccountRow.TYPE);
            MainAccountParams l_mainAccountParams = TestDBUtility.getMainAccountRow();
            l_mainAccountParams.setAccountId(333812512203L);
            l_mainAccountParams.setIfoAccOpenDivTokyo("2");
            TestDBUtility.insertWithDel(l_mainAccountParams);
            
            TestDBUtility.deleteAll(TradingTimeRow.TYPE);
            TradingTimeParams l_TradingTimeParams = TestDBUtility.getTradingTimeRow();
            l_TradingTimeParams.setInstitutionCode("0D");
            l_TradingTimeParams.setBranchCode("123");
            l_TradingTimeParams.setMarketCode("N1");
            l_TradingTimeParams.setTradingTimeType("01");
            l_TradingTimeParams.setProductCode("0");
            l_TradingTimeParams.setBizDateType("1");
            l_TradingTimeParams.setSessionType("1");
            TestDBUtility.insertWithDel(l_TradingTimeParams);
            
            TestDBUtility.deleteAll(RsvIfoOrderUnitRow.TYPE);
            RsvIfoOrderUnitParams l_RsvIfoOrderUnitParams = TestDBUtility.getRsvIfoOrderUnitRow();
            l_RsvIfoOrderUnitParams.setOrderUnitId(1001);
            l_RsvIfoOrderUnitParams.setOrderCateg(OrderCategEnum.IDX_FUTURES_CLOSE);
            l_RsvIfoOrderUnitParams.setClosingOrder("0");
            l_RsvIfoOrderUnitParams.setQuantity(25.0);
            l_RsvIfoOrderUnitParams.setSessionType("0");
            l_RsvIfoOrderUnitParams.setAccountId(333812512203L);
            l_RsvIfoOrderUnitParams.setFirstOrderId(1002);
            l_RsvIfoOrderUnitParams.setSerialNoInParent(1);
            l_RsvIfoOrderUnitParams.setReserveOrderTradingType("13");
            l_RsvIfoOrderUnitParams.setExpirationDateType("3");
            l_RsvIfoOrderUnitParams.setExpirationDate(WEB3DateUtility.getDate("20100521","yyyyMMdd"));
            
            TestDBUtility.deleteAll(IfoOrderUnitRow.TYPE);
            IfoOrderUnitParams l_ifoOrderUnitParams = TestDBUtility.getIfoOrderUnitRow();
            l_ifoOrderUnitParams.setOrderUnitId(1001L);
            l_ifoOrderUnitParams.setOrderType(OrderTypeEnum.IDX_FUTURES_BUY_TO_OPEN);
            l_ifoOrderUnitParams.setAccountId(333812512203L);
            TestDBUtility.insertWithDel(l_ifoOrderUnitParams);
            
             
            WEB3ToSuccIfoOrderUnitImpl l_rsvIfoOrderUnit =
                new WEB3ToSuccIfoOrderUnitImpl(l_RsvIfoOrderUnitParams);

            SettleContractEntry[] l_eqOrderEntry = new SettleContractEntry[1];
            l_eqOrderEntry[0] = new  SettleContractEntry(12345,54321);
            
            l_impl.validateEveningSessionOrderPossibleChange("1", l_rsvIfoOrderUnit);
            
        }
        catch (Exception l_ex)
        {
            log.error(l_ex.getMessage(), l_ex);
            fail();
        }
        log.info(STR_METHOD_NAME + ">>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>test pass !!");
        log.exiting(TEST_END + STR_METHOD_NAME);
    }
    
    public void testvalidateEveningSessionOrderPossibleChangeCase2()
    {
        final String STR_METHOD_NAME = "testvalidateEveningSessionOrderPossibleChangeCase2()";
        log.entering(STR_METHOD_NAME);
        try
        {
            TestDBUtility.deleteAll(RsvIfoOrderActionRow.TYPE);
            TestDBUtility.deleteAll(RsvIfoClosingContractSpecRow.TYPE);
            
            TestDBUtility.deleteAll(MainAccountRow.TYPE);
            MainAccountParams l_mainAccountParams = TestDBUtility.getMainAccountRow();
            l_mainAccountParams.setAccountId(333812512203L);
            l_mainAccountParams.setIfoAccOpenDivTokyo("2");
            TestDBUtility.insertWithDel(l_mainAccountParams);
            
            TestDBUtility.deleteAll(TradingTimeRow.TYPE);
            TradingTimeParams l_TradingTimeParams = TestDBUtility.getTradingTimeRow();
            l_TradingTimeParams.setInstitutionCode("0D");
            l_TradingTimeParams.setBranchCode("123");
            l_TradingTimeParams.setMarketCode("N1");
            l_TradingTimeParams.setTradingTimeType("01");
            l_TradingTimeParams.setProductCode("0");
            l_TradingTimeParams.setBizDateType("1");
            l_TradingTimeParams.setSessionType("1");
            TestDBUtility.insertWithDel(l_TradingTimeParams);
            
            TestDBUtility.deleteAll(RsvIfoOrderUnitRow.TYPE);
            RsvIfoOrderUnitParams l_RsvIfoOrderUnitParams = TestDBUtility.getRsvIfoOrderUnitRow();
            l_RsvIfoOrderUnitParams.setOrderUnitId(1001);
            l_RsvIfoOrderUnitParams.setOrderCateg(OrderCategEnum.IDX_FUTURES_CLOSE);
            l_RsvIfoOrderUnitParams.setClosingOrder("0");
            l_RsvIfoOrderUnitParams.setQuantity(25.0);
            l_RsvIfoOrderUnitParams.setSessionType("0");
            l_RsvIfoOrderUnitParams.setAccountId(333812512203L);
            l_RsvIfoOrderUnitParams.setFirstOrderId(1002);
            l_RsvIfoOrderUnitParams.setSerialNoInParent(1);
            l_RsvIfoOrderUnitParams.setReserveOrderTradingType("13");
            l_RsvIfoOrderUnitParams.setExpirationDateType("1");
            l_RsvIfoOrderUnitParams.setFutureOptionDiv("1");
            l_RsvIfoOrderUnitParams.setExpirationDate(WEB3DateUtility.getDate("20100521","yyyyMMdd"));
            
            TestDBUtility.deleteAll(IfoOrderUnitRow.TYPE);
            IfoOrderUnitParams l_ifoOrderUnitParams = TestDBUtility.getIfoOrderUnitRow();
            l_ifoOrderUnitParams.setOrderUnitId(1001L);
            l_ifoOrderUnitParams.setOrderType(OrderTypeEnum.IDX_FUTURES_BUY_TO_OPEN);
            l_ifoOrderUnitParams.setAccountId(333812512203L);
            TestDBUtility.insertWithDel(l_ifoOrderUnitParams);
            
            TestDBUtility.deleteAll(EnableOrderConditionParams.TYPE);
            EnableOrderConditionParams l_enableOrderConditionParams =
                TestDBUtility.getEnableOrderConditionParamsRow();
            l_enableOrderConditionParams.setInstitutionCode("0D");
            l_enableOrderConditionParams.setProductType(ProductTypeEnum.IFO);
            l_enableOrderConditionParams.setFutureOptionDiv("1");
            l_enableOrderConditionParams.setMarginTradingDiv("0");
            l_enableOrderConditionParams.setMarketCode("0");
            TestDBUtility.insertWithDel(l_enableOrderConditionParams);
            
            WEB3ToSuccIfoOrderUnitImpl l_rsvIfoOrderUnit =
                new WEB3ToSuccIfoOrderUnitImpl(l_RsvIfoOrderUnitParams);
            

            SettleContractEntry[] l_eqOrderEntry = new SettleContractEntry[1];
            l_eqOrderEntry[0] = new  SettleContractEntry(12345,54321);
            
            l_impl.validateEveningSessionOrderPossibleChange("3", l_rsvIfoOrderUnit);
            fail();
        }
        catch (WEB3BusinessLayerException l_ex)
        {
            assertEquals(WEB3ErrorCatalog.BUSINESS_ERROR_02816, l_ex.getErrorInfo());
        }
        catch (Exception l_ex)
        {
            log.error(l_ex.getMessage(), l_ex);
            fail();
        }        log.info(STR_METHOD_NAME + ">>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>test pass !!");
        log.exiting(TEST_END + STR_METHOD_NAME);
    }
    
    public void testvalidateEveningSessionOrderPossibleChangeCase3()
    {
        final String STR_METHOD_NAME = "testvalidateEveningSessionOrderPossibleChangeCase3()";
        log.entering(STR_METHOD_NAME);
        try
        {
            TestDBUtility.deleteAll(RsvIfoOrderActionRow.TYPE);
            TestDBUtility.deleteAll(RsvIfoClosingContractSpecRow.TYPE);
            
            TestDBUtility.deleteAll(MainAccountRow.TYPE);
            MainAccountParams l_mainAccountParams = TestDBUtility.getMainAccountRow();
            l_mainAccountParams.setAccountId(333812512203L);
            l_mainAccountParams.setIfoAccOpenDivTokyo("2");
            TestDBUtility.insertWithDel(l_mainAccountParams);
            
            TestDBUtility.deleteAll(TradingTimeRow.TYPE);
            TradingTimeParams l_TradingTimeParams = TestDBUtility.getTradingTimeRow();
            l_TradingTimeParams.setInstitutionCode("0D");
            l_TradingTimeParams.setBranchCode("123");
            l_TradingTimeParams.setMarketCode("N1");
            l_TradingTimeParams.setTradingTimeType("01");
            l_TradingTimeParams.setProductCode("0");
            l_TradingTimeParams.setBizDateType("1");
            l_TradingTimeParams.setSessionType("0");
            TestDBUtility.insertWithDel(l_TradingTimeParams);
            
            TestDBUtility.deleteAll(RsvIfoOrderUnitRow.TYPE);
            RsvIfoOrderUnitParams l_RsvIfoOrderUnitParams = TestDBUtility.getRsvIfoOrderUnitRow();
            l_RsvIfoOrderUnitParams.setOrderUnitId(1001);
            l_RsvIfoOrderUnitParams.setOrderCateg(OrderCategEnum.IDX_FUTURES_CLOSE);
            l_RsvIfoOrderUnitParams.setClosingOrder("0");
            l_RsvIfoOrderUnitParams.setQuantity(25.0);
            l_RsvIfoOrderUnitParams.setSessionType("0");
            l_RsvIfoOrderUnitParams.setAccountId(333812512203L);
            l_RsvIfoOrderUnitParams.setFirstOrderId(1002);
            l_RsvIfoOrderUnitParams.setSerialNoInParent(1);
            l_RsvIfoOrderUnitParams.setReserveOrderTradingType("13");
            l_RsvIfoOrderUnitParams.setExpirationDateType("1");
            l_RsvIfoOrderUnitParams.setFutureOptionDiv("1");
            l_RsvIfoOrderUnitParams.setExpirationDate(WEB3DateUtility.getDate("20100521","yyyyMMdd"));
            
            TestDBUtility.deleteAll(IfoOrderUnitRow.TYPE);
            IfoOrderUnitParams l_ifoOrderUnitParams = TestDBUtility.getIfoOrderUnitRow();
            l_ifoOrderUnitParams.setOrderUnitId(1001L);
            l_ifoOrderUnitParams.setOrderType(OrderTypeEnum.IDX_FUTURES_BUY_TO_OPEN);
            l_ifoOrderUnitParams.setAccountId(333812512203L);
            TestDBUtility.insertWithDel(l_ifoOrderUnitParams);
            
            TestDBUtility.deleteAll(EnableOrderConditionParams.TYPE);
            EnableOrderConditionParams l_enableOrderConditionParams =
                TestDBUtility.getEnableOrderConditionParamsRow();
            l_enableOrderConditionParams.setInstitutionCode("0D");
            l_enableOrderConditionParams.setProductType(ProductTypeEnum.IFO);
            l_enableOrderConditionParams.setFutureOptionDiv("1");
            l_enableOrderConditionParams.setMarginTradingDiv("0");
            l_enableOrderConditionParams.setMarketCode("0");
            l_enableOrderConditionParams.setEveningSessionOrder("1");
            TestDBUtility.insertWithDel(l_enableOrderConditionParams);
            
            WEB3ToSuccIfoOrderUnitImpl l_rsvIfoOrderUnit =
                new WEB3ToSuccIfoOrderUnitImpl(l_RsvIfoOrderUnitParams);
            

            SettleContractEntry[] l_eqOrderEntry = new SettleContractEntry[1];
            l_eqOrderEntry[0] = new  SettleContractEntry(12345,54321);
            
            l_impl.validateEveningSessionOrderPossibleChange("3", l_rsvIfoOrderUnit);
        }
        catch (Exception l_ex)
        {
            log.error(l_ex.getMessage(), l_ex);
            fail();
        }        log.info(STR_METHOD_NAME + ">>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>test pass !!");
        log.exiting(TEST_END + STR_METHOD_NAME);
    }
    
    private class WEB3ToSuccOrderManagerImplForTest extends WEB3ToSuccOrderManagerImpl
    {
        public boolean isReversingTrade(
            String l_strRsvOrderTradingDiv, 
            OrderUnit l_parentOrderUnit) throws WEB3BaseException
        {
            if ("01".equals(l_strRsvOrderTradingDiv))
            {
                return false;
            }

            return true;
        }
        
        protected double getSuccOrderMaxQuantity(OrderUnit l_parentOrderUnit) throws WEB3BaseException
        {
            
            return 10;
        }
        
        protected void setOrderedToIfoOrderUnit(
                WEB3ToSuccIfoOrderUnitImpl l_rsvIfoOrderUnit) throws WEB3BaseException
        {
            return;
        }
        
        public void validateSuccOrderTrade(
            String l_strRsvOrderTradingType, 
            OrderUnit l_parentOrderUnit) throws WEB3BaseException
        {
            return;
        }
        
        public void validateTriggerOrderSettingToParentOrder(OrderUnit l_parentOrderUnit) 
        throws WEB3BaseException 
        {
            return;
        }
        
        public void validateSuccOrderHandling(
            String l_strInstitutionCode, 
            ProductTypeEnum l_productType, 
            String l_strFutureOptionDiv) throws WEB3BaseException
        {
            return;
        }
        
        public String[] getToSuccOrderDealtCommodityList(String l_strInstitutionCode) throws WEB3BaseException
        {
            return new String[]{"3", "4"};
        }
    }  
    
}
@
