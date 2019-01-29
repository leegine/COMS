head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.04.07.01.33.36;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	5444d9d078c63f4;
filename	WEB3AioSLRepayCancelUpdateInterceptorTest.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright           : (��)��a���� �،��\�����[�V�����V�X�e�����
File Name           : �،��S�ۃ��[���ԍώ���X�V�C���^�Z�v�^�̃e�X�g�N���X(WEB3AioSLRepayCancelUpdateInterceptorTest.java)
Author Name         : Daiwa Institute of Research
Revision History    : 2007/09/23 ���n�m (���u) �V�K�쐬 �d�l�ύX�E���f��No.759,DB�X�V�d�lNo.150
*/

package webbroker3.aio;

import com.fitechlabs.xtrade.plugin.tc.gentrade.OrderManagerPersistenceContext;
import com.fitechlabs.xtrade.plugin.tc.gentrade.OrderManagerPersistenceType;
import com.fitechlabs.xtrade.plugin.tc.gentrade.OrderOpenStatusEnum;
import com.fitechlabs.xtrade.plugin.tc.gentrade.OrderStatusEnum;
import com.fitechlabs.xtrade.plugin.tc.xbaio.data.AioOrderUnitParams;

import webbroker3.common.WEB3BaseRuntimeException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.common.define.WEB3ModifyCancelTypeDef;
import webbroker3.mock.TestBaseForMock;
import webbroker3.util.WEB3LogUtility;

/**
 * (�،��S�ۃ��[���ԍώ���X�V�C���^�Z�v�^)<BR>
 * �،��S�ۃ��[���ԍώ���X�V�C���^�Z�v�^�̃e�X�g�N���X<BR>
 *
 * @@author ���n�m
 * @@version 1.0
 */
public class WEB3AioSLRepayCancelUpdateInterceptorTest extends TestBaseForMock
{

    /**
     * (���O�o�̓��[�e�B���e�B)
     */
    private static WEB3LogUtility log =
        WEB3LogUtility.getInstance(
            WEB3AioSLRepayCancelUpdateInterceptorTest.class);

    /**
     * SL�����J��UnitServiceImpl
     */
    private WEB3AioSLRepayCancelUpdateInterceptor l_interceptor = null;

    /**
     * @@param arg0
     */
    public WEB3AioSLRepayCancelUpdateInterceptorTest(String arg0)
    {
        super(arg0);
    }

    /**
     *
     */
    protected void setUp() throws Exception
    {
        super.setUp();
    }

    /**
     *
     */
    protected void tearDown() throws Exception
    {
        super.tearDown();
    }

    /**
     *
     */
    public void testMutate_case0001()
    {
        String STR_METHOD_NAME = " testMutate_case0001()";
        log.entering(TEST_START + STR_METHOD_NAME);

        l_interceptor = new WEB3AioSLRepayCancelUpdateInterceptor();

        OrderManagerPersistenceType l_orderManagerPersistenceType = null;
        OrderManagerPersistenceContext l_orderManagerPersistenceContext = null;
        AioOrderUnitParams l_aioOrderUnitParams = null;

        try
        {

            l_interceptor.mutate(
                l_orderManagerPersistenceType,
                l_orderManagerPersistenceContext,
                l_aioOrderUnitParams);

            log.exiting(TEST_END + STR_METHOD_NAME);
            fail();
        }
        catch (WEB3BaseRuntimeException l_ex)
        {
            assertEquals(WEB3ErrorCatalog.SYSTEM_ERROR_80017, l_ex.getErrorInfo());
            log.debug(l_ex.getMessage(), l_ex);
            log.exiting(TEST_END + STR_METHOD_NAME);
        }
        catch (Exception l_ex)
        {
            log.debug(l_ex.getMessage(), l_ex);
            log.exiting(TEST_END + STR_METHOD_NAME);
            fail();
        }
    }

    /**
     *
     */
    public void testMutate_case0002()
    {
        String STR_METHOD_NAME = " testMutate_case0002()";
        log.entering(TEST_START + STR_METHOD_NAME);

        l_interceptor = new WEB3AioSLRepayCancelUpdateInterceptor();

        try
        {

            OrderManagerPersistenceType l_orderManagerPersistenceType = null;
            OrderManagerPersistenceContext l_orderManagerPersistenceContext = null;
            AioOrderUnitParams l_aioOrderUnitParams = new AioOrderUnitParams();

            l_aioOrderUnitParams.setOrderStatus(OrderStatusEnum.ACCEPTED);
            l_aioOrderUnitParams.setOrderOpenStatus(OrderOpenStatusEnum.OPEN);
            l_aioOrderUnitParams.setCancelType(WEB3ModifyCancelTypeDef.ERROR);

            AioOrderUnitParams l_actualAioOrderUnitParams =
                l_interceptor.mutate(
                    l_orderManagerPersistenceType,
                    l_orderManagerPersistenceContext,
                    l_aioOrderUnitParams);

            assertNotNull(l_actualAioOrderUnitParams);
            assertEquals(OrderStatusEnum.CANCELLED,
                l_actualAioOrderUnitParams.getOrderStatus());
            assertEquals(OrderOpenStatusEnum.CLOSED,
                l_actualAioOrderUnitParams.getOrderOpenStatus());
            assertEquals(WEB3ModifyCancelTypeDef.CANCELED,
                l_actualAioOrderUnitParams.getCancelType());

            log.exiting(TEST_END + STR_METHOD_NAME);
        }
        catch (WEB3BaseRuntimeException l_ex)
        {
            log.debug(l_ex.getMessage(), l_ex);
            log.exiting(TEST_END + STR_METHOD_NAME);
            fail();
        }
        catch (Exception l_ex)
        {
            log.debug(l_ex.getMessage(), l_ex);
            log.exiting(TEST_END + STR_METHOD_NAME);
            fail();
        }
    }

}
@
