head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.04.07.00.56.10;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	5444d9d078c63f4;
filename	WEB3EquityOrderNotifyServiceImplTest.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@package webbroker3.equity.service.delegate.stdimpls;

import java.util.Calendar;
import java.util.List;

import com.fitechlabs.xtrade.plugin.tc.gentrade.GtlUtils;
import com.fitechlabs.xtrade.plugin.tc.gentrade.data.BranchParams;
import com.fitechlabs.xtrade.plugin.tc.gentrade.data.BranchRow;
import com.fitechlabs.xtrade.plugin.tc.gentrade.data.MainAccountParams;
import com.fitechlabs.xtrade.plugin.tc.gentrade.data.MainAccountRow;

import test.util.TestDBUtility;

import webbroker3.common.define.WEB3StatusDef;
import webbroker3.equity.data.HostEqtypeOrderReceiptDao;
import webbroker3.equity.data.HostEqtypeOrderReceiptParams;
import webbroker3.equity.data.HostEqtypeOrderReceiptRow;
import webbroker3.equity.message.WEB3EquityOrderNotifyRequest;
import webbroker3.equity.service.delegate.stdimpls.WEB3EquityOrderNotifyServiceImpl.WEB3EquityOrderNotifyServiceTransactionCallback;
import webbroker3.gentrade.data.RequestCodesForReadParams;
import webbroker3.mock.TestBaseForMock;
import webbroker3.util.WEB3DateUtility;
import webbroker3.util.WEB3LogUtility;

public class WEB3EquityOrderNotifyServiceImplTest extends TestBaseForMock
{
    /**
     * ログユーティリティ<BR>
     */
    private static WEB3LogUtility log =
         WEB3LogUtility.getInstance(
                 WEB3EquityOrderNotifyServiceImplTest.class);
    
    public WEB3EquityOrderNotifyServiceImplTest(String arg0)
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

    /*
     * Test method for 'webbroker3.equity.service.delegate.stdimpls.WEB3EquityOrderNotifyServiceImpl.WEB3EquityOrderNotifyServiceImpl()'
     */
    public void testProcessC1()
    {
        final String STR_METHOD_NAME = "testProcessC1()";
        log.entering(TEST_START + STR_METHOD_NAME);
        
        MainAccountParams l_mainAccountParams =
            TestDBUtility.getMainAccountRow();
        l_mainAccountParams.setAccountId(333812512203L);
        l_mainAccountParams.setInstitutionId(63);
        
        BranchParams l_branchParams =
            TestDBUtility.getBranchRow();
        l_branchParams.setInstitutionCode("0D");
        l_branchParams.setInstitutionId(63);
        
        HostEqtypeOrderReceiptParams l_params = new HostEqtypeOrderReceiptParams();
        l_params.setInstitutionCode("2D");
        l_params.setBranchCode("381");
        l_params.setAccountCode("2512246");
        l_params.setOrderRequestNumber("111");
        l_params.setSonarTradedCode("11");
        l_params.setStatus("0");
        l_params.setRequestCode("AI826");
        
        RequestCodesForReadParams l_requestCodesForReadParams = getRequestCodesForReadRow();
        l_requestCodesForReadParams.setPtype(WEB3EquityOrderNotifyRequest.PTYPE);
        try
        {
            TestDBUtility.deleteAll(BranchRow.TYPE);
            TestDBUtility.deleteAll(MainAccountRow.TYPE);
            TestDBUtility.deleteAll(HostEqtypeOrderReceiptRow.TYPE);
            TestDBUtility.deleteAll(HostEqtypeOrderReceiptRow.TYPE);
            
            TestDBUtility.insertWithDel(l_branchParams);
            TestDBUtility.insertWithDel(l_mainAccountParams);
            TestDBUtility.insertWithDel(l_params);
            TestDBUtility.insertWithDel(l_requestCodesForReadParams);
        }
        catch (Exception l_ex)
        {
            log.error(l_ex.getMessage(), l_ex);
            fail();
        }
        try
        {
            WEB3EquityOrderNotifyServiceImpl l_impl = new WEB3EquityOrderNotifyServiceImpl();
            WEB3EquityOrderNotifyServiceTransactionCallback l_transactionCallback =
                l_impl.new WEB3EquityOrderNotifyServiceTransactionCallback();
            
            l_transactionCallback.process();
    
            List l_list = HostEqtypeOrderReceiptDao.findRowsByOrderRequestNumber("111");
            HostEqtypeOrderReceiptRow l_row =(HostEqtypeOrderReceiptRow)l_list.get(0);
            
            assertEquals(WEB3StatusDef.DEALT, l_row.getStatus());
            assertEquals(WEB3DateUtility.formatDate(GtlUtils.getSystemTimestamp(),"yyyyMMdd"), 
                    WEB3DateUtility.formatDate(l_row.getLastUpdatedTimestamp(),"yyyyMMdd"));
        }
        catch (Exception l_ex)
        {
            log.error(STR_METHOD_NAME, l_ex);
            fail();
        }
    
    log.exiting(TEST_END + STR_METHOD_NAME);
    }
    
