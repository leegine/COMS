head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.04.07.02.40.17;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	5444d9d078c63f4;
filename	WEB3EquityExecuteReferenceServiceImplTest.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@package webbroker3.equity.service.delegate.stdimpls;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.fitechlabs.xtrade.kernel.boot.Services;
import com.fitechlabs.xtrade.plugin.security.oplogin.impl.LoginInfoImplForMock;
import com.fitechlabs.xtrade.plugin.tc.eqtype.EqTypeExecutionConditionType;
import com.fitechlabs.xtrade.plugin.tc.eqtype.EqTypeProduct;
import com.fitechlabs.xtrade.plugin.tc.eqtype.data.EqtypeOrderActionParams;
import com.fitechlabs.xtrade.plugin.tc.eqtype.data.EqtypeOrderActionRow;
import com.fitechlabs.xtrade.plugin.tc.eqtype.data.EqtypeOrderUnitParams;
import com.fitechlabs.xtrade.plugin.tc.eqtype.data.EqtypeOrderUnitRow;
import com.fitechlabs.xtrade.plugin.tc.gentrade.FinApp;
import com.fitechlabs.xtrade.plugin.tc.gentrade.Market;
import com.fitechlabs.xtrade.plugin.tc.gentrade.Order;
import com.fitechlabs.xtrade.plugin.tc.gentrade.OrderEventTypeEnum;
import com.fitechlabs.xtrade.plugin.tc.gentrade.OrderExpirationStatusEnum;
import com.fitechlabs.xtrade.plugin.tc.gentrade.OrderStatusEnum;
import com.fitechlabs.xtrade.plugin.tc.gentrade.OrderTypeEnum;
import com.fitechlabs.xtrade.plugin.tc.gentrade.ProductTypeEnum;
import com.fitechlabs.xtrade.plugin.tc.gentrade.QuantityTypeEnum;
import com.fitechlabs.xtrade.plugin.tc.gentrade.SubAccount;
import com.fitechlabs.xtrade.plugin.tc.gentrade.SubAccountTypeEnum;
import com.fitechlabs.xtrade.plugin.tc.gentrade.TradedProduct;
import com.fitechlabs.xtrade.plugin.tc.gentrade.TradingModule;
import com.fitechlabs.xtrade.plugin.tc.gentrade.data.BranchParams;
import com.fitechlabs.xtrade.plugin.tc.gentrade.data.BranchRow;
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

import test.util.TestDBUtility;

import webbroker3.common.define.WEB3OrderPriceDivDef;
import webbroker3.common.define.WEB3SonarExecutionConditionDef;
import webbroker3.equity.WEB3EquityOrderManager;
import webbroker3.equity.WEB3EquityTradedProduct;
import webbroker3.equity.define.WEB3EquityReferenceTypeDef;
import webbroker3.equity.message.WEB3EquityExecuteDetailsRequest;
import webbroker3.equity.message.WEB3EquityExecuteDetailsResponse;
import webbroker3.equity.message.WEB3EquityExecuteReferenceRequest;
import webbroker3.equity.message.WEB3EquityExecuteReferenceResponse;
import webbroker3.equity.message.WEB3EquityOrderHistoryRequest;
import webbroker3.equity.message.WEB3EquityOrderHistoryResponse;
import webbroker3.gentrade.WEB3GentradeBranch;
import webbroker3.gentrade.WEB3GentradeSubAccount;
import webbroker3.gentrade.WEB3GentradeTradingTimeManagementForMock;
import webbroker3.mock.TestBaseForMock;
import webbroker3.util.WEB3LogUtility;

/**
 * 
 * @@author tang-xingfeng
 *
 */
public class WEB3EquityExecuteReferenceServiceImplTest extends TestBaseForMock
{
    /**
     * ログ出力ユーティリティ<BR>
     */
    private static WEB3LogUtility log =
        WEB3LogUtility.getInstance(WEB3EquityExecuteReferenceServiceImplTest.class);
    WEB3EquityExecuteReferenceServiceImpl impl =
        new WEB3EquityExecuteReferenceServiceImpl();

    public WEB3EquityExecuteReferenceServiceImplTest(String arg0)
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

    public class WEB3EquityExecuteDetailsRequestForMock
    extends WEB3EquityExecuteDetailsRequest
    {
        public void validate()
        {
            final String STR_METHOD_NAME = "validate()";
            log.entering(STR_METHOD_NAME);
            
            log.exiting(STR_METHOD_NAME);
        }
    }

    public class WEB3EquityOrderHistoryRequestForMock
    extends WEB3EquityOrderHistoryRequest
    {
        public void validate()
        {
            final String STR_METHOD_NAME = "validate()";
            log.entering(STR_METHOD_NAME);
            
            log.exiting(STR_METHOD_NAME);
        }
    }

