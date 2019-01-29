head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.15.01.54.43;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	8704d7ec30c0c9d;
filename	WEB3ToSuccAppPlugin.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : Webbroker3-TriggerOrder プラグインクラス(WEB3ToSuccAppPlugin.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2005/10/18 郭英 (中訊) 新規作成 
Revesion History : 2006/01/23 郭英（中訊）障害U02732対応
Revesion History : 2006/01/24 郭英（中訊）仕様変更・モデル066
Revesion History : 2006/02/07 呉艶飛（中訊）仕様変更・モデル091,092                   
Revesion History : 2006/02/19 沈迪（中訊）仕様変更・モデル094,097~099
Revesion History : 2006/03/09 韋念瓊（中訊）仕様変更・モデル109,110,120~125
Revesion History : 2006/08/31 肖志偉（中訊）仕様変更・モデル157
Revesion History : 2006/11/27 徐宏偉 (中訊)仕様変更・モデル176
Revesion History : 2006/11/27 徐宏偉 (中訊)仕様変更・モデル184
Revesion History : 2008/03/25 トウ鋒鋼 (中訊) 【IFO】連続注文対応
Revesion History : 2008/04/11 趙林鵬 (中訊)仕様変更・モデル277
Revesion History : 2008/04/18 孟亞南 (中訊)【IFO】連続注文対応
Revesion History : 2008/05/06 トウ鋒鋼 (中訊)【IFO】連続注文対応
*/
package webbroker3.triggerorder;

import com.fitechlabs.xtrade.kernel.boot.KernelPlugin;
import com.fitechlabs.xtrade.kernel.boot.Plugin;
import com.fitechlabs.xtrade.kernel.boot.Services;
import com.fitechlabs.xtrade.kernel.interceptor.TransactionalInterceptor;

import webbroker3.common.WEB3LogSysTimeInterceptor;
import webbroker3.equity.WEB3EquityChangeOrderInputServiceInterceptor;
import webbroker3.equity.WEB3EquityOrderBuyInputServiceInterceptor;
import webbroker3.equity.WEB3EquityOrderServiceInterceptor;
import webbroker3.equity.WEB3EquitySellOrderInputServiceInterceptor;
import webbroker3.equity.WEB3MarginChangeCloseMarginInputServiceInterceptor;
import webbroker3.equity.WEB3MarginChangeOpenMarginInputServiceInterceptor;
import webbroker3.equity.WEB3MarginCloseMarginInputServiceInterceptor;
import webbroker3.equity.WEB3MarginCloseMarginServiceInterceptor;
import webbroker3.equity.WEB3MarginOpenMarginInputServiceInterceptor;
import webbroker3.equity.WEB3MarginOpenMarginServiceInterceptor;
import webbroker3.equity.WEB3MarginSwapMarginInputServiceInterceptor;
import webbroker3.equity.WEB3MarginSwapMarginServiceInterceptor;
import webbroker3.ifo.WEB3FuturesOpenContractInputServiceInterceptor;
import webbroker3.ifo.WEB3FuturesOpenContractServiceInterceptor;
import webbroker3.ifo.WEB3FuturesSettleContractInputServiceInterceptor;
import webbroker3.ifo.WEB3FuturesSettleContractOrderServiceInterceptor;
import webbroker3.ifo.WEB3IfoAppPlugin;
import webbroker3.ifo.WEB3OptionOpenContractInputServiceInterceptor;
import webbroker3.ifo.WEB3OptionOpenContractOrderServiceInterceptor;
import webbroker3.ifo.WEB3OptionSettleContractInputServiceInterceptor;
import webbroker3.ifo.WEB3OptionSettleContractOrderServiceInterceptor;
import webbroker3.mqgateway.WEB3MQGatewayInterceptor;
import webbroker3.triggerorder.base.WEB3ToSuccBaseAppPlugin;
import webbroker3.triggerorder.handler.WEB3FuturesOrderCarryOverHandler;
import webbroker3.triggerorder.handler.WEB3OptionOrderCarryOverHandler;
import webbroker3.triggerorder.handler.WEB3ToIfoManualOrderHandler;
import webbroker3.triggerorder.handler.WEB3ToStopEquityManualOrderHandler;
import webbroker3.triggerorder.handler.WEB3ToSuccAdditionalContentsSelectHandler;
import webbroker3.triggerorder.handler.WEB3ToSuccEquityCancelOrderHandler;
import webbroker3.triggerorder.handler.WEB3ToSuccEquityChangeOrderHandler;
import webbroker3.triggerorder.handler.WEB3ToSuccEquityChangeOrderInputHandler;
import webbroker3.triggerorder.handler.WEB3ToSuccEquityManualOrderHandler;
import webbroker3.triggerorder.handler.WEB3ToSuccEquityOrderBuyInputHandler;
import webbroker3.triggerorder.handler.WEB3ToSuccEquityOrderHandler;
import webbroker3.triggerorder.handler.WEB3ToSuccEquitySellOrderInputHandler;
import webbroker3.triggerorder.handler.WEB3ToSuccFuturesCancelOrderHandler;
import webbroker3.triggerorder.handler.WEB3ToSuccFuturesChangeClosingContractHandler;
import webbroker3.triggerorder.handler.WEB3ToSuccFuturesChangeClosingContractInputHandler;
import webbroker3.triggerorder.handler.WEB3ToSuccFuturesChangeOpenContractHandler;
import webbroker3.triggerorder.handler.WEB3ToSuccFuturesChangeOpenContractInputHandler;
import webbroker3.triggerorder.handler.WEB3ToSuccFuturesOpenContractHandler;
import webbroker3.triggerorder.handler.WEB3ToSuccFuturesOpenContractInputHandler;
import webbroker3.triggerorder.handler.WEB3ToSuccFuturesSettleContractInputHandler;
import webbroker3.triggerorder.handler.WEB3ToSuccFuturesSettleContractOrderHandler;
import webbroker3.triggerorder.handler.WEB3ToSuccMarginCancelHandler;
import webbroker3.triggerorder.handler.WEB3ToSuccMarginChangeCloseMarginHandler;
import webbroker3.triggerorder.handler.WEB3ToSuccMarginChangeCloseMarginInputHandler;
import webbroker3.triggerorder.handler.WEB3ToSuccMarginChangeOpenMarginHandler;
import webbroker3.triggerorder.handler.WEB3ToSuccMarginChangeOpenMarginInputHandler;
import webbroker3.triggerorder.handler.WEB3ToSuccMarginCloseMarginHandler;
import webbroker3.triggerorder.handler.WEB3ToSuccMarginCloseMarginInputHandler;
import webbroker3.triggerorder.handler.WEB3ToSuccMarginOpenMarginHandler;
import webbroker3.triggerorder.handler.WEB3ToSuccMarginOpenMarginInputHandler;
import webbroker3.triggerorder.handler.WEB3ToSuccMarginSwapMarginHandler;
import webbroker3.triggerorder.handler.WEB3ToSuccMarginSwapMarginInputHandler;
import webbroker3.triggerorder.handler.WEB3ToSuccOptionCancelOrderHandler;
import webbroker3.triggerorder.handler.WEB3ToSuccOptionChangeClosingContractHandler;
import webbroker3.triggerorder.handler.WEB3ToSuccOptionChangeClosingContractInputHandler;
import webbroker3.triggerorder.handler.WEB3ToSuccOptionChangeOpenContractHandler;
import webbroker3.triggerorder.handler.WEB3ToSuccOptionChangeOpenContractInputHandler;
import webbroker3.triggerorder.handler.WEB3ToSuccOptionOpenContractInputHandler;
import webbroker3.triggerorder.handler.WEB3ToSuccOptionOpenContractOrderHandler;
import webbroker3.triggerorder.handler.WEB3ToSuccOptionSettleContractHandler;
import webbroker3.triggerorder.handler.WEB3ToSuccOptionSettleContractInputServiceHandler;
import webbroker3.triggerorder.handler.WEB3ToSuccSettingContentsConfirmHandler;
import webbroker3.triggerorder.handler.WEB3ToSuccSettingListHandler;
import webbroker3.triggerorder.handler.WEB3ToWLimitEquityManualSwitchHandler;
import webbroker3.triggerorder.message.*;
import webbroker3.triggerorder.service.delegate.WEB3FuturesOrderCarryOverService;
import webbroker3.triggerorder.service.delegate.WEB3FuturesOrderCarryOverUnitService;
import webbroker3.triggerorder.service.delegate.WEB3OptionOrderCarryOverService;
import webbroker3.triggerorder.service.delegate.WEB3OptionOrderCarryOverUnitService;
import webbroker3.triggerorder.service.delegate.WEB3ToIfoManualOrderService;
import webbroker3.triggerorder.service.delegate.WEB3ToIfoManualOrderUnitService;
import webbroker3.triggerorder.service.delegate.WEB3ToStopEquityManualOrderMainService;
import webbroker3.triggerorder.service.delegate.WEB3ToStopEquityManualOrderUnitService;
import webbroker3.triggerorder.service.delegate.WEB3ToStopEquityOrderUnitService;
import webbroker3.triggerorder.service.delegate.WEB3ToStopIfoOrderUnitService;
import webbroker3.triggerorder.service.delegate.WEB3ToStopOrderService;
import webbroker3.triggerorder.service.delegate.WEB3ToSuccAdditionalContentsSelectService;
import webbroker3.triggerorder.service.delegate.WEB3ToSuccDataGettingService;
import webbroker3.triggerorder.service.delegate.WEB3ToSuccEquityCancelOrderService;
import webbroker3.triggerorder.service.delegate.WEB3ToSuccEquityChangeOrderInputService;
import webbroker3.triggerorder.service.delegate.WEB3ToSuccEquityChangeOrderService;
import webbroker3.triggerorder.service.delegate.WEB3ToSuccEquityManualOrderMainService;
import webbroker3.triggerorder.service.delegate.WEB3ToSuccEquityManualOrderUnitService;
import webbroker3.triggerorder.service.delegate.WEB3ToSuccEquityOrderBuyInputService;
import webbroker3.triggerorder.service.delegate.WEB3ToSuccEquityOrderService;
import webbroker3.triggerorder.service.delegate.WEB3ToSuccEquityOrderUnitService;
import webbroker3.triggerorder.service.delegate.WEB3ToSuccEquitySellOrderInputService;
import webbroker3.triggerorder.service.delegate.WEB3ToSuccFuturesCancelOrderService;
import webbroker3.triggerorder.service.delegate.WEB3ToSuccFuturesChangeClosingContractInputService;
import webbroker3.triggerorder.service.delegate.WEB3ToSuccFuturesChangeClosingContractService;
import webbroker3.triggerorder.service.delegate.WEB3ToSuccFuturesChangeOpenContractInputService;
import webbroker3.triggerorder.service.delegate.WEB3ToSuccFuturesChangeOpenContractService;
import webbroker3.triggerorder.service.delegate.WEB3ToSuccFuturesOpenContractInputService;
import webbroker3.triggerorder.service.delegate.WEB3ToSuccFuturesOpenContractService;
import webbroker3.triggerorder.service.delegate.WEB3ToSuccFuturesSettleContractInputService;
import webbroker3.triggerorder.service.delegate.WEB3ToSuccFuturesSettleContractOrderService;
import webbroker3.triggerorder.service.delegate.WEB3ToSuccIfoOrderCarryOverService;
import webbroker3.triggerorder.service.delegate.WEB3ToSuccIfoOrderUnitService;
import webbroker3.triggerorder.service.delegate.WEB3ToSuccMarginCancelService;
import webbroker3.triggerorder.service.delegate.WEB3ToSuccMarginChangeCloseMarginInputService;
import webbroker3.triggerorder.service.delegate.WEB3ToSuccMarginChangeCloseMarginService;
import webbroker3.triggerorder.service.delegate.WEB3ToSuccMarginChangeOpenMarginInputService;
import webbroker3.triggerorder.service.delegate.WEB3ToSuccMarginChangeOpenMarginService;
import webbroker3.triggerorder.service.delegate.WEB3ToSuccMarginCloseMarginInputService;
import webbroker3.triggerorder.service.delegate.WEB3ToSuccMarginCloseMarginService;
import webbroker3.triggerorder.service.delegate.WEB3ToSuccMarginOpenMarginInputService;
import webbroker3.triggerorder.service.delegate.WEB3ToSuccMarginOpenMarginService;
import webbroker3.triggerorder.service.delegate.WEB3ToSuccMarginSwapMarginInputService;
import webbroker3.triggerorder.service.delegate.WEB3ToSuccMarginSwapMarginService;
import webbroker3.triggerorder.service.delegate.WEB3ToSuccOptionCancelOrderService;
import webbroker3.triggerorder.service.delegate.WEB3ToSuccOptionChangeClosingContractInputService;
import webbroker3.triggerorder.service.delegate.WEB3ToSuccOptionChangeClosingContractService;
import webbroker3.triggerorder.service.delegate.WEB3ToSuccOptionChangeOpenContractInputService;
import webbroker3.triggerorder.service.delegate.WEB3ToSuccOptionChangeOpenContractService;
import webbroker3.triggerorder.service.delegate.WEB3ToSuccOptionOpenContractInputService;
import webbroker3.triggerorder.service.delegate.WEB3ToSuccOptionOpenContractOrderService;
import webbroker3.triggerorder.service.delegate.WEB3ToSuccOptionSettleContractInputService;
import webbroker3.triggerorder.service.delegate.WEB3ToSuccOptionSettleContractOrderService;
import webbroker3.triggerorder.service.delegate.WEB3ToSuccOrderService;
import webbroker3.triggerorder.service.delegate.WEB3ToSuccSettingContentsConfirmService;
import webbroker3.triggerorder.service.delegate.WEB3ToSuccSettingListService;
import webbroker3.triggerorder.service.delegate.WEB3ToWLimitEquityManualSwitchMainService;
import webbroker3.triggerorder.service.delegate.WEB3ToWLimitEquityManualSwitchUnitService;
import webbroker3.triggerorder.service.delegate.WEB3ToWLimitEquitySwitchUnitService;
import webbroker3.triggerorder.service.delegate.WEB3ToWLimitIfoSwitchUnitService;
import webbroker3.triggerorder.service.delegate.WEB3ToWLimitMarginCloseMarginSwitchUnitService;
import webbroker3.triggerorder.service.delegate.WEB3ToWLimitMarginOpenMarginSwitchUnitService;
import webbroker3.triggerorder.service.delegate.WEB3ToWLimitSwitchService;
import webbroker3.triggerorder.service.delegate.stdimpls.WEB3FuturesOrderCarryOverServiceImpl;
import webbroker3.triggerorder.service.delegate.stdimpls.WEB3FuturesOrderCarryOverUnitServiceImpl;
import webbroker3.triggerorder.service.delegate.stdimpls.WEB3OptionOrderCarryOverServiceImpl;
import webbroker3.triggerorder.service.delegate.stdimpls.WEB3OptionOrderCarryOverUnitServiceImpl;
import webbroker3.triggerorder.service.delegate.stdimpls.WEB3ToIfoManualOrderServiceImpl;
import webbroker3.triggerorder.service.delegate.stdimpls.WEB3ToIfoManualOrderUnitServiceImpl;
import webbroker3.triggerorder.service.delegate.stdimpls.WEB3ToStopEquityManualOrderMainServiceImpl;
import webbroker3.triggerorder.service.delegate.stdimpls.WEB3ToStopEquityManualOrderUnitServiceImpl;
import webbroker3.triggerorder.service.delegate.stdimpls.WEB3ToStopEquityOrderUnitServiceImpl;
import webbroker3.triggerorder.service.delegate.stdimpls.WEB3ToStopIfoOrderUnitServiceImpl;
import webbroker3.triggerorder.service.delegate.stdimpls.WEB3ToStopOrderServiceImpl;
import webbroker3.triggerorder.service.delegate.stdimpls.WEB3ToSuccAdditionalContentsSelectServiceImpl;
import webbroker3.triggerorder.service.delegate.stdimpls.WEB3ToSuccDataGettingServiceImpl;
import webbroker3.triggerorder.service.delegate.stdimpls.WEB3ToSuccEquityCancelOrderServiceImpl;
import webbroker3.triggerorder.service.delegate.stdimpls.WEB3ToSuccEquityChangeOrderInputServiceImpl;
import webbroker3.triggerorder.service.delegate.stdimpls.WEB3ToSuccEquityChangeOrderServiceImpl;
import webbroker3.triggerorder.service.delegate.stdimpls.WEB3ToSuccEquityManualOrderMainServiceImpl;
import webbroker3.triggerorder.service.delegate.stdimpls.WEB3ToSuccEquityManualOrderUnitServiceImpl;
import webbroker3.triggerorder.service.delegate.stdimpls.WEB3ToSuccEquityOrderBuyInputServiceImpl;
import webbroker3.triggerorder.service.delegate.stdimpls.WEB3ToSuccEquityOrderServiceImpl;
import webbroker3.triggerorder.service.delegate.stdimpls.WEB3ToSuccEquityOrderUnitServiceImpl;
import webbroker3.triggerorder.service.delegate.stdimpls.WEB3ToSuccEquitySellOrderInputServiceImpl;
import webbroker3.triggerorder.service.delegate.stdimpls.WEB3ToSuccFuturesCancelOrderServiceImpl;
import webbroker3.triggerorder.service.delegate.stdimpls.WEB3ToSuccFuturesChangeClosingContractInputServiceImpl;
import webbroker3.triggerorder.service.delegate.stdimpls.WEB3ToSuccFuturesChangeClosingContractServiceImpl;
import webbroker3.triggerorder.service.delegate.stdimpls.WEB3ToSuccFuturesChangeOpenContractInputServiceImpl;
import webbroker3.triggerorder.service.delegate.stdimpls.WEB3ToSuccFuturesChangeOpenContractServiceImpl;
import webbroker3.triggerorder.service.delegate.stdimpls.WEB3ToSuccFuturesOpenContractInputServiceImpl;
import webbroker3.triggerorder.service.delegate.stdimpls.WEB3ToSuccFuturesOpenContractServiceImpl;
import webbroker3.triggerorder.service.delegate.stdimpls.WEB3ToSuccFuturesSettleContractInputServiceImpl;
import webbroker3.triggerorder.service.delegate.stdimpls.WEB3ToSuccFuturesSettleContractOrderServiceImpl;
import webbroker3.triggerorder.service.delegate.stdimpls.WEB3ToSuccIfoOrderCarryOverServiceImpl;
import webbroker3.triggerorder.service.delegate.stdimpls.WEB3ToSuccIfoOrderUnitServiceImpl;
import webbroker3.triggerorder.service.delegate.stdimpls.WEB3ToSuccMarginCancelServiceImpl;
import webbroker3.triggerorder.service.delegate.stdimpls.WEB3ToSuccMarginChangeCloseMarginInputServiceImpl;
import webbroker3.triggerorder.service.delegate.stdimpls.WEB3ToSuccMarginChangeCloseMarginServiceImpl;
import webbroker3.triggerorder.service.delegate.stdimpls.WEB3ToSuccMarginChangeOpenMarginInputServiceImpl;
import webbroker3.triggerorder.service.delegate.stdimpls.WEB3ToSuccMarginChangeOpenMarginServiceImpl;
import webbroker3.triggerorder.service.delegate.stdimpls.WEB3ToSuccMarginCloseMarginInputServiceImpl;
import webbroker3.triggerorder.service.delegate.stdimpls.WEB3ToSuccMarginCloseMarginServiceImpl;
import webbroker3.triggerorder.service.delegate.stdimpls.WEB3ToSuccMarginOpenMarginInputServiceImpl;
import webbroker3.triggerorder.service.delegate.stdimpls.WEB3ToSuccMarginOpenMarginServiceImpl;
import webbroker3.triggerorder.service.delegate.stdimpls.WEB3ToSuccMarginSwapMarginInputServiceImpl;
import webbroker3.triggerorder.service.delegate.stdimpls.WEB3ToSuccMarginSwapMarginServiceImpl;
import webbroker3.triggerorder.service.delegate.stdimpls.WEB3ToSuccOptionCancelOrderServiceImpl;
import webbroker3.triggerorder.service.delegate.stdimpls.WEB3ToSuccOptionChangeClosingContractInputServiceImpl;
import webbroker3.triggerorder.service.delegate.stdimpls.WEB3ToSuccOptionChangeClosingContractServiceImpl;
import webbroker3.triggerorder.service.delegate.stdimpls.WEB3ToSuccOptionChangeOpenContractInputServiceImpl;
import webbroker3.triggerorder.service.delegate.stdimpls.WEB3ToSuccOptionChangeOpenContractServiceImpl;
import webbroker3.triggerorder.service.delegate.stdimpls.WEB3ToSuccOptionOpenContractInputServiceImpl;
import webbroker3.triggerorder.service.delegate.stdimpls.WEB3ToSuccOptionOpenContractOrderServiceImpl;
import webbroker3.triggerorder.service.delegate.stdimpls.WEB3ToSuccOptionSettleContractInputServiceImpl;
import webbroker3.triggerorder.service.delegate.stdimpls.WEB3ToSuccOptionSettleContractOrderServiceImpl;
import webbroker3.triggerorder.service.delegate.stdimpls.WEB3ToSuccOrderServiceImpl;
import webbroker3.triggerorder.service.delegate.stdimpls.WEB3ToSuccSettingContentsConfirmServiceImpl;
import webbroker3.triggerorder.service.delegate.stdimpls.WEB3ToSuccSettingListServiceImpl;
import webbroker3.triggerorder.service.delegate.stdimpls.WEB3ToWLimitEquityManualSwitchMainServiceImpl;
import webbroker3.triggerorder.service.delegate.stdimpls.WEB3ToWLimitEquityManualSwitchUnitServiceImpl;
import webbroker3.triggerorder.service.delegate.stdimpls.WEB3ToWLimitEquitySwitchUnitServiceImpl;
import webbroker3.triggerorder.service.delegate.stdimpls.WEB3ToWLimitIfoSwitchUnitServiceImpl;
import webbroker3.triggerorder.service.delegate.stdimpls.WEB3ToWLimitMarginCloseMarginSwitchUnitServiceImpl;
import webbroker3.triggerorder.service.delegate.stdimpls.WEB3ToWLimitMarginOpenMarginSwitchUnitServiceImpl;
import webbroker3.triggerorder.service.delegate.stdimpls.WEB3ToWLimitSwitchServiceImpl;
import webbroker3.util.WEB3LogUtility;

/**
 * Webbroker3-TriggerOrder プラグインクラス<BR>
 *   
 * @@author 郭英
 * @@version 1.0
 */
public class WEB3ToSuccAppPlugin extends Plugin
{
    /**
     * ログユーティリティ。
     */
    private static WEB3LogUtility log =
        WEB3LogUtility.getInstance(WEB3ToSuccAppPlugin.class);

    /**
     * コンストラクタ。
     */
    public WEB3ToSuccAppPlugin()
    {
    }

    /**
     * プラグインエントリーポイント。
     */
    public static void plug() throws Exception
    {
        String METHOD_NAME = "plug()";
        log.entering(METHOD_NAME);
        
        plug(WEB3ToSuccAppPlugin.class);
        
        log.exiting(METHOD_NAME);
    }
    
    /**
     * プラグイン処理。
     */
    public static void onPlug() throws Exception 
    {
        String METHOD_NAME = "onPlug()";
        log.entering(METHOD_NAME);
        
        //このプラグインより先に読み込む必要のあるプラグインの指定。
        //install the system plugins that we need
        KernelPlugin.plug();

        WEB3ToSuccBaseAppPlugin.plug();
        
        //Service の登録
        //設定内容確認サービス
        Services.registerService(
            WEB3ToSuccSettingContentsConfirmService.class,
            new WEB3ToSuccSettingContentsConfirmServiceImpl());
        
        //追加内容選択サービス
        Services.registerService(
            WEB3ToSuccAdditionalContentsSelectService.class,
            new WEB3ToSuccAdditionalContentsSelectServiceImpl());
            
        //連続設定一覧サービス
        Services.registerService(
            WEB3ToSuccSettingListService.class,
            new WEB3ToSuccSettingListServiceImpl());
            
        //連続注文データ取得サービス     
        Services.registerService(
            WEB3ToSuccDataGettingService.class,
            new WEB3ToSuccDataGettingServiceImpl());
        
        //連続注文株式発注一件サービス
        Services.registerService(
            WEB3ToSuccEquityOrderUnitService.class,
            new WEB3ToSuccEquityOrderUnitServiceImpl()); 
        
        //連続注文発注サービス
        Services.registerService(
            WEB3ToSuccOrderService.class,
            new WEB3ToSuccOrderServiceImpl()); 
            
        //逆指値注文株式発注一件サービス 
        Services.registerService(
            WEB3ToStopEquityOrderUnitService.class,
            new WEB3ToStopEquityOrderUnitServiceImpl()); 
        
        //逆指値注文発注サービス 
        Services.registerService(
            WEB3ToStopOrderService.class,
            new WEB3ToStopOrderServiceImpl());
            
        //（連続）株式注文サービス 
        Services.registerService(
            WEB3ToSuccEquityOrderService.class,
            new WEB3ToSuccEquityOrderServiceImpl()); 
            
        //（連続）現物株式買付注文入力サービス 
        Services.registerService(
            WEB3ToSuccEquityOrderBuyInputService.class,
            new WEB3ToSuccEquityOrderBuyInputServiceImpl()); 
            
        //（連続）現物株式売付入力サービス    
        Services.registerService(
            WEB3ToSuccEquitySellOrderInputService.class,
            new WEB3ToSuccEquitySellOrderInputServiceImpl()); 
            
        //（連続）株式注文取消サービス
        Services.registerService(
            WEB3ToSuccEquityCancelOrderService.class,
            new WEB3ToSuccEquityCancelOrderServiceImpl()); 
        
        //（連続）現物株式注文訂正入力サービスインタフェース
        Services.registerService(
            WEB3ToSuccEquityChangeOrderInputService.class,
            new WEB3ToSuccEquityChangeOrderInputServiceImpl()); 
            
        //（連続）株式注文訂正サービス
        Services.registerService(
            WEB3ToSuccEquityChangeOrderService.class,
            new WEB3ToSuccEquityChangeOrderServiceImpl()); 
            
        //（連続）信用取引現引現渡サービス
        Services.registerService(
            WEB3ToSuccMarginSwapMarginService.class, 
            new WEB3ToSuccMarginSwapMarginServiceImpl());

        //（連続）信用取引現引現渡入力サービス
        Services.registerService(
            WEB3ToSuccMarginSwapMarginInputService.class,
            new WEB3ToSuccMarginSwapMarginInputServiceImpl());

        //（連続）信用取引取消サービス
        Services.registerService(
            WEB3ToSuccMarginCancelService.class, 
            new WEB3ToSuccMarginCancelServiceImpl());

        //（連続）信用取引新規建サービス
        Services.registerService(
            WEB3ToSuccMarginOpenMarginService.class, 
            new WEB3ToSuccMarginOpenMarginServiceImpl());

        //（連続）信用取引新規建入力サービス
        Services.registerService(
            WEB3ToSuccMarginOpenMarginInputService.class,
            new WEB3ToSuccMarginOpenMarginInputServiceImpl());

        //（連続）信用取引訂正新規建サービス
        Services.registerService(
            WEB3ToSuccMarginChangeOpenMarginService.class,
            new WEB3ToSuccMarginChangeOpenMarginServiceImpl());

        //（連続）信用取引訂正新規建入力サービス
        Services.registerService(
            WEB3ToSuccMarginChangeOpenMarginInputService.class,
            new WEB3ToSuccMarginChangeOpenMarginInputServiceImpl());

        //（連続）信用取引訂正返済サービス
        Services.registerService(
            WEB3ToSuccMarginChangeCloseMarginService.class,
            new WEB3ToSuccMarginChangeCloseMarginServiceImpl());

        //（連続）信用取引訂正返済入力サービス
        Services.registerService(
            WEB3ToSuccMarginChangeCloseMarginInputService.class,
            new WEB3ToSuccMarginChangeCloseMarginInputServiceImpl());

        //（連続）信用取引返済サービス
        Services.registerService(
            WEB3ToSuccMarginCloseMarginService.class,
            new WEB3ToSuccMarginCloseMarginServiceImpl());

        //（連続）信用取引返済入力サービス
        Services.registerService(
            WEB3ToSuccMarginCloseMarginInputService.class,
            new WEB3ToSuccMarginCloseMarginInputServiceImpl());
            
        //逆指値注文先物OP発注一件サービス
        Services.registerService(
            WEB3ToStopIfoOrderUnitService.class,
            new WEB3ToStopIfoOrderUnitServiceImpl());
            
        //先物OP手動発注UnitService
        Services.registerService(
            WEB3ToIfoManualOrderUnitService.class,
            new WEB3ToIfoManualOrderUnitServiceImpl());
        
        //先物OP手動発注サービス
        Services.registerService(
            WEB3ToIfoManualOrderService.class,
            new WEB3ToIfoManualOrderServiceImpl());
            
        //株式逆指値注文手動発注メインサービス
        Services.registerService(
            WEB3ToStopEquityManualOrderMainService.class,
            new WEB3ToStopEquityManualOrderMainServiceImpl());
        
        //株式連続注文手動発注メインサービス
        Services.registerService(
            WEB3ToSuccEquityManualOrderMainService.class,
            new WEB3ToSuccEquityManualOrderMainServiceImpl());

        //
        //株式逆指値注文手動発注UnitService
        Services.registerService(
                WEB3ToStopEquityManualOrderUnitService.class,
            new WEB3ToStopEquityManualOrderUnitServiceImpl());
        
        //株式連続注文手動発注UnitService
        Services.registerService(
                WEB3ToSuccEquityManualOrderUnitService.class,
            new WEB3ToSuccEquityManualOrderUnitServiceImpl());
        
        //W指値注文切替処理サービス
        Services.registerService(
            WEB3ToWLimitSwitchService.class,
            new WEB3ToWLimitSwitchServiceImpl());

        //W指値注文先物OP切替一件サービス
        Services.registerService(
            WEB3ToWLimitIfoSwitchUnitService.class,
            new WEB3ToWLimitIfoSwitchUnitServiceImpl());

        //W指値注文現物株式切替一件サービス
        Services.registerService(
            WEB3ToWLimitEquitySwitchUnitService.class,
            new WEB3ToWLimitEquitySwitchUnitServiceImpl());

        //W指値注文信用取引返済切替一件サービス
        Services.registerService(
            WEB3ToWLimitMarginCloseMarginSwitchUnitService.class,
            new WEB3ToWLimitMarginCloseMarginSwitchUnitServiceImpl());

        //W指値注文信用取引新規建切替一件サービス
        Services.registerService(
            WEB3ToWLimitMarginOpenMarginSwitchUnitService.class,
            new WEB3ToWLimitMarginOpenMarginSwitchUnitServiceImpl());

        //株式W指値注文手動切替メインサービス
        Services.registerService(
            WEB3ToWLimitEquityManualSwitchMainService.class,
            new WEB3ToWLimitEquityManualSwitchMainServiceImpl());

        //株式W指値注文手動切替UnitService
        Services.registerService(
            WEB3ToWLimitEquityManualSwitchUnitService.class,
            new WEB3ToWLimitEquityManualSwitchUnitServiceImpl());

        //（連続）先物訂正新規建サービス
        Services.registerService(
            WEB3ToSuccFuturesChangeOpenContractService.class,
            new WEB3ToSuccFuturesChangeOpenContractServiceImpl());

        //（連続）先物訂正新規建入力サービス
        Services.registerService(
            WEB3ToSuccFuturesChangeOpenContractInputService.class,
            new WEB3ToSuccFuturesChangeOpenContractInputServiceImpl());

        //（連続）先物訂正返済サービス
        Services.registerService(
            WEB3ToSuccFuturesChangeClosingContractService.class,
            new WEB3ToSuccFuturesChangeClosingContractServiceImpl());

        //（連続）先物取消注文サービス
        Services.registerService(
            WEB3ToSuccFuturesCancelOrderService.class,
            new WEB3ToSuccFuturesCancelOrderServiceImpl());

        //（連続）先物新規建注文サービス
        Services.registerService(
            WEB3ToSuccFuturesOpenContractService.class,
            new WEB3ToSuccFuturesOpenContractServiceImpl());

        //（連続）先物新規建入力サービス
        Services.registerService(
            WEB3ToSuccFuturesOpenContractInputService.class,
            new WEB3ToSuccFuturesOpenContractInputServiceImpl());

        //（連続）先物訂正返済入力サービス
        Services.registerService(
            WEB3ToSuccFuturesChangeClosingContractInputService.class,
            new WEB3ToSuccFuturesChangeClosingContractInputServiceImpl());

        //（連続）先物返済注文サービス
        Services.registerService(
            WEB3ToSuccFuturesSettleContractOrderService.class,
            new WEB3ToSuccFuturesSettleContractOrderServiceImpl());

        //（連続）先物返済入力サービス
        Services.registerService(
            WEB3ToSuccFuturesSettleContractInputService.class,
            new WEB3ToSuccFuturesSettleContractInputServiceImpl());

        //（連続）OP取消注文サービス
        Services.registerService(
            WEB3ToSuccOptionCancelOrderService.class,
            new WEB3ToSuccOptionCancelOrderServiceImpl());

        //（連続）オプション新規建注文サービス
        Services.registerService(
            WEB3ToSuccOptionOpenContractOrderService.class,
            new WEB3ToSuccOptionOpenContractOrderServiceImpl());

        //（連続）OP新規建入力サービス
        Services.registerService(
            WEB3ToSuccOptionOpenContractInputService.class,
            new WEB3ToSuccOptionOpenContractInputServiceImpl());

        //（連続）OP訂正新規建サービス
        Services.registerService(
            WEB3ToSuccOptionChangeOpenContractService.class,
            new WEB3ToSuccOptionChangeOpenContractServiceImpl());

        //（連続）OP訂正新規建入力サービス
        Services.registerService(
            WEB3ToSuccOptionChangeOpenContractInputService.class,
            new WEB3ToSuccOptionChangeOpenContractInputServiceImpl());

        //（連続）OP訂正返済サービス
        Services.registerService(
            WEB3ToSuccOptionChangeClosingContractService.class,
            new WEB3ToSuccOptionChangeClosingContractServiceImpl());

        //（連続）OP訂正返済入力サービス 
        Services.registerService(
            WEB3ToSuccOptionChangeClosingContractInputService.class,
            new WEB3ToSuccOptionChangeClosingContractInputServiceImpl());

        //（連続）OP返済注文サービス
        Services.registerService(
            WEB3ToSuccOptionSettleContractOrderService.class,
            new WEB3ToSuccOptionSettleContractOrderServiceImpl());

        //（連続）OP返済入力サービス
        Services.registerService(
            WEB3ToSuccOptionSettleContractInputService.class,
            new WEB3ToSuccOptionSettleContractInputServiceImpl());

        //OP注文繰越サービス
        Services.registerService(
            WEB3OptionOrderCarryOverService.class,
            new WEB3OptionOrderCarryOverServiceImpl());
        
        //先物OP連続注文繰越サービス
        Services.registerService(
            WEB3ToSuccIfoOrderCarryOverService.class,
            new WEB3ToSuccIfoOrderCarryOverServiceImpl());

        //OP注文繰越一件サービス
        Services.registerService(
            WEB3OptionOrderCarryOverUnitService.class,
            new WEB3OptionOrderCarryOverUnitServiceImpl());

        //先物注文繰越サービス
        Services.registerService(
            WEB3FuturesOrderCarryOverService.class,
            new WEB3FuturesOrderCarryOverServiceImpl());

        //先物注文繰越一件サービス
        Services.registerService(
            WEB3FuturesOrderCarryOverUnitService.class,
            new WEB3FuturesOrderCarryOverUnitServiceImpl());

        //連続注文先物OP発注一件サービス
        Services.registerService(
            WEB3ToSuccIfoOrderUnitService.class,
            new WEB3ToSuccIfoOrderUnitServiceImpl());

        // Service.execute()呼び出し前後に  ----------------------
        // 処理開始時刻と処理終了時刻をログ出力するように設定する

        //設定内容確認サービス
        Services.addInterceptor(
            WEB3ToSuccSettingContentsConfirmService.class,
            new WEB3LogSysTimeInterceptor());
        
        //追加内容選択サービス
        Services.addInterceptor(
            WEB3ToSuccAdditionalContentsSelectService.class,
            new WEB3LogSysTimeInterceptor());            
            
        //連続設定一覧サービス
        Services.addInterceptor(
            WEB3ToSuccSettingListService.class,
            new WEB3LogSysTimeInterceptor());            
            
        //連続注文データ取得サービス
        Services.addInterceptor(
            WEB3ToSuccDataGettingService.class,
            new WEB3LogSysTimeInterceptor());     
        
        //連続注文株式発注一件サービス
        Services.addInterceptor(
            WEB3ToSuccEquityOrderUnitService.class,
            new WEB3LogSysTimeInterceptor());
            
        //連続注文発注サービス
        Services.addInterceptor(
            WEB3ToSuccOrderService.class,
            new WEB3LogSysTimeInterceptor());
            
        //逆指値注文株式発注一件サービス
        Services.addInterceptor(
            WEB3ToStopEquityOrderUnitService.class,
            new WEB3LogSysTimeInterceptor());
            
        //逆指値注文発注サービス 
        Services.addInterceptor(
            WEB3ToStopOrderService.class,
            new WEB3LogSysTimeInterceptor());
            
        //（連続）株式注文サービス
        Services.addInterceptor(
            WEB3ToSuccEquityOrderService.class,
            new WEB3LogSysTimeInterceptor()); 
            
        //（連続）現物株式買付注文入力サービス
        Services.addInterceptor(
            WEB3ToSuccEquityOrderBuyInputService.class,
            new WEB3LogSysTimeInterceptor()); 
            
        //（連続）現物株式売付入力サービス
        Services.addInterceptor(
            WEB3ToSuccEquitySellOrderInputService.class,
            new WEB3LogSysTimeInterceptor());    
            
        //（連続）株式注文取消サービス
        Services.addInterceptor(
            WEB3ToSuccEquityCancelOrderService.class,
            new WEB3LogSysTimeInterceptor()); 
        
        //（連続）現物株式注文訂正入力サービスインタフェース
        Services.addInterceptor(
            WEB3ToSuccEquityChangeOrderInputService.class,
            new WEB3LogSysTimeInterceptor()); 
            
        //（連続）株式注文訂正サービス
        Services.addInterceptor(
            WEB3ToSuccEquityChangeOrderService.class,
            new WEB3LogSysTimeInterceptor()); 
       
        //（連続）信用取引現引現渡サービス
        Services.addInterceptor(
            WEB3ToSuccMarginSwapMarginService.class,
            new WEB3LogSysTimeInterceptor());
        
        //（連続）信用取引現引現渡入力サービス
        Services.addInterceptor(
            WEB3ToSuccMarginSwapMarginInputService.class,
            new WEB3LogSysTimeInterceptor());
        
        //（連続）信用取引取消サービス
        Services.addInterceptor(
            WEB3ToSuccMarginCancelService.class,
            new WEB3LogSysTimeInterceptor());

        //（連続）信用取引新規建サービス
        Services.addInterceptor(
            WEB3ToSuccMarginOpenMarginService.class,
            new WEB3LogSysTimeInterceptor());
        
        //（連続）信用取引新規建入力サービス
        Services.addInterceptor(
            WEB3ToSuccMarginOpenMarginInputService.class,
            new WEB3LogSysTimeInterceptor());
        
        //（連続）信用取引訂正新規建サービス
        Services.addInterceptor(
            WEB3ToSuccMarginChangeOpenMarginService.class,
            new WEB3LogSysTimeInterceptor());
        
        //（連続）信用取引訂正新規建入力サービス
        Services.addInterceptor(
            WEB3ToSuccMarginChangeOpenMarginInputService.class,
            new WEB3LogSysTimeInterceptor());
        
        //（連続）信用取引訂正返済サービス
        Services.addInterceptor(
            WEB3ToSuccMarginChangeCloseMarginService.class,
            new WEB3LogSysTimeInterceptor());
        
        //（連続）信用取引訂正返済入力サービス
        Services.addInterceptor(
            WEB3ToSuccMarginChangeCloseMarginInputService.class,
            new WEB3LogSysTimeInterceptor());
        
        //（連続）信用取引返済サービス
        Services.addInterceptor(
            WEB3ToSuccMarginCloseMarginService.class,
            new WEB3LogSysTimeInterceptor());
        
        //（連続）信用取引返済入力サービス
        Services.addInterceptor(
            WEB3ToSuccMarginCloseMarginInputService.class,
            new WEB3LogSysTimeInterceptor());
            
        //（逆指値注文先物OP発注一件サービス
        Services.addInterceptor(
            WEB3ToStopIfoOrderUnitService.class,
            new WEB3LogSysTimeInterceptor());
       
        //先物OP手動発注サービス
        Services.addInterceptor(
            WEB3ToIfoManualOrderService.class,
            new WEB3LogSysTimeInterceptor());
        
        //先物OP手動発注UnitService
        Services.addInterceptor(
            WEB3ToIfoManualOrderUnitService.class,
            new WEB3LogSysTimeInterceptor());
        
        //株式逆指値注文手動発注UnitService
        Services.addInterceptor(
            WEB3ToStopEquityManualOrderUnitService.class,
            new WEB3LogSysTimeInterceptor());
        
        //株式逆指値注文手動発注メインサービス
        Services.addInterceptor(
            WEB3ToStopEquityManualOrderMainService.class,
            new WEB3LogSysTimeInterceptor());
        
        //株式連続注文手動発注UnitService
        Services.addInterceptor(
            WEB3ToSuccEquityManualOrderUnitService.class,
            new WEB3LogSysTimeInterceptor());
        
        //株式連続注文手動発注メインサービス
        Services.addInterceptor(
            WEB3ToSuccEquityManualOrderMainService.class,
            new WEB3LogSysTimeInterceptor());
        
        //W指値注文切替処理サービス
        Services.addInterceptor(
            WEB3ToWLimitSwitchService.class,
            new WEB3LogSysTimeInterceptor());

        //W指値注文先物OP切替一件サービス
        Services.addInterceptor(
            WEB3ToWLimitIfoSwitchUnitService.class,
            new WEB3LogSysTimeInterceptor());

        //W指値注文現物株式切替一件サービス
        Services.addInterceptor(
            WEB3ToWLimitEquitySwitchUnitService.class,
            new WEB3LogSysTimeInterceptor());

        //W指値注文信用取引返済切替一件サービス
        Services.addInterceptor(
            WEB3ToWLimitMarginCloseMarginSwitchUnitService.class,
            new WEB3LogSysTimeInterceptor());

        //W指値注文信用取引新規建切替一件サービス
        Services.addInterceptor(
            WEB3ToWLimitMarginOpenMarginSwitchUnitService.class,
            new WEB3LogSysTimeInterceptor());

        //株式W指値注文手動切替メインサービス
        Services.addInterceptor(
            WEB3ToWLimitEquityManualSwitchMainService.class,
            new WEB3LogSysTimeInterceptor());

        //株式W指値注文手動切替UnitService
        Services.addInterceptor(
            WEB3ToWLimitEquityManualSwitchUnitService.class,
            new WEB3LogSysTimeInterceptor());
        
        //（連続）先物訂正新規建サービス
        Services.addInterceptor(
            WEB3ToSuccFuturesChangeOpenContractService.class,
            new WEB3LogSysTimeInterceptor());

        //（連続）先物訂正新規建入力サービス
        Services.addInterceptor(
            WEB3ToSuccFuturesChangeOpenContractInputService.class,
            new WEB3LogSysTimeInterceptor());

        //（連続）先物訂正返済サービス
        Services.addInterceptor(
            WEB3ToSuccFuturesChangeClosingContractService.class,
            new WEB3LogSysTimeInterceptor());

        //（連続）先物取消注文サービス
        Services.addInterceptor(
            WEB3ToSuccFuturesCancelOrderService.class,
            new WEB3LogSysTimeInterceptor());

        //（連続）先物新規建注文サービス
        Services.addInterceptor(
            WEB3ToSuccFuturesOpenContractService.class,
            new WEB3LogSysTimeInterceptor());

        //（連続）先物新規建入力サービス
        Services.addInterceptor(
            WEB3ToSuccFuturesOpenContractInputService.class,
            new WEB3LogSysTimeInterceptor());

        //（連続）先物訂正返済入力サービス
        Services.addInterceptor(
            WEB3ToSuccFuturesChangeClosingContractInputService.class,
            new WEB3LogSysTimeInterceptor());

        //（連続）先物返済注文サービス
        Services.addInterceptor(
            WEB3ToSuccFuturesSettleContractOrderService.class,
            new WEB3LogSysTimeInterceptor());

        //（連続）先物返済入力サービス
        Services.addInterceptor(
            WEB3ToSuccFuturesSettleContractInputService.class,
            new WEB3LogSysTimeInterceptor());

        //（連続）OP取消注文サービス
        Services.addInterceptor(
            WEB3ToSuccOptionCancelOrderService.class,
            new WEB3LogSysTimeInterceptor());

        //（連続）オプション新規建注文サービス
        Services.addInterceptor(
            WEB3ToSuccOptionOpenContractOrderService.class,
            new WEB3LogSysTimeInterceptor());

        //（連続）OP新規建入力サービス
        Services.addInterceptor(
            WEB3ToSuccOptionOpenContractInputService.class,
            new WEB3LogSysTimeInterceptor());

        //（連続）OP訂正新規建サービス
        Services.addInterceptor(
            WEB3ToSuccOptionChangeOpenContractService.class,
            new WEB3LogSysTimeInterceptor());

        //（連続）OP訂正新規建入力サービス
        Services.addInterceptor(
            WEB3ToSuccOptionChangeOpenContractInputService.class,
            new WEB3LogSysTimeInterceptor());

        //（連続）OP訂正返済サービス
        Services.addInterceptor(
            WEB3ToSuccOptionChangeClosingContractService.class,
            new WEB3LogSysTimeInterceptor());

        //（連続）OP訂正返済入力サービス 
        Services.addInterceptor(
            WEB3ToSuccOptionChangeClosingContractInputService.class,
            new WEB3LogSysTimeInterceptor());

        //（連続）OP返済注文サービス
        Services.addInterceptor(
            WEB3ToSuccOptionSettleContractOrderService.class,
            new WEB3LogSysTimeInterceptor());

        //（連続）OP返済入力サービス
        Services.addInterceptor(
            WEB3ToSuccOptionSettleContractInputService.class,
            new WEB3LogSysTimeInterceptor());

        //OP注文繰越サービス
        Services.addInterceptor(
            WEB3OptionOrderCarryOverService.class,
            new WEB3LogSysTimeInterceptor());

        //先物OP連続注文繰越サービス
        Services.addInterceptor(
            WEB3ToSuccIfoOrderCarryOverService.class,
            new WEB3LogSysTimeInterceptor());

        //OP注文繰越一件サービス
        Services.addInterceptor(
            WEB3OptionOrderCarryOverUnitService.class,
            new WEB3LogSysTimeInterceptor());
        
        //先物注文繰越サービス
        Services.addInterceptor(
            WEB3FuturesOrderCarryOverService.class,
            new WEB3LogSysTimeInterceptor());

        //先物注文繰越一件サービス
        Services.addInterceptor(
            WEB3FuturesOrderCarryOverUnitService.class,
            new WEB3LogSysTimeInterceptor());

        //Service に ServiceInterceptor を設定する ----------------------
        //設定内容確認サービス
        Services.addInterceptor(
            WEB3ToSuccSettingContentsConfirmService.class,
            new WEB3ToSuccServiceInterceptor());   
            
        //追加内容選択サービス          
        Services.addInterceptor(
            WEB3ToSuccAdditionalContentsSelectService.class,
            new WEB3ToSuccServiceInterceptor());  
             
        //連続設定一覧サービス
        Services.addInterceptor(
            WEB3ToSuccSettingListService.class,
            new WEB3ToSuccServiceInterceptor());            
        
        //連続注文株式発注一件サービス
        Services.addInterceptor(
            WEB3ToSuccEquityOrderUnitService.class,
            new WEB3ToSuccEquityOrderUnitServiceInterceptor());

        //連続注文発注サービス
        Services.addInterceptor(
            WEB3ToSuccOrderService.class,
            new WEB3ToSuccOrderServiceInterceptor());
            
        //逆指値注文株式発注一件サービス
        Services.addInterceptor(
            WEB3ToStopEquityOrderUnitService.class,
            new WEB3ToStopEquityOrderUnitServiceInterceptor());
        
        //逆指値注文発注サービス 
        Services.addInterceptor(
            WEB3ToStopOrderService.class,
            new WEB3ToStopOrderServiceInterceptor());
                
        //（連続）株式注文サービス
        Services.addInterceptor(
            WEB3ToSuccEquityOrderService.class,
            new WEB3EquityOrderServiceInterceptor()); 
            
        //（連続）現物株式買付注文入力サービス
        Services.addInterceptor(
            WEB3ToSuccEquityOrderBuyInputService.class,
            new WEB3EquityOrderBuyInputServiceInterceptor()); 
            
        //（連続）現物株式売付入力サービス
        Services.addInterceptor(
            WEB3ToSuccEquitySellOrderInputService.class,
            new WEB3EquitySellOrderInputServiceInterceptor());
            
        //（連続）株式注文取消サービス
        Services.addInterceptor(
            WEB3ToSuccEquityCancelOrderService.class,
            new WEB3ToSuccEquityCancelOrderServiceInterceptor()); 
        
        //（連続）現物株式注文訂正入力サービスインタフェース
        Services.addInterceptor(
            WEB3ToSuccEquityChangeOrderInputService.class,
            new WEB3EquityChangeOrderInputServiceInterceptor()); 
            
        //（連続）株式注文訂正サービス
        Services.addInterceptor(
            WEB3ToSuccEquityChangeOrderService.class,
            new WEB3ToSuccEquityChangeOrderServiceInterceptor()); 
            
        //（連続）信用取引現引現渡サービス
        Services.addInterceptor(
            WEB3ToSuccMarginSwapMarginService.class,
            new WEB3MarginSwapMarginServiceInterceptor());
        
        //（連続）信用取引現引現渡入力サービス
        Services.addInterceptor(
            WEB3ToSuccMarginSwapMarginInputService.class,
            new WEB3MarginSwapMarginInputServiceInterceptor());
        
        //（連続）信用取引取消サービス
        Services.addInterceptor(
            WEB3ToSuccMarginCancelService.class,
            new WEB3ToSuccMarginCancelServiceInterceptor());

        //（連続）信用取引新規建サービス
        Services.addInterceptor(
            WEB3ToSuccMarginOpenMarginService.class,
            new WEB3MarginOpenMarginServiceInterceptor());
        
        //（連続）信用取引新規建入力サービス
        Services.addInterceptor(
            WEB3ToSuccMarginOpenMarginInputService.class,
            new WEB3MarginOpenMarginInputServiceInterceptor());
        
        //（連続）信用取引訂正新規建サービス
        Services.addInterceptor(
            WEB3ToSuccMarginChangeOpenMarginService.class,
            new WEB3ToSuccMarginChangeOpenMarginServiceInterceptor());
        
        //（連続）信用取引訂正新規建入力サービス
        Services.addInterceptor(
            WEB3ToSuccMarginChangeOpenMarginInputService.class,
            new WEB3MarginChangeOpenMarginInputServiceInterceptor());
        
        //（連続）信用取引訂正返済サービス
        Services.addInterceptor(
            WEB3ToSuccMarginChangeCloseMarginService.class,
            new WEB3ToSuccMarginChangeCloseMarginServiceInterceptor());
        
        //（連続）信用取引訂正返済入力サービス
        Services.addInterceptor(
            WEB3ToSuccMarginChangeCloseMarginInputService.class,
            new WEB3MarginChangeCloseMarginInputServiceInterceptor());
        
        //（連続）信用取引返済サービス
        Services.addInterceptor(
            WEB3ToSuccMarginCloseMarginService.class,
            new WEB3MarginCloseMarginServiceInterceptor());
        
        //（連続）信用取引返済入力サービス
        Services.addInterceptor(
            WEB3ToSuccMarginCloseMarginInputService.class,
            new WEB3MarginCloseMarginInputServiceInterceptor());
        
        //（逆指値注文先物OP発注一件サービス
        Services.addInterceptor(
            WEB3ToStopIfoOrderUnitService.class,
            new WEB3ToStopIfoOrderUnitServiceInterceptor());
            
        //先物OP手動発注UnitService
        Services.addInterceptor(
            WEB3ToIfoManualOrderUnitService.class,
            new WEB3ToIfoManualOrderUnitServiceInterceptor());
        
        //株式連続注文手動発注メインサービス
        Services.addInterceptor(
            WEB3ToSuccEquityManualOrderMainService.class,
            new WEB3ToEquityManualOrderMainServiceInterceptor());
        
        //株式逆指値注文手動発注メインサービス
        Services.addInterceptor(
            WEB3ToStopEquityManualOrderMainService.class,
            new WEB3ToEquityManualOrderMainServiceInterceptor());

        //株式連続注文手動発注UnitService
        Services.addInterceptor(
            WEB3ToSuccEquityManualOrderUnitService.class,
            new WEB3ToEquityManualOrderUnitServiceInterceptor());
        
        //株式逆指値注文手動発注UnitService
        Services.addInterceptor(
            WEB3ToStopEquityManualOrderUnitService.class,
            new WEB3ToEquityManualOrderUnitServiceInterceptor());

        //W指値注文切替処理サービス
        Services.addInterceptor(
            WEB3ToWLimitSwitchService.class,
            new WEB3ToWLimitSwitchServiceInterceptor());

        //W指値注文先物OP切替一件サービス
        Services.addInterceptor(
            WEB3ToWLimitIfoSwitchUnitService.class,
            new WEB3ToWLimitIfoSwitchUnitServiceInterceptor());

        //W指値注文現物株式切替一件サービス
        Services.addInterceptor(
            WEB3ToWLimitEquitySwitchUnitService.class,
            new WEB3ToWLimitEqTypeSwitchUnitServiceInterceptor());

        //W指値注文信用取引返済切替一件サービス
        Services.addInterceptor(
            WEB3ToWLimitMarginCloseMarginSwitchUnitService.class,
            new WEB3ToWLimitEqTypeSwitchUnitServiceInterceptor());

        //W指値注文信用取引新規建切替一件サービス
        Services.addInterceptor(
            WEB3ToWLimitMarginOpenMarginSwitchUnitService.class,
            new WEB3ToWLimitEqTypeSwitchUnitServiceInterceptor());

        //株式W指値注文手動切替メインサービス
        Services.addInterceptor(
            WEB3ToWLimitEquityManualSwitchMainService.class,
            new WEB3ToEquityManualOrderMainServiceInterceptor());

        //株式W指値注文手動切替UnitService
        Services.addInterceptor(
            WEB3ToWLimitEquityManualSwitchUnitService.class,
            new WEB3ToEquityManualOrderUnitServiceInterceptor());
        
        //（連続）先物訂正新規建サービス
        Services.addInterceptor(
            WEB3ToSuccFuturesChangeOpenContractService.class,
            new WEB3ToSuccFuturesChangeOpenContractServiceInterceptor());

        //（連続）先物訂正新規建入力サービス
        Services.addInterceptor(
            WEB3ToSuccFuturesChangeOpenContractInputService.class,
            new WEB3ToSuccFuturesChangeOpenContractInputServiceInterceptor());

        //（連続）先物訂正返済サービス
        Services.addInterceptor(
            WEB3ToSuccFuturesChangeClosingContractService.class,
            new WEB3ToSuccFuturesChangeClosingContractServiceInterceptor());

        //（連続）先物取消注文サービス
        Services.addInterceptor(
            WEB3ToSuccFuturesCancelOrderService.class,
            new WEB3ToSuccFuturesCancelOrderServiceInterceptor());

        //（連続）先物新規建注文サービス
        Services.addInterceptor(
            WEB3ToSuccFuturesOpenContractService.class,
            new WEB3FuturesOpenContractServiceInterceptor());

        //（連続）先物新規建入力サービス
        Services.addInterceptor(
            WEB3ToSuccFuturesOpenContractInputService.class,
            new WEB3FuturesOpenContractInputServiceInterceptor());

        //（連続）先物訂正返済入力サービス
        Services.addInterceptor(
            WEB3ToSuccFuturesChangeClosingContractInputService.class,
            new WEB3ToSuccFuturesChangeClosingContractInputServiceInterceptor());

        //（連続）先物返済注文サービス
        Services.addInterceptor(
            WEB3ToSuccFuturesSettleContractOrderService.class,
            new WEB3FuturesSettleContractOrderServiceInterceptor());

        //（連続）先物返済入力サービス
        Services.addInterceptor(
            WEB3ToSuccFuturesSettleContractInputService.class,
            new WEB3FuturesSettleContractInputServiceInterceptor());
        
        //（連続）OP取消注文サービス
        Services.addInterceptor(
            WEB3ToSuccOptionCancelOrderService.class,
            new WEB3ToSuccOptionCancelOrderServiceInterceptor());

        //（連続）オプション新規建注文サービス
        Services.addInterceptor(
            WEB3ToSuccOptionOpenContractOrderService.class,
            new WEB3OptionOpenContractOrderServiceInterceptor());

        //（連続）OP新規建入力サービス
        Services.addInterceptor(
            WEB3ToSuccOptionOpenContractInputService.class,
            new WEB3OptionOpenContractInputServiceInterceptor());

        //（連続）OP訂正新規建サービス
        Services.addInterceptor(
            WEB3ToSuccOptionChangeOpenContractService.class,
            new WEB3ToSuccOptionChangeOpenContractServiceInterceptor());

        //（連続）OP訂正新規建入力サービス
        Services.addInterceptor(
            WEB3ToSuccOptionChangeOpenContractInputService.class,
            new WEB3ToSuccOptionChangeOpenContractInputServiceInterceptor());

        //（連続）OP訂正返済サービス
        Services.addInterceptor(
            WEB3ToSuccOptionChangeClosingContractService.class,
            new WEB3ToSuccOptionChangeClosingContractServiceInterceptor());

        //（連続）OP訂正返済入力サービス
        Services.addInterceptor(
            WEB3ToSuccOptionChangeClosingContractInputService.class,
            new WEB3ToSuccOptionChangeClosingContractInputServiceInterceptor());

        //（連続）OP返済注文サービス
        Services.addInterceptor(
            WEB3ToSuccOptionSettleContractOrderService.class,
            new WEB3OptionSettleContractOrderServiceInterceptor());

        //（連続）OP返済入力サービス
        Services.addInterceptor(
            WEB3ToSuccOptionSettleContractInputService.class,
            new WEB3OptionSettleContractInputServiceInterceptor());

        //OP注文繰越サービス一件サービス
        Services.addInterceptor(
            WEB3OptionOrderCarryOverUnitService.class,
            new WEB3OptionOrderCarryOverUnitServiceInterceptor());

        //OP注文繰越サービス
        Services.addInterceptor(
            WEB3OptionOrderCarryOverService.class,
            new WEB3IfoOrderCarryOverMainServiceInterceptor());

        //先物注文繰越一件サービス
        Services.addInterceptor(
            WEB3FuturesOrderCarryOverUnitService.class,
            new WEB3FuturesOrderCarryOverUnitServiceInterceptor());
        //先物注文繰越サービス
        Services.addInterceptor(
            WEB3FuturesOrderCarryOverService.class,
            new WEB3IfoOrderCarryOverMainServiceInterceptor());

        //連続注文先物OP発注一件サービス
        Services.addInterceptor(
            WEB3ToSuccIfoOrderUnitService.class,
            new WEB3ToSuccIfoOrderUnitServiceInterceptor());

        // Service の Interceptor 設定処理 ----------------------
        // 自動トランザクション設定
        //設定内容確認サービス
        Services.addInterceptor(
            WEB3ToSuccSettingContentsConfirmService.class,
            new TransactionalInterceptor(TransactionalInterceptor.TX_JOIN_EXISTING));
        
        //追加内容選択サービス
        Services.addInterceptor(
            WEB3ToSuccAdditionalContentsSelectService.class,
            new TransactionalInterceptor(TransactionalInterceptor.TX_JOIN_EXISTING));            
            
        //連続設定一覧サービス
        Services.addInterceptor(
            WEB3ToSuccSettingListService.class,
            new TransactionalInterceptor(TransactionalInterceptor.TX_JOIN_EXISTING));            
            
        //連続注文データ取得サービス
        Services.addInterceptor(
            WEB3ToSuccDataGettingService.class,
            new TransactionalInterceptor(TransactionalInterceptor.TX_JOIN_EXISTING));     
        
        //連続注文株式発注一件サービス
        Services.addInterceptor(
            WEB3ToSuccEquityOrderUnitService.class,
            new TransactionalInterceptor(TransactionalInterceptor.TX_JOIN_EXISTING));
            
        //連続注文発注サービス
        Services.addInterceptor(
            WEB3ToSuccOrderService.class,
            new TransactionalInterceptor(TransactionalInterceptor.TX_CREATE_NEW));
            
        //逆指値注文株式発注一件サービス
        Services.addInterceptor(
            WEB3ToStopEquityOrderUnitService.class,
            new TransactionalInterceptor(TransactionalInterceptor.TX_CREATE_NEW));
            
        //逆指値注文発注サービス 
        Services.addInterceptor(
            WEB3ToStopOrderService.class,
            new TransactionalInterceptor(TransactionalInterceptor.TX_JOIN_EXISTING));
            
        //（連続）株式注文サービス
        Services.addInterceptor(
            WEB3ToSuccEquityOrderService.class,
            new TransactionalInterceptor(TransactionalInterceptor.TX_JOIN_EXISTING)); 
            
        //（連続）現物株式買付注文入力サービス
        Services.addInterceptor(
            WEB3ToSuccEquityOrderBuyInputService.class,
            new TransactionalInterceptor(TransactionalInterceptor.TX_JOIN_EXISTING)); 
            
        //（連続）現物株式売付入力サービス
        Services.addInterceptor(
            WEB3ToSuccEquitySellOrderInputService.class,
            new TransactionalInterceptor(TransactionalInterceptor.TX_JOIN_EXISTING)); 
        
        //（連続）株式注文取消サービス
        Services.addInterceptor(
            WEB3ToSuccEquityCancelOrderService.class,
            new TransactionalInterceptor(TransactionalInterceptor.TX_JOIN_EXISTING)); 
        
        //（連続）現物株式注文訂正入力サービスインタフェース
        Services.addInterceptor(
            WEB3ToSuccEquityChangeOrderInputService.class,
            new TransactionalInterceptor(TransactionalInterceptor.TX_JOIN_EXISTING)); 
            
        //（連続）株式注文訂正サービス
        Services.addInterceptor(
            WEB3ToSuccEquityChangeOrderService.class,
            new TransactionalInterceptor(TransactionalInterceptor.TX_JOIN_EXISTING)); 
                
        //（連続）信用取引現引現渡サービス
        Services.addInterceptor(
            WEB3ToSuccMarginSwapMarginService.class,
            new TransactionalInterceptor(TransactionalInterceptor.TX_JOIN_EXISTING));
        
        //（連続）信用取引現引現渡入力サービス
        Services.addInterceptor(
            WEB3ToSuccMarginSwapMarginInputService.class,
            new TransactionalInterceptor(TransactionalInterceptor.TX_JOIN_EXISTING));
        
        //（連続）信用取引取消サービス
        Services.addInterceptor(
            WEB3ToSuccMarginCancelService.class,
            new TransactionalInterceptor(TransactionalInterceptor.TX_JOIN_EXISTING));

        //（連続）信用取引新規建サービス
        Services.addInterceptor(
            WEB3ToSuccMarginOpenMarginService.class,
            new TransactionalInterceptor(TransactionalInterceptor.TX_JOIN_EXISTING));
        
        //（連続）信用取引新規建入力サービス
        Services.addInterceptor(
            WEB3ToSuccMarginOpenMarginInputService.class,
            new TransactionalInterceptor(TransactionalInterceptor.TX_JOIN_EXISTING));
        
        //（連続）信用取引訂正新規建サービス
        Services.addInterceptor(
            WEB3ToSuccMarginChangeOpenMarginService.class,
            new TransactionalInterceptor(TransactionalInterceptor.TX_JOIN_EXISTING));
        
        //（連続）信用取引訂正新規建入力サービス
        Services.addInterceptor(
            WEB3ToSuccMarginChangeOpenMarginInputService.class,
            new TransactionalInterceptor(TransactionalInterceptor.TX_JOIN_EXISTING));
        
        //（連続）信用取引訂正返済サービス
        Services.addInterceptor(
            WEB3ToSuccMarginChangeCloseMarginService.class,
            new TransactionalInterceptor(TransactionalInterceptor.TX_JOIN_EXISTING));
        
        //（連続）信用取引訂正返済入力サービス
        Services.addInterceptor(
            WEB3ToSuccMarginChangeCloseMarginInputService.class,
            new TransactionalInterceptor(TransactionalInterceptor.TX_JOIN_EXISTING));
        
        //（連続）信用取引返済サービス
        Services.addInterceptor(
            WEB3ToSuccMarginCloseMarginService.class,
            new TransactionalInterceptor(TransactionalInterceptor.TX_JOIN_EXISTING));
        
        //（連続）信用取引返済入力サービス
        Services.addInterceptor(
            WEB3ToSuccMarginCloseMarginInputService.class,
            new TransactionalInterceptor(TransactionalInterceptor.TX_JOIN_EXISTING));

        //（逆指値注文先物OP発注一件サービス
        Services.addInterceptor(
            WEB3ToStopIfoOrderUnitService.class,
            new TransactionalInterceptor(TransactionalInterceptor.TX_CREATE_NEW));
            
        //先物OP手動発注UnitService
        Services.addInterceptor(
            WEB3ToIfoManualOrderUnitService.class,
            new TransactionalInterceptor(TransactionalInterceptor.TX_CREATE_NEW));
        
        //株式連続注文手動発注UnitService
        Services.addInterceptor(
            WEB3ToSuccEquityManualOrderUnitService.class,
            new TransactionalInterceptor(TransactionalInterceptor.TX_CREATE_NEW));
        //株式逆指値注文手動発注UnitService
        Services.addInterceptor(
                WEB3ToStopEquityManualOrderUnitService.class,
                new TransactionalInterceptor(TransactionalInterceptor.TX_CREATE_NEW));
           
        //W指値注文切替処理サービス
        Services.addInterceptor(
            WEB3ToWLimitSwitchService.class,
            new TransactionalInterceptor(TransactionalInterceptor.TX_JOIN_EXISTING));
        
        //W指値注文先物OP切替一件サービス 
        Services.addInterceptor(
            WEB3ToWLimitIfoSwitchUnitService.class,
            new TransactionalInterceptor(TransactionalInterceptor.TX_CREATE_NEW));

        //株式W指値注文手動切替UnitService
        Services.addInterceptor(
            WEB3ToWLimitEquityManualSwitchUnitService.class,
            new TransactionalInterceptor(TransactionalInterceptor.TX_CREATE_NEW));

        //W指値注文現物株式切替一件サービス
        Services.addInterceptor(
            WEB3ToWLimitEquitySwitchUnitService.class,
            new TransactionalInterceptor(TransactionalInterceptor.TX_CREATE_NEW));

        //W指値注文信用取引返済切替一件サービス
        Services.addInterceptor(
            WEB3ToWLimitMarginCloseMarginSwitchUnitService.class,
            new TransactionalInterceptor(TransactionalInterceptor.TX_CREATE_NEW));

        //W指値注文信用取引新規建切替一件サービス
        Services.addInterceptor(
            WEB3ToWLimitMarginOpenMarginSwitchUnitService.class,
            new TransactionalInterceptor(TransactionalInterceptor.TX_CREATE_NEW));

        //（連続）先物訂正新規建サービス
        Services.addInterceptor(
            WEB3ToSuccFuturesChangeOpenContractService.class,
            new TransactionalInterceptor(TransactionalInterceptor.TX_JOIN_EXISTING));

        //（連続）先物訂正新規建入力サービス
        Services.addInterceptor(
            WEB3ToSuccFuturesChangeOpenContractInputService.class,
            new TransactionalInterceptor(TransactionalInterceptor.TX_JOIN_EXISTING));

        //（連続）先物訂正返済サービス
        Services.addInterceptor(
            WEB3ToSuccFuturesChangeClosingContractService.class,
            new TransactionalInterceptor(TransactionalInterceptor.TX_JOIN_EXISTING));

        //（連続）先物取消注文サービス
        Services.addInterceptor(
            WEB3ToSuccFuturesCancelOrderService.class,
            new TransactionalInterceptor(TransactionalInterceptor.TX_JOIN_EXISTING));

        //（連続）先物新規建注文サービス
        Services.addInterceptor(
            WEB3ToSuccFuturesOpenContractService.class,
            new TransactionalInterceptor(TransactionalInterceptor.TX_JOIN_EXISTING));

        //（連続）先物新規建入力サービス
        Services.addInterceptor(
            WEB3ToSuccFuturesOpenContractInputService.class,
            new TransactionalInterceptor(TransactionalInterceptor.TX_JOIN_EXISTING));

        //（連続）先物訂正返済入力サービス
        Services.addInterceptor(
            WEB3ToSuccFuturesChangeClosingContractInputService.class,
            new TransactionalInterceptor(TransactionalInterceptor.TX_JOIN_EXISTING));

        //（連続）先物返済注文サービス
        Services.addInterceptor(
            WEB3ToSuccFuturesSettleContractOrderService.class,
            new TransactionalInterceptor(TransactionalInterceptor.TX_JOIN_EXISTING));

        //（連続）先物返済入力サービス
        Services.addInterceptor(
            WEB3ToSuccFuturesSettleContractInputService.class,
            new TransactionalInterceptor(TransactionalInterceptor.TX_JOIN_EXISTING));

        //（連続）OP取消注文サービス
        Services.addInterceptor(
            WEB3ToSuccOptionCancelOrderService.class,
            new TransactionalInterceptor(TransactionalInterceptor.TX_JOIN_EXISTING));

        //（連続）オプション新規建注文サービス
        Services.addInterceptor(
            WEB3ToSuccOptionOpenContractOrderService.class,
            new TransactionalInterceptor(TransactionalInterceptor.TX_JOIN_EXISTING));

        //（連続）OP新規建入力サービス
        Services.addInterceptor(
            WEB3ToSuccOptionOpenContractInputService.class,
            new TransactionalInterceptor(TransactionalInterceptor.TX_JOIN_EXISTING));

        //（連続）OP訂正新規建サービス
        Services.addInterceptor(
            WEB3ToSuccOptionChangeOpenContractService.class,
            new TransactionalInterceptor(TransactionalInterceptor.TX_JOIN_EXISTING));

        //（連続）OP訂正新規建入力サービス
        Services.addInterceptor(
            WEB3ToSuccOptionChangeOpenContractInputService.class,
            new TransactionalInterceptor(TransactionalInterceptor.TX_JOIN_EXISTING));

        //（連続）OP訂正返済サービス
        Services.addInterceptor(
            WEB3ToSuccOptionChangeClosingContractService.class,
            new TransactionalInterceptor(TransactionalInterceptor.TX_JOIN_EXISTING));

        //（連続）OP訂正返済入力サービス 
        Services.addInterceptor(
            WEB3ToSuccOptionChangeClosingContractInputService.class,
            new TransactionalInterceptor(TransactionalInterceptor.TX_JOIN_EXISTING));

        //（連続）OP返済注文サービス
        Services.addInterceptor(
            WEB3ToSuccOptionSettleContractOrderService.class,
            new TransactionalInterceptor(TransactionalInterceptor.TX_JOIN_EXISTING));

        //（連続）OP返済入力サービス
        Services.addInterceptor(
            WEB3ToSuccOptionSettleContractInputService.class,
            new TransactionalInterceptor(TransactionalInterceptor.TX_JOIN_EXISTING));

        //OP注文繰越サービス
        Services.addInterceptor(
            WEB3OptionOrderCarryOverService.class,
            new TransactionalInterceptor(TransactionalInterceptor.TX_CREATE_NEW));

        //先物OP連続注文繰越サービス
        Services.addInterceptor(
            WEB3ToSuccIfoOrderCarryOverService.class,
            new TransactionalInterceptor(TransactionalInterceptor.TX_JOIN_EXISTING));

        //OP注文繰越一件サービス
        Services.addInterceptor(
            WEB3OptionOrderCarryOverUnitService.class,
            new TransactionalInterceptor(TransactionalInterceptor.TX_JOIN_EXISTING));

        //先物注文繰越サービス
        Services.addInterceptor(
            WEB3FuturesOrderCarryOverService.class,
            new TransactionalInterceptor(TransactionalInterceptor.TX_CREATE_NEW));

        //先物注文繰越一件サービス
        Services.addInterceptor(
            WEB3FuturesOrderCarryOverUnitService.class,
            new TransactionalInterceptor(TransactionalInterceptor.TX_JOIN_EXISTING));

        //連続注文先物OP発注一件サービス
        Services.addInterceptor(
            WEB3ToSuccIfoOrderUnitService.class,
            new TransactionalInterceptor(TransactionalInterceptor.TX_JOIN_EXISTING));

        // MQGatewayInterceptorの設定 ---------------
        //連続注文発注サービス
        Services.addInterceptor(
            WEB3ToSuccOrderService.class,
            new WEB3MQGatewayInterceptor());
            
        //逆指値注文株式発注一件サービス
        Services.addInterceptor(
            WEB3ToStopEquityOrderUnitService.class,
            new WEB3MQGatewayInterceptor());
            
        //逆指値注文先物OP発注一件サービス
        Services.addInterceptor(
            WEB3ToStopIfoOrderUnitService.class,
            new WEB3MQGatewayInterceptor());
            
        //（連続）信用取引現引現渡サービス
        Services.addInterceptor(
            WEB3ToSuccMarginSwapMarginService.class,
            new WEB3MQGatewayInterceptor());
        
        //（連続）信用取引新規建サービス
        Services.addInterceptor(
            WEB3ToSuccMarginOpenMarginService.class,
            new WEB3MQGatewayInterceptor());
        
        //（連続）信用取引訂正新規建サービス
        Services.addInterceptor(
            WEB3ToSuccMarginChangeOpenMarginService.class,
            new WEB3MQGatewayInterceptor());
        
        //（連続）信用取引訂正返済サービス
        Services.addInterceptor(
            WEB3ToSuccMarginChangeCloseMarginService.class,
            new WEB3MQGatewayInterceptor());
        
        //（連続）信用取引返済サービス
        Services.addInterceptor(
            WEB3ToSuccMarginCloseMarginService.class,
            new WEB3MQGatewayInterceptor());

        //　@W指値注文先物OP切替一件サービス
        Services.addInterceptor(
            WEB3ToWLimitIfoSwitchUnitService.class,
            new WEB3MQGatewayInterceptor());

        //W指値注文現物株式切替一件サービス
        Services.addInterceptor(
            WEB3ToWLimitEquitySwitchUnitService.class,
            new WEB3MQGatewayInterceptor());

        //W指値注文信用取引新規建切替一件サービスImpl
        Services.addInterceptor(
            WEB3ToWLimitMarginOpenMarginSwitchUnitService.class,
            new WEB3MQGatewayInterceptor());

        //W指値注文信用取引返済切替一件サービス
        Services.addInterceptor(
            WEB3ToWLimitMarginCloseMarginSwitchUnitService.class,
            new WEB3MQGatewayInterceptor());

        //（連続）先物訂正新規建サービス
        Services.addInterceptor(
            WEB3ToSuccFuturesChangeOpenContractService.class,
            new WEB3MQGatewayInterceptor());

        //（連続）先物訂正新規建入力サービス
        Services.addInterceptor(
            WEB3ToSuccFuturesChangeOpenContractInputService.class,
            new WEB3MQGatewayInterceptor());

        //（連続）先物訂正返済サービス
        Services.addInterceptor(
            WEB3ToSuccFuturesChangeClosingContractService.class,
            new WEB3MQGatewayInterceptor());

        //（連続）先物取消注文サービス
        Services.addInterceptor(
            WEB3ToSuccFuturesCancelOrderService.class,
            new WEB3MQGatewayInterceptor());

        //（連続）先物新規建注文サービス
        Services.addInterceptor(
            WEB3ToSuccFuturesOpenContractService.class,
            new WEB3MQGatewayInterceptor());

        //（連続）先物新規建入力サービス
        Services.addInterceptor(
            WEB3ToSuccFuturesOpenContractInputService.class,
            new WEB3MQGatewayInterceptor());

        //（連続）先物訂正返済入力サービス
        Services.addInterceptor(
            WEB3ToSuccFuturesChangeClosingContractInputService.class,
            new WEB3MQGatewayInterceptor());

        //（連続）先物返済注文サービス
        Services.addInterceptor(
            WEB3ToSuccFuturesSettleContractOrderService.class,
            new WEB3MQGatewayInterceptor());

        //（連続）先物返済入力サービス
        Services.addInterceptor(
            WEB3ToSuccFuturesSettleContractInputService.class,
            new WEB3MQGatewayInterceptor());

        //（連続）OP取消注文サービス
        Services.addInterceptor(
            WEB3ToSuccOptionCancelOrderService.class,
            new WEB3MQGatewayInterceptor());

        //（連続）オプション新規建注文サービス
        Services.addInterceptor(
            WEB3ToSuccOptionOpenContractOrderService.class,
            new WEB3MQGatewayInterceptor());

        //（連続）OP新規建入力サービス
        Services.addInterceptor(
            WEB3ToSuccOptionOpenContractInputService.class,
            new WEB3MQGatewayInterceptor());

        //（連続）OP訂正新規建サービス
        Services.addInterceptor(
            WEB3ToSuccOptionChangeOpenContractService.class,
            new WEB3MQGatewayInterceptor());

        //（連続）OP訂正新規建入力サービス
        Services.addInterceptor(
            WEB3ToSuccOptionChangeOpenContractInputService.class,
            new WEB3MQGatewayInterceptor());

        //（連続）OP訂正返済サービス
        Services.addInterceptor(
            WEB3ToSuccOptionChangeClosingContractService.class,
            new WEB3MQGatewayInterceptor());

        //（連続）OP訂正返済入力サービス 
        Services.addInterceptor(
            WEB3ToSuccOptionChangeClosingContractInputService.class,
            new WEB3MQGatewayInterceptor());

        //（連続）OP返済注文サービス
        Services.addInterceptor(
            WEB3ToSuccOptionSettleContractOrderService.class,
            new WEB3MQGatewayInterceptor());

        //（連続）OP返済入力サービス
        Services.addInterceptor(
            WEB3ToSuccOptionSettleContractInputService.class,
            new WEB3MQGatewayInterceptor());

        //OP注文繰越サービス
        Services.addInterceptor(
            WEB3OptionOrderCarryOverService.class,
            new WEB3MQGatewayInterceptor());
       
        //先物OP連続注文繰越サービス
        Services.addInterceptor(
            WEB3ToSuccIfoOrderCarryOverService.class,
            new WEB3MQGatewayInterceptor());

        //先物注文繰越サービス
        Services.addInterceptor(
            WEB3FuturesOrderCarryOverService.class,
            new WEB3MQGatewayInterceptor());

        //連続注文先物OP発注一件サービス
        Services.addInterceptor(
            WEB3ToSuccIfoOrderUnitService.class,
            new WEB3MQGatewayInterceptor());

        //Message の登録処理 ----------------------
        //連続設定一覧リクエスト
        regClass(WEB3SuccSettingListRequest.class);
        
        //連続設定一覧レスポンス
        regClass(WEB3SuccSettingListResponse.class);
        
        //設定内容確認リクエスト
        regClass(WEB3SuccSettingContentConfirmRequest.class);
        
        //設定内容確認レスポンス
        regClass(WEB3SuccSettingContentConfirmResponse.class);
        
        //追加内容選択リクエスト
        regClass(WEB3SuccAdditionalContentSelectRequest.class);
        
        //追加内容選択レスポンス
        regClass(WEB3SuccAdditionalContentSelectResponse.class);
        
        //（連続）現物株式買付注文入力リクエスト
        regClass(WEB3SuccEquityBuyInputRequest.class);
                 
        //（連続）現物株式買付注文入力レスポンス         
        regClass(WEB3SuccEquityBuyInputResponse.class);
        
        //（連続）現物株式売付注文入力リクエスト         
        regClass(WEB3SuccEquitySellInputRequest.class);
        
        //（連続）現物株式売付注文入力レスポンス         
        regClass(WEB3SuccEquitySellInputResponse.class);
        
        //（連続）現物株式買付注文確認リクエスト         
        regClass(WEB3SuccEquityBuyConfirmRequest.class);
        
        //（連続）現物株式買付注文確認レスポンス         
        regClass(WEB3SuccEquityBuyConfirmResponse.class);
        
        //（連続）現物株式売付注文確認リクエスト         
        regClass(WEB3SuccEquitySellConfirmRequest.class);
        
        //（連続）現物株式売付注文確認レスポンス         
        regClass(WEB3SuccEquitySellConfirmResponse.class);
        
        //（連続）現物株式買付注文完了リクエスト         
        regClass(WEB3SuccEquityBuyCompleteRequest.class);
        
        //（連続）現物株式買付注文完了レスポンス         
        regClass(WEB3SuccEquityBuyCompleteResponse.class);
        
        //（連続）現物株式売付注文完了リクエスト         
        regClass(WEB3SuccEquitySellCompleteRequest.class);
        
        //（連続）現物株式売付注文完了レスポンス
        regClass(WEB3SuccEquitySellCompleteResponse.class);           
        
        //（連続）現物株式注文取消確認リクエスト
        regClass(WEB3SuccEquityCancelConfirmRequest.class);      
        
        //（連続）現物株式注文取消確認レスポンス
        regClass(WEB3SuccEquityCancelConfirmResponse.class);
        
        //（連続）現物株式注文取消完了リクエスト  
        regClass(WEB3SuccEquityCancelCompleteRequest.class);
           
        //（連続）現物株式注文取消完了レスポンス
        regClass(WEB3SuccEquityCancelCompleteResponse.class);

        //（連続）現物株式注文訂正入力リクエスト
        regClass(WEB3SuccEquityChangeInputRequest.class);
        
        //（連続）現物株式注文訂正入力レスポンス
        regClass(WEB3SuccEquityChangeInputResponse.class);
                
        //（連続）現物株式注文訂正確認リクエスト
        regClass(WEB3SuccEquityChangeConfirmRequest.class);
        
        //（連続）現物株式注文訂正確認レスポンス
        regClass(WEB3SuccEquityChangeConfirmResponse.class);
        
        //（連続）現物株式注文訂正完了リクエスト
        regClass(WEB3SuccEquityChangeCompleteRequest.class);
        
        //（連続）現物株式注文訂正完了レスポンス 
        regClass(WEB3SuccEquityChangeCompleteResponse.class);        
        
        //（連続）信用取引新規建注文入力リクエスト
        regClass(WEB3SuccMarginOpenInputRequest.class);
        
        //（連続）信用取引新規建注文入力レスポンス
        regClass(WEB3SuccMarginOpenInputResponse.class);
        
        //（連続）信用取引新規建注文確認リクエスト
        regClass(WEB3SuccMarginOpenConfirmRequest.class);
    
        //（連続）信用取引新規建注文確認レスポンス
        regClass(WEB3SuccMarginOpenConfirmResponse.class);
        
        //（連続）信用取引新規建注文完了リクエスト
        regClass(WEB3SuccMarginOpenCompleteRequest.class);
        
        //（連続）信用取引新規建注文完了レスポンス
        regClass(WEB3SuccMarginOpenCompleteResponse.class);
        
        //（連続）信用取引返済注文入力リクエスト
        regClass(WEB3SuccMarginCloseInputRequest.class);
        
        //（連続）信用取引返済注文入力レスポンス
        regClass(WEB3SuccMarginCloseInputResponse.class);
        
        //（連続）信用取引返済注文確認リクエスト
        regClass(WEB3SuccMarginCloseConfirmRequest.class);
        
        // （連続）信用取引返済注文確認レスポンス
        regClass(WEB3SuccMarginCloseConfirmResponse.class);
        
        //（連続）信用取引返済注文完了リクエスト
        regClass(WEB3SuccMarginCloseCompleteRequest.class);
        
        //（連続）信用取引返済注文完了レスポンス
        regClass(WEB3SuccMarginCloseCompleteResponse.class);
        
        //（連続）信用取引現引現渡注文入力リクエスト
        regClass(WEB3SuccMarginSwapInputRequest.class);
        
        //（連続）信用取引現引現渡注文入力レスポンス
        regClass(WEB3SuccMarginSwapInputResponse.class);
        
        //（連続）信用取引現引現渡注文確認リクエスト
        regClass(WEB3SuccMarginSwapConfirmRequest.class);
        
        //（連続）信用取引現引現渡注文確認レスポンス
        regClass(WEB3SuccMarginSwapConfirmResponse.class);
        
        //（連続）信用取引現引現渡注文完了リクエスト
        regClass(WEB3SuccMarginSwapCompleteRequest.class);
        
        //（連続）信用取引現引現渡注文完了レスポンス
        regClass(WEB3SuccMarginSwapCompleteResponse.class);
        
        //（連続）信用取引訂正新規建入力リクエスト
        regClass(WEB3SuccMarginOpenChangeInputRequest.class);
        
        //（連続）信用取引訂正新規建入力レスポンス
        regClass(WEB3SuccMarginOpenChangeInputResponse.class);
        
        //（連続）信用取引注文訂正新規建確認リクエスト
        regClass(WEB3SuccMarginOpenChangeConfirmRequest.class);
        
        //（連続）信用取引注文訂正新規建確認レスポンス
        regClass(WEB3SuccMarginOpenChangeConfirmResponse.class);
        
        //（連続）信用取引注文訂正新規建完了リクエスト
        regClass(WEB3SuccMarginOpenChangeCompleteRequest.class);
        
        //（連続）信用取引注文訂正新規建完了レスポンス
        regClass(WEB3SuccMarginOpenChangeCompleteResponse.class);
        
        //（連続）信用取引訂正返済入力リクエスト
        regClass(WEB3SuccMarginCloseChangeInputRequest.class);
        
        //（連続）信用取引訂正返済入力レスポンス
        regClass(WEB3SuccMarginCloseChangeInputResponse.class);
        
        //（連続）信用取引注文訂正返済確認リクエスト
        regClass(WEB3SuccMarginCloseChangeConfirmRequest.class);
        
        //（連続）信用取引注文訂正返済確認レスポンス
        regClass(WEB3SuccMarginCloseChangeConfirmResponse.class);
        
        //（連続）信用取引注文訂正返済完了リクエスト
        regClass(WEB3SuccMarginCloseChangeCompleteRequest.class);
        
        //（連続）信用取引注文訂正返済完了レスポンス
        regClass(WEB3SuccMarginCloseChangeCompleteResponse.class);
        
        //（連続）信用取引注文取消確認リクエスト
        regClass(WEB3SuccMarginCancelConfirmRequest.class);
        
        //（連続）信用取引注文取消確認レスポンス
        regClass(WEB3SuccMarginCancelConfirmResponse.class);
        
        //（連続）信用取引注文取消完了リクエスト
        regClass(WEB3SuccMarginCancelCompleteRequest.class);
        
        //（連続）信用取引注文取消完了レスポンス
        regClass(WEB3SuccMarginCancelCompleteResponse.class);

        //先物OP手動発注確認リクエスト
        regClass(WEB3FuturesOptionsManualConfirmRequest.class);
        
        //先物OP手動発注確認レスポンス
        regClass(WEB3FuturesOptionsManualConfirmResponse.class);
        
        //先物OP手動発注完了リクエスト
        regClass(WEB3FuturesOptionsManualCompleteRequest.class);
        
        //先物OP手動発注完了レスポンス
        regClass(WEB3FuturesOptionsManualCompleteResponse.class);
        
        //株式手動発注共通レスポンス
        regClass(WEB3EquityManualCommonResponse.class);
        
        //株式逆指値注文手動発注確認リクエスト
        regClass(WEB3EquityStopManualConfirmRequest.class);
        
        //株式逆指値注文手動発注完了リクエスト
        regClass(WEB3EquityStopManualCompleteRequest.class);
        
        //株式手動発注確認リクエスト
        regClass(WEB3EquityManualConfirmRequest.class);
        
        //株式手動発注確認レスポンス
        regClass(WEB3EquityManualConfirmResponse.class);
        
        //株式手動発注完了リクエスト
        regClass(WEB3EquityManualCompleteRequest.class);
        
        //株式手動発注完了レスポンス
        regClass(WEB3EquityManualCompleteResponse.class);
        
        //株式連続注文手動発注確認リクエスト
        regClass(WEB3EquitySuccManualConfirmRequest.class);
        
        //株式連続注文手動発注完了リクエスト
        regClass(WEB3EquitySuccManualCompleteRequest.class);

        //株式W指値注文手動切替確認リクエスト
        regClass(WEB3EquityWLimitManualConfirmRequest.class);

        //株式W指値注文手動切替完了リクエスト
        regClass(WEB3EquityWLimitManualCompleteRequest.class);

        //（連続）株価指数先物取消注文確認リクエスト
        regClass(WEB3SuccFuturesCancelConfirmRequest.class);

        //（連続）株価指数先物取消注文確認レスポンス
        regClass(WEB3SuccFuturesCancelConfirmResponse.class);

        //（連続）株価指数先物取消注文完了リクエスト
        regClass(WEB3SuccFuturesCancelCompleteRequest.class);

        //（連続）株価指数先物取消注文完了レスポンス
        regClass(WEB3SuccFuturesCancelCompleteResponse.class);

        //（連続）株価指数先物新規建注文確認リクエスト
        regClass(WEB3SuccFuturesOpenConfirmRequest.class);

        //（連続）株価指数先物新規建注文確認レスポンス
        regClass(WEB3SuccFuturesOpenConfirmResponse.class);

        //（連続）株価指数先物新規建注文完了リクエスト
        regClass(WEB3SuccFuturesOpenCompleteRequest.class);

        //（連続）株価指数先物新規建注文完了レスポンス
        regClass(WEB3SuccFuturesOpenCompleteResponse.class);

        //（連続）株価指数先物新規建注文入力画面リクエスト
        regClass(WEB3SuccFuturesOpenInputRequest.class);

        //（連続）株価指数先物新規建注文入力画面レスポンス
        regClass(WEB3SuccFuturesOpenInputResponse.class);

        //（連続）株価指数先物訂正新規建確認リクエスト
        regClass(WEB3SuccFuturesOpenChangeConfirmRequest.class);

        //（連続）株価指数先物訂正新規建確認レスポンス
        regClass(WEB3SuccFuturesOpenChangeConfirmResponse.class);

        //（連続）株価指数先物訂正新規建完了リクエスト
        regClass(WEB3SuccFuturesOpenChangeCompleteRequest.class);

        //（連続）株価指数先物訂正新規建完了レスポンス
        regClass(WEB3SuccFuturesOpenChangeCompleteResponse.class);

        //（連続）株価指数先物訂正新規建入力画面リクエスト
        regClass(WEB3SuccFuturesOpenChangeInputRequest.class);

        //（連続）株価指数先物訂正新規建入力画面レスポンス
        regClass(WEB3SuccFuturesOpenChangeInputResponse.class);

        //（連続）株価指数先物訂正返済確認リクエスト
        regClass(WEB3SuccFuturesCloseChangeInputRequest.class);

        //（連続）株価指数先物訂正返済確認レスポンス
        regClass(WEB3SuccFuturesCloseChangeInputResponse.class);

        //（連続）株価指数先物訂正返済完了リクエスト
        regClass(WEB3SuccFuturesCloseChangeConfirmRequest.class);

        //（連続）株価指数先物訂正返済完了レスポンス
        regClass(WEB3SuccFuturesCloseChangeConfirmResponse.class);

        //（連続）株価指数先物訂正返済入力画面リクエスト
        regClass(WEB3SuccFuturesCloseChangeCompleteRequest.class);

        //（連続）株価指数先物訂正返済入力画面レスポンス
        regClass(WEB3SuccFuturesCloseChangeCompleteResponse.class);

        //（連続）株価指数先物返済注文確認リクエスト
        regClass(WEB3SuccFuturesCloseConfirmRequest.class);

        //（連続）株価指数先物返済注文確認レスポンス
        regClass(WEB3SuccFuturesCloseConfirmResponse.class);

        //（連続）株価指数先物返済注文完了リクエスト
        regClass(WEB3SuccFuturesCloseCompleteRequest.class);

        //（連続）株価指数先物返済注文完了レスポンス
        regClass(WEB3SuccFuturesCloseCompleteResponse.class);

        //（連続）株価指数先物返済入力画面リクエスト
        regClass(WEB3SuccFuturesCloseInputRequest.class);

        //（連続）株価指数先物返済入力画面レスポンス
        regClass(WEB3SuccFuturesCloseInputResponse.class);

        //株価指数OP注文繰越リクエスト
        regClass(WEB3OptionsOrderCarryOverRequest.class);
        //株価指数OP注文繰越レスポンス
        regClass(WEB3OptionsOrderCarryOverResponse.class);

        //株価指数先物注文繰越リクエスト
        regClass(WEB3FuturesOrderCarryOverRequest.class);
        //株価指数先物注文繰越レスポンス
        regClass(WEB3FuturesOrderCarryOverResponse.class);
        
        //（連続）株価指数オプション取消注文確認リクエスト
        regClass(WEB3SuccOptionsCancelConfirmRequest.class);
        //（連続）株価指数オプション取消注文確認レスポンス
        regClass(WEB3SuccOptionsCancelConfirmResponse.class);

        //（連続）株価指数オプション取消注文完了リクエスト
        regClass(WEB3SuccOptionsCancelCompleteRequest.class);
        //（連続）株価指数オプション取消注文完了レスポンス
        regClass(WEB3SuccOptionsCancelCompleteResponse.class);

        //（連続）株価指数オプション新規建注文確認リクエスト
        regClass(WEB3SuccOptionsOpenConfirmRequest.class);
        //（連続）株価指数オプション新規建注文確認レスポンス
        regClass(WEB3SuccOptionsOpenConfirmResponse.class);

        //（連続）株価指数オプション新規建注文完了リクエスト
        regClass(WEB3SuccOptionsOpenCompleteRequest.class);
        //（連続）株価指数オプション新規建注文完了レスポンス
        regClass(WEB3SuccOptionsOpenCompleteResponse.class);

        //（連続）株価指数オプション新規建注文入力画面リクエスト
        regClass(WEB3SuccOptionsOpenInputRequest.class);
        //（連続）株価指数オプション新規建注文入力画面レスポンス
        regClass(WEB3SuccOptionsOpenInputResponse.class);

        //（連続）株価指数オプション訂正新規建確認リクエスト
        regClass(WEB3SuccOptionsOpenChangeConfirmRequest.class);
        //（連続）株価指数オプション訂正新規建確認レスポンス
        regClass(WEB3SuccOptionsOpenChangeConfirmResponse.class);

        //（連続）株価指数オプション訂正新規建完了リクエスト
        regClass(WEB3SuccOptionsOpenChangeCompleteRequest.class);
        //（連続）株価指数オプション訂正新規建完了レスポンス
        regClass(WEB3SuccOptionsOpenChangeCompleteResponse.class);

        //（連続）株価指数オプション訂正新規建入力画面リクエスト
        regClass(WEB3SuccOptionsOpenChangeInputRequest.class);
        //（連続）株価指数オプション訂正新規建入力画面レスポンス
        regClass(WEB3SuccOptionsOpenChangeInputResponse.class);

        //（連続）株価指数オプション訂正返済確認リクエスト
        regClass(WEB3SuccOptionsCloseChangeConfirmRequest.class);
        //（連続）株価指数オプション訂正返済確認レスポンス
        regClass(WEB3SuccOptionsCloseChangeConfirmResponse.class);

        //（連続）株価指数オプション訂正返済完了リクエスト
        regClass(WEB3SuccOptionsCloseChangeCompleteRequest.class);
        //（連続）株価指数オプション訂正返済完了レスポンス
        regClass(WEB3SuccOptionsCloseChangeCompleteResponse.class);

        //（連続）株価指数オプション訂正返済入力画面リクエスト
        regClass(WEB3SuccOptionsCloseChangeInputRequest.class);
        //（連続）株価指数オプション訂正返済入力画面レスポンス
        regClass(WEB3SuccOptionsCloseChangeInputResponse.class);

        //（連続）株価指数オプション返済注文確認リクエスト
        regClass(WEB3SuccOptionsCloseConfirmRequest.class);
        //（連続）株価指数オプション返済注文確認レスポンス
        regClass(WEB3SuccOptionsCloseConfirmResponse.class);

        //（連続）株価指数オプション返済注文完了リクエスト
        regClass(WEB3SuccOptionsCloseCompleteRequest.class);
        //（連続）株価指数オプション返済注文完了レスポンス
        regClass(WEB3SuccOptionsCloseCompleteResponse.class);

        //（連続）株価指数オプション返済入力画面リクエスト
        regClass(WEB3SuccOptionsCloseInputRequest.class);
        //（連続）株価指数オプション返済入力画面レスポンス
        regClass(WEB3SuccOptionsCloseInputResponse.class);

        //Handler の登録処理
        //設定内容確認ハンドラ 
        regHandler(
            WEB3ToSuccAppPlugin.class,
            WEB3SuccSettingContentConfirmRequest.class,
            WEB3ToSuccSettingContentsConfirmHandler.class,
            "getConfirmScreen");
            
        //追加内容選択ハンドラ 
        regHandler(
            WEB3ToSuccAppPlugin.class,
            WEB3SuccAdditionalContentSelectRequest.class,
            WEB3ToSuccAdditionalContentsSelectHandler.class,
            "getSelectScreen");
            
        //連続設定一覧ハンドラ 
        regHandler(
            WEB3ToSuccAppPlugin.class,
            WEB3SuccSettingListRequest.class,
            WEB3ToSuccSettingListHandler.class,
            "getListScreen");
            
        //（連続）現物株式買付注文入力ハンドラ
        regHandler(
            WEB3ToSuccAppPlugin.class,
            WEB3SuccEquityBuyInputRequest.class,
            WEB3ToSuccEquityOrderBuyInputHandler.class,
            "getInputScreen");
            
        //（連続）現物株式売付入力ハンドラ
        regHandler(
            WEB3ToSuccAppPlugin.class,
            WEB3SuccEquitySellInputRequest.class,
            WEB3ToSuccEquitySellOrderInputHandler.class,
            "getInputScreen");
        
        //（連続）株式注文ハンドラ
        regHandler(
            WEB3ToSuccAppPlugin.class,
            WEB3SuccEquityBuyConfirmRequest.class,
            WEB3ToSuccEquityOrderHandler.class,
            "confirmBuyOrder");
            
        regHandler(
            WEB3ToSuccAppPlugin.class,
            WEB3SuccEquityBuyCompleteRequest.class,
            WEB3ToSuccEquityOrderHandler.class,
            "completeBuyOrder");
            
        regHandler(
            WEB3ToSuccAppPlugin.class,
            WEB3SuccEquitySellConfirmRequest.class,
            WEB3ToSuccEquityOrderHandler.class,
            "confirmSellOrder");
            
        regHandler(
            WEB3ToSuccAppPlugin.class,
            WEB3SuccEquitySellCompleteRequest.class,
            WEB3ToSuccEquityOrderHandler.class,
            "completeSellOrder");                
                
        //（連続）株式注文取消ハンドラ
        regHandler(
            WEB3ToSuccAppPlugin.class,
            WEB3SuccEquityCancelConfirmRequest.class,
            WEB3ToSuccEquityCancelOrderHandler.class,
            "confirmCancelOrder"); 
            
        regHandler(
            WEB3ToSuccAppPlugin.class,
            WEB3SuccEquityCancelCompleteRequest.class,
            WEB3ToSuccEquityCancelOrderHandler.class,
            "completeCancelOrder");
        
        //（連続）現物株式注文訂正入力ハンドラ
        regHandler(
            WEB3ToSuccAppPlugin.class,
            WEB3SuccEquityChangeInputRequest.class,
            WEB3ToSuccEquityChangeOrderInputHandler.class,
            "getChangeInputScreen"); 
            
        //（連続）株式注文訂正ハンドラ
        regHandler(
            WEB3ToSuccAppPlugin.class,
            WEB3SuccEquityChangeConfirmRequest.class,
            WEB3ToSuccEquityChangeOrderHandler.class,
            "confirmChangeOrder"); 
            
        regHandler(
            WEB3ToSuccAppPlugin.class,
            WEB3SuccEquityChangeCompleteRequest.class,
            WEB3ToSuccEquityChangeOrderHandler.class,
            "completeChangeOrder"); 

        //（連続）信用取引現引現渡ハンドラ
        regHandler(
            WEB3ToSuccAppPlugin.class,
            WEB3SuccMarginSwapConfirmRequest.class,
            WEB3ToSuccMarginSwapMarginHandler.class,
            "confirmOrder");
             
        regHandler(
            WEB3ToSuccAppPlugin.class,
            WEB3SuccMarginSwapCompleteRequest.class,
            WEB3ToSuccMarginSwapMarginHandler.class,
            "completeOrder");

        //（連続）信用取引現引現渡入力ハンドラ
        regHandler(
            WEB3ToSuccAppPlugin.class,
            WEB3SuccMarginSwapInputRequest.class,
            WEB3ToSuccMarginSwapMarginInputHandler.class,
            "getSwapMarginInputScreen");
        
        //（連続）信用取引取消ハンドラ
        regHandler(
            WEB3ToSuccAppPlugin.class,
            WEB3SuccMarginCancelConfirmRequest.class,
            WEB3ToSuccMarginCancelHandler.class,
            "confirmCancel");
        
        regHandler(
            WEB3ToSuccAppPlugin.class,
            WEB3SuccMarginCancelCompleteRequest.class,
            WEB3ToSuccMarginCancelHandler.class,
            "completeCancel");
        
        //（連続）信用取引新規建ハンドラ
        regHandler(
            WEB3ToSuccAppPlugin.class,
            WEB3SuccMarginOpenConfirmRequest.class,
            WEB3ToSuccMarginOpenMarginHandler.class,
            "confirmOrder");
        
        regHandler(
            WEB3ToSuccAppPlugin.class,
            WEB3SuccMarginOpenCompleteRequest.class,
            WEB3ToSuccMarginOpenMarginHandler.class,
            "completeOrder");
        
        //（連続）信用取引新規建入力ハンドラ
        regHandler(
            WEB3ToSuccAppPlugin.class,
            WEB3SuccMarginOpenInputRequest.class,
            WEB3ToSuccMarginOpenMarginInputHandler.class,
            "getOpenMarginInputScreen");
        
        //（連続）信用取引訂正新規建ハンドラ
        regHandler(
            WEB3ToSuccAppPlugin.class,
            WEB3SuccMarginOpenChangeConfirmRequest.class,
            WEB3ToSuccMarginChangeOpenMarginHandler.class,
            "confirmOpenMarginChange");
        
        regHandler(
            WEB3ToSuccAppPlugin.class,
            WEB3SuccMarginOpenChangeCompleteRequest.class,
            WEB3ToSuccMarginChangeOpenMarginHandler.class,
            "completeOpenMarginChange");
        
        //（連続）信用取引訂正新規建入力ハンドラ
        regHandler(
            WEB3ToSuccAppPlugin.class,
            WEB3SuccMarginOpenChangeInputRequest.class,
            WEB3ToSuccMarginChangeOpenMarginInputHandler.class,
            "getOpenMarginChangeInputScreen");
        
        //（連続）信用取引訂正返済ハンドラ
        regHandler(
            WEB3ToSuccAppPlugin.class,
            WEB3SuccMarginCloseChangeConfirmRequest.class,
            WEB3ToSuccMarginChangeCloseMarginHandler.class,
            "confirmCloseMarginChange");
        
        regHandler(
            WEB3ToSuccAppPlugin.class,
            WEB3SuccMarginCloseChangeCompleteRequest.class,
            WEB3ToSuccMarginChangeCloseMarginHandler.class,
            "completeCloseMarginChange");
        
        //（連続）信用取引訂正返済入力ハンドラ
        regHandler(
            WEB3ToSuccAppPlugin.class,
            WEB3SuccMarginCloseChangeInputRequest.class,
            WEB3ToSuccMarginChangeCloseMarginInputHandler.class,
            "getCloseMarginChangeInputScreen");
        
        //（連続）信用取引返済ハンドラ
        regHandler(
            WEB3ToSuccAppPlugin.class,
            WEB3SuccMarginCloseConfirmRequest.class,
            WEB3ToSuccMarginCloseMarginHandler.class,
            "confirmCloseMargin");

        regHandler(
            WEB3ToSuccAppPlugin.class,
            WEB3SuccMarginCloseCompleteRequest.class,
            WEB3ToSuccMarginCloseMarginHandler.class,
            "completeCloseMargin");
        
        //（連続）信用取引返済入力ハンドラ
        regHandler(
            WEB3ToSuccAppPlugin.class,
            WEB3SuccMarginCloseInputRequest.class,
            WEB3ToSuccMarginCloseMarginInputHandler.class,
            "getCloseMarginChangeInputScreen");
                         
        //先物OP手動発注ハンドラ
        regHandler(
            WEB3ToSuccAppPlugin.class,
            WEB3FuturesOptionsManualConfirmRequest.class,
            WEB3ToIfoManualOrderHandler.class,
            "confirmManual");
        
        regHandler(
            WEB3ToSuccAppPlugin.class,
            WEB3FuturesOptionsManualCompleteRequest.class,
            WEB3ToIfoManualOrderHandler.class,
            "completeManual");
        
        //株式逆指値注文手動発注ハンドラ
        regHandler(
            WEB3ToSuccAppPlugin.class,
            WEB3EquityStopManualConfirmRequest.class,
            WEB3ToStopEquityManualOrderHandler.class,
            "confirmManualOrder");
        
        regHandler(
            WEB3ToSuccAppPlugin.class,
            WEB3EquityStopManualCompleteRequest.class,
            WEB3ToStopEquityManualOrderHandler.class,
            "completeManualOrder");
        
        //株式連続注文手動発注ハンドラ
        regHandler(
            WEB3ToSuccAppPlugin.class,
            WEB3EquitySuccManualConfirmRequest.class,
            WEB3ToSuccEquityManualOrderHandler.class,
            "confirmManualOrder");
        
        regHandler(
            WEB3ToSuccAppPlugin.class,
            WEB3EquitySuccManualCompleteRequest.class,
            WEB3ToSuccEquityManualOrderHandler.class,
            "completeManualOrder");

        //株式W指値注文手動切替ハンドラ
        regHandler(
            WEB3ToSuccAppPlugin.class,
            WEB3EquityWLimitManualConfirmRequest.class,
            WEB3ToWLimitEquityManualSwitchHandler.class,
            "confirmManualOrder");

        //株式W指値注文手動切替ハンドラ
        regHandler(
            WEB3ToSuccAppPlugin.class,
            WEB3EquityWLimitManualCompleteRequest.class,
            WEB3ToWLimitEquityManualSwitchHandler.class,
            "completeManualOrder");

        //（連続）先物訂正新規建ハンドラ
        regHandler(
            WEB3ToSuccAppPlugin.class,
            WEB3SuccFuturesOpenChangeConfirmRequest.class,
            WEB3ToSuccFuturesChangeOpenContractHandler.class,
            "confirmChangeOpenContract");

        //（連続）先物訂正新規建ハンドラ
        regHandler(
            WEB3ToSuccAppPlugin.class,
            WEB3SuccFuturesOpenChangeCompleteRequest.class,
            WEB3ToSuccFuturesChangeOpenContractHandler.class,
            "completeChangeOpenContract");

        //（連続）先物訂正新規建入力ハンドラ
        regHandler(
            WEB3ToSuccAppPlugin.class,
            WEB3SuccFuturesOpenChangeInputRequest.class,
            WEB3ToSuccFuturesChangeOpenContractInputHandler.class,
            "changeOpenContractInputRequest");

        //（連続）先物訂正返済ハンドラ
        regHandler(
            WEB3ToSuccAppPlugin.class,
            WEB3SuccFuturesCloseChangeConfirmRequest.class,
            WEB3ToSuccFuturesChangeClosingContractHandler.class,
            "confirmChangeClosingContract");

        //（連続）先物訂正返済ハンドラ
        regHandler(
            WEB3ToSuccAppPlugin.class,
            WEB3SuccFuturesCloseChangeCompleteRequest.class,
            WEB3ToSuccFuturesChangeClosingContractHandler.class,
            "completeChangeClosingContract");

        //（連続）先物訂正返済入力ハンドラ
        regHandler(
            WEB3ToSuccAppPlugin.class,
            WEB3SuccFuturesCloseChangeInputRequest.class,
            WEB3ToSuccFuturesChangeClosingContractInputHandler.class,
            "closeChangeInputRequest");

        //（連続）先物新規建注文ハンドラ
        regHandler(
            WEB3ToSuccAppPlugin.class,
            WEB3SuccFuturesOpenConfirmRequest.class,
            WEB3ToSuccFuturesOpenContractHandler.class,
            "confirmOrder");

        //（連続）先物新規建注文ハンドラ
        regHandler(
            WEB3ToSuccAppPlugin.class,
            WEB3SuccFuturesOpenCompleteRequest.class,
            WEB3ToSuccFuturesOpenContractHandler.class,
            "completeOrder");

        //（連続）先物新規建入力ハンドラ
        regHandler(
            WEB3ToSuccAppPlugin.class,
            WEB3SuccFuturesOpenInputRequest.class,
            WEB3ToSuccFuturesOpenContractInputHandler.class,
            "openContractInputRequest");

        //（連続）先物返済注文ハンドラ
        regHandler(
            WEB3ToSuccAppPlugin.class,
            WEB3SuccFuturesCloseConfirmRequest.class,
            WEB3ToSuccFuturesSettleContractOrderHandler.class,
            "confirmSettleContract");

        //（連続）先物返済注文ハンドラ
        regHandler(
            WEB3ToSuccAppPlugin.class,
            WEB3SuccFuturesCloseCompleteRequest.class,
            WEB3ToSuccFuturesSettleContractOrderHandler.class,
            "completeSettleContract");

        //（連続）先物返済入力ハンドラ
        regHandler(
            WEB3ToSuccAppPlugin.class,
            WEB3SuccFuturesCloseInputRequest.class,
            WEB3ToSuccFuturesSettleContractInputHandler.class,
            "getSettleContractInputScreen");

        //（連続）先物取消注文ハンドラ
        regHandler(
            WEB3ToSuccAppPlugin.class,
            WEB3SuccFuturesCancelConfirmRequest.class,
            WEB3ToSuccFuturesCancelOrderHandler.class,
            "confirmCancel");

        //（連続）先物取消注文ハンドラ
        regHandler(
            WEB3ToSuccAppPlugin.class,
            WEB3SuccFuturesCancelCompleteRequest.class,
            WEB3ToSuccFuturesCancelOrderHandler.class,
            "completeCancel");
        
        // （連続）OP取消注文ハンドラ 
        regHandler(
            WEB3ToSuccAppPlugin.class,
            WEB3SuccOptionsCancelConfirmRequest.class,
            WEB3ToSuccOptionCancelOrderHandler.class,
            "confirmCancel");

        // （連続）OP取消注文ハンドラ 
        regHandler(
            WEB3ToSuccAppPlugin.class,
            WEB3SuccOptionsCancelCompleteRequest.class,
            WEB3ToSuccOptionCancelOrderHandler.class,
            "completeCancel");

        //（連続）OP新規建注文ハンドラ 
        regHandler(
            WEB3ToSuccAppPlugin.class,
            WEB3SuccOptionsOpenConfirmRequest.class,
            WEB3ToSuccOptionOpenContractOrderHandler.class,
            "confirmOrder");
 
        //（連続）OP新規建注文ハンドラ 
        regHandler(
            WEB3ToSuccAppPlugin.class,
            WEB3SuccOptionsOpenCompleteRequest.class,
            WEB3ToSuccOptionOpenContractOrderHandler.class,
            "completeOrder");

        //（連続）OP新規建入力ハンドラ 
        regHandler(
            WEB3ToSuccAppPlugin.class,
            WEB3SuccOptionsOpenInputRequest.class,
            WEB3ToSuccOptionOpenContractInputHandler.class,
            "openContractInputRequest");

        //（連続）OP訂正新規建ハンドラ
        regHandler(
            WEB3ToSuccAppPlugin.class,
            WEB3SuccOptionsOpenChangeConfirmRequest.class,
            WEB3ToSuccOptionChangeOpenContractHandler.class,
            "confirmChangeOpenContract");

        //（連続）OP訂正新規建ハンドラ
        regHandler(
            WEB3ToSuccAppPlugin.class,
            WEB3SuccOptionsOpenChangeCompleteRequest.class,
            WEB3ToSuccOptionChangeOpenContractHandler.class,
            "completeChangeOpenContract");

        //（連続）OP訂正新規建入力ハンドラ
        regHandler(
            WEB3ToSuccAppPlugin.class,
            WEB3SuccOptionsOpenChangeInputRequest.class,
            WEB3ToSuccOptionChangeOpenContractInputHandler.class,
            "changeOpenContractInputRequest");

        //（連続）OP訂正返済ハンドラ
        regHandler(
            WEB3ToSuccAppPlugin.class,
            WEB3SuccOptionsCloseChangeConfirmRequest.class,
            WEB3ToSuccOptionChangeClosingContractHandler.class,
            "confirmChangeClosingContract");

        //（連続）OP訂正返済ハンドラ
        regHandler(
            WEB3ToSuccAppPlugin.class,
            WEB3SuccOptionsCloseChangeCompleteRequest.class,
            WEB3ToSuccOptionChangeClosingContractHandler.class,
            "completeChangeClosingContract");

        //（連続）OP訂正返済入力ハンドラ
        regHandler(
            WEB3ToSuccAppPlugin.class,
            WEB3SuccOptionsCloseChangeInputRequest.class,
            WEB3ToSuccOptionChangeClosingContractInputHandler.class,
            "changeCloseInputRequest");

        //（連続）OP返済注文ハンドラ
        regHandler(
            WEB3ToSuccAppPlugin.class,
            WEB3SuccOptionsCloseConfirmRequest.class,
            WEB3ToSuccOptionSettleContractHandler.class,
            "confirmSettleContract");

        //（連続）OP返済注文ハンドラ
        regHandler(
            WEB3ToSuccAppPlugin.class,
            WEB3SuccOptionsCloseCompleteRequest.class,
            WEB3ToSuccOptionSettleContractHandler.class,
            "completeSettleContract");

        //（連続）OP返済入力ハンドラ
        regHandler(
            WEB3ToSuccAppPlugin.class,
            WEB3SuccOptionsCloseInputRequest.class,
            WEB3ToSuccOptionSettleContractInputServiceHandler.class,
            "getSettleContractInputScreen");

        //OP注文繰越リクエスト用ハンドラ
        regHandler(
            WEB3IfoAppPlugin.class,
            WEB3OptionsOrderCarryOverRequest.class,
            WEB3OptionOrderCarryOverHandler.class,
            "orderCarryOverRequest");

        //先物注文繰越リクエスト用ハンドラ
        regHandler(
            WEB3IfoAppPlugin.class,
            WEB3FuturesOrderCarryOverRequest.class,
            WEB3FuturesOrderCarryOverHandler.class,
            "orderCarryOverRequest");

        log.exiting(METHOD_NAME);
    }
}
@
