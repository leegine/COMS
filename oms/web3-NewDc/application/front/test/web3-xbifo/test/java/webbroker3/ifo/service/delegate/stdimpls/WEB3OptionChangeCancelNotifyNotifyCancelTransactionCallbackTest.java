head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.04.07.00.45.17;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	5444d9d078c63f4;
filename	WEB3OptionChangeCancelNotifyNotifyCancelTransactionCallbackTest.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
 Copyright        : (株)大和総研ビジネス・イノベーション
 File Name        : WEB3OptionChangeCancelNotifyNotifyCancelTransactionCallbackテスト
 Author Name      : Daiwa Institute of Research Business Innovation
 Revesion History : 2007/04/26 金傑 (中訊) 新規作成 仕様変更モデルNo.637
 Revesion History : 2010/07/16 趙天月(中訊) 大証次期デリバティブシステム対応
 */
package webbroker3.ifo.service.delegate.stdimpls;

import com.fitechlabs.xtrade.kernel.data.DataCallbackException;
import com.fitechlabs.xtrade.plugin.tc.gentrade.OrderUnit;
import com.fitechlabs.xtrade.plugin.tc.gentrade.ProductTypeEnum;
import com.fitechlabs.xtrade.plugin.tc.gentrade.data.BranchParams;
import com.fitechlabs.xtrade.plugin.tc.gentrade.data.InstitutionParams;
import com.fitechlabs.xtrade.plugin.tc.xbifo.data.IfoOrderUnitParams;
import com.fitechlabs.xtrade.plugin.tc.xbifo.stdimpls.IfoContractOpenOrderUnitImpl;

import test.util.TestDBUtility;

import webbroker3.common.WEB3BaseException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.ifo.WEB3IfoChangeCancelNotifyUpdateInterceptor;
import webbroker3.ifo.data.HostFotypeOrderClmdNotifyParams;
import webbroker3.mock.TestBaseForMock;
import webbroker3.mock.util.WEB3MockObjectParamsValue;
import webbroker3.util.WEB3LogUtility;

/**
 * （OP訂正取消通知取消通知一件TransactionCallbackテスト）<BR>
 * 
 * @@author 金傑
 * @@version 1.0
 */
public class WEB3OptionChangeCancelNotifyNotifyCancelTransactionCallbackTest extends TestBaseForMock
{
    private WEB3OptionChangeCancelNotifyNotifyCancelTransactionCallback l_callBack = null;

    /**
     * ログユーティリティ<BR>
     */
    private static WEB3LogUtility log = WEB3LogUtility
            .getInstance(WEB3OptionChangeCancelNotifyNotifyCancelTransactionCallbackTest.class);

    public WEB3OptionChangeCancelNotifyNotifyCancelTransactionCallbackTest(String name)
    {
        super(name);
    }

    protected void setUp() throws Exception
    {
        super.setUp();
        MOCK_MANAGER.setIsMockUsed(true);
        WEB3IfoChangeCancelNotifyUpdateInterceptor l_interceptor = new WEB3IfoChangeCancelNotifyUpdateInterceptor();
        this.l_callBack = new WEB3OptionChangeCancelNotifyNotifyCancelTransactionCallback(l_interceptor, TestDBUtility
                .getHostFotypeOrderClmdNotifyRow());
        this.getData();
        this.getMockData();

    }

    protected void tearDown() throws Exception
    {
        super.tearDown();
        this.l_callBack = null;
    }

    /**
     * 
     * lock口座抛出異常
     */
    public void testProcess_C0001()
    {
        final String STR_METHOD_NAME = "testProcess_C0001()";
        log.entering(TEST_START + STR_METHOD_NAME);
        try
        {
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue("webbroker3.gentrade.WEB3GentradeAccountManager",
                    "lockAccount", new Class[] { String.class, String.class, String.class }, new WEB3BaseException(
                            WEB3ErrorCatalog.SYSTEM_ERROR_80005, this.getClass().getName() + STR_METHOD_NAME));

            this.l_callBack.process();
            fail();
        }
        catch (Exception l_ex)
        {
            assertEquals(DataCallbackException.class, l_ex.getClass());
            WEB3MockObjectParamsValue l_paramsValue = TestBaseForMock.MOCK_MANAGER.getMethodParamsValue(
                    "webbroker3.gentrade.WEB3GentradeAccountManager", "lockAccount", new Class[] { String.class,
                            String.class, String.class });
            assertEquals("10", l_paramsValue.getFirstCalled()[0]);
            assertEquals("100", l_paramsValue.getFirstCalled()[1]);
            assertEquals("1010050", l_paramsValue.getFirstCalled()[2]);
        }
        log.exiting(TEST_END + STR_METHOD_NAME);
    }

