head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.04.07.02.27.47;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	5444d9d078c63f4;
filename	WEB3EqtypeAdminAppPluginTest.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : //TODO(WEB3EqtypeAdminAppPluginTest.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2007/01/20 謝旋 (中訊) 新規作成
*/
package webbroker3.eqtypeadmin;

import test.util.TestSpecialClassUtility;

import webbroker3.eqtypeadmin.message.WEB3AdminEquityManualLapseConfirmRequest;
import webbroker3.eqtypeadmin.message.WEB3AdminEquityManualLapseInputRequest;
import webbroker3.eqtypeadmin.message.WEB3AdminEquityManualLapseMainRequest;
import webbroker3.eqtypeadmin.message.WEB3AdminEquityManualLapseRunRequest;
import webbroker3.eqtypeadmin.message.WEB3AdminEquityManualLapseStatusRequest;
import webbroker3.eqtypeadmin.message.WEB3AdminFrontNoticeHistoryInputRequest;
import webbroker3.eqtypeadmin.message.WEB3AdminFrontNoticeHistoryReferenceRequest;
import webbroker3.eqtypeadmin.message.WEB3AdminOffFloorChangeCompleteRequest;
import webbroker3.eqtypeadmin.message.WEB3AdminOffFloorChangeConfirmRequest;
import webbroker3.eqtypeadmin.message.WEB3AdminOffFloorChangeInputRequest;
import webbroker3.eqtypeadmin.message.WEB3AdminOffFloorDeleteCompleteRequest;
import webbroker3.eqtypeadmin.message.WEB3AdminOffFloorDeleteConfirmRequest;
import webbroker3.eqtypeadmin.message.WEB3AdminOffFloorProductListRequest;
import webbroker3.eqtypeadmin.message.WEB3AdminOffFloorRegistCompleteRequest;
import webbroker3.eqtypeadmin.message.WEB3AdminOffFloorRegistConfirmRequest;
import webbroker3.eqtypeadmin.message.WEB3AdminOffFloorRegistInputRequest;
import webbroker3.eqtypeadmin.message.WEB3AdminPMAccProductTradeStopDeleteCompleteRequest;
import webbroker3.eqtypeadmin.message.WEB3AdminPMAccProductTradeStopDeleteConfirmRequest;
import webbroker3.eqtypeadmin.message.WEB3AdminPMAccProductTradeStopListRequest;
import webbroker3.eqtypeadmin.message.WEB3AdminPMAccProductTradeStopModifyCompleteRequest;
import webbroker3.eqtypeadmin.message.WEB3AdminPMAccProductTradeStopModifyConfirmRequest;
import webbroker3.eqtypeadmin.message.WEB3AdminPMAccProductTradeStopModifyInputRequest;
import webbroker3.eqtypeadmin.message.WEB3AdminPMAccProductTradeStopRegistCompleteRequest;
import webbroker3.eqtypeadmin.message.WEB3AdminPMAccProductTradeStopRegistConfirmRequest;
import webbroker3.eqtypeadmin.message.WEB3AdminPMAccProductTradeStopRegistInputRequest;
import webbroker3.eqtypeadmin.message.WEB3AdminPMProductCondConfCompleteRequest;
import webbroker3.eqtypeadmin.message.WEB3AdminPMProductCondConfConfirmRequest;
import webbroker3.eqtypeadmin.message.WEB3AdminPMProductCondConfInputRequest;
import webbroker3.eqtypeadmin.message.WEB3AdminPMProductCondListInputRequest;
import webbroker3.eqtypeadmin.message.WEB3AdminPMProductCondListRequest;
import webbroker3.eqtypeadmin.message.WEB3AdminPMProductCondRefInputRequest;
import webbroker3.eqtypeadmin.message.WEB3AdminPMProductCondRefReferenceRequest;
import webbroker3.eqtypeadmin.service.delegate.WEB3AdminEquityAccProductTradeStopChangeService;
import webbroker3.eqtypeadmin.service.delegate.WEB3AdminEquityAccProductTradeStopDeleteService;
import webbroker3.eqtypeadmin.service.delegate.WEB3AdminEquityAccProductTradeStopListService;
import webbroker3.eqtypeadmin.service.delegate.WEB3AdminEquityAccProductTradeStopRegistService;
import webbroker3.eqtypeadmin.service.delegate.WEB3AdminEquityManualExpireMainService;
import webbroker3.eqtypeadmin.service.delegate.WEB3AdminEquityManualExpireService;
import webbroker3.eqtypeadmin.service.delegate.WEB3AdminEquityProductCondReferenceService;
import webbroker3.eqtypeadmin.service.delegate.WEB3AdminEquityProductCondScheduleService;
import webbroker3.eqtypeadmin.service.delegate.WEB3AdminEquityProductCondSettingService;
import webbroker3.eqtypeadmin.service.delegate.WEB3AdminFrontNoticeHistoryService;
import webbroker3.eqtypeadmin.service.delegate.WEB3AdminFrontOrderCommonService;
import webbroker3.eqtypeadmin.service.delegate.WEB3AdminOffFloorChangeService;
import webbroker3.eqtypeadmin.service.delegate.WEB3AdminOffFloorDeleteService;
import webbroker3.eqtypeadmin.service.delegate.WEB3AdminOffFloorProductListService;
import webbroker3.eqtypeadmin.service.delegate.WEB3AdminOffFloorRegistService;
import webbroker3.mock.TestBaseForMock;
import webbroker3.util.WEB3LogUtility;

