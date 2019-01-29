head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.15.04.53.55;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	88c4d7eeee91bda;
filename	WEB3InformAppPlugin.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : Webbroker3-Inform プラグインクラス(WEB3InformAppPlugin.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2005/01/25 凌建平 (中訊) 新規作成
Revesion History : 2007/05/29 謝旋 (中訊) モデルNo.045,046
Revesion History : 2007/06/06 徐宏偉 (中訊) 利金・分配金登録伝票作成対応
Revesion History : 2007/07/23 金傑 (中訊) モデルNo.099
Revesion History : 2007/07/23 張騰宇 (中訊) モデルNo.098
Revesion History : 2007/07/30 金傑 (中訊) モデルNo.100
Revesion History : 2007/09/21 趙林鵬 (中訊) モデルNo.110,112
Revesion History : 2008/02/25 謝旋 (中訊) モデルNo.122,123,124,125,127
Revesion History : 2008/03/05 柴双紅 (中訊) モデルNo.128,129,130
Revesion History : 2009/06/29 柴双紅 (中訊) DBレイアウトNo.013
*/

package webbroker3.inform;

import com.fitechlabs.xtrade.kernel.boot.KernelPlugin;
import com.fitechlabs.xtrade.kernel.boot.Plugin;
import com.fitechlabs.xtrade.kernel.boot.Services;
import com.fitechlabs.xtrade.kernel.interceptor.TransactionalInterceptor;

import webbroker3.common.WEB3LogSysTimeInterceptor;
import webbroker3.inform.data.WEB3InformMasterDatabaseExtensions;
import webbroker3.inform.data.WEB3InformSessionDatabaseExtensions;
import webbroker3.inform.handler.WEB3AdminInformPTSAccOpenStateChangeHandler;
import webbroker3.inform.handler.WEB3AdminInformPTSAccountListHandler;
import webbroker3.inform.handler.WEB3AdminInformProfDistRegistVoucherMakeHandler;
import webbroker3.inform.handler.WEB3AdminInformProfDistSellTransSrcListHandler;
import webbroker3.inform.handler.WEB3AdminInformReferenceHandler;
import webbroker3.inform.handler.WEB3AdminInformSwElecDeliApplyHandler;
import webbroker3.inform.handler.WEB3InformAccSwElecDeliApplyHandler;
import webbroker3.inform.handler.WEB3InformInputHandler;
import webbroker3.inform.handler.WEB3InformPTSAccOpenApplyHandler;
import webbroker3.inform.message.WEB3AdminInformAccSwElecDeliApplyCmpRequest;
import webbroker3.inform.message.WEB3AdminInformAccSwElecDeliApplyCmpResponse;
import webbroker3.inform.message.WEB3AdminInformAccSwElecDeliApplyConfRequest;
import webbroker3.inform.message.WEB3AdminInformAccSwElecDeliApplyConfResponse;
import webbroker3.inform.message.WEB3AdminInformAccSwElecDeliApplyInpRequest;
import webbroker3.inform.message.WEB3AdminInformAccSwElecDeliApplyInpResponse;
import webbroker3.inform.message.WEB3AdminInformAccSwElecDeliApplyRefRequest;
import webbroker3.inform.message.WEB3AdminInformAccSwElecDeliApplyRefResponse;
import webbroker3.inform.message.WEB3AdminInformAccSwElecDeliApplySrcRequest;
import webbroker3.inform.message.WEB3AdminInformAccSwElecDeliApplySrcResponse;
import webbroker3.inform.message.WEB3AdminInformAccSwElecDeliChangeCmpRequest;
import webbroker3.inform.message.WEB3AdminInformAccSwElecDeliChangeCmpResponse;
import webbroker3.inform.message.WEB3AdminInformAccSwElecDeliChangeConfRequest;
import webbroker3.inform.message.WEB3AdminInformAccSwElecDeliChangeConfResponse;
import webbroker3.inform.message.WEB3AdminInformAccSwElecDeliDeleteCmpRequest;
import webbroker3.inform.message.WEB3AdminInformAccSwElecDeliDeleteCmpResponse;
import webbroker3.inform.message.WEB3AdminInformAccSwElecDeliDeleteConfRequest;
import webbroker3.inform.message.WEB3AdminInformAccSwElecDeliDeleteConfResponse;
import webbroker3.inform.message.WEB3AdminInformDetailRequest;
import webbroker3.inform.message.WEB3AdminInformDetailResponse;
import webbroker3.inform.message.WEB3AdminInformDownLoadRequest;
import webbroker3.inform.message.WEB3AdminInformDownLoadResponse;
import webbroker3.inform.message.WEB3AdminInformInputRequest;
import webbroker3.inform.message.WEB3AdminInformInputResponse;
import webbroker3.inform.message.WEB3AdminInformListRequest;
import webbroker3.inform.message.WEB3AdminInformListResponse;
import webbroker3.inform.message.WEB3AdminInformPTSAccOpenStateChangeCmpRequest;
import webbroker3.inform.message.WEB3AdminInformPTSAccOpenStateChangeCmpResponse;
import webbroker3.inform.message.WEB3AdminInformPTSAccOpenStateChangeCnfRequest;
import webbroker3.inform.message.WEB3AdminInformPTSAccOpenStateChangeCnfResponse;
import webbroker3.inform.message.WEB3AdminInformPTSAccOpenStateChangeInpRequest;
import webbroker3.inform.message.WEB3AdminInformPTSAccOpenStateChangeInpResponse;
import webbroker3.inform.message.WEB3AdminInformPTSAccOpenStateChangeSrcRequest;
import webbroker3.inform.message.WEB3AdminInformPTSAccOpenStateChangeSrcResponse;
import webbroker3.inform.message.WEB3AdminInformPTSAccountListInquiryRequest;
import webbroker3.inform.message.WEB3AdminInformPTSAccountListInquiryResponse;
import webbroker3.inform.message.WEB3AdminInformPTSAccountListResultRequest;
import webbroker3.inform.message.WEB3AdminInformPTSAccountListResultResponse;
import webbroker3.inform.message.WEB3AdminInformProfDistSellTransSrcInpRequest;
import webbroker3.inform.message.WEB3AdminInformProfDistSellTransSrcInpResponse;
import webbroker3.inform.message.WEB3AdminInformProfDistSellTransSrcListRequest;
import webbroker3.inform.message.WEB3AdminInformProfDistSellTransSrcListResponse;
import webbroker3.inform.message.WEB3AdminInformProfDistStatusSearchInputRequest;
import webbroker3.inform.message.WEB3AdminInformProfDistStatusSearchInputResponse;
import webbroker3.inform.message.WEB3AdminInformProfDistVoucherCancCmpRequest;
import webbroker3.inform.message.WEB3AdminInformProfDistVoucherCancCmpResponse;
import webbroker3.inform.message.WEB3AdminInformProfDistVoucherCancCnfRequest;
import webbroker3.inform.message.WEB3AdminInformProfDistVoucherCancCnfResponse;
import webbroker3.inform.message.WEB3AdminInformProfDistVoucherChgCmpRequest;
import webbroker3.inform.message.WEB3AdminInformProfDistVoucherChgCmpResponse;
import webbroker3.inform.message.WEB3AdminInformProfDistVoucherChgCnfRequest;
import webbroker3.inform.message.WEB3AdminInformProfDistVoucherChgCnfResponse;
import webbroker3.inform.message.WEB3AdminInformProfDistVoucherInfoRefRequest;
import webbroker3.inform.message.WEB3AdminInformProfDistVoucherInfoRefResponse;
import webbroker3.inform.message.WEB3AdminInformProfDistVoucherMakeCmpRequest;
import webbroker3.inform.message.WEB3AdminInformProfDistVoucherMakeCmpResponse;
import webbroker3.inform.message.WEB3AdminInformProfDistVoucherMakeCnfRequest;
import webbroker3.inform.message.WEB3AdminInformProfDistVoucherMakeCnfResponse;
import webbroker3.inform.message.WEB3AdminInformProfDistVoucherMakeInpRequest;
import webbroker3.inform.message.WEB3AdminInformProfDistVoucherMakeInpResponse;
import webbroker3.inform.message.WEB3InformAccSwElecDeliApplyCmpRequest;
import webbroker3.inform.message.WEB3InformAccSwElecDeliApplyCmpResponse;
import webbroker3.inform.message.WEB3InformAccSwElecDeliApplyConfRequest;
import webbroker3.inform.message.WEB3InformAccSwElecDeliApplyConfResponse;
import webbroker3.inform.message.WEB3InformAccSwElecDeliApplyInpRequest;
import webbroker3.inform.message.WEB3InformAccSwElecDeliApplyInpResponse;
import webbroker3.inform.message.WEB3InformCompleteRequest;
import webbroker3.inform.message.WEB3InformCompleteResponse;
import webbroker3.inform.message.WEB3InformConfirmRequest;
import webbroker3.inform.message.WEB3InformConfirmResponse;
import webbroker3.inform.message.WEB3InformInputRequest;
import webbroker3.inform.message.WEB3InformInputResponse;
import webbroker3.inform.message.WEB3InformPTSAccOpenApplyCmpRequest;
import webbroker3.inform.message.WEB3InformPTSAccOpenApplyCmpResponse;
import webbroker3.inform.message.WEB3InformPTSAccOpenApplyCnfRequest;
import webbroker3.inform.message.WEB3InformPTSAccOpenApplyCnfResponse;
import webbroker3.inform.message.WEB3InformPTSAccOpenApplyInpRequest;
import webbroker3.inform.message.WEB3InformPTSAccOpenApplyInpResponse;
import webbroker3.inform.service.delegate.WEB3AdminInformPTSAccOpenStateChangeService;
import webbroker3.inform.service.delegate.WEB3AdminInformPTSAccountListService;
import webbroker3.inform.service.delegate.WEB3AdminInformProfDistRegistVoucherMakeService;
import webbroker3.inform.service.delegate.WEB3AdminInformProfDistSellTransSrcListService;
import webbroker3.inform.service.delegate.WEB3AdminInformReferenceService;
import webbroker3.inform.service.delegate.WEB3AdminInformSwElecDeliApplyService;
import webbroker3.inform.service.delegate.WEB3InformAccSwElecDeliApplyCommonService;
import webbroker3.inform.service.delegate.WEB3InformAccSwElecDeliApplyService;
import webbroker3.inform.service.delegate.WEB3InformHostReqOrderNumberManageService;
import webbroker3.inform.service.delegate.WEB3InformInputService;
import webbroker3.inform.service.delegate.WEB3InformPTSAccOpenApplyService;
import webbroker3.inform.service.delegate.stdimpls.WEB3AdminInformPTSAccOpenStateChangeServiceImpl;
import webbroker3.inform.service.delegate.stdimpls.WEB3AdminInformPTSAccountListServiceImpl;
import webbroker3.inform.service.delegate.stdimpls.WEB3AdminInformProfDistRegistVoucherMakeServiceImpl;
import webbroker3.inform.service.delegate.stdimpls.WEB3AdminInformProfDistSellTransSrcListServiceImpl;
import webbroker3.inform.service.delegate.stdimpls.WEB3AdminInformReferenceServiceImpl;
import webbroker3.inform.service.delegate.stdimpls.WEB3AdminInformSwElecDeliApplyServiceImpl;
import webbroker3.inform.service.delegate.stdimpls.WEB3InformAccSwElecDeliApplyCommonServiceImpl;
import webbroker3.inform.service.delegate.stdimpls.WEB3InformAccSwElecDeliApplyServiceImpl;
import webbroker3.inform.service.delegate.stdimpls.WEB3InformHostReqOrderNumberManageServiceImpl;
import webbroker3.inform.service.delegate.stdimpls.WEB3InformInputServiceImpl;
import webbroker3.inform.service.delegate.stdimpls.WEB3InformPTSAccOpenApplyServiceImpl;
import webbroker3.mqgateway.WEB3MQGatewayInterceptor;
import webbroker3.util.WEB3LogUtility;

/**
 * Webbroker3-Inform プラグインクラス。
 * @@author 凌建平
 * @@version 1.0
 */
public final class WEB3InformAppPlugin extends Plugin
{
    /**
     * ログユーティリティ。
     */
    private static WEB3LogUtility log =
        WEB3LogUtility.getInstance(
            WEB3InformAppPlugin.class);

    /**
     * コンストラクタ。
     */
    public WEB3InformAppPlugin()
    {

    }

    /**
     * プラグインエントリーポイント。
     */
    public static void plug() throws Exception
    {
        String METHOD_NAME = "plug()";
        log.entering(METHOD_NAME);

        plug(WEB3InformAppPlugin.class);

        log.exiting(METHOD_NAME);
    }

    /**
     * プラグイン処理。
     */
    public static void onPlug() throws Exception
    {
        String METHOD_NAME = "onPlug()";
        log.entering(METHOD_NAME);
        
        WEB3InformMasterDatabaseExtensions.plug();
        WEB3InformSessionDatabaseExtensions.plug();

        KernelPlugin.plug();

        // ---------------------- 1 Service の登録処理 ----------------------
        //連絡入力サービス
        Services.registerService(
            WEB3InformInputService.class,
            new WEB3InformInputServiceImpl());

        //連絡情報検索サービス
        Services.registerService(
            WEB3AdminInformReferenceService.class,
            new WEB3AdminInformReferenceServiceImpl());

        //連絡管理識別コード採番サービス
        Services.registerService(
            WEB3InformHostReqOrderNumberManageService.class,
            new WEB3InformHostReqOrderNumberManageServiceImpl());

        //利金・分配金・売却代金振込先一覧サービスインターフェイス
        Services.registerService(WEB3AdminInformProfDistSellTransSrcListService.class,
            new WEB3AdminInformProfDistSellTransSrcListServiceImpl());

        //利金・分配金登録伝票作成サービスインターフェイス
        Services.registerService(
            WEB3AdminInformProfDistRegistVoucherMakeService.class,
            new WEB3AdminInformProfDistRegistVoucherMakeServiceImpl());

        // 口座切替・電子交付申込共通サービスインターフェイス
        Services.registerService(
            WEB3InformAccSwElecDeliApplyCommonService.class,
            new WEB3InformAccSwElecDeliApplyCommonServiceImpl());

        //管理者口座切替・電子交付申込サービス
        Services.registerService(
            WEB3AdminInformSwElecDeliApplyService.class,
            new WEB3AdminInformSwElecDeliApplyServiceImpl());

        //口座切替・電子交付申込サービス
        Services.registerService(
            WEB3InformAccSwElecDeliApplyService.class,
            new WEB3InformAccSwElecDeliApplyServiceImpl());

        //PTS口座開設申込サービス
        Services.registerService(
            WEB3InformPTSAccOpenApplyService.class,
            new WEB3InformPTSAccOpenApplyServiceImpl());

        //管理者PTS口座開設状況変更サービス
        Services.registerService(
            WEB3AdminInformPTSAccOpenStateChangeService.class,
            new WEB3AdminInformPTSAccOpenStateChangeServiceImpl());

        //管理者PTS申込客一覧問合せサービス
        Services.registerService(
            WEB3AdminInformPTSAccountListService.class,
            new WEB3AdminInformPTSAccountListServiceImpl());


        // ---------------------- 2 Service.execute()呼び出し前後に  ----------------------
        // 処理開始時刻と処理終了時刻をログ出力するように設定する
        //連絡入力サービス
        Services.addInterceptor(
            WEB3InformInputService.class,
            new WEB3LogSysTimeInterceptor());

        //連絡情報検索サービス
        Services.addInterceptor(
            WEB3AdminInformReferenceService.class,
            new WEB3LogSysTimeInterceptor());

        //利金・分配金・売却代金振込先一覧サービスインターフェイス
        Services.addInterceptor(WEB3AdminInformProfDistSellTransSrcListService.class,
            new WEB3LogSysTimeInterceptor());

        //利金・分配金登録伝票作成サービスインターフェイス
        Services.addInterceptor(
            WEB3AdminInformProfDistRegistVoucherMakeService.class,
            new WEB3LogSysTimeInterceptor());

        //管理者口座切替・電子交付申込サービス
        Services.addInterceptor(
            WEB3AdminInformSwElecDeliApplyService.class,
            new WEB3LogSysTimeInterceptor());

        //口座切替・電子交付申込サービス
        Services.addInterceptor(
            WEB3InformAccSwElecDeliApplyService.class,
            new WEB3LogSysTimeInterceptor());

        //PTS口座開設申込サービス
        Services.addInterceptor(
            WEB3InformPTSAccOpenApplyService.class,
            new WEB3LogSysTimeInterceptor());

        //管理者PTS口座開設状況変更サービス
        Services.addInterceptor(
            WEB3AdminInformPTSAccOpenStateChangeService.class,
            new WEB3LogSysTimeInterceptor());

        //管理者PTS申込客一覧問合せサービス
        Services.addInterceptor(
            WEB3AdminInformPTSAccountListService.class,
            new WEB3LogSysTimeInterceptor());

        // ---------------------- 3 Service に ServiceInterceptor を設定する ----------------------
        //連絡入力サービス
        Services.addInterceptor(
            WEB3InformInputService.class,
            new WEB3InformInputServiceInterceptor());

        //連絡情報検索サービス
        Services.addInterceptor(
            WEB3AdminInformReferenceService.class,
            new WEB3AdminInformReferenceServiceInterceptor());

        //利金・分配金・売却代金振込先一覧サービスインターフェイス
        Services.addInterceptor(WEB3AdminInformProfDistSellTransSrcListService.class,
            new WEB3AdminInformProfDistSellTransSrcListServiceInterceptor());

        //利金・分配金登録伝票作成サービスインターフェイス
        Services.addInterceptor(
            WEB3AdminInformProfDistRegistVoucherMakeService.class,
            new WEB3AdminInformProfDistRegistVoucherMakeInterceptor());

        //管理者口座切替・電子交付申込サービス
        Services.addInterceptor(
            WEB3AdminInformSwElecDeliApplyService.class,
            new WEB3AdminInformSwElecDeliApplyServiceInterceptor());

        //口座切替・電子交付申込サービス
        Services.addInterceptor(
            WEB3InformAccSwElecDeliApplyService.class,
            new WEB3InformAccSwElecDeliApplyServiceInterceptor());

        //PTS口座開設申込サービス
        Services.addInterceptor(
            WEB3InformPTSAccOpenApplyService.class,
            new WEB3InformPTSAccOpenApplyServiceInterceptor());

        //管理者PTS口座開設状況変更サービス
        Services.addInterceptor(
            WEB3AdminInformPTSAccOpenStateChangeService.class,
            new WEB3AdminInformPTSAccOpenStateChangeServiceInterceptor());

        //管理者PTS申込客一覧問合せサービス
        Services.addInterceptor(
            WEB3AdminInformPTSAccountListService.class,
            new WEB3AdminInformPTSAccountListServiceInterceptor());

        // ---------------------- 4 Service の Interceptor 設定処理 ----------------------
        // 自動トランザクション設定
        //連絡入力サービス
        Services.addInterceptor(
            WEB3InformInputService.class,
            new TransactionalInterceptor(
                TransactionalInterceptor.TX_JOIN_EXISTING));

        //連絡情報検索サービス
        Services.addInterceptor(
            WEB3AdminInformReferenceService.class,
            new TransactionalInterceptor(
                TransactionalInterceptor.TX_JOIN_EXISTING));

        //連絡管理識別コード採番サービス
        Services.addInterceptor(
            WEB3InformHostReqOrderNumberManageService.class,
            new TransactionalInterceptor(
                TransactionalInterceptor.TX_CREATE_NEW));

        //利金・分配金・売却代金振込先一覧サービスインターフェイス
        Services.addInterceptor(WEB3AdminInformProfDistSellTransSrcListService.class,
            new TransactionalInterceptor(
                TransactionalInterceptor.TX_JOIN_EXISTING));

        //利金・分配金登録伝票作成サービスインターフェイス
        Services.addInterceptor(
            WEB3AdminInformProfDistRegistVoucherMakeService.class,
            new TransactionalInterceptor(
                TransactionalInterceptor.TX_JOIN_EXISTING));

        // 口座切替・電子交付申込共通サービスインターフェイス
        Services.addInterceptor(
            WEB3InformAccSwElecDeliApplyCommonService.class,
            new TransactionalInterceptor(
                TransactionalInterceptor.TX_JOIN_EXISTING));

        //管理者口座切替・電子交付申込サービス
        Services.addInterceptor(
            WEB3AdminInformSwElecDeliApplyService.class,
            new TransactionalInterceptor(
                TransactionalInterceptor.TX_JOIN_EXISTING));

        //口座切替・電子交付申込サービス
        Services.addInterceptor(
            WEB3InformAccSwElecDeliApplyService.class,
            new TransactionalInterceptor(
                TransactionalInterceptor.TX_JOIN_EXISTING));

        //PTS口座開設申込サービス
        Services.addInterceptor(
            WEB3InformPTSAccOpenApplyService.class,
            new TransactionalInterceptor(
                TransactionalInterceptor.TX_JOIN_EXISTING));

        //管理者PTS口座開設状況変更サービス
        Services.addInterceptor(
            WEB3AdminInformPTSAccOpenStateChangeService.class,
            new TransactionalInterceptor(
                TransactionalInterceptor.TX_JOIN_EXISTING));

        //管理者PTS申込客一覧問合せサービス
        Services.addInterceptor(
            WEB3AdminInformPTSAccountListService.class,
            new TransactionalInterceptor(
                TransactionalInterceptor.TX_JOIN_EXISTING));

        // ---------------------- 5 Message の登録処理 ----------------------
        //連絡入力要求リクエスト
        regClass(WEB3InformInputRequest.class);
        //連絡入力応答レスポンス
        regClass(WEB3InformInputResponse.class);

        //連絡確認要求リクエスト
        regClass(WEB3InformConfirmRequest.class);
        //連絡確認応答レスポンス
        regClass(WEB3InformConfirmResponse.class);

        //連絡完了要求リクエスト
        regClass(WEB3InformCompleteRequest.class);
        //連絡完了応答レスポンス
        regClass(WEB3InformCompleteResponse.class);

        //連絡情報検索入力要求リクエスト
        regClass(WEB3AdminInformInputRequest.class);
        //連絡情報検索入力応答レスポンス
        regClass(WEB3AdminInformInputResponse.class);

        //連絡情報検索詳細要求リクエスト
        regClass(WEB3AdminInformDetailRequest.class);
        //連絡情報検索詳細応答レスポンス
        regClass(WEB3AdminInformDetailResponse.class);

        //連絡情報検索一覧要求リクエスト
        regClass(WEB3AdminInformListRequest.class);
        //連絡情報検索一覧応答レスポンス
        regClass(WEB3AdminInformListResponse.class);

        //連絡情報検索ダウンロード要求リクエスト
        regClass(WEB3AdminInformDownLoadRequest.class);
        //連絡情報検索ダウンロード応答レスポンス
        regClass(WEB3AdminInformDownLoadResponse.class);
        
        //利金・分配金・売却代金振込先検索入力リクエストクラス
        regClass(WEB3AdminInformProfDistSellTransSrcInpRequest.class);
        //利金・分配金・売却代金振込先検索入力レスポンスクラス
        regClass(WEB3AdminInformProfDistSellTransSrcInpResponse.class);

        //利金・分配金・売却代金振込先一覧リクエストクラス
        regClass(WEB3AdminInformProfDistSellTransSrcListRequest.class);
        //利金・分配金・売却代金振込先一覧レスポンスクラス
        regClass(WEB3AdminInformProfDistSellTransSrcListResponse.class);

        //管理者・利金・分配金登録状況問合せ入力リクエストクラス
        regClass(WEB3AdminInformProfDistStatusSearchInputRequest.class);
        //管理者・利金・分配金登録状況問合せ入力レスポンスクラス
        regClass(WEB3AdminInformProfDistStatusSearchInputResponse.class);
        //管理者・口座伝票作成入力リクエストクラス
        regClass(WEB3AdminInformProfDistVoucherMakeInpRequest.class);
        //管理者・口座伝票作成入力レスポンスクラス
        regClass(WEB3AdminInformProfDistVoucherMakeInpResponse.class);
        //管理者・口座伝票作成確認リクエストクラス
        regClass(WEB3AdminInformProfDistVoucherMakeCnfRequest.class);
        //管理者・口座伝票作成確認レスポンスクラス
        regClass(WEB3AdminInformProfDistVoucherMakeCnfResponse.class);
        //管理者・口座伝票作成完了リクエスト
        regClass(WEB3AdminInformProfDistVoucherMakeCmpRequest.class);
        //管理者・口座伝票作成完了レスポンスクラス
        regClass(WEB3AdminInformProfDistVoucherMakeCmpResponse.class);
        //管理者・伝票情報参照リクエストクラス
        regClass(WEB3AdminInformProfDistVoucherInfoRefRequest.class);
        //管理者・伝票情報参照レスポンスクラス
        regClass(WEB3AdminInformProfDistVoucherInfoRefResponse.class);
        //管理者・口座伝票変更確認リクエストクラス
        regClass(WEB3AdminInformProfDistVoucherChgCnfRequest.class);
        //管理者・口座伝票変更確認レスポンスクラス
        regClass(WEB3AdminInformProfDistVoucherChgCnfResponse.class);
        //管理者・口座伝票変更完了リクエストクラス
        regClass(WEB3AdminInformProfDistVoucherChgCmpRequest.class);
        //管理者・口座伝票変更完了レスポンスクラス
        regClass(WEB3AdminInformProfDistVoucherChgCmpResponse.class);
        //管理者・口座伝票取消確認リクエストクラス
        regClass(WEB3AdminInformProfDistVoucherCancCnfRequest.class);
        //管理者・口座伝票取消確認レスポンスクラス
        regClass(WEB3AdminInformProfDistVoucherCancCnfResponse.class);
        //管理者・口座伝票取消完了リクエストクラス
        regClass(WEB3AdminInformProfDistVoucherCancCmpRequest.class);
        //管理者・口座伝票取消完了レスポンスクラス
        regClass(WEB3AdminInformProfDistVoucherCancCmpResponse.class);

        //口座切替・電子交付申込入力リクエストクラス
        regClass(WEB3InformAccSwElecDeliApplyInpRequest.class);
        //口座切替・電子交付申込入力レスポンスクラス
        regClass(WEB3InformAccSwElecDeliApplyInpResponse.class);
        //口座切替・電子交付申込確認リクエストクラス
        regClass(WEB3InformAccSwElecDeliApplyConfRequest.class);
        //口座切替・電子交付申込確認レスポンスクラス
        regClass(WEB3InformAccSwElecDeliApplyConfResponse.class);
        //口座切替・電子交付申込完了リクエストクラス
        regClass(WEB3InformAccSwElecDeliApplyCmpRequest.class);
        //口座切替・電子交付申込完了レスポンスクラス
        regClass(WEB3InformAccSwElecDeliApplyCmpResponse.class);
        //管理者・口座切替・電子交付申込検索リクエストクラス
        regClass(WEB3AdminInformAccSwElecDeliApplySrcRequest.class);
        //管理者・口座切替・電子交付申込検索レスポンスクラス
        regClass(WEB3AdminInformAccSwElecDeliApplySrcResponse.class);
        //管理者・口座切替・電子交付申込入力リクエストクラス
        regClass(WEB3AdminInformAccSwElecDeliApplyInpRequest.class);
        //管理者・口座切替・電子交付申込入力レスポンスクラス
        regClass(WEB3AdminInformAccSwElecDeliApplyInpResponse.class);
        //管理者・口座切替・電子交付申込確認リクエストクラス
        regClass(WEB3AdminInformAccSwElecDeliApplyConfRequest.class);
        //管理者・口座切替・電子交付申込確認レスポンスクラス
        regClass(WEB3AdminInformAccSwElecDeliApplyConfResponse.class);
        //管理者・口座切替・電子交付申込完了リクエストクラス
        regClass(WEB3AdminInformAccSwElecDeliApplyCmpRequest.class);
        //管理者・口座切替・電子交付申込完了レスポンスクラス
        regClass(WEB3AdminInformAccSwElecDeliApplyCmpResponse.class);

        //管理者・口座切替・電子交付申込変更確認リクエスト
        regClass(WEB3AdminInformAccSwElecDeliChangeConfRequest.class);

        //管理者・口座切替・電子交付申込変更確認レスポンス
        regClass(WEB3AdminInformAccSwElecDeliChangeConfResponse.class);

        //管理者・口座切替・電子交付申込変更完了リクエスト
        regClass(WEB3AdminInformAccSwElecDeliChangeCmpRequest.class);

        //管理者・口座切替・電子交付申込変更完了レスポンス
        regClass(WEB3AdminInformAccSwElecDeliChangeCmpResponse.class);

        //管理者・口座切替・電子交付申込取消確認リクエスト
        regClass(WEB3AdminInformAccSwElecDeliDeleteConfRequest.class);

        //管理者・口座切替・電子交付申込取消確認レスポンス
        regClass(WEB3AdminInformAccSwElecDeliDeleteConfResponse.class);

        //管理者・口座切替・電子交付申込取消完了リクエスト
        regClass(WEB3AdminInformAccSwElecDeliDeleteCmpRequest.class);

        //管理者・口座切替・電子交付申込取消完了レスポンス
        regClass(WEB3AdminInformAccSwElecDeliDeleteCmpResponse.class);

        //管理者・口座切替・電子交付申込参照リクエスト
        regClass(WEB3AdminInformAccSwElecDeliApplyRefRequest.class);

        //管理者・口座切替・電子交付申込参照レスポンス
        regClass(WEB3AdminInformAccSwElecDeliApplyRefResponse.class);

        //PTS口座開設申込完了リクエスト
        regClass(WEB3InformPTSAccOpenApplyCmpRequest.class);
        //PTS口座開設申込完了レスポンス
        regClass(WEB3InformPTSAccOpenApplyCmpResponse.class);
        //PTS口座開設申込確認リクエスト
        regClass(WEB3InformPTSAccOpenApplyCnfRequest.class);
        //PTS口座開設申込確認レスポンス
        regClass(WEB3InformPTSAccOpenApplyCnfResponse.class);
        //PTS口座開設申込入力リクエスト
        regClass(WEB3InformPTSAccOpenApplyInpRequest.class);
        //PTS口座開設申込入力レスポンス
        regClass(WEB3InformPTSAccOpenApplyInpResponse.class);

        //管理者・PTS口座開設状況変更
        //管理者・PTS口座開設状況変更検索リクエスト
        regClass(WEB3AdminInformPTSAccOpenStateChangeSrcRequest.class);
        //管理者・PTS口座開設状況変更検索レスポンス
        regClass(WEB3AdminInformPTSAccOpenStateChangeSrcResponse.class);
        //管理者・PTS口座開設状況変更入力リクエスト
        regClass(WEB3AdminInformPTSAccOpenStateChangeInpRequest.class);
        //管理者・PTS口座開設状況変更入力レスポンス
        regClass(WEB3AdminInformPTSAccOpenStateChangeInpResponse.class);
        //管理者・PTS口座開設状況変更確認リクエスト
        regClass(WEB3AdminInformPTSAccOpenStateChangeCnfRequest.class);
        //管理者・PTS口座開設状況変更確認レスポンス
        regClass(WEB3AdminInformPTSAccOpenStateChangeCnfResponse.class);
        //管理者・PTS口座開設状況変更完了リクエスト
        regClass(WEB3AdminInformPTSAccOpenStateChangeCmpRequest.class);
        //管理者・PTS口座開設状況変更完了レスポンス
        regClass(WEB3AdminInformPTSAccOpenStateChangeCmpResponse.class);

        //管理者・PTS申込客一覧問合せ
        //管理者・PTS申込客一覧問合せ検索リクエスト
        regClass(WEB3AdminInformPTSAccountListInquiryRequest.class);
        //管理者・PTS申込客一覧問合せ検索レスポンス
        regClass(WEB3AdminInformPTSAccountListInquiryResponse.class);
        //管理者・PTS申込客一覧問合せ検索結果リクエスト
        regClass(WEB3AdminInformPTSAccountListResultRequest.class);
        //管理者・PTS申込客一覧問合せ検索結果レスポンス
        regClass(WEB3AdminInformPTSAccountListResultResponse.class);

        // ---------------------- 6 Handler の登録処理 ----------------------
        //連絡入力
        regHandler(
            WEB3InformAppPlugin.class,
            WEB3InformInputRequest.class,
            WEB3InformInputHandler.class,
            "informInputDisplay");

        regHandler(
            WEB3InformAppPlugin.class,
            WEB3InformConfirmRequest.class,
            WEB3InformInputHandler.class,
            "informConfirm");

        regHandler(
            WEB3InformAppPlugin.class,
            WEB3InformCompleteRequest.class,
            WEB3InformInputHandler.class,
            "informComplete");

        //連絡情報検索
        regHandler(
            WEB3InformAppPlugin.class,
            WEB3AdminInformInputRequest.class,
            WEB3AdminInformReferenceHandler.class,
            "informInputDisplay");

        regHandler(
            WEB3InformAppPlugin.class,
            WEB3AdminInformListRequest.class,
            WEB3AdminInformReferenceHandler.class,
            "informListDisplay");

        regHandler(
            WEB3InformAppPlugin.class,
            WEB3AdminInformDetailRequest.class,
            WEB3AdminInformReferenceHandler.class,
            "informDetailDisplay");

        regHandler(
            WEB3InformAppPlugin.class,
            WEB3AdminInformDownLoadRequest.class,
            WEB3AdminInformReferenceHandler.class,
            "allInformDownload");

        //利金・分配金・売却代金振込先一覧
        regHandler(
            WEB3InformAppPlugin.class,
            WEB3AdminInformProfDistSellTransSrcInpRequest.class,
            WEB3AdminInformProfDistSellTransSrcListHandler.class,
            "displayInputScreen");

        regHandler(
            WEB3InformAppPlugin.class,
            WEB3AdminInformProfDistSellTransSrcListRequest.class,
            WEB3AdminInformProfDistSellTransSrcListHandler.class,
            "displayListScreen");

        //----------利金・分配金登録伝票作成start----------------->
        //登録状況検索
        regHandler(
            WEB3InformAppPlugin.class,
            WEB3AdminInformProfDistStatusSearchInputRequest.class,
            WEB3AdminInformProfDistRegistVoucherMakeHandler.class,
            "registStatusSearch");

        //伝票作成入力
        regHandler(
            WEB3InformAppPlugin.class,
            WEB3AdminInformProfDistVoucherMakeInpRequest.class,
            WEB3AdminInformProfDistRegistVoucherMakeHandler.class,
            "voucherMakeInp");

        //伝票作成確認
        regHandler(
            WEB3InformAppPlugin.class,
            WEB3AdminInformProfDistVoucherMakeCnfRequest.class,
            WEB3AdminInformProfDistRegistVoucherMakeHandler.class,
            "voucherMakeCnf");

        //伝票作成完了
        regHandler(
            WEB3InformAppPlugin.class,
            WEB3AdminInformProfDistVoucherMakeCmpRequest.class,
            WEB3AdminInformProfDistRegistVoucherMakeHandler.class,
            "voucherMakeCmp");

        //登録口座参照
        regHandler(
            WEB3InformAppPlugin.class,
            WEB3AdminInformProfDistVoucherInfoRefRequest.class,
            WEB3AdminInformProfDistRegistVoucherMakeHandler.class,
            "registAccountRef");

        //登録口座変更確認
        regHandler(
            WEB3InformAppPlugin.class,
            WEB3AdminInformProfDistVoucherChgCnfRequest.class,
            WEB3AdminInformProfDistRegistVoucherMakeHandler.class,
            "registAccountChgeCnf");

        //登録口座変更完了
        regHandler(
            WEB3InformAppPlugin.class,
            WEB3AdminInformProfDistVoucherChgCmpRequest.class,
            WEB3AdminInformProfDistRegistVoucherMakeHandler.class,
            "registAccountChgeCmp");

        //登録口座取消確認
        regHandler(
            WEB3InformAppPlugin.class,
            WEB3AdminInformProfDistVoucherCancCnfRequest.class,
            WEB3AdminInformProfDistRegistVoucherMakeHandler.class,
            "registAccountCancCnf");

        //登録口座取消完了
        regHandler(
            WEB3InformAppPlugin.class,
            WEB3AdminInformProfDistVoucherCancCmpRequest.class,
            WEB3AdminInformProfDistRegistVoucherMakeHandler.class,
            "registAccountCancCmp");
        //----------利金・分配金登録伝票作成end----------------->

        //----------管理者口座切替・電子交付申込start----------------->

        // 検索画面表示
        regHandler(
            WEB3InformAppPlugin.class,
            WEB3AdminInformAccSwElecDeliApplySrcRequest.class,
            WEB3AdminInformSwElecDeliApplyHandler.class,
            "searchScreenDisplay");

        // 入力画面表示
        regHandler(
            WEB3InformAppPlugin.class,
            WEB3AdminInformAccSwElecDeliApplyInpRequest.class,
            WEB3AdminInformSwElecDeliApplyHandler.class,
            "displayInputScreen");

        // 申込確認
        regHandler(
            WEB3InformAppPlugin.class,
            WEB3AdminInformAccSwElecDeliApplyConfRequest.class,
            WEB3AdminInformSwElecDeliApplyHandler.class,
            "applyConfirm");

        // 申込完了
        regHandler(
            WEB3InformAppPlugin.class,
            WEB3AdminInformAccSwElecDeliApplyCmpRequest.class,
            WEB3AdminInformSwElecDeliApplyHandler.class,
            "applyComplete");

        //get照会画面
        regHandler(
            WEB3InformAppPlugin.class,
            WEB3AdminInformAccSwElecDeliApplyRefRequest.class,
            WEB3AdminInformSwElecDeliApplyHandler.class,
            "displayReferenceScreen");

        //validate変更
        regHandler(
            WEB3InformAppPlugin.class,
            WEB3AdminInformAccSwElecDeliChangeConfRequest.class,
            WEB3AdminInformSwElecDeliApplyHandler.class,
            "changeConfirm");

        //submit変更
        regHandler(
            WEB3InformAppPlugin.class,
            WEB3AdminInformAccSwElecDeliChangeCmpRequest.class,
            WEB3AdminInformSwElecDeliApplyHandler.class,
            "changeComplete");

        //validate取消
        regHandler(
            WEB3InformAppPlugin.class,
            WEB3AdminInformAccSwElecDeliDeleteConfRequest.class,
            WEB3AdminInformSwElecDeliApplyHandler.class,
            "deleteConfirm");

        //submit取消
        regHandler(
            WEB3InformAppPlugin.class,
            WEB3AdminInformAccSwElecDeliDeleteCmpRequest.class,
            WEB3AdminInformSwElecDeliApplyHandler.class,
            "deleteComplete");

        //----------管理者口座切替・電子交付申込end----------------->

        //----------口座切替・電子交付申込start----------------->
        //入力画面表示
        regHandler(
            WEB3InformAppPlugin.class,
            WEB3InformAccSwElecDeliApplyInpRequest.class,
            WEB3InformAccSwElecDeliApplyHandler.class,
            "displayInputScreen");

        //申込確認
        regHandler(
            WEB3InformAppPlugin.class,
            WEB3InformAccSwElecDeliApplyConfRequest.class,
            WEB3InformAccSwElecDeliApplyHandler.class,
            "applyConfirm");

        //申込完了
        regHandler(
            WEB3InformAppPlugin.class,
            WEB3InformAccSwElecDeliApplyCmpRequest.class,
            WEB3InformAccSwElecDeliApplyHandler.class,
            "applyComplete");

        //----------口座切替・電子交付申込end----------------->

        //----------PTS口座開設申込start----------------->
        //入力画面表示
        regHandler(
            WEB3InformAppPlugin.class,
            WEB3InformPTSAccOpenApplyInpRequest.class,
            WEB3InformPTSAccOpenApplyHandler.class,
            "displayInputScreen");

        //申込確認
        regHandler(
            WEB3InformAppPlugin.class,
            WEB3InformPTSAccOpenApplyCnfRequest.class,
            WEB3InformPTSAccOpenApplyHandler.class,
            "applyConfirm");

        //申込完了
        regHandler(
            WEB3InformAppPlugin.class,
            WEB3InformPTSAccOpenApplyCmpRequest.class,
            WEB3InformPTSAccOpenApplyHandler.class,
            "applyComplete");
        //----------PTS口座開設申込end----------------->

        //管理者・PTS口座開設状況変更
        //検索画面表示
        regHandler(
            WEB3InformAppPlugin.class,
            WEB3AdminInformPTSAccOpenStateChangeSrcRequest.class,
            WEB3AdminInformPTSAccOpenStateChangeHandler.class,
            "displaySearchScrean");

        //入力画面表示
        regHandler(
            WEB3InformAppPlugin.class,
            WEB3AdminInformPTSAccOpenStateChangeInpRequest.class,
            WEB3AdminInformPTSAccOpenStateChangeHandler.class,
            "displayInputScrean");

        //変更確認
        regHandler(
            WEB3InformAppPlugin.class,
            WEB3AdminInformPTSAccOpenStateChangeCnfRequest.class,
            WEB3AdminInformPTSAccOpenStateChangeHandler.class,
            "changeConfirm");

        //変更完了
        regHandler(
            WEB3InformAppPlugin.class,
            WEB3AdminInformPTSAccOpenStateChangeCmpRequest.class,
            WEB3AdminInformPTSAccOpenStateChangeHandler.class,
            "changeComplete");

        //管理者・PTS申込客一覧問合せ
        //検索画面表示
        regHandler(
            WEB3InformAppPlugin.class,
            WEB3AdminInformPTSAccountListInquiryRequest.class,
            WEB3AdminInformPTSAccountListHandler.class,
            "displaySearchScreen");

        //検索結果表示
        regHandler(
            WEB3InformAppPlugin.class,
            WEB3AdminInformPTSAccountListResultRequest.class,
            WEB3AdminInformPTSAccountListHandler.class,
            "displaySearchResultScreen");


        //* MQ-Gateway の Interceptor 設定処理
        Services.addInterceptor(
            WEB3AdminInformProfDistRegistVoucherMakeService.class,
            new WEB3MQGatewayInterceptor());

        //管理者口座切替・電子交付申込サービス
        Services.addInterceptor(
            WEB3AdminInformSwElecDeliApplyService.class,
            new WEB3MQGatewayInterceptor());

        //口座切替・電子交付申込サービス
        Services.addInterceptor(
            WEB3InformAccSwElecDeliApplyService.class,
            new WEB3MQGatewayInterceptor());

        log.exiting(METHOD_NAME);
    }
}
@
