head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.04.07.02.35.08;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	5444d9d078c63f4;
filename	WEB3TPRestraintTest.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
File Name        : //TODO(WEB3TPRestraintTest.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2006/09/20 ���G�� (���u) �V�K�쐬
*/
package webbroker3.tradingpower.updapower.cash;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Hashtable;
import java.util.List;
import java.util.Map;

import com.fitechlabs.xtrade.kernel.boot.Services;
import com.fitechlabs.xtrade.plugin.tc.gentrade.BooleanEnum;
import com.fitechlabs.xtrade.plugin.tc.gentrade.FinApp;
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
import webbroker3.tradingpower.data.TpOtherTempRestraintParams;
import webbroker3.tradingpower.data.TpOtherTempRestraintRow;
import webbroker3.tradingpower.define.WEB3TPIPOLotResultTypeDef;
import webbroker3.tradingpower.define.WEB3TPIPOOfferingDivDef;
import webbroker3.tradingpower.updtpower.WEB3TPAccountInfo;
import webbroker3.tradingpower.updtpower.cash.WEB3TPIPORestraintReflector;
import webbroker3.tradingpower.updtpower.cash.WEB3TPRestraint;
import webbroker3.tradingpower.updtpower.cash.WEB3TPTempRestraint;
import webbroker3.tradingpower.updtpower.cash.WEB3TPTransactionAmount;
import webbroker3.tradingpower.updtpower.cash.WEB3TPTransactionReflector;
import webbroker3.tradingpower.updtpower.contract.WEB3TPContractInfo;
import webbroker3.util.WEB3DateUtility;
import webbroker3.util.WEB3LogUtility;

/**
 * XXXXXX�N���X//TODO
 *<BR>
 * @@author ���G��(���u)
 * @@version 1.0
 */
public class WEB3TPRestraintTest extends TestBaseForMock
{

    private static WEB3LogUtility log = 
        WEB3LogUtility.getInstance(WEB3TPRestraintTest.class);
    
    private WEB3TPRestraint l_traint = new WEB3TPRestraint();
    
    private FinApp l_finApp = (FinApp)Services.getService(FinApp.class);
    
    public WEB3TPRestraintTest(String arg0)
    {
        super(arg0);
    }

    protected void setUp() throws Exception
    {
//        QueryProcessor l_processor = Processors.getDefaultProcessor();
//        MainAccountRow l_mainAccountRow =
//            (MainAccountRow)MainAccountDao.findRowByAccountId(266242662410L);
//        MainAccountParams l_params = new MainAccountParams(l_mainAccountRow);
//        l_params.setMarginGenAccOpenDiv("1");
//        l_params.setMarginSysAccOpenDiv("1");
//        l_processor.doUpdateQuery(l_params);
//        super.doSettingTradingClendarContext(
//            "0",
//            "670",
//            "07",
//            WEB3TradingTimeTypeDef.PAYMENT,
//            "0",
//            WEB3OrderAccProductDef.PAYMENT,
//            WEB3OrderAccTransactionDef.PAYMENT);
        }

