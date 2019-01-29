head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.04.07.00.45.26;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	5444d9d078c63f4;
filename	WEB3FuturesOpenContractServiceImplTest.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : 株価指数先物新規建注文サービスImplTest(WEB3FuturesOpenContractServiceImplTest.java)
Author Name      : Daiwa Institute of Research
Revision History : 2007/04/24 張騰宇 (中訊) 新規作成 仕様変更モデル
*/
package webbroker3.ifo.service.delegate.stdimpls;

import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import com.fitechlabs.xtrade.kernel.boot.Services;
import com.fitechlabs.xtrade.kernel.util.ThreadLocalSystemAttributesRegistry;
import com.fitechlabs.xtrade.plugin.tc.gentrade.BooleanEnum;
import com.fitechlabs.xtrade.plugin.tc.gentrade.ContractTypeEnum;
import com.fitechlabs.xtrade.plugin.tc.gentrade.FinApp;
import com.fitechlabs.xtrade.plugin.tc.gentrade.GtlUtils;
import com.fitechlabs.xtrade.plugin.tc.gentrade.Institution;
import com.fitechlabs.xtrade.plugin.tc.gentrade.NotFoundException;
import com.fitechlabs.xtrade.plugin.tc.gentrade.OrderCategEnum;
import com.fitechlabs.xtrade.plugin.tc.gentrade.OrderTypeEnum;
import com.fitechlabs.xtrade.plugin.tc.gentrade.ProcessingResult;
import com.fitechlabs.xtrade.plugin.tc.gentrade.ProductTypeEnum;
import com.fitechlabs.xtrade.plugin.tc.gentrade.SideEnum;
import com.fitechlabs.xtrade.plugin.tc.gentrade.SubAccount;
import com.fitechlabs.xtrade.plugin.tc.gentrade.TaxTypeEnum;
import com.fitechlabs.xtrade.plugin.tc.gentrade.Trader;
import com.fitechlabs.xtrade.plugin.tc.gentrade.TradingCalendarDetails;
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
import com.fitechlabs.xtrade.plugin.tc.gentrade.ordersubmitter.io.CancelOrderSpec;
import com.fitechlabs.xtrade.plugin.tc.gentrade.ordersubmitter.io.NewOrderValidationResult;
import com.fitechlabs.xtrade.plugin.tc.gentrade.ordersubmitter.io.OrderSubmissionResult;
import com.fitechlabs.xtrade.plugin.tc.gentrade.ordersubmitter.io.SettleContractEntry;
import com.fitechlabs.xtrade.plugin.tc.xbifo.IfoDerivativeTypeEnum;
import com.fitechlabs.xtrade.plugin.tc.xbifo.IfoOrderExecutionConditionType;
import com.fitechlabs.xtrade.plugin.tc.xbifo.IfoOrderManagerPersistenceEventInterceptor;
import com.fitechlabs.xtrade.plugin.tc.xbifo.data.IfoClosingContractSpecParams;
import com.fitechlabs.xtrade.plugin.tc.xbifo.data.IfoClosingContractSpecRow;
import com.fitechlabs.xtrade.plugin.tc.xbifo.data.IfoContractParams;
import com.fitechlabs.xtrade.plugin.tc.xbifo.data.IfoContractRow;
import com.fitechlabs.xtrade.plugin.tc.xbifo.data.IfoOrderUnitParams;
import com.fitechlabs.xtrade.plugin.tc.xbifo.data.IfoOrderUnitRow;
import com.fitechlabs.xtrade.plugin.tc.xbifo.data.IfoProductParams;
import com.fitechlabs.xtrade.plugin.tc.xbifo.data.IfoProductRow;
import com.fitechlabs.xtrade.plugin.tc.xbifo.data.IfoTradedProductParams;
import com.fitechlabs.xtrade.plugin.tc.xbifo.data.IfoTradedProductRow;
import com.fitechlabs.xtrade.plugin.tc.xbifo.data.IfoTradedProductUpdqParams;
import com.fitechlabs.xtrade.plugin.tc.xbifo.data.IfoTradedProductUpdqRow;
import com.fitechlabs.xtrade.plugin.tc.xbifo.ordersubmitter.io.IfoOpenContractOrderSpec;

import test.util.TestDBUtility;

import webbroker3.common.WEB3BaseException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.common.WEB3SystemLayerException;
import webbroker3.common.define.WEB3ChannelDef;
import webbroker3.gentrade.WEB3GentradeBizDate;
import webbroker3.gentrade.WEB3GentradeCommission;
import webbroker3.gentrade.WEB3GentradeSubAccount;
import webbroker3.gentrade.WEB3GentradeTradingClendarContext;
import webbroker3.gentrade.WEB3GentradeTradingClendarDetailsImpl;
import webbroker3.gentrade.WEB3GentradeTradingTimeManagement;
import webbroker3.gentrade.WEB3GentradeTradingTimeManagementForMock;
import webbroker3.gentrade.data.EnableOrderConditionParams;
import webbroker3.gentrade.data.EnableOrderConditionRow;
import webbroker3.gentrade.data.TradingTimeParams;
import webbroker3.gentrade.data.TradingTimeRow;
import webbroker3.ifo.WEB3IfoEstimateDeliveryAmountCalcResult;
import webbroker3.ifo.WEB3IfoOpenContractOrderSpec;
import webbroker3.ifo.WEB3IfoOpenContractUpdateInterceptor;
import webbroker3.ifo.WEB3IfoProductImpl;
import webbroker3.ifo.WEB3IfoTradedProductImpl;
import webbroker3.ifo.message.WEB3FuturesOpenMarginCompleteRequest;
import webbroker3.ifo.message.WEB3FuturesOpenMarginConfirmRequest;
import webbroker3.ifo.message.WEB3FuturesOpenMarginConfirmResponse;
import webbroker3.ifo.message.WEB3FuturesOptionsCloseMarginContractUnit;
import webbroker3.ifo.service.delegate.WEB3FuturesOpenContractRequestAdapter;
import webbroker3.mock.TestBaseForMock;
import webbroker3.mock.util.WEB3MockObjectParamsValue;
import webbroker3.mock.util.WEB3MockObjectRuntimeException;
import webbroker3.tradingpower.WEB3TPTradingPowerResult;
import webbroker3.util.WEB3DateUtility;
import webbroker3.util.WEB3LogUtility;

