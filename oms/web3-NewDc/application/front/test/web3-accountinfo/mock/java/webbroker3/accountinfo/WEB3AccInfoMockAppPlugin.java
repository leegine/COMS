head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.04.06.08.38.01;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	5404d9c264d0465;
filename	WEB3AccInfoMockAppPlugin.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@package webbroker3.accountinfo;

import webbroker3.accountinfo.service.delegate.WEB3AdminAccInfoCampaignAccOpenChangeService;
import webbroker3.accountinfo.service.delegate.WEB3AdminAccInfoCampaignIndiviChangeService;
import webbroker3.accountinfo.service.delegate.WEB3AdminAccInfoCampaignRegistAccListService;
import webbroker3.accountinfo.service.delegate.stdimpls.WEB3AdminAccInfoCampaignAccOpenChangeServiceImplForMock;
import webbroker3.accountinfo.service.delegate.stdimpls.WEB3AdminAccInfoCampaignIndiviChangeServiceImplForMock;
import webbroker3.accountinfo.service.delegate.stdimpls.WEB3AdminAccInfoCampaignRegistAccListServiceImplForMock;
import webbroker3.util.WEB3LogUtility;

import com.fitechlabs.xtrade.kernel.boot.Plugin;
import com.fitechlabs.xtrade.kernel.boot.Services;
import com.fitechlabs.xtrade.plugin.tc.gentrade.FinApp;

public class WEB3AccInfoMockAppPlugin extends Plugin
{

    public WEB3AccInfoMockAppPlugin()
    {
        super();
        // TODO Auto-generated constructor stub
    }
    private static FinApp l_finApp = (FinApp) Services.getService(FinApp.class);
    /**
     * ログユーティリティ。
     */
    private static WEB3LogUtility log = WEB3LogUtility.getInstance(WEB3AccInfoMockAppPlugin.class);

    public static void plug()
    throws Exception
    {
        plug(WEB3AccInfoMockAppPlugin.class);
    }
    
    public static void onPlug()
        throws Exception
    {
      final String STR_METHOD_NAME = "plugAccountinfo()";
      log.entering(STR_METHOD_NAME);

      // 手数料割引キャンペーン共通クラスForMock
      new WEB3AdminAccInfoCampaignCommonForMock().register();

      // 個別顧客指定変更サービスImplMock
      Services.overrideService(WEB3AdminAccInfoCampaignIndiviChangeService.class,
              new WEB3AdminAccInfoCampaignIndiviChangeServiceImplForMock());
      Services.overrideService(WEB3AdminAccInfoCampaignRegistAccListService.class,
              new WEB3AdminAccInfoCampaignRegistAccListServiceImplForMock());

      Services.overrideService(WEB3AdminAccInfoCampaignAccOpenChangeService.class,
              new WEB3AdminAccInfoCampaignAccOpenChangeServiceImplForMock());

      log.exiting(STR_METHOD_NAME);
    }
}
@
