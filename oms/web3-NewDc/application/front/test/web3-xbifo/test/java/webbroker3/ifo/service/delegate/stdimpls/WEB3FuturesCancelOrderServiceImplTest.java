head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.04.07.00.44.53;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	5444d9d078c63f4;
filename	WEB3FuturesCancelOrderServiceImplTest.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : 先物取消注文サービスImplTest(WEB3FuturesCancelOrderServiceImplTest.java)
Author Name      : Daiwa Institute of Research
Revision History : 2007/06/27 張騰宇 (中訊) 新規作成
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
import com.fitechlabs.xtrade.plugin.tc.gentrade.Trader;
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
import com.fitechlabs.xtrade.plugin.tc.gentrade.ordersubmitter.io.ChangeOrderSpec;
import com.fitechlabs.xtrade.plugin.tc.gentrade.ordersubmitter.io.OrderSubmissionResult;
import com.fitechlabs.xtrade.plugin.tc.gentrade.ordersubmitter.io.OrderValidationResult;
import com.fitechlabs.xtrade.plugin.tc.gentrade.ordersubmitter.io.SettleContractEntry;
import com.fitechlabs.xtrade.plugin.tc.xbifo.IfoDerivativeTypeEnum;
import com.fitechlabs.xtrade.plugin.tc.xbifo.data.IfoClosingContractSpecParams;
import com.fitechlabs.xtrade.plugin.tc.xbifo.data.IfoClosingContractSpecRow;
import com.fitechlabs.xtrade.plugin.tc.xbifo.data.IfoContractParams;
import com.fitechlabs.xtrade.plugin.tc.xbifo.data.IfoContractRow;
import com.fitechlabs.xtrade.plugin.tc.xbifo.data.IfoOrderParams;
import com.fitechlabs.xtrade.plugin.tc.xbifo.data.IfoOrderRow;
import com.fitechlabs.xtrade.plugin.tc.xbifo.data.IfoOrderUnitParams;
import com.fitechlabs.xtrade.plugin.tc.xbifo.data.IfoOrderUnitRow;
import com.fitechlabs.xtrade.plugin.tc.xbifo.data.IfoProductParams;
import com.fitechlabs.xtrade.plugin.tc.xbifo.data.IfoProductRow;
import com.fitechlabs.xtrade.plugin.tc.xbifo.data.IfoTradedProductParams;
import com.fitechlabs.xtrade.plugin.tc.xbifo.data.IfoTradedProductRow;
import com.fitechlabs.xtrade.plugin.tc.xbifo.data.IfoTradedProductUpdqParams;
import com.fitechlabs.xtrade.plugin.tc.xbifo.data.IfoTradedProductUpdqRow;

import test.util.TestDBUtility;

import webbroker3.common.WEB3BaseException;
import webbroker3.common.WEB3BusinessLayerException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.common.WEB3SystemLayerException;
import webbroker3.common.define.WEB3ChannelDef;
import webbroker3.gentrade.WEB3GentradeBizDate;
import webbroker3.gentrade.WEB3GentradeCommission;
import webbroker3.gentrade.WEB3GentradeSubAccount;
import webbroker3.gentrade.WEB3GentradeTradingClendarContext;
import webbroker3.gentrade.WEB3GentradeTradingTimeManagementForMock;
import webbroker3.gentrade.data.EnableOrderConditionParams;
import webbroker3.gentrade.data.EnableOrderConditionRow;
import webbroker3.gentrade.data.TradingTimeParams;
import webbroker3.gentrade.data.TradingTimeRow;
import webbroker3.ifo.WEB3IfoChangeSettleContractOrderSpec;
import webbroker3.ifo.WEB3IfoEstimateDeliveryAmountCalcResult;
import webbroker3.ifo.WEB3IfoTradedProductImpl;
import webbroker3.ifo.message.WEB3FuturesCancelCompleteRequest;
import webbroker3.ifo.message.WEB3FuturesCancelConfirmRequest;
import webbroker3.ifo.message.WEB3FuturesCancelConfirmResponse;
import webbroker3.ifo.message.WEB3FuturesOptionsCloseMarginContractUnit;
import webbroker3.mock.TestBaseForMock;
import webbroker3.util.WEB3DateUtility;
import webbroker3.util.WEB3LogUtility;

