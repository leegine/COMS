head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.04.07.00.49.34;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	5444d9d078c63f4;
filename	WEB3TplibAllTests.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : WEB3TplibAllTests.java
Author Name      : Daiwa Institute of Research
Revesion History : 2007/01/9 謝旋 (中訊) 新規作成
*/

package webbroker3.tradingpower;

import junit.framework.Test;
import junit.framework.TestSuite;

import webbroker3.tradingpower.handler.WEB3TPAddDepositMailSendHandlerTest;
import webbroker3.tradingpower.service.delegate.stdimpls.WEB3TPAddDepositMailSendServiceImplTest;
import webbroker3.tradingpower.service.delegate.stdimpls.WEB3TPBondSimplexCooperationServiceImplTest;
import webbroker3.tradingpower.updapower.cash.WEB3TPRestraintTest;
import webbroker3.tradingpower.updtpower.WEB3TPNewOrderSpecTest;
import webbroker3.tradingpower.updtpower.WEB3TPPersistentDataManagerTest;
import webbroker3.tradingpower.updtpower.WEB3TPUnitPriceStandardCallbackTest;
import webbroker3.tradingpower.updtpower.asset.WEB3TPSecurityTransactionChangeTest;
import webbroker3.tradingpower.updtpower.asset.WEB3TPSecurityValuationPerProductTest;
import webbroker3.tradingpower.updtpower.asset.WEB3TPSecurityValuationTest;
import webbroker3.tradingpower.updtpower.cash.WEB3TPTempRestraintTest;
import webbroker3.tradingpower.updtpower.contract.WEB3TPHistoryPerContractTest;
import webbroker3.tradingpower.updtpower.settlement.WEB3TPAccountTransitionTest;
import webbroker3.tradingpower.updtpower.settlement.WEB3TPSettlementReflectorExcludeExceptSettleBuyAmountTest;
import webbroker3.tradingpower.updtpower.settlement.WEB3TPSettlementTest;

/**
 * XXXXXXクラス//TODO
 *
 * @@author 謝旋(中訊)
 * @@version 1.0
 */
public class WEB3TplibAllTests
{
 
    public static Test suite()
    {
        TestSuite suite = new TestSuite("Test for webbroker3.tradingpower");
        //$JUnit-BEGIN$
        suite.addTestSuite(WEB3ForeignPositionContractTest.class);
        suite.addTestSuite(WEB3TPCalcConditionTest.class);
        suite.addTestSuite(WEB3TPPaymentRequisitionManagementTest.class);
        suite.addTestSuite(WEB3TPTradingPowerCalcEquityTest.class);
        suite.addTestSuite(WEB3TPTradingPowerCalcMarginTest.class);
        suite.addTestSuite(WEB3TPTradingPowerServiceImplTest_temp.class);
        suite.addTestSuite(WEB3TPTradingPowerServiceImplTest_validateTradingPowerEquitySell.class);
        suite.addTestSuite(WEB3TPTradingPowerServiceImplTest.class);
        suite.addTestSuite(WEB3TPTradingPowerServiceImplTest3.class);
        suite.addTestSuite(WEB3TPTradingPowerUpdTest1.class);
        suite.addTestSuite(WEB3TPRestraintTest.class);
        suite.addTestSuite(WEB3TPNewOrderSpecTest.class);
        suite.addTestSuite(WEB3TPPersistentDataManagerTest.class);
        suite.addTestSuite(WEB3TPUnitPriceStandardCallbackTest.class);
        suite.addTestSuite(WEB3TPSecurityTransactionChangeTest.class);
        suite.addTestSuite(WEB3TPSecurityValuationPerProductTest.class);
        suite.addTestSuite(WEB3TPSecurityValuationTest.class);
        suite.addTestSuite(WEB3TPTempRestraintTest.class);
        suite.addTestSuite(WEB3TPHistoryPerContractTest.class);
        suite.addTestSuite(WEB3TPAccountTransitionTest.class);
        suite.addTestSuite(WEB3TPSettlementReflectorExcludeExceptSettleBuyAmountTest.class);
        suite.addTestSuite(WEB3TPSettlementTest.class);
        suite.addTestSuite(WEB3TPBondSimplexCooperationServiceImplTest.class);
        
        suite.addTestSuite(WEB3TPAddDepositMailSendServiceImplTest.class);
        suite.addTestSuite(WEB3TPAddDepositMailSendHandlerTest.class);
        //$JUnit-END$
        return suite;
    }

}
@
