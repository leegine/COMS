head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.04.07.00.48.02;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	5444d9d078c63f4;
filename	WEB3FuturesChangeClosingContractInputServiceImplTest.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : 先物訂正返済入力サービスImplTest(WEB3FuturesChangeClosingContractInputServiceImplTest.java)
Author Name      : Daiwa Institute of Research
Revision History : 2007/06/26 張騰宇 (中訊) 新規作成
*/
package webbroker3.ifo.service.delegate.stdimpls;

import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import com.fitechlabs.xtrade.kernel.boot.Services;
import com.fitechlabs.xtrade.kernel.data.DataFindException;
import com.fitechlabs.xtrade.kernel.data.DataNetworkException;
import com.fitechlabs.xtrade.kernel.data.DataQueryException;
import com.fitechlabs.xtrade.kernel.util.ThreadLocalSystemAttributesRegistry;
import com.fitechlabs.xtrade.plugin.tc.gentrade.BooleanEnum;
import com.fitechlabs.xtrade.plugin.tc.gentrade.ContractTypeEnum;
import com.fitechlabs.xtrade.plugin.tc.gentrade.FinApp;
import com.fitechlabs.xtrade.plugin.tc.gentrade.GtlUtils;
import com.fitechlabs.xtrade.plugin.tc.gentrade.NotFoundException;
import com.fitechlabs.xtrade.plugin.tc.gentrade.Order;
import com.fitechlabs.xtrade.plugin.tc.gentrade.OrderAction;
import com.fitechlabs.xtrade.plugin.tc.gentrade.OrderCategEnum;
import com.fitechlabs.xtrade.plugin.tc.gentrade.OrderExecution;
import com.fitechlabs.xtrade.plugin.tc.gentrade.OrderExpirationStatusEnum;
import com.fitechlabs.xtrade.plugin.tc.gentrade.OrderOpenStatusEnum;
import com.fitechlabs.xtrade.plugin.tc.gentrade.OrderStatusEnum;
import com.fitechlabs.xtrade.plugin.tc.gentrade.OrderTypeEnum;
import com.fitechlabs.xtrade.plugin.tc.gentrade.Product;
import com.fitechlabs.xtrade.plugin.tc.gentrade.ProductTypeEnum;
import com.fitechlabs.xtrade.plugin.tc.gentrade.SideEnum;
import com.fitechlabs.xtrade.plugin.tc.gentrade.SubAccount;
import com.fitechlabs.xtrade.plugin.tc.gentrade.TaxTypeEnum;
import com.fitechlabs.xtrade.plugin.tc.gentrade.TradedProduct;
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
import com.fitechlabs.xtrade.plugin.tc.xbifo.IfoOrderExecutionConditionType;
import com.fitechlabs.xtrade.plugin.tc.xbifo.IfoOrderUnit;
import com.fitechlabs.xtrade.plugin.tc.xbifo.IfoTradedProduct;
import com.fitechlabs.xtrade.plugin.tc.xbifo.data.IfoClosingContractSpecParams;
import com.fitechlabs.xtrade.plugin.tc.xbifo.data.IfoClosingContractSpecRow;
import com.fitechlabs.xtrade.plugin.tc.xbifo.data.IfoContractParams;
import com.fitechlabs.xtrade.plugin.tc.xbifo.data.IfoContractRow;
import com.fitechlabs.xtrade.plugin.tc.xbifo.data.IfoOrderParams;
import com.fitechlabs.xtrade.plugin.tc.xbifo.data.IfoOrderUnitParams;
import com.fitechlabs.xtrade.plugin.tc.xbifo.data.IfoOrderUnitRow;
import com.fitechlabs.xtrade.plugin.tc.xbifo.data.IfoProductParams;
import com.fitechlabs.xtrade.plugin.tc.xbifo.data.IfoProductRow;
import com.fitechlabs.xtrade.plugin.tc.xbifo.data.IfoTradedProductParams;
import com.fitechlabs.xtrade.plugin.tc.xbifo.data.IfoTradedProductRow;
import com.fitechlabs.xtrade.plugin.tc.xbifo.data.IfoTradedProductUpdqParams;
import com.fitechlabs.xtrade.plugin.tc.xbifo.data.IfoTradedProductUpdqRow;
import com.fitechlabs.xtrade.plugin.tc.xbifo.stdimpls.IfoOrderImpl;

import test.util.TestDBUtility;

import webbroker3.common.WEB3BaseException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.common.WEB3SystemLayerException;
import webbroker3.common.define.WEB3CarriedOrderLapseDateSpecDef;
import webbroker3.common.define.WEB3ChannelDef;
import webbroker3.gentrade.WEB3GentradeBizDate;
import webbroker3.gentrade.WEB3GentradeBranch;
import webbroker3.gentrade.WEB3GentradeInstitution;
import webbroker3.gentrade.WEB3GentradeMainAccount;
import webbroker3.gentrade.WEB3GentradeMainAccountForMock;
import webbroker3.gentrade.WEB3GentradeMarket;
import webbroker3.gentrade.WEB3GentradeSubAccount;
import webbroker3.gentrade.WEB3GentradeSubAccountForMock;
import webbroker3.gentrade.WEB3GentradeTradingTimeManagementForMock;
import webbroker3.gentrade.data.EnableOrderConditionParams;
import webbroker3.gentrade.data.EnableOrderConditionRow;
import webbroker3.gentrade.data.OrderexecutionEndParams;
import webbroker3.gentrade.data.OrderexecutionEndRow;
import webbroker3.gentrade.data.TradingTimeRow;
import webbroker3.ifo.WEB3IfoProductImpl;
import webbroker3.ifo.WEB3IfoTradedProductImpl;
import webbroker3.ifo.message.WEB3FuturesCloseMarginChangeInputRequest;
import webbroker3.ifo.message.WEB3FuturesCloseMarginChangeInputResponse;
import webbroker3.ifo.message.WEB3FuturesOptionsContractUnit;
import webbroker3.mock.TestBaseForMock;
import webbroker3.quoteadaptor.RealType;
import webbroker3.quoteadaptor.stdimpls.WEB3IfoQuoteDataImplForMock;
import webbroker3.util.WEB3DateUtility;
import webbroker3.util.WEB3LogUtility;

