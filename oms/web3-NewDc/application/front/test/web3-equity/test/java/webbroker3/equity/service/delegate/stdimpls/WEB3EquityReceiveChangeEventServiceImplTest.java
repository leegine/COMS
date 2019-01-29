head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.04.07.00.54.38;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	5444d9d078c63f4;
filename	WEB3EquityReceiveChangeEventServiceImplTest.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
 Copyright        : (株)大和総研 証券ソリューションシステム第二部
 File Name        : WEB3EquityReceiveChangeEventServiceImplTest.java
 Author Name      : Daiwa Institute of Research
 Revision History : 2008/07/30 楊夫志(中訊) 新規作成 モデル 132
*/
package webbroker3.equity.service.delegate.stdimpls;

import java.sql.Timestamp;
import java.util.Date;

import com.fitechlabs.xtrade.kernel.boot.Services;
import com.fitechlabs.xtrade.plugin.tc.eqtype.EqTypeExecutionConditionType;
import com.fitechlabs.xtrade.plugin.tc.eqtype.EqTypeOrder;
import com.fitechlabs.xtrade.plugin.tc.eqtype.EqTypeOrderUnit;
import com.fitechlabs.xtrade.plugin.tc.eqtype.data.EqtypeOrderUnitParams;
import com.fitechlabs.xtrade.plugin.tc.eqtype.data.EqtypeOrderUnitRow;
import com.fitechlabs.xtrade.plugin.tc.gentrade.FinApp;
import com.fitechlabs.xtrade.plugin.tc.gentrade.Order;
import com.fitechlabs.xtrade.plugin.tc.gentrade.OrderAction;
import com.fitechlabs.xtrade.plugin.tc.gentrade.OrderCategEnum;
import com.fitechlabs.xtrade.plugin.tc.gentrade.OrderExecution;
import com.fitechlabs.xtrade.plugin.tc.gentrade.OrderExpirationStatusEnum;
import com.fitechlabs.xtrade.plugin.tc.gentrade.OrderOpenStatusEnum;
import com.fitechlabs.xtrade.plugin.tc.gentrade.OrderStatusEnum;
import com.fitechlabs.xtrade.plugin.tc.gentrade.OrderTypeEnum;
import com.fitechlabs.xtrade.plugin.tc.gentrade.OrderUnit;
import com.fitechlabs.xtrade.plugin.tc.gentrade.ProcessingResult;
import com.fitechlabs.xtrade.plugin.tc.gentrade.Product;
import com.fitechlabs.xtrade.plugin.tc.gentrade.ProductTypeEnum;
import com.fitechlabs.xtrade.plugin.tc.gentrade.SideEnum;
import com.fitechlabs.xtrade.plugin.tc.gentrade.SubAccount;
import com.fitechlabs.xtrade.plugin.tc.gentrade.SubAccountTypeEnum;
import com.fitechlabs.xtrade.plugin.tc.gentrade.TaxTypeEnum;
import com.fitechlabs.xtrade.plugin.tc.gentrade.TradedProduct;
import com.fitechlabs.xtrade.plugin.tc.gentrade.TradingModule;
import com.fitechlabs.xtrade.plugin.tc.gentrade.data.InstitutionParams;
import com.fitechlabs.xtrade.plugin.tc.gentrade.data.InstitutionRow;
import com.fitechlabs.xtrade.plugin.tc.gentrade.data.MainAccountParams;
import com.fitechlabs.xtrade.plugin.tc.gentrade.data.MainAccountRow;
import com.fitechlabs.xtrade.plugin.tc.gentrade.data.MarketParams;
import com.fitechlabs.xtrade.plugin.tc.gentrade.data.MarketRow;
import com.fitechlabs.xtrade.plugin.tc.gentrade.data.ProductParams;
import com.fitechlabs.xtrade.plugin.tc.gentrade.data.ProductRow;
import com.fitechlabs.xtrade.plugin.tc.gentrade.data.SubAccountParams;
import com.fitechlabs.xtrade.plugin.tc.gentrade.data.SubAccountRow;
import com.fitechlabs.xtrade.plugin.tc.gentrade.market.messages.MarketResponseMessage;

import test.util.TestDBUtility;

import webbroker3.common.WEB3BaseException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.common.define.WEB3CanmodReceiptTypeDef;
import webbroker3.common.define.WEB3ModifiedResultDef;
import webbroker3.common.define.WEB3OrderingConditionDef;
import webbroker3.common.define.WEB3PriceConditionSONARDef;
import webbroker3.common.define.WEB3RequestTypeDef;
import webbroker3.equity.WEB3EquityEstimatedDeliveryPrice;
import webbroker3.equity.WEB3EquityOrderManager;
import webbroker3.equity.WEB3EquityTradedProduct;
import webbroker3.equity.data.HostEqtypeOrderClmdReceiptParams;
import webbroker3.gentrade.WEB3GentradeCommission;
import webbroker3.mock.TestBaseForMock;
import webbroker3.util.WEB3LogUtility;

public class WEB3EquityReceiveChangeEventServiceImplTest extends TestBaseForMock
{
    WEB3LogUtility log = WEB3LogUtility.getInstance(WEB3EquityReceiveChangeEventServiceImplTest.class);

