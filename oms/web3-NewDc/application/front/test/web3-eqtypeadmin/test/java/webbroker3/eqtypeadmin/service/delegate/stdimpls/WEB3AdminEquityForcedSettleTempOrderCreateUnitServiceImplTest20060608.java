head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.04.07.01.09.05;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	5444d9d078c63f4;
filename	WEB3AdminEquityForcedSettleTempOrderCreateUnitServiceImplTest20060608.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@package webbroker3.eqtypeadmin.service.delegate.stdimpls;

import java.util.Date;
import java.util.List;

import com.fitechlabs.xtrade.kernel.data.Processors;
import com.fitechlabs.xtrade.kernel.data.QueryProcessor;
import com.fitechlabs.xtrade.plugin.tc.eqtype.EqTypeOrderUnit;
import com.fitechlabs.xtrade.plugin.tc.eqtype.data.EqtypeClosingContractSpecParams;
import com.fitechlabs.xtrade.plugin.tc.eqtype.data.EqtypeContractParams;
import com.fitechlabs.xtrade.plugin.tc.eqtype.data.EqtypeContractRow;
import com.fitechlabs.xtrade.plugin.tc.eqtype.data.EqtypeOrderUnitParams;
import com.fitechlabs.xtrade.plugin.tc.eqtype.data.EqtypeOrderUnitRow;
import com.fitechlabs.xtrade.plugin.tc.eqtype.data.EqtypeProductParams;
import com.fitechlabs.xtrade.plugin.tc.eqtype.data.EqtypeTradedProductParams;
import com.fitechlabs.xtrade.plugin.tc.eqtype.ordersubmitter.io.EqTypeSettleContractOrderEntry;
import com.fitechlabs.xtrade.plugin.tc.gentrade.ContractTypeEnum;
import com.fitechlabs.xtrade.plugin.tc.gentrade.OrderCategEnum;
import com.fitechlabs.xtrade.plugin.tc.gentrade.OrderOpenStatusEnum;
import com.fitechlabs.xtrade.plugin.tc.gentrade.OrderTypeEnum;
import com.fitechlabs.xtrade.plugin.tc.gentrade.ProductTypeEnum;
import com.fitechlabs.xtrade.plugin.tc.gentrade.TaxTypeEnum;
import com.fitechlabs.xtrade.plugin.tc.gentrade.data.BranchParams;
import com.fitechlabs.xtrade.plugin.tc.gentrade.data.MainAccountParams;
import com.fitechlabs.xtrade.plugin.tc.gentrade.data.MarketParams;
import com.fitechlabs.xtrade.plugin.tc.gentrade.data.ProductParams;
import com.fitechlabs.xtrade.plugin.tc.gentrade.data.SubAccountParams;
import com.fitechlabs.xtrade.plugin.tc.gentrade.data.TradedProductParams;
import com.fitechlabs.xtrade.plugin.tc.xbfeq.data.FeqProductParams;
import com.fitechlabs.xtrade.plugin.tc.xbfeq.data.FeqProductRow;
import com.fitechlabs.xtrade.plugin.tc.xbfeq.data.FeqTradedProductParams;

import test.util.TestDBUtility;

import webbroker3.common.WEB3BaseException;
import webbroker3.eqtypeadmin.data.ForcedSettleOrderInqDao;
import webbroker3.eqtypeadmin.data.ForcedSettleOrderInqParams;
import webbroker3.eqtypeadmin.data.ForcedSettleOrderInqRow;
import webbroker3.equity.WEB3EquityContract;
import webbroker3.equity.WEB3EquityRealizedProfitAndLossPrice;
import webbroker3.equity.WEB3EquityTradedProduct;
import webbroker3.equity.data.HostEqtypeOrderAllParams;
import webbroker3.gentrade.WEB3GentradeCommission;
import webbroker3.gentrade.WEB3GentradeSubAccount;
import webbroker3.gentrade.WEB3GentradeTradingTimeManagementForMock;
import webbroker3.mock.TestBaseForMock;
import webbroker3.tradingpower.WEB3TPContractForcedSettleResult;
import webbroker3.triggerorder.base.data.RsvEqOrderUnitParams;
import webbroker3.triggerorder.base.data.RsvEqOrderUnitRow;
import webbroker3.util.WEB3LogUtility;

public class WEB3AdminEquityForcedSettleTempOrderCreateUnitServiceImplTest20060608 extends TestBaseForMock
{
    /**
     * ログユーティリティ<BR>
     */
    private static WEB3LogUtility log =
         WEB3LogUtility.getInstance(
                 WEB3AdminEquityForcedSettleTempOrderCreateUnitServiceImplTest20060608.class);
    WEB3AdminEquityForcedSettleTempOrderCreateUnitServiceImplForTest l_serviceImpl;
    
    public WEB3AdminEquityForcedSettleTempOrderCreateUnitServiceImplTest20060608(String arg0)
    {
        super(arg0);
    }

    protected void setUp() throws Exception
    {
        l_serviceImpl =
            new WEB3AdminEquityForcedSettleTempOrderCreateUnitServiceImplForTest();
        super.setUp();
    }

    protected void tearDown() throws Exception
    {
        super.tearDown();
    }

    public void testExecTempOrderCreation_T01()
    {
        final String STR_METHOD_NAME = "testExecTempOrderCreation_T01()";
        log.entering(STR_METHOD_NAME);
        try
        {
            TestBaseForMock.MOCK_MANAGER.setIsMockUsed(true);
            
            //EqtypeContractParams
            TestDBUtility.deleteAll(EqtypeContractParams.TYPE);
            EqtypeContractParams l_eqtypeContractParams =
                TestDBUtility.getEqtypeContractRow();
            l_eqtypeContractParams.setTaxType(TaxTypeEnum.NORMAL);
            l_eqtypeContractParams.setContractType(ContractTypeEnum.LONG );
            l_eqtypeContractParams.setProductId(3304148080000L);
            TestDBUtility.insertWithDel(l_eqtypeContractParams);

            //EqtypeClosingContractSpecParams
            TestDBUtility.deleteAll(EqtypeClosingContractSpecParams.TYPE);
            EqtypeClosingContractSpecParams l_specParams =
                TestDBUtility.getEqtypeClosingContractSpecRow();
            l_specParams.setContractId(l_eqtypeContractParams.getContractId());
            TestDBUtility.insertWithDel(l_specParams);
            
            //EqtypeOrderUnitParams
            //1
            TestDBUtility.deleteAll(EqtypeOrderUnitParams.TYPE);
            EqtypeOrderUnitParams l_eqtypeOrderUnitParams =
                TestDBUtility.getEqtypeOrderUnitRow();
            l_eqtypeOrderUnitParams.setOrderId(l_specParams.getOrderId());
            l_eqtypeOrderUnitParams.setOrderOpenStatus(OrderOpenStatusEnum.OPEN);
            l_eqtypeOrderUnitParams.setOrderCateg(OrderCategEnum.SWAP_MARGIN);
            TestDBUtility.insertWithDel(l_eqtypeOrderUnitParams);
            
            //2
            QueryProcessor l_processor = Processors.getDefaultProcessor();
            l_eqtypeOrderUnitParams.setOrderUnitId(3304148080002L);
            l_eqtypeOrderUnitParams.setOrderCateg(OrderCategEnum.CLOSE_MARGIN);
            l_eqtypeOrderUnitParams.setOrderConditionType("2");
            l_eqtypeOrderUnitParams.setRequestType("1");
            l_processor.doInsertQuery(l_eqtypeOrderUnitParams);
            
            
            //MainAccountParams
            TestDBUtility.deleteAll(MainAccountParams.TYPE);
            MainAccountParams l_mainAccountParams = TestDBUtility.getMainAccountRow();
            l_mainAccountParams.setAccountId(l_eqtypeContractParams.getAccountId());
            TestDBUtility.insertWithDel(l_mainAccountParams);

            //SubAccountParams
            TestDBUtility.deleteAll(SubAccountParams.TYPE);
            SubAccountParams l_subaccountParams = TestDBUtility.getSubAccountRow();
            l_subaccountParams.setAccountId(l_mainAccountParams.getAccountId());
            TestDBUtility.insertWithDel(l_subaccountParams);
            
            //BranchParams
            TestDBUtility.deleteAll(BranchParams.TYPE);
            BranchParams l_branchParams = TestDBUtility.getBranchRow();
            l_branchParams.setBranchId(l_mainAccountParams.getBranchId());
            TestDBUtility.insertWithDel(l_branchParams);

            //TradedProductParams
            TestDBUtility.deleteAll(TradedProductParams.TYPE);
            TradedProductParams l_tradedProductParams =
                TestDBUtility.getTradedProductRow();
            l_tradedProductParams.setProductId(3304148080000L);
            l_tradedProductParams.setMarketId(l_eqtypeContractParams.getMarketId());
            l_tradedProductParams.setProductType(ProductTypeEnum.EQUITY);
            l_tradedProductParams.setDailyDeliveryDate(new Date(2005-1900,02,02));
            TestDBUtility.insertWithDel(l_tradedProductParams);

            //ProductParams
            TestDBUtility.deleteAll(ProductParams.TYPE);
            ProductParams l_productParams = TestDBUtility.getProductRow();
            l_productParams.setProductId(3304148080000L);
            l_productParams.setProductType(ProductTypeEnum.EQUITY);
            TestDBUtility.insertWithDel(l_productParams);

            //EqtypeTradedProductParams
            TestDBUtility.deleteAll(EqtypeTradedProductParams.TYPE);
            EqtypeTradedProductParams l_eqtypeTradedProductParams =
                TestDBUtility.getEqtypeTradedProductRow();
            l_eqtypeTradedProductParams.setTradedProductId(l_tradedProductParams.getTradedProductId());
            l_eqtypeTradedProductParams.setProductId(3304148080000L);
            TestDBUtility.insertWithDel(l_eqtypeTradedProductParams);

            //EqtypeProductParams
            TestDBUtility.deleteAll(EqtypeProductParams.TYPE);
            EqtypeProductParams l_eqtypeProductParams =
                TestDBUtility.getEqtypeProductRow();
            l_eqtypeProductParams.setProductId(3304148080000L);
            TestDBUtility.insertWithDel(l_eqtypeProductParams);
            
            //HostEqtypeOrderAllRow
            TestDBUtility.deleteAll(HostEqtypeOrderAllParams.TYPE);
            HostEqtypeOrderAllParams l_hostEqtypeOrderAllParams =
                TestDBUtility.getHostEqtypeOrderAllRow();
            l_hostEqtypeOrderAllParams.setRequestCode("AI801");
            l_hostEqtypeOrderAllParams.setInstitutionCode("0D");
            l_hostEqtypeOrderAllParams.setBranchCode("381");
            l_hostEqtypeOrderAllParams.setOrderRequestNumber("1");
            l_hostEqtypeOrderAllParams.setCorpCode("000000000000000000");
            l_hostEqtypeOrderAllParams.setStatus("0");
            TestDBUtility.insertWithDelAndCommit(l_hostEqtypeOrderAllParams);

            //RsvEqOrderUnitRow
            TestDBUtility.deleteAll(RsvEqOrderUnitRow.TYPE);
            //ForcedSettleOrderInqParams
            TestDBUtility.deleteAll(ForcedSettleOrderInqParams.TYPE);
            
            //MarketParams
            TestDBUtility.deleteAll(MarketParams.TYPE);
            MarketParams l_marketParams = TestDBUtility.getMarketRow();
            l_marketParams.setMarketId(l_tradedProductParams.getMarketId());
            TestDBUtility.insertWithDel(l_marketParams);
            
            WEB3GentradeTradingTimeManagementForMock.mockGetOrderBizDate(new Date(2005-1900,01,02));

            //forMock
            //WEB3EquityBizLogicProvider
            WEB3GentradeCommission l_commission = new WEB3GentradeCommission();
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                "webbroker3.equity.WEB3EquityBizLogicProvider",
                "createCommission",
                new Class[] {WEB3GentradeSubAccount.class, String.class, Date.class, String.class,
                    String.class, double.class, OrderCategEnum.class},
                l_commission);

            //WEB3EquityRealizedProfitAndLossPrice
            WEB3EquityRealizedProfitAndLossPrice l_lossPrice =
                new WEB3EquityRealizedProfitAndLossPrice();
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                "webbroker3.equity.WEB3EquityOrderManager",
                "calcEstimatedRealizedProfitAndLossAmount",
                new Class[] {WEB3GentradeCommission.class, double.class,
                        WEB3GentradeSubAccount.class, WEB3EquityTradedProduct.class,
                        EqTypeSettleContractOrderEntry[].class, double.class,
                        EqTypeOrderUnit.class,
                        double.class,
                        double.class,
                        boolean.class,
                        WEB3EquityContract.class},
                        l_lossPrice);
            
