head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.04.07.00.48.56;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	5444d9d078c63f4;
filename	WEB3TPTradingPowerServiceImplTest_validateTradingPowerEquitySell.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : WEB3TPTradingPowerServiceImplTest_validateTradingPowerEquitySell.java
Author Name      : Daiwa Institute of Research
Revision History : 2007/09/30 金傑（中訊）新規作成
*/
package webbroker3.tradingpower;

import java.sql.Timestamp;
import java.util.Calendar;
import java.util.Date;

import com.fitechlabs.xtrade.kernel.util.ThreadLocalSystemAttributesRegistry;
import com.fitechlabs.xtrade.plugin.tc.eqtype.data.EqtypeOrderUnitParams;
import com.fitechlabs.xtrade.plugin.tc.eqtype.data.EqtypeOrderUnitRow;
import com.fitechlabs.xtrade.plugin.tc.eqtype.data.EqtypeProductParams;
import com.fitechlabs.xtrade.plugin.tc.eqtype.data.EqtypeProductRow;
import com.fitechlabs.xtrade.plugin.tc.eqtype.data.EqtypeTradedProductParams;
import com.fitechlabs.xtrade.plugin.tc.eqtype.data.EqtypeTradedProductRow;
import com.fitechlabs.xtrade.plugin.tc.eqtype.data.EqtypeTradedProductUpdqParams;
import com.fitechlabs.xtrade.plugin.tc.gentrade.BranchTypeEnum;
import com.fitechlabs.xtrade.plugin.tc.gentrade.Institution;
import com.fitechlabs.xtrade.plugin.tc.gentrade.MainAccount;
import com.fitechlabs.xtrade.plugin.tc.gentrade.OrderTypeEnum;
import com.fitechlabs.xtrade.plugin.tc.gentrade.ProductTypeEnum;
import com.fitechlabs.xtrade.plugin.tc.gentrade.SubAccountTypeEnum;
import com.fitechlabs.xtrade.plugin.tc.gentrade.data.BranchParams;
import com.fitechlabs.xtrade.plugin.tc.gentrade.data.BranchRow;
import com.fitechlabs.xtrade.plugin.tc.gentrade.data.InstitutionParams;
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

import test.util.TestDBUtility;

import webbroker3.common.WEB3SystemLayerException;
import webbroker3.common.define.WEB3MarginAccountOpenDivDef;
import webbroker3.equity.WEB3EquityTradedProduct;
import webbroker3.gentrade.WEB3GentradeInstitution;
import webbroker3.gentrade.WEB3GentradeMainAccount;
import webbroker3.gentrade.WEB3GentradeSubAccount;
import webbroker3.gentrade.WEB3GentradeTradingClendarContext;
import webbroker3.gentrade.WEB3GentradeTradingTimeManagement;
import webbroker3.gentrade.WEB3GentradeTradingTimeManagementForMock;
import webbroker3.gentrade.data.BranchPreferencesParams;
import webbroker3.gentrade.data.BranchPreferencesRow;
import webbroker3.gentrade.data.TradingTimeParams;
import webbroker3.gentrade.data.TradingTimeRow;
import webbroker3.gentrade.data.TradingpowerCalcConditionParams;
import webbroker3.gentrade.data.TradingpowerCalcConditionRow;
import webbroker3.mock.TestBaseForMock;
import webbroker3.tradingpower.data.TpCalcResultEquityParams;
import webbroker3.tradingpower.data.TpCalcResultEquityRow;
import webbroker3.tradingpower.data.TpCalcResultMarginDetailParams;
import webbroker3.tradingpower.data.TpCalcResultMarginParams;
import webbroker3.tradingpower.data.TpCashBalanceParams;
import webbroker3.tradingpower.updtpower.WEB3TPNewOrderSpec;
import webbroker3.util.WEB3DateUtility;
import webbroker3.util.WEB3LogUtility;

public class WEB3TPTradingPowerServiceImplTest_validateTradingPowerEquitySell extends TestBaseForMock
{

    private WEB3TPTradingPowerServiceImpl l_serviceImpl = null;
    
    private boolean isAssetEvaluation;

    private static WEB3LogUtility log =
        WEB3LogUtility.getInstance(WEB3TPTradingPowerServiceImplTest_validateTradingPowerEquitySell.class);

    public WEB3TPTradingPowerServiceImplTest_validateTradingPowerEquitySell(String name)
    {
        super(name);
    }
    
    protected void setUp() throws Exception
    {
        super.setUp();
        MOCK_MANAGER.setIsMockUsed(true);
        this.l_serviceImpl = new WEB3TPTradingPowerServiceImpl();
        this.initData();
        this.getMockData();
    }
    
    protected void tearDown() throws Exception
    {
        this.l_serviceImpl = null;
        super.tearDown();
    }

    public void testvalidateTradingPowerFeqSell_C0001()
    {
        final String STR_METHOD_NAME = "testvalidateTradingPowerFeqSell_C0001";
        log.entering(TEST_START + STR_METHOD_NAME);
        try
        {
            TestDBUtility.deleteAll(TpCalcResultEquityParams.TYPE);
            this.isAssetEvaluation = true;
            Calendar l_calendar = Calendar.getInstance();
            l_calendar.set(Calendar.YEAR,2007);
            l_calendar.set(Calendar.MONTH,8);
            l_calendar.set(Calendar.DAY_OF_MONTH,28);
            l_calendar.set(Calendar.HOUR_OF_DAY,00);
            l_calendar.set(Calendar.MINUTE,00);
            l_calendar.set(Calendar.SECOND,00);
            SubAccountParams l_subAccountParams = TestDBUtility.getSubAccountRow();
            l_subAccountParams.setSubAccountType(SubAccountTypeEnum.EQUITY_SUB_ACCOUNT);
            WEB3GentradeSubAccount l_subAccount = new WEB3GentradeSubAccountForTest(l_subAccountParams);
            WEB3TPNewOrderSpec[] l_newOrderSpecs = new WEB3TPNewOrderSpec[1];
            WEB3TPNewOrderSpec l_orderSpec = new WEB3TPNewOrderSpec();
            l_orderSpec.setSubAccountId(333812512203L);
            l_orderSpec.setDeliveryDate(l_calendar.getTime());
            l_orderSpec.setProductId(123456L);
            l_orderSpec.setMarketId(3303L);
            l_newOrderSpecs[0] = l_orderSpec;
            
            WEB3TPTradingPowerResult l_result =
                this.l_serviceImpl.validateTradingPowerFeqSell(l_subAccount,l_newOrderSpecs,true);
            assertNull(l_result.getTpErrorInfo());
        }
        catch (Exception l_ex)
        {
            log.error("", l_ex);
            log.exiting(TEST_END + STR_METHOD_NAME);
            fail();
        }
        log.exiting(TEST_END + STR_METHOD_NAME);
    }

    public void testvalidateTradingPowerFeqSell_C0002()
    {
        final String STR_METHOD_NAME = "testvalidateTradingPowerFeqSell_C0002";
        log.entering(TEST_START + STR_METHOD_NAME);
        try
        {
            TestDBUtility.deleteAll(TpCalcResultEquityRow.TYPE);
            // MainAccountDao
            TestDBUtility.deleteAll(MainAccountRow.TYPE);
            MainAccountParams l_mainAccountParams =TestDBUtility.getMainAccountRow();
            l_mainAccountParams.setAccountId(333812512203L);
            TestDBUtility.insertWithDel(l_mainAccountParams);
            
            TestDBUtility.deleteAll(BranchRow.TYPE);
            BranchParams l_branchParams = TestDBUtility.getBranchRow();
            l_branchParams.setBranchId(33381);
            //「営業部店」の場合
            l_branchParams.setBranchType(BranchTypeEnum.REGULAR_LOCAL_BRANCH);
            TestDBUtility.insertWithDel(l_branchParams);
            
            // TradingpowerCalcConditionRow
            TestDBUtility.deleteAll(TradingpowerCalcConditionRow.TYPE);
            TradingpowerCalcConditionParams l_tradingpowerCalcConditionParam = TestDBUtility.getTradingpowerCalcConditionRow();
            l_tradingpowerCalcConditionParam.setAccountId(333812512203L);
            l_tradingpowerCalcConditionParam.setBranchId(33381);
            l_tradingpowerCalcConditionParam.setSpecialDebitAmount(-20);
            l_tradingpowerCalcConditionParam.setTradingStop("0");
            l_tradingpowerCalcConditionParam.setAdditionalDepositStop("0");
            TestDBUtility.insertWithDel(l_tradingpowerCalcConditionParam);
            
            this.isAssetEvaluation = true;
            Calendar l_calendar = Calendar.getInstance();
            l_calendar.set(Calendar.YEAR,2007);
            l_calendar.set(Calendar.MONTH,8);
            l_calendar.set(Calendar.DAY_OF_MONTH,28);
            l_calendar.set(Calendar.HOUR_OF_DAY,00);
            l_calendar.set(Calendar.MINUTE,00);
            l_calendar.set(Calendar.SECOND,00);
            SubAccountParams l_subAccountParams = TestDBUtility.getSubAccountRow();
            l_subAccountParams.setSubAccountType(SubAccountTypeEnum.EQUITY_SUB_ACCOUNT);
            WEB3GentradeSubAccount l_subAccount = new WEB3GentradeSubAccountForTest(l_subAccountParams);
            WEB3TPNewOrderSpec[] l_newOrderSpecs = new WEB3TPNewOrderSpec[1];
            WEB3TPNewOrderSpec l_orderSpec = new WEB3TPNewOrderSpec();
            l_orderSpec.setSubAccountId(333812512203L);
            l_orderSpec.setDeliveryDate(l_calendar.getTime());
            l_orderSpec.setProductId(123456L);
            l_orderSpec.setMarketId(3303L);
            l_newOrderSpecs[0] = l_orderSpec;
            
            WEB3TPTradingPowerResult l_tpResult = this.l_serviceImpl.validateTradingPowerFeqSell(l_subAccount,l_newOrderSpecs,true);
            
            assertNull(l_tpResult.getAttentionObjectionType());
        }
        catch (Exception l_ex)
        {
            log.error("", l_ex);
            log.exiting(TEST_END + STR_METHOD_NAME);
            fail();
        }
        log.exiting(TEST_END + STR_METHOD_NAME);
    }

    public void testvalidateTradingPowerFeqSell_C0003()
    {
        final String STR_METHOD_NAME = "testvalidateTradingPowerFeqSell_C0003";
        log.entering(TEST_START + STR_METHOD_NAME);
        try
        {
            TestDBUtility.deleteAll(BranchRow.TYPE);
            BranchParams l_branchParams = TestDBUtility.getBranchRow();
            l_branchParams.setBranchId(33381);
            TestDBUtility.insertWithDel(l_branchParams);
            
            // TradingpowerCalcConditionRow
            TestDBUtility.deleteAll(TradingpowerCalcConditionRow.TYPE);
            TradingpowerCalcConditionParams l_tradingpowerCalcConditionParam = TestDBUtility.getTradingpowerCalcConditionRow();
            l_tradingpowerCalcConditionParam.setAccountId(333812512203L);
            l_tradingpowerCalcConditionParam.setBranchId(33381);
            l_tradingpowerCalcConditionParam.setSpecialDebitAmount(-20);
            l_tradingpowerCalcConditionParam.setTradingStop("0");
            l_tradingpowerCalcConditionParam.setAdditionalDepositStop("0");
            l_tradingpowerCalcConditionParam.setOtherTradingStop("0");
            TestDBUtility.insertWithDel(l_tradingpowerCalcConditionParam);
            
            //TpCashBalance
            TpCashBalanceParams l_tpCashBalance = new TpCashBalanceParams();
            l_tpCashBalance.setAccountId(333812512203L);
            l_tpCashBalance.setSubAccountId(33381251220301L);
            l_tpCashBalance.setCashBalance2(-2.0d);
            l_tpCashBalance.setCashBalance5(2.0d);
            TestDBUtility.insertWithDel(l_tpCashBalance);

            this.isAssetEvaluation = true;
            Calendar l_calendar = Calendar.getInstance();
            l_calendar.set(Calendar.YEAR,2007);
            l_calendar.set(Calendar.MONTH,8);
            l_calendar.set(Calendar.DAY_OF_MONTH,28);
            l_calendar.set(Calendar.HOUR_OF_DAY,00);
            l_calendar.set(Calendar.MINUTE,00);
            l_calendar.set(Calendar.SECOND,00);
            SubAccountParams l_subAccountParams = TestDBUtility.getSubAccountRow();
            l_subAccountParams.setSubAccountType(SubAccountTypeEnum.EQUITY_SUB_ACCOUNT);
            WEB3GentradeSubAccount l_subAccount = new WEB3GentradeSubAccountForTest(l_subAccountParams);
            WEB3TPNewOrderSpec[] l_newOrderSpecs = new WEB3TPNewOrderSpec[1];
            WEB3TPNewOrderSpec l_orderSpec = new WEB3TPNewOrderSpec();
            l_orderSpec.setSubAccountId(333812512203L);
            l_orderSpec.setDeliveryDate(l_calendar.getTime());
            l_orderSpec.setProductId(123456L);
            l_orderSpec.setMarketId(3303L);
            l_orderSpec.setDeliveryDate(WEB3DateUtility.getDate("20071002", "yyyyMMdd"));
            l_newOrderSpecs[0] = l_orderSpec;
            
            WEB3TPTradingPowerResult l_tpResult = this.l_serviceImpl.validateTradingPowerFeqSell(l_subAccount,l_newOrderSpecs,true);
            
            assertNull(l_tpResult.getAttentionObjectionType());
        }
        catch (Exception l_ex)
        {
            log.error("", l_ex);
            log.exiting(TEST_END + STR_METHOD_NAME);
            fail();
        }
        log.exiting(TEST_END + STR_METHOD_NAME);
    }

