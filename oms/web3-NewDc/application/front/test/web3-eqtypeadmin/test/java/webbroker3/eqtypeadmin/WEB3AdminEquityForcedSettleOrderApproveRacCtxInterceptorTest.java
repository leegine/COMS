head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.04.07.02.27.29;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	5444d9d078c63f4;
filename	WEB3AdminEquityForcedSettleOrderApproveRacCtxInterceptorTest.java;


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

import com.fitechlabs.xtrade.kernel.boot.Service;
import com.fitechlabs.xtrade.kernel.boot.Services;
import com.fitechlabs.xtrade.kernel.util.ThreadLocalSystemAttributesRegistry;
import com.fitechlabs.xtrade.plugin.util.rac.ContextDrivenMultiPoolDataSource;

import webbroker3.eqtypeadmin.data.AdminEqForcedSettleOrderParams;
import webbroker3.mock.TestBaseForMock;
import webbroker3.system.tune.affinity.WEB3DescendRacCtxService;
import webbroker3.system.tune.affinity.WEB3DescendRacCtxServiceImpl;
import webbroker3.util.WEB3LogUtility;

public class WEB3AdminEquityForcedSettleOrderApproveRacCtxInterceptorTest extends TestBaseForMock
{

    /**
     * Log Variable.<BR>
     */
    private static WEB3LogUtility log =
        WEB3LogUtility.getInstance(WEB3AdminEquityForcedSettleOrderApproveRacCtxInterceptorTest.class);
    
    public WEB3AdminEquityForcedSettleOrderApproveRacCtxInterceptorTest(String name)
    {
        super(name);
    }

    protected void setUp() throws Exception
    {
        super.setUp();
        Services.unregisterService(WEB3DescendRacCtxService.class);
        Services.registerService(
            WEB3DescendRacCtxService.class,
            new WEB3DescendRacCtxServiceImpl());
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
        String STR_METHOD_NAME = "test_onCall_0001()";
        log.entering(STR_METHOD_NAME);
        
        WEB3AdminEquityForcedSettleOrderApproveRacCtxInterceptor l_racCtxInterceptor =
            new WEB3AdminEquityForcedSettleOrderApproveRacCtxInterceptor();
        AdminEqForcedSettleOrderParams l_adminEqForcedSettleOrderParams = new AdminEqForcedSettleOrderParams();
        
        l_adminEqForcedSettleOrderParams.setAccountId(56);
        
        
        Object[] l_serviceParams = {l_adminEqForcedSettleOrderParams};
        
        l_racCtxInterceptor.onCall(null, l_serviceParams);
        
        Long l_accountId = (Long)ThreadLocalSystemAttributesRegistry.getAttribute("MPDS_KEY_VALUE");
        
        assertEquals("56", l_accountId.longValue() + "");
        log.exiting(STR_METHOD_NAME);
    }
    
    /**
     * onCall
     */
    public void test_onCall_0002()
    {
        String STR_METHOD_NAME = "test_onCall_0002()";
        log.entering(STR_METHOD_NAME);
        
        WEB3AdminEquityForcedSettleOrderApproveRacCtxInterceptor l_racCtxInterceptor =
            new WEB3AdminEquityForcedSettleOrderApproveRacCtxInterceptor();
        
        Object[] l_serviceParams = {null};
        
        l_racCtxInterceptor.onCall(null, l_serviceParams);
        
        Long l_accountId = (Long)ThreadLocalSystemAttributesRegistry.getAttribute("MPDS_KEY_VALUE");
        
        assertNull(l_accountId);
        log.exiting(STR_METHOD_NAME);
    }
    
    /**
     * onReturn
     */
    public void test_onReturn_0001()
    {
        String STR_METHOD_NAME = "test_onReturn_0001()";
        log.entering(STR_METHOD_NAME);
        
        WEB3DescendRacCtxService l_descendRacCtxService =
            (WEB3DescendRacCtxService)Services.getService(WEB3DescendRacCtxService.class);
        l_descendRacCtxService.setAccountIdCtx(45);
        
        WEB3AdminEquityForcedSettleOrderApproveRacCtxInterceptor l_racCtxInterceptor =
            new WEB3AdminEquityForcedSettleOrderApproveRacCtxInterceptor();
        
        l_racCtxInterceptor.onReturn(null, null);
        
        Long l_accountId = (Long)ThreadLocalSystemAttributesRegistry.getAttribute("MPDS_KEY_VALUE");
        
        assertNull(l_accountId);
        log.exiting(STR_METHOD_NAME);
    }
    
    /**
     * onThrowable
     */
    public void test_onThrowable_0001()
    {
        String STR_METHOD_NAME = "test_onThrowable_0001()";
        log.entering(STR_METHOD_NAME);
        
        WEB3DescendRacCtxService l_descendRacCtxService =
            (WEB3DescendRacCtxService)Services.getService(WEB3DescendRacCtxService.class);
        l_descendRacCtxService.setAccountIdCtx(45);
        
        WEB3AdminEquityForcedSettleOrderApproveRacCtxInterceptor l_racCtxInterceptor =
            new WEB3AdminEquityForcedSettleOrderApproveRacCtxInterceptor();
        
        l_racCtxInterceptor.onThrowable(null, null);
        
        Long l_accountId = (Long)ThreadLocalSystemAttributesRegistry.getAttribute("MPDS_KEY_VALUE");
        
        assertNull(l_accountId);
        log.exiting(STR_METHOD_NAME);
    }
    
}
@