public class WEB3FuturesCancelOrderServiceImplTest extends TestBaseForMock
{
    /**
     * ログユーティリティ
     */
    private static WEB3LogUtility log = WEB3LogUtility.getInstance(WEB3FuturesCancelOrderServiceImplTest.class);

    public WEB3FuturesCancelOrderServiceImplTest(String arg0)
    {
        super(arg0);
    }

    protected void setUp() throws Exception
    {
        super.setUp();
        MOCK_MANAGER.setIsMockUsed(true);
    }

    protected void tearDown() throws Exception
    {
        super.tearDown();
    }
    public static String TRADING_CAL_CONTEXT_PATH = "web3.tradingcalendarcontext";
    public class WEB3FuturesCancelConfirmRequestForMock 
        extends WEB3FuturesCancelConfirmRequest
    {
        public void validate() throws WEB3BaseException 
        {
            final String STR_METHOD_NAME = "WEB3FuturesCancelConfirmRequestForMock + validate()";
            log.entering(STR_METHOD_NAME);
                      
            log.exiting(STR_METHOD_NAME);
        }
    }
    /*
     * Test method for 'webbroker3.ifo.service.delegate.stdimpls.WEB3FuturesCancelOrderServiceImpl.validateOrder(WEB3FuturesCancelConfirmRequest)'
     */
    public void testValidateOrderCase1()
    {
        final String STR_METHOD_NAME = "testValidateOrderCase1()";
        log.entering(STR_METHOD_NAME);
        IfoOrderParams l_orderParams = TestDBUtility.getIfoOrderRow();
        l_orderParams.setOrderId(1001);
        
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
        
        ProductParams l_productParams = TestDBUtility.getProductRow();
        l_productParams.setProductId(3304148080000L);
        l_productParams.setProductType(ProductTypeEnum.IFO);
        
        IfoProductParams l_ifoProductParams = TestDBUtility.getIfoProductRow();
        l_ifoProductParams.setProductId(3304148080000L);
        l_ifoProductParams.setProductType(ProductTypeEnum.IFO);
        
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
            
            TestDBUtility.deleteAll(IfoOrderRow.TYPE);
            TestDBUtility.insertWithDel(l_orderParams);
            
            TestDBUtility.deleteAll(MarketRow.TYPE);
            TestDBUtility.insertWithDel(l_marketParams);
            
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
            TestDBUtility.commit();
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
            
            OrderValidationResult l_result = new OrderValidationResult(ProcessingResult.SUCCESS_RESULT);
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                    "webbroker3.ifo.WEB3FuturesOrderManagerImpl",
                    "validateFuturesChangeSettleContractOrder",
                    new Class[] {WEB3GentradeSubAccount.class, WEB3IfoChangeSettleContractOrderSpec.class, boolean.class},
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
            
            WEB3IfoTradedProductImpl l_tradedProduct = new WEB3IfoTradedProductImpl(330304148080000L);
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                    "webbroker3.ifo.WEB3IfoProductManagerImpl",
                    "getIfoTradedProduct",
                    new Class[] { Institution.class, String.class, String.class, String.class,
                            IfoDerivativeTypeEnum.class, double.class, String.class, String.class },
                            l_tradedProduct);
                    
            WEB3GentradeTradingClendarContext l_clendarContext =
                (WEB3GentradeTradingClendarContext)
                    ThreadLocalSystemAttributesRegistry.getAttribute(TRADING_CAL_CONTEXT_PATH);
            l_clendarContext.setInstitutionCode("0D");
            l_clendarContext.setProductCode("0005");
            l_clendarContext.setMarketCode("0");
            l_clendarContext.setBizDateType("1");
            
            Calendar ca1 =  Calendar.getInstance();
            ca1.set(2007,6-1,19);
            
            Date date1 = ca1.getTime();
            WEB3GentradeTradingTimeManagementForMock.mockGetOrderBizDate(date1);
            
            WEB3OptionClientRequestServiceForMock l_impl =
                new WEB3OptionClientRequestServiceForMock();
            WEB3FuturesCancelConfirmRequestForMock l_request =
                new WEB3FuturesCancelConfirmRequestForMock();
            l_request.id = "1001";
            
