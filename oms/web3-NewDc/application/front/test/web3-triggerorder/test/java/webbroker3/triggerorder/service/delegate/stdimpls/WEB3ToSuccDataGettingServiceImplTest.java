head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.04.07.01.19.15;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	5444d9d078c63f4;
filename	WEB3ToSuccDataGettingServiceImplTest.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : 連続注文データ取得サービスImplテスト(WEB3ToSuccDataGettingServiceImplTest.java)
Author Name      : Daiwa Institute of Research
Revision History : 2007/03/06 趙林鵬 (中訊) 新規作成
*/

package webbroker3.triggerorder.service.delegate.stdimpls;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.Hashtable;

import com.fitechlabs.dbind.Row;
import com.fitechlabs.xtrade.kernel.boot.Services;
import com.fitechlabs.xtrade.kernel.data.DataFindException;
import com.fitechlabs.xtrade.kernel.data.DataNetworkException;
import com.fitechlabs.xtrade.kernel.data.DataQueryException;
import com.fitechlabs.xtrade.kernel.util.ThreadLocalSystemAttributesRegistry;
import com.fitechlabs.xtrade.plugin.tc.eqtype.EqTypeExecutionConditionType;
import com.fitechlabs.xtrade.plugin.tc.eqtype.EqTypeOrderUnit;
import com.fitechlabs.xtrade.plugin.tc.eqtype.data.EqtypeOrderParams;
import com.fitechlabs.xtrade.plugin.tc.eqtype.data.EqtypeOrderUnitParams;
import com.fitechlabs.xtrade.plugin.tc.eqtype.data.EqtypeProductParams;
import com.fitechlabs.xtrade.plugin.tc.eqtype.data.EqtypeProductRow;
import com.fitechlabs.xtrade.plugin.tc.eqtype.stdimpls.EqTypeOrderUnitImpl;
import com.fitechlabs.xtrade.plugin.tc.gentrade.FinApp;
import com.fitechlabs.xtrade.plugin.tc.gentrade.GtlUtils;
import com.fitechlabs.xtrade.plugin.tc.gentrade.Institution;
import com.fitechlabs.xtrade.plugin.tc.gentrade.Market;
import com.fitechlabs.xtrade.plugin.tc.gentrade.Order;
import com.fitechlabs.xtrade.plugin.tc.gentrade.OrderAction;
import com.fitechlabs.xtrade.plugin.tc.gentrade.OrderCategEnum;
import com.fitechlabs.xtrade.plugin.tc.gentrade.OrderExecution;
import com.fitechlabs.xtrade.plugin.tc.gentrade.OrderExpirationStatusEnum;
import com.fitechlabs.xtrade.plugin.tc.gentrade.OrderOpenStatusEnum;
import com.fitechlabs.xtrade.plugin.tc.gentrade.OrderStatusEnum;
import com.fitechlabs.xtrade.plugin.tc.gentrade.OrderTypeEnum;
import com.fitechlabs.xtrade.plugin.tc.gentrade.OrderUnit;
import com.fitechlabs.xtrade.plugin.tc.gentrade.Product;
import com.fitechlabs.xtrade.plugin.tc.gentrade.ProductTypeEnum;
import com.fitechlabs.xtrade.plugin.tc.gentrade.SideEnum;
import com.fitechlabs.xtrade.plugin.tc.gentrade.TaxTypeEnum;
import com.fitechlabs.xtrade.plugin.tc.gentrade.TradedProduct;
import com.fitechlabs.xtrade.plugin.tc.gentrade.TradingModule;
import com.fitechlabs.xtrade.plugin.tc.gentrade.data.BranchParams;
import com.fitechlabs.xtrade.plugin.tc.gentrade.data.InstitutionParams;
import com.fitechlabs.xtrade.plugin.tc.gentrade.data.MarketParams;
import com.fitechlabs.xtrade.plugin.tc.gentrade.data.MarketRow;
import com.fitechlabs.xtrade.plugin.tc.gentrade.data.ProductParams;
import com.fitechlabs.xtrade.plugin.tc.gentrade.data.ProductRow;
import com.fitechlabs.xtrade.plugin.tc.gentrade.data.SubAccountParams;
import com.fitechlabs.xtrade.plugin.tc.gentrade.data.TradedProductParams;
import com.fitechlabs.xtrade.plugin.tc.xbifo.IfoDerivativeTypeEnum;
import com.fitechlabs.xtrade.plugin.tc.xbifo.IfoOrderExecutionConditionType;
import com.fitechlabs.xtrade.plugin.tc.xbifo.IfoOrderUnit;
import com.fitechlabs.xtrade.plugin.tc.xbifo.data.IfoOrderParams;
import com.fitechlabs.xtrade.plugin.tc.xbifo.data.IfoOrderUnitParams;
import com.fitechlabs.xtrade.plugin.tc.xbifo.data.IfoOrderUnitRow;
import com.fitechlabs.xtrade.plugin.tc.xbifo.data.IfoProductParams;
import com.fitechlabs.xtrade.plugin.tc.xbifo.data.IfoTradedProductParams;
import com.fitechlabs.xtrade.plugin.tc.xbifo.stdimpls.IfoContractOpenOrderUnitImpl;
import com.fitechlabs.xtrade.plugin.tc.xbifo.stdimpls.IfoContractSettleOrderUnitImpl;

import test.util.TestDBUtility;

import webbroker3.common.WEB3BaseException;
import webbroker3.equity.WEB3EquityOrderManager;
import webbroker3.equity.WEB3EquityOrderManagerForMock;
import webbroker3.gentrade.WEB3GentradeTradingTimeManagementForMock;
import webbroker3.gentrade.data.BranchPreferencesParams;
import webbroker3.gentrade.data.OrderexecutionEndParams;
import webbroker3.gentrade.data.TradingTimeParams;
import webbroker3.ifo.WEB3FuturesOrderManagerImpl;
import webbroker3.ifo.WEB3IfoProductImpl;
import webbroker3.mock.TestBaseForMock;
import webbroker3.triggerorder.WEB3ToSuccEqTypeOrderUnitImpl;
import webbroker3.triggerorder.WEB3ToSuccIfoOrderUnitImpl;
import webbroker3.triggerorder.WEB3ToSuccOrderManagerImpl;
import webbroker3.triggerorder.base.data.RsvEqOrderUnitParams;
import webbroker3.triggerorder.base.data.RsvEqOrderUnitRow;
import webbroker3.triggerorder.base.data.RsvIfoOrderUnitParams;
import webbroker3.triggerorder.base.data.RsvIfoOrderUnitRow;
import webbroker3.triggerorder.message.WEB3SuccOrderUnit;
import webbroker3.triggerorder.util.WEB3ToSuccOrderManagerUtility;
import webbroker3.triggerorder.util.WEB3TriggerOrderTradingModel;
import webbroker3.util.WEB3DateUtility;
import webbroker3.util.WEB3LogUtility;

/**
 * （連続注文データ取得サービスImplテスト）<BR>
 *
 * @@author 趙林鵬
 * @@version 1.0
 */

public class WEB3ToSuccDataGettingServiceImplTest extends TestBaseForMock
{
    
    /**
     * ログユーティリティ<BR>
     */
    private static WEB3LogUtility log =
         WEB3LogUtility.getInstance(WEB3ToSuccDataGettingServiceImplTest.class);
    
    private String l_strFutureOptionDiv = null;
    
    private boolean l_blnIsPutOption = true;

    public WEB3ToSuccDataGettingServiceImplTest(String arg0)
    {
        super(arg0);
    }

    protected void setUp() throws Exception
    {
        TestDBUtility.deleteAll(RsvIfoOrderUnitParams.TYPE);
        TestDBUtility.deleteAll(EqtypeOrderUnitParams.TYPE);
        TestDBUtility.deleteAll(IfoOrderUnitParams.TYPE);
        TestDBUtility.deleteAll(RsvEqOrderUnitParams.TYPE);
        TestDBUtility.deleteAll(BranchParams.TYPE);
        TestDBUtility.deleteAll(EqtypeOrderParams.TYPE);
        TestDBUtility.deleteAll(InstitutionParams.TYPE);
        TestDBUtility.deleteAll(OrderexecutionEndParams.TYPE);
        TestDBUtility.deleteAll(TradingTimeParams.TYPE);
        TestDBUtility.deleteAll(ProductParams.TYPE);
        TestDBUtility.deleteAll(IfoProductParams.TYPE);
        TestDBUtility.deleteAll(IfoOrderParams.TYPE);
        TestDBUtility.deleteAll(SubAccountParams.TYPE);
        TestDBUtility.deleteAll(MarketParams.TYPE);
        TestDBUtility.deleteAll(TradedProductParams.TYPE);
        TestDBUtility.deleteAll(IfoTradedProductParams.TYPE);
        
        
        super.setUp();
    }

    protected void tearDown() throws Exception
    {
        super.tearDown();
    }

    public void test_createSuccOrderUnit_case0001()
    {
        final String STR_METHOD_NAME = " test_createSuccOrderUnit_case0001";
        log.entering(TEST_START + STR_METHOD_NAME);

        try
        {
            TestDBUtility.deleteAll(RsvEqOrderUnitRow.TYPE);
            RsvEqOrderUnitParams l_rsvEqOrderUnitParams = TestDBUtility.getRsvEqOrderUnitRow();
            l_rsvEqOrderUnitParams.setOrderId(1001L);
            l_rsvEqOrderUnitParams.setPriceAdjustValue(null);
            l_rsvEqOrderUnitParams.setOrderCateg(OrderCategEnum.SWAP_MARGIN);
            l_rsvEqOrderUnitParams.setLimitPrice(200);
            l_rsvEqOrderUnitParams.setMarketId(3303);
            TestDBUtility.insertWithDel(l_rsvEqOrderUnitParams);

            TestDBUtility.deleteAll(ProductRow.TYPE);
            ProductParams l_productParams = TestDBUtility.getProductRow();
            l_productParams.setProductId(1006169090018L);
            l_productParams.setProductType(ProductTypeEnum.EQUITY);
            TestDBUtility.insertWithDel(l_productParams);

            TestDBUtility.deleteAll(EqtypeProductRow.TYPE);
            EqtypeProductParams l_eqtypeProductParams = TestDBUtility.getEqtypeProductRow();
            l_eqtypeProductParams.setProductId(1006169090018L);
            l_eqtypeProductParams.setStandardName("ss");
            TestDBUtility.insertWithDel(l_eqtypeProductParams);
            
            TestDBUtility.deleteAll(MarketRow.TYPE);
            MarketParams l_marketParams = TestDBUtility.getMarketRow();
            TestDBUtility.insertWithDel(l_marketParams);

            WEB3ToSuccOrderManagerImpl l_toOrderManager = 
                (WEB3ToSuccOrderManagerImpl)WEB3TriggerOrderTradingModel.getOrderManager();
            long l_lngOrderId = 1001L;

            WEB3SuccOrderUnit l_succOrderUnit = new WEB3SuccOrderUnit();

            boolean l_blnIsPossibleFlagSet = false;

            OrderUnit l_orderUnit = (WEB3ToSuccEqTypeOrderUnitImpl)l_toOrderManager.getReserveEqtypeOrderUnit(l_lngOrderId);
            WEB3ToSuccDataGettingServiceImpl l_impl = new WEB3ToSuccDataGettingServiceImpl();

            l_impl.createSuccOrderUnit(l_succOrderUnit, l_orderUnit, l_blnIsPossibleFlagSet);

            assertEquals("1", l_succOrderUnit.orderPriceDiv);
            assertEquals("200", l_succOrderUnit.orderPrice);
        }
        catch (Exception l_ex)
        {
            log.error(l_ex.getMessage(), l_ex);
            fail();
        }
        log.info(STR_METHOD_NAME + ">>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>test pass !!");
        log.exiting(TEST_END + STR_METHOD_NAME);  
    }

    public void test_createSuccOrderUnit_case0002()
    {
        final String STR_METHOD_NAME = " test_createSuccOrderUnit_case0002";
        log.entering(TEST_START + STR_METHOD_NAME);

        try
        {
            TestDBUtility.deleteAll(RsvEqOrderUnitRow.TYPE);
            RsvEqOrderUnitParams l_rsvEqOrderUnitParams = TestDBUtility.getRsvEqOrderUnitRow();
            l_rsvEqOrderUnitParams.setOrderId(1001L);
            l_rsvEqOrderUnitParams.setPriceAdjustValue(null);
            l_rsvEqOrderUnitParams.setOrderCateg(OrderCategEnum.SWAP_MARGIN);
            l_rsvEqOrderUnitParams.setLimitPrice(200);
            l_rsvEqOrderUnitParams.setMarketId(3303);
            l_rsvEqOrderUnitParams.setForcedExpireType("1");
            TestDBUtility.insertWithDel(l_rsvEqOrderUnitParams);
            
            TestDBUtility.deleteAll(ProductRow.TYPE);
            ProductParams l_productParams = TestDBUtility.getProductRow();
            l_productParams.setProductId(1006169090018L);
            l_productParams.setProductType(ProductTypeEnum.EQUITY);
            TestDBUtility.insertWithDel(l_productParams);

            TestDBUtility.deleteAll(EqtypeProductRow.TYPE);
            EqtypeProductParams l_eqtypeProductParams = TestDBUtility.getEqtypeProductRow();
            l_eqtypeProductParams.setProductId(1006169090018L);
            l_eqtypeProductParams.setStandardName("ss");
            TestDBUtility.insertWithDel(l_eqtypeProductParams);
            
            TestDBUtility.deleteAll(MarketRow.TYPE);
            MarketParams l_marketParams = TestDBUtility.getMarketRow();
            TestDBUtility.insertWithDel(l_marketParams);

            WEB3ToSuccOrderManagerImpl l_toOrderManager = 
                (WEB3ToSuccOrderManagerImpl)WEB3TriggerOrderTradingModel.getOrderManager();
            long l_lngOrderId = 1001L;

            WEB3SuccOrderUnit l_succOrderUnit = new WEB3SuccOrderUnit();

            boolean l_blnIsPossibleFlagSet = false;

            OrderUnit l_orderUnit = (WEB3ToSuccEqTypeOrderUnitImpl)l_toOrderManager.getReserveEqtypeOrderUnit(l_lngOrderId);
            WEB3ToSuccDataGettingServiceImpl l_impl = new WEB3ToSuccDataGettingServiceImpl();

            l_impl.createSuccOrderUnit(l_succOrderUnit, l_orderUnit, l_blnIsPossibleFlagSet);

            assertEquals("1", l_succOrderUnit.forcedLapseDiv);
        }
        catch (Exception l_ex)
        {
            log.error(l_ex.getMessage(), l_ex);
            fail();
        }
        log.info(STR_METHOD_NAME + ">>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>test pass !!");
        log.exiting(TEST_END + STR_METHOD_NAME);  
    }

