head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.04.07.01.46.33;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	5444d9d078c63f4;
filename	WEB3BondRecruitBuyServiceImplTest.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@package webbroker3.bd.service.delegate.stdimpls;

import java.util.Date;
import java.util.List;

import com.fitechlabs.xtrade.kernel.boot.Services;
import com.fitechlabs.xtrade.kernel.data.Processors;
import com.fitechlabs.xtrade.kernel.data.QueryProcessor;
import com.fitechlabs.xtrade.plugin.tc.gentrade.FinApp;
import com.fitechlabs.xtrade.plugin.tc.gentrade.FinObjectManager;
import com.fitechlabs.xtrade.plugin.tc.gentrade.NotFoundException;
import com.fitechlabs.xtrade.plugin.tc.gentrade.ProcessingResult;
import com.fitechlabs.xtrade.plugin.tc.gentrade.ProductTypeEnum;
import com.fitechlabs.xtrade.plugin.tc.gentrade.SubAccount;
import com.fitechlabs.xtrade.plugin.tc.gentrade.SubAccountTypeEnum;
import com.fitechlabs.xtrade.plugin.tc.gentrade.Trader;
import com.fitechlabs.xtrade.plugin.tc.gentrade.data.BranchParams;
import com.fitechlabs.xtrade.plugin.tc.gentrade.data.MainAccountParams;
import com.fitechlabs.xtrade.plugin.tc.gentrade.data.ProductParams;
import com.fitechlabs.xtrade.plugin.tc.gentrade.data.SubAccountParams;
import com.fitechlabs.xtrade.plugin.tc.gentrade.data.TraderParams;
import com.fitechlabs.xtrade.plugin.tc.gentrade.ordersubmitter.io.NewOrderSpec;
import com.fitechlabs.xtrade.plugin.tc.gentrade.ordersubmitter.io.OrderSubmissionResult;
import com.fitechlabs.xtrade.plugin.tc.xbbd.data.BondOrderParams;
import com.fitechlabs.xtrade.plugin.tc.xbbd.data.BondOrderUnitParams;
import com.fitechlabs.xtrade.plugin.tc.xbbd.data.BondProductParams;

import test.util.TestDBUtility;

import webbroker3.bd.WEB3BondProduct;
import webbroker3.bd.define.WEB3BondDealDivDef;
//import webbroker3.bd.define.WEB3BondOrderPriceDivDef;
import webbroker3.bd.message.WEB3BondApplyBuyCompleteRequest;
import webbroker3.common.WEB3BaseException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.common.WEB3SystemLayerException;
import webbroker3.common.define.WEB3SettlementDivDef;
import webbroker3.gentrade.WEB3GentradeAccountManager;
import webbroker3.gentrade.WEB3GentradeMainAccount;
import webbroker3.gentrade.WEB3GentradeTradingTimeManagementForMock;
import webbroker3.gentrade.define.WEB3GentradeRepaymentDivDef;
import webbroker3.mock.TestBaseForMock;
import webbroker3.util.WEB3DateUtility;
import webbroker3.util.WEB3LogUtility;

public class WEB3BondRecruitBuyServiceImplTest extends TestBaseForMock
{

    WEB3BondRecruitBuyServiceImplForTest l_impl = new WEB3BondRecruitBuyServiceImplForTest();
    WEB3LogUtility log = 
        WEB3LogUtility.getInstance(WEB3BondRecruitBuyServiceImplTest.class);
    public WEB3BondRecruitBuyServiceImplTest(String arg0)
    {
        super(arg0);
    }

    protected void setUp() throws Exception
    {
        super.setUp();
    }

