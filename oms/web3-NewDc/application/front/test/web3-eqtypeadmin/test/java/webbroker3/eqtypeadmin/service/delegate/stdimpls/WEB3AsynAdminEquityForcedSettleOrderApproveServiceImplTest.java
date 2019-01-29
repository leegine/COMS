head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.04.07.01.08.48;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	5444d9d078c63f4;
filename	WEB3AsynAdminEquityForcedSettleOrderApproveServiceImplTest.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@package webbroker3.eqtypeadmin.service.delegate.stdimpls;

import java.util.List;

import com.fitechlabs.xtrade.kernel.boot.Services;
import com.fitechlabs.xtrade.kernel.data.Processors;
import com.fitechlabs.xtrade.kernel.data.QueryProcessor;
import com.fitechlabs.xtrade.plugin.tc.eqtype.data.EqtypeClosingContractSpecParams;
import com.fitechlabs.xtrade.plugin.tc.eqtype.data.EqtypeClosingContractSpecRow;
import com.fitechlabs.xtrade.plugin.tc.eqtype.data.EqtypeContractParams;
import com.fitechlabs.xtrade.plugin.tc.eqtype.data.EqtypeContractRow;
import com.fitechlabs.xtrade.plugin.tc.eqtype.data.EqtypeOrderUnitParams;
import com.fitechlabs.xtrade.plugin.tc.eqtype.data.EqtypeOrderUnitRow;
import com.fitechlabs.xtrade.plugin.tc.gentrade.ContractTypeEnum;
import com.fitechlabs.xtrade.plugin.tc.gentrade.GtlUtils;
import com.fitechlabs.xtrade.plugin.tc.gentrade.OrderCategEnum;
import com.fitechlabs.xtrade.plugin.tc.gentrade.OrderOpenStatusEnum;
import com.fitechlabs.xtrade.plugin.tc.gentrade.OrderStatusEnum;
import com.fitechlabs.xtrade.plugin.tc.gentrade.OrderTypeEnum;
import com.fitechlabs.xtrade.plugin.tc.gentrade.ProductTypeEnum;
import com.fitechlabs.xtrade.plugin.tc.gentrade.TaxTypeEnum;
import com.fitechlabs.xtrade.plugin.tc.gentrade.data.BranchParams;
import com.fitechlabs.xtrade.plugin.tc.gentrade.data.MarketParams;

import test.util.TestDBUtility;

import webbroker3.common.WEB3BaseException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.common.WEB3SystemLayerException;
import webbroker3.common.define.WEB3ApproveStatusType;
import webbroker3.eqtypeadmin.data.AdminEqForcedSettleOrderRow;
//import webbroker3.eqtypeadmin.data.ForcedSettleErrorOrderParams;
//import webbroker3.eqtypeadmin.data.ForcedSettleErrorOrderRow;
import webbroker3.eqtypeadmin.message.WEB3AdminEquityForcedSettleOrderApproveMainRequest;
import webbroker3.eqtypeadmin.service.delegate.WEB3AdminEquityForcedSettleOrderApproveUnitService;
import webbroker3.eqtypeadmin.service.delegate.stdimpls.WEB3AsynAdminEquityForcedSettleOrderApproveServiceImpl.WEB3AdminEquityForcedSettleOrderApproveDaemonTriggerTransactionCallback;
import webbroker3.eqtypeadmin.service.delegate.stdimpls.WEB3AsynAdminEquityForcedSettleOrderApproveServiceImpl.WEB3AdminEquityForcedSettleOrderApproveOnlineRunStatusTransactionCallback;
import webbroker3.gentrade.WEB3GentradeOnlineRunStatus;
import webbroker3.gentrade.WEB3GentradeTradingTimeManagementForMock;
import webbroker3.gentrade.data.AdministratorParams;
import webbroker3.gentrade.data.AdministratorRow;
import webbroker3.gentrade.data.DaemonTriggerParams;
import webbroker3.gentrade.data.OnlineRunStatusParams;
import webbroker3.gentrade.data.OnlineRunStatusRow;
import webbroker3.mock.TestBaseForMock;
import webbroker3.util.WEB3LogUtility;

