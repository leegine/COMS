head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.04.07.01.16.52;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	5444d9d078c63f4;
filename	WEB3GentradeMockAppPlugin.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@package webbroker3.gentrade;

import com.fitechlabs.xtrade.kernel.boot.Plugin;
import com.fitechlabs.xtrade.kernel.boot.Services;
import com.fitechlabs.xtrade.plugin.tc.gentrade.FinApp;
import com.fitechlabs.xtrade.plugin.tc.gentrade.FinObjectManager;

import webbroker3.gentrade.service.delegate.WEB3DocumentDeliverHistoryRegistService;
import webbroker3.gentrade.service.delegate.WEB3ExpirationDateListService;
import webbroker3.gentrade.service.delegate.WEB3GentradeBatoClientService;
import webbroker3.gentrade.service.delegate.stdimpls.WEB3DocumentDeliverHistoryRegistServiceImplForMock;
import webbroker3.gentrade.service.delegate.stdimpls.WEB3ExpirationDateListServiceImplForMock;
import webbroker3.gentrade.service.delegate.stdimpls.WEB3GentradeBatoClientServiceImplForMock;

public class WEB3GentradeMockAppPlugin extends Plugin {

    private static FinApp l_finApp = (FinApp) Services.getService(FinApp.class);
    
	public WEB3GentradeMockAppPlugin() {
		super();
		// TODO Auto-generated constructor stub
	}
    public static void plug()
    throws Exception
	{
	    plug(WEB3GentradeMockAppPlugin.class);
	}
	
	public static void onPlug()
	    throws Exception
	{
		
        l_finApp.overrideAccountManager(new WEB3GentradeAccountManagerForMock());
        l_finApp.overrideFinObjectManager(new WEB3GentradeFinObjectManagerForMock());
        l_finApp.overrideCommonOrderValidator(new WEB3GentradeOrderValidatorForMock());
        l_finApp.overrideGlobalBizLogicProvider(new WEB3GentradeBizLogicProviderForMock());
        // 電子鳩接続サービス
        Services.overrideService(WEB3GentradeBatoClientService.class,
                new WEB3GentradeBatoClientServiceImplForMock());
        
        // WEB3HostReqOrderNumberManageServiceImplForMock
        Services.overrideService(WEB3HostReqOrderNumberManageService.class,
                new WEB3HostReqOrderNumberManageServiceImplForMock());
        
        // WEB3ExpirationDateListServiceImplForMock
        Services.overrideService(WEB3ExpirationDateListService.class,
                new WEB3ExpirationDateListServiceImplForMock());
        
        // WEB3DocumentDeliverHistoryRegistServiceImplForMock
        Services.overrideService(WEB3DocumentDeliverHistoryRegistService.class,
                new WEB3DocumentDeliverHistoryRegistServiceImplForMock());

        FinObjectManager l_finObjectManager = l_finApp.getFinObjectManager();
        l_finObjectManager.overrideTradingCalendarModel(
            new WEB3GentradeTradingCalendarModelImplForMock());
	}
}
@
