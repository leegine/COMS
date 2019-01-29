head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.04.07.01.19.50;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	5444d9d078c63f4;
filename	WEB3ToSuccOptionChangeOpenContractServiceImplTest.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : WEB3ToSuccOptionChangeOpenContractServiceImplTest.java
Author Name      : Daiwa Institute of Research
Revision History : 2008/04/21 于瀟（中訊）新規作成
*/
package webbroker3.triggerorder.service.delegate.stdimpls;

import java.sql.Timestamp;
import java.util.Calendar;
import java.util.Date;

import com.fitechlabs.xtrade.kernel.util.ThreadLocalSystemAttributesRegistry;
import com.fitechlabs.xtrade.plugin.security.oplogin.impl.LoginInfoImplForMock;
import com.fitechlabs.xtrade.plugin.tc.gentrade.Institution;
import com.fitechlabs.xtrade.plugin.tc.gentrade.OrderCategEnum;
import com.fitechlabs.xtrade.plugin.tc.gentrade.OrderExpirationStatusEnum;
import com.fitechlabs.xtrade.plugin.tc.gentrade.OrderManagerPersistenceContext;
import com.fitechlabs.xtrade.plugin.tc.gentrade.OrderOpenStatusEnum;
import com.fitechlabs.xtrade.plugin.tc.gentrade.OrderStatusEnum;
import com.fitechlabs.xtrade.plugin.tc.gentrade.OrderTypeEnum;
import com.fitechlabs.xtrade.plugin.tc.gentrade.OrderUnit;
import com.fitechlabs.xtrade.plugin.tc.gentrade.ProcessingResult;
import com.fitechlabs.xtrade.plugin.tc.gentrade.ProductTypeEnum;
import com.fitechlabs.xtrade.plugin.tc.gentrade.SideEnum;
import com.fitechlabs.xtrade.plugin.tc.gentrade.SubAccount;
import com.fitechlabs.xtrade.plugin.tc.gentrade.SubAccountTypeEnum;
import com.fitechlabs.xtrade.plugin.tc.gentrade.TaxTypeEnum;
import com.fitechlabs.xtrade.plugin.tc.gentrade.data.BranchParams;
import com.fitechlabs.xtrade.plugin.tc.gentrade.data.BranchRow;
import com.fitechlabs.xtrade.plugin.tc.gentrade.data.InstitutionParams;
import com.fitechlabs.xtrade.plugin.tc.gentrade.data.MainAccountParams;
import com.fitechlabs.xtrade.plugin.tc.gentrade.data.MarketParams;
import com.fitechlabs.xtrade.plugin.tc.gentrade.data.MarketRow;
import com.fitechlabs.xtrade.plugin.tc.gentrade.data.ProductParams;
import com.fitechlabs.xtrade.plugin.tc.gentrade.data.ProductRow;
import com.fitechlabs.xtrade.plugin.tc.gentrade.data.SubAccountParams;
import com.fitechlabs.xtrade.plugin.tc.gentrade.data.TradedProductParams;
import com.fitechlabs.xtrade.plugin.tc.gentrade.data.TraderParams;
import com.fitechlabs.xtrade.plugin.tc.gentrade.ordersubmitter.io.NewOrderValidationResult;
import com.fitechlabs.xtrade.plugin.tc.xbifo.IfoDerivativeTypeEnum;
import com.fitechlabs.xtrade.plugin.tc.xbifo.IfoOrderUnit;
import com.fitechlabs.xtrade.plugin.tc.xbifo.data.IfoOrderUnitParams;
import com.fitechlabs.xtrade.plugin.tc.xbifo.data.IfoProductParams;
import com.fitechlabs.xtrade.plugin.tc.xbifo.data.IfoProductRow;
import com.fitechlabs.xtrade.plugin.tc.xbifo.data.IfoTradedProductParams;

import test.util.TestDBUtility;

import webbroker3.common.WEB3BaseException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.common.define.WEB3OrderExpirationDateTypeDef;
import webbroker3.common.define.WEB3ReserveOrderTradingTypeDef;
import webbroker3.gentrade.WEB3GentradeCommission;
import webbroker3.gentrade.WEB3GentradeSubAccount;
import webbroker3.gentrade.WEB3GentradeTradingTimeManagementForMock;
import webbroker3.gentrade.data.EnableOrderConditionParams;
import webbroker3.gentrade.data.EnableOrderConditionRow;
import webbroker3.gentrade.data.OrderexecutionEndParams;
import webbroker3.gentrade.data.OrderexecutionEndRow;
import webbroker3.gentrade.data.TradingTimeParams;
import webbroker3.gentrade.data.TradingTimeRow;
import webbroker3.ifo.WEB3IfoEstimateDeliveryAmountCalcResult;
import webbroker3.ifo.WEB3IfoOpenContractOrderSpec;
import webbroker3.ifo.WEB3IfoProductImpl;
import webbroker3.ifo.WEB3IfoTradedProductImpl;
import webbroker3.ifo.define.WEB3IfoContractTypeDef;
import webbroker3.ifo.message.WEB3FuturesOpenMarginChangeCompleteRequest;
import webbroker3.ifo.message.WEB3OptionsCommonRequest;
import webbroker3.mock.TestBaseForMock;
import webbroker3.triggerorder.WEB3ToSuccIfoChangeOpenContractOrderSpec;
import webbroker3.triggerorder.WEB3ToSuccIfoOrderUnitImpl;
import webbroker3.triggerorder.base.data.RsvIfoOrderUnitParams;
import webbroker3.triggerorder.base.data.RsvIfoOrderUnitRow;
import webbroker3.triggerorder.define.WEB3ToSuccSignDivDef;
import webbroker3.triggerorder.message.WEB3SuccOptionsOpenChangeCompleteRequest;
import webbroker3.triggerorder.message.WEB3SuccOptionsOpenChangeCompleteResponse;
import webbroker3.triggerorder.message.WEB3SuccOptionsOpenChangeConfirmRequest;
import webbroker3.triggerorder.message.WEB3SuccOptionsOpenChangeConfirmResponse;
import webbroker3.triggerorder.message.WEB3SuccOptionsOpenCompleteRequest;
import webbroker3.triggerorder.message.WEB3SuccOptionsOpenConfirmRequest;
import webbroker3.triggerorder.message.WEB3SuccPriceAdjustmentValueInfo;
import webbroker3.triggerorder.util.WEB3ToSuccOrderManagerUtility;
import webbroker3.util.WEB3DateUtility;
import webbroker3.util.WEB3LogUtility;

public class WEB3ToSuccOptionChangeOpenContractServiceImplTest extends TestBaseForMock
{
    /**
     * ログ出力ユーティリティ。<BR>
     */
    private static WEB3LogUtility log = WEB3LogUtility.getInstance(
            WEB3ToSuccOptionChangeOpenContractServiceImplTest.class);

    public WEB3ToSuccOptionChangeOpenContractServiceImplTest(String arg0)
    {
        super(arg0);
    }

    protected void setUp() throws Exception
    {
        super.setUp();
        MOCK_MANAGER.setIsMockUsed(true);
        WEB3ToSuccOrderManagerUtility.changeOrderManager();
        ThreadLocalSystemAttributesRegistry.setAttribute(
            "xblocks.gtl.attributes.systemtimestamp",
            new Timestamp(WEB3DateUtility.getDate("20080520", "yyyyMMdd").getTime()));
    }

    protected void tearDown() throws Exception
    {
        super.tearDown();
        TestDBUtility.deleteAll(ProductRow.TYPE);
        TestDBUtility.deleteAll(IfoProductRow.TYPE);
        TestDBUtility.deleteAll(MarketRow.TYPE);
        TestDBUtility.deleteAll(TradingTimeRow.TYPE);
    }

    /**
     * SYSTEM_ERROR_80017
     * 
     * l_request == null
     */
    public void testExecute_C0001()
    {
        final String STR_METHOD_NAME = "testExecute_C0001()";
        log.entering(STR_METHOD_NAME);
        try
        {
            WEB3ToSuccOptionChangeOpenContractServiceImpl l_toSuccOptionChangeOpenContractServiceImpl =
                new WEB3ToSuccOptionChangeOpenContractServiceImpl();
            l_toSuccOptionChangeOpenContractServiceImpl.execute(null);
            log.exiting(TEST_END + STR_METHOD_NAME);
            fail();
        }
        catch (WEB3BaseException l_ex)
        {
            log.error(l_ex.getMessage(), l_ex);
            log.exiting(TEST_END + STR_METHOD_NAME);
            assertEquals(WEB3ErrorCatalog.SYSTEM_ERROR_80017, l_ex.getErrorInfo());
        }
        catch(Exception l_ex)
        {
            log.error(l_ex.getMessage(), l_ex);
            log.exiting(TEST_END + STR_METHOD_NAME);
            fail();
        }
    }

    /**
     * 正常終了
     * 
     * l_request == WEB3SuccOptionsOpenChangeConfirmRequest
     */
    public void testExecute_C0002()
    {
        final String STR_METHOD_NAME = "testExecute_C0002()";
        log.entering(STR_METHOD_NAME);
        
        WEB3SubExecuteToSuccOptionChangeOpenContractServiceImpl l_subExecuteToSuccOptionChangeOpenContractServiceImpl =
            new WEB3SubExecuteToSuccOptionChangeOpenContractServiceImpl();
        WEB3SuccOptionsOpenChangeConfirmRequest l_request = new WEB3SuccOptionsOpenChangeConfirmRequest();
        try
        {
            WEB3SuccOptionsOpenChangeConfirmResponse l_response =
                (WEB3SuccOptionsOpenChangeConfirmResponse)l_subExecuteToSuccOptionChangeOpenContractServiceImpl.execute(l_request);
            assertEquals("1", l_response.checkPrice);
            log.exiting(TEST_END + STR_METHOD_NAME);
        }
        catch(Exception l_ex)
        {
            log.error(l_ex.getMessage(), l_ex);
            log.exiting(TEST_END + STR_METHOD_NAME);
            fail();
        }
    }
    
    /**
     * 正常終了
     * 
     * WEB3SuccOptionsOpenChangeCompleteRequest 
     */
    public void testExecute_C0003()
    {
        final String STR_METHOD_NAME = "testExecute_C0003()";
        log.entering(STR_METHOD_NAME);
        
        WEB3SubExecuteToSuccOptionChangeOpenContractServiceImpl l_subExecuteToSuccOptionChangeOpenContractServiceImpl =
            new WEB3SubExecuteToSuccOptionChangeOpenContractServiceImpl();
        WEB3SuccOptionsOpenChangeCompleteRequest l_request = new WEB3SuccOptionsOpenChangeCompleteRequest();
        try
        {
            WEB3SuccOptionsOpenChangeCompleteResponse l_response =
                (WEB3SuccOptionsOpenChangeCompleteResponse)l_subExecuteToSuccOptionChangeOpenContractServiceImpl.execute(l_request);
            assertEquals("2", l_response.orderActionId);
            log.exiting(TEST_END + STR_METHOD_NAME);
        }
        catch(Exception l_ex)
        {
            log.error(l_ex.getMessage(), l_ex);
            log.exiting(TEST_END + STR_METHOD_NAME);
            fail();
        }
    }
    
    protected class WEB3SubExecuteToSuccOptionChangeOpenContractServiceImpl extends WEB3ToSuccOptionChangeOpenContractServiceImpl
    {
        protected WEB3SuccOptionsOpenChangeConfirmResponse validateOrder(
            WEB3SuccOptionsOpenChangeConfirmRequest l_request) throws WEB3BaseException
        {
            WEB3SuccOptionsOpenChangeConfirmResponse l_response =
                (WEB3SuccOptionsOpenChangeConfirmResponse)l_request.createResponse();
            l_response.checkPrice = "1";
            return l_response;
        }
        
        protected WEB3SuccOptionsOpenChangeCompleteResponse submitOrder(
                WEB3SuccOptionsOpenChangeCompleteRequest l_request) throws WEB3BaseException
        {
            WEB3SuccOptionsOpenChangeCompleteResponse l_response =
                (WEB3SuccOptionsOpenChangeCompleteResponse)l_request.createResponse();
            l_response.orderActionId = "2";
            return l_response;
        }
    }
    
    /**
     * SYSTEM_ERROR_80018
     * 
     * l_request == WEB3FuturesOpenMarginChangeCompleteRequest
     */
    public void testExecute_C0004()
    {
        final String STR_METHOD_NAME = "testExecute_C0004()";
        log.entering(STR_METHOD_NAME);
        
        WEB3ToSuccOptionChangeOpenContractServiceImpl l_toSuccOptionChangeOpenContractServiceImpl
            = new WEB3ToSuccOptionChangeOpenContractServiceImpl();
        WEB3FuturesOpenMarginChangeCompleteRequest l_request = new WEB3FuturesOpenMarginChangeCompleteRequest();
        try
        {
            l_toSuccOptionChangeOpenContractServiceImpl.execute(l_request);
            log.exiting(TEST_END + STR_METHOD_NAME);
            fail();
        }
        catch (WEB3BaseException l_ex)
        {
            log.error(l_ex.getMessage(), l_ex);
            log.exiting(TEST_END + STR_METHOD_NAME);
            assertEquals(WEB3ErrorCatalog.SYSTEM_ERROR_80018, l_ex.getErrorInfo());
        }
        catch(Exception l_ex)
        {
            log.error(l_ex.getMessage(), l_ex);
            log.exiting(TEST_END + STR_METHOD_NAME);
            fail();
        }
    }

