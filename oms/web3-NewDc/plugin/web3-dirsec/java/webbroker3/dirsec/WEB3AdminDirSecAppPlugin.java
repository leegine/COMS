head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.14.05.46.14;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	8884d7d97833a75;
filename	WEB3AdminDirSecAppPlugin.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : Webbroker3-AdminDirsec プラグイン(WEB3AdminDirsecAppPlugin.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2006/03/30 奚翔 (中訊) 新規作成
Revesion History : 2006/07/24 黄建 (中訊) 式樣變更・モデル008-010
Revesion History : 2006/08/11 黄建 (中訊) 式樣變更・モデル013
Revesion History : 2007/01/17  謝旋 (中訊) 仕様変更モデルNo.017-21
Revesion History : 2007/02/17  李木子 (中訊) 実装の問題No.002-004
Revesion History : 2007/06/20 徐宏偉 (中訊) 顧客口座情報登録伝票ステータス更新対応
Revesion History : 2008/05/04 柴双紅 (中訊) 式樣變更・モデル117
Revesion History : 2008/04/30 柴双紅 (中訊) 式樣變更・モデル116
Revesion History : 2008/07/22 楊夫志 (中訊) 式樣變更・モデル132
*/

package webbroker3.dirsec;

import com.fitechlabs.xtrade.kernel.boot.KernelPlugin;
import com.fitechlabs.xtrade.kernel.boot.Plugin;
import com.fitechlabs.xtrade.kernel.boot.Services;
import com.fitechlabs.xtrade.kernel.interceptor.TransactionalInterceptor;

import webbroker3.common.WEB3LogSysTimeInterceptor;
import webbroker3.dirsec.data.WEB3DirsecMasterDatabaseExtensions;
import webbroker3.dirsec.data.WEB3DirsecSessionDatabaseExtensions;
import webbroker3.dirsec.handler.WEB3AdminDirSecAPMngForcedStartHandler;
import webbroker3.dirsec.handler.WEB3AdminDirSecBatoTroubleFlagUpdateHandler;
import webbroker3.dirsec.handler.WEB3AdminDirSecAccRegVoucherStatUpdHandler;
import webbroker3.dirsec.handler.WEB3AdminDirSecAioOrderUnitTableUpdateHandler;
import webbroker3.dirsec.handler.WEB3AdminDirSecDaemonTriggerTableUpdateHandler;
import webbroker3.dirsec.handler.WEB3AdminDirSecHostTableStatusUpdateHandler;
import webbroker3.dirsec.handler.WEB3AdminDirSecTriggerIssueHandler;
import webbroker3.dirsec.handler.WEB3AdminDirSecUploadTableEndDateUpdateHandler;
import webbroker3.dirsec.handler.WEB3AdminFrontOrderRouteChangeHandler;
import webbroker3.dirsec.handler.WEB3AdminFrontSwitchOrderRouteHandler;
import webbroker3.dirsec.handler.WEB3FrontOrderRouteChangeFormSelectHandler;
import webbroker3.dirsec.message.WEB3AdminDirSecAPMngForcedStartCmpRequest;
import webbroker3.dirsec.message.WEB3AdminDirSecAPMngForcedStartCmpResponse;
import webbroker3.dirsec.message.WEB3AdminDirSecAPMngForcedStartCnfRequest;
import webbroker3.dirsec.message.WEB3AdminDirSecAPMngForcedStartCnfResponse;
import webbroker3.dirsec.message.WEB3AdminDirSecAPMngForcedStartInpRequest;
import webbroker3.dirsec.message.WEB3AdminDirSecAPMngForcedStartInpResponse;
import webbroker3.dirsec.message.WEB3AdminDirSecAPMngListRequest;
import webbroker3.dirsec.message.WEB3AdminDirSecAPMngListResponse;
import webbroker3.dirsec.message.WEB3AdminDirSecAccRegVoucherSearchInpRequest;
import webbroker3.dirsec.message.WEB3AdminDirSecAccRegVoucherSearchInpResponse;
import webbroker3.dirsec.message.WEB3AdminDirSecAccRegVoucherSearchResRequest;
import webbroker3.dirsec.message.WEB3AdminDirSecAccRegVoucherSearchResResponse;
import webbroker3.dirsec.message.WEB3AdminDirSecAccRegVoucherStatUpdCompRequest;
import webbroker3.dirsec.message.WEB3AdminDirSecAccRegVoucherStatUpdCompResponse;
import webbroker3.dirsec.message.WEB3AdminDirSecAccRegVoucherStatUpdConfRequest;
import webbroker3.dirsec.message.WEB3AdminDirSecAccRegVoucherStatUpdConfResponse;
import webbroker3.dirsec.message.WEB3AdminDirSecTriggerIssueCompleteRequest;
import webbroker3.dirsec.message.WEB3AdminDirSecTriggerIssueCompleteResponse;
import webbroker3.dirsec.message.WEB3AdminDirSecTriggerIssueConfirmRequest;
import webbroker3.dirsec.message.WEB3AdminDirSecTriggerIssueConfirmResponse;
import webbroker3.dirsec.message.WEB3AdminDirSecTriggerIssueInputRequest;
import webbroker3.dirsec.message.WEB3AdminDirSecTriggerIssueInputResponse;
import webbroker3.dirsec.message.WEB3AdminDirSecTriggerIssueListRequest;
import webbroker3.dirsec.message.WEB3AdminDirSecTriggerIssueListResponse;
import webbroker3.dirsec.message.WEB3AdminDirSecAioOrderUnitTableSearchInputRequest;
import webbroker3.dirsec.message.WEB3AdminDirSecAioOrderUnitTableSearchResultRequest;
import webbroker3.dirsec.message.WEB3AdminDirSecAioOrderUnitTableUpdateCompleteRequest;
import webbroker3.dirsec.message.WEB3AdminDirSecAioOrderUnitTableUpdateConfirmRequest;
import webbroker3.dirsec.message.WEB3AdminDirSecDaemonTriggerTableSearchInputRequest;
import webbroker3.dirsec.message.WEB3AdminDirSecDaemonTriggerTableSearchResultRequest;
import webbroker3.dirsec.message.WEB3AdminDirSecDaemonTriggerTableUpdateCompleteRequest;
import webbroker3.dirsec.message.WEB3AdminDirSecDaemonTriggerTableUpdateConfirmRequest;
import webbroker3.dirsec.message.WEB3AdminDirSecHostTableReferenceRequest;
import webbroker3.dirsec.message.WEB3AdminDirSecHostTableReferenceResponse;
import webbroker3.dirsec.message.WEB3AdminDirSecHostTableSearchInputRequest;
import webbroker3.dirsec.message.WEB3AdminDirSecHostTableSearchInputResponse;
import webbroker3.dirsec.message.WEB3AdminDirSecHostTableSearchListRequest;
import webbroker3.dirsec.message.WEB3AdminDirSecHostTableSearchListResponse;
import webbroker3.dirsec.message.WEB3AdminDirSecHostTableStatusCompleteRequest;
import webbroker3.dirsec.message.WEB3AdminDirSecHostTableStatusCompleteResponse;
import webbroker3.dirsec.message.WEB3AdminDirSecHostTableStatusConfirmRequest;
import webbroker3.dirsec.message.WEB3AdminDirSecHostTableStatusConfirmResponse;
import webbroker3.dirsec.message.WEB3AdminDirSecUploadTableListRequest;
import webbroker3.dirsec.message.WEB3AdminDirSecUploadTableListResponse;
import webbroker3.dirsec.message.WEB3AdminDirSecUploadTableUpdateCompleteRequest;
import webbroker3.dirsec.message.WEB3AdminDirSecUploadTableUpdateCompleteResponse;
import webbroker3.dirsec.message.WEB3AdminDirSecUploadTableUpdateConfirmRequest;
import webbroker3.dirsec.message.WEB3AdminDirSecUploadTableUpdateConfirmResponse;
import webbroker3.dirsec.message.WEB3AdminDirSecWorkingCompleteRequest;
import webbroker3.dirsec.message.WEB3AdminDirSecWorkingCompleteResponse;
import webbroker3.dirsec.message.WEB3AdminDirSecWorkingConfirmRequest;
import webbroker3.dirsec.message.WEB3AdminDirSecWorkingConfirmResponse;
import webbroker3.dirsec.message.WEB3AdminDirSecWorkingListRequest;
import webbroker3.dirsec.message.WEB3AdminDirSecWorkingListResponse;
import webbroker3.dirsec.message.WEB3AdminFrontChangeProcessSelectRequest;
import webbroker3.dirsec.message.WEB3AdminFrontChangeProcessSelectResponse;
import webbroker3.dirsec.message.WEB3AdminFrontRouteChangeCompleteRequest;
import webbroker3.dirsec.message.WEB3AdminFrontRouteChangeCompleteResponse;
import webbroker3.dirsec.message.WEB3AdminFrontRouteChangeConfirmRequest;
import webbroker3.dirsec.message.WEB3AdminFrontRouteChangeConfirmResponse;
import webbroker3.dirsec.message.WEB3AdminFrontRouteChangeSelectRequest;
import webbroker3.dirsec.message.WEB3AdminFrontRouteChangeSelectResponse;
import webbroker3.dirsec.message.WEB3AdminSwitchOrderRouteCompleteRequest;
import webbroker3.dirsec.message.WEB3AdminSwitchOrderRouteCompleteResponse;
import webbroker3.dirsec.message.WEB3AdminSwitchOrderRouteConfirmRequest;
import webbroker3.dirsec.message.WEB3AdminSwitchOrderRouteConfirmResponse;
import webbroker3.dirsec.message.WEB3AdminSwitchOrderRouteSelectRequest;
import webbroker3.dirsec.message.WEB3AdminSwitchOrderRouteSelectResponse;
import webbroker3.dirsec.service.delegate.WEB3AdminDirSecAPMngForcedStartService;
import webbroker3.dirsec.service.delegate.WEB3AdminDirSecAccRegVoucherStatUpdService;
import webbroker3.dirsec.service.delegate.WEB3AdminDirSecAioOrderUnitTableUpdateService;
import webbroker3.dirsec.service.delegate.WEB3AdminDirSecBatoTroubleFlagUpdateService;
import webbroker3.dirsec.service.delegate.WEB3AdminDirSecDaemonTriggerTableUpdateService;
import webbroker3.dirsec.service.delegate.WEB3AdminDirSecHostTableStatusUpdateService;
import webbroker3.dirsec.service.delegate.WEB3AdminDirSecTriggerIssueService;
import webbroker3.dirsec.service.delegate.WEB3AdminDirSecUploadTableEndDateUpdateService;
import webbroker3.dirsec.service.delegate.WEB3AdminDirSecFrontOrderCommonService;
import webbroker3.dirsec.service.delegate.WEB3AdminFrontOrderRouteChangeService;
import webbroker3.dirsec.service.delegate.WEB3AdminSwitchOrderRouteService;
import webbroker3.dirsec.service.delegate.WEB3FrontOrderRouteChangeFormSelectService;
import webbroker3.dirsec.service.delegate.stdimpls.WEB3AdminDirSecAPMngForcedStartServiceImpl;
import webbroker3.dirsec.service.delegate.stdimpls.WEB3AdminDirSecAccRegVoucherStatUpdServiceImpl;
import webbroker3.dirsec.service.delegate.stdimpls.WEB3AdminDirSecAioOrderUnitTableUpdateServiceImpl;
import webbroker3.dirsec.service.delegate.stdimpls.WEB3AdminDirSecBatoTroubleFlagUpdateServiceImpl;
import webbroker3.dirsec.service.delegate.stdimpls.WEB3AdminDirSecDaemonTriggerTableUpdateServiceImpl;
import webbroker3.dirsec.service.delegate.stdimpls.WEB3AdminDirSecHostTableStatusUpdateServiceImpl;
import webbroker3.dirsec.service.delegate.stdimpls.WEB3AdminDirSecTriggerIssueServiceImpl;
import webbroker3.dirsec.service.delegate.stdimpls.WEB3AdminDirSecUploadTableEndDateUpdateServiceImpl;
import webbroker3.dirsec.service.delegate.stdimpls.WEB3AdminDirSecFrontOrderCommonServiceImpl;
import webbroker3.dirsec.service.delegate.stdimpls.WEB3AdminFrontOrderRouteChangeServiceImpl;
import webbroker3.dirsec.service.delegate.stdimpls.WEB3AdminSwitchOrderRouteServiceImpl;
import webbroker3.dirsec.service.delegate.stdimpls.WEB3FrontOrderRouteChangeFormSelectServiceImpl;
import webbroker3.mqgateway.WEB3MQGatewayInterceptor;
import webbroker3.util.WEB3LogUtility;

/**
 * Webbroker3-AdminDirsec プラグインクラス。
 *                                                                
 * @@author 奚翔
 * @@version 1.0
 */
public class WEB3AdminDirSecAppPlugin extends Plugin
{
    /**
     * ログ出力ユーティリティ。
     */
    private static WEB3LogUtility log = 
        WEB3LogUtility.getInstance(WEB3AdminDirSecAppPlugin.class);

    /**
     * コンストラクタ。
     */
    public WEB3AdminDirSecAppPlugin()
    {

    }

    /**
     * プラグインエントリーポイント。
     */
    public static void plug() throws Exception
    {
        String STR_METHOD_NAME = " plug()";
        log.entering(STR_METHOD_NAME);

        plug(WEB3AdminDirSecAppPlugin.class);

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

        //DatabaseExtensions のプラグイン処理 ----------------------
        WEB3DirsecSessionDatabaseExtensions.plug();
        WEB3DirsecMasterDatabaseExtensions.plug();

        // Service の登録処理 ----------------------

        //管理者キューテーブルステータス更新サービス
        Services.registerService(WEB3AdminDirSecHostTableStatusUpdateService.class, 
            new WEB3AdminDirSecHostTableStatusUpdateServiceImpl());

        //管理者デーモントリガーテーブルステータス更新サービス
        Services.registerService(WEB3AdminDirSecDaemonTriggerTableUpdateService.class, 
            new WEB3AdminDirSecDaemonTriggerTableUpdateServiceImpl());

        // 管理者注文単位テーブル注文状態更新サービス
        Services.registerService(WEB3AdminDirSecAioOrderUnitTableUpdateService.class, 
            new WEB3AdminDirSecAioOrderUnitTableUpdateServiceImpl());

        //管理者アップロードテーブル終了日時更新サービス
        Services.registerService(WEB3AdminDirSecUploadTableEndDateUpdateService.class, 
            new WEB3AdminDirSecUploadTableEndDateUpdateServiceImpl());

        // 管理者発注経路切替処理方式選択サービス
        Services.registerService(WEB3FrontOrderRouteChangeFormSelectService.class,
            new WEB3FrontOrderRouteChangeFormSelectServiceImpl());

        // 管理者発注経路切替サービス
        Services.registerService(WEB3AdminFrontOrderRouteChangeService.class,
            new WEB3AdminFrontOrderRouteChangeServiceImpl());

        // 管理者発注先切替サービス
        Services.registerService(WEB3AdminSwitchOrderRouteService.class,
            new WEB3AdminSwitchOrderRouteServiceImpl());

        // 管理者フロント発注管理共通サービス
        Services.registerService(WEB3AdminDirSecFrontOrderCommonService.class,
            new WEB3AdminDirSecFrontOrderCommonServiceImpl());

        //管理者顧客情報登録伝票ステータス更新サービス
        Services.registerService(WEB3AdminDirSecAccRegVoucherStatUpdService.class,
            new WEB3AdminDirSecAccRegVoucherStatUpdServiceImpl());

        //管理者電子鳩障害フラグ更新サービス
        Services.registerService(WEB3AdminDirSecBatoTroubleFlagUpdateService.class,
            new WEB3AdminDirSecBatoTroubleFlagUpdateServiceImpl());

        //トリガー発行処理サービス
        Services.registerService(WEB3AdminDirSecTriggerIssueService.class,
            new WEB3AdminDirSecTriggerIssueServiceImpl());
        
        //管理者下り処理強制起動サービス
        Services.registerService(WEB3AdminDirSecAPMngForcedStartService.class,
            new WEB3AdminDirSecAPMngForcedStartServiceImpl());

        // Service の Interceptor 設定処理 ----------------------
        // 自動トランザクション設定 

        //管理者キューテーブルステータス更新サービス
        Services.addInterceptor(
            WEB3AdminDirSecHostTableStatusUpdateService.class, 
            new TransactionalInterceptor(TransactionalInterceptor.TX_JOIN_EXISTING));

        //管理者デーモントリガーテーブルステータス更新サービス
        Services.addInterceptor(WEB3AdminDirSecDaemonTriggerTableUpdateService.class, 
            new TransactionalInterceptor(TransactionalInterceptor.TX_JOIN_EXISTING));

        // 管理者注文単位テーブル注文状態更新サービス
        Services.addInterceptor(WEB3AdminDirSecAioOrderUnitTableUpdateService.class, 
            new TransactionalInterceptor(TransactionalInterceptor.TX_JOIN_EXISTING));

        //管理者アップロードテーブル終了日時更新サービス
        Services.addInterceptor(WEB3AdminDirSecUploadTableEndDateUpdateService.class, 
            new TransactionalInterceptor(TransactionalInterceptor.TX_JOIN_EXISTING));

        // 管理者発注経路切替処理方式選択サービス
        Services.addInterceptor(WEB3FrontOrderRouteChangeFormSelectService.class,
            new TransactionalInterceptor(TransactionalInterceptor.TX_CREATE_NEW));

        // 管理者発注経路切替サービス
        Services.addInterceptor(WEB3AdminFrontOrderRouteChangeService.class,
            new TransactionalInterceptor(TransactionalInterceptor.TX_CREATE_NEW));
            
        // 管理者発注先切替サービス
        Services.addInterceptor(WEB3AdminSwitchOrderRouteService.class,
            new TransactionalInterceptor(TransactionalInterceptor.TX_CREATE_NEW));
            
        // 管理者フロント発注管理共通サービス
        Services.addInterceptor(WEB3AdminDirSecFrontOrderCommonService.class,
            new TransactionalInterceptor(TransactionalInterceptor.TX_CREATE_NEW));  

        //管理者顧客情報登録伝票ステータス更新サービス
        Services.addInterceptor(WEB3AdminDirSecAccRegVoucherStatUpdService.class,
            new TransactionalInterceptor(TransactionalInterceptor.TX_CREATE_NEW));

        //管理者電子鳩障害フラグ更新サービス
        Services.addInterceptor(WEB3AdminDirSecBatoTroubleFlagUpdateService.class,
            new TransactionalInterceptor(TransactionalInterceptor.TX_JOIN_EXISTING));

        //管理者下り処理強制起動サービス
        Services.addInterceptor(WEB3AdminDirSecAPMngForcedStartService.class,
            new TransactionalInterceptor(TransactionalInterceptor.TX_JOIN_EXISTING));

        // MQGatewayインタセプタの設定
        //トリガー発行処理サービス
        Services.addInterceptor(
            WEB3AdminDirSecTriggerIssueService.class,
            new WEB3MQGatewayInterceptor());

        // 管理者発注経路切替サービス
        Services.addInterceptor(
            WEB3AdminFrontOrderRouteChangeService.class,
            new WEB3MQGatewayInterceptor());

        // Service.execute()呼び出し前後に  ----------------------
        // 処理開始時刻と処理終了時刻をログ出力するように設定する

        //管理者キューテーブルステータス更新サービス
        Services.addInterceptor(
            WEB3AdminDirSecHostTableStatusUpdateService.class, 
            new WEB3LogSysTimeInterceptor());

        //管理者デーモントリガーテーブルステータス更新サービス
        Services.addInterceptor(
            WEB3AdminDirSecDaemonTriggerTableUpdateService.class, 
            new WEB3LogSysTimeInterceptor());

        // 管理者注文単位テーブル注文状態更新サービス
        Services.addInterceptor(
            WEB3AdminDirSecAioOrderUnitTableUpdateService.class, 
            new WEB3LogSysTimeInterceptor());

        //管理者アップロードテーブル終了日時更新サービス
        Services.addInterceptor(
            WEB3AdminDirSecUploadTableEndDateUpdateService.class, 
            new WEB3LogSysTimeInterceptor());

        // 管理者発注経路切替処理方式選択サービス
        Services.addInterceptor(WEB3FrontOrderRouteChangeFormSelectService.class,
            new WEB3LogSysTimeInterceptor());

        // 管理者発注経路切替サービス
        Services.addInterceptor(WEB3AdminFrontOrderRouteChangeService.class,
            new WEB3LogSysTimeInterceptor());
            
        // 管理者フロント発注管理共通サービス
        Services.addInterceptor(WEB3AdminDirSecFrontOrderCommonService.class,
            new WEB3LogSysTimeInterceptor());

        // 管理者発注先切替サービス
        Services.addInterceptor(WEB3AdminSwitchOrderRouteService.class,
            new WEB3AdminDirSecFrontServiceInterceptor());

        //管理者顧客情報登録伝票ステータス更新サービス
        Services.addInterceptor(WEB3AdminDirSecAccRegVoucherStatUpdService.class,
            new WEB3LogSysTimeInterceptor());

        //管理者電子鳩障害フラグ更新サービス
        Services.addInterceptor(WEB3AdminDirSecBatoTroubleFlagUpdateService.class,
            new WEB3LogSysTimeInterceptor());

        //トリガー発行処理サービス
        Services.addInterceptor(WEB3AdminDirSecTriggerIssueService.class,
            new WEB3LogSysTimeInterceptor());

        //管理者下り処理強制起動サービス
        Services.addInterceptor(WEB3AdminDirSecAPMngForcedStartService.class,
            new WEB3LogSysTimeInterceptor());

        // Message の登録処理 ----------------------

        //管理者・キューテーブル一覧リクエスト
        regClass(WEB3AdminDirSecHostTableReferenceRequest.class);

        //管理者・キューテーブル一覧レスポンス 
        regClass(WEB3AdminDirSecHostTableReferenceResponse.class);

        //管理者・キューテーブル検索入力リクエスト
        regClass(WEB3AdminDirSecHostTableSearchInputRequest.class);

        //管理者・キューテーブル検索入力レスポンス
        regClass(WEB3AdminDirSecHostTableSearchInputResponse.class);

        //管理者・キューテーブル検索結果リクエスト
        regClass(WEB3AdminDirSecHostTableSearchListRequest.class);

        //管理者・キューテーブル検索結果レスポンス
        regClass(WEB3AdminDirSecHostTableSearchListResponse.class);

        //管理者・キューテーブルステータス更新確認リクエスト 
        regClass(WEB3AdminDirSecHostTableStatusConfirmRequest.class);

        //管理者・キューテーブルステータス更新確認レスポンス
        regClass(WEB3AdminDirSecHostTableStatusConfirmResponse.class);

        //管理者・キューテーブルステータス更新完了リクエスト
        regClass(WEB3AdminDirSecHostTableStatusCompleteRequest.class);

        //管理者・キューテーブルステータス更新完了レスポンス
        regClass(WEB3AdminDirSecHostTableStatusCompleteResponse.class);

        //管理者・デーモントリガーテーブル検索入力リクエスト
        regClass(WEB3AdminDirSecDaemonTriggerTableSearchInputRequest.class);

        //管理者・デーモントリガーテーブル検索入力レスポンス
        regClass(WEB3AdminDirSecDaemonTriggerTableSearchInputRequest.class);

        //管理者・デーモントリガーテーブル検索結果リクエスト
        regClass(WEB3AdminDirSecDaemonTriggerTableSearchResultRequest.class);

        //管理者・デーモントリガーテーブル検索結果レスポンス
        regClass(WEB3AdminDirSecDaemonTriggerTableSearchResultRequest.class);

        //管理者・デーモントリガーテーブルステータス更新確認リクエスト
        regClass(WEB3AdminDirSecDaemonTriggerTableUpdateConfirmRequest.class);

        //管理者・デーモントリガーテーブルステータス更新確認レスポンス
        regClass(WEB3AdminDirSecDaemonTriggerTableUpdateConfirmRequest.class);

        //管理者・デーモントリガーテーブルステータス更新完了リクエスト
        regClass(WEB3AdminDirSecDaemonTriggerTableUpdateCompleteRequest.class);

        //管理者・デーモントリガーテーブルステータス更新完了レスポンス
        regClass(WEB3AdminDirSecDaemonTriggerTableUpdateCompleteRequest.class);

        //注文単位テーブル検索入力リクエスト
        regClass(WEB3AdminDirSecAioOrderUnitTableSearchInputRequest.class);

        //注文単位テーブル検索入力レスポンス
        regClass(WEB3AdminDirSecAioOrderUnitTableSearchInputRequest.class);

        //注文単位テーブル検索結果リクエスト
        regClass(WEB3AdminDirSecAioOrderUnitTableSearchResultRequest.class);

        //注文単位テーブル検索結果レスポンス
        regClass(WEB3AdminDirSecAioOrderUnitTableSearchResultRequest.class);

        //注文単位テーブル注文状態更新確認リクエスト
        regClass(WEB3AdminDirSecAioOrderUnitTableUpdateConfirmRequest.class);

        //注文単位テーブル注文状態更新確認レスポンス
        regClass(WEB3AdminDirSecAioOrderUnitTableUpdateConfirmRequest.class);

        //注文単位テーブル注文状態更新完了リクエスト
        regClass(WEB3AdminDirSecAioOrderUnitTableUpdateCompleteRequest.class);

        //注文単位テーブル注文状態更新完了レスポンス
        regClass(WEB3AdminDirSecAioOrderUnitTableUpdateCompleteRequest.class);

        //アップロードテーブルレコード一覧リクエスト 
        regClass(WEB3AdminDirSecUploadTableListRequest.class);

        //アップロードテーブルレコード一覧レスポンス 
        regClass(WEB3AdminDirSecUploadTableListResponse.class);

        //アップロードテーブル終了日時更新確認リクエスト 
        regClass(WEB3AdminDirSecUploadTableUpdateConfirmRequest.class);

        //アップロードテーブル終了日時更新確認レスポンス 
        regClass(WEB3AdminDirSecUploadTableUpdateConfirmResponse.class);

        //アップロードテーブル終了日時更新完了リクエスト 
        regClass(WEB3AdminDirSecUploadTableUpdateCompleteRequest.class);

        //アップロードテーブル終了日時更新完了レスポンス 
        regClass(WEB3AdminDirSecUploadTableUpdateCompleteResponse.class);
        
        //管理者・トリガー発行処理完了リクエスト 　@
        regClass(WEB3AdminDirSecTriggerIssueCompleteRequest.class);

        //管理者・トリガー発行処理完了レスポンス 
        regClass(WEB3AdminDirSecTriggerIssueCompleteResponse.class);

        //管理者・トリガー発行処理確認リクエスト 
        regClass(WEB3AdminDirSecTriggerIssueConfirmRequest.class);

         //管理者・トリガー発行処理確認レスポンス 　@
        regClass(WEB3AdminDirSecTriggerIssueConfirmResponse.class);

        //管理者・トリガー発行処理入力リクエスト 　@
        regClass(WEB3AdminDirSecTriggerIssueInputRequest.class);

        //管理者・トリガー発行処理入力レスポンス 　@
        regClass(WEB3AdminDirSecTriggerIssueInputResponse.class);

        //管理者・トリガー発行処理一覧リクエスト 
         regClass(WEB3AdminDirSecTriggerIssueListRequest.class);

        //管理者・トリガー発行処理一覧レスポンス 　@
        regClass(WEB3AdminDirSecTriggerIssueListResponse.class);

        // 管理者・発注経路切替処理選択リクエスト
        regClass(WEB3AdminFrontChangeProcessSelectRequest.class);
        regClass(WEB3AdminFrontChangeProcessSelectResponse.class);

        // 管理者・発注経路切替選択リクエスト・レスポンス
        regClass(WEB3AdminFrontRouteChangeSelectRequest.class);
        regClass(WEB3AdminFrontRouteChangeSelectResponse.class);

        // 管理者・発注経路切替確認リクエスト・レスポンス
        regClass(WEB3AdminFrontRouteChangeConfirmRequest.class);
        regClass(WEB3AdminFrontRouteChangeConfirmResponse.class);

        // 管理者・発注経路切替完了リクエスト・レスポンス
        regClass(WEB3AdminFrontRouteChangeCompleteRequest.class);
        regClass(WEB3AdminFrontRouteChangeCompleteResponse.class);

        // 管理者・発注先切替選択リクエスト・レスポンス
        regClass(WEB3AdminSwitchOrderRouteSelectRequest.class);
        regClass(WEB3AdminSwitchOrderRouteSelectResponse.class);

        // 管理者・発注先切替確認リクエスト・レスポンス
        regClass(WEB3AdminSwitchOrderRouteConfirmRequest.class);
        regClass(WEB3AdminSwitchOrderRouteConfirmResponse.class);

        // 管理者・発注先切替完了リクエスト・レスポンス
        regClass(WEB3AdminSwitchOrderRouteCompleteRequest.class);
        regClass(WEB3AdminSwitchOrderRouteCompleteResponse.class);

        //管理者・顧客情報登録伝票検索入力リクエスト・レスポンス
        regClass(WEB3AdminDirSecAccRegVoucherSearchInpRequest.class);
        regClass(WEB3AdminDirSecAccRegVoucherSearchInpResponse.class);

        //管理者・顧客情報登録伝票検索結果リクエスト・レスポンス
        regClass(WEB3AdminDirSecAccRegVoucherSearchResRequest.class);
        regClass(WEB3AdminDirSecAccRegVoucherSearchResResponse.class);

        //管理者・顧客情報登録伝票ステータス更新完了リクエスト・レスポンス
        regClass(WEB3AdminDirSecAccRegVoucherStatUpdCompRequest.class);
        regClass(WEB3AdminDirSecAccRegVoucherStatUpdCompResponse.class);

        //管理者・顧客情報登録伝票ステータス更新確認リクエスト・レスポンス
        regClass(WEB3AdminDirSecAccRegVoucherStatUpdConfRequest.class);
        regClass(WEB3AdminDirSecAccRegVoucherStatUpdConfResponse.class);

        //管理者・稼動状況一覧リクエスト
        regClass(WEB3AdminDirSecWorkingListRequest.class);
        //管理者・稼動状況一覧レスポンス
        regClass(WEB3AdminDirSecWorkingListResponse.class);

        //管理者・稼動状況変更確認リクエスト
        regClass(WEB3AdminDirSecWorkingConfirmRequest.class);
        //管理者・稼動状況変更確認レスポンス
        regClass(WEB3AdminDirSecWorkingConfirmResponse.class);

        //管理者・稼動状況変更完了リクエスト
        regClass(WEB3AdminDirSecWorkingCompleteRequest.class);
        //管理者・稼動状況変更完了レスポンス
        regClass(WEB3AdminDirSecWorkingCompleteResponse.class);

        //管理者・下り処理強制起動完了リクエスト
        regClass(WEB3AdminDirSecAPMngForcedStartCmpRequest.class);
        //管理者・下り処理強制起動完了レスポンス
        regClass(WEB3AdminDirSecAPMngForcedStartCmpResponse.class);
        
        //管理者・下り処理強制起動確認リクエスト
        regClass(WEB3AdminDirSecAPMngForcedStartCnfRequest.class);
        //管理者・下り処理強制起動確認レスポンス
        regClass(WEB3AdminDirSecAPMngForcedStartCnfResponse.class);

        //管理者・下り処理強制起動入力リクエスト
        regClass(WEB3AdminDirSecAPMngForcedStartInpRequest.class);
        //管理者・下り処理強制起動入力レスポンス
        regClass(WEB3AdminDirSecAPMngForcedStartInpResponse.class);

        //管理者・下り処理一覧リクエスト
        regClass(WEB3AdminDirSecAPMngListRequest.class);
        //管理者・下り処理一覧レスポンス
        regClass(WEB3AdminDirSecAPMngListResponse.class);

        //
        //Handler の登録処理 ----------------------

        //管理者キューテーブルステータス更新ハンドラの登録
        regHandler(
            WEB3AdminDirSecAppPlugin.class,    
            WEB3AdminDirSecHostTableReferenceRequest.class, 
            WEB3AdminDirSecHostTableStatusUpdateHandler.class, 
            "getHostTableList");

        //管理者キューテーブルステータス更新ハンドラの登録        
        regHandler(
            WEB3AdminDirSecAppPlugin.class,
            WEB3AdminDirSecHostTableSearchInputRequest.class, 
            WEB3AdminDirSecHostTableStatusUpdateHandler.class, 
            "getQueryConditionInputScreen");

        //管理者キューテーブルステータス更新ハンドラの登録        
        regHandler(
            WEB3AdminDirSecAppPlugin.class,
            WEB3AdminDirSecHostTableSearchListRequest.class, 
            WEB3AdminDirSecHostTableStatusUpdateHandler.class, 
            "getQueryResultList");

        //管理者キューテーブルステータス更新ハンドラの登録        
        regHandler(
            WEB3AdminDirSecAppPlugin.class,
            WEB3AdminDirSecHostTableStatusConfirmRequest.class, 
            WEB3AdminDirSecHostTableStatusUpdateHandler.class, 
            "getUpdateConfirmScreen");

        //管理者キューテーブルステータス更新ハンドラの登録        
        regHandler(
            WEB3AdminDirSecAppPlugin.class,
            WEB3AdminDirSecHostTableStatusCompleteRequest.class, 
            WEB3AdminDirSecHostTableStatusUpdateHandler.class, 
            "getUpdateCompleteScreen");

        //管理者デーモントリガーテーブルステータス更新ハンドラ
        regHandler(
            WEB3AdminDirSecAppPlugin.class,
            WEB3AdminDirSecDaemonTriggerTableSearchInputRequest.class, 
            WEB3AdminDirSecDaemonTriggerTableUpdateHandler.class, 
            "searchConditionInput");

        regHandler(
            WEB3AdminDirSecAppPlugin.class,
            WEB3AdminDirSecDaemonTriggerTableSearchResultRequest.class, 
            WEB3AdminDirSecDaemonTriggerTableUpdateHandler.class, 
            "searchResult");

        regHandler(
            WEB3AdminDirSecAppPlugin.class,
            WEB3AdminDirSecDaemonTriggerTableUpdateConfirmRequest.class, 
            WEB3AdminDirSecDaemonTriggerTableUpdateHandler.class, 
            "updateConfirm");
        
        regHandler(
            WEB3AdminDirSecAppPlugin.class,
            WEB3AdminDirSecDaemonTriggerTableUpdateCompleteRequest.class, 
            WEB3AdminDirSecDaemonTriggerTableUpdateHandler.class, 
            "updateComplete");
        
        //管理者注文単位テーブル注文状態更新ハンドラ
        regHandler(
            WEB3AdminDirSecAppPlugin.class,
            WEB3AdminDirSecAioOrderUnitTableSearchInputRequest.class, 
            WEB3AdminDirSecAioOrderUnitTableUpdateHandler.class, 
            "getSearchScreen");
        
        regHandler(
            WEB3AdminDirSecAppPlugin.class,
            WEB3AdminDirSecAioOrderUnitTableSearchResultRequest.class, 
            WEB3AdminDirSecAioOrderUnitTableUpdateHandler.class, 
            "getSearchResultScreen");
        
        regHandler(
            WEB3AdminDirSecAppPlugin.class,
            WEB3AdminDirSecAioOrderUnitTableUpdateConfirmRequest.class, 
            WEB3AdminDirSecAioOrderUnitTableUpdateHandler.class, 
            "getUpdateConfirmScreen");
        
        regHandler(
            WEB3AdminDirSecAppPlugin.class,
            WEB3AdminDirSecAioOrderUnitTableUpdateCompleteRequest.class, 
            WEB3AdminDirSecAioOrderUnitTableUpdateHandler.class, 
            "getUpdateCompleteScreen");
        
        //管理者アップロードテーブル終了日時更新ハンドラ
        regHandler(
            WEB3AdminDirSecAppPlugin.class,
            WEB3AdminDirSecUploadTableListRequest.class, 
            WEB3AdminDirSecUploadTableEndDateUpdateHandler.class, 
            "getListScreen");
        
        regHandler(
            WEB3AdminDirSecAppPlugin.class,
            WEB3AdminDirSecUploadTableUpdateConfirmRequest.class, 
            WEB3AdminDirSecUploadTableEndDateUpdateHandler.class, 
            "getUpdateConfirmScreen");
        
        regHandler(
            WEB3AdminDirSecAppPlugin.class,
            WEB3AdminDirSecUploadTableUpdateCompleteRequest.class, 
            WEB3AdminDirSecUploadTableEndDateUpdateHandler.class, 
            "getUpdateCompleteScreen");

        // 管理者発注経路切替方式選択ハンドラ
        regHandler(
            WEB3AdminDirSecAppPlugin.class,
            WEB3AdminFrontChangeProcessSelectRequest.class,
            WEB3FrontOrderRouteChangeFormSelectHandler.class,
            "getSelectScreen");
            
        // 管理者発注経路切替ハンドラ
        regHandler(
            WEB3AdminDirSecAppPlugin.class,
            WEB3AdminFrontRouteChangeSelectRequest.class,
            WEB3AdminFrontOrderRouteChangeHandler.class,
            "getSelectScreen");
            
        regHandler(
            WEB3AdminDirSecAppPlugin.class,
            WEB3AdminFrontRouteChangeConfirmRequest.class,
            WEB3AdminFrontOrderRouteChangeHandler.class,
            "validateChange");

        regHandler(
            WEB3AdminDirSecAppPlugin.class,
            WEB3AdminFrontRouteChangeCompleteRequest.class,
            WEB3AdminFrontOrderRouteChangeHandler.class,
            "submitChange");

        // 管理者発注先切替ハンドラ
        regHandler(
            WEB3AdminDirSecAppPlugin.class,
            WEB3AdminSwitchOrderRouteSelectRequest.class,
            WEB3AdminFrontSwitchOrderRouteHandler.class,
            "getSelectScreen");
            
        regHandler(
            WEB3AdminDirSecAppPlugin.class,
            WEB3AdminSwitchOrderRouteConfirmRequest.class,
            WEB3AdminFrontSwitchOrderRouteHandler.class,
            "validateOrderRouteChange");

        regHandler(
            WEB3AdminDirSecAppPlugin.class,
            WEB3AdminSwitchOrderRouteCompleteRequest.class,
            WEB3AdminFrontSwitchOrderRouteHandler.class,
            "submitOrderRouteChange");

        //管理者顧客情報登録伝票ステータス更新ハンドラ
        regHandler(
            WEB3AdminDirSecAppPlugin.class,
            WEB3AdminDirSecAccRegVoucherSearchInpRequest.class,
            WEB3AdminDirSecAccRegVoucherStatUpdHandler.class,
            "getSearchScreen");

        regHandler(
            WEB3AdminDirSecAppPlugin.class,
            WEB3AdminDirSecAccRegVoucherSearchResRequest.class,
            WEB3AdminDirSecAccRegVoucherStatUpdHandler.class,
            "getSearchResultScreen");

        regHandler(
            WEB3AdminDirSecAppPlugin.class,
            WEB3AdminDirSecAccRegVoucherStatUpdConfRequest.class,
            WEB3AdminDirSecAccRegVoucherStatUpdHandler.class,
            "getUpdateConfirmScreen");

        regHandler(
            WEB3AdminDirSecAppPlugin.class,
            WEB3AdminDirSecAccRegVoucherStatUpdCompRequest.class,
            WEB3AdminDirSecAccRegVoucherStatUpdHandler.class,
            "getUpdateCompleteScreen");

        regHandler(
            WEB3AdminDirSecAppPlugin.class,    
            WEB3AdminDirSecWorkingListRequest.class, 
            WEB3AdminDirSecBatoTroubleFlagUpdateHandler.class, 
            "getListScreen");

        regHandler(
            WEB3AdminDirSecAppPlugin.class,    
            WEB3AdminDirSecWorkingConfirmRequest.class, 
            WEB3AdminDirSecBatoTroubleFlagUpdateHandler.class, 
            "validateChangeConfirmScreen");

        regHandler(
            WEB3AdminDirSecAppPlugin.class,    
            WEB3AdminDirSecWorkingCompleteRequest.class, 
            WEB3AdminDirSecBatoTroubleFlagUpdateHandler.class, 
            "submitChangeCompleteScreen");

        regHandler(
            WEB3AdminDirSecAppPlugin.class,
            WEB3AdminDirSecTriggerIssueListRequest.class,
            WEB3AdminDirSecTriggerIssueHandler.class,
            "getTriggerIssueListScreenDisplay");

        regHandler(
            WEB3AdminDirSecAppPlugin.class,    
            WEB3AdminDirSecTriggerIssueInputRequest.class,
            WEB3AdminDirSecTriggerIssueHandler.class, 
            "getTriggerIssueInputScreenDisplay");

        regHandler(
            WEB3AdminDirSecAppPlugin.class,    
            WEB3AdminDirSecTriggerIssueConfirmRequest.class, 
            WEB3AdminDirSecTriggerIssueHandler.class, 
            "validateTriggerIssueConfirmScreenDisplay");

        regHandler(
            WEB3AdminDirSecAppPlugin.class,    
            WEB3AdminDirSecTriggerIssueCompleteRequest.class, 
            WEB3AdminDirSecTriggerIssueHandler.class, 
            "submitTriggerIssueCompleteScreenDisplay");

        //管理者下り処理強制起動ハンドラ
        regHandler(
                WEB3AdminDirSecAppPlugin.class,    
                WEB3AdminDirSecAPMngListRequest.class, 
                WEB3AdminDirSecAPMngForcedStartHandler.class, 
                "getAPMngList");

        regHandler(
                WEB3AdminDirSecAppPlugin.class,    
                WEB3AdminDirSecAPMngForcedStartInpRequest.class, 
                WEB3AdminDirSecAPMngForcedStartHandler.class, 
                "getAPMngForcedStartInp");

        regHandler(
                WEB3AdminDirSecAppPlugin.class,    
                WEB3AdminDirSecAPMngForcedStartCnfRequest.class, 
                WEB3AdminDirSecAPMngForcedStartHandler.class, 
                "validateAPMngForcedStartCnf");

        regHandler(
                WEB3AdminDirSecAppPlugin.class,    
                WEB3AdminDirSecAPMngForcedStartCmpRequest.class, 
                WEB3AdminDirSecAPMngForcedStartHandler.class, 
                "submitAPMngForcedStartCmp");

        log.exiting(STR_METHOD_NAME);
    }
}
@
