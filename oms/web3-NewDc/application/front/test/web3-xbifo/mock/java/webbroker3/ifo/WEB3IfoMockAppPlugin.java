head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.04.07.01.42.33;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	5444d9d078c63f4;
filename	WEB3IfoMockAppPlugin.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@package webbroker3.ifo;

import com.fitechlabs.xtrade.kernel.boot.Plugin;
import com.fitechlabs.xtrade.kernel.boot.Services;
import com.fitechlabs.xtrade.plugin.tc.gentrade.FinApp;
import com.fitechlabs.xtrade.plugin.tc.gentrade.GtlUtils;
import com.fitechlabs.xtrade.plugin.tc.gentrade.ProductTypeEnum;
import com.fitechlabs.xtrade.plugin.tc.gentrade.TradingModule;

import webbroker3.ifo.service.delegate.WEB3FuturesChangeCancelNotifyUnitService;
import webbroker3.ifo.service.delegate.WEB3FuturesOpenContractInputService;
import webbroker3.ifo.service.delegate.WEB3FuturesOrderExecNotifyUnitService;
import webbroker3.ifo.service.delegate.WEB3FuturesSettleContractOrderService;
import webbroker3.ifo.service.delegate.WEB3IfoChangeCancelOrderAcceptUnitService;
import webbroker3.ifo.service.delegate.WEB3IfoCloseNotifyUnitService;
import webbroker3.ifo.service.delegate.WEB3IfoExecuteEndNotifyUnitService;
import webbroker3.ifo.service.delegate.WEB3IfoExecutedMailSendService;
import webbroker3.ifo.service.delegate.WEB3IfoFrontOrderService;
import webbroker3.ifo.service.delegate.WEB3IfoOrderAcceptUnitService;
import webbroker3.ifo.service.delegate.WEB3OptionChangeCancelNotifyUnitService;
import webbroker3.ifo.service.delegate.WEB3OptionIndividualSettleContractListService;
import webbroker3.ifo.service.delegate.WEB3OptionOpenContractInputService;
import webbroker3.ifo.service.delegate.WEB3OptionOpenContractOrderService;
import webbroker3.ifo.service.delegate.WEB3OptionOrderExecNotifyUnitService;
import webbroker3.ifo.service.delegate.WEB3OptionOrderExecutedInquiryService;
import webbroker3.ifo.service.delegate.WEB3OptionSettleContractInputService;
import webbroker3.ifo.service.delegate.stdimpls.WEB3FuturesChangeCancelNotifyUnitServiceImplForMock;
import webbroker3.ifo.service.delegate.stdimpls.WEB3FuturesOpenContractInputServiceImplForMock;
import webbroker3.ifo.service.delegate.stdimpls.WEB3FuturesOrderExecNotifyUnitServiceImplForMock;
import webbroker3.ifo.service.delegate.stdimpls.WEB3FuturesSettleContractOrderServiceImplForMock;
import webbroker3.ifo.service.delegate.stdimpls.WEB3IfoChangeCancelOrderAcceptUnitServiceImplForMock;
import webbroker3.ifo.service.delegate.stdimpls.WEB3IfoCloseNotifyUnitServiceImplForMock;
import webbroker3.ifo.service.delegate.stdimpls.WEB3IfoExecuteEndNotifyUnitServiceImplForMock;
import webbroker3.ifo.service.delegate.stdimpls.WEB3IfoExecutedMailSendServiceImplForMock;
import webbroker3.ifo.service.delegate.stdimpls.WEB3IfoFrontOrderServiceImplForMock;
import webbroker3.ifo.service.delegate.stdimpls.WEB3IfoOrderAcceptUnitServiceImplForMock;
import webbroker3.ifo.service.delegate.stdimpls.WEB3OptionChangeCancelNotifyUnitServiceImplForMock;
import webbroker3.ifo.service.delegate.stdimpls.WEB3OptionIndividualSettleContractListServiceImplForMock;
import webbroker3.ifo.service.delegate.stdimpls.WEB3OptionOpenContractInputServiceImplForMock;
import webbroker3.ifo.service.delegate.stdimpls.WEB3OptionOpenContractOrderServiceImplForMock;
import webbroker3.ifo.service.delegate.stdimpls.WEB3OptionOrderExecNotifyUnitServiceImplForMock;
import webbroker3.ifo.service.delegate.stdimpls.WEB3OptionOrderExecutedInquiryServiceImplForMock;
import webbroker3.ifo.service.delegate.stdimpls.WEB3OptionSettleContractInputServiceImplForMock;
import webbroker3.quoteadaptor.prototype.WEB3ProtoQuoteDataSupplierServiceForMock;
import webbroker3.util.WEB3LogUtility;

public class WEB3IfoMockAppPlugin extends Plugin
{
    private static FinApp l_finApp = (FinApp) Services.getService(FinApp.class);
    /**
     * ログユーティリティ。
     */
    private static WEB3LogUtility log = WEB3LogUtility.getInstance(WEB3IfoMockAppPlugin.class);