public class WEB3FuturesOpenContractServiceImplTest extends TestBaseForMock
{
    /**
     * ログユーティリティ
     */
    private static WEB3LogUtility log = WEB3LogUtility.getInstance(WEB3FuturesOpenContractServiceImplTest.class);

    public WEB3FuturesOpenContractServiceImplTest(String arg0)
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
    public static String BASE_TIMESTAMP_FOR_ORDER_BIZ_DATE = "web3.attributes.basetimestampfororderbizdate";
    public static String TRADING_CAL_CONTEXT_PATH = "web3.tradingcalendarcontext";
    public class WEB3FuturesOpenMarginConfirmRequestForMock 
        extends WEB3FuturesOpenMarginConfirmRequest
    {
        public void validate() throws WEB3BaseException 
        {
            final String STR_METHOD_NAME = "WEB3FuturesOpenMarginConfirmRequestForMock + validate()";
            log.entering(STR_METHOD_NAME);
                      
            log.exiting(STR_METHOD_NAME);
        }
    }
    public class WEB3FuturesOpenMarginCompleteRequestForMock 
        extends WEB3FuturesOpenMarginCompleteRequest
    {
        public void validate() throws WEB3BaseException 
        {
            final String STR_METHOD_NAME = "WEB3FuturesOpenMarginCompleteRequestForMock + validate()";
            log.entering(STR_METHOD_NAME);
                      
            log.exiting(STR_METHOD_NAME);
        }
    }
    /*
     * Test method for 'webbroker3.ifo.service.delegate.stdimpls.WEB3FuturesOpenContractServiceImpl.validateOrder(WEB3FuturesOpenMarginConfirmRequest)'
     */
    public void testValidateOrderCase1()
    {
        final String STR_METHOD_NAME = "testValidateOrderCase1()";
        log.entering(STR_METHOD_NAME);
        IfoOrderUnitParams l_orderUnitParams = TestDBUtility.getIfoOrderUnitRow();
        l_orderUnitParams.setOrderId(1001);
        l_orderUnitParams.setFirstOrderUnitId(null);
        l_orderUnitParams.setEveningSessionCarryoverFlag(BooleanEnum.TRUE);
        l_orderUnitParams.setMarketId(3303L);
        l_orderUnitParams.setProductId(3304148080000L);
        l_orderUnitParams.setOrderCateg(OrderCategEnum.IDX_FUTURES_CLOSE);
        l_orderUnitParams.setBizDate("20070630");
        l_orderUnitParams.setOrderType(OrderTypeEnum.IDX_FUTURES_BUY_TO_CLOSE);
        l_orderUnitParams.setSessionType("1");
        
        MarketParams l_marketParams = TestDBUtility.getMarketRow();
        l_marketParams.setMarketId(3303L);
        
        InstitutionParams l_institutionParams = TestDBUtility.getInstitutionRow();
        l_institutionParams.setInstitutionCode("0D");
        
        ProductParams l_productParams = TestDBUtility.getProductRow();
        l_productParams.setProductId(3304148080000L);
        l_productParams.setProductType(ProductTypeEnum.IFO);
        
        IfoProductParams l_ifoProductParams = TestDBUtility.getIfoProductRow();
        l_ifoProductParams.setProductId(3304148080000L);
        l_ifoProductParams.setProductType(ProductTypeEnum.IFO);
        l_ifoProductParams.setProductCode("0005");
        l_ifoProductParams.setInstitutionCode("0D");
        
        TradedProductParams l_tradedProductParams = TestDBUtility.getTradedProductRow();
        l_tradedProductParams.setTradedProductId(330304148080000L);
        l_tradedProductParams.setProductId(3304148080000L);
        l_tradedProductParams.setProductType(ProductTypeEnum.IFO);
        
        IfoTradedProductParams l_ifoTradedProductParams = TestDBUtility.getIfoTradedProductRow();
        l_ifoTradedProductParams.setTradedProductId(330304148080000L);
        l_ifoTradedProductParams.setProductId(3304148080000L);
        Calendar ca =  Calendar.getInstance();
        ca.add(Calendar.DATE, 1);
        Date date = ca.getTime();
        l_ifoTradedProductParams.setValidForBizDate(WEB3DateUtility.formatDate(date, "yyyyMMdd"));
//        l_ifoTradedProductParams.setProductType(ProductTypeEnum.IFO);
        
        IfoClosingContractSpecParams l_ifoClosingContractSpecParams =
            TestDBUtility.getIfoClosingContractSpecRow();
        l_ifoClosingContractSpecParams.setOrderUnitId(1001);
        l_ifoClosingContractSpecParams.setOrderId(1001);
        l_ifoClosingContractSpecParams.setContractId(1001);
        
        IfoContractParams l_ifoContractParams = TestDBUtility.getIfoContractRow();
        l_ifoContractParams.setSessionType("1");
        l_ifoContractParams.setDeliveryDate(WEB3DateUtility.getDate("20060702","yyyyMMdd"));////
        l_ifoContractParams.setProductId(3304148080000L);
        l_ifoContractParams.setMarketId(3303L);
        l_ifoContractParams.setContractType(ContractTypeEnum.UNDEFINED);
        l_ifoContractParams.setContractId(1001);
        l_ifoContractParams.setAccountId(333812512203L);
        l_ifoContractParams.setSubAccountId(33381251220301L);
        l_ifoContractParams.setCloseDate(WEB3DateUtility.getDate("20040701","yyyyMMdd"));
        l_ifoContractParams.setContractType(ContractTypeEnum.SHORT);
        
        EnableOrderConditionParams l_enableOrderConditionParams =
            TestDBUtility.getEnableOrderConditionParamsRow();
        l_enableOrderConditionParams.setInstitutionCode("0D");
        l_enableOrderConditionParams.setProductType(ProductTypeEnum.IFO);
        l_enableOrderConditionParams.setFutureOptionDiv("1");
        l_enableOrderConditionParams.setMarginTradingDiv("0");
        l_enableOrderConditionParams.setMarketCode("0");
        l_enableOrderConditionParams.setCarriedOrder("0");
        
        try
        {
            SimpleDateFormat l_format = GtlUtils.getThreadSafeSimpleDateFormat("yyyyMMdd");
            Date l_datBizDate = GtlUtils.getTradingSystem().getSystemTimestamp();
            Date l_datpreBizDate = new WEB3GentradeBizDate(
                new Timestamp(l_datBizDate.getTime())).roll(1);
            String l_strCreateDate = l_format.format(l_datpreBizDate);
            
            TestDBUtility.deleteAll(IfoTradedProductUpdqRow.TYPE);
            IfoTradedProductUpdqParams l_ifoTradedProductUpdqParams = TestDBUtility.getIfoTradedProductUpdqRow();
            l_ifoTradedProductUpdqParams.setTradedProductId(330304148080000L);
            l_ifoTradedProductUpdqParams.setMarketId(3303L);
            l_ifoTradedProductUpdqParams.setProductId(3304148080000L);
            l_ifoTradedProductUpdqParams.setValidForBizDate(l_strCreateDate);
            TestDBUtility.insertWithDel(l_ifoTradedProductUpdqParams);
            
            TestDBUtility.deleteAll(IfoOrderUnitRow.TYPE);
            TestDBUtility.insertWithDel(l_orderUnitParams);
            
            TestDBUtility.deleteAll(MarketRow.TYPE);
            TestDBUtility.insertWithDel(l_marketParams);
            TestDBUtility.deleteAll(InstitutionRow.TYPE);
            TestDBUtility.insertWithDel(l_institutionParams);
            
            TestDBUtility.deleteAll(ProductRow.TYPE);
            TestDBUtility.insertWithDel(l_productParams);
            TestDBUtility.deleteAll(IfoProductRow.TYPE);
            TestDBUtility.insertWithDel(l_ifoProductParams);
            
            TestDBUtility.deleteAll(TradedProductRow.TYPE);
            TestDBUtility.insertWithDel(l_tradedProductParams);
            TestDBUtility.deleteAll(IfoTradedProductRow.TYPE);
            TestDBUtility.insertWithDel(l_ifoTradedProductParams);
            
            TestDBUtility.deleteAll(IfoClosingContractSpecRow.TYPE);
            TestDBUtility.insertWithDel(l_ifoClosingContractSpecParams);
            
            TestDBUtility.deleteAll(IfoContractRow.TYPE);
            TestDBUtility.insertWithDel(l_ifoContractParams);
            
            TestDBUtility.deleteAll(EnableOrderConditionRow.TYPE);
            TestDBUtility.insertWithDel(l_enableOrderConditionParams);
            
            TestDBUtility.deleteAll(TradingTimeRow.TYPE);
        }
        catch (Exception l_ex)
        {
            log.error(l_ex.getMessage(), l_ex);
            fail();
        }
        try
        {
            MOCK_MANAGER.setIsMockUsed(true);
            SettleContractEntry[] l_eqOrderEntrys = new SettleContractEntry[1];
            SettleContractEntry l_entry = new SettleContractEntry(1001, 100);
            l_eqOrderEntrys[0] = l_entry;
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                    "webbroker3.ifo.WEB3FuturesOrderManagerImpl",
                    "createSettleContractEntry",
                    new Class[] {long.class, double.class, WEB3FuturesOptionsCloseMarginContractUnit[].class},
                    l_eqOrderEntrys);
            
            NewOrderValidationResult l_result = new NewOrderValidationResult(ProcessingResult.SUCCESS_RESULT);
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                    "webbroker3.ifo.WEB3FuturesOrderManagerImpl",
                    "validateFuturesOpenContractOrder",
                    new Class[] {WEB3GentradeSubAccount.class, WEB3IfoOpenContractOrderSpec.class},
                    l_result);
            
