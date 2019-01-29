head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.04.07.01.28.24;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	5444d9d078c63f4;
filename	WEB3IfoDepositCalcResultCreatePerAccountServiceInterceptorTest.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
 Copyright        : (株)大和総研 証券ソリューションシステム第二部
 File Name        : WEB3IfoDepositCalcResultCreatePerAccountServiceInterceptorTest.java
 Author Name      : Daiwa Institute of Research
 Revision History : 2008/08/20 楊夫志(中訊) 新規作成 モデル 132
*/
package webbroker3.ifodeposit;

import com.fitechlabs.xtrade.kernel.boot.Services;
import com.fitechlabs.xtrade.kernel.util.ThreadLocalSystemAttributesRegistry;
import com.fitechlabs.xtrade.plugin.tc.gentrade.AccountManager;
import com.fitechlabs.xtrade.plugin.tc.gentrade.FinApp;
import com.fitechlabs.xtrade.plugin.tc.gentrade.MainAccount;
import com.fitechlabs.xtrade.plugin.tc.gentrade.data.BranchParams;
import com.fitechlabs.xtrade.plugin.tc.gentrade.data.BranchRow;
import com.fitechlabs.xtrade.plugin.tc.gentrade.data.InstitutionParams;
import com.fitechlabs.xtrade.plugin.tc.gentrade.data.InstitutionRow;
import com.fitechlabs.xtrade.plugin.tc.gentrade.data.MainAccountParams;
import com.fitechlabs.xtrade.plugin.tc.gentrade.data.MainAccountRow;
import com.fitechlabs.xtrade.plugin.tc.gentrade.data.SubAccountParams;
import com.fitechlabs.xtrade.plugin.tc.gentrade.data.SubAccountRow;

import test.util.TestDBUtility;
import test.util.TestSpecialClassUtility;

import webbroker3.common.WEB3BaseRuntimeException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.gentrade.WEB3GentradeSubAccount;
import webbroker3.gentrade.WEB3GentradeTradingClendarContext;
import webbroker3.gentrade.WEB3GentradeTradingTimeManagement;
import webbroker3.gentrade.data.TradingTimeParams;
import webbroker3.gentrade.data.TradingTimeRow;
import webbroker3.mock.TestBaseForMock;
import webbroker3.util.WEB3LogUtility;

public class WEB3IfoDepositCalcResultCreatePerAccountServiceInterceptorTest extends TestBaseForMock
{

    WEB3LogUtility log = WEB3LogUtility.getInstance(WEB3IfoDepositCalcResultCreatePerAccountServiceInterceptorTest.class);
    WEB3IfoDepositCalcResultCreatePerAccountServiceInterceptor l_interceptor = null;
    public WEB3IfoDepositCalcResultCreatePerAccountServiceInterceptorTest(String arg0)
    {
        super(arg0);
    }

    protected void setUp() throws Exception
    {
        super.setUp();
        MOCK_MANAGER.setIsMockUsed(true);
        l_interceptor = new WEB3IfoDepositCalcResultCreatePerAccountServiceInterceptor();
    }

    protected void tearDown() throws Exception
    {
        super.tearDown();
    }

    //取引カレンダコンテキストに内容をセットする
    public void testOnCall_C0001()
    {
        final String STR_METHOD_NAME = "testOnCall_C0001()";
        log.entering(STR_METHOD_NAME);
        
        try
        {
            TestDBUtility.deleteAll(MainAccountRow.TYPE);
            MainAccountParams mainAccountParams = TestDBUtility.getMainAccountRow();
            TestDBUtility.insertWithDel(mainAccountParams);

            TestDBUtility.deleteAll(SubAccountRow.TYPE);
            SubAccountParams subAccountParams = TestDBUtility.getSubAccountRow();
            subAccountParams.setAccountId(333812512246L);
            TestDBUtility.insertWithDel(subAccountParams);

            TestDBUtility.deleteAll(InstitutionRow.TYPE);
            InstitutionParams institutionParams = TestDBUtility.getInstitutionRow();
            TestDBUtility.insertWithDel(institutionParams);

            TestDBUtility.deleteAll(BranchRow.TYPE);
            BranchParams branchParams = TestDBUtility.getBranchRow();
            TestDBUtility.insertWithDel(branchParams);

            TestDBUtility.deleteAll(TradingTimeRow.TYPE);
            TradingTimeParams tradingTimeParams = TestDBUtility.getTradingTimeRow();
            tradingTimeParams.setTradingTimeType("11");
            tradingTimeParams.setBizDateType("1");
            tradingTimeParams.setMarketCode("0");
            TestDBUtility.insertWithDel(tradingTimeParams);

            FinApp l_finApp = (FinApp)Services.getService(FinApp.class);
            AccountManager l_accountMgr = l_finApp.getAccountManager();
            MainAccount l_mainAccount = l_accountMgr.getMainAccount(333812512246L);

            WEB3GentradeSubAccount subAccount = new WEB3GentradeSubAccount(l_mainAccount, subAccountParams);

            Object[] arguments = new Object[]{subAccount};
            l_interceptor.onCall(null, arguments);
            WEB3GentradeTradingClendarContext l_clendarContext =
                (WEB3GentradeTradingClendarContext)
                    ThreadLocalSystemAttributesRegistry.getAttribute(
                        WEB3GentradeTradingTimeManagement.TRADING_CAL_CONTEXT_PATH);
            assertEquals(l_clendarContext.getInstitutionCode(), "0D");
            assertEquals(l_clendarContext.getBranchCode(), "381");
            assertEquals(l_clendarContext.getMarketCode(), "0");
            assertEquals(l_clendarContext.getTradingTimeType(), "11");
            assertEquals(l_clendarContext.getProductCode(), "0");
            assertEquals(l_clendarContext.getOrderAcceptProduct(), "31");
            assertEquals(l_clendarContext.getOrderAcceptTransaction(), "07");
        }
        catch (Exception l_ex)
        {
            log.error(l_ex.getMessage(), l_ex);
            fail();
        }
        log.info(STR_METHOD_NAME + ">>>>>>>>>>>>>>>>>>>>>>>>>>>>>test past");
        log.exiting(STR_METHOD_NAME);
    }
    