    public void testvalidateTradingPowerFeqSell_C0004()
    {
        final String STR_METHOD_NAME = "testvalidateTradingPowerFeqSell_C0004";
        log.entering(TEST_START + STR_METHOD_NAME);
        try
        {
            TestDBUtility.deleteAll(TpCalcResultEquityParams.TYPE);
            TestDBUtility.deleteAll(TpCalcResultMarginParams.TYPE);
            TestDBUtility.deleteAll(EqtypeOrderUnitRow.TYPE);
            EqtypeOrderUnitParams l_eqtypeOrderUnitParams =TestDBUtility.getEqtypeOrderUnitRow();
            l_eqtypeOrderUnitParams.setBizDate("20070917");
            l_eqtypeOrderUnitParams.setOrderType(OrderTypeEnum.MARGIN_LONG);
            l_eqtypeOrderUnitParams.setDeliveryDate(WEB3DateUtility.getDate("20070917", "yyyyMMdd"));
            TestDBUtility.insertWithDel(l_eqtypeOrderUnitParams);

            TestDBUtility.deleteAll(EqtypeTradedProductUpdqParams.TYPE);
            EqtypeTradedProductUpdqParams l_eqtypeTradedProductUpdqParams =
                TestDBUtility.getEqtypeTradedProductUpdqRow();
            l_eqtypeTradedProductUpdqParams.setProductId(3304148080001L);
            l_eqtypeTradedProductUpdqParams.setMarketId(3303L);
            l_eqtypeTradedProductUpdqParams.setValidUntilBizDate("20070917");
            TestDBUtility.insertWithDel(l_eqtypeTradedProductUpdqParams);

            TestDBUtility.deleteAll(SubAccountRow.TYPE);
            SubAccountParams l_subAccountParams = TestDBUtility.getSubAccountRow();
            l_subAccountParams.setSubAccountType(SubAccountTypeEnum.EQUITY_MARGIN_SUB_ACCOUNT);
            TestDBUtility.insertWithDel(l_subAccountParams);

            SubAccountParams l_subAccountParams1 = TestDBUtility.getSubAccountRow();
            l_subAccountParams1.setSubAccountId(1);
            l_subAccountParams1.setSubAccountType(SubAccountTypeEnum.EQUITY_SUB_ACCOUNT);
            TestDBUtility.insertWithDel(l_subAccountParams1);

            TestDBUtility.deleteAll(MainAccountRow.TYPE);
            MainAccountParams l_mainAccountParams =TestDBUtility.getMainAccountRow();
            l_mainAccountParams.setAccountId(l_subAccountParams.getAccountId());
            l_mainAccountParams.setMarginGenAccOpenDiv(WEB3MarginAccountOpenDivDef.MARGIN_ACCOUNT_OPEN);
            TestDBUtility.insertWithDel(l_mainAccountParams);

            TestDBUtility.deleteAll(BranchRow.TYPE);
            BranchParams l_branchParams = TestDBUtility.getBranchRow();
            l_branchParams.setBranchId(33381);
            TestDBUtility.insertWithDel(l_branchParams);
            
            // TradingpowerCalcConditionRow
            TestDBUtility.deleteAll(TradingpowerCalcConditionRow.TYPE);
            TradingpowerCalcConditionParams l_tradingpowerCalcConditionParam = TestDBUtility.getTradingpowerCalcConditionRow();
            l_tradingpowerCalcConditionParam.setAccountId(333812512203L);
            l_tradingpowerCalcConditionParam.setBranchId(33381);
            l_tradingpowerCalcConditionParam.setSpecialDebitAmount(-20);
            l_tradingpowerCalcConditionParam.setTradingStop("0");
            l_tradingpowerCalcConditionParam.setAdditionalDepositStop("0");
            l_tradingpowerCalcConditionParam.setOtherTradingStop("0");
            TestDBUtility.insertWithDel(l_tradingpowerCalcConditionParam);
            
            //TpCashBalance
            TpCashBalanceParams l_tpCashBalance = new TpCashBalanceParams();
            l_tpCashBalance.setAccountId(333812512203L);
            l_tpCashBalance.setSubAccountId(33381251220301L);
            l_tpCashBalance.setCashBalance2(-2.0d);
            l_tpCashBalance.setCashBalance5(2.0d);
            TestDBUtility.insertWithDel(l_tpCashBalance);

            this.isAssetEvaluation = true;
            Calendar l_calendar = Calendar.getInstance();
            l_calendar.set(Calendar.YEAR,2007);
            l_calendar.set(Calendar.MONTH,8);
            l_calendar.set(Calendar.DAY_OF_MONTH,28);
            l_calendar.set(Calendar.HOUR_OF_DAY,00);
            l_calendar.set(Calendar.MINUTE,00);
            l_calendar.set(Calendar.SECOND,00);

            WEB3GentradeSubAccount l_subAccount = new WEB3GentradeSubAccount(l_subAccountParams);
            WEB3TPNewOrderSpec[] l_newOrderSpecs = new WEB3TPNewOrderSpec[1];
            WEB3TPNewOrderSpec l_orderSpec = new WEB3TPNewOrderSpec();
            l_orderSpec.setSubAccountId(333812512203L);
            l_orderSpec.setDeliveryDate(l_calendar.getTime());
            l_orderSpec.setProductId(123456L);
            l_orderSpec.setMarketId(3303L);
            l_orderSpec.setOrderBizDate(WEB3DateUtility.getDate("20071002", "yyyyMMdd"));
            l_orderSpec.setDeliveryDate(WEB3DateUtility.getDate("20071002", "yyyyMMdd"));
            l_newOrderSpecs[0] = l_orderSpec;

            TestDBUtility.deleteAll(TpCalcResultMarginParams.TYPE);
            TpCalcResultMarginParams l_tpCalcResultMarginParams = TestDBUtility.getTpCalcResultMarginRow();
            TestDBUtility.insertWithDel(l_tpCalcResultMarginParams);
            TestDBUtility.deleteAll(TpCalcResultMarginDetailParams.TYPE);
            TpCalcResultMarginDetailParams l_tpCalcResultMarginDetailParams =
                TestDBUtility.getTpCalcResultMarginDetailRow();
            TestDBUtility.insertWithDel(l_tpCalcResultMarginDetailParams);
            
            WEB3TPTradingPowerResult l_tpResult = this.l_serviceImpl.validateTradingPowerFeqSell(l_subAccount,l_newOrderSpecs,true);
            
            assertNull(l_tpResult.getAttentionObjectionType());
        }
        catch (Exception l_ex)
        {
            log.error("", l_ex);
            log.exiting(TEST_END + STR_METHOD_NAME);
//            fail();
        }
        log.exiting(TEST_END + STR_METHOD_NAME);
    }

    public void testvalidateTradingPowerFeqBuy_C0001()
    {
        final String STR_METHOD_NAME = "testvalidateTradingPowerFeqBuy_C0001";
        log.entering(TEST_START + STR_METHOD_NAME);
        try
        {
            this.isAssetEvaluation = true;
            Calendar l_calendar = Calendar.getInstance();
            l_calendar.set(Calendar.YEAR,2007);
            l_calendar.set(Calendar.MONTH,8);
            l_calendar.set(Calendar.DAY_OF_MONTH,28);
            l_calendar.set(Calendar.HOUR_OF_DAY,00);
            l_calendar.set(Calendar.MINUTE,00);
            l_calendar.set(Calendar.SECOND,00);
            SubAccountParams l_subAccountParams = TestDBUtility.getSubAccountRow();
            l_subAccountParams.setSubAccountType(SubAccountTypeEnum.EQUITY_SUB_ACCOUNT);
            WEB3GentradeSubAccount l_subAccount = new WEB3GentradeSubAccountForTest(l_subAccountParams);
            WEB3TPNewOrderSpec[] l_newOrderSpecs = new WEB3TPNewOrderSpec[1];
            WEB3TPNewOrderSpec l_orderSpec = new WEB3TPNewOrderSpec();
            l_orderSpec.setSubAccountId(333812512203L);
            l_orderSpec.setDeliveryDate(l_calendar.getTime());
            l_orderSpec.setProductId(123456L);
            l_orderSpec.setMarketId(3303L);
            l_newOrderSpecs[0] = l_orderSpec;
            
            WEB3TPTradingPowerResult l_result =
                this.l_serviceImpl.validateTradingPowerFeqBuy(l_subAccount,l_newOrderSpecs,true);
            assertNull(l_result.getTpErrorInfo().marginSecInfo);
        }
        catch (Exception l_ex)
        {
            log.error("", l_ex);
            log.exiting(TEST_END + STR_METHOD_NAME);
            fail();
        }
        log.exiting(TEST_END + STR_METHOD_NAME);
    }

    public void testvalidateTradingPowerFeqBuy_C0002()
    {
        final String STR_METHOD_NAME = "testvalidateTradingPowerFeqBuy_C0002";
        log.entering(TEST_START + STR_METHOD_NAME);
        try
        {
            TestDBUtility.deleteAll(TpCalcResultEquityRow.TYPE);
            // MainAccountDao
            TestDBUtility.deleteAll(MainAccountRow.TYPE);
            MainAccountParams l_mainAccountParams =TestDBUtility.getMainAccountRow();
            l_mainAccountParams.setAccountId(333812512203L);
            TestDBUtility.insertWithDel(l_mainAccountParams);
            
            TestDBUtility.deleteAll(BranchRow.TYPE);
            BranchParams l_branchParams = TestDBUtility.getBranchRow();
            l_branchParams.setBranchId(33381);
            //「営業部店」の場合
            l_branchParams.setBranchType(BranchTypeEnum.REGULAR_LOCAL_BRANCH);
            TestDBUtility.insertWithDel(l_branchParams);
            
            // TradingpowerCalcConditionRow
            TestDBUtility.deleteAll(TradingpowerCalcConditionRow.TYPE);
            TradingpowerCalcConditionParams l_tradingpowerCalcConditionParam = TestDBUtility.getTradingpowerCalcConditionRow();
            l_tradingpowerCalcConditionParam.setAccountId(333812512203L);
            l_tradingpowerCalcConditionParam.setBranchId(33381);
            l_tradingpowerCalcConditionParam.setSpecialDebitAmount(-20);
            l_tradingpowerCalcConditionParam.setTradingStop("0");
            l_tradingpowerCalcConditionParam.setAdditionalDepositStop("0");
            TestDBUtility.insertWithDel(l_tradingpowerCalcConditionParam);
            
            this.isAssetEvaluation = true;
            Calendar l_calendar = Calendar.getInstance();
            l_calendar.set(Calendar.YEAR,2007);
            l_calendar.set(Calendar.MONTH,8);
            l_calendar.set(Calendar.DAY_OF_MONTH,28);
            l_calendar.set(Calendar.HOUR_OF_DAY,00);
            l_calendar.set(Calendar.MINUTE,00);
            l_calendar.set(Calendar.SECOND,00);
            SubAccountParams l_subAccountParams = TestDBUtility.getSubAccountRow();
            l_subAccountParams.setSubAccountType(SubAccountTypeEnum.EQUITY_SUB_ACCOUNT);
            WEB3GentradeSubAccount l_subAccount = new WEB3GentradeSubAccountForTest(l_subAccountParams);
            WEB3TPNewOrderSpec[] l_newOrderSpecs = new WEB3TPNewOrderSpec[1];
            WEB3TPNewOrderSpec l_orderSpec = new WEB3TPNewOrderSpec();
            l_orderSpec.setSubAccountId(333812512203L);
            l_orderSpec.setDeliveryDate(l_calendar.getTime());
            l_orderSpec.setProductId(123456L);
            l_orderSpec.setMarketId(3303L);
            l_newOrderSpecs[0] = l_orderSpec;
            
            WEB3TPTradingPowerResult l_tpResult = this.l_serviceImpl.validateTradingPowerFeqBuy(l_subAccount,l_newOrderSpecs,true);
            
            assertNull(l_tpResult.getAttentionObjectionType());
        }
        catch (Exception l_ex)
        {
            log.error("", l_ex);
            log.exiting(TEST_END + STR_METHOD_NAME);
            fail();
        }
        log.exiting(TEST_END + STR_METHOD_NAME);
    }

