head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.04.07.02.24.11;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	5444d9d078c63f4;
filename	WEB3TPTransactionAmountTest.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : //TODO(WEB3TPTransactionAmountTest.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2006/09/29 徐宏偉 (中訊) 新規作成
*/
package webbroker3.tradingpower.updtpower.cash;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Hashtable;
import java.util.List;
import java.util.Map;

import com.fitechlabs.xtrade.kernel.boot.Services;
import com.fitechlabs.xtrade.plugin.tc.gentrade.BooleanEnum;
import com.fitechlabs.xtrade.plugin.tc.gentrade.FinApp;
import com.fitechlabs.xtrade.plugin.tc.gentrade.FinTransactionType;
import com.fitechlabs.xtrade.plugin.tc.gentrade.GtlUtils;
import com.fitechlabs.xtrade.plugin.tc.gentrade.OrderOpenStatusEnum;
import com.fitechlabs.xtrade.plugin.tc.gentrade.OrderStatusEnum;
import com.fitechlabs.xtrade.plugin.tc.gentrade.ProductTypeEnum;
import com.fitechlabs.xtrade.plugin.tc.gentrade.SubAccountTypeEnum;

import test.util.TestDBUtility;

import webbroker3.common.WEB3BaseException;
import webbroker3.ipo.data.IpoOrderParams;
import webbroker3.ipo.data.IpoOrderRow;
import webbroker3.ipo.data.IpoProductParams;
import webbroker3.ipo.data.IpoProductRow;
import webbroker3.mock.TestBaseForMock;
import webbroker3.tradingpower.WEB3TPCalcCondition;
import webbroker3.tradingpower.define.WEB3TPIPOLotResultTypeDef;
import webbroker3.tradingpower.define.WEB3TPIPOOfferingDivDef;
import webbroker3.tradingpower.updtpower.WEB3TPAccountInfo;
import webbroker3.util.WEB3DateUtility;
import webbroker3.util.WEB3LogUtility;

/**
 * XXXXXXクラス//TODO
 *
 * @@author 徐宏偉(中訊)
 * @@version 1.0
 */
public class WEB3TPTransactionAmountTest extends TestBaseForMock
{
    private static WEB3LogUtility log = 
        WEB3LogUtility.getInstance(WEB3TPTransactionAmountTest.class);
    
    private WEB3TPTransactionAmount l_amount = new WEB3TPTransactionAmount();
    
    private FinApp l_finApp = (FinApp)Services.getService(FinApp.class);
    
    public WEB3TPTransactionAmountTest(String arg0)
    {
        super(arg0);
    }

    protected void setUp() throws Exception
    {
//        super.tearDown();
//        /**
//         * @@param l_strMarketCode String 市場コード
//         * @@param l_strBranchCode String 部店コード
//         * @@param l_strInstitutionCode String 証券会社コード
//         * @@param l_tradingTimeType String 受付時間区分
//         * @@param l_orderAcceptProduct String 注文受付商品
//         * @@param l_OrderAcceptTransaction String 注文受付トランザクション
//         * @@param l_productCode 銘柄コード 先物OP／投信のみ使用 以外は 0：DEFAULT
//         */
//        super.doSettingTradingClendarContext(
//            "0",
//            "381",
//            "0D",
//            WEB3TradingTimeTypeDef.BOND,
//            "0",
//            WEB3OrderAccProductDef.BOND,
//            WEB3OrderAccTransactionDef.CHANGE);
//        
//        BondOrderUnitRow l_bongOrderUnitRow = (BondOrderUnitRow)BondOrderUnitDao.findRowByOrderUnitId(10001);
//        BondOrderUnitParams l_bondOrderUnitParams = new BondOrderUnitParams(l_bongOrderUnitRow);
//        l_bondOrderUnitParams.setSettlementDiv("1");
//        l_bondOrderUnitParams.setOrderExecStatus("1");
//        
//        QueryProcessor l_qprocessor = Processors.getDefaultProcessor();
//        l_qprocessor.doUpdateQuery(l_bondOrderUnitParams);
        }

    protected void tearDown() throws Exception 
    {
//        doClearTradingClendarContext();
//        super.tearDown();
    }

    public void test_getEquityTransactions_case0001()
    {
        final String STR_METHOD_NAME = "test_getEquityTransactions_case0001()";
        log.entering(STR_METHOD_NAME);

        try
        {
            WEB3TPTransactionAmount l_transactionAmount = new WEB3TPTransactionAmountInner();
            assertEquals(0, l_transactionAmount.getEquityTransactions(null, null).length);
        }
        catch (Exception l_ex)
        {
            log.error("Error in Exception...", l_ex);
            fail();
        }
        log.exiting(STR_METHOD_NAME);
    }
    public class WEB3TPTransactionAmountInner extends WEB3TPTransactionAmount
    {
        protected WEB3TPTransactionReflector[] searchTransactions(
                List l_transactions,
                ProductTypeEnum l_productType,
                FinTransactionType[] l_transactionTypes,
                Date l_datDeliveryDate)
        {
            return null;
        }
    }