    /**
     * get注文単位
     * 
     */
//    public void testProcess_C0002()
//    {
//        final String STR_METHOD_NAME = "testProcess_C0002()";
//        log.entering(TEST_START + STR_METHOD_NAME);
//        try
//        {
//            this.l_callBack.process();
//            fail();
//        }
//        catch (Exception ex)
//        {
//            assertEquals(DataCallbackException.class, ex.getClass());
//            WEB3MockObjectParamsValue l_paramsValue = TestBaseForMock.MOCK_MANAGER.getMethodParamsValue(
//                    "webbroker3.ifo.service.delegate.stdimpls.WEB3OptionChangeCancelNotifyUnitServiceImpl", 
//                    "notifyCancel",
//                    new Class[] {OrderUnit.class, WEB3IfoChangeCancelNotifyUpdateInterceptor.class});
//            assertEquals(IfoContractOpenOrderUnitImpl.class, l_paramsValue.getFirstCalled()[0].getClass());
//        }
//        log.exiting(TEST_END + STR_METHOD_NAME);
//    }

    private void getData()
    {
        // IfoContractOpenOrderUnitImpl
        try
        {
            IfoOrderUnitParams l_ifoOrderUnitParams = TestDBUtility.getIfoOrderUnitRow();
            l_ifoOrderUnitParams.setConfirmedQuantity(5000);
            TestDBUtility.insertWithDel(l_ifoOrderUnitParams);
        }
        catch (Exception l_ex)
        {
            fail();
        }

    }

    private void getMockData()
    {
        try
        {
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue("webbroker3.gentrade.WEB3GentradeAccountManager",
                    "lockAccount", new Class[] { String.class, String.class, String.class }, null);

            IfoContractOpenOrderUnitImpl l_ifoOrder = new IfoContractOpenOrderUnitImpl(1001);

            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue("webbroker3.ifo.WEB3OptionOrderManagerImpl",
                    "getOrderUnit", new Class[] { String.class, String.class, ProductTypeEnum.class, String.class },
                    l_ifoOrder);

            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                    "webbroker3.ifo.service.delegate.stdimpls.WEB3OptionChangeCancelNotifyUnitServiceImpl",
                    "notifyCancel", new Class[] { OrderUnit.class, WEB3IfoChangeCancelNotifyUpdateInterceptor.class },
                    new WEB3BaseException(WEB3ErrorCatalog.SYSTEM_ERROR_80005, this.getClass().getName()));
        }
        catch (Exception l_ex)
        {
            fail();
        }
    }
    
    // 大証次期デリバティブシステム対応
    public void testProcess_C0003()
    {
        final String STR_METHOD_NAME = "testProcess_C0003()";
        log.entering(TEST_START + STR_METHOD_NAME);
        try
        {
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                    "webbroker3.gentrade.WEB3GentradeAccountManager", "lockAccount", new Class[] {String.class, String.class, String.class},
                    null);
            
            TestDBUtility.deleteAll(IfoOrderUnitParams.TYPE);
            IfoOrderUnitParams l_ifoOrderUnitParams = TestDBUtility.getIfoOrderUnitRow();
            l_ifoOrderUnitParams.setAcceptNumber("12345678910111213141");
            l_ifoOrderUnitParams.setProductCode("1006160060");
            l_ifoOrderUnitParams.setBuySellDiv("2");
            TestDBUtility.insertWithDel(l_ifoOrderUnitParams);
            
            TestDBUtility.deleteAll(InstitutionParams.TYPE);
            InstitutionParams l_institutionParams = TestDBUtility.getInstitutionRow();
            TestDBUtility.insertWithDel(l_institutionParams);
            
            TestDBUtility.deleteAll(BranchParams.TYPE);
            BranchParams l_branchParams = TestDBUtility.getBranchRow();
            TestDBUtility.insertWithDel(l_branchParams);
            
            TestDBUtility.deleteAll(HostFotypeOrderClmdNotifyParams.TYPE);
            HostFotypeOrderClmdNotifyParams l_hostFotypeOrderClmdNotifyParams =
                TestDBUtility.getHostFotypeOrderClmdNotifyRow();
            l_hostFotypeOrderClmdNotifyParams.setInstitutionCode("0D");
            l_hostFotypeOrderClmdNotifyParams.setBranchCode("381");
            l_hostFotypeOrderClmdNotifyParams.setAcceptNumber("12345678910111213141");
            l_hostFotypeOrderClmdNotifyParams.setProductCode("1006160060");
            l_hostFotypeOrderClmdNotifyParams.setBuySellDiv("1");
            TestDBUtility.insertWithDel(l_hostFotypeOrderClmdNotifyParams);
            
            l_callBack = new WEB3OptionChangeCancelNotifyNotifyCancelTransactionCallback(
                null, l_hostFotypeOrderClmdNotifyParams);
            l_callBack.process();
            
            fail();
        }
        catch (DataCallbackException l_ex)
        {
            assertEquals("テーブルに該当するデータがありません。", l_ex.getMessage());
        }
        catch (Exception l_ex)
        {
            log.error(STR_METHOD_NAME, l_ex);
            fail();
        }
        finally
        {
            try
            {
                TestDBUtility.deleteAll(IfoOrderUnitParams.TYPE);
                TestDBUtility.deleteAll(InstitutionParams.TYPE);
                TestDBUtility.deleteAll(BranchParams.TYPE);
                TestDBUtility.deleteAll(HostFotypeOrderClmdNotifyParams.TYPE);
            }
            catch(Exception l_ex)
            {
                log.error("***delete fail***");
                fail();
            }
            
            log.exiting(TEST_END + STR_METHOD_NAME);
        }
        
    }

}
@
