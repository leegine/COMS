head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.04.07.01.10.36;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	5444d9d078c63f4;
filename	WEB3MarginCloseMarginServiceInterceptorTest.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : （WEB3MarginCloseMarginServiceInterceptorTest.java）
Author Name      : Daiwa Institute of Research
Revision History : 2008/02/14 トウ鋒鋼 (中訊) 新規作成
*/
package webbroker3.equity;

import java.sql.Timestamp;

import com.fitechlabs.xtrade.kernel.util.ThreadLocalSystemAttributesRegistry;
import com.fitechlabs.xtrade.plugin.tc.gentrade.BooleanEnum;
import com.fitechlabs.xtrade.plugin.tc.gentrade.data.BranchParams;
import com.fitechlabs.xtrade.plugin.tc.gentrade.data.BranchRow;
import com.fitechlabs.xtrade.plugin.tc.gentrade.data.InstitutionParams;
import com.fitechlabs.xtrade.plugin.tc.gentrade.data.InstitutionRow;
import com.fitechlabs.xtrade.plugin.tc.gentrade.data.MainAccountParams;
import com.fitechlabs.xtrade.plugin.tc.gentrade.data.MainAccountRow;

import test.util.TestDBUtility;
import test.util.TestSpecialClassUtility;

import webbroker3.equity.message.WEB3MarginCloseMarginCompleteRequest;
import webbroker3.equity.message.WEB3MarginCloseMarginConfirmRequest;
import webbroker3.gentrade.data.TradingTimeParams;
import webbroker3.gentrade.data.TradingTimeRow;
import webbroker3.mock.TestBaseForMock;
import webbroker3.util.WEB3DateUtility;
import webbroker3.util.WEB3LogUtility;

public class WEB3MarginCloseMarginServiceInterceptorTest extends TestBaseForMock
{
    private static WEB3LogUtility log =
        WEB3LogUtility.getInstance(WEB3MarginCloseMarginServiceInterceptorTest.class);
    
    WEB3MarginCloseMarginServiceInterceptor l_interceptor = new WEB3MarginCloseMarginServiceInterceptor();

    public WEB3MarginCloseMarginServiceInterceptorTest(String arg0)
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

    /**
     * リクエストデータの型が「信用取引返済注文確認リクエスト」の場合
     * 且つリクエストデータ.手動強制決済フラグ＝true
     *
     */
    public void testOnCall0001()
    {
        final String STR_METHOD_NAME = "testOnCall0001()";
        log.exiting(TEST_START + STR_METHOD_NAME);
        
        MOCK_MANAGER.setIsMockUsed(true);
        
        try
        {
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                    "com.fitechlabs.xtrade.plugin.security.oplogin.impl.OpLoginSecurityServiceImpl",
                    "getAccountId",
                    new Class[] {},
                    new Long(333812512246L));
            
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                    "com.fitechlabs.xtrade.plugin.tc.gentrade.stdimpls.TradingSystemImpl",
                    "getSystemTimestamp",
                    new Class[] {},
                    new Timestamp(WEB3DateUtility.getDate("20080214", "yyyyMMdd").getTime()));
            
            TestDBUtility.deleteAll(MainAccountRow.TYPE);
            MainAccountParams l_mainAccountParams = TestDBUtility.getMainAccountRow();
            TestDBUtility.insertWithDel(l_mainAccountParams);
            
            TestDBUtility.deleteAll(InstitutionRow.TYPE);
            InstitutionParams l_institutionParams = TestDBUtility.getInstitutionRow();
            TestDBUtility.insertWithDel(l_institutionParams);
            
            TestDBUtility.deleteAll(BranchRow.TYPE);
            BranchParams l_branchParams = TestDBUtility.getBranchRow();
            TestDBUtility.insertWithDel(l_branchParams);
            
