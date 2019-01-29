head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.04.07.02.34.19;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	5444d9d078c63f4;
filename	WEB3TPPaymentRequisitionManageServiceImplTest.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@package webbroker3.tradingpower.service.delegate.stdimpls;

import java.sql.Timestamp;
import java.util.Calendar;

import test.util.TestDBUtility;
import webbroker3.common.WEB3BaseException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.gentrade.WEB3GentradeMainAccount;
import webbroker3.gentrade.WEB3GentradeTradingClendarContext;
import webbroker3.gentrade.WEB3GentradeTradingTimeManagement;
import webbroker3.gentrade.data.BranchPreferencesParams;
import webbroker3.gentrade.data.BranchPreferencesRow;
import webbroker3.gentrade.data.OrderexecutionEndParams;
import webbroker3.gentrade.data.OrderexecutionEndRow;
import webbroker3.gentrade.data.ProcessManagementRow;
import webbroker3.gentrade.data.TradingpowerCalcConditionParams;
import webbroker3.gentrade.data.TradingpowerCalcConditionRow;
import webbroker3.mock.TestBaseForMock;
import webbroker3.tradingpower.WEB3TPAdddepositGenerationInfo;
import webbroker3.tradingpower.WEB3TPFirstAdddepositInfo;
import webbroker3.tradingpower.WEB3TPPaymentRequisitionAccountDetailInfo;
import webbroker3.tradingpower.WEB3TPShortfallOccurInfo;
import webbroker3.tradingpower.data.PaymentRequisitMngParams;
import webbroker3.tradingpower.data.PaymentRequisitMngRow;
import webbroker3.tradingpower.data.TpCalcResultEquityDetailParams;
import webbroker3.tradingpower.data.TpCalcResultEquityDetailRow;
import webbroker3.tradingpower.data.TpCalcResultEquityParams;
import webbroker3.tradingpower.data.TpCalcResultEquityRow;
import webbroker3.tradingpower.data.TpCalcResultMarginDetailParams;
import webbroker3.tradingpower.data.TpCalcResultMarginDetailRow;
import webbroker3.tradingpower.data.TpCalcResultMarginParams;
import webbroker3.tradingpower.data.TpCalcResultMarginRow;
import webbroker3.tradingpower.data.TpCashBalanceParams;
import webbroker3.tradingpower.data.TpCashBalanceRow;
import webbroker3.tradingpower.define.WEB3TPMarkToMarketDivDef;
import webbroker3.util.WEB3DateUtility;
import webbroker3.util.WEB3LogUtility;

import com.fitechlabs.xtrade.kernel.data.Processors;
import com.fitechlabs.xtrade.kernel.data.QueryProcessor;
import com.fitechlabs.xtrade.kernel.util.ThreadLocalSystemAttributesRegistry;
import com.fitechlabs.xtrade.plugin.tc.gentrade.OrderStatusEnum;
import com.fitechlabs.xtrade.plugin.tc.gentrade.OrderTypeEnum;
import com.fitechlabs.xtrade.plugin.tc.gentrade.ProductTypeEnum;
import com.fitechlabs.xtrade.plugin.tc.gentrade.SubAccountTypeEnum;
import com.fitechlabs.xtrade.plugin.tc.gentrade.data.BranchParams;
import com.fitechlabs.xtrade.plugin.tc.gentrade.data.BranchRow;
import com.fitechlabs.xtrade.plugin.tc.gentrade.data.InstitutionParams;
import com.fitechlabs.xtrade.plugin.tc.gentrade.data.InstitutionRow;
import com.fitechlabs.xtrade.plugin.tc.gentrade.data.MainAccountParams;
import com.fitechlabs.xtrade.plugin.tc.gentrade.data.MainAccountRow;
import com.fitechlabs.xtrade.plugin.tc.gentrade.data.SubAccountParams;
import com.fitechlabs.xtrade.plugin.tc.gentrade.data.SubAccountRow;
import com.fitechlabs.xtrade.plugin.tc.gentrade.data.SystemPreferencesRow;
import com.fitechlabs.xtrade.plugin.tc.xbaio.data.AioOrderUnitParams;
import com.fitechlabs.xtrade.plugin.tc.xbaio.data.AioOrderUnitRow;

public class WEB3TPPaymentRequisitionManageServiceImplTest extends TestBaseForMock
{
    /**
     * ÉçÉOÉÜÅ[ÉeÉBÉäÉeÉB<BR>
     */
    private static WEB3LogUtility log = WEB3LogUtility.getInstance(WEB3TPPaymentRequisitionManageServiceImplTest.class);
    
    public WEB3TPPaymentRequisitionManageServiceImplTest(String name)
    {
        super(name);
    }

    protected void setUp() throws Exception
    {
        super.setUp();
    }

    protected void tearDown() throws Exception
    {
        super.tearDown();
    }

