head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.04.07.02.28.00;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	5444d9d078c63f4;
filename	WEB3AdminEquityForcedSettleOrderApproveUnitServiceInterceptorTest.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : 
Author Name      : Daiwa Institute of Research
Revesion History : 
*/
package webbroker3.eqtypeadmin;

import com.fitechlabs.xtrade.kernel.util.ThreadLocalSystemAttributesRegistry;
import com.fitechlabs.xtrade.plugin.tc.gentrade.BooleanEnum;
import com.fitechlabs.xtrade.plugin.tc.gentrade.data.BranchParams;
import com.fitechlabs.xtrade.plugin.tc.gentrade.data.InstitutionParams;
import com.fitechlabs.xtrade.plugin.tc.gentrade.data.MainAccountParams;
import com.fitechlabs.xtrade.plugin.tc.gentrade.data.MarketParams;

import test.util.TestDBUtility;

import webbroker3.common.WEB3BaseException;
import webbroker3.common.WEB3BaseRuntimeException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.common.define.WEB3ThreadLocalSystemAttributePathDef;
import webbroker3.eqtypeadmin.data.AdminEqForcedSettleOrderParams;
import webbroker3.gentrade.WEB3AdministratorForMock;
import webbroker3.gentrade.WEB3GentradeTradingClendarContext;
import webbroker3.gentrade.WEB3GentradeTradingTimeManagement;
import webbroker3.gentrade.data.AdministratorParams;
import webbroker3.gentrade.data.AdministratorRow;
import webbroker3.gentrade.data.TradingTimeParams;
import webbroker3.mock.TestBaseForMock;
import webbroker3.util.WEB3LogUtility;

public class WEB3AdminEquityForcedSettleOrderApproveUnitServiceInterceptorTest extends TestBaseForMock
{
    public WEB3AdminEquityForcedSettleOrderApproveUnitServiceInterceptorTest(String name)
    {
        super(name);
    }

    /**
     * ログユーティリティ<BR>
     */
    private static WEB3LogUtility log =
        WEB3LogUtility.getInstance(WEB3AdminEquityForcedSettleOrderApproveUnitServiceInterceptorTest.class);
    
    protected void setUp() throws Exception
    {
        super.setUp();
    }

    protected void tearDown() throws Exception
    {
        super.tearDown();
    }

    /**
     * onCall
     */
    public void test_onCall_0001()
    {
        final String STR_METHOD_NAME = "test_onCall_0001()";
        log.entering(TEST_START + STR_METHOD_NAME);

        MOCK_MANAGER.setIsMockUsed(true);
        TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
            "com.fitechlabs.xtrade.plugin.security.oplogin.impl.OpLoginSecurityServiceImpl", "getAccountId",
             new Class[] {}, new Long(333812512246L));

