head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.04.07.02.35.13;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	5444d9d078c63f4;
filename	WEB3TPCashDepositRestraintReflectorTest.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : //TODO(WEB3TPCashDepositRestraintReflectorTest.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2006/09/19 徐宏偉 (中訊) 新規作成
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

import webbroker3.common.define.WEB3OrderAccProductDef;
import webbroker3.common.define.WEB3OrderAccTransactionDef;
import webbroker3.common.define.WEB3TradingTimeTypeDef;
import webbroker3.gentrade.WEB3GentradeSubAccount;
import webbroker3.mock.TestBaseForMock;
import webbroker3.tradingpower.WEB3TPCalcCondition;
import webbroker3.tradingpower.updtpower.cash.WEB3TPCashDepositRestraintReflector;
import webbroker3.util.WEB3DateUtility;
import webbroker3.util.WEB3LogUtility;

/**
 * XXXXXXクラス//TODO
 *
 * @@author 徐宏偉(中訊)
 * @@version 1.0
 */
public class WEB3TPCashDepositRestraintReflectorTest extends TestBaseForMock
{
    /**
     * ログ出力ユーティリティ。
     */
    private static WEB3LogUtility log = 
        WEB3LogUtility.getInstance(WEB3TPCashDepositRestraintReflectorTest.class);
    
    private WEB3TPCashDepositRestraintReflector l_restrain = 
        new WEB3TPCashDepositRestraintReflector();
    
    private FinApp l_finApp = (FinApp)Services.getService(FinApp.class);
    
    public WEB3TPCashDepositRestraintReflectorTest(String arg0)
    {
        super(arg0);
    }

    protected void setUp() throws Exception
    {
        QueryProcessor l_processor = Processors.getDefaultProcessor();
        MainAccountRow l_mainAccountRow =
            (MainAccountRow)MainAccountDao.findRowByAccountId(266242662410L);
        MainAccountParams l_params = new MainAccountParams(l_mainAccountRow);
        l_params.setMarginGenAccOpenDiv("1");
        l_params.setMarginSysAccOpenDiv("1");
        l_processor.doUpdateQuery(l_params);
        super.doSettingTradingClendarContext(
                "0",
                "670",
                "07",
                WEB3TradingTimeTypeDef.PAYMENT,
                "0",
                WEB3OrderAccProductDef.PAYMENT,
                WEB3OrderAccTransactionDef.PAYMENT);
        }

    protected void tearDown() throws Exception 
    {
        doClearTradingClendarContext();
        super.tearDown();
    }
    
    public void testCreate()
    {
        final String STR_METHOD_NAME = "testCreate()";
        log.entering(STR_METHOD_NAME);
        
        try
        {
            WEB3GentradeSubAccount l_subAccount = 
                (WEB3GentradeSubAccount)l_finApp.getAccountManager().getSubAccount(266242662410L, 26624266241001L);
            
            WEB3TPCalcCondition l_calcCondition = WEB3TPCalcCondition.createCalcConditionStandard(l_subAccount);
            double l_dblPaymentStopAmount = 0;
            Date l_datDealDate = WEB3DateUtility.getDate("20040715", "yyyyMMdd");
            
            WEB3TPCashDepositRestraintReflector l_DepositRestraintReflector =
                WEB3TPCashDepositRestraintReflector.createCashDepositRestraint(
                    l_calcCondition, 
                    l_dblPaymentStopAmount, 
                    l_datDealDate);
            assertEquals(0, l_DepositRestraintReflector.getAmount(), 0);
            assertEquals(l_calcCondition, l_DepositRestraintReflector.getCalcCondition());
        }
        catch (Exception l_ex)
        {
            log.error("Error in Exception .....", l_ex);
            fail();
        }
        
        log.exiting(STR_METHOD_NAME);
    }
    
    public void testCalcReflectDay()
    {
        final String STR_METHOD_NAME = "testCalcReflectDay()";
        log.entering(STR_METHOD_NAME);
        
        try
        {
            WEB3GentradeSubAccount l_subAccount = 
                (WEB3GentradeSubAccount)l_finApp.getAccountManager().getSubAccount(266242662410L, 26624266241001L);
            
            WEB3TPCalcCondition l_calcCondition = WEB3TPCalcCondition.createCalcConditionStandard(l_subAccount);
            double l_dblPaymentStopAmount = 0;
            Date l_datDealDate = WEB3DateUtility.getDate("20040715", "yyyyMMdd");
            
            WEB3TPCashDepositRestraintReflector l_DepositRestraintReflector =
                WEB3TPCashDepositRestraintReflector.createCashDepositRestraint(
                    l_calcCondition, 
                    l_dblPaymentStopAmount, 
                    l_datDealDate);
        }
        catch (Exception l_ex)
        {
            log.error("Error in Exception .....", l_ex);
            fail();
        }
        
        log.exiting(STR_METHOD_NAME);
    }
}
@
