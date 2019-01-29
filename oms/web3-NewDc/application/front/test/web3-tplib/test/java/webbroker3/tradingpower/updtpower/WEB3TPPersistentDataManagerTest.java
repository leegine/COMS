head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.04.07.02.24.33;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	5444d9d078c63f4;
filename	WEB3TPPersistentDataManagerTest.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : //TODO(WEB3TPPersistentDataManagerTest.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2006/09/27 徐宏偉 (中訊) 新規作成
*/
package webbroker3.tradingpower.updtpower;

import java.sql.Timestamp;
import java.util.Date;
import java.util.List;

import com.fitechlabs.xtrade.kernel.boot.Services;
import com.fitechlabs.xtrade.kernel.data.Processors;
import com.fitechlabs.xtrade.kernel.data.QueryProcessor;
import com.fitechlabs.xtrade.plugin.tc.gentrade.BooleanEnum;
import com.fitechlabs.xtrade.plugin.tc.gentrade.FinApp;
import com.fitechlabs.xtrade.plugin.tc.gentrade.OrderStatusEnum;
import com.fitechlabs.xtrade.plugin.tc.gentrade.OrderTypeEnum;
import com.fitechlabs.xtrade.plugin.tc.gentrade.ProductTypeEnum;
import com.fitechlabs.xtrade.plugin.tc.gentrade.SubAccountTypeEnum;
import com.fitechlabs.xtrade.plugin.tc.xbaio.AssetTransferTypeEnum;
import com.fitechlabs.xtrade.plugin.tc.xbaio.data.AioOrderUnitParams;
import com.fitechlabs.xtrade.plugin.tc.xbaio.data.AioOrderUnitRow;
import com.fitechlabs.xtrade.plugin.tc.xbfeq.data.FeqProductParams;
import com.fitechlabs.xtrade.plugin.tc.xbfeq.data.FeqProductRow;

import test.util.TestDBUtility;

import webbroker3.common.WEB3BaseRuntimeException;
import webbroker3.gentrade.data.GenCurrencyParams;
import webbroker3.gentrade.data.GenCurrencyRow;
import webbroker3.mock.TestBaseForMock;
import webbroker3.tradingpower.WEB3TPCalcCondition;
import webbroker3.tradingpower.data.TpCashBalanceParams;
import webbroker3.tradingpower.data.TpCashBalanceRow;
import webbroker3.tradingpower.data.TpOtherTempRestraintDao;
import webbroker3.tradingpower.data.TpOtherTempRestraintParams;
import webbroker3.tradingpower.data.TpOtherTempRestraintRow;
import webbroker3.tradingpower.updtpower.cash.WEB3TPCashBalance;
import webbroker3.tradingpower.updtpower.cash.WEB3TPTransactionAmount;
import webbroker3.util.WEB3DateUtility;
import webbroker3.util.WEB3LogUtility;

/**
 * XXXXXXクラス//TODO
 *
 * @@author 徐宏偉(中訊)
 * @@version 1.0
 */
public class WEB3TPPersistentDataManagerTest extends TestBaseForMock
{

    private static WEB3LogUtility log = 
        WEB3LogUtility.getInstance(WEB3TPPersistentDataManagerTest.class);
    
    private WEB3TPTransactionAmount l_amount = new WEB3TPTransactionAmount();
    
    private FinApp l_finApp = (FinApp)Services.getService(FinApp.class);
    
    private WEB3TPCashBalance l_tpcashBalance = new WEB3TPCashBalance();
    
    public WEB3TPPersistentDataManagerTest(String arg0)
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
        doClearTradingClendarContext();
        super.tearDown();
    }

