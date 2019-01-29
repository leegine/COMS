head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.04.07.02.28.09;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	5444d9d078c63f4;
filename	WEB3MockObjectAppPlugin.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@package webbroker3;

import com.fitechlabs.xtrade.kernel.boot.Plugin;
import com.fitechlabs.xtrade.kernel.boot.Services;
import com.fitechlabs.xtrade.plugin.security.oplogin.OpLoginAdminService;
import com.fitechlabs.xtrade.plugin.security.oplogin.OpLoginSecurityService;
import com.fitechlabs.xtrade.plugin.security.oplogin.impl.OpLoginAdminServiceImplForMock;
import com.fitechlabs.xtrade.plugin.security.oplogin.impl.OpLoginSecurityServiceImplForMock;
import com.fitechlabs.xtrade.plugin.tc.gentrade.FinApp;
import com.fitechlabs.xtrade.plugin.tc.gentrade.stdimpls.TradingSystemImplForMock;
import com.fitechlabs.xtrade.plugin.util.rac.RoundRobinBasedMultiPoolDataSource;
import com.fitechlabs.xtrade.plugin.util.rac.stdimpl.RoundRobinBasedMultiPoolDataSourceImplForMock;

import webbroker3.accountinfo.WEB3AccInfoMockAppPlugin;
import webbroker3.accountopen.service.delegate.WEB3AccOpenInformAcceptUnitService;
import webbroker3.accountopen.service.delegate.WEB3AccOpenVoucherRegAcceptUnitService;
import webbroker3.accountopen.service.delegate.stdimpls.WEB3AccOpenInformAcceptUnitServiceImplForMock;
import webbroker3.accountopen.service.delegate.stdimpls.WEB3AccOpenVoucherRegAcceptUnitServiceImplForMock;
import webbroker3.admintriggerorder.WEB3AdminToMockAppPlugin;
import webbroker3.aio.WEB3AioMockAppPlugin;
import webbroker3.dirsec.WEB3DirMockAppPlugin;
import webbroker3.eqtypeadmin.WEB3EqtypeAdminMockAppPlugin;
import webbroker3.equity.WEB3EquityMockAppPlugin;
import webbroker3.feq.WEB3FeqMockAppPlugin;
import webbroker3.gentrade.WEB3GentradeMockAppPlugin;
import webbroker3.ifo.WEB3IfoMockAppPlugin;
import webbroker3.ifodeposit.WEB3IfoDepositMockAppPlugin;
import webbroker3.inform.WEB3InformMockAppPlugin;
import webbroker3.mf.WEB3MutualFixedMockAppPlugin;
import webbroker3.mqgateway.WEB3MQGatewayPlugin;
import webbroker3.mqgateway.WEB3MQGatewayService;
import webbroker3.mqgateway.WEB3MQMessageSenderService;
import webbroker3.mqgateway.manager.service.delegate.WEB3MQManagementService;
import webbroker3.mqgateway.stdimpls.WEB3MQGatewayServiceImplForMock;
import webbroker3.mqgateway.stdimpls.WEB3MQManagementServiceImplForTest;
import webbroker3.mqgateway.stdimpls.WEB3MQMessageSenderServiceForTest;
import webbroker3.pvinfo.WEB3PvInfoMockAppPlugin;
import webbroker3.srvregi.service.delegate.stdimpls.WEB3SrvregiMockAppPlugin;
import webbroker3.trademanagement.WEB3TradeManagementMockAppPlugin;
import webbroker3.tradingpower.WEB3TpInfoMockAppPlugin;
import webbroker3.tradingpower.WEB3TplibMockAppPlugin;
import webbroker3.triggerorder.WEB3TriggerOrderMockPlugin;
import webbroker3.util.WEB3LogUtility;

public class WEB3MockObjectAppPlugin extends Plugin
{
    /**
     * ログユーティリティ。
     */
    private static WEB3LogUtility log = WEB3LogUtility.getInstance(WEB3MockObjectAppPlugin.class);

