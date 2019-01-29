head	1.2;
access;
symbols;
locks; strict;
comment	@// @;


1.2
date	2011.04.08.07.44.30;	author zhang-tengyu;	state Exp;
branches;
next	1.1;
deltatype	text;
kopt	kv;
permissions	666;
commitid	6184d9ebcdd780c;
filename	WEB3AioMockAppPlugin.java;

1.1
date	2011.04.07.01.41.06;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	5444d9d078c63f4;
filename	WEB3AioMockAppPlugin.java;


desc
@@


1.2
log
@*** empty log message ***
@
text
@package webbroker3.aio;

import com.fitechlabs.xtrade.kernel.boot.Plugin;
import com.fitechlabs.xtrade.kernel.boot.Services;
import com.fitechlabs.xtrade.plugin.tc.gentrade.FinApp;
import com.fitechlabs.xtrade.plugin.tc.gentrade.GtlUtils;
import com.fitechlabs.xtrade.plugin.tc.gentrade.ProductTypeEnum;
import com.fitechlabs.xtrade.plugin.tc.gentrade.TradingModule;

import webbroker3.aio.marketadaptor.WEB3AioMarketRequestSenderServiceImplForMock;
import webbroker3.aio.service.delegate.WEB3AccTransChangeAcceptUnitService;
import webbroker3.aio.service.delegate.WEB3AccTransChangeCompleteUnitService;
import webbroker3.aio.service.delegate.WEB3AdminAioListService;
import webbroker3.aio.service.delegate.WEB3AdminAioSLCashOutStopReleaseService;
import webbroker3.aio.service.delegate.WEB3AdminAioSLProductCancelService;
import webbroker3.aio.service.delegate.WEB3AdminAioSLProductChangeService;
import webbroker3.aio.service.delegate.WEB3AdminAioSLProductRegistService;
import webbroker3.aio.service.delegate.WEB3AioCashoutTradingPowerUnitService;
import webbroker3.aio.service.delegate.WEB3MarginTransferService;
import webbroker3.aio.service.delegate.stdimpls.WEB3AccTransChangeAcceptUnitServiceImplForMock;
import webbroker3.aio.service.delegate.stdimpls.WEB3AccTransChangeCompleteUnitServiceImplForMock;
import webbroker3.aio.service.delegate.stdimpls.WEB3AdminAioListServiceImplForMock;
import webbroker3.aio.service.delegate.stdimpls.WEB3AdminAioSLCashOutStopReleaseServiceImplForMock;
import webbroker3.aio.service.delegate.stdimpls.WEB3AdminAioSLProductCancelServiceImplForMock;
import webbroker3.aio.service.delegate.stdimpls.WEB3AdminAioSLProductChangeServiceImplForMock;
import webbroker3.aio.service.delegate.stdimpls.WEB3AdminAioSLProductRegistServiceImplForMock;
import webbroker3.aio.service.delegate.stdimpls.WEB3AioCashoutTradingPowerUnitServiceImplForMock;
import webbroker3.aio.service.delegate.stdimpls.WEB3MarginTransferServiceImplForMock;
import webbroker3.tradingpower.WEB3TPTradingPowerService;
import webbroker3.tradingpower.WEB3TPTradingPowerServiceImplForMock;
import webbroker3.util.WEB3LogUtility;

public class WEB3AioMockAppPlugin extends Plugin
{
    private static FinApp l_finApp = (FinApp) Services.getService(FinApp.class);

    public WEB3AioMockAppPlugin()
    {
        super();
        // TODO Auto-generated constructor stub
    }

    /**
     * ログユーティリティ。
     */
    private static WEB3LogUtility log = WEB3LogUtility
            .getInstance(WEB3AioMockAppPlugin.class);

    public static void plug() throws Exception
    {
        plug(WEB3AioMockAppPlugin.class);
    }