    public void test_createSuccOrderUnit_case0003()
    {
        final String STR_METHOD_NAME = " test_createSuccOrderUnit_case0003";
        log.entering(TEST_START + STR_METHOD_NAME);

        try
        {
            TestDBUtility.deleteAll(EqtypeOrderUnitParams.TYPE);
            EqtypeOrderUnitParams l_eqtypeOrderUnitParams =
                TestDBUtility.getEqtypeOrderUnitRow();
            l_eqtypeOrderUnitParams.setOrderOpenStatus(OrderOpenStatusEnum.OPEN);
            l_eqtypeOrderUnitParams.setOrderCateg(OrderCategEnum.SWAP_MARGIN);
            l_eqtypeOrderUnitParams.setOrderRequestNumber("1");
            l_eqtypeOrderUnitParams.setSonarTradedCode("12");
            l_eqtypeOrderUnitParams.setTraderId(null);
            l_eqtypeOrderUnitParams.setForcedSettleReasonType("2");
            l_eqtypeOrderUnitParams.setForcedExpireType("1");
            l_eqtypeOrderUnitParams.setOrderStatus(OrderStatusEnum.ACCEPTED);
            l_eqtypeOrderUnitParams.setOrderConditionType("2");
            l_eqtypeOrderUnitParams.setWLimitExecCondType(EqTypeExecutionConditionType.AT_MARKET_OPEN);
            l_eqtypeOrderUnitParams.setExecutionConditionType(EqTypeExecutionConditionType.AT_MARKET_CLOSE);
            TestDBUtility.insertWithDel(l_eqtypeOrderUnitParams);
            
            //ProductParams
            TestDBUtility.deleteAll(ProductParams.TYPE);
            ProductParams l_productParams = TestDBUtility.getProductRow();
            l_productParams.setProductId(l_eqtypeOrderUnitParams.getProductId());
            l_productParams.setProductType(ProductTypeEnum.EQUITY);
            TestDBUtility.insertWithDel(l_productParams);

            //EqtypeProductParams
            TestDBUtility.deleteAll(EqtypeProductParams.TYPE);
            EqtypeProductParams l_eqtypeProductParams =
                TestDBUtility.getEqtypeProductRow();
            l_eqtypeProductParams.setProductId(l_eqtypeOrderUnitParams.getProductId());
            TestDBUtility.insertWithDel(l_eqtypeProductParams);
            
            //MarketParams
            TestDBUtility.deleteAll(MarketParams.TYPE);
            MarketParams l_marketParams = TestDBUtility.getMarketRow();
            l_marketParams.setMarketId(l_eqtypeOrderUnitParams.getMarketId());
            TestDBUtility.insertWithDel(l_marketParams);
            
            FinApp l_finApp = (FinApp)Services.getService(FinApp.class);
            TradingModule l_tradingModule = l_finApp.getTradingModule(ProductTypeEnum.EQUITY);
            WEB3EquityOrderManager l_orderMgr =
                (WEB3EquityOrderManager)l_tradingModule.getOrderManager();
            EqTypeOrderUnit l_eqTypeOrderUnit =
                (EqTypeOrderUnit)l_orderMgr.getOrderUnit(l_eqtypeOrderUnitParams.getOrderUnitId());
            WEB3ToSuccDataGettingServiceImplForMock l_impl = new WEB3ToSuccDataGettingServiceImplForMock();

            WEB3SuccOrderUnit l_succOrderUnit = new WEB3SuccOrderUnit();
            boolean l_blnIsPossibleFlagSet = false;
            l_impl.createSuccOrderUnit(l_succOrderUnit, l_eqTypeOrderUnit, l_blnIsPossibleFlagSet);

            assertEquals("3", l_succOrderUnit.wlimitExecCondType);
            assertEquals("1", l_succOrderUnit.forcedLapseDiv);
            assertEquals("2", l_succOrderUnit.forcedSettleReason);
        }
        catch (Exception l_ex)
        {
            log.error(l_ex.getMessage(), l_ex);
            fail();
        }
        log.info(STR_METHOD_NAME + ">>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>test pass !!");
        log.exiting(TEST_END + STR_METHOD_NAME);  
    }

    public void test_createSuccOrderUnit_case0004()
    {
        final String STR_METHOD_NAME = " test_createSuccOrderUnit_case0004";
        log.entering(TEST_START + STR_METHOD_NAME);

        try
        {
            TestDBUtility.deleteAll(IfoOrderUnitParams.TYPE);
            IfoOrderUnitParams l_ifoOrderUnitParams =
                TestDBUtility.getIfoOrderUnitRow();
            l_ifoOrderUnitParams.setOrderConditionType("2");
            l_ifoOrderUnitParams.setWLimitExecCondType(IfoOrderExecutionConditionType.AT_MARKET_OPEN);
            TestDBUtility.insertWithDel(l_ifoOrderUnitParams);
            
            //ProductParams
            TestDBUtility.deleteAll(ProductParams.TYPE);
            ProductParams l_productParams = TestDBUtility.getProductRow();
            l_productParams.setProductId(l_ifoOrderUnitParams.getProductId());
            l_productParams.setProductType(ProductTypeEnum.IFO);
            TestDBUtility.insertWithDel(l_productParams);

            //EqtypeProductParams
            TestDBUtility.deleteAll(IfoProductParams.TYPE);
            IfoProductParams l_ifoProductParams =
                TestDBUtility.getIfoProductRow();
            l_ifoProductParams.setProductId(l_ifoOrderUnitParams.getProductId());
            l_ifoProductParams.setProductType(ProductTypeEnum.IFO);
            TestDBUtility.insertWithDel(l_ifoProductParams);
            
            //MarketParams
            TestDBUtility.deleteAll(MarketParams.TYPE);
            MarketParams l_marketParams = TestDBUtility.getMarketRow();
            l_marketParams.setMarketId(l_ifoOrderUnitParams.getMarketId());
            TestDBUtility.insertWithDel(l_marketParams);

            FinApp l_finApp = (FinApp)Services.getService(FinApp.class);
            TradingModule l_tradingModule = l_finApp.getTradingModule(ProductTypeEnum.IFO);
            WEB3FuturesOrderManagerImpl l_futuresOrderManager =
                (WEB3FuturesOrderManagerImpl)l_tradingModule.getOrderManager();
            IfoOrderUnit l_ifoOrderUnit =
                (IfoOrderUnit)l_futuresOrderManager.getOrderUnit(l_ifoOrderUnitParams.getOrderUnitId());
            WEB3ToSuccDataGettingServiceImplForMock l_impl = new WEB3ToSuccDataGettingServiceImplForMock();

            WEB3SuccOrderUnit l_succOrderUnit = new WEB3SuccOrderUnit();
            boolean l_blnIsPossibleFlagSet = false;
            l_impl.createSuccOrderUnit(l_succOrderUnit, l_ifoOrderUnit, l_blnIsPossibleFlagSet);
            assertEquals("3", l_succOrderUnit.wlimitExecCondType);
            assertNull(l_succOrderUnit.forcedLapseDiv);
            assertNull(l_succOrderUnit.forcedSettleReason);
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
     * 先物OP予約注文単位Implの場合且つ先物OP注文単位.先物／オプション区分 = "オプション"の場合
     * 「先物OP注文単位.先物オプション商品」＝ プットオプション
     * 注文有効期限 = 注文単位.初回注文の注文単位ID != nullの場合
     *
     */
    public void testCreateSuccOrderUnit_C0001()
    {
        final String STR_METHOD_NAME = " testCreateSuccOrderUnit_C0001";
        log.entering(TEST_START + STR_METHOD_NAME);
        try
        {
            MOCK_MANAGER.setIsMockUsed(true);
            // OPTION
//            this.l_strFutureOptionDiv = "2";

            WEB3ToSuccOrderManagerUtility.changeOrderManager();
            
//            WEB3GentradeTradingTimeManagement
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                    "webbroker3.triggerorder.WEB3ToSuccOrderManagerImpl", 
                    "createIfoOrderUnit", 
                    new Class[]{WEB3ToSuccIfoOrderUnitImpl.class},
                    new IfoOrderUnitForTest());
            
            TestDBUtility.deleteAll(ProductParams.TYPE);
            ProductParams l_productParams = TestDBUtility.getProductRow();
            l_productParams.setProductId(3304148080000L);
            l_productParams.setProductType(ProductTypeEnum.IFO);
            TestDBUtility.insertWithDel(l_productParams);

            TradingTimeParams l_tradingTimeParams = TestDBUtility.getTradingTimeRow();
            l_tradingTimeParams.setInstitutionCode("0D");
            l_tradingTimeParams.setBranchCode("123");
            l_tradingTimeParams.setMarketCode("N1");
            l_tradingTimeParams.setTradingTimeType("01");
            l_tradingTimeParams.setProductCode("0");
            l_tradingTimeParams.setBizDateType("1");
            l_tradingTimeParams.setBizdateCalcParameter("0");
            l_tradingTimeParams.setSubmitMarketTrigger("1");
            l_tradingTimeParams.setEndTime("160001");
            l_tradingTimeParams.setStartTime("00");
            TestDBUtility.insertWithDel(l_tradingTimeParams);
            
            TestDBUtility.deleteAll(IfoProductParams.TYPE);
            IfoProductParams l_ifoProductParams =
                TestDBUtility.getIfoProductRow();
            l_ifoProductParams.setProductId(3304148080000L);
            l_ifoProductParams.setProductType(ProductTypeEnum.IFO);
            
            TestDBUtility.insertWithDel(l_ifoProductParams);
            
            TradedProductParams l_tradedProductParams = TestDBUtility.getTradedProductRow();
            l_tradedProductParams.setTradedProductId(1006160060005L);
            l_tradedProductParams.setInstitutionCode("0D");
            l_tradedProductParams.setProductId(3304148080000L);
            l_tradedProductParams.setProductType(ProductTypeEnum.IFO);
           
            TestDBUtility.insertWithDel(l_tradedProductParams);
            
            IfoTradedProductParams l_ifoTradedProductParams = TestDBUtility.getIfoTradedProductRow();
            l_ifoTradedProductParams.setTradedProductId(1006160060005L);
            l_ifoTradedProductParams.setInstitutionCode("0D");
            l_ifoTradedProductParams.setProductId(3304148080000L);
            l_ifoTradedProductParams.setValidForBizDate(null);
            TestDBUtility.insertWithDel(l_ifoTradedProductParams);
            
            //MarketParams
            TestDBUtility.deleteAll(MarketParams.TYPE);
            MarketParams l_marketParams = TestDBUtility.getMarketRow();
            TestDBUtility.insertWithDel(l_marketParams);
            
            TestDBUtility.deleteAll(IfoOrderUnitParams.TYPE);
            IfoOrderUnitParams l_ifoOrderUnitParams =
                TestDBUtility.getIfoOrderUnitRow();
            l_ifoOrderUnitParams.setProductId(3304148080000L);
            TestDBUtility.insertWithDel(l_ifoOrderUnitParams);
            
            WEB3SuccOrderUnit l_succOrderUnit = new WEB3SuccOrderUnit();
            
            RsvIfoOrderUnitParams l_rsvIfoOrderUnitParams = TestDBUtility.getRsvIfoOrderUnitRow();
            l_rsvIfoOrderUnitParams.setMarketId(3303L);
            l_rsvIfoOrderUnitParams.setProductId(3304148080000L);
            l_rsvIfoOrderUnitParams.setSessionType("2");
            l_rsvIfoOrderUnitParams.setFutureOptionDiv("2");
            
            WEB3ToSuccIfoOrderUnitImpl l_toSuccIfoOrderUnitImpl = 
                new WEB3ToSuccIfoOrderUnitImpl(l_rsvIfoOrderUnitParams);
            
            WEB3ToSuccDataGettingServiceImpl l_service =
                new WEB3ToSuccDataGettingServiceImplForTest();
            l_service.createSuccOrderUnit(
                l_succOrderUnit, 
                l_toSuccIfoOrderUnitImpl,
                false);
            
            assertEquals("P",l_succOrderUnit.opProductType);
            assertTrue(l_succOrderUnit.eveningSessionCarryoverFlag);
            assertEquals("2",l_succOrderUnit.sessionType);
            
        }
        catch (Exception l_ex)
        {
            log.error(l_ex.getMessage(), l_ex);
            fail();
        }
        log.exiting(TEST_END + STR_METHOD_NAME);
    }
    
    /**
     * 先物OP予約注文単位Implの場合且つ先物OP注文単位.先物／オプション区分 = "オプション"の場合
     * 「先物OP注文単位.先物オプション商品」＝ コールオプション
     * 注文有効期限 = 注文単位.初回注文の注文単位ID != nullの場合
     *
     */
    public void testCreateSuccOrderUnit_C0002()
    {
        final String STR_METHOD_NAME = " testCreateSuccOrderUnit_C0002";
        log.entering(TEST_START + STR_METHOD_NAME);
        try
        {
            MOCK_MANAGER.setIsMockUsed(true);
            // OPTION
//            this.l_strFutureOptionDiv = "2";
            this.l_blnIsPutOption = false;

            WEB3ToSuccOrderManagerUtility.changeOrderManager();
            
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                    "webbroker3.triggerorder.WEB3ToSuccOrderManagerImpl", 
                    "createIfoOrderUnit", 
                    new Class[]{WEB3ToSuccIfoOrderUnitImpl.class},
                    new IfoOrderUnitForTest());
            
            TestDBUtility.deleteAll(ProductParams.TYPE);
            ProductParams l_productParams = TestDBUtility.getProductRow();
            l_productParams.setProductId(3304148080000L);
            l_productParams.setProductType(ProductTypeEnum.IFO);
            TestDBUtility.insertWithDel(l_productParams);


            TestDBUtility.deleteAll(IfoProductParams.TYPE);
            IfoProductParams l_ifoProductParams =
                TestDBUtility.getIfoProductRow();
            l_ifoProductParams.setProductId(3304148080000L);
            l_ifoProductParams.setProductType(ProductTypeEnum.IFO);
            
            TestDBUtility.insertWithDel(l_ifoProductParams);
            
            //MarketParams
            TestDBUtility.deleteAll(MarketParams.TYPE);
            MarketParams l_marketParams = TestDBUtility.getMarketRow();
            TestDBUtility.insertWithDel(l_marketParams);
            
            TestDBUtility.deleteAll(IfoOrderUnitParams.TYPE);
            IfoOrderUnitParams l_ifoOrderUnitParams =
                TestDBUtility.getIfoOrderUnitRow();
            l_ifoOrderUnitParams.setProductId(3304148080000L);
            TestDBUtility.insertWithDel(l_ifoOrderUnitParams);
            
            WEB3SuccOrderUnit l_succOrderUnit = new WEB3SuccOrderUnit();
            
            TradedProductParams l_tradedProductParams = TestDBUtility.getTradedProductRow();
            l_tradedProductParams.setTradedProductId(1006160060005L);
            l_tradedProductParams.setInstitutionCode("0D");
            l_tradedProductParams.setProductId(3304148080000L);
            l_tradedProductParams.setProductType(ProductTypeEnum.IFO);
           
            TestDBUtility.insertWithDel(l_tradedProductParams);
            
            TradingTimeParams l_tradingTimeParams = TestDBUtility.getTradingTimeRow();
            l_tradingTimeParams.setInstitutionCode("0D");
            l_tradingTimeParams.setBranchCode("123");
            l_tradingTimeParams.setMarketCode("N1");
            l_tradingTimeParams.setTradingTimeType("01");
            l_tradingTimeParams.setProductCode("0");
            l_tradingTimeParams.setBizDateType("1");
            l_tradingTimeParams.setBizdateCalcParameter("0");
            l_tradingTimeParams.setSubmitMarketTrigger("1");
            l_tradingTimeParams.setEndTime("160001");
            l_tradingTimeParams.setStartTime("00");
            TestDBUtility.insertWithDel(l_tradingTimeParams);
            
            IfoTradedProductParams l_ifoTradedProductParams = TestDBUtility.getIfoTradedProductRow();
            l_ifoTradedProductParams.setTradedProductId(1006160060005L);
            l_ifoTradedProductParams.setInstitutionCode("0D");
            l_ifoTradedProductParams.setProductId(3304148080000L);
            l_ifoTradedProductParams.setValidForBizDate(null);
            TestDBUtility.insertWithDel(l_ifoTradedProductParams);
            
            RsvIfoOrderUnitParams l_rsvIfoOrderUnitParams = TestDBUtility.getRsvIfoOrderUnitRow();
            l_rsvIfoOrderUnitParams.setMarketId(3303L);
            l_rsvIfoOrderUnitParams.setProductId(3304148080000L);
            l_rsvIfoOrderUnitParams.setSessionType("2");
            l_rsvIfoOrderUnitParams.setFutureOptionDiv("2");
            
            WEB3ToSuccIfoOrderUnitImpl l_toSuccIfoOrderUnitImpl = 
                new WEB3ToSuccIfoOrderUnitImpl(l_rsvIfoOrderUnitParams);
            
            WEB3ToSuccDataGettingServiceImpl l_service =
                new WEB3ToSuccDataGettingServiceImplForTest();
            l_service.createSuccOrderUnit(
                l_succOrderUnit, 
                l_toSuccIfoOrderUnitImpl,
                false);
            
            assertEquals("C",l_succOrderUnit.opProductType);
            assertTrue(l_succOrderUnit.eveningSessionCarryoverFlag);
            assertEquals("2",l_succOrderUnit.sessionType);
            
        }
        catch (Exception l_ex)
        {
            log.error(l_ex.getMessage(), l_ex);
            fail();
        }
        log.exiting(TEST_END + STR_METHOD_NAME);
    }
    
