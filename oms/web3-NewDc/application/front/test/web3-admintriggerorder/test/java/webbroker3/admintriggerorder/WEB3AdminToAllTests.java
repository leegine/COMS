head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.04.07.02.42.22;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	5444d9d078c63f4;
filename	WEB3AdminToAllTests.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@package webbroker3.admintriggerorder;

import junit.framework.Test;
import junit.framework.TestSuite;

import webbroker3.admintriggerorder.handler.WEB3AdminToEquityOrderReferenceHandlerTest;
import webbroker3.admintriggerorder.handler.WEB3AdminToIfoOrderReferenceHandlerTest;
import webbroker3.admintriggerorder.message.WEB3AdminToCommonRequestTest;
import webbroker3.admintriggerorder.message.WEB3AdminToEquityOrderRefInpRequestTest;
import webbroker3.admintriggerorder.message.WEB3AdminToIfoOrderRefInpRequestTest;
import webbroker3.admintriggerorder.message.WEB3AdminToManualLapseConfirmRequestTest;
import webbroker3.admintriggerorder.message.WEB3AdminToManualLapseInputRequestTest;
import webbroker3.admintriggerorder.message.WEB3AdminToManualLapseMainRequestTest;
import webbroker3.admintriggerorder.message.WEB3AdminToManualLapseRunRequestTest;
import webbroker3.admintriggerorder.message.WEB3AdminToManualLapseStatusRequestTest;
import webbroker3.admintriggerorder.message.WEB3AdminToTradeStopDelCompleteRequestTest;
import webbroker3.admintriggerorder.message.WEB3AdminToTradeStopDelConfirmRequestTest;
import webbroker3.admintriggerorder.service.delegate.stdimpls.WEB3AdminToIfoOrderReferenceServiceImplTest;

public class WEB3AdminToAllTests
{

    public static Test suite()
    {
        TestSuite suite = new TestSuite("Test for webbroker3.admintriggerorder");
        //$JUnit-BEGIN$
        suite.addTestSuite(WEB3AdminToEquityOrderReferenceHandlerTest.class);
        suite.addTestSuite(WEB3AdminToIfoOrderReferenceHandlerTest.class);
        suite.addTestSuite(WEB3AdminToCommonRequestTest.class);
        suite.addTestSuite(WEB3AdminToEquityOrderRefInpRequestTest.class);
        suite.addTestSuite(WEB3AdminToIfoOrderRefInpRequestTest.class);
        suite.addTestSuite(WEB3AdminToManualLapseConfirmRequestTest.class);
        suite.addTestSuite(WEB3AdminToManualLapseInputRequestTest.class);
        suite.addTestSuite(WEB3AdminToManualLapseMainRequestTest.class);
        suite.addTestSuite(WEB3AdminToManualLapseRunRequestTest.class);
        suite.addTestSuite(WEB3AdminToManualLapseStatusRequestTest.class);
        suite.addTestSuite(WEB3AdminToTradeStopDelCompleteRequestTest.class);
        suite.addTestSuite(WEB3AdminToTradeStopDelConfirmRequestTest.class);
        suite.addTestSuite(WEB3AdminToIfoOrderReferenceServiceImplTest.class);
        //$JUnit-END$
        return suite;
    }

}
@
