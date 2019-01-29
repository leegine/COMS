head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.04.07.02.03.05;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	5444d9d078c63f4;
filename	WEB3AsynAdminIfoManualExpireServiceImplTest.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : （非同期）管理者・先物OP手動失効サービスImpl(WEB3AsynAdminIfoManualExpireServiceImpl.java)
Author Name      : Daiwa Institute of Research
Revision History : 2007/01/30　@謝旋(中訊) 新規作成
*/
package webbroker3.ifoadmin.service.delegate.stdimpls;

import java.sql.Timestamp;
import java.util.Calendar;
import java.util.List;

import com.fitechlabs.xtrade.kernel.boot.Services;
import com.fitechlabs.xtrade.kernel.interceptor.TransactionalInterceptor;
import com.fitechlabs.xtrade.kernel.util.ThreadLocalSystemAttributesRegistry;
import com.fitechlabs.xtrade.plugin.tc.gentrade.FinApp;
import com.fitechlabs.xtrade.plugin.tc.gentrade.GtlUtils;
import com.fitechlabs.xtrade.plugin.tc.gentrade.OrderTypeEnum;
import com.fitechlabs.xtrade.plugin.tc.gentrade.OrderUnit;
import com.fitechlabs.xtrade.plugin.tc.gentrade.ProductTypeEnum;
import com.fitechlabs.xtrade.plugin.tc.gentrade.SubAccountTypeEnum;
import com.fitechlabs.xtrade.plugin.tc.gentrade.TradingSystem;
import com.fitechlabs.xtrade.plugin.tc.gentrade.data.InstitutionParams;
import com.fitechlabs.xtrade.plugin.tc.gentrade.data.MainAccountParams;
import com.fitechlabs.xtrade.plugin.tc.gentrade.data.SubAccountParams;
import com.fitechlabs.xtrade.plugin.tc.xbifo.IfoOrderUnit;
import com.fitechlabs.xtrade.plugin.tc.xbifo.data.IfoOrderUnitParams;
import com.fitechlabs.xtrade.plugin.tc.xbifo.data.IfoProductParams;
import com.fitechlabs.xtrade.plugin.tc.xbifo.stdimpls.IfoContractOpenOrderUnitImpl;

import test.util.TestDBUtility;

import webbroker3.common.WEB3BaseException;
import webbroker3.common.WEB3BaseRuntimeException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.common.WEB3SystemLayerException;
import webbroker3.common.define.WEB3FuturesOptionDivDef;
import webbroker3.common.define.WEB3OnlineServiceDiv;
import webbroker3.gentrade.WEB3GentradeInstitution;
import webbroker3.gentrade.WEB3GentradeSubAccount;
import webbroker3.gentrade.WEB3GentradeTradingTimeManagement;
import webbroker3.gentrade.data.DaemonTriggerDao;
import webbroker3.gentrade.data.DaemonTriggerParams;
import webbroker3.gentrade.data.DaemonTriggerRow;
import webbroker3.gentrade.data.OnlineRunStatusDao;
import webbroker3.gentrade.data.OnlineRunStatusParams;
import webbroker3.gentrade.data.OnlineRunStatusRow;
import webbroker3.gentrade.data.TradingTimeParams;
import webbroker3.ifo.data.HostFotypeOrderAllParams;
import webbroker3.ifoadmin.message.WEB3AdminIfoLapseTargetOrderCondition;
import webbroker3.ifoadmin.message.WEB3AdminIfoManualLapseMainRequest;
import webbroker3.ifoadmin.service.delegate.stdimpls.WEB3AsynAdminIfoManualExpireServiceImpl.WEB3AdminIfoManualExpireDaemonTriggerTransactionCallback;
import webbroker3.ifoadmin.service.delegate.stdimpls.WEB3AsynAdminIfoManualExpireServiceImpl.WEB3AdminIfoManualExpireOnlineRunStatusTransactionCallback;
import webbroker3.ifoadmin.service.delegate.stdimpls.WEB3AsynAdminIfoManualExpireServiceImpl.WEB3AdminIfoManualExpireTransactionCallback;
import webbroker3.mock.TestBaseForMock;
import webbroker3.mock.util.WEB3MockObjectParamsValue;
import webbroker3.tradingpower.WEB3TPTradingPowerReCalcService;
import webbroker3.tradingpower.WEB3TPTradingPowerReCalcServiceImpl;
import webbroker3.tradingpower.WEB3TPTradingPowerServiceInterceptor;
import webbroker3.util.WEB3LogUtility;

/**
 * (（非同期）管理者・先物OP手動失効サービスImpl)<BR>
 *（WEB3AsynAdminIfoManualExpireServiceImpl）<BR>
 *（非同期）管理者・先物OP手動失効サービス実装クラス<BR>
 * <BR>
 * 失効処理を非同期で行う。<BR>
 * <BR>
 * @@author 謝旋
 * @@version 1.0
 */
public class WEB3AsynAdminIfoManualExpireServiceImplTest extends TestBaseForMock
{
    /**
     * ログユーティリティ。
     */
    private static WEB3LogUtility log = WEB3LogUtility.getInstance(
        WEB3AsynAdminIfoManualExpireServiceImplTest.class);

    public WEB3AsynAdminIfoManualExpireServiceImplTest(String arg0)
    {
        super(arg0);
    }

