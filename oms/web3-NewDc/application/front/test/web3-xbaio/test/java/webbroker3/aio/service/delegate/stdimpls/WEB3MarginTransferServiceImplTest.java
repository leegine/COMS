head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.04.07.01.38.39;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	5444d9d078c63f4;
filename	WEB3MarginTransferServiceImplTest.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : 保証金への振替サービスImpl
Author Name      : Daiwa Institute of Research
Revesion History : 2007/07/16　@孟亜南(中訊) 
*/
package webbroker3.aio.service.delegate.stdimpls;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import com.fitechlabs.xtrade.kernel.data.DataFindException;
import com.fitechlabs.xtrade.kernel.data.DataNetworkException;
import com.fitechlabs.xtrade.kernel.data.DataQueryException;
import com.fitechlabs.xtrade.kernel.data.Processors;
import com.fitechlabs.xtrade.kernel.data.QueryProcessor;
import com.fitechlabs.xtrade.plugin.security.oplogin.impl.LoginInfoImplForMock;
import com.fitechlabs.xtrade.plugin.tc.gentrade.Branch;
import com.fitechlabs.xtrade.plugin.tc.gentrade.Institution;
import com.fitechlabs.xtrade.plugin.tc.gentrade.PersonNameDetails;
import com.fitechlabs.xtrade.plugin.tc.gentrade.ProductTypeEnum;
import com.fitechlabs.xtrade.plugin.tc.gentrade.SubAccountTypeEnum;
import com.fitechlabs.xtrade.plugin.tc.gentrade.Trader;
import com.fitechlabs.xtrade.plugin.tc.gentrade.TraderTypeEnum;
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
import com.fitechlabs.xtrade.plugin.tc.xbaio.data.AioOrderActionRow;
import com.fitechlabs.xtrade.plugin.tc.xbaio.data.AioOrderUnitRow;

import test.util.TestDBUtility;

import webbroker3.aio.data.HostTransferOrderRow;
import webbroker3.common.WEB3BaseException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.common.WEB3SystemLayerException;
import webbroker3.gentrade.WEB3GentradeMainAccount;
import webbroker3.gentrade.WEB3GentradeSubAccount;
import webbroker3.gentrade.data.TradingpowerCalcConditionParams;
import webbroker3.gentrade.data.TradingpowerCalcConditionRow;
import webbroker3.mock.TestBaseForMock;
import webbroker3.tradingpower.WEB3TPTradingPowerCalcMargin;
import webbroker3.tradingpower.WEB3TPTradingPowerServiceImpl;
import webbroker3.tradingpower.data.TpCalcResultMarginDetailParams;
import webbroker3.tradingpower.data.TpCalcResultMarginParams;
import webbroker3.tradingpower.define.WEB3TPMarkToMarketDivDef;
import webbroker3.util.WEB3LogUtility;


public class WEB3MarginTransferServiceImplTest extends TestBaseForMock
{

    public WEB3MarginTransferServiceImplTest(String name)
    {
        super(name);
    }

    protected void setUp() throws Exception
    {
        
        super.setUp();
        TestDBUtility.deleteAll(HostTransferOrderRow.TYPE);
    }

    protected void tearDown() throws Exception
    {
        super.tearDown();
    }
    
    private static WEB3LogUtility log =
        WEB3LogUtility.getInstance(WEB3MarginTransferServiceImplTest.class);
    
