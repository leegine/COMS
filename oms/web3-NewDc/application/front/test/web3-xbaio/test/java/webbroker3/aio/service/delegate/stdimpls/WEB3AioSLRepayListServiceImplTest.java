head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.04.07.01.36.33;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	5444d9d078c63f4;
filename	WEB3AioSLRepayListServiceImplTest.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright           : (株)大和総研 証券ソリューションシステム第二部
File Name           : 証券担保ローン返済一覧サービスImplのテストクラス(WEB3AioSLRepayListServiceImplTest.java)
Author Name         : Daiwa Institute of Research
Revision History    : 2007/09/23 周墨洋 (中訊) 新規作成 仕様変更 モデルNo.763
*/

package webbroker3.aio.service.delegate.stdimpls;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Date;

import com.fitechlabs.xtrade.plugin.tc.gentrade.OrderOpenStatusEnum;
import com.fitechlabs.xtrade.plugin.tc.gentrade.OrderStatusEnum;
import com.fitechlabs.xtrade.plugin.tc.gentrade.OrderTypeEnum;
import com.fitechlabs.xtrade.plugin.tc.gentrade.SubAccountTypeEnum;
import com.fitechlabs.xtrade.plugin.tc.gentrade.data.MainAccountParams;
import com.fitechlabs.xtrade.plugin.tc.gentrade.data.SubAccountParams;
import com.fitechlabs.xtrade.plugin.tc.xbaio.data.AioOrderUnitParams;

import test.util.TestDBUtility;
import webbroker3.aio.message.WEB3SLRepayCancelListRequest;
import webbroker3.aio.message.WEB3SLRepayCancelListResponse;
import webbroker3.aio.message.WEB3SLRepayUnit;
import webbroker3.common.WEB3BaseException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.common.define.WEB3AccountOpenStatusDef;
import webbroker3.common.define.WEB3SecuredLoanSecAccOpenDivDef;
import webbroker3.gentrade.WEB3GentradeTradingTimeManagementForMock;
import webbroker3.gentrade.data.StockSecuredLoanParams;
import webbroker3.mock.TestBaseForMock;
import webbroker3.util.WEB3DateUtility;
import webbroker3.util.WEB3LogUtility;

/**
 * (証券担保ローン返済一覧サービスImpl)<BR>
 * 証券担保ローン返済一覧サービスImplのテストクラス<BR>
 *
 * @@author 周墨洋
 * @@version 1.0
 */
public class WEB3AioSLRepayListServiceImplTest extends TestBaseForMock
{

    /**
     * (ログ出力ユーティリティ)
     */
    private static WEB3LogUtility log =
        WEB3LogUtility.getInstance(
            WEB3AioSLRepayListServiceImplTest.class);

    /**
     * SL口座開設UnitServiceImpl
     */
    private WEB3AioSLRepayListServiceImpl l_aioSLRepayListServiceImpl = null;

