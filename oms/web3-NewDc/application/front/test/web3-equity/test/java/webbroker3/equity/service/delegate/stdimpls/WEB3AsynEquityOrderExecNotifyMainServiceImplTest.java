head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.04.07.00.54.11;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	5444d9d078c63f4;
filename	WEB3AsynEquityOrderExecNotifyMainServiceImplTest.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : 株式出来通知メインサービスImplテスト(WEB3AsynEquityOrderExecNotifyMainServiceImplTest.java)
Author Name      : Daiwa Institute of Research
Revision History : 2007/04/20 趙林鵬 (中訊) 新規作成
Revision History : 2007/06/12 キョウ再平 (中訊) モデル 1178
*/

package webbroker3.equity.service.delegate.stdimpls;

import java.util.Calendar;
import java.util.List;

import com.fitechlabs.xtrade.kernel.data.Processors;
import com.fitechlabs.xtrade.kernel.data.QueryProcessor;
import com.fitechlabs.xtrade.plugin.tc.eqtype.data.EqtypeOrderUnitRow;
import com.fitechlabs.xtrade.plugin.tc.gentrade.GtlUtils;
import com.fitechlabs.xtrade.plugin.tc.gentrade.data.BranchParams;
import com.fitechlabs.xtrade.plugin.tc.gentrade.data.BranchRow;
import com.fitechlabs.xtrade.plugin.tc.gentrade.data.InstitutionParams;
import com.fitechlabs.xtrade.plugin.tc.gentrade.data.InstitutionRow;
import com.fitechlabs.xtrade.plugin.tc.gentrade.data.MainAccountParams;
import com.fitechlabs.xtrade.plugin.tc.gentrade.data.MainAccountRow;
import com.fitechlabs.xtrade.plugin.tc.gentrade.data.SystemPreferencesParams;
import com.fitechlabs.xtrade.plugin.tc.gentrade.data.SystemPreferencesRow;

import test.util.TestDBUtility;

import webbroker3.equity.data.HostEquityOrderExecNotifyParams;
import webbroker3.equity.data.HostEquityOrderExecNotifyRow;
import webbroker3.equity.message.WEB3EquityExecNotifyMainRequest;
import webbroker3.equity.service.delegate.stdimpls.WEB3AsynEquityOrderExecNotifyMainServiceImpl.WEB3EquityOrderExecNotifyMainTransactionCallback;
import webbroker3.gentrade.data.DaemonTriggerParams;
import webbroker3.gentrade.data.DaemonTriggerRow;
import webbroker3.gentrade.data.RequestCodesForReadParams;
import webbroker3.gentrade.data.RequestCodesForReadRow;
import webbroker3.mock.TestBaseForMock;
import webbroker3.util.WEB3DateUtility;
import webbroker3.util.WEB3LogUtility;

/**
 * （株式出来通知メインサービスImplテスト）<BR>
 *
 * @@author 趙林鵬
 * @@version 1.0
 */

public class WEB3AsynEquityOrderExecNotifyMainServiceImplTest extends TestBaseForMock
{
    /**
     * ログユーティリティ<BR>
     */
    private static WEB3LogUtility log =
         WEB3LogUtility.getInstance(WEB3AsynEquityOrderExecNotifyMainServiceImplTest.class);

    public WEB3AsynEquityOrderExecNotifyMainServiceImplTest(String arg0)
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