            l_serviceImpl.execTempOrderCreation(
                l_eqtypeContractParams,
                null,null,
                false);
            
            List l_resultList = l_processor.doFindAllQuery(ForcedSettleOrderInqRow.TYPE);
            assertEquals(1, l_resultList.size());
            ForcedSettleOrderInqRow l_resultRow = (ForcedSettleOrderInqRow)l_resultList.get(0);
            assertEquals(l_eqtypeOrderUnitParams.getOrderId(), l_resultRow.getOrderId());
            log.debug(STR_METHOD_NAME + "------------------->ok");
        }
        catch(Exception l_exc)
        {
            log.exiting(STR_METHOD_NAME);
            l_exc.printStackTrace();
            fail();
        }
        log.exiting(STR_METHOD_NAME);
    }


    public void testSubmitRepayTempOrder_T01()
    {
        final String STR_METHOD_NAME = "testSubmitRepayTempOrder_T01()";
        log.entering(STR_METHOD_NAME);
        try
        {
            TestBaseForMock.MOCK_MANAGER.setIsMockUsed(true);
            
            //EqtypeContractParams
            TestDBUtility.deleteAll(EqtypeContractParams.TYPE);
            EqtypeContractParams l_eqtypeContractParams =
                TestDBUtility.getEqtypeContractRow();
            l_eqtypeContractParams.setTaxType(TaxTypeEnum.NORMAL);
            l_eqtypeContractParams.setContractType(ContractTypeEnum.LONG );
            l_eqtypeContractParams.setCloseDate(new Date(2006-1900,01,02));
            TestDBUtility.insertWithDel(l_eqtypeContractParams);

            //EqtypeClosingContractSpecParams
            TestDBUtility.deleteAll(EqtypeClosingContractSpecParams.TYPE);
            EqtypeClosingContractSpecParams l_specParams =
                TestDBUtility.getEqtypeClosingContractSpecRow();
            l_specParams.setContractId(l_eqtypeContractParams.getContractId());
            TestDBUtility.insertWithDel(l_specParams);
            
            //EqtypeOrderUnitParams
            TestDBUtility.deleteAll(EqtypeOrderUnitParams.TYPE);
            EqtypeOrderUnitParams l_eqtypeOrderUnitParams =
                TestDBUtility.getEqtypeOrderUnitRow();
            l_eqtypeOrderUnitParams.setOrderId(l_specParams.getOrderId());
            l_eqtypeOrderUnitParams.setOrderOpenStatus(OrderOpenStatusEnum.OPEN);
            l_eqtypeOrderUnitParams.setOrderCateg(OrderCategEnum.SWAP_MARGIN);
            TestDBUtility.insertWithDel(l_eqtypeOrderUnitParams);

            //MainAccountParams
            TestDBUtility.deleteAll(MainAccountParams.TYPE);
            MainAccountParams l_mainAccountParams = TestDBUtility.getMainAccountRow();
            l_mainAccountParams.setAccountId(l_eqtypeContractParams.getAccountId());
            TestDBUtility.insertWithDel(l_mainAccountParams);

            //SubAccountParams
            TestDBUtility.deleteAll(SubAccountParams.TYPE);
            SubAccountParams l_subaccountParams = TestDBUtility.getSubAccountRow();
            l_subaccountParams.setAccountId(l_mainAccountParams.getAccountId());
            TestDBUtility.insertWithDel(l_subaccountParams);
            
            //BranchParams
            TestDBUtility.deleteAll(BranchParams.TYPE);
            BranchParams l_branchParams = TestDBUtility.getBranchRow();
            l_branchParams.setBranchId(l_mainAccountParams.getBranchId());
            TestDBUtility.insertWithDel(l_branchParams);
            
            //TradedProductParams
            TestDBUtility.deleteAll(TradedProductParams.TYPE);
            TradedProductParams l_tradedProductParams =
                TestDBUtility.getTradedProductRow();
            l_tradedProductParams.setProductId(l_eqtypeContractParams.getProductId());
            l_tradedProductParams.setMarketId(l_eqtypeContractParams.getMarketId());
            l_tradedProductParams.setProductType(ProductTypeEnum.EQUITY);
            l_tradedProductParams.setDailyDeliveryDate(new Date(2005-1900,02,02));

            //ProductParams
            TestDBUtility.deleteAll(ProductParams.TYPE);
            ProductParams l_productParams = TestDBUtility.getProductRow();
            l_productParams.setProductId(l_tradedProductParams.getProductId());
            l_productParams.setProductType(ProductTypeEnum.EQUITY);
            TestDBUtility.insertWithDel(l_productParams);

            //EqtypeTradedProductParams
            TestDBUtility.deleteAll(EqtypeTradedProductParams.TYPE);
            EqtypeTradedProductParams l_eqtypeTradedProductParams =
                TestDBUtility.getEqtypeTradedProductRow();
            l_eqtypeTradedProductParams.setTradedProductId(l_tradedProductParams.getTradedProductId());
            l_eqtypeTradedProductParams.setProductId(l_tradedProductParams.getProductId());
            TestDBUtility.insertWithDel(l_eqtypeTradedProductParams);

            //EqtypeProductParams
            TestDBUtility.deleteAll(EqtypeProductParams.TYPE);
            EqtypeProductParams l_eqtypeProductParams =
                TestDBUtility.getEqtypeProductRow();
            l_eqtypeProductParams.setProductId(l_tradedProductParams.getProductId());
            TestDBUtility.insertWithDel(l_eqtypeProductParams);
            
            //MarketParams
            TestDBUtility.deleteAll(MarketParams.TYPE);
            MarketParams l_marketParams = TestDBUtility.getMarketRow();
            l_marketParams.setMarketId(l_tradedProductParams.getMarketId());
            TestDBUtility.insertWithDel(l_marketParams);
            
            WEB3GentradeTradingTimeManagementForMock.mockGetOrderBizDate(new Date(2006-1900,01,02));
            
            //forMock
            //WEB3EquityBizLogicProvider
            WEB3GentradeCommission l_commission = new WEB3GentradeCommission();
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                "webbroker3.equity.WEB3EquityBizLogicProvider",
                "createCommission",
                new Class[] {WEB3GentradeSubAccount.class, String.class, Date.class, String.class,
                    String.class, double.class, OrderCategEnum.class},
                l_commission);

            //WEB3EquityRealizedProfitAndLossPrice
            WEB3EquityRealizedProfitAndLossPrice l_lossPrice =
                new WEB3EquityRealizedProfitAndLossPrice();
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                "webbroker3.equity.WEB3EquityOrderManager",
                "calcEstimatedRealizedProfitAndLossAmount",
                new Class[] {WEB3GentradeCommission.class, double.class,
                        WEB3GentradeSubAccount.class, WEB3EquityTradedProduct.class,
                        EqTypeSettleContractOrderEntry[].class, double.class,
                        EqTypeOrderUnit.class,
                        double.class,
                        double.class,
                        boolean.class,
                        WEB3EquityContract.class},
                        l_lossPrice);
            
            l_serviceImpl.submitRepayTempOrder(
                l_eqtypeContractParams, null, null);
        }
        catch(Exception l_exc)
        {
            log.exiting(STR_METHOD_NAME);
            l_exc.printStackTrace();
            fail();
        }
        log.exiting(STR_METHOD_NAME);
    }
    

    public void testSubmitRepayTempOrder_T02()
    {
        final String STR_METHOD_NAME = "testSubmitRepayTempOrder_T02()";
        log.entering(STR_METHOD_NAME);
        try
        {
            TestBaseForMock.MOCK_MANAGER.setIsMockUsed(true);
            
            //EqtypeContractParams
            TestDBUtility.deleteAll(EqtypeContractParams.TYPE);
            EqtypeContractParams l_eqtypeContractParams =
                TestDBUtility.getEqtypeContractRow();
            l_eqtypeContractParams.setTaxType(TaxTypeEnum.NORMAL);
            l_eqtypeContractParams.setContractType(ContractTypeEnum.LONG );
            TestDBUtility.insertWithDel(l_eqtypeContractParams);

            //EqtypeClosingContractSpecParams
            TestDBUtility.deleteAll(EqtypeClosingContractSpecParams.TYPE);
            EqtypeClosingContractSpecParams l_specParams =
                TestDBUtility.getEqtypeClosingContractSpecRow();
            l_specParams.setContractId(l_eqtypeContractParams.getContractId());
            TestDBUtility.insertWithDel(l_specParams);
            
            //EqtypeOrderUnitParams
            TestDBUtility.deleteAll(EqtypeOrderUnitParams.TYPE);
            EqtypeOrderUnitParams l_eqtypeOrderUnitParams =
                TestDBUtility.getEqtypeOrderUnitRow();
            l_eqtypeOrderUnitParams.setOrderId(l_specParams.getOrderId());
            l_eqtypeOrderUnitParams.setOrderOpenStatus(OrderOpenStatusEnum.OPEN);
            l_eqtypeOrderUnitParams.setOrderCateg(OrderCategEnum.SWAP_MARGIN);
            TestDBUtility.insertWithDel(l_eqtypeOrderUnitParams);

            //MainAccountParams
            TestDBUtility.deleteAll(MainAccountParams.TYPE);
            MainAccountParams l_mainAccountParams = TestDBUtility.getMainAccountRow();
            l_mainAccountParams.setAccountId(l_eqtypeContractParams.getAccountId());
            TestDBUtility.insertWithDel(l_mainAccountParams);

            //SubAccountParams
            TestDBUtility.deleteAll(SubAccountParams.TYPE);
            SubAccountParams l_subaccountParams = TestDBUtility.getSubAccountRow();
            l_subaccountParams.setAccountId(l_mainAccountParams.getAccountId());
            TestDBUtility.insertWithDel(l_subaccountParams);
            
            //BranchParams
            TestDBUtility.deleteAll(BranchParams.TYPE);
            BranchParams l_branchParams = TestDBUtility.getBranchRow();
            l_branchParams.setBranchId(l_mainAccountParams.getBranchId());
            TestDBUtility.insertWithDel(l_branchParams);
            
            //TradedProductParams
            TestDBUtility.deleteAll(TradedProductParams.TYPE);
            TradedProductParams l_tradedProductParams =
                TestDBUtility.getTradedProductRow();
            l_tradedProductParams.setProductId(l_eqtypeContractParams.getProductId());
            l_tradedProductParams.setMarketId(l_eqtypeContractParams.getMarketId());
            l_tradedProductParams.setProductType(ProductTypeEnum.EQUITY);
            l_tradedProductParams.setDailyDeliveryDate(new Date(2005-1900,02,02));
            TestDBUtility.insertWithDel(l_tradedProductParams);
            
            //ProductParams
            TestDBUtility.deleteAll(ProductParams.TYPE);
            ProductParams l_productParams = TestDBUtility.getProductRow();
            l_productParams.setProductId(l_tradedProductParams.getProductId());
            l_productParams.setProductType(ProductTypeEnum.EQUITY);
            TestDBUtility.insertWithDel(l_productParams);

            //EqtypeTradedProductParams
            TestDBUtility.deleteAll(EqtypeTradedProductParams.TYPE);
            EqtypeTradedProductParams l_eqtypeTradedProductParams =
                TestDBUtility.getEqtypeTradedProductRow();
            l_eqtypeTradedProductParams.setTradedProductId(l_tradedProductParams.getTradedProductId());
            l_eqtypeTradedProductParams.setProductId(l_tradedProductParams.getProductId());
            TestDBUtility.insertWithDel(l_eqtypeTradedProductParams);

            //EqtypeProductParams
            TestDBUtility.deleteAll(EqtypeProductParams.TYPE);
            EqtypeProductParams l_eqtypeProductParams =
                TestDBUtility.getEqtypeProductRow();
            l_eqtypeProductParams.setProductId(l_tradedProductParams.getProductId());
            TestDBUtility.insertWithDel(l_eqtypeProductParams);
            
            //MarketParams
            TestDBUtility.deleteAll(MarketParams.TYPE);
            MarketParams l_marketParams = TestDBUtility.getMarketRow();
            l_marketParams.setMarketId(l_tradedProductParams.getMarketId());
            TestDBUtility.insertWithDel(l_marketParams);
            
            WEB3GentradeTradingTimeManagementForMock.mockGetOrderBizDate(new Date(2005-1900,01,02));
            
            //forMock
            //WEB3EquityBizLogicProvider
            WEB3GentradeCommission l_commission = new WEB3GentradeCommission();
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                "webbroker3.equity.WEB3EquityBizLogicProvider",
                "createCommission",
                new Class[] {WEB3GentradeSubAccount.class, String.class, Date.class, String.class,
                    String.class, double.class, OrderCategEnum.class},
                l_commission);

            //WEB3EquityRealizedProfitAndLossPrice
            WEB3EquityRealizedProfitAndLossPrice l_lossPrice =
                new WEB3EquityRealizedProfitAndLossPrice();
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                "webbroker3.equity.WEB3EquityOrderManager",
                "calcEstimatedRealizedProfitAndLossAmount",
                new Class[] {WEB3GentradeCommission.class, double.class,
                        WEB3GentradeSubAccount.class, WEB3EquityTradedProduct.class,
                        EqTypeSettleContractOrderEntry[].class, double.class,
                        EqTypeOrderUnit.class,
                        double.class,
                        double.class,
                        boolean.class,
                        WEB3EquityContract.class},
                        l_lossPrice);
            
            l_serviceImpl.submitRepayTempOrder(
                l_eqtypeContractParams, null, null);
        }
        catch(Exception l_exc)
        {
            log.exiting(STR_METHOD_NAME);
            l_exc.printStackTrace();
            fail();
        }
        log.exiting(STR_METHOD_NAME);
    }
    
    public void testExpireSettleOrder_T01()
    {
        final String STR_METHOD_NAME = "testExpireSettleOrder_T02()";
        log.entering(STR_METHOD_NAME);
        try
        {
            //EqtypeOrderUnitParams
            TestDBUtility.deleteAll(EqtypeOrderUnitParams.TYPE);
            EqtypeOrderUnitParams l_eqtypeOrderUnitParams =
                TestDBUtility.getEqtypeOrderUnitRow();
            l_eqtypeOrderUnitParams.setOrderOpenStatus(OrderOpenStatusEnum.OPEN);
            l_eqtypeOrderUnitParams.setOrderCateg(OrderCategEnum.SWAP_MARGIN);
            l_eqtypeOrderUnitParams.setOrderRequestNumber("1");
            TestDBUtility.insertWithDel(l_eqtypeOrderUnitParams);
            
            //MainAccountParams
            TestDBUtility.deleteAll(MainAccountParams.TYPE);
            MainAccountParams l_mainAccountParams = TestDBUtility.getMainAccountRow();
            l_mainAccountParams.setAccountId(l_eqtypeOrderUnitParams.getAccountId());
            TestDBUtility.insertWithDel(l_mainAccountParams);

            //BranchParams
            TestDBUtility.deleteAll(BranchParams.TYPE);
            BranchParams l_branchParams = TestDBUtility.getBranchRow();
            l_branchParams.setBranchId(l_mainAccountParams.getBranchId());
            TestDBUtility.insertWithDel(l_branchParams);

            //TradedProductParams
            TestDBUtility.deleteAll(TradedProductParams.TYPE);
            TradedProductParams l_tradedProductParams =
                TestDBUtility.getTradedProductRow();
            l_tradedProductParams.setProductId(l_eqtypeOrderUnitParams.getProductId());
            l_tradedProductParams.setMarketId(l_eqtypeOrderUnitParams.getMarketId());
            l_tradedProductParams.setProductType(ProductTypeEnum.EQUITY);
            l_tradedProductParams.setDailyDeliveryDate(new Date(2005-1900,02,02));
            TestDBUtility.insertWithDel(l_tradedProductParams);

            //ProductParams
            TestDBUtility.deleteAll(ProductParams.TYPE);
            ProductParams l_productParams = TestDBUtility.getProductRow();
            l_productParams.setProductId(l_tradedProductParams.getProductId());
            l_productParams.setProductType(ProductTypeEnum.EQUITY);
            TestDBUtility.insertWithDel(l_productParams);

            //EqtypeTradedProductParams
            TestDBUtility.deleteAll(EqtypeTradedProductParams.TYPE);
            EqtypeTradedProductParams l_eqtypeTradedProductParams =
                TestDBUtility.getEqtypeTradedProductRow();
            l_eqtypeTradedProductParams.setTradedProductId(l_tradedProductParams.getTradedProductId());
            l_eqtypeTradedProductParams.setProductId(l_tradedProductParams.getProductId());
            TestDBUtility.insertWithDel(l_eqtypeTradedProductParams);

            //EqtypeProductParams
            TestDBUtility.deleteAll(EqtypeProductParams.TYPE);
            EqtypeProductParams l_eqtypeProductParams =
                TestDBUtility.getEqtypeProductRow();
            l_eqtypeProductParams.setProductId(l_tradedProductParams.getProductId());
            TestDBUtility.insertWithDel(l_eqtypeProductParams);
            
            //HostEqtypeOrderAllRow
            TestDBUtility.deleteAll(HostEqtypeOrderAllParams.TYPE);
            HostEqtypeOrderAllParams l_hostEqtypeOrderAllParams =
                TestDBUtility.getHostEqtypeOrderAllRow();
            l_hostEqtypeOrderAllParams.setRequestCode("AI801");
            l_hostEqtypeOrderAllParams.setInstitutionCode("0D");
            l_hostEqtypeOrderAllParams.setBranchCode("381");
            l_hostEqtypeOrderAllParams.setOrderRequestNumber("1");
            l_hostEqtypeOrderAllParams.setCorpCode("000000000000000000");
            l_hostEqtypeOrderAllParams.setStatus("0");
            TestDBUtility.insertWithDelAndCommit(l_hostEqtypeOrderAllParams);

            //RsvEqOrderUnitRow
            TestDBUtility.deleteAll(RsvEqOrderUnitRow.TYPE);
            l_serviceImpl.expireSettleOrder(l_eqtypeOrderUnitParams);
            
            //已驗證 HostEqtypeOrderAllRow  Status = 4
        }
        catch(Exception l_exc)
        {
            log.exiting(STR_METHOD_NAME);
            fail();
        }
        log.exiting(STR_METHOD_NAME);
    }

    public void testExpireSettleOrder_T02()
    {
        final String STR_METHOD_NAME = "testExpireSettleOrder_T02()";
        log.entering(STR_METHOD_NAME);
        try
        {
            //EqtypeOrderUnitParams
            TestDBUtility.deleteAll(EqtypeOrderUnitParams.TYPE);
            EqtypeOrderUnitParams l_eqtypeOrderUnitParams =
                TestDBUtility.getEqtypeOrderUnitRow();
            l_eqtypeOrderUnitParams.setOrderOpenStatus(OrderOpenStatusEnum.OPEN);
            l_eqtypeOrderUnitParams.setOrderCateg(OrderCategEnum.SWAP_MARGIN);
            l_eqtypeOrderUnitParams.setOrderRequestNumber("1");
            l_eqtypeOrderUnitParams.setOrderConditionType("1");
            l_eqtypeOrderUnitParams.setRequestType("0");
            TestDBUtility.insertWithDel(l_eqtypeOrderUnitParams);
            
            //MainAccountParams
            TestDBUtility.deleteAll(MainAccountParams.TYPE);
            MainAccountParams l_mainAccountParams = TestDBUtility.getMainAccountRow();
            l_mainAccountParams.setAccountId(l_eqtypeOrderUnitParams.getAccountId());
            TestDBUtility.insertWithDel(l_mainAccountParams);

            //BranchParams
            TestDBUtility.deleteAll(BranchParams.TYPE);
            BranchParams l_branchParams = TestDBUtility.getBranchRow();
            l_branchParams.setBranchId(l_mainAccountParams.getBranchId());
            TestDBUtility.insertWithDel(l_branchParams);

            //TradedProductParams
            TestDBUtility.deleteAll(TradedProductParams.TYPE);
            TradedProductParams l_tradedProductParams =
                TestDBUtility.getTradedProductRow();
            l_tradedProductParams.setProductId(l_eqtypeOrderUnitParams.getProductId());
            l_tradedProductParams.setMarketId(l_eqtypeOrderUnitParams.getMarketId());
            l_tradedProductParams.setProductType(ProductTypeEnum.EQUITY);
            l_tradedProductParams.setDailyDeliveryDate(new Date(2005-1900,02,02));
            TestDBUtility.insertWithDel(l_tradedProductParams);

            //ProductParams
            TestDBUtility.deleteAll(ProductParams.TYPE);
            ProductParams l_productParams = TestDBUtility.getProductRow();
            l_productParams.setProductId(l_tradedProductParams.getProductId());
            l_productParams.setProductType(ProductTypeEnum.EQUITY);
            TestDBUtility.insertWithDel(l_productParams);

            //EqtypeTradedProductParams
            TestDBUtility.deleteAll(EqtypeTradedProductParams.TYPE);
            EqtypeTradedProductParams l_eqtypeTradedProductParams =
                TestDBUtility.getEqtypeTradedProductRow();
            l_eqtypeTradedProductParams.setTradedProductId(l_tradedProductParams.getTradedProductId());
            l_eqtypeTradedProductParams.setProductId(l_tradedProductParams.getProductId());
            TestDBUtility.insertWithDel(l_eqtypeTradedProductParams);

            //EqtypeProductParams
            TestDBUtility.deleteAll(EqtypeProductParams.TYPE);
            EqtypeProductParams l_eqtypeProductParams =
                TestDBUtility.getEqtypeProductRow();
            l_eqtypeProductParams.setProductId(l_tradedProductParams.getProductId());
            TestDBUtility.insertWithDel(l_eqtypeProductParams);
            
            //HostEqtypeOrderAllRow
            TestDBUtility.deleteAll(HostEqtypeOrderAllParams.TYPE);
            HostEqtypeOrderAllParams l_hostEqtypeOrderAllParams =
                TestDBUtility.getHostEqtypeOrderAllRow();
            l_hostEqtypeOrderAllParams.setRequestCode("AI801");
            l_hostEqtypeOrderAllParams.setInstitutionCode("0D");
            l_hostEqtypeOrderAllParams.setBranchCode("381");
            l_hostEqtypeOrderAllParams.setOrderRequestNumber("1");
            l_hostEqtypeOrderAllParams.setCorpCode("000000000000000000");
            l_hostEqtypeOrderAllParams.setStatus("0");
            TestDBUtility.insertWithDel(l_hostEqtypeOrderAllParams);

            //RsvEqOrderUnitRow
            TestDBUtility.deleteAll(RsvEqOrderUnitRow.TYPE);
            RsvEqOrderUnitParams l_rsvEqOrderUnitParams =
                TestDBUtility.getRsvEqOrderUnitRow();
            l_rsvEqOrderUnitParams.setParentOrderId(l_eqtypeOrderUnitParams.getOrderId());
            l_rsvEqOrderUnitParams.setOrderOpenStatus(OrderOpenStatusEnum.OPEN);
            l_rsvEqOrderUnitParams.setForcedExpireType("0");
            TestDBUtility.insertWithDel(l_rsvEqOrderUnitParams);

            QueryProcessor processor = Processors.getDefaultProcessor();
            l_serviceImpl.expireSettleOrder(l_eqtypeOrderUnitParams);
            List l_resultList = processor.doFindAllQuery(RsvEqOrderUnitRow.TYPE);
            assertEquals(1, l_resultList.size());
            RsvEqOrderUnitRow l_resultRow = (RsvEqOrderUnitRow)l_resultList.get(0);
            assertEquals("1", l_resultRow.getForcedExpireType());
            log.debug(STR_METHOD_NAME + "------------------->ok");
            
        }
        catch(Exception l_exc)
        {
            log.exiting(STR_METHOD_NAME);
            fail();
        }
        log.exiting(STR_METHOD_NAME);
    }
    
    public void testExpireSettleOrder_T03()
    {
        final String STR_METHOD_NAME = "testExpireSettleOrder_T03()";
        log.entering(STR_METHOD_NAME);
        try
        {
            //EqtypeOrderUnitParams
            TestDBUtility.deleteAll(EqtypeOrderUnitParams.TYPE);
            EqtypeOrderUnitParams l_eqtypeOrderUnitParams =
                TestDBUtility.getEqtypeOrderUnitRow();
            l_eqtypeOrderUnitParams.setOrderOpenStatus(OrderOpenStatusEnum.OPEN);
            l_eqtypeOrderUnitParams.setOrderCateg(OrderCategEnum.SWAP_MARGIN);
            l_eqtypeOrderUnitParams.setOrderRequestNumber("1");
            l_eqtypeOrderUnitParams.setOrderConditionType("1");
            l_eqtypeOrderUnitParams.setRequestType("0");
            TestDBUtility.insertWithDel(l_eqtypeOrderUnitParams);
            
            //MainAccountParams
            TestDBUtility.deleteAll(MainAccountParams.TYPE);
            MainAccountParams l_mainAccountParams = TestDBUtility.getMainAccountRow();
            l_mainAccountParams.setAccountId(l_eqtypeOrderUnitParams.getAccountId());
            TestDBUtility.insertWithDel(l_mainAccountParams);

            //BranchParams
            TestDBUtility.deleteAll(BranchParams.TYPE);
            BranchParams l_branchParams = TestDBUtility.getBranchRow();
            l_branchParams.setBranchId(l_mainAccountParams.getBranchId());
            TestDBUtility.insertWithDel(l_branchParams);

            //TradedProductParams
            TestDBUtility.deleteAll(TradedProductParams.TYPE);
            TradedProductParams l_tradedProductParams =
                TestDBUtility.getTradedProductRow();
            l_tradedProductParams.setProductId(l_eqtypeOrderUnitParams.getProductId());
            l_tradedProductParams.setMarketId(l_eqtypeOrderUnitParams.getMarketId());
            l_tradedProductParams.setProductType(ProductTypeEnum.EQUITY);
            l_tradedProductParams.setDailyDeliveryDate(new Date(2005-1900,02,02));
            TestDBUtility.insertWithDel(l_tradedProductParams);



            //ProductParams
            TestDBUtility.deleteAll(ProductParams.TYPE);
            ProductParams l_productParams = TestDBUtility.getProductRow();
            l_productParams.setProductId(l_tradedProductParams.getProductId());
            l_productParams.setProductType(ProductTypeEnum.EQUITY);
            TestDBUtility.insertWithDel(l_productParams);

            //EqtypeTradedProductParams
            TestDBUtility.deleteAll(EqtypeTradedProductParams.TYPE);
            EqtypeTradedProductParams l_eqtypeTradedProductParams =
                TestDBUtility.getEqtypeTradedProductRow();
            l_eqtypeTradedProductParams.setTradedProductId(l_tradedProductParams.getTradedProductId());
            l_eqtypeTradedProductParams.setProductId(l_tradedProductParams.getProductId());
            TestDBUtility.insertWithDel(l_eqtypeTradedProductParams);

            //EqtypeProductParams
            TestDBUtility.deleteAll(EqtypeProductParams.TYPE);
            EqtypeProductParams l_eqtypeProductParams =
                TestDBUtility.getEqtypeProductRow();
            l_eqtypeProductParams.setProductId(l_tradedProductParams.getProductId());
            TestDBUtility.insertWithDel(l_eqtypeProductParams);
            
            //HostEqtypeOrderAllRow
            TestDBUtility.deleteAll(HostEqtypeOrderAllParams.TYPE);
            HostEqtypeOrderAllParams l_hostEqtypeOrderAllParams =
                TestDBUtility.getHostEqtypeOrderAllRow();
            l_hostEqtypeOrderAllParams.setRequestCode("AI801");
            l_hostEqtypeOrderAllParams.setInstitutionCode("0D");
            l_hostEqtypeOrderAllParams.setBranchCode("381");
            l_hostEqtypeOrderAllParams.setOrderRequestNumber("1");
            l_hostEqtypeOrderAllParams.setCorpCode("000000000000000000");
            l_hostEqtypeOrderAllParams.setStatus("0");
            TestDBUtility.insertWithDel(l_hostEqtypeOrderAllParams);

            //RsvEqOrderUnitRow
            TestDBUtility.deleteAll(RsvEqOrderUnitRow.TYPE);
            RsvEqOrderUnitParams l_rsvEqOrderUnitParams =
                TestDBUtility.getRsvEqOrderUnitRow();
            l_rsvEqOrderUnitParams.setParentOrderId(l_eqtypeOrderUnitParams.getOrderId());
            l_rsvEqOrderUnitParams.setOrderOpenStatus(OrderOpenStatusEnum.OPEN);
            l_rsvEqOrderUnitParams.setForcedExpireType("0");
            TestDBUtility.insertWithDel(l_rsvEqOrderUnitParams);

            QueryProcessor processor = Processors.getDefaultProcessor();
            l_rsvEqOrderUnitParams.setOrderId(1002);
            l_rsvEqOrderUnitParams.setAccountId(101001010011L);
            l_rsvEqOrderUnitParams.setOrderUnitId(1002);
            processor.doInsertQuery(l_rsvEqOrderUnitParams);
            
            l_rsvEqOrderUnitParams.setOrderId(1003);
            l_rsvEqOrderUnitParams.setAccountId(101001010013L);
            l_rsvEqOrderUnitParams.setOrderUnitId(1003);
            processor.doInsertQuery(l_rsvEqOrderUnitParams);
            
            l_serviceImpl.expireSettleOrder(l_eqtypeOrderUnitParams);
            List l_resultList = processor.doFindAllQuery(RsvEqOrderUnitRow.TYPE);
            assertEquals(3, l_resultList.size());
            RsvEqOrderUnitRow l_resultRow1 = (RsvEqOrderUnitRow)l_resultList.get(0);
            RsvEqOrderUnitRow l_resultRow2 = (RsvEqOrderUnitRow)l_resultList.get(1);
            RsvEqOrderUnitRow l_resultRow3 = (RsvEqOrderUnitRow)l_resultList.get(2);
            assertEquals("1", l_resultRow1.getForcedExpireType());
            assertEquals("1", l_resultRow2.getForcedExpireType());
            assertEquals("1", l_resultRow3.getForcedExpireType());
            log.debug(STR_METHOD_NAME + "------------------->ok");
            
        }
        catch(Exception l_exc)
        {
            log.exiting(STR_METHOD_NAME);
            fail();
        }
        log.exiting(STR_METHOD_NAME);
    }
    
    public void testExpireRsvSettleOrder()
    {

    }

    public void testInsertForceSettleErrorOrder_T01()
    {
        final String STR_METHOD_NAME = "testInsertForceSettleErrorOrder_T01()";
        log.entering(STR_METHOD_NAME);
        try
        {
            //EqtypeContractRow
            EqtypeContractParams l_eqtypeContractParams =
                TestDBUtility.getEqtypeContractRow();
            
            //ForcedSettleOrderInqParams
            ForcedSettleOrderInqParams l_forcedSettleOrderInqparams = TestDBUtility.getForcedSettleOrderInqRow();
            l_forcedSettleOrderInqparams.setBizDate("20050202");
            l_forcedSettleOrderInqparams.setContractId(l_eqtypeContractParams.getContractId());
            l_forcedSettleOrderInqparams.setAccountCode("10");
            l_forcedSettleOrderInqparams.setProductCode("0");
            l_forcedSettleOrderInqparams.setOrderId(null);
            TestDBUtility.deleteAll(ForcedSettleOrderInqParams.TYPE);
            TestDBUtility.insertWithDel(l_forcedSettleOrderInqparams);

            WEB3GentradeTradingTimeManagementForMock.mockGetOrderBizDate(new Date(2005-1900,01,02));

            WEB3TPContractForcedSettleResult l_forcedSettleResult =
                new WEB3TPContractForcedSettleResult();

            l_serviceImpl.insertForceSettleErrorOrder(
                l_eqtypeContractParams,
                null,null,null,
                100,null,null);
            log.debug(STR_METHOD_NAME + "--------------------->ok");
        }
        catch(Exception l_exc)
        {
            log.error(""+ l_exc);
            log.exiting(STR_METHOD_NAME);
            fail();
        }
        log.exiting(STR_METHOD_NAME);
    }
  
    public void testInsertForceSettleErrorOrder_T02()
    {
        final String STR_METHOD_NAME = "testInsertForceSettleErrorOrder_T02()";
        log.entering(STR_METHOD_NAME);
        try
        {
            //EqtypeContractRow
            TestDBUtility.deleteAll(EqtypeContractParams.TYPE);
            EqtypeContractParams l_eqtypeContractParams =
                TestDBUtility.getEqtypeContractRow();
            l_eqtypeContractParams.setTaxType(TaxTypeEnum.NORMAL);
            l_eqtypeContractParams.setContractType(ContractTypeEnum.LONG );
            TestDBUtility.deleteAll(l_eqtypeContractParams.TYPE);
            
            //ForcedSettleOrderInqParams 
            TestDBUtility.deleteAll(ForcedSettleOrderInqParams.TYPE);
//            ForcedSettleOrderInqParams l_forcedSettleOrderInqparams =
//                this.getForcedSettleOrderInqParams();
//            l_forcedSettleOrderInqparams.setBizDate("20050202");
//            l_forcedSettleOrderInqparams.setContractId(l_eqtypeContractParams.getContractId());
//            l_forcedSettleOrderInqparams.setOrderId(null);
//            
//            TestDBUtility.insertWithDel(l_forcedSettleOrderInqparams);

            //MainAccountParams
            TestDBUtility.deleteAll(MainAccountParams.TYPE);
            MainAccountParams l_mainAccountParams = TestDBUtility.getMainAccountRow();
            l_mainAccountParams.setAccountId(l_eqtypeContractParams.getAccountId());
            TestDBUtility.insertWithDel(l_mainAccountParams);

            //BranchParams
            TestDBUtility.deleteAll(BranchParams.TYPE);
            BranchParams l_branchParams = TestDBUtility.getBranchRow();
            l_branchParams.setBranchId(l_mainAccountParams.getBranchId());
            TestDBUtility.insertWithDel(l_branchParams);

            //TradedProductParams
            TestDBUtility.deleteAll(TradedProductParams.TYPE);
            TradedProductParams l_tradedProductParams =
                TestDBUtility.getTradedProductRow();
            l_tradedProductParams.setProductId(l_eqtypeContractParams.getProductId());
            l_tradedProductParams.setMarketId(l_eqtypeContractParams.getMarketId());
            l_tradedProductParams.setProductType(ProductTypeEnum.EQUITY);
            l_tradedProductParams.setDailyDeliveryDate(new Date(2005-1900,02,02));
            TestDBUtility.insertWithDel(l_tradedProductParams);

            //ProductParams
            TestDBUtility.deleteAll(ProductParams.TYPE);
            ProductParams l_productParams = TestDBUtility.getProductRow();
            l_productParams.setProductId(l_tradedProductParams.getProductId());
            l_productParams.setProductType(ProductTypeEnum.EQUITY);
            TestDBUtility.insertWithDel(l_productParams);

            //EqtypeTradedProductParams
            TestDBUtility.deleteAll(EqtypeTradedProductParams.TYPE);
            EqtypeTradedProductParams l_eqtypeTradedProductParams =
                TestDBUtility.getEqtypeTradedProductRow();
            l_eqtypeTradedProductParams.setTradedProductId(l_tradedProductParams.getTradedProductId());
            l_eqtypeTradedProductParams.setProductId(l_tradedProductParams.getProductId());
            TestDBUtility.insertWithDel(l_eqtypeTradedProductParams);

            //EqtypeProductParams
            TestDBUtility.deleteAll(EqtypeProductParams.TYPE);
            EqtypeProductParams l_eqtypeProductParams =
                TestDBUtility.getEqtypeProductRow();
            l_eqtypeProductParams.setProductId(l_tradedProductParams.getProductId());
            TestDBUtility.insertWithDel(l_eqtypeProductParams);
            
            WEB3GentradeTradingTimeManagementForMock.mockGetOrderBizDate(new Date(2005-1900,01,02));

            WEB3TPContractForcedSettleResult l_forcedSettleResult =
                new WEB3TPContractForcedSettleResult();

            l_serviceImpl.insertForceSettleErrorOrder(
                l_eqtypeContractParams,//建株Row
                "1",//強制決済理由区分
                null,//強制決済余力計算結果
                new Long(123L),//注文ID
                100,//注文数量
                null,//指値
                "1001");//注文エラー理由コード
            
            List l_resultList =
                ForcedSettleOrderInqDao.findRowsByBranchId(l_branchParams.getBranchId());
            assertEquals(1, l_resultList.size());
            ForcedSettleOrderInqRow l_resultRow =
                (ForcedSettleOrderInqRow)l_resultList.get(0);
            assertEquals(123L,l_resultRow.getOrderId());
            assertEquals(l_eqtypeContractParams.getAccountId(), l_resultRow.getAccountId());
            assertEquals(l_eqtypeContractParams.getSubAccountId(), l_resultRow.getSubAccountId());
            assertEquals(l_mainAccountParams.getBranchId(), l_resultRow.getBranchId());
            assertEquals(OrderTypeEnum.CLOSE_MARGIN_LONG, l_resultRow.getOrderType());
            assertEquals(OrderCategEnum.CLOSE_MARGIN, l_resultRow.getOrderCateg());
            assertEquals(l_eqtypeContractParams.getMarketId(), l_resultRow.getMarketId());
            assertEquals(100, l_resultRow.getQuantity(),0);
            assertTrue(l_resultRow.getLimitPriceIsNull());
            assertEquals(new Date(2005-1900,02,02), l_resultRow.getDeliveryDate());
            assertEquals("20050202", l_resultRow.getBizDate());
            assertEquals(l_eqtypeContractParams.getProductId(), l_resultRow.getProductId());
            assertEquals("1001", l_resultRow.getErrorReasonCode());
            assertEquals("1", l_resultRow.getForcedSettleReasonType());
            assertEquals("9", l_resultRow.getApproveStatusType());
            assertTrue(l_resultRow.getMarginMaintenanceRateIsNull());
            assertNull(l_resultRow.getAdditionalMarginDate());
            assertTrue(l_resultRow.getAdditionalMarginAccruedDaysIsNull());
            assertEquals(l_eqtypeContractParams.getContractId(), l_resultRow.getContractId());
            assertEquals(l_eqtypeContractParams.getOriginalQuantity(), l_resultRow.getOrgContractQuantity(),0);
            assertEquals(l_eqtypeContractParams.getQuantity(), l_resultRow.getContractQuantity(),0);
            assertEquals(l_eqtypeContractParams.getOriginalContractPrice(), l_resultRow.getOriginalContractPrice(),0);
            assertEquals(l_eqtypeContractParams.getContractPrice(), l_resultRow.getContractPrice(), 0);
            assertEquals(l_eqtypeContractParams.getContractType().intValue(), l_resultRow.getContractType());
            assertEquals(l_eqtypeContractParams.getTaxType().intValue(), l_resultRow.getTaxType());
            assertEquals(l_eqtypeContractParams.getRepaymentType(), l_resultRow.getRepaymentType());
            assertEquals(l_eqtypeContractParams.getRepaymentNum(), l_resultRow.getRepaymentNum());
            log.debug(STR_METHOD_NAME + "--------------------->ok");
        }
        catch(Exception l_exc)
        {
            log.exiting(STR_METHOD_NAME);
            fail();
        }
        log.exiting(STR_METHOD_NAME);
    }

    
    public void testInsertForceSettleErrorOrder_T03()
    {
        final String STR_METHOD_NAME = "testInsertForceSettleErrorOrder_T03()";
        log.entering(STR_METHOD_NAME);
        try
        {
            //EqtypeContractRow
            TestDBUtility.deleteAll(EqtypeContractParams.TYPE);
            EqtypeContractParams l_eqtypeContractParams =
                TestDBUtility.getEqtypeContractRow();
            l_eqtypeContractParams.setTaxType(TaxTypeEnum.NORMAL);
            l_eqtypeContractParams.setContractType(ContractTypeEnum.SHORT);
            TestDBUtility.deleteAll(l_eqtypeContractParams.TYPE);
            
            //ForcedSettleOrderInqParams 
            TestDBUtility.deleteAll(ForcedSettleOrderInqParams.TYPE);
//            ForcedSettleOrderInqParams l_forcedSettleOrderInqparams =
//                this.getForcedSettleOrderInqParams();
//            l_forcedSettleOrderInqparams.setBizDate("20050202");
//            l_forcedSettleOrderInqparams.setContractId(l_eqtypeContractParams.getContractId());
//            l_forcedSettleOrderInqparams.setOrderId(null);
//            
//            TestDBUtility.insertWithDel(l_forcedSettleOrderInqparams);

            //MainAccountParams
            TestDBUtility.deleteAll(MainAccountParams.TYPE);
            MainAccountParams l_mainAccountParams = TestDBUtility.getMainAccountRow();
            l_mainAccountParams.setAccountId(l_eqtypeContractParams.getAccountId());
            TestDBUtility.insertWithDel(l_mainAccountParams);

            //BranchParams
            TestDBUtility.deleteAll(BranchParams.TYPE);
            BranchParams l_branchParams = TestDBUtility.getBranchRow();
            l_branchParams.setBranchId(l_mainAccountParams.getBranchId());
            TestDBUtility.insertWithDel(l_branchParams);

            //TradedProductParams
            TestDBUtility.deleteAll(TradedProductParams.TYPE);
            TradedProductParams l_tradedProductParams =
                TestDBUtility.getTradedProductRow();
            l_tradedProductParams.setProductId(l_eqtypeContractParams.getProductId());
            l_tradedProductParams.setMarketId(l_eqtypeContractParams.getMarketId());
            l_tradedProductParams.setProductType(ProductTypeEnum.EQUITY);
            l_tradedProductParams.setDailyDeliveryDate(new Date(2005-1900,02,02));
            TestDBUtility.insertWithDel(l_tradedProductParams);

            //ProductParams
            TestDBUtility.deleteAll(ProductParams.TYPE);
            ProductParams l_productParams = TestDBUtility.getProductRow();
            l_productParams.setProductId(l_tradedProductParams.getProductId());
            l_productParams.setProductType(ProductTypeEnum.EQUITY);
            TestDBUtility.insertWithDel(l_productParams);

            //EqtypeTradedProductParams
            TestDBUtility.deleteAll(EqtypeTradedProductParams.TYPE);
            EqtypeTradedProductParams l_eqtypeTradedProductParams =
                TestDBUtility.getEqtypeTradedProductRow();
            l_eqtypeTradedProductParams.setTradedProductId(l_tradedProductParams.getTradedProductId());
            l_eqtypeTradedProductParams.setProductId(l_tradedProductParams.getProductId());
            TestDBUtility.insertWithDel(l_eqtypeTradedProductParams);

            //EqtypeProductParams
            TestDBUtility.deleteAll(EqtypeProductParams.TYPE);
            EqtypeProductParams l_eqtypeProductParams =
                TestDBUtility.getEqtypeProductRow();
            l_eqtypeProductParams.setProductId(l_tradedProductParams.getProductId());
            TestDBUtility.insertWithDel(l_eqtypeProductParams);
            
            WEB3GentradeTradingTimeManagementForMock.mockGetOrderBizDate(new Date(2005-1900,01,02));

            WEB3TPContractForcedSettleResult l_forcedSettleResult =
                new WEB3TPContractForcedSettleResult();
            l_forcedSettleResult.marginMaintenanceRate = null;
            l_forcedSettleResult.additionalMarginDate = new Date(2005-1900,01,07);
            l_forcedSettleResult.additionalMarginAccruedDays = Integer.valueOf("100");
            
            l_serviceImpl.insertForceSettleErrorOrder(
                l_eqtypeContractParams,//建株Row
                "1",//強制決済理由区分
                l_forcedSettleResult,//強制決済余力計算結果
                null,//注文ID
                100,//注文数量
                new Double(456),//指値
                "1001");//注文エラー理由コード
            
            List l_resultList =
                ForcedSettleOrderInqDao.findRowsByBranchId(l_branchParams.getBranchId());
            assertEquals(1, l_resultList.size());
            ForcedSettleOrderInqRow l_resultRow =
                (ForcedSettleOrderInqRow)l_resultList.get(0);
            assertTrue(l_resultRow.getOrderIdIsNull());
            assertEquals(l_eqtypeContractParams.getAccountId(), l_resultRow.getAccountId());
            assertEquals(l_eqtypeContractParams.getSubAccountId(), l_resultRow.getSubAccountId());
            assertEquals(l_mainAccountParams.getBranchId(), l_resultRow.getBranchId());
            assertEquals(OrderTypeEnum.CLOSE_MARGIN_SHORT, l_resultRow.getOrderType());
            assertEquals(OrderCategEnum.CLOSE_MARGIN, l_resultRow.getOrderCateg());
            assertEquals(l_eqtypeContractParams.getMarketId(), l_resultRow.getMarketId());
            assertEquals(100, l_resultRow.getQuantity(),0);
            assertEquals(456d,l_resultRow.getLimitPrice(),0);
            assertEquals(new Date(2005-1900,02,02), l_resultRow.getDeliveryDate());
            assertEquals("20050202", l_resultRow.getBizDate());
            assertEquals(l_eqtypeContractParams.getProductId(), l_resultRow.getProductId());
            assertEquals("1001", l_resultRow.getErrorReasonCode());
            assertEquals("1", l_resultRow.getForcedSettleReasonType());
            assertEquals("9", l_resultRow.getApproveStatusType());
            assertTrue(l_resultRow.getMarginMaintenanceRateIsNull());
            assertEquals(new Date(2005-1900,01,07), l_resultRow.getAdditionalMarginDate());
            assertEquals(100,l_resultRow.getAdditionalMarginAccruedDays());
            assertEquals(l_eqtypeContractParams.getContractId(), l_resultRow.getContractId());
            assertEquals(l_eqtypeContractParams.getOriginalQuantity(), l_resultRow.getOrgContractQuantity(),0);
            assertEquals(l_eqtypeContractParams.getQuantity(), l_resultRow.getContractQuantity(),0);
            assertEquals(l_eqtypeContractParams.getOriginalContractPrice(), l_resultRow.getOriginalContractPrice(),0);
            assertEquals(l_eqtypeContractParams.getContractPrice(), l_resultRow.getContractPrice(), 0);
            assertEquals(l_eqtypeContractParams.getContractType().intValue(), l_resultRow.getContractType());
            assertEquals(l_eqtypeContractParams.getTaxType().intValue(), l_resultRow.getTaxType());
            assertEquals(l_eqtypeContractParams.getRepaymentType(), l_resultRow.getRepaymentType());
            assertEquals(l_eqtypeContractParams.getRepaymentNum(), l_resultRow.getRepaymentNum());
            log.debug(STR_METHOD_NAME + "--------------------->ok");
        }
        catch(Exception l_exc)
        {
            log.exiting(STR_METHOD_NAME);
            fail();
        }
        log.exiting(STR_METHOD_NAME);
    }
    

    public void testInsertForceSettleErrorOrder_T04()
    {
        final String STR_METHOD_NAME = "testInsertForceSettleErrorOrder_T04()";
        log.entering(STR_METHOD_NAME);
        try
        {
            //EqtypeContractRow
            TestDBUtility.deleteAll(EqtypeContractParams.TYPE);
            EqtypeContractParams l_eqtypeContractParams =
                TestDBUtility.getEqtypeContractRow();
            l_eqtypeContractParams.setTaxType(TaxTypeEnum.NORMAL);
            l_eqtypeContractParams.setContractType(ContractTypeEnum.SHORT);
            TestDBUtility.deleteAll(l_eqtypeContractParams.TYPE);
            
            //ForcedSettleOrderInqParams 
            TestDBUtility.deleteAll(ForcedSettleOrderInqParams.TYPE);
//            ForcedSettleOrderInqParams l_forcedSettleOrderInqparams =
//                this.getForcedSettleOrderInqParams();
//            l_forcedSettleOrderInqparams.setBizDate("20050202");
//            l_forcedSettleOrderInqparams.setContractId(l_eqtypeContractParams.getContractId());
//            l_forcedSettleOrderInqparams.setOrderId(null);
//            
//            TestDBUtility.insertWithDel(l_forcedSettleOrderInqparams);

            //MainAccountParams
            TestDBUtility.deleteAll(MainAccountParams.TYPE);
            MainAccountParams l_mainAccountParams = TestDBUtility.getMainAccountRow();
            l_mainAccountParams.setAccountId(l_eqtypeContractParams.getAccountId());
            TestDBUtility.insertWithDel(l_mainAccountParams);

            //BranchParams
            TestDBUtility.deleteAll(BranchParams.TYPE);
            BranchParams l_branchParams = TestDBUtility.getBranchRow();
            l_branchParams.setBranchId(l_mainAccountParams.getBranchId());
            TestDBUtility.insertWithDel(l_branchParams);

            //TradedProductParams
            TestDBUtility.deleteAll(TradedProductParams.TYPE);
            TradedProductParams l_tradedProductParams =
                TestDBUtility.getTradedProductRow();
            l_tradedProductParams.setProductId(l_eqtypeContractParams.getProductId());
            l_tradedProductParams.setMarketId(l_eqtypeContractParams.getMarketId());
            l_tradedProductParams.setProductType(ProductTypeEnum.EQUITY);
            l_tradedProductParams.setDailyDeliveryDate(new Date(2005-1900,02,02));
            TestDBUtility.insertWithDel(l_tradedProductParams);

            //ProductParams
            TestDBUtility.deleteAll(ProductParams.TYPE);
            ProductParams l_productParams = TestDBUtility.getProductRow();
            l_productParams.setProductId(l_tradedProductParams.getProductId());
            l_productParams.setProductType(ProductTypeEnum.EQUITY);
            TestDBUtility.insertWithDel(l_productParams);

            //EqtypeTradedProductParams
            TestDBUtility.deleteAll(EqtypeTradedProductParams.TYPE);
            EqtypeTradedProductParams l_eqtypeTradedProductParams =
                TestDBUtility.getEqtypeTradedProductRow();
            l_eqtypeTradedProductParams.setTradedProductId(l_tradedProductParams.getTradedProductId());
            l_eqtypeTradedProductParams.setProductId(l_tradedProductParams.getProductId());
            TestDBUtility.insertWithDel(l_eqtypeTradedProductParams);

            //EqtypeProductParams
            TestDBUtility.deleteAll(EqtypeProductParams.TYPE);
            EqtypeProductParams l_eqtypeProductParams =
                TestDBUtility.getEqtypeProductRow();
            l_eqtypeProductParams.setProductId(l_tradedProductParams.getProductId());
            TestDBUtility.insertWithDel(l_eqtypeProductParams);
            
            WEB3GentradeTradingTimeManagementForMock.mockGetOrderBizDate(new Date(2005-1900,01,02));

            WEB3TPContractForcedSettleResult l_forcedSettleResult =
                new WEB3TPContractForcedSettleResult();
            l_forcedSettleResult.marginMaintenanceRate = new Double(222);
            l_forcedSettleResult.additionalMarginDate = new Date(2005-1900,01,07);
            l_forcedSettleResult.additionalMarginAccruedDays = Integer.valueOf("100");
            
            l_serviceImpl.insertForceSettleErrorOrder(
                l_eqtypeContractParams,//建株Row
                "1",//強制決済理由区分
                l_forcedSettleResult,//強制決済余力計算結果
                null,//注文ID
                100,//注文数量
                new Double(456),//指値
                "1001");//注文エラー理由コード
            
            List l_resultList =
                ForcedSettleOrderInqDao.findRowsByBranchId(l_branchParams.getBranchId());
            assertEquals(1, l_resultList.size());
            ForcedSettleOrderInqRow l_resultRow =
                (ForcedSettleOrderInqRow)l_resultList.get(0);
            assertTrue(l_resultRow.getOrderIdIsNull());
            assertEquals(l_eqtypeContractParams.getAccountId(), l_resultRow.getAccountId());
            assertEquals(l_eqtypeContractParams.getSubAccountId(), l_resultRow.getSubAccountId());
            assertEquals(l_mainAccountParams.getBranchId(), l_resultRow.getBranchId());
            assertEquals(OrderTypeEnum.CLOSE_MARGIN_SHORT, l_resultRow.getOrderType());
            assertEquals(OrderCategEnum.CLOSE_MARGIN, l_resultRow.getOrderCateg());
            assertEquals(l_eqtypeContractParams.getMarketId(), l_resultRow.getMarketId());
            assertEquals(100, l_resultRow.getQuantity(),0);
            assertEquals(456d,l_resultRow.getLimitPrice(),0);
            assertEquals(new Date(2005-1900,02,02), l_resultRow.getDeliveryDate());
            assertEquals("20050202", l_resultRow.getBizDate());
            assertEquals(l_eqtypeContractParams.getProductId(), l_resultRow.getProductId());
            assertEquals("1001", l_resultRow.getErrorReasonCode());
            assertEquals("1", l_resultRow.getForcedSettleReasonType());
            assertEquals("9", l_resultRow.getApproveStatusType());
            assertEquals(222d, l_resultRow.getMarginMaintenanceRate(),0);
            assertEquals(new Date(2005-1900,01,07), l_resultRow.getAdditionalMarginDate());
            assertEquals(100,l_resultRow.getAdditionalMarginAccruedDays());
            assertEquals(l_eqtypeContractParams.getContractId(), l_resultRow.getContractId());
            assertEquals(l_eqtypeContractParams.getOriginalQuantity(), l_resultRow.getOrgContractQuantity(),0);
            assertEquals(l_eqtypeContractParams.getQuantity(), l_resultRow.getContractQuantity(),0);
            assertEquals(l_eqtypeContractParams.getOriginalContractPrice(), l_resultRow.getOriginalContractPrice(),0);
            assertEquals(l_eqtypeContractParams.getContractPrice(), l_resultRow.getContractPrice(), 0);
            assertEquals(l_eqtypeContractParams.getContractType().intValue(), l_resultRow.getContractType());
            assertEquals(l_eqtypeContractParams.getTaxType().intValue(), l_resultRow.getTaxType());
            assertEquals(l_eqtypeContractParams.getRepaymentType(), l_resultRow.getRepaymentType());
            assertEquals(l_eqtypeContractParams.getRepaymentNum(), l_resultRow.getRepaymentNum());
            log.debug(STR_METHOD_NAME + "--------------------->ok");
        }
        catch(Exception l_exc)
        {
            log.exiting(STR_METHOD_NAME);
            fail();
        }
        log.exiting(STR_METHOD_NAME);
    }
    
    public ForcedSettleOrderInqParams getForcedSettleOrderInqParams()
    {
        ForcedSettleOrderInqParams l_params = new ForcedSettleOrderInqParams();
        //注文ＩＤ                order_id                18   NULL  
        l_params.setOrderId(789456123L);
        //口座ＩＤ                account_id              18   NOT NULL   
        l_params.setAccountId(333812512203L);
        //補助口座ＩＤ          sub_account_id                  18   NOT NULL           
        l_params.setSubAccountId(33381251220301L);
        //部店ＩＤ                branch_id               18   NOT NULL    
        l_params.setBranchId(33381);
        //注文種別                order_type              6    NOT NULL      
        l_params.setOrderType(OrderTypeEnum.ASSET_IN);
        //注文カテゴリ          order_categ                     6    NOT NULL   
        l_params.setOrderCateg(OrderCategEnum.ASSET);
        //市場ＩＤ                market_id               18   NULL                                                           
        //注文数量                quantity                18   NOT NULL     
        l_params.setQuantity(100);
        //指値              limit_price                 18   NULL                                                       
        //受渡日             delivery_date                        NOT NULL  
        l_params.setDeliveryDate(new Date(2007,05,25));
        //発注日             biz_date                    8    NOT NULL       
        l_params.setBizDate("20070528");
        //銘柄ＩＤ                product_id              18   NOT NULL  
        l_params.setProductId(3304148080000L);
        //受注日時                received_date_time               NULL                                                       
        //注文エラー理由コード      error_reason_code               4    NULL                                                           
        //強制決済理由区分            forced_settle_reason_type       1    NULL                                                       
        //承認状態区分      approve_status_type             1    NULL                                                       
        //保証金維持率          margin_maintenance_rate            18    NULL                                                       
        //追証発生日           additional_margin_date               NULL                                                       
        //追証経過日数      additional_margin_accrued_days      18   NULL                                                   
        //建株ＩＤ                contract_id             18   NOT NULL  
        l_params.setContractId(456132789L);
        //元建株数                org_contract_quantity           18   NOT NULL  
        l_params.setOrgContractQuantity(200);
        //建株数             contract_quantity           18   NOT NULL    
        l_params.setContractQuantity(250);
        //元建単価                original_contract_price         18   NOT NULL    
        l_params.setOriginalContractPrice(10);
        //建単価             contract_price              18   NOT NULL  
        l_params.setContractPrice(10);
        //建区分             contract_type               6    NOT NULL   
        l_params.setContractType(1001);
        //建日              open_date                    NOT NULL  
        l_params.setOpenDate(new Date(2007,05,26));
        //期日              close_date                   NOT NULL    
        l_params.setCloseDate(new Date(2007,05,26));
        //税区分             tax_type                6    NOT NULL 
        l_params.setTaxType(1002);
        //弁済区分                repayment_type              1    NULL                                                           
        //弁済期限値           repayment_num               7    NULL                                                           
        //作成日付                created_timestamp                NOT NULL 
        l_params.setCreatedTimestamp(new Date(2007,05,24));
        //更新日付                last_updated_timestamp               NOT NULL
        l_params.setLastUpdatedTimestamp(new Date(2007,05,24));
        return l_params;
    }
    public class WEB3AdminEquityForcedSettleTempOrderCreateUnitServiceImplForTest extends WEB3AdminEquityForcedSettleTempOrderCreateUnitServiceImpl
    {
        protected void execCloseNotice(EqtypeOrderUnitRow l_eqtypeOrderUnitRow) throws WEB3BaseException
        {
            final String STR_METHOD_NAME = "execCloseNotice(EqtypeOrderUnitRow)";
            log.entering(STR_METHOD_NAME);
            log.exiting(STR_METHOD_NAME);
        }
        
        protected void submitRepayTempOrder(
            EqtypeContractRow l_eqtypeContractRow,
            String l_strForcedSettleResonDiv,
            WEB3TPContractForcedSettleResult l_contractForceSettleResult) throws WEB3BaseException
        {
            final String STR_METHOD_NAME = "submitRepayTempOrder(EqtypeContractRow, String, " +
            "WEB3TPContractForcedSettleResult)";
            log.entering(STR_METHOD_NAME);
            log.exiting(STR_METHOD_NAME);
        }
    }
    

}
@
