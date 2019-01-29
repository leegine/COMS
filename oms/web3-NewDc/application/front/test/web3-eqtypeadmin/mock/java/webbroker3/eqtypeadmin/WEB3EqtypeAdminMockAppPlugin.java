head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.04.07.02.32.20;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	5444d9d078c63f4;
filename	WEB3EqtypeAdminMockAppPlugin.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : (WEB3EqtypeAdminMockAppPlugin.java)
Author Name      : Daiwa Institute of Research
Revision History : 2008/06/18 安陽(中訊) 新規作成
*/
package webbroker3.eqtypeadmin;

import com.fitechlabs.xtrade.kernel.boot.Plugin;
import com.fitechlabs.xtrade.kernel.boot.Services;

import webbroker3.eqtypeadmin.service.delegate.WEB3AdminEquityAttentionInfoNotifyService;
import webbroker3.eqtypeadmin.service.delegate.WEB3AdminEquityAttentionInfoNotifyUnitService;
import webbroker3.eqtypeadmin.service.delegate.WEB3AdminEquityAttentionInfoReferenceService;
import webbroker3.eqtypeadmin.service.delegate.WEB3AdminEquityForcedSettleOrderDLService;
import webbroker3.eqtypeadmin.service.delegate.WEB3AdminEquityPTSCancelExecService;
import webbroker3.eqtypeadmin.service.delegate.WEB3AdminEquityPTSInputExecService;
import webbroker3.eqtypeadmin.service.delegate.WEB3AdminEquityProductCondSettingService;
import webbroker3.eqtypeadmin.service.delegate.stdimpls.WEB3AdminEquityAttentionInfoNotifyServiceImplForMock;
import webbroker3.eqtypeadmin.service.delegate.stdimpls.WEB3AdminEquityAttentionInfoNotifyUnitServiceImplForMock;
import webbroker3.eqtypeadmin.service.delegate.stdimpls.WEB3AdminEquityAttentionInfoReferenceServiceImplForMock;
import webbroker3.eqtypeadmin.service.delegate.stdimpls.WEB3AdminEquityForcedSettleOrderDLServiceImplForMock;
import webbroker3.eqtypeadmin.service.delegate.stdimpls.WEB3AdminEquityPTSCancelExecServiceImplForMock;
import webbroker3.eqtypeadmin.service.delegate.stdimpls.WEB3AdminEquityPTSInputExecServiceImplForMock;
import webbroker3.eqtypeadmin.service.delegate.stdimpls.WEB3AdminEquityProductCondSettingServiceImplForMock;
import webbroker3.util.WEB3LogUtility;

public class WEB3EqtypeAdminMockAppPlugin extends Plugin
{
    public WEB3EqtypeAdminMockAppPlugin()
    {
        super();
        // TODO Auto-generated constructor stub
    }
    /**
     * ログユーティリティ。
     */
    private static WEB3LogUtility log = WEB3LogUtility.getInstance(WEB3EqtypeAdminMockAppPlugin.class);

    public static void plug()
        throws Exception
    {
        plug(WEB3EqtypeAdminMockAppPlugin.class);
    }
    
    public static void onPlug()
        throws Exception
    {
      final String STR_METHOD_NAME = "plugComplianceAudit()";
      log.entering(STR_METHOD_NAME);
      
      Services.overrideService(WEB3AdminEquityForcedSettleOrderDLService.class, new WEB3AdminEquityForcedSettleOrderDLServiceImplForMock());
      
      Services.overrideService(WEB3AdminEquityProductCondSettingService.class, new WEB3AdminEquityProductCondSettingServiceImplForMock());
      
      Services.overrideService(WEB3AdminEquityPTSCancelExecService.class, new WEB3AdminEquityPTSCancelExecServiceImplForMock());
      
      Services.overrideService(WEB3AdminEquityPTSInputExecService.class, new WEB3AdminEquityPTSInputExecServiceImplForMock());
      
      Services.overrideService(WEB3AdminEquityAttentionInfoNotifyUnitService.class, new WEB3AdminEquityAttentionInfoNotifyUnitServiceImplForMock());
      Services.overrideService(WEB3AdminEquityAttentionInfoNotifyService.class, new WEB3AdminEquityAttentionInfoNotifyServiceImplForMock());
      Services.overrideService(WEB3AdminEquityAttentionInfoReferenceService.class, new WEB3AdminEquityAttentionInfoReferenceServiceImplForMock());
      log.exiting(STR_METHOD_NAME);
    }
}
@