    /**
     * 先物OP注文単位の場合セット 且つ出来るまで注文"の場合
     *
     */
    public void testCreateSuccOrderUnit_C0003()
    {
        final String STR_METHOD_NAME = " testCreateSuccOrderUnit_C0003";
        log.entering(TEST_START + STR_METHOD_NAME);
        try
        {
            // OPTION
            this.l_strFutureOptionDiv = "2";

            TestDBUtility.deleteAll(ProductParams.TYPE);
            ProductParams l_productParams = TestDBUtility.getProductRow();
            l_productParams.setProductId(3304148080000L);
            l_productParams.setProductType(ProductTypeEnum.IFO);
            TestDBUtility.insertWithDel(l_productParams);


            TestDBUtility.deleteAll(IfoProductParams.TYPE);
            IfoProductParams l_ifoProductParams =
                TestDBUtility.getIfoProductRow();
            l_ifoProductParams.setProductId(3304148080000L);
            l_ifoProductParams.setProductType(ProductTypeEnum.IFO);
            
            TestDBUtility.insertWithDel(l_ifoProductParams);
            
            //MarketParams
            TestDBUtility.deleteAll(MarketParams.TYPE);
            MarketParams l_marketParams = TestDBUtility.getMarketRow();
            TestDBUtility.insertWithDel(l_marketParams);
            
            TestDBUtility.deleteAll(IfoOrderUnitParams.TYPE);
            IfoOrderUnitParams l_ifoOrderUnitParams =
                TestDBUtility.getIfoOrderUnitRow();
            l_ifoOrderUnitParams.setFirstOrderUnitId(1001);
            l_ifoOrderUnitParams.setProductId(3304148080000L);
            l_ifoOrderUnitParams.setMarketId(3303L);
            Calendar l_cal = Calendar.getInstance();
            l_cal.set(2008,3,8);
            
            l_ifoOrderUnitParams.setExpirationDate(l_cal.getTime());
            l_ifoOrderUnitParams.setOrgStopPriceType("1");
            l_ifoOrderUnitParams.setSessionType("2");
            
            TestDBUtility.insertWithDel(l_ifoOrderUnitParams);
            
            WEB3SuccOrderUnit l_succOrderUnit = new WEB3SuccOrderUnit();
            
            IfoContractOpenOrderUnitImpl l_ifoOrderUnitImpl = 
                new IfoContractOpenOrderUnitImpl(1001);
            
            WEB3ToSuccDataGettingServiceImpl l_service =
                new WEB3ToSuccDataGettingServiceImplForTest();
            
            l_service.createSuccOrderUnit(
                l_succOrderUnit, 
                l_ifoOrderUnitImpl,
                false);
            
            //注文有効期限
            assertEquals(0,WEB3DateUtility.compareToDay(l_cal.getTime(),l_succOrderUnit.expirationDate));
            
            //元プレミアム／原資産価格           
            assertEquals("1",l_succOrderUnit.orgPremium_underlyingAssets);
            
            //夕場前繰越対象フラグ
            assertFalse(l_succOrderUnit.eveningSessionCarryoverFlag);
            
            //立会区分
            assertEquals("2",l_succOrderUnit.sessionType);
            
        }
        catch (Exception l_ex)
        {
            log.error(l_ex.getMessage(), l_ex);
            fail();
        }
        log.exiting(TEST_END + STR_METHOD_NAME);
    }

    public class WEB3ToSuccDataGettingServiceImplForMock extends WEB3ToSuccDataGettingServiceImpl
    {
        public String getExecutionConditionTypeByPr(OrderUnit l_orderUnit)
            throws WEB3BaseException
        {
            final String STR_METHOD_NAME = "getExecutionConditionTypeByPr(OrderUnit)";
            log.entering(STR_METHOD_NAME);
            log.exiting(STR_METHOD_NAME);
            return "1";
        }
        public String getTransactionState(OrderUnit l_orderUnit) 
            throws WEB3BaseException
        {
            final String STR_METHOD_NAME = "getTransactionState(OrderUnit)";
            log.entering(STR_METHOD_NAME);
            log.exiting(STR_METHOD_NAME);
            return "1";
        }
//        public void createSuccOrderUnit(
//                WEB3SuccOrderUnit l_succOrderUnit, 
//                OrderUnit l_orderUnit, 
//                boolean l_blnIsPossibleFlagSet)
//                throws WEB3BaseException 
//       {
//       }
//
//        public void setPossibleFlag(
//                WEB3SuccOrderUnit l_succOrderUnit, 
//                OrderUnit l_orderUnit)
//                throws WEB3BaseException
//            {
//            
//            }
    }
    
    public class WEB3ToSuccDataGettingServiceImplForMock1 extends WEB3ToSuccDataGettingServiceImpl
    {
        public String getExecutionConditionTypeByPr(OrderUnit l_orderUnit)
            throws WEB3BaseException
        {
            final String STR_METHOD_NAME = "getExecutionConditionTypeByPr(OrderUnit)";
            log.entering(STR_METHOD_NAME);
            log.exiting(STR_METHOD_NAME);
            return "1";
        }
        public String getTransactionState(OrderUnit l_orderUnit) 
            throws WEB3BaseException
        {
            final String STR_METHOD_NAME = "getTransactionState(OrderUnit)";
            log.entering(STR_METHOD_NAME);
            log.exiting(STR_METHOD_NAME);
            return "1";
        }
        public void createSuccOrderUnit(
                WEB3SuccOrderUnit l_succOrderUnit, 
                OrderUnit l_orderUnit, 
                boolean l_blnIsPossibleFlagSet)
                throws WEB3BaseException 
       {
       }

        public void setPossibleFlag(
                WEB3SuccOrderUnit l_succOrderUnit, 
                OrderUnit l_orderUnit)
                throws WEB3BaseException
            {
            
            }
    }
    
    private class OrderUnitForTest extends WEB3ToSuccIfoOrderUnitImpl implements IfoOrderUnit
    {

        public OrderUnitForTest(RsvIfoOrderUnitRow l_rsvIfoOrderUnitRow)
        {
            super(l_rsvIfoOrderUnitRow);
        }
        
        public Product getProduct()
        {
            ProductForTest l_ifoProduct = null;
            try
            {
                l_ifoProduct = new ProductForTest(3304148080000L);
            }
            catch(Exception l_ex)
            {
                fail();
            }
            return l_ifoProduct;
        }
        
        public Object getDataSourceObject()
        {
            // RsvIfoOrderUnitRow
            RsvIfoOrderUnitParams l_rsvIfoOrderUnitParams = TestDBUtility.getRsvIfoOrderUnitRow();

            l_rsvIfoOrderUnitParams.setFutureOptionDiv(l_strFutureOptionDiv);
            l_rsvIfoOrderUnitParams.setMarketId(3303L);
            return l_rsvIfoOrderUnitParams;
            
//            IfoOrderUnitParams l_ifoOrderUnitParams = TestDBUtility.getIfoOrderUnitRow();
//            l_ifoOrderUnitParams.setFutureOptionDiv(l_strFutureOptionDiv);
//            l_ifoOrderUnitParams.setMarketId(3303L);

//            return l_ifoOrderUnitParams;
        }
    }
    
    private class ProductForTest extends WEB3IfoProductImpl
    {

        public ProductForTest(long l_lngProductID) throws DataFindException, DataQueryException, DataNetworkException
        {
            super(l_lngProductID);
            // TODO Auto-generated constructor stub
        }

        public long getProductId()
        {
            // TODO Auto-generated method stub
            return 0;
        }

        public Market[] getTradedMarkets()
        {
            // TODO Auto-generated method stub
            return null;
        }

        public Market getPrimaryMarket()
        {
            // TODO Auto-generated method stub
            return null;
        }

        public TradedProduct getPrimaryTradedProduct()
        {
            // TODO Auto-generated method stub
            return null;
        }

        public ProductTypeEnum getProductType()
        {
            // TODO Auto-generated method stub
            return ProductTypeEnum.IFO;
        }

        public boolean isTradedOnMarket(Market arg0)
        {
            // TODO Auto-generated method stub
            return false;
        }

        public double getLotSize()
        {
            // TODO Auto-generated method stub
            return 0;
        }

        public String getStandardName()
        {
            // TODO Auto-generated method stub
            return null;
        }

        public String getNameAlt1()
        {
            // TODO Auto-generated method stub
            return null;
        }

        public String getNameAlt2()
        {
            // TODO Auto-generated method stub
            return null;
        }

        public double getMarginRatio()
        {
            // TODO Auto-generated method stub
            return 0;
        }

        public Institution getInstitution()
        {
            // TODO Auto-generated method stub
            return null;
        }

        public Object getDataSourceObject()
        {
            // IfoProductRow
            IfoProductParams l_ifoProductParams = TestDBUtility.getIfoProductRow();
            if(l_blnIsPutOption)
            {
                l_ifoProductParams.setDerivativeType(IfoDerivativeTypeEnum.PUT_OPTIONS);
            }
            else
            {
                l_ifoProductParams.setDerivativeType(IfoDerivativeTypeEnum.CALL_OPTIONS);
            }
            
            
            return l_ifoProductParams;
        }
        
        public String getMonthOfDelivery()
        {
            return "12";
        }
        
    }
    /**
     * 
     * 先物OP予約注文単位Impl
     *
     */
    public void test_getTransactionState_0001()
    {
        final String STR_METHOD_NAME = " test_getTransactionState_0001()";
        log.entering(TEST_START + STR_METHOD_NAME);
        
        WEB3ToSuccDataGettingServiceImpl l_toSuccDataGettingServiceImpl = new WEB3ToSuccDataGettingServiceImpl();
        RsvIfoOrderUnitRow l_rsvIfoOrderUnitRow = new RsvIfoOrderUnitParams();
        WEB3ToSuccIfoOrderUnitImpl l_toSuccIfoOrderUnitImpl= new WEB3ToSuccIfoOrderUnitImpl(l_rsvIfoOrderUnitRow);
        try
        {
            String l_str = l_toSuccDataGettingServiceImpl.getTransactionState(l_toSuccIfoOrderUnitImpl);
            assertEquals("3000", "" + l_str);
        }
        catch (Exception l_ex)
        {
            log.error(l_ex.getMessage(), l_ex);
            fail();
        }
        log.exiting(TEST_END + STR_METHOD_NAME); 
    }
    
    /**
     * 
     * 先物OP予約注文単位Impl
     *
     */
    public void test_getTransactionState_0002()
    {
        final String STR_METHOD_NAME = " test_getTransactionState_0002()";
        log.entering(TEST_START + STR_METHOD_NAME);
        
        WEB3ToSuccDataGettingServiceImpl l_toSuccDataGettingServiceImpl = new WEB3ToSuccDataGettingServiceImpl();
        try
        {
            WEB3GentradeTradingTimeManagementForMock.mockGetOrderBizDate(WEB3DateUtility.getDate("20080808", "yyyyMMdd"));
            TestDBUtility.deleteAll(IfoOrderUnitRow.TYPE);
            IfoOrderUnitParams l_ifoOrderUnitParams = TestDBUtility.getIfoOrderUnitRow();
            l_ifoOrderUnitParams.setAccountId(333812512246L);
            l_ifoOrderUnitParams.setConfirmedPrice(22.3);
            l_ifoOrderUnitParams.setQuantity(18);
            TestDBUtility.insertWithDel(l_ifoOrderUnitParams);
            
            IfoOrderUnit l_ifoOrderUnit = 
                new IfoContractSettleOrderUnitImpl(l_ifoOrderUnitParams.getOrderUnitId());
            String l_str = l_toSuccDataGettingServiceImpl.getTransactionState(l_ifoOrderUnit);
            assertEquals("000null", "" + l_str);
        }
        catch (Exception l_ex)
        {
            log.error(l_ex.getMessage(), l_ex);
            fail();
        }
        log.exiting(TEST_END + STR_METHOD_NAME); 
    }
    
