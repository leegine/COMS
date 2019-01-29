head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.04.07.00.49.51;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	5444d9d078c63f4;
filename	WEB3TPTradingPowerServiceImplTest_temp.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : 取引余力サービスImpl
Author Name      : Daiwa Institute of Research
Revesion History : 2007/07/16　@孟亜南(中訊) 
*/
package webbroker3.tradingpower;

import java.util.Calendar;
import java.util.Date;

import com.fitechlabs.xtrade.kernel.data.DataNetworkException;
import com.fitechlabs.xtrade.kernel.data.DataQueryException;
import com.fitechlabs.xtrade.plugin.tc.gentrade.ProductTypeEnum;
import com.fitechlabs.xtrade.plugin.tc.gentrade.SubAccountTypeEnum;
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

import test.util.TestDBUtility;

import webbroker3.common.WEB3BaseException;
import webbroker3.gentrade.WEB3GentradeSubAccount;
import webbroker3.gentrade.data.TradingpowerCalcConditionParams;
import webbroker3.gentrade.data.TradingpowerCalcConditionRow;
import webbroker3.mock.TestBaseForMock;
import webbroker3.tradingpower.data.TpCalcResultMarginDetailParams;
import webbroker3.tradingpower.data.TpCalcResultMarginParams;
import webbroker3.tradingpower.define.WEB3TPMarkToMarketDivDef;
import webbroker3.util.WEB3LogUtility;

public class WEB3TPTradingPowerServiceImplTest_temp extends TestBaseForMock
{
    
    public WEB3TPTradingPowerServiceImplTest_temp(String name)
    {
        super(name);
    }

    private static WEB3LogUtility log =
        WEB3LogUtility.getInstance(WEB3TPTradingPowerServiceImplTest.class);

    protected void setUp() throws Exception
    {
        super.setUp();
    }

    protected void tearDown() throws Exception
    {
        super.tearDown();
    }