    public void testvalidateTradingPowerFeqBuy_C0003()
    {
        final String STR_METHOD_NAME = "testvalidateTradingPowerFeqBuy_C0003";
        log.entering(TEST_START + STR_METHOD_NAME);
        try
        {
            TestDBUtility.deleteAll(BranchRow.TYPE);
            BranchParams l_branchParams = TestDBUtility.getBranchRow();
            l_branchParams.setBranchId(33381);
            TestDBUtility.insertWithDel(l_branchParams);
            
            // TradingpowerCalcConditionRow
            TestDBUtility.deleteAll(TradingpowerCalcConditionRow.TYPE);
            TradingpowerCalcConditionParams l_tradingpowerCalcConditionParam = TestDBUtility.getTradingpowerCalcConditionRow();
            l_tradingpowerCalcConditionParam.setAccountId(333812512203L);
            l_tradingpowerCalcConditionParam.setBranchId(33381);
            l_tradingpowerCalcConditionParam.setSpecialDebitAmount(-20);
            l_tradingpowerCalcConditionParam.setTradingStop("0");
            l_tradingpowerCalcConditionParam.setAdditionalDepositStop("0");
            l_tradingpowerCalcConditionParam.setOtherTradingStop("0");
            TestDBUtility.insertWithDel(l_tradingpowerCalcConditionParam);
            
            //TpCashBalance
            TpCashBalanceParams l_tpCashBalance = new TpCashBalanceParams();
            l_tpCashBalance.setAccountId(333812512203L);
            l_tpCashBalance.setSubAccountId(33381251220301L);
            l_tpCashBalance.setCashBalance2(-2.0d);
            l_tpCashBalance.setCashBalance5(2.0d);
            TestDBUtility.insertWithDel(l_tpCashBalance);

            this.isAssetEvaluation = true;
            Calendar l_calendar = Calendar.getInstance();
            l_calendar.set(Calendar.YEAR,2007);
            l_calendar.set(Calendar.MONTH,8);
            l_calendar.set(Calendar.DAY_OF_MONTH,28);
            l_calendar.set(Calendar.HOUR_OF_DAY,00);
            l_calendar.set(Calendar.MINUTE,00);
            l_calendar.set(Calendar.SECOND,00);
            SubAccountParams l_subAccountParams = TestDBUtility.getSubAccountRow();
            l_subAccountParams.setSubAccountType(SubAccountTypeEnum.EQUITY_SUB_ACCOUNT);
            WEB3GentradeSubAccount l_subAccount = new WEB3GentradeSubAccountForTest(l_subAccountParams);
            WEB3TPNewOrderSpec[] l_newOrderSpecs = new WEB3TPNewOrderSpec[1];
            WEB3TPNewOrderSpec l_orderSpec = new WEB3TPNewOrderSpec();
            l_orderSpec.setSubAccountId(333812512203L);
            l_orderSpec.setDeliveryDate(l_calendar.getTime());
            l_orderSpec.setProductId(123456L);
            l_orderSpec.setMarketId(3303L);
            l_orderSpec.setDeliveryDate(WEB3DateUtility.getDate("20071002", "yyyyMMdd"));
            l_newOrderSpecs[0] = l_orderSpec;
            
            WEB3TPTradingPowerResult l_tpResult = this.l_serviceImpl.validateTradingPowerFeqBuy(l_subAccount,l_newOrderSpecs,true);
            
            assertNull(l_tpResult.getAttentionObjectionType());
        }
        catch (Exception l_ex)
        {
            log.error("", l_ex);
            log.exiting(TEST_END + STR_METHOD_NAME);
            fail();
        }
        log.exiting(TEST_END + STR_METHOD_NAME);
    }

    public void testvalidateTradingPowerFeqBuy_C0004()
    {
        final String STR_METHOD_NAME = "testvalidateTradingPowerFeqBuy_C0004";
        log.entering(TEST_START + STR_METHOD_NAME);
        try
        {
            TestDBUtility.deleteAll(EqtypeOrderUnitRow.TYPE);
            EqtypeOrderUnitParams l_eqtypeOrderUnitParams =TestDBUtility.getEqtypeOrderUnitRow();
            l_eqtypeOrderUnitParams.setBizDate("20070917");
            l_eqtypeOrderUnitParams.setOrderType(OrderTypeEnum.MARGIN_LONG);
            l_eqtypeOrderUnitParams.setDeliveryDate(WEB3DateUtility.getDate("20070917", "yyyyMMdd"));
            TestDBUtility.insertWithDel(l_eqtypeOrderUnitParams);

            TestDBUtility.deleteAll(EqtypeTradedProductUpdqParams.TYPE);
            EqtypeTradedProductUpdqParams l_eqtypeTradedProductUpdqParams =
                TestDBUtility.getEqtypeTradedProductUpdqRow();
            l_eqtypeTradedProductUpdqParams.setProductId(3304148080001L);
            l_eqtypeTradedProductUpdqParams.setMarketId(3303L);
            l_eqtypeTradedProductUpdqParams.setValidUntilBizDate("20070917");
            TestDBUtility.insertWithDel(l_eqtypeTradedProductUpdqParams);

            TestDBUtility.deleteAll(SubAccountRow.TYPE);
            SubAccountParams l_subAccountParams = TestDBUtility.getSubAccountRow();
            l_subAccountParams.setSubAccountType(SubAccountTypeEnum.EQUITY_MARGIN_SUB_ACCOUNT);
            TestDBUtility.insertWithDel(l_subAccountParams);

            SubAccountParams l_subAccountParams1 = TestDBUtility.getSubAccountRow();
            l_subAccountParams1.setSubAccountId(1);
            l_subAccountParams1.setSubAccountType(SubAccountTypeEnum.EQUITY_SUB_ACCOUNT);
            TestDBUtility.insertWithDel(l_subAccountParams1);

            TestDBUtility.deleteAll(MainAccountRow.TYPE);
            MainAccountParams l_mainAccountParams =TestDBUtility.getMainAccountRow();
            l_mainAccountParams.setAccountId(l_subAccountParams.getAccountId());
            l_mainAccountParams.setMarginGenAccOpenDiv(WEB3MarginAccountOpenDivDef.MARGIN_ACCOUNT_OPEN);
            TestDBUtility.insertWithDel(l_mainAccountParams);

            TestDBUtility.deleteAll(BranchRow.TYPE);
            BranchParams l_branchParams = TestDBUtility.getBranchRow();
            l_branchParams.setBranchId(33381);
            TestDBUtility.insertWithDel(l_branchParams);
            
            // TradingpowerCalcConditionRow
            TestDBUtility.deleteAll(TradingpowerCalcConditionRow.TYPE);
            TradingpowerCalcConditionParams l_tradingpowerCalcConditionParam = TestDBUtility.getTradingpowerCalcConditionRow();
            l_tradingpowerCalcConditionParam.setAccountId(333812512203L);
            l_tradingpowerCalcConditionParam.setBranchId(33381);
            l_tradingpowerCalcConditionParam.setSpecialDebitAmount(-20);
            l_tradingpowerCalcConditionParam.setTradingStop("0");
            l_tradingpowerCalcConditionParam.setAdditionalDepositStop("0");
            l_tradingpowerCalcConditionParam.setOtherTradingStop("0");
            TestDBUtility.insertWithDel(l_tradingpowerCalcConditionParam);
            
            //TpCashBalance
            TpCashBalanceParams l_tpCashBalance = new TpCashBalanceParams();
            l_tpCashBalance.setAccountId(333812512203L);
            l_tpCashBalance.setSubAccountId(33381251220301L);
            l_tpCashBalance.setCashBalance2(-2.0d);
            l_tpCashBalance.setCashBalance5(2.0d);
            TestDBUtility.insertWithDel(l_tpCashBalance);

            this.isAssetEvaluation = true;
            Calendar l_calendar = Calendar.getInstance();
            l_calendar.set(Calendar.YEAR,2007);
            l_calendar.set(Calendar.MONTH,8);
            l_calendar.set(Calendar.DAY_OF_MONTH,28);
            l_calendar.set(Calendar.HOUR_OF_DAY,00);
            l_calendar.set(Calendar.MINUTE,00);
            l_calendar.set(Calendar.SECOND,00);

            WEB3GentradeSubAccount l_subAccount = new WEB3GentradeSubAccount(l_subAccountParams);
            WEB3TPNewOrderSpec[] l_newOrderSpecs = new WEB3TPNewOrderSpec[1];
            WEB3TPNewOrderSpec l_orderSpec = new WEB3TPNewOrderSpec();
            l_orderSpec.setSubAccountId(333812512203L);
            l_orderSpec.setDeliveryDate(l_calendar.getTime());
            l_orderSpec.setProductId(123456L);
            l_orderSpec.setMarketId(3303L);
            l_orderSpec.setOrderBizDate(WEB3DateUtility.getDate("20071002", "yyyyMMdd"));
            l_orderSpec.setDeliveryDate(WEB3DateUtility.getDate("20071002", "yyyyMMdd"));
            l_newOrderSpecs[0] = l_orderSpec;

            TestDBUtility.deleteAll(TpCalcResultMarginParams.TYPE);
            TpCalcResultMarginParams l_tpCalcResultMarginParams = TestDBUtility.getTpCalcResultMarginRow();
            TestDBUtility.insertWithDel(l_tpCalcResultMarginParams);
            TestDBUtility.deleteAll(TpCalcResultMarginDetailParams.TYPE);
            TpCalcResultMarginDetailParams l_tpCalcResultMarginDetailParams =
                TestDBUtility.getTpCalcResultMarginDetailRow();
            TestDBUtility.insertWithDel(l_tpCalcResultMarginDetailParams);
            
            WEB3TPTradingPowerResult l_tpResult = this.l_serviceImpl.validateTradingPowerFeqBuy(l_subAccount,l_newOrderSpecs,true);
            
            assertNull(l_tpResult.getAttentionObjectionType());
        }
        catch (Exception l_ex)
        {
            log.error("", l_ex);
            log.exiting(TEST_END + STR_METHOD_NAME);
            fail();
        }
        log.exiting(TEST_END + STR_METHOD_NAME);
    }

