head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.04.07.02.01.06;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	5444d9d078c63f4;
filename	WEB3EquityMockAppPlugin.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@package webbroker3.equity;

import com.fitechlabs.xtrade.kernel.boot.Plugin;
import com.fitechlabs.xtrade.kernel.boot.Services;
import com.fitechlabs.xtrade.plugin.tc.gentrade.FinApp;
import com.fitechlabs.xtrade.plugin.tc.gentrade.GtlUtils;
import com.fitechlabs.xtrade.plugin.tc.gentrade.ProductTypeEnum;
import com.fitechlabs.xtrade.plugin.tc.gentrade.TradingModule;

import webbroker3.equity.marketadaptor.WEB3EquityMarketRequestSenderServiceImpl;
import webbroker3.equity.service.delegate.WEB3EquityAssetInquiryService;
import webbroker3.equity.service.delegate.WEB3EquityBalanceReferenceService;
import webbroker3.equity.service.delegate.WEB3EquityCancelOrderService;
import webbroker3.equity.service.delegate.WEB3EquityChangeOrderInputService;
import webbroker3.equity.service.delegate.WEB3EquityChangeOrderService;
import webbroker3.equity.service.delegate.WEB3EquityExecutedMailSenderService;
import webbroker3.equity.service.delegate.WEB3EquityFrontOrderService;
import webbroker3.equity.service.delegate.WEB3EquityMarginExecuteReferenceService;
import webbroker3.equity.service.delegate.WEB3EquityOrderExecNotifyUnitService;
import webbroker3.equity.service.delegate.WEB3EquityPTSCancelOrderService;
import webbroker3.equity.service.delegate.WEB3EquityPTSChangeOrderInputService;
import webbroker3.equity.service.delegate.WEB3EquityPTSChangeOrderService;
import webbroker3.equity.service.delegate.WEB3EquityReceiveCloseOrderUnitService;
import webbroker3.equity.service.delegate.WEB3MarginExecuteReferenceService;
import webbroker3.equity.service.delegate.WEB3MarginOrderExecNotifyUnitService;
import webbroker3.equity.service.delegate.WEB3MarginSwapMarginAcceptUnitService;
import webbroker3.equity.service.delegate.stdimpls.WEB3EquityAssetInquiryServiceImplForMock;
import webbroker3.equity.service.delegate.stdimpls.WEB3EquityBalanceReferenceServiceImplForMock;
import webbroker3.equity.service.delegate.stdimpls.WEB3EquityCancelOrderServiceImplForMock;
import webbroker3.equity.service.delegate.stdimpls.WEB3EquityChangeOrderInputServiceImplForMock;
import webbroker3.equity.service.delegate.stdimpls.WEB3EquityChangeOrderServiceImplForMock;
import webbroker3.equity.service.delegate.stdimpls.WEB3EquityExecutedMailSenderServiceImplForMock;
import webbroker3.equity.service.delegate.stdimpls.WEB3EquityFrontOrderServiceImplForMock;
import webbroker3.equity.service.delegate.stdimpls.WEB3EquityMarginExecuteReferenceServiceImplForMock;
import webbroker3.equity.service.delegate.stdimpls.WEB3EquityOrderExecNotifyUnitServiceImplForMock;
import webbroker3.equity.service.delegate.stdimpls.WEB3EquityPTSCancelOrderServiceImplForMock;
import webbroker3.equity.service.delegate.stdimpls.WEB3EquityPTSChangeOrderInputServiceImplForMock;
import webbroker3.equity.service.delegate.stdimpls.WEB3EquityPTSChangeOrderServiceImplForMock;
import webbroker3.equity.service.delegate.stdimpls.WEB3EquityReceiveCloseOrderUnitServiceImplForMock;
import webbroker3.equity.service.delegate.stdimpls.WEB3MarginExecuteReferenceServiceImplForMock;
import webbroker3.equity.service.delegate.stdimpls.WEB3MarginOrderExecNotifyUnitServiceImplForMock;
import webbroker3.equity.service.delegate.stdimpls.WEB3MarginSwapMarginAcceptUnitServiceImplForMock;
import webbroker3.quoteadaptor.prototype.WEB3ProtoQuoteDataSupplierServiceForMock;
import webbroker3.util.WEB3LogUtility;

public class WEB3EquityMockAppPlugin extends Plugin
{

    public WEB3EquityMockAppPlugin()
    {
        super();
        // TODO Auto-generated constructor stub
    }
    private static FinApp l_finApp = (FinApp) Services.getService(FinApp.class);
    /**
     * ログユーティリティ。
     */
    private static WEB3LogUtility log = WEB3LogUtility.getInstance(WEB3EquityMockAppPlugin.class);

    public static void plug()
    throws Exception
    {
        plug(WEB3EquityMockAppPlugin.class);
    }
    