            WEB3IfoEstimateDeliveryAmountCalcResult l_ifoResult =
                new WEB3IfoEstimateDeliveryAmountCalcResult();
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                    "webbroker3.ifo.WEB3FuturesOrderManagerImpl",
                    "calcChangeEstimateSettlementIncome",
                    new Class[] {WEB3GentradeCommission.class, double.class, WEB3GentradeSubAccount.class,
                            WEB3IfoTradedProductImpl.class, SettleContractEntry[].class, double.class, SideEnum.class,
                            double.class, long.class, boolean.class},
                    l_ifoResult);
            
//            OrderValidationResult
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                    "webbroker3.ifo.WEB3FuturesOrderManagerImpl",
                    "validateFuturesCancelOrder",
                    new Class[] {WEB3GentradeSubAccount.class, CancelOrderSpec.class},
                    l_result);
            
            WEB3IfoEstimateDeliveryAmountCalcResult l_ifoEstimateDeliveryAmountCalcResult = 
                new WEB3IfoEstimateDeliveryAmountCalcResult();
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                    "webbroker3.ifo.WEB3FuturesOrderManagerImpl",
                    "calcEstimatePrice",
                    new Class[] {WEB3GentradeCommission.class, double.class, WEB3GentradeSubAccount.class,
                            WEB3IfoTradedProductImpl.class, double.class, boolean.class},
                            l_ifoEstimateDeliveryAmountCalcResult);
            
            TradingTimeParams l_tradingTimeParams = TestDBUtility.getTradingTimeRow();
            l_tradingTimeParams.setInstitutionCode("0D");
            l_tradingTimeParams.setBranchCode("123");
            l_tradingTimeParams.setMarketCode("N1");
            l_tradingTimeParams.setTradingTimeType("01");
            l_tradingTimeParams.setProductCode("0");
            l_tradingTimeParams.setBizDateType("1");
            TestDBUtility.insertWithDel(l_tradingTimeParams);
            WEB3IfoTradedProductImpl l_tradeProduct = new WEB3IfoTradedProductImpl(330304148080000L);
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                    "webbroker3.ifo.WEB3IfoProductManagerImpl", 
                    "getIfoTradedProduct",
                    new Class[] { Institution.class, String.class, String.class },
                    l_tradeProduct);
            
            WEB3TPTradingPowerResult l_tradingPowerResult = new WEB3TPTradingPowerResult();
            l_tradingPowerResult.setResultFlg(true);
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                    "webbroker3.tradingpower.WEB3TPTradingPowerServiceImpl",
                    "validateTradingPower",
                    new Class[] {WEB3GentradeSubAccount.class, Object[].class,
                            Object[].class, OrderTypeEnum.class,boolean.class },
                    l_tradingPowerResult);
            TradingCalendarDetails tradingCalendarDetails =
                new WEB3GentradeTradingClendarDetailsImpl();
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                    "webbroker3.gentrade.WEB3GentradeTradingCalendarModelImpl",
                    "getTradingCalendarDetails",
                    new Class[] {long.class},
                    tradingCalendarDetails);
            
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                    "webbroker3.gentrade.WEB3GentradeTradingCalendarModelImpl",
                    "getCurrentBizDate",
                    new Class[] {long.class},
                    WEB3DateUtility.getDate("20070619","yyyyMMdd"));

            WEB3IfoProductImpl l_ifoProduct = new WEB3IfoProductImpl(l_ifoProductParams);
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                    "webbroker3.ifo.WEB3IfoProductManagerImpl", "getIfoProduct",
                    new Class[] { Institution.class, String.class },
                    l_ifoProduct);

            WEB3GentradeTradingClendarContext l_clendarContext =
                (WEB3GentradeTradingClendarContext)
                    ThreadLocalSystemAttributesRegistry.getAttribute(TRADING_CAL_CONTEXT_PATH);
            l_clendarContext.setInstitutionCode("0D");
            l_clendarContext.setProductCode("0");
            l_clendarContext.setMarketCode("N1");
            l_clendarContext.setBizDateType("1");
            
            Calendar ca1 =  Calendar.getInstance();
            ca1.set(2007,6-1,19);
            
            Date date1 = ca1.getTime();
            
            WEB3OptionClientRequestServiceForMock l_impl =
                new WEB3OptionClientRequestServiceForMock();
            WEB3FuturesOpenMarginConfirmRequestForMock l_request =
                new WEB3FuturesOpenMarginConfirmRequestForMock();
            l_request.futProductCode = "0";
            l_request.limitPrice = "100";
            l_request.futOrderQuantity = "1000";
            l_request.marketCode = "0D";
            
            WEB3FuturesOpenMarginConfirmResponse l_response = l_impl.validateOrder(l_request);
            
            assertEquals(l_response.expirationDate, WEB3GentradeTradingTimeManagement.getOrderBizDate());
        }
        catch (Exception l_ex)
        {
            log.error(l_ex.getMessage(), l_ex);
            fail();
        }
        log.exiting(STR_METHOD_NAME);
    }

    /*
     * Test method for 'webbroker3.ifo.service.delegate.stdimpls.WEB3FuturesOpenContractServiceImpl.submitOrder(WEB3FuturesOpenMarginCompleteRequest)'
     */
    public void testSubmitOrderCase1()
    {
        final String STR_METHOD_NAME = "testSubmitOrderCase1()";
        log.entering(STR_METHOD_NAME);
        IfoOrderUnitParams l_orderUnitParams = TestDBUtility.getIfoOrderUnitRow();
        l_orderUnitParams.setOrderId(1001);
        l_orderUnitParams.setFirstOrderUnitId(null);
        l_orderUnitParams.setEveningSessionCarryoverFlag(BooleanEnum.TRUE);
        l_orderUnitParams.setMarketId(3303L);
        l_orderUnitParams.setProductId(3304148080000L);
        l_orderUnitParams.setOrderCateg(OrderCategEnum.IDX_FUTURES_CLOSE);
        l_orderUnitParams.setBizDate("20070630");
        l_orderUnitParams.setOrderType(OrderTypeEnum.IDX_FUTURES_BUY_TO_CLOSE);
        l_orderUnitParams.setSessionType("1");
        
        MarketParams l_marketParams = TestDBUtility.getMarketRow();
        l_marketParams.setMarketId(3303L);
        
        InstitutionParams l_institutionParams = TestDBUtility.getInstitutionRow();
        l_institutionParams.setInstitutionCode("0D");
        
        ProductParams l_productParams = TestDBUtility.getProductRow();
        l_productParams.setProductId(3304148080000L);
        l_productParams.setProductType(ProductTypeEnum.IFO);
        
        IfoProductParams l_ifoProductParams = TestDBUtility.getIfoProductRow();
        l_ifoProductParams.setProductId(3304148080000L);
        l_ifoProductParams.setProductType(ProductTypeEnum.IFO);
        l_ifoProductParams.setProductCode("0005");
        l_ifoProductParams.setInstitutionCode("0D");
        
        TradedProductParams l_tradedProductParams = TestDBUtility.getTradedProductRow();
        l_tradedProductParams.setTradedProductId(330304148080000L);
        l_tradedProductParams.setProductId(3304148080000L);
        l_tradedProductParams.setProductType(ProductTypeEnum.IFO);
        
        IfoTradedProductParams l_ifoTradedProductParams = TestDBUtility.getIfoTradedProductRow();
        l_ifoTradedProductParams.setTradedProductId(330304148080000L);
        l_ifoTradedProductParams.setProductId(3304148080000L);
        Calendar ca =  Calendar.getInstance();
        ca.add(Calendar.DATE, 1);
        Date date = ca.getTime();
        l_ifoTradedProductParams.setValidForBizDate(null);
//        l_ifoTradedProductParams.setProductType(ProductTypeEnum.IFO);
        
        IfoClosingContractSpecParams l_ifoClosingContractSpecParams =
            TestDBUtility.getIfoClosingContractSpecRow();
        l_ifoClosingContractSpecParams.setOrderUnitId(1001);
        l_ifoClosingContractSpecParams.setOrderId(1001);
        l_ifoClosingContractSpecParams.setContractId(1001);
        
        IfoContractParams l_ifoContractParams = TestDBUtility.getIfoContractRow();
        l_ifoContractParams.setSessionType("1");
        l_ifoContractParams.setDeliveryDate(WEB3DateUtility.getDate("20060702","yyyyMMdd"));////
        l_ifoContractParams.setProductId(3304148080000L);
        l_ifoContractParams.setMarketId(3303L);
        l_ifoContractParams.setContractType(ContractTypeEnum.UNDEFINED);
        l_ifoContractParams.setContractId(1001);
        l_ifoContractParams.setAccountId(333812512203L);
        l_ifoContractParams.setSubAccountId(33381251220301L);
        l_ifoContractParams.setCloseDate(WEB3DateUtility.getDate("20040701","yyyyMMdd"));
        l_ifoContractParams.setContractType(ContractTypeEnum.SHORT);
        
        EnableOrderConditionParams l_enableOrderConditionParams =
            TestDBUtility.getEnableOrderConditionParamsRow();
        l_enableOrderConditionParams.setInstitutionCode("0D");
        l_enableOrderConditionParams.setProductType(ProductTypeEnum.IFO);
        l_enableOrderConditionParams.setFutureOptionDiv("1");
        l_enableOrderConditionParams.setMarginTradingDiv("0");
        l_enableOrderConditionParams.setMarketCode("0");
        l_enableOrderConditionParams.setCarriedOrder("0");
        
        TradingTimeParams l_tradingTimeParams = TestDBUtility.getTradingTimeRow();
        l_tradingTimeParams.setInstitutionCode("0D");
        l_tradingTimeParams.setBranchCode("123");
        l_tradingTimeParams.setMarketCode("0");
        l_tradingTimeParams.setTradingTimeType("01");
        l_tradingTimeParams.setProductCode("0005");
        l_tradingTimeParams.setBizDateType("1");
        try
        {
            SimpleDateFormat l_format = GtlUtils.getThreadSafeSimpleDateFormat("yyyyMMdd");
            Date l_datBizDate = GtlUtils.getTradingSystem().getSystemTimestamp();
            Date l_datpreBizDate = new WEB3GentradeBizDate(
                new Timestamp(l_datBizDate.getTime())).roll(1);
            String l_strCreateDate = l_format.format(l_datpreBizDate);
            
            TestDBUtility.deleteAll(IfoTradedProductUpdqRow.TYPE);
            IfoTradedProductUpdqParams l_ifoTradedProductUpdqParams = TestDBUtility.getIfoTradedProductUpdqRow();
            l_ifoTradedProductUpdqParams.setTradedProductId(330304148080000L);
            l_ifoTradedProductUpdqParams.setMarketId(3303L);
            l_ifoTradedProductUpdqParams.setProductId(3304148080000L);
            l_ifoTradedProductUpdqParams.setValidForBizDate(l_strCreateDate);
            TestDBUtility.insertWithDel(l_ifoTradedProductUpdqParams);
            
            TestDBUtility.deleteAll(IfoOrderUnitRow.TYPE);
            TestDBUtility.insertWithDel(l_orderUnitParams);
            
            TestDBUtility.deleteAll(MarketRow.TYPE);
            TestDBUtility.insertWithDel(l_marketParams);
            TestDBUtility.deleteAll(InstitutionRow.TYPE);
            TestDBUtility.insertWithDel(l_institutionParams);
            
            TestDBUtility.deleteAll(ProductRow.TYPE);
            TestDBUtility.insertWithDel(l_productParams);
            TestDBUtility.deleteAll(IfoProductRow.TYPE);
            TestDBUtility.insertWithDel(l_ifoProductParams);
            
            TestDBUtility.deleteAll(TradedProductRow.TYPE);
            TestDBUtility.insertWithDel(l_tradedProductParams);
            TestDBUtility.deleteAll(IfoTradedProductRow.TYPE);
            TestDBUtility.insertWithDel(l_ifoTradedProductParams);
            
            TestDBUtility.deleteAll(IfoClosingContractSpecRow.TYPE);
            TestDBUtility.insertWithDel(l_ifoClosingContractSpecParams);
            
            TestDBUtility.deleteAll(IfoContractRow.TYPE);
            TestDBUtility.insertWithDel(l_ifoContractParams);
            
            TestDBUtility.deleteAll(EnableOrderConditionRow.TYPE);
            TestDBUtility.insertWithDel(l_enableOrderConditionParams);
            
            TestDBUtility.deleteAll(TradingTimeRow.TYPE);
            TestDBUtility.insertWithDel(l_tradingTimeParams);
        }
        catch (Exception l_ex)
        {
            log.error(l_ex.getMessage(), l_ex);
            fail();
        }
        try
        {
            MOCK_MANAGER.setIsMockUsed(true);
            
            SettleContractEntry[] l_eqOrderEntrys = new SettleContractEntry[1];
            SettleContractEntry l_entry = new SettleContractEntry(1001, 100);
            l_eqOrderEntrys[0] = l_entry;
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                    "webbroker3.ifo.WEB3FuturesOrderManagerImpl",
                    "createSettleContractEntry",
                    new Class[] {long.class, double.class, WEB3FuturesOptionsCloseMarginContractUnit[].class},
                    l_eqOrderEntrys);
            
            NewOrderValidationResult l_result = new NewOrderValidationResult(ProcessingResult.SUCCESS_RESULT);
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                    "webbroker3.ifo.WEB3FuturesOrderManagerImpl",
                    "validateFuturesOpenContractOrder",
                    new Class[] {WEB3GentradeSubAccount.class, WEB3IfoOpenContractOrderSpec.class},
                    l_result);
            
            WEB3IfoEstimateDeliveryAmountCalcResult l_ifoResult =
                new WEB3IfoEstimateDeliveryAmountCalcResult();
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                    "webbroker3.ifo.WEB3FuturesOrderManagerImpl",
                    "calcChangeEstimateSettlementIncome",
                    new Class[] {WEB3GentradeCommission.class, double.class, WEB3GentradeSubAccount.class,
                            WEB3IfoTradedProductImpl.class, SettleContractEntry[].class, double.class, SideEnum.class,
                            double.class, long.class, boolean.class},
                    l_ifoResult);
            