    /**
     * 
     * 引数.商品区分一覧に"現物株式"
     */
    public void test_getRsvOrderUnitList_0001()
    {
        final String STR_METHOD_NAME = " test_getRsvOrderUnitList_0001()";
        log.entering(TEST_START + STR_METHOD_NAME);
        
        WEB3ToSuccDataGettingServiceImpl l_toSuccDataGettingServiceImpl = new WEB3ToSuccDataGettingServiceImpl();
//        RsvIfoOrderUnitRow l_rsvIfoOrderUnitRow = new RsvIfoOrderUnitParams();
//        WEB3ToSuccIfoOrderUnitImpl l_toSuccIfoOrderUnitImpl= new WEB3ToSuccIfoOrderUnitImpl(l_rsvIfoOrderUnitRow);
        try
        {
            RsvEqOrderUnitParams l_rsvEqOrderUnitParams = TestDBUtility.getRsvEqOrderUnitRow();
            l_rsvEqOrderUnitParams.setProductType(ProductTypeEnum.EQUITY);
            l_rsvEqOrderUnitParams.setPrice(256.36);
            TestDBUtility.insertWithDel(l_rsvEqOrderUnitParams);
            
            RsvEqOrderUnitParams l_rsvEqOrderUnitParams1 = TestDBUtility.getRsvEqOrderUnitRow();
            l_rsvEqOrderUnitParams1.setOrderUnitId(1111);
            l_rsvEqOrderUnitParams1.setAccountId(101001010010L);
            l_rsvEqOrderUnitParams1.setSubAccountId(233323L);
            l_rsvEqOrderUnitParams1.setBranchId(223323);
            l_rsvEqOrderUnitParams1.setTraderId(23345);
            l_rsvEqOrderUnitParams1.setOrderId(23);
            l_rsvEqOrderUnitParams1.setProductType(ProductTypeEnum.EQUITY);
            l_rsvEqOrderUnitParams1.setPrice(123.36);
            l_rsvEqOrderUnitParams1.setBizDate("20070217");
            l_rsvEqOrderUnitParams1.setProductId(213);
            l_rsvEqOrderUnitParams1.setParentOrderId(33);
            l_rsvEqOrderUnitParams1.setSerialNoInParent(2);
            TestDBUtility.insertWithDel(l_rsvEqOrderUnitParams1);
            
            Hashtable l_table = l_toSuccDataGettingServiceImpl.getRsvOrderUnitList(101001010010L,new String[]{"1"});
//            assertEquals("256.36","" + ((RsvEqOrderUnitRow)l_table.get("1002EQUITY")).getPrice());
            assertEquals("123.36","" + ((RsvEqOrderUnitRow)(((ArrayList)l_table.get("33EQUITY")).get(0))).getPrice());
            assertEquals("256.36","" + ((RsvEqOrderUnitRow)(((ArrayList)l_table.get("1002EQUITY")).get(0))).getPrice());
        }
        catch (Exception l_ex)
        {
            log.error(l_ex.getMessage(), l_ex);
            fail();
        }
        log.exiting(TEST_END + STR_METHOD_NAME); 
    }
    
    
    /**
     * 
     * 引数.商品区分一覧に "信用取引" 
     */
    public void test_getRsvOrderUnitList_0002()
    {
        final String STR_METHOD_NAME = " test_getRsvOrderUnitList_0002()";
        log.entering(TEST_START + STR_METHOD_NAME);
        
        WEB3ToSuccDataGettingServiceImpl l_toSuccDataGettingServiceImpl = new WEB3ToSuccDataGettingServiceImpl();
//        RsvIfoOrderUnitRow l_rsvIfoOrderUnitRow = new RsvIfoOrderUnitParams();
//        WEB3ToSuccIfoOrderUnitImpl l_toSuccIfoOrderUnitImpl= new WEB3ToSuccIfoOrderUnitImpl(l_rsvIfoOrderUnitRow);
        try
        {
            RsvEqOrderUnitParams l_rsvEqOrderUnitParams = TestDBUtility.getRsvEqOrderUnitRow();
            l_rsvEqOrderUnitParams.setProductType(ProductTypeEnum.EQUITY);
            l_rsvEqOrderUnitParams.setPrice(256.36);
            TestDBUtility.insertWithDel(l_rsvEqOrderUnitParams);
            
            RsvEqOrderUnitParams l_rsvEqOrderUnitParams1 = TestDBUtility.getRsvEqOrderUnitRow();
            l_rsvEqOrderUnitParams1.setOrderUnitId(1111);
            l_rsvEqOrderUnitParams1.setAccountId(101001010010L);
            l_rsvEqOrderUnitParams1.setSubAccountId(233323L);
            l_rsvEqOrderUnitParams1.setBranchId(223323);
            l_rsvEqOrderUnitParams1.setTraderId(23345);
            l_rsvEqOrderUnitParams1.setOrderId(23);
            l_rsvEqOrderUnitParams1.setProductType(ProductTypeEnum.EQUITY);
            l_rsvEqOrderUnitParams1.setPrice(123.36);
            l_rsvEqOrderUnitParams1.setBizDate("20070217");
            l_rsvEqOrderUnitParams1.setProductId(213);
            l_rsvEqOrderUnitParams1.setParentOrderId(33);
            l_rsvEqOrderUnitParams1.setSerialNoInParent(2);
            TestDBUtility.insertWithDel(l_rsvEqOrderUnitParams1);
            
            Hashtable l_table = l_toSuccDataGettingServiceImpl.getRsvOrderUnitList(101001010010L,new String[]{"2"});
//            assertEquals("256.36","" + ((RsvEqOrderUnitRow)l_table.get("1002EQUITY")).getPrice());
            assertEquals("123.36","" + ((RsvEqOrderUnitRow)(((ArrayList)l_table.get("33EQUITY")).get(0))).getPrice());
            assertEquals("256.36","" + ((RsvEqOrderUnitRow)(((ArrayList)l_table.get("1002EQUITY")).get(0))).getPrice());
        }
        catch (Exception l_ex)
        {
            log.error(l_ex.getMessage(), l_ex);
            fail();
        }
        log.exiting(TEST_END + STR_METHOD_NAME); 
    }
    
    /**
     * 
     * 引数.商品区分一覧に"先物"
     */
    public void test_getRsvOrderUnitList_0003()
    {
        final String STR_METHOD_NAME = " test_getRsvOrderUnitList_0003()";
        log.entering(TEST_START + STR_METHOD_NAME);
        
        WEB3ToSuccDataGettingServiceImpl l_toSuccDataGettingServiceImpl = new WEB3ToSuccDataGettingServiceImpl();
//        RsvIfoOrderUnitRow l_rsvIfoOrderUnitRow = new RsvIfoOrderUnitParams();
//        WEB3ToSuccIfoOrderUnitImpl l_toSuccIfoOrderUnitImpl= new WEB3ToSuccIfoOrderUnitImpl(l_rsvIfoOrderUnitRow);
        try
        {
         
            RsvIfoOrderUnitParams l_rsvIfoOrderUnitParams = new RsvIfoOrderUnitParams();
            l_rsvIfoOrderUnitParams.setAccountId(11);
            l_rsvIfoOrderUnitParams.setMarketId(3303L);
            l_rsvIfoOrderUnitParams.setSubAccountId(123);
            l_rsvIfoOrderUnitParams.setBranchId(1234);
            l_rsvIfoOrderUnitParams.setOrderId(21);
            l_rsvIfoOrderUnitParams.setOrderType(OrderTypeEnum.UNDEFINED);
            l_rsvIfoOrderUnitParams.setOrderCateg(OrderCategEnum.UNDEFINED);
            l_rsvIfoOrderUnitParams.setLastOrderActionSerialNo(0);
            l_rsvIfoOrderUnitParams.setProductType(ProductTypeEnum.IFO);
            l_rsvIfoOrderUnitParams.setQuantity(23);
            l_rsvIfoOrderUnitParams.setOrderStatus(OrderStatusEnum.UNDEFINED);
            l_rsvIfoOrderUnitParams.setOrderOpenStatus(OrderOpenStatusEnum.UNDEFINED);
            l_rsvIfoOrderUnitParams.setExpirationStatus(OrderExpirationStatusEnum.UNDEFINED);
            l_rsvIfoOrderUnitParams.setTaxType(TaxTypeEnum.UNDEFINED);
            l_rsvIfoOrderUnitParams.setBizDate("20070117");
            l_rsvIfoOrderUnitParams.setProductId(3304148080000L);
            l_rsvIfoOrderUnitParams.setParentOrderId(56);
            l_rsvIfoOrderUnitParams.setSerialNoInParent(68);
            l_rsvIfoOrderUnitParams.setCreatedTimestamp(Calendar.getInstance().getTime());
            l_rsvIfoOrderUnitParams.setLastUpdatedTimestamp(Calendar.getInstance().getTime());
            
            //注文期限区分
            l_rsvIfoOrderUnitParams.setExpirationDateType("2");
            //注文有効期限
            l_rsvIfoOrderUnitParams.setExpirationDate(WEB3DateUtility.getDate("20080707", "yyyyMMdd"));
            //立会区分
            l_rsvIfoOrderUnitParams.setSessionType("0");
            //決済順序
            l_rsvIfoOrderUnitParams.setClosingOrder("3");
            //概算受渡代金
            l_rsvIfoOrderUnitParams.setEstimatedPrice(65);
            //指値
            l_rsvIfoOrderUnitParams.setLimitPrice(56);
            //注文種別
            l_rsvIfoOrderUnitParams.setOrderType(OrderTypeEnum.IDX_FUTURES_BUY_TO_OPEN);
            TestDBUtility.insertWithDel(l_rsvIfoOrderUnitParams);
            
            RsvIfoOrderUnitParams l_rsvIfoOrderUnitParams1 = new RsvIfoOrderUnitParams();
            l_rsvIfoOrderUnitParams1.setAccountId(11);
            l_rsvIfoOrderUnitParams1.setMarketId(4563L);
            l_rsvIfoOrderUnitParams1.setSubAccountId(564);
            l_rsvIfoOrderUnitParams1.setBranchId(756);
            l_rsvIfoOrderUnitParams1.setOrderId(235);
            l_rsvIfoOrderUnitParams1.setOrderType(OrderTypeEnum.UNDEFINED);
            l_rsvIfoOrderUnitParams1.setOrderCateg(OrderCategEnum.UNDEFINED);
            l_rsvIfoOrderUnitParams1.setLastOrderActionSerialNo(0);
            l_rsvIfoOrderUnitParams1.setProductType(ProductTypeEnum.IFO);
            l_rsvIfoOrderUnitParams1.setQuantity(23);
            l_rsvIfoOrderUnitParams1.setOrderStatus(OrderStatusEnum.UNDEFINED);
            l_rsvIfoOrderUnitParams1.setOrderOpenStatus(OrderOpenStatusEnum.UNDEFINED);
            l_rsvIfoOrderUnitParams1.setExpirationStatus(OrderExpirationStatusEnum.UNDEFINED);
            l_rsvIfoOrderUnitParams1.setTaxType(TaxTypeEnum.UNDEFINED);
            l_rsvIfoOrderUnitParams1.setBizDate("20070117");
            l_rsvIfoOrderUnitParams1.setProductId(3304148080000L);
            l_rsvIfoOrderUnitParams1.setParentOrderId(45);
            l_rsvIfoOrderUnitParams1.setSerialNoInParent(12);
            l_rsvIfoOrderUnitParams1.setCreatedTimestamp(Calendar.getInstance().getTime());
            l_rsvIfoOrderUnitParams1.setLastUpdatedTimestamp(Calendar.getInstance().getTime());
            //注文期限区分
            l_rsvIfoOrderUnitParams1.setExpirationDateType("3");
            //注文有効期限
            l_rsvIfoOrderUnitParams1.setExpirationDate(WEB3DateUtility.getDate("20080707", "yyyyMMdd"));
            //立会区分
            l_rsvIfoOrderUnitParams1.setSessionType("1");
            //決済順序
            l_rsvIfoOrderUnitParams1.setClosingOrder("3");
            //概算受渡代金
            l_rsvIfoOrderUnitParams1.setEstimatedPrice(65);
            //指値
            l_rsvIfoOrderUnitParams1.setLimitPrice(231);
            //注文種別
            l_rsvIfoOrderUnitParams1.setOrderType(OrderTypeEnum.IDX_FUTURES_BUY_TO_OPEN);
            TestDBUtility.insertWithDel(l_rsvIfoOrderUnitParams1);
            Hashtable l_table = l_toSuccDataGettingServiceImpl.getRsvOrderUnitList(11,new String[]{"3"});
//            assertEquals("256.36","" + ((RsvEqOrderUnitRow)l_table.get("1002EQUITY")).getPrice());
            assertEquals("56.0","" + ((RsvIfoOrderUnitRow)(((ArrayList)l_table.get("56IFO")).get(0))).getLimitPrice());
            assertEquals("231.0","" + ((RsvIfoOrderUnitRow)(((ArrayList)l_table.get("45IFO")).get(0))).getLimitPrice());
        }
        catch (Exception l_ex)
        {
            log.error(l_ex.getMessage(), l_ex);
            fail();
        }
        log.exiting(TEST_END + STR_METHOD_NAME); 
    }
    
