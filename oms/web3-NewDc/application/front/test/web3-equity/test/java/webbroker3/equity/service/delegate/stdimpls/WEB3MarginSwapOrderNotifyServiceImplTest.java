head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.04.07.00.54.33;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	5444d9d078c63f4;
filename	WEB3MarginSwapOrderNotifyServiceImplTest.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : 信用取引現引現渡注文通知サービスImplテスト(WEB3MarginSwapOrderNotifyServiceImplTest.java)
Author Name      : Daiwa Institute of Research
Revision History : 2007/04/18 趙林鵬 (中訊) 新規作成
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
import webbroker3.common.define.WEB3TaxTypeDef;
import webbroker3.equity.data.HostEqtypeSwapReceiptParams;
import webbroker3.equity.data.HostEqtypeSwapReceiptRow;
import webbroker3.equity.service.delegate.stdimpls.WEB3MarginSwapOrderNotifyServiceImpl.WEB3MarginSwapOrderNotifyTransactionCallback;
import webbroker3.gentrade.data.RequestCodesForReadParams;
import webbroker3.gentrade.data.RequestCodesForReadRow;
import webbroker3.mock.TestBaseForMock;
import webbroker3.util.WEB3DateUtility;
import webbroker3.util.WEB3LogUtility;

/**
 * （信用取引現引現渡注文通知サービスImplテスト）<BR>
 *
 * @@author 趙林鵬
 * @@version 1.0
 */

public class WEB3MarginSwapOrderNotifyServiceImplTest extends TestBaseForMock
{
    /**
     * ログユーティリティ<BR>
     */
    private static WEB3LogUtility log =
         WEB3LogUtility.getInstance(WEB3MarginSwapOrderNotifyServiceImplTest.class);

    public WEB3MarginSwapOrderNotifyServiceImplTest(String arg0)
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
            HostEqtypeSwapReceiptParams l_hostEqtypeSwapReceiptParams =
                getHostEqtypeSwapReceiptRow();
            l_hostEqtypeSwapReceiptParams.setOrderRequestNumber("101");
            TestDBUtility.deleteAll(HostEqtypeSwapReceiptRow.TYPE);        
            TestDBUtility.insertWithDel(l_hostEqtypeSwapReceiptParams);
            
            RequestCodesForReadParams l_requestCodesForReadParams =
                getRequestCodesForReadRow();
            l_requestCodesForReadParams.setRequestCode(l_hostEqtypeSwapReceiptParams.getRequestCode());
            TestDBUtility.deleteAll(RequestCodesForReadRow.TYPE);        
            TestDBUtility.insertWithDel(l_requestCodesForReadParams);
            
            MainAccountParams l_mainAccountParams = TestDBUtility.getMainAccountRow();
            l_mainAccountParams.setBranchCode("382");
            TestDBUtility.deleteAll(MainAccountRow.TYPE); 
            TestDBUtility.insertWithDel(l_mainAccountParams);
            
            log.debug("更新前Status =========" + l_hostEqtypeSwapReceiptParams.getStatus());
            
            WEB3MarginSwapOrderNotifyServiceImpl l_impl = new WEB3MarginSwapOrderNotifyServiceImpl();
            
            WEB3MarginSwapOrderNotifyTransactionCallback l_transactionCallBack =
                l_impl.new WEB3MarginSwapOrderNotifyTransactionCallback();

            String[] l_strorderRequestNumberPrefixGroups = {"1"};
            l_transactionCallBack.setOrderRequestNumberPrefixGroup(l_strorderRequestNumberPrefixGroups);
            
            l_transactionCallBack.process();
            
            HostEqtypeSwapReceiptRow l_row = null;
            QueryProcessor l_processor = Processors.getDefaultProcessor();
            String l_strWhere = 
                "order_request_number = ?";
            Object[] l_objWhere = {"101"};
            List l_lstRecords = l_processor.doFindAllQuery(
                    HostEqtypeSwapReceiptRow.TYPE,
                    l_strWhere,
                    l_objWhere);
            
            l_row = (HostEqtypeSwapReceiptRow)l_lstRecords.get(0);
            
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
            HostEqtypeSwapReceiptParams l_hostEqtypeSwapReceiptParams =
                getHostEqtypeSwapReceiptRow();
            l_hostEqtypeSwapReceiptParams.setOrderRequestNumber("101");
            TestDBUtility.deleteAll(HostEqtypeSwapReceiptRow.TYPE);        
            TestDBUtility.insertWithDel(l_hostEqtypeSwapReceiptParams);
            
