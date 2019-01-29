head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.14.05.46.13;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	8884d7d97833a75;
filename	WEB3EqtypeAdminAppPlugin.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : Eqtypeadmin AppPlugin(WEB3EqtypeadminAppPlugin.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2006/05/30　@黄建(中訊) 仕様変更 モデル105
Revesion History : 2007/01/17  謝旋 (中訊) 仕様変更モデルNo.115-118
Revesion History : 2007/04/27  趙林鵬 (中訊) 仕様変更モデルNo.128,129,131,132
Revesion History : 2008/01/29  肖志偉 (中訊) PTS2次対応
Revesion History : 2008/02/19  張騰宇 (中訊) 仕様変更モデルNo.170,171,179,180
Revesion History : 2008/11/20  張少傑 (中訊) 仕様変更モデルNo.213
Revesion History : 2008/12/26  水落 (SRA) 仕様変更DBレイアウトNo.025
Revesion History : 2009/01/07  孟亞南 (中訊) 仕様変更モデルNo.216,No.219
*/
package webbroker3.eqtypeadmin;

import com.fitechlabs.xtrade.kernel.boot.KernelPlugin;
import com.fitechlabs.xtrade.kernel.boot.Plugin;
import com.fitechlabs.xtrade.kernel.boot.Services;
import com.fitechlabs.xtrade.kernel.interceptor.TransactionalInterceptor;

import webbroker3.common.WEB3LogSysTimeInterceptor;
import webbroker3.eqtypeadmin.data.WEB3EqtypeadminMasterDatabaseExtensions;
import webbroker3.eqtypeadmin.data.WEB3EqtypeadminSessionDatabaseExtensions;
import webbroker3.eqtypeadmin.handler.WEB3AdminEquityAccProductTradeStopChangeHandler;
import webbroker3.eqtypeadmin.handler.WEB3AdminEquityAccProductTradeStopDeleteHandler;
import webbroker3.eqtypeadmin.handler.WEB3AdminEquityAccProductTradeStopListHandler;
import webbroker3.eqtypeadmin.handler.WEB3AdminEquityAccProductTradeStopRegistHandler;
import webbroker3.eqtypeadmin.handler.WEB3AdminEquityAttentionInfoNotifyHandler;
import webbroker3.eqtypeadmin.handler.WEB3AdminEquityAttentionInfoReferenceHandler;
import webbroker3.eqtypeadmin.handler.WEB3AdminEquityForcedSettleOrderApproveHandler;
import webbroker3.eqtypeadmin.handler.WEB3AdminEquityForcedSettleOrderApproveMainHandler;
import webbroker3.eqtypeadmin.handler.WEB3AdminEquityForcedSettleOrderDLHandler;
import webbroker3.eqtypeadmin.handler.WEB3AdminEquityForcedSettleReferenceHandler;
import webbroker3.eqtypeadmin.handler.WEB3AdminEquityForcedSettleTempOrderCreateHandler;
import webbroker3.eqtypeadmin.handler.WEB3AdminEquityManualExpireHandler;
import webbroker3.eqtypeadmin.handler.WEB3AdminEquityManualExpireMainHandler;
import webbroker3.eqtypeadmin.handler.WEB3AdminEquityPTSCancelExecHandler;
import webbroker3.eqtypeadmin.handler.WEB3AdminEquityPTSInputExecHandler;
import webbroker3.eqtypeadmin.handler.WEB3AdminEquityProductCondReferenceHandler;
import webbroker3.eqtypeadmin.handler.WEB3AdminEquityProductCondScheduleHandler;
import webbroker3.eqtypeadmin.handler.WEB3AdminEquityProductCondSettingHandler;
import webbroker3.eqtypeadmin.handler.WEB3AdminFrontNoticeHistoryHandler;
import webbroker3.eqtypeadmin.handler.WEB3AdminOffFloorChangeHandler;
import webbroker3.eqtypeadmin.handler.WEB3AdminOffFloorDeleteHandler;
import webbroker3.eqtypeadmin.handler.WEB3AdminOffFloorProductListHandler;
import webbroker3.eqtypeadmin.handler.WEB3AdminOffFloorRegistHandler;
import webbroker3.eqtypeadmin.message.WEB3AdminEquityForcedSettleOrderApproveMainRequest;
import webbroker3.eqtypeadmin.message.WEB3AdminEquityForcedSettleOrderApproveMainResponse;
import webbroker3.eqtypeadmin.message.WEB3AdminEquityForcedSettleTempOrderCreateRequest;
import webbroker3.eqtypeadmin.message.WEB3AdminEquityForcedSettleTempOrderCreateResponse;
import webbroker3.eqtypeadmin.message.WEB3AdminEquityManualLapseConfirmRequest;
import webbroker3.eqtypeadmin.message.WEB3AdminEquityManualLapseConfirmResponse;
import webbroker3.eqtypeadmin.message.WEB3AdminEquityManualLapseInputRequest;
import webbroker3.eqtypeadmin.message.WEB3AdminEquityManualLapseInputResponse;
import webbroker3.eqtypeadmin.message.WEB3AdminEquityManualLapseMainRequest;
import webbroker3.eqtypeadmin.message.WEB3AdminEquityManualLapseMainResponse;
import webbroker3.eqtypeadmin.message.WEB3AdminEquityManualLapseRunRequest;
import webbroker3.eqtypeadmin.message.WEB3AdminEquityManualLapseRunResponse;
import webbroker3.eqtypeadmin.message.WEB3AdminEquityManualLapseStatusRequest;
import webbroker3.eqtypeadmin.message.WEB3AdminEquityManualLapseStatusResponse;
import webbroker3.eqtypeadmin.message.WEB3AdminEquityPTSCancelExecCompleteRequest;
import webbroker3.eqtypeadmin.message.WEB3AdminEquityPTSCancelExecCompleteResponse;
import webbroker3.eqtypeadmin.message.WEB3AdminEquityPTSCancelExecConfirmRequest;
import webbroker3.eqtypeadmin.message.WEB3AdminEquityPTSCancelExecConfirmResponse;
import webbroker3.eqtypeadmin.message.WEB3AdminEquityPTSCancelExecInputRequest;
import webbroker3.eqtypeadmin.message.WEB3AdminEquityPTSCancelExecInputResponse;
import webbroker3.eqtypeadmin.message.WEB3AdminEquityPTSInputCancelExecCommonRequest;
import webbroker3.eqtypeadmin.message.WEB3AdminEquityPTSInputCancelExecCommonResponse;
import webbroker3.eqtypeadmin.message.WEB3AdminEquityPTSInputExecCompleteRequest;
import webbroker3.eqtypeadmin.message.WEB3AdminEquityPTSInputExecCompleteResponse;
import webbroker3.eqtypeadmin.message.WEB3AdminEquityPTSInputExecConfirmRequest;
import webbroker3.eqtypeadmin.message.WEB3AdminEquityPTSInputExecConfirmResponse;
import webbroker3.eqtypeadmin.message.WEB3AdminEquityPTSInputExecInputRequest;
import webbroker3.eqtypeadmin.message.WEB3AdminEquityPTSInputExecInputResponse;
import webbroker3.eqtypeadmin.message.WEB3AdminForcedSettleApproveConfirmRequest;
import webbroker3.eqtypeadmin.message.WEB3AdminForcedSettleApproveConfirmResponse;
import webbroker3.eqtypeadmin.message.WEB3AdminForcedSettleApproveRunRequest;
import webbroker3.eqtypeadmin.message.WEB3AdminForcedSettleApproveRunResponse;
import webbroker3.eqtypeadmin.message.WEB3AdminForcedSettleApproveStatusRequest;
import webbroker3.eqtypeadmin.message.WEB3AdminForcedSettleApproveStatusResponse;
import webbroker3.eqtypeadmin.message.WEB3AdminForcedSettleDownloadInputRequest;
import webbroker3.eqtypeadmin.message.WEB3AdminForcedSettleDownloadInputResponse;
import webbroker3.eqtypeadmin.message.WEB3AdminForcedSettleDownloadRequest;
import webbroker3.eqtypeadmin.message.WEB3AdminForcedSettleDownloadResponse;
import webbroker3.eqtypeadmin.message.WEB3AdminForcedSettleRefInputRequest;
import webbroker3.eqtypeadmin.message.WEB3AdminForcedSettleRefInputResponse;
import webbroker3.eqtypeadmin.message.WEB3AdminForcedSettleReferenceRequest;
import webbroker3.eqtypeadmin.message.WEB3AdminForcedSettleReferenceResponse;
import webbroker3.eqtypeadmin.message.WEB3AdminFrontNoticeHistoryInputRequest;
import webbroker3.eqtypeadmin.message.WEB3AdminFrontNoticeHistoryInputResponse;
import webbroker3.eqtypeadmin.message.WEB3AdminFrontNoticeHistoryReferenceRequest;
import webbroker3.eqtypeadmin.message.WEB3AdminFrontNoticeHistoryReferenceResponse;
import webbroker3.eqtypeadmin.message.WEB3AdminOffFloorChangeCompleteRequest;
import webbroker3.eqtypeadmin.message.WEB3AdminOffFloorChangeCompleteResponse;
import webbroker3.eqtypeadmin.message.WEB3AdminOffFloorChangeConfirmRequest;
import webbroker3.eqtypeadmin.message.WEB3AdminOffFloorChangeConfirmResponse;
import webbroker3.eqtypeadmin.message.WEB3AdminOffFloorChangeInputRequest;
import webbroker3.eqtypeadmin.message.WEB3AdminOffFloorChangeInputResponse;
import webbroker3.eqtypeadmin.message.WEB3AdminOffFloorDeleteCompleteRequest;
import webbroker3.eqtypeadmin.message.WEB3AdminOffFloorDeleteCompleteResponse;
import webbroker3.eqtypeadmin.message.WEB3AdminOffFloorDeleteConfirmRequest;
import webbroker3.eqtypeadmin.message.WEB3AdminOffFloorDeleteConfirmResponse;
import webbroker3.eqtypeadmin.message.WEB3AdminOffFloorProductListRequest;
import webbroker3.eqtypeadmin.message.WEB3AdminOffFloorProductListResponse;
import webbroker3.eqtypeadmin.message.WEB3AdminOffFloorRegistCompleteRequest;
import webbroker3.eqtypeadmin.message.WEB3AdminOffFloorRegistCompleteResponse;
import webbroker3.eqtypeadmin.message.WEB3AdminOffFloorRegistConfirmRequest;
import webbroker3.eqtypeadmin.message.WEB3AdminOffFloorRegistConfirmResponse;
import webbroker3.eqtypeadmin.message.WEB3AdminOffFloorRegistInputRequest;
import webbroker3.eqtypeadmin.message.WEB3AdminOffFloorRegistInputResponse;
import webbroker3.eqtypeadmin.message.WEB3AdminPMAccProductTradeStopDeleteCompleteRequest;
import webbroker3.eqtypeadmin.message.WEB3AdminPMAccProductTradeStopDeleteCompleteResponse;
import webbroker3.eqtypeadmin.message.WEB3AdminPMAccProductTradeStopDeleteConfirmRequest;
import webbroker3.eqtypeadmin.message.WEB3AdminPMAccProductTradeStopDeleteConfirmResponse;
import webbroker3.eqtypeadmin.message.WEB3AdminPMAccProductTradeStopListRequest;
import webbroker3.eqtypeadmin.message.WEB3AdminPMAccProductTradeStopListResponse;
import webbroker3.eqtypeadmin.message.WEB3AdminPMAccProductTradeStopModifyCompleteRequest;
import webbroker3.eqtypeadmin.message.WEB3AdminPMAccProductTradeStopModifyCompleteResponse;
import webbroker3.eqtypeadmin.message.WEB3AdminPMAccProductTradeStopModifyConfirmRequest;
import webbroker3.eqtypeadmin.message.WEB3AdminPMAccProductTradeStopModifyConfirmResponse;
import webbroker3.eqtypeadmin.message.WEB3AdminPMAccProductTradeStopModifyInputRequest;
import webbroker3.eqtypeadmin.message.WEB3AdminPMAccProductTradeStopModifyInputResponse;
import webbroker3.eqtypeadmin.message.WEB3AdminPMAccProductTradeStopRegistCompleteRequest;
import webbroker3.eqtypeadmin.message.WEB3AdminPMAccProductTradeStopRegistCompleteResponse;
import webbroker3.eqtypeadmin.message.WEB3AdminPMAccProductTradeStopRegistConfirmRequest;
import webbroker3.eqtypeadmin.message.WEB3AdminPMAccProductTradeStopRegistConfirmResponse;
import webbroker3.eqtypeadmin.message.WEB3AdminPMAccProductTradeStopRegistInputRequest;
import webbroker3.eqtypeadmin.message.WEB3AdminPMAccProductTradeStopRegistInputResponse;
import webbroker3.eqtypeadmin.message.WEB3AdminPMProductCondConfCompleteRequest;
import webbroker3.eqtypeadmin.message.WEB3AdminPMProductCondConfCompleteResponse;
import webbroker3.eqtypeadmin.message.WEB3AdminPMProductCondConfConfirmRequest;
import webbroker3.eqtypeadmin.message.WEB3AdminPMProductCondConfConfirmResponse;
import webbroker3.eqtypeadmin.message.WEB3AdminPMProductCondConfInputRequest;
import webbroker3.eqtypeadmin.message.WEB3AdminPMProductCondConfInputResponse;
import webbroker3.eqtypeadmin.message.WEB3AdminPMProductCondListInputRequest;
import webbroker3.eqtypeadmin.message.WEB3AdminPMProductCondListInputResponse;
import webbroker3.eqtypeadmin.message.WEB3AdminPMProductCondListRequest;
import webbroker3.eqtypeadmin.message.WEB3AdminPMProductCondListResponse;
import webbroker3.eqtypeadmin.message.WEB3AdminPMProductCondRefInputRequest;
import webbroker3.eqtypeadmin.message.WEB3AdminPMProductCondRefInputResponse;
import webbroker3.eqtypeadmin.message.WEB3AdminPMProductCondRefReferenceRequest;
import webbroker3.eqtypeadmin.message.WEB3AdminPMProductCondRefReferenceResponse;
import webbroker3.eqtypeadmin.message.WEB3AdminEqAttentionInfoRefInpRequest;
import webbroker3.eqtypeadmin.message.WEB3AdminEqAttentionInfoRefInpResponse;
import webbroker3.eqtypeadmin.message.WEB3AdminEqAttentionInfoRefRefRequest;
import webbroker3.eqtypeadmin.message.WEB3AdminEqAttentionInfoRefRefResponse;
import webbroker3.eqtypeadmin.message.WEB3AdminEquityAttentionInfoNotifyRequest;
import webbroker3.eqtypeadmin.message.WEB3AdminEquityAttentionInfoNotifyResponse;
import webbroker3.eqtypeadmin.service.delegate.WEB3AdminEquityAccProductTradeStopChangeService;
import webbroker3.eqtypeadmin.service.delegate.WEB3AdminEquityAccProductTradeStopDeleteService;
import webbroker3.eqtypeadmin.service.delegate.WEB3AdminEquityAccProductTradeStopListService;
import webbroker3.eqtypeadmin.service.delegate.WEB3AdminEquityAccProductTradeStopRegistService;
import webbroker3.eqtypeadmin.service.delegate.WEB3AdminEquityAttentionInfoNotifyService;
import webbroker3.eqtypeadmin.service.delegate.WEB3AdminEquityAttentionInfoNotifyUnitService;
import webbroker3.eqtypeadmin.service.delegate.WEB3AdminEquityAttentionInfoReferenceService;
import webbroker3.eqtypeadmin.service.delegate.WEB3AdminEquityForcedSettleBelowMarginRateBefOnlineTempOrderCreateUnitService;
import webbroker3.eqtypeadmin.service.delegate.WEB3AdminEquityForcedSettleBelowMarginRateIntermissionTempOrderCreateUnitService;
import webbroker3.eqtypeadmin.service.delegate.WEB3AdminEquityForcedSettleCloseDateTempOrderCreateUnitService;
import webbroker3.eqtypeadmin.service.delegate.WEB3AdminEquityForcedSettleOrderApproveMainService;
import webbroker3.eqtypeadmin.service.delegate.WEB3AdminEquityForcedSettleOrderApproveService;
import webbroker3.eqtypeadmin.service.delegate.WEB3AdminEquityForcedSettleOrderApproveUnitService;
import webbroker3.eqtypeadmin.service.delegate.WEB3AdminEquityForcedSettleOrderDLService;
import webbroker3.eqtypeadmin.service.delegate.WEB3AdminEquityForcedSettleReferenceService;
import webbroker3.eqtypeadmin.service.delegate.WEB3AdminEquityForcedSettleTempOrderCreateService;
import webbroker3.eqtypeadmin.service.delegate.WEB3AdminEquityManualExpireMainService;
import webbroker3.eqtypeadmin.service.delegate.WEB3AdminEquityManualExpireService;
import webbroker3.eqtypeadmin.service.delegate.WEB3AdminEquityPTSCancelExecService;
import webbroker3.eqtypeadmin.service.delegate.WEB3AdminEquityPTSInputExecService;
import webbroker3.eqtypeadmin.service.delegate.WEB3AdminEquityProductCondReferenceService;
import webbroker3.eqtypeadmin.service.delegate.WEB3AdminEquityProductCondScheduleService;
import webbroker3.eqtypeadmin.service.delegate.WEB3AdminEquityProductCondSettingService;
import webbroker3.eqtypeadmin.service.delegate.WEB3AdminFrontNoticeHistoryService;
import webbroker3.eqtypeadmin.service.delegate.WEB3AdminFrontOrderCommonService;
import webbroker3.eqtypeadmin.service.delegate.WEB3AdminOffFloorChangeService;
import webbroker3.eqtypeadmin.service.delegate.WEB3AdminOffFloorDeleteService;
import webbroker3.eqtypeadmin.service.delegate.WEB3AdminOffFloorProductListService;
import webbroker3.eqtypeadmin.service.delegate.WEB3AdminOffFloorRegistService;
import webbroker3.eqtypeadmin.service.delegate.stdimpls.WEB3AdminEquityAccProductTradeStopChangeServiceImpl;
import webbroker3.eqtypeadmin.service.delegate.stdimpls.WEB3AdminEquityAccProductTradeStopDeleteServiceImpl;
import webbroker3.eqtypeadmin.service.delegate.stdimpls.WEB3AdminEquityAccProductTradeStopListServiceImpl;
import webbroker3.eqtypeadmin.service.delegate.stdimpls.WEB3AdminEquityAccProductTradeStopRegistServiceImpl;
import webbroker3.eqtypeadmin.service.delegate.stdimpls.WEB3AdminEquityAttentionInfoNotifyServiceImpl;
import webbroker3.eqtypeadmin.service.delegate.stdimpls.WEB3AdminEquityAttentionInfoNotifyUnitServiceImpl;
import webbroker3.eqtypeadmin.service.delegate.stdimpls.WEB3AdminEquityAttentionInfoReferenceServiceImpl;
import webbroker3.eqtypeadmin.service.delegate.stdimpls.WEB3AdminEquityForcedSettleBelowMarginRateBefOnlineTempOrderCreateUnitServiceImpl;
import webbroker3.eqtypeadmin.service.delegate.stdimpls.WEB3AdminEquityForcedSettleBelowMarginRateIntermissionTempOrderCreateUnitServiceImpl;
import webbroker3.eqtypeadmin.service.delegate.stdimpls.WEB3AdminEquityForcedSettleCloseDateTempOrderCreateUnitServiceImpl;
import webbroker3.eqtypeadmin.service.delegate.stdimpls.WEB3AdminEquityForcedSettleOrderApproveMainServiceImpl;
import webbroker3.eqtypeadmin.service.delegate.stdimpls.WEB3AdminEquityForcedSettleOrderApproveServiceImpl;
import webbroker3.eqtypeadmin.service.delegate.stdimpls.WEB3AdminEquityForcedSettleOrderApproveUnitServiceImpl;
import webbroker3.eqtypeadmin.service.delegate.stdimpls.WEB3AdminEquityForcedSettleOrderDLServiceImpl;
import webbroker3.eqtypeadmin.service.delegate.stdimpls.WEB3AdminEquityForcedSettleReferenceServiceImpl;
import webbroker3.eqtypeadmin.service.delegate.stdimpls.WEB3AdminEquityForcedSettleTempOrderCreateServiceImpl;
import webbroker3.eqtypeadmin.service.delegate.stdimpls.WEB3AdminEquityManualExpireMainServiceImpl;
import webbroker3.eqtypeadmin.service.delegate.stdimpls.WEB3AdminEquityManualExpireServiceImpl;
import webbroker3.eqtypeadmin.service.delegate.stdimpls.WEB3AdminEquityPTSCancelExecServiceImpl;
import webbroker3.eqtypeadmin.service.delegate.stdimpls.WEB3AdminEquityPTSInputExecServiceImpl;
import webbroker3.eqtypeadmin.service.delegate.stdimpls.WEB3AdminEquityProductCondReferenceServiceImpl;
import webbroker3.eqtypeadmin.service.delegate.stdimpls.WEB3AdminEquityProductCondScheduleServiceImpl;
import webbroker3.eqtypeadmin.service.delegate.stdimpls.WEB3AdminEquityProductCondSettingServiceImpl;
import webbroker3.eqtypeadmin.service.delegate.stdimpls.WEB3AdminFrontNoticeHistoryServiceImpl;
import webbroker3.eqtypeadmin.service.delegate.stdimpls.WEB3AdminFrontOrderCommonServiceImpl;
import webbroker3.eqtypeadmin.service.delegate.stdimpls.WEB3AdminOffFloorChangeServiceImpl;
import webbroker3.eqtypeadmin.service.delegate.stdimpls.WEB3AdminOffFloorDeleteServiceImpl;
import webbroker3.eqtypeadmin.service.delegate.stdimpls.WEB3AdminOffFloorProductListServiceImpl;
import webbroker3.eqtypeadmin.service.delegate.stdimpls.WEB3AdminOffFloorRegistServiceImpl;
import webbroker3.mqgateway.WEB3MQGatewayInterceptor;
import webbroker3.util.WEB3LogUtility;

/**
 * Webbroker3-Eqtypeadmin<BR>
 * WEB3EqtypeadminAppPlugin
 * @@author SRAI
 * @@version 1.0
 */
public final class WEB3EqtypeAdminAppPlugin extends Plugin
{
    /**
     * ログユーティリティ。
     */
    private static WEB3LogUtility log = WEB3LogUtility.getInstance(WEB3EqtypeAdminAppPlugin.class);

    /**
     * デフォルトコンストラクタ
     */
    public WEB3EqtypeAdminAppPlugin()
    {
    }

    /**
     * plug method
     * @@throws Exception exception
     */
    public static void plug() throws Exception
    {
        final String STR_METHOD_NAME = "plug()";
        log.entering(STR_METHOD_NAME);
        plug(WEB3EqtypeAdminAppPlugin.class);
        log.exiting(STR_METHOD_NAME);
    }

    /**
     * onPlug method
     * @@throws Exception exception
     */
    public static void onPlug() throws Exception
    {
        final String METHOD_NAME = "onPlug()";
        log.entering(METHOD_NAME);

        // このプラグインより先に読み込む必要のあるプラグインの指定。
        KernelPlugin.plug();

        // DatabaseExtensions のプラグイン処理
        WEB3EqtypeadminSessionDatabaseExtensions.plug();
        WEB3EqtypeadminMasterDatabaseExtensions.plug();

        // Service の登録処理
        // 管理者顧客銘柄別取引停止変更サービス
        Services.registerService(WEB3AdminEquityAccProductTradeStopChangeService.class,
            new WEB3AdminEquityAccProductTradeStopChangeServiceImpl());

        // 管理者顧客銘柄別取引停止登録サービス
        Services.registerService(WEB3AdminEquityAccProductTradeStopRegistService.class,
            new WEB3AdminEquityAccProductTradeStopRegistServiceImpl());

        // 管理者顧客銘柄別取引停止一覧サービス
        Services.registerService(WEB3AdminEquityAccProductTradeStopListService.class,
            new WEB3AdminEquityAccProductTradeStopListServiceImpl());

        // 管理者株式銘柄条件予定一覧サービス
        Services.registerService(WEB3AdminEquityProductCondScheduleService.class,
            new WEB3AdminEquityProductCondScheduleServiceImpl());

        // 管理者顧客銘柄別取引停止削除サービス
        Services.registerService(WEB3AdminEquityAccProductTradeStopDeleteService.class,
            new WEB3AdminEquityAccProductTradeStopDeleteServiceImpl());

        // 管理者株式銘柄条件照会サービス
        Services.registerService(WEB3AdminEquityProductCondReferenceService.class,
            new WEB3AdminEquityProductCondReferenceServiceImpl());

        // 管理者立会外分売銘柄一覧サービス
        Services.registerService(WEB3AdminOffFloorProductListService.class,
            new  WEB3AdminOffFloorProductListServiceImpl());

        // 管理者立会外分売銘柄更新サービス
        Services.registerService(WEB3AdminOffFloorChangeService.class,
            new WEB3AdminOffFloorChangeServiceImpl());

        // 管理者立会外分売銘柄削除サービス
        Services.registerService(WEB3AdminOffFloorDeleteService.class,
            new WEB3AdminOffFloorDeleteServiceImpl());

        // 管理者立会外分売銘柄新規登録サービス
        Services.registerService(WEB3AdminOffFloorRegistService.class,
            new WEB3AdminOffFloorRegistServiceImpl());

        // 管理者株式銘柄条件設定サービス
        Services.registerService(WEB3AdminEquityProductCondSettingService.class,
            new WEB3AdminEquityProductCondSettingServiceImpl());

        // 管理者フロント発注共通サービス
        Services.registerService(WEB3AdminFrontOrderCommonService.class,
            new WEB3AdminFrontOrderCommonServiceImpl());

       // 管理者通知履歴参照サービス
        Services.registerService(WEB3AdminFrontNoticeHistoryService.class,
            new WEB3AdminFrontNoticeHistoryServiceImpl());
        
        //管理者・株式手動失効サービス
        Services.registerService(WEB3AdminEquityManualExpireService.class,
            new WEB3AdminEquityManualExpireServiceImpl());
        
        //管理者・株式手動失効メインサービス
        Services.registerService(WEB3AdminEquityManualExpireMainService.class,
            new WEB3AdminEquityManualExpireMainServiceImpl());

        //管理者・強制決済仮注文承認／非承認サービス
        Services.registerService(WEB3AdminEquityForcedSettleOrderApproveService.class,
            new WEB3AdminEquityForcedSettleOrderApproveServiceImpl());

        //管理者・強制決済仮注文承認／非承認メインサービス
        Services.registerService(WEB3AdminEquityForcedSettleOrderApproveMainService.class,
            new WEB3AdminEquityForcedSettleOrderApproveMainServiceImpl());

        //管理者・強制決済仮注文承認／非承認一件サービス
        Services.registerService(WEB3AdminEquityForcedSettleOrderApproveUnitService.class,
            new WEB3AdminEquityForcedSettleOrderApproveUnitServiceImpl());

        //管理者・強制決済注文照会サービス
        Services.registerService(WEB3AdminEquityForcedSettleReferenceService.class,
            new WEB3AdminEquityForcedSettleReferenceServiceImpl());

        //強制決済仮注文作成サービス
        Services.registerService(WEB3AdminEquityForcedSettleTempOrderCreateService.class,
            new WEB3AdminEquityForcedSettleTempOrderCreateServiceImpl());

        //強制決済期日到来建仮注文作成一件サービス
        Services.registerService(WEB3AdminEquityForcedSettleCloseDateTempOrderCreateUnitService.class,
            new WEB3AdminEquityForcedSettleCloseDateTempOrderCreateUnitServiceImpl());

        //強制決済保証金維持率割れ（オンライン開始前）仮注文作成一件サービス
        Services.registerService(WEB3AdminEquityForcedSettleBelowMarginRateBefOnlineTempOrderCreateUnitService.class,
            new WEB3AdminEquityForcedSettleBelowMarginRateBefOnlineTempOrderCreateUnitServiceImpl());

        //管理者・株式（PTS）出来入力サービス
        Services.registerService(WEB3AdminEquityPTSInputExecService.class,
            new WEB3AdminEquityPTSInputExecServiceImpl());

        //管理者・株式（PTS）出来取消サービス
        Services.registerService(WEB3AdminEquityPTSCancelExecService.class,
            new WEB3AdminEquityPTSCancelExecServiceImpl());

        //強制決済保証金維持率割れ（場間）仮注文作成一件サービス
        Services.registerService(WEB3AdminEquityForcedSettleBelowMarginRateIntermissionTempOrderCreateUnitService.class,
            new WEB3AdminEquityForcedSettleBelowMarginRateIntermissionTempOrderCreateUnitServiceImpl());

        //管理者・強制決済注文DLサービス
        Services.registerService(WEB3AdminEquityForcedSettleOrderDLService.class,
            new WEB3AdminEquityForcedSettleOrderDLServiceImpl());

        //管理者注意情報照会サービス
        Services.registerService(WEB3AdminEquityAttentionInfoReferenceService.class,
            new WEB3AdminEquityAttentionInfoReferenceServiceImpl());

        //注意情報通知サービス
        Services.registerService(WEB3AdminEquityAttentionInfoNotifyService.class,
            new WEB3AdminEquityAttentionInfoNotifyServiceImpl());

        //注意情報通知一件サービス
        Services.registerService(WEB3AdminEquityAttentionInfoNotifyUnitService.class,
            new WEB3AdminEquityAttentionInfoNotifyUnitServiceImpl());

        // サービスインタセプタの設定
        // 管理者顧客銘柄別取引停止登録サービス
        Services.addInterceptor(WEB3AdminEquityAccProductTradeStopRegistService.class,
            new WEB3AdminPMEquityServiceInterceptor());


        // 管理者顧客銘柄別取引停止変更サービス
        Services.addInterceptor(WEB3AdminEquityAccProductTradeStopChangeService.class,
            new WEB3AdminPMEquityServiceInterceptor());

        // 管理者顧客銘柄別取引停止一覧サービス
        Services.addInterceptor(WEB3AdminEquityAccProductTradeStopListService.class,
            new WEB3AdminPMEquityServiceInterceptor());

        // 管理者株式銘柄条件予定一覧サービス
        Services.addInterceptor(WEB3AdminEquityProductCondScheduleService.class,
            new WEB3AdminPMEquityServiceInterceptor());

        // 管理者顧客銘柄別取引停止削除サービス
        Services.addInterceptor(WEB3AdminEquityAccProductTradeStopDeleteService.class,
            new WEB3AdminPMEquityServiceInterceptor());

        // 管理者株式銘柄条件照会サービス
        Services.addInterceptor(WEB3AdminEquityProductCondReferenceService.class,
            new WEB3AdminPMEquityServiceInterceptor());

        // 管理者立会外分売銘柄一覧サービス
        Services.addInterceptor(WEB3AdminOffFloorProductListService.class,
            new WEB3AdminOffFloorServiceInterceptor());

        // 管理者立会外分売銘柄更新サービス
        Services.addInterceptor(WEB3AdminOffFloorChangeService.class,
            new WEB3AdminOffFloorServiceInterceptor());

        // 管理者立会外分売銘柄削除サービス
        Services.addInterceptor(WEB3AdminOffFloorDeleteService.class,
            new WEB3AdminOffFloorServiceInterceptor());

        // 管理者立会外分売銘柄新規登録サービス
        Services.addInterceptor(WEB3AdminOffFloorRegistService.class,
            new WEB3AdminOffFloorServiceInterceptor());

        // 管理者株式銘柄条件設定サービス
       Services.addInterceptor(WEB3AdminEquityProductCondSettingService.class,
            new WEB3AdminPMEquityServiceInterceptor());

       //管理者・株式手動失効サービス
       Services.addInterceptor(WEB3AdminEquityManualExpireService.class,
           new WEB3AdminEquityManualExpireServiceInterceptor());

        //管理者・強制決済注文照会サービス
        Services.addInterceptor(WEB3AdminEquityForcedSettleReferenceService.class,
            new WEB3AdminPMEquityServiceInterceptor());

        //管理者・強制決済仮注文承認／非承認サービス
        Services.addInterceptor(WEB3AdminEquityForcedSettleOrderApproveService.class,
            new WEB3AdminPMEquityServiceInterceptor());

        //強制決済期日到来建仮注文作成一件サービス
        Services.addInterceptor(WEB3AdminEquityForcedSettleCloseDateTempOrderCreateUnitService.class,
            new WEB3AdminEquityForcedSettleTempOrderCreateUnitServiceInterceptor());

        //強制決済保証金維持率割れ（オンライン開始前）仮注文作成一件サービス
        Services.addInterceptor(WEB3AdminEquityForcedSettleBelowMarginRateBefOnlineTempOrderCreateUnitService.class,
            new WEB3AdminEquityForcedSettleTempOrderCreateUnitServiceInterceptor());

        //管理者・株式（PTS）出来入力サービス
        Services.addInterceptor(WEB3AdminEquityPTSInputExecService.class,
            new WEB3AdminEquityPTSInputExecServiceInterceptor());

        //管理者・株式（PTS）出来取消サービス
        Services.addInterceptor(WEB3AdminEquityPTSCancelExecService.class,
            new WEB3AdminEquityPTSCancelExecServiceInterceptor());

        //強制決済保証金維持率割れ（場間）仮注文作成一件サービス
        Services.addInterceptor(WEB3AdminEquityForcedSettleBelowMarginRateIntermissionTempOrderCreateUnitService.class,
            new WEB3AdminEquityForcedSettleTempOrderCreateUnitServiceInterceptor());

        //管理者・強制決済仮注文承認／非承認一件サービス
        Services.addInterceptor(WEB3AdminEquityForcedSettleOrderApproveUnitService.class,
            new WEB3AdminEquityForcedSettleOrderApproveUnitServiceInterceptor());
        
        //管理者注意情報照会サービス
        Services.addInterceptor(WEB3AdminEquityAttentionInfoReferenceService.class,
            new WEB3AdminPMEquityServiceInterceptor());

        //注意情報通知一件サービス
        Services.addInterceptor(WEB3AdminEquityAttentionInfoNotifyUnitService.class,
            new WEB3AdminEquityAttentionInfoNotifyUnitServiceInterceptor());

        // サービス呼び出し前後に
        // 処理開始時刻と処理終了時刻をログ出力するように設定する
        // 管理者顧客銘柄別取引停止登録サービス
        Services.addInterceptor(WEB3AdminEquityAccProductTradeStopRegistService.class,
            new WEB3LogSysTimeInterceptor());

        // 管理者顧客銘柄別取引停止変更サービス
        Services.addInterceptor(WEB3AdminEquityAccProductTradeStopChangeService.class,
            new WEB3LogSysTimeInterceptor());

        // 管理者顧客銘柄別取引停止一覧サービス
        Services.addInterceptor(WEB3AdminEquityAccProductTradeStopListService.class,
            new WEB3LogSysTimeInterceptor());

        // 管理者株式銘柄条件予定一覧サービス
        Services.addInterceptor(WEB3AdminEquityProductCondScheduleService.class,
            new WEB3LogSysTimeInterceptor());

        // 管理者顧客銘柄別取引停止削除サービス
        Services.addInterceptor(WEB3AdminEquityAccProductTradeStopDeleteService.class,
            new WEB3LogSysTimeInterceptor());

        // 管理者株式銘柄条件照会サービス
        Services.addInterceptor(WEB3AdminEquityProductCondReferenceService.class,
            new WEB3LogSysTimeInterceptor());

        // 管理者立会外分売銘柄一覧サービス
        Services.addInterceptor(WEB3AdminOffFloorProductListService.class,
            new WEB3LogSysTimeInterceptor());

        // 管理者立会外分売銘柄更新サービス
        Services.addInterceptor(WEB3AdminOffFloorChangeService.class,
            new WEB3LogSysTimeInterceptor());

        // 管理者立会外分売銘柄削除サービス
        Services.addInterceptor(WEB3AdminOffFloorDeleteService.class,
            new WEB3LogSysTimeInterceptor());

        // 管理者立会外分売銘柄登録サービス
        Services.addInterceptor(WEB3AdminOffFloorRegistService.class,
            new WEB3LogSysTimeInterceptor());

        // 管理者株式銘柄条件設定サービス
        Services.addInterceptor(WEB3AdminEquityProductCondSettingService.class,
            new WEB3LogSysTimeInterceptor());
            
        // 管理者フロント発注共通サービス
        Services.addInterceptor(WEB3AdminFrontOrderCommonService.class,
            new WEB3LogSysTimeInterceptor());

        // 管理者通知履歴参照サービス
        Services.addInterceptor(WEB3AdminFrontNoticeHistoryService.class,
            new WEB3LogSysTimeInterceptor());
        
        //管理者・株式手動失効サービス
        Services.addInterceptor(WEB3AdminEquityManualExpireService.class,
            new WEB3LogSysTimeInterceptor());  
        
        //管理者・株式手動失効メインサービス
        Services.addInterceptor(WEB3AdminEquityManualExpireMainService.class,
            new WEB3LogSysTimeInterceptor());  

        //管理者・強制決済仮注文承認／非承認サービス
        Services.addInterceptor(WEB3AdminEquityForcedSettleOrderApproveService.class,
            new WEB3LogSysTimeInterceptor());

        //管理者・強制決済仮注文承認／非承認メインサービス
        Services.addInterceptor(WEB3AdminEquityForcedSettleOrderApproveMainService.class,
            new WEB3LogSysTimeInterceptor());

        //管理者・強制決済仮注文承認／非承認一件サービス
        Services.addInterceptor(WEB3AdminEquityForcedSettleOrderApproveUnitService.class,
            new WEB3LogSysTimeInterceptor());

        //管理者・強制決済注文照会サービス
        Services.addInterceptor(WEB3AdminEquityForcedSettleReferenceService.class,
            new WEB3LogSysTimeInterceptor());

        //強制決済仮注文作成サービス
        Services.addInterceptor(WEB3AdminEquityForcedSettleTempOrderCreateService.class,
            new WEB3LogSysTimeInterceptor());

        //強制決済期日到来建仮注文作成一件サービス
        Services.addInterceptor(WEB3AdminEquityForcedSettleCloseDateTempOrderCreateUnitService.class,
            new WEB3LogSysTimeInterceptor());

        //強制決済保証金維持率割れ（オンライン開始前）仮注文作成一件サービス
        Services.addInterceptor(WEB3AdminEquityForcedSettleBelowMarginRateBefOnlineTempOrderCreateUnitService.class,
            new WEB3LogSysTimeInterceptor());

        //管理者・株式（PTS）出来入力サービス
        Services.addInterceptor(WEB3AdminEquityPTSInputExecService.class,
            new WEB3LogSysTimeInterceptor());

        //管理者・株式（PTS）出来取消サービス
        Services.addInterceptor(WEB3AdminEquityPTSCancelExecService.class,
            new WEB3LogSysTimeInterceptor());

        //強制決済保証金維持率割れ（場間）仮注文作成一件サービス
        Services.addInterceptor(WEB3AdminEquityForcedSettleBelowMarginRateIntermissionTempOrderCreateUnitService.class,
            new WEB3LogSysTimeInterceptor());

        //管理者・強制決済注文DLサービス
        Services.addInterceptor(WEB3AdminEquityForcedSettleOrderDLService.class,
            new WEB3LogSysTimeInterceptor());

        //管理者注意情報照会サービス
        Services.addInterceptor(WEB3AdminEquityAttentionInfoReferenceService.class,
            new WEB3LogSysTimeInterceptor());

        //注意情報通知サービス
        Services.addInterceptor(WEB3AdminEquityAttentionInfoNotifyService.class,
            new WEB3LogSysTimeInterceptor());

        //注意情報通知一件サービス
        Services.addInterceptor(WEB3AdminEquityAttentionInfoNotifyUnitService.class,
            new WEB3LogSysTimeInterceptor());

        // 自動トランザクション設定
        // 管理者顧客銘柄別取引停止登録サービス
        Services.addInterceptor(WEB3AdminEquityAccProductTradeStopRegistService.class,
            new TransactionalInterceptor(TransactionalInterceptor.TX_CREATE_NEW));
            
        // 管理者顧客銘柄別取引停止変更サービス
        Services.addInterceptor(WEB3AdminEquityAccProductTradeStopChangeService.class,
            new TransactionalInterceptor(TransactionalInterceptor.TX_CREATE_NEW));

        // 管理者顧客銘柄別取引停止一覧サービス
        Services.addInterceptor(WEB3AdminEquityAccProductTradeStopListService.class,
            new TransactionalInterceptor(TransactionalInterceptor.TX_CREATE_NEW));

        // 管理者株式銘柄条件予定一覧サービス
        Services.addInterceptor(
        WEB3AdminEquityProductCondScheduleService.class,
            new TransactionalInterceptor(TransactionalInterceptor.TX_CREATE_NEW));

        // 管理者顧客銘柄別取引停止削除サービス
        Services.addInterceptor(WEB3AdminEquityAccProductTradeStopDeleteService.class,
            new TransactionalInterceptor(TransactionalInterceptor.TX_CREATE_NEW));

        // 管理者株式銘柄条件照会サービス
        Services.addInterceptor(WEB3AdminEquityProductCondReferenceService.class,
            new TransactionalInterceptor(TransactionalInterceptor.TX_CREATE_NEW));

        // 管理者立会外分売銘柄一覧サービス
        Services.addInterceptor(WEB3AdminOffFloorProductListService.class,
            new TransactionalInterceptor(TransactionalInterceptor.TX_CREATE_NEW));

        // 管理者立会外分売銘柄更新サービス
        Services.addInterceptor(WEB3AdminOffFloorChangeService.class,
            new TransactionalInterceptor(TransactionalInterceptor.TX_CREATE_NEW));

        // 管理者立会外分売銘柄削除サービス
        Services.addInterceptor(WEB3AdminOffFloorDeleteService.class,
            new TransactionalInterceptor(TransactionalInterceptor.TX_CREATE_NEW));

        // 管理者立会外分売銘柄登録サービス
        Services.addInterceptor(WEB3AdminOffFloorRegistService.class,
            new TransactionalInterceptor(TransactionalInterceptor.TX_CREATE_NEW));

        // 管理者株式銘柄条件設定サービス
        Services.addInterceptor(WEB3AdminEquityProductCondSettingService.class,
            new TransactionalInterceptor(TransactionalInterceptor.TX_CREATE_NEW));
            
        // 管理者フロント発注共通サービス
        Services.addInterceptor(WEB3AdminFrontOrderCommonService.class,
            new TransactionalInterceptor(TransactionalInterceptor.TX_CREATE_NEW));            

        // 管理者通知履歴参照サービス
        Services.addInterceptor(WEB3AdminFrontNoticeHistoryService.class,
        new TransactionalInterceptor(TransactionalInterceptor.TX_CREATE_NEW));
        
        //管理者・株式手動失効サービス
        Services.addInterceptor(WEB3AdminEquityManualExpireService.class,
            new TransactionalInterceptor(TransactionalInterceptor.TX_JOIN_EXISTING));
        
        //管理者・株式手動失効メインサービス
        Services.addInterceptor(WEB3AdminEquityManualExpireMainService.class,
            new TransactionalInterceptor(TransactionalInterceptor.TX_CREATE_NEW));

        //管理者・強制決済仮注文承認／非承認サービス
        Services.addInterceptor(WEB3AdminEquityForcedSettleOrderApproveService.class,
            new TransactionalInterceptor(TransactionalInterceptor.TX_JOIN_EXISTING));

        //管理者・強制決済仮注文承認／非承認メインサービス
        Services.addInterceptor(WEB3AdminEquityForcedSettleOrderApproveMainService.class,
            new TransactionalInterceptor(TransactionalInterceptor.TX_CREATE_NEW));

        //管理者・強制決済仮注文承認／非承認一件サービス
        Services.addInterceptor(WEB3AdminEquityForcedSettleOrderApproveUnitService.class,
            new TransactionalInterceptor(TransactionalInterceptor.TX_CREATE_NEW));

        //管理者・強制決済注文照会サービス
        Services.addInterceptor(WEB3AdminEquityForcedSettleReferenceService.class,
            new TransactionalInterceptor(TransactionalInterceptor.TX_JOIN_EXISTING));

        //強制決済仮注文作成サービス
        Services.addInterceptor(WEB3AdminEquityForcedSettleTempOrderCreateService.class,
            new TransactionalInterceptor(TransactionalInterceptor.TX_CREATE_NEW));

        //強制決済期日到来建仮注文作成一件サービス
        Services.addInterceptor(WEB3AdminEquityForcedSettleCloseDateTempOrderCreateUnitService.class,
            new TransactionalInterceptor(TransactionalInterceptor.TX_CREATE_NEW));

        //強制決済保証金維持率割れ（オンライン開始前）仮注文作成一件サービス
        Services.addInterceptor(WEB3AdminEquityForcedSettleBelowMarginRateBefOnlineTempOrderCreateUnitService.class,
            new TransactionalInterceptor(TransactionalInterceptor.TX_CREATE_NEW));

        //管理者・株式（PTS）出来入力サービス
        Services.addInterceptor(WEB3AdminEquityPTSInputExecService.class,
            new TransactionalInterceptor(TransactionalInterceptor.TX_CREATE_NEW));

        //管理者・株式（PTS）出来取消サービス
        Services.addInterceptor(WEB3AdminEquityPTSCancelExecService.class,
            new TransactionalInterceptor(TransactionalInterceptor.TX_CREATE_NEW));

        //強制決済保証金維持率割れ（場間）仮注文作成一件サービス
        Services.addInterceptor(WEB3AdminEquityForcedSettleBelowMarginRateIntermissionTempOrderCreateUnitService.class,
            new TransactionalInterceptor(TransactionalInterceptor.TX_CREATE_NEW));

        //管理者・強制決済注文DLサービス
        Services.addInterceptor(WEB3AdminEquityForcedSettleOrderDLService.class,
            new TransactionalInterceptor(TransactionalInterceptor.TX_JOIN_EXISTING));

        //管理者注意情報照会サービス
        Services.addInterceptor(WEB3AdminEquityAttentionInfoReferenceService.class,
            new TransactionalInterceptor(TransactionalInterceptor.TX_JOIN_EXISTING));

        //注意情報通知サービス
        Services.addInterceptor(WEB3AdminEquityAttentionInfoNotifyService.class,
            new TransactionalInterceptor(TransactionalInterceptor.TX_JOIN_EXISTING));

        //注意情報通知一件サービス
        Services.addInterceptor(WEB3AdminEquityAttentionInfoNotifyUnitService.class,
            new TransactionalInterceptor(TransactionalInterceptor.TX_CREATE_NEW));

        // MQ-Gatewayインタセプタの設定
        //管理者・強制決済仮注文承認／非承認一件サービス
        Services.addInterceptor(WEB3AdminEquityForcedSettleOrderApproveUnitService.class,
            new WEB3MQGatewayInterceptor());

        // Message の登録
        // 管理者・顧客銘柄別取引停止登録入力リクエスト・レスポンス
        regClass(WEB3AdminPMAccProductTradeStopRegistInputRequest.class);
        regClass(WEB3AdminPMAccProductTradeStopRegistInputResponse.class);

        // 管理者・顧客銘柄別取引停止登録確認リクエスト・レスポンス
        regClass(WEB3AdminPMAccProductTradeStopRegistConfirmRequest.class);
        regClass(WEB3AdminPMAccProductTradeStopRegistConfirmResponse.class);

        // 管理者・顧客銘柄別取引停止登録完了リクエスト・レスポンス
        regClass(WEB3AdminPMAccProductTradeStopRegistCompleteRequest.class);
        regClass(WEB3AdminPMAccProductTradeStopRegistCompleteResponse.class);

        // 管理者・顧客銘柄別取引停止変更入力リクエスト・レスポンス
        regClass(WEB3AdminPMAccProductTradeStopModifyInputRequest.class);
        regClass(WEB3AdminPMAccProductTradeStopModifyInputResponse.class);

        // 管理者・顧客銘柄別取引停止変更確認リクエスト・レスポンス
        regClass(WEB3AdminPMAccProductTradeStopModifyConfirmRequest.class);
        regClass(WEB3AdminPMAccProductTradeStopModifyConfirmResponse.class);

        // 管理者・顧客銘柄別取引停止変更完了リクエスト・レスポンス
        regClass(WEB3AdminPMAccProductTradeStopModifyCompleteRequest.class);
        regClass(WEB3AdminPMAccProductTradeStopModifyCompleteResponse.class);

        // 管理者・顧客銘柄別取引停止一覧リクエスト・レスポンス
        regClass(WEB3AdminPMAccProductTradeStopListRequest.class);
        regClass(WEB3AdminPMAccProductTradeStopListResponse.class);

        // 管理者・株式銘柄条件予定一覧入力リクエスト・レスポンス
        regClass(WEB3AdminPMProductCondListInputRequest.class);
        regClass(WEB3AdminPMProductCondListInputResponse.class);
        
        // 管理者・株式銘柄条件予定一覧リクエスト・レスポンス
        regClass(WEB3AdminPMProductCondListRequest.class);
        regClass(WEB3AdminPMProductCondListResponse.class);

        // 管理者・顧客銘柄別取引停止削除確認リクエスト・レスポンス
        regClass(WEB3AdminPMAccProductTradeStopDeleteConfirmRequest.class);
        regClass(WEB3AdminPMAccProductTradeStopDeleteConfirmResponse.class);

        // 管理者・顧客銘柄別取引停止削除完了リクエスト・レスポンス
        regClass(WEB3AdminPMAccProductTradeStopDeleteCompleteRequest.class);
        regClass(WEB3AdminPMAccProductTradeStopDeleteCompleteResponse.class);

        // 管理者・株式銘柄条件照会銘柄入力リクエスト・レスポンス
        regClass(WEB3AdminPMProductCondRefInputRequest.class);
        regClass(WEB3AdminPMProductCondRefInputResponse.class);

        // 管理者・株式銘柄条件照会リクエスト・レスポンス
        regClass(WEB3AdminPMProductCondRefReferenceRequest.class);
        regClass(WEB3AdminPMProductCondRefReferenceResponse.class);

        // 管理者・立会外分売銘柄一覧リクエスト・レスポンス
        regClass(WEB3AdminOffFloorProductListRequest.class);
        regClass(WEB3AdminOffFloorProductListResponse.class);

        // 管理者・立会外分売銘柄更新入力リクエスト・レスポンス
        regClass(WEB3AdminOffFloorChangeInputRequest.class);
        regClass(WEB3AdminOffFloorChangeInputResponse.class);

        // 管理者・立会外分売銘柄更新確認リクエスト・レスポンス
        regClass(WEB3AdminOffFloorChangeConfirmRequest.class);
        regClass(WEB3AdminOffFloorChangeConfirmResponse.class);

        // 管理者・立会外分売銘柄更新完了リクエスト・レスポンス
        regClass(WEB3AdminOffFloorChangeCompleteRequest.class);
        regClass(WEB3AdminOffFloorChangeCompleteResponse.class);

        // 管理者・立会外分売銘柄削除確認リクエスト・レスポンス
        regClass(WEB3AdminOffFloorDeleteConfirmRequest.class);
        regClass(WEB3AdminOffFloorDeleteConfirmResponse.class);

        // 管理者・立会外分売銘柄削除完了リクエスト・レスポンス
        regClass(WEB3AdminOffFloorDeleteCompleteRequest.class);
        regClass(WEB3AdminOffFloorDeleteCompleteResponse.class);

        // 管理者・立会外分売銘柄新規登録入力リクエスト・レスポンス
        regClass(WEB3AdminOffFloorRegistInputRequest.class);
        regClass(WEB3AdminOffFloorRegistInputResponse.class);

        // 管理者・立会外分売銘柄新規登録完了リクエスト・レスポンス
        regClass(WEB3AdminOffFloorRegistCompleteRequest.class);
        regClass(WEB3AdminOffFloorRegistCompleteResponse.class);

        // 管理者・立会外分売銘柄新規登録確認リクエスト・レスポンス
        regClass(WEB3AdminOffFloorRegistConfirmRequest.class);
        regClass(WEB3AdminOffFloorRegistConfirmResponse.class);

        // 管理者・株式銘柄条件設定入力リクエスト・レスポンス
        regClass(WEB3AdminPMProductCondConfInputRequest.class);
        regClass(WEB3AdminPMProductCondConfInputResponse.class);

        // 管理者・株式銘柄条件設定完了リクエスト・レスポンス
        regClass(WEB3AdminPMProductCondConfCompleteRequest.class);
        regClass(WEB3AdminPMProductCondConfCompleteResponse.class);

        // 管理者・株式銘柄条件設定確認リクエスト・レスポンス
        regClass(WEB3AdminPMProductCondConfConfirmRequest.class);
        regClass(WEB3AdminPMProductCondConfConfirmResponse.class);

        // 管理者・通知履歴参照リクエスト・レスポンス
        regClass(WEB3AdminFrontNoticeHistoryReferenceRequest.class);
        regClass(WEB3AdminFrontNoticeHistoryReferenceResponse.class);
        
        // 管理者・通知履歴入力リクエスト・レスポンス
        regClass(WEB3AdminFrontNoticeHistoryInputRequest.class);
        regClass(WEB3AdminFrontNoticeHistoryInputResponse.class);
        
        //管理者・株式手動失効入力リクエスト・レスポンス 
        regClass(WEB3AdminEquityManualLapseInputRequest.class);
        regClass(WEB3AdminEquityManualLapseInputResponse.class);
        
        //管理者・株式手動失効確認リクエスト・レスポンス 
        regClass(WEB3AdminEquityManualLapseConfirmRequest.class);
        regClass(WEB3AdminEquityManualLapseConfirmResponse.class);
        
        //管理者・株式手動失効処理起動リクエスト・レスポンス 
        regClass(WEB3AdminEquityManualLapseRunRequest.class);
        regClass(WEB3AdminEquityManualLapseRunResponse.class);
        
        //管理者・株式手動失効処理ステータス確認リクエスト・レスポンス 
        regClass(WEB3AdminEquityManualLapseStatusRequest.class);
        regClass(WEB3AdminEquityManualLapseStatusResponse.class);
        
        //管理者・株式手動失効メインリクエスト・レスポンス 
        regClass(WEB3AdminEquityManualLapseMainRequest.class);
        regClass(WEB3AdminEquityManualLapseMainResponse.class);

        //管理者・強制決済仮注文承認／非承認メインリクエスト・レスポンス
        regClass(WEB3AdminEquityForcedSettleOrderApproveMainRequest.class);
        regClass(WEB3AdminEquityForcedSettleOrderApproveMainResponse.class);

        //管理者・強制決済仮注文承認／非承認確認リクエスト・レスポンス
        regClass(WEB3AdminForcedSettleApproveConfirmRequest.class);
        regClass(WEB3AdminForcedSettleApproveConfirmResponse.class);

        //管理者・強制決済仮注文承認／非承認処理起動リクエスト・レスポンス
        regClass(WEB3AdminForcedSettleApproveRunRequest.class);
        regClass(WEB3AdminForcedSettleApproveRunResponse.class);

        //管理者・強制決済仮注文承認／非承認処理ステータス確認リクエスト・レスポンス
        regClass(WEB3AdminForcedSettleApproveStatusRequest.class);
        regClass(WEB3AdminForcedSettleApproveStatusResponse.class);

        //管理者・強制決済注文照会入力リクエスト・レスポンス
        regClass(WEB3AdminForcedSettleRefInputRequest.class);
        regClass(WEB3AdminForcedSettleRefInputResponse.class);

        //管理者・強制決済注文照会リクエスト・レスポンス
        regClass(WEB3AdminForcedSettleReferenceRequest.class);
        regClass(WEB3AdminForcedSettleReferenceResponse.class);

        //強制決済仮注文作成リクエスト・レスポンス
        regClass(WEB3AdminEquityForcedSettleTempOrderCreateRequest.class);
        regClass(WEB3AdminEquityForcedSettleTempOrderCreateResponse.class);

        //管理者・株式(PTS)出来入力取消共通リクエスト
        regClass(WEB3AdminEquityPTSInputCancelExecCommonRequest.class);
        regClass(WEB3AdminEquityPTSInputCancelExecCommonResponse.class);

        //管理者・株式(PTS)出来入力リクエスト・レスポンス
        regClass(WEB3AdminEquityPTSInputExecInputRequest.class);
        regClass(WEB3AdminEquityPTSInputExecInputResponse.class);

        //管理者・株式(PTS)出来入力確認リクエスト・レスポンス
        regClass(WEB3AdminEquityPTSInputExecConfirmRequest.class);
        regClass(WEB3AdminEquityPTSInputExecConfirmResponse.class);

        //管理者・株式（PTS）出来入力完了リクエスト・レスポンス
        regClass(WEB3AdminEquityPTSInputExecCompleteRequest.class);
        regClass(WEB3AdminEquityPTSInputExecCompleteResponse.class);

        //管理者・株式（PTS）出来取消入力リクエスト・レスポンス
        regClass(WEB3AdminEquityPTSCancelExecInputRequest.class);
        regClass(WEB3AdminEquityPTSCancelExecInputResponse.class);
        
        //管理者・株式（PTS）出来取消確認リクエスト・レスポンス
        regClass(WEB3AdminEquityPTSCancelExecConfirmRequest.class);
        regClass(WEB3AdminEquityPTSCancelExecConfirmResponse.class);

        //管理者・株式（PTS）出来取消完了リクエスト・レスポンス
        regClass(WEB3AdminEquityPTSCancelExecCompleteRequest.class);
        regClass(WEB3AdminEquityPTSCancelExecCompleteResponse.class);

        //管理者・強制決済注文ダウンロード入力リクエスト・レスポンス
        regClass(WEB3AdminForcedSettleDownloadInputRequest.class);
        regClass(WEB3AdminForcedSettleDownloadInputResponse.class);

        //管理者・強制決済注文ダウンロードリクエスト・レスポンス
        regClass(WEB3AdminForcedSettleDownloadRequest.class);
        regClass(WEB3AdminForcedSettleDownloadResponse.class);
 
        //管理者・注意情報照会入力リクエスト・レスポンス
        regClass(WEB3AdminEqAttentionInfoRefInpRequest.class);
        regClass(WEB3AdminEqAttentionInfoRefInpResponse.class);

        //管理者・注意情報照会リクエスト・レスポンス
        regClass(WEB3AdminEqAttentionInfoRefRefRequest.class);
        regClass(WEB3AdminEqAttentionInfoRefRefResponse.class);

        //注意情報通知リクエスト・レスポンス
        regClass(WEB3AdminEquityAttentionInfoNotifyRequest.class);
        regClass(WEB3AdminEquityAttentionInfoNotifyResponse.class);

        // ハンドラーの登録
        // 管理者顧客銘柄別取引停止登録ハンドラ
        regHandler(WEB3EqtypeAdminAppPlugin.class,
            WEB3AdminPMAccProductTradeStopRegistInputRequest.class,
            WEB3AdminEquityAccProductTradeStopRegistHandler.class,
            "getInputScreen");
        regHandler(
            WEB3EqtypeAdminAppPlugin.class,
            WEB3AdminPMAccProductTradeStopRegistConfirmRequest.class,
            WEB3AdminEquityAccProductTradeStopRegistHandler.class,
            "confirmRegist");
        regHandler(
            WEB3EqtypeAdminAppPlugin.class,
            WEB3AdminPMAccProductTradeStopRegistCompleteRequest.class,
            WEB3AdminEquityAccProductTradeStopRegistHandler.class,
            "completeRegist");

        // 管理者顧客銘柄別取引停止変更ハンドラ
        regHandler(
            WEB3EqtypeAdminAppPlugin.class,
            WEB3AdminPMAccProductTradeStopModifyInputRequest.class,
            WEB3AdminEquityAccProductTradeStopChangeHandler.class,
            "getInputScreen");
        regHandler(
            WEB3EqtypeAdminAppPlugin.class,
            WEB3AdminPMAccProductTradeStopModifyConfirmRequest.class,
            WEB3AdminEquityAccProductTradeStopChangeHandler.class,
            "confirmChange");
        regHandler(
            WEB3EqtypeAdminAppPlugin.class,
            WEB3AdminPMAccProductTradeStopModifyCompleteRequest.class,
            WEB3AdminEquityAccProductTradeStopChangeHandler.class,
            "completeChange");

        // 管理者顧客銘柄別取引停止一覧ハンドラ
        regHandler(
            WEB3EqtypeAdminAppPlugin.class,
            WEB3AdminPMAccProductTradeStopListRequest.class,
            WEB3AdminEquityAccProductTradeStopListHandler.class,
            "getListScreen");

        // 管理者株式銘柄条件予定一覧ハンドラ
        regHandler(
            WEB3EqtypeAdminAppPlugin.class,
            WEB3AdminPMProductCondListInputRequest.class,
            WEB3AdminEquityProductCondScheduleHandler.class,
            "getInputScreen");

        regHandler(
            WEB3EqtypeAdminAppPlugin.class,
            WEB3AdminPMProductCondListRequest.class,
            WEB3AdminEquityProductCondScheduleHandler.class,
            "getSchedule");

        // 管理者顧客銘柄別取引停止削除ハンドラ
        regHandler(
            WEB3EqtypeAdminAppPlugin.class,
            WEB3AdminPMAccProductTradeStopDeleteConfirmRequest.class,
            WEB3AdminEquityAccProductTradeStopDeleteHandler.class,
            "confirmDelete");

        regHandler(
            WEB3EqtypeAdminAppPlugin.class,
            WEB3AdminPMAccProductTradeStopDeleteCompleteRequest.class,
            WEB3AdminEquityAccProductTradeStopDeleteHandler.class,
            "completeDelete");

        // 管理者株式銘柄条件照会ハンドラ
        regHandler(
            WEB3EqtypeAdminAppPlugin.class,
            WEB3AdminPMProductCondRefInputRequest.class,
            WEB3AdminEquityProductCondReferenceHandler.class,
            "getProductInputScreen");

        regHandler(
            WEB3EqtypeAdminAppPlugin.class,
            WEB3AdminPMProductCondRefReferenceRequest.class,
            WEB3AdminEquityProductCondReferenceHandler.class,
            "getReferenceScreen");

        // 管理者立会外分売銘柄一覧ハンドラ
        regHandler(
            WEB3EqtypeAdminAppPlugin.class,
            WEB3AdminOffFloorProductListRequest.class,
            WEB3AdminOffFloorProductListHandler.class,
            "getProductList");

        // 管理者立会外分売銘柄更新ハンドラ
        regHandler(
            WEB3EqtypeAdminAppPlugin.class,
            WEB3AdminOffFloorChangeInputRequest.class,
            WEB3AdminOffFloorChangeHandler.class,
            "getInputScreen");

        regHandler(
            WEB3EqtypeAdminAppPlugin.class,
            WEB3AdminOffFloorChangeConfirmRequest.class,
            WEB3AdminOffFloorChangeHandler.class,
            "validateChange");
        regHandler(
            WEB3EqtypeAdminAppPlugin.class,
            WEB3AdminOffFloorChangeCompleteRequest.class,
            WEB3AdminOffFloorChangeHandler.class,
            "submitChange");

        // 管理者立会外分売銘柄削除ハンドラ
        regHandler(
            WEB3EqtypeAdminAppPlugin.class,
            WEB3AdminOffFloorDeleteConfirmRequest.class,
            WEB3AdminOffFloorDeleteHandler.class,
            "validateDelete");
        regHandler(
            WEB3EqtypeAdminAppPlugin.class,
            WEB3AdminOffFloorDeleteCompleteRequest.class,
            WEB3AdminOffFloorDeleteHandler.class,
            "submitDelete");

        // 管理者立会外分売銘柄新規登録ハンドラ
        regHandler(
            WEB3EqtypeAdminAppPlugin.class,
            WEB3AdminOffFloorRegistInputRequest.class,
            WEB3AdminOffFloorRegistHandler.class,
            "getInputScreen");
        regHandler(
            WEB3EqtypeAdminAppPlugin.class,
            WEB3AdminOffFloorRegistCompleteRequest.class,
            WEB3AdminOffFloorRegistHandler.class,
            "submitRegist");
        regHandler(
            WEB3EqtypeAdminAppPlugin.class,
            WEB3AdminOffFloorRegistConfirmRequest.class,
            WEB3AdminOffFloorRegistHandler.class,
            "validateRegist");

        // 管理者株式銘柄条件設定ハンドラ
        regHandler(
            WEB3EqtypeAdminAppPlugin.class,
            WEB3AdminPMProductCondConfInputRequest.class,
            WEB3AdminEquityProductCondSettingHandler.class,
            "getConditionSettingInputScreen");

        regHandler(
            WEB3EqtypeAdminAppPlugin.class,
            WEB3AdminPMProductCondConfConfirmRequest.class,
            WEB3AdminEquityProductCondSettingHandler.class,
            "confirmConditionSetting");

        regHandler(
            WEB3EqtypeAdminAppPlugin.class,
            WEB3AdminPMProductCondConfCompleteRequest.class,
            WEB3AdminEquityProductCondSettingHandler.class,
            "completeConditionSetting");

        // 管理者通知履歴参照ハンドラ
        regHandler(
            WEB3EqtypeAdminAppPlugin.class,
            WEB3AdminFrontNoticeHistoryInputRequest.class,
            WEB3AdminFrontNoticeHistoryHandler.class,
            "getInputScreen");
            
        regHandler(
            WEB3EqtypeAdminAppPlugin.class,
            WEB3AdminFrontNoticeHistoryReferenceRequest.class,
            WEB3AdminFrontNoticeHistoryHandler.class,
            "getReferenceScreen");
        
        //管理者・株式手動失効ハンドラ
        regHandler(
            WEB3EqtypeAdminAppPlugin.class,
            WEB3AdminEquityManualLapseInputRequest.class,
            WEB3AdminEquityManualExpireHandler.class,
            "getInputScreen");
        
        regHandler(
            WEB3EqtypeAdminAppPlugin.class,
            WEB3AdminEquityManualLapseConfirmRequest.class,
            WEB3AdminEquityManualExpireHandler.class,
            "confirmManualExpire");
        
        regHandler(
            WEB3EqtypeAdminAppPlugin.class,
            WEB3AdminEquityManualLapseRunRequest.class,
            WEB3AdminEquityManualExpireHandler.class,
            "runManualExpire");
        
        regHandler(
            WEB3EqtypeAdminAppPlugin.class,
            WEB3AdminEquityManualLapseStatusRequest.class,
            WEB3AdminEquityManualExpireHandler.class,
            "confirmStatus");
        
        //管理者・株式手動失効メインハンドラ
        regHandler(
            WEB3EqtypeAdminAppPlugin.class,
            WEB3AdminEquityManualLapseMainRequest.class,
            WEB3AdminEquityManualExpireMainHandler.class,
            "manualExpireRequest");

        //管理者・強制決済仮注文承認／非承認ハンドラ
        regHandler(
            WEB3EqtypeAdminAppPlugin.class,
            WEB3AdminForcedSettleApproveConfirmRequest.class,
            WEB3AdminEquityForcedSettleOrderApproveHandler.class,
            "confirmApprove");

        regHandler(
            WEB3EqtypeAdminAppPlugin.class,
            WEB3AdminForcedSettleApproveRunRequest.class,
            WEB3AdminEquityForcedSettleOrderApproveHandler.class,
            "runApprove");

        regHandler(
            WEB3EqtypeAdminAppPlugin.class,
            WEB3AdminForcedSettleApproveStatusRequest.class,
            WEB3AdminEquityForcedSettleOrderApproveHandler.class,
            "confirmStatus");

        //管理者・強制決済仮注文承認／非承認メインハンドラ
        regHandler(
            WEB3EqtypeAdminAppPlugin.class,
            WEB3AdminEquityForcedSettleOrderApproveMainRequest.class,
            WEB3AdminEquityForcedSettleOrderApproveMainHandler.class,
            "approveRequest");

        //強制決済仮注文作成ハンドラ
        regHandler(
            WEB3EqtypeAdminAppPlugin.class,
            WEB3AdminEquityForcedSettleTempOrderCreateRequest.class,
            WEB3AdminEquityForcedSettleTempOrderCreateHandler.class,
            "completeForcedSettleOrderCreate");

        //管理者・強制決済注文照会ハンドラ
        regHandler(
            WEB3EqtypeAdminAppPlugin.class,
            WEB3AdminForcedSettleRefInputRequest.class,
            WEB3AdminEquityForcedSettleReferenceHandler.class,
            "getInputScreen");

        regHandler(
            WEB3EqtypeAdminAppPlugin.class,
            WEB3AdminForcedSettleReferenceRequest.class,
            WEB3AdminEquityForcedSettleReferenceHandler.class,
            "getReferenceScreen");

        //管理者・株式（PTS）出来入力ハンドラ
        regHandler(
            WEB3EqtypeAdminAppPlugin.class,
            WEB3AdminEquityPTSInputExecInputRequest.class,
            WEB3AdminEquityPTSInputExecHandler.class,
            "getInputScreen");

        regHandler(
            WEB3EqtypeAdminAppPlugin.class,
            WEB3AdminEquityPTSInputExecConfirmRequest.class,
            WEB3AdminEquityPTSInputExecHandler.class,
            "validateInputExec");

        regHandler(
            WEB3EqtypeAdminAppPlugin.class,
            WEB3AdminEquityPTSInputExecCompleteRequest.class,
            WEB3AdminEquityPTSInputExecHandler.class,
            "submitInputExec");

        //管理者・株式（PTS）出来取消ハンドラ
        regHandler(
            WEB3EqtypeAdminAppPlugin.class,
            WEB3AdminEquityPTSCancelExecInputRequest.class,
            WEB3AdminEquityPTSCancelExecHandler.class,
            "getInputScreen");

        regHandler(
            WEB3EqtypeAdminAppPlugin.class,
            WEB3AdminEquityPTSCancelExecConfirmRequest.class,
            WEB3AdminEquityPTSCancelExecHandler.class,
            "validateCancelExec");

        regHandler(
            WEB3EqtypeAdminAppPlugin.class,
            WEB3AdminEquityPTSCancelExecCompleteRequest.class,
            WEB3AdminEquityPTSCancelExecHandler.class,
            "submitCancelExec");
   
        //管理者・強制決済注文DLハンドラ
        regHandler(
            WEB3EqtypeAdminAppPlugin.class,
            WEB3AdminForcedSettleDownloadInputRequest.class,
            WEB3AdminEquityForcedSettleOrderDLHandler.class,
            "getInputScreen");

        regHandler(
            WEB3EqtypeAdminAppPlugin.class,
            WEB3AdminForcedSettleDownloadRequest.class,
            WEB3AdminEquityForcedSettleOrderDLHandler.class,
            "getDownloadFile");

        //管理者注意情報照会ハンドラ
        regHandler(
            WEB3EqtypeAdminAppPlugin.class,
            WEB3AdminEqAttentionInfoRefInpRequest.class,
            WEB3AdminEquityAttentionInfoReferenceHandler.class,
            "getInputScreen");

        regHandler(
            WEB3EqtypeAdminAppPlugin.class,
            WEB3AdminEqAttentionInfoRefRefRequest.class,
            WEB3AdminEquityAttentionInfoReferenceHandler.class,
            "getReferenceScreen");

        //注意情報通知ハンドラ
        regHandler(
            WEB3EqtypeAdminAppPlugin.class,
            WEB3AdminEquityAttentionInfoNotifyRequest.class,
            WEB3AdminEquityAttentionInfoNotifyHandler.class,
            "attentionInfoNotifyRequest");

        //RAC-CTXインタセプタの設定
        //管理者・強制決済仮注文承認／非承認一件サービス
        Services.addInterceptor(WEB3AdminEquityForcedSettleOrderApproveUnitService.class,
            new WEB3AdminEquityForcedSettleOrderApproveRacCtxInterceptor());

        //RAC-CTXインタセプタの設定
        //管理者・強制決済期日到来建仮注文作成一件サービス
        Services.addInterceptor(WEB3AdminEquityForcedSettleCloseDateTempOrderCreateUnitService.class,
            new WEB3AdminEquityForcedSettleOrderRacCtxInterceptor());
        //管理者・強制決済保証金維持率割れ（オンライン開始前）仮注文作成一件サービス
        Services.addInterceptor(WEB3AdminEquityForcedSettleBelowMarginRateBefOnlineTempOrderCreateUnitService.class,
            new WEB3AdminEquityForcedSettleOrderRacCtxInterceptor());
        //管理者・強制決済保証金維持率割れ（場間）仮注文作成一件サービス
        Services.addInterceptor(WEB3AdminEquityForcedSettleBelowMarginRateIntermissionTempOrderCreateUnitService.class,
            new WEB3AdminEquityForcedSettleOrderRacCtxInterceptor());

        log.exiting(METHOD_NAME);
    }
}@