    public void testvalidateTradingPowerFeqBuy_C0005()
    {
        final String STR_METHOD_NAME = "testvalidateTradingPowerFeqBuy_C0005";
        log.entering(TEST_START + STR_METHOD_NAME);
        try
        {
            TestDBUtility.deleteAll(EqtypeOrderUnitRow.TYPE);
            EqtypeOrderUnitParams l_eqtypeOrderUnitParams =TestDBUtility.getEqtypeOrderUnitRow();
            l_eqtypeOrderUnitParams.setBizDate("20070917");
            l_eqtypeOrderUnitParams.setOrderType(OrderTypeEnum.MARGIN_LONG);
            l_eqtypeOrderUnitParams.setDeliveryDate(WEB3DateUtility.getDate("20070917", "yyyyMMdd"));
            TestDBUtility.insertWithDel(l_eqtypeOrderUnitParams);

            TestDBUtility.deleteAll(EqtypeTradedProductUpdqParams.TYPE);
            EqtypeTradedProductUpdqParams l_eqtypeTradedProductUpdqParams =
                TestDBUtility.getEqtypeTradedProductUpdqRow();
            l_eqtypeTradedProductUpdqParams.setProductId(3304148080001L);
            l_eqtypeTradedProductUpdqParams.setMarketId(3303L);
            l_eqtypeTradedProductUpdqParams.setValidUntilBizDate("20070917");
            TestDBUtility.insertWithDel(l_eqtypeTradedProductUpdqParams);

            TestDBUtility.deleteAll(SubAccountRow.TYPE);
            SubAccountParams l_subAccountParams = TestDBUtility.getSubAccountRow();
            l_subAccountParams.setSubAccountType(SubAccountTypeEnum.EQUITY_MARGIN_SUB_ACCOUNT);
            TestDBUtility.insertWithDel(l_subAccountParams);

            SubAccountParams l_subAccountParams1 = TestDBUtility.getSubAccountRow();
            l_subAccountParams1.setSubAccountId(1);
            l_subAccountParams1.setSubAccountType(SubAccountTypeEnum.EQUITY_SUB_ACCOUNT);
            TestDBUtility.insertWithDel(l_subAccountParams1);

            TestDBUtility.deleteAll(MainAccountRow.TYPE);
            MainAccountParams l_mainAccountParams =TestDBUtility.getMainAccountRow();
            l_mainAccountParams.setAccountId(l_subAccountParams.getAccountId());
            l_mainAccountParams.setMarginGenAccOpenDiv(WEB3MarginAccountOpenDivDef.MARGIN_ACCOUNT_OPEN);
            TestDBUtility.insertWithDel(l_mainAccountParams);

            TestDBUtility.deleteAll(BranchRow.TYPE);
            BranchParams l_branchParams = TestDBUtility.getBranchRow();
            l_branchParams.setBranchId(33381);
            TestDBUtility.insertWithDel(l_branchParams);
            
            // TradingpowerCalcConditionRow
            TestDBUtility.deleteAll(TradingpowerCalcConditionRow.TYPE);
            TradingpowerCalcConditionParams l_tradingpowerCalcConditionParam = TestDBUtility.getTradingpowerCalcConditionRow();
            l_tradingpowerCalcConditionParam.setAccountId(333812512203L);
            l_tradingpowerCalcConditionParam.setBranchId(33381);
            l_tradingpowerCalcConditionParam.setSpecialDebitAmount(-20);
            l_tradingpowerCalcConditionParam.setTradingStop("0");
            l_tradingpowerCalcConditionParam.setAdditionalDepositStop("0");
            l_tradingpowerCalcConditionParam.setOtherTradingStop("0");
            TestDBUtility.insertWithDel(l_tradingpowerCalcConditionParam);
            
            //TpCashBalance
            TpCashBalanceParams l_tpCashBalance = new TpCashBalanceParams();
            l_tpCashBalance.setAccountId(333812512203L);
            l_tpCashBalance.setSubAccountId(33381251220301L);
            l_tpCashBalance.setCashBalance2(-2.0d);
            l_tpCashBalance.setCashBalance5(2.0d);
            TestDBUtility.insertWithDel(l_tpCashBalance);

            this.isAssetEvaluation = true;
            Calendar l_calendar = Calendar.getInstance();
            l_calendar.set(Calendar.YEAR,2007);
            l_calendar.set(Calendar.MONTH,8);
            l_calendar.set(Calendar.DAY_OF_MONTH,28);
            l_calendar.set(Calendar.HOUR_OF_DAY,00);
            l_calendar.set(Calendar.MINUTE,00);
            l_calendar.set(Calendar.SECOND,00);

            WEB3GentradeSubAccount l_subAccount = new WEB3GentradeSubAccount(l_subAccountParams);
            WEB3TPNewOrderSpec[] l_newOrderSpecs = new WEB3TPNewOrderSpec[1];
            WEB3TPNewOrderSpec l_orderSpec = new WEB3TPNewOrderSpec();
            l_orderSpec.setSubAccountId(333812512203L);
            l_orderSpec.setDeliveryDate(l_calendar.getTime());
            l_orderSpec.setProductId(123456L);
            l_orderSpec.setMarketId(3303L);
            l_orderSpec.setOrderBizDate(WEB3DateUtility.getDate("20071002", "yyyyMMdd"));
            l_orderSpec.setDeliveryDate(WEB3DateUtility.getDate("20071002", "yyyyMMdd"));
            l_newOrderSpecs[0] = l_orderSpec;

            TestDBUtility.deleteAll(TpCalcResultMarginParams.TYPE);
            TpCalcResultMarginParams l_tpCalcResultMarginParams = TestDBUtility.getTpCalcResultMarginRow();
            TestDBUtility.insertWithDel(l_tpCalcResultMarginParams);
            TestDBUtility.deleteAll(TpCalcResultMarginDetailParams.TYPE);
            TpCalcResultMarginDetailParams l_tpCalcResultMarginDetailParams =
                TestDBUtility.getTpCalcResultMarginDetailRow();
            TestDBUtility.insertWithDel(l_tpCalcResultMarginDetailParams);
            
            WEB3TPTradingPowerResult l_tpResult = this.l_serviceImpl.validateTradingPowerFeqBuy(l_subAccount,l_newOrderSpecs,true);
            
            assertNull(l_tpResult.getAttentionObjectionType());
        }
        catch (Exception l_ex)
        {
            log.error("", l_ex);
            log.exiting(TEST_END + STR_METHOD_NAME);
            fail();
        }
        log.exiting(TEST_END + STR_METHOD_NAME);
    }

    public void testvalidateTradingPowerFeqBuy_C0006()
    {
        final String STR_METHOD_NAME = "testvalidateTradingPowerFeqBuy_C0006";
        log.entering(TEST_START + STR_METHOD_NAME);
        try
        {
            TestDBUtility.deleteAll(EqtypeOrderUnitRow.TYPE);
            EqtypeOrderUnitParams l_eqtypeOrderUnitParams =TestDBUtility.getEqtypeOrderUnitRow();
            l_eqtypeOrderUnitParams.setBizDate("20070917");
            l_eqtypeOrderUnitParams.setOrderType(OrderTypeEnum.MARGIN_LONG);
            l_eqtypeOrderUnitParams.setDeliveryDate(WEB3DateUtility.getDate("20070917", "yyyyMMdd"));
            TestDBUtility.insertWithDel(l_eqtypeOrderUnitParams);

            TestDBUtility.deleteAll(EqtypeTradedProductUpdqParams.TYPE);
            EqtypeTradedProductUpdqParams l_eqtypeTradedProductUpdqParams =
                TestDBUtility.getEqtypeTradedProductUpdqRow();
            l_eqtypeTradedProductUpdqParams.setProductId(3304148080001L);
            l_eqtypeTradedProductUpdqParams.setMarketId(3303L);
            l_eqtypeTradedProductUpdqParams.setValidUntilBizDate("20070917");
            TestDBUtility.insertWithDel(l_eqtypeTradedProductUpdqParams);

            TestDBUtility.deleteAll(SubAccountRow.TYPE);
            SubAccountParams l_subAccountParams = TestDBUtility.getSubAccountRow();
            l_subAccountParams.setSubAccountType(SubAccountTypeEnum.EQUITY_MARGIN_SUB_ACCOUNT);
            TestDBUtility.insertWithDel(l_subAccountParams);

            SubAccountParams l_subAccountParams1 = TestDBUtility.getSubAccountRow();
            l_subAccountParams1.setSubAccountId(1);
            l_subAccountParams1.setSubAccountType(SubAccountTypeEnum.EQUITY_SUB_ACCOUNT);
            TestDBUtility.insertWithDel(l_subAccountParams1);

            TestDBUtility.deleteAll(MainAccountRow.TYPE);
            MainAccountParams l_mainAccountParams =TestDBUtility.getMainAccountRow();
            l_mainAccountParams.setAccountId(l_subAccountParams.getAccountId());
            l_mainAccountParams.setMarginGenAccOpenDiv(WEB3MarginAccountOpenDivDef.MARGIN_ACCOUNT_OPEN);
            TestDBUtility.insertWithDel(l_mainAccountParams);

            TestDBUtility.deleteAll(BranchRow.TYPE);
            BranchParams l_branchParams = TestDBUtility.getBranchRow();
            l_branchParams.setBranchId(33381);
            TestDBUtility.insertWithDel(l_branchParams);
            
            // TradingpowerCalcConditionRow
            TestDBUtility.deleteAll(TradingpowerCalcConditionRow.TYPE);
            TradingpowerCalcConditionParams l_tradingpowerCalcConditionParam = TestDBUtility.getTradingpowerCalcConditionRow();
            l_tradingpowerCalcConditionParam.setAccountId(333812512203L);
            l_tradingpowerCalcConditionParam.setBranchId(33381);
            l_tradingpowerCalcConditionParam.setSpecialDebitAmount(-20);
            l_tradingpowerCalcConditionParam.setTradingStop("0");
            l_tradingpowerCalcConditionParam.setAdditionalDepositStop("0");
            l_tradingpowerCalcConditionParam.setOtherTradingStop("0");
            TestDBUtility.insertWithDel(l_tradingpowerCalcConditionParam);
            
            //TpCashBalance
            TpCashBalanceParams l_tpCashBalance = new TpCashBalanceParams();
            l_tpCashBalance.setAccountId(333812512203L);
            l_tpCashBalance.setSubAccountId(33381251220301L);
            l_tpCashBalance.setCashBalance2(-2.0d);
            l_tpCashBalance.setCashBalance5(2.0d);
            TestDBUtility.insertWithDel(l_tpCashBalance);

            this.isAssetEvaluation = true;
            Calendar l_calendar = Calendar.getInstance();
            l_calendar.set(Calendar.YEAR,2007);
            l_calendar.set(Calendar.MONTH,8);
            l_calendar.set(Calendar.DAY_OF_MONTH,28);
            l_calendar.set(Calendar.HOUR_OF_DAY,00);
            l_calendar.set(Calendar.MINUTE,00);
            l_calendar.set(Calendar.SECOND,00);

            WEB3GentradeSubAccount l_subAccount = new WEB3GentradeSubAccount(l_subAccountParams);
            WEB3TPNewOrderSpec[] l_newOrderSpecs = new WEB3TPNewOrderSpec[1];
            WEB3TPNewOrderSpec l_orderSpec = new WEB3TPNewOrderSpec();
            l_orderSpec.setSubAccountId(333812512203L);
            l_orderSpec.setDeliveryDate(l_calendar.getTime());
            l_orderSpec.setProductId(123456L);
            l_orderSpec.setMarketId(3303L);
            l_orderSpec.setOrderBizDate(WEB3DateUtility.getDate("20071002", "yyyyMMdd"));
            l_orderSpec.setDeliveryDate(WEB3DateUtility.getDate("20071002", "yyyyMMdd"));
            l_newOrderSpecs[0] = l_orderSpec;

            TestDBUtility.deleteAll(TpCalcResultMarginParams.TYPE);
            TpCalcResultMarginParams l_tpCalcResultMarginParams = TestDBUtility.getTpCalcResultMarginRow();
            TestDBUtility.insertWithDel(l_tpCalcResultMarginParams);
            TestDBUtility.deleteAll(TpCalcResultMarginDetailParams.TYPE);
            TpCalcResultMarginDetailParams l_tpCalcResultMarginDetailParams =
                TestDBUtility.getTpCalcResultMarginDetailRow();
            TestDBUtility.insertWithDel(l_tpCalcResultMarginDetailParams);
            
            WEB3TPTradingPowerResult l_tpResult = this.l_serviceImpl.validateTradingPowerFeqBuy(l_subAccount,l_newOrderSpecs,true);
            
            assertNull(l_tpResult.getAttentionObjectionType());
        }
        catch (Exception l_ex)
        {
            log.error("", l_ex);
            log.exiting(TEST_END + STR_METHOD_NAME);
            fail();
        }
        log.exiting(TEST_END + STR_METHOD_NAME);
    }