            RequestCodesForReadParams l_requestCodesForReadParams =
                getRequestCodesForReadRow();
            l_requestCodesForReadParams.setRequestCode(l_hostEqtypeSwapReceiptParams.getRequestCode());
            TestDBUtility.deleteAll(RequestCodesForReadRow.TYPE);        
            TestDBUtility.insertWithDel(l_requestCodesForReadParams);
            
            MainAccountParams l_mainAccountParams = TestDBUtility.getMainAccountRow();
            TestDBUtility.deleteAll(MainAccountRow.TYPE);        
            TestDBUtility.insertWithDel(l_mainAccountParams);
            
            log.debug("更新前Status =========" + l_hostEqtypeSwapReceiptParams.getStatus());
            
            WEB3MarginSwapOrderNotifyServiceImpl l_impl = new WEB3MarginSwapOrderNotifyServiceImpl();
            
            WEB3MarginSwapOrderNotifyTransactionCallback l_transactionCallBack =
                l_impl.new WEB3MarginSwapOrderNotifyTransactionCallback();

            String[] l_strorderRequestNumberPrefixGroups = {"1"};
            l_transactionCallBack.setOrderRequestNumberPrefixGroup(l_strorderRequestNumberPrefixGroups);
            
            l_transactionCallBack.process();
            
            HostEqtypeSwapReceiptRow l_row = null;
            QueryProcessor l_processor = Processors.getDefaultProcessor();
            String l_strWhere = 
                "order_request_number = ?";
            Object[] l_objWhere = {"101"};
            List l_lstRecords = l_processor.doFindAllQuery(
                    HostEqtypeSwapReceiptRow.TYPE,
                    l_strWhere,
                    l_objWhere);
            
            l_row = (HostEqtypeSwapReceiptRow)l_lstRecords.get(0);
            
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
     * 現引現渡入力通知キューテーブル (host_eqtype_swap_receipt)
     */
    public static HostEqtypeSwapReceiptParams getHostEqtypeSwapReceiptRow()
    {
        HostEqtypeSwapReceiptParams l_hostEqtypeSwapReceiptParams = new HostEqtypeSwapReceiptParams();
        l_hostEqtypeSwapReceiptParams.setRequestCode("AI826");
        l_hostEqtypeSwapReceiptParams.setInstitutionCode("0D");
        l_hostEqtypeSwapReceiptParams.setBranchCode("381");
        l_hostEqtypeSwapReceiptParams.setAccountCode("2512246");
        l_hostEqtypeSwapReceiptParams.setTraderCode("1234");
        l_hostEqtypeSwapReceiptParams.setProductCode("0001000");
        l_hostEqtypeSwapReceiptParams.setOrderRequestNumber("1");
        l_hostEqtypeSwapReceiptParams.setTradeType(WEB3SwapTradeTypeDef.DELIVERY);
        l_hostEqtypeSwapReceiptParams.setSonarRepaymentType("1");
        l_hostEqtypeSwapReceiptParams.setSonarMarketCode(WEB3MarketCodeSONARDef.TOKYO);
        l_hostEqtypeSwapReceiptParams.setQuantity(100);
        l_hostEqtypeSwapReceiptParams.setCapitalGainTaxType(WEB3CapitalGainTaxTypeDef.NOTHING);
        l_hostEqtypeSwapReceiptParams.setSwapTaxType(WEB3TaxTypeDef.NORMAL);
        l_hostEqtypeSwapReceiptParams.setTaxType(WEB3TaxTypeDef.NORMAL);
        l_hostEqtypeSwapReceiptParams.setBizDatetime(WEB3DateUtility.getDate("20070101","yyyyMMdd"));
        l_hostEqtypeSwapReceiptParams.setDeliveryDate("20070412");
        l_hostEqtypeSwapReceiptParams.setCancelDiv("0");
        l_hostEqtypeSwapReceiptParams.setStatus("0");
        l_hostEqtypeSwapReceiptParams.setCreatedTimestamp(Calendar.getInstance().getTime());
        l_hostEqtypeSwapReceiptParams.setLastUpdatedTimestamp(Calendar.getInstance().getTime());
        return l_hostEqtypeSwapReceiptParams;
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
