head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.04.07.02.09.11;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	5444d9d078c63f4;
filename	AccountOpenAllTests.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@package webbroker3.accountopen;

import junit.framework.Test;
import junit.framework.TestSuite;

import webbroker3.accountopen.handler.WEB3AdminAccOpenApplyDataDelHandlerTest;
import webbroker3.accountopen.handler.WEB3AdminAccOpenApplyULHandlerTest;
import webbroker3.accountopen.message.WEB3AdminAccOpenApplyDataDelCmpRequestTest;
import webbroker3.accountopen.message.WEB3AdminAccOpenApplyDataDelCnfRequestTest;
import webbroker3.accountopen.message.WEB3AdminAccOpenApplyUploadCancelRequestTest;
import webbroker3.accountopen.message.WEB3AdminAccOpenApplyUploadCompleteRequestTest;
import webbroker3.accountopen.message.WEB3AdminAccOpenApplyUploadConfirmRequestTest;
import webbroker3.accountopen.service.delegate.stdimpls.WEB3AccOpenInformAcceptUnitServiceImplTest;
import webbroker3.accountopen.service.delegate.stdimpls.WEB3AccOpenRealUnitServiceImplTest;
import webbroker3.accountopen.service.delegate.stdimpls.WEB3AccOpenVoucherRegAcceptServiceImplTest;
import webbroker3.accountopen.service.delegate.stdimpls.WEB3AccOpenVoucherRegAcceptUnitServiceImplTest;
import webbroker3.accountopen.service.delegate.stdimpls.WEB3AdminAccOpenApplyDataDelServiceImplTest;
import webbroker3.accountopen.service.delegate.stdimpls.WEB3AdminAccOpenApplyULServiceImplTest;

public class AccountOpenAllTests
{

    public static Test suite()
    {
        TestSuite suite = new TestSuite("Test for webbroker3.accountopen");
        //$JUnit-BEGIN$
        suite.addTestSuite(WEB3AccOpenAccountRegVoucherTest.class);
        suite.addTestSuite(WEB3AccOpenAccountRegBrokerageFirmVoucherTest.class);
        suite.addTestSuite(WEB3AdminAccOpenApplyULCsvTest_WB.class);
        suite.addTestSuite(WEB3AdminAccOpenApplyULCsvTest.class);
        suite.addTestSuite(WEB3AccOpenInformAcceptUnitServiceInterceptorTest.class);
        suite.addTestSuite(WEB3AccOpenBankTransVoucherTest.class);
        suite.addTestSuite(WEB3AccOpenPostalTransVoucherTest.class);
        suite.addTestSuite(WEB3AccOpenExpAccountOpenTest.class);

        suite.addTestSuite(WEB3AdminAccOpenApplyULHandlerTest.class);
        suite.addTestSuite(WEB3AdminAccOpenApplyDataDelHandlerTest.class);

        suite.addTestSuite(WEB3AdminAccOpenApplyUploadCancelRequestTest.class);
        suite.addTestSuite(WEB3AdminAccOpenApplyUploadCompleteRequestTest.class);
        suite.addTestSuite(WEB3AdminAccOpenApplyUploadConfirmRequestTest.class);
        suite.addTestSuite(WEB3AdminAccOpenApplyDataDelCmpRequestTest.class);
        suite.addTestSuite(WEB3AdminAccOpenApplyDataDelCnfRequestTest.class);

        suite.addTestSuite(WEB3AccOpenInformAcceptUnitServiceImplTest.class);
        suite.addTestSuite(WEB3AccOpenRealUnitServiceImplTest.class);
        suite.addTestSuite(WEB3AccOpenVoucherRegAcceptServiceImplTest.class);
        suite.addTestSuite(WEB3AccOpenVoucherRegAcceptUnitServiceImplTest.class);
        suite.addTestSuite(WEB3AdminAccOpenApplyULServiceImplTest.class);
        suite.addTestSuite(WEB3AdminAccOpenApplyULServiceInterceptorTest.class);
        suite.addTestSuite(WEB3AdminAccOpenApplyDataDelServiceImplTest.class);

        //$JUnit-END$
        return suite;
    }

}
@
