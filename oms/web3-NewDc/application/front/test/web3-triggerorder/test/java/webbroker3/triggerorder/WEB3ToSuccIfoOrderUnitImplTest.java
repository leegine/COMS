head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.04.07.00.42.20;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	5444d9d078c63f4;
filename	WEB3ToSuccIfoOrderUnitImplTest.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : 先物OP予約注文単位Implテスト(WEB3ToSuccIfoOrderUnitImplTest.java)
Author Name      : Daiwa Institute of Research
Revision History : 2007/03/28 崔遠鵬 (中訊) 新規作成
*/
package webbroker3.triggerorder;

import java.util.ArrayList;
import java.util.List;

import com.fitechlabs.xtrade.plugin.tc.gentrade.OrderCategEnum;
import com.fitechlabs.xtrade.plugin.tc.gentrade.OrderExpirationStatusEnum;
import com.fitechlabs.xtrade.plugin.tc.gentrade.OrderOpenStatusEnum;
import com.fitechlabs.xtrade.plugin.tc.gentrade.OrderStatusEnum;
import com.fitechlabs.xtrade.plugin.tc.gentrade.OrderTypeEnum;
import com.fitechlabs.xtrade.plugin.tc.gentrade.OrderUnit;
import com.fitechlabs.xtrade.plugin.tc.gentrade.ProductTypeEnum;
import com.fitechlabs.xtrade.plugin.tc.gentrade.SideEnum;
import com.fitechlabs.xtrade.plugin.tc.gentrade.TaxTypeEnum;
import com.fitechlabs.xtrade.plugin.tc.gentrade.TradedProduct;
import com.fitechlabs.xtrade.plugin.tc.gentrade.data.MarketParams;
import com.fitechlabs.xtrade.plugin.tc.gentrade.data.MarketRow;
import com.fitechlabs.xtrade.plugin.tc.gentrade.data.ProductParams;
import com.fitechlabs.xtrade.plugin.tc.gentrade.data.ProductRow;
import com.fitechlabs.xtrade.plugin.tc.gentrade.data.TradedProductParams;
import com.fitechlabs.xtrade.plugin.tc.gentrade.data.TradedProductRow;
import com.fitechlabs.xtrade.plugin.tc.xbifo.IfoDerivativeTypeEnum;
import com.fitechlabs.xtrade.plugin.tc.xbifo.IfoOrderUnit;
import com.fitechlabs.xtrade.plugin.tc.xbifo.data.IfoContractParams;
import com.fitechlabs.xtrade.plugin.tc.xbifo.data.IfoFinTransactionParams;
import com.fitechlabs.xtrade.plugin.tc.xbifo.data.IfoLockedContractDetailsParams;
import com.fitechlabs.xtrade.plugin.tc.xbifo.data.IfoLockedContractDetailsRow;
import com.fitechlabs.xtrade.plugin.tc.xbifo.data.IfoOrderUnitParams;
import com.fitechlabs.xtrade.plugin.tc.xbifo.data.IfoOrderUnitRow;
import com.fitechlabs.xtrade.plugin.tc.xbifo.data.IfoProductParams;
import com.fitechlabs.xtrade.plugin.tc.xbifo.data.IfoProductRow;
import com.fitechlabs.xtrade.plugin.tc.xbifo.data.IfoTradedProductParams;
import com.fitechlabs.xtrade.plugin.tc.xbifo.data.IfoTradedProductRow;

import test.util.TestDBUtility;

import webbroker3.common.WEB3BaseException;
import webbroker3.common.WEB3BusinessLayerException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.common.define.WEB3ClosingOrderDef;
import webbroker3.gentrade.data.OrderexecutionEndParams;
import webbroker3.gentrade.data.OrderexecutionEndRow;
import webbroker3.gentrade.data.TradingTimeParams;
import webbroker3.gentrade.data.TradingTimeRow;
import webbroker3.ifo.WEB3IfoTradedProductImpl;
import webbroker3.mock.TestBaseForMock;
import webbroker3.mock.util.WEB3MockObjectParamsValue;
import webbroker3.triggerorder.base.data.RsvIfoClosingContractSpecParams;
import webbroker3.triggerorder.base.data.RsvIfoClosingContractSpecRow;
import webbroker3.triggerorder.base.data.RsvIfoOrderActionParams;
import webbroker3.triggerorder.base.data.RsvIfoOrderActionRow;
import webbroker3.triggerorder.base.data.RsvIfoOrderUnitParams;
import webbroker3.triggerorder.base.data.RsvIfoOrderUnitRow;
import webbroker3.triggerorder.message.WEB3SuccCommonInfo;
import webbroker3.triggerorder.message.WEB3SuccPriceAdjustmentValueInfo;
import webbroker3.triggerorder.util.WEB3ToSuccOrderManagerUtility;
import webbroker3.util.WEB3DateUtility;
import webbroker3.util.WEB3LogUtility;

/**
 * (先物OP予約注文単位Implテスト)<BR>
 * @@author 崔遠鵬
 * @@version 1.0
 */
public class WEB3ToSuccIfoOrderUnitImplTest extends TestBaseForMock
{
    /**
     * ログユーティリティ<BR>
     */
    private static WEB3LogUtility log =
        WEB3LogUtility.getInstance(WEB3ToSuccIfoOrderUnitImplTest.class);

    public WEB3ToSuccIfoOrderUnitImplTest(String arg0)
    {
        super(arg0);
    }

    protected void setUp() throws Exception
    {
        super.setUp();
        TestBaseForMock.MOCK_MANAGER.setIsMockUsed(true);
        WEB3ToSuccOrderManagerUtility.changeOrderManager();
    }

    protected void tearDown() throws Exception
    {
        super.tearDown();
    }

    public void testGetSetMethod_C001()
    {
        final String STR_METHOD_NAME = " testGetSetMethod_C001";
        log.entering(TEST_START + STR_METHOD_NAME);

        try
        {
            TestDBUtility.deleteAll(RsvIfoOrderUnitRow.TYPE);
            RsvIfoOrderUnitParams l_rsvIfoOrderUnitParams = TestDBUtility.getRsvIfoOrderUnitRow();
            l_rsvIfoOrderUnitParams.setExpirationStatus(OrderExpirationStatusEnum.OPEN);
            l_rsvIfoOrderUnitParams.setOrderId(1002);
            l_rsvIfoOrderUnitParams.setTraderId(20L);
            l_rsvIfoOrderUnitParams.setLimitPrice(30.0d);
            l_rsvIfoOrderUnitParams.setCreatedTimestamp(WEB3DateUtility.getDate("20080301", "yyyyMMdd"));
            l_rsvIfoOrderUnitParams.setExpirationDate(WEB3DateUtility.getDate("20080302", "yyyyMMdd"));
            l_rsvIfoOrderUnitParams.setMarketId(1006139070605L);
            l_rsvIfoOrderUnitParams.setProductId(1001L);
            l_rsvIfoOrderUnitParams.setFutureOptionDiv("1");
            l_rsvIfoOrderUnitParams.setPriceAdjustValue(40.0d);
            l_rsvIfoOrderUnitParams.setExpirationDateType("1");
            l_rsvIfoOrderUnitParams.setFirstOrderId(30L);

            TestDBUtility.deleteAll(RsvIfoOrderActionRow.TYPE);
            RsvIfoOrderActionParams l_rsvIfoOrderActionParams = TestDBUtility.getRsvIfoOrderActionRow();
            l_rsvIfoOrderActionParams.setOrderId(1002);
            l_rsvIfoOrderActionParams.setOrderActionSerialNo(2);
            TestDBUtility.insertWithDel(l_rsvIfoOrderActionParams);
            l_rsvIfoOrderActionParams.setOrderActionSerialNo(1);
            TestDBUtility.insertWithDel(l_rsvIfoOrderActionParams);

            TestDBUtility.deleteAll(ProductRow.TYPE);
            ProductParams l_productParams = TestDBUtility.getProductRow();
            l_productParams.setProductId(1001L);
            TestDBUtility.insertWithDel(l_productParams);

            TestDBUtility.deleteAll(IfoProductRow.TYPE);
            IfoProductParams l_ifoProductParams = TestDBUtility.getIfoProductRow();
            l_ifoProductParams.setProductId(1001L);
            l_ifoProductParams.setDerivativeType(IfoDerivativeTypeEnum.PUT_OPTIONS);
            TestDBUtility.insertWithDel(l_ifoProductParams);

            TestDBUtility.deleteAll(TradedProductRow.TYPE);
            TradedProductParams l_tradedProductParams = TestDBUtility.getTradedProductRow();
            l_tradedProductParams.setProductId(1001L);
            l_tradedProductParams.setMarketId(1006139070605L);
            l_tradedProductParams.setValidUntilBizDate(null);
            TestDBUtility.insertWithDel(l_tradedProductParams);

            TestDBUtility.deleteAll(IfoTradedProductRow.TYPE);
            IfoTradedProductParams l_ifoTradedProductParams = TestDBUtility.getIfoTradedProductRow();
            l_ifoTradedProductParams.setProductId(1001L);
            l_ifoTradedProductParams.setMarketId(1006139070605L);
            l_ifoTradedProductParams.setValidForBizDate(null);
            TestDBUtility.insertWithDel(l_ifoTradedProductParams);

            TestDBUtility.deleteAll(MarketRow.TYPE);
            MarketParams l_marketParams = TestDBUtility.getMarketRow();
            l_marketParams.setMarketId(1006139070605L);
            TestDBUtility.insertWithDel(l_marketParams);

            WEB3ToSuccIfoOrderUnitImpl l_impl = new WEB3ToSuccIfoOrderUnitImpl(l_rsvIfoOrderUnitParams);

            assertTrue(l_impl.isLimitOrder());
            assertFalse(l_impl.isMarketOrder());
            assertEquals(1001L, l_impl.getOrderUnitId());
            assertEquals(1002, l_impl.getOrderId());
            assertEquals(333812512203L, l_impl.getAccountId());
            assertEquals(33381251220301L, l_impl.getSubAccountId());
            assertEquals(33381L, l_impl.getBranchId());
            assertEquals(20L, l_impl.getTraderId());
            assertFalse(l_impl.isExpired());
            assertEquals(60000.0d, l_impl.getQuantity(), 0);
            assertEquals(30.0d, l_impl.getLimitPrice(), 0);

            RsvIfoOrderActionRow[] l_row = l_impl.getRsvIfoOrderActions();
            assertEquals(1002L, l_row[0].getOrderId());
            assertEquals(1, l_row[0].getOrderActionSerialNo());
            assertEquals(1002L, l_row[1].getOrderId());
            assertEquals(2, l_row[1].getOrderActionSerialNo());

            assertEquals(0, WEB3DateUtility.compareToDay(
                WEB3DateUtility.getDate("20080301", "yyyyMMdd"), l_impl.getReceivedTimestamp()));
            assertEquals(0, WEB3DateUtility.compareToDay(
                WEB3DateUtility.getDate("20080302", "yyyyMMdd"), l_impl.getExpirationTimestamp()));

            IfoProductRow l_productRow = (IfoProductRow)l_impl.getProduct().getDataSourceObject();
            assertEquals(1001L, l_productRow.getProductId());

            assertEquals(OrderOpenStatusEnum.OPEN, l_impl.getOrderOpenStatus());
            assertEquals(OrderTypeEnum.IDX_FUTURES_BUY_TO_OPEN, l_impl.getOrderType());
            assertEquals(OrderCategEnum.IDX_FUTURES_OPEN, l_impl.getOrderCateg());
            assertEquals(TaxTypeEnum.NORMAL, l_impl.getTaxType());
            assertEquals(SideEnum.BUY, l_impl.getSide());
            assertEquals(OrderStatusEnum.ACCEPTED, l_impl.getOrderStatus());
            assertEquals(OrderExpirationStatusEnum.OPEN, l_impl.getExpirationStatus());

            IfoTradedProductRow l_tradedProduct = (IfoTradedProductRow)l_impl.getTradedProduct().getDataSourceObject();
            assertEquals(1001L, l_tradedProduct.getProductId());
            assertEquals(1006139070605L, l_tradedProduct.getMarketId());

            assertTrue(l_impl.isFuturesOrder());
            assertFalse(l_impl.isOptionsOrder());
            assertFalse(l_impl.isCallOptionsOrder());
            assertFalse(l_impl.isPutOptionsOrder());
            assertTrue(l_impl.isOpenContractOrder());
            assertFalse(l_impl.isSettleContractOrder());
            assertTrue(l_impl.isBuyOrder());
            assertFalse(l_impl.isSellOrder());
            assertTrue(l_impl.isExecPriceOrder());
            assertEquals(ProductTypeEnum.IFO, l_impl.getProductType());
            assertEquals(1006139070605L, l_impl.getMarket().getMarketId());
            assertEquals("1", l_impl.getExpirationDateType());
            assertFalse(l_impl.isCarriedOrder());
            assertFalse(l_impl.isEveningSessionOrder());
            assertTrue(l_impl.isOrdered());
            assertTrue(l_impl.isOrderExecuted());
            assertTrue(l_impl.isCarryoverRsvOrderUnit());
            assertEquals("0", l_impl.getMsgOrderPriceDiv());
            assertNull(l_impl.getMsgLimitPrice());
            assertNull(l_impl.getMsgExpirationDate());
            assertEquals("1", l_impl.getMsgExecCondType());
            assertEquals("0", l_impl.getMsgOrderCondType());
            assertEquals("3", l_impl.getMsgTradingType());
        }
        catch (Exception l_ex)
        {
            log.info(STR_METHOD_NAME + ">>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>test failed !!");
            fail();
        }

        log.info(STR_METHOD_NAME + ">>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>test passed !!");
        log.exiting(TEST_END + STR_METHOD_NAME);
    }