/**
 * XXXXXXクラス//TODO
 *
 * @@author 謝旋(中訊)
 * @@version 1.0
 */
public class WEB3EqtypeAdminAppPluginTest extends TestBaseForMock
{
    /**
     * Log Variable.<BR>
     */
    private static WEB3LogUtility log =
        WEB3LogUtility.getInstance(WEB3EqtypeAdminAppPluginTest.class);
    
    public WEB3EqtypeAdminAppPluginTest(String name)
    {
        super(name);
    }
    
    public void testonPlug0001()
    {
        String STR_METHOD_NAME = "testonPlug0001()";
        log.entering(STR_METHOD_NAME);
        
        Class[] serviceArray = {
            WEB3AdminEquityAccProductTradeStopChangeService.class,
            WEB3AdminEquityAccProductTradeStopRegistService.class,
            WEB3AdminEquityAccProductTradeStopListService.class,
            WEB3AdminEquityProductCondScheduleService.class,
            WEB3AdminEquityAccProductTradeStopDeleteService.class,
            WEB3AdminEquityProductCondReferenceService.class,
            WEB3AdminOffFloorProductListService.class,
            WEB3AdminOffFloorChangeService.class,
            WEB3AdminOffFloorDeleteService.class,
            WEB3AdminOffFloorRegistService.class,
            WEB3AdminEquityProductCondSettingService.class,
            WEB3AdminFrontOrderCommonService.class,
            WEB3AdminFrontNoticeHistoryService.class,
            WEB3AdminEquityManualExpireService.class,
            WEB3AdminEquityManualExpireMainService.class
        };
        
        Class[] requestArrayMessage = {
            WEB3AdminEquityManualLapseConfirmRequest.class,
            WEB3AdminEquityManualLapseInputRequest.class,
            WEB3AdminEquityManualLapseMainRequest.class,
            WEB3AdminEquityManualLapseRunRequest.class,
            WEB3AdminEquityManualLapseStatusRequest.class,

            WEB3AdminFrontNoticeHistoryInputRequest.class,
            WEB3AdminFrontNoticeHistoryReferenceRequest.class,
            WEB3AdminOffFloorChangeCompleteRequest.class,
            WEB3AdminOffFloorChangeConfirmRequest.class,

            WEB3AdminOffFloorChangeInputRequest.class,
            WEB3AdminOffFloorDeleteCompleteRequest.class,
            WEB3AdminOffFloorDeleteConfirmRequest.class,
            WEB3AdminOffFloorProductListRequest.class,
            WEB3AdminOffFloorRegistCompleteRequest.class,
            WEB3AdminOffFloorRegistConfirmRequest.class,

            WEB3AdminOffFloorRegistInputRequest.class,
            WEB3AdminPMAccProductTradeStopDeleteCompleteRequest.class,
            WEB3AdminPMAccProductTradeStopDeleteConfirmRequest.class,
            WEB3AdminPMAccProductTradeStopListRequest.class,
            WEB3AdminPMAccProductTradeStopModifyCompleteRequest.class,
            WEB3AdminPMAccProductTradeStopModifyConfirmRequest.class,

            WEB3AdminPMAccProductTradeStopModifyInputRequest.class,
            WEB3AdminPMAccProductTradeStopRegistCompleteRequest.class,
            WEB3AdminPMAccProductTradeStopRegistConfirmRequest.class,

            WEB3AdminPMAccProductTradeStopRegistInputRequest.class,
            WEB3AdminPMProductCondConfCompleteRequest.class,
            WEB3AdminPMProductCondConfConfirmRequest.class,

            WEB3AdminPMProductCondConfInputRequest.class,
            WEB3AdminPMProductCondListInputRequest.class,
            WEB3AdminPMProductCondListRequest.class,
            WEB3AdminPMProductCondRefInputRequest.class,
            WEB3AdminPMProductCondRefReferenceRequest.class
            };
        
        TestSpecialClassUtility.testAppPlugIn(requestArrayMessage,serviceArray,TestSpecialClassUtility.intTestBoth);
        
        log.exiting(STR_METHOD_NAME);
    }

}
@
