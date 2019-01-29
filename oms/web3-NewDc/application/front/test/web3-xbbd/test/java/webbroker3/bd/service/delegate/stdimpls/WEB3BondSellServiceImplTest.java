head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.04.07.01.46.47;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	5444d9d078c63f4;
filename	WEB3BondSellServiceImplTest.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (äî)ëÂòaëçå§ èÿåîÉ\ÉäÉÖÅ[ÉVÉáÉìÉVÉXÉeÉÄëÊìÒïî
File Name        : //TODO(WEB3BondSellServiceImplTest.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2007/05/09 âΩï∂ïq(íÜêu) êVãKçÏê¨
Revesion History : 2007/07/05  ÅyWEB3ÅzÅyCITIÉtÉçÉìÉgì±ì¸Åiç¬åîÅjÅzàƒåèéÊè¡ÅCíçù{ë„·˘
*/
package webbroker3.bd.service.delegate.stdimpls;

import java.math.BigDecimal;
import java.util.Calendar;
import java.util.Date;

import com.fitechlabs.xtrade.plugin.security.oplogin.LoginInfo;
import com.fitechlabs.xtrade.plugin.security.oplogin.impl.LoginInfoImpl;
import com.fitechlabs.xtrade.plugin.tc.gentrade.GtlUtils;
import com.fitechlabs.xtrade.plugin.tc.gentrade.ProcessingResult;
import com.fitechlabs.xtrade.plugin.tc.gentrade.ProductTypeEnum;
import com.fitechlabs.xtrade.plugin.tc.gentrade.SubAccount;
import com.fitechlabs.xtrade.plugin.tc.gentrade.SubAccountTypeEnum;
import com.fitechlabs.xtrade.plugin.tc.gentrade.data.AssetParams;
import com.fitechlabs.xtrade.plugin.tc.gentrade.data.AssetRow;
import com.fitechlabs.xtrade.plugin.tc.gentrade.data.BranchParams;
import com.fitechlabs.xtrade.plugin.tc.gentrade.data.BranchRow;
import com.fitechlabs.xtrade.plugin.tc.gentrade.data.MainAccountParams;
import com.fitechlabs.xtrade.plugin.tc.gentrade.data.MainAccountRow;
import com.fitechlabs.xtrade.plugin.tc.gentrade.data.ProductParams;
import com.fitechlabs.xtrade.plugin.tc.gentrade.data.ProductRow;
import com.fitechlabs.xtrade.plugin.tc.gentrade.data.SubAccountParams;
import com.fitechlabs.xtrade.plugin.tc.gentrade.data.SubAccountRow;
import com.fitechlabs.xtrade.plugin.tc.gentrade.data.TraderParams;
import com.fitechlabs.xtrade.plugin.tc.gentrade.data.TraderRow;
import com.fitechlabs.xtrade.plugin.tc.gentrade.ordersubmitter.io.NewOrderSpec;
import com.fitechlabs.xtrade.plugin.tc.gentrade.ordersubmitter.io.OrderSubmissionResult;
import com.fitechlabs.xtrade.plugin.tc.xbbd.data.BondOrderParams;
import com.fitechlabs.xtrade.plugin.tc.xbbd.data.BondOrderRow;
import com.fitechlabs.xtrade.plugin.tc.xbbd.data.BondOrderUnitParams;
import com.fitechlabs.xtrade.plugin.tc.xbbd.data.BondOrderUnitRow;
import com.fitechlabs.xtrade.plugin.tc.xbbd.data.BondProductParams;
import com.fitechlabs.xtrade.plugin.tc.xbbd.data.BondProductRow;

import test.util.TestDBUtility;

import webbroker3.bd.WEB3BondEstimatedPriceCalcResult;
import webbroker3.bd.WEB3BondExecuteDateInfo;
import webbroker3.bd.WEB3BondNewOrderSpec;
import webbroker3.bd.WEB3BondOrderTypeJudge;
import webbroker3.bd.WEB3BondProduct;
import webbroker3.bd.message.WEB3BondSellCompleteRequest;
import webbroker3.bd.message.WEB3BondSellCompleteResponse;
import webbroker3.bd.message.WEB3BondSellConfirmRequest;
import webbroker3.bd.message.WEB3BondSellConfirmResponse;
import webbroker3.gentrade.WEB3GentradeTradingTimeManagementForMock;
import webbroker3.mock.TestBaseForMock;
import webbroker3.mock.util.WEB3MockObjectParamsValue;
import webbroker3.util.WEB3DateUtility;
import webbroker3.util.WEB3LogUtility;

/**
 * @@author âΩï∂ïq(íÜêu)
 * @@version 1.0
 *
 */
public class WEB3BondSellServiceImplTest extends TestBaseForMock
{
    private static WEB3LogUtility log =
        WEB3LogUtility.getInstance(WEB3BondSellServiceImplTest.class);
    
    WEB3BondSellServiceImpl l_mpl = new WEB3BondSellServiceImpl();
    
    public WEB3BondSellServiceImplTest(String arg0)
    {
        super(arg0);
    }

    protected void setUp() throws Exception
    {
        super.setUp();
        MOCK_MANAGER.setIsMockUsed(true);
    }

    protected void tearDown() throws Exception
    {
        super.tearDown();
    }
    
//    public void testValidateSellOrder_case0001()
//    {
//        final String STR_METHOD_NAME = "testValidateSellOrder_case0001()";
//        log.entering(STR_METHOD_NAME);
//        
//        try
//        {
//            TestDBUtility.deleteAll(ProductRow.TYPE);
//            ProductParams l_productParams =
//                TestDBUtility.getProductRow();
//            l_productParams.setProductId(1006169090018L);
//            TestDBUtility.insertWithDel(l_productParams);
//
//            TestDBUtility.deleteAll(BondProductRow.TYPE);
//            BondProductParams l_bondProductParams =
//                TestDBUtility.getBondProductRow();
//            l_bondProductParams.setProductId(1006169090018L);
//             TestDBUtility.insertWithDel(l_bondProductParams);
//
//            TestDBUtility.deleteAll(MainAccountRow.TYPE);
//            MainAccountParams l_mainAccountParams =TestDBUtility.getMainAccountRow();
//            l_mainAccountParams.setAccountId(333812512203L);
//            TestDBUtility.insertWithDel(l_mainAccountParams);
//
//            TestDBUtility.deleteAll(SubAccountRow.TYPE);
//            SubAccountParams l_subAccountParams =TestDBUtility.getSubAccountRow();
//            l_subAccountParams.setAccountId(333812512203L);
//            l_subAccountParams.setSubAccountType(SubAccountTypeEnum.EQUITY_SUB_ACCOUNT);
//            TestDBUtility.insertWithDel(l_subAccountParams);
//
//            TestDBUtility.deleteAll(BranchRow.TYPE);
//            BranchParams l_branchParams = TestDBUtility.getBranchRow();
//            l_branchParams.setBranchId(33381L);
//            TestDBUtility.insertWithDel(l_branchParams);
//            
//            TestDBUtility.deleteAll(AssetRow.TYPE);
//            AssetParams l_assetParams = TestDBUtility.getAssetRow();
//            l_assetParams.setProductType(ProductTypeEnum.BOND);
//            TestDBUtility.insertWithDel(l_assetParams);
//            
//            TestDBUtility.deleteAll(TraderRow.TYPE);
//            TraderParams l_traderParams = TestDBUtility.getTraderRow();
//            TestDBUtility.insertWithDel(l_traderParams);
////            super.commit();
//            
//            Date l_datOrderBizDate = WEB3DateUtility.getDate("20070514", "yyyyMMdd");
////          WEB3GentradeTradingTimeManagementForMock.getClendarContext();
//          WEB3GentradeTradingTimeManagementForMock.mockGetOrderBizDate(l_datOrderBizDate);
//            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
//            "com.fitechlabs.xtrade.plugin.security.oplogin.impl.OpLoginSecurityServiceImpl",
//            "getAccountId",
//            new Class[] {}, new Long(333812512203L));
//            
//            LoginInfo l_loginInfo = new LoginInfoImpl();
//            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
//                "com.fitechlabs.xtrade.plugin.security.oplogin.impl.OpLoginSecurityServiceImpl",
//                "getLoginInfo",
//                new Class[] {}, l_loginInfo);
//            
//            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
//                    "com.fitechlabs.xtrade.plugin.security.oplogin.impl.OpLoginSecurityServiceImpl",
//                    "getLoginId",
//                    new Class[] {}, new Long(3338111123L));
//            
//            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(                
//                    "webbroker3.bd.WEB3BondOrderManager",
//                    "validateSellOrder",
//                    new Class[]{SubAccount.class,WEB3BondProduct.class,WEB3BondNewOrderSpec.class},
//                    null);
//            
//            WEB3BondEstimatedPriceCalcResult l_result = new WEB3BondEstimatedPriceCalcResult();
//            l_result.setAccruedInterest(new BigDecimal(1000));
//            l_result.setForeignTradePrice(new BigDecimal(1000));
//            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
//                "webbroker3.bd.WEB3BondBizLogicProvider",
//                "calcEstimatedPrice",
//                new Class[]{WEB3BondOrderTypeJudge.class,
//                    BigDecimal.class,
//                    BigDecimal.class,
//                    BigDecimal.class,
//                    WEB3BondProduct.class,
//                    WEB3BondExecuteDateInfo.class},
//                    l_result);
//            
//            WEB3BondSellConfirmRequest l_request = new WEB3BondSellConfirmRequest();
//            l_request.id = "1001";
//            l_request.faceAmount = "1000";
//            l_request.settleDiv = "2";
//            l_request.orderPriceDiv = "1";
//            l_request.sellPrice = "12.04";
//            l_request.executionUpdateDate = WEB3DateUtility.getDate("20070514", "yyyyMMdd");
//            l_request.foreignExecutionDate = WEB3DateUtility.getDate("20070514", "yyyyMMdd");
//            l_request.deliveryDate = WEB3DateUtility.getDate("99991001", "yyyyMMdd");
//            l_request.foreignDeliveryDate= WEB3DateUtility.getDate("20070514", "yyyyMMdd");
//            WEB3BondSellConfirmResponse l_response = l_mpl.validateSellOrder(l_request);
//            assertEquals("20070514",WEB3DateUtility.formatDate(l_response.executionUpdateDate, "yyyyMMdd"));
//            assertEquals("20070514" , WEB3DateUtility.formatDate(l_response.foreignExecutionDate, "yyyyMMdd"));
//            assertEquals("99991001" , WEB3DateUtility.formatDate(l_response.deliveryDate, "yyyyMMdd"));
//            assertEquals("99991001" ,WEB3DateUtility.formatDate(l_response.foreignDeliveryDate, "yyyyMMdd"));
//            WEB3MockObjectParamsValue l_value =
//                TestBaseForMock.MOCK_MANAGER.getMethodParamsValue(
//                        "webbroker3.bd.WEB3BondBizLogicProvider",
//                        "calcEstimatedPrice",
//                        new Class[]{WEB3BondOrderTypeJudge.class,
//                                BigDecimal.class,
//                                BigDecimal.class,
//                                BigDecimal.class,
//                                WEB3BondProduct.class,
//                                WEB3BondExecuteDateInfo.class});
//            
//            BigDecimal l_str = (BigDecimal)l_value.getFirstCalled()[2];
//            assertEquals(12.04, l_str.doubleValue(), new Double(0).doubleValue());
//        }
//        catch (Exception l_ex)
//        {
//            log.debug(l_ex.getMessage(), l_ex);
//            fail();
//        }
//        
//        log.exiting(STR_METHOD_NAME);
//    }
//    
//    public void testValidateSellOrder_case0002()
//    {
//        final String STR_METHOD_NAME = "testValidateSellOrder_case0002()";
//        log.entering(STR_METHOD_NAME);
//        
//        try
//        {
//            TestDBUtility.deleteAll(ProductRow.TYPE);
//            ProductParams l_productParams =
//                TestDBUtility.getProductRow();
//            l_productParams.setProductId(1006169090018L);
//            TestDBUtility.insertWithDel(l_productParams);
//
//            TestDBUtility.deleteAll(BondProductRow.TYPE);
//            BondProductParams l_bondProductParams =
//                TestDBUtility.getBondProductRow();
//            l_bondProductParams.setProductId(1006169090018L);
//            l_bondProductParams.setSellPrice(13.001);
//             TestDBUtility.insertWithDel(l_bondProductParams);
//
//            TestDBUtility.deleteAll(MainAccountRow.TYPE);
//            MainAccountParams l_mainAccountParams =TestDBUtility.getMainAccountRow();
//            l_mainAccountParams.setAccountId(333812512203L);
//            TestDBUtility.insertWithDel(l_mainAccountParams);
//
//            TestDBUtility.deleteAll(SubAccountRow.TYPE);
//            SubAccountParams l_subAccountParams =TestDBUtility.getSubAccountRow();
//            l_subAccountParams.setAccountId(333812512203L);
//            l_subAccountParams.setSubAccountType(SubAccountTypeEnum.EQUITY_SUB_ACCOUNT);
//            TestDBUtility.insertWithDel(l_subAccountParams);
//
//            TestDBUtility.deleteAll(BranchRow.TYPE);
//            BranchParams l_branchParams = TestDBUtility.getBranchRow();
//            l_branchParams.setBranchId(33381L);
//            TestDBUtility.insertWithDel(l_branchParams);
//            
//            TestDBUtility.deleteAll(AssetRow.TYPE);
//            AssetParams l_assetParams = TestDBUtility.getAssetRow();
//            l_assetParams.setProductType(ProductTypeEnum.BOND);
//            TestDBUtility.insertWithDel(l_assetParams);
//            
//            TestDBUtility.deleteAll(TraderRow.TYPE);
//            TraderParams l_traderParams = TestDBUtility.getTraderRow();
//            TestDBUtility.insertWithDel(l_traderParams);
////            super.commit();
//            
//            Date l_datOrderBizDate = WEB3DateUtility.getDate("20070514", "yyyyMMdd");
////          WEB3GentradeTradingTimeManagementForMock.getClendarContext();
//          WEB3GentradeTradingTimeManagementForMock.mockGetOrderBizDate(l_datOrderBizDate);
//            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
//            "com.fitechlabs.xtrade.plugin.security.oplogin.impl.OpLoginSecurityServiceImpl",
//            "getAccountId",
//            new Class[] {}, new Long(333812512203L));
//            
//            LoginInfo l_loginInfo = new LoginInfoImpl();
//            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
//                "com.fitechlabs.xtrade.plugin.security.oplogin.impl.OpLoginSecurityServiceImpl",
//                "getLoginInfo",
//                new Class[] {}, l_loginInfo);
//            
//            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
//                    "com.fitechlabs.xtrade.plugin.security.oplogin.impl.OpLoginSecurityServiceImpl",
//                    "getLoginId",
//                    new Class[] {}, new Long(3338111123L));
//            
//            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(                
//                    "webbroker3.bd.WEB3BondOrderManager",
//                    "validateSellOrder",
//                    new Class[]{SubAccount.class,WEB3BondProduct.class,WEB3BondNewOrderSpec.class},
//                    null);
//            
//            WEB3BondEstimatedPriceCalcResult l_result = new WEB3BondEstimatedPriceCalcResult();
//            l_result.setAccruedInterest(new BigDecimal(1000));
//            l_result.setForeignTradePrice(new BigDecimal(1000));
//            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
//                "webbroker3.bd.WEB3BondBizLogicProvider",
//                "calcEstimatedPrice",
//                new Class[]{WEB3BondOrderTypeJudge.class,
//                    BigDecimal.class,
//                    BigDecimal.class,
//                    BigDecimal.class,
//                    WEB3BondProduct.class,
//                    WEB3BondExecuteDateInfo.class},
//                    l_result);
//            
//            WEB3BondSellConfirmRequest l_request = new WEB3BondSellConfirmRequest();
//            l_request.id = "1001";
//            l_request.faceAmount = "1000";
//            l_request.settleDiv = "2";
//            l_request.sellPrice = "12.04";
//            l_request.executionUpdateDate = WEB3DateUtility.getDate("20070514", "yyyyMMdd");
//            l_request.foreignExecutionDate = WEB3DateUtility.getDate("20070514", "yyyyMMdd");
//            l_request.deliveryDate = WEB3DateUtility.getDate("99991001", "yyyyMMdd");
//            l_request.foreignDeliveryDate= WEB3DateUtility.getDate("20070514", "yyyyMMdd");
//            WEB3BondSellConfirmResponse l_response = l_mpl.validateSellOrder(l_request);
//            assertEquals("20070514" ,WEB3DateUtility.formatDate(l_response.executionUpdateDate, "yyyyMMdd"));
//            assertEquals("20070514" , WEB3DateUtility.formatDate(l_response.foreignExecutionDate, "yyyyMMdd"));
//            assertEquals("99991001" , WEB3DateUtility.formatDate(l_response.deliveryDate, "yyyyMMdd"));
//            assertEquals("99991001" ,WEB3DateUtility.formatDate(l_response.foreignDeliveryDate, "yyyyMMdd"));
//            WEB3MockObjectParamsValue l_value =
//                TestBaseForMock.MOCK_MANAGER.getMethodParamsValue(
//                    "webbroker3.bd.WEB3BondBizLogicProvider",
//                    "calcEstimatedPrice",
//                    new Class[]{WEB3BondOrderTypeJudge.class,
//                        BigDecimal.class,
//                        BigDecimal.class,
//                        BigDecimal.class,
//                        WEB3BondProduct.class,
//                        WEB3BondExecuteDateInfo.class});
//            
//            BigDecimal l_str = (BigDecimal)l_value.getFirstCalled()[2];
//            assertEquals(13.001, l_str.doubleValue(), new Double(0).doubleValue());
//        }
//        catch (Exception l_ex)
//        {
//            log.debug(l_ex.getMessage(), l_ex);
//            fail();
//        }
//        
//        log.exiting(STR_METHOD_NAME);
//    }
//    
//    public void testSubmitSellOrder_0001()
//    {
//        final String STR_METHOD_NAME = "testSubmitSellOrder_0001()";
//        log.entering(STR_METHOD_NAME);
//        try
//        {
//        TestDBUtility.deleteAll(ProductRow.TYPE);
//        ProductParams l_productParams =
//            TestDBUtility.getProductRow();
//        l_productParams.setProductId(1006169090018L);
//        TestDBUtility.insertWithDel(l_productParams);
//
//        TestDBUtility.deleteAll(BondProductRow.TYPE);
//        BondProductParams l_bondProductParams =
//            TestDBUtility.getBondProductRow();
//        l_bondProductParams.setProductId(1006169090018L);
//        l_bondProductParams.setSellPrice(13.001);
//         TestDBUtility.insertWithDel(l_bondProductParams);
//
//        TestDBUtility.deleteAll(MainAccountRow.TYPE);
//        MainAccountParams l_mainAccountParams =TestDBUtility.getMainAccountRow();
//        l_mainAccountParams.setAccountId(333812512203L);
//        TestDBUtility.insertWithDel(l_mainAccountParams);
//        
//        TestDBUtility.deleteAll(BondOrderRow.TYPE);
//        BondOrderParams l_bondOrderParams = this.getBondOrderRow();
//        l_bondOrderParams.setOrderId(1001);
//        TestDBUtility.insertWithDel(l_bondOrderParams);
//        
//        TestDBUtility.deleteAll(BondOrderUnitRow.TYPE);
//        BondOrderUnitParams l_BondOrderUnitParams = TestDBUtility.getBondOrderUnitRow();
//        l_BondOrderUnitParams.setOrderId(1001);
//        TestDBUtility.insertWithDel(l_BondOrderUnitParams);
//
//        TestDBUtility.deleteAll(SubAccountRow.TYPE);
//        SubAccountParams l_subAccountParams =TestDBUtility.getSubAccountRow();
//        l_subAccountParams.setAccountId(333812512203L);
//        l_subAccountParams.setSubAccountType(SubAccountTypeEnum.EQUITY_SUB_ACCOUNT);
//        TestDBUtility.insertWithDel(l_subAccountParams);
//
//        TestDBUtility.deleteAll(BranchRow.TYPE);
//        BranchParams l_branchParams = TestDBUtility.getBranchRow();
//        l_branchParams.setBranchId(33381L);
//        TestDBUtility.insertWithDel(l_branchParams);
//        
//        TestDBUtility.deleteAll(AssetRow.TYPE);
//        AssetParams l_assetParams = TestDBUtility.getAssetRow();
//        l_assetParams.setProductType(ProductTypeEnum.BOND);
//        TestDBUtility.insertWithDel(l_assetParams);
//        
//        TestDBUtility.deleteAll(TraderRow.TYPE);
//        TraderParams l_traderParams = TestDBUtility.getTraderRow();
//        TestDBUtility.insertWithDel(l_traderParams);
//        
////        TestDBUtility.deleteAll(OrderAdditionalInfoRow.TYPE);
////        OrderAdditionalInfoParams l_OrderAdditionalInfoParams = this.getOrderAdditionalInfoRows();
////        TestDBUtility.insertWithDel(l_OrderAdditionalInfoParams);
////        super.commit();
//        
//        Date l_datOrderBizDate = WEB3DateUtility.getDate("20070511", "yyyyMMdd");
////      WEB3GentradeTradingTimeManagementForMock.getClendarContext();
//      WEB3GentradeTradingTimeManagementForMock.mockGetOrderBizDate(l_datOrderBizDate);
//        TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
//        "com.fitechlabs.xtrade.plugin.security.oplogin.impl.OpLoginSecurityServiceImpl",
//        "getAccountId",
//        new Class[] {}, new Long(333812512203L));
//        
//        LoginInfo l_loginInfo = new LoginInfoImpl();
//        TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
//            "com.fitechlabs.xtrade.plugin.security.oplogin.impl.OpLoginSecurityServiceImpl",
//            "getLoginInfo",
//            new Class[] {}, l_loginInfo);
//        
//        TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
//                "com.fitechlabs.xtrade.plugin.security.oplogin.impl.OpLoginSecurityServiceImpl",
//                "getLoginId",
//                new Class[] {}, new Long(3338111123L));
//        
//        TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(                
//                "webbroker3.bd.WEB3BondOrderManager",
//                "validateSellOrder",
//                new Class[]{SubAccount.class,WEB3BondProduct.class,WEB3BondNewOrderSpec.class},
//                null);
//        
//        WEB3BondEstimatedPriceCalcResult l_result = new WEB3BondEstimatedPriceCalcResult();
//        l_result.setAccruedInterest(new BigDecimal(1000));
//        l_result.setForeignTradePrice(new BigDecimal(1000));
//        TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
//            "webbroker3.bd.WEB3BondBizLogicProvider",
//            "calcEstimatedPrice",
//            new Class[]{WEB3BondOrderTypeJudge.class,
//                BigDecimal.class,
//                BigDecimal.class,
//                BigDecimal.class,
//                WEB3BondProduct.class,
//                WEB3BondExecuteDateInfo.class},
//                l_result);
//        
//        OrderSubmissionResult l_submissionResult = new OrderSubmissionResult(ProcessingResult.SUCCESS_RESULT);
//        TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
//            "webbroker3.bd.WEB3BondOrderManager",
//            "submitNewOrder",
//            new Class[]{SubAccount.class,
//                 ProductTypeEnum.class,
//                 NewOrderSpec.class,
//                 long.class,
//                 String.class,
//                 boolean.class},
//                 l_submissionResult);
//        
//        WEB3BondSellCompleteRequest l_request = new WEB3BondSellCompleteRequest();
//        l_request.orderId = "1001";
//        l_request.checkDate = WEB3DateUtility.getDate("20070511", "yyyyMMdd");
//        l_request.id = "1001";
//        l_request.faceAmount = "1000";
//        l_request.orderPriceDiv = "1";
//        l_request.settleDiv = "2";
//        l_request.sellPrice = "12.04";
//        l_request.executionUpdateDate = WEB3DateUtility.getDate("20070511", "yyyyMMdd");
//        l_request.foreignExecutionDate = WEB3DateUtility.getDate("20070511", "yyyyMMdd");
//        l_request.deliveryDate = WEB3DateUtility.getDate("99990111", "yyyyMMdd");
//        l_request.foreignDeliveryDate= WEB3DateUtility.getDate("20070511", "yyyyMMdd");
//        WEB3BondSellCompleteResponse l_response = l_mpl.submitSellOrder(l_request);
//        WEB3MockObjectParamsValue l_value =
//            TestBaseForMock.MOCK_MANAGER.getMethodParamsValue(
//                "webbroker3.bd.WEB3BondBizLogicProvider",
//                "calcEstimatedPrice",
//                new Class[]{WEB3BondOrderTypeJudge.class,
//                    BigDecimal.class,
//                    BigDecimal.class,
//                    BigDecimal.class,
//                    WEB3BondProduct.class,
//                    WEB3BondExecuteDateInfo.class});
//        
//        BigDecimal l_str = (BigDecimal)l_value.getFirstCalled()[2];
//        assertEquals(12.04, l_str.doubleValue(), new Double(0).doubleValue());
//        }
//        catch (Exception l_ex)
//        {
//            log.debug(l_ex.getMessage(), l_ex);
//            fail();
//        }
//        
//        log.exiting(STR_METHOD_NAME);
//    }
//    
//    public void testSubmitSellOrder_0002()
//    {
//        final String STR_METHOD_NAME = "testSubmitSellOrder_0002()";
//        log.entering(STR_METHOD_NAME);
//        try
//        {
//        TestDBUtility.deleteAll(ProductRow.TYPE);
//        ProductParams l_productParams =
//            TestDBUtility.getProductRow();
//        l_productParams.setProductId(1006169090018L);
//        TestDBUtility.insertWithDel(l_productParams);
//
//        TestDBUtility.deleteAll(BondProductRow.TYPE);
//        BondProductParams l_bondProductParams =
//            TestDBUtility.getBondProductRow();
//        l_bondProductParams.setProductId(1006169090018L);
//        l_bondProductParams.setSellPrice(13.001);
//         TestDBUtility.insertWithDel(l_bondProductParams);
//
//        TestDBUtility.deleteAll(MainAccountRow.TYPE);
//        MainAccountParams l_mainAccountParams =TestDBUtility.getMainAccountRow();
//        l_mainAccountParams.setAccountId(333812512203L);
//        TestDBUtility.insertWithDel(l_mainAccountParams);
//        
//        TestDBUtility.deleteAll(BondOrderRow.TYPE);
//        BondOrderParams l_bondOrderParams = this.getBondOrderRow();
//        l_bondOrderParams.setOrderId(1001);
//        TestDBUtility.insertWithDel(l_bondOrderParams);
//        
//        TestDBUtility.deleteAll(BondOrderUnitRow.TYPE);
//        BondOrderUnitParams l_BondOrderUnitParams = TestDBUtility.getBondOrderUnitRow();
//        l_BondOrderUnitParams.setOrderId(1001);
//        TestDBUtility.insertWithDel(l_BondOrderUnitParams);
//
//        TestDBUtility.deleteAll(SubAccountRow.TYPE);
//        SubAccountParams l_subAccountParams =TestDBUtility.getSubAccountRow();
//        l_subAccountParams.setAccountId(333812512203L);
//        l_subAccountParams.setSubAccountType(SubAccountTypeEnum.EQUITY_SUB_ACCOUNT);
//        TestDBUtility.insertWithDel(l_subAccountParams);
//
//        TestDBUtility.deleteAll(BranchRow.TYPE);
//        BranchParams l_branchParams = TestDBUtility.getBranchRow();
//        l_branchParams.setBranchId(33381L);
//        TestDBUtility.insertWithDel(l_branchParams);
//        
//        TestDBUtility.deleteAll(AssetRow.TYPE);
//        AssetParams l_assetParams = TestDBUtility.getAssetRow();
//        l_assetParams.setProductType(ProductTypeEnum.BOND);
//        TestDBUtility.insertWithDel(l_assetParams);
//        
//        TestDBUtility.deleteAll(TraderRow.TYPE);
//        TraderParams l_traderParams = TestDBUtility.getTraderRow();
//        TestDBUtility.insertWithDel(l_traderParams);
//        
////        TestDBUtility.deleteAll(OrderAdditionalInfoRow.TYPE);
////        OrderAdditionalInfoParams l_OrderAdditionalInfoParams = this.getOrderAdditionalInfoRows();
////        TestDBUtility.insertWithDel(l_OrderAdditionalInfoParams);
////        super.commit();
//        Date l_datOrderBizDate = WEB3DateUtility.getDate("20070511", "yyyyMMdd");
////        WEB3GentradeTradingTimeManagementForMock.getClendarContext();
//        WEB3GentradeTradingTimeManagementForMock.mockGetOrderBizDate(l_datOrderBizDate);
//        TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
//        "com.fitechlabs.xtrade.plugin.security.oplogin.impl.OpLoginSecurityServiceImpl",
//        "getAccountId",
//        new Class[] {}, new Long(333812512203L));
//        
//        LoginInfo l_loginInfo = new LoginInfoImpl();
//        TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
//            "com.fitechlabs.xtrade.plugin.security.oplogin.impl.OpLoginSecurityServiceImpl",
//            "getLoginInfo",
//            new Class[] {}, l_loginInfo);
//        
//        TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
//                "com.fitechlabs.xtrade.plugin.security.oplogin.impl.OpLoginSecurityServiceImpl",
//                "getLoginId",
//                new Class[] {}, new Long(3338111123L));
//        
//        TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(                
//                "webbroker3.bd.WEB3BondOrderManager",
//                "validateSellOrder",
//                new Class[]{SubAccount.class,WEB3BondProduct.class,WEB3BondNewOrderSpec.class},
//                null);
//        
//        WEB3BondEstimatedPriceCalcResult l_result = new WEB3BondEstimatedPriceCalcResult();
//        l_result.setAccruedInterest(new BigDecimal(1000));
//        l_result.setForeignTradePrice(new BigDecimal(1000));
//        TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
//            "webbroker3.bd.WEB3BondBizLogicProvider",
//            "calcEstimatedPrice",
//            new Class[]{WEB3BondOrderTypeJudge.class,
//                BigDecimal.class,
//                BigDecimal.class,
//                BigDecimal.class,
//                WEB3BondProduct.class,
//                WEB3BondExecuteDateInfo.class},
//                l_result);
//        
//        OrderSubmissionResult l_submissionResult = new OrderSubmissionResult(ProcessingResult.SUCCESS_RESULT);
//        TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
//            "webbroker3.bd.WEB3BondOrderManager",
//            "submitNewOrder",
//            new Class[]{SubAccount.class,
//                 ProductTypeEnum.class,
//                 NewOrderSpec.class,
//                 long.class,
//                 String.class,
//                 boolean.class},
//                 l_submissionResult);
//        
//        WEB3BondSellCompleteRequest l_request = new WEB3BondSellCompleteRequest();
//        l_request.orderId = "1001";
//        l_request.checkDate = WEB3DateUtility.getDate("20070511", "yyyyMMdd");
//        l_request.id = "1001";
//        l_request.faceAmount = "1000";
//        l_request.settleDiv = "2";
//        l_request.sellPrice = "12.04";
//        l_request.executionUpdateDate = WEB3DateUtility.getDate("20070511", "yyyyMMdd");
//        l_request.foreignExecutionDate = WEB3DateUtility.getDate("20070511", "yyyyMMdd");
//        l_request.deliveryDate = WEB3DateUtility.getDate("99990111", "yyyyMMdd");
//        l_request.foreignDeliveryDate= WEB3DateUtility.getDate("20070511", "yyyyMMdd");
//        WEB3BondSellCompleteResponse l_response = l_mpl.submitSellOrder(l_request);
//        WEB3MockObjectParamsValue l_value =
//            TestBaseForMock.MOCK_MANAGER.getMethodParamsValue(
//                "webbroker3.bd.WEB3BondBizLogicProvider",
//                "calcEstimatedPrice",
//                new Class[]{WEB3BondOrderTypeJudge.class,
//                    BigDecimal.class,
//                    BigDecimal.class,
//                    BigDecimal.class,
//                    WEB3BondProduct.class,
//                    WEB3BondExecuteDateInfo.class});
//        
//        BigDecimal l_str = (BigDecimal)l_value.getFirstCalled()[2];
//        assertEquals(13.001, l_str.doubleValue(), new Double(0).doubleValue());
//        }
//        catch (Exception l_ex)
//        {
//            log.debug(l_ex.getMessage(), l_ex);
//            fail();
//        }
//        
//        log.exiting(STR_METHOD_NAME);
//    }
    
    /**
     * ç¬åîíçï∂ÉeÅ[ÉuÉã
     * @@return
     */
    public static BondOrderParams getBondOrderRow()
    {
        BondOrderParams l_bondOrderParams = new BondOrderParams();
        
        l_bondOrderParams.setAccountId(333812512203L);
        l_bondOrderParams.setOrderId(1001);
        l_bondOrderParams.setSubAccountId(333812512203L);
        l_bondOrderParams.setCreatedTimestamp(Calendar.getInstance().getTime());
        l_bondOrderParams.setLastUpdatedTimestamp(Calendar.getInstance().getTime());
        
        return l_bondOrderParams;
    }
    
//    /**
//     * íçï∂ïtâ¡èÓïÒRow
//     */
//    public static OrderAdditionalInfoParams getOrderAdditionalInfoRows()
//    {
//        OrderAdditionalInfoParams l_orderAdditionalInfoParams = new OrderAdditionalInfoParams();
//        
//        l_orderAdditionalInfoParams.setOrderUnitId(1001);
//        l_orderAdditionalInfoParams.setAccountId(333812512203L);
//        l_orderAdditionalInfoParams.setProductType(ProductTypeEnum.BOND);
//        l_orderAdditionalInfoParams.setOrderRequestNumber("123456");
//        l_orderAdditionalInfoParams.setName("tokyo");
//        l_orderAdditionalInfoParams.setCreatedTimestamp(Calendar.getInstance().getTime());
//        l_orderAdditionalInfoParams.setLastUpdatedTimestamp(Calendar.getInstance().getTime());
//        
//        return l_orderAdditionalInfoParams;
//    }
//    
    public void testTest()
    {
        
    }
}
@
