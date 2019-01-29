head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.04.07.01.21.38;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	5444d9d078c63f4;
filename	WEB3ToSuccFuturesOpenContractServiceImplTest.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : （連続）先物新規建注文サービス注文Impl(WEB3ToSuccFuturesOpenContractServiceImplTest.java)
Author Name      : Daiwa Institute of Research
Revision History : 2008/03/28 孟亞南(中訊) 仕様変更モデル294
*/
package webbroker3.triggerorder.service.delegate.stdimpls;

import java.util.Calendar;
import java.util.Date;

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
import com.fitechlabs.xtrade.plugin.tc.gentrade.SubAccount;
import com.fitechlabs.xtrade.plugin.tc.gentrade.SubAccountTypeEnum;
import com.fitechlabs.xtrade.plugin.tc.gentrade.TaxTypeEnum;
import com.fitechlabs.xtrade.plugin.tc.gentrade.Trader;
import com.fitechlabs.xtrade.plugin.tc.gentrade.data.BranchParams;
import com.fitechlabs.xtrade.plugin.tc.gentrade.data.InstitutionParams;
import com.fitechlabs.xtrade.plugin.tc.gentrade.data.MainAccountParams;
import com.fitechlabs.xtrade.plugin.tc.gentrade.data.MarketParams;
import com.fitechlabs.xtrade.plugin.tc.gentrade.data.ProductParams;
import com.fitechlabs.xtrade.plugin.tc.gentrade.data.SubAccountParams;
import com.fitechlabs.xtrade.plugin.tc.gentrade.data.TradedProductParams;
import com.fitechlabs.xtrade.plugin.tc.gentrade.data.TraderParams;
import com.fitechlabs.xtrade.plugin.tc.gentrade.ordersubmitter.io.NewOrderValidationResult;
import com.fitechlabs.xtrade.plugin.tc.gentrade.stdimpls.TraderImpl;
import com.fitechlabs.xtrade.plugin.tc.xbifo.IfoDerivativeTypeEnum;
import com.fitechlabs.xtrade.plugin.tc.xbifo.IfoOrderExecutionConditionType;
import com.fitechlabs.xtrade.plugin.tc.xbifo.IfoOrderUnit;
import com.fitechlabs.xtrade.plugin.tc.xbifo.data.IfoOrderUnitParams;
import com.fitechlabs.xtrade.plugin.tc.xbifo.data.IfoProductParams;
import com.fitechlabs.xtrade.plugin.tc.xbifo.data.IfoTradedProductParams;
import com.fitechlabs.xtrade.plugin.tc.xbifo.stdimpls.IfoContractSettleOrderUnitImpl;

import test.util.TestDBUtility;

import webbroker3.common.WEB3BaseException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.gentrade.WEB3GentradeCommission;
import webbroker3.gentrade.WEB3GentradeSubAccount;
import webbroker3.gentrade.WEB3GentradeTradingTimeManagementForMock;
import webbroker3.gentrade.data.TradingTimeParams;
import webbroker3.ifo.WEB3IfoEstimateDeliveryAmountCalcResult;
import webbroker3.ifo.WEB3IfoOpenContractOrderSpec;
import webbroker3.ifo.WEB3IfoProductImpl;
import webbroker3.ifo.WEB3IfoTradedProductImpl;
import webbroker3.mock.TestBaseForMock;
import webbroker3.mock.util.WEB3MockObjectParamsValue;
import webbroker3.triggerorder.base.data.RsvIfoOrderUnitParams;
import webbroker3.triggerorder.message.WEB3SuccCommonInfo;
import webbroker3.triggerorder.message.WEB3SuccFuturesCancelConfirmRequest;
import webbroker3.triggerorder.message.WEB3SuccFuturesOpenCompleteRequest;
import webbroker3.triggerorder.message.WEB3SuccFuturesOpenCompleteResponse;
import webbroker3.triggerorder.message.WEB3SuccFuturesOpenConfirmRequest;
import webbroker3.triggerorder.message.WEB3SuccFuturesOpenConfirmResponse;
import webbroker3.triggerorder.message.WEB3SuccPriceAdjustmentValueInfo;
import webbroker3.triggerorder.service.delegate.WEB3ToSuccFuturesOpenContractRequestAdapter;
import webbroker3.triggerorder.util.WEB3ToSuccOrderManagerUtility;
import webbroker3.util.WEB3DateUtility;
import webbroker3.util.WEB3LogUtility;

public class WEB3ToSuccFuturesOpenContractServiceImplTest extends TestBaseForMock
{
    /**
     * (ログ出力ユーティリティ。)<BR>
     */
    private static WEB3LogUtility log = WEB3LogUtility.getInstance(WEB3ToSuccFuturesOpenContractServiceImplTest.class);

    WEB3ToSuccFuturesOpenContractServiceImpl l_service = null;
    
    public WEB3ToSuccFuturesOpenContractServiceImplTest(String name)
    {
        super(name);
    }

    protected void setUp() throws Exception
    {
        l_service = new WEB3ToSuccFuturesOpenContractServiceImpl();
        super.setUp();
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
        
        WEB3ToSuccOrderManagerUtility.changeOrderManager();
    }

    protected void tearDown() throws Exception
    {
        l_service = null;
        super.tearDown();
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
    }

    /**
     * （連続）先物新規建注文確認リクエスト
     * WEB3SuccFuturesOpenConfirmRequest
     * this.validate注文()
     */
    public void test_execute_0001()
    {
        final String STR_METHOD_NAME = "test_execute_0001()";
        log.entering(STR_METHOD_NAME);

        WEB3SuccFuturesOpenConfirmRequest l_request = new WEB3SuccFuturesOpenConfirmRequest();

        try
        {
            l_service.execute(l_request);
        }
        catch (WEB3BaseException e)
        {
            log.exiting(STR_METHOD_NAME);
            assertEquals(WEB3ErrorCatalog.BUSINESS_ERROR_00184, e.getErrorInfo());
        }
        catch (Exception e)
        {
            log.exiting(STR_METHOD_NAME);
            fail();
        }

        log.exiting(STR_METHOD_NAME);
    }
    
    /**
     * （連続）先物新規建注文完了リクエスト
     * WEB3SuccFuturesOpenCompleteRequest
     * this.submit注文()
     */
    public void test_execute_0002()
    {
        final String STR_METHOD_NAME = "test_execute_0002()";
        log.entering(STR_METHOD_NAME);

        WEB3SuccFuturesOpenCompleteRequest l_request = new WEB3SuccFuturesOpenCompleteRequest();

        try
        {
            l_service.execute(l_request);
        }
        catch (WEB3BaseException e)
        {
            log.exiting(STR_METHOD_NAME);
            assertEquals(WEB3ErrorCatalog.BUSINESS_ERROR_00184, e.getErrorInfo());
        }
        catch (Exception e)
        {
            log.exiting(STR_METHOD_NAME);
            fail();
        }

        log.exiting(STR_METHOD_NAME);
    }
    
