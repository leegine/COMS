head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.04.07.00.46.45;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	5444d9d078c63f4;
filename	WEB3OptionOrderExecNotifyUnitServiceImplTest.java;


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
Revision History : 2008/07/31 劉剣（中訊）新規作成
*/
package webbroker3.ifo.service.delegate.stdimpls;

import java.sql.Timestamp;
import java.util.Date;

import com.fitechlabs.xtrade.plugin.tc.gentrade.Order;
import com.fitechlabs.xtrade.plugin.tc.gentrade.OrderAction;
import com.fitechlabs.xtrade.plugin.tc.gentrade.OrderCategEnum;
import com.fitechlabs.xtrade.plugin.tc.gentrade.OrderEventTypeEnum;
import com.fitechlabs.xtrade.plugin.tc.gentrade.OrderExecution;
import com.fitechlabs.xtrade.plugin.tc.gentrade.OrderExpirationStatusEnum;
import com.fitechlabs.xtrade.plugin.tc.gentrade.OrderManagerPersistenceContext;
import com.fitechlabs.xtrade.plugin.tc.gentrade.OrderOpenStatusEnum;
import com.fitechlabs.xtrade.plugin.tc.gentrade.OrderStatusEnum;
import com.fitechlabs.xtrade.plugin.tc.gentrade.OrderTypeEnum;
import com.fitechlabs.xtrade.plugin.tc.gentrade.OrderUnit;
import com.fitechlabs.xtrade.plugin.tc.gentrade.ProcessingResult;
import com.fitechlabs.xtrade.plugin.tc.gentrade.Product;
import com.fitechlabs.xtrade.plugin.tc.gentrade.ProductTypeEnum;
import com.fitechlabs.xtrade.plugin.tc.gentrade.SideEnum;
import com.fitechlabs.xtrade.plugin.tc.gentrade.TaxTypeEnum;
import com.fitechlabs.xtrade.plugin.tc.gentrade.TradedProduct;
import com.fitechlabs.xtrade.plugin.tc.gentrade.data.BranchParams;
import com.fitechlabs.xtrade.plugin.tc.gentrade.data.BranchRow;
import com.fitechlabs.xtrade.plugin.tc.gentrade.data.MainAccountParams;
import com.fitechlabs.xtrade.plugin.tc.gentrade.data.MainAccountRow;
import com.fitechlabs.xtrade.plugin.tc.gentrade.data.SubAccountParams;
import com.fitechlabs.xtrade.plugin.tc.gentrade.data.SubAccountRow;
import com.fitechlabs.xtrade.plugin.tc.gentrade.market.messages.OrderFillMarketResponseMessage;
import com.fitechlabs.xtrade.plugin.tc.gentrade.market.messages.UndoOrderFillMarketResponseMessage;
import com.fitechlabs.xtrade.plugin.tc.xbifo.IfoOrderExecutionConditionType;
import com.fitechlabs.xtrade.plugin.tc.xbifo.IfoOrderUnit;
import com.fitechlabs.xtrade.plugin.tc.xbifo.data.IfoOrderUnitParams;
import com.fitechlabs.xtrade.plugin.tc.xbifo.data.IfoOrderUnitRow;

import test.util.TestDBUtility;

import webbroker3.common.WEB3BaseException;
import webbroker3.common.WEB3BusinessLayerException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.common.WEB3SystemLayerException;
import webbroker3.ifo.data.IfoOrderExecSendMailParams;
import webbroker3.ifo.data.IfoOrderExecSendMailRow;
import webbroker3.mock.TestBaseForMock;
import webbroker3.util.WEB3DateUtility;
import webbroker3.util.WEB3LogUtility;

public class WEB3OptionOrderExecNotifyUnitServiceImplTest extends TestBaseForMock
{
    /**
     * ログ出力ユーティリティ。
     */
    private static WEB3LogUtility log =
        WEB3LogUtility.getInstance(WEB3OptionOrderExecNotifyUnitServiceImplTest.class);
    
    private WEB3OptionOrderExecNotifyUnitServiceImpl l_impl = null;
    private boolean l_blnConfirmedQuantity = false;
    private boolean l_blnOrderStatus = false;

    public WEB3OptionOrderExecNotifyUnitServiceImplTest(String arg0)
    {
        super(arg0);
        // TODO Auto-generated constructor stub
    }

    protected void setUp() throws Exception
    {
        super.setUp();
        MOCK_MANAGER.setIsMockUsed(true);
        this.l_impl = new WEB3OptionOrderExecNotifyUnitServiceImpl();
    }