//            OrderValidationResult
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                    "webbroker3.ifo.WEB3FuturesOrderManagerImpl",
                    "validateFuturesCancelOrder",
                    new Class[] {WEB3GentradeSubAccount.class, CancelOrderSpec.class},
                    l_result);
            
            WEB3IfoEstimateDeliveryAmountCalcResult l_ifoEstimateDeliveryAmountCalcResult = 
                new WEB3IfoEstimateDeliveryAmountCalcResult();
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                    "webbroker3.ifo.WEB3FuturesOrderManagerImpl",
                    "calcEstimatePrice",
                    new Class[] {WEB3GentradeCommission.class, double.class, WEB3GentradeSubAccount.class,
                            WEB3IfoTradedProductImpl.class, double.class, boolean.class},
                            l_ifoEstimateDeliveryAmountCalcResult);
            
            WEB3IfoTradedProductImpl l_tradeProduct = new WEB3IfoTradedProductImpl(330304148080000L);
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                    "webbroker3.ifo.WEB3IfoProductManagerImpl", 
                    "getIfoTradedProduct",
                    new Class[] { Institution.class, String.class, String.class },
                    l_tradeProduct);
            
            WEB3TPTradingPowerResult l_tradingPowerResult = new WEB3TPTradingPowerResult();
            l_tradingPowerResult.setResultFlg(true);
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                    "webbroker3.tradingpower.WEB3TPTradingPowerServiceImpl",
                    "validateTradingPower",
                    new Class[] {WEB3GentradeSubAccount.class, Object[].class,
                            Object[].class, OrderTypeEnum.class,boolean.class },
                    l_tradingPowerResult);
            
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                    "webbroker3.ifo.WEB3FuturesOrderManagerImpl",
                    "setThreadLocalPersistenceEventInterceptor", 
                    new Class[]{ IfoOrderManagerPersistenceEventInterceptor.class },
                    new WEB3MockObjectRuntimeException(STR_METHOD_NAME));
            
            WEB3GentradeTradingClendarContext l_clendarContext =
                (WEB3GentradeTradingClendarContext)
                    ThreadLocalSystemAttributesRegistry.getAttribute(TRADING_CAL_CONTEXT_PATH);
            l_clendarContext.setInstitutionCode("0D");
            l_clendarContext.setBranchCode("123");
            l_clendarContext.setProductCode("0005");
            l_clendarContext.setMarketCode("0");
            l_clendarContext.setBizDateType("1");
            l_clendarContext.setTradingTimeType("01");
            WEB3GentradeTradingTimeManagementForMock.setClendarContext(l_clendarContext);
            
            Calendar ca1 =  Calendar.getInstance();
            ca1.set(2007,6-1,19);
            
            Date date1 = ca1.getTime();
