head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.04.07.02.42.17;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	5444d9d078c63f4;
filename	WEB3MutualFundMarketRequestServiceTest.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@package webbroker3.mf.marketadaptor;

import java.util.List;

import com.fitechlabs.xtrade.kernel.boot.Services;
import com.fitechlabs.xtrade.kernel.data.DataNetworkException;
import com.fitechlabs.xtrade.kernel.data.DataQueryException;
import com.fitechlabs.xtrade.kernel.data.Processors;
import com.fitechlabs.xtrade.kernel.data.QueryProcessor;
import com.fitechlabs.xtrade.plugin.tc.gentrade.FinApp;
import com.fitechlabs.xtrade.plugin.tc.gentrade.OrderTypeEnum;
import com.fitechlabs.xtrade.plugin.tc.gentrade.ProductTypeEnum;
import com.fitechlabs.xtrade.plugin.tc.gentrade.QuantityTypeEnum;
import com.fitechlabs.xtrade.plugin.tc.gentrade.SubAccount;
import com.fitechlabs.xtrade.plugin.tc.gentrade.TradingModule;
import com.fitechlabs.xtrade.plugin.tc.gentrade.data.BranchParams;
import com.fitechlabs.xtrade.plugin.tc.gentrade.data.BranchRow;
import com.fitechlabs.xtrade.plugin.tc.gentrade.data.InstitutionParams;
import com.fitechlabs.xtrade.plugin.tc.gentrade.data.InstitutionRow;
import com.fitechlabs.xtrade.plugin.tc.gentrade.data.MainAccountParams;
import com.fitechlabs.xtrade.plugin.tc.gentrade.data.MainAccountRow;
import com.fitechlabs.xtrade.plugin.tc.gentrade.data.ProductParams;
import com.fitechlabs.xtrade.plugin.tc.gentrade.data.ProductRow;
import com.fitechlabs.xtrade.plugin.tc.gentrade.data.SubAccountParams;
import com.fitechlabs.xtrade.plugin.tc.gentrade.data.SubAccountRow;
import com.fitechlabs.xtrade.plugin.tc.gentrade.data.TraderParams;
import com.fitechlabs.xtrade.plugin.tc.gentrade.data.TraderRow;
import com.fitechlabs.xtrade.plugin.tc.xbmf.MutualFundOrderUnit;
import com.fitechlabs.xtrade.plugin.tc.xbmf.MutualFundTypeEnum;
import com.fitechlabs.xtrade.plugin.tc.xbmf.data.MutualFundOrderUnitParams;
import com.fitechlabs.xtrade.plugin.tc.xbmf.data.MutualFundOrderUnitRow;
import com.fitechlabs.xtrade.plugin.tc.xbmf.data.MutualFundProductParams;
import com.fitechlabs.xtrade.plugin.tc.xbmf.data.MutualFundProductRow;

import test.util.TestDBUtility;

import webbroker3.common.WEB3BaseException;
import webbroker3.common.define.WEB3SellDivDef;
import webbroker3.common.define.WEB3SettlementDivDef;
import webbroker3.gentrade.WEB3GentradeAccountManager;
import webbroker3.gentrade.data.BranchPreferencesParams;
import webbroker3.gentrade.data.BranchPreferencesRow;
import webbroker3.gentrade.data.CalendarRow;
import webbroker3.mf.WEB3MutualFundOrderManager;
import webbroker3.mf.data.HostFrgnMmfOrderDao;
import webbroker3.mf.data.HostFrgnMmfOrderParams;
import webbroker3.mf.data.HostFrgnMmfOrderRow;
import webbroker3.mf.data.HostXbmfOrderParams;
import webbroker3.mf.data.HostXbmfOrderRow;
import webbroker3.mock.TestBaseForMock;
import webbroker3.util.WEB3DateUtility;
import webbroker3.util.WEB3LogUtility;

public class WEB3MutualFundMarketRequestServiceTest extends TestBaseForMock
{
    /**
     * Logger
     */
   private static WEB3LogUtility log =
       WEB3LogUtility.getInstance(WEB3MutualFundMarketRequestServiceTest.class);

    public WEB3MutualFundMarketRequestServiceTest(String arg0)
    {
        super(arg0);
    }

    protected void setUp() throws Exception
    {
        super.setUp();
        TestDBUtility.deleteAllAndCommit(CalendarRow.TYPE);
    }

    protected void tearDown() throws Exception
    {
        super.tearDown();
    }