    public void testvalidateTradingPowerFeqBuy_C0007()
    {
        final String STR_METHOD_NAME = "testvalidateTradingPowerFeqBuy_C0007";
        log.entering(TEST_START + STR_METHOD_NAME);
        try
        {
            TestDBUtility.deleteAll(EqtypeOrderUnitRow.TYPE);
            EqtypeOrderUnitParams l_eqtypeOrderUnitParams =TestDBUtility.getEqtypeOrderUnitRow();
            l_eqtypeOrderUnitParams.setBizDate("20070917");
            l_eqtypeOrderUnitParams.setOrderType(OrderTypeEnum.MARGIN_LONG);
            l_eqtypeOrderUnitParams.setDeliveryDate(WEB3DateUtility.getDate("20070917", "yyyyMMdd"));
            TestDBUtility.insertWithDel(l_eqtypeOrderUnitParams);

            TestDBUtility.deleteAll(EqtypeTradedProductUpdqParams.TYPE);
            EqtypeTradedProductUpdqParams l_eqtypeTradedProductUpdqParams =
                TestDBUtility.getEqtypeTradedProductUpdqRow();
            l_eqtypeTradedProductUpdqParams.setProductId(3304148080001L);
            l_eqtypeTradedProductUpdqParams.setMarketId(3303L);
            l_eqtypeTradedProductUpdqParams.setValidUntilBizDate("20070917");
            TestDBUtility.insertWithDel(l_eqtypeTradedProductUpdqParams);

            TestDBUtility.deleteAll(SubAccountRow.TYPE);
            SubAccountParams l_subAccountParams = TestDBUtility.getSubAccountRow();
            l_subAccountParams.setSubAccountType(SubAccountTypeEnum.EQUITY_MARGIN_SUB_ACCOUNT);
            TestDBUtility.insertWithDel(l_subAccountParams);

            SubAccountParams l_subAccountParams1 = TestDBUtility.getSubAccountRow();
            l_subAccountParams1.setSubAccountId(1);
            l_subAccountParams1.setSubAccountType(SubAccountTypeEnum.EQUITY_SUB_ACCOUNT);
            TestDBUtility.insertWithDel(l_subAccountParams1);

            TestDBUtility.deleteAll(MainAccountRow.TYPE);
            MainAccountParams l_mainAccountParams =TestDBUtility.getMainAccountRow();
            l_mainAccountParams.setAccountId(l_subAccountParams.getAccountId());
            l_mainAccountParams.setMarginGenAccOpenDiv(WEB3MarginAccountOpenDivDef.MARGIN_ACCOUNT_OPEN);
            TestDBUtility.insertWithDel(l_mainAccountParams);

            TestDBUtility.deleteAll(BranchRow.TYPE);
            BranchParams l_branchParams = TestDBUtility.getBranchRow();
            l_branchParams.setBranchId(33381);
            TestDBUtility.insertWithDel(l_branchParams);
            
            // TradingpowerCalcConditionRow
            TestDBUtility.deleteAll(TradingpowerCalcConditionRow.TYPE);
            TradingpowerCalcConditionParams l_tradingpowerCalcConditionParam = TestDBUtility.getTradingpowerCalcConditionRow();
            l_tradingpowerCalcConditionParam.setAccountId(333812512203L);
            l_tradingpowerCalcConditionParam.setBranchId(33381);
            l_tradingpowerCalcConditionParam.setSpecialDebitAmount(-20);
            l_tradingpowerCalcConditionParam.setTradingStop("0");
            l_tradingpowerCalcConditionParam.setAdditionalDepositStop("0");
            l_tradingpowerCalcConditionParam.setOtherTradingStop("0");
            TestDBUtility.insertWithDel(l_tradingpowerCalcConditionParam);
            
            //TpCashBalance
            TpCashBalanceParams l_tpCashBalance = new TpCashBalanceParams();
            l_tpCashBalance.setAccountId(333812512203L);
            l_tpCashBalance.setSubAccountId(33381251220301L);
            l_tpCashBalance.setCashBalance2(-2.0d);
            l_tpCashBalance.setCashBalance5(2.0d);
            TestDBUtility.insertWithDel(l_tpCashBalance);

            this.isAssetEvaluation = true;
            Calendar l_calendar = Calendar.getInstance();
            l_calendar.set(Calendar.YEAR,2007);
            l_calendar.set(Calendar.MONTH,8);
            l_calendar.set(Calendar.DAY_OF_MONTH,28);
            l_calendar.set(Calendar.HOUR_OF_DAY,00);
            l_calendar.set(Calendar.MINUTE,00);
            l_calendar.set(Calendar.SECOND,00);

            WEB3GentradeSubAccount l_subAccount = new WEB3GentradeSubAccount(l_subAccountParams);
            WEB3TPNewOrderSpec[] l_newOrderSpecs = new WEB3TPNewOrderSpec[1];
            WEB3TPNewOrderSpec l_orderSpec = new WEB3TPNewOrderSpec();
            l_orderSpec.setSubAccountId(333812512203L);
            l_orderSpec.setDeliveryDate(l_calendar.getTime());
            l_orderSpec.setProductId(123456L);
            l_orderSpec.setMarketId(3303L);
            l_orderSpec.setOrderBizDate(WEB3DateUtility.getDate("20071002", "yyyyMMdd"));
            l_orderSpec.setDeliveryDate(WEB3DateUtility.getDate("20071002", "yyyyMMdd"));
            l_newOrderSpecs[0] = l_orderSpec;

            TestDBUtility.deleteAll(TpCalcResultMarginParams.TYPE);
            TpCalcResultMarginParams l_tpCalcResultMarginParams = TestDBUtility.getTpCalcResultMarginRow();
            TestDBUtility.insertWithDel(l_tpCalcResultMarginParams);
            TestDBUtility.deleteAll(TpCalcResultMarginDetailParams.TYPE);
            TpCalcResultMarginDetailParams l_tpCalcResultMarginDetailParams =
                TestDBUtility.getTpCalcResultMarginDetailRow();
            TestDBUtility.insertWithDel(l_tpCalcResultMarginDetailParams);
            
            WEB3TPTradingPowerResult l_tpResult = this.l_serviceImpl.validateTradingPowerFeqBuy(l_subAccount,l_newOrderSpecs,true);
            
            assertNull(l_tpResult.getAttentionObjectionType());
        }
        catch (Exception l_ex)
        {
            log.error("", l_ex);
            log.exiting(TEST_END + STR_METHOD_NAME);
            fail();
        }
        log.exiting(TEST_END + STR_METHOD_NAME);
    }

    public void testvalidateTradingPowerFeqBuy_C0008()
    {
        final String STR_METHOD_NAME = "testvalidateTradingPowerFeqBuy_C0008";
        log.entering(TEST_START + STR_METHOD_NAME);
        try
        {
            TestDBUtility.deleteAll(EqtypeOrderUnitRow.TYPE);
            EqtypeOrderUnitParams l_eqtypeOrderUnitParams =TestDBUtility.getEqtypeOrderUnitRow();
            l_eqtypeOrderUnitParams.setBizDate("20070917");
            l_eqtypeOrderUnitParams.setOrderType(OrderTypeEnum.MARGIN_LONG);
            l_eqtypeOrderUnitParams.setDeliveryDate(WEB3DateUtility.getDate("20070917", "yyyyMMdd"));
            TestDBUtility.insertWithDel(l_eqtypeOrderUnitParams);

            TestDBUtility.deleteAll(EqtypeTradedProductUpdqParams.TYPE);
            EqtypeTradedProductUpdqParams l_eqtypeTradedProductUpdqParams =
                TestDBUtility.getEqtypeTradedProductUpdqRow();
            l_eqtypeTradedProductUpdqParams.setProductId(3304148080001L);
            l_eqtypeTradedProductUpdqParams.setMarketId(3303L);
            l_eqtypeTradedProductUpdqParams.setValidUntilBizDate("20070917");
            TestDBUtility.insertWithDel(l_eqtypeTradedProductUpdqParams);

            TestDBUtility.deleteAll(SubAccountRow.TYPE);
            SubAccountParams l_subAccountParams = TestDBUtility.getSubAccountRow();
            l_subAccountParams.setSubAccountType(SubAccountTypeEnum.EQUITY_MARGIN_SUB_ACCOUNT);
            TestDBUtility.insertWithDel(l_subAccountParams);

            SubAccountParams l_subAccountParams1 = TestDBUtility.getSubAccountRow();
            l_subAccountParams1.setSubAccountId(1);
            l_subAccountParams1.setSubAccountType(SubAccountTypeEnum.EQUITY_SUB_ACCOUNT);
            TestDBUtility.insertWithDel(l_subAccountParams1);

            TestDBUtility.deleteAll(MainAccountRow.TYPE);
            MainAccountParams l_mainAccountParams =TestDBUtility.getMainAccountRow();
            l_mainAccountParams.setAccountId(l_subAccountParams.getAccountId());
            l_mainAccountParams.setMarginGenAccOpenDiv(WEB3MarginAccountOpenDivDef.MARGIN_ACCOUNT_OPEN);
            TestDBUtility.insertWithDel(l_mainAccountParams);

            TestDBUtility.deleteAll(BranchRow.TYPE);
            BranchParams l_branchParams = TestDBUtility.getBranchRow();
            l_branchParams.setBranchId(33381);
            TestDBUtility.insertWithDel(l_branchParams);
            
            // TradingpowerCalcConditionRow
            TestDBUtility.deleteAll(TradingpowerCalcConditionRow.TYPE);
            TradingpowerCalcConditionParams l_tradingpowerCalcConditionParam = TestDBUtility.getTradingpowerCalcConditionRow();
            l_tradingpowerCalcConditionParam.setAccountId(333812512203L);
            l_tradingpowerCalcConditionParam.setBranchId(33381);
            l_tradingpowerCalcConditionParam.setSpecialDebitAmount(-20);
            l_tradingpowerCalcConditionParam.setTradingStop("0");
            l_tradingpowerCalcConditionParam.setAdditionalDepositStop("0");
            l_tradingpowerCalcConditionParam.setOtherTradingStop("0");
            TestDBUtility.insertWithDel(l_tradingpowerCalcConditionParam);
            
            //TpCashBalance
            TpCashBalanceParams l_tpCashBalance = new TpCashBalanceParams();
            l_tpCashBalance.setAccountId(333812512203L);
            l_tpCashBalance.setSubAccountId(33381251220301L);
            l_tpCashBalance.setCashBalance2(-2.0d);
            l_tpCashBalance.setCashBalance5(2.0d);
            TestDBUtility.insertWithDel(l_tpCashBalance);

            this.isAssetEvaluation = true;
            Calendar l_calendar = Calendar.getInstance();
            l_calendar.set(Calendar.YEAR,2007);
            l_calendar.set(Calendar.MONTH,8);
            l_calendar.set(Calendar.DAY_OF_MONTH,28);
            l_calendar.set(Calendar.HOUR_OF_DAY,00);
            l_calendar.set(Calendar.MINUTE,00);
            l_calendar.set(Calendar.SECOND,00);

            WEB3GentradeSubAccount l_subAccount = new WEB3GentradeSubAccount(l_subAccountParams);
            WEB3TPNewOrderSpec[] l_newOrderSpecs = new WEB3TPNewOrderSpec[1];
            WEB3TPNewOrderSpec l_orderSpec = new WEB3TPNewOrderSpec();
            l_orderSpec.setSubAccountId(333812512203L);
            l_orderSpec.setDeliveryDate(l_calendar.getTime());
            l_orderSpec.setProductId(123456L);
            l_orderSpec.setMarketId(3303L);
            l_orderSpec.setOrderBizDate(WEB3DateUtility.getDate("20071002", "yyyyMMdd"));
            l_orderSpec.setDeliveryDate(WEB3DateUtility.getDate("20071002", "yyyyMMdd"));
            l_newOrderSpecs[0] = l_orderSpec;

            TestDBUtility.deleteAll(TpCalcResultMarginParams.TYPE);
            TpCalcResultMarginParams l_tpCalcResultMarginParams = TestDBUtility.getTpCalcResultMarginRow();
            TestDBUtility.insertWithDel(l_tpCalcResultMarginParams);
            TestDBUtility.deleteAll(TpCalcResultMarginDetailParams.TYPE);
            TpCalcResultMarginDetailParams l_tpCalcResultMarginDetailParams =
                TestDBUtility.getTpCalcResultMarginDetailRow();
            TestDBUtility.insertWithDel(l_tpCalcResultMarginDetailParams);
            
            WEB3TPTradingPowerResult l_tpResult = this.l_serviceImpl.validateTradingPowerFeqBuy(l_subAccount,l_newOrderSpecs,true);
            
            assertNull(l_tpResult.getAttentionObjectionType());
        }
        catch (Exception l_ex)
        {
            log.error("", l_ex);
            log.exiting(TEST_END + STR_METHOD_NAME);
            fail();
        }
        log.exiting(TEST_END + STR_METHOD_NAME);
    }