    public void testGetSetMethod_C002()
    {
        final String STR_METHOD_NAME = " testGetSetMethod_C002";
        log.entering(TEST_START + STR_METHOD_NAME);

        try
        {
            TestDBUtility.deleteAll(RsvIfoOrderUnitRow.TYPE);
            RsvIfoOrderUnitParams l_rsvIfoOrderUnitParams = TestDBUtility.getRsvIfoOrderUnitRow();
            l_rsvIfoOrderUnitParams.setLimitPrice(0.0d);
            l_rsvIfoOrderUnitParams.setExpirationStatus(OrderExpirationStatusEnum.EXPIRED);
            l_rsvIfoOrderUnitParams.setFutureOptionDiv("2");
            l_rsvIfoOrderUnitParams.setProductId(1001L);
            l_rsvIfoOrderUnitParams.setOrderCateg(OrderCategEnum.IDX_OPTIONS_OPEN);
            l_rsvIfoOrderUnitParams.setOrderType(OrderTypeEnum.IDX_OPTIONS_SELL_TO_CLOSE);
            l_rsvIfoOrderUnitParams.setPriceAdjustValue(null);
            l_rsvIfoOrderUnitParams.setExpirationDateType("2");
            l_rsvIfoOrderUnitParams.setOrderUnitId(null);
            l_rsvIfoOrderUnitParams.setOrderErrorCode(null);
            l_rsvIfoOrderUnitParams.setFirstOrderId(null);
            l_rsvIfoOrderUnitParams.setExpirationDate(WEB3DateUtility.getDate("20080409", "yyyyMMdd"));

            TestDBUtility.deleteAll(ProductRow.TYPE);
            ProductParams l_productParams = TestDBUtility.getProductRow();
            l_productParams.setProductId(1001L);
            TestDBUtility.insertWithDel(l_productParams);

            TestDBUtility.deleteAll(IfoProductRow.TYPE);
            IfoProductParams l_ifoProductParams = TestDBUtility.getIfoProductRow();
            l_ifoProductParams.setProductId(1001L);
            l_ifoProductParams.setDerivativeType(IfoDerivativeTypeEnum.CALL_OPTIONS);
            TestDBUtility.insertWithDel(l_ifoProductParams);

            TestDBUtility.deleteAll(TradedProductRow.TYPE);
            TradedProductParams l_tradedProductParams = TestDBUtility.getTradedProductRow();
            l_tradedProductParams.setProductId(1001L);
            l_tradedProductParams.setMarketId(1006139070605L);
            l_tradedProductParams.setValidUntilBizDate(null);
            TestDBUtility.insertWithDel(l_tradedProductParams);

            TestDBUtility.deleteAll(IfoTradedProductRow.TYPE);
            IfoTradedProductParams l_ifoTradedProductParams = TestDBUtility.getIfoTradedProductRow();
            l_ifoTradedProductParams.setProductId(1001L);
            l_ifoTradedProductParams.setMarketId(1006139070605L);
            TestDBUtility.insertWithDel(l_ifoTradedProductParams);

            TestDBUtility.deleteAll(MarketRow.TYPE);
            MarketParams l_marketParams = TestDBUtility.getMarketRow();
            l_marketParams.setMarketId(1006139070605L);
            TestDBUtility.insertWithDel(l_marketParams);

            WEB3ToSuccIfoOrderUnitImpl l_impl = new WEB3ToSuccIfoOrderUnitImpl(l_rsvIfoOrderUnitParams);

            assertFalse(l_impl.isLimitOrder());
            assertTrue(l_impl.isMarketOrder());
            assertTrue(l_impl.isExpired());
            assertFalse(l_impl.isFuturesOrder());
            assertTrue(l_impl.isOptionsOrder());
            assertTrue(l_impl.isCallOptionsOrder());
            assertFalse(l_impl.isPutOptionsOrder());
            assertTrue(l_impl.isOpenContractOrder());
            assertFalse(l_impl.isBuyOrder());
            assertTrue(l_impl.isSellOrder());
            assertFalse(l_impl.isExecPriceOrder());
            assertTrue(l_impl.isCarriedOrder());
            assertFalse(l_impl.isOrdered());
            assertFalse(l_impl.isOrderExecuted());
            assertFalse(l_impl.isCarryoverRsvOrderUnit());
            assertEquals("0", l_impl.getMsgOrderPriceDiv());
            assertEquals(0, WEB3DateUtility.compareToDay(
                l_impl.getMsgExpirationDate(), WEB3DateUtility.getDate("20080409", "yyyyMMdd")));
            assertEquals("5", l_impl.getMsgTradingType());
        }
        catch (Exception l_ex)
        {
            log.info(STR_METHOD_NAME + ">>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>test failed !!");
            fail();
        }

        log.info(STR_METHOD_NAME + ">>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>test passed !!");
        log.exiting(TEST_END + STR_METHOD_NAME);
    }

    public void testGetSetMethod_C003()
    {
        final String STR_METHOD_NAME = " testGetSetMethod_C003";
        log.entering(TEST_START + STR_METHOD_NAME);

        try
        {
            TestDBUtility.deleteAll(RsvIfoOrderUnitRow.TYPE);
            RsvIfoOrderUnitParams l_rsvIfoOrderUnitParams = TestDBUtility.getRsvIfoOrderUnitRow();
            l_rsvIfoOrderUnitParams.setLimitPrice(null);
            l_rsvIfoOrderUnitParams.setFutureOptionDiv("2");
            l_rsvIfoOrderUnitParams.setProductId(1001L);
            l_rsvIfoOrderUnitParams.setOrderCateg(OrderCategEnum.OPEN_MARGIN);
            l_rsvIfoOrderUnitParams.setExpirationDateType("3");
            l_rsvIfoOrderUnitParams.setFirstOrderId(0);
            l_rsvIfoOrderUnitParams.setOrderType(OrderTypeEnum.IDX_FUTURES_SELL_TO_OPEN);

            TestDBUtility.deleteAll(ProductRow.TYPE);
            ProductParams l_productParams = TestDBUtility.getProductRow();
            l_productParams.setProductId(1001L);
            TestDBUtility.insertWithDel(l_productParams);

            TestDBUtility.deleteAll(IfoProductRow.TYPE);
            IfoProductParams l_ifoProductParams = TestDBUtility.getIfoProductRow();
            l_ifoProductParams.setProductId(1001L);
            l_ifoProductParams.setDerivativeType(IfoDerivativeTypeEnum.PUT_OPTIONS);
            TestDBUtility.insertWithDel(l_ifoProductParams);

            TestDBUtility.deleteAll(TradedProductRow.TYPE);
            TradedProductParams l_tradedProductParams = TestDBUtility.getTradedProductRow();
            l_tradedProductParams.setProductId(1001L);
            l_tradedProductParams.setMarketId(1006139070605L);
            l_tradedProductParams.setValidUntilBizDate(null);
            TestDBUtility.insertWithDel(l_tradedProductParams);

            TestDBUtility.deleteAll(IfoTradedProductRow.TYPE);
            IfoTradedProductParams l_ifoTradedProductParams = TestDBUtility.getIfoTradedProductRow();
            l_ifoTradedProductParams.setProductId(1001L);
            l_ifoTradedProductParams.setMarketId(1006139070605L);
            TestDBUtility.insertWithDel(l_ifoTradedProductParams);

            TestDBUtility.deleteAll(MarketRow.TYPE);
            MarketParams l_marketParams = TestDBUtility.getMarketRow();
            l_marketParams.setMarketId(1006139070605L);
            TestDBUtility.insertWithDel(l_marketParams);

            WEB3ToSuccIfoOrderUnitImpl l_impl = new WEB3ToSuccIfoOrderUnitImpl(l_rsvIfoOrderUnitParams);

            assertFalse(l_impl.isLimitOrder());
            assertTrue(l_impl.isMarketOrder());
            assertFalse(l_impl.isCallOptionsOrder());
            assertTrue(l_impl.isPutOptionsOrder());
            assertFalse(l_impl.isOpenContractOrder());
            assertTrue(l_impl.isEveningSessionOrder());
            assertFalse(l_impl.isCarryoverRsvOrderUnit());
            assertEquals("4", l_impl.getMsgTradingType());
        }
        catch (Exception l_ex)
        {
            log.info(STR_METHOD_NAME + ">>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>test failed !!");
            fail();
        }

        log.info(STR_METHOD_NAME + ">>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>test passed !!");
        log.exiting(TEST_END + STR_METHOD_NAME);
    }

    public void testGetSetMethod_C004()
    {
        final String STR_METHOD_NAME = " testGetSetMethod_C004";
        log.entering(TEST_START + STR_METHOD_NAME);

        try
        {
            TestDBUtility.deleteAll(RsvIfoOrderUnitRow.TYPE);
            RsvIfoOrderUnitParams l_rsvIfoOrderUnitParams = TestDBUtility.getRsvIfoOrderUnitRow();
            l_rsvIfoOrderUnitParams.setOrderCateg(OrderCategEnum.IDX_FUTURES_CLOSE);
            l_rsvIfoOrderUnitParams.setPriceAdjustValue(null);
            l_rsvIfoOrderUnitParams.setLimitPrice(2.0d);
            l_rsvIfoOrderUnitParams.setOrderType(OrderTypeEnum.IDX_FUTURES_SELL_TO_OPEN);

            WEB3ToSuccIfoOrderUnitImpl l_impl = new WEB3ToSuccIfoOrderUnitImpl(l_rsvIfoOrderUnitParams);

            assertTrue(l_impl.isSettleContractOrder());
            assertEquals("1", l_impl.getMsgOrderPriceDiv());
            assertEquals("2", l_impl.getMsgLimitPrice());
        }
        catch (Exception l_ex)
        {
            log.info(STR_METHOD_NAME + ">>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>test failed !!");
            fail();
        }

        log.info(STR_METHOD_NAME + ">>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>test passed !!");
        log.exiting(TEST_END + STR_METHOD_NAME);
    }