    /**
     * 
     * 引数.商品区分一覧に"オプション"
     */
    public void test_getRsvOrderUnitList_0004()
    {
        final String STR_METHOD_NAME = " test_getRsvOrderUnitList_0004()";
        log.entering(TEST_START + STR_METHOD_NAME);
        
        WEB3ToSuccDataGettingServiceImpl l_toSuccDataGettingServiceImpl = new WEB3ToSuccDataGettingServiceImpl();
//        RsvIfoOrderUnitRow l_rsvIfoOrderUnitRow = new RsvIfoOrderUnitParams();
//        WEB3ToSuccIfoOrderUnitImpl l_toSuccIfoOrderUnitImpl= new WEB3ToSuccIfoOrderUnitImpl(l_rsvIfoOrderUnitRow);
        try
        {
         
            RsvIfoOrderUnitParams l_rsvIfoOrderUnitParams = new RsvIfoOrderUnitParams();
            l_rsvIfoOrderUnitParams.setAccountId(11);
            l_rsvIfoOrderUnitParams.setMarketId(3303L);
            l_rsvIfoOrderUnitParams.setSubAccountId(123);
            l_rsvIfoOrderUnitParams.setBranchId(1234);
            l_rsvIfoOrderUnitParams.setOrderId(21);
            l_rsvIfoOrderUnitParams.setOrderType(OrderTypeEnum.UNDEFINED);
            l_rsvIfoOrderUnitParams.setOrderCateg(OrderCategEnum.UNDEFINED);
            l_rsvIfoOrderUnitParams.setLastOrderActionSerialNo(0);
            l_rsvIfoOrderUnitParams.setProductType(ProductTypeEnum.IFO);
            l_rsvIfoOrderUnitParams.setQuantity(23);
            l_rsvIfoOrderUnitParams.setOrderStatus(OrderStatusEnum.UNDEFINED);
            l_rsvIfoOrderUnitParams.setOrderOpenStatus(OrderOpenStatusEnum.UNDEFINED);
            l_rsvIfoOrderUnitParams.setExpirationStatus(OrderExpirationStatusEnum.UNDEFINED);
            l_rsvIfoOrderUnitParams.setTaxType(TaxTypeEnum.UNDEFINED);
            l_rsvIfoOrderUnitParams.setBizDate("20070117");
            l_rsvIfoOrderUnitParams.setProductId(3304148080000L);
            l_rsvIfoOrderUnitParams.setParentOrderId(56);
            l_rsvIfoOrderUnitParams.setSerialNoInParent(68);
            l_rsvIfoOrderUnitParams.setCreatedTimestamp(Calendar.getInstance().getTime());
            l_rsvIfoOrderUnitParams.setLastUpdatedTimestamp(Calendar.getInstance().getTime());
            
            //注文期限区分
            l_rsvIfoOrderUnitParams.setExpirationDateType("2");
            //注文有効期限
            l_rsvIfoOrderUnitParams.setExpirationDate(WEB3DateUtility.getDate("20080707", "yyyyMMdd"));
            //立会区分
            l_rsvIfoOrderUnitParams.setSessionType("0");
            //決済順序
            l_rsvIfoOrderUnitParams.setClosingOrder("3");
            //概算受渡代金
            l_rsvIfoOrderUnitParams.setEstimatedPrice(65);
            //指値
            l_rsvIfoOrderUnitParams.setLimitPrice(56);
            //注文種別
            l_rsvIfoOrderUnitParams.setOrderType(OrderTypeEnum.IDX_FUTURES_BUY_TO_OPEN);
            TestDBUtility.insertWithDel(l_rsvIfoOrderUnitParams);
            
            RsvIfoOrderUnitParams l_rsvIfoOrderUnitParams1 = new RsvIfoOrderUnitParams();
            l_rsvIfoOrderUnitParams1.setAccountId(11);
            l_rsvIfoOrderUnitParams1.setMarketId(4563L);
            l_rsvIfoOrderUnitParams1.setSubAccountId(564);
            l_rsvIfoOrderUnitParams1.setBranchId(756);
            l_rsvIfoOrderUnitParams1.setOrderId(235);
            l_rsvIfoOrderUnitParams1.setOrderType(OrderTypeEnum.UNDEFINED);
            l_rsvIfoOrderUnitParams1.setOrderCateg(OrderCategEnum.UNDEFINED);
            l_rsvIfoOrderUnitParams1.setLastOrderActionSerialNo(0);
            l_rsvIfoOrderUnitParams1.setProductType(ProductTypeEnum.IFO);
            l_rsvIfoOrderUnitParams1.setQuantity(23);
            l_rsvIfoOrderUnitParams1.setOrderStatus(OrderStatusEnum.UNDEFINED);
            l_rsvIfoOrderUnitParams1.setOrderOpenStatus(OrderOpenStatusEnum.UNDEFINED);
            l_rsvIfoOrderUnitParams1.setExpirationStatus(OrderExpirationStatusEnum.UNDEFINED);
            l_rsvIfoOrderUnitParams1.setTaxType(TaxTypeEnum.UNDEFINED);
            l_rsvIfoOrderUnitParams1.setBizDate("20070117");
            l_rsvIfoOrderUnitParams1.setProductId(3304148080000L);
            l_rsvIfoOrderUnitParams1.setParentOrderId(45);
            l_rsvIfoOrderUnitParams1.setSerialNoInParent(12);
            l_rsvIfoOrderUnitParams1.setCreatedTimestamp(Calendar.getInstance().getTime());
            l_rsvIfoOrderUnitParams1.setLastUpdatedTimestamp(Calendar.getInstance().getTime());
            //注文期限区分
            l_rsvIfoOrderUnitParams1.setExpirationDateType("3");
            //注文有効期限
            l_rsvIfoOrderUnitParams1.setExpirationDate(WEB3DateUtility.getDate("20080707", "yyyyMMdd"));
            //立会区分
            l_rsvIfoOrderUnitParams1.setSessionType("1");
            //決済順序
            l_rsvIfoOrderUnitParams1.setClosingOrder("3");
            //概算受渡代金
            l_rsvIfoOrderUnitParams1.setEstimatedPrice(65);
            //指値
            l_rsvIfoOrderUnitParams1.setLimitPrice(231);
            //注文種別
            l_rsvIfoOrderUnitParams1.setOrderType(OrderTypeEnum.IDX_FUTURES_BUY_TO_OPEN);
            TestDBUtility.insertWithDel(l_rsvIfoOrderUnitParams1);
            Hashtable l_table = l_toSuccDataGettingServiceImpl.getRsvOrderUnitList(11,new String[]{"4"});
//            assertEquals("256.36","" + ((RsvEqOrderUnitRow)l_table.get("1002EQUITY")).getPrice());
            assertEquals("56.0","" + ((RsvIfoOrderUnitRow)(((ArrayList)l_table.get("56IFO")).get(0))).getLimitPrice());
            assertEquals("231.0","" + ((RsvIfoOrderUnitRow)(((ArrayList)l_table.get("45IFO")).get(0))).getLimitPrice());
        }
        catch (Exception l_ex)
        {
            log.error(l_ex.getMessage(), l_ex);
            fail();
        }
        log.exiting(TEST_END + STR_METHOD_NAME); 
    }
    
    /**
     * 
     * 株式注文単位
     * 現物株式
     */
    public void test_setPossibleFlag_0001()
    {
        final String STR_METHOD_NAME = "test_setPossibleFlag_0001()";
        log.entering(TEST_START + STR_METHOD_NAME);
        
        WEB3ToSuccDataGettingServiceImpl l_toSuccDataGettingServiceImpl = new WEB3ToSuccDataGettingServiceImpl();
        WEB3SuccOrderUnit l_succOrderUnit = new WEB3SuccOrderUnit();
        l_succOrderUnit.commodityType = "1";

        try
        {
//            FinApp l_finApp = (FinApp) Services.getService(FinApp.class);
//            // 拡張トランザクション・マネージャーは
//            // オーバーライドメソッドが無いため拡張取引モジュールクラスを作成し
//            // 拡張取引モジュールクラス内で設定
//            l_finApp.uninstallTradingModule("eqtype");
//            l_finApp.installTradingModule(new WEB3EquityTradingModule());
//            TradingModule l_tradingModule = l_finApp.getTradingModule("eqtype");
            
//            l_tradingModule.overrideOrderManager(
//                    new WEB3EquityOrderManagerForMock());]
            OrderexecutionEndParams l_orderexecutionEndParams = TestDBUtility.getOrderexecutionEndRow();
            l_orderexecutionEndParams.setInstitutionCode("0D");
            l_orderexecutionEndParams.setProductType(ProductTypeEnum.EQUITY);
            l_orderexecutionEndParams.setFutureOptionDiv("0");
            l_orderexecutionEndParams.setOrderexecutionEndType("0");
            l_orderexecutionEndParams.setCarryoverEndType("1");
            TestDBUtility.insertWithDel(l_orderexecutionEndParams);
            
            InstitutionParams l_InstitutionParams = TestDBUtility.getInstitutionRow();
            TestDBUtility.insertWithDel(l_InstitutionParams);
            
            EqtypeOrderParams l_eqTypeOrderParams = TestDBUtility.getEqtypeOrderRow();
            l_eqTypeOrderParams.setOrderId(123456789L);
            TestDBUtility.insertWithDel(l_eqTypeOrderParams);
            
            FinApp l_finApp = (FinApp) Services.getService(FinApp.class);
            TradingModule l_tradingModuleMock = GtlUtils.getTradingModule(ProductTypeEnum.EQUITY);
            l_tradingModuleMock = l_finApp.getTradingModule("eqtype");
            l_tradingModuleMock.overrideOrderManager(new WEB3EquityOrderManagerForMock());
            
            TestBaseForMock.MOCK_MANAGER.setIsMockUsed(true);
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                    "webbroker3.equity.WEB3EquityOrderManager",
                    "validateOrderForChangeability",
                    new Class[] {Order.class},
                    null);
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                    "webbroker3.equity.WEB3EquityOrderManager",
                    "validateOrderForCancellation",
                    new Class[] {Order.class},
                    null);
            
//            ThreadLocalSystemAttributesRegistry.setAttribute("xblocks.gtl.attributes.systemtimestamp", new Timestamp(WEB3DateUtility.getDate("20070707", "yyyyMMdd").getTime()));
                    
//            WEB3GentradeTradingTimeManagementForMock.mockGetOrderBizDate(WEB3DateUtility.getDate("20080808", "yyyyMMdd"));
            EqtypeOrderUnitParams l_eqtypeOrderUnitParams = TestDBUtility.getEqtypeOrderUnitRow();
            EqTypeOrderUnitImpl l_impl = new EqTypeOrderUnitImpl(null, l_eqtypeOrderUnitParams);
//            IfoOrderUnitParams l_ifoOrderUnitParams = TestDBUtility.getIfoOrderUnitRow();
//            l_ifoOrderUnitParams.setAccountId(333812512246L);
//            l_ifoOrderUnitParams.setConfirmedPrice(22.3);
//            l_ifoOrderUnitParams.setQuantity(18);
//            TestDBUtility.insertWithDel(l_ifoOrderUnitParams);
//            
//            IfoOrderUnit l_ifoOrderUnit = 
//                new IfoContractSettleOrderUnitImpl(l_ifoOrderUnitParams.getOrderUnitId());
            
            ThreadLocalSystemAttributesRegistry.setAttribute("xblocks.gtl.attributes.systemtimestamp",
                    new Timestamp(WEB3DateUtility.getDate("20070707170000", "yyyyMMddHHmmss").getTime()));
            
            TradingTimeParams l_tradingTimeParams = TestDBUtility.getTradingTimeRow();
            l_tradingTimeParams.setInstitutionCode("0D");
            l_tradingTimeParams.setBranchCode("123");
            l_tradingTimeParams.setMarketCode("N1");
            l_tradingTimeParams.setTradingTimeType("01");
            l_tradingTimeParams.setProductCode("0");
            l_tradingTimeParams.setBizDateType("1");
            l_tradingTimeParams.setBizdateCalcParameter("0");
            l_tradingTimeParams.setSubmitMarketTrigger("1");
            l_tradingTimeParams.setEndTime("160001");
            l_tradingTimeParams.setStartTime("00");
            TestDBUtility.insertWithDel(l_tradingTimeParams);
            
            TradingTimeParams l_tradingTimeParams1 = TestDBUtility.getTradingTimeRow();
            l_tradingTimeParams1.setInstitutionCode("0D");
            l_tradingTimeParams1.setBranchCode("123");
            l_tradingTimeParams1.setMarketCode("N1");
            l_tradingTimeParams1.setTradingTimeType("01");
            l_tradingTimeParams1.setProductCode("0");
            l_tradingTimeParams1.setBizDateType("0");
            l_tradingTimeParams1.setBizdateCalcParameter("0");
            l_tradingTimeParams1.setSubmitMarketTrigger("1");
            l_tradingTimeParams1.setEndTime("170001");
            l_tradingTimeParams1.setStartTime("00");
            TestDBUtility.insertWithDel(l_tradingTimeParams1);
            
//            TradingTimeParams l_tradingTimeParams1 = TestDBUtility.getTradingTimeRow();
//            l_tradingTimeParams1.setInstitutionCode("0D");
//            l_tradingTimeParams1.setBranchCode("123");
//            l_tradingTimeParams1.setMarketCode("N1");
//            l_tradingTimeParams1.setTradingTimeType("01");
//            l_tradingTimeParams1.setProductCode("0");
//            l_tradingTimeParams1.setBizDateType("1");
//            l_tradingTimeParams1.setBizdateCalcParameter("0");
//            l_tradingTimeParams1.setSubmitMarketTrigger("1");
//            l_tradingTimeParams1.setEndTime("140001");
//            l_tradingTimeParams1.setStartTime("00");
//            TestDBUtility.insertWithDel(l_tradingTimeParams1);
            
            BranchParams l_branchParams = TestDBUtility.getBranchRow();
            TestDBUtility.insertWithDel(l_branchParams);
            MarketParams l_marketParams = TestDBUtility.getMarketRow();
            l_marketParams.setMarketId(3303L);
            //市場コード（SONAR）
            
            l_marketParams.setSonarMarketCode("4");
            l_marketParams.setMarketId(3303L);
            
            TestDBUtility.insertWithDel(l_marketParams);
            l_toSuccDataGettingServiceImpl.setPossibleFlag(l_succOrderUnit, l_impl);
        }
        catch (Exception l_ex)
        {
            log.error(l_ex.getMessage(), l_ex);
            fail();
        }
        

