head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.04.07.02.35.17;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	5444d9d078c63f4;
filename	WEB3TPCashValuationTest.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
File Name        : //TODO(WEB3TPCashValuationTest.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2006/09/19 ���G�� (���u) �V�K�쐬
*/
package webbroker3.tradingpower.updapower.cash;

import java.util.Date;

import com.fitechlabs.xtrade.kernel.boot.Services;
import com.fitechlabs.xtrade.kernel.data.Processors;
import com.fitechlabs.xtrade.kernel.data.QueryProcessor;
import com.fitechlabs.xtrade.plugin.tc.gentrade.FinApp;
import com.fitechlabs.xtrade.plugin.tc.gentrade.data.MainAccountDao;
import com.fitechlabs.xtrade.plugin.tc.gentrade.data.MainAccountParams;
import com.fitechlabs.xtrade.plugin.tc.gentrade.data.MainAccountRow;
import com.fitechlabs.xtrade.plugin.tc.gentrade.data.SubAccountParams;

import test.util.TestDBUtility;

import webbroker3.common.WEB3BaseException;
import webbroker3.common.define.WEB3OrderAccProductDef;
import webbroker3.common.define.WEB3OrderAccTransactionDef;
import webbroker3.common.define.WEB3TradingTimeTypeDef;
import webbroker3.gentrade.WEB3GentradeMainAccount;
import webbroker3.gentrade.WEB3GentradeSubAccount;
import webbroker3.mock.TestBaseForMock;
import webbroker3.tradingpower.WEB3TPCalcCondition;
import webbroker3.tradingpower.WEB3TPTradingPowerUpd;
import webbroker3.tradingpower.updtpower.WEB3TPAccountInfo;
import webbroker3.tradingpower.updtpower.WEB3TPAssetValuation;
import webbroker3.tradingpower.updtpower.WEB3TPNewOrderSpec;
import webbroker3.tradingpower.updtpower.cash.WEB3TPCashBalance;
import webbroker3.tradingpower.updtpower.cash.WEB3TPCashBalanceReflector;
import webbroker3.tradingpower.updtpower.cash.WEB3TPCashValuation;
import webbroker3.tradingpower.updtpower.cash.WEB3TPRestraint;
import webbroker3.tradingpower.updtpower.cash.WEB3TPTransactionAmount;
import webbroker3.tradingpower.updtpower.cash.WEB3TPTransactionReflector;
import webbroker3.util.WEB3DateUtility;
import webbroker3.util.WEB3LogUtility;

/**
 * XXXXXX�N���X//TODO
 *
 * @@author ���G��(���u)
 * @@version 1.0
 */
public class WEB3TPCashValuationTest extends TestBaseForMock
{
    /**
     * ���O�o�̓��[�e�B���e�B�B
     */
    private static WEB3LogUtility log = 
        WEB3LogUtility.getInstance(WEB3TPCashValuationTest.class);
    
    private WEB3TPCashValuation l_valuation = new WEB3TPCashValuation();
    
    private FinApp l_finApp = (FinApp)Services.getService(FinApp.class);
    
