head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.04.07.01.56.55;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	5444d9d078c63f4;
filename	WEB3AdminDirSecAppPluginTest.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@package webbroker3.dirsec;

import test.util.TestSpecialClassUtility;

import webbroker3.dirsec.message.WEB3AdminFrontChangeProcessSelectRequest;
import webbroker3.dirsec.message.WEB3AdminFrontRouteChangeCompleteRequest;
import webbroker3.dirsec.message.WEB3AdminFrontRouteChangeConfirmRequest;
import webbroker3.dirsec.message.WEB3AdminFrontRouteChangeSelectRequest;
import webbroker3.dirsec.message.WEB3AdminSwitchOrderRouteCompleteRequest;
import webbroker3.dirsec.message.WEB3AdminSwitchOrderRouteConfirmRequest;
import webbroker3.dirsec.message.WEB3AdminSwitchOrderRouteSelectRequest;
import webbroker3.dirsec.service.delegate.WEB3AdminFrontOrderRouteChangeService;
import webbroker3.dirsec.service.delegate.WEB3AdminSwitchOrderRouteService;
import webbroker3.dirsec.service.delegate.WEB3FrontOrderRouteChangeFormSelectService;
import webbroker3.mock.TestBaseForMock;
import webbroker3.util.WEB3LogUtility;

public class WEB3AdminDirSecAppPluginTest extends TestBaseForMock
{
    /**
     * Log Variable.<BR>
     */
    private static WEB3LogUtility log =
        WEB3LogUtility.getInstance(WEB3AdminDirSecAppPluginTest.class);

    public WEB3AdminDirSecAppPluginTest(String arg0)
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

    public void testonPlug0001()
    {
        String STR_METHOD_NAME = "testonPlug0001()";
        log.entering(TEST_START + STR_METHOD_NAME);
        
        Class[] serviceArray = {
            WEB3AdminSwitchOrderRouteService.class,
            WEB3AdminFrontOrderRouteChangeService.class,
            WEB3FrontOrderRouteChangeFormSelectService.class
        };
        
        Class[] classArray = {WEB3AdminSwitchOrderRouteConfirmRequest.class,
            WEB3AdminSwitchOrderRouteCompleteRequest.class,
            WEB3AdminSwitchOrderRouteSelectRequest.class,
            WEB3AdminFrontRouteChangeSelectRequest.class,
            WEB3AdminFrontRouteChangeConfirmRequest.class,
            WEB3AdminFrontRouteChangeCompleteRequest.class,
            WEB3AdminFrontChangeProcessSelectRequest.class};
        
        TestSpecialClassUtility.testAppPlugIn(classArray , serviceArray , TestSpecialClassUtility.intTestBoth);

        log.exiting(TEST_END + STR_METHOD_NAME);
    }
}
@
