head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.04.07.00.49.56;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	5444d9d078c63f4;
filename	WEB3TPTradingPowerCalcMarginTest.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : 資産余力情報<信用顧客>テスト(WEB3TPTradingPowerCalcMarginTest.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2007/01/24 李木子 (中訊) 新規作成
Revesion History : 2008/10/23 陸文靜 (中訊) モデルNo.328 計算式書No.019,020
*/
package webbroker3.tradingpower;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;


import test.util.TestDBUtility;
import com.fitechlabs.xtrade.plugin.tc.gentrade.OrderTypeEnum;
import webbroker3.common.WEB3BaseRuntimeException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.gentrade.data.TradingpowerCalcConditionParams;
import webbroker3.gentrade.data.TradingpowerCalcConditionRow;
import webbroker3.mock.TestBaseForMock;
import webbroker3.tradingpower.data.TpCalcResultMarginDetailParams;
import webbroker3.tradingpower.data.TpCalcResultMarginParams;
import webbroker3.tradingpower.data.TpCalcResultMarginRow;
import webbroker3.util.WEB3LogUtility;
import webbroker3.util.WEB3StringTypeUtility;

/**
 * (資産余力情報<信用顧客>テスト)<BR>
 * <BR>
 *
 * @@author 李木子 (fitechlabs)
 * @@version 1.0
 */
public class WEB3TPTradingPowerCalcMarginTest extends TestBaseForMock
{
    /**
     * ログ出力ユーティリティ。<BR>
     */
    private static WEB3LogUtility log =
        WEB3LogUtility.getInstance(WEB3TPTradingPowerCalcMarginTest.class);

	public WEB3TPTradingPowerCalcMarginTest(String arg0)
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

	public void test_calcAppliedMarginTradingPower_0001()
	{
        final String STR_METHOD_NAME = "test_calcAppliedMarginTradingPower_0001()";
		WEB3TPTradingPowerCalcMargin l_WEB3TPTradingPowerCalcMargin = new WEB3TPTradingPowerCalcMargin();
		
		
		WEB3TPCalcCondition l_WEB3TPCalcCondition = new WEB3TPCalcCondition();
		
		l_WEB3TPCalcCondition.setMarginBasePoint(6);
		l_WEB3TPCalcCondition.setMinMarginDeposit(3000000.0D);
		l_WEB3TPCalcCondition.setEquityBizDate(5);
		
		TpCalcResultMarginParams l_TpCalcResultMarginParams = new TpCalcResultMarginParams();
		
		l_TpCalcResultMarginParams.setAccountBalance5(5000000.0D);
		l_TpCalcResultMarginParams.setTodayExecutedAmount5(1000000.0D);
		l_TpCalcResultMarginParams.setTodayUnexecutedAmount5(1000000.0D);
		l_TpCalcResultMarginParams.setOtherRestraint5(1000000.0D);
		l_TpCalcResultMarginParams.setSubstituteSecurityAsset5(200000.0D);
		
		l_WEB3TPTradingPowerCalcMargin.setCalcResultMargin(l_TpCalcResultMarginParams);
		l_WEB3TPTradingPowerCalcMargin.setCalcCondition(l_WEB3TPCalcCondition);
		
		try
		{
			WEB3TPCalcResult l_WEB3TPCalcResult = l_WEB3TPTradingPowerCalcMargin.calcAppliedMarginTradingPower();
			
			assertEquals(5, l_WEB3TPCalcResult.appliedPoint);
		}
		catch (Exception l_ex)
		{
			log.error(STR_METHOD_NAME, l_ex);
			fail();
		}
	}
	
    public void testcalcActualReceiptTradingPower_0001()
    {
        List l_calcResult = new ArrayList();
        l_calcResult.add(new Object());
        // （余力計算条件）
        WEB3TPCalcCondition l_calcCondition = new WEB3TPCalcCondition();
        l_calcCondition.addInstBranCalcCondition("actualreceipt.margincallpower.check","1");
        WEB3TPTradingPowerCalcMargin tPTradingPowerCalcMargin = 
            new WEB3TPTradingPowerCalcMargin(l_calcResult,null,l_calcCondition);
        // 余力計算結果Params
        TpCalcResultMarginParams tpCalcResultMarginParams = new TpCalcResultMarginParams();
        tpCalcResultMarginParams.setContractAmount0(-100D);
        tpCalcResultMarginParams.setDayRepayContractAmount0(50D);

        /* 追証余力(n)を計算する = 1.1 - 1.2 begin */
        /* 1.1 */
        /* 1.11 */
        // account_balance_0コラムの値を設定
        tpCalcResultMarginParams.setAccountBalance0(0D); // "-"
        // today_executed_amount_0コラムの値を設定
        tpCalcResultMarginParams.setTodayExecutedAmount0(0D); // "-"
        // today_unexecuted_amount_0コラムの値を設定
        tpCalcResultMarginParams.setTodayUnexecutedAmount0(0D); // "-"
        // other_restraint_0コラムの値を設定
        tpCalcResultMarginParams.setOtherRestraint0(0D); // "-"
        // 1.11 "+" 1.12
        /* 1.12 */
        // today_unexecuted_amount_0コラムの値を設定
        tpCalcResultMarginParams.setSubstituteSecurityAsset0(11D); // TODO flag
        // 1.1 - 1.2
        /* 1.2 = 0 */
        tpCalcResultMarginParams.setContractAmount0(0D);
        tpCalcResultMarginParams.setDayRepayContractAmount0(0D);
        // 建玉諸経費を返却する
        tpCalcResultMarginParams.setContractTotalCost(0D);
        // 未決済建玉評損益を返却する
        tpCalcResultMarginParams.setContractAssetProfitLoss(0D);
        // 未受渡建玉決済損( T + 0 )を取得する
        tpCalcResultMarginParams.setUndeliContractLoss0(0D);
        
        // set余力計算結果Params
        tPTradingPowerCalcMargin.setCalcResultMargin(tpCalcResultMarginParams);
        
        /* 2.1 */
        // l_dblActualReceiptTradingPower = 1.11 - 2.12 - 2.13
        /* 2.12 */
        // 現金必要保証金( T + 0 )を取得する。
        tpCalcResultMarginParams.setCashMarginDeposit0(10D);
        /* 2.13 */
        // 未受渡建玉現金必要保証金( T + 0 )を取得する
        tpCalcResultMarginParams.setUndeliCashMarginDeposit0(10D);
        
        double l_dbActualReceiptTradingPower = tPTradingPowerCalcMargin.calcActualReceiptTradingPower(0);

        assertEquals(-20D,l_dbActualReceiptTradingPower,0);
    }
    
    public void testcalcActualReceiptTradingPower_0002()
    {
        List l_calcResult = new ArrayList();
        l_calcResult.add(new Object());
        // （余力計算条件）
        WEB3TPCalcCondition l_calcCondition = new WEB3TPCalcCondition();
        l_calcCondition.addInstBranCalcCondition("actualreceipt.margincallpower.check","2");
        WEB3TPTradingPowerCalcMargin tPTradingPowerCalcMargin = 
            new WEB3TPTradingPowerCalcMargin(l_calcResult,null,l_calcCondition);
        // 余力計算結果Params
        TpCalcResultMarginParams tpCalcResultMarginParams = new TpCalcResultMarginParams();
        tpCalcResultMarginParams.setContractAmount0(-100D);
        tpCalcResultMarginParams.setDayRepayContractAmount0(50D);

        /* 追証余力(n)を計算する = 1.1 - 1.2 begin */
        /* 1.1 */
        /* 1.11 */
        // account_balance_0コラムの値を設定
        tpCalcResultMarginParams.setAccountBalance0(0D); // "-"
        // today_executed_amount_0コラムの値を設定
        tpCalcResultMarginParams.setTodayExecutedAmount0(0D); // "-"
        // today_unexecuted_amount_0コラムの値を設定
        tpCalcResultMarginParams.setTodayUnexecutedAmount0(0D); // "-"
        // other_restraint_0コラムの値を設定
        tpCalcResultMarginParams.setOtherRestraint0(0D); // "-"
        // 1.11 "+" 1.12
        /* 1.12 */
        // today_unexecuted_amount_0コラムの値を設定
        tpCalcResultMarginParams.setSubstituteSecurityAsset0(-11D); // TODO flag
        // 1.1 - 1.2
        /* 1.2 = 0 */
        tpCalcResultMarginParams.setContractAmount0(0D);
        tpCalcResultMarginParams.setDayRepayContractAmount0(0D);
        // 建玉諸経費を返却する
        tpCalcResultMarginParams.setContractTotalCost(0D);
        // 未決済建玉評損益を返却する
        tpCalcResultMarginParams.setContractAssetProfitLoss(0D);
        // 未受渡建玉決済損( T + 0 )を取得する
        tpCalcResultMarginParams.setUndeliContractLoss0(0D);
        
        // set余力計算結果Params
        tPTradingPowerCalcMargin.setCalcResultMargin(tpCalcResultMarginParams);
        
        /* 2.1 */
        // l_dblActualReceiptTradingPower = 1.11 - 2.12 - 2.13
        /* 2.12 */
        // 現金必要保証金( T + 0 )を取得する。
        tpCalcResultMarginParams.setCashMarginDeposit0(10D);
        /* 2.13 */
        // 未受渡建玉現金必要保証金( T + 0 )を取得する
        tpCalcResultMarginParams.setUndeliCashMarginDeposit0(10D);
        
        double l_dbActualReceiptTradingPower = tPTradingPowerCalcMargin.calcActualReceiptTradingPower(0);
        
        assertEquals(-20D,l_dbActualReceiptTradingPower,0);
    }
    
    public void testcalcActualReceiptTradingPower_0003()
    {
        List l_calcResult = new ArrayList();
        l_calcResult.add(new Object());
        // （余力計算条件）
        WEB3TPCalcCondition l_calcCondition = new WEB3TPCalcCondition();
        l_calcCondition.addInstBranCalcCondition("actualreceipt.margincallpower.check","1");
        WEB3TPTradingPowerCalcMargin tPTradingPowerCalcMargin = 
            new WEB3TPTradingPowerCalcMargin(l_calcResult,l_calcCondition);
        // 余力計算結果Params
        TpCalcResultMarginParams tpCalcResultMarginParams = new TpCalcResultMarginParams();
        tpCalcResultMarginParams.setContractAmount0(-100D);
        tpCalcResultMarginParams.setDayRepayContractAmount0(50D);

        /* 追証余力(n)を計算する = 1.1 - 1.2 begin */
        /* 1.1 */
        /* 1.11 */
        // account_balance_0コラムの値を設定
        tpCalcResultMarginParams.setAccountBalance0(0D); // "-"
        // today_executed_amount_0コラムの値を設定
        tpCalcResultMarginParams.setTodayExecutedAmount0(0D); // "-"
        // today_unexecuted_amount_0コラムの値を設定
        tpCalcResultMarginParams.setTodayUnexecutedAmount0(0D); // "-"
        // other_restraint_0コラムの値を設定
        tpCalcResultMarginParams.setOtherRestraint0(0D); // "-"
        // 1.11 "+" 1.12
        /* 1.12 */
        // today_unexecuted_amount_0コラムの値を設定
        tpCalcResultMarginParams.setSubstituteSecurityAsset0(-11D);
        // 1.1 - 1.2
        /* 1.2 = 0 */
        tpCalcResultMarginParams.setContractAmount0(0D);
        tpCalcResultMarginParams.setDayRepayContractAmount0(0D);
        // 建玉諸経費を返却する
        tpCalcResultMarginParams.setContractTotalCost(0D);
        // 未決済建玉評損益を返却する
        tpCalcResultMarginParams.setContractAssetProfitLoss(0D);
        // 未受渡建玉決済損( T + 0 )を取得する
        tpCalcResultMarginParams.setUndeliContractLoss0(0D);
        
        // set余力計算結果Params
        tPTradingPowerCalcMargin.setCalcResultMargin(tpCalcResultMarginParams);
        
        double l_dbActualReceiptTradingPower = tPTradingPowerCalcMargin.calcActualReceiptTradingPower(0);
        
        assertEquals(0,l_dbActualReceiptTradingPower,0);
    }
    
    public void testcalcActualReceiptTradingPower_0004()
    {
        List l_calcResult = new ArrayList();
        l_calcResult.add(new Object());
        // （余力計算条件）
        WEB3TPCalcCondition l_calcCondition = new WEB3TPCalcCondition();
        l_calcCondition.addInstBranCalcCondition("actualreceipt.margincallpower.check","1");
        l_calcCondition.setSpecialDebitAmount(0D);
        WEB3TPTradingPowerCalcMargin tPTradingPowerCalcMargin = 
            new WEB3TPTradingPowerCalcMargin(l_calcResult,null,l_calcCondition);
        // 余力計算結果Params
        TpCalcResultMarginParams tpCalcResultMarginParams = new TpCalcResultMarginParams();
        tpCalcResultMarginParams.setContractAmount0(-100D);
        tpCalcResultMarginParams.setDayRepayContractAmount0(50D);

        /* 追証余力(n)を計算する = 1.1 - 1.2 begin */
        /* 1.1 */
        /* 1.11 */
        // account_balance_0コラムの値を設定
        tpCalcResultMarginParams.setAccountBalance0(0D); // "-"
        // today_executed_amount_0コラムの値を設定
        tpCalcResultMarginParams.setTodayExecutedAmount0(0D); // "-"
        // today_unexecuted_amount_0コラムの値を設定
        tpCalcResultMarginParams.setTodayUnexecutedAmount0(0D); // "-"
        // other_restraint_0コラムの値を設定
        tpCalcResultMarginParams.setOtherRestraint0(0D); // "-"
        // 1.11 "+" 1.12
        /* 1.12 */
        // today_unexecuted_amount_0コラムの値を設定
        tpCalcResultMarginParams.setSubstituteSecurityAsset0(11D); // TODO flag
        // 1.1 - 1.2
        /* 1.2 = 0 */
        tpCalcResultMarginParams.setContractAmount0(0D);
        tpCalcResultMarginParams.setDayRepayContractAmount0(0D);
        // 建玉諸経費を返却する
        tpCalcResultMarginParams.setContractTotalCost(0D);
        // 未決済建玉評損益を返却する
        tpCalcResultMarginParams.setContractAssetProfitLoss(0D);
        // 未受渡建玉決済損( T + 0 )を取得する
        tpCalcResultMarginParams.setUndeliContractLoss0(0D);
        
        // set余力計算結果Params
        tPTradingPowerCalcMargin.setCalcResultMargin(tpCalcResultMarginParams);
        
        /* 2.1 */
        // l_dblActualReceiptTradingPower = 1.11 - 2.12 - 2.13
        /* 2.12 */
        // 現金必要保証金( T + 0 )を取得する。
        tpCalcResultMarginParams.setCashMarginDeposit0(10D);
        /* 2.13 */
        // 未受渡建玉現金必要保証金( T + 0 )を取得する
        tpCalcResultMarginParams.setUndeliCashMarginDeposit0(10D);
        
        // 日計り拘束金( T + 0 )を取得する。
        tpCalcResultMarginParams.setDayTradeRestraint0(10D);
        
        double l_dbActualReceiptTradingPower = tPTradingPowerCalcMargin.calcActualReceiptTradingPowerIncDayTrade(0);
        
        assertEquals(-30D,l_dbActualReceiptTradingPower,0);
    }
    
    public void testcalcActualReceiptTradingPower_0005()
    {
        List l_calcResult = new ArrayList();
        l_calcResult.add(new Object());
        // （余力計算条件）
        WEB3TPCalcCondition l_calcCondition = new WEB3TPCalcCondition();
        l_calcCondition.addInstBranCalcCondition("actualreceipt.margincallpower.check","2");
        l_calcCondition.setSpecialDebitAmount(0D);
        WEB3TPTradingPowerCalcMargin tPTradingPowerCalcMargin = 
            new WEB3TPTradingPowerCalcMargin(l_calcResult,null,l_calcCondition);
        // 余力計算結果Params
        TpCalcResultMarginParams tpCalcResultMarginParams = new TpCalcResultMarginParams();
        tpCalcResultMarginParams.setContractAmount0(-100D);
        tpCalcResultMarginParams.setDayRepayContractAmount0(50D);

        /* 追証余力(n)を計算する = 1.1 - 1.2 begin */
        /* 1.1 */
        /* 1.11 */
        // account_balance_0コラムの値を設定
        tpCalcResultMarginParams.setAccountBalance0(0D); // "-"
        // today_executed_amount_0コラムの値を設定
        tpCalcResultMarginParams.setTodayExecutedAmount0(0D); // "-"
        // today_unexecuted_amount_0コラムの値を設定
        tpCalcResultMarginParams.setTodayUnexecutedAmount0(0D); // "-"
        // other_restraint_0コラムの値を設定
        tpCalcResultMarginParams.setOtherRestraint0(0D); // "-"
        // 1.11 "+" 1.12
        /* 1.12 */
        // today_unexecuted_amount_0コラムの値を設定
        tpCalcResultMarginParams.setSubstituteSecurityAsset0(-11D); // TODO flag
        // 1.1 - 1.2
        /* 1.2 = 0 */
        tpCalcResultMarginParams.setContractAmount0(0D);
        tpCalcResultMarginParams.setDayRepayContractAmount0(0D);
        // 建玉諸経費を返却する
        tpCalcResultMarginParams.setContractTotalCost(0D);
        // 未決済建玉評損益を返却する
        tpCalcResultMarginParams.setContractAssetProfitLoss(0D);
        // 未受渡建玉決済損( T + 0 )を取得する
        tpCalcResultMarginParams.setUndeliContractLoss0(0D);
        
        // set余力計算結果Params
        tPTradingPowerCalcMargin.setCalcResultMargin(tpCalcResultMarginParams);
        
        /* 2.1 */
        // l_dblActualReceiptTradingPower = 1.11 - 2.12 - 2.13
        /* 2.12 */
        // 現金必要保証金( T + 0 )を取得する。
        tpCalcResultMarginParams.setCashMarginDeposit0(10D);
        /* 2.13 */
        // 未受渡建玉現金必要保証金( T + 0 )を取得する
        tpCalcResultMarginParams.setUndeliCashMarginDeposit0(10D);
        
        // 日計り拘束金( T + 0 )を取得する。
        tpCalcResultMarginParams.setDayTradeRestraint0(10D);
        
        double l_dbActualReceiptTradingPower = tPTradingPowerCalcMargin.calcActualReceiptTradingPowerIncDayTrade(0);
        
        assertEquals(-30D,l_dbActualReceiptTradingPower,0);
    }
    
    public void testcalcActualReceiptTradingPower_0006()
    {
        List l_calcResult = new ArrayList();
        l_calcResult.add(new Object());
        // （余力計算条件）
        WEB3TPCalcCondition l_calcCondition = new WEB3TPCalcCondition();
        l_calcCondition.addInstBranCalcCondition("actualreceipt.margincallpower.check","1");
        l_calcCondition.setSpecialDebitAmount(0D);
        WEB3TPTradingPowerCalcMargin tPTradingPowerCalcMargin = 
            new WEB3TPTradingPowerCalcMargin(l_calcResult,null,l_calcCondition);
        // 余力計算結果Params
        TpCalcResultMarginParams tpCalcResultMarginParams = new TpCalcResultMarginParams();
        tpCalcResultMarginParams.setContractAmount0(-100D);
        tpCalcResultMarginParams.setDayRepayContractAmount0(50D);

        /* 追証余力(n)を計算する = 1.1 - 1.2 begin */
        /* 1.1 */
        /* 1.11 */
        // account_balance_0コラムの値を設定
        tpCalcResultMarginParams.setAccountBalance0(0D); // "-"
        // today_executed_amount_0コラムの値を設定
        tpCalcResultMarginParams.setTodayExecutedAmount0(0D); // "-"
        // today_unexecuted_amount_0コラムの値を設定
        tpCalcResultMarginParams.setTodayUnexecutedAmount0(0D); // "-"
        // other_restraint_0コラムの値を設定
        tpCalcResultMarginParams.setOtherRestraint0(0D); // "-"
        // 1.11 "+" 1.12
        /* 1.12 */
        // today_unexecuted_amount_0コラムの値を設定
        tpCalcResultMarginParams.setSubstituteSecurityAsset0(-11D); // TODO flag
        // 1.1 - 1.2
        /* 1.2 = 0 */
        tpCalcResultMarginParams.setContractAmount0(0D);
        tpCalcResultMarginParams.setDayRepayContractAmount0(0D);
        // 建玉諸経費を返却する
        tpCalcResultMarginParams.setContractTotalCost(0D);
        // 未決済建玉評損益を返却する
        tpCalcResultMarginParams.setContractAssetProfitLoss(0D);
        // 未受渡建玉決済損( T + 0 )を取得する
        tpCalcResultMarginParams.setUndeliContractLoss0(0D);
        
        // set余力計算結果Params
        tPTradingPowerCalcMargin.setCalcResultMargin(tpCalcResultMarginParams);
        
        /* 2.1 */
        // l_dblActualReceiptTradingPower = 1.11 - 2.12 - 2.13
        /* 2.12 */
        // 現金必要保証金( T + 0 )を取得する。
        tpCalcResultMarginParams.setCashMarginDeposit0(10D);
        /* 2.13 */
        // 未受渡建玉現金必要保証金( T + 0 )を取得する
        tpCalcResultMarginParams.setUndeliCashMarginDeposit0(10D);
        
        // 日計り拘束金( T + 0 )を取得する。
        tpCalcResultMarginParams.setDayTradeRestraint0(10D);
        
        double l_dbActualReceiptTradingPower = tPTradingPowerCalcMargin.calcActualReceiptTradingPowerIncDayTrade(0);
        
        assertEquals(0D,l_dbActualReceiptTradingPower,0);
    }
    
    /**
     * その他商品買付余力停止 == trueかつ
     * 注文種別 = 201:MF_BUY 
     */
    public void testCalcAppliedOtherTradingPowerC001()
    {
        final String STR_METHOD_NAME = "testCalcAppliedOtherTradingPowerC001()";
        log.entering(TEST_START + STR_METHOD_NAME);
        
        OrderTypeEnum l_orderTypeEnum = OrderTypeEnum.MF_BUY;
        WEB3TPTradingPowerCalcMargin l_tpCalcMargin = new WEB3TPTradingPowerCalcMargin();
        l_tpCalcMargin.calcCondition = new WEB3TPCalcCondition();
        
        try
        {
            TestDBUtility.deleteAll(TradingpowerCalcConditionRow.TYPE);
            TradingpowerCalcConditionParams l_tradingpowerCalcConditionParam =
                TestDBUtility.getTradingpowerCalcConditionRow();
            l_tradingpowerCalcConditionParam.setAccountId(101001010010L);
            l_tradingpowerCalcConditionParam.setPaymentStop("1");
            TestDBUtility.insertWithDel(l_tradingpowerCalcConditionParam);
            
            WEB3TPCalcCondition l_tPCalcCondition = new WEB3TPCalcCondition();
            l_tPCalcCondition.setOtherTradingStop(true);
            l_tPCalcCondition.setFundBasePoint(4);
            
            TestDBUtility.deleteAll(TpCalcResultMarginRow.TYPE);
            TpCalcResultMarginParams l_tpCalcResultMarginParams = TestDBUtility.getTpCalcResultMarginRow();
            TestDBUtility.insertWithDel(l_tpCalcResultMarginParams);
            l_tpCalcMargin.setCalcResultMargin(l_tpCalcResultMarginParams);
            l_tpCalcMargin.setCalcCondition(l_tPCalcCondition);
            
            WEB3TPCalcResult l_result =
                l_tpCalcMargin.calcAppliedOtherTradingPower(l_orderTypeEnum);
            
            assertEquals(0, l_result.appliedPoint);
            assertEquals(201, l_result.orderTypeEnum.intValue());
            assertEquals(-1, l_result.tradingPower, 0);
        }
        catch (Exception l_ex)
        {
            log.error(STR_METHOD_NAME, l_ex);
            log.exiting(TEST_END + STR_METHOD_NAME);
            fail();
        }
        
        log.exiting(TEST_END + STR_METHOD_NAME);
    }
    
    /**
     * その他商品買付余力停止 == trueかつ
     * 出金余力停止区分 == true
     * 注文種別がCFD振替注文（CFD口座から預り金)
     */
    public void testCalcAppliedOtherTradingPowerC002()
    {
        final String STR_METHOD_NAME = "testCalcAppliedOtherTradingPowerC002()";
        log.entering(TEST_START + STR_METHOD_NAME);
        
        OrderTypeEnum l_orderTypeEnum = OrderTypeEnum.CFD_FROM_DEPOSIT_AMOUNT;
        WEB3TPTradingPowerCalcMargin l_tpCalcMargin = new WEB3TPTradingPowerCalcMargin();
        l_tpCalcMargin.calcCondition = new WEB3TPCalcCondition();
        
        try
        {
            TestDBUtility.deleteAll(TradingpowerCalcConditionRow.TYPE);
            TradingpowerCalcConditionParams l_tradingpowerCalcConditionParam =
                TestDBUtility.getTradingpowerCalcConditionRow();
            l_tradingpowerCalcConditionParam.setAccountId(101001010010L);
            l_tradingpowerCalcConditionParam.setPaymentStop("1");
            TestDBUtility.insertWithDel(l_tradingpowerCalcConditionParam);
            
            WEB3TPCalcCondition l_tPCalcCondition = new WEB3TPCalcCondition();
            l_tPCalcCondition.setOtherTradingStop(true);
            l_tPCalcCondition.setPaymentStop(true);
            l_tPCalcCondition.setOtherBasePoint(3);
            
            TestDBUtility.deleteAll(TpCalcResultMarginRow.TYPE);
            TpCalcResultMarginParams l_tpCalcResultMarginParams = TestDBUtility.getTpCalcResultMarginRow();
            TestDBUtility.insertWithDel(l_tpCalcResultMarginParams);
            l_tpCalcMargin.setCalcResultMargin(l_tpCalcResultMarginParams);
            l_tpCalcMargin.setCalcCondition(l_tPCalcCondition);
            
            WEB3TPCalcResult l_result =
                l_tpCalcMargin.calcAppliedOtherTradingPower(l_orderTypeEnum);
            
            assertEquals(0, l_result.appliedPoint);
            assertEquals(1021, l_result.orderTypeEnum.intValue());
            assertEquals(-1, l_result.tradingPower, 0);
        }
        catch (Exception l_ex)
        {
            log.error(STR_METHOD_NAME, l_ex);
            log.exiting(TEST_END + STR_METHOD_NAME);
            fail();
        }
        
        log.exiting(TEST_END + STR_METHOD_NAME);
    }

    /**
     * 
     * 引数が0以上5以下でない時、0を返却する
     */
    public void test_getDayTradeRestraint_0001()
    {
        final String STR_METHOD_NAME = "test_getDayTradeRestraint_0001()";
        log.entering(TEST_START + STR_METHOD_NAME);
        
        TpCalcResultMarginParams l_calcResultMarginParams = new TpCalcResultMarginParams();
        l_calcResultMarginParams.setDayTradeRestraint5(938.0D);

        WEB3TPTradingPowerCalcMargin l_margin = new WEB3TPTradingPowerCalcMargin();
        l_margin.calcResultMargin = l_calcResultMarginParams;
        
        assertEquals("938.0", l_margin.getDayTradeRestraint(5) + "");
        
        log.exiting(TEST_END + STR_METHOD_NAME);
    }

    /*
     * 引数.基準日がT+0より小さい時
     * WEB3ErrorCatalog.SYSTEM_ERROR_80017
     */
    public void testCalcAppliedOtherTradingPower2_C0001()
    {
        final String STR_METHOD_NAME = "testCalcAppliedOtherTradingPower2_C0001()";
        log.entering(TEST_START + STR_METHOD_NAME);

        try
        {
            OrderTypeEnum l_orderTypeEnum = OrderTypeEnum.MF_BUY;
            int l_intBasePoint = -1;

            WEB3TPTradingPowerCalcMargin l_margin = new WEB3TPTradingPowerCalcMargin();

            l_margin.calcAppliedOtherTradingPower(l_orderTypeEnum, l_intBasePoint);

            fail();
        }
        catch (WEB3BaseRuntimeException l_ex)
        {
            log.debug(STR_METHOD_NAME);
            assertEquals(WEB3ErrorCatalog.SYSTEM_ERROR_80017, l_ex.getErrorInfo());
        }
        catch (Exception l_ex)
        {
            log.error(STR_METHOD_NAME);
            fail();
        }

        log.exiting(TEST_END + STR_METHOD_NAME);
    }
    /*
     * 引数.注文種別 == 201：投信買付、203：投信募集、204：投信乗換 の場合
     */
    public void testCalcAppliedOtherTradingPower2_C0002()
    {
        final String STR_METHOD_NAME = "testCalcAppliedOtherTradingPower2_C0002()";
        log.entering(TEST_START + STR_METHOD_NAME);

        try
        {
            OrderTypeEnum l_orderTypeEnum = OrderTypeEnum.MF_BUY;
            int l_intBasePoint = 0;

            WEB3TPCalcCondition l_calcCondition = new WEB3TPCalcCondition();
            Integer fundBasePoint = new Integer(10);
            Field l_field = WEB3TPCalcCondition.class.getDeclaredField("fundBasePoint");
            l_field.setAccessible(true);
            l_field.set(l_calcCondition, fundBasePoint);

            WEB3TPTradingPowerCalcMargin l_margin = new WEB3TPTradingPowerCalcMarginForTest();
            l_margin.setCalcCondition(l_calcCondition);

            WEB3TPCalcResult l_result = l_margin.calcAppliedOtherTradingPower(l_orderTypeEnum, l_intBasePoint);

            assertEquals(1, l_result.appliedPoint);
            assertEquals(10, l_margin.calcCondition.getFundBasePoint());
        }
        catch (Exception l_ex)
        {
            log.error(STR_METHOD_NAME);
            fail();
        }

        log.exiting(TEST_END + STR_METHOD_NAME);
    }
    /*
     * 引数.注文種別 == 1001：出金 の場合
     */
    public void testCalcAppliedOtherTradingPower2_C0003()
    {
        final String STR_METHOD_NAME = "testCalcAppliedOtherTradingPower2_C0003()";
        log.entering(TEST_START + STR_METHOD_NAME);

        try
        {
            OrderTypeEnum l_orderTypeEnum = OrderTypeEnum.CASH_OUT;
            int l_intBasePoint = 6;

            WEB3TPCalcCondition l_calcCondition = new WEB3TPCalcCondition();
            Integer paymentBasePoint = new Integer(10);
            Field l_field = WEB3TPCalcCondition.class.getDeclaredField("paymentBasePoint");
            l_field.setAccessible(true);
            l_field.set(l_calcCondition, paymentBasePoint);

            WEB3TPTradingPowerCalcMargin l_margin = new WEB3TPTradingPowerCalcMarginForTest();
            l_margin.setCalcCondition(l_calcCondition);

            WEB3TPCalcResult l_result = l_margin.calcAppliedOtherTradingPower(l_orderTypeEnum, l_intBasePoint);

            assertEquals(1, l_result.appliedPoint);
            assertEquals(10, l_margin.calcCondition.getPaymentBasePoint());
        }
        catch (Exception l_ex)
        {
            log.error(STR_METHOD_NAME);
            fail();
        }

        log.exiting(TEST_END + STR_METHOD_NAME);
    }
    /*
     * 引数.注文種別 == 605：OP新規買建
     */
    public void testCalcAppliedOtherTradingPower2_C0004()
    {
        final String STR_METHOD_NAME = "testCalcAppliedOtherTradingPower2_C0004()";
        log.entering(TEST_START + STR_METHOD_NAME);

        try
        {
            OrderTypeEnum l_orderTypeEnum = OrderTypeEnum.IDX_OPTIONS_BUY_TO_OPEN;
            int l_intBasePoint = 5;

            WEB3TPCalcCondition l_calcCondition = new WEB3TPCalcCondition();
            Integer optionBasePoint = new Integer(10);
            Field l_field = WEB3TPCalcCondition.class.getDeclaredField("optionBasePoint");
            l_field.setAccessible(true);
            l_field.set(l_calcCondition, optionBasePoint);

            WEB3TPTradingPowerCalcMargin l_margin = new WEB3TPTradingPowerCalcMarginForTest();
            l_margin.setCalcCondition(l_calcCondition);

            WEB3TPCalcResult l_result = l_margin.calcAppliedOtherTradingPower(l_orderTypeEnum, l_intBasePoint);

            assertEquals(1, l_result.appliedPoint);
            assertEquals(10, l_margin.calcCondition.getOptionBasePoint());
        }
        catch (Exception l_ex)
        {
            log.error(STR_METHOD_NAME);
            fail();
        }

        log.exiting(TEST_END + STR_METHOD_NAME);
    }
    /*
     * 引数.注文種別 == 1007：証拠金への振替, 1011:為替保証金への振替,
     * 1013:外国株式への振替, 1021:CFD振替注文（預り金からCFD口座）
     */
    public void testCalcAppliedOtherTradingPower2_C0005()
    {
        final String STR_METHOD_NAME = "testCalcAppliedOtherTradingPower2_C0005()";
        log.entering(TEST_START + STR_METHOD_NAME);

        try
        {
            OrderTypeEnum l_orderTypeEnum = OrderTypeEnum.CFD_FROM_DEPOSIT_AMOUNT;
            int l_intBasePoint = 3;

            WEB3TPCalcCondition l_calcCondition = new WEB3TPCalcCondition();
            Integer otherBasePoint = new Integer(10);
            Field l_field = WEB3TPCalcCondition.class.getDeclaredField("otherBasePoint");
            l_field.setAccessible(true);
            l_field.set(l_calcCondition, otherBasePoint);

            WEB3TPTradingPowerCalcMargin l_margin = new WEB3TPTradingPowerCalcMarginForTest();
            l_margin.setCalcCondition(l_calcCondition);

            WEB3TPCalcResult l_result = l_margin.calcAppliedOtherTradingPower(l_orderTypeEnum, l_intBasePoint);

            assertEquals(1, l_result.appliedPoint);
            assertEquals(10, l_margin.calcCondition.getOtherBasePoint());
        }
        catch (Exception l_ex)
        {
            log.error(STR_METHOD_NAME);
            fail();
        }

        log.exiting(TEST_END + STR_METHOD_NAME);
    }
    /*
     * 以外の場合
     */
    public void testCalcAppliedOtherTradingPower2_C0006()
    {
        final String STR_METHOD_NAME = "testCalcAppliedOtherTradingPower2_C0006()";
        log.entering(TEST_START + STR_METHOD_NAME);

        try
        {
            OrderTypeEnum l_orderTypeEnum = OrderTypeEnum.UNDEFINED;
            int l_intBasePoint = 3;

            WEB3TPCalcCondition l_calcCondition = new WEB3TPCalcCondition();
            Integer otherBasePoint = new Integer(10);
            Field l_field = WEB3TPCalcCondition.class.getDeclaredField("otherBasePoint");
            l_field.setAccessible(true);
            l_field.set(l_calcCondition, otherBasePoint);

            WEB3TPTradingPowerCalcMargin l_margin = new WEB3TPTradingPowerCalcMarginForTest();
            l_margin.setCalcCondition(l_calcCondition);

            WEB3TPCalcResult l_result = l_margin.calcAppliedOtherTradingPower(l_orderTypeEnum, l_intBasePoint);

            assertEquals(1, l_result.appliedPoint);
            assertEquals(10, l_margin.calcCondition.getOtherBasePoint());
        }
        catch (Exception l_ex)
        {
            log.error(STR_METHOD_NAME);
            fail();
        }

        log.exiting(TEST_END + STR_METHOD_NAME);
    }

    /**
     * WEB3TPTradingPowerCalcMarginForTest
     */
    private class WEB3TPTradingPowerCalcMarginForTest extends WEB3TPTradingPowerCalcMargin
    {
        public WEB3TPCalcResult calcAppliedOtherTradingPower(OrderTypeEnum l_orderTypeEnum)
        {
            WEB3TPCalcResult l_calcResult = new WEB3TPCalcResult();
            l_calcResult.appliedPoint = 1;

            return l_calcResult;
        }

        public double calcPaidMarginDeposit(int l_intSpecifiedPoint)
        {
            return 40;
        }
    }

    //  建玉諸経費（T+0）を取得する。
    public void testgetContractTotalCost_0001()
    {
        final String STR_METHOD_NAME = "testgetContractTotalCost_0001()";
        log.entering(TEST_START + STR_METHOD_NAME);

        try
        {
            int l_intSpecifiedPoint = 0;

            TestDBUtility.deleteAll(TpCalcResultMarginRow.TYPE);
            TpCalcResultMarginParams l_tpCalcResultMarginParams = TestDBUtility.getTpCalcResultMarginRow();
            l_tpCalcResultMarginParams.setContractTotalCost(10);
            TestDBUtility.insertWithDel(l_tpCalcResultMarginParams);

            WEB3TPTradingPowerCalcMargin l_margin = new WEB3TPTradingPowerCalcMargin();

            l_margin.setCalcResultMargin(l_tpCalcResultMarginParams);
            double l_dbgetContractTotalCost = l_margin.getContractTotalCost(l_intSpecifiedPoint);

            assertEquals("10",WEB3StringTypeUtility.formatNumber(l_dbgetContractTotalCost));
        }
        catch(Exception l_ex)
        {
            log.error(l_ex.getMessage(), l_ex);
            log.exiting(TEST_END + STR_METHOD_NAME);
            fail();  
        }   
    }

    //建玉諸経費（T+1）を取得する。
    public void testgetContractTotalCost_0002()
    {
        final String STR_METHOD_NAME = "testgetContractTotalCost_0002()";
        log.entering(TEST_START + STR_METHOD_NAME);

        try
        {
            int l_intSpecifiedPoint = 1;

            TestDBUtility.deleteAll(TpCalcResultMarginRow.TYPE);
            TpCalcResultMarginParams l_tpCalcResultMarginParams = TestDBUtility.getTpCalcResultMarginRow();
            l_tpCalcResultMarginParams.setContractTotalCost1(10);
            TestDBUtility.insertWithDel(l_tpCalcResultMarginParams);

            WEB3TPTradingPowerCalcMargin l_margin = new WEB3TPTradingPowerCalcMargin();

            l_margin.setCalcResultMargin(l_tpCalcResultMarginParams);
            double l_dbgetContractTotalCost = l_margin.getContractTotalCost(l_intSpecifiedPoint);

            assertEquals("10",WEB3StringTypeUtility.formatNumber(l_dbgetContractTotalCost));
        }
        catch(Exception l_ex)
        {
            log.error(l_ex.getMessage(), l_ex);
            log.exiting(TEST_END + STR_METHOD_NAME);
            fail();  
        }   
    }

    //建玉諸経費（T+2）を取得する。
    public void testgetContractTotalCost_0003()
    {
        final String STR_METHOD_NAME = "testgetContractTotalCost_0003()";
        log.entering(TEST_START + STR_METHOD_NAME);

        try
        {
            int l_intSpecifiedPoint = 2;

            TestDBUtility.deleteAll(TpCalcResultMarginRow.TYPE);
            TpCalcResultMarginParams l_tpCalcResultMarginParams = TestDBUtility.getTpCalcResultMarginRow();
            l_tpCalcResultMarginParams.setContractTotalCost2(10);
            TestDBUtility.insertWithDel(l_tpCalcResultMarginParams);

            WEB3TPTradingPowerCalcMargin l_margin = new WEB3TPTradingPowerCalcMargin();

            l_margin.setCalcResultMargin(l_tpCalcResultMarginParams);
            double l_dbgetContractTotalCost = l_margin.getContractTotalCost(l_intSpecifiedPoint);

            assertEquals("10",WEB3StringTypeUtility.formatNumber(l_dbgetContractTotalCost));
        }
        catch(Exception l_ex)
        {
            log.error(l_ex.getMessage(), l_ex);
            log.exiting(TEST_END + STR_METHOD_NAME);
            fail();  
        }   
    }

    //建玉諸経費（T+3）を取得する。
    public void testgetContractTotalCost_0004()
    {
        final String STR_METHOD_NAME = "testgetContractTotalCost_0004()";
        log.entering(TEST_START + STR_METHOD_NAME);

        try
        {
            int l_intSpecifiedPoint = 3;

            TestDBUtility.deleteAll(TpCalcResultMarginRow.TYPE);
            TpCalcResultMarginParams l_tpCalcResultMarginParams = TestDBUtility.getTpCalcResultMarginRow();
            l_tpCalcResultMarginParams.setContractTotalCost3(10);
            TestDBUtility.insertWithDel(l_tpCalcResultMarginParams);

            WEB3TPTradingPowerCalcMargin l_margin = new WEB3TPTradingPowerCalcMargin();

            l_margin.setCalcResultMargin(l_tpCalcResultMarginParams);
            double l_dbgetContractTotalCost = l_margin.getContractTotalCost(l_intSpecifiedPoint);

            assertEquals("10",WEB3StringTypeUtility.formatNumber(l_dbgetContractTotalCost));
        }
        catch(Exception l_ex)
        {
            log.error(l_ex.getMessage(), l_ex);
            log.exiting(TEST_END + STR_METHOD_NAME);
            fail();  
        }   
    }

    //建玉諸経費（T+4）を取得する。
    public void testgetContractTotalCost_0005()
    {
        final String STR_METHOD_NAME = "testgetContractTotalCost_0005()";
        log.entering(TEST_START + STR_METHOD_NAME);

        try
        {
            int l_intSpecifiedPoint = 4;

            TestDBUtility.deleteAll(TpCalcResultMarginRow.TYPE);
            TpCalcResultMarginParams l_tpCalcResultMarginParams = TestDBUtility.getTpCalcResultMarginRow();
            l_tpCalcResultMarginParams.setContractTotalCost4(10);
            TestDBUtility.insertWithDel(l_tpCalcResultMarginParams);

            WEB3TPTradingPowerCalcMargin l_margin = new WEB3TPTradingPowerCalcMargin();

            l_margin.setCalcResultMargin(l_tpCalcResultMarginParams);
            double l_dbgetContractTotalCost = l_margin.getContractTotalCost(l_intSpecifiedPoint);

            assertEquals("10",WEB3StringTypeUtility.formatNumber(l_dbgetContractTotalCost));
        }
        catch(Exception l_ex)
        {
            log.error(l_ex.getMessage(), l_ex);
            log.exiting(TEST_END + STR_METHOD_NAME);
            fail();  
        }   
    }

    //建玉諸経費（T+5）を取得する。
    public void testgetContractTotalCost_0006()
    {
        final String STR_METHOD_NAME = "testgetContractTotalCost_0006()";
        log.entering(TEST_START + STR_METHOD_NAME);

        try
        {
            int l_intSpecifiedPoint = 5;

            TestDBUtility.deleteAll(TpCalcResultMarginRow.TYPE);
            TpCalcResultMarginParams l_tpCalcResultMarginParams = TestDBUtility.getTpCalcResultMarginRow();
            l_tpCalcResultMarginParams.setContractTotalCost5(10);
            TestDBUtility.insertWithDel(l_tpCalcResultMarginParams);

            WEB3TPTradingPowerCalcMargin l_margin = new WEB3TPTradingPowerCalcMargin();

            l_margin.setCalcResultMargin(l_tpCalcResultMarginParams);
            double l_dbgetContractTotalCost = l_margin.getContractTotalCost(l_intSpecifiedPoint);

            assertEquals("10",WEB3StringTypeUtility.formatNumber(l_dbgetContractTotalCost));
        }
        catch(Exception l_ex)
        {
            log.error(l_ex.getMessage(), l_ex);
            log.exiting(TEST_END + STR_METHOD_NAME);
            fail();  
        }   
    }

    //nが0以上5以下でない時、0を返却する。
    public void testgetContractTotalCost_0007()
    {
        final String STR_METHOD_NAME = "testgetContractTotalCost_0007()";
        log.entering(TEST_START + STR_METHOD_NAME);

        try
        {
            int l_intSpecifiedPoint = -1;

            TestDBUtility.deleteAll(TpCalcResultMarginRow.TYPE);
            TpCalcResultMarginParams l_tpCalcResultMarginParams = TestDBUtility.getTpCalcResultMarginRow();
            TestDBUtility.insertWithDel(l_tpCalcResultMarginParams);

            WEB3TPTradingPowerCalcMargin l_margin = new WEB3TPTradingPowerCalcMargin();

            l_margin.setCalcResultMargin(l_tpCalcResultMarginParams);
            double l_dbgetContractTotalCost = l_margin.getContractTotalCost(l_intSpecifiedPoint);

            assertEquals("0",WEB3StringTypeUtility.formatNumber(l_dbgetContractTotalCost));
        }
        catch(Exception l_ex)
        {
            log.error(l_ex.getMessage(), l_ex);
            log.exiting(TEST_END + STR_METHOD_NAME);
            fail();  
        }   
    }

    //引数で指定された指定日(=n)の、「受入保証金」を返却する。
    public void testcalcReceiptMarginDeposit_0001()
    {
        final String STR_METHOD_NAME = "testcalcReceiptMarginDeposit_0001()";
        log.entering(TEST_START + STR_METHOD_NAME);

        try
        {
            int l_intSpecifiedPoint = 1;

            TestDBUtility.deleteAll(TpCalcResultMarginRow.TYPE);
            TpCalcResultMarginParams l_tpCalcResultMarginParams = TestDBUtility.getTpCalcResultMarginRow();
            l_tpCalcResultMarginParams.setContractTotalCost1(10);
            l_tpCalcResultMarginParams.setUndeliContractLoss1(10);
            l_tpCalcResultMarginParams.setContractAssetProfitLoss1(10);
            TestDBUtility.insertWithDel(l_tpCalcResultMarginParams);

            WEB3TPTradingPowerCalcMarginForTest l_margin = new WEB3TPTradingPowerCalcMarginForTest();

            l_margin.setCalcResultMargin(l_tpCalcResultMarginParams);

            double l_dblReceiptMarginDeposit = l_margin.calcReceiptMarginDeposit(l_intSpecifiedPoint);
            assertEquals("20",WEB3StringTypeUtility.formatNumber(l_dblReceiptMarginDeposit));  
        }
        catch(Exception l_ex)
        {
            log.error(l_ex.getMessage(), l_ex);
            log.exiting(TEST_END + STR_METHOD_NAME);
            fail();  
        }
    }

    //引数で指定された指定日(=n)の「信用拘束金」を計算する
    public void testcalcMarginRestraint_0001()
    {
        final String STR_METHOD_NAME = "testcalcMarginRestraint_0001()";
        log.entering(TEST_START + STR_METHOD_NAME);

        try
        {
            int l_intSpecifiedPoint = 1;

            TestDBUtility.deleteAll(TpCalcResultMarginRow.TYPE);
            TpCalcResultMarginParams l_tpCalcResultMarginParams = TestDBUtility.getTpCalcResultMarginRow();
            l_tpCalcResultMarginParams.setContractTotalCost1(1);
            l_tpCalcResultMarginParams.setUndeliContractLoss1(1);
            l_tpCalcResultMarginParams.setContractAssetProfitLoss1(1);
            TestDBUtility.insertWithDel(l_tpCalcResultMarginParams);

            WEB3TPTradingPowerCalcMarginForTest l_margin = new WEB3TPTradingPowerCalcMarginForTest();

            l_margin.setCalcResultMargin(l_tpCalcResultMarginParams);

            double l_dblReceiptMarginDeposit = l_margin.calcMarginRestraint(l_intSpecifiedPoint);
            assertEquals("2",WEB3StringTypeUtility.formatNumber(l_dblReceiptMarginDeposit));  
        }
        catch(Exception l_ex)
        {
            log.error(l_ex.getMessage(), l_ex);
            log.exiting(TEST_END + STR_METHOD_NAME);
            fail();  
        }   
    }
    
    /*
     * 建手数料( T + 0 )を取得する。
     */
    public void testGetSetupFee_C0001()
    {
        final String STR_METHOD_NAME = "testgetSetupFee_C0001()";
        log.entering(TEST_START + STR_METHOD_NAME);

        try
        {
            int l_intSpecifiedPoint = 0;

            TpCalcResultMarginDetailParams l_TpCalcResultMarginDetailParams = TestDBUtility.getTpCalcResultMarginDetailRow();
            l_TpCalcResultMarginDetailParams.setSetupFee(0.1);
            l_TpCalcResultMarginDetailParams.setSetupFee1(1.1);
            l_TpCalcResultMarginDetailParams.setSetupFee2(2.1);
            l_TpCalcResultMarginDetailParams.setSetupFee3(3.1);
            l_TpCalcResultMarginDetailParams.setSetupFee4(4.1);
            l_TpCalcResultMarginDetailParams.setSetupFee5(5.1);

            WEB3TPTradingPowerCalcMargin l_margin = new WEB3TPTradingPowerCalcMargin();
            l_margin.setCalcResultDetailMargin(l_TpCalcResultMarginDetailParams);

            double l_dblSetupFee = l_margin.getSetupFee(l_intSpecifiedPoint);
            assertEquals("0.1", String.valueOf(l_dblSetupFee));
        }
        catch(Exception l_ex)
        {
            log.error(l_ex.getMessage(), l_ex);
            fail();  
        }
        log.exiting(TEST_END + STR_METHOD_NAME);
    }
    /*
     * 建手数料( T + 1 )を取得する。
     */
    public void testGetSetupFee_C0002()
    {
        final String STR_METHOD_NAME = "testgetSetupFee_C0002()";
        log.entering(TEST_START + STR_METHOD_NAME);

        try
        {
            int l_intSpecifiedPoint = 1;

            TpCalcResultMarginDetailParams l_TpCalcResultMarginDetailParams = TestDBUtility.getTpCalcResultMarginDetailRow();
            l_TpCalcResultMarginDetailParams.setSetupFee(0.1);
            l_TpCalcResultMarginDetailParams.setSetupFee1(1.1);
            l_TpCalcResultMarginDetailParams.setSetupFee2(2.1);
            l_TpCalcResultMarginDetailParams.setSetupFee3(3.1);
            l_TpCalcResultMarginDetailParams.setSetupFee4(4.1);
            l_TpCalcResultMarginDetailParams.setSetupFee5(5.1);

            WEB3TPTradingPowerCalcMargin l_margin = new WEB3TPTradingPowerCalcMargin();
            l_margin.setCalcResultDetailMargin(l_TpCalcResultMarginDetailParams);

            double l_dblSetupFee = l_margin.getSetupFee(l_intSpecifiedPoint);
            assertEquals("1.1", String.valueOf(l_dblSetupFee));
        }
        catch(Exception l_ex)
        {
            log.error(l_ex.getMessage(), l_ex);
            fail();  
        }
        log.exiting(TEST_END + STR_METHOD_NAME);
    }
    /*
     * 建手数料( T + 2 )を取得する。
     */
    public void testGetSetupFee_C0003()
    {
        final String STR_METHOD_NAME = "testgetSetupFee_C0003()";
        log.entering(TEST_START + STR_METHOD_NAME);

        try
        {
            int l_intSpecifiedPoint = 2;

            TpCalcResultMarginDetailParams l_TpCalcResultMarginDetailParams = TestDBUtility.getTpCalcResultMarginDetailRow();
            l_TpCalcResultMarginDetailParams.setSetupFee(0.1);
            l_TpCalcResultMarginDetailParams.setSetupFee1(1.1);
            l_TpCalcResultMarginDetailParams.setSetupFee2(2.1);
            l_TpCalcResultMarginDetailParams.setSetupFee3(3.1);
            l_TpCalcResultMarginDetailParams.setSetupFee4(4.1);
            l_TpCalcResultMarginDetailParams.setSetupFee5(5.1);

            WEB3TPTradingPowerCalcMargin l_margin = new WEB3TPTradingPowerCalcMargin();
            l_margin.setCalcResultDetailMargin(l_TpCalcResultMarginDetailParams);

            double l_dblSetupFee = l_margin.getSetupFee(l_intSpecifiedPoint);
            assertEquals("2.1", String.valueOf(l_dblSetupFee));
        }
        catch(Exception l_ex)
        {
            log.error(l_ex.getMessage(), l_ex);
            fail();  
        }
        log.exiting(TEST_END + STR_METHOD_NAME);
    }
    /*
     * 建手数料( T + 3 )を取得する。
     */
    public void testGetSetupFee_C0004()
    {
        final String STR_METHOD_NAME = "testgetSetupFee_C0004()";
        log.entering(TEST_START + STR_METHOD_NAME);

        try
        {
            int l_intSpecifiedPoint = 3;

            TpCalcResultMarginDetailParams l_TpCalcResultMarginDetailParams = TestDBUtility.getTpCalcResultMarginDetailRow();
            l_TpCalcResultMarginDetailParams.setSetupFee(0.1);
            l_TpCalcResultMarginDetailParams.setSetupFee1(1.1);
            l_TpCalcResultMarginDetailParams.setSetupFee2(2.1);
            l_TpCalcResultMarginDetailParams.setSetupFee3(3.1);
            l_TpCalcResultMarginDetailParams.setSetupFee4(4.1);
            l_TpCalcResultMarginDetailParams.setSetupFee5(5.1);

            WEB3TPTradingPowerCalcMargin l_margin = new WEB3TPTradingPowerCalcMargin();
            l_margin.setCalcResultDetailMargin(l_TpCalcResultMarginDetailParams);

            double l_dblSetupFee = l_margin.getSetupFee(l_intSpecifiedPoint);
            assertEquals("3.1", String.valueOf(l_dblSetupFee));
        }
        catch(Exception l_ex)
        {
            log.error(l_ex.getMessage(), l_ex);
            fail();  
        }
        log.exiting(TEST_END + STR_METHOD_NAME);
    }
    /*
     * 建手数料( T + 4 )を取得する。
     */
    public void testGetSetupFee_C0005()
    {
        final String STR_METHOD_NAME = "testgetSetupFee_C0005()";
        log.entering(TEST_START + STR_METHOD_NAME);

        try
        {
            int l_intSpecifiedPoint = 4;

            TpCalcResultMarginDetailParams l_TpCalcResultMarginDetailParams = TestDBUtility.getTpCalcResultMarginDetailRow();
            l_TpCalcResultMarginDetailParams.setSetupFee(0.1);
            l_TpCalcResultMarginDetailParams.setSetupFee1(1.1);
            l_TpCalcResultMarginDetailParams.setSetupFee2(2.1);
            l_TpCalcResultMarginDetailParams.setSetupFee3(3.1);
            l_TpCalcResultMarginDetailParams.setSetupFee4(4.1);
            l_TpCalcResultMarginDetailParams.setSetupFee5(5.1);

            WEB3TPTradingPowerCalcMargin l_margin = new WEB3TPTradingPowerCalcMargin();
            l_margin.setCalcResultDetailMargin(l_TpCalcResultMarginDetailParams);

            double l_dblSetupFee = l_margin.getSetupFee(l_intSpecifiedPoint);
            assertEquals("4.1", String.valueOf(l_dblSetupFee));
        }
        catch(Exception l_ex)
        {
            log.error(l_ex.getMessage(), l_ex);
            fail();  
        }
        log.exiting(TEST_END + STR_METHOD_NAME);
    }
    /*
     * 建手数料( T + 5 )を取得する。
     */
    public void testGetSetupFee_C0006()
    {
        final String STR_METHOD_NAME = "testgetSetupFee_C0006()";
        log.entering(TEST_START + STR_METHOD_NAME);

        try
        {
            int l_intSpecifiedPoint = 5;

            TpCalcResultMarginDetailParams l_TpCalcResultMarginDetailParams = TestDBUtility.getTpCalcResultMarginDetailRow();
            l_TpCalcResultMarginDetailParams.setSetupFee(0.1);
            l_TpCalcResultMarginDetailParams.setSetupFee1(1.1);
            l_TpCalcResultMarginDetailParams.setSetupFee2(2.1);
            l_TpCalcResultMarginDetailParams.setSetupFee3(3.1);
            l_TpCalcResultMarginDetailParams.setSetupFee4(4.1);
            l_TpCalcResultMarginDetailParams.setSetupFee5(5.1);

            WEB3TPTradingPowerCalcMargin l_margin = new WEB3TPTradingPowerCalcMargin();
            l_margin.setCalcResultDetailMargin(l_TpCalcResultMarginDetailParams);

            double l_dblSetupFee = l_margin.getSetupFee(l_intSpecifiedPoint);
            assertEquals("5.1", String.valueOf(l_dblSetupFee));
        }
        catch(Exception l_ex)
        {
            log.error(l_ex.getMessage(), l_ex);
            fail();  
        }
        log.exiting(TEST_END + STR_METHOD_NAME);
    }
    /*
     * nが0以上でない時、0を返却する。
     */
    public void testGetSetupFee_C0007()
    {
        final String STR_METHOD_NAME = "testgetSetupFee_C0007()";
        log.entering(TEST_START + STR_METHOD_NAME);

        try
        {
            int l_intSpecifiedPoint = -1;

            TpCalcResultMarginDetailParams l_TpCalcResultMarginDetailParams = TestDBUtility.getTpCalcResultMarginDetailRow();
            l_TpCalcResultMarginDetailParams.setSetupFee(0.1);
            l_TpCalcResultMarginDetailParams.setSetupFee1(1.1);
            l_TpCalcResultMarginDetailParams.setSetupFee2(2.1);
            l_TpCalcResultMarginDetailParams.setSetupFee3(3.1);
            l_TpCalcResultMarginDetailParams.setSetupFee4(4.1);
            l_TpCalcResultMarginDetailParams.setSetupFee5(5.1);
            
            WEB3TPTradingPowerCalcMargin l_margin = new WEB3TPTradingPowerCalcMargin();
            l_margin.setCalcResultDetailMargin(l_TpCalcResultMarginDetailParams);

            double l_dblSetupFee = l_margin.getSetupFee(l_intSpecifiedPoint);
            assertEquals("0.0", String.valueOf(l_dblSetupFee));
        }
        catch(Exception l_ex)
        {
            log.error(l_ex.getMessage(), l_ex);
            fail();  
        }
        log.exiting(TEST_END + STR_METHOD_NAME);
    }
    /*
     * nが5以下でない時、0を返却する。
     */
    public void testGetSetupFee_C0008()
    {
        final String STR_METHOD_NAME = "testgetSetupFee_C0008()";
        log.entering(TEST_START + STR_METHOD_NAME);

        try
        {
            int l_intSpecifiedPoint = 6;

            TpCalcResultMarginDetailParams l_TpCalcResultMarginDetailParams = TestDBUtility.getTpCalcResultMarginDetailRow();
            l_TpCalcResultMarginDetailParams.setSetupFee(0.1);
            l_TpCalcResultMarginDetailParams.setSetupFee1(1.1);
            l_TpCalcResultMarginDetailParams.setSetupFee2(2.1);
            l_TpCalcResultMarginDetailParams.setSetupFee3(3.1);
            l_TpCalcResultMarginDetailParams.setSetupFee4(4.1);
            l_TpCalcResultMarginDetailParams.setSetupFee5(5.1);
            
            WEB3TPTradingPowerCalcMargin l_margin = new WEB3TPTradingPowerCalcMargin();
            l_margin.setCalcResultDetailMargin(l_TpCalcResultMarginDetailParams);

            double l_dblSetupFee = l_margin.getSetupFee(l_intSpecifiedPoint);
            assertEquals("0.0", String.valueOf(l_dblSetupFee));
        }
        catch(Exception l_ex)
        {
            log.error(l_ex.getMessage(), l_ex);
            fail();  
        }
        log.exiting(TEST_END + STR_METHOD_NAME);
    }

    /*
     * 日歩・逆日歩損( T + 0 )を取得する。
     */
    public void testGetContractInterestLoss_C0001()
    {
        final String STR_METHOD_NAME = "testGetContractInterestLoss_C0001()";
        log.entering(TEST_START + STR_METHOD_NAME);

        try
        {
            int l_intSpecifiedPoint = 0;

            TpCalcResultMarginDetailParams l_TpCalcResultMarginDetailParams = TestDBUtility.getTpCalcResultMarginDetailRow();
            l_TpCalcResultMarginDetailParams.setContractInterestLoss(0.1);
            l_TpCalcResultMarginDetailParams.setContractInterestLoss1(1.1);
            l_TpCalcResultMarginDetailParams.setContractInterestLoss2(2.1);
            l_TpCalcResultMarginDetailParams.setContractInterestLoss3(3.1);
            l_TpCalcResultMarginDetailParams.setContractInterestLoss4(4.1);
            l_TpCalcResultMarginDetailParams.setContractInterestLoss5(5.1);

            WEB3TPTradingPowerCalcMargin l_margin = new WEB3TPTradingPowerCalcMargin();
            l_margin.setCalcResultDetailMargin(l_TpCalcResultMarginDetailParams);

            double l_dblContractInterestLoss = l_margin.getContractInterestLoss(l_intSpecifiedPoint);
            assertEquals("0.1", String.valueOf(l_dblContractInterestLoss));
        }
        catch(Exception l_ex)
        {
            log.error(l_ex.getMessage(), l_ex);
            fail();  
        }
        log.exiting(TEST_END + STR_METHOD_NAME);
    }
    /*
     * 日歩・逆日歩損( T + 1 )を取得する。
     */
    public void testGetContractInterestLoss_C0002()
    {
        final String STR_METHOD_NAME = "testGetContractInterestLoss_C0002()";
        log.entering(TEST_START + STR_METHOD_NAME);

        try
        {
            int l_intSpecifiedPoint = 1;

            TpCalcResultMarginDetailParams l_TpCalcResultMarginDetailParams = TestDBUtility.getTpCalcResultMarginDetailRow();
            l_TpCalcResultMarginDetailParams.setContractInterestLoss(0.1);
            l_TpCalcResultMarginDetailParams.setContractInterestLoss1(1.1);
            l_TpCalcResultMarginDetailParams.setContractInterestLoss2(2.1);
            l_TpCalcResultMarginDetailParams.setContractInterestLoss3(3.1);
            l_TpCalcResultMarginDetailParams.setContractInterestLoss4(4.1);
            l_TpCalcResultMarginDetailParams.setContractInterestLoss5(5.1);

            WEB3TPTradingPowerCalcMargin l_margin = new WEB3TPTradingPowerCalcMargin();
            l_margin.setCalcResultDetailMargin(l_TpCalcResultMarginDetailParams);

            double l_dblContractInterestLoss = l_margin.getContractInterestLoss(l_intSpecifiedPoint);
            assertEquals("1.1", String.valueOf(l_dblContractInterestLoss));
        }
        catch(Exception l_ex)
        {
            log.error(l_ex.getMessage(), l_ex);
            fail();  
        }
        log.exiting(TEST_END + STR_METHOD_NAME);
    }
    /*
     * 日歩・逆日歩損( T + 2 )を取得する。
     */
    public void testGetContractInterestLoss_C0003()
    {
        final String STR_METHOD_NAME = "testGetContractInterestLoss_C0003()";
        log.entering(TEST_START + STR_METHOD_NAME);

        try
        {
            int l_intSpecifiedPoint = 2;

            TpCalcResultMarginDetailParams l_TpCalcResultMarginDetailParams = TestDBUtility.getTpCalcResultMarginDetailRow();
            l_TpCalcResultMarginDetailParams.setContractInterestLoss(0.1);
            l_TpCalcResultMarginDetailParams.setContractInterestLoss1(1.1);
            l_TpCalcResultMarginDetailParams.setContractInterestLoss2(2.1);
            l_TpCalcResultMarginDetailParams.setContractInterestLoss3(3.1);
            l_TpCalcResultMarginDetailParams.setContractInterestLoss4(4.1);
            l_TpCalcResultMarginDetailParams.setContractInterestLoss5(5.1);

            WEB3TPTradingPowerCalcMargin l_margin = new WEB3TPTradingPowerCalcMargin();
            l_margin.setCalcResultDetailMargin(l_TpCalcResultMarginDetailParams);

            double l_dblContractInterestLoss = l_margin.getContractInterestLoss(l_intSpecifiedPoint);
            assertEquals("2.1", String.valueOf(l_dblContractInterestLoss));
        }
        catch(Exception l_ex)
        {
            log.error(l_ex.getMessage(), l_ex);
            fail();  
        }
        log.exiting(TEST_END + STR_METHOD_NAME);
    }
    /*
     * 日歩・逆日歩損( T + 3 )を取得する。
     */
    public void testGetContractInterestLoss_C0004()
    {
        final String STR_METHOD_NAME = "testGetContractInterestLoss_C0004()";
        log.entering(TEST_START + STR_METHOD_NAME);

        try
        {
            int l_intSpecifiedPoint = 3;

            TpCalcResultMarginDetailParams l_TpCalcResultMarginDetailParams = TestDBUtility.getTpCalcResultMarginDetailRow();
            l_TpCalcResultMarginDetailParams.setContractInterestLoss(0.1);
            l_TpCalcResultMarginDetailParams.setContractInterestLoss1(1.1);
            l_TpCalcResultMarginDetailParams.setContractInterestLoss2(2.1);
            l_TpCalcResultMarginDetailParams.setContractInterestLoss3(3.1);
            l_TpCalcResultMarginDetailParams.setContractInterestLoss4(4.1);
            l_TpCalcResultMarginDetailParams.setContractInterestLoss5(5.1);

            WEB3TPTradingPowerCalcMargin l_margin = new WEB3TPTradingPowerCalcMargin();
            l_margin.setCalcResultDetailMargin(l_TpCalcResultMarginDetailParams);

            double l_dblContractInterestLoss = l_margin.getContractInterestLoss(l_intSpecifiedPoint);
            assertEquals("3.1", String.valueOf(l_dblContractInterestLoss));
        }
        catch(Exception l_ex)
        {
            log.error(l_ex.getMessage(), l_ex);
            fail();  
        }
        log.exiting(TEST_END + STR_METHOD_NAME);
    }
    /*
     * 日歩・逆日歩損( T + 4 )を取得する。
     */
    public void testGetContractInterestLoss_C0005()
    {
        final String STR_METHOD_NAME = "testGetContractInterestLoss_C0005()";
        log.entering(TEST_START + STR_METHOD_NAME);

        try
        {
            int l_intSpecifiedPoint = 4;

            TpCalcResultMarginDetailParams l_TpCalcResultMarginDetailParams = TestDBUtility.getTpCalcResultMarginDetailRow();
            l_TpCalcResultMarginDetailParams.setContractInterestLoss(0.1);
            l_TpCalcResultMarginDetailParams.setContractInterestLoss1(1.1);
            l_TpCalcResultMarginDetailParams.setContractInterestLoss2(2.1);
            l_TpCalcResultMarginDetailParams.setContractInterestLoss3(3.1);
            l_TpCalcResultMarginDetailParams.setContractInterestLoss4(4.1);
            l_TpCalcResultMarginDetailParams.setContractInterestLoss5(5.1);

            WEB3TPTradingPowerCalcMargin l_margin = new WEB3TPTradingPowerCalcMargin();
            l_margin.setCalcResultDetailMargin(l_TpCalcResultMarginDetailParams);

            double l_dblContractInterestLoss = l_margin.getContractInterestLoss(l_intSpecifiedPoint);
            assertEquals("4.1", String.valueOf(l_dblContractInterestLoss));
        }
        catch(Exception l_ex)
        {
            log.error(l_ex.getMessage(), l_ex);
            fail();  
        }
        log.exiting(TEST_END + STR_METHOD_NAME);
    }
    /*
     * 日歩・逆日歩損( T + 5 )を取得する。
     */
    public void testGetContractInterestLoss_C0006()
    {
        final String STR_METHOD_NAME = "testGetContractInterestLoss_C0006()";
        log.entering(TEST_START + STR_METHOD_NAME);

        try
        {
            int l_intSpecifiedPoint = 5;

            TpCalcResultMarginDetailParams l_TpCalcResultMarginDetailParams = TestDBUtility.getTpCalcResultMarginDetailRow();
            l_TpCalcResultMarginDetailParams.setContractInterestLoss(0.1);
            l_TpCalcResultMarginDetailParams.setContractInterestLoss1(1.1);
            l_TpCalcResultMarginDetailParams.setContractInterestLoss2(2.1);
            l_TpCalcResultMarginDetailParams.setContractInterestLoss3(3.1);
            l_TpCalcResultMarginDetailParams.setContractInterestLoss4(4.1);
            l_TpCalcResultMarginDetailParams.setContractInterestLoss5(5.1);

            WEB3TPTradingPowerCalcMargin l_margin = new WEB3TPTradingPowerCalcMargin();
            l_margin.setCalcResultDetailMargin(l_TpCalcResultMarginDetailParams);

            double l_dblContractInterestLoss = l_margin.getContractInterestLoss(l_intSpecifiedPoint);
            assertEquals("5.1", String.valueOf(l_dblContractInterestLoss));
        }
        catch(Exception l_ex)
        {
            log.error(l_ex.getMessage(), l_ex);
            fail();  
        }
        log.exiting(TEST_END + STR_METHOD_NAME);
    }
    /*
     * nが0以上でない時、0を返却する。
     */
    public void testGetContractInterestLoss_C0007()
    {
        final String STR_METHOD_NAME = "testGetContractInterestLoss_C0007()";
        log.entering(TEST_START + STR_METHOD_NAME);

        try
        {
            int l_intSpecifiedPoint = -1;

            TpCalcResultMarginDetailParams l_TpCalcResultMarginDetailParams = TestDBUtility.getTpCalcResultMarginDetailRow();
            l_TpCalcResultMarginDetailParams.setContractInterestLoss(0.1);
            l_TpCalcResultMarginDetailParams.setContractInterestLoss1(1.1);
            l_TpCalcResultMarginDetailParams.setContractInterestLoss2(2.1);
            l_TpCalcResultMarginDetailParams.setContractInterestLoss3(3.1);
            l_TpCalcResultMarginDetailParams.setContractInterestLoss4(4.1);
            l_TpCalcResultMarginDetailParams.setContractInterestLoss5(5.1);

            WEB3TPTradingPowerCalcMargin l_margin = new WEB3TPTradingPowerCalcMargin();
            l_margin.setCalcResultDetailMargin(l_TpCalcResultMarginDetailParams);

            double l_dblContractInterestLoss = l_margin.getContractInterestLoss(l_intSpecifiedPoint);
            assertEquals("0.0", String.valueOf(l_dblContractInterestLoss));
        }
        catch(Exception l_ex)
        {
            log.error(l_ex.getMessage(), l_ex);
            fail();  
        }
        log.exiting(TEST_END + STR_METHOD_NAME);
    }
    /*
     * nが5以下でない時、0を返却する。
     */
    public void testGetContractInterestLoss_C0008()
    {
        final String STR_METHOD_NAME = "testGetContractInterestLoss_C0008()";
        log.entering(TEST_START + STR_METHOD_NAME);

        try
        {
            int l_intSpecifiedPoint = 6;

            TpCalcResultMarginDetailParams l_TpCalcResultMarginDetailParams = TestDBUtility.getTpCalcResultMarginDetailRow();
            l_TpCalcResultMarginDetailParams.setContractInterestLoss(0.1);
            l_TpCalcResultMarginDetailParams.setContractInterestLoss1(1.1);
            l_TpCalcResultMarginDetailParams.setContractInterestLoss2(2.1);
            l_TpCalcResultMarginDetailParams.setContractInterestLoss3(3.1);
            l_TpCalcResultMarginDetailParams.setContractInterestLoss4(4.1);
            l_TpCalcResultMarginDetailParams.setContractInterestLoss5(5.1);

            WEB3TPTradingPowerCalcMargin l_margin = new WEB3TPTradingPowerCalcMargin();
            l_margin.setCalcResultDetailMargin(l_TpCalcResultMarginDetailParams);

            double l_dblContractInterestLoss = l_margin.getContractInterestLoss(l_intSpecifiedPoint);
            assertEquals("0.0", String.valueOf(l_dblContractInterestLoss));
        }
        catch(Exception l_ex)
        {
            log.error(l_ex.getMessage(), l_ex);
            fail();  
        }
        log.exiting(TEST_END + STR_METHOD_NAME);
    }
    
    /*
     * 日歩・逆日歩益( T + 0 )を取得する。
     */
    public void testGetContractInterestProfit_C0001()
    {
        final String STR_METHOD_NAME = "testGetContractInterestProfit_C0001()";
        log.entering(TEST_START + STR_METHOD_NAME);

        try
        {
            int l_intSpecifiedPoint = 0;

            TpCalcResultMarginDetailParams l_TpCalcResultMarginDetailParams = TestDBUtility.getTpCalcResultMarginDetailRow();
            l_TpCalcResultMarginDetailParams.setContractInterestProfit(0.1);
            l_TpCalcResultMarginDetailParams.setContractInterestProfit1(1.1);
            l_TpCalcResultMarginDetailParams.setContractInterestProfit2(2.1);
            l_TpCalcResultMarginDetailParams.setContractInterestProfit3(3.1);
            l_TpCalcResultMarginDetailParams.setContractInterestProfit4(4.1);
            l_TpCalcResultMarginDetailParams.setContractInterestProfit5(5.1);

            WEB3TPTradingPowerCalcMargin l_margin = new WEB3TPTradingPowerCalcMargin();
            l_margin.setCalcResultDetailMargin(l_TpCalcResultMarginDetailParams);

            double l_dblContractInterestProfit = l_margin.getContractInterestProfit(l_intSpecifiedPoint);
            assertEquals("0.1", String.valueOf(l_dblContractInterestProfit));
        }
        catch(Exception l_ex)
        {
            log.error(l_ex.getMessage(), l_ex);
            fail();  
        }
        log.exiting(TEST_END + STR_METHOD_NAME);
    }
    /*
     * 日歩・逆日歩益( T + 1 )を取得する。
     */
    public void testGetContractInterestProfit_C0002()
    {
        final String STR_METHOD_NAME = "testGetContractInterestProfit_C0002()";
        log.entering(TEST_START + STR_METHOD_NAME);

        try
        {
            int l_intSpecifiedPoint = 1;

            TpCalcResultMarginDetailParams l_TpCalcResultMarginDetailParams = TestDBUtility.getTpCalcResultMarginDetailRow();
            l_TpCalcResultMarginDetailParams.setContractInterestProfit(0.1);
            l_TpCalcResultMarginDetailParams.setContractInterestProfit1(1.1);
            l_TpCalcResultMarginDetailParams.setContractInterestProfit2(2.1);
            l_TpCalcResultMarginDetailParams.setContractInterestProfit3(3.1);
            l_TpCalcResultMarginDetailParams.setContractInterestProfit4(4.1);
            l_TpCalcResultMarginDetailParams.setContractInterestProfit5(5.1);

            WEB3TPTradingPowerCalcMargin l_margin = new WEB3TPTradingPowerCalcMargin();
            l_margin.setCalcResultDetailMargin(l_TpCalcResultMarginDetailParams);

            double l_dblContractInterestProfit = l_margin.getContractInterestProfit(l_intSpecifiedPoint);
            assertEquals("1.1", String.valueOf(l_dblContractInterestProfit));
        }
        catch(Exception l_ex)
        {
            log.error(l_ex.getMessage(), l_ex);
            fail();  
        }
        log.exiting(TEST_END + STR_METHOD_NAME);
    }
    /*
     * 日歩・逆日歩益( T + 2 )を取得する。
     */
    public void testGetContractInterestProfit_C0003()
    {
        final String STR_METHOD_NAME = "testGetContractInterestProfit_C0003()";
        log.entering(TEST_START + STR_METHOD_NAME);

        try
        {
            int l_intSpecifiedPoint = 2;

            TpCalcResultMarginDetailParams l_TpCalcResultMarginDetailParams = TestDBUtility.getTpCalcResultMarginDetailRow();
            l_TpCalcResultMarginDetailParams.setContractInterestProfit(0.1);
            l_TpCalcResultMarginDetailParams.setContractInterestProfit1(1.1);
            l_TpCalcResultMarginDetailParams.setContractInterestProfit2(2.1);
            l_TpCalcResultMarginDetailParams.setContractInterestProfit3(3.1);
            l_TpCalcResultMarginDetailParams.setContractInterestProfit4(4.1);
            l_TpCalcResultMarginDetailParams.setContractInterestProfit5(5.1);

            WEB3TPTradingPowerCalcMargin l_margin = new WEB3TPTradingPowerCalcMargin();
            l_margin.setCalcResultDetailMargin(l_TpCalcResultMarginDetailParams);

            double l_dblContractInterestProfit = l_margin.getContractInterestProfit(l_intSpecifiedPoint);
            assertEquals("2.1", String.valueOf(l_dblContractInterestProfit));
        }
        catch(Exception l_ex)
        {
            log.error(l_ex.getMessage(), l_ex);
            fail();  
        }
        log.exiting(TEST_END + STR_METHOD_NAME);
    }
    /*
     * 日歩・逆日歩益( T + 3 )を取得する。
     */
    public void testGetContractInterestProfit_C0004()
    {
        final String STR_METHOD_NAME = "testGetContractInterestProfit_C0004()";
        log.entering(TEST_START + STR_METHOD_NAME);

        try
        {
            int l_intSpecifiedPoint = 3;

            TpCalcResultMarginDetailParams l_TpCalcResultMarginDetailParams = TestDBUtility.getTpCalcResultMarginDetailRow();
            l_TpCalcResultMarginDetailParams.setContractInterestProfit(0.1);
            l_TpCalcResultMarginDetailParams.setContractInterestProfit1(1.1);
            l_TpCalcResultMarginDetailParams.setContractInterestProfit2(2.1);
            l_TpCalcResultMarginDetailParams.setContractInterestProfit3(3.1);
            l_TpCalcResultMarginDetailParams.setContractInterestProfit4(4.1);
            l_TpCalcResultMarginDetailParams.setContractInterestProfit5(5.1);

            WEB3TPTradingPowerCalcMargin l_margin = new WEB3TPTradingPowerCalcMargin();
            l_margin.setCalcResultDetailMargin(l_TpCalcResultMarginDetailParams);

            double l_dblContractInterestProfit = l_margin.getContractInterestProfit(l_intSpecifiedPoint);
            assertEquals("3.1", String.valueOf(l_dblContractInterestProfit));
        }
        catch(Exception l_ex)
        {
            log.error(l_ex.getMessage(), l_ex);
            fail();  
        }
        log.exiting(TEST_END + STR_METHOD_NAME);
    }
    /*
     * 日歩・逆日歩益( T + 4 )を取得する。
     */
    public void testGetContractInterestProfit_C0005()
    {
        final String STR_METHOD_NAME = "testGetContractInterestProfit_C0005()";
        log.entering(TEST_START + STR_METHOD_NAME);

        try
        {
            int l_intSpecifiedPoint = 4;

            TpCalcResultMarginDetailParams l_TpCalcResultMarginDetailParams = TestDBUtility.getTpCalcResultMarginDetailRow();
            l_TpCalcResultMarginDetailParams.setContractInterestProfit(0.1);
            l_TpCalcResultMarginDetailParams.setContractInterestProfit1(1.1);
            l_TpCalcResultMarginDetailParams.setContractInterestProfit2(2.1);
            l_TpCalcResultMarginDetailParams.setContractInterestProfit3(3.1);
            l_TpCalcResultMarginDetailParams.setContractInterestProfit4(4.1);
            l_TpCalcResultMarginDetailParams.setContractInterestProfit5(5.1);

            WEB3TPTradingPowerCalcMargin l_margin = new WEB3TPTradingPowerCalcMargin();
            l_margin.setCalcResultDetailMargin(l_TpCalcResultMarginDetailParams);

            double l_dblContractInterestProfit = l_margin.getContractInterestProfit(l_intSpecifiedPoint);
            assertEquals("4.1", String.valueOf(l_dblContractInterestProfit));
        }
        catch(Exception l_ex)
        {
            log.error(l_ex.getMessage(), l_ex);
            fail();  
        }
        log.exiting(TEST_END + STR_METHOD_NAME);
    }
    /*
     * 日歩・逆日歩益( T + 5 )を取得する。
     */
    public void testGetContractInterestProfit_C0006()
    {
        final String STR_METHOD_NAME = "testGetContractInterestProfit_C0006()";
        log.entering(TEST_START + STR_METHOD_NAME);

        try
        {
            int l_intSpecifiedPoint = 5;

            TpCalcResultMarginDetailParams l_TpCalcResultMarginDetailParams = TestDBUtility.getTpCalcResultMarginDetailRow();
            l_TpCalcResultMarginDetailParams.setContractInterestProfit(0.1);
            l_TpCalcResultMarginDetailParams.setContractInterestProfit1(1.1);
            l_TpCalcResultMarginDetailParams.setContractInterestProfit2(2.1);
            l_TpCalcResultMarginDetailParams.setContractInterestProfit3(3.1);
            l_TpCalcResultMarginDetailParams.setContractInterestProfit4(4.1);
            l_TpCalcResultMarginDetailParams.setContractInterestProfit5(5.1);

            WEB3TPTradingPowerCalcMargin l_margin = new WEB3TPTradingPowerCalcMargin();
            l_margin.setCalcResultDetailMargin(l_TpCalcResultMarginDetailParams);

            double l_dblContractInterestProfit = l_margin.getContractInterestProfit(l_intSpecifiedPoint);
            assertEquals("5.1", String.valueOf(l_dblContractInterestProfit));
        }
        catch(Exception l_ex)
        {
            log.error(l_ex.getMessage(), l_ex);
            fail();  
        }
        log.exiting(TEST_END + STR_METHOD_NAME);
    }
    /*
     * nが0以上でない時、0を返却する。
     */
    public void testGetContractInterestProfit_C0007()
    {
        final String STR_METHOD_NAME = "testGetContractInterestProfit_C0007()";
        log.entering(TEST_START + STR_METHOD_NAME);

        try
        {
            int l_intSpecifiedPoint = -1;

            TpCalcResultMarginDetailParams l_TpCalcResultMarginDetailParams = TestDBUtility.getTpCalcResultMarginDetailRow();
            l_TpCalcResultMarginDetailParams.setContractInterestProfit(0.1);
            l_TpCalcResultMarginDetailParams.setContractInterestProfit1(1.1);
            l_TpCalcResultMarginDetailParams.setContractInterestProfit2(2.1);
            l_TpCalcResultMarginDetailParams.setContractInterestProfit3(3.1);
            l_TpCalcResultMarginDetailParams.setContractInterestProfit4(4.1);
            l_TpCalcResultMarginDetailParams.setContractInterestProfit5(5.1);

            WEB3TPTradingPowerCalcMargin l_margin = new WEB3TPTradingPowerCalcMargin();
            l_margin.setCalcResultDetailMargin(l_TpCalcResultMarginDetailParams);

            double l_dblContractInterestProfit = l_margin.getContractInterestProfit(l_intSpecifiedPoint);
            assertEquals("0.0", String.valueOf(l_dblContractInterestProfit));
        }
        catch(Exception l_ex)
        {
            log.error(l_ex.getMessage(), l_ex);
            fail();  
        }
        log.exiting(TEST_END + STR_METHOD_NAME);
    }
    /*
     * nが5以下でない時、0を返却する。
     */
    public void testGetContractInterestProfit_C0008()
    {
        final String STR_METHOD_NAME = "testGetContractInterestProfit_C0008()";
        log.entering(TEST_START + STR_METHOD_NAME);

        try
        {
            int l_intSpecifiedPoint = 6;

            TpCalcResultMarginDetailParams l_TpCalcResultMarginDetailParams = TestDBUtility.getTpCalcResultMarginDetailRow();
            l_TpCalcResultMarginDetailParams.setContractInterestProfit(0.1);
            l_TpCalcResultMarginDetailParams.setContractInterestProfit1(1.1);
            l_TpCalcResultMarginDetailParams.setContractInterestProfit2(2.1);
            l_TpCalcResultMarginDetailParams.setContractInterestProfit3(3.1);
            l_TpCalcResultMarginDetailParams.setContractInterestProfit4(4.1);
            l_TpCalcResultMarginDetailParams.setContractInterestProfit5(5.1);

            WEB3TPTradingPowerCalcMargin l_margin = new WEB3TPTradingPowerCalcMargin();
            l_margin.setCalcResultDetailMargin(l_TpCalcResultMarginDetailParams);

            double l_dblContractInterestProfit = l_margin.getContractInterestProfit(l_intSpecifiedPoint);
            assertEquals("0.0", String.valueOf(l_dblContractInterestProfit));
        }
        catch(Exception l_ex)
        {
            log.error(l_ex.getMessage(), l_ex);
            fail();  
        }
        log.exiting(TEST_END + STR_METHOD_NAME);
    }
    
    /*
     * その他建玉諸経費( T + 0 )を取得する。
     */
    public void testGetContractOtherCost_C0001()
    {
        final String STR_METHOD_NAME = "testGetContractOtherCost_C0001()";
        log.entering(TEST_START + STR_METHOD_NAME);

        try
        {
            int l_intSpecifiedPoint = 0;

            TpCalcResultMarginDetailParams l_TpCalcResultMarginDetailParams = TestDBUtility.getTpCalcResultMarginDetailRow();
            l_TpCalcResultMarginDetailParams.setContractOtherCost(0.1);
            l_TpCalcResultMarginDetailParams.setContractOtherCost1(1.1);
            l_TpCalcResultMarginDetailParams.setContractOtherCost2(2.1);
            l_TpCalcResultMarginDetailParams.setContractOtherCost3(3.1);
            l_TpCalcResultMarginDetailParams.setContractOtherCost4(4.1);
            l_TpCalcResultMarginDetailParams.setContractOtherCost5(5.1);

            WEB3TPTradingPowerCalcMargin l_margin = new WEB3TPTradingPowerCalcMargin();
            l_margin.setCalcResultDetailMargin(l_TpCalcResultMarginDetailParams);

            double l_dblContractOtherCost = l_margin.getContractOtherCost(l_intSpecifiedPoint);
            assertEquals("0.1", String.valueOf(l_dblContractOtherCost));
        }
        catch(Exception l_ex)
        {
            log.error(l_ex.getMessage(), l_ex);
            fail();  
        }
        log.exiting(TEST_END + STR_METHOD_NAME);
    }
    /*
     * その他建玉諸経費( T + 1 )を取得する。
     */
    public void testGetContractOtherCost_C0002()
    {
        final String STR_METHOD_NAME = "testGetContractOtherCost_C0002()";
        log.entering(TEST_START + STR_METHOD_NAME);

        try
        {
            int l_intSpecifiedPoint = 1;

            TpCalcResultMarginDetailParams l_TpCalcResultMarginDetailParams = TestDBUtility.getTpCalcResultMarginDetailRow();
            l_TpCalcResultMarginDetailParams.setContractOtherCost(0.1);
            l_TpCalcResultMarginDetailParams.setContractOtherCost1(1.1);
            l_TpCalcResultMarginDetailParams.setContractOtherCost2(2.1);
            l_TpCalcResultMarginDetailParams.setContractOtherCost3(3.1);
            l_TpCalcResultMarginDetailParams.setContractOtherCost4(4.1);
            l_TpCalcResultMarginDetailParams.setContractOtherCost5(5.1);

            WEB3TPTradingPowerCalcMargin l_margin = new WEB3TPTradingPowerCalcMargin();
            l_margin.setCalcResultDetailMargin(l_TpCalcResultMarginDetailParams);

            double l_dblContractOtherCost = l_margin.getContractOtherCost(l_intSpecifiedPoint);
            assertEquals("1.1", String.valueOf(l_dblContractOtherCost));
        }
        catch(Exception l_ex)
        {
            log.error(l_ex.getMessage(), l_ex);
            fail();  
        }
        log.exiting(TEST_END + STR_METHOD_NAME);
    }
    /*
     * その他建玉諸経費( T + 2 )を取得する。
     */
    public void testGetContractOtherCost_C0003()
    {
        final String STR_METHOD_NAME = "testGetContractOtherCost_C0003()";
        log.entering(TEST_START + STR_METHOD_NAME);

        try
        {
            int l_intSpecifiedPoint = 2;

            TpCalcResultMarginDetailParams l_TpCalcResultMarginDetailParams = TestDBUtility.getTpCalcResultMarginDetailRow();
            l_TpCalcResultMarginDetailParams.setContractOtherCost(0.1);
            l_TpCalcResultMarginDetailParams.setContractOtherCost1(1.1);
            l_TpCalcResultMarginDetailParams.setContractOtherCost2(2.1);
            l_TpCalcResultMarginDetailParams.setContractOtherCost3(3.1);
            l_TpCalcResultMarginDetailParams.setContractOtherCost4(4.1);
            l_TpCalcResultMarginDetailParams.setContractOtherCost5(5.1);

            WEB3TPTradingPowerCalcMargin l_margin = new WEB3TPTradingPowerCalcMargin();
            l_margin.setCalcResultDetailMargin(l_TpCalcResultMarginDetailParams);

            double l_dblContractOtherCost = l_margin.getContractOtherCost(l_intSpecifiedPoint);
            assertEquals("2.1", String.valueOf(l_dblContractOtherCost));
        }
        catch(Exception l_ex)
        {
            log.error(l_ex.getMessage(), l_ex);
            fail();  
        }
        log.exiting(TEST_END + STR_METHOD_NAME);
    }
    /*
     * その他建玉諸経費( T + 3 )を取得する。
     */
    public void testGetContractOtherCost_C0004()
    {
        final String STR_METHOD_NAME = "testGetContractOtherCost_C0004()";
        log.entering(TEST_START + STR_METHOD_NAME);

        try
        {
            int l_intSpecifiedPoint = 3;

            TpCalcResultMarginDetailParams l_TpCalcResultMarginDetailParams = TestDBUtility.getTpCalcResultMarginDetailRow();
            l_TpCalcResultMarginDetailParams.setContractOtherCost(0.1);
            l_TpCalcResultMarginDetailParams.setContractOtherCost1(1.1);
            l_TpCalcResultMarginDetailParams.setContractOtherCost2(2.1);
            l_TpCalcResultMarginDetailParams.setContractOtherCost3(3.1);
            l_TpCalcResultMarginDetailParams.setContractOtherCost4(4.1);
            l_TpCalcResultMarginDetailParams.setContractOtherCost5(5.1);

            WEB3TPTradingPowerCalcMargin l_margin = new WEB3TPTradingPowerCalcMargin();
            l_margin.setCalcResultDetailMargin(l_TpCalcResultMarginDetailParams);

            double l_dblContractOtherCost = l_margin.getContractOtherCost(l_intSpecifiedPoint);
            assertEquals("3.1", String.valueOf(l_dblContractOtherCost));
        }
        catch(Exception l_ex)
        {
            log.error(l_ex.getMessage(), l_ex);
            fail();  
        }
        log.exiting(TEST_END + STR_METHOD_NAME);
    }
    /*
     * その他建玉諸経費( T + 4 )を取得する。
     */
    public void testGetContractOtherCost_C0005()
    {
        final String STR_METHOD_NAME = "testGetContractOtherCost_C0005()";
        log.entering(TEST_START + STR_METHOD_NAME);

        try
        {
            int l_intSpecifiedPoint = 4;

            TpCalcResultMarginDetailParams l_TpCalcResultMarginDetailParams = TestDBUtility.getTpCalcResultMarginDetailRow();
            l_TpCalcResultMarginDetailParams.setContractOtherCost(0.1);
            l_TpCalcResultMarginDetailParams.setContractOtherCost1(1.1);
            l_TpCalcResultMarginDetailParams.setContractOtherCost2(2.1);
            l_TpCalcResultMarginDetailParams.setContractOtherCost3(3.1);
            l_TpCalcResultMarginDetailParams.setContractOtherCost4(4.1);
            l_TpCalcResultMarginDetailParams.setContractOtherCost5(5.1);

            WEB3TPTradingPowerCalcMargin l_margin = new WEB3TPTradingPowerCalcMargin();
            l_margin.setCalcResultDetailMargin(l_TpCalcResultMarginDetailParams);

            double l_dblContractOtherCost = l_margin.getContractOtherCost(l_intSpecifiedPoint);
            assertEquals("4.1", String.valueOf(l_dblContractOtherCost));
        }
        catch(Exception l_ex)
        {
            log.error(l_ex.getMessage(), l_ex);
            fail();  
        }
        log.exiting(TEST_END + STR_METHOD_NAME);
    }
    /*
     * その他建玉諸経費( T + 5 )を取得する。
     */
    public void testGetContractOtherCost_C0006()
    {
        final String STR_METHOD_NAME = "testGetContractOtherCost_C0006()";
        log.entering(TEST_START + STR_METHOD_NAME);

        try
        {
            int l_intSpecifiedPoint = 5;

            TpCalcResultMarginDetailParams l_TpCalcResultMarginDetailParams = TestDBUtility.getTpCalcResultMarginDetailRow();
            l_TpCalcResultMarginDetailParams.setContractOtherCost(0.1);
            l_TpCalcResultMarginDetailParams.setContractOtherCost1(1.1);
            l_TpCalcResultMarginDetailParams.setContractOtherCost2(2.1);
            l_TpCalcResultMarginDetailParams.setContractOtherCost3(3.1);
            l_TpCalcResultMarginDetailParams.setContractOtherCost4(4.1);
            l_TpCalcResultMarginDetailParams.setContractOtherCost5(5.1);

            WEB3TPTradingPowerCalcMargin l_margin = new WEB3TPTradingPowerCalcMargin();
            l_margin.setCalcResultDetailMargin(l_TpCalcResultMarginDetailParams);

            double l_dblContractOtherCost = l_margin.getContractOtherCost(l_intSpecifiedPoint);
            assertEquals("5.1", String.valueOf(l_dblContractOtherCost));
        }
        catch(Exception l_ex)
        {
            log.error(l_ex.getMessage(), l_ex);
            fail();  
        }
        log.exiting(TEST_END + STR_METHOD_NAME);
    }
    /*
     * nが0以上でない時、0を返却する。
     */
    public void testGetContractOtherCost_C0007()
    {
        final String STR_METHOD_NAME = "testGetContractOtherCost_C0007()";
        log.entering(TEST_START + STR_METHOD_NAME);

        try
        {
            int l_intSpecifiedPoint = -1;

            TpCalcResultMarginDetailParams l_TpCalcResultMarginDetailParams = TestDBUtility.getTpCalcResultMarginDetailRow();
            l_TpCalcResultMarginDetailParams.setContractOtherCost(0.1);
            l_TpCalcResultMarginDetailParams.setContractOtherCost1(1.1);
            l_TpCalcResultMarginDetailParams.setContractOtherCost2(2.1);
            l_TpCalcResultMarginDetailParams.setContractOtherCost3(3.1);
            l_TpCalcResultMarginDetailParams.setContractOtherCost4(4.1);
            l_TpCalcResultMarginDetailParams.setContractOtherCost5(5.1);

            WEB3TPTradingPowerCalcMargin l_margin = new WEB3TPTradingPowerCalcMargin();
            l_margin.setCalcResultDetailMargin(l_TpCalcResultMarginDetailParams);

            double l_dblContractOtherCost = l_margin.getContractOtherCost(l_intSpecifiedPoint);
            assertEquals("0.0", String.valueOf(l_dblContractOtherCost));
        }
        catch(Exception l_ex)
        {
            log.error(l_ex.getMessage(), l_ex);
            fail();  
        }
        log.exiting(TEST_END + STR_METHOD_NAME);
    }
    /*
     * nが5以下でない時、0を返却する。
     */
    public void testGetContractOtherCost_C0008()
    {
        final String STR_METHOD_NAME = "testGetContractOtherCost_C0008()";
        log.entering(TEST_START + STR_METHOD_NAME);

        try
        {
            int l_intSpecifiedPoint = 6;

            TpCalcResultMarginDetailParams l_TpCalcResultMarginDetailParams = TestDBUtility.getTpCalcResultMarginDetailRow();
            l_TpCalcResultMarginDetailParams.setContractOtherCost(0.1);
            l_TpCalcResultMarginDetailParams.setContractOtherCost1(1.1);
            l_TpCalcResultMarginDetailParams.setContractOtherCost2(2.1);
            l_TpCalcResultMarginDetailParams.setContractOtherCost3(3.1);
            l_TpCalcResultMarginDetailParams.setContractOtherCost4(4.1);
            l_TpCalcResultMarginDetailParams.setContractOtherCost5(5.1);

            WEB3TPTradingPowerCalcMargin l_margin = new WEB3TPTradingPowerCalcMargin();
            l_margin.setCalcResultDetailMargin(l_TpCalcResultMarginDetailParams);

            double l_dblContractOtherCost = l_margin.getContractOtherCost(l_intSpecifiedPoint);
            assertEquals("0.0", String.valueOf(l_dblContractOtherCost));
        }
        catch(Exception l_ex)
        {
            log.error(l_ex.getMessage(), l_ex);
            fail();  
        }
        log.exiting(TEST_END + STR_METHOD_NAME);
    }
}
@