    /**
     * get保証金への振替額 > 0
     *
     */
    public void test_submitMarginTransfer_0001()
    {
        final String STR_METHOD_NAME =
            "test_submitMarginTransfer_0001()";
        log.entering(STR_METHOD_NAME);
        
        TestBaseForMock.MOCK_MANAGER.setIsMockUsed(true);
        
        WEB3MarginTransferServiceImpl l_impl = new WEB3MarginTransferServiceImpl();

        Date l_datDeliveryDate = Calendar.getInstance().getTime();
        double l_dblCashinAmt = 81.2D;
        String l_strPassword = null;
        
        
        try
        {

            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                    "com.fitechlabs.xtrade.plugin.security.oplogin.impl.OpLoginSecurityServiceImpl",
                    "getLoginInfo",
                    new Class[] {},
                    new LoginInfoImplForMock());
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                    "com.fitechlabs.xtrade.plugin.security.oplogin.impl.OpLoginSecurityServiceImpl",
                    "getSessionProperty",
                    new Class[] {String.class},
                    "1");
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                    "webbroker3.tradingpower.WEB3TPTradingPowerServiceImpl", "getTransferAmountToDeposit", new Class[]
                    { WEB3GentradeSubAccount.class, Date.class, double.class },
                    new Double(12345));
                    
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                    "webbroker3.gentrade.WEB3HostReqOrderNumberManageServiceImpl", "getNewNumber", new Class[]
                    { String.class, String.class, ProductTypeEnum.class },
                    "22");
            
            TestDBUtility.deleteAll(MainAccountRow.TYPE);
            MainAccountParams l_mainAccountParams = TestDBUtility.getMainAccountRow();
            l_mainAccountParams.setMarginGenAccOpenDiv("1");
            l_mainAccountParams.setMarginSysAccOpenDiv("1");
            TestDBUtility.insertWithDel(l_mainAccountParams);
            
            TestDBUtility.deleteAll(SubAccountRow.TYPE);
            SubAccountParams l_subAccountParams = TestDBUtility.getSubAccountRow();
            l_subAccountParams.setAccountId(333812512246L);
            l_subAccountParams.setSubAccountId(10);
            l_subAccountParams.setSubAccountType(SubAccountTypeEnum.EQUITY_SUB_ACCOUNT);
            
            TestDBUtility.insertWithDel(l_subAccountParams);
            
            SubAccountParams l_subAccountParams1 = TestDBUtility.getSubAccountRow();
            l_subAccountParams1.setAccountId(333812512246L);
            l_subAccountParams1.setSubAccountId(20);
            l_subAccountParams1.setSubAccountType(SubAccountTypeEnum.EQUITY_MARGIN_SUB_ACCOUNT);
            TestDBUtility.insertWithDel(l_subAccountParams1);
            
            TestDBUtility.deleteAll(TradingpowerCalcConditionRow.TYPE);
            TradingpowerCalcConditionParams l_tradingpowerCalcConditionParams = 
                TestDBUtility.getTradingpowerCalcConditionRow();
            l_tradingpowerCalcConditionParams.setAccountId(333812512246L);
//            l_tradingpowerCalcConditionParams.setAdditionalDepositStop("0");
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
            
            WEB3GentradeMainAccount l_mainAccount = null;
            try
            {
                l_mainAccount = new WEB3GentradeMainAccount(33L,33381L,"2512246");
                
//                Services.unregisterService(
//                        WEB3TPTradingPowerService.class);
//                
//                Services.registerService(
//                    WEB3TPTradingPowerService.class,
//                    new WEB3TPTradingPowerServiceImplForTest());
//                    log.debug("WEB3TPTradingPowerService registered.");
                
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
            
            l_impl.submitMarginTransfer(l_mainAccount, l_datDeliveryDate, l_dblCashinAmt, l_strPassword,new MyTraderImplForTest());
            
        }
        catch (WEB3BaseException e)
        {
            log.exiting(STR_METHOD_NAME);
            log.debug("" + e.getErrorInfo());
            fail();
        }
        
        //AioOrderUnitRow sub_account_id = 10
        String l_strWhere1 = " account_id = ? ";
        l_strWhere1 += " and sub_account_id = ? ";
        
        ArrayList l_lisConditions1 = new ArrayList();
        l_lisConditions1.add("333812512246");
        l_lisConditions1.add("10");
        
        
        String[] l_strConditions1 = new String[l_lisConditions1.size()];
        l_lisConditions1.toArray(l_strConditions1);
        List l_lisReturns1 = null;
        try
        {
            QueryProcessor l_queryProcessor = Processors.getDefaultProcessor();
            l_lisReturns1 = l_queryProcessor.doFindAllQuery(
                AioOrderUnitRow.TYPE,
                l_strWhere1,
                l_strConditions1);
        }
        catch (DataFindException l_ex)
        {
            log.error("テーブルに該当するデータがありません。", l_ex);
            log.exiting(STR_METHOD_NAME);
            fail();
        }
        catch (DataQueryException l_ex)
        {
            log.error("テーブルに該当するデータがありません。", l_ex);
            log.exiting(STR_METHOD_NAME);
            fail();
        }
        catch (DataNetworkException l_ex)
        {
            log.error("DBへのアクセスに失敗しました。", l_ex);
            log.exiting(STR_METHOD_NAME);
            fail();
        }
        