    public void testValidateTradingPowerEquitySell_C0001()
    {
        final String STR_METHOD_NAME = "testValidateTradingPowerEquitySell_C0001";
        log.entering(TEST_START + STR_METHOD_NAME);
        try
        {
            this.isAssetEvaluation = true;
            Calendar l_calendar = Calendar.getInstance();
            l_calendar.set(Calendar.YEAR,2007);
            l_calendar.set(Calendar.MONTH,8);
            l_calendar.set(Calendar.DAY_OF_MONTH,28);
            l_calendar.set(Calendar.HOUR_OF_DAY,00);
            l_calendar.set(Calendar.MINUTE,00);
            l_calendar.set(Calendar.SECOND,00);
            SubAccountParams l_subAccountParams = TestDBUtility.getSubAccountRow();
            l_subAccountParams.setSubAccountType(SubAccountTypeEnum.EQUITY_SUB_ACCOUNT);
            WEB3GentradeSubAccount l_subAccount = new WEB3GentradeSubAccountForTest(l_subAccountParams);
            WEB3TPNewOrderSpec[] l_newOrderSpecs = new WEB3TPNewOrderSpec[1];
            WEB3TPNewOrderSpec l_orderSpec = new WEB3TPNewOrderSpec();
            l_orderSpec.setSubAccountId(333812512203L);
            l_orderSpec.setDeliveryDate(l_calendar.getTime());
            l_orderSpec.setProductId(123456L);
            l_orderSpec.setMarketId(3303L);
            l_newOrderSpecs[0] = l_orderSpec;
            
            this.l_serviceImpl.validateTradingPowerEquitySell(l_subAccount,l_newOrderSpecs,true);
            
           // assertEquals(2000,l_tpResult.getTpErrorInfo().sellOrderPossibleQuantity,0);
        }
        catch (Exception l_ex)
        {
            log.error("", l_ex);
            log.exiting(TEST_END + STR_METHOD_NAME);
            fail();
        }
        log.exiting(TEST_END + STR_METHOD_NAME);
    }
    
    /**
     * 「営業部店」の場合
     */
    public void testValidateTradingPowerEquitySell_C0002()
    {
        final String STR_METHOD_NAME = "testValidateTradingPowerEquitySell_C0002";
        log.entering(TEST_START + STR_METHOD_NAME);
        try
        {
            TestDBUtility.deleteAll(TpCalcResultEquityRow.TYPE);
            // MainAccountDao
            TestDBUtility.deleteAll(MainAccountRow.TYPE);
            MainAccountParams l_mainAccountParams =TestDBUtility.getMainAccountRow();
            l_mainAccountParams.setAccountId(333812512203L);
            TestDBUtility.insertWithDel(l_mainAccountParams);
            
            TestDBUtility.deleteAll(BranchRow.TYPE);
            BranchParams l_branchParams = TestDBUtility.getBranchRow();
            l_branchParams.setBranchId(33381);
            //「営業部店」の場合
            l_branchParams.setBranchType(BranchTypeEnum.REGULAR_LOCAL_BRANCH);
            TestDBUtility.insertWithDel(l_branchParams);
            
            // TradingpowerCalcConditionRow
            TestDBUtility.deleteAll(TradingpowerCalcConditionRow.TYPE);
            TradingpowerCalcConditionParams l_tradingpowerCalcConditionParam = TestDBUtility.getTradingpowerCalcConditionRow();
            l_tradingpowerCalcConditionParam.setAccountId(333812512203L);
            l_tradingpowerCalcConditionParam.setBranchId(33381);
            l_tradingpowerCalcConditionParam.setSpecialDebitAmount(-20);
            TestDBUtility.insertWithDel(l_tradingpowerCalcConditionParam);
            
            this.isAssetEvaluation = true;
            Calendar l_calendar = Calendar.getInstance();
            l_calendar.set(Calendar.YEAR,2007);
            l_calendar.set(Calendar.MONTH,8);
            l_calendar.set(Calendar.DAY_OF_MONTH,28);
            l_calendar.set(Calendar.HOUR_OF_DAY,00);
            l_calendar.set(Calendar.MINUTE,00);
            l_calendar.set(Calendar.SECOND,00);
            SubAccountParams l_subAccountParams = TestDBUtility.getSubAccountRow();
            l_subAccountParams.setSubAccountType(SubAccountTypeEnum.EQUITY_SUB_ACCOUNT);
            WEB3GentradeSubAccount l_subAccount = new WEB3GentradeSubAccountForTest(l_subAccountParams);
            WEB3TPNewOrderSpec[] l_newOrderSpecs = new WEB3TPNewOrderSpec[1];
            WEB3TPNewOrderSpec l_orderSpec = new WEB3TPNewOrderSpec();
            l_orderSpec.setSubAccountId(333812512203L);
            l_orderSpec.setDeliveryDate(l_calendar.getTime());
            l_orderSpec.setProductId(123456L);
            l_orderSpec.setMarketId(3303L);
            l_newOrderSpecs[0] = l_orderSpec;
            
            WEB3TPTradingPowerResult l_tpResult = this.l_serviceImpl.validateTradingPowerEquitySell(l_subAccount,l_newOrderSpecs,true);
            
            assertNull(l_tpResult.getAttentionObjectionType());
        }
        catch (Exception l_ex)
        {
            log.error("", l_ex);
            log.exiting(TEST_END + STR_METHOD_NAME);
            fail();
        }
        log.exiting(TEST_END + STR_METHOD_NAME);
    }
    
    /**
     *「（注文後）引出可能現金(T+受渡日) > 0」
     */
    public void testValidateTradingPowerEquitySell_C0003()
    {
        final String STR_METHOD_NAME = "testValidateTradingPowerEquitySell_C0003";
        log.entering(TEST_START + STR_METHOD_NAME);
        try
        {
            TestDBUtility.deleteAll(BranchRow.TYPE);
            BranchParams l_branchParams = TestDBUtility.getBranchRow();
            l_branchParams.setBranchId(33381);
            //「営業部店」の場合
//            l_branchParams.setBranchType(BranchTypeEnum.REGULAR_LOCAL_BRANCH);
            TestDBUtility.insertWithDel(l_branchParams);
            
            // TradingpowerCalcConditionRow
            TestDBUtility.deleteAll(TradingpowerCalcConditionRow.TYPE);
            TradingpowerCalcConditionParams l_tradingpowerCalcConditionParam = TestDBUtility.getTradingpowerCalcConditionRow();
            l_tradingpowerCalcConditionParam.setAccountId(333812512203L);
            l_tradingpowerCalcConditionParam.setBranchId(33381);
            l_tradingpowerCalcConditionParam.setSpecialDebitAmount(-20);
            TestDBUtility.insertWithDel(l_tradingpowerCalcConditionParam);
            
            //TpCashBalance
            TpCashBalanceParams l_tpCashBalance = new TpCashBalanceParams();
            l_tpCashBalance.setAccountId(333812512203L);
            l_tpCashBalance.setSubAccountId(33381251220301L);
            l_tpCashBalance.setCashBalance2(-2.0d);
            l_tpCashBalance.setCashBalance5(2.0d);
            TestDBUtility.insertWithDel(l_tpCashBalance);

            this.isAssetEvaluation = true;
            Calendar l_calendar = Calendar.getInstance();
            l_calendar.set(Calendar.YEAR,2007);
            l_calendar.set(Calendar.MONTH,8);
            l_calendar.set(Calendar.DAY_OF_MONTH,28);
            l_calendar.set(Calendar.HOUR_OF_DAY,00);
            l_calendar.set(Calendar.MINUTE,00);
            l_calendar.set(Calendar.SECOND,00);
            SubAccountParams l_subAccountParams = TestDBUtility.getSubAccountRow();
            l_subAccountParams.setSubAccountType(SubAccountTypeEnum.EQUITY_SUB_ACCOUNT);
            WEB3GentradeSubAccount l_subAccount = new WEB3GentradeSubAccountForTest(l_subAccountParams);
            WEB3TPNewOrderSpec[] l_newOrderSpecs = new WEB3TPNewOrderSpec[1];
            WEB3TPNewOrderSpec l_orderSpec = new WEB3TPNewOrderSpec();
            l_orderSpec.setSubAccountId(333812512203L);
            l_orderSpec.setDeliveryDate(l_calendar.getTime());
            l_orderSpec.setProductId(123456L);
            l_orderSpec.setMarketId(3303L);
            l_orderSpec.setDeliveryDate(WEB3DateUtility.getDate("20071002", "yyyyMMdd"));
            l_newOrderSpecs[0] = l_orderSpec;
            
            WEB3TPTradingPowerResult l_tpResult = this.l_serviceImpl.validateTradingPowerEquitySell(l_subAccount,l_newOrderSpecs,true);
            
            assertNull(l_tpResult.getAttentionObjectionType());
        }
        catch (Exception l_ex)
        {
            log.error("", l_ex);
            log.exiting(TEST_END + STR_METHOD_NAME);
            fail();
        }
        log.exiting(TEST_END + STR_METHOD_NAME);
    }
    
    /**
     *「（注文後）引出可能現金(T+受渡日) = 0」
     */
    public void estValidateTradingPowerEquitySell_C0004()
    {
        final String STR_METHOD_NAME = "testValidateTradingPowerEquitySell_C0004";
        log.entering(TEST_START + STR_METHOD_NAME);
        try
        {
            TestDBUtility.deleteAll(BranchRow.TYPE);
            BranchParams l_branchParams = TestDBUtility.getBranchRow();
            l_branchParams.setBranchId(33381);
            //「営業部店」の場合
//            l_branchParams.setBranchType(BranchTypeEnum.REGULAR_LOCAL_BRANCH);
            TestDBUtility.insertWithDel(l_branchParams);
            
            // TradingpowerCalcConditionRow
            TestDBUtility.deleteAll(TradingpowerCalcConditionRow.TYPE);
            TradingpowerCalcConditionParams l_tradingpowerCalcConditionParam = TestDBUtility.getTradingpowerCalcConditionRow();
            l_tradingpowerCalcConditionParam.setAccountId(333812512203L);
            l_tradingpowerCalcConditionParam.setBranchId(33381);
            l_tradingpowerCalcConditionParam.setSpecialDebitAmount(-20);
            TestDBUtility.insertWithDel(l_tradingpowerCalcConditionParam);
            
            this.isAssetEvaluation = true;
            Calendar l_calendar = Calendar.getInstance();
            l_calendar.set(Calendar.YEAR,2007);
            l_calendar.set(Calendar.MONTH,8);
            l_calendar.set(Calendar.DAY_OF_MONTH,28);
            l_calendar.set(Calendar.HOUR_OF_DAY,00);
            l_calendar.set(Calendar.MINUTE,00);
            l_calendar.set(Calendar.SECOND,00);
            SubAccountParams l_subAccountParams = TestDBUtility.getSubAccountRow();
            l_subAccountParams.setSubAccountType(SubAccountTypeEnum.EQUITY_SUB_ACCOUNT);
            WEB3GentradeSubAccount l_subAccount = new WEB3GentradeSubAccountForTest(l_subAccountParams);
            WEB3TPNewOrderSpec[] l_newOrderSpecs = new WEB3TPNewOrderSpec[1];
            WEB3TPNewOrderSpec l_orderSpec = new WEB3TPNewOrderSpec();
            l_orderSpec.setSubAccountId(333812512203L);
            l_orderSpec.setDeliveryDate(l_calendar.getTime());
            l_orderSpec.setProductId(123456L);
            l_orderSpec.setMarketId(3303L);
            l_orderSpec.setDeliveryDate(WEB3DateUtility.getDate("20071001", "yyyyMMdd"));
            l_newOrderSpecs[0] = l_orderSpec;
            
            WEB3TPTradingPowerResult l_tpResult = this.l_serviceImpl.validateTradingPowerEquitySell(l_subAccount,l_newOrderSpecs,true);
            
            assertNull(l_tpResult.getAttentionObjectionType());
        }
        catch (Exception l_ex)
        {
            log.error("", l_ex);
            log.exiting(TEST_END + STR_METHOD_NAME);
            fail();
        }
        log.exiting(TEST_END + STR_METHOD_NAME);
    }
    
