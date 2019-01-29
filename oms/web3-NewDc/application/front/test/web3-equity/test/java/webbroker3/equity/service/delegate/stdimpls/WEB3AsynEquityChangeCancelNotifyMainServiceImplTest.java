head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.04.07.00.56.14;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	5444d9d078c63f4;
filename	WEB3AsynEquityChangeCancelNotifyMainServiceImplTest.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : 株式訂正取消通知メインサービスImplテスト(WEB3AsynEquityChangeCancelNotifyMainServiceImplTest.java)
Author Name      : Daiwa Institute of Research
Revision History : 2007/04/28 趙林鵬 (中訊) 新規作成
*/

package webbroker3.equity.service.delegate.stdimpls;

import java.util.Calendar;
import java.util.List;

import com.fitechlabs.xtrade.kernel.data.Processors;
import com.fitechlabs.xtrade.kernel.data.QueryProcessor;
import com.fitechlabs.xtrade.plugin.tc.gentrade.data.MainAccountParams;
import com.fitechlabs.xtrade.plugin.tc.gentrade.data.MainAccountRow;

import test.util.TestDBUtility;

import webbroker3.equity.data.HostEqtypeOrderClmdReceiptParams;
import webbroker3.equity.data.HostEqtypeOrderClmdReceiptRow;
import webbroker3.equity.message.WEB3EquityChangeCancelNotifyMainRequest;
import webbroker3.equity.service.delegate.stdimpls.WEB3AsynEquityChangeCancelNotifyMainServiceImpl.WEB3EquityChangeCancelNotifyMainTransactionCallback;
import webbroker3.gentrade.data.RequestCodesForReadParams;
import webbroker3.gentrade.data.RequestCodesForReadRow;
import webbroker3.mock.TestBaseForMock;
import webbroker3.util.WEB3LogUtility;

/**
 * （株式訂正取消通知メインサービスImplテスト）<BR>
 *
 * @@author 趙林鵬
 * @@version 1.0
 */

public class WEB3AsynEquityChangeCancelNotifyMainServiceImplTest extends TestBaseForMock
{
    /**
     * ログユーティリティ<BR>
     */
    private static WEB3LogUtility log =
         WEB3LogUtility.getInstance(WEB3AsynEquityChangeCancelNotifyMainServiceImplTest.class);
    
    public WEB3AsynEquityChangeCancelNotifyMainServiceImplTest(String arg0)
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
            HostEqtypeOrderClmdReceiptParams l_hostEqtypeOrderClmdReceiptParams =
                getHostEqtypeOrderClmdReceiptRow();
            l_hostEqtypeOrderClmdReceiptParams.setStatus("0");
            l_hostEqtypeOrderClmdReceiptParams.setOrderRequestNumber("11");
            TestDBUtility.deleteAll(HostEqtypeOrderClmdReceiptRow.TYPE);        
            TestDBUtility.insertWithDel(l_hostEqtypeOrderClmdReceiptParams);
            
            RequestCodesForReadParams l_requestCodesForReadParams =
            getRequestCodesForReadRow();
            l_requestCodesForReadParams.setRequestCode(l_hostEqtypeOrderClmdReceiptParams.getRequestCode());
            TestDBUtility.deleteAll(RequestCodesForReadRow.TYPE);        
            TestDBUtility.insertWithDel(l_requestCodesForReadParams);
            
            MainAccountParams l_mainAccountParams = TestDBUtility.getMainAccountRow();
            l_mainAccountParams.setBranchCode("382");
            TestDBUtility.deleteAll(MainAccountRow.TYPE); 
            TestDBUtility.insertWithDel(l_mainAccountParams);
            
            log.debug("更新前Status =========" + l_hostEqtypeOrderClmdReceiptParams.getStatus());

            WEB3EquityChangeCancelNotifyMainRequest l_request = new WEB3EquityChangeCancelNotifyMainRequest();
            String[] l_strorderRequestNumberPrefixGroups = {"11"};
            l_request.orderRequestNumberPrefixGroup = l_strorderRequestNumberPrefixGroups;
            WEB3AsynEquityChangeCancelNotifyMainServiceImpl l_impl =
                new WEB3AsynEquityChangeCancelNotifyMainServiceImpl(l_request);
            
            WEB3EquityChangeCancelNotifyMainTransactionCallback l_transactionCallBack =
                l_impl.new WEB3EquityChangeCancelNotifyMainTransactionCallback();

            l_transactionCallBack.setOrderRequestNumberPrefixGroup(l_strorderRequestNumberPrefixGroups);
            
            l_transactionCallBack.process();
            
            HostEqtypeOrderClmdReceiptRow l_row = null;
            QueryProcessor l_processor = Processors.getDefaultProcessor();
            String l_strWhere = 
                "order_request_number = ?";
            Object[] l_objWhere = {"11"};
            List l_lstRecords = l_processor.doFindAllQuery(
                    HostEqtypeOrderClmdReceiptRow.TYPE,
                    l_strWhere,
                    l_objWhere);
            