    /**
     * Åh2Åh(ïsë´ã‡î≠ê∂<êMópå⁄ãq>)Çï‘ãp
     */
    public void test_getLackCashOccurSituation_0001()
    {
        final String STR_METHOD_NAME = "test_getLackCashOccurSituation_0001()";
        log.entering(TEST_START + STR_METHOD_NAME);

        try
        {
            
            Calendar l_calendar = Calendar.getInstance();
            l_calendar.set(Calendar.YEAR,2007);
            l_calendar.set(Calendar.MONTH,2);
            l_calendar.set(Calendar.DAY_OF_MONTH,14);
            l_calendar.set(Calendar.HOUR_OF_DAY,15);
            l_calendar.set(Calendar.MINUTE,00);
            l_calendar.set(Calendar.SECOND,01);

            Timestamp l_tsBizDate = new Timestamp(l_calendar.getTimeInMillis());

            ThreadLocalSystemAttributesRegistry.setAttribute(
                WEB3GentradeTradingTimeManagement.TIMESTAMP_TAG, l_tsBizDate);

            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                "com.fitechlabs.xtrade.plugin.tc.gentrade.stdimpls.TradingSystemImpl",
                "getSystemTimestamp",
                new Class[] {},
                l_tsBizDate);

            WEB3GentradeTradingClendarContext l_clendarContext = new WEB3GentradeTradingClendarContext();
            ThreadLocalSystemAttributesRegistry.setAttribute("web3.tradingcalendarcontext", l_clendarContext);

            //DB
            MainAccountParams l_mainAccountParams = new MainAccountParams();
            QueryProcessor l_processor = Processors.getDefaultProcessor();
            l_processor.doDeleteAllQuery(MainAccountRow.TYPE);
            l_mainAccountParams = TestDBUtility.getMainAccountRow();
            l_mainAccountParams.setMarginSysAccOpenDiv("1");    //the third case
            l_mainAccountParams.setSecuredLoanSecAccOpenDiv("0");
            TestDBUtility.insertWithDel(l_mainAccountParams);

            InstitutionParams l_institutionParams =  new InstitutionParams();
            l_processor.doDeleteAllQuery(InstitutionRow.TYPE);
            l_institutionParams = TestDBUtility.getInstitutionRow();
            l_institutionParams.setAssetEvaluation("0");
            l_institutionParams.setMaximumAssessment(1L);
            TestDBUtility.insertWithDel(l_institutionParams);

            BranchParams l_branchParams =  new BranchParams();
            l_processor.doDeleteAllQuery(BranchRow.TYPE);
            l_branchParams = TestDBUtility.getBranchRow();
            TestDBUtility.insertWithDel(l_branchParams);

            PaymentRequisitMngParams l_paymentRequisitMngParams = new PaymentRequisitMngParams();
            l_processor.doDeleteAllQuery(PaymentRequisitMngRow.TYPE);
            l_paymentRequisitMngParams.setAccountId(333812512246L);
            l_paymentRequisitMngParams.setAccountCode("2512246");
            l_paymentRequisitMngParams.setBranchCode("381");
            l_paymentRequisitMngParams.setInstitutionCode("0D");
            l_paymentRequisitMngParams.setFamilyName("Family Name");
            l_paymentRequisitMngParams.setAccountAttribute("0");
            l_paymentRequisitMngParams.setCalcDate(WEB3DateUtility.getDate("20070728","yyyyMMdd"));
            l_paymentRequisitMngParams.setCreatedTimestamp(WEB3DateUtility.getDate("20070728","yyyyMMdd"));
            l_paymentRequisitMngParams.setLastUpdatedTimestamp(WEB3DateUtility.getDate("20070728","yyyyMMdd"));
            TestDBUtility.insertWithDel(l_paymentRequisitMngParams);

            SubAccountParams l_subAccountParams = new SubAccountParams();
            l_processor.doDeleteAllQuery(SubAccountRow.TYPE);
            l_subAccountParams = TestDBUtility.getSubAccountRow();
            l_subAccountParams.setAccountId(333812512246L);
            l_subAccountParams.setSubAccountType(SubAccountTypeEnum.EQUITY_MARGIN_SUB_ACCOUNT);
            TestDBUtility.insertWithDel(l_subAccountParams);

            BranchPreferencesParams l_branchPreferencesParams = new BranchPreferencesParams();
            l_processor.doDeleteAllQuery(BranchPreferencesRow.TYPE);
            l_branchPreferencesParams = TestDBUtility.getBranchPreferencesRow();
            l_branchPreferencesParams.setName("0");
            l_branchPreferencesParams.setBranchId(33381);
            TestDBUtility.insertWithDel(l_branchPreferencesParams);

            l_processor.doDeleteAllQuery(SystemPreferencesRow.TYPE);

            OrderexecutionEndParams l_qrderexecutionEndParams = new OrderexecutionEndParams();
            l_processor.doDeleteAllQuery(OrderexecutionEndRow.TYPE);
            l_qrderexecutionEndParams.setInstitutionCode("0D");
            l_qrderexecutionEndParams.setProductType(ProductTypeEnum.EQUITY);
            l_qrderexecutionEndParams.setFutureOptionDiv("0");
            l_qrderexecutionEndParams.setCreatedTimestamp(WEB3DateUtility.getDate("20070728","yyyyMMdd"));
            l_qrderexecutionEndParams.setLastUpdatedTimestamp(WEB3DateUtility.getDate("20070728","yyyyMMdd"));
            l_qrderexecutionEndParams.setOrderexecutionEndType("0");
            TestDBUtility.insertWithDel(l_qrderexecutionEndParams);

            TradingpowerCalcConditionParams l_tradingpowerCalcConditionParams = new TradingpowerCalcConditionParams();
            l_processor.doDeleteAllQuery(TradingpowerCalcConditionRow.TYPE);
            l_tradingpowerCalcConditionParams = TestDBUtility.getTradingpowerCalcConditionRow();
            l_tradingpowerCalcConditionParams.setAccountId(333812512246L);
            l_tradingpowerCalcConditionParams.setAdditionalDepositStop("1");
            TestDBUtility.insertWithDel(l_tradingpowerCalcConditionParams);

            TpCalcResultMarginParams l_tpCalcResultMarginParams = new TpCalcResultMarginParams();
            l_processor.doDeleteAllQuery(TpCalcResultMarginRow.TYPE);
            l_tpCalcResultMarginParams = TestDBUtility.getTpCalcResultMarginRow();
            l_tpCalcResultMarginParams.setCreatedTimestamp(WEB3DateUtility.getDate("20070728","yyyyMMdd"));
            TestDBUtility.insertWithDel(l_tpCalcResultMarginParams);

            l_processor.doDeleteAllQuery(ProcessManagementRow.TYPE);

            TpCalcResultMarginDetailParams l_tpCalcResultMarginDetailParams = new TpCalcResultMarginDetailParams();
            l_processor.doDeleteAllQuery(TpCalcResultMarginDetailRow.TYPE);
            l_tpCalcResultMarginDetailParams = TestDBUtility.getTpCalcResultMarginDetailRow();
            TestDBUtility.insertWithDel(l_tpCalcResultMarginDetailParams);

            TpCashBalanceParams l_tpCashBalanceParams = new TpCashBalanceParams();
            l_processor.doDeleteAllQuery(TpCashBalanceRow.TYPE);
            l_tpCashBalanceParams.setAccountId(333812512246L);
            l_tpCashBalanceParams.setSubAccountId(33381251220301L);
            TestDBUtility.insertWithDel(l_tpCashBalanceParams);

            AioOrderUnitParams l_aioOrderUnitParams = new AioOrderUnitParams();
            l_processor.doDeleteAllQuery(AioOrderUnitRow.TYPE);
            l_aioOrderUnitParams = TestDBUtility.getAioOrderUnitRow();
            l_aioOrderUnitParams.setAccountId(333812512246L);
            l_aioOrderUnitParams.setSubAccountId(33381251220301L);
            l_aioOrderUnitParams.setOrderType(OrderTypeEnum.FROM_DEPOSIT_AMOUNT_MARGIN_GUARANTEE);
            l_aioOrderUnitParams.setDeliveryDate(WEB3DateUtility.getDate("20070728","yyyyMMdd"));
            l_aioOrderUnitParams.setOrderStatus(OrderStatusEnum.ACCEPTED);
            TestDBUtility.insertWithDel(l_aioOrderUnitParams);
            l_aioOrderUnitParams.setOrderUnitId(0L);
            l_aioOrderUnitParams.setOrderType(OrderTypeEnum.MARGIN_GUARANTEE_FROM_DEPOSIT_AMOUNT);
            TestDBUtility.insertWithDel(l_aioOrderUnitParams);

            //é¿çs
            WEB3TPPaymentRequisitionManageServiceImpl l_service = new WEB3TPPaymentRequisitionManageServiceImpl();
            String l_str = l_service.getLackCashOccurSituation(new WEB3GentradeMainAccount(l_mainAccountParams));
            //î‰är
            assertEquals("2", l_str);
        }
        catch (Exception l_ex)
        {
            log.debug(STR_METHOD_NAME,l_ex);
            fail();
        }
        log.exiting(TEST_END + STR_METHOD_NAME);
    }
    
    /**
     * Åh1Åh(ïsë´ã‡î≠ê∂<åªï®å⁄ãq>)Çï‘ãp
     */
    public void test_getLackCashOccurSituation_0002()
    {
        final String STR_METHOD_NAME = "test_getLackCashOccurSituation_0002()";
        log.entering(TEST_START + STR_METHOD_NAME);

        try
        {
            
            Calendar l_calendar = Calendar.getInstance();
            l_calendar.set(Calendar.YEAR,2007);
            l_calendar.set(Calendar.MONTH,2);
            l_calendar.set(Calendar.DAY_OF_MONTH,14);
            l_calendar.set(Calendar.HOUR_OF_DAY,15);
            l_calendar.set(Calendar.MINUTE,00);
            l_calendar.set(Calendar.SECOND,01);

            Timestamp l_tsBizDate = new Timestamp(l_calendar.getTimeInMillis());

            ThreadLocalSystemAttributesRegistry.setAttribute(
                WEB3GentradeTradingTimeManagement.TIMESTAMP_TAG, l_tsBizDate);

            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                "com.fitechlabs.xtrade.plugin.tc.gentrade.stdimpls.TradingSystemImpl",
                "getSystemTimestamp",
                new Class[] {},
                l_tsBizDate);
            
            
//            //ÉXÉ^ÉeÉBÉbÉN
//            Calendar l_calendar =  Calendar.getInstance();
//            l_calendar.set(2007, 7-1, 28);
//            Timestamp l_tsAppliyDate = new Timestamp(l_calendar.getTimeInMillis());
//            ThreadLocalSystemAttributesRegistry.setAttribute(
//                "xblocks.gtl.attributes.systemtimestamp",
//                l_tsAppliyDate);

            WEB3GentradeTradingClendarContext l_clendarContext = new WEB3GentradeTradingClendarContext();
//            l_clendarContext
            ThreadLocalSystemAttributesRegistry.setAttribute("web3.tradingcalendarcontext", l_clendarContext);

            //DB
            MainAccountParams l_mainAccountParams = new MainAccountParams();
            QueryProcessor l_processor = Processors.getDefaultProcessor();
            l_processor.doDeleteAllQuery(MainAccountRow.TYPE);
            l_mainAccountParams = TestDBUtility.getMainAccountRow();
            l_mainAccountParams.setMarginSysAccOpenDiv("0");    //the third case
            l_mainAccountParams.setSecuredLoanSecAccOpenDiv("0");
            TestDBUtility.insertWithDel(l_mainAccountParams);

            InstitutionParams l_institutionParams =  new InstitutionParams();
            l_processor.doDeleteAllQuery(InstitutionRow.TYPE);
            l_institutionParams = TestDBUtility.getInstitutionRow();
            l_institutionParams.setAssetEvaluation("0");
            l_institutionParams.setMaximumAssessment(1L);
            TestDBUtility.insertWithDel(l_institutionParams);

            BranchParams l_branchParams =  new BranchParams();
            l_processor.doDeleteAllQuery(BranchRow.TYPE);
            l_branchParams = TestDBUtility.getBranchRow();
            TestDBUtility.insertWithDel(l_branchParams);

            PaymentRequisitMngParams l_paymentRequisitMngParams = new PaymentRequisitMngParams();
            l_processor.doDeleteAllQuery(PaymentRequisitMngRow.TYPE);
            l_paymentRequisitMngParams.setAccountId(333812512246L);
            l_paymentRequisitMngParams.setAccountCode("2512246");
            l_paymentRequisitMngParams.setBranchCode("381");
            l_paymentRequisitMngParams.setInstitutionCode("0D");
            l_paymentRequisitMngParams.setFamilyName("Family Name");
            l_paymentRequisitMngParams.setAccountAttribute("0");
            l_paymentRequisitMngParams.setCalcDate(WEB3DateUtility.getDate("20070728","yyyyMMdd"));
            l_paymentRequisitMngParams.setCreatedTimestamp(WEB3DateUtility.getDate("20070728","yyyyMMdd"));
            l_paymentRequisitMngParams.setLastUpdatedTimestamp(WEB3DateUtility.getDate("20070728","yyyyMMdd"));
            TestDBUtility.insertWithDel(l_paymentRequisitMngParams);

            SubAccountParams l_subAccountParams = new SubAccountParams();
            l_processor.doDeleteAllQuery(SubAccountRow.TYPE);
            l_subAccountParams = TestDBUtility.getSubAccountRow();
            l_subAccountParams.setAccountId(333812512246L);
            l_subAccountParams.setSubAccountType(SubAccountTypeEnum.EQUITY_SUB_ACCOUNT);
            TestDBUtility.insertWithDel(l_subAccountParams);

            BranchPreferencesParams l_branchPreferencesParams = new BranchPreferencesParams();
            l_processor.doDeleteAllQuery(BranchPreferencesRow.TYPE);
            l_branchPreferencesParams = TestDBUtility.getBranchPreferencesRow();
            l_branchPreferencesParams.setName("0");
            l_branchPreferencesParams.setBranchId(33381);
            TestDBUtility.insertWithDel(l_branchPreferencesParams);

            l_processor.doDeleteAllQuery(SystemPreferencesRow.TYPE);

            OrderexecutionEndParams l_qrderexecutionEndParams = new OrderexecutionEndParams();
            l_processor.doDeleteAllQuery(OrderexecutionEndRow.TYPE);
            l_qrderexecutionEndParams.setInstitutionCode("0D");
            l_qrderexecutionEndParams.setProductType(ProductTypeEnum.EQUITY);
            l_qrderexecutionEndParams.setFutureOptionDiv("0");
            l_qrderexecutionEndParams.setCreatedTimestamp(WEB3DateUtility.getDate("20070728","yyyyMMdd"));
            l_qrderexecutionEndParams.setLastUpdatedTimestamp(WEB3DateUtility.getDate("20070728","yyyyMMdd"));
            l_qrderexecutionEndParams.setOrderexecutionEndType("0");
            TestDBUtility.insertWithDel(l_qrderexecutionEndParams);

            TradingpowerCalcConditionParams l_tradingpowerCalcConditionParams = new TradingpowerCalcConditionParams();
            l_processor.doDeleteAllQuery(TradingpowerCalcConditionRow.TYPE);
            l_tradingpowerCalcConditionParams = TestDBUtility.getTradingpowerCalcConditionRow();
            l_tradingpowerCalcConditionParams.setAccountId(333812512246L);
            l_tradingpowerCalcConditionParams.setAdditionalDepositStop("1");
            TestDBUtility.insertWithDel(l_tradingpowerCalcConditionParams);

            TpCalcResultEquityParams l_tpCalcResultEquityParams = new TpCalcResultEquityParams();
            l_processor.doDeleteAllQuery(TpCalcResultEquityRow.TYPE);
            l_tpCalcResultEquityParams = TestDBUtility.getTpCalcResultEquityRow();
            l_tpCalcResultEquityParams.setCalcResultEquityId(333812512246L);
            l_tpCalcResultEquityParams.setAccountId(333812512246L);
            l_tpCalcResultEquityParams.setCreatedTimestamp(WEB3DateUtility.getDate("20070728","yyyyMMdd"));
            TestDBUtility.insertWithDel(l_tpCalcResultEquityParams);

            l_processor.doDeleteAllQuery(ProcessManagementRow.TYPE);

            TpCalcResultMarginDetailParams l_tpCalcResultMarginDetailParams = new TpCalcResultMarginDetailParams();
            l_processor.doDeleteAllQuery(TpCalcResultMarginDetailRow.TYPE);
            l_tpCalcResultMarginDetailParams = TestDBUtility.getTpCalcResultMarginDetailRow();
            TestDBUtility.insertWithDel(l_tpCalcResultMarginDetailParams);
            
            TpCalcResultEquityDetailParams l_resultDetailParams = new TpCalcResultEquityDetailParams();
            l_processor.doDeleteAllQuery(TpCalcResultEquityDetailRow.TYPE);
            l_resultDetailParams.setAccountId(333812512246L);
            l_resultDetailParams.setCalcResultEquityId(333812512246L);
            l_resultDetailParams.setCreatedTimestamp(Calendar.getInstance().getTime());
            l_resultDetailParams.setLastUpdatedTimestamp(Calendar.getInstance().getTime());
            
            TestDBUtility.insertWithDel(l_resultDetailParams);
//            l_resultDetailParams.setAccountId();

            TpCashBalanceParams l_tpCashBalanceParams = new TpCashBalanceParams();
            l_processor.doDeleteAllQuery(TpCashBalanceRow.TYPE);
            l_tpCashBalanceParams.setAccountId(333812512246L);
            l_tpCashBalanceParams.setSubAccountId(33381251220301L);
            TestDBUtility.insertWithDel(l_tpCashBalanceParams);

            AioOrderUnitParams l_aioOrderUnitParams = new AioOrderUnitParams();
            l_processor.doDeleteAllQuery(AioOrderUnitRow.TYPE);
            l_aioOrderUnitParams = TestDBUtility.getAioOrderUnitRow();
            l_aioOrderUnitParams.setAccountId(333812512246L);
            l_aioOrderUnitParams.setSubAccountId(33381251220301L);
            l_aioOrderUnitParams.setOrderType(OrderTypeEnum.FROM_DEPOSIT_AMOUNT_MARGIN_GUARANTEE);
            l_aioOrderUnitParams.setDeliveryDate(WEB3DateUtility.getDate("20070728","yyyyMMdd"));
            l_aioOrderUnitParams.setOrderStatus(OrderStatusEnum.ACCEPTED);
            TestDBUtility.insertWithDel(l_aioOrderUnitParams);
            l_aioOrderUnitParams.setOrderUnitId(0L);
            l_aioOrderUnitParams.setOrderType(OrderTypeEnum.MARGIN_GUARANTEE_FROM_DEPOSIT_AMOUNT);
            TestDBUtility.insertWithDel(l_aioOrderUnitParams);
            //é¿çs
            WEB3TPPaymentRequisitionManageServiceImpl l_service = new WEB3TPPaymentRequisitionManageServiceImpl();
            String l_str = l_service.getLackCashOccurSituation(new WEB3GentradeMainAccount(l_mainAccountParams));
            //î‰är
            assertEquals("1", l_str);
        }
        catch (Exception l_ex)
        {
            log.debug(STR_METHOD_NAME,l_ex);
            fail();
        }
        log.exiting(TEST_END + STR_METHOD_NAME);
    }
    
    /**
     * Åh0Åh(ïsë´ã‡ñ¢î≠ê∂)Çï‘ãp
     */
    public void test_getLackCashOccurSituation_0003()
    {
        final String STR_METHOD_NAME = "test_getLackCashOccurSituation_0003()";
        log.entering(TEST_START + STR_METHOD_NAME);

        try
        {
            
            Calendar l_calendar = Calendar.getInstance();
            l_calendar.set(Calendar.YEAR,2007);
            l_calendar.set(Calendar.MONTH,2);
            l_calendar.set(Calendar.DAY_OF_MONTH,14);
            l_calendar.set(Calendar.HOUR_OF_DAY,15);
            l_calendar.set(Calendar.MINUTE,00);
            l_calendar.set(Calendar.SECOND,01);

            Timestamp l_tsBizDate = new Timestamp(l_calendar.getTimeInMillis());

            ThreadLocalSystemAttributesRegistry.setAttribute(
                WEB3GentradeTradingTimeManagement.TIMESTAMP_TAG, l_tsBizDate);

            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                "com.fitechlabs.xtrade.plugin.tc.gentrade.stdimpls.TradingSystemImpl",
                "getSystemTimestamp",
                new Class[] {},
                l_tsBizDate);

            WEB3GentradeTradingClendarContext l_clendarContext = new WEB3GentradeTradingClendarContext();
            ThreadLocalSystemAttributesRegistry.setAttribute("web3.tradingcalendarcontext", l_clendarContext);

            //DB
            MainAccountParams l_mainAccountParams = new MainAccountParams();
            QueryProcessor l_processor = Processors.getDefaultProcessor();
            l_processor.doDeleteAllQuery(MainAccountRow.TYPE);
            l_mainAccountParams = TestDBUtility.getMainAccountRow();
            l_mainAccountParams.setMarginSysAccOpenDiv("1");    //the third case
            l_mainAccountParams.setSecuredLoanSecAccOpenDiv("0");
            TestDBUtility.insertWithDel(l_mainAccountParams);

            InstitutionParams l_institutionParams =  new InstitutionParams();
            l_processor.doDeleteAllQuery(InstitutionRow.TYPE);
            l_institutionParams = TestDBUtility.getInstitutionRow();
            l_institutionParams.setAssetEvaluation("0");
            l_institutionParams.setMaximumAssessment(1L);
            TestDBUtility.insertWithDel(l_institutionParams);

            BranchParams l_branchParams =  new BranchParams();
            l_processor.doDeleteAllQuery(BranchRow.TYPE);
            l_branchParams = TestDBUtility.getBranchRow();
            TestDBUtility.insertWithDel(l_branchParams);

            PaymentRequisitMngParams l_paymentRequisitMngParams = new PaymentRequisitMngParams();
            l_processor.doDeleteAllQuery(PaymentRequisitMngRow.TYPE);
            l_paymentRequisitMngParams.setAccountId(333812512246L);
            l_paymentRequisitMngParams.setAccountCode("2512246");
            l_paymentRequisitMngParams.setBranchCode("381");
            l_paymentRequisitMngParams.setInstitutionCode("0D");
            l_paymentRequisitMngParams.setFamilyName("Family Name");
            l_paymentRequisitMngParams.setAccountAttribute("0");
            l_paymentRequisitMngParams.setCalcDate(WEB3DateUtility.getDate("20070728","yyyyMMdd"));
            l_paymentRequisitMngParams.setCreatedTimestamp(WEB3DateUtility.getDate("20070728","yyyyMMdd"));
            l_paymentRequisitMngParams.setLastUpdatedTimestamp(WEB3DateUtility.getDate("20070728","yyyyMMdd"));
            TestDBUtility.insertWithDel(l_paymentRequisitMngParams);

            SubAccountParams l_subAccountParams = new SubAccountParams();
            l_processor.doDeleteAllQuery(SubAccountRow.TYPE);
            l_subAccountParams = TestDBUtility.getSubAccountRow();
            l_subAccountParams.setAccountId(333812512246L);
            l_subAccountParams.setSubAccountType(SubAccountTypeEnum.EQUITY_MARGIN_SUB_ACCOUNT);
            TestDBUtility.insertWithDel(l_subAccountParams);

            BranchPreferencesParams l_branchPreferencesParams = new BranchPreferencesParams();
            l_processor.doDeleteAllQuery(BranchPreferencesRow.TYPE);
            l_branchPreferencesParams = TestDBUtility.getBranchPreferencesRow();
            l_branchPreferencesParams.setName("0");
            l_branchPreferencesParams.setBranchId(33381);
            TestDBUtility.insertWithDel(l_branchPreferencesParams);

            l_processor.doDeleteAllQuery(SystemPreferencesRow.TYPE);

            OrderexecutionEndParams l_qrderexecutionEndParams = new OrderexecutionEndParams();
            l_processor.doDeleteAllQuery(OrderexecutionEndRow.TYPE);
            l_qrderexecutionEndParams.setInstitutionCode("0D");
            l_qrderexecutionEndParams.setProductType(ProductTypeEnum.EQUITY);
            l_qrderexecutionEndParams.setFutureOptionDiv("0");
            l_qrderexecutionEndParams.setCreatedTimestamp(WEB3DateUtility.getDate("20070728","yyyyMMdd"));
            l_qrderexecutionEndParams.setLastUpdatedTimestamp(WEB3DateUtility.getDate("20070728","yyyyMMdd"));
            l_qrderexecutionEndParams.setOrderexecutionEndType("0");
            TestDBUtility.insertWithDel(l_qrderexecutionEndParams);

            TradingpowerCalcConditionParams l_tradingpowerCalcConditionParams = new TradingpowerCalcConditionParams();
            l_processor.doDeleteAllQuery(TradingpowerCalcConditionRow.TYPE);
            l_tradingpowerCalcConditionParams = TestDBUtility.getTradingpowerCalcConditionRow();
            l_tradingpowerCalcConditionParams.setAccountId(333812512246L);
            l_tradingpowerCalcConditionParams.setAdditionalDepositStop("1");
            l_tradingpowerCalcConditionParams.setSpecialDebitAmount(0);//TODO
            TestDBUtility.insertWithDel(l_tradingpowerCalcConditionParams);

            TpCalcResultMarginParams l_tpCalcResultMarginParams = new TpCalcResultMarginParams();
            l_processor.doDeleteAllQuery(TpCalcResultMarginRow.TYPE);
//            l_tpCalcResultMarginParams = TestDBUtility.getTpCalcResultMarginRow();
            
            l_tpCalcResultMarginParams.setCalcResultMarginId(1001);
            l_tpCalcResultMarginParams.setAccountId(333812512246L);
            l_tpCalcResultMarginParams.setAccountBalance0(0);
            l_tpCalcResultMarginParams.setAccountBalance1(0);
            l_tpCalcResultMarginParams.setAccountBalance2(0);
            l_tpCalcResultMarginParams.setAccountBalance3(0);
            l_tpCalcResultMarginParams.setAccountBalance4(0);
            l_tpCalcResultMarginParams.setAccountBalance5(0);
            l_tpCalcResultMarginParams.setTodayExecutedAmount0(0);
            l_tpCalcResultMarginParams.setTodayExecutedAmount1(0);
            l_tpCalcResultMarginParams.setTodayExecutedAmount2(0);
            l_tpCalcResultMarginParams.setTodayExecutedAmount3(0);
            l_tpCalcResultMarginParams.setTodayExecutedAmount4(0);
            l_tpCalcResultMarginParams.setTodayExecutedAmount5(0);
            l_tpCalcResultMarginParams.setTodayUnexecutedAmount0(0);
            l_tpCalcResultMarginParams.setTodayUnexecutedAmount1(0);
            l_tpCalcResultMarginParams.setTodayUnexecutedAmount2(0);
            l_tpCalcResultMarginParams.setTodayUnexecutedAmount3(0);
            l_tpCalcResultMarginParams.setTodayUnexecutedAmount4(0);

            l_tpCalcResultMarginParams.setTodayUnexecutedAmount5(0);
            l_tpCalcResultMarginParams.setDayTradeRestraint0(0);
            l_tpCalcResultMarginParams.setDayTradeRestraint1(0);
            l_tpCalcResultMarginParams.setDayTradeRestraint2(0);
            l_tpCalcResultMarginParams.setDayTradeRestraint3(0);
            l_tpCalcResultMarginParams.setDayTradeRestraint4(0);
            l_tpCalcResultMarginParams.setOtherRestraint0(0);
            l_tpCalcResultMarginParams.setOtherRestraint1(0);
            l_tpCalcResultMarginParams.setOtherRestraint2(0);
            l_tpCalcResultMarginParams.setOtherRestraint3(0);
            l_tpCalcResultMarginParams.setOtherRestraint4(0);
            l_tpCalcResultMarginParams.setOtherRestraint5(0);
            l_tpCalcResultMarginParams.setSubstituteSecurityAsset0(0);
            l_tpCalcResultMarginParams.setSubstituteSecurityAsset1(0);
            l_tpCalcResultMarginParams.setSubstituteSecurityAsset2(0);
            l_tpCalcResultMarginParams.setSubstituteSecurityAsset3(0);
            l_tpCalcResultMarginParams.setSubstituteSecurityAsset4(0);
            l_tpCalcResultMarginParams.setSubstituteSecurityAsset5(0);
            l_tpCalcResultMarginParams.setContractAmount0(0);
            l_tpCalcResultMarginParams.setContractAmount1(0);
            l_tpCalcResultMarginParams.setContractAmount2(0);
            l_tpCalcResultMarginParams.setContractAmount3(0);
            l_tpCalcResultMarginParams.setContractAmount4(0);
            l_tpCalcResultMarginParams.setContractAmount5(0);
            l_tpCalcResultMarginParams.setDayRepayContractAmount0(0);
            l_tpCalcResultMarginParams.setDayRepayContractAmount1(0);
            l_tpCalcResultMarginParams.setDayRepayContractAmount2(0);
            l_tpCalcResultMarginParams.setDayRepayContractAmount3(0);
            l_tpCalcResultMarginParams.setDayRepayContractAmount4(0);
            l_tpCalcResultMarginParams.setDayRepayContractAmount5(0);
            l_tpCalcResultMarginParams.setMarginDeposit0(0);
            l_tpCalcResultMarginParams.setMarginDeposit1(0);
            l_tpCalcResultMarginParams.setMarginDeposit2(0);
            l_tpCalcResultMarginParams.setMarginDeposit3(0);
            l_tpCalcResultMarginParams.setMarginDeposit4(0);
            l_tpCalcResultMarginParams.setMarginDeposit5(0);
            l_tpCalcResultMarginParams.setCashMarginDeposit0(0);
            l_tpCalcResultMarginParams.setCashMarginDeposit1(0);
            l_tpCalcResultMarginParams.setCashMarginDeposit2(0);
            l_tpCalcResultMarginParams.setCashMarginDeposit3(0);
            l_tpCalcResultMarginParams.setCashMarginDeposit4(0);
            l_tpCalcResultMarginParams.setCashMarginDeposit5(0);
            l_tpCalcResultMarginParams.setContractAssetProfitLoss(0);
            l_tpCalcResultMarginParams.setUndeliContractAmount0(0);
            l_tpCalcResultMarginParams.setUndeliContractAmount1(0);
            l_tpCalcResultMarginParams.setUndeliContractAmount2(0);
            l_tpCalcResultMarginParams.setUndeliContractAmount3(0);
            l_tpCalcResultMarginParams.setUndeliMarginDeposit0(0);
            l_tpCalcResultMarginParams.setUndeliMarginDeposit1(0);
            l_tpCalcResultMarginParams.setUndeliMarginDeposit2(0);
            l_tpCalcResultMarginParams.setUndeliMarginDeposit3(0);
            l_tpCalcResultMarginParams.setUndeliCashMarginDeposit0(0);
            l_tpCalcResultMarginParams.setUndeliCashMarginDeposit1(0);
            l_tpCalcResultMarginParams.setUndeliCashMarginDeposit2(0);
            l_tpCalcResultMarginParams.setUndeliCashMarginDeposit3(0);
            l_tpCalcResultMarginParams.setUndeliContractLoss0(0);
            l_tpCalcResultMarginParams.setUndeliContractLoss1(0);
            l_tpCalcResultMarginParams.setUndeliContractLoss2(0);
            l_tpCalcResultMarginParams.setUndeliContractLoss3(0);
            l_tpCalcResultMarginParams.setUndeliContractProfit0(0);
            l_tpCalcResultMarginParams.setUndeliContractProfit1(0);
            l_tpCalcResultMarginParams.setUndeliContractProfit2(0);
            l_tpCalcResultMarginParams.setUndeliContractProfit3(0);
            l_tpCalcResultMarginParams.setContractTotalCost(0);
            l_tpCalcResultMarginParams.setCreatedTimestamp(Calendar.getInstance().getTime());
            l_tpCalcResultMarginParams.setLastUpdatedTimestamp(Calendar.getInstance().getTime());
            l_tpCalcResultMarginParams.setMarkToMarketDiv(WEB3TPMarkToMarketDivDef.NORMAL);
            l_tpCalcResultMarginParams.setCreatedTimestamp(WEB3DateUtility.getDate("20070728","yyyyMMdd"));
            TestDBUtility.insertWithDel(l_tpCalcResultMarginParams);

            l_processor.doDeleteAllQuery(ProcessManagementRow.TYPE);

            TpCalcResultMarginDetailParams l_tpCalcResultMarginDetailParams = new TpCalcResultMarginDetailParams();
            l_processor.doDeleteAllQuery(TpCalcResultMarginDetailRow.TYPE);
            l_tpCalcResultMarginDetailParams = TestDBUtility.getTpCalcResultMarginDetailRow();
            TestDBUtility.insertWithDel(l_tpCalcResultMarginDetailParams);

            TpCashBalanceParams l_tpCashBalanceParams = new TpCashBalanceParams();
            l_processor.doDeleteAllQuery(TpCashBalanceRow.TYPE);
            l_tpCashBalanceParams.setAccountId(333812512246L);
            l_tpCashBalanceParams.setSubAccountId(33381251220301L);
            TestDBUtility.insertWithDel(l_tpCashBalanceParams);

            AioOrderUnitParams l_aioOrderUnitParams = new AioOrderUnitParams();
            l_processor.doDeleteAllQuery(AioOrderUnitRow.TYPE);
            l_aioOrderUnitParams = TestDBUtility.getAioOrderUnitRow();
            l_aioOrderUnitParams.setAccountId(333812512246L);
            l_aioOrderUnitParams.setSubAccountId(33381251220301L);
            l_aioOrderUnitParams.setOrderType(OrderTypeEnum.FROM_DEPOSIT_AMOUNT_MARGIN_GUARANTEE);
            l_aioOrderUnitParams.setDeliveryDate(WEB3DateUtility.getDate("20070728","yyyyMMdd"));
            l_aioOrderUnitParams.setOrderStatus(OrderStatusEnum.ACCEPTED);
            TestDBUtility.insertWithDel(l_aioOrderUnitParams);
            l_aioOrderUnitParams.setOrderUnitId(0L);
            l_aioOrderUnitParams.setOrderType(OrderTypeEnum.MARGIN_GUARANTEE_FROM_DEPOSIT_AMOUNT);
            TestDBUtility.insertWithDel(l_aioOrderUnitParams);

            //é¿çs
            WEB3TPPaymentRequisitionManageServiceImpl l_service = new WEB3TPPaymentRequisitionManageServiceImpl();
            String l_str = l_service.getLackCashOccurSituation(new WEB3GentradeMainAccount(l_mainAccountParams));
            //î‰är
            assertEquals("0", l_str);
        }
        catch (Exception l_ex)
        {
            log.debug(STR_METHOD_NAME,l_ex);
            fail();
        }
        log.exiting(TEST_END + STR_METHOD_NAME);
    }
    
    /**
     * Åh0Åh(í«èÿñ¢î≠ê∂)Çï‘ãp
     */
    public void test_getAdditionalMarginSituation_0001()
    {
        final String STR_METHOD_NAME = "test_getAdditionalMarginSituation_0001()";
        log.entering(TEST_START + STR_METHOD_NAME);

        try
        {
            
            Calendar l_calendar = Calendar.getInstance();
            l_calendar.set(Calendar.YEAR,2007);
            l_calendar.set(Calendar.MONTH,2);
            l_calendar.set(Calendar.DAY_OF_MONTH,14);
            l_calendar.set(Calendar.HOUR_OF_DAY,15);
            l_calendar.set(Calendar.MINUTE,00);
            l_calendar.set(Calendar.SECOND,01);

            Timestamp l_tsBizDate = new Timestamp(l_calendar.getTimeInMillis());

            ThreadLocalSystemAttributesRegistry.setAttribute(
                WEB3GentradeTradingTimeManagement.TIMESTAMP_TAG, l_tsBizDate);

            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                "com.fitechlabs.xtrade.plugin.tc.gentrade.stdimpls.TradingSystemImpl",
                "getSystemTimestamp",
                new Class[] {},
                l_tsBizDate);

            WEB3GentradeTradingClendarContext l_clendarContext = new WEB3GentradeTradingClendarContext();
            ThreadLocalSystemAttributesRegistry.setAttribute("web3.tradingcalendarcontext", l_clendarContext);

            //DB
            MainAccountParams l_mainAccountParams = new MainAccountParams();
            QueryProcessor l_processor = Processors.getDefaultProcessor();
            l_processor.doDeleteAllQuery(MainAccountRow.TYPE);
            l_mainAccountParams = TestDBUtility.getMainAccountRow();
            l_mainAccountParams.setMarginSysAccOpenDiv("1");    //the third case
            l_mainAccountParams.setSecuredLoanSecAccOpenDiv("0");
            TestDBUtility.insertWithDel(l_mainAccountParams);

            InstitutionParams l_institutionParams =  new InstitutionParams();
            l_processor.doDeleteAllQuery(InstitutionRow.TYPE);
            l_institutionParams = TestDBUtility.getInstitutionRow();
            l_institutionParams.setAssetEvaluation("0");
            l_institutionParams.setMaximumAssessment(1L);
            TestDBUtility.insertWithDel(l_institutionParams);

            BranchParams l_branchParams =  new BranchParams();
            l_processor.doDeleteAllQuery(BranchRow.TYPE);
            l_branchParams = TestDBUtility.getBranchRow();
            TestDBUtility.insertWithDel(l_branchParams);

            PaymentRequisitMngParams l_paymentRequisitMngParams = new PaymentRequisitMngParams();
            l_processor.doDeleteAllQuery(PaymentRequisitMngRow.TYPE);
            l_paymentRequisitMngParams.setAccountId(333812512246L);
            l_paymentRequisitMngParams.setAccountCode("2512246");
            l_paymentRequisitMngParams.setBranchCode("381");
            l_paymentRequisitMngParams.setInstitutionCode("0D");
            l_paymentRequisitMngParams.setFamilyName("Family Name");
            l_paymentRequisitMngParams.setAccountAttribute("0");
            l_paymentRequisitMngParams.setCalcDate(WEB3DateUtility.getDate("20070728","yyyyMMdd"));
            l_paymentRequisitMngParams.setCreatedTimestamp(WEB3DateUtility.getDate("20070728","yyyyMMdd"));
            l_paymentRequisitMngParams.setLastUpdatedTimestamp(WEB3DateUtility.getDate("20070728","yyyyMMdd"));
            TestDBUtility.insertWithDel(l_paymentRequisitMngParams);

            SubAccountParams l_subAccountParams = new SubAccountParams();
            l_processor.doDeleteAllQuery(SubAccountRow.TYPE);
            l_subAccountParams = TestDBUtility.getSubAccountRow();
            l_subAccountParams.setAccountId(333812512246L);
            l_subAccountParams.setSubAccountType(SubAccountTypeEnum.EQUITY_MARGIN_SUB_ACCOUNT);
            TestDBUtility.insertWithDel(l_subAccountParams);

            BranchPreferencesParams l_branchPreferencesParams = new BranchPreferencesParams();
            l_processor.doDeleteAllQuery(BranchPreferencesRow.TYPE);
            l_branchPreferencesParams = TestDBUtility.getBranchPreferencesRow();
            l_branchPreferencesParams.setName("0");
            l_branchPreferencesParams.setBranchId(33381);
            TestDBUtility.insertWithDel(l_branchPreferencesParams);

            l_processor.doDeleteAllQuery(SystemPreferencesRow.TYPE);

            OrderexecutionEndParams l_qrderexecutionEndParams = new OrderexecutionEndParams();
            l_processor.doDeleteAllQuery(OrderexecutionEndRow.TYPE);
            l_qrderexecutionEndParams.setInstitutionCode("0D");
            l_qrderexecutionEndParams.setProductType(ProductTypeEnum.EQUITY);
            l_qrderexecutionEndParams.setFutureOptionDiv("0");
            l_qrderexecutionEndParams.setCreatedTimestamp(WEB3DateUtility.getDate("20070728","yyyyMMdd"));
            l_qrderexecutionEndParams.setLastUpdatedTimestamp(WEB3DateUtility.getDate("20070728","yyyyMMdd"));
            l_qrderexecutionEndParams.setOrderexecutionEndType("0");
            TestDBUtility.insertWithDel(l_qrderexecutionEndParams);

            TradingpowerCalcConditionParams l_tradingpowerCalcConditionParams = new TradingpowerCalcConditionParams();
            l_processor.doDeleteAllQuery(TradingpowerCalcConditionRow.TYPE);
            l_tradingpowerCalcConditionParams = TestDBUtility.getTradingpowerCalcConditionRow();
            l_tradingpowerCalcConditionParams.setAccountId(333812512246L);
            l_tradingpowerCalcConditionParams.setAdditionalDepositStop("1");
            TestDBUtility.insertWithDel(l_tradingpowerCalcConditionParams);

            TpCalcResultMarginParams l_tpCalcResultMarginParams = new TpCalcResultMarginParams();
            l_processor.doDeleteAllQuery(TpCalcResultMarginRow.TYPE);
            l_tpCalcResultMarginParams = TestDBUtility.getTpCalcResultMarginRow();
            l_tpCalcResultMarginParams.setCreatedTimestamp(WEB3DateUtility.getDate("20070728","yyyyMMdd"));
            TestDBUtility.insertWithDel(l_tpCalcResultMarginParams);

            l_processor.doDeleteAllQuery(ProcessManagementRow.TYPE);

            TpCalcResultMarginDetailParams l_tpCalcResultMarginDetailParams = new TpCalcResultMarginDetailParams();
            l_processor.doDeleteAllQuery(TpCalcResultMarginDetailRow.TYPE);
            l_tpCalcResultMarginDetailParams = TestDBUtility.getTpCalcResultMarginDetailRow();
            TestDBUtility.insertWithDel(l_tpCalcResultMarginDetailParams);

            TpCashBalanceParams l_tpCashBalanceParams = new TpCashBalanceParams();
            l_processor.doDeleteAllQuery(TpCashBalanceRow.TYPE);
            l_tpCashBalanceParams.setAccountId(333812512246L);
            l_tpCashBalanceParams.setSubAccountId(33381251220301L);
            TestDBUtility.insertWithDel(l_tpCashBalanceParams);

            AioOrderUnitParams l_aioOrderUnitParams = new AioOrderUnitParams();
            l_processor.doDeleteAllQuery(AioOrderUnitRow.TYPE);
            l_aioOrderUnitParams = TestDBUtility.getAioOrderUnitRow();
            l_aioOrderUnitParams.setAccountId(333812512246L);
            l_aioOrderUnitParams.setSubAccountId(33381251220301L);
            l_aioOrderUnitParams.setOrderType(OrderTypeEnum.FROM_DEPOSIT_AMOUNT_MARGIN_GUARANTEE);
            l_aioOrderUnitParams.setDeliveryDate(WEB3DateUtility.getDate("20070728","yyyyMMdd"));
            l_aioOrderUnitParams.setOrderStatus(OrderStatusEnum.ACCEPTED);
            TestDBUtility.insertWithDel(l_aioOrderUnitParams);
            l_aioOrderUnitParams.setOrderUnitId(0L);
            l_aioOrderUnitParams.setOrderType(OrderTypeEnum.MARGIN_GUARANTEE_FROM_DEPOSIT_AMOUNT);
            TestDBUtility.insertWithDel(l_aioOrderUnitParams);

            //é¿çs
            WEB3TPPaymentRequisitionManageServiceImpl l_service = new WEB3TPPaymentRequisitionManageServiceImpl();
            String l_str = l_service.getAdditionalMarginSituation(new WEB3GentradeMainAccount(l_mainAccountParams), false);
            //î‰är
            assertEquals("0", l_str);
        }
        catch (Exception l_ex)
        {
            log.debug(STR_METHOD_NAME,l_ex);
            fail();
        }
        log.exiting(TEST_END + STR_METHOD_NAME);
    }
    
    /**
     * Åh2Åh(ëÊìÒêÖèÄí«èÿî≠ê∂)Çï‘ãp
     */
    public void test_getAdditionalMarginSituation_0002()
    {
        final String STR_METHOD_NAME = "test_getAdditionalMarginSituation_0002()";
        log.entering(TEST_START + STR_METHOD_NAME);

        try
        {
            
            Calendar l_calendar = Calendar.getInstance();
            l_calendar.set(Calendar.YEAR,2007);
            l_calendar.set(Calendar.MONTH,2);
            l_calendar.set(Calendar.DAY_OF_MONTH,14);
            l_calendar.set(Calendar.HOUR_OF_DAY,15);
            l_calendar.set(Calendar.MINUTE,00);
            l_calendar.set(Calendar.SECOND,01);

            Timestamp l_tsBizDate = new Timestamp(l_calendar.getTimeInMillis());

            ThreadLocalSystemAttributesRegistry.setAttribute(
                WEB3GentradeTradingTimeManagement.TIMESTAMP_TAG, l_tsBizDate);

            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                "com.fitechlabs.xtrade.plugin.tc.gentrade.stdimpls.TradingSystemImpl",
                "getSystemTimestamp",
                new Class[] {},
                l_tsBizDate);

            WEB3GentradeTradingClendarContext l_clendarContext = new WEB3GentradeTradingClendarContext();
            ThreadLocalSystemAttributesRegistry.setAttribute("web3.tradingcalendarcontext", l_clendarContext);

            //DB
            MainAccountParams l_mainAccountParams = new MainAccountParams();
            QueryProcessor l_processor = Processors.getDefaultProcessor();
            l_processor.doDeleteAllQuery(MainAccountRow.TYPE);
            l_mainAccountParams = TestDBUtility.getMainAccountRow();
            l_mainAccountParams.setMarginSysAccOpenDiv("1");    //the third case
            l_mainAccountParams.setSecuredLoanSecAccOpenDiv("0");
            TestDBUtility.insertWithDel(l_mainAccountParams);

            InstitutionParams l_institutionParams =  new InstitutionParams();
            l_processor.doDeleteAllQuery(InstitutionRow.TYPE);
            l_institutionParams = TestDBUtility.getInstitutionRow();
            l_institutionParams.setAssetEvaluation("0");
            l_institutionParams.setMaximumAssessment(1L);
            TestDBUtility.insertWithDel(l_institutionParams);

            BranchParams l_branchParams =  new BranchParams();
            l_processor.doDeleteAllQuery(BranchRow.TYPE);
            l_branchParams = TestDBUtility.getBranchRow();
            TestDBUtility.insertWithDel(l_branchParams);

            PaymentRequisitMngParams l_paymentRequisitMngParams = new PaymentRequisitMngParams();
            l_processor.doDeleteAllQuery(PaymentRequisitMngRow.TYPE);
            l_paymentRequisitMngParams.setAccountId(333812512246L);
            l_paymentRequisitMngParams.setAccountCode("2512246");
            l_paymentRequisitMngParams.setBranchCode("381");
            l_paymentRequisitMngParams.setInstitutionCode("0D");
            l_paymentRequisitMngParams.setFamilyName("Family Name");
            l_paymentRequisitMngParams.setAccountAttribute("0");
            l_paymentRequisitMngParams.setCalcDate(WEB3DateUtility.getDate("20070728","yyyyMMdd"));
            l_paymentRequisitMngParams.setCreatedTimestamp(WEB3DateUtility.getDate("20070728","yyyyMMdd"));
            l_paymentRequisitMngParams.setLastUpdatedTimestamp(WEB3DateUtility.getDate("20070728","yyyyMMdd"));
            l_paymentRequisitMngParams.setFirstDepositAmount(23);
            l_paymentRequisitMngParams.setSecondDeposit1(2);//TODO
            TestDBUtility.insertWithDel(l_paymentRequisitMngParams);

            SubAccountParams l_subAccountParams = new SubAccountParams();
            l_processor.doDeleteAllQuery(SubAccountRow.TYPE);
            l_subAccountParams = TestDBUtility.getSubAccountRow();
            l_subAccountParams.setAccountId(333812512246L);
            l_subAccountParams.setSubAccountType(SubAccountTypeEnum.EQUITY_MARGIN_SUB_ACCOUNT);
            TestDBUtility.insertWithDel(l_subAccountParams);

            BranchPreferencesParams l_branchPreferencesParams = new BranchPreferencesParams();
            l_processor.doDeleteAllQuery(BranchPreferencesRow.TYPE);
            l_branchPreferencesParams = TestDBUtility.getBranchPreferencesRow();
            l_branchPreferencesParams.setName("0");
            l_branchPreferencesParams.setBranchId(33381);
            TestDBUtility.insertWithDel(l_branchPreferencesParams);

            l_processor.doDeleteAllQuery(SystemPreferencesRow.TYPE);

            OrderexecutionEndParams l_qrderexecutionEndParams = new OrderexecutionEndParams();
            l_processor.doDeleteAllQuery(OrderexecutionEndRow.TYPE);
            l_qrderexecutionEndParams.setInstitutionCode("0D");
            l_qrderexecutionEndParams.setProductType(ProductTypeEnum.EQUITY);
            l_qrderexecutionEndParams.setFutureOptionDiv("0");
            l_qrderexecutionEndParams.setCreatedTimestamp(WEB3DateUtility.getDate("20070728","yyyyMMdd"));
            l_qrderexecutionEndParams.setLastUpdatedTimestamp(WEB3DateUtility.getDate("20070728","yyyyMMdd"));
            l_qrderexecutionEndParams.setOrderexecutionEndType("0");
            TestDBUtility.insertWithDel(l_qrderexecutionEndParams);

            TradingpowerCalcConditionParams l_tradingpowerCalcConditionParams = new TradingpowerCalcConditionParams();
            l_processor.doDeleteAllQuery(TradingpowerCalcConditionRow.TYPE);
            l_tradingpowerCalcConditionParams = TestDBUtility.getTradingpowerCalcConditionRow();
            l_tradingpowerCalcConditionParams.setAccountId(333812512246L);
            l_tradingpowerCalcConditionParams.setAdditionalDepositStop("1");
            TestDBUtility.insertWithDel(l_tradingpowerCalcConditionParams);

            TpCalcResultMarginParams l_tpCalcResultMarginParams = new TpCalcResultMarginParams();
            l_processor.doDeleteAllQuery(TpCalcResultMarginRow.TYPE);
            l_tpCalcResultMarginParams = TestDBUtility.getTpCalcResultMarginRow();
            l_tpCalcResultMarginParams.setCreatedTimestamp(WEB3DateUtility.getDate("20070728","yyyyMMdd"));
            TestDBUtility.insertWithDel(l_tpCalcResultMarginParams);

            l_processor.doDeleteAllQuery(ProcessManagementRow.TYPE);

            TpCalcResultMarginDetailParams l_tpCalcResultMarginDetailParams = new TpCalcResultMarginDetailParams();
            l_processor.doDeleteAllQuery(TpCalcResultMarginDetailRow.TYPE);
            l_tpCalcResultMarginDetailParams = TestDBUtility.getTpCalcResultMarginDetailRow();
            TestDBUtility.insertWithDel(l_tpCalcResultMarginDetailParams);

            TpCashBalanceParams l_tpCashBalanceParams = new TpCashBalanceParams();
            l_processor.doDeleteAllQuery(TpCashBalanceRow.TYPE);
            l_tpCashBalanceParams.setAccountId(333812512246L);
            l_tpCashBalanceParams.setSubAccountId(33381251220301L);
            TestDBUtility.insertWithDel(l_tpCashBalanceParams);

            AioOrderUnitParams l_aioOrderUnitParams = new AioOrderUnitParams();
            l_processor.doDeleteAllQuery(AioOrderUnitRow.TYPE);
            l_aioOrderUnitParams = TestDBUtility.getAioOrderUnitRow();
            l_aioOrderUnitParams.setAccountId(333812512246L);
            l_aioOrderUnitParams.setSubAccountId(33381251220301L);
            l_aioOrderUnitParams.setOrderType(OrderTypeEnum.FROM_DEPOSIT_AMOUNT_MARGIN_GUARANTEE);
            l_aioOrderUnitParams.setDeliveryDate(WEB3DateUtility.getDate("20070728","yyyyMMdd"));
            l_aioOrderUnitParams.setOrderStatus(OrderStatusEnum.ACCEPTED);
            TestDBUtility.insertWithDel(l_aioOrderUnitParams);
            l_aioOrderUnitParams.setOrderUnitId(0L);
            l_aioOrderUnitParams.setOrderType(OrderTypeEnum.MARGIN_GUARANTEE_FROM_DEPOSIT_AMOUNT);
            TestDBUtility.insertWithDel(l_aioOrderUnitParams);

            //é¿çs
            WEB3TPPaymentRequisitionManageServiceImpl l_service = new WEB3TPPaymentRequisitionManageServiceImpl();
            String l_str = l_service.getAdditionalMarginSituation(new WEB3GentradeMainAccount(l_mainAccountParams), true);
            //î‰är
            assertEquals("2", l_str);
        }
        catch (Exception l_ex)
        {
            log.debug(STR_METHOD_NAME,l_ex);
            fail();
        }
        log.exiting(TEST_END + STR_METHOD_NAME);
    }
    
    /**
     * Åh1Åh(ëÊàÍêÖèÄí«èÿî≠ê∂)Çï‘ãp
     */
    public void test_getAdditionalMarginSituation_0003()
    {
        final String STR_METHOD_NAME = "test_getAdditionalMarginSituation_0003()";
        log.entering(TEST_START + STR_METHOD_NAME);

        try
        {
            
            Calendar l_calendar = Calendar.getInstance();
            l_calendar.set(Calendar.YEAR,2007);
            l_calendar.set(Calendar.MONTH,2);
            l_calendar.set(Calendar.DAY_OF_MONTH,14);
            l_calendar.set(Calendar.HOUR_OF_DAY,15);
            l_calendar.set(Calendar.MINUTE,00);
            l_calendar.set(Calendar.SECOND,01);

            Timestamp l_tsBizDate = new Timestamp(l_calendar.getTimeInMillis());

            ThreadLocalSystemAttributesRegistry.setAttribute(
                WEB3GentradeTradingTimeManagement.TIMESTAMP_TAG, l_tsBizDate);

            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                "com.fitechlabs.xtrade.plugin.tc.gentrade.stdimpls.TradingSystemImpl",
                "getSystemTimestamp",
                new Class[] {},
                l_tsBizDate);

            WEB3GentradeTradingClendarContext l_clendarContext = new WEB3GentradeTradingClendarContext();
            ThreadLocalSystemAttributesRegistry.setAttribute("web3.tradingcalendarcontext", l_clendarContext);

            //DB
            MainAccountParams l_mainAccountParams = new MainAccountParams();
            QueryProcessor l_processor = Processors.getDefaultProcessor();
            l_processor.doDeleteAllQuery(MainAccountRow.TYPE);
            l_mainAccountParams = TestDBUtility.getMainAccountRow();
            l_mainAccountParams.setMarginSysAccOpenDiv("1");    //the third case
            l_mainAccountParams.setSecuredLoanSecAccOpenDiv("0");
            TestDBUtility.insertWithDel(l_mainAccountParams);

            InstitutionParams l_institutionParams =  new InstitutionParams();
            l_processor.doDeleteAllQuery(InstitutionRow.TYPE);
            l_institutionParams = TestDBUtility.getInstitutionRow();
            l_institutionParams.setAssetEvaluation("0");
            l_institutionParams.setMaximumAssessment(1L);
            TestDBUtility.insertWithDel(l_institutionParams);

            BranchParams l_branchParams =  new BranchParams();
            l_processor.doDeleteAllQuery(BranchRow.TYPE);
            l_branchParams = TestDBUtility.getBranchRow();
            TestDBUtility.insertWithDel(l_branchParams);

            PaymentRequisitMngParams l_paymentRequisitMngParams = new PaymentRequisitMngParams();
            l_processor.doDeleteAllQuery(PaymentRequisitMngRow.TYPE);
            l_paymentRequisitMngParams.setAccountId(333812512246L);
            l_paymentRequisitMngParams.setAccountCode("2512246");
            l_paymentRequisitMngParams.setBranchCode("381");
            l_paymentRequisitMngParams.setInstitutionCode("0D");
            l_paymentRequisitMngParams.setFamilyName("Family Name");
            l_paymentRequisitMngParams.setAccountAttribute("0");
            l_paymentRequisitMngParams.setCalcDate(WEB3DateUtility.getDate("20070728","yyyyMMdd"));
            l_paymentRequisitMngParams.setCreatedTimestamp(WEB3DateUtility.getDate("20070728","yyyyMMdd"));
            l_paymentRequisitMngParams.setLastUpdatedTimestamp(WEB3DateUtility.getDate("20070728","yyyyMMdd"));
            l_paymentRequisitMngParams.setFirstDepositAmount(23);
            TestDBUtility.insertWithDel(l_paymentRequisitMngParams);

            SubAccountParams l_subAccountParams = new SubAccountParams();
            l_processor.doDeleteAllQuery(SubAccountRow.TYPE);
            l_subAccountParams = TestDBUtility.getSubAccountRow();
            l_subAccountParams.setAccountId(333812512246L);
            l_subAccountParams.setSubAccountType(SubAccountTypeEnum.EQUITY_MARGIN_SUB_ACCOUNT);
            TestDBUtility.insertWithDel(l_subAccountParams);

            BranchPreferencesParams l_branchPreferencesParams = new BranchPreferencesParams();
            l_processor.doDeleteAllQuery(BranchPreferencesRow.TYPE);
            l_branchPreferencesParams = TestDBUtility.getBranchPreferencesRow();
            l_branchPreferencesParams.setName("0");
            l_branchPreferencesParams.setBranchId(33381);
            TestDBUtility.insertWithDel(l_branchPreferencesParams);

            l_processor.doDeleteAllQuery(SystemPreferencesRow.TYPE);

            OrderexecutionEndParams l_qrderexecutionEndParams = new OrderexecutionEndParams();
            l_processor.doDeleteAllQuery(OrderexecutionEndRow.TYPE);
            l_qrderexecutionEndParams.setInstitutionCode("0D");
            l_qrderexecutionEndParams.setProductType(ProductTypeEnum.EQUITY);
            l_qrderexecutionEndParams.setFutureOptionDiv("0");
            l_qrderexecutionEndParams.setCreatedTimestamp(WEB3DateUtility.getDate("20070728","yyyyMMdd"));
            l_qrderexecutionEndParams.setLastUpdatedTimestamp(WEB3DateUtility.getDate("20070728","yyyyMMdd"));
            l_qrderexecutionEndParams.setOrderexecutionEndType("0");
            TestDBUtility.insertWithDel(l_qrderexecutionEndParams);

            TradingpowerCalcConditionParams l_tradingpowerCalcConditionParams = new TradingpowerCalcConditionParams();
            l_processor.doDeleteAllQuery(TradingpowerCalcConditionRow.TYPE);
            l_tradingpowerCalcConditionParams = TestDBUtility.getTradingpowerCalcConditionRow();
            l_tradingpowerCalcConditionParams.setAccountId(333812512246L);
            l_tradingpowerCalcConditionParams.setAdditionalDepositStop("1");
            TestDBUtility.insertWithDel(l_tradingpowerCalcConditionParams);

            TpCalcResultMarginParams l_tpCalcResultMarginParams = new TpCalcResultMarginParams();
            l_processor.doDeleteAllQuery(TpCalcResultMarginRow.TYPE);
            l_tpCalcResultMarginParams = TestDBUtility.getTpCalcResultMarginRow();
            l_tpCalcResultMarginParams.setCreatedTimestamp(WEB3DateUtility.getDate("20070728","yyyyMMdd"));
            l_tpCalcResultMarginParams.setContractAmount0(12);
            TestDBUtility.insertWithDel(l_tpCalcResultMarginParams);

            l_processor.doDeleteAllQuery(ProcessManagementRow.TYPE);

            TpCalcResultMarginDetailParams l_tpCalcResultMarginDetailParams = new TpCalcResultMarginDetailParams();
            l_processor.doDeleteAllQuery(TpCalcResultMarginDetailRow.TYPE);
            l_tpCalcResultMarginDetailParams = TestDBUtility.getTpCalcResultMarginDetailRow();
            TestDBUtility.insertWithDel(l_tpCalcResultMarginDetailParams);

            TpCashBalanceParams l_tpCashBalanceParams = new TpCashBalanceParams();
            l_processor.doDeleteAllQuery(TpCashBalanceRow.TYPE);
            l_tpCashBalanceParams.setAccountId(333812512246L);
            l_tpCashBalanceParams.setSubAccountId(33381251220301L);
            TestDBUtility.insertWithDel(l_tpCashBalanceParams);

            AioOrderUnitParams l_aioOrderUnitParams = new AioOrderUnitParams();
            l_processor.doDeleteAllQuery(AioOrderUnitRow.TYPE);
            l_aioOrderUnitParams = TestDBUtility.getAioOrderUnitRow();
            l_aioOrderUnitParams.setAccountId(333812512246L);
            l_aioOrderUnitParams.setSubAccountId(33381251220301L);
            l_aioOrderUnitParams.setOrderType(OrderTypeEnum.FROM_DEPOSIT_AMOUNT_MARGIN_GUARANTEE);
            l_aioOrderUnitParams.setDeliveryDate(WEB3DateUtility.getDate("20070728","yyyyMMdd"));
            l_aioOrderUnitParams.setOrderStatus(OrderStatusEnum.ACCEPTED);
            TestDBUtility.insertWithDel(l_aioOrderUnitParams);
            l_aioOrderUnitParams.setOrderUnitId(0L);
            l_aioOrderUnitParams.setOrderType(OrderTypeEnum.MARGIN_GUARANTEE_FROM_DEPOSIT_AMOUNT);
            TestDBUtility.insertWithDel(l_aioOrderUnitParams);

            //é¿çs
            WEB3TPPaymentRequisitionManageServiceImpl l_service = new WEB3TPPaymentRequisitionManageServiceImpl();
            String l_str = l_service.getAdditionalMarginSituation(new WEB3GentradeMainAccount(l_mainAccountParams), true);
            //î‰är
            assertEquals("1", l_str);
        }
        catch (Exception l_ex)
        {
            log.debug(STR_METHOD_NAME,l_ex);
            fail();
        }
        log.exiting(TEST_END + STR_METHOD_NAME);
    }
    
    /**
     * ó·äOÇÉXÉçÅ|
     */
    public void test_getAdditionalMarginSituation_0004()
    {
        final String STR_METHOD_NAME = "test_getAdditionalMarginSituation_0004()";
        log.entering(TEST_START + STR_METHOD_NAME);

        try
        {
            
            Calendar l_calendar = Calendar.getInstance();
            l_calendar.set(Calendar.YEAR,2007);
            l_calendar.set(Calendar.MONTH,2);
            l_calendar.set(Calendar.DAY_OF_MONTH,14);
            l_calendar.set(Calendar.HOUR_OF_DAY,15);
            l_calendar.set(Calendar.MINUTE,00);
            l_calendar.set(Calendar.SECOND,01);

            Timestamp l_tsBizDate = new Timestamp(l_calendar.getTimeInMillis());

            ThreadLocalSystemAttributesRegistry.setAttribute(
                WEB3GentradeTradingTimeManagement.TIMESTAMP_TAG, l_tsBizDate);

            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                "com.fitechlabs.xtrade.plugin.tc.gentrade.stdimpls.TradingSystemImpl",
                "getSystemTimestamp",
                new Class[] {},
                l_tsBizDate);

            WEB3GentradeTradingClendarContext l_clendarContext = new WEB3GentradeTradingClendarContext();
            ThreadLocalSystemAttributesRegistry.setAttribute("web3.tradingcalendarcontext", l_clendarContext);

            //DB
            MainAccountParams l_mainAccountParams = new MainAccountParams();
            QueryProcessor l_processor = Processors.getDefaultProcessor();
            l_processor.doDeleteAllQuery(MainAccountRow.TYPE);
            l_mainAccountParams = TestDBUtility.getMainAccountRow();
            l_mainAccountParams.setMarginSysAccOpenDiv("3");    //the third case
            l_mainAccountParams.setSecuredLoanSecAccOpenDiv("0");
            TestDBUtility.insertWithDel(l_mainAccountParams);

            InstitutionParams l_institutionParams =  new InstitutionParams();
            l_processor.doDeleteAllQuery(InstitutionRow.TYPE);
            l_institutionParams = TestDBUtility.getInstitutionRow();
            l_institutionParams.setAssetEvaluation("0");
            l_institutionParams.setMaximumAssessment(1L);
            TestDBUtility.insertWithDel(l_institutionParams);

            BranchParams l_branchParams =  new BranchParams();
            l_processor.doDeleteAllQuery(BranchRow.TYPE);
            l_branchParams = TestDBUtility.getBranchRow();
            TestDBUtility.insertWithDel(l_branchParams);

            PaymentRequisitMngParams l_paymentRequisitMngParams = new PaymentRequisitMngParams();
            l_processor.doDeleteAllQuery(PaymentRequisitMngRow.TYPE);
            l_paymentRequisitMngParams.setAccountId(333812512246L);
            l_paymentRequisitMngParams.setAccountCode("2512246");
            l_paymentRequisitMngParams.setBranchCode("381");
            l_paymentRequisitMngParams.setInstitutionCode("0D");
            l_paymentRequisitMngParams.setFamilyName("Family Name");
            l_paymentRequisitMngParams.setAccountAttribute("0");
            l_paymentRequisitMngParams.setCalcDate(WEB3DateUtility.getDate("20070728","yyyyMMdd"));
            l_paymentRequisitMngParams.setCreatedTimestamp(WEB3DateUtility.getDate("20070728","yyyyMMdd"));
            l_paymentRequisitMngParams.setLastUpdatedTimestamp(WEB3DateUtility.getDate("20070728","yyyyMMdd"));
            l_paymentRequisitMngParams.setFirstDepositAmount(23);
            TestDBUtility.insertWithDel(l_paymentRequisitMngParams);

            SubAccountParams l_subAccountParams = new SubAccountParams();
            l_processor.doDeleteAllQuery(SubAccountRow.TYPE);
            l_subAccountParams = TestDBUtility.getSubAccountRow();
            l_subAccountParams.setAccountId(333812512246L);
            l_subAccountParams.setSubAccountType(SubAccountTypeEnum.EQUITY_MARGIN_SUB_ACCOUNT);
            TestDBUtility.insertWithDel(l_subAccountParams);

            BranchPreferencesParams l_branchPreferencesParams = new BranchPreferencesParams();
            l_processor.doDeleteAllQuery(BranchPreferencesRow.TYPE);
            l_branchPreferencesParams = TestDBUtility.getBranchPreferencesRow();
            l_branchPreferencesParams.setName("0");
            l_branchPreferencesParams.setBranchId(33381);
            TestDBUtility.insertWithDel(l_branchPreferencesParams);

            l_processor.doDeleteAllQuery(SystemPreferencesRow.TYPE);

            OrderexecutionEndParams l_qrderexecutionEndParams = new OrderexecutionEndParams();
            l_processor.doDeleteAllQuery(OrderexecutionEndRow.TYPE);
            l_qrderexecutionEndParams.setInstitutionCode("0D");
            l_qrderexecutionEndParams.setProductType(ProductTypeEnum.EQUITY);
            l_qrderexecutionEndParams.setFutureOptionDiv("0");
            l_qrderexecutionEndParams.setCreatedTimestamp(WEB3DateUtility.getDate("20070728","yyyyMMdd"));
            l_qrderexecutionEndParams.setLastUpdatedTimestamp(WEB3DateUtility.getDate("20070728","yyyyMMdd"));
            l_qrderexecutionEndParams.setOrderexecutionEndType("0");
            TestDBUtility.insertWithDel(l_qrderexecutionEndParams);

            TradingpowerCalcConditionParams l_tradingpowerCalcConditionParams = new TradingpowerCalcConditionParams();
            l_processor.doDeleteAllQuery(TradingpowerCalcConditionRow.TYPE);
            l_tradingpowerCalcConditionParams = TestDBUtility.getTradingpowerCalcConditionRow();
            l_tradingpowerCalcConditionParams.setAccountId(333812512246L);
            l_tradingpowerCalcConditionParams.setAdditionalDepositStop("1");
            TestDBUtility.insertWithDel(l_tradingpowerCalcConditionParams);

            TpCalcResultMarginParams l_tpCalcResultMarginParams = new TpCalcResultMarginParams();
            l_processor.doDeleteAllQuery(TpCalcResultMarginRow.TYPE);
            l_tpCalcResultMarginParams = TestDBUtility.getTpCalcResultMarginRow();
            l_tpCalcResultMarginParams.setCreatedTimestamp(WEB3DateUtility.getDate("20070728","yyyyMMdd"));
            l_tpCalcResultMarginParams.setContractAmount0(12);
            TestDBUtility.insertWithDel(l_tpCalcResultMarginParams);

            l_processor.doDeleteAllQuery(ProcessManagementRow.TYPE);

            TpCalcResultMarginDetailParams l_tpCalcResultMarginDetailParams = new TpCalcResultMarginDetailParams();
            l_processor.doDeleteAllQuery(TpCalcResultMarginDetailRow.TYPE);
            l_tpCalcResultMarginDetailParams = TestDBUtility.getTpCalcResultMarginDetailRow();
            TestDBUtility.insertWithDel(l_tpCalcResultMarginDetailParams);

            TpCashBalanceParams l_tpCashBalanceParams = new TpCashBalanceParams();
            l_processor.doDeleteAllQuery(TpCashBalanceRow.TYPE);
            l_tpCashBalanceParams.setAccountId(333812512246L);
            l_tpCashBalanceParams.setSubAccountId(33381251220301L);
            TestDBUtility.insertWithDel(l_tpCashBalanceParams);

            AioOrderUnitParams l_aioOrderUnitParams = new AioOrderUnitParams();
            l_processor.doDeleteAllQuery(AioOrderUnitRow.TYPE);
            l_aioOrderUnitParams = TestDBUtility.getAioOrderUnitRow();
            l_aioOrderUnitParams.setAccountId(333812512246L);
            l_aioOrderUnitParams.setSubAccountId(33381251220301L);
            l_aioOrderUnitParams.setOrderType(OrderTypeEnum.FROM_DEPOSIT_AMOUNT_MARGIN_GUARANTEE);
            l_aioOrderUnitParams.setDeliveryDate(WEB3DateUtility.getDate("20070728","yyyyMMdd"));
            l_aioOrderUnitParams.setOrderStatus(OrderStatusEnum.ACCEPTED);
            TestDBUtility.insertWithDel(l_aioOrderUnitParams);
            l_aioOrderUnitParams.setOrderUnitId(0L);
            l_aioOrderUnitParams.setOrderType(OrderTypeEnum.MARGIN_GUARANTEE_FROM_DEPOSIT_AMOUNT);
            TestDBUtility.insertWithDel(l_aioOrderUnitParams);

            //é¿çs
            WEB3TPPaymentRequisitionManageServiceImpl l_service = new WEB3TPPaymentRequisitionManageServiceImpl();
            String l_str = l_service.getAdditionalMarginSituation(new WEB3GentradeMainAccount(l_mainAccountParams), true);
            //î‰är
//            assertEquals("1", l_str);
            fail();
        }
        catch (WEB3BaseException l_ex)
        {
            log.debug(STR_METHOD_NAME,l_ex);
            assertEquals(WEB3ErrorCatalog.BUSINESS_ERROR_02887, l_ex.getErrorInfo());
        }
        catch (Exception l_ex)
        {
            log.debug(STR_METHOD_NAME,l_ex);
            fail();
        }
        log.exiting(TEST_END + STR_METHOD_NAME);
    }
    
    /**
     * 
     */
    public void test_getPaymentRequisitionAccountDetailInfo_0001()
    {
        final String STR_METHOD_NAME = "test_getPaymentRequisitionAccountDetailInfo_0001()";
        log.entering(TEST_START + STR_METHOD_NAME);

        try
        {
            
            Calendar l_calendar = Calendar.getInstance();
            l_calendar.set(Calendar.YEAR,2007);
            l_calendar.set(Calendar.MONTH,2);
            l_calendar.set(Calendar.DAY_OF_MONTH,14);
            l_calendar.set(Calendar.HOUR_OF_DAY,15);
            l_calendar.set(Calendar.MINUTE,00);
            l_calendar.set(Calendar.SECOND,01);

            Timestamp l_tsBizDate = new Timestamp(l_calendar.getTimeInMillis());

            ThreadLocalSystemAttributesRegistry.setAttribute(
                WEB3GentradeTradingTimeManagement.TIMESTAMP_TAG, l_tsBizDate);

            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                "com.fitechlabs.xtrade.plugin.tc.gentrade.stdimpls.TradingSystemImpl",
                "getSystemTimestamp",
                new Class[] {},
                l_tsBizDate);

            WEB3GentradeTradingClendarContext l_clendarContext = new WEB3GentradeTradingClendarContext();
            ThreadLocalSystemAttributesRegistry.setAttribute("web3.tradingcalendarcontext", l_clendarContext);

            //DB
            MainAccountParams l_mainAccountParams = new MainAccountParams();
            QueryProcessor l_processor = Processors.getDefaultProcessor();
            l_processor.doDeleteAllQuery(MainAccountRow.TYPE);
            l_mainAccountParams = TestDBUtility.getMainAccountRow();
            l_mainAccountParams.setMarginSysAccOpenDiv("1");    //the third case
            l_mainAccountParams.setSecuredLoanSecAccOpenDiv("0");
            TestDBUtility.insertWithDel(l_mainAccountParams);

            InstitutionParams l_institutionParams =  new InstitutionParams();
            l_processor.doDeleteAllQuery(InstitutionRow.TYPE);
            l_institutionParams = TestDBUtility.getInstitutionRow();
            l_institutionParams.setAssetEvaluation("0");
            l_institutionParams.setMaximumAssessment(1L);
            TestDBUtility.insertWithDel(l_institutionParams);

            BranchParams l_branchParams =  new BranchParams();
            l_processor.doDeleteAllQuery(BranchRow.TYPE);
            l_branchParams = TestDBUtility.getBranchRow();
            TestDBUtility.insertWithDel(l_branchParams);

            PaymentRequisitMngParams l_paymentRequisitMngParams = new PaymentRequisitMngParams();
            l_processor.doDeleteAllQuery(PaymentRequisitMngRow.TYPE);
            l_paymentRequisitMngParams.setAccountId(333812512246L);
            l_paymentRequisitMngParams.setAccountCode("2512246");
            l_paymentRequisitMngParams.setBranchCode("381");
            l_paymentRequisitMngParams.setInstitutionCode("0D");
            l_paymentRequisitMngParams.setFamilyName("Family Name");
            l_paymentRequisitMngParams.setAccountAttribute("0");
            l_paymentRequisitMngParams.setCalcDate(WEB3DateUtility.getDate("20070728","yyyyMMdd"));
            l_paymentRequisitMngParams.setCreatedTimestamp(WEB3DateUtility.getDate("20070728","yyyyMMdd"));
            l_paymentRequisitMngParams.setLastUpdatedTimestamp(WEB3DateUtility.getDate("20070728","yyyyMMdd"));
            l_paymentRequisitMngParams.setFirstDepositAmount(23);
            TestDBUtility.insertWithDel(l_paymentRequisitMngParams);

            SubAccountParams l_subAccountParams = new SubAccountParams();
            l_processor.doDeleteAllQuery(SubAccountRow.TYPE);
            l_subAccountParams = TestDBUtility.getSubAccountRow();
            l_subAccountParams.setAccountId(333812512246L);
            l_subAccountParams.setSubAccountType(SubAccountTypeEnum.EQUITY_MARGIN_SUB_ACCOUNT);
            TestDBUtility.insertWithDel(l_subAccountParams);

            BranchPreferencesParams l_branchPreferencesParams = new BranchPreferencesParams();
            l_processor.doDeleteAllQuery(BranchPreferencesRow.TYPE);
            l_branchPreferencesParams = TestDBUtility.getBranchPreferencesRow();
            l_branchPreferencesParams.setName("0");
            l_branchPreferencesParams.setBranchId(33381);
            TestDBUtility.insertWithDel(l_branchPreferencesParams);

            l_processor.doDeleteAllQuery(SystemPreferencesRow.TYPE);

            OrderexecutionEndParams l_qrderexecutionEndParams = new OrderexecutionEndParams();
            l_processor.doDeleteAllQuery(OrderexecutionEndRow.TYPE);
            l_qrderexecutionEndParams.setInstitutionCode("0D");
            l_qrderexecutionEndParams.setProductType(ProductTypeEnum.EQUITY);
            l_qrderexecutionEndParams.setFutureOptionDiv("0");
            l_qrderexecutionEndParams.setCreatedTimestamp(WEB3DateUtility.getDate("20070728","yyyyMMdd"));
            l_qrderexecutionEndParams.setLastUpdatedTimestamp(WEB3DateUtility.getDate("20070728","yyyyMMdd"));
            l_qrderexecutionEndParams.setOrderexecutionEndType("0");
            TestDBUtility.insertWithDel(l_qrderexecutionEndParams);

            TradingpowerCalcConditionParams l_tradingpowerCalcConditionParams = new TradingpowerCalcConditionParams();
            l_processor.doDeleteAllQuery(TradingpowerCalcConditionRow.TYPE);
            l_tradingpowerCalcConditionParams = TestDBUtility.getTradingpowerCalcConditionRow();
            l_tradingpowerCalcConditionParams.setAccountId(333812512246L);
            l_tradingpowerCalcConditionParams.setAdditionalDepositStop("1");
            TestDBUtility.insertWithDel(l_tradingpowerCalcConditionParams);

            TpCalcResultMarginParams l_tpCalcResultMarginParams = new TpCalcResultMarginParams();
            l_processor.doDeleteAllQuery(TpCalcResultMarginRow.TYPE);
            l_tpCalcResultMarginParams = TestDBUtility.getTpCalcResultMarginRow();
            l_tpCalcResultMarginParams.setCreatedTimestamp(WEB3DateUtility.getDate("20070728","yyyyMMdd"));
            l_tpCalcResultMarginParams.setContractAmount0(12);
            TestDBUtility.insertWithDel(l_tpCalcResultMarginParams);

            l_processor.doDeleteAllQuery(ProcessManagementRow.TYPE);

            TpCalcResultMarginDetailParams l_tpCalcResultMarginDetailParams = new TpCalcResultMarginDetailParams();
            l_processor.doDeleteAllQuery(TpCalcResultMarginDetailRow.TYPE);
            l_tpCalcResultMarginDetailParams = TestDBUtility.getTpCalcResultMarginDetailRow();
            TestDBUtility.insertWithDel(l_tpCalcResultMarginDetailParams);

            TpCashBalanceParams l_tpCashBalanceParams = new TpCashBalanceParams();
            l_processor.doDeleteAllQuery(TpCashBalanceRow.TYPE);
            l_tpCashBalanceParams.setAccountId(333812512246L);
            l_tpCashBalanceParams.setSubAccountId(33381251220301L);
            TestDBUtility.insertWithDel(l_tpCashBalanceParams);

            AioOrderUnitParams l_aioOrderUnitParams = new AioOrderUnitParams();
            l_processor.doDeleteAllQuery(AioOrderUnitRow.TYPE);
            l_aioOrderUnitParams = TestDBUtility.getAioOrderUnitRow();
            l_aioOrderUnitParams.setAccountId(333812512246L);
            l_aioOrderUnitParams.setSubAccountId(33381251220301L);
            l_aioOrderUnitParams.setOrderType(OrderTypeEnum.FROM_DEPOSIT_AMOUNT_MARGIN_GUARANTEE);
            l_aioOrderUnitParams.setDeliveryDate(WEB3DateUtility.getDate("20070728","yyyyMMdd"));
            l_aioOrderUnitParams.setOrderStatus(OrderStatusEnum.ACCEPTED);
            TestDBUtility.insertWithDel(l_aioOrderUnitParams);
            l_aioOrderUnitParams.setOrderUnitId(0L);
            l_aioOrderUnitParams.setOrderType(OrderTypeEnum.MARGIN_GUARANTEE_FROM_DEPOSIT_AMOUNT);
            TestDBUtility.insertWithDel(l_aioOrderUnitParams);

            //é¿çs
            WEB3TPPaymentRequisitionManageServiceImpl l_service = new WEB3TPPaymentRequisitionManageServiceImpl();
            WEB3TPPaymentRequisitionAccountDetailInfo l_paymentRequisitionAccountDetailInfo =
                l_service.getPaymentRequisitionAccountDetailInfo(new WEB3GentradeMainAccount(l_mainAccountParams));
            //î‰är
//            assertEquals("1", l_str);
            WEB3TPShortfallOccurInfo l_info =
                l_paymentRequisitionAccountDetailInfo.getShortfallGenerationInfo();
            assertEquals("20.0", l_info.dayTradeRestraint0 + "");
            assertFalse(l_paymentRequisitionAccountDetailInfo.isDepositAutoTransferDivFlag());
        }
        catch (Exception l_ex)
        {
            log.debug(STR_METHOD_NAME,l_ex);
            fail();
        }
        log.exiting(TEST_END + STR_METHOD_NAME);
    }
    
    /**
     * 
     */
    public void test_getShortfallGenerationInfo_0001()
    {
        final String STR_METHOD_NAME = "test_getShortfallGenerationInfo_0001()";
        log.entering(TEST_START + STR_METHOD_NAME);

        try
        {
            
            Calendar l_calendar = Calendar.getInstance();
            l_calendar.set(Calendar.YEAR,2007);
            l_calendar.set(Calendar.MONTH,2);
            l_calendar.set(Calendar.DAY_OF_MONTH,14);
            l_calendar.set(Calendar.HOUR_OF_DAY,15);
            l_calendar.set(Calendar.MINUTE,00);
            l_calendar.set(Calendar.SECOND,01);

            Timestamp l_tsBizDate = new Timestamp(l_calendar.getTimeInMillis());

            ThreadLocalSystemAttributesRegistry.setAttribute(
                WEB3GentradeTradingTimeManagement.TIMESTAMP_TAG, l_tsBizDate);

            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                "com.fitechlabs.xtrade.plugin.tc.gentrade.stdimpls.TradingSystemImpl",
                "getSystemTimestamp",
                new Class[] {},
                l_tsBizDate);

            WEB3GentradeTradingClendarContext l_clendarContext = new WEB3GentradeTradingClendarContext();
            ThreadLocalSystemAttributesRegistry.setAttribute("web3.tradingcalendarcontext", l_clendarContext);

            //DB
            MainAccountParams l_mainAccountParams = new MainAccountParams();
            QueryProcessor l_processor = Processors.getDefaultProcessor();
            l_processor.doDeleteAllQuery(MainAccountRow.TYPE);
            l_mainAccountParams = TestDBUtility.getMainAccountRow();
            l_mainAccountParams.setMarginSysAccOpenDiv("1");    //the third case
            l_mainAccountParams.setSecuredLoanSecAccOpenDiv("0");
            TestDBUtility.insertWithDel(l_mainAccountParams);

            InstitutionParams l_institutionParams =  new InstitutionParams();
            l_processor.doDeleteAllQuery(InstitutionRow.TYPE);
            l_institutionParams = TestDBUtility.getInstitutionRow();
            l_institutionParams.setAssetEvaluation("0");
            l_institutionParams.setMaximumAssessment(1L);
            TestDBUtility.insertWithDel(l_institutionParams);

            BranchParams l_branchParams =  new BranchParams();
            l_processor.doDeleteAllQuery(BranchRow.TYPE);
            l_branchParams = TestDBUtility.getBranchRow();
            TestDBUtility.insertWithDel(l_branchParams);

            PaymentRequisitMngParams l_paymentRequisitMngParams = new PaymentRequisitMngParams();
            l_processor.doDeleteAllQuery(PaymentRequisitMngRow.TYPE);
            l_paymentRequisitMngParams.setAccountId(333812512246L);
            l_paymentRequisitMngParams.setAccountCode("2512246");
            l_paymentRequisitMngParams.setBranchCode("381");
            l_paymentRequisitMngParams.setInstitutionCode("0D");
            l_paymentRequisitMngParams.setFamilyName("Family Name");
            l_paymentRequisitMngParams.setAccountAttribute("0");
            l_paymentRequisitMngParams.setCalcDate(WEB3DateUtility.getDate("20070728","yyyyMMdd"));
            l_paymentRequisitMngParams.setCreatedTimestamp(WEB3DateUtility.getDate("20070728","yyyyMMdd"));
            l_paymentRequisitMngParams.setLastUpdatedTimestamp(WEB3DateUtility.getDate("20070728","yyyyMMdd"));
            l_paymentRequisitMngParams.setFirstDepositAmount(23);
            TestDBUtility.insertWithDel(l_paymentRequisitMngParams);

            SubAccountParams l_subAccountParams = new SubAccountParams();
            l_processor.doDeleteAllQuery(SubAccountRow.TYPE);
            l_subAccountParams = TestDBUtility.getSubAccountRow();
            l_subAccountParams.setAccountId(333812512246L);
            l_subAccountParams.setSubAccountType(SubAccountTypeEnum.EQUITY_MARGIN_SUB_ACCOUNT);
            TestDBUtility.insertWithDel(l_subAccountParams);

            BranchPreferencesParams l_branchPreferencesParams = new BranchPreferencesParams();
            l_processor.doDeleteAllQuery(BranchPreferencesRow.TYPE);
            l_branchPreferencesParams = TestDBUtility.getBranchPreferencesRow();
            l_branchPreferencesParams.setName("0");
            l_branchPreferencesParams.setBranchId(33381);
            TestDBUtility.insertWithDel(l_branchPreferencesParams);

            l_processor.doDeleteAllQuery(SystemPreferencesRow.TYPE);

            OrderexecutionEndParams l_qrderexecutionEndParams = new OrderexecutionEndParams();
            l_processor.doDeleteAllQuery(OrderexecutionEndRow.TYPE);
            l_qrderexecutionEndParams.setInstitutionCode("0D");
            l_qrderexecutionEndParams.setProductType(ProductTypeEnum.EQUITY);
            l_qrderexecutionEndParams.setFutureOptionDiv("0");
            l_qrderexecutionEndParams.setCreatedTimestamp(WEB3DateUtility.getDate("20070728","yyyyMMdd"));
            l_qrderexecutionEndParams.setLastUpdatedTimestamp(WEB3DateUtility.getDate("20070728","yyyyMMdd"));
            l_qrderexecutionEndParams.setOrderexecutionEndType("0");
            TestDBUtility.insertWithDel(l_qrderexecutionEndParams);

            TradingpowerCalcConditionParams l_tradingpowerCalcConditionParams = new TradingpowerCalcConditionParams();
            l_processor.doDeleteAllQuery(TradingpowerCalcConditionRow.TYPE);
            l_tradingpowerCalcConditionParams = TestDBUtility.getTradingpowerCalcConditionRow();
            l_tradingpowerCalcConditionParams.setAccountId(333812512246L);
            l_tradingpowerCalcConditionParams.setAdditionalDepositStop("1");
            TestDBUtility.insertWithDel(l_tradingpowerCalcConditionParams);

            TpCalcResultMarginParams l_tpCalcResultMarginParams = new TpCalcResultMarginParams();
            l_processor.doDeleteAllQuery(TpCalcResultMarginRow.TYPE);
            l_tpCalcResultMarginParams = TestDBUtility.getTpCalcResultMarginRow();
            l_tpCalcResultMarginParams.setCreatedTimestamp(WEB3DateUtility.getDate("20070728","yyyyMMdd"));
            l_tpCalcResultMarginParams.setContractAmount0(12);
            TestDBUtility.insertWithDel(l_tpCalcResultMarginParams);

            l_processor.doDeleteAllQuery(ProcessManagementRow.TYPE);

            TpCalcResultMarginDetailParams l_tpCalcResultMarginDetailParams = new TpCalcResultMarginDetailParams();
            l_processor.doDeleteAllQuery(TpCalcResultMarginDetailRow.TYPE);
            l_tpCalcResultMarginDetailParams = TestDBUtility.getTpCalcResultMarginDetailRow();
            TestDBUtility.insertWithDel(l_tpCalcResultMarginDetailParams);

            TpCashBalanceParams l_tpCashBalanceParams = new TpCashBalanceParams();
            l_processor.doDeleteAllQuery(TpCashBalanceRow.TYPE);
            l_tpCashBalanceParams.setAccountId(333812512246L);
            l_tpCashBalanceParams.setSubAccountId(33381251220301L);
            TestDBUtility.insertWithDel(l_tpCashBalanceParams);

            AioOrderUnitParams l_aioOrderUnitParams = new AioOrderUnitParams();
            l_processor.doDeleteAllQuery(AioOrderUnitRow.TYPE);
            l_aioOrderUnitParams = TestDBUtility.getAioOrderUnitRow();
            l_aioOrderUnitParams.setAccountId(333812512246L);
            l_aioOrderUnitParams.setSubAccountId(33381251220301L);
            l_aioOrderUnitParams.setOrderType(OrderTypeEnum.FROM_DEPOSIT_AMOUNT_MARGIN_GUARANTEE);
            l_aioOrderUnitParams.setDeliveryDate(WEB3DateUtility.getDate("20070728","yyyyMMdd"));
            l_aioOrderUnitParams.setOrderStatus(OrderStatusEnum.ACCEPTED);
            TestDBUtility.insertWithDel(l_aioOrderUnitParams);
            l_aioOrderUnitParams.setOrderUnitId(0L);
            l_aioOrderUnitParams.setOrderType(OrderTypeEnum.MARGIN_GUARANTEE_FROM_DEPOSIT_AMOUNT);
            TestDBUtility.insertWithDel(l_aioOrderUnitParams);

            //é¿çs
            WEB3TPPaymentRequisitionManageServiceImpl l_service = new WEB3TPPaymentRequisitionManageServiceImpl();
            WEB3TPShortfallOccurInfo l_shortfallOccurInfo =
                l_service.getShortfallGenerationInfo(new WEB3GentradeMainAccount(l_mainAccountParams));
            //î‰är
            assertEquals("20.0", l_shortfallOccurInfo.dayTradeRestraint0 + "");
        }
        catch (Exception l_ex)
        {
            log.debug(STR_METHOD_NAME,l_ex);
            fail();
        }
        log.exiting(TEST_END + STR_METHOD_NAME);
    }
    
    /**
     * 
     */
    public void test_getAdddepositGenerationInfo_0001()
    {
        final String STR_METHOD_NAME = "test_getAdddepositGenerationInfo_0001()";
        log.entering(TEST_START + STR_METHOD_NAME);

        try
        {
            
            Calendar l_calendar = Calendar.getInstance();
            l_calendar.set(Calendar.YEAR,2007);
            l_calendar.set(Calendar.MONTH,2);
            l_calendar.set(Calendar.DAY_OF_MONTH,14);
            l_calendar.set(Calendar.HOUR_OF_DAY,15);
            l_calendar.set(Calendar.MINUTE,00);
            l_calendar.set(Calendar.SECOND,01);

            Timestamp l_tsBizDate = new Timestamp(l_calendar.getTimeInMillis());

            ThreadLocalSystemAttributesRegistry.setAttribute(
                WEB3GentradeTradingTimeManagement.TIMESTAMP_TAG, l_tsBizDate);

            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                "com.fitechlabs.xtrade.plugin.tc.gentrade.stdimpls.TradingSystemImpl",
                "getSystemTimestamp",
                new Class[] {},
                l_tsBizDate);

            WEB3GentradeTradingClendarContext l_clendarContext = new WEB3GentradeTradingClendarContext();
            ThreadLocalSystemAttributesRegistry.setAttribute("web3.tradingcalendarcontext", l_clendarContext);

            //DB
            MainAccountParams l_mainAccountParams = new MainAccountParams();
            QueryProcessor l_processor = Processors.getDefaultProcessor();
            l_processor.doDeleteAllQuery(MainAccountRow.TYPE);
            l_mainAccountParams = TestDBUtility.getMainAccountRow();
            l_mainAccountParams.setMarginSysAccOpenDiv("1");    //the third case
            l_mainAccountParams.setSecuredLoanSecAccOpenDiv("0");
            TestDBUtility.insertWithDel(l_mainAccountParams);

            InstitutionParams l_institutionParams =  new InstitutionParams();
            l_processor.doDeleteAllQuery(InstitutionRow.TYPE);
            l_institutionParams = TestDBUtility.getInstitutionRow();
            l_institutionParams.setAssetEvaluation("0");
            l_institutionParams.setMaximumAssessment(1L);
            TestDBUtility.insertWithDel(l_institutionParams);

            BranchParams l_branchParams =  new BranchParams();
            l_processor.doDeleteAllQuery(BranchRow.TYPE);
            l_branchParams = TestDBUtility.getBranchRow();
            TestDBUtility.insertWithDel(l_branchParams);

            PaymentRequisitMngParams l_paymentRequisitMngParams = new PaymentRequisitMngParams();
            l_processor.doDeleteAllQuery(PaymentRequisitMngRow.TYPE);
            l_paymentRequisitMngParams.setAccountId(333812512246L);
            l_paymentRequisitMngParams.setAccountCode("2512246");
            l_paymentRequisitMngParams.setBranchCode("381");
            l_paymentRequisitMngParams.setInstitutionCode("0D");
            l_paymentRequisitMngParams.setFamilyName("Family Name");
            l_paymentRequisitMngParams.setAccountAttribute("0");
            l_paymentRequisitMngParams.setCalcDate(WEB3DateUtility.getDate("20070728","yyyyMMdd"));
            l_paymentRequisitMngParams.setCreatedTimestamp(WEB3DateUtility.getDate("20070728","yyyyMMdd"));
            l_paymentRequisitMngParams.setLastUpdatedTimestamp(WEB3DateUtility.getDate("20070728","yyyyMMdd"));
            l_paymentRequisitMngParams.setFirstDepositAmount(23);
            TestDBUtility.insertWithDel(l_paymentRequisitMngParams);

            SubAccountParams l_subAccountParams = new SubAccountParams();
            l_processor.doDeleteAllQuery(SubAccountRow.TYPE);
            l_subAccountParams = TestDBUtility.getSubAccountRow();
            l_subAccountParams.setAccountId(333812512246L);
            l_subAccountParams.setSubAccountType(SubAccountTypeEnum.EQUITY_MARGIN_SUB_ACCOUNT);
            TestDBUtility.insertWithDel(l_subAccountParams);

            BranchPreferencesParams l_branchPreferencesParams = new BranchPreferencesParams();
            l_processor.doDeleteAllQuery(BranchPreferencesRow.TYPE);
            l_branchPreferencesParams = TestDBUtility.getBranchPreferencesRow();
            l_branchPreferencesParams.setName("0");
            l_branchPreferencesParams.setBranchId(33381);
            TestDBUtility.insertWithDel(l_branchPreferencesParams);

            l_processor.doDeleteAllQuery(SystemPreferencesRow.TYPE);

            OrderexecutionEndParams l_qrderexecutionEndParams = new OrderexecutionEndParams();
            l_processor.doDeleteAllQuery(OrderexecutionEndRow.TYPE);
            l_qrderexecutionEndParams.setInstitutionCode("0D");
            l_qrderexecutionEndParams.setProductType(ProductTypeEnum.EQUITY);
            l_qrderexecutionEndParams.setFutureOptionDiv("0");
            l_qrderexecutionEndParams.setCreatedTimestamp(WEB3DateUtility.getDate("20070728","yyyyMMdd"));
            l_qrderexecutionEndParams.setLastUpdatedTimestamp(WEB3DateUtility.getDate("20070728","yyyyMMdd"));
            l_qrderexecutionEndParams.setOrderexecutionEndType("0");
            TestDBUtility.insertWithDel(l_qrderexecutionEndParams);

            TradingpowerCalcConditionParams l_tradingpowerCalcConditionParams = new TradingpowerCalcConditionParams();
            l_processor.doDeleteAllQuery(TradingpowerCalcConditionRow.TYPE);
            l_tradingpowerCalcConditionParams = TestDBUtility.getTradingpowerCalcConditionRow();
            l_tradingpowerCalcConditionParams.setAccountId(333812512246L);
            l_tradingpowerCalcConditionParams.setAdditionalDepositStop("1");
            TestDBUtility.insertWithDel(l_tradingpowerCalcConditionParams);

            TpCalcResultMarginParams l_tpCalcResultMarginParams = new TpCalcResultMarginParams();
            l_processor.doDeleteAllQuery(TpCalcResultMarginRow.TYPE);
            l_tpCalcResultMarginParams = TestDBUtility.getTpCalcResultMarginRow();
            l_tpCalcResultMarginParams.setCreatedTimestamp(WEB3DateUtility.getDate("20070728","yyyyMMdd"));
            l_tpCalcResultMarginParams.setContractAmount0(12);
            TestDBUtility.insertWithDel(l_tpCalcResultMarginParams);

            l_processor.doDeleteAllQuery(ProcessManagementRow.TYPE);

            TpCalcResultMarginDetailParams l_tpCalcResultMarginDetailParams = new TpCalcResultMarginDetailParams();
            l_processor.doDeleteAllQuery(TpCalcResultMarginDetailRow.TYPE);
            l_tpCalcResultMarginDetailParams = TestDBUtility.getTpCalcResultMarginDetailRow();
            TestDBUtility.insertWithDel(l_tpCalcResultMarginDetailParams);

            TpCashBalanceParams l_tpCashBalanceParams = new TpCashBalanceParams();
            l_processor.doDeleteAllQuery(TpCashBalanceRow.TYPE);
            l_tpCashBalanceParams.setAccountId(333812512246L);
            l_tpCashBalanceParams.setSubAccountId(33381251220301L);
            TestDBUtility.insertWithDel(l_tpCashBalanceParams);

            AioOrderUnitParams l_aioOrderUnitParams = new AioOrderUnitParams();
            l_processor.doDeleteAllQuery(AioOrderUnitRow.TYPE);
            l_aioOrderUnitParams = TestDBUtility.getAioOrderUnitRow();
            l_aioOrderUnitParams.setAccountId(333812512246L);
            l_aioOrderUnitParams.setSubAccountId(33381251220301L);
            l_aioOrderUnitParams.setOrderType(OrderTypeEnum.FROM_DEPOSIT_AMOUNT_MARGIN_GUARANTEE);
            l_aioOrderUnitParams.setDeliveryDate(WEB3DateUtility.getDate("20070728","yyyyMMdd"));
            l_aioOrderUnitParams.setOrderStatus(OrderStatusEnum.ACCEPTED);
            TestDBUtility.insertWithDel(l_aioOrderUnitParams);
            l_aioOrderUnitParams.setOrderUnitId(0L);
            l_aioOrderUnitParams.setOrderType(OrderTypeEnum.MARGIN_GUARANTEE_FROM_DEPOSIT_AMOUNT);
            TestDBUtility.insertWithDel(l_aioOrderUnitParams);

            //é¿çs
            WEB3TPPaymentRequisitionManageServiceImpl l_service = new WEB3TPPaymentRequisitionManageServiceImpl();
            WEB3TPAdddepositGenerationInfo l_adddepositGenerationInfo =
                l_service.getAdddepositGenerationInfo(new WEB3GentradeMainAccount(l_mainAccountParams));
            //î‰är
            assertEquals("23.0", ((WEB3TPFirstAdddepositInfo)l_adddepositGenerationInfo.getAdddepositInfo()).firstDepositAmount + "");
        }
        catch (Exception l_ex)
        {
            log.debug(STR_METHOD_NAME,l_ex);
            fail();
        }
        log.exiting(TEST_END + STR_METHOD_NAME);
    }
    
    /**
     * 
     */
    public void test_getAdddepositGenerationInfo_0002()
    {
        final String STR_METHOD_NAME = "test_getAdddepositGenerationInfo_0002()";
        log.entering(TEST_START + STR_METHOD_NAME);

        try
        {
            
            Calendar l_calendar = Calendar.getInstance();
            l_calendar.set(Calendar.YEAR,2007);
            l_calendar.set(Calendar.MONTH,2);
            l_calendar.set(Calendar.DAY_OF_MONTH,14);
            l_calendar.set(Calendar.HOUR_OF_DAY,15);
            l_calendar.set(Calendar.MINUTE,00);
            l_calendar.set(Calendar.SECOND,01);

            Timestamp l_tsBizDate = new Timestamp(l_calendar.getTimeInMillis());

            ThreadLocalSystemAttributesRegistry.setAttribute(
                WEB3GentradeTradingTimeManagement.TIMESTAMP_TAG, l_tsBizDate);

            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                "com.fitechlabs.xtrade.plugin.tc.gentrade.stdimpls.TradingSystemImpl",
                "getSystemTimestamp",
                new Class[] {},
                l_tsBizDate);

            WEB3GentradeTradingClendarContext l_clendarContext = new WEB3GentradeTradingClendarContext();
            ThreadLocalSystemAttributesRegistry.setAttribute("web3.tradingcalendarcontext", l_clendarContext);

            //DB
            MainAccountParams l_mainAccountParams = new MainAccountParams();
            QueryProcessor l_processor = Processors.getDefaultProcessor();
            l_processor.doDeleteAllQuery(MainAccountRow.TYPE);
            l_mainAccountParams = TestDBUtility.getMainAccountRow();
            l_mainAccountParams.setMarginSysAccOpenDiv("3");    //the third case
            l_mainAccountParams.setSecuredLoanSecAccOpenDiv("0");
            TestDBUtility.insertWithDel(l_mainAccountParams);

            InstitutionParams l_institutionParams =  new InstitutionParams();
            l_processor.doDeleteAllQuery(InstitutionRow.TYPE);
            l_institutionParams = TestDBUtility.getInstitutionRow();
            l_institutionParams.setAssetEvaluation("0");
            l_institutionParams.setMaximumAssessment(1L);
            TestDBUtility.insertWithDel(l_institutionParams);

            BranchParams l_branchParams =  new BranchParams();
            l_processor.doDeleteAllQuery(BranchRow.TYPE);
            l_branchParams = TestDBUtility.getBranchRow();
            TestDBUtility.insertWithDel(l_branchParams);

            PaymentRequisitMngParams l_paymentRequisitMngParams = new PaymentRequisitMngParams();
            l_processor.doDeleteAllQuery(PaymentRequisitMngRow.TYPE);
            l_paymentRequisitMngParams.setAccountId(333812512246L);
            l_paymentRequisitMngParams.setAccountCode("2512246");
            l_paymentRequisitMngParams.setBranchCode("381");
            l_paymentRequisitMngParams.setInstitutionCode("0D");
            l_paymentRequisitMngParams.setFamilyName("Family Name");
            l_paymentRequisitMngParams.setAccountAttribute("0");
            l_paymentRequisitMngParams.setCalcDate(WEB3DateUtility.getDate("20070728","yyyyMMdd"));
            l_paymentRequisitMngParams.setCreatedTimestamp(WEB3DateUtility.getDate("20070728","yyyyMMdd"));
            l_paymentRequisitMngParams.setLastUpdatedTimestamp(WEB3DateUtility.getDate("20070728","yyyyMMdd"));
            l_paymentRequisitMngParams.setFirstDepositAmount(23);
            TestDBUtility.insertWithDel(l_paymentRequisitMngParams);

            SubAccountParams l_subAccountParams = new SubAccountParams();
            l_processor.doDeleteAllQuery(SubAccountRow.TYPE);
            l_subAccountParams = TestDBUtility.getSubAccountRow();
            l_subAccountParams.setAccountId(333812512246L);
            l_subAccountParams.setSubAccountType(SubAccountTypeEnum.EQUITY_MARGIN_SUB_ACCOUNT);
            TestDBUtility.insertWithDel(l_subAccountParams);

            BranchPreferencesParams l_branchPreferencesParams = new BranchPreferencesParams();
            l_processor.doDeleteAllQuery(BranchPreferencesRow.TYPE);
            l_branchPreferencesParams = TestDBUtility.getBranchPreferencesRow();
            l_branchPreferencesParams.setName("0");
            l_branchPreferencesParams.setBranchId(33381);
            TestDBUtility.insertWithDel(l_branchPreferencesParams);

            l_processor.doDeleteAllQuery(SystemPreferencesRow.TYPE);

            OrderexecutionEndParams l_qrderexecutionEndParams = new OrderexecutionEndParams();
            l_processor.doDeleteAllQuery(OrderexecutionEndRow.TYPE);
            l_qrderexecutionEndParams.setInstitutionCode("0D");
            l_qrderexecutionEndParams.setProductType(ProductTypeEnum.EQUITY);
            l_qrderexecutionEndParams.setFutureOptionDiv("0");
            l_qrderexecutionEndParams.setCreatedTimestamp(WEB3DateUtility.getDate("20070728","yyyyMMdd"));
            l_qrderexecutionEndParams.setLastUpdatedTimestamp(WEB3DateUtility.getDate("20070728","yyyyMMdd"));
            l_qrderexecutionEndParams.setOrderexecutionEndType("0");
            TestDBUtility.insertWithDel(l_qrderexecutionEndParams);

            TradingpowerCalcConditionParams l_tradingpowerCalcConditionParams = new TradingpowerCalcConditionParams();
            l_processor.doDeleteAllQuery(TradingpowerCalcConditionRow.TYPE);
            l_tradingpowerCalcConditionParams = TestDBUtility.getTradingpowerCalcConditionRow();
            l_tradingpowerCalcConditionParams.setAccountId(333812512246L);
            l_tradingpowerCalcConditionParams.setAdditionalDepositStop("1");
            TestDBUtility.insertWithDel(l_tradingpowerCalcConditionParams);

            TpCalcResultMarginParams l_tpCalcResultMarginParams = new TpCalcResultMarginParams();
            l_processor.doDeleteAllQuery(TpCalcResultMarginRow.TYPE);
            l_tpCalcResultMarginParams = TestDBUtility.getTpCalcResultMarginRow();
            l_tpCalcResultMarginParams.setCreatedTimestamp(WEB3DateUtility.getDate("20070728","yyyyMMdd"));
            l_tpCalcResultMarginParams.setContractAmount0(12);
            TestDBUtility.insertWithDel(l_tpCalcResultMarginParams);

            l_processor.doDeleteAllQuery(ProcessManagementRow.TYPE);

            TpCalcResultMarginDetailParams l_tpCalcResultMarginDetailParams = new TpCalcResultMarginDetailParams();
            l_processor.doDeleteAllQuery(TpCalcResultMarginDetailRow.TYPE);
            l_tpCalcResultMarginDetailParams = TestDBUtility.getTpCalcResultMarginDetailRow();
            TestDBUtility.insertWithDel(l_tpCalcResultMarginDetailParams);

            TpCashBalanceParams l_tpCashBalanceParams = new TpCashBalanceParams();
            l_processor.doDeleteAllQuery(TpCashBalanceRow.TYPE);
            l_tpCashBalanceParams.setAccountId(333812512246L);
            l_tpCashBalanceParams.setSubAccountId(33381251220301L);
            TestDBUtility.insertWithDel(l_tpCashBalanceParams);

            AioOrderUnitParams l_aioOrderUnitParams = new AioOrderUnitParams();
            l_processor.doDeleteAllQuery(AioOrderUnitRow.TYPE);
            l_aioOrderUnitParams = TestDBUtility.getAioOrderUnitRow();
            l_aioOrderUnitParams.setAccountId(333812512246L);
            l_aioOrderUnitParams.setSubAccountId(33381251220301L);
            l_aioOrderUnitParams.setOrderType(OrderTypeEnum.FROM_DEPOSIT_AMOUNT_MARGIN_GUARANTEE);
            l_aioOrderUnitParams.setDeliveryDate(WEB3DateUtility.getDate("20070728","yyyyMMdd"));
            l_aioOrderUnitParams.setOrderStatus(OrderStatusEnum.ACCEPTED);
            TestDBUtility.insertWithDel(l_aioOrderUnitParams);
            l_aioOrderUnitParams.setOrderUnitId(0L);
            l_aioOrderUnitParams.setOrderType(OrderTypeEnum.MARGIN_GUARANTEE_FROM_DEPOSIT_AMOUNT);
            TestDBUtility.insertWithDel(l_aioOrderUnitParams);

            //é¿çs
            WEB3TPPaymentRequisitionManageServiceImpl l_service = new WEB3TPPaymentRequisitionManageServiceImpl();
            WEB3TPAdddepositGenerationInfo l_adddepositGenerationInfo =
                l_service.getAdddepositGenerationInfo(new WEB3GentradeMainAccount(l_mainAccountParams));
            fail();
        }
        catch (WEB3BaseException l_ex)
        {
            log.debug(STR_METHOD_NAME,l_ex);
            assertEquals(WEB3ErrorCatalog.BUSINESS_ERROR_02887, l_ex.getErrorInfo());
        }
        catch (Exception l_ex)
        {
            log.debug(STR_METHOD_NAME,l_ex);
            fail();
        }
        log.exiting(TEST_END + STR_METHOD_NAME);
    }
}
@
