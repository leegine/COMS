head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.04.07.02.21.41;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	5444d9d078c63f4;
filename	WEB3InformMockAppPlugin.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@package webbroker3.inform;

import com.fitechlabs.xtrade.kernel.boot.Plugin;
import com.fitechlabs.xtrade.kernel.boot.Services;
import com.fitechlabs.xtrade.plugin.tc.gentrade.FinApp;

import webbroker3.inform.service.delegate.WEB3AdminInformSwElecDeliApplyService;
import webbroker3.inform.service.delegate.WEB3InformAccSwElecDeliApplyCommonService;
import webbroker3.inform.service.delegate.WEB3InformAccSwElecDeliApplyService;
import webbroker3.inform.service.delegate.WEB3InformHostReqOrderNumberManageService;
import webbroker3.inform.service.delegate.stdimpls.WEB3AdminInformSwElecDeliApplyServiceImplForMock;
import webbroker3.inform.service.delegate.stdimpls.WEB3InformAccSwElecDeliApplyCommonServiceImplForMock;
import webbroker3.inform.service.delegate.stdimpls.WEB3InformAccSwElecDeliApplyServiceImplForMock;
import webbroker3.inform.service.delegate.stdimpls.WEB3InformHostReqOrderNumberManageServiceImplForMock;
import webbroker3.util.WEB3LogUtility;

public class WEB3InformMockAppPlugin extends Plugin
{

    public WEB3InformMockAppPlugin()
    {
        super();
        // TODO Auto-generated constructor stub
    }

    private static FinApp l_finApp = (FinApp) Services.getService(FinApp.class);

    /**
     * ログユーティリティ。
     */
    private static WEB3LogUtility log = WEB3LogUtility
            .getInstance(WEB3InformMockAppPlugin.class);

    public static void plug() throws Exception
    {
        plug(WEB3InformMockAppPlugin.class);
    }

    public static void onPlug() throws Exception
    {
        // WEB3InformHostReqOrderNumberManageServiceImplForMock
        Services.overrideService(
                WEB3InformHostReqOrderNumberManageService.class,
                new WEB3InformHostReqOrderNumberManageServiceImplForMock());
        // WEB3InformAccSwElecDeliApplyCommonServiceImplForMock
        Services.overrideService(
                WEB3InformAccSwElecDeliApplyCommonService.class,
                new WEB3InformAccSwElecDeliApplyCommonServiceImplForMock());

        // WEB3InformAccSwElecDeliApplyServiceImplForMock
        Services.overrideService(WEB3InformAccSwElecDeliApplyService.class,
                new WEB3InformAccSwElecDeliApplyServiceImplForMock());

        // WEB3AdminInformSwElecDeliApplyServiceImplForMock
        Services.overrideService(WEB3AdminInformSwElecDeliApplyService.class,
                new WEB3AdminInformSwElecDeliApplyServiceImplForMock());
    }
}
@
