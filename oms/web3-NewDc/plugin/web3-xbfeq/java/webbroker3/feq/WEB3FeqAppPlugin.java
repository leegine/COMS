head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.16.03.46.05;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	6004d80209d0226;
filename	WEB3FeqAppPlugin.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研ビジネス・イノベーション
File Name        : Webbroker3-feq プラグインクラス(WEB3FeqAppPlugin.java)
Author Name      : Daiwa Institute of Research Business Innovation
Revesion History : 2005/07/19 黄建 (中訊) 新規作成      
                 　@2006/09/18  黄建(中訊) 仕様変更・モデル247-255   
Revesion History : 2008/01/17 柴双紅(中訊) 仕様変更・モデル373、377
Revesion History : 2009/08/03 武　@波(中訊) 仕様変更・モデル501
Revesion History : 2010/09/08 張騰宇(中訊) 仕様変更・モデル549
*/

package webbroker3.feq;

import java.util.List;

import com.fitechlabs.xtrade.kernel.boot.KernelPlugin;
import com.fitechlabs.xtrade.kernel.boot.Plugin;
import com.fitechlabs.xtrade.kernel.boot.Services;
import com.fitechlabs.xtrade.kernel.data.Processors;
import com.fitechlabs.xtrade.kernel.data.QueryProcessor;
import com.fitechlabs.xtrade.kernel.interceptor.TransactionalInterceptor;
import com.fitechlabs.xtrade.kernel.util.ThreadLocalSystemAttributesRegistry;
import com.fitechlabs.xtrade.plugin.tc.gentrade.AlreadyInstalledException;
import com.fitechlabs.xtrade.plugin.tc.gentrade.FinApp;
import com.fitechlabs.xtrade.plugin.tc.gentrade.GtlUtils;
import com.fitechlabs.xtrade.plugin.tc.gentrade.NotInstalledException;
import com.fitechlabs.xtrade.plugin.tc.gentrade.ProductTypeEnum;
import com.fitechlabs.xtrade.plugin.tc.gentrade.TradingModule;
import com.fitechlabs.xtrade.plugin.tc.gentrade.data.BranchRow;
import com.fitechlabs.xtrade.plugin.tc.gentrade.data.InstitutionRow;
import com.fitechlabs.xtrade.plugin.tc.xbfeq.data.FeqAccountDatabaseExtensions;
import com.fitechlabs.xtrade.plugin.tc.xbfeq.data.FeqMasterDatabaseExtensions;

import webbroker3.common.WEB3LogSysTimeInterceptor;
import webbroker3.common.define.WEB3EnforcementDef;
import webbroker3.common.define.WEB3OrderAccProductDef;
import webbroker3.common.define.WEB3OrderAccTransactionDef;
import webbroker3.common.define.WEB3ProductCodeDef;
import webbroker3.common.define.WEB3TradingTimeTypeDef;
import webbroker3.feq.data.WEB3FeqAccountDatabaseExtensions;
import webbroker3.feq.data.WEB3FeqMasterDatabaseExtensions;
import webbroker3.feq.data.WEB3FeqSessionDatabaseExtensions;
import webbroker3.feq.handler.WEB3AdminFeqCalendarRegistHandler;
import webbroker3.feq.handler.WEB3AdminFeqCancelExecutionHandler;
import webbroker3.feq.handler.WEB3AdminFeqExchangeRegistHandler;
import webbroker3.feq.handler.WEB3AdminFeqExecuteResultUploadHandler;
import webbroker3.feq.handler.WEB3AdminFeqExecutionEndHandler;
import webbroker3.feq.handler.WEB3AdminFeqExecutionInputHandler;
import webbroker3.feq.handler.WEB3AdminFeqForeignCostRegistHandler;
import webbroker3.feq.handler.WEB3AdminFeqMarketLinkChangeHandler;
import webbroker3.feq.handler.WEB3AdminFeqOpenAtOrderDLHandler;
import webbroker3.feq.handler.WEB3AdminFeqOrderAcceptHandler;
import webbroker3.feq.handler.WEB3AdminFeqOrderAcceptResultUploadHandler;
import webbroker3.feq.handler.WEB3AdminFeqOrderAndExecutionHandler;
import webbroker3.feq.handler.WEB3AdminFeqOrderVoucherListHandler;
import webbroker3.feq.handler.WEB3AdminFeqProductInfoUpdateHandler;
import webbroker3.feq.handler.WEB3AdminFeqProductListHandler;
import webbroker3.feq.handler.WEB3AdminFeqRcvdQueueReferenceHandler;
import webbroker3.feq.handler.WEB3AdminFeqSendQueueRecoveryHandler;
import webbroker3.feq.handler.WEB3AdminFeqSendQueueReferenceHandler;
import webbroker3.feq.handler.WEB3AdminFeqUploadErrCancelHandler;
import webbroker3.feq.handler.WEB3FeqBalanceReferenceHandler;
import webbroker3.feq.handler.WEB3FeqBookValuePriceRegistHandler;
import webbroker3.feq.handler.WEB3FeqBuyHandler;
import webbroker3.feq.handler.WEB3FeqCancelHandler;
import webbroker3.feq.handler.WEB3FeqChangeHandler;
import webbroker3.feq.handler.WEB3FeqExecuteReferenceHandler;
import webbroker3.feq.handler.WEB3FeqNettingExchangeHandler;
import webbroker3.feq.handler.WEB3FeqOrderAcceptExecutionNotifyHandler;
import webbroker3.feq.handler.WEB3FeqOrderCarryOverHandler;
import webbroker3.feq.handler.WEB3FeqProductListHandler;
import webbroker3.feq.handler.WEB3FeqSellHandler;
import webbroker3.feq.message.*;
import webbroker3.feq.service.delegate.WEB3AdminFeqCalendarRegistService;
import webbroker3.feq.service.delegate.WEB3AdminFeqCancelExecutionService;
import webbroker3.feq.service.delegate.WEB3AdminFeqExchangeRegistService;
import webbroker3.feq.service.delegate.WEB3AdminFeqExecuteResultUploadService;
import webbroker3.feq.service.delegate.WEB3AdminFeqExecutionEndService;
import webbroker3.feq.service.delegate.WEB3AdminFeqExecutionEndUnitService;
import webbroker3.feq.service.delegate.WEB3AdminFeqExecutionInputService;
import webbroker3.feq.service.delegate.WEB3AdminFeqForeignCostRegistService;
import webbroker3.feq.service.delegate.WEB3AdminFeqMarketLinkChangeService;
import webbroker3.feq.service.delegate.WEB3AdminFeqOpenAtOrderDLService;
import webbroker3.feq.service.delegate.WEB3AdminFeqOrderAcceptResultUploadService;
import webbroker3.feq.service.delegate.WEB3AdminFeqOrderAcceptService;
import webbroker3.feq.service.delegate.WEB3AdminFeqOrderAndExecutionService;
import webbroker3.feq.service.delegate.WEB3AdminFeqOrderVoucherListService;
import webbroker3.feq.service.delegate.WEB3AdminFeqProductInfoUpdateService;
import webbroker3.feq.service.delegate.WEB3AdminFeqProductListService;
import webbroker3.feq.service.delegate.WEB3AdminFeqRcvdQueueReferenceService;
import webbroker3.feq.service.delegate.WEB3AdminFeqSendQueueRecoveryService;
import webbroker3.feq.service.delegate.WEB3AdminFeqSendQueueReferenceService;
import webbroker3.feq.service.delegate.WEB3AdminFeqUploadErrCancelService;
import webbroker3.feq.service.delegate.WEB3FeqAcceptUpdateService;
import webbroker3.feq.service.delegate.WEB3FeqBalanceReferenceService;
import webbroker3.feq.service.delegate.WEB3FeqBookValuePriceRegistService;
import webbroker3.feq.service.delegate.WEB3FeqBuyService;
import webbroker3.feq.service.delegate.WEB3FeqCancelService;
import webbroker3.feq.service.delegate.WEB3FeqChangeService;
import webbroker3.feq.service.delegate.WEB3FeqExecuteReferenceService;
import webbroker3.feq.service.delegate.WEB3FeqExecutionNotifyUnitService;
import webbroker3.feq.service.delegate.WEB3FeqMailSenderService;
import webbroker3.feq.service.delegate.WEB3FeqNettingExchangeService;
import webbroker3.feq.service.delegate.WEB3FeqOrderAcceptExecutionNotifyService;
import webbroker3.feq.service.delegate.WEB3FeqOrderAcceptUnitService;
import webbroker3.feq.service.delegate.WEB3FeqOrderAndExecutionUpdateService;
import webbroker3.feq.service.delegate.WEB3FeqOrderCarryOverService;
import webbroker3.feq.service.delegate.WEB3FeqOrderCarryOverUnitService;
import webbroker3.feq.service.delegate.WEB3FeqOrderEmpCodeGettingService;
import webbroker3.feq.service.delegate.WEB3FeqOrderEmpCodeManageService;
import webbroker3.feq.service.delegate.WEB3FeqProductListService;
import webbroker3.feq.service.delegate.WEB3FeqSellService;
import webbroker3.feq.service.delegate.stdimpls.WEB3AdminFeqCalendarRegistServiceImpl;
import webbroker3.feq.service.delegate.stdimpls.WEB3AdminFeqCancelExecutionServiceImpl;
import webbroker3.feq.service.delegate.stdimpls.WEB3AdminFeqExchangeRegistServiceImpl;
import webbroker3.feq.service.delegate.stdimpls.WEB3AdminFeqExecuteResultUploadServiceImpl;
import webbroker3.feq.service.delegate.stdimpls.WEB3AdminFeqExecutionEndServiceImpl;
import webbroker3.feq.service.delegate.stdimpls.WEB3AdminFeqExecutionEndUnitServiceImpl;
import webbroker3.feq.service.delegate.stdimpls.WEB3AdminFeqExecutionInputServiceImpl;
import webbroker3.feq.service.delegate.stdimpls.WEB3AdminFeqForeignCostRegistServiceImpl;
import webbroker3.feq.service.delegate.stdimpls.WEB3AdminFeqMarketLinkChangeServiceImpl;
import webbroker3.feq.service.delegate.stdimpls.WEB3AdminFeqOpenAtOrderDLServiceImpl;
import webbroker3.feq.service.delegate.stdimpls.WEB3AdminFeqOrderAcceptResultUploadServiceImpl;
import webbroker3.feq.service.delegate.stdimpls.WEB3AdminFeqOrderAcceptServiceImpl;
import webbroker3.feq.service.delegate.stdimpls.WEB3AdminFeqOrderAndExecutionServiceImpl;
import webbroker3.feq.service.delegate.stdimpls.WEB3AdminFeqOrderVoucherListServiceImpl;
import webbroker3.feq.service.delegate.stdimpls.WEB3AdminFeqProductInfoUpdateServiceImpl;
import webbroker3.feq.service.delegate.stdimpls.WEB3AdminFeqProductListServiceImpl;
import webbroker3.feq.service.delegate.stdimpls.WEB3AdminFeqRcvdQueueReferenceServiceImpl;
import webbroker3.feq.service.delegate.stdimpls.WEB3AdminFeqSendQueueRecoveryServiceImpl;
import webbroker3.feq.service.delegate.stdimpls.WEB3AdminFeqSendQueueReferenceServiceImpl;
import webbroker3.feq.service.delegate.stdimpls.WEB3AdminFeqUploadErrCancelServiceImpl;
import webbroker3.feq.service.delegate.stdimpls.WEB3FeqAcceptUpdateServiceImpl;
import webbroker3.feq.service.delegate.stdimpls.WEB3FeqBalanceReferenceServiceImpl;
import webbroker3.feq.service.delegate.stdimpls.WEB3FeqBookValuePriceRegistServiceImpl;
import webbroker3.feq.service.delegate.stdimpls.WEB3FeqBuyServiceImpl;
import webbroker3.feq.service.delegate.stdimpls.WEB3FeqCancelServiceImpl;
import webbroker3.feq.service.delegate.stdimpls.WEB3FeqChangeServiceImpl;
import webbroker3.feq.service.delegate.stdimpls.WEB3FeqExecuteReferenceServiceImpl;
import webbroker3.feq.service.delegate.stdimpls.WEB3FeqExecutionNotifyUnitServiceImpl;
import webbroker3.feq.service.delegate.stdimpls.WEB3FeqMailSenderServiceImpl;
import webbroker3.feq.service.delegate.stdimpls.WEB3FeqNettingExchangeServiceImpl;
import webbroker3.feq.service.delegate.stdimpls.WEB3FeqOrderAcceptExecutionNotifyServiceImpl;
import webbroker3.feq.service.delegate.stdimpls.WEB3FeqOrderAcceptUnitServiceImpl;
import webbroker3.feq.service.delegate.stdimpls.WEB3FeqOrderAndExecutionUpdateServiceImpl;
import webbroker3.feq.service.delegate.stdimpls.WEB3FeqOrderCarryOverServiceImpl;
import webbroker3.feq.service.delegate.stdimpls.WEB3FeqOrderCarryOverUnitServiceImpl;
import webbroker3.feq.service.delegate.stdimpls.WEB3FeqOrderEmpCodeGettingServiceImpl;
import webbroker3.feq.service.delegate.stdimpls.WEB3FeqOrderEmpCodeManageServiceImpl;
import webbroker3.feq.service.delegate.stdimpls.WEB3FeqProductListServiceImpl;
import webbroker3.feq.service.delegate.stdimpls.WEB3FeqSellServiceImpl;
import webbroker3.gentrade.WEB3GentradeTradingClendarContext;
import webbroker3.gentrade.WEB3GentradeTradingTimeManagement;
import webbroker3.util.WEB3LogUtility;