    public void testGetSetMethod_C005()
    {
        final String STR_METHOD_NAME = " testGetSetMethod_C005";
        log.entering(TEST_START + STR_METHOD_NAME);

        try
        {
            TestDBUtility.deleteAll(RsvIfoOrderUnitRow.TYPE);
            RsvIfoOrderUnitParams l_rsvIfoOrderUnitParams = TestDBUtility.getRsvIfoOrderUnitRow();
            l_rsvIfoOrderUnitParams.setOrderCateg(OrderCategEnum.IDX_OPTIONS_CLOSE);
            l_rsvIfoOrderUnitParams.setPriceAdjustValue(null);
            l_rsvIfoOrderUnitParams.setLimitPrice(null);
            l_rsvIfoOrderUnitParams.setOrderType(OrderTypeEnum.IDX_FUTURES_BUY_TO_CLOSE);

            WEB3ToSuccIfoOrderUnitImpl l_impl = new WEB3ToSuccIfoOrderUnitImpl(l_rsvIfoOrderUnitParams);

            assertTrue(l_impl.isSettleContractOrder());
            assertNull(l_impl.getMsgLimitPrice());
            assertEquals("6", l_impl.getMsgTradingType());
        }
        catch (Exception l_ex)
        {
            log.info(STR_METHOD_NAME + ">>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>test failed !!");
            fail();
        }

        log.info(STR_METHOD_NAME + ">>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>test passed !!");
        log.exiting(TEST_END + STR_METHOD_NAME);
    }

    public void testGetRsvOrderExecPrice_C001()
    {
        final String STR_METHOD_NAME = " testGetRsvOrderExecPrice_C001";
        log.entering(TEST_START + STR_METHOD_NAME);

        try
        {
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                "webbroker3.triggerorder.WEB3ToSuccOrderManagerImpl",
                "getReserveIfoOrderExecPrice",
                new Class[] {IfoOrderUnit.class, double.class, Double.class, WEB3IfoTradedProductImpl.class},
                new Double(5.0d));

            RsvIfoOrderUnitParams l_rsvIfoOrderUnitParams = new RsvIfoOrderUnitParams();

            WEB3ToSuccIfoOrderUnitImplForTest l_impl = new WEB3ToSuccIfoOrderUnitImplForTest(l_rsvIfoOrderUnitParams);
            l_impl.name = STR_METHOD_NAME; 

            assertEquals(5.0d, l_impl.getRsvOrderExecPrice(), 0);
        }
        catch (Exception l_ex)
        {
            log.info(STR_METHOD_NAME + ">>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>test failed !!");
            fail();
        }

