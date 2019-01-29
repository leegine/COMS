head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.16.03.46.28;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	6004d80209d0226;
filename	WEB3XbbdAppPlugin.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : Webbroker3-Xbbd プラグインクラス(WEB3XbbdAppPlugin.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2005/08/23  黄建　@(中訊) 新規作成
Revesion History : 2007/07/13  柴双紅　@(中訊)　@モデルNo.193
Revesion History : 2007/07/17 謝旋 (中訊) 仕様変更・モデル208
Revesion History : 2007/08/03 武波 (中訊) 仕様変更・モデルNo.214,No.217,No.224,No.225,No.226
*/

package webbroker3.bd;

import com.fitechlabs.xtrade.kernel.boot.KernelPlugin;
import com.fitechlabs.xtrade.kernel.boot.Plugin;
import com.fitechlabs.xtrade.kernel.boot.Services;
import com.fitechlabs.xtrade.kernel.interceptor.TransactionalInterceptor;
import com.fitechlabs.xtrade.plugin.tc.gentrade.AlreadyInstalledException;
import com.fitechlabs.xtrade.plugin.tc.gentrade.FinApp;
import com.fitechlabs.xtrade.plugin.tc.gentrade.NotInstalledException;
import com.fitechlabs.xtrade.plugin.tc.gentrade.ProductTypeEnum;
import com.fitechlabs.xtrade.plugin.tc.gentrade.TradingModule;
import com.fitechlabs.xtrade.plugin.tc.xbbd.data.BondAccountDatabaseExtensions;
import com.fitechlabs.xtrade.plugin.tc.xbbd.data.BondMasterDatabaseExtensions;

import webbroker3.bd.data.WEB3BondMasterDatabaseExtensions;
import webbroker3.bd.handler.WEB3AdminBondDomesticProductListHandler;
import webbroker3.bd.handler.WEB3AdminBondDomesticProductRegistHandler;
import webbroker3.bd.handler.WEB3AdminBondDomesticRecruitLimitManageHandler;
import webbroker3.bd.handler.WEB3AdminBondExecuteCancelHandler;
import webbroker3.bd.handler.WEB3AdminBondExecuteChangeHandler;
import webbroker3.bd.handler.WEB3AdminBondOrderAndExecuteHandler;
import webbroker3.bd.handler.WEB3AdminBondOrderLockStatusUpdateHandler;
import webbroker3.bd.handler.WEB3AdminBondOrderReceiveHistoryHandler;
import webbroker3.bd.handler.WEB3AdminBondProductListHandler;
import webbroker3.bd.handler.WEB3AdminBondProductRegisterHandler;
import webbroker3.bd.handler.WEB3BondAutoExecuteHandler;
import webbroker3.bd.handler.WEB3BondBalanceReferenceHandler;
import webbroker3.bd.handler.WEB3BondCancelHandler;
import webbroker3.bd.handler.WEB3BondDomesticApplyHandler;
import webbroker3.bd.handler.WEB3BondDomesticApplyInputHandler;
import webbroker3.bd.handler.WEB3BondDomesticApplyProductListHandler;
import webbroker3.bd.handler.WEB3BondExecuteReferenceHandler;
import webbroker3.bd.handler.WEB3BondRecruitBuyHandler;
import webbroker3.bd.handler.WEB3BondRecruitBuyInputHandler;
import webbroker3.bd.handler.WEB3BondRecruitBuyProductListHandler;
import webbroker3.bd.handler.WEB3BondSellHandler;
import webbroker3.bd.handler.WEB3BondSellInputHandler;
import webbroker3.bd.marketadaptor.WEB3XbbdMarketAdaptorAppPlugin;
import webbroker3.bd.message.WEB3AdminBondDomesticProductListDisplayRequest;
import webbroker3.bd.message.WEB3AdminBondDomesticProductListDisplayResponse;
import webbroker3.bd.message.WEB3AdminBondDomesticProductListSearchDisplayRequest;
import webbroker3.bd.message.WEB3AdminBondDomesticProductListSearchDisplayResponse;
import webbroker3.bd.message.WEB3AdminBondDomesticProductRegistCompleteRequest;
import webbroker3.bd.message.WEB3AdminBondDomesticProductRegistCompleteResponse;
import webbroker3.bd.message.WEB3AdminBondDomesticProductRegistConfirmRequest;
import webbroker3.bd.message.WEB3AdminBondDomesticProductRegistConfirmResponse;
import webbroker3.bd.message.WEB3AdminBondDomesticProductRegistInputRequest;
import webbroker3.bd.message.WEB3AdminBondDomesticProductRegistInputResponse;
import webbroker3.bd.message.WEB3AdminBondDomesticRecruitLimitManageCommonRequest;
import webbroker3.bd.message.WEB3AdminBondDomesticRecruitLimitManageCommonResponse;
import webbroker3.bd.message.WEB3AdminBondDomesticRecruitLimitManageCompleteRequest;
import webbroker3.bd.message.WEB3AdminBondDomesticRecruitLimitManageCompleteResponse;
import webbroker3.bd.message.WEB3AdminBondDomesticRecruitLimitManageConfirmRequest;
import webbroker3.bd.message.WEB3AdminBondDomesticRecruitLimitManageConfirmResponse;
import webbroker3.bd.message.WEB3AdminBondDomesticRecruitLimitManageInputRequest;
import webbroker3.bd.message.WEB3AdminBondDomesticRecruitLimitManageInputResponse;
import webbroker3.bd.message.WEB3AdminBondExecCalculateRequest;
import webbroker3.bd.message.WEB3AdminBondExecCalculateResponse;
import webbroker3.bd.message.WEB3AdminBondExecCancelCompleteRequest;
import webbroker3.bd.message.WEB3AdminBondExecCancelCompleteResponse;
import webbroker3.bd.message.WEB3AdminBondExecCancelConfirmRequest;
import webbroker3.bd.message.WEB3AdminBondExecCancelConfirmResponse;
import webbroker3.bd.message.WEB3AdminBondExecChangeCommonRequest;
import webbroker3.bd.message.WEB3AdminBondExecChangeCompleteRequest;
import webbroker3.bd.message.WEB3AdminBondExecChangeCompleteResponse;
import webbroker3.bd.message.WEB3AdminBondExecChangeConfirmRequest;
import webbroker3.bd.message.WEB3AdminBondExecChangeConfirmResponse;
import webbroker3.bd.message.WEB3AdminBondExecChangeInputRequest;
import webbroker3.bd.message.WEB3AdminBondExecChangeInputResponse;
import webbroker3.bd.message.WEB3AdminBondExecCompleteRequest;
import webbroker3.bd.message.WEB3AdminBondExecCompleteResponse;
import webbroker3.bd.message.WEB3AdminBondExecConfirmRequest;
import webbroker3.bd.message.WEB3AdminBondExecConfirmResponse;
import webbroker3.bd.message.WEB3AdminBondExecInputCommonRequest;
import webbroker3.bd.message.WEB3AdminBondExecInputRequest;
import webbroker3.bd.message.WEB3AdminBondExecInputResponse;
import webbroker3.bd.message.WEB3AdminBondOrderLockUnlockRequest;
import webbroker3.bd.message.WEB3AdminBondOrderLockUnlockResponse;
import webbroker3.bd.message.WEB3AdminBondOrderReceiveHistoryRequest;
import webbroker3.bd.message.WEB3AdminBondOrderReceiveHistoryResponse;
import webbroker3.bd.message.WEB3AdminBondProductRegistCommonRequest;
import webbroker3.bd.message.WEB3AdminBondProductRegistCompleteRequest;
import webbroker3.bd.message.WEB3AdminBondProductRegistCompleteResponse;
import webbroker3.bd.message.WEB3AdminBondProductRegistConfirmRequest;
import webbroker3.bd.message.WEB3AdminBondProductRegistConfirmResponse;
import webbroker3.bd.message.WEB3AdminBondProductRegistInputRequest;
import webbroker3.bd.message.WEB3AdminBondProductRegistInputResponse;
import webbroker3.bd.message.WEB3AdminBondProductSearchInputRequest;
import webbroker3.bd.message.WEB3AdminBondProductSearchInputResponse;
import webbroker3.bd.message.WEB3AdminBondProductSearchListRequest;
import webbroker3.bd.message.WEB3AdminBondProductSearchListResponse;
import webbroker3.bd.message.WEB3BondApplyBuyCommonRequest;
import webbroker3.bd.message.WEB3BondApplyBuyCompleteRequest;
import webbroker3.bd.message.WEB3BondApplyBuyCompleteResponse;
import webbroker3.bd.message.WEB3BondApplyBuyConfirmRequest;
import webbroker3.bd.message.WEB3BondApplyBuyConfirmResponse;
import webbroker3.bd.message.WEB3BondApplyBuyInputRequest;
import webbroker3.bd.message.WEB3BondApplyBuyInputResponse;
import webbroker3.bd.message.WEB3BondApplyBuyProductListRequest;
import webbroker3.bd.message.WEB3BondApplyBuyProductListResponse;
import webbroker3.bd.message.WEB3BondAutoExecRequest;
import webbroker3.bd.message.WEB3BondAutoExecResponse;
import webbroker3.bd.message.WEB3BondBalanceReferenceRequest;
import webbroker3.bd.message.WEB3BondBalanceReferenceResponse;
import webbroker3.bd.message.WEB3BondBalanceReferenceTotalRequest;
import webbroker3.bd.message.WEB3BondBalanceReferenceTotalResponse;
import webbroker3.bd.message.WEB3BondCancelCompleteRequest;
import webbroker3.bd.message.WEB3BondCancelCompleteResponse;
import webbroker3.bd.message.WEB3BondCancelConfirmRequest;
import webbroker3.bd.message.WEB3BondCancelConfirmResponse;
import webbroker3.bd.message.WEB3BondDomesticApplyCompleteRequest;
import webbroker3.bd.message.WEB3BondDomesticApplyCompleteResponse;
import webbroker3.bd.message.WEB3BondDomesticApplyConfirmRequest;
import webbroker3.bd.message.WEB3BondDomesticApplyConfirmResponse;
import webbroker3.bd.message.WEB3BondDomesticApplyInputRequest;
import webbroker3.bd.message.WEB3BondDomesticApplyInputResponse;
import webbroker3.bd.message.WEB3BondDomesticApplyProductListRequest;
import webbroker3.bd.message.WEB3BondDomesticApplyProductListResponse;
import webbroker3.bd.message.WEB3BondExecuteReferenceRequest;
import webbroker3.bd.message.WEB3BondExecuteReferenceResponse;
import webbroker3.bd.message.WEB3BondSellCompleteRequest;
import webbroker3.bd.message.WEB3BondSellCompleteResponse;
import webbroker3.bd.message.WEB3BondSellConfirmRequest;
import webbroker3.bd.message.WEB3BondSellConfirmResponse;
import webbroker3.bd.message.WEB3BondSellInputRequest;
import webbroker3.bd.message.WEB3BondSellInputResponse;
import webbroker3.bd.service.delegate.WEB3AdminBondDomesticProductListService;
import webbroker3.bd.service.delegate.WEB3AdminBondDomesticProductRegistService;
import webbroker3.bd.service.delegate.WEB3AdminBondDomesticRecruitLimitManageService;
import webbroker3.bd.service.delegate.WEB3AdminBondExecuteCancelService;
import webbroker3.bd.service.delegate.WEB3AdminBondExecuteChangeService;
import webbroker3.bd.service.delegate.WEB3AdminBondExecuteNotifyService;
import webbroker3.bd.service.delegate.WEB3AdminBondHelperService;
import webbroker3.bd.service.delegate.WEB3AdminBondOrderAndExecuteService;
import webbroker3.bd.service.delegate.WEB3AdminBondOrderLockStatusUpdateService;
import webbroker3.bd.service.delegate.WEB3AdminBondOrderReceiveHistoryService;
import webbroker3.bd.service.delegate.WEB3AdminBondProductListService;
import webbroker3.bd.service.delegate.WEB3AdminBondProductRegisterService;
import webbroker3.bd.service.delegate.WEB3BondAutoExecuteService;
import webbroker3.bd.service.delegate.WEB3BondAutoExecuteUnitService;
import webbroker3.bd.service.delegate.WEB3BondBalanceReferenceService;
import webbroker3.bd.service.delegate.WEB3BondCancelService;
import webbroker3.bd.service.delegate.WEB3BondDataManagerService;
import webbroker3.bd.service.delegate.WEB3BondDomesticApplyInputService;
import webbroker3.bd.service.delegate.WEB3BondDomesticApplyProductListService;
import webbroker3.bd.service.delegate.WEB3BondDomesticApplyService;
import webbroker3.bd.service.delegate.WEB3BondExecuteReferenceService;
import webbroker3.bd.service.delegate.WEB3BondRecruitBuyInputService;
import webbroker3.bd.service.delegate.WEB3BondRecruitBuyProductListService;
import webbroker3.bd.service.delegate.WEB3BondRecruitBuyService;
import webbroker3.bd.service.delegate.WEB3BondSellInputService;
import webbroker3.bd.service.delegate.WEB3BondSellService;
import webbroker3.bd.service.delegate.stdimpls.WEB3AdminBondDomesticProductListServiceImpl;
import webbroker3.bd.service.delegate.stdimpls.WEB3AdminBondDomesticProductRegistServiceImpl;
import webbroker3.bd.service.delegate.stdimpls.WEB3AdminBondDomesticRecruitLimitManageServiceImpl;
import webbroker3.bd.service.delegate.stdimpls.WEB3AdminBondExecuteCancelServiceImpl;
import webbroker3.bd.service.delegate.stdimpls.WEB3AdminBondExecuteChangeServiceImpl;
import webbroker3.bd.service.delegate.stdimpls.WEB3AdminBondExecuteNotifyServiceImpl;
import webbroker3.bd.service.delegate.stdimpls.WEB3AdminBondHelperServiceImpl;
import webbroker3.bd.service.delegate.stdimpls.WEB3AdminBondOrderAndExecuteServiceImpl;
import webbroker3.bd.service.delegate.stdimpls.WEB3AdminBondOrderLockStatusUpdateServiceImpl;
import webbroker3.bd.service.delegate.stdimpls.WEB3AdminBondOrderReceiveHistoryServiceImpl;
import webbroker3.bd.service.delegate.stdimpls.WEB3AdminBondProductListServiceImpl;
import webbroker3.bd.service.delegate.stdimpls.WEB3AdminBondProductRegisterServiceImpl;
import webbroker3.bd.service.delegate.stdimpls.WEB3BondAutoExecuteServiceImpl;
import webbroker3.bd.service.delegate.stdimpls.WEB3BondAutoExecuteUnitServiceImpl;
import webbroker3.bd.service.delegate.stdimpls.WEB3BondBalanceReferenceServiceImpl;
import webbroker3.bd.service.delegate.stdimpls.WEB3BondCancelServiceImpl;
import webbroker3.bd.service.delegate.stdimpls.WEB3BondDataManagerServiceImpl;
import webbroker3.bd.service.delegate.stdimpls.WEB3BondDomesticApplyInputServiceImpl;
import webbroker3.bd.service.delegate.stdimpls.WEB3BondDomesticApplyProductListServiceImpl;
import webbroker3.bd.service.delegate.stdimpls.WEB3BondDomesticApplyServiceImpl;
import webbroker3.bd.service.delegate.stdimpls.WEB3BondExecuteReferenceServiceImpl;
import webbroker3.bd.service.delegate.stdimpls.WEB3BondRecruitBuyInputServiceImpl;
import webbroker3.bd.service.delegate.stdimpls.WEB3BondRecruitBuyProductListServiceImpl;
import webbroker3.bd.service.delegate.stdimpls.WEB3BondRecruitBuyServiceImpl;
import webbroker3.bd.service.delegate.stdimpls.WEB3BondSellInputServiceImpl;
import webbroker3.bd.service.delegate.stdimpls.WEB3BondSellServiceImpl;
import webbroker3.common.WEB3LogSysTimeInterceptor;
import webbroker3.util.WEB3LogUtility;

/**
 * Webbroker3-Xbbd プラグインクラス。
 *
 * @@author 黄建(中訊)
 * @@version 1.0
 */

public final class WEB3XbbdAppPlugin extends Plugin
{
    /**
     * ログユーティリティ。
     */
    private static WEB3LogUtility log =
        WEB3LogUtility.getInstance(WEB3XbbdAppPlugin.class);

    /**
     * コンストラクタ。
     */
    public WEB3XbbdAppPlugin()
    {

    }

    /**
     * プラグインエントリーポイント。
     */
    public static void plug() throws Exception
    {
        String METHOD_NAME = "plug()";
        log.entering(METHOD_NAME);

        plug(WEB3XbbdAppPlugin.class);

        log.exiting(METHOD_NAME);
    }

    /**
     * プラグイン処理。
     */
    public static void onPlug() throws Exception
    {
        String METHOD_NAME = "onPlug()";
        log.entering(METHOD_NAME);

        // このプラグインより先に読み込む必要のあるプラグインの指定。
        // install the system plugins that we need
        KernelPlugin.plug();

        FinApp l_finApp = (FinApp) Services.getService(FinApp.class);

        // 拡張トランザクション・マネージャーは
        // オーバーライドメソッドが無いため拡張取引モジュールクラスを作成し
        // 拡張取引モジュールクラス内で設定
        try
        {
            l_finApp.uninstallTradingModule("xb-bd-pdbt");
        }
        catch (NotInstalledException l_ex)
        {
            log.info(l_ex.getMessage());
        }

        try
        {
            log.info("Installing TradingModule : web3-xbbd");
            l_finApp.installTradingModule(new WEB3XbbdTradingModule());
            log.info("Installed TradingModule : web3-xbbd");
        }
        catch (AlreadyInstalledException l_ex)
        {
            log.info(l_ex.getMessage());
        }

        TradingModule l_tradingModule = 
            l_finApp.getTradingModule(ProductTypeEnum.BOND);

        //債券計算サービス 
        l_tradingModule.overrideBizLogicProvider(new WEB3BondBizLogicProvider());

        //拡張債券注文マネージャ 
        l_tradingModule.overrideOrderManager(new WEB3BondOrderManager());

        //債券プロダクトマネージャ 
        l_tradingModule.overrideProductManager(new WEB3BondProductManager());

        //債券ポジションマネージャ 
        l_tradingModule.overridePositionManager(new WEB3BondPositionManager());

        //発注審査個別チェック
        WEB3BondOrderManagerReusableValidationsCheck.register();
        
        // Webbroker3-Xbbd-MarketAdaptor プラグイン
        WEB3XbbdMarketAdaptorAppPlugin.plug();
        
        // DatabaseExtensions のプラグイン処理 ----------------------
        BondMasterDatabaseExtensions.plug();
        BondAccountDatabaseExtensions.plug();
        WEB3BondMasterDatabaseExtensions.plug();
        
        // Service の登録処理 ----------------------
        plugServices();

        // Service.execute()呼び出し前後に  ----------------------
        // 処理開始時刻と処理終了時刻をログ出力するように設定する
        plugLogSysTimeInterceptors();
        
        //Service に ServiceInterceptor を設定する ----------------------
        plugServiceInterceptors();

        // Service の Interceptor 設定処理 ----------------------
        // 自動トランザクション設定
        plugTransactionalInterceptors();
        
        // Message の登録処理 ----------------------
        plugMessages();
        
        // Handler の登録処理
        plugHandlers();

        log.exiting(METHOD_NAME);
    }
    
    /**
     * Service の登録処理
     * @@throws Exception
     */
    private static void plugServices() throws Exception
    {
        //==============管理者のサービス=======================
        
        //債券データマネージャーサービス 
        Services.registerService(WEB3BondDataManagerService.class,
            new WEB3BondDataManagerServiceImpl());
        
        //債券管理者ヘルパーサービス
        Services.registerService(WEB3AdminBondHelperService.class,
            new WEB3AdminBondHelperServiceImpl());
        
        //管理者債券約定取消サービス 
        Services.registerService(WEB3AdminBondExecuteCancelService.class,
            new WEB3AdminBondExecuteCancelServiceImpl());
        
        //管理者約定変更サービス
        Services.registerService(WEB3AdminBondExecuteChangeService.class,
            new WEB3AdminBondExecuteChangeServiceImpl());
        
        //管理者新規約定入力サービス
        Services.registerService(WEB3AdminBondOrderAndExecuteService.class,
            new WEB3AdminBondOrderAndExecuteServiceImpl());
        
        //債券管理者注文ロック区分更新サービス 
        Services.registerService(WEB3AdminBondOrderLockStatusUpdateService.class,
            new WEB3AdminBondOrderLockStatusUpdateServiceImpl());
      
        //管理者債券銘柄一覧サービス
        Services.registerService(WEB3AdminBondProductListService.class,
            new WEB3AdminBondProductListServiceImpl());
        
        //管理者債券銘柄登録サービス
        Services.registerService(WEB3AdminBondProductRegisterService.class,
            new WEB3AdminBondProductRegisterServiceImpl());
        
        //債券約定通知サービス 
        Services.registerService(WEB3AdminBondExecuteNotifyService.class,
            new WEB3AdminBondExecuteNotifyServiceImpl());
        
        //債券応募/買付入力サービス
        Services.registerService(WEB3BondRecruitBuyInputService.class,
            new WEB3BondRecruitBuyInputServiceImpl());
        
        //債券応募/買付サービス
        Services.registerService(WEB3BondRecruitBuyService.class,
            new WEB3BondRecruitBuyServiceImpl());
        
        //債券応募/買付銘柄一覧サービス
        Services.registerService(WEB3BondRecruitBuyProductListService.class,
            new WEB3BondRecruitBuyProductListServiceImpl());
        
        //債券売却入力サービス
        Services.registerService(WEB3BondSellInputService.class,
            new WEB3BondSellInputServiceImpl());
        
        //債券売却サービス
        Services.registerService(WEB3BondSellService.class,
            new WEB3BondSellServiceImpl());        
        
        //債券取消サービス
        Services.registerService(WEB3BondCancelService.class,
            new WEB3BondCancelServiceImpl());
        
        //債券注文約定照会サービス
        Services.registerService(WEB3BondExecuteReferenceService.class,
            new WEB3BondExecuteReferenceServiceImpl());
        
        //債券残高照会サービス
        Services.registerService(WEB3BondBalanceReferenceService.class,
            new WEB3BondBalanceReferenceServiceImpl());
        
        //債券自動約定サービス
        Services.registerService(WEB3BondAutoExecuteService.class,
            new WEB3BondAutoExecuteServiceImpl());
        
        //債券自動約定Unitサービス
        Services.registerService(WEB3BondAutoExecuteUnitService.class,
            new WEB3BondAutoExecuteUnitServiceImpl());

        //管理者国内債券銘柄登録サービス
        Services.registerService(WEB3AdminBondDomesticProductRegistService.class,
            new WEB3AdminBondDomesticProductRegistServiceImpl());

        //管理者国内債券銘柄一覧サービス
        Services.registerService(WEB3AdminBondDomesticProductListService.class,
            new WEB3AdminBondDomesticProductListServiceImpl());

        //管理者国内債券部店別応募枠管理サービス
        Services.registerService(WEB3AdminBondDomesticRecruitLimitManageService.class,
            new WEB3AdminBondDomesticRecruitLimitManageServiceImpl());

        //管理者注文受付履歴照会サービス
        Services.registerService(WEB3AdminBondOrderReceiveHistoryService.class,
            new WEB3AdminBondOrderReceiveHistoryServiceImpl());

        //国内債券応募銘柄一覧サービス
        Services.registerService(WEB3BondDomesticApplyProductListService.class,
            new WEB3BondDomesticApplyProductListServiceImpl());

        //国内債券応募入力サービス
        Services.registerService(WEB3BondDomesticApplyInputService.class,
            new WEB3BondDomesticApplyInputServiceImpl());

        //国内債券応募サービス
        Services.registerService(WEB3BondDomesticApplyService.class,
            new WEB3BondDomesticApplyServiceImpl());
    }

    /**
     * Service の Interceptor 設定処理 <BR>
     * 処理開始時刻と処理終了時刻をログ出力するように設定する
     * @@throws Exception
     */
    private static void plugLogSysTimeInterceptors() throws Exception
    {
        //==============管理者のサービス=======================
        
        //債券データマネージャーサービス 
        Services.addInterceptor(WEB3BondDataManagerService.class,
            new WEB3LogSysTimeInterceptor());
        
        //債券管理者ヘルパーサービス
        Services.addInterceptor(WEB3AdminBondHelperService.class,
            new WEB3LogSysTimeInterceptor());
        
        //管理者債券約定取消サービス 
        Services.addInterceptor(WEB3AdminBondExecuteCancelService.class,
            new WEB3LogSysTimeInterceptor());
        
        //管理者約定変更サービス
        Services.addInterceptor(WEB3AdminBondExecuteChangeService.class,
            new WEB3LogSysTimeInterceptor());
        
        //管理者新規約定入力サービス
        Services.addInterceptor(WEB3AdminBondOrderAndExecuteService.class,
            new WEB3LogSysTimeInterceptor());
        
        //債券管理者注文ロック区分更新サービス 
        Services.addInterceptor(WEB3AdminBondOrderLockStatusUpdateService.class,
            new WEB3LogSysTimeInterceptor());
      
        //管理者債券銘柄一覧サービス
        Services.addInterceptor(WEB3AdminBondProductListService.class,
            new WEB3LogSysTimeInterceptor());
        
        //管理者債券銘柄登録サービス
        Services.addInterceptor(WEB3AdminBondProductRegisterService.class,
            new WEB3LogSysTimeInterceptor());
        
        //債券約定通知サービス 
        Services.addInterceptor(WEB3AdminBondExecuteNotifyService.class,
            new WEB3LogSysTimeInterceptor());
        
        //債券応募/買付入力サービス
        Services.addInterceptor(WEB3BondRecruitBuyInputService.class,
            new WEB3LogSysTimeInterceptor());
        
        //債券応募/買付サービス
        Services.addInterceptor(WEB3BondRecruitBuyService.class,
            new WEB3LogSysTimeInterceptor());
        
        //債券応募/買付銘柄一覧サービス
        Services.addInterceptor(WEB3BondRecruitBuyProductListService.class,
            new WEB3LogSysTimeInterceptor());
        
        //債券売却入力サービス
        Services.addInterceptor(WEB3BondSellInputService.class,
            new WEB3LogSysTimeInterceptor());
        
        //債券売却サービス
        Services.addInterceptor(WEB3BondSellService.class,
            new WEB3LogSysTimeInterceptor());
        
        //債券取消サービス
        Services.addInterceptor(WEB3BondCancelService.class,
            new WEB3LogSysTimeInterceptor());
        
        //債券注文約定照会サービス
        Services.addInterceptor(WEB3BondExecuteReferenceService.class,
            new WEB3LogSysTimeInterceptor());
        
        //債券残高照会サービス
        Services.addInterceptor(WEB3BondBalanceReferenceService.class,
            new WEB3LogSysTimeInterceptor());
        
        //債券自動約定サービス
        Services.addInterceptor(WEB3BondAutoExecuteService.class,
            new WEB3LogSysTimeInterceptor());
        
        //債券自動約定Unitサービス 
        Services.addInterceptor(WEB3BondAutoExecuteUnitService.class,
            new WEB3LogSysTimeInterceptor());

        //管理者国内債券銘柄登録サービス
        Services.addInterceptor(WEB3AdminBondDomesticProductRegistService.class,
            new WEB3LogSysTimeInterceptor());

        //管理者国内債券銘柄一覧サービス
        Services.addInterceptor(WEB3AdminBondDomesticProductListService.class,
            new WEB3LogSysTimeInterceptor());

        //管理者国内債券部店別応募枠管理サービス
        Services.addInterceptor(WEB3AdminBondDomesticRecruitLimitManageService.class,
            new WEB3LogSysTimeInterceptor());

        //管理者注文受付履歴照会サービス
        Services.addInterceptor(WEB3AdminBondOrderReceiveHistoryService.class,
            new WEB3LogSysTimeInterceptor());

        //国内債券応募銘柄一覧サービス
        Services.addInterceptor(WEB3BondDomesticApplyProductListService.class,
            new WEB3LogSysTimeInterceptor());

        //国内債券応募入力サービス
        Services.addInterceptor(WEB3BondDomesticApplyInputService.class,
            new WEB3LogSysTimeInterceptor());

        //国内債券応募サービス
        Services.addInterceptor(WEB3BondDomesticApplyService.class,
            new WEB3LogSysTimeInterceptor());
    }

    /**
     * Service の Interceptor 設定処理 <BR>
     * ServiceInterceptor設定
     * @@throws Exception
     */
    private static void plugServiceInterceptors() throws Exception
    {
        //==============管理者のサービス=======================
        
        //管理者債券約定取消サービス 
        Services.addInterceptor(WEB3AdminBondExecuteCancelService.class,
            new WEB3AdminBondExecuteCancelServiceInterceptor());
        
        //管理者約定変更サービス
        Services.addInterceptor(WEB3AdminBondExecuteChangeService.class,
            new WEB3AdminBondExecuteChangeInterceptor());
        
        //管理者新規約定入力サービス
        Services.addInterceptor(WEB3AdminBondOrderAndExecuteService.class,
            new WEB3AdminBondOrderAndExecuteInterceptor());
        
        //債券管理者注文ロック区分更新サービス 
        Services.addInterceptor(WEB3AdminBondOrderLockStatusUpdateService.class,
            new WEB3AdminBondOrderLockStatusUpdateInterceptor());
        
        //管理者債券銘柄登録サービス
        Services.addInterceptor(WEB3AdminBondProductRegisterService.class,
            new WEB3AdminBondProductRegisterInterceptor());
        
        //債券応募/買付入力サービス
        Services.addInterceptor(WEB3BondRecruitBuyInputService.class,
            new WEB3BondRecruitBuyServiceInterceptor());
        
        //債券応募/買付サービス
        Services.addInterceptor(WEB3BondRecruitBuyService.class,
            new WEB3BondRecruitBuyServiceInterceptor());
        
        //債券応募/買付銘柄一覧サービス
        Services.addInterceptor(WEB3BondRecruitBuyProductListService.class,
            new WEB3BondRecruitBuyProductListServiceInterceptor());
        
        //債券売却入力サービス
        Services.addInterceptor(WEB3BondSellInputService.class,
            new WEB3BondSellInputServiceInterceptor());
        
        //債券売却サービス
        Services.addInterceptor(WEB3BondSellService.class,
            new WEB3BondSellServiceInterceptor());
        
        //債券取消サービス
        Services.addInterceptor(WEB3BondCancelService.class,
            new WEB3BondCancelServiceInterceptor());
        
        //債券注文約定照会サービス
        Services.addInterceptor(WEB3BondExecuteReferenceService.class,
            new WEB3BondExecuteReferenceServiceInterceptor());
        
        //債券残高照会サービス
        Services.addInterceptor(WEB3BondBalanceReferenceService.class,
            new WEB3BondBalanceReferenceServiceInterceptor());
        
        //債券自動約定サービス
        Services.addInterceptor(WEB3BondAutoExecuteUnitService.class,
            new WEB3BondDescendRacCtxInterceptor());
        
        //債券自動約定Unitサービス 
        Services.addInterceptor(WEB3BondAutoExecuteUnitService.class,
            new WEB3BondAutoExecuteUnitServiceInterceptor());

        //管理者国内債券銘柄登録サービス
        Services.addInterceptor(WEB3AdminBondDomesticProductRegistService.class,
            new WEB3AdminBondDomesticProductRegistServiceInterceptor());

        //管理者国内債券部店別応募枠管理サービス
        Services.addInterceptor(WEB3AdminBondDomesticRecruitLimitManageService.class,
            new WEB3AdminBondDomesticRecruitLimitManageServiceInterceptor());

        //国内債券応募銘柄一覧サービス
        Services.addInterceptor(WEB3BondDomesticApplyProductListService.class,
            new WEB3BondDomesticApplyProductListServiceInterceptor());

        //国内債券応募入力サービス
        Services.addInterceptor(WEB3BondDomesticApplyInputService.class,
            new WEB3BondDomesticApplyInputServiceInterceptor());

        //国内債券応募サービス
        Services.addInterceptor(WEB3BondDomesticApplyService.class,
            new WEB3BondDomesticApplyServiceInterceptor());
    }

    /**
     * Service の Interceptor 設定処理 <BR>
     * 自動トランザクション設定
     * @@throws Exception
     */
    private static void plugTransactionalInterceptors() throws Exception
    {
        //==============管理者のサービス=======================
        
        //債券データマネージャーサービス 
        Services.addInterceptor(WEB3BondDataManagerService.class,
            new TransactionalInterceptor(TransactionalInterceptor.TX_JOIN_EXISTING));
        
        //債券管理者ヘルパーサービス
        Services.addInterceptor(WEB3AdminBondHelperService.class,
            new TransactionalInterceptor(TransactionalInterceptor.TX_JOIN_EXISTING));
        
        //管理者債券約定取消サービス 
        Services.addInterceptor(WEB3AdminBondExecuteCancelService.class,
            new TransactionalInterceptor(TransactionalInterceptor.TX_JOIN_EXISTING));
        
        //管理者約定変更サービス
        Services.addInterceptor(WEB3AdminBondExecuteChangeService.class,
            new TransactionalInterceptor(TransactionalInterceptor.TX_JOIN_EXISTING));
        
        //管理者新規約定入力サービス
        Services.addInterceptor(WEB3AdminBondOrderAndExecuteService.class,
            new TransactionalInterceptor(TransactionalInterceptor.TX_JOIN_EXISTING));
        
        //債券管理者注文ロック区分更新サービス 
        Services.addInterceptor(WEB3AdminBondOrderLockStatusUpdateService.class,
            new TransactionalInterceptor(TransactionalInterceptor.TX_JOIN_EXISTING));
      
        //管理者債券銘柄一覧サービス
        Services.addInterceptor(WEB3AdminBondProductListService.class,
            new TransactionalInterceptor(TransactionalInterceptor.TX_JOIN_EXISTING));
        
        //管理者債券銘柄登録サービス
        Services.addInterceptor(WEB3AdminBondProductRegisterService.class,
            new TransactionalInterceptor(TransactionalInterceptor.TX_JOIN_EXISTING));
        
        //債券約定通知サービス 
        Services.addInterceptor(WEB3AdminBondExecuteNotifyService.class,
            new TransactionalInterceptor(TransactionalInterceptor.TX_JOIN_EXISTING));
        
        //債券応募/買付入力サービス
        Services.addInterceptor(WEB3BondRecruitBuyInputService.class,
            new TransactionalInterceptor(TransactionalInterceptor.TX_JOIN_EXISTING));
        
        //債券応募/買付サービス
        Services.addInterceptor(WEB3BondRecruitBuyService.class,
            new TransactionalInterceptor(TransactionalInterceptor.TX_JOIN_EXISTING));
        
        //債券応募/買付銘柄一覧サービス
        Services.addInterceptor(WEB3BondRecruitBuyProductListService.class,
            new TransactionalInterceptor(TransactionalInterceptor.TX_JOIN_EXISTING));
        
        //債券売却入力サービス
        Services.addInterceptor(WEB3BondSellInputService.class,
            new TransactionalInterceptor(TransactionalInterceptor.TX_JOIN_EXISTING));
        
        //債券売却サービス
        Services.addInterceptor(WEB3BondSellService.class,
            new TransactionalInterceptor(TransactionalInterceptor.TX_JOIN_EXISTING));
        
        //債券取消サービス
        Services.addInterceptor(WEB3BondCancelService.class,
            new TransactionalInterceptor(TransactionalInterceptor.TX_JOIN_EXISTING));
        
        //債券注文約定照会サービス
        Services.addInterceptor(WEB3BondExecuteReferenceService.class,
            new TransactionalInterceptor(TransactionalInterceptor.TX_JOIN_EXISTING));
        
        //債券残高照会サービス
        Services.addInterceptor(WEB3BondBalanceReferenceService.class,
            new TransactionalInterceptor(TransactionalInterceptor.TX_JOIN_EXISTING));
        
        //債券自動約定サービス
        Services.addInterceptor(WEB3BondAutoExecuteService.class,
            new TransactionalInterceptor(TransactionalInterceptor.TX_CREATE_NEW));
        
        //債券自動約定Unitサービス 
        Services.addInterceptor(WEB3BondAutoExecuteUnitService.class,
            new TransactionalInterceptor(TransactionalInterceptor.TX_JOIN_EXISTING));

        //管理者国内債券銘柄登録サービス
        Services.addInterceptor(WEB3AdminBondDomesticProductRegistService.class,
            new TransactionalInterceptor(TransactionalInterceptor.TX_JOIN_EXISTING));

        //管理者国内債券銘柄一覧サービス
        Services.addInterceptor(WEB3AdminBondDomesticProductListService.class,
            new TransactionalInterceptor(TransactionalInterceptor.TX_JOIN_EXISTING));

        //管理者国内債券部店別応募枠管理サービス
        Services.addInterceptor(WEB3AdminBondDomesticRecruitLimitManageService.class,
            new TransactionalInterceptor(TransactionalInterceptor.TX_JOIN_EXISTING));

        //管理者注文受付履歴照会サービス
        Services.addInterceptor(WEB3AdminBondOrderReceiveHistoryService.class,
            new TransactionalInterceptor(TransactionalInterceptor.TX_JOIN_EXISTING));

        //国内債券応募銘柄一覧サービス
        Services.addInterceptor(WEB3BondDomesticApplyProductListService.class,
            new TransactionalInterceptor(TransactionalInterceptor.TX_JOIN_EXISTING));

        //国内債券応募入力サービス
        Services.addInterceptor(WEB3BondDomesticApplyInputService.class,
            new TransactionalInterceptor(TransactionalInterceptor.TX_JOIN_EXISTING));

        //国内債券応募サービス
        Services.addInterceptor(WEB3BondDomesticApplyService.class,
            new TransactionalInterceptor(TransactionalInterceptor.TX_JOIN_EXISTING));
    }

    /**
     * Message の登録処理
     * @@throws Exception
     */
    private static void plugMessages() throws Exception
    {
        //==============管理者メッセージ=======================
        
        //管理者約定取消確認リクエスト 
        regClass(WEB3AdminBondExecCancelConfirmRequest.class);
        //管理者約定取消確認レスポンス 
        regClass(WEB3AdminBondExecCancelConfirmResponse.class);
        //管理者約定取消完了リクエスト 
        regClass(WEB3AdminBondExecCancelCompleteRequest.class);
        //管理者約定取消完了レスポンス 
        regClass(WEB3AdminBondExecCancelCompleteResponse.class);
        
        //管理者新規約定確認リクエスト 
        regClass(WEB3AdminBondExecConfirmRequest.class);
        //管理者新規約定確認レスポンス 
        regClass(WEB3AdminBondExecConfirmResponse.class);
        //管理者新規約定完了リクエスト 
        regClass(WEB3AdminBondExecCompleteRequest.class);
        //管理者新規約定完了レスポンス 
        regClass(WEB3AdminBondExecCompleteResponse.class);
        //受渡代金計算リクエスト 
        regClass(WEB3AdminBondExecCalculateRequest.class);
        //受渡代金計算レスポンス 
        regClass(WEB3AdminBondExecCalculateResponse.class);
        //管理者新規約定入力リクエスト 
        regClass(WEB3AdminBondExecInputRequest.class);
        //管理者新規約定入力レスポンス 
        regClass(WEB3AdminBondExecInputResponse.class);
        //管理者新規約定入力共通リクエスト 
        regClass(WEB3AdminBondExecInputCommonRequest.class);
        
        //管理者約定変更確認リクエスト 
        regClass(WEB3AdminBondExecChangeConfirmRequest.class);
        //管理者約定変更確認レスポンス 
        regClass(WEB3AdminBondExecChangeConfirmResponse.class);
        //管理者約定変更完了リクエスト 
        regClass(WEB3AdminBondExecChangeCompleteRequest.class);
        //管理者約定変更完了レスポンス 
        regClass(WEB3AdminBondExecChangeCompleteResponse.class);
        //管理者約定変更共通リクエスト 
        regClass(WEB3AdminBondExecChangeCommonRequest.class);
        //管理者約定変更入力リクエスト 
        regClass(WEB3AdminBondExecChangeInputRequest.class);
        //管理者約定変更入力レスポンス 
        regClass(WEB3AdminBondExecChangeInputResponse.class);
        
        //管理者債券注文ロック区分更新リクエスト 
        regClass(WEB3AdminBondOrderLockUnlockRequest.class);
        //管理者債券注文ロック区分更新レスポンス 
        regClass(WEB3AdminBondOrderLockUnlockResponse.class);
        
        //管理者債券銘柄一覧画面表示リクエスト 
        regClass(WEB3AdminBondProductSearchInputRequest.class);
        //管理者債券銘柄一覧画面表示レスポンス 
        regClass(WEB3AdminBondProductSearchInputResponse.class);
        //管理者債券銘柄一覧検索リクエスト 
        regClass(WEB3AdminBondProductSearchListRequest.class);
        //管理者債券銘柄一覧検索レスポンス 
        regClass(WEB3AdminBondProductSearchListResponse.class);
        
        //管理者債券銘柄登録確認リクエスト 
        regClass(WEB3AdminBondProductRegistConfirmRequest.class);
        //管理者債券銘柄登録確認レスポンス 
        regClass(WEB3AdminBondProductRegistConfirmResponse.class);
        //管理者債券銘柄登録完了リクエスト 
        regClass(WEB3AdminBondProductRegistCompleteRequest.class);
        //管理者債券銘柄登録完了レスポンス 
        regClass(WEB3AdminBondProductRegistCompleteResponse.class);
        //管理者債券銘柄登録共通リクエスト 
        regClass(WEB3AdminBondProductRegistCommonRequest.class);
        //管理者債券銘柄登録入力リクエスト 
        regClass(WEB3AdminBondProductRegistInputRequest.class);
        //管理者債券銘柄登録入力レスポンス 
        regClass(WEB3AdminBondProductRegistInputResponse.class);
        
        //債券応募/買付銘柄一覧リクエスト
        regClass(WEB3BondApplyBuyProductListRequest.class);       
        //債券応募/買付銘柄一覧レスポンス
        regClass(WEB3BondApplyBuyProductListResponse.class);
        
        //債券応募/買付共通リクエスト
        regClass(WEB3BondApplyBuyCommonRequest.class);
        //債券応募/買付入力リクエスト
        regClass(WEB3BondApplyBuyInputRequest.class);
        //債券応募/買付入力レスポンス
        regClass(WEB3BondApplyBuyInputResponse.class);
        //債券応募/買付確認リクエスト
        regClass(WEB3BondApplyBuyConfirmRequest.class);        
        //債券応募/買付確認レスポンス
        regClass(WEB3BondApplyBuyConfirmResponse.class);        
        //債券応募/買付完了リクエスト
        regClass(WEB3BondApplyBuyCompleteRequest.class);
        //債券応募/買付完了レスポンス
        regClass(WEB3BondApplyBuyCompleteResponse.class);

        //債券売却入力リクエスト
        regClass(WEB3BondSellInputRequest.class);
        //債券売却入力レスポンス
        regClass(WEB3BondSellInputResponse.class);
        //債券売却確認リクエスト
        regClass(WEB3BondSellConfirmRequest.class);
        //債券売却確認レスポンス
        regClass(WEB3BondSellConfirmResponse.class);
        //債券売却完了リクエスト
        regClass(WEB3BondSellCompleteRequest.class);
        //債券売却完了レスポンス
        regClass(WEB3BondSellCompleteResponse.class);   
        
        //債券取消確認リクエスト
        regClass(WEB3BondCancelConfirmRequest.class); 
        //債券取消確認レスポンス
        regClass(WEB3BondCancelConfirmResponse.class);  
        //債券取消完了リクエスト
        regClass(WEB3BondCancelCompleteRequest.class);  
        //債券取消完了レスポンス
        regClass(WEB3BondCancelCompleteResponse.class);  
        
        //債券注文約定照会リクエスト
        regClass(WEB3BondExecuteReferenceRequest.class);  
        //債券注文約定照会レスポンス
        regClass(WEB3BondExecuteReferenceResponse.class);  
        
        //債券残高照会リクエスト
        regClass(WEB3BondBalanceReferenceRequest.class);  
        //債券残高照会レスポンス
        regClass(WEB3BondBalanceReferenceResponse.class);  

        //債券残高照会残高合計リクエスト
        regClass(WEB3BondBalanceReferenceTotalRequest.class);  
        //債券残高照会残高合計レスポンス
        regClass(WEB3BondBalanceReferenceTotalResponse.class);  

        //債券自動約定リクエスト
        regClass(WEB3BondAutoExecRequest.class);  
        //債券自動約定レスポンス
        regClass(WEB3BondAutoExecResponse.class); 

        //管理者国内債券銘柄登録入力レスポンス
        regClass(WEB3AdminBondDomesticProductRegistInputResponse.class);
        //管理者国内債券銘柄登録入力リクエスト
        regClass(WEB3AdminBondDomesticProductRegistInputRequest.class);

        //管理者国内債券銘柄登録確認レスポンス
        regClass(WEB3AdminBondDomesticProductRegistConfirmResponse.class);
        //管理者国内債券銘柄登録確認リクエスト
        regClass(WEB3AdminBondDomesticProductRegistConfirmRequest.class);

        //管理者国内債券銘柄登録完了レスポンス
        regClass(WEB3AdminBondDomesticProductRegistCompleteResponse.class);
        //管理者国内債券銘柄登録完了リクエスト
        regClass(WEB3AdminBondDomesticProductRegistCompleteRequest.class);

        //管理者国内債券銘柄一覧画面表示レスポンス
        regClass(WEB3AdminBondDomesticProductListDisplayResponse.class);
        //管理者国内債券銘柄一覧画面表示リクエスト
        regClass(WEB3AdminBondDomesticProductListDisplayRequest.class);

        //管理者国内債券銘柄一覧検索画面表示レスポンス
        regClass(WEB3AdminBondDomesticProductListSearchDisplayResponse.class);
        //管理者国内債券銘柄一覧検索画面表示リクエスト
        regClass(WEB3AdminBondDomesticProductListSearchDisplayRequest.class);

        //国内債券応募銘柄一覧リクエスト
        regClass(WEB3BondDomesticApplyProductListRequest.class);
        //国内債券応募銘柄一覧レスポンス
        regClass(WEB3BondDomesticApplyProductListResponse.class);

        //国内債券応募入力リクエスト
        regClass(WEB3BondDomesticApplyInputRequest.class);
        //国内債券応募入力レスポンス
        regClass(WEB3BondDomesticApplyInputResponse.class);

        //国内債券応募確認リクエスト
        regClass(WEB3BondDomesticApplyConfirmRequest.class);
        //国内債券応募確認レスポンス
        regClass(WEB3BondDomesticApplyConfirmResponse.class);

        //国内債券応募完了リクエスト
        regClass(WEB3BondDomesticApplyCompleteRequest.class);
        //国内債券応募完了レスポンス
        regClass(WEB3BondDomesticApplyCompleteResponse.class);

        //管理者国内債券部店別応募枠管理入力リクエスト
        regClass(WEB3AdminBondDomesticRecruitLimitManageInputRequest.class);
        //管理者国内債券部店別応募枠管理入力レスポンス
        regClass(WEB3AdminBondDomesticRecruitLimitManageInputResponse.class);

        //管理者国内債券部店別応募枠管理確認リクエスト
        regClass(WEB3AdminBondDomesticRecruitLimitManageConfirmRequest.class);
        //管理者国内債券部店別応募枠管理確認レスポンス
        regClass(WEB3AdminBondDomesticRecruitLimitManageConfirmResponse.class);

        //管理者国内債券部店別応募枠管理完了リクエスト
        regClass(WEB3AdminBondDomesticRecruitLimitManageCompleteRequest.class);
        //管理者国内債券部店別応募枠管理完了レスポンス
        regClass(WEB3AdminBondDomesticRecruitLimitManageCompleteResponse.class);

        //管理者国内債券部店別応募枠管理共通リクエスト
        regClass(WEB3AdminBondDomesticRecruitLimitManageCommonRequest.class);
        //管理者国内債券部店別応募枠管理共通レスポンス
        regClass(WEB3AdminBondDomesticRecruitLimitManageCommonResponse.class);

        //管理者注文受付履歴照会リクエスト
        regClass(WEB3AdminBondOrderReceiveHistoryRequest.class);
        //管理者注文受付履歴照会レスポンス
        regClass(WEB3AdminBondOrderReceiveHistoryResponse.class);
    }

    /**
     * Handler の登録処理
     * @@throws Exception
     */
    private static void plugHandlers() throws Exception
    {
        //==============管理者メッセージ=======================
        
        //管理者債券約定取消ハンドラ 
        regHandler(
            WEB3XbbdAppPlugin.class,
            WEB3AdminBondExecCancelConfirmRequest.class,
            WEB3AdminBondExecuteCancelHandler.class,
            "confirmExecuteCancel");
        regHandler(
            WEB3XbbdAppPlugin.class,
            WEB3AdminBondExecCancelCompleteRequest.class,
            WEB3AdminBondExecuteCancelHandler.class,
            "completeExecuteCancel");
        
        //管理者約定変更ハンドラ 
        regHandler(
            WEB3XbbdAppPlugin.class,
            WEB3AdminBondExecChangeInputRequest.class,
            WEB3AdminBondExecuteChangeHandler.class,
            "inputExecuteChange");
        regHandler(
            WEB3XbbdAppPlugin.class,
            WEB3AdminBondExecChangeConfirmRequest.class,
            WEB3AdminBondExecuteChangeHandler.class,
            "confirmExecuteChange");
        regHandler(
            WEB3XbbdAppPlugin.class,
            WEB3AdminBondExecChangeCompleteRequest.class,
            WEB3AdminBondExecuteChangeHandler.class,
            "completeExecuteChange");
        
        //管理者新規約定入力ハンドラ 
        regHandler(
            WEB3XbbdAppPlugin.class,
            WEB3AdminBondExecInputRequest.class,
            WEB3AdminBondOrderAndExecuteHandler.class,
            "inputOrderAndExecute");
        regHandler(
            WEB3XbbdAppPlugin.class,
            WEB3AdminBondExecConfirmRequest.class,
            WEB3AdminBondOrderAndExecuteHandler.class,
            "confirmOrderAndExecute");
        regHandler(
            WEB3XbbdAppPlugin.class,
            WEB3AdminBondExecCompleteRequest.class,
            WEB3AdminBondOrderAndExecuteHandler.class,
            "completeOrderAndExecute");
        regHandler(
            WEB3XbbdAppPlugin.class,
            WEB3AdminBondExecCalculateRequest.class,
            WEB3AdminBondOrderAndExecuteHandler.class,
            "calcEstimatedPrice");
        
        //債券管理者注文ロック区分更新ハンドラ
        regHandler(
            WEB3XbbdAppPlugin.class,
            WEB3AdminBondOrderLockUnlockRequest.class,
            WEB3AdminBondOrderLockStatusUpdateHandler.class,
            "updateBondOrderLockStatus");
        
        //債券管理者銘柄一覧ハンドラ 
        regHandler(
            WEB3XbbdAppPlugin.class,
            WEB3AdminBondProductSearchInputRequest.class,
            WEB3AdminBondProductListHandler.class,
            "inputProductList");
        regHandler(
            WEB3XbbdAppPlugin.class,
            WEB3AdminBondProductSearchListRequest.class,
            WEB3AdminBondProductListHandler.class,
            "searchProductList");
        
        //債券管理者銘柄登録ハンドラ
        regHandler(
            WEB3XbbdAppPlugin.class,
            WEB3AdminBondProductRegistInputRequest.class,
            WEB3AdminBondProductRegisterHandler.class,
            "inputProductRegister");
        regHandler(
            WEB3XbbdAppPlugin.class,
            WEB3AdminBondProductRegistConfirmRequest.class,
            WEB3AdminBondProductRegisterHandler.class,
            "confirmProductRegister");
        regHandler(
            WEB3XbbdAppPlugin.class,
            WEB3AdminBondProductRegistCompleteRequest.class,
            WEB3AdminBondProductRegisterHandler.class,
            "completeProductRegister");
        
        //債券応募/買付銘柄一覧ハンドラ
        regHandler(
            WEB3XbbdAppPlugin.class,
            WEB3BondApplyBuyProductListRequest.class,
            WEB3BondRecruitBuyProductListHandler.class,
            "bondRecruitBuyProductList");
        
        //債券応募/買付入力ハンドラ
        regHandler(
            WEB3XbbdAppPlugin.class,
            WEB3BondApplyBuyInputRequest.class,
            WEB3BondRecruitBuyInputHandler.class,
            "inputBondRecruitBuy");
        
        //債券応募/買付ハンドラ
        regHandler(
            WEB3XbbdAppPlugin.class,
            WEB3BondApplyBuyConfirmRequest.class,
            WEB3BondRecruitBuyHandler.class,
            "confirmBondRecruitBuy");
        
        regHandler(
            WEB3XbbdAppPlugin.class,
            WEB3BondApplyBuyCompleteRequest.class,
            WEB3BondRecruitBuyHandler.class,
            "completeBondRecruitBuy");        
        
        //債券売却入力ハンドラ
        regHandler(
            WEB3XbbdAppPlugin.class,
            WEB3BondSellInputRequest.class,
            WEB3BondSellInputHandler.class,
            "inputBondSell");
        
        //債券売却ハンドラ
        regHandler(
            WEB3XbbdAppPlugin.class,
            WEB3BondSellConfirmRequest.class,
            WEB3BondSellHandler.class,
            "confirmBondSell");
        
        regHandler(
            WEB3XbbdAppPlugin.class,
            WEB3BondSellCompleteRequest.class,
            WEB3BondSellHandler.class,
            "completeBondSell");        
        
        //債券取消サービス
        regHandler(
            WEB3XbbdAppPlugin.class,
            WEB3BondCancelConfirmRequest.class,
            WEB3BondCancelHandler.class,
            "confirmCancel");
        
        regHandler(
            WEB3XbbdAppPlugin.class,
            WEB3BondCancelCompleteRequest.class,
            WEB3BondCancelHandler.class,
            "completeCancel");    
        
        //債券注文約定照会サービス
        regHandler(
            WEB3XbbdAppPlugin.class,
            WEB3BondExecuteReferenceRequest.class,
            WEB3BondExecuteReferenceHandler.class,
            "getExecuteReference");
        
        //債券残高照会サービス
        regHandler(
            WEB3XbbdAppPlugin.class,
            WEB3BondBalanceReferenceRequest.class,
            WEB3BondBalanceReferenceHandler.class,
            "getBalanceReference");

        regHandler(
            WEB3XbbdAppPlugin.class,
            WEB3BondBalanceReferenceTotalRequest.class,
            WEB3BondBalanceReferenceHandler.class,
            "getBalanceTotal");

        //債券自動約定サービス
        regHandler(
            WEB3XbbdAppPlugin.class,
            WEB3BondAutoExecRequest.class,
            WEB3BondAutoExecuteHandler.class,
            "completeAutoExecute");

        //管理者国内債券銘柄登録ハンドラ
        regHandler(
            WEB3XbbdAppPlugin.class,
            WEB3AdminBondDomesticProductRegistInputRequest.class,
            WEB3AdminBondDomesticProductRegistHandler.class,
            "inputProductRegist");

        regHandler(
            WEB3XbbdAppPlugin.class,
            WEB3AdminBondDomesticProductRegistConfirmRequest.class,
            WEB3AdminBondDomesticProductRegistHandler.class,
            "validateProductRegist");

        regHandler(
            WEB3XbbdAppPlugin.class,
            WEB3AdminBondDomesticProductRegistCompleteRequest.class,
            WEB3AdminBondDomesticProductRegistHandler.class,
            "submitProductRegist");

        //管理者国内債券銘柄一覧ハンドラ
        regHandler(
            WEB3XbbdAppPlugin.class,
            WEB3AdminBondDomesticProductListDisplayRequest.class,
            WEB3AdminBondDomesticProductListHandler.class,
            "getProductListScreenDisplay");

        regHandler(
            WEB3XbbdAppPlugin.class,
            WEB3AdminBondDomesticProductListSearchDisplayRequest.class,
            WEB3AdminBondDomesticProductListHandler.class,
            "getSearchScreenDisplay");

        //管理者国内債券部店別応募枠管理ハンドラ
        regHandler(
            WEB3XbbdAppPlugin.class,
            WEB3AdminBondDomesticRecruitLimitManageInputRequest.class,
            WEB3AdminBondDomesticRecruitLimitManageHandler.class,
            "inputRecruitLimitManage");

        regHandler(
            WEB3XbbdAppPlugin.class,
            WEB3AdminBondDomesticRecruitLimitManageConfirmRequest.class,
            WEB3AdminBondDomesticRecruitLimitManageHandler.class,
            "validateRecruitLimitManage");

        regHandler(
            WEB3XbbdAppPlugin.class,
            WEB3AdminBondDomesticRecruitLimitManageCompleteRequest.class,
            WEB3AdminBondDomesticRecruitLimitManageHandler.class,
            "submitRecruitLimitManage");

        //管理者注文受付履歴照会ハンドラ
        regHandler(
            WEB3XbbdAppPlugin.class,
            WEB3AdminBondOrderReceiveHistoryRequest.class,
            WEB3AdminBondOrderReceiveHistoryHandler.class,
            "orderReceiveHistory");

        //国内債券応募銘柄一覧ハンドラ
        regHandler(
            WEB3XbbdAppPlugin.class,
            WEB3BondDomesticApplyProductListRequest.class,
            WEB3BondDomesticApplyProductListHandler.class,
            "bondDomesticApplyProductList");

        //国内債券応募入力ハンドラ
        regHandler(
            WEB3XbbdAppPlugin.class,
            WEB3BondDomesticApplyInputRequest.class,
            WEB3BondDomesticApplyInputHandler.class,
            "bondDomesticApplyInput");

        //国内債券応募ハンドラ
        regHandler(
            WEB3XbbdAppPlugin.class,
            WEB3BondDomesticApplyConfirmRequest.class,
            WEB3BondDomesticApplyHandler.class,
            "bondDomesticApplyConfirm");

        regHandler(
            WEB3XbbdAppPlugin.class,
            WEB3BondDomesticApplyCompleteRequest.class,
            WEB3BondDomesticApplyHandler.class,
            "bondDomesticApplyComplete");
    }
}@
