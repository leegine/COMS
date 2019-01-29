head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.04.07.00.53.54;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	5444d9d078c63f4;
filename	WEB3AdminInformProfDistRegistVoucherMakeInterceptorTest.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : //TODO(WEB3AdminInformProfDistRegistVoucherMakeInterceptorTest.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2007/06/08 徐宏偉 (中訊) 新規作成
*/
package webbroker3.inform;

import com.fitechlabs.xtrade.kernel.util.ThreadLocalSystemAttributesRegistry;
import com.fitechlabs.xtrade.plugin.tc.gentrade.data.BranchParams;
import com.fitechlabs.xtrade.plugin.tc.gentrade.data.InstitutionParams;
import com.fitechlabs.xtrade.plugin.tc.gentrade.data.MainAccountParams;

import test.util.TestDBUtility;
import test.util.TestSpecialClassUtility;

import webbroker3.common.WEB3BaseRuntimeException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.gentrade.WEB3Administrator;
import webbroker3.gentrade.WEB3AdministratorForMock;
import webbroker3.gentrade.WEB3GentradeTradingClendarContext;
import webbroker3.gentrade.WEB3GentradeTradingTimeManagement;
import webbroker3.gentrade.WEB3GentradeTradingTimeManagementForMock;
import webbroker3.gentrade.data.AdministratorParams;
import webbroker3.gentrade.data.TradingTimeParams;
import webbroker3.inform.message.WEB3AdminInformProfDistStatusSearchInputRequest;
import webbroker3.inform.message.WEB3AdminInformProfDistVoucherChgCnfRequest;
import webbroker3.mock.TestBaseForMock;
import webbroker3.util.WEB3LogUtility;

/**
 * XXXXXXクラス//TODO
 *
 * @@author 徐宏偉(中訊)
 * @@version 1.0
 */
public class WEB3AdminInformProfDistRegistVoucherMakeInterceptorTest extends TestBaseForMock
{
    /**
     * (ログ出力ユーティリティ)
     */
    private static WEB3LogUtility log =
        WEB3LogUtility.getInstance(
                WEB3AdminInformProfDistRegistVoucherMakeInterceptorTest.class);
    WEB3AdminInformProfDistRegistVoucherMakeInterceptor l_interceptor = 
        new WEB3AdminInformProfDistRegistVoucherMakeInterceptor();
    public WEB3AdminInformProfDistRegistVoucherMakeInterceptorTest(String arg0)
    {
        super(arg0);
    }

    protected void setUp() throws Exception
    {
        super.setUp();
    }

    protected void tearDown() throws Exception
    {
        MOCK_MANAGER.setIsMockUsed(true);
        super.tearDown();
    }

    public void testOnCall_001()
    {
        final String STR_METHOD_NAME = "testOnCall_001()";
        log.entering(TEST_START + STR_METHOD_NAME);
        
        try
        {
            MOCK_MANAGER.setIsMockUsed(true);
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                "com.fitechlabs.xtrade.plugin.security.oplogin.impl.OpLoginSecurityServiceImpl", "getAccountId",
                new Class[] {}, new Long(333812512246L));
            WEB3AdminInformProfDistVoucherChgCnfRequest[] l_serviceParams = null;
            l_interceptor.onCall(null, l_serviceParams);
        }
        catch(WEB3BaseRuntimeException l_ex)
        {
            log.error(STR_METHOD_NAME, l_ex);
            assertEquals(WEB3ErrorCatalog.SYSTEM_ERROR_80017, l_ex.getErrorInfo());
        }
        catch (Exception l_ex)
        {
            log.error(l_ex.getMessage(), l_ex);
            fail();
        }
        
        log.exiting(TEST_END + STR_METHOD_NAME);
    }
    
    /*
     * Test method for 'webbroker3.ifo.WEB3OptionChangeOpenContractServiceInterceptor.onCall(Method, Object[])'
     */
    public void testOnCall_002()
    {
        final String STR_METHOD_NAME = "testOnCall_002()";
        log.entering(TEST_START + STR_METHOD_NAME);

        MOCK_MANAGER.setIsMockUsed(true);
        TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                "com.fitechlabs.xtrade.plugin.security.oplogin.impl.OpLoginSecurityServiceImpl", "getAccountId",
                new Class[] {}, new Long(333812512246L));

        TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                "com.fitechlabs.xtrade.plugin.security.oplogin.impl.OpLoginSecurityServiceImpl", "isAccountIdSet",
                new Class[] {}, Boolean.FALSE);

        try
        {
            // テーブルへデータをインサート
            WEB3GentradeTradingTimeManagementForMock.mockSetTimestamp();

            TradingTimeParams l_tradingTimeParams = TestDBUtility.getTradingTimeRow();
            l_tradingTimeParams.setMarketCode("0");
            l_tradingTimeParams.setTradingTimeType("35");
            l_tradingTimeParams.setBizDateType("1");
            l_tradingTimeParams.setProductCode("0");
            l_tradingTimeParams.setInstitutionCode("0D");
            l_tradingTimeParams.setBranchCode("381");

            TestDBUtility.insertWithDel(l_tradingTimeParams);

            WEB3AdminInformProfDistStatusSearchInputRequest[] l_serviceParams =
                new WEB3AdminInformProfDistStatusSearchInputRequest[1];
            l_serviceParams[0] = new WEB3AdminInformProfDistStatusSearchInputRequest();
            
            TestDBUtility.deleteAll(AdministratorParams.TYPE);
            AdministratorParams l_administratorParams = new AdministratorParams();
            l_administratorParams.setAdministratorCode("123456789");
            l_administratorParams.setAdministratorId(123456l);
            l_administratorParams.setBranchCode("381");
            l_administratorParams.setInstitutionCode("0D");
            l_administratorParams.setInstitutionId(60L);
            l_administratorParams.setLoginId(123456l);
            l_administratorParams.setPermissionLevel("01");
            TestDBUtility.insertWithDel(l_administratorParams);
            WEB3Administrator l_administratorSet = new WEB3Administrator(l_administratorParams);
            WEB3AdministratorForMock.mockGetInstanceFromLoginInfo(l_administratorSet);
            // 実際メソッドをコール
            l_interceptor.onCall(null, l_serviceParams);

            // 予想結果と実際結果の比較
            WEB3GentradeTradingClendarContext l_context =
                (WEB3GentradeTradingClendarContext)
                ThreadLocalSystemAttributesRegistry.getAttribute(
                    WEB3GentradeTradingTimeManagement.TRADING_CAL_CONTEXT_PATH);
            assertEquals("0D", l_context.getInstitutionCode());
            assertEquals("381", l_context.getBranchCode());
            assertEquals("0", l_context.getMarketCode());
            assertEquals("35", l_context.getTradingTimeType());
            assertEquals("0", l_context.getProductCode());
            assertEquals("22", l_context.getOrderAcceptProduct());
            assertEquals("00", l_context.getOrderAcceptTransaction());
        }
        catch (Exception l_ex)
        {
            log.debug(STR_METHOD_NAME, l_ex);
            fail();
        }
        log.exiting(TEST_END + STR_METHOD_NAME);
    }

    /*
     * Test method for 'webbroker3.ifo.WEB3OptionChangeOpenContractServiceInterceptor.onReturn(Object, Object)'
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
     * Test method for 'webbroker3.ifo.WEB3OptionChangeOpenContractServiceInterceptor.onThrowable(Object, Throwable)'
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
