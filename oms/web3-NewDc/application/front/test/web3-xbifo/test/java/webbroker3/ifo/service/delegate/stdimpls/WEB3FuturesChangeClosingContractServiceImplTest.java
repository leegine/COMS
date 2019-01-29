head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.04.07.00.46.15;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	5444d9d078c63f4;
filename	WEB3FuturesChangeClosingContractServiceImplTest.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : 先物訂正返済サービスImplTest(WEB3FuturesChangeClosingContractServiceImplTest.java)
Author Name      : Daiwa Institute of Research
Revision History : 2007/06/27 張騰宇 (中訊) 新規作成
*/
package webbroker3.ifo.service.delegate.stdimpls;

import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import com.fitechlabs.xtrade.kernel.boot.Services;
import com.fitechlabs.xtrade.kernel.data.DataFindException;
import com.fitechlabs.xtrade.kernel.data.DataNetworkException;
import com.fitechlabs.xtrade.kernel.data.DataQueryException;
import com.fitechlabs.xtrade.kernel.data.Processors;
import com.fitechlabs.xtrade.kernel.data.QueryProcessor;
import com.fitechlabs.xtrade.kernel.util.ThreadLocalSystemAttributesRegistry;
import com.fitechlabs.xtrade.plugin.tc.gentrade.BooleanEnum;
import com.fitechlabs.xtrade.plugin.tc.gentrade.Branch;
import com.fitechlabs.xtrade.plugin.tc.gentrade.ContractTypeEnum;
import com.fitechlabs.xtrade.plugin.tc.gentrade.FinApp;
import com.fitechlabs.xtrade.plugin.tc.gentrade.GtlUtils;
import com.fitechlabs.xtrade.plugin.tc.gentrade.Institution;
import com.fitechlabs.xtrade.plugin.tc.gentrade.Market;
import com.fitechlabs.xtrade.plugin.tc.gentrade.NotFoundException;
import com.fitechlabs.xtrade.plugin.tc.gentrade.OrderCategEnum;
import com.fitechlabs.xtrade.plugin.tc.gentrade.OrderOpenStatusEnum;
import com.fitechlabs.xtrade.plugin.tc.gentrade.OrderTypeEnum;
import com.fitechlabs.xtrade.plugin.tc.gentrade.PersonNameDetails;
import com.fitechlabs.xtrade.plugin.tc.gentrade.ProcessingResult;
import com.fitechlabs.xtrade.plugin.tc.gentrade.ProductTypeEnum;
import com.fitechlabs.xtrade.plugin.tc.gentrade.SideEnum;
import com.fitechlabs.xtrade.plugin.tc.gentrade.SubAccount;
import com.fitechlabs.xtrade.plugin.tc.gentrade.Trader;
import com.fitechlabs.xtrade.plugin.tc.gentrade.TraderTypeEnum;
import com.fitechlabs.xtrade.plugin.tc.gentrade.TradingCalendarDetails;
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
import com.fitechlabs.xtrade.plugin.tc.xbifo.ordersubmitter.io.IfoChangeSettleContractOrderSpec;

import test.util.TestDBUtility;

import webbroker3.common.WEB3BaseException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.common.WEB3SystemLayerException;
import webbroker3.common.define.WEB3ChannelDef;
import webbroker3.gentrade.WEB3GentradeBizDate;
import webbroker3.gentrade.WEB3GentradeBranch;
import webbroker3.gentrade.WEB3GentradeCommission;
import webbroker3.gentrade.WEB3GentradeMainAccountForMock;
import webbroker3.gentrade.WEB3GentradeMarket;
import webbroker3.gentrade.WEB3GentradeSubAccount;
import webbroker3.gentrade.WEB3GentradeTradingClendarDetailsImpl;
import webbroker3.gentrade.WEB3GentradeTradingTimeManagementForMock;
import webbroker3.gentrade.data.EnableOrderConditionParams;
import webbroker3.gentrade.data.EnableOrderConditionRow;
import webbroker3.gentrade.data.TradingTimeParams;
import webbroker3.gentrade.data.TradingTimeRow;
import webbroker3.ifo.WEB3IfoChangeSettleContractOrderSpec;
import webbroker3.ifo.WEB3IfoEstimateDeliveryAmountCalcResult;
import webbroker3.ifo.WEB3IfoProductImpl;
import webbroker3.ifo.WEB3IfoProductManagerImpl;
import webbroker3.ifo.WEB3IfoTradedProductImpl;
import webbroker3.ifo.message.WEB3FuturesCloseMarginChangeCompleteRequest;
import webbroker3.ifo.message.WEB3FuturesCloseMarginChangeCompleteResponse;
import webbroker3.ifo.message.WEB3FuturesCloseMarginChangeConfirmRequest;
import webbroker3.ifo.message.WEB3FuturesCloseMarginChangeConfirmResponse;
import webbroker3.ifo.message.WEB3FuturesOptionsCloseMarginContractUnit;
import webbroker3.ifo.message.WEB3FuturesOptionsContractUnit;
import webbroker3.mock.TestBaseForMock;
import webbroker3.triggerorder.base.data.RsvIfoOrderActionRow;
import webbroker3.triggerorder.base.data.RsvIfoOrderUnitRow;
import webbroker3.util.WEB3DateUtility;
import webbroker3.util.WEB3LogUtility;

