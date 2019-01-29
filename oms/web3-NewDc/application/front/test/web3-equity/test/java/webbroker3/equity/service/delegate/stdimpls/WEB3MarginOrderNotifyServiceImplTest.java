head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.04.07.00.55.57;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	5444d9d078c63f4;
filename	WEB3MarginOrderNotifyServiceImplTest.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : 信用取引注文通知サービスImplテスト(WEB3MarginOrderNotifyServiceImplTest.java)
Author Name      : Daiwa Institute of Research
Revision History : 2007/04/19 趙林鵬 (中訊) 新規作成
*/

package webbroker3.equity.service.delegate.stdimpls;

import java.util.Calendar;
import java.util.List;

import com.fitechlabs.xtrade.kernel.data.Processors;
import com.fitechlabs.xtrade.kernel.data.QueryProcessor;
import com.fitechlabs.xtrade.plugin.tc.gentrade.data.MainAccountParams;
import com.fitechlabs.xtrade.plugin.tc.gentrade.data.MainAccountRow;

import test.util.TestDBUtility;

import webbroker3.common.define.WEB3CapitalGainTaxTypeDef;
import webbroker3.common.define.WEB3MarketCodeSONARDef;
import webbroker3.common.define.WEB3SwapTradeTypeDef;
import webbroker3.equity.data.HostEqtypeOrderReceiptParams;
import webbroker3.equity.data.HostEqtypeOrderReceiptRow;
import webbroker3.equity.service.delegate.stdimpls.WEB3MarginOrderNotifyServiceImpl.WEB3MarginOrderNotifyTransactionCallback;
import webbroker3.gentrade.data.RequestCodesForReadParams;
import webbroker3.gentrade.data.RequestCodesForReadRow;
import webbroker3.mock.TestBaseForMock;
import webbroker3.util.WEB3DateUtility;
import webbroker3.util.WEB3LogUtility;

/**
 * （信用取引注文通知サービスImplテスト）<BR>
 *
 * @@author 趙林鵬
 * @@version 1.0
 */

public class WEB3MarginOrderNotifyServiceImplTest extends TestBaseForMock
{
    /**
     * ログユーティリティ<BR>
     */
    private static WEB3LogUtility log =
         WEB3LogUtility.getInstance(WEB3MarginOrderNotifyServiceImplTest.class);

    public WEB3MarginOrderNotifyServiceImplTest(String arg0)
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
            HostEqtypeOrderReceiptParams l_hostEqtypeOrderReceiptParams =
                getHostEqtypeOrderReceiptRow();
            
            l_hostEqtypeOrderReceiptParams.setOrderRequestNumber("101");
            l_hostEqtypeOrderReceiptParams.setSonarTradedCode("51");
            TestDBUtility.deleteAll(HostEqtypeOrderReceiptRow.TYPE);        
            TestDBUtility.insertWithDel(l_hostEqtypeOrderReceiptParams);

            RequestCodesForReadParams l_requestCodesForReadParams =
                getRequestCodesForReadRow();
            l_requestCodesForReadParams.setRequestCode(l_hostEqtypeOrderReceiptParams.getRequestCode());
            l_requestCodesForReadParams.setPtype("margin_orderNotify");
            TestDBUtility.deleteAll(RequestCodesForReadRow.TYPE);        
            TestDBUtility.insertWithDel(l_requestCodesForReadParams);

            MainAccountParams l_mainAccountParams = TestDBUtility.getMainAccountRow();
            l_mainAccountParams.setBranchCode("382");
            TestDBUtility.deleteAll(MainAccountRow.TYPE); 
            TestDBUtility.insertWithDel(l_mainAccountParams);

            log.debug("更新前Status =========" + l_hostEqtypeOrderReceiptParams.getStatus());
            
            WEB3MarginOrderNotifyServiceImpl l_impl = new WEB3MarginOrderNotifyServiceImpl();
            
            WEB3MarginOrderNotifyTransactionCallback l_transactionCallBack =
                l_impl.new WEB3MarginOrderNotifyTransactionCallback();

            String[] l_strorderRequestNumberPrefixGroups = {"1"};
            l_transactionCallBack.setOrderRequestNumberPrefixGroup(l_strorderRequestNumberPrefixGroups);
            
            l_transactionCallBack.process();
            
            HostEqtypeOrderReceiptRow l_row = null;
            QueryProcessor l_processor = Processors.getDefaultProcessor();
            String l_strWhere = 
                "order_request_number = ?";
            Object[] l_objWhere = {"101"};
            List l_lstRecords = l_processor.doFindAllQuery(
                    HostEqtypeOrderReceiptRow.TYPE,
                    l_strWhere,
                    l_objWhere);
            
