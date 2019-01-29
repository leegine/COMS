head	1.2;
access;
symbols;
locks; strict;
comment	@// @;


1.2
date	2011.04.08.00.53.39;	author che-jin;	state Exp;
branches;
next	1.1;
deltatype	text;
kopt	kv;
permissions	666;
commitid	1404d9e5c8b3d81;
filename	WEB3AioMultiBankTelegramProcessServiceInterceptorTest.java;

1.1
date	2011.04.07.01.34.37;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	5444d9d078c63f4;
filename	WEB3AioMultiBankTelegramProcessServiceInterceptorTest.java;


desc
@@


1.2
log
@*** empty log message ***
@
text
@package webbroker3.aio;

import java.util.Calendar;
import java.util.HashMap;

import javax.servlet.http.HttpServletRequest;

import com.fitechlabs.xtrade.plugin.security.oplogin.data.LoginSessionParams;
import com.fitechlabs.xtrade.plugin.security.oplogin.data.LoginSessionRow;

import test.util.TestDBUtility;

import webbroker3.aio.WEB3AioMultiBankSettleControlServiceImplTest.WEB3AioMultiBankSettleControlServiceImplForTest;
import webbroker3.aio.data.BankCashTransferStatusParams;
import webbroker3.aio.data.BankOrderRequestParams;
import webbroker3.aio.data.BankOrderRequestRow;
import webbroker3.aio.data.HostCashTransOrderAcceptParams;
import webbroker3.mock.TestBaseForMock;
import webbroker3.util.WEB3DateUtility;
import webbroker3.util.WEB3LogUtility;

public class WEB3AioMultiBankTelegramProcessServiceInterceptorTest extends TestBaseForMock
{
    private static WEB3LogUtility log =
        WEB3LogUtility.getInstance(WEB3AioMultiBankTelegramProcessServiceInterceptorTest.class);
    WEB3AioMultiBankTelegramProcessServiceInterceptor l_interceptor =
        new WEB3AioMultiBankTelegramProcessServiceInterceptor();
    public WEB3AioMultiBankTelegramProcessServiceInterceptorTest(String arg0)
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

    public void testOnCall()
    {
        final String STR_METHOD_NAME = "testOnCall()";
        log.entering(STR_METHOD_NAME);
        
        try
        {   
            HttpServletRequest[] l_serviceParams = new HttpServletRequest[1];
//            l_serviceParams[0] = new ServletRequestImpl();
            l_interceptor.onCall(null, l_serviceParams);
        }
        catch (Exception l_ex)
        {
            log.debug(l_ex.getMessage(), l_ex);
            fail();
        }
        
        log.exiting(STR_METHOD_NAME);
    }

}
@


1.1
log
@*** empty log message ***
@
text
@a21 2
import weblogic.servlet.internal.ServletRequestImpl;

d51 1
a51 1
            l_serviceParams[0] = new ServletRequestImpl();
@

