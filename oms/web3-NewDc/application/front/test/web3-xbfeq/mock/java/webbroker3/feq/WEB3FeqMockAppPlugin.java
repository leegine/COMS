head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.04.07.02.19.50;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	5444d9d078c63f4;
filename	WEB3FeqMockAppPlugin.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@package webbroker3.feq;

import webbroker3.feq.service.delegate.WEB3FeqAcceptUpdateService;
import webbroker3.feq.service.delegate.WEB3FeqExecutionNotifyUnitService;
import webbroker3.feq.service.delegate.WEB3FeqOrderAndExecutionUpdateService;
import webbroker3.feq.service.delegate.stdimpls.WEB3FeqAcceptUpdateServiceImpl;
import webbroker3.feq.service.delegate.stdimpls.WEB3FeqAcceptUpdateServiceImplForMock;
import webbroker3.feq.service.delegate.stdimpls.WEB3FeqExecutionNotifyUnitServiceImplForMock;
import webbroker3.feq.service.delegate.stdimpls.WEB3FeqOrderAndExecutionUpdateServiceImplForMock;
import webbroker3.util.WEB3LogUtility;

import com.fitechlabs.xtrade.kernel.boot.Plugin;
import com.fitechlabs.xtrade.kernel.boot.Services;
import com.fitechlabs.xtrade.plugin.tc.gentrade.FinApp;
import com.fitechlabs.xtrade.plugin.tc.gentrade.ProductTypeEnum;
import com.fitechlabs.xtrade.plugin.tc.gentrade.TradingModule;

public class WEB3FeqMockAppPlugin extends Plugin
{

    public WEB3FeqMockAppPlugin()
    {
        super();
        // TODO Auto-generated constructor stub
    }
    private static FinApp l_finApp = (FinApp) Services.getService(FinApp.class);

    /**
     * ログユーティリティ。
     */
    private static WEB3LogUtility log = WEB3LogUtility
            .getInstance(WEB3FeqMockAppPlugin.class);

    public static void plug() throws Exception
    {
        plug(WEB3FeqMockAppPlugin.class);
    }

    public static void onPlug() throws Exception
    {
      final String STR_METHOD_NAME = "plugXbfeq()";
      log.entering(STR_METHOD_NAME);

      l_finApp.uninstallTradingModule("xb-feq-pdbt");
      log.info("Installing TradingModule : web3-feq");
      l_finApp.installTradingModule(new WEB3FeqTradingModule());
      log.info("Installed TradingModule : web3-feq");

      TradingModule l_tradingModule = l_finApp.getTradingModule(ProductTypeEnum.FOREIGN_EQUITY);

      // 市場リクエスト送信サービス
      l_tradingModule.getMarketAdapter().installMarketRequestSenderService(
              new WEB3FeqMarketRequestSenderService());

      // ポジションマネージャ
      l_tradingModule.overridePositionManager(new WEB3FeqPositionManager());
      l_tradingModule.overrideOrderManager(new WEB3FeqOrderManagerForMock());
      l_tradingModule.overrideBizLogicProvider(new WEB3FeqBizLogicProviderForMock());
      l_tradingModule.overrideProductManager(new WEB3FeqProductManagerForMock());
      l_tradingModule.overridePositionManager(new WEB3FeqPositionManagerForMock());
      
      // WEB3FeqExecutionNotifyUnitServiceImplForMock
      Services.overrideService(WEB3FeqExecutionNotifyUnitService.class, new WEB3FeqExecutionNotifyUnitServiceImplForMock());
      Services.overrideService(WEB3FeqOrderAndExecutionUpdateService.class, new WEB3FeqOrderAndExecutionUpdateServiceImplForMock());
      Services.overrideService(WEB3FeqAcceptUpdateService.class, new WEB3FeqAcceptUpdateServiceImplForMock());
      log.exiting(STR_METHOD_NAME);
    }
}
@
