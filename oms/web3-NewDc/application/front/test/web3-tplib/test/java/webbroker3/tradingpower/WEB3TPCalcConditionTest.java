head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.04.07.00.49.10;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	5444d9d078c63f4;
filename	WEB3TPCalcConditionTest.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : 余力計算条件Test(WEB3TPCalcConditionTest.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2007/08/03 鄧鋒鋼 (中訊) 新規作成
*/
package webbroker3.tradingpower;

import java.lang.reflect.Method;
import java.sql.Timestamp;
import java.util.Calendar;
import java.util.Date;

import test.util.TestDBUtility;
import webbroker3.gentrade.WEB3GentradeSubAccount;
import webbroker3.gentrade.WEB3GentradeTradingClendarContext;
import webbroker3.gentrade.WEB3GentradeTradingTimeManagement;
import webbroker3.gentrade.data.FeqLastClosingPriceParams;
import webbroker3.gentrade.data.ProcessManagementParams;
import webbroker3.gentrade.data.ProcessManagementRow;
import webbroker3.gentrade.data.SecurityCashoutRestraintParams;
import webbroker3.gentrade.data.SecurityCashoutRestraintRow;
import webbroker3.gentrade.data.StockSecuredLoanParams;
import webbroker3.gentrade.data.StockSecuredLoanRow;
import webbroker3.gentrade.data.TradingTimeParams;
import webbroker3.gentrade.data.TradingTimeRow;
import webbroker3.gentrade.data.TradingpowerCalcConditionParams;
import webbroker3.gentrade.data.TradingpowerCalcConditionRow;
import webbroker3.mock.TestBaseForMock;
import webbroker3.util.WEB3DateUtility;
import webbroker3.util.WEB3LogUtility;

import com.fitechlabs.xtrade.kernel.util.ThreadLocalSystemAttributesRegistry;
import com.fitechlabs.xtrade.plugin.tc.gentrade.data.BranchParams;
import com.fitechlabs.xtrade.plugin.tc.gentrade.data.BranchRow;
import com.fitechlabs.xtrade.plugin.tc.gentrade.data.InstitutionParams;
import com.fitechlabs.xtrade.plugin.tc.gentrade.data.InstitutionRow;
import com.fitechlabs.xtrade.plugin.tc.gentrade.data.MainAccountParams;
import com.fitechlabs.xtrade.plugin.tc.gentrade.data.MainAccountRow;
import com.fitechlabs.xtrade.plugin.tc.gentrade.data.SubAccountParams;
import com.fitechlabs.xtrade.plugin.tc.gentrade.data.SubAccountRow;

public class WEB3TPCalcConditionTest extends TestBaseForMock
{
	
	private static WEB3LogUtility log =
        WEB3LogUtility.getInstance(WEB3TPCalcConditionTest.class); 

	public WEB3TPCalcCondition l_tpCalcCondition = null; 

	public WEB3TPCalcConditionTest(String name) {
		super(name);
	}
	
    protected void setUp() throws Exception
    {
        super.setUp();
        this.l_tpCalcCondition = new WEB3TPCalcCondition();
        
      TestDBUtility.deleteAll(InstitutionRow.TYPE);
      InstitutionParams l_institutionParams = TestDBUtility.getInstitutionRow();
      TestDBUtility.insertWithDel(l_institutionParams);
    }

    protected void tearDown() throws Exception
    {
        super.tearDown();
    }
    
//    public void testCreateCalcCondition_case0001()
//    {
//    	 final String STR_METHOD_NAME = "testCreateCalcCondition_case0001()";
//         log.entering(TEST_START + STR_METHOD_NAME);
//         
//         try
//         {
//             Calendar l_ca =  Calendar.getInstance();
//             l_ca.set(2007,7,2);
//             Date date = l_ca.getTime();
//        	 WEB3GentradeTradingTimeManagementForMock.mockGetOrderBizDate(date);
//        	 
//             TestDBUtility.deleteAll(BranchRow.TYPE);
//             BranchParams l_branchParams = TestDBUtility.getBranchRow();
//             TestDBUtility.insertWithDel(l_branchParams);
//             
//             TestDBUtility.deleteAll(InstitutionRow.TYPE);
//             InstitutionParams l_institutionParams = TestDBUtility.getInstitutionRow();
//             TestDBUtility.insertWithDel(l_institutionParams);
//             
//        	 TestDBUtility.deleteAll(MainAccountRow.TYPE);
//        	 MainAccountParams l_mainAccountParams = TestDBUtility.getMainAccountRow();
//        	 l_mainAccountParams.setAccountId(333812512203L);
//        	 TestDBUtility.insertWithDel(l_mainAccountParams);
//        	 
//        	 TestDBUtility.deleteAll(SubAccountRow.TYPE);
//        	 TestDBUtility.insertWithDel(TestDBUtility.getSubAccountRow());
//        	 WEB3GentradeSubAccount l_subAccount = new WEB3GentradeSubAccount(333812512203L,33381251220301L);
//        	 
//        	 TestDBUtility.deleteAll(TradingTimeRow.TYPE);
//        	 TestDBUtility.insertWithDel(TestDBUtility.getTradingTimeRow());
//        	 
//        	 TestDBUtility.deleteAll(TradingpowerCalcConditionRow.TYPE);
//        	 TradingpowerCalcConditionParams l_tradingpowerCalcConditionParams = TestDBUtility.getTradingpowerCalcConditionRow();
//        	 l_tradingpowerCalcConditionParams.setAccountId(333812512203L);
//        	 l_tradingpowerCalcConditionParams.setAdditionalDepositStop("1");
////             l_tradingpowerCalcConditionParams.setTradingStop("1");
//        	 TestDBUtility.insertWithDel(l_tradingpowerCalcConditionParams);
//        	 
//        	 
//             Method method = WEB3TPCalcCondition.class.getDeclaredMethod(
//                     "createCalcCondition", 
//                     new Class[] {WEB3GentradeSubAccount.class});
//             
//             method.setAccessible(true);
//
//             WEB3TPCalcCondition l_calcCondition = 
//            	 (WEB3TPCalcCondition)method.invoke(
//            			 this.l_tpCalcCondition, new Object[]{l_subAccount});
//             
//             assertEquals(true,l_calcCondition.isTradingStop());
//                 
//         }
//         catch(Exception l_ex)
//         {
//        	 log.error(STR_METHOD_NAME,l_ex);
//        	 fail();
//         }
//         
//         log.exiting(TEST_END + STR_METHOD_NAME);
//    }
//    
//    public void testCreateCalcCondition_case0002()
//    {
//    	 final String STR_METHOD_NAME = "testCreateCalcCondition_case0002()";
//         log.entering(TEST_START + STR_METHOD_NAME);
//         
//         try
//         {
//             Calendar l_ca =  Calendar.getInstance();
//             l_ca.set(2007,7,2);
//             Date date = l_ca.getTime();
//        	 WEB3GentradeTradingTimeManagementForMock.mockGetOrderBizDate(date);
//        	 
//             TestDBUtility.deleteAll(BranchRow.TYPE);
//             BranchParams l_branchParams = TestDBUtility.getBranchRow();
//             TestDBUtility.insertWithDel(l_branchParams);
//             
//             TestDBUtility.deleteAll(InstitutionRow.TYPE);
//             InstitutionParams l_institutionParams = TestDBUtility.getInstitutionRow();
//             TestDBUtility.insertWithDel(l_institutionParams);
//             
//        	 TestDBUtility.deleteAll(MainAccountRow.TYPE);
//        	 MainAccountParams l_mainAccountParams = TestDBUtility.getMainAccountRow();
//        	 l_mainAccountParams.setAccountId(333812512203L);
//        	 TestDBUtility.insertWithDel(l_mainAccountParams);
//        	 
//        	 TestDBUtility.deleteAll(SubAccountRow.TYPE);
//        	 TestDBUtility.insertWithDel(TestDBUtility.getSubAccountRow());
//        	 WEB3GentradeSubAccount l_subAccount = new WEB3GentradeSubAccount(333812512203L,33381251220301L);
//        	 
//        	 TestDBUtility.deleteAll(TradingTimeRow.TYPE);
//        	 TestDBUtility.insertWithDel(TestDBUtility.getTradingTimeRow());
//        	 
//        	 TestDBUtility.deleteAll(TradingpowerCalcConditionRow.TYPE);
//        	 TradingpowerCalcConditionParams l_tradingpowerCalcConditionParams = TestDBUtility.getTradingpowerCalcConditionRow();
//        	 l_tradingpowerCalcConditionParams.setAccountId(333812512203L);
//        	 l_tradingpowerCalcConditionParams.setAdditionalDepositStop("0");
//             l_tradingpowerCalcConditionParams.setTradingStop("1");
//        	 TestDBUtility.insertWithDel(l_tradingpowerCalcConditionParams);
//        	 
//        	 
//             Method method = WEB3TPCalcCondition.class.getDeclaredMethod(
//                     "createCalcCondition", 
//                     new Class[] {WEB3GentradeSubAccount.class});
//             
//             method.setAccessible(true);
//
//             WEB3TPCalcCondition l_calcCondition = 
//            	 (WEB3TPCalcCondition)method.invoke(
//            			 this.l_tpCalcCondition, new Object[]{l_subAccount});
//             
//             assertEquals(true,l_calcCondition.isTradingStop());
//                 
//         }
//         catch(Exception l_ex)
//         {
//        	 log.error(STR_METHOD_NAME,l_ex);
//        	 fail();
//         }
//         
//         log.exiting(TEST_END + STR_METHOD_NAME);
//    }
// 
//    public void testCreateCalcCondition_case0003()
//    {
//         final String STR_METHOD_NAME = "testCreateCalcCondition_case0003()";
//         log.entering(TEST_START + STR_METHOD_NAME);
//         
//         try
//         {
//             Calendar l_ca =  Calendar.getInstance();
//             l_ca.set(2007,7,2);
//             Date date = l_ca.getTime();
//             WEB3GentradeTradingTimeManagementForMock.mockGetOrderBizDate(date);
//             
//             TestDBUtility.deleteAll(BranchRow.TYPE);
//             BranchParams l_branchParams = TestDBUtility.getBranchRow();
//             TestDBUtility.insertWithDel(l_branchParams);
//             
//             TestDBUtility.deleteAll(InstitutionRow.TYPE);
//             InstitutionParams l_institutionParams = TestDBUtility.getInstitutionRow();
//             TestDBUtility.insertWithDel(l_institutionParams);
//             
//             TestDBUtility.deleteAll(MainAccountRow.TYPE);
//             MainAccountParams l_mainAccountParams = TestDBUtility.getMainAccountRow();
//             l_mainAccountParams.setAccountId(333812512203L);
//             TestDBUtility.insertWithDel(l_mainAccountParams);
//             
//             TestDBUtility.deleteAll(SubAccountRow.TYPE);
//             TestDBUtility.insertWithDel(TestDBUtility.getSubAccountRow());
//             WEB3GentradeSubAccount l_subAccount = new WEB3GentradeSubAccount(333812512203L,33381251220301L);
//             
//             TestDBUtility.deleteAll(TradingTimeRow.TYPE);
//             TestDBUtility.insertWithDel(TestDBUtility.getTradingTimeRow());
//             
//             TestDBUtility.deleteAll(TradingpowerCalcConditionRow.TYPE);
//             TradingpowerCalcConditionParams l_tradingpowerCalcConditionParams = TestDBUtility.getTradingpowerCalcConditionRow();
//             l_tradingpowerCalcConditionParams.setAccountId(333812512203L);
//             l_tradingpowerCalcConditionParams.setAdditionalDepositStop("0");
//             l_tradingpowerCalcConditionParams.setTradingStop("0");
//             TestDBUtility.insertWithDel(l_tradingpowerCalcConditionParams);
//             
//             
//             Method method = WEB3TPCalcCondition.class.getDeclaredMethod(
//                     "createCalcCondition", 
//                     new Class[] {WEB3GentradeSubAccount.class});
//             
//             method.setAccessible(true);
//
//             WEB3TPCalcCondition l_calcCondition = 
//                 (WEB3TPCalcCondition)method.invoke(
//                         this.l_tpCalcCondition, new Object[]{l_subAccount});
//             
//             assertEquals(false,l_calcCondition.isTradingStop());
//                 
//         }
//         catch(Exception l_ex)
//         {
//             log.error(STR_METHOD_NAME,l_ex);
//             fail();
//         }
//
//         log.exiting(TEST_END + STR_METHOD_NAME);
//    }
//
    /**
     * calc余力計算基準日 
     * “信用新規建可能額適用日” == FROM_T2_UNTIL_T5の場合
     * エラーがスローされた場合
     *
     */
    public void testCalcBasePoint_0001()
    {
        final String STR_METHOD_NAME = "testCalcBasePoint_0001()";
        log.entering(TEST_START + STR_METHOD_NAME);

        WEB3TPCalcCondition l_tpCalcCondition = new WEB3TPCalcConditionForTest();
        l_tpCalcCondition.addInstBranCalcCondition("marginopen.apply.date", "2");
        try
        {
            TestDBUtility.deleteAll(TradingTimeRow.TYPE);
            TradingTimeParams l_treadingTimeParams = TestDBUtility.getTradingTimeRow();
            l_treadingTimeParams.setInstitutionCode("0");
            l_treadingTimeParams.setBranchCode("123");
            l_treadingTimeParams.setMarketCode("0");
            l_treadingTimeParams.setTradingTimeType("11");
            l_treadingTimeParams.setProductCode("0");
            l_treadingTimeParams.setBizDateType("1");
            l_treadingTimeParams.setBizdateCalcParameter("0");
            l_treadingTimeParams.setSessionType("1");
            TestDBUtility.insertWithDel(l_treadingTimeParams);

            WEB3GentradeTradingClendarContext l_clendarContext = new WEB3GentradeTradingClendarContext();
            l_clendarContext.setInstitutionCode("0");
            l_clendarContext.setBranchCode("123");
            l_clendarContext.setMarketCode("0");
            l_clendarContext.setTradingTimeType("11");
            l_clendarContext.setProductCode("0");
            l_clendarContext.setBizDateType("1");

            ThreadLocalSystemAttributesRegistry.setAttribute("web3.tradingcalendarcontext", l_clendarContext);
            
            ThreadLocalSystemAttributesRegistry.setAttribute(
                    "web3.attributes.basetimestampfororderbizdate",
                    WEB3DateUtility.getDate("yyyyMMdd", "20070829"));

            TestDBUtility.deleteAll(SubAccountRow.TYPE);
            SubAccountParams l_subAccountParams = TestDBUtility.getSubAccountRow();
            TestDBUtility.insertWithDel(l_subAccountParams);

            WEB3GentradeSubAccount l_subAccount = new WEB3GentradeSubAccount(l_subAccountParams);

            l_tpCalcCondition.calcBasePoint(l_subAccount);

            assertEquals(2, l_tpCalcCondition.getMarginBasePoint());
        }
        catch (Exception l_ex)
        {
            log.error(STR_METHOD_NAME, l_ex);
            fail();
        }

        log.exiting(TEST_END + STR_METHOD_NAME);
    }
    
    /**
     * calc余力計算基準日 
     * “信用新規建可能額適用日” == FROM_T2_UNTIL_T5の場合
     *  発注日!＝業務日付の時
     *  発注日!＝翌日日付の時
     */
    public void testCalcBasePoint_0002()
    {
        final String STR_METHOD_NAME = "testCalcBasePoint_0002()";
        log.entering(TEST_START + STR_METHOD_NAME);

        WEB3TPCalcCondition l_tpCalcCondition = new WEB3TPCalcConditionForTest();
        l_tpCalcCondition.addInstBranCalcCondition("marginopen.apply.date", "2");
        try
        {
            Calendar l_calendar = Calendar.getInstance();
            l_calendar.set(Calendar.YEAR,2007);
            l_calendar.set(Calendar.MONTH,2);
            l_calendar.set(Calendar.DAY_OF_MONTH,14);
            l_calendar.set(Calendar.HOUR_OF_DAY,15);
            l_calendar.set(Calendar.MINUTE,00);
            l_calendar.set(Calendar.SECOND,01);

            Timestamp l_tsBizDate = new Timestamp(l_calendar.getTimeInMillis());

            ThreadLocalSystemAttributesRegistry.setAttribute(
                WEB3GentradeTradingTimeManagement.TIMESTAMP_TAG, l_tsBizDate);

            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                "com.fitechlabs.xtrade.plugin.tc.gentrade.stdimpls.TradingSystemImpl",
                "getSystemTimestamp",
                new Class[] {},
                l_tsBizDate);
            
            TestDBUtility.deleteAll(TradingTimeRow.TYPE);
            TradingTimeParams l_treadingTimeParams = TestDBUtility.getTradingTimeRow();
            l_treadingTimeParams.setInstitutionCode("0");
            l_treadingTimeParams.setBranchCode("123");
            l_treadingTimeParams.setMarketCode("0");
            l_treadingTimeParams.setTradingTimeType("01");
            l_treadingTimeParams.setProductCode("0");
            l_treadingTimeParams.setBizDateType("1");
            l_treadingTimeParams.setBizdateCalcParameter("0");
            l_treadingTimeParams.setSessionType("1");
            TestDBUtility.insertWithDel(l_treadingTimeParams);

            WEB3GentradeTradingClendarContext l_clendarContext = new WEB3GentradeTradingClendarContext();
            l_clendarContext.setInstitutionCode("0");
            l_clendarContext.setBranchCode("123");
            l_clendarContext.setMarketCode("0");
            l_clendarContext.setTradingTimeType("11");
            l_clendarContext.setProductCode("0");
            l_clendarContext.setBizDateType("1");

            ThreadLocalSystemAttributesRegistry.setAttribute("web3.tradingcalendarcontext", l_clendarContext);
            
            ThreadLocalSystemAttributesRegistry.setAttribute(
                    "web3.attributes.basetimestampfororderbizdate",
                    WEB3DateUtility.getDate("yyyyMMdd", "20070829"));

            TestDBUtility.deleteAll(SubAccountRow.TYPE);
            SubAccountParams l_subAccountParams = TestDBUtility.getSubAccountRow();
            TestDBUtility.insertWithDel(l_subAccountParams);

            WEB3GentradeSubAccount l_subAccount = new WEB3GentradeSubAccount(l_subAccountParams);

            l_tpCalcCondition.calcBasePoint(l_subAccount);

            assertEquals(2, l_tpCalcCondition.getMarginBasePoint());
        }
        catch (Exception l_ex)
        {
            log.error(STR_METHOD_NAME, l_ex);
            fail();
        }

        log.exiting(TEST_END + STR_METHOD_NAME);
    }
    
    /**
     * calc余力計算基準日 
     * “信用新規建可能額適用日” == FROM_T2_UNTIL_T5の場合
     *  発注日＝業務日付の時
     */
    public void testCalcBasePoint_0003()
    {
        final String STR_METHOD_NAME = "testCalcBasePoint_0002()";
        log.entering(TEST_START + STR_METHOD_NAME);

        WEB3TPCalcCondition l_tpCalcCondition = new WEB3TPCalcConditionForTest1();
        l_tpCalcCondition.addInstBranCalcCondition("marginopen.apply.date", "2");
        try
        {
            Calendar l_calendar = Calendar.getInstance();
            l_calendar.set(Calendar.YEAR,2007);
            l_calendar.set(Calendar.MONTH,2);
            l_calendar.set(Calendar.DAY_OF_MONTH,14);
            l_calendar.set(Calendar.HOUR_OF_DAY,15);
            l_calendar.set(Calendar.MINUTE,00);
            l_calendar.set(Calendar.SECOND,01);

            Timestamp l_tsBizDate = new Timestamp(l_calendar.getTimeInMillis());

            ThreadLocalSystemAttributesRegistry.setAttribute(
                WEB3GentradeTradingTimeManagement.TIMESTAMP_TAG, l_tsBizDate);

            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                "com.fitechlabs.xtrade.plugin.tc.gentrade.stdimpls.TradingSystemImpl",
                "getSystemTimestamp",
                new Class[] {},
                l_tsBizDate);
            
            TestDBUtility.deleteAll(TradingTimeRow.TYPE);
            TradingTimeParams l_treadingTimeParams = TestDBUtility.getTradingTimeRow();
            l_treadingTimeParams.setInstitutionCode("0");
            l_treadingTimeParams.setBranchCode("123");
            l_treadingTimeParams.setMarketCode("0");
            l_treadingTimeParams.setTradingTimeType("01");
            l_treadingTimeParams.setProductCode("0");
            l_treadingTimeParams.setBizDateType("1");
            l_treadingTimeParams.setBizdateCalcParameter("0");
            l_treadingTimeParams.setSessionType("1");
            TestDBUtility.insertWithDel(l_treadingTimeParams);

            WEB3GentradeTradingClendarContext l_clendarContext = new WEB3GentradeTradingClendarContext();
            l_clendarContext.setInstitutionCode("0");
            l_clendarContext.setBranchCode("123");
            l_clendarContext.setMarketCode("0");
            l_clendarContext.setTradingTimeType("11");
            l_clendarContext.setProductCode("0");
            l_clendarContext.setBizDateType("1");

            ThreadLocalSystemAttributesRegistry.setAttribute("web3.tradingcalendarcontext", l_clendarContext);
            
            ThreadLocalSystemAttributesRegistry.setAttribute(
                    "web3.attributes.basetimestampfororderbizdate",
                    WEB3DateUtility.getDate("yyyyMMdd", "20070829"));

            TestDBUtility.deleteAll(SubAccountRow.TYPE);
            SubAccountParams l_subAccountParams = TestDBUtility.getSubAccountRow();
            TestDBUtility.insertWithDel(l_subAccountParams);

            WEB3GentradeSubAccount l_subAccount = new WEB3GentradeSubAccount(l_subAccountParams);

            l_tpCalcCondition.calcBasePoint(l_subAccount);

            assertEquals(2, l_tpCalcCondition.getMarginBasePoint());
        }
        catch (Exception l_ex)
        {
            log.error(STR_METHOD_NAME, l_ex);
            fail();
        }

        log.exiting(TEST_END + STR_METHOD_NAME);
    }
    
    /**
     * calc余力計算基準日 
     * “信用新規建可能額適用日” == FROM_T2_UNTIL_T5の場合
     *  発注日!＝業務日付の時
     *  発注日＝翌日日付の時
     */
    public void testCalcBasePoint_0004()
    {
        final String STR_METHOD_NAME = "testCalcBasePoint_0004()";
        log.entering(TEST_START + STR_METHOD_NAME);

        WEB3TPCalcCondition l_tpCalcCondition = new WEB3TPCalcConditionForTest2();
        l_tpCalcCondition.addInstBranCalcCondition("marginopen.apply.date", "2");
        try
        {
            Calendar l_calendar = Calendar.getInstance();
            l_calendar.set(Calendar.YEAR,2007);
            l_calendar.set(Calendar.MONTH,2);
            l_calendar.set(Calendar.DAY_OF_MONTH,14);
            l_calendar.set(Calendar.HOUR_OF_DAY,15);
            l_calendar.set(Calendar.MINUTE,00);
            l_calendar.set(Calendar.SECOND,01);

            Timestamp l_tsBizDate = new Timestamp(l_calendar.getTimeInMillis());

            ThreadLocalSystemAttributesRegistry.setAttribute(
                WEB3GentradeTradingTimeManagement.TIMESTAMP_TAG, l_tsBizDate);

            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
                "com.fitechlabs.xtrade.plugin.tc.gentrade.stdimpls.TradingSystemImpl",
                "getSystemTimestamp",
                new Class[] {},
                l_tsBizDate);
            
            TestDBUtility.deleteAll(TradingTimeRow.TYPE);
            TradingTimeParams l_treadingTimeParams = TestDBUtility.getTradingTimeRow();
            l_treadingTimeParams.setInstitutionCode("0");
            l_treadingTimeParams.setBranchCode("123");
            l_treadingTimeParams.setMarketCode("0");
            l_treadingTimeParams.setTradingTimeType("01");
            l_treadingTimeParams.setProductCode("0");
            l_treadingTimeParams.setBizDateType("1");
            l_treadingTimeParams.setBizdateCalcParameter("0");
            l_treadingTimeParams.setSessionType("1");
            TestDBUtility.insertWithDel(l_treadingTimeParams);

            WEB3GentradeTradingClendarContext l_clendarContext = new WEB3GentradeTradingClendarContext();
            l_clendarContext.setInstitutionCode("0");
            l_clendarContext.setBranchCode("123");
            l_clendarContext.setMarketCode("0");
            l_clendarContext.setTradingTimeType("11");
            l_clendarContext.setProductCode("0");
            l_clendarContext.setBizDateType("1");

            ThreadLocalSystemAttributesRegistry.setAttribute("web3.tradingcalendarcontext", l_clendarContext);
            
            ThreadLocalSystemAttributesRegistry.setAttribute(
                    "web3.attributes.basetimestampfororderbizdate",
                    WEB3DateUtility.getDate("yyyyMMdd", "20070829"));

            TestDBUtility.deleteAll(SubAccountRow.TYPE);
            SubAccountParams l_subAccountParams = TestDBUtility.getSubAccountRow();
            TestDBUtility.insertWithDel(l_subAccountParams);

            WEB3GentradeSubAccount l_subAccount = new WEB3GentradeSubAccount(l_subAccountParams);

            l_tpCalcCondition.calcBasePoint(l_subAccount);

            assertEquals(3, l_tpCalcCondition.getMarginBasePoint());
        }
        catch (Exception l_ex)
        {
            log.error(STR_METHOD_NAME, l_ex);
            fail();
        }

        log.exiting(TEST_END + STR_METHOD_NAME);
    }
