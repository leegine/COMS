head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.04.07.02.40.21;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	5444d9d078c63f4;
filename	WEB3MarginExecuteReferenceServiceImplTest.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@package webbroker3.equity.service.delegate.stdimpls;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.fitechlabs.xtrade.kernel.boot.Services;
import com.fitechlabs.xtrade.plugin.tc.eqtype.EqTypeExecutionConditionType;
import com.fitechlabs.xtrade.plugin.tc.eqtype.data.EqtypeOrderParams;
import com.fitechlabs.xtrade.plugin.tc.eqtype.data.EqtypeOrderRow;
import com.fitechlabs.xtrade.plugin.tc.eqtype.data.EqtypeOrderUnitParams;
import com.fitechlabs.xtrade.plugin.tc.eqtype.data.EqtypeOrderUnitRow;
import com.fitechlabs.xtrade.plugin.tc.gentrade.AccountManager;
import com.fitechlabs.xtrade.plugin.tc.gentrade.FinApp;
import com.fitechlabs.xtrade.plugin.tc.gentrade.ProductTypeEnum;
import com.fitechlabs.xtrade.plugin.tc.gentrade.SubAccountTypeEnum;
import com.fitechlabs.xtrade.plugin.tc.gentrade.TradingModule;
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
import com.fitechlabs.xtrade.plugin.tc.gentrade.data.TraderParams;
import com.fitechlabs.xtrade.plugin.tc.gentrade.data.TraderRow;

import test.util.TestDBUtility;

import webbroker3.common.WEB3BaseException;
import webbroker3.common.define.WEB3OrderPriceDivDef;
import webbroker3.common.define.WEB3SonarExecutionConditionDef;
import webbroker3.equity.WEB3EquityOrderManager;
import webbroker3.equity.message.WEB3MarginExecuteDetailsRequest;
import webbroker3.equity.message.WEB3MarginExecuteDetailsResponse;
import webbroker3.equity.message.WEB3MarginExecuteGroup;
import webbroker3.equity.message.WEB3MarginExecuteReferenceRequest;
import webbroker3.equity.message.WEB3MarginExecuteReferenceResponse;
import webbroker3.gentrade.WEB3GentradeSubAccount;
import webbroker3.gentrade.WEB3GentradeTradingTimeManagementForMock;
import webbroker3.mock.TestBaseForMock;
import webbroker3.util.WEB3DateUtility;
import webbroker3.util.WEB3LogUtility;

/**
 * 
 * @@author tang-xingfeng
 *
 */
public class WEB3MarginExecuteReferenceServiceImplTest extends TestBaseForMock
{
    /**
     * ログ出力ユーティリティ<BR>
     */
    private static WEB3LogUtility log =
        WEB3LogUtility.getInstance(WEB3MarginExecuteReferenceServiceImplTest.class);
    WEB3MarginExecuteReferenceServiceImpl impl =
        new WEB3MarginExecuteReferenceServiceImpl();

    public WEB3MarginExecuteReferenceServiceImplTest(String arg0)
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