    protected void tearDown() throws Exception 
    {
        doClearTradingClendarContext();
        super.tearDown();
    }
    
//    /*
//     * "1)(�S�ەs���ڋq�f�[�^Row != null 
//     * &&
//     * �S�ەs���ڋq�f�[�^Row.�o����~�敪 == 2:�ꕔ)
//     * 2)double = �S�ەs���ڋq�f�[�^Row.�o����~�z ==null"
//     */
//    public void testLoadCashDepositRestraints_case001()
//    {
//        final String STR_METHOD_NAME = "testLoadCashDepositRestraints_case001()";
//        log.entering(STR_METHOD_NAME);
//        try
//        {
//            WEB3GentradeSubAccount l_subAccount = 
//                (WEB3GentradeSubAccount)l_finApp.getAccountManager().getSubAccount(266242662410L, 26624266241001L);
//
//            WEB3TPCalcCondition l_calcCondition = WEB3TPCalcCondition.createCalcConditionStandard(l_subAccount);
//            
//            l_calcCondition.setSecuredLoanSecAccOpenDiv(true);
//            
//            WEB3TPAccountInfo l_accountInfo = WEB3TPAccountInfo.create(
//                266242662410L,
//                true);
//            
//            //(*)�S�ەs���ڋq�f�[�^�e�[�u�����A���Y�ڋq�̃��R�[�h���擾����B
//            //[�Ώۃe�[�u��]
//            // �S�ەs���ڋq�f�[�^�e�[�u��
//            //[��������]
//            // ����ID = �ڋq����.get����()
//            SecurityShortageAccountRow l_securityShortageAccountRow = 
//                (SecurityShortageAccountRow)SecurityShortageAccountDao.findRowByAccountId(266242662410L);
//            
//            SecurityShortageAccountParams l_params = 
//                new SecurityShortageAccountParams(l_securityShortageAccountRow);
//            
//            l_params.setPaymentStopDiv("2");
//            l_params.setPaymentStopAmount(null);
//            QueryProcessor l_processor = Processors.getDefaultProcessor();
//            l_processor.doUpdateQuery(l_params);
//
//            this.l_traint.setAccountInfo(l_accountInfo);
//            
//            this.l_traint.setCalcCondition(l_calcCondition);
//            
//            this.l_traint.load();
//        }
//        catch (Exception l_ex)
//        {
//            log.error("error in Exception ....", l_ex);
//            fail();
//        }
//        
//        log.exiting(STR_METHOD_NAME);
//    }
//    
//    /*
//     * "1)(�S�ەs���ڋq�f�[�^Row != null 
//     * &&
//     * �S�ەs���ڋq�f�[�^Row.�o����~�敪 == 2:�ꕔ)
//     * 2)double = �S�ەs���ڋq�f�[�^Row.�o����~�z !=null"
//     */
//    public void testLoadCashDepositRestraints_case002()
//    {
//        final String STR_METHOD_NAME = "testLoadCashDepositRestraints_case002()";
//        log.entering(STR_METHOD_NAME);
//        try
//        {
//            WEB3GentradeSubAccount l_subAccount = 
//                (WEB3GentradeSubAccount)l_finApp.getAccountManager().getSubAccount(266242662410L, 26624266241001L);
//
//            WEB3TPCalcCondition l_calcCondition = WEB3TPCalcCondition.createCalcConditionStandard(l_subAccount);
//            
//            l_calcCondition.setSecuredLoanSecAccOpenDiv(true);
//            
//            WEB3TPAccountInfo l_accountInfo = WEB3TPAccountInfo.create(
//                266242662410L,
//                true);
//            
//            //(*)�S�ەs���ڋq�f�[�^�e�[�u�����A���Y�ڋq�̃��R�[�h���擾����B
//            //[�Ώۃe�[�u��]
//            // �S�ەs���ڋq�f�[�^�e�[�u��
//            //[��������]
//            // ����ID = �ڋq����.get����()
//            SecurityShortageAccountRow l_securityShortageAccountRow = 
//                (SecurityShortageAccountRow)SecurityShortageAccountDao.findRowByAccountId(266242662410L);
//            
//            SecurityShortageAccountParams l_params = 
//                new SecurityShortageAccountParams(l_securityShortageAccountRow);
//            
//            l_params.setPaymentStopDiv("2");
//            l_params.setPaymentStopAmount("1");
//            QueryProcessor l_processor = Processors.getDefaultProcessor();
//            l_processor.doUpdateQuery(l_params);
//
//            this.l_traint.setAccountInfo(l_accountInfo);
//            
//            this.l_traint.setCalcCondition(l_calcCondition);
//            
//            this.l_traint.load();
//        }
//        catch (Exception l_ex)
//        {
//            log.error("error in Exception ....", l_ex);
//            fail();
//        }
//        
//        log.exiting(STR_METHOD_NAME);
//    }
//    
//    /*
//     * "(*)�S�ەs���ڋq�f�[�^�e�[�u�����A���Y�ڋq�̃��R�[�h������s���I�̏ꍇ
//     * [�Ώۃe�[�u��]
//     *�S�ەs���ڋq�f�[�^�e�[�u��
//     *[��������]
//     * ����ID = �ڋq����.get����()"
//     */
//    public void testLoadCashDepositRestraints_case003()
//    {
//        final String STR_METHOD_NAME = "testLoadCashDepositRestraints_case003()";
//        log.entering(STR_METHOD_NAME);
//        try
//        {
//            WEB3GentradeSubAccount l_subAccount = 
//                (WEB3GentradeSubAccount)l_finApp.getAccountManager().getSubAccount(266242662410L, 26624266241001L);
//
//            WEB3TPCalcCondition l_calcCondition = WEB3TPCalcCondition.createCalcConditionStandard(l_subAccount);
//            
//            l_calcCondition.setSecuredLoanSecAccOpenDiv(true);
//            
//            WEB3TPAccountInfo l_accountInfo = WEB3TPAccountInfo.create(
//                266242662420L,
//                true);
//
//            this.l_traint.setAccountInfo(l_accountInfo);
//            
//            this.l_traint.setCalcCondition(l_calcCondition);
//            
//            this.l_traint.load();
//        }
//        catch (WEB3BaseRuntimeException l_ex)
//        {
//            assertEquals(WEB3ErrorCatalog.SYSTEM_ERROR_80003, l_ex.getErrorInfo());
//        }
//        catch (Exception l_ex)
//        {
//            log.error("error in Exception ....", l_ex);
//            fail();
//        }
//        
//        log.exiting(STR_METHOD_NAME);
//    }
//    
//    /*
//     * "������ꗗ"�̗v�f��?0�̏ꍇ
//     */
//    public void testLoadStockOptionSellAmountRestraints_case001()
//    {
//        final String STR_METHOD_NAME = "testLoadStockOptionSellAmountRestraints_case001()";
//        log.entering(STR_METHOD_NAME);
//        try
//        {
//            WEB3GentradeSubAccount l_subAccount = 
//                (WEB3GentradeSubAccount)l_finApp.getAccountManager().getSubAccount(266242662410L, 26624266241001L);
//
//            WEB3TPCalcCondition l_calcCondition = WEB3TPCalcCondition.createCalcConditionStandard(l_subAccount);
//            
//            l_calcCondition.setSecuredLoanSecAccOpenDiv(true);
//            
//            WEB3TPAccountInfo l_accountInfo = WEB3TPAccountInfo.create(
//                266242662410L,
//                true);
//            
//            WEB3TPNewOrderSpec[] l_newOrderSpecs = new WEB3TPNewOrderSpec[3];
//            WEB3TPCashValuation l_cashValuation = WEB3TPCashValuation.create(
//                    l_accountInfo,
//                    l_calcCondition,
//                    l_newOrderSpecs);
//            
//            WEB3TPRestraint l_tpRestraint = WEB3TPRestraint.create(l_cashValuation);
//
//            l_tpRestraint.setAccountInfo(l_accountInfo);
//            
//            l_tpRestraint.setCalcCondition(l_calcCondition);
//            
//            double l_dblAmount = 0;
//            Date l_datDeliveryDate = WEB3DateUtility.getDate("20040721", "yyyyMMdd");
//            
//            WEB3TPStockOptionSellAmountRestraintReflector l_RestrainReflector = 
//                WEB3TPStockOptionSellAmountRestraintReflector.createStockOptionSellAmountRestraintReflector(
//                    l_calcCondition, 
//                    l_dblAmount, 
//                    l_datDeliveryDate);
//            l_RestrainReflector.setCalcCondition(l_calcCondition);
//
//            l_tpRestraint.load();
//        }
//        catch (Exception l_ex)
//        {
//            log.error("error in Exception ....", l_ex);
//            fail();
//        }
//        
//        log.exiting(STR_METHOD_NAME);
//    }
//    
//    /*
//     * "������ꗗ"�̗v�f��?1�̏ꍇ
//     * ��ޑ����ʏ���
//     * ("�g�����U�N�V�����^�C�v" = 80:��������� && 
//     * "�ŋ敪" = 5:�X�g�b�N�I�v�V���� && "�g�����U�N�V����������" >= "T-1")
//     */
//    public void testLoadStockOptionSellAmountRestraints_case002()
//    {
//        final String STR_METHOD_NAME = "testLoadStockOptionSellAmountRestraints_case002()";
//        log.entering(STR_METHOD_NAME);
//        try
//        {
//            WEB3GentradeSubAccount l_subAccount = 
//                (WEB3GentradeSubAccount)l_finApp.getAccountManager().getSubAccount(266242662410L, 26624266241001L);
//
//            WEB3TPCalcCondition l_calcCondition = WEB3TPCalcCondition.createCalcConditionStandard(l_subAccount);
//            
//            l_calcCondition.setSecuredLoanSecAccOpenDiv(true);
//            
//            WEB3TPAccountInfo l_accountInfo = WEB3TPAccountInfo.create(
//                266242662410L,
//                true);
//            
//            WEB3TPNewOrderSpec[] l_newOrderSpecs = new WEB3TPNewOrderSpec[3];
//            WEB3TPCashValuation l_cashValuation = WEB3TPCashValuation.create(
//                l_accountInfo,
//                l_calcCondition,
//                l_newOrderSpecs);
//        
//            WEB3TPRestraint l_tpRestraint = WEB3TPRestraint.create(l_cashValuation);
//
//            l_tpRestraint.setAccountInfo(l_accountInfo);
//            
//            l_tpRestraint.setCalcCondition(l_calcCondition);
//            
//            double l_dblAmount = 0;
//            Date l_datDeliveryDate = WEB3DateUtility.getDate("20040721", "yyyyMMdd");
//            
//            WEB3TPStockOptionSellAmountRestraintReflector l_RestrainReflector = 
//                WEB3TPStockOptionSellAmountRestraintReflector.createStockOptionSellAmountRestraintReflector(
//                    l_calcCondition, 
//                    l_dblAmount, 
//                    l_datDeliveryDate);
//            l_RestrainReflector.setCalcCondition(l_calcCondition);
//
//            l_tpRestraint.load();
//        }
//        catch (Exception l_ex)
//        {
//            log.error("error in Exception ....", l_ex);
//            fail();
//        }
//        
//        log.exiting(STR_METHOD_NAME);
//    }
//    
//    /*
//     * "������ꗗ"�̗v�f����1�̏ꍇ
//     * ��s�ޑ����ʏ���
//     * ("�g�����U�N�V�����^�C�v" = 80:��������� && 
//     * "�ŋ敪" = 5:�X�g�b�N�I�v�V���� && "�g�����U�N�V����������" >= "T-1")
//     */
//    public void testLoadStockOptionSellAmountRestraints_case003()
//    {
//        final String STR_METHOD_NAME = "testLoadStockOptionSellAmountRestraints_case003()";
//        log.entering(STR_METHOD_NAME);
//        try
//        {
//            WEB3GentradeSubAccount l_subAccount = 
//                (WEB3GentradeSubAccount)l_finApp.getAccountManager().getSubAccount(266242662410L, 26624266241001L);
//
//            WEB3TPCalcCondition l_calcCondition = WEB3TPCalcCondition.createCalcConditionStandard(l_subAccount);
//            
//            l_calcCondition.setSecuredLoanSecAccOpenDiv(true);
//            
//            WEB3TPAccountInfo l_accountInfo = WEB3TPAccountInfo.create(
//                266242662410L,
//                true);
//            
//            WEB3TPNewOrderSpec[] l_newOrderSpecs = new WEB3TPNewOrderSpec[3];
//            WEB3TPCashValuation l_cashValuation = WEB3TPCashValuation.create(
//                l_accountInfo,
//                l_calcCondition,
//                l_newOrderSpecs);
//            
//            WEB3TPRestraint l_tpRestraint = WEB3TPRestraint.create(l_cashValuation);
//
//            l_tpRestraint.setAccountInfo(l_accountInfo);
//            
//            l_tpRestraint.setCalcCondition(l_calcCondition);
//            
//            double l_dblAmount = 0;
//            Date l_datDeliveryDate = WEB3DateUtility.getDate("20040721", "yyyyMMdd");
//            
//            WEB3TPStockOptionSellAmountRestraintReflector l_RestrainReflector = 
//                WEB3TPStockOptionSellAmountRestraintReflector.createStockOptionSellAmountRestraintReflector(
//                    l_calcCondition, 
//                    l_dblAmount, 
//                    l_datDeliveryDate);
//            l_RestrainReflector.setCalcCondition(l_calcCondition);
//
//            l_tpRestraint.load();
//        }
//        catch (Exception l_ex)
//        {
//            log.error("error in Exception ....", l_ex);
//            fail();
//        }
//        
//        log.exiting(STR_METHOD_NAME);
//    }
//    
//    /*
//     * "������ꗗ"�̗v�f����3�̏ꍇ
//     * ��ޑ����ʏ���
//     * ("�g�����U�N�V�����^�C�v" = 80:��������� && 
//     * "�ŋ敪" = 5:�X�g�b�N�I�v�V���� && "�g�����U�N�V����������" >= "T-1")
//     */
//    public void testLoadStockOptionSellAmountRestraints_case004()
//    {
//        final String STR_METHOD_NAME = "testLoadStockOptionSellAmountRestraints_case004()";
//        log.entering(STR_METHOD_NAME);
//        try
//        {
//            WEB3GentradeSubAccount l_subAccount = 
//                (WEB3GentradeSubAccount)l_finApp.getAccountManager().getSubAccount(266242662410L, 26624266241001L);
//
//            WEB3TPCalcCondition l_calcCondition = WEB3TPCalcCondition.createCalcConditionStandard(l_subAccount);
//            
//            l_calcCondition.setSecuredLoanSecAccOpenDiv(true);
//            
//            WEB3TPAccountInfo l_accountInfo = WEB3TPAccountInfo.create(
//                266242662410L,
//                true);
//            
//            WEB3TPNewOrderSpec[] l_newOrderSpecs = new WEB3TPNewOrderSpec[3];
//            WEB3TPCashValuation l_cashValuation = WEB3TPCashValuation.create(
//                l_accountInfo,
//                l_calcCondition,
//                l_newOrderSpecs);
//            
//            WEB3TPRestraint l_tpRestraint = WEB3TPRestraint.create(l_cashValuation);
//
//            l_tpRestraint.setAccountInfo(l_accountInfo);
//            
//            l_tpRestraint.setCalcCondition(l_calcCondition);
//            
//            double l_dblAmount = 0;
//            Date l_datDeliveryDate = WEB3DateUtility.getDate("20040721", "yyyyMMdd");
//            
//            WEB3TPStockOptionSellAmountRestraintReflector l_RestrainReflector = 
//                WEB3TPStockOptionSellAmountRestraintReflector.createStockOptionSellAmountRestraintReflector(
//                    l_calcCondition, 
//                    l_dblAmount, 
//                    l_datDeliveryDate);
//            l_RestrainReflector.setCalcCondition(l_calcCondition);
//
//            l_tpRestraint.load();
//        }
//        catch (Exception l_ex)
//        {
//            log.error("error in Exception ....", l_ex);
//            fail();
//        }
//        
//        log.exiting(STR_METHOD_NAME);
//    }
    
//    /**
//     * (IPO�w���\�����ɗ]�͂��`�F�b�N����iget��Е��X�ʗ]�͌v�Z����()�̖߂�l == 1�F�`�F�b�N����j ����
//�@@�@@ * IPO�\��.���I���� == 2�F�⌇ ����
//�@@�@@ * IPO�\��.�w���\���敪 != 1�F�w���\��) 
//     */
//    public void test_loadIPORestraints_0001()
//    {
//      final String STR_METHOD_NAME = "test_loadIPORestraints_0001()";
//      log.entering(STR_METHOD_NAME);
//        
//      WEB3TPRestraint l_TPRestraint = new WEB3TPRestraint();
//        
//        Hashtable subAccountIds = new Hashtable();
////        HashMap l_map = new HashMap();
//        subAccountIds.put(new Long("1"),SubAccountTypeEnum.EQUITY_SUB_ACCOUNT);
//        WEB3TPAccountInfo l_assetValuation = new WEB3TPAccountInfo();
//        l_assetValuation.setAccountId(22);
//        
//        l_assetValuation.setSubAccountIds(subAccountIds);
//        
//        l_TPRestraint.setAccountInfo(l_assetValuation);
//        
//        WEB3TPCalcConditionForTest l_calcConditionForTest = new WEB3TPCalcConditionForTest();
//        Date[] l_dat = {new Date(),GtlUtils.getTradingSystem().getBizDate()};
//        l_calcConditionForTest.setBizDate(l_dat);
//        
//        l_calcConditionForTest.addInstBranCalcCondition("ipo.offer.tradingpower.check","1");
//        l_TPRestraint.setCalcCondition(l_calcConditionForTest);
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
//        Method method;
//        try
//        {
//            method = WEB3TPRestraint.class.getDeclaredMethod("loadIPORestraints", new Class[]{});
//            method.setAccessible(true);
//            method.invoke(l_TPRestraint, new Object[]{});
//        }
//        catch (Exception e)
//        {
//            fail();
//        }
////        l_TPRestraint.load();
////        List l_lst = l_TPRestraint.getTodaysTransactions();
////        
////        assertEquals("1","" + l_lst.size());
//        
//        log.exiting(STR_METHOD_NAME);
//    }
//    
//    /**
//     * (IPO�w���\�����ɗ]�͂��`�F�b�N����iget��Е��X�ʗ]�͌v�Z����()�̖߂�l == 1�F�`�F�b�N����j ����
//�@@�@@ * IPO�\��.���I���� == 2�F�⌇ ����
//�@@�@@ * IPO�\��.�w���\���敪 != 1�F�w���\��) *�ȊO
//     * || 
//     * IPO����.�w���\���I����(�ژ_�����L��) < �c�Ɠ�(T+0) *
//     * ||
//     * �擾����IPO����.�폜�t���O=TRUE *
//     */
//    public void test_loadIPORestraints_0002()
//    {
//      final String STR_METHOD_NAME = "test_loadIPORestraints_0002()";
//      log.entering(STR_METHOD_NAME);
//        
//      WEB3TPRestraintForTest l_TPRestraint = new WEB3TPRestraintForTest();
//        
//        Hashtable subAccountIds = new Hashtable();
////        HashMap l_map = new HashMap();
//        subAccountIds.put(new Long("1"),SubAccountTypeEnum.EQUITY_SUB_ACCOUNT);
//        WEB3TPAccountInfo l_assetValuation = new WEB3TPAccountInfo();
//        l_assetValuation.setAccountId(22);
//        
//        l_assetValuation.setSubAccountIds(subAccountIds);
//        
//        l_TPRestraint.setAccountInfo(l_assetValuation);
//        
//        WEB3TPCalcConditionForTest l_calcConditionForTest = new WEB3TPCalcConditionForTest();
//        Date[] l_dat = {new Date(),GtlUtils.getTradingSystem().getBizDate(),new Date(),new Date(),new Date(),new Date(),WEB3DateUtility.getDate("20090909","yyyyMMdd")};
//        l_calcConditionForTest.setBizDate(l_dat);
//        
//        l_calcConditionForTest.addInstBranCalcCondition("ipo.offer.tradingpower.check","1");
//        l_TPRestraint.setCalcCondition(l_calcConditionForTest);
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
//        Method method;
//        try
//        {
//            method = WEB3TPRestraint.class.getDeclaredMethod("loadIPORestraints", new Class[]{});
//            method.setAccessible(true);
//            method.invoke(l_TPRestraint, new Object[]{});
//
//        }
//        catch (Exception e)
//        {
//            fail();
//        }
//        
//        List l_lst = l_TPRestraint.getIPORestraints();
//        assertEquals("1","" + l_lst.size());
//        log.exiting(STR_METHOD_NAME);
//    }
//    
//    class WEB3TPRestraintForTest extends WEB3TPRestraint
//    {
//        private List ipoRestraints = new ArrayList();
//        
//        protected void addIPORestraint(WEB3TPIPORestraintReflector l_ipoRestraint)
//        {
//            if (l_ipoRestraint instanceof WEB3TPIPORestraintReflector)
//            {
//                ipoRestraints.add(l_ipoRestraint);
//            }
//        }
//        
//        public List getIPORestraints()
//        {
//            return ipoRestraints;
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
//    
//    /**
//     * do���S�������[�h
//     * 0
//     */
//    public void test_loadTempRestraints_0001()
//    {
//        final String STR_METHOD_NAME = "test_loadTempRestraints_0001()";
//        log.entering(STR_METHOD_NAME);
//        
//        Hashtable subAccountIds = new Hashtable();
//        subAccountIds.put(new Long("1"),SubAccountTypeEnum.EQUITY_SUB_ACCOUNT);
//      
//        WEB3TPRestraintForTest l_TPRestraint = new WEB3TPRestraintForTest();
//        
//        WEB3TPAccountInfo l_assetValuation = new WEB3TPAccountInfo();
//        l_assetValuation.setAccountId(333812512211L);
//        
//        l_assetValuation.setSubAccountIds(subAccountIds);
//        
//        l_TPRestraint.setAccountInfo(l_assetValuation);
//        
//        try
//        {
//            TestDBUtility.deleteAll(TpOtherTempRestraintRow.TYPE);
//            TpOtherTempRestraintParams l_tpOtherTempRestraintParams = TestDBUtility.getTpOtherTempRestraintRow();
//            TestDBUtility.insertWithDel(l_tpOtherTempRestraintParams);
//            
//        }
//        catch (WEB3BaseException e)
//        {
//            fail();
//        }
//
//        Method method;
//        try
//        {
//            method = WEB3TPRestraint.class.getDeclaredMethod("loadTempRestraints", new Class[]{});
//            method.setAccessible(true);
//            method.invoke(l_TPRestraint, new Object[]{});
//
//        }
//        catch (Exception e)
//        {
//            fail();
//        }
//        
//        log.exiting(STR_METHOD_NAME);
//    }
//    
//    /**
//     * do���S�������[�h
//     * 1
//     */
//    public void test_loadTempRestraints_0002()
//    {
//        final String STR_METHOD_NAME = "test_loadTempRestraints_0002()";
//        log.entering(STR_METHOD_NAME);
//        
//        Hashtable subAccountIds = new Hashtable();
//        subAccountIds.put(new Long("1"),SubAccountTypeEnum.EQUITY_SUB_ACCOUNT);
//      
//        WEB3TPRestraintForTest l_TPRestraint = new WEB3TPRestraintForTest();
//        
//        WEB3TPAccountInfo l_assetValuation = new WEB3TPAccountInfo();
//        l_assetValuation.setAccountId(333812512203L);
//        
//        l_assetValuation.setSubAccountIds(subAccountIds);
//        
//        l_TPRestraint.setAccountInfo(l_assetValuation);
//        
//        try
//        {
//            WEB3TPCalcConditionForTest l_calcConditionForTest = new WEB3TPCalcConditionForTest();
//            Date[] l_dat = {new Date(),GtlUtils.getTradingSystem().getBizDate(),new Date(),new Date(),new Date(),new Date(),WEB3DateUtility.getDate("20090909","yyyyMMdd")};
//            l_calcConditionForTest.setBizDate(l_dat);
//            
//            l_TPRestraint.setCalcCondition(l_calcConditionForTest);
//            
//            TestDBUtility.deleteAll(TpOtherTempRestraintRow.TYPE);
//            TpOtherTempRestraintParams l_tpOtherTempRestraintParams = TestDBUtility.getTpOtherTempRestraintRow();
//            TestDBUtility.insertWithDel(l_tpOtherTempRestraintParams);
//            
//        }
//        catch (WEB3BaseException e)
//        {
//            fail();
//        }
//
//        Method method;
//        try
//        {
//            method = WEB3TPRestraint.class.getDeclaredMethod("loadTempRestraints", new Class[]{});
//            method.setAccessible(true);
//            method.invoke(l_TPRestraint, new Object[]{});
//
//        }
//        catch (Exception e)
//        {
//            fail();
//        }
//        
//        List l_lst = l_TPRestraint.getTempRestraints();
//        assertEquals("1","" + l_lst.size());
//        log.exiting(STR_METHOD_NAME);
//    }
//
//    /**
//     * do���S�������[�h
//     * 3
//     */
//    public void test_loadTempRestraints_0003()
//    {
//        final String STR_METHOD_NAME = "test_loadTempRestraints_0003()";
//        log.entering(STR_METHOD_NAME);
//        
//        Hashtable subAccountIds = new Hashtable();
//        subAccountIds.put(new Long("1"),SubAccountTypeEnum.EQUITY_SUB_ACCOUNT);
//      
//        WEB3TPRestraintForTest l_TPRestraint = new WEB3TPRestraintForTest();
//        
//        WEB3TPAccountInfo l_assetValuation = new WEB3TPAccountInfo();
//        l_assetValuation.setAccountId(333812512203L);
//        
//        l_assetValuation.setSubAccountIds(subAccountIds);
//        
//        l_TPRestraint.setAccountInfo(l_assetValuation);
//        
//        try
//        {
//            WEB3TPCalcConditionForTest l_calcConditionForTest = new WEB3TPCalcConditionForTest();
//            Date[] l_dat = {new Date(),GtlUtils.getTradingSystem().getBizDate(),new Date(),new Date(),new Date(),new Date(),WEB3DateUtility.getDate("20090909","yyyyMMdd")};
//            l_calcConditionForTest.setBizDate(l_dat);
//            
//            l_TPRestraint.setCalcCondition(l_calcConditionForTest);
//            
//            TestDBUtility.deleteAll(TpOtherTempRestraintRow.TYPE);
//            TpOtherTempRestraintParams l_tpOtherTempRestraintParams = TestDBUtility.getTpOtherTempRestraintRow();
//            TestDBUtility.insertWithDel(l_tpOtherTempRestraintParams);
//            
//            TpOtherTempRestraintParams l_tpOtherTempRestraintParams1 = TestDBUtility.getTpOtherTempRestraintRow();
//            TestDBUtility.insertWithDel(l_tpOtherTempRestraintParams1);
//            
//            TpOtherTempRestraintParams l_tpOtherTempRestraintParams2 = TestDBUtility.getTpOtherTempRestraintRow();
//            TestDBUtility.insertWithDel(l_tpOtherTempRestraintParams2);
//            
//        }
//        catch (WEB3BaseException e)
//        {
//            fail();
//        }
//
//        Method method;
//        try
//        {
//            method = WEB3TPRestraint.class.getDeclaredMethod("loadTempRestraints", new Class[]{});
//            method.setAccessible(true);
//            method.invoke(l_TPRestraint, new Object[]{});
//
//        }
//        catch (Exception e)
//        {
//            fail();
//        }
//        
//        List l_lst = l_TPRestraint.getTempRestraints();
//        assertEquals("3","" + l_lst.size());
//        log.exiting(STR_METHOD_NAME);
//    }
    