    public class WEB3EquityExecuteReferenceRequestForMock
    extends WEB3EquityExecuteReferenceRequest
    {
        public void validate()
        {
            final String STR_METHOD_NAME = "validate()";
            log.entering(STR_METHOD_NAME);
            
            log.exiting(STR_METHOD_NAME);
        }
    }
    /*
     * Test method for 'webbroker3.equity.service.delegate.stdimpls.WEB3EquityExecuteReferenceServiceImpl.searchExecuteReference(WEB3EquityExecuteReferenceRequest)'
     */
    public void testSearchExecuteReference_case1()
    {
        final String STR_METHOD_NAME = "testSearchExecuteReference_case1()";
        log.entering(STR_METHOD_NAME);
                
        MOCK_MANAGER.setIsMockUsed(true);
        TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
            "com.fitechlabs.xtrade.plugin.security.oplogin.impl.OpLoginSecurityServiceImpl",
            "getAccountId",
            new Class[] {},new Long(333812512203L));

        LoginInfoImplForMock l_loginInfoImplForMock = new LoginInfoImplForMock();
        
        TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
            "com.fitechlabs.xtrade.plugin.security.oplogin.impl.OpLoginSecurityServiceImpl",
            "getLoginInfo",
            new Class[] {},
            l_loginInfoImplForMock);

        TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
            "com.fitechlabs.xtrade.plugin.security.oplogin.impl.LoginInfoImpl",
            "getLoginId",
            new Class[] {},
            new Long(3338111123L));
        
        try
        {
            WEB3GentradeTradingTimeManagementForMock l_mock =
                new WEB3GentradeTradingTimeManagementForMock();
            WEB3GentradeTradingTimeManagementForMock.mockValidateOrderAccept(true);
            EqtypeOrderUnitParams l_eqTypeOrderUnitParams =
                TestDBUtility.getEqtypeOrderUnitRow();
            l_eqTypeOrderUnitParams.setOrderId(1003L);
            l_eqTypeOrderUnitParams.setBranchId(63101);
            l_eqTypeOrderUnitParams.setProductType(ProductTypeEnum.EQUITY);
            l_eqTypeOrderUnitParams.setOrderRequestNumber("000022001");
            l_eqTypeOrderUnitParams.setExecutionConditionType(EqTypeExecutionConditionType.NONE);
            l_eqTypeOrderUnitParams.setRequestType("2");
            l_eqTypeOrderUnitParams.setWLimitBeforeExecCondType(EqTypeExecutionConditionType.NONE);
            l_eqTypeOrderUnitParams.setOrgWLimitExecCondType(EqTypeExecutionConditionType.NONE);

            SubAccountParams l_subAccountParams =
                TestDBUtility.getSubAccountRow();
            l_subAccountParams.setInstitutionCode("6D");
            l_subAccountParams.setInstitutionId(63);
            l_subAccountParams.setSubAccountType(SubAccountTypeEnum.EQUITY_SUB_ACCOUNT);

            MainAccountParams l_mainAccountParams =
                TestDBUtility.getMainAccountRow();
            l_mainAccountParams.setAccountId(333812512203L);

            ProductParams l_productParams =
                TestDBUtility.getProductRow();
            l_productParams.setProductId(3304148080001L);
            l_productParams.setInstitutionCode("6D");
            l_productParams.setProductType(ProductTypeEnum.EQUITY);

            MarketParams l_marketParams =
                TestDBUtility.getMarketRow();
            l_marketParams.setMarketId(3303L);
            l_marketParams.setMarketCode("N1");
            l_marketParams.setInstitutionCode("6D");

            BranchParams l_branchParams =
                TestDBUtility.getBranchRow();
            l_branchParams.setInstitutionCode("6D");
            l_branchParams.setInstitutionId(63);

            TradedProductParams l_tradedProductParams =
                TestDBUtility.getTradedProductRow();
            l_tradedProductParams.setTradedProductId(330304148080001L);
            l_tradedProductParams.setInstitutionCode("6D");
            l_tradedProductParams.setProductId(3304148080001L);
            l_tradedProductParams.setProductType(ProductTypeEnum.EQUITY);
            
            TraderParams l_traderParams =
                TestDBUtility.getTraderRow();
            l_traderParams.setTraderId(3338111123L);
            l_traderParams.setInstitutionCode("6D");
            l_traderParams.setBranchId(33381L);
            l_traderParams.setBranchCode("381");
            
            TestDBUtility.deleteAll(EqtypeOrderUnitRow.TYPE);
            TestDBUtility.deleteAll(SubAccountRow.TYPE);
            TestDBUtility.deleteAll(MainAccountRow.TYPE);
            TestDBUtility.deleteAll(ProductRow.TYPE);
            TestDBUtility.deleteAll(MarketRow.TYPE);
            TestDBUtility.deleteAll(BranchRow.TYPE);
            TestDBUtility.deleteAll(TradedProductRow.TYPE);
            TestDBUtility.deleteAll(TraderRow.TYPE);
            

            TestDBUtility.insertWithDel(l_eqTypeOrderUnitParams);
            TestDBUtility.insertWithDel(l_subAccountParams);
            TestDBUtility.insertWithDel(l_mainAccountParams);
            TestDBUtility.insertWithDel(l_productParams);
            TestDBUtility.insertWithDel(l_marketParams);
            TestDBUtility.insertWithDel(l_branchParams);
            TestDBUtility.insertWithDel(l_tradedProductParams);
            TestDBUtility.insertWithDel(l_traderParams);

            FinApp l_finApp = (FinApp)Services.getService(FinApp.class);
            TradingModule l_tradingModule =
                l_finApp.getTradingModule(ProductTypeEnum.EQUITY);
            WEB3EquityOrderManager l_equityOrderManager =
                (WEB3EquityOrderManager)l_tradingModule.getOrderManager();
            List l_lisResult = new ArrayList();
            l_lisResult.add(l_equityOrderManager.toOrderUnit(l_eqTypeOrderUnitParams));      
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                "webbroker3.equity.WEB3EquityOrderManager",
                "getOrderUnits",
                new Class[] { WEB3GentradeSubAccount.class, ProductTypeEnum.class, String.class, Object[].class,
                    String.class },
                 l_lisResult);

            WEB3EquityTradedProduct l_tradedProduct =
                new WEB3EquityTradedProduct(l_tradedProductParams);
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                "webbroker3.equity.WEB3EquityOrderManager",
                "validateTradedProduct",
                new Class[] {EqTypeProduct.class, Market.class},
                l_tradedProduct);
            
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                "webbroker3.equity.WEB3EquityOrderManager",
                "validateHandlingMarket",
                new Class[] {WEB3GentradeBranch.class,TradedProduct.class},
                null);

            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                "webbroker3.equity.WEB3EquityOrderManager",
                "validateOrderForChangeability",
                new Class[] {Order.class},
                null);

            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                "webbroker3.equity.WEB3EquityOrderManager",
                "validateInsider",
                new Class[] {SubAccount.class,
                    EqTypeProduct.class},
                null);
            
            WEB3EquityExecuteReferenceRequestForMock l_request =
                new WEB3EquityExecuteReferenceRequestForMock();
            l_request.execType = "0";
            l_request.pageSize = "1";
            l_request.referenceType = WEB3EquityReferenceTypeDef.REFERENCE_TYPE_UPDATE;
            l_request.pageIndex = "10";
            
            WEB3EquityExecuteReferenceResponse l_response =
                impl.searchExecuteReference(l_request);
            assertEquals(l_response.executeGroups[0].orgOrderCondPrice, null);

        }
        catch (Exception l_ex)
        {
            log.error(l_ex.getMessage(), l_ex);
            fail();
        }

        log.exiting(STR_METHOD_NAME);
    }

    public void testSearchExecuteReference_case2()
    {
        final String STR_METHOD_NAME = "testSearchExecuteReference_case2()";
        log.entering(STR_METHOD_NAME);
                
        MOCK_MANAGER.setIsMockUsed(true);
        TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
            "com.fitechlabs.xtrade.plugin.security.oplogin.impl.OpLoginSecurityServiceImpl",
            "getAccountId",
            new Class[] {},new Long(333812512203L));

        LoginInfoImplForMock l_loginInfoImplForMock = new LoginInfoImplForMock();
        
        TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
            "com.fitechlabs.xtrade.plugin.security.oplogin.impl.OpLoginSecurityServiceImpl",
            "getLoginInfo",
            new Class[] {},
            l_loginInfoImplForMock);

        TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
            "com.fitechlabs.xtrade.plugin.security.oplogin.impl.LoginInfoImpl",
            "getLoginId",
            new Class[] {},
            new Long(3338111123L));
        
        try
        {
            WEB3GentradeTradingTimeManagementForMock l_mock =
                new WEB3GentradeTradingTimeManagementForMock();
            WEB3GentradeTradingTimeManagementForMock.mockValidateOrderAccept(true);
            EqtypeOrderUnitParams l_eqTypeOrderUnitParams =
                TestDBUtility.getEqtypeOrderUnitRow();
            l_eqTypeOrderUnitParams.setOrderId(1003L);
            l_eqTypeOrderUnitParams.setBranchId(63101);
            l_eqTypeOrderUnitParams.setProductType(ProductTypeEnum.EQUITY);
            l_eqTypeOrderUnitParams.setOrderRequestNumber("000022001");
            l_eqTypeOrderUnitParams.setExecutionConditionType(EqTypeExecutionConditionType.NONE);
            l_eqTypeOrderUnitParams.setRequestType("2");
            l_eqTypeOrderUnitParams.setWLimitBeforeExecCondType(EqTypeExecutionConditionType.NONE);
            l_eqTypeOrderUnitParams.setOrgWLimitExecCondType(EqTypeExecutionConditionType.NONE);
            l_eqTypeOrderUnitParams.setOrgStopOrderPrice(200);

            SubAccountParams l_subAccountParams =
                TestDBUtility.getSubAccountRow();
            l_subAccountParams.setInstitutionCode("6D");
            l_subAccountParams.setInstitutionId(63);
            l_subAccountParams.setSubAccountType(SubAccountTypeEnum.EQUITY_SUB_ACCOUNT);

            MainAccountParams l_mainAccountParams =
                TestDBUtility.getMainAccountRow();
            l_mainAccountParams.setAccountId(333812512203L);

            ProductParams l_productParams =
                TestDBUtility.getProductRow();
            l_productParams.setProductId(3304148080001L);
            l_productParams.setInstitutionCode("6D");
            l_productParams.setProductType(ProductTypeEnum.EQUITY);

            MarketParams l_marketParams =
                TestDBUtility.getMarketRow();
            l_marketParams.setMarketId(3303L);
            l_marketParams.setMarketCode("N1");
            l_marketParams.setInstitutionCode("6D");

            BranchParams l_branchParams =
                TestDBUtility.getBranchRow();
            l_branchParams.setInstitutionCode("6D");
            l_branchParams.setInstitutionId(63);

            TradedProductParams l_tradedProductParams =
                TestDBUtility.getTradedProductRow();
            l_tradedProductParams.setTradedProductId(330304148080001L);
            l_tradedProductParams.setInstitutionCode("6D");
            l_tradedProductParams.setProductId(3304148080001L);
            l_tradedProductParams.setProductType(ProductTypeEnum.EQUITY);
            
            TraderParams l_traderParams =
                TestDBUtility.getTraderRow();
            l_traderParams.setTraderId(3338111123L);
            l_traderParams.setInstitutionCode("6D");
            l_traderParams.setBranchId(33381L);
            l_traderParams.setBranchCode("381");
            
            TestDBUtility.deleteAll(EqtypeOrderUnitRow.TYPE);
            TestDBUtility.deleteAll(SubAccountRow.TYPE);
            TestDBUtility.deleteAll(MainAccountRow.TYPE);
            TestDBUtility.deleteAll(ProductRow.TYPE);
            TestDBUtility.deleteAll(MarketRow.TYPE);
            TestDBUtility.deleteAll(BranchRow.TYPE);
            TestDBUtility.deleteAll(TradedProductRow.TYPE);
            TestDBUtility.deleteAll(TraderRow.TYPE);
            

            TestDBUtility.insertWithDel(l_eqTypeOrderUnitParams);
            TestDBUtility.insertWithDel(l_subAccountParams);
            TestDBUtility.insertWithDel(l_mainAccountParams);
            TestDBUtility.insertWithDel(l_productParams);
            TestDBUtility.insertWithDel(l_marketParams);
            TestDBUtility.insertWithDel(l_branchParams);
            TestDBUtility.insertWithDel(l_tradedProductParams);
            TestDBUtility.insertWithDel(l_traderParams);

            FinApp l_finApp = (FinApp)Services.getService(FinApp.class);
            TradingModule l_tradingModule =
                l_finApp.getTradingModule(ProductTypeEnum.EQUITY);
            WEB3EquityOrderManager l_equityOrderManager =
                (WEB3EquityOrderManager)l_tradingModule.getOrderManager();
            List l_lisResult = new ArrayList();
            l_lisResult.add(l_equityOrderManager.toOrderUnit(l_eqTypeOrderUnitParams));      
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                "webbroker3.equity.WEB3EquityOrderManager",
                "getOrderUnits",
                new Class[] { WEB3GentradeSubAccount.class, ProductTypeEnum.class, String.class, Object[].class,
                    String.class },
                 l_lisResult);

            WEB3EquityTradedProduct l_tradedProduct =
                new WEB3EquityTradedProduct(l_tradedProductParams);
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                "webbroker3.equity.WEB3EquityOrderManager",
                "validateTradedProduct",
                new Class[] {EqTypeProduct.class, Market.class},
                l_tradedProduct);
            
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                "webbroker3.equity.WEB3EquityOrderManager",
                "validateHandlingMarket",
                new Class[] {WEB3GentradeBranch.class,TradedProduct.class},
                null);

            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                "webbroker3.equity.WEB3EquityOrderManager",
                "validateOrderForChangeability",
                new Class[] {Order.class},
                null);

            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                "webbroker3.equity.WEB3EquityOrderManager",
                "validateInsider",
                new Class[] {SubAccount.class,
                    EqTypeProduct.class},
                null);
            
            WEB3EquityExecuteReferenceRequestForMock l_request =
                new WEB3EquityExecuteReferenceRequestForMock();
            l_request.execType = "0";
            l_request.pageSize = "1";
            l_request.referenceType = WEB3EquityReferenceTypeDef.REFERENCE_TYPE_UPDATE;
            l_request.pageIndex = "10";
            
            WEB3EquityExecuteReferenceResponse l_response =
                impl.searchExecuteReference(l_request);
            assertEquals(l_response.executeGroups[0].orgOrderCondPrice, "200");

        }
        catch (Exception l_ex)
        {
            log.error(l_ex.getMessage(), l_ex);
            fail();
        }

        log.exiting(STR_METHOD_NAME);
    }

    /*
     * Test method for 'webbroker3.equity.service.delegate.stdimpls.WEB3EquityExecuteReferenceServiceImpl.searchExecuteDetails(WEB3EquityExecuteDetailsRequest)'
     */
    public void testSearchExecuteDetails_case1()
    {
        final String STR_METHOD_NAME = "testSearchExecuteDetails_case1()";
        log.entering(STR_METHOD_NAME);

        MOCK_MANAGER.setIsMockUsed(true);
        TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
            "com.fitechlabs.xtrade.plugin.security.oplogin.impl.OpLoginSecurityServiceImpl",
            "getAccountId",
            new Class[] {},new Long(333812512203L));
        
        TestBaseForMock.MOCK_MANAGER.setMethodParamsValue(
            "webbroker3.equity.WEB3EquityBizLogicProvider",
            "getCommissionCourseDiv",
            new Class[] {String.class, String.class, String.class, String.class, Date.class},
            null);
        try
        {
            WEB3GentradeTradingTimeManagementForMock l_mock =
                new WEB3GentradeTradingTimeManagementForMock();
            WEB3GentradeTradingTimeManagementForMock.mockValidateOrderAccept(true);
            EqtypeOrderUnitParams l_eqTypeOrderUnitParams =
                TestDBUtility.getEqtypeOrderUnitRow();
            l_eqTypeOrderUnitParams.setOrderId(1003L);
            l_eqTypeOrderUnitParams.setBranchId(63101);
            l_eqTypeOrderUnitParams.setProductType(ProductTypeEnum.EQUITY);
            l_eqTypeOrderUnitParams.setOrderRequestNumber("000022001");
            l_eqTypeOrderUnitParams.setExecutionConditionType(EqTypeExecutionConditionType.NONE);
            l_eqTypeOrderUnitParams.setRequestType("2");
            l_eqTypeOrderUnitParams.setWLimitBeforeExecCondType(EqTypeExecutionConditionType.NONE);
            l_eqTypeOrderUnitParams.setOrgWLimitExecCondType(EqTypeExecutionConditionType.NONE);

            SubAccountParams l_subAccountParams =
                TestDBUtility.getSubAccountRow();
            l_subAccountParams.setInstitutionCode("6D");
            l_subAccountParams.setInstitutionId(63);
            l_subAccountParams.setSubAccountType(SubAccountTypeEnum.EQUITY_SUB_ACCOUNT);

            MainAccountParams l_mainAccountParams =
                TestDBUtility.getMainAccountRow();
            l_mainAccountParams.setAccountId(333812512203L);

            MarketParams l_marketParams =
                TestDBUtility.getMarketRow();
            l_marketParams.setMarketId(3303L);
            l_marketParams.setInstitutionCode("6D");
            
            TestDBUtility.deleteAll(EqtypeOrderUnitRow.TYPE);
            TestDBUtility.deleteAll(SubAccountRow.TYPE);
            TestDBUtility.deleteAll(MainAccountRow.TYPE);

            TestDBUtility.insertWithDel(l_eqTypeOrderUnitParams);
            TestDBUtility.insertWithDel(l_subAccountParams);
            TestDBUtility.insertWithDel(l_mainAccountParams);
            TestDBUtility.insertWithDel(l_marketParams);
            
            WEB3EquityExecuteDetailsRequestForMock l_request =
                new WEB3EquityExecuteDetailsRequestForMock();
            l_request.id = "1003";
            WEB3EquityExecuteDetailsResponse l_response =
                impl.searchExecuteDetails(l_request);
            assertEquals(l_response.orgOrderCondPrice, null);
            assertEquals(l_response.orgWlimitOrderPriceDiv, null);
            assertEquals(l_response.orgWlimitPrice, null);
            assertEquals(l_response.orgWlimitExecCondType, null);

        }
        catch (Exception l_ex)
        {
            log.error(l_ex.getMessage(), l_ex);
            fail();
        }

        log.exiting(STR_METHOD_NAME);
    }

    public void testSearchExecuteDetails_case2()
    {
        final String STR_METHOD_NAME = "testSearchExecuteDetails_case2()";
        log.entering(STR_METHOD_NAME);

        MOCK_MANAGER.setIsMockUsed(true);
        TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
            "com.fitechlabs.xtrade.plugin.security.oplogin.impl.OpLoginSecurityServiceImpl",
            "getAccountId",
            new Class[] {},new Long(333812512203L));
        
        TestBaseForMock.MOCK_MANAGER.setMethodParamsValue(
            "webbroker3.equity.WEB3EquityBizLogicProvider",
            "getCommissionCourseDiv",
            new Class[] {String.class, String.class, String.class, String.class, Date.class},
            null);
        try
        {
            WEB3GentradeTradingTimeManagementForMock l_mock =
                new WEB3GentradeTradingTimeManagementForMock();
            WEB3GentradeTradingTimeManagementForMock.mockValidateOrderAccept(true);
            EqtypeOrderUnitParams l_eqTypeOrderUnitParams =
                TestDBUtility.getEqtypeOrderUnitRow();
            l_eqTypeOrderUnitParams.setOrderId(1003L);
            l_eqTypeOrderUnitParams.setBranchId(63101);
            l_eqTypeOrderUnitParams.setProductType(ProductTypeEnum.EQUITY);
            l_eqTypeOrderUnitParams.setOrderRequestNumber("000022001");
            l_eqTypeOrderUnitParams.setExecutionConditionType(EqTypeExecutionConditionType.NONE);
            l_eqTypeOrderUnitParams.setOrgStopOrderPrice(200);
            l_eqTypeOrderUnitParams.setOrgOrderConditionType("2");
            l_eqTypeOrderUnitParams.setRequestType("2");
            l_eqTypeOrderUnitParams.setWLimitBeforeExecCondType(EqTypeExecutionConditionType.NONE);
            l_eqTypeOrderUnitParams.setOrgWLimitExecCondType(EqTypeExecutionConditionType.NONE);
            l_eqTypeOrderUnitParams.setOrgWLimitPrice(150D);

            SubAccountParams l_subAccountParams =
                TestDBUtility.getSubAccountRow();
            l_subAccountParams.setInstitutionCode("6D");
            l_subAccountParams.setInstitutionId(63);
            l_subAccountParams.setSubAccountType(SubAccountTypeEnum.EQUITY_SUB_ACCOUNT);

            MainAccountParams l_mainAccountParams =
                TestDBUtility.getMainAccountRow();
            l_mainAccountParams.setAccountId(333812512203L);

            MarketParams l_marketParams =
                TestDBUtility.getMarketRow();
            l_marketParams.setMarketId(3303L);
            l_marketParams.setInstitutionCode("6D");
            
            TestDBUtility.deleteAll(EqtypeOrderUnitRow.TYPE);
            TestDBUtility.deleteAll(SubAccountRow.TYPE);
            TestDBUtility.deleteAll(MainAccountRow.TYPE);
            TestDBUtility.deleteAll(MarketRow.TYPE);
            

            TestDBUtility.insertWithDel(l_eqTypeOrderUnitParams);
            TestDBUtility.insertWithDel(l_subAccountParams);
            TestDBUtility.insertWithDel(l_mainAccountParams);
            TestDBUtility.insertWithDel(l_marketParams);
            
            WEB3EquityExecuteDetailsRequestForMock l_request =
                new WEB3EquityExecuteDetailsRequestForMock();
            l_request.id = "1003";
            WEB3EquityExecuteDetailsResponse l_response =
                impl.searchExecuteDetails(l_request);
            assertEquals(l_response.orgOrderCondPrice, "200");
            assertEquals(l_response.orgWlimitOrderPriceDiv, WEB3OrderPriceDivDef.LIMIT_PRICE);
            assertEquals(l_response.orgWlimitPrice, "150");
            assertEquals(l_response.orgWlimitExecCondType, WEB3SonarExecutionConditionDef.UNCONDITIONDNESS);

        }
        catch (Exception l_ex)
        {
            log.error(l_ex.getMessage(), l_ex);
            fail();
        }

        log.exiting(STR_METHOD_NAME);
    }

    /*
     * Test method for 'webbroker3.equity.service.delegate.stdimpls.WEB3EquityExecuteReferenceServiceImpl.searchOrderHistory(WEB3EquityOrderHistoryRequest)'
     */
    public void testSearchOrderHistory_case1()
    {
        final String STR_METHOD_NAME = "testSearchOrderHistory_case1()";
        log.entering(STR_METHOD_NAME);

        MOCK_MANAGER.setIsMockUsed(true);
        
        TestBaseForMock.MOCK_MANAGER.setMethodParamsValue(
            "webbroker3.equity.WEB3EquityBizLogicProvider",
            "getCommissionCourseDiv",
            new Class[] {String.class, String.class, String.class, String.class, Date.class},
            null);
        try
        {
            WEB3GentradeTradingTimeManagementForMock l_mock =
                new WEB3GentradeTradingTimeManagementForMock();
            WEB3GentradeTradingTimeManagementForMock.mockValidateOrderAccept(true);
            EqtypeOrderUnitParams l_eqTypeOrderUnitParams =
                TestDBUtility.getEqtypeOrderUnitRow();
            l_eqTypeOrderUnitParams.setOrderId(1003L);
            l_eqTypeOrderUnitParams.setBranchId(63101);
            l_eqTypeOrderUnitParams.setProductType(ProductTypeEnum.EQUITY);
            l_eqTypeOrderUnitParams.setOrderRequestNumber("000022001");
            l_eqTypeOrderUnitParams.setExecutionConditionType(EqTypeExecutionConditionType.NONE);
            l_eqTypeOrderUnitParams.setOrgStopOrderPrice(200);
            l_eqTypeOrderUnitParams.setOrgOrderConditionType("2");
            l_eqTypeOrderUnitParams.setRequestType("2");
            l_eqTypeOrderUnitParams.setWLimitBeforeExecCondType(EqTypeExecutionConditionType.NONE);
            l_eqTypeOrderUnitParams.setOrgWLimitExecCondType(EqTypeExecutionConditionType.NONE);

            SubAccountParams l_subAccountParams =
                TestDBUtility.getSubAccountRow();
            l_subAccountParams.setInstitutionCode("6D");
            l_subAccountParams.setInstitutionId(63);
            

            MarketParams l_marketParams =
                TestDBUtility.getMarketRow();
            l_marketParams.setMarketId(3303L);
            l_marketParams.setInstitutionCode("6D");
            EqtypeOrderActionParams l_eqtypeOrderActionParams =
                this.insertOrderAction();
            
            TestDBUtility.deleteAll(EqtypeOrderUnitRow.TYPE);
            TestDBUtility.deleteAll(SubAccountRow.TYPE);
            TestDBUtility.deleteAll(MarketRow.TYPE);
            TestDBUtility.deleteAll(EqtypeOrderActionRow.TYPE);
            

            TestDBUtility.insertWithDel(l_eqTypeOrderUnitParams);
            TestDBUtility.insertWithDel(l_subAccountParams);
            TestDBUtility.insertWithDel(l_marketParams);
            TestDBUtility.insertWithDel(l_eqtypeOrderActionParams);
            
            WEB3EquityOrderHistoryRequestForMock l_request =
                new WEB3EquityOrderHistoryRequestForMock();
            l_request.id = "1003";
            WEB3EquityOrderHistoryResponse l_response =
                impl.searchOrderHistory(l_request);
            assertEquals(l_response.changeCancelHistoryGroups[0].orgOrderCondPrice, null);
        }
        catch (Exception l_ex)
        {
            log.error(l_ex.getMessage(), l_ex);
            fail();
        }

        log.exiting(STR_METHOD_NAME);
    }

    public void testSearchOrderHistory_case2()
    {
        final String STR_METHOD_NAME = "testSearchOrderHistory_case2()";
        log.entering(STR_METHOD_NAME);

        MOCK_MANAGER.setIsMockUsed(true);
        TestBaseForMock.MOCK_MANAGER.setMethodParamsValue(
            "webbroker3.equity.WEB3EquityBizLogicProvider",
            "getCommissionCourseDiv",
            new Class[] {String.class, String.class, String.class, String.class, Date.class},
            null);
        try
        {
            WEB3GentradeTradingTimeManagementForMock l_mock =
                new WEB3GentradeTradingTimeManagementForMock();
            WEB3GentradeTradingTimeManagementForMock.mockValidateOrderAccept(true);
            EqtypeOrderUnitParams l_eqTypeOrderUnitParams =
                TestDBUtility.getEqtypeOrderUnitRow();
            l_eqTypeOrderUnitParams.setOrderId(1003L);
            l_eqTypeOrderUnitParams.setBranchId(63101);
            l_eqTypeOrderUnitParams.setProductType(ProductTypeEnum.EQUITY);
            l_eqTypeOrderUnitParams.setOrderRequestNumber("000022001");
            l_eqTypeOrderUnitParams.setExecutionConditionType(EqTypeExecutionConditionType.NONE);
            l_eqTypeOrderUnitParams.setOrgStopOrderPrice(200);
            l_eqTypeOrderUnitParams.setOrgOrderConditionType("2");
            l_eqTypeOrderUnitParams.setRequestType("2");
            l_eqTypeOrderUnitParams.setWLimitBeforeExecCondType(EqTypeExecutionConditionType.NONE);
            l_eqTypeOrderUnitParams.setOrgWLimitExecCondType(EqTypeExecutionConditionType.NONE);

            SubAccountParams l_subAccountParams =
                TestDBUtility.getSubAccountRow();
            l_subAccountParams.setInstitutionCode("6D");
            l_subAccountParams.setInstitutionId(63);
            

            MarketParams l_marketParams =
                TestDBUtility.getMarketRow();
            l_marketParams.setMarketId(3303L);
            l_marketParams.setInstitutionCode("6D");
            
            EqtypeOrderActionParams l_eqtypeOrderActionParams =
                this.insertOrderAction();
            l_eqtypeOrderActionParams.setOrgStopOrderPrice(1300);
            
            TestDBUtility.deleteAll(EqtypeOrderUnitRow.TYPE);
            TestDBUtility.deleteAll(SubAccountRow.TYPE);
            TestDBUtility.deleteAll(MarketRow.TYPE);
            TestDBUtility.deleteAll(EqtypeOrderActionRow.TYPE);
            

            TestDBUtility.insertWithDel(l_eqTypeOrderUnitParams);
            TestDBUtility.insertWithDel(l_subAccountParams);
            TestDBUtility.insertWithDel(l_marketParams);
            TestDBUtility.insertWithDel(l_eqtypeOrderActionParams);
            
            WEB3EquityOrderHistoryRequestForMock l_request =
                new WEB3EquityOrderHistoryRequestForMock();
            l_request.id = "1003";
            WEB3EquityOrderHistoryResponse l_response =
                impl.searchOrderHistory(l_request);
            assertEquals(l_response.changeCancelHistoryGroups[0].orgOrderCondPrice, "1300");
        }
        catch (Exception l_ex)
        {
            log.error(l_ex.getMessage(), l_ex);
            fail();
        }

        log.exiting(STR_METHOD_NAME);
    }

    public EqtypeOrderActionParams insertOrderAction()
        {
            final String STR_METHOD_NAME = "insertOrderAction()";
            log.entering(STR_METHOD_NAME);
            EqtypeOrderActionParams l_params = new EqtypeOrderActionParams();
            //注文履歴ＩＤ  order_action_id  NUMBER 18  NOT NULL  
            l_params.setOrderActionId(123456L);
            //口座ＩＤ  account_id    NUMBER    18 NOT NULL        
            l_params.setAccountId(333812512203L);
            //補助口座ＩＤ   sub_account_id  NUMBER              18          NOT NULL        
            l_params.setSubAccountId(33381251220301L);
            //取引者ＩＤ  trader_id   NUMBER              18          NULL        
            l_params.setTraderId(3338111123L);
            //注文ＩＤ   order_id   NUMBER              18          NOT NULL        
            l_params.setOrderId(1003L);
            //注文単位ＩＤ   order_unit_id  NUMBER              18          NOT NULL     
            l_params.setOrderUnitId(3304148080001L);
            //市場ＩＤ  market_id    NUMBER              18          NULL        
            l_params.setMarketId(3303L);
            //注文種別   order_type  NUMBER              6           NOT NULL   
            l_params.setOrderType(OrderTypeEnum.SWAP_MARGIN_LONG);
            //注文イベントタイプ  order_event_type  NUMBER              6           NOT NULL   
            l_params.setOrderEventType(OrderEventTypeEnum.CANCEL);
            //注文単価  price   DECIMAL  18  12  6   NULL  
//            l_params.setPrice(l_dbPrice);
            //執行条件  execution_condition_type    NUMBER              6           NULL    
            l_params.setExecutionConditionType(EqTypeExecutionConditionType.AT_MARKET_CLOSE_NOT_EXECUTED);
            //値段条件  price_condition_type  VARCHAR2   1           NULL        
            //発注条件 order_condition_type  VARCHAR2     1           NULL   
//            l_params.setOrderConditionType(l_strOrderConditionType);
            //発注条件演算子  order_cond_operator  VARCHAR2       1           NULL        
            //逆指値基準値  stop_order_price    DECIMAL    18  12  6   NULL        
            //（W指値）訂正指値  w_limit_price   DECIMAL    18  12  6   NULL        
            //注文失効日付  expiration_date    DATE     NULL        
            //注文数量   quantity    DECIMAL    18  12  6   NOT NULL   
            l_params.setQuantity(200);
            //市場と確認済みの指値   confirmed_price   DECIMAL      18  12  6   NULL      
//            l_params.setConfirmedPrice(l_dbConfirmedPrice);
            //市場と確認済みの数量    confirmed_quantity   DECIMAL       18  12  6   NULL        
            //約定数量  executed_quantity   DECIMAL    18  12  6   NULL   
//            l_params.setExecutedQuantity(l_dbExecutedQuantity);
            //注文状態  order_status     NUMBER              6           NOT NULL 
            l_params.setOrderStatus(OrderStatusEnum.ACCEPTED);
            //注文失効ステータス   expiration_status     NUMBER              6           NOT NULL    
            l_params.setExpirationStatus(OrderExpirationStatusEnum.OPEN);
            //注文履歴番号   order_action_serial_no     NUMBER              8           NOT NULL   
            l_params.setOrderActionSerialNo(1);
            //約定単価   executed_price   DECIMAL             18  12  6   NULL        
            //銘柄タイプ  product_type    NUMBER              6           NOT NULL 
            l_params.setProductType(ProductTypeEnum.EQUITY);
            //銘柄ＩＤ   product_id     NUMBER              18          NOT NULL 
            l_params.setProductId(3304148080001L);
            //注文数量タイプ   quantity_type     NUMBER              6           NOT NULL 
            l_params.setQuantityType(QuantityTypeEnum.AMOUNT);
            //概算受渡代金   estimated_price   DECIMAL             18  12  6   NULL        
            //注文訂正・取消区分  modify_cancel_type    VARCHAR2                1           NULL    
//            l_params.setModifyCancelType(l_strModifyCancelType);
            //注文経路区分   order_root_div    VARCHAR2                1           NULL        
            //決済順序区分    closing_order_type     VARCHAR2                1           NULL        
            //注文エラー理由コード  error_reason_code    VARCHAR2                4           NULL        
            //リクエストタイプ    request_type   VARCHAR2                1           NULL    
//            l_params.setRequestType(l_strRequestType);
            //作成日付   created_timestamp      DATE        NOT NULL  
            l_params.setCreatedTimestamp(new Timestamp(20061010L));
            //更新日付   last_updated_timestamp   DATE           NOT NULL  
            l_params.setLastUpdatedTimestamp(new Timestamp(20061015L));
            //IPアドレス  ip_address   VARCHAR2                15          NULL        
            //元発注条件   org_order_condition_type    VARCHAR2        1           NULL    
//            l_params.setOrgOrderConditionType(l_strOrgOrderConditionType);
            //元発注条件演算子   org_order_cond_operator   VARCHAR2        1    NULL        
            //元逆指値基準値   org_stop_order_price    DECIMAL    18  12  6   NULL
            //元（W指値）訂正指値   org_w_limit_price    DECIMAL     18  12  6   NULL    
//            l_params.setOrgWLimitPrice(l_dbOrgWLimitPrice);
            //元（W指値）執行条件    org_w_limit_exec_cond_type    NUMBER              6           NULL      
            l_params.setOrgWLimitExecCondType(EqTypeExecutionConditionType.AT_MARKET_OPEN);
            //（W指値）執行条件     w_limit_exec_cond_type     NUMBER              6           NULL        
            //（W指値）切替前指値    w_limit_before_limit_price     DECIMAL             18  12  6   NULL      
//            l_params.setWLimitBeforeLimitPrice(l_dbWLimitBeforeLimitPrice);
            //（W指値）切替前執行条件     w_limit_before_exec_cond_type      NUMBER    6   NULL  
            l_params.setWLimitBeforeExecCondType(EqTypeExecutionConditionType.AT_MARKET_CLOSE);
            //市場から確認済みの執行条件     confirmed_exec_condition_type     NUMBER    6       NULL  
            l_params.setConfirmedExecConditionType(EqTypeExecutionConditionType.AT_MARKET_OPEN);

            log.exiting(STR_METHOD_NAME);
            return l_params;
        }
}
@