    public class WEB3MarginExecuteReferenceServiceImplInMock extends WEB3MarginExecuteReferenceServiceImpl
    {
        public String[] createSearchCondDataContainers(
                String l_strProductCode,
                String l_strMarketCode,
                Date l_datOrderBizDate)
        throws WEB3BaseException
        {
            final String STR_METHOD_NAME =
                "createSearchCondDataContainers(String ,String ,Date)";
            log.entering(STR_METHOD_NAME);

            String[] l_strLists = {"0"};
            log.exiting(STR_METHOD_NAME);
            return l_strLists;
        }
    }
    /*
     * Test method for 'webbroker3.equity.service.delegate.stdimpls.WEB3MarginExecuteReferenceServiceImpl.searchOrderExecuteDetails(WEB3MarginExecuteDetailsRequest)'
     */
    public void testSearchOrderExecuteDetails_case1()
    {
        final String STR_METHOD_NAME = " testSearchOrderExecuteDetails_case1()";
        log.info(TEST_START + STR_METHOD_NAME);

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
            WEB3GentradeTradingTimeManagementForMock.mockIsTradeOpenTimeZone(true);
            EqtypeOrderUnitParams l_eqTypeOrderUnitParams =
                TestDBUtility.getEqtypeOrderUnitRow();
            l_eqTypeOrderUnitParams.setOrderId(1003L);
            l_eqTypeOrderUnitParams.setBranchId(63101);
            l_eqTypeOrderUnitParams.setProductType(ProductTypeEnum.EQUITY);
            l_eqTypeOrderUnitParams.setOrderRequestNumber("000022001");
            l_eqTypeOrderUnitParams.setExecutionConditionType(EqTypeExecutionConditionType.NONE);

            SubAccountParams l_subAccountParams =
                TestDBUtility.getSubAccountRow();
            l_subAccountParams.setInstitutionCode("6D");
            l_subAccountParams.setInstitutionId(63);

            MainAccountParams l_mainAccountParams =
                TestDBUtility.getMainAccountRow();
            l_mainAccountParams.setAccountId(333812512203L);

            InstitutionParams l_institutionParams =
                TestDBUtility.getInstitutionRow();
            l_institutionParams.setInstitutionCode("6D");
            l_institutionParams.setInstitutionId(63);

            ProductParams l_productParams =
                TestDBUtility.getProductRow();
            l_productParams.setProductId(3304148080001L);
            l_productParams.setInstitutionCode("6D");
            l_productParams.setProductType(ProductTypeEnum.EQUITY);

            MarketParams l_marketParams =
                TestDBUtility.getMarketRow();
            l_marketParams.setMarketId(3303L);
            l_marketParams.setInstitutionCode("6D");

            BranchParams l_branchParams =
                TestDBUtility.getBranchRow();
            l_branchParams.setInstitutionCode("6D");
            l_branchParams.setInstitutionId(63);
            
            
            TestDBUtility.deleteAll(EqtypeOrderUnitRow.TYPE);
            TestDBUtility.deleteAll(SubAccountRow.TYPE);
            TestDBUtility.deleteAll(MainAccountRow.TYPE);
            TestDBUtility.deleteAll(InstitutionRow.TYPE);
            TestDBUtility.deleteAll(ProductRow.TYPE);
            TestDBUtility.deleteAll(MarketRow.TYPE);
            TestDBUtility.deleteAll(BranchRow.TYPE);
            

            TestDBUtility.insertWithDel(l_eqTypeOrderUnitParams);
            TestDBUtility.insertWithDel(l_subAccountParams);
            TestDBUtility.insertWithDel(l_mainAccountParams);
            TestDBUtility.insertWithDel(l_institutionParams);
            TestDBUtility.insertWithDel(l_productParams);
            TestDBUtility.insertWithDel(l_marketParams);
            TestDBUtility.insertWithDel(l_branchParams);


            WEB3MarginExecuteDetailsRequest l_request = new WEB3MarginExecuteDetailsRequest();
            l_request.id = "1003";
            WEB3MarginExecuteDetailsResponse l_response =
                impl.searchOrderExecuteDetails(l_request);
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
    }