public class WEB3FuturesChangeClosingContractServiceImplTest extends TestBaseForMock
{
    /**
     * ログユーティリティ
     */
    private static WEB3LogUtility log = WEB3LogUtility.getInstance(WEB3FuturesChangeClosingContractServiceImplTest.class);

    public WEB3FuturesChangeClosingContractServiceImplTest(String arg0)
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
    public class WEB3FuturesCloseMarginChangeConfirmRequestForMock 
        extends WEB3FuturesCloseMarginChangeConfirmRequest
    {
        public void validate() throws WEB3BaseException 
        {
            final String STR_METHOD_NAME = "WEB3FuturesCloseMarginChangeConfirmRequestForMock + validate()";
            log.entering(STR_METHOD_NAME);
                      
            log.exiting(STR_METHOD_NAME);
        }
    }
    
    public class WEB3FuturesCloseMarginChangeCompleteRequestForMock 
        extends WEB3FuturesCloseMarginChangeCompleteRequest
    {
        public void validate() throws WEB3BaseException 
        {
            final String STR_METHOD_NAME = "WEB3FuturesCloseMarginChangeCompleteRequestForMock + validate()";
            log.entering(STR_METHOD_NAME);
                      
            log.exiting(STR_METHOD_NAME);
        }
    }
    /*
     * Test method for 'webbroker3.ifo.service.delegate.stdimpls.WEB3FuturesChangeClosingContractServiceImpl.validateOrder(WEB3FuturesCloseMarginChangeConfirmRequest)'
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
        
        ProductParams l_productParams = TestDBUtility.getProductRow();
        l_productParams.setProductId(3304148080000L);
        
        IfoProductParams l_ifoProductParams = TestDBUtility.getIfoProductRow();
        l_ifoProductParams.setInstitutionCode("0D");
        l_ifoProductParams.setTargetMarketId(3303);
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
        try
        {
            
            TestDBUtility.deleteAll(IfoOrderRow.TYPE);
            IfoOrderParams l_ifoOrderParams = TestDBUtility.getIfoOrderRow();
            l_ifoOrderParams.setOrderId(1001);
            TestDBUtility.insertWithDel(l_ifoOrderParams);
//            
//            TestDBUtility.deleteAll(SubAccountRow.TYPE);
//            SubAccountParams l_subAccountParams = TestDBUtility.getSubAccountRow();
//            l_subAccountParams.setAccountId(101001010010L);
//            l_subAccountParams.setSubAccountId(10100101001007L);
//            l_subAccountParams.setSubAccountType(SubAccountTypeEnum.EQUITY_OPTIONS_SUB_ACCOUNT);
//            TestDBUtility.insertWithDel(l_subAccountParams);
//            
//            //MainAccountParams
//            TestDBUtility.deleteAll(MainAccountRow.TYPE);
//            MainAccountParams l_mainAccountParams = TestDBUtility.getMainAccountRow();
//            l_mainAccountParams.setAccountId(101001010010L);
//            TestDBUtility.insertWithDel(l_mainAccountParams);
            
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
            
            MainAccountParams l_mainAccountParams = TestDBUtility.getMainAccountRow();
            TestDBUtility.deleteAll(MainAccountRow.TYPE);
            l_mainAccountParams.setAccountId(333812512203L);
            TestDBUtility.insertWithDel(l_mainAccountParams);
            
            InstitutionParams l_institutionParams = TestDBUtility.getInstitutionRow();
            TestDBUtility.deleteAll(InstitutionRow.TYPE);
            TestDBUtility.insertWithDel(l_institutionParams);
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
            
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                    "com.fitechlabs.xtrade.plugin.security.oplogin.impl.OpLoginSecurityServiceImpl",
                    "getAccountId",
                    new Class[] {},new Long(333812512203L));
            
            WEB3IfoTradedProductImpl l_tradedProduct = new WEB3IfoTradedProductImpl(330304148080000L);
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                    "webbroker3.ifo.WEB3IfoProductManagerImpl",
                    "getIfoTradedProduct",
                    new Class[] { Institution.class, String.class, String.class, String.class,
                            IfoDerivativeTypeEnum.class, double.class, String.class, String.class },
                            l_tradedProduct);
            TestDBUtility.deleteAll(BranchRow.TYPE);
            TestDBUtility.insertWithDel(TestDBUtility.getBranchRow());
            WEB3GentradeBranch l_branch = new WEB3GentradeBranch(33381);
            
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                    "webbroker3.gentrade.WEB3GentradeMainAccount", 
                    "getBranch", 
                    new Class[] {},
                    l_branch);
            Calendar ca1 =  Calendar.getInstance();
            ca1.set(2007,6-1,19);
            
            Date date1 = ca1.getTime();
            WEB3GentradeTradingTimeManagementForMock.mockGetOrderBizDate(date1);
            WEB3OptionClientRequestServiceForMock l_impl =
                new WEB3OptionClientRequestServiceForMock();
            WEB3FuturesCloseMarginChangeConfirmRequestForMock l_request =
                new WEB3FuturesCloseMarginChangeConfirmRequestForMock();
            l_request.id = "1001";
            l_request.execCondType = "1";
            l_request.orderCondType = "1";
            l_request.stopOrderCondOperator = "1";
            l_request.stopOrderCondPrice = "100";
            l_request.wlimitExecCondType = "1";
            l_request.futOrderQuantity = "1000";
            l_request.limitPrice = "100";
            l_request.expirationDate = WEB3DateUtility.getDate("20040717", "yyyyMMdd");
            WEB3FuturesOptionsCloseMarginContractUnit[] l_units = new WEB3FuturesOptionsCloseMarginContractUnit[1];
            WEB3FuturesOptionsCloseMarginContractUnit l_unit = new WEB3FuturesOptionsCloseMarginContractUnit();
            l_unit.id = "1001";
            l_units[0] = l_unit;
            l_request.closeMarginContractUnits = l_units;
            WEB3FuturesCloseMarginChangeConfirmResponse l_response =
                l_impl.validateOrder(l_request);
            WEB3FuturesOptionsContractUnit l_contractUnit =l_response.contractUnits[0];
            assertEquals(l_response.contractUnits.length, 1);
            assertEquals(l_contractUnit.sessionType, "1");
            assertEquals(WEB3DateUtility.formatDate(l_response.expirationDate, "yyyyMMdd"), 
            "20040717");
            
        }
        catch (Exception l_ex)
        {
            log.error(l_ex.getMessage(), l_ex);
            fail();
        }
        log.exiting(STR_METHOD_NAME);

    }
    
    public void testValidateOrder_C0002()
    {
        final String STR_METHOD_NAME = "testValidateOrder_C0002()";
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
        
        ProductParams l_productParams = TestDBUtility.getProductRow();
        l_productParams.setProductId(3304148080000L);
        
        IfoProductParams l_ifoProductParams = TestDBUtility.getIfoProductRow();
        l_ifoProductParams.setInstitutionCode("0D");
        l_ifoProductParams.setTargetMarketId(3303);
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
        try
        {
            
            TestDBUtility.deleteAll(IfoOrderRow.TYPE);
            IfoOrderParams l_ifoOrderParams = TestDBUtility.getIfoOrderRow();
            l_ifoOrderParams.setOrderId(1001);
            TestDBUtility.insertWithDel(l_ifoOrderParams);
//            
//            TestDBUtility.deleteAll(SubAccountRow.TYPE);
//            SubAccountParams l_subAccountParams = TestDBUtility.getSubAccountRow();
//            l_subAccountParams.setAccountId(101001010010L);
//            l_subAccountParams.setSubAccountId(10100101001007L);
//            l_subAccountParams.setSubAccountType(SubAccountTypeEnum.EQUITY_OPTIONS_SUB_ACCOUNT);
//            TestDBUtility.insertWithDel(l_subAccountParams);
//            
//            //MainAccountParams
//            TestDBUtility.deleteAll(MainAccountRow.TYPE);
//            MainAccountParams l_mainAccountParams = TestDBUtility.getMainAccountRow();
//            l_mainAccountParams.setAccountId(101001010010L);
//            TestDBUtility.insertWithDel(l_mainAccountParams);
            
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
            
            MainAccountParams l_mainAccountParams = TestDBUtility.getMainAccountRow();
            TestDBUtility.deleteAll(MainAccountRow.TYPE);
            l_mainAccountParams.setAccountId(333812512203L);
            TestDBUtility.insertWithDel(l_mainAccountParams);
            
            InstitutionParams l_institutionParams = TestDBUtility.getInstitutionRow();
            TestDBUtility.deleteAll(InstitutionRow.TYPE);
            TestDBUtility.insertWithDel(l_institutionParams);
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
            
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                    "com.fitechlabs.xtrade.plugin.security.oplogin.impl.OpLoginSecurityServiceImpl",
                    "getAccountId",
                    new Class[] {},new Long(333812512203L));
            
            WEB3IfoTradedProductImpl l_tradedProduct = new WEB3IfoTradedProductImpl(330304148080000L);
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                    "webbroker3.ifo.WEB3IfoProductManagerImpl",
                    "getIfoTradedProduct",
                    new Class[] { Institution.class, String.class, String.class, String.class,
                            IfoDerivativeTypeEnum.class, double.class, String.class, String.class },
                            l_tradedProduct);
            TestDBUtility.deleteAll(BranchRow.TYPE);
            TestDBUtility.insertWithDel(TestDBUtility.getBranchRow());
            WEB3GentradeBranch l_branch = new WEB3GentradeBranch(33381);
            
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                    "webbroker3.gentrade.WEB3GentradeMainAccount", 
                    "getBranch", 
                    new Class[] {},
                    l_branch);
            Calendar ca1 =  Calendar.getInstance();
            ca1.set(2007,6-1,19);
            
            Date date1 = ca1.getTime();
            WEB3GentradeTradingTimeManagementForMock.mockGetOrderBizDate(date1);
            WEB3OptionClientRequestServiceForMock l_impl =
                new WEB3OptionClientRequestServiceForMock();
            WEB3FuturesCloseMarginChangeConfirmRequestForMock l_request =
                new WEB3FuturesCloseMarginChangeConfirmRequestForMock();
            l_request.id = "1001";
            l_request.execCondType = "1";
            l_request.orderCondType = "1";
            l_request.stopOrderCondOperator = "1";
            l_request.stopOrderCondPrice = "100";
            l_request.wlimitExecCondType = "1";
            l_request.futOrderQuantity = "1000";
            l_request.limitPrice = "100";
            l_request.expirationDate = WEB3DateUtility.getDate("20040717", "yyyyMMdd");
            WEB3FuturesOptionsCloseMarginContractUnit[] l_units = new WEB3FuturesOptionsCloseMarginContractUnit[1];
            WEB3FuturesOptionsCloseMarginContractUnit l_unit = new WEB3FuturesOptionsCloseMarginContractUnit();
            l_unit.id = "1001";
            l_units[0] = l_unit;
            l_request.closeMarginContractUnits = l_units;
            WEB3FuturesCloseMarginChangeConfirmResponse l_response =
                l_impl.validateOrder(l_request);
            WEB3FuturesOptionsContractUnit l_contractUnit =l_response.contractUnits[0];
            assertEquals(l_response.contractUnits.length, 1);
            assertEquals(l_contractUnit.sessionType, "1");
            assertEquals(WEB3DateUtility.formatDate(l_response.expirationDate, "yyyyMMdd"), 
            "20040717");
            
            assertEquals("3712000000", l_response.contractUnits[0].incomeCost);
            assertEquals("0", l_response.contractUnits[0].contractCommission);
        }
        catch (Exception l_ex)
        {
            log.error(l_ex.getMessage(), l_ex);
            fail();
        }
        log.exiting(STR_METHOD_NAME);

    }

    /*
     * Test method for 'webbroker3.ifo.service.delegate.stdimpls.WEB3FuturesChangeClosingContractServiceImpl.submitOrder(WEB3FuturesCloseMarginChangeCompleteRequest)'
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
        
        ProductParams l_productParams = TestDBUtility.getProductRow();
        l_productParams.setProductId(3304148080000L);
        
        IfoProductParams l_ifoProductParams = TestDBUtility.getIfoProductRow();
        l_ifoProductParams.setInstitutionCode("0D");
        l_ifoProductParams.setProductId(3304148080000L);
        l_ifoProductParams.setTargetMarketId(3303);
        
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
        try
        {
            
            TestDBUtility.deleteAll(IfoOrderRow.TYPE);
            IfoOrderParams l_ifoOrderParams = TestDBUtility.getIfoOrderRow();
            l_ifoOrderParams.setOrderId(1001);
            TestDBUtility.insertWithDel(l_ifoOrderParams);
            
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
            
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                    "com.fitechlabs.xtrade.plugin.security.oplogin.impl.OpLoginSecurityServiceImpl",
                    "getSessionProperty",
                    new Class[] {String.class},
                    "2");
            
            OrderSubmissionResult l_subResult = new OrderSubmissionResult(ProcessingResult.SUCCESS_RESULT);
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                    "webbroker3.ifo.WEB3FuturesOrderManagerImpl",
                    "submitChangeSettleContractOrder",
                    new Class[] {SubAccount.class, IfoChangeSettleContractOrderSpec.class, String.class, boolean.class},
                    l_subResult);
            
            
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                    "com.fitechlabs.xtrade.plugin.security.oplogin.impl.OpLoginSecurityServiceImpl",
                    "getAccountId",
                    new Class[] {},new Long(333812512203L));
            
            WEB3IfoTradedProductImpl l_tradedProduct = new WEB3IfoTradedProductImpl(330304148080000L);
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                    "webbroker3.ifo.WEB3IfoProductManagerImpl",
                    "getIfoTradedProduct",
                    new Class[] { Institution.class, String.class, String.class },
                            l_tradedProduct);
            WEB3GentradeMainAccountForMock l_mainAccountForMock = new WEB3GentradeMainAccountForMock(TestDBUtility.getMainAccountRow());
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                    "webbroker3.gentrade.WEB3GentradeAccountManager",
                    "getMainAccount",
                    new Class[] {long.class},
                    l_mainAccountForMock);
            Calendar ca1 =  Calendar.getInstance();
            ca1.set(2007,6-1,19);
            
            Date date1 = ca1.getTime();
            WEB3GentradeTradingTimeManagementForMock.mockGetOrderBizDate(date1);
            WEB3OptionClientRequestServiceForMock l_impl =
                new WEB3OptionClientRequestServiceForMock();
            WEB3FuturesCloseMarginChangeCompleteRequestForMock l_request =
                new WEB3FuturesCloseMarginChangeCompleteRequestForMock();
            l_request.id = "1001";
            l_request.execCondType = "1";
            l_request.orderCondType = "1";
            l_request.stopOrderCondOperator = "1";
            l_request.stopOrderCondPrice = "100";
            l_request.wlimitExecCondType = "1";
            l_request.futOrderQuantity = "1000";
            l_request.limitPrice = "100";
            l_request.expirationDate = WEB3DateUtility.getDate("20040717", "yyyyMMdd");
            WEB3FuturesOptionsCloseMarginContractUnit[] l_units = new WEB3FuturesOptionsCloseMarginContractUnit[1];
            WEB3FuturesOptionsCloseMarginContractUnit l_unit = new WEB3FuturesOptionsCloseMarginContractUnit();
            l_unit.id = "1001";
            l_units[0] = l_unit;
            l_request.closeMarginContractUnits = l_units;
            l_impl.submitOrder(l_request);
        }
        catch (Exception l_ex)
        {
            log.error(l_ex.getMessage(), l_ex);
            fail();
        }
        log.exiting(STR_METHOD_NAME);
    }
    
    /**
     * is予約注文確認要 = false
     *
     */
    public void testSubmitOrder_C0001()
    {
        final String STR_METHOD_NAME = "testSubmitOrder_C0001()";
        log.entering(STR_METHOD_NAME);
        try
        {
            MOCK_MANAGER.setIsMockUsed(true);
            TestDBUtility.deleteAll(RsvIfoOrderActionRow.TYPE);
            TestDBUtility.deleteAll(IfoOrderUnitRow.TYPE);
            IfoOrderUnitParams l_ifoOrderUnitParams = TestDBUtility.getIfoOrderUnitRow();
            // 予約注文の設定
            l_ifoOrderUnitParams.setReserveOrderExistFlag("0");
            TestDBUtility.insertWithDel(l_ifoOrderUnitParams);
            
            TestDBUtility.deleteAll(RsvIfoOrderUnitRow.TYPE);
            TestDBUtility.insertWithDel(TestDBUtility.getRsvIfoOrderUnitRow());
            
            TestDBUtility.deleteAll(TradingTimeRow.TYPE);
//            WEB3GentradeTradingTimeManagementForMock.mockGetOrderBizDate(new Date());
            
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
            
            TestDBUtility.deleteAll(IfoContractRow.TYPE);
            IfoContractParams l_ifoContractParams = TestDBUtility.getIfoContractRow();
            l_ifoContractParams.setProductId(1006160060009L);
            l_ifoContractParams.setMarketId(3306L);
            TestDBUtility.insertWithDel(l_ifoContractParams);
            
            TestDBUtility.deleteAll(MainAccountRow.TYPE);
            MainAccountParams l_mainAccountParams = TestDBUtility.getMainAccountRow();
            l_mainAccountParams.setAccountId(333812512246L);
            TestDBUtility.insertWithDel(l_mainAccountParams);
            
            TestDBUtility.deleteAll(BranchRow.TYPE);
            BranchParams l_branchParams = TestDBUtility.getBranchRow();
            l_branchParams.setBranchId(33381);
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
            l_IfoTradedProductParams.setValidForBizDate(null);
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
            
            
            SettleContractEntry[] l_eqOrderEntry = new SettleContractEntry[1];
            l_eqOrderEntry[0] = new SettleContractEntry(123,123);
            
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                    "webbroker3.ifo.WEB3FuturesOrderManagerImpl", 
                    "createSettleContractEntry", 
                    new Class[]{ long.class, double.class, WEB3FuturesOptionsCloseMarginContractUnit[].class },
                    l_eqOrderEntry);
            
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                    "webbroker3.ifo.WEB3IfoProductManagerImpl", 
                    "getProduct", 
                    new Class[]{ long.class },
                    new WEB3IfoProductImplForTest(1006160060009L));
            
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
            