//    
//    /**
//     * calc余力計算基準日 
//     * 発注日を取得 発注日が業務日付の時 is夕場時間帯()の戻り値＝falseの場合
//     *
//     */
//    public void testCalcBasePoint_0002()
//    {
//        final String STR_METHOD_NAME = "testCalcBasePoint_0002()";
//        log.entering(TEST_START + STR_METHOD_NAME);
//
//        WEB3TPCalcCondition l_tpCalcCondition = new WEB3TPCalcConditionForTest();
//
//        try
//        {
//            TestDBUtility.deleteAll(TradingTimeRow.TYPE);
//            TradingTimeParams l_treadingTimeParams = TestDBUtility.getTradingTimeRow();
//            l_treadingTimeParams.setInstitutionCode("0");
//            l_treadingTimeParams.setBranchCode("123");
//            l_treadingTimeParams.setMarketCode("0");
//            l_treadingTimeParams.setTradingTimeType("11");
//            l_treadingTimeParams.setProductCode("0");
//            l_treadingTimeParams.setBizDateType("1");
//            l_treadingTimeParams.setBizdateCalcParameter("0");
//            l_treadingTimeParams.setSessionType("0");
//            TestDBUtility.insertWithDel(l_treadingTimeParams);
//
//            WEB3GentradeTradingClendarContext l_clendarContext = new WEB3GentradeTradingClendarContext();
//            l_clendarContext.setInstitutionCode("0");
//            l_clendarContext.setBranchCode("123");
//            l_clendarContext.setMarketCode("0");
//            l_clendarContext.setTradingTimeType("11");
//            l_clendarContext.setProductCode("0");
//            l_clendarContext.setBizDateType("1");
//
//            ThreadLocalSystemAttributesRegistry.setAttribute("web3.tradingcalendarcontext", l_clendarContext);
//            
//            ThreadLocalSystemAttributesRegistry.setAttribute(
//                    "web3.attributes.basetimestampfororderbizdate",
//                    WEB3DateUtility.getDate("yyyyMMdd", "20070829"));
//
//            TestDBUtility.deleteAll(SubAccountRow.TYPE);
//            SubAccountParams l_subAccountParams = TestDBUtility.getSubAccountRow();
//            TestDBUtility.insertWithDel(l_subAccountParams);
//
//            WEB3GentradeSubAccount l_subAccount = new WEB3GentradeSubAccount(l_subAccountParams);
//
//            l_tpCalcCondition.calcBasePoint(l_subAccount);
//
//            assertEquals(1, l_tpCalcCondition.getOptionBasePoint());
//        }
//        catch (Exception l_ex)
//        {
//            log.error(STR_METHOD_NAME, l_ex);
//            fail();
//        }
//
//        log.exiting(TEST_END + STR_METHOD_NAME);
//    }
//    
    /**
     * PTS出来終了区分 = false
     * is信用口座開設 == false
     * オリックス証券担保ローン口座開設済顧客の場合
     * 担保ローン出金拘束金テーブルの行オブジェクトを取得できた場合
     */
    public void testCreateCalcCondition_case0004()
    {
         final String STR_METHOD_NAME = "testCreateCalcCondition_case0004()";
         log.entering(TEST_START + STR_METHOD_NAME);
         
         try
         {
             TestDBUtility.deleteAll(ProcessManagementRow.TYPE);

             TestDBUtility.deleteAll(BranchRow.TYPE);
             BranchParams l_branchParams = TestDBUtility.getBranchRow();
             TestDBUtility.insertWithDel(l_branchParams);
             
             TestDBUtility.deleteAll(InstitutionRow.TYPE);
             InstitutionParams l_institutionParams = TestDBUtility.getInstitutionRow();
             TestDBUtility.insertWithDel(l_institutionParams);
             
             TestDBUtility.deleteAll(MainAccountRow.TYPE);
             MainAccountParams l_mainAccountParams = TestDBUtility.getMainAccountRow();
             l_mainAccountParams.setSecuredLoanSecAccOpenDiv("1");
             l_mainAccountParams.setAccountId(333812512203L);
             TestDBUtility.insertWithDel(l_mainAccountParams);
             
             TestDBUtility.deleteAll(SubAccountRow.TYPE);
             TestDBUtility.insertWithDel(TestDBUtility.getSubAccountRow());
             WEB3GentradeSubAccount l_subAccount = new WEB3GentradeSubAccount(333812512203L,33381251220301L);

             TestDBUtility.deleteAll(TradingpowerCalcConditionRow.TYPE);
             TradingpowerCalcConditionParams l_tradingpowerCalcConditionParams = TestDBUtility.getTradingpowerCalcConditionRow();
             l_tradingpowerCalcConditionParams.setAccountId(333812512203L);
             l_tradingpowerCalcConditionParams.setAdditionalDepositStop("1");
             TestDBUtility.insertWithDel(l_tradingpowerCalcConditionParams);

             TestDBUtility.deleteAll(StockSecuredLoanRow.TYPE);
             StockSecuredLoanParams l_stockSecuredLoanParams = TestDBUtility.getStockSecuredLoanRow();
             l_stockSecuredLoanParams.setAccountId(333812512203L);
             l_stockSecuredLoanParams.setAccountOpenStatus("2");
             TestDBUtility.insertWithDel(l_stockSecuredLoanParams);
             
             TestDBUtility.deleteAll(SecurityCashoutRestraintRow.TYPE);
             SecurityCashoutRestraintParams l_securityCashoutRestraintParams = 
                 TestDBUtility.getSecurityCashoutRestraintRow();
             l_securityCashoutRestraintParams.setAccountId(333812512203L);
             l_securityCashoutRestraintParams.setCashoutEnablieAmount(100L);
             l_securityCashoutRestraintParams.setAmountLockFlg("2");
             TestDBUtility.insertWithDel(l_securityCashoutRestraintParams);
             


             Method method = WEB3TPCalcCondition.class.getDeclaredMethod(
                 "createCalcCondition", 
                 new Class[] {WEB3GentradeSubAccount.class});
             
             method.setAccessible(true);

             WEB3TPCalcCondition l_calcCondition = 
                 (WEB3TPCalcCondition)method.invoke(
                     this.l_tpCalcCondition, new Object[]{l_subAccount});

             assertTrue(l_calcCondition.isOrixSecuredLoanAccOpenDiv());
             assertEquals("100",l_calcCondition.getOrixSecuredLoanPaymentTradingPower());
             assertEquals("2",l_calcCondition.getOrixSecuredLoanLockFlag());
             assertFalse(l_calcCondition.isPtsOrderExecutionEndType());
         }
         catch(Exception l_ex)
         {
             log.error(STR_METHOD_NAME,l_ex);
             fail();
         }
         
         log.exiting(TEST_END + STR_METHOD_NAME);
    }
    
    /**
     * PTS出来終了区分 = true
     * is信用口座開設 == false
     * オリックス証券担保ローン口座開設済顧客の場合
     * 担保ローン出金拘束金テーブルの行オブジェクトを取得できた場合
     */
    public void testCreateCalcCondition_case0014()
    {
         final String STR_METHOD_NAME = "testCreateCalcCondition_case0014()";
         log.entering(TEST_START + STR_METHOD_NAME);
         
         try
         {
             TestDBUtility.deleteAll(BranchRow.TYPE);
             BranchParams l_branchParams = TestDBUtility.getBranchRow();
             TestDBUtility.insertWithDel(l_branchParams);
             
             TestDBUtility.deleteAll(InstitutionRow.TYPE);
             InstitutionParams l_institutionParams = TestDBUtility.getInstitutionRow();
             TestDBUtility.insertWithDel(l_institutionParams);
             
             TestDBUtility.deleteAll(MainAccountRow.TYPE);
             MainAccountParams l_mainAccountParams = TestDBUtility.getMainAccountRow();
             l_mainAccountParams.setSecuredLoanSecAccOpenDiv("1");
             l_mainAccountParams.setAccountId(333812512203L);
             TestDBUtility.insertWithDel(l_mainAccountParams);
             
             TestDBUtility.deleteAll(SubAccountRow.TYPE);
             TestDBUtility.insertWithDel(TestDBUtility.getSubAccountRow());
             WEB3GentradeSubAccount l_subAccount = new WEB3GentradeSubAccount(333812512203L,33381251220301L);

             TestDBUtility.deleteAll(TradingpowerCalcConditionRow.TYPE);
             TradingpowerCalcConditionParams l_tradingpowerCalcConditionParams = TestDBUtility.getTradingpowerCalcConditionRow();
             l_tradingpowerCalcConditionParams.setAccountId(333812512203L);
             l_tradingpowerCalcConditionParams.setAdditionalDepositStop("1");
             TestDBUtility.insertWithDel(l_tradingpowerCalcConditionParams);

             TestDBUtility.deleteAll(StockSecuredLoanRow.TYPE);
             StockSecuredLoanParams l_stockSecuredLoanParams = TestDBUtility.getStockSecuredLoanRow();
             l_stockSecuredLoanParams.setAccountId(333812512203L);
             l_stockSecuredLoanParams.setAccountOpenStatus("2");
             TestDBUtility.insertWithDel(l_stockSecuredLoanParams);
             
             TestDBUtility.deleteAll(SecurityCashoutRestraintRow.TYPE);
             SecurityCashoutRestraintParams l_securityCashoutRestraintParams = 
                 TestDBUtility.getSecurityCashoutRestraintRow();
             l_securityCashoutRestraintParams.setAccountId(333812512203L);
             l_securityCashoutRestraintParams.setCashoutEnablieAmount(100L);
             l_securityCashoutRestraintParams.setAmountLockFlg("2");
             TestDBUtility.insertWithDel(l_securityCashoutRestraintParams);
             
             ProcessManagementParams l_processManagementParams = new ProcessManagementParams();
             l_processManagementParams.setProcessId("0010");
             l_processManagementParams.setStatus("1");
             l_processManagementParams.setLastUpdater("123");
             l_processManagementParams.setLastUpdatedTimestamp(Calendar.getInstance().getTime());
             l_processManagementParams.setInstitutionCode("0D");
             l_processManagementParams.setBranchCode("381");
             TestDBUtility.insertWithDel(l_processManagementParams);

             Method method = WEB3TPCalcCondition.class.getDeclaredMethod(
                 "createCalcCondition", 
                 new Class[] {WEB3GentradeSubAccount.class});
             
             method.setAccessible(true);

             WEB3TPCalcCondition l_calcCondition = 
                 (WEB3TPCalcCondition)method.invoke(
                     this.l_tpCalcCondition, new Object[]{l_subAccount});

             assertTrue(l_calcCondition.isOrixSecuredLoanAccOpenDiv());
             assertEquals("100",l_calcCondition.getOrixSecuredLoanPaymentTradingPower());
             assertEquals("2",l_calcCondition.getOrixSecuredLoanLockFlag());
             assertTrue(l_calcCondition.isPtsOrderExecutionEndType());
         }
         catch(Exception l_ex)
         {
             log.error(STR_METHOD_NAME,l_ex);
             fail();
         }
         
         log.exiting(TEST_END + STR_METHOD_NAME);
    }