/**
 * Webbroker3-feq プラグインクラス。
 *
 * @@author 黄建 (中訊)
 * @@version 1.0
 */

public final class WEB3FeqAppPlugin  extends Plugin
{

    /**
     * ログユーティリティ。
     */
    private static WEB3LogUtility log =
        WEB3LogUtility.getInstance(WEB3FeqAppPlugin.class);

    /**
     * コンストラクタ。
     */
    public WEB3FeqAppPlugin()
    {
    }

    /**
     * プラグインエントリーポイント。
     */
    public static void plug() throws Exception
    {
        String METHOD_NAME = "plug()";
        log.entering(METHOD_NAME);

        plug(WEB3FeqAppPlugin.class);

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
            l_finApp.uninstallTradingModule("xb-feq-pdbt");
        }
        catch (NotInstalledException l_ex)
        {
            log.info(l_ex.getMessage());
        }

        try
        {
            log.info("Installing TradingModule : web3-feq");
            l_finApp.installTradingModule(new WEB3FeqTradingModule());
            log.info("Installed TradingModule : web3-feq");
        }
        catch (AlreadyInstalledException l_ex)
        {
            log.info(l_ex.getMessage());
        }

        TradingModule l_tradingModule = l_finApp.getTradingModule(ProductTypeEnum.FOREIGN_EQUITY);

        // 市場リクエスト送信サービス
        l_tradingModule.getMarketAdapter().installMarketRequestSenderService(
            new WEB3FeqMarketRequestSenderService());

        // 計算サービスクラス
        l_tradingModule.overrideBizLogicProvider(new WEB3FeqBizLogicProvider());

        // 拡張注文マネージャ
        l_tradingModule.overrideOrderManager(new WEB3FeqOrderManager());

        // FEQプロダクトマネージャ 
        l_tradingModule.overrideProductManager(new WEB3FeqProductManager());

        // ポジションマネージャ
        l_tradingModule.overridePositionManager(new WEB3FeqPositionManager());

        //発注審査個別チェック
        WEB3FeqTypeOrderManagerReusableValidations.register();
        
        //外国株式注文更新マネージャ
        WEB3FeqProductTypeOrderSubmitterPersistenceManager 
            l_feqProductTypeOrderSubmitterPersistenceManager = 
                new WEB3FeqProductTypeOrderSubmitterPersistenceManager();
        l_feqProductTypeOrderSubmitterPersistenceManager.register();
        
        // DatabaseExtensions のプラグイン処理 ----------------------
        FeqMasterDatabaseExtensions.plug();
        FeqAccountDatabaseExtensions.plug();
        WEB3FeqSessionDatabaseExtensions.plug();
        WEB3FeqMasterDatabaseExtensions.plug();
        WEB3FeqAccountDatabaseExtensions.plug();
        
        
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
        
        // キャッシュ・プリロード
        try
        {
            WEB3FeqProductManager l_feqProductManager =
                (WEB3FeqProductManager)GtlUtils.getTradingModule(
                    ProductTypeEnum.FOREIGN_EQUITY).getProductManager();
            QueryProcessor l_processor = Processors.getDefaultProcessor();
            List l_lisInstitution =
                l_processor.doFindAllQuery(InstitutionRow.TYPE);
            if (l_lisInstitution != null)
            {
                WEB3GentradeTradingClendarContext l_context = 
                    new WEB3GentradeTradingClendarContext();
                l_context.setTradingTimeType(WEB3TradingTimeTypeDef.FOREIGN_STOCK);
                l_context.setProductCode(WEB3ProductCodeDef.DEFAULT);
                l_context.setOrderAcceptProduct(WEB3OrderAccProductDef.FOREIGN_STOCK);
                l_context.setOrderAcceptTransaction(WEB3OrderAccTransactionDef.REFERENCE);
                ThreadLocalSystemAttributesRegistry.setAttribute(
                    WEB3GentradeTradingTimeManagement.TRADING_CAL_CONTEXT_PATH,
                    l_context);
                int l_intInstitutionSize = l_lisInstitution.size();
                for (int i = 0;i < l_intInstitutionSize;i++)
                {
                    InstitutionRow l_institutionRow = (InstitutionRow)l_lisInstitution.get(i);
                    String l_strInstitutionCode = l_institutionRow.getInstitutionCode();
                    l_context.setInstitutionCode(l_strInstitutionCode);
                    List l_lisBranch =
                        l_processor.doFindAllQuery(
                            BranchRow.TYPE,
                            "institution_code = ?",
                            new Object[] { l_strInstitutionCode });
                    if (l_lisBranch != null)
                    {
                        int l_intBranchSize = l_lisBranch.size();
                        for (int j = 0;j < l_intBranchSize;j++)
                        {
                            BranchRow l_branchRow = (BranchRow)l_lisBranch.get(j);
                            if (WEB3EnforcementDef.ENFORCEMENT.equals(l_branchRow.getFstkDiv()))
                            {
                                String l_strBranchCode = l_branchRow.getBranchCode();
                                l_context.setBranchCode(l_strBranchCode);
                                l_context.setMarketCode(null);
                                WEB3GentradeTradingTimeManagement.setTimestamp();
                                l_feqProductManager.getProduct(
                                    l_strInstitutionCode, 
                                    null,
                                    null,
                                    null,
                                    true,
                                    null);
                                break;
                            }
                        }
                    }
                }
                ThreadLocalSystemAttributesRegistry.setAttribute(
                    WEB3GentradeTradingTimeManagement.TRADING_CAL_CONTEXT_PATH,
                    null);
            }
        }
        catch (Exception l_exp)
        {
            log.error(l_exp.getMessage(), l_exp);
            Object l_objAttribute = ThreadLocalSystemAttributesRegistry.getAttribute(
                WEB3GentradeTradingTimeManagement.TRADING_CAL_CONTEXT_PATH);
            if (l_objAttribute != null)
            {
                ThreadLocalSystemAttributesRegistry.setAttribute(
                    WEB3GentradeTradingTimeManagement.TRADING_CAL_CONTEXT_PATH,
                    null);
            }
        }
        