    public void testCalcTempRestraintDateCase1()
    {
        final String STR_METHOD_NAME = "testCalcTempRestraintDateCase1()";
        log.entering(STR_METHOD_NAME);
        try
        {
            WEB3TPRestraint l_restraint = new WEB3TPRestraint();
            WEB3TPTempRestraint l_tempRestraint1 = new WEB3TPTempRestraint();
            l_tempRestraint1.setRestraintDiv("1");
            l_tempRestraint1.setReflectStartDay(WEB3DateUtility.getDate("20070525", "yyyyMMdd"));
            l_tempRestraint1.setReflectEndDay(WEB3DateUtility.getDate("20090525", "yyyyMMdd"));
            l_tempRestraint1.setAmount(10.0);
            WEB3TPTempRestraint l_tempRestraint2 = new WEB3TPTempRestraint();
            l_tempRestraint2.setRestraintDiv("2");
            l_tempRestraint2.setReflectStartDay(WEB3DateUtility.getDate("20070525", "yyyyMMdd"));
            l_tempRestraint2.setReflectEndDay(WEB3DateUtility.getDate("20090525", "yyyyMMdd"));
            l_tempRestraint2.setAmount(20.0);
            
            List tempRestraints = new ArrayList();
            tempRestraints.add(l_tempRestraint1);
            tempRestraints.add(l_tempRestraint2);
            // ���L�����I����
            Field field = WEB3TPRestraint.class.getDeclaredField("tempRestraints");
            field.setAccessible(true);
            field.set(l_restraint, tempRestraints);
            double l_dbl =
                l_restraint.calcTempRestraint(WEB3DateUtility.getDate("20080525", "yyyyMMdd"));
            assertEquals("30.0", l_dbl + "");
        }
        catch (Exception l_ex)
        {
            log.error("", l_ex);
            fail();
        }
        
        log.exiting(STR_METHOD_NAME);
    }
    