//        assertEquals("1","" + l_lisReturns1.size());
//        assertEquals("10","" + ((AioOrderUnitRow)l_lisReturns1.get(0)).getSubAccountId());
        /***********************************************************************/
        //AioOrderUnitRow sub_account_id = 20
        String l_strWhere2 = " account_id = ? ";
        l_strWhere2 += " and sub_account_id = ? ";
        
        ArrayList l_lisConditions2 = new ArrayList();
        l_lisConditions2.add("333812512246");
        l_lisConditions2.add("20");
        
        
        String[] l_strConditions2 = new String[l_lisConditions2.size()];
        l_lisConditions2.toArray(l_strConditions2);
        List l_lisReturns2 = null;
        try
        {
            QueryProcessor l_queryProcessor = Processors.getDefaultProcessor();
            l_lisReturns2 = l_queryProcessor.doFindAllQuery(
                AioOrderUnitRow.TYPE,
                l_strWhere2,
                l_strConditions2);
        }
        catch (DataFindException l_ex)
        {
            log.error(l_ex.getMessage(), l_ex);
            log.exiting(STR_METHOD_NAME);
            fail();
        }
        catch (DataQueryException l_ex)
        {
            log.error(l_ex.getMessage(), l_ex);
            log.exiting(STR_METHOD_NAME);
            fail();
        }
        catch (DataNetworkException l_ex)
        {
            log.error(l_ex.getMessage(), l_ex);
            log.exiting(STR_METHOD_NAME);
            fail();
        }
        
//        assertEquals("1","" + l_lisReturns2.size());
//        assertEquals("20","" + ((AioOrderUnitRow)l_lisReturns2.get(0)).getSubAccountId());
        /***********************************************************************************/
        //AioOrderActionRow sub_account_id = 10
        String l_strWhere11 = " account_id = ? ";
        l_strWhere11 += " and sub_account_id = ? ";
        
        ArrayList l_lisConditions11 = new ArrayList();
        l_lisConditions11.add("333812512246");
        l_lisConditions11.add("10");
        
        
        String[] l_strConditions11 = new String[l_lisConditions11.size()];
        l_lisConditions11.toArray(l_strConditions11);
        List l_lisReturns11 = null;
        try
        {
            QueryProcessor l_queryProcessor = Processors.getDefaultProcessor();
            l_lisReturns11 = l_queryProcessor.doFindAllQuery(
                AioOrderActionRow.TYPE,
                l_strWhere11,
                l_strConditions11);
        }
        catch (DataFindException l_ex)
        {
            log.error("テーブルに該当するデータがありません。", l_ex);
            log.exiting(STR_METHOD_NAME);
            fail();
        }
        catch (DataQueryException l_ex)
        {
            log.error("テーブルに該当するデータがありません。", l_ex);
            log.exiting(STR_METHOD_NAME);
            fail();
        }
        catch (DataNetworkException l_ex)
        {
            log.error("DBへのアクセスに失敗しました。", l_ex);
            log.exiting(STR_METHOD_NAME);
            fail();
        }
        
//        assertEquals("1","" + l_lisReturns11.size());
//        assertEquals("10","" + ((AioOrderActionRow)l_lisReturns11.get(0)).getSubAccountId());
        /***********************************************************************/
        //AioOrderActionRow sub_account_id = 20
        String l_strWhere22 = " account_id = ? ";
        l_strWhere22 += " and sub_account_id = ? ";
        
        ArrayList l_lisConditions22 = new ArrayList();
        l_lisConditions22.add("333812512246");
        l_lisConditions22.add("20");
        
        
        String[] l_strConditions22 = new String[l_lisConditions22.size()];
        l_lisConditions22.toArray(l_strConditions22);
        List l_lisReturns22 = null;
        try
        {
            QueryProcessor l_queryProcessor = Processors.getDefaultProcessor();
            l_lisReturns22 = l_queryProcessor.doFindAllQuery(
                AioOrderActionRow.TYPE,
                l_strWhere22,
                l_strConditions22);
        }
        catch (DataFindException l_ex)
        {
            log.error("テーブルに該当するデータがありません。", l_ex);
            log.exiting(STR_METHOD_NAME);
            fail();
        }
        catch (DataQueryException l_ex)
        {
            log.error("テーブルに該当するデータがありません。", l_ex);
            log.exiting(STR_METHOD_NAME);
            fail();
        }
        catch (DataNetworkException l_ex)
        {
            log.error("DBへのアクセスに失敗しました。", l_ex);
            log.exiting(STR_METHOD_NAME);
            fail();
        }
        
