head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.04.07.02.36.14;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	5444d9d078c63f4;
filename	WEB3MockAllTests.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@package webbroker3;

import test.util.TestDBUtilityTest;
import test.util.TestSpecialClassUtilityTest;

import webbroker3.gentrade.WEB3AdministratorForMockTest;
import webbroker3.gentrade.WEB3GentradeFeqBranchMarketDealtCondForMockTest;
import webbroker3.gentrade.WEB3GentradeTradingTimeManagementForMockTest;
import webbroker3.mock.util.WEB3MockObjectManagerTest;

import junit.framework.Test;
import junit.framework.TestSuite;

public class WEB3MockAllTests
{

    public static Test suite()
    {
        TestSuite suite = new TestSuite("Test for webbroker3");
        //$JUnit-BEGIN$
        
        suite.addTestSuite(WEB3MockObjectManagerTest.class);
        suite.addTestSuite(TestDBUtilityTest.class);
        
        suite.addTestSuite(WEB3GentradeFeqBranchMarketDealtCondForMockTest.class);
        suite.addTestSuite(WEB3GentradeTradingTimeManagementForMockTest.class);
        suite.addTestSuite(WEB3GentradeFeqBranchMarketDealtCondForMockTest.class);        
        suite.addTestSuite(WEB3AdministratorForMockTest.class);
        
        //$JUnit-END$
        return suite;
    }

}
@
