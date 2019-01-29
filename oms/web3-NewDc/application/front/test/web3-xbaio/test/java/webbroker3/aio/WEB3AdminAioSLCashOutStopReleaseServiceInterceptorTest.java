head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.04.07.01.35.07;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	5444d9d078c63f4;
filename	WEB3AdminAioSLCashOutStopReleaseServiceInterceptorTest.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : WEB3AdminAioSLCashOutStopReleaseServiceInterceptorTest.java
Author Name      : Daiwa Institute of Research
Revision History : 2007/09/20 金傑（中訊）新規作成
*/
package webbroker3.aio;

import java.lang.reflect.Method;

import com.fitechlabs.xtrade.plugin.security.oplogin.impl.LoginInfoImplForMock;

import test.util.TestDBUtility;

import webbroker3.aio.message.WEB3AdminSLCashOutStopReleaseConfirmRequest;
import webbroker3.aio.service.delegate.stdimpls.WEB3AdminAioSLCashOutStopReleaseServiceImpl;
import webbroker3.common.message.WEB3GenRequest;
import webbroker3.gentrade.data.AdministratorParams;
import webbroker3.gentrade.data.AdministratorRow;
import webbroker3.gentrade.data.TradingTimeParams;
import webbroker3.gentrade.data.TradingTimeRow;
import webbroker3.mock.TestBaseForMock;
import webbroker3.util.WEB3LogUtility;

public class WEB3AdminAioSLCashOutStopReleaseServiceInterceptorTest extends TestBaseForMock
{

    private WEB3AdminAioSLCashOutStopReleaseServiceInterceptor l_interceptor = null;

    /**
     * ログユーティリティ<BR>
     */
    private static WEB3LogUtility log =
         WEB3LogUtility.getInstance(
                 WEB3AdminAioSLCashOutStopReleaseServiceInterceptorTest.class);
    
    public WEB3AdminAioSLCashOutStopReleaseServiceInterceptorTest(String name)
    {
        super(name);
    }
    
    protected void setUp() throws Exception
    {
        super.setUp();
        TestBaseForMock.MOCK_MANAGER.setIsMockUsed(true);
        this.l_interceptor = new WEB3AdminAioSLCashOutStopReleaseServiceInterceptor();
    }
    
    protected void tearDown() throws Exception
    {
        this.l_interceptor = null;
        super.tearDown();
    }
    
    /**
     * 正常結束
     *
     */
    public void testOnCall_C0001()
    {
        String STR_METHOD_NAME = "testOnCall_C0001()";
        log.entering(TEST_START + STR_METHOD_NAME);
        try
        {
            
            LoginInfoImplForMock l_loginInfo = new LoginInfoImplForMock();
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                    "com.fitechlabs.xtrade.plugin.security.oplogin.impl.OpLoginSecurityServiceImpl", 
                    "getLoginInfo",
                    new Class[] {},
                    l_loginInfo);
            
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                    "com.fitechlabs.xtrade.plugin.security.oplogin.impl.LoginInfoImpl",
                    "getLoginId",
                    new Class[] {},
                    new Long(12345));
            
            TestDBUtility.deleteAll(TradingTimeRow.TYPE);
            TradingTimeParams l_tradingTimeParams = TestDBUtility.getTradingTimeRow();
            l_tradingTimeParams.setInstitutionCode("0D");
            l_tradingTimeParams.setBranchCode("381");
            l_tradingTimeParams.setTradingTimeType("00");
            l_tradingTimeParams.setBizDateType("1");
            l_tradingTimeParams.setMarketCode("0");
            l_tradingTimeParams.setProductCode("0");
            
            TestDBUtility.insertWithDel(l_tradingTimeParams);
            
            // AdministratorDao
            TestDBUtility.deleteAll(AdministratorRow.TYPE);
            AdministratorParams l_administratorParams = TestDBUtility.getAdministratorRow();
            l_administratorParams.setLoginId(12345);
            l_administratorParams.setBranchCode("381");
            TestDBUtility.insertWithDel(l_administratorParams);

            Method l_method = WEB3AdminAioSLCashOutStopReleaseServiceImpl.class.getMethod(
            "execute",new Class[]{WEB3GenRequest.class});
            
            WEB3AdminSLCashOutStopReleaseConfirmRequest l_request =
                new WEB3AdminSLCashOutStopReleaseConfirmRequest();
            
            Object[] l_serviceParam = new Object[]{l_request};
            this.l_interceptor.onCall(l_method,l_serviceParam);
        }
        catch(Exception l_ex)
        {
            log.error("", l_ex);
            log.exiting(TEST_END + STR_METHOD_NAME);
            fail();
        }
        log.exiting(TEST_END + STR_METHOD_NAME);
    }
}
@