        log.exiting(METHOD_NAME);
    }
    /**
     * Service の登録処理
     * @@throws Exception
     */
    private static void plugServices() throws Exception
    {
        // ------（外国株式共通）外国株式共通サービス------
    
        //外国株式メール送信サービス
        Services.registerService(
            WEB3FeqMailSenderService.class,
            new WEB3FeqMailSenderServiceImpl());
   
        //外国株式共通メッセージ作成サービス                 
        Services.registerService(
            WEB3FeqOrderEmpCodeManageService.class,
            new WEB3FeqOrderEmpCodeManageServiceImpl());
        
        //外国株式受付更新サービス
        Services.registerService(
            WEB3FeqAcceptUpdateService.class,
            new WEB3FeqAcceptUpdateServiceImpl());

        //外国株式受付更新サービス
        Services.registerService(
            WEB3FeqOrderAndExecutionUpdateService.class,
            new WEB3FeqOrderAndExecutionUpdateServiceImpl());

        //外国株式運用コード取得サービス
        Services.registerService(
            WEB3FeqOrderEmpCodeGettingService.class,
            new WEB3FeqOrderEmpCodeGettingServiceImpl());
        //（外国株式共通）外国株式共通サービス---------End-------

        
        //（外国株式）（管）外国株式サービス------
    
        //外国株式カレンダー登録サービス
        Services.registerService(
            WEB3AdminFeqCalendarRegistService.class,
            new WEB3AdminFeqCalendarRegistServiceImpl());     
      
        //外国株式為替登録サービス
        Services.registerService(
            WEB3AdminFeqExchangeRegistService.class,
            new WEB3AdminFeqExchangeRegistServiceImpl());   
        
        //外国株式寄付注文DLサービス                     
        Services.registerService(
            WEB3AdminFeqOpenAtOrderDLService.class,
            new WEB3AdminFeqOpenAtOrderDLServiceImpl());    
        
       //外国株式現地手数料登録サービス     
        Services.registerService(
            WEB3AdminFeqForeignCostRegistService.class,
            new WEB3AdminFeqForeignCostRegistServiceImpl());       

       //外国株式出来終了UnitService 
        Services.registerService(
            WEB3AdminFeqExecutionEndUnitService.class,
            new WEB3AdminFeqExecutionEndUnitServiceImpl());
        
        //外国株式出来終了サービス
        Services.registerService(
            WEB3AdminFeqExecutionEndService.class,
            new WEB3AdminFeqExecutionEndServiceImpl());  

       //管理者外国株式出来入力サービス
        Services.registerService(
            WEB3AdminFeqExecutionInputService.class,
            new WEB3AdminFeqExecutionInputServiceImpl());           

       //管理者外国株式出来約定取消サービス
        Services.registerService(
            WEB3AdminFeqCancelExecutionService.class,
            new WEB3AdminFeqCancelExecutionServiceImpl());  

        //管理者外国株式注文受付結果ｱｯﾌﾟﾛｰﾄﾞサービス
        Services.registerService(
            WEB3AdminFeqOrderAcceptResultUploadService.class,
            new WEB3AdminFeqOrderAcceptResultUploadServiceImpl());  

        //管理者外国株式注文受付取消認証サービス
        Services.registerService(
            WEB3AdminFeqOrderAcceptService.class,
            new WEB3AdminFeqOrderAcceptServiceImpl());  

        //管理者外国株式当日注文伝票一覧サービス
        Services.registerService(
            WEB3AdminFeqOrderVoucherListService.class,
            new WEB3AdminFeqOrderVoucherListServiceImpl());  

        //管理者外国株式同時ｱｯﾌﾟﾛｰﾄﾞｴﾗｰ解除サービス
        Services.registerService(
            WEB3AdminFeqUploadErrCancelService.class,
            new WEB3AdminFeqUploadErrCancelServiceImpl());  

        //管理者外国株式銘柄一覧サービス
        Services.registerService(
            WEB3AdminFeqProductListService.class,
            new WEB3AdminFeqProductListServiceImpl());  

        //外国株式銘柄情報更新サービス
        Services.registerService(
            WEB3AdminFeqProductInfoUpdateService.class,
            new WEB3AdminFeqProductInfoUpdateServiceImpl());  

        //管理者外国株式約定結果ｱｯﾌﾟﾛｰﾄﾞサービス
        Services.registerService(
            WEB3AdminFeqExecuteResultUploadService.class,
            new WEB3AdminFeqExecuteResultUploadServiceImpl());  

        //管理者外国株式約定入力サービス
        Services.registerService(
            WEB3AdminFeqOrderAndExecutionService.class,
            new WEB3AdminFeqOrderAndExecutionServiceImpl());  
        
        //管理者外国株式RCVDキュー照会サービス 
        Services.registerService(
            WEB3AdminFeqRcvdQueueReferenceService.class,
            new WEB3AdminFeqRcvdQueueReferenceServiceImpl());   
        
        //管理者外国株式SENDキューリカバリサービス 
        Services.registerService(
            WEB3AdminFeqSendQueueRecoveryService.class,
            new WEB3AdminFeqSendQueueRecoveryServiceImpl()); 
        
        //管理者外国株式SENDキュー照会サービス 
        Services.registerService(
            WEB3AdminFeqSendQueueReferenceService.class,
            new WEB3AdminFeqSendQueueReferenceServiceImpl()); 
        
        //管理者外国株式市場連動区分変更サービス 
        Services.registerService(
            WEB3AdminFeqMarketLinkChangeService.class,
            new WEB3AdminFeqMarketLinkChangeServiceImpl ()); 
        
        //（外国株式）（管）外国株式サービス---------End-------

        //（外国株式） 外国株式サービス------

        //外国株式残高照会サービス
        Services.registerService(
            WEB3FeqBalanceReferenceService.class,
            new WEB3FeqBalanceReferenceServiceImpl());  

        //外国株式取消サービス
        Services.registerService(
            WEB3FeqCancelService.class,
            new WEB3FeqCancelServiceImpl());  

        //外国株式注文繰越UnitService
        Services.registerService(
            WEB3FeqOrderCarryOverUnitService.class,
            new WEB3FeqOrderCarryOverUnitServiceImpl());  

        //外国株式注文繰越サービス
        Services.registerService(
            WEB3FeqOrderCarryOverService.class,
            new WEB3FeqOrderCarryOverServiceImpl());  

        //外国株式注文約定照会サービス
        Services.registerService(
            WEB3FeqExecuteReferenceService.class,
            new WEB3FeqExecuteReferenceServiceImpl());  

        //外国株式訂正サービス
        Services.registerService(
            WEB3FeqChangeService.class,
            new WEB3FeqChangeServiceImpl());  

        //外国株式買付サービス
        Services.registerService(
            WEB3FeqBuyService.class,
            new WEB3FeqBuyServiceImpl());  

        //外国株式売付サービス
        Services.registerService(
            WEB3FeqSellService.class,
            new WEB3FeqSellServiceImpl());  

        //外国株式簿価単価登録サービス
        Services.registerService(
            WEB3FeqBookValuePriceRegistService.class,
            new WEB3FeqBookValuePriceRegistServiceImpl());  

        //外国株式銘柄一覧サービス
        Services.registerService(
            WEB3FeqProductListService.class,
            new WEB3FeqProductListServiceImpl());  
        
        //外国株式注文受付出来通知サービス
        Services.registerService(
            WEB3FeqOrderAcceptExecutionNotifyService.class,
            new WEB3FeqOrderAcceptExecutionNotifyServiceImpl());  
        
        //外国株式注文受付１件サービス 
        Services.registerService(
            WEB3FeqOrderAcceptUnitService.class,
            new WEB3FeqOrderAcceptUnitServiceImpl()); 
        
        //外国株式出来通知１件サービス 
        Services.registerService(
            WEB3FeqExecutionNotifyUnitService.class,
            new WEB3FeqExecutionNotifyUnitServiceImpl()); 

        //外国株式為替ネッティングサービス
        Services.registerService(
            WEB3FeqNettingExchangeService.class,
            new WEB3FeqNettingExchangeServiceImpl());
        
        //（外国株式）  外国株式サービス---------End-------
    }
    
    /**
     * Service の Interceptor 設定処理<BR>
     * 処理開始時刻と処理終了時刻をログ出力するように設定する
     * @@throws Exception
     */
    private static void plugLogSysTimeInterceptors() throws Exception
    {
        // ------（外国株式共通）外国株式共通サービス------
    
         //外国株式メール送信サービス
         Services.addInterceptor(
             WEB3FeqMailSenderService.class,
             new WEB3LogSysTimeInterceptor());
   
        //外国株式共通メッセージ作成サービス                 
         Services.addInterceptor(
             WEB3FeqOrderEmpCodeManageService.class,
             new WEB3LogSysTimeInterceptor());        

         //外国株式運用コード取得サービス
         Services.addInterceptor(
             WEB3FeqOrderEmpCodeGettingService.class,
             new WEB3LogSysTimeInterceptor()); 

         //（外国株式共通）外国株式共通サービス---------End-------

         //（外国株式）（管）外国株式サービス------
    
         //外国株式カレンダー登録サービス
         Services.addInterceptor(
             WEB3AdminFeqCalendarRegistService.class,
             new WEB3LogSysTimeInterceptor());   
      
         //外国株式為替登録サービス
         Services.addInterceptor(
             WEB3AdminFeqExchangeRegistService.class,
             new WEB3LogSysTimeInterceptor());
        
         //外国株式寄付注文DLサービス                     
         Services.addInterceptor(
             WEB3AdminFeqOpenAtOrderDLService.class,
             new WEB3LogSysTimeInterceptor());
        
        //外国株式現地手数料登録サービス     
         Services.addInterceptor(
             WEB3AdminFeqForeignCostRegistService.class,
             new WEB3LogSysTimeInterceptor());

        //外国株式出来終了UnitService 
         Services.addInterceptor(
             WEB3AdminFeqExecutionEndUnitService.class,
             new WEB3LogSysTimeInterceptor());
        
         //外国株式出来終了サービス
         Services.addInterceptor(
             WEB3AdminFeqExecutionEndService.class,
             new WEB3LogSysTimeInterceptor());

        //管理者外国株式出来入力サービス
         Services.addInterceptor(
             WEB3AdminFeqExecutionInputService.class,
             new WEB3LogSysTimeInterceptor());          

        //管理者外国株式出来約定取消サービス
         Services.addInterceptor(
             WEB3AdminFeqCancelExecutionService.class,
             new WEB3LogSysTimeInterceptor());

         //管理者外国株式注文受付結果ｱｯﾌﾟﾛｰﾄﾞサービス
         Services.addInterceptor(
             WEB3AdminFeqOrderAcceptResultUploadService.class,
             new WEB3LogSysTimeInterceptor());

         //管理者外国株式注文受付取消認証サービス
         Services.addInterceptor(
             WEB3AdminFeqOrderAcceptService.class,
             new WEB3LogSysTimeInterceptor());

         //管理者外国株式当日注文伝票一覧サービス
         Services.addInterceptor(
             WEB3AdminFeqOrderVoucherListService.class,
             new WEB3LogSysTimeInterceptor()); 

         //管理者外国株式同時ｱｯﾌﾟﾛｰﾄﾞｴﾗｰ解除サービス
         Services.addInterceptor(
             WEB3AdminFeqUploadErrCancelService.class,
             new WEB3LogSysTimeInterceptor());

         //管理者外国株式銘柄一覧サービス
         Services.addInterceptor(
             WEB3AdminFeqProductListService.class,
             new WEB3LogSysTimeInterceptor());

         //外国株式銘柄情報更新サービス
         Services.addInterceptor(
             WEB3AdminFeqProductInfoUpdateService.class,
             new WEB3LogSysTimeInterceptor());

         //管理者外国株式約定結果ｱｯﾌﾟﾛｰﾄﾞサービス
         Services.addInterceptor(
             WEB3AdminFeqExecuteResultUploadService.class,
             new WEB3LogSysTimeInterceptor());

         //管理者外国株式約定入力サービス
         Services.addInterceptor(
             WEB3AdminFeqOrderAndExecutionService.class,
             new WEB3LogSysTimeInterceptor());

         //管理者外国株式RCVDキュー照会サービス 
         Services.addInterceptor(
             WEB3AdminFeqRcvdQueueReferenceService.class,
             new WEB3LogSysTimeInterceptor());
         
         //管理者外国株式SENDキューリカバリサービス 
         Services.addInterceptor(
             WEB3AdminFeqSendQueueRecoveryService.class,
             new WEB3LogSysTimeInterceptor());
         
         //管理者外国株式SENDキュー照会サービス 
         Services.addInterceptor(
             WEB3AdminFeqSendQueueReferenceService.class,
             new WEB3LogSysTimeInterceptor());
         
         //管理者外国株式市場連動区分変更サービス 
         Services.addInterceptor(
             WEB3AdminFeqMarketLinkChangeService.class,
             new WEB3LogSysTimeInterceptor());
         
         //（外国株式）（管）外国株式サービス---------End-------

         //（外国株式） 外国株式サービス------

         //外国株式残高照会サービス
         Services.addInterceptor(
             WEB3FeqBalanceReferenceService.class,
             new WEB3LogSysTimeInterceptor());

         //外国株式取消サービス
         Services.addInterceptor(
             WEB3FeqCancelService.class,
             new WEB3LogSysTimeInterceptor());

         //外国株式注文繰越UnitService
         Services.addInterceptor(
             WEB3FeqOrderCarryOverUnitService.class,
             new WEB3LogSysTimeInterceptor());

         //外国株式注文繰越サービス
         Services.addInterceptor(
             WEB3FeqOrderCarryOverService.class,
             new WEB3LogSysTimeInterceptor());

         //外国株式注文約定照会サービス
         Services.addInterceptor(
             WEB3FeqExecuteReferenceService.class,
             new WEB3LogSysTimeInterceptor());

         //外国株式訂正サービス
         Services.addInterceptor(
             WEB3FeqChangeService.class,
             new WEB3LogSysTimeInterceptor());

         //外国株式買付サービス
         Services.addInterceptor(
             WEB3FeqBuyService.class,
             new WEB3LogSysTimeInterceptor());

         //外国株式売付サービス
         Services.addInterceptor(
             WEB3FeqSellService.class,
             new WEB3LogSysTimeInterceptor());

         //外国株式簿価単価登録サービス
         Services.addInterceptor(
             WEB3FeqBookValuePriceRegistService.class,
             new WEB3LogSysTimeInterceptor());

         //外国株式銘柄一覧サービス
         Services.addInterceptor(
             WEB3FeqProductListService.class,
             new WEB3LogSysTimeInterceptor());
         
         //外国株式注文受付出来通知サービス
         Services.addInterceptor(
             WEB3FeqOrderAcceptExecutionNotifyService.class,
             new WEB3LogSysTimeInterceptor());
         
         //外国株式注文受付１件サービス 
         Services.addInterceptor(
             WEB3FeqOrderAcceptUnitService.class,
             new WEB3LogSysTimeInterceptor());
         
         //外国株式出来通知１件サービス 
         Services.addInterceptor(
             WEB3FeqExecutionNotifyUnitService.class,
             new WEB3LogSysTimeInterceptor());

         //外国株式為替ネッティングサービス
         Services.addInterceptor(
             WEB3FeqNettingExchangeService.class,
             new WEB3LogSysTimeInterceptor());

         //（外国株式）  外国株式サービス---------End-------
    
        }
    /**
     * Service の Interceptor 設定処理<BR>
     * ServiceInterceptor設定
     * @@throws Exception
     */
    private static void plugServiceInterceptors() throws Exception
    {
        // ------（外国株式共通）外国株式共通サービスインタフェース------

        //外国株式出来終了UnitServiceインタフェース
         Services.addInterceptor(
             WEB3AdminFeqExecutionEndUnitService.class,
             new WEB3AdminFeqExecutionEndUnitServiceInterceptor());
         
         //外国株式出来終了サービスインタフェース
         Services.addInterceptor(
             WEB3AdminFeqExecutionEndService.class,
             new WEB3AdminFeqExecutionEndServiceInterceptor());

        //管理者外国株式出来入力サービスインタフェース
         Services.addInterceptor(
             WEB3AdminFeqExecutionInputService.class,
             new WEB3AdminFeqExecutionInputServiceInterceptor());    

        //管理者外国株式出来約定取消サービスインタフェース
         Services.addInterceptor(
             WEB3AdminFeqCancelExecutionService.class,
             new WEB3AdminFeqCancelExecutionServiceInterceptor());

         //管理者外国株式注文受付取消認証サービスインタフェース
         Services.addInterceptor(
             WEB3AdminFeqOrderAcceptService.class,
             new WEB3AdminFeqOrderAcceptServiceInterceptor());

         //管理者外国株式銘柄一覧サービスインタフェース
         Services.addInterceptor(
             WEB3AdminFeqProductListService.class,
             new WEB3AdminFeqProductListServiceInterceptor());   

         //管理者外国株式約定入力サービスインタフェース
         Services.addInterceptor(
             WEB3AdminFeqOrderAndExecutionService.class,
             new WEB3AdminFeqOrderAndExecutionInterceptor());
         
         //管理者外国株式同時ｱｯﾌﾟﾛｰﾄﾞｴﾗｰ解除サービス
         Services.addInterceptor(
             WEB3AdminFeqUploadErrCancelService.class,
             new WEB3AdminFeqUploadServiceInterceptor());

         //管理者外国株式約定結果ｱｯﾌﾟﾛｰﾄﾞサービス
         Services.addInterceptor(
             WEB3AdminFeqExecuteResultUploadService.class,
             new WEB3AdminFeqUploadServiceInterceptor());
             
        //管理者外国株式アップロードサービスインタセプタ
        Services.addInterceptor(
            WEB3AdminFeqOrderAcceptResultUploadService.class,
            new WEB3AdminFeqUploadServiceInterceptor());
            
        //外国株式銘柄情報更新インタセプタ
        Services.addInterceptor(
            WEB3AdminFeqProductInfoUpdateService.class,
            new WEB3AdminFeqProductInfoUpdateServiceInterceptor());
        
        //管理者外国株式SENDキューリカバリサービス 
        Services.addInterceptor(
            WEB3AdminFeqSendQueueRecoveryService.class,
            new WEB3AdminFeqSendQueueRecoveryServiceInterceptor());
         
         //（外国株式）（管）外国株式サービス-インタフェース--------End-------

         //（外国株式） 外国株式サービスインタフェース------

         //外国株式残高照会サービスインタフェース
         Services.addInterceptor(
             WEB3FeqBalanceReferenceService.class,
             new WEB3FeqBalanceReferenceServiceInterceptor());

         //外国株式取消サービスインタフェース
         Services.addInterceptor(
             WEB3FeqCancelService.class,
             new WEB3FeqCancelServiceInterceptor());

         //外国株式注文繰越UnitServiceインタフェース
         Services.addInterceptor(
             WEB3FeqOrderCarryOverUnitService.class,
             new WEB3FeqOrderCarryOverUnitServiceInterceptor());


         //外国株式注文約定照会サービスインタフェース
         Services.addInterceptor(
             WEB3FeqExecuteReferenceService.class,
             new WEB3FeqExecuteReferenceServiceInterceptor());

         //外国株式訂正サービスインタフェース
         Services.addInterceptor(
             WEB3FeqChangeService.class,
             new WEB3FeqChangeServiceInterceptor());

         //外国株式買付サービスインタフェース
         Services.addInterceptor(
             WEB3FeqBuyService.class,
             new WEB3FeqBuyServiceInterceptor());

         //外国株式売付サービスインタフェース
         Services.addInterceptor(
             WEB3FeqSellService.class,
             new WEB3FeqSellServiceInterceptor());

         //外国株式簿価単価登録サービスインタフェース
         Services.addInterceptor(
             WEB3FeqBookValuePriceRegistService.class,
             new WEB3FeqBookValuePriceRegistServiceInterceptor());

         //外国株式銘柄一覧サービスインタフェース
         Services.addInterceptor(
             WEB3FeqProductListService.class,
             new WEB3FeqProductListInterceptor());
         
         //外国株式注文受付１件サービス 
         Services.addInterceptor(
             WEB3FeqOrderAcceptUnitService.class,
             new WEB3FeqOrderAcceptUnitServiceInterceptor());
         
         //外国株式出来通知１件サービス 
         Services.addInterceptor(
             WEB3FeqExecutionNotifyUnitService.class,
             new WEB3FeqExecutionNotifyUnitServiceInterceptor());

         //外国株式為替ネッティングサービス
         Services.addInterceptor(
             WEB3FeqNettingExchangeService.class,
             new WEB3FeqNettingExchangeServiceInterceptor());

         //（外国株式）  外国株式サービスインタフェース---------End-------
     }
    /**
     * Service の Interceptor 設定処理<BR>
     * 自動トランザクション設定
     * @@throws Exception
     */
    private static void plugTransactionalInterceptors() throws Exception
    {
        // ------（外国株式共通）外国株式共通サービス------
    
        //外国株式メール送信サービス
        Services.addInterceptor(
            WEB3FeqMailSenderService.class,
            new TransactionalInterceptor(TransactionalInterceptor.TX_JOIN_EXISTING));
   
       //外国株式共通メッセージ作成サービス                 
        Services.addInterceptor(
            WEB3FeqOrderEmpCodeManageService.class,
            new TransactionalInterceptor(TransactionalInterceptor.TX_JOIN_EXISTING));

        //外国株式運用コード取得サービス
        Services.addInterceptor(
            WEB3FeqOrderEmpCodeGettingService.class,
            new TransactionalInterceptor(TransactionalInterceptor.TX_JOIN_EXISTING));

       //外国株式受付更新サービス
        Services.addInterceptor(
            WEB3FeqAcceptUpdateService.class,
            new TransactionalInterceptor(TransactionalInterceptor.TX_JOIN_EXISTING));
        
       //外国株式出来／約定更新サービス 
        Services.addInterceptor(
            WEB3FeqOrderAndExecutionUpdateService.class,
            new TransactionalInterceptor(TransactionalInterceptor.TX_JOIN_EXISTING));

        //（外国株式共通）外国株式共通サービス---------End-------

        //（外国株式）（管）外国株式サービス------
    
        //外国株式カレンダー登録サービス
        Services.addInterceptor(
            WEB3AdminFeqCalendarRegistService.class,
            new TransactionalInterceptor(TransactionalInterceptor.TX_JOIN_EXISTING));   
      
        //外国株式為替登録サービス
        Services.addInterceptor(
            WEB3AdminFeqExchangeRegistService.class,
            new TransactionalInterceptor(TransactionalInterceptor.TX_JOIN_EXISTING));
        
        //外国株式寄付注文DLサービス                     
        Services.addInterceptor(
            WEB3AdminFeqOpenAtOrderDLService.class,
            new TransactionalInterceptor(TransactionalInterceptor.TX_JOIN_EXISTING));
        
       //外国株式現地手数料登録サービス     
        Services.addInterceptor(
            WEB3AdminFeqForeignCostRegistService.class,
            new TransactionalInterceptor(TransactionalInterceptor.TX_JOIN_EXISTING));

       //外国株式出来終了UnitService 
        Services.addInterceptor(
            WEB3AdminFeqExecutionEndUnitService.class,
            new TransactionalInterceptor(TransactionalInterceptor.TX_CREATE_NEW));
        
        //外国株式出来終了サービス
        Services.addInterceptor(
            WEB3AdminFeqExecutionEndService.class,
            new TransactionalInterceptor(TransactionalInterceptor.TX_JOIN_EXISTING));

       //管理者外国株式出来入力サービス
        Services.addInterceptor(
            WEB3AdminFeqExecutionInputService.class,
            new TransactionalInterceptor(TransactionalInterceptor.TX_JOIN_EXISTING));          

       //管理者外国株式出来約定取消サービス
        Services.addInterceptor(
            WEB3AdminFeqCancelExecutionService.class,
            new TransactionalInterceptor(TransactionalInterceptor.TX_JOIN_EXISTING));

        //管理者外国株式注文受付結果ｱｯﾌﾟﾛｰﾄﾞサービス
        Services.addInterceptor(
            WEB3AdminFeqOrderAcceptResultUploadService.class,
            new TransactionalInterceptor(TransactionalInterceptor.TX_JOIN_EXISTING));

        //管理者外国株式注文受付取消認証サービス
        Services.addInterceptor(
            WEB3AdminFeqOrderAcceptService.class,
            new TransactionalInterceptor(TransactionalInterceptor.TX_JOIN_EXISTING));

        //管理者外国株式当日注文伝票一覧サービス
        Services.addInterceptor(
            WEB3AdminFeqOrderVoucherListService.class,
            new TransactionalInterceptor(TransactionalInterceptor.TX_JOIN_EXISTING)); 

        //管理者外国株式同時ｱｯﾌﾟﾛｰﾄﾞｴﾗｰ解除サービス
        Services.addInterceptor(
            WEB3AdminFeqUploadErrCancelService.class,
            new TransactionalInterceptor(TransactionalInterceptor.TX_JOIN_EXISTING));

        //管理者外国株式銘柄一覧サービス
        Services.addInterceptor(
            WEB3AdminFeqProductListService.class,
            new TransactionalInterceptor(TransactionalInterceptor.TX_JOIN_EXISTING));

        //外国株式銘柄情報更新サービス
        Services.addInterceptor(
            WEB3AdminFeqProductInfoUpdateService.class,
            new TransactionalInterceptor(TransactionalInterceptor.TX_JOIN_EXISTING));

        //管理者外国株式約定結果ｱｯﾌﾟﾛｰﾄﾞサービス
        Services.addInterceptor(
            WEB3AdminFeqExecuteResultUploadService.class,
            new TransactionalInterceptor(TransactionalInterceptor.TX_JOIN_EXISTING));

        //管理者外国株式約定入力サービス
        Services.addInterceptor(
            WEB3AdminFeqOrderAndExecutionService.class,
            new TransactionalInterceptor(TransactionalInterceptor.TX_JOIN_EXISTING));
        
        //管理者外国株式SENDキューリカバリサービス 
        Services.addInterceptor(
            WEB3AdminFeqSendQueueRecoveryService.class,
            new TransactionalInterceptor(TransactionalInterceptor.TX_CREATE_NEW));
        
        //管理者外国株式市場連動区分変更サービス 
        Services.addInterceptor(
            WEB3AdminFeqMarketLinkChangeService.class,
            new TransactionalInterceptor(TransactionalInterceptor.TX_CREATE_NEW));
        
        //（外国株式）（管）外国株式サービス---------End-------

        //（外国株式） 外国株式サービス------

        //外国株式残高照会サービス
        Services.addInterceptor(
            WEB3FeqBalanceReferenceService.class,
            new TransactionalInterceptor(TransactionalInterceptor.TX_JOIN_EXISTING));

        //外国株式取消サービス
        Services.addInterceptor(
            WEB3FeqCancelService.class,
            new TransactionalInterceptor(TransactionalInterceptor.TX_JOIN_EXISTING));

        //外国株式注文繰越UnitService
        Services.addInterceptor(
            WEB3FeqOrderCarryOverUnitService.class,
            new TransactionalInterceptor(TransactionalInterceptor.TX_CREATE_NEW));

        //外国株式注文繰越サービス
        Services.addInterceptor(
            WEB3FeqOrderCarryOverService.class,
            new TransactionalInterceptor(TransactionalInterceptor.TX_JOIN_EXISTING));

        //外国株式注文約定照会サービス
        Services.addInterceptor(
            WEB3FeqExecuteReferenceService.class,
            new TransactionalInterceptor(TransactionalInterceptor.TX_JOIN_EXISTING));

        //外国株式訂正サービス
        Services.addInterceptor(
            WEB3FeqChangeService.class,
            new TransactionalInterceptor(TransactionalInterceptor.TX_JOIN_EXISTING));

        //外国株式買付サービス
        Services.addInterceptor(
            WEB3FeqBuyService.class,
            new TransactionalInterceptor(TransactionalInterceptor.TX_JOIN_EXISTING));

        //外国株式売付サービス
        Services.addInterceptor(
            WEB3FeqSellService.class,
            new TransactionalInterceptor(TransactionalInterceptor.TX_JOIN_EXISTING));

        //外国株式簿価単価登録サービス
        Services.addInterceptor(
            WEB3FeqBookValuePriceRegistService.class,
            new TransactionalInterceptor(TransactionalInterceptor.TX_JOIN_EXISTING));

        //外国株式銘柄一覧サービス
        Services.addInterceptor(
            WEB3FeqProductListService.class,
            new TransactionalInterceptor(TransactionalInterceptor.TX_JOIN_EXISTING));

        //外国株式注文受付１件サービス 
        Services.addInterceptor(
            WEB3FeqOrderAcceptUnitService.class,
            new TransactionalInterceptor(TransactionalInterceptor.TX_JOIN_EXISTING));
        
        //外国株式出来通知１件サービス 
        Services.addInterceptor(
            WEB3FeqExecutionNotifyUnitService.class,
            new TransactionalInterceptor(TransactionalInterceptor.TX_JOIN_EXISTING));

        //外国株式為替ネッティングサービス 
        Services.addInterceptor(
            WEB3FeqNettingExchangeService.class,
            new TransactionalInterceptor(TransactionalInterceptor.TX_JOIN_EXISTING));
        
        //（外国株式）  外国株式サービス---------End-------
    }

    /**
     * Message の登録処理
     * @@throws Exception
     */
    private static void plugMessages() throws Exception
    {
        //外国株式残高合計リクエスト   
        regClass(WEB3FeqBalanceReferenceTotalRequest.class);

        //外国株式残高合計レスポンス   
       regClass(WEB3FeqBalanceReferenceTotalResponse.class);
        
        //外国株式残高照会リクエスト   
       regClass(WEB3FeqBalanceReferenceRequest.class);
        
        //外国株式残高照会レスポンス   
       regClass(WEB3FeqBalanceReferenceResponse.class);
        
        //外国株式取消確認リクエスト   
       regClass(WEB3FeqCancelConfirmRequest.class);
        
        //外国株式取消確認レスポンス   
       regClass(WEB3FeqCancelConfirmResponse.class);
        
        //外国株式取消完了リクエスト   
       regClass(WEB3FeqCancelCompleteRequest.class);
        
        //外国株式取消完了レスポンス   
       regClass(WEB3FeqCancelCompleteResponse.class);
        
        //外国株式注文共通リクエスト   
       regClass(WEB3FeqCommonRequest.class);
        
        //外国株式注文繰越リクエスト   
       regClass(WEB3FeqOrderTransferRequest.class);
        
        //外国株式注文繰越レスポンス   
       regClass(WEB3FeqOrderTransferResponse.class);
        
        //外国株式注文約定照会リクエスト 
       regClass(WEB3FeqExecuteReferenceRequest.class);
        
        //外国株式注文約定照会レスポンス 
       regClass(WEB3FeqExecuteReferenceResponse.class);
        
        //外国株式注文約定詳細リクエスト 
       regClass(WEB3FeqExecuteDetailsRequest.class);
        
        //外国株式注文約定詳細レスポンス 
       regClass(WEB3FeqExecuteDetailsResponse.class);
        
        //外国株式注文約定履歴リクエスト 
       regClass(WEB3FeqOrderHistoryRequest.class);
        
        //外国株式注文約定履歴レスポンス 
       regClass(WEB3FeqOrderHistoryResponse.class);
        
        //外国株式訂正確認リクエスト   
       regClass(WEB3FeqChangeConfirmRequest.class);
        
        //外国株式訂正確認レスポンス   
       regClass(WEB3FeqChangeConfirmResponse.class);
        
        //外国株式訂正完了リクエスト   
       regClass(WEB3FeqChangeCompleteRequest.class);
        
        //外国株式訂正完了レスポンス   
       regClass(WEB3FeqChangeCompleteResponse.class);
        
        //外国株式訂正共通リクエスト   
       regClass(WEB3FeqChangeCommonRequest.class);
        
        //外国株式訂正入力リクエスト   
       regClass(WEB3FeqChangeInputRequest.class);
        
        //外国株式訂正入力レスポンス   
       regClass(WEB3FeqChangeInputResponse.class);
        
        //外国株式入力共通レスポンス   
       regClass(WEB3FeqInputCommonResponse.class);
        
        //外国株式買付確認リクエスト   
       regClass(WEB3FeqBuyConfirmRequest.class);
        
        //外国株式買付確認レスポンス   
       regClass(WEB3FeqBuyConfirmResponse.class);
        
        //外国株式買付完了リクエスト   
       regClass(WEB3FeqBuyCompleteRequest.class);
        
        //外国株式買付完了レスポンス   
       regClass(WEB3FeqBuyCompleteResponse.class);
        
        //外国株式買付共通リクエスト   
       regClass(WEB3FeqBuyCommonRequest.class);
        
        //外国株式買付入力リクエスト   
       regClass(WEB3FeqBuyInputRequest.class);
        
        //外国株式買付入力レスポンス   
       regClass(WEB3FeqBuyInputResponse.class);
       
       //外国株式銘柄選択リクエスト
       regClass(WEB3FeqBuyProductSelectRequest.class);
       
       //外国株式銘柄選択レスポンス
       regClass(WEB3FeqBuyProductSelectResponse.class);
       
        //外国株式売付確認リクエスト   
       regClass(WEB3FeqSellConfirmRequest.class);
        
        //外国株式売付確認レスポンス   
       regClass(WEB3FeqSellConfirmResponse.class);
        
        //外国株式売付完了リクエスト   
       regClass(WEB3FeqSellCompleteRequest.class);
        
        //外国株式売付完了レスポンス   
       regClass(WEB3FeqSellCompleteResponse.class);
        
        //外国株式売付共通リクエスト   
       regClass(WEB3FeqSellCommonRequest.class);
        
        //外国株式売付入力リクエスト   
       regClass(WEB3FeqSellInputRequest.class);
        //外国株式売付入力レスポンス   
        
       regClass(WEB3FeqSellInputResponse.class);
        
        //外国株式簿価単価登録リクエスト 
        regClass(WEB3FeqBookPriceRegistRequest.class);
        
        //外国株式簿価単価登録レスポンス 
        regClass(WEB3FeqBookPriceRegistResponse.class);
        
        //外国株式簿価単価登録入力リクエスト   
        regClass(WEB3FeqBookPriceInputRequest.class);
        
        //外国株式簿価単価登録入力レスポンス   
        regClass(WEB3FeqBookPriceInputResponse.class);
        
        //外国株式銘柄一覧リクエスト   
        regClass(WEB3FeqProductListRequest.class);
        
        //外国株式銘柄一覧レスポンス   
        regClass(WEB3FeqProductListResponse.class);
        
        //管理者外国株式カレンダー検索条件入力リクエスト 
        regClass(WEB3AdminFeqCalendarSearchCondInputRequest.class);
        
        //管理者外国株式カレンダー検索条件入力レスポンス 
        regClass(WEB3AdminFeqCalendarSearchCondInputResponse.class);
        
        //管理者外国株式カレンダー登録確認リクエスト   
        regClass(WEB3AdminFeqCalendarRegistConfirmRequest.class);
        
        //管理者外国株式カレンダー登録確認レスポンス   
        regClass(WEB3AdminFeqCalendarRegistConfirmResponse.class);
        
        //管理者外国株式カレンダー登録完了リクエスト   
        regClass(WEB3AdminFeqCalendarRegistCompleteRequest.class);
        
        //管理者外国株式カレンダー登録完了レスポンス   
        regClass(WEB3AdminFeqCalendarRegistCompleteResponse.class);
        
        //管理者外国株式カレンダー登録共通リクエスト   
        regClass(WEB3AdminFeqCalendarRegistCommonRequest.class);
        
        //管理者外国株式カレンダー登録入力リクエスト   
        regClass(WEB3AdminFeqCalendarRegistInputRequest.class);
        
        //管理者外国株式カレンダー登録入力レスポンス   
        regClass(WEB3AdminFeqCalendarRegistInputResponse.class);
        
        //管理者外国株式為替登録確認リクエスト  
        regClass(WEB3AdminFeqExchangeRegistConfirmRequest.class);
        
        //管理者外国株式為替登録確認レスポンス  
        regClass(WEB3AdminFeqExchangeRegistConfirmResponse.class);
        
        //管理者外国株式為替登録完了リクエスト  
        regClass(WEB3AdminFeqExchangeRegistCompleteRequest.class);
        
        //管理者外国株式為替登録完了レスポンス  
        regClass(WEB3AdminFeqExchangeRegistCompleteResponse.class);
        
        //管理者外国株式為替登録入力リクエスト  
        regClass(WEB3AdminFeqExchangeRegistInputRequest.class);
        
        //管理者外国株式為替登録入力レスポンス  
        regClass(WEB3AdminFeqExchangeRegistInputResponse.class);
        
        //管理者外国株式寄付注文ダウンロードリクエスト  
        regClass(WEB3AdminFeqOpenAtOrderDownloadRequest.class);
        
        //管理者外国株式寄付注文ダウンロードレスポンス  
        regClass(WEB3AdminFeqOpenAtOrderDownloadResponse.class);
        
        //管理者外国株式寄付注文ダウンロード入力リクエスト    
        regClass(WEB3AdminFeqOpenAtOrderDownloadInputRequest.class);
        
        //管理者外国株式寄付注文ダウンロード入力レスポンス    
        regClass(WEB3AdminFeqOpenAtOrderDownloadInputResponse.class);
        
        //管理者外国株式現地手数料登録確認リクエスト   
        regClass(WEB3AdminFeqForeignCostRegistConfirmRequest.class);
        
        //管理者外国株式現地手数料登録確認レスポンス   
        regClass(WEB3AdminFeqForeignCostRegistConfirmResponse.class);
        
        //管理者外国株式現地手数料登録完了リクエスト   
        regClass(WEB3AdminFeqForeignCostRegistCompleteRequest.class);
        
        //管理者外国株式現地手数料登録完了レスポンス   
        regClass(WEB3AdminFeqForeignCostRegistCompleteResponse.class);
        
        //管理者外国株式現地手数料登録共通リクエスト   
        regClass(WEB3AdminFeqForeignCostRegistCommonRequest.class);
        
        //管理者外国株式現地手数料登録入力リクエスト   
        regClass(WEB3AdminFeqForeignCostRegistInputRequest.class);
        
        //管理者外国株式現地手数料登録入力レスポンス   
        regClass(WEB3AdminFeqForeignCostRegistInputResponse.class);
        
        //管理者外国株式出来終了確認リクエスト  
        regClass(WEB3AdminFeqExecutionEndConfirmRequest.class);
        
        //管理者外国株式出来終了確認レスポンス  
        regClass(WEB3AdminFeqExecutionEndConfirmResponse.class);
        
        //管理者外国株式出来終了完了リクエスト  
        regClass(WEB3AdminFeqExecutionEndCompleteRequest.class);
        
        //管理者外国株式出来終了完了レスポンス  
        regClass(WEB3AdminFeqExecutionEndCompleteResponse.class);
        
        //管理者外国株式出来終了共通リクエスト  
        regClass(WEB3AdminFeqExecutionEndCommonRequest.class);
        
        //管理者外国株式出来終了入力リクエスト  
        regClass(WEB3AdminFeqExecutionEndInputRequest.class);
        
        //管理者外国株式出来終了入力レスポンス  
        regClass(WEB3AdminFeqExecutionEndInputResponse.class);
        
        //管理者外国株式出来入力リクエスト    
        regClass(WEB3AdminFeqExecutionInputRequest.class);
        
        //管理者外国株式出来入力レスポンス    
        regClass(WEB3AdminFeqExecutionInputResponse.class);
        
        //管理者外国株式出来入力確認リクエスト  
        regClass(WEB3AdminFeqExecutionConfirmRequest.class);
        
        //管理者外国株式出来入力確認レスポンス  
        regClass(WEB3AdminFeqExecutionConfirmResponse.class);
        
        //管理者外国株式出来入力完了リクエスト  
        regClass(WEB3AdminFeqExecutionCompleteRequest.class);
        
        //管理者外国株式出来入力完了レスポンス  
        regClass(WEB3AdminFeqExecutionCompleteResponse.class);
        
        //管理者外国株式出来入力共通リクエスト  
        regClass(WEB3AdminFeqExecutionCommonRequest.class);
        
        //管理者外国株式出来入力検索リクエスト  
        regClass(WEB3AdminFeqExecutionSearchRequest.class);
        
        //管理者外国株式出来入力検索レスポンス  
        regClass(WEB3AdminFeqExecutionSearchResponse.class);
        
        //管理者外国株式出来約定取消確認リクエスト    
        regClass(WEB3AdminFeqCancelExecutionConfirmRequest.class);
        
        //管理者外国株式出来約定取消確認レスポンス    
        regClass(WEB3AdminFeqCancelExecutionConfirmResponse.class);
        
        //管理者外国株式出来約定取消完了リクエスト    
        regClass(WEB3AdminFeqCancelExecutionCompleteRequest.class);
        
        //管理者外国株式出来約定取消完了レスポンス    
        regClass(WEB3AdminFeqCancelExecutionCompleteResponse.class);
        
        //管理者外国株式出来約定取消入力リクエスト    
        regClass(WEB3AdminFeqCancelExecutionInputRequest.class);
        
        //管理者外国株式出来約定取消入力レスポンス    
        regClass(WEB3AdminFeqCancelExecutionInputResponse.class);
        
        //管理者外国株式注文受付結果アップロード確認リクエスト  
        regClass(WEB3AdminFeqOrderAcceptResultUploadConfirmRequest.class);
        
        //管理者外国株式注文受付結果アップロード確認レスポンス  
        regClass(WEB3AdminFeqOrderAcceptResultUploadConfirmResponse.class);
        
        //管理者外国株式注文受付結果アップロード完了リクエスト
        regClass(WEB3AdminFeqOrderAcceptResultUploadCompleteRequest.class);
        
        //管理者外国株式注文受付結果アップロード完了レスポンス  
        regClass(WEB3AdminFeqOrderAcceptResultUploadCompleteResponse.class);
        
        //管理者外国株式注文受付結果アップロード中止リクエスト  
        regClass(WEB3AdminFeqOrderAcceptResultUploadStopRequest.class);
        
        //管理者外国株式注文受付結果アップロード中止レスポンス  
        regClass(WEB3AdminFeqOrderAcceptResultUploadStopResponse.class);
        
        //管理者外国株式注文受付結果アップロード入力リクエスト  
        regClass(WEB3AdminFeqOrderAcceptResultUploadInputRequest.class);
        
        //管理者外国株式注文受付結果アップロード入力レスポンス  
        regClass(WEB3AdminFeqOrderAcceptResultUploadInputResponse.class);
        
        //管理者外国株式注文受付取消認証完了リクエスト  
        regClass(WEB3AdminFeqOrderAcceptCancelCompleteRequest.class);
        
        //管理者外国株式注文受付取消認証完了レスポンス  
        regClass(WEB3AdminFeqOrderAcceptCancelCompleteResponse.class);
        
        //管理者外国株式注文受付取消認証共通リクエスト  
        regClass(WEB3AdminFeqOrderAcceptCancelCertificationRequest.class);
        
        //管理者外国株式注文受付取消認証検索条件入力リクエスト  
        regClass(WEB3AdminFeqOrderAcceptCancelSearchCondInputRequest.class);
        
        //管理者外国株式注文受付取消認証検索条件入力レスポンス  
        regClass(WEB3AdminFeqOrderAcceptCancelSearchCondInputResponse.class);
        
        //管理者外国株式注文受付取消認証入力リクエスト  
        regClass(WEB3AdminFeqOrderAcceptCancelInputRequest.class);
        
        //管理者外国株式注文受付取消認証入力レスポンス  
        regClass(WEB3AdminFeqOrderAcceptCancelInputResponse.class);
        
        //管理者外国株式当日注文伝票一覧リクエスト    
        regClass(WEB3AdminFeqOrderVoucherListRequest.class);
        
        //管理者外国株式当日注文伝票一覧レスポンス    
        regClass(WEB3AdminFeqOrderVoucherListResponse.class);
        
        //管理者外国株式当日注文伝票一覧入力リクエスト  
        regClass(WEB3AdminFeqOrderVoucherListInputRequest.class);

        //管理者外国株式当日注文伝票一覧入力レスポンス  
        regClass(WEB3AdminFeqOrderVoucherListInputResponse.class);
        
        //管理者外国株式同時ｱｯﾌﾟﾛｰﾄﾞｴﾗｰ解除確認リクエスト   
        regClass(WEB3AdminFeqUploadErrCancelConfirmRequest.class);
        
        //管理者外国株式同時ｱｯﾌﾟﾛｰﾄﾞｴﾗｰ解除確認レスポンス   
        regClass(WEB3AdminFeqUploadErrCancelConfirmResponse.class);
        
        //管理者外国株式同時ｱｯﾌﾟﾛｰﾄﾞｴﾗｰ解除完了リクエスト   
        regClass(WEB3AdminFeqUploadErrCancelCompleteRequest.class);
        
        //管理者外国株式同時ｱｯﾌﾟﾛｰﾄﾞｴﾗｰ解除完了レスポンス   
        regClass(WEB3AdminFeqUploadErrCancelCompleteResponse.class);
        
        //管理者外国株式同時ｱｯﾌﾟﾛｰﾄﾞｴﾗｰ解除入力リクエスト   
        regClass(WEB3AdminFeqUploadErrCancelInputRequest.class);
        
        //管理者外国株式同時ｱｯﾌﾟﾛｰﾄﾞｴﾗｰ解除入力レスポンス   
        regClass(WEB3AdminFeqUploadErrCancelInputResponse.class);
        
        //管理者外国株式銘柄情報一覧リクエスト  
        regClass(WEB3AdminFeqProductListRequest.class);
        
        //管理者外国株式銘柄情報一覧レスポンス  
        regClass(WEB3AdminFeqProductListResponse.class);
        
        //管理者外国株式銘柄情報更新確認リクエスト    
        regClass(WEB3AdminFeqProductInformationUpdateConfirmRequest.class);
        
        //管理者外国株式銘柄情報更新確認レスポンス    
        regClass(WEB3AdminFeqProductInformationUpdateConfirmResponse.class);
        
        //管理者外国株式銘柄情報更新完了リクエスト    
        regClass(WEB3AdminFeqProductInformationUpdateCompleteRequest.class);
        
        //管理者外国株式銘柄情報更新完了レスポンス    
        regClass(WEB3AdminFeqProductInformationUpdateCompleteResponse.class);   
        
        //管理者外国株式銘柄情報更新共通リクエスト    
        regClass(WEB3AdminFeqProductInfomationUpdateCommonRequest.class);
        
        //管理者外国株式銘柄情報更新入力リクエスト    
        regClass(WEB3AdminFeqProductInformationUpdateInputRequest.class);
        
        //管理者外国株式銘柄情報更新入力レスポンス    
        regClass(WEB3AdminFeqProductInformationUpdateInputResponse.class);
        
        //管理者外国株式銘柄情報更新明細入力リクエスト  
        regClass(WEB3AdminFeqProductInformationUpdateDetailsInputRequest.class);
        
        //管理者外国株式銘柄情報更新明細入力レスポンス  
        regClass(WEB3AdminFeqProductInformationUpdateDetailsInputResponse.class);
        
        //管理者外国株式約定結果アップロード確認リクエスト    
        regClass(WEB3AdminFeqExecuteResultUploadConfirmRequest.class);
        
        //管理者外国株式約定結果アップロード確認レスポンス    
        regClass(WEB3AdminFeqExecuteResultUploadConfirmResponse.class);
        
        //管理者外国株式約定結果アップロード完了リクエスト    
        regClass(WEB3AdminFeqExecuteResultUploadCompleteRequest.class);
        
        //管理者外国株式約定結果アップロード完了レスポンス    
        regClass(WEB3AdminFeqExecuteResultUploadCompleteResponse.class);
        
        //管理者外国株式約定結果アップロード中止リクエスト    
        regClass(WEB3AdminFeqExecuteResultUploadStopRequest.class);
        
        //管理者外国株式約定結果アップロード中止レスポンス    
        regClass(WEB3AdminFeqExecuteResultUploadStopResponse.class);
        
        //管理者外国株式約定結果アップロード入力リクエスト    
        regClass(WEB3AdminFeqExecuteResultUploadInputRequest.class);
        
        //管理者外国株式約定結果アップロード入力レスポンス    
        regClass(WEB3AdminFeqExecuteResultUploadInputResponse.class);
        
        //管理者外国株式約定入力リクエスト    
        regClass(WEB3AdminFeqOrderAndExecutionInputRequest.class);
        
        //管理者外国株式約定入力レスポンス    
        regClass(WEB3AdminFeqOrderAndExecutionInputResponse.class);
        
        //管理者外国株式約定入力確認リクエスト  
        regClass(WEB3AdminFeqOrderAndExecutionConfirmRequest.class);
        
        //管理者外国株式約定入力確認レスポンス  
        regClass(WEB3AdminFeqOrderAndExecutionConfirmResponse.class);
        
        //管理者外国株式約定入力完了リクエスト  
        regClass(WEB3AdminFeqOrderAndExecutionCompleteRequest.class);
        
        //管理者外国株式約定入力完了レスポンス  
        regClass(WEB3AdminFeqOrderAndExecutionCompleteResponse.class);
        
        //管理者外国株式約定入力共通リクエスト  
        regClass(WEB3AdminFeqOrderAndExecutionCommonRequest.class);
        
        //管理者外国株式約定入力検索リクエスト  
        regClass(WEB3AdminFeqOrderAndExecutionSearchRequest.class);
        
        //管理者外国株式約定入力検索レスポンス  
        regClass(WEB3AdminFeqOrderAndExecutionSearchResponse.class);
        
        //外国株式注文受付出来通知リクエスト
        regClass(WEB3FeqOrderAcceptExecNotifyRequest.class);
        
        //外国株式注文受付出来通知レスポンス
        regClass(WEB3FeqOrderAcceptExecNotifyResponse.class);
        
        //管理者外国株式RCVDキュー照会一覧リクエスト
        regClass(WEB3AdminFeqRcvdQueueReferenceRequest.class);
        
        //管理者外国株式RCVDキュー照会一覧レスポンス
        regClass(WEB3AdminFeqRcvdQueueReferenceResponse.class);
        
        //管理者外国株式RCVDキュー照会入力リクエスト
        regClass(WEB3AdminFeqRcvdQueueReferenceInputRequest.class);
        
        //管理者外国株式RCVDキュー照会入力レスポンス
        regClass(WEB3AdminFeqRcvdQueueReferenceInputResponse.class);
        
        //管理者外国株式SENDキューリカバリ完了リクエスト
        regClass(WEB3AdminFeqSendQueueRecoveryCompleteRequest.class);
        
        //管理者外国株式SENDキューリカバリ完了レスポンス
        regClass(WEB3AdminFeqSendQueueRecoveryCompleteResponse.class);
        
        //管理者外国株式SENDキュー照会一覧リクエスト
        regClass(WEB3AdminFeqSendQueueReferenceRequest.class);
        
        //管理者外国株式SENDキュー照会一覧レスポンス
        regClass(WEB3AdminFeqSendQueueReferenceResponse.class);
        
        //管理者外国株式SENDキュー照会入力リクエスト
        regClass(WEB3AdminFeqSendQueueReferenceInputRequest.class);
        
        //管理者外国株式SENDキュー照会入力レスポンス
        regClass(WEB3AdminFeqSendQueueReferenceInputResponse.class);
        
        //管理者外国株式市場連動区分変更入力リクエスト
        regClass(WEB3AdminFeqMarketLinkChangeInputRequest.class);
        
        //管理者外国株式市場連動区分変更入力レスポンス
        regClass(WEB3AdminFeqMarketLinkChangeInputResponse.class);
        
        //管理者外国株式市場連動区分変更確認リクエスト
        regClass(WEB3AdminFeqMarketLinkChangeConfirmRequest.class);
        
        //管理者外国株式市場連動区分変更確認レスポンス
        regClass(WEB3AdminFeqMarketLinkChangeConfirmResponse.class);
        
        //管理者外国株式市場連動区分変更完了リクエスト
        regClass(WEB3AdminFeqMarketLinkChangeCompleteRequest.class);
        
        //管理者外国株式市場連動区分変更完了レスポンス
        regClass(WEB3AdminFeqMarketLinkChangeCompleteResponse.class);

        //外国株式簿価単価登録確認リクエスト
        regClass(WEB3FeqBookPriceConfirmRequest.class);

        //外国株式簿価単価登録確認レスポンス
        regClass(WEB3FeqBookPriceConfirmResponse.class);

        //外国株式為替ネッティングリクエスト
        regClass(WEB3FeqNettingExchangeRequest.class);

        //外国株式為替ネッティングレスポンス
        regClass(WEB3FeqNettingExchangeResponse.class);
    }

    /**
     * Handler の登録処理
     * @@throws Exception
     */
    private static void plugHandlers() throws Exception
    {
        //外国株式カレンダー登録ハンドラ
        regHandler(
            WEB3FeqAppPlugin.class,
            WEB3AdminFeqCalendarSearchCondInputRequest.class,
            WEB3AdminFeqCalendarRegistHandler.class,
            "getQueryCondInputScreen");
        regHandler(
            WEB3FeqAppPlugin.class,
            WEB3AdminFeqCalendarRegistInputRequest.class,
            WEB3AdminFeqCalendarRegistHandler.class,
            "getInputScreen");
        regHandler(
            WEB3FeqAppPlugin.class,
            WEB3AdminFeqCalendarRegistConfirmRequest.class,
            WEB3AdminFeqCalendarRegistHandler.class,
            "validateRegist");
        regHandler(
            WEB3FeqAppPlugin.class,
            WEB3AdminFeqCalendarRegistCompleteRequest.class,
            WEB3AdminFeqCalendarRegistHandler.class,
            "submitRegist");
        
        //外国株式為替登録ハンドラ    
        regHandler(
            WEB3FeqAppPlugin.class,
            WEB3AdminFeqExchangeRegistInputRequest.class,
            WEB3AdminFeqExchangeRegistHandler.class,
            "getInputScreen");
        regHandler(
            WEB3FeqAppPlugin.class,
            WEB3AdminFeqExchangeRegistConfirmRequest.class,
            WEB3AdminFeqExchangeRegistHandler.class,
            "validateRateRegist");
        regHandler(
            WEB3FeqAppPlugin.class,
            WEB3AdminFeqExchangeRegistCompleteRequest.class,
            WEB3AdminFeqExchangeRegistHandler.class,
            "submitRateRegist");
        
        //外国株式寄付注文DLハンドラ   
        regHandler(
            WEB3FeqAppPlugin.class,
            WEB3AdminFeqOpenAtOrderDownloadInputRequest.class,
            WEB3AdminFeqOpenAtOrderDLHandler.class,
            "getInputScreen");
        regHandler(
            WEB3FeqAppPlugin.class,
            WEB3AdminFeqOpenAtOrderDownloadRequest.class,
            WEB3AdminFeqOpenAtOrderDLHandler.class,
            "getDownloadFile");
        
        //外国株式現地手数料登録ハンドラ  
        regHandler(
            WEB3FeqAppPlugin.class,
            WEB3AdminFeqForeignCostRegistInputRequest.class,
            WEB3AdminFeqForeignCostRegistHandler.class,
            "getInputScreen");
        regHandler(
            WEB3FeqAppPlugin.class,
            WEB3AdminFeqForeignCostRegistConfirmRequest.class,
            WEB3AdminFeqForeignCostRegistHandler.class,
            "validateRegist");
        regHandler(
            WEB3FeqAppPlugin.class,
            WEB3AdminFeqForeignCostRegistCompleteRequest.class,
            WEB3AdminFeqForeignCostRegistHandler.class,
            "submitRegist");
        
        //外国株式出来終了ハンドラ  
        regHandler(
            WEB3FeqAppPlugin.class,
            WEB3AdminFeqExecutionEndInputRequest.class,
            WEB3AdminFeqExecutionEndHandler.class,
            "getInputScreen");
        regHandler(
            WEB3FeqAppPlugin.class,
            WEB3AdminFeqExecutionEndConfirmRequest.class,
            WEB3AdminFeqExecutionEndHandler.class,
            "validateExecEnd");
        regHandler(
            WEB3FeqAppPlugin.class,
            WEB3AdminFeqExecutionEndCompleteRequest.class,
            WEB3AdminFeqExecutionEndHandler.class,
            "submitExecEnd");
        //管理者外国株式出来入力ハンドラ  
        regHandler(
            WEB3FeqAppPlugin.class,
            WEB3AdminFeqExecutionSearchRequest.class,
            WEB3AdminFeqExecutionInputHandler.class,
            "getQueryScreen");
        regHandler(
            WEB3FeqAppPlugin.class,
            WEB3AdminFeqExecutionInputRequest.class,
            WEB3AdminFeqExecutionInputHandler.class,
            "getInputScreen");
        regHandler(
            WEB3FeqAppPlugin.class,
            WEB3AdminFeqExecutionConfirmRequest.class,
            WEB3AdminFeqExecutionInputHandler.class,
            "validateExec");
        regHandler(
            WEB3FeqAppPlugin.class,
            WEB3AdminFeqExecutionCompleteRequest.class,
            WEB3AdminFeqExecutionInputHandler.class,
            "submitExec");
        
        //管理者外国株式出来約定取消ハンドラ           
        regHandler(
            WEB3FeqAppPlugin.class,
            WEB3AdminFeqCancelExecutionInputRequest.class,
            WEB3AdminFeqCancelExecutionHandler.class,
            "getInputScreen");
        regHandler(
            WEB3FeqAppPlugin.class,
            WEB3AdminFeqCancelExecutionConfirmRequest.class,
            WEB3AdminFeqCancelExecutionHandler.class,
            "validateCancel");
        regHandler(
            WEB3FeqAppPlugin.class,
            WEB3AdminFeqCancelExecutionCompleteRequest.class,
            WEB3AdminFeqCancelExecutionHandler.class,
            "submitCancel");
        
        //管理者外国株式注文受付結果ｱｯﾌﾟﾛｰﾄﾞハンドラ           
        regHandler(
            WEB3FeqAppPlugin.class,
            WEB3AdminFeqOrderAcceptResultUploadInputRequest.class,
            WEB3AdminFeqOrderAcceptResultUploadHandler.class,
            "getUploadScreen");
        regHandler(
            WEB3FeqAppPlugin.class,
            WEB3AdminFeqOrderAcceptResultUploadConfirmRequest.class,
            WEB3AdminFeqOrderAcceptResultUploadHandler.class,
            "validateUpload");
        regHandler(
            WEB3FeqAppPlugin.class,
            WEB3AdminFeqOrderAcceptResultUploadCompleteRequest.class,
            WEB3AdminFeqOrderAcceptResultUploadHandler.class,
            "submitUpload");
        regHandler(
            WEB3FeqAppPlugin.class,
            WEB3AdminFeqOrderAcceptResultUploadStopRequest.class,
            WEB3AdminFeqOrderAcceptResultUploadHandler.class,
            "undoUpload");
        //管理者外国株式注文受付取消認証ハンドラ        
        regHandler(
            WEB3FeqAppPlugin.class,
            WEB3AdminFeqOrderAcceptCancelSearchCondInputRequest.class,
            WEB3AdminFeqOrderAcceptHandler.class,
            "getQueryScreen");
        regHandler(
            WEB3FeqAppPlugin.class,
            WEB3AdminFeqOrderAcceptCancelInputRequest.class,
            WEB3AdminFeqOrderAcceptHandler.class,
            "getInputScreen");
        regHandler(
            WEB3FeqAppPlugin.class,
            WEB3AdminFeqOrderAcceptCancelCompleteRequest.class,
            WEB3AdminFeqOrderAcceptHandler.class,
            "submitAccept");
        
        //管理者外国株式当日注文伝票一覧ハンドラ   
        regHandler(
            WEB3FeqAppPlugin.class,
            WEB3AdminFeqOrderVoucherListInputRequest.class,
            WEB3AdminFeqOrderVoucherListHandler.class,
            "getInputScreen");
        regHandler(
            WEB3FeqAppPlugin.class,
            WEB3AdminFeqOrderVoucherListRequest.class,
            WEB3AdminFeqOrderVoucherListHandler.class,
            "getListScreen");
        
        //管理者外国株式同時ｱｯﾌﾟﾛｰﾄﾞｴﾗｰ解除ハンドラ  
        regHandler(
            WEB3FeqAppPlugin.class,
            WEB3AdminFeqUploadErrCancelInputRequest.class,
            WEB3AdminFeqUploadErrCancelHandler.class,
            "getInputScreen");
        regHandler(
            WEB3FeqAppPlugin.class,
            WEB3AdminFeqUploadErrCancelConfirmRequest.class,
            WEB3AdminFeqUploadErrCancelHandler.class,
            "validateRelease");
        regHandler(
            WEB3FeqAppPlugin.class,
            WEB3AdminFeqUploadErrCancelCompleteRequest.class,
            WEB3AdminFeqUploadErrCancelHandler.class,
            "submitRelease");
        
        //管理者外国株式銘柄一覧ハンドラ 
        regHandler(
            WEB3FeqAppPlugin.class,
            WEB3AdminFeqProductListRequest.class,
            WEB3AdminFeqProductListHandler.class,
            "getListScreen");
        //外国株式銘柄情報更新ハンドラ 
        regHandler(
            WEB3FeqAppPlugin.class,
            WEB3AdminFeqProductInformationUpdateInputRequest.class,
            WEB3AdminFeqProductInfoUpdateHandler.class,
            "getInputScreen");
        regHandler(
            WEB3FeqAppPlugin.class,
            WEB3AdminFeqProductInformationUpdateDetailsInputRequest.class,
            WEB3AdminFeqProductInfoUpdateHandler.class,
            "getDetailInputScreen");
        regHandler(
            WEB3FeqAppPlugin.class,
            WEB3AdminFeqProductInformationUpdateConfirmRequest.class,
            WEB3AdminFeqProductInfoUpdateHandler.class,
            "validateUpdate");
        regHandler(
            WEB3FeqAppPlugin.class,
            WEB3AdminFeqProductInformationUpdateCompleteRequest.class,
            WEB3AdminFeqProductInfoUpdateHandler.class,
            "submitUpdate");
        //管理者外国株式約定結果ｱｯﾌﾟﾛｰﾄﾞハンドラ   
        regHandler(
            WEB3FeqAppPlugin.class,
            WEB3AdminFeqExecuteResultUploadInputRequest.class,
            WEB3AdminFeqExecuteResultUploadHandler.class,
            "getUploadScreen");
        regHandler(
            WEB3FeqAppPlugin.class,
            WEB3AdminFeqExecuteResultUploadConfirmRequest.class,
            WEB3AdminFeqExecuteResultUploadHandler.class,
            "validateUpload");
        regHandler(
            WEB3FeqAppPlugin.class,
            WEB3AdminFeqExecuteResultUploadCompleteRequest.class,
            WEB3AdminFeqExecuteResultUploadHandler.class,
            "submitUpload");
        regHandler(
            WEB3FeqAppPlugin.class,
            WEB3AdminFeqExecuteResultUploadStopRequest.class,
            WEB3AdminFeqExecuteResultUploadHandler.class,
            "undoUpload");
        
        //管理者外国株式約定入力ハンドラ     
        regHandler(
            WEB3FeqAppPlugin.class,
            WEB3AdminFeqOrderAndExecutionSearchRequest.class,
            WEB3AdminFeqOrderAndExecutionHandler.class,
            "getQueryScreen");
        regHandler(
            WEB3FeqAppPlugin.class,
            WEB3AdminFeqOrderAndExecutionInputRequest.class,
            WEB3AdminFeqOrderAndExecutionHandler.class,
            "getInputScreen");
        regHandler(
            WEB3FeqAppPlugin.class,
            WEB3AdminFeqOrderAndExecutionConfirmRequest.class,
            WEB3AdminFeqOrderAndExecutionHandler.class,
            "validateExec");
        regHandler(
            WEB3FeqAppPlugin.class,
            WEB3AdminFeqOrderAndExecutionCompleteRequest.class,
            WEB3AdminFeqOrderAndExecutionHandler.class,
            "submitExec");
        
        //外国株式残高照会ハンドラ    
        regHandler(
            WEB3FeqAppPlugin.class,
            WEB3FeqBalanceReferenceRequest.class,
            WEB3FeqBalanceReferenceHandler.class,
            "getBalanceReference");
        
        regHandler(
            WEB3FeqAppPlugin.class,
            WEB3FeqBalanceReferenceTotalRequest.class,
            WEB3FeqBalanceReferenceHandler.class,
            "getBalanceTotal");
        
        //外国株式取消ハンドラ     
        regHandler(
            WEB3FeqAppPlugin.class,
            WEB3FeqCancelConfirmRequest.class,
            WEB3FeqCancelHandler.class,
            "validateOrder");
        regHandler(
            WEB3FeqAppPlugin.class,
            WEB3FeqCancelCompleteRequest.class,
            WEB3FeqCancelHandler.class,
            "submitOrder");
        //外国株式注文繰越ハンドラ
        regHandler(
            WEB3FeqAppPlugin.class,
            WEB3FeqOrderTransferRequest.class,
            WEB3FeqOrderCarryOverHandler.class,
            "submitOrderCarryOver");
        //外国株式注文約定照会ハンドラ
        regHandler(
            WEB3FeqAppPlugin.class,
            WEB3FeqExecuteReferenceRequest.class,
            WEB3FeqExecuteReferenceHandler.class,
            "getOrderExecuteReference");
        regHandler(
            WEB3FeqAppPlugin.class,
            WEB3FeqExecuteDetailsRequest.class,
            WEB3FeqExecuteReferenceHandler.class,
            "getOrderExecuteDetails");
        regHandler(
            WEB3FeqAppPlugin.class,
            WEB3FeqOrderHistoryRequest.class,
            WEB3FeqExecuteReferenceHandler.class,
            "getOrderExecuteAction");
        
        //外国株式訂正ハンドラ
        regHandler(
            WEB3FeqAppPlugin.class,
            WEB3FeqChangeInputRequest.class,
            WEB3FeqChangeHandler.class,
            "getInputScreen");
        regHandler(
            WEB3FeqAppPlugin.class,
            WEB3FeqChangeConfirmRequest.class,
            WEB3FeqChangeHandler.class,
            "validateOrder");
        regHandler(
            WEB3FeqAppPlugin.class,
            WEB3FeqChangeCompleteRequest.class,
            WEB3FeqChangeHandler.class,
            "submitOrder");
        
        //外国株式買付ハンドラ
        regHandler(
            WEB3FeqAppPlugin.class, 
            WEB3FeqBuyInputRequest.class, 
            WEB3FeqBuyHandler.class, 
            "getInputScreen");
        regHandler(
            WEB3FeqAppPlugin.class, 
            WEB3FeqBuyConfirmRequest.class, 
            WEB3FeqBuyHandler.class, 
            "validateOrder");
        regHandler(
            WEB3FeqAppPlugin.class, 
            WEB3FeqBuyCompleteRequest.class, 
            WEB3FeqBuyHandler.class, 
            "submitOrder");
        regHandler(
            WEB3FeqAppPlugin.class, 
            WEB3FeqBuyProductSelectRequest.class, 
            WEB3FeqBuyHandler.class, 
            "getProductSelectScreen");
        
        //外国株式売付ハンドラ
        regHandler(
            WEB3FeqAppPlugin.class, 
            WEB3FeqSellInputRequest.class, 
            WEB3FeqSellHandler.class, 
            "getInputScreen");
        regHandler(
            WEB3FeqAppPlugin.class, 
            WEB3FeqSellConfirmRequest.class, 
            WEB3FeqSellHandler.class, 
            "validateOrder");
        regHandler(
            WEB3FeqAppPlugin.class, 
            WEB3FeqSellCompleteRequest.class, 
            WEB3FeqSellHandler.class, 
            "submitOrder");
        
        //外国株式簿価単価登録ハンドラ  
        regHandler(
            WEB3FeqAppPlugin.class,
            WEB3FeqBookPriceInputRequest.class,
            WEB3FeqBookValuePriceRegistHandler.class,
            "getInputScreen");
        regHandler(
            WEB3FeqAppPlugin.class,
            WEB3FeqBookPriceRegistRequest.class,
            WEB3FeqBookValuePriceRegistHandler.class,
            "submitBookValuePrice");
        regHandler(
            WEB3FeqAppPlugin.class,
            WEB3FeqBookPriceConfirmRequest.class,
            WEB3FeqBookValuePriceRegistHandler.class,
            "validateBookValuePrice");
        
        //外国株式銘柄一覧ハンドラ
        regHandler(
            WEB3FeqAppPlugin.class,
            WEB3FeqProductListRequest.class,
            WEB3FeqProductListHandler.class,
            "getProductInformationList");
        
        //外国株式注文受付出来通知ハンドラ
        regHandler(
            WEB3FeqAppPlugin.class,
            WEB3FeqOrderAcceptExecNotifyRequest.class,
            WEB3FeqOrderAcceptExecutionNotifyHandler.class,
            "orderAcceptExecNotify");
        
        //管理者外国株式RCVDキュー照会ハンドラ
        regHandler(
            WEB3FeqAppPlugin.class,
            WEB3AdminFeqRcvdQueueReferenceInputRequest.class,
            WEB3AdminFeqRcvdQueueReferenceHandler.class,
            "getInputScreen");
        regHandler(
            WEB3FeqAppPlugin.class,
            WEB3AdminFeqRcvdQueueReferenceRequest.class,
            WEB3AdminFeqRcvdQueueReferenceHandler.class,
            "getListScreen");
        
        //管理者外国株式SENDキューリカバリハンドラ 
        regHandler(
            WEB3FeqAppPlugin.class,
            WEB3AdminFeqSendQueueRecoveryCompleteRequest.class,
            WEB3AdminFeqSendQueueRecoveryHandler.class,
            "submitUpdate");
        
        //管理者外国株式SENDキュー照会ハンドラ 
        regHandler(
            WEB3FeqAppPlugin.class,
            WEB3AdminFeqSendQueueReferenceInputRequest.class,
            WEB3AdminFeqSendQueueReferenceHandler.class,
            "getInputScreen");
        regHandler(
            WEB3FeqAppPlugin.class,
            WEB3AdminFeqSendQueueReferenceRequest.class,
            WEB3AdminFeqSendQueueReferenceHandler.class,
            "getListScreen");
        
        //管理者外国株式市場連動区分変更ハンドラ 
        regHandler(
            WEB3FeqAppPlugin.class,
            WEB3AdminFeqMarketLinkChangeInputRequest.class,
            WEB3AdminFeqMarketLinkChangeHandler.class,
            "getInputScreen");
        regHandler(
            WEB3FeqAppPlugin.class,
            WEB3AdminFeqMarketLinkChangeConfirmRequest.class,
            WEB3AdminFeqMarketLinkChangeHandler.class,
            "validateChange");
        regHandler(
            WEB3FeqAppPlugin.class,
            WEB3AdminFeqMarketLinkChangeCompleteRequest.class,
            WEB3AdminFeqMarketLinkChangeHandler.class,
            "submitChange");

        //外国株式為替ネッティングハンドラ
        regHandler(
            WEB3FeqAppPlugin.class,
            WEB3FeqNettingExchangeRequest.class,
            WEB3FeqNettingExchangeHandler.class,
            "nettingExchange");
    }
}
@