//        l_toSuccDataGettingServiceImpl.setPossibleFlag();
        log.exiting(TEST_END + STR_METHOD_NAME); 
    }
    
    /**
     * 
     * 株式注文単位
     * 信用取引
     */
    public void test_setPossibleFlag_0002()
    {
        final String STR_METHOD_NAME = "test_setPossibleFlag_0002()";
        log.entering(TEST_START + STR_METHOD_NAME);
        
        WEB3ToSuccDataGettingServiceImpl l_toSuccDataGettingServiceImpl = new WEB3ToSuccDataGettingServiceImpl();
        WEB3SuccOrderUnit l_succOrderUnit = new WEB3SuccOrderUnit();
        l_succOrderUnit.commodityType = "2";

        try
        {
//            FinApp l_finApp = (FinApp) Services.getService(FinApp.class);
//            // 拡張トランザクション・マネージャーは
//            // オーバーライドメソッドが無いため拡張取引モジュールクラスを作成し
//            // 拡張取引モジュールクラス内で設定
//            l_finApp.uninstallTradingModule("eqtype");
//            l_finApp.installTradingModule(new WEB3EquityTradingModule());
//            TradingModule l_tradingModule = l_finApp.getTradingModule("eqtype");
            
//            l_tradingModule.overrideOrderManager(
//                    new WEB3EquityOrderManagerForMock());]
            OrderexecutionEndParams l_orderexecutionEndParams = TestDBUtility.getOrderexecutionEndRow();
            l_orderexecutionEndParams.setInstitutionCode("0D");
            l_orderexecutionEndParams.setProductType(ProductTypeEnum.EQUITY);
            l_orderexecutionEndParams.setFutureOptionDiv("0");
            l_orderexecutionEndParams.setOrderexecutionEndType("0");
            l_orderexecutionEndParams.setCarryoverEndType("1");
            TestDBUtility.insertWithDel(l_orderexecutionEndParams);
            
            InstitutionParams l_InstitutionParams = TestDBUtility.getInstitutionRow();
            TestDBUtility.insertWithDel(l_InstitutionParams);
            
            EqtypeOrderParams l_eqTypeOrderParams = TestDBUtility.getEqtypeOrderRow();
            l_eqTypeOrderParams.setOrderId(123456789L);
            TestDBUtility.insertWithDel(l_eqTypeOrderParams);
            
            FinApp l_finApp = (FinApp) Services.getService(FinApp.class);
            TradingModule l_tradingModuleMock = GtlUtils.getTradingModule(ProductTypeEnum.EQUITY);
            l_tradingModuleMock = l_finApp.getTradingModule("eqtype");
            l_tradingModuleMock.overrideOrderManager(new WEB3EquityOrderManagerForMock());
            
            TestBaseForMock.MOCK_MANAGER.setIsMockUsed(true);
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                    "webbroker3.equity.WEB3EquityOrderManager",
                    "validateOrderForChangeability",
                    new Class[] {Order.class},
                    null);
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                    "webbroker3.equity.WEB3EquityOrderManager",
                    "validateOrderForCancellation",
                    new Class[] {Order.class},
                    null);
            
//            ThreadLocalSystemAttributesRegistry.setAttribute("xblocks.gtl.attributes.systemtimestamp", new Timestamp(WEB3DateUtility.getDate("20070707", "yyyyMMdd").getTime()));
                    
//            WEB3GentradeTradingTimeManagementForMock.mockGetOrderBizDate(WEB3DateUtility.getDate("20080808", "yyyyMMdd"));
            EqtypeOrderUnitParams l_eqtypeOrderUnitParams = TestDBUtility.getEqtypeOrderUnitRow();
            EqTypeOrderUnitImpl l_impl = new EqTypeOrderUnitImpl(null, l_eqtypeOrderUnitParams);
//            IfoOrderUnitParams l_ifoOrderUnitParams = TestDBUtility.getIfoOrderUnitRow();
//            l_ifoOrderUnitParams.setAccountId(333812512246L);
//            l_ifoOrderUnitParams.setConfirmedPrice(22.3);
//            l_ifoOrderUnitParams.setQuantity(18);
//            TestDBUtility.insertWithDel(l_ifoOrderUnitParams);
//            
//            IfoOrderUnit l_ifoOrderUnit = 
//                new IfoContractSettleOrderUnitImpl(l_ifoOrderUnitParams.getOrderUnitId());
            
            ThreadLocalSystemAttributesRegistry.setAttribute("xblocks.gtl.attributes.systemtimestamp",
                    new Timestamp(WEB3DateUtility.getDate("20070707170000", "yyyyMMddHHmmss").getTime()));
            
            TradingTimeParams l_tradingTimeParams = TestDBUtility.getTradingTimeRow();
            l_tradingTimeParams.setInstitutionCode("0D");
            l_tradingTimeParams.setBranchCode("123");
            l_tradingTimeParams.setMarketCode("N1");
            l_tradingTimeParams.setTradingTimeType("01");
            l_tradingTimeParams.setProductCode("0");
            l_tradingTimeParams.setBizDateType("1");
            l_tradingTimeParams.setBizdateCalcParameter("0");
            l_tradingTimeParams.setSubmitMarketTrigger("1");
            l_tradingTimeParams.setEndTime("160001");
            l_tradingTimeParams.setStartTime("00");
            TestDBUtility.insertWithDel(l_tradingTimeParams);
            
            TradingTimeParams l_tradingTimeParams1 = TestDBUtility.getTradingTimeRow();
            l_tradingTimeParams1.setInstitutionCode("0D");
            l_tradingTimeParams1.setBranchCode("123");
            l_tradingTimeParams1.setMarketCode("N1");
            l_tradingTimeParams1.setTradingTimeType("01");
            l_tradingTimeParams1.setProductCode("0");
            l_tradingTimeParams1.setBizDateType("0");
            l_tradingTimeParams1.setBizdateCalcParameter("0");
            l_tradingTimeParams1.setSubmitMarketTrigger("1");
            l_tradingTimeParams1.setEndTime("170001");
            l_tradingTimeParams1.setStartTime("00");
            TestDBUtility.insertWithDel(l_tradingTimeParams1);
            
//            TradingTimeParams l_tradingTimeParams1 = TestDBUtility.getTradingTimeRow();
//            l_tradingTimeParams1.setInstitutionCode("0D");
//            l_tradingTimeParams1.setBranchCode("123");
//            l_tradingTimeParams1.setMarketCode("N1");
//            l_tradingTimeParams1.setTradingTimeType("01");
//            l_tradingTimeParams1.setProductCode("0");
//            l_tradingTimeParams1.setBizDateType("1");
//            l_tradingTimeParams1.setBizdateCalcParameter("0");
//            l_tradingTimeParams1.setSubmitMarketTrigger("1");
//            l_tradingTimeParams1.setEndTime("140001");
//            l_tradingTimeParams1.setStartTime("00");
//            TestDBUtility.insertWithDel(l_tradingTimeParams1);
            
            BranchParams l_branchParams = TestDBUtility.getBranchRow();
            TestDBUtility.insertWithDel(l_branchParams);
            MarketParams l_marketParams = TestDBUtility.getMarketRow();
            l_marketParams.setMarketId(3303L);
            //市場コード（SONAR）
            
            l_marketParams.setSonarMarketCode("4");
            l_marketParams.setMarketId(3303L);
            
            TestDBUtility.insertWithDel(l_marketParams);
            l_toSuccDataGettingServiceImpl.setPossibleFlag(l_succOrderUnit, l_impl);
        }
        catch (Exception l_ex)
        {
            log.error(l_ex.getMessage(), l_ex);
            fail();
        }
        

//        l_toSuccDataGettingServiceImpl.setPossibleFlag();
        log.exiting(TEST_END + STR_METHOD_NAME); 
    }
    
    /**
     * 
     * 先物OP注文単位
     * 先物
     */
    public void test_setPossibleFlag_0003()
    {
        final String STR_METHOD_NAME = "test_setPossibleFlag_0003()";
        log.entering(TEST_START + STR_METHOD_NAME);
        
        WEB3ToSuccDataGettingServiceImpl l_toSuccDataGettingServiceImpl = new WEB3ToSuccDataGettingServiceImpl();
        WEB3SuccOrderUnit l_succOrderUnit = new WEB3SuccOrderUnit();
        l_succOrderUnit.commodityType = "3";

        try
        {

            IfoOrderUnitParams l_ifoOrderUnitParams = TestDBUtility.getIfoOrderUnitRow();
            l_ifoOrderUnitParams.setProductId(3304148080000L);
            l_ifoOrderUnitParams.setOrderId(56);
            l_ifoOrderUnitParams.setOrderUnitId(989);
            l_ifoOrderUnitParams.setMarketId(3303L);
            TestDBUtility.insertWithDel(l_ifoOrderUnitParams);
            
            ProductParams l_productParams = TestDBUtility.getProductRow();
            l_productParams.setProductId(3304148080000L);
            l_productParams.setProductType(ProductTypeEnum.IFO);
            TestDBUtility.insertWithDel(l_productParams);

            IfoProductParams l_ifoProductParams = TestDBUtility.getIfoProductRow();
            l_ifoProductParams.setProductId(3304148080000L);
            //原資産銘柄コード
            l_ifoProductParams.setUnderlyingProductCode("0019");
            //限月
            l_ifoProductParams.setMonthOfDelivery("200808");
            TestDBUtility.insertWithDel(l_ifoProductParams);
            
            IfoContractSettleOrderUnitImpl l_ifoOrderUnit= new IfoContractSettleOrderUnitImpl(989);
            
            
            OrderexecutionEndParams l_orderexecutionEndParams = TestDBUtility.getOrderexecutionEndRow();
            l_orderexecutionEndParams.setInstitutionCode("0D");
            l_orderexecutionEndParams.setProductType(ProductTypeEnum.IFO);
            l_orderexecutionEndParams.setFutureOptionDiv("1");
            l_orderexecutionEndParams.setOrderexecutionEndType("0");
            l_orderexecutionEndParams.setCarryoverEndType("1");
            TestDBUtility.insertWithDel(l_orderexecutionEndParams);
            
            InstitutionParams l_InstitutionParams = TestDBUtility.getInstitutionRow();
            TestDBUtility.insertWithDel(l_InstitutionParams);
            
            IfoOrderParams l_ifoOrderParams = TestDBUtility.getIfoOrderRow();
            l_ifoOrderParams.setOrderId(56);
            TestDBUtility.insertWithDel(l_ifoOrderParams);
            
            FinApp l_finApp = (FinApp) Services.getService(FinApp.class);
            TradingModule l_tradingModuleMock = GtlUtils.getTradingModule(ProductTypeEnum.EQUITY);
            l_tradingModuleMock = l_finApp.getTradingModule("eqtype");
            l_tradingModuleMock.overrideOrderManager(new WEB3EquityOrderManagerForMock());
            
            TestBaseForMock.MOCK_MANAGER.setIsMockUsed(true);
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                    "webbroker3.equity.WEB3EquityOrderManager",
                    "validateOrderForChangeability",
                    new Class[] {Order.class},
                    null);
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                    "webbroker3.equity.WEB3EquityOrderManager",
                    "validateOrderForCancellation",
                    new Class[] {Order.class},
                    null);
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                    "webbroker3.ifo.WEB3OptionOrderManagerImpl", 
                    "validateOrderChangePossibleStatus",
                    new Class[]
                    {Order.class},
                    null);
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                    "webbroker3.ifo.WEB3OptionOrderManagerImpl",
                    "validateOrderCancelPossibleStatus",
                    new Class[]
                    {Order.class},
                    null);
            
                    
//            EqtypeOrderUnitParams l_eqtypeOrderUnitParams = TestDBUtility.getEqtypeOrderUnitRow();
//            EqTypeOrderUnitImpl l_impl = new EqTypeOrderUnitImpl(null, l_eqtypeOrderUnitParams);
            
            ThreadLocalSystemAttributesRegistry.setAttribute("xblocks.gtl.attributes.systemtimestamp",
                    new Timestamp(WEB3DateUtility.getDate("20070707170000", "yyyyMMddHHmmss").getTime()));
            
            TradingTimeParams l_tradingTimeParams = TestDBUtility.getTradingTimeRow();
            l_tradingTimeParams.setInstitutionCode("0D");
            l_tradingTimeParams.setBranchCode("123");
            l_tradingTimeParams.setMarketCode("N1");
            l_tradingTimeParams.setTradingTimeType("01");
            l_tradingTimeParams.setProductCode("0");
            l_tradingTimeParams.setBizDateType("1");
            l_tradingTimeParams.setBizdateCalcParameter("0");
            l_tradingTimeParams.setSubmitMarketTrigger("1");
            l_tradingTimeParams.setEndTime("160001");
            l_tradingTimeParams.setStartTime("00");
            TestDBUtility.insertWithDel(l_tradingTimeParams);
            
            TradingTimeParams l_tradingTimeParams1 = TestDBUtility.getTradingTimeRow();
            l_tradingTimeParams1.setInstitutionCode("0D");
            l_tradingTimeParams1.setBranchCode("123");
            l_tradingTimeParams1.setMarketCode("N1");
            l_tradingTimeParams1.setTradingTimeType("01");
            l_tradingTimeParams1.setProductCode("0");
            l_tradingTimeParams1.setBizDateType("0");
            l_tradingTimeParams1.setBizdateCalcParameter("0");
            l_tradingTimeParams1.setSubmitMarketTrigger("1");
            l_tradingTimeParams1.setEndTime("170001");
            l_tradingTimeParams1.setStartTime("00");
            TestDBUtility.insertWithDel(l_tradingTimeParams1);
            
            BranchParams l_branchParams = TestDBUtility.getBranchRow();
            TestDBUtility.insertWithDel(l_branchParams);
            MarketParams l_marketParams = TestDBUtility.getMarketRow();
            l_marketParams.setMarketId(3303L);
            //市場コード（SONAR）
            
            l_marketParams.setSonarMarketCode("4");
            l_marketParams.setMarketId(3303L);
            
            TestDBUtility.insertWithDel(l_marketParams);
            l_toSuccDataGettingServiceImpl.setPossibleFlag(l_succOrderUnit, l_ifoOrderUnit);
        }
        catch (Exception l_ex)
        {
            log.error(l_ex.getMessage(), l_ex);
            fail();
        }
        

