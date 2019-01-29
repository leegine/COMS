head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.04.07.01.11.14;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	5444d9d078c63f4;
filename	WEB3MarginCloseMarginUpdateInterceptorTest.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
 Copyright        : (äî)ëÂòaëçå§ èÿåîÉ\ÉäÉÖÅ[ÉVÉáÉìÉVÉXÉeÉÄëÊìÒïî
 File Name        : (WEB3MarginExecuteReferenceServiceImplTest.java)
 Author Name      : Daiwa Institute of Research
 Revesion History : 2007/06/10  âΩï∂ïq(íÜêu)Å@@êVãKçÏê¨
 */
package webbroker3.equity;

import java.util.Date;

import com.fitechlabs.xtrade.kernel.boot.Services;
import com.fitechlabs.xtrade.plugin.tc.eqtype.EqTypeExecutionConditionType;
import com.fitechlabs.xtrade.plugin.tc.eqtype.data.EqtypeOrderUnitParams;
import com.fitechlabs.xtrade.plugin.tc.eqtype.data.EqtypeOrderUnitRow;
import com.fitechlabs.xtrade.plugin.tc.eqtype.data.EqtypeTradedProductParams;
import com.fitechlabs.xtrade.plugin.tc.eqtype.data.EqtypeTradedProductRow;
import com.fitechlabs.xtrade.plugin.tc.eqtype.ordersubmitter.io.EqTypeSettleContractOrderEntry;
import com.fitechlabs.xtrade.plugin.tc.gentrade.FinApp;
import com.fitechlabs.xtrade.plugin.tc.gentrade.ProductTypeEnum;
import com.fitechlabs.xtrade.plugin.tc.gentrade.TaxTypeEnum;
import com.fitechlabs.xtrade.plugin.tc.gentrade.Trader;
import com.fitechlabs.xtrade.plugin.tc.gentrade.data.BranchParams;
import com.fitechlabs.xtrade.plugin.tc.gentrade.data.BranchRow;
import com.fitechlabs.xtrade.plugin.tc.gentrade.data.InstitutionParams;
import com.fitechlabs.xtrade.plugin.tc.gentrade.data.InstitutionRow;
import com.fitechlabs.xtrade.plugin.tc.gentrade.data.MainAccountParams;
import com.fitechlabs.xtrade.plugin.tc.gentrade.data.MainAccountRow;
import com.fitechlabs.xtrade.plugin.tc.gentrade.data.MarketParams;
import com.fitechlabs.xtrade.plugin.tc.gentrade.data.MarketRow;
import com.fitechlabs.xtrade.plugin.tc.gentrade.data.TradedProductParams;
import com.fitechlabs.xtrade.plugin.tc.gentrade.data.TradedProductRow;
import com.fitechlabs.xtrade.plugin.tc.gentrade.data.TraderParams;
import com.fitechlabs.xtrade.plugin.tc.gentrade.data.TraderRow;

import test.util.TestDBUtility;

import webbroker3.gentrade.WEB3GentradeCommission;
import webbroker3.mock.TestBaseForMock;
import webbroker3.system.tune.affinity.WEB3OrderReqNumberHead2ManageService;
import webbroker3.system.tune.affinity.WEB3OrderReqNumberHead2ManageServiceImpl;
import webbroker3.util.WEB3DateUtility;
import webbroker3.util.WEB3LogUtility;

/**
 * @@author âΩï∂ïq
 * @@version 1.0
 */
public class WEB3MarginCloseMarginUpdateInterceptorTest extends TestBaseForMock
{
    private static WEB3LogUtility log =
        WEB3LogUtility.getInstance(WEB3MarginCloseMarginUpdateInterceptorTest.class);
    
    public WEB3MarginCloseMarginUpdateInterceptorTest(String arg0)
    {
        super(arg0);
    }

    protected void setUp() throws Exception
    {
        
        super.setUp();
        TestBaseForMock.MOCK_MANAGER.setIsMockUsed(true);
    }

    protected void tearDown() throws Exception
    {
        
        super.tearDown();
    }
    
    public void testMutate_Case0001()
    {
        final String STR_METHOD_NAME = "testMutate_Case0001()";
        log.entering(STR_METHOD_NAME);
        
        try
        {
            
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                "webbroker3.gentrade.WEB3HostReqOrderNumberManageServiceImpl",
                "getNewNumber", 
                new Class[]
                { String.class, String.class, ProductTypeEnum.class }, 
                "12345");
            
            TestDBUtility.deleteAll(TraderRow.TYPE);
            TraderParams l_traderParams = TestDBUtility.getTraderRow();
            TestDBUtility.insertWithDel(l_traderParams);
            
            TestDBUtility.deleteAll(MainAccountRow.TYPE);
            MainAccountParams l_mainAccountParams = TestDBUtility.getMainAccountRow();
            l_mainAccountParams.setAccountId(333812512203L);
            TestDBUtility.insertWithDel(l_mainAccountParams);
            
            TestDBUtility.deleteAll(InstitutionRow.TYPE);
            InstitutionParams l_institutionParams = TestDBUtility.getInstitutionRow();
            l_institutionParams.setInstitutionId(33);
            TestDBUtility.insertWithDel(l_institutionParams);
            
            TestDBUtility.deleteAll(BranchRow.TYPE);
            BranchParams l_branchParams = TestDBUtility.getBranchRow();
            l_branchParams.setBranchId(33381);
            TestDBUtility.insertWithDel(l_branchParams);
            
            TestDBUtility.deleteAll(MarketRow.TYPE);
            MarketParams l_marketParams = TestDBUtility.getMarketRow();
            l_marketParams.setMarketId(3303);
            TestDBUtility.insertWithDel(l_marketParams);

            TestDBUtility.deleteAll(TradedProductRow.TYPE);
            TradedProductParams l_tradedProductParams = TestDBUtility.getTradedProductRow();
            l_tradedProductParams.setMarketId(3303);
            l_tradedProductParams.setProductId(3304148080001L);
            l_tradedProductParams.setProductType(ProductTypeEnum.EQUITY);
            TestDBUtility.insertWithDel(l_tradedProductParams);
            
            TestDBUtility.deleteAll(EqtypeTradedProductRow.TYPE);
            EqtypeTradedProductParams l_eqtypeTradedProductParams = TestDBUtility.getEqtypeTradedProductRow();
            l_eqtypeTradedProductParams.setTradedProductId(330304148080000L);
            TestDBUtility.insertWithDel(l_eqtypeTradedProductParams);
            
            FinApp l_finApp = (FinApp)Services.getService(FinApp.class);
            Trader l_trader = l_finApp.getFinObjectManager().getTraderByLoginId(3338111123L);
            
            EqTypeSettleContractOrderEntry l_closeMarginContractEntry = new EqTypeSettleContractOrderEntry(12, 12);
            EqTypeSettleContractOrderEntry[] l_closeMarginContractEntrys =
                new EqTypeSettleContractOrderEntry[]{l_closeMarginContractEntry};

            double l_dblLimitPrice = 12;
            EqTypeExecutionConditionType l_executionCondition = EqTypeExecutionConditionType.AT_MARKET_CLOSE;
            Date l_datExpirationDate = WEB3DateUtility.getDate("20070608", "yyyyMMdd");
            TaxTypeEnum l_taxType = TaxTypeEnum.NORMAL;
            WEB3MarginSettleContractOrderSpec creditCloseMarginOrderSpec =
                new WEB3MarginSettleContractOrderSpec(
                    l_trader,
                    l_closeMarginContractEntrys,
                    l_dblLimitPrice,
                    l_executionCondition,
                    l_datExpirationDate,
                    l_taxType);
            WEB3GentradeCommission l_commission = new WEB3GentradeCommission();
            WEB3EquityRealizedProfitAndLossPrice l_estimateSettlementIncomeCalculationAmount =
                new WEB3EquityRealizedProfitAndLossPrice();
            WEB3MarginCloseMarginUpdateInterceptor l_interceptor =
                new WEB3MarginCloseMarginUpdateInterceptor(
                    creditCloseMarginOrderSpec,
                    l_commission,
                    l_estimateSettlementIncomeCalculationAmount,
                    "1",
                    10,
                    "1",
                    "1",
                    true);
            TestDBUtility.deleteAll(EqtypeOrderUnitRow.TYPE);
            EqtypeOrderUnitParams l_orderUnitParams = TestDBUtility.getEqtypeOrderUnitRow();
            l_orderUnitParams.setProductId(3304148080001L);
            l_orderUnitParams.setMarketId(3303);
            TestDBUtility.insertWithDel(l_orderUnitParams);
            l_interceptor.setSubmitOrderRouteDiv("1");
            EqtypeOrderUnitParams l_params = l_interceptor.mutate(null, null, l_orderUnitParams);
            assertEquals(l_params.getForcedSettleReasonType(), "9");
            assertEquals(l_params.getApproveStatusType(), "1");
        }
        catch (Exception l_ex)
        {
            log.debug(l_ex.getMessage(), l_ex);
            fail();
        }
        
        log.exiting(STR_METHOD_NAME);
    }
    
    public void testMutate_Case0002()
    {
        final String STR_METHOD_NAME = "testMutate_Case0002()";
        log.entering(STR_METHOD_NAME);
        
        try
        {
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                    "webbroker3.gentrade.WEB3HostReqOrderNumberManageServiceImpl",
                    "getNewNumber", 
                    new Class[]
                    { String.class, String.class, ProductTypeEnum.class }, 
                    "12345");
            TestDBUtility.deleteAll(TraderRow.TYPE);
            TraderParams l_traderParams = TestDBUtility.getTraderRow();
            TestDBUtility.insertWithDel(l_traderParams);
            
            TestDBUtility.deleteAll(MainAccountRow.TYPE);
            MainAccountParams l_mainAccountParams = TestDBUtility.getMainAccountRow();
            l_mainAccountParams.setAccountId(333812512203L);
            TestDBUtility.insertWithDel(l_mainAccountParams);
            
            TestDBUtility.deleteAll(InstitutionRow.TYPE);
            InstitutionParams l_institutionParams = TestDBUtility.getInstitutionRow();
            l_institutionParams.setInstitutionId(33);
            TestDBUtility.insertWithDel(l_institutionParams);
            
            TestDBUtility.deleteAll(BranchRow.TYPE);
            BranchParams l_branchParams = TestDBUtility.getBranchRow();
            l_branchParams.setBranchId(33381);
            TestDBUtility.insertWithDel(l_branchParams);
            
            TestDBUtility.deleteAll(MarketRow.TYPE);
            MarketParams l_marketParams = TestDBUtility.getMarketRow();
            l_marketParams.setMarketId(3303);
            TestDBUtility.insertWithDel(l_marketParams);

            TestDBUtility.deleteAll(TradedProductRow.TYPE);
            TradedProductParams l_tradedProductParams = TestDBUtility.getTradedProductRow();
            l_tradedProductParams.setMarketId(3303);
            l_tradedProductParams.setProductId(3304148080001L);
            l_tradedProductParams.setProductType(ProductTypeEnum.EQUITY);
            TestDBUtility.insertWithDel(l_tradedProductParams);
            
            TestDBUtility.deleteAll(EqtypeTradedProductRow.TYPE);
            EqtypeTradedProductParams l_eqtypeTradedProductParams = TestDBUtility.getEqtypeTradedProductRow();
            l_eqtypeTradedProductParams.setTradedProductId(330304148080000L);
            TestDBUtility.insertWithDel(l_eqtypeTradedProductParams);
            
            FinApp l_finApp = (FinApp)Services.getService(FinApp.class);
            Trader l_trader = l_finApp.getFinObjectManager().getTraderByLoginId(3338111123L);
            
            EqTypeSettleContractOrderEntry l_closeMarginContractEntry = new EqTypeSettleContractOrderEntry(12, 12);
            EqTypeSettleContractOrderEntry[] l_closeMarginContractEntrys =
                new EqTypeSettleContractOrderEntry[]{l_closeMarginContractEntry};

            double l_dblLimitPrice = 12;
            EqTypeExecutionConditionType l_executionCondition = EqTypeExecutionConditionType.AT_MARKET_CLOSE;
            Date l_datExpirationDate = WEB3DateUtility.getDate("20070608", "yyyyMMdd");
            TaxTypeEnum l_taxType = TaxTypeEnum.NORMAL;
            WEB3MarginSettleContractOrderSpec creditCloseMarginOrderSpec =
                new WEB3MarginSettleContractOrderSpec(
                    l_trader,
                    l_closeMarginContractEntrys,
                    l_dblLimitPrice,
                    l_executionCondition,
                    l_datExpirationDate,
                    l_taxType);
            WEB3GentradeCommission l_commission = new WEB3GentradeCommission();
            WEB3EquityRealizedProfitAndLossPrice l_estimateSettlementIncomeCalculationAmount =
                new WEB3EquityRealizedProfitAndLossPrice();
            WEB3MarginCloseMarginUpdateInterceptor l_interceptor =
                new WEB3MarginCloseMarginUpdateInterceptor(
                    creditCloseMarginOrderSpec,
                    l_commission,
                    l_estimateSettlementIncomeCalculationAmount,
                    "1",
                    10,
                    "1",
                    "1",
                    false);
            TestDBUtility.deleteAll(EqtypeOrderUnitRow.TYPE);
            EqtypeOrderUnitParams l_orderUnitParams = TestDBUtility.getEqtypeOrderUnitRow();
            l_orderUnitParams.setProductId(3304148080001L);
            l_orderUnitParams.setMarketId(3303);
            TestDBUtility.insertWithDel(l_orderUnitParams);
            l_interceptor.setSubmitOrderRouteDiv("1");
            EqtypeOrderUnitParams l_params = l_interceptor.mutate(null, null, l_orderUnitParams);
            assertEquals(l_params.getForcedSettleReasonType(), null);
            assertEquals(l_params.getApproveStatusType(), null);
        }
        catch (Exception l_ex)
        {
            log.debug(l_ex.getMessage(), l_ex);
            fail();
        }
        
        log.exiting(STR_METHOD_NAME);
    }

}
@
