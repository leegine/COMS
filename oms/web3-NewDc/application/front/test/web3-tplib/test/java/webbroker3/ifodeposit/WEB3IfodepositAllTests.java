head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.04.07.01.29.12;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	5444d9d078c63f4;
filename	WEB3IfodepositAllTests.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (äî)ëÂòaëçå§ èÿåîÉ\ÉäÉÖÅ[ÉVÉáÉìÉVÉXÉeÉÄëÊìÒïî
File Name        : WEB3IfodepositAllTests.java
Author Name      : Daiwa Institute of Research
Revesion History : 2008/08/21 è—éuàÃ (íÜêu) êVãKçÏê¨
*/

package webbroker3.ifodeposit;

import junit.framework.Test;
import junit.framework.TestSuite;

import webbroker3.ifodeposit.handler.WEB3IfoDepositCalcResultSaveHandlerTest;
import webbroker3.ifodeposit.handler.WEB3IfoDepositTransitionReferenceHandlerTest;
import webbroker3.ifodeposit.service.delegate.WEB3IfoDepositClientRequestServiceTest;
import webbroker3.ifodeposit.service.delegate.stdimpls.WEB3IfoDepositCalcResultCreatePerAccountServiceImplTest;
import webbroker3.ifodeposit.service.delegate.stdimpls.WEB3IfoDepositCalcResultSaveServiceImplTest;
import webbroker3.ifodeposit.service.delegate.stdimpls.WEB3IfoDepositDBManagerTest;
import webbroker3.ifodeposit.service.delegate.stdimpls.WEB3IfoDepositTransitionReferenceServiceImplTest;

/**
 * 
 * @@author è—éuàÃ
 * @@version 1.0
 */
public class WEB3IfodepositAllTests
{
    public static Test suite()
    {
        TestSuite suite = new TestSuite("Test for webbroker3.ifodeposit");
        //$JUnit-BEGIN$
        suite.addTestSuite(WEB3IfoContractTest.class);
        suite.addTestSuite(WEB3IfoCustomerTransferTest.class);
        suite.addTestSuite(WEB3IfoDepositTransitionReferenceServiceInterceptorTest.class);
        suite.addTestSuite(WEB3IfoFinTransactionTest.class);
        suite.addTestSuite(WEB3IfoNewOrderSpecTest.class);
        suite.addTestSuite(WEB3IfoProductTest.class);
        suite.addTestSuite(WEB3IfoSummaryContractPerProductContractPriceTest.class);
        suite.addTestSuite(WEB3IfoSummaryContractTest.class);
        suite.addTestSuite(WEB3IfoTodayOpenOrderTest.class);
        suite.addTestSuite(WEB3IfoDepositClientRequestServiceTest.class);
        suite.addTestSuite(WEB3IfoDepositDBManagerTest.class);
        suite.addTestSuite(WEB3IfoDepositCalcConditionTest.class);
        suite.addTestSuite(WEB3IfoDepositCalcResultSaveServiceImplTest.class);
        suite.addTestSuite(WEB3IfoDepositCalcServiceImplTest.class);
        suite.addTestSuite(WEB3IfoDepositCalcServiceInterceptorTest.class);
        suite.addTestSuite(WEB3IfoDepositCalcResultCreatePerAccountServiceInterceptorTest.class);
        suite.addTestSuite(WEB3IfoDepositPersistentDataManagerTest.class);
        suite.addTestSuite(WEB3IfoDepositPluginTest.class);
        suite.addTestSuite(WEB3IfoDepositCalcResultSaveHandlerTest.class);
        suite.addTestSuite(WEB3IfoDepositTransitionReferenceHandlerTest.class);
        suite.addTestSuite(WEB3IfoDepositCalcResultCreatePerAccountServiceImplTest.class);
        suite.addTestSuite(WEB3IfoDepositTransitionReferenceServiceImplTest.class);
        suite.addTestSuite(WEB3IfoSummaryContractPerIndexTest.class);
        suite.addTestSuite(WEB3IfoDepositCalcTest.class);
        suite.addTestSuite(WEB3IfoSummaryContractPerProductTest.class);
        //$JUnit-END$
        return suite;
    }
}

@