    public void testCalcTempRestraintCase1()
    {
        final String STR_METHOD_NAME = "testCalcTempRestraintCase1()";
        log.entering(STR_METHOD_NAME);
        try
        {
            WEB3TPRestraint l_restraint = new WEB3TPRestraint();
            WEB3TPTempRestraint l_tempRestraint1 = new WEB3TPTempRestraint();
            l_tempRestraint1.setRestraintDiv("1");
            l_tempRestraint1.setReflectStartDay(WEB3DateUtility.getDate("20070525", "yyyyMMdd"));
            l_tempRestraint1.setReflectEndDay(WEB3DateUtility.getDate("20090525", "yyyyMMdd"));
            l_tempRestraint1.setAmount(10.0);
            WEB3TPTempRestraint l_tempRestraint2 = new WEB3TPTempRestraint();
            l_tempRestraint2.setRestraintDiv("2");
            l_tempRestraint2.setReflectStartDay(WEB3DateUtility.getDate("20070525", "yyyyMMdd"));
            l_tempRestraint2.setReflectEndDay(WEB3DateUtility.getDate("20090525", "yyyyMMdd"));
            l_tempRestraint2.setAmount(20.0);
            WEB3TPTempRestraint l_tempRestraint3 = new WEB3TPTempRestraint();
            l_tempRestraint3.setRestraintDiv("1");
            l_tempRestraint3.setReflectStartDay(WEB3DateUtility.getDate("20070525", "yyyyMMdd"));
            l_tempRestraint3.setReflectEndDay(WEB3DateUtility.getDate("20090525", "yyyyMMdd"));
            l_tempRestraint3.setAmount(30.0);
            
            List tempRestraints = new ArrayList();
            tempRestraints.add(l_tempRestraint1);
            tempRestraints.add(l_tempRestraint2);
            tempRestraints.add(l_tempRestraint3);
            // ���L�����I����
            Field field = WEB3TPRestraint.class.getDeclaredField("tempRestraints");
            field.setAccessible(true);
            field.set(l_restraint, tempRestraints);
            
            String[] l_restraintDivList = new String[]{"1", "2"};
            double l_dbl =
                l_restraint.calcTempRestraint(WEB3DateUtility.getDate("20080525", "yyyyMMdd"), l_restraintDivList);
            assertEquals("60.0", l_dbl + "");
        }
        catch (Exception l_ex)
        {
            log.error("", l_ex);
            fail();
        }
        
        log.exiting(STR_METHOD_NAME);
    }
    
    
    public void testCalcTempRestraintCase2()
    {
        final String STR_METHOD_NAME = "testCalcTempRestraintCase2()";
        log.entering(STR_METHOD_NAME);
        try
        {
            WEB3TPRestraint l_restraint = new WEB3TPRestraint();
            WEB3TPTempRestraint l_tempRestraint1 = new WEB3TPTempRestraint();
            l_tempRestraint1.setRestraintDiv("1");
            l_tempRestraint1.setReflectStartDay(WEB3DateUtility.getDate("20070525", "yyyyMMdd"));
            l_tempRestraint1.setReflectEndDay(WEB3DateUtility.getDate("20090525", "yyyyMMdd"));
            l_tempRestraint1.setAmount(10.0);
            WEB3TPTempRestraint l_tempRestraint2 = new WEB3TPTempRestraint();
            l_tempRestraint2.setRestraintDiv("2");
            l_tempRestraint2.setReflectStartDay(WEB3DateUtility.getDate("20070525", "yyyyMMdd"));
            l_tempRestraint2.setReflectEndDay(WEB3DateUtility.getDate("20090525", "yyyyMMdd"));
            l_tempRestraint2.setAmount(20.0);
            
            List tempRestraints = new ArrayList();
            tempRestraints.add(l_tempRestraint1);
            tempRestraints.add(l_tempRestraint2);
            // ���L�����I����
            Field field = WEB3TPRestraint.class.getDeclaredField("tempRestraints");
            field.setAccessible(true);
            field.set(l_restraint, tempRestraints);
            double l_dbl =
                l_restraint.calcTempRestraint(WEB3DateUtility.getDate("20080525", "yyyyMMdd"), null);
            assertEquals("30.0", l_dbl + "");
        }
        catch (Exception l_ex)
        {
            log.error("", l_ex);
            fail();
        }
        
        log.exiting(STR_METHOD_NAME);
    }
    
    
    public void testGetAccountCashBalanceReflectObjectRestraintDivListCase1()
    {
        final String STR_METHOD_NAME = "testGetAccountCashBalanceReflectObjectRestraintDivListCase1()";
        log.entering(STR_METHOD_NAME);
        try
        {
            WEB3TPRestraint l_restraint = new WEB3TPRestraint();

            String[] l_str =
                l_restraint.getAccountCashBalanceReflectObjectRestraintDivList();
            assertEquals("1", l_str[0]);
        }
        catch (Exception l_ex)
        {
            log.error("", l_ex);
            fail();
        }
        
        log.exiting(STR_METHOD_NAME);
    }
    
    
    public void testGetTempRestraintListCase1()
    {
        final String STR_METHOD_NAME = "testGetTempRestraintListCase1()";
        log.entering(STR_METHOD_NAME);
        try
        {
            WEB3TPRestraint l_restraint = new WEB3TPRestraint();
            WEB3TPTempRestraint l_tempRestraint1 = new WEB3TPTempRestraint();
            l_tempRestraint1.setRestraintDiv("1");
            l_tempRestraint1.setReflectStartDay(WEB3DateUtility.getDate("20070525", "yyyyMMdd"));
            l_tempRestraint1.setReflectEndDay(WEB3DateUtility.getDate("20090525", "yyyyMMdd"));
            l_tempRestraint1.setAmount(10.0);
            WEB3TPTempRestraint l_tempRestraint2 = new WEB3TPTempRestraint();
            l_tempRestraint2.setRestraintDiv("2");
            l_tempRestraint2.setReflectStartDay(WEB3DateUtility.getDate("20070525", "yyyyMMdd"));
            l_tempRestraint2.setReflectEndDay(WEB3DateUtility.getDate("20090525", "yyyyMMdd"));
            l_tempRestraint2.setAmount(20.0);
            WEB3TPTempRestraint l_tempRestraint3 = new WEB3TPTempRestraint();
            l_tempRestraint3.setRestraintDiv("2");
            l_tempRestraint3.setReflectStartDay(WEB3DateUtility.getDate("20070525", "yyyyMMdd"));
            l_tempRestraint3.setReflectEndDay(WEB3DateUtility.getDate("20090525", "yyyyMMdd"));
            l_tempRestraint3.setAmount(30.0);
            
            List tempRestraints = new ArrayList();
            tempRestraints.add(l_tempRestraint1);
            tempRestraints.add(l_tempRestraint2);
            tempRestraints.add(l_tempRestraint3);
            // ���L�����I����
            Field field = WEB3TPRestraint.class.getDeclaredField("tempRestraints");
            field.setAccessible(true);
            field.set(l_restraint, tempRestraints);
            String[] l_restraintDivList = new String[]{"1", "2"};
            List l_lis = l_restraint.getTempRestraintList(tempRestraints, l_restraintDivList);
            assertEquals(3, l_lis.size());
            assertEquals("10.0", ((WEB3TPTempRestraint)l_lis.get(0)).getAmount()+"");
            assertEquals("20.0", ((WEB3TPTempRestraint)l_lis.get(1)).getAmount()+"");
            assertEquals("30.0", ((WEB3TPTempRestraint)l_lis.get(2)).getAmount()+"");
        }
        catch (Exception l_ex)
        {
            log.error("", l_ex);
            fail();
        }
        
        log.exiting(STR_METHOD_NAME);
    }
    