            l_row = (HostEqtypeOrderClmdReceiptRow)l_lstRecords.get(0);
            
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
            HostEqtypeOrderClmdReceiptParams l_hostEqtypeOrderClmdReceiptParams =
                getHostEqtypeOrderClmdReceiptRow();
            l_hostEqtypeOrderClmdReceiptParams.setStatus("0");
            l_hostEqtypeOrderClmdReceiptParams.setOrderRequestNumber("11");
            TestDBUtility.deleteAll(HostEqtypeOrderClmdReceiptRow.TYPE);        
            TestDBUtility.insertWithDel(l_hostEqtypeOrderClmdReceiptParams);
            
            RequestCodesForReadParams l_requestCodesForReadParams =
            getRequestCodesForReadRow();
            l_requestCodesForReadParams.setRequestCode(l_hostEqtypeOrderClmdReceiptParams.getRequestCode());
            TestDBUtility.deleteAll(RequestCodesForReadRow.TYPE);        
            TestDBUtility.insertWithDel(l_requestCodesForReadParams);
            
            MainAccountParams l_mainAccountParams = TestDBUtility.getMainAccountRow();
            l_mainAccountParams.setBranchCode("381");
            TestDBUtility.deleteAll(MainAccountRow.TYPE); 
            TestDBUtility.insertWithDel(l_mainAccountParams);
            
            log.debug("更新前Status =========" + l_hostEqtypeOrderClmdReceiptParams.getStatus());

            WEB3EquityChangeCancelNotifyMainRequest l_request = new WEB3EquityChangeCancelNotifyMainRequest();
            String[] l_strorderRequestNumberPrefixGroups = {"11"};
            l_request.orderRequestNumberPrefixGroup = l_strorderRequestNumberPrefixGroups;
            WEB3AsynEquityChangeCancelNotifyMainServiceImpl l_impl =
                new WEB3AsynEquityChangeCancelNotifyMainServiceImpl(l_request);
            
            WEB3EquityChangeCancelNotifyMainTransactionCallback l_transactionCallBack =
                l_impl.new WEB3EquityChangeCancelNotifyMainTransactionCallback();

            l_transactionCallBack.setOrderRequestNumberPrefixGroup(l_strorderRequestNumberPrefixGroups);
            
            l_transactionCallBack.process();
            
            HostEqtypeOrderClmdReceiptRow l_row = null;
            QueryProcessor l_processor = Processors.getDefaultProcessor();
            String l_strWhere = 
                "order_request_number = ?";
            Object[] l_objWhere = {"11"};
            List l_lstRecords = l_processor.doFindAllQuery(
                    HostEqtypeOrderClmdReceiptRow.TYPE,
                    l_strWhere,
                    l_objWhere);
            
            l_row = (HostEqtypeOrderClmdReceiptRow)l_lstRecords.get(0);
            
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

    /**
     * 株式注文訂正取消通知キューテーブル(host_eqtype_order_clmd_receipt)
     */
    public static HostEqtypeOrderClmdReceiptParams getHostEqtypeOrderClmdReceiptRow()
    {
        HostEqtypeOrderClmdReceiptParams l_hostEqtypeOrderClmdReceiptParams = new HostEqtypeOrderClmdReceiptParams();
        l_hostEqtypeOrderClmdReceiptParams.setRequestCode("AI817");
        l_hostEqtypeOrderClmdReceiptParams.setInstitutionCode("0D");
        l_hostEqtypeOrderClmdReceiptParams.setBranchCode("381");
        l_hostEqtypeOrderClmdReceiptParams.setAccountCode("2512246");
        l_hostEqtypeOrderClmdReceiptParams.setTraderCode("1234");
        l_hostEqtypeOrderClmdReceiptParams.setOrderRequestNumber("0");
        l_hostEqtypeOrderClmdReceiptParams.setModifiedQuantity(1000);
        l_hostEqtypeOrderClmdReceiptParams.setModifiedLimitPrice(100);
        l_hostEqtypeOrderClmdReceiptParams.setModifiedPriceConditionType("A");
        l_hostEqtypeOrderClmdReceiptParams.setModifiedResult("02");
        l_hostEqtypeOrderClmdReceiptParams.setCanmodReceiptType("1");
        l_hostEqtypeOrderClmdReceiptParams.setModifiedExecutionType("1");
        l_hostEqtypeOrderClmdReceiptParams.setStatus("0");
        l_hostEqtypeOrderClmdReceiptParams.setCreatedTimestamp(Calendar.getInstance().getTime());
        l_hostEqtypeOrderClmdReceiptParams.setLastUpdatedTimestamp(Calendar.getInstance().getTime());
        l_hostEqtypeOrderClmdReceiptParams.setModifiedOrderRev("00");
        l_hostEqtypeOrderClmdReceiptParams.setVirtualServerNumberMarket("1");
        l_hostEqtypeOrderClmdReceiptParams.setNoticeType("02");
        l_hostEqtypeOrderClmdReceiptParams.setNoticeNumber(100);
        l_hostEqtypeOrderClmdReceiptParams.setModSubmitOrderRouteDiv("0");
        
        return l_hostEqtypeOrderClmdReceiptParams;
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