    public void testProcessCase0001()
    {
        
        final String STR_METHOD_NAME = " testProcessCase0001";
        log.entering(TEST_START + STR_METHOD_NAME);

        try
        {
            HostEquityOrderExecNotifyParams l_hostEquityOrderExecNotifyParams =
            getHostEquityOrderExecNotifyRow();
            l_hostEquityOrderExecNotifyParams.setOrderRequestNumber("11");
            TestDBUtility.deleteAll(HostEquityOrderExecNotifyRow.TYPE);        
            TestDBUtility.insertWithDel(l_hostEquityOrderExecNotifyParams);
            
            RequestCodesForReadParams l_requestCodesForReadParams =
            getRequestCodesForReadRow();
            l_requestCodesForReadParams.setRequestCode(l_hostEquityOrderExecNotifyParams.getRequestCode());
            TestDBUtility.deleteAll(RequestCodesForReadRow.TYPE);        
            TestDBUtility.insertWithDel(l_requestCodesForReadParams);
            
            MainAccountParams l_mainAccountParams = TestDBUtility.getMainAccountRow();
            l_mainAccountParams.setBranchCode("382");
            TestDBUtility.deleteAll(MainAccountRow.TYPE); 
            TestDBUtility.insertWithDel(l_mainAccountParams);

            InstitutionParams l_institutionParams = TestDBUtility.getInstitutionRow();
            l_institutionParams.setInstitutionCode(l_hostEquityOrderExecNotifyParams.getInstitutionCode());
            l_institutionParams.setInstitutionId(l_mainAccountParams.getInstitutionId());
            TestDBUtility.deleteAll(InstitutionRow.TYPE); 
            TestDBUtility.insertWithDel(l_institutionParams);
          
            BranchParams l_branchParams = TestDBUtility.getBranchRow();
            l_branchParams.setBranchId(l_mainAccountParams.getBranchId());
            l_branchParams.setBranchCode(l_hostEquityOrderExecNotifyParams.getBranchCode());
            TestDBUtility.deleteAll(BranchRow.TYPE); 
            TestDBUtility.insertWithDel(l_branchParams);

            SystemPreferencesParams l_systemPreferencesParams = TestDBUtility.getSystemPreferencesRow();
            l_systemPreferencesParams.setName("exec.notify.wait.seconds");
            TestDBUtility.deleteAll(SystemPreferencesRow.TYPE); 
            TestDBUtility.insertWithDel(l_systemPreferencesParams);
            
            DaemonTriggerParams l_daemonTriggerParams = getDaemonTriggerRow();
            l_daemonTriggerParams.setOrderRequestNumber(l_hostEquityOrderExecNotifyParams.getOrderRequestNumber());
            TestDBUtility.deleteAll(DaemonTriggerRow.TYPE); 
            TestDBUtility.insertWithDel(l_daemonTriggerParams);
            log.debug("更新前Status =========" + l_hostEquityOrderExecNotifyParams.getStatus());
            
            
            WEB3EquityExecNotifyMainRequest l_request = new WEB3EquityExecNotifyMainRequest();
            String[] l_strorderRequestNumberPrefixGroups = {"11"};
            l_request.orderRequestNumberPrefixGroup = l_strorderRequestNumberPrefixGroups;
            l_request.threadNo = new Long(l_daemonTriggerParams.getThreadNo());
            WEB3AsynEquityOrderExecNotifyMainServiceImpl l_impl =
                new WEB3AsynEquityOrderExecNotifyMainServiceImpl(l_request);
            
            WEB3EquityOrderExecNotifyMainTransactionCallback l_transactionCallBack =
                l_impl.new WEB3EquityOrderExecNotifyMainTransactionCallback();

            l_transactionCallBack.setOrderRequestNumberPrefixGroup(l_strorderRequestNumberPrefixGroups);
            
            l_transactionCallBack.setThreadNo(new Long(l_daemonTriggerParams.getThreadNo()));
            
            l_transactionCallBack.process();
            
            HostEquityOrderExecNotifyRow l_row = null;
            QueryProcessor l_processor = Processors.getDefaultProcessor();
            String l_strWhere = 
                "order_request_number = ?";
            Object[] l_objWhere = {"11"};
            List l_lstRecords = l_processor.doFindAllQuery(
                    HostEquityOrderExecNotifyRow.TYPE,
                    l_strWhere,
                    l_objWhere);
            
            l_row = (HostEquityOrderExecNotifyRow)l_lstRecords.get(0);
            
            log.debug("更新後Status =========" + l_row.getStatus());
            assertEquals("1", l_row.getStatus());
            
        }
        catch (Exception l_ex)
        {
            log.error(l_ex.getMessage(), l_ex);
            fail();
        }
        log.info(STR_METHOD_NAME + ">>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>test pass !!");
        log.exiting(TEST_END + STR_METHOD_NAME);
    }
    
    public void testProcessCase0002()
    {
        final String STR_METHOD_NAME = " testProcessCase0002";
        log.entering(TEST_START + STR_METHOD_NAME);

        try
        {
            HostEquityOrderExecNotifyParams l_hostEquityOrderExecNotifyParams =
            getHostEquityOrderExecNotifyRow();
            l_hostEquityOrderExecNotifyParams.setOrderRequestNumber("11");
            l_hostEquityOrderExecNotifyParams.setCreatedTimestamp(WEB3DateUtility.getDate("20070110", "yyyyMMdd"));
            TestDBUtility.deleteAll(HostEquityOrderExecNotifyRow.TYPE);
            TestDBUtility.insertWithDel(l_hostEquityOrderExecNotifyParams);
            
            RequestCodesForReadParams l_requestCodesForReadParams =
            getRequestCodesForReadRow();
            l_requestCodesForReadParams.setRequestCode(l_hostEquityOrderExecNotifyParams.getRequestCode());
            TestDBUtility.deleteAll(RequestCodesForReadRow.TYPE);        
            TestDBUtility.insertWithDel(l_requestCodesForReadParams);
            
            MainAccountParams l_mainAccountParams = TestDBUtility.getMainAccountRow();
            l_mainAccountParams.setBranchCode("381");
            TestDBUtility.deleteAll(MainAccountRow.TYPE); 
            TestDBUtility.insertWithDel(l_mainAccountParams);
            
            TestDBUtility.deleteAll(InstitutionRow.TYPE); 
            TestDBUtility.deleteAll(BranchRow.TYPE); 

            SystemPreferencesParams l_systemPreferencesParams = TestDBUtility.getSystemPreferencesRow();
            l_systemPreferencesParams.setName("exec.notify.wait.seconds");
            l_systemPreferencesParams.setValue(null);
            TestDBUtility.deleteAll(SystemPreferencesRow.TYPE); 
            TestDBUtility.insertWithDel(l_systemPreferencesParams);
            
            DaemonTriggerParams l_daemonTriggerParams = getDaemonTriggerRow();
            l_daemonTriggerParams.setOrderRequestNumber(l_hostEquityOrderExecNotifyParams.getOrderRequestNumber());
            TestDBUtility.deleteAll(DaemonTriggerRow.TYPE); 
            TestDBUtility.insertWithDel(l_daemonTriggerParams);

            log.debug("更新前Status =========" + l_hostEquityOrderExecNotifyParams.getStatus());

            WEB3EquityExecNotifyMainRequest l_request = new WEB3EquityExecNotifyMainRequest();
            String[] l_strorderRequestNumberPrefixGroups = {"11"};
            l_request.orderRequestNumberPrefixGroup = l_strorderRequestNumberPrefixGroups;
            l_request.threadNo = new Long(l_daemonTriggerParams.getThreadNo());
            WEB3AsynEquityOrderExecNotifyMainServiceImpl l_impl =
                new WEB3AsynEquityOrderExecNotifyMainServiceImpl(l_request);
            
            WEB3EquityOrderExecNotifyMainTransactionCallback l_transactionCallBack =
                l_impl.new WEB3EquityOrderExecNotifyMainTransactionCallback();

            l_transactionCallBack.setOrderRequestNumberPrefixGroup(l_strorderRequestNumberPrefixGroups);
            
            l_transactionCallBack.setThreadNo(new Long(l_daemonTriggerParams.getThreadNo()));
            
            l_transactionCallBack.process();
            
            HostEquityOrderExecNotifyRow l_row = null;
            QueryProcessor l_processor = Processors.getDefaultProcessor();
            String l_strWhere = 
                "order_request_number = ?";
            Object[] l_objWhere = {"11"};
            List l_lstRecords = l_processor.doFindAllQuery(
                HostEquityOrderExecNotifyRow.TYPE,
                l_strWhere,
                l_objWhere);
            
            l_row = (HostEquityOrderExecNotifyRow)l_lstRecords.get(0);
            
            log.debug("更新後Status =========" + l_row.getStatus());
            assertEquals("9", l_row.getStatus());

        }
        catch (Exception l_ex)
        {
            log.error(l_ex.getMessage(), l_ex);
            fail();
        }
        log.info(STR_METHOD_NAME + ">>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>test pass !!");
        log.exiting(TEST_END + STR_METHOD_NAME);
    }