            TestDBUtility.deleteAll(TradingTimeRow.TYPE);
            TradingTimeParams l_tradingTimeParams = TestDBUtility.getTradingTimeRow();
            l_tradingTimeParams.setInstitutionCode("0D");
            l_tradingTimeParams.setBranchCode("381");
            l_tradingTimeParams.setTradingTimeType("01");
            l_tradingTimeParams.setBizDateType("1");
            l_tradingTimeParams.setProductCode("0");
            TestDBUtility.insertWithDel(l_tradingTimeParams);
            
            WEB3MarginCloseMarginConfirmRequest l_request = new WEB3MarginCloseMarginConfirmRequest();
            l_request.manualForcedSettleFlag = true;
            
            Object[] l_obj = {l_request};

            l_interceptor.onCall(null, l_obj);
     //??更点已?除 
            
//            BooleanEnum l_blnEnum = (BooleanEnum)ThreadLocalSystemAttributesRegistry.getAttribute(
//                "web3.forcedsettleordervalidationskip");
//            
//            assertEquals(BooleanEnum.TRUE, l_blnEnum);
        }
        catch (Exception l_ex)
        {
            log.error("", l_ex);
            log.exiting(TEST_END + STR_METHOD_NAME);
            fail();
        }
        
        log.exiting(TEST_END + STR_METHOD_NAME);
    }