            l_row = (HostEqtypeOrderReceiptRow)l_lstRecords.get(0);
            
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
            HostEqtypeOrderReceiptParams l_hostEqtypeOrderReceiptParams =
                getHostEqtypeOrderReceiptRow();
            
            l_hostEqtypeOrderReceiptParams.setOrderRequestNumber("101");
            l_hostEqtypeOrderReceiptParams.setSonarTradedCode("51");
            TestDBUtility.deleteAll(HostEqtypeOrderReceiptRow.TYPE);        
            TestDBUtility.insertWithDel(l_hostEqtypeOrderReceiptParams);

            RequestCodesForReadParams l_requestCodesForReadParams =
                getRequestCodesForReadRow();
            l_requestCodesForReadParams.setRequestCode(l_hostEqtypeOrderReceiptParams.getRequestCode());
            l_requestCodesForReadParams.setPtype("margin_orderNotify");
            TestDBUtility.deleteAll(RequestCodesForReadRow.TYPE);        
            TestDBUtility.insertWithDel(l_requestCodesForReadParams);

            MainAccountParams l_mainAccountParams = TestDBUtility.getMainAccountRow();
            l_mainAccountParams.setBranchCode("381");
            TestDBUtility.deleteAll(MainAccountRow.TYPE); 
            TestDBUtility.insertWithDel(l_mainAccountParams);

            log.debug("更新前Status =========" + l_hostEqtypeOrderReceiptParams.getStatus());
            
            WEB3MarginOrderNotifyServiceImpl l_impl = new WEB3MarginOrderNotifyServiceImpl();
            
            WEB3MarginOrderNotifyTransactionCallback l_transactionCallBack =
                l_impl.new WEB3MarginOrderNotifyTransactionCallback();

            String[] l_strorderRequestNumberPrefixGroups = {"1"};
            l_transactionCallBack.setOrderRequestNumberPrefixGroup(l_strorderRequestNumberPrefixGroups);
            
            l_transactionCallBack.process();
            
            HostEqtypeOrderReceiptRow l_row = null;
            QueryProcessor l_processor = Processors.getDefaultProcessor();
            String l_strWhere = 
                "order_request_number = ?";
            Object[] l_objWhere = {"101"};
            List l_lstRecords = l_processor.doFindAllQuery(
                    HostEqtypeOrderReceiptRow.TYPE,
                    l_strWhere,
                    l_objWhere);
            
            l_row = (HostEqtypeOrderReceiptRow)l_lstRecords.get(0);
            
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
     * 株式注文入力通知キューテーブル (host_eqtype_order_receipt)
     */
    public static HostEqtypeOrderReceiptParams getHostEqtypeOrderReceiptRow()
    {
        HostEqtypeOrderReceiptParams l_hostEqtypeOrderReceiptParams = new HostEqtypeOrderReceiptParams();
        l_hostEqtypeOrderReceiptParams.setRequestCode("AI821");
        l_hostEqtypeOrderReceiptParams.setInstitutionCode("0D");
        l_hostEqtypeOrderReceiptParams.setBranchCode("381");
        l_hostEqtypeOrderReceiptParams.setAccountCode("2512246");
        l_hostEqtypeOrderReceiptParams.setTraderCode("1234");
        l_hostEqtypeOrderReceiptParams.setProductCode("0001000");
        l_hostEqtypeOrderReceiptParams.setOrderRequestNumber("1");
        l_hostEqtypeOrderReceiptParams.setTradeType(WEB3SwapTradeTypeDef.DELIVERY);
        l_hostEqtypeOrderReceiptParams.setSonarRepaymentType("1");
        l_hostEqtypeOrderReceiptParams.setSonarMarketCode(WEB3MarketCodeSONARDef.TOKYO);
        l_hostEqtypeOrderReceiptParams.setQuantity(100);
        l_hostEqtypeOrderReceiptParams.setCapitalGainTaxType(WEB3CapitalGainTaxTypeDef.NOTHING);
        l_hostEqtypeOrderReceiptParams.setBizDatetime(WEB3DateUtility.getDate("20070101","yyyyMMdd"));
        l_hostEqtypeOrderReceiptParams.setDeliveryDate("20070412");
        l_hostEqtypeOrderReceiptParams.setStatus("0");
        l_hostEqtypeOrderReceiptParams.setCreatedTimestamp(Calendar.getInstance().getTime());
        l_hostEqtypeOrderReceiptParams.setLastUpdatedTimestamp(Calendar.getInstance().getTime());
        return l_hostEqtypeOrderReceiptParams;
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