    public void testProcessCase0003()
    {
        final String STR_METHOD_NAME = " testProcessCase0003";
        log.entering(TEST_START + STR_METHOD_NAME);

        try
        {
            HostEquityOrderExecNotifyParams l_hostEquityOrderExecNotifyParams =
            getHostEquityOrderExecNotifyRow();
            l_hostEquityOrderExecNotifyParams.setOrderRequestNumber("11");
            l_hostEquityOrderExecNotifyParams.setCreatedTimestamp(WEB3DateUtility.getDate("20070110", "yyyyMMdd"));
            TestDBUtility.deleteAll(HostEquityOrderExecNotifyRow.TYPE);
            TestDBUtility.insertWithDel(l_hostEquityOrderExecNotifyParams);
            
            RequestCodesForReadParams l_requestCodesForReadParams =
            getRequestCodesForReadRow();
            l_requestCodesForReadParams.setRequestCode(l_hostEquityOrderExecNotifyParams.getRequestCode());
            TestDBUtility.deleteAll(RequestCodesForReadRow.TYPE);        
            TestDBUtility.insertWithDel(l_requestCodesForReadParams);
            
            MainAccountParams l_mainAccountParams = TestDBUtility.getMainAccountRow();
            l_mainAccountParams.setBranchCode("381");
            TestDBUtility.deleteAll(MainAccountRow.TYPE); 
            TestDBUtility.insertWithDel(l_mainAccountParams);
            
            InstitutionParams l_institutionParams = TestDBUtility.getInstitutionRow();
            l_institutionParams.setInstitutionCode(l_hostEquityOrderExecNotifyParams.getInstitutionCode());
            l_institutionParams.setInstitutionId(l_mainAccountParams.getInstitutionId());
            TestDBUtility.deleteAll(InstitutionRow.TYPE); 
            TestDBUtility.insertWithDel(l_institutionParams);
            
            BranchParams l_branchParams = TestDBUtility.getBranchRow();
            l_branchParams.setBranchId(l_mainAccountParams.getBranchId());
            l_branchParams.setBranchCode(l_hostEquityOrderExecNotifyParams.getBranchCode());
            TestDBUtility.deleteAll(BranchRow.TYPE); 
            TestDBUtility.insertWithDel(l_branchParams);

            SystemPreferencesParams l_systemPreferencesParams = TestDBUtility.getSystemPreferencesRow();
            l_systemPreferencesParams.setName("exec.notify.wait.seconds");
            l_systemPreferencesParams.setValue(null);
            TestDBUtility.deleteAll(SystemPreferencesRow.TYPE); 
            TestDBUtility.insertWithDel(l_systemPreferencesParams);
            
            DaemonTriggerParams l_daemonTriggerParams = getDaemonTriggerRow();
            l_daemonTriggerParams.setOrderRequestNumber(l_hostEquityOrderExecNotifyParams.getOrderRequestNumber());
            TestDBUtility.deleteAll(DaemonTriggerRow.TYPE); 
            TestDBUtility.insertWithDel(l_daemonTriggerParams);
            
            log.debug("更新前Status =========" + l_hostEquityOrderExecNotifyParams.getStatus());
            
            
            WEB3EquityExecNotifyMainRequest l_request = new WEB3EquityExecNotifyMainRequest();
            String[] l_strorderRequestNumberPrefixGroups = {"11"};
            l_request.orderRequestNumberPrefixGroup = l_strorderRequestNumberPrefixGroups;
            l_request.threadNo = new Long(l_daemonTriggerParams.getThreadNo());
            WEB3AsynEquityOrderExecNotifyMainServiceImpl l_impl =
                new WEB3AsynEquityOrderExecNotifyMainServiceImpl(l_request);
            
            WEB3EquityOrderExecNotifyMainTransactionCallback l_transactionCallBack =
                l_impl.new WEB3EquityOrderExecNotifyMainTransactionCallback();

            l_transactionCallBack.setOrderRequestNumberPrefixGroup(l_strorderRequestNumberPrefixGroups);
            
            l_transactionCallBack.setThreadNo(new Long(l_daemonTriggerParams.getThreadNo()));
            
            l_transactionCallBack.process();
            
            HostEquityOrderExecNotifyRow l_row = null;
            QueryProcessor l_processor = Processors.getDefaultProcessor();
            String l_strWhere = 
                "order_request_number = ?";
            Object[] l_objWhere = {"11"};
            List l_lstRecords = l_processor.doFindAllQuery(
                HostEquityOrderExecNotifyRow.TYPE,
                l_strWhere,
                l_objWhere);
            
            l_row = (HostEquityOrderExecNotifyRow)l_lstRecords.get(0);
            
            log.debug("更新後Status =========" + l_row.getStatus());
            assertEquals("9", l_row.getStatus());

        }
        catch (Exception l_ex)
        {
            log.error(l_ex.getMessage(), l_ex);
            fail();
        }
        log.info(STR_METHOD_NAME + ">>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>test pass !!");
        log.exiting(TEST_END + STR_METHOD_NAME);
    }
    