    public void testProcessC2()
    {
        final String STR_METHOD_NAME = "testProcessC2()";
        log.entering(TEST_START + STR_METHOD_NAME);
        
        MainAccountParams l_mainAccountParams =
            TestDBUtility.getMainAccountRow();
        l_mainAccountParams.setAccountId(333812512203L);
        l_mainAccountParams.setInstitutionId(63);
        
        HostEqtypeOrderReceiptParams l_params = new HostEqtypeOrderReceiptParams();
        l_params.setInstitutionCode("0D");
        l_params.setBranchCode("381");
        l_params.setAccountCode("2512246");
        l_params.setOrderRequestNumber("111");
        l_params.setSonarTradedCode("11");
        l_params.setStatus("0");
        l_params.setRequestCode("AI826");
        
        RequestCodesForReadParams l_requestCodesForReadParams = getRequestCodesForReadRow();
        l_requestCodesForReadParams.setPtype(WEB3EquityOrderNotifyRequest.PTYPE);
        try
        {
            TestDBUtility.deleteAll(MainAccountRow.TYPE);
            TestDBUtility.deleteAll(HostEqtypeOrderReceiptRow.TYPE);
            TestDBUtility.deleteAll(HostEqtypeOrderReceiptRow.TYPE);
            
            
            TestDBUtility.insertWithDel(l_mainAccountParams);
            TestDBUtility.insertWithDel(l_params);
            TestDBUtility.insertWithDel(l_requestCodesForReadParams);
        }
        catch (Exception l_ex)
        {
            log.error(l_ex.getMessage(), l_ex);
            fail();
        }
        try
        {
            WEB3EquityOrderNotifyServiceImpl l_impl = new WEB3EquityOrderNotifyServiceImpl();
            WEB3EquityOrderNotifyServiceTransactionCallback l_transactionCallback =
                l_impl.new WEB3EquityOrderNotifyServiceTransactionCallback();
            
            l_transactionCallback.process();
    
            List l_list = HostEqtypeOrderReceiptDao.findRowsByOrderRequestNumber("111");
            HostEqtypeOrderReceiptRow l_row =(HostEqtypeOrderReceiptRow)l_list.get(0);
            
            assertEquals(WEB3StatusDef.DATA_ERROR, l_row.getStatus());
            assertEquals(WEB3DateUtility.formatDate(GtlUtils.getSystemTimestamp(),"yyyyMMdd"), 
                    WEB3DateUtility.formatDate(l_row.getLastUpdatedTimestamp(),"yyyyMMdd"));
        }
        catch (Exception l_ex)
        {
            log.error(STR_METHOD_NAME, l_ex);
            fail();
        }
    
    log.exiting(TEST_END + STR_METHOD_NAME);
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

}
@