    /*
     * Test method for 'webbroker3.mf.marketadaptor.WEB3MutualFundMarketRequestService.newOrderMarketRequestSendMessageAcquiredSellSwt(MutualFundOrderUnit, SubAccount)'
     */
    public void testNewOrderMarketRequestSendMessageAcquiredSellSwtC1()
    {
        final String STR_METHOD_NAME = "testNewOrderMarketRequestSendMessageAcquiredSellSwtC1()";
        log.entering(STR_METHOD_NAME);

        WEB3MutualFundMarketRequestService l_requestService = new WEB3MutualFundMarketRequestService();

        InstitutionParams l_insParams = TestDBUtility.getInstitutionRow();
        l_insParams.setInstitutionId(33);
        
        SubAccountParams l_subAccountParams =
            TestDBUtility.getSubAccountRow();
        l_subAccountParams.setAccountId(333812512246L);
        
        BranchParams l_branchParams =
            TestDBUtility.getBranchRow();
        l_branchParams.setBranchId(33381);
        
        MainAccountParams l_mainAccountParams =
            TestDBUtility.getMainAccountRow();
        l_mainAccountParams.setAccountId(333812512246L);
        
        ProductParams l_productParams =
            TestDBUtility.getProductRow();
        l_productParams.setProductId(3304148080001L);
        l_productParams.setProductType(ProductTypeEnum.MUTUAL_FUND);
        
        MutualFundOrderUnitParams l_mfOrderUnitParams =
            TestDBUtility.getMutualFundOrderUnitRow();
        l_mfOrderUnitParams.setSubAccountId(33381251220301L);
        l_mfOrderUnitParams.setProductId(3304148080001L);
        l_mfOrderUnitParams.setBranchId(33381);
        l_mfOrderUnitParams.setAccountId(333812512246L);
        l_mfOrderUnitParams.setOrderRequestNumber("123456789");
        l_mfOrderUnitParams.setReceivedDateTime(WEB3DateUtility.getDate("20071001","yyyyMMdd"));
        l_mfOrderUnitParams.setOrderType(OrderTypeEnum.MF_BUY);
        l_mfOrderUnitParams.setTraderId(3338111123L);
        l_mfOrderUnitParams.setQuantityType(QuantityTypeEnum.QUANTITY);
        l_mfOrderUnitParams.setQuantity(100);
        l_mfOrderUnitParams.setSettlementDiv(WEB3SettlementDivDef.JAPANESE_CURRENCY);
        l_mfOrderUnitParams.setFundType(MutualFundTypeEnum.FOREIGN_MMF);
        
        MutualFundProductParams l_mfProductParams =
            TestDBUtility.getMutualFundProductRow();
        l_mfProductParams.setProductId(3304148080001L);
        
        TraderParams l_traderParams =
            TestDBUtility.getTraderRow();
        l_traderParams.setTraderId(l_mfOrderUnitParams.getTraderId());
        
        BranchPreferencesParams l_branchPreferencesParams =
            TestDBUtility.getBranchPreferencesRow();
        
        try
        {
            TestDBUtility.insertWithDel(l_insParams);

            TestDBUtility.deleteAll(SubAccountRow.TYPE);
            TestDBUtility.insertWithDel(l_subAccountParams);
            
            TestDBUtility.deleteAll(BranchRow.TYPE);
            TestDBUtility.insertWithDel(l_branchParams);
            
            TestDBUtility.deleteAll(ProductRow.TYPE);
            TestDBUtility.insertWithDel(l_productParams);
            
            TestDBUtility.deleteAll(MainAccountRow.TYPE);
            TestDBUtility.insertWithDel(l_mainAccountParams);
            
            TestDBUtility.deleteAll(MutualFundOrderUnitRow.TYPE);
            TestDBUtility.insertWithDel(l_mfOrderUnitParams);
            
            TestDBUtility.deleteAll(MutualFundProductRow.TYPE);
            TestDBUtility.insertWithDel(l_mfProductParams);
            

            TestDBUtility.deleteAll(BranchPreferencesRow.TYPE);
            TestDBUtility.insertWithDel(l_branchPreferencesParams);
            
            TestDBUtility.deleteAll(TraderRow.TYPE);
            TestDBUtility.insertWithDel(l_traderParams);
            
            
            TestDBUtility.deleteAll(HostXbmfOrderRow.TYPE);
            TestDBUtility.deleteAll(HostFrgnMmfOrderRow.TYPE);
            
            //ŽæorderUnit
            FinApp l_finApp = (FinApp) Services.getService(FinApp.class);
            TradingModule l_tradingModule =
                l_finApp.getTradingModule(ProductTypeEnum.MUTUAL_FUND);
            WEB3MutualFundOrderManager l_mfOrderManager =
                (WEB3MutualFundOrderManager)l_tradingModule.getOrderManager();
            MutualFundOrderUnit l_mutualFundOrderUnit =
                (MutualFundOrderUnit)l_mfOrderManager.getOrderUnit(l_mfOrderUnitParams.getOrderUnitId());
            //ŽæsubAccount

            WEB3GentradeAccountManager l_gentradeManager =
                (WEB3GentradeAccountManager)l_finApp.getAccountManager();
            SubAccount l_subAccount =
                l_gentradeManager.getSubAccount(
                    l_subAccountParams.getAccountId(),
                    l_subAccountParams.getSubAccountId());

            
            l_requestService.newOrderMarketRequestSendMessageAcquiredSellSwt(
                l_mutualFundOrderUnit,
                l_subAccount);
            
            
            HostFrgnMmfOrderRow l_fmmfOrderRow =
                HostFrgnMmfOrderDao.findRowByPk("0D","381","123456789");
            assertEquals(l_fmmfOrderRow.getRequestCode(), "DI821");
            assertEquals(l_fmmfOrderRow.getBranchCode(), "381");
            assertEquals(l_fmmfOrderRow.getInstitutionCode(), "0D");
            assertEquals(l_fmmfOrderRow.getAccountCode(), "2512246");
            assertEquals(l_fmmfOrderRow.getTraderCode(), "11123");
            assertEquals(l_fmmfOrderRow.getProductCode(), "0001000");
            assertEquals(l_fmmfOrderRow.getSpecifyDiv(), "1");
            assertEquals(l_fmmfOrderRow.getSellOrderQuantity(), 0);
            assertEquals(l_fmmfOrderRow.getBuyOrderQuantity(), 100);
            assertEquals(l_fmmfOrderRow.getTicketNumber(), 9789);
            assertEquals(l_fmmfOrderRow.getBuyDiv(), " ");
            assertEquals(l_fmmfOrderRow.getSettlementDiv(), "0");
            assertEquals(l_fmmfOrderRow.getExecuteDiv(), "1");
            assertEquals(l_fmmfOrderRow.getOrderRequestNumber(), "123456789");
            assertEquals(WEB3DateUtility.formatDate(l_fmmfOrderRow.getCreateDate(),"yyyyMMdd"), "20040716");
            assertEquals(WEB3DateUtility.formatDate(l_fmmfOrderRow.getCreateTime(),"yyyyMMdd"),"20040716");
            assertEquals(WEB3DateUtility.formatDate(l_fmmfOrderRow.getOrderDate(),"yyyyMMdd"), "20040720");
            assertEquals(l_fmmfOrderRow.getStatus(), "0");
        }
        catch (WEB3BaseException l_ex)
        {
            log.error(l_ex.getMessage(), l_ex);
            fail(); 
        } 
        catch (Exception l_ex)
        {
            log.error(l_ex.getMessage(), l_ex);
            fail();
        }
        
        log.exiting(STR_METHOD_NAME);

    }
    
