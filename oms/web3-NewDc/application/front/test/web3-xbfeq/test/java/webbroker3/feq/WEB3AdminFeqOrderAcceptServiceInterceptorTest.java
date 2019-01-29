head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.04.07.01.54.52;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	5444d9d078c63f4;
filename	WEB3AdminFeqOrderAcceptServiceInterceptorTest.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@package webbroker3.feq;

import com.fitechlabs.xtrade.kernel.util.ThreadLocalSystemAttributesRegistry;

import webbroker3.gentrade.WEB3Administrator;
import webbroker3.gentrade.WEB3AdministratorForMock;
import webbroker3.gentrade.WEB3GentradeTradingClendarContext;
import webbroker3.gentrade.WEB3GentradeTradingTimeManagement;
import webbroker3.gentrade.data.AdministratorParams;
import webbroker3.util.WEB3LogUtility;

import test.util.JunitTestBase;
import test.util.TestDBUtility;

public class WEB3AdminFeqOrderAcceptServiceInterceptorTest extends JunitTestBase
{
    /**
     * ログ出力ユーティリティ。
     */
    private static WEB3LogUtility log =
        WEB3LogUtility.getInstance(WEB3AdminFeqOrderAcceptServiceInterceptorTest.class);
    WEB3AdminFeqOrderAcceptServiceInterceptor l_intercepter = null;
    public WEB3AdminFeqOrderAcceptServiceInterceptorTest(String arg0)
    {
        super(arg0);
    }

    protected void setUp() throws Exception
    {
        super.setUp();
        l_intercepter = new WEB3AdminFeqOrderAcceptServiceInterceptor();
    }

    protected void tearDown() throws Exception
    {
        super.tearDown();
    }
    
    public void testOncall()
    {
        final String STR_METHOD_NAME = "testOncall()";
        log.entering(STR_METHOD_NAME);
        try
        {
            //AdministratorParams
            TestDBUtility.deleteAll(AdministratorParams.TYPE);
            AdministratorParams l_administratorParams =
                TestDBUtility.getAdministratorRow();
            TestDBUtility.insertWithDel(l_administratorParams);

            WEB3Administrator l_administrator = new WEB3Administrator(l_administratorParams);
            WEB3AdministratorForMock.mockGetInstanceFromLoginInfo(l_administrator);
            l_intercepter.onCall(null, null);
            WEB3GentradeTradingClendarContext l_context =
                (WEB3GentradeTradingClendarContext)ThreadLocalSystemAttributesRegistry.getAttribute(
                    WEB3GentradeTradingTimeManagement.TRADING_CAL_CONTEXT_PATH);
            assertEquals(l_context.getInstitutionCode(), l_administrator.getInstitutionCode());
            assertEquals(l_context.getBranchCode(), l_administrator.getBranchCode());
            assertNull(l_context.getMarketCode());
            assertEquals("10", l_context.getTradingTimeType());
            assertEquals("04", l_context.getOrderAcceptProduct());
            assertEquals("0", l_context.getProductCode());
            assertNull(l_context.getOrderAcceptTransaction());
        }
        catch (Exception l_ex)
        {
            log.error(l_ex.getMessage(), l_ex);
            fail();
        }
        log.exiting(STR_METHOD_NAME);
    }

}
@