    /**
     * @@param arg0
     */
    public WEB3AioSLRepayListServiceImplTest(String arg0)
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
    public void testGetCancelEnableFlag_case0001()
    {
        String STR_METHOD_NAME = " testGetCancelEnableFlag_case0001()";
        log.entering(TEST_START + STR_METHOD_NAME);

        l_aioSLRepayListServiceImpl = new WEB3AioSLRepayListServiceImpl();

        try
        {
            AioOrderUnitParams l_aioOrderUnitParams = new AioOrderUnitParams();
            l_aioOrderUnitParams.setMqStatus("0");
            l_aioOrderUnitParams.setOrderStatus(OrderStatusEnum.CANCELLED);
            l_aioOrderUnitParams.setOrderOpenStatus(OrderOpenStatusEnum.CLOSED);
            l_aioOrderUnitParams.setBizDate("20070927");
            Date l_datOrderBizDate = WEB3DateUtility.getDate("20070926", "yyyyMMdd");

            Method l_method =
                WEB3AioSLRepayListServiceImpl.class.getDeclaredMethod(
                    "getCancelEnableFlag",
                    new Class[] {AioOrderUnitParams.class, Date.class});
            l_method.setAccessible(true);

            String l_strCancelEnableFlag =
                (String)l_method.invoke(
                    l_aioSLRepayListServiceImpl,
                    new Object[] {l_aioOrderUnitParams, l_datOrderBizDate});

            assertNotNull(l_strCancelEnableFlag);
            assertEquals("3", l_strCancelEnableFlag);
            log.exiting(TEST_END + STR_METHOD_NAME);
        }
        catch (NoSuchMethodException l_ex)
        {
            log.debug(l_ex.getMessage(), l_ex);
            log.exiting(TEST_END + STR_METHOD_NAME);
            fail();
        }
        catch (InvocationTargetException l_ex)
        {
            log.debug(l_ex.getMessage(), l_ex);
            log.exiting(TEST_END + STR_METHOD_NAME);
            fail();
        }
        catch (IllegalAccessException l_ex)
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

    /**
     *
     */
    public void testGetCancelEnableFlag_case0002()
    {
        String STR_METHOD_NAME = " testGetCancelEnableFlag_case0002()";
        log.entering(TEST_START + STR_METHOD_NAME);

        l_aioSLRepayListServiceImpl = new WEB3AioSLRepayListServiceImpl();

        try
        {
            AioOrderUnitParams l_aioOrderUnitParams = new AioOrderUnitParams();
            l_aioOrderUnitParams.setMqStatus("0");
            l_aioOrderUnitParams.setOrderStatus(OrderStatusEnum.CANCELLED);
            l_aioOrderUnitParams.setOrderOpenStatus(OrderOpenStatusEnum.CLOSED);
            l_aioOrderUnitParams.setBizDate("20070926");
            Date l_datOrderBizDate = WEB3DateUtility.getDate("20070926", "yyyyMMdd");

            Method l_method =
                WEB3AioSLRepayListServiceImpl.class.getDeclaredMethod(
                    "getCancelEnableFlag",
                    new Class[] {AioOrderUnitParams.class, Date.class});
            l_method.setAccessible(true);

            String l_strCancelEnableFlag =
                (String)l_method.invoke(
                    l_aioSLRepayListServiceImpl,
                    new Object[] {l_aioOrderUnitParams, l_datOrderBizDate});

            assertNotNull(l_strCancelEnableFlag);
            assertEquals("3", l_strCancelEnableFlag);
            log.exiting(TEST_END + STR_METHOD_NAME);
        }
        catch (NoSuchMethodException l_ex)
        {
            log.debug(l_ex.getMessage(), l_ex);
            log.exiting(TEST_END + STR_METHOD_NAME);
            fail();
        }
        catch (InvocationTargetException l_ex)
        {
            log.debug(l_ex.getMessage(), l_ex);
            log.exiting(TEST_END + STR_METHOD_NAME);
            fail();
        }
        catch (IllegalAccessException l_ex)
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

    /**
     *
     */
    public void testGetCancelEnableFlag_case0003()
    {
        String STR_METHOD_NAME = " testGetCancelEnableFlag_case0003()";
        log.entering(TEST_START + STR_METHOD_NAME);

        l_aioSLRepayListServiceImpl = new WEB3AioSLRepayListServiceImpl();

        try
        {
            AioOrderUnitParams l_aioOrderUnitParams = new AioOrderUnitParams();
            l_aioOrderUnitParams.setMqStatus("0");
            l_aioOrderUnitParams.setOrderStatus(OrderStatusEnum.CANCELLED);
            l_aioOrderUnitParams.setOrderOpenStatus(OrderOpenStatusEnum.CLOSED);
            l_aioOrderUnitParams.setBizDate("20070925");
            Date l_datOrderBizDate = WEB3DateUtility.getDate("20070926", "yyyyMMdd");

            Method l_method =
                WEB3AioSLRepayListServiceImpl.class.getDeclaredMethod(
                    "getCancelEnableFlag",
                    new Class[] {AioOrderUnitParams.class, Date.class});
            l_method.setAccessible(true);

            String l_strCancelEnableFlag =
                (String)l_method.invoke(
                    l_aioSLRepayListServiceImpl,
                    new Object[] {l_aioOrderUnitParams, l_datOrderBizDate});

            assertNotNull(l_strCancelEnableFlag);
            assertEquals("3", l_strCancelEnableFlag);
            log.exiting(TEST_END + STR_METHOD_NAME);
        }
        catch (NoSuchMethodException l_ex)
        {
            log.debug(l_ex.getMessage(), l_ex);
            log.exiting(TEST_END + STR_METHOD_NAME);
            fail();
        }
        catch (InvocationTargetException l_ex)
        {
            log.debug(l_ex.getMessage(), l_ex);
            log.exiting(TEST_END + STR_METHOD_NAME);
            fail();
        }
        catch (IllegalAccessException l_ex)
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

    /**
     *
     */
    public void testGetCancelEnableFlag_case0004()
    {
        String STR_METHOD_NAME = " testGetCancelEnableFlag_case0004()";
        log.entering(TEST_START + STR_METHOD_NAME);

        l_aioSLRepayListServiceImpl = new WEB3AioSLRepayListServiceImpl();

        try
        {
            AioOrderUnitParams l_aioOrderUnitParams = new AioOrderUnitParams();
            l_aioOrderUnitParams.setMqStatus("0");
            l_aioOrderUnitParams.setOrderStatus(OrderStatusEnum.CANCELLED);
            l_aioOrderUnitParams.setOrderOpenStatus(OrderOpenStatusEnum.OPEN);
            l_aioOrderUnitParams.setBizDate("20070925");
            Date l_datOrderBizDate = WEB3DateUtility.getDate("20070926", "yyyyMMdd");

            Method l_method =
                WEB3AioSLRepayListServiceImpl.class.getDeclaredMethod(
                    "getCancelEnableFlag",
                    new Class[] {AioOrderUnitParams.class, Date.class});
            l_method.setAccessible(true);

            String l_strCancelEnableFlag =
                (String)l_method.invoke(
                    l_aioSLRepayListServiceImpl,
                    new Object[] {l_aioOrderUnitParams, l_datOrderBizDate});

            assertNotNull(l_strCancelEnableFlag);
            assertEquals("0", l_strCancelEnableFlag);
            log.exiting(TEST_END + STR_METHOD_NAME);
        }
        catch (NoSuchMethodException l_ex)
        {
            log.debug(l_ex.getMessage(), l_ex);
            log.exiting(TEST_END + STR_METHOD_NAME);
            fail();
        }
        catch (InvocationTargetException l_ex)
        {
            log.debug(l_ex.getMessage(), l_ex);
            log.exiting(TEST_END + STR_METHOD_NAME);
            fail();
        }
        catch (IllegalAccessException l_ex)
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

    /**
     *
     */
    public void testGetCancelEnableFlag_case0005()
    {
        String STR_METHOD_NAME = " testGetCancelEnableFlag_case0005()";
        log.entering(TEST_START + STR_METHOD_NAME);

        l_aioSLRepayListServiceImpl = new WEB3AioSLRepayListServiceImpl();

        try
        {
            AioOrderUnitParams l_aioOrderUnitParams = new AioOrderUnitParams();
            l_aioOrderUnitParams.setMqStatus("0");
            l_aioOrderUnitParams.setOrderStatus(OrderStatusEnum.ORDERED);
            l_aioOrderUnitParams.setOrderOpenStatus(OrderOpenStatusEnum.CLOSED);
            l_aioOrderUnitParams.setBizDate("20070925");
            Date l_datOrderBizDate = WEB3DateUtility.getDate("20070926", "yyyyMMdd");

            Method l_method =
                WEB3AioSLRepayListServiceImpl.class.getDeclaredMethod(
                    "getCancelEnableFlag",
                    new Class[] {AioOrderUnitParams.class, Date.class});
            l_method.setAccessible(true);

            String l_strCancelEnableFlag =
                (String)l_method.invoke(
                    l_aioSLRepayListServiceImpl,
                    new Object[] {l_aioOrderUnitParams, l_datOrderBizDate});

            assertNotNull(l_strCancelEnableFlag);
            assertEquals("0", l_strCancelEnableFlag);
            log.exiting(TEST_END + STR_METHOD_NAME);
        }
        catch (NoSuchMethodException l_ex)
        {
            log.debug(l_ex.getMessage(), l_ex);
            log.exiting(TEST_END + STR_METHOD_NAME);
            fail();
        }
        catch (InvocationTargetException l_ex)
        {
            log.debug(l_ex.getMessage(), l_ex);
            log.exiting(TEST_END + STR_METHOD_NAME);
            fail();
        }
        catch (IllegalAccessException l_ex)
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

    /**
     *
     */
    public void testGetCancelEnableFlag_case0006()
    {
        String STR_METHOD_NAME = " testGetCancelEnableFlag_case0006()";
        log.entering(TEST_START + STR_METHOD_NAME);

        l_aioSLRepayListServiceImpl = new WEB3AioSLRepayListServiceImpl();

        try
        {
            AioOrderUnitParams l_aioOrderUnitParams = new AioOrderUnitParams();
            l_aioOrderUnitParams.setMqStatus("0");
            l_aioOrderUnitParams.setOrderStatus(OrderStatusEnum.ORDERED);
            l_aioOrderUnitParams.setOrderOpenStatus(OrderOpenStatusEnum.OPEN);
            l_aioOrderUnitParams.setBizDate("20070925");
            Date l_datOrderBizDate = WEB3DateUtility.getDate("20070926", "yyyyMMdd");

            Method l_method =
                WEB3AioSLRepayListServiceImpl.class.getDeclaredMethod(
                    "getCancelEnableFlag",
                    new Class[] {AioOrderUnitParams.class, Date.class});
            l_method.setAccessible(true);

            String l_strCancelEnableFlag =
                (String)l_method.invoke(
                    l_aioSLRepayListServiceImpl,
                    new Object[] {l_aioOrderUnitParams, l_datOrderBizDate});

            assertNotNull(l_strCancelEnableFlag);
            assertEquals("0", l_strCancelEnableFlag);
            log.exiting(TEST_END + STR_METHOD_NAME);
        }
        catch (NoSuchMethodException l_ex)
        {
            log.debug(l_ex.getMessage(), l_ex);
            log.exiting(TEST_END + STR_METHOD_NAME);
            fail();
        }
        catch (InvocationTargetException l_ex)
        {
            log.debug(l_ex.getMessage(), l_ex);
            log.exiting(TEST_END + STR_METHOD_NAME);
            fail();
        }
        catch (IllegalAccessException l_ex)
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

    /**
     *
     */
    public void testGetCancelEnableFlag_case0007()
    {
        String STR_METHOD_NAME = " testGetCancelEnableFlag_case0007()";
        log.entering(TEST_START + STR_METHOD_NAME);

        l_aioSLRepayListServiceImpl = new WEB3AioSLRepayListServiceImpl();

        try
        {
            AioOrderUnitParams l_aioOrderUnitParams = new AioOrderUnitParams();
            l_aioOrderUnitParams.setMqStatus("0");
            l_aioOrderUnitParams.setOrderStatus(OrderStatusEnum.CANCELLED);
            l_aioOrderUnitParams.setOrderOpenStatus(OrderOpenStatusEnum.OPEN);
            l_aioOrderUnitParams.setBizDate("20070926");
            Date l_datOrderBizDate = WEB3DateUtility.getDate("20070926", "yyyyMMdd");

            Method l_method =
                WEB3AioSLRepayListServiceImpl.class.getDeclaredMethod(
                    "getCancelEnableFlag",
                    new Class[] {AioOrderUnitParams.class, Date.class});
            l_method.setAccessible(true);

            String l_strCancelEnableFlag =
                (String)l_method.invoke(
                    l_aioSLRepayListServiceImpl,
                    new Object[] {l_aioOrderUnitParams, l_datOrderBizDate});

            assertNotNull(l_strCancelEnableFlag);
            assertEquals("1", l_strCancelEnableFlag);
            log.exiting(TEST_END + STR_METHOD_NAME);
        }
        catch (NoSuchMethodException l_ex)
        {
            log.debug(l_ex.getMessage(), l_ex);
            log.exiting(TEST_END + STR_METHOD_NAME);
            fail();
        }
        catch (InvocationTargetException l_ex)
        {
            log.debug(l_ex.getMessage(), l_ex);
            log.exiting(TEST_END + STR_METHOD_NAME);
            fail();
        }
        catch (IllegalAccessException l_ex)
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

    /**
     *
     */
    public void testGetCancelEnableFlag_case0008()
    {
        String STR_METHOD_NAME = " testGetCancelEnableFlag_case0008()";
        log.entering(TEST_START + STR_METHOD_NAME);

        l_aioSLRepayListServiceImpl = new WEB3AioSLRepayListServiceImpl();

        try
        {
            AioOrderUnitParams l_aioOrderUnitParams = new AioOrderUnitParams();
            l_aioOrderUnitParams.setMqStatus("0");
            l_aioOrderUnitParams.setOrderStatus(OrderStatusEnum.ORDERED);
            l_aioOrderUnitParams.setOrderOpenStatus(OrderOpenStatusEnum.CLOSED);
            l_aioOrderUnitParams.setBizDate("20070927");
            Date l_datOrderBizDate = WEB3DateUtility.getDate("20070926", "yyyyMMdd");

            Method l_method =
                WEB3AioSLRepayListServiceImpl.class.getDeclaredMethod(
                    "getCancelEnableFlag",
                    new Class[] {AioOrderUnitParams.class, Date.class});
            l_method.setAccessible(true);

            String l_strCancelEnableFlag =
                (String)l_method.invoke(
                    l_aioSLRepayListServiceImpl,
                    new Object[] {l_aioOrderUnitParams, l_datOrderBizDate});

            assertNotNull(l_strCancelEnableFlag);
            assertEquals("1", l_strCancelEnableFlag);
            log.exiting(TEST_END + STR_METHOD_NAME);
        }
        catch (NoSuchMethodException l_ex)
        {
            log.debug(l_ex.getMessage(), l_ex);
            log.exiting(TEST_END + STR_METHOD_NAME);
            fail();
        }
        catch (InvocationTargetException l_ex)
        {
            log.debug(l_ex.getMessage(), l_ex);
            log.exiting(TEST_END + STR_METHOD_NAME);
            fail();
        }
        catch (IllegalAccessException l_ex)
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

    /**
     *
     */
    public void testGetCancelEnableFlag_case0009()
    {
        String STR_METHOD_NAME = " testGetCancelEnableFlag_case0009()";
        log.entering(TEST_START + STR_METHOD_NAME);

        l_aioSLRepayListServiceImpl = new WEB3AioSLRepayListServiceImpl();

        try
        {
            AioOrderUnitParams l_aioOrderUnitParams = new AioOrderUnitParams();
            l_aioOrderUnitParams.setMqStatus("0");
            l_aioOrderUnitParams.setOrderStatus(OrderStatusEnum.ORDERED);
            l_aioOrderUnitParams.setOrderOpenStatus(OrderOpenStatusEnum.OPEN);
            l_aioOrderUnitParams.setBizDate("20070927");
            Date l_datOrderBizDate = WEB3DateUtility.getDate("20070926", "yyyyMMdd");

            Method l_method =
                WEB3AioSLRepayListServiceImpl.class.getDeclaredMethod(
                    "getCancelEnableFlag",
                    new Class[] {AioOrderUnitParams.class, Date.class});
            l_method.setAccessible(true);

            String l_strCancelEnableFlag =
                (String)l_method.invoke(
                    l_aioSLRepayListServiceImpl,
                    new Object[] {l_aioOrderUnitParams, l_datOrderBizDate});

            assertNotNull(l_strCancelEnableFlag);
            assertEquals("1", l_strCancelEnableFlag);
            log.exiting(TEST_END + STR_METHOD_NAME);
        }
        catch (NoSuchMethodException l_ex)
        {
            log.debug(l_ex.getMessage(), l_ex);
            log.exiting(TEST_END + STR_METHOD_NAME);
            fail();
        }
        catch (InvocationTargetException l_ex)
        {
            log.debug(l_ex.getMessage(), l_ex);
            log.exiting(TEST_END + STR_METHOD_NAME);
            fail();
        }
        catch (IllegalAccessException l_ex)
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

    /**
     *
     */
    public void testGetCancelEnableFlag_case0010()
    {
        String STR_METHOD_NAME = " testGetCancelEnableFlag_case0010()";
        log.entering(TEST_START + STR_METHOD_NAME);

        l_aioSLRepayListServiceImpl = new WEB3AioSLRepayListServiceImpl();

        try
        {
            AioOrderUnitParams l_aioOrderUnitParams = new AioOrderUnitParams();
            l_aioOrderUnitParams.setMqStatus("1");
            l_aioOrderUnitParams.setOrderStatus(OrderStatusEnum.CANCELLED);
            l_aioOrderUnitParams.setOrderOpenStatus(OrderOpenStatusEnum.CLOSED);
            l_aioOrderUnitParams.setBizDate("20070925");
            Date l_datOrderBizDate = WEB3DateUtility.getDate("20070926", "yyyyMMdd");

            Method l_method =
                WEB3AioSLRepayListServiceImpl.class.getDeclaredMethod(
                    "getCancelEnableFlag",
                    new Class[] {AioOrderUnitParams.class, Date.class});
            l_method.setAccessible(true);

            String l_strCancelEnableFlag =
                (String)l_method.invoke(
                    l_aioSLRepayListServiceImpl,
                    new Object[] {l_aioOrderUnitParams, l_datOrderBizDate});

            assertNotNull(l_strCancelEnableFlag);
            assertEquals("2", l_strCancelEnableFlag);
            log.exiting(TEST_END + STR_METHOD_NAME);
        }
        catch (NoSuchMethodException l_ex)
        {
            log.debug(l_ex.getMessage(), l_ex);
            log.exiting(TEST_END + STR_METHOD_NAME);
            fail();
        }
        catch (InvocationTargetException l_ex)
        {
            log.debug(l_ex.getMessage(), l_ex);
            log.exiting(TEST_END + STR_METHOD_NAME);
            fail();
        }
        catch (IllegalAccessException l_ex)
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

    /**
     *
     */
    public void testGetCancelEnableFlag_case0011()
    {
        String STR_METHOD_NAME = " testGetCancelEnableFlag_case0011()";
        log.entering(TEST_START + STR_METHOD_NAME);

        l_aioSLRepayListServiceImpl = new WEB3AioSLRepayListServiceImpl();

        try
        {
            AioOrderUnitParams l_aioOrderUnitParams = new AioOrderUnitParams();
            l_aioOrderUnitParams.setMqStatus("2");
            l_aioOrderUnitParams.setOrderStatus(OrderStatusEnum.CANCELLED);
            l_aioOrderUnitParams.setOrderOpenStatus(OrderOpenStatusEnum.CLOSED);
            l_aioOrderUnitParams.setBizDate("20070925");
            Date l_datOrderBizDate = WEB3DateUtility.getDate("20070926", "yyyyMMdd");

            Method l_method =
                WEB3AioSLRepayListServiceImpl.class.getDeclaredMethod(
                    "getCancelEnableFlag",
                    new Class[] {AioOrderUnitParams.class, Date.class});
            l_method.setAccessible(true);

            String l_strCancelEnableFlag =
                (String)l_method.invoke(
                    l_aioSLRepayListServiceImpl,
                    new Object[] {l_aioOrderUnitParams, l_datOrderBizDate});

            assertNotNull(l_strCancelEnableFlag);
            assertEquals("4", l_strCancelEnableFlag);
            log.exiting(TEST_END + STR_METHOD_NAME);
        }
        catch (NoSuchMethodException l_ex)
        {
            log.debug(l_ex.getMessage(), l_ex);
            log.exiting(TEST_END + STR_METHOD_NAME);
            fail();
        }
        catch (InvocationTargetException l_ex)
        {
            log.debug(l_ex.getMessage(), l_ex);
            log.exiting(TEST_END + STR_METHOD_NAME);
            fail();
        }
        catch (IllegalAccessException l_ex)
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

    /**
     *
     */
    public void testExecute_case0001()
    {
        String STR_METHOD_NAME = " testExecute_case0001()";
        log.entering(TEST_START + STR_METHOD_NAME);

        l_aioSLRepayListServiceImpl = new WEB3AioSLRepayListServiceImpl();

        try
        {
            TestDBUtility.deleteAll(MainAccountParams.TYPE);
            TestDBUtility.deleteAll(SubAccountParams.TYPE);
            TestDBUtility.deleteAll(StockSecuredLoanParams.TYPE);
        }
        catch (Exception l_ex)
        {
            log.debug(l_ex.getMessage(), l_ex);
            log.exiting(TEST_END + STR_METHOD_NAME);
            fail();
        }

        try
        {
            WEB3SLRepayCancelListRequest l_slRepayCancelListRequest = null;

            l_aioSLRepayListServiceImpl.execute(l_slRepayCancelListRequest);

            log.exiting(TEST_END + STR_METHOD_NAME);
            fail();
        }
        catch (WEB3BaseException l_ex)
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
        finally
        {

            try
            {
                TestDBUtility.deleteAll(MainAccountParams.TYPE);
                TestDBUtility.deleteAll(SubAccountParams.TYPE);
                TestDBUtility.deleteAll(StockSecuredLoanParams.TYPE);
            }
            catch (Exception l_ex)
            {
                log.debug(l_ex.getMessage(), l_ex);
                log.exiting(TEST_END + STR_METHOD_NAME);
                fail();
            }
        }
    }

    /**
     *
     */
    public void testExecute_case0002()
    {
        String STR_METHOD_NAME = " testExecute_case0002()";
        log.entering(TEST_START + STR_METHOD_NAME);

        l_aioSLRepayListServiceImpl = new WEB3AioSLRepayListServiceImpl();

        MainAccountParams l_mainAccountParams = TestDBUtility.getMainAccountRow();
        SubAccountParams l_subAccountParams = TestDBUtility.getSubAccountRow();
        l_subAccountParams.setAccountId(l_mainAccountParams.getAccountId());
        l_subAccountParams.setSubAccountType(SubAccountTypeEnum.EQUITY_SUB_ACCOUNT);

        try
        {
            TestDBUtility.deleteAll(MainAccountParams.TYPE);
            TestDBUtility.deleteAll(SubAccountParams.TYPE);
            TestDBUtility.deleteAll(StockSecuredLoanParams.TYPE);

            TestDBUtility.insertWithDel(l_mainAccountParams);
            TestDBUtility.insertWithDel(l_subAccountParams);
        }
        catch (Exception l_ex)
        {
            log.debug(l_ex.getMessage(), l_ex);
            log.exiting(TEST_END + STR_METHOD_NAME);
            fail();
        }

        try
        {
            WEB3SLRepayCancelListRequest l_slRepayCancelListRequest =
                new WEB3SLRepayCancelListRequest();

            WEB3GentradeTradingTimeManagementForMock.mockValidateOrderAccept(true);

            TestBaseForMock.MOCK_MANAGER.setIsMockUsed(true);
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                "com.fitechlabs.xtrade.plugin.security.oplogin.impl."
                + "OpLoginSecurityServiceImpl",
                "getAccountId",
                new Class[] {},
                new Long(l_mainAccountParams.getAccountId()));

            l_aioSLRepayListServiceImpl.execute(l_slRepayCancelListRequest);

            log.exiting(TEST_END + STR_METHOD_NAME);
            fail();
        }
        catch (WEB3BaseException l_ex)
        {
            assertEquals(WEB3ErrorCatalog.BUSINESS_ERROR_02914, l_ex.getErrorInfo());
            log.debug(l_ex.getMessage(), l_ex);
            log.exiting(TEST_END + STR_METHOD_NAME);
        }
        catch (Exception l_ex)
        {
            log.debug(l_ex.getMessage(), l_ex);
            log.exiting(TEST_END + STR_METHOD_NAME);
            fail();
        }
        finally
        {

            try
            {
                TestDBUtility.deleteAll(MainAccountParams.TYPE);
                TestDBUtility.deleteAll(SubAccountParams.TYPE);
                TestDBUtility.deleteAll(StockSecuredLoanParams.TYPE);
            }
            catch (Exception l_ex)
            {
                log.debug(l_ex.getMessage(), l_ex);
                log.exiting(TEST_END + STR_METHOD_NAME);
                fail();
            }
        }
    }

    /**
     *
     */
    public void testExecute_case0003()
    {
        String STR_METHOD_NAME = " testExecute_case0003()";
        log.entering(TEST_START + STR_METHOD_NAME);

        l_aioSLRepayListServiceImpl = new WEB3AioSLRepayListServiceImpl();

        MainAccountParams l_mainAccountParams = TestDBUtility.getMainAccountRow();
        l_mainAccountParams.setSecuredLoanSecAccOpenDiv(
            WEB3SecuredLoanSecAccOpenDivDef.OPEN);
        SubAccountParams l_subAccountParams = TestDBUtility.getSubAccountRow();
        l_subAccountParams.setAccountId(l_mainAccountParams.getAccountId());
        l_subAccountParams.setSubAccountType(SubAccountTypeEnum.EQUITY_SUB_ACCOUNT);
        StockSecuredLoanParams l_stockSecuredLoanParams =
            TestDBUtility.getStockSecuredLoanRow();
        l_stockSecuredLoanParams.setAccountId(l_mainAccountParams.getAccountId());
        l_stockSecuredLoanParams.setAccountOpenStatus(WEB3AccountOpenStatusDef.PROMISE);

        try
        {
            TestDBUtility.deleteAll(MainAccountParams.TYPE);
            TestDBUtility.deleteAll(SubAccountParams.TYPE);
            TestDBUtility.deleteAll(StockSecuredLoanParams.TYPE);
            TestDBUtility.deleteAll(AioOrderUnitParams.TYPE);

            TestDBUtility.insertWithDel(l_mainAccountParams);
            TestDBUtility.insertWithDel(l_subAccountParams);
            TestDBUtility.insertWithDel(l_stockSecuredLoanParams);
        }
        catch (Exception l_ex)
        {
            log.debug(l_ex.getMessage(), l_ex);
            log.exiting(TEST_END + STR_METHOD_NAME);
            fail();
        }

        try
        {
            WEB3SLRepayCancelListRequest l_slRepayCancelListRequest =
                new WEB3SLRepayCancelListRequest();

            WEB3GentradeTradingTimeManagementForMock.mockValidateOrderAccept(true);

            TestBaseForMock.MOCK_MANAGER.setIsMockUsed(true);
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                "com.fitechlabs.xtrade.plugin.security.oplogin.impl."
                + "OpLoginSecurityServiceImpl",
                "getAccountId",
                new Class[] {},
                new Long(l_mainAccountParams.getAccountId()));

            WEB3SLRepayCancelListResponse l_slRepayCancelListResponse =
                (WEB3SLRepayCancelListResponse)l_aioSLRepayListServiceImpl.execute(
                    l_slRepayCancelListRequest);

            assertNotNull(l_slRepayCancelListResponse);
            assertNotNull(l_slRepayCancelListResponse.stockLoanRepayUnits);
            WEB3SLRepayUnit[] l_actualSLRepayUnits =
                l_slRepayCancelListResponse.stockLoanRepayUnits;
            assertEquals(0, l_actualSLRepayUnits.length);

            log.exiting(TEST_END + STR_METHOD_NAME);
        }
        catch (WEB3BaseException l_ex)
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
        finally
        {
            try
            {
                TestDBUtility.deleteAll(MainAccountParams.TYPE);
                TestDBUtility.deleteAll(SubAccountParams.TYPE);
                TestDBUtility.deleteAll(StockSecuredLoanParams.TYPE);
                TestDBUtility.deleteAll(AioOrderUnitParams.TYPE);
            }
            catch (Exception l_ex)
            {
                log.debug(l_ex.getMessage(), l_ex);
                log.exiting(TEST_END + STR_METHOD_NAME);
                fail();
            }
        }
    }

    /**
     *
     */
    public void testExecute_case0004()
    {
        String STR_METHOD_NAME = " testExecute_case0004()";
        log.entering(TEST_START + STR_METHOD_NAME);

        l_aioSLRepayListServiceImpl = new WEB3AioSLRepayListServiceImpl();

        MainAccountParams l_mainAccountParams = TestDBUtility.getMainAccountRow();
        l_mainAccountParams.setSecuredLoanSecAccOpenDiv(
            WEB3SecuredLoanSecAccOpenDivDef.OPEN);
        SubAccountParams l_subAccountParams = TestDBUtility.getSubAccountRow();
        l_subAccountParams.setAccountId(l_mainAccountParams.getAccountId());
        l_subAccountParams.setSubAccountType(SubAccountTypeEnum.EQUITY_SUB_ACCOUNT);
        StockSecuredLoanParams l_stockSecuredLoanParams =
            TestDBUtility.getStockSecuredLoanRow();
        l_stockSecuredLoanParams.setAccountId(l_mainAccountParams.getAccountId());
        l_stockSecuredLoanParams.setAccountOpenStatus(WEB3AccountOpenStatusDef.PROMISE);
        AioOrderUnitParams l_aioOrderUnitParams = TestDBUtility.getAioOrderUnitRow();
        l_aioOrderUnitParams.setOrderId(321654001L);
        l_aioOrderUnitParams.setAccountId(l_mainAccountParams.getAccountId());
        l_aioOrderUnitParams.setSubAccountId(l_subAccountParams.getSubAccountId());
        l_aioOrderUnitParams.setOrderType(OrderTypeEnum.TO_ORIX_CREDIT);
        l_aioOrderUnitParams.setReceivedDateTime(
            WEB3DateUtility.getDate("20070924", "yyyyMMdd"));
        l_aioOrderUnitParams.setEstTransferDate(
            WEB3DateUtility.getDate("20070925", "yyyyMMdd"));
        l_aioOrderUnitParams.setQuantity(120.8D);
        l_aioOrderUnitParams.setMqStatus("0");
        l_aioOrderUnitParams.setOrderStatus(OrderStatusEnum.CANCELLED);
        l_aioOrderUnitParams.setOrderOpenStatus(OrderOpenStatusEnum.CLOSED);

        try
        {
            TestDBUtility.deleteAll(MainAccountParams.TYPE);
            TestDBUtility.deleteAll(SubAccountParams.TYPE);
            TestDBUtility.deleteAll(StockSecuredLoanParams.TYPE);
            TestDBUtility.deleteAll(AioOrderUnitParams.TYPE);

            TestDBUtility.insertWithDel(l_mainAccountParams);
            TestDBUtility.insertWithDel(l_subAccountParams);
            TestDBUtility.insertWithDel(l_stockSecuredLoanParams);
            TestDBUtility.insertWithDel(l_aioOrderUnitParams);
        }
        catch (Exception l_ex)
        {
            log.debug(l_ex.getMessage(), l_ex);
            log.exiting(TEST_END + STR_METHOD_NAME);
            fail();
        }

        try
        {

            WEB3SLRepayCancelListRequest l_slRepayCancelListRequest =
                new WEB3SLRepayCancelListRequest();

            WEB3GentradeTradingTimeManagementForMock.mockValidateOrderAccept(true);

            TestBaseForMock.MOCK_MANAGER.setIsMockUsed(true);
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                "com.fitechlabs.xtrade.plugin.security.oplogin.impl."
                + "OpLoginSecurityServiceImpl",
                "getAccountId",
                new Class[] {},
                new Long(l_mainAccountParams.getAccountId()));

            WEB3SLRepayCancelListResponse l_slRepayCancelListResponse =
                (WEB3SLRepayCancelListResponse)l_aioSLRepayListServiceImpl.execute(
                    l_slRepayCancelListRequest);

            assertNotNull(l_slRepayCancelListResponse);
            assertNotNull(l_slRepayCancelListResponse.stockLoanRepayUnits);
            WEB3SLRepayUnit[] l_actualSLRepayUnits =
                l_slRepayCancelListResponse.stockLoanRepayUnits;
            assertEquals(1, l_actualSLRepayUnits.length);
            WEB3SLRepayUnit l_actualSLRepayUnit = l_actualSLRepayUnits[0];
            assertEquals("321654001", l_actualSLRepayUnit.orderId);
            assertEquals(
                WEB3DateUtility.getDate("20070924", "yyyyMMdd"),
                l_actualSLRepayUnit.receptionDate);
            assertEquals(
                WEB3DateUtility.getDate("20070925", "yyyyMMdd"),
                l_actualSLRepayUnit.repayScheduledDate);
            assertEquals("120.8", l_actualSLRepayUnit.repayAmt);
            assertEquals("3", l_actualSLRepayUnit.cancelFlag);

            log.exiting(TEST_END + STR_METHOD_NAME);
        }
        catch (WEB3BaseException l_ex)
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
        finally
        {
            try
            {
                TestDBUtility.deleteAll(MainAccountParams.TYPE);
                TestDBUtility.deleteAll(SubAccountParams.TYPE);
                TestDBUtility.deleteAll(StockSecuredLoanParams.TYPE);
                TestDBUtility.deleteAll(AioOrderUnitParams.TYPE);
            }
            catch (Exception l_ex)
            {
                log.debug(l_ex.getMessage(), l_ex);
                log.exiting(TEST_END + STR_METHOD_NAME);
                fail();
            }
        }
    }

    /**
     *
     */
    public void testExecute_case0005()
    {
        String STR_METHOD_NAME = " testExecute_case0005()";
        log.entering(TEST_START + STR_METHOD_NAME);

        l_aioSLRepayListServiceImpl = new WEB3AioSLRepayListServiceImpl();

        MainAccountParams l_mainAccountParams = TestDBUtility.getMainAccountRow();
        l_mainAccountParams.setSecuredLoanSecAccOpenDiv(
            WEB3SecuredLoanSecAccOpenDivDef.OPEN);
        SubAccountParams l_subAccountParams = TestDBUtility.getSubAccountRow();
        l_subAccountParams.setAccountId(l_mainAccountParams.getAccountId());
        l_subAccountParams.setSubAccountType(SubAccountTypeEnum.EQUITY_SUB_ACCOUNT);
        StockSecuredLoanParams l_stockSecuredLoanParams =
            TestDBUtility.getStockSecuredLoanRow();
        l_stockSecuredLoanParams.setAccountId(l_mainAccountParams.getAccountId());
        l_stockSecuredLoanParams.setAccountOpenStatus(WEB3AccountOpenStatusDef.PROMISE);

        try
        {
            TestDBUtility.deleteAll(MainAccountParams.TYPE);
            TestDBUtility.deleteAll(SubAccountParams.TYPE);
            TestDBUtility.deleteAll(StockSecuredLoanParams.TYPE);
            TestDBUtility.deleteAll(AioOrderUnitParams.TYPE);

            TestDBUtility.insertWithDel(l_mainAccountParams);
            TestDBUtility.insertWithDel(l_subAccountParams);
            TestDBUtility.insertWithDel(l_stockSecuredLoanParams);

            AioOrderUnitParams l_aioOrderUnitParams = TestDBUtility.getAioOrderUnitRow();
            l_aioOrderUnitParams.setOrderUnitId(321654001L);
            l_aioOrderUnitParams.setAccountId(l_mainAccountParams.getAccountId());
            l_aioOrderUnitParams.setSubAccountId(l_subAccountParams.getSubAccountId());
            l_aioOrderUnitParams.setOrderType(OrderTypeEnum.TO_ORIX_CREDIT);
            l_aioOrderUnitParams.setReceivedDateTime(
                WEB3DateUtility.getDate("20070924", "yyyyMMdd"));
            l_aioOrderUnitParams.setEstTransferDate(
                WEB3DateUtility.getDate("20070925", "yyyyMMdd"));
            l_aioOrderUnitParams.setQuantity(120.8D);
            l_aioOrderUnitParams.setMqStatus("0");
            l_aioOrderUnitParams.setOrderStatus(OrderStatusEnum.CANCELLED);
            l_aioOrderUnitParams.setOrderOpenStatus(OrderOpenStatusEnum.CLOSED);

            TestDBUtility.insertWithDel(l_aioOrderUnitParams);

            l_aioOrderUnitParams = TestDBUtility.getAioOrderUnitRow();
            l_aioOrderUnitParams.setOrderUnitId(321654002L);
            l_aioOrderUnitParams.setAccountId(l_mainAccountParams.getAccountId());
            l_aioOrderUnitParams.setSubAccountId(l_subAccountParams.getSubAccountId());
            l_aioOrderUnitParams.setOrderType(OrderTypeEnum.TO_ORIX_CREDIT);
            l_aioOrderUnitParams.setReceivedDateTime(
                WEB3DateUtility.getDate("20070926", "yyyyMMdd"));
            l_aioOrderUnitParams.setEstTransferDate(
                WEB3DateUtility.getDate("20070927", "yyyyMMdd"));
            l_aioOrderUnitParams.setQuantity(44.0D);
            l_aioOrderUnitParams.setMqStatus("0");
            l_aioOrderUnitParams.setOrderStatus(OrderStatusEnum.ORDERED);
            l_aioOrderUnitParams.setOrderOpenStatus(OrderOpenStatusEnum.OPEN);

            TestDBUtility.insertWithDel(l_aioOrderUnitParams);

            l_aioOrderUnitParams = TestDBUtility.getAioOrderUnitRow();
            l_aioOrderUnitParams.setOrderUnitId(321654003L);
            l_aioOrderUnitParams.setAccountId(l_mainAccountParams.getAccountId());
            l_aioOrderUnitParams.setSubAccountId(l_subAccountParams.getSubAccountId());
            l_aioOrderUnitParams.setOrderType(OrderTypeEnum.TO_ORIX_CREDIT);
            l_aioOrderUnitParams.setReceivedDateTime(
                WEB3DateUtility.getDate("20070928", "yyyyMMdd"));
            l_aioOrderUnitParams.setEstTransferDate(
                WEB3DateUtility.getDate("20070929", "yyyyMMdd"));
            l_aioOrderUnitParams.setQuantity(0.123D);
            l_aioOrderUnitParams.setMqStatus("1");

            TestDBUtility.insertWithDel(l_aioOrderUnitParams);
        }
        catch (Exception l_ex)
        {
            log.debug(l_ex.getMessage(), l_ex);
            log.exiting(TEST_END + STR_METHOD_NAME);
            fail();
        }

        try
        {

            WEB3SLRepayCancelListRequest l_slRepayCancelListRequest =
                new WEB3SLRepayCancelListRequest();

            WEB3GentradeTradingTimeManagementForMock.mockValidateOrderAccept(true);

            TestBaseForMock.MOCK_MANAGER.setIsMockUsed(true);
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                "com.fitechlabs.xtrade.plugin.security.oplogin.impl."
                + "OpLoginSecurityServiceImpl",
                "getAccountId",
                new Class[] {},
                new Long(l_mainAccountParams.getAccountId()));

            WEB3SLRepayCancelListResponse l_slRepayCancelListResponse =
                (WEB3SLRepayCancelListResponse)l_aioSLRepayListServiceImpl.execute(
                    l_slRepayCancelListRequest);

            assertNotNull(l_slRepayCancelListResponse);
            assertNotNull(l_slRepayCancelListResponse.stockLoanRepayUnits);
            WEB3SLRepayUnit[] l_actualSLRepayUnits =
                l_slRepayCancelListResponse.stockLoanRepayUnits;
            assertEquals(3, l_actualSLRepayUnits.length);

            WEB3SLRepayUnit l_actualSLRepayUnit = l_actualSLRepayUnits[0];
            assertEquals("2000011", l_actualSLRepayUnit.orderId);
            assertEquals(
                WEB3DateUtility.getDate("20070924", "yyyyMMdd"),
                l_actualSLRepayUnit.receptionDate);
            assertEquals(
                WEB3DateUtility.getDate("20070925", "yyyyMMdd"),
                l_actualSLRepayUnit.repayScheduledDate);
            assertEquals("120.8", l_actualSLRepayUnit.repayAmt);
            assertEquals("3", l_actualSLRepayUnit.cancelFlag);

            l_actualSLRepayUnit = l_actualSLRepayUnits[1];
            assertEquals("2000011", l_actualSLRepayUnit.orderId);
            assertEquals(
                WEB3DateUtility.getDate("20070926", "yyyyMMdd"),
                l_actualSLRepayUnit.receptionDate);
            assertEquals(
                WEB3DateUtility.getDate("20070927", "yyyyMMdd"),
                l_actualSLRepayUnit.repayScheduledDate);
            assertEquals("44", l_actualSLRepayUnit.repayAmt);
            assertEquals("0", l_actualSLRepayUnit.cancelFlag);

            l_actualSLRepayUnit = l_actualSLRepayUnits[2];
            assertEquals("2000011", l_actualSLRepayUnit.orderId);
            assertEquals(
                WEB3DateUtility.getDate("20070928", "yyyyMMdd"),
                l_actualSLRepayUnit.receptionDate);
            assertEquals(
                WEB3DateUtility.getDate("20070929", "yyyyMMdd"),
                l_actualSLRepayUnit.repayScheduledDate);
            assertEquals("0.123", l_actualSLRepayUnit.repayAmt);
            assertEquals("2", l_actualSLRepayUnit.cancelFlag);

            log.exiting(TEST_END + STR_METHOD_NAME);
        }
        catch (WEB3BaseException l_ex)
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
        finally
        {
            try
            {
                TestDBUtility.deleteAll(MainAccountParams.TYPE);
                TestDBUtility.deleteAll(SubAccountParams.TYPE);
                TestDBUtility.deleteAll(StockSecuredLoanParams.TYPE);
                TestDBUtility.deleteAll(AioOrderUnitParams.TYPE);
            }
            catch (Exception l_ex)
            {
                log.debug(l_ex.getMessage(), l_ex);
                log.exiting(TEST_END + STR_METHOD_NAME);
                fail();
            }
        }
    }

}
@
