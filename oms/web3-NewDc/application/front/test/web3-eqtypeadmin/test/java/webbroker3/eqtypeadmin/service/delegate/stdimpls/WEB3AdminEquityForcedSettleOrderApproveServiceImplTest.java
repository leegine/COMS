head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.04.07.01.09.35;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	5444d9d078c63f4;
filename	WEB3AdminEquityForcedSettleOrderApproveServiceImplTest.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : 管理者・強制決済仮注文承認／非承認サービスImplTest(WEB3AdminEquityForcedSettleOrderApproveServiceImplTest.java)
Author Name      : Daiwa Institute of Research
Revision History : 2007/04/29 張騰宇 (中訊) 新規作成
*/
package webbroker3.eqtypeadmin.service.delegate.stdimpls;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

import com.fitechlabs.xtrade.kernel.comm.client.ServerAccessor;
import com.fitechlabs.xtrade.kernel.comm.client.ServerAccessorImplForMock;
import com.fitechlabs.xtrade.kernel.comm.client.ServerAccessorImplForMockException;
import com.fitechlabs.xtrade.kernel.data.db.ServerConfigParams;
import com.fitechlabs.xtrade.kernel.data.db.ServerConfigRow;
import com.fitechlabs.xtrade.plugin.security.oplogin.impl.LoginInfoImplForMock;
import com.fitechlabs.xtrade.plugin.tc.eqtype.data.EqtypeClosingContractSpecParams;
import com.fitechlabs.xtrade.plugin.tc.eqtype.data.EqtypeClosingContractSpecRow;
import com.fitechlabs.xtrade.plugin.tc.eqtype.data.EqtypeContractParams;
import com.fitechlabs.xtrade.plugin.tc.eqtype.data.EqtypeContractRow;
import com.fitechlabs.xtrade.plugin.tc.eqtype.data.EqtypeOrderUnitParams;
import com.fitechlabs.xtrade.plugin.tc.eqtype.data.EqtypeOrderUnitRow;
import com.fitechlabs.xtrade.plugin.tc.eqtype.data.EqtypeProductParams;
import com.fitechlabs.xtrade.plugin.tc.eqtype.data.EqtypeProductRow;
import com.fitechlabs.xtrade.plugin.tc.eqtype.data.EqtypeTradedProductParams;
import com.fitechlabs.xtrade.plugin.tc.eqtype.data.EqtypeTradedProductRow;
import com.fitechlabs.xtrade.plugin.tc.gentrade.BooleanEnum;
import com.fitechlabs.xtrade.plugin.tc.gentrade.ContractTypeEnum;
import com.fitechlabs.xtrade.plugin.tc.gentrade.GtlUtils;
import com.fitechlabs.xtrade.plugin.tc.gentrade.OrderOpenStatusEnum;
import com.fitechlabs.xtrade.plugin.tc.gentrade.OrderStatusEnum;
import com.fitechlabs.xtrade.plugin.tc.gentrade.ProductTypeEnum;
import com.fitechlabs.xtrade.plugin.tc.gentrade.SubAccountTypeEnum;
import com.fitechlabs.xtrade.plugin.tc.gentrade.TaxTypeEnum;
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

import test.util.TestDBUtility;
import webbroker3.common.WEB3BaseException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.common.WEB3SystemLayerException;
import webbroker3.common.define.WEB3DaemonTriggerStatusDef;
import webbroker3.common.define.WEB3FuturesOptionDivDef;
import webbroker3.common.define.WEB3LoginTypeAttributeKeyDef;
import webbroker3.common.define.WEB3RunStatusDivDef;
import webbroker3.common.define.WEB3TradingPwdEnvDef;
import webbroker3.common.message.WEB3GenRequest;
import webbroker3.eqtypeadmin.define.WEB3AdminEquityApproveTypeDef;
import webbroker3.eqtypeadmin.message.WEB3AdminEquityForcedSettleOrderApproveMainRequest;
import webbroker3.eqtypeadmin.message.WEB3AdminForcedSettleApproveConfirmRequest;
import webbroker3.eqtypeadmin.message.WEB3AdminForcedSettleApproveConfirmResponse;
import webbroker3.eqtypeadmin.message.WEB3AdminForcedSettleApproveRunRequest;
import webbroker3.eqtypeadmin.message.WEB3AdminForcedSettleApproveRunResponse;
import webbroker3.eqtypeadmin.message.WEB3AdminForcedSettleApproveStatusRequest;
import webbroker3.eqtypeadmin.message.WEB3AdminForcedSettleApproveStatusResponse;
import webbroker3.eqtypeadmin.message.WEB3AdminForcedSettleTemporaryOrderUnit;
import webbroker3.eqtypeadmin.message.WEB3AdminFrontNoticeHistoryReferenceRequest;
import webbroker3.gentrade.WEB3Administrator;
import webbroker3.gentrade.WEB3GentradeTradingTimeManagementForMock;
import webbroker3.gentrade.data.AdminPermissionParams;
import webbroker3.gentrade.data.AdminPermissionRow;
import webbroker3.gentrade.data.AdministratorParams;
import webbroker3.gentrade.data.AdministratorRow;
import webbroker3.gentrade.data.DaemonTriggerDao;
import webbroker3.gentrade.data.DaemonTriggerParams;
import webbroker3.gentrade.data.DaemonTriggerRow;
import webbroker3.gentrade.data.OnlineRunStatusDao;
import webbroker3.gentrade.data.OnlineRunStatusParams;
import webbroker3.gentrade.data.OnlineRunStatusRow;
import webbroker3.gentrade.data.TradingTimeParams;
import webbroker3.gentrade.data.TradingTimeRow;
import webbroker3.mock.TestBaseForMock;
import webbroker3.system.tune.affinity.message.WEB3AffinityDescendRequest;
import webbroker3.util.WEB3DateUtility;
import webbroker3.util.WEB3LogUtility;

public class WEB3AdminEquityForcedSettleOrderApproveServiceImplTest extends TestBaseForMock
{
    private static WEB3LogUtility log =
        WEB3LogUtility.getInstance(WEB3AdminEquityForcedSettleOrderApproveServiceImplTest.class);

    public WEB3AdminEquityForcedSettleOrderApproveServiceImplTest(String arg0)
    {
        super(arg0);
    }

    protected void setUp() throws Exception
    {
        super.setUp();
        MOCK_MANAGER.setIsMockUsed(true);
        
        TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
            "com.fitechlabs.xtrade.plugin.security.oplogin.impl.OpLoginSecurityServiceImpl",
            "getLoginInfo",
            new Class[] {},
            new LoginInfoImplForMock());
        
        TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                "com.fitechlabs.xtrade.plugin.security.oplogin.impl.OpLoginSecurityServiceImpl",
                "checkPassword",
                new Class[] {String.class},
                Boolean.TRUE);
        
        HashMap l_map = new HashMap();
        l_map.put(WEB3LoginTypeAttributeKeyDef.TRADING_PWD_ENV, WEB3TradingPwdEnvDef.DEFAULT);
        TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                "com.fitechlabs.xtrade.plugin.security.oplogin.impl.OpLoginAdminServiceImpl",
                "getLoginTypeAttributes",
                new Class[] {long.class},
                l_map);

         TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
            "com.fitechlabs.xtrade.plugin.security.oplogin.impl.LoginInfoImpl",
            "getLoginId",
            new Class[] {},
            new Long(3338111123L));
        
        SubAccountParams l_subAccountParams =
            TestDBUtility.getSubAccountRow();
        l_subAccountParams.setInstitutionCode("6D");
        l_subAccountParams.setInstitutionId(63);
        l_subAccountParams.setSubAccountId(333812512203L);
        l_subAccountParams.setSubAccountType(SubAccountTypeEnum.EQUITY_SUB_ACCOUNT);

        AdministratorParams l_administratorParams = TestDBUtility.getAdministratorRow();
        l_administratorParams.setLoginId(3338111123L);
        l_administratorParams.setAdministratorId(33333333333L);
        
        AdminPermissionParams l_adminPermissionParams = TestDBUtility.getAdminPermissionRow();
        l_adminPermissionParams.setInstitutionCode("0D");
        l_adminPermissionParams.setPermissionLevel("331");
        l_adminPermissionParams.setTransactionCategory("C0108");
        l_adminPermissionParams.setUpdatePermissionFlag(BooleanEnum.TRUE);

        EqtypeOrderUnitParams l_eqOrderUnitParams =
            TestDBUtility.getEqtypeOrderUnitRow();
        l_eqOrderUnitParams.setOrderStatus(OrderStatusEnum.ACCEPTED);
        l_eqOrderUnitParams.setOrderId(111L);
        l_eqOrderUnitParams.setOrderOpenStatus(OrderOpenStatusEnum.OPEN);
        l_eqOrderUnitParams.setOrderUnitId(111L);
        l_eqOrderUnitParams.setAccountId(333812512203L);
        l_eqOrderUnitParams.setApproveStatusType("0");
        l_eqOrderUnitParams.setSubAccountId(333812512203L);
        l_eqOrderUnitParams.setBranchId(33381L);
        l_eqOrderUnitParams.setProductType(ProductTypeEnum.EQUITY);
        l_eqOrderUnitParams.setProductId(3304148080001L);
        l_eqOrderUnitParams.setMarketId(3303L);
        l_eqOrderUnitParams.setForcedSettleReasonType("0");
        l_eqOrderUnitParams.setProductId(3304148080001L);

//        ForcedSettleErrorOrderParams l_errorOrderParams =
//            TestDBUtility.getForcedSettleErrorOrderRow();
//        l_errorOrderParams.setApproveStatusType("0");
//        l_errorOrderParams.setForcedSettleReasonType("1");
//        l_errorOrderParams.setForcedSettleErrorOrderId(1001);
//        l_errorOrderParams.setAccountId(333812512203L);
//        l_errorOrderParams.setSubAccountId(333812512203L);
//        l_errorOrderParams.setBranchId(33381L);
//        l_errorOrderParams.setOrderType(OrderTypeEnum.CLOSE_MARGIN_LONG);
//        l_errorOrderParams.setOrderCateg(OrderCategEnum.CLOSE_MARGIN);
//        l_errorOrderParams.setProductId(3304148080001L);
//        l_errorOrderParams.setContractId(123L);
//        l_errorOrderParams.setContractType(1);

        EqtypeContractParams l_contractParams = TestDBUtility.getEqtypeContractRow();
        l_contractParams.setContractId(123L);
        l_contractParams.setAccountId(333812512203L);
        l_contractParams.setSubAccountId(333812512203L);
        l_contractParams.setMarketId(3303L);
        l_contractParams.setContractType(ContractTypeEnum.LONG);
        l_contractParams.setProductId(3304148080001L);
        l_contractParams.setProductType(ProductTypeEnum.EQUITY);
        l_contractParams.setTaxType(TaxTypeEnum.NORMAL);

        EqtypeClosingContractSpecParams l_contractSpecParams = TestDBUtility.getEqtypeClosingContractSpecRow();
        l_contractSpecParams.setContractId(123L);
        l_contractSpecParams.setOrderUnitId(111L);
        l_contractSpecParams.setOrderId(111L);
        l_contractSpecParams.setClosingContractSpecId(33445566778899L);
        l_contractSpecParams.setAccountId(333812512203L);
        l_contractSpecParams.setSubAccountId(333812512203L);

        MainAccountParams l_mainAccountParams =
            TestDBUtility.getMainAccountRow();
        l_mainAccountParams.setAccountId(333812512203L);
        l_mainAccountParams.setInstitutionId(63);

        InstitutionParams l_institutionParams =
            TestDBUtility.getInstitutionRow();
        l_institutionParams.setInstitutionCode("0D");
        l_institutionParams.setInstitutionId(63);

        EqtypeProductParams l_eqProductParams = TestDBUtility.getEqtypeProductRow();
        l_eqProductParams.setProductId(3304148080001L);
        l_eqProductParams.setInstitutionCode("0D");
        
        EqtypeTradedProductParams l_eqProductTradedParams = TestDBUtility.getEqtypeTradedProductRow();
        l_eqProductTradedParams.setProductId(3304148080001L);
        l_eqProductTradedParams.setMarketId(3303);
        l_eqProductTradedParams.setTradedProductId(1006160060005L);
        l_eqProductTradedParams.setValidUntilBizDate(null);
        
        TradedProductParams l_tradedProductParams = TestDBUtility.getTradedProductRow();
        l_tradedProductParams.setProductId(3304148080001L);
        l_tradedProductParams.setMarketId(3303);
        l_tradedProductParams.setTradedProductId(1006160060005L);
        l_tradedProductParams.setValidUntilBizDate(null);

        ProductParams l_productParams =
            TestDBUtility.getProductRow();
        l_productParams.setProductId(3304148080001L);
        l_productParams.setInstitutionCode("0D");
        l_productParams.setProductType(ProductTypeEnum.EQUITY);

        MarketParams l_marketParams =
            TestDBUtility.getMarketRow();
        l_marketParams.setMarketCode("N1");
        l_marketParams.setMarketId(3303L);
        l_marketParams.setInstitutionCode("0D");

        BranchParams l_branchParams =
            TestDBUtility.getBranchRow();
        l_branchParams.setInstitutionCode("0D");
        l_branchParams.setInstitutionId(63);

        TradingTimeParams l_tradingTimeParams = TestDBUtility.getTradingTimeRow();
        l_tradingTimeParams.setInstitutionCode("0D");
        l_tradingTimeParams.setBranchCode("381");
        l_tradingTimeParams.setTradingTimeType("01");
        l_tradingTimeParams.setBizDateType("1");
        l_tradingTimeParams.setMarketCode("N1");
        l_tradingTimeParams.setProductCode("0");
        
        DaemonTriggerParams l_daemonTriggerParams = new DaemonTriggerParams();
        l_daemonTriggerParams.setThreadNo(111L);
        l_daemonTriggerParams.setTriggerType("Z");
        l_daemonTriggerParams.setOrderRequestNumber("1");
        l_daemonTriggerParams.setRangeFrom(0);
        l_daemonTriggerParams.setRangeTo(6);
        l_daemonTriggerParams.setTriggerStatus(WEB3DaemonTriggerStatusDef.THREAD_PROCESSING);
        try
        {
            TestDBUtility.deleteAllAndCommit(SubAccountRow.TYPE);
            TestDBUtility.deleteAllAndCommit(MainAccountRow.TYPE);
            TestDBUtility.deleteAllAndCommit(InstitutionRow.TYPE);
            TestDBUtility.deleteAllAndCommit(ProductRow.TYPE);
            TestDBUtility.deleteAllAndCommit(TradedProductRow.TYPE);
            TestDBUtility.deleteAllAndCommit(MarketRow.TYPE);
            TestDBUtility.deleteAllAndCommit(BranchRow.TYPE);
            TestDBUtility.deleteAllAndCommit(TradingTimeRow.TYPE);
            TestDBUtility.deleteAllAndCommit(AdministratorRow.TYPE);
            TestDBUtility.deleteAllAndCommit(AdminPermissionRow.TYPE);
            TestDBUtility.deleteAllAndCommit(DaemonTriggerRow.TYPE);
            TestDBUtility.deleteAllAndCommit(EqtypeOrderUnitRow.TYPE);
//            TestDBUtility.deleteAllAndCommit(ForcedSettleErrorOrderRow.TYPE);
            TestDBUtility.deleteAllAndCommit(EqtypeContractRow.TYPE);
            TestDBUtility.deleteAllAndCommit(EqtypeClosingContractSpecRow.TYPE);
            TestDBUtility.deleteAllAndCommit(EqtypeProductRow.TYPE);
            TestDBUtility.deleteAllAndCommit(EqtypeTradedProductRow.TYPE);

            TestDBUtility.deleteAllAndCommit(DaemonTriggerRow.TYPE);
            TestDBUtility.insertWithDelAndCommit(l_daemonTriggerParams);
            
            TestDBUtility.insertWithDelAndCommit(l_subAccountParams);
            TestDBUtility.insertWithDelAndCommit(l_mainAccountParams);
            TestDBUtility.insertWithDelAndCommit(l_institutionParams);
            TestDBUtility.insertWithDelAndCommit(l_productParams);
            TestDBUtility.insertWithDelAndCommit(l_marketParams);
            TestDBUtility.insertWithDelAndCommit(l_branchParams);
            TestDBUtility.insertWithDelAndCommit(l_administratorParams);
            TestDBUtility.insertWithDelAndCommit(l_adminPermissionParams);
            TestDBUtility.insertWithDelAndCommit(l_daemonTriggerParams);
//            TestDBUtility.insertWithDelAndCommit(l_errorOrderParams);
            TestDBUtility.insertWithDelAndCommit(l_eqOrderUnitParams);
            TestDBUtility.insertWithDelAndCommit(l_tradingTimeParams);
            TestDBUtility.insertWithDelAndCommit(l_contractSpecParams);
            TestDBUtility.insertWithDelAndCommit(l_contractParams);
            TestDBUtility.insertWithDelAndCommit(l_eqProductParams);
            TestDBUtility.insertWithDelAndCommit(l_tradedProductParams);
            TestDBUtility.insertWithDelAndCommit(l_eqProductTradedParams);
        }
        catch (WEB3BaseException l_ex)
        {
            log.error(l_ex.getMessage(), l_ex);
            fail();
        } 
    }

    protected void tearDown() throws Exception
    {
        super.tearDown();
    }
    
    public class WEB3AdminForcedSettleApproveConfirmRequestForMock 
        extends WEB3AdminForcedSettleApproveConfirmRequest
    {
        public void validate() throws WEB3BaseException 
        {
            final String STR_METHOD_NAME = "WEB3AdminForcedSettleApproveConfirmRequestForMock + validate()";
            log.entering(STR_METHOD_NAME);
                      
            log.exiting(STR_METHOD_NAME);
        }
    }
    public class WEB3AdminForcedSettleApproveRunRequestForMock 
        extends WEB3AdminForcedSettleApproveRunRequest
    {
        public void validate() throws WEB3BaseException 
        {
            final String STR_METHOD_NAME = "WEB3AdminForcedSettleApproveRunRequestForMock + validate()";
            log.entering(STR_METHOD_NAME);
                      
            log.exiting(STR_METHOD_NAME);
        }
    }
    public class WEB3AdminForcedSettleApproveStatusRequestForMock 
        extends WEB3AdminForcedSettleApproveStatusRequest
    {
        public void validate() throws WEB3BaseException 
        {
            final String STR_METHOD_NAME = "WEB3AdminForcedSettleApproveStatusRequestForMock + validate()";
            log.entering(STR_METHOD_NAME);
                      
            log.exiting(STR_METHOD_NAME);
        }
    }

   private class WEB3AdminEquityForcedSettleOrderApproveServiceImplForTest
       extends WEB3AdminEquityForcedSettleOrderApproveServiceImpl
   {
       public ServerAccessor getServerAccessor() throws WEB3BaseException
       {
           final String STR_METHOD_NAME = "getServerAccessor()";
           log.entering(STR_METHOD_NAME);
           
           ServerAccessorImplForMock l_serverAccessorImpl =
               new ServerAccessorImplForMock();
           l_serverAccessorImpl.setValue("1", null);
           log.exiting(STR_METHOD_NAME);
           return l_serverAccessorImpl;
       }

   }

    /*
     * Test method for 'webbroker3.eqtypeadmin.service.delegate.stdimpls.WEB3AdminEquityForcedSettleOrderApproveServiceImpl.execute(WEB3GenRequest)'
     */