    private static FinApp l_finApp = (FinApp) Services.getService(FinApp.class);

    public WEB3MockObjectAppPlugin()
    {
        super();
    }

    /**
     * プラグインエントリーポイント。
     */
    public static void plug() throws Exception
    {
        String METHOD_NAME = "plug()";
        log.entering(METHOD_NAME);
        
        plug(WEB3MockObjectAppPlugin.class);

        log.exiting(METHOD_NAME);
    }

    /**
     * プラグイン処理。
     */
    public static void onPlug() throws Exception
    {
	    plugXTrade();
        plugMQGateway();
        plugSystem();
//        plugAccountOpen();
	    WEB3GentradeMockAppPlugin.plug();
//	    WEB3TriggerOrderMockPlugin.plug();	    
//        WEB3IfoMockAppPlugin.plug();
//        WEB3TplibMockAppPlugin.plug();
//        WEB3EquityMockAppPlugin.plug();
        WEB3AccInfoMockAppPlugin.plug();
//        WEB3MutualFixedMockAppPlugin.plug();
//        WEB3DirMockAppPlugin.plug();
//        WEB3SrvregiMockAppPlugin.plug();
//        WEB3ComplianceMockAppPlugin.plug();
//        WEB3AioMockAppPlugin.plug();
//        WEB3FeqMockAppPlugin.plug();
//        WEB3EqtypeAdminMockAppPlugin.plug();
//        WEB3InformMockAppPlugin.plug();
//        WEB3IfoDepositMockAppPlugin.plug();
//        WEB3TradeManagementMockAppPlugin.plug();
//        WEB3AdminToMockAppPlugin.plug();
//        WEB3TpInfoMockAppPlugin.plug();
//        WEB3PvInfoMockAppPlugin.plug();
    }
    private static void plugXTrade()
    {
        final String STR_METHOD_NAME = "plugXTrade()";
        log.entering(STR_METHOD_NAME);
        
        l_finApp.overrideTradingSystem(new TradingSystemImplForMock());
        Services.overrideService(OpLoginSecurityService.class, new OpLoginSecurityServiceImplForMock());
        Services.overrideService(OpLoginAdminService.class, new OpLoginAdminServiceImplForMock());
        Services.registerService(RoundRobinBasedMultiPoolDataSource.class,
                new RoundRobinBasedMultiPoolDataSourceImplForMock(null));
     
        log.exiting(STR_METHOD_NAME);
    }
    private static void plugMQGateway() throws Exception
    {
        final String STR_METHOD_NAME = "plugMQGateway()";
        log.entering(STR_METHOD_NAME);
        // Gatewayサービスを登録
        Services.overrideService(WEB3MQGatewayService.class, new WEB3MQGatewayServiceImplForMock());
        // WEB3MQGatewayPluginがインストールされていない場合はここでインストール
        if (!WEB3MQGatewayPlugin.isPlugged())
        {
            WEB3MQGatewayPlugin.plug();
            log.info("WEB3MQGatewayPlugin was plugged.");
        }
        
        Services.overrideService(
            WEB3MQMessageSenderService.class,
            new WEB3MQMessageSenderServiceForTest());
        
        Services.registerService(
                WEB3MQManagementService.class,
                new WEB3MQManagementServiceImplForTest());
        
        log.exiting(STR_METHOD_NAME);
    }
    