    protected void tearDown() throws Exception
    {
        super.tearDown();
    }

//    public void testSubmitRecruitBuyOrder_T01()
//    {
//        final String STR_METHOD_NAME = "testSubmitRecruitBuyOrder_T01()";
//        log.entering(STR_METHOD_NAME);
//        try
//        {
//            MOCK_MANAGER.setIsMockUsed(true);
//            WEB3BondApplyBuyCompleteRequest l_request =
//                new WEB3BondApplyBuyCompleteRequest();
//            l_request.tradeDiv = WEB3BondDealDivDef.RECRUIT;//BUY
//            l_request.productId = "123456";
//            l_request.settleDiv = WEB3SettlementDivDef.JAPANESE_CURRENCY;
//            l_request.faceAmount = "123";
//            l_request.orderPriceDiv = WEB3BondOrderPriceDivDef.MARKET_PRICE;
//            l_request.buyPrice = "100";
//            l_request.deliveryDate = new Date(2010,6,15);
//            l_request.foreignDeliveryDate = new Date(2010,6,15);
//            
//            l_request.id = "123";
//            l_request.checkDate = new Date(2007,6,15);
//            l_request.introduceStoreDiv = null;
//            //l_request.orderAddInfoList = null;
//  
//            //ProductParams
//            TestDBUtility.deleteAll(ProductParams.TYPE);
//            ProductParams l_productParams = TestDBUtility.getProductRow();
//            l_productParams.setProductType(ProductTypeEnum.BOND);
//            l_productParams.setProductId(123456);
//            TestDBUtility.insertWithDel(l_productParams);
//
//            //BondProductParams
//            TestDBUtility.deleteAll(BondProductParams.TYPE);
//            BondProductParams l_bondProductParams = TestDBUtility.getBondProductRow();
//            l_bondProductParams.setProductId(123456);
//            TestDBUtility.insertWithDel(l_bondProductParams);
//            l_bondProductParams.setIssueDate(WEB3DateUtility.getDate("2010/01/01", "yyyy/MM/dd"));
//            
//            //WEB3GentradeTradingTimeManagement
//            WEB3GentradeTradingTimeManagementForMock.mockGetOrderBizDate(l_request.checkDate);
//            
//            //SubAccountParams
//            TestDBUtility.deleteAll(SubAccountParams.TYPE);
//            SubAccountParams l_subAccountParams = TestDBUtility.getSubAccountRow();
//            l_subAccountParams.setSubAccountType(SubAccountTypeEnum.EQUITY_SUB_ACCOUNT);
//            TestDBUtility.insertWithDel(l_subAccountParams);
//            
//            //MainAccountParams
//            TestDBUtility.deleteAll(MainAccountParams.TYPE);
//            MainAccountParams l_mainAccountParams =
//                TestDBUtility.getMainAccountRow();
//            l_mainAccountParams.setAccountId(l_subAccountParams.getAccountId());
//            TestDBUtility.insertWithDel(l_mainAccountParams);
//
//            //TraderParams
//            TestDBUtility.deleteAll(TraderParams.TYPE);
//            TraderParams l_traderParams =
//                TestDBUtility.getTraderRow();
//            TestDBUtility.insertWithDel(l_traderParams);
//            //BranchParams
//            TestDBUtility.deleteAll(BranchParams.TYPE);
//            BranchParams l_branchParams =
//                TestDBUtility.getBranchRow();
//            l_branchParams.setBranchId(l_subAccountParams.getBranchId());
//            TestDBUtility.insertWithDel(l_branchParams);
//
//            //formock
//            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
//                "webbroker3.bd.WEB3BondOrderManager",
//                "validateRecruitOrBuyOrder",
//                new Class[] {SubAccount.class, 
//                        WEB3BondProduct.class, 
//                        String.class, 
//                        String.class, 
//                        double.class},
//                        null);
//                
//            ProcessingResult processingResult = ProcessingResult.newSuccessResultInstance();
//            OrderSubmissionResult l_submitNewOrderResult =
//                new  OrderSubmissionResult(processingResult);
//            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
//                "webbroker3.bd.WEB3BondOrderManager",
//                "submitNewOrder",
//                new Class[] {SubAccount.class,
//                        ProductTypeEnum.class,
//                        NewOrderSpec.class,
//                        long.class,
//                        String.class,
//                        boolean.class},
//                l_submitNewOrderResult);
//            
////            //delete
////            TestDBUtility.deleteAll(OrderAdditionalInfoParams.TYPE);
////
////            l_impl.submitRecruitBuyOrder(l_request);
////            QueryProcessor l_processor = Processors.getDefaultProcessor();
////            List l_lisResult = l_processor.doFindAllQuery(OrderAdditionalInfoParams.TYPE);
////            assertEquals(0, l_lisResult.size());
////            log.exiting(STR_METHOD_NAME);
//            
//        }
//        catch(Exception l_exc)
//        {
//            l_exc.printStackTrace();
//            fail();
//        }
//    }
//
//    public void testSubmitRecruitBuyOrder_T02()
//    {
//        final String STR_METHOD_NAME = "testSubmitRecruitBuyOrder_T02()";
//        log.entering(STR_METHOD_NAME);
//        try
//        {
//            MOCK_MANAGER.setIsMockUsed(true);
//            WEB3BondApplyBuyCompleteRequest l_request =
//                new WEB3BondApplyBuyCompleteRequest();
//            l_request.tradeDiv = WEB3BondDealDivDef.RECRUIT;//BUY
//            l_request.productId = "123456";
//            l_request.settleDiv = WEB3SettlementDivDef.JAPANESE_CURRENCY;
//            l_request.faceAmount = "123";
//            l_request.orderPriceDiv = WEB3BondOrderPriceDivDef.MARKET_PRICE;
//            l_request.buyPrice = "100";
//            l_request.deliveryDate = new Date(2010,6,15);
//            l_request.foreignDeliveryDate = new Date(2010,6,15);
//            
//            l_request.id = "123";
//            l_request.checkDate = new Date(2007,6,15);
//            l_request.introduceStoreDiv = null;
//            
//            WEB3GentradeOrderAddInfoUnit[] orderAddInfoList = 
//                new WEB3GentradeOrderAddInfoUnit[3];
//            WEB3GentradeOrderAddInfoUnit l_infoUnit1 = 
//                new WEB3GentradeOrderAddInfoUnit();
//            l_infoUnit1.name = "jiddk123";
//            l_infoUnit1.value = "123";
//            WEB3GentradeOrderAddInfoUnit l_infoUnit2 = 
//                new WEB3GentradeOrderAddInfoUnit();
//            l_infoUnit2.name = "jiddk456";
//            l_infoUnit2.value = "456";
//            WEB3GentradeOrderAddInfoUnit l_infoUnit3 = 
//                new WEB3GentradeOrderAddInfoUnit();
//            l_infoUnit3.name = "jiddk789";
//            l_infoUnit3.value = "789";
//            orderAddInfoList[0] = l_infoUnit1;
//            orderAddInfoList[1] = l_infoUnit2;
//            orderAddInfoList[2] = l_infoUnit3;
//            l_request.orderAddInfoList = orderAddInfoList;
//  
//            //ProductParams
//            TestDBUtility.deleteAll(ProductParams.TYPE);
//            ProductParams l_productParams = TestDBUtility.getProductRow();
//            l_productParams.setProductType(ProductTypeEnum.BOND);
//            l_productParams.setProductId(123456);
//            TestDBUtility.insertWithDel(l_productParams);
//
//            //BondProductParams
//            TestDBUtility.deleteAll(BondProductParams.TYPE);
//            BondProductParams l_bondProductParams = TestDBUtility.getBondProductRow();
//            l_bondProductParams.setProductId(123456);
//            TestDBUtility.insertWithDel(l_bondProductParams);
//            l_bondProductParams.setIssueDate(WEB3DateUtility.getDate("2010/01/01", "yyyy/MM/dd"));
//            
//            //WEB3GentradeTradingTimeManagement
//            WEB3GentradeTradingTimeManagementForMock.mockGetOrderBizDate(l_request.checkDate);
//            
//            //SubAccountParams
//            TestDBUtility.deleteAll(SubAccountParams.TYPE);
//            SubAccountParams l_subAccountParams = TestDBUtility.getSubAccountRow();
//            l_subAccountParams.setSubAccountType(SubAccountTypeEnum.EQUITY_SUB_ACCOUNT);
//            TestDBUtility.insertWithDel(l_subAccountParams);
//            
//            //MainAccountParams
//            TestDBUtility.deleteAll(MainAccountParams.TYPE);
//            MainAccountParams l_mainAccountParams =
//                TestDBUtility.getMainAccountRow();
//            l_mainAccountParams.setAccountId(l_subAccountParams.getAccountId());
//            TestDBUtility.insertWithDel(l_mainAccountParams);
//
//            //TraderParams
//            TestDBUtility.deleteAll(TraderParams.TYPE);
//            TraderParams l_traderParams =
//                TestDBUtility.getTraderRow();
//            TestDBUtility.insertWithDel(l_traderParams);
//            
//            //BondOrderParams
//            TestDBUtility.deleteAll(BondOrderParams.TYPE);
//            BondOrderParams l_bondOrderParams = new BondOrderParams();
//            l_bondOrderParams.setOrderId(123);
//            l_bondOrderParams.setAccountId(333812512203L);
//            l_bondOrderParams.setSubAccountId(33381251220301L);
//            l_bondOrderParams.setCreatedTimestamp(WEB3DateUtility.getDate("20070408", "yyyyMMdd"));
//            l_bondOrderParams.setLastUpdatedTimestamp(WEB3DateUtility.getDate("20070408", "yyyyMMdd"));
//            l_bondOrderParams.setProductType(ProductTypeEnum.BOND);
//            TestDBUtility.insertWithDel(l_bondOrderParams);
//            
//            //BondOrderUnitParams
//            TestDBUtility.deleteAll(BondOrderUnitParams.TYPE);
//            BondOrderUnitParams l_bondOrderUnitParams =
//                TestDBUtility.getBondOrderUnitRow();
//            l_bondOrderUnitParams.setOrderId(123);
//            l_bondOrderUnitParams.setOrderRequestNumber("1");
//            TestDBUtility.insertWithDel(l_bondOrderUnitParams);
//            
//            //BranchParams
//            TestDBUtility.deleteAll(BranchParams.TYPE);
//            BranchParams l_branchParams =
//                TestDBUtility.getBranchRow();
//            l_branchParams.setBranchId(l_subAccountParams.getBranchId());
//            TestDBUtility.insertWithDel(l_branchParams);
//
//
//            //formock
//            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
//                "webbroker3.bd.WEB3BondOrderManager",
//                "validateRecruitOrBuyOrder",
//                new Class[] {SubAccount.class, 
//                        WEB3BondProduct.class, 
//                        String.class, 
//                        String.class, 
//                        double.class},
//                        null);
//                
//            ProcessingResult processingResult = ProcessingResult.newSuccessResultInstance();
//            OrderSubmissionResult l_submitNewOrderResult =
//                new  OrderSubmissionResult(processingResult);
//            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
//                "webbroker3.bd.WEB3BondOrderManager",
//                "submitNewOrder",
//                new Class[] {SubAccount.class,
//                        ProductTypeEnum.class,
//                        NewOrderSpec.class,
//                        long.class,
//                        String.class,
//                        boolean.class},
//                l_submitNewOrderResult);
//            
//            //delete
//            TestDBUtility.deleteAll(OrderAdditionalInfoParams.TYPE);
//
//            l_impl.submitRecruitBuyOrder(l_request);
//            QueryProcessor l_processor = Processors.getDefaultProcessor();
//            List l_lisResult = l_processor.doFindAllQuery(OrderAdditionalInfoParams.TYPE);
//            assertEquals(3, l_lisResult.size());
//            log.exiting(STR_METHOD_NAME);
//            
//        }
//        catch(Exception l_exc)
//        {
//            l_exc.printStackTrace();
//            fail();
//        }
//    }
//    
    
