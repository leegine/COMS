head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.04.07.00.55.21;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	5444d9d078c63f4;
filename	WEB3EquityOrderExecNotifyTransactionCallbackTest.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
 Copyright        : (株)大和総研 証券ソリューションシステム第二部
 File Name        : WEB3EquityOrderExecNotifyTransactionCallbackTest.java
 Author Name      : Daiwa Institute of Research
 Revision History : 2008/07/29 楊夫志(中訊) 新規作成 モデル 132
*/
package webbroker3.equity.service.delegate.stdimpls;

import com.fitechlabs.xtrade.kernel.boot.Services;
import com.fitechlabs.xtrade.kernel.data.DataCallbackException;
import com.fitechlabs.xtrade.plugin.tc.eqtype.EqTypeOrderUnit;
import com.fitechlabs.xtrade.plugin.tc.eqtype.data.EqtypeOrderUnitParams;
import com.fitechlabs.xtrade.plugin.tc.eqtype.data.EqtypeOrderUnitRow;
import com.fitechlabs.xtrade.plugin.tc.gentrade.FinApp;
import com.fitechlabs.xtrade.plugin.tc.gentrade.GtlUtils;
import com.fitechlabs.xtrade.plugin.tc.gentrade.OrderCategEnum;
import com.fitechlabs.xtrade.plugin.tc.gentrade.ProductTypeEnum;
import com.fitechlabs.xtrade.plugin.tc.gentrade.TradingModule;

import test.util.TestDBUtility;

import webbroker3.common.WEB3BaseException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.common.define.WEB3DealedTypeDef;
import webbroker3.common.define.WEB3StatusDef;
import webbroker3.equity.WEB3EquityOrderManager;
import webbroker3.equity.data.HostEquityOrderExecNotifyParams;
import webbroker3.mock.TestBaseForMock;
import webbroker3.util.WEB3LogUtility;

public class WEB3EquityOrderExecNotifyTransactionCallbackTest extends TestBaseForMock
{

    WEB3LogUtility log = WEB3LogUtility.getInstance(WEB3EquityOrderExecNotifyTransactionCallbackTest.class);
    WEB3EquityOrderExecNotifyTransactionCallback l_callback = null;
    public WEB3EquityOrderExecNotifyTransactionCallbackTest(String arg0)
    {
        super(arg0);
    }

    protected void setUp() throws Exception
    {
        super.setUp();
        MOCK_MANAGER.setIsMockUsed(true);
    }

    protected void tearDown() throws Exception
    {
        super.tearDown();
    }