//    
//    /**
//     * リクエストデータの型が「信用取引返済注文完了リクエスト」の場合
//     * 且つリクエストデータ.手動強制決済フラグ＝true
//     * 
//     */
//    public void testOnCall0002()
//    {
//        final String STR_METHOD_NAME = "testOnCall0002()";
//        log.exiting(TEST_START + STR_METHOD_NAME);
//        
//        MOCK_MANAGER.setIsMockUsed(true);
//        
//        try
//        {
//            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
//                    "com.fitechlabs.xtrade.plugin.security.oplogin.impl.OpLoginSecurityServiceImpl",
//                    "getAccountId",
//                    new Class[] {},
//                    new Long(333812512246L));
//            
//            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
//                    "com.fitechlabs.xtrade.plugin.tc.gentrade.stdimpls.TradingSystemImpl",
//                    "getSystemTimestamp",
//                    new Class[] {},
//                    new Timestamp(WEB3DateUtility.getDate("20080214", "yyyyMMdd").getTime()));
//            
//            TestDBUtility.deleteAll(MainAccountRow.TYPE);
//            MainAccountParams l_mainAccountParams = TestDBUtility.getMainAccountRow();
//            TestDBUtility.insertWithDel(l_mainAccountParams);
//            
//            TestDBUtility.deleteAll(InstitutionRow.TYPE);
//            InstitutionParams l_institutionParams = TestDBUtility.getInstitutionRow();
//            TestDBUtility.insertWithDel(l_institutionParams);
//            
//            TestDBUtility.deleteAll(BranchRow.TYPE);
//            BranchParams l_branchParams = TestDBUtility.getBranchRow();
//            TestDBUtility.insertWithDel(l_branchParams);
//            
//            TestDBUtility.deleteAll(TradingTimeRow.TYPE);
//            TradingTimeParams l_tradingTimeParams = TestDBUtility.getTradingTimeRow();
//            l_tradingTimeParams.setInstitutionCode("0D");
//            l_tradingTimeParams.setBranchCode("381");
//            l_tradingTimeParams.setTradingTimeType("01");
//            l_tradingTimeParams.setBizDateType("1");
//            l_tradingTimeParams.setProductCode("0");
//            TestDBUtility.insertWithDel(l_tradingTimeParams);
//            
//            WEB3MarginCloseMarginCompleteRequest l_request = new WEB3MarginCloseMarginCompleteRequest();
//            l_request.manualForcedSettleFlag = true;
//            
//            Object[] l_obj = {l_request};
//
//            l_interceptor.onCall(null, l_obj);
//            
//            BooleanEnum l_blnEnum = (BooleanEnum)ThreadLocalSystemAttributesRegistry.getAttribute(
//                "web3.forcedsettleordervalidationskip");
//            
//            assertEquals(BooleanEnum.TRUE, l_blnEnum);
//        }
//        catch (Exception l_ex)
//        {
//            log.error("", l_ex);
//            log.exiting(TEST_END + STR_METHOD_NAME);
//            fail();
//        }
//        
//        log.exiting(TEST_END + STR_METHOD_NAME);
//    }
//    
//    /**
//     * リクエストデータの型が「信用取引返済注文完了リクエスト」の場合
//     * 且つリクエストデータ.手動強制決済フラグ＝false
//     * 
//     */
//    public void testOnCall0003()
//    {
//        final String STR_METHOD_NAME = "testOnCall0003()";
//        log.exiting(TEST_START + STR_METHOD_NAME);
//        
//        MOCK_MANAGER.setIsMockUsed(true);
//        
//        try
//        {
//            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
//                    "com.fitechlabs.xtrade.plugin.security.oplogin.impl.OpLoginSecurityServiceImpl",
//                    "getAccountId",
//                    new Class[] {},
//                    new Long(333812512246L));
//            
//            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
//                    "com.fitechlabs.xtrade.plugin.tc.gentrade.stdimpls.TradingSystemImpl",
//                    "getSystemTimestamp",
//                    new Class[] {},
//                    new Timestamp(WEB3DateUtility.getDate("20080214", "yyyyMMdd").getTime()));
//            
//            TestDBUtility.deleteAll(MainAccountRow.TYPE);
//            MainAccountParams l_mainAccountParams = TestDBUtility.getMainAccountRow();
//            TestDBUtility.insertWithDel(l_mainAccountParams);
//            
//            TestDBUtility.deleteAll(InstitutionRow.TYPE);
//            InstitutionParams l_institutionParams = TestDBUtility.getInstitutionRow();
//            TestDBUtility.insertWithDel(l_institutionParams);
//            
//            TestDBUtility.deleteAll(BranchRow.TYPE);
//            BranchParams l_branchParams = TestDBUtility.getBranchRow();
//            TestDBUtility.insertWithDel(l_branchParams);
//            
//            TestDBUtility.deleteAll(TradingTimeRow.TYPE);
//            TradingTimeParams l_tradingTimeParams = TestDBUtility.getTradingTimeRow();
//            l_tradingTimeParams.setInstitutionCode("0D");
//            l_tradingTimeParams.setBranchCode("381");
//            l_tradingTimeParams.setTradingTimeType("01");
//            l_tradingTimeParams.setBizDateType("1");
//            l_tradingTimeParams.setProductCode("0");
//            TestDBUtility.insertWithDel(l_tradingTimeParams);
//            
//            WEB3MarginCloseMarginCompleteRequest l_request = new WEB3MarginCloseMarginCompleteRequest();
//            l_request.manualForcedSettleFlag = false;
//            
//            Object[] l_obj = {l_request};
//
//            l_interceptor.onCall(null, l_obj);
//            
//            BooleanEnum l_blnEnum = (BooleanEnum)ThreadLocalSystemAttributesRegistry.getAttribute(
//                "web3.forcedsettleordervalidationskip");
//            
//            assertNull(l_blnEnum);
//        }
//        catch (Exception l_ex)
//        {
//            log.error("", l_ex);
//            log.exiting(TEST_END + STR_METHOD_NAME);
//            fail();
//        }
//        
//        log.exiting(TEST_END + STR_METHOD_NAME);
//    }
//
    /*
     * Test method for 'webbroker3.equity.WEB3MarginCloseMarginServiceInterceptor.onReturn(Object, Object)'
     */
    public void testOnReturn()
    {
        TestSpecialClassUtility.testServiceInterceptor(l_interceptor);
    }

    /*
     * Test method for 'webbroker3.equity.WEB3MarginCloseMarginServiceInterceptor.onThrowable(Object, Throwable)'
     */
    public void testOnThrowable()
    {
        TestSpecialClassUtility.testServiceInterceptor(l_interceptor);
    }

}
@