    protected void setUp() throws Exception
    {
        super.setUp();
        
        Calendar l_calendar = Calendar.getInstance();
        l_calendar.set(Calendar.YEAR,2007);
        l_calendar.set(Calendar.MONTH,2);
        l_calendar.set(Calendar.DAY_OF_MONTH,9);
        l_calendar.set(Calendar.HOUR_OF_DAY,15);
        l_calendar.set(Calendar.MINUTE,00);
        l_calendar.set(Calendar.SECOND,01);
        
        Timestamp l_tsBizDate = new Timestamp(l_calendar.getTimeInMillis());
        
        TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                "com.fitechlabs.xtrade.plugin.tc.gentrade.stdimpls.TradingSystemImpl",
                "getSystemTimestamp",
                new Class[] {},
                new Timestamp(l_tsBizDate.getTime()));
    }

    protected void tearDown() throws Exception
    {
        super.tearDown();
    }
    
    public void testRun_0001()
    {
        String STR_METHOD_NAME = "testRun_0001()";
        log.entering(TEST_START + STR_METHOD_NAME);
        
        DaemonTriggerParams l_daemonTriggerParams = new DaemonTriggerParams();
        l_daemonTriggerParams.setTriggerType("1");
        l_daemonTriggerParams.setThreadNo(123L);
        l_daemonTriggerParams.setOrderRequestNumber("12");
        l_daemonTriggerParams.setRangeFrom(123456L);
        l_daemonTriggerParams.setRangeTo(123456L);
        l_daemonTriggerParams.setTriggerStatus("1");
        try
        {
            TestDBUtility.deleteAll(l_daemonTriggerParams.getRowType());
            TestDBUtility.insertWithDel(l_daemonTriggerParams);
            
        } catch (WEB3BaseException l_ex)
        {
            log.error(TEST_END + STR_METHOD_NAME , l_ex);
            log.exiting(TEST_END + STR_METHOD_NAME);
            fail();
        }
        
        WEB3AdminIfoManualLapseMainRequest request = new WEB3AdminIfoManualLapseMainRequest();
        request.threadNo = new Long(123456789L);
        WEB3AsynAdminIfoManualExpireServiceImpl l_web3AsynAdminIfoManualExpireServiceImpl = 
            new WEB3AsynAdminIfoManualExpireServiceImpl(request);
        
        try
        {
            l_web3AsynAdminIfoManualExpireServiceImpl.run();
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
        String STR_METHOD_NAME = "testRun_0002()";
        log.entering(TEST_START + STR_METHOD_NAME);

        DaemonTriggerParams l_daemonTriggerParams = new DaemonTriggerParams();
        l_daemonTriggerParams.setTriggerType("1");
        l_daemonTriggerParams.setThreadNo(123L);
        l_daemonTriggerParams.setOrderRequestNumber("12");
        l_daemonTriggerParams.setRangeFrom(123456L);
        l_daemonTriggerParams.setRangeTo(123456L);
        l_daemonTriggerParams.setTriggerStatus("1");
        
        OnlineRunStatusParams l_onlineRunStatusParams = new OnlineRunStatusParams();
        l_onlineRunStatusParams.setInstitutionCode("123");
        l_onlineRunStatusParams.setProductType(ProductTypeEnum.IFO);
        l_onlineRunStatusParams.setFutureOptionDiv(WEB3FuturesOptionDivDef.DEFAULT);
        l_onlineRunStatusParams.setOnlineServiceDiv(WEB3OnlineServiceDiv.MANUAL_EXPIRE);
        // 5：処理中
        l_onlineRunStatusParams.setRunStatusDiv("5");
        l_onlineRunStatusParams.setAccountIdFrom(123456L);
        l_onlineRunStatusParams.setAccountIdTo(123456L);
        try
        {
            TestDBUtility.deleteAll(l_daemonTriggerParams.getRowType());
            TestDBUtility.insertWithDel(l_daemonTriggerParams);
            TestDBUtility.deleteAllAndCommit(l_onlineRunStatusParams.getRowType());
            TestDBUtility.insertWithDelAndCommit(l_onlineRunStatusParams);
        } 
        catch (WEB3BaseException l_ex)
        {
            log.error(TEST_END + STR_METHOD_NAME , l_ex);
            log.exiting(TEST_END + STR_METHOD_NAME);
            fail();
        }
        
        WEB3AdminIfoManualLapseMainRequest request = new WEB3AdminIfoManualLapseMainRequest();
        request.threadNo = new Long(123L);
        request.institutionCode = "123";
        request.accountIdFrom = new Long(123456L);
        request.accountIdTo = new Long(123456L);
        WEB3AsynAdminIfoManualExpireServiceImpl l_web3AsynAdminIfoManualExpireServiceImpl = 
            new WEB3AsynAdminIfoManualExpireServiceImpl(request);
        
        try
        {
            l_web3AsynAdminIfoManualExpireServiceImpl.run();
            
            log.exiting(TEST_END + STR_METHOD_NAME);
            fail();
        }
        catch (WEB3BaseRuntimeException l_ex)
        {
            assertEquals(WEB3ErrorCatalog.BUSINESS_ERROR_01992 , l_ex.getErrorInfo());
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
        String STR_METHOD_NAME = "testRun_0003()";
        log.entering(TEST_START + STR_METHOD_NAME);

        DaemonTriggerParams l_daemonTriggerParams = new DaemonTriggerParams();
        l_daemonTriggerParams.setTriggerType("1");
        l_daemonTriggerParams.setThreadNo(123L);
        l_daemonTriggerParams.setOrderRequestNumber("12");
        l_daemonTriggerParams.setRangeFrom(123456L);
        l_daemonTriggerParams.setRangeTo(123456L);
        l_daemonTriggerParams.setTriggerStatus("1");
        
        OnlineRunStatusParams l_onlineRunStatusParams = new OnlineRunStatusParams();
        l_onlineRunStatusParams.setInstitutionCode("123");
        l_onlineRunStatusParams.setProductType(ProductTypeEnum.IFO);
        l_onlineRunStatusParams.setFutureOptionDiv(WEB3FuturesOptionDivDef.DEFAULT);
        l_onlineRunStatusParams.setOnlineServiceDiv(WEB3OnlineServiceDiv.MANUAL_EXPIRE);
        // 1：処理済
        l_onlineRunStatusParams.setRunStatusDiv("1");
        l_onlineRunStatusParams.setAccountIdFrom(123456L);
        l_onlineRunStatusParams.setAccountIdTo(123456L);
        
        // mainAccount InstitutionCode = "0D"|BranchCode = "381"|AccountCode = "2512246"
        MainAccountParams l_mainAccountParams = TestDBUtility.getMainAccountRow();
        
        IfoOrderUnitParams l_ifoOrderUnitParams = TestDBUtility.getIfoOrderUnitRow();
        l_ifoOrderUnitParams.setAccountId(333812512246L);
        l_ifoOrderUnitParams.setBranchId(33381L);

        IfoOrderUnitParams l_ifoOrderUnitParams1 = TestDBUtility.getIfoOrderUnitRow();
        l_ifoOrderUnitParams1.setOrderUnitId(1002L);
        l_ifoOrderUnitParams1.setAccountId(333812512255L);
        l_ifoOrderUnitParams1.setBranchId(33381L);
        l_ifoOrderUnitParams1.setOrderRequestNumber("000003007");

        IfoOrderUnitParams l_ifoOrderUnitParams_setBranchId = TestDBUtility.getIfoOrderUnitRow();
        l_ifoOrderUnitParams_setBranchId.setOrderUnitId(1003L);
        l_ifoOrderUnitParams_setBranchId.setAccountId(333812512246L);
        l_ifoOrderUnitParams_setBranchId.setBranchId(33382L);
        l_ifoOrderUnitParams_setBranchId.setOrderRequestNumber("000003008");
        
        IfoOrderUnitParams l_ifoOrderUnitParams_setProductid = TestDBUtility.getIfoOrderUnitRow();
        l_ifoOrderUnitParams_setProductid.setOrderUnitId(1004L);
        l_ifoOrderUnitParams_setProductid.setAccountId(333812512246L);
        l_ifoOrderUnitParams_setProductid.setBranchId(33381L);
        l_ifoOrderUnitParams_setProductid.setProductId(1006169090019L);
        l_ifoOrderUnitParams_setProductid.setOrderRequestNumber("000003009");
        
        IfoOrderUnitParams l_ifoOrderUnitParams_setOrdertype = TestDBUtility.getIfoOrderUnitRow();
        l_ifoOrderUnitParams_setOrdertype.setOrderUnitId(1005L);
        l_ifoOrderUnitParams_setOrdertype.setAccountId(333812512246L);
        l_ifoOrderUnitParams_setOrdertype.setBranchId(33381L);
        l_ifoOrderUnitParams_setOrdertype.setOrderType(OrderTypeEnum.IDX_FUTURES_SELL_TO_OPEN);
        l_ifoOrderUnitParams_setOrdertype.setOrderRequestNumber("000003040");
        
        IfoOrderUnitParams l_ifoOrderUnitParams_setAccountid = TestDBUtility.getIfoOrderUnitRow();
        l_ifoOrderUnitParams_setAccountid.setOrderUnitId(1006L);
        l_ifoOrderUnitParams_setAccountid.setBranchId(33381L);
        l_ifoOrderUnitParams_setAccountid.setAccountId(333812512247L);
        l_ifoOrderUnitParams_setAccountid.setOrderRequestNumber("000003041");
        
        /* Table HostFotypeOrderAllParams  */
        HostFotypeOrderAllParams l_hostFotypeOrderAllParams = TestDBUtility.getHostFotypeOrderAllRow();
        l_hostFotypeOrderAllParams.setStatus("0");
        l_hostFotypeOrderAllParams.setOrderRequestNumber("000003006");
        
        InstitutionParams l_institutionParams = new InstitutionParams();
        WEB3GentradeInstitution l_institution = null;
        try
        {
            TestDBUtility.deleteAll(l_daemonTriggerParams.getRowType());
            TestDBUtility.insertWithDel(l_daemonTriggerParams);
            TestDBUtility.deleteAllAndCommit(l_onlineRunStatusParams.getRowType());
            TestDBUtility.insertWithDelAndCommit(l_onlineRunStatusParams);
            
            TestDBUtility.deleteAll(l_mainAccountParams.getRowType());
            TestDBUtility.insertWithDel(l_mainAccountParams);
            
            TestDBUtility.deleteAll(l_ifoOrderUnitParams.getRowType());
            TestDBUtility.insertWithDel(l_ifoOrderUnitParams);
            TestDBUtility.insertWithDel(l_ifoOrderUnitParams1);
            TestDBUtility.insertWithDel(l_ifoOrderUnitParams_setBranchId);
            TestDBUtility.insertWithDel(l_ifoOrderUnitParams_setProductid);
            TestDBUtility.insertWithDel(l_ifoOrderUnitParams_setOrdertype);
            TestDBUtility.insertWithDel(l_ifoOrderUnitParams_setAccountid);
            
            TestDBUtility.deleteAll(TestDBUtility.getInstitutionRow().getRowType());
            TestDBUtility.insertWithDel(TestDBUtility.getInstitutionRow());
            
            TestDBUtility.deleteAll(TestDBUtility.getBranchRow().getRowType());
            TestDBUtility.insertWithDel(TestDBUtility.getBranchRow());
            
            TestDBUtility.deleteAllAndCommit(l_hostFotypeOrderAllParams.getRowType());
            TestDBUtility.insertWithDelAndCommit(l_hostFotypeOrderAllParams);
            
            l_institution = new WEB3GentradeInstitution(l_institutionParams);
        }
        catch (Exception l_ex)
        {
            log.error(TEST_END + STR_METHOD_NAME , l_ex);
            log.exiting(TEST_END + STR_METHOD_NAME);
            fail();            
        }

        TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
            "webbroker3.gentrade.WEB3GentradeAccountManager",
            "getInstitution",
            new Class[] {String.class},
            l_institution);
        
        WEB3AdminIfoManualLapseMainRequest request = new WEB3AdminIfoManualLapseMainRequest();
        request.threadNo = new Long(123L);
        request.institutionCode = "0D";
        request.accountIdFrom = new Long(123456L);
        request.accountIdTo = new Long(123456L);
        
        WEB3AdminIfoLapseTargetOrderCondition l_ifoLapseTargetOrderCondition = 
            new WEB3AdminIfoLapseTargetOrderCondition();
        l_ifoLapseTargetOrderCondition.targetProductCode = "";
        l_ifoLapseTargetOrderCondition.delivaryMonth = "";
        l_ifoLapseTargetOrderCondition.strikePrice = "";
        l_ifoLapseTargetOrderCondition.opProductType = "";
        
        request.ifoLapseTargetOrderCondition = l_ifoLapseTargetOrderCondition;
        WEB3AsynAdminIfoManualExpireServiceImpl l_web3AsynAdminIfoManualExpireServiceImpl = 
            new WEB3AsynAdminIfoManualExpireServiceImpl(request);
        
        try
        {
            l_web3AsynAdminIfoManualExpireServiceImpl.run();
            
            assertNull(
                ThreadLocalSystemAttributesRegistry.getAttribute(WEB3GentradeTradingTimeManagement.TIMESTAMP_TAG));
        }
        catch (WEB3BaseRuntimeException l_ex)
        {
            assertEquals(WEB3ErrorCatalog.BUSINESS_ERROR_01992 , l_ex.getErrorInfo());
        }
        catch (Exception l_ex)
        {
            log.error(TEST_END + STR_METHOD_NAME , l_ex);
            log.exiting(TEST_END + STR_METHOD_NAME);
            fail();
        }
    }
    
    public void testRun_0004()
    {
        String STR_METHOD_NAME = "testRun_0004()";
        log.entering(TEST_START + STR_METHOD_NAME);

        DaemonTriggerParams l_daemonTriggerParams = new DaemonTriggerParams();
        l_daemonTriggerParams.setTriggerType("1");
        l_daemonTriggerParams.setThreadNo(123L);
        l_daemonTriggerParams.setOrderRequestNumber("12");
        l_daemonTriggerParams.setRangeFrom(123456L);
        l_daemonTriggerParams.setRangeTo(123456L);
        l_daemonTriggerParams.setTriggerStatus("1");
        
        OnlineRunStatusParams l_onlineRunStatusParams = new OnlineRunStatusParams();
        l_onlineRunStatusParams.setInstitutionCode("123");
        l_onlineRunStatusParams.setProductType(ProductTypeEnum.IFO);
        l_onlineRunStatusParams.setFutureOptionDiv(WEB3FuturesOptionDivDef.DEFAULT);
        l_onlineRunStatusParams.setOnlineServiceDiv(WEB3OnlineServiceDiv.MANUAL_EXPIRE);
        // 1：処理済
        l_onlineRunStatusParams.setRunStatusDiv("1");
        l_onlineRunStatusParams.setAccountIdFrom(123456L);
        l_onlineRunStatusParams.setAccountIdTo(123456L);
        
        // mainAccount InstitutionCode = "0D"|BranchCode = "381"|AccountCode = "2512246"
        MainAccountParams l_mainAccountParams = TestDBUtility.getMainAccountRow();
        
        IfoOrderUnitParams l_ifoOrderUnitParams = TestDBUtility.getIfoOrderUnitRow();
        l_ifoOrderUnitParams.setAccountId(333812512246L);
        l_ifoOrderUnitParams.setBranchId(33381L);

        IfoOrderUnitParams l_ifoOrderUnitParams1 = TestDBUtility.getIfoOrderUnitRow();
        l_ifoOrderUnitParams1.setOrderUnitId(1002L);
        l_ifoOrderUnitParams1.setAccountId(333812512255L);
        l_ifoOrderUnitParams1.setBranchId(33381L);
        l_ifoOrderUnitParams1.setOrderRequestNumber("000003007");

        IfoOrderUnitParams l_ifoOrderUnitParams_setBranchId = TestDBUtility.getIfoOrderUnitRow();
        l_ifoOrderUnitParams_setBranchId.setOrderUnitId(1003L);
        l_ifoOrderUnitParams_setBranchId.setAccountId(333812512246L);
        l_ifoOrderUnitParams_setBranchId.setBranchId(33382L);
        l_ifoOrderUnitParams_setBranchId.setOrderRequestNumber("000003008");
        
        IfoOrderUnitParams l_ifoOrderUnitParams_setOrdertype = TestDBUtility.getIfoOrderUnitRow();
        l_ifoOrderUnitParams_setOrdertype.setOrderUnitId(1005L);
        l_ifoOrderUnitParams_setOrdertype.setAccountId(333812512246L);
        l_ifoOrderUnitParams_setOrdertype.setBranchId(33381L);
        l_ifoOrderUnitParams_setOrdertype.setOrderType(OrderTypeEnum.IDX_FUTURES_SELL_TO_OPEN);
        l_ifoOrderUnitParams_setOrdertype.setOrderRequestNumber("000003040");
        
        IfoOrderUnitParams l_ifoOrderUnitParams_setAccountid = TestDBUtility.getIfoOrderUnitRow();
        l_ifoOrderUnitParams_setAccountid.setOrderUnitId(1006L);
        l_ifoOrderUnitParams_setAccountid.setBranchId(33381L);
        l_ifoOrderUnitParams_setAccountid.setAccountId(333812512247L);
        l_ifoOrderUnitParams_setAccountid.setOrderRequestNumber("000003041");
        
        /* Table HostFotypeOrderAllParams  */
        HostFotypeOrderAllParams l_hostFotypeOrderAllParams = TestDBUtility.getHostFotypeOrderAllRow();
        l_hostFotypeOrderAllParams.setStatus("0");
        l_hostFotypeOrderAllParams.setOrderRequestNumber("000003006");
        
        InstitutionParams l_institutionParams = new InstitutionParams();
        WEB3GentradeInstitution l_institution = null;
        try
        {
            TestDBUtility.deleteAll(l_daemonTriggerParams.getRowType());
            TestDBUtility.insertWithDel(l_daemonTriggerParams);
            TestDBUtility.deleteAllAndCommit(l_onlineRunStatusParams.getRowType());
            TestDBUtility.insertWithDelAndCommit(l_onlineRunStatusParams);
            
            TestDBUtility.deleteAll(l_mainAccountParams.getRowType());
            TestDBUtility.insertWithDel(l_mainAccountParams);
            
            TestDBUtility.deleteAll(l_ifoOrderUnitParams.getRowType());
            TestDBUtility.insertWithDel(l_ifoOrderUnitParams);
            TestDBUtility.insertWithDel(l_ifoOrderUnitParams1);
            TestDBUtility.insertWithDel(l_ifoOrderUnitParams_setBranchId);
            TestDBUtility.insertWithDel(l_ifoOrderUnitParams_setOrdertype);
            TestDBUtility.insertWithDel(l_ifoOrderUnitParams_setAccountid);
            
            TestDBUtility.deleteAll(TestDBUtility.getInstitutionRow().getRowType());
            TestDBUtility.insertWithDel(TestDBUtility.getInstitutionRow());
            
            TestDBUtility.deleteAll(TestDBUtility.getBranchRow().getRowType());
            TestDBUtility.insertWithDel(TestDBUtility.getBranchRow());
            
            TestDBUtility.deleteAllAndCommit(l_hostFotypeOrderAllParams.getRowType());
            TestDBUtility.insertWithDelAndCommit(l_hostFotypeOrderAllParams);
            
            l_institution = new WEB3GentradeInstitution(l_institutionParams);
        } 
        catch (WEB3BaseException l_ex)
        {
            log.error(TEST_END + STR_METHOD_NAME , l_ex);
            log.exiting(TEST_END + STR_METHOD_NAME);
            fail();
        }
        catch (Exception l_ex)
        {
            log.error(TEST_END + STR_METHOD_NAME , l_ex);
            log.exiting(TEST_END + STR_METHOD_NAME);
            fail();            
        }

        TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
            "webbroker3.gentrade.WEB3GentradeAccountManager",
            "getInstitution",
            new Class[] {String.class},
            l_institution);
        
        WEB3AdminIfoManualLapseMainRequest request = new WEB3AdminIfoManualLapseMainRequest();
        request.threadNo = new Long(123L);
        request.institutionCode = "0D";
        request.accountIdFrom = new Long(123456L);
        request.accountIdTo = new Long(123456L);
        
        WEB3AdminIfoLapseTargetOrderCondition l_ifoLapseTargetOrderCondition = 
            new WEB3AdminIfoLapseTargetOrderCondition();
        l_ifoLapseTargetOrderCondition.targetProductCode = "";
        l_ifoLapseTargetOrderCondition.delivaryMonth = "";
        l_ifoLapseTargetOrderCondition.strikePrice = "";
        l_ifoLapseTargetOrderCondition.opProductType = "";
        String[] l_branchCode = {"381"};
        l_ifoLapseTargetOrderCondition.branchCode = l_branchCode;
        String[] l_tradingTypeList = {"601"};
        l_ifoLapseTargetOrderCondition.tradingTypeList = l_tradingTypeList;
        l_ifoLapseTargetOrderCondition.accountCode = "2512246";
        
        request.ifoLapseTargetOrderCondition = l_ifoLapseTargetOrderCondition;
        request.accountIdFrom = new Long(333812512246L);
        request.accountIdTo = new Long(333812512248L);
        request.institutionCode = "0D";
        WEB3AsynAdminIfoManualExpireServiceImpl l_web3AsynAdminIfoManualExpireServiceImpl = 
            new WEB3AsynAdminIfoManualExpireServiceImpl(request);
        
        try
        {
            l_web3AsynAdminIfoManualExpireServiceImpl.run();
            
            OnlineRunStatusRow l_onlineRunStatusRow = 
                OnlineRunStatusDao.findRowByPk("0D" , ProductTypeEnum.IFO , "0" , "3" , 333812512246L);
            
            assertEquals("9" , l_onlineRunStatusRow.getRunStatusDiv());
        }
        catch (Exception l_ex)
        {
            log.error(TEST_END + STR_METHOD_NAME , l_ex);
            log.exiting(TEST_END + STR_METHOD_NAME);
            fail();
        }
    }
    
    public void testRun_0005()
    {
        String STR_METHOD_NAME = "testRun_0005()";
        log.entering(TEST_START + STR_METHOD_NAME);

        DaemonTriggerParams l_daemonTriggerParams = new DaemonTriggerParams();
        l_daemonTriggerParams.setTriggerType("1");
        l_daemonTriggerParams.setThreadNo(123L);
        l_daemonTriggerParams.setOrderRequestNumber("12");
        l_daemonTriggerParams.setRangeFrom(123456L);
        l_daemonTriggerParams.setRangeTo(123456L);
        l_daemonTriggerParams.setTriggerStatus("1");
        
        OnlineRunStatusParams l_onlineRunStatusParams = new OnlineRunStatusParams();
        l_onlineRunStatusParams.setInstitutionCode("123");
        l_onlineRunStatusParams.setProductType(ProductTypeEnum.IFO);
        l_onlineRunStatusParams.setFutureOptionDiv(WEB3FuturesOptionDivDef.DEFAULT);
        l_onlineRunStatusParams.setOnlineServiceDiv(WEB3OnlineServiceDiv.MANUAL_EXPIRE);
        // 1：処理済
        l_onlineRunStatusParams.setRunStatusDiv("1");
        l_onlineRunStatusParams.setAccountIdFrom(123456L);
        l_onlineRunStatusParams.setAccountIdTo(123456L);
        
        // mainAccount InstitutionCode = "0D"|BranchCode = "381"|AccountCode = "2512246"
        MainAccountParams l_mainAccountParams = TestDBUtility.getMainAccountRow();
        
        IfoOrderUnitParams l_ifoOrderUnitParams = TestDBUtility.getIfoOrderUnitRow();
        l_ifoOrderUnitParams.setAccountId(333812512246L);
        l_ifoOrderUnitParams.setBranchId(33381L);

        IfoOrderUnitParams l_ifoOrderUnitParams1 = TestDBUtility.getIfoOrderUnitRow();
        l_ifoOrderUnitParams1.setOrderUnitId(1002L);
        l_ifoOrderUnitParams1.setAccountId(333812512255L);
        l_ifoOrderUnitParams1.setBranchId(33381L);
        l_ifoOrderUnitParams1.setOrderRequestNumber("000003007");

        IfoOrderUnitParams l_ifoOrderUnitParams_setBranchId = TestDBUtility.getIfoOrderUnitRow();
        l_ifoOrderUnitParams_setBranchId.setOrderUnitId(1003L);
        l_ifoOrderUnitParams_setBranchId.setAccountId(333812512246L);
        l_ifoOrderUnitParams_setBranchId.setBranchId(33382L);
        l_ifoOrderUnitParams_setBranchId.setOrderRequestNumber("000003008");
        
        IfoOrderUnitParams l_ifoOrderUnitParams_setOrdertype = TestDBUtility.getIfoOrderUnitRow();
        l_ifoOrderUnitParams_setOrdertype.setOrderUnitId(1005L);
        l_ifoOrderUnitParams_setOrdertype.setAccountId(333812512246L);
        l_ifoOrderUnitParams_setOrdertype.setBranchId(33381L);
        l_ifoOrderUnitParams_setOrdertype.setOrderType(OrderTypeEnum.IDX_FUTURES_SELL_TO_OPEN);
        l_ifoOrderUnitParams_setOrdertype.setOrderRequestNumber("000003040");
        
        IfoOrderUnitParams l_ifoOrderUnitParams_setAccountid = TestDBUtility.getIfoOrderUnitRow();
        l_ifoOrderUnitParams_setAccountid.setOrderUnitId(1006L);
        l_ifoOrderUnitParams_setAccountid.setBranchId(33381L);
        l_ifoOrderUnitParams_setAccountid.setAccountId(333812512247L);
        l_ifoOrderUnitParams_setAccountid.setOrderRequestNumber("000003041");
        
        /* Table HostFotypeOrderAllParams  */
        HostFotypeOrderAllParams l_hostFotypeOrderAllParams = TestDBUtility.getHostFotypeOrderAllRow();
        l_hostFotypeOrderAllParams.setStatus("0");
        l_hostFotypeOrderAllParams.setOrderRequestNumber("000003006");
        
        InstitutionParams l_institutionParams = new InstitutionParams();
        WEB3GentradeInstitution l_institution = null;
        try
        {
            TestDBUtility.deleteAll(l_daemonTriggerParams.getRowType());
            TestDBUtility.insertWithDel(l_daemonTriggerParams);
            TestDBUtility.deleteAllAndCommit(l_onlineRunStatusParams.getRowType());
            TestDBUtility.insertWithDelAndCommit(l_onlineRunStatusParams);
            
            TestDBUtility.deleteAll(l_mainAccountParams.getRowType());
            TestDBUtility.insertWithDel(l_mainAccountParams);
            
            TestDBUtility.deleteAll(l_ifoOrderUnitParams.getRowType());
            TestDBUtility.insertWithDel(l_ifoOrderUnitParams);
            TestDBUtility.insertWithDel(l_ifoOrderUnitParams1);
            TestDBUtility.insertWithDel(l_ifoOrderUnitParams_setBranchId);
            TestDBUtility.insertWithDel(l_ifoOrderUnitParams_setOrdertype);
            TestDBUtility.insertWithDel(l_ifoOrderUnitParams_setAccountid);
            
            TestDBUtility.deleteAll(TestDBUtility.getInstitutionRow().getRowType());
            TestDBUtility.insertWithDel(TestDBUtility.getInstitutionRow());
            
            TestDBUtility.deleteAll(TestDBUtility.getBranchRow().getRowType());
            TestDBUtility.insertWithDel(TestDBUtility.getBranchRow());
            
            TestDBUtility.deleteAllAndCommit(l_hostFotypeOrderAllParams.getRowType());
            TestDBUtility.insertWithDelAndCommit(l_hostFotypeOrderAllParams);
            
            l_institution = new WEB3GentradeInstitution(l_institutionParams);
        } 
        catch (WEB3BaseException l_ex)
        {
            log.error(TEST_END + STR_METHOD_NAME , l_ex);
            log.exiting(TEST_END + STR_METHOD_NAME);
            fail();
        }
        catch (Exception l_ex)
        {
            log.error(TEST_END + STR_METHOD_NAME , l_ex);
            log.exiting(TEST_END + STR_METHOD_NAME);
            fail();            
        }

        TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
            "webbroker3.gentrade.WEB3GentradeAccountManager",
            "getInstitution",
            new Class[] {String.class},
            l_institution);
        
        WEB3AdminIfoManualLapseMainRequest request = new WEB3AdminIfoManualLapseMainRequest();
        request.threadNo = new Long(123L);
        request.institutionCode = "0D";
        request.accountIdFrom = new Long(123456L);
        request.accountIdTo = new Long(123456L);
        
        WEB3AdminIfoLapseTargetOrderCondition l_ifoLapseTargetOrderCondition = 
            new WEB3AdminIfoLapseTargetOrderCondition();
        l_ifoLapseTargetOrderCondition.targetProductCode = "";
        l_ifoLapseTargetOrderCondition.delivaryMonth = "";
        l_ifoLapseTargetOrderCondition.strikePrice = "";
        l_ifoLapseTargetOrderCondition.opProductType = "";
        String[] l_branchCode = {"381"};
        l_ifoLapseTargetOrderCondition.branchCode = l_branchCode;
        String[] l_tradingTypeList = {"601"};
        l_ifoLapseTargetOrderCondition.tradingTypeList = l_tradingTypeList;
        l_ifoLapseTargetOrderCondition.accountCode = "2512246";
        
        request.ifoLapseTargetOrderCondition = l_ifoLapseTargetOrderCondition;
        request.accountIdFrom = new Long(333812512246L);
        request.accountIdTo = new Long(333812512248L);
        request.institutionCode = "0D";
        WEB3AsynAdminIfoManualExpireServiceImpl l_web3AsynAdminIfoManualExpireServiceImpl = 
            new WEB3AsynAdminIfoManualExpireServiceImpl(request);
        
        try
        {
            l_web3AsynAdminIfoManualExpireServiceImpl.run();
            
            DaemonTriggerRow l_daemonTriggerRow = 
                DaemonTriggerDao.findRowByPk(123L);
            
            assertEquals("0" , l_daemonTriggerRow.getTriggerStatus());
            assertEquals(GtlUtils.getSystemTimestamp().getYear() , l_daemonTriggerRow.getTriggerDate().getYear());
            assertEquals(GtlUtils.getSystemTimestamp().getMonth() , l_daemonTriggerRow.getTriggerDate().getMonth());
            assertEquals(GtlUtils.getSystemTimestamp().getDay() , l_daemonTriggerRow.getTriggerDate().getDay());
        }
        catch (Exception l_ex)
        {
            log.error(TEST_END + STR_METHOD_NAME , l_ex);
            log.exiting(TEST_END + STR_METHOD_NAME);
            fail();
        }
    }
    
    public void testProcess_0001()
    {
        String STR_METHOD_NAME = "testProcess_0001()";
        log.entering(TEST_START + STR_METHOD_NAME);
        
        WEB3AdminIfoManualExpireOnlineRunStatusTransactionCallback l_callback = 
            new WEB3AsynAdminIfoManualExpireServiceImpl().new WEB3AdminIfoManualExpireOnlineRunStatusTransactionCallback("123" , 123456L , 123456L);
        
        OnlineRunStatusParams l_onlineRunStatusParams = new OnlineRunStatusParams();
        l_onlineRunStatusParams.setInstitutionCode("123");
        l_onlineRunStatusParams.setProductType(ProductTypeEnum.IFO);
        l_onlineRunStatusParams.setFutureOptionDiv(WEB3FuturesOptionDivDef.DEFAULT);
        l_onlineRunStatusParams.setOnlineServiceDiv(WEB3OnlineServiceDiv.MANUAL_EXPIRE);
        // 1：処理済
        l_onlineRunStatusParams.setRunStatusDiv("1");
        l_onlineRunStatusParams.setAccountIdFrom(123456L);
        l_onlineRunStatusParams.setAccountIdTo(123456L);
        try
        {
            TestDBUtility.deleteAll(l_onlineRunStatusParams.getRowType());
            TestDBUtility.insertWithDel(l_onlineRunStatusParams);
        } 
        catch (WEB3BaseException l_ex)
        {
            log.error(TEST_END + STR_METHOD_NAME , l_ex);
            log.exiting(TEST_END + STR_METHOD_NAME);
            fail();
        }
        
        try
        {
            l_callback.process();
        }
        catch (Exception l_ex)
        {
            log.error(TEST_END + STR_METHOD_NAME , l_ex);
            log.exiting(TEST_END + STR_METHOD_NAME);
            fail();
        }
    }
    
    public void testProcess_0002()
    {
        String STR_METHOD_NAME = "testProcess_0002()";
        log.entering(TEST_START + STR_METHOD_NAME);
        
        WEB3AdminIfoManualExpireOnlineRunStatusTransactionCallback l_callback = 
            new WEB3AsynAdminIfoManualExpireServiceImpl().new WEB3AdminIfoManualExpireOnlineRunStatusTransactionCallback("123" , 123456L , 123456L);
        
        OnlineRunStatusParams l_onlineRunStatusParams = new OnlineRunStatusParams();
        l_onlineRunStatusParams.setInstitutionCode("123");
        l_onlineRunStatusParams.setProductType(ProductTypeEnum.IFO);
        l_onlineRunStatusParams.setFutureOptionDiv(WEB3FuturesOptionDivDef.DEFAULT);
        l_onlineRunStatusParams.setOnlineServiceDiv(WEB3OnlineServiceDiv.MANUAL_EXPIRE);
        // 5：処理中
        l_onlineRunStatusParams.setRunStatusDiv("5");
        l_onlineRunStatusParams.setAccountIdFrom(123456L);
        l_onlineRunStatusParams.setAccountIdTo(123456L);
        try
        {
            TestDBUtility.deleteAll(l_onlineRunStatusParams.getRowType());
            TestDBUtility.insertWithDel(l_onlineRunStatusParams);
        } 
        catch (WEB3BaseException l_ex)
        {
            log.error(TEST_END + STR_METHOD_NAME , l_ex);
            log.exiting(TEST_END + STR_METHOD_NAME);
            fail();
        }
        
        try
        {
            l_callback.process();
            
            log.exiting(TEST_END + STR_METHOD_NAME);
            fail();
        }
        catch (WEB3BaseRuntimeException l_ex)
        {
            assertEquals(WEB3ErrorCatalog.BUSINESS_ERROR_01992 , l_ex.getErrorInfo());
        }
        catch (Exception l_ex)
        {
            log.error(TEST_END + STR_METHOD_NAME , l_ex);
            log.exiting(TEST_END + STR_METHOD_NAME);
            fail();
        }
    }
    
    public void testProcess_0003()
    {
        String STR_METHOD_NAME = "testProcess_0003()";
        log.entering(TEST_START + STR_METHOD_NAME);
        TestBaseForMock.MOCK_MANAGER.setIsMockUsed(true);
        
        TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
            "webbroker3.ifo.service.delegate.stdimpls.WEB3IfoFrontOrderServiceImpl",
            "lockHostFotypeOrderAll",
            new Class[] {IfoOrderUnit.class},
            null);
        
        TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
            "webbroker3.ifo.service.delegate.stdimpls.WEB3IfoFrontOrderServiceImpl",
            "getHostFotypeOrderAll",
            new Class[] {IfoOrderUnit.class},
            null);
        
        MainAccountParams l_mainAccountParams = TestDBUtility.getMainAccountRow();
        l_mainAccountParams.setAccountId(101001010010L);
        try
        {
            TestDBUtility.deleteAll(l_mainAccountParams.getRowType());
            TestDBUtility.insertWithDel(l_mainAccountParams);
            TestDBUtility.deleteAll(TestDBUtility.getInstitutionRow().getRowType());
            TestDBUtility.insertWithDel(TestDBUtility.getInstitutionRow());
            TestDBUtility.deleteAll(TestDBUtility.getBranchRow().getRowType());
            TestDBUtility.insertWithDel(TestDBUtility.getBranchRow());
        }
        catch (WEB3BaseException l_ex)
        {
            log.error(TEST_END + STR_METHOD_NAME, l_ex);
            log.exiting(TEST_END + STR_METHOD_NAME);
            fail();
        }
        
        WEB3AdminIfoManualExpireTransactionCallback callback;
        IfoOrderUnitParams l_ifoOrderUnitParams = TestDBUtility.getIfoOrderUnitRow();
        callback = new WEB3AsynAdminIfoManualExpireServiceImpl().new WEB3AdminIfoManualExpireTransactionCallback(l_ifoOrderUnitParams);
        
        try
        {
            assertNull(callback.process());
            assertTrue(TestDBUtility.isTableLockedSuccessful(l_mainAccountParams.getRowType()));
        }
        catch (Exception l_ex)
        {
            log.error(TEST_END + STR_METHOD_NAME, l_ex);
            log.exiting(TEST_END + STR_METHOD_NAME);
            fail();
        }
    }
    
    public void testProcess_0004()
    {
        String STR_METHOD_NAME = "testProcess_0004()";
        log.entering(TEST_START + STR_METHOD_NAME);

        TestBaseForMock.MOCK_MANAGER.setIsMockUsed(true);
        
        TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
            "webbroker3.ifo.service.delegate.stdimpls.WEB3IfoFrontOrderServiceImpl",
            "lockHostFotypeOrderAll",
            new Class[] {IfoOrderUnit.class},
            null);
        
        HostFotypeOrderAllParams l_hostFotypeOrderAllParams = TestDBUtility.getHostFotypeOrderAllRow();
        TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
            "webbroker3.ifo.service.delegate.stdimpls.WEB3IfoFrontOrderServiceImpl",
            "getHostFotypeOrderAll",
            new Class[] {IfoOrderUnit.class},
            l_hostFotypeOrderAllParams);
        
        TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
            "webbroker3.ifo.service.delegate.stdimpls.WEB3IfoCloseNotifyUnitServiceImpl",
            "notifyClose",
            new Class[] 
                      {OrderUnit.class,
                    double.class,
                    String.class,
                    String.class,
                    },
            null);
        
        TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
            "webbroker3.ifo.WEB3FuturesOrderManagerImpl",
            "updateOrderData",
            new Class[]{IfoOrderUnit.class,boolean.class},
            null);
        
        MainAccountParams l_mainAccountParams = TestDBUtility.getMainAccountRow();
        l_mainAccountParams.setAccountId(101001010010L);
        SubAccountParams l_subAccountParams = TestDBUtility.getSubAccountRow();
        l_subAccountParams.setSubAccountType(SubAccountTypeEnum.EQUITY_OPTIONS_SUB_ACCOUNT);
        l_subAccountParams.setAccountId(101001010010L);
        l_subAccountParams.setSubAccountId(10100101001007L);
        IfoProductParams l_ifoProductParams = TestDBUtility.getIfoProductRow();
        l_ifoProductParams.setProductId(3304148080000L);
        try
        {
            TestDBUtility.deleteAll(l_mainAccountParams.getRowType());
            TestDBUtility.insertWithDel(l_mainAccountParams);
            
            TestDBUtility.deleteAll(TestDBUtility.getInstitutionRow().getRowType());
            TestDBUtility.insertWithDel(TestDBUtility.getInstitutionRow());
            
            TestDBUtility.deleteAll(TestDBUtility.getBranchRow().getRowType());
            TestDBUtility.insertWithDel(TestDBUtility.getBranchRow());
            
            TestDBUtility.deleteAll(l_subAccountParams.getRowType());
            TestDBUtility.insertWithDel(l_subAccountParams);
            
            TestDBUtility.deleteAll(TestDBUtility.getProductRow().getRowType());
            TestDBUtility.insertWithDel(TestDBUtility.getProductRow());
            
            TestDBUtility.deleteAll(l_ifoProductParams.getRowType());
            TestDBUtility.insertWithDel(l_ifoProductParams);

            TradingTimeParams l_tradingTimeParams = new TradingTimeParams();

            l_tradingTimeParams.setInstitutionCode("0D");
            l_tradingTimeParams.setBranchCode("381");
            l_tradingTimeParams.setMarketCode("0");
            l_tradingTimeParams.setTradingTimeType("11");
            l_tradingTimeParams.setProductCode("0005");
            FinApp l_finApp = (FinApp) Services.getService(FinApp.class);
            TradingSystem l_tradingSys = l_finApp.getTradingSystem();
            java.sql.Timestamp l_processTime = l_tradingSys.getSystemTimestamp();
            String l_BizDateType = WEB3GentradeTradingTimeManagement.getBizDateType(l_processTime);
            l_tradingTimeParams.setBizDateType(l_BizDateType);
            l_tradingTimeParams.setStartTime("000000");
            l_tradingTimeParams.setEndTime("235959");        
            l_tradingTimeParams.setSubmitMarketTrigger("0");
            l_tradingTimeParams.setEnableOrder("0");
            l_tradingTimeParams.setBizdateCalcParameter("0");
            l_tradingTimeParams.setCreatedTimestamp(Calendar.getInstance().getTime());
            l_tradingTimeParams.setLastUpdatedTimestamp(Calendar .getInstance().getTime());
            TestDBUtility.deleteAll(l_tradingTimeParams.getRowType());
            TestDBUtility.insertWithDel(l_tradingTimeParams);
            
            Services.unregisterService(WEB3TPTradingPowerReCalcService.class);
            Services.registerService(WEB3TPTradingPowerReCalcService.class , 
                new WEB3TPTradingPowerReCalcServiceImplForMock());
            Services.addInterceptor(
                WEB3TPTradingPowerReCalcService.class,
                new WEB3TPTradingPowerServiceInterceptor());
            Services.addInterceptor(
                WEB3TPTradingPowerReCalcService.class,
                new TransactionalInterceptor(TransactionalInterceptor.TX_JOIN_EXISTING));
            
        }
        catch (WEB3BaseException l_ex)
        {
            log.error(TEST_END + STR_METHOD_NAME, l_ex);
            log.exiting(TEST_END + STR_METHOD_NAME);
            fail();
        }
                
        WEB3AdminIfoManualExpireTransactionCallback callback;
        IfoOrderUnitParams l_ifoOrderUnitParams = TestDBUtility.getIfoOrderUnitRow();
        l_ifoOrderUnitParams.setProductId(3304148080000L);
        callback = new WEB3AsynAdminIfoManualExpireServiceImpl().new WEB3AdminIfoManualExpireTransactionCallback(l_ifoOrderUnitParams);

        try
        {
            callback.process();
            
            WEB3MockObjectParamsValue l_value = 
                TestBaseForMock.MOCK_MANAGER.getMethodParamsValue(
                    "webbroker3.ifo.service.delegate.stdimpls.WEB3IfoCloseNotifyUnitServiceImpl",
                    "notifyClose",
                    new Class[] 
                          {OrderUnit.class,
                        double.class,
                        String.class,
                        String.class,
                        });
            assertEquals(IfoContractOpenOrderUnitImpl.class , l_value.getFirstCalled()[0].getClass());
            assertEquals("9" , l_value.getFirstCalled()[2]);
            assertEquals("1" , l_value.getFirstCalled()[3]);
            
            Services.unregisterService(WEB3TPTradingPowerReCalcService.class);
            Services.registerService(WEB3TPTradingPowerReCalcService.class , 
                new WEB3TPTradingPowerReCalcServiceImpl());
            Services.addInterceptor(
                WEB3TPTradingPowerReCalcService.class,
                new WEB3TPTradingPowerServiceInterceptor());
            Services.addInterceptor(
                WEB3TPTradingPowerReCalcService.class,
                new TransactionalInterceptor(TransactionalInterceptor.TX_JOIN_EXISTING));
            
        }
        catch (Exception l_ex)
        {
            log.error(TEST_END + STR_METHOD_NAME, l_ex);
            log.exiting(TEST_END + STR_METHOD_NAME);
            fail();
        }
    }
    
    public void testProcess_0005()
    {
        String STR_METHOD_NAME = "testProcess_0005()";
        log.entering(TEST_START + STR_METHOD_NAME);

        TestBaseForMock.MOCK_MANAGER.setIsMockUsed(true);
        
        TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
            "webbroker3.ifo.service.delegate.stdimpls.WEB3IfoFrontOrderServiceImpl",
            "lockHostFotypeOrderAll",
            new Class[] {IfoOrderUnit.class},
            null);
        
        HostFotypeOrderAllParams l_hostFotypeOrderAllParams = TestDBUtility.getHostFotypeOrderAllRow();
        TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
            "webbroker3.ifo.service.delegate.stdimpls.WEB3IfoFrontOrderServiceImpl",
            "getHostFotypeOrderAll",
            new Class[] {IfoOrderUnit.class},
            l_hostFotypeOrderAllParams);
        
        TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
            "webbroker3.ifo.service.delegate.stdimpls.WEB3IfoCloseNotifyUnitServiceImpl",
            "notifyClose",
            new Class[] 
                      {OrderUnit.class,
                    double.class,
                    String.class,
                    String.class,
                    },
            null);
        
        TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
            "webbroker3.ifo.WEB3FuturesOrderManagerImpl",
            "updateOrderData",
            new Class[]{IfoOrderUnit.class,boolean.class},
            null);
        
        MainAccountParams l_mainAccountParams = TestDBUtility.getMainAccountRow();
        l_mainAccountParams.setAccountId(101001010010L);
        SubAccountParams l_subAccountParams = TestDBUtility.getSubAccountRow();
        l_subAccountParams.setSubAccountType(SubAccountTypeEnum.FUTURES_SUB_ACCOUNT);
        l_subAccountParams.setAccountId(101001010010L);
        l_subAccountParams.setSubAccountId(10100101001007L);
        IfoProductParams l_ifoProductParams = TestDBUtility.getIfoProductRow();
        l_ifoProductParams.setProductId(3304148080000L);
        try
        {
            TestDBUtility.deleteAll(l_mainAccountParams.getRowType());
            TestDBUtility.insertWithDel(l_mainAccountParams);
            
            TestDBUtility.deleteAll(TestDBUtility.getInstitutionRow().getRowType());
            TestDBUtility.insertWithDel(TestDBUtility.getInstitutionRow());
            
            TestDBUtility.deleteAll(TestDBUtility.getBranchRow().getRowType());
            TestDBUtility.insertWithDel(TestDBUtility.getBranchRow());
            
            TestDBUtility.deleteAll(l_subAccountParams.getRowType());
            TestDBUtility.insertWithDel(l_subAccountParams);
            
            TestDBUtility.deleteAll(TestDBUtility.getProductRow().getRowType());
            TestDBUtility.insertWithDel(TestDBUtility.getProductRow());
            
            TestDBUtility.deleteAll(l_ifoProductParams.getRowType());
            TestDBUtility.insertWithDel(l_ifoProductParams);

            TradingTimeParams l_tradingTimeParams = new TradingTimeParams();

            l_tradingTimeParams.setInstitutionCode("0D");
            l_tradingTimeParams.setBranchCode("381");
            l_tradingTimeParams.setMarketCode("0");
            l_tradingTimeParams.setTradingTimeType("11");
            l_tradingTimeParams.setProductCode("0005");
            FinApp l_finApp = (FinApp) Services.getService(FinApp.class);
            TradingSystem l_tradingSys = l_finApp.getTradingSystem();
            java.sql.Timestamp l_processTime = l_tradingSys.getSystemTimestamp();
            String l_BizDateType = WEB3GentradeTradingTimeManagement.getBizDateType(l_processTime);
            l_tradingTimeParams.setBizDateType(l_BizDateType);
            l_tradingTimeParams.setStartTime("000000");
            l_tradingTimeParams.setEndTime("235959");        
            l_tradingTimeParams.setSubmitMarketTrigger("0");
            l_tradingTimeParams.setEnableOrder("0");
            l_tradingTimeParams.setBizdateCalcParameter("0");
            l_tradingTimeParams.setCreatedTimestamp(Calendar.getInstance().getTime());
            l_tradingTimeParams.setLastUpdatedTimestamp(Calendar .getInstance().getTime());
            TestDBUtility.deleteAll(l_tradingTimeParams.getRowType());
            TestDBUtility.insertWithDel(l_tradingTimeParams);
            
            Services.unregisterService(WEB3TPTradingPowerReCalcService.class);
            Services.registerService(WEB3TPTradingPowerReCalcService.class , 
                new WEB3TPTradingPowerReCalcServiceImplForMock());
            Services.addInterceptor(
                WEB3TPTradingPowerReCalcService.class,
                new WEB3TPTradingPowerServiceInterceptor());
            Services.addInterceptor(
                WEB3TPTradingPowerReCalcService.class,
                new TransactionalInterceptor(TransactionalInterceptor.TX_JOIN_EXISTING));
            
        }
        catch (WEB3BaseException l_ex)
        {
            log.error(TEST_END + STR_METHOD_NAME, l_ex);
            log.exiting(TEST_END + STR_METHOD_NAME);
            fail();
        }
                
        WEB3AdminIfoManualExpireTransactionCallback callback;
        IfoOrderUnitParams l_ifoOrderUnitParams = TestDBUtility.getIfoOrderUnitRow();
        l_ifoOrderUnitParams.setProductId(3304148080000L);
        callback = new WEB3AsynAdminIfoManualExpireServiceImpl().new WEB3AdminIfoManualExpireTransactionCallback(l_ifoOrderUnitParams);

        try
        {
            callback.process();
            
            log.exiting(TEST_END + STR_METHOD_NAME);
            fail();
        }
        catch (WEB3BaseRuntimeException l_ex)
        {
            log.debug(TEST_END + STR_METHOD_NAME, l_ex);
            log.exiting(TEST_END + STR_METHOD_NAME);

            assertEquals(WEB3ErrorCatalog.SYSTEM_ERROR_80002 , l_ex.getErrorInfo());
            
            Services.unregisterService(WEB3TPTradingPowerReCalcService.class);
            Services.registerService(WEB3TPTradingPowerReCalcService.class , 
                new WEB3TPTradingPowerReCalcServiceImpl());
            Services.addInterceptor(
                WEB3TPTradingPowerReCalcService.class,
                new WEB3TPTradingPowerServiceInterceptor());
            Services.addInterceptor(
                WEB3TPTradingPowerReCalcService.class,
                new TransactionalInterceptor(TransactionalInterceptor.TX_JOIN_EXISTING));

            log.debug(TEST_END + STR_METHOD_NAME, l_ex);
            log.exiting(TEST_END + STR_METHOD_NAME);
        }
        catch (Exception l_ex)
        {
            log.error(TEST_END + STR_METHOD_NAME, l_ex);
            log.exiting(TEST_END + STR_METHOD_NAME);
            fail();
        }
    }
    
    public void testProcess_0006()
    {
        String STR_METHOD_NAME = "testProcess_0006()";
        log.entering(TEST_START + STR_METHOD_NAME);
        
        DaemonTriggerParams l_daemonTriggerParams = new DaemonTriggerParams();
        l_daemonTriggerParams.setTriggerType("1");
        l_daemonTriggerParams.setThreadNo(123456789L);
        l_daemonTriggerParams.setOrderRequestNumber("12");
        l_daemonTriggerParams.setRangeFrom(123456L);
        l_daemonTriggerParams.setRangeTo(123456L);
        l_daemonTriggerParams.setTriggerStatus("1");
        try
        {
            TestDBUtility.deleteAll(l_daemonTriggerParams.getRowType());
            TestDBUtility.insertWithDel(l_daemonTriggerParams);
            
        } catch (WEB3BaseException l_ex)
        {
            log.error(TEST_END + STR_METHOD_NAME , l_ex);
            log.exiting(TEST_END + STR_METHOD_NAME);
            fail();
        }
        
        try
        {
            WEB3AdminIfoManualExpireDaemonTriggerTransactionCallback callback;
            IfoOrderUnitParams l_ifoOrderUnitParams = TestDBUtility.getIfoOrderUnitRow();
            l_ifoOrderUnitParams.setProductId(3304148080000L);
            callback = new WEB3AsynAdminIfoManualExpireServiceImpl().new WEB3AdminIfoManualExpireDaemonTriggerTransactionCallback(123456789L);

            List list = (List) callback.process();
            
            assertFalse(list.isEmpty());
        }
        catch (Exception l_ex)
        {
            log.error(TEST_END + STR_METHOD_NAME , l_ex);
            log.exiting(TEST_END + STR_METHOD_NAME);
            fail();
        }
    }
    
    public void testProcess_0007()
    {
        String STR_METHOD_NAME = "testProcess_0007()";
        log.entering(TEST_START + STR_METHOD_NAME);
        
        DaemonTriggerParams l_daemonTriggerParams = new DaemonTriggerParams();
        l_daemonTriggerParams.setTriggerType("1");
        l_daemonTriggerParams.setThreadNo(123456789L);
        l_daemonTriggerParams.setOrderRequestNumber("12");
        l_daemonTriggerParams.setRangeFrom(123456L);
        l_daemonTriggerParams.setRangeTo(123456L);
        l_daemonTriggerParams.setTriggerStatus("1");
        try
        {
            TestDBUtility.deleteAll(l_daemonTriggerParams.getRowType());
            TestDBUtility.insertWithDel(l_daemonTriggerParams);
            
        } catch (WEB3BaseException l_ex)
        {
            log.error(TEST_END + STR_METHOD_NAME , l_ex);
            log.exiting(TEST_END + STR_METHOD_NAME);
            fail();
        }
        
        try
        {
            WEB3AdminIfoManualExpireDaemonTriggerTransactionCallback callback;
            IfoOrderUnitParams l_ifoOrderUnitParams = TestDBUtility.getIfoOrderUnitRow();
            l_ifoOrderUnitParams.setProductId(3304148080000L);
            callback = new WEB3AsynAdminIfoManualExpireServiceImpl().new WEB3AdminIfoManualExpireDaemonTriggerTransactionCallback(1234567L);

            assertNull(callback.process());
        }
        catch (Exception l_ex)
        {
            log.error(TEST_END + STR_METHOD_NAME , l_ex);
            log.exiting(TEST_END + STR_METHOD_NAME);
            fail();
        }
    }
    
    public class WEB3TPTradingPowerReCalcServiceImplForMock extends WEB3TPTradingPowerReCalcServiceImpl
    {
        public void reCalcTradingPower(WEB3GentradeSubAccount l_subAccount)
        throws WEB3SystemLayerException
        {
            String STR_METHOD_NAME = "reCalcTradingPower(WEB3GentradeSubAccount)";
            throw new WEB3SystemLayerException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80017,
                this.getClass().getName() + "." + STR_METHOD_NAME);
        }
    }

}
@