    public void testImpl()
    {
        
    }
    public class WEB3BondRecruitBuyServiceImplForTest extends WEB3BondRecruitBuyServiceImpl
    {
        /**
         * (get補助口座)<BR>
         * （getSubAccountのオーバーロード）  <BR>
         * <BR>
         * ログインセキュリティサービスより補助口座を取得する。  <BR>
         * <BR>
         * １）　@ログインセキュリティサービスより口座ＩＤを取得し、該当する顧客オブジェクト
         * を取得する。 <BR>
         * <BR>
         * ２）　@拡張アカウントマネージャ.getSubAccount()にて、該当顧客の補助口座オブジェク
         * トを取得する。 <BR>
         * <BR>
         * 　@[getSubAccount()にセットするパラメータ] <BR>
         * 　@　@補助口座タイプ：　@<BR>
         * 　@　@　@[顧客オブジェクト.is信用口座開設(”指定なし”) == trueの場合]<BR>
         * 　@　@　@　@SubAccountTypeEnum.株式信用取引口座<BR>
         * 　@　@　@[上記以外の場合<BR>
         * 　@　@　@　@SubAccountTypeEnum.株式取引口座<BR>
         * @@return 補助口座
         * @@roseuid 421947A80030
         */
        public SubAccount getSubAccount() throws WEB3BaseException 
        {
            final String STR_METHOD_NAME = "getSubAccount()";
            //１）ログインセキュリティサービスより口座ＩＤを取得する。
            
            FinApp l_finApp = (FinApp)Services.getService(FinApp.class);
            WEB3GentradeAccountManager l_accountManager = (WEB3GentradeAccountManager)l_finApp.getAccountManager();
            SubAccount  l_subAccount = null;

            long l_lngAccountId = 333812512203L;
            try
            {
                SubAccountTypeEnum l_subAccountType = null;
                WEB3GentradeMainAccount l_mainAccount = (WEB3GentradeMainAccount)l_accountManager.getMainAccount(l_lngAccountId);
                //[顧客オブジェクト.is信用口座開設(”指定なし”) == trueの場合]SubAccountTypeEnum.株式信用取引口座
                if (l_mainAccount.isMarginAccountEstablished(WEB3GentradeRepaymentDivDef.DEFAULT))
                {
                    l_subAccountType = SubAccountTypeEnum.EQUITY_MARGIN_SUB_ACCOUNT;
                }
                //上記以外の場合SubAccountTypeEnum.株式取引口座
                else
                {
                    l_subAccountType =SubAccountTypeEnum.EQUITY_SUB_ACCOUNT;
                }
                //２）　@拡張アカウントマネージャ.getSubAccount()にて、該当顧客の補助口座オブジェクトを取得する。
                l_subAccount = l_accountManager.getSubAccount(l_lngAccountId,l_subAccountType);
            }
            catch (NotFoundException l_nfe)
            {
                throw new WEB3SystemLayerException(
                    WEB3ErrorCatalog.SYSTEM_ERROR_80006,
                    this.getClass().getName()+  "." + STR_METHOD_NAME);
            }

            return l_subAccount;
        }
        
        
        public Trader getTrader() throws WEB3SystemLayerException
        {
            final String STR_METHOD_NAME = "getTrader()";

            Trader l_trader = null;

            long l_loginId = 3338111123L;
            FinApp l_finApp = (FinApp) Services.getService(FinApp.class);
            FinObjectManager l_finObjMgr = l_finApp.getFinObjectManager();

            try
            {
                l_trader = l_finObjMgr.getTraderByLoginId(l_loginId);
            }
            catch (NotFoundException e)
            {
                return null;
            }

            return l_trader;
        }
    }

}
@
