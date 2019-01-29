head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.04.07.01.12.08;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	5444d9d078c63f4;
filename	WEB3MstkSellServiceInterceptorTest.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
 Copyright        : (株)大和総研 証券ソリューションシステム第二部
 File Name        : WEB3MstkSellServiceInterceptorTest.java
 Author Name      : Daiwa Institute of Research
 Revesion History : 2008/07/04 楊夫志 (中訊) 新規作成
*/
package webbroker3.equity;

import com.fitechlabs.xtrade.plugin.tc.gentrade.data.BranchParams;
import com.fitechlabs.xtrade.plugin.tc.gentrade.data.BranchRow;
import com.fitechlabs.xtrade.plugin.tc.gentrade.data.InstitutionParams;
import com.fitechlabs.xtrade.plugin.tc.gentrade.data.InstitutionRow;
import com.fitechlabs.xtrade.plugin.tc.gentrade.data.MainAccountParams;
import com.fitechlabs.xtrade.plugin.tc.gentrade.data.MainAccountRow;

import test.util.TestDBUtility;

import webbroker3.common.WEB3BaseException;
import webbroker3.common.WEB3BaseRuntimeException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.common.WEB3SystemLayerException;
import webbroker3.equity.message.WEB3MstkSellCompleteRequest;
import webbroker3.equity.message.WEB3MstkSellConfirmRequest;
import webbroker3.gentrade.WEB3GentradeTradingClendarContext;
import webbroker3.gentrade.data.TradingTimeParams;
import webbroker3.gentrade.data.TradingTimeRow;
import webbroker3.mock.TestBaseForMock;
import webbroker3.util.WEB3LogUtility;

public class WEB3MstkSellServiceInterceptorTest extends TestBaseForMock {

    public WEB3LogUtility log = WEB3LogUtility.getInstance(WEB3MstkSellServiceInterceptorTest.class);
    
    WEB3MstkSellServiceInterceptor l_interceptor = null;
    
	public WEB3MstkSellServiceInterceptorTest(String arg0) {
		super(arg0);
	}

	protected void setUp() throws Exception {
		super.setUp();
        MOCK_MANAGER.setIsMockUsed(true);
        l_interceptor = new WEB3MstkSellServiceInterceptor();
	}

	protected void tearDown() throws Exception {
		super.tearDown();
	}

	//リクエストデータの型が「株式ミニ投資売付注文完了リクエスト」の場合のみ、口座をロックする
	public void testOnCall_C0001()
    {
        final String STR_METHOD_NAME = "testOnCall_C0001()";
        log.entering(TEST_START + STR_METHOD_NAME);

        try
        {
            TestDBUtility.deleteAll(MainAccountRow.TYPE);
            MainAccountParams mainAccountParams = TestDBUtility.getMainAccountRow();
            TestDBUtility.insertWithDel(mainAccountParams);
            
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
            tradingTimeParams.setTradingTimeType("05");
            tradingTimeParams.setBizDateType("1");
            tradingTimeParams.setProductCode("0");
            tradingTimeParams.setMarketCode("0");
            TestDBUtility.insertWithDel(tradingTimeParams);

            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                    "com.fitechlabs.xtrade.plugin.security.oplogin.impl.OpLoginSecurityServiceImpl",
                    "getAccountId",
                    new Class[] {},
                    new Long(333812512246L));

            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                    "webbroker3.gentrade.WEB3GentradeAccountManager",
                    "lockAccount",
                    new Class[] {String.class,String.class,String.class},
                    null);