    public void testProcessCase0004()
    {
        final String STR_METHOD_NAME = " testProcessCase0004 ";
        log.entering(TEST_START + STR_METHOD_NAME);

        try
        {
            HostEquityOrderExecNotifyParams l_hostEquityOrderExecNotifyParams =
            getHostEquityOrderExecNotifyRow();
            l_hostEquityOrderExecNotifyParams.setOrderRequestNumber("11");
            l_hostEquityOrderExecNotifyParams.setCreatedTimestamp(GtlUtils.getSystemTimestamp());
            TestDBUtility.deleteAll(HostEquityOrderExecNotifyRow.TYPE);
            TestDBUtility.insertWithDel(l_hostEquityOrderExecNotifyParams);
            
            RequestCodesForReadParams l_requestCodesForReadParams =
                getRequestCodesForReadRow();
            l_requestCodesForReadParams.setRequestCode(l_hostEquityOrderExecNotifyParams.getRequestCode());
            TestDBUtility.deleteAll(RequestCodesForReadRow.TYPE);        
            TestDBUtility.insertWithDel(l_requestCodesForReadParams);
            
            MainAccountParams l_mainAccountParams = TestDBUtility.getMainAccountRow();
            l_mainAccountParams.setBranchCode("381");
            TestDBUtility.deleteAll(MainAccountRow.TYPE); 
            TestDBUtility.insertWithDel(l_mainAccountParams);
            
            InstitutionParams l_institutionParams = TestDBUtility.getInstitutionRow();
            l_institutionParams.setInstitutionCode(l_hostEquityOrderExecNotifyParams.getInstitutionCode());
            l_institutionParams.setInstitutionId(l_mainAccountParams.getInstitutionId());
            TestDBUtility.deleteAll(InstitutionRow.TYPE); 
            TestDBUtility.insertWithDel(l_institutionParams);
            
            BranchParams l_branchParams = TestDBUtility.getBranchRow();
            l_branchParams.setBranchId(l_mainAccountParams.getBranchId());
            l_branchParams.setBranchCode(l_hostEquityOrderExecNotifyParams.getBranchCode());
            TestDBUtility.deleteAll(BranchRow.TYPE); 
            TestDBUtility.insertWithDel(l_branchParams);

            SystemPreferencesParams l_systemPreferencesParams = TestDBUtility.getSystemPreferencesRow();
            l_systemPreferencesParams.setName("exec.notify.wait.seconds");
            l_systemPreferencesParams.setValue("300000");
            TestDBUtility.deleteAll(SystemPreferencesRow.TYPE); 
            TestDBUtility.insertWithDel(l_systemPreferencesParams);
            
            DaemonTriggerParams l_daemonTriggerParams = getDaemonTriggerRow();
            l_daemonTriggerParams.setOrderRequestNumber(l_hostEquityOrderExecNotifyParams.getOrderRequestNumber());
            TestDBUtility.deleteAll(DaemonTriggerRow.TYPE); 
            TestDBUtility.insertWithDel(l_daemonTriggerParams);
            
            log.debug("更新前Status =========" + l_hostEquityOrderExecNotifyParams.getStatus());
            
            
            WEB3EquityExecNotifyMainRequest l_request = new WEB3EquityExecNotifyMainRequest();
            String[] l_strorderRequestNumberPrefixGroups = {"11"};
            l_request.orderRequestNumberPrefixGroup = l_strorderRequestNumberPrefixGroups;
            l_request.threadNo = new Long(l_daemonTriggerParams.getThreadNo());
            WEB3AsynEquityOrderExecNotifyMainServiceImpl l_impl =
                new WEB3AsynEquityOrderExecNotifyMainServiceImpl(l_request);
            
            WEB3EquityOrderExecNotifyMainTransactionCallback l_transactionCallBack =
                l_impl.new WEB3EquityOrderExecNotifyMainTransactionCallback();

            l_transactionCallBack.setOrderRequestNumberPrefixGroup(l_strorderRequestNumberPrefixGroups);
            
            l_transactionCallBack.setThreadNo(new Long(l_daemonTriggerParams.getThreadNo()));
            
            l_transactionCallBack.process();
            
            HostEquityOrderExecNotifyRow l_row = null;
            QueryProcessor l_processor = Processors.getDefaultProcessor();
            String l_strWhere = 
                "order_request_number = ?";
            Object[] l_objWhere = {"11"};
            List l_lstRecords = l_processor.doFindAllQuery(
                HostEquityOrderExecNotifyRow.TYPE,
                l_strWhere,
                l_objWhere);
            
            l_row = (HostEquityOrderExecNotifyRow)l_lstRecords.get(0);
            
            log.debug("更新後Status =========" + l_row.getStatus());
            assertEquals("0", l_row.getStatus());

        }
        catch (Exception l_ex)
        {
            log.error(l_ex.getMessage(), l_ex);
            fail();
        }
        log.info(STR_METHOD_NAME + ">>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>test pass !!");
        log.exiting(TEST_END + STR_METHOD_NAME);
    }
    
