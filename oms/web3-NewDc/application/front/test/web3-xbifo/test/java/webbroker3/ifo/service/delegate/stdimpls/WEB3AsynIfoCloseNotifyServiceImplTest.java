head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.04.07.00.48.38;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	5444d9d078c63f4;
filename	WEB3AsynIfoCloseNotifyServiceImplTest.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
 Copyright        : (株)大和総研ビジネス・イノベーション
 File Name        : WEB3AsynIfoCloseNotifyServiceImplテスト
 Author Name      : Daiwa Institute of Research Business Innovation
 Revesion History : 2007/04/26 孟亜南 (中訊) 新規作成
 Revesion History : 2010/07/20 趙天月 (中訊) 大証次期デリバティブシステム対応
 */
package webbroker3.ifo.service.delegate.stdimpls;

import java.util.ArrayList;
import java.util.List;

import com.fitechlabs.xtrade.kernel.data.DataNetworkException;
import com.fitechlabs.xtrade.kernel.data.DataQueryException;
import com.fitechlabs.xtrade.kernel.data.Processors;
import com.fitechlabs.xtrade.kernel.data.QueryProcessor;
import com.fitechlabs.xtrade.plugin.tc.gentrade.GtlUtils;
import com.fitechlabs.xtrade.plugin.tc.gentrade.OrderCategEnum;
import com.fitechlabs.xtrade.plugin.tc.gentrade.OrderExpirationStatusEnum;
import com.fitechlabs.xtrade.plugin.tc.gentrade.OrderOpenStatusEnum;
import com.fitechlabs.xtrade.plugin.tc.gentrade.OrderStatusEnum;
import com.fitechlabs.xtrade.plugin.tc.gentrade.OrderTypeEnum;
import com.fitechlabs.xtrade.plugin.tc.gentrade.OrderUnit;
import com.fitechlabs.xtrade.plugin.tc.gentrade.ProductTypeEnum;
import com.fitechlabs.xtrade.plugin.tc.gentrade.TaxTypeEnum;
import com.fitechlabs.xtrade.plugin.tc.gentrade.data.BranchParams;
import com.fitechlabs.xtrade.plugin.tc.gentrade.data.BranchRow;
import com.fitechlabs.xtrade.plugin.tc.gentrade.data.InstitutionParams;
import com.fitechlabs.xtrade.plugin.tc.gentrade.data.InstitutionRow;
import com.fitechlabs.xtrade.plugin.tc.gentrade.data.MainAccountParams;
import com.fitechlabs.xtrade.plugin.tc.gentrade.data.MainAccountRow;
import com.fitechlabs.xtrade.plugin.tc.gentrade.data.SystemPreferencesParams;
import com.fitechlabs.xtrade.plugin.tc.gentrade.data.SystemPreferencesRow;
import com.fitechlabs.xtrade.plugin.tc.xbifo.IfoOrderExecutionConditionType;
import com.fitechlabs.xtrade.plugin.tc.xbifo.data.IfoOrderUnitParams;
import com.fitechlabs.xtrade.plugin.tc.xbifo.data.IfoOrderUnitRow;
import com.fitechlabs.xtrade.plugin.tc.xbifo.stdimpls.IfoContractSettleOrderUnitImpl;

import test.util.TestDBUtility;

import webbroker3.common.WEB3BaseException;
import webbroker3.gentrade.define.WEB3GentradeTimeDef;
import webbroker3.ifo.data.HostFotypeCloseOrderNotifyParams;
import webbroker3.ifo.data.HostFotypeCloseOrderNotifyRow;
import webbroker3.ifo.message.WEB3IfoCloseOrderRequest;
import webbroker3.ifo.service.delegate.stdimpls.WEB3AsynIfoCloseNotifyServiceImpl.WEB3IfoCloseNotifyTransactionCallback;
import webbroker3.mock.TestBaseForMock;
import webbroker3.util.WEB3DateUtility;
import webbroker3.util.WEB3LogUtility;

public class WEB3AsynIfoCloseNotifyServiceImplTest extends TestBaseForMock
{
    public WEB3AsynIfoCloseNotifyServiceImplTest(String name)
    {
        super(name);
    }
    
