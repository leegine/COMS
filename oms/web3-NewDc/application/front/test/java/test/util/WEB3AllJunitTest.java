head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.14.02.32.04;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	89c4d7d7dfd671c;
filename	WEB3AllJunitTest.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@package test.util;

import junit.framework.Test;
import junit.framework.TestSuite;

public class WEB3AllJunitTest {

	public static Test suite() {
		TestSuite suite = new TestSuite("Test for test.util");
		//$JUnit-BEGIN$
        suite.addTest(webbroker3.WEB3MockAllTests.suite());
        suite.addTest(webbroker3.accountinfo.WEB3AccInfoAllTests.suite());
        suite.addTest(webbroker3.accountopen.AccountOpenAllTests.suite());
        suite.addTest(webbroker3.adminorderexecinquiry.WEB3AdminOrderExecinquiryAllTests.suite());
        suite.addTest(webbroker3.admintriggerorder.WEB3AdminToAllTests.suite());             
        suite.addTest(webbroker3.aio.WEB3AioAllTests.suite());                
        suite.addTest(webbroker3.compliance.audit.ComplianceAllTest.suite()); 
        suite.addTest(webbroker3.dirsec.WEB3DirsecAllTests.suite());              
        suite.addTest(webbroker3.docadmin.WEB3DocadminAllTests.suite());
        suite.addTest(webbroker3.eqtypeadmin.WEB3EqtypeAdminAllTests.suite());             
        suite.addTest(webbroker3.equity.WEB3EquityAllTests.suite());
        suite.addTest(webbroker3.feq.WEB3FeqAllTests.suite());        
        suite.addTest(webbroker3.gentrade.WEB3GentradeAllTests.suite());             
        suite.addTest(webbroker3.ifo.WEB3IFOAllTests.suite());          
        suite.addTest(webbroker3.ifoadmin.WEB3AdminIfoAllTests.suite());             
        suite.addTest(webbroker3.ifodeposit.WEB3IfodepositAllTests.suite());             
        suite.addTest(webbroker3.inform.WEB3InformAllTests.suite());                                       
        suite.addTest(webbroker3.login.WEB3LoginAllTests.suite());       
        suite.addTest(webbroker3.mf.WEB3XbmfAllTests.suite());
        suite.addTest(webbroker3.srvregi.WEB3SrvRegiAllTests.suite());             
        suite.addTest(webbroker3.tradingpower.WEB3TplibAllTests.suite());
        suite.addTest(webbroker3.trademanagement.WEB3TrademanagementAllTests.suite());                                                    
        suite.addTest(webbroker3.triggerorder.WEB3TriggerOrderAllTests.suite());                                                    
        suite.addTest(webbroker3.util.WEB3UtilAllTests.suite());
		//$JUnit-END$
		return suite;
	}
}
@
