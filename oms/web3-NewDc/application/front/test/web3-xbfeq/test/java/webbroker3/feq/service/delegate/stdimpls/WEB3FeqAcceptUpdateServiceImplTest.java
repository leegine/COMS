head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.04.07.02.17.00;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	5444d9d078c63f4;
filename	WEB3FeqAcceptUpdateServiceImplTest.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@package webbroker3.feq.service.delegate.stdimpls;

import java.util.Date;

import com.fitechlabs.xtrade.kernel.boot.Services;
import com.fitechlabs.xtrade.plugin.tc.gentrade.OrderEventTypeEnum;
import com.fitechlabs.xtrade.plugin.tc.gentrade.OrderExpirationStatusEnum;
import com.fitechlabs.xtrade.plugin.tc.gentrade.OrderOpenStatusEnum;
import com.fitechlabs.xtrade.plugin.tc.gentrade.OrderStatusEnum;
import com.fitechlabs.xtrade.plugin.tc.gentrade.SubAccount;
import com.fitechlabs.xtrade.plugin.tc.gentrade.data.MainAccountParams;
import com.fitechlabs.xtrade.plugin.tc.gentrade.data.SubAccountParams;
import com.fitechlabs.xtrade.plugin.tc.gentrade.market.messages.DefaultCancelOrderRejectedMarketResponseMessage;
import com.fitechlabs.xtrade.plugin.tc.gentrade.market.messages.DefaultCancelOrderSentMarketResponseMessage;
import com.fitechlabs.xtrade.plugin.tc.gentrade.market.messages.DefaultChangeOrderSentMarketResponseMessage;
import com.fitechlabs.xtrade.plugin.tc.gentrade.market.messages.DefaultNewOrderAcceptedMarketResponseMessage;
import com.fitechlabs.xtrade.plugin.tc.gentrade.market.messages.DefaultNewOrderSentMarketResponseMessage;
import com.fitechlabs.xtrade.plugin.tc.gentrade.market.messages.DefaultUndoOrderInvalidatedMarketResponseMessage;
import com.fitechlabs.xtrade.plugin.tc.xbfeq.data.FeqOrderActionParams;
import com.fitechlabs.xtrade.plugin.tc.xbfeq.data.FeqOrderParams;
import com.fitechlabs.xtrade.plugin.tc.xbfeq.data.FeqOrderUnitParams;

import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.common.WEB3SystemLayerException;
import webbroker3.feq.WEB3FeqOrderUnit;
import webbroker3.gentrade.WEB3GentradeSubAccount;
import webbroker3.ifodeposit.WEB3IfoDepositCalcService;
import webbroker3.ifodeposit.WEB3IfoDepositCalcServiceImplForMock;
import webbroker3.mock.TestBaseForMock;
import webbroker3.tradingpower.WEB3TPTradingPowerReCalcService;
import webbroker3.tradingpower.WEB3TPTradingPowerReCalcServiceImplForMock;
import webbroker3.util.WEB3DateUtility;
import webbroker3.util.WEB3LogUtility;

import test.util.JunitTestBase;
import test.util.TestDBUtility;

public class WEB3FeqAcceptUpdateServiceImplTest extends JunitTestBase
{
    /**
     * ログ出力ユーティリティ。
     */
    private static WEB3LogUtility log =
        WEB3LogUtility.getInstance(WEB3FeqAcceptUpdateServiceImplTest.class);
    
    WEB3FeqAcceptUpdateServiceImpl l_impl;
    
    public WEB3FeqAcceptUpdateServiceImplTest(String arg0)
    {
        super(arg0);
    }

    protected void setUp() throws Exception
    {
        super.setUp();
        l_impl = new WEB3FeqAcceptUpdateServiceImpl();
        TestBaseForMock.MOCK_MANAGER.setIsMockUsed(true);
    }

    protected void tearDown() throws Exception
    {
        super.tearDown();
    }
    
