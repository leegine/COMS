head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.04.07.00.40.35;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	5444d9d078c63f4;
filename	WEB3TplibInfoAllTests.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : WEB3TplibInfoAllTests.java
Author Name      : Daiwa Institute of Research
Revesion History : 2007/08/09 金傑 (中訊) 新規作成
*/

package webbroker3.tradingpower;

import junit.framework.Test;
import junit.framework.TestSuite;

import webbroker3.tradingpower.handler.WEB3TPAdditionalGenerationHandlerTest;
import webbroker3.tradingpower.handler.WEB3TPShortfallGenerationHandlerTest;
import webbroker3.tradingpower.service.delegate.stdimpls.WEB3TPAssetTradingPowerServiceImplTest;


/**
 *
 * @@author 金傑(中訊)
 * @@version 1.0
 */
public class WEB3TplibInfoAllTests
{
    private static boolean WEB3TPAssetTradingPowerServiceImplTest = true;
    
    public static Test suite()
    {
        TestSuite suite = new TestSuite("Test for webbroker3.tradingpower");
        //$JUnit-BEGIN$
        
        if (WEB3TPAssetTradingPowerServiceImplTest) 
        {
            suite.addTestSuite(WEB3TPAssetTradingPowerServiceImplTest.class);
            suite.addTestSuite(WEB3TPAdditionalGenerationHandlerTest.class);
            suite.addTestSuite(WEB3TPShortfallGenerationHandlerTest.class);
        }
        return suite;
    }

}
@
