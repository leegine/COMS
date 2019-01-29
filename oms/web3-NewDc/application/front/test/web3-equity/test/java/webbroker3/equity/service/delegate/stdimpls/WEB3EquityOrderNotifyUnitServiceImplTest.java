head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.04.07.00.55.35;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	5444d9d078c63f4;
filename	WEB3EquityOrderNotifyUnitServiceImplTest.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
 Copyright        : (株)大和総研 証券ソリューションシステム第二部
 File Name        : WEB3EquityOrderNotifyUnitServiceImplTest.java
 Author Name      : Daiwa Institute of Research
 Revision History : 2008/07/29 楊夫志(中訊) 新規作成 モデル 132
*/
package webbroker3.equity.service.delegate.stdimpls;

import java.lang.reflect.Field;

import com.fitechlabs.xtrade.plugin.tc.eqtype.EqTypeExecutionConditionType;
import com.fitechlabs.xtrade.plugin.tc.eqtype.data.EqtypeProductParams;
import com.fitechlabs.xtrade.plugin.tc.eqtype.data.EqtypeProductRow;
import com.fitechlabs.xtrade.plugin.tc.eqtype.data.EqtypeTradedProductParams;
import com.fitechlabs.xtrade.plugin.tc.eqtype.data.EqtypeTradedProductRow;
import com.fitechlabs.xtrade.plugin.tc.eqtype.ordersubmitter.io.EqTypeNewCashBasedOrderSpec;
import com.fitechlabs.xtrade.plugin.tc.eqtype.ordersubmitter.io.EqTypeOrderSubmissionResult;
import com.fitechlabs.xtrade.plugin.tc.gentrade.OrderTypeEnum;
import com.fitechlabs.xtrade.plugin.tc.gentrade.ProcessingResult;
import com.fitechlabs.xtrade.plugin.tc.gentrade.ProductTypeEnum;
import com.fitechlabs.xtrade.plugin.tc.gentrade.SubAccount;
import com.fitechlabs.xtrade.plugin.tc.gentrade.SubAccountTypeEnum;
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
import com.fitechlabs.xtrade.plugin.tc.gentrade.data.TradedProductParams;
import com.fitechlabs.xtrade.plugin.tc.gentrade.data.TradedProductRow;
import com.fitechlabs.xtrade.plugin.tc.gentrade.data.TraderParams;
import com.fitechlabs.xtrade.plugin.tc.gentrade.data.TraderRow;

import test.util.TestDBUtility;

import webbroker3.common.WEB3BaseException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.common.define.WEB3MarginAccountOpenDivDef;
import webbroker3.common.define.WEB3PriceConditionSONARDef;
import webbroker3.common.define.WEB3TradeTypeDef;
import webbroker3.equity.WEB3EquityEstimatedDeliveryPrice;
import webbroker3.equity.WEB3EquityNewCashBasedOrderSpec;
import webbroker3.equity.WEB3EquityTradedProduct;
import webbroker3.equity.data.HostEqtypeOrderReceiptParams;
import webbroker3.equity.service.delegate.WEB3EquityOrderInputNotifyAdapter;
import webbroker3.gentrade.WEB3GentradeCommission;
import webbroker3.gentrade.WEB3GentradeSubAccount;
import webbroker3.gentrade.data.TradingTimeParams;
import webbroker3.gentrade.data.TradingTimeRow;
import webbroker3.mock.TestBaseForMock;
import webbroker3.util.WEB3LogUtility;

public class WEB3EquityOrderNotifyUnitServiceImplTest extends TestBaseForMock
{
    WEB3LogUtility log = WEB3LogUtility.getInstance(WEB3EquityOrderNotifyUnitServiceImplTest.class);

    WEB3EquityOrderNotifyUnitServiceImpl l_serviceImpl= null;

    public WEB3EquityOrderNotifyUnitServiceImplTest(String arg0)
    {
        super(arg0);
    }

    protected void setUp() throws Exception
    {
        super.setUp();
        MOCK_MANAGER.setIsMockUsed(true);
        l_serviceImpl = new WEB3EquityOrderNotifyUnitServiceImpl();
    }

    protected void tearDown() throws Exception
    {
        super.tearDown();
    }