public class WEB3AsynAdminEquityForcedSettleOrderApproveServiceImplTest extends TestBaseForMock
{
    /**
     * ログ
     */
    private static WEB3LogUtility log =
        WEB3LogUtility.getInstance(
            WEB3AsynAdminEquityForcedSettleOrderApproveServiceImplTest.class);

    public WEB3AsynAdminEquityForcedSettleOrderApproveServiceImplTest(String arg0)
    {
        super(arg0);
    }

    protected void setUp() throws Exception
    {
        super.setUp();
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
        l_eqOrderUnitParams.setForcedSettleReasonType("1");
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
        try
        {
            TestDBUtility.deleteAllAndCommit(EqtypeOrderUnitRow.TYPE);
//            TestDBUtility.deleteAllAndCommit(ForcedSettleErrorOrderRow.TYPE);
            TestDBUtility.deleteAllAndCommit(EqtypeContractRow.TYPE);
            TestDBUtility.deleteAllAndCommit(EqtypeClosingContractSpecRow.TYPE);

//            TestDBUtility.insertWithDelAndCommit(l_errorOrderParams);
            TestDBUtility.insertWithDelAndCommit(l_eqOrderUnitParams);
            TestDBUtility.insertWithDelAndCommit(l_contractSpecParams);
            TestDBUtility.insertWithDelAndCommit(l_contractParams);
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
    
    public void testWEB3AdminEquityForcedSettleOrderApproveOnlineRunStatusTransactionCallbackProcess_0001()
    {
        String STR_METHOD_NAME = ".testWEB3AdminEquityForcedSettleOrderApproveOnlineRunStatusTransactionCallbackProcess_0001()";
        log.entering(TEST_START + STR_METHOD_NAME);
        
        String l_strInstitutionCode = "123";
        long l_lngAccountIdFrom = 1234L;
        long l_lngAccountIdTo = 4321L;
        String l_strApproveType = "0";
        
        WEB3AsynAdminEquityForcedSettleOrderApproveServiceImpl l_asynAdminEquityForcedSettleOrderApproveServiceImpl = 
            new WEB3AsynAdminEquityForcedSettleOrderApproveServiceImpl();
        
        WEB3AdminEquityForcedSettleOrderApproveOnlineRunStatusTransactionCallback l_adminEquityForcedSettleOrderApproveOnlineRunStatusTransactionCallback = 
            l_asynAdminEquityForcedSettleOrderApproveServiceImpl.new WEB3AdminEquityForcedSettleOrderApproveOnlineRunStatusTransactionCallback(l_strInstitutionCode , l_lngAccountIdFrom , l_lngAccountIdTo , l_strApproveType);
        
        try
        {
            TestDBUtility.deleteAllAndCommit(OnlineRunStatusRow.TYPE);
            
            WEB3GentradeOnlineRunStatus l_onlineRunStatus = 
                (WEB3GentradeOnlineRunStatus)l_adminEquityForcedSettleOrderApproveOnlineRunStatusTransactionCallback.process();
            
            OnlineRunStatusRow onlineRunStatusRow = (OnlineRunStatusRow)l_onlineRunStatus.getDataSourceObject();
            OnlineRunStatusParams l_onlineRunStatusParams = new OnlineRunStatusParams(onlineRunStatusRow);
            
            assertEquals(l_strInstitutionCode , l_onlineRunStatusParams.getInstitutionCode());
            assertEquals(l_lngAccountIdFrom , l_onlineRunStatusParams.getAccountIdFrom());
            assertEquals(l_lngAccountIdTo , l_onlineRunStatusParams.getAccountIdTo());
            assertEquals("6" , l_onlineRunStatusParams.getOnlineServiceDiv());
        } 
        catch (Exception l_ex)
        {
            log.error(TEST_END + STR_METHOD_NAME , l_ex);
            log.exiting(TEST_END + STR_METHOD_NAME);
            
            fail();
        }
    }
    
    public void testWEB3AdminEquityForcedSettleOrderApproveOnlineRunStatusTransactionCallbackProcess_0002()
    {
        String STR_METHOD_NAME = ".testWEB3AdminEquityForcedSettleOrderApproveOnlineRunStatusTransactionCallbackProcess_0002()";
        log.entering(TEST_START + STR_METHOD_NAME);
        
        String l_strInstitutionCode = "123";
        long l_lngAccountIdFrom = 1234L;
        long l_lngAccountIdTo = 4321L;
        String l_strApproveType = "1";
        
        WEB3AsynAdminEquityForcedSettleOrderApproveServiceImpl l_asynAdminEquityForcedSettleOrderApproveServiceImpl = 
            new WEB3AsynAdminEquityForcedSettleOrderApproveServiceImpl();
        
        WEB3AdminEquityForcedSettleOrderApproveOnlineRunStatusTransactionCallback l_adminEquityForcedSettleOrderApproveOnlineRunStatusTransactionCallback = 
            l_asynAdminEquityForcedSettleOrderApproveServiceImpl.new WEB3AdminEquityForcedSettleOrderApproveOnlineRunStatusTransactionCallback(l_strInstitutionCode , l_lngAccountIdFrom , l_lngAccountIdTo , l_strApproveType);
        
        try
        {
            TestDBUtility.deleteAllAndCommit(OnlineRunStatusRow.TYPE);
            
            WEB3GentradeOnlineRunStatus l_onlineRunStatus = 
                (WEB3GentradeOnlineRunStatus)l_adminEquityForcedSettleOrderApproveOnlineRunStatusTransactionCallback.process();
            
            OnlineRunStatusRow onlineRunStatusRow = (OnlineRunStatusRow)l_onlineRunStatus.getDataSourceObject();
            OnlineRunStatusParams l_onlineRunStatusParams = new OnlineRunStatusParams(onlineRunStatusRow);
            
            assertEquals(l_strInstitutionCode , l_onlineRunStatusParams.getInstitutionCode());
            assertEquals(l_lngAccountIdFrom , l_onlineRunStatusParams.getAccountIdFrom());
            assertEquals(l_lngAccountIdTo , l_onlineRunStatusParams.getAccountIdTo());
            assertEquals("7" , l_onlineRunStatusParams.getOnlineServiceDiv());
        } 
        catch (Exception l_ex)
        {
            log.error(TEST_END + STR_METHOD_NAME , l_ex);
            log.exiting(TEST_END + STR_METHOD_NAME);
            
            fail();
        }
    }
    
    public void testWEB3AdminEquityForcedSettleOrderApproveDaemonTriggerTransactionCallbackProcess_0001()
    {
        String STR_METHOD_NAME = ".testWEB3AdminEquityForcedSettleOrderApproveDaemonTriggerTransactionCallbackProcess_0001()";
        log.entering(TEST_START + STR_METHOD_NAME);

        DaemonTriggerParams l_daemonTriggerParams = new DaemonTriggerParams();
        l_daemonTriggerParams.setTriggerType("1");
        l_daemonTriggerParams.setThreadNo(123456789L);
        l_daemonTriggerParams.setOrderRequestNumber("12");
        l_daemonTriggerParams.setRangeFrom(123456789L);
        l_daemonTriggerParams.setRangeTo(234567890L);
        l_daemonTriggerParams.setTriggerStatus("3");
        l_daemonTriggerParams.setTriggerDate(GtlUtils.getTradingSystem().getBizDate());
        
        try
        {
            TestDBUtility.deleteAll(l_daemonTriggerParams.getRowType());
            TestDBUtility.insertWithDel(l_daemonTriggerParams);
        }
        catch (Exception l_ex)
        {
            log.error(TEST_END + STR_METHOD_NAME , l_ex);
            log.exiting(TEST_END + STR_METHOD_NAME);
            
            fail();
        }
        WEB3AsynAdminEquityForcedSettleOrderApproveServiceImpl l_asynAdminEquityForcedSettleOrderApproveServiceImpl = 
            new WEB3AsynAdminEquityForcedSettleOrderApproveServiceImpl();
        
        WEB3AdminEquityForcedSettleOrderApproveDaemonTriggerTransactionCallback l_adminEquityForcedSettleOrderApproveDaemonTriggerTransactionCallback = 
            l_asynAdminEquityForcedSettleOrderApproveServiceImpl.new WEB3AdminEquityForcedSettleOrderApproveDaemonTriggerTransactionCallback(123456789L);

        try
        {
            List l_list = 
                (List)l_adminEquityForcedSettleOrderApproveDaemonTriggerTransactionCallback.process();
            DaemonTriggerParams l_daemonTriggerParamsT = (DaemonTriggerParams)l_list.get(0);
            assertEquals("1" , l_daemonTriggerParamsT.getTriggerType());
            assertEquals(123456789L , l_daemonTriggerParamsT.getThreadNo());
        }
        catch (Exception l_ex)
        {
            log.error(TEST_END + STR_METHOD_NAME , l_ex);
            log.exiting(TEST_END + STR_METHOD_NAME);
            
            fail();
        }
    }
    
    public void testWEB3AdminEquityForcedSettleOrderApproveDaemonTriggerTransactionCallbackProcess_0002()
    {
        String STR_METHOD_NAME = ".testWEB3AdminEquityForcedSettleOrderApproveDaemonTriggerTransactionCallbackProcess_0002()";
        log.entering(TEST_START + STR_METHOD_NAME);

        WEB3AsynAdminEquityForcedSettleOrderApproveServiceImpl l_asynAdminEquityForcedSettleOrderApproveServiceImpl = 
            new WEB3AsynAdminEquityForcedSettleOrderApproveServiceImpl();
        
        WEB3AdminEquityForcedSettleOrderApproveDaemonTriggerTransactionCallback l_adminEquityForcedSettleOrderApproveDaemonTriggerTransactionCallback = 
            l_asynAdminEquityForcedSettleOrderApproveServiceImpl.new WEB3AdminEquityForcedSettleOrderApproveDaemonTriggerTransactionCallback(123456789L);

        try
        {
            TestDBUtility.deleteAllAndCommit(DaemonTriggerParams.TYPE);
            
            List l_list = (List)l_adminEquityForcedSettleOrderApproveDaemonTriggerTransactionCallback.process();
            
            assertNull(l_list);
        }
        catch (Exception l_ex)
        {
            log.error(TEST_END + STR_METHOD_NAME , l_ex);
            log.exiting(TEST_END + STR_METHOD_NAME);
            
            fail();
        }
    }
    
    public void testRun_0001()
    {
        String STR_METHOD_NAME = ".testRun_0001()";
        log.entering(TEST_START + STR_METHOD_NAME);
        

        try
        {
            TestDBUtility.deleteAllAndCommit(DaemonTriggerParams.TYPE);
            
            WEB3AdminEquityForcedSettleOrderApproveMainRequest request = 
                new WEB3AdminEquityForcedSettleOrderApproveMainRequest();
            request.threadNo = new Long(123L);
            
            WEB3AsynAdminEquityForcedSettleOrderApproveServiceImpl l_asynAdminEquityForcedSettleOrderApproveServiceImpl = 
                new WEB3AsynAdminEquityForcedSettleOrderApproveServiceImpl(request);
            
            l_asynAdminEquityForcedSettleOrderApproveServiceImpl.run();
        }
        catch (Exception l_ex)
        {
            log.error(TEST_END + STR_METHOD_NAME , l_ex);
            log.exiting(TEST_END + STR_METHOD_NAME);
            
            fail();
        }
    }
    
    public void testRun_0002()
    {
        String STR_METHOD_NAME = ".testRun_0002()";
        log.entering(TEST_START + STR_METHOD_NAME);
        
        DaemonTriggerParams l_daemonTriggerParams = new DaemonTriggerParams();
        l_daemonTriggerParams.setTriggerType("1");
        l_daemonTriggerParams.setThreadNo(123456789L);
        l_daemonTriggerParams.setOrderRequestNumber("12");
        l_daemonTriggerParams.setRangeFrom(123456789L);
        l_daemonTriggerParams.setRangeTo(234567890L);
        l_daemonTriggerParams.setTriggerStatus("3");
        l_daemonTriggerParams.setTriggerDate(GtlUtils.getTradingSystem().getBizDate());

        AdministratorParams l_administratorParams = TestDBUtility.getAdministratorRow();
        l_administratorParams.setAdministratorId(11111111111L);
        try
        {
            TestDBUtility.deleteAllAndCommit(DaemonTriggerParams.TYPE);
            TestDBUtility.insertWithDelAndCommit(l_daemonTriggerParams);
            
            TestDBUtility.deleteAllAndCommit(OnlineRunStatusRow.TYPE);
            
            TestDBUtility.deleteAllAndCommit(AdministratorRow.TYPE);
            TestDBUtility.insertWithDelAndCommit(l_administratorParams);
            
            
            WEB3AdminEquityForcedSettleOrderApproveMainRequest request = 
                new WEB3AdminEquityForcedSettleOrderApproveMainRequest();
            
            String[] l_strList = new String[3];
            l_strList[0] = "";
            request.threadNo = new Long(123456789L);
            request.institutionCode = "123";
            request.rangeFrom = new Long(1234L);
            request.rangeTo = new Long(4321L);
            request.approveType = "0";
            request.orderIdList = l_strList;
            request.administratorId = new Long( 11111111111L);
            
            WEB3AsynAdminEquityForcedSettleOrderApproveServiceImpl l_asynAdminEquityForcedSettleOrderApproveServiceImpl = 
                new WEB3AsynAdminEquityForcedSettleOrderApproveServiceImpl(request);
            
            l_asynAdminEquityForcedSettleOrderApproveServiceImpl.run();
            
            QueryProcessor l_queryProcessor = Processors.getDefaultProcessor();
            List l_listOnlineRunStatusParams = l_queryProcessor.doFindAllQuery(OnlineRunStatusParams.TYPE);
            OnlineRunStatusParams l_onlineRunStatusParams = (OnlineRunStatusParams)l_listOnlineRunStatusParams.get(0);
            
            List l_listDaemonTriggerParams = l_queryProcessor.doFindAllQuery(DaemonTriggerParams.TYPE);
            DaemonTriggerParams l_daemonTriggerParams1 = (DaemonTriggerParams)l_listDaemonTriggerParams.get(0);
            
            assertEquals("1" , l_onlineRunStatusParams.getRunStatusDiv());
            assertEquals("0" , l_daemonTriggerParams1.getTriggerStatus());
        }
        catch (Exception l_ex)
        {
            log.error(TEST_END + STR_METHOD_NAME , l_ex);
            log.exiting(TEST_END + STR_METHOD_NAME);
            
            fail();
        }
    }
    
    public void testRun_0003()
    {
        String STR_METHOD_NAME = ".testRun_0003()";
        log.entering(TEST_START + STR_METHOD_NAME);
        
        DaemonTriggerParams l_daemonTriggerParams = new DaemonTriggerParams();
        l_daemonTriggerParams.setTriggerType("1");
        l_daemonTriggerParams.setThreadNo(123456789L);
        l_daemonTriggerParams.setOrderRequestNumber("12");
        l_daemonTriggerParams.setRangeFrom(123456789L);
        l_daemonTriggerParams.setRangeTo(234567890L);
        l_daemonTriggerParams.setTriggerStatus("3");
        l_daemonTriggerParams.setTriggerDate(GtlUtils.getTradingSystem().getBizDate());
        
        EqtypeOrderUnitParams l_eqtypeOrderUnitParams = TestDBUtility.getEqtypeOrderUnitRow();
        l_eqtypeOrderUnitParams.setOrderUnitId(123456789L);
        l_eqtypeOrderUnitParams.setForcedSettleReasonType("1");
        l_eqtypeOrderUnitParams.setOrderOpenStatus(OrderOpenStatusEnum.OPEN);
        l_eqtypeOrderUnitParams.setApproveStatusType(WEB3ApproveStatusType.UNAPPROVED);
        l_eqtypeOrderUnitParams.setTaxType(TaxTypeEnum.UNDEFINED);
        
        EqtypeClosingContractSpecParams l_eqtypeClosingContractSpecParams = 
            TestDBUtility.getEqtypeClosingContractSpecRow();
        l_eqtypeClosingContractSpecParams.setOrderUnitId(123456789L);
        l_eqtypeClosingContractSpecParams.setContractId(123456789L);
        
        EqtypeContractParams l_eqtypeContractParams = TestDBUtility.getEqtypeContractRow();
        l_eqtypeContractParams.setContractId(123456789L);
        l_eqtypeContractParams.setTaxType(TaxTypeEnum.UNDEFINED);
        
        BranchParams l_branchParams = TestDBUtility.getBranchRow();
        l_branchParams.setBranchId(33381L);
        l_branchParams.setBranchCode("123");
        
        MarketParams l_marketParams = TestDBUtility.getMarketRow();
        l_marketParams.setMarketId(3303L);
        l_marketParams.setMarketCode("N1");
        
        AdministratorParams l_administratorParams = TestDBUtility.getAdministratorRow();
        l_administratorParams.setAdministratorId(11111111111L);
        try
        {
            TestDBUtility.deleteAllAndCommit(DaemonTriggerParams.TYPE);
            TestDBUtility.insertWithDelAndCommit(l_daemonTriggerParams);
            
            TestDBUtility.deleteAllAndCommit(l_eqtypeOrderUnitParams.getRowType());
            TestDBUtility.deleteAllAndCommit(l_eqtypeClosingContractSpecParams.getRowType());
            TestDBUtility.deleteAllAndCommit(l_eqtypeContractParams.getRowType());
            TestDBUtility.deleteAllAndCommit(l_branchParams.getRowType());
            TestDBUtility.deleteAllAndCommit(l_marketParams.getRowType());
            
            TestDBUtility.deleteAllAndCommit(AdministratorRow.TYPE);
            TestDBUtility.insertWithDelAndCommit(l_administratorParams);
            
            TestDBUtility.insertWithDelAndCommit(l_eqtypeOrderUnitParams);
            TestDBUtility.insertWithDelAndCommit(l_eqtypeClosingContractSpecParams);
            TestDBUtility.insertWithDelAndCommit(l_eqtypeContractParams);
            TestDBUtility.insertWithDelAndCommit(l_branchParams);
            TestDBUtility.insertWithDelAndCommit(l_marketParams);
            
            TestDBUtility.deleteAllAndCommit(OnlineRunStatusRow.TYPE);
            
            Services.overrideService(WEB3AdminEquityForcedSettleOrderApproveUnitService.class, new WEB3AdminEquityForcedSettleOrderApproveUnitServiceImplForTest());
            
            WEB3GentradeTradingTimeManagementForMock.mockSetTimestamp();
            
            WEB3AdminEquityForcedSettleOrderApproveMainRequest request = 
                new WEB3AdminEquityForcedSettleOrderApproveMainRequest();
            String[] l_strList = new String[3];
            l_strList[0] = "123456789";
            request.threadNo = new Long(123456789L);
            request.institutionCode = "0D";
            request.rangeFrom = new Long(333812512200L);
            request.rangeTo = new Long(333812512210L);
            request.approveType = "0";
            request.orderIdList = l_strList;
            request.administratorId = new Long( 11111111111L);
            
            WEB3AsynAdminEquityForcedSettleOrderApproveServiceImpl l_asynAdminEquityForcedSettleOrderApproveServiceImpl = 
                new WEB3AsynAdminEquityForcedSettleOrderApproveServiceImpl(request);
            
            l_asynAdminEquityForcedSettleOrderApproveServiceImpl.run();
            
            QueryProcessor l_queryProcessor = Processors.getDefaultProcessor();
            List l_listOnlineRunStatusParams = l_queryProcessor.doFindAllQuery(OnlineRunStatusParams.TYPE);
            OnlineRunStatusParams l_onlineRunStatusParams = (OnlineRunStatusParams)l_listOnlineRunStatusParams.get(0);
            
            assertEquals("9" , l_onlineRunStatusParams.getRunStatusDiv());
            
            Services.overrideService(WEB3AdminEquityForcedSettleOrderApproveUnitService.class, new WEB3AdminEquityForcedSettleOrderApproveUnitServiceImpl());
        }
        catch (Exception l_ex)
        {
            log.error(TEST_END + STR_METHOD_NAME , l_ex);
            log.exiting(TEST_END + STR_METHOD_NAME);
            
            fail();
        }
        log.exiting(STR_METHOD_NAME);
    }
    
    public void testRun_0004()
    {
        String STR_METHOD_NAME = ".testRun_0004()";
        log.entering(TEST_START + STR_METHOD_NAME);
        
        DaemonTriggerParams l_daemonTriggerParams = new DaemonTriggerParams();
        l_daemonTriggerParams.setTriggerType("1");
        l_daemonTriggerParams.setThreadNo(123456789L);
        l_daemonTriggerParams.setOrderRequestNumber("12");
        l_daemonTriggerParams.setRangeFrom(123456789L);
        l_daemonTriggerParams.setRangeTo(234567890L);
        l_daemonTriggerParams.setTriggerStatus("3");
        l_daemonTriggerParams.setTriggerDate(GtlUtils.getTradingSystem().getBizDate());
        
        EqtypeOrderUnitParams l_eqtypeOrderUnitParams = TestDBUtility.getEqtypeOrderUnitRow();
        l_eqtypeOrderUnitParams.setOrderUnitId(123456789L);
        l_eqtypeOrderUnitParams.setForcedSettleReasonType("1");
        l_eqtypeOrderUnitParams.setOrderOpenStatus(OrderOpenStatusEnum.OPEN);
        l_eqtypeOrderUnitParams.setApproveStatusType(WEB3ApproveStatusType.UNAPPROVED);
        l_eqtypeOrderUnitParams.setTaxType(TaxTypeEnum.UNDEFINED);
        
        EqtypeClosingContractSpecParams l_eqtypeClosingContractSpecParams = 
            TestDBUtility.getEqtypeClosingContractSpecRow();
        l_eqtypeClosingContractSpecParams.setOrderUnitId(123456789L);
        l_eqtypeClosingContractSpecParams.setContractId(123456789L);
        
        EqtypeContractParams l_eqtypeContractParams = TestDBUtility.getEqtypeContractRow();
        l_eqtypeContractParams.setContractId(123456789L);
        l_eqtypeContractParams.setTaxType(TaxTypeEnum.UNDEFINED);
        
        BranchParams l_branchParams = TestDBUtility.getBranchRow();
        l_branchParams.setBranchId(33381L);
        l_branchParams.setBranchCode("123");
        
        MarketParams l_marketParams = TestDBUtility.getMarketRow();
        l_marketParams.setMarketId(3303L);
        l_marketParams.setMarketCode("N1");
        
        try
        {
            TestDBUtility.deleteAllAndCommit(DaemonTriggerParams.TYPE);
            TestDBUtility.insertWithDelAndCommit(l_daemonTriggerParams);
            
            TestDBUtility.deleteAllAndCommit(l_eqtypeOrderUnitParams.getRowType());
            TestDBUtility.deleteAllAndCommit(l_eqtypeClosingContractSpecParams.getRowType());
            TestDBUtility.deleteAllAndCommit(l_eqtypeContractParams.getRowType());
            TestDBUtility.deleteAllAndCommit(l_branchParams.getRowType());
            TestDBUtility.deleteAllAndCommit(l_marketParams.getRowType());
            
            TestDBUtility.insertWithDelAndCommit(l_eqtypeOrderUnitParams);
            TestDBUtility.insertWithDelAndCommit(l_eqtypeClosingContractSpecParams);
            TestDBUtility.insertWithDelAndCommit(l_eqtypeContractParams);
            TestDBUtility.insertWithDelAndCommit(l_branchParams);
            TestDBUtility.insertWithDelAndCommit(l_marketParams);
            
            TestDBUtility.deleteAllAndCommit(OnlineRunStatusRow.TYPE);
            
            Services.overrideService(WEB3AdminEquityForcedSettleOrderApproveUnitService.class, new WEB3AdminEquityForcedSettleOrderApproveUnitServiceImplForTest());
            
            WEB3GentradeTradingTimeManagementForMock.mockSetTimestamp();
            
            WEB3AdminEquityForcedSettleOrderApproveMainRequest request = 
                new WEB3AdminEquityForcedSettleOrderApproveMainRequest();
            String[] l_strList = new String[3];
            l_strList[0] = "123456789";
            request.threadNo = new Long(123456789L);
            request.institutionCode = "0D";
            request.rangeFrom = new Long(333812512200L);
            request.rangeTo = new Long(333812512210L);
            request.approveType = "1";
            request.orderIdList = l_strList;
            request.administratorId = new Long( 11111111111L);
            
            WEB3AsynAdminEquityForcedSettleOrderApproveServiceImpl l_asynAdminEquityForcedSettleOrderApproveServiceImpl = 
                new WEB3AsynAdminEquityForcedSettleOrderApproveServiceImpl(request);
            
            l_asynAdminEquityForcedSettleOrderApproveServiceImpl.run();
            
            QueryProcessor l_queryProcessor = Processors.getDefaultProcessor();
            List l_listOnlineRunStatusParams = l_queryProcessor.doFindAllQuery(OnlineRunStatusParams.TYPE);
            OnlineRunStatusParams l_onlineRunStatusParams = (OnlineRunStatusParams)l_listOnlineRunStatusParams.get(0);
            
            assertEquals("9" , l_onlineRunStatusParams.getRunStatusDiv());
            
            Services.overrideService(WEB3AdminEquityForcedSettleOrderApproveUnitService.class, new WEB3AdminEquityForcedSettleOrderApproveUnitServiceImpl());
        }
        catch (Exception l_ex)
        {
            log.error(TEST_END + STR_METHOD_NAME , l_ex);
            log.exiting(TEST_END + STR_METHOD_NAME);
            
            fail();
        }
    }
    
    public class WEB3AdminEquityForcedSettleOrderApproveUnitServiceImplForTest extends WEB3AdminEquityForcedSettleOrderApproveUnitServiceImpl
    {
        public boolean execApprove(AdminEqForcedSettleOrderRow l_forcedSettleOrderRow)
        throws WEB3BaseException
        {
            final String STR_METHOD_NAME = "execApprove(AdminEqForcedSettleOrderRow)";
            log.entering(STR_METHOD_NAME);
            
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80017,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                "パラメータ値不正。");
        }
        
        public boolean execDisapprove(AdminEqForcedSettleOrderRow l_forcedSettleOrderRow)
        throws WEB3BaseException
        {
            final String STR_METHOD_NAME = "execDisapprove(AdminEqForcedSettleOrderRow)";
            log.entering(STR_METHOD_NAME);

            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80017,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                "パラメータ値不正。");
        }
    }

}
@
