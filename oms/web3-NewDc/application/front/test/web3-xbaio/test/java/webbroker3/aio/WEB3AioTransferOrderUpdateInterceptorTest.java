head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.04.07.01.34.32;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	5444d9d078c63f4;
filename	WEB3AioTransferOrderUpdateInterceptorTest.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (äî)ëÂòaëçå§ èÿåîÉ\ÉäÉÖÅ[ÉVÉáÉìÉVÉXÉeÉÄëÊìÒïî
File Name        : (WEB3AioTransferOrderUpdateInterceptorTest.java)
Author Name      : Daiwa Institute of Research
Revision History : 2007/08/03 í£ì´âF (íÜêu) êVãKçÏê¨
*/
package webbroker3.aio;

import com.fitechlabs.xtrade.plugin.tc.gentrade.OrderManagerPersistenceContext;
import com.fitechlabs.xtrade.plugin.tc.gentrade.OrderManagerPersistenceType;
import com.fitechlabs.xtrade.plugin.tc.gentrade.OrderTypeEnum;
import com.fitechlabs.xtrade.plugin.tc.gentrade.data.MainAccountParams;
import com.fitechlabs.xtrade.plugin.tc.gentrade.data.MainAccountRow;
import com.fitechlabs.xtrade.plugin.tc.xbaio.data.AioOrderUnitParams;

import test.util.TestDBUtility;

import webbroker3.mock.TestBaseForMock;
import webbroker3.util.WEB3DateUtility;
import webbroker3.util.WEB3LogUtility;

public class WEB3AioTransferOrderUpdateInterceptorTest extends TestBaseForMock
{
    private static WEB3LogUtility log =
        WEB3LogUtility.getInstance(WEB3AioTransferOrderUpdateInterceptorTest.class);
    public WEB3AioTransferOrderUpdateInterceptorTest(String arg0)
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
     * Test method for 'webbroker3.aio.WEB3AioTransferOrderUpdateInterceptor.mutate(OrderManagerPersistenceType, OrderManagerPersistenceContext, AioOrderUnitParams)'
     */
    public void testMutateCase1()
    {
        final String STR_METHOD_NAME = "testMutateCase1()";
        log.entering(STR_METHOD_NAME);

        MainAccountParams l_mainAccountParams = TestDBUtility.getMainAccountRow();
        l_mainAccountParams.setAccountId(333812512246L);

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
        }
        catch (Exception l_ex)
        {
            log.error(l_ex.getMessage(), l_ex);
            fail();
        }
        try
        {
            WEB3AioNewOrderSpec cashTransOrderSpec = null;
            OrderManagerPersistenceType l_updateType = null;
            OrderManagerPersistenceContext l_dealing = null;
            AioOrderUnitParams l_orderUnitParams = TestDBUtility.getAioOrderUnitRow();
            l_orderUnitParams.setAccountId(333812512246L);
            l_orderUnitParams.setOrderType(OrderTypeEnum.FROM_DEPOSIT_AMOUNT_MARGIN_GUARANTEE);
            WEB3AioTransferOrderUpdateInterceptor l_interceptor =
                new WEB3AioTransferOrderUpdateInterceptor(cashTransOrderSpec);

            l_interceptor.setBizDate(WEB3DateUtility.getDate("20040716","yyyyMMdd"));

            l_interceptor.setDeliveryDate(WEB3DateUtility.getDate("20040816","yyyyMMdd"));

            AioOrderUnitParams l_orderUnitParams1 =
                l_interceptor.mutate(l_updateType, l_dealing, l_orderUnitParams);

            //íçï∂åoòHãÊï™
            assertEquals("3", l_orderUnitParams1.getOrderRootDiv());
            //É~ÉjäîãÊï™
            assertEquals("0", l_orderUnitParams1.getMiniStockDiv());
            //í â›ÉRÅ[Éh
            assertNull(l_orderUnitParams1.getCurrencyCode());
            //ì¸èoã‡ã‡äz(â~ä∑éZäz)
            assertTrue(l_orderUnitParams1.getConvertAmountIsNull());
            assertEquals("20040716", l_orderUnitParams1.getBizDate());
            assertEquals("20040816", WEB3DateUtility.formatDate(l_orderUnitParams1.getDeliveryDate(), "yyyyMMdd"));
            assertEquals(13, l_orderUnitParams1.getOrderCateg().intValue());
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
        try
        {
            TestDBUtility.deleteAll(MainAccountRow.TYPE);
            TestDBUtility.insertWithDel(l_mainAccountParams);
        }
        catch (Exception l_ex)
        {
            log.error(l_ex.getMessage(), l_ex);
            fail();
        }
        try
        {
            WEB3AioNewOrderSpec cashTransOrderSpec = null;
            OrderManagerPersistenceType l_updateType = null;
            OrderManagerPersistenceContext l_dealing = null;
            AioOrderUnitParams l_orderUnitParams = TestDBUtility.getAioOrderUnitRow();
            l_orderUnitParams.setAccountId(333812512246L);
            l_orderUnitParams.setOrderType(OrderTypeEnum.FROM_DEPOSIT_AMOUNT_MARGIN_GUARANTEE);
            WEB3AioTransferOrderUpdateInterceptor l_interceptor =
                new WEB3AioTransferOrderUpdateInterceptor(cashTransOrderSpec);
            
            AioOrderUnitParams l_orderUnitParams1 =
                l_interceptor.mutate(l_updateType, l_dealing, l_orderUnitParams);
            
            //íçï∂åoòHãÊï™
            assertEquals("2", l_orderUnitParams1.getOrderRootDiv());
            //É~ÉjäîãÊï™
            assertEquals("0", l_orderUnitParams1.getMiniStockDiv());
            //í â›ÉRÅ[Éh
            assertNull(l_orderUnitParams1.getCurrencyCode());
            //ì¸èoã‡ã‡äz(â~ä∑éZäz)
            assertTrue(l_orderUnitParams1.getConvertAmountIsNull());
        }
        catch (Exception l_ex)
        {
            log.error(l_ex.getMessage(), l_ex);
            fail();
        }
        log.exiting(STR_METHOD_NAME);
    }
    
    public void testMutateCase3()
    {
        final String STR_METHOD_NAME = "testMutateCase3()";
        log.entering(STR_METHOD_NAME);
        MOCK_MANAGER.setIsMockUsed(true);
        TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                "com.fitechlabs.xtrade.plugin.security.oplogin.impl.OpLoginSecurityServiceImpl",
                "getSessionProperty",
                new Class[] {String.class},
                "3");
        MainAccountParams l_mainAccountParams = TestDBUtility.getMainAccountRow();
        l_mainAccountParams.setAccountId(333812512246L);
        try
        {
            TestDBUtility.deleteAll(MainAccountRow.TYPE);
            TestDBUtility.insertWithDel(l_mainAccountParams);
        }
        catch (Exception l_ex)
        {
            log.error(l_ex.getMessage(), l_ex);
            fail();
        }
        try
        {
            WEB3AioNewOrderSpec cashTransOrderSpec = null;
            OrderManagerPersistenceType l_updateType = null;
            OrderManagerPersistenceContext l_dealing = null;
            AioOrderUnitParams l_orderUnitParams = TestDBUtility.getAioOrderUnitRow();
            l_orderUnitParams.setAccountId(333812512246L);
            WEB3AioTransferOrderUpdateInterceptor l_interceptor =
                new WEB3AioTransferOrderUpdateInterceptor(cashTransOrderSpec);
            
            AioOrderUnitParams l_orderUnitParams1 =
                l_interceptor.mutate(l_updateType, l_dealing, l_orderUnitParams);
            
            //íçï∂åoòHãÊï™
            assertEquals("3", l_orderUnitParams1.getOrderRootDiv());
            //É~ÉjäîãÊï™
            assertEquals("0", l_orderUnitParams1.getMiniStockDiv());
            //í â›ÉRÅ[Éh
            assertNull(l_orderUnitParams1.getCurrencyCode());
            //ì¸èoã‡ã‡äz(â~ä∑éZäz)
            assertTrue(l_orderUnitParams1.getConvertAmountIsNull());
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
