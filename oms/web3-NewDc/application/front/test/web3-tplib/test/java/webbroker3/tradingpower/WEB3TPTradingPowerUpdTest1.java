head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.04.07.00.50.06;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	5444d9d078c63f4;
filename	WEB3TPTradingPowerUpdTest1.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (��)��a���� �،��\�����[�V�����V�X�e�����
File Name        : �]�͍X�V�T�[�r�X�e�X�g(WEB3TPTradingPowerUpdTest1.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2007/01/24 ���؎q (���u) �V�K�쐬
*/
package webbroker3.tradingpower;

import java.lang.reflect.Method;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import test.util.TestDBUtility;
import webbroker3.mock.TestBaseForMock;
import webbroker3.tradingpower.data.TpCalcResultEquityDetailParams;
import webbroker3.tradingpower.data.TpCalcResultEquityParams;
import webbroker3.tradingpower.data.TpCalcResultMarginDetailParams;
import webbroker3.tradingpower.data.TpCalcResultMarginParams;
import webbroker3.tradingpower.updtpower.WEB3TPAccountInfo;
import webbroker3.tradingpower.updtpower.WEB3TPAssetReflector;
import webbroker3.tradingpower.updtpower.WEB3TPAssetValuation;
import webbroker3.tradingpower.updtpower.asset.WEB3TPSecurityValuation;
import webbroker3.tradingpower.updtpower.cash.WEB3TPCashBalance;
import webbroker3.tradingpower.updtpower.cash.WEB3TPCashValuation;
import webbroker3.tradingpower.updtpower.cash.WEB3TPRestraint;
import webbroker3.tradingpower.updtpower.cash.WEB3TPTodayDepFundRestraintReflector;
import webbroker3.tradingpower.updtpower.cash.WEB3TPTransactionAmount;
import webbroker3.tradingpower.updtpower.contract.WEB3TPContractInfo;
import webbroker3.tradingpower.updtpower.contract.WEB3TPSummaryDayTradeSwapContract;
import webbroker3.tradingpower.updtpower.contract.WEB3TPSummaryOpenContract;
import webbroker3.tradingpower.updtpower.contract.WEB3TPSummaryUndeliveredContract;
import webbroker3.tradingpower.updtpower.settlement.WEB3TPSettlement;
import webbroker3.util.WEB3DateUtility;
import webbroker3.util.WEB3LogUtility;

import com.fitechlabs.xtrade.plugin.tc.gentrade.BranchTypeEnum;
import com.fitechlabs.xtrade.plugin.tc.gentrade.ProductTypeEnum;

/**
 * (�]�͍X�V�T�[�r�X�e�X�g)<BR>
 * <BR>
 *
 * @@author ���؎q (fitechlabs)
 * @@version 1.0
 */
public class WEB3TPTradingPowerUpdTest1 extends TestBaseForMock
{
    /**
     * ���O�o�̓��[�e�B���e�B�B<BR>
     */
    private static WEB3LogUtility log =
        WEB3LogUtility.getInstance(WEB3TPTradingPowerUpdTest1.class);

	public WEB3TPTradingPowerUpdTest1(String arg0)
	{
		super(arg0);
	}

	protected void setUp() throws Exception
	{
        final String STR_METHOD_NAME = "setUp()";
        log.entering(TEST_START + STR_METHOD_NAME);

		super.setUp();
	}

	protected void tearDown() throws Exception
	{
		super.tearDown();
	}

	public void test_calcTradingpowerUpdResultMargin_0001()
	{
        final String STR_METHOD_NAME = "test_calcTradingpowerUpdResultMargin_0001()";
		WEB3TPTodayDepFundRestraintReflector l_WEB3TPTodayDepFundRestraintReflector = new WEB3TPTodayDepFundRestraintReflector();
		
		l_WEB3TPTodayDepFundRestraintReflector.setFinTransactionDate(WEB3DateUtility.getDate("20040202","yyyyMMdd"));
		
		Date[] l_datBizDate = new Date[]{WEB3DateUtility.getDate("20040301","yyyyMMdd"),
			WEB3DateUtility.getDate("20040302","yyyyMMdd"),
			WEB3DateUtility.getDate("20040303","yyyyMMdd"),	
			WEB3DateUtility.getDate("20040304","yyyyMMdd"),	
			WEB3DateUtility.getDate("20040305","yyyyMMdd"),	
			WEB3DateUtility.getDate("20040306","yyyyMMdd"),	
			WEB3DateUtility.getDate("20040307","yyyyMMdd"),	
	        WEB3DateUtility.getDate("20040308","yyyyMMdd")};	
			
		WEB3TPCalcCondition l_WEB3TPCalcCondition = new WEB3TPCalcCondition();

		l_WEB3TPCalcCondition.setBizDate(l_datBizDate);
		l_WEB3TPCalcCondition.setBranchType(BranchTypeEnum.MAIN_BRANCH);
		
		WEB3TPAccountInfo l_WEB3TPAccountInfo = new WEB3TPAccountInfo();
		l_WEB3TPAccountInfo.setAccountId(333812512203L);
		
		WEB3TPAssetReflectorForTest l_WEB3TPAssetReflector = new WEB3TPAssetReflectorForTest();
		
		l_WEB3TPAssetReflector.setReflectStartDay(WEB3DateUtility.getDate("20040202","yyyyMMdd"));
		l_WEB3TPAssetReflector.setReflectEndDay(WEB3DateUtility.getDate("20040310","yyyyMMdd"));
		
		l_WEB3TPAssetReflector.setCalcCondition(l_WEB3TPCalcCondition);
		
		WEB3TPRestraint l_WEB3TPRestraint = new WEB3TPRestraint();
		
		WEB3TPTodayDepFundRestraintReflector l_serviceChargeRestraint = 
			WEB3TPTodayDepFundRestraintReflector.create(l_WEB3TPCalcCondition, 1000.0D, null, WEB3DateUtility.getDate("20040308","yyyyMMdd"));
		
		try
		{
			Method method = WEB3TPRestraint.class.getDeclaredMethod("addTodayFundDepRestraint", 
					new Class[] {WEB3TPTodayDepFundRestraintReflector.class});
				method.setAccessible(true);
				method.invoke(l_WEB3TPRestraint, new Object[] {l_serviceChargeRestraint});
		}
		catch (Exception e)
		{
			log.error(STR_METHOD_NAME, e);
			fail();
		}

		WEB3TPCashValuationForTest l_WEB3TPCashValuation = new WEB3TPCashValuationForTest();
		WEB3TPCashBalance l_WEB3TPCashBalance = new WEB3TPCashBalance();
		WEB3TPTransactionAmount l_WEB3TPTransactionAmount = new WEB3TPTransactionAmount();
		WEB3TPSecurityValuationForTest l_WEB3TPSecurityValuation = new WEB3TPSecurityValuationForTest();
		
		WEB3TPSettlementForTest l_WEB3TPSettlement = new WEB3TPSettlementForTest(l_WEB3TPCashValuation,
				l_WEB3TPSecurityValuation,
		        l_WEB3TPCalcCondition);
		
		l_WEB3TPCashValuation.setCashBalance(l_WEB3TPCashBalance);
		l_WEB3TPCashValuation.setTransactionAmount(l_WEB3TPTransactionAmount);
		l_WEB3TPCashValuation.setRestraint(l_WEB3TPRestraint);
        
        WEB3TPContractInfoForTest l_WEB3TPContractInfo = new WEB3TPContractInfoForTest();

        
		WEB3TPTradingPowerUpdForTest l_WEB3TPTradingPowerUpd = new WEB3TPTradingPowerUpdForTest
		    (l_WEB3TPCalcCondition, 
                l_WEB3TPAccountInfo, 
                l_WEB3TPCashValuation, 
                l_WEB3TPSettlement , 
                l_WEB3TPContractInfo,
                l_WEB3TPSecurityValuation);
        
		
		try
		{
			List l_listTradingpowerUpdResultMargin = l_WEB3TPTradingPowerUpd.calcTradingpowerUpdResultMargin("111");
			
			double l_dblWEB3TPRestraint0 = 
				((TpCalcResultMarginParams)l_listTradingpowerUpdResultMargin.get(0)).getContractTotalCost();
				
			double l_dblWEB3TPRestraint1 = 
				((TpCalcResultMarginParams)l_listTradingpowerUpdResultMargin.get(0)).getContractTotalCost1();
				
			double l_dblWEB3TPRestraint2 = 
				((TpCalcResultMarginParams)l_listTradingpowerUpdResultMargin.get(0)).getContractTotalCost2();
				
			double l_dblWEB3TPRestraint3 = 
				((TpCalcResultMarginParams)l_listTradingpowerUpdResultMargin.get(0)).getContractTotalCost3();
				
			double l_dblWEB3TPRestraint4 = 
				((TpCalcResultMarginParams)l_listTradingpowerUpdResultMargin.get(0)).getContractTotalCost4();
				
			double l_dblWEB3TPRestraint5 = 
				((TpCalcResultMarginParams)l_listTradingpowerUpdResultMargin.get(0)).getContractTotalCost5();
            
			assertEquals(219D,l_dblWEB3TPRestraint0,0);
            assertEquals(219D,l_dblWEB3TPRestraint1,0);
            assertEquals(219D,l_dblWEB3TPRestraint2,0);
            assertEquals(219D,l_dblWEB3TPRestraint3,0);
            assertEquals(219D,l_dblWEB3TPRestraint4,0);
            assertEquals(219D,l_dblWEB3TPRestraint5,0);
		}
		catch (Exception l_ex)
		{
			log.error(STR_METHOD_NAME, l_ex);
			fail();
		}
	}
    
//    public void testCalcTradingpowerUpdResultEquityIncUnexecSellOrder_C0001()
//    {
//        final String STR_METHOD_NAME = "testCalcTradingpowerUpdResultEquityIncUnexecSellOrder_C0001()";
//        log.entering(TEST_START + STR_METHOD_NAME);
//        
//        WEB3TPTradingPowerUpd l_tradingPowerUpd = new WEB3TPTradingPowerUpdForTest1();
//        l_tradingPowerUpd.calcCondition = new WEB3TPCalcCondition();
//        
//        Date[] l_datBizDate = new Date[7];
//        l_datBizDate[0] = WEB3DateUtility.getDate("20070925", "yyyyMMdd");
//        l_datBizDate[1] = WEB3DateUtility.getDate("20070927", "yyyyMMdd");
//        l_datBizDate[2] = WEB3DateUtility.getDate("20070925", "yyyyMMdd");
//        l_datBizDate[3] = WEB3DateUtility.getDate("20070927", "yyyyMMdd");
//        l_datBizDate[4] = WEB3DateUtility.getDate("20070925", "yyyyMMdd");
//        l_datBizDate[5] = WEB3DateUtility.getDate("20070927", "yyyyMMdd");
//        l_datBizDate[6] = WEB3DateUtility.getDate("20070925", "yyyyMMdd");
//        l_tradingPowerUpd.calcCondition.setBizDate(l_datBizDate);
//        
//        l_tradingPowerUpd.settlement = new WEB3TPSettlementForTest(null, null, null);
//        try
//        {
//            List l_lisResult = l_tradingPowerUpd.calcTradingpowerUpdResultEquityIncUnexecSellOrder(false);
//            assertEquals(new Double(1.0), new Double(((TpCalcResultEquityParams)l_lisResult.get(0)).getDayTradeRestraint0()));
//            assertEquals(new Double(1.0), new Double(((TpCalcResultEquityParams)l_lisResult.get(0)).getDayTradeRestraint1()));
//            assertEquals(new Double(1.0), new Double(((TpCalcResultEquityParams)l_lisResult.get(0)).getDayTradeRestraint2()));
//            assertEquals(new Double(1.0), new Double(((TpCalcResultEquityParams)l_lisResult.get(0)).getDayTradeRestraint3()));
//            assertEquals(new Double(1.0), new Double(((TpCalcResultEquityParams)l_lisResult.get(0)).getDayTradeRestraint4()));
//            assertEquals(new Double(1.0), new Double(((TpCalcResultEquityParams)l_lisResult.get(0)).getDayTradeRestraint5()));
//        }
//        catch (Exception l_ex)
//        {
//            log.error(STR_METHOD_NAME, l_ex);
//            log.exiting(TEST_END + STR_METHOD_NAME);
//            fail();
//        }
//        log.exiting(TEST_END + STR_METHOD_NAME);
//    }
//    
//    public void testcalcTradingpowerUpdResultMarginIncUnexecSellOrder_C0001()
//    {
//        final String STR_METHOD_NAME = "testcalcTradingpowerUpdResultMarginIncUnexecSellOrder_C0001()";
//        log.entering(TEST_START + STR_METHOD_NAME);
//        
//        WEB3TPTradingPowerUpd l_tradingPowerUpd = new WEB3TPTradingPowerUpdForTest1();
//        l_tradingPowerUpd.calcCondition = new WEB3TPCalcCondition();
//        
//        Date[] l_datBizDate = new Date[7];
//        l_datBizDate[0] = WEB3DateUtility.getDate("20070925", "yyyyMMdd");
//        l_datBizDate[1] = WEB3DateUtility.getDate("20070927", "yyyyMMdd");
//        l_datBizDate[2] = WEB3DateUtility.getDate("20070925", "yyyyMMdd");
//        l_datBizDate[3] = WEB3DateUtility.getDate("20070927", "yyyyMMdd");
//        l_datBizDate[4] = WEB3DateUtility.getDate("20070925", "yyyyMMdd");
//        l_datBizDate[5] = WEB3DateUtility.getDate("20070927", "yyyyMMdd");
//        l_datBizDate[6] = WEB3DateUtility.getDate("20070925", "yyyyMMdd");
//        l_tradingPowerUpd.calcCondition.setBizDate(l_datBizDate);
//        
//        l_tradingPowerUpd.settlement = new WEB3TPSettlementForTest(null, null, null);
//        try
//        {
//            List l_lisResult = l_tradingPowerUpd.calcTradingpowerUpdResultMarginIncUnexecSellOrder();
//            assertEquals(new Double(9.0), new Double(((TpCalcResultMarginParams)l_lisResult.get(0)).getDayTradeRestraint0()));
//            assertEquals(new Double(9.0), new Double(((TpCalcResultMarginParams)l_lisResult.get(0)).getDayTradeRestraint1()));
//            assertEquals(new Double(9.0), new Double(((TpCalcResultMarginParams)l_lisResult.get(0)).getDayTradeRestraint2()));
//            assertEquals(new Double(9.0), new Double(((TpCalcResultMarginParams)l_lisResult.get(0)).getDayTradeRestraint3()));
//            assertEquals(new Double(9.0), new Double(((TpCalcResultMarginParams)l_lisResult.get(0)).getDayTradeRestraint4()));
//            assertEquals(new Double(9.0), new Double(((TpCalcResultMarginParams)l_lisResult.get(0)).getDayTradeRestraint5()));
//        }
//        catch (Exception l_ex)
//        {
//            log.error(STR_METHOD_NAME, l_ex);
//            log.exiting(TEST_END + STR_METHOD_NAME);
//            fail();
//        }
//        log.exiting(TEST_END + STR_METHOD_NAME);
//    }
//    
//    public void testCalcTradingpowerUpdResultEquityIncUnexecSellOrder_C0002()
//    {
//        final String STR_METHOD_NAME = "testCalcTradingpowerUpdResultEquityIncUnexecSellOrder_C0002()";
//        log.entering(TEST_START + STR_METHOD_NAME);
//        
//        WEB3TPTradingPowerUpd l_tradingPowerUpd = new WEB3TPTradingPowerUpdForTest1();
//        l_tradingPowerUpd.calcCondition = new WEB3TPCalcCondition();
//        
//        Date[] l_datBizDate = new Date[7];
//        l_datBizDate[0] = WEB3DateUtility.getDate("20070925", "yyyyMMdd");
//        l_datBizDate[1] = WEB3DateUtility.getDate("20070927", "yyyyMMdd");
//        l_datBizDate[2] = WEB3DateUtility.getDate("20070925", "yyyyMMdd");
//        l_datBizDate[3] = WEB3DateUtility.getDate("20070927", "yyyyMMdd");
//        l_datBizDate[4] = WEB3DateUtility.getDate("20070925", "yyyyMMdd");
//        l_datBizDate[5] = WEB3DateUtility.getDate("20070927", "yyyyMMdd");
//        l_datBizDate[6] = WEB3DateUtility.getDate("20070925", "yyyyMMdd");
//        l_tradingPowerUpd.calcCondition.setBizDate(l_datBizDate);
//        
//        l_tradingPowerUpd.settlement = new WEB3TPSettlementForTest(null, null, null);
//        try
//        {
//            List l_lisResult = l_tradingPowerUpd.calcTradingpowerUpdResultEquityIncUnexecSellOrder(true);
//            assertEquals(new Double(0.0), new Double(((TpCalcResultEquityParams)l_lisResult.get(0)).getDayTradeRestraint0()));
//            assertEquals(new Double(0.0), new Double(((TpCalcResultEquityParams)l_lisResult.get(0)).getDayTradeRestraint1()));
//            assertEquals(new Double(0.0), new Double(((TpCalcResultEquityParams)l_lisResult.get(0)).getDayTradeRestraint2()));
//            assertEquals(new Double(0.0), new Double(((TpCalcResultEquityParams)l_lisResult.get(0)).getDayTradeRestraint3()));
//            assertEquals(new Double(0.0), new Double(((TpCalcResultEquityParams)l_lisResult.get(0)).getDayTradeRestraint4()));
//        }
//        catch (Exception l_ex)
//        {
//            log.error(STR_METHOD_NAME, l_ex);
//            log.exiting(TEST_END + STR_METHOD_NAME);
//            fail();
//        }
//        log.exiting(TEST_END + STR_METHOD_NAME);
//    }
	
	public class WEB3TPAssetReflectorForTest extends WEB3TPAssetReflector
	{
	    public WEB3TPAssetReflectorForTest()
	    {

	    }

		public void calcReflectDay(Date l_datDeliveryDate)
		{
			
		}
	}
	
	public class WEB3TPRestraintForTest extends WEB3TPRestraint
	{
		
	}
    
    public class WEB3TPAssetValuationForTest extends WEB3TPAssetValuation
    {
        
    }
    
    public class WEB3TPSettlementForTest extends WEB3TPSettlement
    {

        public WEB3TPSettlementForTest(WEB3TPCashValuation l_cashValuation, WEB3TPSecurityValuation l_securityValuation, WEB3TPCalcCondition l_calcCondition)
        {
            super(l_cashValuation, l_securityValuation, l_calcCondition);
        }
        
        /**
         * �iget���v��S�����j<BR>
         * 
         * �p�����[�^.�w����̓��v�S�������擾���ԋp����B<BR>
         * <BR>
         * �V�[�P���X�}�u(��������)get���v��S�����v�Q��<BR>
         * @@param l_datSpecifiedDate - �i�w����j
         * @@return double
         * @@roseuid 40F73A1F0399
         */
        public double getDayTradeRestraint(Date l_datSpecifiedDate)
        {
            return 9;
        }
        
        public double getDayTradeRestraintIncUnexecSellOrder(Date l_datSpecifiedDate, boolean l_blnIsSettlement)
        {
            if (l_blnIsSettlement)
            {
                return 0;
            }
            else
            {
                return 1;
            }
        }
        public double getDayTradeRestraintIncUnexecSellOrder(Date l_datSpecifiedDate)
        {
            return 9.0;
        }
    }
    
    public class WEB3TPContractInfoForTest extends WEB3TPContractInfo
    {
        public double getSummaryTodayRepayContractAmount()
        {
            return 100;
        }
        
        public WEB3TPSummaryOpenContract getSummaryOpenContract(Date l_datDate) 
        {
            WEB3TPSummaryOpenContract l_openContract = new WEB3TPSummaryOpenContract();
            
            //���������ό��ʑ��
            l_openContract.setContractAmount(0);
            //���������ϕK�v�ۏ؋�
            l_openContract.setMarginDeposit(0);
            //���������ό����K�v�ۏ؋�
            l_openContract.setCashMarginDeposit(0);
            //���������ʑ��
            l_openContract.setUnExecContractAmount(0);
            //�������K�v�ۏ؋�
            l_openContract.setUnExecMarginDeposit(0);
            //�����������K�v�ۏ؋�
            l_openContract.setUnExecCashMarginDeposit(0);
            //�]����
            l_openContract.setAssetLoss(0);
            //�]���v
            l_openContract.setAssetProfit(0);
            //���萔��
            l_openContract.setSetupFee(10);
            //�����E�t������
            l_openContract.setInterestLoss(20);
            //�����E�t�����v
            l_openContract.setInterestProfit(30);
            //���̑����ʏ��o��
            l_openContract.setOtherCost(40);
            
            return l_openContract;
        }
        
        public WEB3TPSummaryUndeliveredContract getSummaryUndeliveredContract(Date l_datDate) 
        {
            WEB3TPSummaryUndeliveredContract l_undeliveredContract = new WEB3TPSummaryUndeliveredContract();
            
            //����n���ʑ��
            l_undeliveredContract.
                setContractAmount(0);
            //����n���ʌ��ϑ�---------------------
            l_undeliveredContract.setContractLoss(111.1);
            //����n���ʌ��ωv
            l_undeliveredContract.setContractProfit(0);
            //����n���ʕK�v�ۏ؋�
            l_undeliveredContract.setMarginDeposit(0);
            //����n���ʌ����K�v�ۏ؋�
            l_undeliveredContract.
                setCashMarginDeposit(0);
            //���ʌ��ϑ�<����>
            l_undeliveredContract.setTodayRepayContractLoss(0);
            //���ʌ��ωv<����>
            l_undeliveredContract.setTodayRepayContractProfit(0);
            //���ό��ʑO�����i�]��<����>
            l_undeliveredContract.setTodayRepayContractPrevAsset(0);
            //���ʌ��ϑ�<�w���>
            l_undeliveredContract.setDesignateDateContractLoss(0);
            //���ʌ��ωv<�w���>
            l_undeliveredContract.setDesignateDateContractProfit(0);
            //���ʏ��o��
//            l_undeliveredContract.setContractTotalCost(20);
            
            //���萔��
            l_undeliveredContract.setSetupFee(11);
            //�����E�t������
            l_undeliveredContract.setContractInterestLoss(21);
            //�����E�t�����v
            l_undeliveredContract.setContractInterestProfit(31);
            //���̑����ʏ��o��
            l_undeliveredContract.setContractOtherCost(41);
            
            return l_undeliveredContract;
        }
        
        public WEB3TPSummaryDayTradeSwapContract getSummaryDayTradeSwapContract(Date l_datDate) 
        {
            WEB3TPSummaryDayTradeSwapContract l_dayTradeSwapContract = new WEB3TPSummaryDayTradeSwapContract();
            
            //���v��ԍρE�������n���ʑ��
            l_dayTradeSwapContract.setContractAmount(0);
            //���v��ԍρE�������n���ʕK�v�ۏ؋�
            l_dayTradeSwapContract.setMarginDeposit(0);
            //���v��ԍρE�������n���ʌ����K�v�ۏ؋�
            l_dayTradeSwapContract.setCashMarginDeposit(0);
            
            //���v��ԍρE�������n���ʂ̌������n���ʌ��ϑ�---------------------------------------------
//            l_dayTradeSwapContract.setSwapContractSettleLoss(222.2);
            
            //���萔��
            l_dayTradeSwapContract.setSetupFee(12);
            //�����E�t������
            l_dayTradeSwapContract.setContractInterestLoss(22);
            //�����E�t�����v
            l_dayTradeSwapContract.setContractInterestProfit(32);
            //���̑����ʏ��o��
            l_dayTradeSwapContract.setContractOtherCost(42);
            
            return l_dayTradeSwapContract;
        }
    }
    
    public class WEB3TPContractInfoForTest1 extends WEB3TPContractInfo
    {
        public double getSummaryTodayRepayContractAmount()
        {
            return 100;
        }
        
        public WEB3TPSummaryOpenContract getSummaryOpenContract(Date l_datDate) 
        {
            WEB3TPSummaryOpenContract l_openContract = new WEB3TPSummaryOpenContract();
            
            //���������ό��ʑ��
            l_openContract.setContractAmount(0);
            //���������ϕK�v�ۏ؋�
            l_openContract.setMarginDeposit(0);
            //���������ό����K�v�ۏ؋�
            l_openContract.setCashMarginDeposit(0);
            //���������ʑ��
            l_openContract.setUnExecContractAmount(0);
            //�������K�v�ۏ؋�
            l_openContract.setUnExecMarginDeposit(0);
            //�����������K�v�ۏ؋�
            l_openContract.setUnExecCashMarginDeposit(0);
            //�]����
            l_openContract.setAssetLoss(1300.11);
            //�]���v
            l_openContract.setAssetProfit(111.11);
            //���萔��
            l_openContract.setSetupFee(253.33);
            //�����E�t������
            l_openContract.setInterestLoss(1300.11);
            //�����E�t�����v
            l_openContract.setInterestProfit(111.11);
            //���̑����ʏ��o��
            l_openContract.setOtherCost(10.233);
            
            return l_openContract;
        }
        
    }
    
    public class WEB3TPSecurityValuationForTest extends WEB3TPSecurityValuation
    {
        public double calcSubstituteValuationPrice(Date l_datDate)
        {
            return 0;
        }
        
        public double calcUnExecSubstituteValuationPrice(Date l_datDate)
        {
            return 0;
        }
        
        public double calcValuationPrice(Date l_datDate)
        {
            return 10;
        }
        
        public double calcAssetValuationPrice(
            Date l_datDate,
            ProductTypeEnum l_productType,
            String l_strMiniStockDivDef)
        {
            if ((WEB3DateUtility.compareToDay(WEB3DateUtility.getDate("20071017", "yyyyMMdd"), l_datDate) == 0))
            {
                return 1.0;
            }
            else if ((WEB3DateUtility.compareToDay(WEB3DateUtility.getDate("20071024", "yyyyMMdd"), l_datDate) == 0))
            {
                return 5.0;
            }
            return 0;
        }
        
        public double calcPrevPriceSubstituteValuation()
        {
            return 200;
        }
    }
    
    public class WEB3TPCashValuationForTest extends WEB3TPCashValuation
    {
        public double calcCashOut(Date l_datDate)
        {
            return 0;
        }
    }
    
    private class WEB3TPTradingPowerUpdForTest1 extends WEB3TPTradingPowerUpd
    {
        public List calcTradingpowerUpdResultEquity()
        {
            TpCalcResultEquityDetailParams l_calcResultEquityDetailParams =
                TestDBUtility.getTpCalcResultEquityDetailRow();
            
            TpCalcResultEquityParams l_calcResultEquityParams = TestDBUtility.getTpCalcResultEquityRow();
            
            List l_list = new ArrayList();
            l_list.add(l_calcResultEquityParams);
            l_list.add(l_calcResultEquityDetailParams);
            
            return l_list;
        }
        public List calcTradingpowerUpdResultMargin(String l_strMarkToMarketDiv)
        {
            TpCalcResultMarginDetailParams l_calcResultMarginDetailParams =
                TestDBUtility.getTpCalcResultMarginDetailRow();
            
            TpCalcResultMarginParams l_calcResultMarginParams = TestDBUtility.getTpCalcResultMarginRow();
            
            List l_list = new ArrayList();
            l_list.add(l_calcResultMarginParams);
            l_list.add(l_calcResultMarginDetailParams);
            return l_list;
        }
    }
    
    //�yT0801211TP�z�y�]�́z�����،��@@�O���I�l�]�����ڒǉ��@@�]�͂̑Ή�
    //calcTradingpowerUpdResultEquity
    public void testCalcTradingpowerUpdResultEquity1()
    {
        final String STR_METHOD_NAME = "testCalcTradingpowerUpdResultEquity1()";
        log.entering(TEST_START + STR_METHOD_NAME);
        
        try
        {
            TestBaseForMock.MOCK_MANAGER.setIsMockUsed(true);
            Date l_datBizDate = WEB3DateUtility.getDate("20071017", "yyyyMMdd");
            
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                "com.fitechlabs.xtrade.plugin.tc.gentrade.stdimpls.TradingSystemImpl",
                "getSystemTimestamp",
                new Class[] {},
                new Timestamp(l_datBizDate.getTime()));

            WEB3TPCalcCondition l_condition = new WEB3TPCalcCondition();
            
            l_condition.calcBizDate();
            
            WEB3TPAccountInfo l_accountInfo = new WEB3TPAccountInfo();
            l_accountInfo.setAccountId(333812512203L);
            
            WEB3TPCashValuation l_cashValuation =
                WEB3TPCashValuation.create(l_accountInfo, l_condition, null);
            
            WEB3TPSecurityValuation securityValuation = new WEB3TPSecurityValuationForTest();
            
            WEB3TPSettlement settlement = new WEB3TPSettlementForTest(l_cashValuation, securityValuation,l_condition);
            
            WEB3TPTradingPowerUpd l_upd = new WEB3TPTradingPowerUpd();
            l_upd.calcCondition = l_condition;
            l_upd.accountInfo = l_accountInfo;
            l_upd.cashValuation = l_cashValuation;
            l_upd.settlement = settlement;
            l_upd.securityValuation = securityValuation;
            List l_lisResult =
                l_upd.calcTradingpowerUpdResultEquity();
            TpCalcResultEquityDetailParams l_detailParams = (TpCalcResultEquityDetailParams)l_lisResult.get(1);
            
            assertEquals(l_detailParams.getForeignEquityAssetDelivered() + "", "1.0");
            assertEquals(l_detailParams.getForeignEquityAssetExecuted() + "", "5.0");
            
            TpCalcResultEquityParams l_equityParams = (TpCalcResultEquityParams)l_lisResult.get(0);
            assertEquals(l_equityParams.getDayTradeRestraint5() + "", "9.0");
        }
        catch(Exception l_ex)
        {
            log.error(STR_METHOD_NAME,l_ex);
            fail();
        }
        
        log.exiting(TEST_END + STR_METHOD_NAME);
    }
    
    public void testCalcTradingpowerUpdResultMargin1()
    {
        final String STR_METHOD_NAME = "testCalcTradingpowerUpdResultMargin1()";
        log.entering(TEST_START + STR_METHOD_NAME);
        
        try
        {
            TestBaseForMock.MOCK_MANAGER.setIsMockUsed(true);
            Date l_datBizDate = WEB3DateUtility.getDate("20071017", "yyyyMMdd");
            
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                "com.fitechlabs.xtrade.plugin.tc.gentrade.stdimpls.TradingSystemImpl",
                "getSystemTimestamp",
                new Class[] {},
                new Timestamp(l_datBizDate.getTime()));

            WEB3TPCalcCondition l_condition = new WEB3TPCalcCondition();
            
            l_condition.calcBizDate();
            
            WEB3TPAccountInfo l_accountInfo = new WEB3TPAccountInfo();
            l_accountInfo.setAccountId(333812512203L);
            
            WEB3TPCashValuation l_cashValuation =
                WEB3TPCashValuation.create(l_accountInfo, l_condition, null);
            
            WEB3TPSecurityValuation securityValuation = new WEB3TPSecurityValuationForTest();
            
            WEB3TPSettlement settlement = new WEB3TPSettlementForTest(l_cashValuation, securityValuation,l_condition);
            
            WEB3TPContractInfoForTest l_WEB3TPContractInfo = new WEB3TPContractInfoForTest();
            
            WEB3TPTradingPowerUpd l_upd = new WEB3TPTradingPowerUpd();
            l_upd.calcCondition = l_condition;
            l_upd.accountInfo = l_accountInfo;
            l_upd.cashValuation = l_cashValuation;
            l_upd.settlement = settlement;
            l_upd.securityValuation = securityValuation;
            l_upd.contractInfo =l_WEB3TPContractInfo;
            List l_lisResult =
                l_upd.calcTradingpowerUpdResultMargin("123");
            TpCalcResultMarginDetailParams l_detailParams = (TpCalcResultMarginDetailParams)l_lisResult.get(1);
            
            assertEquals(l_detailParams.getForeignEquityAssetDelivered() + "", "1.0");
            assertEquals(l_detailParams.getForeignEquityAssetExecuted() + "", "5.0");
            
            TpCalcResultMarginParams l_params = (TpCalcResultMarginParams)l_lisResult.get(0);
            
            assertEquals(l_params.getContractTotalCost() + "", "219.0");//���ʏ��o��( T0)
            assertEquals(l_params.getContractTotalCost1() + "", "219.0");//���ʏ��o��( T1)
            assertEquals(l_params.getContractTotalCost2() + "", "219.0");//���ʏ��o��( T2)
            assertEquals(l_params.getContractTotalCost3() + "", "219.0");//���ʏ��o��( T3)
            assertEquals(l_params.getContractTotalCost4() + "", "219.0");//���ʏ��o��( T4)
            assertEquals(l_params.getContractTotalCost5() + "", "219.0");//���ʏ��o��( T5)
            
            assertEquals(l_params.getUndeliContractLoss0() + "", "111.1");//����n���ʌ��ϑ�( T0)
            assertEquals(l_params.getUndeliContractLoss1() + "", "111.1");//����n���ʌ��ϑ�( T1)
            assertEquals(l_params.getUndeliContractLoss2() + "", "111.1");//����n���ʌ��ϑ�( T2)
            assertEquals(l_params.getUndeliContractLoss3() + "", "111.1");//����n���ʌ��ϑ�( T3)
            
            assertEquals(l_detailParams.getSetupFee() + "", "33.0");//���萔��( T0)
            assertEquals(l_detailParams.getSetupFee1() + "", "33.0");//���萔��( T1)
            assertEquals(l_detailParams.getSetupFee2() + "", "33.0");//���萔��( T2)
            assertEquals(l_detailParams.getSetupFee3() + "", "33.0");//���萔��( T3)
            assertEquals(l_detailParams.getSetupFee4() + "", "33.0");//���萔��( T4)
            assertEquals(l_detailParams.getSetupFee5() + "", "33.0");//���萔��( T5)
            
            assertEquals(l_detailParams.getContractInterestLoss() + "", "63.0");//�����E�t������( T0)
            assertEquals(l_detailParams.getContractInterestLoss1() + "", "63.0");//�����E�t������( T1)
            assertEquals(l_detailParams.getContractInterestLoss2() + "", "63.0");//�����E�t������( T2)
            assertEquals(l_detailParams.getContractInterestLoss3() + "", "63.0");//�����E�t������( T3)
            assertEquals(l_detailParams.getContractInterestLoss4() + "", "63.0");//�����E�t������( T4)
            assertEquals(l_detailParams.getContractInterestLoss5() + "", "63.0");//�����E�t������( T5)
            
            assertEquals(l_detailParams.getContractInterestProfit() + "", "93.0");//�����E�t�����v( T0)
            assertEquals(l_detailParams.getContractInterestProfit1() + "", "93.0");//�����E�t�����v( T1)
            assertEquals(l_detailParams.getContractInterestProfit2() + "", "93.0");//�����E�t�����v( T2)
            assertEquals(l_detailParams.getContractInterestProfit3() + "", "93.0");//�����E�t�����v( T3)
            assertEquals(l_detailParams.getContractInterestProfit4() + "", "93.0");//�����E�t�����v( T4)
            assertEquals(l_detailParams.getContractInterestProfit5() + "", "93.0");//�����E�t�����v( T5)
            
            assertEquals(l_detailParams.getContractOtherCost() + "", "123.0");//���̑����ʏ��o��( T0)
            assertEquals(l_detailParams.getContractOtherCost1() + "", "123.0");//���̑����ʏ��o��( T1)
            assertEquals(l_detailParams.getContractOtherCost2() + "", "123.0");//���̑����ʏ��o��( T2)
            assertEquals(l_detailParams.getContractOtherCost3() + "", "123.0");//���̑����ʏ��o��( T3)
            assertEquals(l_detailParams.getContractOtherCost4() + "", "123.0");//���̑����ʏ��o��( T4)
            assertEquals(l_detailParams.getContractOtherCost5() + "", "123.0");//���̑����ʏ��o��( T5)
            
        }
        catch(Exception l_ex)
        {
            log.error(STR_METHOD_NAME,l_ex);
            fail();
        }
        
        log.exiting(TEST_END + STR_METHOD_NAME);
    }
    
    public void testCalcTradingpowerUpdResultMargin2()
    {
        final String STR_METHOD_NAME = "testCalcTradingpowerUpdResultMargin2()";
        log.entering(TEST_START + STR_METHOD_NAME);
        
        try
        {
            TestBaseForMock.MOCK_MANAGER.setIsMockUsed(true);
            Date l_datBizDate = WEB3DateUtility.getDate("20071017", "yyyyMMdd");
            
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                "com.fitechlabs.xtrade.plugin.tc.gentrade.stdimpls.TradingSystemImpl",
                "getSystemTimestamp",
                new Class[] {},
                new Timestamp(l_datBizDate.getTime()));

            WEB3TPCalcCondition l_condition = new WEB3TPCalcCondition();
            
            l_condition.calcBizDate();
            
            WEB3TPAccountInfo l_accountInfo = new WEB3TPAccountInfo();
            l_accountInfo.setAccountId(333812512203L);
            
            WEB3TPCashValuation l_cashValuation =
                WEB3TPCashValuation.create(l_accountInfo, l_condition, null);
            
            WEB3TPSecurityValuation securityValuation = new WEB3TPSecurityValuationForTest();
            
            WEB3TPSettlement settlement = new WEB3TPSettlementForTest(l_cashValuation, securityValuation,l_condition);
            
            WEB3TPContractInfoForTest l_WEB3TPContractInfo = new WEB3TPContractInfoForTest();
            
            WEB3TPTradingPowerUpd l_upd = new WEB3TPTradingPowerUpd();
            l_upd.calcCondition = l_condition;
            l_upd.accountInfo = l_accountInfo;
            l_upd.cashValuation = l_cashValuation;
            l_upd.settlement = settlement;
            l_upd.securityValuation = securityValuation;
            l_upd.contractInfo =l_WEB3TPContractInfo;
            List l_lisResult =
                l_upd.calcTradingpowerUpdResultMargin("123");
            TpCalcResultMarginParams l_MarginParams = (TpCalcResultMarginParams)l_lisResult.get(0);
            
            assertEquals(l_MarginParams.getUndeliContractLoss0() + "", "111.1");
            assertEquals(l_MarginParams.getUndeliContractLoss1() + "", "111.1");
            assertEquals(l_MarginParams.getUndeliContractLoss2() + "", "111.1");
            assertEquals(l_MarginParams.getUndeliContractLoss3() + "", "111.1");
        }
        catch(Exception l_ex)
        {
            log.error(STR_METHOD_NAME,l_ex);
            fail();
        }
        
        log.exiting(TEST_END + STR_METHOD_NAME);
    }
    public void testCalcTradingpowerUpdResultMargin3()
    {
        final String STR_METHOD_NAME = "testCalcTradingpowerUpdResultMargin3()";
        log.entering(TEST_START + STR_METHOD_NAME);
        
        try
        {
            TestBaseForMock.MOCK_MANAGER.setIsMockUsed(true);
            Date l_datBizDate = WEB3DateUtility.getDate("20071017", "yyyyMMdd");
            
            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                "com.fitechlabs.xtrade.plugin.tc.gentrade.stdimpls.TradingSystemImpl",
                "getSystemTimestamp",
                new Class[] {},
                new Timestamp(l_datBizDate.getTime()));

            WEB3TPCalcCondition l_condition = new WEB3TPCalcCondition();
            
            l_condition.calcBizDate();
            
            WEB3TPAccountInfo l_accountInfo = new WEB3TPAccountInfo();
            l_accountInfo.setAccountId(333812512203L);
            
            WEB3TPCashValuation l_cashValuation =
                WEB3TPCashValuation.create(l_accountInfo, l_condition, null);
            
            WEB3TPSecurityValuation securityValuation = new WEB3TPSecurityValuationForTest();
            
            WEB3TPSettlement settlement = new WEB3TPSettlementForTest(l_cashValuation, securityValuation,l_condition);
            
            WEB3TPContractInfoForTest1 l_WEB3TPContractInfo = new WEB3TPContractInfoForTest1();
            
            WEB3TPTradingPowerUpd l_upd = new WEB3TPTradingPowerUpd();
            l_upd.calcCondition = l_condition;
            l_upd.accountInfo = l_accountInfo;
            l_upd.cashValuation = l_cashValuation;
            l_upd.settlement = settlement;
            l_upd.securityValuation = securityValuation;
            l_upd.contractInfo =l_WEB3TPContractInfo;
            List l_lisResult =
                l_upd.calcTradingpowerUpdResultMargin("123");
            TpCalcResultMarginDetailParams l_MarginDetailParams = (TpCalcResultMarginDetailParams)l_lisResult.get(1);
            
            assertEquals(l_MarginDetailParams.getSetupFee1() + "", "253.33");
            assertEquals(l_MarginDetailParams.getSetupFee2() + "", "253.33");
            assertEquals(l_MarginDetailParams.getSetupFee3() + "", "253.33");
            assertEquals(l_MarginDetailParams.getSetupFee4() + "", "253.33");
            assertEquals(l_MarginDetailParams.getSetupFee5() + "", "253.33");
            
            assertEquals(l_MarginDetailParams.getContractInterestLoss1() + "", "1300.11");
            assertEquals(l_MarginDetailParams.getContractInterestLoss2() + "", "1300.11");
            assertEquals(l_MarginDetailParams.getContractInterestLoss3() + "", "1300.11");
            assertEquals(l_MarginDetailParams.getContractInterestLoss4() + "", "1300.11");
            assertEquals(l_MarginDetailParams.getContractInterestLoss5() + "", "1300.11");
            
            assertEquals(l_MarginDetailParams.getContractInterestProfit1() + "", "111.11");
            assertEquals(l_MarginDetailParams.getContractInterestProfit2() + "", "111.11");
            assertEquals(l_MarginDetailParams.getContractInterestProfit3() + "", "111.11");
            assertEquals(l_MarginDetailParams.getContractInterestProfit4() + "", "111.11");
            assertEquals(l_MarginDetailParams.getContractInterestProfit5() + "", "111.11");
            
            assertEquals(l_MarginDetailParams.getContractOtherCost1() + "", "10.233");
            assertEquals(l_MarginDetailParams.getContractOtherCost2() + "", "10.233");
            assertEquals(l_MarginDetailParams.getContractOtherCost3() + "", "10.233");
            assertEquals(l_MarginDetailParams.getContractOtherCost4() + "", "10.233");
            assertEquals(l_MarginDetailParams.getContractOtherCost5() + "", "10.233");
        }
        catch(Exception l_ex)
        {
            log.error(STR_METHOD_NAME,l_ex);
            fail();
        }
        
        log.exiting(TEST_END + STR_METHOD_NAME);
    }
}
@