    public void test_getEquityTransactions_case0002()
    {
        final String STR_METHOD_NAME = "test_getEquityTransactions_case0002()";
        log.entering(STR_METHOD_NAME);

        try
        {
            WEB3TPTransactionAmount l_transactionAmount = new WEB3TPTransactionAmountInner1();
            assertEquals(2, l_transactionAmount.getEquityTransactions(null, null).length);
        }
        catch (Exception l_ex)
        {
            log.error("Error in Exception...", l_ex);
            fail();
        }
        log.exiting(STR_METHOD_NAME);
    }
    public class WEB3TPTransactionAmountInner1 extends WEB3TPTransactionAmount
    {
        protected WEB3TPTransactionReflector[] searchTransactions(
                List l_transactions,
                ProductTypeEnum l_productType,
                FinTransactionType[] l_transactionTypes,
                Date l_datDeliveryDate)
        {
            return new WEB3TPTransactionReflector[]{
                new WEB3TPTransactionReflector()};
        }
    }
//    /*
//     * 約定済の場合
//     *（引数.債券注文.get注文約定区分() == 1：約定済）
//     *[a.募集取引の場合]
//     *（引数.債券注文.get注文種別() == 401：債券買い注文
//     *　@&&
//     * 引数.債券注文.get取引() == 35:募集取引）
//     */
//    public void testCreateWEB3TPTransactionReflector_case001()
//    {
//        final String STR_METHOD_NAME = "testCreateWEB3TPTransactionReflector_case001()";
//        log.entering(STR_METHOD_NAME);
//        
//        try
//        {
//            BondOrderUnitRow l_bondOrderUnitRow = (BondOrderUnitRow)BondOrderUnitDao.findRowByOrderUnitId(10001);
//            BondOrderUnitParams l_bondOrderUnitParams = new BondOrderUnitParams(l_bondOrderUnitRow);
//            l_bondOrderUnitParams.setSettlementDiv("1");
//            l_bondOrderUnitParams.setOrderType(OrderTypeEnum.BOND_BUY);
//            l_bondOrderUnitParams.setOrderExecStatus("1");
//            
//            QueryProcessor l_qprocessor = Processors.getDefaultProcessor();
//            l_qprocessor.doUpdateQuery(l_bondOrderUnitParams);
//            BondOrderUnitRow l_bondOrderUnitRow1 = (BondOrderUnitRow)BondOrderUnitDao.findRowByOrderUnitId(10001);
//    
//            WEB3GentradeSubAccount l_subAccount = 
//                (WEB3GentradeSubAccount)l_finApp.getAccountManager().getSubAccount(333812512203L, 33381251220301L);
//            WEB3TPCalcCondition l_calcCondition = 
//                WEB3TPCalcCondition.createCalcConditionStandard(l_subAccount);
//            
//            WEB3TPAccountInfo l_accountInfo = WEB3TPAccountInfo.create(
//                333812512203L,
//                true);
//            
//            //現注文内容[]
//            WEB3TPNewOrderSpec[] l_newOrderSpecss = new WEB3TPNewOrderSpec[1];
//            WEB3TPNewOrderSpec l_newOrderSpecs = new WEB3TPNewOrderSpec();
//            l_newOrderSpecs.setOrderType(OrderTypeEnum.BOND_BUY);
//            l_newOrderSpecs.setDealType("35");
//            l_newOrderSpecs.setDeliveryDate(WEB3DateUtility.getDate("20060606", "yyyyMMdd"));
//            l_newOrderSpecs.setExecutedQuantity(50);
//            l_newOrderSpecs.setOrderBizDate(WEB3DateUtility.getDate("20060606", "yyyyMMdd"));
//            l_newOrderSpecs.setPaymentDate(WEB3DateUtility.getDate("20060606", "yyyyMMdd"));
//            l_newOrderSpecs.setReceivedDateTime(WEB3DateUtility.getDate("20060606", "yyyyMMdd"));
//            l_newOrderSpecs.setQuantity(500);
//            l_newOrderSpecs.setOrderUnitId(10001);
//            l_newOrderSpecs.setOrderExecStatus("0");
//            
//            l_newOrderSpecss[0] = l_newOrderSpecs;
//            l_amount.setAccountInfo(l_accountInfo);
//            l_amount.setCalcCondition(l_calcCondition);
//            l_amount.setNewOrderSpecs(l_newOrderSpecss);
//            WEB3TPTransactionReflector l_reflector = 
//                l_amount.createWEB3TPTransactionReflector(l_bondOrderUnitRow1);
//            
//            int l_intCompare = 
//                WEB3DateUtility.compareToDay(
//                    l_bondOrderUnitRow.getPaymentDate(), l_reflector.getDeliveryDate());
//            
//            assertEquals(0, l_intCompare);
//            
//            log.debug("l_reflector.getDeliveryDate()" + l_reflector.getDeliveryDate());
//            assertEquals(l_bondOrderUnitParams.getExecutedQuantity(), l_reflector.getExecutedQuantity(), 0);
//            assertEquals(l_bondOrderUnitParams.getProductId(), l_reflector.getProductId());
//            assertEquals(l_bondOrderUnitParams.getProductType(), l_reflector.getProductType());
//            log.debug("l_reflector.getReflectEndDay()" + l_reflector.getReflectEndDay());
//            log.debug("l_reflector.getReflectStartDay()" + l_reflector.getReflectStartDay());
//            assertEquals(l_bondOrderUnitRow.getTaxType(), l_reflector.getTaxType());
//            assertEquals(0, l_reflector.getUnexecutedAmount(), 0);
//            assertEquals(0, l_reflector.getUnexecutedQuantity(), 0);
//            assertEquals(-111065, l_reflector.getAmount(), 0);
//        }
//        catch (Exception l_ex)
//        {
//            log.error("Error in Exception...", l_ex);
//            fail();
//        }
//        log.exiting(STR_METHOD_NAME);
//    }
//    
//    /*
//     * 未約定の場合
//     *（引数.債券注文.get注文約定区分() == 0：未約定）
//     *（引数.債券注文.get注文種別() == 401：債券買い注文
//     */
//    public void testCreateWEB3TPTransactionReflector_case002()
//    {
//        final String STR_METHOD_NAME = "testCreateWEB3TPTransactionReflector_case002()";
//        log.entering(STR_METHOD_NAME);
//        
//        try
//        {
//            BondOrderUnitRow l_bondOrderUnitRow = (BondOrderUnitRow)BondOrderUnitDao.findRowByOrderUnitId(10001);
//            BondOrderUnitParams l_bondOrderUnitParams = new BondOrderUnitParams(l_bondOrderUnitRow);
//            l_bondOrderUnitParams.setSettlementDiv("1");
//            l_bondOrderUnitParams.setOrderExecStatus("0");
//            l_bondOrderUnitParams.setOrderType(OrderTypeEnum.BOND_BUY);
//            QueryProcessor l_qprocessor = Processors.getDefaultProcessor();
//            l_qprocessor.doUpdateQuery(l_bondOrderUnitParams);
//            BondOrderUnitRow l_bondOrderUnitRow1 = (BondOrderUnitRow)BondOrderUnitDao.findRowByOrderUnitId(10001);
//    
//            
//            WEB3GentradeSubAccount l_subAccount = 
//                (WEB3GentradeSubAccount)l_finApp.getAccountManager().getSubAccount(333812512203L, 33381251220301L);
//            WEB3TPCalcCondition l_calcCondition = 
//                WEB3TPCalcCondition.createCalcConditionStandard(l_subAccount);
//            
//            WEB3TPAccountInfo l_accountInfo = WEB3TPAccountInfo.create(
//                333812512203L,
//                true);
//            
//            //現注文内容[]
//            WEB3TPNewOrderSpec[] l_newOrderSpecss = new WEB3TPNewOrderSpec[1];
//            WEB3TPNewOrderSpec l_newOrderSpecs = new WEB3TPNewOrderSpec();
//            l_newOrderSpecs.setOrderType(OrderTypeEnum.BOND_BUY);
//            l_newOrderSpecs.setDealType("35");
//            l_newOrderSpecs.setDeliveryDate(WEB3DateUtility.getDate("20060606", "yyyyMMdd"));
//            l_newOrderSpecs.setExecutedQuantity(50);
//            l_newOrderSpecs.setOrderBizDate(WEB3DateUtility.getDate("20060606", "yyyyMMdd"));
//            l_newOrderSpecs.setPaymentDate(WEB3DateUtility.getDate("20060606", "yyyyMMdd"));
//            l_newOrderSpecs.setReceivedDateTime(WEB3DateUtility.getDate("20060606", "yyyyMMdd"));
//            l_newOrderSpecs.setQuantity(500);
//            l_newOrderSpecs.setOrderUnitId(10001);
//            l_newOrderSpecs.setOrderExecStatus("0");
//            
//            l_newOrderSpecss[0] = l_newOrderSpecs;
//            l_amount.setAccountInfo(l_accountInfo);
//            l_amount.setCalcCondition(l_calcCondition);
//            l_amount.setNewOrderSpecs(l_newOrderSpecss);
//            WEB3TPTransactionReflector l_reflector = 
//                l_amount.createWEB3TPTransactionReflector(l_bondOrderUnitRow1);
//            
//            int l_intCompare = 
//                WEB3DateUtility.compareToDay(
//                    l_bondOrderUnitRow.getPaymentDate(), l_reflector.getDeliveryDate());
//            
//            assertEquals(0, l_intCompare);
//            
//            log.debug("l_reflector.getDeliveryDate()" + l_reflector.getDeliveryDate());
//            log.debug("l_reflector.getReflectEndDay()" + l_reflector.getReflectEndDay());
//            log.debug("l_reflector.getReflectStartDay()" + l_reflector.getReflectStartDay());
//            
//            assertEquals(l_bondOrderUnitParams.getQuantity(), l_reflector.getUnexecutedQuantity(), 0);
//            assertEquals(l_bondOrderUnitParams.getProductId(), l_reflector.getProductId());
//            assertEquals(l_bondOrderUnitParams.getProductType(), l_reflector.getProductType());
//            assertEquals(l_bondOrderUnitRow.getTaxType(), l_reflector.getTaxType());
//            assertEquals(-l_bondOrderUnitRow.getEstimatedPrice(), l_reflector.getUnexecutedAmount(), 0);
//            assertEquals(0 , l_reflector.getExecutedQuantity(), 0);
//            assertEquals(0 , l_reflector.getExecutedQuantity(), 0);
//        }
//        catch (Exception l_ex)
//        {
//            log.error("Error in Exception...", l_ex);
//            fail();
//        }
//        log.exiting(STR_METHOD_NAME);
//    }
//    
//    /*
//     * 未約定の場合
//     *（引数.債券注文.get注文約定区分() == 0：未約定）
//     *（引数.債券注文.get注文種別() != 401：債券買い注文
//     */
//    public void testCreateWEB3TPTransactionReflector_case003()
//    {
//        final String STR_METHOD_NAME = "testCreateWEB3TPTransactionReflector_case003()";
//        log.entering(STR_METHOD_NAME);
//        
//        try
//        {
//            BondOrderUnitRow l_bondOrderUnitRow = (BondOrderUnitRow)BondOrderUnitDao.findRowByOrderUnitId(10001);
//            BondOrderUnitParams l_bondOrderUnitParams = new BondOrderUnitParams(l_bondOrderUnitRow);
//            l_bondOrderUnitParams.setSettlementDiv("1");
//            l_bondOrderUnitParams.setOrderExecStatus("0");
//            l_bondOrderUnitParams.setOrderType(OrderTypeEnum.BOND_SELL);
//            QueryProcessor l_qprocessor = Processors.getDefaultProcessor();
//            l_qprocessor.doUpdateQuery(l_bondOrderUnitParams);
//            BondOrderUnitRow l_bondOrderUnitRow1 = (BondOrderUnitRow)BondOrderUnitDao.findRowByOrderUnitId(10001);
//    
//            WEB3GentradeSubAccount l_subAccount = 
//                (WEB3GentradeSubAccount)l_finApp.getAccountManager().getSubAccount(333812512203L, 33381251220301L);
//            WEB3TPCalcCondition l_calcCondition = 
//                WEB3TPCalcCondition.createCalcConditionStandard(l_subAccount);
//            
//            WEB3TPAccountInfo l_accountInfo = WEB3TPAccountInfo.create(
//                333812512203L,
//                true);
//            
//            //現注文内容[]
//            WEB3TPNewOrderSpec[] l_newOrderSpecss = new WEB3TPNewOrderSpec[1];
//            WEB3TPNewOrderSpec l_newOrderSpecs = new WEB3TPNewOrderSpec();
//            l_newOrderSpecs.setOrderType(OrderTypeEnum.BOND_SELL);
//            l_newOrderSpecs.setDealType("20");
//            l_newOrderSpecs.setDeliveryDate(WEB3DateUtility.getDate("20060606", "yyyyMMdd"));
//            l_newOrderSpecs.setExecutedQuantity(50);
//            l_newOrderSpecs.setOrderBizDate(WEB3DateUtility.getDate("20060606", "yyyyMMdd"));
//            l_newOrderSpecs.setPaymentDate(WEB3DateUtility.getDate("20060606", "yyyyMMdd"));
//            l_newOrderSpecs.setReceivedDateTime(WEB3DateUtility.getDate("20060606", "yyyyMMdd"));
//            l_newOrderSpecs.setQuantity(500);
//            l_newOrderSpecs.setOrderUnitId(10001);
//            l_newOrderSpecs.setOrderExecStatus("0");
//            
//            l_newOrderSpecss[0] = l_newOrderSpecs;
//            l_amount.setAccountInfo(l_accountInfo);
//            l_amount.setCalcCondition(l_calcCondition);
//            l_amount.setNewOrderSpecs(l_newOrderSpecss);
//            WEB3TPTransactionReflector l_reflector = 
//                l_amount.createWEB3TPTransactionReflector(l_bondOrderUnitRow1);
//
//            log.debug("l_reflector.getDeliveryDate()" + l_reflector.getDeliveryDate());
//            int l_intCompare = 
//                WEB3DateUtility.compareToDay(
//                    l_bondOrderUnitRow.getDeliveryDate(), l_reflector.getDeliveryDate());
//            
//            assertEquals(0, l_intCompare);
//
//            assertEquals(l_bondOrderUnitParams.getProductId(), l_reflector.getProductId());
//            assertEquals(l_bondOrderUnitParams.getProductType(), l_reflector.getProductType());
//            log.debug("l_reflector.getReflectEndDay()" + l_reflector.getReflectEndDay());
//            log.debug("l_reflector.getReflectStartDay()" + l_reflector.getReflectStartDay());
//            assertEquals(l_bondOrderUnitRow.getTaxType(), l_reflector.getTaxType());
//            assertEquals(0, l_reflector.getUnexecutedAmount(), 0);
//            assertEquals(0, l_reflector.getUnexecutedQuantity(), 0);
//            assertEquals(0, l_reflector.getAmount(), 0);
//            assertEquals(0, l_reflector.getExecutedQuantity(), 0);
//        }
//        catch (Exception l_ex)
//        {
//            log.error("Error in Exception...", l_ex);
//            fail();
//        }
//        log.exiting(STR_METHOD_NAME);
//    }
//    
//    /*
//     * 約定済の場合
//     *（引数.債券注文.get注文約定区分() == 1：約定済）
//     *[a.募集取引の場合]
//     *（引数.債券注文.get注文種別() != 401：債券買い注文
//     *　@&&
//     * 引数.債券注文.get取引() != 35:募集取引）
//     */
//    public void testCreateWEB3TPTransactionReflector_case004()
//    {
//        final String STR_METHOD_NAME = "testCreateWEB3TPTransactionReflector_case004()";
//        log.entering(STR_METHOD_NAME);
//        
//        try
//        {
//            BondOrderUnitRow l_bondOrderUnitRow = (BondOrderUnitRow)BondOrderUnitDao.findRowByOrderUnitId(10001);
//            BondOrderUnitParams l_bondOrderUnitParams = new BondOrderUnitParams(l_bondOrderUnitRow);
//            l_bondOrderUnitParams.setSettlementDiv("1");
//            l_bondOrderUnitParams.setOrderExecStatus("1");
//            l_bondOrderUnitParams.setOrderType(OrderTypeEnum.BOND_SELL);
//            QueryProcessor l_qprocessor = Processors.getDefaultProcessor();
//            l_qprocessor.doUpdateQuery(l_bondOrderUnitParams);
//            BondOrderUnitRow l_bondOrderUnitRow1 = (BondOrderUnitRow)BondOrderUnitDao.findRowByOrderUnitId(10001);
//    
//            WEB3GentradeSubAccount l_subAccount = 
//                (WEB3GentradeSubAccount)l_finApp.getAccountManager().getSubAccount(333812512203L, 33381251220301L);
//            WEB3TPCalcCondition l_calcCondition = 
//                WEB3TPCalcCondition.createCalcConditionStandard(l_subAccount);
//            
//            WEB3TPAccountInfo l_accountInfo = WEB3TPAccountInfo.create(
//                333812512203L,
//                true);
//            
//            //現注文内容[]
//            WEB3TPNewOrderSpec[] l_newOrderSpecss = new WEB3TPNewOrderSpec[1];
//            WEB3TPNewOrderSpec l_newOrderSpecs = new WEB3TPNewOrderSpec();
//            l_newOrderSpecs.setOrderType(OrderTypeEnum.BOND_SELL);
//            l_newOrderSpecs.setDealType("20");
//            l_newOrderSpecs.setDeliveryDate(WEB3DateUtility.getDate("20060606", "yyyyMMdd"));
//            l_newOrderSpecs.setExecutedQuantity(50);
//            l_newOrderSpecs.setOrderBizDate(WEB3DateUtility.getDate("20060606", "yyyyMMdd"));
//            l_newOrderSpecs.setPaymentDate(WEB3DateUtility.getDate("20060606", "yyyyMMdd"));
//            l_newOrderSpecs.setReceivedDateTime(WEB3DateUtility.getDate("20060606", "yyyyMMdd"));
//            l_newOrderSpecs.setQuantity(500);
//            l_newOrderSpecs.setOrderUnitId(10001);
//            l_newOrderSpecs.setOrderExecStatus("0");
//            
//            l_newOrderSpecss[0] = l_newOrderSpecs;
//            l_amount.setAccountInfo(l_accountInfo);
//            l_amount.setCalcCondition(l_calcCondition);
//            l_amount.setNewOrderSpecs(l_newOrderSpecss);
//            WEB3TPTransactionReflector l_reflector = 
//                l_amount.createWEB3TPTransactionReflector(l_bondOrderUnitRow1);
//            assertEquals(111065, l_reflector.getAmount(), 0);
//            
//            int l_intCompare = 
//                WEB3DateUtility.compareToDay(
//                    l_bondOrderUnitRow.getDeliveryDate(), l_reflector.getDeliveryDate());
//            
//            assertEquals(0, l_intCompare);
//            log.debug("l_reflector.getDeliveryDate()" + l_reflector.getDeliveryDate());
//            log.debug("l_reflector.getReflectEndDay()" + l_reflector.getReflectEndDay());
//            log.debug("l_reflector.getReflectStartDay()" + l_reflector.getReflectStartDay());
//            
//            assertEquals(l_bondOrderUnitParams.getExecutedQuantity(), l_reflector.getExecutedQuantity(), 0);
//            assertEquals(l_bondOrderUnitParams.getProductId(), l_reflector.getProductId());
//            assertEquals(l_bondOrderUnitParams.getProductType(), l_reflector.getProductType());
//            assertEquals(l_bondOrderUnitRow.getTaxType(), l_reflector.getTaxType());
//            assertEquals(0, l_reflector.getUnexecutedAmount(), 0);
//            assertEquals(0, l_reflector.getUnexecutedQuantity(), 0);
//        }
//        catch (Exception l_ex)
//        {
//            log.error("Error in Exception...", l_ex);
//            fail();
//        }
//        log.exiting(STR_METHOD_NAME);
//    }
//    
//    /*
//     * get債券注文<取引代金>( )の戻り値 != null　@の場合
//     * get債券注文<取引代金>()の戻り値の要素数為1
//     */
//    public void testLoadBondTransactions_case001()
//    {
//        final String STR_METHOD_NAME = "testLoadBondTransactions_case001()";
//        log.entering(STR_METHOD_NAME);
//
//        try
//        {
//            BondOrderUnitRow l_bondOrderUnitRow = (BondOrderUnitRow)BondOrderUnitDao.findRowByOrderUnitId(10001);
//            BondOrderUnitParams l_bondOrderUnitParams = new BondOrderUnitParams(l_bondOrderUnitRow);
//            l_bondOrderUnitParams.setSettlementDiv("1");
//            l_bondOrderUnitParams.setOrderType(OrderTypeEnum.BOND_SELL);
//            QueryProcessor l_qprocessor = Processors.getDefaultProcessor();
//            l_qprocessor.doUpdateQuery(l_bondOrderUnitParams);
//    
//            WEB3GentradeSubAccount l_subAccount = 
//                (WEB3GentradeSubAccount)l_finApp.getAccountManager().getSubAccount(333812512203L, 33381251220301L);
//            WEB3TPCalcCondition l_calcCondition = 
//                WEB3TPCalcCondition.createCalcConditionStandard(l_subAccount);
//            
//            WEB3TPAccountInfo l_accountInfo = WEB3TPAccountInfo.create(
//                333812512203L,
//                true);
//            
//            //現注文内容[]
//            WEB3TPNewOrderSpec[] l_newOrderSpecss = new WEB3TPNewOrderSpec[1];
//            WEB3TPNewOrderSpec l_newOrderSpecs = new WEB3TPNewOrderSpec();
//            l_newOrderSpecs.setOrderType(OrderTypeEnum.BOND_SELL);
//            l_newOrderSpecs.setDealType("20");
//            l_newOrderSpecs.setDeliveryDate(WEB3DateUtility.getDate("20060606", "yyyyMMdd"));
//            l_newOrderSpecs.setExecutedQuantity(50);
//            l_newOrderSpecs.setOrderBizDate(WEB3DateUtility.getDate("20060606", "yyyyMMdd"));
//            l_newOrderSpecs.setPaymentDate(WEB3DateUtility.getDate("20060606", "yyyyMMdd"));
//            l_newOrderSpecs.setReceivedDateTime(WEB3DateUtility.getDate("20060606", "yyyyMMdd"));
//            l_newOrderSpecs.setQuantity(500);
//            l_newOrderSpecs.setOrderUnitId(10001);
//            l_newOrderSpecs.setOrderExecStatus("0");
//            
//            l_newOrderSpecss[0] = l_newOrderSpecs;
//            l_amount.setAccountInfo(l_accountInfo);
//            l_amount.setCalcCondition(l_calcCondition);
//            l_amount.setNewOrderSpecs(l_newOrderSpecss);
//            this.l_amount.load();
//            
//        }
//        catch (Exception l_ex)
//        {
//            log.error("Error in Exception...", l_ex);
//            fail();
//        }
//        log.exiting(STR_METHOD_NAME);
//    }
//    
//    /*
//     * get債券注文<取引代金>( )の戻り値  == null　@の場合
//     */
//    public void testLoadBondTransactions_case002()
//    {
//        final String STR_METHOD_NAME = "testLoadBondTransactions_case002()";
//        log.entering(STR_METHOD_NAME);
//
//        try
//        {
//            BondOrderUnitRow l_bondOrderUnitRow = (BondOrderUnitRow)BondOrderUnitDao.findRowByOrderUnitId(10001);
//            BondOrderUnitParams l_bondOrderUnitParams = new BondOrderUnitParams(l_bondOrderUnitRow);
//            l_bondOrderUnitParams.setSettlementDiv("0");
//            l_bondOrderUnitParams.setOrderType(OrderTypeEnum.BOND_SELL);
//            QueryProcessor l_qprocessor = Processors.getDefaultProcessor();
//            l_qprocessor.doUpdateQuery(l_bondOrderUnitParams);
//    
//            WEB3GentradeSubAccount l_subAccount = 
//                (WEB3GentradeSubAccount)l_finApp.getAccountManager().getSubAccount(333812512203L, 33381251220301L);
//            WEB3TPCalcCondition l_calcCondition = 
//                WEB3TPCalcCondition.createCalcConditionStandard(l_subAccount);
//            
//            WEB3TPAccountInfo l_accountInfo = WEB3TPAccountInfo.create(
//                333812512203L,
//                true);
//            
//            //現注文内容[]
//            WEB3TPNewOrderSpec[] l_newOrderSpecss = new WEB3TPNewOrderSpec[1];
//            WEB3TPNewOrderSpec l_newOrderSpecs = new WEB3TPNewOrderSpec();
//            l_newOrderSpecs.setOrderType(OrderTypeEnum.BOND_SELL);
//            l_newOrderSpecs.setDealType("20");
//            l_newOrderSpecs.setDeliveryDate(WEB3DateUtility.getDate("20060606", "yyyyMMdd"));
//            l_newOrderSpecs.setExecutedQuantity(50);
//            l_newOrderSpecs.setOrderBizDate(WEB3DateUtility.getDate("20060606", "yyyyMMdd"));
//            l_newOrderSpecs.setPaymentDate(WEB3DateUtility.getDate("20060606", "yyyyMMdd"));
//            l_newOrderSpecs.setReceivedDateTime(WEB3DateUtility.getDate("20060606", "yyyyMMdd"));
//            l_newOrderSpecs.setQuantity(500);
//            l_newOrderSpecs.setOrderUnitId(10001);
//            l_newOrderSpecs.setOrderExecStatus("0");
//            
//            l_newOrderSpecss[0] = l_newOrderSpecs;
//            l_amount.setAccountInfo(l_accountInfo);
//            l_amount.setCalcCondition(l_calcCondition);
//            l_amount.setNewOrderSpecs(l_newOrderSpecss);
//            this.l_amount.load();
//            
//        }
//        catch (Exception l_ex)
//        {
//            log.error("Error in Exception...", l_ex);
//            fail();
//        }
//        log.exiting(STR_METHOD_NAME);
//    }
//    
//    /*
//     * get債券注文<取引代金>( )の戻り値 != null　@の場合
//     * get債券注文<取引代金>()の戻り値の要素数為3
//     */
//    public void testLoadBondTransactions_case003()
//    {
//        final String STR_METHOD_NAME = "testLoadBondTransactions_case003()";
//        log.entering(STR_METHOD_NAME);
//
//        try
//        {
//            BondOrderUnitRow l_bongOrderUnitRow = (BondOrderUnitRow)BondOrderUnitDao.findRowByOrderUnitId(10001);
//            BondOrderUnitParams l_bondOrderUnitParams = new BondOrderUnitParams(l_bongOrderUnitRow);
//            l_bondOrderUnitParams.setSettlementDiv("1");
//            BondOrderUnitRow l_bongOrderUnitRow1 = (BondOrderUnitRow)BondOrderUnitDao.findRowByOrderUnitId(10026);
//            BondOrderUnitParams l_bondOrderUnitParams1 = new BondOrderUnitParams(l_bongOrderUnitRow1);
//            l_bondOrderUnitParams1.setSettlementDiv("1");
//            BondOrderUnitRow l_bongOrderUnitRow2 = (BondOrderUnitRow)BondOrderUnitDao.findRowByOrderUnitId(10025);
//            BondOrderUnitParams l_bondOrderUnitParams2 = new BondOrderUnitParams(l_bongOrderUnitRow2);
//            l_bondOrderUnitParams2.setSettlementDiv("1");
//            
//            QueryProcessor l_qprocessor = Processors.getDefaultProcessor();
//            l_qprocessor.doUpdateQuery(l_bondOrderUnitParams);
//            l_qprocessor.doUpdateQuery(l_bondOrderUnitParams1);
//            l_qprocessor.doUpdateQuery(l_bondOrderUnitParams2);
//            
//            WEB3GentradeSubAccount l_subAccount = 
//                (WEB3GentradeSubAccount)l_finApp.getAccountManager().getSubAccount(333812512203L, 33381251220301L);
//            WEB3TPCalcCondition l_calcCondition = 
//                WEB3TPCalcCondition.createCalcConditionStandard(l_subAccount);
//            
//            WEB3TPAccountInfo l_accountInfo = WEB3TPAccountInfo.create(
//                333812512203L,
//                true);
//            
//            //現注文内容[]
//            WEB3TPNewOrderSpec[] l_newOrderSpecss = new WEB3TPNewOrderSpec[3];
//            WEB3TPNewOrderSpec l_newOrderSpecs1 = new WEB3TPNewOrderSpec();
//            l_newOrderSpecs1.setOrderType(OrderTypeEnum.BOND_BUY);
//            l_newOrderSpecs1.setDealType("35");
//            l_newOrderSpecs1.setDeliveryDate(WEB3DateUtility.getDate("20060606", "yyyyMMdd"));
//            l_newOrderSpecs1.setExecutedQuantity(50);
//            l_newOrderSpecs1.setOrderBizDate(WEB3DateUtility.getDate("20060606", "yyyyMMdd"));
//            l_newOrderSpecs1.setPaymentDate(WEB3DateUtility.getDate("20060606", "yyyyMMdd"));
//            l_newOrderSpecs1.setReceivedDateTime(WEB3DateUtility.getDate("20060606", "yyyyMMdd"));
//            l_newOrderSpecs1.setQuantity(500);
//            l_newOrderSpecs1.setOrderUnitId(10001);
//            l_newOrderSpecs1.setOrderExecStatus("0");
//            
//            l_newOrderSpecss[0] = l_newOrderSpecs1;
//            
//            WEB3TPNewOrderSpec l_newOrderSpecs2 = new WEB3TPNewOrderSpec();
//            l_newOrderSpecs2.setOrderType(OrderTypeEnum.BOND_BUY);
//            l_newOrderSpecs2.setDealType("35");
//            l_newOrderSpecs2.setDeliveryDate(WEB3DateUtility.getDate("20060606", "yyyyMMdd"));
//            l_newOrderSpecs2.setExecutedQuantity(50);
//            l_newOrderSpecs2.setOrderBizDate(WEB3DateUtility.getDate("20060606", "yyyyMMdd"));
//            l_newOrderSpecs2.setPaymentDate(WEB3DateUtility.getDate("20060606", "yyyyMMdd"));
//            l_newOrderSpecs2.setReceivedDateTime(WEB3DateUtility.getDate("20060606", "yyyyMMdd"));
//            l_newOrderSpecs2.setQuantity(500);
//            l_newOrderSpecs2.setOrderUnitId(10001);
//            l_newOrderSpecs2.setOrderExecStatus("0");
//            
//            l_newOrderSpecss[1] = l_newOrderSpecs2;
//            
//            WEB3TPNewOrderSpec l_newOrderSpecs3 = new WEB3TPNewOrderSpec();
//            l_newOrderSpecs3.setOrderType(OrderTypeEnum.BOND_BUY);
//            l_newOrderSpecs3.setDealType("35");
//            l_newOrderSpecs3.setDeliveryDate(WEB3DateUtility.getDate("20060606", "yyyyMMdd"));
//            l_newOrderSpecs3.setExecutedQuantity(50);
//            l_newOrderSpecs3.setOrderBizDate(WEB3DateUtility.getDate("20060606", "yyyyMMdd"));
//            l_newOrderSpecs3.setPaymentDate(WEB3DateUtility.getDate("20060606", "yyyyMMdd"));
//            l_newOrderSpecs3.setReceivedDateTime(WEB3DateUtility.getDate("20060606", "yyyyMMdd"));
//            l_newOrderSpecs3.setQuantity(500);
//            l_newOrderSpecs3.setOrderUnitId(10001);
//            l_newOrderSpecs3.setOrderExecStatus("0");
//            
//            l_newOrderSpecss[2] = l_newOrderSpecs3;
//            
//            l_amount.setAccountInfo(l_accountInfo);
//            l_amount.setCalcCondition(l_calcCondition);
//            l_amount.setNewOrderSpecs(l_newOrderSpecss);
//            l_amount.load();
//            
//        }
//        catch (Exception l_ex)
//        {
//            log.error("Error in Exception...", l_ex);
//            fail();
//        }
//        log.exiting(STR_METHOD_NAME);
//    }
//    
//    /**
//     * (IPO購入申込時に余力をチェックする（get会社部店別余力計算条件()の戻り値 == 1：チェックする） かつ
//　@　@ * IPO申告.抽選結果 == 2：補欠 かつ
//　@　@ * IPO申告.購入申込区分 != 1：購入申込)
//     */
//    public void test_loadTodaysIpoTransactions_0001()
//    {
//      final String STR_METHOD_NAME = "test_loadTodaysIpoTransactions_0001()";
//      log.entering(STR_METHOD_NAME);
//        
//        WEB3TPTransactionAmountForTest l_transactionAmountForTest = new WEB3TPTransactionAmountForTest();
//        
//        Hashtable subAccountIds = new Hashtable();
////        HashMap l_map = new HashMap();
//        subAccountIds.put(new Long("1"),SubAccountTypeEnum.EQUITY_SUB_ACCOUNT);
//        WEB3TPAccountInfo l_assetValuation = new WEB3TPAccountInfo();
//        l_assetValuation.setAccountId(22);
//        
//        l_assetValuation.setSubAccountIds(subAccountIds);
//        
//        l_transactionAmountForTest.setAccountInfo(l_assetValuation);
//        
//        WEB3TPCalcConditionForTest l_calcConditionForTest = new WEB3TPCalcConditionForTest();
//        Date[] l_dat = {new Date(),GtlUtils.getTradingSystem().getBizDate()};
//        l_calcConditionForTest.setBizDate(l_dat);
//        
//        l_calcConditionForTest.addInstBranCalcCondition("ipo.offer.tradingpower.check","1");
//        l_transactionAmountForTest.setCalcCondition(l_calcConditionForTest);
//        
//        try
//        {
//            TestDBUtility.deleteAll(IpoOrderParams.TYPE);
//            IpoOrderParams l_ipoOrderParams = new IpoOrderParams();
//            l_ipoOrderParams.setIpoOrderId(123);
//            l_ipoOrderParams.setIpoProductId(321);
//            l_ipoOrderParams.setBranchId(11);
//            l_ipoOrderParams.setAccountId(22);
//            l_ipoOrderParams.setSubAccountId(1);
//            l_ipoOrderParams.setLastOrderActionSerialNo(9);
//            l_ipoOrderParams.setQuantity(9.5);
//            l_ipoOrderParams.setLimitPrice(6.2);
//            l_ipoOrderParams.setOrderStatus(OrderStatusEnum.NOT_ORDERED);
//            l_ipoOrderParams.setOrderOpenStatus(OrderOpenStatusEnum.OPEN);
//            
//            //IPO申告.購入申込区分 != 1：購入申込
//            l_ipoOrderParams.setOfferingDiv(WEB3TPIPOOfferingDivDef.DEFAULT);
//            
//            //IPO申告.抽選結果 == 2：補欠
//            l_ipoOrderParams.setLotResult(WEB3TPIPOLotResultTypeDef.SUBSTITUTE);
//            /**/
//            l_ipoOrderParams.setLotResultRetry(WEB3TPIPOLotResultTypeDef.DEFAULT);
//            
//            //IPO申告.受付状態が"2:SONAR反映済"
//            l_ipoOrderParams.setAcceptStatus("2");
//            
//            TestDBUtility.insertWithDel(l_ipoOrderParams);
//            
//            
//            TestDBUtility.deleteAll(IpoProductParams.TYPE);
//            IpoProductParams l_ipoProductParams = new IpoProductParams();
//            l_ipoProductParams.setIpoProductId(321);
//            l_ipoProductParams.setInstitutionCode("321");
//            l_ipoProductParams.setProductCode("11");
//            l_ipoProductParams.setProductType(ProductTypeEnum.IPO);
//            l_ipoProductParams.setIpoRegistDiv("1");
//            l_ipoProductParams.setIpoRegistDetailDiv("9");
//            l_ipoProductParams.setPublicMarket("2");
//            l_ipoProductParams.setProvisionalValueDiv("4");
//            
//            //IPO銘柄.購入申込終了日(目論見書記載) != null
//            //IPO銘柄.購入申込終了日(目論見書記載) > 営業日(T+0) 
//            l_ipoProductParams.setOfferEndDateProspec(WEB3DateUtility.getDate("20090909","yyyyMMdd"));
//            //IPO銘柄.削除フラグ=FALSE
//            l_ipoProductParams.setDeleteFlag(BooleanEnum.FALSE);
//            
//            TestDBUtility.insertWithDel(l_ipoProductParams);
//        }
//        catch (WEB3BaseException e)
//        {
//            log.exiting(STR_METHOD_NAME);
//            fail();
//        }
//        
//        l_transactionAmountForTest.load();
//        List l_lst = l_transactionAmountForTest.getTodaysTransactions();
//        
//        assertEquals("0","" + l_lst.size());
//        
//        log.exiting(STR_METHOD_NAME);
//    }
//    
//    
//    /**
//     * (IPO購入申込時に余力をチェックする（get会社部店別余力計算条件()の戻り値 == 1：チェックする） かつ
//　@　@ * IPO申告.抽選結果 == 2：補欠 かつ
//　@　@ * IPO申告.購入申込区分 != 1：購入申込) *以外
//     * || 
//     * IPO申告.受付状態が"2:SONAR反映済"以外
//     */
//    public void test_loadTodaysIpoTransactions_0002()
//    {
//      final String STR_METHOD_NAME = "test_loadTodaysIpoTransactions_0002()";
//      log.entering(STR_METHOD_NAME);
//        
//        WEB3TPTransactionAmountForTest l_transactionAmountForTest = new WEB3TPTransactionAmountForTest();
//        
//        Hashtable subAccountIds = new Hashtable();
////        HashMap l_map = new HashMap();
//        subAccountIds.put(new Long("1"),SubAccountTypeEnum.EQUITY_SUB_ACCOUNT);
//        WEB3TPAccountInfo l_assetValuation = new WEB3TPAccountInfo();
//        l_assetValuation.setAccountId(22);
//        
//        l_assetValuation.setSubAccountIds(subAccountIds);
//        
//        l_transactionAmountForTest.setAccountInfo(l_assetValuation);
//        
//        WEB3TPCalcConditionForTest l_calcConditionForTest = new WEB3TPCalcConditionForTest();
//        Date[] l_dat = {new Date(),GtlUtils.getTradingSystem().getBizDate()};
//        l_calcConditionForTest.setBizDate(l_dat);
//        
//        l_calcConditionForTest.addInstBranCalcCondition("ipo.offer.tradingpower.check","1");
//        l_transactionAmountForTest.setCalcCondition(l_calcConditionForTest);
//        
//        try
//        {
//            TestDBUtility.deleteAll(IpoOrderParams.TYPE);
//            IpoOrderParams l_ipoOrderParams = new IpoOrderParams();
//            l_ipoOrderParams.setIpoOrderId(123);
//            l_ipoOrderParams.setIpoProductId(321);
//            l_ipoOrderParams.setBranchId(11);
//            l_ipoOrderParams.setAccountId(22);
//            l_ipoOrderParams.setSubAccountId(1);
//            l_ipoOrderParams.setLastOrderActionSerialNo(9);
//            l_ipoOrderParams.setQuantity(9.5);
//            l_ipoOrderParams.setLimitPrice(6.2);
//            l_ipoOrderParams.setOrderStatus(OrderStatusEnum.NOT_ORDERED);
//            l_ipoOrderParams.setOrderOpenStatus(OrderOpenStatusEnum.OPEN);
//            
//            //IPO申告.購入申込区分 != 1：購入申込
//            l_ipoOrderParams.setOfferingDiv(WEB3TPIPOOfferingDivDef.DEFAULT);
//            
//            //IPO申告.抽選結果 != 2：補欠
//            l_ipoOrderParams.setLotResult(WEB3TPIPOLotResultTypeDef.WIN);
//            /**/
//            l_ipoOrderParams.setLotResultRetry(WEB3TPIPOLotResultTypeDef.DEFAULT);
//            
//            //IPO申告.受付状態が"2:SONAR反映済"以外
//            l_ipoOrderParams.setAcceptStatus("1");
//            
//            TestDBUtility.insertWithDel(l_ipoOrderParams);
//            
//            
//            TestDBUtility.deleteAll(IpoProductParams.TYPE);
//            IpoProductParams l_ipoProductParams = new IpoProductParams();
//            l_ipoProductParams.setIpoProductId(321);
//            l_ipoProductParams.setInstitutionCode("321");
//            l_ipoProductParams.setProductCode("11");
//            l_ipoProductParams.setProductType(ProductTypeEnum.IPO);
//            l_ipoProductParams.setIpoRegistDiv("1");
//            l_ipoProductParams.setIpoRegistDetailDiv("9");
//            l_ipoProductParams.setPublicMarket("2");
//            l_ipoProductParams.setProvisionalValueDiv("4");
//            
//            //IPO銘柄.購入申込終了日(目論見書記載) != null
//            //IPO銘柄.購入申込終了日(目論見書記載) > 営業日(T+0) 
//            l_ipoProductParams.setOfferEndDateProspec(WEB3DateUtility.getDate("20090909","yyyyMMdd"));
//            //IPO銘柄.削除フラグ=FALSE
//            l_ipoProductParams.setDeleteFlag(BooleanEnum.FALSE);
//            
//            TestDBUtility.insertWithDel(l_ipoProductParams);
//        }
//        catch (WEB3BaseException e)
//        {
//            log.exiting(STR_METHOD_NAME);
//            fail();
//        }
//        
//        l_transactionAmountForTest.load();
//        List l_lst = l_transactionAmountForTest.getTodaysTransactions();
//        
//        assertEquals("0","" + l_lst.size());
//        
//        log.exiting(STR_METHOD_NAME);
//    }
//    
//    /**
//     * (IPO購入申込時に余力をチェックする（get会社部店別余力計算条件()の戻り値 == 1：チェックする） かつ
//　@　@ * IPO申告.抽選結果 == 2：補欠 かつ
//　@　@ * IPO申告.購入申込区分 != 1：購入申込) *以外
//     * || 
//     * IPO申告.受付状態が"2:SONAR反映済"以外 *
//     * ||
//     * IPO銘柄.購入申込終了日(目論見書記載) == null 
//     */
//    public void test_loadTodaysIpoTransactions_0003()
//    {
//      final String STR_METHOD_NAME = "test_loadTodaysIpoTransactions_0003()";
//      log.entering(STR_METHOD_NAME);
//        
//        WEB3TPTransactionAmountForTest l_transactionAmountForTest = new WEB3TPTransactionAmountForTest();
//        
//        Hashtable subAccountIds = new Hashtable();
////        HashMap l_map = new HashMap();
//        subAccountIds.put(new Long("1"),SubAccountTypeEnum.EQUITY_SUB_ACCOUNT);
//        WEB3TPAccountInfo l_assetValuation = new WEB3TPAccountInfo();
//        l_assetValuation.setAccountId(22);
//        
//        l_assetValuation.setSubAccountIds(subAccountIds);
//        
//        l_transactionAmountForTest.setAccountInfo(l_assetValuation);
//        
//        WEB3TPCalcConditionForTest l_calcConditionForTest = new WEB3TPCalcConditionForTest();
//        Date[] l_dat = {new Date(),GtlUtils.getTradingSystem().getBizDate()};
//        l_calcConditionForTest.setBizDate(l_dat);
//        
//        l_calcConditionForTest.addInstBranCalcCondition("ipo.offer.tradingpower.check","1");
//        l_transactionAmountForTest.setCalcCondition(l_calcConditionForTest);
//        
//        try
//        {
//            TestDBUtility.deleteAll(IpoOrderParams.TYPE);
//            IpoOrderParams l_ipoOrderParams = new IpoOrderParams();
//            l_ipoOrderParams.setIpoOrderId(123);
//            l_ipoOrderParams.setIpoProductId(321);
//            l_ipoOrderParams.setBranchId(11);
//            l_ipoOrderParams.setAccountId(22);
//            l_ipoOrderParams.setSubAccountId(1);
//            l_ipoOrderParams.setLastOrderActionSerialNo(9);
//            l_ipoOrderParams.setQuantity(9.5);
//            l_ipoOrderParams.setLimitPrice(6.2);
//            l_ipoOrderParams.setOrderStatus(OrderStatusEnum.NOT_ORDERED);
//            l_ipoOrderParams.setOrderOpenStatus(OrderOpenStatusEnum.OPEN);
//            
//            //IPO申告.購入申込区分 == 1：購入申込
//            l_ipoOrderParams.setOfferingDiv(WEB3TPIPOOfferingDivDef.OFFERING);
//            
//            //IPO申告.抽選結果 == 2：補欠
//            l_ipoOrderParams.setLotResult(WEB3TPIPOLotResultTypeDef.SUBSTITUTE);
//            /**/
//            l_ipoOrderParams.setLotResultRetry(WEB3TPIPOLotResultTypeDef.DEFAULT);
//            
//            //IPO申告.受付状態が"2:SONAR反映済"以外
//            l_ipoOrderParams.setAcceptStatus("2");
//            
//            TestDBUtility.insertWithDel(l_ipoOrderParams);
//            
//            
//            TestDBUtility.deleteAll(IpoProductParams.TYPE);
//            IpoProductParams l_ipoProductParams = new IpoProductParams();
//            l_ipoProductParams.setIpoProductId(321);
//            l_ipoProductParams.setInstitutionCode("321");
//            l_ipoProductParams.setProductCode("11");
//            l_ipoProductParams.setProductType(ProductTypeEnum.IPO);
//            l_ipoProductParams.setIpoRegistDiv("1");
//            l_ipoProductParams.setIpoRegistDetailDiv("9");
//            l_ipoProductParams.setPublicMarket("2");
//            l_ipoProductParams.setProvisionalValueDiv("4");
//            
//            //IPO銘柄.購入申込終了日(目論見書記載) != null
//            //IPO銘柄.購入申込終了日(目論見書記載) > 営業日(T+0) 
////            l_ipoProductParams.setOfferEndDateProspec(WEB3DateUtility.getDate("20090909","yyyyMMdd"));
//            //IPO銘柄.削除フラグ=FALSE
//            l_ipoProductParams.setDeleteFlag(BooleanEnum.FALSE);
//            
//            TestDBUtility.insertWithDel(l_ipoProductParams);
//        }
//        catch (WEB3BaseException e)
//        {
//            log.exiting(STR_METHOD_NAME);
//            fail();
//        }
//        
//        l_transactionAmountForTest.load();
//        List l_lst = l_transactionAmountForTest.getTodaysTransactions();
//        
//        assertEquals("0","" + l_lst.size());
//        
//        log.exiting(STR_METHOD_NAME);
//    }
//    
//    /**
//     * (IPO購入申込時に余力をチェックする（get会社部店別余力計算条件()の戻り値 == 1：チェックする） かつ
//　@　@ * IPO申告.抽選結果 == 2：補欠 かつ
//　@　@ * IPO申告.購入申込区分 != 1：購入申込) *以外
//     * || 
//     * IPO申告.受付状態が"2:SONAR反映済"以外 *
//     * ||
//     * IPO銘柄.購入申込終了日(目論見書記載) != null *
//     * ||
//     * IPO銘柄.購入申込終了日(目論見書記載) < 営業日(T+0) 
//     */
//    public void test_loadTodaysIpoTransactions_0004()
//    {
//      final String STR_METHOD_NAME = "test_loadTodaysIpoTransactions_0004()";
//      log.entering(STR_METHOD_NAME);
//        
//        WEB3TPTransactionAmountForTest l_transactionAmountForTest = new WEB3TPTransactionAmountForTest();
//        
//        Hashtable subAccountIds = new Hashtable();
////        HashMap l_map = new HashMap();
//        subAccountIds.put(new Long("1"),SubAccountTypeEnum.EQUITY_SUB_ACCOUNT);
//        WEB3TPAccountInfo l_assetValuation = new WEB3TPAccountInfo();
//        l_assetValuation.setAccountId(22);
//        
//        l_assetValuation.setSubAccountIds(subAccountIds);
//        
//        l_transactionAmountForTest.setAccountInfo(l_assetValuation);
//        
//        WEB3TPCalcConditionForTest l_calcConditionForTest = new WEB3TPCalcConditionForTest();
//        Date[] l_dat = {new Date(),GtlUtils.getTradingSystem().getBizDate()};
//        l_calcConditionForTest.setBizDate(l_dat);
//        
//        l_calcConditionForTest.addInstBranCalcCondition("ipo.offer.tradingpower.check","1");
//        l_transactionAmountForTest.setCalcCondition(l_calcConditionForTest);
//        
//        try
//        {
//            TestDBUtility.deleteAll(IpoOrderParams.TYPE);
//            IpoOrderParams l_ipoOrderParams = new IpoOrderParams();
//            l_ipoOrderParams.setIpoOrderId(123);
//            l_ipoOrderParams.setIpoProductId(321);
//            l_ipoOrderParams.setBranchId(11);
//            l_ipoOrderParams.setAccountId(22);
//            l_ipoOrderParams.setSubAccountId(1);
//            l_ipoOrderParams.setLastOrderActionSerialNo(9);
//            l_ipoOrderParams.setQuantity(9.5);
//            l_ipoOrderParams.setLimitPrice(6.2);
//            l_ipoOrderParams.setOrderStatus(OrderStatusEnum.NOT_ORDERED);
//            l_ipoOrderParams.setOrderOpenStatus(OrderOpenStatusEnum.OPEN);
//            
//            //IPO申告.購入申込区分 == 1：購入申込
//            l_ipoOrderParams.setOfferingDiv(WEB3TPIPOOfferingDivDef.OFFERING);
//            
//            //IPO申告.抽選結果 == 2：補欠
//            l_ipoOrderParams.setLotResult(WEB3TPIPOLotResultTypeDef.SUBSTITUTE);
//            /**/
//            l_ipoOrderParams.setLotResultRetry(WEB3TPIPOLotResultTypeDef.DEFAULT);
//            
//            //IPO申告.受付状態が"2:SONAR反映済"以外
//            l_ipoOrderParams.setAcceptStatus("2");
//            
//            TestDBUtility.insertWithDel(l_ipoOrderParams);
//            
//            
//            TestDBUtility.deleteAll(IpoProductParams.TYPE);
//            IpoProductParams l_ipoProductParams = new IpoProductParams();
//            l_ipoProductParams.setIpoProductId(321);
//            l_ipoProductParams.setInstitutionCode("321");
//            l_ipoProductParams.setProductCode("11");
//            l_ipoProductParams.setProductType(ProductTypeEnum.IPO);
//            l_ipoProductParams.setIpoRegistDiv("1");
//            l_ipoProductParams.setIpoRegistDetailDiv("9");
//            l_ipoProductParams.setPublicMarket("2");
//            l_ipoProductParams.setProvisionalValueDiv("4");
//            
//            //IPO銘柄.購入申込終了日(目論見書記載) != null
//            //IPO銘柄.購入申込終了日(目論見書記載) < 営業日(T+0) 
//            l_ipoProductParams.setOfferEndDateProspec(WEB3DateUtility.getDate("20040909","yyyyMMdd"));
//            //IPO銘柄.削除フラグ=FALSE
//            l_ipoProductParams.setDeleteFlag(BooleanEnum.FALSE);
//            
//            TestDBUtility.insertWithDel(l_ipoProductParams);
//        }
//        catch (WEB3BaseException e)
//        {
//            log.exiting(STR_METHOD_NAME);
//            fail();
//        }
//        
//        l_transactionAmountForTest.load();
//        List l_lst = l_transactionAmountForTest.getTodaysTransactions();
//        
//        assertEquals("0","" + l_lst.size());
//        
//        log.exiting(STR_METHOD_NAME);
//    }
//    
//    /**
//     * (IPO購入申込時に余力をチェックする（get会社部店別余力計算条件()の戻り値 == 1：チェックする） かつ
//　@　@ * IPO申告.抽選結果 == 2：補欠 かつ
//　@　@ * IPO申告.購入申込区分 != 1：購入申込) *以外
//     * || 
//     * IPO申告.受付状態が"2:SONAR反映済"以外 *
//     * ||
//     * IPO銘柄.購入申込終了日(目論見書記載) != null *
//     * ||
//     * IPO銘柄.購入申込終了日(目論見書記載) < 営業日(T+0) *
//     * ||
//     * 取得したIPO銘柄.削除フラグ=TRUE
//     */
//    public void test_loadTodaysIpoTransactions_0005()
//    {
//      final String STR_METHOD_NAME = "test_loadTodaysIpoTransactions_0005()";
//      log.entering(STR_METHOD_NAME);
//        
//        WEB3TPTransactionAmountForTest l_transactionAmountForTest = new WEB3TPTransactionAmountForTest();
//        
//        Hashtable subAccountIds = new Hashtable();
////        HashMap l_map = new HashMap();
//        subAccountIds.put(new Long("1"),SubAccountTypeEnum.EQUITY_SUB_ACCOUNT);
//        WEB3TPAccountInfo l_assetValuation = new WEB3TPAccountInfo();
//        l_assetValuation.setAccountId(22);
//        
//        l_assetValuation.setSubAccountIds(subAccountIds);
//        
//        l_transactionAmountForTest.setAccountInfo(l_assetValuation);
//        
//        WEB3TPCalcConditionForTest l_calcConditionForTest = new WEB3TPCalcConditionForTest();
//        Date[] l_dat = {new Date(),GtlUtils.getTradingSystem().getBizDate()};
//        l_calcConditionForTest.setBizDate(l_dat);
//        
//        l_calcConditionForTest.addInstBranCalcCondition("ipo.offer.tradingpower.check","1");
//        l_transactionAmountForTest.setCalcCondition(l_calcConditionForTest);
//        
//        try
//        {
//            TestDBUtility.deleteAll(IpoOrderParams.TYPE);
//            IpoOrderParams l_ipoOrderParams = new IpoOrderParams();
//            l_ipoOrderParams.setIpoOrderId(123);
//            l_ipoOrderParams.setIpoProductId(321);
//            l_ipoOrderParams.setBranchId(11);
//            l_ipoOrderParams.setAccountId(22);
//            l_ipoOrderParams.setSubAccountId(1);
//            l_ipoOrderParams.setLastOrderActionSerialNo(9);
//            l_ipoOrderParams.setQuantity(9.5);
//            l_ipoOrderParams.setLimitPrice(6.2);
//            l_ipoOrderParams.setOrderStatus(OrderStatusEnum.NOT_ORDERED);
//            l_ipoOrderParams.setOrderOpenStatus(OrderOpenStatusEnum.OPEN);
//            
//            //IPO申告.購入申込区分 == 1：購入申込
//            l_ipoOrderParams.setOfferingDiv(WEB3TPIPOOfferingDivDef.OFFERING);
//            
//            //IPO申告.抽選結果 == 2：補欠
//            l_ipoOrderParams.setLotResult(WEB3TPIPOLotResultTypeDef.SUBSTITUTE);
//            /**/
//            l_ipoOrderParams.setLotResultRetry(WEB3TPIPOLotResultTypeDef.DEFAULT);
//            
//            //IPO申告.受付状態が"2:SONAR反映済"以外
//            l_ipoOrderParams.setAcceptStatus("2");
//            
//            TestDBUtility.insertWithDel(l_ipoOrderParams);
//            
//            
//            TestDBUtility.deleteAll(IpoProductParams.TYPE);
//            IpoProductParams l_ipoProductParams = new IpoProductParams();
//            l_ipoProductParams.setIpoProductId(321);
//            l_ipoProductParams.setInstitutionCode("321");
//            l_ipoProductParams.setProductCode("11");
//            l_ipoProductParams.setProductType(ProductTypeEnum.IPO);
//            l_ipoProductParams.setIpoRegistDiv("1");
//            l_ipoProductParams.setIpoRegistDetailDiv("9");
//            l_ipoProductParams.setPublicMarket("2");
//            l_ipoProductParams.setProvisionalValueDiv("4");
//            
//            //IPO銘柄.購入申込終了日(目論見書記載) != null
//            //IPO銘柄.購入申込終了日(目論見書記載) > 営業日(T+0) 
//            l_ipoProductParams.setOfferEndDateProspec(WEB3DateUtility.getDate("20090909","yyyyMMdd"));
//            //IPO銘柄.削除フラグ=TRUE
//            l_ipoProductParams.setDeleteFlag(BooleanEnum.TRUE);
//            
//            TestDBUtility.insertWithDel(l_ipoProductParams);
//        }
//        catch (WEB3BaseException e)
//        {
//            log.exiting(STR_METHOD_NAME);
//            fail();
//        }
//        
//        l_transactionAmountForTest.load();
//        List l_lst = l_transactionAmountForTest.getTodaysTransactions();
//        
//        assertEquals("0","" + l_lst.size());
//        
//        log.exiting(STR_METHOD_NAME);
//    }
//    
//    /**
//     * (IPO購入申込時に余力をチェックする（get会社部店別余力計算条件()の戻り値 == 1：チェックする） かつ
//　@　@ * IPO申告.抽選結果 == 2：補欠 かつ
//　@　@ * IPO申告.購入申込区分 != 1：購入申込) *以外
//     * || 
//     * IPO申告.受付状態が"2:SONAR反映済"以外 *
//     * ||
//     * IPO銘柄.購入申込終了日(目論見書記載) != null *
//     * ||
//     * IPO銘柄.購入申込終了日(目論見書記載) < 営業日(T+0) *
//     * ||
//     * 取得したIPO銘柄.削除フラグ=TRUE*
//     */
//    public void test_loadTodaysIpoTransactions_0006()
//    {
//      final String STR_METHOD_NAME = "test_loadTodaysIpoTransactions_0006()";
//      log.entering(STR_METHOD_NAME);
//        
//        WEB3TPTransactionAmountForTest l_transactionAmountForTest = new WEB3TPTransactionAmountForTest();
//        
//        Hashtable subAccountIds = new Hashtable();
////        HashMap l_map = new HashMap();
//        subAccountIds.put(new Long("1"),SubAccountTypeEnum.EQUITY_SUB_ACCOUNT);
//        WEB3TPAccountInfo l_assetValuation = new WEB3TPAccountInfo();
//        l_assetValuation.setAccountId(22);
//        
//        l_assetValuation.setSubAccountIds(subAccountIds);
//        
//        l_transactionAmountForTest.setAccountInfo(l_assetValuation);
//        
//        WEB3TPCalcConditionForTest l_calcConditionForTest = new WEB3TPCalcConditionForTest();
//        Date[] l_dat = {new Date(),GtlUtils.getTradingSystem().getBizDate()};
//        l_calcConditionForTest.setBizDate(l_dat);
//        
//        l_calcConditionForTest.addInstBranCalcCondition("ipo.offer.tradingpower.check","1");
//        l_transactionAmountForTest.setCalcCondition(l_calcConditionForTest);
//        
//        try
//        {
//            TestDBUtility.deleteAll(IpoOrderParams.TYPE);
//            IpoOrderParams l_ipoOrderParams = new IpoOrderParams();
//            l_ipoOrderParams.setIpoOrderId(123);
//            l_ipoOrderParams.setIpoProductId(321);
//            l_ipoOrderParams.setBranchId(11);
//            l_ipoOrderParams.setAccountId(22);
//            l_ipoOrderParams.setSubAccountId(1);
//            l_ipoOrderParams.setLastOrderActionSerialNo(9);
//            l_ipoOrderParams.setQuantity(9.5);
//            l_ipoOrderParams.setLimitPrice(6.2);
//            l_ipoOrderParams.setOrderStatus(OrderStatusEnum.NOT_ORDERED);
//            l_ipoOrderParams.setOrderOpenStatus(OrderOpenStatusEnum.OPEN);
//            
//            //IPO申告.購入申込区分 == 1：購入申込
//            l_ipoOrderParams.setOfferingDiv(WEB3TPIPOOfferingDivDef.OFFERING);
//            
//            //IPO申告.抽選結果 == 2：補欠
//            l_ipoOrderParams.setLotResult(WEB3TPIPOLotResultTypeDef.SUBSTITUTE);
//            /**/
//            l_ipoOrderParams.setLotResultRetry(WEB3TPIPOLotResultTypeDef.DEFAULT);
//            
//            //IPO申告.受付状態が"2:SONAR反映済"以外
//            l_ipoOrderParams.setAcceptStatus("2");
//            
//            TestDBUtility.insertWithDel(l_ipoOrderParams);
//            
//            
//            TestDBUtility.deleteAll(IpoProductParams.TYPE);
//            IpoProductParams l_ipoProductParams = new IpoProductParams();
//            l_ipoProductParams.setIpoProductId(321);
//            l_ipoProductParams.setInstitutionCode("321");
//            l_ipoProductParams.setProductCode("11");
//            l_ipoProductParams.setProductType(ProductTypeEnum.IPO);
//            l_ipoProductParams.setIpoRegistDiv("1");
//            l_ipoProductParams.setIpoRegistDetailDiv("9");
//            l_ipoProductParams.setPublicMarket("2");
//            l_ipoProductParams.setProvisionalValueDiv("4");
//            
//            //IPO銘柄.購入申込終了日(目論見書記載) != null
//            //IPO銘柄.購入申込終了日(目論見書記載) > 営業日(T+0) 
//            l_ipoProductParams.setOfferEndDateProspec(WEB3DateUtility.getDate("20090909","yyyyMMdd"));
//            //IPO銘柄.削除フラグ=FALSE
//            l_ipoProductParams.setDeleteFlag(BooleanEnum.FALSE);
//            
//            TestDBUtility.insertWithDel(l_ipoProductParams);
//        }
//        catch (WEB3BaseException e)
//        {
//            log.exiting(STR_METHOD_NAME);
//            fail();
//        }
//        
//        l_transactionAmountForTest.load();
//        List l_lst = l_transactionAmountForTest.getTodaysTransactions();
//        
//        assertEquals("1","" + l_lst.size());
//        
//        log.exiting(STR_METHOD_NAME);
//    }
//    
//    class WEB3TPTransactionAmountForTest extends WEB3TPTransactionAmount
//    {
//        private List todaysTransactions = new ArrayList();
//        
//        public void load()
//        {
//            this.loadTodaysIpoTransactions();
//        }
//        
//        protected WEB3TPTransactionReflector createWEB3TPTransactionReflector(
//                IpoOrderRow l_ipoOrderRow,IpoProductRow l_ipoProductRow)
//        {
//            WEB3TPTransactionReflector l_TPTransactionReflector = new WEB3TPTransactionReflector();
//            
//            return l_TPTransactionReflector;
//        }
//        
//        protected void addTodaysTransaction(WEB3TPTransactionReflector l_transaction)
//        {
//            if (l_transaction instanceof WEB3TPTransactionReflector)
//            {
//                todaysTransactions.add(l_transaction);
//            }
//        }
//        
//        public List getTodaysTransactions()
//        {
//            return todaysTransactions;
//        }
//    }
//    
//    class WEB3TPCalcConditionForTest extends WEB3TPCalcCondition
//    {
//        /**
//         * (会社部店別余力計算条件)
//         * 
//         * 会社部店毎の余力計算条件を格納するMap
//         */
//        private Map instBranCalcCondition;
//        
//        public WEB3TPCalcConditionForTest()
//        {
//            this.instBranCalcCondition = new HashMap();
//        }
//        /**
//         * (get会社部店別余力計算条件)<BR>
//         * 
//         * 引数.プリファ@レンス名に対応する値をマップより検索し返却する。
//         * ※レコードが存在しない場合、nullを返却する。
//         * 
//         * @@param l_strName - (プリファ@レンス名)
//         * @@return String
//         */
//        public String getInstBranCalcCondition(String l_strName)
//        {
//            boolean l_isRegistered = instBranCalcCondition.containsKey(l_strName);
//
//            //値
//            String l_strValue = null;
//            
//            //登録済の場合
//            if(l_isRegistered == true)
//            {
//                l_strValue = (String)instBranCalcCondition.get(l_strName);
//            }
//
//            return l_strValue;
//        }
//
//        /**
//         * (add会社部店別余力計算条件)<BR>
//         * <BR>
//         * １）引数.プリファ@レンス名をキーとして引数.値を<BR>
//         * Map（this.会社部店別余力計算条件）にセットする。<BR>
//         * <BR>
//         * 　@-this.会社部店別余力計算条件.put()をコール<BR>
//         * <BR>
//         * 　@[引数]<BR>
//         * 　@　@Object：　@引数.プリファ@レンス名<BR>
//         * 　@　@Object：　@引数.値<BR>
//         * <BR>
//         * @@param l_strName - (プリファ@レンス名)
//         * @@param l_strValue - (値)
//         */
//        protected void addInstBranCalcCondition(String l_strName, String l_strValue) 
//        {
//            instBranCalcCondition.put(l_strName, l_strValue);
//        }
//    }
}
@