    protected void tearDown() throws Exception
    {
        super.tearDown();
    }
    /*
     * 注文単位オブジェクト = null
     * ?出:WEB3ErrorCatalog.SYSTEM_ERROR_80017
     */
    public void testNotifyExecute_C0001()
    {
        final String STR_METHOD_NAME = "testNotifyExecute_C0001()";
        log.entering(TEST_START + STR_METHOD_NAME);

        try
        {
            OrderUnit l_orderUnit = null;

            Timestamp l_tsExecDate = new Timestamp(WEB3DateUtility.getDate("20080731","yyyyMMdd").getTime());
            double l_dblExecQuantity = 1;
            double l_dblExecPrice = 2;
            String l_strExecutedNotifyDivision = "abc";
            
            l_impl.notifyExecute(
                l_orderUnit, l_tsExecDate, l_dblExecQuantity, l_dblExecPrice, l_strExecutedNotifyDivision);
            
            fail();
        }
        catch (WEB3SystemLayerException l_ex)
        {
            log.debug(STR_METHOD_NAME, l_ex);
            assertEquals(WEB3ErrorCatalog.SYSTEM_ERROR_80017, l_ex.getErrorInfo());
        }
        catch (Exception l_ex)
        {
            log.error(STR_METHOD_NAME, l_ex);
            fail();
        }

        log.exiting(TEST_END + STR_METHOD_NAME);
    }
    /*
     * 注文単位を再取得する失敗。
     * ?出:WEB3ErrorCatalog.SYSTEM_ERROR_80006
     */
    public void testNotifyExecute_C0002()
    {
        final String STR_METHOD_NAME = "testNotifyExecute_C0002()";
        log.entering(TEST_START + STR_METHOD_NAME);

        try
        {
            OrderUnit l_orderUnit = new OrderUnitForTest();
            
            TestDBUtility.deleteAll(IfoOrderUnitRow.TYPE);

            Timestamp l_tsExecDate = new Timestamp(WEB3DateUtility.getDate("20080731","yyyyMMdd").getTime());
            double l_dblExecQuantity = 1;
            double l_dblExecPrice = 2;
            String l_strExecutedNotifyDivision = "abc";
            
            l_impl.notifyExecute(
                l_orderUnit, l_tsExecDate, l_dblExecQuantity, l_dblExecPrice, l_strExecutedNotifyDivision);
            
            fail();
        }
        catch (WEB3SystemLayerException l_ex)
        {
            log.debug(STR_METHOD_NAME, l_ex);
            assertEquals(WEB3ErrorCatalog.SYSTEM_ERROR_80006, l_ex.getErrorInfo());
        }
        catch (Exception l_ex)
        {
            log.error(STR_METHOD_NAME, l_ex);
            fail();
        }

        log.exiting(TEST_END + STR_METHOD_NAME);
    }
    /*
     * 該当注文は受付未済／変更の受付済／発注中の状態。
     * ?出:WEB3ErrorCatalog.BUSINESS_ERROR_01975
     */
    public void testNotifyExecute_C0003()
    {
        final String STR_METHOD_NAME = "testNotifyExecute_C0003()";
        log.entering(TEST_START + STR_METHOD_NAME);

        try
        {
            l_blnConfirmedQuantity = true;
            
            OrderUnit l_orderUnit = new OrderUnitForTest();

            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                    "webbroker3.ifo.WEB3OptionOrderManagerImpl",
                    "getOrderUnit",
                    new Class[] {long.class},
                    l_orderUnit);

            Timestamp l_tsExecDate = new Timestamp(WEB3DateUtility.getDate("20080731","yyyyMMdd").getTime());
            double l_dblExecQuantity = 1;
            double l_dblExecPrice = 2;
            String l_strExecutedNotifyDivision = "abc";
            
            l_impl.notifyExecute(
                l_orderUnit, l_tsExecDate, l_dblExecQuantity, l_dblExecPrice, l_strExecutedNotifyDivision);
            
            fail();
        }
        catch (WEB3BusinessLayerException l_ex)
        {
            log.debug(STR_METHOD_NAME, l_ex);
            assertEquals(WEB3ErrorCatalog.BUSINESS_ERROR_01975, l_ex.getErrorInfo());
        }
        catch (Exception l_ex)
        {
            log.error(STR_METHOD_NAME, l_ex);
            fail();
        }