    /**
     * 株式出来通知キューテーブル(host_eqtype_swap_receipt)
     */
    public static HostEquityOrderExecNotifyParams getHostEquityOrderExecNotifyRow()
    {
        HostEquityOrderExecNotifyParams l_hostEquityOrderExecNotifyParams = new HostEquityOrderExecNotifyParams();
        l_hostEquityOrderExecNotifyParams.setRequestCode("AI811");
        l_hostEquityOrderExecNotifyParams.setInstitutionCode("0D");
        l_hostEquityOrderExecNotifyParams.setBranchCode("381");
        l_hostEquityOrderExecNotifyParams.setAccountCode("2512246");
        l_hostEquityOrderExecNotifyParams.setTraderCode("1234");
        l_hostEquityOrderExecNotifyParams.setOrderRequestNumber("1");
        l_hostEquityOrderExecNotifyParams.setExecQuantity(1000);
        l_hostEquityOrderExecNotifyParams.setExecPrice(100);
        l_hostEquityOrderExecNotifyParams.setExecTimestamp(Calendar.getInstance().getTime());
        l_hostEquityOrderExecNotifyParams.setDealedType("0");
        l_hostEquityOrderExecNotifyParams.setStatus("0");
        l_hostEquityOrderExecNotifyParams.setCreatedTimestamp(Calendar.getInstance().getTime());
        l_hostEquityOrderExecNotifyParams.setLastUpdatedTimestamp(Calendar.getInstance().getTime());
        l_hostEquityOrderExecNotifyParams.setVirtualServerNumberMarket("1");
        l_hostEquityOrderExecNotifyParams.setNoticeType("02");
        l_hostEquityOrderExecNotifyParams.setNoticeNumber(100);
        l_hostEquityOrderExecNotifyParams.setExecNumber(100);
        
        return l_hostEquityOrderExecNotifyParams;
    }
    
    /**
     * 処理対象データコードテーブル(request_codes_for_read)
     */
    public static RequestCodesForReadParams getRequestCodesForReadRow()
    {
        RequestCodesForReadParams l_requestCodesForReadParams = new RequestCodesForReadParams();
        l_requestCodesForReadParams.setRequestCode("AI826");
        l_requestCodesForReadParams.setPtype("margin_swapOrderNotify");
        l_requestCodesForReadParams.setSubmitOrderRouteDiv("0");
        l_requestCodesForReadParams.setCreatedTimestamp(Calendar.getInstance().getTime());
        l_requestCodesForReadParams.setLastUpdatedTimestamp(Calendar.getInstance().getTime());
        return l_requestCodesForReadParams;
    }

     /**
      * デーモントリガーテーブル(daemon_trigger)
      */
    public static DaemonTriggerParams getDaemonTriggerRow()
    {
        DaemonTriggerParams l_daemonTriggerParams = new DaemonTriggerParams();
        l_daemonTriggerParams.setTriggerType("1");
        l_daemonTriggerParams.setThreadNo(1);
        l_daemonTriggerParams.setOrderRequestNumber("11");
        l_daemonTriggerParams.setRangeFrom(0);
        l_daemonTriggerParams.setRangeTo(999999999);
        l_daemonTriggerParams.setTriggerStatus("0");
        l_daemonTriggerParams.setTriggerDate(Calendar.getInstance().getTime());
        return l_daemonTriggerParams;
    }

