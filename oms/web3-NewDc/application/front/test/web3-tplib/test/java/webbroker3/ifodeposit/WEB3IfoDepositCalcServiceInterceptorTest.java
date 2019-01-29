head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.04.07.01.29.07;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	5444d9d078c63f4;
filename	WEB3IfoDepositCalcServiceInterceptorTest.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
 Copyright        : (株)大和総研 証券ソリューションシステム第二部
 File Name        : WEB3IfoDepositCalcServiceInterceptorTest.java
 Author Name      : Daiwa Institute of Research
 Revision History : 2008/08/26 楊夫志(中訊) 新規作成 モデル 132
*/
package webbroker3.ifodeposit;

import com.fitechlabs.xtrade.kernel.boot.Services;
import com.fitechlabs.xtrade.kernel.util.ThreadLocalSystemAttributesRegistry;
import com.fitechlabs.xtrade.plugin.tc.gentrade.AccountManager;
import com.fitechlabs.xtrade.plugin.tc.gentrade.FinApp;
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
import webbroker3.common.define.WEB3MarketCodeDef;
import webbroker3.common.define.WEB3OrderAccProductDef;
import webbroker3.common.define.WEB3OrderAccTransactionDef;
import webbroker3.common.define.WEB3ProductCodeDef;
import webbroker3.common.define.WEB3TradingTimeTypeDef;
import webbroker3.gentrade.WEB3GentradeSubAccount;
import webbroker3.gentrade.WEB3GentradeTradingClendarContext;
import webbroker3.gentrade.WEB3GentradeTradingTimeManagement;
import webbroker3.gentrade.data.TradingTimeParams;
import webbroker3.gentrade.data.TradingTimeRow;
import webbroker3.mock.TestBaseForMock;
import webbroker3.util.WEB3LogUtility;

public class WEB3IfoDepositCalcServiceInterceptorTest extends TestBaseForMock
{

    WEB3LogUtility log = WEB3LogUtility.getInstance(WEB3IfoDepositCalcServiceInterceptorTest.class);
    WEB3IfoDepositCalcServiceInterceptor l_interceptor = null;
    public WEB3IfoDepositCalcServiceInterceptorTest(String arg0)
    {
        super(arg0);
    }

    protected void setUp() throws Exception
    {
        super.setUp();
        l_interceptor = new WEB3IfoDepositCalcServiceInterceptor();
    }

    protected void tearDown() throws Exception
    {
        super.tearDown();
    }

    //取得した取引カレンダコンテキスト.商品コード == nullの場合のみ”0:DEFAULT”をセット
    //取得した取引カレンダコンテキスト.注文受付商品 == nullの場合のみ、”05：先物”をセット
    //取得した取引カレンダコンテキスト.注文受付トランザクション == nullの場合のみ、”07：照会”をセット
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
            tradingTimeParams.setInstitutionCode("0D");
            tradingTimeParams.setBranchCode("381");
            tradingTimeParams.setMarketCode("0");
            tradingTimeParams.setTradingTimeType("11");
            tradingTimeParams.setBizDateType("1");
            tradingTimeParams.setProductCode("0");
            TestDBUtility.insertWithDel(tradingTimeParams);

            WEB3GentradeTradingClendarContext l_context = null;
            ThreadLocalSystemAttributesRegistry.setAttribute(
                    WEB3GentradeTradingTimeManagement.TRADING_CAL_CONTEXT_PATH,
                    l_context);
            
            FinApp l_finApp = (FinApp)Services.getService(FinApp.class);
            AccountManager l_accountMgr = l_finApp.getAccountManager();
            WEB3GentradeSubAccount l_subAccount =
                (WEB3GentradeSubAccount) l_accountMgr.getSubAccount(333812512246L, 33381251220301L);

            Object[] objects = new Object[]{l_subAccount};
            l_interceptor.onCall(null, objects);