    private static void plugSystem()
    {
        final String STR_METHOD_NAME = "plugSystem()";
        log.entering(STR_METHOD_NAME);
        //Services.overrideService(WEB3OrderReqNumberHead2ManageService.class, new WEB3OrderReqNumberHead2ManageServiceImplForMock());
        log.exiting(STR_METHOD_NAME);
    }
//
//    private static void plugRuito()
//    {
//        final String STR_METHOD_NAME = "plugRuito()";
//        log.entering(STR_METHOD_NAME);
//        TradingModule l_tradingModule = l_finApp.getTradingModule(ProductTypeEnum.RUITO);
//        l_tradingModule.overrideOrderManager(new WEB3RuitoOrderManagerForMock());
//        log.exiting(STR_METHOD_NAME);
//    }
//
//    private static void plugXbfeq() throws AlreadyInstalledException
//    {
//        final String STR_METHOD_NAME = "plugXbfeq()";
//        log.entering(STR_METHOD_NAME);
//
//        l_finApp.uninstallTradingModule("xb-feq-pdbt");
//        log.info("Installing TradingModule : web3-feq");
//        l_finApp.installTradingModule(new WEB3FeqTradingModule());
//        log.info("Installed TradingModule : web3-feq");
//
//        TradingModule l_tradingModule = l_finApp.getTradingModule(ProductTypeEnum.FOREIGN_EQUITY);
//
//        // 市場リクエスト送信サービス
//        l_tradingModule.getMarketAdapter().installMarketRequestSenderService(
//                new WEB3FeqMarketRequestSenderService());
//
//        // ポジションマネージャ
//        l_tradingModule.overridePositionManager(new WEB3FeqPositionManager());
//        l_tradingModule.overrideOrderManager(new WEB3FeqOrderManagerForMock());
//        l_tradingModule.overrideBizLogicProvider(new WEB3FeqBizLogicProviderForMock());
//        l_tradingModule.overrideProductManager(new WEB3FeqProductManagerForMock());
//        
//        // WEB3FeqExecutionNotifyUnitServiceImplForMock
//        Services.overrideService(WEB3FeqExecutionNotifyUnitService.class, new WEB3FeqExecutionNotifyUnitServiceImplForMock());
//        
//        log.exiting(STR_METHOD_NAME);
//    }
//    
//    private static void plugComplianceAudit()
//    {
//        final String STR_METHOD_NAME = "plugComplianceAudit()";
//        log.entering(STR_METHOD_NAME);
//        Services.overrideService(WEB3ComplianceStatusCountReferenceService.class, new WEB3ComplianceStatusCountReferenceServiceImplForMock());
//        log.exiting(STR_METHOD_NAME);
//        
//    }
//    
    private static void plugAccountOpen()
    {
        final String STR_METHOD_NAME = "plugAccountOpen()";
        log.entering(STR_METHOD_NAME);
        Services.overrideService(WEB3AccOpenVoucherRegAcceptUnitService.class,new WEB3AccOpenVoucherRegAcceptUnitServiceImplForMock());
        // WEB3AccOpenInformAcceptUnitServiceImplForMock
        Services.overrideService(WEB3AccOpenInformAcceptUnitService.class,new WEB3AccOpenInformAcceptUnitServiceImplForMock());
        
        log.exiting(STR_METHOD_NAME);
    }
//    
//    private static void plugTplib()
//    {
//        final String STR_METHOD_NAME = "plugTplib()";
//        log.entering(STR_METHOD_NAME);
//
//        Services.overrideService(WEB3TPTradingPowerService.class, new WEB3TPTradingPowerServiceImplForMock());
//        
//        //WEB3TPPaymentRequisitionManageServiceImplForMock
////        Services.overrideService(WEB3TPPaymentRequisitionManageService.class, new WEB3TPPaymentRequisitionManageServiceImplForMock());
//        
//        // WEB3TPTradingPowerSettlementServiceImplForMock
//        Services.overrideService(WEB3TPTradingPowerSettlementService.class, new WEB3TPTradingPowerSettlementServiceImplForMock());
//        
//        log.exiting(STR_METHOD_NAME);
//    }
//    
//    private static void plugAdminTPLib()
//    {
//        final String STR_METHOD_NAME = "plugAdminTPLib()";
//        
//        //Services.overrideService(WEB3AdminTPPaymentRequisitionManageInfoService.class, new WEB3AdminTPPaymentRequisitionManageInfoServiceImplForMock());
//        
//        log.entering(STR_METHOD_NAME);
//        
//    }
//    private static void plugEquity() throws AlreadyInstalledException
//    {
//        final String STR_METHOD_NAME = "plugEquity()";
//        log.entering(STR_METHOD_NAME);
//
//        TradingModule l_tradingModuleMock = GtlUtils.getTradingModule(ProductTypeEnum.EQUITY);
//        TradingModule l_tradingModule = GtlUtils.getTradingModule(ProductTypeEnum.EQUITY);
//
//         Services.overrideService(WEB3EquityMarginExecuteReferenceService.class,
//         new WEB3EquityMarginExecuteReferenceServiceImplForMock());
//
//        l_finApp.uninstallTradingModule("eqtype");
//        l_finApp.installTradingModule(new WEB3EquityTradingModuleForMock());
//
//        l_tradingModuleMock = l_finApp.getTradingModule("eqtype");
//        l_tradingModuleMock.overrideOrderManager(new WEB3EquityPTSOrderManagerForMock());
//        l_tradingModuleMock.getMarketAdapter().installMarketRequestSenderService(
//                new WEB3EquityMarketRequestSenderServiceImpl());
//        // 拡張プロダクト・マネージャー
//        l_tradingModuleMock.overrideProductManager(new WEB3EquityProductManagerForMock());
//
//        // 計算サービスクラス
//        l_tradingModuleMock.overrideBizLogicProvider(new WEB3EquityBizLogicProviderForMock());
//
//        // ポジションマネージャ
//        l_tradingModuleMock.overridePositionManager(new WEB3EquityPositionManagerForMock());
//        // 時価情報提供サービス
//
//        // 信用取引注文約定照会サービスImplForMock
//        Services.overrideService(WEB3MarginExecuteReferenceService.class,
//                new WEB3MarginExecuteReferenceServiceImplForMock());
//        l_tradingModuleMock.installQuoteDataSupplierService(l_tradingModule.getQuoteDataSupplierService());
//
//        //WEB3EquityReceiveCloseOrderUnitServiceImplForMock
//        Services.overrideService(WEB3EquityReceiveCloseOrderUnitService.class,
//                new WEB3EquityReceiveCloseOrderUnitServiceImplForMock());
//
//        //WEB3EquityFrontOrderServiceImplForMock
//        Services.overrideService(WEB3EquityFrontOrderService.class,
//                new WEB3EquityFrontOrderServiceImplForMock());
//        
//        // WEB3EquityChangeOrderInputServiceImplForMock
//        Services.overrideService(WEB3EquityChangeOrderInputService.class,
//            new WEB3EquityChangeOrderInputServiceImplForMock());
//        
//        // WEB3EquityPTSChangeOrderInputServiceImplForMock
////        Services.overrideService(WEB3EquityPTSChangeOrderInputService.class,
////            new WEB3EquityPTSChangeOrderInputServiceImplForMock());
////        
////        // WEB3EquityPTSCancelOrderServiceImplForMock
////        Services.overrideService(WEB3EquityPTSCancelOrderService.class,
////            new WEB3EquityPTSCancelOrderServiceImplForMock());
////        
////        // WEB3EquityCancelOrderServiceImplForMock
////        Services.overrideService(WEB3EquityCancelOrderService.class,
////            new WEB3EquityCancelOrderServiceImplForMock());
////        
////        // WEB3EquityPTSChangeOrderServiceImplForMock
////        Services.overrideService(WEB3EquityPTSChangeOrderService.class,
////            new WEB3EquityPTSChangeOrderServiceImplForMock());
//        
//        // WEB3EquityChangeOrderServiceImplForMock
//        Services.overrideService(WEB3EquityChangeOrderService.class,
//            new WEB3EquityChangeOrderServiceImplForMock());
//        
//        //WEB3EquityPTSOrderManagerReusableValidationsForMock
//        new WEB3EquityPTSOrderManagerReusableValidationsForMock().register();
//        log.exiting(STR_METHOD_NAME);
//    }
//    
//    private static void plugEquityAdmin()
//    {
//        final String STR_METHOD_NAME = "plugEquityAdmin()";
//        log.entering(STR_METHOD_NAME);
//        
//        // WEB3AdminEquityPTSCancelExecServiceImplForMock
////        Services.overrideService(WEB3AdminEquityPTSCancelExecService.class,
////            new WEB3AdminEquityPTSCancelExecServiceImplForMock());
////        
////        // WEB3AdminEquityPTSInputExecServiceImplForMock
////        Services.overrideService(WEB3AdminEquityPTSInputExecService.class,
////            new WEB3AdminEquityPTSInputExecServiceImplForMock());
//        
//        //WEB3AdminEquityForcedSettleOrderDLServiceImplForMock
////        Services.overrideService(WEB3AdminEquityForcedSettleOrderDLService.class,
////                new WEB3AdminEquityForcedSettleOrderDLServiceImplForMock());
//        
//        log.exiting(STR_METHOD_NAME);
//    }
//
//    private static void plugWEB3Gentrade()
//    {
//        final String STR_METHOD_NAME = "plugWEB3Gentrade()";
//        log.entering(STR_METHOD_NAME);
//
//        l_finApp.overrideAccountManager(new WEB3GentradeAccountManagerForMock());
//        l_finApp.overrideFinObjectManager(new WEB3GentradeFinObjectManagerForMock());
//        l_finApp.overrideCommonOrderValidator(new WEB3GentradeOrderValidatorForMock());
//        l_finApp.overrideGlobalBizLogicProvider(new WEB3GentradeBizLogicProviderForMock());
//        // 電子鳩接続サービス
//        Services.overrideService(WEB3GentradeBatoClientService.class,
//                new WEB3GentradeBatoClientServiceImplForMock());
//        
//        // WEB3HostReqOrderNumberManageServiceImplForMock
//        Services.overrideService(WEB3HostReqOrderNumberManageService.class,
//                new WEB3HostReqOrderNumberManageServiceImplForMock());
//        log.exiting(STR_METHOD_NAME);
//    }
//

//
//    private static void plugXbifo() throws AlreadyInstalledException
//    {
//        final String STR_METHOD_NAME = "plugXbifo()";
//        log.entering(STR_METHOD_NAME);
//        TradingModule l_tradingModuleMock = GtlUtils.getTradingModule(ProductTypeEnum.IFO);
//        TradingModule l_tradingModule = GtlUtils.getTradingModule(ProductTypeEnum.IFO);
//
//        // 拡張取引モジュールForMock
//        l_finApp.uninstallTradingModule("xb-ifo-pdbt");
//        l_finApp.installTradingModule(new WEB3IfoTradingModuleForMock());
//
//        l_tradingModuleMock = l_finApp.getTradingModule("xb-ifo-pdbt");
//
//        l_tradingModuleMock.overrideBizLogicProvider(new WEB3IfoBizLogicProviderForMock());
//
//        l_tradingModuleMock.overridePositionManager(new WEB3IfoPositionManagerImplForMock());
//
//        // 拡張プロダクト・マネージャーForMock
//        l_tradingModuleMock.overrideProductManager(new WEB3IfoProductManagerImplForMock());
//
//        // OP注文マネージャForMock
//        l_tradingModuleMock.overrideOrderManager(new WEB3FuturesOrderManagerImplForMock());
//
//        l_tradingModuleMock.installQuoteDataSupplierService(new WEB3ProtoQuoteDataSupplierServiceForMock());
//
//        // 先物OP注文受付UnitServiceImplForMock
//        Services.overrideService(WEB3IfoOrderAcceptUnitService.class,
//                new WEB3IfoOrderAcceptUnitServiceImplForMock());
//
//        // 先物OP発注サービスImplForMock
//        Services.overrideService(WEB3IfoFrontOrderService.class, new WEB3IfoFrontOrderServiceImplForMock());
//
//        // 先物OP訂正取消受付一件サービス
//        Services.overrideService(WEB3IfoChangeCancelOrderAcceptUnitService.class,
//                new WEB3IfoChangeCancelOrderAcceptUnitServiceImplForMock());
//
//        // 株式発注審査個別チェックForMock
//        new WEB3IfoOrderManagerReusableValidationsForMock().register();
//        // 先物OP失効通知UnitServiceForMock
//        Services.overrideService(WEB3IfoCloseNotifyUnitService.class,
//                new WEB3IfoCloseNotifyUnitServiceImplForMock());
//
//        // 先物訂正取消通知一件サービス
//        Services.overrideService(WEB3FuturesChangeCancelNotifyUnitService.class,
//                new WEB3FuturesChangeCancelNotifyUnitServiceImplForMock());
//
//        // OP訂正取消通知UnitServiceImpl(Mock)
//        Services.overrideService(WEB3OptionChangeCancelNotifyUnitService.class,
//                new WEB3OptionChangeCancelNotifyUnitServiceImplForMock());
//
////        // OP注文繰越UnitServiceImplForMock
////        Services.overrideService(WEB3OptionOrderCarryOverUnitService.class,
////            new WEB3OptionOrderCarryOverUnitServiceImplForMock());
////
////        // 先物注文繰越UnitServiceImplForMock
////        Services.overrideService(WEB3FuturesOrderCarryOverUnitService.class,
////            new WEB3FuturesOrderCarryOverUnitServiceImplForMock());
//        // WEB3FuturesOrderExecNotifyUnitServiceImplForMock
//        Services.overrideService(WEB3FuturesOrderExecNotifyUnitService.class,
//                new WEB3FuturesOrderExecNotifyUnitServiceImplForMock());
//        
//        // WEB3IfoExecuteEndNotifyUnitServiceImplForMock
//        Services.overrideService(WEB3IfoExecuteEndNotifyUnitService.class,
//                new WEB3IfoExecuteEndNotifyUnitServiceImplForMock());
//        
//        // WEB3IfoDepositCalcServiceImplForMock()
//        Services.overrideService(WEB3IfoDepositCalcService.class, new WEB3IfoDepositCalcServiceImplForMock());
//        
//        // WEB3OptionOpenContractInputServiceImplForMock
//        Services.overrideService(WEB3OptionOpenContractInputService.class, new WEB3OptionOpenContractInputServiceImplForMock());
//        
//        // WEB3OptionSettleContractInputServiceImplForMock
//        Services.overrideService(WEB3OptionSettleContractInputService.class, new WEB3OptionSettleContractInputServiceImplForMock());
//        
//        // WEB3OptionIndividualSettleContractListServiceImplForMock
//        Services.overrideService(WEB3OptionIndividualSettleContractListService.class, new WEB3OptionIndividualSettleContractListServiceImplForMock());
//        
//        // WEB3OptionOrderExecutedInquiryServiceImplForMock
//        Services.overrideService(WEB3OptionOrderExecutedInquiryService.class, new WEB3OptionOrderExecutedInquiryServiceImplForMock());
//        
//        // WEB3OptionOpenContractOrderServiceImplForMock
//        Services.overrideService(WEB3OptionOpenContractOrderService.class, new WEB3OptionOpenContractOrderServiceImplForMock());
//        
//        log.exiting(STR_METHOD_NAME);
//    }
//
//    private static void plugPvInfo()
//    {
//        // ﾌﾟﾗｲﾍﾞｰﾄｲﾝﾌｫﾒｰｼｮﾝﾃﾞｰﾀﾏﾈｰｼﾞｬ
//        Services.overrideService(WEB3PvInfoDataManager.class, new WEB3PvInfoDataManagerImplForMock());
//        
//    }
//    
//    private static void plugInform()
//    {
//        // WEB3InformHostReqOrderNumberManageServiceImplForMock
//        Services.overrideService(WEB3InformHostReqOrderNumberManageService.class, new WEB3InformHostReqOrderNumberManageServiceImplForMock());
//        // WEB3InformAccSwElecDeliApplyCommonServiceImplForMock
//        Services.overrideService(WEB3InformAccSwElecDeliApplyCommonService.class, new WEB3InformAccSwElecDeliApplyCommonServiceImplForMock());
//        
//        // WEB3InformAccSwElecDeliApplyServiceImplForMock
//        Services.overrideService(WEB3InformAccSwElecDeliApplyService.class, new WEB3InformAccSwElecDeliApplyServiceImplForMock());
//        
//        // WEB3AdminInformSwElecDeliApplyServiceImplForMock
//        Services.overrideService(WEB3AdminInformSwElecDeliApplyService.class, new WEB3AdminInformSwElecDeliApplyServiceImplForMock());
//    }
//    
//    private static void plugMQGateway()
//    {
//        final String STR_METHOD_NAME = "plugMQGateway()";
//        log.entering(STR_METHOD_NAME);
//        // Gatewayサービスを登録
//        Services.overrideService(WEB3MQGatewayService.class, new WEB3MQGatewayServiceImplForMock());
//        log.exiting(STR_METHOD_NAME);
//    }
//
//    private static void plugAccountinfo()
//    {
//        final String STR_METHOD_NAME = "plugAccountinfo()";
//        log.entering(STR_METHOD_NAME);
//
//        // 手数料割引キャンペーン共通クラスForMock
//        new WEB3AdminAccInfoCampaignCommonForMock().register();
//
//        // 個別顧客指定変更サービスImplMock
//        Services.overrideService(WEB3AdminAccInfoCampaignIndiviChangeService.class,
//                new WEB3AdminAccInfoCampaignIndiviChangeServiceImplForMock());
//        Services.overrideService(WEB3AdminAccInfoCampaignRegistAccListService.class,
//                new WEB3AdminAccInfoCampaignRegistAccListServiceImplForMock());
//
//        Services.overrideService(WEB3AdminAccInfoCampaignAccOpenChangeService.class,
//                new WEB3AdminAccInfoCampaignAccOpenChangeServiceImplForMock());
//
//        log.exiting(STR_METHOD_NAME);
//    }
//    
//    private static void plugXbbd() throws AlreadyInstalledException
//    {
//        final String STR_METHOD_NAME = "plugXbbd()";
//        log.entering(STR_METHOD_NAME);
//        
//        TradingModule l_tradingModule = GtlUtils.getTradingModule(ProductTypeEnum.BOND);
//        l_tradingModule.overrideOrderManager(new WEB3BondOrderManagerForMock());
//        l_tradingModule.overrideProductManager(new WEB3BondProductManagerForMock());
//        l_tradingModule.overrideBizLogicProvider(new WEB3BondBizLogicProviderForMock());
//        
//        Services.overrideService(WEB3AdminBondHelperService.class, new WEB3AdminBondHelperServiceImplForMock());
//        //Services.overrideService(WEB3AdminBondDomesticProductListService.class, new WEB3AdminBondDomesticProductListServiceImplForMock());
//
//        log.exiting(STR_METHOD_NAME);
//    }
//    
//    private static void plugAdminorderexecinquiry() throws AlreadyInstalledException
//    {
//        final String STR_METHOD_NAME = "plugAdminorderexecinquiry()";
//        log.entering(STR_METHOD_NAME);
//        log.exiting(STR_METHOD_NAME);
//    }
//
//    private static void plugXbaio() throws AlreadyInstalledException 
//    {
//        final String STR_METHOD_NAME = "plugXbaio()";
//        log.entering(STR_METHOD_NAME);
//
//        TradingModule l_tradingModule = GtlUtils.getTradingModule(ProductTypeEnum.AIO);
//        TradingModule l_tradingModuleMock = GtlUtils.getTradingModule(ProductTypeEnum.AIO);
//        
//        l_finApp.uninstallTradingModule("xb-aio-pdbt");
//        l_finApp.installTradingModule(new WEB3AioTradingModuleForMock());
//        
//        l_tradingModuleMock = l_finApp.getTradingModule("xb-aio-pdbt");
//        
//        // 計算サービスクラス
//        l_tradingModuleMock.overrideBizLogicProvider(new WEB3AioBizLogicProviderForMock());
//        // 拡張注文マネージャ
//        l_tradingModuleMock.overrideOrderManager(new WEB3AioOrderManagerForMock());
//        
//        
//        l_tradingModuleMock.getMarketAdapter().installMarketRequestSenderService(
//            new WEB3AioMarketRequestSenderServiceImplForMock());
//        
//        // AIOプロダクトマネージャ
//        l_tradingModuleMock.overrideProductManager(l_tradingModule.getProductManager());
//        
//        // ポジションマネージャ
//        l_tradingModuleMock.overridePositionManager(l_tradingModule.getPositionManager());
//        
//        //管理者入出金一覧サービスImplForMock
//        Services.overrideService(WEB3AdminAioListService.class, new WEB3AdminAioListServiceImplForMock());
//        
//        //出金余力チェックUnitServiceImplForMock
//        Services.overrideService(WEB3AioCashoutTradingPowerUnitService.class, 
//            new WEB3AioCashoutTradingPowerUnitServiceImplForMock());
//        // WEB3MarginTransferServiceImplForMock
//        Services.overrideService(WEB3MarginTransferService.class, 
//            new WEB3MarginTransferServiceImplForMock());
//        // WEB3FXTelegramProcessServiceImplForMock
//        Services.overrideService(WEB3FXTelegramProcessService.class, 
//            new WEB3FXTelegramProcessServiceImplForMock());
//        // WEB3FXDataControlServiceImplForMock
//        Services.overrideService(WEB3FXDataControlService.class, 
//            new WEB3FXDataControlServiceImplForMock());
//        
//        // WEB3AdminAioSLCashOutStopReleaseServiceImplForMock
//        Services.overrideService(WEB3AdminAioSLCashOutStopReleaseService.class, 
//            new WEB3AdminAioSLCashOutStopReleaseServiceImplForMock());
//        
//        // WEB3AdminAioSLProductCancelServiceImplForMock
//        Services.overrideService(WEB3AdminAioSLProductCancelService.class, 
//            new WEB3AdminAioSLProductCancelServiceImplForMock());
//        
//        // WEB3AdminAioSLProductRegistServiceImplForMock
//        Services.overrideService(WEB3AdminAioSLProductRegistService.class, 
//            new WEB3AdminAioSLProductRegistServiceImplForMock());
//        
//         //WEB3AdminAioSLProductChangeServiceImplForMock
//        Services.overrideService(WEB3AdminAioSLProductChangeService.class, 
//            new WEB3AdminAioSLProductChangeServiceImplForMock());
//         
//        // WEB3AdminAioSLProductRegistControlServiceImplForMock
//        Services.overrideService(WEB3AdminAioSLProductRegistControlService.class, 
//            new WEB3AdminAioSLProductRegistControlServiceImplForMock());
//        
//        log.exiting(STR_METHOD_NAME);
//    }
//
//    private static void plugSrvregi() throws AlreadyInstalledException
//    {
//        final String STR_METHOD_NAME = "plugSrvregi()";
//        log.entering(STR_METHOD_NAME);
//        
//        //WEB3SrvRegiRegistServiceImplForMock
//        Services.overrideService(WEB3SrvRegiRegistService.class, new WEB3SrvRegiRegistServiceImplForMock());
//        // WEB3AdminSrvRegiAccountDataUploadUnitServiceImplForMock.java
//        Services.overrideService(WEB3AdminSrvRegiAccountDataUploadUnitService.class, new WEB3AdminSrvRegiAccountDataUploadUnitServiceImplForMock());
//        log.exiting(STR_METHOD_NAME);
//    }
}
@