    /*
     * 受付日時、日付ロールをセットする失敗。
     */
    public void testOnCall_C0002()
    {
        final String STR_METHOD_NAME = "testOnCall_C0002()";
        log.entering(STR_METHOD_NAME);
        
        try
        {
            TestDBUtility.deleteAll(MainAccountRow.TYPE);
            MainAccountParams mainAccountParams = TestDBUtility.getMainAccountRow();
            TestDBUtility.insertWithDel(mainAccountParams);

            TestDBUtility.deleteAll(SubAccountRow.TYPE);
            SubAccountParams subAccountParams = TestDBUtility.getSubAccountRow();
            subAccountParams.setAccountId(333812512246L);
            TestDBUtility.insertWithDel(subAccountParams);

            TestDBUtility.deleteAll(InstitutionRow.TYPE);
            InstitutionParams institutionParams = TestDBUtility.getInstitutionRow();
            TestDBUtility.insertWithDel(institutionParams);

            TestDBUtility.deleteAll(BranchRow.TYPE);
            BranchParams branchParams = TestDBUtility.getBranchRow();
            TestDBUtility.insertWithDel(branchParams);

            TestDBUtility.deleteAll(TradingTimeRow.TYPE);

            FinApp l_finApp = (FinApp)Services.getService(FinApp.class);
            AccountManager l_accountMgr = l_finApp.getAccountManager();
            MainAccount l_mainAccount = l_accountMgr.getMainAccount(333812512246L);

            WEB3GentradeSubAccount subAccount = new WEB3GentradeSubAccount(l_mainAccount, subAccountParams);

            Object[] arguments = new Object[]{subAccount};
            l_interceptor.onCall(null, arguments);
            WEB3GentradeTradingClendarContext l_clendarContext =
                (WEB3GentradeTradingClendarContext)
                    ThreadLocalSystemAttributesRegistry.getAttribute(
                        WEB3GentradeTradingTimeManagement.TRADING_CAL_CONTEXT_PATH);
            assertEquals(l_clendarContext.getInstitutionCode(), "0D");
            assertEquals(l_clendarContext.getBranchCode(), "381");
            assertEquals(l_clendarContext.getMarketCode(), "0");
            assertEquals(l_clendarContext.getTradingTimeType(), "11");
            assertEquals(l_clendarContext.getProductCode(), "0");
            assertEquals(l_clendarContext.getOrderAcceptProduct(), "31");
            assertEquals(l_clendarContext.getOrderAcceptTransaction(), "07");
        }
        catch (WEB3BaseRuntimeException l_ex)
        {
            log.debug(STR_METHOD_NAME);
            assertEquals(WEB3ErrorCatalog.SYSTEM_ERROR_80005, l_ex.getErrorInfo());
        }
        catch (Exception l_ex)
        {
            log.error(l_ex.getMessage(), l_ex);
            fail();
        }
        log.info(STR_METHOD_NAME + ">>>>>>>>>>>>>>>>>>>>>>>>>>>>>test past");
        log.exiting(STR_METHOD_NAME);
    }
    /*
     * normal case
     */
    public void testOnReturn()
    {
        final String STR_METHOD_NAME = "testOnReturn()";
        log.entering(TEST_START + STR_METHOD_NAME);

        try
        {      
            TestSpecialClassUtility.testServiceInterceptor(l_interceptor);
            assertEquals(ThreadLocalSystemAttributesRegistry.getAttribute(WEB3GentradeTradingTimeManagement.TIMESTAMP_TAG), null);
            assertEquals(ThreadLocalSystemAttributesRegistry.getAttribute(WEB3GentradeTradingTimeManagement.OFFSET_TAG), null);
            assertEquals(ThreadLocalSystemAttributesRegistry.getAttribute(WEB3GentradeTradingTimeManagement.TRADING_CAL_CONTEXT_PATH), null);
        }
        catch (Exception l_ex)
        {
            log.error(l_ex.getMessage(), l_ex);
            fail();
        }
        log.exiting(TEST_END + STR_METHOD_NAME);
    }

    /*
     * normal case
     */
    public void testOnThrowable()
    {
        final String STR_METHOD_NAME = "testOnThrowable()";
        log.entering(TEST_START + STR_METHOD_NAME);
        
        // 実際メソッドをコール
        TestSpecialClassUtility.testServiceInterceptor(l_interceptor);
        assertEquals(ThreadLocalSystemAttributesRegistry.
            getAttribute(WEB3GentradeTradingTimeManagement.TIMESTAMP_TAG), null);
        assertEquals(ThreadLocalSystemAttributesRegistry.
            getAttribute(WEB3GentradeTradingTimeManagement.OFFSET_TAG), null);
        assertEquals(ThreadLocalSystemAttributesRegistry.
            getAttribute(WEB3GentradeTradingTimeManagement.TRADING_CAL_CONTEXT_PATH), null);
        
        log.exiting(TEST_END + STR_METHOD_NAME);
    }
}
@