    /**
     * 
     *
     */
//    public void test_getMarginPowerTransferAmount_0001()
//    {
//        final String STR_METHOD_NAME =
//            "test_getMarginPowerTransferAmount_0001()";
//        log.entering(STR_METHOD_NAME);
//        
//        WEB3TPTradingPowerServiceImplAForTest l_impl = new WEB3TPTradingPowerServiceImplAForTest();
//
//        Date l_datDeliveryDate = Calendar.getInstance().getTime();
//        double l_dblCashinAmt = 81.2356D;
////        String l_strPassword = null;
//        
//        
//        try
//        {
//            TestDBUtility.deleteAll(MainAccountRow.TYPE);
//            MainAccountParams l_mainAccountParams = TestDBUtility.getMainAccountRow();
//            l_mainAccountParams.setMarginGenAccOpenDiv("1");
//            l_mainAccountParams.setMarginSysAccOpenDiv("1");
//            TestDBUtility.insertWithDel(l_mainAccountParams);
//            
//            TestDBUtility.deleteAll(SubAccountRow.TYPE);
//            SubAccountParams l_subAccountParams = TestDBUtility.getSubAccountRow();
//            l_subAccountParams.setAccountId(333812512246L);
//            l_subAccountParams.setSubAccountId(10);
//            l_subAccountParams.setSubAccountType(SubAccountTypeEnum.EQUITY_SUB_ACCOUNT);
//            
//            TestDBUtility.insertWithDel(l_subAccountParams);
//            
////            SubAccountParams l_subAccountParams1 = TestDBUtility.getSubAccountRow();
////            l_subAccountParams1.setAccountId(333812512246L);
////            l_subAccountParams1.setSubAccountId(20);
////            l_subAccountParams1.setSubAccountType(SubAccountTypeEnum.EQUITY_MARGIN_SUB_ACCOUNT);
////            TestDBUtility.insertWithDel(l_subAccountParams1);
//            
//            TestDBUtility.deleteAll(TradingpowerCalcConditionRow.TYPE);
//            TradingpowerCalcConditionParams l_tradingpowerCalcConditionParams = 
//                TestDBUtility.getTradingpowerCalcConditionRow();
//            l_tradingpowerCalcConditionParams.setAccountId(333812512246L);
//            TestDBUtility.insertWithDel(l_tradingpowerCalcConditionParams);
//            
//            TestDBUtility.deleteAll(InstitutionRow.TYPE);
//            InstitutionParams l_institutionParams = 
//                TestDBUtility.getInstitutionRow();
//            l_institutionParams.setInstitutionId(33);
//            TestDBUtility.insertWithDel(l_institutionParams);
//            
//            TestDBUtility.deleteAll(BranchRow.TYPE);
//            BranchParams l_branchParams = 
//                TestDBUtility.getBranchRow();
//            l_branchParams.setBranchId(33381L);
//            TestDBUtility.insertWithDel(l_branchParams);
//            
//            TestDBUtility.deleteAll(TpCalcResultMarginParams.TYPE);
//            TpCalcResultMarginParams l_tpCalcResultMarginParams = new  TpCalcResultMarginParams();
//            
//            l_tpCalcResultMarginParams.setCalcResultMarginId(1);                               
//            l_tpCalcResultMarginParams.setAccountId(333812512246L);                                 
//            l_tpCalcResultMarginParams.setAccountBalance0(1);                                 
//            l_tpCalcResultMarginParams.setAccountBalance1(1);                                 
//            l_tpCalcResultMarginParams.setAccountBalance2(1);                                 
//            l_tpCalcResultMarginParams.setAccountBalance3(1);                                 
//            l_tpCalcResultMarginParams.setAccountBalance4(1);                             
//            l_tpCalcResultMarginParams.setAccountBalance5(1);                                 
//            l_tpCalcResultMarginParams.setTodayExecutedAmount0(1);                                    
//            l_tpCalcResultMarginParams.setTodayExecutedAmount1(1);                                    
//            l_tpCalcResultMarginParams.setTodayExecutedAmount2(1);                                    
//            l_tpCalcResultMarginParams.setTodayExecutedAmount3(1);                                    
//            l_tpCalcResultMarginParams.setTodayExecutedAmount4(1);                                    
//            l_tpCalcResultMarginParams.setTodayExecutedAmount5(1);                                    
//            l_tpCalcResultMarginParams.setTodayUnexecutedAmount0(1);                                  
//            l_tpCalcResultMarginParams.setTodayUnexecutedAmount1(1);                                  
//            l_tpCalcResultMarginParams.setTodayUnexecutedAmount2(1);                                  
//            l_tpCalcResultMarginParams.setTodayUnexecutedAmount3(1);                                  
//            l_tpCalcResultMarginParams.setTodayUnexecutedAmount4(1);                                  
//            l_tpCalcResultMarginParams.setTodayUnexecutedAmount5(1);                                  
//            l_tpCalcResultMarginParams.setDayTradeRestraint0(1);                                  
//            l_tpCalcResultMarginParams.setDayTradeRestraint1(1);                                  
//            l_tpCalcResultMarginParams.setDayTradeRestraint2(1);                                  
//            l_tpCalcResultMarginParams.setDayTradeRestraint3(1);                                  
//            l_tpCalcResultMarginParams.setDayTradeRestraint4(1);                                  
//            l_tpCalcResultMarginParams.setOtherRestraint0(1);                                 
//            l_tpCalcResultMarginParams.setOtherRestraint1(1);                                 
//            l_tpCalcResultMarginParams.setOtherRestraint2(1);                                 
//            l_tpCalcResultMarginParams.setOtherRestraint3(1);                                 
//            l_tpCalcResultMarginParams.setOtherRestraint4(1);                                 
//            l_tpCalcResultMarginParams.setOtherRestraint5(1);                                 
//            l_tpCalcResultMarginParams.setSubstituteSecurityAsset0(1);                                    
//            l_tpCalcResultMarginParams.setSubstituteSecurityAsset1(1);                                
//            l_tpCalcResultMarginParams.setSubstituteSecurityAsset2(1);                                
//            l_tpCalcResultMarginParams.setSubstituteSecurityAsset3(1);                                
//            l_tpCalcResultMarginParams.setSubstituteSecurityAsset4(1);                                    
//            l_tpCalcResultMarginParams.setSubstituteSecurityAsset5(1);                                    
//            l_tpCalcResultMarginParams.setContractAmount0(1);                                 
//            l_tpCalcResultMarginParams.setContractAmount1(1);                                 
//            l_tpCalcResultMarginParams.setContractAmount2(1);                                 
//            l_tpCalcResultMarginParams.setContractAmount3(1);                                 
//            l_tpCalcResultMarginParams.setContractAmount4(1);                                 
//            l_tpCalcResultMarginParams.setContractAmount5(1);                                 
//            l_tpCalcResultMarginParams.setDayRepayContractAmount0(1);                                 
//            l_tpCalcResultMarginParams.setDayRepayContractAmount1(1);                                 
//            l_tpCalcResultMarginParams.setDayRepayContractAmount2(1);                                 
//            l_tpCalcResultMarginParams.setDayRepayContractAmount3(1);                                 
//            l_tpCalcResultMarginParams.setDayRepayContractAmount4(1);                                 
//            l_tpCalcResultMarginParams.setDayRepayContractAmount5(1);                                 
//            l_tpCalcResultMarginParams.setMarginDeposit0(1);                                  
//            l_tpCalcResultMarginParams.setMarginDeposit1(1);                                  
//            l_tpCalcResultMarginParams.setMarginDeposit2(1);                                  
//            l_tpCalcResultMarginParams.setMarginDeposit3(1);                                  
//            l_tpCalcResultMarginParams.setMarginDeposit4(1);                                  
//            l_tpCalcResultMarginParams.setMarginDeposit5(1);                                  
//            l_tpCalcResultMarginParams.setCashMarginDeposit0(1);                                  
//            l_tpCalcResultMarginParams.setCashMarginDeposit1(1);                                  
//            l_tpCalcResultMarginParams.setCashMarginDeposit2(1);                                  
//            l_tpCalcResultMarginParams.setCashMarginDeposit3(1);                                  
//            l_tpCalcResultMarginParams.setCashMarginDeposit4(1);                                  
//            l_tpCalcResultMarginParams.setCashMarginDeposit5(1);                                  
//            l_tpCalcResultMarginParams.setContractAssetProfitLoss(1);                                 
//            l_tpCalcResultMarginParams.setUndeliContractAmount0(1);                                   
//            l_tpCalcResultMarginParams.setUndeliContractAmount1(1);                                   
//            l_tpCalcResultMarginParams.setUndeliContractAmount2(1);                                   
//            l_tpCalcResultMarginParams.setUndeliContractAmount3(1);                                   
//            l_tpCalcResultMarginParams.setUndeliMarginDeposit0(1);                                    
//            l_tpCalcResultMarginParams.setUndeliMarginDeposit1(1);                                    
//            l_tpCalcResultMarginParams.setUndeliMarginDeposit2(1);                                    
//            l_tpCalcResultMarginParams.setUndeliMarginDeposit3(1);                                    
//            l_tpCalcResultMarginParams.setUndeliCashMarginDeposit0(1);                                    
//            l_tpCalcResultMarginParams.setUndeliCashMarginDeposit1(1);                                    
//            l_tpCalcResultMarginParams.setUndeliCashMarginDeposit2(1);                                    
//            l_tpCalcResultMarginParams.setUndeliCashMarginDeposit3(1);                                    
//            l_tpCalcResultMarginParams.setUndeliContractLoss0(1);                                 
//            l_tpCalcResultMarginParams.setUndeliContractLoss1(1);                                 
//            l_tpCalcResultMarginParams.setUndeliContractLoss2(1);                                 
//            l_tpCalcResultMarginParams.setUndeliContractLoss3(1);                                 
//            l_tpCalcResultMarginParams.setUndeliContractProfit0(1);                                   
//            l_tpCalcResultMarginParams.setUndeliContractProfit1(1);                                   
//            l_tpCalcResultMarginParams.setUndeliContractProfit2(1);                                   
//            l_tpCalcResultMarginParams.setUndeliContractProfit3(1);                                   
//            l_tpCalcResultMarginParams.setContractTotalCost(1);                             
//            l_tpCalcResultMarginParams.setCreatedTimestamp(Calendar.getInstance().getTime());                                  
//            l_tpCalcResultMarginParams.setLastUpdatedTimestamp(Calendar.getInstance().getTime());  
//            l_tpCalcResultMarginParams.setMarkToMarketDiv(WEB3TPMarkToMarketDivDef.NORMAL);
//            TestDBUtility.insertWithDel(l_tpCalcResultMarginParams);
//            
//            TestDBUtility.deleteAll(ProductRow.TYPE);
//            ProductParams l_productParams = 
//                TestDBUtility.getProductRow();
//            l_productParams.setProductType(ProductTypeEnum.CASH);
//            l_productParams.setInstitutionCode("0D");
//            TestDBUtility.insertWithDel(l_productParams);
//            
//            
//            TestDBUtility.deleteAll(TpCalcResultMarginDetailParams.TYPE);
//            TpCalcResultMarginDetailParams l_tpCalcResultMarginDetailParams = new  TpCalcResultMarginDetailParams();
//            l_tpCalcResultMarginDetailParams.setCalcResultMarginId(1);                               
//            l_tpCalcResultMarginDetailParams.setAccountId(333812512246L); 
//            l_tpCalcResultMarginDetailParams.setCreatedTimestamp(Calendar.getInstance().getTime());                                  
//            l_tpCalcResultMarginDetailParams.setLastUpdatedTimestamp(Calendar.getInstance().getTime()); 
////            l_tpCalcResultMarginDetailParams.setmarktomarketdiv();
//            TestDBUtility.insertWithDel(l_tpCalcResultMarginDetailParams);
//            
//            
//            WEB3GentradeSubAccount l_subAccount = null;
//            try
//            {
//                l_subAccount = new WEB3GentradeSubAccount(333812512246L,10L);
//            }
//            catch (DataQueryException e1)
//            {
//                log.exiting(STR_METHOD_NAME);
//                fail();
//            }
//            catch (DataNetworkException e1)
//            {
//                log.exiting(STR_METHOD_NAME);
//                fail();
//            }
//            
//            double l_dbl = l_impl.getTransferAmountToDeposit(l_subAccount,l_datDeliveryDate,l_dblCashinAmt);
//            assertEquals("" + 81.2356D,"" + l_dbl);
//        }
//        catch (WEB3BaseException e)
//        {
//            log.exiting(STR_METHOD_NAME);
//            log.debug("" + e.getErrorInfo());
//            fail();
//        }
//
//        log.exiting(STR_METHOD_NAME);
//    }
    
