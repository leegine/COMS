head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.04.07.02.29.32;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	5444d9d078c63f4;
filename	WEB3AdminIfoAllTests.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@package webbroker3.ifoadmin;

import junit.framework.Test;
import junit.framework.TestSuite;

import webbroker3.ifoadmin.handler.WEB3AdminIfoFailureOrderInTroubleDownloadHandlerTest;
import webbroker3.ifoadmin.handler.WEB3AdminIfoManualExpireHandlerTest;
import webbroker3.ifoadmin.handler.WEB3AdminIfoManualExpireMainHandlerTest;
import webbroker3.ifoadmin.message.WEB3AdminIfoFailureOrderInTroubleReceivedDateTimeComparatorTest;
import webbroker3.ifoadmin.message.WEB3AdminIfoLapseTargetOrderConditionTest;
import webbroker3.ifoadmin.message.WEB3AdminIfoManualLapseConfirmRequestTest;
import webbroker3.ifoadmin.message.WEB3AdminIfoManualLapseInputRequestTest;
import webbroker3.ifoadmin.message.WEB3AdminIfoManualLapseMainRequestTest;
import webbroker3.ifoadmin.message.WEB3AdminIfoManualLapseRunRequestTest;
import webbroker3.ifoadmin.service.delegate.stdimpls.WEB3AdminIfoFailureOrderInTroubleDownloadServiceImplTest;
import webbroker3.ifoadmin.service.delegate.stdimpls.WEB3AdminIfoManualExpireMainServiceImplTest;
import webbroker3.ifoadmin.service.delegate.stdimpls.WEB3AdminIfoManualExpireServiceImplTest;
import webbroker3.ifoadmin.service.delegate.stdimpls.WEB3AdminIfoManualExpireServiceImplTest_Lmz;
import webbroker3.ifoadmin.service.delegate.stdimpls.WEB3AdminIfoManualExpireServiceImplTest_runManualExpire;
import webbroker3.ifoadmin.service.delegate.stdimpls.WEB3AsynAdminIfoManualExpireServiceImplTest;

public class WEB3AdminIfoAllTests
{

    public static Test suite()
    {
        TestSuite suite = new TestSuite("Test for webbroker3.ifoadmin");
        
        //$JUnit-BEGIN$
        // 管理者・先物OP手動失効メインサービス実装クラス
        suite.addTestSuite(WEB3AdminIfoManualExpireMainServiceImplTest.class);
        suite.addTestSuite(WEB3AdminIfoDataManagerTest.class);
        suite.addTestSuite(WEB3AdminIfoManualExpireServiceInterceptorTest.class);
        suite.addTestSuite(WEB3IfoAdminAppPluginTest.class);
        suite.addTestSuite(WEB3AdminIfoManualExpireHandlerTest.class);
        suite.addTestSuite(WEB3AdminIfoManualExpireMainHandlerTest.class);
        suite.addTestSuite(WEB3AdminIfoLapseTargetOrderConditionTest.class);
        suite.addTestSuite(WEB3AdminIfoManualLapseConfirmRequestTest.class);
        suite.addTestSuite(WEB3AdminIfoManualLapseInputRequestTest.class);
        suite.addTestSuite(WEB3AdminIfoManualLapseMainRequestTest.class);
        suite.addTestSuite(WEB3AdminIfoManualLapseRunRequestTest.class);
        suite.addTestSuite(WEB3AsynAdminIfoManualExpireServiceImplTest.class);
        suite.addTestSuite(WEB3AdminIfoManualExpireServiceImplTest_Lmz.class);
        suite.addTestSuite(WEB3AdminIfoManualExpireServiceImplTest_runManualExpire.class);
        suite.addTestSuite(WEB3AdminIfoManualExpireServiceImplTest.class);
        suite.addTestSuite(WEB3AdminIfoFailureOrderInTroubleDownloadCsvTest.class);
        suite.addTestSuite(WEB3AdminIfoFailureOrderInTroubleDownloadHandlerTest.class);
        suite.addTestSuite(WEB3AdminIfoFailureOrderInTroubleReceivedDateTimeComparatorTest.class);
        suite.addTestSuite(WEB3AdminIfoFailureOrderInTroubleDownloadServiceImplTest.class);
        //$JUnit-END$
        return suite;
    }

}
@
