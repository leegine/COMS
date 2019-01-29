head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.04.07.01.28.00;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	5444d9d078c63f4;
filename	WEB3IfoDepositTransitionReferenceServiceInterceptorTest.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@package webbroker3.ifodeposit;

import com.fitechlabs.xtrade.kernel.util.ThreadLocalSystemAttributesRegistry;
import com.fitechlabs.xtrade.plugin.tc.gentrade.data.BranchParams;
import com.fitechlabs.xtrade.plugin.tc.gentrade.data.BranchRow;
import com.fitechlabs.xtrade.plugin.tc.gentrade.data.InstitutionParams;
import com.fitechlabs.xtrade.plugin.tc.gentrade.data.InstitutionRow;
import com.fitechlabs.xtrade.plugin.tc.gentrade.data.MainAccountParams;
import com.fitechlabs.xtrade.plugin.tc.gentrade.data.MainAccountRow;

import test.util.TestDBUtility;
import test.util.TestSpecialClassUtility;

import webbroker3.gentrade.WEB3GentradeTradingClendarContext;
import webbroker3.gentrade.WEB3GentradeTradingTimeManagement;
import webbroker3.gentrade.WEB3GentradeTradingTimeManagementForMock;
import webbroker3.mock.TestBaseForMock;
import webbroker3.util.WEB3LogUtility;

public class WEB3IfoDepositTransitionReferenceServiceInterceptorTest extends TestBaseForMock
{

    private static WEB3LogUtility log = WEB3LogUtility
            .getInstance(WEB3IfoDepositTransitionReferenceServiceInterceptorTest.class);

    private WEB3IfoDepositTransitionReferenceServiceInterceptor l_interceptor = 
        null;

    public WEB3IfoDepositTransitionReferenceServiceInterceptorTest(String arg0)
    {
        super(arg0);
    }

    protected void setUp() throws Exception
    {
        super.setUp();
        MOCK_MANAGER.setIsMockUsed(true);
        this.l_interceptor = new WEB3IfoDepositTransitionReferenceServiceInterceptor();
    }

    protected void tearDown() throws Exception
    {
        super.tearDown();
    }

    /*
     * Test method for
     * 'webbroker3.ifodeposit.WEB3IfoDepositTransitionReferenceServiceInterceptor.WEB3IfoDepositTransitionReferenceServiceInterceptor()'
     */
    public void testOnCall_Case0001()
    {
        final String STR_METHOD_NAME = "testOnCall_C0001()";
        log.entering(TEST_START + STR_METHOD_NAME);

        MOCK_MANAGER.setIsMockUsed(true);

        try
        {
            TestDBUtility.deleteAll(MainAccountRow.TYPE);
            MainAccountParams l_mainAccountParams = TestDBUtility.getMainAccountRow();
            l_mainAccountParams.setBranchCode("381");
            l_mainAccountParams.setInstitutionId(33L);
            l_mainAccountParams.setInstitutionCode("0D");
            TestDBUtility.insertWithDel(l_mainAccountParams);

            TestDBUtility.deleteAll(InstitutionRow.TYPE);
            InstitutionParams l_institutionParams = TestDBUtility.getInstitutionRow();
            TestDBUtility.insertWithDel(l_institutionParams);
            
            TestDBUtility.deleteAll(BranchRow.TYPE);
            BranchParams l_branchParams = TestDBUtility.getBranchRow();
            TestDBUtility.insertWithDel(l_branchParams);
            
            WEB3GentradeTradingClendarContext l_tmpClendarContext = new WEB3GentradeTradingClendarContext();
            l_tmpClendarContext.setInstitutionCode("0D");
            l_tmpClendarContext.setBranchCode("381");
            l_tmpClendarContext.setMarketCode("0");
            l_tmpClendarContext.setTradingTimeType("11");
            l_tmpClendarContext.setBizDateType("1");
            l_tmpClendarContext.setProductCode("0");
            WEB3GentradeTradingTimeManagementForMock.setClendarContext(l_tmpClendarContext);
            WEB3GentradeTradingTimeManagementForMock.mockSetTimestamp();
            
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                    "com.fitechlabs.xtrade.plugin.security.oplogin.impl.OpLoginSecurityServiceImpl",
                    "getAccountId",
                    new Class[] {},
                    new Long(333812512246L));   
            
            l_interceptor.onCall(null, null);

            WEB3GentradeTradingClendarContext l_context =
                (WEB3GentradeTradingClendarContext)
                ThreadLocalSystemAttributesRegistry.getAttribute(
                    WEB3GentradeTradingTimeManagement.TRADING_CAL_CONTEXT_PATH);
            
            assertEquals("0D", l_context.getInstitutionCode());
            assertEquals("381", l_context.getBranchCode());
            assertEquals("0", l_context.getMarketCode());
            assertEquals("11", l_context.getTradingTimeType());
            assertEquals("0", l_context.getProductCode());
            assertEquals("31", l_context.getOrderAcceptProduct());
            assertEquals("07", l_context.getOrderAcceptTransaction());
        }
        catch (Exception l_ex)
        {
            log.debug(l_ex.getMessage(), l_ex);
            fail();
        }
        
        log.exiting(STR_METHOD_NAME);
    }
    
    public void testOnReturn_C0001()
    {
        final String STR_METHOD_NAME = "testOnReturn_C0001()";
        log.entering(TEST_START + STR_METHOD_NAME);

        try
        {
            // 実際メソッドをコール
            TestSpecialClassUtility.testServiceInterceptor(l_interceptor);
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
            TestSpecialClassUtility.testServiceInterceptor(l_interceptor);
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