    /**
     *「（注文後）引出可能現金(T+受渡日) > （注文前）引出可能現金(T+受渡日)」　@の場合
     */
    public void estValidateTradingPowerEquitySell_C0005()
    {
        final String STR_METHOD_NAME = "testValidateTradingPowerEquitySell_C0005";
        log.entering(TEST_START + STR_METHOD_NAME);
        try
        {
            TestDBUtility.deleteAll(BranchRow.TYPE);
            BranchParams l_branchParams = TestDBUtility.getBranchRow();
            l_branchParams.setBranchId(33381);
            //「営業部店」の場合
//            l_branchParams.setBranchType(BranchTypeEnum.REGULAR_LOCAL_BRANCH);
            TestDBUtility.insertWithDel(l_branchParams);
            
            // TradingpowerCalcConditionRow
            TestDBUtility.deleteAll(TradingpowerCalcConditionRow.TYPE);
            TradingpowerCalcConditionParams l_tradingpowerCalcConditionParam = TestDBUtility.getTradingpowerCalcConditionRow();
            l_tradingpowerCalcConditionParam.setAccountId(333812512203L);
            l_tradingpowerCalcConditionParam.setBranchId(33381);
            l_tradingpowerCalcConditionParam.setSpecialDebitAmount(-20);
            TestDBUtility.insertWithDel(l_tradingpowerCalcConditionParam);
            
            this.isAssetEvaluation = true;
            Calendar l_calendar = Calendar.getInstance();
            l_calendar.set(Calendar.YEAR,2007);
            l_calendar.set(Calendar.MONTH,8);
            l_calendar.set(Calendar.DAY_OF_MONTH,28);
            l_calendar.set(Calendar.HOUR_OF_DAY,00);
            l_calendar.set(Calendar.MINUTE,00);
            l_calendar.set(Calendar.SECOND,00);
            SubAccountParams l_subAccountParams = TestDBUtility.getSubAccountRow();
            l_subAccountParams.setSubAccountType(SubAccountTypeEnum.EQUITY_SUB_ACCOUNT);
            WEB3GentradeSubAccount l_subAccount = new WEB3GentradeSubAccountForTest(l_subAccountParams);
            WEB3TPNewOrderSpec[] l_newOrderSpecs = new WEB3TPNewOrderSpec[1];
            WEB3TPNewOrderSpec l_orderSpec = new WEB3TPNewOrderSpec();
            l_orderSpec.setSubAccountId(333812512203L);
            l_orderSpec.setDeliveryDate(l_calendar.getTime());
            l_orderSpec.setProductId(123456L);
            l_orderSpec.setMarketId(3303L);
            l_orderSpec.setDeliveryDate(WEB3DateUtility.getDate("20071004", "yyyyMMdd"));
            l_newOrderSpecs[0] = l_orderSpec;
            
            WEB3TPTradingPowerResult l_tpResult = this.l_serviceImpl.validateTradingPowerEquitySell(l_subAccount,l_newOrderSpecs,true);
            
            assertNull(l_tpResult.getAttentionObjectionType());
        }
        catch (Exception l_ex)
        {
            log.error("", l_ex);
            log.exiting(TEST_END + STR_METHOD_NAME);
            fail();
        }
        log.exiting(TEST_END + STR_METHOD_NAME);
    }
    
    /**
     * 差金決済相当外買付代金非考慮の(注文後)引出可能現金(T+受渡日) < 0 
     */
    public void estValidateTradingPowerEquitySell_C0006()
    {
        final String STR_METHOD_NAME = "testValidateTradingPowerEquitySell_C0006";
        log.entering(TEST_START + STR_METHOD_NAME);
        try
        {
            this.isAssetEvaluation = true;
            Calendar l_calendar = Calendar.getInstance();
            l_calendar.set(Calendar.YEAR,2007);
            l_calendar.set(Calendar.MONTH,8);
            l_calendar.set(Calendar.DAY_OF_MONTH,28);
            l_calendar.set(Calendar.HOUR_OF_DAY,00);
            l_calendar.set(Calendar.MINUTE,00);
            l_calendar.set(Calendar.SECOND,00);
            SubAccountParams l_subAccountParams = TestDBUtility.getSubAccountRow();
            l_subAccountParams.setSubAccountType(SubAccountTypeEnum.EQUITY_SUB_ACCOUNT);
            WEB3GentradeSubAccount l_subAccount = new WEB3GentradeSubAccountForTest(l_subAccountParams);
            WEB3TPNewOrderSpec[] l_newOrderSpecs = new WEB3TPNewOrderSpec[1];
            WEB3TPNewOrderSpec l_orderSpec = new WEB3TPNewOrderSpec();
            l_orderSpec.setSubAccountId(333812512203L);
            l_orderSpec.setDeliveryDate(l_calendar.getTime());
            l_orderSpec.setProductId(123456L);
            l_orderSpec.setMarketId(3303L);
            l_orderSpec.setDeliveryDate(WEB3DateUtility.getDate("20071005", "yyyyMMdd"));
            l_newOrderSpecs[0] = l_orderSpec;
            
            WEB3TPTradingPowerResult l_tpResult = this.l_serviceImpl.validateTradingPowerEquitySell(l_subAccount,l_newOrderSpecs,true);
            
            assertEquals(2000,l_tpResult.getTpErrorInfo().sellOrderPossibleQuantity,0);
        }
        catch (Exception l_ex)
        {
            log.error("", l_ex);
            log.exiting(TEST_END + STR_METHOD_NAME);
            fail();
        }
        log.exiting(TEST_END + STR_METHOD_NAME);
    }
    
    public void estValidateTradingPowerEquitySell_C0007()
    {
        final String STR_METHOD_NAME = "testValidateTradingPowerEquitySell_C0007";
        log.entering(TEST_START + STR_METHOD_NAME);
        try
        {
            
            this.isAssetEvaluation = true;
            Calendar l_calendar = Calendar.getInstance();
            l_calendar.set(Calendar.YEAR,2007);
            l_calendar.set(Calendar.MONTH,8);
            l_calendar.set(Calendar.DAY_OF_MONTH,28);
            l_calendar.set(Calendar.HOUR_OF_DAY,00);
            l_calendar.set(Calendar.MINUTE,00);
            l_calendar.set(Calendar.SECOND,00);
            SubAccountParams l_subAccountParams = TestDBUtility.getSubAccountRow();
            l_subAccountParams.setSubAccountType(SubAccountTypeEnum.EQUITY_SUB_ACCOUNT);
            WEB3GentradeSubAccount l_subAccount = new WEB3GentradeSubAccountForTest(l_subAccountParams);
            WEB3TPNewOrderSpec[] l_newOrderSpecs = new WEB3TPNewOrderSpec[1];
            WEB3TPNewOrderSpec l_orderSpec = new WEB3TPNewOrderSpec();
            l_orderSpec.setSubAccountId(333812512203L);
            l_orderSpec.setDeliveryDate(l_calendar.getTime());
            l_orderSpec.setProductId(123456L);
            l_orderSpec.setMarketId(3303L);
            l_newOrderSpecs[0] = l_orderSpec;
            
            WEB3TPTradingPowerResult l_tpResult = this.l_serviceImpl.validateTradingPowerEquitySell(l_subAccount,l_newOrderSpecs,true);
            
            assertEquals(12.0,l_tpResult.getTpErrorInfo().lackAccountBalance,0);
        }
        catch (Exception l_ex)
        {
            log.error("", l_ex);
            log.exiting(TEST_END + STR_METHOD_NAME);
            fail();
        }
        log.exiting(TEST_END + STR_METHOD_NAME);
    }
    
    public void estValidateTradingPowerEquitySell_C0008()
    {
        final String STR_METHOD_NAME = "testValidateTradingPowerEquitySell_C0008";
        log.entering(TEST_START + STR_METHOD_NAME);
        try
        {
            this.isAssetEvaluation = true;
            Calendar l_calendar = Calendar.getInstance();
            l_calendar.set(Calendar.YEAR,2007);
            l_calendar.set(Calendar.MONTH,8);
            l_calendar.set(Calendar.DAY_OF_MONTH,28);
            l_calendar.set(Calendar.HOUR_OF_DAY,00);
            l_calendar.set(Calendar.MINUTE,00);
            l_calendar.set(Calendar.SECOND,00);
            SubAccountParams l_subAccountParams = TestDBUtility.getSubAccountRow();
            l_subAccountParams.setSubAccountType(SubAccountTypeEnum.EQUITY_SUB_ACCOUNT);
            WEB3GentradeSubAccount l_subAccount = new WEB3GentradeSubAccountForTest(l_subAccountParams);
            WEB3TPNewOrderSpec[] l_newOrderSpecs = new WEB3TPNewOrderSpec[1];
            WEB3TPNewOrderSpec l_orderSpec = new WEB3TPNewOrderSpec();
            l_orderSpec.setSubAccountId(333812512203L);
            l_orderSpec.setDeliveryDate(WEB3DateUtility.getDate("20071005", "yyyyMMdd"));
            l_orderSpec.setProductId(123456L);
            l_orderSpec.setMarketId(3303L);
            l_newOrderSpecs[0] = l_orderSpec;
            
            WEB3TPTradingPowerResult l_tpResult = this.l_serviceImpl.validateTradingPowerEquitySell(l_subAccount,l_newOrderSpecs,true);
            
            assertEquals(12.0,l_tpResult.getTpErrorInfo().lackAccountBalance,0);
        }
        catch (Exception l_ex)
        {
            log.error("", l_ex);
            log.exiting(TEST_END + STR_METHOD_NAME);
            fail();
        }
        log.exiting(TEST_END + STR_METHOD_NAME);
    }

    public void estValidateTradingPowerEquitySell_C0009()
    {
        final String STR_METHOD_NAME = "testValidateTradingPowerEquitySell_C0009";
        log.entering(TEST_START + STR_METHOD_NAME);
        try
        {
//            this.isAssetEvaluation = true;
//            Calendar l_calendar = Calendar.getInstance();
//            l_calendar.set(Calendar.YEAR,2007);
//            l_calendar.set(Calendar.MONTH,8);
//            l_calendar.set(Calendar.DAY_OF_MONTH,28);
//            l_calendar.set(Calendar.HOUR_OF_DAY,00);
//            l_calendar.set(Calendar.MINUTE,00);
//            l_calendar.set(Calendar.SECOND,00);
            InstitutionParams l_institutionParams = TestDBUtility.getInstitutionRow();
            TestDBUtility.insertWithDel(l_institutionParams);

            SubAccountParams l_subAccountParams = TestDBUtility.getSubAccountRow();
            l_subAccountParams.setSubAccountType(SubAccountTypeEnum.EQUITY_SUB_ACCOUNT);
            WEB3GentradeSubAccount l_subAccount = new WEB3GentradeSubAccountForTest(l_subAccountParams);
            WEB3TPNewOrderSpec[] l_newOrderSpecs = new WEB3TPNewOrderSpec[1];
            WEB3TPNewOrderSpec l_orderSpec = new WEB3TPNewOrderSpec();
            l_orderSpec.setSubAccountId(333812512203L);
            l_orderSpec.setDeliveryDate(WEB3DateUtility.getDate("20071005", "yyyyMMdd"));
            l_orderSpec.setProductId(123456L);
            l_orderSpec.setMarketId(3303L);
            l_newOrderSpecs[0] = l_orderSpec;
            
            WEB3TPTradingPowerResult l_tpResult = this.l_serviceImpl.validateTradingPowerEquitySell(l_subAccount,l_newOrderSpecs,true);
            
            assertEquals(12.0,l_tpResult.getTpErrorInfo().lackAccountBalance,0);
        }
        catch (Exception l_ex)
        {
            log.error("", l_ex);
            log.exiting(TEST_END + STR_METHOD_NAME);
            fail();
        }
        log.exiting(TEST_END + STR_METHOD_NAME);
    }

