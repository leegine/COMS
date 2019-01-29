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
     * ���O�o�̓��[�e�B���e�B�B<BR>
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
     * get�L���敨OP�q�����P�ʈꗗ
     * �w�肳�ꂽ�e�����ɕR�t���A�L���Ȑ敨OP�\�񒍕��P�ʃI�u�W�F�N�g�̔z���ԋp����B
         �L���ȗ\�񒍕��P�ʂ��擾1��
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
     * get�L���敨OP�q�����P�ʈꗗ
     * �w�肳�ꂽ�e�����ɕR�t���A�L���Ȑ敨OP�\�񒍕��P�ʃI�u�W�F�N�g�̔z���ԋp����B
     �L���ȗ\�񒍕��P�ʂ���s���B
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
     * get�L���敨OP�q�����P�ʈꗗ
     * �w�肳�ꂽ�e�����ɕR�t���A�L���Ȑ敨OP�\�񒍕��P�ʃI�u�W�F�N�g�̔z���ԋp����B
     �L���ȗ\�񒍕��P�ʂ��擾3��
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
     * get�敨OP�\�񒍕����s�P��
     * �����̒P�������l == null �̏ꍇ�́A�����̎w�l�����̂܂ܕԋp����B 
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
     * get�敨OP�\�񒍕����s�P��
     * �e�������S����肵�Ă���ꍇ
        �q�������}�C�i�X�w��̏ꍇ
        �e�����̒����P��.isFullyExecuted()==true
        �����̒P�������l�̕���==�}�C�i�X
        ���s�P�������߂�
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
     * get�敨OP�\�񒍕����s�P��
     * �e�������S����肵�Ă���ꍇ
        �q�������v���X�w��̏ꍇ
        �e�����̒����P��.isFullyExecuted()==true
        �����̒P�������l�̕������}�C�i�X
        ���s�P�������߂�
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
     * get�敨OP�\�񒍕����s�P��
     * �e�������S�����ȊO�̏ꍇ
        �e�������w�l�����̏ꍇ
        ���s�P�������߂�
        �e�����̒����P��.isFullyExecuted()==false
        �e�����̒����P��.isLimitPrice()==true
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
     * get�敨OP�\�񒍕����s�P��
     * �e�������S�����ȊO�̏ꍇ
        �e�������w�l�����̏ꍇ
        ���s�P�������߂�
        �e�����̒����P��.isFullyExecuted()==false
        �e�����̒����P��.isLimitPrice()==false
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
     * get�敨OP�\�񒍕����s�P��
     * ���߂����s�P����0�ȉ��̏ꍇ�́A�u���s�P����0�ȉ��v�̗�O��throw����B
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
     * get�敨OP�e�����̒����P��
     * OP�����}�l�[�W��.getOrderUnits(�����́i�e�����j����ID)���R�[������B 
         �߂�l�̍ŏ��̗v�f��e�����̒����P�ʃI�u�W�F�N�g�Ƃ��ĕԋp����B
         �擞�ԉ�B
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
     * get�敨OP�e�����̒����P��
     * OP�����}�l�[�W��.getOrderUnits(�����́i�e�����j����ID)���R�[������B 
         �߂�l�̍ŏ��̗v�f��e�����̒����P�ʃI�u�W�F�N�g�Ƃ��ĕԋp����B
         ��s���ԉ�null;
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
     * submit�敨OP�V�K���V�K�\�񒍕�
     * �����`�F�b�N.validate����p�X���[�hisFailedResult
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
     * submit�敨OP�V�K���V�K�\�񒍕�
     * �p�����[�^.�e�����̒����P��.�敨�^�I�v�V�����敪��"�敨"�̏ꍇ 
         �敨OP�\�񒍕��P�ʃe�[�u���Ƀ��R�[�h��o�^����
         �����̒������e.isBuyToOpenOrder() == true
         �����̒P�������l == null
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
     * submit�敨OP�V�K���V�K�\�񒍕�
     * �p�����[�^.�e�����̒����P��.�敨�^�I�v�V�����敪��"�敨"�̏ꍇ 
         �敨OP�\�񒍕��P�ʃe�[�u���Ƀ��R�[�h��o�^����
         �����̒������e.isBuyToOpenOrder() != true
         �����̒P�������l != null
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
     * submit�敨OP�V�K���V�K�\�񒍕�
     * �p�����[�^.�e�����̒����P��.�敨�^�I�v�V�����敪��"�I�v�V����"�̏ꍇ
     �敨OP�\�񒍕��P�ʃe�[�u���Ƀ��R�[�h��o�^����
         �����̒������e.isBuyToOpenOrder() == true
         �����̒P�������l == null
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
     * submit�敨OP�V�K���V�K�\�񒍕�
     * �p�����[�^.�e�����̒����P��.�敨�^�I�v�V�����敪��"�I�v�V����"�̏ꍇ
         �敨OP�\�񒍕��P�ʃe�[�u���Ƀ��R�[�h��o�^����
         �����̒������e.isBuyToOpenOrder() != true
         �����̒P�������l != null
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
     * submit�敨OP�ԍϐV�K�\�񒍕�
     * �����`�F�b�N.validate����p�X���[�hisFailedResult
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
     * submit�敨OP�ԍϐV�K�\�񒍕�
     * �p�����[�^.�e�����̒����P��.�敨�^�I�v�V�����敪��"�敨"�̏ꍇ
        �敨OP�\�񒍕��P�ʃe�[�u���Ƀ��R�[�h��o�^����B
        ���Δ����łȂ��ꍇ
        �敨OP�\�񌚋ʕԍώw����e�[�u���� 
        �@@���R�[�h��o�^����B
        �����̌���.isLong()==true
        �����̒P�������l == null
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
     * submit�敨OP�ԍϐV�K�\�񒍕�
     * �p�����[�^.�e�����̒����P��.�敨�^�I�v�V�����敪��"�敨"�̏ꍇ
        �敨OP�\�񒍕��P�ʃe�[�u���Ƀ��R�[�h��o�^����B
        ���Δ����łȂ��ꍇ
        �敨OP�\�񌚋ʕԍώw����e�[�u���� 
        �@@���R�[�h��o�^����B
        �����̌���.isLong() �I=true
        �����̒P�������l != null
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
     * submit�敨OP�ԍϐV�K�\�񒍕�
     * �p�����[�^.�e�����̒����P��.�敨�^�I�v�V�����敪��"�I�v�V����"�̏ꍇ
       �敨OP�\�񒍕��P�ʃe�[�u���Ƀ��R�[�h��o�^����B
        ���Δ����łȂ��ꍇ
        �敨OP�\�񌚋ʕԍώw����e�[�u���� 
        �@@���R�[�h��o�^����B
        �����̌���.isLong()==true
        �����̒P�������l == null
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
    

    /** submit�敨OP�ԍϐV�K�\�񒍕�
    * �p�����[�^.�e�����̒����P��.�敨�^�I�v�V�����敪��"�敨"�̏ꍇ
       �敨OP�\�񒍕��P�ʃe�[�u���Ƀ��R�[�h��o�^����B
       ���Δ����łȂ��ꍇ
       �敨OP�\�񌚋ʕԍώw����e�[�u���� 
       �@@���R�[�h��o�^����B
       �����̌���.isLong() �I=true
       �����̒P�������l != null
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
    * submit�敨OP�����\��V�K������
    * �����`�F�b�N.validate����p�X���[�hisFailedResult
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
     * submit�敨OP�����\��V�K������
     * �敨OP�\�񒍕��P�ʃe�[�u���̃��R�[�h���X�V����B
     * �u�i�A���j�V�K������_�敨OP�\�񒍕��P�ʃe�[�u��.xls�v���Q�ƁB
     * get����()�̖߂�l != nul
     * �����̗\�񒍕��������e.get������P�������l()==null
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
    * submit�敨OP�����\��V�K������
    * �敨OP�\�񒍕��P�ʃe�[�u���̃��R�[�h���X�V����B
    * �u�i�A���j�V�K������_�敨OP�\�񒍕��P�ʃe�[�u��.xls�v���Q�ƁB
        get����()�̖߂�l == nul
        �����̗\�񒍕��������e.get������P�������l() !=null
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
   * submit�敨OP�����\��V�K������
   * �����`�F�b�N.validate����p�X���[�hisFailedResult
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
     * �敨OP�\�񒍕��P�ʃe�[�u���̃��R�[�h���X�V����B
    �u�i�A���j�ԍϒ�������_�敨OP�\�񒍕��P�ʃe�[�u��.xls�v���Q�ƁB
    �i�p�����[�^.�����O�\�񒍕��P��.is���Δ������() == false�j 
    �@@�敨OP�\�񌚋ʕԍώw����e�[�u���̃��R�[�h���X�V����B �敨OP�\�񒍕��P�ʃe�[�u���̃��R�[�h���X�V����B
    �u�i�A���j�ԍϒ�������_�敨OP�\�񒍕��P�ʃe�[�u��.xls�v���Q�ƁB
    �i�p�����[�^.�����O�\�񒍕��P��.is���Δ������() == false�j 
    �@@�敨OP�\�񌚋ʕԍώw����e�[�u���̃��R�[�h���X�V����B 
     *get����()�̖߂�l != nul
        �����̗\�񒍕��������e.get������P�������l()==null
        �p�����[�^.�����O�\�񒍕��P��.is���Δ������() == false
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
   * �敨OP�\�񒍕��P�ʃe�[�u���̃��R�[�h���X�V����B
  �u�i�A���j�ԍϒ�������_�敨OP�\�񒍕��P�ʃe�[�u��.xls�v���Q�ƁB
    get����()�̖߂�l == nul
    �����̗\�񒍕��������e.get������P�������l() !=null
    �p�����[�^.�����O�\�񒍕��P��.is���Δ������() == true
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
     * submit�敨OP�\�񒍕����
   * �����`�F�b�N.validate����p�X���[�hisFailedResult
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
     * submit�敨OP�\�񒍕����
     * �敨OP�\�񒍕��X�V�T�[�r�X.cancel�\�񒍕��P��(�����̗\�񒍕��P��)�� 
�@@       �R�[������B
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
     * validate�敨OP�������
     * validate�����e�ُ�
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
     * validate�敨OP�������
     * validate����\��ԝe�ُ�
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
     * get�敨OP�e�������A��
     * �敨OP�\�񒍕��P�ʃe�[�u���z����������B
     �擪���R�[�h�́i�e�������A�ԁ{�P�j�������ʂ��A�e�������A�ԂƂ��ĕԋp����
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
     * get�敨OP�e�������A��
     * �敨OP�\�񒍕��P�ʃe�[�u���z����������B
         �Y�����R�[�h�Ȃ��̏ꍇ�́A�P��ԋp����
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
     * set�\�񒍕��ݒ�To�敨OP�e����
     *�����̐e�����̒����P�ʂ��Aclone�I�u�W�F�N�g���쐬����B 
        ��������clone�I�u�W�F�N�g�ɁA�ȉ��̃v���p�e�B���ăZ�b�g����B 
        �\�񒍕��ݒ�t���O�F�@@"�ݒ�̉\������" 
        �X�V���t�F�@@GtlUtils.getSystemTimestamp()
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
     * create�ԍό��ʃG���g�� 
     * SettleContractEntry�C���X�^���X�� 
         �쐬���A���̃C���X�^���X�݂̂�v�f�Ƃ���z���ԋp����B
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
     * create���ʖ���
        �����P�ʂ�茚�ʖ��ׂ��쐬����
        ���������C���X�^���X�Ɉȉ��̃v���p�e�B���Z�b�g����B 
        �ȉ��̍��ڈȊO��null���Z�b�g����B 
        ���� = �����P��.������ 
        ���ʐ� = �����P��.�������� 
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
//     * create���ʏƉ��
//        �����P�ʂ�茚�ʏƉ�ׂ��쐬����
//        ���������C���X�^���X�Ɉȉ��̃v���p�e�B���Z�b�g����B 
//        �ȉ��̍��ڈȊO��null���Z�b�g����B 
//        �w����� = �����P��.�敨OP����.�����Y�����R�[�h 
//        ���� = �����P��.�敨OP����.���� 
//        ����s�� = �����P��.�s��R�[�h�iSONAR�j 
//        ���敪 = �����P��.getSide() == "��"�̏ꍇ�A"����"
//        ���� = �����P��.������ 
//        ������ = �����P��.�������� 
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
//     * create���ʏƉ��
//        �����P�ʂ�茚�ʏƉ�ׂ��쐬����
//        ���������C���X�^���X�Ɉȉ��̃v���p�e�B���Z�b�g����B 
//        �ȉ��̍��ڈȊO��null���Z�b�g����B 
//        �w����� = �����P��.�敨OP����.�����Y�����R�[�h 
//        ���� = �����P��.�敨OP����.���� 
//        ����s�� = �����P��.�s��R�[�h�iSONAR�j 
//        ���敪 = �����P��.getSide() != "��"�̏ꍇ�A"����"
//        ���� = �����P��.������ 
//        ������ = �����P��.�������� 
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
     * get�A�������戵���i�ꗗ
        ����Ђ��戵���Ă���A�������̏��i�ꗗ��ԋp����
        �v�f��"��������"or"�M�p���"�̏ꍇ
        validate�A�������戵�\()����O���X���[����
        �@@�v�f��"�敨"or"�I�v�V����"�̏ꍇ
        validate�A�������戵�\()����O���X���[���Ȃ�
        �쐬����ArrayList��Ԃ�
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
     * create�敨OP�����P��
     * �敨OP�����P��Params�C���X�^���X�𐶐�����B 
     * �\�񒍕��P��.�����J�e�S�����敨�V�K������
         �\�񒍕��P��.�敨�^�I�v�V�����敪���敨
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
     * create�敨OP�����P��
     * �敨OP�����P��Params�C���X�^���X�𐶐�����B 
     * �\�񒍕��P��.�����J�e�S�����敨�ԍϒ���
         �\�񒍕��P��.�敨�^�I�v�V�����敪 !���敨
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
     * create�敨OP�����P��
     * �\�񒍕��P��.�����J�e�S���I���敨�V�K�������A
     * ����OP�V�K���������͐敨�ԍϒ����A����OP�ԍϒ��� �̏ꍇ��O��throw����B  
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
     * get�敨OP�\�񒍕��P��
     * �y�敨OP�\�񒍕��P�ʃe�[�u���z����������B 
         �敨OP�\�񒍕��P�ʃI�u�W�F�N�g��ԋp����B
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
        �����̒����P��Row�̌^���敨OP�\�񒍕��P��Row�̏ꍇ
        �����̒����P��Row�I�u�W�F�N�g�������Ɏw�肵�āA 
        �敨OP�\�񒍕��P�ʃI�u�W�F�N�g�𐶐�����B 
        ���������I�u�W�F�N�g��ԋp����B
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
     * validate�A�������ő�ݒ萔
     * �@@[�敨OP�����P�ʂ̏ꍇ] 
         this.get�L���敨OP�q�����P�ʈꗗ()���R�[������B 
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
     * get�A�������ő�ݒ茏��
     * ���X�v���t�@@�����X�e�[�u�����ȉ��̏����� ��������B 
     * �v���t�@@�����X���̘A�� = �p�����[�^.�e�����̒����P�ʂ̌^���A�敨OP�����P�ʂ̏ꍇ�A"2" 
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
     * validate�g���K�[�����ݒ�To�e����
        �e�����̒����P�ʂ̌^���A���������P�ʂ̏ꍇ�@@ 
        �e�������������ϒ���(*3)�̏ꍇ�A 
        �u�e�������������ϒ����̂��߃g���K�[�����ݒ�s�v�� 
        ��O��throw����B 
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
     * validate�g���K�[�����ݒ�To�e����
     * �e�����̒����P�ʂ̌^ !=���������P�ʂ̏ꍇ�@@ 
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
     * set������To�\�񒍕��P�� 
     * �w�肳�ꂽ�\�񒍕��P�ʃI�u�W�F�N�g���A�����ς̏�ԂɍX�V����B
        �����̖����^�C�v !="����"�̏ꍇ
        this.set������To�敨OP�\�񒍕��P��( �P�j�Ŏ擾�����敨OP�\�񒍕��P��)���R�[������B
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
     * validate�A������
     * �A����������t�\�Ȏ��ԑт��ǂ������`�F�b�N����B
     * �@@�����̖����^�C�v == "��"�̏ꍇ�A"�o���I���i�ŏI�j" 
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
     * validate�A������
     * �A����������t�\�Ȏ��ԑт��ǂ������`�F�b�N����B
     * �����̕⏕����.get����X().is�[����{() == true and �����̐e�����̒����P��.����敪 == "���̑�"�̏ꍇ�A"�[��O�o���I��" 
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
     * validate�A������
     * �A����������t�\�Ȏ��ԑт��ǂ������`�F�b�N����B
     * �ȊO�A"�o���I���i�ŏI�j" 
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
     * get���Δ������
        �؋��������J�ݍς̏ꍇ ���� �P�j�̖߂�l��"�敨"���܂܂�Ă���ꍇ 
        �ȉ��̕���ɂ������ǉ�����B 
        ["�敨�V�K��������" or �@@"�敨�V�K��������"�̏ꍇ] �E"�敨�ԍρi�O�񒍕��j" 
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
     * get���Δ������
        OP�����J�ݍ�(*3)�̏ꍇ ���� �P�j�̖߂�l��"�I�v�V����"���܂܂�Ă���ꍇ 
        �ȉ��̕���ɂ������ǉ�����B 
        ["OP�V�K��������" or "OP�V�K��������"�̏ꍇ] �E"OP�ԍρi�O�񒍕��j" 
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
     * get�A����������ꗗ
        �p�����[�^.���i�敪�ꗗ��"�敨"�� �܂܂��ꍇ ���� �敨�����J�ݍ�(*2)�̏ꍇ ���� 
        �P�j�̖߂�l��"�敨"���܂܂�Ă���ꍇ�A 
        �敨�̎���敪��ArrayList�ɒǉ�����B 
        "�敨�V�K��" 
        "�敨�ԍρi�����c�j" 
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
     * get�A����������ꗗ
        �p�����[�^.���i�敪�ꗗ��"�I�v�V����"�� 
        �܂܂��ꍇ ���� OP�����J�ݍ�(*3)�̏ꍇ ���� 
        �P�j�̖߂�l��"�I�v�V����"���܂܂�Ă���ꍇ�A 
        �敨�̎���敪��ArrayList�ɒǉ�����B 
        �E"OP�V�K��" 
        �E"OP�ԍρi�����c�j"  
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
     * create����
     * ���敪 = �����P��.getSide() == "��"�̏ꍇ�A"����"
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
                //����ID = 0 
                assertEquals("0", l_contract.getContractId() + "");
                //�@@����ID = �����P�ʂ̓����� 
                assertEquals("101001010010", l_contract.getAccountId() + "");
                //�@@�⏕����ID = �����P�ʂ̓����� 
                assertEquals("10100101001007", l_contract.getSubAccountId() + "");
                //�@@�s��ID = �����P�ʂ̓����� 
                assertEquals("1002", l_contract.getMarketId() + "");
                //�@@1�P�ʓ���搔 = �P�j�Ŏ擾�����敨OP�������.getUnitSize() 
                assertEquals("10000.0", l_contract.getUnitSize() + "");
                //�@@���ʌ����� = �����P��.�������� 
                assertEquals("100.0", l_contract.getOriginalQuantity() + "");
                //�@@���ʐ��� = �����P��.�������� 
                assertEquals("100.0", l_contract.getQuantity() + "");
                //�@@�����P�� = �����P��.�����P�� 
                assertEquals("200.0", l_contract.getOriginalContractPrice() + "");
                //�@@���P�� = �����P��.�����P�� 
                assertEquals("200.0", l_contract.getContractPrice() + "");
                //�@@���敪 = �����P��.getSide() == "��"�̏ꍇ�A"����" �ȊO�A"����" 
                assertEquals("1", l_contract.getContractType().intValue() + "");
                //�@@���� = �����P��.������ 
                assertEquals("20040101", WEB3DateUtility.formatDate(l_contract.getOpenDate(), "yyyyMMdd"));
                //�@@���� = �P�j�Ŏ擾�����敨OP�������.getLastTradingDate() 
                assertEquals("20050101", WEB3DateUtility.formatDate(l_contract.getCloseDate(), "yyyyMMdd"));
                //�@@���ϑ��萔�� = (*1) 
                assertEquals("0.0", l_contract.getSetupFee() + "");
                //�@@���ϑ��萔������� = (*1) 
                assertEquals("100.0", l_contract.getSetupFeeTax() + "");
                //�@@�Ǘ��� = 0 
                assertEquals("0.0", l_contract.getManagementFee() + "");
                //�@@�Ǘ������� = 0 
                assertEquals("0.0", l_contract.getManagementFeeTax() + "");
                //�@@���q = 0 
                assertEquals("0.0", l_contract.getInterestFee() + "");
                //�@@���q����� = 0 
                assertEquals("0.0", l_contract.getInterestFeeTax() + "");
                //�@@����ID = �����P�ʂ̓����� 
                assertEquals("1006169090018", l_contract.getProductId() + "");
                //�@@�����^�C�v = �����P�ʂ̓����� 
                assertEquals("6", l_contract.getProductType().intValue() + "");
                //�@@�쐬���t = GtlUtils.getSystemTimestamp() 
                //�@@�X�V���t = GtlUtils.getSystemTimestamp() 
                //�@@��n�� = �����P�ʂ̓����� 
                assertEquals("20040101", WEB3DateUtility.formatDate(l_contract.getDeliveryDate(), "yyyyMMdd"));
                //�@@����敪 = �����P�ʂ̓�����
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
     * �����P��.getSide() == "��"�̏ꍇ�ȊO
     * ���敪 "����"
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
                //����ID = 0 
                assertEquals("0", l_contract.getContractId() + "");
                //�@@����ID = �����P�ʂ̓����� 
                assertEquals("101001010010", l_contract.getAccountId() + "");
                //�@@�⏕����ID = �����P�ʂ̓����� 
                assertEquals("10100101001007", l_contract.getSubAccountId() + "");
                //�@@�s��ID = �����P�ʂ̓����� 
                assertEquals("1002", l_contract.getMarketId() + "");
                //�@@1�P�ʓ���搔 = �P�j�Ŏ擾�����敨OP�������.getUnitSize() 
                assertEquals("10000.0", l_contract.getUnitSize() + "");
                //�@@���ʌ����� = �����P��.�������� 
                assertEquals("100.0", l_contract.getOriginalQuantity() + "");
                //�@@���ʐ��� = �����P��.�������� 
                assertEquals("100.0", l_contract.getQuantity() + "");
                //�@@�����P�� = �����P��.�����P�� 
                assertEquals("200.0", l_contract.getOriginalContractPrice() + "");
                //�@@���P�� = �����P��.�����P�� 
                assertEquals("200.0", l_contract.getContractPrice() + "");
                //�@@���敪 = �����P��.getSide() == "��"�̏ꍇ�A"����" �ȊO�A"����" 
                assertEquals("2", l_contract.getContractType().intValue() + "");
                //�@@���� = �����P��.������ 
                assertEquals("20040101", WEB3DateUtility.formatDate(l_contract.getOpenDate(), "yyyyMMdd"));
                //�@@���� = �P�j�Ŏ擾�����敨OP�������.getLastTradingDate() 
                assertEquals("20050101", WEB3DateUtility.formatDate(l_contract.getCloseDate(), "yyyyMMdd"));
                //�@@���ϑ��萔�� = (*1) 
                assertEquals("0.0", l_contract.getSetupFee() + "");
                //�@@���ϑ��萔������� = (*1) 
                assertEquals("100.0", l_contract.getSetupFeeTax() + "");
                //�@@�Ǘ��� = 0 
                assertEquals("0.0", l_contract.getManagementFee() + "");
                //�@@�Ǘ������� = 0 
                assertEquals("0.0", l_contract.getManagementFeeTax() + "");
                //�@@���q = 0 
                assertEquals("0.0", l_contract.getInterestFee() + "");
                //�@@���q����� = 0 
                assertEquals("0.0", l_contract.getInterestFeeTax() + "");
                //�@@����ID = �����P�ʂ̓����� 
                assertEquals("1006169090018", l_contract.getProductId() + "");
                //�@@�����^�C�v = �����P�ʂ̓����� 
                assertEquals("6", l_contract.getProductType().intValue() + "");
                //�@@�쐬���t = GtlUtils.getSystemTimestamp() 
                //�@@�X�V���t = GtlUtils.getSystemTimestamp() 
                //�@@��n�� = �����P�ʂ̓����� 
                assertEquals("20040101", WEB3DateUtility.formatDate(l_contract.getDeliveryDate(), "yyyyMMdd"));
                //�@@����敪 = �����P�ʂ̓�����
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
     * �V�K�������i�\�񒍕��P��.�����J�e�S�� == "�敨�V�K������"�j�̏ꍇ
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
     * �V�K�������i�\�񒍕��P��.�����J�e�S�� == ""OP�V�K������"�j�̏ꍇ
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
     * ���Δ����iis���Δ������()�̖߂�l == true�j�̏ꍇ
     * �\�񒍕��P��.���Ϗ����敪 == "�����_��"�̏ꍇ
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
     * ���Δ����iis���Δ������()�̖߂�l == true�j�̏ꍇ
     * �\�񒍕��P��.���Ϗ����敪 != "�����_��"�̏ꍇ
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
     * ���Δ����iis���Δ������()�̖߂�l == false�j�̏ꍇ���Δ����łȂ��ꍇ
     * ���ʖ���[] == null
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
     * ���Δ����iis���Δ������()�̖߂�l == false�j�̏ꍇ���Δ����łȂ��ꍇ
     * �敨OP����.���ʐ��� < �����Ώۂ̗v�f.�ԍϒ������ʂ̏ꍇ�Anull��ԋp���ďI������B
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
     * ���Δ����iis���Δ������()�̖߂�l == false�j�̏ꍇ���Δ����łȂ��ꍇ
     * �敨OP����.���ʐ��� > �����Ώۂ̗v�f.�ԍϒ������ʂ̏ꍇ
     * �v���p�e�B�Z�b�g
     * �ԉ񌚋ʖ���[]���x��1
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
            //ID = ����.����ID
            assertEquals("1001", l_units[0].id);
            //���� = ����.����
            assertEquals("20040702", WEB3DateUtility.formatDate(l_units[0].openDate, "yyyyMMdd"));
            //���ʐ� = ����.���ʐ���
            assertEquals("200", l_units[0].contractQuantity);
            //���P�� = ����.���P��
            assertEquals("3720", l_units[0].contractPrice);
            //�������z = get��������̖߂�l
            assertEquals("5580000000", l_units[0].contractExecPrice);
            //���萔�� = ����.get���萔��()�̖߂�l + ����.get���萔�������()�̖߂�l
            assertEquals("1575", l_units[0].contractCommission);
            //���v = ����.get�]�����v()�̖߂�l
            assertEquals("-5568000000", l_units[0].income);
            //���v�i���o��j = ����.get�]�����v() - �i����.get���萔��() + ����.get���萔�������())
            assertEquals("-5568001575", l_units[0].incomeCost);
            //�ԍϐ��� = �����Ώۂ̗v�f.�ԍϒ�������
            assertEquals("150", l_units[0].contractOrderQuantity);
            //�ԍϖ�萔�� = null
            assertNull(l_units[0].contractExecQuantity);
            //���Ϗ��� = �����Ώۂ̗v�f.�ԍϘA��
            assertEquals("5", l_units[0].settlePriority);
            //����敪 = ����.����
            assertNull(l_units[0].sessionType);
        }
        catch (Exception l_ex)
        {
            log.error(ERROR + l_ex.getMessage(), l_ex);
            fail();
        }
    }
    
    /**
     * ���Δ����iis���Δ������()�̖߂�l == false�j�̏ꍇ���Δ����łȂ��ꍇ
     * �敨OP����.���ʐ��� < �����Ώۂ̗v�f.�ԍϒ������ʂ̏ꍇ�Anull��ԋp���ďI������B
     * �敨OP�\�񌚋ʕԍώw����ꗗ���x��3
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
            //ID = ����.����ID
            assertEquals("1001", l_units[0].id);
            //ID = ����.����ID
            assertEquals("1002", l_units[1].id);
            //ID = ����.����ID
            assertEquals("1003", l_units[2].id);
        }
        catch (Exception l_ex)
        {
            log.error(ERROR + l_ex.getMessage(), l_ex);
            fail();
        }
    }
    
//    /**
//     * set������To�敨OP�\�񒍕��P��
//     * �w�肳�ꂽ�敨OP�\�񒍕��P�ʃI�u�W�F�N�g���A�����ς̏�ԂɍX�V����B
//         DB�X�V�d�l �u�A�����������iOK�j_�敨OP�\�񒍕��P�ʃe�[�u��.xls�v���Q�ƁB
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
     * �p�����[�^.�����P�� == �敨OP�\�񒍕��P��Impl���A 
     * �@@�@@�@@�@@�[��O�����J�z�iget����敪() == �h�[��h�j�̏ꍇ
     * �i*1�j�����o�^�����������蒍��  
     * �@@�@@�@@�@@�p�����[�^.�敨OP�\�񒍕��P��Impl.get���������敪() == �h��������h���A  
     * �@@�@@�@@�@@�p�����[�^.�敨OP�\�񒍕��P��Impl.����敪 == null 
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
    
    //�[��O�����J�z�iget����敪() != �h�[��h�j
    //��L�ȊO�̏ꍇ ����.�����P��.���������� <= �Ɩ����t
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
    
    //�p�����[�^.�敨OP�\�񒍕��P��Impl.����敪 != null
    //����.�����P��.���������� > �Ɩ����t
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

    //submit�敨OP�V�K���J�z�\�񒍕�
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
            
            //�����P�ʂh�c        null
            assertTrue(l_row.getOrderUnitIdIsNull());
            //�����h�c          �擾��������ID�i�����̔ԁj
            assertEquals(1001, l_row.getOrderId());
            //���������ŏI�ʔ�      1
            assertEquals(1, l_row.getLastOrderActionSerialNo());
            //�������          "1:��t�ρi�V�K�����j�iOrderStatusEnum�ɂĒ�`�j"
            assertEquals(OrderStatusEnum.ACCEPTED, l_row.getOrderStatus());
            //�����L�����        "1:�I�[�v���iOrderOpenStatusEnum�ɂĒ�`�j"
            assertEquals(OrderOpenStatusEnum.OPEN, l_row.getOrderOpenStatus());
            //�����敪          "1:�I�[�v���iOrderExpirationStatusEnum�ɂĒ�`�j"
            assertEquals(OrderExpirationStatusEnum.OPEN, l_row.getExpirationStatus());
            //�����G���[���R�R�[�h        0000�F����
            assertEquals("0000", l_row.getErrorReasonCode());
            //���񒍕��̒����h�c     "�J�z���\�񒍕��P��.���񒍕��̒����h�c = null �̏ꍇ�A
            //�J�z���\�񒍕��P��.�����h�c����ȊO�̏ꍇ�A�J�z���\�񒍕��P�ʂ̓�����"
            assertEquals(1001, l_row.getFirstOrderId());
            //�����G���[�R�[�h      null
            assertNull(l_row.getOrderErrorCode());
            //�e�����̒����h�c      ����.�J�z��̐e�����P��.����ID
            assertEquals(1001, l_row.getParentOrderId());
            //�e�����̒����P�ʂh�c    ����.�J�z��̐e�����P��.�����P��ID
            assertEquals(1001, l_row.getParentOrderUnitId());
            //����敪          ������ԊǗ�.get����敪()
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
            
            //�����P�ʂh�c        null
            assertTrue(l_row.getOrderUnitIdIsNull());
            //�����h�c          �擾��������ID�i�����̔ԁj
            assertEquals(1001, l_row.getOrderId());
            //���������ŏI�ʔ�      1
            assertEquals(1, l_row.getLastOrderActionSerialNo());
            //�������          "1:��t�ρi�V�K�����j�iOrderStatusEnum�ɂĒ�`�j"
            assertEquals(OrderStatusEnum.ACCEPTED, l_row.getOrderStatus());
            //�����L�����        "1:�I�[�v���iOrderOpenStatusEnum�ɂĒ�`�j"
            assertEquals(OrderOpenStatusEnum.OPEN, l_row.getOrderOpenStatus());
            //�����敪          "1:�I�[�v���iOrderExpirationStatusEnum�ɂĒ�`�j"
            assertEquals(OrderExpirationStatusEnum.OPEN, l_row.getExpirationStatus());
            //�����G���[���R�R�[�h        0000�F����
            assertEquals("0000", l_row.getErrorReasonCode());
            //���񒍕��̒����h�c     "�J�z���\�񒍕��P��.���񒍕��̒����h�c = null �̏ꍇ�A
            //�J�z���\�񒍕��P��.�����h�c����ȊO�̏ꍇ�A�J�z���\�񒍕��P�ʂ̓�����"
            assertEquals(1002, l_row.getFirstOrderId());
            //�����G���[�R�[�h      null
            assertNull(l_row.getOrderErrorCode());
            //�e�����̒����h�c      ����.�J�z��̐e�����P��.����ID
            assertEquals(1001, l_row.getParentOrderId());
            //�e�����̒����P�ʂh�c    ����.�J�z��̐e�����P��.�����P��ID
            assertEquals(1001, l_row.getParentOrderUnitId());
            //����敪          ������ԊǗ�.get����敪()
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
    
    
    //submit�敨OP�ԍόJ�z�\�񒍕�
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
            
            //�����P�ʂh�c        null
            assertTrue(l_row.getOrderUnitIdIsNull());
            //�����h�c          �擾��������ID�i�����̔ԁj
            assertEquals(1001, l_row.getOrderId());
            //���������ŏI�ʔ�      1
            assertEquals(1, l_row.getLastOrderActionSerialNo());
            //�������          "1:��t�ρi�V�K�����j�iOrderStatusEnum�ɂĒ�`�j"
            assertEquals(OrderStatusEnum.ACCEPTED, l_row.getOrderStatus());
            //�����L�����        "1:�I�[�v���iOrderOpenStatusEnum�ɂĒ�`�j"
            assertEquals(OrderOpenStatusEnum.OPEN, l_row.getOrderOpenStatus());
            //�����敪          "1:�I�[�v���iOrderExpirationStatusEnum�ɂĒ�`�j"
            assertEquals(OrderExpirationStatusEnum.OPEN, l_row.getExpirationStatus());
            //�����G���[���R�R�[�h        0000�F����
            assertEquals("0000", l_row.getErrorReasonCode());
            //���񒍕��̒����h�c     "�J�z���\�񒍕��P��.���񒍕��̒����h�c = null �̏ꍇ�A
            //�J�z���\�񒍕��P��.�����h�c����ȊO�̏ꍇ�A�J�z���\�񒍕��P�ʂ̓�����"
            assertEquals(1002, l_row.getFirstOrderId());
            //�����G���[�R�[�h      null
            assertNull(l_row.getOrderErrorCode());
            //�e�����̒����h�c      ����.�J�z��̐e�����P��.����ID
            assertEquals(1001, l_row.getParentOrderId());
            //�e�����̒����P�ʂh�c    ����.�J�z��̐e�����P��.�����P��ID
            assertEquals(1001, l_row.getParentOrderUnitId());
            //����敪          ������ԊǗ�.get����敪()
            assertEquals("1", l_row.getSessionType());
            
            RsvIfoClosingContractSpecRow l_contractRow =
                RsvIfoClosingContractSpecDao.findRowByOrderIdContractId(1001, 12345);
            //�����̌J�z���\�񒍕��P��.����ID      
            assertEquals(333812512203L, l_contractRow.getAccountId());
            //�����̌J�z���\�񒍕��P��.�⏕����ID          
            assertEquals(33381251220301L, l_contractRow.getSubAccountId());
            //�����̒���ID             
            assertEquals(1001L, l_contractRow.getOrderId());
            //�����̕ԍό��ʃG���g��[index].getContractId()     
            assertEquals(12345, l_contractRow.getContractId());
            //index + 1  
            assertEquals(1, l_contractRow.getClosingSerialNo());
            //�����̕ԍό��ʃG���g��[index].getQuantity()
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
            
            //�����P�ʂh�c        null
            assertTrue(l_row.getOrderUnitIdIsNull());
            //�����h�c          �擾��������ID�i�����̔ԁj
            assertEquals(1001, l_row.getOrderId());
            //���������ŏI�ʔ�      1
            assertEquals(1, l_row.getLastOrderActionSerialNo());
            //�������          "1:��t�ρi�V�K�����j�iOrderStatusEnum�ɂĒ�`�j"
            assertEquals(OrderStatusEnum.ACCEPTED, l_row.getOrderStatus());
            //�����L�����        "1:�I�[�v���iOrderOpenStatusEnum�ɂĒ�`�j"
            assertEquals(OrderOpenStatusEnum.OPEN, l_row.getOrderOpenStatus());
            //�����敪          "1:�I�[�v���iOrderExpirationStatusEnum�ɂĒ�`�j"
            assertEquals(OrderExpirationStatusEnum.OPEN, l_row.getExpirationStatus());
            //�����G���[���R�R�[�h        0000�F����
            assertEquals("0000", l_row.getErrorReasonCode());
            //���񒍕��̒����h�c     "�J�z���\�񒍕��P��.���񒍕��̒����h�c = null �̏ꍇ�A
            //�J�z���\�񒍕��P��.�����h�c����ȊO�̏ꍇ�A�J�z���\�񒍕��P�ʂ̓�����"
            assertEquals(1002, l_row.getFirstOrderId());
            //�����G���[�R�[�h      null
            assertNull(l_row.getOrderErrorCode());
            //�e�����̒����h�c      ����.�J�z��̐e�����P��.����ID
            assertEquals(1001, l_row.getParentOrderId());
            //�e�����̒����P�ʂh�c    ����.�J�z��̐e�����P��.�����P��ID
            assertEquals(1001, l_row.getParentOrderUnitId());
            //����敪          ������ԊǗ�.get����敪()
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
    
    //validate�[��܂Œ��������\
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