public class WEB3FuturesChangeClosingContractInputServiceImplTest extends TestBaseForMock
{
    /**
     * ログユーティリティ
     */
    private static WEB3LogUtility log = WEB3LogUtility.getInstance(WEB3FuturesChangeClosingContractInputServiceImplTest.class);
    
    private boolean l_blnIsMarketOrder = false;

    public WEB3FuturesChangeClosingContractInputServiceImplTest(String arg0)
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
    public class WEB3FuturesCloseMarginChangeInputRequestForMock 
        extends WEB3FuturesCloseMarginChangeInputRequest
    {
        public void validate() throws WEB3BaseException 
        {
            final String STR_METHOD_NAME = "WEB3FuturesCloseMarginChangeInputRequestForMock + validate()";
            log.entering(STR_METHOD_NAME);
                      
            log.exiting(STR_METHOD_NAME);
        }
    }
    /*
     * Test method for 'webbroker3.ifo.service.delegate.stdimpls.WEB3FuturesChangeClosingContractInputServiceImpl.execute(WEB3GenRequest)'
     */
    public void testExecuteCase1()
    {
        final String STR_METHOD_NAME = "testExecuteCase1()";
        log.entering(STR_METHOD_NAME);
        BranchParams l_branchParams = TestDBUtility.getBranchRow();
        l_branchParams.setBranchId(33381);
        
        InstitutionParams l_institytionParams = TestDBUtility.getInstitutionRow();

        
        IfoOrderUnitParams l_orderUnitParams = TestDBUtility.getIfoOrderUnitRow();
        l_orderUnitParams.setOrderId(1001);
        l_orderUnitParams.setFirstOrderUnitId(null);
        l_orderUnitParams.setEveningSessionCarryoverFlag(BooleanEnum.TRUE);
        l_orderUnitParams.setMarketId(3303L);
        l_orderUnitParams.setProductId(3304148080000L);
        l_orderUnitParams.setOrderCateg(OrderCategEnum.IDX_FUTURES_CLOSE);
        l_orderUnitParams.setBizDate("20070630");
        l_orderUnitParams.setLimitPrice(0);
        l_orderUnitParams.setOrderType(OrderTypeEnum.IDX_FUTURES_BUY_TO_CLOSE);
        l_orderUnitParams.setSessionType("1");
        
        MarketParams l_marketParams = TestDBUtility.getMarketRow();
        l_marketParams.setMarketId(3303L);
        
        ProductParams l_productParams = TestDBUtility.getProductRow();
        l_productParams.setProductId(3304148080000L);
        
        IfoProductParams l_ifoProductParams = TestDBUtility.getIfoProductRow();
        l_ifoProductParams.setProductId(3304148080000L);
        
        TradedProductParams l_tradedProductParams = TestDBUtility.getTradedProductRow();
        l_tradedProductParams.setTradedProductId(330304148080000L);
        l_tradedProductParams.setProductId(3304148080000L);
        
        IfoTradedProductParams l_ifoTradedProductParams = TestDBUtility.getIfoTradedProductRow();
        l_ifoTradedProductParams.setTradedProductId(330304148080000L);
        l_ifoTradedProductParams.setProductId(3304148080000L);
        Calendar ca =  Calendar.getInstance();
        ca.add(Calendar.DATE, 1);
        Date date = ca.getTime();
        l_ifoTradedProductParams.setValidForBizDate(null);
        
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
        
        OrderexecutionEndParams l_orderexecutionEndParams = TestDBUtility.getOrderexecutionEndRow();
        l_orderexecutionEndParams.setInstitutionCode("0D");
        l_orderexecutionEndParams.setProductType(ProductTypeEnum.IFO);
        l_orderexecutionEndParams.setFutureOptionDiv("1");
        l_orderexecutionEndParams.setOrderexecutionEndType("1");
        
               
        try
        {
            SimpleDateFormat l_format = GtlUtils.getThreadSafeSimpleDateFormat("yyyyMMdd");
            Date l_datBizDate = GtlUtils.getTradingSystem().getSystemTimestamp();
            Date l_datpreBizDate = new WEB3GentradeBizDate(
                new Timestamp(l_datBizDate.getTime())).roll(1);
            String l_strCreateDate = l_format.format(l_datpreBizDate);
            
            TestDBUtility.deleteAll(IfoOrderParams.TYPE);
            IfoOrderParams l_IfoOrderParams = TestDBUtility.getIfoOrderRow();
            l_IfoOrderParams.setOrderId(1001);
            TestDBUtility.insertWithDel(l_IfoOrderParams);
            
            TestDBUtility.deleteAll(IfoTradedProductUpdqRow.TYPE);
            IfoTradedProductUpdqParams l_ifoTradedProductUpdqParams = TestDBUtility.getIfoTradedProductUpdqRow();
            l_ifoTradedProductUpdqParams.setTradedProductId(330304148080000L);
            l_ifoTradedProductUpdqParams.setMarketId(3303L);
            l_ifoTradedProductUpdqParams.setValidForBizDate(l_strCreateDate);
            l_ifoTradedProductUpdqParams.setProductId(3304148080000L);
            TestDBUtility.insertWithDel(l_ifoTradedProductUpdqParams);
            TestDBUtility.deleteAll(InstitutionRow.TYPE);
            TestDBUtility.insertWithDel(l_institytionParams);
            TestDBUtility.deleteAll(IfoOrderUnitRow.TYPE);
            TestDBUtility.insertWithDel(l_orderUnitParams);
            
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
            TestDBUtility.deleteAll(BranchParams.TYPE);
            TestDBUtility.insertWithDel(l_branchParams);
            
            TestDBUtility.deleteAll(OrderexecutionEndRow.TYPE);
            TestDBUtility.insertWithDel(l_orderexecutionEndParams);
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
            
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                    "webbroker3.ifo.WEB3IfoOrderManagerReusableValidations",
                    "validateOrderForChangeability",
                    new Class[] {Order.class, boolean.class},
                    null);
            
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                    "webbroker3.ifo.WEB3OptionOrderManagerImpl",
                    "validateOrder",
                    new Class[] {SubAccount.class, String.class},
                    null);
            
