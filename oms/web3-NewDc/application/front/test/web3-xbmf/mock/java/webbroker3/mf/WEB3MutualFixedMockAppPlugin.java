head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.04.07.02.23.14;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	5444d9d078c63f4;
filename	WEB3MutualFixedMockAppPlugin.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@package webbroker3.mf;

import com.fitechlabs.xtrade.kernel.boot.Plugin;
import com.fitechlabs.xtrade.kernel.boot.Services;
import com.fitechlabs.xtrade.plugin.tc.gentrade.FinApp;
import com.fitechlabs.xtrade.plugin.tc.gentrade.GtlUtils;
import com.fitechlabs.xtrade.plugin.tc.gentrade.ProductTypeEnum;
import com.fitechlabs.xtrade.plugin.tc.gentrade.TradingModule;

import webbroker3.mf.service.delegate.WEB3AdminMutualConditionsService;
import webbroker3.mf.service.delegate.WEB3AdminMutualTPACancelService;
import webbroker3.mf.service.delegate.WEB3MutualFixedBuyConditionService;
import webbroker3.mf.service.delegate.WEB3MutualOrderAcceptUnitService;
import webbroker3.mf.service.delegate.WEB3MutualTradeOrderNotifyUnitService;
import webbroker3.mf.service.delegate.stdimpls.WEB3AdminMutualConditionsServiceImplForMock;
import webbroker3.mf.service.delegate.stdimpls.WEB3AdminMutualTPACancelServiceImplForMock;
import webbroker3.mf.service.delegate.stdimpls.WEB3MutualFixedBuyConditionServiceImplForMock;
import webbroker3.mf.service.delegate.stdimpls.WEB3MutualOrderAcceptUnitServiceImplForMock;
import webbroker3.mf.service.delegate.stdimpls.WEB3MutualTradeOrderNotifyUnitServiceImplForMock;

public class WEB3MutualFixedMockAppPlugin extends Plugin {

	public WEB3MutualFixedMockAppPlugin() {
		super();
		// TODO Auto-generated constructor stub
	}
    public static void plug()
    throws Exception
	{
	    plug(WEB3MutualFixedMockAppPlugin.class);
	}
    private static FinApp l_finApp = (FinApp) Services.getService(FinApp.class);
    
	public static void onPlug()
	    throws Exception
	{
		TradingModule l_tradingModule = GtlUtils.getTradingModule(ProductTypeEnum.MUTUAL_FUND);

        // 拡張トランザクション・マネージャーは
        // オーバーライドメソッドが無いため拡張取引モジュールクラスを作成し
        // 拡張取引モジュールクラス内で設定
        l_finApp.uninstallTradingModule("xb-mf-pdbt");
        l_finApp.installTradingModule(new WEB3MutualFundTradingModuleForMock());

        l_tradingModule = l_finApp.getTradingModule("xb-mf-pdbt");
        // 拡張プロダクト・マネージャー
        l_tradingModule.overrideProductManager(new WEB3MutualFundProductManagerForMock());

        // 計算サービスクラス
        l_tradingModule.overrideBizLogicProvider(new WEB3MutualFundBizLogicProviderForMock());
        
        // 拡張注文マネージャ
        l_tradingModule.overrideOrderManager(new WEB3MutualFundOrderManagerForMock());

        // ポジションマネージャ
        l_tradingModule.overridePositionManager(new WEB3MutualFundPositionManagerForMock());

        // 投信注文受付UnitServiceImplForMock
        Services.overrideService(WEB3MutualOrderAcceptUnitService.class,
                new WEB3MutualOrderAcceptUnitServiceImplForMock());

        // 投資信託発注審査個別チェックForMock
        WEB3MutualFundOrderManagerReusableValidationsCheckForMock.register();
        
        // WEB3MutualFixedBuyCommonServiceImplForMock
        Services.overrideService(WEB3MutualFixedBuyCommonService.class,
                new WEB3MutualFixedBuyCommonServiceImplForMock());
        
        //投信定時定額買付銘柄条件登録サービスForMock
        Services.overrideService(WEB3MutualFixedBuyConditionService.class,
                new WEB3MutualFixedBuyConditionServiceImplForMock());

        //管理者投信銘柄条件登録サービスForMock
        Services.overrideService(WEB3AdminMutualConditionsService.class,
                new WEB3AdminMutualConditionsServiceImplForMock());

        //投信管理者余力調整取消サービスForMock
        Services.overrideService(WEB3AdminMutualTPACancelService.class,
                new WEB3AdminMutualTPACancelServiceImplForMock());
        
        Services.overrideService(WEB3MutualTradeOrderNotifyUnitService.class,
                new WEB3MutualTradeOrderNotifyUnitServiceImplForMock());
        
	}
}
@
