head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.04.07.00.53.13;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	5444d9d078c63f4;
filename	WEB3InformAccSwElecDeliApplyServiceInterceptorTest.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : WEB3InformAccSwElecDeliApplyServiceInterceptorTest.java
Author Name      : Daiwa Institute of Research
Revision History : 2007/08/22 金傑（中訊）新規作成
*/
package webbroker3.inform;

import java.lang.reflect.Method;

import com.fitechlabs.xtrade.plugin.tc.gentrade.data.BranchRow;
import com.fitechlabs.xtrade.plugin.tc.gentrade.data.InstitutionRow;

import test.util.TestDBUtility;

import webbroker3.common.WEB3BaseRuntimeException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.common.message.WEB3GenRequest;
import webbroker3.gentrade.WEB3GentradeBranch;
import webbroker3.gentrade.WEB3GentradeInstitution;
import webbroker3.gentrade.WEB3GentradeMainAccountForMock;
import webbroker3.gentrade.WEB3GentradeTradingClendarContext;
import webbroker3.gentrade.data.TradingTimeParams;
import webbroker3.gentrade.data.TradingTimeRow;
import webbroker3.inform.handler.WEB3InformAccSwElecDeliApplyHandlerTest;
import webbroker3.inform.message.WEB3InformAccSwElecDeliApplyInpRequest;
import webbroker3.inform.service.delegate.stdimpls.WEB3InformAccSwElecDeliApplyServiceImpl;
import webbroker3.mock.TestBaseForMock;
import webbroker3.util.WEB3LogUtility;

public class WEB3InformAccSwElecDeliApplyServiceInterceptorTest extends TestBaseForMock
{
    
    private WEB3InformAccSwElecDeliApplyServiceInterceptor l_interceptor = null;

    /**
     * (ログ出力ユーティリティ)
     */
    private static WEB3LogUtility log =
        WEB3LogUtility.getInstance(
                WEB3InformAccSwElecDeliApplyHandlerTest.class);
    
    public WEB3InformAccSwElecDeliApplyServiceInterceptorTest(String name)
    {
        super(name);
    }
    
    protected void setUp() throws Exception
    {
        super.setUp();
        MOCK_MANAGER.setIsMockUsed(true);
        this.l_interceptor = new WEB3InformAccSwElecDeliApplyServiceInterceptor();

    }

    protected void tearDown() throws Exception
    {
        this.l_interceptor = null;
        super.tearDown();
    }
    
    /**
     * 抛出異常信息：WEB3ErrorCatalog.SYSTEM_ERROR_80017
     *
     */
    public void testOnCall_C0001()
    {
        String STR_METHOD_NAME = "testOnCall_C0001()";
        log.entering(TEST_START + STR_METHOD_NAME);
        WEB3InformAccSwElecDeliApplyInpRequest l_request = new WEB3InformAccSwElecDeliApplyInpRequest();
        try
        {
            Method l_method = WEB3InformAccSwElecDeliApplyServiceImpl.class.getMethod("execute",new Class[]{WEB3GenRequest.class});
            Object l_obj = this.l_interceptor.onCall(l_method,null);
            fail();
        }
        catch(WEB3BaseRuntimeException l_ex)
        {
            log.error(STR_METHOD_NAME,l_ex);
            assertEquals(WEB3ErrorCatalog.SYSTEM_ERROR_80017, l_ex.getErrorInfo());
            log.exiting(TEST_END + STR_METHOD_NAME);
        }
        catch(Exception l_ex)
        {
            log.error(STR_METHOD_NAME,l_ex);
            fail();
            log.exiting(TEST_END + STR_METHOD_NAME);
        }
        log.exiting(TEST_END + STR_METHOD_NAME);
    }
    
    /**
     * 抛出異常信息：WEB3ErrorCatalog.SYSTEM_ERROR_80017
     *
     */
    public void testOnCall_C0002()
    {
        String STR_METHOD_NAME = "testOnCall_C0002()";
        log.entering(TEST_START + STR_METHOD_NAME);
        WEB3InformAccSwElecDeliApplyInpRequest l_request = new WEB3InformAccSwElecDeliApplyInpRequest();
        try
        {
            
            TestDBUtility.deleteAll(TradingTimeRow.TYPE);
            
            TradingTimeParams l_tradingTimeParams = TestDBUtility.getTradingTimeRow();
            l_tradingTimeParams.setInstitutionCode("0D");
            l_tradingTimeParams.setBranchCode("381");
            l_tradingTimeParams.setTradingTimeType("35");
            l_tradingTimeParams.setBizDateType("1");
            l_tradingTimeParams.setMarketCode("0");
            l_tradingTimeParams.setProductCode("0");
            
            TestDBUtility.insertWithDel(l_tradingTimeParams);
            
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                    "com.fitechlabs.xtrade.plugin.security.oplogin.impl.OpLoginSecurityServiceImpl",
                    "getAccountId",
                    new Class[] {},
                    new Long(12345));
            
            WEB3GentradeMainAccountForMock l_mainAccountForMock = new WEB3GentradeMainAccountForMock(TestDBUtility.getMainAccountRow());
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                    "webbroker3.gentrade.WEB3GentradeAccountManager",
                    "getMainAccount",
                    new Class[] {long.class},
                    l_mainAccountForMock);
            
            TestDBUtility.deleteAll(InstitutionRow.TYPE);
            TestDBUtility.insertWithDel(TestDBUtility.getInstitutionRow());
            WEB3GentradeInstitution l_institution = new WEB3GentradeInstitution(33);
            
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                    "webbroker3.gentrade.WEB3GentradeMainAccount", 
                    "getInstitution", 
                    new Class[]{},
                    l_institution);
            
            TestDBUtility.deleteAll(BranchRow.TYPE);
            TestDBUtility.insertWithDel(TestDBUtility.getBranchRow());
            WEB3GentradeBranch l_branch = new WEB3GentradeBranch(33381);
            
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                    "webbroker3.gentrade.WEB3GentradeMainAccount", 
                    "getBranch", 
                    new Class[] {},
                    l_branch);
                    
            Method l_method = WEB3InformAccSwElecDeliApplyServiceImpl.class.getMethod("execute",new Class[]{WEB3GenRequest.class});
            
            
            Object[] l_serviceParam = new Object[]{l_request};
            Object l_obj = this.l_interceptor.onCall(l_method,l_serviceParam);
            
            assertEquals("0D",((WEB3GentradeTradingClendarContext)l_obj).getInstitutionCode());
            assertEquals("381",((WEB3GentradeTradingClendarContext)l_obj).getBranchCode());
            assertEquals("0",((WEB3GentradeTradingClendarContext)l_obj).getMarketCode());
            assertEquals("35",((WEB3GentradeTradingClendarContext)l_obj).getTradingTimeType());
            assertEquals("0",((WEB3GentradeTradingClendarContext)l_obj).getProductCode());
            
        }
        catch(WEB3BaseRuntimeException l_ex)
        {
            log.error(STR_METHOD_NAME,l_ex);
            fail();
            log.exiting(TEST_END + STR_METHOD_NAME);
        }
        catch(Exception l_ex)
        {
            log.error(STR_METHOD_NAME,l_ex);
            fail();
            log.exiting(TEST_END + STR_METHOD_NAME);
        }
        log.exiting(TEST_END + STR_METHOD_NAME);
    }
}
@
