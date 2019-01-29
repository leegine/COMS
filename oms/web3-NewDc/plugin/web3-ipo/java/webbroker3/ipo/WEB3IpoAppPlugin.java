head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.15.05.44.14;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	6f44d7efa104045;
filename	WEB3IpoAppPlugin.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : Webbroker3-IPO プラグイン(WEB3IpoAppPlugin.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2004/08/10 張威 (中訊) 新規作成
Revesion History : 2005/01/13 坂上 (SRA) >>>管理者銘柄新規登録、銘柄訂正のインタセプター登録
Revesion History : 2005/12/20 沈迪 (中訊) 仕様変更101~114
Revesion History : 2006/01/26 郭英（中訊）仕様変更・モデル118
*/

package webbroker3.ipo;

import com.fitechlabs.xtrade.kernel.boot.KernelPlugin;
import com.fitechlabs.xtrade.kernel.boot.Plugin;
import com.fitechlabs.xtrade.kernel.boot.Services;
import com.fitechlabs.xtrade.kernel.interceptor.TransactionalInterceptor;
import com.fitechlabs.xtrade.plugin.tc.gentrade.AlreadyInstalledException;
import com.fitechlabs.xtrade.plugin.tc.gentrade.FinApp;
import com.fitechlabs.xtrade.plugin.tc.gentrade.ProductTypeEnum;
import com.fitechlabs.xtrade.plugin.tc.gentrade.TradingModule;

import webbroker3.common.WEB3LogSysTimeInterceptor;
import webbroker3.ipo.data.WEB3IpoAccountDatabaseExtensions;
import webbroker3.ipo.data.WEB3IpoMasterDatabaseExtensions;
import webbroker3.ipo.handler.WEB3AdminIpoBookbuildingStateDownloadHandler;
import webbroker3.ipo.handler.WEB3AdminIpoCancelHandler;
import webbroker3.ipo.handler.WEB3AdminIpoLotHandler;
import webbroker3.ipo.handler.WEB3AdminIpoLotResultOfferDownloadHandler;
import webbroker3.ipo.handler.WEB3AdminIpoLotResultUploadHandler;
import webbroker3.ipo.handler.WEB3AdminIpoManagementDetailsHandler;
import webbroker3.ipo.handler.WEB3AdminIpoOfferStopResumeHandler;
import webbroker3.ipo.handler.WEB3AdminIpoProductChangeHandler;
import webbroker3.ipo.handler.WEB3AdminIpoProductDeleteHandler;
import webbroker3.ipo.handler.WEB3AdminIpoProductRegistrationHandler;
import webbroker3.ipo.handler.WEB3IpoBatoClientHandler;
import webbroker3.ipo.handler.WEB3IpoBookbuildingCancelHandler;
import webbroker3.ipo.handler.WEB3IpoBookbuildingChangeHandler;
import webbroker3.ipo.handler.WEB3IpoBookbuildingEnterHandler;
import webbroker3.ipo.handler.WEB3IpoBookbuildingOrderHandler;
import webbroker3.ipo.handler.WEB3IpoDeclineHandler;
import webbroker3.ipo.handler.WEB3IpoOfferHandler;
import webbroker3.ipo.handler.WEB3IpoOrderOfferListHandler;
import webbroker3.ipo.message.WEB3AdminIPOBookBuildingDemandMapRequest;
import webbroker3.ipo.message.WEB3AdminIPOBookBuildingDemandMapResponse;
import webbroker3.ipo.message.WEB3AdminIPOBookBuildingHistoryRequest;
import webbroker3.ipo.message.WEB3AdminIPOBookBuildingHistoryResponse;
import webbroker3.ipo.message.WEB3AdminIPOBookBuildingStateDownloadRequest;
import webbroker3.ipo.message.WEB3AdminIPOBookBuildingStateDownloadResponse;
import webbroker3.ipo.message.WEB3AdminIPOBookBuildingStateFileDownloadRequest;
import webbroker3.ipo.message.WEB3AdminIPOBookBuildingStateFileDownloadResponse;
import webbroker3.ipo.message.WEB3AdminIPOCancelCompleteRequest;
import webbroker3.ipo.message.WEB3AdminIPOCancelCompleteResponse;
import webbroker3.ipo.message.WEB3AdminIPOCancelConfirmRequest;
import webbroker3.ipo.message.WEB3AdminIPOCancelConfirmResponse;
import webbroker3.ipo.message.WEB3AdminIPOLotCompleteRequest;
import webbroker3.ipo.message.WEB3AdminIPOLotCompleteResponse;
import webbroker3.ipo.message.WEB3AdminIPOLotConfirmRequest;
import webbroker3.ipo.message.WEB3AdminIPOLotConfirmResponse;
import webbroker3.ipo.message.WEB3AdminIPOLotInputRequest;
import webbroker3.ipo.message.WEB3AdminIPOLotInputResponse;
import webbroker3.ipo.message.WEB3AdminIPOLotResultOfferDownloadRequest;
import webbroker3.ipo.message.WEB3AdminIPOLotResultOfferDownloadResponse;
import webbroker3.ipo.message.WEB3AdminIPOLotResultOfferFileDownloadRequest;
import webbroker3.ipo.message.WEB3AdminIPOLotResultOfferFileDownloadResponse;
import webbroker3.ipo.message.WEB3AdminIPOLotResultOfferListRequest;
import webbroker3.ipo.message.WEB3AdminIPOLotResultOfferListResponse;
import webbroker3.ipo.message.WEB3AdminIPOLotResultOfferRequest;
import webbroker3.ipo.message.WEB3AdminIPOLotResultOfferResponse;
import webbroker3.ipo.message.WEB3AdminIPOLotResultUploadCancelRequest;
import webbroker3.ipo.message.WEB3AdminIPOLotResultUploadCancelResponse;
import webbroker3.ipo.message.WEB3AdminIPOLotResultUploadCompleteRequest;
import webbroker3.ipo.message.WEB3AdminIPOLotResultUploadCompleteResponse;
import webbroker3.ipo.message.WEB3AdminIPOLotResultUploadConfirmRequest;
import webbroker3.ipo.message.WEB3AdminIPOLotResultUploadConfirmResponse;
import webbroker3.ipo.message.WEB3AdminIPOLotResultUploadInputRequest;
import webbroker3.ipo.message.WEB3AdminIPOLotResultUploadInputResponse;
import webbroker3.ipo.message.WEB3AdminIPOManagementRequest;
import webbroker3.ipo.message.WEB3AdminIPOManagementResponse;
import webbroker3.ipo.message.WEB3AdminIPOOfferStopResumeCompleteRequest;
import webbroker3.ipo.message.WEB3AdminIPOOfferStopResumeCompleteResponse;
import webbroker3.ipo.message.WEB3AdminIPOOfferStopResumeConfirmRequest;
import webbroker3.ipo.message.WEB3AdminIPOOfferStopResumeConfirmResponse;
import webbroker3.ipo.message.WEB3AdminIPOProductChangeCompleteRequest;
import webbroker3.ipo.message.WEB3AdminIPOProductChangeCompleteResponse;
import webbroker3.ipo.message.WEB3AdminIPOProductChangeConfirmRequest;
import webbroker3.ipo.message.WEB3AdminIPOProductChangeConfirmResponse;
import webbroker3.ipo.message.WEB3AdminIPOProductChangeInputRequest;
import webbroker3.ipo.message.WEB3AdminIPOProductChangeInputResponse;
import webbroker3.ipo.message.WEB3AdminIPOProductDeleteCompleteRequest;
import webbroker3.ipo.message.WEB3AdminIPOProductDeleteCompleteResponse;
import webbroker3.ipo.message.WEB3AdminIPOProductDeleteConfirmRequest;
import webbroker3.ipo.message.WEB3AdminIPOProductDeleteConfirmResponse;
import webbroker3.ipo.message.WEB3AdminIPOProductDetailsRequest;
import webbroker3.ipo.message.WEB3AdminIPOProductDetailsResponse;
import webbroker3.ipo.message.WEB3AdminIPOProductRegistrationCompleteRequest;
import webbroker3.ipo.message.WEB3AdminIPOProductRegistrationCompleteResponse;
import webbroker3.ipo.message.WEB3AdminIPOProductRegistrationConfirmRequest;
import webbroker3.ipo.message.WEB3AdminIPOProductRegistrationConfirmResponse;
import webbroker3.ipo.message.WEB3AdminIPOProductRegistrationInputRequest;
import webbroker3.ipo.message.WEB3AdminIPOProductRegistrationInputResponse;
import webbroker3.ipo.message.WEB3IPOBatoUrlRequest;
import webbroker3.ipo.message.WEB3IPOBatoUrlResponse;
import webbroker3.ipo.message.WEB3IPOBookBuildingCancelCompleteRequest;
import webbroker3.ipo.message.WEB3IPOBookBuildingCancelCompleteResponse;
import webbroker3.ipo.message.WEB3IPOBookBuildingCancelConfirmRequest;
import webbroker3.ipo.message.WEB3IPOBookBuildingCancelConfirmResponse;
import webbroker3.ipo.message.WEB3IPOBookBuildingChangeCompleteRequest;
import webbroker3.ipo.message.WEB3IPOBookBuildingChangeCompleteResponse;
import webbroker3.ipo.message.WEB3IPOBookBuildingChangeConfirmRequest;
import webbroker3.ipo.message.WEB3IPOBookBuildingChangeConfirmResponse;
import webbroker3.ipo.message.WEB3IPOBookBuildingChangeInputRequest;
import webbroker3.ipo.message.WEB3IPOBookBuildingChangeInputResponse;
import webbroker3.ipo.message.WEB3IPOBookBuildingDemandCompleteRequest;
import webbroker3.ipo.message.WEB3IPOBookBuildingDemandCompleteResponse;
import webbroker3.ipo.message.WEB3IPOBookBuildingDemandConfirmRequest;
import webbroker3.ipo.message.WEB3IPOBookBuildingDemandConfirmResponse;
import webbroker3.ipo.message.WEB3IPOBookBuildingDemandInputRequest;
import webbroker3.ipo.message.WEB3IPOBookBuildingDemandInputResponse;
import webbroker3.ipo.message.WEB3IPOBookBuildingEnterRequest;
import webbroker3.ipo.message.WEB3IPOBookBuildingEnterResponse;
import webbroker3.ipo.message.WEB3IPOBookBuildingHistoryRequest;
import webbroker3.ipo.message.WEB3IPOBookBuildingHistoryResponse;
import webbroker3.ipo.message.WEB3IPODeclineCompleteRequest;
import webbroker3.ipo.message.WEB3IPODeclineCompleteResponse;
import webbroker3.ipo.message.WEB3IPODeclineConfirmRequest;
import webbroker3.ipo.message.WEB3IPODeclineConfirmResponse;
import webbroker3.ipo.message.WEB3IPODemandCommonRequest;
import webbroker3.ipo.message.WEB3IPODemandCommonResponse;
import webbroker3.ipo.message.WEB3IPODemandOfferRequest;
import webbroker3.ipo.message.WEB3IPODemandOfferResponse;
import webbroker3.ipo.message.WEB3IPOLotCommonRequest;
import webbroker3.ipo.message.WEB3IPOLotCommonResponse;
import webbroker3.ipo.message.WEB3IPOOfferCommonResponse;
import webbroker3.ipo.message.WEB3IPOOfferCompleteRequest;
import webbroker3.ipo.message.WEB3IPOOfferCompleteResponse;
import webbroker3.ipo.message.WEB3IPOOfferConfirmRequest;
import webbroker3.ipo.message.WEB3IPOOfferConfirmResponse;
import webbroker3.ipo.message.WEB3IPOOfferInputRequest;
import webbroker3.ipo.message.WEB3IPOOfferInputResponse;
import webbroker3.ipo.message.WEB3IPOProductInfoRequest;
import webbroker3.ipo.message.WEB3IPOProductInfoResponse;
import webbroker3.ipo.service.delegate.WEB3AdminIpoBookbuildingStateDownloadService;
import webbroker3.ipo.service.delegate.WEB3AdminIpoCancelService;
import webbroker3.ipo.service.delegate.WEB3AdminIpoLotResultOfferDownloadService;
import webbroker3.ipo.service.delegate.WEB3AdminIpoLotResultUploadService;
import webbroker3.ipo.service.delegate.WEB3AdminIpoLotResultUploadUnitService;
import webbroker3.ipo.service.delegate.WEB3AdminIpoLotService;
import webbroker3.ipo.service.delegate.WEB3AdminIpoManagementDetailsService;
import webbroker3.ipo.service.delegate.WEB3AdminIpoOfferStopResumeService;
import webbroker3.ipo.service.delegate.WEB3AdminIpoProductChangeService;
import webbroker3.ipo.service.delegate.WEB3AdminIpoProductDeleteService;
import webbroker3.ipo.service.delegate.WEB3AdminIpoProductRegistrationService;
import webbroker3.ipo.service.delegate.WEB3IpoBatoClientService;
import webbroker3.ipo.service.delegate.WEB3IpoBookbuildingCancelService;
import webbroker3.ipo.service.delegate.WEB3IpoBookbuildingChangeService;
import webbroker3.ipo.service.delegate.WEB3IpoBookbuildingEnterService;
import webbroker3.ipo.service.delegate.WEB3IpoBookbuildingOrderService;
import webbroker3.ipo.service.delegate.WEB3IpoDeclineService;
import webbroker3.ipo.service.delegate.WEB3IpoOfferService;
import webbroker3.ipo.service.delegate.WEB3IpoOrderActionUnitService;
import webbroker3.ipo.service.delegate.WEB3IpoOrderOfferListService;
import webbroker3.ipo.service.delegate.WEB3IpoProductInfoService;
import webbroker3.ipo.service.delegate.WEB3IpoUploadActionUnitService;
import webbroker3.ipo.service.delegate.stdimpls.WEB3AdminIpoBookbuildingStateDownloadServiceImpl;
import webbroker3.ipo.service.delegate.stdimpls.WEB3AdminIpoCancelServiceImpl;
import webbroker3.ipo.service.delegate.stdimpls.WEB3AdminIpoLotResultOfferDownloadServiceImpl;
import webbroker3.ipo.service.delegate.stdimpls.WEB3AdminIpoLotResultUploadServiceImpl;
import webbroker3.ipo.service.delegate.stdimpls.WEB3AdminIpoLotResultUploadUnitServiceImpl;
import webbroker3.ipo.service.delegate.stdimpls.WEB3AdminIpoLotServiceImpl;
import webbroker3.ipo.service.delegate.stdimpls.WEB3AdminIpoManagementDetailsServiceImpl;
import webbroker3.ipo.service.delegate.stdimpls.WEB3AdminIpoOfferStopResumeServiceImpl;
import webbroker3.ipo.service.delegate.stdimpls.WEB3AdminIpoProductChangeServiceImpl;
import webbroker3.ipo.service.delegate.stdimpls.WEB3AdminIpoProductDeleteServiceImpl;
import webbroker3.ipo.service.delegate.stdimpls.WEB3AdminIpoProductRegistrationServiceImpl;
import webbroker3.ipo.service.delegate.stdimpls.WEB3IpoBatoClientServiceImpl;
import webbroker3.ipo.service.delegate.stdimpls.WEB3IpoBookbuildingCancelServiceImpl;
import webbroker3.ipo.service.delegate.stdimpls.WEB3IpoBookbuildingChangeServiceImpl;
import webbroker3.ipo.service.delegate.stdimpls.WEB3IpoBookbuildingEnterServiceImpl;
import webbroker3.ipo.service.delegate.stdimpls.WEB3IpoBookbuildingOrderServiceImpl;
import webbroker3.ipo.service.delegate.stdimpls.WEB3IpoDeclineServiceImpl;
import webbroker3.ipo.service.delegate.stdimpls.WEB3IpoOfferServiceImpl;
import webbroker3.ipo.service.delegate.stdimpls.WEB3IpoOrderActionUnitServiceImpl;
import webbroker3.ipo.service.delegate.stdimpls.WEB3IpoOrderOfferListServiceImpl;
import webbroker3.ipo.service.delegate.stdimpls.WEB3IpoProductInfoServiceImpl;
import webbroker3.ipo.service.delegate.stdimpls.WEB3IpoUploadActionUnitServiceImpl;
import webbroker3.util.WEB3LogUtility;

/**
 * Webbroker3-IPO プラグインクラス。
 *                                                                
 * @@author 張威
 * @@version 1.0
 */
public final class WEB3IpoAppPlugin extends Plugin
{
    /**
     * ログユーティリティ。
     */
    private static WEB3LogUtility log = WEB3LogUtility.getInstance(WEB3IpoAppPlugin.class);

    /**
     * コンストラクタ。
     */
    public WEB3IpoAppPlugin()
    {
        String STR_METHOD_NAME = " WEB3IpoAppPlugin()";
        log.entering(STR_METHOD_NAME);

        log.exiting(STR_METHOD_NAME);
    }

    /**
     * プラグインエントリーポイント。
     */
    public static void plug() throws Exception
    {
        String STR_METHOD_NAME = " plug()";
        log.entering(STR_METHOD_NAME);

        plug(WEB3IpoAppPlugin.class);

        log.exiting(STR_METHOD_NAME);
    }
    
    /**
     * プラグイン処理。
     */
    public static void onPlug() throws Exception
    {
        String STR_METHOD_NAME = " onPlug()";
        log.entering(STR_METHOD_NAME);
        
        // このプラグインより先に読み込む必要のあるプラグインの指定。
        // install the system plugins that we need
        KernelPlugin.plug();
        
        FinApp l_finApp = (FinApp)Services.getService(FinApp.class);
        
        // 拡張トランザクション・マネージャーは
        // オーバーライドメソッドが無いため拡張取引モジュールクラスを作成し
        // 拡張取引モジュールクラス内で設定

        try
        {
            log.debug("Installing TradingModule : web3-ipo: ENTER");
            l_finApp.installTradingModule(new WEB3IpoTradingModule());
            log.debug("Installed TradingModule : web3-ipo: END");
        }
        catch (AlreadyInstalledException l_ex)
        {
            log.debug(l_ex.getMessage());
        }
        
        TradingModule l_tradingModule = l_finApp.getTradingModule(ProductTypeEnum.IPO);

        // 拡張プロダクト・マネージャー
        l_tradingModule.overrideProductManager(new WEB3IpoProductManagerImpl());

        // 拡張注文マネージャ
        l_tradingModule.overrideOrderManager(new WEB3IpoOrderManagerImpl());

        // DatabaseExtensions のプラグイン処理 ----------------------
        WEB3IpoMasterDatabaseExtensions.plug();
        WEB3IpoAccountDatabaseExtensions.plug();

        // Service の登録処理 ----------------------
        
        //管理者IPOﾌﾞｯｸﾋﾞﾙﾃﾞｨﾝｸﾞ状況ﾀﾞｳﾝﾛｰﾄﾞｻｰﾋﾞｽ
        Services.registerService(WEB3AdminIpoBookbuildingStateDownloadService.class, new WEB3AdminIpoBookbuildingStateDownloadServiceImpl());

        //管理者IPO中止サービス
        Services.registerService(WEB3AdminIpoCancelService.class, new WEB3AdminIpoCancelServiceImpl());
        
        //管理者IPO抽選結果購入申込状況ﾀﾞｳﾝﾛｰﾄﾞｻｰﾋﾞｽ
        Services.registerService(WEB3AdminIpoLotResultOfferDownloadService.class, new WEB3AdminIpoLotResultOfferDownloadServiceImpl());

        //管理者IPO抽選結果ｱｯﾌﾟﾛｰﾄﾞｻｰﾋﾞｽ
        Services.registerService(WEB3AdminIpoLotResultUploadService.class, new WEB3AdminIpoLotResultUploadServiceImpl());

        //管理者IPO抽選結果アップロード１件サービス
        Services.registerService(WEB3AdminIpoLotResultUploadUnitService.class, new WEB3AdminIpoLotResultUploadUnitServiceImpl());

        //管理者IPO銘柄管理・詳細サービス
        Services.registerService(WEB3AdminIpoManagementDetailsService.class, new WEB3AdminIpoManagementDetailsServiceImpl());

        //管理者IPO募集停止再開サービス
        Services.registerService(WEB3AdminIpoOfferStopResumeService.class, new WEB3AdminIpoOfferStopResumeServiceImpl());

        //管理者IPO銘柄訂正サービス
        Services.registerService(WEB3AdminIpoProductChangeService.class, new WEB3AdminIpoProductChangeServiceImpl());

        //管理者IPO銘柄削除サービス
        Services.registerService(WEB3AdminIpoProductDeleteService.class, new WEB3AdminIpoProductDeleteServiceImpl());

        //管理者IPO銘柄登録サービス
        Services.registerService(WEB3AdminIpoProductRegistrationService.class, new WEB3AdminIpoProductRegistrationServiceImpl());

        //IPOブックビルディング取消サービス
        Services.registerService(WEB3IpoBookbuildingCancelService.class, new WEB3IpoBookbuildingCancelServiceImpl());

        //IPOブックビルディング訂正サービス
        Services.registerService(WEB3IpoBookbuildingChangeService.class, new WEB3IpoBookbuildingChangeServiceImpl());

        //IPOブックビルディング申告サービス
        Services.registerService(WEB3IpoBookbuildingOrderService.class, new WEB3IpoBookbuildingOrderServiceImpl());

        //IPOブックビルディング参加サービス
        Services.registerService(WEB3IpoBookbuildingEnterService.class, new WEB3IpoBookbuildingEnterServiceImpl());

        //IPO辞退サービス
        Services.registerService(WEB3IpoDeclineService.class, new WEB3IpoDeclineServiceImpl());

        //IPO申告・購入申込一覧
        Services.registerService(WEB3IpoOrderOfferListService.class, new WEB3IpoOrderOfferListServiceImpl());

        //IPO申告履歴明細作成サービス
        Services.registerService(WEB3IpoOrderActionUnitService.class, new WEB3IpoOrderActionUnitServiceImpl());

        //IPO購入申込サービス
        Services.registerService(WEB3IpoOfferService.class, new WEB3IpoOfferServiceImpl());

        //IPO銘柄情報作成サービス
        Services.registerService(WEB3IpoProductInfoService.class, new WEB3IpoProductInfoServiceImpl());

        //IPOアップロード履歴明細作成サービス
        Services.registerService(WEB3IpoUploadActionUnitService.class, new WEB3IpoUploadActionUnitServiceImpl());
        
        //管理者IPO抽選割当サービス
        Services.registerService(WEB3AdminIpoLotService.class, new WEB3AdminIpoLotServiceImpl());
        
        //IPO電子鳩接続サービス
        Services.registerService(WEB3IpoBatoClientService.class, new WEB3IpoBatoClientServiceImpl());
        
		//Service に ServiceInterceptor を設定する ----------------------

		//管理者IPOﾌﾞｯｸﾋﾞﾙﾃﾞｨﾝｸﾞ状況ﾀﾞｳﾝﾛｰﾄﾞｻｰﾋﾞｽ
//		  Services.addInterceptor(WEB3AdminIpoBookbuildingStateDownloadService.class, new WEB3IpoServiceInterceptor());

		//管理者IPO中止サービス
//		  Services.addInterceptor(WEB3AdminIpoCancelService.class, new WEB3IpoServiceInterceptor());

		//管理者IPO抽選結果購入申込状況ﾀﾞｳﾝﾛｰﾄﾞｻｰﾋﾞｽ 
		Services.addInterceptor(WEB3AdminIpoLotResultOfferDownloadService.class, new WEB3IpoServiceInterceptor());

		//管理者IPO銘柄管理・詳細サービス
//		  Services.addInterceptor(WEB3AdminIpoManagementDetailsService.class, new WEB3IpoServiceInterceptor());

		//管理者IPO募集停止再開サービス
//		  Services.addInterceptor(WEB3AdminIpoOfferStopResumeService.class, new WEB3IpoServiceInterceptor());

		//管理者IPO銘柄訂正サービス
		Services.addInterceptor(WEB3AdminIpoProductChangeService.class, new WEB3IpoServiceInterceptor());

		//管理者IPO銘柄削除サービス
//		  Services.addInterceptor(WEB3AdminIpoProductDeleteService.class, new WEB3IpoServiceInterceptor());

		//管理者IPO銘柄登録サービス
		Services.addInterceptor(WEB3AdminIpoProductRegistrationService.class, new WEB3IpoServiceInterceptor());

		//IPOブックビルディング取消サービス
		Services.addInterceptor(WEB3IpoBookbuildingCancelService.class, new WEB3IpoServiceInterceptor());

		//IPOブックビルディング訂正サービス
		Services.addInterceptor(WEB3IpoBookbuildingChangeService.class, new WEB3IpoServiceInterceptor());

		//IPOブックビルディング参加サービス
		Services.addInterceptor(WEB3IpoBookbuildingEnterService.class, new WEB3IpoServiceInterceptor());

		//IPOブックビルディング申告サービス
		Services.addInterceptor(WEB3IpoBookbuildingOrderService.class, new WEB3IpoServiceInterceptor());

		//IPO辞退サービス
		Services.addInterceptor(WEB3IpoDeclineService.class, new WEB3IpoServiceInterceptor());

		//IPO購入申込サービス
		Services.addInterceptor(WEB3IpoOfferService.class, new WEB3IpoServiceInterceptor());

		//IPO申告購入申込一覧サービス
		Services.addInterceptor(WEB3IpoOrderOfferListService.class, new WEB3IpoServiceInterceptor());

		//IPOアップロード履歴明細作成サービス
//		  Services.addInterceptor(WEB3IpoUploadActionUnitService.class, new WEB3IpoServiceInterceptor());

		//IPO申告履歴明細作成サービス
//		  Services.addInterceptor(WEB3IpoOrderActionUnitService.class, new WEB3IpoServiceInterceptor());

		//IPO銘柄情報作成サービス
//		  Services.addInterceptor(WEB3IpoProductInfoService.class, new WEB3IpoServiceInterceptor());

		//管理者IPO抽選結果ｱｯﾌﾟﾛｰﾄﾞUnitService
//		  Services.addInterceptor(WEB3AdminIpoLotResultUploadUnitService.class, new WEB3AdminIpoLotResultUploadInterceptor());

		//管理者IPO抽選結果ｱｯﾌﾟﾛｰﾄﾞｻｰﾋﾞｽ
		Services.addInterceptor(WEB3AdminIpoLotResultUploadService.class, new WEB3AdminIpoLotResultUploadInterceptor());
        
        //IPO電子鳩接続サービス
        Services.addInterceptor(WEB3IpoBatoClientService.class, new WEB3IpoBatoClientServiceInterceptor());

        // Service の Interceptor 設定処理 ----------------------
        // 自動トランザクション設定

        //管理者IPOﾌﾞｯｸﾋﾞﾙﾃﾞｨﾝｸﾞ状況ﾀﾞｳﾝﾛｰﾄﾞｻｰﾋﾞｽ
        Services.addInterceptor(
            WEB3AdminIpoBookbuildingStateDownloadService.class,
            new TransactionalInterceptor(TransactionalInterceptor.TX_JOIN_EXISTING));

        //管理者IPO中止サービス
        Services.addInterceptor(WEB3AdminIpoCancelService.class, new TransactionalInterceptor(TransactionalInterceptor.TX_JOIN_EXISTING));

        //管理者IPO抽選結果購入申込状況ﾀﾞｳﾝﾛｰﾄﾞｻｰﾋﾞｽ
        Services.addInterceptor(
            WEB3AdminIpoLotResultOfferDownloadService.class,
            new TransactionalInterceptor(TransactionalInterceptor.TX_JOIN_EXISTING));

        //管理者IPO抽選結果ｱｯﾌﾟﾛｰﾄﾞｻｰﾋﾞｽ
        Services.addInterceptor(WEB3AdminIpoLotResultUploadService.class, new TransactionalInterceptor(TransactionalInterceptor.TX_JOIN_EXISTING));

        //管理者IPO抽選結果アップロード１件サービス
        Services.addInterceptor(
            WEB3AdminIpoLotResultUploadUnitService.class,
            new TransactionalInterceptor(TransactionalInterceptor.TX_JOIN_EXISTING));

        //管理者IPO銘柄管理・詳細サービス
        Services.addInterceptor(WEB3AdminIpoManagementDetailsService.class, new TransactionalInterceptor(TransactionalInterceptor.TX_JOIN_EXISTING));

        //管理者IPO募集停止再開サービス
        Services.addInterceptor(WEB3AdminIpoOfferStopResumeService.class, new TransactionalInterceptor(TransactionalInterceptor.TX_JOIN_EXISTING));

        //管理者IPO銘柄訂正サービス
        Services.addInterceptor(WEB3AdminIpoProductChangeService.class, new TransactionalInterceptor(TransactionalInterceptor.TX_JOIN_EXISTING));

        //管理者IPO銘柄削除サービス
        Services.addInterceptor(WEB3AdminIpoProductDeleteService.class, new TransactionalInterceptor(TransactionalInterceptor.TX_JOIN_EXISTING));

        //管理者IPO銘柄登録サービス
        Services.addInterceptor(
            WEB3AdminIpoProductRegistrationService.class,
            new TransactionalInterceptor(TransactionalInterceptor.TX_JOIN_EXISTING));

        //IPOブックビルディング取消サービス
        Services.addInterceptor(WEB3IpoBookbuildingCancelService.class, new TransactionalInterceptor(TransactionalInterceptor.TX_JOIN_EXISTING));

        //IPOブックビルディング訂正サービス
        Services.addInterceptor(WEB3IpoBookbuildingChangeService.class, new TransactionalInterceptor(TransactionalInterceptor.TX_JOIN_EXISTING));

        //IPOブックビルディング申告サービス
        Services.addInterceptor(WEB3IpoBookbuildingOrderService.class, new TransactionalInterceptor(TransactionalInterceptor.TX_JOIN_EXISTING));

        //IPOブックビルディング参加サービス
        Services.addInterceptor(WEB3IpoBookbuildingEnterService.class, new TransactionalInterceptor(TransactionalInterceptor.TX_JOIN_EXISTING));

        //IPO辞退サービス
        Services.addInterceptor(WEB3IpoDeclineService.class, new TransactionalInterceptor(TransactionalInterceptor.TX_JOIN_EXISTING));

        //IPO申告・購入申込一覧
        Services.addInterceptor(WEB3IpoOrderOfferListService.class, new TransactionalInterceptor(TransactionalInterceptor.TX_JOIN_EXISTING));

        //IPO申告履歴明細作成サービス
//        Services.addInterceptor(WEB3IpoOrderActionUnitService.class, new TransactionalInterceptor(TransactionalInterceptor.TX_JOIN_EXISTING));

        //IPO購入申込サービス
        Services.addInterceptor(WEB3IpoOfferService.class, new TransactionalInterceptor(TransactionalInterceptor.TX_JOIN_EXISTING));

        //IPO銘柄情報作成サービス
//        Services.addInterceptor(WEB3IpoProductInfoService.class, new TransactionalInterceptor(TransactionalInterceptor.TX_JOIN_EXISTING));

        //IPOアップロード履歴明細作成サービス
//        Services.addInterceptor(WEB3IpoUploadActionUnitService.class, new TransactionalInterceptor(TransactionalInterceptor.TX_JOIN_EXISTING));

        //IPO電子鳩接続サービス
        Services.addInterceptor(WEB3IpoBatoClientService.class, new TransactionalInterceptor(TransactionalInterceptor.TX_JOIN_EXISTING));

        // Service.execute()呼び出し前後に  ----------------------
        // 処理開始時刻と処理終了時刻をログ出力するように設定する

        //管理者IPOﾌﾞｯｸﾋﾞﾙﾃﾞｨﾝｸﾞ状況ﾀﾞｳﾝﾛｰﾄﾞｻｰﾋﾞｽ
        Services.addInterceptor(WEB3AdminIpoBookbuildingStateDownloadService.class, new WEB3LogSysTimeInterceptor());

        //管理者IPO中止サービス
        Services.addInterceptor(WEB3AdminIpoCancelService.class, new WEB3LogSysTimeInterceptor());

        //管理者IPO抽選結果購入申込状況ﾀﾞｳﾝﾛｰﾄﾞｻｰﾋﾞｽ
        Services.addInterceptor(WEB3AdminIpoLotResultOfferDownloadService.class, new WEB3LogSysTimeInterceptor());

        //管理者IPO抽選結果ｱｯﾌﾟﾛｰﾄﾞｻｰﾋﾞｽ
        Services.addInterceptor(WEB3AdminIpoLotResultUploadService.class, new WEB3LogSysTimeInterceptor());

        //管理者IPO抽選結果アップロード１件サービス
        Services.addInterceptor(WEB3AdminIpoLotResultUploadUnitService.class, new WEB3LogSysTimeInterceptor());

        //管理者IPO銘柄管理・詳細サービス
        Services.addInterceptor(WEB3AdminIpoManagementDetailsService.class, new WEB3LogSysTimeInterceptor());

        //管理者IPO募集停止再開サービス
        Services.addInterceptor(WEB3AdminIpoOfferStopResumeService.class, new WEB3LogSysTimeInterceptor());

        //管理者IPO銘柄訂正サービス
        Services.addInterceptor(WEB3AdminIpoProductChangeService.class, new WEB3LogSysTimeInterceptor());

        //管理者IPO銘柄削除サービス
        Services.addInterceptor(WEB3AdminIpoProductDeleteService.class, new WEB3LogSysTimeInterceptor());

        //管理者IPO銘柄登録サービス
        Services.addInterceptor(WEB3AdminIpoProductRegistrationService.class, new WEB3LogSysTimeInterceptor());

        //IPOブックビルディング取消サービス
        Services.addInterceptor(WEB3IpoBookbuildingCancelService.class, new WEB3LogSysTimeInterceptor());

        //IPOブックビルディング訂正サービス
        Services.addInterceptor(WEB3IpoBookbuildingChangeService.class, new WEB3LogSysTimeInterceptor());

        //IPOブックビルディング申告サービス
        Services.addInterceptor(WEB3IpoBookbuildingOrderService.class, new WEB3LogSysTimeInterceptor());

        //IPOブックビルディング参加サービス
        Services.addInterceptor(WEB3IpoBookbuildingEnterService.class, new WEB3LogSysTimeInterceptor());

        //IPO辞退サービス
        Services.addInterceptor(WEB3IpoDeclineService.class, new WEB3LogSysTimeInterceptor());

        //IPO申告・購入申込一覧
        Services.addInterceptor(WEB3IpoOrderOfferListService.class, new WEB3LogSysTimeInterceptor());

        //IPO申告履歴明細作成サービス
        Services.addInterceptor(WEB3IpoOrderActionUnitService.class, new WEB3LogSysTimeInterceptor());

        //IPO購入申込サービス
        Services.addInterceptor(WEB3IpoOfferService.class, new WEB3LogSysTimeInterceptor());

        //IPO銘柄情報作成サービス
        Services.addInterceptor(WEB3IpoProductInfoService.class, new WEB3LogSysTimeInterceptor());

        //IPOアップロード履歴明細作成サービス
        Services.addInterceptor(WEB3IpoUploadActionUnitService.class, new WEB3LogSysTimeInterceptor());
        
        //管理者IPO抽選割当サービス
        Services.addInterceptor(WEB3AdminIpoLotService.class, new WEB3AdminIpoLotInterceptor());
        
        //IPO電子鳩接続サービス
        Services.addInterceptor(WEB3IpoBatoClientService.class, new WEB3LogSysTimeInterceptor());

        // Message の登録処理 ----------------------

        //IPOブックビルディング参加リクエスト
        regClass(WEB3IPOBookBuildingEnterRequest.class);
        //IPOブックビルディング参加レスポンス
        regClass(WEB3IPOBookBuildingEnterResponse.class);

        //IPOブックビルディング取消確認リクエスト
        regClass(WEB3IPOBookBuildingCancelConfirmRequest.class);
        //IPOブックビルディング取消確認レスポンス
        regClass(WEB3IPOBookBuildingCancelConfirmResponse.class);

        //IPOブックビルディング取消完了リクエスト
        regClass(WEB3IPOBookBuildingCancelCompleteRequest.class);
        //IPOブックビルディング取消完了レスポンス
        regClass(WEB3IPOBookBuildingCancelCompleteResponse.class);

        //IPOブックビルディング申告確認リクエスト
        regClass(WEB3IPOBookBuildingDemandConfirmRequest.class);
        //IPOブックビルディング申告確認レスポンス
        regClass(WEB3IPOBookBuildingDemandConfirmResponse.class);

        //IPOブックビルディング申告完了リクエスト
        regClass(WEB3IPOBookBuildingDemandCompleteRequest.class);
        //IPOブックビルディング申告完了レスポンス
        regClass(WEB3IPOBookBuildingDemandCompleteResponse.class);

        //IPOブックビルディング申告入力リクエスト
        regClass(WEB3IPOBookBuildingDemandInputRequest.class);
        //IPOブックビルディング申告入力レスポンス
        regClass(WEB3IPOBookBuildingDemandInputResponse.class);

        //IPOブックビルディング申告履歴リクエスト
        regClass(WEB3IPOBookBuildingHistoryRequest.class);
        //IPOブックビルディング申告履歴レスポンス
        regClass(WEB3IPOBookBuildingHistoryResponse.class);

        //IPOブックビルディング訂正確認リクエスト
        regClass(WEB3IPOBookBuildingChangeConfirmRequest.class);
        //IPOブックビルディング訂正確認レスポンス
        regClass(WEB3IPOBookBuildingChangeConfirmResponse.class);

        //IPOブックビルディング訂正完了リクエスト
        regClass(WEB3IPOBookBuildingChangeCompleteRequest.class);
        //IPOブックビルディング訂正完了レスポンス
        regClass(WEB3IPOBookBuildingChangeCompleteResponse.class);

        //IPOブックビルディング訂正入力リクエスト
        regClass(WEB3IPOBookBuildingChangeInputRequest.class);
        //IPOブックビルディング訂正入力レスポンス
        regClass(WEB3IPOBookBuildingChangeInputResponse.class);

        //IPO個別銘柄情報リクエスト
        regClass(WEB3IPOProductInfoRequest.class);
        //IPO個別銘柄情報レスポンス
        regClass(WEB3IPOProductInfoResponse.class);

        //IPO購入申込確認リクエスト
        regClass(WEB3IPOOfferConfirmRequest.class);
        //IPO購入申込確認レスポンス
        regClass(WEB3IPOOfferConfirmResponse.class);

        //IPO購入申込完了リクエスト
        regClass(WEB3IPOOfferCompleteRequest.class);
        //IPO購入申込完了レスポンス
        regClass(WEB3IPOOfferCompleteResponse.class);

        //IPO購入申込共通レスポンス
        regClass(WEB3IPOOfferCommonResponse.class);

        //IPO購入申込入力リクエスト
        regClass(WEB3IPOOfferInputRequest.class);
        //IPO購入申込入力レスポンス
        regClass(WEB3IPOOfferInputResponse.class);

        //IPO辞退確認リクエスト
        regClass(WEB3IPODeclineConfirmRequest.class);
        //IPO辞退確認レスポンス
        regClass(WEB3IPODeclineConfirmResponse.class);

        //IPO辞退完了レスポンス
        regClass(WEB3IPODeclineCompleteRequest.class);
        //IPO辞退完了リクエスト
        regClass(WEB3IPODeclineCompleteResponse.class);

        //IPO申告共通レスポンス
        regClass(WEB3IPODemandCommonResponse.class);
        //IPO申告共通リクエスト
        regClass(WEB3IPODemandCommonRequest.class);

        //IPO申告購入申込リクエスト
        regClass(WEB3IPODemandOfferRequest.class);
        //IPO申告購入申込レスポンス
        regClass(WEB3IPODemandOfferResponse.class);

        //管理者IPOﾌﾞｯｸﾋﾞﾙﾃﾞｨﾝｸﾞ状況ﾀﾞｳﾝﾛｰﾄﾞﾘｸｴｽﾄ
        regClass(WEB3AdminIPOBookBuildingStateDownloadRequest.class);
        //管理者IPOﾌﾞｯｸﾋﾞﾙﾃﾞｨﾝｸﾞ状況ﾀﾞｳﾝﾛｰﾄﾞﾚｽﾎﾟﾝｽ
        regClass(WEB3AdminIPOBookBuildingStateDownloadResponse.class);

        //管理者IPOﾌﾞｯｸﾋﾞﾙﾃﾞｨﾝｸﾞ状況ﾌｧｲﾙﾀﾞｳﾝﾛｰﾄﾞﾘｸｴｽﾄ
        regClass(WEB3AdminIPOBookBuildingStateFileDownloadRequest.class);
        //管理者IPOﾌﾞｯｸﾋﾞﾙﾃﾞｨﾝｸﾞ状況ﾌｧｲﾙﾀﾞｳﾝﾛｰﾄﾞﾚｽﾎﾟﾝｽ
        regClass(WEB3AdminIPOBookBuildingStateFileDownloadResponse.class);

        //管理者IPOﾌﾞｯｸﾋﾞﾙﾃﾞｨﾝｸﾞ申告分布図ﾘｸｴｽﾄ
        regClass(WEB3AdminIPOBookBuildingDemandMapRequest.class);
        //管理者IPOﾌﾞｯｸﾋﾞﾙﾃﾞｨﾝｸﾞ申告分布図ﾚｽﾎﾟﾝｽ
        regClass(WEB3AdminIPOBookBuildingDemandMapResponse.class);

        //管理者IPOﾌﾞｯｸﾋﾞﾙﾃﾞｨﾝｸﾞ申告履歴ﾘｸｴｽﾄ
        regClass(WEB3AdminIPOBookBuildingHistoryRequest.class);
        //管理者IPOﾌﾞｯｸﾋﾞﾙﾃﾞｨﾝｸﾞ申告履歴ﾚｽﾎﾟﾝｽ
        regClass(WEB3AdminIPOBookBuildingHistoryResponse.class);

        //管理者IPO中止確認リクエスト
        regClass(WEB3AdminIPOCancelConfirmRequest.class);
        //管理者IPO中止確認レスポンス
        regClass(WEB3AdminIPOCancelConfirmResponse.class);

        //管理者IPO中止完了リクエスト
        regClass(WEB3AdminIPOCancelCompleteRequest.class);
        //管理者IPO中止完了レスポンス
        regClass(WEB3AdminIPOCancelCompleteResponse.class);

        //管理者IPO抽選結果ｱｯﾌﾟﾛｰﾄﾞ確認ﾘｸｴｽﾄ
        regClass(WEB3AdminIPOLotResultUploadConfirmRequest.class);
        //管理者IPO抽選結果ｱｯﾌﾟﾛｰﾄﾞ確認ﾚｽﾎﾟﾝｽ
        regClass(WEB3AdminIPOLotResultUploadConfirmResponse.class);

        //管理者IPO抽選結果ｱｯﾌﾟﾛｰﾄﾞ完了ﾘｸｴｽﾄ
        regClass(WEB3AdminIPOLotResultUploadCompleteRequest.class);
        //管理者IPO抽選結果ｱｯﾌﾟﾛｰﾄﾞ完了ﾚｽﾎﾟﾝｽ
        regClass(WEB3AdminIPOLotResultUploadCompleteResponse.class);

        //管理者IPO抽選結果ｱｯﾌﾟﾛｰﾄﾞ中止ﾘｸｴｽﾄ
        regClass(WEB3AdminIPOLotResultUploadCancelRequest.class);
        //管理者IPO抽選結果ｱｯﾌﾟﾛｰﾄﾞ中止ﾚｽﾎﾟﾝｽ
        regClass(WEB3AdminIPOLotResultUploadCancelResponse.class);

        //管理者IPO抽選結果ｱｯﾌﾟﾛｰﾄﾞ入力ﾘｸｴｽﾄ
        regClass(WEB3AdminIPOLotResultUploadInputRequest.class);
        //管理者IPO抽選結果ｱｯﾌﾟﾛｰﾄﾞ入力ﾚｽﾎﾟﾝｽ
        regClass(WEB3AdminIPOLotResultUploadInputResponse.class);

        //管理者IPO抽選結果購入申込状況ﾀﾞｳﾝﾛｰﾄﾞﾘｸｴｽﾄ
        regClass(WEB3AdminIPOLotResultOfferDownloadRequest.class);
        //管理者IPO抽選結果購入申込状況ﾀﾞｳﾝﾛｰﾄﾞﾚｽﾎﾟﾝｽ
        regClass(WEB3AdminIPOLotResultOfferDownloadResponse.class);

        //管理者IPO抽選結果購入申込状況ﾌｧｲﾙﾀﾞｳﾝﾛｰﾄﾞﾞﾘｸｴｽﾄ
        regClass(WEB3AdminIPOLotResultOfferFileDownloadRequest.class);
        //管理者IPO抽選結果購入申込状況ﾌｧｲﾙﾀﾞｳﾝﾛｰﾄﾞﾚｽﾎﾟﾝｽ
        regClass(WEB3AdminIPOLotResultOfferFileDownloadResponse.class);

        //管理者IPO抽選結果購入申込状況ﾘｸｴｽﾄ
        regClass(WEB3AdminIPOLotResultOfferRequest.class);
        //管理者IPO抽選結果購入申込状況ﾚｽﾎﾟﾝｽ
        regClass(WEB3AdminIPOLotResultOfferResponse.class);

        //管理者IPO抽選結果購入申込状況一覧ﾘｸｴｽﾄ
        regClass(WEB3AdminIPOLotResultOfferListRequest.class);
        //管理者IPO抽選結果購入申込状況一覧ﾚｽﾎﾟﾝｽ
        regClass(WEB3AdminIPOLotResultOfferListResponse.class);

        //管理者IPO募集停止再開確認リクエスト
        regClass(WEB3AdminIPOOfferStopResumeConfirmRequest.class);
        //管理者IPO募集停止再開確認レスポンス
        regClass(WEB3AdminIPOOfferStopResumeConfirmResponse.class);

        //管理者IPO募集停止再開完了リクエスト 
        regClass(WEB3AdminIPOOfferStopResumeCompleteRequest.class);
        //管理者IPO募集停止再開完了レスポンス
        regClass(WEB3AdminIPOOfferStopResumeCompleteResponse.class);

        //管理者IPO銘柄管理リクエスト
        regClass(WEB3AdminIPOManagementRequest.class);
        //管理者IPO銘柄管理レスポンス
        regClass(WEB3AdminIPOManagementResponse.class);

        //管理者IPO銘柄削除確認リクエスト
        regClass(WEB3AdminIPOProductDeleteConfirmRequest.class);
        //管理者IPO銘柄削除確認レスポンス
        regClass(WEB3AdminIPOProductDeleteConfirmResponse.class);

        //管理者IPO銘柄削除完了リクエスト
        regClass(WEB3AdminIPOProductDeleteCompleteRequest.class);
        //管理者IPO銘柄削除完了レスポンス
        regClass(WEB3AdminIPOProductDeleteCompleteResponse.class);

        //管理者IPO銘柄詳細リクエスト
        regClass(WEB3AdminIPOProductDetailsRequest.class);
        //管理者IPO銘柄詳細レスポンス
        regClass(WEB3AdminIPOProductDetailsResponse.class);

        //管理者IPO銘柄新規登録確認リクエスト
        regClass(WEB3AdminIPOProductRegistrationConfirmRequest.class);
        //管理者IPO銘柄新規登録確認レスポンス
        regClass(WEB3AdminIPOProductRegistrationConfirmResponse.class);

        //管理者IPO銘柄新規登録完了リクエスト
        regClass(WEB3AdminIPOProductRegistrationCompleteRequest.class);
        //管理者IPO銘柄新規登録完了レスポンス
        regClass(WEB3AdminIPOProductRegistrationCompleteResponse.class);

        //管理者IPO銘柄新規登録入力リクエスト
        regClass(WEB3AdminIPOProductRegistrationInputRequest.class);
        //管理者IPO銘柄新規登録入力レスポンス
        regClass(WEB3AdminIPOProductRegistrationInputResponse.class);

        //管理者IPO銘柄訂正確認リクエスト
        regClass(WEB3AdminIPOProductChangeConfirmRequest.class);
        //管理者IPO銘柄訂正確認レスポンス
        regClass(WEB3AdminIPOProductChangeConfirmResponse.class);

        //管理者IPO銘柄訂正完了リクエスト
        regClass(WEB3AdminIPOProductChangeCompleteRequest.class);
        //管理者IPO銘柄訂正完了レスポンス
        regClass(WEB3AdminIPOProductChangeCompleteResponse.class);

        //管理者IPO銘柄訂正入力リクエスト
        regClass(WEB3AdminIPOProductChangeInputRequest.class);
        //管理者IPO銘柄訂正入力レスポンス
        regClass(WEB3AdminIPOProductChangeInputResponse.class);

        //管理者IPO抽選割当入力リクエスト
        regClass(WEB3AdminIPOLotInputRequest.class);
        //管理者IPO抽選割当入力レスポンス
        regClass(WEB3AdminIPOLotInputResponse.class);
        //管理者IPO抽選割当確認リクエスト
        regClass(WEB3AdminIPOLotConfirmRequest.class);
        //管理者IPO抽選割当確認レスポンス
        regClass(WEB3AdminIPOLotConfirmResponse.class);
        //管理者IPO抽選割当完了リクエスト
        regClass(WEB3AdminIPOLotCompleteRequest.class);
        //管理者IPO抽選割当完了レスポンス
        regClass(WEB3AdminIPOLotCompleteResponse.class);
        //管理者IPO抽選割当共通リクエスト
        regClass(WEB3IPOLotCommonRequest.class);
        //管理者IPO抽選割当共通レスポンス
        regClass(WEB3IPOLotCommonResponse.class);
        
        //IPO電子鳩URL取得リクエスト
        regClass(WEB3IPOBatoUrlRequest.class);
        //IPO電子鳩URL取得レスポンス
        regClass(WEB3IPOBatoUrlResponse.class);

        //Handler の登録処理 ----------------------

        //管理者IPOﾌﾞｯｸﾋﾞﾙﾃﾞｨﾝｸﾞ状況ﾀﾞｳﾝﾛｰﾄﾞ 用ハンドラーの登録
        regHandler(
            WEB3AdminIPOBookBuildingHistoryRequest.class,
            WEB3AdminIpoBookbuildingStateDownloadHandler.class,
            "bookbuildingOrderActionIndication");

        //管理者IPOﾌﾞｯｸﾋﾞﾙﾃﾞｨﾝｸﾞ状況ﾀﾞｳﾝﾛｰﾄﾞ 用ハンドラーの登録
        regHandler(
            WEB3AdminIPOBookBuildingStateFileDownloadRequest.class,
            WEB3AdminIpoBookbuildingStateDownloadHandler.class,
            "bookbuildingStateDownload");

        //管理者IPOﾌﾞｯｸﾋﾞﾙﾃﾞｨﾝｸﾞ状況ﾀﾞｳﾝﾛｰﾄﾞ 用ハンドラーの登録
        regHandler(
            WEB3AdminIPOBookBuildingStateDownloadRequest.class,
            WEB3AdminIpoBookbuildingStateDownloadHandler.class,
            "bookbuildingStateDownloadScreenIndication");

        //管理者IPOﾌﾞｯｸﾋﾞﾙﾃﾞｨﾝｸﾞ状況ﾀﾞｳﾝﾛｰﾄﾞ 用ハンドラーの登録
        regHandler(
            WEB3AdminIPOBookBuildingDemandMapRequest.class,
            WEB3AdminIpoBookbuildingStateDownloadHandler.class,
            "orderDistributionChartScreenIndication");

        //管理者IPO中止 用ハンドラーの登録
        regHandler(WEB3AdminIPOCancelCompleteRequest.class, WEB3AdminIpoCancelHandler.class, "ipoCancelComplete");

        //管理者IPO中止 用ハンドラーの登録
        regHandler(WEB3AdminIPOCancelConfirmRequest.class, WEB3AdminIpoCancelHandler.class, "ipoCancelConfirm");

        //管理者IPO抽選結果購入申込状況ﾀﾞｳﾝﾛｰﾄﾞ 用ハンドラーの登録
        regHandler(
            WEB3AdminIPOLotResultOfferFileDownloadRequest.class,
            WEB3AdminIpoLotResultOfferDownloadHandler.class,
            "lotResultOfferStateDownload");

        //管理者IPO抽選結果購入申込状況ﾀﾞｳﾝﾛｰﾄﾞ 用ハンドラーの登録
        regHandler(
            WEB3AdminIPOLotResultOfferDownloadRequest.class,
            WEB3AdminIpoLotResultOfferDownloadHandler.class,
            "lotResultOfferStateDownloadScreenIndication");

        //管理者IPO抽選結果購入申込状況ﾀﾞｳﾝﾛｰﾄﾞ 用ハンドラーの登録
        regHandler(WEB3AdminIPOLotResultOfferRequest.class, WEB3AdminIpoLotResultOfferDownloadHandler.class, "lotResultOfferStateIndication");

        //管理者IPO抽選結果購入申込状況ﾀﾞｳﾝﾛｰﾄﾞ 用ハンドラーの登録
        regHandler(WEB3AdminIPOLotResultOfferListRequest.class, WEB3AdminIpoLotResultOfferDownloadHandler.class, "lotResultOfferStateListIndication");

        //管理者IPO抽選結果ｱｯﾌﾟﾛｰﾄﾞ 用ハンドラーの登録
        regHandler(WEB3AdminIPOLotResultUploadCompleteRequest.class, WEB3AdminIpoLotResultUploadHandler.class, "lotResultUpload");

        //管理者IPO抽選結果ｱｯﾌﾟﾛｰﾄﾞ 用ハンドラーの登録
        regHandler(WEB3AdminIPOLotResultUploadCancelRequest.class, WEB3AdminIpoLotResultUploadHandler.class, "lotResultUploadCancel");

        //管理者IPO抽選結果ｱｯﾌﾟﾛｰﾄﾞ 用ハンドラーの登録
        regHandler(WEB3AdminIPOLotResultUploadConfirmRequest.class, WEB3AdminIpoLotResultUploadHandler.class, "lotResultUploadFileConfirm");

        //管理者IPO抽選結果ｱｯﾌﾟﾛｰﾄﾞ 用ハンドラーの登録
        regHandler(WEB3AdminIPOLotResultUploadInputRequest.class, WEB3AdminIpoLotResultUploadHandler.class, "lotResultUploadScreenIndication");

        //管理者IPO銘柄管理・詳細 用ハンドラーの登録
        regHandler(WEB3AdminIPOProductDetailsRequest.class, WEB3AdminIpoManagementDetailsHandler.class, "productDetailsIndication");

        //管理者IPO銘柄管理・詳細 用ハンドラーの登録
        regHandler(WEB3AdminIPOManagementRequest.class, WEB3AdminIpoManagementDetailsHandler.class, "productManagement");

        //管理者IPO募集停止再開 用ハンドラーの登録
        regHandler(WEB3AdminIPOOfferStopResumeCompleteRequest.class, WEB3AdminIpoOfferStopResumeHandler.class, "offerStopResumeComplete");

        //管理者IPO募集停止再開 用ハンドラーの登録
        regHandler(WEB3AdminIPOOfferStopResumeConfirmRequest.class, WEB3AdminIpoOfferStopResumeHandler.class, "offerStopResumeConfirm");

        //管理者IPO銘柄訂正 用ハンドラーの登録
        regHandler(WEB3AdminIPOProductChangeInputRequest.class, WEB3AdminIpoProductChangeHandler.class, "inputScreenIndication");

        //管理者IPO銘柄訂正 用ハンドラーの登録
        regHandler(WEB3AdminIPOProductChangeCompleteRequest.class, WEB3AdminIpoProductChangeHandler.class, "productChangeComplete");

        //管理者IPO銘柄訂正 用ハンドラーの登録
        regHandler(WEB3AdminIPOProductChangeConfirmRequest.class, WEB3AdminIpoProductChangeHandler.class, "productChangeConfirm");

        //管理者IPO銘柄削除 用ハンドラーの登録
        regHandler(WEB3AdminIPOProductDeleteCompleteRequest.class, WEB3AdminIpoProductDeleteHandler.class, "productDeleteComplete");

        //管理者IPO銘柄削除 用ハンドラーの登録
        regHandler(WEB3AdminIPOProductDeleteConfirmRequest.class, WEB3AdminIpoProductDeleteHandler.class, "productDeleteConfirm");

        //管理者IPO銘柄登録 用ハンドラーの登録
        regHandler(WEB3AdminIPOProductRegistrationInputRequest.class, WEB3AdminIpoProductRegistrationHandler.class, "inputScreenIndication");

        //管理者IPO銘柄登録 用ハンドラーの登録
        regHandler(WEB3AdminIPOProductRegistrationCompleteRequest.class, WEB3AdminIpoProductRegistrationHandler.class, "productRegistrationComplete");

        //管理者IPO銘柄登録 用ハンドラーの登録
        regHandler(WEB3AdminIPOProductRegistrationConfirmRequest.class, WEB3AdminIpoProductRegistrationHandler.class, "productRegistrationConfirm");

        //IPOブックビルディング取消 用ハンドラーの登録
        regHandler(WEB3IPOBookBuildingCancelCompleteRequest.class, WEB3IpoBookbuildingCancelHandler.class, "bookbuildingCancelComplete");

        //IPOブックビルディング取消 用ハンドラーの登録
        regHandler(WEB3IPOBookBuildingCancelConfirmRequest.class, WEB3IpoBookbuildingCancelHandler.class, "bookbuildingCancelConfirm");

        //IPOブックビルディング訂正 用ハンドラーの登録
        regHandler(WEB3IPOBookBuildingChangeCompleteRequest.class, WEB3IpoBookbuildingChangeHandler.class, "bookbuildingChangeComplete");

        //IPOブックビルディング訂正 用ハンドラーの登録
        regHandler(WEB3IPOBookBuildingChangeConfirmRequest.class, WEB3IpoBookbuildingChangeHandler.class, "bookbuildingChangeConfirm");

        //IPOブックビルディング訂正 用ハンドラーの登録
        regHandler(WEB3IPOBookBuildingChangeInputRequest.class, WEB3IpoBookbuildingChangeHandler.class, "inputScreenIndication");

        //IPOブックビルディング申告 用ハンドラーの登録
        regHandler(WEB3IPOBookBuildingDemandCompleteRequest.class, WEB3IpoBookbuildingOrderHandler.class, "bookbuildingOrderComplete");

        //IPOブックビルディング申告 用ハンドラーの登録
        regHandler(WEB3IPOBookBuildingDemandConfirmRequest.class, WEB3IpoBookbuildingOrderHandler.class, "bookbuildingOrderConfirm");

        //IPOブックビルディング申告 用ハンドラーの登録
        regHandler(WEB3IPOBookBuildingDemandInputRequest.class, WEB3IpoBookbuildingOrderHandler.class, "inputScreenIndication");

        //IPOブックビルディング参加 用ハンドラーの登録
        regHandler(WEB3IPOBookBuildingEnterRequest.class, WEB3IpoBookbuildingEnterHandler.class, "bookbuildingEnter");

        //IPOブックビルディング参加 用ハンドラーの登録
        regHandler(WEB3IPOProductInfoRequest.class, WEB3IpoBookbuildingEnterHandler.class, "individualProductInfoIndication");

        //IPO辞退 用ハンドラーの登録
        regHandler(WEB3IPODeclineCompleteRequest.class, WEB3IpoDeclineHandler.class, "declineComplete");

        //IPO辞退 用ハンドラーの登録
        regHandler(WEB3IPODeclineConfirmRequest.class, WEB3IpoDeclineHandler.class, "declineConfirm");

        //IPO申告・購入申込一覧 用ハンドラーの登録
        regHandler(WEB3IPOBookBuildingHistoryRequest.class, WEB3IpoOrderOfferListHandler.class, "bookbuildingAction");

        //IPO申告・購入申込一覧 用ハンドラーの登録
        regHandler(WEB3IPODemandOfferRequest.class, WEB3IpoOrderOfferListHandler.class, "orderOfferList");

        //IPO購入申込 用ハンドラーの登録
        regHandler(WEB3IPOOfferCompleteRequest.class, WEB3IpoOfferHandler.class, "offerComplete");

        //IPO購入申込 用ハンドラーの登録
        regHandler(WEB3IPOOfferConfirmRequest.class, WEB3IpoOfferHandler.class, "offerConfirm");

        //IPO購入申込 用ハンドラーの登録
        regHandler(WEB3IPOOfferInputRequest.class, WEB3IpoOfferHandler.class, "offerInput");
        
        //管理者IPO抽選割当 用ハンドラーの登録
        regHandler(WEB3AdminIPOLotInputRequest.class, WEB3AdminIpoLotHandler.class, "getLotInput");
        
        //管理者IPO抽選割当 用ハンドラーの登録
        regHandler(WEB3AdminIPOLotConfirmRequest.class, WEB3AdminIpoLotHandler.class, "getLotConfirm");
        
        //管理者IPO抽選割当 用ハンドラーの登録
        regHandler(WEB3AdminIPOLotCompleteRequest.class, WEB3AdminIpoLotHandler.class, "getLotComplete");
        
        //IPO電子鳩接続サービス
        regHandler(WEB3IPOBatoUrlRequest.class, WEB3IpoBatoClientHandler.class, "getBatoUrl");

        log.exiting(STR_METHOD_NAME);
    }
}@
