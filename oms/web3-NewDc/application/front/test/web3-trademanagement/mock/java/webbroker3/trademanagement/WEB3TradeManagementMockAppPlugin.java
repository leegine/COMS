head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.04.07.02.36.41;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	5444d9d078c63f4;
filename	WEB3TradeManagementMockAppPlugin.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : (WEB3TradeManagementMockAppPlugin.java)
Author Name      : Daiwa Institute of Research
Revision History : 2008/09/26 安陽(中訊) 新規作成
*/
package webbroker3.trademanagement;

import com.fitechlabs.xtrade.kernel.boot.Plugin;
import com.fitechlabs.xtrade.kernel.boot.Services;
import com.fitechlabs.xtrade.plugin.tc.gentrade.FinApp;

import webbroker3.trademanagement.service.delegate.WEB3AdminTMLoginFrequencyListService;
import webbroker3.trademanagement.service.delegate.WEB3AdminTMLoginProcessingListService;
import webbroker3.trademanagement.service.delegate.WEB3AdminTMLoginRejectIPManagementService;
import webbroker3.trademanagement.service.delegate.stdimpls.WEB3AdminTMLoginFrequencyListServiceImplForMock;
import webbroker3.trademanagement.service.delegate.stdimpls.WEB3AdminTMLoginProcessingListServiceImplForMock;
import webbroker3.trademanagement.service.delegate.stdimpls.WEB3AdminTMLoginRejectIPManagementServiceImplForMock;

public class WEB3TradeManagementMockAppPlugin extends Plugin
{

    public WEB3TradeManagementMockAppPlugin()
    {
        super();
        // TODO Auto-generated constructor stub
    }

    public static void plug()
    throws Exception
    {
        plug(WEB3TradeManagementMockAppPlugin.class);
    }
    private static FinApp l_finApp = (FinApp) Services.getService(FinApp.class);
    
    public static void onPlug()
        throws Exception
    {
        // WEB3AdminTMLoginRejectIPManagementServiceImplForMock
        Services.overrideService(WEB3AdminTMLoginRejectIPManagementService.class,
            new WEB3AdminTMLoginRejectIPManagementServiceImplForMock());
        
        //WEB3AdminTMLoginProcessingListServiceImplForMock
        Services.overrideService(WEB3AdminTMLoginProcessingListService.class,
                new WEB3AdminTMLoginProcessingListServiceImplForMock());
        
        //IP別ログイン回数一覧サービスImplForMock
        Services.overrideService(WEB3AdminTMLoginFrequencyListService.class,
            new WEB3AdminTMLoginFrequencyListServiceImplForMock());
    }
}
@
