head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.04.07.00.49.05;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	5444d9d078c63f4;
filename	WEB3TPShortfallOccurInfoTest.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : WEB3IfoContractTest
Author Name      : Daiwa Institute of Research
Revision History : 2008/10/22 陸文靜（中訊）新規作成
*/
package webbroker3.tradingpower;

import java.util.Date;

import test.util.TestDBUtility;

import webbroker3.mock.TestBaseForMock;
import webbroker3.tradingpower.data.PaymentRequisitMngParams;
import webbroker3.tradingpower.data.PaymentRequisitMngRow;
import webbroker3.tradingpower.define.WEB3TPMarginEquityJudgeFlagDef;
import webbroker3.util.WEB3DateUtility;
import webbroker3.util.WEB3LogUtility;
import webbroker3.util.WEB3StringTypeUtility;

public class WEB3TPShortfallOccurInfoTest extends TestBaseForMock
{
    /**
     * ログ出力ユーティリティ。<BR>
     */
    private static WEB3LogUtility log = WEB3LogUtility.getInstance(
            WEB3TPShortfallOccurInfoTest.class);

    public WEB3TPShortfallOccurInfoTest(String arg0)
    {
        super(arg0);
        // TODO Auto-generated constructor stub
    }
    protected void setUp() throws Exception
    {
        super.setUp();
    }
    protected void tearDown() throws Exception
    {
        super.tearDown();
    }

    public void testcreateShortfallGenerationInfo_C0001()
    {
        final String STR_METHOD_NAME = "testcreateShortfallGenerationInfo_C0001()";
        log.entering(STR_METHOD_NAME);

        try
        {
            WEB3TPPaymentRequisitionManagementForTest l_paymentRequisitionManagementForTest =
                new WEB3TPPaymentRequisitionManagementForTest();
            //保証金自動振替後判定フラグ = true
            boolean l_blnDepositAutoTransferDivFlag = true;
            l_paymentRequisitionManagementForTest.setDepositAutoTransferDivFlag(l_blnDepositAutoTransferDivFlag);
            
            WEB3TPTradingPowerCalcEquity l_tradingPowerCalcEquity = new WEB3TPTradingPowerCalcEquity();
            WEB3TPCalcCondition l_calcCondition = new WEB3TPCalcCondition();
            
            Date[] l_datBizDate = new Date[9];
            for(int i = 0;i < 9;i++)
            {
                l_datBizDate[i] = new Date();
                l_datBizDate[i] = WEB3DateUtility.getDate("20040702","yyyyMMdd");
            }
            l_calcCondition.setBizDate(l_datBizDate);
            
            l_tradingPowerCalcEquity.setCalcCondition(l_calcCondition);
            l_paymentRequisitionManagementForTest.setTpCalcEquity(l_tradingPowerCalcEquity);

            //立替金 = 10
            TestDBUtility.deleteAll(PaymentRequisitMngRow.TYPE);
            PaymentRequisitMngParams l_paymentRequisitMngParams = TestDBUtility.getPaymentRequisitMngParams();
            l_paymentRequisitMngParams.setDebitAmount(10);
            l_paymentRequisitMngParams.setSpecialDebitAmount(10);
            TestDBUtility.insertWithDel(l_paymentRequisitMngParams);

            l_paymentRequisitionManagementForTest.setPaymentRequisitMngParams(l_paymentRequisitMngParams);
            String l_strMarginEquityJudgeFlag = WEB3TPMarginEquityJudgeFlagDef.EQUITY_ACCOUNT;
            l_paymentRequisitionManagementForTest.setMarginEquityJudgeFlag(l_strMarginEquityJudgeFlag);

            int l_intSpecifiedPoint = 10;
            l_paymentRequisitionManagementForTest.getLackAccountBalance(l_intSpecifiedPoint);

            WEB3TPShortfallOccurInfo l_shortfallOccurInfo =
                WEB3TPShortfallOccurInfo.createShortfallGenerationInfo(l_paymentRequisitionManagementForTest);

            assertEquals(true,l_shortfallOccurInfo.depositAutoTransferDivFlag);
            assertEquals("10",WEB3StringTypeUtility.formatNumber(l_shortfallOccurInfo.debitAmount));
            assertEquals("10",WEB3StringTypeUtility.formatNumber(l_shortfallOccurInfo.specialDebitAmount));
            assertEquals("20040702",WEB3DateUtility.formatDate(l_shortfallOccurInfo.closeDate0,"yyyyMMdd"));
            assertEquals("20040702",WEB3DateUtility.formatDate(l_shortfallOccurInfo.closeDate1,"yyyyMMdd"));
            assertEquals("20040702",WEB3DateUtility.formatDate(l_shortfallOccurInfo.closeDate2,"yyyyMMdd"));
            assertEquals("20040702",WEB3DateUtility.formatDate(l_shortfallOccurInfo.closeDate3,"yyyyMMdd"));
            assertEquals("20040702",WEB3DateUtility.formatDate(l_shortfallOccurInfo.closeDate4,"yyyyMMdd"));
            assertEquals("20040702",WEB3DateUtility.formatDate(l_shortfallOccurInfo.closeDate5,"yyyyMMdd"));
            assertEquals("1",WEB3StringTypeUtility.formatNumber(l_shortfallOccurInfo.requiredPayAmt0));
            assertEquals("1",WEB3StringTypeUtility.formatNumber(l_shortfallOccurInfo.requiredPayAmt1));
            assertEquals("1",WEB3StringTypeUtility.formatNumber(l_shortfallOccurInfo.requiredPayAmt2));
            assertEquals("1",WEB3StringTypeUtility.formatNumber(l_shortfallOccurInfo.requiredPayAmt3));
            assertEquals("1",WEB3StringTypeUtility.formatNumber(l_shortfallOccurInfo.requiredPayAmt4));
            assertEquals("1",WEB3StringTypeUtility.formatNumber(l_shortfallOccurInfo.requiredPayAmt5));
            assertEquals("0",WEB3StringTypeUtility.formatNumber(l_shortfallOccurInfo.adjustedAmt0));
            assertEquals("0",WEB3StringTypeUtility.formatNumber(l_shortfallOccurInfo.adjustedAmt1));
            assertEquals("0",WEB3StringTypeUtility.formatNumber(l_shortfallOccurInfo.dayTradeRestraint0));
            assertEquals("0",WEB3StringTypeUtility.formatNumber(l_shortfallOccurInfo.dayTradeRestraint1));
            assertEquals("0",WEB3StringTypeUtility.formatNumber(l_shortfallOccurInfo.transferFromMarginDeposit0));
            assertEquals("0",WEB3StringTypeUtility.formatNumber(l_shortfallOccurInfo.transferFromMarginDeposit1));

            log.exiting(TEST_END + STR_METHOD_NAME);
        }
        catch(Exception l_ex)
        {
            log.error(l_ex.getMessage(), l_ex);
            log.exiting(TEST_END + STR_METHOD_NAME);
            fail();  
        }
    }
    
    class WEB3TPPaymentRequisitionManagementForTest extends WEB3TPPaymentRequisitionManagement
    {
        public double getLackAccountBalance(int l_intSpecifiedPoint) 
        {
            return 1;
        }
    }
}
@
