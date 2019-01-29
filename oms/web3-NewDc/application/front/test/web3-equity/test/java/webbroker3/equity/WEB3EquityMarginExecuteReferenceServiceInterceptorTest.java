head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.04.07.01.11.54;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	5444d9d078c63f4;
filename	WEB3EquityMarginExecuteReferenceServiceInterceptorTest.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : (WEB3EquityMarginExecuteReferenceServiceInterceptorTest.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2007/01/17　@崔遠鵬(中訊)
*/
package webbroker3.equity;

import java.sql.Timestamp;

import com.fitechlabs.xtrade.kernel.util.ThreadLocalSystemAttributesRegistry;
import com.fitechlabs.xtrade.plugin.tc.gentrade.data.BranchParams;
import com.fitechlabs.xtrade.plugin.tc.gentrade.data.InstitutionParams;
import com.fitechlabs.xtrade.plugin.tc.gentrade.data.MainAccountParams;

import test.util.TestDBUtility;
import test.util.TestSpecialClassUtility;

import webbroker3.common.define.WEB3OrderAccProductDef;
import webbroker3.common.define.WEB3OrderAccTransactionDef;
import webbroker3.common.define.WEB3ProductCodeDef;
import webbroker3.common.define.WEB3TradingTimeTypeDef;
import webbroker3.equity.define.WEB3EquityReferenceTypeDef;
import webbroker3.equity.message.WEB3EquityMarginExecuteReferenceRequest;
import webbroker3.gentrade.WEB3GentradeTradingClendarContext;
import webbroker3.gentrade.WEB3GentradeTradingTimeManagement;
import webbroker3.gentrade.WEB3GentradeTradingTimeManagementForMock;
import webbroker3.gentrade.data.CalendarRow;
import webbroker3.gentrade.data.TradingTimeParams;
import webbroker3.mock.TestBaseForMock;
import webbroker3.util.WEB3DateUtility;
import webbroker3.util.WEB3LogUtility;

/**
 * @@author 崔遠鵬(中訊)
 * @@version 1.0
 */
public class WEB3EquityMarginExecuteReferenceServiceInterceptorTest extends TestBaseForMock
{
    /**
     * ログユーティリティ<BR>
     */
    private static WEB3LogUtility log =
         WEB3LogUtility.getInstance(
             WEB3EquityMarginExecuteReferenceServiceInterceptorTest.class);

    WEB3EquityMarginExecuteReferenceServiceInterceptor l_equityMarginInterceptor = 
        new WEB3EquityMarginExecuteReferenceServiceInterceptor();

    public WEB3EquityMarginExecuteReferenceServiceInterceptorTest(String arg0)
    {
        super(arg0);
    }

    protected void setUp() throws Exception
    {
        super.setUp();
        MOCK_MANAGER.setIsMockUsed(true);
    }

    protected void tearDown() throws Exception
    {
        super.tearDown();
    }
    
    public void testOnCall_C0001()
    {
        final String STR_METHOD_NAME = ".testOnCall_C0001()";
        log.entering(TEST_START + STR_METHOD_NAME);

        TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
            "com.fitechlabs.xtrade.plugin.security.oplogin.impl.OpLoginSecurityServiceImpl", "getAccountId",
            new Class[] {}, new Long(333812512246L));

        try
        {
            // テーブルへデータをインサート
            MainAccountParams l_mainAccountParams = TestDBUtility.getMainAccountRow();
            TestDBUtility.insertWithDel(l_mainAccountParams);
            InstitutionParams l_institutionParams = TestDBUtility.getInstitutionRow();
            TestDBUtility.insertWithDel(l_institutionParams);
            BranchParams l_branchParams = TestDBUtility.getBranchRow();
            TestDBUtility.insertWithDel(l_branchParams);
            TradingTimeParams l_tradingTimeParams = TestDBUtility.getTradingTimeRow();
            l_tradingTimeParams.setMarketCode("0");
            l_tradingTimeParams.setTradingTimeType("01");
            l_tradingTimeParams.setBizDateType("1");
            TestDBUtility.insertWithDel(l_tradingTimeParams);

            Timestamp l_tsOrderAcceptTime = new Timestamp(WEB3DateUtility.getDate("20071228", "yyyyMMdd").getTime());
            
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                "com.fitechlabs.xtrade.plugin.tc.gentrade.stdimpls.TradingSystemImpl",
                "getSystemTimestamp",
                new Class[] {}, 
                l_tsOrderAcceptTime);
            
            WEB3GentradeTradingTimeManagementForMock.mockGetBizDateType(l_tsOrderAcceptTime, "1");
            
            WEB3EquityMarginExecuteReferenceRequest[] l_serviceParams = new WEB3EquityMarginExecuteReferenceRequest[1];
            l_serviceParams[0] = new WEB3EquityMarginExecuteReferenceRequest();

            // 実際メソッドをコール
            l_serviceParams[0].marketCode = null;
            l_serviceParams[0].referenceType = WEB3EquityReferenceTypeDef.REFERENCE_TYPE_VIEW;
            l_equityMarginInterceptor.onCall(null, l_serviceParams);

            // 予想結果と実際結果の比較
            WEB3GentradeTradingClendarContext l_context =
                (WEB3GentradeTradingClendarContext) ThreadLocalSystemAttributesRegistry
                .getAttribute(WEB3GentradeTradingTimeManagement.TRADING_CAL_CONTEXT_PATH);
            assertEquals("0D", l_context.getInstitutionCode());
            assertEquals("381", l_context.getBranchCode());
            assertNull(l_context.getMarketCode());
            assertEquals(WEB3TradingTimeTypeDef.EQUITY, l_context.getTradingTimeType());
            assertEquals(WEB3ProductCodeDef.DEFAULT, l_context.getProductCode());
            assertEquals(WEB3OrderAccProductDef.STOCK, l_context.getOrderAcceptProduct());
            assertEquals(WEB3OrderAccTransactionDef.REFERENCE, l_context.getOrderAcceptTransaction());
        }
        catch (Exception l_ex)
        {
            log.debug(STR_METHOD_NAME, l_ex);
            fail();
        }

        log.exiting(TEST_END + STR_METHOD_NAME);
    }