//    public void testExecuteC1()
//    {
//        final String STR_METHOD_NAME = "testExecuteC1()";
//        log.entering(STR_METHOD_NAME);
////        
////        WEB3AdminEquityForcedSettleOrderApproveServiceImpl l_impl =
////            new WEB3AdminEquityForcedSettleOrderApproveServiceImpl();
////
////        try
////        {
////            l_impl.execute(null);
////        }
////        catch(WEB3SystemLayerException l_ex)
////        {
////            log.error(l_ex.getMessage(), l_ex);
////            assertEquals(WEB3ErrorCatalog.SYSTEM_ERROR_80017,l_ex.getErrorInfo());
////            log.info(STR_METHOD_NAME + ">>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>test pass !!");
////        }
////        catch (Exception l_ex)
////        {
////            log.error(ERROR + l_ex.getMessage(), l_ex);
////            log.exiting(STR_METHOD_NAME);
////            fail();
////        }
////        
//        log.exiting(STR_METHOD_NAME);
//    }
    
    public void testExecuteC2()
    {
        final String STR_METHOD_NAME = "testExecuteC2()";
        log.entering(STR_METHOD_NAME);
        
        WEB3AdminEquityForcedSettleOrderApproveServiceImpl l_impl =
            new WEB3AdminEquityForcedSettleOrderApproveServiceImpl();
        WEB3AdminForcedSettleApproveConfirmRequestForMock l_request =
            new WEB3AdminForcedSettleApproveConfirmRequestForMock();
        OnlineRunStatusParams l_runStatusParams = new OnlineRunStatusParams();
        l_runStatusParams.setInstitutionCode("0D");
        l_runStatusParams.setRunStatusDiv("5");
        l_runStatusParams.setFutureOptionDiv(WEB3FuturesOptionDivDef.DEFAULT);
        l_runStatusParams.setProductType(ProductTypeEnum.EQUITY);
        l_runStatusParams.setAccountIdFrom(1);
        l_runStatusParams.setAccountIdTo(6);
        l_runStatusParams.setOnlineServiceDiv("8");
        l_runStatusParams.setCreatedTimestamp(WEB3DateUtility.getDate("20070116","yyyyMMdd"));
        l_runStatusParams.setLastUpdatedTimestamp(WEB3DateUtility.getDate("20070116","yyyyMMdd"));
        try
        {
            TestDBUtility.deleteAllAndCommit(OnlineRunStatusRow.TYPE);
            TestDBUtility.insertWithDelAndCommit(l_runStatusParams);
        }
        catch (Exception l_ex)
        {
            log.error(l_ex.getMessage(), l_ex);
            fail();
        }
        try
        {
            WEB3GentradeTradingTimeManagementForMock.mockSetTimestamp();

            Date l_datDate =  WEB3DateUtility.getDate("20070116","yyyyMMdd");
            WEB3GentradeTradingTimeManagementForMock.mockGetOrderBizDate(l_datDate);

            String[] l_strIdList = new String[2];
            l_strIdList[0] = "111";
            l_strIdList[1] = "222";
            l_request.id = l_strIdList;
            l_request.approveType = WEB3AdminEquityApproveTypeDef.DISAPPROVE;
            l_impl.execute(l_request);
        }
        catch(WEB3SystemLayerException l_ex)
        {
            log.error(l_ex.getMessage(), l_ex);
            assertEquals(WEB3ErrorCatalog.SYSTEM_ERROR_80017,l_ex.getErrorInfo());
            log.info(STR_METHOD_NAME + ">>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>test pass !!");
        }
        catch (Exception l_ex)
        {
            log.error(ERROR + l_ex.getMessage(), l_ex);
            log.exiting(STR_METHOD_NAME);
            fail();
        }
        log.exiting(STR_METHOD_NAME);
    }
    
    public void testExecuteC3()
    {
        final String STR_METHOD_NAME = "testExecuteC3()";
        log.entering(STR_METHOD_NAME);
        
        WEB3AdminEquityForcedSettleOrderApproveServiceImpl l_impl =
            new WEB3AdminEquityForcedSettleOrderApproveServiceImpl();
        WEB3AdminForcedSettleApproveRunRequest l_request =
            new WEB3AdminForcedSettleApproveRunRequest();
        try
        {
            WEB3GentradeTradingTimeManagementForMock.mockSetTimestamp();

            Date l_datDate =  WEB3DateUtility.getDate("20070116","yyyyMMdd");
            WEB3GentradeTradingTimeManagementForMock.mockGetOrderBizDate(l_datDate);

            l_request.approveType = WEB3AdminEquityApproveTypeDef.DISAPPROVE;
            l_impl.execute(l_request);
        }
        catch(WEB3BaseException l_ex)
        {
            log.error(l_ex.getMessage(), l_ex);
            assertEquals(WEB3ErrorCatalog.BUSINESS_ERROR_00600,l_ex.getErrorInfo());
            log.info(STR_METHOD_NAME + ">>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>test pass !!");
        }
        catch (Exception l_ex)
        {
            log.error(ERROR + l_ex.getMessage(), l_ex);
            log.exiting(STR_METHOD_NAME);
            fail();
        }
        log.exiting(STR_METHOD_NAME);
    }
    
    public void testExecuteC4()
    {
        final String STR_METHOD_NAME = "testExecuteC4()";
        log.entering(STR_METHOD_NAME);
        
        WEB3AdminEquityForcedSettleOrderApproveServiceImpl l_impl =
            new WEB3AdminEquityForcedSettleOrderApproveServiceImpl();
        WEB3AdminForcedSettleApproveStatusRequestForMock l_request =
            new WEB3AdminForcedSettleApproveStatusRequestForMock();
        OnlineRunStatusParams l_runStatusParams = new OnlineRunStatusParams();
        l_runStatusParams.setInstitutionCode("0D");
        l_runStatusParams.setRunStatusDiv("5");
        l_runStatusParams.setFutureOptionDiv(WEB3FuturesOptionDivDef.DEFAULT);
        l_runStatusParams.setProductType(ProductTypeEnum.EQUITY);
        l_runStatusParams.setAccountIdFrom(1);
        l_runStatusParams.setAccountIdTo(6);
        l_runStatusParams.setOnlineServiceDiv("7");
        l_runStatusParams.setCreatedTimestamp(WEB3DateUtility.getDate("20070116","yyyyMMdd"));
        l_runStatusParams.setLastUpdatedTimestamp(WEB3DateUtility.getDate("20070116","yyyyMMdd"));
        
        OnlineRunStatusParams l_runStatusParams1 = new OnlineRunStatusParams();
        l_runStatusParams1.setInstitutionCode("0D");
        l_runStatusParams1.setRunStatusDiv("5");
        l_runStatusParams1.setFutureOptionDiv(WEB3FuturesOptionDivDef.DEFAULT);
        l_runStatusParams1.setProductType(ProductTypeEnum.EQUITY);
        l_runStatusParams1.setAccountIdFrom(0);
        l_runStatusParams1.setAccountIdTo(5);
        l_runStatusParams1.setOnlineServiceDiv("7");
        l_runStatusParams1.setCreatedTimestamp(WEB3DateUtility.getDate("20070119","yyyyMMdd"));
        l_runStatusParams1.setLastUpdatedTimestamp(WEB3DateUtility.getDate("20070119","yyyyMMdd"));
        try
        {
            TestDBUtility.deleteAllAndCommit(OnlineRunStatusRow.TYPE);
            TestDBUtility.insertWithDelAndCommit(l_runStatusParams);
            TestDBUtility.insertWithDelAndCommit(l_runStatusParams1);
        }
        catch (Exception l_ex)
        {
            log.error(l_ex.getMessage(), l_ex);
            fail();
        }
        try
        {
            WEB3GentradeTradingTimeManagementForMock.mockSetTimestamp();

            Date l_datDate =  WEB3DateUtility.getDate("20070116","yyyyMMdd");
            WEB3GentradeTradingTimeManagementForMock.mockGetOrderBizDate(l_datDate);
            l_request.approveType = WEB3AdminEquityApproveTypeDef.DISAPPROVE;
            l_impl.execute(l_request);
        }
        catch(WEB3SystemLayerException l_ex)
        {
            log.error(l_ex.getMessage(), l_ex);
            assertEquals(WEB3ErrorCatalog.SYSTEM_ERROR_80017,l_ex.getErrorInfo());
            log.info(STR_METHOD_NAME + ">>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>test pass !!");
        }
        catch (Exception l_ex)
        {
            log.error(ERROR + l_ex.getMessage(), l_ex);
            log.exiting(STR_METHOD_NAME);
            fail();
        }
        log.exiting(STR_METHOD_NAME);
    }


    public void testExecuteC5()
    {
        final String STR_METHOD_NAME = "testExecuteC5()";
        log.entering(STR_METHOD_NAME);
        
        WEB3AdminEquityForcedSettleOrderApproveServiceImpl l_impl =
            new WEB3AdminEquityForcedSettleOrderApproveServiceImpl();
        WEB3AdminFrontNoticeHistoryReferenceRequest l_request =
            new WEB3AdminFrontNoticeHistoryReferenceRequest();
        try
        {
            l_impl.execute(l_request);
        }
        catch(WEB3SystemLayerException l_ex)
        {
            log.error(l_ex.getMessage(), l_ex);
            assertEquals(WEB3ErrorCatalog.SYSTEM_ERROR_80018,l_ex.getErrorInfo());
            log.info(STR_METHOD_NAME + ">>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>test pass !!");
        }
        catch (Exception l_ex)
        {
            log.error(ERROR + l_ex.getMessage(), l_ex);
            log.exiting(STR_METHOD_NAME);
            fail();
        }
        log.exiting(STR_METHOD_NAME);
    }
    
    /*
     * Test method for 'webbroker3.eqtypeadmin.service.delegate.stdimpls.WEB3AdminEquityForcedSettleOrderApproveServiceImpl.validateApprove(WEB3AdminForcedSettleApproveConfirmRequest)'
     */
    public void testValidateApproveC1()
    {
        final String STR_METHOD_NAME = "testValidateApproveC1()";
        log.entering(STR_METHOD_NAME);

        WEB3AdminEquityForcedSettleOrderApproveServiceImpl l_impl =
            new WEB3AdminEquityForcedSettleOrderApproveServiceImpl();

        WEB3AdminForcedSettleApproveConfirmRequestForMock l_request =
            new WEB3AdminForcedSettleApproveConfirmRequestForMock();

        EqtypeOrderUnitParams l_eqOrderUnitParams1 =
            TestDBUtility.getEqtypeOrderUnitRow();
        l_eqOrderUnitParams1.setOrderStatus(OrderStatusEnum.ACCEPTED);
        l_eqOrderUnitParams1.setOrderId(222L);
        l_eqOrderUnitParams1.setOrderOpenStatus(OrderOpenStatusEnum.OPEN);
        l_eqOrderUnitParams1.setOrderUnitId(222L);
        l_eqOrderUnitParams1.setAccountId(333812512203L);
        l_eqOrderUnitParams1.setApproveStatusType("0");
        l_eqOrderUnitParams1.setSubAccountId(333812512203L);
        l_eqOrderUnitParams1.setBranchId(33381L);
        l_eqOrderUnitParams1.setProductType(ProductTypeEnum.EQUITY);
        l_eqOrderUnitParams1.setProductId(3304148080001L);
        l_eqOrderUnitParams1.setMarketId(3303L);
        l_eqOrderUnitParams1.setForcedSettleReasonType("0");
        l_eqOrderUnitParams1.setProductId(3304148080001L);

//        ForcedSettleErrorOrderParams l_errorOrderParams1 =
//            TestDBUtility.getForcedSettleErrorOrderRow();
//        l_errorOrderParams1.setApproveStatusType("0");
//        l_errorOrderParams1.setForcedSettleReasonType("1");
//        l_errorOrderParams1.setForcedSettleErrorOrderId(1002);
//        l_errorOrderParams1.setAccountId(333812512203L);
//        l_errorOrderParams1.setSubAccountId(333812512203L);
//        l_errorOrderParams1.setBranchId(33381L);
//        l_errorOrderParams1.setOrderType(OrderTypeEnum.CLOSE_MARGIN_LONG);
//        l_errorOrderParams1.setOrderCateg(OrderCategEnum.CLOSE_MARGIN);
//        l_errorOrderParams1.setProductId(3304148080001L);
//        l_errorOrderParams1.setContractId(456L);
//        l_errorOrderParams1.setContractType(1);

        EqtypeContractParams l_contractParams1 = TestDBUtility.getEqtypeContractRow();
        l_contractParams1.setContractId(456L);
        l_contractParams1.setAccountId(333812512203L);
        l_contractParams1.setSubAccountId(333812512203L);
        l_contractParams1.setMarketId(3303L);
        l_contractParams1.setContractType(ContractTypeEnum.LONG);
        l_contractParams1.setProductId(3304148080001L);
        l_contractParams1.setProductType(ProductTypeEnum.EQUITY);
        l_contractParams1.setTaxType(TaxTypeEnum.NORMAL);

        EqtypeClosingContractSpecParams l_contractSpecParams1 = TestDBUtility.getEqtypeClosingContractSpecRow();
        l_contractSpecParams1.setContractId(456L);
        l_contractSpecParams1.setOrderUnitId(222L);
        l_contractSpecParams1.setOrderId(222L);
        l_contractSpecParams1.setClosingContractSpecId(1111111111L);
        l_contractSpecParams1.setAccountId(333812512203L);
        l_contractSpecParams1.setSubAccountId(333812512203L);
        
        EqtypeOrderUnitParams l_eqOrderUnitParams2 =
            TestDBUtility.getEqtypeOrderUnitRow();
        l_eqOrderUnitParams2.setOrderStatus(OrderStatusEnum.ACCEPTED);
        l_eqOrderUnitParams2.setOrderId(333L);
        l_eqOrderUnitParams2.setOrderOpenStatus(OrderOpenStatusEnum.OPEN);
        l_eqOrderUnitParams2.setOrderUnitId(333L);
        l_eqOrderUnitParams2.setAccountId(333812512203L);
        l_eqOrderUnitParams2.setApproveStatusType("0");
        l_eqOrderUnitParams2.setSubAccountId(333812512203L);
        l_eqOrderUnitParams2.setBranchId(33381L);
        l_eqOrderUnitParams2.setProductType(ProductTypeEnum.EQUITY);
        l_eqOrderUnitParams2.setProductId(3304148080001L);
        l_eqOrderUnitParams2.setMarketId(3303L);
        l_eqOrderUnitParams2.setForcedSettleReasonType("0");
        l_eqOrderUnitParams2.setProductId(3304148080001L);

//        ForcedSettleErrorOrderParams l_errorOrderParams2 =
//            TestDBUtility.getForcedSettleErrorOrderRow();
//        l_errorOrderParams2.setApproveStatusType("0");
//        l_errorOrderParams2.setForcedSettleReasonType("1");
//        l_errorOrderParams2.setForcedSettleErrorOrderId(1003);
//        l_errorOrderParams2.setAccountId(333812512203L);
//        l_errorOrderParams2.setSubAccountId(333812512203L);
//        l_errorOrderParams2.setBranchId(33381L);
//        l_errorOrderParams2.setOrderType(OrderTypeEnum.CLOSE_MARGIN_LONG);
//        l_errorOrderParams2.setOrderCateg(OrderCategEnum.CLOSE_MARGIN);
//        l_errorOrderParams2.setProductId(3304148080001L);
//        l_errorOrderParams2.setContractId(789L);
//        l_errorOrderParams2.setContractType(1);

        EqtypeContractParams l_contractParams2 = TestDBUtility.getEqtypeContractRow();
        l_contractParams2.setContractId(789L);
        l_contractParams2.setAccountId(333812512203L);
        l_contractParams2.setSubAccountId(333812512203L);
        l_contractParams2.setMarketId(3303L);
        l_contractParams2.setContractType(ContractTypeEnum.LONG);
        l_contractParams2.setProductId(3304148080001L);
        l_contractParams2.setProductType(ProductTypeEnum.EQUITY);
        l_contractParams2.setTaxType(TaxTypeEnum.NORMAL);

        EqtypeClosingContractSpecParams l_contractSpecParams2 = TestDBUtility.getEqtypeClosingContractSpecRow();
        l_contractSpecParams2.setContractId(789L);
        l_contractSpecParams2.setOrderUnitId(333L);
        l_contractSpecParams2.setOrderId(333L);
        l_contractSpecParams2.setClosingContractSpecId(222222222222L);
        l_contractSpecParams2.setAccountId(333812512203L);
        l_contractSpecParams2.setSubAccountId(333812512203L);
        
        OnlineRunStatusParams l_runStatusParams = new OnlineRunStatusParams();
        l_runStatusParams.setInstitutionCode("0D");
        l_runStatusParams.setRunStatusDiv("5");
        l_runStatusParams.setFutureOptionDiv(WEB3FuturesOptionDivDef.DEFAULT);
        l_runStatusParams.setProductType(ProductTypeEnum.EQUITY);
        l_runStatusParams.setAccountIdFrom(1);
        l_runStatusParams.setAccountIdTo(6);
        l_runStatusParams.setOnlineServiceDiv("8");
        l_runStatusParams.setCreatedTimestamp(WEB3DateUtility.getDate("20070116","yyyyMMdd"));
        l_runStatusParams.setLastUpdatedTimestamp(WEB3DateUtility.getDate("20070116","yyyyMMdd"));
        try
        {

//            TestDBUtility.insertWithDelAndCommit(l_errorOrderParams1);
            TestDBUtility.insertWithDelAndCommit(l_eqOrderUnitParams1);
            TestDBUtility.insertWithDelAndCommit(l_contractSpecParams1);
            TestDBUtility.insertWithDelAndCommit(l_contractParams1);
            TestDBUtility.deleteAllAndCommit(OnlineRunStatusRow.TYPE);
            
            TestDBUtility.insertWithDelAndCommit(l_runStatusParams);
//            TestDBUtility.insertWithDelAndCommit(l_errorOrderParams2);
            TestDBUtility.insertWithDelAndCommit(l_eqOrderUnitParams2);
            TestDBUtility.insertWithDelAndCommit(l_contractSpecParams2);
            TestDBUtility.insertWithDelAndCommit(l_contractParams2);
        }
        catch (WEB3BaseException l_ex)
        {
            log.error(l_ex.getMessage(), l_ex);
            fail();
        } 
        
        try
        {
             WEB3GentradeTradingTimeManagementForMock.mockSetTimestamp();

             Date l_datDate =  WEB3DateUtility.getDate("20070116","yyyyMMdd");
             WEB3GentradeTradingTimeManagementForMock.mockGetOrderBizDate(l_datDate);

            String[] l_strIdList = new String[3];
            l_strIdList[0] = "111";
            l_strIdList[1] = "222";
            l_strIdList[2] = "333";
            l_request.id = l_strIdList;
            l_request.approveType = WEB3AdminEquityApproveTypeDef.DISAPPROVE;

            WEB3AdminForcedSettleApproveConfirmResponse l_response =
                l_impl.validateApprove(l_request);
            int l_intLength = l_response.forcedSettleTemporaryOrderList.length;
            WEB3AdminForcedSettleTemporaryOrderUnit[] l_orderUnitList =
                new WEB3AdminForcedSettleTemporaryOrderUnit[l_intLength];
            l_orderUnitList = l_response.forcedSettleTemporaryOrderList;
            assertEquals(l_intLength, 3);
            assertEquals(l_orderUnitList[0].approveState, "0");
            assertEquals(l_orderUnitList[0].branchCode, "381");
            assertEquals(l_orderUnitList[0].id, "111");
            
        }
        catch (Exception l_ex)
        {
            log.error(ERROR + l_ex.getMessage(), l_ex);
            log.exiting(STR_METHOD_NAME);
            fail();
        }
        log.exiting(STR_METHOD_NAME);
    }
    
    //追加case
    public void testValidateApproveC4()
    {
        final String STR_METHOD_NAME = "testValidateApproveC4()";
        log.entering(STR_METHOD_NAME);

        WEB3AdminEquityForcedSettleOrderApproveServiceImpl l_impl =
            new WEB3AdminEquityForcedSettleOrderApproveServiceImpl();

        WEB3AdminForcedSettleApproveConfirmRequestForMock l_request =
            new WEB3AdminForcedSettleApproveConfirmRequestForMock();

        EqtypeOrderUnitParams l_eqOrderUnitParams1 =
            TestDBUtility.getEqtypeOrderUnitRow();
        l_eqOrderUnitParams1.setOrderStatus(OrderStatusEnum.ACCEPTED);
        l_eqOrderUnitParams1.setOrderId(222L);
        l_eqOrderUnitParams1.setOrderOpenStatus(OrderOpenStatusEnum.OPEN);
        l_eqOrderUnitParams1.setOrderUnitId(222L);
        l_eqOrderUnitParams1.setAccountId(333812512203L);
        l_eqOrderUnitParams1.setApproveStatusType("0");
        l_eqOrderUnitParams1.setSubAccountId(333812512203L);
        l_eqOrderUnitParams1.setBranchId(33381L);
        l_eqOrderUnitParams1.setProductType(ProductTypeEnum.EQUITY);
        l_eqOrderUnitParams1.setProductId(3304148080001L);
        l_eqOrderUnitParams1.setMarketId(3303L);
        l_eqOrderUnitParams1.setForcedSettleReasonType("0");
        l_eqOrderUnitParams1.setProductId(3304148080001L);

//        ForcedSettleErrorOrderParams l_errorOrderParams1 =
//            TestDBUtility.getForcedSettleErrorOrderRow();
//        l_errorOrderParams1.setApproveStatusType("0");
//        l_errorOrderParams1.setForcedSettleReasonType("1");
//        l_errorOrderParams1.setForcedSettleErrorOrderId(1002);
//        l_errorOrderParams1.setAccountId(333812512203L);
//        l_errorOrderParams1.setSubAccountId(333812512203L);
//        l_errorOrderParams1.setBranchId(33381L);
//        l_errorOrderParams1.setOrderType(OrderTypeEnum.CLOSE_MARGIN_LONG);
//        l_errorOrderParams1.setOrderCateg(OrderCategEnum.CLOSE_MARGIN);
//        l_errorOrderParams1.setProductId(3304148080001L);
//        l_errorOrderParams1.setContractId(456L);
//        l_errorOrderParams1.setContractType(1);

        EqtypeContractParams l_contractParams1 = TestDBUtility.getEqtypeContractRow();
        l_contractParams1.setContractId(456L);
        l_contractParams1.setAccountId(333812512203L);
        l_contractParams1.setSubAccountId(333812512203L);
        l_contractParams1.setMarketId(3303L);
        l_contractParams1.setContractType(ContractTypeEnum.LONG);
        l_contractParams1.setProductId(3304148080001L);
        l_contractParams1.setProductType(ProductTypeEnum.EQUITY);
        l_contractParams1.setTaxType(TaxTypeEnum.NORMAL);

        EqtypeClosingContractSpecParams l_contractSpecParams1 = TestDBUtility.getEqtypeClosingContractSpecRow();
        l_contractSpecParams1.setContractId(456L);
        l_contractSpecParams1.setOrderUnitId(222L);
        l_contractSpecParams1.setOrderId(222L);
        l_contractSpecParams1.setClosingContractSpecId(1111111111L);
        l_contractSpecParams1.setAccountId(333812512203L);
        l_contractSpecParams1.setSubAccountId(333812512203L);
        
        EqtypeOrderUnitParams l_eqOrderUnitParams2 =
            TestDBUtility.getEqtypeOrderUnitRow();
        l_eqOrderUnitParams2.setOrderStatus(OrderStatusEnum.ACCEPTED);
        l_eqOrderUnitParams2.setOrderId(333L);
        l_eqOrderUnitParams2.setOrderOpenStatus(OrderOpenStatusEnum.OPEN);
        l_eqOrderUnitParams2.setOrderUnitId(333L);
        l_eqOrderUnitParams2.setAccountId(333812512203L);
        l_eqOrderUnitParams2.setApproveStatusType("0");
        l_eqOrderUnitParams2.setSubAccountId(333812512203L);
        l_eqOrderUnitParams2.setBranchId(33381L);
        l_eqOrderUnitParams2.setProductType(ProductTypeEnum.EQUITY);
        l_eqOrderUnitParams2.setProductId(3304148080001L);
        l_eqOrderUnitParams2.setMarketId(3303L);
        l_eqOrderUnitParams2.setForcedSettleReasonType("0");
        l_eqOrderUnitParams2.setProductId(3304148080001L);
        l_eqOrderUnitParams2.setBizDate("20070516");

//        ForcedSettleErrorOrderParams l_errorOrderParams2 =
//            TestDBUtility.getForcedSettleErrorOrderRow();
//        l_errorOrderParams2.setApproveStatusType("0");
//        l_errorOrderParams2.setForcedSettleReasonType("1");
//        l_errorOrderParams2.setForcedSettleErrorOrderId(1003);
//        l_errorOrderParams2.setAccountId(333812512203L);
//        l_errorOrderParams2.setSubAccountId(333812512203L);
//        l_errorOrderParams2.setBranchId(33381L);
//        l_errorOrderParams2.setOrderType(OrderTypeEnum.CLOSE_MARGIN_LONG);
//        l_errorOrderParams2.setOrderCateg(OrderCategEnum.CLOSE_MARGIN);
//        l_errorOrderParams2.setProductId(3304148080001L);
//        l_errorOrderParams2.setContractId(789L);
//        l_errorOrderParams2.setContractType(1);

        EqtypeContractParams l_contractParams2 = TestDBUtility.getEqtypeContractRow();
        l_contractParams2.setContractId(789L);
        l_contractParams2.setAccountId(333812512203L);
        l_contractParams2.setSubAccountId(333812512203L);
        l_contractParams2.setMarketId(3303L);
        l_contractParams2.setContractType(ContractTypeEnum.LONG);
        l_contractParams2.setProductId(3304148080001L);
        l_contractParams2.setProductType(ProductTypeEnum.EQUITY);
        l_contractParams2.setTaxType(TaxTypeEnum.NORMAL);

        EqtypeClosingContractSpecParams l_contractSpecParams2 = TestDBUtility.getEqtypeClosingContractSpecRow();
        l_contractSpecParams2.setContractId(789L);
        l_contractSpecParams2.setOrderUnitId(333L);
        l_contractSpecParams2.setOrderId(333L);
        l_contractSpecParams2.setClosingContractSpecId(222222222222L);
        l_contractSpecParams2.setAccountId(333812512203L);
        l_contractSpecParams2.setSubAccountId(333812512203L);
        
        OnlineRunStatusParams l_runStatusParams = new OnlineRunStatusParams();
        l_runStatusParams.setInstitutionCode("0D");
        l_runStatusParams.setRunStatusDiv("5");
        l_runStatusParams.setFutureOptionDiv(WEB3FuturesOptionDivDef.DEFAULT);
        l_runStatusParams.setProductType(ProductTypeEnum.EQUITY);
        l_runStatusParams.setAccountIdFrom(1);
        l_runStatusParams.setAccountIdTo(6);
        l_runStatusParams.setOnlineServiceDiv("8");
        l_runStatusParams.setCreatedTimestamp(WEB3DateUtility.getDate("20070116","yyyyMMdd"));
        l_runStatusParams.setLastUpdatedTimestamp(WEB3DateUtility.getDate("20070116","yyyyMMdd"));
        try
        {

//            TestDBUtility.insertWithDelAndCommit(l_errorOrderParams1);
            TestDBUtility.insertWithDelAndCommit(l_eqOrderUnitParams1);
            TestDBUtility.insertWithDelAndCommit(l_contractSpecParams1);
            TestDBUtility.insertWithDelAndCommit(l_contractParams1);
            TestDBUtility.deleteAllAndCommit(OnlineRunStatusRow.TYPE);
            
            TestDBUtility.insertWithDelAndCommit(l_runStatusParams);
//            TestDBUtility.insertWithDelAndCommit(l_errorOrderParams2);
            TestDBUtility.insertWithDelAndCommit(l_eqOrderUnitParams2);
            TestDBUtility.insertWithDelAndCommit(l_contractSpecParams2);
            TestDBUtility.insertWithDelAndCommit(l_contractParams2);
        }
        catch (WEB3BaseException l_ex)
        {
            log.error(l_ex.getMessage(), l_ex);
            fail();
        } 
        
        try
        {
             WEB3GentradeTradingTimeManagementForMock.mockSetTimestamp();

             Date l_datDate =  WEB3DateUtility.getDate("20070116","yyyyMMdd");
             WEB3GentradeTradingTimeManagementForMock.mockGetOrderBizDate(l_datDate);

            String[] l_strIdList = new String[3];
            l_strIdList[0] = "111";
            l_strIdList[1] = "222";
            l_strIdList[2] = "333";
            l_request.id = l_strIdList;
            l_request.approveType = WEB3AdminEquityApproveTypeDef.DISAPPROVE;

            WEB3AdminForcedSettleApproveConfirmResponse l_response =
                l_impl.validateApprove(l_request);
            int l_intLength = l_response.forcedSettleTemporaryOrderList.length;
            WEB3AdminForcedSettleTemporaryOrderUnit[] l_orderUnitList =
                new WEB3AdminForcedSettleTemporaryOrderUnit[l_intLength];
            l_orderUnitList = l_response.forcedSettleTemporaryOrderList;
            assertEquals(l_intLength, 2);
            assertEquals(l_orderUnitList[0].approveState, "0");
            assertEquals(l_orderUnitList[0].branchCode, "381");
            assertEquals(l_orderUnitList[0].id, "111");
            
        }
        catch (Exception l_ex)
        {
            log.error(ERROR + l_ex.getMessage(), l_ex);
            log.exiting(STR_METHOD_NAME);
            fail();
        }
        log.exiting(STR_METHOD_NAME);
    }
    
    
    //get強制決済注文一覧(String[], 強制決済ソートキー[], Long, Long)返回null
    public void testValidateApproveC2()
    {
        final String STR_METHOD_NAME = "testValidateApproveC2()";
        log.entering(STR_METHOD_NAME);

        WEB3AdminEquityForcedSettleOrderApproveServiceImpl l_impl =
            new WEB3AdminEquityForcedSettleOrderApproveServiceImpl();

        WEB3AdminForcedSettleApproveConfirmRequestForMock l_request =
            new WEB3AdminForcedSettleApproveConfirmRequestForMock();

        EqtypeOrderUnitParams l_eqOrderUnitParams =
            TestDBUtility.getEqtypeOrderUnitRow();
        OnlineRunStatusParams l_runStatusParams = new OnlineRunStatusParams();
        l_runStatusParams.setInstitutionCode("0D");
        l_runStatusParams.setRunStatusDiv("5");
        l_runStatusParams.setFutureOptionDiv(WEB3FuturesOptionDivDef.DEFAULT);
        l_runStatusParams.setProductType(ProductTypeEnum.EQUITY);
        l_runStatusParams.setAccountIdFrom(1);
        l_runStatusParams.setAccountIdTo(6);
        l_runStatusParams.setOnlineServiceDiv("8");
        l_runStatusParams.setCreatedTimestamp(WEB3DateUtility.getDate("20070116","yyyyMMdd"));
        l_runStatusParams.setLastUpdatedTimestamp(WEB3DateUtility.getDate("20070116","yyyyMMdd"));
        try
        {
            TestDBUtility.deleteAllAndCommit(EqtypeOrderUnitRow.TYPE);
            TestDBUtility.deleteAllAndCommit(OnlineRunStatusRow.TYPE);
            
            TestDBUtility.insertWithDelAndCommit(l_runStatusParams);
            TestDBUtility.insertWithDelAndCommit(l_eqOrderUnitParams);
        }
        catch (WEB3BaseException l_ex)
        {
            log.error(l_ex.getMessage(), l_ex);
            fail();
        }
        try
        {
             WEB3GentradeTradingTimeManagementForMock.mockSetTimestamp();

             Date l_datDate =  WEB3DateUtility.getDate("20070116","yyyyMMdd");
             WEB3GentradeTradingTimeManagementForMock.mockGetOrderBizDate(l_datDate);

            String[] l_strIdList = new String[2];
            l_strIdList[0] = "111";
            l_strIdList[1] = "222";
            l_request.id = l_strIdList;
            l_request.approveType = WEB3AdminEquityApproveTypeDef.DISAPPROVE;

            WEB3AdminForcedSettleApproveConfirmResponse l_response =
                l_impl.validateApprove(l_request);
        }
        catch(WEB3BaseException l_ex)
        {
            log.error(l_ex.getMessage(), l_ex);
            assertEquals(WEB3ErrorCatalog.BUSINESS_ERROR_02086,l_ex.getErrorInfo());
            log.info(STR_METHOD_NAME + ">>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>test pass !!");
        }
        catch (Exception l_ex)
        {
            log.error(ERROR + l_ex.getMessage(), l_ex);
            log.exiting(STR_METHOD_NAME);
            fail();
        }
        log.exiting(STR_METHOD_NAME);
    }

    //is承認／非承認処理対象注文()の戻り値 == falseの場合
    public void testValidateApproveC3()
    {
        final String STR_METHOD_NAME = "testValidateApproveC3()";
        log.entering(STR_METHOD_NAME);

        WEB3AdminEquityForcedSettleOrderApproveServiceImpl l_impl =
            new WEB3AdminEquityForcedSettleOrderApproveServiceImpl();

        WEB3AdminForcedSettleApproveConfirmRequestForMock l_request =
            new WEB3AdminForcedSettleApproveConfirmRequestForMock();
        OnlineRunStatusParams l_runStatusParams = new OnlineRunStatusParams();
        l_runStatusParams.setInstitutionCode("0D");
        l_runStatusParams.setRunStatusDiv("5");
        l_runStatusParams.setFutureOptionDiv(WEB3FuturesOptionDivDef.DEFAULT);
        l_runStatusParams.setProductType(ProductTypeEnum.EQUITY);
        l_runStatusParams.setAccountIdFrom(1);
        l_runStatusParams.setAccountIdTo(6);
        l_runStatusParams.setOnlineServiceDiv("8");
        l_runStatusParams.setCreatedTimestamp(WEB3DateUtility.getDate("20070116","yyyyMMdd"));
        l_runStatusParams.setLastUpdatedTimestamp(WEB3DateUtility.getDate("20070116","yyyyMMdd"));
        try
        {
            TestDBUtility.deleteAllAndCommit(OnlineRunStatusRow.TYPE);
            
            TestDBUtility.insertWithDelAndCommit(l_runStatusParams);
        }
        catch (WEB3BaseException l_ex)
        {
            log.error(l_ex.getMessage(), l_ex);
            fail();
        }
        try
        {
            String[] l_strIdList = new String[2];
            l_strIdList[0] = "111";
            l_strIdList[1] = "222";
            l_request.id = l_strIdList;
            l_request.approveType = WEB3AdminEquityApproveTypeDef.DISAPPROVE;

            l_impl.validateApprove(l_request);
        }
        catch(WEB3BaseException l_ex)
        {
            log.error(l_ex.getMessage(), l_ex);
            assertEquals(WEB3ErrorCatalog.BUSINESS_ERROR_02086,l_ex.getErrorInfo());
            log.info(STR_METHOD_NAME + ">>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>test pass !!");
        }
        catch (Exception l_ex)
        {
            log.error(ERROR + l_ex.getMessage(), l_ex);
            log.exiting(STR_METHOD_NAME);
            fail();
        }
        log.exiting(STR_METHOD_NAME);
    }
 
    /*
     * Test method for 'webbroker3.eqtypeadmin.service.delegate.stdimpls.WEB3AdminEquityForcedSettleOrderApproveServiceImpl.runApprove(WEB3AdminForcedSettleApproveRunRequest)'
     */
    public void testRunApproveC1()
    {
        final String STR_METHOD_NAME = "testRunApproveC1()";
        log.entering(STR_METHOD_NAME);
        
        ServerConfigParams l_serverConfigParams = new ServerConfigParams();
        l_serverConfigParams.setConfigCateg("cluster.urls");
        l_serverConfigParams.setConfigName("01");
        l_serverConfigParams.setConfigTitle("developer");
        l_serverConfigParams.setConfigValue("sockpool://localhost:3001");
        
        OnlineRunStatusParams l_runStatusParams = new OnlineRunStatusParams();
        l_runStatusParams.setInstitutionCode("0D");
        l_runStatusParams.setRunStatusDiv("5");
        l_runStatusParams.setFutureOptionDiv(WEB3FuturesOptionDivDef.DEFAULT);
        l_runStatusParams.setProductType(ProductTypeEnum.EQUITY);
        l_runStatusParams.setAccountIdFrom(1);
        l_runStatusParams.setAccountIdTo(6);
        l_runStatusParams.setOnlineServiceDiv("8");
        l_runStatusParams.setCreatedTimestamp(WEB3DateUtility.getDate("20070116","yyyyMMdd"));
        l_runStatusParams.setLastUpdatedTimestamp(WEB3DateUtility.getDate("20070116","yyyyMMdd"));
        try
        {
            TestDBUtility.deleteAllAndCommit(OnlineRunStatusRow.TYPE);
            TestDBUtility.insertWithDelAndCommit(l_runStatusParams);
            
            TestDBUtility.deleteAllAndCommit(ServerConfigRow.TYPE);
            TestDBUtility.insertWithDelAndCommit(l_serverConfigParams);
        }
        catch (Exception l_ex)
        {
            log.error(l_ex.getMessage(), l_ex);
            fail();
        }
        try
        {
            WEB3AdminEquityForcedSettleOrderApproveServiceImplForTest l_impl =
                new WEB3AdminEquityForcedSettleOrderApproveServiceImplForTest();
            WEB3AdminForcedSettleApproveRunRequestForMock l_request =
                new WEB3AdminForcedSettleApproveRunRequestForMock();
            String[] l_strIdList = new String[2];
            l_strIdList[0] = "111";
            l_strIdList[1] = "222";
            l_request.id = l_strIdList;
            l_request.approveType = WEB3AdminEquityApproveTypeDef.DISAPPROVE;
            l_request.password = "1";
            l_impl.runApprove(l_request);

        }
        catch (ServerAccessorImplForMockException l_ex)
        {
            WEB3AdminEquityForcedSettleOrderApproveMainRequest l_mainRequest =
                (WEB3AdminEquityForcedSettleOrderApproveMainRequest)l_ex.getObject();
            String[] l_strIdList1 = new String[2];
            l_strIdList1 = l_mainRequest.orderIdList;
            assertEquals(l_mainRequest.institutionCode, "0D");
            assertEquals(l_mainRequest.threadNo, "111");
            assertEquals(l_mainRequest.rangeFrom, "0");
            assertEquals(l_mainRequest.rangeTo, "6");
            assertEquals(l_mainRequest.approveType, "1");
            assertEquals(l_mainRequest.administratorId, "33333333333L");//變更是追加的
            assertEquals(l_strIdList1[0], "111");
            assertEquals(l_strIdList1[1], "222");
        }
        catch (Exception l_ex)
        {
            log.error(l_ex.getMessage(), l_ex);
            fail();
        }
        
        log.exiting(STR_METHOD_NAME);
    }
    
    public void testRunApproveC2()
    {
        final String STR_METHOD_NAME = "testRunApproveC2()";
        log.entering(STR_METHOD_NAME);
        
        ServerConfigParams l_serverConfigParams = new ServerConfigParams();
        l_serverConfigParams.setConfigCateg("cluster.urls");
        l_serverConfigParams.setConfigName("01");
        l_serverConfigParams.setConfigTitle("developer");
        l_serverConfigParams.setConfigValue("sockpool://localhost:3001");
        
        OnlineRunStatusParams l_runStatusParams = new OnlineRunStatusParams();
        l_runStatusParams.setInstitutionCode("0D");
        l_runStatusParams.setRunStatusDiv("5");
        l_runStatusParams.setFutureOptionDiv(WEB3FuturesOptionDivDef.DEFAULT);
        l_runStatusParams.setProductType(ProductTypeEnum.EQUITY);
        l_runStatusParams.setAccountIdFrom(1);
        l_runStatusParams.setAccountIdTo(6);
        l_runStatusParams.setOnlineServiceDiv("8");
        l_runStatusParams.setCreatedTimestamp(WEB3DateUtility.getDate("20070116","yyyyMMdd"));
        l_runStatusParams.setLastUpdatedTimestamp(WEB3DateUtility.getDate("20070116","yyyyMMdd"));
        try
        {
            TestDBUtility.deleteAllAndCommit(OnlineRunStatusRow.TYPE);
            TestDBUtility.insertWithDelAndCommit(l_runStatusParams);
            
            TestDBUtility.deleteAllAndCommit(ServerConfigRow.TYPE);
            TestDBUtility.insertWithDelAndCommit(l_serverConfigParams);
        }
        catch (Exception l_ex)
        {
            log.error(l_ex.getMessage(), l_ex);
            fail();
        }
        try
        {
            WEB3AdminEquityForcedSettleOrderApproveServiceImplForTest l_impl =
                new WEB3AdminEquityForcedSettleOrderApproveServiceImplForTest();
            WEB3AdminForcedSettleApproveRunRequestForMock l_request =
                new WEB3AdminForcedSettleApproveRunRequestForMock();
            String[] l_strIdList = new String[2];
            l_strIdList[0] = "111";
            l_strIdList[1] = "222";
            l_request.id = l_strIdList;
            l_request.approveType = WEB3AdminEquityApproveTypeDef.DISAPPROVE;
            l_request.password = "1";
            WEB3AdminForcedSettleApproveRunResponse l_response = l_impl.runApprove(l_request);
            assertEquals(WEB3DateUtility.formatDate(l_response.currentTime,"yyyyMMdd"),
                WEB3DateUtility.formatDate(GtlUtils.getSystemTimestamp(), "yyyyMMdd"));
        }
        catch (Exception l_ex)
        {
            log.error(l_ex.getMessage(), l_ex);
            fail();
        }
        
        log.exiting(STR_METHOD_NAME);
    }
    
    //Test モデル212
    public void testRunApproveC3()
    {
        final String STR_METHOD_NAME = "testRunApproveC3()";
        log.entering(STR_METHOD_NAME);
        
        ServerConfigParams l_serverConfigParams = new ServerConfigParams();
        l_serverConfigParams.setConfigCateg("cluster.urls");
        l_serverConfigParams.setConfigName("01");
        l_serverConfigParams.setConfigTitle("developer");
        l_serverConfigParams.setConfigValue("sockpool://localhost:3001");
        
        OnlineRunStatusParams l_runStatusParams = new OnlineRunStatusParams();
        l_runStatusParams.setInstitutionCode("0D");
        l_runStatusParams.setRunStatusDiv("5");
        l_runStatusParams.setFutureOptionDiv(WEB3FuturesOptionDivDef.DEFAULT);
        l_runStatusParams.setProductType(ProductTypeEnum.EQUITY);
        l_runStatusParams.setAccountIdFrom(1);
        l_runStatusParams.setAccountIdTo(6);
        l_runStatusParams.setOnlineServiceDiv("8");
        l_runStatusParams.setCreatedTimestamp(WEB3DateUtility.getDate("20070116","yyyyMMdd"));
        l_runStatusParams.setLastUpdatedTimestamp(WEB3DateUtility.getDate("20070116","yyyyMMdd"));
        try
        {
            TestDBUtility.deleteAllAndCommit(OnlineRunStatusRow.TYPE);
            TestDBUtility.insertWithDelAndCommit(l_runStatusParams);

            TestDBUtility.deleteAllAndCommit(ServerConfigRow.TYPE);
            TestDBUtility.insertWithDelAndCommit(l_serverConfigParams);

            WEB3AdminEquityForcedSettleOrderApproveServiceImpl l_impl =
                new WEB3AdminEquityForcedSettleOrderApproveServiceImplForTest1();
            WEB3AdminForcedSettleApproveRunRequestForMock l_request =
                new WEB3AdminForcedSettleApproveRunRequestForMock();
            String[] l_strIdList = new String[2];
            l_strIdList[0] = "111";
            l_strIdList[1] = "222";
            l_request.id = l_strIdList;
            l_request.approveType = WEB3AdminEquityApproveTypeDef.DISAPPROVE;
            l_request.password = "1";
            l_impl.runApprove(l_request);
        }
        catch (ServerAccessorImplForMockException l_ex)
        {
        	WEB3AffinityDescendRequest l_request =
                (WEB3AffinityDescendRequest)l_ex.getObject();
            assertEquals(l_request.account_id_range, "0,999999999");
            assertTrue(l_request.request instanceof WEB3AdminEquityForcedSettleOrderApproveMainRequest[]);
        }
        catch (Exception l_ex)
        {
            log.error(l_ex.getMessage(), l_ex);
            fail();
        }        
        log.exiting(STR_METHOD_NAME);
    }

    private class WEB3AdminEquityForcedSettleOrderApproveServiceImplForTest1 extends WEB3AdminEquityForcedSettleOrderApproveServiceImpl
    {
    	 protected void validateApprovePossibility(WEB3Administrator l_admin, WEB3GenRequest l_request)
         throws WEB3BaseException
         {
         }

    	 protected void deleteOnlineRunResult(String l_strInstitutionCode, String l_strApproveType)
         throws WEB3BaseException
         {
         }

    	 protected List getDaemonTriggerUnits(String l_strApproveType) throws WEB3BaseException
    	 {
    		 List l_lisDaemonTriggerUnits = new ArrayList();
    		 DaemonTriggerParams l_triggerParams = TestDBUtility.getDaemonTriggerRow();
    		 l_triggerParams.setTriggerType("thread_no asc");
    		 l_lisDaemonTriggerUnits.add(l_triggerParams);
			return l_lisDaemonTriggerUnits; 
    	 }
    	 
    	 public ServerAccessor getServerAccessor() throws WEB3BaseException
         {
             final String STR_METHOD_NAME = "getServerAccessor()";
             log.entering(STR_METHOD_NAME);
             
             ServerAccessorImplForMock l_serverAccessorImpl =
                 new ServerAccessorImplForMock();
              //l_serverAccessorImpl.setValue("1", null);
             log.exiting(STR_METHOD_NAME);
             return l_serverAccessorImpl;
         }
    	 
    	 protected void updateAPCalling(long l_lngThreadNo) throws WEB3BaseException
    	 {
    	 }
    	 
    }

    /*
     * Test method for 'webbroker3.eqtypeadmin.service.delegate.stdimpls.WEB3AdminEquityForcedSettleOrderApproveServiceImpl.validateStatus(WEB3AdminForcedSettleApproveStatusRequest)'
     */
    // ・オンライン実行結果レコードが取得できなかった場合
    public void testValidateStatusC1()
    {
        final String STR_METHOD_NAME = "testValidateStatusC1()";
        log.entering(STR_METHOD_NAME);
        try
        {
            WEB3AdminForcedSettleApproveStatusRequestForMock l_request =
                new WEB3AdminForcedSettleApproveStatusRequestForMock();
            
          WEB3AdminEquityForcedSettleOrderApproveServiceImpl l_impl =
          new WEB3AdminEquityForcedSettleOrderApproveServiceImpl();
          l_request.approveType = WEB3AdminEquityApproveTypeDef.DISAPPROVE;
          WEB3AdminForcedSettleApproveStatusResponse l_response =
              l_impl.validateStatus(l_request);
          assertEquals(l_response.lapseStatus, WEB3RunStatusDivDef.DEALING);
        }
      catch (Exception l_ex)
      {
          log.error(l_ex.getMessage(), l_ex);
          fail();
      }
        log.exiting(STR_METHOD_NAME);

    }
    
    //　@・取得したデーモントリガーレコードの件数と、オンライン実行結果レコードの件数が異なる場合
    public void testValidateStatusC2()
    {
        final String STR_METHOD_NAME = "testValidateStatusC2()";
        log.entering(STR_METHOD_NAME);
        OnlineRunStatusParams l_runStatusParams = new OnlineRunStatusParams();
        l_runStatusParams.setInstitutionCode("0D");
        l_runStatusParams.setRunStatusDiv("5");
        l_runStatusParams.setFutureOptionDiv(WEB3FuturesOptionDivDef.DEFAULT);
        l_runStatusParams.setProductType(ProductTypeEnum.EQUITY);
        l_runStatusParams.setAccountIdFrom(1);
        l_runStatusParams.setAccountIdTo(6);
        l_runStatusParams.setOnlineServiceDiv("7");
        l_runStatusParams.setCreatedTimestamp(WEB3DateUtility.getDate("20070116","yyyyMMdd"));
        l_runStatusParams.setLastUpdatedTimestamp(WEB3DateUtility.getDate("20070116","yyyyMMdd"));
        
        OnlineRunStatusParams l_runStatusParams1 = new OnlineRunStatusParams();
        l_runStatusParams1.setInstitutionCode("0D");
        l_runStatusParams1.setRunStatusDiv("5");
        l_runStatusParams1.setFutureOptionDiv(WEB3FuturesOptionDivDef.DEFAULT);
        l_runStatusParams1.setProductType(ProductTypeEnum.EQUITY);
        l_runStatusParams1.setAccountIdFrom(0);
        l_runStatusParams1.setAccountIdTo(5);
        l_runStatusParams1.setOnlineServiceDiv("7");
        l_runStatusParams1.setCreatedTimestamp(WEB3DateUtility.getDate("20070119","yyyyMMdd"));
        l_runStatusParams1.setLastUpdatedTimestamp(WEB3DateUtility.getDate("20070119","yyyyMMdd"));
        try
        {
            TestDBUtility.deleteAllAndCommit(OnlineRunStatusRow.TYPE);
            TestDBUtility.insertWithDelAndCommit(l_runStatusParams);
            TestDBUtility.insertWithDelAndCommit(l_runStatusParams1);
        }
        catch (Exception l_ex)
        {
            log.error(l_ex.getMessage(), l_ex);
            fail();
        }
        try
        {
            WEB3AdminForcedSettleApproveStatusRequestForMock l_request =
                new WEB3AdminForcedSettleApproveStatusRequestForMock();
            
          WEB3AdminEquityForcedSettleOrderApproveServiceImpl l_impl =
          new WEB3AdminEquityForcedSettleOrderApproveServiceImpl();
          l_request.approveType = WEB3AdminEquityApproveTypeDef.DISAPPROVE;
          WEB3AdminForcedSettleApproveStatusResponse l_response =
              l_impl.validateStatus(l_request);
          assertEquals(l_response.lapseStatus, WEB3RunStatusDivDef.DEALING);
        }
      catch (Exception l_ex)
      {
          log.error(l_ex.getMessage(), l_ex);
          fail();
      }
        log.exiting(STR_METHOD_NAME);

    }
    
    // 　@　@・取得したデーモントリガーレコード.処理状態に"処理中"が1件でも存在する場合
    public void testValidateStatusC3()
    {
        final String STR_METHOD_NAME = "testValidateStatusC3()";
        log.entering(STR_METHOD_NAME);
        OnlineRunStatusParams l_runStatusParams = new OnlineRunStatusParams();
        l_runStatusParams.setInstitutionCode("0D");
        l_runStatusParams.setRunStatusDiv("0");
        l_runStatusParams.setFutureOptionDiv(WEB3FuturesOptionDivDef.DEFAULT);
        l_runStatusParams.setProductType(ProductTypeEnum.EQUITY);
        l_runStatusParams.setAccountIdFrom(1);
        l_runStatusParams.setAccountIdTo(6);
        l_runStatusParams.setOnlineServiceDiv("7");
        l_runStatusParams.setCreatedTimestamp(WEB3DateUtility.getDate("20070116","yyyyMMdd"));
        l_runStatusParams.setLastUpdatedTimestamp(WEB3DateUtility.getDate("20070116","yyyyMMdd"));
     
        DaemonTriggerParams l_daemonTriggerParams = new DaemonTriggerParams();
        l_daemonTriggerParams.setThreadNo(111L);
        l_daemonTriggerParams.setTriggerType("Z");
        l_daemonTriggerParams.setOrderRequestNumber("1");
        l_daemonTriggerParams.setRangeFrom(1);
        l_daemonTriggerParams.setRangeTo(5);
        l_daemonTriggerParams.setTriggerStatus(WEB3DaemonTriggerStatusDef.THREAD_PROCESSING);
        try
        {
            TestDBUtility.deleteAllAndCommit(OnlineRunStatusRow.TYPE);
            
            TestDBUtility.insertWithDelAndCommit(l_runStatusParams);

            TestDBUtility.deleteAllAndCommit(DaemonTriggerRow.TYPE);
            TestDBUtility.insertWithDelAndCommit(l_daemonTriggerParams);
        }
        catch (Exception l_ex)
        {
            log.error(l_ex.getMessage(), l_ex);
            fail();
        }
        try
        {
            WEB3AdminForcedSettleApproveStatusRequestForMock l_request =
                new WEB3AdminForcedSettleApproveStatusRequestForMock();
            
          WEB3AdminEquityForcedSettleOrderApproveServiceImpl l_impl =
          new WEB3AdminEquityForcedSettleOrderApproveServiceImpl();
          l_request.approveType = WEB3AdminEquityApproveTypeDef.DISAPPROVE;
          WEB3AdminForcedSettleApproveStatusResponse l_response =
              l_impl.validateStatus(l_request);
          assertEquals(l_response.lapseStatus, WEB3RunStatusDivDef.DEALING);
        }
      catch (Exception l_ex)
      {
          log.error(l_ex.getMessage(), l_ex);
          fail();
      }
        log.exiting(STR_METHOD_NAME);

    }
    
    // 　@　@・取得したオンライン実行結果レコード.実行ステータス区分に"処理中"が1件でも存在する場合
    public void testValidateStatusC4()
    {
        final String STR_METHOD_NAME = "testValidateStatusC4()";
        log.entering(STR_METHOD_NAME);
        OnlineRunStatusParams l_runStatusParams = new OnlineRunStatusParams();
        l_runStatusParams.setInstitutionCode("0D");
        l_runStatusParams.setRunStatusDiv("5");
        l_runStatusParams.setFutureOptionDiv(WEB3FuturesOptionDivDef.DEFAULT);
        l_runStatusParams.setProductType(ProductTypeEnum.EQUITY);
        l_runStatusParams.setAccountIdFrom(1);
        l_runStatusParams.setAccountIdTo(6);
        l_runStatusParams.setOnlineServiceDiv("7");
        l_runStatusParams.setCreatedTimestamp(WEB3DateUtility.getDate("20070116","yyyyMMdd"));
        l_runStatusParams.setLastUpdatedTimestamp(WEB3DateUtility.getDate("20070116","yyyyMMdd"));
     
        DaemonTriggerParams l_daemonTriggerParams = new DaemonTriggerParams();
        l_daemonTriggerParams.setThreadNo(111L);
        l_daemonTriggerParams.setTriggerType("Z");
        l_daemonTriggerParams.setOrderRequestNumber("1");
        l_daemonTriggerParams.setRangeFrom(1);
        l_daemonTriggerParams.setRangeTo(5);
        l_daemonTriggerParams.setTriggerStatus(WEB3DaemonTriggerStatusDef.THREAD_IDLE);
        try
        {
            TestDBUtility.deleteAllAndCommit(OnlineRunStatusRow.TYPE);
            
            TestDBUtility.insertWithDelAndCommit(l_runStatusParams);

            TestDBUtility.deleteAllAndCommit(DaemonTriggerRow.TYPE);
            TestDBUtility.insertWithDelAndCommit(l_daemonTriggerParams);
        }
        catch (Exception l_ex)
        {
            log.error(l_ex.getMessage(), l_ex);
            fail();
        }
        try
        {
            WEB3AdminForcedSettleApproveStatusRequestForMock l_request =
                new WEB3AdminForcedSettleApproveStatusRequestForMock();
            
          WEB3AdminEquityForcedSettleOrderApproveServiceImpl l_impl =
          new WEB3AdminEquityForcedSettleOrderApproveServiceImpl();
          l_request.approveType = WEB3AdminEquityApproveTypeDef.DISAPPROVE;
          WEB3AdminForcedSettleApproveStatusResponse l_response =
              l_impl.validateStatus(l_request);
          assertEquals(l_response.lapseStatus, WEB3RunStatusDivDef.DEALING);
        }
      catch (Exception l_ex)
      {
          log.error(l_ex.getMessage(), l_ex);
          fail();
      }
        log.exiting(STR_METHOD_NAME);

    }
    
    // 　@　@・取得した全てのデーモントリガーレコード.処理状態 == "未稼動"　@かつ
    //　@　@取得した全てのオンライン実行結果レコード.実行ステータス区分 == "処理済"
    public void testValidateStatusC5()
    {
        final String STR_METHOD_NAME = "testValidateStatusC5()";
        log.entering(STR_METHOD_NAME);
        OnlineRunStatusParams l_runStatusParams = new OnlineRunStatusParams();
        l_runStatusParams.setInstitutionCode("0D");
        l_runStatusParams.setRunStatusDiv("1");
        l_runStatusParams.setFutureOptionDiv(WEB3FuturesOptionDivDef.DEFAULT);
        l_runStatusParams.setProductType(ProductTypeEnum.EQUITY);
        l_runStatusParams.setAccountIdFrom(1);
        l_runStatusParams.setAccountIdTo(6);
        l_runStatusParams.setOnlineServiceDiv("7");
        l_runStatusParams.setCreatedTimestamp(WEB3DateUtility.getDate("20070116","yyyyMMdd"));
        l_runStatusParams.setLastUpdatedTimestamp(WEB3DateUtility.getDate("20070116","yyyyMMdd"));
     
        DaemonTriggerParams l_daemonTriggerParams = new DaemonTriggerParams();
        l_daemonTriggerParams.setThreadNo(111L);
        l_daemonTriggerParams.setTriggerType("Z");
        l_daemonTriggerParams.setOrderRequestNumber("1");
        l_daemonTriggerParams.setRangeFrom(1);
        l_daemonTriggerParams.setRangeTo(5);
        l_daemonTriggerParams.setTriggerStatus(WEB3DaemonTriggerStatusDef.THREAD_IDLE);
        try
        {
            TestDBUtility.deleteAllAndCommit(OnlineRunStatusRow.TYPE);
            
            TestDBUtility.insertWithDelAndCommit(l_runStatusParams);
         
            TestDBUtility.deleteAllAndCommit(DaemonTriggerRow.TYPE);
            TestDBUtility.insertWithDelAndCommit(l_daemonTriggerParams);
        }
        catch (Exception l_ex)
        {
            log.error(l_ex.getMessage(), l_ex);
            fail();
        }
        try
        {
            WEB3AdminForcedSettleApproveStatusRequestForMock l_request =
                new WEB3AdminForcedSettleApproveStatusRequestForMock();
            
          WEB3AdminEquityForcedSettleOrderApproveServiceImpl l_impl =
          new WEB3AdminEquityForcedSettleOrderApproveServiceImpl();
          l_request.approveType = WEB3AdminEquityApproveTypeDef.DISAPPROVE;
          WEB3AdminForcedSettleApproveStatusResponse l_response =
              l_impl.validateStatus(l_request);
          assertEquals(l_response.lapseStatus, WEB3RunStatusDivDef.DEALED);
        }
      catch (Exception l_ex)
      {
          log.error(l_ex.getMessage(), l_ex);
          fail();
      }
        log.exiting(STR_METHOD_NAME);

    }
    
    //③"エラー"をセットする条件①@、②以外の場合
    //デーモントリガーレコード
    //オンライン実行結果レコード都有兩條記? ，
    //其中：
    //デーモントリガーレコード.処理状態兩條都是 "未稼動"
    //オンライン実行結果レコード中一條是 "処理済"，一條是“未処理”    //
    public void testValidateStatusC6()
    {
        final String STR_METHOD_NAME = "testValidateStatusC6()";
        log.entering(STR_METHOD_NAME);
        OnlineRunStatusParams l_runStatusParams = new OnlineRunStatusParams();
        l_runStatusParams.setInstitutionCode("0D");
        l_runStatusParams.setRunStatusDiv("1");
        l_runStatusParams.setFutureOptionDiv(WEB3FuturesOptionDivDef.DEFAULT);
        l_runStatusParams.setProductType(ProductTypeEnum.EQUITY);
        l_runStatusParams.setAccountIdFrom(1);
        l_runStatusParams.setAccountIdTo(6);
        l_runStatusParams.setOnlineServiceDiv("7");
        l_runStatusParams.setCreatedTimestamp(WEB3DateUtility.getDate("20070116","yyyyMMdd"));
        l_runStatusParams.setLastUpdatedTimestamp(WEB3DateUtility.getDate("20070116","yyyyMMdd"));
        
        OnlineRunStatusParams l_runStatusParams1 = new OnlineRunStatusParams();
        l_runStatusParams1.setInstitutionCode("0D");
        l_runStatusParams1.setRunStatusDiv("0");
        l_runStatusParams1.setFutureOptionDiv(WEB3FuturesOptionDivDef.DEFAULT);
        l_runStatusParams1.setProductType(ProductTypeEnum.EQUITY);
        l_runStatusParams1.setAccountIdFrom(0);
        l_runStatusParams1.setAccountIdTo(5);
        l_runStatusParams1.setOnlineServiceDiv("7");
        l_runStatusParams1.setCreatedTimestamp(WEB3DateUtility.getDate("20070116","yyyyMMdd"));
        l_runStatusParams1.setLastUpdatedTimestamp(WEB3DateUtility.getDate("20070116","yyyyMMdd"));
     
        DaemonTriggerParams l_daemonTriggerParams = new DaemonTriggerParams();
        l_daemonTriggerParams.setThreadNo(111L);
        l_daemonTriggerParams.setTriggerType("Z");
        l_daemonTriggerParams.setOrderRequestNumber("1");
        l_daemonTriggerParams.setRangeFrom(0);
        l_daemonTriggerParams.setRangeTo(5);
        l_daemonTriggerParams.setTriggerStatus(WEB3DaemonTriggerStatusDef.THREAD_IDLE);
        
        DaemonTriggerParams l_daemonTriggerParams1 = new DaemonTriggerParams();
        l_daemonTriggerParams1.setThreadNo(123L);
        l_daemonTriggerParams1.setTriggerType("Z");
        l_daemonTriggerParams1.setOrderRequestNumber("1");
        l_daemonTriggerParams1.setRangeFrom(1);
        l_daemonTriggerParams1.setRangeTo(6);
        l_daemonTriggerParams1.setTriggerStatus(WEB3DaemonTriggerStatusDef.THREAD_IDLE);
        try
        {
            TestDBUtility.deleteAllAndCommit(OnlineRunStatusRow.TYPE);
            TestDBUtility.insertWithDelAndCommit(l_runStatusParams);
            TestDBUtility.insertWithDelAndCommit(l_runStatusParams1);
         
            TestDBUtility.deleteAllAndCommit(DaemonTriggerRow.TYPE);
            TestDBUtility.insertWithDelAndCommit(l_daemonTriggerParams);
            TestDBUtility.insertWithDelAndCommit(l_daemonTriggerParams1);
        }
        catch (Exception l_ex)
        {
            log.error(l_ex.getMessage(), l_ex);
            fail();
        }
        try
        {
            WEB3AdminForcedSettleApproveStatusRequestForMock l_request =
                new WEB3AdminForcedSettleApproveStatusRequestForMock();
            
          WEB3AdminEquityForcedSettleOrderApproveServiceImpl l_impl =
          new WEB3AdminEquityForcedSettleOrderApproveServiceImpl();
          l_request.approveType = WEB3AdminEquityApproveTypeDef.DISAPPROVE;
          WEB3AdminForcedSettleApproveStatusResponse l_response =
              l_impl.validateStatus(l_request);
          assertEquals(l_response.lapseStatus, WEB3RunStatusDivDef.ERROR);
        }
      catch (Exception l_ex)
      {
          log.error(l_ex.getMessage(), l_ex);
          fail();
      }
        log.exiting(STR_METHOD_NAME);

    }

    /*
     * Test method for 'webbroker3.eqtypeadmin.service.delegate.stdimpls.WEB3AdminEquityForcedSettleOrderApproveServiceImpl.validateApprovePossibility(WEB3Administrator, WEB3GenRequest)'
     */
    public void testValidateApprovePossibilityC1()
    {
        final String STR_METHOD_NAME = "testValidateApprovePossibilityC1()";
        log.entering(STR_METHOD_NAME);
        try
        {
            WEB3AdminEquityForcedSettleOrderApproveServiceImpl l_impl =
                new WEB3AdminEquityForcedSettleOrderApproveServiceImpl();
            //getInstanceFromログイン情報( )
            WEB3Administrator l_admin = WEB3Administrator.getInstanceFromLoginInfo();
            
            l_impl.validateApprovePossibility(l_admin, null);
            
        }
        catch(WEB3SystemLayerException l_ex)
        {
            log.error(l_ex.getMessage(), l_ex);
            assertEquals(WEB3ErrorCatalog.SYSTEM_ERROR_80017,l_ex.getErrorInfo());
            log.info(STR_METHOD_NAME + ">>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>test pass !!");
        }
        catch (Exception l_ex)
        {
            log.error(l_ex.getMessage(), l_ex);
            fail();
        }
      log.exiting(STR_METHOD_NAME);
    }
    
    //①@getオンライン実行結果一覧()の戻り値 ！= null
    // getオンライン実行結果一覧()の戻り値の件数 ！= getデーモントリガー一覧()の戻り値の件数
    public void testValidateApprovePossibilityC2()
    {
        final String STR_METHOD_NAME = "testValidateApprovePossibilityC2()";
        log.entering(STR_METHOD_NAME);
        OnlineRunStatusParams l_runStatusParams = new OnlineRunStatusParams();
        l_runStatusParams.setInstitutionCode("0D");
        l_runStatusParams.setRunStatusDiv("5");
        l_runStatusParams.setFutureOptionDiv(WEB3FuturesOptionDivDef.DEFAULT);
        l_runStatusParams.setProductType(ProductTypeEnum.EQUITY);
        l_runStatusParams.setAccountIdFrom(1);
        l_runStatusParams.setAccountIdTo(6);
        l_runStatusParams.setOnlineServiceDiv("7");
        l_runStatusParams.setCreatedTimestamp(WEB3DateUtility.getDate("20070116","yyyyMMdd"));
        l_runStatusParams.setLastUpdatedTimestamp(WEB3DateUtility.getDate("20070116","yyyyMMdd"));
        
        OnlineRunStatusParams l_runStatusParams1 = new OnlineRunStatusParams();
        l_runStatusParams1.setInstitutionCode("0D");
        l_runStatusParams1.setRunStatusDiv("5");
        l_runStatusParams1.setFutureOptionDiv(WEB3FuturesOptionDivDef.DEFAULT);
        l_runStatusParams1.setProductType(ProductTypeEnum.EQUITY);
        l_runStatusParams1.setAccountIdFrom(0);
        l_runStatusParams1.setAccountIdTo(5);
        l_runStatusParams1.setOnlineServiceDiv("7");
        l_runStatusParams1.setCreatedTimestamp(WEB3DateUtility.getDate("20070119","yyyyMMdd"));
        l_runStatusParams1.setLastUpdatedTimestamp(WEB3DateUtility.getDate("20070119","yyyyMMdd"));
        try
        {
            TestDBUtility.deleteAllAndCommit(OnlineRunStatusRow.TYPE);
            TestDBUtility.insertWithDelAndCommit(l_runStatusParams);
            TestDBUtility.insertWithDelAndCommit(l_runStatusParams1);
        }
        catch (Exception l_ex)
        {
            log.error(l_ex.getMessage(), l_ex);
            fail();
        }
        try
        {
            WEB3AdminEquityForcedSettleOrderApproveServiceImpl l_impl =
                new WEB3AdminEquityForcedSettleOrderApproveServiceImpl();
            //getInstanceFromログイン情報( )
            WEB3Administrator l_admin = WEB3Administrator.getInstanceFromLoginInfo();
            WEB3AdminForcedSettleApproveConfirmRequestForMock l_request =
                new WEB3AdminForcedSettleApproveConfirmRequestForMock();
            l_request.approveType = WEB3AdminEquityApproveTypeDef.DISAPPROVE;
            l_impl.validateApprovePossibility(l_admin, l_request);
        }
        catch(WEB3BaseException l_ex)
        {
            log.error(l_ex.getMessage(), l_ex);
            assertEquals(WEB3ErrorCatalog.BUSINESS_ERROR_01992,l_ex.getErrorInfo());
            log.info(STR_METHOD_NAME + ">>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>test pass !!");
        }
      catch (Exception l_ex)
      {
          log.error(l_ex.getMessage(), l_ex);
          fail();
      }
        log.exiting(STR_METHOD_NAME);
    }
    
    //①@getオンライン実行結果一覧()の戻り値 ！= null
    //getオンライン実行結果一覧()の戻り値の件数 == getデーモントリガー一覧()の戻り値の件数
    //getオンライン実行結果一覧()の戻り値の各要素の実行ステータス区分に"処理中"が存在する。
    public void testValidateApprovePossibilityC3()
    {
        final String STR_METHOD_NAME = "testValidateApprovePossibilityC3()";
        log.entering(STR_METHOD_NAME);
        OnlineRunStatusParams l_runStatusParams = new OnlineRunStatusParams();
        l_runStatusParams.setInstitutionCode("0D");
        l_runStatusParams.setRunStatusDiv("5");
        l_runStatusParams.setFutureOptionDiv(WEB3FuturesOptionDivDef.DEFAULT);
        l_runStatusParams.setProductType(ProductTypeEnum.EQUITY);
        l_runStatusParams.setAccountIdFrom(1);
        l_runStatusParams.setAccountIdTo(6);
        l_runStatusParams.setOnlineServiceDiv("7");
        l_runStatusParams.setCreatedTimestamp(WEB3DateUtility.getDate("20070116","yyyyMMdd"));
        l_runStatusParams.setLastUpdatedTimestamp(WEB3DateUtility.getDate("20070116","yyyyMMdd"));
        
        try
        {
            TestDBUtility.deleteAllAndCommit(OnlineRunStatusRow.TYPE);
            TestDBUtility.insertWithDelAndCommit(l_runStatusParams);
        }
        catch (Exception l_ex)
        {
            log.error(l_ex.getMessage(), l_ex);
            fail();
        }
        try
        {
            WEB3AdminEquityForcedSettleOrderApproveServiceImpl l_impl =
                new WEB3AdminEquityForcedSettleOrderApproveServiceImpl();
            //getInstanceFromログイン情報( )
            WEB3Administrator l_admin = WEB3Administrator.getInstanceFromLoginInfo();
            WEB3AdminForcedSettleApproveRunRequestForMock l_request =
                new WEB3AdminForcedSettleApproveRunRequestForMock();
            l_request.approveType = WEB3AdminEquityApproveTypeDef.DISAPPROVE;
            l_impl.validateApprovePossibility(l_admin, l_request);
        }
        catch(WEB3BaseException l_ex)
        {
            log.error(l_ex.getMessage(), l_ex);
            assertEquals(WEB3ErrorCatalog.BUSINESS_ERROR_01992,l_ex.getErrorInfo());
            log.info(STR_METHOD_NAME + ">>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>test pass !!");
        }
        catch (Exception l_ex)
        {
            log.error(l_ex.getMessage(), l_ex);
            fail();
        }
        log.exiting(STR_METHOD_NAME);
    }
    
    //    //①@getオンライン実行結果一覧()の戻り値 ！= null
    //getオンライン実行結果一覧()の戻り値の件数 == getデーモントリガー一覧()の戻り値の件数
    //getオンライン実行結果一覧()の戻り値の各要素の実行ステータス区分に"処理中"が存在しない
    //getデーモントリガー一覧()の戻り値の各要素の処理状態又一條不是"未稼動"。
    public void testValidateApprovePossibilityC4()
    {
        final String STR_METHOD_NAME = "testValidateApprovePossibilityC4()";
        log.entering(STR_METHOD_NAME);
        OnlineRunStatusParams l_runStatusParams = new OnlineRunStatusParams();
        l_runStatusParams.setInstitutionCode("0D");
        l_runStatusParams.setRunStatusDiv("1");
        l_runStatusParams.setFutureOptionDiv(WEB3FuturesOptionDivDef.DEFAULT);
        l_runStatusParams.setProductType(ProductTypeEnum.EQUITY);
        l_runStatusParams.setAccountIdFrom(1);
        l_runStatusParams.setAccountIdTo(6);
        l_runStatusParams.setOnlineServiceDiv("7");
        l_runStatusParams.setCreatedTimestamp(WEB3DateUtility.getDate("20070116","yyyyMMdd"));
        l_runStatusParams.setLastUpdatedTimestamp(WEB3DateUtility.getDate("20070116","yyyyMMdd"));
        
        OnlineRunStatusParams l_runStatusParams1 = new OnlineRunStatusParams();
        l_runStatusParams1.setInstitutionCode("0D");
        l_runStatusParams1.setRunStatusDiv("0");
        l_runStatusParams1.setFutureOptionDiv(WEB3FuturesOptionDivDef.DEFAULT);
        l_runStatusParams1.setProductType(ProductTypeEnum.EQUITY);
        l_runStatusParams1.setAccountIdFrom(0);
        l_runStatusParams1.setAccountIdTo(5);
        l_runStatusParams1.setOnlineServiceDiv("7");
        l_runStatusParams1.setCreatedTimestamp(WEB3DateUtility.getDate("20070116","yyyyMMdd"));
        l_runStatusParams1.setLastUpdatedTimestamp(WEB3DateUtility.getDate("20070116","yyyyMMdd"));
     
        DaemonTriggerParams l_daemonTriggerParams = new DaemonTriggerParams();
        l_daemonTriggerParams.setThreadNo(111L);
        l_daemonTriggerParams.setTriggerType("Z");
        l_daemonTriggerParams.setOrderRequestNumber("1");
        l_daemonTriggerParams.setRangeFrom(0);
        l_daemonTriggerParams.setRangeTo(5);
        l_daemonTriggerParams.setTriggerStatus(WEB3DaemonTriggerStatusDef.THREAD_IDLE);
        
        DaemonTriggerParams l_daemonTriggerParams1 = new DaemonTriggerParams();
        l_daemonTriggerParams1.setThreadNo(123L);
        l_daemonTriggerParams1.setTriggerType("Z");
        l_daemonTriggerParams1.setOrderRequestNumber("1");
        l_daemonTriggerParams1.setRangeFrom(1);
        l_daemonTriggerParams1.setRangeTo(6);
        l_daemonTriggerParams1.setTriggerStatus(WEB3DaemonTriggerStatusDef.THREAD_PROCESSING);
        try
        {
            TestDBUtility.deleteAllAndCommit(OnlineRunStatusRow.TYPE);
            TestDBUtility.insertWithDelAndCommit(l_runStatusParams);
            TestDBUtility.insertWithDelAndCommit(l_runStatusParams1);
         
            TestDBUtility.deleteAllAndCommit(DaemonTriggerRow.TYPE);
            TestDBUtility.insertWithDelAndCommit(l_daemonTriggerParams);
            TestDBUtility.insertWithDelAndCommit(l_daemonTriggerParams1);
        }
        catch (Exception l_ex)
        {
            log.error(l_ex.getMessage(), l_ex);
            fail();
        }
        try
        {
            WEB3AdminEquityForcedSettleOrderApproveServiceImpl l_impl =
                new WEB3AdminEquityForcedSettleOrderApproveServiceImpl();
            //getInstanceFromログイン情報( )
            WEB3Administrator l_admin = WEB3Administrator.getInstanceFromLoginInfo();
            WEB3AdminForcedSettleApproveRunRequestForMock l_request =
                new WEB3AdminForcedSettleApproveRunRequestForMock();
            l_request.approveType = WEB3AdminEquityApproveTypeDef.DISAPPROVE;
            l_impl.validateApprovePossibility(l_admin, l_request);
        }
        catch(WEB3BaseException l_ex)
        {
            log.error(l_ex.getMessage(), l_ex);
            assertEquals(WEB3ErrorCatalog.BUSINESS_ERROR_01992,l_ex.getErrorInfo());
            log.info(STR_METHOD_NAME + ">>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>test pass !!");
        }
        catch (Exception l_ex)
        {
            log.error(l_ex.getMessage(), l_ex);
            fail();
        }
        log.exiting(STR_METHOD_NAME);
    }
    
    //①@getオンライン実行結果一覧()の戻り値 ！= null
    //getオンライン実行結果一覧()の戻り値の件数 == getデーモントリガー一覧()の戻り値の件数
    //getオンライン実行結果一覧()の戻り値の各要素の実行ステータス区分に"処理中"が存在しない
    //getデーモントリガー一覧()の戻り値の各要素の処理状態全是"未稼動"。
    public void testValidateApprovePossibilityC5()
    {
        final String STR_METHOD_NAME = "testValidateApprovePossibilityC5()";
        log.entering(STR_METHOD_NAME);
        OnlineRunStatusParams l_runStatusParams = new OnlineRunStatusParams();
        l_runStatusParams.setInstitutionCode("0D");
        l_runStatusParams.setRunStatusDiv("1");
        l_runStatusParams.setFutureOptionDiv(WEB3FuturesOptionDivDef.DEFAULT);
        l_runStatusParams.setProductType(ProductTypeEnum.EQUITY);
        l_runStatusParams.setAccountIdFrom(1);
        l_runStatusParams.setAccountIdTo(6);
        l_runStatusParams.setOnlineServiceDiv("7");
        l_runStatusParams.setCreatedTimestamp(WEB3DateUtility.getDate("20070116","yyyyMMdd"));
        l_runStatusParams.setLastUpdatedTimestamp(WEB3DateUtility.getDate("20070116","yyyyMMdd"));
        
        OnlineRunStatusParams l_runStatusParams1 = new OnlineRunStatusParams();
        l_runStatusParams1.setInstitutionCode("0D");
        l_runStatusParams1.setRunStatusDiv("0");
        l_runStatusParams1.setFutureOptionDiv(WEB3FuturesOptionDivDef.DEFAULT);
        l_runStatusParams1.setProductType(ProductTypeEnum.EQUITY);
        l_runStatusParams1.setAccountIdFrom(0);
        l_runStatusParams1.setAccountIdTo(5);
        l_runStatusParams1.setOnlineServiceDiv("7");
        l_runStatusParams1.setCreatedTimestamp(WEB3DateUtility.getDate("20070116","yyyyMMdd"));
        l_runStatusParams1.setLastUpdatedTimestamp(WEB3DateUtility.getDate("20070116","yyyyMMdd"));
     
        DaemonTriggerParams l_daemonTriggerParams = new DaemonTriggerParams();
        l_daemonTriggerParams.setThreadNo(111L);
        l_daemonTriggerParams.setTriggerType("Z");
        l_daemonTriggerParams.setOrderRequestNumber("1");
        l_daemonTriggerParams.setRangeFrom(0);
        l_daemonTriggerParams.setRangeTo(5);
        l_daemonTriggerParams.setTriggerStatus(WEB3DaemonTriggerStatusDef.THREAD_IDLE);
        
        DaemonTriggerParams l_daemonTriggerParams1 = new DaemonTriggerParams();
        l_daemonTriggerParams1.setThreadNo(123L);
        l_daemonTriggerParams1.setTriggerType("Z");
        l_daemonTriggerParams1.setOrderRequestNumber("1");
        l_daemonTriggerParams1.setRangeFrom(1);
        l_daemonTriggerParams1.setRangeTo(6);
        l_daemonTriggerParams1.setTriggerStatus(WEB3DaemonTriggerStatusDef.THREAD_IDLE);
        try
        {
            TestDBUtility.deleteAllAndCommit(OnlineRunStatusRow.TYPE);
            TestDBUtility.insertWithDelAndCommit(l_runStatusParams);
            TestDBUtility.insertWithDelAndCommit(l_runStatusParams1);
         
            TestDBUtility.deleteAllAndCommit(DaemonTriggerRow.TYPE);
            TestDBUtility.insertWithDelAndCommit(l_daemonTriggerParams);
            TestDBUtility.insertWithDelAndCommit(l_daemonTriggerParams1);
        }
        catch (Exception l_ex)
        {
            log.error(l_ex.getMessage(), l_ex);
            fail();
        }
        try
        {
            WEB3AdminEquityForcedSettleOrderApproveServiceImpl l_impl =
                new WEB3AdminEquityForcedSettleOrderApproveServiceImpl();
            //getInstanceFromログイン情報( )
            WEB3Administrator l_admin = WEB3Administrator.getInstanceFromLoginInfo();
            WEB3AdminForcedSettleApproveRunRequestForMock l_request =
                new WEB3AdminForcedSettleApproveRunRequestForMock();
            l_request.approveType = WEB3AdminEquityApproveTypeDef.DISAPPROVE;
            l_impl.validateApprovePossibility(l_admin, l_request);
        }
        catch (Exception l_ex)
        {
            log.error(l_ex.getMessage(), l_ex);
            fail();
        }
        log.exiting(STR_METHOD_NAME);
    }
    
    //getオンライン実行結果一覧()の戻り値 == null
    public void testValidateApprovePossibilityC6()
    {
        final String STR_METHOD_NAME = "testValidateApprovePossibilityC6()";
        log.entering(STR_METHOD_NAME);
        OnlineRunStatusParams l_runStatusParams = new OnlineRunStatusParams();
        l_runStatusParams.setInstitutionCode("1D");
        l_runStatusParams.setRunStatusDiv("1");
        l_runStatusParams.setFutureOptionDiv(WEB3FuturesOptionDivDef.DEFAULT);
        l_runStatusParams.setProductType(ProductTypeEnum.EQUITY);
        l_runStatusParams.setAccountIdFrom(1);
        l_runStatusParams.setAccountIdTo(6);
        l_runStatusParams.setOnlineServiceDiv("7");
        l_runStatusParams.setCreatedTimestamp(WEB3DateUtility.getDate("20070116","yyyyMMdd"));
        l_runStatusParams.setLastUpdatedTimestamp(WEB3DateUtility.getDate("20070116","yyyyMMdd"));
     
        DaemonTriggerParams l_daemonTriggerParams = new DaemonTriggerParams();
        l_daemonTriggerParams.setThreadNo(111L);
        l_daemonTriggerParams.setTriggerType("Z");
        l_daemonTriggerParams.setOrderRequestNumber("1");
        l_daemonTriggerParams.setRangeFrom(0);
        l_daemonTriggerParams.setRangeTo(5);
        l_daemonTriggerParams.setTriggerStatus(WEB3DaemonTriggerStatusDef.THREAD_IDLE);
        
        DaemonTriggerParams l_daemonTriggerParams1 = new DaemonTriggerParams();
        l_daemonTriggerParams1.setThreadNo(123L);
        l_daemonTriggerParams1.setTriggerType("Z");
        l_daemonTriggerParams1.setOrderRequestNumber("1");
        l_daemonTriggerParams1.setRangeFrom(1);
        l_daemonTriggerParams1.setRangeTo(6);
        l_daemonTriggerParams1.setTriggerStatus(WEB3DaemonTriggerStatusDef.THREAD_PROCESSING);
        try
        {
            TestDBUtility.deleteAllAndCommit(OnlineRunStatusRow.TYPE);
            TestDBUtility.insertWithDelAndCommit(l_runStatusParams);
         
            TestDBUtility.deleteAllAndCommit(DaemonTriggerRow.TYPE);
            TestDBUtility.insertWithDelAndCommit(l_daemonTriggerParams);
            TestDBUtility.insertWithDelAndCommit(l_daemonTriggerParams1);
        }
        catch (Exception l_ex)
        {
            log.error(l_ex.getMessage(), l_ex);
            fail();
        }
        try
        {
            WEB3AdminEquityForcedSettleOrderApproveServiceImpl l_impl =
                new WEB3AdminEquityForcedSettleOrderApproveServiceImpl();
            //getInstanceFromログイン情報( )
            WEB3Administrator l_admin = WEB3Administrator.getInstanceFromLoginInfo();
            WEB3AdminForcedSettleApproveRunRequestForMock l_request =
                new WEB3AdminForcedSettleApproveRunRequestForMock();
            l_request.approveType = WEB3AdminEquityApproveTypeDef.DISAPPROVE;
            l_impl.validateApprovePossibility(l_admin, l_request);
        }
        catch (Exception l_ex)
        {
            log.error(l_ex.getMessage(), l_ex);
            fail();
        }
        log.exiting(STR_METHOD_NAME);
    }

    public void testValidateApprovePossibilityC7()
    {
        final String STR_METHOD_NAME = "testValidateApprovePossibilityC7()";
        log.entering(STR_METHOD_NAME);
        try
        {
            WEB3AdminEquityForcedSettleOrderApproveServiceImpl l_impl =
                new WEB3AdminEquityForcedSettleOrderApproveServiceImpl();
            //getInstanceFromログイン情報( )
            WEB3Administrator l_admin = WEB3Administrator.getInstanceFromLoginInfo();
            WEB3AdminForcedSettleApproveStatusRequestForMock l_request =
                new WEB3AdminForcedSettleApproveStatusRequestForMock();
            l_impl.validateApprovePossibility(l_admin, l_request);
            
        }
        catch(WEB3SystemLayerException l_ex)
        {
            log.error(l_ex.getMessage(), l_ex);
            assertEquals(WEB3ErrorCatalog.SYSTEM_ERROR_80018,l_ex.getErrorInfo());
            log.info(STR_METHOD_NAME + ">>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>test pass !!");
        }
        catch (Exception l_ex)
        {
            log.error(l_ex.getMessage(), l_ex);
            fail();
        }
      log.exiting(STR_METHOD_NAME);
    }
    /*
     * Test method for 'webbroker3.eqtypeadmin.service.delegate.stdimpls.WEB3AdminEquityForcedSettleOrderApproveServiceImpl.getDaemonTriggerUnits(String)'
     */
    public void testGetDaemonTriggerUnitsC1()
    {
        final String STR_METHOD_NAME = "testGetDaemonTriggerUnitsC1()";
        log.entering(STR_METHOD_NAME);
        try
        {
            WEB3AdminEquityForcedSettleOrderApproveServiceImpl l_impl =
                new WEB3AdminEquityForcedSettleOrderApproveServiceImpl();
            String l_strTriggerType = WEB3AdminEquityApproveTypeDef.APPROVE;
            l_impl.getDaemonTriggerUnits(l_strTriggerType);
            
        }
        catch(WEB3SystemLayerException l_ex)
        {
            log.error(l_ex.getMessage(), l_ex);
            assertEquals(WEB3ErrorCatalog.SYSTEM_ERROR_80005,l_ex.getErrorInfo());
            log.info(STR_METHOD_NAME + ">>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>test pass !!");
        }
        catch (Exception l_ex)
        {
            log.error(l_ex.getMessage(), l_ex);
            fail();
        }
      log.exiting(STR_METHOD_NAME);
    }
    
    public void testGetDaemonTriggerUnitsC2()
    {
        final String STR_METHOD_NAME = "testGetDaemonTriggerUnitsC2()";
        log.entering(STR_METHOD_NAME);
        try
        {
            WEB3AdminEquityForcedSettleOrderApproveServiceImpl l_impl =
                new WEB3AdminEquityForcedSettleOrderApproveServiceImpl();
            String l_strTriggerType = WEB3AdminEquityApproveTypeDef.DISAPPROVE;
            l_impl.getDaemonTriggerUnits(l_strTriggerType);
            
        }
        catch (Exception l_ex)
        {
            log.error(l_ex.getMessage(), l_ex);
            fail();
        }
      log.exiting(STR_METHOD_NAME);
    }

    /*
     * Test method for 'webbroker3.eqtypeadmin.service.delegate.stdimpls.WEB3AdminEquityForcedSettleOrderApproveServiceImpl.getOnlineRunResultUnits(String, String)'
     */
    public void testGetOnlineRunResultUnitsC1()
    {
        final String STR_METHOD_NAME = "testGetOnlineRunResultUnitsC1()";
        log.entering(STR_METHOD_NAME);
        OnlineRunStatusParams l_runStatusParams = new OnlineRunStatusParams();
        l_runStatusParams.setInstitutionCode("0D");
        l_runStatusParams.setRunStatusDiv("1");
        l_runStatusParams.setFutureOptionDiv(WEB3FuturesOptionDivDef.DEFAULT);
        l_runStatusParams.setProductType(ProductTypeEnum.EQUITY);
        l_runStatusParams.setAccountIdFrom(1);
        l_runStatusParams.setAccountIdTo(6);
        l_runStatusParams.setOnlineServiceDiv("7");
        l_runStatusParams.setCreatedTimestamp(WEB3DateUtility.getDate("20070116","yyyyMMdd"));
        l_runStatusParams.setLastUpdatedTimestamp(WEB3DateUtility.getDate("20070116","yyyyMMdd"));
        try
        {
            TestDBUtility.deleteAllAndCommit(OnlineRunStatusRow.TYPE);
            TestDBUtility.insertWithDelAndCommit(l_runStatusParams);
        }
        catch (Exception l_ex)
        {
            log.error(l_ex.getMessage(), l_ex);
            fail();
        }
        try
        {
            WEB3AdminEquityForcedSettleOrderApproveServiceImpl l_impl =
                new WEB3AdminEquityForcedSettleOrderApproveServiceImpl();
            String l_strTriggerType = WEB3AdminEquityApproveTypeDef.APPROVE;
            l_impl.getOnlineRunResultUnits("0D",l_strTriggerType);
            
        }
        catch(WEB3SystemLayerException l_ex)
        {
            log.error(l_ex.getMessage(), l_ex);
            assertEquals(WEB3ErrorCatalog.SYSTEM_ERROR_80005,l_ex.getErrorInfo());
            log.info(STR_METHOD_NAME + ">>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>test pass !!");
        }
        catch (Exception l_ex)
        {
            log.error(l_ex.getMessage(), l_ex);
            fail();
        }
      log.exiting(STR_METHOD_NAME);
    }
    
    public void testGetOnlineRunResultUnitsC2()
    {
        final String STR_METHOD_NAME = "testGetOnlineRunResultUnitsC2()";
        log.entering(STR_METHOD_NAME);
        OnlineRunStatusParams l_runStatusParams = new OnlineRunStatusParams();
        l_runStatusParams.setInstitutionCode("0D");
        l_runStatusParams.setRunStatusDiv("1");
        l_runStatusParams.setFutureOptionDiv(WEB3FuturesOptionDivDef.DEFAULT);
        l_runStatusParams.setProductType(ProductTypeEnum.EQUITY);
        l_runStatusParams.setAccountIdFrom(1);
        l_runStatusParams.setAccountIdTo(6);
        l_runStatusParams.setOnlineServiceDiv("7");
        l_runStatusParams.setCreatedTimestamp(WEB3DateUtility.getDate("20070116","yyyyMMdd"));
        l_runStatusParams.setLastUpdatedTimestamp(WEB3DateUtility.getDate("20070116","yyyyMMdd"));
        try
        {
            TestDBUtility.deleteAllAndCommit(OnlineRunStatusRow.TYPE);
            TestDBUtility.insertWithDelAndCommit(l_runStatusParams);
        }
        catch (Exception l_ex)
        {
            log.error(l_ex.getMessage(), l_ex);
            fail();
        }
        try
        {
            WEB3AdminEquityForcedSettleOrderApproveServiceImpl l_impl =
                new WEB3AdminEquityForcedSettleOrderApproveServiceImpl();
            String l_strTriggerType = WEB3AdminEquityApproveTypeDef.DISAPPROVE;
            l_impl.getOnlineRunResultUnits("0D",l_strTriggerType);
            
        }
        catch (Exception l_ex)
        {
            log.error(l_ex.getMessage(), l_ex);
            fail();
        }
      log.exiting(STR_METHOD_NAME);
    }

    /*
     * Test method for 'webbroker3.eqtypeadmin.service.delegate.stdimpls.WEB3AdminEquityForcedSettleOrderApproveServiceImpl.deleteOnlineRunResult(String, String)'
     */
    public void testDeleteOnlineRunResultC1()
    {
        final String STR_METHOD_NAME = "testDeleteOnlineRunResultC1()";
        log.entering(STR_METHOD_NAME);
        OnlineRunStatusParams l_runStatusParams = new OnlineRunStatusParams();
        l_runStatusParams.setInstitutionCode("0D");
        l_runStatusParams.setRunStatusDiv("1");
        l_runStatusParams.setFutureOptionDiv(WEB3FuturesOptionDivDef.DEFAULT);
        l_runStatusParams.setProductType(ProductTypeEnum.EQUITY);
        l_runStatusParams.setAccountIdFrom(1);
        l_runStatusParams.setAccountIdTo(6);
        l_runStatusParams.setOnlineServiceDiv("7");
        l_runStatusParams.setCreatedTimestamp(WEB3DateUtility.getDate("20070116","yyyyMMdd"));
        l_runStatusParams.setLastUpdatedTimestamp(WEB3DateUtility.getDate("20070116","yyyyMMdd"));
        try
        {
            TestDBUtility.deleteAllAndCommit(OnlineRunStatusRow.TYPE);
            TestDBUtility.insertWithDelAndCommit(l_runStatusParams);
        }
        catch (Exception l_ex)
        {
            log.error(l_ex.getMessage(), l_ex);
            fail();
        }
        try
        {
            WEB3AdminEquityForcedSettleOrderApproveServiceImpl l_impl =
                new WEB3AdminEquityForcedSettleOrderApproveServiceImpl();
            String l_strTriggerType = WEB3AdminEquityApproveTypeDef.DISAPPROVE;
            
            OnlineRunStatusRow l_row1 = OnlineRunStatusDao.findRowByPk("0D",ProductTypeEnum.EQUITY,"0","7",1);
            assertNotNull(l_row1);

            l_impl.deleteOnlineRunResult("0D", l_strTriggerType);
//            OnlineRunStatusRow l_row2 = OnlineRunStatusDao.findRowByPk("0D",ProductTypeEnum.EQUITY,"0","7",1);          
        }
        catch (Exception l_ex)
        {
            log.error(l_ex.getMessage(), l_ex);
            fail();
        }
      log.exiting(STR_METHOD_NAME);
    }
    
    public void testDeleteOnlineRunResultC2()
    {
        final String STR_METHOD_NAME = "testDeleteOnlineRunResultC2()";
        log.entering(STR_METHOD_NAME);
        OnlineRunStatusParams l_runStatusParams = new OnlineRunStatusParams();
        l_runStatusParams.setInstitutionCode("0D");
        l_runStatusParams.setRunStatusDiv("1");
        l_runStatusParams.setFutureOptionDiv(WEB3FuturesOptionDivDef.DEFAULT);
        l_runStatusParams.setProductType(ProductTypeEnum.EQUITY);
        l_runStatusParams.setAccountIdFrom(1);
        l_runStatusParams.setAccountIdTo(6);
        l_runStatusParams.setOnlineServiceDiv("7");
        l_runStatusParams.setCreatedTimestamp(WEB3DateUtility.getDate("20070116","yyyyMMdd"));
        l_runStatusParams.setLastUpdatedTimestamp(WEB3DateUtility.getDate("20070116","yyyyMMdd"));
        try
        {
            TestDBUtility.deleteAllAndCommit(OnlineRunStatusRow.TYPE);
            TestDBUtility.insertWithDelAndCommit(l_runStatusParams);
        }
        catch (Exception l_ex)
        {
            log.error(l_ex.getMessage(), l_ex);
            fail();
        }
        try
        {
            WEB3AdminEquityForcedSettleOrderApproveServiceImpl l_impl =
                new WEB3AdminEquityForcedSettleOrderApproveServiceImpl();
            String l_strTriggerType = WEB3AdminEquityApproveTypeDef.APPROVE;
            
            OnlineRunStatusRow l_row1 = OnlineRunStatusDao.findRowByPk("0D",ProductTypeEnum.EQUITY,"0","7",1);
            assertNotNull(l_row1);

            l_impl.deleteOnlineRunResult("0D", l_strTriggerType);

            OnlineRunStatusRow l_row2 = OnlineRunStatusDao.findRowByPk("0D",ProductTypeEnum.EQUITY,"0","7",1);
            assertNotNull(l_row2);
            assertEquals(l_row1, l_row2);
        }
        catch (Exception l_ex)
        {
            log.error(l_ex.getMessage(), l_ex);
            fail();
        }
      log.exiting(STR_METHOD_NAME);
    }

    /*
     * Test method for 'webbroker3.eqtypeadmin.service.delegate.stdimpls.WEB3AdminEquityForcedSettleOrderApproveServiceImpl.updateAPCalling(long)'
     */
    public void testUpdateAPCallingC1()
    {
        final String STR_METHOD_NAME = "testUpdateAPCallingC1()";
        log.entering(STR_METHOD_NAME);
        try
        {
            WEB3AdminEquityForcedSettleOrderApproveServiceImpl l_impl =
                new WEB3AdminEquityForcedSettleOrderApproveServiceImpl();
            DaemonTriggerRow l_row1 = DaemonTriggerDao.findRowByPk(111);
            assertEquals(l_row1.getTriggerStatus(),WEB3DaemonTriggerStatusDef.THREAD_PROCESSING);

            l_impl.updateAPCalling(111);

            DaemonTriggerRow l_row2 = DaemonTriggerDao.findRowByPk(111);
            assertEquals(l_row2.getTriggerStatus(),WEB3DaemonTriggerStatusDef.THREAD_API_CALL);
        }
        catch (Exception l_ex)
        {
            log.error(l_ex.getMessage(), l_ex);
            fail();
        }
      log.exiting(STR_METHOD_NAME);
    }

    /*
     * Test method for 'webbroker3.eqtypeadmin.service.delegate.stdimpls.WEB3AdminEquityForcedSettleOrderApproveServiceImpl.getServerAccessor()'
     */
    public void testGetServerAccessorC1()
    {
        final String STR_METHOD_NAME = "testRunApproveC1()";
        log.entering(STR_METHOD_NAME);
        
        ServerConfigParams l_serverConfigParams = new ServerConfigParams();
        l_serverConfigParams.setConfigCateg("cluster.urls");
        l_serverConfigParams.setConfigName("01");
        l_serverConfigParams.setConfigTitle("developer");
        l_serverConfigParams.setConfigValue("sockpool://localhost:3001");
        
        OnlineRunStatusParams l_runStatusParams = new OnlineRunStatusParams();
        l_runStatusParams.setInstitutionCode("0D");
        l_runStatusParams.setRunStatusDiv("5");
        l_runStatusParams.setFutureOptionDiv(WEB3FuturesOptionDivDef.DEFAULT);
        l_runStatusParams.setProductType(ProductTypeEnum.EQUITY);
        l_runStatusParams.setAccountIdFrom(1);
        l_runStatusParams.setAccountIdTo(6);
        l_runStatusParams.setOnlineServiceDiv("8");
        l_runStatusParams.setCreatedTimestamp(WEB3DateUtility.getDate("20070116","yyyyMMdd"));
        l_runStatusParams.setLastUpdatedTimestamp(WEB3DateUtility.getDate("20070116","yyyyMMdd"));
        try
        {
            TestDBUtility.deleteAllAndCommit(OnlineRunStatusRow.TYPE);
            TestDBUtility.insertWithDelAndCommit(l_runStatusParams);
            
            TestDBUtility.deleteAllAndCommit(ServerConfigRow.TYPE);
            TestDBUtility.insertWithDelAndCommit(l_serverConfigParams);
        }
        catch (Exception l_ex)
        {
            log.error(l_ex.getMessage(), l_ex);
            fail();
        }
        
        try
        {
            WEB3AdminEquityForcedSettleOrderApproveServiceImpl l_impl =
                new WEB3AdminEquityForcedSettleOrderApproveServiceImpl();
            l_impl.getServerAccessor();
        }
        catch(WEB3SystemLayerException l_ex)
        {
            log.error(l_ex.getMessage(), l_ex);
            assertEquals(WEB3ErrorCatalog.SYSTEM_ERROR_80002,l_ex.getErrorInfo());
            log.info(STR_METHOD_NAME + ">>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>test pass !!");
        }
        catch (Exception l_ex)
        {
            log.error(l_ex.getMessage(), l_ex);
            fail();
        }
        log.exiting(STR_METHOD_NAME);
    }

}
@
