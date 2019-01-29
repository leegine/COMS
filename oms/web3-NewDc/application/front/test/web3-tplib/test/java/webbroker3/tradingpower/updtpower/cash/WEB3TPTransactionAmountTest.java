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
Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
File Name        : //TODO(WEB3TPTransactionAmountTest.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2006/09/29 ���G�� (���u) �V�K�쐬
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
 * XXXXXX�N���X//TODO
 *
 * @@author ���G��(���u)
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
//         * @@param l_strMarketCode String �s��R�[�h
//         * @@param l_strBranchCode String ���X�R�[�h
//         * @@param l_strInstitutionCode String �،���ЃR�[�h
//         * @@param l_tradingTimeType String ��t���ԋ敪
//         * @@param l_orderAcceptProduct String ������t���i
//         * @@param l_OrderAcceptTransaction String ������t�g�����U�N�V����
//         * @@param l_productCode �����R�[�h �敨OP�^���M�̂ݎg�p �ȊO�� 0�FDEFAULT
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
//     * ���ς̏ꍇ
//     *�i����.������.get�������敪() == 1�F���ρj
//     *[a.��W����̏ꍇ]
//     *�i����.������.get�������() == 401�F����������
//     *�@@&&
//     * ����.������.get���() == 35:��W����j
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
//            //���������e[]
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
//     * �����̏ꍇ
//     *�i����.������.get�������敪() == 0�F�����j
//     *�i����.������.get�������() == 401�F����������
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
//            //���������e[]
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
//     * �����̏ꍇ
//     *�i����.������.get�������敪() == 0�F�����j
//     *�i����.������.get�������() != 401�F����������
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
//            //���������e[]
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
//     * ���ς̏ꍇ
//     *�i����.������.get�������敪() == 1�F���ρj
//     *[a.��W����̏ꍇ]
//     *�i����.������.get�������() != 401�F����������
//     *�@@&&
//     * ����.������.get���() != 35:��W����j
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
//            //���������e[]
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
//     * get������<������>( )�̖߂�l != null�@@�̏ꍇ
//     * get������<������>()�̖߂�l�̗v�f����1
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
//            //���������e[]
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
//     * get������<������>( )�̖߂�l  == null�@@�̏ꍇ
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
//            //���������e[]
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
//     * get������<������>( )�̖߂�l != null�@@�̏ꍇ
//     * get������<������>()�̖߂�l�̗v�f����3
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
//            //���������e[]
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
//     * (IPO�w���\�����ɗ]�͂��`�F�b�N����iget��Е��X�ʗ]�͌v�Z����()�̖߂�l == 1�F�`�F�b�N����j ����
//�@@�@@ * IPO�\��.���I���� == 2�F�⌇ ����
//�@@�@@ * IPO�\��.�w���\���敪 != 1�F�w���\��)
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
//            //IPO�\��.�w���\���敪 != 1�F�w���\��
//            l_ipoOrderParams.setOfferingDiv(WEB3TPIPOOfferingDivDef.DEFAULT);
//            
//            //IPO�\��.���I���� == 2�F�⌇
//            l_ipoOrderParams.setLotResult(WEB3TPIPOLotResultTypeDef.SUBSTITUTE);
//            /**/
//            l_ipoOrderParams.setLotResultRetry(WEB3TPIPOLotResultTypeDef.DEFAULT);
//            
//            //IPO�\��.��t��Ԃ�"2:SONAR���f��"
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
//            //IPO����.�w���\���I����(�ژ_�����L��) != null
//            //IPO����.�w���\���I����(�ژ_�����L��) > �c�Ɠ�(T+0) 
//            l_ipoProductParams.setOfferEndDateProspec(WEB3DateUtility.getDate("20090909","yyyyMMdd"));
//            //IPO����.�폜�t���O=FALSE
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
//     * (IPO�w���\�����ɗ]�͂��`�F�b�N����iget��Е��X�ʗ]�͌v�Z����()�̖߂�l == 1�F�`�F�b�N����j ����
//�@@�@@ * IPO�\��.���I���� == 2�F�⌇ ����
//�@@�@@ * IPO�\��.�w���\���敪 != 1�F�w���\��) *�ȊO
//     * || 
//     * IPO�\��.��t��Ԃ�"2:SONAR���f��"�ȊO
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
//            //IPO�\��.�w���\���敪 != 1�F�w���\��
//            l_ipoOrderParams.setOfferingDiv(WEB3TPIPOOfferingDivDef.DEFAULT);
//            
//            //IPO�\��.���I���� != 2�F�⌇
//            l_ipoOrderParams.setLotResult(WEB3TPIPOLotResultTypeDef.WIN);
//            /**/
//            l_ipoOrderParams.setLotResultRetry(WEB3TPIPOLotResultTypeDef.DEFAULT);
//            
//            //IPO�\��.��t��Ԃ�"2:SONAR���f��"�ȊO
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
//            //IPO����.�w���\���I����(�ژ_�����L��) != null
//            //IPO����.�w���\���I����(�ژ_�����L��) > �c�Ɠ�(T+0) 
//            l_ipoProductParams.setOfferEndDateProspec(WEB3DateUtility.getDate("20090909","yyyyMMdd"));
//            //IPO����.�폜�t���O=FALSE
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
//     * (IPO�w���\�����ɗ]�͂��`�F�b�N����iget��Е��X�ʗ]�͌v�Z����()�̖߂�l == 1�F�`�F�b�N����j ����
//�@@�@@ * IPO�\��.���I���� == 2�F�⌇ ����
//�@@�@@ * IPO�\��.�w���\���敪 != 1�F�w���\��) *�ȊO
//     * || 
//     * IPO�\��.��t��Ԃ�"2:SONAR���f��"�ȊO *
//     * ||
//     * IPO����.�w���\���I����(�ژ_�����L��) == null 
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
//            //IPO�\��.�w���\���敪 == 1�F�w���\��
//            l_ipoOrderParams.setOfferingDiv(WEB3TPIPOOfferingDivDef.OFFERING);
//            
//            //IPO�\��.���I���� == 2�F�⌇
//            l_ipoOrderParams.setLotResult(WEB3TPIPOLotResultTypeDef.SUBSTITUTE);
//            /**/
//            l_ipoOrderParams.setLotResultRetry(WEB3TPIPOLotResultTypeDef.DEFAULT);
//            
//            //IPO�\��.��t��Ԃ�"2:SONAR���f��"�ȊO
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
//            //IPO����.�w���\���I����(�ژ_�����L��) != null
//            //IPO����.�w���\���I����(�ژ_�����L��) > �c�Ɠ�(T+0) 
////            l_ipoProductParams.setOfferEndDateProspec(WEB3DateUtility.getDate("20090909","yyyyMMdd"));
//            //IPO����.�폜�t���O=FALSE
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
//     * (IPO�w���\�����ɗ]�͂��`�F�b�N����iget��Е��X�ʗ]�͌v�Z����()�̖߂�l == 1�F�`�F�b�N����j ����
//�@@�@@ * IPO�\��.���I���� == 2�F�⌇ ����
//�@@�@@ * IPO�\��.�w���\���敪 != 1�F�w���\��) *�ȊO
//     * || 
//     * IPO�\��.��t��Ԃ�"2:SONAR���f��"�ȊO *
//     * ||
//     * IPO����.�w���\���I����(�ژ_�����L��) != null *
//     * ||
//     * IPO����.�w���\���I����(�ژ_�����L��) < �c�Ɠ�(T+0) 
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
//            //IPO�\��.�w���\���敪 == 1�F�w���\��
//            l_ipoOrderParams.setOfferingDiv(WEB3TPIPOOfferingDivDef.OFFERING);
//            
//            //IPO�\��.���I���� == 2�F�⌇
//            l_ipoOrderParams.setLotResult(WEB3TPIPOLotResultTypeDef.SUBSTITUTE);
//            /**/
//            l_ipoOrderParams.setLotResultRetry(WEB3TPIPOLotResultTypeDef.DEFAULT);
//            
//            //IPO�\��.��t��Ԃ�"2:SONAR���f��"�ȊO
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
//            //IPO����.�w���\���I����(�ژ_�����L��) != null
//            //IPO����.�w���\���I����(�ژ_�����L��) < �c�Ɠ�(T+0) 
//            l_ipoProductParams.setOfferEndDateProspec(WEB3DateUtility.getDate("20040909","yyyyMMdd"));
//            //IPO����.�폜�t���O=FALSE
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
//     * (IPO�w���\�����ɗ]�͂��`�F�b�N����iget��Е��X�ʗ]�͌v�Z����()�̖߂�l == 1�F�`�F�b�N����j ����
//�@@�@@ * IPO�\��.���I���� == 2�F�⌇ ����
//�@@�@@ * IPO�\��.�w���\���敪 != 1�F�w���\��) *�ȊO
//     * || 
//     * IPO�\��.��t��Ԃ�"2:SONAR���f��"�ȊO *
//     * ||
//     * IPO����.�w���\���I����(�ژ_�����L��) != null *
//     * ||
//     * IPO����.�w���\���I����(�ژ_�����L��) < �c�Ɠ�(T+0) *
//     * ||
//     * �擾����IPO����.�폜�t���O=TRUE
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
//            //IPO�\��.�w���\���敪 == 1�F�w���\��
//            l_ipoOrderParams.setOfferingDiv(WEB3TPIPOOfferingDivDef.OFFERING);
//            
//            //IPO�\��.���I���� == 2�F�⌇
//            l_ipoOrderParams.setLotResult(WEB3TPIPOLotResultTypeDef.SUBSTITUTE);
//            /**/
//            l_ipoOrderParams.setLotResultRetry(WEB3TPIPOLotResultTypeDef.DEFAULT);
//            
//            //IPO�\��.��t��Ԃ�"2:SONAR���f��"�ȊO
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
//            //IPO����.�w���\���I����(�ژ_�����L��) != null
//            //IPO����.�w���\���I����(�ژ_�����L��) > �c�Ɠ�(T+0) 
//            l_ipoProductParams.setOfferEndDateProspec(WEB3DateUtility.getDate("20090909","yyyyMMdd"));
//            //IPO����.�폜�t���O=TRUE
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
//     * (IPO�w���\�����ɗ]�͂��`�F�b�N����iget��Е��X�ʗ]�͌v�Z����()�̖߂�l == 1�F�`�F�b�N����j ����
//�@@�@@ * IPO�\��.���I���� == 2�F�⌇ ����
//�@@�@@ * IPO�\��.�w���\���敪 != 1�F�w���\��) *�ȊO
//     * || 
//     * IPO�\��.��t��Ԃ�"2:SONAR���f��"�ȊO *
//     * ||
//     * IPO����.�w���\���I����(�ژ_�����L��) != null *
//     * ||
//     * IPO����.�w���\���I����(�ژ_�����L��) < �c�Ɠ�(T+0) *
//     * ||
//     * �擾����IPO����.�폜�t���O=TRUE*
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
//            //IPO�\��.�w���\���敪 == 1�F�w���\��
//            l_ipoOrderParams.setOfferingDiv(WEB3TPIPOOfferingDivDef.OFFERING);
//            
//            //IPO�\��.���I���� == 2�F�⌇
//            l_ipoOrderParams.setLotResult(WEB3TPIPOLotResultTypeDef.SUBSTITUTE);
//            /**/
//            l_ipoOrderParams.setLotResultRetry(WEB3TPIPOLotResultTypeDef.DEFAULT);
//            
//            //IPO�\��.��t��Ԃ�"2:SONAR���f��"�ȊO
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
//            //IPO����.�w���\���I����(�ژ_�����L��) != null
//            //IPO����.�w���\���I����(�ژ_�����L��) > �c�Ɠ�(T+0) 
//            l_ipoProductParams.setOfferEndDateProspec(WEB3DateUtility.getDate("20090909","yyyyMMdd"));
//            //IPO����.�폜�t���O=FALSE
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
//         * (��Е��X�ʗ]�͌v�Z����)
//         * 
//         * ��Е��X���̗]�͌v�Z�������i�[����Map
//         */
//        private Map instBranCalcCondition;
//        
//        public WEB3TPCalcConditionForTest()
//        {
//            this.instBranCalcCondition = new HashMap();
//        }
//        /**
//         * (get��Е��X�ʗ]�͌v�Z����)<BR>
//         * 
//         * ����.�v���t�@@�����X���ɑΉ�����l���}�b�v��茟�����ԋp����B
//         * �����R�[�h�����݂��Ȃ��ꍇ�Anull��ԋp����B
//         * 
//         * @@param l_strName - (�v���t�@@�����X��)
//         * @@return String
//         */
//        public String getInstBranCalcCondition(String l_strName)
//        {
//            boolean l_isRegistered = instBranCalcCondition.containsKey(l_strName);
//
//            //�l
//            String l_strValue = null;
//            
//            //�o�^�ς̏ꍇ
//            if(l_isRegistered == true)
//            {
//                l_strValue = (String)instBranCalcCondition.get(l_strName);
//            }
//
//            return l_strValue;
//        }
//
//        /**
//         * (add��Е��X�ʗ]�͌v�Z����)<BR>
//         * <BR>
//         * �P�j����.�v���t�@@�����X�����L�[�Ƃ��Ĉ���.�l��<BR>
//         * Map�ithis.��Е��X�ʗ]�͌v�Z�����j�ɃZ�b�g����B<BR>
//         * <BR>
//         * �@@-this.��Е��X�ʗ]�͌v�Z����.put()���R�[��<BR>
//         * <BR>
//         * �@@[����]<BR>
//         * �@@�@@Object�F�@@����.�v���t�@@�����X��<BR>
//         * �@@�@@Object�F�@@����.�l<BR>
//         * <BR>
//         * @@param l_strName - (�v���t�@@�����X��)
//         * @@param l_strValue - (�l)
//         */
//        protected void addInstBranCalcCondition(String l_strName, String l_strValue) 
//        {
//            instBranCalcCondition.put(l_strName, l_strValue);
//        }
//    }
}
@