    public static void onPlug() throws Exception
    {
        final String STR_METHOD_NAME = "plugXbaio()";
        log.entering(STR_METHOD_NAME);

        TradingModule l_tradingModule = GtlUtils
                .getTradingModule(ProductTypeEnum.AIO);
        TradingModule l_tradingModuleMock = GtlUtils
                .getTradingModule(ProductTypeEnum.AIO);

        l_finApp.uninstallTradingModule("xb-aio-pdbt");
        l_finApp.installTradingModule(new WEB3AioTradingModuleForMock());

        l_tradingModuleMock = l_finApp.getTradingModule("xb-aio-pdbt");

        // 計算サービスクラス
        l_tradingModuleMock
                .overrideBizLogicProvider(new WEB3AioBizLogicProviderForMock());
        // 拡張注文マネージャ
        l_tradingModuleMock
                .overrideOrderManager(new WEB3AioOrderManagerForMock());

        l_tradingModuleMock.getMarketAdapter()
                .installMarketRequestSenderService(
                        new WEB3AioMarketRequestSenderServiceImplForMock());

        // AIOプロダクトマネージャ
        l_tradingModuleMock.overrideProductManager(l_tradingModule
                .getProductManager());

        // ポジションマネージャ
        l_tradingModuleMock.overridePositionManager(l_tradingModule
                .getPositionManager());

        // 管理者入出金一覧サービスImplForMock
        Services.overrideService(WEB3AdminAioListService.class,
                new WEB3AdminAioListServiceImplForMock());

        // 出金余力チェックUnitServiceImplForMock
        Services.overrideService(WEB3AioCashoutTradingPowerUnitService.class,
                new WEB3AioCashoutTradingPowerUnitServiceImplForMock());
        // WEB3MarginTransferServiceImplForMock
        Services.overrideService(WEB3MarginTransferService.class,
                new WEB3MarginTransferServiceImplForMock());
        // WEB3FXTelegramProcessServiceImplForMock
        Services.overrideService(WEB3FXTelegramProcessService.class,
                new WEB3FXTelegramProcessServiceImplForMock());
        // WEB3FXDataControlServiceImplForMock
        Services.overrideService(WEB3FXDataControlService.class,
                new WEB3FXDataControlServiceImplForMock());
 
        // WEB3AdminAioSLCashOutStopReleaseServiceImplForMock
        Services.overrideService(WEB3AdminAioSLCashOutStopReleaseService.class,
                new WEB3AdminAioSLCashOutStopReleaseServiceImplForMock());

        // WEB3AdminAioSLProductCancelServiceImplForMock
        Services.overrideService(WEB3AdminAioSLProductCancelService.class,
                new WEB3AdminAioSLProductCancelServiceImplForMock());

        // WEB3AdminAioSLProductRegistServiceImplForMock
        Services.overrideService(WEB3AdminAioSLProductRegistService.class,
                new WEB3AdminAioSLProductRegistServiceImplForMock());

        // WEB3AdminAioSLProductChangeServiceImplForMock
        Services.overrideService(WEB3AdminAioSLProductChangeService.class,
                new WEB3AdminAioSLProductChangeServiceImplForMock());

        // WEB3AdminAioSLProductRegistControlServiceImplForMock
        Services.overrideService(
                WEB3AdminAioSLProductRegistControlService.class,
                new WEB3AdminAioSLProductRegistControlServiceImplForMock());

        //WEB3AccTransChangeAcceptUnitServiceImplForMock
        Services.overrideService(
            WEB3AccTransChangeAcceptUnitService.class,
            new WEB3AccTransChangeAcceptUnitServiceImplForMock());

        //WEB3AccTransChangeCompleteUnitServiceImplForMock
        Services.overrideService(
            WEB3AccTransChangeCompleteUnitService.class,
            new WEB3AccTransChangeCompleteUnitServiceImplForMock());

        //WEB3AccTransChangeAcceptUnitServiceImplForMock
        Services.overrideService(
                WEB3AccTransChangeAcceptUnitService.class,
                new WEB3AccTransChangeAcceptUnitServiceImplForMock());
        
        Services.overrideService(
                WEB3FXTransferAbleAmtDisplayService.class,
                new WEB3FXTransferAbleAmtDisplayServiceImplForMock());
        
        //WEB3FXTransConnection
        Services.overrideService(
                WEB3FXTransConnection.class,
                new WEB3FXTransConnectionImplForMock());
        
        Services.overrideService(WEB3TPTradingPowerService.class, new WEB3TPTradingPowerServiceImplForMock());
       
        //WEB3FXTransConnection
        Services.overrideService(
        		WEB3FXConnCommonService.class,
                new WEB3FXConnCommonServiceImplForMock());
        log.exiting(STR_METHOD_NAME);
    }
}
@


1.1
log
@*** empty log message ***
@
text
@d151 5
@