    public static void onPlug()
        throws Exception
    {
      final String STR_METHOD_NAME = "plugEquity()";
      log.entering(STR_METHOD_NAME);
            
       Services.overrideService(WEB3EquityMarginExecuteReferenceService.class,
       new WEB3EquityMarginExecuteReferenceServiceImplForMock());

      l_finApp.uninstallTradingModule("eqtype");
      l_finApp.installTradingModule(new WEB3EquityTradingModuleForMock());

      TradingModule l_tradingModuleMock = GtlUtils.getTradingModule(ProductTypeEnum.EQUITY);
      
      l_tradingModuleMock = l_finApp.getTradingModule("eqtype");
      l_tradingModuleMock.overrideOrderManager(new WEB3EquityPTSOrderManagerForMock());
      l_tradingModuleMock.getMarketAdapter().installMarketRequestSenderService(
              new WEB3EquityMarketRequestSenderServiceImpl());
      // 拡張プロダクト・マネージャー
      l_tradingModuleMock.overrideProductManager(new WEB3EquityProductManagerForMock());

      // 計算サービスクラス
      l_tradingModuleMock.overrideBizLogicProvider(new WEB3EquityBizLogicProviderForMock());

      // ポジションマネージャ
      l_tradingModuleMock.overridePositionManager(new WEB3EquityPositionManagerForMock());

      // 時価情報提供サービス
      l_tradingModuleMock.installQuoteDataSupplierService(new WEB3ProtoQuoteDataSupplierServiceForMock());
      
      // 信用取引注文約定照会サービスImplForMock
      Services.overrideService(WEB3MarginExecuteReferenceService.class,
              new WEB3MarginExecuteReferenceServiceImplForMock());
      //l_tradingModuleMock.installQuoteDataSupplierService(l_tradingModule.getQuoteDataSupplierService());

      //WEB3EquityReceiveCloseOrderUnitServiceImplForMock
      Services.overrideService(WEB3EquityReceiveCloseOrderUnitService.class,
              new WEB3EquityReceiveCloseOrderUnitServiceImplForMock());

      //WEB3EquityFrontOrderServiceImplForMock
      Services.overrideService(WEB3EquityFrontOrderService.class,
              new WEB3EquityFrontOrderServiceImplForMock());
      
      // WEB3EquityChangeOrderInputServiceImplForMock
      Services.overrideService(WEB3EquityChangeOrderInputService.class,
          new WEB3EquityChangeOrderInputServiceImplForMock());
      
      // WEB3EquityPTSChangeOrderInputServiceImplForMock
      Services.overrideService(WEB3EquityPTSChangeOrderInputService.class,
          new WEB3EquityPTSChangeOrderInputServiceImplForMock());
      
      // WEB3EquityPTSCancelOrderServiceImplForMock
      Services.overrideService(WEB3EquityPTSCancelOrderService.class,
          new WEB3EquityPTSCancelOrderServiceImplForMock());
      
      // WEB3EquityCancelOrderServiceImplForMock
      Services.overrideService(WEB3EquityCancelOrderService.class,
          new WEB3EquityCancelOrderServiceImplForMock());
      
      // WEB3EquityPTSChangeOrderServiceImplForMock
      Services.overrideService(WEB3EquityPTSChangeOrderService.class,
          new WEB3EquityPTSChangeOrderServiceImplForMock());
      
      // WEB3EquityChangeOrderServiceImplForMock
      Services.overrideService(WEB3EquityChangeOrderService.class,
          new WEB3EquityChangeOrderServiceImplForMock());
      
      //WEB3EquityPTSOrderManagerReusableValidationsForMock
      new WEB3EquityPTSOrderManagerReusableValidationsForMock().register();
      
      //WEB3EquityAssetInquiryServiceImplForMock
      Services.overrideService(WEB3EquityAssetInquiryService.class,
              new WEB3EquityAssetInquiryServiceImplForMock());
      
      //WEB3EquityBalanceReferenceServiceImplForMock
      Services.overrideService(WEB3EquityBalanceReferenceService.class,
              new WEB3EquityBalanceReferenceServiceImplForMock());
      
      //WEB3EquityOrderExecNotifyUnitServiceImplForMock
      Services.overrideService(WEB3EquityOrderExecNotifyUnitService.class,
              new WEB3EquityOrderExecNotifyUnitServiceImplForMock());

      //WEB3MarginOrderExecNotifyUnitServiceImplForMock
      Services.overrideService(WEB3MarginOrderExecNotifyUnitService.class,
              new WEB3MarginOrderExecNotifyUnitServiceImplForMock());

      //WEB3EquityExecutedMailSenderServiceImplForMock
      Services.overrideService(WEB3EquityExecutedMailSenderService.class,
              new WEB3EquityExecutedMailSenderServiceImplForMock());

      //WEB3MarginSwapMarginAcceptUnitServiceImplForMock
      Services.overrideService(WEB3MarginSwapMarginAcceptUnitService.class,
              new WEB3MarginSwapMarginAcceptUnitServiceImplForMock());

      log.exiting(STR_METHOD_NAME);
    }
}
@