            WEB3FuturesCancelConfirmResponse l_response =
                l_impl.validateOrder(l_request);
            assertNull(l_response.expirationDate);
        }
        catch (Exception l_ex)
        {
            log.error(l_ex.getMessage(), l_ex);
            fail();
        }
        log.exiting(STR_METHOD_NAME);
    }
    
    public void testValidateOrderCase2()
    {
        final String STR_METHOD_NAME = "testValidateOrderCase2()";
        log.entering(STR_METHOD_NAME);
        IfoOrderParams l_orderParams = TestDBUtility.getIfoOrderRow();
        l_orderParams.setOrderId(1001);
        IfoOrderUnitParams l_orderUnitParams = TestDBUtility.getIfoOrderUnitRow();
        l_orderUnitParams.setOrderId(1001);
        l_orderUnitParams.setFirstOrderUnitId(1001);
        l_orderUnitParams.setEveningSessionCarryoverFlag(BooleanEnum.FALSE);
        l_orderUnitParams.setMarketId(3303L);
        l_orderUnitParams.setProductId(3304148080000L);
        l_orderUnitParams.setOrderCateg(OrderCategEnum.IDX_FUTURES_CLOSE);
        l_orderUnitParams.setBizDate("20070630");
        l_orderUnitParams.setOrderType(OrderTypeEnum.IDX_FUTURES_BUY_TO_CLOSE);
        l_orderUnitParams.setSessionType("1");
        l_orderUnitParams.setExpirationDate(WEB3DateUtility.getDate("20060702","yyyyMMdd"));
        
        MarketParams l_marketParams = TestDBUtility.getMarketRow();
        l_marketParams.setMarketId(3303L);
        
        ProductParams l_productParams = TestDBUtility.getProductRow();
        l_productParams.setProductId(3304148080000L);
        l_productParams.setProductType(ProductTypeEnum.IFO);
        
        IfoProductParams l_ifoProductParams = TestDBUtility.getIfoProductRow();
        l_ifoProductParams.setProductId(3304148080000L);
        l_ifoProductParams.setProductType(ProductTypeEnum.IFO);
        
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
            
            TestDBUtility.deleteAll(IfoOrderRow.TYPE);
            TestDBUtility.insertWithDel(l_orderParams);
            
            TestDBUtility.deleteAll(MarketRow.TYPE);
            TestDBUtility.insertWithDel(l_marketParams);
            
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
            TestDBUtility.commit();
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
            
            OrderValidationResult l_result = new OrderValidationResult(ProcessingResult.SUCCESS_RESULT);
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                    "webbroker3.ifo.WEB3FuturesOrderManagerImpl",
                    "validateFuturesChangeSettleContractOrder",
                    new Class[] {WEB3GentradeSubAccount.class, WEB3IfoChangeSettleContractOrderSpec.class, boolean.class},
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
            
            WEB3IfoTradedProductImpl l_tradedProduct = new WEB3IfoTradedProductImpl(330304148080000L);
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                    "webbroker3.ifo.WEB3IfoProductManagerImpl",
                    "getIfoTradedProduct",
                    new Class[] { Institution.class, String.class, String.class, String.class,
                            IfoDerivativeTypeEnum.class, double.class, String.class, String.class },
                            l_tradedProduct);
            WEB3GentradeTradingClendarContext l_clendarContext =
                (WEB3GentradeTradingClendarContext)
                    ThreadLocalSystemAttributesRegistry.getAttribute(TRADING_CAL_CONTEXT_PATH);
            l_clendarContext.setInstitutionCode("0D");
            l_clendarContext.setProductCode("0005");
            l_clendarContext.setMarketCode("0");
            l_clendarContext.setBizDateType("1");
            
            Calendar ca1 =  Calendar.getInstance();
            ca1.set(2007,6-1,19);
            
            Date date1 = ca1.getTime();
            WEB3GentradeTradingTimeManagementForMock.mockGetOrderBizDate(date1);
            
            WEB3OptionClientRequestServiceForMock l_impl =
                new WEB3OptionClientRequestServiceForMock();
            WEB3FuturesCancelConfirmRequestForMock l_request =
                new WEB3FuturesCancelConfirmRequestForMock();
            l_request.id = "1001";
            
            WEB3FuturesCancelConfirmResponse l_response =
                l_impl.validateOrder(l_request);
            assertEquals(WEB3DateUtility.formatDate(l_response.expirationDate, "yyyyMMdd"), "20060702");
        }
        catch (Exception l_ex)
        {
            log.error(l_ex.getMessage(), l_ex);
            fail();
        }
        log.exiting(STR_METHOD_NAME);
    }
    
    /**
     * 約注文確認要（is予約注文確認要()==true）の場合
     *
     */
    public void testSubmitOrder_C0001()
    {
        final String STR_METHOD_NAME = "testSubmitOrder_C0001()";
        log.entering(STR_METHOD_NAME);
        try
        {
            MOCK_MANAGER.setIsMockUsed(true);
            
            TestDBUtility.deleteAll(IfoOrderUnitRow.TYPE);
            IfoOrderUnitParams l_ifoOrderUnitParams = TestDBUtility.getIfoOrderUnitRow();
            l_ifoOrderUnitParams.setReserveOrderExistFlag("1");
            TestDBUtility.insertWithDel(l_ifoOrderUnitParams);
            
            OrderValidationResult l_orderResult = new OrderValidationResult(ProcessingResult.newSuccessResultInstance());
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                    "webbroker3.ifo.WEB3FuturesOrderManagerImpl", "validateFuturesCancelOrder", new Class[]
                    { WEB3GentradeSubAccount.class, CancelOrderSpec.class },
                    l_orderResult);
            
            OrderSubmissionResult l_sResult = new OrderSubmissionResult(ProcessingResult.newSuccessResultInstance());
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                    "webbroker3.ifo.WEB3FuturesOrderManagerImpl",
                    "submitCancelOrder", 
                    new Class[]
                    {SubAccount.class, CancelOrderSpec.class, String.class, boolean.class},
                    l_sResult);
                    
            WEB3FuturesCancelCompleteRequest l_request = new WEB3FuturesCancelCompleteRequestForTest();
            l_request.id="1001";
            WEB3OptionClientRequestServiceForMock l_service = new WEB3OptionClientRequestServiceForMock();
            l_service.submitOrder(l_request);
        }
        catch (Exception l_ex)
        {
            log.error(l_ex.getMessage(), l_ex);
            fail();
        }
        log.exiting(STR_METHOD_NAME);
    }
    
    /**
     * 約注文確認要（is予約注文確認要()==false）の場合
     *
     */
    public void testSubmitOrder_C0002()
    {
        final String STR_METHOD_NAME = "testSubmitOrder_C0002()";
        log.entering(STR_METHOD_NAME);
        try
        {
            MOCK_MANAGER.setIsMockUsed(true);
            
            TestDBUtility.deleteAll(IfoOrderUnitRow.TYPE);
            IfoOrderUnitParams l_ifoOrderUnitParams = TestDBUtility.getIfoOrderUnitRow();
            TestDBUtility.insertWithDel(l_ifoOrderUnitParams);
            
            OrderValidationResult l_orderResult = new OrderValidationResult(ProcessingResult.newSuccessResultInstance());
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                    "webbroker3.ifo.WEB3FuturesOrderManagerImpl", "validateFuturesCancelOrder", new Class[]
                    { WEB3GentradeSubAccount.class, CancelOrderSpec.class },
                    l_orderResult);
            
            OrderSubmissionResult l_sResult = new OrderSubmissionResult(ProcessingResult.newSuccessResultInstance());
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                    "webbroker3.ifo.WEB3FuturesOrderManagerImpl",
                    "submitCancelOrder", 
                    new Class[]
                    {SubAccount.class, CancelOrderSpec.class, String.class, boolean.class},
                    l_sResult);
                    
            WEB3FuturesCancelCompleteRequest l_request = new WEB3FuturesCancelCompleteRequestForTest();
            l_request.id="1001";
            WEB3OptionClientRequestServiceForMock l_service = new WEB3OptionClientRequestServiceForMock();
            l_service.submitOrder(l_request);
            assertTrue(true);
        }
        catch (Exception l_ex)
        {
            log.error(l_ex.getMessage(), l_ex);
            fail();
        }
        log.exiting(STR_METHOD_NAME);
    }

    private class WEB3OptionClientRequestServiceForMock extends WEB3FuturesCancelOrderServiceImpl
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
    
    private class WEB3FuturesCancelCompleteRequestForTest extends WEB3FuturesCancelCompleteRequest
    {
        public void validate() throws WEB3BusinessLayerException 
        {
            
        }
    }
}
@