    /**
     * リクエスト = null
     */
    public void test_execute_0003()
    {
        final String STR_METHOD_NAME = "test_execute_0003()";
        log.entering(STR_METHOD_NAME);

        WEB3SuccFuturesOpenCompleteRequest l_request = null;

        try
        {
            l_service.execute(l_request);
        }
        catch (WEB3BaseException e)
        {
            log.exiting(STR_METHOD_NAME);
            assertEquals(WEB3ErrorCatalog.SYSTEM_ERROR_80017, e.getErrorInfo());
        }
        catch (Exception e)
        {
            log.exiting(STR_METHOD_NAME);
            fail();
        }

        log.exiting(STR_METHOD_NAME);
    }

    /**
     * 
     */
    public void test_execute_0004()
    {
        final String STR_METHOD_NAME = "test_execute_0004()";
        log.entering(STR_METHOD_NAME);

        WEB3SuccFuturesCancelConfirmRequest l_request = new WEB3SuccFuturesCancelConfirmRequest();

        try
        {
            l_service.execute(l_request);
        }
        catch (WEB3BaseException e)
        {
            log.exiting(STR_METHOD_NAME);
            assertEquals(WEB3ErrorCatalog.SYSTEM_ERROR_80018, e.getErrorInfo());
        }
        catch (Exception e)
        {
            log.exiting(STR_METHOD_NAME);
            fail();
        }

        log.exiting(STR_METHOD_NAME);
    }
    
    /**
     * set単価
     * リクエストデータ.単価調整値情報 ≠ null（±指値指定）の場合
     * レスポンス.調整後単価に、引数のリクエストアダプタ．get単価()の戻り値をセットする。  
     */
    public void test_setPrice_0001()
    {
        final String STR_METHOD_NAME = "test_setPrice_0001()";
        log.entering(STR_METHOD_NAME);
        
        WEB3SuccFuturesOpenConfirmRequest l_request = new WEB3SuccFuturesOpenConfirmRequest();
        WEB3SuccPriceAdjustmentValueInfo l_priceAdjustmentValueInfo = new WEB3SuccPriceAdjustmentValueInfo();
        l_priceAdjustmentValueInfo.setPriceAdjustmentValue(23.6);
        l_request.priceAdjustmentValueInfo = l_priceAdjustmentValueInfo;
        
        WEB3SuccFuturesOpenConfirmResponse l_response = new WEB3SuccFuturesOpenConfirmResponse(l_request);
        WEB3ToSuccFuturesOpenContractRequestAdapter l_adapter = new WEB3ToSuccFuturesOpenContractRequestAdapter();
        l_adapter.request = l_request;
        l_adapter.price = 1;
        try
        {
            l_service.setPrice(l_adapter, l_response);
            assertEquals("1", "" + l_response.afterAdjustmentPrice);
        }
        catch (WEB3BaseException e)
        {
            log.exiting(STR_METHOD_NAME);
            fail();
        }
        catch (Exception e)
        {
            log.exiting(STR_METHOD_NAME);
            fail();
        }
        log.exiting(STR_METHOD_NAME);
    }
    
    /**
     * set単価
     * リクエストデータ.単価調整値情報 = nullの場合
     * 　@ returnをする。
     */
    public void test_setPrice_0002()
    {
        final String STR_METHOD_NAME = "test_setPrice_0002()";
        log.entering(STR_METHOD_NAME);
        
        WEB3SuccFuturesOpenConfirmRequest l_request = new WEB3SuccFuturesOpenConfirmRequest();
        l_request.priceAdjustmentValueInfo = null;
        
        WEB3SuccFuturesOpenConfirmResponse l_response = new WEB3SuccFuturesOpenConfirmResponse(l_request);
        WEB3ToSuccFuturesOpenContractRequestAdapter l_adapter = new WEB3ToSuccFuturesOpenContractRequestAdapter();
        l_adapter.request = l_request;
        try
        {
            l_service.setPrice(l_adapter, l_response);
            assertNull(l_response.afterAdjustmentPrice);
        }
        catch (WEB3BaseException e)
        {
            log.exiting(STR_METHOD_NAME);
            fail();
        }
        catch (Exception e)
        {
            log.exiting(STR_METHOD_NAME);
            fail();
        }
        log.exiting(STR_METHOD_NAME);
    }
    
    /**
     * createリクエストアダプタ
     */
    public void test_createRequestAdapter_0001()
    {
        final String STR_METHOD_NAME = "test_createRequestAdapter_0001()";
        log.entering(STR_METHOD_NAME);
        WEB3SuccFuturesOpenConfirmRequest l_request = new WEB3SuccFuturesOpenConfirmRequest();
        WEB3SuccCommonInfo l_succCommonInfo = new WEB3SuccCommonInfo();
        l_succCommonInfo.parentOrderId = "3304148080000";
        l_request.contractType = "1";
        l_request.succCommonInfo = l_succCommonInfo;
        try
        {
            IfoOrderUnitParams l_ifoOrderUnitParams = TestDBUtility.getIfoOrderUnitRow();
            l_ifoOrderUnitParams.setProductId(3304148080000L);
            l_ifoOrderUnitParams.setOrderId(56);
            l_ifoOrderUnitParams.setMarketId(3303L);
            TestDBUtility.insertWithDel(l_ifoOrderUnitParams);
            
            WEB3ToSuccFuturesOpenContractRequestAdapter l_adapter =
                (WEB3ToSuccFuturesOpenContractRequestAdapter)l_service.createRequestAdapter(l_request);
            assertEquals("1", ((WEB3SuccFuturesOpenConfirmRequest)l_adapter.request).contractType);
        }
        catch (WEB3BaseException e)
        {
            log.exiting(STR_METHOD_NAME);
            fail();
        }
        catch (Exception e)
        {
            log.exiting(STR_METHOD_NAME);
            fail();
        }
        log.exiting(STR_METHOD_NAME);
    }
    
    /**
     * notify予約注文登録
     */
    public void test_notifyRsvOrderRegister_0001()
    {
        final String STR_METHOD_NAME = "test_notifyRsvOrderRegister_0001()";
        log.entering(STR_METHOD_NAME);
        MOCK_MANAGER.setIsMockUsed(true);
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
            
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue("webbroker3.ifo.WEB3FuturesOrderManagerImpl",
                    "notifyRLS", new Class[]
                    { IfoOrderUnit.class, OrderManagerPersistenceContext.class },
                    null);
            
            l_service.notifyRsvOrderRegister(21L);
            
            WEB3MockObjectParamsValue l_paramsValue1 =
                TestBaseForMock.MOCK_MANAGER.getMethodParamsValue(
                    "webbroker3.ifo.WEB3FuturesOrderManagerImpl",
                    "notifyRLS",
                    new Class[]{IfoOrderUnit.class, OrderManagerPersistenceContext.class});

           assertEquals("11", "" + ((IfoOrderUnit)l_paramsValue1.getCalled(0)[0]).getAccountId());
           assertEquals("1", "" + ((OrderManagerPersistenceContext)l_paramsValue1.getCalled(0)[1]).getIntegerValue());
        }
        catch (WEB3BaseException e)
        {
            log.exiting(STR_METHOD_NAME);
            fail();
        }
        catch (Exception e)
        {
            log.exiting(STR_METHOD_NAME);
            fail();
        }
        