    public void testNewOrderMarketRequestSendMessageAcquiredSellSwtC2()
    {
        final String STR_METHOD_NAME = "testNewOrderMarketRequestSendMessageAcquiredSellSwtC2()";
        log.entering(STR_METHOD_NAME);
        
        WEB3MutualFundMarketRequestService l_requestService = new WEB3MutualFundMarketRequestService();

        InstitutionParams l_insParams = TestDBUtility.getInstitutionRow();
        l_insParams.setInstitutionId(33);

        SubAccountParams l_subAccountParams =
            TestDBUtility.getSubAccountRow();
        l_subAccountParams.setAccountId(333812512246L);
        
        BranchParams l_branchParams =
            TestDBUtility.getBranchRow();
        l_branchParams.setBranchId(33381);
        
        MainAccountParams l_mainAccountParams =
            TestDBUtility.getMainAccountRow();
        l_mainAccountParams.setAccountId(333812512246L);
        
        ProductParams l_productParams =
            TestDBUtility.getProductRow();
        l_productParams.setProductId(3304148080001L);
        l_productParams.setProductType(ProductTypeEnum.MUTUAL_FUND);
        
        MutualFundOrderUnitParams l_mfOrderUnitParams =
            TestDBUtility.getMutualFundOrderUnitRow();
        l_mfOrderUnitParams.setSubAccountId(33381251220301L);
        l_mfOrderUnitParams.setProductId(3304148080001L);
        l_mfOrderUnitParams.setBranchId(33381);
        l_mfOrderUnitParams.setAccountId(333812512246L);
        l_mfOrderUnitParams.setOrderRequestNumber("123456789");
        l_mfOrderUnitParams.setReceivedDateTime(WEB3DateUtility.getDate("20071001","yyyyMMdd"));
        l_mfOrderUnitParams.setOrderType(OrderTypeEnum.MF_BUY);
        l_mfOrderUnitParams.setTraderId(null);
        l_mfOrderUnitParams.setQuantityType(QuantityTypeEnum.AMOUNT);
        l_mfOrderUnitParams.setQuantity(1111.0);
        l_mfOrderUnitParams.setSettlementDiv(WEB3SettlementDivDef.FOREIGN_CURRENCY);
        l_mfOrderUnitParams.setFundType(MutualFundTypeEnum.FOREIGN_MMF);
        
        MutualFundProductParams l_mfProductParams =
            TestDBUtility.getMutualFundProductRow();
        l_mfProductParams.setProductId(3304148080001L);
        
        TraderParams l_traderParams =
            TestDBUtility.getTraderRow();
        l_traderParams.setTraderId(l_mfOrderUnitParams.getTraderId());
        
        BranchPreferencesParams l_branchPreferencesParams =
            TestDBUtility.getBranchPreferencesRow();
        
        try
        {
            
            TestDBUtility.insertWithDel(l_insParams);

            TestDBUtility.deleteAll(SubAccountRow.TYPE);
            TestDBUtility.insertWithDel(l_subAccountParams);
            
            TestDBUtility.deleteAll(BranchRow.TYPE);
            TestDBUtility.insertWithDel(l_branchParams);
            
            TestDBUtility.deleteAll(ProductRow.TYPE);
            TestDBUtility.insertWithDel(l_productParams);
            
            TestDBUtility.deleteAll(MainAccountRow.TYPE);
            TestDBUtility.insertWithDel(l_mainAccountParams);
            
            TestDBUtility.deleteAll(MutualFundOrderUnitRow.TYPE);
            TestDBUtility.insertWithDel(l_mfOrderUnitParams);
            
            TestDBUtility.deleteAll(MutualFundProductRow.TYPE);
            TestDBUtility.insertWithDel(l_mfProductParams);
            

            TestDBUtility.deleteAll(BranchPreferencesRow.TYPE);
            TestDBUtility.insertWithDel(l_branchPreferencesParams);
            
            TestDBUtility.deleteAll(TraderRow.TYPE);
            TestDBUtility.insertWithDel(l_traderParams);
            
            
            TestDBUtility.deleteAll(HostXbmfOrderRow.TYPE);
            TestDBUtility.deleteAll(HostFrgnMmfOrderRow.TYPE);
            
            //ŽæorderUnit
            FinApp l_finApp = (FinApp) Services.getService(FinApp.class);
            TradingModule l_tradingModule =
                l_finApp.getTradingModule(ProductTypeEnum.MUTUAL_FUND);
            WEB3MutualFundOrderManager l_mfOrderManager =
                (WEB3MutualFundOrderManager)l_tradingModule.getOrderManager();
            MutualFundOrderUnit l_mutualFundOrderUnit =
                (MutualFundOrderUnit)l_mfOrderManager.getOrderUnit(l_mfOrderUnitParams.getOrderUnitId());
            //ŽæsubAccount

            WEB3GentradeAccountManager l_gentradeManager =
                (WEB3GentradeAccountManager)l_finApp.getAccountManager();
            SubAccount l_subAccount =
                l_gentradeManager.getSubAccount(
                    l_subAccountParams.getAccountId(),
                    l_subAccountParams.getSubAccountId());

            
            l_requestService.newOrderMarketRequestSendMessageAcquiredSellSwt(
                l_mutualFundOrderUnit,
                l_subAccount);
            
            
            HostFrgnMmfOrderRow l_fmmfOrderRow =
                HostFrgnMmfOrderDao.findRowByPk("0D","381","123456789");
            assertEquals(l_fmmfOrderRow.getRequestCode(), "DI821");
            assertEquals(l_fmmfOrderRow.getBranchCode(), "381");
            assertEquals(l_fmmfOrderRow.getInstitutionCode(), "0D");
            assertEquals(l_fmmfOrderRow.getAccountCode(), "2512246");
            assertNull(l_fmmfOrderRow.getTraderCode());
            assertEquals(l_fmmfOrderRow.getProductCode(), "0001000");
            assertEquals(l_fmmfOrderRow.getSpecifyDiv(), "2");
            assertEquals(l_fmmfOrderRow.getSellOrderQuantity(), 0);
            log.debug("11111111111"+l_fmmfOrderRow.getBuyOrderQuantity());
            assertEquals(l_fmmfOrderRow.getBuyOrderQuantity(), 1111);
            assertEquals(l_fmmfOrderRow.getTicketNumber(), 9789);
            assertEquals(l_fmmfOrderRow.getBuyDiv(), " ");
            assertEquals(l_fmmfOrderRow.getSettlementDiv(), "1");
            assertEquals(l_fmmfOrderRow.getExecuteDiv(), "1");
            assertEquals(l_fmmfOrderRow.getOrderRequestNumber(), "123456789");
            assertEquals(WEB3DateUtility.formatDate(l_fmmfOrderRow.getCreateDate(),"yyyyMMdd"), "20040716");
            assertEquals(WEB3DateUtility.formatDate(l_fmmfOrderRow.getCreateTime(),"yyyyMMdd"),"20040716");
            assertEquals(WEB3DateUtility.formatDate(l_fmmfOrderRow.getOrderDate(),"yyyyMMdd"), "20040720");
            assertEquals(l_fmmfOrderRow.getStatus(), "0");
        }
        catch (WEB3BaseException l_ex)
        {
            log.error(l_ex.getMessage(), l_ex);
            fail(); 
        } 
        catch (Exception l_ex)
        {
            log.error(l_ex.getMessage(), l_ex);
            fail();
        }
        
        log.exiting(STR_METHOD_NAME);
    }
    