//        l_toSuccDataGettingServiceImpl.setPossibleFlag();
        log.exiting(TEST_END + STR_METHOD_NAME); 
    }
    
    /**
     * 
     * 先物OP注文単位
     * オプション
     */
    public void test_setPossibleFlag_0004()
    {
        final String STR_METHOD_NAME = "test_setPossibleFlag_0004()";
        log.entering(TEST_START + STR_METHOD_NAME);
        
        WEB3ToSuccDataGettingServiceImpl l_toSuccDataGettingServiceImpl = new WEB3ToSuccDataGettingServiceImpl();
        WEB3SuccOrderUnit l_succOrderUnit = new WEB3SuccOrderUnit();
        l_succOrderUnit.commodityType = "4";

        try
        {

            BranchPreferencesParams l_branchPreferencesParams = TestDBUtility.getBranchPreferencesRow();
            l_branchPreferencesParams.setBranchId(33381);
            l_branchPreferencesParams.setName("evening.session.div");
            l_branchPreferencesParams.setNameSerialNo(2);
            l_branchPreferencesParams.setValue("1");
            TestDBUtility.insertWithDel(l_branchPreferencesParams);
            
            IfoOrderUnitParams l_ifoOrderUnitParams = TestDBUtility.getIfoOrderUnitRow();
            l_ifoOrderUnitParams.setProductId(3304148080000L);
            l_ifoOrderUnitParams.setOrderId(56);
            l_ifoOrderUnitParams.setOrderUnitId(989);
            l_ifoOrderUnitParams.setMarketId(3303L);
            l_ifoOrderUnitParams.setSessionType(null);
            TestDBUtility.insertWithDel(l_ifoOrderUnitParams);
            
            ProductParams l_productParams = TestDBUtility.getProductRow();
            l_productParams.setProductId(3304148080000L);
            l_productParams.setProductType(ProductTypeEnum.IFO);
            TestDBUtility.insertWithDel(l_productParams);

            IfoProductParams l_ifoProductParams = TestDBUtility.getIfoProductRow();
            l_ifoProductParams.setProductId(3304148080000L);
            //原資産銘柄コード
            l_ifoProductParams.setUnderlyingProductCode("0019");
            //限月
            l_ifoProductParams.setMonthOfDelivery("200808");
            TestDBUtility.insertWithDel(l_ifoProductParams);
            
            IfoContractSettleOrderUnitImpl l_ifoOrderUnit= new IfoContractSettleOrderUnitImpl(989);
            
            
            OrderexecutionEndParams l_orderexecutionEndParams = TestDBUtility.getOrderexecutionEndRow();
            l_orderexecutionEndParams.setInstitutionCode("0D");
            l_orderexecutionEndParams.setProductType(ProductTypeEnum.IFO);
            l_orderexecutionEndParams.setFutureOptionDiv("1");
            l_orderexecutionEndParams.setOrderexecutionEndType("0");
            l_orderexecutionEndParams.setCarryoverEndType("1");
            TestDBUtility.insertWithDel(l_orderexecutionEndParams);
            
            InstitutionParams l_InstitutionParams = TestDBUtility.getInstitutionRow();
            TestDBUtility.insertWithDel(l_InstitutionParams);
            
            IfoOrderParams l_ifoOrderParams = TestDBUtility.getIfoOrderRow();
            l_ifoOrderParams.setOrderId(56);
            TestDBUtility.insertWithDel(l_ifoOrderParams);
            
            FinApp l_finApp = (FinApp) Services.getService(FinApp.class);
            TradingModule l_tradingModuleMock = GtlUtils.getTradingModule(ProductTypeEnum.EQUITY);
            l_tradingModuleMock = l_finApp.getTradingModule("eqtype");
            l_tradingModuleMock.overrideOrderManager(new WEB3EquityOrderManagerForMock());
            
            TestBaseForMock.MOCK_MANAGER.setIsMockUsed(true);
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                    "webbroker3.equity.WEB3EquityOrderManager",
                    "validateOrderForChangeability",
                    new Class[] {Order.class},
                    null);
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                    "webbroker3.equity.WEB3EquityOrderManager",
                    "validateOrderForCancellation",
                    new Class[] {Order.class},
                    null);
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                    "webbroker3.ifo.WEB3OptionOrderManagerImpl", 
                    "validateOrderChangePossibleStatus",
                    new Class[]
                    {Order.class},
                    null);
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                    "webbroker3.ifo.WEB3OptionOrderManagerImpl",
                    "validateOrderCancelPossibleStatus",
                    new Class[]
                    {Order.class},
                    null);
            
                    
//            EqtypeOrderUnitParams l_eqtypeOrderUnitParams = TestDBUtility.getEqtypeOrderUnitRow();
//            EqTypeOrderUnitImpl l_impl = new EqTypeOrderUnitImpl(null, l_eqtypeOrderUnitParams);
            
            ThreadLocalSystemAttributesRegistry.setAttribute("xblocks.gtl.attributes.systemtimestamp",
                    new Timestamp(WEB3DateUtility.getDate("20070707170000", "yyyyMMddHHmmss").getTime()));
            
            TradingTimeParams l_tradingTimeParams = TestDBUtility.getTradingTimeRow();
            l_tradingTimeParams.setInstitutionCode("0D");
            l_tradingTimeParams.setBranchCode("123");
            l_tradingTimeParams.setMarketCode("N1");
            l_tradingTimeParams.setTradingTimeType("01");
            l_tradingTimeParams.setProductCode("0");
            l_tradingTimeParams.setBizDateType("1");
            l_tradingTimeParams.setBizdateCalcParameter("0");
            l_tradingTimeParams.setSubmitMarketTrigger("1");
            l_tradingTimeParams.setEndTime("160001");
            l_tradingTimeParams.setStartTime("00");
            TestDBUtility.insertWithDel(l_tradingTimeParams);
            
            TradingTimeParams l_tradingTimeParams1 = TestDBUtility.getTradingTimeRow();
            l_tradingTimeParams1.setInstitutionCode("0D");
            l_tradingTimeParams1.setBranchCode("123");
            l_tradingTimeParams1.setMarketCode("N1");
            l_tradingTimeParams1.setTradingTimeType("01");
            l_tradingTimeParams1.setProductCode("0");
            l_tradingTimeParams1.setBizDateType("0");
            l_tradingTimeParams1.setBizdateCalcParameter("0");
            l_tradingTimeParams1.setSubmitMarketTrigger("1");
            l_tradingTimeParams1.setEndTime("170001");
            l_tradingTimeParams1.setStartTime("00");
            TestDBUtility.insertWithDel(l_tradingTimeParams1);
            
            BranchParams l_branchParams = TestDBUtility.getBranchRow();
            TestDBUtility.insertWithDel(l_branchParams);
            MarketParams l_marketParams = TestDBUtility.getMarketRow();
            l_marketParams.setMarketId(3303L);
            //市場コード（SONAR）
            
            l_marketParams.setSonarMarketCode("4");
            l_marketParams.setMarketId(3303L);
            
            TestDBUtility.insertWithDel(l_marketParams);
            l_toSuccDataGettingServiceImpl.setPossibleFlag(l_succOrderUnit, l_ifoOrderUnit);
        }
        catch (Exception l_ex)
        {
            log.error(l_ex.getMessage(), l_ex);
            fail();
        }
        