    public void testProcessCase0005()
    {
        final String STR_METHOD_NAME = " testProcessCase0005 ";
        log.entering(TEST_START + STR_METHOD_NAME);

        try
        {
            HostEquityOrderExecNotifyParams l_hostEquityOrderExecNotifyParams =
            getHostEquityOrderExecNotifyRow();
            l_hostEquityOrderExecNotifyParams.setOrderRequestNumber("11");
            l_hostEquityOrderExecNotifyParams.setCreatedTimestamp(GtlUtils.getSystemTimestamp());
            TestDBUtility.deleteAll(HostEquityOrderExecNotifyRow.TYPE);
            TestDBUtility.insertWithDel(l_hostEquityOrderExecNotifyParams);
            
            RequestCodesForReadParams l_requestCodesForReadParams =
                getRequestCodesForReadRow();
            l_requestCodesForReadParams.setRequestCode(l_hostEquityOrderExecNotifyParams.getRequestCode());
            TestDBUtility.deleteAll(RequestCodesForReadRow.TYPE);        
            TestDBUtility.insertWithDel(l_requestCodesForReadParams);
            
            MainAccountParams l_mainAccountParams = TestDBUtility.getMainAccountRow();
            l_mainAccountParams.setBranchCode("381");
            TestDBUtility.deleteAll(MainAccountRow.TYPE); 
            TestDBUtility.insertWithDel(l_mainAccountParams);
            
            InstitutionParams l_institutionParams = TestDBUtility.getInstitutionRow();
            l_institutionParams.setInstitutionCode(l_hostEquityOrderExecNotifyParams.getInstitutionCode());
            l_institutionParams.setInstitutionId(l_mainAccountParams.getInstitutionId());
            TestDBUtility.deleteAll(InstitutionRow.TYPE); 
            TestDBUtility.insertWithDel(l_institutionParams);
            
            BranchParams l_branchParams = TestDBUtility.getBranchRow();
            l_branchParams.setBranchId(l_mainAccountParams.getBranchId());
            l_branchParams.setBranchCode(l_hostEquityOrderExecNotifyParams.getBranchCode());
            TestDBUtility.deleteAll(BranchRow.TYPE); 
            TestDBUtility.insertWithDel(l_branchParams);

            SystemPreferencesParams l_systemPreferencesParams = TestDBUtility.getSystemPreferencesRow();
            l_systemPreferencesParams.setName("exec.notify.wait.seconds");
            l_systemPreferencesParams.setValue("300000");
            TestDBUtility.deleteAll(SystemPreferencesRow.TYPE); 
            TestDBUtility.insertWithDel(l_systemPreferencesParams);
            
            DaemonTriggerParams l_daemonTriggerParams = getDaemonTriggerRow();
            l_daemonTriggerParams.setOrderRequestNumber(l_hostEquityOrderExecNotifyParams.getOrderRequestNumber());
            TestDBUtility.deleteAll(DaemonTriggerRow.TYPE); 
            TestDBUtility.insertWithDel(l_daemonTriggerParams);

            TestDBUtility.deleteAll(EqtypeOrderUnitRow.TYPE); 
            TestDBUtility.deleteAll(MainAccountRow.TYPE); 
            
            log.debug("更新前Status =========" + l_hostEquityOrderExecNotifyParams.getStatus());
            
            
            WEB3EquityExecNotifyMainRequest l_request = new WEB3EquityExecNotifyMainRequest();
            String[] l_strorderRequestNumberPrefixGroups = {"11"};
            l_request.orderRequestNumberPrefixGroup = l_strorderRequestNumberPrefixGroups;
            l_request.threadNo = new Long(l_daemonTriggerParams.getThreadNo());
            WEB3AsynEquityOrderExecNotifyMainServiceImpl l_impl =
                new WEB3AsynEquityOrderExecNotifyMainServiceImpl(l_request);
            
            WEB3EquityOrderExecNotifyMainTransactionCallback l_transactionCallBack =
                l_impl.new WEB3EquityOrderExecNotifyMainTransactionCallback();

            l_transactionCallBack.setOrderRequestNumberPrefixGroup(l_strorderRequestNumberPrefixGroups);
            
            l_transactionCallBack.setThreadNo(new Long(l_daemonTriggerParams.getThreadNo()));
            
            l_transactionCallBack.process();
            
            HostEquityOrderExecNotifyRow l_row = null;
            QueryProcessor l_processor = Processors.getDefaultProcessor();
            String l_strWhere = 
                "order_request_number = ?";
            Object[] l_objWhere = {"11"};
            List l_lstRecords = l_processor.doFindAllQuery(
                HostEquityOrderExecNotifyRow.TYPE,
                l_strWhere,
                l_objWhere);
            
            l_row = (HostEquityOrderExecNotifyRow)l_lstRecords.get(0);
            
            log.debug("更新後Status =========" + l_row.getStatus());
            assertEquals("1", l_row.getStatus());

        }
        catch (Exception l_ex)
        {
            log.error(l_ex.getMessage(), l_ex);
            fail();
        }
        log.info(STR_METHOD_NAME + ">>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>test pass !!");
        log.exiting(TEST_END + STR_METHOD_NAME);
    }

