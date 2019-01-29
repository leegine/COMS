head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.04.07.01.01.21;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	5444d9d078c63f4;
filename	WEB3IfoCancelConfirmUpdateInterceptorTest.java;


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
import webbroker3.common.define.WEB3ErrorReasonCodeDef;
import webbroker3.common.define.WEB3ModifyCancelTypeDef;
import webbroker3.mock.TestBaseForMock;
import webbroker3.util.WEB3LogUtility;

public class WEB3IfoCancelConfirmUpdateInterceptorTest extends TestBaseForMock
{
    /**
     * ログ出力ユーティリティ。
     */
    private static WEB3LogUtility log =
        WEB3LogUtility.getInstance(WEB3IfoCancelConfirmUpdateInterceptorTest.class);
    
    private WEB3IfoCancelConfirmUpdateInterceptor l_interceptor = null;

    public WEB3IfoCancelConfirmUpdateInterceptorTest(String arg0)
    {
        super(arg0);
        // TODO Auto-generated constructor stub
    }

    protected void setUp() throws Exception
    {
        super.setUp();
        MOCK_MANAGER.setIsMockUsed(true);
        this.l_interceptor = new WEB3IfoCancelConfirmUpdateInterceptor();
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

        OrderManagerPersistenceType l_orderManagerPersistenceType = null;
        OrderManagerPersistenceContext l_orderManagerPersistenceContext = null;
        IfoOrderUnitParams l_ifoOrderUnitParams = null;

        try
        {
            
            l_interceptor.mutate(
                    l_orderManagerPersistenceType,
                    l_orderManagerPersistenceContext,
                    l_ifoOrderUnitParams);
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
     * l_orderUnit.isUnexecuted() == true
     */
    public void testMutate_C0002()
    {
        final String STR_METHOD_NAME = "testOnCall_C0002()";
        log.entering(TEST_START + STR_METHOD_NAME);

        OrderManagerPersistenceType l_orderManagerPersistenceType = null;
        OrderManagerPersistenceContext l_orderManagerPersistenceContext = null;
        
        IfoOrderUnitParams l_AfterIfoOrderUnitParams = new IfoOrderUnitParams();
        
        try
        {
            TestDBUtility.deleteAll(IfoOrderRow.TYPE);
            IfoOrderParams l_ifoOrderParams = TestDBUtility.getIfoOrderRow();
            l_ifoOrderParams.setOrderId(1001);
            TestDBUtility.insertWithDel(l_ifoOrderParams);
            
            TestDBUtility.deleteAll(IfoOrderUnitRow.TYPE);
            IfoOrderUnitParams l_ifoOrderUnitParams = TestDBUtility.getIfoOrderUnitRow();
            TestDBUtility.insertWithDel(l_ifoOrderUnitParams);
            
            l_AfterIfoOrderUnitParams = l_interceptor.mutate(
                    l_orderManagerPersistenceType,
                    l_orderManagerPersistenceContext,
                    l_ifoOrderUnitParams);

            assertEquals(WEB3ErrorReasonCodeDef.NORMAL,l_AfterIfoOrderUnitParams.getErrorReasonCode());
            assertEquals(WEB3ModifyCancelTypeDef.CANCELED,l_AfterIfoOrderUnitParams.getModifyCancelType());

        }
        catch (Exception l_ex)
        {
            log.error(STR_METHOD_NAME, l_ex);
            fail();
        }

        log.exiting(TEST_END + STR_METHOD_NAME);
    }
    /*
     * l_orderUnit.isUnexecuted() == false
     */
    public void testMutate_C0003()
    {
        final String STR_METHOD_NAME = "testOnCall_C0003()";
        log.entering(TEST_START + STR_METHOD_NAME);

        OrderManagerPersistenceType l_orderManagerPersistenceType = null;
        OrderManagerPersistenceContext l_orderManagerPersistenceContext = null;
        
        IfoOrderUnitParams l_AfterIfoOrderUnitParams = new IfoOrderUnitParams();

        try
        {
            TestDBUtility.deleteAll(IfoOrderRow.TYPE);
            IfoOrderParams l_ifoOrderParams = TestDBUtility.getIfoOrderRow();
            l_ifoOrderParams.setOrderId(1001);
            TestDBUtility.insertWithDel(l_ifoOrderParams);
            
            TestDBUtility.deleteAll(IfoOrderUnitRow.TYPE);
            IfoOrderUnitParams l_ifoOrderUnitParams = TestDBUtility.getIfoOrderUnitRow();
            l_ifoOrderUnitParams.setExecutedQuantity(100);
            
            TestDBUtility.insertWithDel(l_ifoOrderUnitParams);

            l_AfterIfoOrderUnitParams = l_interceptor.mutate(
                    l_orderManagerPersistenceType,
                    l_orderManagerPersistenceContext,
                    l_ifoOrderUnitParams);

            assertEquals(WEB3ErrorReasonCodeDef.NORMAL,l_AfterIfoOrderUnitParams.getErrorReasonCode());
            assertEquals(WEB3ModifyCancelTypeDef.PART_CANCELED,l_AfterIfoOrderUnitParams.getModifyCancelType());

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