        log.exiting(STR_METHOD_NAME);
    }
    
    /**
     * submit新規建注文
     * リクエスト.単価調整値情報 == nullの場合
     */
    public void test_submitOpenContractOrder_0001()
    {
        final String STR_METHOD_NAME = "test_submitOpenContractOrder_0001()";
        log.entering(STR_METHOD_NAME);
        
        WEB3SuccFuturesOpenCompleteRequest l_request = new WEB3SuccFuturesOpenCompleteRequest();
        WEB3SuccCommonInfo l_succCommonInfo = new WEB3SuccCommonInfo();
        l_succCommonInfo.succTradingType = "1";
        l_request.succCommonInfo = l_succCommonInfo;
        l_request.priceAdjustmentValueInfo = null;
        
//        WEB3SuccPriceAdjustmentValueInfo l_priceAdjustmentValueInfo = new WEB3SuccPriceAdjustmentValueInfo();
//        l_priceAdjustmentValueInfo.setPriceAdjustmentValue(23.6);
//        l_request.priceAdjustmentValueInfo = l_priceAdjustmentValueInfo;
        

        
        MOCK_MANAGER.setIsMockUsed(true);
        try
        {
            WEB3GentradeTradingTimeManagementForMock.mockGetOrderBizDate(WEB3DateUtility.getDate("20080808", "yyyyMMdd"));
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue("webbroker3.triggerorder.WEB3ToSuccOrderManagerImpl",
                    "submitIfoOpenContractNewOrder", new Class[]
                    {SubAccount.class, WEB3IfoOpenContractOrderSpec.class, long.class, String.class, String.class, Double.class, IfoOrderUnit.class, WEB3IfoEstimateDeliveryAmountCalcResult.class},
                    null);
            
            TraderParams l_traderParam = TestDBUtility.getTraderRow();
            l_traderParam.setTraderId(3338111123L);
            
            TestDBUtility.insertWithDel(l_traderParam);
            
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
            
            SubAccountParams l_subAccountParams = TestDBUtility.getSubAccountRow();
            l_subAccountParams.setAccountId(333812512246L);
            l_subAccountParams.setSubAccountType(SubAccountTypeEnum.EQUITY_OPTIONS_SUB_ACCOUNT);
//            TestDBUtility.insertWithDel(l_subAccountParams);
            WEB3GentradeSubAccount l_subAccount = new WEB3GentradeSubAccount(l_subAccountParams);
            
            WEB3IfoProductImpl l_ifoProduct = new WEB3IfoProductImpl(3304148080000L);
            Trader l_trader = new TraderImpl(3338111123L, false);
            WEB3IfoOpenContractOrderSpec l_spec =
                WEB3IfoOpenContractOrderSpec.createOpenContractOrderSpec(
                    "0D",
                    l_trader,
                    false,
                    "12d",
                    l_ifoProduct,
                    8D,
                    9D,
                    IfoOrderExecutionConditionType.AT_MARKET_CLOSE,
                    new Date(),
                    "2",
                    98D,
                    67D,
                    IfoOrderExecutionConditionType.AT_MARKET_CLOSE,
                    "21sad",
                    new Long("23"),
                    false);
            
            
            WEB3IfoEstimateDeliveryAmountCalcResult l_estimateDeliveryAmountCalcResult = new WEB3IfoEstimateDeliveryAmountCalcResult();
            l_estimateDeliveryAmountCalcResult.setCalcUnitPrice(589);
            
            IfoContractSettleOrderUnitImpl l_ifoOrderUnit= new IfoContractSettleOrderUnitImpl(989);
            WEB3ToSuccFuturesOpenContractRequestAdapter l_adapter = new WEB3ToSuccFuturesOpenContractRequestAdapter();
            l_adapter.request = l_request;
            l_adapter.price = 1;
            l_adapter.parentOrderUnit = l_ifoOrderUnit;
            
            l_service.submitOpenContractOrder(l_subAccount, l_spec, 34L, "23", l_adapter, l_estimateDeliveryAmountCalcResult);
            WEB3MockObjectParamsValue l_paramsValue1 =
                TestBaseForMock.MOCK_MANAGER.getMethodParamsValue(
                    "webbroker3.triggerorder.WEB3ToSuccOrderManagerImpl",
                    "submitIfoOpenContractNewOrder",
                    new Class[]{SubAccount.class, WEB3IfoOpenContractOrderSpec.class, long.class, String.class, String.class, Double.class, IfoOrderUnit.class, WEB3IfoEstimateDeliveryAmountCalcResult.class});

           assertEquals("333812512246", "" + ((SubAccount)l_paramsValue1.getCalled(0)[0]).getAccountId());
           assertEquals("0D", "" + ((WEB3IfoOpenContractOrderSpec)l_paramsValue1.getCalled(0)[1]).getInstitutionCode());
           assertEquals("34", "" + ((Long)l_paramsValue1.getCalled(0)[2]).longValue());
           assertEquals("23", "" + ((String)l_paramsValue1.getCalled(0)[3]));
           assertEquals("1", "" + ((String)l_paramsValue1.getCalled(0)[4]));
           assertNull((l_paramsValue1.getCalled(0)[5]));
           assertEquals("56", "" + ((IfoOrderUnit)l_paramsValue1.getCalled(0)[6]).getOrderId());
           assertEquals("589.0", "" + ((WEB3IfoEstimateDeliveryAmountCalcResult)l_paramsValue1.getCalled(0)[7]).getCalcUnitPrice());
        
        }
        catch (WEB3BaseException e)
        {
            log.exiting(STR_METHOD_NAME);
            fail();
        }
        catch (Exception e)
        {
            log.exiting(STR_METHOD_NAME);
            
            fail();
        }
        
        log.exiting(STR_METHOD_NAME);
    }
    
    /**
     * submit新規建注文
     * リクエスト.単価調整値情報 != nullの場合
     */
    public void test_submitOpenContractOrder_0002()
    {
        final String STR_METHOD_NAME = "test_submitOpenContractOrder_0002()";
        log.entering(STR_METHOD_NAME);
        
        WEB3SuccFuturesOpenCompleteRequest l_request = new WEB3SuccFuturesOpenCompleteRequest();
        WEB3SuccCommonInfo l_succCommonInfo = new WEB3SuccCommonInfo();
        l_succCommonInfo.succTradingType = "1";
        l_request.succCommonInfo = l_succCommonInfo;
//        l_request.priceAdjustmentValueInfo = null;
        
        WEB3SuccPriceAdjustmentValueInfo l_priceAdjustmentValueInfo = new WEB3SuccPriceAdjustmentValueInfo();
        l_priceAdjustmentValueInfo.setPriceAdjustmentValue(23.6);
        l_request.priceAdjustmentValueInfo = l_priceAdjustmentValueInfo;
        

        
        MOCK_MANAGER.setIsMockUsed(true);
        try
        {
            WEB3GentradeTradingTimeManagementForMock.mockGetOrderBizDate(WEB3DateUtility.getDate("20080808", "yyyyMMdd"));
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue("webbroker3.triggerorder.WEB3ToSuccOrderManagerImpl",
                    "submitIfoOpenContractNewOrder", new Class[]
                    {SubAccount.class, WEB3IfoOpenContractOrderSpec.class, long.class, String.class, String.class, Double.class, IfoOrderUnit.class, WEB3IfoEstimateDeliveryAmountCalcResult.class},
                    null);
            
            TraderParams l_traderParam = TestDBUtility.getTraderRow();
            l_traderParam.setTraderId(3338111123L);
            
            TestDBUtility.insertWithDel(l_traderParam);
            
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
            
            SubAccountParams l_subAccountParams = TestDBUtility.getSubAccountRow();
            l_subAccountParams.setAccountId(333812512246L);
            l_subAccountParams.setSubAccountType(SubAccountTypeEnum.EQUITY_OPTIONS_SUB_ACCOUNT);
//            TestDBUtility.insertWithDel(l_subAccountParams);
            WEB3GentradeSubAccount l_subAccount = new WEB3GentradeSubAccount(l_subAccountParams);
            
            WEB3IfoProductImpl l_ifoProduct = new WEB3IfoProductImpl(3304148080000L);
            Trader l_trader = new TraderImpl(3338111123L, false);
            WEB3IfoOpenContractOrderSpec l_spec =
                WEB3IfoOpenContractOrderSpec.createOpenContractOrderSpec(
                    "0D",
                    l_trader,
                    false,
                    "12d",
                    l_ifoProduct,
                    8D,
                    9D,
                    IfoOrderExecutionConditionType.AT_MARKET_CLOSE,
                    new Date(),
                    "2",
                    98D,
                    67D,
                    IfoOrderExecutionConditionType.AT_MARKET_CLOSE,
                    "21sad",
                    new Long("23"),
                    false);
            
            
            WEB3IfoEstimateDeliveryAmountCalcResult l_estimateDeliveryAmountCalcResult = new WEB3IfoEstimateDeliveryAmountCalcResult();
            l_estimateDeliveryAmountCalcResult.setCalcUnitPrice(589);
            
            IfoContractSettleOrderUnitImpl l_ifoOrderUnit= new IfoContractSettleOrderUnitImpl(989);
            WEB3ToSuccFuturesOpenContractRequestAdapter l_adapter = new WEB3ToSuccFuturesOpenContractRequestAdapter();
            l_adapter.request = l_request;
            l_adapter.price = 1;
            l_adapter.parentOrderUnit = l_ifoOrderUnit;
            
            l_service.submitOpenContractOrder(l_subAccount, l_spec, 34L, "23", l_adapter, l_estimateDeliveryAmountCalcResult);
            WEB3MockObjectParamsValue l_paramsValue1 =
                TestBaseForMock.MOCK_MANAGER.getMethodParamsValue(
                    "webbroker3.triggerorder.WEB3ToSuccOrderManagerImpl",
                    "submitIfoOpenContractNewOrder",
                    new Class[]{SubAccount.class, WEB3IfoOpenContractOrderSpec.class, long.class, String.class, String.class, Double.class, IfoOrderUnit.class, WEB3IfoEstimateDeliveryAmountCalcResult.class});

           assertEquals("333812512246", "" + ((SubAccount)l_paramsValue1.getCalled(0)[0]).getAccountId());
           assertEquals("0D", "" + ((WEB3IfoOpenContractOrderSpec)l_paramsValue1.getCalled(0)[1]).getInstitutionCode());
           assertEquals("34", "" + ((Long)l_paramsValue1.getCalled(0)[2]).longValue());
           assertEquals("23", "" + ((String)l_paramsValue1.getCalled(0)[3]));
           assertEquals("1", "" + ((String)l_paramsValue1.getCalled(0)[4]));
           assertEquals("23.6", "" + ((Double)l_paramsValue1.getCalled(0)[5]).doubleValue());
           assertEquals("56", "" + ((IfoOrderUnit)l_paramsValue1.getCalled(0)[6]).getOrderId());
           assertEquals("589.0", "" + ((WEB3IfoEstimateDeliveryAmountCalcResult)l_paramsValue1.getCalled(0)[7]).getCalcUnitPrice());
        
        }
        catch (WEB3BaseException e)
        {
            log.exiting(STR_METHOD_NAME);
            fail();
        }
        catch (Exception e)
        {
            log.exiting(STR_METHOD_NAME);
            
            fail();
        }
        
        log.exiting(STR_METHOD_NAME);
    }
    
    /**
     * validateリクエストデータat反対取引 
     * （連続）先物新規建注文確認リクエスト
     * WEB3SuccFuturesOpenConfirmRequest
     * 連続注文マネージャImpl.is反対売買取引() == false（＝反対取引でない）の場合、returnする。 
     */
    public void test_validateRequestDataAtReversingTrade_0001()
    {
        final String STR_METHOD_NAME = "test_validateRequestDataAtReversingTrade_0001()";
        log.entering(STR_METHOD_NAME);

        MOCK_MANAGER.setIsMockUsed(true);
        
        try
        {
            IfoOrderUnitParams l_ifoOrderUnitParams = TestDBUtility.getIfoOrderUnitRow();
            l_ifoOrderUnitParams.setProductId(3304148080000L);
            l_ifoOrderUnitParams.setOrderId(56);
            l_ifoOrderUnitParams.setOrderUnitId(989);
            l_ifoOrderUnitParams.setMarketId(3303L);
            TestDBUtility.insertWithDel(l_ifoOrderUnitParams);
            
            WEB3SuccFuturesOpenConfirmRequest l_request = new WEB3SuccFuturesOpenConfirmRequest();
            WEB3SuccCommonInfo l_succCommonInfo = new WEB3SuccCommonInfo();
            l_succCommonInfo.succTradingType = "1";
            l_request.succCommonInfo = l_succCommonInfo;
//            l_request.priceAdjustmentValueInfo = null;
            IfoContractSettleOrderUnitImpl l_ifoOrderUnit= new IfoContractSettleOrderUnitImpl(989);
            WEB3SuccPriceAdjustmentValueInfo l_priceAdjustmentValueInfo = new WEB3SuccPriceAdjustmentValueInfo();
            l_priceAdjustmentValueInfo.setPriceAdjustmentValue(23.6);
            l_request.priceAdjustmentValueInfo = l_priceAdjustmentValueInfo;
            
            
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                    "webbroker3.triggerorder.WEB3ToSuccOrderManagerImpl", "isReversingTrade", new Class[]
                    {String.class, OrderUnit.class},
                    Boolean.FALSE);
            
            
            l_service.validateRequestDataAtReversingTrade(l_request, l_ifoOrderUnit);
            
            WEB3MockObjectParamsValue l_paramsValue2 =
                TestBaseForMock.MOCK_MANAGER.getMethodParamsValue(
                    "webbroker3.triggerorder.WEB3ToSuccOrderManagerImpl",
                    "isReversingTrade",
                    new Class[]{ String.class, OrderUnit.class });

           assertEquals("1", "" + ((String)l_paramsValue2.getCalled(0)[0]));
           assertEquals("989", "" + ((OrderUnit)l_paramsValue2.getCalled(0)[1]).getOrderUnitId());
        }
        catch (WEB3BaseException e)
        {
            log.exiting(STR_METHOD_NAME);
            fail();
        }
        catch (Exception e)
        {
            log.exiting(STR_METHOD_NAME);
            
            fail();
        }
        log.exiting(STR_METHOD_NAME);
    }
    
    /**
     * validateリクエストデータat反対取引 
     * （連続）先物新規建注文完了リクエスト
     * WEB3SuccFuturesOpenCompleteRequest
     * 連続注文マネージャImpl.is反対売買取引() == false（＝反対取引でない）の場合、returnする。 
     */
    public void test_validateRequestDataAtReversingTrade_0002()
    {
        final String STR_METHOD_NAME = "test_validateRequestDataAtReversingTrade_0002()";
        log.entering(STR_METHOD_NAME);

        MOCK_MANAGER.setIsMockUsed(true);
        
        try
        {
            IfoOrderUnitParams l_ifoOrderUnitParams = TestDBUtility.getIfoOrderUnitRow();
            l_ifoOrderUnitParams.setProductId(3304148080000L);
            l_ifoOrderUnitParams.setOrderId(56);
            l_ifoOrderUnitParams.setOrderUnitId(989);
            l_ifoOrderUnitParams.setMarketId(3303L);
            TestDBUtility.insertWithDel(l_ifoOrderUnitParams);
            
            WEB3SuccFuturesOpenCompleteRequest l_request = new WEB3SuccFuturesOpenCompleteRequest();
            WEB3SuccCommonInfo l_succCommonInfo = new WEB3SuccCommonInfo();
            l_succCommonInfo.succTradingType = "1";
            l_request.succCommonInfo = l_succCommonInfo;
//            l_request.priceAdjustmentValueInfo = null;
            IfoContractSettleOrderUnitImpl l_ifoOrderUnit= new IfoContractSettleOrderUnitImpl(989);
            WEB3SuccPriceAdjustmentValueInfo l_priceAdjustmentValueInfo = new WEB3SuccPriceAdjustmentValueInfo();
            l_priceAdjustmentValueInfo.setPriceAdjustmentValue(23.6);
            l_request.priceAdjustmentValueInfo = l_priceAdjustmentValueInfo;
            
            
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                    "webbroker3.triggerorder.WEB3ToSuccOrderManagerImpl", "isReversingTrade", new Class[]
                    {String.class, OrderUnit.class},
                    Boolean.FALSE);
            
            
            l_service.validateRequestDataAtReversingTrade(l_request, l_ifoOrderUnit);
            
            WEB3MockObjectParamsValue l_paramsValue2 =
                TestBaseForMock.MOCK_MANAGER.getMethodParamsValue(
                    "webbroker3.triggerorder.WEB3ToSuccOrderManagerImpl",
                    "isReversingTrade",
                    new Class[]{ String.class, OrderUnit.class });

           assertEquals("1", "" + ((String)l_paramsValue2.getCalled(0)[0]));
           assertEquals("989", "" + ((OrderUnit)l_paramsValue2.getCalled(0)[1]).getOrderUnitId());
        }
        catch (WEB3BaseException e)
        {
            log.exiting(STR_METHOD_NAME);
            fail();
        }
        catch (Exception e)
        {
            log.exiting(STR_METHOD_NAME);
            
            fail();
        }
        log.exiting(STR_METHOD_NAME);
    }
    
    
    /**
     * validateリクエストデータat反対取引 
     * （連続）先物新規建注文確認リクエスト
     * WEB3SuccFuturesOpenConfirmRequest
     * 連続注文マネージャImpl.is反対売買取引() != false（＝反対取引でない）の場合
     * 引数.リクエストデータ.銘柄コード ≠ 引数.親注文の注文単位.銘柄IDに該当する銘柄コード 
     * 「反対取引時の銘柄指定が、親注文と不整合」の例外をthrowする。 
     */
    public void test_validateRequestDataAtReversingTrade_0003()
    {
        final String STR_METHOD_NAME = "test_validateRequestDataAtReversingTrade_0003()";
        log.entering(STR_METHOD_NAME);

        MOCK_MANAGER.setIsMockUsed(true);
        
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
            l_ifoProductParams.setProductCode("160030005");
            //原資産銘柄コード
            l_ifoProductParams.setUnderlyingProductCode("0019");
            //限月
            l_ifoProductParams.setMonthOfDelivery("200808");
            TestDBUtility.insertWithDel(l_ifoProductParams);
            
            WEB3SuccFuturesOpenConfirmRequest l_request = new WEB3SuccFuturesOpenConfirmRequest();
            WEB3SuccCommonInfo l_succCommonInfo = new WEB3SuccCommonInfo();
            l_succCommonInfo.succTradingType = "1";
            l_request.succCommonInfo = l_succCommonInfo;
            l_request.futProductCode = "111111";
//            l_request.priceAdjustmentValueInfo = null;
            IfoContractSettleOrderUnitImpl l_ifoOrderUnit= new IfoContractSettleOrderUnitImpl(989);
            WEB3SuccPriceAdjustmentValueInfo l_priceAdjustmentValueInfo = new WEB3SuccPriceAdjustmentValueInfo();
            l_priceAdjustmentValueInfo.setPriceAdjustmentValue(23.6);
            l_request.priceAdjustmentValueInfo = l_priceAdjustmentValueInfo;
            
            
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                    "webbroker3.triggerorder.WEB3ToSuccOrderManagerImpl", "isReversingTrade", new Class[]
                    {String.class, OrderUnit.class},
                    Boolean.TRUE);
            
            
            l_service.validateRequestDataAtReversingTrade(l_request, l_ifoOrderUnit);
            fail();
        }
        catch (WEB3BaseException e)
        {
            log.exiting(STR_METHOD_NAME);
            assertEquals(WEB3ErrorCatalog.BUSINESS_ERROR_02250, e.getErrorInfo());
        }
        catch (Exception e)
        {
            log.exiting(STR_METHOD_NAME);
            
            fail();
        }
        log.exiting(STR_METHOD_NAME);
    }
    
    /**
     * validateリクエストデータat反対取引 
     * （連続）先物新規建注文完了リクエスト
     * WEB3SuccFuturesOpenCompleteRequest
     * 連続注文マネージャImpl.is反対売買取引() != false（＝反対取引でない）の場合
     * 引数.リクエストデータ.銘柄コード ≠ 引数.親注文の注文単位.銘柄IDに該当する銘柄コード 
     * 「反対取引時の銘柄指定が、親注文と不整合」の例外をthrowする。 
     */
    public void test_validateRequestDataAtReversingTrade_0004()
    {
        final String STR_METHOD_NAME = "test_validateRequestDataAtReversingTrade_0004()";
        log.entering(STR_METHOD_NAME);

        MOCK_MANAGER.setIsMockUsed(true);
        
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
            l_ifoProductParams.setProductCode("160030005");
            //原資産銘柄コード
            l_ifoProductParams.setUnderlyingProductCode("0019");
            //限月
            l_ifoProductParams.setMonthOfDelivery("200808");
            TestDBUtility.insertWithDel(l_ifoProductParams);
            
            WEB3SuccFuturesOpenCompleteRequest l_request = new WEB3SuccFuturesOpenCompleteRequest();
            WEB3SuccCommonInfo l_succCommonInfo = new WEB3SuccCommonInfo();
            l_succCommonInfo.succTradingType = "1";
            l_request.succCommonInfo = l_succCommonInfo;
            l_request.futProductCode = "111111";
//            l_request.priceAdjustmentValueInfo = null;
            IfoContractSettleOrderUnitImpl l_ifoOrderUnit= new IfoContractSettleOrderUnitImpl(989);
            WEB3SuccPriceAdjustmentValueInfo l_priceAdjustmentValueInfo = new WEB3SuccPriceAdjustmentValueInfo();
            l_priceAdjustmentValueInfo.setPriceAdjustmentValue(23.6);
            l_request.priceAdjustmentValueInfo = l_priceAdjustmentValueInfo;
            
            
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                    "webbroker3.triggerorder.WEB3ToSuccOrderManagerImpl", "isReversingTrade", new Class[]
                    {String.class, OrderUnit.class},
                    Boolean.TRUE);
            
            
            l_service.validateRequestDataAtReversingTrade(l_request, l_ifoOrderUnit);
            fail();
        }
        catch (WEB3BaseException e)
        {
            log.exiting(STR_METHOD_NAME);
            assertEquals(WEB3ErrorCatalog.BUSINESS_ERROR_02250, e.getErrorInfo());
        }
        catch (Exception e)
        {
            log.exiting(STR_METHOD_NAME);
            
            fail();
        }
        log.exiting(STR_METHOD_NAME);
    }
    
    /**
     * validateリクエストデータat反対取引 
     * （連続）先物新規建注文確認リクエスト
     * WEB3SuccFuturesOpenConfirmRequest
     * 連続注文マネージャImpl.is反対売買取引() != false（＝反対取引でない）の場合
     * 引数.リクエストデータ.銘柄コード = 引数.親注文の注文単位.銘柄IDに該当する銘柄コード 
     */
    public void test_validateRequestDataAtReversingTrade_0005()
    {
        final String STR_METHOD_NAME = "test_validateRequestDataAtReversingTrade_0005()";
        log.entering(STR_METHOD_NAME);

        MOCK_MANAGER.setIsMockUsed(true);
        
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
            l_ifoProductParams.setProductCode("160030005");
            //原資産銘柄コード
            l_ifoProductParams.setUnderlyingProductCode("0019");
            //限月
            l_ifoProductParams.setMonthOfDelivery("200808");
            TestDBUtility.insertWithDel(l_ifoProductParams);
            
            WEB3SuccFuturesOpenConfirmRequest l_request = new WEB3SuccFuturesOpenConfirmRequest();
            WEB3SuccCommonInfo l_succCommonInfo = new WEB3SuccCommonInfo();
            l_succCommonInfo.succTradingType = "1";
            l_request.succCommonInfo = l_succCommonInfo;
            l_request.futProductCode = "160030005";
//            l_request.priceAdjustmentValueInfo = null;
            IfoContractSettleOrderUnitImpl l_ifoOrderUnit= new IfoContractSettleOrderUnitImpl(989);
            WEB3SuccPriceAdjustmentValueInfo l_priceAdjustmentValueInfo = new WEB3SuccPriceAdjustmentValueInfo();
            l_priceAdjustmentValueInfo.setPriceAdjustmentValue(23.6);
            l_request.priceAdjustmentValueInfo = l_priceAdjustmentValueInfo;
            
            
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                    "webbroker3.triggerorder.WEB3ToSuccOrderManagerImpl", "isReversingTrade", new Class[]
                    {String.class, OrderUnit.class},
                    Boolean.TRUE);
            
            
            l_service.validateRequestDataAtReversingTrade(l_request, l_ifoOrderUnit);
        }
        catch (WEB3BaseException e)
        {
            log.exiting(STR_METHOD_NAME);
            fail();
        }
        catch (Exception e)
        {
            log.exiting(STR_METHOD_NAME);
            
            fail();
        }
        log.exiting(STR_METHOD_NAME);
    }
    
    /**
     * validateリクエストデータat反対取引 
     * （連続）先物新規建注文完了リクエスト
     * WEB3SuccFuturesOpenCompleteRequest
     * 連続注文マネージャImpl.is反対売買取引() != false（＝反対取引でない）の場合
     * 引数.リクエストデータ.銘柄コード = 引数.親注文の注文単位.銘柄IDに該当する銘柄コード 
     */
    public void test_validateRequestDataAtReversingTrade_0006()
    {
        final String STR_METHOD_NAME = "test_validateRequestDataAtReversingTrade_0006()";
        log.entering(STR_METHOD_NAME);

        MOCK_MANAGER.setIsMockUsed(true);
        
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
            l_ifoProductParams.setProductCode("160030007");
            //原資産銘柄コード
            l_ifoProductParams.setUnderlyingProductCode("0019");
            //限月
            l_ifoProductParams.setMonthOfDelivery("200808");
            TestDBUtility.insertWithDel(l_ifoProductParams);
            
            WEB3SuccFuturesOpenCompleteRequest l_request = new WEB3SuccFuturesOpenCompleteRequest();
            WEB3SuccCommonInfo l_succCommonInfo = new WEB3SuccCommonInfo();
            l_succCommonInfo.succTradingType = "1";
            l_request.succCommonInfo = l_succCommonInfo;
            l_request.futProductCode = "160030007";
//            l_request.priceAdjustmentValueInfo = null;
            IfoContractSettleOrderUnitImpl l_ifoOrderUnit= new IfoContractSettleOrderUnitImpl(989);
            WEB3SuccPriceAdjustmentValueInfo l_priceAdjustmentValueInfo = new WEB3SuccPriceAdjustmentValueInfo();
            l_priceAdjustmentValueInfo.setPriceAdjustmentValue(23.6);
            l_request.priceAdjustmentValueInfo = l_priceAdjustmentValueInfo;
            
            
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                    "webbroker3.triggerorder.WEB3ToSuccOrderManagerImpl", "isReversingTrade", new Class[]
                    {String.class, OrderUnit.class},
                    Boolean.TRUE);
            
            
            l_service.validateRequestDataAtReversingTrade(l_request, l_ifoOrderUnit);
        }
        catch (WEB3BaseException e)
        {
            log.exiting(STR_METHOD_NAME);
            fail();
        }
        catch (Exception e)
        {
            log.exiting(STR_METHOD_NAME);
            
            fail();
        }
        log.exiting(STR_METHOD_NAME);
    }
    
    /**
     * validate連続注文最大設定数
     */
    public void test_validateSuccOrderMaxQuantity_0001()
    {
        final String STR_METHOD_NAME = "test_validateSuccOrderMaxQuantity_0001()";
        log.entering(STR_METHOD_NAME);

        MOCK_MANAGER.setIsMockUsed(true);
        try
        {
            IfoOrderUnitParams l_ifoOrderUnitParams = TestDBUtility.getIfoOrderUnitRow();
            l_ifoOrderUnitParams.setProductId(3304148080000L);
            l_ifoOrderUnitParams.setOrderId(56);
            l_ifoOrderUnitParams.setOrderUnitId(989);
            l_ifoOrderUnitParams.setMarketId(3303L);
            TestDBUtility.insertWithDel(l_ifoOrderUnitParams);
            IfoContractSettleOrderUnitImpl l_ifoOrderUnit= new IfoContractSettleOrderUnitImpl(989);
            
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                    "webbroker3.triggerorder.WEB3ToSuccOrderManagerImpl",
                    "validateSuccOrderMaxQuantity", new Class[]
                    {OrderUnit.class},
                    null);
            
            l_service.validateSuccOrderMaxQuantity(l_ifoOrderUnit);
            
            WEB3MockObjectParamsValue l_paramsValue2 =
                TestBaseForMock.MOCK_MANAGER.getMethodParamsValue(
                    "webbroker3.triggerorder.WEB3ToSuccOrderManagerImpl",
                    "validateSuccOrderMaxQuantity",
                    new Class[]{ OrderUnit.class });

           assertEquals("989", "" + ((OrderUnit)l_paramsValue2.getCalled(0)[0]).getOrderUnitId());
        }
        catch (WEB3BaseException e)
        {
            log.exiting(STR_METHOD_NAME);
            fail();
        }
        catch (Exception e)
        {
            log.exiting(STR_METHOD_NAME);
            
            fail();
        }
        log.exiting(STR_METHOD_NAME);
    }
    
    /**
     * submit注文
     */
    public void test_submitOrder_0001()
    {
        final String STR_METHOD_NAME = "test_submitOrder_0001()";
        log.entering(STR_METHOD_NAME);

        MOCK_MANAGER.setIsMockUsed(true);
        try
        {
            WEB3SuccFuturesOpenCompleteRequest l_request = new WEB3SuccFuturesOpenCompleteRequest();
            
            l_request.orderPriceDiv = "0";
            l_request.orderId = "21";
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
            l_request.checkPrice = "12";
            l_request.checkDate = WEB3DateUtility.getDate("20080808", "yyyyMMdd");
            //super.validate()
//            l_request.futProductCode = "0001";
            l_request.contractType = "1";
            l_request.marketCode = "1";
            l_request.futOrderQuantity = "1000";
            l_request.delivaryMonth = "2";
            l_request.targetProductCode = "3";
//            l_request.orderId = null;
            //l_request.succCommonInfo != null;
            l_request.succCommonInfo = new WEB3SuccCommonInfo();
            //this.succCommonInfo.validate();
            l_request.succCommonInfo.parentOrderId = "56";
            //this.連続注文共通情報.連続注文取引区分が以下の値以外の場合
            l_request.succCommonInfo.succTradingType = "11";
            //連続注文単価調整値情報≠nullの場合
            l_request.priceAdjustmentValueInfo = new WEB3SuccPriceAdjustmentValueInfo();
            //this.priceAdjustmentValueInfo.validate();
            l_request.priceAdjustmentValueInfo.sign = "+";
            l_request.priceAdjustmentValueInfo.priceAdjustmentValue = "1000";
            l_request.afterAdjustmentPrice = "23";
            
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
            
            SubAccountParams l_subAccountParams = TestDBUtility.getSubAccountRow();
            l_subAccountParams.setAccountId(333812512246L);
            l_subAccountParams.setSubAccountType(SubAccountTypeEnum.EQUITY_OPTIONS_SUB_ACCOUNT);
            TestDBUtility.insertWithDel(l_subAccountParams);
            MainAccountParams l_mainAccountParams = TestDBUtility.getMainAccountRow();
            l_mainAccountParams.setQuotoType("1");
            TestDBUtility.insertWithDel(l_mainAccountParams);
            BranchParams l_branchParams = TestDBUtility.getBranchRow();
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
            IfoContractSettleOrderUnitImpl l_ifoOrderUnit= new IfoContractSettleOrderUnitImpl(989);
            
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
            
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue("webbroker3.ifo.WEB3FuturesOrderManagerImpl",
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
                    "validateFuturesOpenContractOrder", new Class[]
                    { WEB3GentradeSubAccount.class, WEB3IfoOpenContractOrderSpec.class },
                    l_result);
            
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                    "com.fitechlabs.xtrade.plugin.security.oplogin.impl.OpLoginSecurityServiceImpl", 
                    "getSessionProperty",
                    new Class[] {String.class},
                    "");
            
            WEB3IfoEstimateDeliveryAmountCalcResult l_calcResult = new WEB3IfoEstimateDeliveryAmountCalcResult();
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                    "webbroker3.ifo.WEB3FuturesOrderManagerImpl",
                    "calcEstimatePrice",
                    new Class[]
                    { WEB3GentradeCommission.class, double.class, WEB3GentradeSubAccount.class,
                            WEB3IfoTradedProductImpl.class, double.class, boolean.class },
                            l_calcResult);
            
            WEB3SuccFuturesOpenCompleteResponse l_response = l_service.submitOrder(l_request);
            
            WEB3MockObjectParamsValue l_paramsValue1 =
                TestBaseForMock.MOCK_MANAGER.getMethodParamsValue(
                    "webbroker3.triggerorder.WEB3ToSuccOrderManagerImpl",
                    "isReversingTrade",
                    new Class[]{ String.class, OrderUnit.class });

           assertEquals("11", "" + ((String)l_paramsValue1.getCalled(0)[0]));
           assertEquals("989", "" + ((OrderUnit)l_paramsValue1.getCalled(0)[1]).getOrderUnitId());
           
            WEB3MockObjectParamsValue l_paramsValue2 =
                TestBaseForMock.MOCK_MANAGER.getMethodParamsValue(
                    "webbroker3.triggerorder.WEB3ToSuccOrderManagerImpl",
                    "validateSuccOrderMaxQuantity",
                    new Class[]{ OrderUnit.class });

           assertEquals("989", "" + ((OrderUnit)l_paramsValue2.getCalled(0)[0]).getOrderUnitId());
           
           WEB3MockObjectParamsValue l_paramsValue3 =
               TestBaseForMock.MOCK_MANAGER.getMethodParamsValue(
                   "webbroker3.triggerorder.WEB3ToSuccOrderManagerImpl",
                   "validateSuccOrder",
                   new Class[]{WEB3GentradeSubAccount.class, ProductTypeEnum.class, String.class, String.class, OrderUnit.class});
           
           assertEquals("333812512246", "" + ((WEB3GentradeSubAccount)l_paramsValue3.getCalled(0)[0]).getAccountId());
           assertEquals("6", "" + ((ProductTypeEnum)l_paramsValue3.getCalled(0)[1]).intValue());
           assertEquals("1", "" + ((String)l_paramsValue3.getCalled(0)[2]));
           assertEquals("11", "" + ((String)l_paramsValue3.getCalled(0)[3]));
           assertEquals("100.0", "" + ((OrderUnit)l_paramsValue3.getCalled(0)[4]).getQuantity());
           
           
           assertEquals("21", l_response.orderActionId);
        }
        catch (WEB3BaseException e)
        {
            log.exiting(STR_METHOD_NAME);
            fail();
        }
        catch (Exception e)
        {
            log.exiting(STR_METHOD_NAME);
            
            fail();
        }
        log.exiting(STR_METHOD_NAME);
    }
    
    /**
     * 
     */
    public void test_validateOrder_0001()
    {
        final String STR_METHOD_NAME = "test_validateOrder_0001()";
        log.entering(STR_METHOD_NAME);

        MOCK_MANAGER.setIsMockUsed(true);
        try
        {
            WEB3SuccFuturesOpenConfirmRequest l_request = new WEB3SuccFuturesOpenConfirmRequest();
            
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
            // super.validate()
            l_request.contractType = "1";
            l_request.marketCode = "1";
            l_request.targetProductCode = "0001";
            l_request.delivaryMonth = "200803";
            l_request.futOrderQuantity = "1000";
            // 連続注文共通情報!＝nullの場合
            l_request.succCommonInfo = new WEB3SuccCommonInfo();
            // this.succCommonInfo.validate();
            l_request.succCommonInfo.parentOrderId = "0001";
            l_request.succCommonInfo.succTradingType = "11";
            // 連続注文単価調整値情報!=nullの場合、
            l_request.priceAdjustmentValueInfo = new WEB3SuccPriceAdjustmentValueInfo();
            //this.priceAdjustmentValueInfo.validate();
            l_request.priceAdjustmentValueInfo.sign = "+";
            l_request.priceAdjustmentValueInfo.priceAdjustmentValue = "1000";
            
            String[] l_strValues = {"0005"};
            WEB3GentradeTradingTimeManagementForMock.mockGetTradeCloseSuspension(l_strValues, "1");
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
            
            SubAccountParams l_subAccountParams = TestDBUtility.getSubAccountRow();
            l_subAccountParams.setAccountId(333812512246L);
            l_subAccountParams.setSubAccountType(SubAccountTypeEnum.EQUITY_OPTIONS_SUB_ACCOUNT);
            TestDBUtility.insertWithDel(l_subAccountParams);
            MainAccountParams l_mainAccountParams = TestDBUtility.getMainAccountRow();
            l_mainAccountParams.setQuotoType("1");
            TestDBUtility.insertWithDel(l_mainAccountParams);
            BranchParams l_branchParams = TestDBUtility.getBranchRow();
            l_branchParams.setCloseWorngSysFuture(1);
            TestDBUtility.insertWithDel(l_branchParams);
            InstitutionParams l_institutionParams = TestDBUtility.getInstitutionRow();
            l_institutionParams.setInstitutionId(33);
            TestDBUtility.insertWithDel(l_institutionParams);
            
            IfoOrderUnitParams l_ifoOrderUnitParams = TestDBUtility.getIfoOrderUnitRow();
            l_ifoOrderUnitParams.setProductId(3304148080000L);
            l_ifoOrderUnitParams.setOrderId(0001);
            l_ifoOrderUnitParams.setOrderUnitId(989);
            l_ifoOrderUnitParams.setMarketId(3303L);
            TestDBUtility.insertWithDel(l_ifoOrderUnitParams);
            IfoContractSettleOrderUnitImpl l_ifoOrderUnit= new IfoContractSettleOrderUnitImpl(989);
            
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
            
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue("webbroker3.ifo.WEB3FuturesOrderManagerImpl",
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
                    "validateFuturesOpenContractOrder", new Class[]
                    { WEB3GentradeSubAccount.class, WEB3IfoOpenContractOrderSpec.class },
                    l_result);
            
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                    "com.fitechlabs.xtrade.plugin.security.oplogin.impl.OpLoginSecurityServiceImpl", 
                    "getSessionProperty",
                    new Class[] {String.class},
                    "");
            
            WEB3IfoEstimateDeliveryAmountCalcResult l_calcResult = new WEB3IfoEstimateDeliveryAmountCalcResult();
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                    "webbroker3.ifo.WEB3FuturesOrderManagerImpl",
                    "calcEstimatePrice",
                    new Class[]
                    { WEB3GentradeCommission.class, double.class, WEB3GentradeSubAccount.class,
                            WEB3IfoTradedProductImpl.class, double.class, boolean.class },
                            l_calcResult);
            
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                    "webbroker3.triggerorder.WEB3ToSuccOrderManagerImpl", "getReserveIfoOrderExecPrice", new Class[]
                    {IfoOrderUnit.class, double.class, Double.class, WEB3IfoTradedProductImpl.class}, new Double("231"));
                    
            WEB3SuccFuturesOpenConfirmResponse l_response = l_service.validateOrder(l_request);
            
            WEB3MockObjectParamsValue l_paramsValue1 =
                TestBaseForMock.MOCK_MANAGER.getMethodParamsValue(
                    "webbroker3.triggerorder.WEB3ToSuccOrderManagerImpl",
                    "isReversingTrade",
                    new Class[]{ String.class, OrderUnit.class });

           assertEquals("11", "" + ((String)l_paramsValue1.getCalled(0)[0]));
           assertEquals("989", "" + ((OrderUnit)l_paramsValue1.getCalled(0)[1]).getOrderUnitId());
           
           WEB3MockObjectParamsValue l_paramsValue2 =
               TestBaseForMock.MOCK_MANAGER.getMethodParamsValue(
                   "webbroker3.triggerorder.WEB3ToSuccOrderManagerImpl",
                   "validateSuccOrderMaxQuantity",
                   new Class[]{ OrderUnit.class });

          assertEquals("989", "" + ((OrderUnit)l_paramsValue2.getCalled(0)[0]).getOrderUnitId());
           
           WEB3MockObjectParamsValue l_paramsValue3 =
               TestBaseForMock.MOCK_MANAGER.getMethodParamsValue(
                   "webbroker3.triggerorder.WEB3ToSuccOrderManagerImpl",
                   "validateSuccOrder",
                   new Class[]{WEB3GentradeSubAccount.class, ProductTypeEnum.class, String.class, String.class, OrderUnit.class});
           
           assertEquals("333812512246", "" + ((WEB3GentradeSubAccount)l_paramsValue3.getCalled(0)[0]).getAccountId());
           assertEquals("6", "" + ((ProductTypeEnum)l_paramsValue3.getCalled(0)[1]).intValue());
           assertEquals("1", "" + ((String)l_paramsValue3.getCalled(0)[2]));
           assertEquals("11", "" + ((String)l_paramsValue3.getCalled(0)[3]));
           assertEquals("100.0", "" + ((OrderUnit)l_paramsValue3.getCalled(0)[4]).getQuantity());
           
           assertEquals("160030005", l_response.futProductCode);
           
           
        }
        catch (WEB3BaseException e)
        {
            log.exiting(STR_METHOD_NAME);
            fail();
        }
        catch (Exception e)
        {
             log.exiting(STR_METHOD_NAME);
            
            fail();
        }
        log.exiting(STR_METHOD_NAME);
    }
}
@