            WEB3GentradeTradingClendarContext l_getContext =
                (WEB3GentradeTradingClendarContext)ThreadLocalSystemAttributesRegistry.getAttribute(
                    WEB3GentradeTradingTimeManagement.TRADING_CAL_CONTEXT_PATH);
            assertEquals(l_getContext.getInstitutionCode(),"0D");
            assertEquals(l_getContext.getBranchCode(),"381");
            assertEquals(l_getContext.getMarketCode(),WEB3MarketCodeDef.DEFAULT);
            assertEquals(l_getContext.getTradingTimeType(), WEB3TradingTimeTypeDef.INDEX_FUTURE_OP);
            assertEquals(l_getContext.getProductCode(), WEB3ProductCodeDef.DEFAULT);
            assertEquals(l_getContext.getOrderAcceptProduct(), WEB3OrderAccProductDef.FUTURE);
            assertEquals(l_getContext.getOrderAcceptTransaction(), WEB3OrderAccTransactionDef.REFERENCE);
        }
        catch (Exception l_ex)
        {
            log.error(l_ex.getMessage(), l_ex);
            fail();
        }

        log.info(STR_METHOD_NAME + ">>>>>>>>>>>>>>>>>>>>>>>>>>>>>test past");
        log.exiting(STR_METHOD_NAME);
    }

    //取得した取引時間コンテキスト == nullの場合、取引時間管理.setTimestamp()をコールする。
    //WEB3SystemLayerException
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
            TradingTimeParams tradingTimeParams = TestDBUtility.getTradingTimeRow();
            tradingTimeParams.setInstitutionCode("00");
            tradingTimeParams.setBranchCode("381");
            tradingTimeParams.setMarketCode("0");
            tradingTimeParams.setTradingTimeType("11");
            tradingTimeParams.setBizDateType("1");
            tradingTimeParams.setProductCode("0");
            TestDBUtility.insertWithDel(tradingTimeParams);

            WEB3GentradeTradingClendarContext l_context = null;
            ThreadLocalSystemAttributesRegistry.setAttribute(
                    WEB3GentradeTradingTimeManagement.TRADING_CAL_CONTEXT_PATH,
                    l_context);
            
            FinApp l_finApp = (FinApp)Services.getService(FinApp.class);
            AccountManager l_accountMgr = l_finApp.getAccountManager();
            WEB3GentradeSubAccount l_subAccount =
                (WEB3GentradeSubAccount) l_accountMgr.getSubAccount(333812512246L, 33381251220301L);

            Object[] objects = new Object[]{l_subAccount};
            l_interceptor.onCall(null, objects);
        }
        catch(WEB3BaseRuntimeException l_ex)
        {
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

    //　@１）で取得した取引カレンダコンテキスト.商品コード == nullの場合のみ”0:DEFAULT”をセット
    //以外は１）の取引カレンダコンテキスト.商品コードをセット
    //１）で取得した取引カレンダコンテキスト.注文受付商品 == nullの場合のみ、”05：先物”をセット
    //以外は１）の取引カレンダコンテキスト.注文受付商品をセット
    //１）で取得した取引カレンダコンテキスト.注文受付トランザクション == nullの場合のみ、”07：照会”をセット
    //以外は１）の取引カレンダコンテキスト.注文受付トランザクションをセット
    public void testOnCall_C0003()
    {
        final String STR_METHOD_NAME = "testOnCall_C0003()";
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

            WEB3GentradeTradingClendarContext l_context = new WEB3GentradeTradingClendarContext();
            l_context.setProductCode("01");
            l_context.setOrderAcceptProduct("02");
            l_context.setOrderAcceptTransaction("03");
            ThreadLocalSystemAttributesRegistry.setAttribute(
                    WEB3GentradeTradingTimeManagement.TRADING_CAL_CONTEXT_PATH,
                    l_context);
            
            FinApp l_finApp = (FinApp)Services.getService(FinApp.class);
            AccountManager l_accountMgr = l_finApp.getAccountManager();
            WEB3GentradeSubAccount l_subAccount =
                (WEB3GentradeSubAccount) l_accountMgr.getSubAccount(333812512246L, 33381251220301L);

            Object[] objects = new Object[]{l_subAccount};
            l_interceptor.onCall(null, objects);

            WEB3GentradeTradingClendarContext l_getContext =
                (WEB3GentradeTradingClendarContext)ThreadLocalSystemAttributesRegistry.getAttribute(
                    WEB3GentradeTradingTimeManagement.TRADING_CAL_CONTEXT_PATH);
            assertEquals(l_getContext.getInstitutionCode(),"0D");
            assertEquals(l_getContext.getBranchCode(),"381");
            assertEquals(l_getContext.getMarketCode(),WEB3MarketCodeDef.DEFAULT);
            assertEquals(l_getContext.getTradingTimeType(), WEB3TradingTimeTypeDef.INDEX_FUTURE_OP);
            assertEquals(l_getContext.getProductCode(), "01");
            assertEquals(l_getContext.getOrderAcceptProduct(), "02");
            assertEquals(l_getContext.getOrderAcceptTransaction(), "03");
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
