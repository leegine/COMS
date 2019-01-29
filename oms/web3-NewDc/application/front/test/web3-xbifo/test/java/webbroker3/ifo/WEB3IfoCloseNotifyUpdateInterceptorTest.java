head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.04.07.00.59.57;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	5444d9d078c63f4;
filename	WEB3IfoCloseNotifyUpdateInterceptorTest.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
File Name        : WEB3ToSuccIfoOrderUnitServiceInterceptorTest.java
Author Name      : Daiwa Institute of Research
Revision History : 2008/07/14 �����i���u�j�V�K�쐬
*/
package webbroker3.ifo;

import com.fitechlabs.xtrade.plugin.tc.gentrade.OrderManagerPersistenceContext;
import com.fitechlabs.xtrade.plugin.tc.gentrade.OrderManagerPersistenceType;
import com.fitechlabs.xtrade.plugin.tc.xbifo.data.IfoOrderUnitParams;
import com.fitechlabs.xtrade.plugin.tc.xbifo.data.IfoOrderUnitRow;

import test.util.TestDBUtility;

import webbroker3.common.WEB3BaseRuntimeException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.mock.TestBaseForMock;
import webbroker3.util.WEB3LogUtility;

public class WEB3IfoCloseNotifyUpdateInterceptorTest extends TestBaseForMock
{
    /**
     * ���O�o�̓��[�e�B���e�B�B
     */
    private static WEB3LogUtility log =
        WEB3LogUtility.getInstance(WEB3IfoCloseNotifyUpdateInterceptorTest.class);
    
    private WEB3IfoCloseNotifyUpdateInterceptor l_interceptor = null;

    public WEB3IfoCloseNotifyUpdateInterceptorTest(String arg0)
    {
        super(arg0);
        // TODO Auto-generated constructor stub
    }

    protected void setUp() throws Exception
    {
        super.setUp();
        MOCK_MANAGER.setIsMockUsed(true);
        this.l_interceptor = new WEB3IfoCloseNotifyUpdateInterceptor();
    }

    protected void tearDown() throws Exception
    {
        super.tearDown();
    }
    /*
     * �����ʒm�敪=="�������"�����~�b�g�����L��(*1�j�̏ꍇ�A�X�V�O�̋t�w�l��l
     * �����ʒm�敪=="�������"�����~�b�g�����L��(*1�j�̏ꍇ�A�X�V�O�́iW�w�l�j�����w�l
     */
    public void testMutate_C0001()
    {
        final String STR_METHOD_NAME = "testOnCall_C0001()";
        log.entering(TEST_START + STR_METHOD_NAME);

        OrderManagerPersistenceType l_updateType = null;
        OrderManagerPersistenceContext l_dealing = null;

        IfoOrderUnitParams l_afterIfoOrderUnitParams = null;
        try
        {

            TestDBUtility.deleteAll(IfoOrderUnitRow.TYPE);
            IfoOrderUnitParams l_ifoOrderUnitParams = TestDBUtility.getIfoOrderUnitRow();
            l_ifoOrderUnitParams.setOrderConditionType(null);
            l_ifoOrderUnitParams.setOrgOrderConditionType("2");
            l_ifoOrderUnitParams.setRequestType("0");
            l_ifoOrderUnitParams.setStopOrderPrice(null);
            l_ifoOrderUnitParams.setWLimitPrice(null);
            TestDBUtility.insertWithDel(l_ifoOrderUnitParams);

            l_interceptor.setCloseNotifyType("2");
            l_afterIfoOrderUnitParams = l_interceptor.mutate(
                    l_updateType,
                    l_dealing,
                    l_ifoOrderUnitParams);
            assertEquals("0.0",String.valueOf(l_afterIfoOrderUnitParams.getOrgStopOrderPrice()));
            assertEquals("0.0",String.valueOf(l_afterIfoOrderUnitParams.getOrgWLimitPrice()));

        }
        catch (Exception l_ex)
        {
            log.error(STR_METHOD_NAME, l_ex);
            fail();
        }

        log.exiting(TEST_END + STR_METHOD_NAME);
    }
    /*
     * �ȊO�A�i�����l�j
     */
    public void testMutate_C0002()
    {
        final String STR_METHOD_NAME = "testOnCall_C0002()";
        log.entering(TEST_START + STR_METHOD_NAME);

        OrderManagerPersistenceType l_updateType = null;
        OrderManagerPersistenceContext l_dealing = null;

        IfoOrderUnitParams l_afterIfoOrderUnitParams = null;
        try
        {

            TestDBUtility.deleteAll(IfoOrderUnitRow.TYPE);
            IfoOrderUnitParams l_ifoOrderUnitParams = TestDBUtility.getIfoOrderUnitRow();
            l_ifoOrderUnitParams.setOrderConditionType(null);
            l_ifoOrderUnitParams.setOrgOrderConditionType("2");
            l_ifoOrderUnitParams.setRequestType("0");
            l_ifoOrderUnitParams.setStopOrderPrice(123);
            l_ifoOrderUnitParams.setWLimitPrice(456);
            TestDBUtility.insertWithDel(l_ifoOrderUnitParams);

            l_interceptor.setCloseNotifyType("2");
            l_afterIfoOrderUnitParams = l_interceptor.mutate(
                    l_updateType,
                    l_dealing,
                    l_ifoOrderUnitParams);
            assertEquals("123.0",String.valueOf(l_afterIfoOrderUnitParams.getOrgStopOrderPrice()));
            assertEquals("456.0",String.valueOf(l_afterIfoOrderUnitParams.getOrgWLimitPrice()));

        }
        catch (Exception l_ex)
        {
            log.error(STR_METHOD_NAME, l_ex);
            fail();
        }

        log.exiting(TEST_END + STR_METHOD_NAME);
    }
    /*
     * �����P�ʂ��擾�ł��܂���ł����B
     * ?�o:WEB3ErrorCatalog.SYSTEM_ERROR_80003
     */
    public void testMutate_C0003()
    {
        final String STR_METHOD_NAME = "testOnCall_C0003()";
        log.entering(TEST_START + STR_METHOD_NAME);

        OrderManagerPersistenceType l_updateType = null;
        OrderManagerPersistenceContext l_dealing = null;
        IfoOrderUnitParams l_ifoOrderUnitParams = new IfoOrderUnitParams();

        try
        {

            l_interceptor.mutate(
                    l_updateType,
                    l_dealing,
                    l_ifoOrderUnitParams);
            fail();

        }
        catch (WEB3BaseRuntimeException l_ex)
        {
            log.error(STR_METHOD_NAME, l_ex);
            assertEquals(WEB3ErrorCatalog.SYSTEM_ERROR_80003,l_ex.getErrorInfo());
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