        log.info(STR_METHOD_NAME + ">>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>test passed !!");
        log.exiting(TEST_END + STR_METHOD_NAME);
    }

    /**
     * get親注文の注文単位
     * 正常完了
     */
    public void testGetParentOrderUnit()
    {
        final String STR_METHOD_NAME = "testGetParentOrderUnit()";
        log.entering(TEST_START + STR_METHOD_NAME);
        
        try
        {
            TestDBUtility.deleteAll(RsvIfoOrderUnitRow.TYPE);
            RsvIfoOrderUnitParams l_rsvIfoOrderUnitParams = TestDBUtility.getRsvIfoOrderUnitRow();
            l_rsvIfoOrderUnitParams.setParentOrderId(1001);
            TestDBUtility.insertWithDel(l_rsvIfoOrderUnitParams);
            
            TestDBUtility.deleteAll(IfoOrderUnitRow.TYPE);
            IfoOrderUnitParams l_ifoOrderUnitParams = TestDBUtility.getIfoOrderUnitRow();
            l_ifoOrderUnitParams.setOrderId(1001);
            l_ifoOrderUnitParams.setLimitPrice(20);
            TestDBUtility.insertWithDel(l_ifoOrderUnitParams);
            
            WEB3ToSuccIfoOrderUnitImpl l_impl =
                new WEB3ToSuccIfoOrderUnitImpl(l_rsvIfoOrderUnitParams);
            
            IfoOrderUnit l_ifoOrderUnit = l_impl.getParentOrderUnit();
            assertEquals("20.0", "" + l_ifoOrderUnit.getLimitPrice());
        }
        catch (Exception l_ex)
        {
            log.error("", l_ex);
            log.exiting(TEST_END + STR_METHOD_NAME);
            fail();
        }
        
        log.exiting(TEST_END + STR_METHOD_NAME);
    }
    
    public void testIsReversingTrade()
    {
        final String STR_METHOD_NAME = "testIsReversingTrade()";
        log.entering(TEST_START + STR_METHOD_NAME);
        
        try
        {
            TestDBUtility.deleteAll(RsvIfoOrderUnitRow.TYPE);
            RsvIfoOrderUnitParams l_rsvIfoOrderUnitParams = TestDBUtility.getRsvIfoOrderUnitRow();
            l_rsvIfoOrderUnitParams.setParentOrderId(1001);
            l_rsvIfoOrderUnitParams.setReserveOrderTradingType("1");
            TestDBUtility.insertWithDel(l_rsvIfoOrderUnitParams);
            
            TestDBUtility.deleteAll(IfoOrderUnitRow.TYPE);
            IfoOrderUnitParams l_ifoOrderUnitParams = TestDBUtility.getIfoOrderUnitRow();
            l_ifoOrderUnitParams.setOrderId(1001);
            l_ifoOrderUnitParams.setLimitPrice(20);
            TestDBUtility.insertWithDel(l_ifoOrderUnitParams);
            
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                    "webbroker3.triggerorder.WEB3ToSuccOrderManagerImpl",
                    "isReversingTrade", new Class[]
                    {String.class, OrderUnit.class}, Boolean.TRUE);
            
            WEB3ToSuccIfoOrderUnitImpl l_impl =
                new WEB3ToSuccIfoOrderUnitImpl(l_rsvIfoOrderUnitParams);
            
            assertTrue(l_impl.isReversingTrade());
            
            //參數驗證
            WEB3MockObjectParamsValue l_paramsValue = MOCK_MANAGER.getMethodParamsValue(
                "webbroker3.triggerorder.WEB3ToSuccOrderManagerImpl",
                "isReversingTrade", new Class[]{String.class, OrderUnit.class});
            
            Object[] l_paramsValues = l_paramsValue.getFirstCalled();
            
            assertEquals("1", (String)l_paramsValues[0]);
            assertEquals("20.0", "" + ((IfoOrderUnit)l_paramsValues[1]).getLimitPrice());
        }
        catch (Exception l_ex)
        {
            log.error("", l_ex);
            log.exiting(TEST_END + STR_METHOD_NAME);
            fail();
        }
        
        log.exiting(TEST_END + STR_METHOD_NAME);
    }
    
    /**
     * validate訂正可能状態
     * this.先物OP予約注文単位Row.注文有効状態≠"オープン"の場合
     */
    public void testValidateOrderForChangeability_C001()
    {
        final String STR_METHOD_NAME = "testValidateOrderForChangeability_C001()";
        log.entering(TEST_START + STR_METHOD_NAME);
        
        try
        {
            TestDBUtility.deleteAll(RsvIfoOrderUnitRow.TYPE);
            RsvIfoOrderUnitParams l_rsvIfoOrderUnitParams = TestDBUtility.getRsvIfoOrderUnitRow();
            l_rsvIfoOrderUnitParams.setParentOrderId(1001);
            l_rsvIfoOrderUnitParams.setReserveOrderTradingType("1");
            l_rsvIfoOrderUnitParams.setOrderOpenStatus(OrderOpenStatusEnum.UNDEFINED);
            TestDBUtility.insertWithDel(l_rsvIfoOrderUnitParams);
            
            WEB3ToSuccIfoOrderUnitImpl l_impl =
                new WEB3ToSuccIfoOrderUnitImpl(l_rsvIfoOrderUnitParams);
            
            l_impl.validateOrderForChangeability();
            fail();
        }
        catch (WEB3BaseException l_ex)
        {
            assertEquals(WEB3ErrorCatalog.BUSINESS_ERROR_02287, l_ex.getErrorInfo());
        }
        catch (Exception l_ex)
        {
            log.error("", l_ex);
            log.exiting(TEST_END + STR_METHOD_NAME);
            fail();
        }
        
        log.exiting(TEST_END + STR_METHOD_NAME);
    }
    
    /**
     * validate訂正可能状態
     * 訂正時間帯チェック が異常の場合
     */
    public void testValidateOrderForChangeability_C002()
    {
        final String STR_METHOD_NAME = "testValidateOrderForChangeability_C002()";
        log.entering(TEST_START + STR_METHOD_NAME);
        
        try
        {
            TestDBUtility.deleteAll(RsvIfoOrderUnitRow.TYPE);
            RsvIfoOrderUnitParams l_rsvIfoOrderUnitParams = TestDBUtility.getRsvIfoOrderUnitRow();
            l_rsvIfoOrderUnitParams.setParentOrderId(1001);
            l_rsvIfoOrderUnitParams.setReserveOrderTradingType("1");
            l_rsvIfoOrderUnitParams.setOrderOpenStatus(OrderOpenStatusEnum.OPEN);
            TestDBUtility.insertWithDel(l_rsvIfoOrderUnitParams);
            
            TestDBUtility.deleteAll(OrderexecutionEndRow.TYPE);
            
            
            TestDBUtility.deleteAll(TradingTimeRow.TYPE);
            TradingTimeParams l_tradingTimeParams = TestDBUtility.getTradingTimeRow();
            l_tradingTimeParams.setInstitutionCode("0D");
            l_tradingTimeParams.setBranchCode("123");
            l_tradingTimeParams.setMarketCode("N1");
            l_tradingTimeParams.setTradingTimeType("01");
            l_tradingTimeParams.setProductCode("0");
            l_tradingTimeParams.setBizDateType("1");
            TestDBUtility.insertWithDel(l_tradingTimeParams);
            
            TradingTimeParams l_tradingTimeParams1 = TestDBUtility.getTradingTimeRow();
            l_tradingTimeParams1.setInstitutionCode("0D");
            l_tradingTimeParams1.setBranchCode("123");
            l_tradingTimeParams1.setMarketCode("N1");
            l_tradingTimeParams1.setTradingTimeType("01");
            l_tradingTimeParams1.setProductCode("0");
            l_tradingTimeParams1.setBizDateType("2");
            TestDBUtility.insertWithDel(l_tradingTimeParams1);
            
            
            TradingTimeParams l_tradingTimeParams2 = TestDBUtility.getTradingTimeRow();
            l_tradingTimeParams2.setInstitutionCode("0D");
            l_tradingTimeParams2.setBranchCode("123");
            l_tradingTimeParams2.setMarketCode("N1");
            l_tradingTimeParams2.setTradingTimeType("01");
            l_tradingTimeParams2.setProductCode("0");
            l_tradingTimeParams2.setBizDateType("3");
            TestDBUtility.insertWithDel(l_tradingTimeParams2);
            
            TradingTimeParams l_tradingTimeParams3 = TestDBUtility.getTradingTimeRow();
            l_tradingTimeParams3.setInstitutionCode("0D");
            l_tradingTimeParams3.setBranchCode("123");
            l_tradingTimeParams3.setMarketCode("N1");
            l_tradingTimeParams3.setTradingTimeType("01");
            l_tradingTimeParams3.setProductCode("0");
            l_tradingTimeParams3.setBizDateType("4");
            TestDBUtility.insertWithDel(l_tradingTimeParams3);
            
            WEB3ToSuccIfoOrderUnitImpl l_impl =
                new WEB3ToSuccIfoOrderUnitImpl(l_rsvIfoOrderUnitParams);
            
            l_impl.validateOrderForChangeability();
            fail();
        }
        catch (WEB3BaseException l_ex)
        {
            assertEquals(WEB3ErrorCatalog.BUSINESS_ERROR_00812, l_ex.getErrorInfo());
        }
        catch (Exception l_ex)
        {
            log.error("", l_ex);
            log.exiting(TEST_END + STR_METHOD_NAME);
            fail();
        }
        
        log.exiting(TEST_END + STR_METHOD_NAME);
    }
    
    /**
     * validate訂正可能状態
     * 連続注文マネージャImpl.validateトリガー注文設定To親注文 が異常の場合
     */
    public void testValidateOrderForChangeability_C003()
    {
        final String STR_METHOD_NAME = "testValidateOrderForChangeability_C003()";
        log.entering(TEST_START + STR_METHOD_NAME);
        
        try
        {
            TestDBUtility.deleteAll(RsvIfoOrderUnitRow.TYPE);
            RsvIfoOrderUnitParams l_rsvIfoOrderUnitParams = TestDBUtility.getRsvIfoOrderUnitRow();
            l_rsvIfoOrderUnitParams.setParentOrderId(1001);
            l_rsvIfoOrderUnitParams.setReserveOrderTradingType("1");
            l_rsvIfoOrderUnitParams.setOrderOpenStatus(OrderOpenStatusEnum.OPEN);
            l_rsvIfoOrderUnitParams.setFutureOptionDiv("1");
            TestDBUtility.insertWithDel(l_rsvIfoOrderUnitParams);
            
            TestDBUtility.deleteAll(OrderexecutionEndRow.TYPE);
            OrderexecutionEndParams l_orderexecutionEndParams = TestDBUtility.getOrderexecutionEndRow();
            l_orderexecutionEndParams.setInstitutionCode("0D");
            l_orderexecutionEndParams.setProductType(ProductTypeEnum.IFO);
            l_orderexecutionEndParams.setOrderexecutionEndType("0");
            l_orderexecutionEndParams.setFutureOptionDiv("1");
            TestDBUtility.insertWithDel(l_orderexecutionEndParams);
            
            OrderexecutionEndParams l_orderexecutionEndParams1 = TestDBUtility.getOrderexecutionEndRow();
            l_orderexecutionEndParams1.setInstitutionCode("0D");
            l_orderexecutionEndParams1.setProductType(ProductTypeEnum.IFO);
            l_orderexecutionEndParams1.setOrderexecutionEndType("1");
            l_orderexecutionEndParams1.setFutureOptionDiv("1");
            TestDBUtility.insertWithDel(l_orderexecutionEndParams1);
            
            TestDBUtility.deleteAll(IfoOrderUnitRow.TYPE);
            IfoOrderUnitParams l_ifoOrderUnitParams = TestDBUtility.getIfoOrderUnitRow();
            l_ifoOrderUnitParams.setOrderId(1001);
            l_ifoOrderUnitParams.setLimitPrice(20);
            l_ifoOrderUnitParams.setOrderStatus(OrderStatusEnum.CANCEL_ACCEPTED);
            TestDBUtility.insertWithDel(l_ifoOrderUnitParams);
            
            WEB3ToSuccIfoOrderUnitImpl l_impl =
                new WEB3ToSuccIfoOrderUnitImpl(l_rsvIfoOrderUnitParams);

            l_impl.validateOrderForChangeability();
            fail();
        }
        catch (WEB3BaseException l_ex)
        {
            assertEquals(WEB3ErrorCatalog.BUSINESS_ERROR_02343, l_ex.getErrorInfo());
        }
        catch (Exception l_ex)
        {
            log.error("", l_ex);
            log.exiting(TEST_END + STR_METHOD_NAME);
            fail();
        }
        
        log.exiting(TEST_END + STR_METHOD_NAME);
    }
    
    /**
     * validate訂正可能状態
     * 正常完了の場合
     */
    public void testValidateOrderForChangeability_C004()
    {
        final String STR_METHOD_NAME = "testValidateOrderForChangeability_C004()";
        log.entering(TEST_START + STR_METHOD_NAME);
        
        try
        {
            TestDBUtility.deleteAll(RsvIfoOrderUnitRow.TYPE);
            RsvIfoOrderUnitParams l_rsvIfoOrderUnitParams = TestDBUtility.getRsvIfoOrderUnitRow();
            l_rsvIfoOrderUnitParams.setParentOrderId(1001);
            l_rsvIfoOrderUnitParams.setReserveOrderTradingType("1");
            l_rsvIfoOrderUnitParams.setOrderOpenStatus(OrderOpenStatusEnum.OPEN);
            l_rsvIfoOrderUnitParams.setFutureOptionDiv("1");
            TestDBUtility.insertWithDel(l_rsvIfoOrderUnitParams);
            
            TestDBUtility.deleteAll(OrderexecutionEndRow.TYPE);
            OrderexecutionEndParams l_orderexecutionEndParams = TestDBUtility.getOrderexecutionEndRow();
            l_orderexecutionEndParams.setInstitutionCode("0D");
            l_orderexecutionEndParams.setProductType(ProductTypeEnum.IFO);
            l_orderexecutionEndParams.setOrderexecutionEndType("0");
            l_orderexecutionEndParams.setFutureOptionDiv("1");
            TestDBUtility.insertWithDel(l_orderexecutionEndParams);
            
            OrderexecutionEndParams l_orderexecutionEndParams1 = TestDBUtility.getOrderexecutionEndRow();
            l_orderexecutionEndParams1.setInstitutionCode("0D");
            l_orderexecutionEndParams1.setProductType(ProductTypeEnum.IFO);
            l_orderexecutionEndParams1.setOrderexecutionEndType("1");
            l_orderexecutionEndParams1.setFutureOptionDiv("1");
            TestDBUtility.insertWithDel(l_orderexecutionEndParams1);
            
            TestDBUtility.deleteAll(IfoOrderUnitRow.TYPE);
            IfoOrderUnitParams l_ifoOrderUnitParams = TestDBUtility.getIfoOrderUnitRow();
            l_ifoOrderUnitParams.setOrderId(1001);
            l_ifoOrderUnitParams.setLimitPrice(20);
            TestDBUtility.insertWithDel(l_ifoOrderUnitParams);
            
            WEB3ToSuccIfoOrderUnitImpl l_impl =
                new WEB3ToSuccIfoOrderUnitImpl(l_rsvIfoOrderUnitParams);

            l_impl.validateOrderForChangeability();
        }
        catch (Exception l_ex)
        {
            log.error("", l_ex);
            log.exiting(TEST_END + STR_METHOD_NAME);
            fail();
        }
        
        log.exiting(TEST_END + STR_METHOD_NAME);
    }
    
    /**
     * validate取消可能状態
     * this.先物OP予約注文単位Row.注文有効状態≠"オープン"の場合
     */
    public void testValidateOrderForCancellation_C001()
    {
        final String STR_METHOD_NAME = "testValidateOrderForCancellation_C001()";
        log.entering(TEST_START + STR_METHOD_NAME);
        
        try
        {
            TestDBUtility.deleteAll(RsvIfoOrderUnitRow.TYPE);
            RsvIfoOrderUnitParams l_rsvIfoOrderUnitParams = TestDBUtility.getRsvIfoOrderUnitRow();
            l_rsvIfoOrderUnitParams.setParentOrderId(1001);
            l_rsvIfoOrderUnitParams.setReserveOrderTradingType("1");
            l_rsvIfoOrderUnitParams.setOrderOpenStatus(OrderOpenStatusEnum.UNDEFINED);
            TestDBUtility.insertWithDel(l_rsvIfoOrderUnitParams);
            
            WEB3ToSuccIfoOrderUnitImpl l_impl =
                new WEB3ToSuccIfoOrderUnitImpl(l_rsvIfoOrderUnitParams);
            
            l_impl.validateOrderForCancellation();
            fail();
        }
        catch (WEB3BaseException l_ex)
        {
            assertEquals(WEB3ErrorCatalog.BUSINESS_ERROR_02287, l_ex.getErrorInfo());
        }
        catch (Exception l_ex)
        {
            log.error("", l_ex);
            log.exiting(TEST_END + STR_METHOD_NAME);
            fail();
        }
        
        log.exiting(TEST_END + STR_METHOD_NAME);
    }
    
    /**
     * validate取消可能状態
     * 取消時間帯チェック が異常の場合
     */
    public void testValidateOrderForCancellation_C002()
    {
        final String STR_METHOD_NAME = "testValidateOrderForCancellation_C002()";
        log.entering(TEST_START + STR_METHOD_NAME);
        
        try
        {
            TestDBUtility.deleteAll(RsvIfoOrderUnitRow.TYPE);
            RsvIfoOrderUnitParams l_rsvIfoOrderUnitParams = TestDBUtility.getRsvIfoOrderUnitRow();
            l_rsvIfoOrderUnitParams.setParentOrderId(1001);
            l_rsvIfoOrderUnitParams.setReserveOrderTradingType("1");
            l_rsvIfoOrderUnitParams.setOrderOpenStatus(OrderOpenStatusEnum.OPEN);
            TestDBUtility.insertWithDel(l_rsvIfoOrderUnitParams);
            
            TestDBUtility.deleteAll(OrderexecutionEndRow.TYPE);
            
            WEB3ToSuccIfoOrderUnitImpl l_impl =
                new WEB3ToSuccIfoOrderUnitImpl(l_rsvIfoOrderUnitParams);
            
            l_impl.validateOrderForCancellation();
            fail();
        }
        catch (WEB3BaseException l_ex)
        {
            assertEquals(WEB3ErrorCatalog.BUSINESS_ERROR_00812, l_ex.getErrorInfo());
        }
        catch (Exception l_ex)
        {
            log.error("", l_ex);
            log.exiting(TEST_END + STR_METHOD_NAME);
            fail();
        }
        
        log.exiting(TEST_END + STR_METHOD_NAME);
    }
    
    /**
     * validate取消可能状態
     * 正常完了の場合
     */
    public void testValidateOrderForCancellation_C003()
    {
        final String STR_METHOD_NAME = "testValidateOrderForCancellation_C003()";
        log.entering(TEST_START + STR_METHOD_NAME);
        
        try
        {
            TestDBUtility.deleteAll(RsvIfoOrderUnitRow.TYPE);
            RsvIfoOrderUnitParams l_rsvIfoOrderUnitParams = TestDBUtility.getRsvIfoOrderUnitRow();
            l_rsvIfoOrderUnitParams.setParentOrderId(1001);
            l_rsvIfoOrderUnitParams.setReserveOrderTradingType("1");
            l_rsvIfoOrderUnitParams.setOrderOpenStatus(OrderOpenStatusEnum.OPEN);
            l_rsvIfoOrderUnitParams.setFutureOptionDiv("1");
            TestDBUtility.insertWithDel(l_rsvIfoOrderUnitParams);
            
            TestDBUtility.deleteAll(OrderexecutionEndRow.TYPE);
            OrderexecutionEndParams l_orderexecutionEndParams = TestDBUtility.getOrderexecutionEndRow();
            l_orderexecutionEndParams.setInstitutionCode("0D");
            l_orderexecutionEndParams.setProductType(ProductTypeEnum.IFO);
            l_orderexecutionEndParams.setOrderexecutionEndType("0");
            l_orderexecutionEndParams.setFutureOptionDiv("1");
            TestDBUtility.insertWithDel(l_orderexecutionEndParams);
            
            OrderexecutionEndParams l_orderexecutionEndParams1 = TestDBUtility.getOrderexecutionEndRow();
            l_orderexecutionEndParams1.setInstitutionCode("0D");
            l_orderexecutionEndParams1.setProductType(ProductTypeEnum.IFO);
            l_orderexecutionEndParams1.setOrderexecutionEndType("1");
            l_orderexecutionEndParams1.setFutureOptionDiv("1");
            TestDBUtility.insertWithDel(l_orderexecutionEndParams1);
            
            WEB3ToSuccIfoOrderUnitImpl l_impl =
                new WEB3ToSuccIfoOrderUnitImpl(l_rsvIfoOrderUnitParams);
            
            l_impl.validateOrderForCancellation();
        }
        catch (Exception l_ex)
        {
            log.error("", l_ex);
            log.exiting(TEST_END + STR_METHOD_NAME);
            fail();
        }
        
        log.exiting(TEST_END + STR_METHOD_NAME);
    }
    
    /**
     * create連続注文共通情報
     * 正常終了
     */
    public void testCreateSuccCommonInfo()
    {
        final String STR_METHOD_NAME = "testCreateSuccCommonInfo()";
        log.entering(TEST_START + STR_METHOD_NAME);
        
        try
        {
            TestDBUtility.deleteAll(RsvIfoOrderUnitRow.TYPE);
            RsvIfoOrderUnitParams l_rsvIfoOrderUnitParams = TestDBUtility.getRsvIfoOrderUnitRow();
            l_rsvIfoOrderUnitParams.setParentOrderId(1001);
            l_rsvIfoOrderUnitParams.setReserveOrderTradingType("1");
            l_rsvIfoOrderUnitParams.setOrderOpenStatus(OrderOpenStatusEnum.UNDEFINED);
            TestDBUtility.insertWithDel(l_rsvIfoOrderUnitParams);
            
            WEB3ToSuccIfoOrderUnitImpl l_impl =
                new WEB3ToSuccIfoOrderUnitImpl(l_rsvIfoOrderUnitParams);
            
            WEB3SuccCommonInfo l_succCommonInfo = l_impl.createSuccCommonInfo();
            assertEquals("1001", l_succCommonInfo.parentOrderId);
            assertEquals("1", l_succCommonInfo.succTradingType);
        }
        catch (Exception l_ex)
        {
            log.error("", l_ex);
            log.exiting(TEST_END + STR_METHOD_NAME);
            fail();
        }
        
        log.exiting(TEST_END + STR_METHOD_NAME);
    }
    
    /**
     * create単価調整値情報
     * this.is±指値指定()==falseの場合
     */
    public void testCreateSuccPriceAdjustmentValueInfo_C001()
    {
        final String STR_METHOD_NAME = "testCreateSuccPriceAdjustmentValueInfo_C001()";
        log.entering(TEST_START + STR_METHOD_NAME);
        
        try
        {
            TestDBUtility.deleteAll(RsvIfoOrderUnitRow.TYPE);
            RsvIfoOrderUnitParams l_rsvIfoOrderUnitParams = TestDBUtility.getRsvIfoOrderUnitRow();
            l_rsvIfoOrderUnitParams.setParentOrderId(1001);
            l_rsvIfoOrderUnitParams.setReserveOrderTradingType("1");
            l_rsvIfoOrderUnitParams.setOrderOpenStatus(OrderOpenStatusEnum.UNDEFINED);
            l_rsvIfoOrderUnitParams.setPriceAdjustValue(null);
            TestDBUtility.insertWithDel(l_rsvIfoOrderUnitParams);
            
            WEB3ToSuccIfoOrderUnitImpl l_impl =
                new WEB3ToSuccIfoOrderUnitImpl(l_rsvIfoOrderUnitParams);

            assertNull(l_impl.createSuccPriceAdjustmentValueInfo());
        }
        catch (Exception l_ex)
        {
            log.error("", l_ex);
            log.exiting(TEST_END + STR_METHOD_NAME);
            fail();
        }
        
        log.exiting(TEST_END + STR_METHOD_NAME);
    }
    
    /**
     * create単価調整値情報
     * this.is±指値指定()==trueの場合
     * 且つマイナス値の場合
     */
    public void testCreateSuccPriceAdjustmentValueInfo_C002()
    {
        final String STR_METHOD_NAME = "testCreateSuccPriceAdjustmentValueInfo_C002()";
        log.entering(TEST_START + STR_METHOD_NAME);
        
        try
        {
            TestDBUtility.deleteAll(RsvIfoOrderUnitRow.TYPE);
            RsvIfoOrderUnitParams l_rsvIfoOrderUnitParams = TestDBUtility.getRsvIfoOrderUnitRow();
            l_rsvIfoOrderUnitParams.setParentOrderId(1001);
            l_rsvIfoOrderUnitParams.setReserveOrderTradingType("1");
            l_rsvIfoOrderUnitParams.setOrderOpenStatus(OrderOpenStatusEnum.UNDEFINED);
            l_rsvIfoOrderUnitParams.setPriceAdjustValue(-20);
            TestDBUtility.insertWithDel(l_rsvIfoOrderUnitParams);
            
            WEB3ToSuccIfoOrderUnitImpl l_impl =
                new WEB3ToSuccIfoOrderUnitImpl(l_rsvIfoOrderUnitParams);
            
            WEB3SuccPriceAdjustmentValueInfo l_succPriceAdjustmentValueInfo =
                l_impl.createSuccPriceAdjustmentValueInfo();

            assertEquals("-", l_succPriceAdjustmentValueInfo.sign);
            assertEquals("20", l_succPriceAdjustmentValueInfo.priceAdjustmentValue);
        }
        catch (Exception l_ex)
        {
            log.error("", l_ex);
            log.exiting(TEST_END + STR_METHOD_NAME);
            fail();
        }
        
        log.exiting(TEST_END + STR_METHOD_NAME);
    }
    
    /**
     * create単価調整値情報
     * this.is±指値指定()==trueの場合
     * 0以上の場合
     */
    public void testCreateSuccPriceAdjustmentValueInfo_C003()
    {
        final String STR_METHOD_NAME = "testCreateSuccPriceAdjustmentValueInfo_C003()";
        log.entering(TEST_START + STR_METHOD_NAME);
        
        try
        {
            TestDBUtility.deleteAll(RsvIfoOrderUnitRow.TYPE);
            RsvIfoOrderUnitParams l_rsvIfoOrderUnitParams = TestDBUtility.getRsvIfoOrderUnitRow();
            l_rsvIfoOrderUnitParams.setParentOrderId(1001);
            l_rsvIfoOrderUnitParams.setReserveOrderTradingType("1");
            l_rsvIfoOrderUnitParams.setOrderOpenStatus(OrderOpenStatusEnum.UNDEFINED);
            l_rsvIfoOrderUnitParams.setPriceAdjustValue(20);
            TestDBUtility.insertWithDel(l_rsvIfoOrderUnitParams);
            
            WEB3ToSuccIfoOrderUnitImpl l_impl =
                new WEB3ToSuccIfoOrderUnitImpl(l_rsvIfoOrderUnitParams);
            
            WEB3SuccPriceAdjustmentValueInfo l_succPriceAdjustmentValueInfo =
                l_impl.createSuccPriceAdjustmentValueInfo();

            assertEquals("+", l_succPriceAdjustmentValueInfo.sign);
            assertEquals("20", l_succPriceAdjustmentValueInfo.priceAdjustmentValue);
        }
        catch (Exception l_ex)
        {
            log.error("", l_ex);
            log.exiting(TEST_END + STR_METHOD_NAME);
            fail();
        }
        
        log.exiting(TEST_END + STR_METHOD_NAME);
    }

    public void testGetContractsToClose_C001()
    {
        final String STR_METHOD_NAME = " testGetContractsToClose_C001";
        log.entering(TEST_START + STR_METHOD_NAME);

        try
        {
            RsvIfoOrderUnitParams l_rsvIfoOrderUnitParams = TestDBUtility.getRsvIfoOrderUnitRow();
            l_rsvIfoOrderUnitParams.setOrderCateg(OrderCategEnum.ASSET);

            WEB3ToSuccIfoOrderUnitImpl l_impl = new WEB3ToSuccIfoOrderUnitImpl(l_rsvIfoOrderUnitParams);
            l_impl.getContractsToClose();

            fail();
        }
        catch (WEB3BusinessLayerException l_ex)
        {
            assertEquals(l_ex.getErrorInfo().getErrorTag(), "BUSINESS_ERROR_00653");
        }
        catch (Exception l_ex)
        {
            log.info(STR_METHOD_NAME + ">>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>test failed !!");
            fail();
        }

        log.info(STR_METHOD_NAME + ">>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>test passed !!");
        log.exiting(TEST_END + STR_METHOD_NAME);
    }

    public void testGetContractsToClose_C002()
    {
        final String STR_METHOD_NAME = " testGetContractsToClose_C002";
        log.entering(TEST_START + STR_METHOD_NAME);

        try
        {
            TestDBUtility.deleteAll(RsvIfoClosingContractSpecRow.TYPE);

            RsvIfoOrderUnitParams l_rsvIfoOrderUnitParams = TestDBUtility.getRsvIfoOrderUnitRow();
            l_rsvIfoOrderUnitParams.setOrderCateg(OrderCategEnum.IDX_FUTURES_CLOSE);

            WEB3ToSuccIfoOrderUnitImplForTest l_impl = new WEB3ToSuccIfoOrderUnitImplForTest(l_rsvIfoOrderUnitParams);
            l_impl.name = STR_METHOD_NAME;

            assertNull(l_impl.getContractsToClose());
        }
        catch (Exception l_ex)
        {
            log.info(STR_METHOD_NAME + ">>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>test failed !!");
            fail();
        }

        log.info(STR_METHOD_NAME + ">>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>test passed !!");
        log.exiting(TEST_END + STR_METHOD_NAME);
    }

    public void testGetContractsToClose_C003()
    {
        final String STR_METHOD_NAME = " testGetContractsToClose_C003";
        log.entering(TEST_START + STR_METHOD_NAME);

        try
        {
            TestDBUtility.deleteAll(RsvIfoClosingContractSpecRow.TYPE);
            RsvIfoClosingContractSpecParams l_rsvIfoClosingContractSpecParams = TestDBUtility.getRsvIfoClosingContractSpecRow();
            TestDBUtility.insertWithDel(l_rsvIfoClosingContractSpecParams);
            l_rsvIfoClosingContractSpecParams.setContractId(1002);
            l_rsvIfoClosingContractSpecParams.setClosingSerialNo(2);
            TestDBUtility.insertWithDel(l_rsvIfoClosingContractSpecParams);

            RsvIfoOrderUnitParams l_rsvIfoOrderUnitParams = TestDBUtility.getRsvIfoOrderUnitRow();
            l_rsvIfoOrderUnitParams.setOrderCateg(OrderCategEnum.IDX_OPTIONS_CLOSE);
            l_rsvIfoOrderUnitParams.setOrderId(123456789L);

            WEB3ToSuccIfoOrderUnitImplForTest l_impl = new WEB3ToSuccIfoOrderUnitImplForTest(l_rsvIfoOrderUnitParams);
            l_impl.name = STR_METHOD_NAME;
            RsvIfoClosingContractSpecRow[] l_rsvIfoClosingContractSpecRows = l_impl.getContractsToClose();

            assertEquals(1001L, l_rsvIfoClosingContractSpecRows[0].getContractId());
            assertEquals(1L, l_rsvIfoClosingContractSpecRows[0].getClosingSerialNo());
            assertEquals(1002L, l_rsvIfoClosingContractSpecRows[1].getContractId());
            assertEquals(2L, l_rsvIfoClosingContractSpecRows[1].getClosingSerialNo());
        }
        catch (Exception l_ex)
        {
            log.info(STR_METHOD_NAME + ">>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>test failed !!");
            fail();
        }

        log.info(STR_METHOD_NAME + ">>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>test passed !!");
        log.exiting(TEST_END + STR_METHOD_NAME);
    }

    public void testGetContractsToClose_C004()
    {
        final String STR_METHOD_NAME = " testGetContractsToClose_C004";
        log.entering(TEST_START + STR_METHOD_NAME);

        try
        {
            RsvIfoOrderUnitParams l_rsvIfoOrderUnitParams = TestDBUtility.getRsvIfoOrderUnitRow();
            l_rsvIfoOrderUnitParams.setOrderCateg(OrderCategEnum.IDX_FUTURES_CLOSE);
            l_rsvIfoOrderUnitParams.setAccountId(1001L);
            l_rsvIfoOrderUnitParams.setSubAccountId(1002L);
            l_rsvIfoOrderUnitParams.setOrderId(1003L);
            l_rsvIfoOrderUnitParams.setQuantity(5.0d);
            l_rsvIfoOrderUnitParams.setCreatedTimestamp(WEB3DateUtility.getDate("20080408", "yyyyMMdd"));
            l_rsvIfoOrderUnitParams.setLastUpdatedTimestamp(WEB3DateUtility.getDate("20080409", "yyyyMMdd"));

            WEB3ToSuccIfoOrderUnitImplForTest l_impl = new WEB3ToSuccIfoOrderUnitImplForTest(l_rsvIfoOrderUnitParams);
            l_impl.name = STR_METHOD_NAME;
            RsvIfoClosingContractSpecRow[] l_rsvIfoClosingContractSpecRows = l_impl.getContractsToClose();

            assertEquals(1001L, l_rsvIfoClosingContractSpecRows[0].getAccountId());
            assertEquals(1002L, l_rsvIfoClosingContractSpecRows[0].getSubAccountId());
            assertEquals(1003L, l_rsvIfoClosingContractSpecRows[0].getOrderId());
            assertEquals(0L, l_rsvIfoClosingContractSpecRows[0].getContractId());
            assertEquals(1L, l_rsvIfoClosingContractSpecRows[0].getClosingSerialNo());
            assertEquals(5.0d, l_rsvIfoClosingContractSpecRows[0].getQuantity(), 0);
            assertEquals(0, WEB3DateUtility.compareToDay(l_rsvIfoClosingContractSpecRows[0].getCreatedTimestamp(),
                WEB3DateUtility.getDate("20080408", "yyyyMMdd")));
            assertEquals(0, WEB3DateUtility.compareToDay(l_rsvIfoClosingContractSpecRows[0].getLastUpdatedTimestamp(),
                WEB3DateUtility.getDate("20080409", "yyyyMMdd")));
        }
        catch (Exception l_ex)
        {
            log.info(STR_METHOD_NAME + ">>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>test failed !!");
            fail();
        }

        log.info(STR_METHOD_NAME + ">>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>test passed !!");
        log.exiting(TEST_END + STR_METHOD_NAME);
    }

    public void testGetContractsToClose_C005()
    {
        final String STR_METHOD_NAME = " testGetContractsToClose_C005";
        log.entering(TEST_START + STR_METHOD_NAME);

        try
        {
            List l_lisIfoContractRows = new ArrayList();
            IfoContractParams l_ifoContractParams1 = TestDBUtility.getIfoContractRow();
            l_ifoContractParams1.setQuantity(9.0);
            l_lisIfoContractRows.add(l_ifoContractParams1);
            IfoContractParams l_ifoContractParams2 = TestDBUtility.getIfoContractRow();
            l_ifoContractParams2.setContractId(1002L);
            l_ifoContractParams2.setContractPrice(3710.0);
            l_ifoContractParams2.setQuantity(7.0);
            l_lisIfoContractRows.add(l_ifoContractParams2);

            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                "webbroker3.ifo.WEB3IfoPositionManagerImpl",
                "getContractListByOrderUnit",
                new Class[] {long.class},
                l_lisIfoContractRows);

            List l_lisIfoFinTransactionRows = new ArrayList();
            IfoFinTransactionParams l_ifoFinTransactionParams = TestDBUtility.getIfoFinTransactionRow();
            l_lisIfoFinTransactionRows.add(l_ifoFinTransactionParams);

            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                "webbroker3.ifo.WEB3IfoPositionManagerImpl", 
                "getTransactionsListByOrderUnitPlusContract",
                new Class[]
                {long.class, long.class},
                l_lisIfoFinTransactionRows);

            RsvIfoOrderUnitParams l_rsvIfoOrderUnitParams = TestDBUtility.getRsvIfoOrderUnitRow();
            l_rsvIfoOrderUnitParams.setOrderCateg(OrderCategEnum.IDX_FUTURES_CLOSE);
            l_rsvIfoOrderUnitParams.setClosingOrder(WEB3ClosingOrderDef.RANDOM);
            l_rsvIfoOrderUnitParams.setOrderType(OrderTypeEnum.IDX_FUTURES_SELL_TO_CLOSE);
            l_rsvIfoOrderUnitParams.setAccountId(1001L);
            l_rsvIfoOrderUnitParams.setSubAccountId(1002L);
            l_rsvIfoOrderUnitParams.setOrderId(1003L);
            l_rsvIfoOrderUnitParams.setQuantity(5.0d);
            l_rsvIfoOrderUnitParams.setCreatedTimestamp(WEB3DateUtility.getDate("20080408", "yyyyMMdd"));
            l_rsvIfoOrderUnitParams.setLastUpdatedTimestamp(WEB3DateUtility.getDate("20080409", "yyyyMMdd"));
            l_rsvIfoOrderUnitParams.setQuantity(8.0d);

            TestDBUtility.deleteAll(IfoLockedContractDetailsRow.TYPE);
            IfoLockedContractDetailsParams l_ifoLockedContractDetailsParams =
                TestDBUtility.getIfoLockedContractDetailsRow();
            l_ifoLockedContractDetailsParams.setContractId(1002L);
            l_ifoLockedContractDetailsParams.setLockedQuantity(2);
            TestDBUtility.insertWithDel(l_ifoLockedContractDetailsParams);
            l_ifoLockedContractDetailsParams.setContractId(1001L);
            TestDBUtility.insertWithDel(l_ifoLockedContractDetailsParams);

            WEB3ToSuccIfoOrderUnitImplForTest l_impl = new WEB3ToSuccIfoOrderUnitImplForTest(l_rsvIfoOrderUnitParams);
            l_impl.name = STR_METHOD_NAME;
            RsvIfoClosingContractSpecRow[] l_rsvIfoClosingContractSpecRows = l_impl.getContractsToClose();

            assertEquals(1001L, l_rsvIfoClosingContractSpecRows[0].getAccountId());
            assertEquals(1002L, l_rsvIfoClosingContractSpecRows[0].getSubAccountId());
            assertEquals(1003L, l_rsvIfoClosingContractSpecRows[0].getOrderId());
            assertEquals(1002L, l_rsvIfoClosingContractSpecRows[0].getContractId());
            assertEquals(1, l_rsvIfoClosingContractSpecRows[0].getClosingSerialNo());
            assertEquals(5.0d, l_rsvIfoClosingContractSpecRows[0].getQuantity(), 0);
            assertEquals(0, WEB3DateUtility.compareToDay(WEB3DateUtility.getDate("20080408", "yyyyMMdd"),
                l_rsvIfoClosingContractSpecRows[0].getCreatedTimestamp()));
            assertEquals(0, WEB3DateUtility.compareToDay(WEB3DateUtility.getDate("20080409", "yyyyMMdd"),
                l_rsvIfoClosingContractSpecRows[0].getLastUpdatedTimestamp()));

            assertEquals(1001L, l_rsvIfoClosingContractSpecRows[1].getAccountId());
            assertEquals(1002L, l_rsvIfoClosingContractSpecRows[1].getSubAccountId());
            assertEquals(1003L, l_rsvIfoClosingContractSpecRows[1].getOrderId());
            assertEquals(1001L, l_rsvIfoClosingContractSpecRows[1].getContractId());
            assertEquals(2, l_rsvIfoClosingContractSpecRows[1].getClosingSerialNo());
            assertEquals(3.0d, l_rsvIfoClosingContractSpecRows[1].getQuantity(), 0);
            assertEquals(0, WEB3DateUtility.compareToDay(WEB3DateUtility.getDate("20080408", "yyyyMMdd"),
                l_rsvIfoClosingContractSpecRows[1].getCreatedTimestamp()));
            assertEquals(0, WEB3DateUtility.compareToDay(WEB3DateUtility.getDate("20080409", "yyyyMMdd"),
                l_rsvIfoClosingContractSpecRows[1].getLastUpdatedTimestamp()));

        }
        catch (Exception l_ex)
        {
            log.info(STR_METHOD_NAME + ">>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>test failed !!");
            fail();
        }

        log.info(STR_METHOD_NAME + ">>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>test passed !!");
        log.exiting(TEST_END + STR_METHOD_NAME);
    }

    public void testGetContractsToClose_C006()
    {
        final String STR_METHOD_NAME = " testGetContractsToClose_C006";
        log.entering(TEST_START + STR_METHOD_NAME);

        try
        {
            List l_lisIfoContractRows = new ArrayList();
            IfoContractParams l_ifoContractParams1 = TestDBUtility.getIfoContractRow();
            l_ifoContractParams1.setQuantity(9.0);
            l_lisIfoContractRows.add(l_ifoContractParams1);
            IfoContractParams l_ifoContractParams2 = TestDBUtility.getIfoContractRow();
            l_ifoContractParams2.setContractId(1002L);
            l_ifoContractParams2.setContractPrice(3710.0);
            l_ifoContractParams2.setQuantity(7.0);
            l_lisIfoContractRows.add(l_ifoContractParams2);

            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                "webbroker3.ifo.WEB3IfoPositionManagerImpl",
                "getContractListByOrderUnit",
                new Class[] {long.class},
                l_lisIfoContractRows);

            List l_lisIfoFinTransactionRows = new ArrayList();
            IfoFinTransactionParams l_ifoFinTransactionParams = TestDBUtility.getIfoFinTransactionRow();
            l_lisIfoFinTransactionRows.add(l_ifoFinTransactionParams);

            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                "webbroker3.ifo.WEB3IfoPositionManagerImpl", 
                "getTransactionsListByOrderUnitPlusContract",
                new Class[]
                {long.class, long.class},
                l_lisIfoFinTransactionRows);

            RsvIfoOrderUnitParams l_rsvIfoOrderUnitParams = TestDBUtility.getRsvIfoOrderUnitRow();
            l_rsvIfoOrderUnitParams.setOrderCateg(OrderCategEnum.IDX_FUTURES_CLOSE);
            l_rsvIfoOrderUnitParams.setClosingOrder(WEB3ClosingOrderDef.UNIT_PRICE_LOSS);
            l_rsvIfoOrderUnitParams.setOrderType(OrderTypeEnum.ASSET_IN);
            l_rsvIfoOrderUnitParams.setAccountId(1001L);
            l_rsvIfoOrderUnitParams.setSubAccountId(1002L);
            l_rsvIfoOrderUnitParams.setOrderId(1003L);
            l_rsvIfoOrderUnitParams.setQuantity(5.0d);
            l_rsvIfoOrderUnitParams.setCreatedTimestamp(WEB3DateUtility.getDate("20080408", "yyyyMMdd"));
            l_rsvIfoOrderUnitParams.setLastUpdatedTimestamp(WEB3DateUtility.getDate("20080409", "yyyyMMdd"));
            l_rsvIfoOrderUnitParams.setQuantity(8.0d);

            TestDBUtility.deleteAll(IfoLockedContractDetailsRow.TYPE);
            IfoLockedContractDetailsParams l_ifoLockedContractDetailsParams =
                TestDBUtility.getIfoLockedContractDetailsRow();
            l_ifoLockedContractDetailsParams.setContractId(1002L);
            l_ifoLockedContractDetailsParams.setLockedQuantity(2);
            TestDBUtility.insertWithDel(l_ifoLockedContractDetailsParams);
            l_ifoLockedContractDetailsParams.setContractId(1001L);
            TestDBUtility.insertWithDel(l_ifoLockedContractDetailsParams);

            WEB3ToSuccIfoOrderUnitImplForTest l_impl = new WEB3ToSuccIfoOrderUnitImplForTest(l_rsvIfoOrderUnitParams);
            l_impl.name = STR_METHOD_NAME;
            RsvIfoClosingContractSpecRow[] l_rsvIfoClosingContractSpecRows = l_impl.getContractsToClose();

            assertEquals(1001L, l_rsvIfoClosingContractSpecRows[0].getAccountId());
            assertEquals(1002L, l_rsvIfoClosingContractSpecRows[0].getSubAccountId());
            assertEquals(1003L, l_rsvIfoClosingContractSpecRows[0].getOrderId());
            assertEquals(1002L, l_rsvIfoClosingContractSpecRows[0].getContractId());
            assertEquals(1, l_rsvIfoClosingContractSpecRows[0].getClosingSerialNo());
            assertEquals(5.0d, l_rsvIfoClosingContractSpecRows[0].getQuantity(), 0);
            assertEquals(0, WEB3DateUtility.compareToDay(WEB3DateUtility.getDate("20080408", "yyyyMMdd"),
                l_rsvIfoClosingContractSpecRows[0].getCreatedTimestamp()));
            assertEquals(0, WEB3DateUtility.compareToDay(WEB3DateUtility.getDate("20080409", "yyyyMMdd"),
                l_rsvIfoClosingContractSpecRows[0].getLastUpdatedTimestamp()));

            assertEquals(1001L, l_rsvIfoClosingContractSpecRows[1].getAccountId());
            assertEquals(1002L, l_rsvIfoClosingContractSpecRows[1].getSubAccountId());
            assertEquals(1003L, l_rsvIfoClosingContractSpecRows[1].getOrderId());
            assertEquals(1001L, l_rsvIfoClosingContractSpecRows[1].getContractId());
            assertEquals(2, l_rsvIfoClosingContractSpecRows[1].getClosingSerialNo());
            assertEquals(3.0d, l_rsvIfoClosingContractSpecRows[1].getQuantity(), 0);
            assertEquals(0, WEB3DateUtility.compareToDay(WEB3DateUtility.getDate("20080408", "yyyyMMdd"),
                l_rsvIfoClosingContractSpecRows[1].getCreatedTimestamp()));
            assertEquals(0, WEB3DateUtility.compareToDay(WEB3DateUtility.getDate("20080409", "yyyyMMdd"),
                l_rsvIfoClosingContractSpecRows[1].getLastUpdatedTimestamp()));

        }
        catch (Exception l_ex)
        {
            log.info(STR_METHOD_NAME + ">>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>test failed !!");
            fail();
        }

        log.info(STR_METHOD_NAME + ">>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>test passed !!");
        log.exiting(TEST_END + STR_METHOD_NAME);
    }

    public void testGetContractsToClose_C007()
    {
        final String STR_METHOD_NAME = " testGetContractsToClose_C007";
        log.entering(TEST_START + STR_METHOD_NAME);

        try
        {
            List l_lisIfoContractRows = new ArrayList();
            IfoContractParams l_ifoContractParams1 = TestDBUtility.getIfoContractRow();
            l_ifoContractParams1.setQuantity(9.0);
            l_lisIfoContractRows.add(l_ifoContractParams1);
            IfoContractParams l_ifoContractParams2 = TestDBUtility.getIfoContractRow();
            l_ifoContractParams2.setContractId(1002L);
            l_ifoContractParams2.setContractPrice(3710.0);
            l_ifoContractParams2.setQuantity(7.0);
            l_lisIfoContractRows.add(l_ifoContractParams2);

            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                "webbroker3.ifo.WEB3IfoPositionManagerImpl",
                "getContractListByOrderUnit",
                new Class[] {long.class},
                l_lisIfoContractRows);

            List l_lisIfoFinTransactionRows = new ArrayList();
            IfoFinTransactionParams l_ifoFinTransactionParams = TestDBUtility.getIfoFinTransactionRow();
            l_lisIfoFinTransactionRows.add(l_ifoFinTransactionParams);

            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                "webbroker3.ifo.WEB3IfoPositionManagerImpl", 
                "getTransactionsListByOrderUnitPlusContract",
                new Class[]
                {long.class, long.class},
                l_lisIfoFinTransactionRows);

            RsvIfoOrderUnitParams l_rsvIfoOrderUnitParams = TestDBUtility.getRsvIfoOrderUnitRow();
            l_rsvIfoOrderUnitParams.setOrderCateg(OrderCategEnum.IDX_FUTURES_CLOSE);
            l_rsvIfoOrderUnitParams.setClosingOrder(WEB3ClosingOrderDef.RANDOM);
            l_rsvIfoOrderUnitParams.setOrderType(OrderTypeEnum.ASSET_IN);
            l_rsvIfoOrderUnitParams.setAccountId(1001L);
            l_rsvIfoOrderUnitParams.setSubAccountId(1002L);
            l_rsvIfoOrderUnitParams.setOrderId(1003L);
            l_rsvIfoOrderUnitParams.setQuantity(5.0d);
            l_rsvIfoOrderUnitParams.setCreatedTimestamp(WEB3DateUtility.getDate("20080408", "yyyyMMdd"));
            l_rsvIfoOrderUnitParams.setLastUpdatedTimestamp(WEB3DateUtility.getDate("20080409", "yyyyMMdd"));
            l_rsvIfoOrderUnitParams.setQuantity(8.0d);

            TestDBUtility.deleteAll(IfoLockedContractDetailsRow.TYPE);
            IfoLockedContractDetailsParams l_ifoLockedContractDetailsParams =
                TestDBUtility.getIfoLockedContractDetailsRow();
            l_ifoLockedContractDetailsParams.setContractId(1002L);
            l_ifoLockedContractDetailsParams.setLockedQuantity(2);
            TestDBUtility.insertWithDel(l_ifoLockedContractDetailsParams);
            l_ifoLockedContractDetailsParams.setContractId(1001L);
            TestDBUtility.insertWithDel(l_ifoLockedContractDetailsParams);

            WEB3ToSuccIfoOrderUnitImplForTest l_impl = new WEB3ToSuccIfoOrderUnitImplForTest(l_rsvIfoOrderUnitParams);
            l_impl.name = STR_METHOD_NAME;
            RsvIfoClosingContractSpecRow[] l_rsvIfoClosingContractSpecRows = l_impl.getContractsToClose();

            assertEquals(1001L, l_rsvIfoClosingContractSpecRows[0].getAccountId());
            assertEquals(1002L, l_rsvIfoClosingContractSpecRows[0].getSubAccountId());
            assertEquals(1003L, l_rsvIfoClosingContractSpecRows[0].getOrderId());
            assertEquals(1001L, l_rsvIfoClosingContractSpecRows[0].getContractId());
            assertEquals(1, l_rsvIfoClosingContractSpecRows[0].getClosingSerialNo());
            assertEquals(6.0d, l_rsvIfoClosingContractSpecRows[0].getQuantity(), 0);
            assertEquals(0, WEB3DateUtility.compareToDay(WEB3DateUtility.getDate("20080408", "yyyyMMdd"),
                l_rsvIfoClosingContractSpecRows[0].getCreatedTimestamp()));
            assertEquals(0, WEB3DateUtility.compareToDay(WEB3DateUtility.getDate("20080409", "yyyyMMdd"),
                l_rsvIfoClosingContractSpecRows[0].getLastUpdatedTimestamp()));

            assertEquals(1001L, l_rsvIfoClosingContractSpecRows[1].getAccountId());
            assertEquals(1002L, l_rsvIfoClosingContractSpecRows[1].getSubAccountId());
            assertEquals(1003L, l_rsvIfoClosingContractSpecRows[1].getOrderId());
            assertEquals(1002L, l_rsvIfoClosingContractSpecRows[1].getContractId());
            assertEquals(2, l_rsvIfoClosingContractSpecRows[1].getClosingSerialNo());
            assertEquals(2.0d, l_rsvIfoClosingContractSpecRows[1].getQuantity(), 0);
            assertEquals(0, WEB3DateUtility.compareToDay(WEB3DateUtility.getDate("20080408", "yyyyMMdd"),
                l_rsvIfoClosingContractSpecRows[1].getCreatedTimestamp()));
            assertEquals(0, WEB3DateUtility.compareToDay(WEB3DateUtility.getDate("20080409", "yyyyMMdd"),
                l_rsvIfoClosingContractSpecRows[1].getLastUpdatedTimestamp()));

        }
        catch (Exception l_ex)
        {
            log.info(STR_METHOD_NAME + ">>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>test failed !!");
            fail();
        }

        log.info(STR_METHOD_NAME + ">>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>test passed !!");
        log.exiting(TEST_END + STR_METHOD_NAME);
    }

    public void testGetContractsToClose_C008()
    {
        final String STR_METHOD_NAME = " testGetContractsToClose_C008";
        log.entering(TEST_START + STR_METHOD_NAME);

        try
        {
            List l_lisIfoContractRows = new ArrayList();
            IfoContractParams l_ifoContractParams1 = TestDBUtility.getIfoContractRow();
            l_ifoContractParams1.setQuantity(1.0);
            l_lisIfoContractRows.add(l_ifoContractParams1);
            IfoContractParams l_ifoContractParams2 = TestDBUtility.getIfoContractRow();
            l_ifoContractParams2.setContractId(1002L);
            l_ifoContractParams2.setContractPrice(3710.0);
            l_ifoContractParams2.setQuantity(7.0);
            l_lisIfoContractRows.add(l_ifoContractParams2);

            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                "webbroker3.ifo.WEB3IfoPositionManagerImpl",
                "getContractListByOrderUnit",
                new Class[] {long.class},
                l_lisIfoContractRows);

            List l_lisIfoFinTransactionRows = new ArrayList();
            IfoFinTransactionParams l_ifoFinTransactionParams = TestDBUtility.getIfoFinTransactionRow();
            l_lisIfoFinTransactionRows.add(l_ifoFinTransactionParams);

            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                "webbroker3.ifo.WEB3IfoPositionManagerImpl", 
                "getTransactionsListByOrderUnitPlusContract",
                new Class[]
                {long.class, long.class},
                l_lisIfoFinTransactionRows);

            RsvIfoOrderUnitParams l_rsvIfoOrderUnitParams = TestDBUtility.getRsvIfoOrderUnitRow();
            l_rsvIfoOrderUnitParams.setOrderCateg(OrderCategEnum.IDX_FUTURES_CLOSE);
            l_rsvIfoOrderUnitParams.setClosingOrder(WEB3ClosingOrderDef.UNIT_PRICE_LOSS);
            l_rsvIfoOrderUnitParams.setOrderType(OrderTypeEnum.IDX_FUTURES_SELL_TO_CLOSE);

            TestDBUtility.deleteAll(IfoLockedContractDetailsRow.TYPE);
            IfoLockedContractDetailsParams l_ifoLockedContractDetailsParams =
                TestDBUtility.getIfoLockedContractDetailsRow();
            l_ifoLockedContractDetailsParams.setContractId(1002L);
            l_ifoLockedContractDetailsParams.setLockedQuantity(2);
            TestDBUtility.insertWithDel(l_ifoLockedContractDetailsParams);
            l_ifoLockedContractDetailsParams.setContractId(1001L);
            TestDBUtility.insertWithDel(l_ifoLockedContractDetailsParams);

            WEB3ToSuccIfoOrderUnitImplForTest l_impl = new WEB3ToSuccIfoOrderUnitImplForTest(l_rsvIfoOrderUnitParams);
            l_impl.name = STR_METHOD_NAME;
            l_impl.getContractsToClose();

            fail();
        }
        catch (WEB3BusinessLayerException l_ex)
        {
            assertEquals("BUSINESS_ERROR_00299", l_ex.getErrorInfo().getErrorTag());
        }
        catch (Exception l_ex)
        {
            log.info(STR_METHOD_NAME + ">>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>test failed !!");
            fail();
        }

        log.info(STR_METHOD_NAME + ">>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>test passed !!");
        log.exiting(TEST_END + STR_METHOD_NAME);
    }

    public void testGetContractsToClose_C009()
    {
        final String STR_METHOD_NAME = " testGetContractsToClose_C009";
        log.entering(TEST_START + STR_METHOD_NAME);

        try
        {
            List l_lisIfoContractRows = new ArrayList();

            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                "webbroker3.ifo.WEB3IfoPositionManagerImpl",
                "getContractListByOrderUnit",
                new Class[] {long.class},
                l_lisIfoContractRows);

            RsvIfoOrderUnitParams l_rsvIfoOrderUnitParams = TestDBUtility.getRsvIfoOrderUnitRow();
            l_rsvIfoOrderUnitParams.setOrderCateg(OrderCategEnum.IDX_FUTURES_CLOSE);
            l_rsvIfoOrderUnitParams.setClosingOrder(WEB3ClosingOrderDef.UNIT_PRICE_LOSS);
            l_rsvIfoOrderUnitParams.setOrderType(OrderTypeEnum.IDX_FUTURES_SELL_TO_CLOSE);

            WEB3ToSuccIfoOrderUnitImplForTest l_impl = new WEB3ToSuccIfoOrderUnitImplForTest(l_rsvIfoOrderUnitParams);
            l_impl.name = STR_METHOD_NAME;
            assertNull(l_impl.getContractsToClose());

        }
        catch (WEB3BusinessLayerException l_ex)
        {
            assertEquals("BUSINESS_ERROR_00299", l_ex.getErrorInfo().getErrorTag());
        }
        catch (Exception l_ex)
        {
            log.info(STR_METHOD_NAME + ">>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>test failed !!");
            fail();
        }

        log.info(STR_METHOD_NAME + ">>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>test passed !!");
        log.exiting(TEST_END + STR_METHOD_NAME);
    }

    class WEB3ToSuccIfoOrderUnitImplForTest extends WEB3ToSuccIfoOrderUnitImpl
    {
        public String name;

        public WEB3ToSuccIfoOrderUnitImplForTest(RsvIfoOrderUnitRow l_rsvIfoOrderUnitRow)
        {
            super(l_rsvIfoOrderUnitRow);
        }

        public IfoOrderUnit getParentOrderUnit()
        {
            if (" testGetRsvOrderExecPrice_C001".equals(name))
            {
                return null;
            }
            else if (" testGetContractsToClose_C004".equals(name) || " testGetContractsToClose_C005".equals(name)
                || " testGetContractsToClose_C006".equals(name) || " testGetContractsToClose_C007".equals(name)
                || " testGetContractsToClose_C008".equals(name) || " testGetContractsToClose_C009".equals(name))
            {
                WEB3ToSuccIfoOrderUnitImplForTest l_implForTest = new WEB3ToSuccIfoOrderUnitImplForTest(null);
                l_implForTest.name = name;
                return l_implForTest;
            }
            else
            {
                return super.getParentOrderUnit();
            }
        }

        public TradedProduct getTradedProduct()
        {
            if (" testGetRsvOrderExecPrice_C001".equals(name))
            {
                return null;
            }
            else
            {
                return super.getTradedProduct();
            }
        }

        public boolean isReversingTrade() throws WEB3BaseException
        {
            if (" testGetContractsToClose_C002".equals(name) || " testGetContractsToClose_C003".equals(name))
            {
                return false;
            }
            else if (" testGetContractsToClose_C004".equals(name) || " testGetContractsToClose_C005".equals(name)
                || " testGetContractsToClose_C006".equals(name) || " testGetContractsToClose_C007".equals(name)
                || " testGetContractsToClose_C008".equals(name) || " testGetContractsToClose_C009".equals(name))
            {
                return true;
            }
            else
            {
                return super.isReversingTrade();
            }
        }

        public boolean isFullyExecuted()
        {
            if (" testGetContractsToClose_C004".equals(name))
            {
                return false;
            }
            else if (" testGetContractsToClose_C005".equals(name) || " testGetContractsToClose_C006".equals(name)
                || " testGetContractsToClose_C007".equals(name) || " testGetContractsToClose_C008".equals(name)
                || " testGetContractsToClose_C009".equals(name))
            {
                return true;
            }            
            else
            {
                return super.isFullyExecuted();
            }
        }

        public long getOrderId()
        {
            if (" testGetContractsToClose_C005".equals(name) || " testGetContractsToClose_C006".equals(name)
                || " testGetContractsToClose_C007".equals(name) || " testGetContractsToClose_C008".equals(name)
                || " testGetContractsToClose_C009".equals(name))
            {
                return 100L;
            }
            else
            {
                return super.getOrderId();
            }
        }

        public long getOrderUnitId()
        {
            if (" testGetContractsToClose_C005".equals(name) || " testGetContractsToClose_C006".equals(name)
                || " testGetContractsToClose_C007".equals(name) || " testGetContractsToClose_C008".equals(name)
                || " testGetContractsToClose_C009".equals(name))
            {
                return 100L;
            }
            else
            {
                return super.getOrderUnitId();
            }
        }
    }

    public void testGetMsgTradingType_C001()
    {
        final String STR_METHOD_NAME = " testGetMsgTradingType_C001";
        log.entering(TEST_START + STR_METHOD_NAME);

        try
        {
            RsvIfoOrderUnitParams l_rsvIfoOrderUnitParams = TestDBUtility.getRsvIfoOrderUnitRow();
            l_rsvIfoOrderUnitParams.setOrderType(OrderTypeEnum.IDX_OPTIONS_BUY_TO_OPEN);

            WEB3ToSuccIfoOrderUnitImpl l_impl = new WEB3ToSuccIfoOrderUnitImpl(l_rsvIfoOrderUnitParams);

            assertEquals("3", l_impl.getMsgTradingType());
        }
        catch (Exception l_ex)
        {
            log.info(STR_METHOD_NAME + ">>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>test failed !!");
            fail();
        }

        log.info(STR_METHOD_NAME + ">>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>test passed !!");
        log.exiting(TEST_END + STR_METHOD_NAME);
    }

    public void testGetMsgTradingType_C002()
    {
        final String STR_METHOD_NAME = " testGetMsgTradingType_C002";
        log.entering(TEST_START + STR_METHOD_NAME);

        try
        {
            RsvIfoOrderUnitParams l_rsvIfoOrderUnitParams = TestDBUtility.getRsvIfoOrderUnitRow();
            l_rsvIfoOrderUnitParams.setOrderType(OrderTypeEnum.IDX_FUTURES_SELL_TO_OPEN);

            WEB3ToSuccIfoOrderUnitImpl l_impl = new WEB3ToSuccIfoOrderUnitImpl(l_rsvIfoOrderUnitParams);

            assertEquals("4", l_impl.getMsgTradingType());
        }
        catch (Exception l_ex)
        {
            log.info(STR_METHOD_NAME + ">>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>test failed !!");
            fail();
        }

        log.info(STR_METHOD_NAME + ">>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>test passed !!");
        log.exiting(TEST_END + STR_METHOD_NAME);
    }

    public void testGetMsgTradingType_C003()
    {
        final String STR_METHOD_NAME = " testGetMsgTradingType_C003";
        log.entering(TEST_START + STR_METHOD_NAME);

        try
        {
            RsvIfoOrderUnitParams l_rsvIfoOrderUnitParams = TestDBUtility.getRsvIfoOrderUnitRow();
            l_rsvIfoOrderUnitParams.setOrderType(OrderTypeEnum.IDX_OPTIONS_SELL_TO_OPEN);

            WEB3ToSuccIfoOrderUnitImpl l_impl = new WEB3ToSuccIfoOrderUnitImpl(l_rsvIfoOrderUnitParams);

            assertEquals("4", l_impl.getMsgTradingType());
        }
        catch (Exception l_ex)
        {
            log.info(STR_METHOD_NAME + ">>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>test failed !!");
            fail();
        }

        log.info(STR_METHOD_NAME + ">>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>test passed !!");
        log.exiting(TEST_END + STR_METHOD_NAME);
    }

    public void testGetMsgTradingType_C004()
    {
        final String STR_METHOD_NAME = " testGetMsgTradingType_C004";
        log.entering(TEST_START + STR_METHOD_NAME);

        try
        {
            RsvIfoOrderUnitParams l_rsvIfoOrderUnitParams = TestDBUtility.getRsvIfoOrderUnitRow();
            l_rsvIfoOrderUnitParams.setOrderType(OrderTypeEnum.IDX_FUTURES_SELL_TO_CLOSE);

            WEB3ToSuccIfoOrderUnitImpl l_impl = new WEB3ToSuccIfoOrderUnitImpl(l_rsvIfoOrderUnitParams);

            assertEquals("5", l_impl.getMsgTradingType());
        }
        catch (Exception l_ex)
        {
            log.info(STR_METHOD_NAME + ">>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>test failed !!");
            fail();
        }

        log.info(STR_METHOD_NAME + ">>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>test passed !!");
        log.exiting(TEST_END + STR_METHOD_NAME);
    }

    public void testGetMsgTradingType_C005()
    {
        final String STR_METHOD_NAME = " testGetMsgTradingType_C005";
        log.entering(TEST_START + STR_METHOD_NAME);

        try
        {
            RsvIfoOrderUnitParams l_rsvIfoOrderUnitParams = TestDBUtility.getRsvIfoOrderUnitRow();
            l_rsvIfoOrderUnitParams.setOrderType(OrderTypeEnum.IDX_OPTIONS_SELL_TO_CLOSE);

            WEB3ToSuccIfoOrderUnitImpl l_impl = new WEB3ToSuccIfoOrderUnitImpl(l_rsvIfoOrderUnitParams);

            assertEquals("5", l_impl.getMsgTradingType());
        }
        catch (Exception l_ex)
        {
            log.info(STR_METHOD_NAME + ">>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>test failed !!");
            fail();
        }

        log.info(STR_METHOD_NAME + ">>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>test passed !!");
        log.exiting(TEST_END + STR_METHOD_NAME);
    }

    public void testGetMsgTradingType_C006()
    {
        final String STR_METHOD_NAME = " testGetMsgTradingType_C006";
        log.entering(TEST_START + STR_METHOD_NAME);

        try
        {
            RsvIfoOrderUnitParams l_rsvIfoOrderUnitParams = TestDBUtility.getRsvIfoOrderUnitRow();
            l_rsvIfoOrderUnitParams.setOrderType(OrderTypeEnum.IDX_FUTURES_BUY_TO_CLOSE);

            WEB3ToSuccIfoOrderUnitImpl l_impl = new WEB3ToSuccIfoOrderUnitImpl(l_rsvIfoOrderUnitParams);

            assertEquals("6", l_impl.getMsgTradingType());
        }
        catch (Exception l_ex)
        {
            log.info(STR_METHOD_NAME + ">>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>test failed !!");
            fail();
        }

        log.info(STR_METHOD_NAME + ">>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>test passed !!");
        log.exiting(TEST_END + STR_METHOD_NAME);
    }

    public void testGetMsgTradingType_C007()
    {
        final String STR_METHOD_NAME = " testGetMsgTradingType_C007";
        log.entering(TEST_START + STR_METHOD_NAME);

        try
        {
            RsvIfoOrderUnitParams l_rsvIfoOrderUnitParams = TestDBUtility.getRsvIfoOrderUnitRow();
            l_rsvIfoOrderUnitParams.setOrderType(OrderTypeEnum.IDX_OPTIONS_BUY_TO_CLOSE);

            WEB3ToSuccIfoOrderUnitImpl l_impl = new WEB3ToSuccIfoOrderUnitImpl(l_rsvIfoOrderUnitParams);

            assertEquals("6", l_impl.getMsgTradingType());
        }
        catch (Exception l_ex)
        {
            log.info(STR_METHOD_NAME + ">>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>test failed !!");
            fail();
        }

        log.info(STR_METHOD_NAME + ">>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>test passed !!");
        log.exiting(TEST_END + STR_METHOD_NAME);
    }
}
@