            WEB3GentradeMarket l_market = new WEB3GentradeMarket(3303L);
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                    "webbroker3.ifo.WEB3IfoOrderManagerReusableValidations",
                    "validateMarketID",
                    new Class[] {long.class},
                    l_market);
            
            WEB3IfoProductImpl l_productImpl = new WEB3IfoProductImpl(3304148080000L);
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                    "webbroker3.ifo.WEB3IfoOrderManagerReusableValidations",
                    "validateProductID",
                    new Class[] {long.class},
                    l_productImpl);
            
            WEB3IfoTradedProductImpl l_tradedProductImpl = new WEB3IfoTradedProductImpl(330304148080000L);
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                    "webbroker3.ifo.WEB3IfoOrderManagerReusableValidations",
                    "validateTradedProduct",
                    new Class[] {WEB3IfoProductImpl.class,
                            WEB3GentradeMarket.class,
                            boolean.class,
                            boolean.class},
                    l_tradedProductImpl);
            
            Calendar ca1 =  Calendar.getInstance();
            ca1.set(2007,6-1,19);
            
            Date date1 = ca1.getTime();
            WEB3GentradeTradingTimeManagementForMock.mockGetOrderBizDate(date1);
            
            WEB3OptionClientRequestServiceForMock l_impl =
                new WEB3OptionClientRequestServiceForMock();
            
            WEB3FuturesCloseMarginChangeInputRequestForMock l_request =
                new WEB3FuturesCloseMarginChangeInputRequestForMock();
            
            l_request.id = "1001";//orderId
            
            WEB3FuturesCloseMarginChangeInputResponse l_response =
                (WEB3FuturesCloseMarginChangeInputResponse)l_impl.execute(l_request);
            
            l_orderUnitParams.setLimitPrice(null);
            System.out.print(GtlUtils.Double.isZero(l_orderUnitParams.getLimitPrice()));
            System.out.print(l_orderUnitParams.getLimitPrice());
            
            assertEquals(l_response.orderPriceDivList[0], "0");
            assertEquals(l_response.execCondList[0], "1");
            assertEquals(l_response.sessionType, "1");
            assertEquals(l_response.expirationDateType, "3");
            assertNull(l_response.expirationDate);
        }
        catch (Exception l_ex)
        {
            log.error(l_ex.getMessage(), l_ex);
            fail();
        }
        log.exiting(STR_METHOD_NAME);
    }
    
    public void testExecuteCase2()
    {
        final String STR_METHOD_NAME = "testExecuteCase2()";
        log.entering(STR_METHOD_NAME);
        
        BranchParams l_branchParams = TestDBUtility.getBranchRow();
        l_branchParams.setBranchId(33381);
        
        IfoOrderUnitParams l_orderUnitParams = TestDBUtility.getIfoOrderUnitRow();
        l_orderUnitParams.setOrderId(1001);
        l_orderUnitParams.setFirstOrderUnitId(null);
        l_orderUnitParams.setEveningSessionCarryoverFlag(BooleanEnum.FALSE);
        l_orderUnitParams.setMarketId(3303L);
        l_orderUnitParams.setProductId(3304148080000L);
        l_orderUnitParams.setOrderCateg(OrderCategEnum.IDX_FUTURES_CLOSE);
        l_orderUnitParams.setBizDate("20070630");
        l_orderUnitParams.setOrderType(OrderTypeEnum.IDX_FUTURES_BUY_TO_CLOSE);
        l_orderUnitParams.setSessionType("1");
        l_orderUnitParams.setOrderConditionType("0");
        l_orderUnitParams.setLimitPrice(10);
        l_orderUnitParams.setExpirationDate(WEB3DateUtility.getDate("20080615","yyyyMMdd"));
        
        MarketParams l_marketParams = TestDBUtility.getMarketRow();
        l_marketParams.setMarketId(3303L);
        
        ProductParams l_productParams = TestDBUtility.getProductRow();
        l_productParams.setProductId(3304148080000L);
        
        IfoProductParams l_ifoProductParams = TestDBUtility.getIfoProductRow();
        l_ifoProductParams.setProductId(3304148080000L);
        
        TradedProductParams l_tradedProductParams = TestDBUtility.getTradedProductRow();
        l_tradedProductParams.setTradedProductId(330304148080000L);
        l_tradedProductParams.setProductId(3304148080000L);
        
        IfoTradedProductParams l_ifoTradedProductParams = TestDBUtility.getIfoTradedProductRow();
        l_ifoTradedProductParams.setTradedProductId(330304148080000L);
        l_ifoTradedProductParams.setProductId(3304148080000L);
        Calendar ca =  Calendar.getInstance();
        ca.add(Calendar.DATE, 1);
        Date date = ca.getTime();
        l_ifoTradedProductParams.setValidForBizDate(null);
        l_ifoTradedProductParams.setLastTradingDate(WEB3DateUtility.getDate("20080615","yyyyMMdd"));
        
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
        l_enableOrderConditionParams.setCarriedOrder("1");
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
            l_ifoTradedProductUpdqParams.setValidForBizDate(l_strCreateDate);
            l_ifoTradedProductUpdqParams.setProductId(3304148080000L);
            TestDBUtility.insertWithDel(l_ifoTradedProductUpdqParams);
            
            TestDBUtility.deleteAll(IfoOrderUnitRow.TYPE);
            TestDBUtility.insertWithDel(l_orderUnitParams);
            
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
            
            TestDBUtility.deleteAll(BranchParams.TYPE);
            TestDBUtility.insertWithDel(l_branchParams);
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
            
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                    "webbroker3.ifo.WEB3IfoOrderManagerReusableValidations",
                    "validateOrderForChangeability",
                    new Class[] {Order.class, boolean.class},
                    null);
            
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                    "webbroker3.ifo.WEB3OptionOrderManagerImpl",
                    "validateOrder",
                    new Class[] {SubAccount.class, String.class},
                    null);
            
            WEB3GentradeMarket l_market = new WEB3GentradeMarket(3303L);
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                    "webbroker3.ifo.WEB3IfoOrderManagerReusableValidations",
                    "validateMarketID",
                    new Class[] {long.class},
                    l_market);
            
            WEB3IfoProductImpl l_productImpl = new WEB3IfoProductImpl(3304148080000L);
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                    "webbroker3.ifo.WEB3IfoOrderManagerReusableValidations",
                    "validateProductID",
                    new Class[] {long.class},
                    l_productImpl);
            
            WEB3IfoTradedProductImpl l_tradedProductImpl = new WEB3IfoTradedProductImpl(330304148080000L);
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                    "webbroker3.ifo.WEB3IfoOrderManagerReusableValidations",
                    "validateTradedProduct",
                    new Class[] {WEB3IfoProductImpl.class,
                            WEB3GentradeMarket.class,
                            boolean.class,
                            boolean.class},
                    l_tradedProductImpl);
            Calendar ca1 =  Calendar.getInstance();
            ca1.set(2007,6-1,19);
            
            Date date1 = ca1.getTime();
            WEB3GentradeTradingTimeManagementForMock.mockGetOrderBizDate(date1);
            
            WEB3OptionClientRequestServiceForMock l_impl =
                new WEB3OptionClientRequestServiceForMock();
            
            WEB3FuturesCloseMarginChangeInputRequestForMock l_request =
                new WEB3FuturesCloseMarginChangeInputRequestForMock();
            
            l_request.id = "1001";//orderId
            
            WEB3FuturesCloseMarginChangeInputResponse l_response =
                (WEB3FuturesCloseMarginChangeInputResponse)l_impl.execute(l_request);
            assertEquals(l_response.orderPriceDivList[0], "1");
            assertEquals(l_response.execCondList[0], "1");
            assertEquals(l_response.execCondList[1], "3");
            assertEquals(l_response.sessionType, "1");
            assertEquals(l_response.expirationDateType, "1");
            assertNull(l_response.expirationDate);
        }
        catch (Exception l_ex)
        {
            log.error(l_ex.getMessage(), l_ex);
            fail();
        }
        log.exiting(STR_METHOD_NAME);
    }
    
    public void testExecuteCase3()
    {
        final String STR_METHOD_NAME = "testExecuteCase3()";
        log.entering(STR_METHOD_NAME);
        BranchParams l_branchParams = TestDBUtility.getBranchRow();
        l_branchParams.setBranchId(33381);
        
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
        l_orderUnitParams.setOrderConditionType("0");
        l_orderUnitParams.setExpirationDate(WEB3DateUtility.getDate("20080615","yyyyMMdd"));
        
        MarketParams l_marketParams = TestDBUtility.getMarketRow();
        l_marketParams.setMarketId(3303L);
        
        ProductParams l_productParams = TestDBUtility.getProductRow();
        l_productParams.setProductId(3304148080000L);
        
        IfoProductParams l_ifoProductParams = TestDBUtility.getIfoProductRow();
        l_ifoProductParams.setProductId(3304148080000L);
        
        TradedProductParams l_tradedProductParams = TestDBUtility.getTradedProductRow();
        l_tradedProductParams.setTradedProductId(330304148080000L);
        l_tradedProductParams.setProductId(3304148080000L);
        
        IfoTradedProductParams l_ifoTradedProductParams = TestDBUtility.getIfoTradedProductRow();
        l_ifoTradedProductParams.setTradedProductId(330304148080000L);
        l_ifoTradedProductParams.setProductId(3304148080000L);
        Calendar ca =  Calendar.getInstance();
        ca.add(Calendar.DATE, 1);
        Date date = ca.getTime();
        l_ifoTradedProductParams.setValidForBizDate(null);
        l_ifoTradedProductParams.setLastTradingDate(WEB3DateUtility.getDate("20080615","yyyyMMdd"));
        
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
        l_enableOrderConditionParams.setCarriedOrder("1");
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
            l_ifoTradedProductUpdqParams.setValidForBizDate(l_strCreateDate);
            l_ifoTradedProductUpdqParams.setProductId(3304148080000L);
            TestDBUtility.insertWithDel(l_ifoTradedProductUpdqParams);
            
            TestDBUtility.deleteAll(IfoOrderUnitRow.TYPE);
            TestDBUtility.insertWithDel(l_orderUnitParams);
            
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
            
            TestDBUtility.deleteAll(BranchParams.TYPE);
            TestDBUtility.insertWithDel(l_branchParams);
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
            
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                    "webbroker3.ifo.WEB3IfoOrderManagerReusableValidations",
                    "validateOrderForChangeability",
                    new Class[] {Order.class, boolean.class},
                    null);
            
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                    "webbroker3.ifo.WEB3OptionOrderManagerImpl",
                    "validateOrder",
                    new Class[] {SubAccount.class, String.class},
                    null);
            
            WEB3GentradeMarket l_market = new WEB3GentradeMarket(3303L);
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                    "webbroker3.ifo.WEB3IfoOrderManagerReusableValidations",
                    "validateMarketID",
                    new Class[] {long.class},
                    l_market);
            
            WEB3IfoProductImpl l_productImpl = new WEB3IfoProductImpl(3304148080000L);
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                    "webbroker3.ifo.WEB3IfoOrderManagerReusableValidations",
                    "validateProductID",
                    new Class[] {long.class},
                    l_productImpl);
            
            WEB3IfoTradedProductImpl l_tradedProductImpl = new WEB3IfoTradedProductImpl(330304148080000L);
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                    "webbroker3.ifo.WEB3IfoOrderManagerReusableValidations",
                    "validateTradedProduct",
                    new Class[] {WEB3IfoProductImpl.class,
                            WEB3GentradeMarket.class,
                            boolean.class,
                            boolean.class},
                    l_tradedProductImpl);
            
            Calendar ca1 =  Calendar.getInstance();
            ca1.set(2007,6-1,19);
            
            Date date1 = ca1.getTime();
            WEB3GentradeTradingTimeManagementForMock.mockGetOrderBizDate(date1);
            
            WEB3OptionClientRequestServiceForMock l_impl =
                new WEB3OptionClientRequestServiceForMock();
            
            WEB3FuturesCloseMarginChangeInputRequestForMock l_request =
                new WEB3FuturesCloseMarginChangeInputRequestForMock();
            
            l_request.id = "1001";//orderId
            
            WEB3FuturesCloseMarginChangeInputResponse l_response =
                (WEB3FuturesCloseMarginChangeInputResponse)l_impl.execute(l_request);
            assertEquals(l_response.execCondList[0], "1");
            assertEquals(l_response.sessionType, "1");
            assertEquals(l_response.expirationDateType, "2");
            assertEquals("20080615", WEB3DateUtility.formatDate(l_response.expirationDate,"yyyyMMdd"));
            assertEquals("20070629", WEB3DateUtility.formatDate(l_response.expirationEndDate,"yyyyMMdd"));
        }
        catch (Exception l_ex)
        {
            log.error(l_ex.getMessage(), l_ex);
            fail();
        }
        log.exiting(STR_METHOD_NAME);
    }
    
    /*
     * Test method for 'webbroker3.ifo.service.delegate.stdimpls.WEB3FuturesChangeClosingContractInputServiceImpl.execute(WEB3GenRequest)'
     */
    public void testExecuteCase4()
    {
        final String STR_METHOD_NAME = "testExecuteCase4()";
        log.entering(STR_METHOD_NAME);
        
        BranchParams l_branchParams = TestDBUtility.getBranchRow();
        l_branchParams.setBranchId(33381);
        
        IfoOrderUnitParams l_orderUnitParams = TestDBUtility.getIfoOrderUnitRow();
        l_orderUnitParams.setOrderId(1001);
        l_orderUnitParams.setFirstOrderUnitId(null);
        l_orderUnitParams.setEveningSessionCarryoverFlag(BooleanEnum.TRUE);
        l_orderUnitParams.setMarketId(3303L);
        l_orderUnitParams.setProductId(3304148080000L);
        l_orderUnitParams.setOrderCateg(OrderCategEnum.IDX_FUTURES_CLOSE);
        l_orderUnitParams.setBizDate("20070630");
        l_orderUnitParams.setOrderType(OrderTypeEnum.IDX_FUTURES_BUY_TO_CLOSE);
        l_orderUnitParams.setFirstOrderUnitId(1001);
        l_orderUnitParams.setSessionType("1");
        
        MarketParams l_marketParams = TestDBUtility.getMarketRow();
        l_marketParams.setMarketId(3303L);
        
        ProductParams l_productParams = TestDBUtility.getProductRow();
        l_productParams.setProductId(3304148080000L);
        
        IfoProductParams l_ifoProductParams = TestDBUtility.getIfoProductRow();
        l_ifoProductParams.setProductId(3304148080000L);
        
        TradedProductParams l_tradedProductParams = TestDBUtility.getTradedProductRow();
        l_tradedProductParams.setTradedProductId(330304148080000L);
        l_tradedProductParams.setProductId(3304148080000L);
        
        IfoTradedProductParams l_ifoTradedProductParams = TestDBUtility.getIfoTradedProductRow();
        l_ifoTradedProductParams.setTradedProductId(330304148080000L);
        l_ifoTradedProductParams.setProductId(3304148080000L);
        Calendar ca =  Calendar.getInstance();
        ca.add(Calendar.DATE, 1);
        Date date = ca.getTime();
        l_ifoTradedProductParams.setValidForBizDate(null);
        
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
        l_enableOrderConditionParams.setCarriedOrderLapseDateSpec(WEB3CarriedOrderLapseDateSpecDef.EXPIRATION_DATE_USER_DES);
               
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
            l_ifoTradedProductUpdqParams.setValidForBizDate(l_strCreateDate);
            l_ifoTradedProductUpdqParams.setProductId(3304148080000L);
            TestDBUtility.insertWithDel(l_ifoTradedProductUpdqParams);
            
            TestDBUtility.deleteAll(IfoOrderUnitRow.TYPE);
            TestDBUtility.insertWithDel(l_orderUnitParams);
            
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
            
            TestDBUtility.deleteAll(BranchParams.TYPE);
            TestDBUtility.insertWithDel(l_branchParams);
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
            
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                    "webbroker3.ifo.WEB3IfoOrderManagerReusableValidations",
                    "validateOrderForChangeability",
                    new Class[] {Order.class, boolean.class},
                    null);
            
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                    "webbroker3.ifo.WEB3OptionOrderManagerImpl",
                    "validateOrder",
                    new Class[] {SubAccount.class, String.class},
                    null);
            
            WEB3GentradeMarket l_market = new WEB3GentradeMarket(3303L);
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                    "webbroker3.ifo.WEB3IfoOrderManagerReusableValidations",
                    "validateMarketID",
                    new Class[] {long.class},
                    l_market);
            
            WEB3IfoProductImpl l_productImpl = new WEB3IfoProductImpl(3304148080000L);
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                    "webbroker3.ifo.WEB3IfoOrderManagerReusableValidations",
                    "validateProductID",
                    new Class[] {long.class},
                    l_productImpl);
            
            WEB3IfoTradedProductImpl l_tradedProductImpl = new WEB3IfoTradedProductImpl(330304148080000L);
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                    "webbroker3.ifo.WEB3IfoOrderManagerReusableValidations",
                    "validateTradedProduct",
                    new Class[] {WEB3IfoProductImpl.class,
                            WEB3GentradeMarket.class,
                            boolean.class,
                            boolean.class},
                    l_tradedProductImpl);
            
            Calendar ca1 =  Calendar.getInstance();
            ca1.set(2007,6-1,19);
            
            Date date1 = ca1.getTime();
            WEB3GentradeTradingTimeManagementForMock.mockGetOrderBizDate(date1);
            
            WEB3OptionClientRequestServiceForMock l_impl =
                new WEB3OptionClientRequestServiceForMock();
            
            WEB3FuturesCloseMarginChangeInputRequestForMock l_request =
                new WEB3FuturesCloseMarginChangeInputRequestForMock();
            
            l_request.id = "1001";//orderId
            
            WEB3FuturesCloseMarginChangeInputResponse l_response =
                (WEB3FuturesCloseMarginChangeInputResponse)l_impl.execute(l_request);
            fail();
        }
        catch (WEB3BaseException e)
        {
            assertEquals(WEB3ErrorCatalog.BUSINESS_ERROR_00413,e.getErrorInfo());
        }
        catch (Exception l_ex)
        {
            log.error(l_ex.getMessage(), l_ex);
            fail();
        }
        log.exiting(STR_METHOD_NAME);
    }
    
    public void testCreateContractUnitByOrder_C0001()
    {
        final String STR_METHOD_NAME = "testCreateContractUnitByOrder_C0001()";
        log.entering(STR_METHOD_NAME);
        try
        {
            MOCK_MANAGER.setIsMockUsed(true);
            
            WEB3FuturesOptionsContractUnit[] l_units = new WEB3FuturesOptionsContractUnit[1];
            l_units[0] = new WEB3FuturesOptionsContractUnit();
            l_units[0].id = "12345";
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                    "webbroker3.ifo.WEB3FuturesOrderManagerImpl", 
                    "createContractUnitByOrder", new Class[]
                    { long.class },
                    l_units);
                    
            WEB3FuturesChangeClosingContractInputServiceImpl l_service = 
                new WEB3FuturesChangeClosingContractInputServiceImpl();
            
            IfoOrderUnit l_orderUnit = new IfoOrderUnitForTest();
            WEB3FuturesOptionsContractUnit[] l_results = l_service.createContractUnitByOrder(l_orderUnit);
            assertEquals("12345",l_results[0].id);
            
        }
        catch (Exception l_ex)
        {
            log.error(l_ex.getMessage(), l_ex);
            fail();
        }
        log.exiting(STR_METHOD_NAME);
    }
    
    public void testGetChangeOrderUnit_C0001()
    {
        final String STR_METHOD_NAME = "testGetChangeOrderUnit_C0001()";
        log.entering(STR_METHOD_NAME);
        try
        {
            WEB3FuturesCloseMarginChangeInputRequest l_request =
                new WEB3FuturesCloseMarginChangeInputRequest();
            WEB3FuturesChangeClosingContractInputServiceImpl l_service = 
                new WEB3FuturesChangeClosingContractInputServiceImpl();
            
            l_request.id="1001";
            
            IfoOrderUnit l_results = l_service.getChangeOrderUnit(l_request);
            
            assertEquals(1001,l_results.getOrderUnitId());
        }
        catch (Exception l_ex)
        {
            log.error(l_ex.getMessage(), l_ex);
            fail();
        }
    }
    
    public void testValidateOrderForChangeability_C0001()
    {
        final String STR_METHOD_NAME = "testValidateOrderForChangeability_C0001()";
        log.entering(STR_METHOD_NAME);
        try
        {
            MOCK_MANAGER.setIsMockUsed(true);
            WEB3FuturesChangeClosingContractInputServiceImpl l_service = 
                new WEB3FuturesChangeClosingContractInputServiceImpl();
            
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                    "webbroker3.ifo.WEB3IfoOrderManagerReusableValidations",
                    "validateOrderForChangeability", new Class[]
                    { Order.class },
                    new WEB3BaseException(WEB3ErrorCatalog.SYSTEM_ERROR_80006,STR_METHOD_NAME));
            
            l_service.validateOrderForChangeability(new IfoOrderUnitForTest());
            fail();
        }
        catch (Exception l_ex)
        {
            log.error(l_ex.getMessage(), l_ex);
            assertTrue(true);
        }
    }
    
    public void testValidateOrderForChangeability_C0002()
    {
        final String STR_METHOD_NAME = "testValidateOrderForChangeability_C0002()";
        log.entering(STR_METHOD_NAME);
        try
        {
            MOCK_MANAGER.setIsMockUsed(true);
            WEB3FuturesChangeClosingContractInputServiceImpl l_service = 
                new WEB3FuturesChangeClosingContractInputServiceImpl();
            
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                    "webbroker3.ifo.WEB3IfoOrderManagerReusableValidations",
                    "validateOrderForChangeability", 
                    new Class[]{ Order.class },
                    null);
            
            l_service.validateOrderForChangeability(new IfoOrderUnitForTest());
            assertTrue(true);
        }
        catch (Exception l_ex)
        {
            log.error(l_ex.getMessage(), l_ex);
            fail();
        }
    }
    
    /**
     * 注文単位.isMarketOrder == false
     *
     */
    public void testGetCloseChangeInputScreen_C0001()
    {
        final String STR_METHOD_NAME = "testGetCloseChangeInputScreen_C0001()";
        log.entering(STR_METHOD_NAME);
        try
        {
            this.initData();
            this.setMockMethod();
            
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                    "webbroker3.ifo.WEB3FuturesOrderManagerImpl",
                    "isCarriedOrderUnit", 
                    new Class[]{ IfoOrderUnit.class },
                    new Boolean(false));
            
            WEB3FuturesChangeClosingContractInputServiceImplForTest l_service =
                new WEB3FuturesChangeClosingContractInputServiceImplForTest();
            
            WEB3FuturesCloseMarginChangeInputRequest l_FRequest =
                new WEB3FuturesCloseMarginChangeInputRequestForTest();
            
            WEB3FuturesCloseMarginChangeInputResponse l_response = l_service.getCloseChangeInputScreen(l_FRequest);
            
            assertEquals("1",l_response.orderPriceDiv);
        }
        catch (Exception l_ex)
        {
            log.error(l_ex.getMessage(), l_ex);
            fail();
        }
    }
    
    /**
     * 注文単位.isMarketOrder == true
     *
     */
    public void testGetCloseChangeInputScreen_C0002()
    {
        final String STR_METHOD_NAME = "testGetCloseChangeInputScreen_C0002()";
        log.entering(STR_METHOD_NAME);
        try
        {
            this.initData();
            this.setMockMethod();
            this.l_blnIsMarketOrder = true;
            
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                    "webbroker3.ifo.WEB3FuturesOrderManagerImpl",
                    "isCarriedOrderUnit", 
                    new Class[]{ IfoOrderUnit.class },
                    new Boolean(false));
            
            WEB3FuturesChangeClosingContractInputServiceImplForTest l_service =
                new WEB3FuturesChangeClosingContractInputServiceImplForTest();
            
            WEB3FuturesCloseMarginChangeInputRequest l_FRequest =
                new WEB3FuturesCloseMarginChangeInputRequestForTest();
            
            WEB3FuturesCloseMarginChangeInputResponse l_response = l_service.getCloseChangeInputScreen(l_FRequest);
            
            assertEquals("0",l_response.orderPriceDiv);
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
        log.entering(TEST_START + STR_METHOD_NAME);
        try
        {            
            
            TestDBUtility.deleteAll(MarketRow.TYPE);
            MarketParams l_marketParams = TestDBUtility.getMarketRow();
            l_marketParams.setMarketId(3306L);
            TestDBUtility.insertWithDel(l_marketParams);
            
            TestDBUtility.deleteAll(InstitutionRow.TYPE);
            InstitutionParams l_institutionParams = TestDBUtility.getInstitutionRow();
            l_institutionParams.setInstitutionCode("1D");
            TestDBUtility.insertWithDel(l_institutionParams);
            
            TestDBUtility.deleteAll(IfoProductRow.TYPE);
            IfoProductParams l_ifoProductParams = TestDBUtility.getIfoProductRow();
            l_ifoProductParams.setProductId(1006160060009L);
            TestDBUtility.insertWithDel(l_ifoProductParams);
            

            TestDBUtility.deleteAll(MainAccountRow.TYPE);
            MainAccountParams l_mainAccountParams = TestDBUtility.getMainAccountRow();
            l_mainAccountParams.setAccountId(333812512246L);
            TestDBUtility.insertWithDel(l_mainAccountParams);
            
            TestDBUtility.deleteAll(BranchRow.TYPE);
            BranchParams l_branchParams = TestDBUtility.getBranchRow();
            l_branchParams.setBranchId(33387);
            TestDBUtility.insertWithDel(l_branchParams);
            
            TestDBUtility.deleteAll(ProductRow.TYPE);
            ProductParams l_productParams = TestDBUtility.getProductRow();
            l_productParams.setProductId(1006160060009L);
            TestDBUtility.insertWithDel(l_productParams);
            
            TestDBUtility.deleteAll(IfoTradedProductRow.TYPE);
            IfoTradedProductParams l_IfoTradedProductParams = TestDBUtility.getIfoTradedProductRow();
            l_IfoTradedProductParams.setProductId(1006160060009L);
            l_IfoTradedProductParams.setTradedProductId(330304148080002L);
            l_IfoTradedProductParams.setMarketId(3306L);
            l_IfoTradedProductParams.setLastTradingDate(WEB3DateUtility.getDate("20050101", "yyyyMMdd"));
            TestDBUtility.insertWithDel(l_IfoTradedProductParams);
            
            TestDBUtility.deleteAll(TradedProductRow.TYPE);
            TradedProductParams l_tradedProductParams = TestDBUtility.getTradedProductRow();
            l_tradedProductParams.setTradedProductId(330304148080002L);
            l_tradedProductParams.setProductId(1006160060009L);
            l_tradedProductParams.setMarketId(3306L);
            TestDBUtility.insertWithDel(l_tradedProductParams);
            
            TestDBUtility.deleteAll(IfoTradedProductUpdqRow.TYPE);
            IfoTradedProductUpdqParams l_ifoTradedProductUpdqParams = TestDBUtility.getIfoTradedProductUpdqRow();
            l_ifoTradedProductUpdqParams.setTradedProductId(330304148080002L);
            l_ifoTradedProductUpdqParams.setMarketId(3306L);
            
            
//            SimpleDateFormat l_format = GtlUtils.getThreadSafeSimpleDateFormat("yyyyMMdd");
//            Date l_datBizDate = GtlUtils.getTradingSystem().getSystemTimestamp();
//            Date l_datpreBizDate = new WEB3GentradeBizDate(
//                new Timestamp(l_datBizDate.getTime())).roll(1);
//            String l_strCreateDate = l_format.format(l_datpreBizDate);
            
            
            Calendar ca =  Calendar.getInstance();
            ca.set(2007,6-1,26);
            ca.set(Calendar.HOUR_OF_DAY, 0);
            ca.set(Calendar.MINUTE, 0);
            ca.set(Calendar.SECOND, 0);
            ca.set(Calendar.MILLISECOND, 0);
            Date date = ca.getTime();
            
            ThreadLocalSystemAttributesRegistry.setAttribute
                (WEB3GentradeTradingTimeManagementForMock.TIMESTAMP_TAG,new Timestamp(date.getTime()));
            


            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                    "com.fitechlabs.xtrade.plugin.tc.gentrade.stdimpls.TradingSystemImpl",
                    "getSystemTimestamp",
                    new Class[] {},
                    new Timestamp(date.getTime()));
            
            l_ifoTradedProductUpdqParams.setValidForBizDate("20070626");
            l_ifoTradedProductUpdqParams.setProductId(1006160060009L);
            l_ifoTradedProductUpdqParams.setLastTradingDate(WEB3DateUtility.getDate("20070716","yyyyMMdd"));
            TestDBUtility.insertWithDel(l_ifoTradedProductUpdqParams);
            
            TestDBUtility.deleteAll(TradingTimeRow.TYPE);
            

            WEB3GentradeTradingTimeManagementForMock.mockGetOrderBizDate(date);
            
            TestDBUtility.deleteAll(EnableOrderConditionRow.TYPE);
            
            EnableOrderConditionParams l_enableOrderConditionParams = 
                TestDBUtility.getEnableOrderConditionParamsRow();
            l_enableOrderConditionParams.setInstitutionCode("1D");
            l_enableOrderConditionParams.setProductType(ProductTypeEnum.IFO);
            l_enableOrderConditionParams.setFutureOptionDiv("1");
            l_enableOrderConditionParams.setMarginTradingDiv("0");
            l_enableOrderConditionParams.setMarketCode("0");
            l_enableOrderConditionParams.setCarriedOrder("1");
            TestDBUtility.insertWithDel(l_enableOrderConditionParams);    
            
            TestDBUtility.deleteAll(IfoOrderUnitRow.TYPE);
            TestDBUtility.insertWithDel(TestDBUtility.getIfoOrderUnitRow());
            
//          OrderexecutionEndRow
            TestDBUtility.deleteAll(OrderexecutionEndRow.TYPE);
            OrderexecutionEndParams l_orderexecutionEndParams = TestDBUtility.getOrderexecutionEndRow();
            TestDBUtility.insertWithDel(l_orderexecutionEndParams);
                        
        }
        catch (Exception l_ex)
        {
            log.error("", l_ex);
            fail();
        }
        log.exiting(TEST_END + STR_METHOD_NAME);
    }
    
    private void setMockMethod()
    {
        final String STR_METHOD_NAME = "setMockMethod()";
        log.entering(TEST_START + STR_METHOD_NAME);
        try
        {
            MOCK_MANAGER.setIsMockUsed(true);
            IfoOrderImpl l_ifoOrder = new IfoOrderImpl(TestDBUtility.getIfoOrderRow());
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                    "webbroker3.ifo.WEB3FuturesOrderManagerImpl", 
                    "getOrder", new Class[]{ long.class },
                    l_ifoOrder);
            
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                    "webbroker3.ifo.WEB3IfoOrderManagerReusableValidations",
                    "validateOrderForChangeability", new Class[]
                    { Order.class },
                    null);
            
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                    "webbroker3.ifo.WEB3FuturesOrderManagerImpl",
                    "validateOrder", 
                    new Class[]{ SubAccount.class, String.class },
                    null);
            
            WEB3GentradeMarket l_market = new WEB3GentradeMarket(3306L);
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                    "webbroker3.ifo.WEB3IfoOrderManagerReusableValidations",
                    "validateMarketID",
                    new Class[]{long.class},
                    l_market);
            
            WEB3IfoProductImpl l_productImpl = new WEB3IfoProductImpl(1006160060009L);
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                    "webbroker3.ifo.WEB3IfoOrderManagerReusableValidations",
                    "validateProductID",
                    new Class[]{long.class},
                    l_productImpl);
            
            WEB3IfoTradedProductImpl l_tradedProduct = new WEB3IfoTradedProductImplForTest(330304148080002L);
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                    "webbroker3.ifo.WEB3IfoOrderManagerReusableValidations",
                    "validateTradedProduct",
                    new Class[]{WEB3IfoProductImpl.class, WEB3GentradeMarket.class,
                        boolean.class, boolean.class},
                        l_tradedProduct);
            
            WEB3GentradeBranch l_branch = new WEB3GentradeBranch(33387);
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                    "webbroker3.gentrade.WEB3GentradeSubAccount",
                    "getWeb3GenBranch",
                    new Class[] {},
                    l_branch);
            
            WEB3GentradeMainAccount l_mainAccount = new WEB3GentradeMainAccountForMock(333812512246L);
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                    "webbroker3.gentrade.WEB3GentradeSubAccount", 
                    "getMainAccount", 
                    new Class[]{},
                    l_mainAccount);
            
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                    "webbroker3.gentrade.WEB3GentradeMainAccount", 
                    "getBranch", new Class[] {},
                    l_branch);
            
            
            WEB3IfoQuoteDataImplForMock l_ifoQuoteDataForMock = new WEB3IfoQuoteDataImplForMock();
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                    "webbroker3.quoteadaptor.prototype.WEB3ProtoQuoteDataSupplierService", 
                    "getIfoQuote",
                    new Class[]
                    {IfoTradedProduct.class,RealType.class},
                    l_ifoQuoteDataForMock);
            
            WEB3GentradeInstitution l_institution = new WEB3GentradeInstitution("1D");
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                    "webbroker3.gentrade.WEB3GentradeSubAccount", 
                    "getInstitution", new Class[]{},
                    l_institution);
            
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                    "webbroker3.ifo.WEB3FuturesOrderManagerImpl", 
                    "getHandlingPossibleExecConds", 
                    new Class[]{ String[].class, String[].class },
                    new String[]{"2"});
            
            
        }
        catch(Exception l_ex)
        {
            log.error("", l_ex);
            fail();
        }
        log.exiting(TEST_END + STR_METHOD_NAME);
    }
    
    private class WEB3OptionClientRequestServiceForMock extends WEB3FuturesChangeClosingContractInputServiceImpl
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
    
    private class WEB3FuturesChangeClosingContractInputServiceImplForTest extends WEB3FuturesChangeClosingContractInputServiceImpl
    {
        public SubAccount getSubAccount() throws WEB3BaseException
        {
             
            return new WEB3GentradeSubAccountForMock(TestDBUtility.getSubAccountRow());
        }
        public void validateOrderForChangeability(IfoOrderUnit l_orderUnit)
            throws WEB3BaseException
        {

        }
        
        protected IfoOrderUnit getChangeOrderUnit(WEB3FuturesCloseMarginChangeInputRequest l_request)
        throws WEB3BaseException
        {
                return new IfoOrderUnitForTest();
        }
        
        protected WEB3FuturesOptionsContractUnit[] createContractUnitByOrder(IfoOrderUnit l_orderUnit)
        throws WEB3BaseException
        {
                return new WEB3FuturesOptionsContractUnit[1];
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
            return l_blnIsMarketOrder;
        }

        public TradedProduct getTradedProduct()
        {
            // TODO Auto-generated method stub
            return null;
        }

        public Object getDataSourceObject()
        {
            return TestDBUtility.getIfoOrderUnitRow();
        }
        
    }
    
    private class WEB3FuturesCloseMarginChangeInputRequestForTest extends WEB3FuturesCloseMarginChangeInputRequest
    {
        public void validate() throws WEB3BaseException 
        {
            
        }
    }
    
    private class WEB3IfoTradedProductImplForTest extends WEB3IfoTradedProductImpl
    {

        public WEB3IfoTradedProductImplForTest(long l_lngTradedProductID) throws DataQueryException, DataNetworkException, DataFindException
        {
            super(l_lngTradedProductID);
        }
        
    }
}
@