    public void testNewOrderMarketRequestSendMessageAcquiredSellSwtC3()
    {
        final String STR_METHOD_NAME = "testNewOrderMarketRequestSendMessageAcquiredSellSwtC3()";
        log.entering(STR_METHOD_NAME);
        
        WEB3MutualFundMarketRequestService l_requestService = new WEB3MutualFundMarketRequestService();

        InstitutionParams l_insParams = TestDBUtility.getInstitutionRow();
        l_insParams.setInstitutionId(33);
        
        SubAccountParams l_subAccountParams =
            TestDBUtility.getSubAccountRow();
        l_subAccountParams.setAccountId(333812512246L);
        
        BranchParams l_branchParams =
            TestDBUtility.getBranchRow();
        l_branchParams.setBranchId(33381);
        
        MainAccountParams l_mainAccountParams =
            TestDBUtility.getMainAccountRow();
        l_mainAccountParams.setAccountId(333812512246L);
        
        ProductParams l_productParams =
            TestDBUtility.getProductRow();
        l_productParams.setProductId(3304148080001L);
        l_productParams.setProductType(ProductTypeEnum.MUTUAL_FUND);
        
        MutualFundOrderUnitParams l_mfOrderUnitParams =
            TestDBUtility.getMutualFundOrderUnitRow();
        l_mfOrderUnitParams.setSubAccountId(33381251220301L);
        l_mfOrderUnitParams.setProductId(3304148080001L);
        l_mfOrderUnitParams.setBranchId(33381);
        l_mfOrderUnitParams.setAccountId(333812512246L);
        l_mfOrderUnitParams.setOrderRequestNumber("123456789");
        l_mfOrderUnitParams.setReceivedDateTime(WEB3DateUtility.getDate("20071001","yyyyMMdd"));
        l_mfOrderUnitParams.setOrderType(OrderTypeEnum.MF_SELL);
        l_mfOrderUnitParams.setTraderId(3338111123L);
        l_mfOrderUnitParams.setQuantityType(QuantityTypeEnum.QUANTITY);
        l_mfOrderUnitParams.setQuantity(2222.0);
        l_mfOrderUnitParams.setSettlementDiv(WEB3SettlementDivDef.JAPANESE_CURRENCY);
        l_mfOrderUnitParams.setFundSellDiv(WEB3SellDivDef.COUNT_DESIGNATE);
        l_mfOrderUnitParams.setFundType(MutualFundTypeEnum.FOREIGN_MMF);
        
        MutualFundProductParams l_mfProductParams =
            TestDBUtility.getMutualFundProductRow();
        l_mfProductParams.setProductId(3304148080001L);
        
        TraderParams l_traderParams =
            TestDBUtility.getTraderRow();
        l_traderParams.setTraderId(l_mfOrderUnitParams.getTraderId());
        
        BranchPreferencesParams l_branchPreferencesParams =
            TestDBUtility.getBranchPreferencesRow();
        
        try
        {
            TestDBUtility.insertWithDel(l_insParams);
            
            TestDBUtility.deleteAll(SubAccountRow.TYPE);
            TestDBUtility.insertWithDel(l_subAccountParams);
            
            TestDBUtility.deleteAll(BranchRow.TYPE);
            TestDBUtility.insertWithDel(l_branchParams);
            
            TestDBUtility.deleteAll(ProductRow.TYPE);
            TestDBUtility.insertWithDel(l_productParams);
            
            TestDBUtility.deleteAll(MainAccountRow.TYPE);
            TestDBUtility.insertWithDel(l_mainAccountParams);
            
            TestDBUtility.deleteAll(MutualFundOrderUnitRow.TYPE);
            TestDBUtility.insertWithDel(l_mfOrderUnitParams);
            
            TestDBUtility.deleteAll(MutualFundProductRow.TYPE);
            TestDBUtility.insertWithDel(l_mfProductParams);
            

            TestDBUtility.deleteAll(BranchPreferencesRow.TYPE);
            TestDBUtility.insertWithDel(l_branchPreferencesParams);
            
            TestDBUtility.deleteAll(TraderRow.TYPE);
            TestDBUtility.insertWithDel(l_traderParams);
            
            
            TestDBUtility.deleteAll(HostXbmfOrderRow.TYPE);
            TestDBUtility.deleteAll(HostFrgnMmfOrderRow.TYPE);
            
            //ŽæorderUnit
            FinApp l_finApp = (FinApp) Services.getService(FinApp.class);
            TradingModule l_tradingModule =
                l_finApp.getTradingModule(ProductTypeEnum.MUTUAL_FUND);
            WEB3MutualFundOrderManager l_mfOrderManager =
                (WEB3MutualFundOrderManager)l_tradingModule.getOrderManager();
            MutualFundOrderUnit l_mutualFundOrderUnit =
                (MutualFundOrderUnit)l_mfOrderManager.getOrderUnit(l_mfOrderUnitParams.getOrderUnitId());
            //ŽæsubAccount

            WEB3GentradeAccountManager l_gentradeManager =
                (WEB3GentradeAccountManager)l_finApp.getAccountManager();
            SubAccount l_subAccount =
                l_gentradeManager.getSubAccount(
                    l_subAccountParams.getAccountId(),
                    l_subAccountParams.getSubAccountId());

            
            l_requestService.newOrderMarketRequestSendMessageAcquiredSellSwt(
                l_mutualFundOrderUnit,
                l_subAccount);
            
            
            HostFrgnMmfOrderRow l_fmmfOrderRow =
                HostFrgnMmfOrderDao.findRowByPk("0D","381","123456789");
            assertEquals(l_fmmfOrderRow.getRequestCode(), "DI821");
            assertEquals(l_fmmfOrderRow.getBranchCode(), "381");
            assertEquals(l_fmmfOrderRow.getInstitutionCode(), "0D");
            assertEquals(l_fmmfOrderRow.getAccountCode(), "2512246");
            assertEquals(l_fmmfOrderRow.getTraderCode(), "11123");
            assertEquals(l_fmmfOrderRow.getProductCode(), "0001000");
            assertEquals(l_fmmfOrderRow.getSpecifyDiv(), "1");
            assertEquals(l_fmmfOrderRow.getSellOrderQuantity(), 2222);
            assertEquals(l_fmmfOrderRow.getBuyOrderQuantity(), 0);
            assertEquals(l_fmmfOrderRow.getTicketNumber(), 9789);
            assertEquals(l_fmmfOrderRow.getBuyDiv(), " ");
            assertEquals(l_fmmfOrderRow.getSettlementDiv(), "0");
            assertEquals(l_fmmfOrderRow.getExecuteDiv(), "1");
            assertEquals(l_fmmfOrderRow.getOrderRequestNumber(), "123456789");
            assertEquals(WEB3DateUtility.formatDate(l_fmmfOrderRow.getCreateDate(),"yyyyMMdd"), "20040716");
            assertEquals(WEB3DateUtility.formatDate(l_fmmfOrderRow.getCreateTime(),"yyyyMMdd"),"20040716");
            assertEquals(WEB3DateUtility.formatDate(l_fmmfOrderRow.getOrderDate(),"yyyyMMdd"), "20040720");
            assertEquals(l_fmmfOrderRow.getStatus(), "0");
        }
        catch (WEB3BaseException l_ex)
        {
            log.error(l_ex.getMessage(), l_ex);
            fail(); 
        } 
        catch (Exception l_ex)
        {
            log.error(l_ex.getMessage(), l_ex);
            fail();
        }
        
        log.exiting(STR_METHOD_NAME);

    }
    
//    public void testNewOrderMarketRequestSendMessageAcquiredSellSwtC4()
//    {
//        final String STR_METHOD_NAME = "testNewOrderMarketRequestSendMessageAcquiredSellSwtC4()";
//        log.entering(STR_METHOD_NAME);
//        
//        WEB3MutualFundMarketRequestService l_requestService = new WEB3MutualFundMarketRequestService();
//
//        SubAccountParams l_subAccountParams =
//            TestDBUtility.getSubAccountRow();
//        l_subAccountParams.setAccountId(333812512246L);
//        
//        BranchParams l_branchParams =
//            TestDBUtility.getBranchRow();
//        l_branchParams.setBranchId(33381);
//        
//        MainAccountParams l_mainAccountParams =
//            TestDBUtility.getMainAccountRow();
//        l_mainAccountParams.setAccountId(333812512246L);
//        
//        ProductParams l_productParams =
//            TestDBUtility.getProductRow();
//        l_productParams.setProductId(3304148080001L);
//        l_productParams.setProductType(ProductTypeEnum.MUTUAL_FUND);
//        
//        MutualFundOrderUnitParams l_mfOrderUnitParams =
//            TestDBUtility.getMutualFundOrderUnitRow();
//        l_mfOrderUnitParams.setSubAccountId(33381251220301L);
//        l_mfOrderUnitParams.setProductId(3304148080001L);
//        l_mfOrderUnitParams.setBranchId(33381);
//        l_mfOrderUnitParams.setAccountId(333812512246L);
//        l_mfOrderUnitParams.setOrderRequestNumber("123456789");
//        l_mfOrderUnitParams.setReceivedDateTime(WEB3DateUtility.getDate("20071001","yyyyMMdd"));
//        l_mfOrderUnitParams.setOrderType(OrderTypeEnum.MF_SELL);
//        l_mfOrderUnitParams.setTraderId(null);
//        l_mfOrderUnitParams.setQuantityType(QuantityTypeEnum.AMOUNT);
//        l_mfOrderUnitParams.setSettlementDiv(WEB3SettlementDivDef.FOREIGN_CURRENCY);
//        l_mfOrderUnitParams.setFundSellDiv(WEB3SellDivDef.MONEY_DESIGNATE);
//        l_mfOrderUnitParams.setFundType(MutualFundTypeEnum.FOREIGN_MMF);
//        
//        MutualFundProductParams l_mfProductParams =
//            TestDBUtility.getMutualFundProductRow();
//        l_mfProductParams.setProductId(3304148080001L);
//        
//        TraderParams l_traderParams =
//            TestDBUtility.getTraderRow();
//        l_traderParams.setTraderId(l_mfOrderUnitParams.getTraderId());
//        
//        BranchPreferencesParams l_branchPreferencesParams =
//            TestDBUtility.getBranchPreferencesRow();
//        
//        try
//        {
//            
//            TestDBUtility.deleteAll(SubAccountRow.TYPE);
//            TestDBUtility.insertWithDel(l_subAccountParams);
//            
//            TestDBUtility.deleteAll(BranchRow.TYPE);
//            TestDBUtility.insertWithDel(l_branchParams);
//            
//            TestDBUtility.deleteAll(ProductRow.TYPE);
//            TestDBUtility.insertWithDel(l_productParams);
//            
//            TestDBUtility.deleteAll(MainAccountRow.TYPE);
//            TestDBUtility.insertWithDel(l_mainAccountParams);
//            
//            TestDBUtility.deleteAll(MutualFundOrderUnitRow.TYPE);
//            TestDBUtility.insertWithDel(l_mfOrderUnitParams);
//            
//            TestDBUtility.deleteAll(MutualFundProductRow.TYPE);
//            TestDBUtility.insertWithDel(l_mfProductParams);
//            
//
//            TestDBUtility.deleteAll(BranchPreferencesRow.TYPE);
//            TestDBUtility.insertWithDel(l_branchPreferencesParams);
//            
//            TestDBUtility.deleteAll(TraderRow.TYPE);
//            TestDBUtility.insertWithDel(l_traderParams);
//            
//            
//            TestDBUtility.deleteAll(HostXbmfOrderRow.TYPE);
//            TestDBUtility.deleteAll(HostFrgnMmfOrderRow.TYPE);
//            
//            //ŽæorderUnit
//            FinApp l_finApp = (FinApp) Services.getService(FinApp.class);
//            TradingModule l_tradingModule =
//                l_finApp.getTradingModule(ProductTypeEnum.MUTUAL_FUND);
//            WEB3MutualFundOrderManager l_mfOrderManager =
//                (WEB3MutualFundOrderManager)l_tradingModule.getOrderManager();
//            MutualFundOrderUnit l_mutualFundOrderUnit =
//                (MutualFundOrderUnit)l_mfOrderManager.getOrderUnit(l_mfOrderUnitParams.getOrderUnitId());
//            //ŽæsubAccount
//
//            WEB3GentradeAccountManager l_gentradeManager =
//                (WEB3GentradeAccountManager)l_finApp.getAccountManager();
//            SubAccount l_subAccount =
//                l_gentradeManager.getSubAccount(
//                    l_subAccountParams.getAccountId(),
//                    l_subAccountParams.getSubAccountId());
//
//            
//            l_requestService.newOrderMarketRequestSendMessageAcquiredSellSwt(
//                l_mutualFundOrderUnit,
//                l_subAccount);
//            
//            
//            HostFrgnMmfOrderRow l_fmmfOrderRow =
//                HostFrgnMmfOrderDao.findRowByPk("0D","381","123456789");
//            assertEquals(l_fmmfOrderRow.getRequestCode(), "DI821");
//            assertEquals(l_fmmfOrderRow.getBranchCode(), "381");
//            assertEquals(l_fmmfOrderRow.getInstitutionCode(), "0D");
//            assertEquals(l_fmmfOrderRow.getAccountCode(), "2512246");
//            assertNull(l_fmmfOrderRow.getTraderCode());
//            assertEquals(l_fmmfOrderRow.getProductCode(), "0001000");
//            assertEquals(l_fmmfOrderRow.getSpecifyDiv(), "2");
//            //assertEquals(l_fmmfOrderRow.getSellOrderQuantity(), 0);
//            assertEquals(l_fmmfOrderRow.getBuyOrderQuantity(), 0);
//            assertEquals(l_fmmfOrderRow.getTicketNumber(), 9789);
//            assertEquals(l_fmmfOrderRow.getBuyDiv(), " ");
//            assertEquals(l_fmmfOrderRow.getSettlementDiv(), "1");
//            assertEquals(l_fmmfOrderRow.getExecuteDiv(), "1");
//            assertEquals(l_fmmfOrderRow.getOrderRequestNumber(), "123456789");
//            assertEquals(WEB3DateUtility.formatDate(l_fmmfOrderRow.getCreateDate(),"yyyyMMdd"), "20040716");
//            assertEquals(WEB3DateUtility.formatDate(l_fmmfOrderRow.getCreateTime(),"yyyyMMdd"),"20040716");
//            assertEquals(WEB3DateUtility.formatDate(l_fmmfOrderRow.getOrderDate(),"yyyyMMdd"), "20040720");
//            assertEquals(l_fmmfOrderRow.getStatus(), "0");
//        }
//        catch (WEB3BaseException l_ex)
//        {
//            log.error(l_ex.getMessage(), l_ex);
//            fail(); 
//        } 
//        catch (Exception l_ex)
//        {
//            log.error(l_ex.getMessage(), l_ex);
//            fail();
//        }
//        
//        log.exiting(STR_METHOD_NAME);
//    }
//    
//    public void testCancelOrderMarketRequestSendMessageNotSubmitc1()
//    {
//        final String STR_METHOD_NAME = "testCancelOrderMarketRequestSendMessageNotSubmitc1()";
//        log.entering(STR_METHOD_NAME);
//        
//        WEB3MutualFundMarketRequestService l_requestService = new WEB3MutualFundMarketRequestService();
//
//        SubAccountParams l_subAccountParams =
//            TestDBUtility.getSubAccountRow();
//        l_subAccountParams.setAccountId(333812512246L);
//        
//        BranchParams l_branchParams =
//            TestDBUtility.getBranchRow();
//        l_branchParams.setBranchId(33381);
//        
//        MainAccountParams l_mainAccountParams =
//            TestDBUtility.getMainAccountRow();
//        l_mainAccountParams.setAccountId(333812512246L);
//
//        MutualFundOrderUnitParams l_mfOrderUnitParams =
//            TestDBUtility.getMutualFundOrderUnitRow();
//        l_mfOrderUnitParams.setSubAccountId(33381251220301L);
//        l_mfOrderUnitParams.setProductId(3304148080001L);
//        l_mfOrderUnitParams.setBranchId(33381);
//        l_mfOrderUnitParams.setAccountId(333812512246L);
//        l_mfOrderUnitParams.setOrderRequestNumber("123456789");
//        l_mfOrderUnitParams.setReceivedDateTime(WEB3DateUtility.getDate("20071001","yyyyMMdd"));
//        l_mfOrderUnitParams.setOrderType(OrderTypeEnum.MF_SELL);
//        l_mfOrderUnitParams.setFundType(MutualFundTypeEnum.FOREIGN_MMF);
//        
//        try
//        {
//            TestDBUtility.deleteAll(SubAccountRow.TYPE);
//            TestDBUtility.insertWithDel(l_subAccountParams);
//            
//            TestDBUtility.deleteAll(BranchRow.TYPE);
//            TestDBUtility.insertWithDel(l_branchParams);
//            
//            TestDBUtility.deleteAll(MainAccountRow.TYPE);
//            TestDBUtility.insertWithDel(l_mainAccountParams);
//            
//            TestDBUtility.deleteAll(MutualFundOrderUnitRow.TYPE);
//            TestDBUtility.insertWithDel(l_mfOrderUnitParams);
//            
//            
//            TestDBUtility.deleteAll(HostXbmfOrderRow.TYPE);
//            TestDBUtility.deleteAll(HostFrgnMmfOrderRow.TYPE);
//            
//            //ŽæorderUnit
//            FinApp l_finApp = (FinApp) Services.getService(FinApp.class);
//            TradingModule l_tradingModule =
//                l_finApp.getTradingModule(ProductTypeEnum.MUTUAL_FUND);
//            WEB3MutualFundOrderManager l_mfOrderManager =
//                (WEB3MutualFundOrderManager)l_tradingModule.getOrderManager();
//            MutualFundOrderUnit l_mutualFundOrderUnit =
//                (MutualFundOrderUnit)l_mfOrderManager.getOrderUnit(l_mfOrderUnitParams.getOrderUnitId());
//            //ŽæsubAccount
//
//            WEB3GentradeAccountManager l_gentradeManager =
//                (WEB3GentradeAccountManager)l_finApp.getAccountManager();
//            SubAccount l_subAccount =
//                l_gentradeManager.getSubAccount(
//                    l_subAccountParams.getAccountId(),
//                    l_subAccountParams.getSubAccountId());
//
//            l_requestService.newOrderMarketRequestSendMessageAcquiredSellSwt(
//                l_mutualFundOrderUnit,
//                l_subAccount);
//            HostFrgnMmfOrderRow l_fmmfOrderRow1 =
//                HostFrgnMmfOrderDao.findRowByPk("0D","381","123456789");
//            assertEquals(l_fmmfOrderRow1.getRequestCode(), "DI821");
//            l_requestService.cancelOrderMarketRequestSendMessageNotSubmit(
//                l_mutualFundOrderUnit,
//                l_subAccount);
//            String l_strWhere =
//                " institution_code=? and branch_code=? and account_code=? and order_request_number=? ";
//            Object[] l_objWhereValues = {
//                "0D",
//                "381",
//                "333812512246",
//                "123456789"
//            };
//            QueryProcessor processor = Processors.getDefaultProcessor();
//            List l_list = processor.doFindAllQuery(
//                HostFrgnMmfOrderRow.TYPE,
//                l_strWhere,
//                l_objWhereValues);
//            assertEquals(l_list.size(), 0);
//        }
//        catch (Exception l_ex)
//        {
//            log.error(l_ex.getMessage(), l_ex);
//            fail();
//        }
//        log.exiting(STR_METHOD_NAME);
//    }
//
//    public void testCancelOrderMarketRequestSendMessageNotSubmitc2()
//    {
//        final String STR_METHOD_NAME = "testCancelOrderMarketRequestSendMessageNotSubmitc2()";
//        log.entering(STR_METHOD_NAME);
//        
//        WEB3MutualFundMarketRequestService l_requestService = new WEB3MutualFundMarketRequestService();
// 
//        SubAccountParams l_subAccountParams =
//            TestDBUtility.getSubAccountRow();
//        l_subAccountParams.setAccountId(333812512246L);
//        
//        BranchParams l_branchParams =
//            TestDBUtility.getBranchRow();
//        l_branchParams.setBranchId(33381);
//        
//        MainAccountParams l_mainAccountParams =
//            TestDBUtility.getMainAccountRow();
//        l_mainAccountParams.setAccountId(333812512246L);
//        
//        ProductParams l_productParams =
//            TestDBUtility.getProductRow();
//        l_productParams.setProductId(3304148080001L);
//        l_productParams.setProductType(ProductTypeEnum.MUTUAL_FUND);
// 
//        MutualFundOrderUnitParams l_mfOrderUnitParams =
//            TestDBUtility.getMutualFundOrderUnitRow();
//        l_mfOrderUnitParams.setSubAccountId(33381251220301L);
//        l_mfOrderUnitParams.setProductId(3304148080001L);
//        l_mfOrderUnitParams.setBranchId(33381);
//        l_mfOrderUnitParams.setAccountId(333812512246L);
//        l_mfOrderUnitParams.setOrderRequestNumber("4");
//        l_mfOrderUnitParams.setReceivedDateTime(WEB3DateUtility.getDate("20071001","yyyyMMdd"));
//        l_mfOrderUnitParams.setOrderType(OrderTypeEnum.MF_SELL);
//        l_mfOrderUnitParams.setFundType(MutualFundTypeEnum.FOREIGN);
//        
//        MutualFundProductParams l_mfProductParams =
//            TestDBUtility.getMutualFundProductRow();
//        l_mfProductParams.setProductId(3304148080001L);
//        
//        TraderParams l_traderParams =
//            TestDBUtility.getTraderRow();
//        l_traderParams.setTraderId(l_mfOrderUnitParams.getTraderId());
//        
//        BranchPreferencesParams l_branchPreferencesParams =
//            TestDBUtility.getBranchPreferencesRow();
// 
//        HostXbmfOrderParams l_hostXbmfOrderParams = TestDBUtility.getHostXbmfOrderRow();
//        l_hostXbmfOrderParams.setInstitutionCode("0D");
//        l_hostXbmfOrderParams.setBranchCode("381");
//        l_hostXbmfOrderParams.setOrderRequestNumber("4");
//        l_hostXbmfOrderParams.setAccountCode("2512246");
//        HostFrgnMmfOrderParams l_hostFrgnMmfOrderParams = TestDBUtility.getHostFrgnMmfOrderRow();
//        l_hostFrgnMmfOrderParams.setInstitutionCode("0D");
//        l_hostFrgnMmfOrderParams.setBranchCode("381");
//        l_hostFrgnMmfOrderParams.setOrderRequestNumber("4");
// 
//        InstitutionParams l_institutionParams = TestDBUtility.getInstitutionRow();
//        try
//        {
//            TestDBUtility.deleteAll(SubAccountRow.TYPE);
//            TestDBUtility.insertWithDel(l_subAccountParams);
//            
//            TestDBUtility.deleteAll(BranchRow.TYPE);
//            TestDBUtility.insertWithDel(l_branchParams);
//            
//            TestDBUtility.deleteAll(ProductRow.TYPE);
//            TestDBUtility.insertWithDel(l_productParams);
//            
//            TestDBUtility.deleteAll(MainAccountRow.TYPE);
//            TestDBUtility.insertWithDel(l_mainAccountParams);
//            
//            TestDBUtility.deleteAll(MutualFundOrderUnitRow.TYPE);
//            TestDBUtility.insertWithDel(l_mfOrderUnitParams);
//            
//            TestDBUtility.deleteAll(MutualFundProductRow.TYPE);
//            TestDBUtility.insertWithDel(l_mfProductParams);
//            
// 
//            TestDBUtility.deleteAll(BranchPreferencesRow.TYPE);
//            TestDBUtility.insertWithDel(l_branchPreferencesParams);
//            
//            TestDBUtility.deleteAll(TraderRow.TYPE);
//            TestDBUtility.insertWithDel(l_traderParams);
//            
//            
//            TestDBUtility.deleteAll(HostXbmfOrderRow.TYPE);
//            TestDBUtility.deleteAll(HostFrgnMmfOrderRow.TYPE);
//            TestDBUtility.deleteAll(InstitutionRow.TYPE);
// 
//            TestDBUtility.insertWithDel(l_hostXbmfOrderParams);
//            TestDBUtility.insertWithDel(l_hostFrgnMmfOrderParams);
//            TestDBUtility.insertWithDel(l_institutionParams);
//            //ŽæorderUnit
//            FinApp l_finApp = (FinApp) Services.getService(FinApp.class);
//            TradingModule l_tradingModule =
//                l_finApp.getTradingModule(ProductTypeEnum.MUTUAL_FUND);
//            WEB3MutualFundOrderManager l_mfOrderManager =
//                (WEB3MutualFundOrderManager)l_tradingModule.getOrderManager();
//            MutualFundOrderUnit l_mutualFundOrderUnit =
//                (MutualFundOrderUnit)l_mfOrderManager.getOrderUnit(l_mfOrderUnitParams.getOrderUnitId());
//            //ŽæsubAccount
// 
//            WEB3GentradeAccountManager l_gentradeManager =
//                (WEB3GentradeAccountManager)l_finApp.getAccountManager();
//            SubAccount l_subAccount =
//                l_gentradeManager.getSubAccount(
//                    l_subAccountParams.getAccountId(),
//                    l_subAccountParams.getSubAccountId());
// 
//            l_requestService.cancelOrderMarketRequestSendMessageNotSubmit(
//                l_mutualFundOrderUnit,
//                l_subAccount);
//            
//            HostFrgnMmfOrderRow l_fmmfOrderRow2 =
//                HostFrgnMmfOrderDao.findRowByPk("0D","381","4");
//            assertEquals(l_fmmfOrderRow2.getRequestCode(), "CI801");
// 
//        }
//        catch (Exception l_ex)
//        {
//            log.error(l_ex.getMessage(), l_ex);
//            fail();
//        }
//        String l_strWhere =
//            " institution_code=? and branch_code=? and account_code=? and order_request_number=? ";
//        Object[] l_objWhereValues = {
//            "0D",
//            "381",
//            "2512246",
//            "4"
//        };
// 
//        List l_list = null;
//        try
//        {
//            QueryProcessor processor = Processors.getDefaultProcessor();
//            l_list = processor.doFindAllQuery(
//                HostXbmfOrderRow.TYPE,
//                l_strWhere,
//                l_objWhereValues);
//            assertEquals(0, l_list.size());
//        }
//        catch (DataQueryException l_ex)
//        {
//            log.error(l_ex.getMessage(), l_ex);
//            fail();
//        }
//        catch (DataNetworkException l_ex)
//        {
//            log.error(l_ex.getMessage(), l_ex);
//            fail();
//        }
// 
//        log.exiting(STR_METHOD_NAME);
//    }

}
@