    WEB3EquityReceiveChangeEventServiceImpl l_serviceImpl= null;

    public WEB3EquityReceiveChangeEventServiceImplTest(String arg0)
    {
        super(arg0);
    }

    protected void setUp() throws Exception
    {
        super.setUp();
        MOCK_MANAGER.setIsMockUsed(true);
        l_serviceImpl = new WEB3EquityReceiveChangeEventServiceImpl();
    }

    protected void tearDown() throws Exception
    {
        super.tearDown();
    }

    //getOrderUnit(注文単位.注文単位ID : long)なし
    //WEB3ErrorCatalog.SYSTEM_ERROR_80005
    public void testNotifyChange_C0001()
    {
        final String STR_METHOD_NAME = "testNotifyChange_C0001()";
        log.entering(TEST_START + STR_METHOD_NAME);

        try
        {
            TestDBUtility.deleteAll(EqtypeOrderUnitRow.TYPE);
            EqtypeOrderUnitParams eqtypeOrderUnitParams = TestDBUtility.getEqtypeOrderUnitRow();
            TestDBUtility.insertWithDel(eqtypeOrderUnitParams);
            
            EqTypeOrderUnitForTest forTest = new EqTypeOrderUnitForTest();
            HostEqtypeOrderClmdReceiptParams l_params = new HostEqtypeOrderClmdReceiptParams();
            l_serviceImpl.notifyChange(l_params, forTest);
            fail();
        }
        catch(WEB3BaseException l_ex)
        {
            assertEquals( WEB3ErrorCatalog.SYSTEM_ERROR_80005, l_ex.getErrorInfo());
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

    //validate訂正結果コード()
    public void testNotifyChange_C0002()
    {
        final String STR_METHOD_NAME = "testNotifyChange_C0002()";
        log.entering(TEST_START + STR_METHOD_NAME);

        try
        {
            TestDBUtility.deleteAll(EqtypeOrderUnitRow.TYPE);
            EqtypeOrderUnitParams eqtypeOrderUnitParams = TestDBUtility.getEqtypeOrderUnitRow();
            TestDBUtility.insertWithDel(eqtypeOrderUnitParams);
            
            FinApp l_finApp = (FinApp)Services.getService(FinApp.class);
            TradingModule l_tradingMod =
                l_finApp.getTradingModule(ProductTypeEnum.EQUITY);
            WEB3EquityOrderManager l_orderManager =
                (WEB3EquityOrderManager)l_tradingMod.getOrderManager();
            EqTypeOrderUnit l_orderUnit =
                (EqTypeOrderUnit)l_orderManager.getOrderUnit(3304148080001L);
            
            HostEqtypeOrderClmdReceiptParams l_params = new HostEqtypeOrderClmdReceiptParams();
            l_params.setModifiedResult(WEB3ModifiedResultDef.PARTIALLY_CANCEL);
            l_serviceImpl.notifyChange(l_params, l_orderUnit);
            fail();
        }
        catch(WEB3BaseException l_ex)
        {
            assertEquals(WEB3ErrorCatalog.BUSINESS_ERROR_00129, l_ex.getErrorInfo());
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

    //get補助口座(口座ID : long, 補助口座ID : long)なし
    //SYSTEM_ERROR_80005
    public void testNotifyChange_C0003()
    {
        final String STR_METHOD_NAME = "testNotifyChange_C0003()";
        log.entering(TEST_START + STR_METHOD_NAME);

        try
        {
            TestDBUtility.deleteAll(EqtypeOrderUnitRow.TYPE);
            EqtypeOrderUnitParams eqtypeOrderUnitParams = TestDBUtility.getEqtypeOrderUnitRow();
            eqtypeOrderUnitParams.setAccountId(333812512202L);
            TestDBUtility.insertWithDel(eqtypeOrderUnitParams);
            
            TestDBUtility.deleteAll(SubAccountRow.TYPE);
            SubAccountParams subAccountParams = TestDBUtility.getSubAccountRow();
            subAccountParams.setSubAccountType(SubAccountTypeEnum.EQUITY_SUB_ACCOUNT);
            TestDBUtility.insertWithDel(subAccountParams);
            
            TestDBUtility.deleteAll(MainAccountRow.TYPE);
            MainAccountParams mainAccountParams = TestDBUtility.getMainAccountRow();
            mainAccountParams.setAccountId(333812512203L);
            TestDBUtility.insertWithDel(mainAccountParams);
            
            FinApp l_finApp = (FinApp)Services.getService(FinApp.class);
            TradingModule l_tradingMod =
                l_finApp.getTradingModule(ProductTypeEnum.EQUITY);
            WEB3EquityOrderManager l_orderManager =
                (WEB3EquityOrderManager)l_tradingMod.getOrderManager();
            EqTypeOrderUnit l_orderUnit =
                (EqTypeOrderUnit)l_orderManager.getOrderUnit(3304148080001L);
            
            HostEqtypeOrderClmdReceiptParams l_params = new HostEqtypeOrderClmdReceiptParams();
            l_params.setModifiedResult(WEB3ModifiedResultDef.CHANGE_SLIP_DESTORIED);
            l_params.setModifiedPriceConditionType(WEB3PriceConditionSONARDef.DEFAULT);
            l_serviceImpl.notifyChange(l_params, l_orderUnit);
            fail();
        }
        catch(WEB3BaseException l_ex)
        {
            assertEquals(WEB3ErrorCatalog.SYSTEM_ERROR_80005, l_ex.getErrorInfo());
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

    //1.13. get取引銘柄()なし
    //SYSTEM_ERROR_80005
    public void testNotifyChange_C0004()
    {
        final String STR_METHOD_NAME = "testNotifyChange_C0004()";
        log.entering(TEST_START + STR_METHOD_NAME);

        try
        {
            TestDBUtility.deleteAll(EqtypeOrderUnitRow.TYPE);
            EqtypeOrderUnitParams eqtypeOrderUnitParams = TestDBUtility.getEqtypeOrderUnitRow();
            TestDBUtility.insertWithDel(eqtypeOrderUnitParams);
            
            TestDBUtility.deleteAll(SubAccountRow.TYPE);
            SubAccountParams subAccountParams = TestDBUtility.getSubAccountRow();
            subAccountParams.setSubAccountType(SubAccountTypeEnum.EQUITY_SUB_ACCOUNT);
            TestDBUtility.insertWithDel(subAccountParams);
            
            TestDBUtility.deleteAll(MainAccountRow.TYPE);
            MainAccountParams mainAccountParams = TestDBUtility.getMainAccountRow();
            mainAccountParams.setAccountId(333812512203L);
            TestDBUtility.insertWithDel(mainAccountParams);
            
            FinApp l_finApp = (FinApp)Services.getService(FinApp.class);
            TradingModule l_tradingMod =
                l_finApp.getTradingModule(ProductTypeEnum.EQUITY);
            WEB3EquityOrderManager l_orderManager =
                (WEB3EquityOrderManager)l_tradingMod.getOrderManager();
            EqTypeOrderUnit l_orderUnit =
                (EqTypeOrderUnit)l_orderManager.getOrderUnit(3304148080001L);
            
            HostEqtypeOrderClmdReceiptParams l_params = new HostEqtypeOrderClmdReceiptParams();
            l_params.setModifiedResult(WEB3ModifiedResultDef.CHANGE_SLIP_DESTORIED);
            l_params.setModifiedPriceConditionType(WEB3PriceConditionSONARDef.DEFAULT);
            l_params.setInstitutionCode("11");
            l_serviceImpl.notifyChange(l_params, l_orderUnit);
            fail();
        }
        catch(WEB3BaseException l_ex)
        {
            assertEquals(WEB3ErrorCatalog.SYSTEM_ERROR_80005, l_ex.getErrorInfo());
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

    //isストップ注文有効(EqTypeOrderUnit)
    //訂正結果コード =  1 : 訂正完了
    //通知キュー.訂正取消通知区分＝"訂正失敗"かつ拡張株式注文マネージャ.isストップ注文切替中＝true　@の以外的場合
    //分岐フロー：　@getストップ注文失効時概算代金計算結果の戻り値がnullの場合
    //1.22. 通知キュー.訂正取消通知区分＝"訂正完了"の場合 ,l_result.isSuccessResult()
    //1.22.4. 全部約定（isFullyExecuted( )==false）の場合のみ
    public void testNotifyChange_C0005()
    {
        final String STR_METHOD_NAME = "testNotifyChange_C0005()";
        log.entering(TEST_START + STR_METHOD_NAME);

        try
        {
            TestDBUtility.deleteAll(EqtypeOrderUnitRow.TYPE);
            EqtypeOrderUnitParams eqtypeOrderUnitParams = TestDBUtility.getEqtypeOrderUnitRow();
            eqtypeOrderUnitParams.setOrderConditionType(WEB3OrderingConditionDef.W_LIMIT_PRICE);
            eqtypeOrderUnitParams.setRequestType(WEB3RequestTypeDef.QUOTE_SERVER);
            eqtypeOrderUnitParams.setProductId(3304148080000L);
            TestDBUtility.insertWithDel(eqtypeOrderUnitParams);
            
            TestDBUtility.deleteAll(SubAccountRow.TYPE);
            SubAccountParams subAccountParams = TestDBUtility.getSubAccountRow();
            subAccountParams.setSubAccountType(SubAccountTypeEnum.EQUITY_SUB_ACCOUNT);
            TestDBUtility.insertWithDel(subAccountParams);
            
            TestDBUtility.deleteAll(MainAccountRow.TYPE);
            MainAccountParams mainAccountParams = TestDBUtility.getMainAccountRow();
            mainAccountParams.setAccountId(333812512203L);
            TestDBUtility.insertWithDel(mainAccountParams);
            
            TestDBUtility.deleteAll(MarketRow.TYPE);
            MarketParams marketParams = TestDBUtility.getMarketRow();
            TestDBUtility.insertWithDel(marketParams);
            
            TestDBUtility.deleteAll(InstitutionRow.TYPE);
            InstitutionParams institutionParams = TestDBUtility.getInstitutionRow();
            TestDBUtility.insertWithDel(institutionParams);
            
            TestDBUtility.deleteAll(ProductRow.TYPE);
            ProductParams productParams = TestDBUtility.getProductRow();
            productParams.setProductType(ProductTypeEnum.EQUITY);
            TestDBUtility.insertWithDel(productParams);
            
            WEB3EquityEstimatedDeliveryPrice l_price = new WEB3EquityEstimatedDeliveryPrice();
            l_price.setCalcUnitPrice(100);
            l_price.setEstimateDeliveryAmount(100);
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                    "webbroker3.equity.WEB3EquityOrderManager",
                    "calcEstimateDeliveryAmount",
                    new Class[] {
                            WEB3GentradeCommission.class, double.class,
                            double.class, double.class,
                            EqTypeExecutionConditionType.class, EqTypeExecutionConditionType.class,
                            String.class,
                            String.class,
                            String.class,
                            boolean.class,
                            SubAccount.class,
                            WEB3EquityTradedProduct.class,
                            double.class,
                            boolean.class,
                            double.class,
                            double.class,
                            boolean.class
                            },
                            l_price);
            
            FinApp l_finApp = (FinApp)Services.getService(FinApp.class);
            TradingModule l_tradingMod =
                l_finApp.getTradingModule(ProductTypeEnum.EQUITY);
            WEB3EquityOrderManager l_orderManager =
                (WEB3EquityOrderManager)l_tradingMod.getOrderManager();
            EqTypeOrderUnit l_orderUnit =
                (EqTypeOrderUnit)l_orderManager.getOrderUnit(3304148080001L);
            
            HostEqtypeOrderClmdReceiptParams l_params = new HostEqtypeOrderClmdReceiptParams();
            l_params.setModifiedResult(WEB3ModifiedResultDef.CHANGE_SLIP_DESTORIED);
            l_params.setModifiedPriceConditionType(WEB3PriceConditionSONARDef.DEFAULT);
            l_params.setInstitutionCode("0D");
            l_params.setCanmodReceiptType(WEB3CanmodReceiptTypeDef.CHANGED);
            l_serviceImpl.notifyChange(l_params, l_orderUnit);
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

    //isストップ注文有効(EqTypeOrderUnit)
    //訂正結果コード =  1 : 訂正完了
    //通知キュー.訂正取消通知区分＝"訂正失敗"かつ拡張株式注文マネージャ.isストップ注文切替中＝true　@の以外的場合
    //分岐フロー：　@getストップ注文失効時概算代金計算結果の戻り値がnullの場合
    //1.22. 通知キュー.訂正取消通知区分＝"訂正完了"の場合 ,l_result.isFailedResult()
    //1.22.4. 全部約定（isFullyExecuted( )==true）の場合のみ
    public void testNotifyChange_C0006()
    {
        final String STR_METHOD_NAME = "testNotifyChange_C0006()";
        log.entering(TEST_START + STR_METHOD_NAME);

        try
        {
            TestDBUtility.deleteAll(EqtypeOrderUnitRow.TYPE);
            EqtypeOrderUnitParams eqtypeOrderUnitParams = TestDBUtility.getEqtypeOrderUnitRow();
            eqtypeOrderUnitParams.setOrderConditionType(WEB3OrderingConditionDef.W_LIMIT_PRICE);
            eqtypeOrderUnitParams.setRequestType(WEB3RequestTypeDef.QUOTE_SERVER);
            eqtypeOrderUnitParams.setConfirmedQuantity(100);
            eqtypeOrderUnitParams.setExecutedQuantity(100);
            eqtypeOrderUnitParams.setProductId(3304148080000L);
            TestDBUtility.insertWithDel(eqtypeOrderUnitParams);
            
            TestDBUtility.deleteAll(SubAccountRow.TYPE);
            SubAccountParams subAccountParams = TestDBUtility.getSubAccountRow();
            subAccountParams.setSubAccountType(SubAccountTypeEnum.EQUITY_SUB_ACCOUNT);
            TestDBUtility.insertWithDel(subAccountParams);
            
            TestDBUtility.deleteAll(MainAccountRow.TYPE);
            MainAccountParams mainAccountParams = TestDBUtility.getMainAccountRow();
            mainAccountParams.setAccountId(333812512203L);
            TestDBUtility.insertWithDel(mainAccountParams);
            
            TestDBUtility.deleteAll(MarketRow.TYPE);
            MarketParams marketParams = TestDBUtility.getMarketRow();
            TestDBUtility.insertWithDel(marketParams);
            
            TestDBUtility.deleteAll(InstitutionRow.TYPE);
            InstitutionParams institutionParams = TestDBUtility.getInstitutionRow();
            TestDBUtility.insertWithDel(institutionParams);
            
            TestDBUtility.deleteAll(ProductRow.TYPE);
            ProductParams productParams = TestDBUtility.getProductRow();
            productParams.setProductType(ProductTypeEnum.EQUITY);
            TestDBUtility.insertWithDel(productParams);
            
            WEB3EquityEstimatedDeliveryPrice l_price = new WEB3EquityEstimatedDeliveryPrice();
            l_price.setCalcUnitPrice(100);
            l_price.setEstimateDeliveryAmount(100);
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                    "webbroker3.equity.WEB3EquityOrderManager",
                    "calcEstimateDeliveryAmount",
                    new Class[] {
                            WEB3GentradeCommission.class, double.class,
                            double.class, double.class,
                            EqTypeExecutionConditionType.class, EqTypeExecutionConditionType.class,
                            String.class,
                            String.class,
                            String.class,
                            boolean.class,
                            SubAccount.class,
                            WEB3EquityTradedProduct.class,
                            double.class,
                            boolean.class,
                            double.class,
                            double.class,
                            boolean.class
                            },
                            l_price);
            
            ProcessingResult result = ProcessingResult.newFailedResultInstance(WEB3ErrorCatalog.BUSINESS_ERROR_00004);
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                    "com.fitechlabs.xtrade.plugin.tc.eqtype.stdimpls.EqTypeMarketResponseReceiverCallbackServiceImpl",
                    "process",
                    new Class[] {MarketResponseMessage.class},
                    result);
                    
            FinApp l_finApp = (FinApp)Services.getService(FinApp.class);
            TradingModule l_tradingMod =
                l_finApp.getTradingModule(ProductTypeEnum.EQUITY);
            WEB3EquityOrderManager l_orderManager =
                (WEB3EquityOrderManager)l_tradingMod.getOrderManager();
            EqTypeOrderUnit l_orderUnit =
                (EqTypeOrderUnit)l_orderManager.getOrderUnit(3304148080001L);
            
            HostEqtypeOrderClmdReceiptParams l_params = new HostEqtypeOrderClmdReceiptParams();
            l_params.setModifiedResult(WEB3ModifiedResultDef.CHANGE_SLIP_DESTORIED);
            l_params.setModifiedPriceConditionType(WEB3PriceConditionSONARDef.DEFAULT);
            l_params.setInstitutionCode("0D");
            l_params.setCanmodReceiptType(WEB3CanmodReceiptTypeDef.CHANGED);
            l_serviceImpl.notifyChange(l_params, l_orderUnit);
            fail();
        }
        catch(WEB3BaseException l_ex)
        {
            assertEquals(WEB3ErrorCatalog.BUSINESS_ERROR_00004, l_ex.getErrorInfo());
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

    //isストップ注文有効(EqTypeOrderUnit)
    //訂正結果コード =  1 : 訂正完了
    //通知キュー.訂正取消通知区分＝"訂正失敗"かつ拡張株式注文マネージャ.isストップ注文切替中＝true　@の以外的場合
    //分岐フロー：　@getストップ注文失効時概算代金計算結果の戻り値がnullの場合
    //1.22. 通知キュー.訂正取消通知区分＝"訂正完了"の場合 ,l_result.isSuccessResult()
    //1.22.4. 全部約定（isFullyExecuted( )==true）の場合のみ
    public void testNotifyChange_C0007()
    {
        final String STR_METHOD_NAME = "testNotifyChange_C0007()";
        log.entering(TEST_START + STR_METHOD_NAME);

        try
        {
            TestDBUtility.deleteAll(EqtypeOrderUnitRow.TYPE);
            EqtypeOrderUnitParams eqtypeOrderUnitParams = TestDBUtility.getEqtypeOrderUnitRow();
            eqtypeOrderUnitParams.setOrderConditionType(WEB3OrderingConditionDef.W_LIMIT_PRICE);
            eqtypeOrderUnitParams.setRequestType(WEB3RequestTypeDef.QUOTE_SERVER);
            eqtypeOrderUnitParams.setProductId(3304148080000L);
            eqtypeOrderUnitParams.setConfirmedQuantity(500);
            eqtypeOrderUnitParams.setExecutedQuantity(500);
            TestDBUtility.insertWithDel(eqtypeOrderUnitParams);
            
            TestDBUtility.deleteAll(SubAccountRow.TYPE);
            SubAccountParams subAccountParams = TestDBUtility.getSubAccountRow();
            subAccountParams.setSubAccountType(SubAccountTypeEnum.EQUITY_SUB_ACCOUNT);
            TestDBUtility.insertWithDel(subAccountParams);
            
            TestDBUtility.deleteAll(MainAccountRow.TYPE);
            MainAccountParams mainAccountParams = TestDBUtility.getMainAccountRow();
            mainAccountParams.setAccountId(333812512203L);
            TestDBUtility.insertWithDel(mainAccountParams);
            
            TestDBUtility.deleteAll(MarketRow.TYPE);
            MarketParams marketParams = TestDBUtility.getMarketRow();
            TestDBUtility.insertWithDel(marketParams);
            
            TestDBUtility.deleteAll(InstitutionRow.TYPE);
            InstitutionParams institutionParams = TestDBUtility.getInstitutionRow();
            TestDBUtility.insertWithDel(institutionParams);
            
            TestDBUtility.deleteAll(ProductRow.TYPE);
            ProductParams productParams = TestDBUtility.getProductRow();
            productParams.setProductType(ProductTypeEnum.EQUITY);
            TestDBUtility.insertWithDel(productParams);
            
            WEB3EquityEstimatedDeliveryPrice l_price = new WEB3EquityEstimatedDeliveryPrice();
            l_price.setCalcUnitPrice(100);
            l_price.setEstimateDeliveryAmount(100);
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                    "webbroker3.equity.WEB3EquityOrderManager",
                    "calcEstimateDeliveryAmount",
                    new Class[] {
                            WEB3GentradeCommission.class, double.class,
                            double.class, double.class,
                            EqTypeExecutionConditionType.class, EqTypeExecutionConditionType.class,
                            String.class,
                            String.class,
                            String.class,
                            boolean.class,
                            SubAccount.class,
                            WEB3EquityTradedProduct.class,
                            double.class,
                            boolean.class,
                            double.class,
                            double.class,
                            boolean.class
                            },
                            l_price);
            
            ProcessingResult result = ProcessingResult.newSuccessResultInstance();
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                    "com.fitechlabs.xtrade.plugin.tc.eqtype.stdimpls.EqTypeMarketResponseReceiverCallbackServiceImpl",
                    "process",
                    new Class[] {MarketResponseMessage.class},
                    result);
                    
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                    "webbroker3.equity.service.delegate.stdimpls.WEB3EquityExecutedMailSenderServiceImpl",
                    "sendMailProcess", 
                    new Class[]{OrderUnit.class, String.class},
                    null);
            
            FinApp l_finApp = (FinApp)Services.getService(FinApp.class);
            TradingModule l_tradingMod =
                l_finApp.getTradingModule(ProductTypeEnum.EQUITY);
            WEB3EquityOrderManager l_orderManager =
                (WEB3EquityOrderManager)l_tradingMod.getOrderManager();
            EqTypeOrderUnit l_orderUnit =
                (EqTypeOrderUnit)l_orderManager.getOrderUnit(3304148080001L);
            
            HostEqtypeOrderClmdReceiptParams l_params = new HostEqtypeOrderClmdReceiptParams();
            l_params.setModifiedResult(WEB3ModifiedResultDef.CHANGE_SLIP_DESTORIED);
            l_params.setModifiedPriceConditionType(WEB3PriceConditionSONARDef.DEFAULT);
            l_params.setInstitutionCode("0D");
            l_params.setCanmodReceiptType(WEB3CanmodReceiptTypeDef.CHANGED);
            l_serviceImpl.notifyChange(l_params, l_orderUnit);
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

    //isストップ注文有効(EqTypeOrderUnit)
    //訂正結果コード =  1 : 訂正完了以外的場合
    //通知キュー.訂正取消通知区分＝"訂正失敗"かつ拡張株式注文マネージャ.isストップ注文切替中＝true　@の的場合
    //分岐フロー：　@getストップ注文失効時概算代金計算結果の戻り値がnot nullの場合
    //1.22. 通知キュー.訂正取消通知区分＝"訂正完了"の以外的場合 ,l_result.isSuccessResult()
    public void testNotifyChange_C0008()
    {
        final String STR_METHOD_NAME = "testNotifyChange_C0008()";
        log.entering(TEST_START + STR_METHOD_NAME);

        try
        {
            TestDBUtility.deleteAll(EqtypeOrderUnitRow.TYPE);
            EqtypeOrderUnitParams eqtypeOrderUnitParams = TestDBUtility.getEqtypeOrderUnitRow();
            eqtypeOrderUnitParams.setOrderConditionType(WEB3OrderingConditionDef.W_LIMIT_PRICE);
            eqtypeOrderUnitParams.setRequestType(WEB3RequestTypeDef.QUOTE_SERVER);
            eqtypeOrderUnitParams.setProductId(3304148080000L);
            TestDBUtility.insertWithDel(eqtypeOrderUnitParams);
            
            TestDBUtility.deleteAll(SubAccountRow.TYPE);
            SubAccountParams subAccountParams = TestDBUtility.getSubAccountRow();
            subAccountParams.setSubAccountType(SubAccountTypeEnum.EQUITY_SUB_ACCOUNT);
            TestDBUtility.insertWithDel(subAccountParams);
            
            TestDBUtility.deleteAll(MainAccountRow.TYPE);
            MainAccountParams mainAccountParams = TestDBUtility.getMainAccountRow();
            mainAccountParams.setAccountId(333812512203L);
            TestDBUtility.insertWithDel(mainAccountParams);
            
            TestDBUtility.deleteAll(MarketRow.TYPE);
            MarketParams marketParams = TestDBUtility.getMarketRow();
            TestDBUtility.insertWithDel(marketParams);
            
            TestDBUtility.deleteAll(InstitutionRow.TYPE);
            InstitutionParams institutionParams = TestDBUtility.getInstitutionRow();
            TestDBUtility.insertWithDel(institutionParams);
            
            TestDBUtility.deleteAll(ProductRow.TYPE);
            ProductParams productParams = TestDBUtility.getProductRow();
            productParams.setProductType(ProductTypeEnum.EQUITY);
            TestDBUtility.insertWithDel(productParams);
            
            WEB3EquityEstimatedDeliveryPrice l_price = new WEB3EquityEstimatedDeliveryPrice();
            l_price.setCalcUnitPrice(100);
            l_price.setEstimateDeliveryAmount(100);
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                    "webbroker3.equity.WEB3EquityOrderManager",
                    "calcEstimateDeliveryAmount",
                    new Class[] {
                            WEB3GentradeCommission.class, double.class,
                            double.class, double.class,
                            EqTypeExecutionConditionType.class, EqTypeExecutionConditionType.class,
                            String.class,
                            String.class,
                            String.class,
                            boolean.class,
                            SubAccount.class,
                            WEB3EquityTradedProduct.class,
                            double.class,
                            boolean.class,
                            double.class,
                            double.class,
                            boolean.class
                            },
                            l_price);
            
            ProcessingResult result = ProcessingResult.newSuccessResultInstance();
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                    "com.fitechlabs.xtrade.plugin.tc.eqtype.stdimpls.EqTypeMarketResponseReceiverCallbackServiceImpl",
                    "process",
                    new Class[] {MarketResponseMessage.class},
                    result);
            
            WEB3EquityEstimatedDeliveryPrice price = new WEB3EquityEstimatedDeliveryPrice();
            price.setCalcUnitPrice(100);
            price.setEstimateDeliveryAmount(100);
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue("webbroker3.equity.WEB3EquityOrderManager",
                    "getStopOrderExpireEstimatedPrice", new Class[]
                    {EqTypeOrderUnit.class, SubAccount.class},
                    price);

            FinApp l_finApp = (FinApp)Services.getService(FinApp.class);
            TradingModule l_tradingMod =
                l_finApp.getTradingModule(ProductTypeEnum.EQUITY);
            WEB3EquityOrderManager l_orderManager =
                (WEB3EquityOrderManager)l_tradingMod.getOrderManager();
            EqTypeOrderUnit l_orderUnit =
                (EqTypeOrderUnit)l_orderManager.getOrderUnit(3304148080001L);
            
            HostEqtypeOrderClmdReceiptParams l_params = new HostEqtypeOrderClmdReceiptParams();
            l_params.setModifiedResult(WEB3ModifiedResultDef.CHANGE_SLIP_DESTORIED);
            l_params.setModifiedPriceConditionType(WEB3PriceConditionSONARDef.DEFAULT);
            l_params.setInstitutionCode("0D");
            l_params.setCanmodReceiptType(WEB3CanmodReceiptTypeDef.CHANGE_FAILED);
            l_serviceImpl.notifyChange(l_params, l_orderUnit);
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

    //isストップ注文有効(EqTypeOrderUnit)
    //訂正結果コード =  1 : 訂正完了以外的場合
    //通知キュー.訂正取消通知区分＝"訂正失敗"かつ拡張株式注文マネージャ.isストップ注文切替中＝true　@の的場合
    //分岐フロー：　@getストップ注文失効時概算代金計算結果の戻り値がnot nullの場合
    //1.22. 通知キュー.訂正取消通知区分＝"訂正完了"の以外的場合 ,l_result.isFailedResult()
    public void testNotifyChange_C0009()
    {
        final String STR_METHOD_NAME = "testNotifyChange_C0009()";
        log.entering(TEST_START + STR_METHOD_NAME);

        try
        {
            TestDBUtility.deleteAll(EqtypeOrderUnitRow.TYPE);
            EqtypeOrderUnitParams eqtypeOrderUnitParams = TestDBUtility.getEqtypeOrderUnitRow();
            eqtypeOrderUnitParams.setOrderConditionType(WEB3OrderingConditionDef.W_LIMIT_PRICE);
            eqtypeOrderUnitParams.setRequestType(WEB3RequestTypeDef.QUOTE_SERVER);
            eqtypeOrderUnitParams.setProductId(3304148080000L);
            TestDBUtility.insertWithDel(eqtypeOrderUnitParams);
            
            TestDBUtility.deleteAll(SubAccountRow.TYPE);
            SubAccountParams subAccountParams = TestDBUtility.getSubAccountRow();
            subAccountParams.setSubAccountType(SubAccountTypeEnum.EQUITY_SUB_ACCOUNT);
            TestDBUtility.insertWithDel(subAccountParams);
            
            TestDBUtility.deleteAll(MainAccountRow.TYPE);
            MainAccountParams mainAccountParams = TestDBUtility.getMainAccountRow();
            mainAccountParams.setAccountId(333812512203L);
            TestDBUtility.insertWithDel(mainAccountParams);
            
            TestDBUtility.deleteAll(MarketRow.TYPE);
            MarketParams marketParams = TestDBUtility.getMarketRow();
            TestDBUtility.insertWithDel(marketParams);
            
            TestDBUtility.deleteAll(InstitutionRow.TYPE);
            InstitutionParams institutionParams = TestDBUtility.getInstitutionRow();
            TestDBUtility.insertWithDel(institutionParams);
            
            TestDBUtility.deleteAll(ProductRow.TYPE);
            ProductParams productParams = TestDBUtility.getProductRow();
            productParams.setProductType(ProductTypeEnum.EQUITY);
            TestDBUtility.insertWithDel(productParams);
            
            WEB3EquityEstimatedDeliveryPrice l_price = new WEB3EquityEstimatedDeliveryPrice();
            l_price.setCalcUnitPrice(100);
            l_price.setEstimateDeliveryAmount(100);
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                    "webbroker3.equity.WEB3EquityOrderManager",
                    "calcEstimateDeliveryAmount",
                    new Class[] {
                            WEB3GentradeCommission.class, double.class,
                            double.class, double.class,
                            EqTypeExecutionConditionType.class, EqTypeExecutionConditionType.class,
                            String.class,
                            String.class,
                            String.class,
                            boolean.class,
                            SubAccount.class,
                            WEB3EquityTradedProduct.class,
                            double.class,
                            boolean.class,
                            double.class,
                            double.class,
                            boolean.class
                            },
                            l_price);
            
            ProcessingResult result = ProcessingResult.newFailedResultInstance(WEB3ErrorCatalog.BUSINESS_ERROR_00004);
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                    "com.fitechlabs.xtrade.plugin.tc.eqtype.stdimpls.EqTypeMarketResponseReceiverCallbackServiceImpl",
                    "process",
                    new Class[] {MarketResponseMessage.class},
                    result);
            
            WEB3EquityEstimatedDeliveryPrice price = new WEB3EquityEstimatedDeliveryPrice();
            price.setCalcUnitPrice(100);
            price.setEstimateDeliveryAmount(100);
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue("webbroker3.equity.WEB3EquityOrderManager",
                    "getStopOrderExpireEstimatedPrice", new Class[]
                    {EqTypeOrderUnit.class, SubAccount.class},
                    price);

            FinApp l_finApp = (FinApp)Services.getService(FinApp.class);
            TradingModule l_tradingMod =
                l_finApp.getTradingModule(ProductTypeEnum.EQUITY);
            WEB3EquityOrderManager l_orderManager =
                (WEB3EquityOrderManager)l_tradingMod.getOrderManager();
            EqTypeOrderUnit l_orderUnit =
                (EqTypeOrderUnit)l_orderManager.getOrderUnit(3304148080001L);
            
            HostEqtypeOrderClmdReceiptParams l_params = new HostEqtypeOrderClmdReceiptParams();
            l_params.setModifiedResult(WEB3ModifiedResultDef.CHANGE_SLIP_DESTORIED);
            l_params.setModifiedPriceConditionType(WEB3PriceConditionSONARDef.DEFAULT);
            l_params.setInstitutionCode("0D");
            l_params.setCanmodReceiptType(WEB3CanmodReceiptTypeDef.CHANGE_FAILED);
            l_serviceImpl.notifyChange(l_params, l_orderUnit);
            fail();
        }
        catch(WEB3BaseException l_ex)
        {
            assertEquals(WEB3ErrorCatalog.BUSINESS_ERROR_00004, l_ex.getErrorInfo());
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

    //コード値が”0?”（訂正完了に該当）の場合、以下の訂正ケースのいずれかに該当するかチェックする。
    //02：全数訂正（内出来なし） 
    //03：全数訂正（内出来あり）  
    //05：一部訂正 
    //08：一部訂正不能（内出来なし） 
    //09：一部訂正不能（内出来あり）
    public void testValidateChangeResultCode_C0001()
    {
        final String STR_METHOD_NAME = "testValidateChangeResultCode_C0001()";
        log.entering(TEST_START + STR_METHOD_NAME);

        try
        {
            HostEqtypeOrderClmdReceiptParams l_params = new HostEqtypeOrderClmdReceiptParams();
            l_params.setModifiedResult("02");
            l_serviceImpl.validateChangeResultCode(l_params);
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

    //コード値が”0?”（訂正完了に該当）の場合、以下の訂正ケースのいずれかに該当するかチェックする。 
    //いずれにも該当しない場合は、例外をthrowする。 
    public void testValidateChangeResultCode_C0002()
    {
        final String STR_METHOD_NAME = "testValidateChangeResultCode_C0002()";
        log.entering(TEST_START + STR_METHOD_NAME);

        try
        {
            HostEqtypeOrderClmdReceiptParams l_params = new HostEqtypeOrderClmdReceiptParams();
            l_params.setModifiedResult("04");
            l_serviceImpl.validateChangeResultCode(l_params);
            fail();
        }
        catch(WEB3BaseException l_ex)
        {
            assertEquals( WEB3ErrorCatalog.BUSINESS_ERROR_00129, l_ex.getErrorInfo());
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

    //訂正時における、【訂正取消通知キューテーブル】の「訂正結果コード」の値の正当性チェックなしを行う。 
    public void testValidateChangeResultCode_C0003()
    {
        final String STR_METHOD_NAME = "testValidateChangeResultCode_C0003()";
        log.entering(TEST_START + STR_METHOD_NAME);

        try
        {
            HostEqtypeOrderClmdReceiptParams l_params = new HostEqtypeOrderClmdReceiptParams();
            l_params.setModifiedResult("11");
            l_serviceImpl.validateChangeResultCode(l_params);
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

    private class EqTypeOrderUnitForTest implements EqTypeOrderUnit
    {

        public EqTypeOrder getEqTypeOrder()
        {
            // TODO Auto-generated method stub
            return null;
        }

        public long getOrderUnitId()
        {
            // TODO Auto-generated method stub
            return 1111;
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

        public OrderAction[] getOrderActions()
        {
            // TODO Auto-generated method stub
            return null;
        }

        public Timestamp getReceivedTimestamp()
        {
            // TODO Auto-generated method stub
            return null;
        }

        public Timestamp getExpirationTimestamp()
        {
            // TODO Auto-generated method stub
            return null;
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
            // TODO Auto-generated method stub
            return null;
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
            return null;
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
            // TODO Auto-generated method stub
            return null;
        }}
}
@