//        assertEquals("1","" + l_lisReturns22.size());
//        assertEquals("20","" + ((AioOrderActionRow)l_lisReturns22.get(0)).getSubAccountId());
        /***********************************************************************************/
        
//        Services.unregisterService(
//                WEB3TPTradingPowerService.class);
//        
//        Services.registerService(
//                WEB3TPTradingPowerService.class,
//                new WEB3TPTradingPowerServiceImpl());
//                log.debug("WEB3TPTradingPowerService registered.");

        log.exiting(STR_METHOD_NAME);
    }
    
    class WEB3TPTradingPowerServiceImplForTest extends WEB3TPTradingPowerServiceImpl
    {
        protected double calcRealBalanceMargin(
                WEB3GentradeSubAccount l_subAccount,
                WEB3TPTradingPowerCalcMargin l_tpCalcMargin,
                Date l_datDeliveryDate)
       {
            return 30.1D;
       }
    }
    
    /**
     * get保証金への振替額 > 0
     *
     */
    public void test_submitMarginTransfer_0002()
    {
        final String STR_METHOD_NAME =
            "test_submitMarginTransfer_0002()";
        log.entering(STR_METHOD_NAME);
        
        TestBaseForMock.MOCK_MANAGER.setIsMockUsed(true);
        
        WEB3MarginTransferServiceImpl l_impl = new WEB3MarginTransferServiceImpl();

        Date l_datDeliveryDate = Calendar.getInstance().getTime();
        double l_dblCashinAmt = 81.2D;
        String l_strPassword = null;
        
        
        try
        {
            
            WEB3GentradeMainAccount l_mainAccount = null;

            l_impl.submitMarginTransfer(l_mainAccount, l_datDeliveryDate, l_dblCashinAmt, l_strPassword,new MyTraderImplForTest());
            fail();
            
        }
        catch (WEB3SystemLayerException e)
        {
            log.exiting(STR_METHOD_NAME);
            log.debug("" + e.getErrorInfo());
            assertEquals(WEB3ErrorCatalog.SYSTEM_ERROR_80017,e.getErrorInfo());
        }
        catch (WEB3BaseException e)
        {
            log.exiting(STR_METHOD_NAME);
            log.debug("" + e.getErrorInfo());
            fail();
        }
        
        log.exiting(STR_METHOD_NAME);
    }
    
    private class MyTraderImplForTest implements Trader
    {

        public long getTraderId()
        {
            // TODO Auto-generated method stub
            return 0;
        }

        public String getTraderCode()
        {
            // TODO Auto-generated method stub
            return null;
        }

        public long getLoginId()
        {
            // TODO Auto-generated method stub
            return 0;
        }

        public TraderTypeEnum getTraderType()
        {
            // TODO Auto-generated method stub
            return null;
        }

        public PersonNameDetails getNameDetails()
        {
            // TODO Auto-generated method stub
            return null;
        }

        public PersonNameDetails getAlt1NameDetails()
        {
            // TODO Auto-generated method stub
            return null;
        }

        public PersonNameDetails getAlt2NameDetails()
        {
            // TODO Auto-generated method stub
            return null;
        }

        public Branch getBranch()
        {
            // TODO Auto-generated method stub
            return null;
        }

        public Institution getInstitution()
        {
            // TODO Auto-generated method stub
            return null;
        }

        public Object getDataSourceObject()
        {
            // TODO Auto-generated method stub
            return null;
        }
        
    }

}
@