//    /*
//     * [a.現物顧客の場合]
//     * 顧客属性".is信用顧客() == false
//     */
//    public void testGetBondOrdersAmount_case001()
//    {
//        final String STR_METHOD_NAME = "testGetBondOrdersAmount_case001()";
//        log.entering(STR_METHOD_NAME);
//        
//        try
//        {
//            BondOrderUnitRow l_bongOrderUnitRow = (BondOrderUnitRow)BondOrderUnitDao.findRowByOrderUnitId(10001);
//            BondOrderUnitParams l_bondOrderUnitParams = new BondOrderUnitParams(l_bongOrderUnitRow);
//            l_bondOrderUnitParams.setSettlementDiv("1");
//            BondOrderUnitRow l_bongOrderUnitRow1 = (BondOrderUnitRow)BondOrderUnitDao.findRowByOrderUnitId(10026);
//            BondOrderUnitParams l_bondOrderUnitParams1 = new BondOrderUnitParams(l_bongOrderUnitRow1);
//            l_bondOrderUnitParams1.setSettlementDiv("2");
//            BondOrderUnitRow l_bongOrderUnitRow2 = (BondOrderUnitRow)BondOrderUnitDao.findRowByOrderUnitId(10025);
//            BondOrderUnitParams l_bondOrderUnitParams2 = new BondOrderUnitParams(l_bongOrderUnitRow2);
//            l_bondOrderUnitParams2.setSettlementDiv("2");
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
//            l_amount.load();
//            List l_lis = l_amount.getTodaysTransactions();
//            Object l_obj = l_lis.get(0);
//            log.debug("l_obj.toString()" + l_obj.toString());
//        }
//        catch (Exception l_ex)
//        {
//            log.error("Error in Exception ...", l_ex);
//            fail();
//        }
//        
//        log.exiting(STR_METHOD_NAME);
//    }
//    
//    /*
//     * LOOP処理：債券注文単位テーブルの検索結果の行数為3の場合
//     */
//    public void testGetBondOrdersAmount_case002()
//    {
//        final String STR_METHOD_NAME = "testGetBondOrdersAmount_case002()";
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
//            List l_lis = l_amount.getTodaysTransactions();
//            Object l_obj = l_lis.get(0);
//            log.debug("l_obj.toString()" + l_obj.toString());
//            Object l_obj1 = l_lis.get(1);
//            log.debug("l_obj1.toString()" + l_obj1.toString());
//            Object l_obj2 = l_lis.get(2);
//            log.debug("l_obj2.toString()" + l_obj2.toString());
//        }
//        
//        catch (Exception l_ex)
//        {
//            log.error("Error in Exception ...", l_ex);
//            fail();
//        }
//        
//        log.exiting(STR_METHOD_NAME);
//    }
//    
//    
//    /*
//     * LOOP処理：債券注文単位テーブルの検索結果の行数為0の場合
//     * 以外（新規注文）の場合
//     */
//    public void testGetBondOrdersAmount_case003()
//    {
//        final String STR_METHOD_NAME = "testGetBondOrdersAmount_case003()";
//        log.entering(STR_METHOD_NAME);
//        
//        try
//        {
//            BondOrderUnitRow l_bongOrderUnitRow = (BondOrderUnitRow)BondOrderUnitDao.findRowByOrderUnitId(10001);
//            BondOrderUnitParams l_bondOrderUnitParams = new BondOrderUnitParams(l_bongOrderUnitRow);
//            l_bondOrderUnitParams.setSettlementDiv("2");
//            BondOrderUnitRow l_bongOrderUnitRow1 = (BondOrderUnitRow)BondOrderUnitDao.findRowByOrderUnitId(10026);
//            BondOrderUnitParams l_bondOrderUnitParams1 = new BondOrderUnitParams(l_bongOrderUnitRow1);
//            l_bondOrderUnitParams1.setSettlementDiv("2");
//            BondOrderUnitRow l_bongOrderUnitRow2 = (BondOrderUnitRow)BondOrderUnitDao.findRowByOrderUnitId(10025);
//            BondOrderUnitParams l_bondOrderUnitParams2 = new BondOrderUnitParams(l_bongOrderUnitRow2);
//            l_bondOrderUnitParams2.setSettlementDiv("2");
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
//            WEB3TPNewOrderSpec[] l_newOrderSpecss = new WEB3TPNewOrderSpec[1];
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
//            l_amount.setAccountInfo(l_accountInfo);
//            l_amount.setCalcCondition(l_calcCondition);
//            l_amount.setNewOrderSpecs(l_newOrderSpecss);
//            l_amount.load();
//            List l_lis = l_amount.getTodaysTransactions();
//            Object l_obj = l_lis.get(0);
//            log.debug("l_obj.toString()" + l_obj.toString());
//        }
//        catch (Exception l_ex)
//        {
//            log.error("Error in Exception ...", l_ex);
//            fail();
//        }
//        
//        log.exiting(STR_METHOD_NAME);
//    }
//    
//    /*
//     * "債券注文である場合
//     * (get注文タイプ()の戻り値 not IN (401：債券買い注文　@402：債券売り注文))"    
//     * if條件不成立   程序結束
//     */
//    public void testGetBondOrdersAmount_case004()
//    {
//        final String STR_METHOD_NAME = "testGetBondOrdersAmount_case004()";
//        log.entering(STR_METHOD_NAME);
//        
//        try
//        {
//            BondOrderUnitRow l_bongOrderUnitRow = (BondOrderUnitRow)BondOrderUnitDao.findRowByOrderUnitId(10001);
//            BondOrderUnitParams l_bondOrderUnitParams = new BondOrderUnitParams(l_bongOrderUnitRow);
//            l_bondOrderUnitParams.setSettlementDiv("1");
//            
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
//            WEB3TPNewOrderSpec l_newOrderSpecs1 = new WEB3TPNewOrderSpec();
//            l_newOrderSpecs1.setOrderType(OrderTypeEnum.ASSET_IN);
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
//            l_amount.setAccountInfo(l_accountInfo);
//            l_amount.setCalcCondition(l_calcCondition);
//            l_amount.setNewOrderSpecs(l_newOrderSpecss);
//            l_amount.load();
//        }
//        catch (Exception l_ex)
//        {
//            log.error("Error in Exception ...", l_ex);
//            fail();
//        }
//        
//        log.exiting(STR_METHOD_NAME);
//    }
//
//    /*
//     *[a.現物顧客の場合]
//     * ("顧客属性".is信用顧客() == false)
//     */
//    public void testGetBondOrdersCash_case001()
//    {
//        final String STR_METHOD_NAME = "testGetBondOrdersCash_case001()";
//        log.entering(STR_METHOD_NAME);
//        
//        try
//        {
//            BondOrderUnitRow l_bongOrderUnitRow = (BondOrderUnitRow)BondOrderUnitDao.findRowByOrderUnitId(10001);
//            BondOrderUnitParams l_bondOrderUnitParams = new BondOrderUnitParams(l_bongOrderUnitRow);
//            l_bondOrderUnitParams.setSettlementDiv("1");
//            l_bondOrderUnitParams.setOrderExecStatus("2");
//            
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
//                false);
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
//            l_tpcashBalance.setAccountInfo(l_accountInfo);
//            l_tpcashBalance.setCalcCondition(l_calcCondition);
//            l_tpcashBalance.setNewOrderSpecs(l_newOrderSpecss);
//            l_tpcashBalance.load();
//        }
//        catch (Exception l_ex)
//        {
//            log.error("Error in exception ...", l_ex);
//            fail();
//        }
//        
//        finally
//        {
//            log.exiting(STR_METHOD_NAME);
//        }
//    }
//    
//
//    /*
//     *[a.現物顧客の場合]
//     * ("顧客属性".is信用顧客() == true)
//     */
//    public void testGetBondOrdersCash_002()
//    {
//        try
//        {
//            BondOrderUnitRow l_bongOrderUnitRow = (BondOrderUnitRow)BondOrderUnitDao.findRowByOrderUnitId(10001);
//            BondOrderUnitParams l_bondOrderUnitParams = new BondOrderUnitParams(l_bongOrderUnitRow);
//            l_bondOrderUnitParams.setSettlementDiv("1");
//            l_bondOrderUnitParams.setOrderExecStatus("2");
//            
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
//            l_tpcashBalance.setAccountInfo(l_accountInfo);
//            l_tpcashBalance.setCalcCondition(l_calcCondition);
//            l_tpcashBalance.setNewOrderSpecs(l_newOrderSpecss);
//            l_tpcashBalance.load();
//        }
//        catch (Exception l_ex)
//        {
//            log.error("Error in exception ...", l_ex);
//            fail();
//        }
//    }
    
    /**
     * 1020：振替注文（預り金からオリックスクレジット）
     */
    public void test_getTodaysCashTransferOrders_0001()
    {
        final String STR_METHOD_NAME = "test_getTodaysCashTransferOrders_0001()";
        log.entering(STR_METHOD_NAME);
        WEB3TPPersistentDataManager l_manager = new WEB3TPPersistentDataManager();
        
        WEB3TPAssetValuationForTest1 l_assetValuation = new WEB3TPAssetValuationForTest1();
        WEB3TPAccountInfoForTest l_accountInfo = new WEB3TPAccountInfoForTest();
        WEB3TPCalcConditionForTest l_calcCondition = new WEB3TPCalcConditionForTest();
        WEB3TPNewOrderSpec l_spec = new WEB3TPNewOrderSpec();
        l_spec.setOrderType(OrderTypeEnum.TO_ORIX_CREDIT);
        l_spec.setOrderUnitId(12L);
        l_spec.setQuantity(90L);
        
        
        WEB3TPNewOrderSpec[] l_newOrderSpecs = new WEB3TPNewOrderSpec[]{l_spec};
        
        l_assetValuation.setAccountInfo(l_accountInfo);
        l_assetValuation.setCalcCondition(l_calcCondition);
        l_assetValuation.setNewOrderSpecs(l_newOrderSpecs);
        List l_lis = l_manager.getTodaysCashTransferOrders(l_assetValuation);
        AioOrderUnitParams l_params = (AioOrderUnitParams)l_lis.get(0);
        
        assertEquals("12", l_params.getOrderUnitId() + "");
        assertEquals(AssetTransferTypeEnum.CASH_OUT, l_params.getTransferType());
        log.exiting(STR_METHOD_NAME);
    }

    /*
     * 1021：CFD振替注文（預り金からCFD口座）
     */
    public void test_getTodaysCashTransferOrders_0002()
    {
        final String STR_METHOD_NAME = "test_getTodaysCashTransferOrders_0002()";
        log.entering(STR_METHOD_NAME);

        try
        {
            WEB3TPPersistentDataManager l_manager = new WEB3TPPersistentDataManager();

            WEB3TPAssetValuationForTest1 l_assetValuation = new WEB3TPAssetValuationForTest1();
            WEB3TPAccountInfoForTest l_accountInfo = new WEB3TPAccountInfoForTest();
            WEB3TPCalcConditionForTest l_calcCondition = new WEB3TPCalcConditionForTest();
            WEB3TPNewOrderSpec l_spec = new WEB3TPNewOrderSpec();
            l_spec.setOrderType(OrderTypeEnum.CFD_FROM_DEPOSIT_AMOUNT);
            l_spec.setOrderUnitId(12L);
            l_spec.setQuantity(90L);

            WEB3TPNewOrderSpec[] l_newOrderSpecs = new WEB3TPNewOrderSpec[]{l_spec};

            l_assetValuation.setAccountInfo(l_accountInfo);
            l_assetValuation.setCalcCondition(l_calcCondition);
            l_assetValuation.setNewOrderSpecs(l_newOrderSpecs);

            TestDBUtility.deleteAll(AioOrderUnitRow.TYPE);
            AioOrderUnitParams l_aioOrderUnitParams = TestDBUtility.getAioOrderUnitRow();
            l_aioOrderUnitParams.setAccountId(8);
            l_aioOrderUnitParams.setSubAccountId(9);
            l_aioOrderUnitParams.setOrderStatus(OrderStatusEnum.ACCEPTED);
            l_aioOrderUnitParams.setBizDate("20080911");
            l_aioOrderUnitParams.setOrderType(OrderTypeEnum.CFD_FROM_DEPOSIT_AMOUNT);
            TestDBUtility.insertWithDel(l_aioOrderUnitParams);

            List l_lis = l_manager.getTodaysCashTransferOrders(l_assetValuation);
            AioOrderUnitParams l_params0 = (AioOrderUnitParams)l_lis.get(0);
            AioOrderUnitParams l_params1 = (AioOrderUnitParams)l_lis.get(1);

            assertEquals(12L, l_params0.getOrderUnitId());
            assertEquals(2000011L, l_params1.getOrderUnitId());
            assertEquals(AssetTransferTypeEnum.CASH_OUT, l_params0.getTransferType());
        }
        catch (Exception l_ex)
        {
            log.error(STR_METHOD_NAME);
            fail();
        }

        log.exiting(STR_METHOD_NAME);
    }
    /*
     * 1022：CFD振替注文（CFD口座から預り金）
     */
    public void test_getTodaysCashTransferOrders_0003()
    {
        final String STR_METHOD_NAME = "test_getTodaysCashTransferOrders_0003()";
        log.entering(STR_METHOD_NAME);

        try
        {
            WEB3TPPersistentDataManager l_manager = new WEB3TPPersistentDataManager();

            WEB3TPAssetValuationForTest1 l_assetValuation = new WEB3TPAssetValuationForTest1();
            WEB3TPAccountInfoForTest l_accountInfo = new WEB3TPAccountInfoForTest();
            WEB3TPCalcConditionForTest l_calcCondition = new WEB3TPCalcConditionForTest();
            WEB3TPNewOrderSpec l_spec = new WEB3TPNewOrderSpec();
            l_spec.setOrderType(OrderTypeEnum.DEPOSIT_AMOUNT_FROM_CFD);
            l_spec.setOrderUnitId(12L);
            l_spec.setQuantity(90L);

            WEB3TPNewOrderSpec[] l_newOrderSpecs = new WEB3TPNewOrderSpec[]{l_spec};

            l_assetValuation.setAccountInfo(l_accountInfo);
            l_assetValuation.setCalcCondition(l_calcCondition);
            l_assetValuation.setNewOrderSpecs(l_newOrderSpecs);

            TestDBUtility.deleteAll(AioOrderUnitRow.TYPE);
            AioOrderUnitParams l_aioOrderUnitParams = TestDBUtility.getAioOrderUnitRow();
            l_aioOrderUnitParams.setAccountId(8);
            l_aioOrderUnitParams.setSubAccountId(9);
            l_aioOrderUnitParams.setOrderStatus(OrderStatusEnum.ACCEPTED);
            l_aioOrderUnitParams.setBizDate("20080911");
            l_aioOrderUnitParams.setOrderType(OrderTypeEnum.DEPOSIT_AMOUNT_FROM_CFD);
            TestDBUtility.insertWithDel(l_aioOrderUnitParams);

            List l_lis = l_manager.getTodaysCashTransferOrders(l_assetValuation);
            AioOrderUnitParams l_params0 = (AioOrderUnitParams)l_lis.get(0);
            AioOrderUnitParams l_params1 = (AioOrderUnitParams)l_lis.get(1);

            assertEquals(12L, l_params0.getOrderUnitId());
            assertEquals(2000011L, l_params1.getOrderUnitId());
            assertEquals(AssetTransferTypeEnum.CASH_IN, l_params0.getTransferType());
        }
        catch (Exception l_ex)
        {
            log.error(STR_METHOD_NAME);
            fail();
        }

        log.exiting(STR_METHOD_NAME);
    }

    /**
     *その他拘束金(仮拘束金)テーブルを以下の条件で検索する。 
        [検索条件] 
        口座ID = 引数.口座ID 
        AND 削除フラグ = 0：false 
        檢索到數據
     */
    public void testGetTempRestraintCase0001()
    {
        final String STR_METHOD_NAME = "testGetTempRestraintCase0001()";
        log.entering(STR_METHOD_NAME);
        
        WEB3TPPersistentDataManager l_manager = WEB3TPPersistentDataManager.getInstance();
        
        try
        {
            TestDBUtility.deleteAll(TpOtherTempRestraintRow.TYPE);
            TpOtherTempRestraintParams l_tpOtherTempRestraintParams = TestDBUtility.getTpOtherTempRestraintRow();
            TestDBUtility.insertWithDel(l_tpOtherTempRestraintParams);
            
            List l_lisTempRestraints =
                l_manager.getTempRestraint(new Long(l_tpOtherTempRestraintParams.getAccountId()));
            
            TpOtherTempRestraintRow l_row = (TpOtherTempRestraintParams)l_lisTempRestraints.get(0);
            assertEquals(l_lisTempRestraints.size() + "", "1");
            assertEquals(l_row.getDeleteFlag().intValue() + "", "0");
            
        }
        catch (Exception l_ex)
        {
            log.error("Error in Exception ...", l_ex);
            fail();
        }
      
        log.exiting(STR_METHOD_NAME);
    }
    
    /**
     *その他拘束金(仮拘束金)テーブルを以下の条件で検索する。 
        [検索条件] 
        口座ID = 引数.口座ID 
        AND 削除フラグ = 0：false 
        檢索不到數據
     */
    public void testGetTempRestraintCase0002()
    {
        final String STR_METHOD_NAME = "testGetTempRestraintCase0002()";
        log.entering(STR_METHOD_NAME);
        
        WEB3TPPersistentDataManager l_manager = WEB3TPPersistentDataManager.getInstance();
        
        try
        {
            TestDBUtility.deleteAll(TpOtherTempRestraintRow.TYPE);
            TpOtherTempRestraintParams l_tpOtherTempRestraintParams = TestDBUtility.getTpOtherTempRestraintRow();
            l_tpOtherTempRestraintParams.setDeleteFlag(BooleanEnum.TRUE);
            TestDBUtility.insertWithDel(l_tpOtherTempRestraintParams);
            
            List l_lisTempRestraints =
                l_manager.getTempRestraint(new Long(l_tpOtherTempRestraintParams.getAccountId()));
            
            assertEquals(l_lisTempRestraints.size() + "", "0");
        }
        catch (Exception l_ex)
        {
            log.error("Error in Exception ...", l_ex);
            fail();
        }
      
        log.exiting(STR_METHOD_NAME);
    }
    
    
    public class WEB3TPAssetValuationForTest1 extends WEB3TPAssetValuation
    {
    	
    }
    
    class WEB3TPAccountInfoForTest extends WEB3TPAccountInfo
    {
        public long getAccountId()
        {
        	return 8L;
        }
        public boolean isMarginCustomer()
        {
        	return false;
        }
        public long getSubAccountId(SubAccountTypeEnum l_subAccountType)
        {
        	return 9L;
        }
    }
    
    class WEB3TPCalcConditionForTest extends WEB3TPCalcCondition
    {
        public Date getBizDate(int l_intSpecifiedPoint)
        {
        	return new Date(WEB3DateUtility.getDate("20080911","yyyyMMdd").getTime());
        }
    }

    /**
     * 正常実行の場合
     */
    public void testGetFeqProduct_C001()
    {
        final String STR_METHOD_NAME = "testGetFeqProduct_C001()";
        log.entering(STR_METHOD_NAME);

        try
        {
            //DBの準備
            QueryProcessor l_processor = Processors.getDefaultProcessor();
            l_processor.doDeleteAllQuery(FeqProductRow.TYPE);
            FeqProductParams l_feqProductParams = TestDBUtility.getFeqProductRow();
            TestDBUtility.insertWithDel(l_feqProductParams);

            //実際のメソッドを実行
            WEB3TPPersistentDataManager l_manager = WEB3TPPersistentDataManager.getInstance();
            FeqProductRow l_feqProductRow = l_manager.getFeqProduct(3304148080000L);

            //比較
            assertEquals(l_feqProductRow.getProductId(), 3304148080000L);
            assertEquals(l_feqProductParams.getInstitutionCode(), "0D");
            assertEquals(l_feqProductParams.getProductCode(), "N8080");
            assertEquals(l_feqProductParams.getProductType(), ProductTypeEnum.FOREIGN_EQUITY);
            assertEquals(l_feqProductParams.getStandardNameKana(), "シンセンテルス");
            assertEquals(l_feqProductParams.getStandardNameKanji(), "ST深セン特力集団");
            assertEquals(l_feqProductParams.getOffshoreProductCode(), "200025");
            assertEquals(l_feqProductParams.getMarketCode(), "N2");
            assertEquals(l_feqProductParams.getCurrencyCode(), "001");
            assertEquals(l_feqProductParams.getBooksClosingYm1(), "1231");
            assertEquals(l_feqProductParams.getCapitalGainTaxDealingsReg(), 1);

        }
        catch (Exception l_ex)
        {
            fail();
        }
        log.exiting(STR_METHOD_NAME);
    }

    /**
     * 例外の場合
     */
    public void testGetFeqProduct_C002()
    {
        final String STR_METHOD_NAME = "testGetFeqProduct_C002()";
        log.entering(STR_METHOD_NAME);

        try
        {
            //DBの準備
            QueryProcessor l_processor = Processors.getDefaultProcessor();
            l_processor.doDeleteAllQuery(FeqProductRow.TYPE);

            //実際のメソッドを実行
            WEB3TPPersistentDataManager l_manager = WEB3TPPersistentDataManager.getInstance();
            l_manager.getFeqProduct(3304148080000L);

            fail();
        }
        catch (WEB3BaseRuntimeException l_ex)
        {
            //比較
            assertEquals("80005", l_ex.getErrorInfo().getErrorCode());
            assertEquals("テーブルに該当するデータがありません。", l_ex.getErrorInfo().getErrorMessage());
        }
        catch (Exception l_ex)
        {
            fail();
        }
        log.exiting(STR_METHOD_NAME);
    }

    /**
     * 正常実行の場合
     */
    public void testGetGenCurrency_C001()
    {
        final String STR_METHOD_NAME = "testGetGenCurrency_C001()";
        log.entering(STR_METHOD_NAME);

        try
        {
            //DBの準備
            QueryProcessor l_processor = Processors.getDefaultProcessor();
            l_processor.doDeleteAllQuery(GenCurrencyRow.TYPE);
            GenCurrencyParams l_genCurrencyParams = TestDBUtility.getGenCurrencyRow();
            TestDBUtility.insertWithDel(l_genCurrencyParams);

            //実際のメソッドを実行
            WEB3TPPersistentDataManager l_manager = WEB3TPPersistentDataManager.getInstance();
            GenCurrencyRow l_genCurrencyRow = l_manager.getGenCurrency("123", "132");

            //比較
            assertEquals(l_genCurrencyRow.getInstitutionCode(), "123");
            assertEquals(l_genCurrencyRow.getCurrencyCode(), "132");
            assertEquals(l_genCurrencyRow.getScale(), 1234);
            assertEquals(l_genCurrencyRow.getChangeJpyRoundDiv(), "1");
            assertEquals(l_genCurrencyRow.getChangeFCcyRoundDiv(), "1");

        }
        catch (Exception l_ex)
        {
            fail();
        }
        log.exiting(STR_METHOD_NAME);
    }

    /**
     * 例外の場合
     */
    public void testGetGenCurrency_C002()
    {
        final String STR_METHOD_NAME = "testGetGenCurrency_C002()";
        log.entering(STR_METHOD_NAME);

        try
        {
            //DBの準備
            QueryProcessor l_processor = Processors.getDefaultProcessor();
            l_processor.doDeleteAllQuery(GenCurrencyRow.TYPE);

            //実際のメソッドを実行
            WEB3TPPersistentDataManager l_manager = WEB3TPPersistentDataManager.getInstance();
            l_manager.getGenCurrency("123", "132");

            fail();
        }
        catch (WEB3BaseRuntimeException l_ex)
        {
            //比較
            assertEquals("80005", l_ex.getErrorInfo().getErrorCode());
            assertEquals("テーブルに該当するデータがありません。", l_ex.getErrorInfo().getErrorMessage());
        }
        catch (Exception l_ex)
        {
            fail();
        }
        log.exiting(STR_METHOD_NAME);
    }
    
    public void testSaveOtherRestraint_C0001()
    {
        final String STR_METHOD_NAME = "testSaveOtherRestraint_C0001()";
        log.entering(STR_METHOD_NAME);
        
        
        try
        {
            MOCK_MANAGER.setIsMockUsed(true);
            
            TestDBUtility.deleteAll(TpOtherTempRestraintRow.TYPE);

            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                    "com.fitechlabs.xtrade.plugin.tc.gentrade.stdimpls.TradingSystemImpl",
                    "getSystemTimestamp",
                    new Class[] {}, 
                    new Timestamp(WEB3DateUtility.getDate("20080529112233", "yyyyMMddHHmmss").getTime()));
            
            WEB3TPPersistentDataManager l_manager = WEB3TPPersistentDataManager.getInstance();
            l_manager.saveOtherRestraint(
                995100L, 
                5000D, 
                WEB3DateUtility.getDate("20080525", "yyyyMMdd"),
                WEB3DateUtility.getDate("20080528", "yyyyMMdd"), 
                "abc", 
                "2");

            String l_whereClause = 
                "account_id = ?";

            List l_lisRows = null;
            Object l_bindVars[] =
                { new Long(995100L)};

            l_lisRows = Processors.getDefaultProcessor().doFindAllQuery(
                TpOtherTempRestraintRow.TYPE,
                l_whereClause,
                l_bindVars);
            
            TpOtherTempRestraintRow l_tpOtherTempRestraintRows = 
                (TpOtherTempRestraintRow)l_lisRows.get(0);
            
            assertEquals(1, l_lisRows.size());
            assertEquals(995100L, l_tpOtherTempRestraintRows.getAccountId());
            assertEquals("2", l_tpOtherTempRestraintRows.getRestraintDiv());
            assertEquals(5000D, l_tpOtherTempRestraintRows.getAmount(), 0);
            assertEquals("20080525",
                WEB3DateUtility.formatDate(l_tpOtherTempRestraintRows.getTransactionDate(), "yyyyMMdd"));
            assertEquals("20080528",
                WEB3DateUtility.formatDate(l_tpOtherTempRestraintRows.getDeliveryDate(), "yyyyMMdd"));
            assertEquals("abc", l_tpOtherTempRestraintRows.getDeleteKey1());
            assertEquals(null, l_tpOtherTempRestraintRows.getDeleteKey2());
            assertEquals(BooleanEnum.FALSE, l_tpOtherTempRestraintRows.getDeleteFlag());
            assertEquals("20080529112233",
                WEB3DateUtility.formatDate(l_tpOtherTempRestraintRows.getCreatedTimestamp(), "yyyyMMddHHmmss"));
            assertEquals("20080529112233",
                WEB3DateUtility.formatDate(l_tpOtherTempRestraintRows.getLastUpdatedTimestamp(), "yyyyMMddHHmmss"));
        }
        catch (Exception l_ex)
        {
            log.error("Error in Exception ...", l_ex);
            log.exiting(STR_METHOD_NAME);
            fail();
        }
      
        log.exiting(STR_METHOD_NAME);
    }

    public void testDeleteOtherRestraint_C0001()
    {
        final String STR_METHOD_NAME = "testDeleteOtherRestraint_C0001()";
        log.entering(STR_METHOD_NAME);
        
        try
        {
            TestDBUtility.deleteAll(TpOtherTempRestraintRow.TYPE);         
            
            WEB3TPPersistentDataManager l_manager = WEB3TPPersistentDataManager.getInstance();
            l_manager.deleteOtherRestraint("1", "abc");
        }
        catch (Exception l_ex)
        {
            log.error("Error in Exception ...", l_ex);
            log.exiting(STR_METHOD_NAME);
            fail();
        } 
        log.exiting(STR_METHOD_NAME);
    }    

    public void testDeleteOtherRestraint_C0002()
    {
        final String STR_METHOD_NAME = "testDeleteOtherRestraint_C0002()";
        log.entering(STR_METHOD_NAME);
        
        try
        {
            
            MOCK_MANAGER.setIsMockUsed(true);
            
            TestDBUtility.deleteAll(TpOtherTempRestraintRow.TYPE);
            TpOtherTempRestraintParams l_tpOtherTempRestraintParams = new TpOtherTempRestraintParams();
            l_tpOtherTempRestraintParams.setAccountId(995100L);
            l_tpOtherTempRestraintParams.setRestraintDiv("2");
            l_tpOtherTempRestraintParams.setAmount(5000D);
            l_tpOtherTempRestraintParams.setTransactionDate(WEB3DateUtility.getDate("20080525", "yyyyMMdd"));
            l_tpOtherTempRestraintParams.setDeliveryDate(WEB3DateUtility.getDate("20080528", "yyyyMMdd"));
            l_tpOtherTempRestraintParams.setDeleteKey1("aaa");
            l_tpOtherTempRestraintParams.setDeleteKey2(null);
            l_tpOtherTempRestraintParams.setDeleteFlag(BooleanEnum.FALSE);
            l_tpOtherTempRestraintParams.setCreatedTimestamp(
                new Timestamp(WEB3DateUtility.getDate("20080501001122", "yyyyMMddHHmmss").getTime()));
            l_tpOtherTempRestraintParams.setLastUpdatedTimestamp(
                new Timestamp(WEB3DateUtility.getDate("20080510123456", "yyyyMMddHHmmss").getTime()));
            TestDBUtility.insertWithDel(l_tpOtherTempRestraintParams);
           
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                    "com.fitechlabs.xtrade.plugin.tc.gentrade.stdimpls.TradingSystemImpl",
                    "getSystemTimestamp",
                    new Class[] {}, 
                    new Timestamp(WEB3DateUtility.getDate("20080529112233", "yyyyMMddHHmmss").getTime()));
            
            WEB3TPPersistentDataManager l_manager = WEB3TPPersistentDataManager.getInstance();
            l_manager.deleteOtherRestraint("1", "abc");
   
            List l_lisRows = null;
            l_lisRows = TpOtherTempRestraintDao.findRowsByRestraintDivDeleteKey1("1", "abc");
            assertEquals(0, l_lisRows.size());
            
            l_lisRows = TpOtherTempRestraintDao.findRowsByRestraintDivDeleteKey1("2", "aaa");
            assertEquals(1, l_lisRows.size());
            
            TpOtherTempRestraintRow l_tpOtherTempRestraintRow = null;
            
            l_tpOtherTempRestraintRow = (TpOtherTempRestraintRow)l_lisRows.get(0);
            assertEquals(995100L, l_tpOtherTempRestraintRow.getAccountId());
            assertEquals("2", l_tpOtherTempRestraintRow.getRestraintDiv());
            assertEquals(5000D, l_tpOtherTempRestraintRow.getAmount(), 0);
            assertEquals("20080525", 
                WEB3DateUtility.formatDate(l_tpOtherTempRestraintRow.getTransactionDate(), "yyyyMMdd"));
            assertEquals("20080528", 
                WEB3DateUtility.formatDate(l_tpOtherTempRestraintRow.getDeliveryDate(), "yyyyMMdd"));
            assertEquals("aaa", l_tpOtherTempRestraintRow.getDeleteKey1());
            assertEquals(null, l_tpOtherTempRestraintRow.getDeleteKey2());
            assertEquals(BooleanEnum.FALSE, l_tpOtherTempRestraintRow.getDeleteFlag());
            assertEquals("20080501001122", 
                WEB3DateUtility.formatDate(l_tpOtherTempRestraintRow.getCreatedTimestamp(), "yyyyMMddHHmmss"));
            assertEquals("20080510123456", 
                WEB3DateUtility.formatDate(l_tpOtherTempRestraintRow.getLastUpdatedTimestamp(), "yyyyMMddHHmmss"));          
        }
        catch (Exception l_ex)
        {
            log.error("Error in Exception ...", l_ex);
            log.exiting(STR_METHOD_NAME);
            fail();
        } 
        log.exiting(STR_METHOD_NAME);
    }
    
    public void testDeleteOtherRestraint_C0003()
    {
        final String STR_METHOD_NAME = "testDeleteOtherRestraint_C0003()";
        log.entering(STR_METHOD_NAME);
        
        try
        {
            
            MOCK_MANAGER.setIsMockUsed(true);
            
            TestDBUtility.deleteAll(TpOtherTempRestraintRow.TYPE);
            TpOtherTempRestraintParams l_tpOtherTempRestraintParams = new TpOtherTempRestraintParams();
            l_tpOtherTempRestraintParams.setAccountId(995100L);
            l_tpOtherTempRestraintParams.setRestraintDiv("1");
            l_tpOtherTempRestraintParams.setAmount(5000D);
            l_tpOtherTempRestraintParams.setTransactionDate(WEB3DateUtility.getDate("20080525", "yyyyMMdd"));
            l_tpOtherTempRestraintParams.setDeliveryDate(WEB3DateUtility.getDate("20080528", "yyyyMMdd"));
            l_tpOtherTempRestraintParams.setDeleteKey1("abc");
            l_tpOtherTempRestraintParams.setDeleteKey2(null);
            l_tpOtherTempRestraintParams.setDeleteFlag(BooleanEnum.FALSE);
            l_tpOtherTempRestraintParams.setCreatedTimestamp(
                new Timestamp(WEB3DateUtility.getDate("20080501001122", "yyyyMMddHHmmss").getTime()));
            l_tpOtherTempRestraintParams.setLastUpdatedTimestamp(
                new Timestamp(WEB3DateUtility.getDate("20080510123456", "yyyyMMddHHmmss").getTime()));
            TestDBUtility.insertWithDel(l_tpOtherTempRestraintParams);
            
            l_tpOtherTempRestraintParams.setAmount(3000D);
            TestDBUtility.insertWithDel(l_tpOtherTempRestraintParams);
            
            l_tpOtherTempRestraintParams.setDeleteKey1("aaa");
            TestDBUtility.insertWithDel(l_tpOtherTempRestraintParams);
           
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                    "com.fitechlabs.xtrade.plugin.tc.gentrade.stdimpls.TradingSystemImpl",
                    "getSystemTimestamp",
                    new Class[] {}, 
                    new Timestamp(WEB3DateUtility.getDate("20080529112233", "yyyyMMddHHmmss").getTime()));
            
            WEB3TPPersistentDataManager l_manager = WEB3TPPersistentDataManager.getInstance();
            l_manager.deleteOtherRestraint("1", "abc");
   
            List l_lisRows = null;
            l_lisRows = TpOtherTempRestraintDao.findRowsByRestraintDivDeleteKey1("1", "abc");
            assertEquals(2, l_lisRows.size());
            
            TpOtherTempRestraintRow l_tpOtherTempRestraintRow = null;
            
            l_tpOtherTempRestraintRow = (TpOtherTempRestraintRow)l_lisRows.get(0);
            assertEquals(995100L, l_tpOtherTempRestraintRow.getAccountId());
            assertEquals("1", l_tpOtherTempRestraintRow.getRestraintDiv());
            assertEquals(5000D, l_tpOtherTempRestraintRow.getAmount(), 0);
            assertEquals("20080525", 
                WEB3DateUtility.formatDate(l_tpOtherTempRestraintRow.getTransactionDate(), "yyyyMMdd"));
            assertEquals("20080528", 
                WEB3DateUtility.formatDate(l_tpOtherTempRestraintRow.getDeliveryDate(), "yyyyMMdd"));
            assertEquals("abc", l_tpOtherTempRestraintRow.getDeleteKey1());
            assertEquals(null, l_tpOtherTempRestraintRow.getDeleteKey2());
            assertEquals(BooleanEnum.TRUE, l_tpOtherTempRestraintRow.getDeleteFlag());
            assertEquals("20080501001122", 
                WEB3DateUtility.formatDate(l_tpOtherTempRestraintRow.getCreatedTimestamp(), "yyyyMMddHHmmss"));
            assertEquals("20080529112233", 
                WEB3DateUtility.formatDate(l_tpOtherTempRestraintRow.getLastUpdatedTimestamp(), "yyyyMMddHHmmss"));
            
            l_tpOtherTempRestraintRow = (TpOtherTempRestraintRow)l_lisRows.get(1);
            assertEquals(995100L, l_tpOtherTempRestraintRow.getAccountId());
            assertEquals("1", l_tpOtherTempRestraintRow.getRestraintDiv());
            assertEquals(3000D, l_tpOtherTempRestraintRow.getAmount(), 0);
            assertEquals("20080525", 
                WEB3DateUtility.formatDate(l_tpOtherTempRestraintRow.getTransactionDate(), "yyyyMMdd"));
            assertEquals("20080528", 
                WEB3DateUtility.formatDate(l_tpOtherTempRestraintRow.getDeliveryDate(), "yyyyMMdd"));
            assertEquals("abc", l_tpOtherTempRestraintRow.getDeleteKey1());
            assertEquals(null, l_tpOtherTempRestraintRow.getDeleteKey2());
            assertEquals(BooleanEnum.TRUE, l_tpOtherTempRestraintRow.getDeleteFlag());
            assertEquals("20080501001122", 
                WEB3DateUtility.formatDate(l_tpOtherTempRestraintRow.getCreatedTimestamp(), "yyyyMMddHHmmss"));
            assertEquals("20080529112233", 
                WEB3DateUtility.formatDate(l_tpOtherTempRestraintRow.getLastUpdatedTimestamp(), "yyyyMMddHHmmss"));

            l_lisRows = TpOtherTempRestraintDao.findRowsByRestraintDivDeleteKey1("1", "aaa");
            assertEquals(1, l_lisRows.size());
            
            l_tpOtherTempRestraintRow = (TpOtherTempRestraintRow)l_lisRows.get(0);
            assertEquals(995100L, l_tpOtherTempRestraintRow.getAccountId());
            assertEquals("1", l_tpOtherTempRestraintRow.getRestraintDiv());
            assertEquals(3000D, l_tpOtherTempRestraintRow.getAmount(), 0);
            assertEquals("20080525", 
                WEB3DateUtility.formatDate(l_tpOtherTempRestraintRow.getTransactionDate(), "yyyyMMdd"));
            assertEquals("20080528", 
                WEB3DateUtility.formatDate(l_tpOtherTempRestraintRow.getDeliveryDate(), "yyyyMMdd"));
            assertEquals("aaa", l_tpOtherTempRestraintRow.getDeleteKey1());
            assertEquals(null, l_tpOtherTempRestraintRow.getDeleteKey2());
            assertEquals(BooleanEnum.FALSE, l_tpOtherTempRestraintRow.getDeleteFlag());
            assertEquals("20080501001122", 
                WEB3DateUtility.formatDate(l_tpOtherTempRestraintRow.getCreatedTimestamp(), "yyyyMMddHHmmss"));
            assertEquals("20080510123456", 
                WEB3DateUtility.formatDate(l_tpOtherTempRestraintRow.getLastUpdatedTimestamp(), "yyyyMMddHHmmss"));            
        }
        catch (Exception l_ex)
        {
            log.error("Error in Exception ...", l_ex);
            log.exiting(STR_METHOD_NAME);
            fail();
        } 
        log.exiting(STR_METHOD_NAME);
    }    
    
    public void testIsExistOtherRestraint_C0001()
    {
        final String STR_METHOD_NAME = "testIsExistOtherRestraint_C0001()";
        log.entering(STR_METHOD_NAME);
        
        try
        {
            TestDBUtility.deleteAll(TpOtherTempRestraintRow.TYPE);
            
            WEB3TPPersistentDataManager l_manager = WEB3TPPersistentDataManager.getInstance();
            boolean l_blnExistOtherRestraint = l_manager.isExistOtherRestraint("1", "abc");
            
            assertEquals(false, l_blnExistOtherRestraint);    
        }
        catch (Exception l_ex)
        {
            log.error("Error in Exception ...", l_ex);
            log.exiting(STR_METHOD_NAME);
            fail();
        }
        
        log.exiting(STR_METHOD_NAME);
    }
    
    public void testIsExistOtherRestraint_C0002()
    {
        final String STR_METHOD_NAME = "testIsExistOtherRestraint_C0002()";
        log.entering(STR_METHOD_NAME);
        
        try
        {
            TestDBUtility.deleteAll(TpOtherTempRestraintRow.TYPE);
            TpOtherTempRestraintParams l_tpOtherTempRestraintParams = TestDBUtility.getTpOtherTempRestraintRow();
            l_tpOtherTempRestraintParams.setRestraintDiv("1");
            l_tpOtherTempRestraintParams.setDeleteKey1("aaa");
            TestDBUtility.insertWithDel(l_tpOtherTempRestraintParams);
            
            WEB3TPPersistentDataManager l_manager = WEB3TPPersistentDataManager.getInstance();
            boolean l_blnExistOtherRestraint = l_manager.isExistOtherRestraint("1", "abc");
            
            assertEquals(false, l_blnExistOtherRestraint);                     
        }
        catch (Exception l_ex)
        {
            log.error("Error in Exception ...", l_ex);
            log.exiting(STR_METHOD_NAME);
            fail();
        }
        
        log.exiting(STR_METHOD_NAME);
    }
    
    public void testIsExistOtherRestraint_C0003()
    {
        final String STR_METHOD_NAME = "testIsExistOtherRestraint_C0003()";
        log.entering(STR_METHOD_NAME);
        
        try
        {
            TestDBUtility.deleteAll(TpOtherTempRestraintRow.TYPE);
            TpOtherTempRestraintParams l_tpOtherTempRestraintParams = TestDBUtility.getTpOtherTempRestraintRow();
            
            l_tpOtherTempRestraintParams.setRestraintDiv("1");
            l_tpOtherTempRestraintParams.setDeleteKey1("abc");
            TestDBUtility.insertWithDel(l_tpOtherTempRestraintParams);
            
            l_tpOtherTempRestraintParams.setDeleteKey1("aaa");
            TestDBUtility.insertWithDel(l_tpOtherTempRestraintParams);
            TestDBUtility.insertWithDel(l_tpOtherTempRestraintParams);
            
            
            
            WEB3TPPersistentDataManager l_manager = WEB3TPPersistentDataManager.getInstance();
            boolean l_blnExistOtherRestraint = l_manager.isExistOtherRestraint("1", "abc");
            
            assertEquals(true, l_blnExistOtherRestraint);                     
        }
        catch (Exception l_ex)
        {
            log.error("Error in Exception ...", l_ex);
            log.exiting(STR_METHOD_NAME);
            fail();
        }
        
        log.exiting(STR_METHOD_NAME);
    }
    
    public void testGetTpCashBalanceRow_C0001()
    {
        final String STR_METHOD_NAME = "testGetTpCashBalanceRow_C0001()";
        log.entering(STR_METHOD_NAME);
        
        try
        {
            TestDBUtility.deleteAll(TpCashBalanceRow.TYPE);
            
            WEB3TPPersistentDataManager l_manager = WEB3TPPersistentDataManager.getInstance();
            TpCashBalanceRow l_tpCashBalanceRow = l_manager.getTpCashBalanceRow(1001L, 20002L);
            
            assertEquals(null, l_tpCashBalanceRow);
        }
        catch (Exception l_ex)
        {
            log.error("Error in Exception ...", l_ex);
            log.exiting(STR_METHOD_NAME);
            fail();
        }
        
        log.exiting(STR_METHOD_NAME);
    }
    
    public void testGetTpCashBalanceRow_C0002()
    {
        final String STR_METHOD_NAME = "testGetTpCashBalanceRow_C0002()";
        log.entering(STR_METHOD_NAME);
        
        try
        {
            TestDBUtility.deleteAll(TpCashBalanceRow.TYPE);
            TpCashBalanceParams l_tpCashBalanceParams = new TpCashBalanceParams();
            l_tpCashBalanceParams.setTpCashBalanceId(101L);
            l_tpCashBalanceParams.setAccountId(99100L);
            l_tpCashBalanceParams.setSubAccountId(995100L);
            l_tpCashBalanceParams.setCashBalance0(5000D);
            l_tpCashBalanceParams.setCashBalance1(5001D);
            l_tpCashBalanceParams.setCashBalance2(5002D);
            l_tpCashBalanceParams.setCashBalance3(5003D);
            l_tpCashBalanceParams.setCashBalance4(5004D);
            l_tpCashBalanceParams.setCashBalance5(5005D);
            l_tpCashBalanceParams.setMrfBalance(5111D);
            l_tpCashBalanceParams.setCreatedTimestamp(
                new Timestamp(WEB3DateUtility.getDate("20080501001122", "yyyyMMddHHmmss").getTime()));
            l_tpCashBalanceParams.setLastUpdatedTimestamp(
                new Timestamp(WEB3DateUtility.getDate("20080510123456", "yyyyMMddHHmmss").getTime()));
            TestDBUtility.insertWithDel(l_tpCashBalanceParams);
            
            WEB3TPPersistentDataManager l_manager = WEB3TPPersistentDataManager.getInstance();
            TpCashBalanceRow l_tpCashBalanceRow = l_manager.getTpCashBalanceRow(1001L, 20002L);
            
            assertEquals(null, l_tpCashBalanceRow);
        }
        catch (Exception l_ex)
        {
            log.error("Error in Exception ...", l_ex);
            log.exiting(STR_METHOD_NAME);
            fail();
        }
        
        log.exiting(STR_METHOD_NAME);
    }
    
    public void testGetTpCashBalanceRow_C0003()
    {
        final String STR_METHOD_NAME = "testGetTpCashBalanceRow_C0003()";
        log.entering(STR_METHOD_NAME);
        
        try
        {
            TestDBUtility.deleteAll(TpCashBalanceRow.TYPE);
            TpCashBalanceParams l_tpCashBalanceParams = new TpCashBalanceParams();
            
            l_tpCashBalanceParams.setTpCashBalanceId(101L);
            l_tpCashBalanceParams.setAccountId(99100L);
            l_tpCashBalanceParams.setSubAccountId(995100L);
            l_tpCashBalanceParams.setCashBalance0(5000D);
            l_tpCashBalanceParams.setCashBalance1(5001D);
            l_tpCashBalanceParams.setCashBalance2(5002D);
            l_tpCashBalanceParams.setCashBalance3(5003D);
            l_tpCashBalanceParams.setCashBalance4(5004D);
            l_tpCashBalanceParams.setCashBalance5(5005D);
            l_tpCashBalanceParams.setMrfBalance(5111D);
            l_tpCashBalanceParams.setCreatedTimestamp(
                new Timestamp(WEB3DateUtility.getDate("20080501001122", "yyyyMMddHHmmss").getTime()));
            l_tpCashBalanceParams.setLastUpdatedTimestamp(
                new Timestamp(WEB3DateUtility.getDate("20080510123456", "yyyyMMddHHmmss").getTime()));
            TestDBUtility.insertWithDel(l_tpCashBalanceParams);
            
            l_tpCashBalanceParams.setTpCashBalanceId(102L);
            l_tpCashBalanceParams.setAccountId(1001L);
            l_tpCashBalanceParams.setSubAccountId(20002L);
            TestDBUtility.insertWithDel(l_tpCashBalanceParams);
            
            WEB3TPPersistentDataManager l_manager = WEB3TPPersistentDataManager.getInstance();
            TpCashBalanceRow l_tpCashBalanceRow = l_manager.getTpCashBalanceRow(1001L, 20002L);
            
            assertEquals(102L, l_tpCashBalanceRow.getTpCashBalanceId());
        }
        catch (Exception l_ex)
        {
            log.error("Error in Exception ...", l_ex);
            log.exiting(STR_METHOD_NAME);
            fail();
        }
        
        log.exiting(STR_METHOD_NAME);
    }
}   
@