    public void testGetTempRestraintListCase2()
    {
        final String STR_METHOD_NAME = "testGetTempRestraintListCase2()";
        log.entering(STR_METHOD_NAME);
        try
        {
            WEB3TPRestraint l_restraint = new WEB3TPRestraint();
            WEB3TPTempRestraint l_tempRestraint1 = new WEB3TPTempRestraint();
            l_tempRestraint1.setRestraintDiv("1");
            l_tempRestraint1.setReflectStartDay(WEB3DateUtility.getDate("20070525", "yyyyMMdd"));
            l_tempRestraint1.setReflectEndDay(WEB3DateUtility.getDate("20090525", "yyyyMMdd"));
            l_tempRestraint1.setAmount(10.0);
            WEB3TPTempRestraint l_tempRestraint2 = new WEB3TPTempRestraint();
            l_tempRestraint2.setRestraintDiv("2");
            l_tempRestraint2.setReflectStartDay(WEB3DateUtility.getDate("20070525", "yyyyMMdd"));
            l_tempRestraint2.setReflectEndDay(WEB3DateUtility.getDate("20090525", "yyyyMMdd"));
            l_tempRestraint2.setAmount(20.0);
            WEB3TPTempRestraint l_tempRestraint3 = new WEB3TPTempRestraint();
            l_tempRestraint3.setRestraintDiv("2");
            l_tempRestraint3.setReflectStartDay(WEB3DateUtility.getDate("20070525", "yyyyMMdd"));
            l_tempRestraint3.setReflectEndDay(WEB3DateUtility.getDate("20090525", "yyyyMMdd"));
            l_tempRestraint3.setAmount(30.0);
            
            List tempRestraints = new ArrayList();
            tempRestraints.add(l_tempRestraint1);
            tempRestraints.add(l_tempRestraint2);
            tempRestraints.add(l_tempRestraint3);
            // ���L�����I����
            Field field = WEB3TPRestraint.class.getDeclaredField("tempRestraints");
            field.setAccessible(true);
            field.set(l_restraint, tempRestraints);
            String[] l_restraintDivList = new String[]{"2", "2"};
            List l_lis = l_restraint.getTempRestraintList(tempRestraints, l_restraintDivList);
            assertEquals(2, l_lis.size());
            assertEquals("20.0", ((WEB3TPTempRestraint)l_lis.get(0)).getAmount()+"");
            assertEquals("30.0", ((WEB3TPTempRestraint)l_lis.get(1)).getAmount()+"");
        }
        catch (Exception l_ex)
        {
            log.error("", l_ex);
            fail();
        }
        
        log.exiting(STR_METHOD_NAME);
    }
    