    //is信用口座開設(弁済区分 : String)なし
    public void testCalcEstimateDeliveryAmount_C0001()
    {
        final String STR_METHOD_NAME = "testCalcEstimateDeliveryAmount_C0001()";
        log.entering(TEST_START + STR_METHOD_NAME);

        try
        {
            TestDBUtility.deleteAll(InstitutionRow.TYPE);
            InstitutionParams institutionParams = TestDBUtility.getInstitutionRow();
            TestDBUtility.insertWithDel(institutionParams);

            TestDBUtility.deleteAll(MainAccountRow.TYPE);
            MainAccountParams mainAccountParams = TestDBUtility.getMainAccountRow();
            TestDBUtility.insertWithDel(mainAccountParams);
            
            TestDBUtility.deleteAll(TradingTimeRow.TYPE);
            TradingTimeParams tradingTimeParams = TestDBUtility.getTradingTimeRow();
            tradingTimeParams.setInstitutionCode("0D");
            tradingTimeParams.setBranchCode("123");
            tradingTimeParams.setMarketCode("N1");
            tradingTimeParams.setTradingTimeType("01");
            tradingTimeParams.setProductCode("0");
            tradingTimeParams.setBizDateType("1");
            TestDBUtility.insertWithDel(tradingTimeParams);
            
            WEB3EquityNewCashBasedOrderSpec l_newCashBasedOrderSpec = WEB3EquityNewCashBasedOrderSpec.createOrderSpec(
                STR_METHOD_NAME, null, STR_METHOD_NAME, STR_METHOD_NAME, 0, 
                0, null, null, null, false, STR_METHOD_NAME, STR_METHOD_NAME,
                STR_METHOD_NAME, STR_METHOD_NAME, 0, 0, null);
            l_newCashBasedOrderSpec.setInstitutionCode("0D");
            
            HostEqtypeOrderReceiptParams hostEqtypeOrderReceiptParams = new HostEqtypeOrderReceiptParams();
            hostEqtypeOrderReceiptParams.setBranchCode("381");
            hostEqtypeOrderReceiptParams.setAccountCode("222");
            Field field = WEB3EquityOrderNotifyUnitServiceImpl.class.getDeclaredField("hostEqtypeOrderReceiptParams");
            field.setAccessible(true);
            field.set(l_serviceImpl, hostEqtypeOrderReceiptParams);
            
            l_serviceImpl.calcEstimateDeliveryAmount(l_newCashBasedOrderSpec);
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

    //1.3 is信用口座開設()==true（＝信用客）
    //l_orderType = OrderTypeEnum.EQUITY_BUY
    public void testCalcEstimateDeliveryAmount_C0002()
    {
        final String STR_METHOD_NAME = "testCalcEstimateDeliveryAmount_C0002()";
        log.entering(TEST_START + STR_METHOD_NAME);

        try
        {
            TestDBUtility.deleteAll(InstitutionRow.TYPE);
            InstitutionParams institutionParams = TestDBUtility.getInstitutionRow();
            TestDBUtility.insertWithDel(institutionParams);

            TestDBUtility.deleteAll(MainAccountRow.TYPE);
            MainAccountParams mainAccountParams = TestDBUtility.getMainAccountRow();
            mainAccountParams.setMarginGenAccOpenDiv(WEB3MarginAccountOpenDivDef.MARGIN_ACCOUNT_OPEN);
            TestDBUtility.insertWithDel(mainAccountParams);
            
            TestDBUtility.deleteAll(SubAccountRow.TYPE);
            SubAccountParams subAccountParams = TestDBUtility.getSubAccountRow();
            subAccountParams.setAccountId(333812512246L);
            subAccountParams.setSubAccountType(SubAccountTypeEnum.EQUITY_MARGIN_SUB_ACCOUNT);
            TestDBUtility.insertWithDel(subAccountParams);
            
            TestDBUtility.deleteAll(MarketRow.TYPE);
            MarketParams marketParams = TestDBUtility.getMarketRow();
            TestDBUtility.insertWithDel(marketParams);
            
            TestDBUtility.deleteAll(EqtypeTradedProductRow.TYPE);
            EqtypeTradedProductParams eqtypeTradedProductParams = TestDBUtility.getEqtypeTradedProductRow();
            eqtypeTradedProductParams.setProductId(3304148080000L);
            TestDBUtility.insertWithDel(eqtypeTradedProductParams);

            TestDBUtility.deleteAll(EqtypeProductRow.TYPE);
            EqtypeProductParams eqtypeProductParams = TestDBUtility.getEqtypeProductRow();
            TestDBUtility.insertWithDel(eqtypeProductParams);

            TestDBUtility.deleteAll(TradedProductRow.TYPE);
            TradedProductParams tradedProductParams = TestDBUtility.getTradedProductRow();
            tradedProductParams.setProductType(ProductTypeEnum.EQUITY);
            TestDBUtility.insertWithDel(tradedProductParams);

            TestDBUtility.deleteAll(ProductRow.TYPE);
            ProductParams productParams = TestDBUtility.getProductRow();
            productParams.setProductId(3304148080000L);
            productParams.setProductType(ProductTypeEnum.EQUITY);
            TestDBUtility.insertWithDel(productParams);

            TestDBUtility.deleteAll(TraderRow.TYPE);
            TraderParams traderParams = TestDBUtility.getTraderRow();
            TestDBUtility.insertWithDel(traderParams);

            WEB3EquityNewCashBasedOrderSpec l_newCashBasedOrderSpec = WEB3EquityNewCashBasedOrderSpec.createOrderSpec(
                STR_METHOD_NAME, null, "SP", "N8080", 0, 
                0, null, null, null, false, STR_METHOD_NAME, STR_METHOD_NAME,
                STR_METHOD_NAME, STR_METHOD_NAME, 0, 0, null);
            l_newCashBasedOrderSpec.setInstitutionCode("0D");
            
            HostEqtypeOrderReceiptParams hostEqtypeOrderReceiptParams = new HostEqtypeOrderReceiptParams();
            hostEqtypeOrderReceiptParams.setBranchCode("381");
            hostEqtypeOrderReceiptParams.setAccountCode("2512246");
            hostEqtypeOrderReceiptParams.setInstitutionCode("0D");
            hostEqtypeOrderReceiptParams.setSonarMarketCode("G");
            hostEqtypeOrderReceiptParams.setTradeType(WEB3TradeTypeDef.SELL);
            hostEqtypeOrderReceiptParams.setPriceConditionType(WEB3PriceConditionSONARDef.DEFAULT);
            hostEqtypeOrderReceiptParams.setProductCode("N8080");
            hostEqtypeOrderReceiptParams.setOrderRequestNumber("11");
            
            Field field = WEB3EquityOrderNotifyUnitServiceImpl.class.getDeclaredField("hostEqtypeOrderReceiptParams");
            field.setAccessible(true);
            field.set(l_serviceImpl, hostEqtypeOrderReceiptParams);
            
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                    "webbroker3.equity.WEB3EquityOrderManager",
                    "calcPriceForRestraintAmount",
                    new Class[] {
                        WEB3GentradeCommission.class, OrderTypeEnum.class,
                        double.class, double.class,
                        String.class, EqTypeExecutionConditionType.class,
                        String.class,WEB3EquityTradedProduct.class,
                        WEB3GentradeSubAccount.class,String.class},
                        new Double(100));
            WEB3EquityEstimatedDeliveryPrice l_price = new WEB3EquityEstimatedDeliveryPrice();
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                    "webbroker3.equity.WEB3EquityOrderManager",
                    "calcEstimateDeliveryAmount",
                    new Class[] {
                        WEB3GentradeCommission.class, double.class,
                        SubAccount.class, WEB3EquityTradedProduct.class,
                        double.class, boolean.class,
                        double.class, double.class,
                        boolean.class, boolean.class},
                        l_price);
            
            l_serviceImpl.calcEstimateDeliveryAmount(l_newCashBasedOrderSpec);
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

    //is信用口座開設()≠true（＝非信用客）の場合
    public void testCalcEstimateDeliveryAmount_C0003()
    {
        final String STR_METHOD_NAME = "testCalcEstimateDeliveryAmount_C0003()";
        log.entering(TEST_START + STR_METHOD_NAME);

        try
        {
            TestDBUtility.deleteAll(InstitutionRow.TYPE);
            InstitutionParams institutionParams = TestDBUtility.getInstitutionRow();
            TestDBUtility.insertWithDel(institutionParams);

            TestDBUtility.deleteAll(MainAccountRow.TYPE);
            MainAccountParams mainAccountParams = TestDBUtility.getMainAccountRow();
            mainAccountParams.setMarginGenAccOpenDiv(WEB3MarginAccountOpenDivDef.MARGIN_ACCOUNT_NOT_OPEN);
            mainAccountParams.setMarginSysAccOpenDiv(WEB3MarginAccountOpenDivDef.MARGIN_ACCOUNT_NOT_OPEN);
            TestDBUtility.insertWithDel(mainAccountParams);
            
            TestDBUtility.deleteAll(SubAccountRow.TYPE);
            SubAccountParams subAccountParams = TestDBUtility.getSubAccountRow();
            subAccountParams.setAccountId(333812512246L);
            subAccountParams.setSubAccountType(SubAccountTypeEnum.EQUITY_SUB_ACCOUNT);
            TestDBUtility.insertWithDel(subAccountParams);
            
            TestDBUtility.deleteAll(MarketRow.TYPE);
            MarketParams marketParams = TestDBUtility.getMarketRow();
            TestDBUtility.insertWithDel(marketParams);
            
            TestDBUtility.deleteAll(EqtypeTradedProductRow.TYPE);
            EqtypeTradedProductParams eqtypeTradedProductParams = TestDBUtility.getEqtypeTradedProductRow();
            eqtypeTradedProductParams.setProductId(3304148080000L);
            TestDBUtility.insertWithDel(eqtypeTradedProductParams);

            TestDBUtility.deleteAll(EqtypeProductRow.TYPE);
            EqtypeProductParams eqtypeProductParams = TestDBUtility.getEqtypeProductRow();
            TestDBUtility.insertWithDel(eqtypeProductParams);

            TestDBUtility.deleteAll(TradedProductRow.TYPE);
            TradedProductParams tradedProductParams = TestDBUtility.getTradedProductRow();
            tradedProductParams.setProductType(ProductTypeEnum.EQUITY);
            TestDBUtility.insertWithDel(tradedProductParams);

            TestDBUtility.deleteAll(ProductRow.TYPE);
            ProductParams productParams = TestDBUtility.getProductRow();
            productParams.setProductId(3304148080000L);
            productParams.setProductType(ProductTypeEnum.EQUITY);
            TestDBUtility.insertWithDel(productParams);
            
            TestDBUtility.deleteAll(TraderRow.TYPE);
            TraderParams traderParams = TestDBUtility.getTraderRow();
            TestDBUtility.insertWithDel(traderParams);

            WEB3EquityNewCashBasedOrderSpec l_newCashBasedOrderSpec = WEB3EquityNewCashBasedOrderSpec.createOrderSpec(
                STR_METHOD_NAME, null, "SP", "N8080", 0, 
                0, null, null, null, false, STR_METHOD_NAME, STR_METHOD_NAME,
                STR_METHOD_NAME, STR_METHOD_NAME, 0, 0, null);
            l_newCashBasedOrderSpec.setInstitutionCode("0D");
            
            HostEqtypeOrderReceiptParams hostEqtypeOrderReceiptParams = new HostEqtypeOrderReceiptParams();
            hostEqtypeOrderReceiptParams.setBranchCode("381");
            hostEqtypeOrderReceiptParams.setAccountCode("2512246");
            hostEqtypeOrderReceiptParams.setInstitutionCode("0D");
            hostEqtypeOrderReceiptParams.setSonarMarketCode("G");
            hostEqtypeOrderReceiptParams.setTradeType(WEB3TradeTypeDef.SELL);
            hostEqtypeOrderReceiptParams.setPriceConditionType(WEB3PriceConditionSONARDef.DEFAULT);
            hostEqtypeOrderReceiptParams.setProductCode("N8080");
            hostEqtypeOrderReceiptParams.setOrderRequestNumber("11");
            
            Field field = WEB3EquityOrderNotifyUnitServiceImpl.class.getDeclaredField("hostEqtypeOrderReceiptParams");
            field.setAccessible(true);
            field.set(l_serviceImpl, hostEqtypeOrderReceiptParams);
            
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                    "webbroker3.equity.WEB3EquityOrderManager",
                    "calcPriceForRestraintAmount",
                    new Class[] {
                        WEB3GentradeCommission.class, OrderTypeEnum.class,
                        double.class, double.class,
                        String.class, EqTypeExecutionConditionType.class,
                        String.class,WEB3EquityTradedProduct.class,
                        WEB3GentradeSubAccount.class,String.class},
                        new Double(100));
            WEB3EquityEstimatedDeliveryPrice l_price = new WEB3EquityEstimatedDeliveryPrice();
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                    "webbroker3.equity.WEB3EquityOrderManager",
                    "calcEstimateDeliveryAmount",
                    new Class[] {
                        WEB3GentradeCommission.class, double.class,
                        SubAccount.class, WEB3EquityTradedProduct.class,
                        double.class, boolean.class,
                        double.class, double.class,
                        boolean.class, boolean.class},
                        l_price);
            
            l_serviceImpl.calcEstimateDeliveryAmount(l_newCashBasedOrderSpec);
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

    //1.3 is信用口座開設()==true（＝信用客）
    //信用取引口座取得なし
    public void testCalcEstimateDeliveryAmount_C0004()
    {
        final String STR_METHOD_NAME = "testCalcEstimateDeliveryAmount_C0004()";
        log.entering(TEST_START + STR_METHOD_NAME);

        try
        {
            TestDBUtility.deleteAll(InstitutionRow.TYPE);
            InstitutionParams institutionParams = TestDBUtility.getInstitutionRow();
            TestDBUtility.insertWithDel(institutionParams);

            TestDBUtility.deleteAll(MainAccountRow.TYPE);
            MainAccountParams mainAccountParams = TestDBUtility.getMainAccountRow();
            mainAccountParams.setMarginGenAccOpenDiv(WEB3MarginAccountOpenDivDef.MARGIN_ACCOUNT_OPEN);
            TestDBUtility.insertWithDel(mainAccountParams);
            
            TestDBUtility.deleteAll(SubAccountRow.TYPE);
            SubAccountParams subAccountParams = TestDBUtility.getSubAccountRow();
            subAccountParams.setAccountId(333812512246L);
            subAccountParams.setSubAccountType(SubAccountTypeEnum.EQUITY_SUB_ACCOUNT);
            TestDBUtility.insertWithDel(subAccountParams);
            
            TestDBUtility.deleteAll(MarketRow.TYPE);
            MarketParams marketParams = TestDBUtility.getMarketRow();
            TestDBUtility.insertWithDel(marketParams);
            
            TestDBUtility.deleteAll(EqtypeTradedProductRow.TYPE);
            EqtypeTradedProductParams eqtypeTradedProductParams = TestDBUtility.getEqtypeTradedProductRow();
            eqtypeTradedProductParams.setProductId(3304148080000L);
            TestDBUtility.insertWithDel(eqtypeTradedProductParams);

            TestDBUtility.deleteAll(EqtypeProductRow.TYPE);
            EqtypeProductParams eqtypeProductParams = TestDBUtility.getEqtypeProductRow();
            TestDBUtility.insertWithDel(eqtypeProductParams);

            TestDBUtility.deleteAll(TradedProductRow.TYPE);
            TradedProductParams tradedProductParams = TestDBUtility.getTradedProductRow();
            tradedProductParams.setProductType(ProductTypeEnum.EQUITY);
            TestDBUtility.insertWithDel(tradedProductParams);

            TestDBUtility.deleteAll(ProductRow.TYPE);
            ProductParams productParams = TestDBUtility.getProductRow();
            productParams.setProductId(3304148080000L);
            productParams.setProductType(ProductTypeEnum.EQUITY);
            TestDBUtility.insertWithDel(productParams);
            
            TestDBUtility.deleteAll(TraderRow.TYPE);
            TraderParams traderParams = TestDBUtility.getTraderRow();
            TestDBUtility.insertWithDel(traderParams);

            WEB3EquityNewCashBasedOrderSpec l_newCashBasedOrderSpec = WEB3EquityNewCashBasedOrderSpec.createOrderSpec(
                STR_METHOD_NAME, null, "SP", "N8080", 0, 
                0, null, null, null, false, STR_METHOD_NAME, STR_METHOD_NAME,
                STR_METHOD_NAME, STR_METHOD_NAME, 0, 0, null);
            l_newCashBasedOrderSpec.setInstitutionCode("0D");
            
            HostEqtypeOrderReceiptParams hostEqtypeOrderReceiptParams = new HostEqtypeOrderReceiptParams();
            hostEqtypeOrderReceiptParams.setBranchCode("381");
            hostEqtypeOrderReceiptParams.setAccountCode("2512246");
            hostEqtypeOrderReceiptParams.setInstitutionCode("0D");
            hostEqtypeOrderReceiptParams.setSonarMarketCode("G");
            hostEqtypeOrderReceiptParams.setTradeType(WEB3TradeTypeDef.SELL);
            hostEqtypeOrderReceiptParams.setPriceConditionType(WEB3PriceConditionSONARDef.DEFAULT);
            hostEqtypeOrderReceiptParams.setProductCode("N8080");
            hostEqtypeOrderReceiptParams.setOrderRequestNumber("11");
            
            Field field = WEB3EquityOrderNotifyUnitServiceImpl.class.getDeclaredField("hostEqtypeOrderReceiptParams");
            field.setAccessible(true);
            field.set(l_serviceImpl, hostEqtypeOrderReceiptParams);
            
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                    "webbroker3.equity.WEB3EquityOrderManager",
                    "calcPriceForRestraintAmount",
                    new Class[] {
                        WEB3GentradeCommission.class, OrderTypeEnum.class,
                        double.class, double.class,
                        String.class, EqTypeExecutionConditionType.class,
                        String.class,WEB3EquityTradedProduct.class,
                        WEB3GentradeSubAccount.class,String.class},
                        new Double(100));
            WEB3EquityEstimatedDeliveryPrice l_price = new WEB3EquityEstimatedDeliveryPrice();
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                    "webbroker3.equity.WEB3EquityOrderManager",
                    "calcEstimateDeliveryAmount",
                    new Class[] {
                        WEB3GentradeCommission.class, double.class,
                        SubAccount.class, WEB3EquityTradedProduct.class,
                        double.class, boolean.class,
                        double.class, double.class,
                        boolean.class, boolean.class},
                        l_price);
            
            l_serviceImpl.calcEstimateDeliveryAmount(l_newCashBasedOrderSpec);
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

    //1.5 get取引銘柄( )なし
    public void testCalcEstimateDeliveryAmount_C0005()
    {
        final String STR_METHOD_NAME = "testCalcEstimateDeliveryAmount_C0005()";
        log.entering(TEST_START + STR_METHOD_NAME);

        try
        {
            TestDBUtility.deleteAll(InstitutionRow.TYPE);
            InstitutionParams institutionParams = TestDBUtility.getInstitutionRow();
            TestDBUtility.insertWithDel(institutionParams);

            TestDBUtility.deleteAll(MainAccountRow.TYPE);
            MainAccountParams mainAccountParams = TestDBUtility.getMainAccountRow();
            mainAccountParams.setMarginGenAccOpenDiv(WEB3MarginAccountOpenDivDef.MARGIN_ACCOUNT_OPEN);
            TestDBUtility.insertWithDel(mainAccountParams);
            
            TestDBUtility.deleteAll(SubAccountRow.TYPE);
            SubAccountParams subAccountParams = TestDBUtility.getSubAccountRow();
            subAccountParams.setAccountId(333812512246L);
            subAccountParams.setSubAccountType(SubAccountTypeEnum.EQUITY_MARGIN_SUB_ACCOUNT);
            TestDBUtility.insertWithDel(subAccountParams);
            
            TestDBUtility.deleteAll(MarketRow.TYPE);
            MarketParams marketParams = TestDBUtility.getMarketRow();
            TestDBUtility.insertWithDel(marketParams);
            
            TestDBUtility.deleteAll(EqtypeTradedProductRow.TYPE);
            EqtypeTradedProductParams eqtypeTradedProductParams = TestDBUtility.getEqtypeTradedProductRow();
            eqtypeTradedProductParams.setProductId(3304148080000L);
            TestDBUtility.insertWithDel(eqtypeTradedProductParams);

            TestDBUtility.deleteAll(EqtypeProductRow.TYPE);
            EqtypeProductParams eqtypeProductParams = TestDBUtility.getEqtypeProductRow();
            TestDBUtility.insertWithDel(eqtypeProductParams);

            TestDBUtility.deleteAll(TradedProductRow.TYPE);
            TradedProductParams tradedProductParams = TestDBUtility.getTradedProductRow();
            tradedProductParams.setProductType(ProductTypeEnum.EQUITY);
            TestDBUtility.insertWithDel(tradedProductParams);

            TestDBUtility.deleteAll(ProductRow.TYPE);
            ProductParams productParams = TestDBUtility.getProductRow();
            productParams.setProductId(3304148080000L);
            productParams.setProductType(ProductTypeEnum.EQUITY);
            TestDBUtility.insertWithDel(productParams);
            
            TestDBUtility.deleteAll(TraderRow.TYPE);
            TraderParams traderParams = TestDBUtility.getTraderRow();
            TestDBUtility.insertWithDel(traderParams);

            WEB3EquityNewCashBasedOrderSpec l_newCashBasedOrderSpec = WEB3EquityNewCashBasedOrderSpec.createOrderSpec(
                STR_METHOD_NAME, null, "11", "N8080", 0, 
                0, null, null, null, false, STR_METHOD_NAME, STR_METHOD_NAME,
                STR_METHOD_NAME, STR_METHOD_NAME, 0, 0, null);
            l_newCashBasedOrderSpec.setInstitutionCode("0D");
            
            HostEqtypeOrderReceiptParams hostEqtypeOrderReceiptParams = new HostEqtypeOrderReceiptParams();
            hostEqtypeOrderReceiptParams.setBranchCode("381");
            hostEqtypeOrderReceiptParams.setAccountCode("2512246");
            hostEqtypeOrderReceiptParams.setInstitutionCode("0D");
            hostEqtypeOrderReceiptParams.setSonarMarketCode("G");
            hostEqtypeOrderReceiptParams.setTradeType(WEB3TradeTypeDef.SELL);
            hostEqtypeOrderReceiptParams.setPriceConditionType(WEB3PriceConditionSONARDef.DEFAULT);
            hostEqtypeOrderReceiptParams.setProductCode("N8080");
            hostEqtypeOrderReceiptParams.setOrderRequestNumber("11");
            
            Field field = WEB3EquityOrderNotifyUnitServiceImpl.class.getDeclaredField("hostEqtypeOrderReceiptParams");
            field.setAccessible(true);
            field.set(l_serviceImpl, hostEqtypeOrderReceiptParams);
            
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                    "webbroker3.equity.WEB3EquityOrderManager",
                    "calcPriceForRestraintAmount",
                    new Class[] {
                        WEB3GentradeCommission.class, OrderTypeEnum.class,
                        double.class, double.class,
                        String.class, EqTypeExecutionConditionType.class,
                        String.class,WEB3EquityTradedProduct.class,
                        WEB3GentradeSubAccount.class,String.class},
                        new Double(100));
            WEB3EquityEstimatedDeliveryPrice l_price = new WEB3EquityEstimatedDeliveryPrice();
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                    "webbroker3.equity.WEB3EquityOrderManager",
                    "calcEstimateDeliveryAmount",
                    new Class[] {
                        WEB3GentradeCommission.class, double.class,
                        SubAccount.class, WEB3EquityTradedProduct.class,
                        double.class, boolean.class,
                        double.class, double.class,
                        boolean.class, boolean.class},
                        l_price);
            
            l_serviceImpl.calcEstimateDeliveryAmount(l_newCashBasedOrderSpec);
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

    //l_orderType = OrderTypeEnum.EQUITY_SELL
    public void testCalcEstimateDeliveryAmount_C0006()
    {
        final String STR_METHOD_NAME = "testCalcEstimateDeliveryAmount_C0006()";
        log.entering(TEST_START + STR_METHOD_NAME);

        try
        {
            TestDBUtility.deleteAll(InstitutionRow.TYPE);
            InstitutionParams institutionParams = TestDBUtility.getInstitutionRow();
            TestDBUtility.insertWithDel(institutionParams);

            TestDBUtility.deleteAll(MainAccountRow.TYPE);
            MainAccountParams mainAccountParams = TestDBUtility.getMainAccountRow();
            mainAccountParams.setMarginGenAccOpenDiv(WEB3MarginAccountOpenDivDef.MARGIN_ACCOUNT_OPEN);
            TestDBUtility.insertWithDel(mainAccountParams);
            
            TestDBUtility.deleteAll(SubAccountRow.TYPE);
            SubAccountParams subAccountParams = TestDBUtility.getSubAccountRow();
            subAccountParams.setAccountId(333812512246L);
            subAccountParams.setSubAccountType(SubAccountTypeEnum.EQUITY_MARGIN_SUB_ACCOUNT);
            TestDBUtility.insertWithDel(subAccountParams);
            
            TestDBUtility.deleteAll(MarketRow.TYPE);
            MarketParams marketParams = TestDBUtility.getMarketRow();
            TestDBUtility.insertWithDel(marketParams);
            
            TestDBUtility.deleteAll(EqtypeTradedProductRow.TYPE);
            EqtypeTradedProductParams eqtypeTradedProductParams = TestDBUtility.getEqtypeTradedProductRow();
            eqtypeTradedProductParams.setProductId(3304148080000L);
            TestDBUtility.insertWithDel(eqtypeTradedProductParams);

            TestDBUtility.deleteAll(EqtypeProductRow.TYPE);
            EqtypeProductParams eqtypeProductParams = TestDBUtility.getEqtypeProductRow();
            TestDBUtility.insertWithDel(eqtypeProductParams);

            TestDBUtility.deleteAll(TradedProductRow.TYPE);
            TradedProductParams tradedProductParams = TestDBUtility.getTradedProductRow();
            tradedProductParams.setProductType(ProductTypeEnum.EQUITY);
            TestDBUtility.insertWithDel(tradedProductParams);

            TestDBUtility.deleteAll(ProductRow.TYPE);
            ProductParams productParams = TestDBUtility.getProductRow();
            productParams.setProductId(3304148080000L);
            productParams.setProductType(ProductTypeEnum.EQUITY);
            TestDBUtility.insertWithDel(productParams);
            
            TestDBUtility.deleteAll(TraderRow.TYPE);
            TraderParams traderParams = TestDBUtility.getTraderRow();
            TestDBUtility.insertWithDel(traderParams);

            WEB3EquityNewCashBasedOrderSpec l_newCashBasedOrderSpec = WEB3EquityNewCashBasedOrderSpec.createOrderSpec(
                STR_METHOD_NAME, null, "SP", "N8080", 0, 
                0, null, null, null, true, STR_METHOD_NAME, STR_METHOD_NAME,
                STR_METHOD_NAME, STR_METHOD_NAME, 0, 0, null);
            l_newCashBasedOrderSpec.setInstitutionCode("0D");
            
            HostEqtypeOrderReceiptParams hostEqtypeOrderReceiptParams = new HostEqtypeOrderReceiptParams();
            hostEqtypeOrderReceiptParams.setBranchCode("381");
            hostEqtypeOrderReceiptParams.setAccountCode("2512246");
            hostEqtypeOrderReceiptParams.setInstitutionCode("0D");
            hostEqtypeOrderReceiptParams.setSonarMarketCode("G");
            hostEqtypeOrderReceiptParams.setTradeType(WEB3TradeTypeDef.SELL);
            hostEqtypeOrderReceiptParams.setPriceConditionType(WEB3PriceConditionSONARDef.DEFAULT);
            hostEqtypeOrderReceiptParams.setProductCode("N8080");
            hostEqtypeOrderReceiptParams.setOrderRequestNumber("11");
            
            Field field = WEB3EquityOrderNotifyUnitServiceImpl.class.getDeclaredField("hostEqtypeOrderReceiptParams");
            field.setAccessible(true);
            field.set(l_serviceImpl, hostEqtypeOrderReceiptParams);
            
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                    "webbroker3.equity.WEB3EquityOrderManager",
                    "calcPriceForRestraintAmount",
                    new Class[] {
                        WEB3GentradeCommission.class, OrderTypeEnum.class,
                        double.class, double.class,
                        String.class, EqTypeExecutionConditionType.class,
                        String.class,WEB3EquityTradedProduct.class,
                        WEB3GentradeSubAccount.class,String.class},
                        new Double(100));
            WEB3EquityEstimatedDeliveryPrice l_price = new WEB3EquityEstimatedDeliveryPrice();
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                    "webbroker3.equity.WEB3EquityOrderManager",
                    "calcEstimateDeliveryAmount",
                    new Class[] {
                        WEB3GentradeCommission.class, double.class,
                        SubAccount.class, WEB3EquityTradedProduct.class,
                        double.class, boolean.class,
                        double.class, double.class,
                        boolean.class, boolean.class},
                        l_price);
            
            l_serviceImpl.calcEstimateDeliveryAmount(l_newCashBasedOrderSpec);
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

    //is信用口座開設()==true（＝信用客）
    //1.2.2.16 余力再計算( )
    public void testNotifyPartProcess_C0001()
    {
        final String STR_METHOD_NAME = "testNotifyPartProcess_C0001()";
        log.entering(TEST_START + STR_METHOD_NAME);

        try
        {
            TestDBUtility.deleteAll(InstitutionRow.TYPE);
            InstitutionParams institutionParams = TestDBUtility.getInstitutionRow();
            TestDBUtility.insertWithDel(institutionParams);

            TestDBUtility.deleteAll(MainAccountRow.TYPE);
            MainAccountParams mainAccountParams = TestDBUtility.getMainAccountRow();
            mainAccountParams.setMarginGenAccOpenDiv(WEB3MarginAccountOpenDivDef.MARGIN_ACCOUNT_OPEN);
            TestDBUtility.insertWithDel(mainAccountParams);
 
            TestDBUtility.deleteAll(SubAccountRow.TYPE);
            SubAccountParams subAccountParams = TestDBUtility.getSubAccountRow();
            subAccountParams.setAccountId(333812512246L);
            subAccountParams.setSubAccountType(SubAccountTypeEnum.EQUITY_MARGIN_SUB_ACCOUNT);
            TestDBUtility.insertWithDel(subAccountParams);

            TestDBUtility.deleteAll(MarketRow.TYPE);
            MarketParams marketParams = TestDBUtility.getMarketRow();
            TestDBUtility.insertWithDel(marketParams);
            
            TestDBUtility.deleteAll(EqtypeTradedProductRow.TYPE);
            EqtypeTradedProductParams eqtypeTradedProductParams = TestDBUtility.getEqtypeTradedProductRow();
            eqtypeTradedProductParams.setProductId(3304148080000L);
            TestDBUtility.insertWithDel(eqtypeTradedProductParams);

            TestDBUtility.deleteAll(EqtypeProductRow.TYPE);
            EqtypeProductParams eqtypeProductParams = TestDBUtility.getEqtypeProductRow();
            TestDBUtility.insertWithDel(eqtypeProductParams);

            TestDBUtility.deleteAll(TradedProductRow.TYPE);
            TradedProductParams tradedProductParams = TestDBUtility.getTradedProductRow();
            tradedProductParams.setProductType(ProductTypeEnum.EQUITY);
            TestDBUtility.insertWithDel(tradedProductParams);

            TestDBUtility.deleteAll(ProductRow.TYPE);
            ProductParams productParams = TestDBUtility.getProductRow();
            productParams.setProductId(3304148080000L);
            productParams.setProductType(ProductTypeEnum.EQUITY);
            TestDBUtility.insertWithDel(productParams);
            
            TestDBUtility.deleteAll(TraderRow.TYPE);
            TraderParams traderParams = TestDBUtility.getTraderRow();
            TestDBUtility.insertWithDel(traderParams);
            
            HostEqtypeOrderReceiptParams hostEqtypeOrderReceiptParams = new HostEqtypeOrderReceiptParams();
            hostEqtypeOrderReceiptParams.setAccountCode("2512246");
            hostEqtypeOrderReceiptParams.setBranchCode("381");
            hostEqtypeOrderReceiptParams.setInstitutionCode("0D");
            hostEqtypeOrderReceiptParams.setSonarMarketCode("G");
            hostEqtypeOrderReceiptParams.setTradeType(WEB3TradeTypeDef.SELL);
            hostEqtypeOrderReceiptParams.setPriceConditionType(WEB3PriceConditionSONARDef.DEFAULT);
            hostEqtypeOrderReceiptParams.setProductCode("N8080");
            hostEqtypeOrderReceiptParams.setOrderRequestNumber("11");

            WEB3EquityOrderInputNotifyAdapter l_orderInputNotifyAdapter = WEB3EquityOrderInputNotifyAdapter.create(hostEqtypeOrderReceiptParams);

            ProcessingResult processingResult = ProcessingResult.newSuccessResultInstance();
            EqTypeOrderSubmissionResult orderSubmissionResult = new EqTypeOrderSubmissionResult(processingResult);
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                    "webbroker3.equity.WEB3EquityPTSOrderManager",
                    "submitNewCashBasedOrder",
                    new Class[] {SubAccount.class, EqTypeNewCashBasedOrderSpec.class, long.class, String.class, boolean.class},
                    orderSubmissionResult);

            WEB3EquityOrderNotifyUnitServiceImplForTest forTest = new WEB3EquityOrderNotifyUnitServiceImplForTest();
            forTest.notifyPartProcess(l_orderInputNotifyAdapter);
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

    //is信用口座開設()≠true（＝非信用客）の場合
    //1.2.2.16 余力再計算( )
    public void testNotifyPartProcess_C0002()
    {
        final String STR_METHOD_NAME = "testNotifyPartProcess_C0002()";
        log.entering(TEST_START + STR_METHOD_NAME);

        try
        {
            TestDBUtility.deleteAll(InstitutionRow.TYPE);
            InstitutionParams institutionParams = TestDBUtility.getInstitutionRow();
            TestDBUtility.insertWithDel(institutionParams);

            TestDBUtility.deleteAll(MainAccountRow.TYPE);
            MainAccountParams mainAccountParams = TestDBUtility.getMainAccountRow();
            mainAccountParams.setMarginGenAccOpenDiv(WEB3MarginAccountOpenDivDef.MARGIN_ACCOUNT_NOT_OPEN);
            TestDBUtility.insertWithDel(mainAccountParams);
 
            TestDBUtility.deleteAll(SubAccountRow.TYPE);
            SubAccountParams subAccountParams = TestDBUtility.getSubAccountRow();
            subAccountParams.setAccountId(333812512246L);
            subAccountParams.setSubAccountType(SubAccountTypeEnum.EQUITY_SUB_ACCOUNT);
            TestDBUtility.insertWithDel(subAccountParams);

            TestDBUtility.deleteAll(MarketRow.TYPE);
            MarketParams marketParams = TestDBUtility.getMarketRow();
            TestDBUtility.insertWithDel(marketParams);
            
            TestDBUtility.deleteAll(EqtypeTradedProductRow.TYPE);
            EqtypeTradedProductParams eqtypeTradedProductParams = TestDBUtility.getEqtypeTradedProductRow();
            eqtypeTradedProductParams.setProductId(3304148080000L);
            TestDBUtility.insertWithDel(eqtypeTradedProductParams);

            TestDBUtility.deleteAll(EqtypeProductRow.TYPE);
            EqtypeProductParams eqtypeProductParams = TestDBUtility.getEqtypeProductRow();
            TestDBUtility.insertWithDel(eqtypeProductParams);

            TestDBUtility.deleteAll(TradedProductRow.TYPE);
            TradedProductParams tradedProductParams = TestDBUtility.getTradedProductRow();
            tradedProductParams.setProductType(ProductTypeEnum.EQUITY);
            TestDBUtility.insertWithDel(tradedProductParams);

            TestDBUtility.deleteAll(ProductRow.TYPE);
            ProductParams productParams = TestDBUtility.getProductRow();
            productParams.setProductId(3304148080000L);
            productParams.setProductType(ProductTypeEnum.EQUITY);
            TestDBUtility.insertWithDel(productParams);
            
            TestDBUtility.deleteAll(TraderRow.TYPE);
            TraderParams traderParams = TestDBUtility.getTraderRow();
            TestDBUtility.insertWithDel(traderParams);
            
            HostEqtypeOrderReceiptParams hostEqtypeOrderReceiptParams = new HostEqtypeOrderReceiptParams();
            hostEqtypeOrderReceiptParams.setAccountCode("2512246");
            hostEqtypeOrderReceiptParams.setBranchCode("381");
            hostEqtypeOrderReceiptParams.setInstitutionCode("0D");
            hostEqtypeOrderReceiptParams.setSonarMarketCode("G");
            hostEqtypeOrderReceiptParams.setTradeType(WEB3TradeTypeDef.SELL);
            hostEqtypeOrderReceiptParams.setPriceConditionType(WEB3PriceConditionSONARDef.DEFAULT);
            hostEqtypeOrderReceiptParams.setProductCode("N8080");
            hostEqtypeOrderReceiptParams.setOrderRequestNumber("11");

            WEB3EquityOrderInputNotifyAdapter l_orderInputNotifyAdapter = WEB3EquityOrderInputNotifyAdapter.create(hostEqtypeOrderReceiptParams);

            ProcessingResult processingResult = ProcessingResult.newSuccessResultInstance();
            EqTypeOrderSubmissionResult orderSubmissionResult = new EqTypeOrderSubmissionResult(processingResult);
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                    "webbroker3.equity.WEB3EquityPTSOrderManager",
                    "submitNewCashBasedOrder",
                    new Class[] {SubAccount.class, EqTypeNewCashBasedOrderSpec.class, long.class, String.class, boolean.class},
                    orderSubmissionResult);

            WEB3EquityOrderNotifyUnitServiceImplForTest forTest = new WEB3EquityOrderNotifyUnitServiceImplForTest();
            forTest.notifyPartProcess(l_orderInputNotifyAdapter);
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

    //l_orderSubmissionResult.getProcessingResult().isFailedResult()
    public void testNotifyPartProcess_C0003()
    {
        final String STR_METHOD_NAME = "testNotifyPartProcess_C0003()";
        log.entering(TEST_START + STR_METHOD_NAME);

        try
        {
            TestDBUtility.deleteAll(InstitutionRow.TYPE);
            InstitutionParams institutionParams = TestDBUtility.getInstitutionRow();
            TestDBUtility.insertWithDel(institutionParams);

            TestDBUtility.deleteAll(MainAccountRow.TYPE);
            MainAccountParams mainAccountParams = TestDBUtility.getMainAccountRow();
            mainAccountParams.setMarginGenAccOpenDiv(WEB3MarginAccountOpenDivDef.MARGIN_ACCOUNT_NOT_OPEN);
            mainAccountParams.setMarginSysAccOpenDiv(WEB3MarginAccountOpenDivDef.MARGIN_ACCOUNT_NOT_OPEN);
            TestDBUtility.insertWithDel(mainAccountParams);
 
            TestDBUtility.deleteAll(SubAccountRow.TYPE);
            SubAccountParams subAccountParams = TestDBUtility.getSubAccountRow();
            subAccountParams.setAccountId(333812512246L);
            subAccountParams.setSubAccountType(SubAccountTypeEnum.EQUITY_SUB_ACCOUNT);
            TestDBUtility.insertWithDel(subAccountParams);

            TestDBUtility.deleteAll(MarketRow.TYPE);
            MarketParams marketParams = TestDBUtility.getMarketRow();
            TestDBUtility.insertWithDel(marketParams);
            
            TestDBUtility.deleteAll(EqtypeTradedProductRow.TYPE);
            EqtypeTradedProductParams eqtypeTradedProductParams = TestDBUtility.getEqtypeTradedProductRow();
            eqtypeTradedProductParams.setProductId(3304148080000L);
            TestDBUtility.insertWithDel(eqtypeTradedProductParams);

            TestDBUtility.deleteAll(EqtypeProductRow.TYPE);
            EqtypeProductParams eqtypeProductParams = TestDBUtility.getEqtypeProductRow();
            TestDBUtility.insertWithDel(eqtypeProductParams);

            TestDBUtility.deleteAll(TradedProductRow.TYPE);
            TradedProductParams tradedProductParams = TestDBUtility.getTradedProductRow();
            tradedProductParams.setProductType(ProductTypeEnum.EQUITY);
            TestDBUtility.insertWithDel(tradedProductParams);

            TestDBUtility.deleteAll(ProductRow.TYPE);
            ProductParams productParams = TestDBUtility.getProductRow();
            productParams.setProductId(3304148080000L);
            productParams.setProductType(ProductTypeEnum.EQUITY);
            TestDBUtility.insertWithDel(productParams);
            
            TestDBUtility.deleteAll(TraderRow.TYPE);
            TraderParams traderParams = TestDBUtility.getTraderRow();
            TestDBUtility.insertWithDel(traderParams);
            
            HostEqtypeOrderReceiptParams hostEqtypeOrderReceiptParams = new HostEqtypeOrderReceiptParams();
            hostEqtypeOrderReceiptParams.setAccountCode("2512246");
            hostEqtypeOrderReceiptParams.setBranchCode("381");
            hostEqtypeOrderReceiptParams.setInstitutionCode("0D");
            hostEqtypeOrderReceiptParams.setSonarMarketCode("G");
            hostEqtypeOrderReceiptParams.setTradeType(WEB3TradeTypeDef.SELL);
            hostEqtypeOrderReceiptParams.setPriceConditionType(WEB3PriceConditionSONARDef.DEFAULT);
            hostEqtypeOrderReceiptParams.setProductCode("N8080");
            hostEqtypeOrderReceiptParams.setOrderRequestNumber("11");

            WEB3EquityOrderInputNotifyAdapter l_orderInputNotifyAdapter = WEB3EquityOrderInputNotifyAdapter.create(hostEqtypeOrderReceiptParams);

            ProcessingResult processingResult = ProcessingResult.newFailedResultInstance(WEB3ErrorCatalog.BUSINESS_ERROR_00003);
            EqTypeOrderSubmissionResult orderSubmissionResult = new EqTypeOrderSubmissionResult(processingResult);
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                    "webbroker3.equity.WEB3EquityPTSOrderManager",
                    "submitNewCashBasedOrder",
                    new Class[] {SubAccount.class, EqTypeNewCashBasedOrderSpec.class, long.class, String.class, boolean.class},
                    orderSubmissionResult);

            WEB3EquityOrderNotifyUnitServiceImplForTest forTest = new WEB3EquityOrderNotifyUnitServiceImplForTest();
            forTest.notifyPartProcess(l_orderInputNotifyAdapter);
            fail();
        }
        catch(WEB3BaseException l_ex)
        {
            assertEquals(WEB3ErrorCatalog.BUSINESS_ERROR_00003, l_ex.getErrorInfo());
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

    private class WEB3EquityOrderNotifyUnitServiceImplForTest extends WEB3EquityOrderNotifyUnitServiceImpl
    {
        public void calcEstimateDeliveryAmount(
                WEB3EquityNewCashBasedOrderSpec l_newCashBasedOrderSpec)
                throws WEB3BaseException
        {
            return;
        }
    }
}
@