    //取得した注文単位＝現物株式の場合
    //株式出来通知キューParams.出来通知区分＝（"全部約定"or"一部約定"）の場合
    public void testProcess_C0001()
    {
        final String STR_METHOD_NAME = "testProcess_C0001()";
        log.entering(TEST_START + STR_METHOD_NAME);

        try
        {
            TestDBUtility.deleteAll(EqtypeOrderUnitRow.TYPE);
            EqtypeOrderUnitParams eqtypeOrderUnitParams = TestDBUtility.getEqtypeOrderUnitRow();
            eqtypeOrderUnitParams.setOrderCateg(OrderCategEnum.ASSET);
            TestDBUtility.insertWithDel(eqtypeOrderUnitParams);

            FinApp l_finApp = (FinApp)Services.getService(FinApp.class);
            TradingModule l_tradingMod =
                l_finApp.getTradingModule(ProductTypeEnum.EQUITY);
            WEB3EquityOrderManager l_orderManager =
                (WEB3EquityOrderManager)l_tradingMod.getOrderManager();
            EqTypeOrderUnit l_orderUnit =
                (EqTypeOrderUnit)l_orderManager.getOrderUnit(3304148080001L);

            HostEquityOrderExecNotifyParams l_orderExecNotifyParams = new HostEquityOrderExecNotifyParams();
            l_orderExecNotifyParams.setDealedType(WEB3DealedTypeDef.EXECUTED);

            l_callback = new WEB3EquityOrderExecNotifyTransactionCallback(l_orderUnit, l_orderExecNotifyParams);

            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                    "webbroker3.equity.service.delegate.stdimpls.WEB3EquityOrderExecNotifyUnitServiceImpl",
                    "notifyExecute",
                    new Class[] {EqTypeOrderUnit.class, HostEquityOrderExecNotifyParams.class},
                    new WEB3BaseException(WEB3ErrorCatalog.BUSINESS_ERROR_00003, "BUSINESS_ERROR_00003", "BUSINESS_ERROR_00003"));

            l_callback.process();
            fail();
        }
        catch(DataCallbackException l_ex)
        {
            assertEquals("BUSINESS_ERROR_00003", l_ex.getMessage());
        }
        catch (Exception l_ex)
        {
            log.error(STR_METHOD_NAME, l_ex);
            fail();
        }
        log.info(STR_METHOD_NAME
            + ">>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>test pass !!");
        log.exiting(TEST_END + STR_METHOD_NAME);
    }

    //取得した注文単位＝現物株式の場合
    //株式出来通知キューParams.出来通知区分＝（"約定取消"）の場合
    public void testProcess_C0002()
    {
        final String STR_METHOD_NAME = "testProcess_C0002()";
        log.entering(TEST_START + STR_METHOD_NAME);

        try
        {
            TestDBUtility.deleteAll(EqtypeOrderUnitRow.TYPE);
            EqtypeOrderUnitParams eqtypeOrderUnitParams = TestDBUtility.getEqtypeOrderUnitRow();
            eqtypeOrderUnitParams.setOrderCateg(OrderCategEnum.ASSET);
            TestDBUtility.insertWithDel(eqtypeOrderUnitParams);

            FinApp l_finApp = (FinApp)Services.getService(FinApp.class);
            TradingModule l_tradingMod =
                l_finApp.getTradingModule(ProductTypeEnum.EQUITY);
            WEB3EquityOrderManager l_orderManager =
                (WEB3EquityOrderManager)l_tradingMod.getOrderManager();
            EqTypeOrderUnit l_orderUnit =
                (EqTypeOrderUnit)l_orderManager.getOrderUnit(3304148080001L);

            HostEquityOrderExecNotifyParams l_orderExecNotifyParams = new HostEquityOrderExecNotifyParams();
            l_orderExecNotifyParams.setDealedType(WEB3DealedTypeDef.CANCEL);

            l_callback = new WEB3EquityOrderExecNotifyTransactionCallback(l_orderUnit, l_orderExecNotifyParams);

            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                    "webbroker3.equity.service.delegate.stdimpls.WEB3EquityOrderExecNotifyUnitServiceImpl",
                    "notifyExecuteCancel",
                    new Class[] {EqTypeOrderUnit.class, HostEquityOrderExecNotifyParams.class},
                    new WEB3BaseException(WEB3ErrorCatalog.BUSINESS_ERROR_00004, "BUSINESS_ERROR_00004", "BUSINESS_ERROR_00004"));

            l_callback.process();
            fail();
        }
        catch(DataCallbackException l_ex)
        {
            assertEquals("BUSINESS_ERROR_00004", l_ex.getMessage());
        }
        catch (Exception l_ex)
        {
            log.error(STR_METHOD_NAME, l_ex);
            fail();
        }
        log.info(STR_METHOD_NAME
            + ">>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>test pass !!");
        log.exiting(TEST_END + STR_METHOD_NAME);
    }

    //取得した注文単位＝現物株式の場合
    //株式出来通知キューParams.出来通知区分＝（"約定取消"）,
    //（"全部約定"or"一部約定"）以外の場合
    public void testProcess_C0003()
    {
        final String STR_METHOD_NAME = "testProcess_C0003()";
        log.entering(TEST_START + STR_METHOD_NAME);

        try
        {
            TestDBUtility.deleteAll(EqtypeOrderUnitRow.TYPE);
            EqtypeOrderUnitParams eqtypeOrderUnitParams = TestDBUtility.getEqtypeOrderUnitRow();
            eqtypeOrderUnitParams.setOrderCateg(OrderCategEnum.ASSET);
            TestDBUtility.insertWithDel(eqtypeOrderUnitParams);

            FinApp l_finApp = (FinApp)Services.getService(FinApp.class);
            TradingModule l_tradingMod =
                l_finApp.getTradingModule(ProductTypeEnum.EQUITY);
            WEB3EquityOrderManager l_orderManager =
                (WEB3EquityOrderManager)l_tradingMod.getOrderManager();
            EqTypeOrderUnit l_orderUnit =
                (EqTypeOrderUnit)l_orderManager.getOrderUnit(3304148080001L);

            HostEquityOrderExecNotifyParams l_orderExecNotifyParams = new HostEquityOrderExecNotifyParams();
            l_orderExecNotifyParams.setDealedType("3");

            l_callback = new WEB3EquityOrderExecNotifyTransactionCallback(l_orderUnit, l_orderExecNotifyParams);
            l_callback.process();
            fail();
        }
        catch(DataCallbackException l_ex)
        {
            assertEquals(WEB3ErrorCatalog.SYSTEM_ERROR_80025, l_ex.getDetails());
        }
        catch (Exception l_ex)
        {
            log.error(STR_METHOD_NAME, l_ex);
            fail();
        }
        log.info(STR_METHOD_NAME
            + ">>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>test pass !!");
        log.exiting(TEST_END + STR_METHOD_NAME);
    }

    //取得した注文単位＝信用取引の場合
    //株式出来通知キューParams.出来通知区分＝（"全部約定"or"一部約定"）の場合
    public void testProcess_C0004()
    {
        final String STR_METHOD_NAME = "testProcess_C0004()";
        log.entering(TEST_START + STR_METHOD_NAME);

        try
        {
            TestDBUtility.deleteAll(EqtypeOrderUnitRow.TYPE);
            EqtypeOrderUnitParams eqtypeOrderUnitParams = TestDBUtility.getEqtypeOrderUnitRow();
            eqtypeOrderUnitParams.setOrderCateg(OrderCategEnum.OPEN_MARGIN);
            TestDBUtility.insertWithDel(eqtypeOrderUnitParams);

            FinApp l_finApp = (FinApp)Services.getService(FinApp.class);
            TradingModule l_tradingMod =
                l_finApp.getTradingModule(ProductTypeEnum.EQUITY);
            WEB3EquityOrderManager l_orderManager =
                (WEB3EquityOrderManager)l_tradingMod.getOrderManager();
            EqTypeOrderUnit l_orderUnit =
                (EqTypeOrderUnit)l_orderManager.getOrderUnit(3304148080001L);

            HostEquityOrderExecNotifyParams l_orderExecNotifyParams = new HostEquityOrderExecNotifyParams();
            l_orderExecNotifyParams.setDealedType(WEB3DealedTypeDef.EXECUTED);

            l_callback = new WEB3EquityOrderExecNotifyTransactionCallback(l_orderUnit, l_orderExecNotifyParams);

            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                    "webbroker3.equity.service.delegate.stdimpls.WEB3MarginOrderExecNotifyUnitServiceImpl",
                    "notifyExecute",
                    new Class[] {EqTypeOrderUnit.class, HostEquityOrderExecNotifyParams.class},
                    new WEB3BaseException(WEB3ErrorCatalog.BUSINESS_ERROR_00005, "BUSINESS_ERROR_00005", "BUSINESS_ERROR_00005"));

            l_callback.process();
            fail();
        }
        catch(DataCallbackException l_ex)
        {
            assertEquals("BUSINESS_ERROR_00005", l_ex.getMessage());
        }
        catch (Exception l_ex)
        {
            log.error(STR_METHOD_NAME, l_ex);
            fail();
        }
        log.info(STR_METHOD_NAME
            + ">>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>test pass !!");
        log.exiting(TEST_END + STR_METHOD_NAME);
    }

    //取得した注文単位＝信用取引の場合
    //株式出来通知キューParams.出来通知区分＝（"約定取消"）の場合
    public void testProcess_C0005()
    {
        final String STR_METHOD_NAME = "testProcess_C0005()";
        log.entering(TEST_START + STR_METHOD_NAME);

        try
        {
            TestDBUtility.deleteAll(EqtypeOrderUnitRow.TYPE);
            EqtypeOrderUnitParams eqtypeOrderUnitParams = TestDBUtility.getEqtypeOrderUnitRow();
            eqtypeOrderUnitParams.setOrderCateg(OrderCategEnum.OPEN_MARGIN);
            TestDBUtility.insertWithDel(eqtypeOrderUnitParams);

            FinApp l_finApp = (FinApp)Services.getService(FinApp.class);
            TradingModule l_tradingMod =
                l_finApp.getTradingModule(ProductTypeEnum.EQUITY);
            WEB3EquityOrderManager l_orderManager =
                (WEB3EquityOrderManager)l_tradingMod.getOrderManager();
            EqTypeOrderUnit l_orderUnit =
                (EqTypeOrderUnit)l_orderManager.getOrderUnit(3304148080001L);

            HostEquityOrderExecNotifyParams l_orderExecNotifyParams = new HostEquityOrderExecNotifyParams();
            l_orderExecNotifyParams.setDealedType(WEB3DealedTypeDef.CANCEL);

            l_callback = new WEB3EquityOrderExecNotifyTransactionCallback(l_orderUnit, l_orderExecNotifyParams);

            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                    "webbroker3.equity.service.delegate.stdimpls.WEB3MarginOrderExecNotifyUnitServiceImpl",
                    "notifyExecuteCancel",
                    new Class[] {EqTypeOrderUnit.class, HostEquityOrderExecNotifyParams.class},
                    new WEB3BaseException(WEB3ErrorCatalog.BUSINESS_ERROR_00006, "BUSINESS_ERROR_00006", "BUSINESS_ERROR_00006"));

            l_callback.process();
            fail();
        }
        catch(DataCallbackException l_ex)
        {
            assertEquals("BUSINESS_ERROR_00006", l_ex.getMessage());
        }
        catch (Exception l_ex)
        {
            log.error(STR_METHOD_NAME, l_ex);
            fail();
        }
        log.info(STR_METHOD_NAME
            + ">>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>test pass !!");
        log.exiting(TEST_END + STR_METHOD_NAME);
    }

    //取得した注文単位＝信用取引の場合
    //株式出来通知キューParams.出来通知区分＝（"約定取消"）,
    //（"全部約定"or"一部約定"）以外の場合
    public void testProcess_C0006()
    {
        final String STR_METHOD_NAME = "testProcess_C0006()";
        log.entering(TEST_START + STR_METHOD_NAME);

        try
        {
            TestDBUtility.deleteAll(EqtypeOrderUnitRow.TYPE);
            EqtypeOrderUnitParams eqtypeOrderUnitParams = TestDBUtility.getEqtypeOrderUnitRow();
            eqtypeOrderUnitParams.setOrderCateg(OrderCategEnum.OPEN_MARGIN);
            TestDBUtility.insertWithDel(eqtypeOrderUnitParams);

            FinApp l_finApp = (FinApp)Services.getService(FinApp.class);
            TradingModule l_tradingMod =
                l_finApp.getTradingModule(ProductTypeEnum.EQUITY);
            WEB3EquityOrderManager l_orderManager =
                (WEB3EquityOrderManager)l_tradingMod.getOrderManager();
            EqTypeOrderUnit l_orderUnit =
                (EqTypeOrderUnit)l_orderManager.getOrderUnit(3304148080001L);

            HostEquityOrderExecNotifyParams l_orderExecNotifyParams = new HostEquityOrderExecNotifyParams();
            l_orderExecNotifyParams.setDealedType("3");

            l_callback = new WEB3EquityOrderExecNotifyTransactionCallback(l_orderUnit, l_orderExecNotifyParams);
            l_callback.process();
            fail();
        }
        catch(DataCallbackException l_ex)
        {
            assertEquals(WEB3ErrorCatalog.SYSTEM_ERROR_80025, l_ex.getDetails());
        }
        catch (Exception l_ex)
        {
            log.error(STR_METHOD_NAME, l_ex);
            fail();
        }
        log.info(STR_METHOD_NAME
            + ">>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>test pass !!");
        log.exiting(TEST_END + STR_METHOD_NAME);
    }

    //取得した注文単位＝信用取引,現物株式の場合以外
    //SYSTEM_ERROR_80025
    public void testProcess_C0007()
    {
        final String STR_METHOD_NAME = "testProcess_C0007()";
        log.entering(TEST_START + STR_METHOD_NAME);

        try
        {
            TestDBUtility.deleteAll(EqtypeOrderUnitRow.TYPE);
            EqtypeOrderUnitParams eqtypeOrderUnitParams = TestDBUtility.getEqtypeOrderUnitRow();
            eqtypeOrderUnitParams.setOrderCateg(OrderCategEnum.UNDEFINED);
            TestDBUtility.insertWithDel(eqtypeOrderUnitParams);

            FinApp l_finApp = (FinApp)Services.getService(FinApp.class);
            TradingModule l_tradingMod =
                l_finApp.getTradingModule(ProductTypeEnum.EQUITY);
            WEB3EquityOrderManager l_orderManager =
                (WEB3EquityOrderManager)l_tradingMod.getOrderManager();
            EqTypeOrderUnit l_orderUnit =
                (EqTypeOrderUnit)l_orderManager.getOrderUnit(3304148080001L);

            HostEquityOrderExecNotifyParams l_orderExecNotifyParams = new HostEquityOrderExecNotifyParams();

            l_callback = new WEB3EquityOrderExecNotifyTransactionCallback(l_orderUnit, l_orderExecNotifyParams);
            l_callback.process();
            fail();
        }
        catch(DataCallbackException l_ex)
        {
            assertEquals(WEB3ErrorCatalog.SYSTEM_ERROR_80025, l_ex.getDetails());
        }
        catch (Exception l_ex)
        {
            log.error(STR_METHOD_NAME, l_ex);
            fail();
        }
        log.info(STR_METHOD_NAME
            + ">>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>test pass !!");
        log.exiting(TEST_END + STR_METHOD_NAME);
    }

    //normalcase
    public void testProcess_C0008()
    {
        final String STR_METHOD_NAME = "testProcess_C0008()";
        log.entering(TEST_START + STR_METHOD_NAME);

        try
        {
            TestDBUtility.deleteAll(EqtypeOrderUnitRow.TYPE);
            EqtypeOrderUnitParams eqtypeOrderUnitParams = TestDBUtility.getEqtypeOrderUnitRow();
            eqtypeOrderUnitParams.setOrderCateg(OrderCategEnum.OPEN_MARGIN);
            TestDBUtility.insertWithDel(eqtypeOrderUnitParams);

            FinApp l_finApp = (FinApp)Services.getService(FinApp.class);
            TradingModule l_tradingMod =
                l_finApp.getTradingModule(ProductTypeEnum.EQUITY);
            WEB3EquityOrderManager l_orderManager =
                (WEB3EquityOrderManager)l_tradingMod.getOrderManager();
            EqTypeOrderUnit l_orderUnit =
                (EqTypeOrderUnit)l_orderManager.getOrderUnit(3304148080001L);

            HostEquityOrderExecNotifyParams l_orderExecNotifyParams = new HostEquityOrderExecNotifyParams();
            l_orderExecNotifyParams.setDealedType(WEB3DealedTypeDef.CANCEL);

            l_callback = new WEB3EquityOrderExecNotifyTransactionCallback(l_orderUnit, l_orderExecNotifyParams);

            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                    "webbroker3.equity.service.delegate.stdimpls.WEB3MarginOrderExecNotifyUnitServiceImpl",
                    "notifyExecuteCancel",
                    new Class[] {EqTypeOrderUnit.class, HostEquityOrderExecNotifyParams.class},
                    null);

            l_callback.process();
            assertEquals(WEB3StatusDef.DEALT, l_orderExecNotifyParams.getStatus());
            assertEquals(GtlUtils.getSystemTimestamp(), l_orderExecNotifyParams.getLastUpdatedTimestamp());
        }
        catch (Exception l_ex)
        {
            log.error(STR_METHOD_NAME, l_ex);
            fail();
        }
        log.info(STR_METHOD_NAME
            + ">>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>test pass !!");
        log.exiting(TEST_END + STR_METHOD_NAME);
    }

}
@