    private static WEB3LogUtility log = WEB3LogUtility.getInstance(WEB3AsynIfoCloseNotifyServiceImplTest.class);

    
    public void test_process_0006()
    {
        String[]
               l_orderRequestNumberPrefixGroup = {"1"};
        WEB3IfoCloseOrderRequest l_request = null;
        WEB3AsynIfoCloseNotifyServiceImpl l_asynIfo = new WEB3AsynIfoCloseNotifyServiceImpl(l_request);
        
        WEB3IfoCloseNotifyTransactionCallback l_callback = l_asynIfo.new WEB3IfoCloseNotifyTransactionCallback();
        l_callback.setOrderRequestNumberPrefixGroup(l_orderRequestNumberPrefixGroup);
        
        try
        {
            TestDBUtility.deleteAllAndCommit(HostFotypeCloseOrderNotifyRow.TYPE);
            HostFotypeCloseOrderNotifyParams l_fotypeCloseOrderNotify = getHostFotypeCloseOrderNotifyRow();
            l_fotypeCloseOrderNotify.setBranchCode("381");
            l_fotypeCloseOrderNotify.setAcceptNumber("12345678901234567890");
            l_fotypeCloseOrderNotify.setProductCode("1234567890");
            l_fotypeCloseOrderNotify.setBuySellDiv("1");
            l_fotypeCloseOrderNotify.setExecutedQuantity(500.0);
            TestDBUtility.insertWithDelAndCommit(l_fotypeCloseOrderNotify);
            
            TestDBUtility.deleteAllAndCommit(MainAccountRow.TYPE);
            MainAccountParams l_mainAccountParams = TestDBUtility.getMainAccountRow();
            l_mainAccountParams.setIfoAccOpenDivTokyo("1");
            l_mainAccountParams.setIfoAccOpenDivOsaka("1");
            l_mainAccountParams.setIfoAccOpenDivNagoya("1");
            TestDBUtility.insertWithDelAndCommit(l_mainAccountParams);
            
            TestDBUtility.deleteAllAndCommit(InstitutionRow.TYPE);
            InstitutionParams l_InstitutionParams = TestDBUtility.getInstitutionRow();
            TestDBUtility.insertWithDelAndCommit(l_InstitutionParams);
            
            TestDBUtility.deleteAllAndCommit(BranchRow.TYPE);
            BranchParams l_BranchParams = TestDBUtility.getBranchRow();
            TestDBUtility.insertWithDelAndCommit(l_BranchParams);
            
            TestDBUtility.deleteAllAndCommit(IfoOrderUnitRow.TYPE);
            IfoOrderUnitParams l_ifoOrderUnitParams = getIfoOrderUnitRow();
            l_ifoOrderUnitParams.setConfirmedQuantity(0D);
            l_ifoOrderUnitParams.setAcceptNumber("12345678901234567890");
            l_ifoOrderUnitParams.setProductCode("1234567890");
            l_ifoOrderUnitParams.setBuySellDiv("1");
            TestDBUtility.insertWithDelAndCommit(l_ifoOrderUnitParams);
            
            IfoContractSettleOrderUnitImpl l_ifoContract = new  IfoContractSettleOrderUnitImpl(1001);
            
            MOCK_MANAGER.setIsMockUsed(true);
            
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                    "webbroker3.gentrade.WEB3GentradeAccountManager",
                    "lockAccount",
                    new Class[] {String.class,String.class,String.class},
                    null);
            
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                "webbroker3.ifo.WEB3FuturesOrderManagerImpl",
                "getOrderUnit",
                new Class[] {String.class, String.class, ProductTypeEnum.class, String.class},
                l_ifoContract);
            
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                "webbroker3.ifo.WEB3OptionOrderManagerImpl",
                "getOrderUnit",
                new Class[] {long.class},
                l_ifoContract);
            
            l_callback.process();
            
            StringBuffer l_sbWhere = new StringBuffer();
            l_sbWhere.append("status= ?");

            List l_listRecords = new ArrayList();
            
            Object[] l_objWhere =
            {
            "0"
            };
            
            QueryProcessor l_queryProcessor = null;
            l_queryProcessor = Processors.getDefaultProcessor();
            l_listRecords = l_queryProcessor.doFindAllQuery(
                HostFotypeCloseOrderNotifyRow.TYPE,
                l_sbWhere.toString() ,
                null,
                null,
                l_objWhere) ;
            
            assertEquals(0,WEB3DateUtility.compareToDay(GtlUtils.getSystemTimestamp(),
                ((HostFotypeCloseOrderNotifyRow)l_listRecords.get(0)).getLastUpdatedTimestamp()));
        }
        catch (Exception e)
        {
            fail();
        } 

    }
    
    public void test_process_0007()
    {
        String[]
               l_orderRequestNumberPrefixGroup = {"1"};
        WEB3IfoCloseOrderRequest l_request = null;
        WEB3AsynIfoCloseNotifyServiceImpl l_asynIfo = new WEB3AsynIfoCloseNotifyServiceImpl(l_request);
        
        WEB3IfoCloseNotifyTransactionCallback l_callback = l_asynIfo.new WEB3IfoCloseNotifyTransactionCallback();
        l_callback.setOrderRequestNumberPrefixGroup(l_orderRequestNumberPrefixGroup);
        
        try
        {
            TestDBUtility.deleteAllAndCommit(HostFotypeCloseOrderNotifyRow.TYPE);
            HostFotypeCloseOrderNotifyParams l_fotypeCloseOrderNotify = getHostFotypeCloseOrderNotifyRow();
            l_fotypeCloseOrderNotify.setBranchCode("381");
            l_fotypeCloseOrderNotify.setAcceptNumber("12345678901234567890");
            l_fotypeCloseOrderNotify.setProductCode("1234567890");
            l_fotypeCloseOrderNotify.setBuySellDiv("1");
            l_fotypeCloseOrderNotify.setExecutedQuantity(500.0);
            TestDBUtility.insertWithDelAndCommit(l_fotypeCloseOrderNotify);
            
            TestDBUtility.deleteAllAndCommit(MainAccountRow.TYPE);
            MainAccountParams l_mainAccountParams = TestDBUtility.getMainAccountRow();
            l_mainAccountParams.setIfoAccOpenDivTokyo("1");
            l_mainAccountParams.setIfoAccOpenDivOsaka("1");
            l_mainAccountParams.setIfoAccOpenDivNagoya("1");
            TestDBUtility.insertWithDelAndCommit(l_mainAccountParams);
            
            TestDBUtility.deleteAllAndCommit(InstitutionRow.TYPE);
            InstitutionParams l_InstitutionParams = TestDBUtility.getInstitutionRow();
//            TestDBUtility.insertWithDelAndCommit(l_InstitutionParams);
            
            TestDBUtility.deleteAllAndCommit(BranchRow.TYPE);
            BranchParams l_BranchParams = TestDBUtility.getBranchRow();
            TestDBUtility.insertWithDelAndCommit(l_BranchParams);
            
            TestDBUtility.deleteAllAndCommit(IfoOrderUnitRow.TYPE);
            IfoOrderUnitParams l_ifoOrderUnitParams = getIfoOrderUnitRow();
            l_ifoOrderUnitParams.setConfirmedQuantity(0D);
            l_ifoOrderUnitParams.setAcceptNumber("12345678901234567890");
            l_ifoOrderUnitParams.setProductCode("1234567890");
            l_ifoOrderUnitParams.setBuySellDiv("1");
            TestDBUtility.insertWithDelAndCommit(l_ifoOrderUnitParams);
            
            IfoContractSettleOrderUnitImpl l_ifoContract = new  IfoContractSettleOrderUnitImpl(1001);
            
            MOCK_MANAGER.setIsMockUsed(true);
            
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                    "webbroker3.gentrade.WEB3GentradeAccountManager",
                    "lockAccount",
                    new Class[] {String.class,String.class,String.class},
                    null);
            
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                "webbroker3.ifo.WEB3FuturesOrderManagerImpl",
                "getOrderUnit",
                new Class[] {String.class, String.class, ProductTypeEnum.class, String.class},
                l_ifoContract);
            
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                "webbroker3.ifo.WEB3OptionOrderManagerImpl",
                "getOrderUnit",
                new Class[] {long.class},
                l_ifoContract);
            
            l_callback.process();
            
            StringBuffer l_sbWhere = new StringBuffer();
            l_sbWhere.append("branch_code= ?");

            List l_listRecords = new ArrayList();
            
            Object[] l_objWhere =
            {
            "381"
            };
            
            QueryProcessor l_queryProcessor = null;
            l_queryProcessor = Processors.getDefaultProcessor();
            l_listRecords = l_queryProcessor.doFindAllQuery(
                HostFotypeCloseOrderNotifyRow.TYPE,
                l_sbWhere.toString() ,
                null,
                null,
                l_objWhere) ;
            
            assertEquals("9", ((HostFotypeCloseOrderNotifyRow)l_listRecords.get(0)).getStatus());
            assertEquals(0,WEB3DateUtility.compareToDay(GtlUtils.getSystemTimestamp(),
                    ((HostFotypeCloseOrderNotifyRow)l_listRecords.get(0)).getLastUpdatedTimestamp()));
        }
        catch (Exception e)
        {
            fail();
        } 

    }
    
    public void test_process_0008()
    {
        String[]
               l_orderRequestNumberPrefixGroup = {"1"};
        WEB3IfoCloseOrderRequest l_request = null;
        WEB3AsynIfoCloseNotifyServiceImpl l_asynIfo = new WEB3AsynIfoCloseNotifyServiceImpl(l_request);
        
        WEB3IfoCloseNotifyTransactionCallback l_callback = l_asynIfo.new WEB3IfoCloseNotifyTransactionCallback();
        l_callback.setOrderRequestNumberPrefixGroup(l_orderRequestNumberPrefixGroup);
        
        try
        {
            TestDBUtility.deleteAllAndCommit(HostFotypeCloseOrderNotifyRow.TYPE);
            HostFotypeCloseOrderNotifyParams l_fotypeCloseOrderNotify = getHostFotypeCloseOrderNotifyRow();
            l_fotypeCloseOrderNotify.setBranchCode("381");
            l_fotypeCloseOrderNotify.setAcceptNumber("12345678901234567890");
            l_fotypeCloseOrderNotify.setProductCode("1234567890");
            l_fotypeCloseOrderNotify.setBuySellDiv("1");
            l_fotypeCloseOrderNotify.setExecutedQuantity(500.0);
            l_fotypeCloseOrderNotify.setCreatedTimestamp(GtlUtils.getSystemTimestamp());
            TestDBUtility.insertWithDelAndCommit(l_fotypeCloseOrderNotify);
            
            TestDBUtility.deleteAllAndCommit(MainAccountRow.TYPE);
            MainAccountParams l_mainAccountParams = TestDBUtility.getMainAccountRow();
            l_mainAccountParams.setIfoAccOpenDivTokyo("1");
            l_mainAccountParams.setIfoAccOpenDivOsaka("1");
            l_mainAccountParams.setIfoAccOpenDivNagoya("1");
            TestDBUtility.insertWithDelAndCommit(l_mainAccountParams);
            
            TestDBUtility.deleteAllAndCommit(InstitutionRow.TYPE);
            InstitutionParams l_InstitutionParams = TestDBUtility.getInstitutionRow();
            TestDBUtility.insertWithDelAndCommit(l_InstitutionParams);
            
            TestDBUtility.deleteAllAndCommit(BranchRow.TYPE);
            BranchParams l_BranchParams = TestDBUtility.getBranchRow();
            TestDBUtility.insertWithDelAndCommit(l_BranchParams);
            
            TestDBUtility.deleteAllAndCommit(IfoOrderUnitRow.TYPE);
            IfoOrderUnitParams l_ifoOrderUnitParams = getIfoOrderUnitRow();
            l_ifoOrderUnitParams.setConfirmedQuantity(0D);
            l_ifoOrderUnitParams.setAcceptNumber("12345678901234567890");
            l_ifoOrderUnitParams.setProductCode("1234567890");
            l_ifoOrderUnitParams.setBuySellDiv("2");
            TestDBUtility.insertWithDelAndCommit(l_ifoOrderUnitParams);
            
            TestDBUtility.deleteAllAndCommit(SystemPreferencesRow.TYPE);
            SystemPreferencesParams l_SystemPreferencesParams = TestDBUtility.getSystemPreferencesRow();
            l_SystemPreferencesParams.setName("exec.notify.wait.seconds");
            l_SystemPreferencesParams.setValue("-1");
            TestDBUtility.insertWithDelAndCommit(l_SystemPreferencesParams);
            
            IfoContractSettleOrderUnitImpl l_ifoContract = new  IfoContractSettleOrderUnitImpl(1001);
            
            MOCK_MANAGER.setIsMockUsed(true);
            
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                    "webbroker3.gentrade.WEB3GentradeAccountManager",
                    "lockAccount",
                    new Class[] {String.class,String.class,String.class},
                    null);
            
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                "webbroker3.ifo.WEB3FuturesOrderManagerImpl",
                "getOrderUnit",
                new Class[] {String.class, String.class, ProductTypeEnum.class, String.class},
                l_ifoContract);
            
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                "webbroker3.ifo.WEB3OptionOrderManagerImpl",
                "getOrderUnit",
                new Class[] {long.class},
                l_ifoContract);
            
            l_callback.process();
            
            StringBuffer l_sbWhere = new StringBuffer();
            l_sbWhere.append("branch_code= ?");

            List l_listRecords = new ArrayList();
            
            Object[] l_objWhere =
            {
            "381"
            };
            
            QueryProcessor l_queryProcessor = null;
            l_queryProcessor = Processors.getDefaultProcessor();
            l_listRecords = l_queryProcessor.doFindAllQuery(
                HostFotypeCloseOrderNotifyRow.TYPE,
                l_sbWhere.toString() ,
                null,
                null,
                l_objWhere) ;
            
            assertEquals("9", ((HostFotypeCloseOrderNotifyRow)l_listRecords.get(0)).getStatus());
            assertEquals(0,WEB3DateUtility.compareToDay(GtlUtils.getSystemTimestamp(),
                    ((HostFotypeCloseOrderNotifyRow)l_listRecords.get(0)).getLastUpdatedTimestamp()));
        }
        catch (Exception e)
        {
            fail();
        } 

    }
    
    public void test_process_0009()
    {
        String[]
               l_orderRequestNumberPrefixGroup = {"1"};
        WEB3IfoCloseOrderRequest l_request = null;
        WEB3AsynIfoCloseNotifyServiceImpl l_asynIfo = new WEB3AsynIfoCloseNotifyServiceImpl(l_request);
        
        WEB3IfoCloseNotifyTransactionCallback l_callback = l_asynIfo.new WEB3IfoCloseNotifyTransactionCallback();
        l_callback.setOrderRequestNumberPrefixGroup(l_orderRequestNumberPrefixGroup);
        
        try
        {
            TestDBUtility.deleteAllAndCommit(HostFotypeCloseOrderNotifyRow.TYPE);
            HostFotypeCloseOrderNotifyParams l_fotypeCloseOrderNotify = getHostFotypeCloseOrderNotifyRow();
            l_fotypeCloseOrderNotify.setBranchCode("381");
            l_fotypeCloseOrderNotify.setAcceptNumber("12345678901234567890");
            l_fotypeCloseOrderNotify.setProductCode("1234567890");
            l_fotypeCloseOrderNotify.setBuySellDiv("1");
            l_fotypeCloseOrderNotify.setExecutedQuantity(500.0);
            l_fotypeCloseOrderNotify.setCreatedTimestamp(GtlUtils.getSystemTimestamp());
            TestDBUtility.insertWithDelAndCommit(l_fotypeCloseOrderNotify);
            
            TestDBUtility.deleteAllAndCommit(MainAccountRow.TYPE);
            MainAccountParams l_mainAccountParams = TestDBUtility.getMainAccountRow();
            l_mainAccountParams.setIfoAccOpenDivTokyo("1");
            l_mainAccountParams.setIfoAccOpenDivOsaka("1");
            l_mainAccountParams.setIfoAccOpenDivNagoya("1");
            TestDBUtility.insertWithDelAndCommit(l_mainAccountParams);
            
            TestDBUtility.deleteAllAndCommit(InstitutionRow.TYPE);
            InstitutionParams l_InstitutionParams = TestDBUtility.getInstitutionRow();
            TestDBUtility.insertWithDelAndCommit(l_InstitutionParams);
            
            TestDBUtility.deleteAllAndCommit(BranchRow.TYPE);
            BranchParams l_BranchParams = TestDBUtility.getBranchRow();
            TestDBUtility.insertWithDelAndCommit(l_BranchParams);
            
            TestDBUtility.deleteAllAndCommit(IfoOrderUnitRow.TYPE);
            IfoOrderUnitParams l_ifoOrderUnitParams = getIfoOrderUnitRow();
            l_ifoOrderUnitParams.setConfirmedQuantity(0D);
            l_ifoOrderUnitParams.setAcceptNumber("12345678901234567890");
            l_ifoOrderUnitParams.setProductCode("1234567890");
            l_ifoOrderUnitParams.setBuySellDiv("2");
            TestDBUtility.insertWithDelAndCommit(l_ifoOrderUnitParams);
            
            TestDBUtility.deleteAllAndCommit(SystemPreferencesRow.TYPE);
            SystemPreferencesParams l_SystemPreferencesParams = TestDBUtility.getSystemPreferencesRow();
            l_SystemPreferencesParams.setName("exec.notify.wait.seconds");
            l_SystemPreferencesParams.setValue("1");
            TestDBUtility.insertWithDelAndCommit(l_SystemPreferencesParams);
            
            IfoContractSettleOrderUnitImpl l_ifoContract = new  IfoContractSettleOrderUnitImpl(1001);
            
            MOCK_MANAGER.setIsMockUsed(true);
            
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                    "webbroker3.gentrade.WEB3GentradeAccountManager",
                    "lockAccount",
                    new Class[] {String.class,String.class,String.class},
                    null);
            
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                "webbroker3.ifo.WEB3FuturesOrderManagerImpl",
                "getOrderUnit",
                new Class[] {String.class, String.class, ProductTypeEnum.class, String.class},
                l_ifoContract);
            
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                "webbroker3.ifo.WEB3OptionOrderManagerImpl",
                "getOrderUnit",
                new Class[] {long.class},
                l_ifoContract);
            
            l_callback.process();
            
            StringBuffer l_sbWhere = new StringBuffer();
            l_sbWhere.append("branch_code= ?");

            List l_listRecords = new ArrayList();
            
            Object[] l_objWhere =
            {
            "381"
            };
            
            QueryProcessor l_queryProcessor = null;
            l_queryProcessor = Processors.getDefaultProcessor();
            l_listRecords = l_queryProcessor.doFindAllQuery(
                HostFotypeCloseOrderNotifyRow.TYPE,
                l_sbWhere.toString() ,
                null,
                null,
                l_objWhere) ;
            
            assertEquals("0", ((HostFotypeCloseOrderNotifyRow)l_listRecords.get(0)).getStatus());
            assertEquals(0,WEB3DateUtility.compareToDay(GtlUtils.getSystemTimestamp(),
                    ((HostFotypeCloseOrderNotifyRow)l_listRecords.get(0)).getLastUpdatedTimestamp()));
        }
        catch (Exception e)
        {
            fail();
        } 

    }
    
    public void test_process_0001()
    {
        String[]
               l_orderRequestNumberPrefixGroup = {"1"};
        WEB3IfoCloseOrderRequest l_request = null;
        WEB3AsynIfoCloseNotifyServiceImpl l_asynIfo = new WEB3AsynIfoCloseNotifyServiceImpl(l_request);
        
        WEB3IfoCloseNotifyTransactionCallback l_callback = l_asynIfo.new WEB3IfoCloseNotifyTransactionCallback();
        l_callback.setOrderRequestNumberPrefixGroup(l_orderRequestNumberPrefixGroup);
        
        try
        {
            TestDBUtility.deleteAllAndCommit(HostFotypeCloseOrderNotifyRow.TYPE);
            HostFotypeCloseOrderNotifyParams l_fotypeCloseOrderNotify = getHostFotypeCloseOrderNotifyRow();
            l_fotypeCloseOrderNotify.setBranchCode("381");
            l_fotypeCloseOrderNotify.setAcceptNumber("12345678901234567890");
            l_fotypeCloseOrderNotify.setProductCode("1234567890");
            l_fotypeCloseOrderNotify.setBuySellDiv("1");
            l_fotypeCloseOrderNotify.setExecutedQuantity(500.0);
            TestDBUtility.insertWithDelAndCommit(l_fotypeCloseOrderNotify);
            
            TestDBUtility.deleteAllAndCommit(MainAccountRow.TYPE);
            MainAccountParams l_mainAccountParams = TestDBUtility.getMainAccountRow();
            l_mainAccountParams.setIfoAccOpenDivTokyo("1");
            l_mainAccountParams.setIfoAccOpenDivOsaka("1");
            l_mainAccountParams.setIfoAccOpenDivNagoya("1");
            TestDBUtility.insertWithDelAndCommit(l_mainAccountParams);
            
            TestDBUtility.deleteAllAndCommit(InstitutionRow.TYPE);
            InstitutionParams l_InstitutionParams = TestDBUtility.getInstitutionRow();
            TestDBUtility.insertWithDelAndCommit(l_InstitutionParams);
            
            TestDBUtility.deleteAllAndCommit(BranchRow.TYPE);
            BranchParams l_BranchParams = TestDBUtility.getBranchRow();
            TestDBUtility.insertWithDelAndCommit(l_BranchParams);
            
            TestDBUtility.deleteAllAndCommit(IfoOrderUnitRow.TYPE);
            IfoOrderUnitParams l_ifoOrderUnitParams = getIfoOrderUnitRow();
            l_ifoOrderUnitParams.setConfirmedQuantity(0D);
            l_ifoOrderUnitParams.setAcceptNumber("12345678901234567890");
            l_ifoOrderUnitParams.setProductCode("1234567890");
            l_ifoOrderUnitParams.setBuySellDiv("1");
            TestDBUtility.insertWithDelAndCommit(l_ifoOrderUnitParams);
            
            IfoContractSettleOrderUnitImpl l_ifoContract = new  IfoContractSettleOrderUnitImpl(1001);
            
            MOCK_MANAGER.setIsMockUsed(true);
            
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                    "webbroker3.gentrade.WEB3GentradeAccountManager",
                    "lockAccount",
                    new Class[] {String.class,String.class,String.class},
                    null);
            
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                "webbroker3.ifo.WEB3FuturesOrderManagerImpl",
                "getOrderUnit",
                new Class[] {String.class, String.class, ProductTypeEnum.class, String.class},
                l_ifoContract);
            
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                "webbroker3.ifo.WEB3OptionOrderManagerImpl",
                "getOrderUnit",
                new Class[] {long.class},
                l_ifoContract);
            
            l_callback.process();
            
            StringBuffer l_sbWhere = new StringBuffer();
            l_sbWhere.append("status= ?");

            List l_listRecords = new ArrayList();
            
            Object[] l_objWhere =
            {
            "0"
            };
            
            QueryProcessor l_queryProcessor = null;
            l_queryProcessor = Processors.getDefaultProcessor();
            l_listRecords = l_queryProcessor.doFindAllQuery(
                HostFotypeCloseOrderNotifyRow.TYPE,
                l_sbWhere.toString() ,
                null,
                null,
                l_objWhere) ;
            
            assertEquals(0,WEB3DateUtility.compareToDay(GtlUtils.getSystemTimestamp(),
                ((HostFotypeCloseOrderNotifyRow)l_listRecords.get(0)).getLastUpdatedTimestamp()));
        }
        catch (DataNetworkException e)
        {
            fail();
        } 
        catch (DataQueryException e) 
        {
            fail();
        }
        catch (WEB3BaseException e) 
        {
            fail();
        }
    }
    
    public static HostFotypeCloseOrderNotifyParams getHostFotypeCloseOrderNotifyRow()
    {
        HostFotypeCloseOrderNotifyParams l_fotypeCloseOrderNotify = new 
            HostFotypeCloseOrderNotifyParams();
        
        l_fotypeCloseOrderNotify.setRequestCode("EI813");
        l_fotypeCloseOrderNotify.setStatus("0");
        l_fotypeCloseOrderNotify.setInstitutionCode("0D");
        l_fotypeCloseOrderNotify.setBranchCode("220");
        l_fotypeCloseOrderNotify.setAccountCode("2512246");
        l_fotypeCloseOrderNotify.setOrderRequestNumber("11");
        l_fotypeCloseOrderNotify.setCloseNotifyType("1");
        l_fotypeCloseOrderNotify.setReasonCode("1");
        
        
        return l_fotypeCloseOrderNotify;
    }
    
    /**
     * 注文単位テーブル(ifo_order_unit)
     */
    public static IfoOrderUnitParams getIfoOrderUnitRow()
    {
        IfoOrderUnitParams l_ifoOrderUnitParams = new IfoOrderUnitParams();
        
        l_ifoOrderUnitParams.setOrderUnitId(1001);
        l_ifoOrderUnitParams.setAccountId(101001010010L);
        l_ifoOrderUnitParams.setSubAccountId(10100101001007L);
        l_ifoOrderUnitParams.setBranchId(33381);
        l_ifoOrderUnitParams.setTraderId(null);
        l_ifoOrderUnitParams.setOrderId(1001);
        l_ifoOrderUnitParams.setOrderType(OrderTypeEnum.IDX_FUTURES_BUY_TO_OPEN);
        l_ifoOrderUnitParams.setOrderCateg(OrderCategEnum.IDX_FUTURES_OPEN);
        l_ifoOrderUnitParams.setLastOrderActionSerialNo(1);
        l_ifoOrderUnitParams.setLastExecutionSerialNo(0);
        l_ifoOrderUnitParams.setProductType(ProductTypeEnum.IFO);
        l_ifoOrderUnitParams.setFutureOptionDiv("1");
        l_ifoOrderUnitParams.setMarketId(1002);
        l_ifoOrderUnitParams.setQuantity(100);
        l_ifoOrderUnitParams.setLimitPrice(0);
        l_ifoOrderUnitParams.setExecutionConditionType(IfoOrderExecutionConditionType.NONE);
        l_ifoOrderUnitParams.setOrderConditionType("0");
        l_ifoOrderUnitParams.setOrderCondOperator(null);
        l_ifoOrderUnitParams.setStopPriceType(null);
        l_ifoOrderUnitParams.setStopOrderPrice(null);
        l_ifoOrderUnitParams.setWLimitPrice(null);
        l_ifoOrderUnitParams.setDeliveryDate(WEB3DateUtility.getDate("20040101","yyyyMMdd"));
        l_ifoOrderUnitParams.setOrderStatus(OrderStatusEnum.ACCEPTED);
        l_ifoOrderUnitParams.setOrderOpenStatus(OrderOpenStatusEnum.OPEN);
        l_ifoOrderUnitParams.setExpirationStatus(OrderExpirationStatusEnum.EXPIRED);
        l_ifoOrderUnitParams.setTaxType(TaxTypeEnum.NORMAL);
        l_ifoOrderUnitParams.setBizDate("20040101");
        l_ifoOrderUnitParams.setProductId(1006169090018L);
        l_ifoOrderUnitParams.setCreatedTimestamp(WEB3DateUtility.getDate("20040101","yyyyMMdd"));
        l_ifoOrderUnitParams.setLastUpdatedTimestamp(WEB3DateUtility.getDate("20040101","yyyyMMdd"));
        l_ifoOrderUnitParams.setOrderRequestNumber("000003006");
//        l_ifoOrderUnitParams.setConfirmedOrderRev("2");
//        l_ifoOrderUnitParams.setOrderRev("1");
        return l_ifoOrderUnitParams;
    }
    
    // 大証次期デリバティブシステム対応
    public void test_process_0002()
    {
        final String STR_METHOD_NAME = "test_process_0002()";
        log.entering(TEST_START + STR_METHOD_NAME);
        
        WEB3IfoCloseOrderRequest l_request = null;
        WEB3AsynIfoCloseNotifyServiceImpl l_asynIfo = new WEB3AsynIfoCloseNotifyServiceImpl(l_request);        
        WEB3IfoCloseNotifyTransactionCallback l_callback = l_asynIfo.new WEB3IfoCloseNotifyTransactionCallback();
        
        try
        {
            TestDBUtility.deleteAllAndCommit(HostFotypeCloseOrderNotifyRow.TYPE);
            HostFotypeCloseOrderNotifyParams l_fotypeCloseOrderNotify = getHostFotypeCloseOrderNotifyRow();
            l_fotypeCloseOrderNotify.setBranchCode("381");
            l_fotypeCloseOrderNotify.setAccountCode("2512247");
            l_fotypeCloseOrderNotify.setAcceptNumber("12345678901234567890");
            l_fotypeCloseOrderNotify.setProductCode("1234567890");
            l_fotypeCloseOrderNotify.setBuySellDiv("1");
            l_fotypeCloseOrderNotify.setExecutedQuantity(500.0);
            TestDBUtility.insertWithDelAndCommit(l_fotypeCloseOrderNotify);
            
            TestDBUtility.deleteAllAndCommit(MainAccountRow.TYPE);
            MainAccountParams l_mainAccountParams = TestDBUtility.getMainAccountRow();
            l_mainAccountParams.setIfoAccOpenDivTokyo("1");
            l_mainAccountParams.setIfoAccOpenDivOsaka("1");
            l_mainAccountParams.setIfoAccOpenDivNagoya("1");
            TestDBUtility.insertWithDelAndCommit(l_mainAccountParams);
            
            TestDBUtility.deleteAllAndCommit(IfoOrderUnitRow.TYPE);
            IfoOrderUnitParams l_ifoOrderUnitParams = getIfoOrderUnitRow();
            l_ifoOrderUnitParams.setAcceptNumber("12345678901234567890");
            l_ifoOrderUnitParams.setProductCode("1234567890");
            l_ifoOrderUnitParams.setBuySellDiv("1");
            l_ifoOrderUnitParams.setConfirmedQuantity(500.0);
            l_ifoOrderUnitParams.setOrderStatus(OrderStatusEnum.UNDEFINED);
            TestDBUtility.insertWithDelAndCommit(l_ifoOrderUnitParams);
            
            TestDBUtility.deleteAllAndCommit(InstitutionRow.TYPE);
            InstitutionParams l_InstitutionParams = TestDBUtility.getInstitutionRow();
            TestDBUtility.insertWithDelAndCommit(l_InstitutionParams);
            
            TestDBUtility.deleteAllAndCommit(BranchRow.TYPE);
            BranchParams l_BranchParams = TestDBUtility.getBranchRow();
            TestDBUtility.insertWithDelAndCommit(l_BranchParams);
            
            MOCK_MANAGER.setIsMockUsed(true);
            
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                    "webbroker3.gentrade.WEB3GentradeAccountManager",
                    "lockAccount",
                    new Class[] {String.class,String.class,String.class},
                    null);
            
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                    "webbroker3.ifo.service.delegate.stdimpls.WEB3IfoCloseNotifyUnitServiceImpl",
                    "notifyClose",
                    new Class[] {OrderUnit.class, double.class, String.class, String.class},
                    "1");
            
            l_callback.process();
            
            QueryProcessor l_queryProcessor = Processors.getDefaultProcessor();
            List l_listResult = l_queryProcessor.doFindAllQuery(HostFotypeCloseOrderNotifyParams.TYPE);
            HostFotypeCloseOrderNotifyRow l_closeRow = (HostFotypeCloseOrderNotifyRow)l_listResult.get(0);
            
            assertEquals("9", l_closeRow.getStatus());
        }
        catch (Exception l_ex)
        {
            fail();
        }
        finally
        {
            try
            {
                TestDBUtility.deleteAll(HostFotypeCloseOrderNotifyRow.TYPE);
                TestDBUtility.deleteAll(MainAccountRow.TYPE);
                TestDBUtility.deleteAll(IfoOrderUnitRow.TYPE);
                TestDBUtility.deleteAll(InstitutionRow.TYPE);
                TestDBUtility.deleteAll(BranchRow.TYPE);
            }
            catch(Exception l_ex)
            {
                fail();
            }
            
            log.exiting(TEST_END + STR_METHOD_NAME);
        }
    }
    
    // 大証次期デリバティブシステム対応
    public void test_process_0003()
    {
        final String STR_METHOD_NAME = "test_process_0003()";
        log.entering(TEST_START + STR_METHOD_NAME);
        
        WEB3IfoCloseOrderRequest l_request = null;
        WEB3AsynIfoCloseNotifyServiceImpl l_asynIfo = new WEB3AsynIfoCloseNotifyServiceImpl(l_request);        
        WEB3IfoCloseNotifyTransactionCallback l_callback = l_asynIfo.new WEB3IfoCloseNotifyTransactionCallback();
        
        try
        {
            TestDBUtility.deleteAllAndCommit(HostFotypeCloseOrderNotifyRow.TYPE);
            HostFotypeCloseOrderNotifyParams l_fotypeCloseOrderNotify = getHostFotypeCloseOrderNotifyRow();
            l_fotypeCloseOrderNotify.setBranchCode("381");
            l_fotypeCloseOrderNotify.setAcceptNumber("12345678901234567890");
            l_fotypeCloseOrderNotify.setProductCode("1234567890");
            l_fotypeCloseOrderNotify.setBuySellDiv("1");
            l_fotypeCloseOrderNotify.setExecutedQuantity(500.0);
            TestDBUtility.insertWithDelAndCommit(l_fotypeCloseOrderNotify);
            
            TestDBUtility.deleteAllAndCommit(MainAccountRow.TYPE);
            MainAccountParams l_mainAccountParams = TestDBUtility.getMainAccountRow();
            l_mainAccountParams.setIfoAccOpenDivTokyo("2");
            l_mainAccountParams.setIfoAccOpenDivOsaka("2");
            l_mainAccountParams.setIfoAccOpenDivNagoya("2");
            TestDBUtility.insertWithDelAndCommit(l_mainAccountParams);
            
            TestDBUtility.deleteAllAndCommit(IfoOrderUnitRow.TYPE);
            IfoOrderUnitParams l_ifoOrderUnitParams = getIfoOrderUnitRow();
            l_ifoOrderUnitParams.setAcceptNumber("12345678901234567890");
            l_ifoOrderUnitParams.setProductCode("1234567890");
            l_ifoOrderUnitParams.setBuySellDiv("1");
            l_ifoOrderUnitParams.setConfirmedQuantity(500.0);
            l_ifoOrderUnitParams.setOrderStatus(OrderStatusEnum.UNDEFINED);
            TestDBUtility.insertWithDelAndCommit(l_ifoOrderUnitParams);
            
            TestDBUtility.deleteAllAndCommit(InstitutionRow.TYPE);
            InstitutionParams l_InstitutionParams = TestDBUtility.getInstitutionRow();
            TestDBUtility.insertWithDelAndCommit(l_InstitutionParams);
            
            TestDBUtility.deleteAllAndCommit(BranchRow.TYPE);
            BranchParams l_BranchParams = TestDBUtility.getBranchRow();
            TestDBUtility.insertWithDelAndCommit(l_BranchParams);
            
            MOCK_MANAGER.setIsMockUsed(true);
            
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                    "webbroker3.gentrade.WEB3GentradeAccountManager",
                    "lockAccount",
                    new Class[] {String.class,String.class,String.class},
                    null);
            
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                    "webbroker3.ifo.service.delegate.stdimpls.WEB3IfoCloseNotifyUnitServiceImpl",
                    "notifyClose",
                    new Class[] {OrderUnit.class, double.class, String.class, String.class},
                    "1");
            
            l_callback.process();
            
            QueryProcessor l_queryProcessor = Processors.getDefaultProcessor();
            List l_listResult = l_queryProcessor.doFindAllQuery(HostFotypeCloseOrderNotifyParams.TYPE);
            HostFotypeCloseOrderNotifyRow l_closeRow = (HostFotypeCloseOrderNotifyRow)l_listResult.get(0);
            
            assertEquals("9", l_closeRow.getStatus());
        }
        catch (Exception l_ex)
        {
            fail();
        }
        finally
        {
            try
            {
                TestDBUtility.deleteAll(HostFotypeCloseOrderNotifyRow.TYPE);
                TestDBUtility.deleteAll(MainAccountRow.TYPE);
                TestDBUtility.deleteAll(IfoOrderUnitRow.TYPE);
                TestDBUtility.deleteAll(InstitutionRow.TYPE);
                TestDBUtility.deleteAll(BranchRow.TYPE);
            }
            catch(Exception l_ex)
            {
                fail();
            }
            
            log.exiting(TEST_END + STR_METHOD_NAME);
        }
    }
    
    // 大証次期デリバティブシステム対応
    public void test_process_0004()
    {
        final String STR_METHOD_NAME = "test_process_0004()";
        log.entering(TEST_START + STR_METHOD_NAME);
        
        WEB3IfoCloseOrderRequest l_request = null;
        WEB3AsynIfoCloseNotifyServiceImpl l_asynIfo = new WEB3AsynIfoCloseNotifyServiceImpl(l_request);        
        WEB3IfoCloseNotifyTransactionCallback l_callback = l_asynIfo.new WEB3IfoCloseNotifyTransactionCallback();
        
        try
        {
            TestDBUtility.deleteAllAndCommit(HostFotypeCloseOrderNotifyRow.TYPE);
            HostFotypeCloseOrderNotifyParams l_fotypeCloseOrderNotify = getHostFotypeCloseOrderNotifyRow();
            l_fotypeCloseOrderNotify.setBranchCode("381");
            l_fotypeCloseOrderNotify.setRequestCode("EI817");
            l_fotypeCloseOrderNotify.setAcceptNumber("12345678901234567890");
            l_fotypeCloseOrderNotify.setProductCode("1234567890");
            l_fotypeCloseOrderNotify.setBuySellDiv("1");
            l_fotypeCloseOrderNotify.setExecutedQuantity(500.0);
            TestDBUtility.insertWithDelAndCommit(l_fotypeCloseOrderNotify);
            
            TestDBUtility.deleteAllAndCommit(MainAccountRow.TYPE);
            MainAccountParams l_mainAccountParams = TestDBUtility.getMainAccountRow();
            l_mainAccountParams.setIfoAccOpenDivTokyo("1");
            l_mainAccountParams.setIfoAccOpenDivOsaka("1");
            l_mainAccountParams.setIfoAccOpenDivNagoya("1");
            TestDBUtility.insertWithDelAndCommit(l_mainAccountParams);
            
            TestDBUtility.deleteAllAndCommit(IfoOrderUnitRow.TYPE);
            IfoOrderUnitParams l_ifoOrderUnitParams = getIfoOrderUnitRow();
            l_ifoOrderUnitParams.setAcceptNumber("12345678901234567890");
            l_ifoOrderUnitParams.setProductCode("1234567890");
            l_ifoOrderUnitParams.setBuySellDiv("1");
            l_ifoOrderUnitParams.setConfirmedQuantity(500.0);
            l_ifoOrderUnitParams.setOrderStatus(OrderStatusEnum.UNDEFINED);
            TestDBUtility.insertWithDelAndCommit(l_ifoOrderUnitParams);
            
            TestDBUtility.deleteAllAndCommit(InstitutionRow.TYPE);
            InstitutionParams l_InstitutionParams = TestDBUtility.getInstitutionRow();
            TestDBUtility.insertWithDelAndCommit(l_InstitutionParams);
            
            TestDBUtility.deleteAllAndCommit(BranchRow.TYPE);
            BranchParams l_BranchParams = TestDBUtility.getBranchRow();
            TestDBUtility.insertWithDelAndCommit(l_BranchParams);
            
            MOCK_MANAGER.setIsMockUsed(true);
            
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                    "webbroker3.gentrade.WEB3GentradeAccountManager",
                    "lockAccount",
                    new Class[] {String.class,String.class,String.class},
                    null);
            
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                    "webbroker3.ifo.service.delegate.stdimpls.WEB3IfoCloseNotifyUnitServiceImpl",
                    "notifyClose",
                    new Class[] {OrderUnit.class, double.class, String.class, String.class},
                    "1");
            
            l_callback.process();
            
            QueryProcessor l_queryProcessor = Processors.getDefaultProcessor();
            List l_listResult = l_queryProcessor.doFindAllQuery(HostFotypeCloseOrderNotifyParams.TYPE);
            HostFotypeCloseOrderNotifyRow l_closeRow = (HostFotypeCloseOrderNotifyRow)l_listResult.get(0);
            
            assertEquals("9", l_closeRow.getStatus());
        }
        catch (Exception l_ex)
        {
            fail();
        }
        finally
        {
            try
            {
                TestDBUtility.deleteAll(HostFotypeCloseOrderNotifyRow.TYPE);
                TestDBUtility.deleteAll(MainAccountRow.TYPE);
                TestDBUtility.deleteAll(IfoOrderUnitRow.TYPE);
                TestDBUtility.deleteAll(InstitutionRow.TYPE);
                TestDBUtility.deleteAll(BranchRow.TYPE);
            }
            catch(Exception l_ex)
            {
                fail();
            }
            
            log.exiting(TEST_END + STR_METHOD_NAME);
        }
    }
    
    // 大証次期デリバティブシステム対応
    public void test_process_0005()
    {
        final String STR_METHOD_NAME = "test_process_0005()";
        log.entering(TEST_START + STR_METHOD_NAME);
        
        WEB3IfoCloseOrderRequest l_request = null;
        WEB3AsynIfoCloseNotifyServiceImpl l_asynIfo = new WEB3AsynIfoCloseNotifyServiceImpl(l_request);        
        WEB3IfoCloseNotifyTransactionCallback l_callback = l_asynIfo.new WEB3IfoCloseNotifyTransactionCallback();
        
        try
        {
            TestDBUtility.deleteAllAndCommit(HostFotypeCloseOrderNotifyRow.TYPE);
            HostFotypeCloseOrderNotifyParams l_fotypeCloseOrderNotify = getHostFotypeCloseOrderNotifyRow();
            l_fotypeCloseOrderNotify.setBranchCode("381");
            l_fotypeCloseOrderNotify.setAcceptNumber("12345678901234567890");
            l_fotypeCloseOrderNotify.setProductCode("1234567890");
            l_fotypeCloseOrderNotify.setBuySellDiv("1");
            l_fotypeCloseOrderNotify.setExecutedQuantity(500.0);
            l_fotypeCloseOrderNotify.setRequestCode("EI814");
            TestDBUtility.insertWithDelAndCommit(l_fotypeCloseOrderNotify);
            
            TestDBUtility.deleteAllAndCommit(MainAccountRow.TYPE);
            MainAccountParams l_mainAccountParams = TestDBUtility.getMainAccountRow();
            l_mainAccountParams.setIfoAccOpenDivTokyo("1");
            l_mainAccountParams.setIfoAccOpenDivOsaka("1");
            l_mainAccountParams.setIfoAccOpenDivNagoya("1");
            TestDBUtility.insertWithDelAndCommit(l_mainAccountParams);
            
            TestDBUtility.deleteAllAndCommit(IfoOrderUnitRow.TYPE);
            IfoOrderUnitParams l_ifoOrderUnitParams = getIfoOrderUnitRow();
            l_ifoOrderUnitParams.setAcceptNumber("12345678901234567890");
            l_ifoOrderUnitParams.setProductCode("1234567890");
            l_ifoOrderUnitParams.setBuySellDiv("2");
            l_ifoOrderUnitParams.setConfirmedQuantity(500.0);
            l_ifoOrderUnitParams.setOrderStatus(OrderStatusEnum.UNDEFINED);
            TestDBUtility.insertWithDelAndCommit(l_ifoOrderUnitParams);
            
            TestDBUtility.deleteAllAndCommit(InstitutionRow.TYPE);
            InstitutionParams l_InstitutionParams = TestDBUtility.getInstitutionRow();
            TestDBUtility.insertWithDelAndCommit(l_InstitutionParams);
            
            TestDBUtility.deleteAllAndCommit(BranchRow.TYPE);
            BranchParams l_BranchParams = TestDBUtility.getBranchRow();
            TestDBUtility.insertWithDelAndCommit(l_BranchParams);
            
            MOCK_MANAGER.setIsMockUsed(true);
            
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                    "webbroker3.gentrade.WEB3GentradeAccountManager",
                    "lockAccount",
                    new Class[] {String.class,String.class,String.class},
                    null);
            
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                    "webbroker3.ifo.service.delegate.stdimpls.WEB3IfoCloseNotifyUnitServiceImpl",
                    "notifyClose",
                    new Class[] {OrderUnit.class, double.class, String.class, String.class},
                    "1");
            
            l_callback.process();
            
            QueryProcessor l_queryProcessor = Processors.getDefaultProcessor();
            List l_listResult = l_queryProcessor.doFindAllQuery(HostFotypeCloseOrderNotifyParams.TYPE);
            HostFotypeCloseOrderNotifyRow l_closeRow = (HostFotypeCloseOrderNotifyRow)l_listResult.get(0);
            
            assertEquals("0", l_closeRow.getStatus());
        }
        catch (Exception l_ex)
        {
            fail();
        }
        finally
        {
            try
            {
                TestDBUtility.deleteAll(HostFotypeCloseOrderNotifyRow.TYPE);
                TestDBUtility.deleteAll(MainAccountRow.TYPE);
                TestDBUtility.deleteAll(IfoOrderUnitRow.TYPE);
                TestDBUtility.deleteAll(InstitutionRow.TYPE);
                TestDBUtility.deleteAll(BranchRow.TYPE);
            }
            catch(Exception l_ex)
            {
                fail();
            }
            
            log.exiting(TEST_END + STR_METHOD_NAME);
        }
    }
}
@