    public void testValidateOrder_C0001()
    {
        final String STR_METHOD_NAME = "testValidateOrder_C0001()";
        log.entering(STR_METHOD_NAME);
        MOCK_MANAGER.setIsMockUsed(true);
        
        WEB3ToSuccOptionChangeOpenContractServiceImpl l_impl = new WEB3ToSuccOptionChangeOpenContractServiceImpl();
        WEB3SuccOptionsOpenChangeConfirmRequest l_request = new WEB3SuccOptionsOpenChangeConfirmRequest();
        l_request.orderPriceDiv = "0";
        l_request.limitPrice = null;
        l_request.execCondType = "1";
        l_request.expirationDateType = "1";
        l_request.expirationDate = null;
        l_request.orderCondType = "0";
        l_request.stopOrderCondPrice = null;
        l_request.stopOrderCondOperator = null;
        l_request.wlimitOrderCondPrice = null;
        l_request.wlimitOrderCondOperator = null;
        l_request.wLimitOrderPriceDiv = null;
        l_request.wLimitPrice = null;
        l_request.wlimitExecCondType = null;
        l_request.wlimitEnableStatusDiv = null;
        //super.validate()
        l_request.id = "0001";
        l_request.opOrderQuantity = "1000";
        //連続注文単価調整値情報==nullの場合
        l_request.priceAdjustmentValueInfo = null;
        try
        {
            TestDBUtility.deleteAll(IfoOrderUnitParams.TYPE);
            TestDBUtility.deleteAll(SubAccountParams.TYPE);
            TestDBUtility.deleteAll(ProductParams.TYPE);
            TestDBUtility.deleteAll(IfoProductParams.TYPE);
            TestDBUtility.deleteAll(TraderParams.TYPE);
            TestDBUtility.deleteAll(SubAccountParams.TYPE);
            TestDBUtility.deleteAll(MainAccountParams.TYPE);
            TestDBUtility.deleteAll(RsvIfoOrderUnitParams.TYPE);
            TestDBUtility.deleteAll(BranchParams.TYPE);
            TestDBUtility.deleteAll(IfoProductParams.TYPE);
            TestDBUtility.deleteAll(TradedProductParams.TYPE);
            TestDBUtility.deleteAll(IfoTradedProductParams.TYPE);
            TestDBUtility.deleteAll(MarketParams.TYPE);
            TestDBUtility.deleteAll(IfoOrderUnitParams.TYPE);
            
            TestDBUtility.deleteAll(TradingTimeParams.TYPE);
            TestDBUtility.deleteAll(InstitutionParams.TYPE);
            
            SubAccountParams l_subAccountParams = TestDBUtility.getSubAccountRow();
            l_subAccountParams.setAccountId(333812512246L);
            l_subAccountParams.setSubAccountType(SubAccountTypeEnum.EQUITY_SUB_ACCOUNT);
            TestDBUtility.insertWithDel(l_subAccountParams);
            MainAccountParams l_mainAccountParams = TestDBUtility.getMainAccountRow();
            l_mainAccountParams.setIfoAccOpenDivTokyo("1");
            l_mainAccountParams.setQuotoType("1");
            TestDBUtility.insertWithDel(l_mainAccountParams);
            
            InstitutionParams l_institutionParams = TestDBUtility.getInstitutionRow();
            l_institutionParams.setInstitutionId(33);
            TestDBUtility.insertWithDel(l_institutionParams);
            
            TestDBUtility.deleteAll(RsvIfoOrderUnitRow.TYPE);
            RsvIfoOrderUnitParams l_rsvIfoOrderUnitParams = TestDBUtility.getRsvIfoOrderUnitRow();
            l_rsvIfoOrderUnitParams.setFutureOptionDiv("2");
            l_rsvIfoOrderUnitParams.setProductId(3304148080000L);
            l_rsvIfoOrderUnitParams.setOrderId(0001L);
            l_rsvIfoOrderUnitParams.setMarketId(231);
            l_rsvIfoOrderUnitParams.setReserveOrderTradingType("16");
            TestDBUtility.insertWithDel(l_rsvIfoOrderUnitParams);
            
            WEB3GentradeTradingTimeManagementForMock.mockGetOrderBizDate(WEB3DateUtility.getDate("20080520", "yyyyMMdd"));
            WEB3GentradeTradingTimeManagementForMock.mockGetBizDateType(new Timestamp(WEB3DateUtility.getDate("20080520", "yyyyMMdd").getTime()), "1");
//            TestDBUtility.deleteAll(TradingTimeRow.TYPE);
//            TradingTimeParams l_tradingTimeParams = TestDBUtility.getTradingTimeRow();
//            l_tradingTimeParams.setInstitutionCode("0D");
//            l_tradingTimeParams.setBranchCode("123");
//            l_tradingTimeParams.setMarketCode("N1");
//            l_tradingTimeParams.setTradingTimeType("01");
//            l_tradingTimeParams.setProductCode("0");
//            l_tradingTimeParams.setBizDateType("2");
//            TestDBUtility.insertWithDel(l_tradingTimeParams);
            
            TestDBUtility.deleteAll(BranchRow.TYPE);
            BranchParams l_branchParams = TestDBUtility.getBranchRow();
            l_branchParams.setBranchId(33381L);
            l_branchParams.setCloseWorngOption(0);
            TestDBUtility.insertWithDel(l_branchParams);
            
            TestDBUtility.deleteAll(IfoOrderUnitParams.TYPE);
            IfoOrderUnitParams l_ifoOrderUnitParams = TestDBUtility.getIfoOrderUnitRow();
            l_ifoOrderUnitParams.setOrderId(1001);
            l_ifoOrderUnitParams.setProductId(3304148080000L);
            l_ifoOrderUnitParams.setMarketId(231);
            TestDBUtility.insertWithDel(l_ifoOrderUnitParams);
            
            TestDBUtility.deleteAll(OrderexecutionEndRow.TYPE);
            OrderexecutionEndParams l_orderexecutionEndParams = TestDBUtility.getOrderexecutionEndRow();
            l_orderexecutionEndParams.setInstitutionCode("0D");
            l_orderexecutionEndParams.setProductType(ProductTypeEnum.IFO);
            l_orderexecutionEndParams.setFutureOptionDiv("2");
            l_orderexecutionEndParams.setOrderexecutionEndType("0");
            TestDBUtility.insertWithDel(l_orderexecutionEndParams);
            
            ProductParams l_productParams = TestDBUtility.getProductRow();
            l_productParams.setProductId(3304148080000L);
            l_productParams.setProductType(ProductTypeEnum.IFO);
            TestDBUtility.insertWithDel(l_productParams);
            
            IfoProductParams l_ifoProductParams = TestDBUtility.getIfoProductRow();
            l_ifoProductParams.setProductId(3304148080000L);
            l_ifoProductParams.setProductType(ProductTypeEnum.IFO);
            l_ifoProductParams.setDerivativeType(IfoDerivativeTypeEnum.PUT_OPTIONS);
            l_ifoProductParams.setStrikePrice(223);
            TestDBUtility.insertWithDel(l_ifoProductParams);
            
            MarketParams l_marketParams = TestDBUtility.getMarketRow();
            l_marketParams.setMarketId(231);
            l_marketParams.setMarketCode("1");
            TestDBUtility.insertWithDel(l_marketParams);
            
            TradedProductParams l_tradedProductParams = TestDBUtility.getTradedProductRow();
            l_tradedProductParams.setTradedProductId(1006160060005L);
            l_tradedProductParams.setProductId(3304148080000L);
            l_tradedProductParams.setMarketId(231L);
            l_tradedProductParams.setProductType(ProductTypeEnum.IFO);
            TestDBUtility.insertWithDel(l_tradedProductParams);
            
            IfoTradedProductParams l_ifoTradedProductParams = TestDBUtility.getIfoTradedProductRow();
            l_ifoTradedProductParams.setTradedProductId(1006160060005L);
            l_ifoTradedProductParams.setMarketId(231L);
            l_ifoTradedProductParams.setProductId(3304148080000L);
            l_ifoTradedProductParams.setValidForBizDate(null);
            TestDBUtility.insertWithDel(l_ifoTradedProductParams);
            
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                    "com.fitechlabs.xtrade.plugin.security.oplogin.impl.OpLoginSecurityServiceImpl", "getAccountId",
                    new Class[] {}, new Long("333812512246"));
            
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                    "com.fitechlabs.xtrade.plugin.security.oplogin.impl.OpLoginSecurityServiceImpl", "getLoginInfo",
                    new Class[] {},
                    new LoginInfoImplForMock());
            
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                    "com.fitechlabs.xtrade.plugin.security.oplogin.impl.LoginInfoImpl",
                    "getLoginId",
                    new Class[] {},
                    new Long(256));
            
            WEB3IfoProductImpl l_ifoProductImpl = new WEB3IfoProductImpl(3304148080000L);
            
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue("webbroker3.ifo.WEB3OptionOrderManagerImpl",
                    "notifyRLS", new Class[]
                    { IfoOrderUnit.class, OrderManagerPersistenceContext.class },
                    null);
            
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue("webbroker3.triggerorder.WEB3ToSuccOrderManagerImpl",
                    "submitIfoOpenContractNewOrder", new Class[]
                    {SubAccount.class, WEB3IfoOpenContractOrderSpec.class, long.class, String.class, String.class, Double.class, IfoOrderUnit.class, WEB3IfoEstimateDeliveryAmountCalcResult.class},
                    null);
            
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                    "webbroker3.triggerorder.WEB3ToSuccOrderManagerImpl",
                    "validateSuccOrderMaxQuantity", new Class[]
                    {OrderUnit.class},
                    null);
            
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                    "webbroker3.triggerorder.WEB3ToSuccOrderManagerImpl", "isReversingTrade", new Class[]
                    {String.class, OrderUnit.class},
                    Boolean.FALSE);
            
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue("webbroker3.triggerorder.WEB3ToSuccOrderManagerImpl",
                    "validateSuccOrder", new Class[]
                    {WEB3GentradeSubAccount.class, ProductTypeEnum.class, String.class, String.class, OrderUnit.class},
                    null);
                    
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                    "webbroker3.ifo.WEB3IfoProductManagerImpl",
                    "getIfoProduct",
                    new Class[] 
                    {Institution.class, String.class, String.class, IfoDerivativeTypeEnum.class,
                     double.class, String.class, String.class},
                     l_ifoProductImpl);
            
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                    "webbroker3.ifo.WEB3IfoProductManagerImpl",
                    "getIfoProduct",
                    new Class[] { Institution.class, String.class },
                    l_ifoProductImpl);
            
            WEB3IfoTradedProductImpl l_ifoTradedProductImpl = new WEB3IfoTradedProductImpl(1006160060005L);
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                    "webbroker3.ifo.WEB3IfoProductManagerImpl",
                    "getIfoTradedProduct",
                    new Class[] { Institution.class, String.class, String.class },
                    l_ifoTradedProductImpl);
            
            WEB3IfoEstimateDeliveryAmountCalcResult l_ifoEstimateDeliveryAmountCalcResult =
                new WEB3IfoEstimateDeliveryAmountCalcResult();
            
            l_ifoEstimateDeliveryAmountCalcResult.setEstimateDeliveryAmount(0);
            l_ifoEstimateDeliveryAmountCalcResult.setCommissionCourse("12");
            l_ifoEstimateDeliveryAmountCalcResult.setCommission(23);
            l_ifoEstimateDeliveryAmountCalcResult.setCommissionTax(36);
            l_ifoEstimateDeliveryAmountCalcResult.setCalcUnitPrice(56);
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                    "webbroker3.ifo.WEB3FuturesOrderManagerImpl", "calcEstimateDeliveryAmount",
                    new Class[]
                    {WEB3GentradeCommission.class,
                     double.class,
                     SubAccount.class,
                     WEB3IfoTradedProductImpl.class,
                     double.class,
                     SideEnum.class,
                     boolean.class,
                     boolean.class},
                     l_ifoEstimateDeliveryAmountCalcResult);
            
            ProcessingResult processingResult = ProcessingResult.newSuccessResultInstance();
            NewOrderValidationResult l_result = new NewOrderValidationResult(processingResult);
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                    "webbroker3.ifo.WEB3FuturesOrderManagerImpl",
                    "validateOpenContractOrder", new Class[]
                    { WEB3GentradeSubAccount.class, WEB3IfoOpenContractOrderSpec.class },
                    l_result);
            
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                    "com.fitechlabs.xtrade.plugin.security.oplogin.impl.OpLoginSecurityServiceImpl", 
                    "getSessionProperty",
                    new Class[] {String.class},
                    "");
 
            WEB3SuccOptionsOpenChangeConfirmResponse l_response = l_impl.validateOrder(l_request);
            assertEquals("0", l_response.partExecQuantity);
            assertEquals("0", l_response.estimatedPrice);
            assertEquals("12", l_response.commissionCourse);
            assertEquals("23", l_response.commission);
            assertEquals("36", l_response.commissionConsumptionTax);
            assertNull(l_response.messageSuspension);
            assertEquals("56", l_response.checkPrice);
            assertEquals("20080520", WEB3DateUtility.formatDate(l_response.checkDate, "yyyyMMdd"));
            assertEquals("20080520", WEB3DateUtility.formatDate(l_response.expirationDate, "yyyyMMdd"));
            assertNull(l_response.afterAdjustmentPrice);
        }
        catch (WEB3BaseException l_ex)
        {
            log.error(l_ex.getMessage(), l_ex);
            log.exiting(TEST_END + STR_METHOD_NAME);
            fail();
        }
        catch(Exception l_ex)
        {
            log.error(l_ex.getMessage(), l_ex);
            log.exiting(TEST_END + STR_METHOD_NAME);
            fail();
        }
        
        log.exiting(TEST_END + STR_METHOD_NAME);
    }
    
    public void testValidateOrder_C0002()
    {
        final String STR_METHOD_NAME = "testValidateOrder_C0002()";
        log.entering(STR_METHOD_NAME);
        MOCK_MANAGER.setIsMockUsed(true);
        
        WEB3ToSuccOptionChangeOpenContractServiceImpl l_impl = new WEB3ToSuccOptionChangeOpenContractServiceImpl();
        WEB3SuccOptionsOpenChangeConfirmRequest l_request = new WEB3SuccOptionsOpenChangeConfirmRequest();
        l_request.orderPriceDiv = "0";
        l_request.limitPrice = null;
        l_request.execCondType = "1";
        l_request.expirationDateType = "1";
        l_request.expirationDate = null;
        l_request.orderCondType = "0";
        l_request.stopOrderCondPrice = null;
        l_request.stopOrderCondOperator = null;
        l_request.wlimitOrderCondPrice = null;
        l_request.wlimitOrderCondOperator = null;
        l_request.wLimitOrderPriceDiv = null;
        l_request.wLimitPrice = null;
        l_request.wlimitExecCondType = null;
        l_request.wlimitEnableStatusDiv = null;
        //super.validate()
        l_request.id = "0001";
        l_request.opOrderQuantity = "1000";
        //連続注文単価調整値情報!=nullの場合
        WEB3SuccPriceAdjustmentValueInfo l_succPriceAdjustmentValueInfo =
            new WEB3SuccPriceAdjustmentValueInfo();
        l_succPriceAdjustmentValueInfo.sign = WEB3ToSuccSignDivDef.ADD;
        l_succPriceAdjustmentValueInfo.priceAdjustmentValue = "123";
        l_request.priceAdjustmentValueInfo = l_succPriceAdjustmentValueInfo;
        try
        {
            TestDBUtility.deleteAll(IfoOrderUnitParams.TYPE);
            TestDBUtility.deleteAll(SubAccountParams.TYPE);
            TestDBUtility.deleteAll(ProductParams.TYPE);
            TestDBUtility.deleteAll(IfoProductParams.TYPE);
            TestDBUtility.deleteAll(TraderParams.TYPE);
            TestDBUtility.deleteAll(SubAccountParams.TYPE);
            TestDBUtility.deleteAll(MainAccountParams.TYPE);
            TestDBUtility.deleteAll(RsvIfoOrderUnitParams.TYPE);
            TestDBUtility.deleteAll(BranchParams.TYPE);
            TestDBUtility.deleteAll(IfoProductParams.TYPE);
            TestDBUtility.deleteAll(TradedProductParams.TYPE);
            TestDBUtility.deleteAll(IfoTradedProductParams.TYPE);
            TestDBUtility.deleteAll(MarketParams.TYPE);
            TestDBUtility.deleteAll(IfoOrderUnitParams.TYPE);
            
            TestDBUtility.deleteAll(TradingTimeParams.TYPE);
            TestDBUtility.deleteAll(InstitutionParams.TYPE);
            
            SubAccountParams l_subAccountParams = TestDBUtility.getSubAccountRow();
            l_subAccountParams.setAccountId(333812512246L);
            l_subAccountParams.setSubAccountType(SubAccountTypeEnum.EQUITY_SUB_ACCOUNT);
            TestDBUtility.insertWithDel(l_subAccountParams);
            MainAccountParams l_mainAccountParams = TestDBUtility.getMainAccountRow();
            l_mainAccountParams.setIfoAccOpenDivTokyo("1");
            l_mainAccountParams.setQuotoType("1");
            TestDBUtility.insertWithDel(l_mainAccountParams);
            
            InstitutionParams l_institutionParams = TestDBUtility.getInstitutionRow();
            l_institutionParams.setInstitutionId(33);
            TestDBUtility.insertWithDel(l_institutionParams);
            
            TestDBUtility.deleteAll(RsvIfoOrderUnitRow.TYPE);
            RsvIfoOrderUnitParams l_rsvIfoOrderUnitParams = TestDBUtility.getRsvIfoOrderUnitRow();
            l_rsvIfoOrderUnitParams.setFutureOptionDiv("2");
            l_rsvIfoOrderUnitParams.setProductId(3304148080000L);
            l_rsvIfoOrderUnitParams.setOrderId(0001L);
            l_rsvIfoOrderUnitParams.setMarketId(231);
            l_rsvIfoOrderUnitParams.setReserveOrderTradingType(WEB3ReserveOrderTradingTypeDef.OPEN_OP_ASSUMPTION_ORDER);
            TestDBUtility.insertWithDel(l_rsvIfoOrderUnitParams);
            
            WEB3GentradeTradingTimeManagementForMock.mockGetOrderBizDate(WEB3DateUtility.getDate("20080430", "yyyyMMdd"));
            WEB3GentradeTradingTimeManagementForMock.mockGetBizDateType(new Timestamp(WEB3DateUtility.getDate("20080430", "yyyyMMdd").getTime()), "1");
//            TestDBUtility.deleteAll(TradingTimeRow.TYPE);
//            TradingTimeParams l_tradingTimeParams = TestDBUtility.getTradingTimeRow();
//            l_tradingTimeParams.setInstitutionCode("0D");
//            l_tradingTimeParams.setBranchCode("123");
//            l_tradingTimeParams.setMarketCode("N1");
//            l_tradingTimeParams.setTradingTimeType("01");
//            l_tradingTimeParams.setProductCode("0");
//            l_tradingTimeParams.setBizDateType("2");
//            TestDBUtility.insertWithDel(l_tradingTimeParams);
            
            TestDBUtility.deleteAll(BranchRow.TYPE);
            BranchParams l_branchParams = TestDBUtility.getBranchRow();
            l_branchParams.setBranchId(33381L);
            l_branchParams.setCloseWorngOption(0);
            TestDBUtility.insertWithDel(l_branchParams);
            
            TestDBUtility.deleteAll(IfoOrderUnitParams.TYPE);
            IfoOrderUnitParams l_ifoOrderUnitParams = TestDBUtility.getIfoOrderUnitRow();
            l_ifoOrderUnitParams.setOrderId(1001);
            l_ifoOrderUnitParams.setProductId(3304148080000L);
            l_ifoOrderUnitParams.setMarketId(231);
            TestDBUtility.insertWithDel(l_ifoOrderUnitParams);
            
            TestDBUtility.deleteAll(OrderexecutionEndRow.TYPE);
            OrderexecutionEndParams l_orderexecutionEndParams = TestDBUtility.getOrderexecutionEndRow();
            l_orderexecutionEndParams.setInstitutionCode("0D");
            l_orderexecutionEndParams.setProductType(ProductTypeEnum.IFO);
            l_orderexecutionEndParams.setFutureOptionDiv("2");
            l_orderexecutionEndParams.setOrderexecutionEndType("0");
            TestDBUtility.insertWithDel(l_orderexecutionEndParams);
            
            ProductParams l_productParams = TestDBUtility.getProductRow();
            l_productParams.setProductId(3304148080000L);
            l_productParams.setProductType(ProductTypeEnum.IFO);
            TestDBUtility.insertWithDel(l_productParams);
            
            IfoProductParams l_ifoProductParams = TestDBUtility.getIfoProductRow();
            l_ifoProductParams.setProductId(3304148080000L);
            l_ifoProductParams.setProductType(ProductTypeEnum.IFO);
            l_ifoProductParams.setDerivativeType(IfoDerivativeTypeEnum.PUT_OPTIONS);
            l_ifoProductParams.setStrikePrice(223);
            TestDBUtility.insertWithDel(l_ifoProductParams);
            
            MarketParams l_marketParams = TestDBUtility.getMarketRow();
            l_marketParams.setMarketId(231);
            l_marketParams.setMarketCode("1");
            TestDBUtility.insertWithDel(l_marketParams);
            
            TradedProductParams l_tradedProductParams = TestDBUtility.getTradedProductRow();
            l_tradedProductParams.setTradedProductId(1006160060005L);
            l_tradedProductParams.setProductId(3304148080000L);
            l_tradedProductParams.setMarketId(231L);
            l_tradedProductParams.setProductType(ProductTypeEnum.IFO);
            TestDBUtility.insertWithDel(l_tradedProductParams);
            
            IfoTradedProductParams l_ifoTradedProductParams = TestDBUtility.getIfoTradedProductRow();
            l_ifoTradedProductParams.setTradedProductId(1006160060005L);
            l_ifoTradedProductParams.setMarketId(231L);
            l_ifoTradedProductParams.setProductId(3304148080000L);
            l_ifoTradedProductParams.setValidForBizDate(null);
            TestDBUtility.insertWithDel(l_ifoTradedProductParams);
            TestDBUtility.commit();
            
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                    "com.fitechlabs.xtrade.plugin.security.oplogin.impl.OpLoginSecurityServiceImpl", "getAccountId",
                    new Class[] {}, new Long("333812512246"));
            
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                    "com.fitechlabs.xtrade.plugin.security.oplogin.impl.OpLoginSecurityServiceImpl", "getLoginInfo",
                    new Class[] {},
                    new LoginInfoImplForMock());
            
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                    "com.fitechlabs.xtrade.plugin.security.oplogin.impl.LoginInfoImpl",
                    "getLoginId",
                    new Class[] {},
                    new Long(256));
            
            WEB3IfoProductImpl l_ifoProductImpl = new WEB3IfoProductImpl(3304148080000L);
            
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue("webbroker3.ifo.WEB3OptionOrderManagerImpl",
                    "notifyRLS", new Class[]
                    { IfoOrderUnit.class, OrderManagerPersistenceContext.class },
                    null);
            
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue("webbroker3.triggerorder.WEB3ToSuccOrderManagerImpl",
                    "submitIfoOpenContractNewOrder", new Class[]
                    {SubAccount.class, WEB3IfoOpenContractOrderSpec.class, long.class, String.class, String.class, Double.class, IfoOrderUnit.class, WEB3IfoEstimateDeliveryAmountCalcResult.class},
                    null);
            
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                    "webbroker3.triggerorder.WEB3ToSuccOrderManagerImpl",
                    "validateSuccOrderMaxQuantity", new Class[]
                    {OrderUnit.class},
                    null);
            
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                    "webbroker3.triggerorder.WEB3ToSuccOrderManagerImpl", "isReversingTrade", new Class[]
                    {String.class, OrderUnit.class},
                    Boolean.FALSE);
            
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue("webbroker3.triggerorder.WEB3ToSuccOrderManagerImpl",
                    "validateSuccOrder", new Class[]
                    {WEB3GentradeSubAccount.class, ProductTypeEnum.class, String.class, String.class, OrderUnit.class},
                    null);
                    
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                    "webbroker3.ifo.WEB3IfoProductManagerImpl",
                    "getIfoProduct",
                    new Class[] 
                    {Institution.class, String.class, String.class, IfoDerivativeTypeEnum.class,
                     double.class, String.class, String.class},
                     l_ifoProductImpl);
            
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                    "webbroker3.ifo.WEB3IfoProductManagerImpl",
                    "getIfoProduct",
                    new Class[] { Institution.class, String.class },
                    l_ifoProductImpl);
            
            WEB3IfoTradedProductImpl l_ifoTradedProductImpl = new WEB3IfoTradedProductImpl(1006160060005L);
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                    "webbroker3.ifo.WEB3IfoProductManagerImpl",
                    "getIfoTradedProduct",
                    new Class[] { Institution.class, String.class, String.class },
                    l_ifoTradedProductImpl);
            
            WEB3IfoEstimateDeliveryAmountCalcResult l_ifoEstimateDeliveryAmountCalcResult =
                new WEB3IfoEstimateDeliveryAmountCalcResult();
            
            l_ifoEstimateDeliveryAmountCalcResult.setEstimateDeliveryAmount(0);
            l_ifoEstimateDeliveryAmountCalcResult.setCommissionCourse("12");
            l_ifoEstimateDeliveryAmountCalcResult.setCommission(23);
            l_ifoEstimateDeliveryAmountCalcResult.setCommissionTax(36);
            l_ifoEstimateDeliveryAmountCalcResult.setCalcUnitPrice(56);
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                    "webbroker3.ifo.WEB3FuturesOrderManagerImpl", "calcEstimateDeliveryAmount",
                    new Class[]
                    {WEB3GentradeCommission.class,
                     double.class,
                     SubAccount.class,
                     WEB3IfoTradedProductImpl.class,
                     double.class,
                     SideEnum.class,
                     boolean.class,
                     boolean.class},
                     l_ifoEstimateDeliveryAmountCalcResult);
            
            ProcessingResult processingResult = ProcessingResult.newSuccessResultInstance();
            NewOrderValidationResult l_result = new NewOrderValidationResult(processingResult);
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                    "webbroker3.ifo.WEB3FuturesOrderManagerImpl",
                    "validateOpenContractOrder", new Class[]
                    { WEB3GentradeSubAccount.class, WEB3IfoOpenContractOrderSpec.class },
                    l_result);
            
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                    "com.fitechlabs.xtrade.plugin.security.oplogin.impl.OpLoginSecurityServiceImpl", 
                    "getSessionProperty",
                    new Class[] {String.class},
                    "");
 
            WEB3SuccOptionsOpenChangeConfirmResponse l_response = l_impl.validateOrder(l_request);
            assertEquals("0", l_response.partExecQuantity);
            assertEquals("0", l_response.estimatedPrice);
            assertEquals("12", l_response.commissionCourse);
            assertEquals("23", l_response.commission);
            assertEquals("36", l_response.commissionConsumptionTax);
            assertNull(l_response.messageSuspension);
            assertNull(l_response.checkPrice);
            Date l_datResult1 = WEB3DateUtility.getDate("20080430", "yyyyMMdd");
            assertEquals(l_datResult1, l_response.checkDate);
            Date l_datResult2 = WEB3DateUtility.getDate("20080430", "yyyyMMdd");
            assertEquals(l_datResult2, l_response.expirationDate);
            assertEquals("131", l_response.afterAdjustmentPrice);
        }
        catch (WEB3BaseException l_ex)
        {
            log.error(l_ex.getMessage(), l_ex);
            log.exiting(TEST_END + STR_METHOD_NAME);
            fail();
        }
        catch(Exception l_ex)
        {
            log.error(l_ex.getMessage(), l_ex);
            log.exiting(TEST_END + STR_METHOD_NAME);
            fail();
        }
        
        log.exiting(TEST_END + STR_METHOD_NAME);
    }
    
    /**
     * BUSINESS_ERROR_00184
     *
     * リクエストデータの整合性をチェックする。
     */
    public void testValidateOrder_C0003()
    {
        final String STR_METHOD_NAME = "testValidateOrder_C0003()";
        log.entering(STR_METHOD_NAME);
        
        WEB3ToSuccOptionChangeOpenContractServiceImpl l_impl = new WEB3ToSuccOptionChangeOpenContractServiceImpl();
        WEB3SuccOptionsOpenChangeConfirmRequest l_request = new WEB3SuccOptionsOpenChangeConfirmRequest();
        l_request.orderPriceDiv = null;
        try
        {
            l_impl.validateOrder(l_request);
        }
        catch (WEB3BaseException l_ex)
        {
            log.error(l_ex.getMessage(), l_ex);
            log.exiting(TEST_END + STR_METHOD_NAME);
            assertEquals(WEB3ErrorCatalog.BUSINESS_ERROR_00184, l_ex.getErrorInfo());
        }
        catch(Exception l_ex)
        {
            log.error(l_ex.getMessage(), l_ex);
            log.exiting(TEST_END + STR_METHOD_NAME);
            fail();
        }
    }
    
    /**
     * BUSINESS_ERROR_02287
     * 
     * validate訂正可能状態
     */
    public void testValidateOrder_C0004()
    {

        final String STR_METHOD_NAME = "testValidateOrder_C0001()";
        log.entering(STR_METHOD_NAME);
        MOCK_MANAGER.setIsMockUsed(true);
        
        WEB3ToSuccOptionChangeOpenContractServiceImpl l_impl = new WEB3ToSuccOptionChangeOpenContractServiceImpl();
        WEB3SuccOptionsOpenChangeConfirmRequest l_request = new WEB3SuccOptionsOpenChangeConfirmRequest();
        l_request.orderPriceDiv = "0";
        l_request.limitPrice = null;
        l_request.execCondType = "1";
        l_request.expirationDateType = "1";
        l_request.expirationDate = null;
        l_request.orderCondType = "0";
        l_request.stopOrderCondPrice = null;
        l_request.stopOrderCondOperator = null;
        l_request.wlimitOrderCondPrice = null;
        l_request.wlimitOrderCondOperator = null;
        l_request.wLimitOrderPriceDiv = null;
        l_request.wLimitPrice = null;
        l_request.wlimitExecCondType = null;
        l_request.wlimitEnableStatusDiv = null;
        //super.validate()
        l_request.id = "0001";
        l_request.opOrderQuantity = "1000";
        //連続注文単価調整値情報==nullの場合
        l_request.priceAdjustmentValueInfo = null;
        try
        {
            TestDBUtility.deleteAll(IfoOrderUnitParams.TYPE);
            TestDBUtility.deleteAll(SubAccountParams.TYPE);
            TestDBUtility.deleteAll(ProductParams.TYPE);
            TestDBUtility.deleteAll(IfoProductParams.TYPE);
            TestDBUtility.deleteAll(TraderParams.TYPE);
            TestDBUtility.deleteAll(SubAccountParams.TYPE);
            TestDBUtility.deleteAll(MainAccountParams.TYPE);
            TestDBUtility.deleteAll(RsvIfoOrderUnitParams.TYPE);
            TestDBUtility.deleteAll(BranchParams.TYPE);
            TestDBUtility.deleteAll(IfoProductParams.TYPE);
            TestDBUtility.deleteAll(TradedProductParams.TYPE);
            TestDBUtility.deleteAll(IfoTradedProductParams.TYPE);
            TestDBUtility.deleteAll(MarketParams.TYPE);
            TestDBUtility.deleteAll(IfoOrderUnitParams.TYPE);
            
            TestDBUtility.deleteAll(TradingTimeParams.TYPE);
            TestDBUtility.deleteAll(InstitutionParams.TYPE);
            
            SubAccountParams l_subAccountParams = TestDBUtility.getSubAccountRow();
            l_subAccountParams.setAccountId(333812512246L);
            l_subAccountParams.setSubAccountType(SubAccountTypeEnum.EQUITY_SUB_ACCOUNT);
            TestDBUtility.insertWithDel(l_subAccountParams);
            MainAccountParams l_mainAccountParams = TestDBUtility.getMainAccountRow();
            l_mainAccountParams.setIfoAccOpenDivTokyo("1");
            l_mainAccountParams.setQuotoType("1");
            TestDBUtility.insertWithDel(l_mainAccountParams);
            
            InstitutionParams l_institutionParams = TestDBUtility.getInstitutionRow();
            l_institutionParams.setInstitutionId(33);
            TestDBUtility.insertWithDel(l_institutionParams);
            
            TestDBUtility.deleteAll(RsvIfoOrderUnitRow.TYPE);
            RsvIfoOrderUnitParams l_rsvIfoOrderUnitParams = TestDBUtility.getRsvIfoOrderUnitRow();
            l_rsvIfoOrderUnitParams.setFutureOptionDiv("2");
            l_rsvIfoOrderUnitParams.setOrderOpenStatus(OrderOpenStatusEnum.CLOSED);
            l_rsvIfoOrderUnitParams.setProductId(3304148080000L);
            l_rsvIfoOrderUnitParams.setOrderId(0001L);
            l_rsvIfoOrderUnitParams.setMarketId(231);
            l_rsvIfoOrderUnitParams.setReserveOrderTradingType("16");
            TestDBUtility.insertWithDel(l_rsvIfoOrderUnitParams);
            
            TestDBUtility.deleteAll(TradingTimeRow.TYPE);
            TradingTimeParams l_tradingTimeParams = TestDBUtility.getTradingTimeRow();
            l_tradingTimeParams.setInstitutionCode("0D");
            l_tradingTimeParams.setBranchCode("123");
            l_tradingTimeParams.setMarketCode("N1");
            l_tradingTimeParams.setTradingTimeType("01");
            l_tradingTimeParams.setProductCode("0");
            l_tradingTimeParams.setBizDateType("1");
            TestDBUtility.insertWithDel(l_tradingTimeParams);
            
            TestDBUtility.deleteAll(BranchRow.TYPE);
            BranchParams l_branchParams = TestDBUtility.getBranchRow();
            l_branchParams.setBranchId(33381L);
            l_branchParams.setCloseWorngOption(0);
            TestDBUtility.insertWithDel(l_branchParams);
            
            TestDBUtility.deleteAll(IfoOrderUnitParams.TYPE);
            IfoOrderUnitParams l_ifoOrderUnitParams = TestDBUtility.getIfoOrderUnitRow();
            l_ifoOrderUnitParams.setOrderId(1001);
            l_ifoOrderUnitParams.setProductId(3304148080000L);
            l_ifoOrderUnitParams.setMarketId(231);
            TestDBUtility.insertWithDel(l_ifoOrderUnitParams);
            
            TestDBUtility.deleteAll(OrderexecutionEndRow.TYPE);
            OrderexecutionEndParams l_orderexecutionEndParams = TestDBUtility.getOrderexecutionEndRow();
            l_orderexecutionEndParams.setInstitutionCode("0D");
            l_orderexecutionEndParams.setProductType(ProductTypeEnum.IFO);
            l_orderexecutionEndParams.setFutureOptionDiv("2");
            l_orderexecutionEndParams.setOrderexecutionEndType("0");
            TestDBUtility.insertWithDel(l_orderexecutionEndParams);
            
            ProductParams l_productParams = TestDBUtility.getProductRow();
            l_productParams.setProductId(3304148080000L);
            l_productParams.setProductType(ProductTypeEnum.IFO);
            TestDBUtility.insertWithDel(l_productParams);
            
            IfoProductParams l_ifoProductParams = TestDBUtility.getIfoProductRow();
            l_ifoProductParams.setProductId(3304148080000L);
            l_ifoProductParams.setProductType(ProductTypeEnum.IFO);
            l_ifoProductParams.setDerivativeType(IfoDerivativeTypeEnum.PUT_OPTIONS);
            l_ifoProductParams.setStrikePrice(223);
            TestDBUtility.insertWithDel(l_ifoProductParams);
            
            MarketParams l_marketParams = TestDBUtility.getMarketRow();
            l_marketParams.setMarketId(231);
            l_marketParams.setMarketCode("1");
            TestDBUtility.insertWithDel(l_marketParams);
            
            TradedProductParams l_tradedProductParams = TestDBUtility.getTradedProductRow();
            l_tradedProductParams.setTradedProductId(1006160060005L);
            l_tradedProductParams.setProductId(3304148080000L);
            l_tradedProductParams.setMarketId(231L);
            l_tradedProductParams.setProductType(ProductTypeEnum.IFO);
            TestDBUtility.insertWithDel(l_tradedProductParams);
            
            IfoTradedProductParams l_ifoTradedProductParams = TestDBUtility.getIfoTradedProductRow();
            l_ifoTradedProductParams.setTradedProductId(1006160060005L);
            l_ifoTradedProductParams.setMarketId(231L);
            l_ifoTradedProductParams.setProductId(3304148080000L);
            l_ifoTradedProductParams.setValidForBizDate(null);
            TestDBUtility.insertWithDel(l_ifoTradedProductParams);
            
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                    "com.fitechlabs.xtrade.plugin.security.oplogin.impl.OpLoginSecurityServiceImpl", "getAccountId",
                    new Class[] {}, new Long("333812512246"));
            
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                    "com.fitechlabs.xtrade.plugin.security.oplogin.impl.OpLoginSecurityServiceImpl", "getLoginInfo",
                    new Class[] {},
                    new LoginInfoImplForMock());
            
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                    "com.fitechlabs.xtrade.plugin.security.oplogin.impl.LoginInfoImpl",
                    "getLoginId",
                    new Class[] {},
                    new Long(256));
            
            WEB3IfoProductImpl l_ifoProductImpl = new WEB3IfoProductImpl(3304148080000L);
            
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue("webbroker3.ifo.WEB3OptionOrderManagerImpl",
                    "notifyRLS", new Class[]
                    { IfoOrderUnit.class, OrderManagerPersistenceContext.class },
                    null);
            
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue("webbroker3.triggerorder.WEB3ToSuccOrderManagerImpl",
                    "submitIfoOpenContractNewOrder", new Class[]
                    {SubAccount.class, WEB3IfoOpenContractOrderSpec.class, long.class, String.class, String.class, Double.class, IfoOrderUnit.class, WEB3IfoEstimateDeliveryAmountCalcResult.class},
                    null);
            
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                    "webbroker3.triggerorder.WEB3ToSuccOrderManagerImpl",
                    "validateSuccOrderMaxQuantity", new Class[]
                    {OrderUnit.class},
                    null);
            
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                    "webbroker3.triggerorder.WEB3ToSuccOrderManagerImpl", "isReversingTrade", new Class[]
                    {String.class, OrderUnit.class},
                    Boolean.FALSE);
            
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue("webbroker3.triggerorder.WEB3ToSuccOrderManagerImpl",
                    "validateSuccOrder", new Class[]
                    {WEB3GentradeSubAccount.class, ProductTypeEnum.class, String.class, String.class, OrderUnit.class},
                    null);
                    
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                    "webbroker3.ifo.WEB3IfoProductManagerImpl",
                    "getIfoProduct",
                    new Class[] 
                    {Institution.class, String.class, String.class, IfoDerivativeTypeEnum.class,
                     double.class, String.class, String.class},
                     l_ifoProductImpl);
            
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                    "webbroker3.ifo.WEB3IfoProductManagerImpl",
                    "getIfoProduct",
                    new Class[] { Institution.class, String.class },
                    l_ifoProductImpl);
            
            WEB3IfoTradedProductImpl l_ifoTradedProductImpl = new WEB3IfoTradedProductImpl(1006160060005L);
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                    "webbroker3.ifo.WEB3IfoProductManagerImpl",
                    "getIfoTradedProduct",
                    new Class[] { Institution.class, String.class, String.class },
                    l_ifoTradedProductImpl);
            
            WEB3IfoEstimateDeliveryAmountCalcResult l_ifoEstimateDeliveryAmountCalcResult =
                new WEB3IfoEstimateDeliveryAmountCalcResult();
            
            l_ifoEstimateDeliveryAmountCalcResult.setEstimateDeliveryAmount(0);
            l_ifoEstimateDeliveryAmountCalcResult.setCommissionCourse("12");
            l_ifoEstimateDeliveryAmountCalcResult.setCommission(23);
            l_ifoEstimateDeliveryAmountCalcResult.setCommissionTax(36);
            l_ifoEstimateDeliveryAmountCalcResult.setCalcUnitPrice(56);
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                    "webbroker3.ifo.WEB3OptionOrderManagerImpl", "calcEstimateDeliveryAmount",
                    new Class[]
                    {WEB3GentradeCommission.class,
                     double.class,
                     SubAccount.class,
                     WEB3IfoTradedProductImpl.class,
                     double.class,
                     SideEnum.class,
                     boolean.class,
                     boolean.class},
                     l_ifoEstimateDeliveryAmountCalcResult);
            
            ProcessingResult processingResult = ProcessingResult.newSuccessResultInstance();
            NewOrderValidationResult l_result = new NewOrderValidationResult(processingResult);
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                    "webbroker3.ifo.WEB3OptionOrderManagerImpl",
                    "validateOpenContractOrder", new Class[]
                    { WEB3GentradeSubAccount.class, WEB3IfoOpenContractOrderSpec.class },
                    l_result);
            
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                    "com.fitechlabs.xtrade.plugin.security.oplogin.impl.OpLoginSecurityServiceImpl", 
                    "getSessionProperty",
                    new Class[] {String.class},
                    "");
 
            l_impl.validateOrder(l_request);
            log.exiting(TEST_END + STR_METHOD_NAME);
            fail();
        }
        catch (WEB3BaseException l_ex)
        {
            log.error(l_ex.getMessage(), l_ex);
            log.exiting(TEST_END + STR_METHOD_NAME);
            assertEquals(WEB3ErrorCatalog.BUSINESS_ERROR_02287, l_ex.getErrorInfo());
        }
        catch(Exception l_ex)
        {
            log.error(l_ex.getMessage(), l_ex);
            log.exiting(TEST_END + STR_METHOD_NAME);
            fail();
        }
    }
    /**
     * BUSINESS_ERROR_02816
     *
     * validate夕場まで注文訂正可能(String, 先物OP予約注文単位Impl)
     */
    public void testValidateOrder_C0005()
    {
        final String STR_METHOD_NAME = "testValidateOrder_C0005()";
        log.entering(STR_METHOD_NAME);
        MOCK_MANAGER.setIsMockUsed(true);
        
        WEB3ToSuccOptionChangeOpenContractServiceImpl l_impl = new WEB3ToSuccOptionChangeOpenContractServiceImpl();
        WEB3SuccOptionsOpenChangeConfirmRequest l_request = new WEB3SuccOptionsOpenChangeConfirmRequest();
        l_request.orderPriceDiv = "0";
        l_request.limitPrice = null;
        l_request.execCondType = "1";
        l_request.expirationDateType = WEB3OrderExpirationDateTypeDef.EVENING_SESSION_ORDER;
        l_request.expirationDate = null;
        l_request.orderCondType = "0";
        l_request.stopOrderCondPrice = null;
        l_request.stopOrderCondOperator = null;
        l_request.wlimitOrderCondPrice = null;
        l_request.wlimitOrderCondOperator = null;
        l_request.wLimitOrderPriceDiv = null;
        l_request.wLimitPrice = null;
        l_request.wlimitExecCondType = null;
        l_request.wlimitEnableStatusDiv = null;
        //super.validate()
        l_request.id = "0001";
        l_request.opOrderQuantity = "1000";
        //連続注文単価調整値情報==nullの場合
        l_request.priceAdjustmentValueInfo = null;
        try
        {
            TestDBUtility.deleteAll(IfoOrderUnitParams.TYPE);
            TestDBUtility.deleteAll(SubAccountParams.TYPE);
            TestDBUtility.deleteAll(ProductParams.TYPE);
            TestDBUtility.deleteAll(IfoProductParams.TYPE);
            TestDBUtility.deleteAll(TraderParams.TYPE);
            TestDBUtility.deleteAll(SubAccountParams.TYPE);
            TestDBUtility.deleteAll(MainAccountParams.TYPE);
            TestDBUtility.deleteAll(RsvIfoOrderUnitParams.TYPE);
            TestDBUtility.deleteAll(BranchParams.TYPE);
            TestDBUtility.deleteAll(IfoProductParams.TYPE);
            TestDBUtility.deleteAll(TradedProductParams.TYPE);
            TestDBUtility.deleteAll(IfoTradedProductParams.TYPE);
            TestDBUtility.deleteAll(MarketParams.TYPE);
            TestDBUtility.deleteAll(IfoOrderUnitParams.TYPE);
            
            TestDBUtility.deleteAll(TradingTimeParams.TYPE);
            TestDBUtility.deleteAll(InstitutionParams.TYPE);
            
            SubAccountParams l_subAccountParams = TestDBUtility.getSubAccountRow();
            l_subAccountParams.setAccountId(333812512246L);
            l_subAccountParams.setSubAccountType(SubAccountTypeEnum.EQUITY_SUB_ACCOUNT);
            TestDBUtility.insertWithDel(l_subAccountParams);
            MainAccountParams l_mainAccountParams = TestDBUtility.getMainAccountRow();
            l_mainAccountParams.setIfoAccOpenDivTokyo("1");
            l_mainAccountParams.setQuotoType("1");
            TestDBUtility.insertWithDel(l_mainAccountParams);
            
            InstitutionParams l_institutionParams = TestDBUtility.getInstitutionRow();
            l_institutionParams.setInstitutionId(33);
            TestDBUtility.insertWithDel(l_institutionParams);
            
            TestDBUtility.deleteAll(RsvIfoOrderUnitRow.TYPE);
            RsvIfoOrderUnitParams l_rsvIfoOrderUnitParams = TestDBUtility.getRsvIfoOrderUnitRow();
            l_rsvIfoOrderUnitParams.setFutureOptionDiv("2");
            l_rsvIfoOrderUnitParams.setProductId(3304148080000L);
            l_rsvIfoOrderUnitParams.setOrderId(0001L);
            l_rsvIfoOrderUnitParams.setMarketId(231);
            l_rsvIfoOrderUnitParams.setReserveOrderTradingType("16");
            TestDBUtility.insertWithDel(l_rsvIfoOrderUnitParams);
            
            WEB3GentradeTradingTimeManagementForMock.mockGetOrderBizDate(WEB3DateUtility.getDate("20080520", "yyyyMMdd"));
            WEB3GentradeTradingTimeManagementForMock.mockGetBizDateType(new Timestamp(WEB3DateUtility.getDate("20080520", "yyyyMMdd").getTime()), "1");
            
//            TestDBUtility.deleteAll(TradingTimeRow.TYPE);
//            TradingTimeParams l_tradingTimeParams = TestDBUtility.getTradingTimeRow();
//            l_tradingTimeParams.setInstitutionCode("0D");
//            l_tradingTimeParams.setBranchCode("123");
//            l_tradingTimeParams.setMarketCode("N1");
//            l_tradingTimeParams.setTradingTimeType("01");
//            l_tradingTimeParams.setProductCode("0");
//            l_tradingTimeParams.setBizDateType("1");
//            TestDBUtility.insertWithDel(l_tradingTimeParams);
            
            TestDBUtility.deleteAll(BranchRow.TYPE);
            BranchParams l_branchParams = TestDBUtility.getBranchRow();
            l_branchParams.setBranchId(33381L);
            l_branchParams.setCloseWorngOption(0);
            TestDBUtility.insertWithDel(l_branchParams);
            
            TestDBUtility.deleteAll(IfoOrderUnitParams.TYPE);
            IfoOrderUnitParams l_ifoOrderUnitParams = TestDBUtility.getIfoOrderUnitRow();
            l_ifoOrderUnitParams.setOrderId(1001);
            l_ifoOrderUnitParams.setProductId(3304148080000L);
            l_ifoOrderUnitParams.setMarketId(231);
            TestDBUtility.insertWithDel(l_ifoOrderUnitParams);
            
            TestDBUtility.deleteAll(OrderexecutionEndRow.TYPE);
            OrderexecutionEndParams l_orderexecutionEndParams = TestDBUtility.getOrderexecutionEndRow();
            l_orderexecutionEndParams.setInstitutionCode("0D");
            l_orderexecutionEndParams.setProductType(ProductTypeEnum.IFO);
            l_orderexecutionEndParams.setFutureOptionDiv("2");
            l_orderexecutionEndParams.setOrderexecutionEndType("0");
            TestDBUtility.insertWithDel(l_orderexecutionEndParams);
            
            ProductParams l_productParams = TestDBUtility.getProductRow();
            l_productParams.setProductId(3304148080000L);
            l_productParams.setProductType(ProductTypeEnum.IFO);
            TestDBUtility.insertWithDel(l_productParams);
            
            IfoProductParams l_ifoProductParams = TestDBUtility.getIfoProductRow();
            l_ifoProductParams.setProductId(3304148080000L);
            l_ifoProductParams.setProductType(ProductTypeEnum.IFO);
            l_ifoProductParams.setDerivativeType(IfoDerivativeTypeEnum.PUT_OPTIONS);
            l_ifoProductParams.setStrikePrice(223);
            TestDBUtility.insertWithDel(l_ifoProductParams);
            
            MarketParams l_marketParams = TestDBUtility.getMarketRow();
            l_marketParams.setMarketId(231);
            l_marketParams.setMarketCode("1");
            TestDBUtility.insertWithDel(l_marketParams);
            
            TradedProductParams l_tradedProductParams = TestDBUtility.getTradedProductRow();
            l_tradedProductParams.setTradedProductId(1006160060005L);
            l_tradedProductParams.setProductId(3304148080000L);
            l_tradedProductParams.setMarketId(231L);
            l_tradedProductParams.setProductType(ProductTypeEnum.IFO);
            TestDBUtility.insertWithDel(l_tradedProductParams);
            
            IfoTradedProductParams l_ifoTradedProductParams = TestDBUtility.getIfoTradedProductRow();
            l_ifoTradedProductParams.setTradedProductId(1006160060005L);
            l_ifoTradedProductParams.setMarketId(231L);
            l_ifoTradedProductParams.setProductId(3304148080000L);
            l_ifoTradedProductParams.setValidForBizDate(null);
            TestDBUtility.insertWithDel(l_ifoTradedProductParams);
            
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                    "com.fitechlabs.xtrade.plugin.security.oplogin.impl.OpLoginSecurityServiceImpl", "getAccountId",
                    new Class[] {}, new Long("333812512246"));
            
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                    "com.fitechlabs.xtrade.plugin.security.oplogin.impl.OpLoginSecurityServiceImpl", "getLoginInfo",
                    new Class[] {},
                    new LoginInfoImplForMock());
            
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                    "com.fitechlabs.xtrade.plugin.security.oplogin.impl.LoginInfoImpl",
                    "getLoginId",
                    new Class[] {},
                    new Long(256));
            
            WEB3IfoProductImpl l_ifoProductImpl = new WEB3IfoProductImpl(3304148080000L);
            
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue("webbroker3.ifo.WEB3OptionOrderManagerImpl",
                    "notifyRLS", new Class[]
                    { IfoOrderUnit.class, OrderManagerPersistenceContext.class },
                    null);
            
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue("webbroker3.triggerorder.WEB3ToSuccOrderManagerImpl",
                    "submitIfoOpenContractNewOrder", new Class[]
                    {SubAccount.class, WEB3IfoOpenContractOrderSpec.class, long.class, String.class, String.class, Double.class, IfoOrderUnit.class, WEB3IfoEstimateDeliveryAmountCalcResult.class},
                    null);
            
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                    "webbroker3.triggerorder.WEB3ToSuccOrderManagerImpl",
                    "validateSuccOrderMaxQuantity", new Class[]
                    {OrderUnit.class},
                    null);
            
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                    "webbroker3.triggerorder.WEB3ToSuccOrderManagerImpl", "isReversingTrade", new Class[]
                    {String.class, OrderUnit.class},
                    Boolean.FALSE);
            
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue("webbroker3.triggerorder.WEB3ToSuccOrderManagerImpl",
                    "validateSuccOrder", new Class[]
                    {WEB3GentradeSubAccount.class, ProductTypeEnum.class, String.class, String.class, OrderUnit.class},
                    null);
                    
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                    "webbroker3.ifo.WEB3IfoProductManagerImpl",
                    "getIfoProduct",
                    new Class[] 
                    {Institution.class, String.class, String.class, IfoDerivativeTypeEnum.class,
                     double.class, String.class, String.class},
                     l_ifoProductImpl);
            
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                    "webbroker3.ifo.WEB3IfoProductManagerImpl",
                    "getIfoProduct",
                    new Class[] { Institution.class, String.class },
                    l_ifoProductImpl);
            
            WEB3IfoTradedProductImpl l_ifoTradedProductImpl = new WEB3IfoTradedProductImpl(1006160060005L);
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                    "webbroker3.ifo.WEB3IfoProductManagerImpl",
                    "getIfoTradedProduct",
                    new Class[] { Institution.class, String.class, String.class },
                    l_ifoTradedProductImpl);
            
            WEB3IfoEstimateDeliveryAmountCalcResult l_ifoEstimateDeliveryAmountCalcResult =
                new WEB3IfoEstimateDeliveryAmountCalcResult();
            
            l_ifoEstimateDeliveryAmountCalcResult.setEstimateDeliveryAmount(0);
            l_ifoEstimateDeliveryAmountCalcResult.setCommissionCourse("12");
            l_ifoEstimateDeliveryAmountCalcResult.setCommission(23);
            l_ifoEstimateDeliveryAmountCalcResult.setCommissionTax(36);
            l_ifoEstimateDeliveryAmountCalcResult.setCalcUnitPrice(56);
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                    "webbroker3.ifo.WEB3FuturesOrderManagerImpl", "calcEstimateDeliveryAmount",
                    new Class[]
                    {WEB3GentradeCommission.class,
                     double.class,
                     SubAccount.class,
                     WEB3IfoTradedProductImpl.class,
                     double.class,
                     SideEnum.class,
                     boolean.class,
                     boolean.class},
                     l_ifoEstimateDeliveryAmountCalcResult);
            
            ProcessingResult processingResult = ProcessingResult.newSuccessResultInstance();
            NewOrderValidationResult l_result = new NewOrderValidationResult(processingResult);
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                    "webbroker3.ifo.WEB3FuturesOrderManagerImpl",
                    "validateOpenContractOrder", new Class[]
                    { WEB3GentradeSubAccount.class, WEB3IfoOpenContractOrderSpec.class },
                    l_result);
            
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                    "com.fitechlabs.xtrade.plugin.security.oplogin.impl.OpLoginSecurityServiceImpl", 
                    "getSessionProperty",
                    new Class[] {String.class},
                    "");
 
            l_impl.validateOrder(l_request);
            
            log.exiting(TEST_END + STR_METHOD_NAME);
            fail();
        }
        catch (WEB3BaseException l_ex)
        {
            log.error(l_ex.getMessage(), l_ex);
            log.exiting(TEST_END + STR_METHOD_NAME);
            assertEquals(WEB3ErrorCatalog.BUSINESS_ERROR_02816, l_ex.getErrorInfo());
        }
        catch(Exception l_ex)
        {
            log.error(l_ex.getMessage(), l_ex);
            log.exiting(TEST_END + STR_METHOD_NAME);
            fail();
        }
    }
    
    /**
     * l_request.checkPrice == null
     *
     * l_request.expirationDateType != WEB3OrderExpirationDateTypeDef.CARRIED_ORDER
     */
    public void testSubmitOrder_C0001()
    {
        final String STR_METHOD_NAME = "testSubmitOrder_C0001()";
        log.entering(STR_METHOD_NAME);
 
        WEB3ToSuccOptionChangeOpenContractServiceImpl l_impl = new WEB3ToSuccOptionChangeOpenContractServiceImpl();
        MOCK_MANAGER.setIsMockUsed(true);
        try
        {
            WEB3SuccOptionsOpenChangeCompleteRequest l_request = new WEB3SuccOptionsOpenChangeCompleteRequest();
            l_request.orderPriceDiv = "1";
            l_request.limitPrice = "1";
            l_request.execCondType = "1";
            l_request.expirationDateType = "1";
            l_request.expirationDate = null;
            l_request.orderCondType = "0";
            l_request.stopOrderCondPrice = null;
            l_request.stopOrderCondOperator = null;
            l_request.wlimitOrderCondPrice = null;
            l_request.wlimitOrderCondOperator = null;
            l_request.wLimitOrderPriceDiv = null;
            l_request.wLimitPrice = null;
            l_request.wlimitExecCondType = null;
            l_request.wlimitEnableStatusDiv = null;
            //super.validate()
            l_request.id = "21";
            l_request.opOrderQuantity = "1000";
            //２）　@確認時概算建代金チェック
            l_request.estimatedPrice = "1000";
            //３） 連続注文単価調整値情報チェック
            l_request.priceAdjustmentValueInfo = null;
            l_request.checkDate = WEB3DateUtility.getDate("20080520", "yyyyMMdd");
    
            TestDBUtility.deleteAll(IfoOrderUnitParams.TYPE);
            TestDBUtility.deleteAll(SubAccountParams.TYPE);
            TestDBUtility.deleteAll(ProductParams.TYPE);
            TestDBUtility.deleteAll(IfoProductParams.TYPE);
            TestDBUtility.deleteAll(TraderParams.TYPE);
            TestDBUtility.deleteAll(SubAccountParams.TYPE);
            TestDBUtility.deleteAll(MainAccountParams.TYPE);
            TestDBUtility.deleteAll(RsvIfoOrderUnitParams.TYPE);
            TestDBUtility.deleteAll(BranchParams.TYPE);
            TestDBUtility.deleteAll(IfoProductParams.TYPE);
            TestDBUtility.deleteAll(TradedProductParams.TYPE);
            TestDBUtility.deleteAll(IfoTradedProductParams.TYPE);
            TestDBUtility.deleteAll(MarketParams.TYPE);
            TestDBUtility.deleteAll(IfoOrderUnitParams.TYPE);
            
            TestDBUtility.deleteAll(TradingTimeParams.TYPE);
            TestDBUtility.deleteAll(InstitutionParams.TYPE);
       
            ThreadLocalSystemAttributesRegistry.setAttribute(
                "xblocks.gtl.attributes.systemtimestamp",
                new Timestamp(WEB3DateUtility.getDate("20080520", "yyyyMMdd").getTime()));
            WEB3GentradeTradingTimeManagementForMock.mockGetOrderBizDate(WEB3DateUtility.getDate("20080520", "yyyyMMdd"));
            WEB3GentradeTradingTimeManagementForMock.mockGetBizDateType(new Timestamp(WEB3DateUtility.getDate("20080520", "yyyyMMdd").getTime()), "1");
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                    "com.fitechlabs.xtrade.plugin.security.oplogin.impl.OpLoginSecurityServiceImpl", "getAccountId",
                    new Class[] {}, new Long("333812512246"));
            
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                    "com.fitechlabs.xtrade.plugin.security.oplogin.impl.OpLoginSecurityServiceImpl", "getLoginInfo",
                    new Class[] {},
                    new LoginInfoImplForMock());
            
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                    "com.fitechlabs.xtrade.plugin.security.oplogin.impl.LoginInfoImpl",
                    "getLoginId",
                    new Class[] {},
                    new Long(256));
            
            TestDBUtility.deleteAll(OrderexecutionEndRow.TYPE);
            OrderexecutionEndParams l_orderexecutionEndParams = TestDBUtility.getOrderexecutionEndRow();
            l_orderexecutionEndParams.setInstitutionCode("0D");
            l_orderexecutionEndParams.setProductType(ProductTypeEnum.IFO);
            l_orderexecutionEndParams.setFutureOptionDiv("2");
            l_orderexecutionEndParams.setOrderexecutionEndType("0");
            TestDBUtility.insertWithDel(l_orderexecutionEndParams);
            
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
            l_rsvIfoOrderUnitParams.setOrderOpenStatus(OrderOpenStatusEnum.OPEN);
            l_rsvIfoOrderUnitParams.setCreatedTimestamp(Calendar.getInstance().getTime());
            l_rsvIfoOrderUnitParams.setFutureOptionDiv("2");
            l_rsvIfoOrderUnitParams.setReserveOrderTradingType("15");
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
            
            MarketParams l_marketParams = TestDBUtility.getMarketRow();
            l_marketParams.setMarketId(3303L);
            l_marketParams.setMarketCode("1");
            TestDBUtility.insertWithDel(l_marketParams);
            
            SubAccountParams l_subAccountParams = TestDBUtility.getSubAccountRow();
            l_subAccountParams.setAccountId(333812512246L);
            l_subAccountParams.setSubAccountType(SubAccountTypeEnum.EQUITY_SUB_ACCOUNT);
            TestDBUtility.insertWithDel(l_subAccountParams);
            MainAccountParams l_mainAccountParams = TestDBUtility.getMainAccountRow();
            l_mainAccountParams.setIfoAccOpenDivTokyo("1");
            l_mainAccountParams.setQuotoType("1");
            l_mainAccountParams.setBranchId(1234);
            TestDBUtility.insertWithDel(l_mainAccountParams);
            BranchParams l_branchParams = TestDBUtility.getBranchRow();
            l_branchParams.setBranchId(1234);
            l_branchParams.setCloseWorngSysFuture(1);
            TestDBUtility.insertWithDel(l_branchParams);
            InstitutionParams l_institutionParams = TestDBUtility.getInstitutionRow();
            l_institutionParams.setInstitutionId(33);
            TestDBUtility.insertWithDel(l_institutionParams);
            
            IfoOrderUnitParams l_ifoOrderUnitParams = TestDBUtility.getIfoOrderUnitRow();
            l_ifoOrderUnitParams.setProductId(3304148080000L);
            l_ifoOrderUnitParams.setOrderId(56);
            l_ifoOrderUnitParams.setOrderUnitId(989);
            l_ifoOrderUnitParams.setMarketId(3303L);
            TestDBUtility.insertWithDel(l_ifoOrderUnitParams);
            TraderParams l_traderParam = TestDBUtility.getTraderRow();
            l_traderParam.setTraderId(3338111123L);
            l_traderParam.setLoginId(256L);
            TestDBUtility.insertWithDel(l_traderParam);
            
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
            l_ifoProductParams.setDerivativeType(IfoDerivativeTypeEnum.PUT_OPTIONS);
            TestDBUtility.insertWithDel(l_ifoProductParams);
 
            TradedProductParams l_tradedProductParams = TestDBUtility.getTradedProductRow();
            l_tradedProductParams.setTradedProductId(1006160060005L);
            l_tradedProductParams.setProductId(3304148080000L);
            l_tradedProductParams.setMarketId(3303L);
            l_tradedProductParams.setProductType(ProductTypeEnum.IFO);
            TestDBUtility.insertWithDel(l_tradedProductParams);
            
            IfoTradedProductParams l_ifoTradedProductParams = TestDBUtility.getIfoTradedProductRow();
            l_ifoTradedProductParams.setTradedProductId(1006160060005L);
            l_ifoTradedProductParams.setMarketId(3303L);
            l_ifoTradedProductParams.setProductId(3304148080000L);
            l_ifoTradedProductParams.setValidForBizDate(null);
            TestDBUtility.insertWithDel(l_ifoTradedProductParams);

            WEB3IfoProductImpl l_ifoProductImpl = new WEB3IfoProductImpl(3304148080000L);
            
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue("webbroker3.ifo.WEB3OptionOrderManagerImpl",
                    "notifyRLS", new Class[]
                    { IfoOrderUnit.class, OrderManagerPersistenceContext.class },
                    null);
            
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue("webbroker3.triggerorder.WEB3ToSuccOrderManagerImpl",
                    "submitIfoOpenContractNewOrder", new Class[]
                    {SubAccount.class, WEB3IfoOpenContractOrderSpec.class, long.class, String.class, String.class, Double.class, IfoOrderUnit.class, WEB3IfoEstimateDeliveryAmountCalcResult.class},
                    null);
            
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                    "webbroker3.triggerorder.WEB3ToSuccOrderManagerImpl",
                    "validateSuccOrderMaxQuantity", new Class[]
                    {OrderUnit.class},
                    null);
            
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                    "webbroker3.triggerorder.WEB3ToSuccOrderManagerImpl", "isReversingTrade", new Class[]
                    {String.class, OrderUnit.class},
                    Boolean.FALSE);
            
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue("webbroker3.triggerorder.WEB3ToSuccOrderManagerImpl",
                    "validateSuccOrder", new Class[]
                    {WEB3GentradeSubAccount.class, ProductTypeEnum.class, String.class, String.class, OrderUnit.class},
                    null);
                    
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                    "webbroker3.ifo.WEB3IfoProductManagerImpl",
                    "getIfoProduct",
                    new Class[] 
                    {Institution.class, String.class, String.class, IfoDerivativeTypeEnum.class,
                     double.class, String.class, String.class},
                     l_ifoProductImpl);
            
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                    "webbroker3.ifo.WEB3IfoProductManagerImpl",
                    "getIfoProduct",
                    new Class[] { Institution.class, String.class },
                    l_ifoProductImpl);
            
            WEB3IfoTradedProductImpl l_ifoTradedProductImpl = new WEB3IfoTradedProductImpl(1006160060005L);
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                    "webbroker3.ifo.WEB3IfoProductManagerImpl",
                    "getIfoTradedProduct",
                    new Class[] { Institution.class, String.class, String.class },
                    l_ifoTradedProductImpl);
            
            ProcessingResult processingResult = ProcessingResult.newSuccessResultInstance();
            NewOrderValidationResult l_result = new NewOrderValidationResult(processingResult);
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                    "webbroker3.ifo.WEB3FuturesOrderManagerImpl",
                    "validateOpenContractOrder", new Class[]
                    { WEB3GentradeSubAccount.class, WEB3IfoOpenContractOrderSpec.class },
                    l_result);
            
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                    "com.fitechlabs.xtrade.plugin.security.oplogin.impl.OpLoginSecurityServiceImpl", 
                    "getSessionProperty",
                    new Class[] {String.class},
                    "");
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                    "webbroker3.ifo.WEB3FuturesOrderManagerImpl", "calcEstimateDeliveryAmount",
                    new Class[]
                    {WEB3GentradeCommission.class,
                     double.class,
                     SubAccount.class,
                     WEB3IfoTradedProductImpl.class,
                     double.class,
                     SideEnum.class,
                     boolean.class,
                     boolean.class},
                     null);

            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                    "webbroker3.triggerorder.WEB3ToSuccOrderManagerImpl",
                    "submitIfoChangeOpenContractOrder",
                    new Class[]
                    {SubAccount.class, WEB3ToSuccIfoChangeOpenContractOrderSpec.class, String.class,
                            WEB3ToSuccIfoOrderUnitImpl.class},
                            null);
            
            WEB3SuccOptionsOpenChangeCompleteResponse l_response = l_impl.submitOrder(l_request);
            
            assertEquals("20080520", WEB3DateUtility.formatDate(l_response.lastUpdatedTimestamp, "yyyyMMdd"));
            assertEquals("21", l_response.orderActionId);
            assertFalse(l_response.succSettingFlag);
            
        }
        catch (WEB3BaseException l_ex)
        {
            log.error(l_ex.getMessage(), l_ex);
            log.exiting(TEST_END + STR_METHOD_NAME);
            fail();
        }
        catch(Exception l_ex)
        {
            log.error(l_ex.getMessage(), l_ex);
            log.exiting(TEST_END + STR_METHOD_NAME);
            fail();
        }
        
        log.exiting(TEST_END + STR_METHOD_NAME);
    }
    
    /**
     * l_request.checkPrice != null
     *
     * l_request.expirationDateType == WEB3OrderExpirationDateTypeDef.CARRIED_ORDER
     */
    public void testSubmitOrder_C0002()
    {
        final String STR_METHOD_NAME = "testSubmitOrder_C0001()";
        log.entering(STR_METHOD_NAME);
 
        WEB3ToSuccOptionChangeOpenContractServiceImpl l_impl = new WEB3ToSuccOptionChangeOpenContractServiceImpl();
        MOCK_MANAGER.setIsMockUsed(true);
        try
        {
            WEB3SuccOptionsOpenChangeCompleteRequest l_request = new WEB3SuccOptionsOpenChangeCompleteRequest();
            l_request.orderPriceDiv = "1";
            l_request.limitPrice = "1";
            l_request.checkPrice = "1";
            l_request.execCondType = "1";
            l_request.expirationDateType = "2";
            l_request.expirationDate = WEB3DateUtility.getDate("20080808", "yyyyMMdd");
            l_request.orderCondType = "0";
            l_request.stopOrderCondPrice = null;
            l_request.stopOrderCondOperator = null;
            l_request.wlimitOrderCondPrice = null;
            l_request.wlimitOrderCondOperator = null;
            l_request.wLimitOrderPriceDiv = null;
            l_request.wLimitPrice = null;
            l_request.wlimitExecCondType = null;
            l_request.wlimitEnableStatusDiv = null;
            //super.validate()
            l_request.id = "21";
            l_request.opOrderQuantity = "1000";
            //２）　@確認時概算建代金チェック
            l_request.estimatedPrice = "1000";
            //３） 連続注文単価調整値情報チェック
            l_request.priceAdjustmentValueInfo = null;
            l_request.checkDate = WEB3DateUtility.getDate("20080520", "yyyyMMdd");
    
            TestDBUtility.deleteAll(IfoOrderUnitParams.TYPE);
            TestDBUtility.deleteAll(SubAccountParams.TYPE);
            TestDBUtility.deleteAll(ProductParams.TYPE);
            TestDBUtility.deleteAll(IfoProductParams.TYPE);
            TestDBUtility.deleteAll(TraderParams.TYPE);
            TestDBUtility.deleteAll(SubAccountParams.TYPE);
            TestDBUtility.deleteAll(MainAccountParams.TYPE);
            TestDBUtility.deleteAll(RsvIfoOrderUnitParams.TYPE);
            TestDBUtility.deleteAll(BranchParams.TYPE);
            TestDBUtility.deleteAll(IfoProductParams.TYPE);
            TestDBUtility.deleteAll(TradedProductParams.TYPE);
            TestDBUtility.deleteAll(IfoTradedProductParams.TYPE);
            TestDBUtility.deleteAll(MarketParams.TYPE);
            TestDBUtility.deleteAll(IfoOrderUnitParams.TYPE);
            
            TestDBUtility.deleteAll(TradingTimeParams.TYPE);
            TestDBUtility.deleteAll(InstitutionParams.TYPE);
            
            ThreadLocalSystemAttributesRegistry.setAttribute(
                "xblocks.gtl.attributes.systemtimestamp",
                new Timestamp(WEB3DateUtility.getDate("20080520", "yyyyMMdd").getTime()));
            WEB3GentradeTradingTimeManagementForMock.mockGetOrderBizDate(WEB3DateUtility.getDate("20080520", "yyyyMMdd"));
            WEB3GentradeTradingTimeManagementForMock.mockGetBizDateType(new Timestamp(WEB3DateUtility.getDate("20080520", "yyyyMMdd").getTime()), "1");
 
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                    "com.fitechlabs.xtrade.plugin.security.oplogin.impl.OpLoginSecurityServiceImpl", "getAccountId",
                    new Class[] {}, new Long("333812512246"));
            
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                    "com.fitechlabs.xtrade.plugin.security.oplogin.impl.OpLoginSecurityServiceImpl", "getLoginInfo",
                    new Class[] {},
                    new LoginInfoImplForMock());
            
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                    "com.fitechlabs.xtrade.plugin.security.oplogin.impl.LoginInfoImpl",
                    "getLoginId",
                    new Class[] {},
                    new Long(256));
            
            TestDBUtility.deleteAll(OrderexecutionEndRow.TYPE);
            OrderexecutionEndParams l_orderexecutionEndParams = TestDBUtility.getOrderexecutionEndRow();
            l_orderexecutionEndParams.setInstitutionCode("0D");
            l_orderexecutionEndParams.setProductType(ProductTypeEnum.IFO);
            l_orderexecutionEndParams.setFutureOptionDiv("2");
            l_orderexecutionEndParams.setOrderexecutionEndType("0");
            TestDBUtility.insertWithDel(l_orderexecutionEndParams);
            
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
            l_rsvIfoOrderUnitParams.setOrderOpenStatus(OrderOpenStatusEnum.OPEN);
            l_rsvIfoOrderUnitParams.setCreatedTimestamp(Calendar.getInstance().getTime());
            l_rsvIfoOrderUnitParams.setFutureOptionDiv("2");
            l_rsvIfoOrderUnitParams.setReserveOrderTradingType("15");
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
            
            MarketParams l_marketParams = TestDBUtility.getMarketRow();
            l_marketParams.setMarketId(3303L);
            l_marketParams.setMarketCode("1");
            TestDBUtility.insertWithDel(l_marketParams);
            
            SubAccountParams l_subAccountParams = TestDBUtility.getSubAccountRow();
            l_subAccountParams.setAccountId(333812512246L);
            l_subAccountParams.setSubAccountType(SubAccountTypeEnum.EQUITY_SUB_ACCOUNT);
            TestDBUtility.insertWithDel(l_subAccountParams);
            MainAccountParams l_mainAccountParams = TestDBUtility.getMainAccountRow();
            l_mainAccountParams.setIfoAccOpenDivTokyo("1");
            l_mainAccountParams.setQuotoType("1");
            l_mainAccountParams.setBranchId(1234);
            TestDBUtility.insertWithDel(l_mainAccountParams);
            BranchParams l_branchParams = TestDBUtility.getBranchRow();
            l_branchParams.setBranchId(1234);
            l_branchParams.setCloseWorngSysFuture(1);
            TestDBUtility.insertWithDel(l_branchParams);
            InstitutionParams l_institutionParams = TestDBUtility.getInstitutionRow();
            l_institutionParams.setInstitutionId(33);
            TestDBUtility.insertWithDel(l_institutionParams);
            
            IfoOrderUnitParams l_ifoOrderUnitParams = TestDBUtility.getIfoOrderUnitRow();
            l_ifoOrderUnitParams.setProductId(3304148080000L);
            l_ifoOrderUnitParams.setOrderId(56);
            l_ifoOrderUnitParams.setOrderUnitId(989);
            l_ifoOrderUnitParams.setMarketId(3303L);
            TestDBUtility.insertWithDel(l_ifoOrderUnitParams);
            TraderParams l_traderParam = TestDBUtility.getTraderRow();
            l_traderParam.setTraderId(3338111123L);
            l_traderParam.setLoginId(256L);
            TestDBUtility.insertWithDel(l_traderParam);
            
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
            l_ifoProductParams.setDerivativeType(IfoDerivativeTypeEnum.PUT_OPTIONS);
            TestDBUtility.insertWithDel(l_ifoProductParams);
 
            TradedProductParams l_tradedProductParams = TestDBUtility.getTradedProductRow();
            l_tradedProductParams.setTradedProductId(1006160060005L);
            l_tradedProductParams.setProductId(3304148080000L);
            l_tradedProductParams.setMarketId(3303L);
            l_tradedProductParams.setProductType(ProductTypeEnum.IFO);
            TestDBUtility.insertWithDel(l_tradedProductParams);
            
            IfoTradedProductParams l_ifoTradedProductParams = TestDBUtility.getIfoTradedProductRow();
            l_ifoTradedProductParams.setTradedProductId(1006160060005L);
            l_ifoTradedProductParams.setMarketId(3303L);
            l_ifoTradedProductParams.setProductId(3304148080000L);
            l_ifoTradedProductParams.setValidForBizDate(null);
            TestDBUtility.insertWithDel(l_ifoTradedProductParams);
            
            WEB3IfoProductImpl l_ifoProductImpl = new WEB3IfoProductImpl(3304148080000L);
            
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue("webbroker3.ifo.WEB3OptionOrderManagerImpl",
                    "notifyRLS", new Class[]
                    { IfoOrderUnit.class, OrderManagerPersistenceContext.class },
                    null);
            
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue("webbroker3.triggerorder.WEB3ToSuccOrderManagerImpl",
                    "submitIfoOpenContractNewOrder", new Class[]
                    {SubAccount.class, WEB3IfoOpenContractOrderSpec.class, long.class, String.class, String.class, Double.class, IfoOrderUnit.class, WEB3IfoEstimateDeliveryAmountCalcResult.class},
                    null);
            
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                    "webbroker3.triggerorder.WEB3ToSuccOrderManagerImpl",
                    "validateSuccOrderMaxQuantity", new Class[]
                    {OrderUnit.class},
                    null);
            
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                    "webbroker3.triggerorder.WEB3ToSuccOrderManagerImpl", "isReversingTrade", new Class[]
                    {String.class, OrderUnit.class},
                    Boolean.FALSE);
            
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue("webbroker3.triggerorder.WEB3ToSuccOrderManagerImpl",
                    "validateSuccOrder", new Class[]
                    {WEB3GentradeSubAccount.class, ProductTypeEnum.class, String.class, String.class, OrderUnit.class},
                    null);
                    
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                    "webbroker3.ifo.WEB3IfoProductManagerImpl",
                    "getIfoProduct",
                    new Class[] 
                    {Institution.class, String.class, String.class, IfoDerivativeTypeEnum.class,
                     double.class, String.class, String.class},
                     l_ifoProductImpl);
            
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                    "webbroker3.ifo.WEB3IfoProductManagerImpl",
                    "getIfoProduct",
                    new Class[] { Institution.class, String.class },
                    l_ifoProductImpl);
            
            WEB3IfoTradedProductImpl l_ifoTradedProductImpl = new WEB3IfoTradedProductImpl(1006160060005L);
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                    "webbroker3.ifo.WEB3IfoProductManagerImpl",
                    "getIfoTradedProduct",
                    new Class[] { Institution.class, String.class, String.class },
                    l_ifoTradedProductImpl);
            
            ProcessingResult processingResult = ProcessingResult.newSuccessResultInstance();
            NewOrderValidationResult l_result = new NewOrderValidationResult(processingResult);
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                    "webbroker3.ifo.WEB3FuturesOrderManagerImpl",
                    "validateOpenContractOrder", new Class[]
                    { WEB3GentradeSubAccount.class, WEB3IfoOpenContractOrderSpec.class },
                    l_result);
            
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                    "com.fitechlabs.xtrade.plugin.security.oplogin.impl.OpLoginSecurityServiceImpl", 
                    "getSessionProperty",
                    new Class[] {String.class},
                    "");
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                    "webbroker3.ifo.WEB3FuturesOrderManagerImpl", "calcEstimateDeliveryAmount",
                    new Class[]
                    {WEB3GentradeCommission.class,
                     double.class,
                     SubAccount.class,
                     WEB3IfoTradedProductImpl.class,
                     double.class,
                     SideEnum.class,
                     boolean.class,
                     boolean.class},
                     null);

            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                    "webbroker3.triggerorder.WEB3ToSuccOrderManagerImpl",
                    "submitIfoChangeOpenContractOrder",
                    new Class[]
                    {SubAccount.class, WEB3ToSuccIfoChangeOpenContractOrderSpec.class, String.class,
                            WEB3ToSuccIfoOrderUnitImpl.class},
                            null);
            
            WEB3SuccOptionsOpenChangeCompleteResponse l_response = l_impl.submitOrder(l_request);
            
            assertEquals("20080520", WEB3DateUtility.formatDate(l_response.lastUpdatedTimestamp, "yyyyMMdd"));
            assertEquals("21", l_response.orderActionId);
            assertFalse(l_response.succSettingFlag);
            
        }
        catch (WEB3BaseException l_ex)
        {
            log.error(l_ex.getMessage(), l_ex);
            log.exiting(TEST_END + STR_METHOD_NAME);
            fail();
        }
        catch(Exception l_ex)
        {
            log.error(l_ex.getMessage(), l_ex);
            log.exiting(TEST_END + STR_METHOD_NAME);
            fail();
        }
        
        log.exiting(TEST_END + STR_METHOD_NAME);
    }

    /**
     * 正常終了
     * 
     * l_request.priceAdjustmentValueInfo != null
     */
    public void testSubmitOrder_C0003()
    {
        final String STR_METHOD_NAME = "testSubmitOrder_C0003()";
        log.entering(STR_METHOD_NAME);
 
        WEB3ToSuccOptionChangeOpenContractServiceImpl l_impl = new WEB3ToSuccOptionChangeOpenContractServiceImpl();
        MOCK_MANAGER.setIsMockUsed(true);
        try
        {
            WEB3SuccOptionsOpenChangeCompleteRequest l_request = new WEB3SuccOptionsOpenChangeCompleteRequest();
            l_request.orderPriceDiv = "0";
            l_request.execCondType = "1";
            l_request.expirationDateType = "1";
            l_request.expirationDate = null;
            l_request.orderCondType = "0";
            l_request.stopOrderCondPrice = null;
            l_request.stopOrderCondOperator = null;
            l_request.wlimitOrderCondPrice = null;
            l_request.wlimitOrderCondOperator = null;
            l_request.wLimitOrderPriceDiv = null;
            l_request.wLimitPrice = null;
            l_request.wlimitExecCondType = null;
            l_request.wlimitEnableStatusDiv = null;
            l_request.afterAdjustmentPrice = "2";
            //super.validate()
            l_request.id = "21";
            l_request.opOrderQuantity = "1000";
            //２）　@確認時概算建代金チェック
            l_request.estimatedPrice = "1000";
            //３） 連続注文単価調整値情報チェック
            WEB3SuccPriceAdjustmentValueInfo l_info = new WEB3SuccPriceAdjustmentValueInfo();
            l_info.sign = "+";
            l_info.priceAdjustmentValue = "123";
            l_request.priceAdjustmentValueInfo = l_info;
            
            l_request.checkDate = WEB3DateUtility.getDate("20080520", "yyyyMMdd");
    
            TestDBUtility.deleteAll(IfoOrderUnitParams.TYPE);
            TestDBUtility.deleteAll(SubAccountParams.TYPE);
            TestDBUtility.deleteAll(ProductParams.TYPE);
            TestDBUtility.deleteAll(IfoProductParams.TYPE);
            TestDBUtility.deleteAll(TraderParams.TYPE);
            TestDBUtility.deleteAll(SubAccountParams.TYPE);
            TestDBUtility.deleteAll(MainAccountParams.TYPE);
            TestDBUtility.deleteAll(RsvIfoOrderUnitParams.TYPE);
            TestDBUtility.deleteAll(BranchParams.TYPE);
            TestDBUtility.deleteAll(IfoProductParams.TYPE);
            TestDBUtility.deleteAll(TradedProductParams.TYPE);
            TestDBUtility.deleteAll(IfoTradedProductParams.TYPE);
            TestDBUtility.deleteAll(MarketParams.TYPE);
            TestDBUtility.deleteAll(IfoOrderUnitParams.TYPE);
            
            TestDBUtility.deleteAll(TradingTimeParams.TYPE);
            TestDBUtility.deleteAll(InstitutionParams.TYPE);
            
            ThreadLocalSystemAttributesRegistry.setAttribute(
                "xblocks.gtl.attributes.systemtimestamp",
                new Timestamp(WEB3DateUtility.getDate("20080520", "yyyyMMdd").getTime()));
            WEB3GentradeTradingTimeManagementForMock.mockGetOrderBizDate(WEB3DateUtility.getDate("20080520", "yyyyMMdd"));
            WEB3GentradeTradingTimeManagementForMock.mockGetBizDateType(new Timestamp(WEB3DateUtility.getDate("20080520", "yyyyMMdd").getTime()), "1");
            
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                    "com.fitechlabs.xtrade.plugin.security.oplogin.impl.OpLoginSecurityServiceImpl", "getAccountId",
                    new Class[] {}, new Long("333812512246"));
            
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                    "com.fitechlabs.xtrade.plugin.security.oplogin.impl.OpLoginSecurityServiceImpl", "getLoginInfo",
                    new Class[] {},
                    new LoginInfoImplForMock());
            
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                    "com.fitechlabs.xtrade.plugin.security.oplogin.impl.LoginInfoImpl",
                    "getLoginId",
                    new Class[] {},
                    new Long(256));
            
            TestDBUtility.deleteAll(OrderexecutionEndRow.TYPE);
            OrderexecutionEndParams l_orderexecutionEndParams = TestDBUtility.getOrderexecutionEndRow();
            l_orderexecutionEndParams.setInstitutionCode("0D");
            l_orderexecutionEndParams.setProductType(ProductTypeEnum.IFO);
            l_orderexecutionEndParams.setFutureOptionDiv("2");
            l_orderexecutionEndParams.setOrderexecutionEndType("0");
            TestDBUtility.insertWithDel(l_orderexecutionEndParams);
            
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
            l_rsvIfoOrderUnitParams.setOrderOpenStatus(OrderOpenStatusEnum.OPEN);
            l_rsvIfoOrderUnitParams.setCreatedTimestamp(Calendar.getInstance().getTime());
            l_rsvIfoOrderUnitParams.setFutureOptionDiv("2");
            l_rsvIfoOrderUnitParams.setReserveOrderTradingType("15");
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
            
            MarketParams l_marketParams = TestDBUtility.getMarketRow();
            l_marketParams.setMarketId(3303L);
            l_marketParams.setMarketCode("1");
            TestDBUtility.insertWithDel(l_marketParams);
            
            SubAccountParams l_subAccountParams = TestDBUtility.getSubAccountRow();
            l_subAccountParams.setAccountId(333812512246L);
            l_subAccountParams.setSubAccountType(SubAccountTypeEnum.EQUITY_SUB_ACCOUNT);
            TestDBUtility.insertWithDel(l_subAccountParams);
            MainAccountParams l_mainAccountParams = TestDBUtility.getMainAccountRow();
            l_mainAccountParams.setIfoAccOpenDivTokyo("1");
            l_mainAccountParams.setQuotoType("1");
            l_mainAccountParams.setBranchId(1234);
            TestDBUtility.insertWithDel(l_mainAccountParams);
            BranchParams l_branchParams = TestDBUtility.getBranchRow();
            l_branchParams.setBranchId(1234);
            l_branchParams.setCloseWorngSysFuture(1);
            TestDBUtility.insertWithDel(l_branchParams);
            InstitutionParams l_institutionParams = TestDBUtility.getInstitutionRow();
            l_institutionParams.setInstitutionId(33);
            TestDBUtility.insertWithDel(l_institutionParams);
            
            IfoOrderUnitParams l_ifoOrderUnitParams = TestDBUtility.getIfoOrderUnitRow();
            l_ifoOrderUnitParams.setProductId(3304148080000L);
            l_ifoOrderUnitParams.setOrderId(56);
            l_ifoOrderUnitParams.setOrderUnitId(989);
            l_ifoOrderUnitParams.setMarketId(3303L);
            TestDBUtility.insertWithDel(l_ifoOrderUnitParams);
            TraderParams l_traderParam = TestDBUtility.getTraderRow();
            l_traderParam.setTraderId(3338111123L);
            l_traderParam.setLoginId(256L);
            TestDBUtility.insertWithDel(l_traderParam);
            
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
            l_ifoProductParams.setDerivativeType(IfoDerivativeTypeEnum.PUT_OPTIONS);
            TestDBUtility.insertWithDel(l_ifoProductParams);
 
            TradedProductParams l_tradedProductParams = TestDBUtility.getTradedProductRow();
            l_tradedProductParams.setTradedProductId(1006160060005L);
            l_tradedProductParams.setProductId(3304148080000L);
            l_tradedProductParams.setMarketId(3303L);
            l_tradedProductParams.setProductType(ProductTypeEnum.IFO);
            TestDBUtility.insertWithDel(l_tradedProductParams);
            
            IfoTradedProductParams l_ifoTradedProductParams = TestDBUtility.getIfoTradedProductRow();
            l_ifoTradedProductParams.setTradedProductId(1006160060005L);
            l_ifoTradedProductParams.setMarketId(3303L);
            l_ifoTradedProductParams.setProductId(3304148080000L);
            l_ifoTradedProductParams.setValidForBizDate(null);
            TestDBUtility.insertWithDel(l_ifoTradedProductParams);
            
            WEB3IfoProductImpl l_ifoProductImpl = new WEB3IfoProductImpl(3304148080000L);
            
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue("webbroker3.ifo.WEB3OptionOrderManagerImpl",
                    "notifyRLS", new Class[]
                    { IfoOrderUnit.class, OrderManagerPersistenceContext.class },
                    null);
            
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue("webbroker3.triggerorder.WEB3ToSuccOrderManagerImpl",
                    "submitIfoOpenContractNewOrder", new Class[]
                    {SubAccount.class, WEB3IfoOpenContractOrderSpec.class, long.class, String.class, String.class, Double.class, IfoOrderUnit.class, WEB3IfoEstimateDeliveryAmountCalcResult.class},
                    null);
            
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                    "webbroker3.triggerorder.WEB3ToSuccOrderManagerImpl",
                    "validateSuccOrderMaxQuantity", new Class[]
                    {OrderUnit.class},
                    null);
            
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                    "webbroker3.triggerorder.WEB3ToSuccOrderManagerImpl", "isReversingTrade", new Class[]
                    {String.class, OrderUnit.class},
                    Boolean.FALSE);
            
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue("webbroker3.triggerorder.WEB3ToSuccOrderManagerImpl",
                    "validateSuccOrder", new Class[]
                    {WEB3GentradeSubAccount.class, ProductTypeEnum.class, String.class, String.class, OrderUnit.class},
                    null);
                    
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                    "webbroker3.ifo.WEB3IfoProductManagerImpl",
                    "getIfoProduct",
                    new Class[] 
                    {Institution.class, String.class, String.class, IfoDerivativeTypeEnum.class,
                     double.class, String.class, String.class},
                     l_ifoProductImpl);
            
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                    "webbroker3.ifo.WEB3IfoProductManagerImpl",
                    "getIfoProduct",
                    new Class[] { Institution.class, String.class },
                    l_ifoProductImpl);
            
            WEB3IfoTradedProductImpl l_ifoTradedProductImpl = new WEB3IfoTradedProductImpl(1006160060005L);
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                    "webbroker3.ifo.WEB3IfoProductManagerImpl",
                    "getIfoTradedProduct",
                    new Class[] { Institution.class, String.class, String.class },
                    l_ifoTradedProductImpl);
            
            ProcessingResult processingResult = ProcessingResult.newSuccessResultInstance();
            NewOrderValidationResult l_result = new NewOrderValidationResult(processingResult);
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                    "webbroker3.ifo.WEB3FuturesOrderManagerImpl",
                    "validateOpenContractOrder", new Class[]
                    { WEB3GentradeSubAccount.class, WEB3IfoOpenContractOrderSpec.class },
                    l_result);
            
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                    "com.fitechlabs.xtrade.plugin.security.oplogin.impl.OpLoginSecurityServiceImpl", 
                    "getSessionProperty",
                    new Class[] {String.class},
                    "");
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                    "webbroker3.ifo.WEB3FuturesOrderManagerImpl", "calcEstimateDeliveryAmount",
                    new Class[]
                    {WEB3GentradeCommission.class,
                     double.class,
                     SubAccount.class,
                     WEB3IfoTradedProductImpl.class,
                     double.class,
                     SideEnum.class,
                     boolean.class,
                     boolean.class},
                     null);

            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                    "webbroker3.triggerorder.WEB3ToSuccOrderManagerImpl",
                    "submitIfoChangeOpenContractOrder",
                    new Class[]
                    {SubAccount.class, WEB3ToSuccIfoChangeOpenContractOrderSpec.class, String.class,
                            WEB3ToSuccIfoOrderUnitImpl.class},
                            null);
            
            WEB3SuccOptionsOpenChangeCompleteResponse l_response = l_impl.submitOrder(l_request);
            
            assertEquals("20080520", WEB3DateUtility.formatDate(l_response.lastUpdatedTimestamp, "yyyyMMdd"));
            assertEquals("21", l_response.orderActionId);
            assertFalse(l_response.succSettingFlag);
        }
        catch (WEB3BaseException l_ex)
        {
            log.error(l_ex.getMessage(), l_ex);
            log.exiting(TEST_END + STR_METHOD_NAME);
            fail();
        }
        catch(Exception l_ex)
        {
            log.error(l_ex.getMessage(), l_ex);
            log.exiting(TEST_END + STR_METHOD_NAME);
            fail();
        }
        
        log.exiting(TEST_END + STR_METHOD_NAME);
    }
    
    /**
     * BUSINESS_ERROR_00184
     * 
     * リクエストデータの整合性をチェックする。
     */
    public void testSubmitOrder_C0004()
    {
        final String STR_METHOD_NAME = "testSubmitOrder_C0004()";
        log.entering(STR_METHOD_NAME);
 
        WEB3ToSuccOptionChangeOpenContractServiceImpl l_impl = new WEB3ToSuccOptionChangeOpenContractServiceImpl();
        try
        {
            WEB3SuccOptionsOpenChangeCompleteRequest l_request = new WEB3SuccOptionsOpenChangeCompleteRequest();
            l_request.orderPriceDiv = null;
            l_impl.submitOrder(l_request);
            
            log.exiting(TEST_END + STR_METHOD_NAME);
            fail();
        }
        catch (WEB3BaseException l_ex)
        {
            log.error(l_ex.getMessage(), l_ex);
            log.exiting(TEST_END + STR_METHOD_NAME);
            assertEquals(WEB3ErrorCatalog.BUSINESS_ERROR_00184, l_ex.getErrorInfo());
        }
        catch(Exception l_ex)
        {
            log.error(l_ex.getMessage(), l_ex);
            log.exiting(TEST_END + STR_METHOD_NAME);
            fail();
        }
    }
    
    /**
     * SYSTEM_ERROR_80005
     * 
     * validate訂正可能状態
     */
    public void testSubmitOrder_C0005()
    {
        final String STR_METHOD_NAME = "testSubmitOrder_C0005()";
        log.entering(STR_METHOD_NAME);
 
        WEB3ToSuccOptionChangeOpenContractServiceImpl l_impl = new WEB3ToSuccOptionChangeOpenContractServiceImpl();
        MOCK_MANAGER.setIsMockUsed(true);
        try
        {
            WEB3SuccOptionsOpenChangeCompleteRequest l_request = new WEB3SuccOptionsOpenChangeCompleteRequest();
            l_request.orderPriceDiv = "1";
            l_request.limitPrice = "1";
            l_request.execCondType = "1";
            l_request.expirationDateType = "1";
            l_request.expirationDate = null;
            l_request.orderCondType = "0";
            l_request.stopOrderCondPrice = null;
            l_request.stopOrderCondOperator = null;
            l_request.wlimitOrderCondPrice = null;
            l_request.wlimitOrderCondOperator = null;
            l_request.wLimitOrderPriceDiv = null;
            l_request.wLimitPrice = null;
            l_request.wlimitExecCondType = null;
            l_request.wlimitEnableStatusDiv = null;
            //super.validate()
            l_request.id = "21";
            l_request.opOrderQuantity = "1000";
            //２）　@確認時概算建代金チェック
            l_request.estimatedPrice = "1000";
            //３） 連続注文単価調整値情報チェック
            l_request.priceAdjustmentValueInfo = null;
            l_request.checkDate = WEB3DateUtility.getDate("20080811", "yyyyMMdd");
    
            TestDBUtility.deleteAll(IfoOrderUnitParams.TYPE);
            TestDBUtility.deleteAll(SubAccountParams.TYPE);
            TestDBUtility.deleteAll(ProductParams.TYPE);
            TestDBUtility.deleteAll(IfoProductParams.TYPE);
            TestDBUtility.deleteAll(TraderParams.TYPE);
            TestDBUtility.deleteAll(SubAccountParams.TYPE);
            TestDBUtility.deleteAll(MainAccountParams.TYPE);
            TestDBUtility.deleteAll(RsvIfoOrderUnitParams.TYPE);
            TestDBUtility.deleteAll(BranchParams.TYPE);
            TestDBUtility.deleteAll(IfoProductParams.TYPE);
            TestDBUtility.deleteAll(TradedProductParams.TYPE);
            TestDBUtility.deleteAll(IfoTradedProductParams.TYPE);
            TestDBUtility.deleteAll(MarketParams.TYPE);
            TestDBUtility.deleteAll(IfoOrderUnitParams.TYPE);
            
            TestDBUtility.deleteAll(TradingTimeParams.TYPE);
            TestDBUtility.deleteAll(InstitutionParams.TYPE);
            
            WEB3GentradeTradingTimeManagementForMock.mockGetOrderBizDate(WEB3DateUtility.getDate("20080808", "yyyyMMdd"));
 
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                    "com.fitechlabs.xtrade.plugin.security.oplogin.impl.OpLoginSecurityServiceImpl", "getAccountId",
                    new Class[] {}, new Long("333812512246"));
            
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                    "com.fitechlabs.xtrade.plugin.security.oplogin.impl.OpLoginSecurityServiceImpl", "getLoginInfo",
                    new Class[] {},
                    new LoginInfoImplForMock());
            
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                    "com.fitechlabs.xtrade.plugin.security.oplogin.impl.LoginInfoImpl",
                    "getLoginId",
                    new Class[] {},
                    new Long(256));
            
            TestDBUtility.deleteAll(OrderexecutionEndRow.TYPE);
            OrderexecutionEndParams l_orderexecutionEndParams = TestDBUtility.getOrderexecutionEndRow();
            l_orderexecutionEndParams.setInstitutionCode("0D");
            l_orderexecutionEndParams.setProductType(ProductTypeEnum.IFO);
            l_orderexecutionEndParams.setFutureOptionDiv("2");
            l_orderexecutionEndParams.setOrderexecutionEndType("0");
            TestDBUtility.insertWithDel(l_orderexecutionEndParams);
            
            RsvIfoOrderUnitParams l_rsvIfoOrderUnitParams = new RsvIfoOrderUnitParams();
            l_rsvIfoOrderUnitParams.setAccountId(11);
            l_rsvIfoOrderUnitParams.setMarketId(3303L);
            l_rsvIfoOrderUnitParams.setSubAccountId(123);
            l_rsvIfoOrderUnitParams.setBranchId(123);
            l_rsvIfoOrderUnitParams.setOrderId(21);
            l_rsvIfoOrderUnitParams.setOrderType(OrderTypeEnum.UNDEFINED);
            l_rsvIfoOrderUnitParams.setOrderCateg(OrderCategEnum.UNDEFINED);
            l_rsvIfoOrderUnitParams.setLastOrderActionSerialNo(0);
            l_rsvIfoOrderUnitParams.setProductType(ProductTypeEnum.IFO);
            l_rsvIfoOrderUnitParams.setQuantity(23);
            l_rsvIfoOrderUnitParams.setOrderStatus(OrderStatusEnum.UNDEFINED);
            l_rsvIfoOrderUnitParams.setOrderOpenStatus(OrderOpenStatusEnum.OPEN);
            l_rsvIfoOrderUnitParams.setExpirationStatus(OrderExpirationStatusEnum.UNDEFINED);
            l_rsvIfoOrderUnitParams.setTaxType(TaxTypeEnum.UNDEFINED);
            l_rsvIfoOrderUnitParams.setBizDate("20070117");
            l_rsvIfoOrderUnitParams.setProductId(3304148080000L);
            l_rsvIfoOrderUnitParams.setParentOrderId(56);
            l_rsvIfoOrderUnitParams.setSerialNoInParent(68);
            l_rsvIfoOrderUnitParams.setOrderOpenStatus(OrderOpenStatusEnum.OPEN);
            l_rsvIfoOrderUnitParams.setCreatedTimestamp(Calendar.getInstance().getTime());
            l_rsvIfoOrderUnitParams.setFutureOptionDiv("2");
            l_rsvIfoOrderUnitParams.setReserveOrderTradingType("15");
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
            
            MarketParams l_marketParams = TestDBUtility.getMarketRow();
            l_marketParams.setMarketId(3303L);
            l_marketParams.setMarketCode("1");
            TestDBUtility.insertWithDel(l_marketParams);
            
            SubAccountParams l_subAccountParams = TestDBUtility.getSubAccountRow();
            l_subAccountParams.setAccountId(333812512246L);
            l_subAccountParams.setSubAccountType(SubAccountTypeEnum.EQUITY_SUB_ACCOUNT);
            TestDBUtility.insertWithDel(l_subAccountParams);
            MainAccountParams l_mainAccountParams = TestDBUtility.getMainAccountRow();
            l_mainAccountParams.setIfoAccOpenDivTokyo("1");
            l_mainAccountParams.setQuotoType("1");
            l_mainAccountParams.setBranchId(1234);
            TestDBUtility.insertWithDel(l_mainAccountParams);
            BranchParams l_branchParams = TestDBUtility.getBranchRow();
            l_branchParams.setBranchId(1234);
            l_branchParams.setCloseWorngSysFuture(1);
            TestDBUtility.insertWithDel(l_branchParams);
            InstitutionParams l_institutionParams = TestDBUtility.getInstitutionRow();
            l_institutionParams.setInstitutionId(33);
            TestDBUtility.insertWithDel(l_institutionParams);
            
            IfoOrderUnitParams l_ifoOrderUnitParams = TestDBUtility.getIfoOrderUnitRow();
            l_ifoOrderUnitParams.setProductId(3304148080000L);
            l_ifoOrderUnitParams.setOrderId(56);
            l_ifoOrderUnitParams.setOrderUnitId(989);
            l_ifoOrderUnitParams.setMarketId(3303L);
            TestDBUtility.insertWithDel(l_ifoOrderUnitParams);
            TraderParams l_traderParam = TestDBUtility.getTraderRow();
            l_traderParam.setTraderId(3338111123L);
            l_traderParam.setLoginId(256L);
            TestDBUtility.insertWithDel(l_traderParam);
            
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
            l_ifoProductParams.setDerivativeType(IfoDerivativeTypeEnum.PUT_OPTIONS);
            TestDBUtility.insertWithDel(l_ifoProductParams);
 
            TradedProductParams l_tradedProductParams = TestDBUtility.getTradedProductRow();
            l_tradedProductParams.setTradedProductId(1006160060005L);
            l_tradedProductParams.setProductId(3304148080000L);
            l_tradedProductParams.setMarketId(3303L);
            l_tradedProductParams.setProductType(ProductTypeEnum.IFO);
            TestDBUtility.insertWithDel(l_tradedProductParams);
            
            IfoTradedProductParams l_ifoTradedProductParams = TestDBUtility.getIfoTradedProductRow();
            l_ifoTradedProductParams.setTradedProductId(1006160060005L);
            l_ifoTradedProductParams.setMarketId(3303L);
            l_ifoTradedProductParams.setProductId(3304148080000L);
            l_ifoTradedProductParams.setValidForBizDate(null);
            TestDBUtility.insertWithDel(l_ifoTradedProductParams);
            
            TradingTimeParams l_tradingTimeParams = TestDBUtility.getTradingTimeRow();
            l_tradingTimeParams.setInstitutionCode("0D");
            l_tradingTimeParams.setBranchCode("123");
            l_tradingTimeParams.setMarketCode("N1");
            l_tradingTimeParams.setTradingTimeType("01");
            l_tradingTimeParams.setProductCode("0");
            l_tradingTimeParams.setBizDateType("0");
            TestDBUtility.insertWithDel(l_tradingTimeParams);
            
            TradingTimeParams l_tradingTimeParams4 = TestDBUtility.getTradingTimeRow();
            l_tradingTimeParams4.setInstitutionCode("0D");
            l_tradingTimeParams4.setBranchCode("123");
            l_tradingTimeParams4.setMarketCode("N1");
            l_tradingTimeParams4.setTradingTimeType("01");
            l_tradingTimeParams4.setProductCode("0");
            l_tradingTimeParams4.setBizDateType("1");
            TestDBUtility.insertWithDel(l_tradingTimeParams4);
            
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
            
            WEB3IfoProductImpl l_ifoProductImpl = new WEB3IfoProductImpl(3304148080000L);
            
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue("webbroker3.ifo.WEB3OptionOrderManagerImpl",
                    "notifyRLS", new Class[]
                    { IfoOrderUnit.class, OrderManagerPersistenceContext.class },
                    null);
            
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue("webbroker3.triggerorder.WEB3ToSuccOrderManagerImpl",
                    "submitIfoOpenContractNewOrder", new Class[]
                    {SubAccount.class, WEB3IfoOpenContractOrderSpec.class, long.class, String.class, String.class, Double.class, IfoOrderUnit.class, WEB3IfoEstimateDeliveryAmountCalcResult.class},
                    null);
            
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                    "webbroker3.triggerorder.WEB3ToSuccOrderManagerImpl",
                    "validateSuccOrderMaxQuantity", new Class[]
                    {OrderUnit.class},
                    null);
            
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                    "webbroker3.triggerorder.WEB3ToSuccOrderManagerImpl", "isReversingTrade", new Class[]
                    {String.class, OrderUnit.class},
                    Boolean.FALSE);
            
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue("webbroker3.triggerorder.WEB3ToSuccOrderManagerImpl",
                    "validateSuccOrder", new Class[]
                    {WEB3GentradeSubAccount.class, ProductTypeEnum.class, String.class, String.class, OrderUnit.class},
                    null);
                    
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                    "webbroker3.ifo.WEB3IfoProductManagerImpl",
                    "getIfoProduct",
                    new Class[] 
                    {Institution.class, String.class, String.class, IfoDerivativeTypeEnum.class,
                     double.class, String.class, String.class},
                     l_ifoProductImpl);
            
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                    "webbroker3.ifo.WEB3IfoProductManagerImpl",
                    "getIfoProduct",
                    new Class[] { Institution.class, String.class },
                    l_ifoProductImpl);
            
            WEB3IfoTradedProductImpl l_ifoTradedProductImpl = new WEB3IfoTradedProductImpl(1006160060005L);
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                    "webbroker3.ifo.WEB3IfoProductManagerImpl",
                    "getIfoTradedProduct",
                    new Class[] { Institution.class, String.class, String.class },
                    l_ifoTradedProductImpl);
            
            ProcessingResult processingResult = ProcessingResult.newSuccessResultInstance();
            NewOrderValidationResult l_result = new NewOrderValidationResult(processingResult);
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                    "webbroker3.ifo.WEB3OptionOrderManagerImpl",
                    "validateOpenContractOrder", new Class[]
                    { WEB3GentradeSubAccount.class, WEB3IfoOpenContractOrderSpec.class },
                    l_result);
            
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                    "com.fitechlabs.xtrade.plugin.security.oplogin.impl.OpLoginSecurityServiceImpl", 
                    "getSessionProperty",
                    new Class[] {String.class},
                    "");
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                    "webbroker3.ifo.WEB3OptionOrderManagerImpl", "calcEstimateDeliveryAmount",
                    new Class[]
                    {WEB3GentradeCommission.class,
                     double.class,
                     SubAccount.class,
                     WEB3IfoTradedProductImpl.class,
                     double.class,
                     SideEnum.class,
                     boolean.class,
                     boolean.class},
                     null);

            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                    "webbroker3.triggerorder.WEB3ToSuccOrderManagerImpl",
                    "submitIfoChangeOpenContractOrder",
                    new Class[]
                    {SubAccount.class, WEB3ToSuccIfoChangeOpenContractOrderSpec.class, String.class,
                            WEB3ToSuccIfoOrderUnitImpl.class},
                            null);
            
            l_impl.submitOrder(l_request);
            
            log.exiting(TEST_END + STR_METHOD_NAME);
            fail();
        }
        catch (WEB3BaseException l_ex)
        {
            log.error(l_ex.getMessage(), l_ex);
            log.exiting(TEST_END + STR_METHOD_NAME);
            assertEquals(WEB3ErrorCatalog.SYSTEM_ERROR_80005, l_ex.getErrorInfo());
        }
        catch(Exception l_ex)
        {
            log.error(l_ex.getMessage(), l_ex);
            log.exiting(TEST_END + STR_METHOD_NAME);
            fail();
        }
        
        log.exiting(TEST_END + STR_METHOD_NAME);
    }
    
    /**
     * BUSINESS_ERROR_02816
     * 
     * validate夕場まで注文訂正可能(String, 先物OP予約注文単位Impl)
     */
    public void testSubmitOrder_C0006()
    {
        final String STR_METHOD_NAME = "testSubmitOrder_C0006()";
        log.entering(STR_METHOD_NAME);
 
        WEB3ToSuccOptionChangeOpenContractServiceImpl l_impl = new WEB3ToSuccOptionChangeOpenContractServiceImpl();
        MOCK_MANAGER.setIsMockUsed(true);
        try
        {
            WEB3SuccOptionsOpenChangeCompleteRequest l_request = new WEB3SuccOptionsOpenChangeCompleteRequest();
            l_request.orderPriceDiv = "1";
            l_request.limitPrice = "1";
            l_request.execCondType = "1";
            l_request.expirationDateType = "3";
            l_request.expirationDate = null;
            l_request.orderCondType = "0";
            l_request.stopOrderCondPrice = null;
            l_request.stopOrderCondOperator = null;
            l_request.wlimitOrderCondPrice = null;
            l_request.wlimitOrderCondOperator = null;
            l_request.wLimitOrderPriceDiv = null;
            l_request.wLimitPrice = null;
            l_request.wlimitExecCondType = null;
            l_request.wlimitEnableStatusDiv = null;
            //super.validate()
            l_request.id = "21";
            l_request.opOrderQuantity = "1000";
            //２）　@確認時概算建代金チェック
            l_request.estimatedPrice = "1000";
            //３） 連続注文単価調整値情報チェック
            l_request.priceAdjustmentValueInfo = null;
            l_request.checkDate = WEB3DateUtility.getDate("20080811", "yyyyMMdd");
    
            TestDBUtility.deleteAll(IfoOrderUnitParams.TYPE);
            TestDBUtility.deleteAll(SubAccountParams.TYPE);
            TestDBUtility.deleteAll(ProductParams.TYPE);
            TestDBUtility.deleteAll(IfoProductParams.TYPE);
            TestDBUtility.deleteAll(TraderParams.TYPE);
            TestDBUtility.deleteAll(SubAccountParams.TYPE);
            TestDBUtility.deleteAll(MainAccountParams.TYPE);
            TestDBUtility.deleteAll(RsvIfoOrderUnitParams.TYPE);
            TestDBUtility.deleteAll(BranchParams.TYPE);
            TestDBUtility.deleteAll(IfoProductParams.TYPE);
            TestDBUtility.deleteAll(TradedProductParams.TYPE);
            TestDBUtility.deleteAll(IfoTradedProductParams.TYPE);
            TestDBUtility.deleteAll(MarketParams.TYPE);
            TestDBUtility.deleteAll(IfoOrderUnitParams.TYPE);
            
            TestDBUtility.deleteAll(TradingTimeParams.TYPE);
            TestDBUtility.deleteAll(InstitutionParams.TYPE);
            
            WEB3GentradeTradingTimeManagementForMock.mockGetOrderBizDate(WEB3DateUtility.getDate("20080808", "yyyyMMdd"));
 
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                    "com.fitechlabs.xtrade.plugin.security.oplogin.impl.OpLoginSecurityServiceImpl", "getAccountId",
                    new Class[] {}, new Long("333812512246"));
            
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                    "com.fitechlabs.xtrade.plugin.security.oplogin.impl.OpLoginSecurityServiceImpl", "getLoginInfo",
                    new Class[] {},
                    new LoginInfoImplForMock());
            
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                    "com.fitechlabs.xtrade.plugin.security.oplogin.impl.LoginInfoImpl",
                    "getLoginId",
                    new Class[] {},
                    new Long(256));
            
            TestDBUtility.deleteAll(OrderexecutionEndRow.TYPE);
            OrderexecutionEndParams l_orderexecutionEndParams = TestDBUtility.getOrderexecutionEndRow();
            l_orderexecutionEndParams.setInstitutionCode("0D");
            l_orderexecutionEndParams.setProductType(ProductTypeEnum.IFO);
            l_orderexecutionEndParams.setFutureOptionDiv("2");
            l_orderexecutionEndParams.setOrderexecutionEndType("0");
            TestDBUtility.insertWithDel(l_orderexecutionEndParams);
            
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
            l_rsvIfoOrderUnitParams.setOrderOpenStatus(OrderOpenStatusEnum.OPEN);
            l_rsvIfoOrderUnitParams.setCreatedTimestamp(Calendar.getInstance().getTime());
            l_rsvIfoOrderUnitParams.setFutureOptionDiv("2");
            l_rsvIfoOrderUnitParams.setReserveOrderTradingType("15");
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
            
            MarketParams l_marketParams = TestDBUtility.getMarketRow();
            l_marketParams.setMarketId(3303L);
            l_marketParams.setMarketCode("1");
            TestDBUtility.insertWithDel(l_marketParams);
            
            SubAccountParams l_subAccountParams = TestDBUtility.getSubAccountRow();
            l_subAccountParams.setAccountId(333812512246L);
            l_subAccountParams.setSubAccountType(SubAccountTypeEnum.EQUITY_SUB_ACCOUNT);
            TestDBUtility.insertWithDel(l_subAccountParams);
            MainAccountParams l_mainAccountParams = TestDBUtility.getMainAccountRow();
            l_mainAccountParams.setIfoAccOpenDivTokyo("1");
            l_mainAccountParams.setQuotoType("1");
            l_mainAccountParams.setBranchId(1234);
            TestDBUtility.insertWithDel(l_mainAccountParams);
            BranchParams l_branchParams = TestDBUtility.getBranchRow();
            l_branchParams.setBranchId(1234);
            l_branchParams.setCloseWorngSysFuture(1);
            TestDBUtility.insertWithDel(l_branchParams);
            InstitutionParams l_institutionParams = TestDBUtility.getInstitutionRow();
            l_institutionParams.setInstitutionId(33);
            TestDBUtility.insertWithDel(l_institutionParams);
            
            IfoOrderUnitParams l_ifoOrderUnitParams = TestDBUtility.getIfoOrderUnitRow();
            l_ifoOrderUnitParams.setProductId(3304148080000L);
            l_ifoOrderUnitParams.setOrderId(56);
            l_ifoOrderUnitParams.setOrderUnitId(989);
            l_ifoOrderUnitParams.setMarketId(3303L);
            TestDBUtility.insertWithDel(l_ifoOrderUnitParams);
            TraderParams l_traderParam = TestDBUtility.getTraderRow();
            l_traderParam.setTraderId(3338111123L);
            l_traderParam.setLoginId(256L);
            TestDBUtility.insertWithDel(l_traderParam);
            
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
            l_ifoProductParams.setDerivativeType(IfoDerivativeTypeEnum.PUT_OPTIONS);
            TestDBUtility.insertWithDel(l_ifoProductParams);
 
            TradedProductParams l_tradedProductParams = TestDBUtility.getTradedProductRow();
            l_tradedProductParams.setTradedProductId(1006160060005L);
            l_tradedProductParams.setProductId(3304148080000L);
            l_tradedProductParams.setMarketId(3303L);
            l_tradedProductParams.setProductType(ProductTypeEnum.IFO);
            TestDBUtility.insertWithDel(l_tradedProductParams);
            
            IfoTradedProductParams l_ifoTradedProductParams = TestDBUtility.getIfoTradedProductRow();
            l_ifoTradedProductParams.setTradedProductId(1006160060005L);
            l_ifoTradedProductParams.setMarketId(3303L);
            l_ifoTradedProductParams.setProductId(3304148080000L);
            l_ifoTradedProductParams.setValidForBizDate(null);
            TestDBUtility.insertWithDel(l_ifoTradedProductParams);
            
            TradingTimeParams l_tradingTimeParams = TestDBUtility.getTradingTimeRow();
            l_tradingTimeParams.setInstitutionCode("0D");
            l_tradingTimeParams.setBranchCode("123");
            l_tradingTimeParams.setMarketCode("N1");
            l_tradingTimeParams.setTradingTimeType("01");
            l_tradingTimeParams.setProductCode("0");
            l_tradingTimeParams.setBizDateType("0");
            TestDBUtility.insertWithDel(l_tradingTimeParams);
            
            TradingTimeParams l_tradingTimeParams4 = TestDBUtility.getTradingTimeRow();
            l_tradingTimeParams4.setInstitutionCode("0D");
            l_tradingTimeParams4.setBranchCode("123");
            l_tradingTimeParams4.setMarketCode("N1");
            l_tradingTimeParams4.setTradingTimeType("01");
            l_tradingTimeParams4.setProductCode("0");
            l_tradingTimeParams4.setBizDateType("1");
            TestDBUtility.insertWithDel(l_tradingTimeParams4);
            
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
            
            TestDBUtility.deleteAll(EnableOrderConditionRow.TYPE);
            EnableOrderConditionParams l_enableOrderConditionParams = TestDBUtility.getEnableOrderConditionParamsRow();
            l_enableOrderConditionParams.setInstitutionCode("0D");
            l_enableOrderConditionParams.setProductType(ProductTypeEnum.IFO);
            l_enableOrderConditionParams.setFutureOptionDiv("2");
            l_enableOrderConditionParams.setMarginTradingDiv("0");
            l_enableOrderConditionParams.setMarketCode("0");
            TestDBUtility.insertWithDel(l_enableOrderConditionParams);
            
            WEB3IfoProductImpl l_ifoProductImpl = new WEB3IfoProductImpl(3304148080000L);
            
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue("webbroker3.ifo.WEB3OptionOrderManagerImpl",
                    "notifyRLS", new Class[]
                    { IfoOrderUnit.class, OrderManagerPersistenceContext.class },
                    null);
            
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue("webbroker3.triggerorder.WEB3ToSuccOrderManagerImpl",
                    "submitIfoOpenContractNewOrder", new Class[]
                    {SubAccount.class, WEB3IfoOpenContractOrderSpec.class, long.class, String.class, String.class, Double.class, IfoOrderUnit.class, WEB3IfoEstimateDeliveryAmountCalcResult.class},
                    null);
            
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                    "webbroker3.triggerorder.WEB3ToSuccOrderManagerImpl",
                    "validateSuccOrderMaxQuantity", new Class[]
                    {OrderUnit.class},
                    null);
            
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                    "webbroker3.triggerorder.WEB3ToSuccOrderManagerImpl", "isReversingTrade", new Class[]
                    {String.class, OrderUnit.class},
                    Boolean.FALSE);
            
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue("webbroker3.triggerorder.WEB3ToSuccOrderManagerImpl",
                    "validateSuccOrder", new Class[]
                    {WEB3GentradeSubAccount.class, ProductTypeEnum.class, String.class, String.class, OrderUnit.class},
                    null);
                    
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                    "webbroker3.ifo.WEB3IfoProductManagerImpl",
                    "getIfoProduct",
                    new Class[] 
                    {Institution.class, String.class, String.class, IfoDerivativeTypeEnum.class,
                     double.class, String.class, String.class},
                     l_ifoProductImpl);
            
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                    "webbroker3.ifo.WEB3IfoProductManagerImpl",
                    "getIfoProduct",
                    new Class[] { Institution.class, String.class },
                    l_ifoProductImpl);
            
            WEB3IfoTradedProductImpl l_ifoTradedProductImpl = new WEB3IfoTradedProductImpl(1006160060005L);
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                    "webbroker3.ifo.WEB3IfoProductManagerImpl",
                    "getIfoTradedProduct",
                    new Class[] { Institution.class, String.class, String.class },
                    l_ifoTradedProductImpl);
            
            ProcessingResult processingResult = ProcessingResult.newSuccessResultInstance();
            NewOrderValidationResult l_result = new NewOrderValidationResult(processingResult);
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                    "webbroker3.ifo.WEB3FuturesOrderManagerImpl",
                    "validateOpenContractOrder", new Class[]
                    { WEB3GentradeSubAccount.class, WEB3IfoOpenContractOrderSpec.class },
                    l_result);
            
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                    "com.fitechlabs.xtrade.plugin.security.oplogin.impl.OpLoginSecurityServiceImpl", 
                    "getSessionProperty",
                    new Class[] {String.class},
                    "");
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                    "webbroker3.ifo.WEB3FuturesOrderManagerImpl", "calcEstimateDeliveryAmount",
                    new Class[]
                    {WEB3GentradeCommission.class,
                     double.class,
                     SubAccount.class,
                     WEB3IfoTradedProductImpl.class,
                     double.class,
                     SideEnum.class,
                     boolean.class,
                     boolean.class},
                     null);

            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                    "webbroker3.triggerorder.WEB3ToSuccOrderManagerImpl",
                    "submitIfoChangeOpenContractOrder",
                    new Class[]
                    {SubAccount.class, WEB3ToSuccIfoChangeOpenContractOrderSpec.class, String.class,
                            WEB3ToSuccIfoOrderUnitImpl.class},
                            null);
            
            l_impl.submitOrder(l_request);
            log.exiting(TEST_END + STR_METHOD_NAME);
            fail();
        }
        catch (WEB3BaseException l_ex)
        {
            log.error(l_ex.getMessage(), l_ex);
            log.exiting(TEST_END + STR_METHOD_NAME);
            assertEquals(WEB3ErrorCatalog.BUSINESS_ERROR_02816, l_ex.getErrorInfo());
        }
        catch(Exception l_ex)
        {
            log.error(l_ex.getMessage(), l_ex);
            log.exiting(TEST_END + STR_METHOD_NAME);
            fail();
        }
    }
    
    /**
     * SYSTEM_ERROR_80017
     *
     * l_request == null
     */
    public void testCreateConfirmRequest_C0001()
    {
        final String STR_METHOD_NAME = "testCreateConfirmRequest_C0001()";
        log.entering(STR_METHOD_NAME);
        
        WEB3ToSuccOptionChangeOpenContractServiceImpl l_toSuccOptionChangeOpenContractServiceImpl
            = new WEB3ToSuccOptionChangeOpenContractServiceImpl();
        WEB3ToSuccIfoOrderUnitImpl l_toSuccIfoOrderUnitImpl = new WEB3ToSuccIfoOrderUnitImpl(null);
        try
        {
            l_toSuccOptionChangeOpenContractServiceImpl.createConfirmRequest(null, l_toSuccIfoOrderUnitImpl);
            log.exiting(TEST_END + STR_METHOD_NAME);
            fail();
        }
        catch (WEB3BaseException l_ex)
        {
            log.error(l_ex.getMessage(), l_ex);
            log.exiting(TEST_END + STR_METHOD_NAME);
            assertEquals(WEB3ErrorCatalog.SYSTEM_ERROR_80017, l_ex.getErrorInfo());
        }
        catch(Exception l_ex)
        {
            log.error(l_ex.getMessage(), l_ex);
            log.exiting(TEST_END + STR_METHOD_NAME);
            fail();
        }
    }
    
    /**
     * SYSTEM_ERROR_80017
     *
     * l_orderUnit == null
     */
    public void testCreateConfirmRequest_C0002()
    {
        final String STR_METHOD_NAME = "testCreateConfirmRequest_C0002()";
        log.entering(STR_METHOD_NAME);
        
        WEB3ToSuccOptionChangeOpenContractServiceImpl l_toSuccOptionChangeOpenContractServiceImpl
            = new WEB3ToSuccOptionChangeOpenContractServiceImpl();
        WEB3SuccOptionsOpenChangeConfirmRequest l_request =
            new WEB3SuccOptionsOpenChangeConfirmRequest();
        try
        {
            l_toSuccOptionChangeOpenContractServiceImpl.createConfirmRequest(l_request, null);
            log.exiting(TEST_END + STR_METHOD_NAME);
            fail();
        }
        catch (WEB3BaseException l_ex)
        {
            log.error(l_ex.getMessage(), l_ex);
            log.exiting(TEST_END + STR_METHOD_NAME);
            assertEquals(WEB3ErrorCatalog.SYSTEM_ERROR_80017, l_ex.getErrorInfo());
        }
        catch(Exception l_ex)
        {
            log.error(l_ex.getMessage(), l_ex);
            log.exiting(TEST_END + STR_METHOD_NAME);
            fail();
        }
    }

    /**
     * 正常終了
     * 
     * l_orderUnit.isBuyOrder() == true 的場合
     */
    public void testCreateConfirmRequest_C0003()
    {
        final String STR_METHOD_NAME = "testCreateConfirmRequest_C0003()";
        log.entering(STR_METHOD_NAME);

        WEB3SubCreateConfirmCompleteRequestToSuccOptionChangeOpenContractServiceImpl l_subCreateConfirmRequestToSuccOptionChangeOpenContractServiceImpl
            = new WEB3SubCreateConfirmCompleteRequestToSuccOptionChangeOpenContractServiceImpl();
        WEB3SuccOptionsOpenChangeConfirmRequest l_request =
            new WEB3SuccOptionsOpenChangeConfirmRequest();
        l_request.priceAdjustmentValueInfo = new WEB3SuccPriceAdjustmentValueInfo();
        l_request.priceAdjustmentValueInfo.priceAdjustmentValue = "1";
        RsvIfoOrderUnitParams l_rsvIfoOrderUnitParams =
            new RsvIfoOrderUnitParams();
        l_rsvIfoOrderUnitParams.product_id = 3304148080000L;
        l_rsvIfoOrderUnitParams.order_type = OrderTypeEnum.IDX_FUTURES_BUY_TO_OPEN;
        l_rsvIfoOrderUnitParams.market_id = new Long(3303L);
        
        WEB3ToSuccIfoOrderUnitImpl l_toSuccIfoOrderUnitImpl =
            new WEB3ToSuccIfoOrderUnitImpl(l_rsvIfoOrderUnitParams);
        
        try
        {
            TestDBUtility.deleteAll(ProductRow.TYPE);
            ProductParams l_productParams = TestDBUtility.getProductRow();
            l_productParams.setProductType(ProductTypeEnum.IFO);
            TestDBUtility.insertWithDel(l_productParams);
            
            TestDBUtility.deleteAll(IfoProductRow.TYPE);
            IfoProductParams l_ifoProductParams = TestDBUtility.getIfoProductRow();
            l_ifoProductParams.setProductId(3304148080000L);
            TestDBUtility.insertWithDel(l_ifoProductParams);
            
            TestDBUtility.deleteAll(MarketRow.TYPE);
            MarketParams l_marketParams = TestDBUtility.getMarketRow();
            l_marketParams.setMarketId(3303L);
            TestDBUtility.insertWithDel(l_marketParams);
            
            WEB3SuccOptionsOpenConfirmRequest l_succOptionsOpenConfirmRequest =
                l_subCreateConfirmRequestToSuccOptionChangeOpenContractServiceImpl.createConfirmRequest(l_request, l_toSuccIfoOrderUnitImpl);
            
            assertEquals("160030005", l_succOptionsOpenConfirmRequest.opProductCode);
            assertEquals(WEB3IfoContractTypeDef.OPEN_BUY, l_succOptionsOpenConfirmRequest.contractType);
            assertEquals("SP", l_succOptionsOpenConfirmRequest.marketCode);
            assertEquals("0005", l_succOptionsOpenConfirmRequest.targetProductCode);
            assertEquals("200503", l_succOptionsOpenConfirmRequest.delivaryMonth);
            assertNull(l_succOptionsOpenConfirmRequest.opProductType);
            assertEquals("0", l_succOptionsOpenConfirmRequest.strikePrice);
            assertEquals("0", l_succOptionsOpenConfirmRequest.succCommonInfo.parentOrderId);
            assertEquals("1", l_succOptionsOpenConfirmRequest.priceAdjustmentValueInfo.priceAdjustmentValue);
            
            log.exiting(TEST_END + STR_METHOD_NAME);
        }
        catch (WEB3BaseException l_ex)
        {
            log.error(l_ex.getMessage(), l_ex);
            log.exiting(TEST_END + STR_METHOD_NAME);
            fail();
        }
        catch(Exception l_ex)
        {
            log.error(l_ex.getMessage(), l_ex);
            log.exiting(TEST_END + STR_METHOD_NAME);
            fail();
        }
    }
    
    /**
     * 正常終了
     * 
     * l_orderUnit.isBuyOrder() == false 的場合
     */
    public void testCreateConfirmRequest_C0004()
    {
        final String STR_METHOD_NAME = "testCreateConfirmRequest_C0004()";
        log.entering(STR_METHOD_NAME);

        WEB3SubCreateConfirmCompleteRequestToSuccOptionChangeOpenContractServiceImpl l_subCreateConfirmRequestToSuccOptionChangeOpenContractServiceImpl
            = new WEB3SubCreateConfirmCompleteRequestToSuccOptionChangeOpenContractServiceImpl();
        WEB3SuccOptionsOpenChangeConfirmRequest l_request =
            new WEB3SuccOptionsOpenChangeConfirmRequest();
        l_request.priceAdjustmentValueInfo = new WEB3SuccPriceAdjustmentValueInfo();
        l_request.priceAdjustmentValueInfo.priceAdjustmentValue = "1";
        RsvIfoOrderUnitParams l_rsvIfoOrderUnitParams =
            new RsvIfoOrderUnitParams();
        l_rsvIfoOrderUnitParams.product_id = 3304148080000L;
        l_rsvIfoOrderUnitParams.order_type = OrderTypeEnum.BOND_SELL;
        l_rsvIfoOrderUnitParams.market_id = new Long(3303L);
        
        WEB3ToSuccIfoOrderUnitImpl l_toSuccIfoOrderUnitImpl =
            new WEB3ToSuccIfoOrderUnitImpl(l_rsvIfoOrderUnitParams);
        
        try
        {
            TestDBUtility.deleteAll(ProductRow.TYPE);
            ProductParams l_productParams = TestDBUtility.getProductRow();
            l_productParams.setProductType(ProductTypeEnum.IFO);
            TestDBUtility.insertWithDel(l_productParams);
            
            TestDBUtility.deleteAll(IfoProductRow.TYPE);
            IfoProductParams l_ifoProductParams = TestDBUtility.getIfoProductRow();
            l_ifoProductParams.setProductId(3304148080000L);
            TestDBUtility.insertWithDel(l_ifoProductParams);
            
            TestDBUtility.deleteAll(MarketRow.TYPE);
            MarketParams l_marketParams = TestDBUtility.getMarketRow();
            TestDBUtility.insertWithDel(l_marketParams);
            
            WEB3SuccOptionsOpenConfirmRequest l_succOptionsOpenConfirmRequest =
                l_subCreateConfirmRequestToSuccOptionChangeOpenContractServiceImpl.createConfirmRequest(l_request, l_toSuccIfoOrderUnitImpl);
            
            assertEquals("160030005", l_succOptionsOpenConfirmRequest.opProductCode);
            assertEquals(WEB3IfoContractTypeDef.OPEN_SELL, l_succOptionsOpenConfirmRequest.contractType);
            assertEquals("SP", l_succOptionsOpenConfirmRequest.marketCode);
            assertEquals("0005", l_succOptionsOpenConfirmRequest.targetProductCode);
            assertEquals("200503", l_succOptionsOpenConfirmRequest.delivaryMonth);
            assertNull(l_succOptionsOpenConfirmRequest.opProductType);
            assertEquals("0", l_succOptionsOpenConfirmRequest.strikePrice);
            assertEquals("0", l_succOptionsOpenConfirmRequest.succCommonInfo.parentOrderId);
            assertEquals("1", l_succOptionsOpenConfirmRequest.priceAdjustmentValueInfo.priceAdjustmentValue);
            
            log.exiting(TEST_END + STR_METHOD_NAME);
        }
        catch (WEB3BaseException l_ex)
        {
            log.error(l_ex.getMessage(), l_ex);
            log.exiting(TEST_END + STR_METHOD_NAME);
            fail();
        }
        catch(Exception l_ex)
        {
            log.error(l_ex.getMessage(), l_ex);
            log.exiting(TEST_END + STR_METHOD_NAME);
            fail();
        }
    }
    
    /**
     * SYSTEM_ERROR_80005
     */
    public void testCreateConfirmRequest_C0005()
    {
        final String STR_METHOD_NAME = "testCreateConfirmRequest_C0005()";
        log.entering(STR_METHOD_NAME);

        WEB3SubCreateConfirmCompleteRequestToSuccOptionChangeOpenContractServiceImpl l_subCreateConfirmRequestToSuccOptionChangeOpenContractServiceImpl
            = new WEB3SubCreateConfirmCompleteRequestToSuccOptionChangeOpenContractServiceImpl();
        WEB3SuccOptionsOpenChangeConfirmRequest l_request =
            new WEB3SuccOptionsOpenChangeConfirmRequest();
        l_request.priceAdjustmentValueInfo = new WEB3SuccPriceAdjustmentValueInfo();
        l_request.priceAdjustmentValueInfo.priceAdjustmentValue = "1";
        RsvIfoOrderUnitParams l_rsvIfoOrderUnitParams =
            new RsvIfoOrderUnitParams();
        l_rsvIfoOrderUnitParams.product_id = 3304148080000L;
        l_rsvIfoOrderUnitParams.order_type = OrderTypeEnum.BOND_SELL;
        l_rsvIfoOrderUnitParams.market_id = new Long(3303L);
        
        WEB3ToSuccIfoOrderUnitImpl l_toSuccIfoOrderUnitImpl =
            new WEB3ToSuccIfoOrderUnitImpl(l_rsvIfoOrderUnitParams);
        
        try
        {
            TestDBUtility.deleteAll(ProductRow.TYPE);
            ProductParams l_productParams = TestDBUtility.getProductRow();
            l_productParams.setProductType(ProductTypeEnum.IFO);
            TestDBUtility.insertWithDel(l_productParams);
            
            TestDBUtility.deleteAll(IfoProductRow.TYPE);
            IfoProductParams l_ifoProductParams = TestDBUtility.getIfoProductRow();
            l_ifoProductParams.setProductId(3304148080000L);
            TestDBUtility.insertWithDel(l_ifoProductParams);
            
            l_subCreateConfirmRequestToSuccOptionChangeOpenContractServiceImpl.createConfirmRequest(l_request, l_toSuccIfoOrderUnitImpl);
            
            log.exiting(TEST_END + STR_METHOD_NAME);
            fail();
        }
        catch (WEB3BaseException l_ex)
        {
            log.error(l_ex.getMessage(), l_ex);
            log.exiting(TEST_END + STR_METHOD_NAME);
            assertEquals(WEB3ErrorCatalog.SYSTEM_ERROR_80005, l_ex.getErrorInfo());
        }
        catch(Exception l_ex)
        {
            log.error(l_ex.getMessage(), l_ex);
            log.exiting(TEST_END + STR_METHOD_NAME);
            fail();
        }
    }
    
    protected class WEB3SubCreateConfirmCompleteRequestToSuccOptionChangeOpenContractServiceImpl extends WEB3ToSuccOptionChangeOpenContractServiceImpl
    {
        protected WEB3OptionsCommonRequest setOptionsCommonRequest(
            WEB3OptionsCommonRequest l_outputCommonRequest,
            WEB3OptionsCommonRequest l_inputCommonRequest)
        {
            return null;
        }
    }
    
    /**
     * SYSTEM_ERROR_80017
     *
     * l_request == null
     */
    public void testCreateCompleteRequest_C0001()
    {
        final String STR_METHOD_NAME = "testCreateCompleteRequest_C0001()";
        log.entering(STR_METHOD_NAME);
        
        WEB3ToSuccOptionChangeOpenContractServiceImpl l_toSuccOptionChangeOpenContractServiceImpl
            = new WEB3ToSuccOptionChangeOpenContractServiceImpl();
        WEB3ToSuccIfoOrderUnitImpl l_toSuccIfoOrderUnitImpl = new WEB3ToSuccIfoOrderUnitImpl(null);
        try
        {
            l_toSuccOptionChangeOpenContractServiceImpl.createCompleteRequest(null, l_toSuccIfoOrderUnitImpl);
            log.exiting(TEST_END + STR_METHOD_NAME);
            fail();
        }
        catch (WEB3BaseException l_ex)
        {
            log.error(l_ex.getMessage(), l_ex);
            log.exiting(TEST_END + STR_METHOD_NAME);
            assertEquals(WEB3ErrorCatalog.SYSTEM_ERROR_80017, l_ex.getErrorInfo());
        }
        catch(Exception l_ex)
        {
            log.error(l_ex.getMessage(), l_ex);
            log.exiting(TEST_END + STR_METHOD_NAME);
            fail();
        }
    }
    
    /**
     * SYSTEM_ERROR_80017
     *
     * l_orderUnit == null
     */
    public void testCreateCompleteRequest_C0002()
    {
        final String STR_METHOD_NAME = "testCreateCompleteRequest_C0002()";
        log.entering(STR_METHOD_NAME);
        
        WEB3ToSuccOptionChangeOpenContractServiceImpl l_toSuccOptionChangeOpenContractServiceImpl
            = new WEB3ToSuccOptionChangeOpenContractServiceImpl();
        WEB3SuccOptionsOpenChangeCompleteRequest l_request =
            new WEB3SuccOptionsOpenChangeCompleteRequest();
        try
        {
            l_toSuccOptionChangeOpenContractServiceImpl.createCompleteRequest(l_request, null);
            log.exiting(TEST_END + STR_METHOD_NAME);
            fail();
        }
        catch (WEB3BaseException l_ex)
        {
            log.error(l_ex.getMessage(), l_ex);
            log.exiting(TEST_END + STR_METHOD_NAME);
            assertEquals(WEB3ErrorCatalog.SYSTEM_ERROR_80017, l_ex.getErrorInfo());
        }
        catch(Exception l_ex)
        {
            log.error(l_ex.getMessage(), l_ex);
            log.exiting(TEST_END + STR_METHOD_NAME);
            fail();
        }
    }
    
    /**
     * 正常終了
     * 
     * l_orderUnit.isBuyOrder() == true 的場合
     */
    public void testCreateCompleteRequest_C0003()
    {
        final String STR_METHOD_NAME = "testCreateCompleteRequest_C0003()";
        log.entering(STR_METHOD_NAME);
        
        WEB3SubCreateConfirmCompleteRequestToSuccOptionChangeOpenContractServiceImpl l_subCreateConfirmCompleteRequestToSuccOptionChangeOpenContractServiceImpl= 
            new WEB3SubCreateConfirmCompleteRequestToSuccOptionChangeOpenContractServiceImpl();
        WEB3SuccOptionsOpenChangeCompleteRequest l_request =
            new WEB3SuccOptionsOpenChangeCompleteRequest();
        l_request.priceAdjustmentValueInfo = new WEB3SuccPriceAdjustmentValueInfo();
        l_request.priceAdjustmentValueInfo.priceAdjustmentValue = "1";
        l_request.password = "123456";
        l_request.checkPrice = "q";
        Date l_datExpect = WEB3DateUtility.getDate("20040710","yyyyMMdd");
        l_request.checkDate = l_datExpect;
        l_request.afterAdjustmentPrice = "q";
        
        RsvIfoOrderUnitParams l_rsvIfoOrderUnitParams =
            new RsvIfoOrderUnitParams();
        l_rsvIfoOrderUnitParams.product_id = 3304148080000L;
        l_rsvIfoOrderUnitParams.order_type = OrderTypeEnum.IDX_FUTURES_BUY_TO_OPEN;
        l_rsvIfoOrderUnitParams.market_id = new Long(3303L);
        
        WEB3ToSuccIfoOrderUnitImpl l_toSuccIfoOrderUnitImpl =
            new WEB3ToSuccIfoOrderUnitImpl(l_rsvIfoOrderUnitParams);
        
        try
        {
            TestDBUtility.deleteAll(ProductRow.TYPE);
            ProductParams l_productParams = TestDBUtility.getProductRow();
            l_productParams.setProductType(ProductTypeEnum.IFO);
            TestDBUtility.insertWithDel(l_productParams);
            
            TestDBUtility.deleteAll(IfoProductRow.TYPE);
            IfoProductParams l_ifoProductParams = TestDBUtility.getIfoProductRow();
            l_ifoProductParams.setProductId(3304148080000L);
            TestDBUtility.insertWithDel(l_ifoProductParams);
            
            TestDBUtility.deleteAll(MarketRow.TYPE);
            MarketParams l_marketParams = TestDBUtility.getMarketRow();
            TestDBUtility.insertWithDel(l_marketParams);
            
            WEB3SuccOptionsOpenCompleteRequest l_succOptionsOpenCompleteRequest =
                l_subCreateConfirmCompleteRequestToSuccOptionChangeOpenContractServiceImpl.createCompleteRequest(l_request, l_toSuccIfoOrderUnitImpl);
            
            assertEquals("0", l_succOptionsOpenCompleteRequest.orderId);
            assertEquals("160030005", l_succOptionsOpenCompleteRequest.opProductCode);
            assertEquals("1", l_succOptionsOpenCompleteRequest.contractType);
            assertEquals("SP", l_succOptionsOpenCompleteRequest.marketCode);
            assertEquals("123456", l_succOptionsOpenCompleteRequest.password);
            assertEquals("q", l_succOptionsOpenCompleteRequest.checkPrice);
            Date l_datResult = WEB3DateUtility.getDate("20040710","yyyyMMdd");
            assertEquals(l_datResult, l_succOptionsOpenCompleteRequest.checkDate);
            assertEquals("0005", l_succOptionsOpenCompleteRequest.targetProductCode);
            assertEquals("200503", l_succOptionsOpenCompleteRequest.delivaryMonth);
            assertNull(l_succOptionsOpenCompleteRequest.opProductType);
            assertEquals("0", l_succOptionsOpenCompleteRequest.strikePrice);
            assertEquals("0", l_succOptionsOpenCompleteRequest.succCommonInfo.parentOrderId);
            assertEquals("1", l_succOptionsOpenCompleteRequest.priceAdjustmentValueInfo.priceAdjustmentValue);
            assertEquals("q", l_succOptionsOpenCompleteRequest.afterAdjustmentPrice);
            
    
            log.exiting(TEST_END + STR_METHOD_NAME);
        }
        catch (WEB3BaseException l_ex)
        {
            log.error(l_ex.getMessage(), l_ex);
            log.exiting(TEST_END + STR_METHOD_NAME);
            fail();
        }
        catch(Exception l_ex)
        {
            log.error(l_ex.getMessage(), l_ex);
            log.exiting(TEST_END + STR_METHOD_NAME);
            fail();
        }
    }
    
    /**
     * 正常終了
     * 
     * l_orderUnit.isBuyOrder() == false 的場合
     */
    public void testCreateCompleteRequest_C0004()
    {
        final String STR_METHOD_NAME = "testCreateCompleteRequest_C0004()";
        log.entering(STR_METHOD_NAME);
        
        WEB3SubCreateConfirmCompleteRequestToSuccOptionChangeOpenContractServiceImpl l_subCreateConfirmCompleteRequestToSuccOptionChangeOpenContractServiceImpl = 
            new WEB3SubCreateConfirmCompleteRequestToSuccOptionChangeOpenContractServiceImpl();
        WEB3SuccOptionsOpenChangeCompleteRequest l_request =
            new WEB3SuccOptionsOpenChangeCompleteRequest();
        l_request.priceAdjustmentValueInfo = new WEB3SuccPriceAdjustmentValueInfo();
        l_request.priceAdjustmentValueInfo.priceAdjustmentValue = "1";
        l_request.password = "123456";
        l_request.checkPrice = "q";
        Date l_datExpect = WEB3DateUtility.getDate("20040710","yyyyMMdd");
        l_request.checkDate = l_datExpect;
        l_request.afterAdjustmentPrice = "q";
        
        RsvIfoOrderUnitParams l_rsvIfoOrderUnitParams =
            new RsvIfoOrderUnitParams();
        l_rsvIfoOrderUnitParams.product_id = 3304148080000L;
        l_rsvIfoOrderUnitParams.order_type = OrderTypeEnum.BOND_SELL;
        l_rsvIfoOrderUnitParams.market_id = new Long(3303L);
        
        WEB3ToSuccIfoOrderUnitImpl l_toSuccIfoOrderUnitImpl =
            new WEB3ToSuccIfoOrderUnitImpl(l_rsvIfoOrderUnitParams);
        
        try
        {
            TestDBUtility.deleteAll(ProductRow.TYPE);
            ProductParams l_productParams = TestDBUtility.getProductRow();
            l_productParams.setProductType(ProductTypeEnum.IFO);
            TestDBUtility.insertWithDel(l_productParams);
            
            TestDBUtility.deleteAll(IfoProductRow.TYPE);
            IfoProductParams l_ifoProductParams = TestDBUtility.getIfoProductRow();
            l_ifoProductParams.setProductId(3304148080000L);
            TestDBUtility.insertWithDel(l_ifoProductParams);
            
            TestDBUtility.deleteAll(MarketRow.TYPE);
            MarketParams l_marketParams = TestDBUtility.getMarketRow();
            TestDBUtility.insertWithDel(l_marketParams);
            
            WEB3SuccOptionsOpenCompleteRequest l_succOptionsOpenCompleteRequest =
                l_subCreateConfirmCompleteRequestToSuccOptionChangeOpenContractServiceImpl.createCompleteRequest(l_request, l_toSuccIfoOrderUnitImpl);
            
            assertEquals("0", l_succOptionsOpenCompleteRequest.orderId);
            assertEquals("160030005", l_succOptionsOpenCompleteRequest.opProductCode);
            assertEquals("2", l_succOptionsOpenCompleteRequest.contractType);
            assertEquals("SP", l_succOptionsOpenCompleteRequest.marketCode);
            assertEquals("123456", l_succOptionsOpenCompleteRequest.password);
            assertEquals("q", l_succOptionsOpenCompleteRequest.checkPrice);
            Date l_datResult = WEB3DateUtility.getDate("20040710","yyyyMMdd");
            assertEquals(l_datResult, l_succOptionsOpenCompleteRequest.checkDate);
            assertEquals("0005", l_succOptionsOpenCompleteRequest.targetProductCode);
            assertEquals("200503", l_succOptionsOpenCompleteRequest.delivaryMonth);
            assertNull(l_succOptionsOpenCompleteRequest.opProductType);
            assertEquals("0", l_succOptionsOpenCompleteRequest.strikePrice);
            assertEquals("0", l_succOptionsOpenCompleteRequest.succCommonInfo.parentOrderId);
            assertEquals("1", l_succOptionsOpenCompleteRequest.priceAdjustmentValueInfo.priceAdjustmentValue);
            assertEquals("q", l_succOptionsOpenCompleteRequest.afterAdjustmentPrice);
            
    
            log.exiting(TEST_END + STR_METHOD_NAME);
        }
        catch (WEB3BaseException l_ex)
        {
            log.error(l_ex.getMessage(), l_ex);
            log.exiting(TEST_END + STR_METHOD_NAME);
            fail();
        }
        catch(Exception l_ex)
        {
            log.error(l_ex.getMessage(), l_ex);
            log.exiting(TEST_END + STR_METHOD_NAME);
            fail();
        }
    }
    
    /**
     * SYSTEM_ERROR_80005
     */
    public void testCreateCompleteRequest_C0005()
    {
        final String STR_METHOD_NAME = "testCreateCompleteRequest_C0005()";
        log.entering(STR_METHOD_NAME);
        
        WEB3SubCreateConfirmCompleteRequestToSuccOptionChangeOpenContractServiceImpl l_subCreateConfirmCompleteRequestToSuccOptionChangeOpenContractServiceImpl = 
            new WEB3SubCreateConfirmCompleteRequestToSuccOptionChangeOpenContractServiceImpl();
        WEB3SuccOptionsOpenChangeCompleteRequest l_request =
            new WEB3SuccOptionsOpenChangeCompleteRequest();
        l_request.priceAdjustmentValueInfo = new WEB3SuccPriceAdjustmentValueInfo();
        l_request.priceAdjustmentValueInfo.priceAdjustmentValue = "1";
        l_request.password = "123456";
        l_request.checkPrice = "q";
        Date l_datExpect = WEB3DateUtility.getDate("20040710","yyyyMMdd");
        l_request.checkDate = l_datExpect;
        l_request.afterAdjustmentPrice = "q";
        
        RsvIfoOrderUnitParams l_rsvIfoOrderUnitParams =
            new RsvIfoOrderUnitParams();
        l_rsvIfoOrderUnitParams.product_id = 3304148080000L;
        l_rsvIfoOrderUnitParams.order_type = OrderTypeEnum.BOND_SELL;
        l_rsvIfoOrderUnitParams.market_id = new Long(3303L);
        
        WEB3ToSuccIfoOrderUnitImpl l_toSuccIfoOrderUnitImpl =
            new WEB3ToSuccIfoOrderUnitImpl(l_rsvIfoOrderUnitParams);
        
        try
        {
            TestDBUtility.deleteAll(ProductRow.TYPE);
            ProductParams l_productParams = TestDBUtility.getProductRow();
            l_productParams.setProductType(ProductTypeEnum.IFO);
            TestDBUtility.insertWithDel(l_productParams);
            
            TestDBUtility.deleteAll(IfoProductRow.TYPE);
            IfoProductParams l_ifoProductParams = TestDBUtility.getIfoProductRow();
            l_ifoProductParams.setProductId(3304148080000L);
            TestDBUtility.insertWithDel(l_ifoProductParams);
            
            l_subCreateConfirmCompleteRequestToSuccOptionChangeOpenContractServiceImpl.createCompleteRequest(l_request, l_toSuccIfoOrderUnitImpl);
    
            log.exiting(TEST_END + STR_METHOD_NAME);
            fail();
        }
        catch (WEB3BaseException l_ex)
        {
            log.error(l_ex.getMessage(), l_ex);
            log.exiting(TEST_END + STR_METHOD_NAME);
            assertEquals(WEB3ErrorCatalog.SYSTEM_ERROR_80005, l_ex.getErrorInfo());
        }
        catch(Exception l_ex)
        {
            log.error(l_ex.getMessage(), l_ex);
            log.exiting(TEST_END + STR_METHOD_NAME);
            fail();
        }
    }

    /**
     * 正常終了
     */
    public void testSetOptionsCommonRequest_C0001()
    {
        final String STR_METHOD_NAME = "testSetOptionsCommonRequest_C0001()";
        log.entering(STR_METHOD_NAME);
        
        WEB3ToSuccOptionChangeOpenContractServiceImpl l_toSuccOptionChangeOpenContractServiceImpl =
            new WEB3ToSuccOptionChangeOpenContractServiceImpl();
        
        WEB3OptionsCommonRequest l_inputCommonRequest = new WEB3OptionsCommonRequest();

        //注文数量
        l_inputCommonRequest.opOrderQuantity = "1";

        // 注文単価区分
        l_inputCommonRequest.orderPriceDiv = "2";

        // 注文単価
        l_inputCommonRequest.limitPrice = "3";

        // 執行条件
        l_inputCommonRequest.execCondType = "4";

        // 注文期限区分
        l_inputCommonRequest.expirationDateType = "5";

        // 注文有効期限
        l_inputCommonRequest.expirationDate = WEB3DateUtility.getDate("20040710","yyyyMMdd");

        // 発注条件区分
        l_inputCommonRequest.orderCondType = "7";

        // 逆指値用プレミアム／原資産価格
        l_inputCommonRequest.stopPremium_underlyingAssets = "8";

        // 逆指値用発注条件単価
        l_inputCommonRequest.stopOrderCondPrice = "9";

        // 逆指値用発注条件演算子
        l_inputCommonRequest.stopOrderCondOperator = "10";

        // W指値用プレミアム／原資産価格
        l_inputCommonRequest.wlimitPremium_underlyingAssets = "11";

        // W指値用発注条件単価
        l_inputCommonRequest.wlimitOrderCondPrice = "12";

        // W指値用発注条件演算子
        l_inputCommonRequest.wlimitOrderCondOperator = "13";

        // W指値用注文単価区分
        l_inputCommonRequest.wLimitOrderPriceDiv = "14";

        // W指値用注文単価
        l_inputCommonRequest.wLimitPrice = "15";

        // W指値用執行条件
        l_inputCommonRequest.wlimitExecCondType = "16";

        // W指値用有効状態区分
        l_inputCommonRequest.wlimitEnableStatusDiv = "17";
        
        WEB3OptionsCommonRequest l_outputCommonRequest =
            new WEB3OptionsCommonRequest();
        try
        {
            WEB3OptionsCommonRequest l_outputCommonRequestResults =
                l_toSuccOptionChangeOpenContractServiceImpl.setOptionsCommonRequest(l_outputCommonRequest, l_inputCommonRequest);
            assertEquals("1", l_outputCommonRequestResults.opOrderQuantity);
            assertEquals("2", l_outputCommonRequestResults.orderPriceDiv);
            assertEquals("3", l_outputCommonRequestResults.limitPrice);
            assertEquals("4", l_outputCommonRequestResults.execCondType);
            assertEquals("5", l_outputCommonRequestResults.expirationDateType);
            Date l_datExcept = WEB3DateUtility.getDate("20040710","yyyyMMdd");
            assertEquals(l_datExcept, l_outputCommonRequestResults.expirationDate);
            assertEquals("7", l_outputCommonRequestResults.orderCondType);
            assertEquals("8", l_outputCommonRequestResults.stopPremium_underlyingAssets);
            assertEquals("9", l_outputCommonRequestResults.stopOrderCondPrice);
            assertEquals("10", l_outputCommonRequestResults.stopOrderCondOperator);
            assertEquals("11", l_outputCommonRequestResults.wlimitPremium_underlyingAssets);
            assertEquals("12", l_outputCommonRequestResults.wlimitOrderCondPrice);
            assertEquals("13", l_outputCommonRequestResults.wlimitOrderCondOperator);
            assertEquals("14", l_outputCommonRequestResults.wLimitOrderPriceDiv);
            assertEquals("15", l_outputCommonRequestResults.wLimitPrice);
            assertEquals("16", l_outputCommonRequestResults.wlimitExecCondType);
            assertEquals("17", l_outputCommonRequestResults.wlimitEnableStatusDiv);
            log.exiting(TEST_END + STR_METHOD_NAME);
        }
        catch(Exception l_ex)
        {
            log.error(l_ex.getMessage(), l_ex);
            log.exiting(TEST_END + STR_METHOD_NAME);
            fail();
        }
    }
}
@