//            WEB3GentradeTradingTimeManagementForMock.mockGetOrderBizDate(date1);
            this.setExpectedDate(date1, "1");
            
            WEB3OptionClientRequestServiceForMock l_impl =
                new WEB3OptionClientRequestServiceForMock();
            WEB3FuturesOpenMarginCompleteRequestForMock l_request =
                new WEB3FuturesOpenMarginCompleteRequestForMock();
            l_request.futProductCode = "0005";
            l_request.limitPrice = "100";
            l_request.futOrderQuantity = "1000";
            l_request.marketCode = "0D";
            l_impl.submitOrder(l_request);
            fail();
        }
        catch (WEB3MockObjectRuntimeException l_web3MockObjectRuntimeException)
        {
            WEB3MockObjectParamsValue l_paramsValue6 = TestBaseForMock.MOCK_MANAGER.getMethodParamsValue(
                    "webbroker3.ifo.WEB3FuturesOrderManagerImpl", 
                    "setThreadLocalPersistenceEventInterceptor", 
                    new Class[] {IfoOrderManagerPersistenceEventInterceptor.class});
            assertEquals(((WEB3IfoOpenContractUpdateInterceptor)l_paramsValue6.getFirstCalled()[0]).getSessionType(), 
                    "1");
        }
        catch (Exception l_ex)
        {
            log.error(l_ex.getMessage(), l_ex);
            fail();
        }
        log.exiting(STR_METHOD_NAME);
    }
    private void setExpectedDate(Date l_expectDate,String l_sessionType)
    {
        final String STR_METHOD_NAME = "setExpectedDate()";
        log.entering(STR_METHOD_NAME);
        try
        {
            WEB3GentradeTradingTimeManagementForMock.mockGetBizDateType(new Timestamp(l_expectDate.getTime()), "1");
            
            TestDBUtility.deleteAll(TradingTimeRow.TYPE);
            TradingTimeParams l_tradingTimeParams = new TradingTimeParams();
            
            l_tradingTimeParams.setInstitutionCode("0D");
            l_tradingTimeParams.setBranchCode("123");
            l_tradingTimeParams.setMarketCode("0");
            l_tradingTimeParams.setTradingTimeType("01");
            l_tradingTimeParams.setProductCode("0005");
            l_tradingTimeParams.setBizDateType("1");
            l_tradingTimeParams.setStartTime("000000");
            l_tradingTimeParams.setEndTime("235959");        
            l_tradingTimeParams.setSubmitMarketTrigger("0");
            l_tradingTimeParams.setEnableOrder("0");
            l_tradingTimeParams.setBizdateCalcParameter("0");
            l_tradingTimeParams.setCreatedTimestamp(Calendar.getInstance().getTime());
            l_tradingTimeParams.setLastUpdatedTimestamp(Calendar.getInstance().getTime());
            l_tradingTimeParams.setSessionType(l_sessionType);
            
            TestDBUtility.insertWithDel(l_tradingTimeParams);
            
            ThreadLocalSystemAttributesRegistry.setAttribute(
                BASE_TIMESTAMP_FOR_ORDER_BIZ_DATE, new Timestamp(l_expectDate.getTime()));   
        }
        catch (Exception l_ex)
        {
            log.error(l_ex.getMessage(), l_ex);
            fail();
        }
        log.exiting(STR_METHOD_NAME);
    }
    
    /**
     * 先物新規買建
     * BUSINESS_ERROR_01306:取引余力チェックエラー。
     *
     */
    public void testValidateTradingPower_C0001()
    {
        final String STR_METHOD_NAME = "testValidateTradingPower_C0001()";
        log.entering(STR_METHOD_NAME);
        try
        {
            MOCK_MANAGER.setIsMockUsed(true);
            WEB3GentradeTradingTimeManagementForMock.mockGetOrderBizDate(new Date());
            
            WEB3TPTradingPowerResult l_tradingPowerResult = new WEB3TPTradingPowerResult();
            l_tradingPowerResult.setResultFlg(false);
            
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                "webbroker3.tradingpower.WEB3TPTradingPowerServiceImpl", 
                "validateTradingPower",
                new Class[] {WEB3GentradeSubAccount.class, Object[].class, Object[].class, OrderTypeEnum.class,boolean.class},
                l_tradingPowerResult);
            
            WEB3FuturesOpenContractServiceImpl l_service = new WEB3FuturesOpenContractServiceImpl();
            WEB3GentradeSubAccount l_subAccount = new WEB3GentradeSubAccount(TestDBUtility.getSubAccountRow());
            Object[] l_objOrderSpecInterceptors = new Object[0];
            WEB3IfoOpenContractOrderSpec [] l_orderSpec = new WEB3IfoOpenContractOrderSpec[1];
            
            
//            String l_strInstitutionCode, 
//            Trader l_trader, 
//            boolean l_blnIsBuyToOpenOrder, 
//            String l_strMarket, 
//            WEB3IfoProductImpl l_product, 
//            double l_dblQuantity, 
//            double l_dblLimitPrice, 
//            IfoOrderExecutionConditionType l_executionConditionType, 
//            Date l_datExpirationDate, 
//            String l_strOrderCond, 
//            double l_dblStopOrderBasePrice,
//            double l_dblWLimitPriceChange,
//            IfoOrderExecutionConditionType l_wLimitExecCondType,
//            String l_strExpirationDateType, 
//            Long l_lngFirstOrderUnitId,
//            boolean l_blnEveningSessionCarryoverFlag
            WEB3IfoProductImpl l_product = new WEB3IfoProductImpl(3304148080000L);
            l_orderSpec[0] = WEB3IfoOpenContractOrderSpec.createOpenContractOrderSpec(
                "1D",null,true,null,l_product,0,0,null,new Date(),null,0,0,null,null,null,false);
            
            l_service.validateTradingPower(l_subAccount,l_objOrderSpecInterceptors,l_orderSpec,true);
            fail();
        }
        catch(WEB3BaseException l_ex)
        {
            log.error(l_ex.getMessage(), l_ex);
            assertEquals(l_ex.getErrorInfo(),WEB3ErrorCatalog.BUSINESS_ERROR_01306);
        }
        catch (Exception l_ex)
        {
            log.error(l_ex.getMessage(), l_ex);
            fail();
        }
        log.exiting(STR_METHOD_NAME);
    }
    
    /**
     * 先物新規売建
     * 正常返回
     *
     */
    public void testValidateTradingPower_C0002()
    {
        final String STR_METHOD_NAME = "testValidateTradingPower_C0002()";
        log.entering(STR_METHOD_NAME);
        try
        {
            MOCK_MANAGER.setIsMockUsed(true);
            WEB3GentradeTradingTimeManagementForMock.mockGetOrderBizDate(new Date());
            
            WEB3TPTradingPowerResult l_tradingPowerResult = new WEB3TPTradingPowerResult();
            l_tradingPowerResult.setResultFlg(true);
            
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                "webbroker3.tradingpower.WEB3TPTradingPowerServiceImpl", 
                "validateTradingPower",
                new Class[] {WEB3GentradeSubAccount.class, Object[].class, Object[].class, OrderTypeEnum.class,boolean.class},
                l_tradingPowerResult);
            
            WEB3FuturesOpenContractServiceImpl l_service = new WEB3FuturesOpenContractServiceImpl();
            WEB3GentradeSubAccount l_subAccount = new WEB3GentradeSubAccount(TestDBUtility.getSubAccountRow());
            Object[] l_objOrderSpecInterceptors = new Object[0];
            WEB3IfoOpenContractOrderSpec [] l_orderSpec = new WEB3IfoOpenContractOrderSpec[1];
            
            
//            String l_strInstitutionCode, 
//            Trader l_trader, 
//            boolean l_blnIsBuyToOpenOrder, 
//            String l_strMarket, 
//            WEB3IfoProductImpl l_product, 
//            double l_dblQuantity, 
//            double l_dblLimitPrice, 
//            IfoOrderExecutionConditionType l_executionConditionType, 
//            Date l_datExpirationDate, 
//            String l_strOrderCond, 
//            double l_dblStopOrderBasePrice,
//            double l_dblWLimitPriceChange,
//            IfoOrderExecutionConditionType l_wLimitExecCondType,
//            String l_strExpirationDateType, 
//            Long l_lngFirstOrderUnitId,
//            boolean l_blnEveningSessionCarryoverFlag
            WEB3IfoProductImpl l_product = new WEB3IfoProductImpl(3304148080000L);
            l_orderSpec[0] = WEB3IfoOpenContractOrderSpec.createOpenContractOrderSpec(
                "1D",null,false,null,l_product,0,0,null,new Date(),null,0,0,null,null,null,false);
            
            l_service.validateTradingPower(l_subAccount,l_objOrderSpecInterceptors,l_orderSpec,true);
            
        }
        catch(WEB3BaseException l_ex)
        {
            log.error(l_ex.getMessage(), l_ex);
            fail();
        }
        catch (Exception l_ex)
        {
            log.error(l_ex.getMessage(), l_ex);
            fail();
        }
        log.exiting(STR_METHOD_NAME);
    }
    
    /**
     * submit失敗的場合
     *
     */
    public void testSubmitOpenContractOrder_C0001()
    {
        final String STR_METHOD_NAME = "testSubmitOpenContractOrder_C0001()";
        log.entering(STR_METHOD_NAME);
        try
        {
            WEB3FuturesOpenContractServiceImpl l_service = new WEB3FuturesOpenContractServiceImpl();
            
            MOCK_MANAGER.setIsMockUsed(true);
            
            OrderSubmissionResult l_submissionResult = new OrderSubmissionResult(
                ProcessingResult.newFailedResultInstance(WEB3ErrorCatalog.BUSINESS_ERROR_01303));
            
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                "webbroker3.ifo.WEB3FuturesOrderManagerImpl", 
                "submitOpenContractOrder", 
                new Class[]{ SubAccount.class, IfoOpenContractOrderSpec.class, long.class, String.class, boolean.class },
                l_submissionResult);
            
            //            WEB3GentradeSubAccount l_subAccount,
//            IfoOpenContractOrderSpec l_ifoOpenContractOrderSpec,
//            long l_lngOrderId,
//            String l_strTradingPassword,
//            WEB3FuturesOpenContractRequestAdapter l_requestAdapter,
//            WEB3IfoEstimateDeliveryAmountCalcResult l_estimateDeliveryAmountCalcResult
            
            WEB3GentradeSubAccount l_subAccount = new WEB3GentradeSubAccount(TestDBUtility.getSubAccountRow());
            
////            Trader trader, 
//              boolean isBuyToOpen, 
//              String underlyingProductCode, 
//              IfoDerivativeTypeEnum derivativeType, 
//              String monthOfDelivery, 
////            double strikePrice, 
//              String marketCode, 
////            double quantity, 
//              IfoOrderExecutionConditionType execType, 
//              Date orderExpDate, 
//              TaxTypeEnum taxTyp
            IfoOpenContractOrderSpec l_ifoOpenContractOrderSpec =
                IfoOpenContractOrderSpec.createMarketOrderSpec(
                    null,
                    true,
                    null,
                    null,
                    null,
                    0,
                    null,
                    0,
                    null,
                    new Date(),
                    null);
            
            WEB3FuturesOpenContractRequestAdapter l_requestAdapter = WEB3FuturesOpenContractRequestAdapter.create(null);
            WEB3IfoEstimateDeliveryAmountCalcResult l_estimateDeliveryAmountCalcResult =
                new WEB3IfoEstimateDeliveryAmountCalcResult();
            
            l_service.submitOpenContractOrder(
                l_subAccount,l_ifoOpenContractOrderSpec,0,null,l_requestAdapter,l_estimateDeliveryAmountCalcResult);
        }
        catch(WEB3BaseException l_ex)
        {
            log.error(l_ex.getMessage(), l_ex);
            assertEquals(WEB3ErrorCatalog.BUSINESS_ERROR_01303,l_ex.getErrorInfo());
        }
        catch (Exception l_ex)
        {
            log.error(l_ex.getMessage(), l_ex);
            fail();
        }
    }
    
    /**
     * submit成功的場合
     *
     */
    public void testSubmitOpenContractOrder_C0002()
    {
        final String STR_METHOD_NAME = "testSubmitOpenContractOrder_C0002()";
        log.entering(STR_METHOD_NAME);
        try
        {
            WEB3FuturesOpenContractServiceImpl l_service = new WEB3FuturesOpenContractServiceImpl();
            
            MOCK_MANAGER.setIsMockUsed(true);
            
            OrderSubmissionResult l_submissionResult = new OrderSubmissionResult(
                ProcessingResult.newSuccessResultInstance());
            
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                "webbroker3.ifo.WEB3FuturesOrderManagerImpl", 
                "submitOpenContractOrder", 
                new Class[]{ SubAccount.class, IfoOpenContractOrderSpec.class, long.class, String.class, boolean.class },
                l_submissionResult);
            
            //            WEB3GentradeSubAccount l_subAccount,
//            IfoOpenContractOrderSpec l_ifoOpenContractOrderSpec,
//            long l_lngOrderId,
//            String l_strTradingPassword,
//            WEB3FuturesOpenContractRequestAdapter l_requestAdapter,
//            WEB3IfoEstimateDeliveryAmountCalcResult l_estimateDeliveryAmountCalcResult
            
            WEB3GentradeSubAccount l_subAccount = new WEB3GentradeSubAccount(TestDBUtility.getSubAccountRow());
            
////            Trader trader, 
//              boolean isBuyToOpen, 
//              String underlyingProductCode, 
//              IfoDerivativeTypeEnum derivativeType, 
//              String monthOfDelivery, 
////            double strikePrice, 
//              String marketCode, 
////            double quantity, 
//              IfoOrderExecutionConditionType execType, 
//              Date orderExpDate, 
//              TaxTypeEnum taxTyp
            IfoOpenContractOrderSpec l_ifoOpenContractOrderSpec =
                IfoOpenContractOrderSpec.createMarketOrderSpec(
                    null,
                    true,
                    null,
                    null,
                    null,
                    0,
                    null,
                    0,
                    null,
                    new Date(),
                    null);
            
            WEB3FuturesOpenContractRequestAdapter l_requestAdapter = WEB3FuturesOpenContractRequestAdapter.create(null);
            WEB3IfoEstimateDeliveryAmountCalcResult l_estimateDeliveryAmountCalcResult =
                new WEB3IfoEstimateDeliveryAmountCalcResult();
            
            l_service.submitOpenContractOrder(
                l_subAccount,l_ifoOpenContractOrderSpec,0,null,l_requestAdapter,l_estimateDeliveryAmountCalcResult);
            
            assertTrue(true);
        }
        catch(WEB3BaseException l_ex)
        {
            log.error(l_ex.getMessage(), l_ex);
            fail();
        }
        catch (Exception l_ex)
        {
            log.error(l_ex.getMessage(), l_ex);
            fail();
        }
    }
    
    private void initData()
    {
        final String STR_METHOD_NAME = "initData()";
        log.entering(STR_METHOD_NAME);
        try
        {
            TestDBUtility.deleteAll(ProductRow.TYPE);
            ProductParams l_productParams = TestDBUtility.getProductRow();
            l_productParams.setProductId(3304148080000L);
            TestDBUtility.insertWithDel(l_productParams);
            
            TestDBUtility.deleteAll(IfoProductRow.TYPE);
            IfoProductParams l_ifoProductParams = TestDBUtility.getIfoProductRow();
            l_ifoProductParams.setProductId(3304148080000L);
            TestDBUtility.insertWithDel(l_ifoProductParams);
            
        }
        catch (Exception l_ex)
        {
            log.error(l_ex.getMessage(), l_ex);
            fail();
        }
        log.exiting(STR_METHOD_NAME);
    }
    
    private class WEB3OptionClientRequestServiceForMock extends WEB3FuturesOpenContractServiceImpl
    {
        public SubAccount getSubAccount() throws WEB3BaseException 
        {
            final String STR_METHOD_NAME = "getSubAccount()";
            log.entering(STR_METHOD_NAME);
            MainAccountParams l_mainAccountParams = TestDBUtility.getMainAccountRow();
            l_mainAccountParams.setAccountId(333812512203L);
            
            SubAccountParams l_subAccountParams = TestDBUtility.getSubAccountRow();
            l_subAccountParams.setAccountId(333812512203L);
            l_subAccountParams.setSubAccountId(33381251220301L);
            
            try
            {
                TestDBUtility.deleteAll(MainAccountRow.TYPE);
                TestDBUtility.insertWithDel(l_mainAccountParams);
                
                TestDBUtility.deleteAll(SubAccountRow.TYPE);
                TestDBUtility.insertWithDel(l_subAccountParams);
            }
            catch (Exception l_ex)
            {
                log.error(l_ex.getMessage(), l_ex);
                fail();
            }
            FinApp l_finApp = (FinApp)Services.getService(FinApp.class);
            WEB3GentradeSubAccount l_subAccount = null;
            try
            {
                //取得補助口座
                l_subAccount =
                    (WEB3GentradeSubAccount)l_finApp.getAccountManager().getSubAccount(
                        333812512203L,
                        33381251220301L);
            }
            catch (NotFoundException l_nfe)
            {
                log.error("データ不整合エラー。", l_nfe);
                throw new WEB3SystemLayerException(
                    WEB3ErrorCatalog.SYSTEM_ERROR_80006,
                    this.getClass().getName() + STR_METHOD_NAME,
                    l_nfe.getMessage(),
                    l_nfe); 
            }
            
            log.exiting(STR_METHOD_NAME);
            return l_subAccount;
        }
        public Trader getTrader() throws WEB3SystemLayerException
        {
            return null;
        }
        
        public String getLoginChannel()
        {
            return WEB3ChannelDef.BRANCH;
        }
    }
}
@
