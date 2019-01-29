head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.04.07.00.53.18;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	5444d9d078c63f4;
filename	WEB3AdminInformProfDistSellTransSrcListServiceInterceptorTest.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@package webbroker3.inform;

import java.lang.reflect.Method;
import java.util.Calendar;

import com.fitechlabs.xtrade.kernel.boot.Services;
import com.fitechlabs.xtrade.plugin.security.oplogin.impl.OpLoginSecurityServiceImpl;
import com.fitechlabs.xtrade.plugin.tc.gentrade.FinApp;
import com.fitechlabs.xtrade.plugin.tc.gentrade.TradingSystem;
import com.fitechlabs.xtrade.plugin.tc.gentrade.data.BranchParams;
import com.fitechlabs.xtrade.plugin.tc.gentrade.data.InstitutionParams;
import com.fitechlabs.xtrade.plugin.tc.gentrade.data.MainAccountParams;

import test.util.TestDBUtility;
import test.util.TestSpecialClassUtility;

import webbroker3.common.WEB3BaseRuntimeException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.gentrade.WEB3GentradeTradingClendarContext;
import webbroker3.gentrade.WEB3GentradeTradingTimeManagement;
import webbroker3.gentrade.data.TradingTimeParams;
import webbroker3.inform.message.WEB3AdminInformProfDistSellTransSrcInpRequest;
import webbroker3.mock.TestBaseForMock;
import webbroker3.util.WEB3LogUtility;

public class WEB3AdminInformProfDistSellTransSrcListServiceInterceptorTest extends TestBaseForMock
{
    /**
     * (ログ出力ユーティリティ)
     */
    private static WEB3LogUtility log =
        WEB3LogUtility.getInstance(
            WEB3AdminInformProfDistSellTransSrcListServiceInterceptorTest.class);

    public WEB3AdminInformProfDistSellTransSrcListServiceInterceptorTest(String arg0)
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
    
    public void testOnCall_0001()
    {
        String STR_METHOD_NAME = "testOnCall_0001()";
        log.entering(TEST_START + STR_METHOD_NAME);

        WEB3AdminInformProfDistSellTransSrcListServiceInterceptor l_inter =
            new WEB3AdminInformProfDistSellTransSrcListServiceInterceptor();

        try
        {
            Method l_thisMethod = null;
            Object[] l_objs = {};
            Object l_objReturnValue = l_inter.onCall(l_thisMethod, l_objs);
            
            log.exiting(TEST_END + STR_METHOD_NAME);
            assertNotNull(l_objReturnValue);
        }
        catch (WEB3BaseRuntimeException l_ex)
        {
            log.error(TEST_END + STR_METHOD_NAME, l_ex);
            log.exiting(TEST_END + STR_METHOD_NAME);
            assertEquals(WEB3ErrorCatalog.SYSTEM_ERROR_80017, l_ex.getErrorInfo());
        }
        catch (Exception l_ex)
        {
            log.error(TEST_END + STR_METHOD_NAME, l_ex);
            log.exiting(TEST_END + STR_METHOD_NAME);
            fail();
        }

    }
    
    public void testOnCall_0002()
    {
        String STR_METHOD_NAME = "testOnCall_0002()";
        log.entering(TEST_START + STR_METHOD_NAME);
        try
        {
            MainAccountParams l_mainAccountParams = TestDBUtility.getMainAccountRow();
            TestDBUtility.deleteAll(l_mainAccountParams.getRowType());
            TestDBUtility.insertWithDel(l_mainAccountParams);

            MOCK_MANAGER.setIsMockUsed(true);
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                "com.fitechlabs.xtrade.plugin.security.oplogin.impl.OpLoginSecurityServiceImpl",
                "getAccountId",
                new Class[] {}, new Long(l_mainAccountParams.getAccountId()));

            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                "com.fitechlabs.xtrade.plugin.security.oplogin.impl.OpLoginSecurityServiceImpl",
                "isAccountIdSet",
                new Class[] {}, Boolean.TRUE);

            WEB3AdminInformProfDistSellTransSrcListServiceInterceptor l_inter =
                new WEB3AdminInformProfDistSellTransSrcListServiceInterceptor();
    
            Method l_thisMethod = null;
            Object[] l_objs = {new WEB3AdminInformProfDistSellTransSrcInpRequest()};
 
//            Services.overrideService(OpLoginSecurityService.class, new OpLoginSecurityServiceImplForTest());

            TradingTimeParams l_tradingTimeParams = new TradingTimeParams();

            FinApp l_finApp = (FinApp) Services.getService(FinApp.class);
            TradingSystem l_tradingSys = l_finApp.getTradingSystem();
            java.sql.Timestamp l_processTime = l_tradingSys.getSystemTimestamp();
            
            l_tradingTimeParams.setInstitutionCode("0D");
            l_tradingTimeParams.setBranchCode("381");
            l_tradingTimeParams.setMarketCode("0");
            l_tradingTimeParams.setTradingTimeType("00");
            l_tradingTimeParams.setProductCode("0");
            l_tradingTimeParams.setBizDateType(WEB3GentradeTradingTimeManagement.getBizDateType(l_processTime));
            l_tradingTimeParams.setStartTime("000000");
            l_tradingTimeParams.setEndTime("235959");        
            l_tradingTimeParams.setSubmitMarketTrigger("0");
            l_tradingTimeParams.setEnableOrder("0");
            l_tradingTimeParams.setBizdateCalcParameter("0");
            l_tradingTimeParams.setCreatedTimestamp(Calendar.getInstance().getTime());
            l_tradingTimeParams.setLastUpdatedTimestamp(Calendar.getInstance().getTime());
            TestDBUtility.deleteAll(l_tradingTimeParams.getRowType());
            TestDBUtility.insertWithDel(l_tradingTimeParams);

            InstitutionParams l_institutionParams = TestDBUtility.getInstitutionRow();
            l_institutionParams.setInstitutionId(33L);
            TestDBUtility.deleteAll(l_institutionParams.getRowType());
            TestDBUtility.insertWithDel(l_institutionParams);
            
            BranchParams l_branchParams = TestDBUtility.getBranchRow();
            l_branchParams.setBranchId(33381L);
            TestDBUtility.deleteAll(l_branchParams.getRowType());
            TestDBUtility.insertWithDel(l_branchParams);

            WEB3GentradeTradingClendarContext l_context = 
                (WEB3GentradeTradingClendarContext) l_inter.onCall(l_thisMethod, l_objs);
            
            assertEquals("0D", l_context.getInstitutionCode());
            assertEquals("381", l_context.getBranchCode());
            assertEquals("0", l_context.getMarketCode());
            assertEquals("00", l_context.getTradingTimeType());
            assertEquals("0", l_context.getProductCode());
            assertEquals("22", l_context.getOrderAcceptProduct());
            assertEquals("07", l_context.getOrderAcceptTransaction());
        }
        catch (Exception l_ex)
        {
            log.error(TEST_END + STR_METHOD_NAME, l_ex);
            log.exiting(TEST_END + STR_METHOD_NAME);
            fail();
        }
        log.exiting(TEST_END + STR_METHOD_NAME);
    }
    
    public void testOnReturnOnThrowable_0001()
    {
        WEB3AdminInformProfDistSellTransSrcListServiceInterceptor l_inter = 
            new WEB3AdminInformProfDistSellTransSrcListServiceInterceptor();
        TestSpecialClassUtility.testServiceInterceptor(l_inter);
    }
    
    public class OpLoginSecurityServiceImplForTest extends OpLoginSecurityServiceImpl
    {
        public long getAccountId()
        {
            return 333812512246L;
        }
    }

}
@
