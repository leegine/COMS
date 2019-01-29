head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.04.07.01.00.10;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	5444d9d078c63f4;
filename	WEB3FuturesOrderManagerImplTest.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : 先物注文マネージャクラステスト(WEB3FuturesOrderManagerImplTest.java)
Author Name      : Daiwa Institute of Research
Revision History : 2007/02/03 趙林鵬 (中訊) 新規作成
*/

package webbroker3.ifo;

import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.Date;

import com.fitechlabs.xtrade.kernel.boot.Services;
import com.fitechlabs.xtrade.kernel.data.DataNetworkException;
import com.fitechlabs.xtrade.kernel.data.DataQueryException;
import com.fitechlabs.xtrade.kernel.util.ThreadLocalSystemAttributesRegistry;
import com.fitechlabs.xtrade.plugin.tc.gentrade.FinApp;
import com.fitechlabs.xtrade.plugin.tc.gentrade.GtlUtils;
import com.fitechlabs.xtrade.plugin.tc.gentrade.Order;
import com.fitechlabs.xtrade.plugin.tc.gentrade.OrderTypeEnum;
import com.fitechlabs.xtrade.plugin.tc.gentrade.OrderUnit;
import com.fitechlabs.xtrade.plugin.tc.gentrade.Product;
import com.fitechlabs.xtrade.plugin.tc.gentrade.ProductTypeEnum;
import com.fitechlabs.xtrade.plugin.tc.gentrade.SideEnum;
import com.fitechlabs.xtrade.plugin.tc.gentrade.SubAccount;
import com.fitechlabs.xtrade.plugin.tc.gentrade.TradedProduct;
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
import com.fitechlabs.xtrade.plugin.tc.gentrade.data.TradedProductCalendarRow;
import com.fitechlabs.xtrade.plugin.tc.gentrade.data.TradedProductParams;
import com.fitechlabs.xtrade.plugin.tc.gentrade.data.TradedProductRow;
import com.fitechlabs.xtrade.plugin.tc.gentrade.ordersubmitter.io.NewOrderValidationResult;
import com.fitechlabs.xtrade.plugin.tc.gentrade.ordersubmitter.io.OrderValidationResult;
import com.fitechlabs.xtrade.plugin.tc.gentrade.ordersubmitter.io.SettleContractEntry;
import com.fitechlabs.xtrade.plugin.tc.xbifo.IfoDerivativeTypeEnum;
import com.fitechlabs.xtrade.plugin.tc.xbifo.IfoOrderExecutionConditionType;
import com.fitechlabs.xtrade.plugin.tc.xbifo.IfoOrderUnit;
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
import webbroker3.common.WEB3BaseRuntimeException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.common.WEB3SystemLayerException;
import webbroker3.gentrade.WEB3GentradeBizDate;
import webbroker3.gentrade.WEB3GentradeCommission;
import webbroker3.gentrade.WEB3GentradeMarket;
import webbroker3.gentrade.WEB3GentradeSubAccount;
import webbroker3.gentrade.WEB3GentradeTradingClendarContext;
import webbroker3.gentrade.WEB3GentradeTradingTimeManagementForMock;
import webbroker3.gentrade.data.OrderexecutionEndParams;
import webbroker3.gentrade.data.OrderexecutionEndRow;
import webbroker3.mock.TestBaseForMock;
import webbroker3.mock.util.WEB3MockObjectParamsValue;
import webbroker3.util.WEB3DateUtility;
import webbroker3.util.WEB3LogUtility;

/**
 * （先物注文マネージャクラステスト）<BR>
 *
 * @@author 趙林鵬
 * @@version 1.0
 */

public class WEB3FuturesOrderManagerImplTest extends TestBaseForMock
{
    /**
     * ログユーティリティ<BR>
     */
    private static WEB3LogUtility log =
         WEB3LogUtility.getInstance(WEB3FuturesOrderManagerImplTest.class);
    
    public WEB3FuturesOrderManagerImplTest(String arg0)
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
    
    public static String TRADING_CAL_CONTEXT_PATH = "web3.tradingcalendarcontext";
    
    public void test_validateFuturesChangeSettleContractOrder_C0001()
    {
        final String STR_METHOD_NAME = " test_validateFuturesChangeSettleContractOrder_C0001";
        log.entering(TEST_START + STR_METHOD_NAME);
        
        MOCK_MANAGER.setIsMockUsed(true);

        TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
            "webbroker3.ifo.WEB3FuturesOrderManagerImpl",
            "validateOrder",
            new Class[] {SubAccount.class,String.class},
            null);
        
        TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
            "webbroker3.ifo.WEB3IfoOrderManagerReusableValidations",
            "validateOrderForChangeability",
            new Class[] {Order.class, boolean.class},
            null);
        
        TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
            "webbroker3.ifo.WEB3IfoOrderManagerReusableValidations",
            "validateHandlingIndex",
            new Class[]{String.class, WEB3IfoTradedProductImpl.class},
            null);
        
        TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
            "webbroker3.ifo.WEB3IfoOrderManagerReusableValidations",
            "validateOrderCond",
            new Class[]{
                WEB3GentradeSubAccount.class,
                long.class, boolean.class, 
                WEB3IfoTradedProductImpl.class, boolean.class,
                boolean.class, Date.class, Date.class, String.class, 
                IfoOrderExecutionConditionType.class, String.class,Long.class},
                null);
        
        TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
            "webbroker3.ifo.WEB3IfoOrderManagerReusableValidations",
            "validateOrderUnitPrice",
            new Class[] {double.class, WEB3IfoTradedProductImpl.class, SubAccount.class},
            null);
        
        TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
            "webbroker3.ifo.WEB3IfoOrderManagerReusableValidations",
            "validateWLimitPriceOrder",
            new Class[]{
                WEB3GentradeSubAccount.class,
                long.class, double.class, 
                String.class, double.class,
                String.class,
                IfoOrderExecutionConditionType.class, String.class,
                WEB3IfoTradedProductImpl.class,
                boolean.class, boolean.class},
                null);
        
        TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
            "webbroker3.ifo.WEB3IfoOrderManagerReusableValidations",
            "validateOrderChangeSpec",
            new Class[]{
                OrderUnit.class,
                double.class, double.class, 
                IfoOrderExecutionConditionType.class, String.class,
                String.class, String.class, double.class, double.class, 
                IfoOrderExecutionConditionType.class, Date.class, String.class,
                SettleContractEntry[].class},
                null);
        
        TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
            "webbroker3.ifo.WEB3IfoOrderManagerReusableValidations",
            "validateChangeOrderRevLimit",
            new Class[]{
                IfoOrderUnit.class,
                double.class, double.class, 
                IfoOrderExecutionConditionType.class},
                null);

        FinApp l_finApp = (FinApp) Services.getService(FinApp.class);

        try
        {
            Date l_datExpect = WEB3DateUtility.getDate("20040710","yyyyMMdd");
            WEB3GentradeTradingTimeManagementForMock.mockGetOrderBizDate(l_datExpect);
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                    "com.fitechlabs.xtrade.plugin.tc.gentrade.stdimpls.TradingSystemImpl",
                    "getSystemTimestamp",
                    new Class[] {},
                    new Timestamp(l_datExpect.getTime()));
            
            WEB3FuturesOrderManagerImpl l_futuresOrderManager = 
                (WEB3FuturesOrderManagerImpl)l_finApp.getTradingModule(ProductTypeEnum.IFO).getOrderManager();;
 
            MainAccountParams l_mainAccountParams = TestDBUtility.getMainAccountRow();
            SubAccountParams l_subAccountParams = TestDBUtility.getSubAccountRow();
            IfoOrderUnitParams l_ifoOrderUnitParams = TestDBUtility.getIfoOrderUnitRow();
            IfoOrderParams l_ifoOrderParams = TestDBUtility.getIfoOrderRow();
            ProductParams l_productParams = TestDBUtility.getProductRow();
            IfoProductParams l_ifoProductParams = TestDBUtility.getIfoProductRow();
            MarketParams l_marketParams = TestDBUtility.getMarketRow();
            IfoContractParams l_ifoContractParams = TestDBUtility.getIfoContractRow();
            IfoTradedProductParams l_ifoTradedProductParams = TestDBUtility.getIfoTradedProductRow();
            BranchParams l_branchParams = TestDBUtility.getBranchRow();
            InstitutionParams l_institutionParams = TestDBUtility.getInstitutionRow();
            TradedProductParams l_tradedProductParams = TestDBUtility.getTradedProductRow();
            IfoTradedProductUpdqParams l_ifoTradedProductUpdqParams = TestDBUtility.getIfoTradedProductUpdqRow();

            l_mainAccountParams.setAccountId(101001010010L);

            l_subAccountParams.setAccountId(101001010010L);
            l_subAccountParams.setSubAccountId(10100101001007L);
     
            l_ifoOrderParams.setOrderId(1001);
            l_ifoOrderParams.setAccountId(101001010010L);
            l_ifoOrderParams.setSubAccountId(10100101001007L);
            l_ifoOrderParams.setProductType(ProductTypeEnum.IFO);
            l_ifoOrderParams.setCreatedTimestamp(WEB3DateUtility.getDate("20070101","yyyyMMdd"));
            l_ifoOrderParams.setLastUpdatedTimestamp(WEB3DateUtility.getDate("20070101","yyyyMMdd"));
            
            l_ifoOrderUnitParams.setOrderRootDiv("9");
            
            l_productParams.setProductId(1006169090018L);
            l_productParams.setProductType(ProductTypeEnum.IFO);
            
            l_ifoProductParams.setProductId(1006169090018L);
            l_ifoProductParams.setProductType(ProductTypeEnum.IFO);
            l_ifoProductParams.setInstitutionCode("0D");
            l_ifoProductParams.setUnderlyingProductCode("1");
            l_ifoProductParams.setDerivativeType(IfoDerivativeTypeEnum.FUTURES);
            l_ifoProductParams.setStrikePrice(11);
            l_ifoProductParams.setMonthOfDelivery("1111");
            l_ifoProductParams.setCreatedTimestamp(WEB3DateUtility.getDate("20070202", "yyyyMMdd"));
            l_ifoProductParams.setLastUpdatedTimestamp(WEB3DateUtility.getDate("20070202", "yyyyMMdd"));
            
            l_marketParams.setMarketId(1002);

            l_ifoContractParams.setAccountId(101001010010L);
            l_ifoContractParams.setSubAccountId(10100101001007L);
            l_ifoContractParams.setProductId(1006169090018L);
            l_ifoContractParams.setDeliveryDate(WEB3DateUtility.getDate("20070202", "yyyyMMdd"));
            
            l_ifoTradedProductParams.setInstitutionCode("0D");
            l_ifoTradedProductParams.setMarketId(1002);
            l_ifoTradedProductParams.setProductId(1006169090018L);
            
            l_tradedProductParams.setProductId(1006169090018L);
            l_tradedProductParams.setMarketId(1002);
            l_tradedProductParams.setProductType(ProductTypeEnum.IFO);
            
            l_ifoTradedProductUpdqParams.setProductId(1006169090018L);
            l_ifoTradedProductUpdqParams.setInstitutionCode("0D");
            l_ifoTradedProductUpdqParams.setMarketId(1002);
            SimpleDateFormat l_format = GtlUtils.getThreadSafeSimpleDateFormat("yyyyMMdd");

            String l_strCreateDate = l_format.format(l_datExpect);
            l_ifoTradedProductUpdqParams.setValidForBizDate(l_strCreateDate); 
            
            l_ifoTradedProductUpdqParams.setStartTradingDate(WEB3DateUtility.getDate("20040701","yyyyMMdd"));
            l_ifoTradedProductUpdqParams.setLastTradingDate(WEB3DateUtility.getDate("20040720","yyyyMMdd"));
            l_ifoTradedProductUpdqParams.setListDate(WEB3DateUtility.getDate("20040701","yyyyMMdd"));
            l_ifoTradedProductUpdqParams.setUnlistedDate(WEB3DateUtility.getDate("20040720","yyyyMMdd"));
            
            OrderexecutionEndParams l_orderexecutionEndParams = new OrderexecutionEndParams();
            l_orderexecutionEndParams.setInstitutionCode("0D");
            l_orderexecutionEndParams.setProductType(ProductTypeEnum.IFO);
            l_orderexecutionEndParams.setFutureOptionDiv("1");
            l_orderexecutionEndParams.setCreatedTimestamp(WEB3DateUtility.getDate("20070202", "yyyyMMdd"));
            l_orderexecutionEndParams.setLastUpdatedTimestamp(WEB3DateUtility.getDate("20070202", "yyyyMMdd"));
            l_orderexecutionEndParams.setOrderexecutionEndType("0");
            
            this.deleteAll();
            TestDBUtility.insertWithDel(l_ifoOrderUnitParams);
            TestDBUtility.insertWithDel(l_subAccountParams);
            TestDBUtility.insertWithDel(l_mainAccountParams);
            TestDBUtility.insertWithDel(l_ifoOrderParams);
            TestDBUtility.insertWithDel(l_productParams);
            TestDBUtility.insertWithDel(l_ifoProductParams);
            TestDBUtility.insertWithDel(l_marketParams);
            TestDBUtility.insertWithDel(l_ifoContractParams);
            TestDBUtility.insertWithDel(l_ifoTradedProductParams);
            TestDBUtility.insertWithDel(l_branchParams);
            TestDBUtility.insertWithDel(l_institutionParams);
            TestDBUtility.insertWithDel(l_tradedProductParams);
            TestDBUtility.insertWithDel(l_ifoTradedProductUpdqParams);
            TestDBUtility.insertWithDel(l_orderexecutionEndParams);
            
            WEB3IfoTradedProductImpl l_tradedProductImpl = new WEB3IfoTradedProductImpl(1006160060005L);
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                    "webbroker3.ifo.WEB3IfoOrderManagerReusableValidations",
                    "validateTradedProduct",
                    new Class[] {WEB3IfoProductImpl.class,
                            WEB3GentradeMarket.class,
                            boolean.class,
                            boolean.class},
                    l_tradedProductImpl);
             
            WEB3GentradeSubAccount l_subAccount = new WEB3GentradeSubAccount(101001010010L, 10100101001007L);

            SettleContractEntry[] l_SettleContractEntry = new SettleContractEntry[1];
            l_SettleContractEntry[0] = new SettleContractEntry(1001, 200);
            
            WEB3IfoChangeSettleContractOrderSpec l_changeSettleContractOrderSpec =
                new WEB3IfoChangeSettleContractOrderSpec(
                    1001, 1001, 200, l_SettleContractEntry);
            
            boolean l_blnIsSkipDelayStatusCheck = false;

            OrderValidationResult l_orderValidationResult = l_futuresOrderManager.validateFuturesChangeSettleContractOrder(
                l_subAccount,
                l_changeSettleContractOrderSpec,
                l_blnIsSkipDelayStatusCheck);
            
            log.debug("l_orderValidationResult=============" + l_orderValidationResult); 
            assertTrue(l_orderValidationResult.getProcessingResult().isSuccessfulResult());
        }
        catch (Exception l_ex)
        {
            log.error(l_ex.getMessage(), l_ex);
            fail();
        }
        log.info(STR_METHOD_NAME + ">>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>test pass !!");
        log.exiting(TEST_END + STR_METHOD_NAME);
    }
    
    public void test_validateFuturesChangeSettleContractOrder_C0002()
    {
        final String STR_METHOD_NAME = " test_validateFuturesChangeSettleContractOrder_C0002";
        log.entering(TEST_START + STR_METHOD_NAME);
        
        MOCK_MANAGER.setIsMockUsed(true);
        
        TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
            "webbroker3.ifo.WEB3FuturesOrderManagerImpl",
            "validateOrder",
            new Class[] {SubAccount.class,String.class},
            null);
        
        TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
            "webbroker3.ifo.WEB3IfoOrderManagerReusableValidations",
            "validateOrderForChangeability",
            new Class[] {Order.class, boolean.class},
            null);
        
        TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
            "webbroker3.ifo.WEB3IfoOrderManagerReusableValidations",
            "validateHandlingIndex",
            new Class[]{String.class, WEB3IfoTradedProductImpl.class},
            null);
        
        TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
            "webbroker3.ifo.WEB3IfoOrderManagerReusableValidations",
            "validateOrderCond",
            new Class[]{
                WEB3GentradeSubAccount.class,
                long.class, boolean.class, 
                WEB3IfoTradedProductImpl.class, boolean.class,
                boolean.class, Date.class, Date.class, String.class, 
                IfoOrderExecutionConditionType.class, String.class,Long.class},
                null);
        
        TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
            "webbroker3.ifo.WEB3IfoOrderManagerReusableValidations",
            "validateOrderUnitPrice",
            new Class[] {double.class, WEB3IfoTradedProductImpl.class, SubAccount.class},
            null);
        
        TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
            "webbroker3.ifo.WEB3IfoOrderManagerReusableValidations",
            "validateWLimitPriceOrder",
            new Class[]{
                WEB3GentradeSubAccount.class,
                long.class, double.class, 
                String.class, double.class,
                String.class,
                IfoOrderExecutionConditionType.class, String.class,
                WEB3IfoTradedProductImpl.class,
                boolean.class, boolean.class},
                null);
        
        TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
            "webbroker3.ifo.WEB3IfoOrderManagerReusableValidations",
            "validateOrderChangeSpec",
            new Class[]{
                OrderUnit.class,
                double.class, double.class, 
                IfoOrderExecutionConditionType.class, String.class,
                String.class, String.class, double.class, double.class, 
                IfoOrderExecutionConditionType.class, Date.class, String.class,
                SettleContractEntry[].class},
                null);
        
        TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
            "webbroker3.ifo.WEB3IfoOrderManagerReusableValidations",
            "validateChangeOrderRevLimit",
            new Class[]{
                IfoOrderUnit.class,
                double.class, double.class, 
                IfoOrderExecutionConditionType.class},
                null);



        FinApp l_finApp = (FinApp) Services.getService(FinApp.class);

        try
        {
            Date l_datExpect = WEB3DateUtility.getDate("20040710","yyyyMMdd");
            WEB3GentradeTradingTimeManagementForMock.mockGetOrderBizDate(l_datExpect);
            
            WEB3FuturesOrderManagerImpl l_futuresOrderManager = 
                (WEB3FuturesOrderManagerImpl)l_finApp.getTradingModule(ProductTypeEnum.IFO).getOrderManager();;
                      
            MainAccountParams l_mainAccountParams = TestDBUtility.getMainAccountRow();
            SubAccountParams l_subAccountParams = TestDBUtility.getSubAccountRow();
            IfoOrderUnitParams l_ifoOrderUnitParams = TestDBUtility.getIfoOrderUnitRow();
            IfoOrderParams l_ifoOrderParams = TestDBUtility.getIfoOrderRow();
            ProductParams l_productParams = TestDBUtility.getProductRow();
            IfoProductParams l_ifoProductParams = TestDBUtility.getIfoProductRow();
            MarketParams l_marketParams = TestDBUtility.getMarketRow();
            IfoContractParams l_ifoContractParams = TestDBUtility.getIfoContractRow();
            IfoTradedProductParams l_ifoTradedProductParams = TestDBUtility.getIfoTradedProductRow();
            l_ifoTradedProductParams.setValidForBizDate(null);
            BranchParams l_branchParams = TestDBUtility.getBranchRow();
            InstitutionParams l_institutionParams = TestDBUtility.getInstitutionRow();
            TradedProductParams l_tradedProductParams = TestDBUtility.getTradedProductRow();
            IfoTradedProductUpdqParams l_ifoTradedProductUpdqParams = TestDBUtility.getIfoTradedProductUpdqRow();

            l_mainAccountParams.setAccountId(101001010010L);

            l_subAccountParams.setAccountId(101001010010L);
            l_subAccountParams.setSubAccountId(10100101001007L);
     
            l_ifoOrderParams.setOrderId(1001);
            l_ifoOrderParams.setAccountId(101001010010L);
            l_ifoOrderParams.setSubAccountId(10100101001007L);
            l_ifoOrderParams.setProductType(ProductTypeEnum.IFO);
            l_ifoOrderParams.setCreatedTimestamp(WEB3DateUtility.getDate("20070101","yyyyMMdd"));
            l_ifoOrderParams.setLastUpdatedTimestamp(WEB3DateUtility.getDate("20070101","yyyyMMdd"));
            
            l_ifoOrderUnitParams.setOrderRootDiv("9");
            l_ifoOrderUnitParams.setBizDate("20061010");
            
            l_productParams.setProductId(1006169090018L);
            l_productParams.setProductType(ProductTypeEnum.IFO);
            
            l_ifoProductParams.setProductId(1006169090018L);
            l_ifoProductParams.setProductType(ProductTypeEnum.IFO);
            l_ifoProductParams.setInstitutionCode("0D");
            l_ifoProductParams.setUnderlyingProductCode("1");
            l_ifoProductParams.setDerivativeType(IfoDerivativeTypeEnum.FUTURES);
            l_ifoProductParams.setStrikePrice(11);
            l_ifoProductParams.setMonthOfDelivery("1111");
            l_ifoProductParams.setCreatedTimestamp(WEB3DateUtility.getDate("20070202", "yyyyMMdd"));
            l_ifoProductParams.setLastUpdatedTimestamp(WEB3DateUtility.getDate("20070202", "yyyyMMdd"));
            
            l_marketParams.setMarketId(1002);

            l_ifoContractParams.setAccountId(101001010010L);
            l_ifoContractParams.setSubAccountId(10100101001007L);
            l_ifoContractParams.setProductId(1006169090018L);
            
            l_ifoTradedProductParams.setInstitutionCode("0D");
            l_ifoTradedProductParams.setMarketId(1002);
            l_ifoTradedProductParams.setProductId(1006169090018L);
            l_ifoContractParams.setDeliveryDate(WEB3DateUtility.getDate("20070202", "yyyyMMdd"));
            
            l_tradedProductParams.setProductId(1006169090018L);
            l_tradedProductParams.setMarketId(1002);
            l_tradedProductParams.setProductType(ProductTypeEnum.IFO);
            
            l_ifoTradedProductUpdqParams.setProductId(1006169090018L);
            l_ifoTradedProductUpdqParams.setInstitutionCode("0D");
            l_ifoTradedProductUpdqParams.setMarketId(1002);            
            SimpleDateFormat l_format = GtlUtils.getThreadSafeSimpleDateFormat("yyyyMMdd");
            Date l_datBizDate = GtlUtils.getTradingSystem().getSystemTimestamp();
            Date l_datpreBizDate = new WEB3GentradeBizDate(
                new Timestamp(l_datBizDate.getTime())).roll(1);
            String l_strCreateDate = l_format.format(l_datpreBizDate);
            l_ifoTradedProductUpdqParams.setValidForBizDate(l_strCreateDate);
            
            l_ifoTradedProductUpdqParams.setStartTradingDate(WEB3DateUtility.getDate("20040701","yyyyMMdd"));
            l_ifoTradedProductUpdqParams.setLastTradingDate(WEB3DateUtility.getDate("20040720","yyyyMMdd"));
            l_ifoTradedProductUpdqParams.setListDate(WEB3DateUtility.getDate("20040701","yyyyMMdd"));
            l_ifoTradedProductUpdqParams.setUnlistedDate(WEB3DateUtility.getDate("20040720","yyyyMMdd"));
            
            this.deleteAll();
            TestDBUtility.insertWithDel(l_ifoOrderUnitParams);
            TestDBUtility.insertWithDel(l_subAccountParams);
            TestDBUtility.insertWithDel(l_mainAccountParams);
            TestDBUtility.insertWithDel(l_ifoOrderParams);
            TestDBUtility.insertWithDel(l_productParams);
            TestDBUtility.insertWithDel(l_ifoProductParams);
            TestDBUtility.insertWithDel(l_marketParams);
            TestDBUtility.insertWithDel(l_ifoContractParams);
            TestDBUtility.insertWithDel(l_ifoTradedProductParams);
            TestDBUtility.insertWithDel(l_branchParams);
            TestDBUtility.insertWithDel(l_institutionParams);
            TestDBUtility.insertWithDel(l_tradedProductParams);
            TestDBUtility.insertWithDel(l_ifoTradedProductUpdqParams);
            
            WEB3IfoTradedProductImpl l_tradedProduct = new WEB3IfoTradedProductImpl(1006160060005L);
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue("webbroker3.ifo.WEB3IfoOrderManagerReusableValidations",
                    "validateTradedProduct", 
                    new Class[] { WEB3IfoProductImpl.class, 
                    WEB3GentradeMarket.class, boolean.class, boolean.class },
                    l_tradedProduct);
            
            WEB3GentradeSubAccount l_subAccount = new WEB3GentradeSubAccount(101001010010L, 10100101001007L);

            SettleContractEntry[] l_SettleContractEntry = new SettleContractEntry[1];
            l_SettleContractEntry[0] = new SettleContractEntry(1001, 200);
            
            WEB3IfoChangeSettleContractOrderSpec l_changeSettleContractOrderSpec =
                new WEB3IfoChangeSettleContractOrderSpec(
                    1001, 1001, 200, l_SettleContractEntry);
            
            boolean l_blnIsSkipDelayStatusCheck = false;

            OrderValidationResult l_orderValidationResult = l_futuresOrderManager.validateFuturesChangeSettleContractOrder(
                l_subAccount,
                l_changeSettleContractOrderSpec,
                l_blnIsSkipDelayStatusCheck);
            
            log.debug("l_orderValidationResult=============" + l_orderValidationResult); 
            assertTrue(l_orderValidationResult.getProcessingResult().isSuccessfulResult());
        }
        catch (Exception l_ex)
        {
            log.error(l_ex.getMessage(), l_ex);
            fail();
        }
        log.info(STR_METHOD_NAME + ">>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>test pass !!");
        log.exiting(TEST_END + STR_METHOD_NAME);
    }
    
    public void test_validateFuturesChangeSettleContractOrder_C0003()
    {
        final String STR_METHOD_NAME = " test_validateFuturesChangeSettleContractOrder_C0003";
        log.entering(TEST_START + STR_METHOD_NAME);
        
        MOCK_MANAGER.setIsMockUsed(true);
        
        TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
            "webbroker3.ifo.WEB3FuturesOrderManagerImpl",
            "validateOrder",
            new Class[] {SubAccount.class,String.class},
            null);

        FinApp l_finApp = (FinApp) Services.getService(FinApp.class);

        try
        {
            Date l_datExpect = WEB3DateUtility.getDate("20040710","yyyyMMdd");
            WEB3GentradeTradingTimeManagementForMock.mockGetOrderBizDate(l_datExpect);
            
            WEB3FuturesOrderManagerImpl l_futuresOrderManager = 
                (WEB3FuturesOrderManagerImpl)l_finApp.getTradingModule(ProductTypeEnum.IFO).getOrderManager();;
                      
            MainAccountParams l_mainAccountParams = TestDBUtility.getMainAccountRow();
            SubAccountParams l_subAccountParams = TestDBUtility.getSubAccountRow();
            IfoOrderUnitParams l_ifoOrderUnitParams = TestDBUtility.getIfoOrderUnitRow();
            IfoOrderParams l_ifoOrderParams = TestDBUtility.getIfoOrderRow();

            l_mainAccountParams.setAccountId(101001010010L);

            l_subAccountParams.setAccountId(101001010010L);
            l_subAccountParams.setSubAccountId(10100101001007L);
     
            l_ifoOrderParams.setOrderId(1002);
            l_ifoOrderParams.setAccountId(101001010010L);
            l_ifoOrderParams.setSubAccountId(10100101001007L);
            l_ifoOrderParams.setProductType(ProductTypeEnum.IFO);
            l_ifoOrderParams.setCreatedTimestamp(WEB3DateUtility.getDate("20070101","yyyyMMdd"));
            l_ifoOrderParams.setLastUpdatedTimestamp(WEB3DateUtility.getDate("20070101","yyyyMMdd"));
            
            l_ifoOrderUnitParams.setOrderRootDiv("9");
            l_ifoOrderUnitParams.setBizDate("20061010");
            
            this.deleteAll();
            TestDBUtility.insertWithDel(l_ifoOrderUnitParams);
            TestDBUtility.insertWithDel(l_subAccountParams);
            TestDBUtility.insertWithDel(l_mainAccountParams);
            TestDBUtility.insertWithDel(l_ifoOrderParams);
 
            WEB3GentradeSubAccount l_subAccount = new WEB3GentradeSubAccount(101001010010L, 10100101001007L);

            SettleContractEntry[] l_SettleContractEntry = new SettleContractEntry[1];
            l_SettleContractEntry[0] = new SettleContractEntry(1001, 200);
            
            WEB3IfoChangeSettleContractOrderSpec l_changeSettleContractOrderSpec =
                new WEB3IfoChangeSettleContractOrderSpec(
                    1002, 1001, 200, l_SettleContractEntry);
            
            boolean l_blnIsSkipDelayStatusCheck = false;

            OrderValidationResult l_orderValidationResult = l_futuresOrderManager.validateFuturesChangeSettleContractOrder(
                l_subAccount,
                l_changeSettleContractOrderSpec,
                l_blnIsSkipDelayStatusCheck);
            
            log.debug("l_orderValidationResult=============" + l_orderValidationResult); 
            
            assertEquals(l_orderValidationResult.getProcessingResult().getErrorInfo(),
                WEB3ErrorCatalog.SYSTEM_ERROR_80017);
        }
        catch (Exception l_ex)
        {
            log.error(l_ex.getMessage(), l_ex);
            fail();
        }
        log.info(STR_METHOD_NAME + ">>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>test pass !!");
        log.exiting(TEST_END + STR_METHOD_NAME);
    }

    public void test_validateFuturesChangeSettleContractOrder_C0004()
    {
        final String STR_METHOD_NAME = " test_validateFuturesChangeSettleContractOrder_C0004";
        log.entering(TEST_START + STR_METHOD_NAME);
        
        MOCK_MANAGER.setIsMockUsed(true);
        
        TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
            "webbroker3.ifo.WEB3FuturesOrderManagerImpl",
            "validateOrder",
            new Class[] {SubAccount.class,String.class},
            null);
        
        TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
            "webbroker3.ifo.WEB3IfoOrderManagerReusableValidations",
            "validateOrderForChangeability",
            new Class[] {Order.class, boolean.class},
            null);
        
        TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
            "webbroker3.ifo.WEB3IfoOrderManagerReusableValidations",
            "validateHandlingIndex",
            new Class[]{String.class, WEB3IfoTradedProductImpl.class},
            null);
        
        TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
            "webbroker3.ifo.WEB3IfoOrderManagerReusableValidations",
            "validateOrderCond",
            new Class[]{
                WEB3GentradeSubAccount.class,
                long.class, boolean.class, 
                WEB3IfoTradedProductImpl.class, boolean.class,
                boolean.class, Date.class, Date.class, String.class, 
                IfoOrderExecutionConditionType.class, String.class,Long.class},
                null);
        
        TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
            "webbroker3.ifo.WEB3IfoOrderManagerReusableValidations",
            "validateOrderUnitPrice",
            new Class[] {double.class, WEB3IfoTradedProductImpl.class, SubAccount.class},
            null);
        
        TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
            "webbroker3.ifo.WEB3IfoOrderManagerReusableValidations",
            "validateWLimitPriceOrder",
            new Class[]{
                WEB3GentradeSubAccount.class,
                long.class, double.class, 
                String.class, double.class,
                String.class,
                IfoOrderExecutionConditionType.class, String.class,
                WEB3IfoTradedProductImpl.class,
                boolean.class, boolean.class},
                null);
        
        TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
            "webbroker3.ifo.WEB3IfoOrderManagerReusableValidations",
            "validateOrderChangeSpec",
            new Class[]{
                OrderUnit.class,
                double.class, double.class, 
                IfoOrderExecutionConditionType.class, String.class,
                String.class, String.class, double.class, double.class, 
                IfoOrderExecutionConditionType.class, Date.class, String.class,
                SettleContractEntry[].class},
                null);
        
        TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
            "webbroker3.ifo.WEB3IfoOrderManagerReusableValidations",
            "validateChangeOrderRevLimit",
            new Class[]{
                IfoOrderUnit.class,
                double.class, double.class, 
                IfoOrderExecutionConditionType.class},
            new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80017, ""));

        FinApp l_finApp = (FinApp) Services.getService(FinApp.class);

        try
        {
            Date l_datExpect = WEB3DateUtility.getDate("20040710","yyyyMMdd");
            WEB3GentradeTradingTimeManagementForMock.mockGetOrderBizDate(l_datExpect);
            
            WEB3FuturesOrderManagerImpl l_futuresOrderManager = 
                (WEB3FuturesOrderManagerImpl)l_finApp.getTradingModule(ProductTypeEnum.IFO).getOrderManager();;
                      
            MainAccountParams l_mainAccountParams = TestDBUtility.getMainAccountRow();
            SubAccountParams l_subAccountParams = TestDBUtility.getSubAccountRow();
            IfoOrderUnitParams l_ifoOrderUnitParams = TestDBUtility.getIfoOrderUnitRow();
            IfoOrderParams l_ifoOrderParams = TestDBUtility.getIfoOrderRow();
            ProductParams l_productParams = TestDBUtility.getProductRow();
            IfoProductParams l_ifoProductParams = TestDBUtility.getIfoProductRow();
            MarketParams l_marketParams = TestDBUtility.getMarketRow();
            IfoContractParams l_ifoContractParams = TestDBUtility.getIfoContractRow();
            IfoTradedProductParams l_ifoTradedProductParams = TestDBUtility.getIfoTradedProductRow();
            l_ifoTradedProductParams.setValidForBizDate(null);
            BranchParams l_branchParams = TestDBUtility.getBranchRow();
            InstitutionParams l_institutionParams = TestDBUtility.getInstitutionRow();
            TradedProductParams l_tradedProductParams = TestDBUtility.getTradedProductRow();
            IfoTradedProductUpdqParams l_ifoTradedProductUpdqParams = TestDBUtility.getIfoTradedProductUpdqRow();

            l_mainAccountParams.setAccountId(101001010010L);

            l_subAccountParams.setAccountId(101001010010L);
            l_subAccountParams.setSubAccountId(10100101001007L);
     
            l_ifoOrderParams.setOrderId(1001);
            l_ifoOrderParams.setAccountId(101001010010L);
            l_ifoOrderParams.setSubAccountId(10100101001007L);
            l_ifoOrderParams.setProductType(ProductTypeEnum.IFO);
            l_ifoOrderParams.setCreatedTimestamp(WEB3DateUtility.getDate("20070101","yyyyMMdd"));
            l_ifoOrderParams.setLastUpdatedTimestamp(WEB3DateUtility.getDate("20070101","yyyyMMdd"));
            
            l_ifoOrderUnitParams.setOrderRootDiv("9");
            l_ifoOrderUnitParams.setBizDate("20061010");
            
            l_productParams.setProductId(1006169090018L);
            l_productParams.setProductType(ProductTypeEnum.IFO);
            
            l_ifoProductParams.setProductId(1006169090018L);
            l_ifoProductParams.setProductType(ProductTypeEnum.IFO);
            l_ifoProductParams.setInstitutionCode("0D");
            l_ifoProductParams.setUnderlyingProductCode("1");
            l_ifoProductParams.setDerivativeType(IfoDerivativeTypeEnum.FUTURES);
            l_ifoProductParams.setStrikePrice(11);
            l_ifoProductParams.setMonthOfDelivery("1111");
            l_ifoProductParams.setCreatedTimestamp(WEB3DateUtility.getDate("20070202", "yyyyMMdd"));
            l_ifoProductParams.setLastUpdatedTimestamp(WEB3DateUtility.getDate("20070202", "yyyyMMdd"));
            
            l_marketParams.setMarketId(1002);

            l_ifoContractParams.setAccountId(101001010010L);
            l_ifoContractParams.setSubAccountId(10100101001007L);
            l_ifoContractParams.setProductId(1006169090018L);
            l_ifoContractParams.setDeliveryDate(WEB3DateUtility.getDate("20070202", "yyyyMMdd"));
            
            l_ifoTradedProductParams.setInstitutionCode("0D");
            l_ifoTradedProductParams.setMarketId(1002);
            l_ifoTradedProductParams.setProductId(1006169090018L);
            
            l_tradedProductParams.setProductId(1006169090018L);
            l_tradedProductParams.setMarketId(1002);
            l_tradedProductParams.setProductType(ProductTypeEnum.IFO);
            
            l_ifoTradedProductUpdqParams.setProductId(1006169090018L);
            l_ifoTradedProductUpdqParams.setInstitutionCode("0D");
            l_ifoTradedProductUpdqParams.setMarketId(1002);
            SimpleDateFormat l_format = GtlUtils.getThreadSafeSimpleDateFormat("yyyyMMdd");
            Date l_datBizDate = GtlUtils.getTradingSystem().getSystemTimestamp();
            Date l_datpreBizDate = new WEB3GentradeBizDate(
                new Timestamp(l_datBizDate.getTime())).roll(1);
            String l_strCreateDate = l_format.format(l_datpreBizDate);
            l_ifoTradedProductUpdqParams.setValidForBizDate(l_strCreateDate);  
            
            l_ifoTradedProductUpdqParams.setStartTradingDate(WEB3DateUtility.getDate("20040701","yyyyMMdd"));
            l_ifoTradedProductUpdqParams.setLastTradingDate(WEB3DateUtility.getDate("20040720","yyyyMMdd"));
            l_ifoTradedProductUpdqParams.setListDate(WEB3DateUtility.getDate("20040701","yyyyMMdd"));
            l_ifoTradedProductUpdqParams.setUnlistedDate(WEB3DateUtility.getDate("20040720","yyyyMMdd"));
            
            this.deleteAll();
            TestDBUtility.insertWithDel(l_ifoOrderUnitParams);
            TestDBUtility.insertWithDel(l_subAccountParams);
            TestDBUtility.insertWithDel(l_mainAccountParams);
            TestDBUtility.insertWithDel(l_ifoOrderParams);
            TestDBUtility.insertWithDel(l_productParams);
            TestDBUtility.insertWithDel(l_ifoProductParams);
            TestDBUtility.insertWithDel(l_marketParams);
            TestDBUtility.insertWithDel(l_ifoContractParams);
            TestDBUtility.insertWithDel(l_ifoTradedProductParams);
            TestDBUtility.insertWithDel(l_branchParams);
            TestDBUtility.insertWithDel(l_institutionParams);
            TestDBUtility.insertWithDel(l_tradedProductParams);
            TestDBUtility.insertWithDel(l_ifoTradedProductUpdqParams);
            
            WEB3IfoTradedProductImpl l_tradedProduct = new WEB3IfoTradedProductImpl(1006160060005L);
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue("webbroker3.ifo.WEB3IfoOrderManagerReusableValidations",
                    "validateTradedProduct", 
                    new Class[] { WEB3IfoProductImpl.class, 
                    WEB3GentradeMarket.class, boolean.class, boolean.class },
                    l_tradedProduct);
            
            WEB3GentradeSubAccount l_subAccount = new WEB3GentradeSubAccount(101001010010L, 10100101001007L);

            SettleContractEntry[] l_SettleContractEntry = new SettleContractEntry[1];
            l_SettleContractEntry[0] = new SettleContractEntry(1001, 200);
            
            WEB3IfoChangeSettleContractOrderSpec l_changeSettleContractOrderSpec =
                new WEB3IfoChangeSettleContractOrderSpec(
                    1001, 1001, 200, l_SettleContractEntry);
            
            boolean l_blnIsSkipDelayStatusCheck = false;

            OrderValidationResult l_orderValidationResult = l_futuresOrderManager.validateFuturesChangeSettleContractOrder(
                l_subAccount,
                l_changeSettleContractOrderSpec,
                l_blnIsSkipDelayStatusCheck);
            
            log.debug("l_orderValidationResult=============" + l_orderValidationResult); 
            
            assertEquals(l_orderValidationResult.getProcessingResult().getErrorInfo(),
                WEB3ErrorCatalog.SYSTEM_ERROR_80017);
            
        }
        catch (Exception l_ex)
        {
            log.error(l_ex.getMessage(), l_ex);
            fail();
        }
        log.info(STR_METHOD_NAME + ">>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>test pass !!");
        log.exiting(TEST_END + STR_METHOD_NAME);
    }

    public void test_validateFuturesChangeOrder_C0001()
    {
        final String STR_METHOD_NAME = " test_validateFuturesChangeOrder_C0001";
        log.entering(TEST_START + STR_METHOD_NAME);
        
        MOCK_MANAGER.setIsMockUsed(true);
        
        TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
            "webbroker3.ifo.WEB3FuturesOrderManagerImpl",
            "validateOrder",
            new Class[] {SubAccount.class,String.class},
            null);
        
        TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
            "webbroker3.ifo.WEB3IfoOrderManagerReusableValidations",
            "validateOrderForChangeability",
            new Class[] {Order.class, boolean.class},
            null);
        
        TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
            "webbroker3.ifo.WEB3IfoOrderManagerReusableValidations",
            "validateHandlingIndex",
            new Class[]{String.class, WEB3IfoTradedProductImpl.class},
            null);
        
        TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
            "webbroker3.ifo.WEB3IfoOrderManagerReusableValidations",
            "validateOrderCond",
            new Class[]{
                WEB3GentradeSubAccount.class,
                long.class, boolean.class, 
                WEB3IfoTradedProductImpl.class, boolean.class,
                boolean.class, Date.class, Date.class, String.class, 
                IfoOrderExecutionConditionType.class, String.class,Long.class},
                null);
        
        TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
            "webbroker3.ifo.WEB3IfoOrderManagerReusableValidations",
            "validateOrderUnitPrice",
            new Class[] {double.class, WEB3IfoTradedProductImpl.class, SubAccount.class},
            null);
        
        TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
            "webbroker3.ifo.WEB3IfoOrderManagerReusableValidations",
            "validateWLimitPriceOrder",
            new Class[]{
                WEB3GentradeSubAccount.class,
                long.class, double.class, 
                String.class, double.class,
                String.class,
                IfoOrderExecutionConditionType.class, String.class,
                WEB3IfoTradedProductImpl.class,
                boolean.class, boolean.class},
                null);
        
        TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
            "webbroker3.ifo.WEB3IfoOrderManagerReusableValidations",
            "validateOrderChangeSpec",
            new Class[]{
                OrderUnit.class,
                double.class, double.class, 
                IfoOrderExecutionConditionType.class, String.class,
                String.class, String.class, double.class, double.class, 
                IfoOrderExecutionConditionType.class, Date.class, String.class,
                SettleContractEntry[].class},
                null);
        
        TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
            "webbroker3.ifo.WEB3IfoOrderManagerReusableValidations",
            "validateChangeOrderRevLimit",
            new Class[]{
                IfoOrderUnit.class,
                double.class, double.class, 
                IfoOrderExecutionConditionType.class},
                null);

        FinApp l_finApp = (FinApp) Services.getService(FinApp.class);

        try
        {
            Date l_datExpect = WEB3DateUtility.getDate("20040710","yyyyMMdd");
            WEB3GentradeTradingTimeManagementForMock.mockGetOrderBizDate(l_datExpect);
            
            WEB3FuturesOrderManagerImpl l_futuresOrderManager = 
                (WEB3FuturesOrderManagerImpl)l_finApp.getTradingModule(ProductTypeEnum.IFO).getOrderManager();;
                      
            MainAccountParams l_mainAccountParams = TestDBUtility.getMainAccountRow();
            SubAccountParams l_subAccountParams = TestDBUtility.getSubAccountRow();
            IfoOrderUnitParams l_ifoOrderUnitParams = TestDBUtility.getIfoOrderUnitRow();
            IfoOrderParams l_ifoOrderParams = TestDBUtility.getIfoOrderRow();
            ProductParams l_productParams = TestDBUtility.getProductRow();
            IfoProductParams l_ifoProductParams = TestDBUtility.getIfoProductRow();
            MarketParams l_marketParams = TestDBUtility.getMarketRow();
            IfoContractParams l_ifoContractParams = TestDBUtility.getIfoContractRow();
            IfoTradedProductParams l_ifoTradedProductParams = TestDBUtility.getIfoTradedProductRow();
            l_ifoTradedProductParams.setValidForBizDate(null);
            BranchParams l_branchParams = TestDBUtility.getBranchRow();
            InstitutionParams l_institutionParams = TestDBUtility.getInstitutionRow();
            TradedProductParams l_tradedProductParams = TestDBUtility.getTradedProductRow();
            IfoTradedProductUpdqParams l_ifoTradedProductUpdqParams = TestDBUtility.getIfoTradedProductUpdqRow();

            l_mainAccountParams.setAccountId(101001010010L);

            l_subAccountParams.setAccountId(101001010010L);
            l_subAccountParams.setSubAccountId(10100101001007L);
     
            l_ifoOrderParams.setOrderId(1001);
            l_ifoOrderParams.setAccountId(101001010010L);
            l_ifoOrderParams.setSubAccountId(10100101001007L);
            l_ifoOrderParams.setProductType(ProductTypeEnum.IFO);
            l_ifoOrderParams.setCreatedTimestamp(WEB3DateUtility.getDate("20070101","yyyyMMdd"));
            l_ifoOrderParams.setLastUpdatedTimestamp(WEB3DateUtility.getDate("20070101","yyyyMMdd"));
            
            l_ifoOrderUnitParams.setOrderRootDiv("9");
            l_ifoOrderUnitParams.setOrderType(OrderTypeEnum.BOND_BUY);
            
            l_productParams.setProductId(1006169090018L);
            l_productParams.setProductType(ProductTypeEnum.IFO);
            
            l_ifoProductParams.setProductId(1006169090018L);
            l_ifoProductParams.setProductType(ProductTypeEnum.IFO);
            l_ifoProductParams.setInstitutionCode("0D");
            l_ifoProductParams.setUnderlyingProductCode("1");
            l_ifoProductParams.setDerivativeType(IfoDerivativeTypeEnum.FUTURES);
            l_ifoProductParams.setStrikePrice(11);
            l_ifoProductParams.setMonthOfDelivery("1111");
            l_ifoProductParams.setCreatedTimestamp(WEB3DateUtility.getDate("20070202", "yyyyMMdd"));
            l_ifoProductParams.setLastUpdatedTimestamp(WEB3DateUtility.getDate("20070202", "yyyyMMdd"));
            
            l_marketParams.setMarketId(1002);

            l_ifoContractParams.setAccountId(101001010010L);
            l_ifoContractParams.setSubAccountId(10100101001007L);
            l_ifoContractParams.setProductId(1006169090018L);
            l_ifoContractParams.setDeliveryDate(WEB3DateUtility.getDate("20070202", "yyyyMMdd"));
            
            l_ifoTradedProductParams.setInstitutionCode("0D");
            l_ifoTradedProductParams.setMarketId(1002);
            l_ifoTradedProductParams.setProductId(1006169090018L);
            
            l_tradedProductParams.setProductId(1006169090018L);
            l_tradedProductParams.setMarketId(1002);
            l_tradedProductParams.setProductType(ProductTypeEnum.IFO);
            
            l_ifoTradedProductUpdqParams.setProductId(1006169090018L);
            l_ifoTradedProductUpdqParams.setInstitutionCode("0D");
            l_ifoTradedProductUpdqParams.setMarketId(1002);
            SimpleDateFormat l_format = GtlUtils.getThreadSafeSimpleDateFormat("yyyyMMdd");
            Date l_datBizDate = GtlUtils.getTradingSystem().getSystemTimestamp();
            Date l_datpreBizDate = new WEB3GentradeBizDate(
                new Timestamp(l_datBizDate.getTime())).roll(1);
            String l_strCreateDate = l_format.format(l_datpreBizDate);
            l_ifoTradedProductUpdqParams.setValidForBizDate(l_strCreateDate); 
            
            l_ifoTradedProductUpdqParams.setStartTradingDate(WEB3DateUtility.getDate("20040701","yyyyMMdd"));
            l_ifoTradedProductUpdqParams.setLastTradingDate(WEB3DateUtility.getDate("20040720","yyyyMMdd"));
            l_ifoTradedProductUpdqParams.setListDate(WEB3DateUtility.getDate("20040701","yyyyMMdd"));
            l_ifoTradedProductUpdqParams.setUnlistedDate(WEB3DateUtility.getDate("20040720","yyyyMMdd"));
            
            OrderexecutionEndParams l_orderexecutionEndParams = new OrderexecutionEndParams();
            l_orderexecutionEndParams.setInstitutionCode("0D");
            l_orderexecutionEndParams.setProductType(ProductTypeEnum.IFO);
            l_orderexecutionEndParams.setFutureOptionDiv("1");
            l_orderexecutionEndParams.setCreatedTimestamp(WEB3DateUtility.getDate("20070202", "yyyyMMdd"));
            l_orderexecutionEndParams.setLastUpdatedTimestamp(WEB3DateUtility.getDate("20070202", "yyyyMMdd"));
            l_orderexecutionEndParams.setOrderexecutionEndType("0");
            
            this.deleteAll();
            TestDBUtility.insertWithDel(l_ifoOrderUnitParams);
            TestDBUtility.insertWithDel(l_subAccountParams);
            TestDBUtility.insertWithDel(l_mainAccountParams);
            TestDBUtility.insertWithDel(l_ifoOrderParams);
            TestDBUtility.insertWithDel(l_productParams);
            TestDBUtility.insertWithDel(l_ifoProductParams);
            TestDBUtility.insertWithDel(l_marketParams);
            TestDBUtility.insertWithDel(l_ifoContractParams);
            TestDBUtility.insertWithDel(l_ifoTradedProductParams);
            TestDBUtility.insertWithDel(l_branchParams);
            TestDBUtility.insertWithDel(l_institutionParams);
            TestDBUtility.insertWithDel(l_tradedProductParams);
            TestDBUtility.insertWithDel(l_ifoTradedProductUpdqParams);
            TestDBUtility.insertWithDel(l_orderexecutionEndParams);
            
            WEB3IfoTradedProductImpl l_tradedProduct = new WEB3IfoTradedProductImpl(1006160060005L);
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue("webbroker3.ifo.WEB3IfoOrderManagerReusableValidations",
                    "validateTradedProduct", 
                    new Class[] { WEB3IfoProductImpl.class, 
                    WEB3GentradeMarket.class, boolean.class, boolean.class },
                    l_tradedProduct);
            
            WEB3GentradeSubAccount l_subAccount = new WEB3GentradeSubAccount(101001010010L, 10100101001007L);

            WEB3IfoOpenContractChangeSpec l_ifoOpenContractChangeSpec =
                new WEB3IfoOpenContractChangeSpec(1001, 1001, 200, 200);

            boolean l_blnIsSkipDelayStatusCheck = false;
            
            WEB3GentradeTradingClendarContext l_clendarContext =
                (WEB3GentradeTradingClendarContext)ThreadLocalSystemAttributesRegistry.getAttribute(
                TRADING_CAL_CONTEXT_PATH);
            
            l_clendarContext.setInstitutionCode("0D");

            OrderValidationResult l_orderValidationResult = l_futuresOrderManager.validateFuturesChangeOrder(
                l_subAccount,
                l_ifoOpenContractChangeSpec,
                l_blnIsSkipDelayStatusCheck);
            
            log.debug("l_orderValidationResult=============" + l_orderValidationResult); 
            assertTrue(l_orderValidationResult.getProcessingResult().isSuccessfulResult());
        }
        catch (Exception l_ex)
        {
            log.error(l_ex.getMessage(), l_ex);
            fail();
        }
        log.info(STR_METHOD_NAME + ">>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>test pass !!");
        log.exiting(TEST_END + STR_METHOD_NAME);
    }
    
    public void test_validateFuturesChangeOrder_C0002()
    {
        final String STR_METHOD_NAME = " test_validateFuturesChangeOrder_C0002";
        log.entering(TEST_START + STR_METHOD_NAME);
        
        MOCK_MANAGER.setIsMockUsed(true);
        
        TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
            "webbroker3.ifo.WEB3FuturesOrderManagerImpl",
            "validateOrder",
            new Class[] {SubAccount.class,String.class},
            null);
        
        TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
            "webbroker3.ifo.WEB3IfoOrderManagerReusableValidations",
            "validateOrderForChangeability",
            new Class[] {Order.class, boolean.class},
            null);
        
        TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
            "webbroker3.ifo.WEB3IfoOrderManagerReusableValidations",
            "validateHandlingIndex",
            new Class[]{String.class, WEB3IfoTradedProductImpl.class},
            null);
        
        TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
            "webbroker3.ifo.WEB3IfoOrderManagerReusableValidations",
            "validateOrderCond",
            new Class[]{
                WEB3GentradeSubAccount.class,
                long.class, boolean.class, 
                WEB3IfoTradedProductImpl.class, boolean.class,
                boolean.class, Date.class, Date.class, String.class, 
                IfoOrderExecutionConditionType.class, String.class,Long.class},
                null);
        
        TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
            "webbroker3.ifo.WEB3IfoOrderManagerReusableValidations",
            "validateOrderUnitPrice",
            new Class[] {double.class, WEB3IfoTradedProductImpl.class, SubAccount.class},
            null);
        
        TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
            "webbroker3.ifo.WEB3IfoOrderManagerReusableValidations",
            "validateWLimitPriceOrder",
            new Class[]{
                WEB3GentradeSubAccount.class,
                long.class, double.class, 
                String.class, double.class,
                String.class,
                IfoOrderExecutionConditionType.class, String.class,
                WEB3IfoTradedProductImpl.class,
                boolean.class, boolean.class},
                null);
        
        TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
            "webbroker3.ifo.WEB3IfoOrderManagerReusableValidations",
            "validateOrderChangeSpec",
            new Class[]{
                OrderUnit.class,
                double.class, double.class, 
                IfoOrderExecutionConditionType.class, String.class,
                String.class, String.class, double.class, double.class, 
                IfoOrderExecutionConditionType.class, Date.class, String.class,
                SettleContractEntry[].class},
                null);
        
        TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
            "webbroker3.ifo.WEB3IfoOrderManagerReusableValidations",
            "validateChangeOrderRevLimit",
            new Class[]{
                IfoOrderUnit.class,
                double.class, double.class, 
                IfoOrderExecutionConditionType.class},
                null);

        FinApp l_finApp = (FinApp) Services.getService(FinApp.class);

        try
        {
            Date l_datExpect = WEB3DateUtility.getDate("20040710","yyyyMMdd");
            WEB3GentradeTradingTimeManagementForMock.mockGetOrderBizDate(l_datExpect);
            
            WEB3FuturesOrderManagerImpl l_futuresOrderManager = 
                (WEB3FuturesOrderManagerImpl)l_finApp.getTradingModule(ProductTypeEnum.IFO).getOrderManager();;
                      
            MainAccountParams l_mainAccountParams = TestDBUtility.getMainAccountRow();
            SubAccountParams l_subAccountParams = TestDBUtility.getSubAccountRow();
            IfoOrderUnitParams l_ifoOrderUnitParams = TestDBUtility.getIfoOrderUnitRow();
            IfoOrderParams l_ifoOrderParams = TestDBUtility.getIfoOrderRow();
            ProductParams l_productParams = TestDBUtility.getProductRow();
            IfoProductParams l_ifoProductParams = TestDBUtility.getIfoProductRow();
            MarketParams l_marketParams = TestDBUtility.getMarketRow();
            IfoContractParams l_ifoContractParams = TestDBUtility.getIfoContractRow();
            IfoTradedProductParams l_ifoTradedProductParams = TestDBUtility.getIfoTradedProductRow();
            l_ifoTradedProductParams.setValidForBizDate(null);
            BranchParams l_branchParams = TestDBUtility.getBranchRow();
            InstitutionParams l_institutionParams = TestDBUtility.getInstitutionRow();
            TradedProductParams l_tradedProductParams = TestDBUtility.getTradedProductRow();
            IfoTradedProductUpdqParams l_ifoTradedProductUpdqParams = TestDBUtility.getIfoTradedProductUpdqRow();

            l_mainAccountParams.setAccountId(101001010010L);

            l_subAccountParams.setAccountId(101001010010L);
            l_subAccountParams.setSubAccountId(10100101001007L);
     
            l_ifoOrderParams.setOrderId(1001);
            l_ifoOrderParams.setAccountId(101001010010L);
            l_ifoOrderParams.setSubAccountId(10100101001007L);
            l_ifoOrderParams.setProductType(ProductTypeEnum.IFO);
            l_ifoOrderParams.setCreatedTimestamp(WEB3DateUtility.getDate("20070101","yyyyMMdd"));
            l_ifoOrderParams.setLastUpdatedTimestamp(WEB3DateUtility.getDate("20070101","yyyyMMdd"));
            
            l_ifoOrderUnitParams.setOrderRootDiv("9");
            l_ifoOrderUnitParams.setOrderType(OrderTypeEnum.BOND_SELL);
            l_ifoOrderUnitParams.setBizDate("20061010");
            
            l_productParams.setProductId(1006169090018L);
            l_productParams.setProductType(ProductTypeEnum.IFO);
            
            l_ifoProductParams.setProductId(1006169090018L);
            l_ifoProductParams.setProductType(ProductTypeEnum.IFO);
            l_ifoProductParams.setInstitutionCode("0D");
            l_ifoProductParams.setUnderlyingProductCode("1");
            l_ifoProductParams.setDerivativeType(IfoDerivativeTypeEnum.FUTURES);
            l_ifoProductParams.setStrikePrice(11);
            l_ifoProductParams.setMonthOfDelivery("1111");
            l_ifoProductParams.setCreatedTimestamp(WEB3DateUtility.getDate("20070202", "yyyyMMdd"));
            l_ifoProductParams.setLastUpdatedTimestamp(WEB3DateUtility.getDate("20070202", "yyyyMMdd"));
            
            l_marketParams.setMarketId(1002);

            l_ifoContractParams.setAccountId(101001010010L);
            l_ifoContractParams.setSubAccountId(10100101001007L);
            l_ifoContractParams.setProductId(1006169090018L);
            l_ifoContractParams.setDeliveryDate(WEB3DateUtility.getDate("20070202", "yyyyMMdd"));
            
            l_ifoTradedProductParams.setInstitutionCode("0D");
            l_ifoTradedProductParams.setMarketId(1002);
            l_ifoTradedProductParams.setProductId(1006169090018L);
            
            l_tradedProductParams.setProductId(1006169090018L);
            l_tradedProductParams.setMarketId(1002);
            l_tradedProductParams.setProductType(ProductTypeEnum.IFO);
            
            l_ifoTradedProductUpdqParams.setProductId(1006169090018L);
            l_ifoTradedProductUpdqParams.setInstitutionCode("0D");
            l_ifoTradedProductUpdqParams.setMarketId(1002);
            SimpleDateFormat l_format = GtlUtils.getThreadSafeSimpleDateFormat("yyyyMMdd");
            Date l_datBizDate = GtlUtils.getTradingSystem().getSystemTimestamp();
            Date l_datpreBizDate = new WEB3GentradeBizDate(
                new Timestamp(l_datBizDate.getTime())).roll(1);
            String l_strCreateDate = l_format.format(l_datpreBizDate);
            l_ifoTradedProductUpdqParams.setValidForBizDate(l_strCreateDate);  
            
            l_ifoTradedProductUpdqParams.setStartTradingDate(WEB3DateUtility.getDate("20040701","yyyyMMdd"));
            l_ifoTradedProductUpdqParams.setLastTradingDate(WEB3DateUtility.getDate("20040720","yyyyMMdd"));
            l_ifoTradedProductUpdqParams.setListDate(WEB3DateUtility.getDate("20040701","yyyyMMdd"));
            l_ifoTradedProductUpdqParams.setUnlistedDate(WEB3DateUtility.getDate("20040720","yyyyMMdd"));
            
            this.deleteAll();
            TestDBUtility.insertWithDel(l_ifoOrderUnitParams);
            TestDBUtility.insertWithDel(l_subAccountParams);
            TestDBUtility.insertWithDel(l_mainAccountParams);
            TestDBUtility.insertWithDel(l_ifoOrderParams);
            TestDBUtility.insertWithDel(l_productParams);
            TestDBUtility.insertWithDel(l_ifoProductParams);
            TestDBUtility.insertWithDel(l_marketParams);
            TestDBUtility.insertWithDel(l_ifoContractParams);
            TestDBUtility.insertWithDel(l_ifoTradedProductParams);
            TestDBUtility.insertWithDel(l_branchParams);
            TestDBUtility.insertWithDel(l_institutionParams);
            TestDBUtility.insertWithDel(l_tradedProductParams);
            TestDBUtility.insertWithDel(l_ifoTradedProductUpdqParams);
            
            WEB3IfoTradedProductImpl l_tradedProduct = new WEB3IfoTradedProductImpl(1006160060005L);
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue("webbroker3.ifo.WEB3IfoOrderManagerReusableValidations",
                    "validateTradedProduct", 
                    new Class[] { WEB3IfoProductImpl.class, 
                    WEB3GentradeMarket.class, boolean.class, boolean.class },
                    l_tradedProduct);
            
            WEB3GentradeSubAccount l_subAccount = new WEB3GentradeSubAccount(101001010010L, 10100101001007L);

            WEB3IfoOpenContractChangeSpec l_ifoOpenContractChangeSpec =
                new WEB3IfoOpenContractChangeSpec(1001, 1001, 200, 200);

            boolean l_blnIsSkipDelayStatusCheck = false;

            OrderValidationResult l_orderValidationResult = l_futuresOrderManager.validateFuturesChangeOrder(
                l_subAccount,
                l_ifoOpenContractChangeSpec,
                l_blnIsSkipDelayStatusCheck);
            
            log.debug("l_orderValidationResult=============" + l_orderValidationResult); 
            assertTrue(l_orderValidationResult.getProcessingResult().isSuccessfulResult());
        }
        catch (Exception l_ex)
        {
            log.error(l_ex.getMessage(), l_ex);
            fail();
        }
        log.info(STR_METHOD_NAME + ">>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>test pass !!");
        log.exiting(TEST_END + STR_METHOD_NAME);
    }
    
    public void test_validateFuturesChangeOrder_C0003()
    {
        final String STR_METHOD_NAME = " test_validateFuturesChangeOrder_C0003";
        log.entering(TEST_START + STR_METHOD_NAME);
        
        MOCK_MANAGER.setIsMockUsed(true);
        
        TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
            "webbroker3.ifo.WEB3FuturesOrderManagerImpl",
            "validateOrder",
            new Class[] {SubAccount.class,String.class},
            null);

        FinApp l_finApp = (FinApp) Services.getService(FinApp.class);

        try
        {
            Date l_datExpect = WEB3DateUtility.getDate("20040710","yyyyMMdd");
            WEB3GentradeTradingTimeManagementForMock.mockGetOrderBizDate(l_datExpect);
            
            WEB3FuturesOrderManagerImpl l_futuresOrderManager = 
                (WEB3FuturesOrderManagerImpl)l_finApp.getTradingModule(ProductTypeEnum.IFO).getOrderManager();;
                      
            MainAccountParams l_mainAccountParams = TestDBUtility.getMainAccountRow();
            SubAccountParams l_subAccountParams = TestDBUtility.getSubAccountRow();
            IfoOrderUnitParams l_ifoOrderUnitParams = TestDBUtility.getIfoOrderUnitRow();
            IfoOrderParams l_ifoOrderParams = TestDBUtility.getIfoOrderRow();

            l_mainAccountParams.setAccountId(101001010010L);

            l_subAccountParams.setAccountId(101001010010L);
            l_subAccountParams.setSubAccountId(10100101001007L);
     
            l_ifoOrderParams.setOrderId(1002);
            l_ifoOrderParams.setAccountId(101001010010L);
            l_ifoOrderParams.setSubAccountId(10100101001007L);
            l_ifoOrderParams.setProductType(ProductTypeEnum.IFO);
            l_ifoOrderParams.setCreatedTimestamp(WEB3DateUtility.getDate("20070101","yyyyMMdd"));
            l_ifoOrderParams.setLastUpdatedTimestamp(WEB3DateUtility.getDate("20070101","yyyyMMdd"));
            
            l_ifoOrderUnitParams.setOrderRootDiv("9");
            l_ifoOrderUnitParams.setBizDate("20061010");
            
            this.deleteAll();
            TestDBUtility.insertWithDel(l_ifoOrderUnitParams);
            TestDBUtility.insertWithDel(l_subAccountParams);
            TestDBUtility.insertWithDel(l_mainAccountParams);
            TestDBUtility.insertWithDel(l_ifoOrderParams);
 
            WEB3GentradeSubAccount l_subAccount = new WEB3GentradeSubAccount(101001010010L, 10100101001007L);

            WEB3IfoOpenContractChangeSpec l_ifoOpenContractChangeSpec =
                new WEB3IfoOpenContractChangeSpec(1002, 1001, 200, 200);

            boolean l_blnIsSkipDelayStatusCheck = false;

            OrderValidationResult l_orderValidationResult = l_futuresOrderManager.validateFuturesChangeOrder(
                l_subAccount,
                l_ifoOpenContractChangeSpec,
                l_blnIsSkipDelayStatusCheck);
            
            log.debug("l_orderValidationResult=============" + l_orderValidationResult); 
            
            assertEquals(l_orderValidationResult.getProcessingResult().getErrorInfo(),
                WEB3ErrorCatalog.SYSTEM_ERROR_80017);
        }
        catch (Exception l_ex)
        {
            log.error(l_ex.getMessage(), l_ex);
            fail();
        }
        log.info(STR_METHOD_NAME + ">>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>test pass !!");
        log.exiting(TEST_END + STR_METHOD_NAME);
    }

    public void test_validateFuturesChangeOrder_C0004()
    {
        final String STR_METHOD_NAME = " test_validateFuturesChangeOrder_C0004";
        log.entering(TEST_START + STR_METHOD_NAME);
        
        MOCK_MANAGER.setIsMockUsed(true);
        
        TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
            "webbroker3.ifo.WEB3FuturesOrderManagerImpl",
            "validateOrder",
            new Class[] {SubAccount.class,String.class},
            null);
        
        TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
            "webbroker3.ifo.WEB3IfoOrderManagerReusableValidations",
            "validateOrderForChangeability",
            new Class[] {Order.class, boolean.class},
            null);
        
        TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
            "webbroker3.ifo.WEB3IfoOrderManagerReusableValidations",
            "validateHandlingIndex",
            new Class[]{String.class, WEB3IfoTradedProductImpl.class},
            null);
        
        TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
            "webbroker3.ifo.WEB3IfoOrderManagerReusableValidations",
            "validateOrderCond",
            new Class[]{
                WEB3GentradeSubAccount.class,
                long.class, boolean.class, 
                WEB3IfoTradedProductImpl.class, boolean.class,
                boolean.class, Date.class, Date.class, String.class, 
                IfoOrderExecutionConditionType.class, String.class,Long.class},
                null);
        
        TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
            "webbroker3.ifo.WEB3IfoOrderManagerReusableValidations",
            "validateOrderUnitPrice",
            new Class[] {double.class, WEB3IfoTradedProductImpl.class, SubAccount.class},
            null);
        
        TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
            "webbroker3.ifo.WEB3IfoOrderManagerReusableValidations",
            "validateWLimitPriceOrder",
            new Class[]{
                WEB3GentradeSubAccount.class,
                long.class, double.class, 
                String.class, double.class,
                String.class,
                IfoOrderExecutionConditionType.class, String.class,
                WEB3IfoTradedProductImpl.class,
                boolean.class, boolean.class},
                null);
        
        TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
            "webbroker3.ifo.WEB3IfoOrderManagerReusableValidations",
            "validateOrderChangeSpec",
            new Class[]{
                OrderUnit.class,
                double.class, double.class, 
                IfoOrderExecutionConditionType.class, String.class,
                String.class, String.class, double.class, double.class, 
                IfoOrderExecutionConditionType.class, Date.class, String.class,
                SettleContractEntry[].class},
                null);
        
        TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
            "webbroker3.ifo.WEB3IfoOrderManagerReusableValidations",
            "validateChangeOrderRevLimit",
            new Class[]{
                IfoOrderUnit.class,
                double.class, double.class, 
                IfoOrderExecutionConditionType.class},
            new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80017, ""));

        FinApp l_finApp = (FinApp) Services.getService(FinApp.class);

        try
        {
            Date l_datExpect = WEB3DateUtility.getDate("20040710","yyyyMMdd");
            WEB3GentradeTradingTimeManagementForMock.mockGetOrderBizDate(l_datExpect);
            
            WEB3FuturesOrderManagerImpl l_futuresOrderManager = 
                (WEB3FuturesOrderManagerImpl)l_finApp.getTradingModule(ProductTypeEnum.IFO).getOrderManager();;
                      
            MainAccountParams l_mainAccountParams = TestDBUtility.getMainAccountRow();
            SubAccountParams l_subAccountParams = TestDBUtility.getSubAccountRow();
            IfoOrderUnitParams l_ifoOrderUnitParams = TestDBUtility.getIfoOrderUnitRow();
            IfoOrderParams l_ifoOrderParams = TestDBUtility.getIfoOrderRow();
            ProductParams l_productParams = TestDBUtility.getProductRow();
            IfoProductParams l_ifoProductParams = TestDBUtility.getIfoProductRow();
            MarketParams l_marketParams = TestDBUtility.getMarketRow();
            IfoContractParams l_ifoContractParams = TestDBUtility.getIfoContractRow();
            IfoTradedProductParams l_ifoTradedProductParams = TestDBUtility.getIfoTradedProductRow();
            l_ifoTradedProductParams.setValidForBizDate(null);
            BranchParams l_branchParams = TestDBUtility.getBranchRow();
            InstitutionParams l_institutionParams = TestDBUtility.getInstitutionRow();
            TradedProductParams l_tradedProductParams = TestDBUtility.getTradedProductRow();
            IfoTradedProductUpdqParams l_ifoTradedProductUpdqParams = TestDBUtility.getIfoTradedProductUpdqRow();

            l_mainAccountParams.setAccountId(101001010010L);

            l_subAccountParams.setAccountId(101001010010L);
            l_subAccountParams.setSubAccountId(10100101001007L);
     
            l_ifoOrderParams.setOrderId(1001);
            l_ifoOrderParams.setAccountId(101001010010L);
            l_ifoOrderParams.setSubAccountId(10100101001007L);
            l_ifoOrderParams.setProductType(ProductTypeEnum.IFO);
            l_ifoOrderParams.setCreatedTimestamp(WEB3DateUtility.getDate("20070101","yyyyMMdd"));
            l_ifoOrderParams.setLastUpdatedTimestamp(WEB3DateUtility.getDate("20070101","yyyyMMdd"));
            
            l_ifoOrderUnitParams.setOrderRootDiv("9");
            l_ifoOrderUnitParams.setBizDate("20061010");
            
            l_productParams.setProductId(1006169090018L);
            l_productParams.setProductType(ProductTypeEnum.IFO);
            
            l_ifoProductParams.setProductId(1006169090018L);
            l_ifoProductParams.setProductType(ProductTypeEnum.IFO);
            l_ifoProductParams.setInstitutionCode("0D");
            l_ifoProductParams.setUnderlyingProductCode("1");
            l_ifoProductParams.setDerivativeType(IfoDerivativeTypeEnum.FUTURES);
            l_ifoProductParams.setStrikePrice(11);
            l_ifoProductParams.setMonthOfDelivery("1111");
            l_ifoProductParams.setCreatedTimestamp(WEB3DateUtility.getDate("20070202", "yyyyMMdd"));
            l_ifoProductParams.setLastUpdatedTimestamp(WEB3DateUtility.getDate("20070202", "yyyyMMdd"));
            
            l_marketParams.setMarketId(1002);

            l_ifoContractParams.setAccountId(101001010010L);
            l_ifoContractParams.setSubAccountId(10100101001007L);
            l_ifoContractParams.setProductId(1006169090018L);
            l_ifoContractParams.setDeliveryDate(WEB3DateUtility.getDate("20070202", "yyyyMMdd"));
            
            l_ifoTradedProductParams.setInstitutionCode("0D");
            l_ifoTradedProductParams.setMarketId(1002);
            l_ifoTradedProductParams.setProductId(1006169090018L);
            
            l_tradedProductParams.setProductId(1006169090018L);
            l_tradedProductParams.setMarketId(1002);
            l_tradedProductParams.setProductType(ProductTypeEnum.IFO);
            
            l_ifoTradedProductUpdqParams.setProductId(1006169090018L);
            l_ifoTradedProductUpdqParams.setInstitutionCode("0D");
            l_ifoTradedProductUpdqParams.setMarketId(1002);
            SimpleDateFormat l_format = GtlUtils.getThreadSafeSimpleDateFormat("yyyyMMdd");
            Date l_datBizDate = GtlUtils.getTradingSystem().getSystemTimestamp();
            Date l_datpreBizDate = new WEB3GentradeBizDate(
                new Timestamp(l_datBizDate.getTime())).roll(1);
            String l_strCreateDate = l_format.format(l_datpreBizDate);
            l_ifoTradedProductUpdqParams.setValidForBizDate(l_strCreateDate); 
            
            l_ifoTradedProductUpdqParams.setStartTradingDate(WEB3DateUtility.getDate("20040701","yyyyMMdd"));
            l_ifoTradedProductUpdqParams.setLastTradingDate(WEB3DateUtility.getDate("20040720","yyyyMMdd"));
            l_ifoTradedProductUpdqParams.setListDate(WEB3DateUtility.getDate("20040701","yyyyMMdd"));
            l_ifoTradedProductUpdqParams.setUnlistedDate(WEB3DateUtility.getDate("20040720","yyyyMMdd"));

            this.deleteAll();
            TestDBUtility.insertWithDel(l_ifoOrderUnitParams);
            TestDBUtility.insertWithDel(l_subAccountParams);
            TestDBUtility.insertWithDel(l_mainAccountParams);
            TestDBUtility.insertWithDel(l_ifoOrderParams);
            TestDBUtility.insertWithDel(l_productParams);
            TestDBUtility.insertWithDel(l_ifoProductParams);
            TestDBUtility.insertWithDel(l_marketParams);
            TestDBUtility.insertWithDel(l_ifoContractParams);
            TestDBUtility.insertWithDel(l_ifoTradedProductParams);
            TestDBUtility.insertWithDel(l_branchParams);
            TestDBUtility.insertWithDel(l_institutionParams);
            TestDBUtility.insertWithDel(l_tradedProductParams);
            TestDBUtility.insertWithDel(l_ifoTradedProductUpdqParams);
            
            WEB3IfoTradedProductImpl l_tradedProduct = new WEB3IfoTradedProductImpl(1006160060005L);
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue("webbroker3.ifo.WEB3IfoOrderManagerReusableValidations",
                    "validateTradedProduct", 
                    new Class[] { WEB3IfoProductImpl.class, 
                    WEB3GentradeMarket.class, boolean.class, boolean.class },
                    l_tradedProduct);
            
            WEB3GentradeSubAccount l_subAccount = new WEB3GentradeSubAccount(101001010010L, 10100101001007L);

            WEB3IfoOpenContractChangeSpec l_ifoOpenContractChangeSpec =
                new WEB3IfoOpenContractChangeSpec(1001, 1001, 200, 200);

            boolean l_blnIsSkipDelayStatusCheck = false;

            OrderValidationResult l_orderValidationResult = l_futuresOrderManager.validateFuturesChangeOrder(
                l_subAccount,
                l_ifoOpenContractChangeSpec,
                l_blnIsSkipDelayStatusCheck);
 
            log.debug("l_orderValidationResult=============" + l_orderValidationResult); 
            
            assertEquals(l_orderValidationResult.getProcessingResult().getErrorInfo(),
                WEB3ErrorCatalog.SYSTEM_ERROR_80017);
            
        }
        catch (Exception l_ex)
        {
            log.error(l_ex.getMessage(), l_ex);
            fail();
        }
        log.info(STR_METHOD_NAME + ">>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>test pass !!");
        log.exiting(TEST_END + STR_METHOD_NAME);
    }
    
    
    //  連続注文対応//TODO
    //validate先物返済注文（兩個參數）
    //validateFuturesSettleContractOrder
    public void testValidateFuturesSettleContractOrderCase1()
    {
        final String STR_METHOD_NAME = " testValidateFuturesSettleContractOrderCase1";
        log.entering(TEST_START + STR_METHOD_NAME);
        try
        {
            MOCK_MANAGER.setIsMockUsed(true);
            
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                "webbroker3.ifo.WEB3FuturesOrderManagerImpl",
                "validateOrder",
                new Class[] {SubAccount.class,String.class},
                null);
            MainAccountParams l_mainAccountParams = TestDBUtility.getMainAccountRow();
            SubAccountParams l_subAccountParams = TestDBUtility.getSubAccountRow();
            IfoOrderUnitParams l_ifoOrderUnitParams = TestDBUtility.getIfoOrderUnitRow();
            IfoOrderParams l_ifoOrderParams = TestDBUtility.getIfoOrderRow();
            ProductParams l_productParams = TestDBUtility.getProductRow();
            IfoProductParams l_ifoProductParams = TestDBUtility.getIfoProductRow();
            MarketParams l_marketParams = TestDBUtility.getMarketRow();
            IfoContractParams l_ifoContractParams = TestDBUtility.getIfoContractRow();
            IfoTradedProductParams l_ifoTradedProductParams = TestDBUtility.getIfoTradedProductRow();
            BranchParams l_branchParams = TestDBUtility.getBranchRow();
            InstitutionParams l_institutionParams = TestDBUtility.getInstitutionRow();
            TradedProductParams l_tradedProductParams = TestDBUtility.getTradedProductRow();
            IfoTradedProductUpdqParams l_ifoTradedProductUpdqParams = TestDBUtility.getIfoTradedProductUpdqRow();

            l_mainAccountParams.setAccountId(101001010010L);

            l_subAccountParams.setAccountId(101001010010L);
            l_subAccountParams.setSubAccountId(10100101001007L);
     
            l_ifoOrderParams.setOrderId(1001);
            l_ifoOrderParams.setAccountId(101001010010L);
            l_ifoOrderParams.setSubAccountId(10100101001007L);
            l_ifoOrderParams.setProductType(ProductTypeEnum.IFO);
            l_ifoOrderParams.setCreatedTimestamp(WEB3DateUtility.getDate("20070101","yyyyMMdd"));
            l_ifoOrderParams.setLastUpdatedTimestamp(WEB3DateUtility.getDate("20070101","yyyyMMdd"));
            
            l_ifoOrderUnitParams.setOrderRootDiv("9");
            l_ifoOrderUnitParams.setBizDate("20061010");
            
            l_productParams.setProductId(1006169090018L);
            l_productParams.setProductType(ProductTypeEnum.IFO);
            
            l_ifoProductParams.setProductId(1006169090018L);
            l_ifoProductParams.setProductType(ProductTypeEnum.IFO);
            l_ifoProductParams.setInstitutionCode("0D");
            l_ifoProductParams.setUnderlyingProductCode("1");
            l_ifoProductParams.setDerivativeType(IfoDerivativeTypeEnum.FUTURES);
            l_ifoProductParams.setStrikePrice(11);
            l_ifoProductParams.setMonthOfDelivery("1111");
            l_ifoProductParams.setCreatedTimestamp(WEB3DateUtility.getDate("20070202", "yyyyMMdd"));
            l_ifoProductParams.setLastUpdatedTimestamp(WEB3DateUtility.getDate("20070202", "yyyyMMdd"));
            
            l_marketParams.setMarketId(1002);

            l_ifoContractParams.setAccountId(101001010010L);
            l_ifoContractParams.setSubAccountId(10100101001007L);
            l_ifoContractParams.setProductId(1006169090018L);
            l_ifoContractParams.setDeliveryDate(WEB3DateUtility.getDate("20070202", "yyyyMMdd"));
            
            l_ifoTradedProductParams.setInstitutionCode("0D");
            l_ifoTradedProductParams.setMarketId(1002);
            l_ifoTradedProductParams.setProductId(1006169090018L);
            
            l_tradedProductParams.setProductId(1006169090018L);
            l_tradedProductParams.setMarketId(1002);
            l_tradedProductParams.setProductType(ProductTypeEnum.IFO);
            
            l_ifoTradedProductUpdqParams.setProductId(1006169090018L);
            l_ifoTradedProductUpdqParams.setInstitutionCode("0D");
            l_ifoTradedProductUpdqParams.setMarketId(1002);
            SimpleDateFormat l_format = GtlUtils.getThreadSafeSimpleDateFormat("yyyyMMdd");
            Date l_datBizDate = GtlUtils.getTradingSystem().getSystemTimestamp();
            Date l_datpreBizDate = new WEB3GentradeBizDate(
                new Timestamp(l_datBizDate.getTime())).roll(1);
            String l_strCreateDate = l_format.format(l_datpreBizDate);
            l_ifoTradedProductUpdqParams.setValidForBizDate(l_strCreateDate); 
            
            l_ifoTradedProductUpdqParams.setStartTradingDate(WEB3DateUtility.getDate("20040701","yyyyMMdd"));
            l_ifoTradedProductUpdqParams.setLastTradingDate(WEB3DateUtility.getDate("20040720","yyyyMMdd"));
            l_ifoTradedProductUpdqParams.setListDate(WEB3DateUtility.getDate("20040701","yyyyMMdd"));
            l_ifoTradedProductUpdqParams.setUnlistedDate(WEB3DateUtility.getDate("20040720","yyyyMMdd"));

            this.deleteAll();
            TestDBUtility.insertWithDel(l_ifoOrderUnitParams);
            TestDBUtility.insertWithDel(l_subAccountParams);
            TestDBUtility.insertWithDel(l_mainAccountParams);
            TestDBUtility.insertWithDel(l_ifoOrderParams);
            TestDBUtility.insertWithDel(l_productParams);
            TestDBUtility.insertWithDel(l_ifoProductParams);
            TestDBUtility.insertWithDel(l_marketParams);
            TestDBUtility.insertWithDel(l_ifoContractParams);
            TestDBUtility.insertWithDel(l_ifoTradedProductParams);
            TestDBUtility.insertWithDel(l_branchParams);
            TestDBUtility.insertWithDel(l_institutionParams);
            TestDBUtility.insertWithDel(l_tradedProductParams);
            TestDBUtility.insertWithDel(l_ifoTradedProductUpdqParams);
            
            WEB3GentradeSubAccount l_subAccount = new WEB3GentradeSubAccount(101001010010L, 10100101001007L);
            
            FinApp l_finApp = (FinApp) Services.getService(FinApp.class);
            WEB3FuturesOrderManagerImpl l_futuresOrderManager = 
                (WEB3FuturesOrderManagerImpl)l_finApp.getTradingModule(ProductTypeEnum.IFO).getOrderManager();
            
            SettleContractEntry[] l_settleContractOrderEntries = new SettleContractEntry[1];
            l_settleContractOrderEntries[0] = new SettleContractEntry(1001, 100);
            
            WEB3IfoSettleContractOrderSpec l_settleContractOrderSpec =
                WEB3IfoSettleContractOrderSpec.createSettleContractOrderSpec(
                        null,null,0.0,null,null,l_settleContractOrderEntries,null,0.0,0.0,null,null,null,true);
            NewOrderValidationResult l_result =
                l_futuresOrderManager.validateFuturesSettleContractOrder(l_subAccount, l_settleContractOrderSpec);
                
            assertEquals(l_result.getProcessingResult().getErrorInfo(), WEB3ErrorCatalog.BUSINESS_ERROR_00003);
        }
        catch (Exception l_ex)
        {
            log.error(l_ex.getMessage(), l_ex);
            fail();
        }
        
        
        log.info(STR_METHOD_NAME + ">>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>test pass !!");
        log.exiting(TEST_END + STR_METHOD_NAME);
    }
    
    //反対取引以外（パラメータ.建玉 != null）の場合
    public void testValidateFuturesSettleContractOrderCase2()
    {
        final String STR_METHOD_NAME = " testValidateFuturesSettleContractOrderCase2";
        log.entering(TEST_START + STR_METHOD_NAME);
        try
        {
            MOCK_MANAGER.setIsMockUsed(true);
            
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                "webbroker3.ifo.WEB3FuturesOrderManagerImpl",
                "validateOrder",
                new Class[] {SubAccount.class,String.class},
                null);
            MainAccountParams l_mainAccountParams = TestDBUtility.getMainAccountRow();
            SubAccountParams l_subAccountParams = TestDBUtility.getSubAccountRow();
            IfoOrderUnitParams l_ifoOrderUnitParams = TestDBUtility.getIfoOrderUnitRow();
            IfoOrderParams l_ifoOrderParams = TestDBUtility.getIfoOrderRow();
            ProductParams l_productParams = TestDBUtility.getProductRow();
            IfoProductParams l_ifoProductParams = TestDBUtility.getIfoProductRow();
            MarketParams l_marketParams = TestDBUtility.getMarketRow();
            IfoContractParams l_ifoContractParams = TestDBUtility.getIfoContractRow();
            IfoTradedProductParams l_ifoTradedProductParams = TestDBUtility.getIfoTradedProductRow();
            BranchParams l_branchParams = TestDBUtility.getBranchRow();
            InstitutionParams l_institutionParams = TestDBUtility.getInstitutionRow();
            TradedProductParams l_tradedProductParams = TestDBUtility.getTradedProductRow();
            IfoTradedProductUpdqParams l_ifoTradedProductUpdqParams = TestDBUtility.getIfoTradedProductUpdqRow();

            l_mainAccountParams.setAccountId(101001010010L);

            l_subAccountParams.setAccountId(101001010010L);
            l_subAccountParams.setSubAccountId(10100101001007L);
     
            l_ifoOrderParams.setOrderId(1001);
            l_ifoOrderParams.setAccountId(101001010010L);
            l_ifoOrderParams.setSubAccountId(10100101001007L);
            l_ifoOrderParams.setProductType(ProductTypeEnum.IFO);
            l_ifoOrderParams.setCreatedTimestamp(WEB3DateUtility.getDate("20070101","yyyyMMdd"));
            l_ifoOrderParams.setLastUpdatedTimestamp(WEB3DateUtility.getDate("20070101","yyyyMMdd"));
            
            l_ifoOrderUnitParams.setOrderRootDiv("9");
            l_ifoOrderUnitParams.setBizDate("20061010");
            
            l_productParams.setProductId(1006169090018L);
            l_productParams.setProductType(ProductTypeEnum.IFO);
            
            l_ifoProductParams.setProductId(1006169090018L);
            l_ifoProductParams.setProductType(ProductTypeEnum.IFO);
            l_ifoProductParams.setInstitutionCode("0D");
            l_ifoProductParams.setUnderlyingProductCode("1");
            l_ifoProductParams.setDerivativeType(IfoDerivativeTypeEnum.FUTURES);
            l_ifoProductParams.setStrikePrice(11);
            l_ifoProductParams.setMonthOfDelivery("1111");
            l_ifoProductParams.setCreatedTimestamp(WEB3DateUtility.getDate("20070202", "yyyyMMdd"));
            l_ifoProductParams.setLastUpdatedTimestamp(WEB3DateUtility.getDate("20070202", "yyyyMMdd"));
            
            l_marketParams.setMarketId(1002);

            l_ifoContractParams.setAccountId(101001010010L);
            l_ifoContractParams.setSubAccountId(10100101001007L);
            l_ifoContractParams.setProductId(1006169090018L);
            l_ifoContractParams.setDeliveryDate(WEB3DateUtility.getDate("20070202", "yyyyMMdd"));
            
            l_ifoTradedProductParams.setInstitutionCode("0D");
            l_ifoTradedProductParams.setMarketId(1002);
            l_ifoTradedProductParams.setProductId(1006169090018L);
            
            l_tradedProductParams.setProductId(1006169090018L);
            l_tradedProductParams.setMarketId(1002);
            l_tradedProductParams.setProductType(ProductTypeEnum.IFO);
            
            l_ifoTradedProductUpdqParams.setProductId(1006169090018L);
            l_ifoTradedProductUpdqParams.setInstitutionCode("0D");
            l_ifoTradedProductUpdqParams.setMarketId(1002);
            SimpleDateFormat l_format = GtlUtils.getThreadSafeSimpleDateFormat("yyyyMMdd");
            Date l_datBizDate = GtlUtils.getTradingSystem().getSystemTimestamp();
            Date l_datpreBizDate = new WEB3GentradeBizDate(
                new Timestamp(l_datBizDate.getTime())).roll(1);
            String l_strCreateDate = l_format.format(l_datpreBizDate);
            l_ifoTradedProductUpdqParams.setValidForBizDate(l_strCreateDate); 
            
            l_ifoTradedProductUpdqParams.setStartTradingDate(WEB3DateUtility.getDate("20040701","yyyyMMdd"));
            l_ifoTradedProductUpdqParams.setLastTradingDate(WEB3DateUtility.getDate("20040720","yyyyMMdd"));
            l_ifoTradedProductUpdqParams.setListDate(WEB3DateUtility.getDate("20040701","yyyyMMdd"));
            l_ifoTradedProductUpdqParams.setUnlistedDate(WEB3DateUtility.getDate("20040720","yyyyMMdd"));

            this.deleteAll();
            TestDBUtility.insertWithDel(l_ifoOrderUnitParams);
            TestDBUtility.insertWithDel(l_subAccountParams);
            TestDBUtility.insertWithDel(l_mainAccountParams);
            TestDBUtility.insertWithDel(l_ifoOrderParams);
            TestDBUtility.insertWithDel(l_productParams);
            TestDBUtility.insertWithDel(l_ifoProductParams);
            TestDBUtility.insertWithDel(l_marketParams);
            TestDBUtility.insertWithDel(l_ifoContractParams);
            TestDBUtility.insertWithDel(l_ifoTradedProductParams);
            TestDBUtility.insertWithDel(l_branchParams);
            TestDBUtility.insertWithDel(l_institutionParams);
            TestDBUtility.insertWithDel(l_tradedProductParams);
            TestDBUtility.insertWithDel(l_ifoTradedProductUpdqParams);
            
            WEB3GentradeSubAccount l_subAccount = new WEB3GentradeSubAccount(101001010010L, 10100101001007L);
            
            FinApp l_finApp = (FinApp) Services.getService(FinApp.class);
            WEB3FuturesOrderManagerImpl l_futuresOrderManager = 
                (WEB3FuturesOrderManagerImpl)l_finApp.getTradingModule(ProductTypeEnum.IFO).getOrderManager();
            
            SettleContractEntry[] l_settleContractOrderEntries = new SettleContractEntry[1];
            l_settleContractOrderEntries[0] = new SettleContractEntry(1001, 100);
            
            WEB3IfoSettleContractOrderSpec l_settleContractOrderSpec =
                WEB3IfoSettleContractOrderSpec.createSettleContractOrderSpec(
                        null,null,0.0,null,null,null,null,0.0,0.0,null,null,null,true);
            WEB3IfoContractImpl l_ifoContractImpl = new WEB3IfoContractImpl(l_ifoContractParams);
            NewOrderValidationResult l_result =
                l_futuresOrderManager.validateFuturesSettleContractOrder(
                    l_subAccount, l_settleContractOrderSpec, l_ifoContractImpl);
            assertEquals(l_result.getProcessingResult().getErrorInfo(), WEB3ErrorCatalog.BUSINESS_ERROR_00003);
        }
        catch (Exception l_ex)
        {
            log.error(l_ex.getMessage(), l_ex);
            fail();
        }
        
        
        log.info(STR_METHOD_NAME + ">>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>test pass !!");
        log.exiting(TEST_END + STR_METHOD_NAME);
    }
    
    //calc概算決済損益（兩個參數）
    //calcEstimateSettlementIncome
    public void testCalcEstimateSettlementIncomeCase1()
    {
        final String STR_METHOD_NAME = " testCalcEstimateSettlementIncomeCase1";
        log.entering(TEST_START + STR_METHOD_NAME);
        try
        {
            MOCK_MANAGER.setIsMockUsed(true);
            
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                "webbroker3.ifo.WEB3FuturesOrderManagerImpl",
                "validateOrder",
                new Class[] {SubAccount.class,String.class},
                null);
            MainAccountParams l_mainAccountParams = TestDBUtility.getMainAccountRow();
            SubAccountParams l_subAccountParams = TestDBUtility.getSubAccountRow();
            IfoOrderUnitParams l_ifoOrderUnitParams = TestDBUtility.getIfoOrderUnitRow();
            IfoOrderParams l_ifoOrderParams = TestDBUtility.getIfoOrderRow();
            ProductParams l_productParams = TestDBUtility.getProductRow();
            IfoProductParams l_ifoProductParams = TestDBUtility.getIfoProductRow();
            MarketParams l_marketParams = TestDBUtility.getMarketRow();
            IfoContractParams l_ifoContractParams = TestDBUtility.getIfoContractRow();
            IfoTradedProductParams l_ifoTradedProductParams = TestDBUtility.getIfoTradedProductRow();
            l_ifoTradedProductParams.setValidForBizDate(null);
            BranchParams l_branchParams = TestDBUtility.getBranchRow();
            InstitutionParams l_institutionParams = TestDBUtility.getInstitutionRow();
            TradedProductParams l_tradedProductParams = TestDBUtility.getTradedProductRow();
            IfoTradedProductUpdqParams l_ifoTradedProductUpdqParams = TestDBUtility.getIfoTradedProductUpdqRow();

            l_mainAccountParams.setAccountId(101001010010L);

            l_subAccountParams.setAccountId(101001010010L);
            l_subAccountParams.setSubAccountId(10100101001007L);
     
            l_ifoOrderParams.setOrderId(1001);
            l_ifoOrderParams.setAccountId(101001010010L);
            l_ifoOrderParams.setSubAccountId(10100101001007L);
            l_ifoOrderParams.setProductType(ProductTypeEnum.IFO);
            l_ifoOrderParams.setCreatedTimestamp(WEB3DateUtility.getDate("20070101","yyyyMMdd"));
            l_ifoOrderParams.setLastUpdatedTimestamp(WEB3DateUtility.getDate("20070101","yyyyMMdd"));
            
            l_ifoOrderUnitParams.setOrderRootDiv("9");
            l_ifoOrderUnitParams.setBizDate("20061010");
            
            l_productParams.setProductId(1006169090018L);
            l_productParams.setProductType(ProductTypeEnum.IFO);
            
            l_ifoProductParams.setProductId(1006169090018L);
            l_ifoProductParams.setProductType(ProductTypeEnum.IFO);
            l_ifoProductParams.setInstitutionCode("0D");
            l_ifoProductParams.setUnderlyingProductCode("1");
            l_ifoProductParams.setDerivativeType(IfoDerivativeTypeEnum.FUTURES);
            l_ifoProductParams.setStrikePrice(11);
            l_ifoProductParams.setMonthOfDelivery("1111");
            l_ifoProductParams.setCreatedTimestamp(WEB3DateUtility.getDate("20070202", "yyyyMMdd"));
            l_ifoProductParams.setLastUpdatedTimestamp(WEB3DateUtility.getDate("20070202", "yyyyMMdd"));
            
            l_marketParams.setMarketId(1002);

            l_ifoContractParams.setAccountId(101001010010L);
            l_ifoContractParams.setSubAccountId(10100101001007L);
            l_ifoContractParams.setProductId(1006169090018L);
            l_ifoContractParams.setDeliveryDate(WEB3DateUtility.getDate("20070202", "yyyyMMdd"));
            
            l_ifoTradedProductParams.setInstitutionCode("0D");
            l_ifoTradedProductParams.setMarketId(1002);
            l_ifoTradedProductParams.setProductId(1006169090018L);
            
            l_tradedProductParams.setProductId(1006169090018L);
            l_tradedProductParams.setMarketId(1002);
            l_tradedProductParams.setProductType(ProductTypeEnum.IFO);
            
            l_ifoTradedProductUpdqParams.setProductId(1006169090018L);
            l_ifoTradedProductUpdqParams.setInstitutionCode("0D");
            l_ifoTradedProductUpdqParams.setMarketId(1002);
            SimpleDateFormat l_format = GtlUtils.getThreadSafeSimpleDateFormat("yyyyMMdd");
            Date l_datBizDate = GtlUtils.getTradingSystem().getSystemTimestamp();
            Date l_datpreBizDate = new WEB3GentradeBizDate(
                new Timestamp(l_datBizDate.getTime())).roll(1);
            String l_strCreateDate = l_format.format(l_datpreBizDate);
            l_ifoTradedProductUpdqParams.setValidForBizDate(l_strCreateDate); 
            
            l_ifoTradedProductUpdqParams.setStartTradingDate(WEB3DateUtility.getDate("20040701","yyyyMMdd"));
            l_ifoTradedProductUpdqParams.setLastTradingDate(WEB3DateUtility.getDate("20040720","yyyyMMdd"));
            l_ifoTradedProductUpdqParams.setListDate(WEB3DateUtility.getDate("20040701","yyyyMMdd"));
            l_ifoTradedProductUpdqParams.setUnlistedDate(WEB3DateUtility.getDate("20040720","yyyyMMdd"));

            this.deleteAll();
            TestDBUtility.insertWithDel(l_ifoOrderUnitParams);
            TestDBUtility.insertWithDel(l_subAccountParams);
            TestDBUtility.insertWithDel(l_mainAccountParams);
            TestDBUtility.insertWithDel(l_ifoOrderParams);
            TestDBUtility.insertWithDel(l_productParams);
            TestDBUtility.insertWithDel(l_ifoProductParams);
            TestDBUtility.insertWithDel(l_marketParams);
            TestDBUtility.insertWithDel(l_ifoContractParams);
            TestDBUtility.insertWithDel(l_ifoTradedProductParams);
            TestDBUtility.insertWithDel(l_branchParams);
            TestDBUtility.insertWithDel(l_institutionParams);
            TestDBUtility.insertWithDel(l_tradedProductParams);
            TestDBUtility.insertWithDel(l_ifoTradedProductUpdqParams);
            TestDBUtility.commit();
            
            FinApp l_finApp = (FinApp) Services.getService(FinApp.class);
            WEB3FuturesOrderManagerImpl l_futuresOrderManager = 
                (WEB3FuturesOrderManagerImpl)l_finApp.getTradingModule(ProductTypeEnum.IFO).getOrderManager();
            
            SettleContractEntry[] l_settleContractOrderEntries = new SettleContractEntry[1];
            l_settleContractOrderEntries[0] = new SettleContractEntry(1001, 100);
 
            WEB3GentradeCommission l_commission = new WEB3GentradeCommission();

            WEB3IfoTradedProductImpl l_TradedProductImpl = new WEB3IfoTradedProductImpl(l_tradedProductParams);

                l_futuresOrderManager.calcEstimateSettlementIncome(
                    l_commission, 0.0, null, l_TradedProductImpl, l_settleContractOrderEntries,
                    0.0, SideEnum.BUY, true);
        }
        catch (WEB3BaseRuntimeException l_ex)
        {
            assertEquals(l_ex.getErrorInfo(), WEB3ErrorCatalog.SYSTEM_ERROR_80017);
        }
        catch (Exception l_ex)
        {
            log.error(l_ex.getMessage(), l_ex);
            fail();
        }
        
        
        log.info(STR_METHOD_NAME + ">>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>test pass !!");
        log.exiting(TEST_END + STR_METHOD_NAME);
    }
    
    //calcEstimateSettlementIncome
    //calc概算決済損益（三個參數）
    public void testCalcEstimateSettlementIncomeCase2()
    {
        final String STR_METHOD_NAME = " testCalcEstimateSettlementIncomeCase2";
        log.entering(TEST_START + STR_METHOD_NAME);
        try
        {
            MOCK_MANAGER.setIsMockUsed(true);
            
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                "webbroker3.ifo.WEB3FuturesOrderManagerImpl",
                "validateOrder",
                new Class[] {SubAccount.class,String.class},
                null);
            MainAccountParams l_mainAccountParams = TestDBUtility.getMainAccountRow();
            SubAccountParams l_subAccountParams = TestDBUtility.getSubAccountRow();
            IfoOrderUnitParams l_ifoOrderUnitParams = TestDBUtility.getIfoOrderUnitRow();
            IfoOrderParams l_ifoOrderParams = TestDBUtility.getIfoOrderRow();
            ProductParams l_productParams = TestDBUtility.getProductRow();
            IfoProductParams l_ifoProductParams = TestDBUtility.getIfoProductRow();
            MarketParams l_marketParams = TestDBUtility.getMarketRow();
            IfoContractParams l_ifoContractParams = TestDBUtility.getIfoContractRow();
            IfoTradedProductParams l_ifoTradedProductParams = TestDBUtility.getIfoTradedProductRow();
            l_ifoTradedProductParams.setValidForBizDate(null);
            BranchParams l_branchParams = TestDBUtility.getBranchRow();
            InstitutionParams l_institutionParams = TestDBUtility.getInstitutionRow();
            TradedProductParams l_tradedProductParams = TestDBUtility.getTradedProductRow();
            IfoTradedProductUpdqParams l_ifoTradedProductUpdqParams = TestDBUtility.getIfoTradedProductUpdqRow();

            l_mainAccountParams.setAccountId(101001010010L);

            l_subAccountParams.setAccountId(101001010010L);
            l_subAccountParams.setSubAccountId(10100101001007L);
     
            l_ifoOrderParams.setOrderId(1001);
            l_ifoOrderParams.setAccountId(101001010010L);
            l_ifoOrderParams.setSubAccountId(10100101001007L);
            l_ifoOrderParams.setProductType(ProductTypeEnum.IFO);
            l_ifoOrderParams.setCreatedTimestamp(WEB3DateUtility.getDate("20070101","yyyyMMdd"));
            l_ifoOrderParams.setLastUpdatedTimestamp(WEB3DateUtility.getDate("20070101","yyyyMMdd"));
            
            l_ifoOrderUnitParams.setOrderRootDiv("9");
            l_ifoOrderUnitParams.setBizDate("20061010");
            
            l_productParams.setProductId(1006169090018L);
            l_productParams.setProductType(ProductTypeEnum.IFO);
            
            l_ifoProductParams.setProductId(1006169090018L);
            l_ifoProductParams.setProductType(ProductTypeEnum.IFO);
            l_ifoProductParams.setInstitutionCode("0D");
            l_ifoProductParams.setUnderlyingProductCode("1");
            l_ifoProductParams.setDerivativeType(IfoDerivativeTypeEnum.FUTURES);
            l_ifoProductParams.setStrikePrice(11);
            l_ifoProductParams.setMonthOfDelivery("1111");
            l_ifoProductParams.setCreatedTimestamp(WEB3DateUtility.getDate("20070202", "yyyyMMdd"));
            l_ifoProductParams.setLastUpdatedTimestamp(WEB3DateUtility.getDate("20070202", "yyyyMMdd"));
            
            l_marketParams.setMarketId(1002);

            l_ifoContractParams.setAccountId(101001010010L);
            l_ifoContractParams.setSubAccountId(10100101001007L);
            l_ifoContractParams.setProductId(1006169090018L);
            l_ifoContractParams.setDeliveryDate(WEB3DateUtility.getDate("20070202", "yyyyMMdd"));
            
            l_ifoTradedProductParams.setInstitutionCode("0D");
            l_ifoTradedProductParams.setMarketId(1002);
            l_ifoTradedProductParams.setProductId(1006169090018L);
            
            l_tradedProductParams.setProductId(1006169090018L);
            l_tradedProductParams.setMarketId(1002);
            l_tradedProductParams.setProductType(ProductTypeEnum.IFO);
            
            l_ifoTradedProductUpdqParams.setProductId(1006169090018L);
            l_ifoTradedProductUpdqParams.setInstitutionCode("0D");
            l_ifoTradedProductUpdqParams.setMarketId(1002);
            SimpleDateFormat l_format = GtlUtils.getThreadSafeSimpleDateFormat("yyyyMMdd");
            Date l_datBizDate = GtlUtils.getTradingSystem().getSystemTimestamp();
            Date l_datpreBizDate = new WEB3GentradeBizDate(
                new Timestamp(l_datBizDate.getTime())).roll(1);
            String l_strCreateDate = l_format.format(l_datpreBizDate);
            l_ifoTradedProductUpdqParams.setValidForBizDate(l_strCreateDate); 
            
            l_ifoTradedProductUpdqParams.setStartTradingDate(WEB3DateUtility.getDate("20040701","yyyyMMdd"));
            l_ifoTradedProductUpdqParams.setLastTradingDate(WEB3DateUtility.getDate("20040720","yyyyMMdd"));
            l_ifoTradedProductUpdqParams.setListDate(WEB3DateUtility.getDate("20040701","yyyyMMdd"));
            l_ifoTradedProductUpdqParams.setUnlistedDate(WEB3DateUtility.getDate("20040720","yyyyMMdd"));

            this.deleteAll();
            TestDBUtility.insertWithDel(l_ifoOrderUnitParams);
            TestDBUtility.insertWithDel(l_subAccountParams);
            TestDBUtility.insertWithDel(l_mainAccountParams);
            TestDBUtility.insertWithDel(l_ifoOrderParams);
            TestDBUtility.insertWithDel(l_productParams);
            TestDBUtility.insertWithDel(l_ifoProductParams);
            TestDBUtility.insertWithDel(l_marketParams);
            TestDBUtility.insertWithDel(l_ifoContractParams);
            TestDBUtility.insertWithDel(l_ifoTradedProductParams);
            TestDBUtility.insertWithDel(l_branchParams);
            TestDBUtility.insertWithDel(l_institutionParams);
            TestDBUtility.insertWithDel(l_tradedProductParams);
            TestDBUtility.insertWithDel(l_ifoTradedProductUpdqParams);
            TestDBUtility.commit();
            
            FinApp l_finApp = (FinApp) Services.getService(FinApp.class);
            WEB3FuturesOrderManagerImpl l_futuresOrderManager = 
                (WEB3FuturesOrderManagerImpl)l_finApp.getTradingModule(ProductTypeEnum.IFO).getOrderManager();
            
            SettleContractEntry[] l_settleContractOrderEntries = new SettleContractEntry[1];
            l_settleContractOrderEntries[0] = new SettleContractEntry(1001, 100);
            
            WEB3GentradeCommission l_commission = new WEB3GentradeCommission();

            WEB3IfoTradedProductImpl l_TradedProductImpl = new WEB3IfoTradedProductImpl(l_tradedProductParams);
            WEB3IfoContractImpl l_ifoContractImpl = new WEB3IfoContractImpl(l_ifoContractParams);
                l_futuresOrderManager.calcEstimateSettlementIncome(
                    l_commission, 0.0, null, l_TradedProductImpl, l_settleContractOrderEntries,
                    0.0, SideEnum.BUY, true, l_ifoContractImpl);
        }
        catch (WEB3BaseRuntimeException l_ex)
        {
            assertEquals(l_ex.getErrorInfo(), WEB3ErrorCatalog.SYSTEM_ERROR_80017);
        }
        catch (Exception l_ex)
        {
            log.error(l_ex.getMessage(), l_ex);
            fail();
        }
        
        
        log.info(STR_METHOD_NAME + ">>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>test pass !!");
        log.exiting(TEST_END + STR_METHOD_NAME);
    }
    
    public void deleteAll()
    {
        try
        {
            TestDBUtility.deleteAll(IfoOrderUnitRow.TYPE);
            TestDBUtility.deleteAll(SubAccountRow.TYPE);
            TestDBUtility.deleteAll(MainAccountRow.TYPE);
            TestDBUtility.deleteAll(IfoOrderRow.TYPE);
            TestDBUtility.deleteAll(ProductRow.TYPE);
            TestDBUtility.deleteAll(IfoProductRow.TYPE);
            TestDBUtility.deleteAll(MarketRow.TYPE);
            TestDBUtility.deleteAll(IfoContractRow.TYPE);
            TestDBUtility.deleteAll(IfoTradedProductRow.TYPE);
            TestDBUtility.deleteAll(BranchRow.TYPE);
            TestDBUtility.deleteAll(InstitutionRow.TYPE);
            TestDBUtility.deleteAll(TradedProductRow.TYPE);
            TestDBUtility.deleteAll(IfoTradedProductUpdqRow.TYPE);
            TestDBUtility.deleteAll(TradedProductCalendarRow.TYPE);
            TestDBUtility.deleteAll(OrderexecutionEndRow.TYPE);
        }
        catch (Exception l_ex)
        {
            log.error(l_ex.getMessage(), l_ex);
            fail();
        }
        log.info("*******************deleteAll***************** !!");
    }
    
    public void testCalcChangeEstimatePrice_C0001()
    {
        final String STR_METHOD_NAME = "testCalcChangeEstimatePrice_C0001";
        log.entering(TEST_START + STR_METHOD_NAME);
        
        try
        {
            MOCK_MANAGER.setIsMockUsed(true);
            
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                "webbroker3.ifo.WEB3IfoBizLogicProvider", 
                "calcRestraintTurnOver",
                new Class[] 
                {
                    double.class, 
                    double.class, 
                    long.class, 
                    String.class, 
                    boolean.class, 
                    WEB3IfoTradedProductImpl.class
                },
                new Double(50.0D));
            
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                "webbroker3.ifo.WEB3IfoBizLogicProvider", 
                "calcCommission",
                new Class[] {WEB3GentradeCommission.class, SubAccount.class},
                null);
            
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                "webbroker3.ifo.WEB3IfoBizLogicProvider", 
                "calcSalesTax",
                new Class[] {double.class, Timestamp.class, SubAccount.class},
                new Double(0.0D));

            FinApp l_finApp = (FinApp) Services.getService(FinApp.class);
            WEB3FuturesOrderManagerImpl l_futuresOrderManager = 
                (WEB3FuturesOrderManagerImpl)l_finApp.getTradingModule(ProductTypeEnum.IFO).getOrderManager();
            
            WEB3GentradeCommission l_commission = new WEB3GentradeCommission();
            l_commission.setBranchId(338L);
            
            double l_dblLimitPrice = 5.0;;
            WEB3GentradeSubAccount l_subAccount = null;
            WEB3IfoTradedProductImpl l_futuresOptionTradedProduct = null;
            double l_dblQuantity = 500.0;
            double l_dblExecQuantity = 120.0;
            double l_dblSumTransferredAssetBookValue = 100.0;
            boolean l_blnIsSkipPriceCheck = true;

            WEB3IfoEstimateDeliveryAmountCalcResult l_result =
                l_futuresOrderManager.calcChangeEstimatePrice(
                    l_commission,
                    l_dblLimitPrice,
                    l_subAccount,
                    l_futuresOptionTradedProduct,
                    l_dblQuantity,
                    l_dblExecQuantity,
                    l_dblSumTransferredAssetBookValue,
                    l_blnIsSkipPriceCheck);
           
            WEB3MockObjectParamsValue l_paramsValue = TestBaseForMock.MOCK_MANAGER.getMethodParamsValue(
                "webbroker3.ifo.WEB3IfoBizLogicProvider", 
                "calcRestraintTurnOver",
                new Class[]
                {
                    double.class,
                    double.class,
                    long.class,
                    String.class,
                    boolean.class,
                    WEB3IfoTradedProductImpl.class
                });
            
            assertEquals(new Double(380.0D), l_paramsValue.getFirstCalled()[0]);
            
            assertEquals(150.0D, l_result.getEstimateDeliveryAmount(), 0);
        }
        catch (Exception l_ex)
        {
            log.error(l_ex.getMessage(), l_ex);
            fail();
        }
        
        log.info(STR_METHOD_NAME + ">>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>test pass !!");
        log.exiting(TEST_END + STR_METHOD_NAME);
    }
    
    public void testCalcChangeEstimateSettlementIncome_C0001()
    {
        final String STR_METHOD_NAME = "testCalcChangeEstimateSettlementIncome_C0001";
        log.entering(TEST_START + STR_METHOD_NAME);
        
        try
        {
            MOCK_MANAGER.setIsMockUsed(true);
            
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                "webbroker3.ifo.WEB3IfoBizLogicProvider", 
                "calcTurnOver",
                new Class[] 
                {
                    double.class, 
                    double.class,  
                    WEB3IfoTradedProductImpl.class
                },
                new Double(100.05D));
            
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                "webbroker3.ifo.WEB3IfoBizLogicProvider", 
                "calcCommission",
                new Class[] {WEB3GentradeCommission.class, SubAccount.class},
                null);
            
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                "webbroker3.ifo.WEB3IfoBizLogicProvider",
                "calcDeliveryAmount",
                new Class[]
                {
                    SideEnum.class, 
                    double.class, 
                    double.class, 
                    double.class
                },
                new Double(555.005D));

            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                "webbroker3.ifo.WEB3IfoBizLogicProvider", 
                "calcSalesTax",
                new Class[] {double.class, Timestamp.class, SubAccount.class},
                new Double(0.0D));

            IfoOrderUnitParams l_ifoOrderUnitParams = TestDBUtility.getIfoOrderUnitRow();
            l_ifoOrderUnitParams.setExecutedAmount(122.15);

            ProductParams l_productParams = TestDBUtility.getProductRow();
            
            IfoProductParams l_ifoProductParams = TestDBUtility.getIfoProductRow();
            
            MarketParams l_marketParams = TestDBUtility.getMarketRow();
            l_marketParams.setMarketId(1002);
            
            IfoContractParams l_ifoContractParams = TestDBUtility.getIfoContractRow();
            l_ifoContractParams.setQuantity(0.0);
            l_ifoContractParams.setMarketId(1002);
            l_ifoContractParams.setUnitSize(1000.0);
            l_ifoContractParams.setContractPrice(1234.0);
            
            IfoTradedProductParams l_ifoTradedProductParams = TestDBUtility.getIfoTradedProductRow();

            TradedProductParams l_tradedProductParams = TestDBUtility.getTradedProductRow();

            l_productParams.setProductId(1006169090018L);
            l_productParams.setProductType(ProductTypeEnum.IFO);

            l_ifoProductParams.setProductId(1006169090018L);
            l_ifoProductParams.setProductType(ProductTypeEnum.IFO);
            l_ifoProductParams.setInstitutionCode("0D");

            l_ifoContractParams.setProductId(1006169090018L);

            l_ifoTradedProductParams.setInstitutionCode("0D");
            l_ifoTradedProductParams.setMarketId(1002);
            l_ifoTradedProductParams.setProductId(1006169090018L);
   
            l_tradedProductParams.setProductId(1006169090018L);
            l_tradedProductParams.setMarketId(1002);
            l_tradedProductParams.setProductType(ProductTypeEnum.IFO);
            
            this.deleteAll();
            TestDBUtility.insertWithDel(l_ifoOrderUnitParams);

            TestDBUtility.insertWithDel(l_productParams);
            TestDBUtility.insertWithDel(l_ifoProductParams);
            TestDBUtility.insertWithDel(l_marketParams);
            TestDBUtility.insertWithDel(l_ifoContractParams);
            TestDBUtility.insertWithDel(l_ifoTradedProductParams);

            TestDBUtility.insertWithDel(l_tradedProductParams);

            FinApp l_finApp = (FinApp) Services.getService(FinApp.class);
            WEB3FuturesOrderManagerImpl l_futuresOrderManager = 
                (WEB3FuturesOrderManagerImpl)l_finApp.getTradingModule(ProductTypeEnum.IFO).getOrderManager();

            WEB3GentradeCommission l_commission = new WEB3GentradeCommission();
            l_commission.setBranchId(338L);

            double l_dblLimitPrice = 5.5;
            WEB3GentradeSubAccount l_subAccount = null;
            WEB3IfoTradedProductImpl l_futuresOptionTradedProduct = null;
            
            SettleContractEntry[] l_settleContractEntry = new SettleContractEntry[1];
            l_settleContractEntry[0] = new SettleContractEntry(1001L, 0.0);
            
            double l_dblQuantity = 200.15;
            SideEnum l_dealing = SideEnum.BUY;
            double l_dblExecQuantity = 89.05;
            long l_lngOrderUnitId = 1001L;
            boolean l_blnIsSkipPriceCheck = true;

            WEB3IfoEstimateDeliveryAmountCalcResult l_result =
                l_futuresOrderManager.calcChangeEstimateSettlementIncome(
                    l_commission,
                    l_dblLimitPrice,
                    l_subAccount,
                    l_futuresOptionTradedProduct,
                    l_settleContractEntry,
                    l_dblQuantity,
                    l_dealing,
                    l_dblExecQuantity,
                    l_lngOrderUnitId,
                    l_blnIsSkipPriceCheck);
            
            WEB3MockObjectParamsValue l_paramsValue3 = TestBaseForMock.MOCK_MANAGER.getMethodParamsValue(
                "webbroker3.ifo.WEB3IfoBizLogicProvider",
                "calcDeliveryAmount",
                new Class[]
                {
                    SideEnum.class, 
                    double.class, 
                    double.class, 
                    double.class
                });
            assertEquals(new Double(222.2D), l_paramsValue3.getCalled(0)[1]);
            assertEquals(new Double(0.0D), l_paramsValue3.getCalled(1)[1]);
            assertEquals(new Double(0.0D), l_paramsValue3.getCalled(1)[2]);
            assertEquals(new Double(0.0D), l_paramsValue3.getCalled(1)[3]);


            assertEquals(0.0D, l_result.getEstimateDeliveryAmount(), 0);
        }
        catch (Exception l_ex)
        {
            log.error(l_ex.getMessage(), l_ex);
            fail();
        }
        
        log.info(STR_METHOD_NAME + ">>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>test pass !!");
        log.exiting(TEST_END + STR_METHOD_NAME);
    }
    
    public void testCalcEstimateSettlementIncome_C0003()
    {
        final String STR_METHOD_NAME = "testCalcEstimateSettlementIncome_C0003";
        log.entering(TEST_START + STR_METHOD_NAME);
        
        try
        {
            MOCK_MANAGER.setIsMockUsed(true);
            
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                "webbroker3.ifo.WEB3IfoBizLogicProvider", 
                "calcTurnOver",
                new Class[] 
                {
                    double.class, 
                    double.class,  
                    WEB3IfoTradedProductImpl.class
                },
                new Double(0.0D));//
            
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                "webbroker3.ifo.WEB3IfoBizLogicProvider", 
                "calcCommission",
                new Class[] {WEB3GentradeCommission.class, SubAccount.class},
                null);
            
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                "webbroker3.ifo.WEB3IfoBizLogicProvider",
                "calcDeliveryAmount",
                new Class[]
                {
                    SideEnum.class, 
                    double.class, 
                    double.class, 
                    double.class
                },
                new Double(555.005D));

            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                "webbroker3.ifo.WEB3IfoBizLogicProvider", 
                "calcSalesTax",
                new Class[] {double.class, Timestamp.class, SubAccount.class},
                new Double(0.0D));
  
            IfoOrderUnitParams l_ifoOrderUnitParams = TestDBUtility.getIfoOrderUnitRow();
            l_ifoOrderUnitParams.setExecutedAmount(122.15);

            ProductParams l_productParams = TestDBUtility.getProductRow();
            
            IfoProductParams l_ifoProductParams = TestDBUtility.getIfoProductRow();
            
            MarketParams l_marketParams = TestDBUtility.getMarketRow();
            l_marketParams.setMarketId(1002);
            
            IfoContractParams l_ifoContractParams = TestDBUtility.getIfoContractRow();
            l_ifoContractParams.setQuantity(0.0);
            l_ifoContractParams.setMarketId(1002);
            l_ifoContractParams.setUnitSize(1000.0);
            l_ifoContractParams.setContractPrice(1234.0);
            
            IfoTradedProductParams l_ifoTradedProductParams = TestDBUtility.getIfoTradedProductRow();

            TradedProductParams l_tradedProductParams = TestDBUtility.getTradedProductRow();
    
            l_productParams.setProductId(1006169090018L);
            l_productParams.setProductType(ProductTypeEnum.IFO);
 
            l_ifoProductParams.setProductId(1006169090018L);
            l_ifoProductParams.setProductType(ProductTypeEnum.IFO);
            l_ifoProductParams.setInstitutionCode("0D");

            l_ifoContractParams.setProductId(1006169090018L);
   
            l_ifoTradedProductParams.setInstitutionCode("0D");
            l_ifoTradedProductParams.setMarketId(1002);
            l_ifoTradedProductParams.setProductId(1006169090018L);
 
            l_tradedProductParams.setProductId(1006169090018L);
            l_tradedProductParams.setMarketId(1002);
            l_tradedProductParams.setProductType(ProductTypeEnum.IFO);
            
            this.deleteAll();
            TestDBUtility.insertWithDel(l_ifoOrderUnitParams);
            TestDBUtility.insertWithDel(l_productParams);
            TestDBUtility.insertWithDel(l_ifoProductParams);
            TestDBUtility.insertWithDel(l_marketParams);
            TestDBUtility.insertWithDel(l_ifoContractParams);
            TestDBUtility.insertWithDel(l_ifoTradedProductParams);
            TestDBUtility.insertWithDel(l_tradedProductParams);

            FinApp l_finApp = (FinApp) Services.getService(FinApp.class);
            WEB3FuturesOrderManagerImpl l_futuresOrderManager = 
                (WEB3FuturesOrderManagerImpl)l_finApp.getTradingModule(ProductTypeEnum.IFO).getOrderManager();
           
            WEB3GentradeCommission l_commission = new WEB3GentradeCommission();
            l_commission.setBranchId(338L);

            double l_dblLimitPrice = 5.5;
            WEB3GentradeSubAccount l_subAccount = null;
            WEB3IfoTradedProductImpl l_futuresOptionTradedProduct = null;
            
            SettleContractEntry[] l_settleContractEntry = new SettleContractEntry[1];
            l_settleContractEntry[0] = new SettleContractEntry(1001L, 0.0);
            
            double l_dblQuantity = 200.15;
            SideEnum l_dealing = SideEnum.BUY;
            boolean l_blnIsSkipPriceCheck = true;

            WEB3IfoContractImpl l_ifoContractImpl = new WEB3IfoContractImplForTest(1001);

            WEB3IfoEstimateDeliveryAmountCalcResult l_result =
                l_futuresOrderManager.calcEstimateSettlementIncome(
                    l_commission,
                    l_dblLimitPrice,
                    l_subAccount,
                    l_futuresOptionTradedProduct,
                    l_settleContractEntry,
                    l_dblQuantity,
                    l_dealing,
                    l_blnIsSkipPriceCheck,
                    l_ifoContractImpl);
            
            WEB3MockObjectParamsValue l_paramsValue3 = TestBaseForMock.MOCK_MANAGER.getMethodParamsValue(
                "webbroker3.ifo.WEB3IfoBizLogicProvider",
                "calcDeliveryAmount",
                new Class[]
                {
                    SideEnum.class, 
                    double.class, 
                    double.class, 
                    double.class
                });
            assertEquals(new Double(1.111D), l_paramsValue3.getCalled(1)[1]);
            assertEquals(new Double(2.222D), l_paramsValue3.getCalled(1)[2]);
            assertEquals(new Double(3.333D), l_paramsValue3.getCalled(1)[3]);


            assertEquals(0.0D, l_result.getEstimateDeliveryAmount(), 0);
        }
        catch (Exception l_ex)
        {
            log.error(l_ex.getMessage(), l_ex);
            fail();
        }
        
        log.info(STR_METHOD_NAME + ">>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>test pass !!");
        log.exiting(TEST_END + STR_METHOD_NAME);
    }
    
    private class WEB3IfoContractImplForTest extends WEB3IfoContractImpl
    {

        public WEB3IfoContractImplForTest(long l_lngContractId) throws DataQueryException, DataNetworkException
        {
            super(l_lngContractId);
        } 

        public double getContractExecutedAmount(double l_dblCount) throws WEB3BaseException
        {
            return 1.111;
        }
        
        public double getContractCommission(double l_dblContractCnt)
        {
            return 2.222;
        }
        
        public double getContractCommissionConsumptionTax(double l_dblContractCnt)
        {
            return 3.333;
        }

    }

}
@
