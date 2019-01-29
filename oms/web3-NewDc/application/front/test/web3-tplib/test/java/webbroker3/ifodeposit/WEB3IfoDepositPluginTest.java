head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.04.07.01.29.02;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	5444d9d078c63f4;
filename	WEB3IfoDepositPluginTest.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
 Copyright        : (株)大和総研 証券ソリューションシステム第二部
 File Name        : WEB3IfoDepositPluginTest.java
 Author Name      : Daiwa Institute of Research
 Revision History : 2008/08/26 楊夫志(中訊) 新規作成 モデル 132
*/
package webbroker3.ifodeposit;

import test.util.TestSpecialClassUtility;

import webbroker3.ifodeposit.message.WEB3IfoDepositCalcResultSaveRequest;
import webbroker3.ifodeposit.message.WEB3IfoDepositTransitionReferenceRequest;
import webbroker3.ifodeposit.service.delegate.WEB3IfoDepositCalcResultCreatePerAccountService;
import webbroker3.ifodeposit.service.delegate.WEB3IfoDepositCalcResultSaveService;
import webbroker3.ifodeposit.service.delegate.WEB3IfoDepositTransitionReferenceService;
import webbroker3.mock.TestBaseForMock;
import webbroker3.util.WEB3LogUtility;

public class WEB3IfoDepositPluginTest extends TestBaseForMock
{
    private static WEB3LogUtility log =
        WEB3LogUtility.getInstance(WEB3IfoDepositPluginTest.class);

    public WEB3IfoDepositPluginTest(String arg0)
    {
        super(arg0);
    }

    protected void setUp() throws Exception
    {
        super.setUp();
    }

    protected void tearDown() throws Exception
    {
        super.tearDown();
    }

    /*
     * Test method for 'webbroker3.ifodeposit.WEB3IfoDepositPlugin.onPlug()'
     */
    public void testOnPlug_C0001()
    {
        String STR_METHOD_NAME = "testOnPlug_C0001()";
        log.entering(STR_METHOD_NAME);

        Class[] serviceArray = {
            WEB3IfoDepositCalcResultCreatePerAccountService.class,
            WEB3IfoDepositCalcResultSaveService.class,
            WEB3IfoDepositCalcService.class,
            WEB3IfoDepositTransitionReferenceService.class
        };

        Class[] requestArrayMessage = {
            WEB3IfoDepositTransitionReferenceRequest.class,
            WEB3IfoDepositCalcResultSaveRequest.class,
        };
        
        TestSpecialClassUtility.testAppPlugIn(requestArrayMessage,serviceArray,TestSpecialClassUtility.intTestBoth);
        
        log.exiting(STR_METHOD_NAME);
    }

}
@
