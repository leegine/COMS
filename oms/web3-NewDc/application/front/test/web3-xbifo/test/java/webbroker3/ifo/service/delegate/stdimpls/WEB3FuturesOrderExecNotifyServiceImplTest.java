head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.04.07.00.46.41;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	5444d9d078c63f4;
filename	WEB3FuturesOrderExecNotifyServiceImplTest.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : WEB3ToSuccIfoOrderUnitServiceInterceptorTest.java
Author Name      : Daiwa Institute of Research
Revision History : 2008/07/15 劉剣（中訊）新規作成
*/
package webbroker3.ifo.service.delegate.stdimpls;

import webbroker3.common.WEB3BaseException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.common.message.WEB3BackRequest;
import webbroker3.common.message.WEB3BackResponse;
import webbroker3.ifo.message.WEB3FuturesOrderExecNotifyRequest;
import webbroker3.mock.TestBaseForMock;
import webbroker3.util.WEB3LogUtility;

public class WEB3FuturesOrderExecNotifyServiceImplTest extends TestBaseForMock
{
    /**
     * ログ出力ユーティリティ。
     */
    private static WEB3LogUtility log =
        WEB3LogUtility.getInstance(WEB3FuturesOrderExecNotifyServiceImplTest.class);
    
    private WEB3FuturesOrderExecNotifyServiceImpl l_impl = null;
    private WEB3BackRequest l_request = null;

    public WEB3FuturesOrderExecNotifyServiceImplTest(String arg0)
    {
        super(arg0);
        // TODO Auto-generated constructor stub
    }

    protected void setUp() throws Exception
    {
        super.setUp();
        MOCK_MANAGER.setIsMockUsed(true);
        this.l_impl = new WEB3FuturesOrderExecNotifyServiceImpl();
    }

    protected void tearDown() throws Exception
    {
        super.tearDown();
    }
    /*
     * リクエストデータ = null
     * 抛出：WEB3ErrorCatalog.SYSTEM_ERROR_80017
     */
    public void testExecute_C0001()
    {
        final String STR_METHOD_NAME = "testExecute_C0001()";
        log.entering(TEST_START + STR_METHOD_NAME);

        try
        {
            this.l_impl.execute(l_request);
        }
        catch (WEB3BaseException l_ex)
        {
            log.debug(STR_METHOD_NAME, l_ex);
            assertEquals(WEB3ErrorCatalog.SYSTEM_ERROR_80017,l_ex.getErrorInfo());
        }
        catch (Exception l_ex)
        {
            log.error(STR_METHOD_NAME, l_ex);
            fail();
        }

        log.exiting(TEST_END + STR_METHOD_NAME);
    }
    /*
     * normal case
     */
    public void testExecute_C0002()
    {
        final String STR_METHOD_NAME = "testExecute_C0002()";
        log.entering(TEST_START + STR_METHOD_NAME);

        WEB3BackResponse l_response = null;
        try
        {
            WEB3FuturesOrderExecNotifyRequest l_orderExecNotifyRequest = new WEB3FuturesOrderExecNotifyRequest();
            l_orderExecNotifyRequest.threadNo = new Long(100);
            l_response = l_impl.execute(l_orderExecNotifyRequest);
            assertNotNull(l_response);
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