//    
//    /**
//     * is信用口座開設 == false
//     * オリックス証券担保ローン口座開設済顧客の場合
//     * 担保ローン出金拘束金テーブルの行オブジェクトを取得でない場合
//     */
//    public void testCreateCalcCondition_case0005()
//    {
//         final String STR_METHOD_NAME = "testCreateCalcCondition_case0005()";
//         log.entering(TEST_START + STR_METHOD_NAME);
//         
//         try
//         {
//             TestDBUtility.deleteAll(BranchRow.TYPE);
//             BranchParams l_branchParams = TestDBUtility.getBranchRow();
//             TestDBUtility.insertWithDel(l_branchParams);
//             
//             TestDBUtility.deleteAll(InstitutionRow.TYPE);
//             InstitutionParams l_institutionParams = TestDBUtility.getInstitutionRow();
//             TestDBUtility.insertWithDel(l_institutionParams);
//             
//             TestDBUtility.deleteAll(MainAccountRow.TYPE);
//             MainAccountParams l_mainAccountParams = TestDBUtility.getMainAccountRow();
//             l_mainAccountParams.setSecuredLoanSecAccOpenDiv("1");
//             l_mainAccountParams.setAccountId(333812512203L);
//             TestDBUtility.insertWithDel(l_mainAccountParams);
//             
//             TestDBUtility.deleteAll(SubAccountRow.TYPE);
//             TestDBUtility.insertWithDel(TestDBUtility.getSubAccountRow());
//             WEB3GentradeSubAccount l_subAccount = new WEB3GentradeSubAccount(333812512203L,33381251220301L);
//             
//             TestDBUtility.deleteAll(TradingpowerCalcConditionRow.TYPE);
//             TradingpowerCalcConditionParams l_tradingpowerCalcConditionParams = TestDBUtility.getTradingpowerCalcConditionRow();
//             l_tradingpowerCalcConditionParams.setAccountId(333812512203L);
//             l_tradingpowerCalcConditionParams.setAdditionalDepositStop("1");
//             TestDBUtility.insertWithDel(l_tradingpowerCalcConditionParams);
//
//             TestDBUtility.deleteAll(StockSecuredLoanRow.TYPE);
//             StockSecuredLoanParams l_stockSecuredLoanParams = TestDBUtility.getStockSecuredLoanRow();
//             l_stockSecuredLoanParams.setAccountId(333812512203L);
//             l_stockSecuredLoanParams.setAccountOpenStatus("2");
//             TestDBUtility.insertWithDel(l_stockSecuredLoanParams);
//             
//             TestDBUtility.deleteAll(SecurityCashoutRestraintRow.TYPE);
//
//             Method method = WEB3TPCalcCondition.class.getDeclaredMethod(
//                 "createCalcCondition", 
//                 new Class[] {WEB3GentradeSubAccount.class});
//             
//             method.setAccessible(true);
//
//             WEB3TPCalcCondition l_calcCondition = 
//                 (WEB3TPCalcCondition)method.invoke(
//                     this.l_tpCalcCondition, new Object[]{l_subAccount});
//             
////             assertFalse(l_calcCondition.isOrixSecuredLoanAccOpenDiv());
////             assertNull(l_calcCondition.getOrixSecuredLoanPaymentTradingPower());
////             assertNull(l_calcCondition.getOrixSecuredLoanLockFlag());
//         }
//         catch(Exception l_ex)
//         {
//             log.error(STR_METHOD_NAME,l_ex);
//             fail();
//         }
//         
//         log.exiting(TEST_END + STR_METHOD_NAME);
//    }
//    
//    /**
//     * is信用口座開設 == false
//     * オリックス証券担保ローン口座未開設顧客の場合
//     */
//    public void testCreateCalcCondition_case0006()
//    {
//         final String STR_METHOD_NAME = "testCreateCalcCondition_case0006()";
//         log.entering(TEST_START + STR_METHOD_NAME);
//         
//         try
//         {
//             TestDBUtility.deleteAll(BranchRow.TYPE);
//             BranchParams l_branchParams = TestDBUtility.getBranchRow();
//             TestDBUtility.insertWithDel(l_branchParams);
//             
//             TestDBUtility.deleteAll(InstitutionRow.TYPE);
//             InstitutionParams l_institutionParams = TestDBUtility.getInstitutionRow();
//             TestDBUtility.insertWithDel(l_institutionParams);
//             
//             TestDBUtility.deleteAll(MainAccountRow.TYPE);
//             MainAccountParams l_mainAccountParams = TestDBUtility.getMainAccountRow();
//             l_mainAccountParams.setAccountId(333812512203L);
//             TestDBUtility.insertWithDel(l_mainAccountParams);
//             
//             TestDBUtility.deleteAll(SubAccountRow.TYPE);
//             TestDBUtility.insertWithDel(TestDBUtility.getSubAccountRow());
//             WEB3GentradeSubAccount l_subAccount = new WEB3GentradeSubAccount(333812512203L,33381251220301L);
//             
//             TestDBUtility.deleteAll(TradingpowerCalcConditionRow.TYPE);
//             TradingpowerCalcConditionParams l_tradingpowerCalcConditionParams = TestDBUtility.getTradingpowerCalcConditionRow();
//             l_tradingpowerCalcConditionParams.setAccountId(333812512203L);
//             l_tradingpowerCalcConditionParams.setAdditionalDepositStop("1");
//             TestDBUtility.insertWithDel(l_tradingpowerCalcConditionParams);
//
//             TestDBUtility.deleteAll(StockSecuredLoanRow.TYPE);
//             StockSecuredLoanParams l_stockSecuredLoanParams = TestDBUtility.getStockSecuredLoanRow();
//             l_stockSecuredLoanParams.setAccountId(333812512203L);
//             l_stockSecuredLoanParams.setAccountOpenStatus("1");
//             TestDBUtility.insertWithDel(l_stockSecuredLoanParams);
//
//             Method method = WEB3TPCalcCondition.class.getDeclaredMethod(
//                 "createCalcCondition", 
//                 new Class[] {WEB3GentradeSubAccount.class});
//             
//             method.setAccessible(true);
//
//             WEB3TPCalcCondition l_calcCondition = 
//                 (WEB3TPCalcCondition)method.invoke(
//                     this.l_tpCalcCondition, new Object[]{l_subAccount});
//             
////             assertFalse(l_calcCondition.isOrixSecuredLoanAccOpenDiv());
////             assertNull(l_calcCondition.getOrixSecuredLoanPaymentTradingPower());
////             assertNull(l_calcCondition.getOrixSecuredLoanLockFlag());
//         }
//         catch(Exception l_ex)
//         {
//             log.error(STR_METHOD_NAME,l_ex);
//             fail();
//         }
//         
//         log.exiting(TEST_END + STR_METHOD_NAME);
//    }
//
//    /**
//     * calc営業日
//     * 営業日[T-2..+6]を算出し、自身のプロパティにセットする
//     */
//    public void testCalcBizDateCase001()
//    {
//        final String STR_METHOD_NAME = "testCalcBizDateCase001()";
//        log.entering(TEST_START + STR_METHOD_NAME);
//        
//        try
//        {
//            TestBaseForMock.MOCK_MANAGER.setIsMockUsed(true);
//            Date l_datBizDate = WEB3DateUtility.getDate("20071017", "yyyyMMdd");
//            
//            TestBaseForMock.MOCK_MANAGER.setMethodReturnValue(
//                "com.fitechlabs.xtrade.plugin.tc.gentrade.stdimpls.TradingSystemImpl",
//                "getSystemTimestamp",
//                new Class[] {},
//                new Timestamp(l_datBizDate.getTime()));
//
//            WEB3TPCalcCondition l_condition = new WEB3TPCalcCondition();
//            
//            l_condition.calcBizDate();
//            
//            Date[] l_dats = l_condition.getBizDate();
//            
//            assertEquals(WEB3DateUtility.formatDate(l_dats[0], "yyyyMMdd"), "20071016");
//            assertEquals(WEB3DateUtility.formatDate(l_dats[1], "yyyyMMdd"), "20071017");
//            assertEquals(WEB3DateUtility.formatDate(l_dats[2], "yyyyMMdd"), "20071018");
//            assertEquals(WEB3DateUtility.formatDate(l_dats[3], "yyyyMMdd"), "20071019");
//            assertEquals(WEB3DateUtility.formatDate(l_dats[4], "yyyyMMdd"), "20071022");
//            assertEquals(WEB3DateUtility.formatDate(l_dats[5], "yyyyMMdd"), "20071023");
//            assertEquals(WEB3DateUtility.formatDate(l_dats[6], "yyyyMMdd"), "20071024");
//            assertEquals(WEB3DateUtility.formatDate(l_dats[7], "yyyyMMdd"), "20071025");
//        }
//        catch(Exception l_ex)
//        {
//            log.error(STR_METHOD_NAME,l_ex);
//            fail();
//        }
//        log.exiting(TEST_END + STR_METHOD_NAME);
//        
//    }

    private class WEB3TPCalcConditionForTest extends WEB3TPCalcCondition
    {
        public Date getBizDate(int l_intSpecifiedPoint)
        {
            if (l_intSpecifiedPoint == 0)
            {
                return WEB3DateUtility.getDate("20070829", "yyyyMMdd");
            }
            else
            {
                return WEB3DateUtility.getDate("20070827", "yyyyMMdd");
            }
        }
    }
    
    private class WEB3TPCalcConditionForTest1 extends WEB3TPCalcCondition
    {
        public Date getBizDate(int l_intSpecifiedPoint)
        {
            if (l_intSpecifiedPoint == 0)
            {
                return WEB3DateUtility.getDate("20070314", "yyyyMMdd");
            }
            else
            {
                return WEB3DateUtility.getDate("20070827", "yyyyMMdd");
            }
        }
    }
    
    private class WEB3TPCalcConditionForTest2 extends WEB3TPCalcCondition
    {
        public Date getBizDate(int l_intSpecifiedPoint)
        {
            if (l_intSpecifiedPoint == 0)
            {
                return WEB3DateUtility.getDate("20070316", "yyyyMMdd");
            }
            else
            {
                return WEB3DateUtility.getDate("20070314", "yyyyMMdd");
            }
        }
    }
    
    //【T0801211TP】【余力】内藤証券　@外株終値評価項目追加　@余力の対応
    //１外株終値Rowを取得できない場合
    //　@2外株終値Rowを取得の場合 取得1條紀? 
    public void testGetFeqLastClosingPrice1()
    {
        final String STR_METHOD_NAME = "testGetFeqLastClosingPrice1()";
        log.entering(TEST_START + STR_METHOD_NAME);
        
        try
        {
            TestDBUtility.deleteAll(FeqLastClosingPriceParams.TYPE);
            FeqLastClosingPriceParams l_feqLastClosingPriceParams = new FeqLastClosingPriceParams();
            l_feqLastClosingPriceParams.setProductId(3304148080000L);
            l_feqLastClosingPriceParams.setBaseDate(WEB3DateUtility.getDate("20071017", "yyyyMMdd"));
            l_feqLastClosingPriceParams.setOffshoreProductCode("01");
            l_feqLastClosingPriceParams.setFeqClosingPrice(11);
            l_feqLastClosingPriceParams.setFeqClosingPriceMarketCode("1");
            l_feqLastClosingPriceParams.setFeqClosingPriceMrktCodeS("1");
            TestDBUtility.insertWithDel(l_feqLastClosingPriceParams);
            
            WEB3TPCalcCondition l_condition = new WEB3TPCalcCondition();
            double l_dblFeqLastClosingPric = l_condition.getFeqLastClosingPrice(3304148080000L);
            assertEquals(l_dblFeqLastClosingPric + "","11.0");
        }
        catch(Exception l_ex)
        {
            log.error(STR_METHOD_NAME,l_ex);
            fail();
        }
        
        log.exiting(TEST_END + STR_METHOD_NAME);
    }
    
    //１外株終値Rowを取得できない場合
    //　@2外株終値Rowを取得の場合 取得3條紀? 
    //降序排列
    public void testGetFeqLastClosingPrice2()
    {
        final String STR_METHOD_NAME = "testGetFeqLastClosingPrice2()";
        log.entering(TEST_START + STR_METHOD_NAME);
        
        try
        {
            TestDBUtility.deleteAll(FeqLastClosingPriceParams.TYPE);
            FeqLastClosingPriceParams l_feqLastClosingPriceParams1 = new FeqLastClosingPriceParams();
            l_feqLastClosingPriceParams1.setProductId(3304148080000L);
            l_feqLastClosingPriceParams1.setBaseDate(WEB3DateUtility.getDate("20071017", "yyyyMMdd"));
            l_feqLastClosingPriceParams1.setOffshoreProductCode("01");
            l_feqLastClosingPriceParams1.setFeqClosingPrice(22);
            l_feqLastClosingPriceParams1.setFeqClosingPriceMarketCode("1");
            l_feqLastClosingPriceParams1.setFeqClosingPriceMrktCodeS("1");
            TestDBUtility.insertWithDel(l_feqLastClosingPriceParams1);
            FeqLastClosingPriceParams l_feqLastClosingPriceParams2 = new FeqLastClosingPriceParams();
            l_feqLastClosingPriceParams2.setProductId(3304148080000L);
            l_feqLastClosingPriceParams2.setBaseDate(WEB3DateUtility.getDate("20071015", "yyyyMMdd"));
            l_feqLastClosingPriceParams2.setOffshoreProductCode("01");
            l_feqLastClosingPriceParams2.setFeqClosingPrice(11);
            l_feqLastClosingPriceParams2.setFeqClosingPriceMarketCode("1");
            l_feqLastClosingPriceParams2.setFeqClosingPriceMrktCodeS("1");
            TestDBUtility.insertWithDel(l_feqLastClosingPriceParams2);
            FeqLastClosingPriceParams l_feqLastClosingPriceParams3 = new FeqLastClosingPriceParams();
            l_feqLastClosingPriceParams3.setProductId(3304148080000L);
            l_feqLastClosingPriceParams3.setBaseDate(WEB3DateUtility.getDate("20071018", "yyyyMMdd"));
            l_feqLastClosingPriceParams3.setOffshoreProductCode("01");
            l_feqLastClosingPriceParams3.setFeqClosingPrice(33);
            l_feqLastClosingPriceParams3.setFeqClosingPriceMarketCode("1");
            l_feqLastClosingPriceParams3.setFeqClosingPriceMrktCodeS("1");
            TestDBUtility.insertWithDel(l_feqLastClosingPriceParams3);
            
            WEB3TPCalcCondition l_condition = new WEB3TPCalcCondition();
            l_condition.addFeqLastClosingPriceRow(3304148080001L, l_feqLastClosingPriceParams1);
            double l_dblFeqLastClosingPric = l_condition.getFeqLastClosingPrice(3304148080000L);
            assertEquals(l_dblFeqLastClosingPric + "","33.0");
        }
        catch(Exception l_ex)
        {
            log.error(STR_METHOD_NAME,l_ex);
            fail();
        }
        
        log.exiting(TEST_END + STR_METHOD_NAME);
    }
    
    //２外株終値Rowを取得できない場合
    public void testGetFeqLastClosingPrice3()
    {
        final String STR_METHOD_NAME = "testGetFeqLastClosingPrice3()";
        log.entering(TEST_START + STR_METHOD_NAME);
        
        try
        {
            TestDBUtility.deleteAll(FeqLastClosingPriceParams.TYPE);
            FeqLastClosingPriceParams l_feqLastClosingPriceParams1 = new FeqLastClosingPriceParams();
            l_feqLastClosingPriceParams1.setProductId(3304148080001L);
            l_feqLastClosingPriceParams1.setBaseDate(WEB3DateUtility.getDate("20071017", "yyyyMMdd"));
            l_feqLastClosingPriceParams1.setOffshoreProductCode("01");
            l_feqLastClosingPriceParams1.setFeqClosingPrice(22);
            l_feqLastClosingPriceParams1.setFeqClosingPriceMarketCode("1");
            l_feqLastClosingPriceParams1.setFeqClosingPriceMrktCodeS("1");
            TestDBUtility.insertWithDel(l_feqLastClosingPriceParams1);
            
            WEB3TPCalcCondition l_condition = new WEB3TPCalcCondition();
            l_condition.addFeqLastClosingPriceRow(3304148080002L, l_feqLastClosingPriceParams1);
            double l_dblFeqLastClosingPric = l_condition.getFeqLastClosingPrice(3304148080000L);
            assertEquals(l_dblFeqLastClosingPric + "","0.0");
        }
        catch(Exception l_ex)
        {
            log.error(STR_METHOD_NAME,l_ex);
            fail();
        }
        
        log.exiting(TEST_END + STR_METHOD_NAME);
    }

    //１外株終値Rowを取得の場合
    public void testGetFeqLastClosingPrice4()
    {
        final String STR_METHOD_NAME = "testGetFeqLastClosingPrice4()";
        log.entering(TEST_START + STR_METHOD_NAME);
        
        try
        {
            FeqLastClosingPriceParams l_feqLastClosingPriceParams1 = new FeqLastClosingPriceParams();
            l_feqLastClosingPriceParams1.setProductId(3304148080001L);
            l_feqLastClosingPriceParams1.setBaseDate(WEB3DateUtility.getDate("20071017", "yyyyMMdd"));
            l_feqLastClosingPriceParams1.setOffshoreProductCode("01");
            l_feqLastClosingPriceParams1.setFeqClosingPrice(22);
            l_feqLastClosingPriceParams1.setFeqClosingPriceMarketCode("1");
            l_feqLastClosingPriceParams1.setFeqClosingPriceMrktCodeS("1");
            
            WEB3TPCalcCondition l_condition = new WEB3TPCalcCondition();
            l_condition.addFeqLastClosingPriceRow(33041480800001L, l_feqLastClosingPriceParams1);
            
            double l_dblFeqLastClosingPrice = l_condition.getFeqLastClosingPrice(33041480800001L);
            assertEquals(l_dblFeqLastClosingPrice + "","22.0");
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
