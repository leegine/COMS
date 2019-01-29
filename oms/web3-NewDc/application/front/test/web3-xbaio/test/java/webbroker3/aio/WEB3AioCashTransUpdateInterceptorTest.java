head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.04.07.01.33.54;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	5444d9d078c63f4;
filename	WEB3AioCashTransUpdateInterceptorTest.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : (.java)
Author Name      : Daiwa Institute of Research
Revision History : 2007/04/24 張騰宇 (中訊) 新規作成 仕様変更モデル
*/
package webbroker3.aio;

import com.fitechlabs.xtrade.plugin.tc.gentrade.OrderManagerPersistenceContext;
import com.fitechlabs.xtrade.plugin.tc.gentrade.OrderManagerPersistenceType;
import com.fitechlabs.xtrade.plugin.tc.gentrade.OrderTypeEnum;
import com.fitechlabs.xtrade.plugin.tc.gentrade.data.MainAccountParams;
import com.fitechlabs.xtrade.plugin.tc.gentrade.data.MainAccountRow;
import com.fitechlabs.xtrade.plugin.tc.xbaio.data.AioOrderActionParams;
import com.fitechlabs.xtrade.plugin.tc.xbaio.data.AioOrderUnitParams;
import com.fitechlabs.xtrade.plugin.tc.xbaio.data.AioOrderUnitRow;

import test.util.TestDBUtility;

import webbroker3.mock.TestBaseForMock;
import webbroker3.util.WEB3LogUtility;

public class WEB3AioCashTransUpdateInterceptorTest extends TestBaseForMock
{
    private static WEB3LogUtility log =
        WEB3LogUtility.getInstance(WEB3AioCashTransUpdateInterceptorTest.class);
    public WEB3AioCashTransUpdateInterceptorTest(String arg0)
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

    /*
     * Test method for 'webbroker3.aio.WEB3AioCashTransUpdateInterceptor.mutate(OrderManagerPersistenceType, OrderManagerPersistenceContext, AioOrderUnitParams)'
     */
    public void testMutateCase1()
    {
        final String STR_METHOD_NAME = "testMutateCase1()";
        log.entering(STR_METHOD_NAME);

        MainAccountParams l_mainAccountParams = TestDBUtility.getMainAccountRow();
        l_mainAccountParams.setAccountId(333812512246L);

        AioOrderUnitParams l_orderUnitParams = TestDBUtility.getAioOrderUnitRow();
        l_orderUnitParams.setAccountId(333812512246L);
        l_orderUnitParams.setOrderType(OrderTypeEnum.FROM_DEPOSIT_AMOUNT_MARGIN_GUARANTEE);
        MOCK_MANAGER.setIsMockUsed(true);
        TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                "com.fitechlabs.xtrade.plugin.security.oplogin.impl.OpLoginSecurityServiceImpl",
                "getSessionProperty",
                new Class[] {String.class},
                "3");
        try
        {
            TestDBUtility.deleteAll(MainAccountRow.TYPE);
            TestDBUtility.insertWithDel(l_mainAccountParams);
            TestDBUtility.deleteAll(AioOrderUnitRow.TYPE);
            TestDBUtility.insertWithDel(l_orderUnitParams);
        }
        catch (Exception l_ex)
        {
            log.error(l_ex.getMessage(), l_ex);
            fail();
        }
        try
        {
            AioOrderActionParams l_orderActionParams = new AioOrderActionParams();
            l_orderActionParams.setOrderUnitId(2000011L);

            OrderManagerPersistenceType l_updateType = null;
            OrderManagerPersistenceContext l_dealing = null;

            WEB3AioCashTransUpdateInterceptor l_interceptor = new WEB3AioCashTransUpdateInterceptor();

            AioOrderActionParams l_orderActionParams1 =
                l_interceptor.mutate(l_updateType,l_dealing,l_orderActionParams);


//            //通貨コード
            assertTrue(l_orderActionParams1.getConvertAmountIsNull());

        }
        catch (Exception l_ex)
        {
            log.error(l_ex.getMessage(), l_ex);
            fail();
        }
        log.exiting(STR_METHOD_NAME);
    }

    public void testMutateCase2()
    {
        final String STR_METHOD_NAME = "testMutateCase2()";
        log.entering(STR_METHOD_NAME);

        MainAccountParams l_mainAccountParams = TestDBUtility.getMainAccountRow();
        l_mainAccountParams.setAccountId(333812512246L);

        AioOrderUnitParams l_orderUnitParams = TestDBUtility.getAioOrderUnitRow();
        l_orderUnitParams.setAccountId(333812512246L);
        l_orderUnitParams.setOrderType(OrderTypeEnum.FROM_DEPOSIT_AMOUNT_MARGIN_GUARANTEE);
        l_orderUnitParams.setConvertAmount(100);
        MOCK_MANAGER.setIsMockUsed(true);
        TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                "com.fitechlabs.xtrade.plugin.security.oplogin.impl.OpLoginSecurityServiceImpl",
                "getSessionProperty",
                new Class[] {String.class},
                "3");
        try
        {
            TestDBUtility.deleteAll(MainAccountRow.TYPE);
            TestDBUtility.insertWithDel(l_mainAccountParams);
            TestDBUtility.deleteAll(AioOrderUnitRow.TYPE);
            TestDBUtility.insertWithDel(l_orderUnitParams);
        }
        catch (Exception l_ex)
        {
            log.error(l_ex.getMessage(), l_ex);
            fail();
        }
        try
        {
            AioOrderActionParams l_orderActionParams = new AioOrderActionParams();
            l_orderActionParams.setOrderUnitId(2000011L);


            OrderManagerPersistenceType l_updateType = null;
            OrderManagerPersistenceContext l_dealing = null;

            WEB3AioCashTransUpdateInterceptor l_interceptor = new WEB3AioCashTransUpdateInterceptor();

            AioOrderActionParams l_orderActionParams1 =
                l_interceptor.mutate(l_updateType,l_dealing,l_orderActionParams);


//            //通貨コード
            assertEquals(100D, l_orderActionParams1.getConvertAmount(), 0);

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