    public void testSearchOrderExecuteDetails_case2()
    {
        final String STR_METHOD_NAME = " testSearchOrderExecuteDetails_case2()";
        log.info(TEST_START + STR_METHOD_NAME);

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
            WEB3GentradeTradingTimeManagementForMock.mockIsTradeOpenTimeZone(true);
            EqtypeOrderUnitParams l_eqTypeOrderUnitParams =
                TestDBUtility.getEqtypeOrderUnitRow();
            l_eqTypeOrderUnitParams.setOrderId(1003L);
            l_eqTypeOrderUnitParams.setBranchId(63101);
            l_eqTypeOrderUnitParams.setProductType(ProductTypeEnum.EQUITY);
            l_eqTypeOrderUnitParams.setOrderRequestNumber("000022001");
            l_eqTypeOrderUnitParams.setExecutionConditionType(EqTypeExecutionConditionType.NONE);
            l_eqTypeOrderUnitParams.setOrgStopOrderPrice(200);
            l_eqTypeOrderUnitParams.setOrgOrderConditionType("2");
            l_eqTypeOrderUnitParams.setOrderConditionType("1");
            l_eqTypeOrderUnitParams.setRequestType("2");
            l_eqTypeOrderUnitParams.setWLimitBeforeExecCondType(EqTypeExecutionConditionType.NONE);
            l_eqTypeOrderUnitParams.setOrgWLimitExecCondType(EqTypeExecutionConditionType.NONE);
            

            SubAccountParams l_subAccountParams =
                TestDBUtility.getSubAccountRow();
            l_subAccountParams.setInstitutionCode("6D");
            l_subAccountParams.setInstitutionId(63);

            MainAccountParams l_mainAccountParams =
                TestDBUtility.getMainAccountRow();
            l_mainAccountParams.setAccountId(333812512203L);

            InstitutionParams l_institutionParams =
                TestDBUtility.getInstitutionRow();
            l_institutionParams.setInstitutionCode("6D");
            l_institutionParams.setInstitutionId(63);

            ProductParams l_productParams =
                TestDBUtility.getProductRow();
            l_productParams.setProductId(3304148080001L);
            l_productParams.setInstitutionCode("6D");
            l_productParams.setProductType(ProductTypeEnum.EQUITY);

            MarketParams l_marketParams =
                TestDBUtility.getMarketRow();
            l_marketParams.setMarketId(3303L);
            l_marketParams.setInstitutionCode("6D");

            BranchParams l_branchParams =
                TestDBUtility.getBranchRow();
            l_branchParams.setInstitutionCode("6D");
            l_branchParams.setInstitutionId(63);
            
            
            TestDBUtility.deleteAll(EqtypeOrderUnitRow.TYPE);
            TestDBUtility.deleteAll(SubAccountRow.TYPE);
            TestDBUtility.deleteAll(MainAccountRow.TYPE);
            TestDBUtility.deleteAll(InstitutionRow.TYPE);
            TestDBUtility.deleteAll(ProductRow.TYPE);
            TestDBUtility.deleteAll(MarketRow.TYPE);
            TestDBUtility.deleteAll(BranchRow.TYPE);
            

            TestDBUtility.insertWithDel(l_eqTypeOrderUnitParams);
            TestDBUtility.insertWithDel(l_subAccountParams);
            TestDBUtility.insertWithDel(l_mainAccountParams);
            TestDBUtility.insertWithDel(l_institutionParams);
            TestDBUtility.insertWithDel(l_productParams);
            TestDBUtility.insertWithDel(l_marketParams);
            TestDBUtility.insertWithDel(l_branchParams);


            WEB3MarginExecuteDetailsRequest l_request = new WEB3MarginExecuteDetailsRequest();
            l_request.id = "1003";
            WEB3MarginExecuteDetailsResponse l_response =
                impl.searchOrderExecuteDetails(l_request);
            assertEquals(l_response.orgOrderCondPrice, "200");
            assertEquals(l_response.orgWlimitOrderPriceDiv, WEB3OrderPriceDivDef.MARKET_PRICE);
            assertEquals(l_response.orgWlimitPrice, null);
            assertEquals(l_response.orgWlimitExecCondType, WEB3SonarExecutionConditionDef.UNCONDITIONDNESS);
        }
        catch (Exception l_ex)
        {
            log.error(l_ex.getMessage(), l_ex);
            fail();
        }
    }

    /*
     * Test method for 'webbroker3.equity.service.delegate.stdimpls.WEB3MarginExecuteReferenceServiceImpl.createExecuteReference(WEB3GentradeSubAccount, WEB3MarginExecuteReferenceRequest, WEB3MarginExecuteReferenceResponse)'
     */
    public void testCreateExecuteReference_case1()
    {
        final String STR_METHOD_NAME = " testCreateExecuteReference_case1()";
        log.info(TEST_START + STR_METHOD_NAME);

        try
        {
            WEB3GentradeTradingTimeManagementForMock l_mock = new WEB3GentradeTradingTimeManagementForMock();
            WEB3MarginExecuteReferenceServiceImplInMock implInMock =
                new WEB3MarginExecuteReferenceServiceImplInMock();
            WEB3MarginExecuteReferenceRequest l_request =
                new WEB3MarginExecuteReferenceRequest();
            WEB3MarginExecuteReferenceResponse l_response =
                new WEB3MarginExecuteReferenceResponse();
            l_request.execType = null;
            l_request.productCode = "0";
            l_request.marketCode = "SP";
            l_request.orderBizDate = WEB3DateUtility.getDate("20040708", "yyyyMMdd");
            l_request.pageIndex = "50";
            l_request.pageSize = "200";

            EqtypeOrderUnitParams l_eqTypeOrderUnitParams =
                TestDBUtility.getEqtypeOrderUnitRow();
            l_eqTypeOrderUnitParams.setOrderId(1003L);
            l_eqTypeOrderUnitParams.setBranchId(63101);
            l_eqTypeOrderUnitParams.setProductType(ProductTypeEnum.EQUITY);
            l_eqTypeOrderUnitParams.setOrderRequestNumber("000022001");
            l_eqTypeOrderUnitParams.setExecutionConditionType(EqTypeExecutionConditionType.NONE);

            SubAccountParams l_subAccountParams =
                TestDBUtility.getSubAccountRow();
            l_subAccountParams.setInstitutionCode("6D");
            l_subAccountParams.setInstitutionId(63);
            
            MarketParams l_marketParams =
                TestDBUtility.getMarketRow();
            l_marketParams.setMarketId(3303L);
            l_marketParams.setInstitutionCode("6D");

            EqtypeOrderParams l_eqtypeOrderParams =
                TestDBUtility.getEqtypeOrderRow();
            l_eqtypeOrderParams.setAccountId(333812512203L);
            l_eqtypeOrderParams.setSubAccountId(33381251220301L);
            l_eqtypeOrderParams.setOrderId(1003L);

            TraderParams l_traderParams =
                TestDBUtility.getTraderRow();
            l_traderParams.setInstitutionCode("6D");
            l_traderParams.setBranchId(63101);
            l_traderParams.setBranchCode("101");
            

            TestDBUtility.deleteAllAndCommit(EqtypeOrderUnitRow.TYPE);
            TestDBUtility.deleteAllAndCommit(SubAccountRow.TYPE);
            TestDBUtility.deleteAllAndCommit(MarketRow.TYPE);
            TestDBUtility.deleteAllAndCommit(EqtypeOrderRow.TYPE);
            TestDBUtility.deleteAllAndCommit(TraderRow.TYPE);

            TestDBUtility.insertWithDelAndCommit(l_eqTypeOrderUnitParams);
            TestDBUtility.insertWithDelAndCommit(l_subAccountParams);
            TestDBUtility.insertWithDelAndCommit(l_marketParams);
            TestDBUtility.insertWithDelAndCommit(l_eqtypeOrderParams);
            TestDBUtility.insertWithDelAndCommit(l_traderParams);
            

            FinApp l_finApp = (FinApp) Services.getService(FinApp.class);
            AccountManager l_accMgr = l_finApp.getAccountManager();
            WEB3GentradeSubAccount l_subAccount =
                (WEB3GentradeSubAccount)l_accMgr.getMainAccount(333812512203L).getSubAccount(
                    SubAccountTypeEnum.EQUITY_MARGIN_SUB_ACCOUNT);

            TradingModule l_tradingModule =
                l_finApp.getTradingModule(ProductTypeEnum.EQUITY);
            WEB3EquityOrderManager l_equityOrderManager =
                (WEB3EquityOrderManager)l_tradingModule.getOrderManager();

            List l_lisResult = new ArrayList();
            l_lisResult.add(l_equityOrderManager.toOrderUnit(l_eqTypeOrderUnitParams));
            MOCK_MANAGER.setIsMockUsed(true);            
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                "webbroker3.equity.WEB3EquityOrderManager",
                "getOrderUnits",
                new Class[] { WEB3GentradeSubAccount.class, ProductTypeEnum.class, String.class, Object[].class,
                    String.class },
                 l_lisResult);
            
            WEB3MarginExecuteGroup[] l_group =
                implInMock.createExecuteReference(l_subAccount,l_request,l_response);
            
        }
        catch (Exception l_ex)
        {
            log.error(l_ex.getMessage(), l_ex);
            fail();
        }
    }

    public void testCreateExecuteReference_case2()
    {
        final String STR_METHOD_NAME = " testCreateExecuteReference_case2()";
        log.info(TEST_START + STR_METHOD_NAME);

        try
        {
            WEB3GentradeTradingTimeManagementForMock l_mock = new WEB3GentradeTradingTimeManagementForMock();
            WEB3MarginExecuteReferenceServiceImplInMock implInMock =
                new WEB3MarginExecuteReferenceServiceImplInMock();
            WEB3MarginExecuteReferenceRequest l_request =
                new WEB3MarginExecuteReferenceRequest();
            WEB3MarginExecuteReferenceResponse l_response =
                new WEB3MarginExecuteReferenceResponse();
            l_request.execType = null;
            l_request.productCode = "0";
            l_request.marketCode = "SP";
            l_request.orderBizDate = WEB3DateUtility.getDate("20040708", "yyyyMMdd");
            l_request.pageIndex = "50";
            l_request.pageSize = "200";

            EqtypeOrderUnitParams l_eqTypeOrderUnitParams =
                TestDBUtility.getEqtypeOrderUnitRow();
            l_eqTypeOrderUnitParams.setOrderId(1003L);
            l_eqTypeOrderUnitParams.setBranchId(63101);
            l_eqTypeOrderUnitParams.setProductType(ProductTypeEnum.EQUITY);
            l_eqTypeOrderUnitParams.setOrderRequestNumber("000022001");
            l_eqTypeOrderUnitParams.setExecutionConditionType(EqTypeExecutionConditionType.NONE);
            l_eqTypeOrderUnitParams.setOrgStopOrderPrice(200);

            SubAccountParams l_subAccountParams =
                TestDBUtility.getSubAccountRow();
            l_subAccountParams.setInstitutionCode("6D");
            l_subAccountParams.setInstitutionId(63);
            
            MarketParams l_marketParams =
                TestDBUtility.getMarketRow();
            l_marketParams.setMarketId(3303L);
            l_marketParams.setInstitutionCode("6D");

            EqtypeOrderParams l_eqtypeOrderParams =
                TestDBUtility.getEqtypeOrderRow();
            l_eqtypeOrderParams.setAccountId(333812512203L);
            l_eqtypeOrderParams.setSubAccountId(33381251220301L);
            l_eqtypeOrderParams.setOrderId(1003L);

            TraderParams l_traderParams =
                TestDBUtility.getTraderRow();
            l_traderParams.setInstitutionCode("6D");
            l_traderParams.setBranchId(63101);
            l_traderParams.setBranchCode("101");
            

            TestDBUtility.deleteAllAndCommit(EqtypeOrderUnitRow.TYPE);
            TestDBUtility.deleteAllAndCommit(SubAccountRow.TYPE);
            TestDBUtility.deleteAllAndCommit(MarketRow.TYPE);
            TestDBUtility.deleteAllAndCommit(EqtypeOrderRow.TYPE);
            TestDBUtility.deleteAllAndCommit(TraderRow.TYPE);

            TestDBUtility.insertWithDelAndCommit(l_eqTypeOrderUnitParams);
            TestDBUtility.insertWithDelAndCommit(l_subAccountParams);
            TestDBUtility.insertWithDelAndCommit(l_marketParams);
            TestDBUtility.insertWithDelAndCommit(l_eqtypeOrderParams);
            TestDBUtility.insertWithDelAndCommit(l_traderParams);
            

            FinApp l_finApp = (FinApp) Services.getService(FinApp.class);
            AccountManager l_accMgr = l_finApp.getAccountManager();
            WEB3GentradeSubAccount l_subAccount =
                (WEB3GentradeSubAccount)l_accMgr.getMainAccount(333812512203L).getSubAccount(
                    SubAccountTypeEnum.EQUITY_MARGIN_SUB_ACCOUNT);

            TradingModule l_tradingModule =
                l_finApp.getTradingModule(ProductTypeEnum.EQUITY);
            WEB3EquityOrderManager l_equityOrderManager =
                (WEB3EquityOrderManager)l_tradingModule.getOrderManager();

            List l_lisResult = new ArrayList();
            l_lisResult.add(l_equityOrderManager.toOrderUnit(l_eqTypeOrderUnitParams));
            MOCK_MANAGER.setIsMockUsed(true);            
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                "webbroker3.equity.WEB3EquityOrderManager",
                "getOrderUnits",
                new Class[] { WEB3GentradeSubAccount.class, ProductTypeEnum.class, String.class, Object[].class,
                    String.class },
                 l_lisResult);
            
            WEB3MarginExecuteGroup[] l_group =
                implInMock.createExecuteReference(l_subAccount,l_request,l_response);

            
        }
        catch (Exception l_ex)
        {
            log.error(l_ex.getMessage(), l_ex);
            fail();
        }
    }
}
@
