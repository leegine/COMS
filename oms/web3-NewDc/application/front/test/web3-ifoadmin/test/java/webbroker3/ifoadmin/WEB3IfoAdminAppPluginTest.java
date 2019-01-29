head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.04.07.02.29.28;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	5444d9d078c63f4;
filename	WEB3IfoAdminAppPluginTest.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : Eqtypeadmin AppPlugin(WEB3EqtypeadminAppPlugin.java)
Author Name      : Daiwa Institute of Research
Revision History : 2007/01/17  謝旋 (中訊)新規作成
*/
package webbroker3.ifoadmin;

import test.util.TestSpecialClassUtility;

import webbroker3.ifoadmin.message.WEB3AdminIfoManualLapseConfirmRequest;
import webbroker3.ifoadmin.message.WEB3AdminIfoManualLapseInputRequest;
import webbroker3.ifoadmin.message.WEB3AdminIfoManualLapseMainRequest;
import webbroker3.ifoadmin.message.WEB3AdminIfoManualLapseRunRequest;
import webbroker3.ifoadmin.message.WEB3AdminIfoManualLapseStatusRequest;
import webbroker3.ifoadmin.service.delegate.WEB3AdminIfoManualExpireMainService;
import webbroker3.ifoadmin.service.delegate.WEB3AdminIfoManualExpireService;
import webbroker3.mock.TestBaseForMock;

/**
 * Webbroker3-Ifoadmin<BR>
 * WEB3IfoAdminAppPlugin
 * @@author 謝旋
 * @@version 1.0
 */
public class WEB3IfoAdminAppPluginTest extends TestBaseForMock
{

    public WEB3IfoAdminAppPluginTest(String arg0)
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
    
    public void testAppPlugIn()
    {
        Class[] l_services = {
            WEB3AdminIfoManualExpireMainService.class,
            WEB3AdminIfoManualExpireService.class
        };
        
        Class[] l_request = {
            WEB3AdminIfoManualLapseConfirmRequest.class,
            WEB3AdminIfoManualLapseInputRequest.class,
            WEB3AdminIfoManualLapseMainRequest.class,
            WEB3AdminIfoManualLapseRunRequest.class,
            WEB3AdminIfoManualLapseStatusRequest.class
        };
        
        TestSpecialClassUtility.testAppPlugIn(l_request , l_services , TestSpecialClassUtility.intTestBoth);
    }

}
@
