head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.04.07.00.59.26;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	5444d9d078c63f4;
filename	WEB3IfoChangeConfirmUpdateInterceptorTest.java;


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
Revision History : 2008/07/14 劉剣（中訊）新規作成
*/
package webbroker3.ifo;

import com.fitechlabs.xtrade.plugin.tc.gentrade.OrderManagerPersistenceContext;
import com.fitechlabs.xtrade.plugin.tc.gentrade.OrderManagerPersistenceType;
import com.fitechlabs.xtrade.plugin.tc.xbifo.data.IfoOrderParams;
import com.fitechlabs.xtrade.plugin.tc.xbifo.data.IfoOrderRow;
import com.fitechlabs.xtrade.plugin.tc.xbifo.data.IfoOrderUnitParams;
import com.fitechlabs.xtrade.plugin.tc.xbifo.data.IfoOrderUnitRow;

import test.util.TestDBUtility;

import webbroker3.common.WEB3BaseRuntimeException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.ifo.define.WEB3IfoOrderModifyCancelTypeDef;
import webbroker3.mock.TestBaseForMock;
import webbroker3.util.WEB3LogUtility;

public class WEB3IfoChangeConfirmUpdateInterceptorTest extends TestBaseForMock
{
    /**
     * ログ出力ユーティリティ。
     */
    private static WEB3LogUtility log =
        WEB3LogUtility.getInstance(WEB3IfoChangeConfirmUpdateInterceptorTest.class);
    
    private WEB3IfoChangeConfirmUpdateInterceptor l_interceptor = null;

    public WEB3IfoChangeConfirmUpdateInterceptorTest(String arg0)
    {
        super(arg0);
        // TODO Auto-generated constructor stub
    }

    protected void setUp() throws Exception
    {
        super.setUp();
        MOCK_MANAGER.setIsMockUsed(true);
        this.l_interceptor = new WEB3IfoChangeConfirmUpdateInterceptor();
    }

    protected void tearDown() throws Exception
    {
        super.tearDown();
    }
    /*
     * パラメータ = null
     * 抛出：WEB3ErrorCatalog.SYSTEM_ERROR_80017
     */
    public void testMutate_C0001()
    {
        final String STR_METHOD_NAME = "testOnCall_C0001()";
        log.entering(TEST_START + STR_METHOD_NAME);

        OrderManagerPersistenceType l_updateType = null;
        OrderManagerPersistenceContext l_dealing = null;
        IfoOrderUnitParams l_orderUnitParams = null;

        try
        {
            
            l_interceptor.mutate(
                    l_updateType,
                    l_dealing,
                    l_orderUnitParams);
            fail();

        }
        catch (WEB3BaseRuntimeException l_ex)
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
     * 注文単価 == null
     * 概算受渡代金 == null
     * 注文単位.isUnexecuted() == true
     */
    public void testMutate_C0002()
    {
        final String STR_METHOD_NAME = "testOnCall_C0002()";
        log.entering(TEST_START + STR_METHOD_NAME);

        OrderManagerPersistenceType l_updateType = null;
        OrderManagerPersistenceContext l_dealing = null;

        IfoOrderUnitParams l_AfterIfoOrderUnitParams = new IfoOrderUnitParams();

        try
        {
            TestDBUtility.deleteAll(IfoOrderRow.TYPE);
            IfoOrderParams l_ifoOrderParams = TestDBUtility.getIfoOrderRow();
            l_ifoOrderParams.setOrderId(1001);
            TestDBUtility.insertWithDel(l_ifoOrderParams);
            
            TestDBUtility.deleteAll(IfoOrderUnitRow.TYPE);
            IfoOrderUnitParams l_orderUnitParams = TestDBUtility.getIfoOrderUnitRow();
            l_orderUnitParams.setPrice(null);
            l_orderUnitParams.setEstimatedPrice(null);
            TestDBUtility.insertWithDel(l_orderUnitParams);

            l_AfterIfoOrderUnitParams = l_interceptor.mutate(
                    l_updateType,
                    l_dealing,
                    l_orderUnitParams);

            assertEquals("0.0",String.valueOf(l_AfterIfoOrderUnitParams.getConfirmedOrderPrice()));
            assertEquals("0.0",String.valueOf(l_AfterIfoOrderUnitParams.getConfirmedEstimatedPrice()));
            assertEquals(WEB3IfoOrderModifyCancelTypeDef.ORDER_REVISED_ALL,l_AfterIfoOrderUnitParams.getModifyCancelType());
        }
        catch (Exception l_ex)
        {
            log.error(STR_METHOD_NAME, l_ex);
            fail();
        }

        log.exiting(TEST_END + STR_METHOD_NAME);
    }
    /*
     * 注文単価 != null
     * 概算受渡代金 != null
     * 注文単位.isUnexecuted() == false
     */
    public void testMutate_C0003()
    {
        final String STR_METHOD_NAME = "testOnCall_C0003()";
        log.entering(TEST_START + STR_METHOD_NAME);

        OrderManagerPersistenceType l_updateType = null;
        OrderManagerPersistenceContext l_dealing = null;

        IfoOrderUnitParams l_AfterIfoOrderUnitParams = new IfoOrderUnitParams();

        try
        {
            TestDBUtility.deleteAll(IfoOrderRow.TYPE);
            IfoOrderParams l_ifoOrderParams = TestDBUtility.getIfoOrderRow();
            l_ifoOrderParams.setOrderId(1001);
            TestDBUtility.insertWithDel(l_ifoOrderParams);
            
            TestDBUtility.deleteAll(IfoOrderUnitRow.TYPE);
            IfoOrderUnitParams l_orderUnitParams = TestDBUtility.getIfoOrderUnitRow();
            l_orderUnitParams.setPrice(123);
            l_orderUnitParams.setEstimatedPrice(456);
            l_orderUnitParams.setExecutedQuantity(100);
            TestDBUtility.insertWithDel(l_orderUnitParams);

            l_AfterIfoOrderUnitParams = l_interceptor.mutate(
                    l_updateType,
                    l_dealing,
                    l_orderUnitParams);

            assertEquals("123.0",String.valueOf(l_AfterIfoOrderUnitParams.getConfirmedOrderPrice()));
            assertEquals("456.0",String.valueOf(l_AfterIfoOrderUnitParams.getConfirmedEstimatedPrice()));
            assertEquals(WEB3IfoOrderModifyCancelTypeDef.ORDER_REVISED_PARTLY,l_AfterIfoOrderUnitParams.getModifyCancelType());
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