//        l_toSuccDataGettingServiceImpl.setPossibleFlag();
        log.exiting(TEST_END + STR_METHOD_NAME); 
    }
    
    /**
     *
     */
    public void test_createRsvOrderUnit_0001()
    {
        final String STR_METHOD_NAME = "test_createRsvOrderUnit_0001()";
        log.entering(TEST_START + STR_METHOD_NAME);
        
        WEB3ToSuccDataGettingServiceImpl l_toSuccDataGettingServiceImpl = new WEB3ToSuccDataGettingServiceImplForMock1();
        
        WEB3SuccOrderUnit l_requiredOrderUnit = new WEB3SuccOrderUnit();
//        EqtypeOrderUnitParams l_eqtypeOrderUnitParams = TestDBUtility.getEqtypeOrderUnitRow();
//        EqTypeOrderUnitImpl l_impl = new EqTypeOrderUnitImpl(null, l_eqtypeOrderUnitParams);
        RsvEqOrderUnitParams l_rsvEqOrderUnitParams = TestDBUtility.getRsvEqOrderUnitRow();
        l_rsvEqOrderUnitParams.setOrderId(1001L);
        l_rsvEqOrderUnitParams.setPriceAdjustValue(null);
        l_rsvEqOrderUnitParams.setOrderCateg(OrderCategEnum.SWAP_MARGIN);
        l_rsvEqOrderUnitParams.setLimitPrice(200);
        l_rsvEqOrderUnitParams.setMarketId(3303);
        l_rsvEqOrderUnitParams.setOrderUnitId(null);
        Row[] l_rsvOrderRowList = new Row[]{l_rsvEqOrderUnitParams};
        boolean l_blnIsPossibleFlagSet = true;
        try
        {
            TradingTimeParams l_tradingTimeParams = TestDBUtility.getTradingTimeRow();
            l_tradingTimeParams.setInstitutionCode("0D");
            l_tradingTimeParams.setBranchCode("123");
            l_tradingTimeParams.setMarketCode("SP");
            l_tradingTimeParams.setTradingTimeType("19");
            l_tradingTimeParams.setProductCode("0");
            l_tradingTimeParams.setBizDateType("1");
            l_tradingTimeParams.setBizdateCalcParameter("0");
            l_tradingTimeParams.setSubmitMarketTrigger("1");
            l_tradingTimeParams.setEndTime("160001");
            l_tradingTimeParams.setStartTime("00");
            TestDBUtility.insertWithDel(l_tradingTimeParams);
            
            ProductParams l_ProductParams = TestDBUtility.getProductRow();
            l_ProductParams.setProductId(3304148080001L);
            l_ProductParams.setInstitutionCode("0D");
            l_ProductParams.setProductType(ProductTypeEnum.EQUITY);
            TestDBUtility.insertWithDel(l_ProductParams);
            
            TestDBUtility.deleteAll(EqtypeOrderUnitParams.TYPE);
            EqtypeOrderUnitParams l_eqtypeOrderUnitParams =
                TestDBUtility.getEqtypeOrderUnitRow();
            l_eqtypeOrderUnitParams.setOrderOpenStatus(OrderOpenStatusEnum.OPEN);
            l_eqtypeOrderUnitParams.setOrderCateg(OrderCategEnum.SWAP_MARGIN);
            l_eqtypeOrderUnitParams.setOrderRequestNumber("1");
            l_eqtypeOrderUnitParams.setSonarTradedCode("12");
            l_eqtypeOrderUnitParams.setTraderId(null);
            l_eqtypeOrderUnitParams.setForcedSettleReasonType("2");
            l_eqtypeOrderUnitParams.setForcedExpireType("1");
            l_eqtypeOrderUnitParams.setOrderStatus(OrderStatusEnum.ACCEPTED);
            l_eqtypeOrderUnitParams.setOrderUnitId(1001);
            l_eqtypeOrderUnitParams.setExecutionConditionType(EqTypeExecutionConditionType.NONE);
            l_eqtypeOrderUnitParams.setOrderOpenStatus(OrderOpenStatusEnum.CLOSED);
            TestDBUtility.insertWithDel(l_eqtypeOrderUnitParams);
            
            SubAccountParams l_subAccountParams = TestDBUtility.getSubAccountRow();
            l_subAccountParams.setAccountId(101001010010L);
            l_subAccountParams.setSubAccountId(10100101001007L);
            TestDBUtility.insertWithDel(l_subAccountParams);
            InstitutionParams l_InstitutionParams = TestDBUtility.getInstitutionRow();
            TestDBUtility.insertWithDel(l_InstitutionParams);
            MarketParams l_MarketParams = TestDBUtility.getMarketRow();
            TestDBUtility.insertWithDel(l_MarketParams);
            
            l_toSuccDataGettingServiceImpl.createRsvOrderUnit(l_requiredOrderUnit, l_rsvOrderRowList, l_blnIsPossibleFlagSet);
        }
        catch (Exception l_ex)
        {
            log.error(l_ex.getMessage(), l_ex);
            fail();
        }
        
        log.exiting(TEST_END + STR_METHOD_NAME); 
    }
    
    /**
    *
    */
   public void test_createRsvOrderUnit_0002()
   {
       final String STR_METHOD_NAME = "test_createRsvOrderUnit_0002()";
       log.entering(TEST_START + STR_METHOD_NAME);
       
       WEB3ToSuccDataGettingServiceImpl l_toSuccDataGettingServiceImpl = new WEB3ToSuccDataGettingServiceImplForMock1();
       
       WEB3SuccOrderUnit l_requiredOrderUnit = new WEB3SuccOrderUnit();
//       EqtypeOrderUnitParams l_eqtypeOrderUnitParams = TestDBUtility.getEqtypeOrderUnitRow();
//       EqTypeOrderUnitImpl l_impl = new EqTypeOrderUnitImpl(null, l_eqtypeOrderUnitParams);
       RsvIfoOrderUnitParams l_rsvIfoOrderUnitParams = new RsvIfoOrderUnitParams();
       l_rsvIfoOrderUnitParams.setAccountId(11);
       l_rsvIfoOrderUnitParams.setMarketId(3303L);
       l_rsvIfoOrderUnitParams.setSubAccountId(123);
       l_rsvIfoOrderUnitParams.setBranchId(33381);
       l_rsvIfoOrderUnitParams.setOrderId(21);
       l_rsvIfoOrderUnitParams.setOrderType(OrderTypeEnum.UNDEFINED);
       l_rsvIfoOrderUnitParams.setOrderCateg(OrderCategEnum.UNDEFINED);
       l_rsvIfoOrderUnitParams.setLastOrderActionSerialNo(0);
       l_rsvIfoOrderUnitParams.setProductType(ProductTypeEnum.IFO);
       l_rsvIfoOrderUnitParams.setQuantity(23);
       l_rsvIfoOrderUnitParams.setOrderStatus(OrderStatusEnum.UNDEFINED);
       l_rsvIfoOrderUnitParams.setOrderOpenStatus(OrderOpenStatusEnum.UNDEFINED);
       l_rsvIfoOrderUnitParams.setExpirationStatus(OrderExpirationStatusEnum.UNDEFINED);
       l_rsvIfoOrderUnitParams.setTaxType(TaxTypeEnum.UNDEFINED);
       l_rsvIfoOrderUnitParams.setBizDate("20070117");
       l_rsvIfoOrderUnitParams.setProductId(3304148080000L);
       l_rsvIfoOrderUnitParams.setParentOrderId(56);
       l_rsvIfoOrderUnitParams.setSerialNoInParent(68);
       l_rsvIfoOrderUnitParams.setCreatedTimestamp(Calendar.getInstance().getTime());
       l_rsvIfoOrderUnitParams.setLastUpdatedTimestamp(Calendar.getInstance().getTime());
       
       //注文期限区分
       l_rsvIfoOrderUnitParams.setExpirationDateType("2");
       //注文有効期限
       l_rsvIfoOrderUnitParams.setExpirationDate(WEB3DateUtility.getDate("20080707", "yyyyMMdd"));
       //立会区分
       l_rsvIfoOrderUnitParams.setSessionType("0");
       //決済順序
       l_rsvIfoOrderUnitParams.setClosingOrder("3");
       //概算受渡代金
       l_rsvIfoOrderUnitParams.setEstimatedPrice(65);
       //指値
       l_rsvIfoOrderUnitParams.setLimitPrice(56);
       
       l_rsvIfoOrderUnitParams.setOrderUnitId(312);
       //注文種別
       l_rsvIfoOrderUnitParams.setOrderType(OrderTypeEnum.IDX_FUTURES_BUY_TO_OPEN);
       Row[] l_rsvOrderRowList = new Row[]{l_rsvIfoOrderUnitParams};
       boolean l_blnIsPossibleFlagSet = true;
       try
       {
           BranchParams l_BranchParams = TestDBUtility.getBranchRow();
           TestDBUtility.insertWithDel(l_BranchParams);
           TradingTimeParams l_tradingTimeParams = TestDBUtility.getTradingTimeRow();
           l_tradingTimeParams.setInstitutionCode("0D");
           l_tradingTimeParams.setBranchCode("123");
           l_tradingTimeParams.setMarketCode("SP");
           l_tradingTimeParams.setTradingTimeType("19");
           l_tradingTimeParams.setProductCode("0");
           l_tradingTimeParams.setBizDateType("1");
           l_tradingTimeParams.setBizdateCalcParameter("0");
           l_tradingTimeParams.setSubmitMarketTrigger("1");
           l_tradingTimeParams.setEndTime("160001");
           l_tradingTimeParams.setStartTime("00");
           TestDBUtility.insertWithDel(l_tradingTimeParams);
           
           ProductParams l_ProductParams = TestDBUtility.getProductRow();
           l_ProductParams.setProductId(3304148080000L);
           l_ProductParams.setInstitutionCode("0D");
           l_ProductParams.setProductType(ProductTypeEnum.IFO);
           TestDBUtility.insertWithDel(l_ProductParams);
           

           TestDBUtility.deleteAll(IfoOrderUnitParams.TYPE);
           IfoOrderUnitParams l_ifoOrderUnitParams =
               TestDBUtility.getIfoOrderUnitRow();
           l_ifoOrderUnitParams.setOrderUnitId(312);
           TestDBUtility.insertWithDel(l_ifoOrderUnitParams);
           
           IfoProductParams l_IfoProductParams = TestDBUtility.getIfoProductRow();
           l_IfoProductParams.setProductId(3304148080000L);
           TestDBUtility.insertWithDel(l_IfoProductParams);
           SubAccountParams l_subAccountParams = TestDBUtility.getSubAccountRow();
           l_subAccountParams.setAccountId(11);
           l_subAccountParams.setSubAccountId(123);
           TestDBUtility.insertWithDel(l_subAccountParams);
           InstitutionParams l_InstitutionParams = TestDBUtility.getInstitutionRow();
           TestDBUtility.insertWithDel(l_InstitutionParams);
           MarketParams l_MarketParams = TestDBUtility.getMarketRow();
           TestDBUtility.insertWithDel(l_MarketParams);
           
           l_toSuccDataGettingServiceImpl.createRsvOrderUnit(l_requiredOrderUnit, l_rsvOrderRowList, l_blnIsPossibleFlagSet);
       }
       catch (Exception l_ex)
       {
           log.error(l_ex.getMessage(), l_ex);
           fail();
       }
       
       log.exiting(TEST_END + STR_METHOD_NAME); 
   }
   
   /**
   *
   */
  public void test_createRsvOrderUnit_0003()
  {
      final String STR_METHOD_NAME = "test_createRsvOrderUnit_0003()";
      log.entering(TEST_START + STR_METHOD_NAME);
      
      WEB3ToSuccDataGettingServiceImpl l_toSuccDataGettingServiceImpl = new WEB3ToSuccDataGettingServiceImplForMock1();
      
      WEB3SuccOrderUnit l_requiredOrderUnit = new WEB3SuccOrderUnit();
//      EqtypeOrderUnitParams l_eqtypeOrderUnitParams = TestDBUtility.getEqtypeOrderUnitRow();
//      EqTypeOrderUnitImpl l_impl = new EqTypeOrderUnitImpl(null, l_eqtypeOrderUnitParams);
      RsvIfoOrderUnitParams l_rsvIfoOrderUnitParams = new RsvIfoOrderUnitParams();
      l_rsvIfoOrderUnitParams.setAccountId(11);
      l_rsvIfoOrderUnitParams.setMarketId(3303L);
      l_rsvIfoOrderUnitParams.setSubAccountId(123);
      l_rsvIfoOrderUnitParams.setBranchId(33381);
      l_rsvIfoOrderUnitParams.setOrderId(21);
      l_rsvIfoOrderUnitParams.setOrderType(OrderTypeEnum.UNDEFINED);
      l_rsvIfoOrderUnitParams.setOrderCateg(OrderCategEnum.UNDEFINED);
      l_rsvIfoOrderUnitParams.setLastOrderActionSerialNo(0);
      l_rsvIfoOrderUnitParams.setProductType(ProductTypeEnum.IFO);
      l_rsvIfoOrderUnitParams.setQuantity(23);
      l_rsvIfoOrderUnitParams.setOrderStatus(OrderStatusEnum.UNDEFINED);
      l_rsvIfoOrderUnitParams.setOrderOpenStatus(OrderOpenStatusEnum.UNDEFINED);
      l_rsvIfoOrderUnitParams.setExpirationStatus(OrderExpirationStatusEnum.UNDEFINED);
      l_rsvIfoOrderUnitParams.setTaxType(TaxTypeEnum.UNDEFINED);
      l_rsvIfoOrderUnitParams.setBizDate("20070117");
      l_rsvIfoOrderUnitParams.setProductId(3304148080000L);
      l_rsvIfoOrderUnitParams.setParentOrderId(56);
      l_rsvIfoOrderUnitParams.setSerialNoInParent(68);
      l_rsvIfoOrderUnitParams.setCreatedTimestamp(Calendar.getInstance().getTime());
      l_rsvIfoOrderUnitParams.setLastUpdatedTimestamp(Calendar.getInstance().getTime());
      
      //注文期限区分
      l_rsvIfoOrderUnitParams.setExpirationDateType("2");
      //注文有効期限
      l_rsvIfoOrderUnitParams.setExpirationDate(WEB3DateUtility.getDate("20080707", "yyyyMMdd"));
      //立会区分
      l_rsvIfoOrderUnitParams.setSessionType(null);
      //決済順序
      l_rsvIfoOrderUnitParams.setClosingOrder("3");
      //概算受渡代金
      l_rsvIfoOrderUnitParams.setEstimatedPrice(65);
      //指値
      l_rsvIfoOrderUnitParams.setLimitPrice(56);
      
      l_rsvIfoOrderUnitParams.setOrderUnitId(312);
      //注文種別
      l_rsvIfoOrderUnitParams.setOrderType(OrderTypeEnum.IDX_FUTURES_BUY_TO_OPEN);
      
      l_rsvIfoOrderUnitParams.setPriceAdjustValue(88);
      Row[] l_rsvOrderRowList = new Row[]{l_rsvIfoOrderUnitParams};
      boolean l_blnIsPossibleFlagSet = true;
      try
      {
          BranchParams l_BranchParams = TestDBUtility.getBranchRow();
          TestDBUtility.insertWithDel(l_BranchParams);
          TradingTimeParams l_tradingTimeParams = TestDBUtility.getTradingTimeRow();
          l_tradingTimeParams.setInstitutionCode("0D");
          l_tradingTimeParams.setBranchCode("123");
          l_tradingTimeParams.setMarketCode("SP");
          l_tradingTimeParams.setTradingTimeType("19");
          l_tradingTimeParams.setProductCode("0");
          l_tradingTimeParams.setBizDateType("1");
          l_tradingTimeParams.setBizdateCalcParameter("0");
          l_tradingTimeParams.setSubmitMarketTrigger("1");
          l_tradingTimeParams.setEndTime("160001");
          l_tradingTimeParams.setStartTime("00");
          TestDBUtility.insertWithDel(l_tradingTimeParams);
          
          ProductParams l_ProductParams = TestDBUtility.getProductRow();
          l_ProductParams.setProductId(3304148080000L);
          l_ProductParams.setInstitutionCode("0D");
          l_ProductParams.setProductType(ProductTypeEnum.IFO);
          TestDBUtility.insertWithDel(l_ProductParams);
          

          TestDBUtility.deleteAll(IfoOrderUnitParams.TYPE);
          IfoOrderUnitParams l_ifoOrderUnitParams =
              TestDBUtility.getIfoOrderUnitRow();
          l_ifoOrderUnitParams.setOrderUnitId(312);
          TestDBUtility.insertWithDel(l_ifoOrderUnitParams);
          
          IfoProductParams l_IfoProductParams = TestDBUtility.getIfoProductRow();
          l_IfoProductParams.setProductId(3304148080000L);
          TestDBUtility.insertWithDel(l_IfoProductParams);
          SubAccountParams l_subAccountParams = TestDBUtility.getSubAccountRow();
          l_subAccountParams.setAccountId(11);
          l_subAccountParams.setSubAccountId(123);
          TestDBUtility.insertWithDel(l_subAccountParams);
          InstitutionParams l_InstitutionParams = TestDBUtility.getInstitutionRow();
          TestDBUtility.insertWithDel(l_InstitutionParams);
          MarketParams l_MarketParams = TestDBUtility.getMarketRow();
          TestDBUtility.insertWithDel(l_MarketParams);
          
          l_toSuccDataGettingServiceImpl.createRsvOrderUnit(l_requiredOrderUnit, l_rsvOrderRowList, l_blnIsPossibleFlagSet);
      }
      catch (Exception l_ex)
      {
          log.error(l_ex.getMessage(), l_ex);
          fail();
      }
      
      log.exiting(TEST_END + STR_METHOD_NAME); 
  }
  
    private class WEB3ToSuccDataGettingServiceImplForTest extends WEB3ToSuccDataGettingServiceImpl
    {
        public Product getProduct(long l_lngProductId, ProductTypeEnum l_productType) throws WEB3BaseException
        {

            ProductForTest l_ifoProduct = null;
            try
            {
                l_ifoProduct = new ProductForTest(3304148080000L);
            }
            catch(Exception l_ex)
            {
                fail();
            }
            return l_ifoProduct;
        }
        
        public String getTransactionState(OrderUnit l_orderUnit) throws WEB3BaseException
        {
            return "begin";
        }
    }
    
    private class IfoOrderUnitForTest implements IfoOrderUnit
    {

        public ProductTypeEnum getProductType()
        {
            // TODO Auto-generated method stub
            return null;
        }

        public boolean isFuturesOrder()
        {
            // TODO Auto-generated method stub
            return false;
        }

        public boolean isOptionsOrder()
        {
            // TODO Auto-generated method stub
            return false;
        }

        public boolean isCallOptionsOrder()
        {
            // TODO Auto-generated method stub
            return false;
        }

        public boolean isPutOptionsOrder()
        {
            // TODO Auto-generated method stub
            return false;
        }

        public boolean isOpenContractOrder()
        {
            // TODO Auto-generated method stub
            return false;
        }

        public boolean isSettleContractOrder()
        {
            // TODO Auto-generated method stub
            return false;
        }

        public IfoOrderExecutionConditionType getExecutionConditionType()
        {
            // TODO Auto-generated method stub
            return null;
        }

        public long getOrderUnitId()
        {
            // TODO Auto-generated method stub
            return 0;
        }

        public long getOrderId()
        {
            // TODO Auto-generated method stub
            return 0;
        }

        public long getAccountId()
        {
            // TODO Auto-generated method stub
            return 0;
        }

        public long getSubAccountId()
        {
            // TODO Auto-generated method stub
            return 0;
        }

        public long getBranchId()
        {
            // TODO Auto-generated method stub
            return 0;
        }

        public long getTraderId()
        {
            // TODO Auto-generated method stub
            return 0;
        }

        public OrderAction[] getOrderActions()
        {
            // TODO Auto-generated method stub
            return null;
        }

        public Timestamp getReceivedTimestamp()
        {
            // TODO Auto-generated method stub
            return null;
        }

        public Timestamp getExpirationTimestamp()
        {
            // TODO Auto-generated method stub
            return null;
        }

        public Product getProduct()
        {
            // TODO Auto-generated method stub
            return null;
        }

        public Order getOrder()
        {
            // TODO Auto-generated method stub
            return null;
        }

        public OrderExecution[] getExecutions()
        {
            // TODO Auto-generated method stub
            return null;
        }

        public OrderOpenStatusEnum getOrderOpenStatus()
        {
            // TODO Auto-generated method stub
            return null;
        }

        public OrderTypeEnum getOrderType()
        {
            // TODO Auto-generated method stub
            return null;
        }

        public OrderCategEnum getOrderCateg()
        {
            // TODO Auto-generated method stub
            return null;
        }

        public TaxTypeEnum getTaxType()
        {
            // TODO Auto-generated method stub
            return null;
        }

        public SideEnum getSide()
        {
            // TODO Auto-generated method stub
            return null;
        }

        public OrderStatusEnum getOrderStatus()
        {
            // TODO Auto-generated method stub
            return null;
        }

        public OrderExpirationStatusEnum getExpirationStatus()
        {
            // TODO Auto-generated method stub
            return null;
        }

        public boolean isExpired()
        {
            // TODO Auto-generated method stub
            return false;
        }

        public boolean isFullyExecuted()
        {
            // TODO Auto-generated method stub
            return false;
        }

        public boolean isPartiallyExecuted()
        {
            // TODO Auto-generated method stub
            return false;
        }

        public boolean isUnexecuted()
        {
            // TODO Auto-generated method stub
            return false;
        }

        public double getConfirmedPrice()
        {
            // TODO Auto-generated method stub
            return 0;
        }

        public boolean isConfirmedPriceMarketOrder()
        {
            // TODO Auto-generated method stub
            return false;
        }

        public double getConfirmedQuantity()
        {
            // TODO Auto-generated method stub
            return 0;
        }

        public double getQuantity()
        {
            // TODO Auto-generated method stub
            return 0;
        }

        public Date getDeliveryDate()
        {
            // TODO Auto-generated method stub
            return null;
        }

        public double getExecutedAmount()
        {
            // TODO Auto-generated method stub
            return 0;
        }

        public double getExecutedQuantity()
        {
            // TODO Auto-generated method stub
            return 0;
        }

        public double getLimitPrice()
        {
            // TODO Auto-generated method stub
            return 0;
        }

        public boolean isMarketOrder()
        {
            // TODO Auto-generated method stub
            return false;
        }

        public TradedProduct getTradedProduct()
        {
            // TODO Auto-generated method stub
            return null;
        }

        public Object getDataSourceObject()
        {
            // TODO Auto-generated method stub
            
            return TestDBUtility.getIfoOrderUnitRow();
        }
        
    }
}
@