        try
        {
            // テーブルへデータをインサート
            TestDBUtility.deleteAll(MainAccountParams.TYPE);
            TestDBUtility.deleteAll(InstitutionParams.TYPE);
            TestDBUtility.deleteAll(BranchParams.TYPE);
            TestDBUtility.deleteAll(TradingTimeParams.TYPE);
            TestDBUtility.deleteAll(MarketParams.TYPE);

            MarketParams l_marketParams = TestDBUtility.getMarketRow();
            TestDBUtility.insertWithDel(l_marketParams);
            MainAccountParams l_mainAccountParams = TestDBUtility.getMainAccountRow();
            TestDBUtility.insertWithDel(l_mainAccountParams);
            InstitutionParams l_institutionParams = TestDBUtility.getInstitutionRow();
            TestDBUtility.insertWithDel(l_institutionParams);
            BranchParams l_branchParams = TestDBUtility.getBranchRow();
            TestDBUtility.insertWithDel(l_branchParams);
            TradingTimeParams l_tradingTimeParams = TestDBUtility.getTradingTimeRow();
            l_tradingTimeParams.setMarketCode("SP");
            l_tradingTimeParams.setTradingTimeType("01");
            l_tradingTimeParams.setBizDateType("1");
            TestDBUtility.insertWithDel(l_tradingTimeParams);

            AdministratorParams l_administratorParams = new AdministratorParams();
            l_administratorParams.setInstitutionCode("0D");
            
            WEB3AdministratorForMock l_forMock = new WEB3AdministratorForMock(l_administratorParams);
            
            WEB3AdminEquityForcedSettleOrderApproveUnitServiceInterceptor l_interceptor =
                new WEB3AdminEquityForcedSettleOrderApproveUnitServiceInterceptor();
            
            AdminEqForcedSettleOrderParams l_params = new AdminEqForcedSettleOrderParams();
            l_params.setBranchId(33381);
            l_params.setMarketId(3303L);
            
            Object[] l_serviceParams = {l_params, l_forMock};
            l_interceptor.onCall(null, l_serviceParams);

            // 予想結果と実際結果の比較
            WEB3GentradeTradingClendarContext l_context =
                (WEB3GentradeTradingClendarContext)ThreadLocalSystemAttributesRegistry
                .getAttribute(WEB3GentradeTradingTimeManagement.TRADING_CAL_CONTEXT_PATH);
            
            Integer l_offset = (Integer)ThreadLocalSystemAttributesRegistry
            .getAttribute(WEB3GentradeTradingTimeManagement.OFFSET_TAG);
            assertEquals("0", l_offset.toString());
            
            assertEquals("0D", l_context.getInstitutionCode());
            assertEquals("381", l_context.getBranchCode());
            assertEquals("SP", l_context.getMarketCode());
            assertEquals("01", l_context.getTradingTimeType());
            
            assertEquals("0", l_context.getProductCode());
            assertEquals("03", l_context.getOrderAcceptProduct());
            assertEquals("03", l_context.getOrderAcceptTransaction());
            
            BooleanEnum l_ = (BooleanEnum)ThreadLocalSystemAttributesRegistry.getAttribute(
                    WEB3ThreadLocalSystemAttributePathDef.FORCED_SETTLE_ORDER_VALIDATION_SKIP);
            assertEquals("1", "" + l_.intValue());
            
        }
        catch (Exception l_ex)
        {
            log.debug(STR_METHOD_NAME, l_ex);
            fail();
        }

