head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.04.07.00.41.41;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	5444d9d078c63f4;
filename	WEB3ToStopIfoOrderUpdateInterceptorTest.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : (WEB3ToStopIfoOrderUpdateInterceptorTest.java)
Author Name      : Daiwa Institute of Research
Revision History : 2008/08/01 安陽(中訊) 新規作成
*/
package webbroker3.triggerorder;

import com.fitechlabs.xtrade.plugin.tc.xbifo.data.IfoOrderUnitParams;

import webbroker3.common.WEB3BaseRuntimeException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.mock.TestBaseForMock;
import webbroker3.util.WEB3LogUtility;

public class WEB3ToStopIfoOrderUpdateInterceptorTest extends TestBaseForMock
{
    /**
     * ログユーティリティ<BR>
     */
    private static WEB3LogUtility log =
         WEB3LogUtility.getInstance(WEB3ToStopIfoOrderUpdateInterceptorTest.class);
    
    public WEB3ToStopIfoOrderUpdateInterceptorTest(String arg0)
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

    /*
     * Test method for 'webbroker3.triggerorder.WEB3ToStopIfoOrderUpdateInterceptor.mutate(OrderManagerPersistenceType, OrderManagerPersistenceContext, IfoOrderUnitParams)'
     */
    public void testMutate_C0001()
    {
        final String STR_METHOD_NAME = "testMutate_C0001()";
        log.entering(TEST_START + STR_METHOD_NAME);
        
        try
        {
            WEB3ToStopIfoOrderUpdateInterceptor l_interceptor =
                new WEB3ToStopIfoOrderUpdateInterceptor("333");
            IfoOrderUnitParams l_inputParams = null;
            
            l_interceptor.mutate(null, null, l_inputParams);
            
            fail();
        }
        catch(WEB3BaseRuntimeException l_ex)
        {
            log.debug(l_ex.getMessage(), l_ex);
            assertEquals(WEB3ErrorCatalog.SYSTEM_ERROR_80017, l_ex.getErrorInfo());
        }
        catch(Exception l_ex)
        {
            log.error(l_ex.getMessage(), l_ex);
            log.exiting(TEST_END + STR_METHOD_NAME);
            fail();
        }
        
        log.exiting(TEST_END + STR_METHOD_NAME);
    }

    public void testMutate_C0002()
    {
        final String STR_METHOD_NAME = "testMutate_C0002()";
        log.entering(TEST_START + STR_METHOD_NAME);
        
        try
        {
            WEB3ToStopIfoOrderUpdateInterceptor l_interceptor =
                new WEB3ToStopIfoOrderUpdateInterceptor("555");
            IfoOrderUnitParams l_inputParams = new IfoOrderUnitParams();
            l_inputParams.setRequestType("0");
            l_inputParams.setErrorReasonCode("0");
            
            IfoOrderUnitParams l_outputParams =
                l_interceptor.mutate(null, null, l_inputParams);
            
            assertEquals("8", l_outputParams.getRequestType());
            assertEquals("555", l_outputParams.getErrorReasonCode());
        }
        catch(Exception l_ex)
        {
            log.error(l_ex.getMessage(), l_ex);
            log.exiting(TEST_END + STR_METHOD_NAME);
            fail();
        }
        
        log.exiting(TEST_END + STR_METHOD_NAME);
    }
}
@
