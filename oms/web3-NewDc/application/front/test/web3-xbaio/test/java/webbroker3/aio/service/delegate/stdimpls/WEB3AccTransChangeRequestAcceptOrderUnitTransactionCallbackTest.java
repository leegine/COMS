head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.04.07.01.36.38;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	5444d9d078c63f4;
filename	WEB3AccTransChangeRequestAcceptOrderUnitTransactionCallbackTest.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : 振替請求受付正常処理一件TransactionCallbackテスト（WEB3AccTransChangeRequestAcceptOrderUnitTransactionCallbackTest.java）
Author Name      : Daiwa Institute of Research
Revision History : 2008/12/09 武波 (中訊) 新規作成
*/
package webbroker3.aio.service.delegate.stdimpls;

import com.fitechlabs.xtrade.plugin.tc.xbaio.AioOrderUnit;

import test.util.TestDBUtility;

import webbroker3.aio.data.HostTransferAcceptParams;
import webbroker3.aio.data.HostTransferAcceptRow;
import webbroker3.mock.TestBaseForMock;
import webbroker3.util.WEB3LogUtility;

/**
 * （振替請求受付正常処理一件TransactionCallbackテスト）。<BR>
 * <BR>
 * トランザクション処理を実施する内部クラス。<BR>
 * @@author 武波
 * @@version 1.0
 */
public class WEB3AccTransChangeRequestAcceptOrderUnitTransactionCallbackTest extends TestBaseForMock
{

    private static WEB3LogUtility log =
        WEB3LogUtility.getInstance(
            WEB3AccTransChangeRequestAcceptOrderUnitTransactionCallbackTest.class);
    public WEB3AccTransChangeRequestAcceptOrderUnitTransactionCallbackTest(String arg0)
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

    /**
     * testProcess_C0001
     * 振替請求受付キューRow.受付通知区分 = 1:振替受付完了<BR>
     * 振替完了処理に伴う注文データの更新<BR>
     */
    public void testProcess_C0001()
    {
        final String STR_METHOD_NAME = "testProcess_C0001()";
        log.entering(STR_METHOD_NAME);

        try
        {
            MOCK_MANAGER.setIsMockUsed(true);
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                "webbroker3.aio.service.delegate.stdimpls.WEB3AccTransChangeAcceptUnitServiceImpl",
                "execute",
                new Class[] {AioOrderUnit.class, String.class, String.class},
                null);

            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                "webbroker3.aio.service.delegate.stdimpls.WEB3AccTransChangeCompleteUnitServiceImpl",
                "completeChange",
                new Class[] {AioOrderUnit.class},
                null);
            HostTransferAcceptRow l_hostTransferAcceptRow = TestDBUtility.getHostTransferAcceptRow();
            AioOrderUnit l_aioOrderUnit = null;
            WEB3AccTransChangeRequestAcceptOrderUnitTransactionCallback l_transactionCallback =
                new WEB3AccTransChangeRequestAcceptOrderUnitTransactionCallback(
                    l_hostTransferAcceptRow, l_aioOrderUnit);
            l_transactionCallback.process();
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
     * testProcess_C0002
     * 振替請求受付キューRow.受付通知区分 != 1:振替受付完了<BR>
     * 振替完了処理に伴う注文データの更新しない<BR>
     */
    public void testProcess_C0002()
    {
        final String STR_METHOD_NAME = "testProcess_C0002()";
        log.entering(STR_METHOD_NAME);

        try
        {
            MOCK_MANAGER.setIsMockUsed(true);
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                "webbroker3.aio.service.delegate.stdimpls.WEB3AccTransChangeAcceptUnitServiceImpl",
                "execute",
                new Class[] {AioOrderUnit.class, String.class, String.class},
                null);

            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                "webbroker3.aio.service.delegate.stdimpls.WEB3AccTransChangeCompleteUnitServiceImpl",
                "completeChange",
                new Class[] {AioOrderUnit.class},
                null);
            HostTransferAcceptParams l_hostTransferAcceptRow = TestDBUtility.getHostTransferAcceptRow();
            l_hostTransferAcceptRow.setAcceptDiv("2");
            AioOrderUnit l_aioOrderUnit = null;
            WEB3AccTransChangeRequestAcceptOrderUnitTransactionCallback l_transactionCallback =
                new WEB3AccTransChangeRequestAcceptOrderUnitTransactionCallback(
                    l_hostTransferAcceptRow, l_aioOrderUnit);
            l_transactionCallback.process();
        }
        catch(Exception l_ex)
        {
            log.error(STR_METHOD_NAME, l_ex);
            log.exiting(STR_METHOD_NAME);
            fail();
        }

        log.exiting(STR_METHOD_NAME);
    }
}
@
