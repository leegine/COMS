head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.04.07.01.40.18;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	5444d9d078c63f4;
filename	WEB3AccTransChangeRequestAcceptServiceImplTest.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : 振替請求受付サービスImplテスト（WEB3AccTransChangeRequestAcceptServiceImplTest.java）
Author Name      : Daiwa Institute of Research
Revision History : 2008/12/16 武波 (中訊) 新規作成
*/
package webbroker3.aio.service.delegate.stdimpls;

import com.fitechlabs.xtrade.kernel.data.DataCallbackException;
import com.fitechlabs.xtrade.kernel.data.DataNetworkException;
import com.fitechlabs.xtrade.kernel.data.DataQueryException;

import test.util.TestDBUtility;

import webbroker3.aio.data.HostTransferAcceptParams;
import webbroker3.aio.message.WEB3AccTransChangeRequestAcceptRequest;
import webbroker3.common.WEB3BaseRuntimeException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.common.define.WEB3HostRequestCodeDef;
import webbroker3.common.define.WEB3StatusDef;
import webbroker3.common.message.WEB3BackRequest;
import webbroker3.mock.TestBaseForMock;
import webbroker3.util.WEB3LogUtility;

/**
 * (振替請求受付サービスImplテスト)<BR>
 * 振替請求受付サービス実装クラス<BR>
 *
 * @@author 武波(中訊)
 * @@version 1.0
 */
public class WEB3AccTransChangeRequestAcceptServiceImplTest extends TestBaseForMock
{

    public WEB3AccTransChangeRequestAcceptServiceImplTest(String arg0)
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

    private static WEB3LogUtility log =
        WEB3LogUtility.getInstance(
            WEB3AccTransChangeRequestAcceptServiceImplTest.class);

    /**
     * testExecute_C0001<BR>
     * 驗證入口參數是否為空<BR>
     * l_request = null<BR>
     */
    public void testExecute_C0001()
    {
        final String STR_METHOD_NAME = "testExecute_C0001()";
        log.entering(STR_METHOD_NAME);

        try
        {
            WEB3AccTransChangeRequestAcceptServiceImpl l_impl =
                new WEB3AccTransChangeRequestAcceptServiceImpl();
            l_impl.execute(null);
            fail();
        }
        catch(WEB3BaseRuntimeException l_ex)
        {
            assertEquals(l_ex.getErrorInfo(), WEB3ErrorCatalog.SYSTEM_ERROR_80017);
        }
        catch(Exception l_ex)
        {
            log.error(STR_METHOD_NAME, l_ex);
            log.exiting(STR_METHOD_NAME);
            fail();
        }

        log.exiting(STR_METHOD_NAME);
    }

    /**
     * testExecute_C0002<BR>
     * 驗證入口參數是否為空<BR>
     * l_request != null<BR>
     */
    public void testExecute_C0002()
    {
        final String STR_METHOD_NAME = "testExecute_C0002()";
        log.entering(STR_METHOD_NAME);

        try
        {
            WEB3AccTransChangeRequestAcceptServiceImpl l_impl =
                new WEB3AccTransChangeRequestAcceptServiceImplTest1();
            WEB3BackRequest l_request = new WEB3AccTransChangeRequestAcceptRequest();
            l_impl.execute(l_request);
        }
        catch(Exception l_ex)
        {
            log.error(STR_METHOD_NAME, l_ex);
            log.exiting(STR_METHOD_NAME);
            fail();
        }

        log.exiting(STR_METHOD_NAME);
    }

    /**
     * testProcess_C0001<BR>
     * 取得した振替請求受付キューParamsレコード為空時<BR>
     */
    public void testProcess_C0001()
    {
        final String STR_METHOD_NAME = "testProcess_C0001()";
        log.entering(STR_METHOD_NAME);

        try
        {
            WEB3AccTransChangeRequestAcceptServiceImplTest2 l_impl =
                new WEB3AccTransChangeRequestAcceptServiceImplTest2();
            WEB3AccTransChangeRequestAcceptServiceImplTest2.WEB3AccTransChangeRequestAcceptTransactionCallback l_callback =
                l_impl.getCallback();
            TestDBUtility.deleteAll(HostTransferAcceptParams.TYPE);
            l_callback.process();
        }
        catch(Exception l_ex)
        {
            log.error(STR_METHOD_NAME, l_ex);
            log.exiting(STR_METHOD_NAME);
            fail();
        }

        log.exiting(STR_METHOD_NAME);
    }

    /**
     * testProcess_C0002<BR>
     * 取得した振替請求受付キューParamsレコード有一條紀?<BR>
     */
    public void testProcess_C0002()
    {
        final String STR_METHOD_NAME = "testProcess_C0002()";
        log.entering(STR_METHOD_NAME);

        try
        {
            WEB3AccTransChangeRequestAcceptServiceImplTest2 l_impl =
                new WEB3AccTransChangeRequestAcceptServiceImplTest2();
            WEB3AccTransChangeRequestAcceptServiceImplTest2.WEB3AccTransChangeRequestAcceptTransactionCallback l_callback =
                l_impl.getCallback();
            TestDBUtility.deleteAll(HostTransferAcceptParams.TYPE);
            HostTransferAcceptParams l_hostTransferAcceptParams =
                TestDBUtility.getHostTransferAcceptRow();
            l_hostTransferAcceptParams.setRequestCode(WEB3HostRequestCodeDef.AIO_TRANSFER_REQUEST_ACCEPT);
            l_hostTransferAcceptParams.setStatus(WEB3StatusDef.NOT_DEAL);
            TestDBUtility.insertWithDel(l_hostTransferAcceptParams);
            l_callback.process();
        }
        catch(Exception l_ex)
        {
            log.error(STR_METHOD_NAME, l_ex);
            log.exiting(STR_METHOD_NAME);
            fail();
        }

        log.exiting(STR_METHOD_NAME);
    }

    class WEB3AccTransChangeRequestAcceptServiceImplTest1 extends WEB3AccTransChangeRequestAcceptServiceImpl
    {
        public class WEB3AccTransChangeRequestAcceptTransactionCallbackTest
            extends WEB3AccTransChangeRequestAcceptTransactionCallback
        {
            public Object process()
                throws DataNetworkException, DataQueryException, DataCallbackException
            {
                return null;
            }
        }
    }

    class WEB3AccTransChangeRequestAcceptServiceImplTest2 extends WEB3AccTransChangeRequestAcceptServiceImpl
    {
        public class WEB3AccTransChangeRequestAcceptTransactionCallbackTest
            extends WEB3AccTransChangeRequestAcceptTransactionCallback
        {

        }

        public WEB3AccTransChangeRequestAcceptTransactionCallback getCallback()
        {
            return new WEB3AccTransChangeRequestAcceptTransactionCallback();
        }
    }
}
@
