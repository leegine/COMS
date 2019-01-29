head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.04.07.01.22.42;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	5444d9d078c63f4;
filename	WEB3GentradeAllTests.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : (WEB3GentradeAllTests.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2007/02/07 栄イ (中訊) 新規作成
*/

package webbroker3.gentrade;

import junit.framework.Test;
import junit.framework.TestSuite;
import webbroker3.gentrade.handler.WEB3ExpirationDateListHandlerTest;
import webbroker3.gentrade.message.WEB3ExpirationDateListRequestTest;
import webbroker3.gentrade.service.delegate.stdimpls.WEB3ExpirationDateListServiceImplTest;

/**
 * テストクラス
 *
 * @@author 栄イ (中訊)
 * @@version 1.0
 */
public class WEB3GentradeAllTests
{
    public static Test suite()
    {
        TestSuite suite = new TestSuite("Test for webbroker3.gentrade");
        //$JUnit-BEGIN$

        suite.addTestSuite(WEB3ExpirationDateListServiceInterceptorTest.class);
        suite.addTestSuite(WEB3GentradeBizDateTest.class);
        suite.addTestSuite(WEB3GentradeBizLogicProviderTest.class);
        suite.addTestSuite(WEB3GentradeBranchListmarketDealtCondTest.class);
        suite.addTestSuite(WEB3GentradeBranchMarketPTSDealtCondTest.class);
        suite.addTestSuite(WEB3GentradeBranchTest.class);
        suite.addTestSuite(WEB3GentradeCommissionTest.class);
        suite.addTestSuite(WEB3GentradeCsvDataModelTest.class);
        suite.addTestSuite(WEB3GentradeHandlingOrderCondTest.class);
        suite.addTestSuite(WEB3GentradeInstitutionTest.class);
        suite.addTestSuite(WEB3GentradeMainAccountTest.class);
        suite.addTestSuite(WEB3GentradeMarketTest.class);
        suite.addTestSuite(WEB3GentradeOrderValidatorTest.class);
        suite.addTestSuite(WEB3GentradeSrvRegiApplicationTest.class);
        suite.addTestSuite(WEB3GentradeTradingCalendarModelImplTest.class);
        suite.addTestSuite(WEB3GentradeTradingTimeManagementTest.class);
        suite.addTestSuite(WEB3ExpirationDateListHandlerTest.class);
        suite.addTestSuite(WEB3ExpirationDateListRequestTest.class);
        suite.addTestSuite(WEB3ExpirationDateListServiceImplTest.class);
        //$JUnit-END$
        return suite;

    }
}
@