    /**
     * 
     *
     */
//    public void test_getMarginPowerTransferAmount_0002()
//    {
//        final String STR_METHOD_NAME =
//            "test_getMarginPowerTransferAmount_0002()";
//        log.entering(STR_METHOD_NAME);
//        
//        WEB3TPTradingPowerServiceImplBForTest l_impl = new WEB3TPTradingPowerServiceImplBForTest();
//
//        Date l_datDeliveryDate = Calendar.getInstance().getTime();
//        double l_dblCashinAmt = 81.2356D;
////        String l_strPassword = null;
//        
//        
//        try
//        {
//            TestDBUtility.deleteAll(MainAccountRow.TYPE);
//            MainAccountParams l_mainAccountParams = TestDBUtility.getMainAccountRow();
//            l_mainAccountParams.setMarginGenAccOpenDiv("1");
//            l_mainAccountParams.setMarginSysAccOpenDiv("1");
//            TestDBUtility.insertWithDel(l_mainAccountParams);
//            
//            TestDBUtility.deleteAll(SubAccountRow.TYPE);
//            SubAccountParams l_subAccountParams = TestDBUtility.getSubAccountRow();
//            l_subAccountParams.setAccountId(333812512246L);
//            l_subAccountParams.setSubAccountId(10);
//            l_subAccountParams.setSubAccountType(SubAccountTypeEnum.EQUITY_SUB_ACCOUNT);
//            
//            TestDBUtility.insertWithDel(l_subAccountParams);
//            
////            SubAccountParams l_subAccountParams1 = TestDBUtility.getSubAccountRow();
////            l_subAccountParams1.setAccountId(333812512246L);
////            l_subAccountParams1.setSubAccountId(20);
////            l_subAccountParams1.setSubAccountType(SubAccountTypeEnum.EQUITY_MARGIN_SUB_ACCOUNT);
////            TestDBUtility.insertWithDel(l_subAccountParams1);
//            
//            TestDBUtility.deleteAll(TradingpowerCalcConditionRow.TYPE);
//            TradingpowerCalcConditionParams l_tradingpowerCalcConditionParams = 
//                TestDBUtility.getTradingpowerCalcConditionRow();
//            l_tradingpowerCalcConditionParams.setAccountId(333812512246L);
//            TestDBUtility.insertWithDel(l_tradingpowerCalcConditionParams);
//            
//            TestDBUtility.deleteAll(InstitutionRow.TYPE);
//            InstitutionParams l_institutionParams = 
//                TestDBUtility.getInstitutionRow();
//            l_institutionParams.setInstitutionId(33);
//            TestDBUtility.insertWithDel(l_institutionParams);
//            
//            TestDBUtility.deleteAll(BranchRow.TYPE);
//            BranchParams l_branchParams = 
//                TestDBUtility.getBranchRow();
//            l_branchParams.setBranchId(33381L);
//            TestDBUtility.insertWithDel(l_branchParams);
//            
//            TestDBUtility.deleteAll(TpCalcResultMarginParams.TYPE);
//            TpCalcResultMarginParams l_tpCalcResultMarginParams = new  TpCalcResultMarginParams();
//            
//            l_tpCalcResultMarginParams.setCalcResultMarginId(1);                               
//            l_tpCalcResultMarginParams.setAccountId(333812512246L);                                 
//            l_tpCalcResultMarginParams.setAccountBalance0(1);                                 
//            l_tpCalcResultMarginParams.setAccountBalance1(1);                                 
//            l_tpCalcResultMarginParams.setAccountBalance2(1);                                 
//            l_tpCalcResultMarginParams.setAccountBalance3(1);                                 
//            l_tpCalcResultMarginParams.setAccountBalance4(1);                             
//            l_tpCalcResultMarginParams.setAccountBalance5(1);                                 
//            l_tpCalcResultMarginParams.setTodayExecutedAmount0(1);                                    
//            l_tpCalcResultMarginParams.setTodayExecutedAmount1(1);                                    
//            l_tpCalcResultMarginParams.setTodayExecutedAmount2(1);                                    
//            l_tpCalcResultMarginParams.setTodayExecutedAmount3(1);                                    
//            l_tpCalcResultMarginParams.setTodayExecutedAmount4(1);                                    
//            l_tpCalcResultMarginParams.setTodayExecutedAmount5(1);                                    
//            l_tpCalcResultMarginParams.setTodayUnexecutedAmount0(1);                                  
//            l_tpCalcResultMarginParams.setTodayUnexecutedAmount1(1);                                  
//            l_tpCalcResultMarginParams.setTodayUnexecutedAmount2(1);                                  
//            l_tpCalcResultMarginParams.setTodayUnexecutedAmount3(1);                                  
//            l_tpCalcResultMarginParams.setTodayUnexecutedAmount4(1);                                  
//            l_tpCalcResultMarginParams.setTodayUnexecutedAmount5(1);                                  
//            l_tpCalcResultMarginParams.setDayTradeRestraint0(1);                                  
//            l_tpCalcResultMarginParams.setDayTradeRestraint1(1);                                  
//            l_tpCalcResultMarginParams.setDayTradeRestraint2(1);                                  
//            l_tpCalcResultMarginParams.setDayTradeRestraint3(1);                                  
//            l_tpCalcResultMarginParams.setDayTradeRestraint4(1);                                  
//            l_tpCalcResultMarginParams.setOtherRestraint0(1);                                 
//            l_tpCalcResultMarginParams.setOtherRestraint1(1);                                 
//            l_tpCalcResultMarginParams.setOtherRestraint2(1);                                 
//            l_tpCalcResultMarginParams.setOtherRestraint3(1);                                 
//            l_tpCalcResultMarginParams.setOtherRestraint4(1);                                 
//            l_tpCalcResultMarginParams.setOtherRestraint5(1);                                 
//            l_tpCalcResultMarginParams.setSubstituteSecurityAsset0(1);                                    
//            l_tpCalcResultMarginParams.setSubstituteSecurityAsset1(1);                                
//            l_tpCalcResultMarginParams.setSubstituteSecurityAsset2(1);                                
//            l_tpCalcResultMarginParams.setSubstituteSecurityAsset3(1);                                
//            l_tpCalcResultMarginParams.setSubstituteSecurityAsset4(1);                                    
//            l_tpCalcResultMarginParams.setSubstituteSecurityAsset5(1);                                    
//            l_tpCalcResultMarginParams.setContractAmount0(1);                                 
//            l_tpCalcResultMarginParams.setContractAmount1(1);                                 
//            l_tpCalcResultMarginParams.setContractAmount2(1);                                 
//            l_tpCalcResultMarginParams.setContractAmount3(1);                                 
//            l_tpCalcResultMarginParams.setContractAmount4(1);                                 
//            l_tpCalcResultMarginParams.setContractAmount5(1);                                 
//            l_tpCalcResultMarginParams.setDayRepayContractAmount0(1);                                 
//            l_tpCalcResultMarginParams.setDayRepayContractAmount1(1);                                 
//            l_tpCalcResultMarginParams.setDayRepayContractAmount2(1);                                 
//            l_tpCalcResultMarginParams.setDayRepayContractAmount3(1);                                 
//            l_tpCalcResultMarginParams.setDayRepayContractAmount4(1);                                 
//            l_tpCalcResultMarginParams.setDayRepayContractAmount5(1);                                 
//            l_tpCalcResultMarginParams.setMarginDeposit0(1);                                  
//            l_tpCalcResultMarginParams.setMarginDeposit1(1);                                  
//            l_tpCalcResultMarginParams.setMarginDeposit2(1);                                  
//            l_tpCalcResultMarginParams.setMarginDeposit3(1);                                  
//            l_tpCalcResultMarginParams.setMarginDeposit4(1);                                  
//            l_tpCalcResultMarginParams.setMarginDeposit5(1);                                  
//            l_tpCalcResultMarginParams.setCashMarginDeposit0(1);                                  
//            l_tpCalcResultMarginParams.setCashMarginDeposit1(1);                                  
//            l_tpCalcResultMarginParams.setCashMarginDeposit2(1);                                  
//            l_tpCalcResultMarginParams.setCashMarginDeposit3(1);                                  
//            l_tpCalcResultMarginParams.setCashMarginDeposit4(1);                                  
//            l_tpCalcResultMarginParams.setCashMarginDeposit5(1);                                  
//            l_tpCalcResultMarginParams.setContractAssetProfitLoss(1);                                 
//            l_tpCalcResultMarginParams.setUndeliContractAmount0(1);                                   
//            l_tpCalcResultMarginParams.setUndeliContractAmount1(1);                                   
//            l_tpCalcResultMarginParams.setUndeliContractAmount2(1);                                   
//            l_tpCalcResultMarginParams.setUndeliContractAmount3(1);                                   
//            l_tpCalcResultMarginParams.setUndeliMarginDeposit0(1);                                    
//            l_tpCalcResultMarginParams.setUndeliMarginDeposit1(1);                                    
//            l_tpCalcResultMarginParams.setUndeliMarginDeposit2(1);                                    
//            l_tpCalcResultMarginParams.setUndeliMarginDeposit3(1);                                    
//            l_tpCalcResultMarginParams.setUndeliCashMarginDeposit0(1);                                    
//            l_tpCalcResultMarginParams.setUndeliCashMarginDeposit1(1);                                    
//            l_tpCalcResultMarginParams.setUndeliCashMarginDeposit2(1);                                    
//            l_tpCalcResultMarginParams.setUndeliCashMarginDeposit3(1);                                    
//            l_tpCalcResultMarginParams.setUndeliContractLoss0(1);                                 
//            l_tpCalcResultMarginParams.setUndeliContractLoss1(1);                                 
//            l_tpCalcResultMarginParams.setUndeliContractLoss2(1);                                 
//            l_tpCalcResultMarginParams.setUndeliContractLoss3(1);                                 
//            l_tpCalcResultMarginParams.setUndeliContractProfit0(1);                                   
//            l_tpCalcResultMarginParams.setUndeliContractProfit1(1);                                   
//            l_tpCalcResultMarginParams.setUndeliContractProfit2(1);                                   
//            l_tpCalcResultMarginParams.setUndeliContractProfit3(1);                                   
//            l_tpCalcResultMarginParams.setContractTotalCost(1);                             
//            l_tpCalcResultMarginParams.setCreatedTimestamp(Calendar.getInstance().getTime());                                  
//            l_tpCalcResultMarginParams.setLastUpdatedTimestamp(Calendar.getInstance().getTime());  
//            l_tpCalcResultMarginParams.setMarkToMarketDiv(WEB3TPMarkToMarketDivDef.NORMAL);
//            TestDBUtility.insertWithDel(l_tpCalcResultMarginParams);
//            
//            TestDBUtility.deleteAll(ProductRow.TYPE);
//            ProductParams l_productParams = 
//                TestDBUtility.getProductRow();
//            l_productParams.setProductType(ProductTypeEnum.CASH);
//            l_productParams.setInstitutionCode("0D");
//            TestDBUtility.insertWithDel(l_productParams);
//            
//            
//            TestDBUtility.deleteAll(TpCalcResultMarginDetailParams.TYPE);
//            TpCalcResultMarginDetailParams l_tpCalcResultMarginDetailParams = new  TpCalcResultMarginDetailParams();
//            l_tpCalcResultMarginDetailParams.setCalcResultMarginId(1);                               
//            l_tpCalcResultMarginDetailParams.setAccountId(333812512246L); 
//            l_tpCalcResultMarginDetailParams.setCreatedTimestamp(Calendar.getInstance().getTime());                                  
//            l_tpCalcResultMarginDetailParams.setLastUpdatedTimestamp(Calendar.getInstance().getTime()); 
////            l_tpCalcResultMarginDetailParams.setmarktomarketdiv();
//            TestDBUtility.insertWithDel(l_tpCalcResultMarginDetailParams);
//            
//            
//            WEB3GentradeSubAccount l_subAccount = null;
//            try
//            {
//                l_subAccount = new WEB3GentradeSubAccount(333812512246L,10L);
//            }
//            catch (DataQueryException e1)
//            {
//                log.exiting(STR_METHOD_NAME);
//                fail();
//            }
//            catch (DataNetworkException e1)
//            {
//                log.exiting(STR_METHOD_NAME);
//                fail();
//            }
//            
//            double l_dbl = l_impl.getTransferAmountToDeposit(l_subAccount,l_datDeliveryDate,l_dblCashinAmt);
//            assertEquals("" + 51.1126D,"" + l_dbl);
//        }
//        catch (WEB3BaseException e)
//        {
//            log.exiting(STR_METHOD_NAME);
//            log.debug("" + e.getErrorInfo());
//            fail();
//        }
//
//        log.exiting(STR_METHOD_NAME);
//    }
//    
//    /**
//     * 
//     *
//     */
//    public void test_getMarginPowerTransferAmount_0003()
//    {
//        final String STR_METHOD_NAME =
//            "test_getMarginPowerTransferAmount_0003()";
//        log.entering(STR_METHOD_NAME);
//        
//        WEB3TPTradingPowerServiceImplCForTest l_impl = new WEB3TPTradingPowerServiceImplCForTest();
//
//        Date l_datDeliveryDate = Calendar.getInstance().getTime();
//        double l_dblCashinAmt = 2.0D;
////        String l_strPassword = null;
//        
//        
//        try
//        {
//            TestDBUtility.deleteAll(MainAccountRow.TYPE);
//            MainAccountParams l_mainAccountParams = TestDBUtility.getMainAccountRow();
//            l_mainAccountParams.setMarginGenAccOpenDiv("1");
//            l_mainAccountParams.setMarginSysAccOpenDiv("1");
//            TestDBUtility.insertWithDel(l_mainAccountParams);
//            
//            TestDBUtility.deleteAll(SubAccountRow.TYPE);
//            SubAccountParams l_subAccountParams = TestDBUtility.getSubAccountRow();
//            l_subAccountParams.setAccountId(333812512246L);
//            l_subAccountParams.setSubAccountId(10);
//            l_subAccountParams.setSubAccountType(SubAccountTypeEnum.EQUITY_SUB_ACCOUNT);
//            
//            TestDBUtility.insertWithDel(l_subAccountParams);
//            
////            SubAccountParams l_subAccountParams1 = TestDBUtility.getSubAccountRow();
////            l_subAccountParams1.setAccountId(333812512246L);
////            l_subAccountParams1.setSubAccountId(20);
////            l_subAccountParams1.setSubAccountType(SubAccountTypeEnum.EQUITY_MARGIN_SUB_ACCOUNT);
////            TestDBUtility.insertWithDel(l_subAccountParams1);
//            
//            TestDBUtility.deleteAll(TradingpowerCalcConditionRow.TYPE);
//            TradingpowerCalcConditionParams l_tradingpowerCalcConditionParams = 
//                TestDBUtility.getTradingpowerCalcConditionRow();
//            l_tradingpowerCalcConditionParams.setAccountId(333812512246L);
//            TestDBUtility.insertWithDel(l_tradingpowerCalcConditionParams);
//            
//            TestDBUtility.deleteAll(InstitutionRow.TYPE);
//            InstitutionParams l_institutionParams = 
//                TestDBUtility.getInstitutionRow();
//            l_institutionParams.setInstitutionId(33);
//            TestDBUtility.insertWithDel(l_institutionParams);
//            
//            TestDBUtility.deleteAll(BranchRow.TYPE);
//            BranchParams l_branchParams = 
//                TestDBUtility.getBranchRow();
//            l_branchParams.setBranchId(33381L);
//            TestDBUtility.insertWithDel(l_branchParams);
//            
//            TestDBUtility.deleteAll(TpCalcResultMarginParams.TYPE);
//            TpCalcResultMarginParams l_tpCalcResultMarginParams = new  TpCalcResultMarginParams();
//            
//            l_tpCalcResultMarginParams.setCalcResultMarginId(1);                               
//            l_tpCalcResultMarginParams.setAccountId(333812512246L);                                 
//            l_tpCalcResultMarginParams.setAccountBalance0(1);                                 
//            l_tpCalcResultMarginParams.setAccountBalance1(1);                                 
//            l_tpCalcResultMarginParams.setAccountBalance2(1);                                 
//            l_tpCalcResultMarginParams.setAccountBalance3(1);                                 
//            l_tpCalcResultMarginParams.setAccountBalance4(1);                             
//            l_tpCalcResultMarginParams.setAccountBalance5(1);                                 
//            l_tpCalcResultMarginParams.setTodayExecutedAmount0(1);                                    
//            l_tpCalcResultMarginParams.setTodayExecutedAmount1(1);                                    
//            l_tpCalcResultMarginParams.setTodayExecutedAmount2(1);                                    
//            l_tpCalcResultMarginParams.setTodayExecutedAmount3(1);                                    
//            l_tpCalcResultMarginParams.setTodayExecutedAmount4(1);                                    
//            l_tpCalcResultMarginParams.setTodayExecutedAmount5(1);                                    
//            l_tpCalcResultMarginParams.setTodayUnexecutedAmount0(1);                                  
//            l_tpCalcResultMarginParams.setTodayUnexecutedAmount1(1);                                  
//            l_tpCalcResultMarginParams.setTodayUnexecutedAmount2(1);                                  
//            l_tpCalcResultMarginParams.setTodayUnexecutedAmount3(1);                                  
//            l_tpCalcResultMarginParams.setTodayUnexecutedAmount4(1);                                  
//            l_tpCalcResultMarginParams.setTodayUnexecutedAmount5(1);                                  
//            l_tpCalcResultMarginParams.setDayTradeRestraint0(1);                                  
//            l_tpCalcResultMarginParams.setDayTradeRestraint1(1);                                  
//            l_tpCalcResultMarginParams.setDayTradeRestraint2(1);                                  
//            l_tpCalcResultMarginParams.setDayTradeRestraint3(1);                                  
//            l_tpCalcResultMarginParams.setDayTradeRestraint4(1);                                  
//            l_tpCalcResultMarginParams.setOtherRestraint0(1);                                 
//            l_tpCalcResultMarginParams.setOtherRestraint1(1);                                 
//            l_tpCalcResultMarginParams.setOtherRestraint2(1);                                 
//            l_tpCalcResultMarginParams.setOtherRestraint3(1);                                 
//            l_tpCalcResultMarginParams.setOtherRestraint4(1);                                 
//            l_tpCalcResultMarginParams.setOtherRestraint5(1);                                 
//            l_tpCalcResultMarginParams.setSubstituteSecurityAsset0(1);                                    
//            l_tpCalcResultMarginParams.setSubstituteSecurityAsset1(1);                                
//            l_tpCalcResultMarginParams.setSubstituteSecurityAsset2(1);                                
//            l_tpCalcResultMarginParams.setSubstituteSecurityAsset3(1);                                
//            l_tpCalcResultMarginParams.setSubstituteSecurityAsset4(1);                                    
//            l_tpCalcResultMarginParams.setSubstituteSecurityAsset5(1);                                    
//            l_tpCalcResultMarginParams.setContractAmount0(1);                                 
//            l_tpCalcResultMarginParams.setContractAmount1(1);                                 
//            l_tpCalcResultMarginParams.setContractAmount2(1);                                 
//            l_tpCalcResultMarginParams.setContractAmount3(1);                                 
//            l_tpCalcResultMarginParams.setContractAmount4(1);                                 
//            l_tpCalcResultMarginParams.setContractAmount5(1);                                 
//            l_tpCalcResultMarginParams.setDayRepayContractAmount0(1);                                 
//            l_tpCalcResultMarginParams.setDayRepayContractAmount1(1);                                 
//            l_tpCalcResultMarginParams.setDayRepayContractAmount2(1);                                 
//            l_tpCalcResultMarginParams.setDayRepayContractAmount3(1);                                 
//            l_tpCalcResultMarginParams.setDayRepayContractAmount4(1);                                 
//            l_tpCalcResultMarginParams.setDayRepayContractAmount5(1);                                 
//            l_tpCalcResultMarginParams.setMarginDeposit0(1);                                  
//            l_tpCalcResultMarginParams.setMarginDeposit1(1);                                  
//            l_tpCalcResultMarginParams.setMarginDeposit2(1);                                  
//            l_tpCalcResultMarginParams.setMarginDeposit3(1);                                  
//            l_tpCalcResultMarginParams.setMarginDeposit4(1);                                  
//            l_tpCalcResultMarginParams.setMarginDeposit5(1);                                  
//            l_tpCalcResultMarginParams.setCashMarginDeposit0(1);                                  
//            l_tpCalcResultMarginParams.setCashMarginDeposit1(1);                                  
//            l_tpCalcResultMarginParams.setCashMarginDeposit2(1);                                  
//            l_tpCalcResultMarginParams.setCashMarginDeposit3(1);                                  
//            l_tpCalcResultMarginParams.setCashMarginDeposit4(1);                                  
//            l_tpCalcResultMarginParams.setCashMarginDeposit5(1);                                  
//            l_tpCalcResultMarginParams.setContractAssetProfitLoss(1);                                 
//            l_tpCalcResultMarginParams.setUndeliContractAmount0(1);                                   
//            l_tpCalcResultMarginParams.setUndeliContractAmount1(1);                                   
//            l_tpCalcResultMarginParams.setUndeliContractAmount2(1);                                   
//            l_tpCalcResultMarginParams.setUndeliContractAmount3(1);                                   
//            l_tpCalcResultMarginParams.setUndeliMarginDeposit0(1);                                    
//            l_tpCalcResultMarginParams.setUndeliMarginDeposit1(1);                                    
//            l_tpCalcResultMarginParams.setUndeliMarginDeposit2(1);                                    
//            l_tpCalcResultMarginParams.setUndeliMarginDeposit3(1);                                    
//            l_tpCalcResultMarginParams.setUndeliCashMarginDeposit0(1);                                    
//            l_tpCalcResultMarginParams.setUndeliCashMarginDeposit1(1);                                    
//            l_tpCalcResultMarginParams.setUndeliCashMarginDeposit2(1);                                    
//            l_tpCalcResultMarginParams.setUndeliCashMarginDeposit3(1);                                    
//            l_tpCalcResultMarginParams.setUndeliContractLoss0(1);                                 
//            l_tpCalcResultMarginParams.setUndeliContractLoss1(1);                                 
//            l_tpCalcResultMarginParams.setUndeliContractLoss2(1);                                 
//            l_tpCalcResultMarginParams.setUndeliContractLoss3(1);                                 
//            l_tpCalcResultMarginParams.setUndeliContractProfit0(1);                                   
//            l_tpCalcResultMarginParams.setUndeliContractProfit1(1);                                   
//            l_tpCalcResultMarginParams.setUndeliContractProfit2(1);                                   
//            l_tpCalcResultMarginParams.setUndeliContractProfit3(1);                                   
//            l_tpCalcResultMarginParams.setContractTotalCost(1);                             
//            l_tpCalcResultMarginParams.setCreatedTimestamp(Calendar.getInstance().getTime());                                  
//            l_tpCalcResultMarginParams.setLastUpdatedTimestamp(Calendar.getInstance().getTime());  
//            l_tpCalcResultMarginParams.setMarkToMarketDiv(WEB3TPMarkToMarketDivDef.NORMAL);
//            TestDBUtility.insertWithDel(l_tpCalcResultMarginParams);
//            
//            TestDBUtility.deleteAll(ProductRow.TYPE);
//            ProductParams l_productParams = 
//                TestDBUtility.getProductRow();
//            l_productParams.setProductType(ProductTypeEnum.CASH);
//            l_productParams.setInstitutionCode("0D");
//            TestDBUtility.insertWithDel(l_productParams);
//            
//            
//            TestDBUtility.deleteAll(TpCalcResultMarginDetailParams.TYPE);
//            TpCalcResultMarginDetailParams l_tpCalcResultMarginDetailParams = new  TpCalcResultMarginDetailParams();
//            l_tpCalcResultMarginDetailParams.setCalcResultMarginId(1);                               
//            l_tpCalcResultMarginDetailParams.setAccountId(333812512246L); 
//            l_tpCalcResultMarginDetailParams.setCreatedTimestamp(Calendar.getInstance().getTime());                                  
//            l_tpCalcResultMarginDetailParams.setLastUpdatedTimestamp(Calendar.getInstance().getTime()); 
////            l_tpCalcResultMarginDetailParams.setmarktomarketdiv();
//            TestDBUtility.insertWithDel(l_tpCalcResultMarginDetailParams);
//            
//            
//            WEB3GentradeSubAccount l_subAccount = null;
//            try
//            {
//                l_subAccount = new WEB3GentradeSubAccount(333812512246L,10L);
//            }
//            catch (DataQueryException e1)
//            {
//                log.exiting(STR_METHOD_NAME);
//                fail();
//            }
//            catch (DataNetworkException e1)
//            {
//                log.exiting(STR_METHOD_NAME);
//                fail();
//            }
//            
//            double l_dbl = l_impl.getTransferAmountToDeposit(l_subAccount,l_datDeliveryDate,l_dblCashinAmt);
//            assertEquals("" + 2.0D,"" + l_dbl);
//        }
//        catch (WEB3BaseException e)
//        {
//            log.exiting(STR_METHOD_NAME);
//            log.debug("" + e.getErrorInfo());
//            fail();
//        }
//
//        log.exiting(STR_METHOD_NAME);
//    }
    