    public void testUpdateAccept_Case001()
    {
        final String STR_METHOD_NAME = "testUpdateAccept_Case001()";
        log.entering(STR_METHOD_NAME);
        try
        {
            //FeqOrderParams
            TestDBUtility.deleteAll(FeqOrderParams.TYPE);
            FeqOrderParams l_feqOrderParams =
                TestDBUtility.getFeqOrderRow();
            l_feqOrderParams.setOrderId(123456789);
            TestDBUtility.insertWithDel(l_feqOrderParams);
            
            //FeqOrderUnitParams
            TestDBUtility.deleteAll(FeqOrderUnitParams.TYPE);
            FeqOrderUnitParams l_feqOrderUnitParams =
                TestDBUtility.getFeqOrderUnitRow();
            l_feqOrderUnitParams.setOrderId(l_feqOrderParams.getOrderId());
            l_feqOrderUnitParams.setOrderUnitId(123456789);
            l_feqOrderUnitParams.setConfirmedQuantity(200);
            l_feqOrderUnitParams.setExecutedQuantity(100);
            TestDBUtility.insertWithDel(l_feqOrderUnitParams);
            l_feqOrderUnitParams.setTemporaryExecutionFlag("0");

            //FeqOrderActionParams
            TestDBUtility.deleteAll(FeqOrderActionParams.TYPE);
            FeqOrderActionParams l_feqOrderActionParams =
                TestDBUtility.getFeqOrderActionParams();
            l_feqOrderActionParams.setOrderUnitId(l_feqOrderUnitParams.getOrderUnitId());
            l_feqOrderActionParams.setOrderEventType(OrderEventTypeEnum.CHANGE);
            l_feqOrderActionParams.setOrderStatus(OrderStatusEnum.MODIFY_ACCEPTED);
            TestDBUtility.insertWithDel(l_feqOrderActionParams);

            //SubAccountParams
            TestDBUtility.deleteAll(SubAccountParams.TYPE);
            SubAccountParams l_subAccountParams =
                TestDBUtility.getSubAccountRow();
            l_subAccountParams.setAccountId(l_feqOrderUnitParams.getAccountId());
            l_subAccountParams.setSubAccountId(l_feqOrderUnitParams.getSubAccountId());
            TestDBUtility.insertWithDel(l_subAccountParams);

            //MainAccountParams
            TestDBUtility.deleteAll(MainAccountParams.TYPE);
            MainAccountParams l_mainAccountParams =
                TestDBUtility.getMainAccountRow();
            l_mainAccountParams.setAccountId(l_feqOrderUnitParams.getAccountId());
            TestDBUtility.insertWithDel(l_mainAccountParams);
            
            
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                "webbroker3.feq.WEB3FeqOrderManager",
                "updateEstimatedPrice",
                new Class[] {WEB3FeqOrderUnit.class, Date.class}, 
                new WEB3SystemLayerException(
                    WEB3ErrorCatalog.SYSTEM_ERROR_80005,
                    "テーブルに該当するデータがありません。"));

            DefaultChangeOrderSentMarketResponseMessage l_responseMessage =
                new DefaultChangeOrderSentMarketResponseMessage(123456789);
            l_impl.updateAccept(l_responseMessage);
            fail();
        }
        catch (WEB3SystemLayerException l_ex)
        {
            log.error(l_ex.getMessage(), l_ex);
            assertEquals(l_ex.getErrorInfo(), WEB3ErrorCatalog.SYSTEM_ERROR_80005);
            
        }
        catch (Exception l_ex)
        {
            log.error(l_ex.getMessage(), l_ex);
            fail();
        }
        log.exiting(STR_METHOD_NAME);
    }
    
    public void testUpdateAccept_Case002()
    {
        final String STR_METHOD_NAME = "testUpdateAccept_Case002()";
        log.entering(STR_METHOD_NAME);
        try
        {
            //FeqOrderParams
            TestDBUtility.deleteAll(FeqOrderParams.TYPE);
            FeqOrderParams l_feqOrderParams =
                TestDBUtility.getFeqOrderRow();
            l_feqOrderParams.setOrderId(123456789);
            TestDBUtility.insertWithDel(l_feqOrderParams);
            
            //FeqOrderUnitParams
            TestDBUtility.deleteAll(FeqOrderUnitParams.TYPE);
            FeqOrderUnitParams l_feqOrderUnitParams =
                TestDBUtility.getFeqOrderUnitRow();
            l_feqOrderUnitParams.setOrderId(l_feqOrderParams.getOrderId());
            l_feqOrderUnitParams.setOrderUnitId(123456789);
            l_feqOrderUnitParams.setConfirmedQuantity(200);
            l_feqOrderUnitParams.setExecutedQuantity(100);
            TestDBUtility.insertWithDel(l_feqOrderUnitParams);
            l_feqOrderUnitParams.setTemporaryExecutionFlag("0");

            //FeqOrderActionParams
            TestDBUtility.deleteAll(FeqOrderActionParams.TYPE);
            FeqOrderActionParams l_feqOrderActionParams =
                TestDBUtility.getFeqOrderActionParams();
            l_feqOrderActionParams.setOrderUnitId(l_feqOrderUnitParams.getOrderUnitId());
            l_feqOrderActionParams.setOrderEventType(OrderEventTypeEnum.CHANGE);
            l_feqOrderActionParams.setOrderStatus(OrderStatusEnum.MODIFY_ACCEPTED);
            TestDBUtility.insertWithDel(l_feqOrderActionParams);

            //SubAccountParams
            TestDBUtility.deleteAll(SubAccountParams.TYPE);
            SubAccountParams l_subAccountParams =
                TestDBUtility.getSubAccountRow();
            l_subAccountParams.setAccountId(l_feqOrderUnitParams.getAccountId());
            l_subAccountParams.setSubAccountId(l_feqOrderUnitParams.getSubAccountId());
            TestDBUtility.insertWithDel(l_subAccountParams);

            //MainAccountParams
            TestDBUtility.deleteAll(MainAccountParams.TYPE);
            MainAccountParams l_mainAccountParams =
                TestDBUtility.getMainAccountRow();
            l_mainAccountParams.setAccountId(l_feqOrderUnitParams.getAccountId());
            TestDBUtility.insertWithDel(l_mainAccountParams);
            
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                "webbroker3.feq.WEB3FeqOrderManager",
                "updateEstimatedPrice",
                new Class[] {WEB3FeqOrderUnit.class, Date.class}, 
                new WEB3SystemLayerException(
                    WEB3ErrorCatalog.SYSTEM_ERROR_80005,
                    "テーブルに該当するデータがありません。"));

            DefaultCancelOrderSentMarketResponseMessage l_responseMessage =
                new DefaultCancelOrderSentMarketResponseMessage(123456789);
            l_impl.updateAccept(l_responseMessage);
            fail();
        }
        catch (WEB3SystemLayerException l_ex)
        {
            log.error(l_ex.getMessage(), l_ex);
            assertEquals(l_ex.getErrorInfo(), WEB3ErrorCatalog.SYSTEM_ERROR_80005);
            
        }
        catch (Exception l_ex)
        {
            log.error(l_ex.getMessage(), l_ex);
            fail();
        }
        log.exiting(STR_METHOD_NAME);
    }
    
    public void testUpdateAccept_Case003()
    {
        final String STR_METHOD_NAME = "testUpdateAccept_Case003()";
        log.entering(STR_METHOD_NAME);
        try
        {
            //FeqOrderParams
            TestDBUtility.deleteAll(FeqOrderParams.TYPE);
            FeqOrderParams l_feqOrderParams =
                TestDBUtility.getFeqOrderRow();
            l_feqOrderParams.setOrderId(123456789);
            TestDBUtility.insertWithDel(l_feqOrderParams);
            
            //FeqOrderUnitParams
            TestDBUtility.deleteAll(FeqOrderUnitParams.TYPE);
            FeqOrderUnitParams l_feqOrderUnitParams =
                TestDBUtility.getFeqOrderUnitRow();
            l_feqOrderUnitParams.setOrderId(l_feqOrderParams.getOrderId());
            l_feqOrderUnitParams.setOrderUnitId(123456789);
            l_feqOrderUnitParams.setConfirmedQuantity(200);
            l_feqOrderUnitParams.setExecutedQuantity(100);
            l_feqOrderUnitParams.setOrderOpenStatus(OrderOpenStatusEnum.CLOSED);
            l_feqOrderUnitParams.setExpirationStatus(OrderExpirationStatusEnum.INVALIDATED_BY_MKT);
            TestDBUtility.insertWithDel(l_feqOrderUnitParams);
            l_feqOrderUnitParams.setTemporaryExecutionFlag("0");

            //FeqOrderActionParams
            TestDBUtility.deleteAll(FeqOrderActionParams.TYPE);
            FeqOrderActionParams l_feqOrderActionParams =
                TestDBUtility.getFeqOrderActionParams();
            l_feqOrderActionParams.setOrderUnitId(l_feqOrderUnitParams.getOrderUnitId());
            l_feqOrderActionParams.setOrderEventType(OrderEventTypeEnum.EXPIRE);
            l_feqOrderActionParams.setOrderStatus(OrderStatusEnum.MODIFY_ACCEPTED);
            l_feqOrderActionParams.setOrderActionSerialNo(1001);
            TestDBUtility.insertWithDel(l_feqOrderActionParams);
            
            //222
            l_feqOrderActionParams.setOrderActionId(321654987);
            l_feqOrderActionParams.setOrderStatus(OrderStatusEnum.MODIFY_ACCEPTED);
            l_feqOrderActionParams.setOrderActionSerialNo(1002);
            TestDBUtility.insertWithDel(l_feqOrderActionParams);

            //SubAccountParams
            TestDBUtility.deleteAll(SubAccountParams.TYPE);
            SubAccountParams l_subAccountParams =
                TestDBUtility.getSubAccountRow();
            l_subAccountParams.setAccountId(l_feqOrderUnitParams.getAccountId());
            l_subAccountParams.setSubAccountId(l_feqOrderUnitParams.getSubAccountId());
            TestDBUtility.insertWithDel(l_subAccountParams);

            //MainAccountParams
            TestDBUtility.deleteAll(MainAccountParams.TYPE);
            MainAccountParams l_mainAccountParams =
                TestDBUtility.getMainAccountRow();
            l_mainAccountParams.setAccountId(l_feqOrderUnitParams.getAccountId());
            TestDBUtility.insertWithDel(l_mainAccountParams);
            
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                "webbroker3.feq.WEB3FeqOrderManager",
                "updateEstimatedPrice",
                new Class[] {WEB3FeqOrderUnit.class, Date.class}, 
                new WEB3SystemLayerException(
                    WEB3ErrorCatalog.SYSTEM_ERROR_80005,
                    "テーブルに該当するデータがありません。"));

            DefaultUndoOrderInvalidatedMarketResponseMessage l_responseMessage =
                new DefaultUndoOrderInvalidatedMarketResponseMessage(123456789);
            l_impl.updateAccept(l_responseMessage);
            fail();
        }
        catch (WEB3SystemLayerException l_ex)
        {
            log.error(l_ex.getMessage(), l_ex);
            assertEquals(l_ex.getErrorInfo(), WEB3ErrorCatalog.SYSTEM_ERROR_80005);
            
        }
        catch (Exception l_ex)
        {
            log.error(l_ex.getMessage(), l_ex);
            fail();
        }
        log.exiting(STR_METHOD_NAME);
    }
    
    public void testUpdateAccept_Case004()
    {
        final String STR_METHOD_NAME = "testUpdateAccept_Case004()";
        log.entering(STR_METHOD_NAME);
        try
        {
            //FeqOrderParams
            TestDBUtility.deleteAll(FeqOrderParams.TYPE);
            FeqOrderParams l_feqOrderParams =
                TestDBUtility.getFeqOrderRow();
            l_feqOrderParams.setOrderId(123456789);
            TestDBUtility.insertWithDel(l_feqOrderParams);
            
            //FeqOrderUnitParams
            TestDBUtility.deleteAll(FeqOrderUnitParams.TYPE);
            FeqOrderUnitParams l_feqOrderUnitParams =
                TestDBUtility.getFeqOrderUnitRow();
            l_feqOrderUnitParams.setOrderId(l_feqOrderParams.getOrderId());
            l_feqOrderUnitParams.setOrderUnitId(123456789);
            l_feqOrderUnitParams.setConfirmedQuantity(200);
            l_feqOrderUnitParams.setExecutedQuantity(200);
            TestDBUtility.insertWithDel(l_feqOrderUnitParams);
            l_feqOrderUnitParams.setTemporaryExecutionFlag("0");

            //FeqOrderActionParams
            TestDBUtility.deleteAll(FeqOrderActionParams.TYPE);
            FeqOrderActionParams l_feqOrderActionParams =
                TestDBUtility.getFeqOrderActionParams();
            l_feqOrderActionParams.setOrderUnitId(l_feqOrderUnitParams.getOrderUnitId());
            l_feqOrderActionParams.setOrderEventType(OrderEventTypeEnum.CHANGE);
            l_feqOrderActionParams.setOrderStatus(OrderStatusEnum.MODIFY_ACCEPTED);
            TestDBUtility.insertWithDel(l_feqOrderActionParams);

            //SubAccountParams
            TestDBUtility.deleteAll(SubAccountParams.TYPE);
            SubAccountParams l_subAccountParams =
                TestDBUtility.getSubAccountRow();
            l_subAccountParams.setAccountId(l_feqOrderUnitParams.getAccountId());
            l_subAccountParams.setSubAccountId(l_feqOrderUnitParams.getSubAccountId());
            TestDBUtility.insertWithDel(l_subAccountParams);

            //MainAccountParams
            TestDBUtility.deleteAll(MainAccountParams.TYPE);
            MainAccountParams l_mainAccountParams =
                TestDBUtility.getMainAccountRow();
            l_mainAccountParams.setAccountId(l_feqOrderUnitParams.getAccountId());
            TestDBUtility.insertWithDel(l_mainAccountParams);
            
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                "webbroker3.feq.WEB3FeqOrderManager",
                "updateEstimatedPrice",
                new Class[] {WEB3FeqOrderUnit.class, Date.class}, 
                new WEB3SystemLayerException(
                    WEB3ErrorCatalog.SYSTEM_ERROR_80005,
                    "テーブルに該当するデータがありません。"));

            DefaultChangeOrderSentMarketResponseMessage l_responseMessage =
                new DefaultChangeOrderSentMarketResponseMessage(123456789);
            l_impl.updateAccept(l_responseMessage);
            fail();
        }
        catch (WEB3SystemLayerException l_ex)
        {
            log.error(l_ex.getMessage(), l_ex);
            assertEquals(l_ex.getErrorInfo(), WEB3ErrorCatalog.SYSTEM_ERROR_80005);
            
        }
        catch (Exception l_ex)
        {
            log.error(l_ex.getMessage(), l_ex);
            fail();
        }
        log.exiting(STR_METHOD_NAME);
    }
    
    public void testUpdateAccept_Case005()
    {
        final String STR_METHOD_NAME = "testUpdateAccept_Case005()";
        log.entering(STR_METHOD_NAME);
        try
        {
            //FeqOrderParams
            TestDBUtility.deleteAll(FeqOrderParams.TYPE);
            FeqOrderParams l_feqOrderParams =
                TestDBUtility.getFeqOrderRow();
            l_feqOrderParams.setOrderId(123456789);
            TestDBUtility.insertWithDel(l_feqOrderParams);
            
            //FeqOrderUnitParams
            TestDBUtility.deleteAll(FeqOrderUnitParams.TYPE);
            FeqOrderUnitParams l_feqOrderUnitParams =
                TestDBUtility.getFeqOrderUnitRow();
            l_feqOrderUnitParams.setOrderId(l_feqOrderParams.getOrderId());
            l_feqOrderUnitParams.setOrderUnitId(123456789);
            l_feqOrderUnitParams.setConfirmedQuantity(200);
            l_feqOrderUnitParams.setExecutedQuantity(200);
            TestDBUtility.insertWithDel(l_feqOrderUnitParams);
            l_feqOrderUnitParams.setTemporaryExecutionFlag("0");

            //FeqOrderActionParams
            TestDBUtility.deleteAll(FeqOrderActionParams.TYPE);
            FeqOrderActionParams l_feqOrderActionParams =
                TestDBUtility.getFeqOrderActionParams();
            l_feqOrderActionParams.setOrderUnitId(l_feqOrderUnitParams.getOrderUnitId());
            l_feqOrderActionParams.setOrderEventType(OrderEventTypeEnum.CHANGE);
            l_feqOrderActionParams.setOrderStatus(OrderStatusEnum.MODIFY_ACCEPTED);
            TestDBUtility.insertWithDel(l_feqOrderActionParams);

            //SubAccountParams
            TestDBUtility.deleteAll(SubAccountParams.TYPE);
            SubAccountParams l_subAccountParams =
                TestDBUtility.getSubAccountRow();
            l_subAccountParams.setAccountId(l_feqOrderUnitParams.getAccountId());
            l_subAccountParams.setSubAccountId(l_feqOrderUnitParams.getSubAccountId());
            TestDBUtility.insertWithDel(l_subAccountParams);

            //MainAccountParams
            TestDBUtility.deleteAll(MainAccountParams.TYPE);
            MainAccountParams l_mainAccountParams =
                TestDBUtility.getMainAccountRow();
            l_mainAccountParams.setAccountId(l_feqOrderUnitParams.getAccountId());
            TestDBUtility.insertWithDel(l_mainAccountParams);
            
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                "webbroker3.feq.WEB3FeqOrderManager",
                "updateEstimatedPrice",
                new Class[] {WEB3FeqOrderUnit.class, Date.class}, 
                new WEB3SystemLayerException(
                    WEB3ErrorCatalog.SYSTEM_ERROR_80005,
                    "テーブルに該当するデータがありません。"));

            DefaultCancelOrderSentMarketResponseMessage l_responseMessage =
                new DefaultCancelOrderSentMarketResponseMessage(123456789);
            l_impl.updateAccept(l_responseMessage);
            fail();
        }
        catch (WEB3SystemLayerException l_ex)
        {
            log.error(l_ex.getMessage(), l_ex);
            assertEquals(l_ex.getErrorInfo(), WEB3ErrorCatalog.SYSTEM_ERROR_80005);
            
        }
        catch (Exception l_ex)
        {
            log.error(l_ex.getMessage(), l_ex);
            fail();
        }
        log.exiting(STR_METHOD_NAME);
    }
    
    public void testUpdateAccept_Case006()
    {
        final String STR_METHOD_NAME = "testUpdateAccept_Case006()";
        log.entering(STR_METHOD_NAME);
        try
        {
            //FeqOrderParams
            TestDBUtility.deleteAll(FeqOrderParams.TYPE);
            FeqOrderParams l_feqOrderParams =
                TestDBUtility.getFeqOrderRow();
            l_feqOrderParams.setOrderId(123456789);
            TestDBUtility.insertWithDel(l_feqOrderParams);
            
            //FeqOrderUnitParams
            TestDBUtility.deleteAll(FeqOrderUnitParams.TYPE);
            FeqOrderUnitParams l_feqOrderUnitParams =
                TestDBUtility.getFeqOrderUnitRow();
            l_feqOrderUnitParams.setOrderId(l_feqOrderParams.getOrderId());
            l_feqOrderUnitParams.setOrderUnitId(123456789);
            l_feqOrderUnitParams.setConfirmedQuantity(200);
            l_feqOrderUnitParams.setExecutedQuantity(100);
            l_feqOrderUnitParams.setOrderOpenStatus(OrderOpenStatusEnum.CLOSED);
            l_feqOrderUnitParams.setExpirationStatus(OrderExpirationStatusEnum.INVALIDATED_BY_MKT);
            TestDBUtility.insertWithDel(l_feqOrderUnitParams);
            l_feqOrderUnitParams.setTemporaryExecutionFlag("0");

            //FeqOrderActionParams
            TestDBUtility.deleteAll(FeqOrderActionParams.TYPE);
            FeqOrderActionParams l_feqOrderActionParams =
                TestDBUtility.getFeqOrderActionParams();
            l_feqOrderActionParams.setOrderUnitId(l_feqOrderUnitParams.getOrderUnitId());
            l_feqOrderActionParams.setOrderEventType(OrderEventTypeEnum.EXPIRE);
            l_feqOrderActionParams.setOrderStatus(OrderStatusEnum.MODIFY_ACCEPTED);
            l_feqOrderActionParams.setOrderActionSerialNo(1001);
            TestDBUtility.insertWithDel(l_feqOrderActionParams);
            
            //222
            l_feqOrderActionParams.setOrderActionId(321654987);
            l_feqOrderActionParams.setOrderStatus(OrderStatusEnum.MODIFY_ACCEPTED);
            l_feqOrderActionParams.setOrderActionSerialNo(1002);
            TestDBUtility.insertWithDel(l_feqOrderActionParams);

            //SubAccountParams
            TestDBUtility.deleteAll(SubAccountParams.TYPE);
            SubAccountParams l_subAccountParams =
                TestDBUtility.getSubAccountRow();
            l_subAccountParams.setAccountId(l_feqOrderUnitParams.getAccountId());
            l_subAccountParams.setSubAccountId(l_feqOrderUnitParams.getSubAccountId());
            TestDBUtility.insertWithDel(l_subAccountParams);

            //MainAccountParams
            TestDBUtility.deleteAll(MainAccountParams.TYPE);
            MainAccountParams l_mainAccountParams =
                TestDBUtility.getMainAccountRow();
            l_mainAccountParams.setAccountId(l_feqOrderUnitParams.getAccountId());
            TestDBUtility.insertWithDel(l_mainAccountParams);
            
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                "webbroker3.feq.WEB3FeqOrderManager",
                "updateEstimatedPrice",
                new Class[] {WEB3FeqOrderUnit.class, Date.class}, 
                null);
            
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                "webbroker3.tradingpower.WEB3TPTradingPowerReCalcServiceImpl",
                "reCalcTradingPower",
                new Class[] {WEB3GentradeSubAccount.class }, 
                new WEB3SystemLayerException(
                    WEB3ErrorCatalog.SYSTEM_ERROR_80005,
                    "テーブルに該当するデータがありません。"));
            
            Services.overrideService(
                WEB3TPTradingPowerReCalcService.class,
                new WEB3TPTradingPowerReCalcServiceImplForMock());
            
            DefaultUndoOrderInvalidatedMarketResponseMessage l_responseMessage =
                new DefaultUndoOrderInvalidatedMarketResponseMessage(123456789);
            l_impl.updateAccept(l_responseMessage);
            fail();
        }
        catch (WEB3SystemLayerException l_ex)
        {
            log.error(l_ex.getMessage(), l_ex);
            assertEquals(l_ex.getErrorInfo(), WEB3ErrorCatalog.SYSTEM_ERROR_80005);
        }
        catch (Exception l_ex)
        {
            log.error(l_ex.getMessage(), l_ex);
            fail();
        }
        log.exiting(STR_METHOD_NAME);
    }
    
    public void testUpdateAccept_Case007()
    {
        final String STR_METHOD_NAME = "testUpdateAccept_Case007()";
        log.entering(STR_METHOD_NAME);
        try
        {
            //FeqOrderParams
            TestDBUtility.deleteAll(FeqOrderParams.TYPE);
            FeqOrderParams l_feqOrderParams =
                TestDBUtility.getFeqOrderRow();
            l_feqOrderParams.setOrderId(123456789);
            TestDBUtility.insertWithDel(l_feqOrderParams);
            
            //FeqOrderUnitParams
            TestDBUtility.deleteAll(FeqOrderUnitParams.TYPE);
            FeqOrderUnitParams l_feqOrderUnitParams =
                TestDBUtility.getFeqOrderUnitRow();
            l_feqOrderUnitParams.setOrderId(l_feqOrderParams.getOrderId());
            l_feqOrderUnitParams.setOrderUnitId(123456789);
            l_feqOrderUnitParams.setConfirmedQuantity(200);
            l_feqOrderUnitParams.setExecutedQuantity(100);
            l_feqOrderUnitParams.setOrderOpenStatus(OrderOpenStatusEnum.CLOSED);
            l_feqOrderUnitParams.setExpirationStatus(OrderExpirationStatusEnum.INVALIDATED_BY_MKT);
            TestDBUtility.insertWithDel(l_feqOrderUnitParams);
            l_feqOrderUnitParams.setTemporaryExecutionFlag("0");

            //FeqOrderActionParams
            TestDBUtility.deleteAll(FeqOrderActionParams.TYPE);
            FeqOrderActionParams l_feqOrderActionParams =
                TestDBUtility.getFeqOrderActionParams();
            l_feqOrderActionParams.setOrderUnitId(l_feqOrderUnitParams.getOrderUnitId());
            l_feqOrderActionParams.setOrderEventType(OrderEventTypeEnum.EXPIRE);
            l_feqOrderActionParams.setOrderStatus(OrderStatusEnum.MODIFY_ACCEPTED);
            l_feqOrderActionParams.setOrderActionSerialNo(1001);
            TestDBUtility.insertWithDel(l_feqOrderActionParams);
            
            //222
            l_feqOrderActionParams.setOrderActionId(321654987);
            l_feqOrderActionParams.setOrderStatus(OrderStatusEnum.MODIFY_ACCEPTED);
            l_feqOrderActionParams.setOrderActionSerialNo(1002);
            TestDBUtility.insertWithDel(l_feqOrderActionParams);

            //SubAccountParams
            TestDBUtility.deleteAll(SubAccountParams.TYPE);
            SubAccountParams l_subAccountParams =
                TestDBUtility.getSubAccountRow();
            l_subAccountParams.setAccountId(l_feqOrderUnitParams.getAccountId());
            l_subAccountParams.setSubAccountId(l_feqOrderUnitParams.getSubAccountId());
            TestDBUtility.insertWithDel(l_subAccountParams);

            //MainAccountParams
            TestDBUtility.deleteAll(MainAccountParams.TYPE);
            MainAccountParams l_mainAccountParams =
                TestDBUtility.getMainAccountRow();
            l_mainAccountParams.setAccountId(l_feqOrderUnitParams.getAccountId());
            TestDBUtility.insertWithDel(l_mainAccountParams);
            
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                "webbroker3.feq.WEB3FeqOrderManager",
                "updateEstimatedPrice",
                new Class[] {WEB3FeqOrderUnit.class, Date.class}, 
                null);
            
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                "webbroker3.tradingpower.WEB3TPTradingPowerReCalcServiceImpl",
                "reCalcTradingPower",
                new Class[] {WEB3GentradeSubAccount.class }, 
                new WEB3SystemLayerException(
                    WEB3ErrorCatalog.SYSTEM_ERROR_80005,
                    "テーブルに該当するデータがありません。"));
            
            Services.overrideService(
                WEB3TPTradingPowerReCalcService.class,
                new WEB3TPTradingPowerReCalcServiceImplForMock());
            
            DefaultNewOrderAcceptedMarketResponseMessage l_responseMessage =
                new DefaultNewOrderAcceptedMarketResponseMessage(123456789);
            l_impl.updateAccept(l_responseMessage);

        }
        catch (WEB3SystemLayerException l_ex)
        {
            log.error(l_ex.getMessage(), l_ex);
            assertEquals(l_ex.getErrorInfo(), WEB3ErrorCatalog.SYSTEM_ERROR_80005);
        }
        catch (Exception l_ex)
        {
            log.error(l_ex.getMessage(), l_ex);
            fail();
        }
        log.exiting(STR_METHOD_NAME);
    }
    
    public void testUpdateAccept_Case008()
    {
        final String STR_METHOD_NAME = "testUpdateAccept_Case008()";
        log.entering(STR_METHOD_NAME);
        try
        {
            //FeqOrderParams
            TestDBUtility.deleteAll(FeqOrderParams.TYPE);
            FeqOrderParams l_feqOrderParams =
                TestDBUtility.getFeqOrderRow();
            l_feqOrderParams.setOrderId(123456789);
            TestDBUtility.insertWithDel(l_feqOrderParams);
            
            //FeqOrderUnitParams
            TestDBUtility.deleteAll(FeqOrderUnitParams.TYPE);
            FeqOrderUnitParams l_feqOrderUnitParams =
                TestDBUtility.getFeqOrderUnitRow();
            l_feqOrderUnitParams.setOrderId(l_feqOrderParams.getOrderId());
            l_feqOrderUnitParams.setOrderUnitId(123456789);
            l_feqOrderUnitParams.setConfirmedQuantity(200);
            l_feqOrderUnitParams.setExecutedQuantity(100);
            l_feqOrderUnitParams.setOrderOpenStatus(OrderOpenStatusEnum.CLOSED);
            l_feqOrderUnitParams.setExpirationStatus(OrderExpirationStatusEnum.INVALIDATED_BY_MKT);
            TestDBUtility.insertWithDel(l_feqOrderUnitParams);
            l_feqOrderUnitParams.setTemporaryExecutionFlag("0");

            //FeqOrderActionParams
            TestDBUtility.deleteAll(FeqOrderActionParams.TYPE);
            FeqOrderActionParams l_feqOrderActionParams =
                TestDBUtility.getFeqOrderActionParams();
            l_feqOrderActionParams.setOrderUnitId(l_feqOrderUnitParams.getOrderUnitId());
            l_feqOrderActionParams.setOrderEventType(OrderEventTypeEnum.EXPIRE);
            l_feqOrderActionParams.setOrderStatus(OrderStatusEnum.MODIFY_ACCEPTED);
            l_feqOrderActionParams.setOrderActionSerialNo(1001);
            TestDBUtility.insertWithDel(l_feqOrderActionParams);
            
            //222
            l_feqOrderActionParams.setOrderActionId(321654987);
            l_feqOrderActionParams.setOrderStatus(OrderStatusEnum.MODIFY_ACCEPTED);
            l_feqOrderActionParams.setOrderActionSerialNo(1002);
            TestDBUtility.insertWithDel(l_feqOrderActionParams);

            //SubAccountParams
            TestDBUtility.deleteAll(SubAccountParams.TYPE);
            SubAccountParams l_subAccountParams =
                TestDBUtility.getSubAccountRow();
            l_subAccountParams.setAccountId(l_feqOrderUnitParams.getAccountId());
            l_subAccountParams.setSubAccountId(l_feqOrderUnitParams.getSubAccountId());
            TestDBUtility.insertWithDel(l_subAccountParams);

            //MainAccountParams
            TestDBUtility.deleteAll(MainAccountParams.TYPE);
            MainAccountParams l_mainAccountParams =
                TestDBUtility.getMainAccountRow();
            l_mainAccountParams.setAccountId(l_feqOrderUnitParams.getAccountId());
            TestDBUtility.insertWithDel(l_mainAccountParams);
            
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                "webbroker3.feq.WEB3FeqOrderManager",
                "updateEstimatedPrice",
                new Class[] {WEB3FeqOrderUnit.class, Date.class}, 
                null);
            
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                "webbroker3.tradingpower.WEB3TPTradingPowerReCalcServiceImpl",
                "reCalcTradingPower",
                new Class[] {WEB3GentradeSubAccount.class }, 
                new WEB3SystemLayerException(
                    WEB3ErrorCatalog.SYSTEM_ERROR_80005,
                    "テーブルに該当するデータがありません。"));
            
            Services.overrideService(
                WEB3TPTradingPowerReCalcService.class,
                new WEB3TPTradingPowerReCalcServiceImplForMock());
            
            DefaultCancelOrderRejectedMarketResponseMessage l_responseMessage =
                new DefaultCancelOrderRejectedMarketResponseMessage(123456789);
            l_impl.updateAccept(l_responseMessage);

        }
        catch (WEB3SystemLayerException l_ex)
        {
            log.error(l_ex.getMessage(), l_ex);
            assertEquals(l_ex.getErrorInfo(), WEB3ErrorCatalog.SYSTEM_ERROR_80005);
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