    public void testProcessCase0006()
    {
        final String STR_METHOD_NAME = " testProcessCase0006 ";
        log.entering(TEST_START + STR_METHOD_NAME);

        try
        {
            HostEquityOrderExecNotifyParams l_hostEquityOrderExecNotifyParams =
            getHostEquityOrderExecNotifyRow();
            l_hostEquityOrderExecNotifyParams.setOrderRequestNumber("11");
            l_hostEquityOrderExecNotifyParams.setCreatedTimestamp(GtlUtils.getSystemTimestamp());
            TestDBUtility.deleteAll(HostEquityOrderExecNotifyRow.TYPE);
            TestDBUtility.insertWithDel(l_hostEquityOrderExecNotifyParams);
            
            RequestCodesForReadParams l_requestCodesForReadParams =
                getRequestCodesForReadRow();
            l_requestCodesForReadParams.setRequestCode(l_hostEquityOrderExecNotifyParams.getRequestCode());
            TestDBUtility.deleteAll(RequestCodesForReadRow.TYPE);        
            TestDBUtility.insertWithDel(l_requestCodesForReadParams);
            
            MainAccountParams l_mainAccountParams = TestDBUtility.getMainAccountRow();
            l_mainAccountParams.setBranchCode("381");
            TestDBUtility.deleteAll(MainAccountRow.TYPE); 
            TestDBUtility.insertWithDel(l_mainAccountParams);
            
            InstitutionParams l_institutionParams = TestDBUtility.getInstitutionRow();
            l_institutionParams.setInstitutionCode(l_hostEquityOrderExecNotifyParams.getInstitutionCode());
            l_institutionParams.setInstitutionId(l_mainAccountParams.getInstitutionId());
            TestDBUtility.deleteAll(InstitutionRow.TYPE); 
            TestDBUtility.insertWithDel(l_institutionParams);
            
            BranchParams l_branchParams = TestDBUtility.getBranchRow();
            l_branchParams.setBranchId(l_mainAccountParams.getBranchId());
            l_branchParams.setBranchCode(l_hostEquityOrderExecNotifyParams.getBranchCode());
            TestDBUtility.deleteAll(BranchRow.TYPE); 
            TestDBUtility.insertWithDel(l_branchParams);

            SystemPreferencesParams l_systemPreferencesParams = TestDBUtility.getSystemPreferencesRow();
            l_systemPreferencesParams.setName("exec.notify.wait.seconds");
            l_systemPreferencesParams.setValue("300000");
            TestDBUtility.deleteAll(SystemPreferencesRow.TYPE); 
            TestDBUtility.insertWithDel(l_systemPreferencesParams);
            
            DaemonTriggerParams l_daemonTriggerParams = getDaemonTriggerRow();
            l_daemonTriggerParams.setOrderRequestNumber(l_hostEquityOrderExecNotifyParams.getOrderRequestNumber());
            TestDBUtility.deleteAll(DaemonTriggerRow.TYPE); 
            TestDBUtility.insertWithDel(l_daemonTriggerParams);

            TestDBUtility.deleteAll(EqtypeOrderUnitRow.TYPE); 
//            TestDBUtility.deleteAll(MainAccountRow.TYPE);
            
            log.debug("更新前Status =========" + l_hostEquityOrderExecNotifyParams.getStatus());
            
            
            WEB3EquityExecNotifyMainRequest l_request = new WEB3EquityExecNotifyMainRequest();
            String[] l_strorderRequestNumberPrefixGroups = {"11"};
            l_request.orderRequestNumberPrefixGroup = l_strorderRequestNumberPrefixGroups;
            l_request.threadNo = new Long(l_daemonTriggerParams.getThreadNo());
            WEB3AsynEquityOrderExecNotifyMainServiceImpl l_impl =
                new WEB3AsynEquityOrderExecNotifyMainServiceImpl(l_request);
            
            WEB3EquityOrderExecNotifyMainTransactionCallback l_transactionCallBack =
                l_impl.new WEB3EquityOrderExecNotifyMainTransactionCallback();

            l_transactionCallBack.setOrderRequestNumberPrefixGroup(l_strorderRequestNumberPrefixGroups);
            
            l_transactionCallBack.setThreadNo(new Long(l_daemonTriggerParams.getThreadNo()));
            
            assertEquals(null, l_transactionCallBack.process());
            
//            HostEquityOrderExecNotifyRow l_row = null;
//            QueryProcessor l_processor = Processors.getDefaultProcessor();
//            String l_strWhere = 
//                "order_request_number = ?";
//            Object[] l_objWhere = {"11"};
//            List l_lstRecords = l_processor.doFindAllQuery(
//                HostEquityOrderExecNotifyRow.TYPE,
//                l_strWhere,
//                l_objWhere);
//            
//            l_row = (HostEquityOrderExecNotifyRow)l_lstRecords.get(0);
//            
//            log.debug("更新後Status =========" + l_row.getStatus());
//            assertEquals("1", l_row.getStatus());

        }
        catch (Exception l_ex)
        {
            log.error(l_ex.getMessage(), l_ex);
            fail();
        }
        log.info(STR_METHOD_NAME + ">>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>test pass !!");
        log.exiting(TEST_END + STR_METHOD_NAME);
    }