    /**
     * 
     *
     */
    public void test_getMarginPowerTransferAmount_0004()
    {
        final String STR_METHOD_NAME =
            "test_getMarginPowerTransferAmount_0004()";
        log.entering(STR_METHOD_NAME);
        
        WEB3TPTradingPowerServiceImplCForTest l_impl = new WEB3TPTradingPowerServiceImplCForTest();

        Date l_datDeliveryDate = Calendar.getInstance().getTime();
        double l_dblCashinAmt = 2.0D;
//        String l_strPassword = null;
        
        
        try
        {
            TestDBUtility.deleteAll(MainAccountRow.TYPE);
            MainAccountParams l_mainAccountParams = TestDBUtility.getMainAccountRow();
//            l_mainAccountParams.setMarginGenAccOpenDiv("1");
//            l_mainAccountParams.setMarginSysAccOpenDiv("1");
            TestDBUtility.insertWithDel(l_mainAccountParams);
            
            TestDBUtility.deleteAll(SubAccountRow.TYPE);
            SubAccountParams l_subAccountParams = TestDBUtility.getSubAccountRow();
            l_subAccountParams.setAccountId(333812512246L);
            l_subAccountParams.setSubAccountId(10);
            l_subAccountParams.setSubAccountType(SubAccountTypeEnum.EQUITY_SUB_ACCOUNT);
            
            TestDBUtility.insertWithDel(l_subAccountParams);
            
//            SubAccountParams l_subAccountParams1 = TestDBUtility.getSubAccountRow();
//            l_subAccountParams1.setAccountId(333812512246L);
//            l_subAccountParams1.setSubAccountId(20);
//            l_subAccountParams1.setSubAccountType(SubAccountTypeEnum.EQUITY_MARGIN_SUB_ACCOUNT);
//            TestDBUtility.insertWithDel(l_subAccountParams1);
            
            TestDBUtility.deleteAll(TradingpowerCalcConditionRow.TYPE);
            TradingpowerCalcConditionParams l_tradingpowerCalcConditionParams = 
                TestDBUtility.getTradingpowerCalcConditionRow();
            l_tradingpowerCalcConditionParams.setAccountId(333812512246L);
            TestDBUtility.insertWithDel(l_tradingpowerCalcConditionParams);
            
            TestDBUtility.deleteAll(InstitutionRow.TYPE);
            InstitutionParams l_institutionParams = 
                TestDBUtility.getInstitutionRow();
            l_institutionParams.setInstitutionId(33);
            TestDBUtility.insertWithDel(l_institutionParams);
            
            TestDBUtility.deleteAll(BranchRow.TYPE);
            BranchParams l_branchParams = 
                TestDBUtility.getBranchRow();
            l_branchParams.setBranchId(33381L);
            TestDBUtility.insertWithDel(l_branchParams);
            
            TestDBUtility.deleteAll(TpCalcResultMarginParams.TYPE);
            TpCalcResultMarginParams l_tpCalcResultMarginParams = new  TpCalcResultMarginParams();
            
            l_tpCalcResultMarginParams.setCalcResultMarginId(1);                               
            l_tpCalcResultMarginParams.setAccountId(333812512246L);                                 
            l_tpCalcResultMarginParams.setAccountBalance0(1);                                 
            l_tpCalcResultMarginParams.setAccountBalance1(1);                                 
            l_tpCalcResultMarginParams.setAccountBalance2(1);                                 
            l_tpCalcResultMarginParams.setAccountBalance3(1);                                 
            l_tpCalcResultMarginParams.setAccountBalance4(1);                             
            l_tpCalcResultMarginParams.setAccountBalance5(1);                                 
            l_tpCalcResultMarginParams.setTodayExecutedAmount0(1);                                    
            l_tpCalcResultMarginParams.setTodayExecutedAmount1(1);                                    
            l_tpCalcResultMarginParams.setTodayExecutedAmount2(1);                                    
            l_tpCalcResultMarginParams.setTodayExecutedAmount3(1);                                    
            l_tpCalcResultMarginParams.setTodayExecutedAmount4(1);                                    
            l_tpCalcResultMarginParams.setTodayExecutedAmount5(1);                                    
            l_tpCalcResultMarginParams.setTodayUnexecutedAmount0(1);                                  
            l_tpCalcResultMarginParams.setTodayUnexecutedAmount1(1);                                  
            l_tpCalcResultMarginParams.setTodayUnexecutedAmount2(1);                                  
            l_tpCalcResultMarginParams.setTodayUnexecutedAmount3(1);                                  
            l_tpCalcResultMarginParams.setTodayUnexecutedAmount4(1);                                  
            l_tpCalcResultMarginParams.setTodayUnexecutedAmount5(1);                                  
            l_tpCalcResultMarginParams.setDayTradeRestraint0(1);                                  
            l_tpCalcResultMarginParams.setDayTradeRestraint1(1);                                  
            l_tpCalcResultMarginParams.setDayTradeRestraint2(1);                                  
            l_tpCalcResultMarginParams.setDayTradeRestraint3(1);                                  
            l_tpCalcResultMarginParams.setDayTradeRestraint4(1);                                  
            l_tpCalcResultMarginParams.setOtherRestraint0(1);                                 
            l_tpCalcResultMarginParams.setOtherRestraint1(1);                                 
            l_tpCalcResultMarginParams.setOtherRestraint2(1);                                 
            l_tpCalcResultMarginParams.setOtherRestraint3(1);                                 
            l_tpCalcResultMarginParams.setOtherRestraint4(1);                                 
            l_tpCalcResultMarginParams.setOtherRestraint5(1);                                 
            l_tpCalcResultMarginParams.setSubstituteSecurityAsset0(1);                                    
            l_tpCalcResultMarginParams.setSubstituteSecurityAsset1(1);                                
            l_tpCalcResultMarginParams.setSubstituteSecurityAsset2(1);                                
            l_tpCalcResultMarginParams.setSubstituteSecurityAsset3(1);                                
            l_tpCalcResultMarginParams.setSubstituteSecurityAsset4(1);                                    
            l_tpCalcResultMarginParams.setSubstituteSecurityAsset5(1);                                    
            l_tpCalcResultMarginParams.setContractAmount0(1);                                 
            l_tpCalcResultMarginParams.setContractAmount1(1);                                 
            l_tpCalcResultMarginParams.setContractAmount2(1);                                 
            l_tpCalcResultMarginParams.setContractAmount3(1);                                 
            l_tpCalcResultMarginParams.setContractAmount4(1);                                 
            l_tpCalcResultMarginParams.setContractAmount5(1);                                 
            l_tpCalcResultMarginParams.setDayRepayContractAmount0(1);                                 
            l_tpCalcResultMarginParams.setDayRepayContractAmount1(1);                                 
            l_tpCalcResultMarginParams.setDayRepayContractAmount2(1);                                 
            l_tpCalcResultMarginParams.setDayRepayContractAmount3(1);                                 
            l_tpCalcResultMarginParams.setDayRepayContractAmount4(1);                                 
            l_tpCalcResultMarginParams.setDayRepayContractAmount5(1);                                 
            l_tpCalcResultMarginParams.setMarginDeposit0(1);                                  
            l_tpCalcResultMarginParams.setMarginDeposit1(1);                                  
            l_tpCalcResultMarginParams.setMarginDeposit2(1);                                  
            l_tpCalcResultMarginParams.setMarginDeposit3(1);                                  
            l_tpCalcResultMarginParams.setMarginDeposit4(1);                                  
            l_tpCalcResultMarginParams.setMarginDeposit5(1);                                  
            l_tpCalcResultMarginParams.setCashMarginDeposit0(1);                                  
            l_tpCalcResultMarginParams.setCashMarginDeposit1(1);                                  
            l_tpCalcResultMarginParams.setCashMarginDeposit2(1);                                  
            l_tpCalcResultMarginParams.setCashMarginDeposit3(1);                                  
            l_tpCalcResultMarginParams.setCashMarginDeposit4(1);                                  
            l_tpCalcResultMarginParams.setCashMarginDeposit5(1);                                  
            l_tpCalcResultMarginParams.setContractAssetProfitLoss(1);                                 
            l_tpCalcResultMarginParams.setUndeliContractAmount0(1);                                   
            l_tpCalcResultMarginParams.setUndeliContractAmount1(1);                                   
            l_tpCalcResultMarginParams.setUndeliContractAmount2(1);                                   
            l_tpCalcResultMarginParams.setUndeliContractAmount3(1);                                   
            l_tpCalcResultMarginParams.setUndeliMarginDeposit0(1);                                    
            l_tpCalcResultMarginParams.setUndeliMarginDeposit1(1);                                    
            l_tpCalcResultMarginParams.setUndeliMarginDeposit2(1);                                    
            l_tpCalcResultMarginParams.setUndeliMarginDeposit3(1);                                    
            l_tpCalcResultMarginParams.setUndeliCashMarginDeposit0(1);                                    
            l_tpCalcResultMarginParams.setUndeliCashMarginDeposit1(1);                                    
            l_tpCalcResultMarginParams.setUndeliCashMarginDeposit2(1);                                    
            l_tpCalcResultMarginParams.setUndeliCashMarginDeposit3(1);                                    
            l_tpCalcResultMarginParams.setUndeliContractLoss0(1);                                 
            l_tpCalcResultMarginParams.setUndeliContractLoss1(1);                                 
            l_tpCalcResultMarginParams.setUndeliContractLoss2(1);                                 
            l_tpCalcResultMarginParams.setUndeliContractLoss3(1);                                 
            l_tpCalcResultMarginParams.setUndeliContractProfit0(1);                                   
            l_tpCalcResultMarginParams.setUndeliContractProfit1(1);                                   
            l_tpCalcResultMarginParams.setUndeliContractProfit2(1);                                   
            l_tpCalcResultMarginParams.setUndeliContractProfit3(1);                                   
            l_tpCalcResultMarginParams.setContractTotalCost(1);                             
            l_tpCalcResultMarginParams.setCreatedTimestamp(Calendar.getInstance().getTime());                                  
            l_tpCalcResultMarginParams.setLastUpdatedTimestamp(Calendar.getInstance().getTime());  
            l_tpCalcResultMarginParams.setMarkToMarketDiv(WEB3TPMarkToMarketDivDef.NORMAL);
            TestDBUtility.insertWithDel(l_tpCalcResultMarginParams);
            
            TestDBUtility.deleteAll(ProductRow.TYPE);
            ProductParams l_productParams = 
                TestDBUtility.getProductRow();
            l_productParams.setProductType(ProductTypeEnum.CASH);
            l_productParams.setInstitutionCode("0D");
            TestDBUtility.insertWithDel(l_productParams);
            
            
            TestDBUtility.deleteAll(TpCalcResultMarginDetailParams.TYPE);
            TpCalcResultMarginDetailParams l_tpCalcResultMarginDetailParams = new  TpCalcResultMarginDetailParams();
            l_tpCalcResultMarginDetailParams.setCalcResultMarginId(1);                               
            l_tpCalcResultMarginDetailParams.setAccountId(333812512246L); 
            l_tpCalcResultMarginDetailParams.setCreatedTimestamp(Calendar.getInstance().getTime());                                  
            l_tpCalcResultMarginDetailParams.setLastUpdatedTimestamp(Calendar.getInstance().getTime()); 
//            l_tpCalcResultMarginDetailParams.setmarktomarketdiv();
            TestDBUtility.insertWithDel(l_tpCalcResultMarginDetailParams);
            
            
            WEB3GentradeSubAccount l_subAccount = null;
            try
            {
                l_subAccount = new WEB3GentradeSubAccount(333812512246L,10L);
            }
            catch (DataQueryException e1)
            {
                log.exiting(STR_METHOD_NAME);
                fail();
            }
            catch (DataNetworkException e1)
            {
                log.exiting(STR_METHOD_NAME);
                fail();
            }
            
            double l_dbl = l_impl.getTransferAmountToDeposit(l_subAccount,l_datDeliveryDate,l_dblCashinAmt);
            assertEquals("" + 0.0D,"" + l_dbl);
        }
        catch (WEB3BaseException e)
        {
            log.exiting(STR_METHOD_NAME);
            log.debug("" + e.getErrorInfo());
            fail();
        }

        log.exiting(STR_METHOD_NAME);
    }
    
    class WEB3TPTradingPowerServiceImplAForTest extends WEB3TPTradingPowerServiceImpl
    {
        protected double calcRealBalanceMargin(
                WEB3GentradeSubAccount l_subAccount,
                WEB3TPTradingPowerCalcMargin l_tpCalcMargin,
                Date l_datDeliveryDate)
       {
            return 30.123D;
       }
    }
    
    class WEB3TPTradingPowerServiceImplBForTest extends WEB3TPTradingPowerServiceImpl
    {
        protected double calcRealBalanceMargin(
                WEB3GentradeSubAccount l_subAccount,
                WEB3TPTradingPowerCalcMargin l_tpCalcMargin,
                Date l_datDeliveryDate)
       {
            return -30.123D;
       }
    }
    
    class WEB3TPTradingPowerServiceImplCForTest extends WEB3TPTradingPowerServiceImpl
    {
        protected double calcRealBalanceMargin(
                WEB3GentradeSubAccount l_subAccount,
                WEB3TPTradingPowerCalcMargin l_tpCalcMargin,
                Date l_datDeliveryDate)
       {
            return 2.0D;
       }
    }
}
@
