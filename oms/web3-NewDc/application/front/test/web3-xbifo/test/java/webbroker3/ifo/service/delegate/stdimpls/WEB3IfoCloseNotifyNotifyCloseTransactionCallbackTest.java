head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.04.07.00.46.54;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	5444d9d078c63f4;
filename	WEB3IfoCloseNotifyNotifyCloseTransactionCallbackTest.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研ビジネス・イノベーション
File Name        : WEB3IfoCloseNotifyNotifyCloseTransactionCallbackTest.java
Author Name      : Daiwa Institute of Research Business Innovation
Revision History : 2010/07/20 趙天月(中訊) 大証次期デリバティブシステム対応 新規作成
*/
package webbroker3.ifo.service.delegate.stdimpls;

import com.fitechlabs.xtrade.kernel.data.DataCallbackException;
import com.fitechlabs.xtrade.plugin.tc.gentrade.OrderStatusEnum;
import com.fitechlabs.xtrade.plugin.tc.gentrade.OrderUnit;
import com.fitechlabs.xtrade.plugin.tc.gentrade.data.BranchParams;
import com.fitechlabs.xtrade.plugin.tc.gentrade.data.BranchRow;
import com.fitechlabs.xtrade.plugin.tc.gentrade.data.InstitutionParams;
import com.fitechlabs.xtrade.plugin.tc.gentrade.data.InstitutionRow;
import com.fitechlabs.xtrade.plugin.tc.xbifo.data.IfoOrderUnitParams;
import com.fitechlabs.xtrade.plugin.tc.xbifo.data.IfoOrderUnitRow;

import test.util.TestDBUtility;

import webbroker3.ifo.data.HostFotypeCloseOrderNotifyParams;
import webbroker3.ifo.data.HostFotypeCloseOrderNotifyRow;
import webbroker3.mock.TestBaseForMock;
import webbroker3.util.WEB3LogUtility;

public class WEB3IfoCloseNotifyNotifyCloseTransactionCallbackTest extends TestBaseForMock
{

    public WEB3IfoCloseNotifyNotifyCloseTransactionCallbackTest(String name)
    {
        super(name);
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
    
    private static WEB3LogUtility log = WEB3LogUtility.getInstance(WEB3IfoCloseNotifyNotifyCloseTransactionCallbackTest.class);

    private WEB3IfoCloseNotifyNotifyCloseTransactionCallback l_closeNotifyCallback = null;
    
    public static HostFotypeCloseOrderNotifyParams getHostFotypeCloseOrderNotifyRow()
    {
        HostFotypeCloseOrderNotifyParams l_fotypeCloseOrderNotify = new HostFotypeCloseOrderNotifyParams();
        
        l_fotypeCloseOrderNotify.setRequestCode("EI813");
        l_fotypeCloseOrderNotify.setStatus("0");
        l_fotypeCloseOrderNotify.setInstitutionCode("0D");
        l_fotypeCloseOrderNotify.setBranchCode("381");
        l_fotypeCloseOrderNotify.setAccountCode("2512246");
        l_fotypeCloseOrderNotify.setAcceptNumber("12345678901234567890");
        l_fotypeCloseOrderNotify.setProductCode("1234567890");
        l_fotypeCloseOrderNotify.setBuySellDiv("1");
        l_fotypeCloseOrderNotify.setOrderRequestNumber("11");
        l_fotypeCloseOrderNotify.setCloseNotifyType("1");
        l_fotypeCloseOrderNotify.setReasonCode("1");
        
        return l_fotypeCloseOrderNotify;
    }
    
    public void test_process_0001()
    {
        final String STR_METHOD_NAME = "test_process_0001()";
        log.entering(TEST_START + STR_METHOD_NAME);
        try
        {
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                    "webbroker3.gentrade.WEB3GentradeAccountManager",
                    "lockAccount",
                    new Class[] {String.class,String.class,String.class},
                    null);
            
            TestDBUtility.deleteAllAndCommit(HostFotypeCloseOrderNotifyRow.TYPE);
            HostFotypeCloseOrderNotifyParams l_fotypeCloseOrderNotifyParams = getHostFotypeCloseOrderNotifyRow();
            TestDBUtility.insertWithDelAndCommit(l_fotypeCloseOrderNotifyParams);
            
            TestDBUtility.deleteAllAndCommit(InstitutionRow.TYPE);
            InstitutionParams l_InstitutionParams = TestDBUtility.getInstitutionRow();
            TestDBUtility.insertWithDelAndCommit(l_InstitutionParams);
            
            TestDBUtility.deleteAllAndCommit(BranchRow.TYPE);
            BranchParams l_BranchParams = TestDBUtility.getBranchRow();
            TestDBUtility.insertWithDelAndCommit(l_BranchParams);
            
            TestDBUtility.deleteAllAndCommit(IfoOrderUnitRow.TYPE);
            IfoOrderUnitParams l_ifoOrderUnitParams = TestDBUtility.getIfoOrderUnitRow();
            l_ifoOrderUnitParams.setAcceptNumber("12345678901234567890");
            l_ifoOrderUnitParams.setProductCode("1234567890");
            l_ifoOrderUnitParams.setBuySellDiv("2");
            l_ifoOrderUnitParams.setConfirmedQuantity(500.0);
            l_ifoOrderUnitParams.setOrderStatus(OrderStatusEnum.UNDEFINED);
            TestDBUtility.insertWithDelAndCommit(l_ifoOrderUnitParams);
            
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                    "webbroker3.ifo.service.delegate.stdimpls.WEB3IfoCloseNotifyUnitServiceImpl",
                    "notifyClose",
                    new Class[] {OrderUnit.class, double.class, String.class, String.class},
                    "1");
            
            l_closeNotifyCallback = new WEB3IfoCloseNotifyNotifyCloseTransactionCallback(
                null, 500.0, "1", "1", l_fotypeCloseOrderNotifyParams);
            
            l_closeNotifyCallback.process();
            
            fail();            
        }
        catch(DataCallbackException l_ex)
        {
            log.debug(STR_METHOD_NAME, l_ex);
            assertEquals("テーブルに該当するデータがありません。", l_ex.getMessage());
        }
        catch(Exception l_ex)
        {
            log.error(STR_METHOD_NAME, l_ex);
            fail();
        }
        finally
        {
            try
            {
                TestDBUtility.deleteAll(HostFotypeCloseOrderNotifyRow.TYPE);
                TestDBUtility.deleteAll(InstitutionRow.TYPE);
                TestDBUtility.deleteAll(BranchRow.TYPE);
                TestDBUtility.deleteAll(IfoOrderUnitRow.TYPE);
            }
            catch(Exception l_ex)
            {
                log.error(STR_METHOD_NAME, l_ex);
                fail();
            }
            
            log.exiting(TEST_END + STR_METHOD_NAME);
        }
    }
}
@