    public void testProcessCase0007()
    {
        final String STR_METHOD_NAME = " testProcessCase0007 ";
        log.entering(TEST_START + STR_METHOD_NAME);

        try
        {
            HostEquityOrderExecNotifyParams l_hostEquityOrderExecNotifyParams =
            getHostEquityOrderExecNotifyRow();
            l_hostEquityOrderExecNotifyParams.setOrderRequestNumber("11");
            l_hostEquityOrderExecNotifyParams.setCreatedTimestamp(GtlUtils.getSystemTimestamp());
            TestDBUtility.deleteAll(HostEquityOrderExecNotifyRow.TYPE);
            TestDBUtility.insertWithDel(l_hostEquityOrderExecNotifyParams);
            
            RequestCodesForReadParams l_requestCodesForReadParams =
                getRequestCodesForReadRow();
            l_requestCodesForReadParams.setRequestCode(l_hostEquityOrderExecNotifyParams.getRequestCode());
            TestDBUtility.deleteAll(RequestCodesForReadRow.TYPE);        
            TestDBUtility.insertWithDel(l_requestCodesForReadParams);
            
            MainAccountParams l_mainAccountParams = TestDBUtility.getMainAccountRow();
            l_mainAccountParams.setBranchCode("381");
            TestDBUtility.deleteAll(MainAccountRow.TYPE); 
            TestDBUtility.insertWithDel(l_mainAccountParams);
            
            InstitutionParams l_institutionParams = TestDBUtility.getInstitutionRow();
            l_institutionParams.setInstitutionCode(l_hostEquityOrderExecNotifyParams.getInstitutionCode());
            l_institutionParams.setInstitutionId(l_mainAccountParams.getInstitutionId());
            TestDBUtility.deleteAll(InstitutionRow.TYPE); 
//           TestDBUtility.insertWithDel(l_institutionParams);
            
            BranchParams l_branchParams = TestDBUtility.getBranchRow();
            l_branchParams.setBranchId(l_mainAccountParams.getBranchId());
            l_branchParams.setBranchCode(l_hostEquityOrderExecNotifyParams.getBranchCode());
            TestDBUtility.deleteAll(BranchRow.TYPE); 
            TestDBUtility.insertWithDel(l_branchParams);

            SystemPreferencesParams l_systemPreferencesParams = TestDBUtility.getSystemPreferencesRow();
            l_systemPreferencesParams.setName("exec.notify.wait.seconds");
            l_systemPreferencesParams.setValue("300000");
            TestDBUtility.deleteAll(SystemPreferencesRow.TYPE); 
            TestDBUtility.insertWithDel(l_systemPreferencesParams);
            
            DaemonTriggerParams l_daemonTriggerParams = getDaemonTriggerRow();
            l_daemonTriggerParams.setOrderRequestNumber(l_hostEquityOrderExecNotifyParams.getOrderRequestNumber());
            TestDBUtility.deleteAll(DaemonTriggerRow.TYPE); 
            TestDBUtility.insertWithDel(l_daemonTriggerParams);

//           TestDBUtility.deleteAll(EqtypeOrderUnitRow.TYPE); 
//            TestDBUtility.deleteAll(MainAccountRow.TYPE);
            
            log.debug("更新前Status =========" + l_hostEquityOrderExecNotifyParams.getStatus());
            
            
            WEB3EquityExecNotifyMainRequest l_request = new WEB3EquityExecNotifyMainRequest();
            String[] l_strorderRequestNumberPrefixGroups = {"11"};
            l_request.orderRequestNumberPrefixGroup = l_strorderRequestNumberPrefixGroups;
            l_request.threadNo = new Long(l_daemonTriggerParams.getThreadNo());
            WEB3AsynEquityOrderExecNotifyMainServiceImpl l_impl =
                new WEB3AsynEquityOrderExecNotifyMainServiceImpl(l_request);
            
            WEB3EquityOrderExecNotifyMainTransactionCallback l_transactionCallBack =
                l_impl.new WEB3EquityOrderExecNotifyMainTransactionCallback();

            l_transactionCallBack.setOrderRequestNumberPrefixGroup(l_strorderRequestNumberPrefixGroups);
            
            l_transactionCallBack.setThreadNo(new Long(l_daemonTriggerParams.getThreadNo()));
            
            assertEquals(null, l_transactionCallBack.process());
            
//            HostEquityOrderExecNotifyRow l_row = null;
//            QueryProcessor l_processor = Processors.getDefaultProcessor();
//            String l_strWhere = 
//                "order_request_number = ?";
//            Object[] l_objWhere = {"11"};
//            List l_lstRecords = l_processor.doFindAllQuery(
//                HostEquityOrderExecNotifyRow.TYPE,
//                l_strWhere,
//                l_objWhere);
//            
//            l_row = (HostEquityOrderExecNotifyRow)l_lstRecords.get(0);
//            
//            log.debug("更新後Status =========" + l_row.getStatus());
//            assertEquals("1", l_row.getStatus());

        }
        catch (Exception l_ex)
        {
            log.error(l_ex.getMessage(), l_ex);
            fail();
        }
        log.info(STR_METHOD_NAME + ">>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>test pass !!");
        log.exiting(TEST_END + STR_METHOD_NAME);
    }
}
@