//    public void testOnCall_C0002()
//    {
//        final String STR_METHOD_NAME = ".testOnCall_C0002()";
//        log.entering(TEST_START + STR_METHOD_NAME);
//
//        TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
//            "com.fitechlabs.xtrade.plugin.security.oplogin.impl.OpLoginSecurityServiceImpl", "getAccountId",
//            new Class[] {}, new Long(333812512246L));
//
//        try
//        {
//            // テーブルへデータをインサート
//            MainAccountParams l_mainAccountParams = TestDBUtility.getMainAccountRow();
//            TestDBUtility.insertWithDel(l_mainAccountParams);
//            InstitutionParams l_institutionParams = TestDBUtility.getInstitutionRow();
//            TestDBUtility.insertWithDel(l_institutionParams);
//            BranchParams l_branchParams = TestDBUtility.getBranchRow();
//            TestDBUtility.insertWithDel(l_branchParams);
//            TradingTimeParams l_tradingTimeParams = TestDBUtility.getTradingTimeRow();
//            l_tradingTimeParams.setMarketCode("1");
//            l_tradingTimeParams.setTradingTimeType("01");
//            l_tradingTimeParams.setBizDateType("1");
//            TestDBUtility.insertWithDel(l_tradingTimeParams);
//
//            WEB3EquityMarginExecuteReferenceRequest[] l_serviceParams = new WEB3EquityMarginExecuteReferenceRequest[1];
//            l_serviceParams[0] = new WEB3EquityMarginExecuteReferenceRequest();
//
//            // 実際メソッドをコール
//            l_serviceParams[0].marketCode = "1";
//            l_serviceParams[0].referenceType = WEB3EquityReferenceTypeDef.REFERENCE_TYPE_UPDATE;
//            l_equityMarginInterceptor.onCall(null, l_serviceParams);
//
//            // 予想結果と実際結果の比較
//            WEB3GentradeTradingClendarContext l_context =
//                (WEB3GentradeTradingClendarContext) ThreadLocalSystemAttributesRegistry
//                .getAttribute(WEB3GentradeTradingTimeManagement.TRADING_CAL_CONTEXT_PATH);
//            assertEquals("0D", l_context.getInstitutionCode());
//            assertEquals("381", l_context.getBranchCode());
//            assertEquals("1", l_context.getMarketCode());
//            assertEquals(WEB3TradingTimeTypeDef.EQUITY, l_context.getTradingTimeType());
//            assertEquals(WEB3ProductCodeDef.DEFAULT, l_context.getProductCode());
//            assertEquals(WEB3OrderAccProductDef.STOCK, l_context.getOrderAcceptProduct());
//            assertEquals(WEB3OrderAccTransactionDef.CHANGE, l_context.getOrderAcceptTransaction());
//        }
//        catch (Exception l_ex)
//        {
//            log.debug(STR_METHOD_NAME, l_ex);
//            fail();
//        }
//
//        log.exiting(TEST_END + STR_METHOD_NAME);
//    }

    public void testOnReturn_C0001()
    {
        final String STR_METHOD_NAME = "testOnReturn_C0001()";
        log.entering(TEST_START + STR_METHOD_NAME);

        try
        {
            // 実際メソッドをコール
            TestSpecialClassUtility.testServiceInterceptor(l_equityMarginInterceptor);
            assertEquals(ThreadLocalSystemAttributesRegistry.
                getAttribute(WEB3GentradeTradingTimeManagement.TIMESTAMP_TAG), null);
            assertEquals(ThreadLocalSystemAttributesRegistry.
                getAttribute(WEB3GentradeTradingTimeManagement.OFFSET_TAG), null);
            assertEquals(ThreadLocalSystemAttributesRegistry.
                getAttribute(WEB3GentradeTradingTimeManagement.TRADING_CAL_CONTEXT_PATH), null);
        }
        catch (Exception l_ex)
        {
            log.error(STR_METHOD_NAME, l_ex);
            fail();
        }

        log.exiting(TEST_END + STR_METHOD_NAME);
    }

    public void testOnThrowable_C0001()
    {
        final String STR_METHOD_NAME = "testOnThrowable_C0001()";
        log.entering(TEST_START + STR_METHOD_NAME);

        try
        {
            // 実際メソッドをコール
            TestSpecialClassUtility.testServiceInterceptor(l_equityMarginInterceptor);
            assertEquals(ThreadLocalSystemAttributesRegistry.
                getAttribute(WEB3GentradeTradingTimeManagement.TIMESTAMP_TAG), null);
            assertEquals(ThreadLocalSystemAttributesRegistry.
                getAttribute(WEB3GentradeTradingTimeManagement.OFFSET_TAG), null);
            assertEquals(ThreadLocalSystemAttributesRegistry.
                getAttribute(WEB3GentradeTradingTimeManagement.TRADING_CAL_CONTEXT_PATH), null);
        }
        catch (Exception l_ex)
        {
            log.error(STR_METHOD_NAME, l_ex);
            fail();
        }

        log.exiting(TEST_END + STR_METHOD_NAME);
    }
}
@
