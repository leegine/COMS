head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.04.07.00.56.06;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	5444d9d078c63f4;
filename	WEB3MarginOrderNotifyUnitServiceImplTest.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
 Copyright        : (Š”)‘å˜a‘Œ¤ ØŒ”ƒ\ƒŠƒ…[ƒVƒ‡ƒ“ƒVƒXƒeƒ€‘æ“ñ•”
 File Name        : WEB3MarginOrderNotifyUnitServiceImplTest.java
 Author Name      : Daiwa Institute of Research
 Revision History : 2008/07/31 —k•vŽu(’†u) V‹Kì¬ ƒ‚ƒfƒ‹ 132
*/
package webbroker3.equity.service.delegate.stdimpls;

import com.fitechlabs.xtrade.plugin.tc.eqtype.EqTypeExecutionConditionType;
import com.fitechlabs.xtrade.plugin.tc.eqtype.EqTypeOrderUnit;
import com.fitechlabs.xtrade.plugin.tc.eqtype.data.EqtypeOrderUnitParams;
import com.fitechlabs.xtrade.plugin.tc.eqtype.data.EqtypeOrderUnitRow;
import com.fitechlabs.xtrade.plugin.tc.eqtype.data.EqtypeProductParams;
import com.fitechlabs.xtrade.plugin.tc.eqtype.data.EqtypeProductRow;
import com.fitechlabs.xtrade.plugin.tc.eqtype.data.EqtypeTradedProductParams;
import com.fitechlabs.xtrade.plugin.tc.eqtype.data.EqtypeTradedProductRow;
import com.fitechlabs.xtrade.plugin.tc.eqtype.ordersubmitter.io.EqTypeOpenContractOrderSpec;
import com.fitechlabs.xtrade.plugin.tc.eqtype.ordersubmitter.io.EqTypeOrderSubmissionResult;
import com.fitechlabs.xtrade.plugin.tc.eqtype.ordersubmitter.io.EqTypeSettleContractOrderEntry;
import com.fitechlabs.xtrade.plugin.tc.eqtype.ordersubmitter.io.EqTypeSettleContractOrderSpec;
import com.fitechlabs.xtrade.plugin.tc.gentrade.GtlUtils;
import com.fitechlabs.xtrade.plugin.tc.gentrade.OrderTypeEnum;
import com.fitechlabs.xtrade.plugin.tc.gentrade.ProcessingResult;
import com.fitechlabs.xtrade.plugin.tc.gentrade.ProductTypeEnum;
import com.fitechlabs.xtrade.plugin.tc.gentrade.SubAccount;
import com.fitechlabs.xtrade.plugin.tc.gentrade.SubAccountTypeEnum;
import com.fitechlabs.xtrade.plugin.tc.gentrade.TaxTypeEnum;
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

import test.util.TestDBUtility;

import webbroker3.common.WEB3BaseException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.common.define.WEB3MarketCodeDef;
import webbroker3.common.define.WEB3PriceConditionSONARDef;
import webbroker3.common.define.WEB3RepaymentDivDef;
import webbroker3.common.define.WEB3ShortSellOrderFlagDef;
import webbroker3.common.define.WEB3SonarExecutionConditionDef;
import webbroker3.common.define.WEB3StatusDef;
import webbroker3.common.define.WEB3TradeTypeDef;
import webbroker3.common.define.WEB3TransactionTypeSONARDef;
import webbroker3.equity.WEB3EquityRealizedProfitAndLossPrice;
import webbroker3.equity.WEB3EquityTradedProduct;
import webbroker3.equity.data.HostEqtypeOrderReceiptParams;
import webbroker3.equity.message.WEB3MarginCloseMarginContractUnit;
import webbroker3.equity.service.delegate.WEB3MarginOrderNotifyDataAdapter;
import webbroker3.gentrade.WEB3GentradeCommission;
import webbroker3.gentrade.WEB3GentradeSubAccount;
import webbroker3.gentrade.data.BranchMarketRepayDealtCondParams;
import webbroker3.gentrade.data.BranchMarketRepayDealtCondRow;
import webbroker3.mock.TestBaseForMock;
import webbroker3.util.WEB3LogUtility;

public class WEB3MarginOrderNotifyUnitServiceImplTest extends TestBaseForMock
{
    WEB3LogUtility log = WEB3LogUtility.getInstance(WEB3MarginOrderNotifyUnitServiceImplTest.class);

    WEB3MarginOrderNotifyUnitServiceImpl l_serviceImpl= null;

    public WEB3MarginOrderNotifyUnitServiceImplTest(String arg0)
    {
        super(arg0);
    }

    protected void setUp() throws Exception
    {
        super.setUp();
        MOCK_MANAGER.setIsMockUsed(true);
        l_serviceImpl = new WEB3MarginOrderNotifyUnitServiceImpl();
    }

    protected void tearDown() throws Exception
    {
        super.tearDown();
    }

    //is”ƒŒš( )=true
    //l_orderSubmissionResult.getProcessingResult().isSuccessResult()
    public void testNotifyOpenMarginOrder_C0001()
    {
        final String STR_METHOD_NAME = "testNotifyOpenMarginOrder_C0001()";
        log.entering(TEST_START + STR_METHOD_NAME);

        try
        {
            HostEqtypeOrderReceiptParams l_params = new HostEqtypeOrderReceiptParams();
            l_params.setInstitutionCode("0D");
            l_params.setBranchCode("381");
            l_params.setAccountCode("2512246");
            l_params.setChannel("1");
            l_params.setProductCode("N8080");
            l_params.setSonarMarketCode("G");
            l_params.setTradeType(WEB3TradeTypeDef.OPEN_LONG_MARGIN);
            l_params.setSonarTradedCode(WEB3TransactionTypeSONARDef.OPEN_CONTRACT);
            l_params.setExecutionCondition(WEB3SonarExecutionConditionDef.UNCONDITIONDNESS);
            l_params.setPriceConditionType(WEB3PriceConditionSONARDef.DEFAULT);
            l_params.setSonarRepaymentType("1");
            l_params.setShortSellOrderFlag(WEB3ShortSellOrderFlagDef.PRICE_RESTRAINT);

            WEB3MarginOrderNotifyDataAdapter l_marginOrderNotifyDataAdapter =
                WEB3MarginOrderNotifyDataAdapter.create(l_params);

            TestDBUtility.deleteAll(EqtypeOrderUnitRow.TYPE);
            EqtypeOrderUnitParams eqtypeOrderUnitParams = TestDBUtility.getEqtypeOrderUnitRow();
            TestDBUtility.insertWithDel(eqtypeOrderUnitParams);

            TestDBUtility.deleteAll(MainAccountRow.TYPE);
            MainAccountParams mainAccountParams = TestDBUtility.getMainAccountRow();
            mainAccountParams.setAccountId(333812512203L);
            TestDBUtility.insertWithDel(mainAccountParams);

            TestDBUtility.deleteAll(SubAccountRow.TYPE);
            SubAccountParams subAccountParams = TestDBUtility.getSubAccountRow();
            subAccountParams.setSubAccountType(SubAccountTypeEnum.EQUITY_MARGIN_SUB_ACCOUNT);
            TestDBUtility.insertWithDel(subAccountParams);

            TestDBUtility.deleteAll(InstitutionRow.TYPE);
            InstitutionParams institutionParams = TestDBUtility.getInstitutionRow();
            TestDBUtility.insertWithDel(institutionParams);

            TestDBUtility.deleteAll(ProductRow.TYPE);
            ProductParams productParams = TestDBUtility.getProductRow();
            productParams.setProductType(ProductTypeEnum.EQUITY);
            TestDBUtility.insertWithDel(productParams);

            TestDBUtility.deleteAll(MarketRow.TYPE);
            MarketParams marketParams = TestDBUtility.getMarketRow();
            marketParams.setMarketCode(WEB3MarketCodeDef.TOKYO);
            TestDBUtility.insertWithDel(marketParams);

            TestDBUtility.deleteAll(EqtypeProductRow.TYPE);
            EqtypeProductParams eqtypeProductParams = TestDBUtility.getEqtypeProductRow();
            TestDBUtility.insertWithDel(eqtypeProductParams);

            TestDBUtility.deleteAll(EqtypeTradedProductRow.TYPE);
            EqtypeTradedProductParams eqtypeTradedProductParams = TestDBUtility.getEqtypeTradedProductRow();
            TestDBUtility.insertWithDel(eqtypeTradedProductParams);

            TestDBUtility.deleteAll(BranchMarketRepayDealtCondRow.TYPE);
            BranchMarketRepayDealtCondParams repayDealtCondParams = TestDBUtility.getBranchMarketRepayDealCondRow();
            repayDealtCondParams.setInstitutionCode("0D");
            repayDealtCondParams.setBranchCode("381");
            repayDealtCondParams.setSonarRepaymentType(WEB3RepaymentDivDef.REPAYMENT_DIV_MARGIN_SYS);
            TestDBUtility.insertWithDel(repayDealtCondParams);

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

            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                    "webbroker3.equity.WEB3EquityOrderManager",
                    "calcContractAmountAtOrder",
                    new Class[] {
                        WEB3GentradeCommission.class, double.class,
                        WEB3GentradeSubAccount.class, WEB3EquityTradedProduct.class,
                        double.class, double.class,
                        double.class, boolean.class},
                        new Double(100));

            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue("webbroker3.equity.WEB3EquityBizLogicProvider",
                    "calcCommission", new Class[]
                    { WEB3GentradeCommission.class, SubAccount.class },
                    null);

            ProcessingResult processingResult = ProcessingResult.newSuccessResultInstance();
            EqTypeOrderSubmissionResult l_result = new EqTypeOrderSubmissionResult(processingResult);
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                    "webbroker3.equity.WEB3EquityOrderManager", "submitOpenContractOrder", new Class[]
                    { SubAccount.class, EqTypeOpenContractOrderSpec.class, long.class, String.class, boolean.class },
                    l_result);

            l_serviceImpl.notifyOpenMarginOrder(l_marginOrderNotifyDataAdapter);

            assertEquals(l_params.getStatus(), WEB3StatusDef.DEALT);
            assertEquals(l_params.getLastUpdatedTimestamp(), GtlUtils.getSystemTimestamp());
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

    //is”ƒŒš( )=false
    //l_orderSubmissionResult.getProcessingResult().isFailedResult()
    public void testNotifyOpenMarginOrder_C0002()
    {
        final String STR_METHOD_NAME = "testNotifyOpenMarginOrder_C0002()";
        log.entering(TEST_START + STR_METHOD_NAME);

        try
        {
            HostEqtypeOrderReceiptParams l_params = new HostEqtypeOrderReceiptParams();
            l_params.setInstitutionCode("0D");
            l_params.setBranchCode("381");
            l_params.setAccountCode("2512246");
            l_params.setChannel("1");
            l_params.setProductCode("N8080");
            l_params.setSonarMarketCode("G");
            l_params.setTradeType(WEB3TradeTypeDef.OPEN_SHORT_MARGIN);
            l_params.setSonarTradedCode(WEB3TransactionTypeSONARDef.OPEN_CONTRACT);
            l_params.setExecutionCondition(WEB3SonarExecutionConditionDef.UNCONDITIONDNESS);
            l_params.setPriceConditionType(WEB3PriceConditionSONARDef.DEFAULT);
            l_params.setSonarRepaymentType("1");
            l_params.setShortSellOrderFlag(WEB3ShortSellOrderFlagDef.PRICE_RESTRAINT);

            WEB3MarginOrderNotifyDataAdapter l_marginOrderNotifyDataAdapter =
                WEB3MarginOrderNotifyDataAdapter.create(l_params);

            TestDBUtility.deleteAll(EqtypeOrderUnitRow.TYPE);
            EqtypeOrderUnitParams eqtypeOrderUnitParams = TestDBUtility.getEqtypeOrderUnitRow();
            TestDBUtility.insertWithDel(eqtypeOrderUnitParams);

            TestDBUtility.deleteAll(MainAccountRow.TYPE);
            MainAccountParams mainAccountParams = TestDBUtility.getMainAccountRow();
            mainAccountParams.setAccountId(333812512203L);
            TestDBUtility.insertWithDel(mainAccountParams);

            TestDBUtility.deleteAll(SubAccountRow.TYPE);
            SubAccountParams subAccountParams = TestDBUtility.getSubAccountRow();
            subAccountParams.setSubAccountType(SubAccountTypeEnum.EQUITY_MARGIN_SUB_ACCOUNT);
            TestDBUtility.insertWithDel(subAccountParams);

            TestDBUtility.deleteAll(InstitutionRow.TYPE);
            InstitutionParams institutionParams = TestDBUtility.getInstitutionRow();
            TestDBUtility.insertWithDel(institutionParams);

            TestDBUtility.deleteAll(ProductRow.TYPE);
            ProductParams productParams = TestDBUtility.getProductRow();
            productParams.setProductType(ProductTypeEnum.EQUITY);
            TestDBUtility.insertWithDel(productParams);

            TestDBUtility.deleteAll(MarketRow.TYPE);
            MarketParams marketParams = TestDBUtility.getMarketRow();
            marketParams.setMarketCode(WEB3MarketCodeDef.TOKYO);
            TestDBUtility.insertWithDel(marketParams);

            TestDBUtility.deleteAll(EqtypeProductRow.TYPE);
            EqtypeProductParams eqtypeProductParams = TestDBUtility.getEqtypeProductRow();
            TestDBUtility.insertWithDel(eqtypeProductParams);

            TestDBUtility.deleteAll(EqtypeTradedProductRow.TYPE);
            EqtypeTradedProductParams eqtypeTradedProductParams = TestDBUtility.getEqtypeTradedProductRow();
            TestDBUtility.insertWithDel(eqtypeTradedProductParams);

            TestDBUtility.deleteAll(BranchMarketRepayDealtCondRow.TYPE);
            BranchMarketRepayDealtCondParams repayDealtCondParams = TestDBUtility.getBranchMarketRepayDealCondRow();
            repayDealtCondParams.setInstitutionCode("0D");
            repayDealtCondParams.setBranchCode("381");
            repayDealtCondParams.setSonarRepaymentType(WEB3RepaymentDivDef.REPAYMENT_DIV_MARGIN_SYS);
            TestDBUtility.insertWithDel(repayDealtCondParams);

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

            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                    "webbroker3.equity.WEB3EquityOrderManager",
                    "calcContractAmountAtOrder",
                    new Class[] {
                        WEB3GentradeCommission.class, double.class,
                        WEB3GentradeSubAccount.class, WEB3EquityTradedProduct.class,
                        double.class, double.class,
                        double.class, boolean.class},
                        new Double(100));

            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue("webbroker3.equity.WEB3EquityBizLogicProvider",
                    "calcCommission", new Class[]
                    { WEB3GentradeCommission.class, SubAccount.class },
                    null);

            ProcessingResult processingResult = ProcessingResult.newFailedResultInstance(WEB3ErrorCatalog.BUSINESS_ERROR_00003);
            EqTypeOrderSubmissionResult l_result = new EqTypeOrderSubmissionResult(processingResult);
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                    "webbroker3.equity.WEB3EquityOrderManager", "submitOpenContractOrder", new Class[]
                    { SubAccount.class, EqTypeOpenContractOrderSpec.class, long.class, String.class, boolean.class },
                    l_result);

            l_serviceImpl.notifyOpenMarginOrder(l_marginOrderNotifyDataAdapter);
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

    //is”ƒŒš( )=false
    //NotFoundException
    public void testNotifyOpenMarginOrder_C0003()
    {
        final String STR_METHOD_NAME = "testNotifyOpenMarginOrder_C0003()";
        log.entering(TEST_START + STR_METHOD_NAME);

        try
        {
            HostEqtypeOrderReceiptParams l_params = new HostEqtypeOrderReceiptParams();
            l_params.setInstitutionCode("00");
            l_params.setBranchCode("381");
            l_params.setAccountCode("2512246");
            l_params.setChannel("1");
            l_params.setProductCode("N8080");
            l_params.setSonarMarketCode("G");
            l_params.setTradeType(WEB3TradeTypeDef.OPEN_SHORT_MARGIN);
            l_params.setSonarTradedCode(WEB3TransactionTypeSONARDef.OPEN_CONTRACT);
            l_params.setExecutionCondition(WEB3SonarExecutionConditionDef.UNCONDITIONDNESS);
            l_params.setPriceConditionType(WEB3PriceConditionSONARDef.DEFAULT);
            l_params.setSonarRepaymentType("1");
            l_params.setShortSellOrderFlag(WEB3ShortSellOrderFlagDef.PRICE_RESTRAINT);

            WEB3MarginOrderNotifyDataAdapter l_marginOrderNotifyDataAdapter =
                WEB3MarginOrderNotifyDataAdapter.create(l_params);

            TestDBUtility.deleteAll(EqtypeOrderUnitRow.TYPE);
            EqtypeOrderUnitParams eqtypeOrderUnitParams = TestDBUtility.getEqtypeOrderUnitRow();
            TestDBUtility.insertWithDel(eqtypeOrderUnitParams);

            TestDBUtility.deleteAll(MainAccountRow.TYPE);
            MainAccountParams mainAccountParams = TestDBUtility.getMainAccountRow();
            mainAccountParams.setAccountId(333812512203L);
            TestDBUtility.insertWithDel(mainAccountParams);

            TestDBUtility.deleteAll(SubAccountRow.TYPE);
            SubAccountParams subAccountParams = TestDBUtility.getSubAccountRow();
            subAccountParams.setSubAccountType(SubAccountTypeEnum.EQUITY_MARGIN_SUB_ACCOUNT);
            TestDBUtility.insertWithDel(subAccountParams);

            TestDBUtility.deleteAll(InstitutionRow.TYPE);
            InstitutionParams institutionParams = TestDBUtility.getInstitutionRow();
            TestDBUtility.insertWithDel(institutionParams);

            TestDBUtility.deleteAll(ProductRow.TYPE);
            ProductParams productParams = TestDBUtility.getProductRow();
            productParams.setProductType(ProductTypeEnum.EQUITY);
            TestDBUtility.insertWithDel(productParams);

            TestDBUtility.deleteAll(MarketRow.TYPE);
            MarketParams marketParams = TestDBUtility.getMarketRow();
            marketParams.setMarketCode(WEB3MarketCodeDef.TOKYO);
            TestDBUtility.insertWithDel(marketParams);

            TestDBUtility.deleteAll(EqtypeProductRow.TYPE);
            EqtypeProductParams eqtypeProductParams = TestDBUtility.getEqtypeProductRow();
            TestDBUtility.insertWithDel(eqtypeProductParams);

            TestDBUtility.deleteAll(EqtypeTradedProductRow.TYPE);
            EqtypeTradedProductParams eqtypeTradedProductParams = TestDBUtility.getEqtypeTradedProductRow();
            TestDBUtility.insertWithDel(eqtypeTradedProductParams);

            TestDBUtility.deleteAll(BranchMarketRepayDealtCondRow.TYPE);
            BranchMarketRepayDealtCondParams repayDealtCondParams = TestDBUtility.getBranchMarketRepayDealCondRow();
            repayDealtCondParams.setInstitutionCode("0D");
            repayDealtCondParams.setBranchCode("381");
            repayDealtCondParams.setSonarRepaymentType(WEB3RepaymentDivDef.REPAYMENT_DIV_MARGIN_SYS);
            TestDBUtility.insertWithDel(repayDealtCondParams);

            l_serviceImpl.notifyOpenMarginOrder(l_marginOrderNotifyDataAdapter);
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

    //l_orderSubmissionResult.getProcessingResult().isSuccessResult()
    public void testNotifyCloseMarginOrder_C0001()
    {
        final String STR_METHOD_NAME = "testNotifyCloseMarginOrder_C0001()";
        log.entering(TEST_START + STR_METHOD_NAME);

        try
        {
            HostEqtypeOrderReceiptParams l_params = new HostEqtypeOrderReceiptParams();
            l_params.setInstitutionCode("0D");
            l_params.setBranchCode("381");
            l_params.setAccountCode("2512246");
            l_params.setChannel("1");
            l_params.setProductCode("N8080");
            l_params.setSonarMarketCode("G");
            l_params.setTradeType(WEB3TradeTypeDef.OPEN_LONG_MARGIN);
            l_params.setSonarTradedCode(WEB3TransactionTypeSONARDef.OPEN_CONTRACT);
            l_params.setExecutionCondition(WEB3SonarExecutionConditionDef.UNCONDITIONDNESS);
            l_params.setPriceConditionType(WEB3PriceConditionSONARDef.DEFAULT);
            l_params.setSonarRepaymentType("1");
            l_params.setShortSellOrderFlag(WEB3ShortSellOrderFlagDef.PRICE_RESTRAINT);

            WEB3MarginOrderNotifyDataAdapter l_marginOrderNotifyDataAdapter =
                WEB3MarginOrderNotifyDataAdapter.create(l_params);

            TestDBUtility.deleteAll(EqtypeOrderUnitRow.TYPE);
            EqtypeOrderUnitParams eqtypeOrderUnitParams = TestDBUtility.getEqtypeOrderUnitRow();
            TestDBUtility.insertWithDel(eqtypeOrderUnitParams);

            TestDBUtility.deleteAll(MainAccountRow.TYPE);
            MainAccountParams mainAccountParams = TestDBUtility.getMainAccountRow();
            mainAccountParams.setAccountId(333812512203L);
            TestDBUtility.insertWithDel(mainAccountParams);

            TestDBUtility.deleteAll(SubAccountRow.TYPE);
            SubAccountParams subAccountParams = TestDBUtility.getSubAccountRow();
            subAccountParams.setSubAccountType(SubAccountTypeEnum.EQUITY_MARGIN_SUB_ACCOUNT);
            TestDBUtility.insertWithDel(subAccountParams);

            TestDBUtility.deleteAll(InstitutionRow.TYPE);
            InstitutionParams institutionParams = TestDBUtility.getInstitutionRow();
            TestDBUtility.insertWithDel(institutionParams);

            TestDBUtility.deleteAll(ProductRow.TYPE);
            ProductParams productParams = TestDBUtility.getProductRow();
            productParams.setProductType(ProductTypeEnum.EQUITY);
            TestDBUtility.insertWithDel(productParams);

            TestDBUtility.deleteAll(MarketRow.TYPE);
            MarketParams marketParams = TestDBUtility.getMarketRow();
            marketParams.setMarketCode(WEB3MarketCodeDef.TOKYO);
            TestDBUtility.insertWithDel(marketParams);

            TestDBUtility.deleteAll(EqtypeProductRow.TYPE);
            EqtypeProductParams eqtypeProductParams = TestDBUtility.getEqtypeProductRow();
            TestDBUtility.insertWithDel(eqtypeProductParams);

            TestDBUtility.deleteAll(EqtypeTradedProductRow.TYPE);
            EqtypeTradedProductParams eqtypeTradedProductParams = TestDBUtility.getEqtypeTradedProductRow();
            TestDBUtility.insertWithDel(eqtypeTradedProductParams);

            TestDBUtility.deleteAll(BranchMarketRepayDealtCondRow.TYPE);
            BranchMarketRepayDealtCondParams repayDealtCondParams = TestDBUtility.getBranchMarketRepayDealCondRow();
            repayDealtCondParams.setInstitutionCode("0D");
            repayDealtCondParams.setBranchCode("381");
            repayDealtCondParams.setSonarRepaymentType(WEB3RepaymentDivDef.REPAYMENT_DIV_MARGIN_SYS);
            TestDBUtility.insertWithDel(repayDealtCondParams);

            WEB3EquityRealizedProfitAndLossPrice l_price = new WEB3EquityRealizedProfitAndLossPrice();
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                    "webbroker3.equity.WEB3EquityOrderManager",
                    "calcEstimatedRealizedProfitAndLossAmount",
                    new Class[]{WEB3GentradeCommission.class,
                            double.class,
                            WEB3GentradeSubAccount.class,
                            WEB3EquityTradedProduct.class,
                            EqTypeSettleContractOrderEntry[].class,
                            double.class,
                            EqTypeOrderUnit.class,
                            double.class,
                            double.class,
                            boolean.class},
                            l_price);

            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue("webbroker3.equity.WEB3EquityBizLogicProvider",
                    "calcCommission", new Class[]
                    { WEB3GentradeCommission.class, SubAccount.class },
                    null);

            ProcessingResult processingResult = ProcessingResult.newSuccessResultInstance();
            EqTypeOrderSubmissionResult l_result = new EqTypeOrderSubmissionResult(processingResult);
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                    "webbroker3.equity.WEB3EquityOrderManager",
                    "submitSettleContractOrder",
                    new Class[]
                    {SubAccount.class,EqTypeSettleContractOrderSpec.class,long.class,String.class,boolean.class},
                    l_result);
            
            WEB3MarginCloseMarginContractUnit[] units = new WEB3MarginCloseMarginContractUnit[]{};
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                    "webbroker3.equity.WEB3EquityPositionManager", "createCloseMarginContracts", new Class[]
                    {WEB3GentradeSubAccount.class,boolean.class, long.class, long.class, TaxTypeEnum.class, String.class, double.class},
                    units);

            EqTypeSettleContractOrderEntry[] entries = new EqTypeSettleContractOrderEntry[]{};
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                    "webbroker3.equity.WEB3EquityOrderManager",
                    "createClosingContractEntry",
                    new Class[] {
                        long.class,
                        double.class,
                        WEB3MarginCloseMarginContractUnit[].class,
                        boolean.class},
                        entries);

            l_serviceImpl.notifyCloseMarginOrder(l_marginOrderNotifyDataAdapter);

            assertEquals(l_params.getStatus(), WEB3StatusDef.DEALT);
            assertEquals(l_params.getLastUpdatedTimestamp(), GtlUtils.getSystemTimestamp());
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
    public void testNotifyCloseMarginOrder_C0002()
    {
        final String STR_METHOD_NAME = "testNotifyCloseMarginOrder_C0002()";
        log.entering(TEST_START + STR_METHOD_NAME);

        try
        {
            HostEqtypeOrderReceiptParams l_params = new HostEqtypeOrderReceiptParams();
            l_params.setInstitutionCode("0D");
            l_params.setBranchCode("381");
            l_params.setAccountCode("2512246");
            l_params.setChannel("1");
            l_params.setProductCode("N8080");
            l_params.setSonarMarketCode("G");
            l_params.setTradeType(WEB3TradeTypeDef.OPEN_LONG_MARGIN);
            l_params.setSonarTradedCode(WEB3TransactionTypeSONARDef.OPEN_CONTRACT);
            l_params.setExecutionCondition(WEB3SonarExecutionConditionDef.UNCONDITIONDNESS);
            l_params.setPriceConditionType(WEB3PriceConditionSONARDef.DEFAULT);
            l_params.setSonarRepaymentType("1");
            l_params.setShortSellOrderFlag(WEB3ShortSellOrderFlagDef.PRICE_RESTRAINT);

            WEB3MarginOrderNotifyDataAdapter l_marginOrderNotifyDataAdapter =
                WEB3MarginOrderNotifyDataAdapter.create(l_params);

            TestDBUtility.deleteAll(EqtypeOrderUnitRow.TYPE);
            EqtypeOrderUnitParams eqtypeOrderUnitParams = TestDBUtility.getEqtypeOrderUnitRow();
            TestDBUtility.insertWithDel(eqtypeOrderUnitParams);

            TestDBUtility.deleteAll(MainAccountRow.TYPE);
            MainAccountParams mainAccountParams = TestDBUtility.getMainAccountRow();
            mainAccountParams.setAccountId(333812512203L);
            TestDBUtility.insertWithDel(mainAccountParams);

            TestDBUtility.deleteAll(SubAccountRow.TYPE);
            SubAccountParams subAccountParams = TestDBUtility.getSubAccountRow();
            subAccountParams.setSubAccountType(SubAccountTypeEnum.EQUITY_MARGIN_SUB_ACCOUNT);
            TestDBUtility.insertWithDel(subAccountParams);

            TestDBUtility.deleteAll(InstitutionRow.TYPE);
            InstitutionParams institutionParams = TestDBUtility.getInstitutionRow();
            TestDBUtility.insertWithDel(institutionParams);

            TestDBUtility.deleteAll(ProductRow.TYPE);
            ProductParams productParams = TestDBUtility.getProductRow();
            productParams.setProductType(ProductTypeEnum.EQUITY);
            TestDBUtility.insertWithDel(productParams);

            TestDBUtility.deleteAll(MarketRow.TYPE);
            MarketParams marketParams = TestDBUtility.getMarketRow();
            marketParams.setMarketCode(WEB3MarketCodeDef.TOKYO);
            TestDBUtility.insertWithDel(marketParams);

            TestDBUtility.deleteAll(EqtypeProductRow.TYPE);
            EqtypeProductParams eqtypeProductParams = TestDBUtility.getEqtypeProductRow();
            TestDBUtility.insertWithDel(eqtypeProductParams);

            TestDBUtility.deleteAll(EqtypeTradedProductRow.TYPE);
            EqtypeTradedProductParams eqtypeTradedProductParams = TestDBUtility.getEqtypeTradedProductRow();
            TestDBUtility.insertWithDel(eqtypeTradedProductParams);

            TestDBUtility.deleteAll(BranchMarketRepayDealtCondRow.TYPE);
            BranchMarketRepayDealtCondParams repayDealtCondParams = TestDBUtility.getBranchMarketRepayDealCondRow();
            repayDealtCondParams.setInstitutionCode("0D");
            repayDealtCondParams.setBranchCode("381");
            repayDealtCondParams.setSonarRepaymentType(WEB3RepaymentDivDef.REPAYMENT_DIV_MARGIN_SYS);
            TestDBUtility.insertWithDel(repayDealtCondParams);

            WEB3EquityRealizedProfitAndLossPrice l_price = new WEB3EquityRealizedProfitAndLossPrice();
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                    "webbroker3.equity.WEB3EquityOrderManager",
                    "calcEstimatedRealizedProfitAndLossAmount",
                    new Class[]{WEB3GentradeCommission.class,
                            double.class,
                            WEB3GentradeSubAccount.class,
                            WEB3EquityTradedProduct.class,
                            EqTypeSettleContractOrderEntry[].class,
                            double.class,
                            EqTypeOrderUnit.class,
                            double.class,
                            double.class,
                            boolean.class},
                            l_price);

            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue("webbroker3.equity.WEB3EquityBizLogicProvider",
                    "calcCommission", new Class[]
                    { WEB3GentradeCommission.class, SubAccount.class },
                    null);

            ProcessingResult processingResult = ProcessingResult.newFailedResultInstance(WEB3ErrorCatalog.BUSINESS_ERROR_00003);
            EqTypeOrderSubmissionResult l_result = new EqTypeOrderSubmissionResult(processingResult);
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                    "webbroker3.equity.WEB3EquityOrderManager",
                    "submitSettleContractOrder",
                    new Class[]
                    {SubAccount.class,EqTypeSettleContractOrderSpec.class,long.class,String.class,boolean.class},
                    l_result);
            
            WEB3MarginCloseMarginContractUnit[] units = new WEB3MarginCloseMarginContractUnit[]{};
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                    "webbroker3.equity.WEB3EquityPositionManager", "createCloseMarginContracts", new Class[]
                    {WEB3GentradeSubAccount.class,boolean.class, long.class, long.class, TaxTypeEnum.class, String.class, double.class},
                    units);

            EqTypeSettleContractOrderEntry[] entries = new EqTypeSettleContractOrderEntry[]{};
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                    "webbroker3.equity.WEB3EquityOrderManager",
                    "createClosingContractEntry",
                    new Class[] {
                        long.class,
                        double.class,
                        WEB3MarginCloseMarginContractUnit[].class,
                        boolean.class},
                        entries);

            l_serviceImpl.notifyCloseMarginOrder(l_marginOrderNotifyDataAdapter);
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

    //NotFoundException
    public void testNotifyCloseMarginOrder_C0003()
    {
        final String STR_METHOD_NAME = "testNotifyCloseMarginOrder_C0003()";
        log.entering(TEST_START + STR_METHOD_NAME);

        try
        {
            HostEqtypeOrderReceiptParams l_params = new HostEqtypeOrderReceiptParams();
            l_params.setInstitutionCode("00");
            l_params.setBranchCode("381");
            l_params.setAccountCode("2512246");
            l_params.setChannel("1");
            l_params.setProductCode("N8080");
            l_params.setSonarMarketCode("G");
            l_params.setTradeType(WEB3TradeTypeDef.OPEN_LONG_MARGIN);
            l_params.setSonarTradedCode(WEB3TransactionTypeSONARDef.OPEN_CONTRACT);
            l_params.setExecutionCondition(WEB3SonarExecutionConditionDef.UNCONDITIONDNESS);
            l_params.setPriceConditionType(WEB3PriceConditionSONARDef.DEFAULT);
            l_params.setSonarRepaymentType("1");
            l_params.setShortSellOrderFlag(WEB3ShortSellOrderFlagDef.PRICE_RESTRAINT);

            WEB3MarginOrderNotifyDataAdapter l_marginOrderNotifyDataAdapter =
                WEB3MarginOrderNotifyDataAdapter.create(l_params);

            TestDBUtility.deleteAll(EqtypeOrderUnitRow.TYPE);
            EqtypeOrderUnitParams eqtypeOrderUnitParams = TestDBUtility.getEqtypeOrderUnitRow();
            TestDBUtility.insertWithDel(eqtypeOrderUnitParams);

            TestDBUtility.deleteAll(MainAccountRow.TYPE);
            MainAccountParams mainAccountParams = TestDBUtility.getMainAccountRow();
            mainAccountParams.setAccountId(333812512203L);
            TestDBUtility.insertWithDel(mainAccountParams);

            TestDBUtility.deleteAll(SubAccountRow.TYPE);
            SubAccountParams subAccountParams = TestDBUtility.getSubAccountRow();
            subAccountParams.setSubAccountType(SubAccountTypeEnum.EQUITY_MARGIN_SUB_ACCOUNT);
            TestDBUtility.insertWithDel(subAccountParams);

            TestDBUtility.deleteAll(InstitutionRow.TYPE);
            InstitutionParams institutionParams = TestDBUtility.getInstitutionRow();
            TestDBUtility.insertWithDel(institutionParams);

            TestDBUtility.deleteAll(ProductRow.TYPE);
            ProductParams productParams = TestDBUtility.getProductRow();
            productParams.setProductType(ProductTypeEnum.EQUITY);
            TestDBUtility.insertWithDel(productParams);

            TestDBUtility.deleteAll(MarketRow.TYPE);
            MarketParams marketParams = TestDBUtility.getMarketRow();
            marketParams.setMarketCode(WEB3MarketCodeDef.TOKYO);
            TestDBUtility.insertWithDel(marketParams);

            TestDBUtility.deleteAll(EqtypeProductRow.TYPE);
            EqtypeProductParams eqtypeProductParams = TestDBUtility.getEqtypeProductRow();
            TestDBUtility.insertWithDel(eqtypeProductParams);

            TestDBUtility.deleteAll(EqtypeTradedProductRow.TYPE);
            EqtypeTradedProductParams eqtypeTradedProductParams = TestDBUtility.getEqtypeTradedProductRow();
            TestDBUtility.insertWithDel(eqtypeTradedProductParams);

            TestDBUtility.deleteAll(BranchMarketRepayDealtCondRow.TYPE);
            BranchMarketRepayDealtCondParams repayDealtCondParams = TestDBUtility.getBranchMarketRepayDealCondRow();
            repayDealtCondParams.setInstitutionCode("0D");
            repayDealtCondParams.setBranchCode("381");
            repayDealtCondParams.setSonarRepaymentType(WEB3RepaymentDivDef.REPAYMENT_DIV_MARGIN_SYS);
            TestDBUtility.insertWithDel(repayDealtCondParams);

            l_serviceImpl.notifyCloseMarginOrder(l_marginOrderNotifyDataAdapter);
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

}
@
