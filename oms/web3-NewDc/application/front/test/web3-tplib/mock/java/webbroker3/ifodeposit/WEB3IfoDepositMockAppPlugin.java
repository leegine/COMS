head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.04.07.01.58.54;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	5444d9d078c63f4;
filename	WEB3IfoDepositMockAppPlugin.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@package webbroker3.ifodeposit;

import com.fitechlabs.xtrade.kernel.boot.Plugin;
import com.fitechlabs.xtrade.kernel.boot.Services;
import com.fitechlabs.xtrade.plugin.tc.gentrade.FinApp;

import webbroker3.ifodeposit.service.delegate.WEB3IfoDepositCalcResultSaveService;
import webbroker3.ifodeposit.service.delegate.WEB3IfoDepositTransitionReferenceService;
import webbroker3.ifodeposit.service.delegate.stdimpls.WEB3IfoDepositCalcResultSaveServiceImplForMock;
import webbroker3.ifodeposit.service.delegate.stdimpls.WEB3IfoDepositTransitionReferenceServiceImplForMock;
import webbroker3.tradingpower.WEB3TplibMockAppPlugin;
import webbroker3.util.WEB3LogUtility;

public class WEB3IfoDepositMockAppPlugin extends Plugin
{

    public WEB3IfoDepositMockAppPlugin()
    {
        super();
        // TODO Auto-generated constructor stub
    }
    private static FinApp l_finApp = (FinApp) Services.getService(FinApp.class);
    /**
     * ログユーティリティ。
     */
    private static WEB3LogUtility log = WEB3LogUtility.getInstance(WEB3IfoDepositMockAppPlugin.class);

    public static void plug()
    throws Exception
    {
        plug(WEB3IfoDepositMockAppPlugin.class);
    }
    public static void onPlug()
    throws Exception
    {
      final String STR_METHOD_NAME = "plugTplib()";
      log.entering(STR_METHOD_NAME);

      Services.overrideService(WEB3IfoDepositCalcResultSaveService.class, new WEB3IfoDepositCalcResultSaveServiceImplForMock());
      Services.overrideService(WEB3IfoDepositTransitionReferenceService.class, new WEB3IfoDepositTransitionReferenceServiceImplForMock());
      log.exiting(STR_METHOD_NAME);
    }

}
@
