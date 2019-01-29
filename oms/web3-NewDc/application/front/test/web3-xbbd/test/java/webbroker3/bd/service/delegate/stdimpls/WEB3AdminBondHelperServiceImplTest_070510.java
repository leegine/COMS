head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.04.07.01.45.53;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	5444d9d078c63f4;
filename	WEB3AdminBondHelperServiceImplTest_070510.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (äî)ëÂòaëçå§ èÿåîÉ\ÉäÉÖÅ[ÉVÉáÉìÉVÉXÉeÉÄëÊìÒïî
File Name        : //TODO(WEB3AdminBondHelperServiceImplTest_070510)
Author Name      : Daiwa Institute of Research
Revesion History : 2007/05/11 ïêîg(íÜêu) êVãKçÏê¨
*/
package webbroker3.bd.service.delegate.stdimpls;

import com.fitechlabs.xtrade.plugin.tc.gentrade.Branch;
import com.fitechlabs.xtrade.plugin.tc.gentrade.OrderTypeEnum;
import com.fitechlabs.xtrade.plugin.tc.gentrade.ProductTypeEnum;
import com.fitechlabs.xtrade.plugin.tc.gentrade.data.BranchParams;
import com.fitechlabs.xtrade.plugin.tc.gentrade.data.ProductParams;
import com.fitechlabs.xtrade.plugin.tc.gentrade.stdimpls.BranchImpl;
import com.fitechlabs.xtrade.plugin.tc.xbbd.data.BondOrderUnitParams;
import com.fitechlabs.xtrade.plugin.tc.xbbd.data.BondProductParams;

import test.util.TestDBUtility;

import webbroker3.bd.WEB3BondExecuteDateInfo;
import webbroker3.bd.WEB3BondOrderTypeJudge;
import webbroker3.bd.WEB3BondOrderUnit;
import webbroker3.bd.WEB3BondProduct;
import webbroker3.bd.data.BondBranchConditionParams;
import webbroker3.bd.message.WEB3AdminBondOrderExecInfo;
import webbroker3.bd.message.WEB3AdminBondOrderInfo;
import webbroker3.common.WEB3BaseException;
import webbroker3.common.WEB3ErrorCatalog;
//import webbroker3.gentrade.data.OrderAdditionalInfoParams;
//import webbroker3.gentrade.message.WEB3GentradeOrderAddInfoUnit;
import webbroker3.mock.TestBaseForMock;
import webbroker3.util.WEB3DateUtility;
import webbroker3.util.WEB3LogUtility;

/**
 * @@author ïêîg(íÜêu)
 * @@version 1.0
 */
public class WEB3AdminBondHelperServiceImplTest_070510 extends TestBaseForMock
{

    public WEB3AdminBondHelperServiceImplTest_070510(String name)
    {
        super(name);
        // TODO Auto-generated constructor stub
    }

    /**
     * Log Variable.<BR>
     */
    private static WEB3LogUtility log =
        WEB3LogUtility.getInstance(WEB3AdminBondHelperServiceImplTest_070510.class);

    protected void setUp() throws Exception
    {
        super.setUp();
    }

    protected void tearDown() throws Exception
    {
        super.tearDown();
    }
    
//    /*
//     *ç¬åîñ¡ïø.îÉïtíPâø == Åh0Åh
//     */
//    public  void test_toOrderInfo_C0001()
//    {
//        final String STR_METHOD_NAME = "test_toOrderInfo_C0001()";
//        log.entering(TEST_START + STR_METHOD_NAME);
//        try
//        {
//            TestDBUtility.deleteAll(BondOrderUnitParams.TYPE);
//            BondOrderUnitParams l_bondOrderUnitParams = TestDBUtility.getBondOrderUnitRow();
//            l_bondOrderUnitParams.setOrderUnitId(123456L);
//            l_bondOrderUnitParams.setPrice(23.2);
//            l_bondOrderUnitParams.setLimitPrice(0d);
//            l_bondOrderUnitParams.setProductType(ProductTypeEnum.BOND);
//            TestDBUtility.insertWithDel(l_bondOrderUnitParams);
//
////            TestDBUtility.deleteAll(OrderAdditionalInfoParams.TYPE);
////            OrderAdditionalInfoParams l_orderAdditionalInfoParams = TestDBUtility.getOrderAdditionalInfoRow();
////            l_orderAdditionalInfoParams.setName("123");
////            l_orderAdditionalInfoParams.setValue("321");
////            l_orderAdditionalInfoParams.setOrderUnitId(123456L);
////            l_orderAdditionalInfoParams.setProductType(ProductTypeEnum.BOND);
////            TestDBUtility.insertWithDel(l_orderAdditionalInfoParams);
//
//            TestDBUtility.deleteAll(ProductParams.TYPE);
//            ProductParams l_productParams = TestDBUtility.getProductRow();
//            l_productParams.setProductId(123456L);
//            TestDBUtility.insertWithDel(l_productParams);
//
//            TestDBUtility.deleteAll(BondProductParams.TYPE);
//            BondProductParams l_bondProductParams = TestDBUtility.getBondProductRow();
//            l_bondProductParams.setProductId(123456L);
//            TestDBUtility.insertWithDel(l_bondProductParams);
//
//            WEB3BondOrderUnit l_orderUnit = new WEB3BondOrderUnit(123456L);
//            
//            WEB3AdminBondHelperServiceImpl l_adminBondHelperServiceImpl = new WEB3AdminBondHelperServiceImpl();
//            WEB3AdminBondOrderInfo l_adminBondOrderInfo = l_adminBondHelperServiceImpl.toOrderInfo(l_orderUnit);
//
//            assertEquals("2", l_adminBondOrderInfo.orderPriceDiv);
//            assertEquals("23.2",l_adminBondOrderInfo.price);
////            assertEquals(1,l_adminBondOrderInfo.orderAddInfoList.length);
//////            WEB3GentradeOrderAddInfoUnit[] l_orderAddInfoList = l_adminBondOrderInfo.orderAddInfoList;
////            assertEquals("123",l_orderAddInfoList[0].name);
////            assertEquals("321",l_orderAddInfoList[0].value);
//        }
//        catch (Exception l_ex)
//        {
//            log.error(STR_METHOD_NAME, l_ex);
//            fail();
//        }
//        log.exiting(TEST_END + STR_METHOD_NAME);
//    }
//    
//    /*
//     *ç¬åîñ¡ïø.îÉïtíPâø == Åh5Åh
//     */
//    public  void test_toOrderInfo_C0002()
//    {
//        final String STR_METHOD_NAME = "test_toOrderInfo_C0002()";
//        log.entering(TEST_START + STR_METHOD_NAME);
//        try
//        {
//            TestDBUtility.deleteAll(BondOrderUnitParams.TYPE);
//            BondOrderUnitParams l_bondOrderUnitParams = TestDBUtility.getBondOrderUnitRow();
//            l_bondOrderUnitParams.setOrderUnitId(123456L);
//            l_bondOrderUnitParams.setPrice(23.5);
//            l_bondOrderUnitParams.setLimitPrice(5d);
//            l_bondOrderUnitParams.setProductType(ProductTypeEnum.BOND);
//            TestDBUtility.insertWithDel(l_bondOrderUnitParams);
//
////            TestDBUtility.deleteAll(OrderAdditionalInfoParams.TYPE);
////            OrderAdditionalInfoParams l_orderAdditionalInfoParams = TestDBUtility.getOrderAdditionalInfoRow();
////            l_orderAdditionalInfoParams.setName("123");
////            l_orderAdditionalInfoParams.setValue("321");
////            l_orderAdditionalInfoParams.setOrderUnitId(123456L);
////            l_orderAdditionalInfoParams.setProductType(ProductTypeEnum.BOND);
////            TestDBUtility.insertWithDel(l_orderAdditionalInfoParams);
////
////            OrderAdditionalInfoParams l_orderAdditionalInfoParams1 = TestDBUtility.getOrderAdditionalInfoRow();
////            l_orderAdditionalInfoParams1.setName("1234");
////            l_orderAdditionalInfoParams1.setValue("3215");
////            l_orderAdditionalInfoParams1.setOrderUnitId(123456L);
////            l_orderAdditionalInfoParams1.setProductType(ProductTypeEnum.BOND);
////            TestDBUtility.insertWithDel(l_orderAdditionalInfoParams1);
//
//            TestDBUtility.deleteAll(ProductParams.TYPE);
//            ProductParams l_productParams = TestDBUtility.getProductRow();
//            l_productParams.setProductId(123456L);
//            TestDBUtility.insertWithDel(l_productParams);
//
//            TestDBUtility.deleteAll(BondProductParams.TYPE);
//            BondProductParams l_bondProductParams = TestDBUtility.getBondProductRow();
//            l_bondProductParams.setProductId(123456L);
//            TestDBUtility.insertWithDel(l_bondProductParams);
//
//            WEB3BondOrderUnit l_orderUnit = new WEB3BondOrderUnit(123456L);
//            
//            WEB3AdminBondHelperServiceImpl l_adminBondHelperServiceImpl = new WEB3AdminBondHelperServiceImpl();
//            WEB3AdminBondOrderInfo l_adminBondOrderInfo = l_adminBondHelperServiceImpl.toOrderInfo(l_orderUnit);
//
//            assertEquals("1", l_adminBondOrderInfo.orderPriceDiv);
//            assertEquals("23.5",l_adminBondOrderInfo.price);
////            assertEquals(2,l_adminBondOrderInfo.orderAddInfoList.length);
////            WEB3GentradeOrderAddInfoUnit[] l_orderAddInfoList = l_adminBondOrderInfo.orderAddInfoList;
////            assertEquals("123",l_orderAddInfoList[0].name);
////            assertEquals("321",l_orderAddInfoList[0].value);
////            assertEquals("1234",l_orderAddInfoList[1].name);
////            assertEquals("3215",l_orderAddInfoList[1].value);
//        }
//        catch (Exception l_ex)
//        {
//            log.error(STR_METHOD_NAME, l_ex);
//            fail();
//        }
//        log.exiting(TEST_END + STR_METHOD_NAME);
//    }

    /*
     *
     */
    public  void test_resetExecuteDateInfo_C0003()
    {
        final String STR_METHOD_NAME = "test_resetExecuteDateInfo_C0003()";
        log.entering(TEST_START + STR_METHOD_NAME);
        try
        {
            TestDBUtility.deleteAll(BondProductParams.TYPE);
            BondProductParams l_bondProductParams = TestDBUtility.getBondProductRow();
            l_bondProductParams.setProductId(123L);
            TestDBUtility.insertWithDel(l_bondProductParams);

            TestDBUtility.deleteAll(ProductParams.TYPE);
            ProductParams l_productParams = TestDBUtility.getProductRow();
            l_productParams.setProductId(123L);
            TestDBUtility.insertWithDel(l_productParams);

            TestDBUtility.deleteAll(BranchParams.TYPE);
            BranchParams l_branchParams = TestDBUtility.getBranchRow();
            TestDBUtility.insertWithDel(l_branchParams);

            WEB3AdminBondOrderExecInfo l_orderExecInfo = new WEB3AdminBondOrderExecInfo();
            l_orderExecInfo.domesticDeliveryDate = null;
            WEB3BondExecuteDateInfo l_executeDateInfo = new WEB3BondExecuteDateInfo();

            String l_strTrading = "36";
            WEB3BondOrderTypeJudge l_bondOrderTypeJudge = new WEB3BondOrderTypeJudge(OrderTypeEnum.BOND_BUY,l_strTrading);

            WEB3BondProduct l_bondProduct = new WEB3BondProduct(l_bondProductParams);
            
            String l_strSettleDiv = "123";
            Branch l_branch = new BranchImpl(l_branchParams);
            
            WEB3AdminBondHelperServiceImpl l_adminBondHelperServiceImpl = new WEB3AdminBondHelperServiceImpl();
            l_adminBondHelperServiceImpl.resetExecuteDateInfo(
                 l_orderExecInfo, l_executeDateInfo,l_bondOrderTypeJudge, 
                 l_bondProduct,  l_strSettleDiv,  l_branch);
            fail();
        }
        catch (WEB3BaseException l_web3BaseException)
        {
            assertEquals(WEB3ErrorCatalog.BUSINESS_ERROR_01079,l_web3BaseException.getErrorInfo());
        }
        catch (Exception l_ex)
        {
            log.error(STR_METHOD_NAME, l_ex);
            fail();
        }
        log.exiting(TEST_END + STR_METHOD_NAME);
    }
    
    /*
    *
    */
   public  void test_resetExecuteDateInfo_C0004()
   {
       final String STR_METHOD_NAME = "test_resetExecuteDateInfo_C0004()";
       log.entering(TEST_START + STR_METHOD_NAME);
       try
       {
           TestDBUtility.deleteAll(BondBranchConditionParams.TYPE);
           BondBranchConditionParams l_bondBranchConditionParams =
               TestDBUtility.getBondBranchConditionRow();
           l_bondBranchConditionParams.setBranchId(33381);
           l_bondBranchConditionParams.setPaymentDateSetDiv("2");
           TestDBUtility.insertWithDel(l_bondBranchConditionParams);

           TestDBUtility.deleteAll(BondProductParams.TYPE);
           BondProductParams l_bondProductParams = TestDBUtility.getBondProductRow();
           l_bondProductParams.setProductId(123L);
           TestDBUtility.insertWithDel(l_bondProductParams);

           TestDBUtility.deleteAll(ProductParams.TYPE);
           ProductParams l_productParams = TestDBUtility.getProductRow();
           l_productParams.setProductId(123L);
           TestDBUtility.insertWithDel(l_productParams);

           TestDBUtility.deleteAll(BranchParams.TYPE);
           BranchParams l_branchParams = TestDBUtility.getBranchRow();
           TestDBUtility.insertWithDel(l_branchParams);

           WEB3AdminBondOrderExecInfo l_orderExecInfo = new WEB3AdminBondOrderExecInfo();
           l_orderExecInfo.domesticDeliveryDate = null;
           l_orderExecInfo.domesticExecutionDate = WEB3DateUtility.getDate("20070511", "yyyyMMdd");
           l_orderExecInfo.foreignExecutionDate = WEB3DateUtility.getDate("20070511", "yyyyMMdd");
           l_orderExecInfo.domesticDeliveryDate = WEB3DateUtility.getDate("20070511", "yyyyMMdd");
           l_orderExecInfo.foreignDeliveryDate = WEB3DateUtility.getDate("20070511", "yyyyMMdd");
           l_orderExecInfo.paymentDate = null;

           WEB3BondExecuteDateInfo l_executeDateInfo = new WEB3BondExecuteDateInfo();
           l_executeDateInfo.setExecuteDate(WEB3DateUtility.getDate("20070511", "yyyyMMdd"));

           String l_strTrading = "35";
           WEB3BondOrderTypeJudge l_bondOrderTypeJudge = new WEB3BondOrderTypeJudge(OrderTypeEnum.BOND_BUY,l_strTrading);

           WEB3BondProduct l_bondProduct = new WEB3BondProduct(l_bondProductParams);
           
           String l_strSettleDiv = "123";
           Branch l_branch = new BranchImpl(l_branchParams);
           
           WEB3AdminBondHelperServiceImpl l_adminBondHelperServiceImpl = new WEB3AdminBondHelperServiceImpl();
           WEB3BondExecuteDateInfo l_bondExecuteDateInfo = l_adminBondHelperServiceImpl.resetExecuteDateInfo(
                l_orderExecInfo, l_executeDateInfo,l_bondOrderTypeJudge, 
                l_bondProduct,  l_strSettleDiv,  l_branch);
           assertEquals(WEB3DateUtility.getDate("20070511", "yyyyMMdd"),l_bondExecuteDateInfo.getPaymentDate());
       }
       catch (Exception l_ex)
       {
           log.error(STR_METHOD_NAME, l_ex);
           fail();
       }
       log.exiting(TEST_END + STR_METHOD_NAME);
   }
}@