            OrderSubmissionResult l_subResult = new OrderSubmissionResult(ProcessingResult.SUCCESS_RESULT);
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                    "webbroker3.ifo.WEB3FuturesOrderManagerImpl",
                    "submitChangeSettleContractOrder",
                    new Class[] {SubAccount.class, IfoChangeSettleContractOrderSpec.class, String.class, boolean.class},
                    l_subResult);
            
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                    "com.fitechlabs.xtrade.plugin.security.oplogin.impl.OpLoginSecurityServiceImpl",
                    "getSessionProperty",
                    new Class[] {String.class},
                    "2");
            
            WEB3IfoTradedProductImpl l_tradedProduct = new WEB3IfoTradedProductImpl(330304148080002L);
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                    "webbroker3.ifo.WEB3IfoProductManagerImpl",
                    "getIfoTradedProduct",
                    new Class[] { Institution.class, String.class, String.class, String.class,
                            IfoDerivativeTypeEnum.class, double.class, String.class, String.class },
                            l_tradedProduct);
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

            TradingTimeParams l_tradingTimeParams =  TestDBUtility.getTradingTimeRow();
            l_tradingTimeParams.setInstitutionCode("0D");
            l_tradingTimeParams.setBranchCode("123");
            l_tradingTimeParams.setMarketCode("N1");
            l_tradingTimeParams.setTradingTimeType("01");
            l_tradingTimeParams.setProductCode("0");
            l_tradingTimeParams.setBizDateType("1");
            TestDBUtility.insertWithDel(l_tradingTimeParams);
            
            TestBaseForMock.MOCK_MANAGER.setIsMockUsed(true);
            WEB3FuturesChangeClosingContractServiceImpl l_service =
                new  WEB3FuturesChangeClosingContractServiceImplForTest();
            
            WEB3FuturesCloseMarginChangeCompleteRequest l_request =
                new WEB3FuturesCloseMarginChangeCompleteRequestForTest();
            
            l_request.id = "1001";
            l_request.limitPrice = "2000";
            l_request.closeMarginContractUnits = new WEB3FuturesOptionsCloseMarginContractUnit[1];
            l_request.closeMarginContractUnits[0] = new WEB3FuturesOptionsCloseMarginContractUnit();
            l_request.closeMarginContractUnits[0].id = "1001";
            
            WEB3FuturesCloseMarginChangeCompleteResponse l_response = l_service.submitOrder(l_request);
            
            assertNotNull(l_response);
            
            QueryProcessor l_queryProcessor = Processors.getDefaultProcessor();
            List l_lisRsvIfoOrderUnitRows =
                l_queryProcessor.doFindAllQuery(
                    RsvIfoOrderUnitRow.TYPE,
                    " parent_order_id = ?",
                    new Object[]{new Long(1001)});
            // setOrderOpenStatus
            assertEquals(OrderOpenStatusEnum.OPEN,((RsvIfoOrderUnitRow)l_lisRsvIfoOrderUnitRows.get(0)).getOrderOpenStatus());
            
            List l_lisRsvIfoOrderActionRows =
                l_queryProcessor.doFindAllQuery(
                    RsvIfoOrderActionRow.TYPE,
                    "order_id = ?",
                    new Object[]{new Long(1001)});
            
            assertEquals(0,l_lisRsvIfoOrderActionRows.size());
        }
        catch (Exception l_ex)
        {
            log.error(l_ex.getMessage(), l_ex);
            fail();
        }
        log.exiting(STR_METHOD_NAME);
    }
    
    /**
     * is予約注文確認要 = true
     *
     */
    public void testSubmitOrder_C0002()
    {
        final String STR_METHOD_NAME = "testSubmitOrder_C0002()";
        log.entering(STR_METHOD_NAME);
        try
        {
            MOCK_MANAGER.setIsMockUsed(true);
            TestDBUtility.deleteAll(RsvIfoOrderActionRow.TYPE);
            TestDBUtility.deleteAll(IfoOrderUnitRow.TYPE);
            IfoOrderUnitParams l_ifoOrderUnitParams = TestDBUtility.getIfoOrderUnitRow();
            // 予約注文の設定
            l_ifoOrderUnitParams.setReserveOrderExistFlag("1");
            TestDBUtility.insertWithDel(l_ifoOrderUnitParams);
            
            TestDBUtility.deleteAll(RsvIfoOrderUnitRow.TYPE);
            TestDBUtility.insertWithDel(TestDBUtility.getRsvIfoOrderUnitRow());
            
            TestDBUtility.deleteAll(TradingTimeRow.TYPE);
//            WEB3GentradeTradingTimeManagementForMock.mockGetOrderBizDate(new Date());
            
            TestDBUtility.deleteAll(MarketRow.TYPE);
            MarketParams l_marketParams = TestDBUtility.getMarketRow();
            l_marketParams.setMarketId(3306L);
            TestDBUtility.insertWithDel(l_marketParams);
            
            TestDBUtility.deleteAll(InstitutionRow.TYPE);
            InstitutionParams l_institutionParams = TestDBUtility.getInstitutionRow();
            l_institutionParams.setInstitutionCode("0D");
            TestDBUtility.insertWithDel(l_institutionParams);
            
            TestDBUtility.deleteAll(IfoProductRow.TYPE);
            IfoProductParams l_ifoProductParams = TestDBUtility.getIfoProductRow();
            l_ifoProductParams.setProductId(1006160060009L);
            TestDBUtility.insertWithDel(l_ifoProductParams);
            
            TestDBUtility.deleteAll(IfoContractRow.TYPE);
            IfoContractParams l_ifoContractParams = TestDBUtility.getIfoContractRow();
            l_ifoContractParams.setProductId(1006160060009L);
            l_ifoContractParams.setMarketId(3306L);
            TestDBUtility.insertWithDel(l_ifoContractParams);
            
            TestDBUtility.deleteAll(MainAccountRow.TYPE);
            MainAccountParams l_mainAccountParams = TestDBUtility.getMainAccountRow();
            l_mainAccountParams.setAccountId(333812512246L);
            TestDBUtility.insertWithDel(l_mainAccountParams);
            
            TestDBUtility.deleteAll(BranchRow.TYPE);
            BranchParams l_branchParams = TestDBUtility.getBranchRow();
            l_branchParams.setBranchId(33381);
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
            l_IfoTradedProductParams.setValidForBizDate(null);
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
            
            
            SettleContractEntry[] l_eqOrderEntry = new SettleContractEntry[1];
            l_eqOrderEntry[0] = new SettleContractEntry(123,123);
            
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                    "webbroker3.ifo.WEB3FuturesOrderManagerImpl", 
                    "createSettleContractEntry", 
                    new Class[]{ long.class, double.class, WEB3FuturesOptionsCloseMarginContractUnit[].class },
                    l_eqOrderEntry);
            
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                    "webbroker3.ifo.WEB3IfoProductManagerImpl", 
                    "getProduct", 
                    new Class[]{ long.class },
                    new WEB3IfoProductImplForTest(1006160060009L));
            
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
            
            OrderSubmissionResult l_subResult = new OrderSubmissionResult(ProcessingResult.SUCCESS_RESULT);
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                    "webbroker3.ifo.WEB3FuturesOrderManagerImpl",
                    "submitChangeSettleContractOrder",
                    new Class[] {SubAccount.class, IfoChangeSettleContractOrderSpec.class, String.class, boolean.class},
                    l_subResult);
            
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                    "com.fitechlabs.xtrade.plugin.security.oplogin.impl.OpLoginSecurityServiceImpl",
                    "getSessionProperty",
                    new Class[] {String.class},
                    "2");
            
            WEB3IfoTradedProductImpl l_tradedProduct = new WEB3IfoTradedProductImpl(330304148080002L);
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                    "webbroker3.ifo.WEB3IfoProductManagerImpl",
                    "getIfoTradedProduct",
                    new Class[] { Institution.class, String.class, String.class, String.class,
                            IfoDerivativeTypeEnum.class, double.class, String.class, String.class },
                            l_tradedProduct);
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
            
            TradingTimeParams l_tradingTimeParams =  TestDBUtility.getTradingTimeRow();
            l_tradingTimeParams.setInstitutionCode("0D");
            l_tradingTimeParams.setBranchCode("123");
            l_tradingTimeParams.setMarketCode("N1");
            l_tradingTimeParams.setTradingTimeType("01");
            l_tradingTimeParams.setProductCode("0");
            l_tradingTimeParams.setBizDateType("1");
            TestDBUtility.insertWithDel(l_tradingTimeParams);

            WEB3FuturesChangeClosingContractServiceImpl l_service =
                new  WEB3FuturesChangeClosingContractServiceImplForTest();
            
            WEB3FuturesCloseMarginChangeCompleteRequest l_request =
                new WEB3FuturesCloseMarginChangeCompleteRequestForTest();
            
            l_request.id = "1001";
            l_request.limitPrice = "2000";
            l_request.closeMarginContractUnits = new WEB3FuturesOptionsCloseMarginContractUnit[1];
            l_request.closeMarginContractUnits[0] = new WEB3FuturesOptionsCloseMarginContractUnit();
            l_request.closeMarginContractUnits[0].id = "1001";
            
            WEB3FuturesCloseMarginChangeCompleteResponse l_response = l_service.submitOrder(l_request);
            
            assertNotNull(l_response);
            
            QueryProcessor l_queryProcessor = Processors.getDefaultProcessor();
            
            List l_lisRsvIfoOrderUnitRows =
                l_queryProcessor.doFindAllQuery(
                    RsvIfoOrderUnitRow.TYPE,
                    " parent_order_id = ?",
                    new Object[]{new Long(1001)});
            // setOrderOpenStatus
            assertEquals(OrderOpenStatusEnum.OPEN,((RsvIfoOrderUnitRow)l_lisRsvIfoOrderUnitRows.get(0)).getOrderOpenStatus());
            
        }
        catch (Exception l_ex)
        {
            log.error(l_ex.getMessage(), l_ex);
            fail();
        }
        log.exiting(STR_METHOD_NAME);
    }

    private class WEB3OptionClientRequestServiceForMock extends WEB3FuturesChangeClosingContractServiceImpl
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
    
    private class WEB3FuturesCloseMarginChangeCompleteRequestForTest extends WEB3FuturesCloseMarginChangeCompleteRequest
    {
        public void validate() throws WEB3BaseException 
        { 
            
        }
    }
    
    private class WEB3IfoProductImplForTest extends WEB3IfoProductImpl
    {
        public WEB3IfoProductImplForTest(long l_lngProductID) throws DataFindException, DataQueryException, DataNetworkException
        {
            super(l_lngProductID);
            // TODO Auto-generated constructor stub
        }

        public Market getPrimaryMarket()           
        {  
            WEB3GentradeMarket l_market = null;
            try
            {
                l_market = new WEB3GentradeMarket(3306L);
            }
            catch (Exception e)
            {
                fail();
            }
            return l_market;
        }
    }
    
    private class WEB3FuturesChangeClosingContractServiceImplForTest extends WEB3FuturesChangeClosingContractServiceImpl
    {
        public SubAccount getSubAccount() throws WEB3BaseException 
        {
            return new WEB3GentradeSubAccount(TestDBUtility.getSubAccountRow());
        }
        
        public String getLoginChannel()
        {
            return "abc";
        }
        
        public Trader getTrader() throws WEB3SystemLayerException
        {
            return new TraderForTest();
        }
    }
    
    private class TraderForTest implements Trader
    {

        public long getTraderId()
        {
            // TODO Auto-generated method stub
            return 0;
        }

        public String getTraderCode()
        {
            // TODO Auto-generated method stub
            return null;
        }

        public long getLoginId()
        {
            // TODO Auto-generated method stub
            return 0;
        }

        public TraderTypeEnum getTraderType()
        {
            // TODO Auto-generated method stub
            return null;
        }

        public PersonNameDetails getNameDetails()
        {
            // TODO Auto-generated method stub
            return null;
        }

        public PersonNameDetails getAlt1NameDetails()
        {
            // TODO Auto-generated method stub
            return null;
        }

        public PersonNameDetails getAlt2NameDetails()
        {
            // TODO Auto-generated method stub
            return null;
        }

        public Branch getBranch()
        {
            // TODO Auto-generated method stub
            return null;
        }

        public Institution getInstitution()
        {
            // TODO Auto-generated method stub
            return null;
        }

        public Object getDataSourceObject()
        {
            // TODO Auto-generated method stub
            return null;
        }
        
    }
    
}
@