    public WEB3IfoMockAppPlugin()
    {
        super();
        // TODO Auto-generated constructor stub
    }
    public static void plug()
    throws Exception
    {
        plug(WEB3IfoMockAppPlugin.class);
    }
    
    public static void onPlug()
        throws Exception
    {
      final String STR_METHOD_NAME = "plugXbifo()";
      log.entering(STR_METHOD_NAME);

      // 拡張取引モジュールForMock
      l_finApp.uninstallTradingModule("xb-ifo-pdbt");
      l_finApp.installTradingModule(new WEB3IfoTradingModuleForMock());

      TradingModule l_tradingModuleMock = GtlUtils.getTradingModule(ProductTypeEnum.IFO);
      
      l_tradingModuleMock = l_finApp.getTradingModule("xb-ifo-pdbt");

      l_tradingModuleMock.overrideBizLogicProvider(new WEB3IfoBizLogicProviderForMock());

      l_tradingModuleMock.overridePositionManager(new WEB3IfoPositionManagerImplForMock());

      // 拡張プロダクト・マネージャーForMock
      l_tradingModuleMock.overrideProductManager(new WEB3IfoProductManagerImplForMock());

      // OP注文マネージャForMock
      l_tradingModuleMock.overrideOrderManager(new WEB3FuturesOrderManagerImplForMock());

      l_tradingModuleMock.installQuoteDataSupplierService(new WEB3ProtoQuoteDataSupplierServiceForMock());

      // 先物OP注文受付UnitServiceImplForMock
      Services.overrideService(WEB3IfoOrderAcceptUnitService.class,
              new WEB3IfoOrderAcceptUnitServiceImplForMock());

      // 先物OP発注サービスImplForMock
      Services.overrideService(WEB3IfoFrontOrderService.class, new WEB3IfoFrontOrderServiceImplForMock());

      // 先物OP訂正取消受付一件サービス
      Services.overrideService(WEB3IfoChangeCancelOrderAcceptUnitService.class,
              new WEB3IfoChangeCancelOrderAcceptUnitServiceImplForMock());

      // 株式発注審査個別チェックForMock
      new WEB3IfoOrderManagerReusableValidationsForMock().register();
      // 先物OP失効通知UnitServiceForMock
      Services.overrideService(WEB3IfoCloseNotifyUnitService.class,
              new WEB3IfoCloseNotifyUnitServiceImplForMock());

      // 先物訂正取消通知一件サービス
      Services.overrideService(WEB3FuturesChangeCancelNotifyUnitService.class,
              new WEB3FuturesChangeCancelNotifyUnitServiceImplForMock());

      // OP訂正取消通知UnitServiceImpl(Mock)
      Services.overrideService(WEB3OptionChangeCancelNotifyUnitService.class,
              new WEB3OptionChangeCancelNotifyUnitServiceImplForMock());

      Services.overrideService(WEB3FuturesOrderExecNotifyUnitService.class,
              new WEB3FuturesOrderExecNotifyUnitServiceImplForMock());
      
      // WEB3IfoExecuteEndNotifyUnitServiceImplForMock
      Services.overrideService(WEB3IfoExecuteEndNotifyUnitService.class,
              new WEB3IfoExecuteEndNotifyUnitServiceImplForMock());
      

      // WEB3OptionOpenContractInputServiceImplForMock
      Services.overrideService(WEB3OptionOpenContractInputService.class, new WEB3OptionOpenContractInputServiceImplForMock());
      
      // WEB3OptionSettleContractInputServiceImplForMock
      Services.overrideService(WEB3OptionSettleContractInputService.class, new WEB3OptionSettleContractInputServiceImplForMock());
      
      // WEB3OptionIndividualSettleContractListServiceImplForMock
      Services.overrideService(WEB3OptionIndividualSettleContractListService.class, new WEB3OptionIndividualSettleContractListServiceImplForMock());
      
      // WEB3OptionOrderExecutedInquiryServiceImplForMock
      Services.overrideService(WEB3OptionOrderExecutedInquiryService.class, new WEB3OptionOrderExecutedInquiryServiceImplForMock());
      
      // WEB3OptionOpenContractOrderServiceImplForMock
      Services.overrideService(WEB3OptionOpenContractOrderService.class, new WEB3OptionOpenContractOrderServiceImplForMock());
      
      //WEB3OptionOrderExecNotifyUnitServiceImplForMock
      Services.overrideService(WEB3OptionOrderExecNotifyUnitService.class, new WEB3OptionOrderExecNotifyUnitServiceImplForMock());
      
      // WEB3IfoExecutedMailSendServiceImplForMock
      Services.overrideService(WEB3IfoExecutedMailSendService.class,
              new WEB3IfoExecutedMailSendServiceImplForMock());
      //WEB3FuturesOpenContractInputServiceImplForMock
      Services.overrideService(WEB3FuturesOpenContractInputService.class,
              new WEB3FuturesOpenContractInputServiceImplForMock());
      //WEB3FuturesSettleContractOrderServiceImplForMock
      Services.overrideService(WEB3FuturesSettleContractOrderService.class,
              new WEB3FuturesSettleContractOrderServiceImplForMock());
      log.exiting(STR_METHOD_NAME);
    }
}
@