    public void testGetTempRestraintListCase3()
    {
        final String STR_METHOD_NAME = "testGetTempRestraintListCase3()";
        log.entering(STR_METHOD_NAME);
        try
        {
            WEB3TPRestraint l_restraint = new WEB3TPRestraint();
            WEB3TPTempRestraint l_tempRestraint1 = new WEB3TPTempRestraint();
            l_tempRestraint1.setRestraintDiv("0");
            l_tempRestraint1.setReflectStartDay(WEB3DateUtility.getDate("20070525", "yyyyMMdd"));
            l_tempRestraint1.setReflectEndDay(WEB3DateUtility.getDate("20090525", "yyyyMMdd"));
            l_tempRestraint1.setAmount(10.0);
            WEB3TPTempRestraint l_tempRestraint2 = new WEB3TPTempRestraint();
            l_tempRestraint2.setRestraintDiv("0");
            l_tempRestraint2.setReflectStartDay(WEB3DateUtility.getDate("20070525", "yyyyMMdd"));
            l_tempRestraint2.setReflectEndDay(WEB3DateUtility.getDate("20090525", "yyyyMMdd"));
            l_tempRestraint2.setAmount(20.0);
            WEB3TPTempRestraint l_tempRestraint3 = new WEB3TPTempRestraint();
            l_tempRestraint3.setRestraintDiv("0");
            l_tempRestraint3.setReflectStartDay(WEB3DateUtility.getDate("20070525", "yyyyMMdd"));
            l_tempRestraint3.setReflectEndDay(WEB3DateUtility.getDate("20090525", "yyyyMMdd"));
            l_tempRestraint3.setAmount(30.0);
            
            List tempRestraints = new ArrayList();
            tempRestraints.add(l_tempRestraint1);
            tempRestraints.add(l_tempRestraint2);
            tempRestraints.add(l_tempRestraint3);
            // ���L�����I����
            Field field = WEB3TPRestraint.class.getDeclaredField("tempRestraints");
            field.setAccessible(true);
            field.set(l_restraint, tempRestraints);
            String[] l_restraintDivList = new String[]{"1", "2"};
            List l_lis = l_restraint.getTempRestraintList(tempRestraints, l_restraintDivList);
            assertEquals(0, l_lis.size());
        }
        catch (Exception l_ex)
        {
            log.error("", l_ex);
            fail();
        }
        
        log.exiting(STR_METHOD_NAME);
    }
}
@