        log.exiting(TEST_END + STR_METHOD_NAME);
    }
    
    
    /**
     * onCall
     */
    public void test_onCall_0002()
    {
        final String STR_METHOD_NAME = "test_onCall_0002()";
        log.entering(TEST_START + STR_METHOD_NAME);

        MOCK_MANAGER.setIsMockUsed(true);
        TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
            "com.fitechlabs.xtrade.plugin.security.oplogin.impl.OpLoginSecurityServiceImpl", "getAccountId",
             new Class[] {}, new Long(333812512246L));

        try
        {
            // テーブルへデータをインサート
            TestDBUtility.deleteAll(MainAccountParams.TYPE);
            TestDBUtility.deleteAll(InstitutionParams.TYPE);
            TestDBUtility.deleteAll(BranchParams.TYPE);
            TestDBUtility.deleteAll(TradingTimeParams.TYPE);
            TestDBUtility.deleteAll(MarketParams.TYPE);

            MarketParams l_marketParams = TestDBUtility.getMarketRow();
            TestDBUtility.insertWithDel(l_marketParams);
            MainAccountParams l_mainAccountParams = TestDBUtility.getMainAccountRow();
            TestDBUtility.insertWithDel(l_mainAccountParams);
            InstitutionParams l_institutionParams = TestDBUtility.getInstitutionRow();
            TestDBUtility.insertWithDel(l_institutionParams);
            BranchParams l_branchParams = TestDBUtility.getBranchRow();
            TestDBUtility.insertWithDel(l_branchParams);
//            TradingTimeParams l_tradingTimeParams = TestDBUtility.getTradingTimeRow();
//            l_tradingTimeParams.setMarketCode("SP");
//            l_tradingTimeParams.setTradingTimeType("01");
//            l_tradingTimeParams.setBizDateType("1");
//            TestDBUtility.insertWithDel(l_tradingTimeParams);

            AdministratorParams l_administratorParams = new AdministratorParams();
            l_administratorParams.setInstitutionCode("0D");
            
            WEB3AdministratorForMock l_forMock = new WEB3AdministratorForMock(l_administratorParams);
            
            WEB3AdminEquityForcedSettleOrderApproveUnitServiceInterceptor l_interceptor =
                new WEB3AdminEquityForcedSettleOrderApproveUnitServiceInterceptor();
            
            AdminEqForcedSettleOrderParams l_params = new AdminEqForcedSettleOrderParams();
            l_params.setBranchId(33381);
            l_params.setMarketId(3303L);
            
            Object[] l_serviceParams = {l_params, l_forMock};
            l_interceptor.onCall(null, l_serviceParams);
            fail();
            
        }
        catch (WEB3BaseRuntimeException l_ex)
        {
            assertEquals(WEB3ErrorCatalog.SYSTEM_ERROR_80005, l_ex.getErrorInfo());
        }
        catch (Exception l_ex)
        {
            log.debug(STR_METHOD_NAME, l_ex);
            fail();
        }

        log.exiting(TEST_END + STR_METHOD_NAME);
    }
    
    
    /**
     * onReturn
     *
     */
    public void test_onReturn_0001()
    {
        final String STR_METHOD_NAME = "test_onReturn_0001()";
        log.entering(TEST_START + STR_METHOD_NAME);

        WEB3AdminEquityForcedSettleOrderApproveUnitServiceInterceptor l_interceptor =
            new WEB3AdminEquityForcedSettleOrderApproveUnitServiceInterceptor();
        
        l_interceptor.onReturn(null, null);
        
        BooleanEnum l_ = (BooleanEnum)ThreadLocalSystemAttributesRegistry.getAttribute(
                WEB3ThreadLocalSystemAttributePathDef.FORCED_SETTLE_ORDER_VALIDATION_SKIP);
        
        assertNull(l_);
        
        assertNull(ThreadLocalSystemAttributesRegistry.getAttribute(WEB3GentradeTradingTimeManagement.TIMESTAMP_TAG));
        assertNull(ThreadLocalSystemAttributesRegistry.getAttribute(WEB3GentradeTradingTimeManagement.OFFSET_TAG));
        assertNull(ThreadLocalSystemAttributesRegistry.getAttribute(WEB3GentradeTradingTimeManagement.TRADING_CAL_CONTEXT_PATH));
        
        log.exiting(TEST_END + STR_METHOD_NAME);
    }
    
    /**
     * onThrowable
     *
     */
    public void test_onThrowable_0001()
    {
        final String STR_METHOD_NAME = "test_onThrowable_0001()";
        log.entering(TEST_START + STR_METHOD_NAME);

        WEB3AdminEquityForcedSettleOrderApproveUnitServiceInterceptor l_interceptor =
            new WEB3AdminEquityForcedSettleOrderApproveUnitServiceInterceptor();
        
        l_interceptor.onThrowable(null, null);
        
        BooleanEnum l_ = (BooleanEnum)ThreadLocalSystemAttributesRegistry.getAttribute(
                WEB3ThreadLocalSystemAttributePathDef.FORCED_SETTLE_ORDER_VALIDATION_SKIP);
        assertNull(l_);
        assertNull(ThreadLocalSystemAttributesRegistry.getAttribute(WEB3GentradeTradingTimeManagement.TIMESTAMP_TAG));
        assertNull(ThreadLocalSystemAttributesRegistry.getAttribute(WEB3GentradeTradingTimeManagement.OFFSET_TAG));
        assertNull(ThreadLocalSystemAttributesRegistry.getAttribute(WEB3GentradeTradingTimeManagement.TRADING_CAL_CONTEXT_PATH));
        
        
        log.exiting(TEST_END + STR_METHOD_NAME);
    }
}
@