        log.exiting(TEST_END + STR_METHOD_NAME);
    }
    /*
     * 出来通知の内容で、DBを更新エラー
     */
    public void testNotifyExecute_C0004()
    {
        final String STR_METHOD_NAME = "testNotifyExecute_C0004()";
        log.entering(TEST_START + STR_METHOD_NAME);

        try
        {
            l_blnConfirmedQuantity = false;
            l_blnOrderStatus = false;
            
            OrderUnit l_orderUnit = new OrderUnitForTest();

            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                "webbroker3.ifo.WEB3OptionOrderManagerImpl",
                "getOrderUnit",
                new Class[] {long.class},
                l_orderUnit);
            
            ProcessingResult l_result =
                ProcessingResult.newFailedResultInstance(WEB3ErrorCatalog.BUSINESS_ERROR_00003);
            
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                    "com.fitechlabs.xtrade.plugin.tc.xbifo.stdimpls.IfoMarketResponseReceiverCallbackServiceImpl",
                    "process",
                    new Class[] {OrderFillMarketResponseMessage.class},
                    l_result);
            

            Timestamp l_tsExecDate = new Timestamp(WEB3DateUtility.getDate("20080731","yyyyMMdd").getTime());
            double l_dblExecQuantity = 1;
            double l_dblExecPrice = 2;
            String l_strExecutedNotifyDivision = "abc";
            
            l_impl.notifyExecute(
                l_orderUnit, l_tsExecDate, l_dblExecQuantity, l_dblExecPrice, l_strExecutedNotifyDivision);
            
            fail();
        }
        catch (WEB3BaseException l_ex)
        {
            log.debug(STR_METHOD_NAME, l_ex);
            assertEquals(WEB3ErrorCatalog.BUSINESS_ERROR_00003, l_ex.getErrorInfo());
        }
        catch (Exception l_ex)
        {
            log.error(STR_METHOD_NAME, l_ex);
            fail();
        }

        log.exiting(TEST_END + STR_METHOD_NAME);
    }
    /*
     * get補助口座失敗的場合
     * ?出:WEB3ErrorCatalog.SYSTEM_ERROR_80006
     */
    public void testNotifyExecute_C0005()
    {
        final String STR_METHOD_NAME = "testNotifyExecute_C0005()";
        log.entering(TEST_START + STR_METHOD_NAME);

        try
        {
            l_blnConfirmedQuantity = false;
            l_blnOrderStatus = false;
            
            OrderUnit l_orderUnit = new OrderUnitForTest();

            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                "webbroker3.ifo.WEB3OptionOrderManagerImpl",
                "getOrderUnit",
                new Class[] {long.class},
                l_orderUnit);
            
            TestDBUtility.deleteAll(SubAccountRow.TYPE);

            Timestamp l_tsExecDate = new Timestamp(WEB3DateUtility.getDate("20080731","yyyyMMdd").getTime());
            double l_dblExecQuantity = 1;
            double l_dblExecPrice = 2;
            String l_strExecutedNotifyDivision = "abc";
            
            l_impl.notifyExecute(
                l_orderUnit, l_tsExecDate, l_dblExecQuantity, l_dblExecPrice, l_strExecutedNotifyDivision);
            
            fail();
        }
        catch (WEB3SystemLayerException l_ex)
        {
            log.debug(STR_METHOD_NAME, l_ex);
            assertEquals(WEB3ErrorCatalog.SYSTEM_ERROR_80006, l_ex.getErrorInfo());
        }
        catch (Exception l_ex)
        {
            log.error(STR_METHOD_NAME, l_ex);
            fail();
        }

        log.exiting(TEST_END + STR_METHOD_NAME);
    }
    /*
     * notifyルールエンジンサーバ()にて業務エラーがスローされた場合、※ロールバックしない。
     */
    public void testNotifyExecute_C0006()
    {
        final String STR_METHOD_NAME = "testNotifyExecute_C0006()";
        log.entering(TEST_START + STR_METHOD_NAME);

        try
        {
            l_blnConfirmedQuantity = false;
            l_blnOrderStatus = false;
            
            OrderUnit l_orderUnit = new OrderUnitForTest();

            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                "webbroker3.ifo.WEB3OptionOrderManagerImpl",
                "getOrderUnit",
                new Class[] {long.class},
                l_orderUnit);
            
            TestDBUtility.deleteAll(SubAccountRow.TYPE);
            SubAccountParams l_subAccountParams = TestDBUtility.getSubAccountRow();
            TestDBUtility.insertWithDel(l_subAccountParams);
            
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                "webbroker3.ifo.service.delegate.stdimpls.WEB3IfoExecutedMailSendServiceImpl",
                "sendMailProcess",
                new Class[]
                { OrderUnit.class, String.class },
                null);
            
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue("webbroker3.ifo.WEB3OptionOrderManagerImpl",
                "notifyRLS",
                new Class[]
                {IfoOrderUnit.class, OrderManagerPersistenceContext.class},
                new WEB3BusinessLayerException(WEB3ErrorCatalog.BUSINESS_ERROR_00003,
                    "notifyルールエンジンサーバ()にて業務エラーがスローされた場合、※ロールバックしない。"));

            Timestamp l_tsExecDate = new Timestamp(WEB3DateUtility.getDate("20080731","yyyyMMdd").getTime());
            double l_dblExecQuantity = 1;
            double l_dblExecPrice = 2;
            String l_strExecutedNotifyDivision = "abc";
            
            l_impl.notifyExecute(
                l_orderUnit, l_tsExecDate, l_dblExecQuantity, l_dblExecPrice, l_strExecutedNotifyDivision);
            
            assertTrue(true);
        }
        catch (Exception l_ex)
        {
            log.error(STR_METHOD_NAME, l_ex);
            fail();
        }

        log.exiting(TEST_END + STR_METHOD_NAME);
    }
    /*
     * normal case
     */
    public void testNotifyExecute_C0007()
    {
        final String STR_METHOD_NAME = "testNotifyExecute_C0007()";
        log.entering(TEST_START + STR_METHOD_NAME);

        try
        {
            l_blnConfirmedQuantity = false;
            l_blnOrderStatus = false;
            
            OrderUnit l_orderUnit = new OrderUnitForTest();

            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                "webbroker3.ifo.WEB3OptionOrderManagerImpl",
                "getOrderUnit",
                new Class[] {long.class},
                l_orderUnit);
            
            TestDBUtility.deleteAll(SubAccountRow.TYPE);
            SubAccountParams l_subAccountParams = TestDBUtility.getSubAccountRow();
            TestDBUtility.insertWithDel(l_subAccountParams);
            
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                "webbroker3.ifo.service.delegate.stdimpls.WEB3IfoExecutedMailSendServiceImpl",
                "sendMailProcess",
                new Class[]
                { OrderUnit.class, String.class },
                null);

            Timestamp l_tsExecDate = new Timestamp(WEB3DateUtility.getDate("20080731","yyyyMMdd").getTime());
            double l_dblExecQuantity = 1;
            double l_dblExecPrice = 2;
            String l_strExecutedNotifyDivision = "abc";
            
            l_impl.notifyExecute(
                l_orderUnit, l_tsExecDate, l_dblExecQuantity, l_dblExecPrice, l_strExecutedNotifyDivision);
            
            assertTrue(true);
        }
        catch (Exception l_ex)
        {
            log.error(STR_METHOD_NAME, l_ex);
            fail();
        }

        log.exiting(TEST_END + STR_METHOD_NAME);
    }
    /*
     * 注文単位オブジェクト = null
     * ?出:WEB3ErrorCatalog.SYSTEM_ERROR_80017
     */
    public void testNotifyExecuteCancel_C0001()
    {
        final String STR_METHOD_NAME = "testNotifyExecuteCancel_C0001()";
        log.entering(TEST_START + STR_METHOD_NAME);

        try
        {
            OrderUnit l_orderUnit = null;

            Timestamp l_tsExecDate = new Timestamp(WEB3DateUtility.getDate("20080731","yyyyMMdd").getTime());
            double l_dblExecQuantity = 1;
            double l_dblExecPrice = 2;
            
            l_impl.notifyExecuteCancel(l_orderUnit, l_tsExecDate, l_dblExecQuantity, l_dblExecPrice);
            
            fail();
        }
        catch (WEB3SystemLayerException l_ex)
        {
            log.debug(STR_METHOD_NAME, l_ex);
            assertEquals(WEB3ErrorCatalog.SYSTEM_ERROR_80017, l_ex.getErrorInfo());
        }
        catch (Exception l_ex)
        {
            log.error(STR_METHOD_NAME, l_ex);
            fail();
        }

        log.exiting(TEST_END + STR_METHOD_NAME);
    }
    /*
     * 注文単位を再取得する失敗。
     * ?出:WEB3ErrorCatalog.SYSTEM_ERROR_80006
     */
    public void testNotifyExecuteCancel_C0002()
    {
        final String STR_METHOD_NAME = "testNotifyExecuteCancel_C0002()";
        log.entering(TEST_START + STR_METHOD_NAME);

        try
        {
            OrderUnit l_orderUnit = new OrderUnitForTest();
            
            TestDBUtility.deleteAll(IfoOrderUnitRow.TYPE);

            Timestamp l_tsExecDate = new Timestamp(WEB3DateUtility.getDate("20080731","yyyyMMdd").getTime());
            double l_dblExecQuantity = 1;
            double l_dblExecPrice = 2;
            
            l_impl.notifyExecuteCancel(l_orderUnit, l_tsExecDate, l_dblExecQuantity, l_dblExecPrice);
            
            fail();
        }
        catch (WEB3SystemLayerException l_ex)
        {
            log.debug(STR_METHOD_NAME, l_ex);
            assertEquals(WEB3ErrorCatalog.SYSTEM_ERROR_80006, l_ex.getErrorInfo());
        }
        catch (Exception l_ex)
        {
            log.error(STR_METHOD_NAME, l_ex);
            fail();
        }

        log.exiting(TEST_END + STR_METHOD_NAME);
    }
    /*
     * 該当注文は受付未済／変更の受付済／発注中の状態。
     * ?出:WEB3ErrorCatalog.BUSINESS_ERROR_01975
     */
    public void testNotifyExecuteCancel_C0003()
    {
        final String STR_METHOD_NAME = "testNotifyExecuteCancel_C0003()";
        log.entering(TEST_START + STR_METHOD_NAME);

        try
        {
            l_blnConfirmedQuantity = true;
            
            OrderUnit l_orderUnit = new OrderUnitForTest();

            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                    "webbroker3.ifo.WEB3OptionOrderManagerImpl",
                    "getOrderUnit",
                    new Class[] {long.class},
                    l_orderUnit);

            Timestamp l_tsExecDate = new Timestamp(WEB3DateUtility.getDate("20080731","yyyyMMdd").getTime());
            double l_dblExecQuantity = 1;
            double l_dblExecPrice = 2;
            
            l_impl.notifyExecuteCancel(l_orderUnit, l_tsExecDate, l_dblExecQuantity, l_dblExecPrice);
            
            fail();
        }
        catch (WEB3BusinessLayerException l_ex)
        {
            log.debug(STR_METHOD_NAME, l_ex);
            assertEquals(WEB3ErrorCatalog.BUSINESS_ERROR_01975, l_ex.getErrorInfo());
        }
        catch (Exception l_ex)
        {
            log.error(STR_METHOD_NAME, l_ex);
            fail();
        }

        log.exiting(TEST_END + STR_METHOD_NAME);
    }
    /*
     * get補助口座失敗的場合
     * ?出:WEB3ErrorCatalog.SYSTEM_ERROR_80006
     */
    public void testNotifyExecuteCancel_C0004()
    {
        final String STR_METHOD_NAME = "testNotifyExecuteCancel_C0004()";
        log.entering(TEST_START + STR_METHOD_NAME);

        try
        {
            l_blnConfirmedQuantity = false;
            l_blnOrderStatus = false;
            
            OrderUnit l_orderUnit = new OrderUnitForTest();

            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                    "webbroker3.ifo.WEB3OptionOrderManagerImpl",
                    "getOrderUnit",
                    new Class[] {long.class},
                    l_orderUnit);
            
            ProcessingResult l_result =
                ProcessingResult.newSuccessResultInstance();
            
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                    "com.fitechlabs.xtrade.plugin.tc.xbifo.stdimpls.IfoMarketResponseReceiverCallbackServiceImpl",
                    "process",
                    new Class[] {UndoOrderFillMarketResponseMessage.class},
                    l_result);

            TestDBUtility.deleteAll(SubAccountRow.TYPE);
            
            Timestamp l_tsExecDate = new Timestamp(WEB3DateUtility.getDate("20080731","yyyyMMdd").getTime());
            double l_dblExecQuantity = 1;
            double l_dblExecPrice = 2;
            
            l_impl.notifyExecuteCancel(l_orderUnit, l_tsExecDate, l_dblExecQuantity, l_dblExecPrice);
            
            fail();
        }
        catch (WEB3SystemLayerException l_ex)
        {
            log.debug(STR_METHOD_NAME, l_ex);
            assertEquals(WEB3ErrorCatalog.SYSTEM_ERROR_80006, l_ex.getErrorInfo());
        }
        catch (Exception l_ex)
        {
            log.error(STR_METHOD_NAME, l_ex);
            fail();
        }

        log.exiting(TEST_END + STR_METHOD_NAME);
    }
    /*
     * normal case
     */
    public void testNotifyExecuteCancel_C0005()
    {
        final String STR_METHOD_NAME = "testNotifyExecuteCancel_C0005()";
        log.entering(TEST_START + STR_METHOD_NAME);

        try
        {
            l_blnConfirmedQuantity = false;
            l_blnOrderStatus = false;
            
            OrderUnit l_orderUnit = new OrderUnitForTest();

            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                    "webbroker3.ifo.WEB3OptionOrderManagerImpl",
                    "getOrderUnit",
                    new Class[] {long.class},
                    l_orderUnit);
            
            ProcessingResult l_result =
                ProcessingResult.newSuccessResultInstance();
            
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                    "com.fitechlabs.xtrade.plugin.tc.xbifo.stdimpls.IfoMarketResponseReceiverCallbackServiceImpl",
                    "process",
                    new Class[] {UndoOrderFillMarketResponseMessage.class},
                    l_result);

            TestDBUtility.deleteAll(SubAccountRow.TYPE);
            SubAccountParams l_SubAccountParams = TestDBUtility.getSubAccountRow();
            TestDBUtility.insertWithDel(l_SubAccountParams);
            
            TestDBUtility.deleteAll(BranchRow.TYPE);
            BranchParams l_branchParams = TestDBUtility.getBranchRow();
            TestDBUtility.insertWithDel(l_branchParams);
            
            TestDBUtility.deleteAll(MainAccountRow.TYPE);
            MainAccountParams l_mainAccountParams = TestDBUtility.getMainAccountRow();
            l_mainAccountParams.setAccountId(333812512203L);
            TestDBUtility.insertWithDel(l_mainAccountParams);
            
            TestDBUtility.deleteAll(IfoOrderExecSendMailRow.TYPE);
            IfoOrderExecSendMailParams l_ifoOrderExecSendMailParams = TestDBUtility.getIfoOrderExecSendMailRow();
            TestDBUtility.insertWithDel(l_ifoOrderExecSendMailParams);
            
            Timestamp l_tsExecDate = new Timestamp(WEB3DateUtility.getDate("20080731","yyyyMMdd").getTime());
            double l_dblExecQuantity = 1;
            double l_dblExecPrice = 2;
            
            l_impl.notifyExecuteCancel(l_orderUnit, l_tsExecDate, l_dblExecQuantity, l_dblExecPrice);
            
            assertTrue(true);
        }
        catch (Exception l_ex)
        {
            log.error(STR_METHOD_NAME, l_ex);
            fail();
        }

        log.exiting(TEST_END + STR_METHOD_NAME);
    }
    /*
     * 市場から確認済みの数量==nullの場合、
     * ?出:WEB3ErrorCatalog.BUSINESS_ERROR_01975
     */
    public void testValidateOrderStatus_C0001()
    {
        final String STR_METHOD_NAME = "testValidateOrderStatus_C0001()";
        log.entering(TEST_START + STR_METHOD_NAME);

        try
        {
            l_blnConfirmedQuantity = true;
            
            OrderUnit l_orderUnit = new OrderUnitForTest();

            l_impl.validateOrderStatus(l_orderUnit);
            
            fail();
        }
        catch (WEB3BusinessLayerException l_ex)
        {
            log.debug(STR_METHOD_NAME, l_ex);
            assertEquals(WEB3ErrorCatalog.BUSINESS_ERROR_01975, l_ex.getErrorInfo());
        }
        catch (Exception l_ex)
        {
            log.error(STR_METHOD_NAME, l_ex);
            fail();
        }

        log.exiting(TEST_END + STR_METHOD_NAME);
    }
    /*
     * 注文状態のチェック：以下のいずれかに該当する場合、
     * 　@　@　@　@ACCEPTED：受付済（新規注文）
     * 　@　@　@　@MODIFY_ACCEPTED：受付済（変更注文）
     * 　@　@　@　@MODIFYING：発注中（変更注文）
     * ?出:WEB3ErrorCatalog.BUSINESS_ERROR_01975
     */
    public void testValidateOrderStatus_C0002()
    {
        final String STR_METHOD_NAME = "testValidateOrderStatus_C0002()";
        log.entering(TEST_START + STR_METHOD_NAME);

        try
        {
            l_blnConfirmedQuantity = false;
            l_blnOrderStatus = true;
            
            OrderUnit l_orderUnit = new OrderUnitForTest();

            l_impl.validateOrderStatus(l_orderUnit);
            
            fail();
        }
        catch (WEB3BusinessLayerException l_ex)
        {
            log.debug(STR_METHOD_NAME, l_ex);
            assertEquals(WEB3ErrorCatalog.BUSINESS_ERROR_01975, l_ex.getErrorInfo());
        }
        catch (Exception l_ex)
        {
            log.error(STR_METHOD_NAME, l_ex);
            fail();
        }

        log.exiting(TEST_END + STR_METHOD_NAME);
    }
    /*
     * normal case
     */
    public void testValidateOrderStatus_C0003()
    {
        final String STR_METHOD_NAME = "testValidateOrderStatus_C0003()";
        log.entering(TEST_START + STR_METHOD_NAME);

        try
        {
            l_blnConfirmedQuantity = false;
            l_blnOrderStatus = false;
            
            OrderUnit l_orderUnit = new OrderUnitForTest();

            l_impl.validateOrderStatus(l_orderUnit);
            
            assertTrue(true);
        }
        catch (Exception l_ex)
        {
            log.error(STR_METHOD_NAME, l_ex);
            fail();
        }

        log.exiting(TEST_END + STR_METHOD_NAME);
    }
    
    private class OrderUnitForTest implements IfoOrderUnit
    {

        public ProductTypeEnum getProductType()
        {
            // TODO Auto-generated method stub
            return null;
        }

        public boolean isFuturesOrder()
        {
            // TODO Auto-generated method stub
            return false;
        }

        public boolean isOptionsOrder()
        {
            // TODO Auto-generated method stub
            return false;
        }

        public boolean isCallOptionsOrder()
        {
            // TODO Auto-generated method stub
            return false;
        }

        public boolean isPutOptionsOrder()
        {
            // TODO Auto-generated method stub
            return false;
        }

        public boolean isOpenContractOrder()
        {
            // TODO Auto-generated method stub
            return false;
        }

        public boolean isSettleContractOrder()
        {
            // TODO Auto-generated method stub
            return false;
        }

        public IfoOrderExecutionConditionType getExecutionConditionType()
        {
            // TODO Auto-generated method stub
            return null;
        }

        public long getOrderUnitId()
        {
            // TODO Auto-generated method stub
            return 1001;
        }

        public long getOrderId()
        {
            // TODO Auto-generated method stub
            return 1001;
        }

        public long getAccountId()
        {
            // TODO Auto-generated method stub
            return 333812512203L;
        }

        public long getSubAccountId()
        {
            // TODO Auto-generated method stub
            return 33381251220301L;
        }

        public long getBranchId()
        {
            // TODO Auto-generated method stub
            return 33381;
        }

        public long getTraderId()
        {
            // TODO Auto-generated method stub
            return 0;
        }

        public OrderAction[] getOrderActions()
        {
            OrderAction[] l_orderAction = new OrderActionForTest[1];
            l_orderAction[0] = new OrderActionForTest();
            
            return l_orderAction;
        }

        public Timestamp getReceivedTimestamp()
        {
            // TODO Auto-generated method stub
            return null;
        }

        public Timestamp getExpirationTimestamp()
        {
            Timestamp l_tsExecutionTimeTemp =
                new Timestamp(WEB3DateUtility.getDate("20080731","yyyyMMdd").getTime());
            
            return l_tsExecutionTimeTemp;
        }

        public Product getProduct()
        {
            // TODO Auto-generated method stub
            return null;
        }

        public Order getOrder()
        {
            // TODO Auto-generated method stub
            return null;
        }

        public OrderExecution[] getExecutions()
        {
            OrderExecution[] l_orderExecution = new OrderExecutionForTest[1];
            l_orderExecution[0] = new OrderExecutionForTest();
            
            return l_orderExecution;
        }

        public OrderOpenStatusEnum getOrderOpenStatus()
        {
            // TODO Auto-generated method stub
            return null;
        }

        public OrderTypeEnum getOrderType()
        {
            // TODO Auto-generated method stub
            return null;
        }

        public OrderCategEnum getOrderCateg()
        {
            // TODO Auto-generated method stub
            return OrderCategEnum.IDX_FUTURES_OPEN;
        }

        public TaxTypeEnum getTaxType()
        {
            // TODO Auto-generated method stub
            return null;
        }

        public SideEnum getSide()
        {
            // TODO Auto-generated method stub
            return null;
        }

        public OrderStatusEnum getOrderStatus()
        {
            // TODO Auto-generated method stub
            return null;
        }

        public OrderExpirationStatusEnum getExpirationStatus()
        {
            // TODO Auto-generated method stub
            return null;
        }

        public boolean isExpired()
        {
            // TODO Auto-generated method stub
            return false;
        }

        public boolean isFullyExecuted()
        {
            // TODO Auto-generated method stub
            return true;
        }

        public boolean isPartiallyExecuted()
        {
            // TODO Auto-generated method stub
            return false;
        }

        public boolean isUnexecuted()
        {
            // TODO Auto-generated method stub
            return false;
        }

        public double getConfirmedPrice()
        {
            // TODO Auto-generated method stub
            return 0;
        }

        public boolean isConfirmedPriceMarketOrder()
        {
            // TODO Auto-generated method stub
            return false;
        }

        public double getConfirmedQuantity()
        {
            // TODO Auto-generated method stub
            return 0;
        }

        public double getQuantity()
        {
            // TODO Auto-generated method stub
            return 0;
        }

        public Date getDeliveryDate()
        {
            // TODO Auto-generated method stub
            return null;
        }

        public double getExecutedAmount()
        {
            // TODO Auto-generated method stub
            return 0;
        }

        public double getExecutedQuantity()
        {
            // TODO Auto-generated method stub
            return 0;
        }

        public double getLimitPrice()
        {
            // TODO Auto-generated method stub
            return 0;
        }

        public boolean isMarketOrder()
        {
            // TODO Auto-generated method stub
            return false;
        }

        public TradedProduct getTradedProduct()
        {
            // TODO Auto-generated method stub
            return null;
        }

        public Object getDataSourceObject()
        {
            IfoOrderUnitParams l_ifoOrderUnitParams = null;
            
            try
            {
                TestDBUtility.deleteAll(IfoOrderUnitRow.TYPE);
                l_ifoOrderUnitParams = TestDBUtility.getIfoOrderUnitRow();
                l_ifoOrderUnitParams.setPrice(11);
                l_ifoOrderUnitParams.setExecutedAmount(10);
                l_ifoOrderUnitParams.setConfirmedQuantity(1);
                l_ifoOrderUnitParams.setOrderStatus(OrderStatusEnum.UNDEFINED);

                if (l_blnConfirmedQuantity)
                {
                    l_ifoOrderUnitParams.setConfirmedQuantity(0);
                }

                if (l_blnOrderStatus)
                {
                    l_ifoOrderUnitParams.setOrderStatus(OrderStatusEnum.ACCEPTED);
                }

                TestDBUtility.insertWithDel(l_ifoOrderUnitParams);
            }
            catch (WEB3BaseException l_ex)
            {
                fail();
            }

            return l_ifoOrderUnitParams;
        }
        
    }
    
    private class OrderExecutionForTest implements OrderExecution
    {

        public long getAccountId()
        {
            // TODO Auto-generated method stub
            return 0;
        }

        public long getBranchId()
        {
            // TODO Auto-generated method stub
            return 0;
        }

        public long getTraderId()
        {
            // TODO Auto-generated method stub
            return 0;
        }

        public Date getDeliveryDate()
        {
            // TODO Auto-generated method stub
            return null;
        }

        public double getExecutionPrice()
        {
            // TODO Auto-generated method stub
            return 2;
        }

        public int getExecutionSerialNo()
        {
            // TODO Auto-generated method stub
            return 0;
        }

        public Timestamp getExecutionTimestamp()
        {
            // TODO Auto-generated method stub
            return null;
        }

        public long getMarketId()
        {
            // TODO Auto-generated method stub
            return 0;
        }

        public long getOrderExecutionId()
        {
            // TODO Auto-generated method stub
            return 0;
        }

        public long getOrderUnitId()
        {
            // TODO Auto-generated method stub
            return 0;
        }

        public Product getProduct()
        {
            // TODO Auto-generated method stub
            return null;
        }

        public double getExecutionQuantity()
        {
            // TODO Auto-generated method stub
            return 1;
        }

        public long getSubAccountId()
        {
            // TODO Auto-generated method stub
            return 0;
        }

        public OrderTypeEnum getOrderType()
        {
            // TODO Auto-generated method stub
            return null;
        }

        public TradedProduct getTradedProduct()
        {
            // TODO Auto-generated method stub
            return null;
        }

        public Object getDataSourceObject()
        {
            // TODO Auto-generated method stub
            return null;
        }
        
    }
    
    private class OrderActionForTest implements OrderAction
    {

        public long getOrderActionId()
        {
            // TODO Auto-generated method stub
            return 2001;
        }

        public long getOrderUnitId()
        {
            // TODO Auto-generated method stub
            return 0;
        }

        public long getOrderId()
        {
            // TODO Auto-generated method stub
            return 0;
        }

        public long getAccountId()
        {
            // TODO Auto-generated method stub
            return 0;
        }

        public long getSubAccountId()
        {
            // TODO Auto-generated method stub
            return 0;
        }

        public Product getProduct()
        {
            // TODO Auto-generated method stub
            return null;
        }

        public Timestamp getOrderActionTimestamp()
        {
            // TODO Auto-generated method stub
            return null;
        }

        public int getOrderActionSerialNo()
        {
            // TODO Auto-generated method stub
            return 1;
        }

        public boolean isExpired()
        {
            // TODO Auto-generated method stub
            return false;
        }

        public boolean isFullyExecuted()
        {
            // TODO Auto-generated method stub
            return false;
        }

        public boolean isPartiallyExecuted()
        {
            // TODO Auto-generated method stub
            return false;
        }

        public boolean isUnexecuted()
        {
            // TODO Auto-generated method stub
            return false;
        }

        public double getExecutionQuantity()
        {
            // TODO Auto-generated method stub
            return 0;
        }

        public double getExecutionPrice()
        {
            // TODO Auto-generated method stub
            return 0;
        }

        public double getQuantity()
        {
            // TODO Auto-generated method stub
            return 0;
        }

        public double getConfirmedQuantity()
        {
            // TODO Auto-generated method stub
            return 0;
        }

        public SideEnum getSide()
        {
            // TODO Auto-generated method stub
            return null;
        }

        public OrderExpirationStatusEnum getExpirationStatus()
        {
            // TODO Auto-generated method stub
            return null;
        }

        public OrderTypeEnum getOrderType()
        {
            // TODO Auto-generated method stub
            return null;
        }

        public OrderEventTypeEnum getOrderEventType()
        {
            // TODO Auto-generated method stub
            return null;
        }

        public OrderStatusEnum getOrderStatus()
        {
            // TODO Auto-generated method stub
            return null;
        }

        public double getPrice()
        {
            // TODO Auto-generated method stub
            return 0;
        }

        public boolean isMarketOrder()
        {
            // TODO Auto-generated method stub
            return false;
        }

        public double getConfirmedPrice()
        {
            // TODO Auto-generated method stub
            return 0;
        }

        public TradedProduct getTradedProduct()
        {
            // TODO Auto-generated method stub
            return null;
        }

        public Object getDataSourceObject()
        {
            // TODO Auto-generated method stub
            return null;
        }
        
    }
}
@