            Object[] l_serviceParams = new Object[]{new WEB3MstkSellCompleteRequest()};
            WEB3GentradeTradingClendarContext l_context =
                (WEB3GentradeTradingClendarContext)l_interceptor.onCall(null, l_serviceParams);
            assertEquals(l_context.getBranchCode(), "381");
        }
        catch (Exception l_ex)
        {
            log.error(STR_METHOD_NAME, l_ex);
            fail();
        }
        log.info(STR_METHOD_NAME + ">>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>test pass !!");
        log.exiting(TEST_END + STR_METHOD_NAME);
	}

    //リクエストデータの型不是「株式ミニ投資売付注文完了リクエスト」の場合のみ、口座をロックする
    public void testOnCall_C0002()
    {
        final String STR_METHOD_NAME = "testOnCall_C0002()";
        log.entering(TEST_START + STR_METHOD_NAME);

        try
        {
            TestDBUtility.deleteAll(MainAccountRow.TYPE);
            MainAccountParams mainAccountParams = TestDBUtility.getMainAccountRow();
            TestDBUtility.insertWithDel(mainAccountParams);
            
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
            tradingTimeParams.setTradingTimeType("05");
            tradingTimeParams.setBizDateType("1");
            tradingTimeParams.setMarketCode("0");
            tradingTimeParams.setProductCode("0");
            TestDBUtility.insertWithDel(tradingTimeParams);

            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                    "com.fitechlabs.xtrade.plugin.security.oplogin.impl.OpLoginSecurityServiceImpl",
                    "getAccountId",
                    new Class[] {},
                    new Long(333812512246L));

            Object[] l_serviceParams = new Object[]{new WEB3MstkSellConfirmRequest()};
            WEB3GentradeTradingClendarContext l_context =
                (WEB3GentradeTradingClendarContext)l_interceptor.onCall(null, l_serviceParams);
            assertEquals(l_context.getBranchCode(), "381");
        }
        catch (Exception l_ex)
        {
            log.error(STR_METHOD_NAME, l_ex);
            fail();
        }
        log.info(STR_METHOD_NAME + ">>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>test pass !!");
        log.exiting(TEST_END + STR_METHOD_NAME);
    }

    //異常NotFoundException
    public void testOnCall_C0003()
    {
        final String STR_METHOD_NAME = "testOnCall_C0003()";
        log.entering(TEST_START + STR_METHOD_NAME);

        try
        {
            TestDBUtility.deleteAll(MainAccountRow.TYPE);
            MainAccountParams mainAccountParams = TestDBUtility.getMainAccountRow();
            mainAccountParams.setAccountId(111);
            TestDBUtility.insertWithDel(mainAccountParams);

            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                    "com.fitechlabs.xtrade.plugin.security.oplogin.impl.OpLoginSecurityServiceImpl",
                    "getAccountId",
                    new Class[] {},
                    new Long(123));

            Object[] l_serviceParams = new Object[]{new WEB3MstkSellCompleteRequest()};
            l_interceptor.onCall(null, l_serviceParams);
        }
        catch(WEB3SystemLayerException l_ex)
        {
            assertEquals(WEB3ErrorCatalog.SYSTEM_ERROR_80005, l_ex.getErrorInfo());
        }
        catch (Exception l_ex)
        {
            log.error(STR_METHOD_NAME, l_ex);
            fail();
        }
        log.info(STR_METHOD_NAME + ">>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>test pass !!");
        log.exiting(TEST_END + STR_METHOD_NAME);
    }

    //異常WEB3BaseException
    public void testOnCall_C0004()
    {
        final String STR_METHOD_NAME = "testOnCall_C0004()";
        log.entering(TEST_START + STR_METHOD_NAME);

        try
        {
            TestDBUtility.deleteAll(MainAccountRow.TYPE);
            MainAccountParams mainAccountParams = TestDBUtility.getMainAccountRow();
            TestDBUtility.insertWithDel(mainAccountParams);
            
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
            tradingTimeParams.setTradingTimeType("05");
            tradingTimeParams.setBizDateType("1");
            tradingTimeParams.setProductCode("0");
            tradingTimeParams.setMarketCode("0");
            TestDBUtility.insertWithDel(tradingTimeParams);

            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                    "com.fitechlabs.xtrade.plugin.security.oplogin.impl.OpLoginSecurityServiceImpl",
                    "getAccountId",
                    new Class[] {},
                    new Long(333812512246L));

            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                    "webbroker3.gentrade.WEB3GentradeAccountManager",
                    "lockAccount",
                    new Class[] {String.class,String.class,String.class},
                    new WEB3BaseException(WEB3ErrorCatalog.BUSINESS_ERROR_00003, ""));

            Object[] l_serviceParams = new Object[]{new WEB3MstkSellCompleteRequest()};
            l_interceptor.onCall(null, l_serviceParams);
        }
        catch(WEB3BaseRuntimeException l_ex)
        {
            assertEquals(WEB3ErrorCatalog.BUSINESS_ERROR_00003, l_ex.getErrorInfo());
        }
        catch (Exception l_ex)
        {
            log.error(STR_METHOD_NAME, l_ex);
            fail();
        }
        log.info(STR_METHOD_NAME + ">>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>test pass !!");
        log.exiting(TEST_END + STR_METHOD_NAME);
    }

}
@