    public WEB3TPCashValuationTest(String arg0)
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
//                "0",
//                "670",
//                "07",
//                WEB3TradingTimeTypeDef.PAYMENT,
//                "0",
//                WEB3OrderAccProductDef.PAYMENT,
//                WEB3OrderAccTransactionDef.PAYMENT);
        }

    protected void tearDown() throws Exception 
    {
        doClearTradingClendarContext();
        super.tearDown();
    }
    
    /*
     * [a.�ȊO(�،��S�ۃ��[�������{�j�̏ꍇ]
     */
    public void testCaleCashDepositRestraint_case001()
    {
        final String STR_METHOD_NAME = "testCaleCashDepositRestraint_case001()";
        log.entering(STR_METHOD_NAME);
        
        try
        {
            WEB3GentradeSubAccount l_subAccount = 
                (WEB3GentradeSubAccount)l_finApp.getAccountManager().getSubAccount(266242662410L, 26624266241001L);
            
            //1.2.�ڋq���擾
            WEB3GentradeMainAccount l_mainAccount =
                (WEB3GentradeMainAccount)l_subAccount.getMainAccount();
            
            //1.22.5.�ڋq�I�u�W�F�N�g���A�ڋq�}�X�^�̍s�I�u�W�F�N�g���擾����B
            MainAccountRow l_mainAccountRow = (MainAccountRow)l_mainAccount.getDataSourceObject();
            MainAccountParams l_params = new MainAccountParams(l_mainAccountRow);
            l_params.setSecuredLoanSecAccOpenDiv("0");
            
            QueryProcessor l_processor = Processors.getDefaultProcessor();
            l_processor.doUpdateQuery(l_params);
            
            WEB3TPCalcCondition l_calcCondition = WEB3TPCalcCondition.createCalcConditionStandard(l_subAccount);
            Date l_datBizDate = WEB3DateUtility.getDate("20060808", "yyyyMMdd");
            l_calcCondition.setSecuredLoanSecAccOpenDiv(false);
            this.l_valuation.setCalcCondition(l_calcCondition);
            
            double l_dblCashDepositRestraint = 
                this.l_valuation.caleCashDepositRestraint(l_datBizDate);
            assertEquals(0, l_dblCashDepositRestraint, 0);
        }
        catch (Exception l_ex)
        {
            log.error("Error in Exception....", l_ex);
        }
        log.exiting(STR_METHOD_NAME);
    }
    
    /*
     * [a.�ȊO(�،��S�ۃ��[�������{�j�̏ꍇ]
     */
    public void testCaleCashDepositRestraint_case002()
    {
        final String STR_METHOD_NAME = "testCaleCashDepositRestraint_case002()";
        log.entering(STR_METHOD_NAME);
        
        try
        {
            WEB3GentradeSubAccount l_subAccount = 
                (WEB3GentradeSubAccount)l_finApp.getAccountManager().getSubAccount(266242662410L, 26624266241001L);

            WEB3TPCalcCondition l_calcCondition = WEB3TPCalcCondition.createCalcConditionStandard(l_subAccount);
            
            l_calcCondition.setSecuredLoanSecAccOpenDiv(true);
            
            WEB3TPAccountInfo l_accountInfo = WEB3TPAccountInfo.create(
                266242662410L,
                true);
            
            //���������e[]
            WEB3TPNewOrderSpec[] l_newOrderSpecs = new WEB3TPNewOrderSpec[3];
            WEB3TPCashValuation l_valuation = WEB3TPCashValuation.create(
                l_accountInfo,
                l_calcCondition, l_newOrderSpecs);
            
            Date l_datBizDate = WEB3DateUtility.getDate("20060808", "yyyyMMdd");
            l_valuation.setCalcCondition(l_calcCondition);
            
            double l_dblCashDepositRestraint = 
                l_valuation.caleCashDepositRestraint(l_datBizDate);
            assertEquals(50, l_dblCashDepositRestraint, 0);
        }
        catch (Exception l_ex)
        {
            log.error("Error in Exception....", l_ex);
        }
        log.exiting(STR_METHOD_NAME);
    }
    
    public void testCalcCashoutRestraint_case001()
    {
        final String STR_METHOD_NAME = "testCalcCashoutRestraint_case001()";
        log.entering(STR_METHOD_NAME);
        
        try
        {
            TestDBUtility.deleteAll(MainAccountParams.TYPE);
            MainAccountParams l_MainAccountParams = TestDBUtility.getMainAccountRow();
            l_MainAccountParams.setAccountId(333812512203L);
            TestDBUtility.insertWithDel(l_MainAccountParams);
            TestDBUtility.deleteAll(SubAccountParams.TYPE);
            SubAccountParams l_SubAccountParams = TestDBUtility.getSubAccountRow();
            TestDBUtility.insertWithDel(l_SubAccountParams);
//            WEB3GentradeSubAccount l_subAccount = 
//                (WEB3GentradeSubAccount)l_finApp.getAccountManager().getSubAccount(333812512203L, 33381251220301L);

            WEB3TPCalcCondition l_calcCondition = new WEB3TPCalcCondition(); //WEB3TPCalcCondition.createCalcConditionStandard(l_subAccount);
            
            l_calcCondition.setSecuredLoanSecAccOpenDiv(true);
            
            WEB3TPAccountInfo l_accountInfo = WEB3TPAccountInfo.create(
                    333812512203L,
                true);
            
            //���������e[]
            WEB3TPNewOrderSpec[] l_newOrderSpecs = new WEB3TPNewOrderSpec[3];
            WEB3TPCashValuation l_valuation = WEB3TPCashValuation.create(
                l_accountInfo,
                l_calcCondition,
                l_newOrderSpecs);
//            WEB3TPTransactionReflector l_transaction = new WEB3TPTransactionReflector();
            WEB3TPTransactionAmountForTest l_transactionAmount = new WEB3TPTransactionAmountForTest();
            WEB3TPCashBalanceForTest l_cashBalance = new WEB3TPCashBalanceForTest();

            l_valuation.setTransactionAmount(l_transactionAmount);
            l_valuation.setCashBalance(l_cashBalance);
//            Date l_datBizDate = WEB3DateUtility.getDate("20060808", "yyyyMMdd");
            l_valuation.setCalcCondition(l_calcCondition);
            
            double l_dblCashoutRestraint = 
                l_valuation.calcCashoutRestraint(1000, 150, 400, 250);
            
            assertEquals(750.0, l_dblCashoutRestraint, 0);
        }
        catch (Exception l_ex)
        {
            log.error(STR_METHOD_NAME, l_ex);
            log.exiting(TEST_END + STR_METHOD_NAME);
            fail();
        }
        log.exiting(STR_METHOD_NAME);
    }
    
    public void testCalcCashoutRestraint_case002()
    {
        final String STR_METHOD_NAME = "testCalcCashoutRestraint_case002()";
        log.entering(STR_METHOD_NAME);
        
        try
        {
            TestDBUtility.deleteAll(MainAccountParams.TYPE);
            MainAccountParams l_MainAccountParams = TestDBUtility.getMainAccountRow();
            l_MainAccountParams.setAccountId(333812512203L);
            TestDBUtility.insertWithDel(l_MainAccountParams);
            TestDBUtility.deleteAll(SubAccountParams.TYPE);
            SubAccountParams l_SubAccountParams = TestDBUtility.getSubAccountRow();
            TestDBUtility.insertWithDel(l_SubAccountParams);
//            WEB3GentradeSubAccount l_subAccount = 
//                (WEB3GentradeSubAccount)l_finApp.getAccountManager().getSubAccount(333812512203L, 33381251220301L);

            WEB3TPCalcCondition l_calcCondition = new WEB3TPCalcCondition(); //WEB3TPCalcCondition.createCalcConditionStandard(l_subAccount);
            
            l_calcCondition.setSecuredLoanSecAccOpenDiv(true);
            
            WEB3TPAccountInfo l_accountInfo = WEB3TPAccountInfo.create(
                    333812512203L,
                true);
            
            //���������e[]
            WEB3TPNewOrderSpec[] l_newOrderSpecs = new WEB3TPNewOrderSpec[3];
            WEB3TPCashValuation l_valuation = WEB3TPCashValuation.create(
                l_accountInfo,
                l_calcCondition,
                l_newOrderSpecs);
//            WEB3TPTransactionReflector l_transaction = new WEB3TPTransactionReflector();
            WEB3TPTransactionAmountForTest l_transactionAmount = new WEB3TPTransactionAmountForTest();
            WEB3TPCashBalanceForTest l_cashBalance = new WEB3TPCashBalanceForTest();

            l_valuation.setTransactionAmount(l_transactionAmount);
            l_valuation.setCashBalance(l_cashBalance);
//            Date l_datBizDate = WEB3DateUtility.getDate("20060808", "yyyyMMdd");
            l_valuation.setCalcCondition(l_calcCondition);
            
            double l_dblCashoutRestraint = 
                l_valuation.calcCashoutRestraint(2000, 150, 400, 250);
            
            assertEquals(0.0, l_dblCashoutRestraint, 0);
        }
        catch (Exception l_ex)
        {
            log.error(STR_METHOD_NAME, l_ex);
            log.exiting(TEST_END + STR_METHOD_NAME);
            fail();
        }
        log.exiting(STR_METHOD_NAME);
    }
    
    public void testCalcCashoutRestraint_case003()
    {
        final String STR_METHOD_NAME = "testCalcCashoutRestraint_case003()";
        log.entering(STR_METHOD_NAME);
        
        try
        {
            TestDBUtility.deleteAll(MainAccountParams.TYPE);
            MainAccountParams l_MainAccountParams = TestDBUtility.getMainAccountRow();
            l_MainAccountParams.setAccountId(333812512203L);
            TestDBUtility.insertWithDel(l_MainAccountParams);
            TestDBUtility.deleteAll(SubAccountParams.TYPE);
            SubAccountParams l_SubAccountParams = TestDBUtility.getSubAccountRow();
            TestDBUtility.insertWithDel(l_SubAccountParams);
//            WEB3GentradeSubAccount l_subAccount = 
//                (WEB3GentradeSubAccount)l_finApp.getAccountManager().getSubAccount(333812512203L, 33381251220301L);

            WEB3TPCalcCondition l_calcCondition = new WEB3TPCalcCondition(); //WEB3TPCalcCondition.createCalcConditionStandard(l_subAccount);
            
            l_calcCondition.setSecuredLoanSecAccOpenDiv(true);
            
            WEB3TPAccountInfo l_accountInfo = WEB3TPAccountInfo.create(
                    333812512203L,
                true);
            
            //���������e[]
            WEB3TPNewOrderSpec[] l_newOrderSpecs = new WEB3TPNewOrderSpec[3];
            WEB3TPCashValuation l_valuation = WEB3TPCashValuation.create(
                l_accountInfo,
                l_calcCondition,
                l_newOrderSpecs);
//            WEB3TPTransactionReflector l_transaction = new WEB3TPTransactionReflector();
            WEB3TPTransactionAmountForTest l_transactionAmount = new WEB3TPTransactionAmountForTest();
            WEB3TPCashBalanceForTest l_cashBalance = new WEB3TPCashBalanceForTest();

            l_valuation.setTransactionAmount(l_transactionAmount);
            l_valuation.setCashBalance(l_cashBalance);
//            Date l_datBizDate = WEB3DateUtility.getDate("20060808", "yyyyMMdd");
            l_valuation.setCalcCondition(l_calcCondition);
            
            double l_dblCashoutRestraint = 
                l_valuation.calcCashoutRestraint(300, 150, 400, 250);
            
            assertEquals(1000.0, l_dblCashoutRestraint, 0);
        }
        catch (Exception l_ex)
        {
            log.error(STR_METHOD_NAME, l_ex);
            log.exiting(TEST_END + STR_METHOD_NAME);
            fail();
        }
        log.exiting(STR_METHOD_NAME);
    }
    
    public class WEB3TPTransactionAmountForTest extends WEB3TPTransactionAmount
    {
        /**
         * (calc�����o���z)<BR>
         * @@return double
         */
        public double clacNextBizDateCashoutAmount()
        {
            final String STR_METHOD_NAME = "clacNextBizDateCashoutAmount()";
            log.entering(STR_METHOD_NAME);
            
            log.exiting(STR_METHOD_NAME);
            return 1000;
        }
        
        /**
         * (calc�����ȍ~�a����ۏ؋��Ԃ̐U�֊z)<BR>
         * @@return double
         * @@throws WEB3BaseException
         */
        public double calcTodayMarginDepositTransferAmount() throws WEB3BaseException
        {
            final String STR_METHOD_NAME = "calcTodayMarginDepositTransferAmount()";
            log.entering(STR_METHOD_NAME);
            log.exiting(STR_METHOD_NAME);
            return 150;
        }
    }
    /**
     * (�a���)<BR>
     * �a����c���̐��ڂ�\������B<BR>
     */
    public class WEB3TPCashBalanceForTest extends WEB3TPCashBalance
    {
        /**
         * (calc�m��ۏ؋�)<BR>
         * (calc�m��ۏ؋�) <BR>
         * 
         * ����.�w����̊m��ۏ؋����W�v���ĕԋp����B <BR>
         * <BR>
         * �P�j�m��ۏ؋����W�v����B <BR>
         * �@@ <BR>
         * �@@��LOOP�����Fthis.�m��a����ꗗ�̗v�f���� <BR>
         * <BR>
         * �@@�@@�@@�m��a����ꗗ���A�v�f�I�u�W�F�N�g(="�m��a���")���擾����B <BR>
         * <BR>
         * �@@�@@�@@"�m��a���" = this.�m��a����ꗗ.get(:int = LOOP����Index����) <BR>
         * <BR>
         * �@@�@@�A"�m��ۏ؋�"���W�v����B <BR>
         * <BR>
         * �@@�@@�@@[a."�m��a���".is�ϓ����f���Ԓ�(:Date = ����.�w���) == true ���@@"�m��a���".is�ۏ؋�() == true] <BR>
         * <BR>
         * �@@�@@�@@�@@"�m��ۏ؋�" += "�m��a���".get�a����c��() <BR>
         * <BR>
         * �Q�j�W�v����"�m��ۏ؋�"��ԋp����B <BR>
         * <BR>
         * �@@�ԋp�l�F"�m��ۏ؋�" <BR>
         * @@param l_datDate -   (�w���)<BR>
         * �w���<BR>
         * @@return double
         */
        public double calcFixedDeposit(Date l_datDate)
        {
            final String STR_METHOD_NAME = "calcFixedDeposit(Date)";
            log.entering(STR_METHOD_NAME);
            log.exiting(STR_METHOD_NAME);
            return 100;
        }
    }
}
@
