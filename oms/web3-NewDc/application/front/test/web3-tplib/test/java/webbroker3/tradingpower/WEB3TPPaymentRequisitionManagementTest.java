head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.04.07.00.49.47;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	5444d9d078c63f4;
filename	WEB3TPPaymentRequisitionManagementTest.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
 Copyright        : (株)大和総研 証券ソリューションシステム第二部
 File Name        : (WEB3TPPaymentRequisitionManagementTest.java)
 Author Name      : Daiwa Institute of Research
 Revision History : 2007/07/28 崔遠鵬(中訊) 新規作成 仕様変更モデルNo.113
 Revision History : 2007/08/20 トウ鋒鋼　@(中訊)　@仕様変更モデルNo.167
 Revision History : 2007/08/20 トウ鋒鋼　@(中訊)　@仕様変更モデルNo.168
 */
package webbroker3.tradingpower;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import test.util.TestDBUtility;
import webbroker3.common.WEB3BaseException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.common.define.WEB3BranchPreferencesNameDef;
import webbroker3.gentrade.WEB3GentradeMainAccount;
import webbroker3.gentrade.WEB3GentradeTradingClendarContext;
import webbroker3.gentrade.WEB3GentradeTradingTimeManagement;
import webbroker3.gentrade.data.BranchPreferencesParams;
import webbroker3.gentrade.data.BranchPreferencesRow;
import webbroker3.gentrade.data.OrderexecutionEndParams;
import webbroker3.gentrade.data.OrderexecutionEndRow;
import webbroker3.gentrade.data.ProcessManagementParams;
import webbroker3.gentrade.data.ProcessManagementRow;
import webbroker3.gentrade.data.TradingpowerCalcConditionParams;
import webbroker3.gentrade.data.TradingpowerCalcConditionRow;
import webbroker3.mock.TestBaseForMock;
import webbroker3.tradingpower.data.PaymentRequisitMngParams;
import webbroker3.tradingpower.data.PaymentRequisitMngRow;
import webbroker3.tradingpower.data.TpCalcResultEquityDetailParams;
import webbroker3.tradingpower.data.TpCalcResultEquityDetailRow;
import webbroker3.tradingpower.data.TpCalcResultEquityParams;
import webbroker3.tradingpower.data.TpCalcResultEquityRow;
import webbroker3.tradingpower.data.TpCalcResultMarginDetailParams;
import webbroker3.tradingpower.data.TpCalcResultMarginDetailRow;
import webbroker3.tradingpower.data.TpCalcResultMarginParams;
import webbroker3.tradingpower.data.TpCalcResultMarginRow;
import webbroker3.tradingpower.data.TpCashBalanceParams;
import webbroker3.tradingpower.data.TpCashBalanceRow;
import webbroker3.tradingpower.define.WEB3TPMarginEquityJudgeFlagDef;
import webbroker3.tradingpower.define.WEB3TPSpecifiedPointDef;
import webbroker3.util.WEB3DateUtility;
import webbroker3.util.WEB3LogUtility;
import webbroker3.util.WEB3StringTypeUtility;

import com.fitechlabs.xtrade.kernel.data.Processors;
import com.fitechlabs.xtrade.kernel.data.QueryProcessor;
import com.fitechlabs.xtrade.kernel.util.ThreadLocalSystemAttributesRegistry;
import com.fitechlabs.xtrade.plugin.tc.gentrade.OrderStatusEnum;
import com.fitechlabs.xtrade.plugin.tc.gentrade.OrderTypeEnum;
import com.fitechlabs.xtrade.plugin.tc.gentrade.ProductTypeEnum;
import com.fitechlabs.xtrade.plugin.tc.gentrade.SubAccountTypeEnum;
import com.fitechlabs.xtrade.plugin.tc.gentrade.data.BranchParams;
import com.fitechlabs.xtrade.plugin.tc.gentrade.data.BranchRow;
import com.fitechlabs.xtrade.plugin.tc.gentrade.data.InstitutionParams;
import com.fitechlabs.xtrade.plugin.tc.gentrade.data.InstitutionRow;
import com.fitechlabs.xtrade.plugin.tc.gentrade.data.MainAccountParams;
import com.fitechlabs.xtrade.plugin.tc.gentrade.data.MainAccountRow;
import com.fitechlabs.xtrade.plugin.tc.gentrade.data.SubAccountParams;
import com.fitechlabs.xtrade.plugin.tc.gentrade.data.SubAccountRow;
import com.fitechlabs.xtrade.plugin.tc.gentrade.data.SystemPreferencesRow;
import com.fitechlabs.xtrade.plugin.tc.xbaio.data.AioOrderUnitParams;
import com.fitechlabs.xtrade.plugin.tc.xbaio.data.AioOrderUnitRow;

public class WEB3TPPaymentRequisitionManagementTest extends TestBaseForMock
{
    /**
     * ログユーティリティ<BR>
     */
    private static WEB3LogUtility log = WEB3LogUtility.getInstance(WEB3TPPaymentRequisitionManagementTest.class);

    private String l_strFlag = "";
    private WEB3TPPaymentRequisitionManagement l_management = null;
    private boolean l_blnFlag = false;
    private boolean l_bln = false;
    
    public WEB3TPPaymentRequisitionManagementTest(String arg0)
    {
        super(arg0);
    }

    protected void setUp() throws Exception
    {
        super.setUp();
        TestBaseForMock.MOCK_MANAGER.setIsMockUsed(true);
        l_management = new WEB3TPPaymentRequisitionManagement();
    }

    protected void tearDown() throws Exception
    {
        
        super.tearDown();
    }

    //引数チェックを行う。
    //nが0以上1以下でない時、0を返却する。
    public void testGetTransferFromMarginDeposit_C0001()
    {
        final String STR_METHOD_NAME = "testGetTransferFromMarginDeposit_C0001()";
        log.entering(TEST_START + STR_METHOD_NAME);

        try
        {
            int l_intSpecifiedPoint = WEB3TPSpecifiedPointDef.T_2;

            WEB3TPPaymentRequisitionManagement l_paymentRequisitionManagement =
                new WEB3TPPaymentRequisitionManagement();
            double l_dbTransferFromMarginDeposit =
                l_paymentRequisitionManagement.getTransferFromMarginDeposit(l_intSpecifiedPoint);

            assertEquals("0",WEB3StringTypeUtility.formatNumber(l_dbTransferFromMarginDeposit));     
        }
        catch (Exception l_ex)
        {
            log.debug(STR_METHOD_NAME,l_ex);
            fail();
        }
    }
    
    //（a）this.信用現物判定フラグ　@==　@0(現物顧客)の場合
    //保証金からの振替額　@=　@0を設定し、返却する。
    public void testGetTransferFromMarginDeposit_C0002()
    {
        final String STR_METHOD_NAME = "testGetTransferFromMarginDeposit_C0002()";
        log.entering(TEST_START + STR_METHOD_NAME);

        try
        {
            int l_intSpecifiedPoint = WEB3TPSpecifiedPointDef.T_1;

            String l_strMarginEquityJudgeFlag = WEB3TPMarginEquityJudgeFlagDef.EQUITY_ACCOUNT;

            WEB3TPPaymentRequisitionManagement l_paymentRequisitionManagement =
                new WEB3TPPaymentRequisitionManagement();

            l_paymentRequisitionManagement.setMarginEquityJudgeFlag(l_strMarginEquityJudgeFlag);
            double l_dbTransferFromMarginDeposit =
                l_paymentRequisitionManagement.getTransferFromMarginDeposit(l_intSpecifiedPoint);

            assertEquals("0",WEB3StringTypeUtility.formatNumber(l_dbTransferFromMarginDeposit));     
        }
        catch (Exception l_ex)
        {
            log.debug(STR_METHOD_NAME,l_ex);
            fail();
        }
    }

    //（b）this.信用現物判定フラグ　@==　@1(信用顧客)の場合
    //（a）預り金不足額(T+n)　@==　@0の場合
    //保証金からの振替額　@=　@0を設定し、返却する。
    public void testGetTransferFromMarginDeposit_C0003()
    {
        final String STR_METHOD_NAME = "testGetTransferFromMarginDeposit_C0003()";
        log.entering(TEST_START + STR_METHOD_NAME);

        try
        {
            int l_intSpecifiedPoint = WEB3TPSpecifiedPointDef.T_0;

            String l_strMarginEquityJudgeFlag = WEB3TPMarginEquityJudgeFlagDef.MARGIN_ACCOUNT;

            WEB3TPPaymentRequisitionManagementForTest4 l_paymentRequisitionManagementForTest4 =
                new WEB3TPPaymentRequisitionManagementForTest4();

            l_paymentRequisitionManagementForTest4.setMarginEquityJudgeFlag(l_strMarginEquityJudgeFlag);
            double l_dbTransferFromMarginDeposit =
                l_paymentRequisitionManagementForTest4.getTransferFromMarginDeposit(l_intSpecifiedPoint);

            assertEquals("0",WEB3StringTypeUtility.formatNumber(l_dbTransferFromMarginDeposit));     
        }
        catch (Exception l_ex)
        {
            log.debug(STR_METHOD_NAME,l_ex);
            fail();
        }
    }
    
    //（b）this.信用現物判定フラグ　@==　@1(信用顧客)の場合
    //（a）指定日(=n)　@==　@1 かつ is保証金自動振替後判定フラグ() == FALSE の場合
    //保証金からの振替額　@=　@0
    public void testGetTransferFromMarginDeposit_C0004()
    {
        final String STR_METHOD_NAME = "testGetTransferFromMarginDeposit_C0004()";
        log.entering(TEST_START + STR_METHOD_NAME);

        try
        {
            int l_intSpecifiedPoint = WEB3TPSpecifiedPointDef.T_1;

            String l_strMarginEquityJudgeFlag = WEB3TPMarginEquityJudgeFlagDef.MARGIN_ACCOUNT;

            boolean l_blnDepositAutoTransferDivFlag = false;
            l_bln = true;
            WEB3TPPaymentRequisitionManagementForTest4 l_paymentRequisitionManagementForTest4 =
                new WEB3TPPaymentRequisitionManagementForTest4();

            l_paymentRequisitionManagementForTest4.setDepositAutoTransferDivFlag(l_blnDepositAutoTransferDivFlag);
            l_paymentRequisitionManagementForTest4.setMarginEquityJudgeFlag(l_strMarginEquityJudgeFlag);
            double l_dbTransferFromMarginDeposit =
                l_paymentRequisitionManagementForTest4.getTransferFromMarginDeposit(l_intSpecifiedPoint);

            assertEquals("0",WEB3StringTypeUtility.formatNumber(l_dbTransferFromMarginDeposit));     
        }
        catch (Exception l_ex)
        {
            log.debug(STR_METHOD_NAME,l_ex);
            fail();
        }
    }

    //(b)上記(a)以外 の場合
    //保証金からの振替額　@=　@集計後振替額
    //集計後振替額　@　@　@・・・this.calc保証金からの振替額(日付指定)(T+n)
    public void testGetTransferFromMarginDeposit_C0005()
    {
        final String STR_METHOD_NAME = "testGetTransferFromMarginDeposit_C0005()";
        log.entering(TEST_START + STR_METHOD_NAME);

        try
        {
            int l_intSpecifiedPoint = WEB3TPSpecifiedPointDef.T_1;

            String l_strMarginEquityJudgeFlag = WEB3TPMarginEquityJudgeFlagDef.MARGIN_ACCOUNT;

            boolean l_blnDepositAutoTransferDivFlag = true;
            l_bln = true;
            WEB3TPPaymentRequisitionManagementForTest4 l_paymentRequisitionManagementForTest4 =
                new WEB3TPPaymentRequisitionManagementForTest4();

            l_paymentRequisitionManagementForTest4.setDepositAutoTransferDivFlag(l_blnDepositAutoTransferDivFlag);
            l_paymentRequisitionManagementForTest4.setMarginEquityJudgeFlag(l_strMarginEquityJudgeFlag);
            double l_dbTransferFromMarginDeposit =
                l_paymentRequisitionManagementForTest4.getTransferFromMarginDeposit(l_intSpecifiedPoint);

            assertEquals("1",WEB3StringTypeUtility.formatNumber(l_dbTransferFromMarginDeposit));     
        }
        catch (Exception l_ex)
        {
            log.debug(STR_METHOD_NAME,l_ex);
            fail();
        }
    }

    //１）　@引数チェックを行う。
    //nが0以上1以下でない時、0を返却する。
    public void testCalcDayTradeRestraint_C0001()
    {
        final String STR_METHOD_NAME = "testCalcDayTradeRestraint_C0001()";
        log.entering(TEST_START + STR_METHOD_NAME);
        try
        {
            int l_intSpecifiedPoint = WEB3TPSpecifiedPointDef.T_2;

            WEB3TPPaymentRequisitionManagement l_paymentRequisitionManagement =
                new WEB3TPPaymentRequisitionManagement();
            double l_dbTransferFromMarginDeposit =
                l_paymentRequisitionManagement.calcDayTradeRestraint(l_intSpecifiedPoint);

            assertEquals("0",WEB3StringTypeUtility.formatNumber(l_dbTransferFromMarginDeposit));
        }
        catch (Exception l_ex)
        {
            log.debug(STR_METHOD_NAME,l_ex);
            fail();
        }
    }

    //（a）this.信用現物判定フラグ　@==　@0(現物顧客)の場合
    //日計り拘束金(T+n)　@=　@0を設定し、返却する。
    public void testCalcDayTradeRestraint_C0002()
    {
        final String STR_METHOD_NAME = "testCalcDayTradeRestraint_C0002()";
        log.entering(TEST_START + STR_METHOD_NAME);
        try
        {
            int l_intSpecifiedPoint = WEB3TPSpecifiedPointDef.T_1;

            String l_strMarginEquityJudgeFlag = WEB3TPMarginEquityJudgeFlagDef.EQUITY_ACCOUNT;

            WEB3TPPaymentRequisitionManagement l_paymentRequisitionManagement =
                new WEB3TPPaymentRequisitionManagement();

            l_paymentRequisitionManagement.setMarginEquityJudgeFlag(l_strMarginEquityJudgeFlag);
            double l_dbTransferFromMarginDeposit =
                l_paymentRequisitionManagement.calcDayTradeRestraint(l_intSpecifiedPoint);

            assertEquals("0",WEB3StringTypeUtility.formatNumber(l_dbTransferFromMarginDeposit));  
        }
        catch (Exception l_ex)
        {
            log.debug(STR_METHOD_NAME,l_ex);
            fail();
        }
    }

    //（b）this.信用現物判定フラグ　@==　@1(信用顧客)の場合
    //（a）預り金不足額(T+n)　@==　@0の場合
    //日計り拘束金(T+n)　@=　@0を設定し、返却する。
    public void testCalcDayTradeRestraint_C0003()
    {
        final String STR_METHOD_NAME = "testCalcDayTradeRestraint_C0003()";
        log.entering(TEST_START + STR_METHOD_NAME);
        try
        {
            int l_intSpecifiedPoint = WEB3TPSpecifiedPointDef.T_0;

            String l_strMarginEquityJudgeFlag = WEB3TPMarginEquityJudgeFlagDef.MARGIN_ACCOUNT;

            WEB3TPPaymentRequisitionManagementForTest4 l_paymentRequisitionManagementForTest4 =
                new WEB3TPPaymentRequisitionManagementForTest4();

            l_paymentRequisitionManagementForTest4.setMarginEquityJudgeFlag(l_strMarginEquityJudgeFlag);
            double l_dbTransferFromMarginDeposit =
                l_paymentRequisitionManagementForTest4.calcDayTradeRestraint(l_intSpecifiedPoint);

            assertEquals("0",WEB3StringTypeUtility.formatNumber(l_dbTransferFromMarginDeposit));  
        }
        catch (Exception l_ex)
        {
            log.debug(STR_METHOD_NAME,l_ex);
            fail();
        }
    }
    
    //（b）this.信用現物判定フラグ　@==　@1(信用顧客)の場合
    //（a）指定日(=n)　@==　@0　@の場合
    //［計算式］
    //日計り拘束金(T+0)　@=　@MAX（日計り拘束金(T+0)　@－　@振替額(預⇒保)(T+0),　@特別立替金実績,　@0）
    public void testCalcDayTradeRestraint_C0004()
    {
        final String STR_METHOD_NAME = "testCalcDayTradeRestraint_C0004()";
        log.entering(TEST_START + STR_METHOD_NAME);
        try
        {
            int l_intSpecifiedPoint = WEB3TPSpecifiedPointDef.T_0;

            String l_strMarginEquityJudgeFlag = WEB3TPMarginEquityJudgeFlagDef.MARGIN_ACCOUNT;
            
            double l_dblSpecialDebitAmount = 0;

            l_bln = true;
            TestDBUtility.deleteAll(TpCalcResultMarginRow.TYPE);
            TpCalcResultMarginParams l_tpCalcResultMarginParams = TestDBUtility.getTpCalcResultMarginRow();
            l_tpCalcResultMarginParams.setDayTradeRestraint0(1);
            l_tpCalcResultMarginParams.setAccountBalance0(10);
            l_tpCalcResultMarginParams.setTodayExecutedAmount0(10);
            l_tpCalcResultMarginParams.setTodayUnexecutedAmount0(10);
            l_tpCalcResultMarginParams.setTodayExecutedAmount0(10);
            TestDBUtility.insertWithDel(l_tpCalcResultMarginParams);

            TestDBUtility.deleteAll(TpCashBalanceRow.TYPE);
            TpCashBalanceParams l_tpCashBalanceParams = TestDBUtility.getTpCashBalanceRow();
            l_tpCashBalanceParams.setCashBalance0(10);
            TestDBUtility.insertWithDel(l_tpCashBalanceParams);

            WEB3TPTradingPowerCalcMargin l_tpCalcMargin = new WEB3TPTradingPowerCalcMargin();
            WEB3TPCalcCondition l_TPCalcCondition = new WEB3TPCalcCondition();

            WEB3TPPaymentRequisitionManagementForTest4 l_paymentRequisitionManagementForTest4 =
                new WEB3TPPaymentRequisitionManagementForTest4();

            l_paymentRequisitionManagementForTest4.setTpCalcMargin(l_tpCalcMargin);
            l_tpCalcMargin.setCalcResultMargin(l_tpCalcResultMarginParams);
            l_paymentRequisitionManagementForTest4.setTpCashBalanceParams(l_tpCashBalanceParams);
            l_TPCalcCondition.setSpecialDebitAmount(l_dblSpecialDebitAmount);
            l_tpCalcMargin.setCalcCondition(l_TPCalcCondition);

            l_paymentRequisitionManagementForTest4.setMarginEquityJudgeFlag(l_strMarginEquityJudgeFlag);
            double l_dbTransferFromMarginDeposit =
                l_paymentRequisitionManagementForTest4.calcDayTradeRestraint(l_intSpecifiedPoint);

            assertEquals("1",WEB3StringTypeUtility.formatNumber(l_dbTransferFromMarginDeposit));
        }
        catch (Exception l_ex)
        {
            log.debug(STR_METHOD_NAME,l_ex);
            fail();
        }
    }

    //（b）指定日(=n)　@==　@1 かつ is保証金自動振替後判定フラグ() == TRUE の場合
    //［計算式］
    //日計り拘束金(T+1)　@=　@MAX（日計り拘束金(T+1)　@－　@振替額(預⇒保)(T+1),　@0)
    public void testCalcDayTradeRestraint_C0005()
    {
        final String STR_METHOD_NAME = "testCalcDayTradeRestraint_C0005()";
        log.entering(TEST_START + STR_METHOD_NAME);
        try
        {
            int l_intSpecifiedPoint = WEB3TPSpecifiedPointDef.T_1;
            String l_strMarginEquityJudgeFlag = WEB3TPMarginEquityJudgeFlagDef.MARGIN_ACCOUNT;
            double l_dblSpecialDebitAmount = 0;
            boolean l_blnDepositAutoTransferDivFlag = true;

            l_bln = true;
            TestDBUtility.deleteAll(TpCalcResultMarginRow.TYPE);
            TpCalcResultMarginParams l_tpCalcResultMarginParams = TestDBUtility.getTpCalcResultMarginRow();
            l_tpCalcResultMarginParams.setDayTradeRestraint0(1);
            l_tpCalcResultMarginParams.setAccountBalance0(10);
            l_tpCalcResultMarginParams.setTodayExecutedAmount0(10);
            l_tpCalcResultMarginParams.setTodayUnexecutedAmount0(10);
            l_tpCalcResultMarginParams.setTodayExecutedAmount0(10);
            TestDBUtility.insertWithDel(l_tpCalcResultMarginParams);

            TestDBUtility.deleteAll(TpCashBalanceRow.TYPE);
            TpCashBalanceParams l_tpCashBalanceParams = TestDBUtility.getTpCashBalanceRow();
            l_tpCashBalanceParams.setCashBalance0(10);
            TestDBUtility.insertWithDel(l_tpCashBalanceParams);

            WEB3TPTradingPowerCalcMargin l_tpCalcMargin = new WEB3TPTradingPowerCalcMargin();
            WEB3TPCalcCondition l_TPCalcCondition = new WEB3TPCalcCondition();

            WEB3TPPaymentRequisitionManagementForTest4 l_paymentRequisitionManagementForTest4 =
                new WEB3TPPaymentRequisitionManagementForTest4();

            l_paymentRequisitionManagementForTest4.setTpCalcMargin(l_tpCalcMargin);
            l_tpCalcMargin.setCalcResultMargin(l_tpCalcResultMarginParams);
            l_paymentRequisitionManagementForTest4.setTpCashBalanceParams(l_tpCashBalanceParams);
            l_TPCalcCondition.setSpecialDebitAmount(l_dblSpecialDebitAmount);
            l_tpCalcMargin.setCalcCondition(l_TPCalcCondition);
            l_paymentRequisitionManagementForTest4.setDepositAutoTransferDivFlag(l_blnDepositAutoTransferDivFlag);

            l_paymentRequisitionManagementForTest4.setMarginEquityJudgeFlag(l_strMarginEquityJudgeFlag);
            double l_dbTransferFromMarginDeposit =
                l_paymentRequisitionManagementForTest4.calcDayTradeRestraint(l_intSpecifiedPoint);

            assertEquals("1",WEB3StringTypeUtility.formatNumber(l_dbTransferFromMarginDeposit));
        }
        catch (Exception l_ex)
        {
            log.debug(STR_METHOD_NAME,l_ex);
            fail();
        }
    }
    //（c）指定日(=n)　@==　@1 かつ is保証金自動振替後判定フラグ() == FALSE の場合
    //［計算式］
    //日計り拘束金(T+1)　@=　@0
    public void testCalcDayTradeRestraint_C0006()
    {
        final String STR_METHOD_NAME = "testCalcDayTradeRestraint_C0006()";
        log.entering(TEST_START + STR_METHOD_NAME);
        try
        {
            int l_intSpecifiedPoint = WEB3TPSpecifiedPointDef.T_1;
            String l_strMarginEquityJudgeFlag = WEB3TPMarginEquityJudgeFlagDef.MARGIN_ACCOUNT;
            boolean l_blnDepositAutoTransferDivFlag = false;

            l_bln = true;
            WEB3TPPaymentRequisitionManagementForTest4 l_paymentRequisitionManagementForTest4 =
                new WEB3TPPaymentRequisitionManagementForTest4();

            l_paymentRequisitionManagementForTest4.setDepositAutoTransferDivFlag(l_blnDepositAutoTransferDivFlag);

            l_paymentRequisitionManagementForTest4.setMarginEquityJudgeFlag(l_strMarginEquityJudgeFlag);
            double l_dbTransferFromMarginDeposit =
                l_paymentRequisitionManagementForTest4.calcDayTradeRestraint(l_intSpecifiedPoint);

            assertEquals("0",WEB3StringTypeUtility.formatNumber(l_dbTransferFromMarginDeposit));
        }
        catch (Exception l_ex)
        {
            log.debug(STR_METHOD_NAME,l_ex);
            fail();
        }
    }
    
    //１）　@引数チェックを行う。
    //nが0以上1以下でない時、0を返却する。
    public void testCalcAdjustedAmt_C0001()
    {
        final String STR_METHOD_NAME = "testCalcAdjustedAmt_C0001()";
        log.entering(TEST_START + STR_METHOD_NAME);
        try
        {
            int l_intSpecifiedPoint = WEB3TPSpecifiedPointDef.T_2;

            WEB3TPPaymentRequisitionManagement l_paymentRequisitionManagement =
                new WEB3TPPaymentRequisitionManagement();
            
            double l_dbCalcAdjustedAmt = l_paymentRequisitionManagement.calcAdjustedAmt(l_intSpecifiedPoint);

            assertEquals("0",WEB3StringTypeUtility.formatNumber(l_dbCalcAdjustedAmt));
        }
        catch (Exception l_ex)
        {
            log.debug(STR_METHOD_NAME,l_ex);
            fail();
        }
    }

    //（a）this.信用現物判定フラグ　@==　@0(現物顧客)の場合
    //精算額(T+n)　@=　@0を設定し、返却する。
    public void testCalcAdjustedAmt_C0002()
    {
        final String STR_METHOD_NAME = "testCalcAdjustedAmt_C0002()";
        log.entering(TEST_START + STR_METHOD_NAME);
        try
        {
            int l_intSpecifiedPoint = WEB3TPSpecifiedPointDef.T_1;

            String l_strMarginEquityJudgeFlag = WEB3TPMarginEquityJudgeFlagDef.EQUITY_ACCOUNT;

            WEB3TPPaymentRequisitionManagement l_paymentRequisitionManagement =
                new WEB3TPPaymentRequisitionManagement();

            l_paymentRequisitionManagement.setMarginEquityJudgeFlag(l_strMarginEquityJudgeFlag);
            double l_dbCalcAdjustedAmt = l_paymentRequisitionManagement.calcAdjustedAmt(l_intSpecifiedPoint);

            assertEquals("0",WEB3StringTypeUtility.formatNumber(l_dbCalcAdjustedAmt));
        }
        catch (Exception l_ex)
        {
            log.debug(STR_METHOD_NAME,l_ex);
            fail();
        }
    }

    //（b）this.信用現物判定フラグ　@==　@1(信用顧客)の場合
    //３）　@預り金不足額(T+n)の判定を行う。
    //（a）預り金不足額(T+n)　@==　@0の場合
    //精算額(T+n)　@=　@0を設定し、返却する。
    public void testCalcAdjustedAmt_C0003()
    {
        final String STR_METHOD_NAME = "testCalcAdjustedAmt_C0003()";
        log.entering(TEST_START + STR_METHOD_NAME);
        try
        {
            int l_intSpecifiedPoint = WEB3TPSpecifiedPointDef.T_0;

            String l_strMarginEquityJudgeFlag = WEB3TPMarginEquityJudgeFlagDef.MARGIN_ACCOUNT;

            WEB3TPPaymentRequisitionManagementForTest4 l_paymentRequisitionManagementForTest4 =
                new WEB3TPPaymentRequisitionManagementForTest4();

            l_paymentRequisitionManagementForTest4.setMarginEquityJudgeFlag(l_strMarginEquityJudgeFlag);
            double l_dbTransferFromMarginDeposit =
                l_paymentRequisitionManagementForTest4.calcAdjustedAmt(l_intSpecifiedPoint);

            assertEquals("0",WEB3StringTypeUtility.formatNumber(l_dbTransferFromMarginDeposit));  
        }
        catch (Exception l_ex)
        {
            log.debug(STR_METHOD_NAME,l_ex);
            fail();
        }
    }

    //（b）this.信用現物判定フラグ　@==　@1(信用顧客)の場合
    //４）　@「精算額」を計算する。
    //（a）指定日(=n)　@==　@0　@の場合
    //［計算式］
    //精算額(T+0)　@=　@顧客勘定(T+0)　@－　@振替額(保⇒預)(T+0)
    public void testCalcAdjustedAmt_C0004()
    {
        final String STR_METHOD_NAME = "testCalcAdjustedAmt_C0004()";
        log.entering(TEST_START + STR_METHOD_NAME);
        try
        {
            int l_intSpecifiedPoint = WEB3TPSpecifiedPointDef.T_0;

            String l_strMarginEquityJudgeFlag = WEB3TPMarginEquityJudgeFlagDef.MARGIN_ACCOUNT;

            l_bln = true;

            WEB3TPPaymentRequisitionManagementForTest4 l_paymentRequisitionManagementForTest4 =
                new WEB3TPPaymentRequisitionManagementForTest4();

            l_paymentRequisitionManagementForTest4.setMarginEquityJudgeFlag(l_strMarginEquityJudgeFlag);
            double l_dbTransferFromMarginDeposit =
                l_paymentRequisitionManagementForTest4.calcAdjustedAmt(l_intSpecifiedPoint);

            assertEquals("-1",WEB3StringTypeUtility.formatNumber(l_dbTransferFromMarginDeposit));  
        }
        catch (Exception l_ex)
        {
            log.debug(STR_METHOD_NAME,l_ex);
            fail();
        }
    }

    //（b）this.信用現物判定フラグ　@==　@1(信用顧客)の場合
    //（b）指定日(=n)　@==　@1 かつ is保証金自動振替後判定フラグ() == TRUE の場合
    //［計算式］
    //精算額(T+1)　@=　@顧客勘定(T+1)　@－　@顧客勘定(T+0)　@－　@振替額(保⇒預)(T+1)
    public void testCalcAdjustedAmt_C0005()
    {
        final String STR_METHOD_NAME = "testCalcAdjustedAmt_C0005()";
        log.entering(TEST_START + STR_METHOD_NAME);
        try
        {
            int l_intSpecifiedPoint = WEB3TPSpecifiedPointDef.T_1;

            String l_strMarginEquityJudgeFlag = WEB3TPMarginEquityJudgeFlagDef.MARGIN_ACCOUNT;

            l_bln = true;
            boolean l_blnDepositAutoTransferDivFlag = true;

            WEB3TPPaymentRequisitionManagementForTest4 l_paymentRequisitionManagementForTest4 =
                new WEB3TPPaymentRequisitionManagementForTest4();

            l_paymentRequisitionManagementForTest4.setMarginEquityJudgeFlag(l_strMarginEquityJudgeFlag);
            l_paymentRequisitionManagementForTest4.setDepositAutoTransferDivFlag(l_blnDepositAutoTransferDivFlag);
            double l_dbTransferFromMarginDeposit =
                l_paymentRequisitionManagementForTest4.calcAdjustedAmt(l_intSpecifiedPoint);

            assertEquals("-1",WEB3StringTypeUtility.formatNumber(l_dbTransferFromMarginDeposit));  
        }
        catch (Exception l_ex)
        {
            log.debug(STR_METHOD_NAME,l_ex);
            fail();
        }
    }

    //（b）this.信用現物判定フラグ　@==　@1(信用顧客)の場合
    //（c）指定日(=n)　@==　@1 かつ is保証金自動振替後判定フラグ() == FALSE の場合
    //［計算式］
    //精算額(T+1)　@=　@0
    public void testCalcAdjustedAmt_C0006()
    {
        final String STR_METHOD_NAME = "testCalcAdjustedAmt_C0006()";
        log.entering(TEST_START + STR_METHOD_NAME);
        try
        {
            int l_intSpecifiedPoint = WEB3TPSpecifiedPointDef.T_1;

            String l_strMarginEquityJudgeFlag = WEB3TPMarginEquityJudgeFlagDef.MARGIN_ACCOUNT;

            l_bln = true;
            boolean l_blnDepositAutoTransferDivFlag = false;

            WEB3TPPaymentRequisitionManagementForTest4 l_paymentRequisitionManagementForTest4 =
                new WEB3TPPaymentRequisitionManagementForTest4();

            l_paymentRequisitionManagementForTest4.setMarginEquityJudgeFlag(l_strMarginEquityJudgeFlag);
            l_paymentRequisitionManagementForTest4.setDepositAutoTransferDivFlag(l_blnDepositAutoTransferDivFlag);
            double l_dbTransferFromMarginDeposit =
                l_paymentRequisitionManagementForTest4.calcAdjustedAmt(l_intSpecifiedPoint);

            assertEquals("0",WEB3StringTypeUtility.formatNumber(l_dbTransferFromMarginDeposit));  
        }
        catch (Exception l_ex)
        {
            log.debug(STR_METHOD_NAME,l_ex);
            fail();
        }
    }

    //１）　@引数チェックを行う。
    //nが-2以上5以下でない時、nullを返却する。
    public void testGetDate_C0001()
    {
        final String STR_METHOD_NAME = "testGetDate_C0001()";
        log.entering(TEST_START + STR_METHOD_NAME);
        try
        {
            int l_intSpecifiedPoint = -3;

            WEB3TPPaymentRequisitionManagement l_paymentRequisitionManagement =
                new WEB3TPPaymentRequisitionManagement();

            Date l_datGetDate = l_paymentRequisitionManagement.getDate(l_intSpecifiedPoint);

            assertEquals(null,l_datGetDate);  
        }
        catch (Exception l_ex)
        {
            log.debug(STR_METHOD_NAME,l_ex);
            fail();
        }
    }

    //（１）this.信用現物判定フラグ　@==　@0(現物顧客）の場合
    public void testGetDate_C0002()
    {
        final String STR_METHOD_NAME = "testGetDate_C0002()";
        log.entering(TEST_START + STR_METHOD_NAME);
        try
        {
            int l_intSpecifiedPoint = WEB3TPSpecifiedPointDef.T_0;

            String l_strMarginEquityJudgeFlag = WEB3TPMarginEquityJudgeFlagDef.EQUITY_ACCOUNT;

            WEB3TPTradingPowerCalcEquity l_tpTradingPowerCalcEquity =
                new WEB3TPTradingPowerCalcEquity();

            WEB3TPCalcCondition l_tpCalcCondition =
                new WEB3TPCalcCondition();
            Date[] l_datBizDate = new Date[9];
            for(int i = 0;i < 9;i ++)
            {
                l_datBizDate[i] = WEB3DateUtility.getDate("20080810","yyyyMMdd");
            }
            l_tpTradingPowerCalcEquity.setCalcCondition(l_tpCalcCondition);
            l_tpCalcCondition.setBizDate(l_datBizDate);
           

            WEB3TPPaymentRequisitionManagement l_paymentRequisitionManagement =
                new WEB3TPPaymentRequisitionManagement();

            l_paymentRequisitionManagement.setMarginEquityJudgeFlag(l_strMarginEquityJudgeFlag);
            l_paymentRequisitionManagement.setTpCalcEquity(l_tpTradingPowerCalcEquity);
            Date l_datGetDate = l_paymentRequisitionManagement.getDate(l_intSpecifiedPoint);

            assertEquals("20080810",WEB3DateUtility.formatDate(l_datGetDate,"yyyyMMdd"));  
        }
        catch (Exception l_ex)
        {
            log.debug(STR_METHOD_NAME,l_ex);
            fail();
        }
    }

    //（２）this.信用現物判定フラグ　@==　@1(信用顧客）の場合
    public void testGetDate_C0003()
    {
        final String STR_METHOD_NAME = "testGetDate_C0003()";
        log.entering(TEST_START + STR_METHOD_NAME);
        try
        {
            int l_intSpecifiedPoint = WEB3TPSpecifiedPointDef.T_0;

            String l_strMarginEquityJudgeFlag = WEB3TPMarginEquityJudgeFlagDef.MARGIN_ACCOUNT;

            WEB3TPTradingPowerCalcMargin l_tpCalcMargin =
                new WEB3TPTradingPowerCalcMargin();


            WEB3TPCalcCondition l_tpCalcCondition =
                new WEB3TPCalcCondition();
            Date[] l_datBizDate = new Date[9];
            for(int i = 0;i < 9;i ++)
            {
                l_datBizDate[i] = WEB3DateUtility.getDate("20080810","yyyyMMdd");
            }
            l_tpCalcMargin.setCalcCondition(l_tpCalcCondition);
            l_tpCalcCondition.setBizDate(l_datBizDate);
           

            WEB3TPPaymentRequisitionManagement l_paymentRequisitionManagement =
                new WEB3TPPaymentRequisitionManagement();

            l_paymentRequisitionManagement.setMarginEquityJudgeFlag(l_strMarginEquityJudgeFlag);
            l_paymentRequisitionManagement.setTpCalcMargin(l_tpCalcMargin);
            Date l_datGetDate = l_paymentRequisitionManagement.getDate(l_intSpecifiedPoint);

            assertEquals("20080810",WEB3DateUtility.formatDate(l_datGetDate,"yyyyMMdd"));  
        }
        catch (Exception l_ex)
        {
            log.debug(STR_METHOD_NAME,l_ex);
            fail();
        }
    }

    //（２）this.信用現物判定フラグ　@==　@1(信用顧客）の場合
    //this.資産余力情報<信用顧客>.get余力計算条件.get営業日（T+n）
    public void testGetDate_C0004()
    {
        final String STR_METHOD_NAME = "testGetDate_C0004()";
        log.entering(TEST_START + STR_METHOD_NAME);
        try
        {
            int l_intSpecifiedPoint = WEB3TPSpecifiedPointDef.T_MINUS2;

            String l_strMarginEquityJudgeFlag = WEB3TPMarginEquityJudgeFlagDef.MARGIN_ACCOUNT;

            WEB3TPTradingPowerCalcMargin l_tpCalcMargin =
                new WEB3TPTradingPowerCalcMargin();


            WEB3TPCalcCondition l_tpCalcCondition =
                new WEB3TPCalcCondition();
            Date[] l_datBizDate = new Date[9];
            for(int i = 0;i < 9;i ++)
            {
                l_datBizDate[i] = WEB3DateUtility.getDate("20080810","yyyyMMdd");
            }
            l_tpCalcMargin.setCalcCondition(l_tpCalcCondition);
            l_tpCalcCondition.setBizDate(l_datBizDate);
           

            WEB3TPPaymentRequisitionManagement l_paymentRequisitionManagement =
                new WEB3TPPaymentRequisitionManagement();

            l_paymentRequisitionManagement.setMarginEquityJudgeFlag(l_strMarginEquityJudgeFlag);
            l_paymentRequisitionManagement.setTpCalcMargin(l_tpCalcMargin);
            Date l_datGetDate = l_paymentRequisitionManagement.getDate(l_intSpecifiedPoint);

            assertEquals("20080810",WEB3DateUtility.formatDate(l_datGetDate,"yyyyMMdd"));  
        }
        catch (Exception l_ex)
        {
            log.debug(STR_METHOD_NAME,l_ex);
            fail();
        }
    }


    //（a）this.信用現物判定フラグ　@==　@0(現物顧客)の場合
    //［返却値］
    //this.calc預り金不足額(現物顧客)(T+n)
    public void testGetLackAccountBalance_C0001()
    {
        final String STR_METHOD_NAME = "testGetLackAccountBalance_C0001()";
        log.entering(TEST_START + STR_METHOD_NAME);
        try
        {
            int l_intSpecifiedPoint = WEB3TPSpecifiedPointDef.T_0;

            String l_strMarginEquityJudgeFlag = WEB3TPMarginEquityJudgeFlagDef.EQUITY_ACCOUNT;
            
            WEB3TPPaymentRequisitionManagementForTest4 l_paymentRequisitionManagementForTest4 =
                new WEB3TPPaymentRequisitionManagementForTest4();

            l_paymentRequisitionManagementForTest4.setMarginEquityJudgeFlag(l_strMarginEquityJudgeFlag);
            double l_dbGetLackAccountBalance = l_paymentRequisitionManagementForTest4.getLackAccountBalance(l_intSpecifiedPoint);

            assertEquals("1",WEB3StringTypeUtility.formatNumber(l_dbGetLackAccountBalance));
        }
        catch (Exception l_ex)
        {
            log.debug(STR_METHOD_NAME,l_ex);
            fail();
        }
    }

    //（b）this.信用現物判定フラグ　@==　@1(信用顧客)の場合
    //［返却値］
    //this.calc預り金不足額(信用顧客)(T+n)
    public void testGetLackAccountBalance_C0002()
    {
        final String STR_METHOD_NAME = "testGetLackAccountBalance_C0002()";
        log.entering(TEST_START + STR_METHOD_NAME);
        try
        {
            int l_intSpecifiedPoint = WEB3TPSpecifiedPointDef.T_0;

            String l_strMarginEquityJudgeFlag = WEB3TPMarginEquityJudgeFlagDef.MARGIN_ACCOUNT;
            
            WEB3TPPaymentRequisitionManagementForTest4 l_paymentRequisitionManagementForTest4 =
                new WEB3TPPaymentRequisitionManagementForTest4();

            l_paymentRequisitionManagementForTest4.setMarginEquityJudgeFlag(l_strMarginEquityJudgeFlag);
            double l_dbGetLackAccountBalance = l_paymentRequisitionManagementForTest4.getLackAccountBalance(l_intSpecifiedPoint);

            assertEquals("0",WEB3StringTypeUtility.formatNumber(l_dbGetLackAccountBalance));
        }
        catch (Exception l_ex)
        {
            log.debug(STR_METHOD_NAME,l_ex);
            fail();
        }
    }

    //(b)this.入金請求管理Params != null の場合
    public void testGetSpecialDebitAmount_C0001()
    {
        final String STR_METHOD_NAME = "testGetSpecialDebitAmount_C0001()";
        log.entering(TEST_START + STR_METHOD_NAME);
        try
        {
            TestDBUtility.deleteAll(PaymentRequisitMngRow.TYPE);
            PaymentRequisitMngParams l_paymentRequisitMngParams = TestDBUtility.getPaymentRequisitMngParams();
            l_paymentRequisitMngParams.setSpecialDebitAmount(10);
            TestDBUtility.insertWithDel(l_paymentRequisitMngParams);

            
            WEB3TPPaymentRequisitionManagement l_paymentRequisitionManagement =
                new WEB3TPPaymentRequisitionManagement();

            l_paymentRequisitionManagement.setPaymentRequisitMngParams(l_paymentRequisitMngParams);

            double l_dbGetSpecialDebitAmount = l_paymentRequisitionManagement.getSpecialDebitAmount();

            assertEquals("10",WEB3StringTypeUtility.formatNumber(l_dbGetSpecialDebitAmount));
        }
        catch (Exception l_ex)
        {
            log.debug(STR_METHOD_NAME,l_ex);
            fail();
        }
    }

    //(a)this.入金請求管理Params == null の場合
    //［返却値］
    //0
    public void testGetSpecialDebitAmount_C0002()
    {
        final String STR_METHOD_NAME = "testGetSpecialDebitAmount_C0002()";
        log.entering(TEST_START + STR_METHOD_NAME);
        try
        {
            WEB3TPPaymentRequisitionManagement l_paymentRequisitionManagement =
                new WEB3TPPaymentRequisitionManagement();

            double l_dbGetSpecialDebitAmount = l_paymentRequisitionManagement.getSpecialDebitAmount();

            assertEquals("0",WEB3StringTypeUtility.formatNumber(l_dbGetSpecialDebitAmount));
        }
        catch (Exception l_ex)
        {
            log.debug(STR_METHOD_NAME,l_ex);
            fail();
        }
    }

    //(a)this.入金請求管理Params == null の場合
    //［返却値］
    //0
    public void testgetDebitAmount_C0001()
    {
        final String STR_METHOD_NAME = "testgetDebitAmount_C0001()";
        log.entering(TEST_START + STR_METHOD_NAME);
        try
        {
            WEB3TPPaymentRequisitionManagement l_paymentRequisitionManagement =
                new WEB3TPPaymentRequisitionManagement();

            double l_dbGetDebitAmount = l_paymentRequisitionManagement.getDebitAmount();

            assertEquals("0",WEB3StringTypeUtility.formatNumber(l_dbGetDebitAmount));
        }
        catch (Exception l_ex)
        {
            log.debug(STR_METHOD_NAME,l_ex);
            fail();
        }
    }

    //(b)this.入金請求管理Params != null の場合
    public void testgetDebitAmount_C0002()
    {
        final String STR_METHOD_NAME = "testgetDebitAmount_C0002()";
        log.entering(TEST_START + STR_METHOD_NAME);
        try
        {
            TestDBUtility.deleteAll(PaymentRequisitMngRow.TYPE);
            PaymentRequisitMngParams l_paymentRequisitMngParams = TestDBUtility.getPaymentRequisitMngParams();
            l_paymentRequisitMngParams.setDebitAmount(10);
            TestDBUtility.insertWithDel(l_paymentRequisitMngParams);

            WEB3TPPaymentRequisitionManagement l_paymentRequisitionManagement =
                new WEB3TPPaymentRequisitionManagement();

            l_paymentRequisitionManagement.setPaymentRequisitMngParams(l_paymentRequisitMngParams);

            double l_dbGetDebitAmount = l_paymentRequisitionManagement.getDebitAmount();

            assertEquals("10",WEB3StringTypeUtility.formatNumber(l_dbGetDebitAmount));
        }
        catch (Exception l_ex)
        {
            log.debug(STR_METHOD_NAME,l_ex);
            fail();
        }
    }
    class WEB3TPPaymentRequisitionManagementForTest4 extends WEB3TPPaymentRequisitionManagement
    {
        protected double calcTransferFromMarginDeposit(int l_intSpecifiedPoint)
        {
            return 1;
        }

        protected double calcAccountBalanceShortfallMargin(int l_intSpecifiedPoint)
        {
            if(!l_bln)
            {
                return 0;
            }
            else
            {
                return 1;
            }
        }
        protected double calcAccountBalanceFromMarginDeposit(int l_intSpecifiedPoint)
        {
            return 0;
        }
        protected double calcAccountCalculate(int l_intSpecifiedPoint)
        {
            return 0;
        }
        protected double calcAccountBalanceShortfallEquity(int l_intSpecifiedPoint)
        {
            return 1;
        }
    }
    
    //this.信用現物判定フラグ　@==　@0(現物顧客)の場合 
    //第一水準追証経過日数　@=　@0を設定し、返却する。
    public void testGetFirstAdddepositPassDay_C001()
    {
        final String STR_METHOD_NAME = "testGetFirstAdddepositPassDay_C001";
        log.entering(STR_METHOD_NAME);
        
        WEB3TPPaymentRequisitionManagementForTest1 l_tpRequisitionManagement =
            new WEB3TPPaymentRequisitionManagementForTest1();
        l_tpRequisitionManagement.setMarginEquityJudgeFlag("0");
        int l_intReturn  = l_tpRequisitionManagement.getFirstAdddepositPassDay();
        assertEquals(0, l_intReturn);
        
        log.exiting(STR_METHOD_NAME);
    }
    //this.信用現物判定フラグ　@==　@1(信用顧客)の場合
    //（a）this.保証金自動振替後判定フラグ　@==　@true の場合
    //（１）第一水準追証金額　@>　@0の場合 
    //[1]第一水準追証未解消金額　@<=　@0の場合
    //第一水準追証経過日数　@=　@1 
    public void testGetFirstAdddepositPassDay_C002()
    {
        final String STR_METHOD_NAME = "testGetFirstAdddepositPassDay_C002";
        log.entering(STR_METHOD_NAME);
        
        WEB3TPPaymentRequisitionManagementForTest1 l_tpRequisitionManagement =
            new WEB3TPPaymentRequisitionManagementForTest1();
        l_tpRequisitionManagement.l_strMethodName = "testGetFirstAdddepositPassDay_C002";
        l_tpRequisitionManagement.setMarginEquityJudgeFlag("1");
        l_tpRequisitionManagement.setDepositAutoTransferDivFlag(true);
        int l_intReturn  = l_tpRequisitionManagement.getFirstAdddepositPassDay();
        assertEquals(1, l_intReturn);
        
        log.exiting(STR_METHOD_NAME);
    }
    
    //this.信用現物判定フラグ　@==　@1(信用顧客)の場合
    //（a）this.保証金自動振替後判定フラグ　@==　@true の場合
    //（１）第一水準追証金額　@>　@0の場合 
    //[2] [1]以外の場合
    //this.入金請求管理Params == null の場合は、経過日数 = 0 
    //第一水準追証経過日数　@=　@経過日数　@＋　@1
    public void testGetFirstAdddepositPassDay_C003()
    {
        final String STR_METHOD_NAME = "testGetFirstAdddepositPassDay_C003";
        log.entering(STR_METHOD_NAME);
        
        WEB3TPPaymentRequisitionManagementForTest1 l_tpRequisitionManagement =
            new WEB3TPPaymentRequisitionManagementForTest1();
        l_tpRequisitionManagement.l_strMethodName = "testGetFirstAdddepositPassDay_C003";
        l_tpRequisitionManagement.setMarginEquityJudgeFlag("1");
        l_tpRequisitionManagement.setDepositAutoTransferDivFlag(true);
        int l_intReturn  = l_tpRequisitionManagement.getFirstAdddepositPassDay();
        assertEquals(1, l_intReturn);
        
        log.exiting(STR_METHOD_NAME);
    }
    
    
    //this.信用現物判定フラグ　@==　@1(信用顧客)の場合
    //（a）this.保証金自動振替後判定フラグ　@==　@true の場合
    //　@　@（２）(1)以外の場合 
    //　@　@　@第一水準追証経過日数　@=　@0 
    public void testGetFirstAdddepositPassDay_C004()
    {
        final String STR_METHOD_NAME = "testGetFirstAdddepositPassDay_C004";
        log.entering(STR_METHOD_NAME);
        
        WEB3TPPaymentRequisitionManagementForTest1 l_tpRequisitionManagement =
            new WEB3TPPaymentRequisitionManagementForTest1();
        l_tpRequisitionManagement.l_strMethodName = "testGetFirstAdddepositPassDay_C004";
        l_tpRequisitionManagement.setMarginEquityJudgeFlag("1");
        l_tpRequisitionManagement.setDepositAutoTransferDivFlag(true);
        int l_intReturn  = l_tpRequisitionManagement.getFirstAdddepositPassDay();
        assertEquals(0, l_intReturn);
        
        log.exiting(STR_METHOD_NAME);
    }
    
    
//    this.信用現物判定フラグ　@==　@1(信用顧客)の場合
//    this.保証金自動振替後判定フラグ　@==　@false の場合 
//    　@　@（１）this.入金請求管理Params == nullの場合 
//    　@　@［設定値］ 
//    　@　@第一水準追証経過日数　@=　@0 

    public void testGetFirstAdddepositPassDay_C005()
    {
        final String STR_METHOD_NAME = "testGetFirstAdddepositPassDay_C005";
        log.entering(STR_METHOD_NAME);
        
        WEB3TPPaymentRequisitionManagementForTest1 l_tpRequisitionManagement =
            new WEB3TPPaymentRequisitionManagementForTest1();
        PaymentRequisitMngParams l_paymentRequisitMngParams = null;
        l_tpRequisitionManagement.setPaymentRequisitMngParams(l_paymentRequisitMngParams);
        l_tpRequisitionManagement.l_strMethodName = "testGetFirstAdddepositPassDay_C005";
        l_tpRequisitionManagement.setMarginEquityJudgeFlag("1");
        l_tpRequisitionManagement.setDepositAutoTransferDivFlag(false);
        
        int l_intReturn  = l_tpRequisitionManagement.getFirstAdddepositPassDay();
        assertEquals(0, l_intReturn);
        
        log.exiting(STR_METHOD_NAME);
    }
    
//    this.信用現物判定フラグ　@==　@1(信用顧客)の場合
//    this.保証金自動振替後判定フラグ　@==　@false の場合 
//    （２）this.入金請求管理Params.get第一水準追証経過日数（） == 0 の場合 
//    　@　@［設定値］ 
//    　@　@第一水準追証経過日数　@=　@0 
    public void testGetFirstAdddepositPassDay_C006()
    {
        final String STR_METHOD_NAME = "testGetFirstAdddepositPassDay_C006";
        log.entering(STR_METHOD_NAME);
        
        WEB3TPPaymentRequisitionManagementForTest1 l_tpRequisitionManagement =
            new WEB3TPPaymentRequisitionManagementForTest1();
        PaymentRequisitMngParams l_paymentRequisitMngParams = new PaymentRequisitMngParams();
        l_paymentRequisitMngParams.setFirstDepositPassDay(0);
        l_tpRequisitionManagement.setPaymentRequisitMngParams(l_paymentRequisitMngParams);
        l_tpRequisitionManagement.l_strMethodName = "testGetFirstAdddepositPassDay_C006";
        l_tpRequisitionManagement.setMarginEquityJudgeFlag("1");
        l_tpRequisitionManagement.setDepositAutoTransferDivFlag(false);
        
        int l_intReturn  = l_tpRequisitionManagement.getFirstAdddepositPassDay();
        assertEquals(0, l_intReturn);
        
        log.exiting(STR_METHOD_NAME);
    }
    
//    this.信用現物判定フラグ　@==　@1(信用顧客)の場合
//    this.保証金自動振替後判定フラグ　@==　@false の場合 
//    （３）上記（１）（２）以外の場合 
//    　@　@［設定値］ 
//    　@　@第一水準追証経過日数　@=　@経過日数　@＋　@1
    public void testGetFirstAdddepositPassDay_C007()
    {
        final String STR_METHOD_NAME = "testGetFirstAdddepositPassDay_C007";
        log.entering(STR_METHOD_NAME);
        
        WEB3TPPaymentRequisitionManagementForTest1 l_tpRequisitionManagement =
            new WEB3TPPaymentRequisitionManagementForTest1();
        PaymentRequisitMngParams l_paymentRequisitMngParams = new PaymentRequisitMngParams();
        l_paymentRequisitMngParams.setFirstDepositPassDay(3);
        l_tpRequisitionManagement.setPaymentRequisitMngParams(l_paymentRequisitMngParams);
        l_tpRequisitionManagement.l_strMethodName = "testGetFirstAdddepositPassDay_C007";
        l_tpRequisitionManagement.setMarginEquityJudgeFlag("1");
        l_tpRequisitionManagement.setDepositAutoTransferDivFlag(false);
        
        int l_intReturn  = l_tpRequisitionManagement.getFirstAdddepositPassDay();
        assertEquals(4, l_intReturn);
        
        log.exiting(STR_METHOD_NAME);
    }
    //this.信用現物判定フラグ　@==　@0(現物顧客)の場合 
    //　@［返却値］ 
    //　@ 0 
    public void testGetFirstAdddepositPassDayValid_C001()
    {
        final String STR_METHOD_NAME = "testGetFirstAdddepositPassDayValid_C001";
        log.entering(STR_METHOD_NAME);
        
        WEB3TPPaymentRequisitionManagementForTest1 l_tpRequisitionManagement =
            new WEB3TPPaymentRequisitionManagementForTest1();
        l_tpRequisitionManagement.setMarginEquityJudgeFlag("0");
        int l_intReturn = l_tpRequisitionManagement.getFirstAdddepositPassDayValid();
        
        assertEquals(0, l_intReturn);
        log.exiting(STR_METHOD_NAME);
    }
    
//    this.信用現物判定フラグ　@==　@1(信用顧客)の場合 
// 　@　@ (1)this.資産余力情報<信用顧客>.余力計算条件.get会社部店別余力計算条件("first.margin.pass.day1") == null　@の場合 
// 　@　@　@［返却値］ 
// 　@　@　@　@999 
    
    public void testGetFirstAdddepositPassDayValid_C002()
    {
        final String STR_METHOD_NAME = "testGetFirstAdddepositPassDayValid_C002";
        log.entering(STR_METHOD_NAME);
        
        WEB3TPPaymentRequisitionManagementForTest1 l_tpRequisitionManagement =
            new WEB3TPPaymentRequisitionManagementForTest1();
        l_tpRequisitionManagement.setMarginEquityJudgeFlag("1");
        WEB3TPTradingPowerCalcMargin tpCalcMargin1 = new WEB3TPTradingPowerCalcMargin();
        WEB3TPCalcCondition l_calcCondition = new WEB3TPCalcCondition();
        l_calcCondition.addInstBranCalcCondition(
            WEB3BranchPreferencesNameDef.FIRST_MARGIN_PASS_DAY1, null);
        tpCalcMargin1.setCalcCondition(l_calcCondition);
        l_tpRequisitionManagement.tpCalcMargin = tpCalcMargin1;
        l_tpRequisitionManagement.setMarginEquityJudgeFlag("1");
        int l_intReturn = l_tpRequisitionManagement.getFirstAdddepositPassDayValid();
        
        assertEquals(999, l_intReturn);
        log.exiting(STR_METHOD_NAME);
    }
    
    
//    (2)this.資産余力情報<信用顧客>.余力計算条件.get会社部店別余力計算条件("first.margin.pass.day1") != null　@の場合 
// 　@　@　@［返却値］ 
// 　@　@　@　@this.資産余力情報<信用顧客>.余力計算条件.get会社部店別余力計算条件("first.margin.pass.day1")
    public void testGetFirstAdddepositPassDayValid_C003()
    {
        final String STR_METHOD_NAME = "testGetFirstAdddepositPassDayValid_C003";
        log.entering(STR_METHOD_NAME);
        
        WEB3TPPaymentRequisitionManagementForTest1 l_tpRequisitionManagement =
            new WEB3TPPaymentRequisitionManagementForTest1();
        WEB3TPTradingPowerCalcMargin tpCalcMargin1 = new WEB3TPTradingPowerCalcMargin();
        WEB3TPCalcCondition l_calcCondition = new WEB3TPCalcCondition();
        l_calcCondition.addInstBranCalcCondition(
            WEB3BranchPreferencesNameDef.FIRST_MARGIN_PASS_DAY1, "1234");
        tpCalcMargin1.setCalcCondition(l_calcCondition);
        l_tpRequisitionManagement.tpCalcMargin = tpCalcMargin1;
        l_tpRequisitionManagement.setMarginEquityJudgeFlag("1");
        int l_intReturn = l_tpRequisitionManagement.getFirstAdddepositPassDayValid();
        
        assertEquals(1234, l_intReturn);
        log.exiting(STR_METHOD_NAME);
    }
    
//    this.信用現物判定フラグ　@==　@0(現物顧客)の場合 
//  　@　@第一水準追証発生日　@=　@NULLを設定し、返却する。
    public void testGetFirstAdddepositOccurredDate_C001()
    {
        final String STR_METHOD_NAME = "testGetFirstAdddepositOccurredDate_C001";  
        log.entering(STR_METHOD_NAME);
        
        WEB3TPPaymentRequisitionManagementForTest1 l_tpRequisitionManagement =
            new WEB3TPPaymentRequisitionManagementForTest1();
        l_tpRequisitionManagement.setMarginEquityJudgeFlag("0");
        Date l_dateReturn = l_tpRequisitionManagement.getFirstAdddepositOccurredDate();
        assertEquals(null, l_dateReturn);
        
        log.exiting(STR_METHOD_NAME);
    }   
//    信用現物判定フラグ　@==　@1(信用顧客)の場合
//    this.保証金自動振替後判定フラグ　@==　@true の場合 
//    　@　@（１）「第一水準追証経過日数　@==　@0」の場合 
//    　@　@　@［設定値］ 
//    　@　@　@第一水準追証発生日　@=　@NULL 
    public void testGetFirstAdddepositOccurredDate_C002()
    {
        final String STR_METHOD_NAME = "testGetFirstAdddepositOccurredDate_C002";  
        log.entering(STR_METHOD_NAME);
        
        WEB3TPPaymentRequisitionManagementForTest1 l_tpRequisitionManagement =
            new WEB3TPPaymentRequisitionManagementForTest1();
        l_tpRequisitionManagement.setMarginEquityJudgeFlag("1");
        l_tpRequisitionManagement.setDepositAutoTransferDivFlag(true);
        l_tpRequisitionManagement.l_strMethodName = "testGetFirstAdddepositOccurredDate_C002";
        Date l_dateReturn = l_tpRequisitionManagement.getFirstAdddepositOccurredDate();
        assertEquals(null, l_dateReturn);
        
        log.exiting(STR_METHOD_NAME);
    }
    
//    信用現物判定フラグ　@==　@1(信用顧客)の場合
//    this.保証金自動振替後判定フラグ　@==　@true の場合 
//    　@（２）「第一水準追証経過日数　@==　@1」の場合 
//    　@　@　@［設定値］ 
//    　@　@　@第一水準追証発生日　@=　@期日(T+0)  
    public void testGetFirstAdddepositOccurredDate_C003()
    {
        final String STR_METHOD_NAME = "testGetFirstAdddepositOccurredDate_C003";  
        log.entering(STR_METHOD_NAME);
        
        WEB3TPPaymentRequisitionManagementForTest1 l_tpRequisitionManagement =
            new WEB3TPPaymentRequisitionManagementForTest1();
        l_tpRequisitionManagement.setMarginEquityJudgeFlag("1");
        l_tpRequisitionManagement.setDepositAutoTransferDivFlag(true);
        l_tpRequisitionManagement.l_strMethodName = "testGetFirstAdddepositOccurredDate_C003";
        Date l_dateReturn = l_tpRequisitionManagement.getFirstAdddepositOccurredDate();
        assertEquals(WEB3DateUtility.getDate("20040716", "yyyyMMdd"), l_dateReturn);
        
        log.exiting(STR_METHOD_NAME);
    }
    
//    信用現物判定フラグ　@==　@1(信用顧客)の場合
//    this.保証金自動振替後判定フラグ　@==　@true の場合 
//    　@　@（３）(1)、(2)以外の場合
//    this.入金請求管理Params == null の場合は、発生日 = null とする 
//    　@　@　@［設定値］ 
//    　@　@　@第一水準追証発生日　@=　@発生日 
    
    public void testGetFirstAdddepositOccurredDate_C004()
    {
        final String STR_METHOD_NAME = "testGetFirstAdddepositOccurredDate_C004";  
        log.entering(STR_METHOD_NAME);
        
        WEB3TPPaymentRequisitionManagementForTest1 l_tpRequisitionManagement =
            new WEB3TPPaymentRequisitionManagementForTest1();
        l_tpRequisitionManagement.setMarginEquityJudgeFlag("1");
        l_tpRequisitionManagement.setDepositAutoTransferDivFlag(true);
        l_tpRequisitionManagement.l_strMethodName = "testGetFirstAdddepositOccurredDate_C004";
        Date l_dateReturn = l_tpRequisitionManagement.getFirstAdddepositOccurredDate();
        assertEquals(null, l_dateReturn);
        
        log.exiting(STR_METHOD_NAME);
    }
    
//    信用現物判定フラグ　@==　@1(信用顧客)の場合
//   　@（b）(a)以外の場合
//   入金請求管理Params != null の場合 
//   　@　@［設定値］ 
//   　@　@第一水準追証発生日　@=　@発生日 
    
    public void testGetFirstAdddepositOccurredDate_C005()
    {
        final String STR_METHOD_NAME = "testGetFirstAdddepositOccurredDate_C005";  
        log.entering(STR_METHOD_NAME);
        
        WEB3TPPaymentRequisitionManagementForTest1 l_tpRequisitionManagement =
            new WEB3TPPaymentRequisitionManagementForTest1();
        l_tpRequisitionManagement.setMarginEquityJudgeFlag("1");
        l_tpRequisitionManagement.setDepositAutoTransferDivFlag(false);
        
        PaymentRequisitMngParams l_paymentRequisitMngParams = new PaymentRequisitMngParams();
        l_paymentRequisitMngParams.
            setFirstDepositOccurredDate(WEB3DateUtility.getDate("20040720", "yyyyMMdd"));
        l_tpRequisitionManagement.paymentRequisitMngParams = l_paymentRequisitMngParams;
        l_tpRequisitionManagement.l_strMethodName = "testGetFirstAdddepositOccurredDate_C004";
        Date l_dateReturn = l_tpRequisitionManagement.getFirstAdddepositOccurredDate();
        assertEquals(WEB3DateUtility.getDate("20040720", "yyyyMMdd"), l_dateReturn);
        
        log.exiting(STR_METHOD_NAME);
    }
    
//    this.信用現物判定フラグ　@==　@0(現物顧客)の場合 
//  　@　@第一水準追証保証金率　@=　@0を設定し、返却する。
    public void testGetFirstAdddepositMarginDepositRate_C001()
    {
        final String STR_METHOD_NAME = "testGetFirstAdddepositMarginDepositRate_C001";
        log.entering(STR_METHOD_NAME);
        
        WEB3TPPaymentRequisitionManagementForTest1 l_tpRequisitionManagement =
            new WEB3TPPaymentRequisitionManagementForTest1();
        l_tpRequisitionManagement.setMarginEquityJudgeFlag("0");
        double l_dblReturn = l_tpRequisitionManagement.getFirstAdddepositMarginDepositRate();
        assertEquals(0, l_dblReturn, 0);
        log.exiting(STR_METHOD_NAME);
    }
    
    
//    信用現物判定フラグ　@==　@1(信用顧客)の場合
//    保証金自動振替後判定フラグ　@==　@true の場合 
//    　@　@［返却値］ 
//    　@　@this.calc保証金率（） 
    public void testGetFirstAdddepositMarginDepositRate_C002()
    {
        final String STR_METHOD_NAME = "testGetFirstAdddepositMarginDepositRate_C002";
        log.entering(STR_METHOD_NAME);
        
        WEB3TPPaymentRequisitionManagementForTest1 l_tpRequisitionManagement =
            new WEB3TPPaymentRequisitionManagementForTest1();
        l_tpRequisitionManagement.setMarginEquityJudgeFlag("1");
        l_tpRequisitionManagement.setDepositAutoTransferDivFlag(true);
        double l_dblReturn = l_tpRequisitionManagement.getFirstAdddepositMarginDepositRate();
        assertEquals(100.5, l_dblReturn, 0);
        log.exiting(STR_METHOD_NAME);
    }

//    信用現物判定フラグ　@==　@1(信用顧客)の場合
//    this.保証金自動振替後判定フラグ　@==　@false かつ this.入金請求管理Params == NULL の場合 
//    　@　@［返却値］ 
//    　@　@0 
    public void testGetFirstAdddepositMarginDepositRate_C003()
    {
        final String STR_METHOD_NAME = "testGetFirstAdddepositMarginDepositRate_C003";
        log.entering(STR_METHOD_NAME);
        
        WEB3TPPaymentRequisitionManagementForTest1 l_tpRequisitionManagement =
            new WEB3TPPaymentRequisitionManagementForTest1();
        l_tpRequisitionManagement.setMarginEquityJudgeFlag("1");
        l_tpRequisitionManagement.setDepositAutoTransferDivFlag(false);
        double l_dblReturn = l_tpRequisitionManagement.getFirstAdddepositMarginDepositRate();
        assertEquals(0, l_dblReturn, 0);
        log.exiting(STR_METHOD_NAME);
    }
    
//    信用現物判定フラグ　@==　@1(信用顧客)の場合
//    this.保証金自動振替後判定フラグ　@==　@false かつ this.入金請求管理Params != NULL の場合 
//    　@　@［返却値］ 
//    　@　@this.入金請求管理Params.get保証金預託率()
    public void testGetFirstAdddepositMarginDepositRate_C004()
    {
        final String STR_METHOD_NAME = "testGetFirstAdddepositMarginDepositRate_C004";
        log.entering(STR_METHOD_NAME);
        
        WEB3TPPaymentRequisitionManagementForTest1 l_tpRequisitionManagement =
            new WEB3TPPaymentRequisitionManagementForTest1();
        l_tpRequisitionManagement.setMarginEquityJudgeFlag("1");
        l_tpRequisitionManagement.setDepositAutoTransferDivFlag(false);
        PaymentRequisitMngParams l_paymentRequisitMngParams = new PaymentRequisitMngParams();
        l_paymentRequisitMngParams.setMarginDepositRate(20.63);
        l_tpRequisitionManagement.paymentRequisitMngParams = l_paymentRequisitMngParams;
        double l_dblReturn = l_tpRequisitionManagement.getFirstAdddepositMarginDepositRate();
        assertEquals(20.63, l_dblReturn, 0);
        log.exiting(STR_METHOD_NAME);
    }

//    this.信用現物判定フラグ　@==　@0(現物顧客)の場合 
//  　@　@［返却値］ 
//  　@　@0 
    public void testGetFirstAdddepositDepositRate_C001()
    {
        final String STR_METHOD_NAME = "testGetFirstAdddepositDepositRate_C001";
        log.entering(STR_METHOD_NAME);
        
        WEB3TPPaymentRequisitionManagementForTest1 l_tpRequisitionManagement =
            new WEB3TPPaymentRequisitionManagementForTest1();
        l_tpRequisitionManagement.setMarginEquityJudgeFlag("0");
        double l_dblReturn = l_tpRequisitionManagement.getFirstAdddepositDepositRate();
        assertEquals(0, l_dblReturn, 0);
        
        log.exiting(STR_METHOD_NAME);
    }
    
    
//    this.信用現物判定フラグ　@==　@1(信用顧客)の場合 
//  　@　@（1）this.入金請求管理Params == null かつ 
//  　@　@　@　@ this.資産余力情報<信用顧客>.余力計算条件.get会社部店別余力計算条件("first.deposit.rate1") == NULLの場合 
//  　@ 　@［返却値］ 
//  　@　@ 0 
  public void testGetFirstAdddepositDepositRate_C002()
  {
      final String STR_METHOD_NAME = "testGetFirstAdddepositDepositRate_C002";
      log.entering(STR_METHOD_NAME);
      
      WEB3TPPaymentRequisitionManagementForTest1 l_tpRequisitionManagement =
          new WEB3TPPaymentRequisitionManagementForTest1();
      l_tpRequisitionManagement.setMarginEquityJudgeFlag("1");
      WEB3TPTradingPowerCalcMargin tpCalcMargin1 = new WEB3TPTradingPowerCalcMargin();
      WEB3TPCalcCondition l_calcCondition = new WEB3TPCalcCondition();
      l_calcCondition.addInstBranCalcCondition(
          WEB3BranchPreferencesNameDef.FIRST_DEPOSIT_RATE1, null);
      tpCalcMargin1.setCalcCondition(l_calcCondition);
      
      PaymentRequisitMngParams l_paymentRequisitMngParams = null;
      l_tpRequisitionManagement.paymentRequisitMngParams = l_paymentRequisitMngParams;
      
      l_tpRequisitionManagement.tpCalcMargin = tpCalcMargin1;
      
      double l_dblReturn = l_tpRequisitionManagement.getFirstAdddepositDepositRate();
      assertEquals(0, l_dblReturn, 0);
      
      log.exiting(STR_METHOD_NAME);
  }

//    this.信用現物判定フラグ　@==　@1(信用顧客)の場合 
//  　@　@this.入金請求管理Params != null の場合 
//  　@　@　@［返却値］ 
//  　@　@　@this.入金請求管理Params.get第一水準保証金維持率（） 
    public void testGetFirstAdddepositDepositRate_C003()
    {
        final String STR_METHOD_NAME = "testGetFirstAdddepositDepositRate_C003";
        log.entering(STR_METHOD_NAME);
        
        WEB3TPPaymentRequisitionManagementForTest1 l_tpRequisitionManagement =
            new WEB3TPPaymentRequisitionManagementForTest1();
        l_tpRequisitionManagement.setMarginEquityJudgeFlag("1");
        WEB3TPTradingPowerCalcMargin tpCalcMargin1 = new WEB3TPTradingPowerCalcMargin();
        WEB3TPCalcCondition l_calcCondition = new WEB3TPCalcCondition();
        l_calcCondition.addInstBranCalcCondition(
            WEB3BranchPreferencesNameDef.FIRST_DEPOSIT_RATE1, "100.2");
        tpCalcMargin1.setCalcCondition(l_calcCondition);
        
        PaymentRequisitMngParams l_paymentRequisitMngParams = new PaymentRequisitMngParams();
        l_paymentRequisitMngParams.setFirstDepositRate(20.66);
        l_tpRequisitionManagement.paymentRequisitMngParams = l_paymentRequisitMngParams;
        l_tpRequisitionManagement.tpCalcMargin = tpCalcMargin1;
        
        double l_dblReturn = l_tpRequisitionManagement.getFirstAdddepositDepositRate();
        assertEquals(20.66, l_dblReturn, 0);
        
        log.exiting(STR_METHOD_NAME);
    }
    
    
//    b）this.信用現物判定フラグ　@==　@1(信用顧客)の場合 
//  　@　@（1）this.入金請求管理Params == nullかつ 
//  　@　@　@　@ this.資産余力情報<信用顧客>.余力計算条件.get会社部店別余力計算条件("first.deposit.rate1") != NULLの場合 
//  　@ 　@［返却値］ 
//  　@　@ this.資産余力情報<信用顧客>.余力計算条件.get会社部店別余力計算条件("first.deposit.rate1")
  public void testGetFirstAdddepositDepositRate_C004()
  {
      final String STR_METHOD_NAME = "testGetFirstAdddepositDepositRate_C004";
      log.entering(STR_METHOD_NAME);
      
      WEB3TPPaymentRequisitionManagementForTest1 l_tpRequisitionManagement =
          new WEB3TPPaymentRequisitionManagementForTest1();
      l_tpRequisitionManagement.setMarginEquityJudgeFlag("1");
      WEB3TPTradingPowerCalcMargin tpCalcMargin1 = new WEB3TPTradingPowerCalcMargin();
      WEB3TPCalcCondition l_calcCondition = new WEB3TPCalcCondition();
      l_calcCondition.addInstBranCalcCondition(
          WEB3BranchPreferencesNameDef.FIRST_DEPOSIT_RATE1, "100.2");
      tpCalcMargin1.setCalcCondition(l_calcCondition);
      l_tpRequisitionManagement.tpCalcMargin = tpCalcMargin1;
      
      PaymentRequisitMngParams l_paymentRequisitMngParams = null;
      l_tpRequisitionManagement.paymentRequisitMngParams = l_paymentRequisitMngParams;
      
      double l_dblReturn = l_tpRequisitionManagement.getFirstAdddepositDepositRate();
      assertEquals(100.2, l_dblReturn, 0);
      
      log.exiting(STR_METHOD_NAME);
  }

//    this.信用現物判定フラグ　@==　@0(現物顧客)の場合 
//  　@　@［返却値］ 
//  　@　@0 
    public void testGetFirstAdddepositAmount_C001()
    {
        final  String STR_METHOD_NAME = "testGetFirstAdddepositAmount_C001";
        log.entering(STR_METHOD_NAME);
        
        WEB3TPPaymentRequisitionManagementForTest1 l_tpRequisitionManagement =
            new WEB3TPPaymentRequisitionManagementForTest1();
        l_tpRequisitionManagement.setMarginEquityJudgeFlag("0");
        double l_dblreturn = l_tpRequisitionManagement.getFirstAdddepositAmount();
        assertEquals(0, l_dblreturn, 0);
        
        log.exiting(STR_METHOD_NAME);
    }
    
//    this.信用現物判定フラグ　@==　@1(信用顧客)の場合 
//  　@　@（1）this.入金請求管理Params == null の場合 
//  　@　@　@［返却値］ 
//  　@　@　@ 0 
    public void testGetFirstAdddepositAmount_C002()
    {
        final  String STR_METHOD_NAME = "testGetFirstAdddepositAmount_C002";
        log.entering(STR_METHOD_NAME);
        
        WEB3TPPaymentRequisitionManagementForTest1 l_tpRequisitionManagement =
            new WEB3TPPaymentRequisitionManagementForTest1();
        l_tpRequisitionManagement.setMarginEquityJudgeFlag("1");
        double l_dblreturn = l_tpRequisitionManagement.getFirstAdddepositAmount();
        assertEquals(0, l_dblreturn, 0);
        
        log.exiting(STR_METHOD_NAME);
    }
    
    
//    this.信用現物判定フラグ　@==　@1(信用顧客)の場合 
//  　@　@（2）this.入金請求管理Params != null の場合 
//  　@　@　@［返却値］ 
//  　@　@　@this.入金請求管理Params.get第一水準追証金額（） 
    public void testGetFirstAdddepositAmount_C003()
    {
        final  String STR_METHOD_NAME = "testGetFirstAdddepositAmount_C003";
        log.entering(STR_METHOD_NAME);
        
        WEB3TPPaymentRequisitionManagementForTest1 l_tpRequisitionManagement =
            new WEB3TPPaymentRequisitionManagementForTest1();
        l_tpRequisitionManagement.setMarginEquityJudgeFlag("1");
        
        PaymentRequisitMngParams l_paymentRequisitMngParams = new PaymentRequisitMngParams();
        l_paymentRequisitMngParams.setFirstDepositAmount(20.86);
        l_tpRequisitionManagement.paymentRequisitMngParams = l_paymentRequisitMngParams;
        double l_dblreturn = l_tpRequisitionManagement.getFirstAdddepositAmount();
        assertEquals(20.86, l_dblreturn, 0);
        
        log.exiting(STR_METHOD_NAME);
    }
    
//    this.信用現物判定フラグ　@==　@0(現物顧客)の場合 
//  　@　@［返却値］ 
//  　@　@0 
    public void testGetFirstAdddepositSettlement_C001()
    {
        final String STR_METHOD_NAME = "testGetFirstAdddepositSettlement_C001";
        log.entering(STR_METHOD_NAME);
        
        WEB3TPPaymentRequisitionManagementForTest1 l_tpRequisitionManagement =
            new WEB3TPPaymentRequisitionManagementForTest1();
        l_tpRequisitionManagement.setMarginEquityJudgeFlag("0");
        double l_dblReturn = l_tpRequisitionManagement.getFirstAdddepositSettlement();
        assertEquals(0, l_dblReturn, 0);
        log.exiting(STR_METHOD_NAME);
    }
    
//    this.信用現物判定フラグ　@==　@1(信用顧客)の場合 
//  　@　@（1）this.入金請求管理Params == null の場合 
//  　@　@　@［返却値］ 
//  　@　@　@ 0 
    public void testGetFirstAdddepositSettlement_C002()
    {
        final String STR_METHOD_NAME = "testGetFirstAdddepositSettlement_C002";
        log.entering(STR_METHOD_NAME);
        
        WEB3TPPaymentRequisitionManagementForTest1 l_tpRequisitionManagement =
            new WEB3TPPaymentRequisitionManagementForTest1();
        l_tpRequisitionManagement.setMarginEquityJudgeFlag("1");
        double l_dblReturn = l_tpRequisitionManagement.getFirstAdddepositSettlement();
        assertEquals(0, l_dblReturn, 0);
        log.exiting(STR_METHOD_NAME);
    }
    
//    this.信用現物判定フラグ　@==　@1(信用顧客)の場合 
//    2）this.入金請求管理Params != null の場合 
//    　@　@　@［返却値］ 
//    　@　@　@this.入金請求管理Params.get第一水準追証決済必要額（） 
    public void testGetFirstAdddepositSettlement_C003()
    {
        final String STR_METHOD_NAME = "testGetFirstAdddepositSettlement_C003";
        log.entering(STR_METHOD_NAME);
        
        WEB3TPPaymentRequisitionManagementForTest1 l_tpRequisitionManagement =
            new WEB3TPPaymentRequisitionManagementForTest1();
        l_tpRequisitionManagement.setMarginEquityJudgeFlag("1");
        PaymentRequisitMngParams l_paymentRequisitMngParams = new PaymentRequisitMngParams();
        l_paymentRequisitMngParams.setFirstSettlement(20.96);
        l_tpRequisitionManagement.paymentRequisitMngParams = l_paymentRequisitMngParams;
        
        double l_dblReturn = l_tpRequisitionManagement.getFirstAdddepositSettlement();
        assertEquals(20.96, l_dblReturn, 0);
        log.exiting(STR_METHOD_NAME);
    }
    
//    （a）this.信用現物判定フラグ　@==　@0(現物顧客)の場合 
//  　@　@［返却値］ 
//  　@　@0 
    public void testGetFirstAdddepositMarginDepositInDe_C001()
    {
        final String STR_METHOD_NAME = "testGetFirstAdddepositMarginDepositInDe_C001";
        log.entering(STR_METHOD_NAME);
        
        WEB3TPPaymentRequisitionManagementForTest1 l_tpRequisitionManagement =
            new WEB3TPPaymentRequisitionManagementForTest1();
        l_tpRequisitionManagement.setMarginEquityJudgeFlag("0");
        double l_dblReturn = l_tpRequisitionManagement.getFirstAdddepositMarginDepositInDe();
        assertEquals(0, l_dblReturn, 0);
        
        log.exiting(STR_METHOD_NAME);
    }
    
//    b）this.信用現物判定フラグ　@==　@1(信用顧客)の場合 
//  　@　@［返却値］ 
//  　@　@this.calc保証金増減（）
    public void testGetFirstAdddepositMarginDepositInDe_C002()
    {
        final String STR_METHOD_NAME = "testGetFirstAdddepositMarginDepositInDe_C002";
        log.entering(STR_METHOD_NAME);
        
        WEB3TPPaymentRequisitionManagementForTest1 l_tpRequisitionManagement =
            new WEB3TPPaymentRequisitionManagementForTest1();
        l_tpRequisitionManagement.setMarginEquityJudgeFlag("1");
        double l_dblReturn = l_tpRequisitionManagement.getFirstAdddepositMarginDepositInDe();
        assertEquals(22.53, l_dblReturn, 0);
        
        log.exiting(STR_METHOD_NAME);
    }
    
//    （a）this.信用現物判定フラグ　@==　@0(現物顧客)の場合 
//  　@　@［返却値］ 
//  　@　@0 
    public void testGetFirstAdddepositMarginDepositInDeExpect_C001()
    {
        final String STR_METHOD_NAME = "testGetFirstAdddepositMarginDepositInDeExpect_C001";
        log.entering(STR_METHOD_NAME);
        
        WEB3TPPaymentRequisitionManagementForTest1 l_tpRequisitionManagement =
            new WEB3TPPaymentRequisitionManagementForTest1();
        l_tpRequisitionManagement.setMarginEquityJudgeFlag("0");
        double l_dblReturn = l_tpRequisitionManagement.getFirstAdddepositMarginDepositInDeExpect();
        assertEquals(0, l_dblReturn, 0);
        
        log.exiting(STR_METHOD_NAME);
    }
    
//    （b）this.信用現物判定フラグ　@==　@1(信用顧客)の場合 
//  　@　@［返却値］ 
//  　@　@this.calc保証金増減(見込金額)（）
    public void testGetFirstAdddepositMarginDepositInDeExpect_C002()
    {
        final String STR_METHOD_NAME = "testGetFirstAdddepositMarginDepositInDeExpect_C002";
        log.entering(STR_METHOD_NAME);
        
        WEB3TPPaymentRequisitionManagementForTest1 l_tpRequisitionManagement =
            new WEB3TPPaymentRequisitionManagementForTest1();
        l_tpRequisitionManagement.setMarginEquityJudgeFlag("1");
        double l_dblReturn = l_tpRequisitionManagement.getFirstAdddepositMarginDepositInDeExpect();
        assertEquals(23.55, l_dblReturn, 0);
        
        log.exiting(STR_METHOD_NAME);
    }
    
//   　@（１）this.信用現物判定フラグ　@==　@0(現物顧客)の場合 
//  　@　@［返却値］ 
//  　@　@0 
    public void testGetFirstAdddepositSettledContract_C001()
    {
        final String STR_METHOD_NAME = "testGetFirstAdddepositSettledContract_C001";
        log.entering(STR_METHOD_NAME);
        
        WEB3TPPaymentRequisitionManagementForTest1 l_tpRequisitionManagement =
            new WEB3TPPaymentRequisitionManagementForTest1();
        l_tpRequisitionManagement.setMarginEquityJudgeFlag("0");
        double l_dblReturn = l_tpRequisitionManagement.getFirstAdddepositSettledContract();
        assertEquals(0, l_dblReturn, 0);
        
        log.exiting(STR_METHOD_NAME);
    }
    
//   　@（２）this.信用現物判定フラグ　@==　@1(信用顧客)の場合 
//  　@　@［返却値］ 
//  　@　@this.get当日返済建玉代金（）
    public void testGetFirstAdddepositSettledContract_C002()
    {
        final String STR_METHOD_NAME = "testGetFirstAdddepositSettledContract_C002";
        log.entering(STR_METHOD_NAME);
        
        WEB3TPPaymentRequisitionManagementForTest1 l_tpRequisitionManagement =
            new WEB3TPPaymentRequisitionManagementForTest1();
        l_tpRequisitionManagement.setMarginEquityJudgeFlag("1");
        double l_dblReturn = l_tpRequisitionManagement.getFirstAdddepositSettledContract();
        assertEquals(24.35, l_dblReturn, 0);
        
        log.exiting(STR_METHOD_NAME);
    }
    
//   　@（a）this.信用現物判定フラグ　@==　@0(現物顧客)の場合 
//  　@　@［返却値］ 
//  　@　@0 
    public void testGetFirstAdddepositUncancelAmt_C001()
    {
        final String STR_METHOD_NAME = "testGetFirstAdddepositUncancelAmt_C001";
        log.entering(STR_METHOD_NAME);
        
        WEB3TPPaymentRequisitionManagementForTest1 l_tpRequisitionManagement =
            new WEB3TPPaymentRequisitionManagementForTest1();
        l_tpRequisitionManagement.setMarginEquityJudgeFlag("0");
        double l_dblReturn = l_tpRequisitionManagement.getFirstAdddepositUncancelAmt();
        assertEquals(0, l_dblReturn, 0);
        
        log.exiting(STR_METHOD_NAME);
    }
    
//    this.信用現物判定フラグ　@==　@1(信用顧客)の場合 
//  　@　@（１）this.保証金自動振替後判定フラグ　@==　@true の場合 
//  　@　@　@［返却値］ 
//  　@　@　@this.calc第一水準追証金額（） 
    public void testGetFirstAdddepositUncancelAmt_C002()
    {
        final String STR_METHOD_NAME = "testGetFirstAdddepositUncancelAmt_C002";
        log.entering(STR_METHOD_NAME);
        
        WEB3TPPaymentRequisitionManagementForTest1 l_tpRequisitionManagement =
            new WEB3TPPaymentRequisitionManagementForTest1();
        l_tpRequisitionManagement.setMarginEquityJudgeFlag("1");
        l_tpRequisitionManagement.setDepositAutoTransferDivFlag(true);
        l_tpRequisitionManagement.l_strMethodName = "testGetFirstAdddepositUncancelAmt_C002";
        double l_dblReturn = l_tpRequisitionManagement.getFirstAdddepositUncancelAmt();
        assertEquals(11.52, l_dblReturn, 0);
        
        log.exiting(STR_METHOD_NAME);
    }
    
//    （２）this.保証金自動振替後判定フラグ　@==　@false の場合 
// 　@　@　@［返却値］ 
// 　@　@　@this.calc第一水準追証未解消金額（）
    public void testGetFirstAdddepositUncancelAmt_C003()
    {
        final String STR_METHOD_NAME = "testGetFirstAdddepositUncancelAmt_C003";
        log.entering(STR_METHOD_NAME);
        
        WEB3TPPaymentRequisitionManagementForTest1 l_tpRequisitionManagement =
            new WEB3TPPaymentRequisitionManagementForTest1();
        l_tpRequisitionManagement.setMarginEquityJudgeFlag("1");
        l_tpRequisitionManagement.setDepositAutoTransferDivFlag(false);
        double l_dblReturn = l_tpRequisitionManagement.getFirstAdddepositUncancelAmt();
        assertEquals(2.5, l_dblReturn, 0);
        
        log.exiting(STR_METHOD_NAME);
    }
    
//   　@（a）this.信用現物判定フラグ　@==　@0(現物顧客)の場合 
//  　@　@［返却値］ 
//  　@　@0 
    public void testGetFirstAdddepositUncancelSettleRequiredAmt_C001()
    {
        final String STR_METHOD_NAME = "testGetFirstAdddepositUncancelSettleRequiredAmt_C001";
        log.entering(STR_METHOD_NAME);
        
        WEB3TPPaymentRequisitionManagementForTest1 l_tpRequisitionManagement =
            new WEB3TPPaymentRequisitionManagementForTest1();
        l_tpRequisitionManagement.setMarginEquityJudgeFlag("0");
        double l_dblReturn = l_tpRequisitionManagement.getFirstAdddepositUncancelSettleRequiredAmt();
        assertEquals(0, l_dblReturn,0);
        
        log.exiting(STR_METHOD_NAME);
    }
    
//   　@（b）this.信用現物判定フラグ　@==　@1(信用顧客)の場合 
//  　@　@（１）this.保証金自動振替後判定フラグ　@==　@true の場合 
//  　@　@　@［返却値］ 
//  　@　@　@this.calc第一水準追証決済必要額（） 
    public void testGetFirstAdddepositUncancelSettleRequiredAmt_C002()
    {
        final String STR_METHOD_NAME = "testGetFirstAdddepositUncancelSettleRequiredAmt_C002";
        log.entering(STR_METHOD_NAME);
        
        WEB3TPPaymentRequisitionManagementForTest1 l_tpRequisitionManagement =
            new WEB3TPPaymentRequisitionManagementForTest1();
        l_tpRequisitionManagement.setMarginEquityJudgeFlag("1");
        l_tpRequisitionManagement.setDepositAutoTransferDivFlag(true);
        double l_dblReturn = l_tpRequisitionManagement.getFirstAdddepositUncancelSettleRequiredAmt();
        assertEquals(45.32, l_dblReturn,0);
        
        log.exiting(STR_METHOD_NAME);
    }

//    （b）this.信用現物判定フラグ　@==　@1(信用顧客)の場合 
//  　@　@（２）this.保証金自動振替後判定フラグ　@==　@false の場合 
//  　@　@　@［返却値］ 
//  　@　@　@this.calc第一水準追証未解消決済必要額（） 
    public void testGetFirstAdddepositUncancelSettleRequiredAmt_C003()
    {
        final String STR_METHOD_NAME = "testGetFirstAdddepositUncancelSettleRequiredAmt_C003";
        log.entering(STR_METHOD_NAME);
        
        WEB3TPPaymentRequisitionManagementForTest1 l_tpRequisitionManagement =
            new WEB3TPPaymentRequisitionManagementForTest1();
        l_tpRequisitionManagement.setMarginEquityJudgeFlag("1");
        l_tpRequisitionManagement.setDepositAutoTransferDivFlag(false);
        double l_dblReturn = l_tpRequisitionManagement.getFirstAdddepositUncancelSettleRequiredAmt();
        assertEquals(45.33, l_dblReturn,0);
        
        log.exiting(STR_METHOD_NAME);
    }
    
//   　@（a）this.信用現物判定フラグ　@==　@0(現物顧客)の場合 
//  　@　@［返却値］ 
//  　@　@NULL 
    public void testGetSecondAdddepositCloseDate2_C001()
    {
        final String STR_METHOD_NAME = "testGetSecondAdddepositCloseDate2_C001";
        log.entering(STR_METHOD_NAME);
        
        WEB3TPPaymentRequisitionManagementForTest1 l_tpRequisitionManagement =
            new WEB3TPPaymentRequisitionManagementForTest1();
        l_tpRequisitionManagement.setMarginEquityJudgeFlag("0");
        Date l_datReturn = l_tpRequisitionManagement.getSecondAdddepositCloseDate2();
        assertEquals(null, l_datReturn);

        log.exiting(STR_METHOD_NAME);
    }
    
//    this.信用現物判定フラグ　@==　@1(信用顧客)の場合 
//    this.資産余力情報<信用顧客>.get余力計算条件. 
//    　@　@　@　@　@　@get会社部店別余力計算条件（”second.deposit.compulsory.execution.timelimit”）　@==　@NULLの場合 
//    正?的第二水準追証期日(請求2)を返却する
    public void testGetSecondAdddepositCloseDate2_C002()
    {
        final String STR_METHOD_NAME = "testGetSecondAdddepositCloseDate2_C002";
        log.entering(STR_METHOD_NAME);
        
        WEB3TPPaymentRequisitionManagementForTest1 l_tpRequisitionManagement =
            new WEB3TPPaymentRequisitionManagementForTest1();
        l_tpRequisitionManagement.setMarginEquityJudgeFlag("1");
        
        WEB3TPTradingPowerCalcMargin tpCalcMargin1 = new WEB3TPTradingPowerCalcMargin();
        WEB3TPCalcCondition l_calcCondition = new WEB3TPCalcCondition();
        l_calcCondition.addInstBranCalcCondition(
            WEB3BranchPreferencesNameDef.SECOND_DEPOSIT_COMPULSORY_EXECUTION_TIMELIMIT, null);
        tpCalcMargin1.setCalcCondition(l_calcCondition);
        l_tpRequisitionManagement.tpCalcMargin = tpCalcMargin1;
        Date l_datReturn = l_tpRequisitionManagement.getSecondAdddepositCloseDate2();
        assertEquals(WEB3DateUtility.getDate("2004/07/16 00:00", "yyyy/MM/dd HH:mm"), l_datReturn);

        log.exiting(STR_METHOD_NAME);
    }
    
//    this.信用現物判定フラグ　@==　@1(信用顧客)の場合 
//    this.資産余力情報<信用顧客>.get余力計算条件. 
//    　@（b）this.資産余力情報<信用顧客>.get余力計算条件. 
//    　@　@　@　@　@　@get会社部店別余力計算条件（”second.deposit.compulsory.execution.timelimit”）　@!=　@NULLの場合 
//    正?的第二水準追証期日(請求2)を返却する
    public void testGetSecondAdddepositCloseDate2_C003()
    {
        final String STR_METHOD_NAME = "testGetSecondAdddepositCloseDate2_C003";
        log.entering(STR_METHOD_NAME);
        
        WEB3TPPaymentRequisitionManagementForTest1 l_tpRequisitionManagement =
            new WEB3TPPaymentRequisitionManagementForTest1();
        l_tpRequisitionManagement.setMarginEquityJudgeFlag("1");
        
        WEB3TPTradingPowerCalcMargin tpCalcMargin1 = new WEB3TPTradingPowerCalcMargin();
        WEB3TPCalcCondition l_calcCondition = new WEB3TPCalcCondition();
        l_calcCondition.addInstBranCalcCondition(
            WEB3BranchPreferencesNameDef.SECOND_DEPOSIT_COMPULSORY_EXECUTION_TIMELIMIT, "0304");
        tpCalcMargin1.setCalcCondition(l_calcCondition);
        l_tpRequisitionManagement.tpCalcMargin = tpCalcMargin1;
        Date l_datReturn = l_tpRequisitionManagement.getSecondAdddepositCloseDate2();
        assertEquals(WEB3DateUtility.getDate("2004/07/16 03:04", "yyyy/MM/dd HH:mm"), l_datReturn);

        log.exiting(STR_METHOD_NAME);
    }
    
    public void testGetSecondAdddepositCloseDate2_C004()
    {
        final String STR_METHOD_NAME = "testGetSecondAdddepositCloseDate2_C004";
        log.entering(STR_METHOD_NAME);
        
        WEB3TPPaymentRequisitionManagementForTest1 l_tpRequisitionManagement =
            new WEB3TPPaymentRequisitionManagementForTest1();
        l_tpRequisitionManagement.setMarginEquityJudgeFlag("1");
        l_tpRequisitionManagement.l_strMethodName ="testGetSecondAdddepositCloseDate1_004";
        
        WEB3TPTradingPowerCalcMargin tpCalcMargin1 = new WEB3TPTradingPowerCalcMargin();
        WEB3TPCalcCondition l_calcCondition = new WEB3TPCalcCondition();
        l_calcCondition.addInstBranCalcCondition("marginforcedsettle.seconddeposit2.div", "1");
        tpCalcMargin1.setCalcCondition(l_calcCondition);
        l_tpRequisitionManagement.tpCalcMargin = tpCalcMargin1;
        Date l_datReturn = l_tpRequisitionManagement.getSecondAdddepositCloseDate2();
        assertEquals(WEB3DateUtility.getDate("2004/07/23 00:00", "yyyy/MM/dd HH:mm"), l_datReturn);

        log.exiting(STR_METHOD_NAME);
    }
    
    
    public void testGetSecondAdddepositCloseDateExpect_C001()
    {
        final String STR_METHOD_NAME = "testGetSecondAdddepositCloseDateExpect_C001";
        log.entering(STR_METHOD_NAME);
        
        WEB3TPPaymentRequisitionManagementForTest1 l_tpRequisitionManagement =
            new WEB3TPPaymentRequisitionManagementForTest1();
        l_tpRequisitionManagement.setMarginEquityJudgeFlag("1");
        
        WEB3TPTradingPowerCalcMargin tpCalcMargin1 = new WEB3TPTradingPowerCalcMargin();
        WEB3TPCalcCondition l_calcCondition = new WEB3TPCalcCondition();
        l_calcCondition.addInstBranCalcCondition(
            WEB3BranchPreferencesNameDef.SECOND_DEPOSIT_COMPULSORY_EXECUTION_TIMELIMIT, "0304");
        tpCalcMargin1.setCalcCondition(l_calcCondition);
        l_tpRequisitionManagement.tpCalcMargin = tpCalcMargin1;
        Date l_datReturn = l_tpRequisitionManagement.getSecondAdddepositCloseDateExpect();
        assertEquals(WEB3DateUtility.getDate("2004/07/16 03:04", "yyyy/MM/dd HH:mm"), l_datReturn);

        log.exiting(STR_METHOD_NAME);
    }
    
    public void testGetSecondAdddepositCloseDateExpect_C002()
    {
        final String STR_METHOD_NAME = "testGetSecondAdddepositCloseDateExpect_C002";
        log.entering(STR_METHOD_NAME);
        
        WEB3TPPaymentRequisitionManagementForTest1 l_tpRequisitionManagement =
            new WEB3TPPaymentRequisitionManagementForTest1();
        l_tpRequisitionManagement.setMarginEquityJudgeFlag("1");
        l_tpRequisitionManagement.l_strMethodName ="testGetSecondAdddepositCloseDate1_004";
        
        WEB3TPTradingPowerCalcMargin tpCalcMargin1 = new WEB3TPTradingPowerCalcMargin();
        WEB3TPCalcCondition l_calcCondition = new WEB3TPCalcCondition();
        l_calcCondition.addInstBranCalcCondition("marginforcedsettle.seconddeposit2.div", "1");
        tpCalcMargin1.setCalcCondition(l_calcCondition);
        l_tpRequisitionManagement.tpCalcMargin = tpCalcMargin1;
        Date l_datReturn = l_tpRequisitionManagement.getSecondAdddepositCloseDateExpect();
        assertEquals(WEB3DateUtility.getDate("2004/07/23 00:00", "yyyy/MM/dd HH:mm"), l_datReturn);

        log.exiting(STR_METHOD_NAME);
    }
    
    
//    this.信用現物判定フラグ　@==　@0(現物顧客)の場合 
//  　@　@［返却値］ 
//  　@　@NULL 
    public void testGetSecondAdddepositCloseDate1_001()
    {
        final String STR_METHOD_NAME = "testGetSecondAdddepositCloseDate1_001";
        log.entering(STR_METHOD_NAME);
        
        WEB3TPPaymentRequisitionManagementForTest1 l_tpRequisitionManagement =
            new WEB3TPPaymentRequisitionManagementForTest1();
        l_tpRequisitionManagement.setMarginEquityJudgeFlag("0");
        
        Date l_datReturn = l_tpRequisitionManagement.getSecondAdddepositCloseDate1();
        assertEquals(null, l_datReturn);
        
        log.exiting(STR_METHOD_NAME);
    }
    
//    this.信用現物判定フラグ　@==　@1(信用顧客)の場合
//    this.資産余力情報<信用顧客>.get余力計算条件. 
//    　@　@　@　@　@　@get会社部店別余力計算条件（”second.deposit.compulsory.execution.timelimit”）　@==　@NULLの場
//    正?的第二水準追証期日(請求1)を返却する。
    public void testGetSecondAdddepositCloseDate1_002()
    {
        final String STR_METHOD_NAME = "testGetSecondAdddepositCloseDate1_002";
        log.entering(STR_METHOD_NAME);
        
        WEB3TPPaymentRequisitionManagementForTest1 l_tpRequisitionManagement =
            new WEB3TPPaymentRequisitionManagementForTest1();
        l_tpRequisitionManagement.setMarginEquityJudgeFlag("1");
        
        WEB3TPTradingPowerCalcMargin tpCalcMargin1 = new WEB3TPTradingPowerCalcMargin();
        WEB3TPCalcCondition l_calcCondition = new WEB3TPCalcCondition();
        l_calcCondition.addInstBranCalcCondition(
            WEB3BranchPreferencesNameDef.SECOND_DEPOSIT_COMPULSORY_EXECUTION_TIMELIMIT, null);
        tpCalcMargin1.setCalcCondition(l_calcCondition);
        l_tpRequisitionManagement.tpCalcMargin = tpCalcMargin1;
        
        Date l_datReturn = l_tpRequisitionManagement.getSecondAdddepositCloseDate1();
        assertEquals(WEB3DateUtility.getDate("2004/07/16 00:00", "yyyy/MM/dd HH:mm"), l_datReturn);
        
        log.exiting(STR_METHOD_NAME);
    }
    
//    this.信用現物判定フラグ　@==　@1(信用顧客)の場合
//    this.資産余力情報<信用顧客>.get余力計算条件. 
//    　@　@　@　@　@　@get会社部店別余力計算条件（”second.deposit.compulsory.execution.timelimit”）　@!=　@NULLの場
//    正?的第二水準追証期日(請求1)を返却する。
    public void testGetSecondAdddepositCloseDate1_003()
    {
        final String STR_METHOD_NAME = "testGetSecondAdddepositCloseDate1_003";
        log.entering(STR_METHOD_NAME);
        
        WEB3TPPaymentRequisitionManagementForTest1 l_tpRequisitionManagement =
            new WEB3TPPaymentRequisitionManagementForTest1();
        l_tpRequisitionManagement.setMarginEquityJudgeFlag("1");
        
        WEB3TPTradingPowerCalcMargin tpCalcMargin1 = new WEB3TPTradingPowerCalcMargin();
        WEB3TPCalcCondition l_calcCondition = new WEB3TPCalcCondition();
        l_calcCondition.addInstBranCalcCondition(
            WEB3BranchPreferencesNameDef.SECOND_DEPOSIT_COMPULSORY_EXECUTION_TIMELIMIT, "0506");
        tpCalcMargin1.setCalcCondition(l_calcCondition);
        l_tpRequisitionManagement.tpCalcMargin = tpCalcMargin1;
        
        Date l_datReturn = l_tpRequisitionManagement.getSecondAdddepositCloseDate1();
        assertEquals(WEB3DateUtility.getDate("2004/07/16 05:06", "yyyy/MM/dd HH:mm"), l_datReturn);
        log.exiting(STR_METHOD_NAME);
    }
    
    public void testGetSecondAdddepositCloseDate1_004()
    {
        final String STR_METHOD_NAME = "testGetSecondAdddepositCloseDate1_004";
        log.entering(STR_METHOD_NAME);
        
        WEB3TPPaymentRequisitionManagementForTest1 l_tpRequisitionManagement =
            new WEB3TPPaymentRequisitionManagementForTest1();
        l_tpRequisitionManagement.setMarginEquityJudgeFlag("1");
        l_tpRequisitionManagement.l_strMethodName ="testGetSecondAdddepositCloseDate1_004";
        WEB3TPTradingPowerCalcMargin tpCalcMargin1 = new WEB3TPTradingPowerCalcMargin();
        WEB3TPCalcCondition l_calcCondition = new WEB3TPCalcCondition();
        l_calcCondition.addInstBranCalcCondition("marginforcedsettle.seconddeposit2.div", "1");
        tpCalcMargin1.setCalcCondition(l_calcCondition);
        l_tpRequisitionManagement.tpCalcMargin = tpCalcMargin1;
        
        Date l_datReturn = l_tpRequisitionManagement.getSecondAdddepositCloseDate1();
        assertEquals(WEB3DateUtility.getDate("2004/07/23 00:00", "yyyy/MM/dd HH:mm"), l_datReturn);
        log.exiting(STR_METHOD_NAME);
    }
    
//   　@（a）this.信用現物判定フラグ　@==　@0(現物顧客)の場合 
//  　@　@［返却値］ 
//  　@　@NULL 
    public void testGetSecondAdddepositDepositOccurredDate2_C001()
    {
        final String STR_METHOD_NAME = "testGetSecondAdddepositDepositOccurredDate2_C001";
        log.entering(STR_METHOD_NAME);
        
        WEB3TPPaymentRequisitionManagementForTest1 l_tpRequisitionManagement =
            new WEB3TPPaymentRequisitionManagementForTest1();
        l_tpRequisitionManagement.setMarginEquityJudgeFlag("0");
        Date l_datReturn = l_tpRequisitionManagement.getSecondAdddepositDepositOccurredDate2();
        assertEquals(null, l_datReturn);
        
        log.exiting(STR_METHOD_NAME);
    }
    
//    （b）this.信用現物判定フラグ　@==　@1(信用顧客)の場合 
//  　@　@［返却値］ 
//  　@　@this.get期日(T-2)
    public void testGetSecondAdddepositDepositOccurredDate2_C002()
    {
        final String STR_METHOD_NAME = "testGetSecondAdddepositDepositOccurredDate2_C002";
        log.entering(STR_METHOD_NAME);
        
        WEB3TPPaymentRequisitionManagementForTest1 l_tpRequisitionManagement =
            new WEB3TPPaymentRequisitionManagementForTest1();
        l_tpRequisitionManagement.l_strMethodName = "testGetSecondAdddepositDepositOccurredDate2_C002";
        l_tpRequisitionManagement.setMarginEquityJudgeFlag("1");
        Date l_datReturn = l_tpRequisitionManagement.getSecondAdddepositDepositOccurredDate2();
        assertEquals(WEB3DateUtility.getDate("20040721", "yyyyMMdd"), l_datReturn);
        
        log.exiting(STR_METHOD_NAME);
    }
    
//   　@（a）this.信用現物判定フラグ　@==　@0(現物顧客)の場合 
//  　@　@［返却値］ 
//  　@　@NULL 
    public void testGetSecondAdddepositDepositOccurredDate1_C001()
    {
        final String STR_METHOD_NAME = "testGetSecondAdddepositDepositOccurredDate1_C001";
        log.entering(STR_METHOD_NAME);
        
        WEB3TPPaymentRequisitionManagementForTest1 l_tpRequisitionManagement =
            new WEB3TPPaymentRequisitionManagementForTest1();
        l_tpRequisitionManagement.l_strMethodName = "testGetSecondAdddepositDepositOccurredDate1_C001";
        l_tpRequisitionManagement.setMarginEquityJudgeFlag("0");
        Date l_datReturn = l_tpRequisitionManagement.getSecondAdddepositDepositOccurredDate1();
        assertEquals(null, l_datReturn);
        
        log.exiting(STR_METHOD_NAME);
    }
    
//    （b）this.信用現物判定フラグ　@==　@1(信用顧客)の場合 
//  　@　@［返却値］ 
//  　@　@this.get期日(T-1)
    public void testGetSecondAdddepositDepositOccurredDate1_C002()
    {
        final String STR_METHOD_NAME = "testGetSecondAdddepositDepositOccurredDate1_C002";
        log.entering(STR_METHOD_NAME);
        
        WEB3TPPaymentRequisitionManagementForTest1 l_tpRequisitionManagement =
            new WEB3TPPaymentRequisitionManagementForTest1();
        l_tpRequisitionManagement.l_strMethodName = "testGetSecondAdddepositDepositOccurredDate1_C002";
        l_tpRequisitionManagement.setMarginEquityJudgeFlag("1");
        Date l_datReturn = l_tpRequisitionManagement.getSecondAdddepositDepositOccurredDate1();
        assertEquals(WEB3DateUtility.getDate("20040722", "yyyyMMdd"), l_datReturn);
        
        log.exiting(STR_METHOD_NAME);
    }
    
    //get第二水準追証発生日（見込金額）
    public void testGetSecondAdddepositDepositOccurredDateExpect_C001()
    {
        final String STR_METHOD_NAME = "testGetSecondAdddepositDepositOccurredDateExpect_C001";
        log.entering(STR_METHOD_NAME);
        
        WEB3TPPaymentRequisitionManagementForTest1 l_tpRequisitionManagement =
            new WEB3TPPaymentRequisitionManagementForTest1();
        l_tpRequisitionManagement.setMarginEquityJudgeFlag("0");
        Date l_datReturn =
            l_tpRequisitionManagement.getSecondAdddepositDepositOccurredDateExpect();
        assertEquals(null, l_datReturn);
        
        log.exiting(STR_METHOD_NAME);
    }
    
    //get第二水準追証発生日（見込金額）
    public void testGetSecondAdddepositDepositOccurredDateExpect_C002()
    {
        final String STR_METHOD_NAME = "testGetSecondAdddepositDepositOccurredDateExpect_C002";
        log.entering(STR_METHOD_NAME);
        
        WEB3TPPaymentRequisitionManagementForTest1 l_tpRequisitionManagement =
            new WEB3TPPaymentRequisitionManagementForTest1();
        l_tpRequisitionManagement.setMarginEquityJudgeFlag("1");
        l_tpRequisitionManagement.setDepositAutoTransferDivFlag(true);
        l_tpRequisitionManagement.l_strMethodName = "testGetSecondAdddepositDepositOccurredDateExpect_C002";
        Date l_datReturn =
            l_tpRequisitionManagement.getSecondAdddepositDepositOccurredDateExpect();
        assertEquals(WEB3DateUtility.getDate("20040723", "yyyyMMdd"), l_datReturn);
        
        log.exiting(STR_METHOD_NAME);
    }
    
    //get第二水準追証発生日（見込金額）
    public void testGetSecondAdddepositDepositOccurredDateExpect_C003()
    {
        final String STR_METHOD_NAME = "testGetSecondAdddepositDepositOccurredDateExpect_C003";
        log.entering(STR_METHOD_NAME);
        
        WEB3TPPaymentRequisitionManagement l_tpRequisitionManagement =
            new WEB3TPPaymentRequisitionManagement();
        l_tpRequisitionManagement.setMarginEquityJudgeFlag("1");
        l_tpRequisitionManagement.setDepositAutoTransferDivFlag(false);
//        l_tpRequisitionManagement.l_strMethodName = "testGetSecondAdddepositDepositOccurredDateExpect_C003";
        Date l_datReturn =
            l_tpRequisitionManagement.getSecondAdddepositDepositOccurredDateExpect();
        assertEquals(null, l_datReturn);
        
        log.exiting(STR_METHOD_NAME);
    }
    
//    this.信用現物判定フラグ　@==　@0(現物顧客)の場合 
//  　@　@［返却値］ 
//  　@　@0 
    public void testGetSecondAdddepositDepositRate_C001()
    {
        final String STR_METHOD_NAME = "testGetSecondAdddepositDepositRate_C001";
        log.entering(STR_METHOD_NAME);
        
        WEB3TPPaymentRequisitionManagementForTest1 l_tpRequisitionManagement =
            new WEB3TPPaymentRequisitionManagementForTest1();
        l_tpRequisitionManagement.setMarginEquityJudgeFlag("0");
        double l_dblReturn = l_tpRequisitionManagement.getSecondAdddepositDepositRate();
        assertEquals(0, l_dblReturn, 0);
        
        log.exiting(STR_METHOD_NAME);
    }
    
//    this.信用現物判定フラグ　@==　@1(信用顧客)の場合 
//  　@　@（1）this.入金請求管理Params == null かつ 
//  　@　@ this.資産余力情報<信用顧客>.余力計算条件.get会社部店別余力計算条件("second.deposit.rate") == NULL の場合 
//  　@ 　@［返却値］ 
//  　@　@ 0 
    public void testGetSecondAdddepositDepositRate_C002()
    {
        final String STR_METHOD_NAME = "testGetSecondAdddepositDepositRate_C002";
        log.entering(STR_METHOD_NAME);
        
        WEB3TPPaymentRequisitionManagementForTest1 l_tpRequisitionManagement =
            new WEB3TPPaymentRequisitionManagementForTest1();
        l_tpRequisitionManagement.setMarginEquityJudgeFlag("1");
        
        WEB3TPTradingPowerCalcMargin tpCalcMargin1 = new WEB3TPTradingPowerCalcMargin();
        WEB3TPCalcCondition l_calcCondition = new WEB3TPCalcCondition();
        l_calcCondition.addInstBranCalcCondition(
            WEB3BranchPreferencesNameDef.SECOND_DEPOSIT_RATE, null);
        tpCalcMargin1.setCalcCondition(l_calcCondition);
        l_tpRequisitionManagement.tpCalcMargin = tpCalcMargin1;
        PaymentRequisitMngParams l_paymentRequisitMngParams = null;
        l_tpRequisitionManagement.paymentRequisitMngParams = l_paymentRequisitMngParams;
        
        double l_dblReturn = l_tpRequisitionManagement.getSecondAdddepositDepositRate();
        assertEquals(0, l_dblReturn, 0);
        
        log.exiting(STR_METHOD_NAME);
    }
    
//    this.信用現物判定フラグ　@==　@1(信用顧客)の場合
//    this.入金請求管理Params == null かつ 
//    　@　@ this.資産余力情報<信用顧客>.余力計算条件.get会社部店別余力計算条件("second.deposit.rate") != NULL の場合 
//    　@ 　@［返却値］ 
//    　@　@ this.資産余力情報<信用顧客>.余力計算条件.get会社部店別余力計算条件("second.deposit.rate")  
    public void testGetSecondAdddepositDepositRate_C003()
    {
        final String STR_METHOD_NAME = "testGetSecondAdddepositDepositRate_C003";
        log.entering(STR_METHOD_NAME);
        
        WEB3TPPaymentRequisitionManagementForTest1 l_tpRequisitionManagement =
            new WEB3TPPaymentRequisitionManagementForTest1();
        l_tpRequisitionManagement.setMarginEquityJudgeFlag("1");
        
        WEB3TPTradingPowerCalcMargin tpCalcMargin1 = new WEB3TPTradingPowerCalcMargin();
        WEB3TPCalcCondition l_calcCondition = new WEB3TPCalcCondition();
        l_calcCondition.addInstBranCalcCondition(
            WEB3BranchPreferencesNameDef.SECOND_DEPOSIT_RATE, "100.23");
        tpCalcMargin1.setCalcCondition(l_calcCondition);
        l_tpRequisitionManagement.tpCalcMargin = tpCalcMargin1;
        PaymentRequisitMngParams l_paymentRequisitMngParams = null;
        l_tpRequisitionManagement.paymentRequisitMngParams = l_paymentRequisitMngParams;
        
        double l_dblReturn = l_tpRequisitionManagement.getSecondAdddepositDepositRate();
        assertEquals(100.23, l_dblReturn, 0);
        
        log.exiting(STR_METHOD_NAME);
    }
    
//    this.信用現物判定フラグ　@==　@1(信用顧客)の場合
//  　@　@（3）this.入金請求管理Params != null の場合 
//  　@　@　@［返却値］ 
//  　@　@　@this.入金請求管理Params.get第二水準保証金維持率（） 
    public void testGetSecondAdddepositDepositRate_C004()
    {
        final String STR_METHOD_NAME = "testGetSecondAdddepositDepositRate_C004";
        log.entering(STR_METHOD_NAME);
        
        WEB3TPPaymentRequisitionManagementForTest1 l_tpRequisitionManagement =
            new WEB3TPPaymentRequisitionManagementForTest1();
        l_tpRequisitionManagement.setMarginEquityJudgeFlag("1");
        
        WEB3TPTradingPowerCalcMargin tpCalcMargin1 = new WEB3TPTradingPowerCalcMargin();
        WEB3TPCalcCondition l_calcCondition = new WEB3TPCalcCondition();
        l_calcCondition.addInstBranCalcCondition(
            WEB3BranchPreferencesNameDef.SECOND_DEPOSIT_RATE, null);
        tpCalcMargin1.setCalcCondition(l_calcCondition);
        l_tpRequisitionManagement.tpCalcMargin = tpCalcMargin1;
        PaymentRequisitMngParams l_paymentRequisitMngParams =
            new PaymentRequisitMngParams();
        l_paymentRequisitMngParams.setSecondDepositRate(10.223);
        l_tpRequisitionManagement.paymentRequisitMngParams = l_paymentRequisitMngParams;
        
        double l_dblReturn = l_tpRequisitionManagement.getSecondAdddepositDepositRate();
        assertEquals(10.223, l_dblReturn, 0);
        
        log.exiting(STR_METHOD_NAME);
    }
    
//    this.信用現物判定フラグ　@==　@0(現物顧客)の場合 
//  　@　@［返却値］ 
//  　@　@0 
    public void testGetSecondAdddepositDepositBackRate_C001()
    {
        final String STR_METHOD_NAME = "testGetSecondAdddepositDepositBackRate_C001";
        log.entering(STR_METHOD_NAME);

        WEB3TPPaymentRequisitionManagementForTest1 l_tpRequisitionManagement =
            new WEB3TPPaymentRequisitionManagementForTest1();
        l_tpRequisitionManagement.setMarginEquityJudgeFlag("0");
        double l_dblReturn = l_tpRequisitionManagement.getSecondAdddepositDepositBackRate();
        assertEquals(0, l_dblReturn, 0);
        
        log.exiting(STR_METHOD_NAME);
    }
    
//    this.信用現物判定フラグ　@==　@1(信用顧客)の場合 
//  　@　@（1）this.入金請求管理Params == null かつ 
//  　@　@　@this.資産余力情報<信用顧客>.余力計算条件.get会社部店別余力計算条件("second.deposit.back.rate") == NULL の場合 
//  　@　@［返却値］ 
//  　@　@ 0 
    public void testGetSecondAdddepositDepositBackRate_C002()
    {
        final String STR_METHOD_NAME = "testGetSecondAdddepositDepositBackRate_C002";
        log.entering(STR_METHOD_NAME);

        WEB3TPPaymentRequisitionManagementForTest1 l_tpRequisitionManagement =
            new WEB3TPPaymentRequisitionManagementForTest1();
        l_tpRequisitionManagement.setMarginEquityJudgeFlag("1");
        
        WEB3TPTradingPowerCalcMargin tpCalcMargin1 = new WEB3TPTradingPowerCalcMargin();
        WEB3TPCalcCondition l_calcCondition = new WEB3TPCalcCondition();
        l_calcCondition.addInstBranCalcCondition(
            WEB3BranchPreferencesNameDef.SECOND_DEPOSIT_BACK_RATE, null);
        
        PaymentRequisitMngParams l_paymentRequisitMngParams = null;
        l_tpRequisitionManagement.paymentRequisitMngParams = l_paymentRequisitMngParams;
        tpCalcMargin1.setCalcCondition(l_calcCondition);
        l_tpRequisitionManagement.tpCalcMargin = tpCalcMargin1;
        
        double l_dblReturn = l_tpRequisitionManagement.getSecondAdddepositDepositBackRate();
        assertEquals(0, l_dblReturn, 0);
        
        log.exiting(STR_METHOD_NAME);
    }
    
//    this.信用現物判定フラグ　@==　@1(信用顧客)の場合 
//    this.入金請求管理Params == null かつ 
//    　@　@　@this.資産余力情報<信用顧客>.余力計算条件.get会社部店別余力計算条件("second.deposit.back.rate") != NULL の場合 
//    　@　@［返却値］ 
//    　@　@ this.資産余力情報<信用顧客>.余力計算条件.get会社部店別余力計算条件("second.deposit.back.rate") 
    public void testGetSecondAdddepositDepositBackRate_C003()
    {
        final String STR_METHOD_NAME = "testGetSecondAdddepositDepositBackRate_C003";
        log.entering(STR_METHOD_NAME);

        WEB3TPPaymentRequisitionManagementForTest1 l_tpRequisitionManagement =
            new WEB3TPPaymentRequisitionManagementForTest1();
        l_tpRequisitionManagement.setMarginEquityJudgeFlag("1");
        
        WEB3TPTradingPowerCalcMargin tpCalcMargin1 = new WEB3TPTradingPowerCalcMargin();
        WEB3TPCalcCondition l_calcCondition = new WEB3TPCalcCondition();
        l_calcCondition.addInstBranCalcCondition(
            WEB3BranchPreferencesNameDef.SECOND_DEPOSIT_BACK_RATE, "101.23");
        tpCalcMargin1.setCalcCondition(l_calcCondition);
        l_tpRequisitionManagement.tpCalcMargin = tpCalcMargin1;
        PaymentRequisitMngParams l_paymentRequisitMngParams = null;
        l_tpRequisitionManagement.paymentRequisitMngParams = l_paymentRequisitMngParams;
        
        double l_dblReturn = l_tpRequisitionManagement.getSecondAdddepositDepositBackRate();
        assertEquals(101.23, l_dblReturn, 0);
        
        log.exiting(STR_METHOD_NAME);
    }
    
//    this.信用現物判定フラグ　@==　@1(信用顧客)の場合 
//  　@　@（3）this.入金請求管理Params != null の場合 
//  　@　@［返却値］ 
//  　@　@ this.入金請求管理Params.get第二水準保証金戻し維持率（） 
    public void testGetSecondAdddepositDepositBackRate_C004()
    {
        final String STR_METHOD_NAME = "testGetSecondAdddepositDepositBackRate_C004";
        log.entering(STR_METHOD_NAME);

        WEB3TPPaymentRequisitionManagementForTest1 l_tpRequisitionManagement =
            new WEB3TPPaymentRequisitionManagementForTest1();
        l_tpRequisitionManagement.setMarginEquityJudgeFlag("1");
        
        WEB3TPTradingPowerCalcMargin tpCalcMargin1 = new WEB3TPTradingPowerCalcMargin();
        WEB3TPCalcCondition l_calcCondition = new WEB3TPCalcCondition();
        l_calcCondition.addInstBranCalcCondition(
            WEB3BranchPreferencesNameDef.SECOND_DEPOSIT_BACK_RATE, "101.23");
        tpCalcMargin1.setCalcCondition(l_calcCondition);
        l_tpRequisitionManagement.tpCalcMargin = tpCalcMargin1;
        PaymentRequisitMngParams l_paymentRequisitMngParams =
            new PaymentRequisitMngParams();
        l_paymentRequisitMngParams.setSecondDepositBackRate(11.023);
        l_tpRequisitionManagement.paymentRequisitMngParams = l_paymentRequisitMngParams;
        
        double l_dblReturn = l_tpRequisitionManagement.getSecondAdddepositDepositBackRate();
        assertEquals(11.023, l_dblReturn, 0);
        
        log.exiting(STR_METHOD_NAME);
    }
    
//    a）this.信用現物判定フラグ　@==　@0(現物顧客)の場合 
//  　@　@［返却値］ 
//  　@　@0 
    public void testGetSecondAdddepositMarginDepositRate2_C001()
    {
        final String STR_METHO_NAME = "testGetSecondAdddepositMarginDepositRate2_C001";
        log.entering(STR_METHO_NAME);
        
        WEB3TPPaymentRequisitionManagementForTest1 l_tpRequisitionManagement =
            new WEB3TPPaymentRequisitionManagementForTest1();
        l_tpRequisitionManagement.setMarginEquityJudgeFlag("0");
        double l_dblReturn = l_tpRequisitionManagement.getSecondAdddepositMarginDepositRate2();
        assertEquals(0, l_dblReturn, 0);
        
        log.exiting(STR_METHO_NAME);
    }
    
//    this.信用現物判定フラグ　@==　@1(信用顧客)の場合 
//  　@　@（1）this.入金請求管理Params == null の場合 
//  　@　@　@［返却値］ 
//  　@　@　@0 
    public void testGetSecondAdddepositMarginDepositRate2_C002()
    {
        final String STR_METHO_NAME = "testGetSecondAdddepositMarginDepositRate2_C002";
        log.entering(STR_METHO_NAME);
        
        WEB3TPPaymentRequisitionManagementForTest1 l_tpRequisitionManagement =
            new WEB3TPPaymentRequisitionManagementForTest1();
        l_tpRequisitionManagement.setMarginEquityJudgeFlag("1");
        
        PaymentRequisitMngParams l_paymentRequisitMngParams = null;
        l_tpRequisitionManagement.paymentRequisitMngParams = l_paymentRequisitMngParams;
        
        double l_dblReturn = l_tpRequisitionManagement.getSecondAdddepositMarginDepositRate2();
        
      
        assertEquals(0, l_dblReturn, 0);
        
        log.exiting(STR_METHO_NAME);
    }
    
//    this.信用現物判定フラグ　@==　@1(信用顧客)の場合 
//    （2）this.入金請求管理Params != null の場合 
//    　@　@　@［返却値］ 
//    　@　@　@this.入金請求管理Params.get保証金預託率(前日)（） 
    public void testGetSecondAdddepositMarginDepositRate2_C003()
    {
        final String STR_METHO_NAME = "testGetSecondAdddepositMarginDepositRate2_C003";
        log.entering(STR_METHO_NAME);
        
        WEB3TPPaymentRequisitionManagementForTest1 l_tpRequisitionManagement =
            new WEB3TPPaymentRequisitionManagementForTest1();
        l_tpRequisitionManagement.setMarginEquityJudgeFlag("1");
        
        PaymentRequisitMngParams l_paymentRequisitMngParams = new PaymentRequisitMngParams();
        l_paymentRequisitMngParams.setMarginDepositRatePrebizdate(12.356);
        l_tpRequisitionManagement.paymentRequisitMngParams = l_paymentRequisitMngParams;
        
        double l_dblReturn = l_tpRequisitionManagement.getSecondAdddepositMarginDepositRate2();
        
      
        assertEquals(12.356, l_dblReturn, 0);
        
        log.exiting(STR_METHO_NAME);
    }
    
//    this.信用現物判定フラグ　@==　@0(現物顧客)の場合 
//  　@　@［返却値］ 
//  　@　@0 
    public void testGetSecondAdddepositMarginDepositRate1_C001()
    {
        final String STR_METHOD_NAME = "testGetSecondAdddepositMarginDepositRate1_C001";
        log.entering(STR_METHOD_NAME);
        
        WEB3TPPaymentRequisitionManagementForTest1 l_tpRequisitionManagement =
            new WEB3TPPaymentRequisitionManagementForTest1();
        l_tpRequisitionManagement.setMarginEquityJudgeFlag("0");
        
        double l_dblReturn = l_tpRequisitionManagement.getSecondAdddepositMarginDepositRate1();
        assertEquals(0, l_dblReturn, 0);
        
        log.exiting(STR_METHOD_NAME);
    }
    
//    this.信用現物判定フラグ　@==　@1(信用顧客)の場合 
//  　@　@（1）this.入金請求管理Params == null の場合 
//  　@　@　@［返却値］ 
//  　@　@　@0 
    public void testGetSecondAdddepositMarginDepositRate1_C002()
    {
        final String STR_METHOD_NAME = "testGetSecondAdddepositMarginDepositRate1_C002";
        log.entering(STR_METHOD_NAME);
        
        WEB3TPPaymentRequisitionManagementForTest1 l_tpRequisitionManagement =
            new WEB3TPPaymentRequisitionManagementForTest1();
        l_tpRequisitionManagement.setMarginEquityJudgeFlag("1");
        
        double l_dblReturn = l_tpRequisitionManagement.getSecondAdddepositMarginDepositRate1();
        assertEquals(0, l_dblReturn, 0);
        
        log.exiting(STR_METHOD_NAME);
    }
    
//    this.信用現物判定フラグ　@==　@1(信用顧客)の場合 
//    this.入金請求管理Params != null の場合 
//    　@　@　@［返却値］ 
//    　@　@　@this.入金請求管理Params.get保証金預託率（）
    public void testGetSecondAdddepositMarginDepositRate1_C003()
    {
        final String STR_METHOD_NAME = "testGetSecondAdddepositMarginDepositRate1_C003";
        log.entering(STR_METHOD_NAME);
        
        WEB3TPPaymentRequisitionManagementForTest1 l_tpRequisitionManagement =
            new WEB3TPPaymentRequisitionManagementForTest1();
        l_tpRequisitionManagement.setMarginEquityJudgeFlag("1");
        
        PaymentRequisitMngParams l_paymentRequisitMngParams = new PaymentRequisitMngParams();
        l_paymentRequisitMngParams.setMarginDepositRate(12.123);
        l_tpRequisitionManagement.paymentRequisitMngParams = l_paymentRequisitMngParams;
        
        double l_dblReturn = l_tpRequisitionManagement.getSecondAdddepositMarginDepositRate1();
        assertEquals(12.123, l_dblReturn, 0);
        
        log.exiting(STR_METHOD_NAME);
    }
   
//    this.信用現物判定フラグ　@==　@0(現物顧客)の場合 
//  　@　@［返却値］ 
//  　@　@0   
    public void testGetSecondAdddepositMarginDepositRateExpect_C001()
    {
        final String STR_METHOD_NAME = "testGetSecondAdddepositMarginDepositRateExpect_C001";
        log.entering(STR_METHOD_NAME);
        
        WEB3TPPaymentRequisitionManagementForTest1 l_tpRequisitionManagement =
            new WEB3TPPaymentRequisitionManagementForTest1();
        l_tpRequisitionManagement.setMarginEquityJudgeFlag("0");
        double l_dblReturn = l_tpRequisitionManagement.getSecondAdddepositMarginDepositRateExpect();
        assertEquals(0, l_dblReturn, 0);
        
        log.exiting(STR_METHOD_NAME);
    }
    
//    信用現物判定フラグ　@==　@1(信用顧客)の場合
//    is保証金自動振替後判定フラグ() == TRUE の場合 
//    　@　@［返却値］ 
//    　@　@this.calc保証金率（） 
    public void testGetSecondAdddepositMarginDepositRateExpect_C002()
    {
        
        final String STR_METHOD_NAME = "testGetSecondAdddepositMarginDepositRateExpect_C002";
        log.entering(STR_METHOD_NAME);
        
        WEB3TPPaymentRequisitionManagementForTest1 l_tpRequisitionManagement =
            new WEB3TPPaymentRequisitionManagementForTest1();
        l_tpRequisitionManagement.setMarginEquityJudgeFlag("1");
        l_tpRequisitionManagement.setDepositAutoTransferDivFlag(true);
        double l_dblReturn = l_tpRequisitionManagement.getSecondAdddepositMarginDepositRateExpect();

        assertEquals(100.5, l_dblReturn, 0);
        
        log.exiting(STR_METHOD_NAME);
    }
    
    
//  信用現物判定フラグ　@==　@1(信用顧客)の場合
//  is保証金自動振替後判定フラグ() == false の場合 
//  　@　@［返却値］ 
//  　@　@ 0 
  public void testGetSecondAdddepositMarginDepositRateExpect_C003()
  {
      
      final String STR_METHOD_NAME = "testGetSecondAdddepositMarginDepositRateExpect_C003";
      log.entering(STR_METHOD_NAME);
      
      WEB3TPPaymentRequisitionManagement l_tpRequisitionManagement =
          new WEB3TPPaymentRequisitionManagement();
      l_tpRequisitionManagement.setMarginEquityJudgeFlag("1");
      l_tpRequisitionManagement.setDepositAutoTransferDivFlag(false);
      double l_dblReturn = l_tpRequisitionManagement.getSecondAdddepositMarginDepositRateExpect();

      assertEquals(0, l_dblReturn, 0);
      
      log.exiting(STR_METHOD_NAME);
  }
  
//  this.信用現物判定フラグ　@==　@0(現物顧客)の場合 
//　@　@［返却値］ 
//　@　@0 
  public void testGetSecondAdddepositDepositNonPay_C001()
  {
      final String STR_METHOD_NAME = "testGetSecondAdddepositDepositNonPay_C001";
      log.entering(STR_METHOD_NAME);
      
      WEB3TPPaymentRequisitionManagementForTest1 l_tpRequisitionManagement =
          new WEB3TPPaymentRequisitionManagementForTest1();
      l_tpRequisitionManagement.setMarginEquityJudgeFlag("0");
      double l_dblReturn = l_tpRequisitionManagement.getSecondAdddepositDepositNonPay();
      assertEquals(0, l_dblReturn, 0);
      
      log.exiting(STR_METHOD_NAME);
  }
  
//  this.信用現物判定フラグ　@==　@1(信用顧客)の場合 
//　@　@（1）this.入金請求管理Params == null の場合 
//　@　@　@［返却値］ 
//　@　@　@0 
  public void testGetSecondAdddepositDepositNonPay_C002()
  {
      final String STR_METHOD_NAME = "testGetSecondAdddepositDepositNonPay_C002";
      log.entering(STR_METHOD_NAME);
      
      WEB3TPPaymentRequisitionManagementForTest1 l_tpRequisitionManagement =
          new WEB3TPPaymentRequisitionManagementForTest1();
      l_tpRequisitionManagement.setMarginEquityJudgeFlag("1");
      double l_dblReturn = l_tpRequisitionManagement.getSecondAdddepositDepositNonPay();
      assertEquals(0, l_dblReturn, 0);
      
      log.exiting(STR_METHOD_NAME);
  }
   
//  this.信用現物判定フラグ　@==　@1(信用顧客)の場合 
//  this.入金請求管理Params != null の場合 
//  　@　@　@［返却値］ 
//  　@　@　@this.入金請求管理Params.get第二水準追証未入金（）
  public void testGetSecondAdddepositDepositNonPay_C003()
  {
      final String STR_METHOD_NAME = "testGetSecondAdddepositDepositNonPay_C003";
      log.entering(STR_METHOD_NAME);
      
      WEB3TPPaymentRequisitionManagementForTest1 l_tpRequisitionManagement =
          new WEB3TPPaymentRequisitionManagementForTest1();
      l_tpRequisitionManagement.setMarginEquityJudgeFlag("1");
      
      PaymentRequisitMngParams l_paymentRequisitMngParams = new PaymentRequisitMngParams();
      l_paymentRequisitMngParams.setSecondDepositNonPay(12.123456);
      l_tpRequisitionManagement.paymentRequisitMngParams = l_paymentRequisitMngParams;
      double l_dblReturn = l_tpRequisitionManagement.getSecondAdddepositDepositNonPay();
      assertEquals(12.123456, l_dblReturn, 0);
      
      log.exiting(STR_METHOD_NAME);
  }
  
//  this.信用現物判定フラグ　@==　@0(現物顧客)の場合 
//　@　@［返却値］ 
//　@　@0 
  public void testGetSecondAdddepositDeposit2_C001()
  {
      final String STR_METHOD_NAME = "testGetSecondAdddepositDeposit2_C001";
      log.entering(STR_METHOD_NAME);
      
      WEB3TPPaymentRequisitionManagementForTest1 l_tpRequisitionManagement =
          new WEB3TPPaymentRequisitionManagementForTest1();
      l_tpRequisitionManagement.setMarginEquityJudgeFlag("0");
      double l_dblReturn = l_tpRequisitionManagement.getSecondAdddepositDeposit2();
      assertEquals(0, l_dblReturn, 0);
      
      log.exiting(STR_METHOD_NAME);
  }
  
//  this.信用現物判定フラグ　@==　@1(信用顧客)の場合 
//  this.入金請求管理Params == null の場合 
//  　@　@　@［返却値］ 
//  　@　@　@0 
  public void testGetSecondAdddepositDeposit2_C002()
  {
      final String STR_METHOD_NAME = "testGetSecondAdddepositDeposit2_C002";
      log.entering(STR_METHOD_NAME);
      
      WEB3TPPaymentRequisitionManagementForTest1 l_tpRequisitionManagement =
          new WEB3TPPaymentRequisitionManagementForTest1();
      l_tpRequisitionManagement.setMarginEquityJudgeFlag("1");
      double l_dblReturn = l_tpRequisitionManagement.getSecondAdddepositDeposit2();
      assertEquals(0, l_dblReturn, 0);
      
      log.exiting(STR_METHOD_NAME);
  }
  
//  this.信用現物判定フラグ　@==　@1(信用顧客)の場合 
//  this.入金請求管理Params != null の場合 
//  　@　@　@［返却値］ 
//  　@　@　@this.入金請求管理Params.get第二水準追証請求(2)（）
  public void testGetSecondAdddepositDeposit2_C003()
  {
      
      final String STR_METHOD_NAME = "testGetSecondAdddepositDeposit2_C003";
      log.entering(STR_METHOD_NAME);
      
      WEB3TPPaymentRequisitionManagementForTest1 l_tpRequisitionManagement =
          new WEB3TPPaymentRequisitionManagementForTest1();
      l_tpRequisitionManagement.setMarginEquityJudgeFlag("1");

      PaymentRequisitMngParams l_paymentRequisitMngParams = new PaymentRequisitMngParams();
      l_paymentRequisitMngParams.setSecondDeposit2(12.1234567);
      l_tpRequisitionManagement.paymentRequisitMngParams = l_paymentRequisitMngParams;
      double l_dblReturn = l_tpRequisitionManagement.getSecondAdddepositDeposit2();
      
      assertEquals(12.1234567, l_dblReturn, 0);
      
      log.exiting(STR_METHOD_NAME);
  }
  
//  this.信用現物判定フラグ　@==　@0(現物顧客)の場合 
//　@　@［返却値］ 
//　@　@0 
  public void testGetSecondAdddepositDeposit1_C001()
  {
      final String STR_METHOD_NAME = "testGetSecondAdddepositDeposit1_C001";
      log.entering(STR_METHOD_NAME);
      
      WEB3TPPaymentRequisitionManagementForTest1 l_tpRequisitionManagement =
          new WEB3TPPaymentRequisitionManagementForTest1();
      l_tpRequisitionManagement.setMarginEquityJudgeFlag("0");
      double l_dblReturn  = l_tpRequisitionManagement.getSecondAdddepositDeposit1();
      assertEquals(0, l_dblReturn, 0);
      
      log.exiting(STR_METHOD_NAME);
  }
  
  
//  this.信用現物判定フラグ　@==　@1(信用顧客)の場合 
//　@　@（1）this.入金請求管理Params == null の場合 
//　@　@　@［返却値］ 
//　@　@　@0 
  public void testGetSecondAdddepositDeposit1_C002()
  {
      final String STR_METHOD_NAME = "testGetSecondAdddepositDeposit1_C002";
      log.entering(STR_METHOD_NAME);
      
      WEB3TPPaymentRequisitionManagementForTest1 l_tpRequisitionManagement =
          new WEB3TPPaymentRequisitionManagementForTest1();
      l_tpRequisitionManagement.setMarginEquityJudgeFlag("1");
      double l_dblReturn  = l_tpRequisitionManagement.getSecondAdddepositDeposit1();
      assertEquals(0, l_dblReturn, 0);
      
      log.exiting(STR_METHOD_NAME);
  }
  
//  this.信用現物判定フラグ　@==　@1(信用顧客)の場合 
//  this.入金請求管理Params != null の場合 
//  　@　@　@［返却値］ 
//  　@　@　@this.入金請求管理Params.get第二水準追証請求(1)（）
  public void testGetSecondAdddepositDeposit1_C003()
  {
      final String STR_METHOD_NAME = "testGetSecondAdddepositDeposit1_C003";
      log.entering(STR_METHOD_NAME);
      
      WEB3TPPaymentRequisitionManagementForTest1 l_tpRequisitionManagement =
          new WEB3TPPaymentRequisitionManagementForTest1();
      
      PaymentRequisitMngParams l_paymentRequisitMngParams = new PaymentRequisitMngParams();
      l_paymentRequisitMngParams.setSecondDeposit1(13.1234567);
      l_tpRequisitionManagement.paymentRequisitMngParams = l_paymentRequisitMngParams;
      l_tpRequisitionManagement.setMarginEquityJudgeFlag("1");
      double l_dblReturn  = l_tpRequisitionManagement.getSecondAdddepositDeposit1();
      assertEquals(13.1234567, l_dblReturn, 7);
      
      log.exiting(STR_METHOD_NAME);
  }
  
//  a）this.信用現物判定フラグ　@==　@0(現物顧客)の場合 
//　@　@［返却値］ 
//　@　@0 
  public void testGetSecondAdddepositSettlementNonPay_C001()
  {
      final String STR_METHOD_NAME = "testGetSecondAdddepositSettlementNonPay_C001";
      log.entering(STR_METHOD_NAME);
      
      WEB3TPPaymentRequisitionManagementForTest1 l_tpRequisitionManagement =
          new WEB3TPPaymentRequisitionManagementForTest1();
      l_tpRequisitionManagement.setMarginEquityJudgeFlag("0");
      double l_dblReturn = l_tpRequisitionManagement.getSecondAdddepositSettlementNonPay();
      assertEquals(0, l_dblReturn, 0);
      
      log.exiting(STR_METHOD_NAME);
  }
  
//  this.信用現物判定フラグ　@==　@1(信用顧客)の場合 
//　@　@（1）this.入金請求管理Params == null の場合 
//　@　@　@［返却値］ 
//　@　@　@0 
  public void testGetSecondAdddepositSettlementNonPay_C002()
  {
      final String STR_METHOD_NAME = "testGetSecondAdddepositSettlementNonPay_C002";
      log.entering(STR_METHOD_NAME);
      
      WEB3TPPaymentRequisitionManagementForTest1 l_tpRequisitionManagement =
          new WEB3TPPaymentRequisitionManagementForTest1();
      l_tpRequisitionManagement.setMarginEquityJudgeFlag("1");
      double l_dblReturn = l_tpRequisitionManagement.getSecondAdddepositSettlementNonPay();
      assertEquals(0, l_dblReturn, 0);
      
      log.exiting(STR_METHOD_NAME);
  }
  
//  this.信用現物判定フラグ　@==　@1(信用顧客)の場合 
//  this.入金請求管理Params != null の場合 
//  　@　@　@［返却値］ 
//  　@　@　@this.入金請求管理Params.get第二水準追証未入金決済必要額（） 
  public void testGetSecondAdddepositSettlementNonPay_C003()
  {
      final String STR_METHOD_NAME = "testGetSecondAdddepositSettlementNonPay_C003";
      log.entering(STR_METHOD_NAME);
      
      WEB3TPPaymentRequisitionManagementForTest1 l_tpRequisitionManagement =
          new WEB3TPPaymentRequisitionManagementForTest1();
      l_tpRequisitionManagement.setMarginEquityJudgeFlag("1");
      
      PaymentRequisitMngParams l_paymentRequisitMngParams = new PaymentRequisitMngParams();
      l_paymentRequisitMngParams.setSecondSettlementNonPay(13.12);
      l_tpRequisitionManagement.paymentRequisitMngParams = l_paymentRequisitMngParams;
      double l_dblReturn = l_tpRequisitionManagement.getSecondAdddepositSettlementNonPay();
      assertEquals(13.12, l_dblReturn, 0);
      
      log.exiting(STR_METHOD_NAME);
  }

// 　@（a）this.信用現物判定フラグ　@==　@0(現物顧客)の場合 
//　@　@［返却値］ 
//　@　@0 
  public void testGetSecondAdddepositSettlement2_C001()
  {
      final String STR_METHOD_NAME = "testGetSecondAdddepositSettlement2_C001";
      log.entering(STR_METHOD_NAME);
      
      WEB3TPPaymentRequisitionManagementForTest1 l_tpRequisitionManagement =
          new WEB3TPPaymentRequisitionManagementForTest1();
      l_tpRequisitionManagement.setMarginEquityJudgeFlag("0");
      double l_dblReturn = l_tpRequisitionManagement.getSecondAdddepositSettlement2();
      assertEquals(0, l_dblReturn, 0);
      
      log.exiting(STR_METHOD_NAME);
  }
  
//  this.信用現物判定フラグ　@==　@1(信用顧客)の場合 
//　@　@（1）this.入金請求管理Params == null の場合 
//　@　@　@［返却値］ 
//　@　@　@0 
  public void testGetSecondAdddepositSettlement2_C002()
  {
      final String STR_METHOD_NAME = "testGetSecondAdddepositSettlement2_C002";
      log.entering(STR_METHOD_NAME);
      
      WEB3TPPaymentRequisitionManagementForTest1 l_tpRequisitionManagement =
          new WEB3TPPaymentRequisitionManagementForTest1();
      l_tpRequisitionManagement.setMarginEquityJudgeFlag("1");
      double l_dblReturn = l_tpRequisitionManagement.getSecondAdddepositSettlement2();
      assertEquals(0, l_dblReturn, 0);
      
      log.exiting(STR_METHOD_NAME);
  }
  
//  this.信用現物判定フラグ　@==　@1(信用顧客)の場合 
//  this.入金請求管理Params != null の場合 
//  　@　@　@［返却値］ 
//  　@　@　@this.入金請求管理Params.get第二水準追証決済必要額(2)（）
  public void testGetSecondAdddepositSettlement2_C003()
  {
      final String STR_METHOD_NAME = "testGetSecondAdddepositSettlement2_C003";
      log.entering(STR_METHOD_NAME);
      
      WEB3TPPaymentRequisitionManagementForTest1 l_tpRequisitionManagement =
          new WEB3TPPaymentRequisitionManagementForTest1();
      l_tpRequisitionManagement.setMarginEquityJudgeFlag("1");
      
      PaymentRequisitMngParams l_paymentRequisitMngParams = new PaymentRequisitMngParams();
      l_paymentRequisitMngParams.setSecondSettlement2(13.13);
      l_tpRequisitionManagement.paymentRequisitMngParams = l_paymentRequisitMngParams;
      double l_dblReturn = l_tpRequisitionManagement.getSecondAdddepositSettlement2();
      assertEquals(13.13, l_dblReturn, 0);
      
      log.exiting(STR_METHOD_NAME);
  }

//  this.信用現物判定フラグ　@==　@0(現物顧客)の場合 
//　@　@［返却値］ 
//　@　@0 
  public void testGetSecondAdddepositSettlement1_C001()
  {
      final String STR_METHOD_NAME = "testGetSecondAdddepositSettlement1_C001";
      log.entering(STR_METHOD_NAME);
      
      WEB3TPPaymentRequisitionManagementForTest1 l_tpRequisitionManagement =
          new WEB3TPPaymentRequisitionManagementForTest1();
      l_tpRequisitionManagement.setMarginEquityJudgeFlag("0");
      double l_dblReturn = l_tpRequisitionManagement.getSecondAdddepositSettlement1();
      assertEquals(0, l_dblReturn, 0);
      
      log.exiting(STR_METHOD_NAME);
  }
  
//  this.信用現物判定フラグ　@==　@1(信用顧客)の場合 
//　@　@（1）this.入金請求管理Params == null の場合 
//　@　@　@［返却値］ 
//　@　@　@0 
  public void testGetSecondAdddepositSettlement1_C002()
  {
      final String STR_METHOD_NAME = "testGetSecondAdddepositSettlement1_C002";
      log.entering(STR_METHOD_NAME);
      
      WEB3TPPaymentRequisitionManagementForTest1 l_tpRequisitionManagement =
          new WEB3TPPaymentRequisitionManagementForTest1();
      l_tpRequisitionManagement.setMarginEquityJudgeFlag("1");
      double l_dblReturn = l_tpRequisitionManagement.getSecondAdddepositSettlement1();
      assertEquals(0, l_dblReturn, 0);
      
      log.exiting(STR_METHOD_NAME);
  }
  
//  this.信用現物判定フラグ　@==　@1(信用顧客)の場合 
//  this.入金請求管理Params != null の場合 
//  　@　@　@［返却値］ 
//  　@　@　@this.入金請求管理Params.get第二水準追証決済必要額(1)（）
  public void testGetSecondAdddepositSettlement1_C003()
  {
      final String STR_METHOD_NAME = "testGetSecondAdddepositSettlement1_C003";
      log.entering(STR_METHOD_NAME);
      
      WEB3TPPaymentRequisitionManagementForTest1 l_tpRequisitionManagement =
          new WEB3TPPaymentRequisitionManagementForTest1();
      l_tpRequisitionManagement.setMarginEquityJudgeFlag("1");
      
      PaymentRequisitMngParams l_paymentRequisitMngParams = new PaymentRequisitMngParams();
      l_paymentRequisitMngParams.setSecondSettlement1(13.14);
      l_tpRequisitionManagement.paymentRequisitMngParams = l_paymentRequisitMngParams;
      
      double l_dblReturn = l_tpRequisitionManagement.getSecondAdddepositSettlement1();
      assertEquals(13.14, l_dblReturn, 0);
      
      log.exiting(STR_METHOD_NAME);
  }

//  （a）this.信用現物判定フラグ　@==　@0(現物顧客)の場合 
//　@　@［返却値］ 
//　@　@0 
  public void testGetSecondAdddepositMarginDepositInDe_C001()
  {
      final String STR_METHOD_NAME = "testGetSecondAdddepositMarginDepositInDe_C001";
      log.entering(STR_METHOD_NAME);
      
      WEB3TPPaymentRequisitionManagementForTest1 l_tpRequisitionManagement =
          new WEB3TPPaymentRequisitionManagementForTest1();
      l_tpRequisitionManagement.setMarginEquityJudgeFlag("0");
      double l_dblReturn = l_tpRequisitionManagement.getSecondAdddepositMarginDepositInDe();
      assertEquals(0, l_dblReturn, 0);
      
      log.exiting(STR_METHOD_NAME);
  }
  
//  b）this.信用現物判定フラグ　@==　@1(信用顧客)の場合 
//　@　@［返却値］ 
//　@　@this.calc保証金増減（）
  public void testGetSecondAdddepositMarginDepositInDe_C002()
  {
      final String STR_METHOD_NAME = "testGetSecondAdddepositMarginDepositInDe_C002";
      log.entering(STR_METHOD_NAME);
      
      WEB3TPPaymentRequisitionManagementForTest1 l_tpRequisitionManagement =
          new WEB3TPPaymentRequisitionManagementForTest1();
      l_tpRequisitionManagement.setMarginEquityJudgeFlag("1");
      double l_dblReturn = l_tpRequisitionManagement.getSecondAdddepositMarginDepositInDe();
      assertEquals(22.53, l_dblReturn, 0);
      
      log.exiting(STR_METHOD_NAME);
  }
  
// 　@this.信用現物判定フラグ　@==　@0(現物顧客)の場合 
//　@　@［返却値］ 
//　@　@0 
  public void testGetSecondAdddepositMarginDepositInDeExpect_C001()
  {
      final String STR_METHOD_NAME = "testGetSecondAdddepositMarginDepositInDeExpect_C001";
      log.entering(STR_METHOD_NAME);
      
      WEB3TPPaymentRequisitionManagementForTest1 l_tpRequisitionManagement =
          new WEB3TPPaymentRequisitionManagementForTest1();
      l_tpRequisitionManagement.setMarginEquityJudgeFlag("0");
      double l_dblReturn = l_tpRequisitionManagement.getSecondAdddepositMarginDepositInDeExpect();
      assertEquals(0, l_dblReturn, 0);
      
      log.exiting(STR_METHOD_NAME);
  }
  
//  this.信用現物判定フラグ　@==　@1(信用顧客)の場合 
//  is保証金自動振替後判定フラグ() == TRUE の場合 
//  　@　@［返却値］ 
//  　@　@this.calc保証金増減(見込金額)（） 
  public void testGetSecondAdddepositMarginDepositInDeExpect_C002()
  {
      final String STR_METHOD_NAME = "testGetSecondAdddepositMarginDepositInDeExpect_C002";
      log.entering(STR_METHOD_NAME);
      
      WEB3TPPaymentRequisitionManagementForTest1 l_tpRequisitionManagement =
          new WEB3TPPaymentRequisitionManagementForTest1();
      l_tpRequisitionManagement.setMarginEquityJudgeFlag("1");
      l_tpRequisitionManagement.setDepositAutoTransferDivFlag(true);
      double l_dblReturn = l_tpRequisitionManagement.getSecondAdddepositMarginDepositInDeExpect();
      
      assertEquals(23.55, l_dblReturn, 0);
      
      log.exiting(STR_METHOD_NAME);
  }
  
//  this.信用現物判定フラグ　@==　@1(信用顧客)の場合 i
//  s保証金自動振替後判定フラグ() == FALSE の場合 
//  　@　@［返却値］ 
//  　@　@0
  public void testGetSecondAdddepositMarginDepositInDeExpect_C003()
  {
      final String STR_METHOD_NAME = "testGetSecondAdddepositMarginDepositInDeExpect_C002";
      log.entering(STR_METHOD_NAME);
      
      WEB3TPPaymentRequisitionManagement l_tpRequisitionManagement =
          new WEB3TPPaymentRequisitionManagement();
      l_tpRequisitionManagement.setMarginEquityJudgeFlag("1");
      l_tpRequisitionManagement.setDepositAutoTransferDivFlag(false);
      double l_dblReturn = l_tpRequisitionManagement.getSecondAdddepositMarginDepositInDeExpect();
      
      assertEquals(0, l_dblReturn, 0);
      
      log.exiting(STR_METHOD_NAME);
  }
  
//  this.信用現物判定フラグ　@==　@0(現物顧客)の場合 
//　@　@［返却値］ 
//　@　@0 
  public void testGetSecondAdddepositSettledContract_C001()
  {
      final String STR_METHOD_NAME = "testGetSecondAdddepositSettledContract_C001";
      log.entering(STR_METHOD_NAME);
      
      WEB3TPPaymentRequisitionManagementForTest1 l_tpRequisitionManagement =
          new WEB3TPPaymentRequisitionManagementForTest1();
      l_tpRequisitionManagement.setMarginEquityJudgeFlag("0");
      double l_dblReturn = l_tpRequisitionManagement.getSecondAdddepositSettledContract();
      assertEquals(0, l_dblReturn, 0);
      
      log.exiting(STR_METHOD_NAME);
  }
  
//  this.信用現物判定フラグ　@==　@1(信用顧客)の場合 
//　@　@［返却値］ 
//　@　@this.get当日返済建玉代金（）
  public void testGetSecondAdddepositSettledContract_C002()
  {
      final String STR_METHOD_NAME = "testGetSecondAdddepositSettledContract_C002";
      log.entering(STR_METHOD_NAME);
      
      WEB3TPPaymentRequisitionManagementForTest1 l_tpRequisitionManagement =
          new WEB3TPPaymentRequisitionManagementForTest1();
      l_tpRequisitionManagement.setMarginEquityJudgeFlag("1");
      double l_dblReturn = l_tpRequisitionManagement.getSecondAdddepositSettledContract();
      assertEquals(24.35, l_dblReturn, 0);
      
      log.exiting(STR_METHOD_NAME);
  }
  private class WEB3TPPaymentRequisitionManagementForTest1 extends WEB3TPPaymentRequisitionManagement
    {
        protected double calcFirstAdddepositAmount()
        {
            if("testGetFirstAdddepositPassDay_C002".equals(l_strMethodName) ||
                "testGetFirstAdddepositPassDay_C003".equals(l_strMethodName) ||
                "testGetFirstAdddepositUncancelAmt_C002".equals(l_strMethodName))
            {
                return 11.52;
            }
            else
            {
                return 0;
            }
        }
        protected double calcFirstAdddepositUncancelAmt()
        {
            if("testGetFirstAdddepositPassDay_C002".equals(l_strMethodName))
            {
                return 0;
            }
            else
            {
                return 2.5;
            }
        }
        public int getFirstAdddepositPassDay()
        {
            if ("testGetFirstAdddepositOccurredDate_C002".equals(l_strMethodName))
            {
                return 0;
            }
            if ("testGetFirstAdddepositOccurredDate_C003".equals(l_strMethodName))
            {
                return 1;
            }
            if ("testGetFirstAdddepositOccurredDate_C004".equals(l_strMethodName))
            {
                return 2;
            }
            else
            {
                return super.getFirstAdddepositPassDay();
            }
        }
        public Date getDate(int l_intSpecifiedPoint)
        {
            if ("testGetSecondAdddepositDepositOccurredDate2_C002".equals(l_strMethodName))
            {
                return WEB3DateUtility.getDate("20040721", "yyyyMMdd");
            }
            else if("testGetSecondAdddepositDepositOccurredDate1_C002".equals(l_strMethodName))
            {
                return WEB3DateUtility.getDate("20040722", "yyyyMMdd");
            }
            else if("testGetSecondAdddepositDepositOccurredDateExpect_C002".equals(l_strMethodName))
            {
                return WEB3DateUtility.getDate("20040723", "yyyyMMdd");
            }
            else if("testGetSecondAdddepositCloseDate1_004".equals(l_strMethodName))
            {
                return WEB3DateUtility.getDate("20040723", "yyyyMMdd");
            }
            else
            {
                return WEB3DateUtility.getDate("20040716", "yyyyMMdd");
            }
        }
        protected double calcMarginDepositRate()
        {
            return 100.5;
        }
        protected double calcMarginDepositInDe()
        {
            return 22.53;
        }
        protected double calcMarginDepositInDeExpect()
        {
            return 23.55;
        }
        protected double getTodayRepayContractAmount()
        {
            return 24.35;
        }
        protected double calcFirstAdddepositSettlement()
        {
            return 45.32;
        }
        protected double calcFirstAdddepositUncancelSettleRequiredAmt()
        {
            return 45.33;
        }
        public boolean isDepositAutoTransferDivFlag()
        {
            return true;
        }
        public String l_strMethodName;
    }
    
    /*
     * （１） プロセス管理テーブルの検索結果　@==　@NULL　@or　@プロセス管理テーブルの検索結果が0件の場合
     */
    public void testIsDepositAutoTransfer_C0001()
    {
        final String STR_METHOD_NAME = "testIsDepositAutoTransfer_C0001()";
        log.entering(TEST_START + STR_METHOD_NAME);

        try
        {
            String l_strInstitutionCode = "";
            String l_strBranchCode = "";
            
            TestDBUtility.deleteAll(ProcessManagementRow.TYPE);
            
            boolean l_blnReturn =
                WEB3TPPaymentRequisitionManagement.isDepositAutoTransfer(l_strInstitutionCode, l_strBranchCode);
            
            assertFalse(l_blnReturn);
        }
        catch (Exception l_ex)
        {
            log.debug(STR_METHOD_NAME,l_ex);
            fail();
        }
        log.exiting(TEST_END + STR_METHOD_NAME);
    }
    /*
     * （２） (1)以外の場合
     */
    public void testIsDepositAutoTransfer_C0002()
    {
        final String STR_METHOD_NAME = "testIsDepositAutoTransfer_C0002()";
        log.entering(TEST_START + STR_METHOD_NAME);

        try
        {
            String l_strInstitutionCode = "0D";
            String l_strBranchCode = "22";
            
            TestDBUtility.deleteAll(ProcessManagementRow.TYPE);
            ProcessManagementParams l_processManagementParams =
                new ProcessManagementParams();
            l_processManagementParams.setProcessId("0005");
            l_processManagementParams.setInstitutionCode("0D");
            l_processManagementParams.setBranchCode("22");
            TestDBUtility.insertWithDel(l_processManagementParams);
            
            boolean l_blnReturn =
                WEB3TPPaymentRequisitionManagement.isDepositAutoTransfer(l_strInstitutionCode, l_strBranchCode);
            
            assertTrue(l_blnReturn);
        }
        catch (Exception l_ex)
        {
            log.debug(STR_METHOD_NAME,l_ex);
            fail();
        }
        log.exiting(TEST_END + STR_METHOD_NAME);
    }
    
    /*
     * get預り金不足額(T+n)の戻り値 > 0
     */
    public void testIsShortfallGeneration_C0001()
    {
        final String STR_METHOD_NAME = "testIsShortfallGeneration_C0001()";
        log.entering(TEST_START + STR_METHOD_NAME);

        try
        {
            l_blnFlag = true;
            
            WEB3TPPaymentRequisitionManagement l_managementForTest =
                new WEB3TPPaymentRequisitionManagementForTest4a();
            
            boolean l_blnReturn =
                l_managementForTest.isShortfallGeneration();
            
            assertTrue(l_blnReturn);
        }
        catch (Exception l_ex)
        {
            log.debug(STR_METHOD_NAME,l_ex);
            fail();
        }
        log.exiting(TEST_END + STR_METHOD_NAME);
    }
    /*
     * !(get預り金不足額(T+n)の戻り値 > 0)
     */
    public void testIsShortfallGeneration_C0002()
    {
        final String STR_METHOD_NAME = "testIsShortfallGeneration_C0002()";
        log.entering(TEST_START + STR_METHOD_NAME);

        try
        {
            l_blnFlag = false;
            
            WEB3TPPaymentRequisitionManagement l_managementForTest =
                new WEB3TPPaymentRequisitionManagementForTest4a();
            
            boolean l_blnReturn =
                l_managementForTest.isShortfallGeneration();
            
            assertFalse(l_blnReturn);
        }
        catch (Exception l_ex)
        {
            log.debug(STR_METHOD_NAME,l_ex);
            fail();
        }
        log.exiting(TEST_END + STR_METHOD_NAME);
    }
    
    /*
     * this.当初追証発生考慮フラグ == false の場合
     * get第一水準追証未解消金額() > 0 の場合
     */
    public void testIsFirstAdddeposit_C0001()
    {
        final String STR_METHOD_NAME = "testIsFirstAdddeposit_C0001()";
        log.entering(TEST_START + STR_METHOD_NAME);

        try
        {
            l_blnFlag = true;
            
            WEB3TPPaymentRequisitionManagement l_managementForTest =
                new WEB3TPPaymentRequisitionManagementForTest4b();
            l_managementForTest.setFirstOpenAdddepositFlag(false);
            
            boolean l_blnReturn =
                l_managementForTest.isFirstAdddeposit();
            
            assertTrue(l_blnReturn);
        }
        catch (Exception l_ex)
        {
            log.debug(STR_METHOD_NAME,l_ex);
            fail();
        }
        log.exiting(TEST_END + STR_METHOD_NAME);
    }
    /*
     * this.当初追証発生考慮フラグ == true の場合
     * is保証金自動振替後判定フラグ() = true かつ get第一水準追証未解消金額() > 0 の場合
     */
    public void testIsFirstAdddeposit_C0002()
    {
        final String STR_METHOD_NAME = "testIsFirstAdddeposit_C0002()";
        log.entering(TEST_START + STR_METHOD_NAME);

        try
        {
            l_blnFlag = true;
            
            WEB3TPPaymentRequisitionManagement l_managementForTest =
                new WEB3TPPaymentRequisitionManagementForTest4b();
            l_managementForTest.setFirstOpenAdddepositFlag(true);
            l_managementForTest.setDepositAutoTransferDivFlag(true);
            
            boolean l_blnReturn =
                l_managementForTest.isFirstAdddeposit();
            
            assertTrue(l_blnReturn);
        }
        catch (Exception l_ex)
        {
            log.debug(STR_METHOD_NAME,l_ex);
            fail();
        }
        log.exiting(TEST_END + STR_METHOD_NAME);
    }
    /*
     * this.当初追証発生考慮フラグ == true の場合
     * is保証金自動振替後判定フラグ() = false かつ get第一水準追証金額() > 0 の場合
     */
    public void testIsFirstAdddeposit_C0003()
    {
        final String STR_METHOD_NAME = "testIsFirstAdddeposit_C0003()";
        log.entering(TEST_START + STR_METHOD_NAME);

        try
        {
            l_blnFlag = true;
            
            WEB3TPPaymentRequisitionManagement l_managementForTest =
                new WEB3TPPaymentRequisitionManagementForTest4b();
            l_managementForTest.setFirstOpenAdddepositFlag(true);
            l_managementForTest.setDepositAutoTransferDivFlag(false);
            
            boolean l_blnReturn =
                l_managementForTest.isFirstAdddeposit();
            
            assertTrue(l_blnReturn);
        }
        catch (Exception l_ex)
        {
            log.debug(STR_METHOD_NAME,l_ex);
            fail();
        }
        log.exiting(TEST_END + STR_METHOD_NAME);
    }
    /*
     * FALSE(第一水準追証未発生)を返却
     */
    public void testIsFirstAdddeposit_C0004()
    {
        final String STR_METHOD_NAME = "testIsFirstAdddeposit_C0004()";
        log.entering(TEST_START + STR_METHOD_NAME);

        try
        {
            l_blnFlag = false;
            
            WEB3TPPaymentRequisitionManagement l_managementForTest =
                new WEB3TPPaymentRequisitionManagementForTest4b();
            l_managementForTest.setFirstOpenAdddepositFlag(false);
            l_managementForTest.setDepositAutoTransferDivFlag(false);
            
            boolean l_blnReturn =
                l_managementForTest.isFirstAdddeposit();
            
            assertFalse(l_blnReturn);
        }
        catch (Exception l_ex)
        {
            log.debug(STR_METHOD_NAME,l_ex);
            fail();
        }
        log.exiting(TEST_END + STR_METHOD_NAME);
    }
    
    /*
     * this.当初追証発生考慮フラグ == false の場合
     * 第二水準追証未解消情報.追証金額(未入金) > 0 又は
     * 第二水準追証未解消情報.追証金額(請求2) > 0　@又は
     * 第二水準追証未解消情報.追証金額(請求1) > 0 又は
     * 第二水準追証未解消情報.追証金額(見込金額) > 0 の場合
     */
    public void testIsSecondAdddeposit_C0001()
    {
        final String STR_METHOD_NAME = "testIsSecondAdddeposit_C0001()";
        log.entering(TEST_START + STR_METHOD_NAME);

        try
        {
            l_blnFlag = true;
            
            WEB3TPPaymentRequisitionManagement l_managementForTest =
                new WEB3TPPaymentRequisitionManagementForTest4c();
            l_managementForTest.setFirstOpenAdddepositFlag(false);
            
            boolean l_blnReturn =
                l_managementForTest.isSecondAdddeposit();
            
            assertTrue(l_blnReturn);
        }
        catch (Exception l_ex)
        {
            log.debug(STR_METHOD_NAME,l_ex);
            fail();
        }
        log.exiting(TEST_END + STR_METHOD_NAME);
    }
    /*
     * this.当初追証発生考慮フラグ == true の場合
     * is保証金自動振替後判定フラグ() = true かつ
     * 第二水準追証未解消情報.追証金額(未入金) > 0 又は
     * 第二水準追証未解消情報.追証金額(請求2) > 0　@又は
     * 第二水準追証未解消情報.追証金額(請求1) > 0 又は
     * 第二水準追証未解消情報.追証金額(見込金額) > 0 の場合
     */
    public void testIsSecondAdddeposit_C0002()
    {
        final String STR_METHOD_NAME = "testIsSecondAdddeposit_C0002()";
        log.entering(TEST_START + STR_METHOD_NAME);

        try
        {
            l_blnFlag = true;
            
            WEB3TPPaymentRequisitionManagement l_managementForTest =
                new WEB3TPPaymentRequisitionManagementForTest4c();
            l_managementForTest.setFirstOpenAdddepositFlag(true);
            l_managementForTest.setDepositAutoTransferDivFlag(true);
            
            boolean l_blnReturn =
                l_managementForTest.isSecondAdddeposit();
            
            assertTrue(l_blnReturn);
        }
        catch (Exception l_ex)
        {
            log.debug(STR_METHOD_NAME,l_ex);
            fail();
        }
        log.exiting(TEST_END + STR_METHOD_NAME);
    }
    /*
     * this.当初追証発生考慮フラグ == true の場合
     * is保証金自動振替後判定フラグ() = false かつ
     * (get第二水準追証金額（未入金） > 0 又は
     * get第二水準追証金額（請求2） > 0 又は
     * get第二水準追証金額（請求1） > 0 )の場合
     */
    public void testIsSecondAdddeposit_C0003()
    {
        final String STR_METHOD_NAME = "testIsSecondAdddeposit_C0003()";
        log.entering(TEST_START + STR_METHOD_NAME);

        try
        {
            WEB3TPPaymentRequisitionManagement l_managementForTest =
                new WEB3TPPaymentRequisitionManagementForTest4c();
            l_managementForTest.setFirstOpenAdddepositFlag(true);
            l_managementForTest.setDepositAutoTransferDivFlag(false);
            
            boolean l_blnReturn =
                l_managementForTest.isSecondAdddeposit();
            
            assertTrue(l_blnReturn);
        }
        catch (Exception l_ex)
        {
            log.debug(STR_METHOD_NAME,l_ex);
            fail();
        }
        log.exiting(TEST_END + STR_METHOD_NAME);
    }
    /*
     * FALSE(第二水準追証未発生)を返却
     */
    public void testIsSecondAdddeposit_C0004()
    {
        final String STR_METHOD_NAME = "testIsSecondAdddeposit_C0004()";
        log.entering(TEST_START + STR_METHOD_NAME);

        try
        {
            l_blnFlag = false;
            
            WEB3TPPaymentRequisitionManagement l_managementForTest =
                new WEB3TPPaymentRequisitionManagementForTest4c();
            l_managementForTest.setFirstOpenAdddepositFlag(true);
            l_managementForTest.setDepositAutoTransferDivFlag(true);
            
            boolean l_blnReturn =
                l_managementForTest.isSecondAdddeposit();
            
            assertFalse(l_blnReturn);
        }
        catch (Exception l_ex)
        {
            log.debug(STR_METHOD_NAME,l_ex);
            fail();
        }
        log.exiting(TEST_END + STR_METHOD_NAME);
    }
    
    /*
     * (a)this.入金請求管理Params == null の場合
     * (1)this.信用現物判定フラグ == "0"（現物顧客） かつ
     * this.資産余力情報<現物顧客>.余力計算条件.get預り証券評価制() == false の場合
     */
    public void testGetAccountAttribute_C0001()
    {
        final String STR_METHOD_NAME = "testGetAccountAttribute_C0001()";
        log.entering(TEST_START + STR_METHOD_NAME);

        try
        {
            WEB3TPPaymentRequisitionManagement l_managementForTest =
                new WEB3TPPaymentRequisitionManagementForTest4a();
            l_managementForTest.setMarginEquityJudgeFlag("0");
            
            WEB3TPTradingPowerCalcEquity tpCalcEquity = new WEB3TPTradingPowerCalcEquity();
            WEB3TPCalcCondition calcCondition = new WEB3TPCalcCondition();
            calcCondition.setEquityEvalDiv(false);
            tpCalcEquity.setCalcCondition(calcCondition);
            l_managementForTest.setTpCalcEquity(tpCalcEquity);
            
            String l_strReturn =
                l_managementForTest.getAccountAttribute();
            
            assertEquals("0", l_strReturn);
        }
        catch (Exception l_ex)
        {
            log.debug(STR_METHOD_NAME,l_ex);
            fail();
        }
        log.exiting(TEST_END + STR_METHOD_NAME);
    }
    /*
     * (a)this.入金請求管理Params == null の場合
     * (2)this.信用現物判定フラグ == "0"（現物顧客） かつ
     * this.資産余力情報<現物顧客>.余力計算条件.get預り証券評価制() == true の場合
     */
    public void testGetAccountAttribute_C0002()
    {
        final String STR_METHOD_NAME = "testGetAccountAttribute_C0002()";
        log.entering(TEST_START + STR_METHOD_NAME);

        try
        {
            WEB3TPPaymentRequisitionManagement l_managementForTest =
                new WEB3TPPaymentRequisitionManagementForTest4a();
            l_managementForTest.setMarginEquityJudgeFlag("0");
            
            WEB3TPTradingPowerCalcEquity tpCalcEquity = new WEB3TPTradingPowerCalcEquity();
            WEB3TPCalcCondition calcCondition = new WEB3TPCalcCondition();
            calcCondition.setEquityEvalDiv(true);
            tpCalcEquity.setCalcCondition(calcCondition);
            l_managementForTest.setTpCalcEquity(tpCalcEquity);
            
            String l_strReturn =
                l_managementForTest.getAccountAttribute();
            
            assertEquals("1", l_strReturn);
        }
        catch (Exception l_ex)
        {
            log.debug(STR_METHOD_NAME,l_ex);
            fail();
        }
        log.exiting(TEST_END + STR_METHOD_NAME);
    }
    /*
     * (a)this.入金請求管理Params == null の場合
     * (3)this.信用現物判定フラグ == "1"（信用顧客）の場合
     */
    public void testGetAccountAttribute_C0003()
    {
        final String STR_METHOD_NAME = "testGetAccountAttribute_C0003()";
        log.entering(TEST_START + STR_METHOD_NAME);

        try
        {
            WEB3TPPaymentRequisitionManagement l_managementForTest =
                new WEB3TPPaymentRequisitionManagementForTest4a();
            l_managementForTest.setMarginEquityJudgeFlag("1");
            
            String l_strReturn =
                l_managementForTest.getAccountAttribute();
            
            assertEquals("2", l_strReturn);
        }
        catch (Exception l_ex)
        {
            log.debug(STR_METHOD_NAME,l_ex);
            fail();
        }
        log.exiting(TEST_END + STR_METHOD_NAME);
    }
    /*
     * (b)this.入金請求管理Params != null の場合
     */
    public void testGetAccountAttribute_C0004()
    {
        final String STR_METHOD_NAME = "testGetAccountAttribute_C0004()";
        log.entering(TEST_START + STR_METHOD_NAME);

        try
        {
            WEB3TPPaymentRequisitionManagement l_managementForTest =
                new WEB3TPPaymentRequisitionManagementForTest4a();
            
            PaymentRequisitMngParams paymentRequisitMngParams = new PaymentRequisitMngParams();
            paymentRequisitMngParams.setAccountAttribute("5");
            l_managementForTest.setPaymentRequisitMngParams(paymentRequisitMngParams);
            
            String l_strReturn =
                l_managementForTest.getAccountAttribute();
            
            assertEquals("5", l_strReturn);
        }
        catch (Exception l_ex)
        {
            log.debug(STR_METHOD_NAME,l_ex);
            fail();
        }
        log.exiting(TEST_END + STR_METHOD_NAME);
    }
    
    /*
     * (a)this.入金請求管理Params == null の場合
     * (1)this.信用現物判定フラグ == "0"（現物顧客）
     */
    public void testGetCalcDate_C0001()
    {
        final String STR_METHOD_NAME = "testGetCalcDate_C0001()";
        log.entering(TEST_START + STR_METHOD_NAME);

        try
        {
            WEB3TPPaymentRequisitionManagement l_managementForTest =
                new WEB3TPPaymentRequisitionManagementForTest4a();
            l_managementForTest.setMarginEquityJudgeFlag("0");
            
            
            WEB3TPTradingPowerCalcEquity tpCalcEquity = new WEB3TPTradingPowerCalcEquity();
            WEB3TPCalcCondition calcCondition = new WEB3TPCalcCondition();
            
            Date[] bizDate = new Date[3];
            bizDate[1] = new Date();
            bizDate[1] = WEB3DateUtility.getDate("20080808", "yyyyMMdd");
            
            calcCondition.setBizDate(bizDate);
            tpCalcEquity.setCalcCondition(calcCondition);
            l_managementForTest.setTpCalcEquity(tpCalcEquity);
            
            Date l_datReturn =
                l_managementForTest.getCalcDate();
            
            assertEquals(WEB3DateUtility.getDate("20080808", "yyyyMMdd"), l_datReturn);
        }
        catch (Exception l_ex)
        {
            log.debug(STR_METHOD_NAME,l_ex);
            fail();
        }
        log.exiting(TEST_END + STR_METHOD_NAME);
    }
    /*
     * (a)this.入金請求管理Params == null の場合
     * (2)this.信用現物判定フラグ == "1"（信用顧客）の場合
     */
    public void testGetCalcDate_C0002()
    {
        final String STR_METHOD_NAME = "testGetCalcDate_C0002()";
        log.entering(TEST_START + STR_METHOD_NAME);

        try
        {
            WEB3TPPaymentRequisitionManagement l_managementForTest =
                new WEB3TPPaymentRequisitionManagementForTest4a();
            l_managementForTest.setMarginEquityJudgeFlag("1");
            
            
            WEB3TPTradingPowerCalcMargin tpCalcMargin = new WEB3TPTradingPowerCalcMargin();
            WEB3TPCalcCondition calcCondition = new WEB3TPCalcCondition();
            
            Date[] bizDate = new Date[3];
            bizDate[1] = new Date();
            bizDate[1] = WEB3DateUtility.getDate("20070707", "yyyyMMdd");
            
            calcCondition.setBizDate(bizDate);
            tpCalcMargin.setCalcCondition(calcCondition);
            l_managementForTest.setTpCalcMargin(tpCalcMargin);

            Date l_datReturn =
                l_managementForTest.getCalcDate();
            
            assertEquals(WEB3DateUtility.getDate("20070707", "yyyyMMdd"), l_datReturn);
        }
        catch (Exception l_ex)
        {
            log.debug(STR_METHOD_NAME,l_ex);
            fail();
        }
        log.exiting(TEST_END + STR_METHOD_NAME);
    }
    /*
     * (b)this.入金請求管理Params != null の場合
     */
    public void testGetCalcDate_C0003()
    {
        final String STR_METHOD_NAME = "testGetCalcDate_C0003()";
        log.entering(TEST_START + STR_METHOD_NAME);

        try
        {
            WEB3TPPaymentRequisitionManagement l_managementForTest =
                new WEB3TPPaymentRequisitionManagementForTest4a();
            
            PaymentRequisitMngParams paymentRequisitMngParams = new PaymentRequisitMngParams();
            paymentRequisitMngParams.setCalcDate(WEB3DateUtility.getDate("20060606", "yyyyMMdd"));
            l_managementForTest.setPaymentRequisitMngParams(paymentRequisitMngParams);
            
            Date l_datReturn =
                l_managementForTest.getCalcDate();
            
            assertEquals(WEB3DateUtility.getDate("20060606", "yyyyMMdd"), l_datReturn);
        }
        catch (Exception l_ex)
        {
            log.debug(STR_METHOD_NAME,l_ex);
            fail();
        }
        log.exiting(TEST_END + STR_METHOD_NAME);
    }
    
    /**
     * WEB3TPPaymentRequisitionManagementForTest4a
     */
    private class WEB3TPPaymentRequisitionManagementForTest4a extends WEB3TPPaymentRequisitionManagement
    {
        public double getLackAccountBalance(int l_intSpecifiedPoint)
        {
            if (l_blnFlag)
            {
                return 1;
            }
            else
            {
                return 0;
            }
        }
    }
    /**
     * WEB3TPPaymentRequisitionManagementForTest4b
     */
    private class WEB3TPPaymentRequisitionManagementForTest4b extends WEB3TPPaymentRequisitionManagement
    {
        public double getFirstAdddepositUncancelAmt()
        {
            if (l_blnFlag)
            {
                return 1;
            }
            else
            {
                return 0;
            }
        }
        public double getFirstAdddepositAmount()
        {

            if (l_blnFlag)
            {
                return 1;
            }
            else
            {
                return 0;
            }
        }
    }
    /**
     * WEB3TPPaymentRequisitionManagementForTest4b
     */
    private class WEB3TPPaymentRequisitionManagementForTest4c extends WEB3TPPaymentRequisitionManagement
    {
        public WEB3TPSecondAdddepositNotClearInfo createSecondAdddepositNotClearInfo()
        {
            WEB3TPSecondAdddepositNotClearInfo l_Info = new WEB3TPSecondAdddepositNotClearInfo();
            if (l_blnFlag)
            {
                l_Info.secondDepositNonPay = 1;
                l_Info.secondDeposit2 = 2;
                l_Info.secondDeposit1 = 3;
                l_Info.secondDepositExpect = 4;
            }
            else
            {
                l_Info.secondDepositNonPay = -1;
                l_Info.secondDeposit2 = -2;
                l_Info.secondDeposit1 = -3;
                l_Info.secondDepositExpect = -4;
            }
            
            return l_Info;
        }
        public double getSecondAdddepositDepositNonPay()
        {
            return 1;
        }
        public double getSecondAdddepositDeposit2()
        {
            return 2;
        }
        public double getSecondAdddepositDeposit1()
        {
            return 3;
        }
    }
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    /*
     * nが0以上5以下でない時、0を返却する。
     */
    public void testCalcAccountBalanceShortfallEquity_C0001()
    {
        final String STR_METHOD_NAME = "testCalcAccountBalanceShortfallEquity_C0001()";
        log.entering(TEST_START + STR_METHOD_NAME);

        try
        {
            int l_intSpecifiedPoint = 6;
            
            double l_dblAccountBalanceShortfallEquity =
                l_management.calcAccountBalanceShortfallEquity(l_intSpecifiedPoint);
            
            assertEquals("0.0", String.valueOf(l_dblAccountBalanceShortfallEquity));
        }
        catch (Exception l_ex)
        {
            log.debug(STR_METHOD_NAME,l_ex);
            fail();
        }
        log.exiting(TEST_END + STR_METHOD_NAME);
    }
    /*
     * (a)指定日(=n)　@==　@0　@の場合
     */
    public void testCalcAccountBalanceShortfallEquity_C0002()
    {
        final String STR_METHOD_NAME = "testCalcAccountBalanceShortfallEquity_C0002()";
        log.entering(TEST_START + STR_METHOD_NAME);

        try
        {
            int l_intSpecifiedPoint = 0;
            
            WEB3TPPaymentRequisitionManagement l_managementForTest =
                new WEB3TPPaymentRequisitionManagementForTest2a();
            
            TpCalcResultEquityParams l_tpCalcResultEquityParams = TestDBUtility.getTpCalcResultEquityRow();
            l_tpCalcResultEquityParams.setDayTradeRestraint0(11.0);
            l_tpCalcResultEquityParams.setAccountBalance0(12.0);
            l_tpCalcResultEquityParams.setTodayExecutedAmount0(13.0);
            l_tpCalcResultEquityParams.setTodayUnexecutedAmount0(14.0);
            l_tpCalcResultEquityParams.setOtherRestraint0(15.0);
            
            WEB3TPTradingPowerCalcEquity tpCalcEquity = new WEB3TPTradingPowerCalcEquity();
            tpCalcEquity.setCalcResultEquity(l_tpCalcResultEquityParams);
            
            WEB3TPCalcCondition calcCondition = new WEB3TPCalcCondition();
            calcCondition.setSpecialDebitAmount(16.0);
            
            tpCalcEquity.setCalcCondition(calcCondition);
            l_managementForTest.setTpCalcEquity(tpCalcEquity);
            
            double l_dblAccountBalanceShortfallEquity =
                l_managementForTest.calcAccountBalanceShortfallEquity(l_intSpecifiedPoint);
            
            assertEquals("46.0", String.valueOf(l_dblAccountBalanceShortfallEquity));
        }
        catch (Exception l_ex)
        {
            log.debug(STR_METHOD_NAME,l_ex);
            fail();
        }
        log.exiting(TEST_END + STR_METHOD_NAME);
    }
    /*
     * (b)(a)以外の場合
     */
    public void testCalcAccountBalanceShortfallEquity_C0003()
    {
        final String STR_METHOD_NAME = "testCalcAccountBalanceShortfallEquity_C0003()";
        log.entering(TEST_START + STR_METHOD_NAME);

        try
        {
            int l_intSpecifiedPoint = 1;
            
            WEB3TPPaymentRequisitionManagement l_managementForTest =
                new WEB3TPPaymentRequisitionManagementForTest2a();
            
            TpCalcResultEquityParams l_tpCalcResultEquityParams = TestDBUtility.getTpCalcResultEquityRow();
            l_tpCalcResultEquityParams.setDayTradeRestraint0(11.0);
            l_tpCalcResultEquityParams.setAccountBalance0(12.0);
            l_tpCalcResultEquityParams.setTodayExecutedAmount0(13.0);
            l_tpCalcResultEquityParams.setTodayUnexecutedAmount0(14.0);
            l_tpCalcResultEquityParams.setOtherRestraint0(15.0);
            
            l_tpCalcResultEquityParams.setDayTradeRestraint1(1.0);
            l_tpCalcResultEquityParams.setAccountBalance1(2.0);
            l_tpCalcResultEquityParams.setTodayExecutedAmount1(3.0);
            l_tpCalcResultEquityParams.setTodayUnexecutedAmount1(4.0);
            l_tpCalcResultEquityParams.setOtherRestraint1(5.0);

            
            WEB3TPTradingPowerCalcEquity tpCalcEquity = new WEB3TPTradingPowerCalcEquity();
            tpCalcEquity.setCalcResultEquity(l_tpCalcResultEquityParams);
            
            WEB3TPCalcCondition calcCondition = new WEB3TPCalcCondition();
            calcCondition.setSpecialDebitAmount(16.0);
            
            tpCalcEquity.setCalcCondition(calcCondition);
            l_managementForTest.setTpCalcEquity(tpCalcEquity);
            
            double l_dblAccountBalanceShortfallEquity =
                l_managementForTest.calcAccountBalanceShortfallEquity(l_intSpecifiedPoint);
            
            assertEquals("0.0", String.valueOf(l_dblAccountBalanceShortfallEquity));
        }
        catch (Exception l_ex)
        {
            log.debug(STR_METHOD_NAME,l_ex);
            fail();
        }
        log.exiting(TEST_END + STR_METHOD_NAME);
    }

    /*
     * nが0以上1以下でない時、0を返却する
     */
    public void testCalcAccountBalanceShortfallMargin_C0001()
    {
        final String STR_METHOD_NAME = "testCalcAccountBalanceShortfallMargin_C0001()";
        log.entering(TEST_START + STR_METHOD_NAME);

        try
        {
            int l_intSpecifiedPoint = 2;
            
            WEB3TPPaymentRequisitionManagement l_managementForTest =
                new WEB3TPPaymentRequisitionManagementForTest2a();
            
            WEB3TPTradingPowerCalcMargin tpCalcMargin = new WEB3TPTradingPowerCalcMargin();
            
            WEB3TPCalcCondition calcCondition = new WEB3TPCalcCondition();
            
            calcCondition.setSpecialDebitAmount(3);
            tpCalcMargin.setCalcCondition(calcCondition);
            
            l_managementForTest.setTpCalcMargin(tpCalcMargin);
            
            
            double l_bdRequiredPayAmt =
                l_managementForTest.calcAccountBalanceShortfallMargin(l_intSpecifiedPoint);
            
            assertEquals("0.0", String.valueOf(l_bdRequiredPayAmt));
        }
        catch (Exception l_ex)
        {
            log.debug(STR_METHOD_NAME,l_ex);
            fail();
        }
        log.exiting(TEST_END + STR_METHOD_NAME);
    }
    /*
     * a)指定日(=n)　@==　@0　@の場合
     */
    public void testCalcAccountBalanceShortfallMargin_C0002()
    {
        final String STR_METHOD_NAME = "testCalcAccountBalanceShortfallMargin_C0002()";
        log.entering(TEST_START + STR_METHOD_NAME);

        try
        {
            int l_intSpecifiedPoint = 0;
            
            WEB3TPPaymentRequisitionManagement l_managementForTest =
                new WEB3TPPaymentRequisitionManagementForTest2a();
            
            WEB3TPTradingPowerCalcMargin tpCalcMargin = new WEB3TPTradingPowerCalcMargin();
            
            WEB3TPCalcCondition calcCondition = new WEB3TPCalcCondition();
            
            calcCondition.setSpecialDebitAmount(3);
            tpCalcMargin.setCalcCondition(calcCondition);
            
            l_managementForTest.setTpCalcMargin(tpCalcMargin);
            
            
            double l_bdRequiredPayAmt =
                l_managementForTest.calcAccountBalanceShortfallMargin(l_intSpecifiedPoint);
            
            assertEquals("3.0", String.valueOf(l_bdRequiredPayAmt));
        }
        catch (Exception l_ex)
        {
            log.debug(STR_METHOD_NAME,l_ex);
            fail();
        }
        log.exiting(TEST_END + STR_METHOD_NAME);
    }
    /*
     * (b)指定日(=n)　@==　@1 かつ is保証金自動振替後判定フラグ() == TRUE の場合
     */
    public void testCalcAccountBalanceShortfallMargin_C0003()
    {
        final String STR_METHOD_NAME = "testCalcAccountBalanceShortfallMargin_C0003()";
        log.entering(TEST_START + STR_METHOD_NAME);

        try
        {
            int l_intSpecifiedPoint = 1;
            
            WEB3TPPaymentRequisitionManagement l_managementForTest =
                new WEB3TPPaymentRequisitionManagementForTest2a();
            
            WEB3TPTradingPowerCalcMargin tpCalcMargin = new WEB3TPTradingPowerCalcMargin();
            
            WEB3TPCalcCondition calcCondition = new WEB3TPCalcCondition();
            
            calcCondition.setSpecialDebitAmount(3);
            tpCalcMargin.setCalcCondition(calcCondition);
            
            l_managementForTest.setTpCalcMargin(tpCalcMargin);
            l_managementForTest.setDepositAutoTransferDivFlag(true);
            
            double l_bdRequiredPayAmt =
                l_managementForTest.calcAccountBalanceShortfallMargin(l_intSpecifiedPoint);
            
            assertEquals("0.0", String.valueOf(l_bdRequiredPayAmt));
        }
        catch (Exception l_ex)
        {
            log.debug(STR_METHOD_NAME,l_ex);
            fail();
        }
        log.exiting(TEST_END + STR_METHOD_NAME);
    }
    /*
     * c)指定日(=n)　@==　@1 かつ is保証金自動振替後判定フラグ() == FALSE の場合
     */
    public void testCalcAccountBalanceShortfallMargin_C0004()
    {
        final String STR_METHOD_NAME = "testCalcAccountBalanceShortfallMargin_C0004()";
        log.entering(TEST_START + STR_METHOD_NAME);

        try
        {
            int l_intSpecifiedPoint = 1;
            
            WEB3TPPaymentRequisitionManagement l_managementForTest =
                new WEB3TPPaymentRequisitionManagementForTest2a();
            
            WEB3TPTradingPowerCalcMargin tpCalcMargin = new WEB3TPTradingPowerCalcMargin();
            
            WEB3TPCalcCondition calcCondition = new WEB3TPCalcCondition();
            
            calcCondition.setSpecialDebitAmount(3);
            tpCalcMargin.setCalcCondition(calcCondition);
            
            l_managementForTest.setTpCalcMargin(tpCalcMargin);
            l_managementForTest.setDepositAutoTransferDivFlag(false);
            
            double l_bdRequiredPayAmt =
                l_managementForTest.calcAccountBalanceShortfallMargin(l_intSpecifiedPoint);
            
            assertEquals("0.0", String.valueOf(l_bdRequiredPayAmt));
        }
        catch (Exception l_ex)
        {
            log.debug(STR_METHOD_NAME,l_ex);
            fail();
        }
        log.exiting(TEST_END + STR_METHOD_NAME);
    }
    
    /*
     * nが0以上1以下でない時、0を返却する
     */
    public void testCalcAccountBalanceFromMarginDeposit_C0001()
    {
        final String STR_METHOD_NAME = "testCalcAccountBalanceFromMarginDeposit_C0001()";
        log.entering(TEST_START + STR_METHOD_NAME);

        try
        {
            int l_intSpecifiedPoint = 2;

            double l_dblReturn =
                l_management.calcAccountBalanceFromMarginDeposit(l_intSpecifiedPoint);
            
            assertEquals("0.0", String.valueOf(l_dblReturn));
        }
        catch (Exception l_ex)
        {
            log.debug(STR_METHOD_NAME,l_ex);
            fail();
        }
        log.exiting(TEST_END + STR_METHOD_NAME);
    }
    /*
     * （a）this.信用現物判定フラグ　@==　@0(現物顧客)の場合
     */
    public void testCalcAccountBalanceFromMarginDeposit_C0002()
    {
        final String STR_METHOD_NAME = "testCalcAccountBalanceFromMarginDeposit_C0002()";
        log.entering(TEST_START + STR_METHOD_NAME);

        try
        {
            int l_intSpecifiedPoint = 0;

            WEB3TPPaymentRequisitionManagement l_managementForTest =
                new WEB3TPPaymentRequisitionManagementForTest2b();
            l_managementForTest.marginEquityJudgeFlag = "0";
            
            double l_dblReturn =
                l_managementForTest.calcAccountBalanceFromMarginDeposit(l_intSpecifiedPoint);
            
            assertEquals("0.0", String.valueOf(l_dblReturn));
        }
        catch (Exception l_ex)
        {
            log.debug(STR_METHOD_NAME,l_ex);
            fail();
        }
        log.exiting(TEST_END + STR_METHOD_NAME);
    }
    /*
     * this.入出金注文単位一覧(預り金⇒保証金)(i).get受渡日（）　@==　@this.get期日(n)の場合、以下の処理を行う。
     */
    public void testCalcAccountBalanceFromMarginDeposit_C0003()
    {
        final String STR_METHOD_NAME = "testCalcAccountBalanceFromMarginDeposit_C0003()";
        log.entering(TEST_START + STR_METHOD_NAME);

        try
        {
            int l_intSpecifiedPoint = 0;

            WEB3TPPaymentRequisitionManagement l_managementForTest =
                new WEB3TPPaymentRequisitionManagementForTest2b();
            l_managementForTest.marginEquityJudgeFlag = "1";
            
            AioOrderUnitParams l_aioOrderUnitParams = TestDBUtility.getAioOrderUnitRow();
            l_aioOrderUnitParams.setDeliveryDate(WEB3DateUtility.getDate("20080808", "yyyyMMdd"));
            l_aioOrderUnitParams.setQuantity(5);
            
            List aioOrderUnitListFromDepositToMargins = new ArrayList();
            aioOrderUnitListFromDepositToMargins.add(0, l_aioOrderUnitParams);
            
            l_managementForTest.setAioOrderUnitListFromDepositToMargins(aioOrderUnitListFromDepositToMargins);
            
            
            double l_dblReturn =
                l_managementForTest.calcAccountBalanceFromMarginDeposit(l_intSpecifiedPoint);
            
            assertEquals("5.0", String.valueOf(l_dblReturn));
        }
        catch (Exception l_ex)
        {
            log.debug(STR_METHOD_NAME,l_ex);
            fail();
        }
        log.exiting(TEST_END + STR_METHOD_NAME);
    }
    
    /*
     * nが0以上1以下でない時、0を返却する
     */
    public void testCalcTransferFromMarginDeposit_C0001()
    {
        final String STR_METHOD_NAME = "testCalcTransferFromMarginDeposit_C0001()";
        log.entering(TEST_START + STR_METHOD_NAME);

        try
        {
            int l_intSpecifiedPoint = 2;

            double l_dblReturn =
                l_management.calcTransferFromMarginDeposit(l_intSpecifiedPoint);
            
            assertEquals("0.0", String.valueOf(l_dblReturn));
        }
        catch (Exception l_ex)
        {
            log.debug(STR_METHOD_NAME,l_ex);
            fail();
        }
        log.exiting(TEST_END + STR_METHOD_NAME);
    }
    /*
     * （a）this.信用現物判定フラグ　@==　@0(現物顧客)の場合
     */
    public void testCalcTransferFromMarginDeposit_C0002()
    {
        final String STR_METHOD_NAME = "testCalcTransferFromMarginDeposit_C0002()";
        log.entering(TEST_START + STR_METHOD_NAME);

        try
        {
            int l_intSpecifiedPoint = 0;

            WEB3TPPaymentRequisitionManagement l_managementForTest =
                new WEB3TPPaymentRequisitionManagementForTest2b();
            l_managementForTest.marginEquityJudgeFlag = "0";
            
            double l_dblReturn =
                l_management.calcTransferFromMarginDeposit(l_intSpecifiedPoint);
            
            assertEquals("0.0", String.valueOf(l_dblReturn));
        }
        catch (Exception l_ex)
        {
            log.debug(STR_METHOD_NAME,l_ex);
            fail();
        }
        log.exiting(TEST_END + STR_METHOD_NAME);
    }
    /*
     * this.入出金注文単位一覧(保証金⇒預り金)(i).get受渡日（）　@==　@this.get期日(n)の場合、以下の処理を行う。
     */
    public void testCalcTransferFromMarginDeposit_C0003()
    {
        final String STR_METHOD_NAME = "testCalcTransferFromMarginDeposit_C0003()";
        log.entering(TEST_START + STR_METHOD_NAME);

        try
        {
            int l_intSpecifiedPoint = 0;

            WEB3TPPaymentRequisitionManagement l_managementForTest =
                new WEB3TPPaymentRequisitionManagementForTest2b();
            l_managementForTest.marginEquityJudgeFlag = "1";
            
            AioOrderUnitParams l_aioOrderUnitParams = TestDBUtility.getAioOrderUnitRow();
            l_aioOrderUnitParams.setDeliveryDate(WEB3DateUtility.getDate("20080808", "yyyyMMdd"));
            l_aioOrderUnitParams.setQuantity(10);
            
            List aioOrderUnitListFromMarginToDeposits = new ArrayList();
            aioOrderUnitListFromMarginToDeposits.add(0, l_aioOrderUnitParams);
            
            l_managementForTest.setAioOrderUnitListFromMarginToDeposits(aioOrderUnitListFromMarginToDeposits);
            
            AioOrderUnitParams l_aioOrderUnitParams1 = TestDBUtility.getAioOrderUnitRow();
            l_aioOrderUnitParams1.setDeliveryDate(WEB3DateUtility.getDate("20080808", "yyyyMMdd"));
            l_aioOrderUnitParams1.setQuantity(6);
            
            List aioOrderUnitListFromDepositToMargins = new ArrayList();
            aioOrderUnitListFromDepositToMargins.add(0, l_aioOrderUnitParams1);
            
            l_managementForTest.setAioOrderUnitListFromDepositToMargins(aioOrderUnitListFromDepositToMargins);
            
            double l_dblReturn =
                l_managementForTest.calcTransferFromMarginDeposit(l_intSpecifiedPoint);
            
            assertEquals("4.0", String.valueOf(l_dblReturn));
        }
        catch (Exception l_ex)
        {
            log.debug(STR_METHOD_NAME,l_ex);
            fail();
        }
        log.exiting(TEST_END + STR_METHOD_NAME);
    }
    
    public void testCalcTransferFromMarginDeposit_C0004()
    {
        final String STR_METHOD_NAME = "testCalcTransferFromMarginDeposit_C0004()";
        log.entering(TEST_START + STR_METHOD_NAME);

        try
        {
            int l_intSpecifiedPoint = 0;

            WEB3TPPaymentRequisitionManagement l_managementForTest =
                new WEB3TPPaymentRequisitionManagementForTest2b();
            l_managementForTest.marginEquityJudgeFlag = "1";
            
            AioOrderUnitParams l_aioOrderUnitParams = TestDBUtility.getAioOrderUnitRow();
            l_aioOrderUnitParams.setDeliveryDate(WEB3DateUtility.getDate("20080808", "yyyyMMdd"));
            l_aioOrderUnitParams.setQuantity(10);
            
            List aioOrderUnitListFromMarginToDeposits = new ArrayList();
            aioOrderUnitListFromMarginToDeposits.add(0, l_aioOrderUnitParams);
            
            l_managementForTest.setAioOrderUnitListFromMarginToDeposits(aioOrderUnitListFromMarginToDeposits);
            
            AioOrderUnitParams l_aioOrderUnitParams1 = TestDBUtility.getAioOrderUnitRow();
            l_aioOrderUnitParams1.setDeliveryDate(WEB3DateUtility.getDate("20080808", "yyyyMMdd"));
            l_aioOrderUnitParams1.setQuantity(16);
            
            List aioOrderUnitListFromDepositToMargins = new ArrayList();
            aioOrderUnitListFromDepositToMargins.add(0, l_aioOrderUnitParams1);
            
            l_managementForTest.setAioOrderUnitListFromDepositToMargins(aioOrderUnitListFromDepositToMargins);
            
            double l_dblReturn =
                l_managementForTest.calcTransferFromMarginDeposit(l_intSpecifiedPoint);
            
            assertEquals("0.0", String.valueOf(l_dblReturn));
        }
        catch (Exception l_ex)
        {
            log.debug(STR_METHOD_NAME,l_ex);
            fail();
        }
        log.exiting(TEST_END + STR_METHOD_NAME);
    }
    
    /*
     * nが0以上5以下でない時、0を返却する。
     */
    public void testGetDayTradeRestraint_C0001()
    {
        final String STR_METHOD_NAME = "testGetDayTradeRestraint_C0001()";
        log.entering(TEST_START + STR_METHOD_NAME);

        try
        {
            int l_intSpecifiedPoint = 6;

            double l_dblReturn =
                l_management.getDayTradeRestraint(l_intSpecifiedPoint);
            
            assertEquals("0.0", String.valueOf(l_dblReturn));
        }
        catch (Exception l_ex)
        {
            log.debug(STR_METHOD_NAME,l_ex);
            fail();
        }
        log.exiting(TEST_END + STR_METHOD_NAME);
    }
    /*
     * （１）this.信用現物判定フラグ　@==　@0(現物顧客）の場合
     */
    public void testGetDayTradeRestraint_C0002()
    {
        final String STR_METHOD_NAME = "testGetDayTradeRestraint_C0002()";
        log.entering(TEST_START + STR_METHOD_NAME);

        try
        {
            int l_intSpecifiedPoint = 0;

            WEB3TPPaymentRequisitionManagement l_managementForTest =
                new WEB3TPPaymentRequisitionManagementForTest2b();
            l_managementForTest.setMarginEquityJudgeFlag("0");
            
            TpCalcResultEquityParams calcResultEquity = TestDBUtility.getTpCalcResultEquityRow();
            calcResultEquity.setDayTradeRestraint0(5.0);
            
            WEB3TPTradingPowerCalcEquity tpCalcEquity = new WEB3TPTradingPowerCalcEquity();
            tpCalcEquity.setCalcResultEquity(calcResultEquity);
            l_managementForTest.setTpCalcEquity(tpCalcEquity);
            
            double l_dblReturn =
                l_managementForTest.getDayTradeRestraint(l_intSpecifiedPoint);
            
            assertEquals("5.0", String.valueOf(l_dblReturn));
        }
        catch (Exception l_ex)
        {
            log.debug(STR_METHOD_NAME,l_ex);
            fail();
        }
        log.exiting(TEST_END + STR_METHOD_NAME);
    }
    /*
     * （２）this.信用現物判定フラグ　@==　@1(信用顧客）の場合
     */
    public void testGetDayTradeRestraint_C0003()
    {
        final String STR_METHOD_NAME = "testGetDayTradeRestraint_C0003()";
        log.entering(TEST_START + STR_METHOD_NAME);

        try
        {
            int l_intSpecifiedPoint = 0;

            WEB3TPPaymentRequisitionManagement l_managementForTest =
                new WEB3TPPaymentRequisitionManagementForTest2b();
            l_managementForTest.setMarginEquityJudgeFlag("1");
            
            TpCalcResultMarginParams calcResultMargin = TestDBUtility.getTpCalcResultMarginRow();
            calcResultMargin.setDayTradeRestraint0(5.0);
            
            WEB3TPTradingPowerCalcMargin tpCalcMargin = new WEB3TPTradingPowerCalcMargin();
            tpCalcMargin.setCalcResultMargin(calcResultMargin);
            l_managementForTest.setTpCalcMargin(tpCalcMargin);
            
            double l_dblReturn =
                l_managementForTest.getDayTradeRestraint(l_intSpecifiedPoint);
            
            assertEquals("5.0", String.valueOf(l_dblReturn));
        }
        catch (Exception l_ex)
        {
            log.debug(STR_METHOD_NAME,l_ex);
            fail();
        }
        log.exiting(TEST_END + STR_METHOD_NAME);
    }

    /*
     * nが0以上5以下でない時、0を返却する。
     */
    public void testCalcAccountCalculate_C0001()
    {
        final String STR_METHOD_NAME = "testCalcAccountCalculate_C0001()";
        log.entering(TEST_START + STR_METHOD_NAME);

        try
        {
            int l_intSpecifiedPoint = 6;

            double l_dblReturn =
                l_management.calcAccountCalculate(l_intSpecifiedPoint);
            
            assertEquals("0.0", String.valueOf(l_dblReturn));
        }
        catch (Exception l_ex)
        {
            log.debug(STR_METHOD_NAME,l_ex);
            fail();
        }
        log.exiting(TEST_END + STR_METHOD_NAME);
    }
    /*
     * a）this.信用現物判定フラグ　@==　@0(現物顧客）の場合
     */
    public void testCalcAccountCalculate_C0002()
    {
        final String STR_METHOD_NAME = "testCalcAccountCalculate_C0002()";
        log.entering(TEST_START + STR_METHOD_NAME);

        try
        {
            int l_intSpecifiedPoint = 0;

            WEB3TPPaymentRequisitionManagement l_managementForTest =
                new WEB3TPPaymentRequisitionManagementForTest2c();
            l_managementForTest.setMarginEquityJudgeFlag("0");
            
            double l_dblReturn =
                l_managementForTest.calcAccountCalculate(l_intSpecifiedPoint);
            
            assertEquals("12.0", String.valueOf(l_dblReturn));
        }
        catch (Exception l_ex)
        {
            log.debug(STR_METHOD_NAME,l_ex);
            fail();
        }
        log.exiting(TEST_END + STR_METHOD_NAME);
    }
    /*
     * （b）this.信用現物判定フラグ　@==　@1(信用顧客）の場合
     */
    public void testCalcAccountCalculate_C0003()
    {
        final String STR_METHOD_NAME = "testCalcAccountCalculate_C0003()";
        log.entering(TEST_START + STR_METHOD_NAME);

        try
        {
            int l_intSpecifiedPoint = 0;

            WEB3TPPaymentRequisitionManagement l_managementForTest =
                new WEB3TPPaymentRequisitionManagementForTest2c();
            l_managementForTest.setMarginEquityJudgeFlag("1");
            
            double l_dblReturn =
                l_managementForTest.calcAccountCalculate(l_intSpecifiedPoint);
            
            assertEquals("1.0", String.valueOf(l_dblReturn));
        }
        catch (Exception l_ex)
        {
            log.debug(STR_METHOD_NAME,l_ex);
            fail();
        }
        log.exiting(TEST_END + STR_METHOD_NAME);
    }
    
    /*
     * nが0以上5以下でない時、0を返却する。
     */
    public void testCalcAccountBalance_C0001()
    {
        final String STR_METHOD_NAME = "testCalcAccountBalance_C0001()";
        log.entering(TEST_START + STR_METHOD_NAME);

        try
        {
            int l_intSpecifiedPoint = 6;
            
            double l_dblReturn =
                l_management.calcAccountBalance(l_intSpecifiedPoint);
            
            assertEquals("0.0", String.valueOf(l_dblReturn));
        }
        catch (Exception l_ex)
        {
            log.debug(STR_METHOD_NAME,l_ex);
            fail();
        }
        log.exiting(TEST_END + STR_METHOD_NAME);
    }
    /*
     * （１）this.信用現物判定フラグ　@==　@0(現物顧客）の場合
     */
    public void testCalcAccountBalance_C0002()
    {
        final String STR_METHOD_NAME = "testCalcAccountBalance_C0002()";
        log.entering(TEST_START + STR_METHOD_NAME);

        try
        {
            int l_intSpecifiedPoint = 0;
            
            WEB3TPPaymentRequisitionManagement l_managementForTest =
                new WEB3TPPaymentRequisitionManagementForTest2b();
            l_managementForTest.setMarginEquityJudgeFlag("0");
            
            WEB3TPTradingPowerCalcEquity l_tradingPowerCalcEquity =
                new WEB3TPTradingPowerCalcEquity();
            
            TpCalcResultEquityParams calcResultEquity = TestDBUtility.getTpCalcResultEquityRow();
            calcResultEquity.setAccountBalance0(11.0);
            calcResultEquity.setTodayExecutedAmount0(12.0);
            calcResultEquity.setTodayUnexecutedAmount0(13.0);
            
            l_tradingPowerCalcEquity.setCalcResultEquity(calcResultEquity);
            
            l_managementForTest.setTpCalcEquity(l_tradingPowerCalcEquity);
            
            double l_dblReturn =
                l_managementForTest.calcAccountBalance(l_intSpecifiedPoint);
            
            assertEquals("-14.0", String.valueOf(l_dblReturn));
        }
        catch (Exception l_ex)
        {
            log.debug(STR_METHOD_NAME,l_ex);
            fail();
        }
        log.exiting(TEST_END + STR_METHOD_NAME);
    }
    /*
     * （２）this.信用現物判定フラグ　@==　@1(信用顧客）の場合
     */
    public void testCalcAccountBalance_C0003()
    {
        final String STR_METHOD_NAME = "testCalcAccountBalance_C0003()";
        log.entering(TEST_START + STR_METHOD_NAME);

        try
        {
            int l_intSpecifiedPoint = 0;
            
            WEB3TPPaymentRequisitionManagement l_managementForTest =
                new WEB3TPPaymentRequisitionManagementForTest2b();
            l_managementForTest.setMarginEquityJudgeFlag("1");
            
            WEB3TPTradingPowerCalcMargin l_tradingPowerCalcMargin =
                new WEB3TPTradingPowerCalcMargin();
            
            TpCalcResultMarginParams calcResultMargin = TestDBUtility.getTpCalcResultMarginRow();
            calcResultMargin.setAccountBalance0(11.0);
            calcResultMargin.setTodayExecutedAmount0(12.0);
            calcResultMargin.setTodayUnexecutedAmount0(13.0);
            
            l_tradingPowerCalcMargin.setCalcResultMargin(calcResultMargin);
            
            l_managementForTest.setTpCalcMargin(l_tradingPowerCalcMargin);
            
            double l_dblReturn =
                l_managementForTest.calcAccountBalance(l_intSpecifiedPoint);
            
            assertEquals("-14.0", String.valueOf(l_dblReturn));
        }
        catch (Exception l_ex)
        {
            log.debug(STR_METHOD_NAME,l_ex);
            fail();
        }
        log.exiting(TEST_END + STR_METHOD_NAME);
    }
    
    /*
     * nが0以上5以下でない時、0を返却する。
     */
    public void testCalcCashMarginDeposit_C0001()
    {
        final String STR_METHOD_NAME = "testCalcCashMarginDeposit_C0001()";
        log.entering(TEST_START + STR_METHOD_NAME);

        try
        {
            int l_intSpecifiedPoint = 6;
            
            double l_dblReturn =
                l_management.calcCashMarginDeposit(l_intSpecifiedPoint);
            
            assertEquals("0.0", String.valueOf(l_dblReturn));
        }
        catch (Exception l_ex)
        {
            log.debug(STR_METHOD_NAME,l_ex);
            fail();
        }
        log.exiting(TEST_END + STR_METHOD_NAME);
    }
    /*
     * （a）this.信用現物判定フラグ　@==　@0(現物顧客)の場合
     */
    public void testCalcCashMarginDeposit_C0002()
    {
        final String STR_METHOD_NAME = "testCalcCashMarginDeposit_C0002()";
        log.entering(TEST_START + STR_METHOD_NAME);

        try
        {
            int l_intSpecifiedPoint = 0;
            
            WEB3TPPaymentRequisitionManagement l_managementForTest =
                new WEB3TPPaymentRequisitionManagementForTest2b();
            l_managementForTest.setMarginEquityJudgeFlag("0");
            
            double l_dblReturn =
                l_managementForTest.calcCashMarginDeposit(l_intSpecifiedPoint);
            
            assertEquals("0.0", String.valueOf(l_dblReturn));
        }
        catch (Exception l_ex)
        {
            log.debug(STR_METHOD_NAME,l_ex);
            fail();
        }
        log.exiting(TEST_END + STR_METHOD_NAME);
    }
    /*
     * （b）this.信用現物判定フラグ　@==　@1(信用顧客)の場合
     * 指定日 = 0
     * this.入出金注文単位一覧(預り金⇒保証金)(i).get受渡日（）　@<=　@this.get期日(n)かつ
     * this.入出金注文単位一覧(預り金⇒保証金)(i).get発注日（）　@>=　@this.get期日(0)の場合
     * this.入出金注文単位一覧(保証金⇒預り金)(i).get受渡日（）　@<=　@this.get期日(n)かつ
     * this.入出金注文単位一覧(保証金⇒預り金)(i).get発注日（）　@>=　@this.get期日(0)の場合、以下の処理を行う。
     */
    public void testCalcCashMarginDeposit_C0003()
    {
        final String STR_METHOD_NAME = "testCalcCashMarginDeposit_C0003()";
        log.entering(TEST_START + STR_METHOD_NAME);

        try
        {
            int l_intSpecifiedPoint = 0;
            
            WEB3TPPaymentRequisitionManagement l_managementForTest =
                new WEB3TPPaymentRequisitionManagementForTest2b();
            l_managementForTest.setMarginEquityJudgeFlag("1");
            
            TpCashBalanceParams tpCashBalanceParams = TestDBUtility.getTpCashBalanceRow();
            tpCashBalanceParams.setCashBalance0(11);
            l_managementForTest.setTpCashBalanceParams(tpCashBalanceParams);
            
            List aioOrderUnitListFromDepositToMargins = new ArrayList();
            
            AioOrderUnitParams l_aioOrderUnitParams1 = TestDBUtility.getAioOrderUnitRow();
            l_aioOrderUnitParams1.setDeliveryDate(WEB3DateUtility.getDate("20080808", "yyyyMMdd"));
            l_aioOrderUnitParams1.setBizDate("20080811");
            l_aioOrderUnitParams1.setQuantity(3);
            aioOrderUnitListFromDepositToMargins.add(0, l_aioOrderUnitParams1);
            
            List aioOrderUnitListFromMarginToDeposits = new ArrayList();
            AioOrderUnitParams l_aioOrderUnitParams2 = TestDBUtility.getAioOrderUnitRow();
            l_aioOrderUnitParams2.setBizDate("20080811");
            l_aioOrderUnitParams2.setDeliveryDate(WEB3DateUtility.getDate("20080808", "yyyyMMdd"));
            
            l_aioOrderUnitParams2.setQuantity(5);
            aioOrderUnitListFromMarginToDeposits.add(0, l_aioOrderUnitParams2);
            
            l_managementForTest.setAioOrderUnitListFromDepositToMargins(aioOrderUnitListFromDepositToMargins);
            l_managementForTest.setAioOrderUnitListFromMarginToDeposits(aioOrderUnitListFromMarginToDeposits);
            
            double l_dblReturn =
                l_managementForTest.calcCashMarginDeposit(l_intSpecifiedPoint);
            
            assertEquals("9.0", String.valueOf(l_dblReturn));
        }
        catch (Exception l_ex)
        {
            log.debug(STR_METHOD_NAME,l_ex);
            fail();
        }
        log.exiting(TEST_END + STR_METHOD_NAME);
    }
    /*
     * （b）this.信用現物判定フラグ　@==　@1(信用顧客)の場合
     * 指定日 = 5
     * this.入出金注文単位一覧(預り金⇒保証金)(i).get受渡日（）　@<=　@this.get期日(n)の場合、
     * this.入出金注文単位一覧(保証金⇒預り金)(i).get受渡日（）　@<=　@this.get期日(n)の場合、以下の処理を行う。
     */
    public void testCalcCashMarginDeposit_C0004()
    {
        final String STR_METHOD_NAME = "testCalcCashMarginDeposit_C0004()";
        log.entering(TEST_START + STR_METHOD_NAME);

        try
        {
            int l_intSpecifiedPoint = 5;
            
            WEB3TPPaymentRequisitionManagement l_managementForTest =
                new WEB3TPPaymentRequisitionManagementForTest2b();
            l_managementForTest.setMarginEquityJudgeFlag("1");
            
            TpCashBalanceParams tpCashBalanceParams = TestDBUtility.getTpCashBalanceRow();
            tpCashBalanceParams.setCashBalance5(11);
            l_managementForTest.setTpCashBalanceParams(tpCashBalanceParams);
            
            List aioOrderUnitListFromDepositToMargins = new ArrayList();
            
            AioOrderUnitParams l_aioOrderUnitParams1 = TestDBUtility.getAioOrderUnitRow();
            l_aioOrderUnitParams1.setDeliveryDate(WEB3DateUtility.getDate("20080808", "yyyyMMdd"));
            l_aioOrderUnitParams1.setBizDate("20080811");
            l_aioOrderUnitParams1.setQuantity(3);
            aioOrderUnitListFromDepositToMargins.add(0, l_aioOrderUnitParams1);
            
            List aioOrderUnitListFromMarginToDeposits = new ArrayList();
            AioOrderUnitParams l_aioOrderUnitParams2 = TestDBUtility.getAioOrderUnitRow();
            l_aioOrderUnitParams2.setDeliveryDate(WEB3DateUtility.getDate("20080808", "yyyyMMdd"));
            l_aioOrderUnitParams2.setBizDate("20080811");
            l_aioOrderUnitParams2.setQuantity(5);
            aioOrderUnitListFromMarginToDeposits.add(0, l_aioOrderUnitParams2);
            
            l_managementForTest.setAioOrderUnitListFromDepositToMargins(aioOrderUnitListFromDepositToMargins);
            l_managementForTest.setAioOrderUnitListFromMarginToDeposits(aioOrderUnitListFromMarginToDeposits);
            
            double l_dblReturn =
                l_managementForTest.calcCashMarginDeposit(l_intSpecifiedPoint);
            
            assertEquals("9.0", String.valueOf(l_dblReturn));
        }
        catch (Exception l_ex)
        {
            log.debug(STR_METHOD_NAME,l_ex);
            fail();
        }
        log.exiting(TEST_END + STR_METHOD_NAME);
    }
    
    /*
     * （a）this.信用現物判定フラグ　@==　@0(現物顧客)の場合
     */
    public void testCalcMarginDepositRate_C0001()
    {
        final String STR_METHOD_NAME = "testCalcMarginDepositRate_C0001()";
        log.entering(TEST_START + STR_METHOD_NAME);

        try
        {
            WEB3TPPaymentRequisitionManagement l_managementForTest =
                new WEB3TPPaymentRequisitionManagementForTest2d();
            
            WEB3TPTradingPowerCalcMargin tpCalcMargin = new WEB3TPTradingPowerCalcMargin();
            
            TpCalcResultMarginParams l_tpCalcResultMarginParams = TestDBUtility.getTpCalcResultMarginRow();
            l_tpCalcResultMarginParams.setContractAmount0(0.0);
            l_tpCalcResultMarginParams.setDayRepayContractAmount0(0.0);
            tpCalcMargin.setCalcResultMargin(l_tpCalcResultMarginParams);
            
            l_managementForTest.setTpCalcMargin(tpCalcMargin);
            l_managementForTest.setMarginEquityJudgeFlag("0");
            
            double l_dblReturn =
                l_managementForTest.calcMarginDepositRate();
            
            assertEquals("0.0", String.valueOf(l_dblReturn));
        }
        catch (Exception l_ex)
        {
            log.debug(STR_METHOD_NAME,l_ex);
            fail();
        }
        log.exiting(TEST_END + STR_METHOD_NAME);
    }
    /*
     * （b）this.信用現物判定フラグ　@==　@1(信用顧客)の場合
     * （a）「建玉代金　@=　@0」の場合
     */
    public void testCalcMarginDepositRate_C0002()
    {
        final String STR_METHOD_NAME = "testCalcMarginDepositRate_C0002()";
        log.entering(TEST_START + STR_METHOD_NAME);

        try
        {
            WEB3TPPaymentRequisitionManagement l_managementForTest =
                new WEB3TPPaymentRequisitionManagementForTest2d();
            l_managementForTest.setMarginEquityJudgeFlag("1");
            
            WEB3TPTradingPowerCalcMargin tpCalcMargin = new WEB3TPTradingPowerCalcMargin();
            
            TpCalcResultMarginParams l_tpCalcResultMarginParams = TestDBUtility.getTpCalcResultMarginRow();
            l_tpCalcResultMarginParams.setContractAmount0(0.0);
            l_tpCalcResultMarginParams.setDayRepayContractAmount0(0.0);
            tpCalcMargin.setCalcResultMargin(l_tpCalcResultMarginParams);
            
            l_managementForTest.setTpCalcMargin(tpCalcMargin);
            
            double l_dblReturn =
                l_managementForTest.calcMarginDepositRate();
            
            assertEquals("999.99", String.valueOf(l_dblReturn));
        }
        catch (Exception l_ex)
        {
            log.debug(STR_METHOD_NAME,l_ex);
            fail();
        }
        log.exiting(TEST_END + STR_METHOD_NAME);
    }
    /*
     * （b）this.信用現物判定フラグ　@==　@1(信用顧客)の場合
     * （a）「建玉代金　@=　@0」の場合
     */
    public void testCalcMarginDepositRate_C0003()
    {
        final String STR_METHOD_NAME = "testCalcMarginDepositRate_C0003()";
        log.entering(TEST_START + STR_METHOD_NAME);

        try
        {
            WEB3TPPaymentRequisitionManagement l_managementForTest =
                new WEB3TPPaymentRequisitionManagementForTest2d();
            l_managementForTest.setMarginEquityJudgeFlag("1");
            
            WEB3TPTradingPowerCalcMargin tpCalcMargin = new WEB3TPTradingPowerCalcMargin();
            
            TpCalcResultMarginParams l_tpCalcResultMarginParams = TestDBUtility.getTpCalcResultMarginRow();
            l_tpCalcResultMarginParams.setContractAmount0(11.0);
            l_tpCalcResultMarginParams.setDayRepayContractAmount0(12.0);
            tpCalcMargin.setCalcResultMargin(l_tpCalcResultMarginParams);
            
            l_managementForTest.setTpCalcMargin(tpCalcMargin);
            
            double l_dblReturn =
                l_managementForTest.calcMarginDepositRate();
            
            assertEquals("21.73", String.valueOf(l_dblReturn));
        }
        catch (Exception l_ex)
        {
            log.debug(STR_METHOD_NAME,l_ex);
            fail();
        }
        log.exiting(TEST_END + STR_METHOD_NAME);
    }
    
    /*
     * （１）this.信用現物判定フラグ　@==　@0(現物顧客）の場合
     */
    public void testGetTodayRepayContractAmount_C0001()
    {
        final String STR_METHOD_NAME = "testGetTodayRepayContractAmount_C0001()";
        log.entering(TEST_START + STR_METHOD_NAME);

        try
        {

            WEB3TPPaymentRequisitionManagement l_managementForTest =
                new WEB3TPPaymentRequisitionManagementForTest2b();
            l_managementForTest.setMarginEquityJudgeFlag("0");
            
            double l_dblReturn =
                l_managementForTest.getTodayRepayContractAmount();
            
            assertEquals("0.0", String.valueOf(l_dblReturn));
        }
        catch (Exception l_ex)
        {
            log.debug(STR_METHOD_NAME,l_ex);
            fail();
        }
        log.exiting(TEST_END + STR_METHOD_NAME);
    }
    /*
     * （２）this.信用現物判定フラグ　@==　@1(信用顧客）の場合
     */
    public void testGetTodayRepayContractAmount_C0002()
    {
        final String STR_METHOD_NAME = "testGetTodayRepayContractAmount_C0002()";
        log.entering(TEST_START + STR_METHOD_NAME);

        try
        {
            WEB3TPPaymentRequisitionManagement l_managementForTest =
                new WEB3TPPaymentRequisitionManagementForTest2b();
            l_managementForTest.setMarginEquityJudgeFlag("1");
            
            TpCalcResultMarginDetailParams calcResultDetailMargin = TestDBUtility.getTpCalcResultMarginDetailRow();
            calcResultDetailMargin.setTodayRepayContractAmount(5.0);
            
            WEB3TPTradingPowerCalcMargin tpCalcMargin = new WEB3TPTradingPowerCalcMargin();
            tpCalcMargin.setCalcResultDetailMargin(calcResultDetailMargin);
            l_managementForTest.setTpCalcMargin(tpCalcMargin);
            
            double l_dblReturn =
                l_managementForTest.getTodayRepayContractAmount();
            
            assertEquals("5.0", String.valueOf(l_dblReturn));
        }
        catch (Exception l_ex)
        {
            log.debug(STR_METHOD_NAME,l_ex);
            fail();
        }
        log.exiting(TEST_END + STR_METHOD_NAME);
    }

    /*
     * （a）this.信用現物判定フラグ　@==　@0(現物顧客)の場合
     */
    public void testCalcMarginDepositInDe_C0001()
    {
        final String STR_METHOD_NAME = "testCalcMarginDepositInDe_C0001()";
        log.entering(TEST_START + STR_METHOD_NAME);

        try
        {
            WEB3TPPaymentRequisitionManagement l_managementForTest =
                new WEB3TPPaymentRequisitionManagementForTest2c();
            l_managementForTest.setMarginEquityJudgeFlag("0");
            
            WEB3TPTradingPowerCalcMargin tpCalcMargin = new WEB3TPTradingPowerCalcMargin();
            
            TpCalcResultMarginDetailParams calcResultDetailMargin = TestDBUtility.getTpCalcResultMarginDetailRow();
            calcResultDetailMargin.setSubstituteAssetOldDayValue(5.0);
            tpCalcMargin.setCalcResultDetailMargin(calcResultDetailMargin);
            
            l_managementForTest.setTpCalcMargin(tpCalcMargin);
            
            double l_dblReturn =
                l_managementForTest.calcMarginDepositInDe();
            
            assertEquals("0.0", String.valueOf(l_dblReturn));
        }
        catch (Exception l_ex)
        {
            log.debug(STR_METHOD_NAME,l_ex);
            fail();
        }
        log.exiting(TEST_END + STR_METHOD_NAME);
    }
    /*
     * （b）this.信用現物判定フラグ　@==　@1(信用顧客)の場合
     * this.入金請求管理Params == null 
     */
    public void testCalcMarginDepositInDe_C0002()
    {
        final String STR_METHOD_NAME = "testCalcMarginDepositInDe_C0002()";
        log.entering(TEST_START + STR_METHOD_NAME);

        try
        {
            WEB3TPPaymentRequisitionManagement l_managementForTest =
                new WEB3TPPaymentRequisitionManagementForTest2c();
            l_managementForTest.setMarginEquityJudgeFlag("1");
            
            WEB3TPTradingPowerCalcMargin tpCalcMargin = new WEB3TPTradingPowerCalcMargin();
            
            TpCalcResultMarginDetailParams calcResultDetailMargin = TestDBUtility.getTpCalcResultMarginDetailRow();
            calcResultDetailMargin.setSubstituteAssetOldDayValue(5.0);
            tpCalcMargin.setCalcResultDetailMargin(calcResultDetailMargin);
            
            l_managementForTest.setTpCalcMargin(tpCalcMargin);
            
            double l_dblReturn =
                l_managementForTest.calcMarginDepositInDe();
            
            assertEquals("0.0", String.valueOf(l_dblReturn));
        }
        catch (Exception l_ex)
        {
            log.debug(STR_METHOD_NAME,l_ex);
            fail();
        }
        log.exiting(TEST_END + STR_METHOD_NAME);
    }
    /*
     * （b）this.信用現物判定フラグ　@==　@1(信用顧客)の場合
     * this.入金請求管理Params != null 
     */
    public void testCalcMarginDepositInDe_C0003()
    {
        final String STR_METHOD_NAME = "testCalcMarginDepositInDe_C0003()";
        log.entering(TEST_START + STR_METHOD_NAME);

        try
        {
            WEB3TPPaymentRequisitionManagement l_managementForTest =
                new WEB3TPPaymentRequisitionManagementForTest2c();
            l_managementForTest.setMarginEquityJudgeFlag("1");
            
            WEB3TPTradingPowerCalcMargin tpCalcMargin = new WEB3TPTradingPowerCalcMargin();
            
            TpCalcResultMarginDetailParams calcResultDetailMargin = TestDBUtility.getTpCalcResultMarginDetailRow();
            calcResultDetailMargin.setSubstituteAssetOldDayValue(5.0);
            tpCalcMargin.setCalcResultDetailMargin(calcResultDetailMargin);
            
            l_managementForTest.setTpCalcMargin(tpCalcMargin);
            
            PaymentRequisitMngParams paymentRequisitMngParams = TestDBUtility.getPaymentRequisitMngParams();
            paymentRequisitMngParams.setCashMarginDeposit(21.0);
            paymentRequisitMngParams.setSubstituteSecurityAsset(22.0);
            l_managementForTest.setPaymentRequisitMngParams(paymentRequisitMngParams);
            
            double l_dblReturn =
                l_managementForTest.calcMarginDepositInDe();
            
            assertEquals("-27.0", String.valueOf(l_dblReturn));
        }
        catch (Exception l_ex)
        {
            log.debug(STR_METHOD_NAME,l_ex);
            fail();
        }
        log.exiting(TEST_END + STR_METHOD_NAME);
    }
    
    /*
     * （a）this.信用現物判定フラグ　@==　@0(現物顧客)の場合
     */
    public void testCalcMarginDepositInDeExpect_C0001()
    {
        final String STR_METHOD_NAME = "testCalcMarginDepositInDeExpect_C0001()";
        log.entering(TEST_START + STR_METHOD_NAME);

        try
        {
            WEB3TPPaymentRequisitionManagement l_managementForTest =
                new WEB3TPPaymentRequisitionManagementForTest2c();
            l_managementForTest.setMarginEquityJudgeFlag("0");
            
            WEB3TPTradingPowerCalcMargin tpCalcMargin = new WEB3TPTradingPowerCalcMargin();
            
            TpCalcResultMarginParams calcResultMargin = TestDBUtility.getTpCalcResultMarginRow();
            calcResultMargin.setCashMarginDeposit0(5.0);
            calcResultMargin.setSubstituteSecurityAsset1(6.0);
            calcResultMargin.setSubstituteSecurityAsset1(7.0);
            tpCalcMargin.setCalcResultMargin(calcResultMargin);
            
            l_managementForTest.setTpCalcMargin(tpCalcMargin);
            
            double l_dblReturn =
                l_managementForTest.calcMarginDepositInDeExpect();
            
            assertEquals("0.0", String.valueOf(l_dblReturn));
        }
        catch (Exception l_ex)
        {
            log.debug(STR_METHOD_NAME,l_ex);
            fail();
        }
        log.exiting(TEST_END + STR_METHOD_NAME);
    }
    /*
     * （b）this.信用現物判定フラグ　@==　@1(信用顧客)の場合
     */
    public void testCalcMarginDepositInDeExpect_C0002()
    {
        final String STR_METHOD_NAME = "testCalcMarginDepositInDeExpect_C0002()";
        log.entering(TEST_START + STR_METHOD_NAME);

        try
        {
            WEB3TPPaymentRequisitionManagement l_managementForTest =
                new WEB3TPPaymentRequisitionManagementForTest2c();
            l_managementForTest.setMarginEquityJudgeFlag("1");
            
            WEB3TPTradingPowerCalcMargin tpCalcMargin = new WEB3TPTradingPowerCalcMargin();
            
            TpCalcResultMarginParams calcResultMargin = TestDBUtility.getTpCalcResultMarginRow();
            calcResultMargin.setCashMarginDeposit0(5.0);
            calcResultMargin.setSubstituteSecurityAsset1(6.0);
            calcResultMargin.setSubstituteSecurityAsset0(7.0);
            tpCalcMargin.setCalcResultMargin(calcResultMargin);
            
            l_managementForTest.setTpCalcMargin(tpCalcMargin);
            
            double l_dblReturn =
                l_managementForTest.calcMarginDepositInDeExpect();
            
            assertEquals("-1.0", String.valueOf(l_dblReturn));
        }
        catch (Exception l_ex)
        {
            log.debug(STR_METHOD_NAME,l_ex);
            fail();
        }
        log.exiting(TEST_END + STR_METHOD_NAME);
    }
    
    /**
     * WEB3TPPaymentRequisitionManagementForTest2a
     */
    private class WEB3TPPaymentRequisitionManagementForTest2a extends WEB3TPPaymentRequisitionManagement
    {
        protected double calcAccountCalculate(int l_intSpecifiedPoint)
        {
            double l_dblReturn = 0;
            if (l_intSpecifiedPoint == 0)
            {
                l_dblReturn = 0;
            }
            if (l_intSpecifiedPoint == 1)
            {
                l_dblReturn = 1;
            }
            return l_dblReturn;
        }
        protected double getDayTradeRestraint(int l_intSpecifiedPoint)
        {
            double l_dblReturn = 0;
            if (l_intSpecifiedPoint == 0)
            {
                l_dblReturn = 10;
            }
            if (l_intSpecifiedPoint == 1)
            {
                l_dblReturn = 11;
            }
            return l_dblReturn;
        }
        protected double calcAccountBalanceFromMarginDeposit(int l_intSpecifiedPoint)
        {
            double l_dblReturn = 0;
            if (l_intSpecifiedPoint == 0)
            {
                l_dblReturn = 20;
            }
            if (l_intSpecifiedPoint == 1)
            {
                l_dblReturn = 21;
            }
            return l_dblReturn;
        }
    }
    /**
     * WEB3TPPaymentRequisitionManagementForTest2b
     */
    private class WEB3TPPaymentRequisitionManagementForTest2b extends WEB3TPPaymentRequisitionManagement
    {
        public Date getDate(int l_intSpecifiedPoint)
        {
            return WEB3DateUtility.getDate("20080808", "yyyyMMdd");
        }
    }
    /**
     * WEB3TPPaymentRequisitionManagementForTest2c
     */
    private class WEB3TPPaymentRequisitionManagementForTest2c extends WEB3TPPaymentRequisitionManagement
    {
        protected double calcAccountBalance(int l_intSpecifiedPoint)
        {
            return 12.0;
        }
        protected double calcCashMarginDeposit(int l_intSpecifiedPoint)
        {
            return 11.0;
        }
    }
    /**
     * WEB3TPPaymentRequisitionManagementForTest2d
     */
    private class WEB3TPPaymentRequisitionManagementForTest2d extends WEB3TPPaymentRequisitionManagement
    {
        protected double calcRealMargin()
        {
            return 5.0;
        }
    }
    
    
    //(calc第一水準追証当日消し込み換算額)
    //（a）this.信用現物判定フラグ　@==　@0(現物顧客)の場合
    public void testCalcFirstAdddepositEliminateAmount_C0001()
    {
        final String STR_METHOD_NAME = "testCalcFirstAdddepositEliminateAmount_C0001()";
        log.entering(TEST_START + STR_METHOD_NAME);

        try
        {
            WEB3TPPaymentRequisitionManagement l_paymentRequisitionManagement =
                new WEB3TPPaymentRequisitionManagement();
            
            //this.信用現物判定フラグ　@==　@0(現物顧客)の場合 
            l_paymentRequisitionManagement.marginEquityJudgeFlag = "0";

            double l_dblReturnValue =
                l_paymentRequisitionManagement.calcFirstAdddepositEliminateAmount();

            assertEquals(0, l_dblReturnValue, 0);
        }
        catch (Exception l_ex)
        {
            log.error(STR_METHOD_NAME, l_ex);
            fail();
        }

        log.exiting(TEST_END + STR_METHOD_NAME);
    }
    
    //(calc第一水準追証当日消し込み換算額)
    //this.資産余力情報<信用顧客>.get余力計算条件.get会社部店別余力計算条件（”today.clearance.determination.div”）
    //　@==　@NULLの場合
    public void testCalcFirstAdddepositEliminateAmount_C0002()
    {
        final String STR_METHOD_NAME = "testCalcFirstAdddepositEliminateAmount_C0002()";
        log.entering(TEST_START + STR_METHOD_NAME);
        
        l_strFlag = STR_METHOD_NAME;
       
        try
        {
            WEB3TPPaymentRequisitionManagementForTest3 l_paymentRequisitionManagementForTest =
                new WEB3TPPaymentRequisitionManagementForTest3();
            
            //this.信用現物判定フラグ　@==　@1(信用顧客)の場合 
            l_paymentRequisitionManagementForTest.marginEquityJudgeFlag = "1";

            WEB3TPCalcConditionForTest l_calcConditionForTest = new WEB3TPCalcConditionForTest();
            
            WEB3TPTradingPowerCalcMargin l_tpCalcMargin = new WEB3TPTradingPowerCalcMargin();
            l_tpCalcMargin.calcCondition = l_calcConditionForTest;
            
            l_paymentRequisitionManagementForTest.tpCalcMargin = l_tpCalcMargin;
            
            double l_dblReturnValue =
                l_paymentRequisitionManagementForTest.calcFirstAdddepositEliminateAmount();

            assertEquals(0, l_dblReturnValue, 0);
        }
        catch (Exception l_ex)
        {
            log.error(STR_METHOD_NAME, l_ex);
            fail();
        }

        log.exiting(TEST_END + STR_METHOD_NAME);
    }
    
    //(calc第一水準追証当日消し込み換算額)
    //this.資産余力情報<信用顧客>.get余力計算条件.get会社部店別余力計算条件（”today.clearance.determination.div”）
    //　@==　@"2"の場合
    public void testCalcFirstAdddepositEliminateAmount_C0003()
    {
        final String STR_METHOD_NAME = "testCalcFirstAdddepositEliminateAmount_C0003()";
        log.entering(TEST_START + STR_METHOD_NAME);
        
        l_strFlag = STR_METHOD_NAME;
       
        try
        {
            WEB3TPPaymentRequisitionManagementForTest3 l_paymentRequisitionManagementForTest =
                new WEB3TPPaymentRequisitionManagementForTest3();
            
            //this.信用現物判定フラグ　@==　@1(信用顧客)の場合 
            l_paymentRequisitionManagementForTest.marginEquityJudgeFlag = "1";

            WEB3TPCalcConditionForTest l_calcConditionForTest = new WEB3TPCalcConditionForTest();
            
            WEB3TPTradingPowerCalcMargin l_tpCalcMargin = new WEB3TPTradingPowerCalcMargin();
            l_tpCalcMargin.calcCondition = l_calcConditionForTest;
            
            l_paymentRequisitionManagementForTest.tpCalcMargin = l_tpCalcMargin;
            
            double l_dblReturnValue =
                l_paymentRequisitionManagementForTest.calcFirstAdddepositEliminateAmount();

            assertEquals(0, l_dblReturnValue, 0);
        }
        catch (Exception l_ex)
        {
            log.error(STR_METHOD_NAME, l_ex);
            fail();
        }

        log.exiting(TEST_END + STR_METHOD_NAME);
    }
    
    //(calc第一水準追証当日消し込み換算額)
    //this.資産余力情報<信用顧客>.get余力計算条件.get会社部店別余力計算条件（”today.clearance.determination.div”）
    //　@==　@"1"の場合
    //this.入金請求管理Params == null の場合
    public void testCalcFirstAdddepositEliminateAmount_C0004()
    {
        final String STR_METHOD_NAME = "testCalcFirstAdddepositEliminateAmount_C0004()";
        log.entering(TEST_START + STR_METHOD_NAME);
        
        l_strFlag = STR_METHOD_NAME;
       
        try
        {
            WEB3TPPaymentRequisitionManagementForTest3 l_paymentRequisitionManagementForTest =
                new WEB3TPPaymentRequisitionManagementForTest3();
            
            //this.信用現物判定フラグ　@==　@1(信用顧客)の場合 
            l_paymentRequisitionManagementForTest.marginEquityJudgeFlag = "1";

            WEB3TPCalcConditionForTest l_calcConditionForTest = new WEB3TPCalcConditionForTest();
            
            WEB3TPTradingPowerCalcMargin l_tpCalcMargin = new WEB3TPTradingPowerCalcMargin();
            l_tpCalcMargin.calcCondition = l_calcConditionForTest;
            
            l_paymentRequisitionManagementForTest.tpCalcMargin = l_tpCalcMargin;
            
            l_paymentRequisitionManagementForTest.paymentRequisitMngParams = null;
            
            double l_dblReturnValue =
                l_paymentRequisitionManagementForTest.calcFirstAdddepositEliminateAmount();

            assertEquals(0, l_dblReturnValue, 0);
        }
        catch (Exception l_ex)
        {
            log.error(STR_METHOD_NAME, l_ex);
            fail();
        }

        log.exiting(TEST_END + STR_METHOD_NAME);
    }
    
    //(calc第一水準追証当日消し込み換算額)
    //this.資産余力情報<信用顧客>.get余力計算条件.get会社部店別余力計算条件（”today.clearance.determination.div”）
    //　@==　@"1"の場合
    //this.入金請求管理Params != null の場合
    public void testCalcFirstAdddepositEliminateAmount_C0005()
    {
        final String STR_METHOD_NAME = "testCalcFirstAdddepositEliminateAmount_C0005()";
        log.entering(TEST_START + STR_METHOD_NAME);
        
        l_strFlag = STR_METHOD_NAME;
       
        try
        {
            WEB3TPPaymentRequisitionManagementForTest3 l_paymentRequisitionManagementForTest =
                new WEB3TPPaymentRequisitionManagementForTest3();
            
            //this.信用現物判定フラグ　@==　@1(信用顧客)の場合 
            l_paymentRequisitionManagementForTest.marginEquityJudgeFlag = "1";

            WEB3TPCalcConditionForTest l_calcConditionForTest = new WEB3TPCalcConditionForTest();
            WEB3TPTradingPowerCalcMargin l_tpCalcMargin = new WEB3TPTradingPowerCalcMargin();
            l_tpCalcMargin.calcCondition = l_calcConditionForTest;
            l_paymentRequisitionManagementForTest.tpCalcMargin = l_tpCalcMargin;
            
            l_paymentRequisitionManagementForTest.paymentRequisitMngParams = new PaymentRequisitMngParams();
            l_paymentRequisitionManagementForTest.paymentRequisitMngParams.setFirstDepositAmount(6);
            l_paymentRequisitionManagementForTest.paymentRequisitMngParams.setFirstSettlement(10);
            
            double l_dblReturnValue =
                l_paymentRequisitionManagementForTest.calcFirstAdddepositEliminateAmount();

            assertEquals(4, l_dblReturnValue, 0);
        }
        catch (Exception l_ex)
        {
            log.error(STR_METHOD_NAME, l_ex);
            fail();
        }

        log.exiting(TEST_END + STR_METHOD_NAME);
    }
    
    //(calc当日保証金入金額)
    //（a）this.信用現物判定フラグ　@==　@0(現物顧客)の場合 
    public void testCalcTodayDepositPaymentAmount_C0001()
    {
        final String STR_METHOD_NAME = "testCalcTodayDepositPaymentAmount_C0001()";
        log.entering(TEST_START + STR_METHOD_NAME);

        try
        {
            WEB3TPPaymentRequisitionManagement l_paymentRequisitionManagement =
                new WEB3TPPaymentRequisitionManagement();
            
            //this.信用現物判定フラグ　@==　@0(現物顧客)の場合 
            l_paymentRequisitionManagement.marginEquityJudgeFlag = "0";

            double l_dblReturnValue =
                l_paymentRequisitionManagement.calcTodayDepositPaymentAmount();

            assertEquals(0, l_dblReturnValue, 0);
        }
        catch (Exception l_ex)
        {
            log.error(STR_METHOD_NAME, l_ex);
            fail();
        }

        log.exiting(TEST_END + STR_METHOD_NAME);
    }
    
    
    //(calc当日保証金入金額)
    //（a）this.信用現物判定フラグ　@==　@1(信用顧客)の場合
    public void testCalcTodayDepositPaymentAmount_C0002()
    {
        final String STR_METHOD_NAME = "testCalcTodayDepositPaymentAmount_C0002()";
        log.entering(TEST_START + STR_METHOD_NAME);
        
        l_strFlag = STR_METHOD_NAME;
       
        try
        {
            WEB3TPPaymentRequisitionManagementForTest3 l_paymentRequisitionManagementForTest =
                new WEB3TPPaymentRequisitionManagementForTest3();
            
            //this.信用現物判定フラグ　@==　@1(信用顧客)の場合 
            l_paymentRequisitionManagementForTest.marginEquityJudgeFlag = "1";
            
            double l_dblReturnValue =
                l_paymentRequisitionManagementForTest.calcTodayDepositPaymentAmount();

            assertEquals(5, l_dblReturnValue, 0);
        }
        catch (Exception l_ex)
        {
            log.error(STR_METHOD_NAME, l_ex);
            fail();
        }

        log.exiting(TEST_END + STR_METHOD_NAME);
    }
    
    //(calc当日保証金入金額)
    //（a）this.信用現物判定フラグ　@==　@1(信用顧客)の場合
    public void testCalcTodayDepositPaymentAmount_C0003()
    {
        final String STR_METHOD_NAME = "testCalcTodayDepositPaymentAmount_C0003()";
        log.entering(TEST_START + STR_METHOD_NAME);
        
        l_strFlag = STR_METHOD_NAME;
       
        try
        {
            WEB3TPPaymentRequisitionManagementForTest3 l_paymentRequisitionManagementForTest =
                new WEB3TPPaymentRequisitionManagementForTest3();
            
            //this.信用現物判定フラグ　@==　@1(信用顧客)の場合 
            l_paymentRequisitionManagementForTest.marginEquityJudgeFlag = "1";
            
            double l_dblReturnValue =
                l_paymentRequisitionManagementForTest.calcTodayDepositPaymentAmount();

            assertEquals(0, l_dblReturnValue, 0);
        }
        catch (Exception l_ex)
        {
            log.error(STR_METHOD_NAME, l_ex);
            fail();
        }

        log.exiting(TEST_END + STR_METHOD_NAME);
    }
    
    
    
    
    //(calc当日保証金入金額（見込金額）)
    //（a）this.信用現物判定フラグ　@==　@0(現物顧客)の場合 
    public void testCalcTodayDepositPaymentAmountExpect_C0001()
    {
        final String STR_METHOD_NAME = "testCalcTodayDepositPaymentAmountExpect_C0001()";
        log.entering(TEST_START + STR_METHOD_NAME);

        try
        {
            WEB3TPPaymentRequisitionManagement l_paymentRequisitionManagement =
                new WEB3TPPaymentRequisitionManagement();
            
            //this.信用現物判定フラグ　@==　@0(現物顧客)の場合 
            l_paymentRequisitionManagement.marginEquityJudgeFlag = "0";

            double l_dblReturnValue =
                l_paymentRequisitionManagement.calcTodayDepositPaymentAmountExpect();

            assertEquals(0, l_dblReturnValue, 0);
        }
        catch (Exception l_ex)
        {
            log.error(STR_METHOD_NAME, l_ex);
            fail();
        }

        log.exiting(TEST_END + STR_METHOD_NAME);
    }
    
    //(calc当日保証金入金額（見込金額）)
    //（a）this.信用現物判定フラグ　@==　@1(信用顧客)の場合
    public void testCalcTodayDepositPaymentAmountExpect_C0002()
    {
        final String STR_METHOD_NAME = "testCalcTodayDepositPaymentAmountExpect_C0002()";
        log.entering(TEST_START + STR_METHOD_NAME);
        
        l_strFlag = STR_METHOD_NAME;
       
        try
        {
            WEB3TPPaymentRequisitionManagementForTest3 l_paymentRequisitionManagementForTest =
                new WEB3TPPaymentRequisitionManagementForTest3();
            
            //this.信用現物判定フラグ　@==　@1(信用顧客)の場合 
            l_paymentRequisitionManagementForTest.marginEquityJudgeFlag = "1";
            
            double l_dblReturnValue =
                l_paymentRequisitionManagementForTest.calcTodayDepositPaymentAmountExpect();

            assertEquals(5, l_dblReturnValue, 0);
        }
        catch (Exception l_ex)
        {
            log.error(STR_METHOD_NAME, l_ex);
            fail();
        }

        log.exiting(TEST_END + STR_METHOD_NAME);
    }
    
    //(calc当日保証金入金額（見込金額）)
    //（a）this.信用現物判定フラグ　@==　@1(信用顧客)の場合
    public void testCalcTodayDepositPaymentAmountExpect_C0003()
    {
        final String STR_METHOD_NAME = "testCalcTodayDepositPaymentAmountExpect_C0003()";
        log.entering(TEST_START + STR_METHOD_NAME);
        
        l_strFlag = STR_METHOD_NAME;
       
        try
        {
            WEB3TPPaymentRequisitionManagementForTest3 l_paymentRequisitionManagementForTest =
                new WEB3TPPaymentRequisitionManagementForTest3();
            
            //this.信用現物判定フラグ　@==　@1(信用顧客)の場合 
            l_paymentRequisitionManagementForTest.marginEquityJudgeFlag = "1";
            
            double l_dblReturnValue =
                l_paymentRequisitionManagementForTest.calcTodayDepositPaymentAmountExpect();

            assertEquals(0, l_dblReturnValue, 0);
        }
        catch (Exception l_ex)
        {
            log.error(STR_METHOD_NAME, l_ex);
            fail();
        }

        log.exiting(TEST_END + STR_METHOD_NAME);
    }
    
    
    //(calc実質保証金)calcRealMargin
    //（a）this.信用現物判定フラグ　@==　@0(現物顧客)の場合
    public void testCalcRealMargin_C0001()
    {
        final String STR_METHOD_NAME = "testCalcRealMargin_C0001()";
        log.entering(TEST_START + STR_METHOD_NAME);

        try
        {
            WEB3TPPaymentRequisitionManagement l_paymentRequisitionManagement =
                new WEB3TPPaymentRequisitionManagement();
            
            //this.信用現物判定フラグ　@==　@0(現物顧客)の場合 
            l_paymentRequisitionManagement.marginEquityJudgeFlag = "0";

            double l_dblReturnValue =
                l_paymentRequisitionManagement.calcRealMargin();

            assertEquals(0, l_dblReturnValue, 0);
        }
        catch (Exception l_ex)
        {
            log.error(STR_METHOD_NAME, l_ex);
            fail();
        }

        log.exiting(TEST_END + STR_METHOD_NAME);
    }
    
    //(calc実質保証金)calcRealMargin
    //（a）this.信用現物判定フラグ　@==　@1(信用顧客)の場合
    public void testCalcRealMargin_C0002()
    {
        final String STR_METHOD_NAME = "testCalcRealMargin_C0002()";
        log.entering(TEST_START + STR_METHOD_NAME);
        
        l_strFlag = STR_METHOD_NAME;
       
        try
        {
            WEB3TPPaymentRequisitionManagementForTest3D l_paymentRequisitionManagementForTest =
                new WEB3TPPaymentRequisitionManagementForTest3D();
            
            //this.信用現物判定フラグ　@==　@1(信用顧客)の場合 
            l_paymentRequisitionManagementForTest.marginEquityJudgeFlag = "1";

            TpCalcResultMarginParams l_calcResultMarginParams = new TpCalcResultMarginParams();
            l_calcResultMarginParams.setSubstituteSecurityAsset0(20);
            l_calcResultMarginParams.setContractAssetProfitLoss(3);//contract_asset_profit_loss
            l_calcResultMarginParams.setContractTotalCost(5);//contract_total_cost
            l_calcResultMarginParams.setUndeliContractLoss0(10);//undeli_contract_loss_0
            
            WEB3TPTradingPowerCalcMargin l_tpCalcMargin = new WEB3TPTradingPowerCalcMargin();
            l_tpCalcMargin.calcResultMargin = l_calcResultMarginParams;
            
            l_paymentRequisitionManagementForTest.tpCalcMargin = l_tpCalcMargin;
            
            double l_dblReturnValue =
                l_paymentRequisitionManagementForTest.calcRealMargin();

            assertEquals(85, l_dblReturnValue, 0);
        }
        catch (Exception l_ex)
        {
            log.error(STR_METHOD_NAME, l_ex);
            fail();
        }

        log.exiting(TEST_END + STR_METHOD_NAME);
    }
    
    //(calc実質保証金)calcRealMargin
    //（a）this.信用現物判定フラグ　@==　@1(信用顧客)の場合
    public void testCalcRealMargin_C0003()
    {
        final String STR_METHOD_NAME = "testCalcRealMargin_C0003()";
        log.entering(TEST_START + STR_METHOD_NAME);
        
        l_strFlag = STR_METHOD_NAME;
       
        try
        {
            WEB3TPPaymentRequisitionManagementForTest3D l_paymentRequisitionManagementForTest =
                new WEB3TPPaymentRequisitionManagementForTest3D();
            
            //this.信用現物判定フラグ　@==　@1(信用顧客)の場合 
            l_paymentRequisitionManagementForTest.marginEquityJudgeFlag = "1";

            TpCalcResultMarginParams l_calcResultMarginParams = new TpCalcResultMarginParams();
            l_calcResultMarginParams.setSubstituteSecurityAsset0(20);
            l_calcResultMarginParams.setContractAssetProfitLoss(-3);//contract_asset_profit_loss
            l_calcResultMarginParams.setContractTotalCost(5);//contract_total_cost
            l_calcResultMarginParams.setUndeliContractLoss0(10);//undeli_contract_loss_0
            
            WEB3TPTradingPowerCalcMargin l_tpCalcMargin = new WEB3TPTradingPowerCalcMargin();
            l_tpCalcMargin.calcResultMargin = l_calcResultMarginParams;
            
            l_paymentRequisitionManagementForTest.tpCalcMargin = l_tpCalcMargin;
            
            double l_dblReturnValue =
                l_paymentRequisitionManagementForTest.calcRealMargin();

            assertEquals(82, l_dblReturnValue, 0);
        }
        catch (Exception l_ex)
        {
            log.error(STR_METHOD_NAME, l_ex);
            fail();
        }

        log.exiting(TEST_END + STR_METHOD_NAME);
    }
    
    
    
    
    
    
    //(calc第一水準追証未解消金額)
    //（a）this.信用現物判定フラグ　@==　@0(現物顧客)の場合
    public void testCalcFirstAdddepositUncancelAmt_C0001()
    {
        final String STR_METHOD_NAME = "testCalcFirstAdddepositUncancelAmt_C0001()";
        log.entering(TEST_START + STR_METHOD_NAME);

        try
        {
            WEB3TPPaymentRequisitionManagement l_paymentRequisitionManagement =
                new WEB3TPPaymentRequisitionManagement();
            
            //this.信用現物判定フラグ　@==　@0(現物顧客)の場合 
            l_paymentRequisitionManagement.marginEquityJudgeFlag = "0";

            double l_dblReturnValue =
                l_paymentRequisitionManagement.calcFirstAdddepositUncancelAmt();

            assertEquals(0, l_dblReturnValue, 0);
        }
        catch (Exception l_ex)
        {
            log.error(STR_METHOD_NAME, l_ex);
            fail();
        }

        log.exiting(TEST_END + STR_METHOD_NAME);
    }
    
    //(calc第一水準追証未解消金額)
    //this.入金請求管理Params == null
    public void testCalcFirstAdddepositUncancelAmt_C0002()
    {
        final String STR_METHOD_NAME = "testCalcFirstAdddepositUncancelAmt_C0002()";
        log.entering(TEST_START + STR_METHOD_NAME);
        
        l_strFlag = STR_METHOD_NAME;
       
        try
        {
            WEB3TPPaymentRequisitionManagementForTest3B l_paymentRequisitionManagementForTest =
                new WEB3TPPaymentRequisitionManagementForTest3B();
            
            //this.信用現物判定フラグ　@==　@1(信用顧客)の場合 
            l_paymentRequisitionManagementForTest.marginEquityJudgeFlag = "1";

            l_paymentRequisitionManagementForTest.paymentRequisitMngParams = null;
            
            double l_dblReturnValue =
                l_paymentRequisitionManagementForTest.calcFirstAdddepositUncancelAmt();

            assertEquals(0, l_dblReturnValue, 0);
        }
        catch (Exception l_ex)
        {
            log.error(STR_METHOD_NAME, l_ex);
            fail();
        }

        log.exiting(TEST_END + STR_METHOD_NAME);
    }
    
    //(calc第一水準追証未解消金額)
    //this.入金請求管理Params != null
    public void testCalcFirstAdddepositUncancelAmt_C0003()
    {
        final String STR_METHOD_NAME = "testCalcFirstAdddepositUncancelAmt_C0003()";
        log.entering(TEST_START + STR_METHOD_NAME);
        
        l_strFlag = STR_METHOD_NAME;
       
        try
        {
            WEB3TPPaymentRequisitionManagementForTest3B l_paymentRequisitionManagementForTest =
                new WEB3TPPaymentRequisitionManagementForTest3B();
            
            //this.信用現物判定フラグ　@==　@1(信用顧客)の場合 
            l_paymentRequisitionManagementForTest.marginEquityJudgeFlag = "1";

            l_paymentRequisitionManagementForTest.paymentRequisitMngParams = new PaymentRequisitMngParams();
            l_paymentRequisitionManagementForTest.paymentRequisitMngParams.setFirstDepositAmount(100);
            
            double l_dblReturnValue =
                l_paymentRequisitionManagementForTest.calcFirstAdddepositUncancelAmt();

            assertEquals(85, l_dblReturnValue, 0);
        }
        catch (Exception l_ex)
        {
            log.error(STR_METHOD_NAME, l_ex);
            fail();
        }

        log.exiting(TEST_END + STR_METHOD_NAME);
    }
    
    //(calc第一水準追証未解消決済必要額)
    //（a）this.信用現物判定フラグ　@==　@0(現物顧客)の場合
    public void testCalcFirstAdddepositUncancelSettleRequiredAmt_C0001()
    {
        final String STR_METHOD_NAME = "testCalcFirstAdddepositUncancelSettleRequiredAmt_C0001()";
        log.entering(TEST_START + STR_METHOD_NAME);

        try
        {
            WEB3TPPaymentRequisitionManagement l_paymentRequisitionManagement =
                new WEB3TPPaymentRequisitionManagement();
            
            //this.信用現物判定フラグ　@==　@0(現物顧客)の場合 
            l_paymentRequisitionManagement.marginEquityJudgeFlag = "0";

            double l_dblReturnValue =
                l_paymentRequisitionManagement.calcFirstAdddepositUncancelSettleRequiredAmt();

            assertEquals(0, l_dblReturnValue, 0);
        }
        catch (Exception l_ex)
        {
            log.error(STR_METHOD_NAME, l_ex);
            fail();
        }

        log.exiting(TEST_END + STR_METHOD_NAME);
    }

    //(calc第一水準追証未解消決済必要額)
    //（b）this.信用現物判定フラグ　@==　@1(信用顧客)の場合
    //this.入金請求管理Params == null の場合
    public void testCalcFirstAdddepositUncancelSettleRequiredAmt_C0002()
    {
        final String STR_METHOD_NAME = "testCalcFirstAdddepositUncancelSettleRequiredAmt_C0002()";
        log.entering(TEST_START + STR_METHOD_NAME);
       
        try
        {
            WEB3TPPaymentRequisitionManagement l_paymentRequisitionManagement =
                new WEB3TPPaymentRequisitionManagement();
            
            //this.信用現物判定フラグ　@==　@1(信用顧客)の場合 
            l_paymentRequisitionManagement.marginEquityJudgeFlag = "1";

            l_paymentRequisitionManagement.paymentRequisitMngParams = null;
            
            double l_dblReturnValue =
                l_paymentRequisitionManagement.calcFirstAdddepositUncancelSettleRequiredAmt();

            assertEquals(0, l_dblReturnValue, 0);
        }
        catch (Exception l_ex)
        {
            log.error(STR_METHOD_NAME, l_ex);
            fail();
        }

        log.exiting(TEST_END + STR_METHOD_NAME);
    }
    
    //(calc第一水準追証未解消決済必要額)
    //（b）this.信用現物判定フラグ　@==　@1(信用顧客)の場合
    //（a）第一水準追証金額　@==　@0の場合
    //this.入金請求管理Params.get第一水準追証金額（）== 0
    public void testCalcFirstAdddepositUncancelSettleRequiredAmt_C0003()
    {
        final String STR_METHOD_NAME = "testCalcFirstAdddepositUncancelSettleRequiredAmt_C0003()";
        log.entering(TEST_START + STR_METHOD_NAME);
       
        try
        {
            WEB3TPPaymentRequisitionManagement l_paymentRequisitionManagement =
                new WEB3TPPaymentRequisitionManagement();
            
            //this.信用現物判定フラグ　@==　@1(信用顧客)の場合 
            l_paymentRequisitionManagement.marginEquityJudgeFlag = "1";

            l_paymentRequisitionManagement.paymentRequisitMngParams = new PaymentRequisitMngParams();
            l_paymentRequisitionManagement.paymentRequisitMngParams.setFirstDepositAmount(0);
            
            double l_dblReturnValue =
                l_paymentRequisitionManagement.calcFirstAdddepositUncancelSettleRequiredAmt();

            assertEquals(0, l_dblReturnValue, 0);
        }
        catch (Exception l_ex)
        {
            log.error(STR_METHOD_NAME, l_ex);
            fail();
        }

        log.exiting(TEST_END + STR_METHOD_NAME);
    }
    
    
    //(calc第一水準追証未解消決済必要額)
    public void testCalcFirstAdddepositUncancelSettleRequiredAmt_C0004()
    {
        final String STR_METHOD_NAME = "testCalcFirstAdddepositUncancelSettleRequiredAmt_C0004()";
        log.entering(TEST_START + STR_METHOD_NAME);
        
        l_strFlag = STR_METHOD_NAME;
       
        try
        {
            WEB3TPPaymentRequisitionManagementForTest3 l_paymentRequisitionManagementForTest =
                new WEB3TPPaymentRequisitionManagementForTest3();
            
            //this.信用現物判定フラグ　@==　@1(信用顧客)の場合 
            l_paymentRequisitionManagementForTest.marginEquityJudgeFlag = "1";

            l_paymentRequisitionManagementForTest.paymentRequisitMngParams = new PaymentRequisitMngParams();
            l_paymentRequisitionManagementForTest.paymentRequisitMngParams.setFirstDepositAmount(10);
            l_paymentRequisitionManagementForTest.paymentRequisitMngParams.setFirstSettlement(9);
            
            double l_dblReturnValue =
                l_paymentRequisitionManagementForTest.calcFirstAdddepositUncancelSettleRequiredAmt();

            assertEquals(6, l_dblReturnValue, 0);
        }
        catch (Exception l_ex)
        {
            log.error(STR_METHOD_NAME, l_ex);
            fail();
        }

        log.exiting(TEST_END + STR_METHOD_NAME);
    }

    
    
    
    
    //(calc第一水準追証金額)
    //（a）this.信用現物判定フラグ　@==　@0(現物顧客)の場合
    public void testCalcFirstAdddepositAmount_C0001()
    {
        final String STR_METHOD_NAME = "testCalcFirstAdddepositAmount_C0001()";
        log.entering(TEST_START + STR_METHOD_NAME);
       
        try
        {
            WEB3TPPaymentRequisitionManagement l_paymentRequisitionManagement =
                new WEB3TPPaymentRequisitionManagement();
            
            //this.信用現物判定フラグ　@==　@0(現物顧客)の場合 
            l_paymentRequisitionManagement.marginEquityJudgeFlag = "0";

            double l_dblReturnValue = l_paymentRequisitionManagement.calcFirstAdddepositAmount();

            assertEquals(0, l_dblReturnValue, 0);
        }
        catch (Exception l_ex)
        {
            log.error(STR_METHOD_NAME, l_ex);
            fail();
        }

        log.exiting(TEST_END + STR_METHOD_NAME);
    }
    
    //(calc第一水準追証金額)
    //（b）this.信用現物判定フラグ　@==　@1(信用顧客)の場合
    //第二水準追証未入金　@>　@0
    public void testCalcFirstAdddepositAmount_C0002()
    {
        final String STR_METHOD_NAME = "testCalcFirstAdddepositAmount_C0002()";
        log.entering(TEST_START + STR_METHOD_NAME);

        l_strFlag = STR_METHOD_NAME;

        try
        {
            WEB3TPPaymentRequisitionManagementForTest3C l_paymentRequisitionManagementForTest =
                new WEB3TPPaymentRequisitionManagementForTest3C();
            
            //this.信用現物判定フラグ　@==　@1(信用顧客)の場合 
            l_paymentRequisitionManagementForTest.marginEquityJudgeFlag = "1";

            double l_dblReturnValue = l_paymentRequisitionManagementForTest.calcFirstAdddepositAmount();

            assertEquals(0, l_dblReturnValue, 0);
        }
        catch (Exception l_ex)
        {
            log.error(STR_METHOD_NAME, l_ex);
            fail();
        }

        log.exiting(TEST_END + STR_METHOD_NAME);
    }
    
    //(calc第一水準追証金額)
    //（b）this.信用現物判定フラグ　@==　@1(信用顧客)の場合
    //第二水準追証請求(2)　@>　@0
    public void testCalcFirstAdddepositAmount_C0003()
    {
        final String STR_METHOD_NAME = "testCalcFirstAdddepositAmount_C0003()";
        log.entering(TEST_START + STR_METHOD_NAME);

        l_strFlag = STR_METHOD_NAME;

        try
        {
            WEB3TPPaymentRequisitionManagementForTest3C l_paymentRequisitionManagementForTest =
                new WEB3TPPaymentRequisitionManagementForTest3C();
            
            //this.信用現物判定フラグ　@==　@1(信用顧客)の場合 
            l_paymentRequisitionManagementForTest.marginEquityJudgeFlag = "1";

            double l_dblReturnValue = l_paymentRequisitionManagementForTest.calcFirstAdddepositAmount();

            assertEquals(0, l_dblReturnValue, 0);
        }
        catch (Exception l_ex)
        {
            log.error(STR_METHOD_NAME, l_ex);
            fail();
        }

        log.exiting(TEST_END + STR_METHOD_NAME);
    }
    
    //(calc第一水準追証金額)
    //（b）this.信用現物判定フラグ　@==　@1(信用顧客)の場合
    //第二水準追証請求(1)　@>　@0
    public void testCalcFirstAdddepositAmount_C0004()
    {
        final String STR_METHOD_NAME = "testCalcFirstAdddepositAmount_C0004()";
        log.entering(TEST_START + STR_METHOD_NAME);

        l_strFlag = STR_METHOD_NAME;

        try
        {
            WEB3TPPaymentRequisitionManagementForTest3C l_paymentRequisitionManagementForTest =
                new WEB3TPPaymentRequisitionManagementForTest3C();
            
            //this.信用現物判定フラグ　@==　@1(信用顧客)の場合 
            l_paymentRequisitionManagementForTest.marginEquityJudgeFlag = "1";

            double l_dblReturnValue = l_paymentRequisitionManagementForTest.calcFirstAdddepositAmount();

            assertEquals(0, l_dblReturnValue, 0);
        }
        catch (Exception l_ex)
        {
            log.error(STR_METHOD_NAME, l_ex);
            fail();
        }

        log.exiting(TEST_END + STR_METHOD_NAME);
    }
    
    //(calc第一水準追証金額)
    //（b）this.信用現物判定フラグ　@==　@1(信用顧客)の場合
    //第二水準追証請求見込　@>　@0
    public void testCalcFirstAdddepositAmount_C0005()
    {
        final String STR_METHOD_NAME = "testCalcFirstAdddepositAmount_C0005()";
        log.entering(TEST_START + STR_METHOD_NAME);

        l_strFlag = STR_METHOD_NAME;

        try
        {
            WEB3TPPaymentRequisitionManagementForTest3C l_paymentRequisitionManagementForTest =
                new WEB3TPPaymentRequisitionManagementForTest3C();
            
            //this.信用現物判定フラグ　@==　@1(信用顧客)の場合 
            l_paymentRequisitionManagementForTest.marginEquityJudgeFlag = "1";

            double l_dblReturnValue = l_paymentRequisitionManagementForTest.calcFirstAdddepositAmount();

            assertEquals(0, l_dblReturnValue, 0);
        }
        catch (Exception l_ex)
        {
            log.error(STR_METHOD_NAME, l_ex);
            fail();
        }

        log.exiting(TEST_END + STR_METHOD_NAME);
    }
    
    //(calc第一水準追証金額)
    //（b）this.信用現物判定フラグ　@==　@1(信用顧客)の場合
    public void testCalcFirstAdddepositAmount_C0006()
    {
        final String STR_METHOD_NAME = "testCalcFirstAdddepositAmount_C0006()";
        log.entering(TEST_START + STR_METHOD_NAME);

        l_strFlag = STR_METHOD_NAME;

        try
        {
            TpCalcResultMarginParams l_calcResultMarginParams = new TpCalcResultMarginParams();
            l_calcResultMarginParams.setContractAmount0(2.2);
            l_calcResultMarginParams.setDayRepayContractAmount0(4.4);
            
            WEB3TPTradingPowerCalcMargin l_tpCalcMargin = new WEB3TPTradingPowerCalcMargin();
            l_tpCalcMargin.calcResultMargin = l_calcResultMarginParams;
            
            WEB3TPPaymentRequisitionManagementForTest3C l_paymentRequisitionManagementForTest =
                new WEB3TPPaymentRequisitionManagementForTest3C();
            
            l_paymentRequisitionManagementForTest.tpCalcMargin = l_tpCalcMargin;
            
            //this.信用現物判定フラグ　@==　@1(信用顧客)の場合 
            l_paymentRequisitionManagementForTest.marginEquityJudgeFlag = "1";

            double l_dblReturnValue = l_paymentRequisitionManagementForTest.calcFirstAdddepositAmount();

            assertEquals(0, l_dblReturnValue, 0);
        }
        catch (Exception l_ex)
        {
            log.error(STR_METHOD_NAME, l_ex);
            fail();
        }

        log.exiting(TEST_END + STR_METHOD_NAME);
    }
    
    //(calc第一水準追証金額)
    //（b）this.信用現物判定フラグ　@==　@1(信用顧客)の場合
    //（b）建玉代金　@!=　@0の場合
    //第一水準追証金額　@=　@ABS（MIN（実質保証金　@－　@（建玉代金　@×　@第一水準保証金維持率　@／　@100），　@0））
    public void testCalcFirstAdddepositAmount_C0007()
    {
        final String STR_METHOD_NAME = "testCalcFirstAdddepositAmount_C0007()";
        log.entering(TEST_START + STR_METHOD_NAME);

        l_strFlag = STR_METHOD_NAME;

        try
        {
            TpCalcResultMarginParams l_calcResultMarginParams = new TpCalcResultMarginParams();
            l_calcResultMarginParams.setContractAmount0(2.2);
            l_calcResultMarginParams.setDayRepayContractAmount0(4.4);
            
            WEB3TPTradingPowerCalcMargin l_tpCalcMargin = new WEB3TPTradingPowerCalcMargin();
            l_tpCalcMargin.calcResultMargin = l_calcResultMarginParams;
            
            WEB3TPPaymentRequisitionManagementForTest3C l_paymentRequisitionManagementForTest =
                new WEB3TPPaymentRequisitionManagementForTest3C();
            
            l_paymentRequisitionManagementForTest.tpCalcMargin = l_tpCalcMargin;
            
            //this.信用現物判定フラグ　@==　@1(信用顧客)の場合 
            l_paymentRequisitionManagementForTest.marginEquityJudgeFlag = "1";

            double l_dblReturnValue = l_paymentRequisitionManagementForTest.calcFirstAdddepositAmount();

            assertEquals("1.0", l_dblReturnValue + "");
        }
        catch (Exception l_ex)
        {
            log.error(STR_METHOD_NAME, l_ex);
            fail();
        }

        log.exiting(TEST_END + STR_METHOD_NAME);
    }
    
    //(calc第一水準追証金額)
    //（a）建玉代金　@==　@0の場合 
    // 　@第一水準追証金額　@=　@0 を設定し、返却する。
    public void testCalcFirstAdddepositAmount_C0008()
    {
        final String STR_METHOD_NAME = "testCalcFirstAdddepositAmount_C0008()";
        log.entering(TEST_START + STR_METHOD_NAME);

        l_strFlag = STR_METHOD_NAME;

        try
        {
            TpCalcResultMarginParams l_calcResultMarginParams = new TpCalcResultMarginParams();
            l_calcResultMarginParams.setContractAmount0(0);
            l_calcResultMarginParams.setDayRepayContractAmount0(0);
            
            WEB3TPTradingPowerCalcMargin l_tpCalcMargin = new WEB3TPTradingPowerCalcMargin();
            l_tpCalcMargin.calcResultMargin = l_calcResultMarginParams;
            
            WEB3TPPaymentRequisitionManagementForTest3C l_paymentRequisitionManagementForTest =
                new WEB3TPPaymentRequisitionManagementForTest3C();
            
            l_paymentRequisitionManagementForTest.tpCalcMargin = l_tpCalcMargin;
            
            //this.信用現物判定フラグ　@==　@1(信用顧客)の場合 
            l_paymentRequisitionManagementForTest.marginEquityJudgeFlag = "1";

            double l_dblReturnValue = l_paymentRequisitionManagementForTest.calcFirstAdddepositAmount();

            assertEquals("0.0", l_dblReturnValue + "");
        }
        catch (Exception l_ex)
        {
            log.error(STR_METHOD_NAME, l_ex);
            fail();
        }

        log.exiting(TEST_END + STR_METHOD_NAME);
    }
    
    
    
    
    //(calc第一水準追証決済必要額)
    //（a）this.信用現物判定フラグ　@==　@0(現物顧客)の場合
    public void testCalcFirstAdddepositSettlement_C0001()
    {
        final String STR_METHOD_NAME = "testCalcFirstAdddepositSettlement_C0001()";
        log.entering(TEST_START + STR_METHOD_NAME);
       
        try
        {
            WEB3TPPaymentRequisitionManagement l_paymentRequisitionManagement =
                new WEB3TPPaymentRequisitionManagement();
            
            //this.信用現物判定フラグ　@==　@0(現物顧客)の場合 
            l_paymentRequisitionManagement.marginEquityJudgeFlag = "0";

            double l_dblReturnValue = l_paymentRequisitionManagement.calcFirstAdddepositSettlement();

            assertEquals(0, l_dblReturnValue, 0);
        }
        catch (Exception l_ex)
        {
            log.error(STR_METHOD_NAME, l_ex);
            fail();
        }

        log.exiting(TEST_END + STR_METHOD_NAME);
    }
    
    //(calc第一水準追証決済必要額)
    //（b）this.信用現物判定フラグ　@==　@1(信用顧客)の場合
    //第二水準追証未入金　@>　@0
    public void testCalcFirstAdddepositSettlement_C0002()
    {
        final String STR_METHOD_NAME = "testCalcFirstAdddepositSettlement_C0002()";
        log.entering(TEST_START + STR_METHOD_NAME);

        l_strFlag = STR_METHOD_NAME;

        try
        {
            WEB3TPPaymentRequisitionManagementForTest3 l_paymentRequisitionManagementForTest =
                new WEB3TPPaymentRequisitionManagementForTest3();
            
            //this.信用現物判定フラグ　@==　@1(信用顧客)の場合 
            l_paymentRequisitionManagementForTest.marginEquityJudgeFlag = "1";

            double l_dblReturnValue = l_paymentRequisitionManagementForTest.calcFirstAdddepositSettlement();

            assertEquals(0, l_dblReturnValue, 0);
        }
        catch (Exception l_ex)
        {
            log.error(STR_METHOD_NAME, l_ex);
            fail();
        }

        log.exiting(TEST_END + STR_METHOD_NAME);
    }
    
    //(calc第一水準追証決済必要額)
    //（b）this.信用現物判定フラグ　@==　@1(信用顧客)の場合
    //第二水準追証請求(2)　@>　@0
    public void testCalcFirstAdddepositSettlement_C0003()
    {
        final String STR_METHOD_NAME = "testCalcFirstAdddepositSettlement_C0003()";
        log.entering(TEST_START + STR_METHOD_NAME);

        l_strFlag = STR_METHOD_NAME;

        try
        {
            WEB3TPPaymentRequisitionManagementForTest3 l_paymentRequisitionManagementForTest =
                new WEB3TPPaymentRequisitionManagementForTest3();
            
            //this.信用現物判定フラグ　@==　@1(信用顧客)の場合 
            l_paymentRequisitionManagementForTest.marginEquityJudgeFlag = "1";

            double l_dblReturnValue = l_paymentRequisitionManagementForTest.calcFirstAdddepositSettlement();

            assertEquals(0, l_dblReturnValue, 0);
        }
        catch (Exception l_ex)
        {
            log.error(STR_METHOD_NAME, l_ex);
            fail();
        }

        log.exiting(TEST_END + STR_METHOD_NAME);
    }
    
    //(calc第一水準追証決済必要額)
    //（b）this.信用現物判定フラグ　@==　@1(信用顧客)の場合
    //第二水準追証請求(1)　@>　@0
    public void testCalcFirstAdddepositSettlement_C0004()
    {
        final String STR_METHOD_NAME = "testCalcFirstAdddepositSettlement_C0004()";
        log.entering(TEST_START + STR_METHOD_NAME);

        l_strFlag = STR_METHOD_NAME;

        try
        {
            WEB3TPPaymentRequisitionManagementForTest3 l_paymentRequisitionManagementForTest =
                new WEB3TPPaymentRequisitionManagementForTest3();
            
            //this.信用現物判定フラグ　@==　@1(信用顧客)の場合 
            l_paymentRequisitionManagementForTest.marginEquityJudgeFlag = "1";

            double l_dblReturnValue = l_paymentRequisitionManagementForTest.calcFirstAdddepositSettlement();

            assertEquals(0, l_dblReturnValue, 0);
        }
        catch (Exception l_ex)
        {
            log.error(STR_METHOD_NAME, l_ex);
            fail();
        }

        log.exiting(TEST_END + STR_METHOD_NAME);
    }
    
    //(calc第一水準追証決済必要額)
    //（b）this.信用現物判定フラグ　@==　@1(信用顧客)の場合
    //第二水準追証請求見込　@>　@0
    public void testCalcFirstAdddepositSettlement_C0005()
    {
        final String STR_METHOD_NAME = "testCalcFirstAdddepositSettlement_C0005()";
        log.entering(TEST_START + STR_METHOD_NAME);

        l_strFlag = STR_METHOD_NAME;

        try
        {
            WEB3TPPaymentRequisitionManagementForTest3 l_paymentRequisitionManagementForTest =
                new WEB3TPPaymentRequisitionManagementForTest3();
            
            //this.信用現物判定フラグ　@==　@1(信用顧客)の場合 
            l_paymentRequisitionManagementForTest.marginEquityJudgeFlag = "1";

            double l_dblReturnValue = l_paymentRequisitionManagementForTest.calcFirstAdddepositSettlement();

            assertEquals(0, l_dblReturnValue, 0);
        }
        catch (Exception l_ex)
        {
            log.error(STR_METHOD_NAME, l_ex);
            fail();
        }

        log.exiting(TEST_END + STR_METHOD_NAME);
    }
    
    //(calc第一水準追証決済必要額)
    //（b）this.信用現物判定フラグ　@==　@1(信用顧客)の場合
    //（a）実質保証金　@＋　@第一水準追証金額　@==　@0の場合
    public void testCalcFirstAdddepositSettlement_C0006()
    {
        final String STR_METHOD_NAME = "testCalcFirstAdddepositSettlement_C0006()";
        log.entering(TEST_START + STR_METHOD_NAME);

        l_strFlag = STR_METHOD_NAME;

        try
        {
            WEB3TPPaymentRequisitionManagementForTest3 l_paymentRequisitionManagementForTest =
                new WEB3TPPaymentRequisitionManagementForTest3();
            
            //this.信用現物判定フラグ　@==　@1(信用顧客)の場合 
            l_paymentRequisitionManagementForTest.marginEquityJudgeFlag = "1";

            double l_dblReturnValue = l_paymentRequisitionManagementForTest.calcFirstAdddepositSettlement();

            assertEquals(0, l_dblReturnValue, 0);
        }
        catch (Exception l_ex)
        {
            log.error(STR_METHOD_NAME, l_ex);
            fail();
        }

        log.exiting(TEST_END + STR_METHOD_NAME);
    }
    
    //(calc第一水準追証決済必要額)
    //（b）this.信用現物判定フラグ　@==　@1(信用顧客)の場合
    //（a）実質保証金　@＋　@第一水準追証金額　@!=　@0の場合
    public void testCalcFirstAdddepositSettlement_C0007()
    {
        final String STR_METHOD_NAME = "testCalcFirstAdddepositSettlement_C0007()";
        log.entering(TEST_START + STR_METHOD_NAME);

        l_strFlag = STR_METHOD_NAME;

        try
        {
            TpCalcResultMarginParams l_calcResultMarginParams = new TpCalcResultMarginParams();
            l_calcResultMarginParams.setContractAmount0(2.2);
            l_calcResultMarginParams.setDayRepayContractAmount0(4.4);
            
            WEB3TPTradingPowerCalcMargin l_tpCalcMargin = new WEB3TPTradingPowerCalcMargin();
            l_tpCalcMargin.calcResultMargin = l_calcResultMarginParams;
            
            WEB3TPPaymentRequisitionManagementForTest3B l_paymentRequisitionManagementForTest =
                new WEB3TPPaymentRequisitionManagementForTest3B();
            
            l_paymentRequisitionManagementForTest.tpCalcMargin = l_tpCalcMargin;
            
            //this.信用現物判定フラグ　@==　@1(信用顧客)の場合 
            l_paymentRequisitionManagementForTest.marginEquityJudgeFlag = "1";

            double l_dblReturnValue = l_paymentRequisitionManagementForTest.calcFirstAdddepositSettlement();

            assertEquals(3, l_dblReturnValue, 0);
        }
        catch (Exception l_ex)
        {
            log.error(STR_METHOD_NAME, l_ex);
            fail();
        }

        log.exiting(TEST_END + STR_METHOD_NAME);
    }

    //(calc第二水準追証金額)
    //（a）this.信用現物判定フラグ　@==　@0(現物顧客)の場合
    public void testCalcSecondAdddepositAmount_C0001()
    {
        final String STR_METHOD_NAME = "testCalcSecondAdddepositAmount_C0001()";
        log.entering(TEST_START + STR_METHOD_NAME);
       
        try
        {
            WEB3TPPaymentRequisitionManagement l_paymentRequisitionManagement =
                new WEB3TPPaymentRequisitionManagement();
            
            //this.信用現物判定フラグ　@==　@0(現物顧客)の場合 
            l_paymentRequisitionManagement.marginEquityJudgeFlag = "0";

            double l_dblReturnValue = l_paymentRequisitionManagement.calcSecondAdddepositAmount();

            assertEquals(0, l_dblReturnValue, 0);
        }
        catch (Exception l_ex)
        {
            log.error(STR_METHOD_NAME, l_ex);
            fail();
        }

        log.exiting(TEST_END + STR_METHOD_NAME);
    }
    
    //(calc第二水準追証金額)
    //（b）this.信用現物判定フラグ　@==　@1(信用顧客)の場合
    //（a）保証金預託率　@>　@第二水準保証金維持率 の場合
    public void testCalcSecondAdddepositAmount_C0002()
    {
        final String STR_METHOD_NAME = "testCalcSecondAdddepositAmount_C0002()";
        log.entering(TEST_START + STR_METHOD_NAME);
       
        l_strFlag = STR_METHOD_NAME;
        
        try
        {
            WEB3TPPaymentRequisitionManagementForTest3B l_paymentRequisitionManagementForTest =
                new WEB3TPPaymentRequisitionManagementForTest3B();
            
            //this.信用現物判定フラグ　@==　@1(信用顧客)の場合 
            l_paymentRequisitionManagementForTest.marginEquityJudgeFlag = "1";

            double l_dblReturnValue = l_paymentRequisitionManagementForTest.calcSecondAdddepositAmount();

            assertEquals(0, l_dblReturnValue, 0);
        }
        catch (Exception l_ex)
        {
            log.error(STR_METHOD_NAME, l_ex);
            fail();
        }

        log.exiting(TEST_END + STR_METHOD_NAME);
    }
    
    
    
    
    //(calc第二水準追証金額)
    //（b）this.信用現物判定フラグ　@==　@1(信用顧客)の場合
    //保証金預託率　@=　@第二水準保証金維持率 の場合
    public void testCalcSecondAdddepositAmount_C0003()
    {
        final String STR_METHOD_NAME = "testCalcSecondAdddepositAmount_C0003()";
        log.entering(TEST_START + STR_METHOD_NAME);
        
        l_strFlag = STR_METHOD_NAME;
       
        try
        {
            TpCalcResultMarginParams l_calcResultMarginParams = new TpCalcResultMarginParams();
            l_calcResultMarginParams.setContractAmount0(2.2);
            l_calcResultMarginParams.setDayRepayContractAmount0(4.4);
            
            WEB3TPTradingPowerCalcMargin l_tpCalcMargin = new WEB3TPTradingPowerCalcMargin();
            l_tpCalcMargin.calcResultMargin = l_calcResultMarginParams;
            
            WEB3TPPaymentRequisitionManagementForTest3B l_paymentRequisitionManagementForTest =
                new WEB3TPPaymentRequisitionManagementForTest3B();
            
            l_paymentRequisitionManagementForTest.tpCalcMargin = l_tpCalcMargin;

            //this.信用現物判定フラグ　@==　@1(信用顧客)の場合 
            l_paymentRequisitionManagementForTest.marginEquityJudgeFlag = "1";

            double l_dblReturnValue = l_paymentRequisitionManagementForTest.calcSecondAdddepositAmount();

            assertEquals(0, l_dblReturnValue, 0);
        }
        catch (Exception l_ex)
        {
            log.error(STR_METHOD_NAME, l_ex);
            fail();
        }

        log.exiting(TEST_END + STR_METHOD_NAME);
    }
    
    public void testCalcSecondAdddepositAmount_C0004()
    {
        final String STR_METHOD_NAME = "testCalcSecondAdddepositAmount_C0004()";
        log.entering(TEST_START + STR_METHOD_NAME);
        
        l_strFlag = STR_METHOD_NAME;
       
        try
        {
            TpCalcResultMarginParams l_calcResultMarginParams = new TpCalcResultMarginParams();
            l_calcResultMarginParams.setContractAmount0(2.2);
            l_calcResultMarginParams.setDayRepayContractAmount0(4.4);
            
            WEB3TPTradingPowerCalcMargin l_tpCalcMargin = new WEB3TPTradingPowerCalcMargin();
            l_tpCalcMargin.calcResultMargin = l_calcResultMarginParams;
            
            WEB3TPPaymentRequisitionManagementForTest3B l_paymentRequisitionManagementForTest =
                new WEB3TPPaymentRequisitionManagementForTest3B();
            
            l_paymentRequisitionManagementForTest.tpCalcMargin = l_tpCalcMargin;

            //this.信用現物判定フラグ　@==　@1(信用顧客)の場合 
            l_paymentRequisitionManagementForTest.marginEquityJudgeFlag = "1";

            double l_dblReturnValue = l_paymentRequisitionManagementForTest.calcSecondAdddepositAmount();

            assertEquals("1.0", l_dblReturnValue + "");
        }
        catch (Exception l_ex)
        {
            log.error(STR_METHOD_NAME, l_ex);
            fail();
        }

        log.exiting(TEST_END + STR_METHOD_NAME);
    }

    //(calc第二水準追証決済必要額)
    //this.信用現物判定フラグ　@==　@0(現物顧客)の場合
    public void testCalcSecondAdddepositSettlement_C0001()
    {
        final String STR_METHOD_NAME = "testCalcSecondAdddepositSettlement_C0001()";
        log.entering(TEST_START + STR_METHOD_NAME);
       
        try
        {
            WEB3TPPaymentRequisitionManagement l_paymentRequisitionManagement =
                new WEB3TPPaymentRequisitionManagement();
            
            //this.信用現物判定フラグ　@==　@0(現物顧客)の場合 
            l_paymentRequisitionManagement.marginEquityJudgeFlag = "0";

            double l_dblReturnValue = l_paymentRequisitionManagement.calcSecondAdddepositSettlement();

            assertEquals(0, l_dblReturnValue, 0);
        }
        catch (Exception l_ex)
        {
            log.error(STR_METHOD_NAME, l_ex);
            fail();
        }

        log.exiting(TEST_END + STR_METHOD_NAME);
    }
    
    //(calc第二水準追証決済必要額)
    //（b）this.信用現物判定フラグ　@==　@1(信用顧客)の場合
    //（a）実質保証金　@＋　@第二水準追証金額　@==　@0の場合 
    public void testCalcSecondAdddepositSettlement_C0002()
    {
        final String STR_METHOD_NAME = "testCalcSecondAdddepositSettlement_C0002()";
        log.entering(TEST_START + STR_METHOD_NAME);
       
        try
        {
            WEB3TPPaymentRequisitionManagementForTest3 l_paymentRequisitionManagementForTest =
                new WEB3TPPaymentRequisitionManagementForTest3();
            
            //this.信用現物判定フラグ　@==　@1(信用顧客)の場合 
            l_paymentRequisitionManagementForTest.marginEquityJudgeFlag = "1";

            double l_dblReturnValue = l_paymentRequisitionManagementForTest.calcSecondAdddepositSettlement();

            assertEquals(0, l_dblReturnValue, 0);
        }
        catch (Exception l_ex)
        {
            log.error(STR_METHOD_NAME, l_ex);
            fail();
        }

        log.exiting(TEST_END + STR_METHOD_NAME);
    }
    
    //(calc第二水準追証決済必要額)
    //（b）実質保証金　@＋　@第二水準追証金額　@!=　@0の場合
    public void testCalcSecondAdddepositSettlement_C0003()
    {
        final String STR_METHOD_NAME = "testCalcSecondAdddepositSettlement_C0003()";
        log.entering(TEST_START + STR_METHOD_NAME);
        
        l_strFlag = STR_METHOD_NAME;
       
        try
        {
            TpCalcResultMarginParams l_calcResultMarginParams = new TpCalcResultMarginParams();
            l_calcResultMarginParams.setContractAmount0(2.2);
            l_calcResultMarginParams.setDayRepayContractAmount0(4.4);
            
            WEB3TPTradingPowerCalcMargin l_tpCalcMargin = new WEB3TPTradingPowerCalcMargin();
            l_tpCalcMargin.calcResultMargin = l_calcResultMarginParams;
            
            WEB3TPPaymentRequisitionManagementForTest3 l_paymentRequisitionManagementForTest =
                new WEB3TPPaymentRequisitionManagementForTest3();
            
            l_paymentRequisitionManagementForTest.tpCalcMargin = l_tpCalcMargin;

            //this.信用現物判定フラグ　@==　@1(信用顧客)の場合 
            l_paymentRequisitionManagementForTest.marginEquityJudgeFlag = "1";

            double l_dblReturnValue = l_paymentRequisitionManagementForTest.calcSecondAdddepositSettlement();

            assertEquals(3, l_dblReturnValue, 0);
        }
        catch (Exception l_ex)
        {
            log.error(STR_METHOD_NAME, l_ex);
            fail();
        }

        log.exiting(TEST_END + STR_METHOD_NAME);
    }
   
    private class WEB3TPPaymentRequisitionManagementForTest3 extends WEB3TPPaymentRequisitionManagement
    {
        //get当日返済建玉代金
        protected double getTodayRepayContractAmount()
        {
//            {
//          if (l_strFlag.equals("testCalcFirstAdddepositEliminateAmount_C0002()"))
//                return 7;
//            }
//            if (l_strFlag.equals("testCalcFirstAdddepositEliminateAmount_C0003()"))
//            {
//                return 7;
//            }
            return 8;
        }
        
        //calc保証金増減
        protected double calcMarginDepositInDe()
        {
            if (l_strFlag.equals("testCalcTodayDepositPaymentAmount_C0002()"))
            {
                return 5;
            }
            if (l_strFlag.equals("testCalcTodayDepositPaymentAmount_C0003()"))
            {
                return -5;
            }
            return 0;
        }
        
        //calc保証金増減(見込金額)
        protected double calcMarginDepositInDeExpect()
        {
            if (l_strFlag.equals("testCalcTodayDepositPaymentAmountExpect_C0002()"))
            {
                return 5;
            }
            if (l_strFlag.equals("testCalcTodayDepositPaymentAmountExpect_C0003()"))
            {
                return -5;
            }
            return 0;
        }
        
        //calc第一水準追証未解消金額
        protected double calcFirstAdddepositUncancelAmt()
        {
            if (l_strFlag.equals("testCalcFirstAdddepositUncancelSettleRequiredAmt_C0004()"))
            {
                return 6;
            }
            return 0;
        }
        
        //calc第一水準追証金額
        protected double calcFirstAdddepositAmount()
        {
            return 0;
        }

        //create第二水準追証未解消情報
        public WEB3TPSecondAdddepositNotClearInfo createSecondAdddepositNotClearInfo()
        {
            WEB3TPSecondAdddepositNotClearInfo l_secondAdddepositNotClearInfo =
                new WEB3TPSecondAdddepositNotClearInfo();
            if (l_strFlag.equals("testCalcFirstAdddepositSettlement_C0002()"))
            {
                l_secondAdddepositNotClearInfo.secondDepositNonPay = 5;
                return l_secondAdddepositNotClearInfo;
            }
            if (l_strFlag.equals("testCalcFirstAdddepositSettlement_C0003()"))
            {
                l_secondAdddepositNotClearInfo.secondDeposit2 = 5;
                return l_secondAdddepositNotClearInfo;
            }
            if (l_strFlag.equals("testCalcFirstAdddepositSettlement_C0004()"))
            {
                l_secondAdddepositNotClearInfo.secondDeposit1 = 5;
                return l_secondAdddepositNotClearInfo;
            }
            if (l_strFlag.equals("testCalcFirstAdddepositSettlement_C0005()"))
            {
                l_secondAdddepositNotClearInfo.secondDepositExpect = 5;
                return l_secondAdddepositNotClearInfo;
            }
            return l_secondAdddepositNotClearInfo;
        }
        
        //calc実質保証金
        protected double calcRealMargin()
        {
            if (l_strFlag.equals("testCalcSecondAdddepositSettlement_C0003()"))
            {
                return 2.2;
            }
            return 0;
        }
        
        protected double calcSecondAdddepositAmount()
        {
            if (l_strFlag.equals("testCalcSecondAdddepositSettlement_C0003()"))
            {
                return 1.1;
            }
            return 0;
        }
        
        //calc保証金率
        protected double calcMarginDepositRate()
        {
            return 0;
        }
        
        //get第二水準追証保証金維持率
        public double getSecondAdddepositDepositRate()
        {
            if (l_strFlag.equals("testCalcSecondAdddepositAmount_C0002()"))
            {
                return 2.2;
            }
            return 0;
        }
        
    }
    
    private class WEB3TPPaymentRequisitionManagementForTest3B extends WEB3TPPaymentRequisitionManagement
    {
        
        //calc当日保証金入金額
        protected double calcTodayDepositPaymentAmount()
        {
            if (l_strFlag.equals("testCalcFirstAdddepositUncancelAmt_C0002()"))
            {
                return 6;
            }
            if (l_strFlag.equals("testCalcFirstAdddepositUncancelAmt_C0003()"))
            {
                return 6;
            }
            return 0;
        }
        
        //calc第一水準追証当日消し込み換算額
        protected double calcFirstAdddepositEliminateAmount()
        {
            if (l_strFlag.equals("testCalcFirstAdddepositUncancelAmt_C0002()"))
            {
                return 9;
            }
            if (l_strFlag.equals("testCalcFirstAdddepositUncancelAmt_C0003()"))
            {
                return 9;
            }
            return 0;
        }
        
        //create第二水準追証未解消情報
        public WEB3TPSecondAdddepositNotClearInfo createSecondAdddepositNotClearInfo()
        {
            WEB3TPSecondAdddepositNotClearInfo l_secondAdddepositNotClearInfo =
                new WEB3TPSecondAdddepositNotClearInfo();
            return l_secondAdddepositNotClearInfo;
        }
        
        //calc第一水準追証金額
        protected double calcFirstAdddepositAmount()
        {
            if (l_strFlag.equals("testCalcFirstAdddepositSettlement_C0007()"))
            {
                return 1.1;
            }
            return 0;
        }

        //calc保証金率
        protected double calcMarginDepositRate()
        {
            if (l_strFlag.equals("testCalcSecondAdddepositAmount_C0002()"))
            {
                return 5.5;
            }
            if (l_strFlag.equals("testCalcSecondAdddepositAmount_C0004()"))
            {
                return 2.2;
            }
            return 0;
        }
        
        //calc実質保証金
        protected double calcRealMargin()
        {
            if (l_strFlag.equals("testCalcFirstAdddepositSettlement_C0007()"))
            {
                return 2.2;
            }
            if (l_strFlag.equals("testCalcSecondAdddepositAmount_C0003()"))
            {
                return 100;
            }
            return 0;
        }

        //get第二水準追証保証金維持率
        public double getSecondAdddepositDepositRate()
        {
            if (l_strFlag.equals("testCalcSecondAdddepositAmount_C0002()"))
            {
                return 2.2;
            }
            if (l_strFlag.equals("testCalcSecondAdddepositAmount_C0004()"))
            {
                return 5.5;
            }
            return 0;
        }
        
        //get第二水準追証保証金戻し維持率
        public double getSecondAdddepositDepositBackRate()
        {
            if (l_strFlag.equals("testCalcSecondAdddepositAmount_C0004()"))
            {
                return 2;
            }
            return 0;
        }
    }
    
    private class WEB3TPPaymentRequisitionManagementForTest3C extends WEB3TPPaymentRequisitionManagement
    {
        //calc現金保証金
        protected double calcCashMarginDeposit(int l_intSpecifiedPoint)
        {
            if (l_strFlag.equals("testCalcRealMargin_C0002()") && l_intSpecifiedPoint == 0)
            {
                return 80;
            }
            if (l_strFlag.equals("testCalcRealMargin_C0003()") && l_intSpecifiedPoint == 0)
            {
                return 80;
            }
            return 0;
        }

        //create第二水準追証未解消情報
        public WEB3TPSecondAdddepositNotClearInfo createSecondAdddepositNotClearInfo()
        {
            WEB3TPSecondAdddepositNotClearInfo l_secondAdddepositNotClearInfo =
                new WEB3TPSecondAdddepositNotClearInfo();
            if (l_strFlag.equals("testCalcFirstAdddepositAmount_C0002()"))
            {
                l_secondAdddepositNotClearInfo.secondDepositNonPay = 5;
                return l_secondAdddepositNotClearInfo;
            }
            if (l_strFlag.equals("testCalcFirstAdddepositAmount_C0003()"))
            {
                l_secondAdddepositNotClearInfo.secondDeposit2 = 5;
                return l_secondAdddepositNotClearInfo;
            }
            if (l_strFlag.equals("testCalcFirstAdddepositAmount_C0004()"))
            {
                l_secondAdddepositNotClearInfo.secondDeposit1 = 5;
                return l_secondAdddepositNotClearInfo;
            }
            if (l_strFlag.equals("testCalcFirstAdddepositAmount_C0005()"))
            {
                l_secondAdddepositNotClearInfo.secondDepositExpect = 5;
                return l_secondAdddepositNotClearInfo;
            }
            return l_secondAdddepositNotClearInfo;
        }
        
        protected double calcRealMargin()
        {
            if (l_strFlag.equals("testCalcFirstAdddepositAmount_C0006()"))
            {
                return 100;
            }
            return 0;
        }
        
        //get第一水準追証保証金維持率
        public double getFirstAdddepositDepositRate()
        {
            if (l_strFlag.equals("testCalcFirstAdddepositAmount_C0007()"))
            {
                return 2;
            }
            return 0;
        }
    }
    
    private class WEB3TPPaymentRequisitionManagementForTest3D extends WEB3TPPaymentRequisitionManagement
    {
        //calc現金保証金
        protected double calcCashMarginDeposit(int l_intSpecifiedPoint)
        {
            if (l_strFlag.equals("testCalcRealMargin_C0002()") && l_intSpecifiedPoint == 0)
            {
                return 80;
            }
            if (l_strFlag.equals("testCalcRealMargin_C0003()") && l_intSpecifiedPoint == 0)
            {
                return 80;
            }
            return 0;
        }
    }   

    private class WEB3TPCalcConditionForTest extends WEB3TPCalcCondition
    {
        public String getInstBranCalcCondition(String l_strName)
        {
            if (l_strFlag.equals("testCalcFirstAdddepositEliminateAmount_C0002()")
                && "today.clearance.determination.div".equals(l_strName))
            {
                return null;
            }
            if (l_strFlag.equals("testCalcFirstAdddepositEliminateAmount_C0003()")
                    && "today.clearance.determination.div".equals(l_strName))
            {
                return "2";
            }
            if (l_strFlag.equals("testCalcFirstAdddepositEliminateAmount_C0004()")
                    && "today.clearance.determination.div".equals(l_strName))
            {
                return "1";
            }
            if (l_strFlag.equals("testCalcFirstAdddepositEliminateAmount_C0005()")
                    && "today.clearance.determination.div".equals(l_strName))
            {
                return "1";
            }
            return null;
        }
    }

    /**
     * create入金請求管理
     * <信用顧客>
     */
    public void testCreatPaymentRequisitionManagement02()
    {
        final String STR_METHOD_NAME = "testCreatPaymentRequisitionManagement02()";
        log.entering(TEST_START + STR_METHOD_NAME);

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
            
            
//            //スタティック
//            Calendar l_calendar =  Calendar.getInstance();
//            l_calendar.set(2007, 7-1, 28);
//            Timestamp l_tsAppliyDate = new Timestamp(l_calendar.getTimeInMillis());
//            ThreadLocalSystemAttributesRegistry.setAttribute(
//                "xblocks.gtl.attributes.systemtimestamp",
//                l_tsAppliyDate);

            WEB3GentradeTradingClendarContext l_clendarContext = new WEB3GentradeTradingClendarContext();
//            l_clendarContext
            ThreadLocalSystemAttributesRegistry.setAttribute("web3.tradingcalendarcontext", l_clendarContext);

            //DB
            MainAccountParams l_mainAccountParams = new MainAccountParams();
            QueryProcessor l_processor = Processors.getDefaultProcessor();
            l_processor.doDeleteAllQuery(MainAccountRow.TYPE);
            l_mainAccountParams = TestDBUtility.getMainAccountRow();
            l_mainAccountParams.setMarginSysAccOpenDiv("1");    //the third case
            l_mainAccountParams.setSecuredLoanSecAccOpenDiv("0");
            TestDBUtility.insertWithDel(l_mainAccountParams);

            InstitutionParams l_institutionParams =  new InstitutionParams();
            l_processor.doDeleteAllQuery(InstitutionRow.TYPE);
            l_institutionParams = TestDBUtility.getInstitutionRow();
            l_institutionParams.setAssetEvaluation("0");
            l_institutionParams.setMaximumAssessment(1L);
            TestDBUtility.insertWithDel(l_institutionParams);

            BranchParams l_branchParams =  new BranchParams();
            l_processor.doDeleteAllQuery(BranchRow.TYPE);
            l_branchParams = TestDBUtility.getBranchRow();
            TestDBUtility.insertWithDel(l_branchParams);

            PaymentRequisitMngParams l_paymentRequisitMngParams = new PaymentRequisitMngParams();
            l_processor.doDeleteAllQuery(PaymentRequisitMngRow.TYPE);
            l_paymentRequisitMngParams.setAccountId(333812512246L);
            l_paymentRequisitMngParams.setAccountCode("2512246");
            l_paymentRequisitMngParams.setBranchCode("381");
            l_paymentRequisitMngParams.setInstitutionCode("0D");
            l_paymentRequisitMngParams.setFamilyName("Family Name");
            l_paymentRequisitMngParams.setAccountAttribute("0");
            l_paymentRequisitMngParams.setCalcDate(WEB3DateUtility.getDate("20070728","yyyyMMdd"));
            l_paymentRequisitMngParams.setCreatedTimestamp(WEB3DateUtility.getDate("20070728","yyyyMMdd"));
            l_paymentRequisitMngParams.setLastUpdatedTimestamp(WEB3DateUtility.getDate("20070728","yyyyMMdd"));
            TestDBUtility.insertWithDel(l_paymentRequisitMngParams);

            SubAccountParams l_subAccountParams = new SubAccountParams();
            l_processor.doDeleteAllQuery(SubAccountRow.TYPE);
            l_subAccountParams = TestDBUtility.getSubAccountRow();
            l_subAccountParams.setAccountId(333812512246L);
            l_subAccountParams.setSubAccountType(SubAccountTypeEnum.EQUITY_MARGIN_SUB_ACCOUNT);
            TestDBUtility.insertWithDel(l_subAccountParams);

            BranchPreferencesParams l_branchPreferencesParams = new BranchPreferencesParams();
            l_processor.doDeleteAllQuery(BranchPreferencesRow.TYPE);
            l_branchPreferencesParams = TestDBUtility.getBranchPreferencesRow();
            l_branchPreferencesParams.setName("0");
            l_branchPreferencesParams.setBranchId(33381);
            TestDBUtility.insertWithDel(l_branchPreferencesParams);

            l_processor.doDeleteAllQuery(SystemPreferencesRow.TYPE);

            OrderexecutionEndParams l_qrderexecutionEndParams = new OrderexecutionEndParams();
            l_processor.doDeleteAllQuery(OrderexecutionEndRow.TYPE);
            l_qrderexecutionEndParams.setInstitutionCode("0D");
            l_qrderexecutionEndParams.setProductType(ProductTypeEnum.EQUITY);
            l_qrderexecutionEndParams.setFutureOptionDiv("0");
            l_qrderexecutionEndParams.setCreatedTimestamp(WEB3DateUtility.getDate("20070728","yyyyMMdd"));
            l_qrderexecutionEndParams.setLastUpdatedTimestamp(WEB3DateUtility.getDate("20070728","yyyyMMdd"));
            l_qrderexecutionEndParams.setOrderexecutionEndType("0");
            TestDBUtility.insertWithDel(l_qrderexecutionEndParams);

            TradingpowerCalcConditionParams l_tradingpowerCalcConditionParams = new TradingpowerCalcConditionParams();
            l_processor.doDeleteAllQuery(TradingpowerCalcConditionRow.TYPE);
            l_tradingpowerCalcConditionParams = TestDBUtility.getTradingpowerCalcConditionRow();
            l_tradingpowerCalcConditionParams.setAccountId(333812512246L);
            l_tradingpowerCalcConditionParams.setAdditionalDepositStop("1");
            TestDBUtility.insertWithDel(l_tradingpowerCalcConditionParams);

            TpCalcResultMarginParams l_tpCalcResultMarginParams = new TpCalcResultMarginParams();
            l_processor.doDeleteAllQuery(TpCalcResultMarginRow.TYPE);
            l_tpCalcResultMarginParams = TestDBUtility.getTpCalcResultMarginRow();
            l_tpCalcResultMarginParams.setCreatedTimestamp(WEB3DateUtility.getDate("20070728","yyyyMMdd"));
            TestDBUtility.insertWithDel(l_tpCalcResultMarginParams);

            l_processor.doDeleteAllQuery(ProcessManagementRow.TYPE);

            TpCalcResultMarginDetailParams l_tpCalcResultMarginDetailParams = new TpCalcResultMarginDetailParams();
            l_processor.doDeleteAllQuery(TpCalcResultMarginDetailRow.TYPE);
            l_tpCalcResultMarginDetailParams = TestDBUtility.getTpCalcResultMarginDetailRow();
            TestDBUtility.insertWithDel(l_tpCalcResultMarginDetailParams);

            TpCashBalanceParams l_tpCashBalanceParams = new TpCashBalanceParams();
            l_processor.doDeleteAllQuery(TpCashBalanceRow.TYPE);
            l_tpCashBalanceParams.setAccountId(333812512246L);
            l_tpCashBalanceParams.setSubAccountId(33381251220301L);
            TestDBUtility.insertWithDel(l_tpCashBalanceParams);

            AioOrderUnitParams l_aioOrderUnitParams = new AioOrderUnitParams();
            l_processor.doDeleteAllQuery(AioOrderUnitRow.TYPE);
            l_aioOrderUnitParams = TestDBUtility.getAioOrderUnitRow();
            l_aioOrderUnitParams.setAccountId(333812512246L);
            l_aioOrderUnitParams.setSubAccountId(33381251220301L);
            l_aioOrderUnitParams.setOrderType(OrderTypeEnum.FROM_DEPOSIT_AMOUNT_MARGIN_GUARANTEE);
            l_aioOrderUnitParams.setDeliveryDate(WEB3DateUtility.getDate("20070728","yyyyMMdd"));
            l_aioOrderUnitParams.setOrderStatus(OrderStatusEnum.ACCEPTED);
            TestDBUtility.insertWithDel(l_aioOrderUnitParams);
            l_aioOrderUnitParams.setOrderUnitId(0L);
            l_aioOrderUnitParams.setOrderType(OrderTypeEnum.MARGIN_GUARANTEE_FROM_DEPOSIT_AMOUNT);
            TestDBUtility.insertWithDel(l_aioOrderUnitParams);

            //実行
            WEB3TPPaymentRequisitionManagement l_paymentRequisitionManagement = WEB3TPPaymentRequisitionManagement.
                createPaymentRequisitionManagement(new WEB3GentradeMainAccount(l_mainAccountParams));

            //比較
            assertNull(l_paymentRequisitionManagement.tpCalcEquity);
            assertEquals("1", l_paymentRequisitionManagement.marginEquityJudgeFlag);
        }
        catch (Exception l_ex)
        {
            log.debug(STR_METHOD_NAME,l_ex);
            fail();
        }
        log.exiting(TEST_END + STR_METHOD_NAME);
    }
    
    /**
     * create入金請求管理
     * <現物顧客>
     */
    public void testCreatPaymentRequisitionManagement03()
    {
        final String STR_METHOD_NAME = "testCreatPaymentRequisitionManagement03()";
        log.entering(TEST_START + STR_METHOD_NAME);

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
            
            
//            //スタティック
//            Calendar l_calendar =  Calendar.getInstance();
//            l_calendar.set(2007, 7-1, 28);
//            Timestamp l_tsAppliyDate = new Timestamp(l_calendar.getTimeInMillis());
//            ThreadLocalSystemAttributesRegistry.setAttribute(
//                "xblocks.gtl.attributes.systemtimestamp",
//                l_tsAppliyDate);

            WEB3GentradeTradingClendarContext l_clendarContext = new WEB3GentradeTradingClendarContext();
//            l_clendarContext
            ThreadLocalSystemAttributesRegistry.setAttribute("web3.tradingcalendarcontext", l_clendarContext);

            //DB
            MainAccountParams l_mainAccountParams = new MainAccountParams();
            QueryProcessor l_processor = Processors.getDefaultProcessor();
            l_processor.doDeleteAllQuery(MainAccountRow.TYPE);
            l_mainAccountParams = TestDBUtility.getMainAccountRow();
            l_mainAccountParams.setMarginSysAccOpenDiv("0");    //the third case
            l_mainAccountParams.setSecuredLoanSecAccOpenDiv("0");
            TestDBUtility.insertWithDel(l_mainAccountParams);

            InstitutionParams l_institutionParams =  new InstitutionParams();
            l_processor.doDeleteAllQuery(InstitutionRow.TYPE);
            l_institutionParams = TestDBUtility.getInstitutionRow();
            l_institutionParams.setAssetEvaluation("0");
            l_institutionParams.setMaximumAssessment(1L);
            TestDBUtility.insertWithDel(l_institutionParams);

            BranchParams l_branchParams =  new BranchParams();
            l_processor.doDeleteAllQuery(BranchRow.TYPE);
            l_branchParams = TestDBUtility.getBranchRow();
            TestDBUtility.insertWithDel(l_branchParams);

            PaymentRequisitMngParams l_paymentRequisitMngParams = new PaymentRequisitMngParams();
            l_processor.doDeleteAllQuery(PaymentRequisitMngRow.TYPE);
            l_paymentRequisitMngParams.setAccountId(333812512246L);
            l_paymentRequisitMngParams.setAccountCode("2512246");
            l_paymentRequisitMngParams.setBranchCode("381");
            l_paymentRequisitMngParams.setInstitutionCode("0D");
            l_paymentRequisitMngParams.setFamilyName("Family Name");
            l_paymentRequisitMngParams.setAccountAttribute("0");
            l_paymentRequisitMngParams.setCalcDate(WEB3DateUtility.getDate("20070728","yyyyMMdd"));
            l_paymentRequisitMngParams.setCreatedTimestamp(WEB3DateUtility.getDate("20070728","yyyyMMdd"));
            l_paymentRequisitMngParams.setLastUpdatedTimestamp(WEB3DateUtility.getDate("20070728","yyyyMMdd"));
            TestDBUtility.insertWithDel(l_paymentRequisitMngParams);

            SubAccountParams l_subAccountParams = new SubAccountParams();
            l_processor.doDeleteAllQuery(SubAccountRow.TYPE);
            l_subAccountParams = TestDBUtility.getSubAccountRow();
            l_subAccountParams.setAccountId(333812512246L);
            l_subAccountParams.setSubAccountType(SubAccountTypeEnum.EQUITY_SUB_ACCOUNT);
            TestDBUtility.insertWithDel(l_subAccountParams);

            BranchPreferencesParams l_branchPreferencesParams = new BranchPreferencesParams();
            l_processor.doDeleteAllQuery(BranchPreferencesRow.TYPE);
            l_branchPreferencesParams = TestDBUtility.getBranchPreferencesRow();
            l_branchPreferencesParams.setName("0");
            l_branchPreferencesParams.setBranchId(33381);
            TestDBUtility.insertWithDel(l_branchPreferencesParams);

            l_processor.doDeleteAllQuery(SystemPreferencesRow.TYPE);

            OrderexecutionEndParams l_qrderexecutionEndParams = new OrderexecutionEndParams();
            l_processor.doDeleteAllQuery(OrderexecutionEndRow.TYPE);
            l_qrderexecutionEndParams.setInstitutionCode("0D");
            l_qrderexecutionEndParams.setProductType(ProductTypeEnum.EQUITY);
            l_qrderexecutionEndParams.setFutureOptionDiv("0");
            l_qrderexecutionEndParams.setCreatedTimestamp(WEB3DateUtility.getDate("20070728","yyyyMMdd"));
            l_qrderexecutionEndParams.setLastUpdatedTimestamp(WEB3DateUtility.getDate("20070728","yyyyMMdd"));
            l_qrderexecutionEndParams.setOrderexecutionEndType("0");
            TestDBUtility.insertWithDel(l_qrderexecutionEndParams);

            TradingpowerCalcConditionParams l_tradingpowerCalcConditionParams = new TradingpowerCalcConditionParams();
            l_processor.doDeleteAllQuery(TradingpowerCalcConditionRow.TYPE);
            l_tradingpowerCalcConditionParams = TestDBUtility.getTradingpowerCalcConditionRow();
            l_tradingpowerCalcConditionParams.setAccountId(333812512246L);
            l_tradingpowerCalcConditionParams.setAdditionalDepositStop("1");
            TestDBUtility.insertWithDel(l_tradingpowerCalcConditionParams);

            TpCalcResultEquityParams l_tpCalcResultEquityParams = new TpCalcResultEquityParams();
            l_processor.doDeleteAllQuery(TpCalcResultEquityRow.TYPE);
            l_tpCalcResultEquityParams = TestDBUtility.getTpCalcResultEquityRow();
            l_tpCalcResultEquityParams.setCalcResultEquityId(333812512246L);
            l_tpCalcResultEquityParams.setAccountId(333812512246L);
            l_tpCalcResultEquityParams.setCreatedTimestamp(WEB3DateUtility.getDate("20070728","yyyyMMdd"));
            TestDBUtility.insertWithDel(l_tpCalcResultEquityParams);

            l_processor.doDeleteAllQuery(ProcessManagementRow.TYPE);

            TpCalcResultMarginDetailParams l_tpCalcResultMarginDetailParams = new TpCalcResultMarginDetailParams();
            l_processor.doDeleteAllQuery(TpCalcResultMarginDetailRow.TYPE);
            l_tpCalcResultMarginDetailParams = TestDBUtility.getTpCalcResultMarginDetailRow();
            TestDBUtility.insertWithDel(l_tpCalcResultMarginDetailParams);
            
            TpCalcResultEquityDetailParams l_resultDetailParams = new TpCalcResultEquityDetailParams();
            l_processor.doDeleteAllQuery(TpCalcResultEquityDetailRow.TYPE);
            l_resultDetailParams.setAccountId(333812512246L);
            l_resultDetailParams.setCalcResultEquityId(333812512246L);
            l_resultDetailParams.setCreatedTimestamp(Calendar.getInstance().getTime());
            l_resultDetailParams.setLastUpdatedTimestamp(Calendar.getInstance().getTime());
            
            TestDBUtility.insertWithDel(l_resultDetailParams);
//            l_resultDetailParams.setAccountId();

            TpCashBalanceParams l_tpCashBalanceParams = new TpCashBalanceParams();
            l_processor.doDeleteAllQuery(TpCashBalanceRow.TYPE);
            l_tpCashBalanceParams.setAccountId(333812512246L);
            l_tpCashBalanceParams.setSubAccountId(33381251220301L);
            TestDBUtility.insertWithDel(l_tpCashBalanceParams);

            AioOrderUnitParams l_aioOrderUnitParams = new AioOrderUnitParams();
            l_processor.doDeleteAllQuery(AioOrderUnitRow.TYPE);
            l_aioOrderUnitParams = TestDBUtility.getAioOrderUnitRow();
            l_aioOrderUnitParams.setAccountId(333812512246L);
            l_aioOrderUnitParams.setSubAccountId(33381251220301L);
            l_aioOrderUnitParams.setOrderType(OrderTypeEnum.FROM_DEPOSIT_AMOUNT_MARGIN_GUARANTEE);
            l_aioOrderUnitParams.setDeliveryDate(WEB3DateUtility.getDate("20070728","yyyyMMdd"));
            l_aioOrderUnitParams.setOrderStatus(OrderStatusEnum.ACCEPTED);
            TestDBUtility.insertWithDel(l_aioOrderUnitParams);
            l_aioOrderUnitParams.setOrderUnitId(0L);
            l_aioOrderUnitParams.setOrderType(OrderTypeEnum.MARGIN_GUARANTEE_FROM_DEPOSIT_AMOUNT);
            TestDBUtility.insertWithDel(l_aioOrderUnitParams);

            //実行
            WEB3TPPaymentRequisitionManagement l_paymentRequisitionManagement = WEB3TPPaymentRequisitionManagement.
                createPaymentRequisitionManagement(new WEB3GentradeMainAccount(l_mainAccountParams));

            //比較
//            assertNull(l_paymentRequisitionManagement.tpCalcEquity);
            assertEquals("0", l_paymentRequisitionManagement.marginEquityJudgeFlag);
        }
        catch (Exception l_ex)
        {
            log.debug(STR_METHOD_NAME,l_ex);
            fail();
        }
        log.exiting(TEST_END + STR_METHOD_NAME);
    }
    
    /**
     * create入金請求管理
     * <信用顧客>
     * WEB3ErrorCatalog.SYSTEM_ERROR_80005
     */
    public void testCreatPaymentRequisitionManagement04()
    {
        final String STR_METHOD_NAME = "testCreatPaymentRequisitionManagement04()";
        log.entering(TEST_START + STR_METHOD_NAME);

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
            
            
//            //スタティック
//            Calendar l_calendar =  Calendar.getInstance();
//            l_calendar.set(2007, 7-1, 28);
//            Timestamp l_tsAppliyDate = new Timestamp(l_calendar.getTimeInMillis());
//            ThreadLocalSystemAttributesRegistry.setAttribute(
//                "xblocks.gtl.attributes.systemtimestamp",
//                l_tsAppliyDate);

            WEB3GentradeTradingClendarContext l_clendarContext = new WEB3GentradeTradingClendarContext();
//            l_clendarContext
            ThreadLocalSystemAttributesRegistry.setAttribute("web3.tradingcalendarcontext", l_clendarContext);

            //DB
            MainAccountParams l_mainAccountParams = new MainAccountParams();
            QueryProcessor l_processor = Processors.getDefaultProcessor();
            l_processor.doDeleteAllQuery(MainAccountRow.TYPE);
            l_mainAccountParams = TestDBUtility.getMainAccountRow();
            l_mainAccountParams.setMarginSysAccOpenDiv("1");    //the third case
            l_mainAccountParams.setSecuredLoanSecAccOpenDiv("0");
            TestDBUtility.insertWithDel(l_mainAccountParams);

            InstitutionParams l_institutionParams =  new InstitutionParams();
            l_processor.doDeleteAllQuery(InstitutionRow.TYPE);
            l_institutionParams = TestDBUtility.getInstitutionRow();
            l_institutionParams.setAssetEvaluation("0");
            l_institutionParams.setMaximumAssessment(1L);
            TestDBUtility.insertWithDel(l_institutionParams);

            BranchParams l_branchParams =  new BranchParams();
            l_processor.doDeleteAllQuery(BranchRow.TYPE);
            l_branchParams = TestDBUtility.getBranchRow();
            TestDBUtility.insertWithDel(l_branchParams);

            PaymentRequisitMngParams l_paymentRequisitMngParams = new PaymentRequisitMngParams();
            l_processor.doDeleteAllQuery(PaymentRequisitMngRow.TYPE);
            l_paymentRequisitMngParams.setAccountId(333812512246L);
            l_paymentRequisitMngParams.setAccountCode("2512246");
            l_paymentRequisitMngParams.setBranchCode("381");
            l_paymentRequisitMngParams.setInstitutionCode("0D");
            l_paymentRequisitMngParams.setFamilyName("Family Name");
            l_paymentRequisitMngParams.setAccountAttribute("0");
            l_paymentRequisitMngParams.setCalcDate(WEB3DateUtility.getDate("20070728","yyyyMMdd"));
            l_paymentRequisitMngParams.setCreatedTimestamp(WEB3DateUtility.getDate("20070728","yyyyMMdd"));
            l_paymentRequisitMngParams.setLastUpdatedTimestamp(WEB3DateUtility.getDate("20070728","yyyyMMdd"));
            TestDBUtility.insertWithDel(l_paymentRequisitMngParams);

            SubAccountParams l_subAccountParams = new SubAccountParams();
            l_processor.doDeleteAllQuery(SubAccountRow.TYPE);
            l_subAccountParams = TestDBUtility.getSubAccountRow();
            l_subAccountParams.setAccountId(333812512246L);
            l_subAccountParams.setSubAccountType(SubAccountTypeEnum.EQUITY_MARGIN_SUB_ACCOUNT);
            TestDBUtility.insertWithDel(l_subAccountParams);

            BranchPreferencesParams l_branchPreferencesParams = new BranchPreferencesParams();
            l_processor.doDeleteAllQuery(BranchPreferencesRow.TYPE);
            l_branchPreferencesParams = TestDBUtility.getBranchPreferencesRow();
            l_branchPreferencesParams.setName("0");
            l_branchPreferencesParams.setBranchId(33381);
            TestDBUtility.insertWithDel(l_branchPreferencesParams);

            l_processor.doDeleteAllQuery(SystemPreferencesRow.TYPE);

            OrderexecutionEndParams l_qrderexecutionEndParams = new OrderexecutionEndParams();
            l_processor.doDeleteAllQuery(OrderexecutionEndRow.TYPE);
            l_qrderexecutionEndParams.setInstitutionCode("0D");
            l_qrderexecutionEndParams.setProductType(ProductTypeEnum.EQUITY);
            l_qrderexecutionEndParams.setFutureOptionDiv("0");
            l_qrderexecutionEndParams.setCreatedTimestamp(WEB3DateUtility.getDate("20070728","yyyyMMdd"));
            l_qrderexecutionEndParams.setLastUpdatedTimestamp(WEB3DateUtility.getDate("20070728","yyyyMMdd"));
            l_qrderexecutionEndParams.setOrderexecutionEndType("0");
            TestDBUtility.insertWithDel(l_qrderexecutionEndParams);

            TradingpowerCalcConditionParams l_tradingpowerCalcConditionParams = new TradingpowerCalcConditionParams();
            l_processor.doDeleteAllQuery(TradingpowerCalcConditionRow.TYPE);
            l_tradingpowerCalcConditionParams = TestDBUtility.getTradingpowerCalcConditionRow();
            l_tradingpowerCalcConditionParams.setAccountId(333812512246L);
            l_tradingpowerCalcConditionParams.setAdditionalDepositStop("1");
            TestDBUtility.insertWithDel(l_tradingpowerCalcConditionParams);

            TpCalcResultMarginParams l_tpCalcResultMarginParams = new TpCalcResultMarginParams();
            l_processor.doDeleteAllQuery(TpCalcResultMarginRow.TYPE);
            l_tpCalcResultMarginParams = TestDBUtility.getTpCalcResultMarginRow();
            l_tpCalcResultMarginParams.setCreatedTimestamp(WEB3DateUtility.getDate("20070728","yyyyMMdd"));
            TestDBUtility.insertWithDel(l_tpCalcResultMarginParams);

            l_processor.doDeleteAllQuery(ProcessManagementRow.TYPE);

            TpCalcResultMarginDetailParams l_tpCalcResultMarginDetailParams = new TpCalcResultMarginDetailParams();
            l_processor.doDeleteAllQuery(TpCalcResultMarginDetailRow.TYPE);
            l_tpCalcResultMarginDetailParams = TestDBUtility.getTpCalcResultMarginDetailRow();
            TestDBUtility.insertWithDel(l_tpCalcResultMarginDetailParams);

            TpCashBalanceParams l_tpCashBalanceParams = new TpCashBalanceParams();
            l_processor.doDeleteAllQuery(TpCashBalanceRow.TYPE);
            l_tpCashBalanceParams.setAccountId(11);
            l_tpCashBalanceParams.setSubAccountId(11);
            TestDBUtility.insertWithDel(l_tpCashBalanceParams);

            AioOrderUnitParams l_aioOrderUnitParams = new AioOrderUnitParams();
            l_processor.doDeleteAllQuery(AioOrderUnitRow.TYPE);
            l_aioOrderUnitParams = TestDBUtility.getAioOrderUnitRow();
            l_aioOrderUnitParams.setAccountId(333812512246L);
            l_aioOrderUnitParams.setSubAccountId(33381251220301L);
            l_aioOrderUnitParams.setOrderType(OrderTypeEnum.FROM_DEPOSIT_AMOUNT_MARGIN_GUARANTEE);
            l_aioOrderUnitParams.setDeliveryDate(WEB3DateUtility.getDate("20070728","yyyyMMdd"));
            l_aioOrderUnitParams.setOrderStatus(OrderStatusEnum.ACCEPTED);
            TestDBUtility.insertWithDel(l_aioOrderUnitParams);
            l_aioOrderUnitParams.setOrderUnitId(0L);
            l_aioOrderUnitParams.setOrderType(OrderTypeEnum.MARGIN_GUARANTEE_FROM_DEPOSIT_AMOUNT);
            TestDBUtility.insertWithDel(l_aioOrderUnitParams);

            //実行
            WEB3TPPaymentRequisitionManagement l_paymentRequisitionManagement = WEB3TPPaymentRequisitionManagement.
                createPaymentRequisitionManagement(new WEB3GentradeMainAccount(l_mainAccountParams));

            //比較
            assertNull(l_paymentRequisitionManagement.tpCalcEquity);
            assertEquals("1", l_paymentRequisitionManagement.marginEquityJudgeFlag);
        }
        catch (WEB3BaseException l_ex)
        {
            assertEquals(WEB3ErrorCatalog.SYSTEM_ERROR_80005,l_ex.getErrorInfo());
        }
        catch (Exception l_ex)
        {
            log.debug(STR_METHOD_NAME,l_ex);
            fail();
        }
        log.exiting(TEST_END + STR_METHOD_NAME);
    }
    
    public void test_createSecondAdddepositNotClearInfo_0001()
    {
        final String STR_METHOD_NAME = "test_createSecondAdddepositNotClearInfo_0001()";
        log.entering(TEST_START + STR_METHOD_NAME);

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
            
            
//            //スタティック
//            Calendar l_calendar =  Calendar.getInstance();
//            l_calendar.set(2007, 7-1, 28);
//            Timestamp l_tsAppliyDate = new Timestamp(l_calendar.getTimeInMillis());
//            ThreadLocalSystemAttributesRegistry.setAttribute(
//                "xblocks.gtl.attributes.systemtimestamp",
//                l_tsAppliyDate);

            WEB3GentradeTradingClendarContext l_clendarContext = new WEB3GentradeTradingClendarContext();
//            l_clendarContext
            ThreadLocalSystemAttributesRegistry.setAttribute("web3.tradingcalendarcontext", l_clendarContext);

            //DB
            MainAccountParams l_mainAccountParams = new MainAccountParams();
            QueryProcessor l_processor = Processors.getDefaultProcessor();
            l_processor.doDeleteAllQuery(MainAccountRow.TYPE);
            l_mainAccountParams = TestDBUtility.getMainAccountRow();
            l_mainAccountParams.setMarginSysAccOpenDiv("1");    //the third case
            l_mainAccountParams.setSecuredLoanSecAccOpenDiv("0");
            TestDBUtility.insertWithDel(l_mainAccountParams);

            InstitutionParams l_institutionParams =  new InstitutionParams();
            l_processor.doDeleteAllQuery(InstitutionRow.TYPE);
            l_institutionParams = TestDBUtility.getInstitutionRow();
            l_institutionParams.setAssetEvaluation("0");
            l_institutionParams.setMaximumAssessment(1L);
            TestDBUtility.insertWithDel(l_institutionParams);

            BranchParams l_branchParams =  new BranchParams();
            l_processor.doDeleteAllQuery(BranchRow.TYPE);
            l_branchParams = TestDBUtility.getBranchRow();
            TestDBUtility.insertWithDel(l_branchParams);

            PaymentRequisitMngParams l_paymentRequisitMngParams = new PaymentRequisitMngParams();
            l_processor.doDeleteAllQuery(PaymentRequisitMngRow.TYPE);
            l_paymentRequisitMngParams.setAccountId(333812512246L);
            l_paymentRequisitMngParams.setAccountCode("2512246");
            l_paymentRequisitMngParams.setBranchCode("381");
            l_paymentRequisitMngParams.setInstitutionCode("0D");
            l_paymentRequisitMngParams.setFamilyName("Family Name");
            l_paymentRequisitMngParams.setAccountAttribute("0");
            l_paymentRequisitMngParams.setCalcDate(WEB3DateUtility.getDate("20070728","yyyyMMdd"));
            l_paymentRequisitMngParams.setCreatedTimestamp(WEB3DateUtility.getDate("20070728","yyyyMMdd"));
            l_paymentRequisitMngParams.setLastUpdatedTimestamp(WEB3DateUtility.getDate("20070728","yyyyMMdd"));
            
            l_paymentRequisitMngParams.setCashMarginDeposit(50);
            //第二水準追証未入金
            l_paymentRequisitMngParams.setSecondDepositNonPay(210);
            //第二水準追証請求(2)
            l_paymentRequisitMngParams.setSecondDeposit2(36);
            //第二水準追証未入金決済必要額
            l_paymentRequisitMngParams.setSecondSettlementNonPay(26);
            //第二水準追証請求(2)
            l_paymentRequisitMngParams.setSecondDeposit2(2);
            //第二水準追証決済必要額(2)
            l_paymentRequisitMngParams.setSecondSettlement2(45.5);
            //第二水準追証請求(1)
            l_paymentRequisitMngParams.setSecondDeposit1(23);
            //第二水準追証決済必要額(1)
            l_paymentRequisitMngParams.setSecondSettlement1(12.6);
            //
            l_paymentRequisitMngParams.setSecondDepositBackRate(20);
            TestDBUtility.insertWithDel(l_paymentRequisitMngParams);

            SubAccountParams l_subAccountParams = new SubAccountParams();
            l_processor.doDeleteAllQuery(SubAccountRow.TYPE);
            l_subAccountParams = TestDBUtility.getSubAccountRow();
            l_subAccountParams.setAccountId(333812512246L);
            l_subAccountParams.setSubAccountType(SubAccountTypeEnum.EQUITY_MARGIN_SUB_ACCOUNT);
            TestDBUtility.insertWithDel(l_subAccountParams);

            BranchPreferencesParams l_branchPreferencesParams = new BranchPreferencesParams();
            l_processor.doDeleteAllQuery(BranchPreferencesRow.TYPE);
            l_branchPreferencesParams = TestDBUtility.getBranchPreferencesRow();
            l_branchPreferencesParams.setBranchId(33381);
            l_branchPreferencesParams.setName("today.clearance.determination.div");
            l_branchPreferencesParams.setValue("1");
            TestDBUtility.insertWithDel(l_branchPreferencesParams);

            l_processor.doDeleteAllQuery(SystemPreferencesRow.TYPE);

            OrderexecutionEndParams l_qrderexecutionEndParams = new OrderexecutionEndParams();
            l_processor.doDeleteAllQuery(OrderexecutionEndRow.TYPE);
            l_qrderexecutionEndParams.setInstitutionCode("0D");
            l_qrderexecutionEndParams.setProductType(ProductTypeEnum.EQUITY);
            l_qrderexecutionEndParams.setFutureOptionDiv("0");
            l_qrderexecutionEndParams.setCreatedTimestamp(WEB3DateUtility.getDate("20070728","yyyyMMdd"));
            l_qrderexecutionEndParams.setLastUpdatedTimestamp(WEB3DateUtility.getDate("20070728","yyyyMMdd"));
            l_qrderexecutionEndParams.setOrderexecutionEndType("0");
            TestDBUtility.insertWithDel(l_qrderexecutionEndParams);

            TradingpowerCalcConditionParams l_tradingpowerCalcConditionParams = new TradingpowerCalcConditionParams();
            l_processor.doDeleteAllQuery(TradingpowerCalcConditionRow.TYPE);
            l_tradingpowerCalcConditionParams = TestDBUtility.getTradingpowerCalcConditionRow();
            l_tradingpowerCalcConditionParams.setAccountId(333812512246L);
            l_tradingpowerCalcConditionParams.setAdditionalDepositStop("1");
            TestDBUtility.insertWithDel(l_tradingpowerCalcConditionParams);

            TpCalcResultMarginParams l_tpCalcResultMarginParams = new TpCalcResultMarginParams();
            l_processor.doDeleteAllQuery(TpCalcResultMarginRow.TYPE);
            l_tpCalcResultMarginParams = TestDBUtility.getTpCalcResultMarginRow();
            l_tpCalcResultMarginParams.setCreatedTimestamp(WEB3DateUtility.getDate("20070728","yyyyMMdd"));
            
            l_tpCalcResultMarginParams.setContractAmount0(50);
            TestDBUtility.insertWithDel(l_tpCalcResultMarginParams);

            l_processor.doDeleteAllQuery(ProcessManagementRow.TYPE);

            TpCalcResultMarginDetailParams l_tpCalcResultMarginDetailParams = new TpCalcResultMarginDetailParams();
            l_processor.doDeleteAllQuery(TpCalcResultMarginDetailRow.TYPE);
            l_tpCalcResultMarginDetailParams = TestDBUtility.getTpCalcResultMarginDetailRow();
            
            l_tpCalcResultMarginDetailParams.setTodayRepayContractAmount(100);
            
            TestDBUtility.insertWithDel(l_tpCalcResultMarginDetailParams);

            TpCashBalanceParams l_tpCashBalanceParams = new TpCashBalanceParams();
            l_processor.doDeleteAllQuery(TpCashBalanceRow.TYPE);
            l_tpCashBalanceParams.setAccountId(333812512246L);
            l_tpCashBalanceParams.setSubAccountId(33381251220301L);
            
            l_tpCashBalanceParams.setCashBalance0(120);
            TestDBUtility.insertWithDel(l_tpCashBalanceParams);

            AioOrderUnitParams l_aioOrderUnitParams = new AioOrderUnitParams();
            l_processor.doDeleteAllQuery(AioOrderUnitRow.TYPE);
            l_aioOrderUnitParams = TestDBUtility.getAioOrderUnitRow();
            l_aioOrderUnitParams.setAccountId(333812512246L);
            l_aioOrderUnitParams.setSubAccountId(33381251220301L);
            l_aioOrderUnitParams.setOrderType(OrderTypeEnum.FROM_DEPOSIT_AMOUNT_MARGIN_GUARANTEE);
            l_aioOrderUnitParams.setDeliveryDate(WEB3DateUtility.getDate("20070728","yyyyMMdd"));
            l_aioOrderUnitParams.setOrderStatus(OrderStatusEnum.ACCEPTED);
            TestDBUtility.insertWithDel(l_aioOrderUnitParams);
            l_aioOrderUnitParams.setOrderUnitId(0L);
            l_aioOrderUnitParams.setOrderType(OrderTypeEnum.MARGIN_GUARANTEE_FROM_DEPOSIT_AMOUNT);
            TestDBUtility.insertWithDel(l_aioOrderUnitParams);

            //実行
            WEB3TPPaymentRequisitionManagement l_paymentRequisitionManagement = WEB3TPPaymentRequisitionManagement.
            createPaymentRequisitionManagement(new WEB3GentradeMainAccount(l_mainAccountParams));
            l_paymentRequisitionManagement.createSecondAdddepositNotClearInfo();
            

            //比較
//            assertNull(l_paymentRequisitionManagement.tpCalcEquity);
//            assertEquals("1", l_paymentRequisitionManagement.marginEquityJudgeFlag);
        }
        catch (Exception l_ex)
        {
            log.debug(STR_METHOD_NAME,l_ex);
            fail();
        }
        log.exiting(TEST_END + STR_METHOD_NAME);
    }
    
    public void test_createSecondAdddepositNotClearInfo_0002()
    {
        final String STR_METHOD_NAME = "test_createSecondAdddepositNotClearInfo_0002()";
        log.entering(TEST_START + STR_METHOD_NAME);

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
            
            
//            //スタティック
//            Calendar l_calendar =  Calendar.getInstance();
//            l_calendar.set(2007, 7-1, 28);
//            Timestamp l_tsAppliyDate = new Timestamp(l_calendar.getTimeInMillis());
//            ThreadLocalSystemAttributesRegistry.setAttribute(
//                "xblocks.gtl.attributes.systemtimestamp",
//                l_tsAppliyDate);

            WEB3GentradeTradingClendarContext l_clendarContext = new WEB3GentradeTradingClendarContext();
//            l_clendarContext
            ThreadLocalSystemAttributesRegistry.setAttribute("web3.tradingcalendarcontext", l_clendarContext);

            //DB
            MainAccountParams l_mainAccountParams = new MainAccountParams();
            QueryProcessor l_processor = Processors.getDefaultProcessor();
            l_processor.doDeleteAllQuery(MainAccountRow.TYPE);
            l_mainAccountParams = TestDBUtility.getMainAccountRow();
            l_mainAccountParams.setMarginSysAccOpenDiv("1");    //the third case
            l_mainAccountParams.setSecuredLoanSecAccOpenDiv("0");
            TestDBUtility.insertWithDel(l_mainAccountParams);

            InstitutionParams l_institutionParams =  new InstitutionParams();
            l_processor.doDeleteAllQuery(InstitutionRow.TYPE);
            l_institutionParams = TestDBUtility.getInstitutionRow();
            l_institutionParams.setAssetEvaluation("0");
            l_institutionParams.setMaximumAssessment(1L);
            TestDBUtility.insertWithDel(l_institutionParams);

            BranchParams l_branchParams =  new BranchParams();
            l_processor.doDeleteAllQuery(BranchRow.TYPE);
            l_branchParams = TestDBUtility.getBranchRow();
            TestDBUtility.insertWithDel(l_branchParams);

            PaymentRequisitMngParams l_paymentRequisitMngParams = new PaymentRequisitMngParams();
            l_processor.doDeleteAllQuery(PaymentRequisitMngRow.TYPE);
            l_paymentRequisitMngParams.setAccountId(333812512246L);
            l_paymentRequisitMngParams.setAccountCode("2512246");
            l_paymentRequisitMngParams.setBranchCode("381");
            l_paymentRequisitMngParams.setInstitutionCode("0D");
            l_paymentRequisitMngParams.setFamilyName("Family Name");
            l_paymentRequisitMngParams.setAccountAttribute("0");
            l_paymentRequisitMngParams.setCalcDate(WEB3DateUtility.getDate("20070728","yyyyMMdd"));
            l_paymentRequisitMngParams.setCreatedTimestamp(WEB3DateUtility.getDate("20070728","yyyyMMdd"));
            l_paymentRequisitMngParams.setLastUpdatedTimestamp(WEB3DateUtility.getDate("20070728","yyyyMMdd"));
            
            l_paymentRequisitMngParams.setCashMarginDeposit(50);
            //第二水準追証未入金
            l_paymentRequisitMngParams.setSecondDepositNonPay(210);
            //第二水準追証請求(2)
            l_paymentRequisitMngParams.setSecondDeposit2(36);
            //第二水準追証未入金決済必要額
            l_paymentRequisitMngParams.setSecondSettlementNonPay(26);
            //第二水準追証請求(2)
            l_paymentRequisitMngParams.setSecondDeposit2(2);
            //第二水準追証決済必要額(2)
            l_paymentRequisitMngParams.setSecondSettlement2(45.5);
            //第二水準追証請求(1)
            l_paymentRequisitMngParams.setSecondDeposit1(23);
            //第二水準追証決済必要額(1)
            l_paymentRequisitMngParams.setSecondSettlement1(12.6);
            //
            l_paymentRequisitMngParams.setSecondDepositBackRate(20);
            TestDBUtility.insertWithDel(l_paymentRequisitMngParams);

            SubAccountParams l_subAccountParams = new SubAccountParams();
            l_processor.doDeleteAllQuery(SubAccountRow.TYPE);
            l_subAccountParams = TestDBUtility.getSubAccountRow();
            l_subAccountParams.setAccountId(333812512246L);
            l_subAccountParams.setSubAccountType(SubAccountTypeEnum.EQUITY_MARGIN_SUB_ACCOUNT);
            TestDBUtility.insertWithDel(l_subAccountParams);

            BranchPreferencesParams l_branchPreferencesParams = new BranchPreferencesParams();
            l_processor.doDeleteAllQuery(BranchPreferencesRow.TYPE);
            l_branchPreferencesParams = TestDBUtility.getBranchPreferencesRow();
            l_branchPreferencesParams.setBranchId(33381);
            l_branchPreferencesParams.setName("today.clearance.determination.div");
            l_branchPreferencesParams.setValue("1");
            TestDBUtility.insertWithDel(l_branchPreferencesParams);

            l_processor.doDeleteAllQuery(SystemPreferencesRow.TYPE);

            OrderexecutionEndParams l_qrderexecutionEndParams = new OrderexecutionEndParams();
            l_processor.doDeleteAllQuery(OrderexecutionEndRow.TYPE);
            l_qrderexecutionEndParams.setInstitutionCode("0D");
            l_qrderexecutionEndParams.setProductType(ProductTypeEnum.EQUITY);
            l_qrderexecutionEndParams.setFutureOptionDiv("0");
            l_qrderexecutionEndParams.setCreatedTimestamp(WEB3DateUtility.getDate("20070728","yyyyMMdd"));
            l_qrderexecutionEndParams.setLastUpdatedTimestamp(WEB3DateUtility.getDate("20070728","yyyyMMdd"));
            l_qrderexecutionEndParams.setOrderexecutionEndType("0");
            TestDBUtility.insertWithDel(l_qrderexecutionEndParams);

            TradingpowerCalcConditionParams l_tradingpowerCalcConditionParams = new TradingpowerCalcConditionParams();
            l_processor.doDeleteAllQuery(TradingpowerCalcConditionRow.TYPE);
            l_tradingpowerCalcConditionParams = TestDBUtility.getTradingpowerCalcConditionRow();
            l_tradingpowerCalcConditionParams.setAccountId(333812512246L);
            l_tradingpowerCalcConditionParams.setAdditionalDepositStop("1");
            TestDBUtility.insertWithDel(l_tradingpowerCalcConditionParams);

            TpCalcResultMarginParams l_tpCalcResultMarginParams = new TpCalcResultMarginParams();
            l_processor.doDeleteAllQuery(TpCalcResultMarginRow.TYPE);
            l_tpCalcResultMarginParams = TestDBUtility.getTpCalcResultMarginRow();
            l_tpCalcResultMarginParams.setCreatedTimestamp(WEB3DateUtility.getDate("20070728","yyyyMMdd"));
            
            l_tpCalcResultMarginParams.setContractAmount0(50);
            TestDBUtility.insertWithDel(l_tpCalcResultMarginParams);

            l_processor.doDeleteAllQuery(ProcessManagementRow.TYPE);

            TpCalcResultMarginDetailParams l_tpCalcResultMarginDetailParams = new TpCalcResultMarginDetailParams();
            l_processor.doDeleteAllQuery(TpCalcResultMarginDetailRow.TYPE);
            l_tpCalcResultMarginDetailParams = TestDBUtility.getTpCalcResultMarginDetailRow();
            
            l_tpCalcResultMarginDetailParams.setTodayRepayContractAmount(100);
            
            TestDBUtility.insertWithDel(l_tpCalcResultMarginDetailParams);

            TpCashBalanceParams l_tpCashBalanceParams = new TpCashBalanceParams();
            l_processor.doDeleteAllQuery(TpCashBalanceRow.TYPE);
            l_tpCashBalanceParams.setAccountId(333812512246L);
            l_tpCashBalanceParams.setSubAccountId(33381251220301L);
            
            l_tpCashBalanceParams.setCashBalance0(120);
            TestDBUtility.insertWithDel(l_tpCashBalanceParams);

            AioOrderUnitParams l_aioOrderUnitParams = new AioOrderUnitParams();
            l_processor.doDeleteAllQuery(AioOrderUnitRow.TYPE);
            l_aioOrderUnitParams = TestDBUtility.getAioOrderUnitRow();
            l_aioOrderUnitParams.setAccountId(333812512246L);
            l_aioOrderUnitParams.setSubAccountId(33381251220301L);
            l_aioOrderUnitParams.setOrderType(OrderTypeEnum.FROM_DEPOSIT_AMOUNT_MARGIN_GUARANTEE);
            l_aioOrderUnitParams.setDeliveryDate(WEB3DateUtility.getDate("20070728","yyyyMMdd"));
            l_aioOrderUnitParams.setOrderStatus(OrderStatusEnum.ACCEPTED);
            TestDBUtility.insertWithDel(l_aioOrderUnitParams);
            l_aioOrderUnitParams.setOrderUnitId(0L);
            l_aioOrderUnitParams.setOrderType(OrderTypeEnum.MARGIN_GUARANTEE_FROM_DEPOSIT_AMOUNT);
            TestDBUtility.insertWithDel(l_aioOrderUnitParams);

            //実行
            WEB3TPPaymentRequisitionManagement l_paymentRequisitionManagement = WEB3TPPaymentRequisitionManagement.
            createPaymentRequisitionManagement(new WEB3GentradeMainAccount(l_mainAccountParams));
            l_paymentRequisitionManagement.depositAutoTransferDivFlag = true;
            l_paymentRequisitionManagement.createSecondAdddepositNotClearInfo();

            //比較
//            assertNull(l_paymentRequisitionManagement.tpCalcEquity);
//            assertEquals("1", l_paymentRequisitionManagement.marginEquityJudgeFlag);
        }
        catch (Exception l_ex)
        {
            log.debug(STR_METHOD_NAME,l_ex);
            fail();
        }
        log.exiting(TEST_END + STR_METHOD_NAME);
    }
}
@