    private void initData()
    {
        final String STR_METHOD_NAME = "initData()";
        log.entering(TEST_START + STR_METHOD_NAME);
        try
        {
            Calendar l_calendar = Calendar.getInstance();
            l_calendar.set(Calendar.YEAR,2007);
            l_calendar.set(Calendar.MONTH,8);
            l_calendar.set(Calendar.DAY_OF_MONTH,28);
            l_calendar.set(Calendar.HOUR_OF_DAY,00);
            l_calendar.set(Calendar.MINUTE,00);
            l_calendar.set(Calendar.SECOND,00);

            Timestamp l_tsBizDate = new Timestamp(l_calendar.getTimeInMillis());

            ThreadLocalSystemAttributesRegistry.setAttribute(
                WEB3GentradeTradingTimeManagement.TIMESTAMP_TAG, l_tsBizDate);
            
            ThreadLocalSystemAttributesRegistry.setAttribute(
                WEB3GentradeTradingTimeManagement.BASE_TIMESTAMP_FOR_ORDER_BIZ_DATE,l_tsBizDate);
            
            WEB3GentradeTradingClendarContext l_tmpClendarContext = new WEB3GentradeTradingClendarContext();
            l_tmpClendarContext.setInstitutionCode("0D");
            l_tmpClendarContext.setBranchCode("381");
            l_tmpClendarContext.setMarketCode("0");
            l_tmpClendarContext.setTradingTimeType("01");
            l_tmpClendarContext.setProductCode("0");
            l_tmpClendarContext.setBizDateType("1");
            
            WEB3GentradeTradingTimeManagementForMock.setClendarContext(l_tmpClendarContext);
//            WEB3GentradeTradingTimeManagementForMock.mockGetOrderBizDate(new Date());
            
            TestDBUtility.deleteAll(TradingTimeRow.TYPE);
            TradingTimeParams l_tradingTimeParams1 = TestDBUtility.getTradingTimeRow();
            l_tradingTimeParams1.setInstitutionCode("0D");
            l_tradingTimeParams1.setBranchCode("381");
            l_tradingTimeParams1.setMarketCode("0");
            l_tradingTimeParams1.setTradingTimeType("01");
            l_tradingTimeParams1.setProductCode("0");
            l_tradingTimeParams1.setBizDateType("1");
            
            TestDBUtility.insertWithDel(l_tradingTimeParams1);
            
            TradingTimeParams l_tradingTimeParams2 = TestDBUtility.getTradingTimeRow();
            l_tradingTimeParams2.setInstitutionCode("0D");
            l_tradingTimeParams2.setBranchCode("381");
            l_tradingTimeParams2.setMarketCode("0");
            l_tradingTimeParams2.setTradingTimeType("11");
            l_tradingTimeParams2.setProductCode("0");
            l_tradingTimeParams2.setBizDateType("1");
            TestDBUtility.insertWithDel(l_tradingTimeParams2);
            
            TradingTimeParams l_tradingTimeParams3 = TestDBUtility.getTradingTimeRow();
            l_tradingTimeParams3.setInstitutionCode("0D");
            l_tradingTimeParams3.setBranchCode("381");
            l_tradingTimeParams3.setMarketCode("0");
            l_tradingTimeParams3.setTradingTimeType("06");
            l_tradingTimeParams3.setProductCode("0");
            l_tradingTimeParams3.setBizDateType("1");
            TestDBUtility.insertWithDel(l_tradingTimeParams3);
            
            TradingTimeParams l_tradingTimeParams4 = TestDBUtility.getTradingTimeRow();
            l_tradingTimeParams4.setInstitutionCode("0D");
            l_tradingTimeParams4.setBranchCode("381");
            l_tradingTimeParams4.setMarketCode("0");
            l_tradingTimeParams4.setTradingTimeType("16");
            l_tradingTimeParams4.setProductCode("0");
            l_tradingTimeParams4.setBizDateType("1");
            TestDBUtility.insertWithDel(l_tradingTimeParams4);
            
            TradingTimeParams l_tradingTimeParams5 = TestDBUtility.getTradingTimeRow();
            l_tradingTimeParams5.setInstitutionCode("0D");
            l_tradingTimeParams5.setBranchCode("381");
            l_tradingTimeParams5.setMarketCode("0");
            l_tradingTimeParams5.setTradingTimeType("05");
            l_tradingTimeParams5.setProductCode("0");
            l_tradingTimeParams5.setBizDateType("1");
            TestDBUtility.insertWithDel(l_tradingTimeParams5);
            
            TradingTimeParams l_tradingTimeParams6 = TestDBUtility.getTradingTimeRow();
            l_tradingTimeParams6.setInstitutionCode("0D");
            l_tradingTimeParams6.setBranchCode("381");
            l_tradingTimeParams6.setMarketCode("0");
            l_tradingTimeParams6.setTradingTimeType("07");
            l_tradingTimeParams6.setProductCode("0");
            l_tradingTimeParams6.setBizDateType("1");
            TestDBUtility.insertWithDel(l_tradingTimeParams6);
            
            TradingTimeParams l_tradingTimeParams7 = TestDBUtility.getTradingTimeRow();
            l_tradingTimeParams7.setInstitutionCode("0D");
            l_tradingTimeParams7.setBranchCode("381");
            l_tradingTimeParams7.setMarketCode("0");
            l_tradingTimeParams7.setTradingTimeType("10");
            l_tradingTimeParams7.setProductCode("0");
            l_tradingTimeParams7.setBizDateType("1");
            TestDBUtility.insertWithDel(l_tradingTimeParams7);
            
            // TradingpowerCalcConditionRow
            TestDBUtility.deleteAll(TradingpowerCalcConditionRow.TYPE);
            TradingpowerCalcConditionParams l_tradingpowerCalcConditionParam = TestDBUtility.getTradingpowerCalcConditionRow();
            l_tradingpowerCalcConditionParam.setAccountId(333812512203L);
            l_tradingpowerCalcConditionParam.setBranchId(33381);
            TestDBUtility.insertWithDel(l_tradingpowerCalcConditionParam);
            
            // EqtypeOrderUnitRow
            TestDBUtility.deleteAll(EqtypeOrderUnitRow.TYPE);
            EqtypeOrderUnitParams l_eqtypeOrderUnitParams =TestDBUtility.getEqtypeOrderUnitRow();
            l_eqtypeOrderUnitParams.setBizDate("20190117");
            TestDBUtility.insertWithDel(l_eqtypeOrderUnitParams);
            
            // MainAccountDao
            TestDBUtility.deleteAll(MainAccountRow.TYPE);
            MainAccountParams l_mainAccountParams =TestDBUtility.getMainAccountRow();
            l_mainAccountParams.setAccountId(333812512203L);
            TestDBUtility.insertWithDel(l_mainAccountParams);
            
            TestDBUtility.deleteAll(BranchRow.TYPE);
            BranchParams l_branchParams = TestDBUtility.getBranchRow();
            l_branchParams.setBranchId(33381);
            TestDBUtility.insertWithDel(l_branchParams);
            
            // SubAccountRow
            TestDBUtility.deleteAll(SubAccountRow.TYPE);
            SubAccountParams l_subAccountParams = TestDBUtility.getSubAccountRow();
            l_subAccountParams.setSubAccountType(SubAccountTypeEnum.EQUITY_SUB_ACCOUNT);
            TestDBUtility.insertWithDel(l_subAccountParams);
            
            // IfoOrderUnitRow.TYPE
            
            // ProductDao
            TestDBUtility.deleteAll(ProductRow.TYPE);
            ProductParams l_productParams = TestDBUtility.getProductRow();
            l_productParams.setProductId(123456L);
            l_productParams.setProductType(ProductTypeEnum.EQUITY);
            TestDBUtility.insertWithDel(l_productParams);
            
            TestDBUtility.deleteAll(EqtypeProductRow.TYPE);
            EqtypeProductParams l_eqtypeProductParams = TestDBUtility.getEqtypeProductRow();
            l_eqtypeProductParams.setProductId(123456L);
            TestDBUtility.insertWithDel(l_eqtypeProductParams);
            
            TestDBUtility.deleteAll(EqtypeTradedProductRow.TYPE);
            EqtypeTradedProductParams l_eqtypeTradedProductParams = TestDBUtility.getEqtypeTradedProductRow();
            l_eqtypeTradedProductParams.setTradedProductId(1006160060005L);
            l_eqtypeTradedProductParams.setProductId(123456L);
            TestDBUtility.insertWithDel(l_eqtypeTradedProductParams);
            
            TestDBUtility.deleteAll(TradedProductRow.TYPE);
            TradedProductParams l_tradedProductParams = TestDBUtility.getTradedProductRow();
            l_tradedProductParams.setTradedProductId(1006160060005L);
            l_tradedProductParams.setProductId(123456L);
            l_tradedProductParams.setProductType(ProductTypeEnum.EQUITY);
            TestDBUtility.insertWithDel(l_tradedProductParams);
            
            TestDBUtility.deleteAll(MarketRow.TYPE);
            MarketParams l_marketParams = TestDBUtility.getMarketRow();
            l_marketParams.setMarketId(3303L);
            TestDBUtility.insertWithDel(l_marketParams);
            
                        
            // BranchPreferencesRow
            TestDBUtility.deleteAll(BranchPreferencesRow.TYPE);
            BranchPreferencesParams l_branchPreferencesParams = TestDBUtility.getBranchPreferencesRow();
            l_branchPreferencesParams.setBranchId(33381);
            l_branchPreferencesParams.setName("exclude.except.settlement.buy.amount.check");
            TestDBUtility.insertWithDel(l_branchPreferencesParams);
            
            
            
        }
        catch (Exception l_ex)
        {
            log.error("", l_ex);
            log.exiting(TEST_END + STR_METHOD_NAME);
            fail();
        }
        log.exiting(TEST_END + STR_METHOD_NAME);
    }
    
    private void getMockData()
    {
        final String STR_METHOD_NAME = "getMockData()";
        log.entering(TEST_START + STR_METHOD_NAME);
        try
        {
            // TradedProductRow
            TradedProductParams l_tradedProductParams = TestDBUtility.getTradedProductRow();
            l_tradedProductParams.setTradedProductId(1006160060005L);
            l_tradedProductParams.setProductId(123456L);
            
            WEB3EquityTradedProduct l_web3EquityTradedProduct =
                new WEB3EquityTradedProduct(l_tradedProductParams);
            

            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                    "webbroker3.equity.WEB3EquityProductManager", "getTradedProduct", new Class[]
                    { long.class, long.class },
                    l_web3EquityTradedProduct);
            
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                    "webbroker3.tradingpower.WEB3TPTradingPowerSettlementServiceImpl", 
                    "getSellOrderPossibleQuantity",
                    new Class[]
                    { WEB3GentradeSubAccount.class, Date.class, long.class, long.class, double.class, double.class },
                    new Double(2000));
        }
        catch (Exception l_ex)
        {
            log.error("", l_ex);
            log.exiting(TEST_END + STR_METHOD_NAME);
            fail();
        }
        log.exiting(TEST_END + STR_METHOD_NAME);
    }
    
    private class WEB3GentradeSubAccountForTest extends WEB3GentradeSubAccount
    {

        public WEB3GentradeSubAccountForTest(SubAccountRow l_subAccountRow)
        {
            super(l_subAccountRow);
        }
        
        public MainAccount getMainAccount()
        {
            return new WEB3GentradeMainAccountForTest(TestDBUtility.getMainAccountRow());
        }
        
        public Institution getInstitution()
        {
            try
            {
                InstitutionParams l_institutionParams = TestDBUtility.getInstitutionRow();
                l_institutionParams.setAssetEvaluation("1");
                l_institutionParams.setBondEvaluation("1");
                return new WEB3GentradeInstitution(l_institutionParams);
            }
            catch(Exception l_ex)
            {
                return null;
            }
            
        }
        
    }
    
    private class WEB3GentradeMainAccountForTest extends WEB3GentradeMainAccount
    {

        public WEB3GentradeMainAccountForTest(MainAccountRow l_row)
        {
            super(l_row);
        }
        
        public boolean isMarginAccountEstablished(String l_strRepaymentType)
        {
            return false;
        }
        
        public boolean isAssetEvaluation() throws WEB3SystemLayerException
        {
            return isAssetEvaluation;
        }
        
    }

}
@
