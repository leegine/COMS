head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.14.06.37.47;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	7984d7db5d91d86;
filename	WEB3SrvRegiAppPlugin.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : Webbroker3-サービス利用 プラグイン(WEB3SrvRegiAppPlugin.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2004/10/22 張威 (中訊) 新規作成
Revesion History : 2008/02/18 武波 (中訊) モデル310
Revesion History : 2008/03/13 武波 (中訊) モデル335,336,337,338
Revesion History : 2008/05/22 馮海濤 (中訊) モデル371
*/

package webbroker3.srvregi;

import com.fitechlabs.xtrade.kernel.boot.KernelPlugin;
import com.fitechlabs.xtrade.kernel.boot.Plugin;
import com.fitechlabs.xtrade.kernel.boot.Services;
import com.fitechlabs.xtrade.kernel.interceptor.TransactionalInterceptor;

import webbroker3.common.WEB3LogSysTimeInterceptor;
import webbroker3.srvregi.data.WEB3SrvRegiMasterDatabaseExtensions;
import webbroker3.srvregi.handler.WEB3AdminSrvRegiAccountAppliStateListHandler;
import webbroker3.srvregi.handler.WEB3AdminSrvRegiAccountChangeHandler;
import webbroker3.srvregi.handler.WEB3AdminSrvRegiAccountChangeInputHandler;
import webbroker3.srvregi.handler.WEB3AdminSrvRegiAccountDataDownloadHandler;
import webbroker3.srvregi.handler.WEB3AdminSrvRegiAccountDataUlStateInquiryHandler;
import webbroker3.srvregi.handler.WEB3AdminSrvRegiAccountDataUploadHandler;
import webbroker3.srvregi.handler.WEB3AdminSrvRegiAccountListChangeInquiryHandler;
import webbroker3.srvregi.handler.WEB3AdminSrvRegiAccountRegistHandler;
import webbroker3.srvregi.handler.WEB3AdminSrvRegiOtherOrgIdDownloadHandler;
import webbroker3.srvregi.handler.WEB3AdminSrvRegiOtherOrgIdListHandler;
import webbroker3.srvregi.handler.WEB3AdminSrvRegiOtherOrgIdUploadHandler;
import webbroker3.srvregi.handler.WEB3AdminSrvRegiServiceActionInfoHandler;
import webbroker3.srvregi.handler.WEB3AdminSrvRegiServiceBidPriceUpdateHandler;
import webbroker3.srvregi.handler.WEB3AdminSrvRegiServiceBidPriceUpdateInputHandler;
import webbroker3.srvregi.handler.WEB3AdminSrvRegiServiceChangeHandler;
import webbroker3.srvregi.handler.WEB3AdminSrvRegiServiceChangeInputHandler;
import webbroker3.srvregi.handler.WEB3AdminSrvRegiServiceDetailHandler;
import webbroker3.srvregi.handler.WEB3AdminSrvRegiServiceRegiHandler;
import webbroker3.srvregi.handler.WEB3AdminSrvRegiSrvListHandler;
import webbroker3.srvregi.handler.WEB3SrvRegiApplicationInputHandler;
import webbroker3.srvregi.handler.WEB3SrvRegiCancelHandler;
import webbroker3.srvregi.handler.WEB3SrvRegiServiceListInquiryHandler;
import webbroker3.srvregi.handler.WEB3SrvRegiServiceStartHandler;
import webbroker3.srvregi.handler.WEB3SrvRegiServiceUseApplicationHandler;
import webbroker3.srvregi.handler.WEB3SrvRegiStreamHandler;
import webbroker3.srvregi.message.WEB3AdminSrvRegiCustomerChangeCompleteRequest;
import webbroker3.srvregi.message.WEB3AdminSrvRegiCustomerChangeCompleteResponse;
import webbroker3.srvregi.message.WEB3AdminSrvRegiCustomerChangeConfirmRequest;
import webbroker3.srvregi.message.WEB3AdminSrvRegiCustomerChangeConfirmResponse;
import webbroker3.srvregi.message.WEB3AdminSrvRegiCustomerChangeInputRequest;
import webbroker3.srvregi.message.WEB3AdminSrvRegiCustomerChangeInputResponse;
import webbroker3.srvregi.message.WEB3AdminSrvRegiCustomerReferenceRequest;
import webbroker3.srvregi.message.WEB3AdminSrvRegiCustomerReferenceResponse;
import webbroker3.srvregi.message.WEB3AdminSrvRegiCustomerRegistCommonRequest;
import webbroker3.srvregi.message.WEB3AdminSrvRegiCustomerRegistCompleteRequest;
import webbroker3.srvregi.message.WEB3AdminSrvRegiCustomerRegistCompleteResponse;
import webbroker3.srvregi.message.WEB3AdminSrvRegiCustomerRegistConfirmRequest;
import webbroker3.srvregi.message.WEB3AdminSrvRegiCustomerRegistConfirmResponse;
import webbroker3.srvregi.message.WEB3AdminSrvRegiDownloadRequest;
import webbroker3.srvregi.message.WEB3AdminSrvRegiDownloadResponse;
import webbroker3.srvregi.message.WEB3AdminSrvRegiOtherOrgIdCommonRequest;
import webbroker3.srvregi.message.WEB3AdminSrvRegiOtherOrgIdDownloadRequest;
import webbroker3.srvregi.message.WEB3AdminSrvRegiOtherOrgIdDownloadResponse;
import webbroker3.srvregi.message.WEB3AdminSrvRegiOtherOrgIdListReferenceRequest;
import webbroker3.srvregi.message.WEB3AdminSrvRegiOtherOrgIdListReferenceResponse;
import webbroker3.srvregi.message.WEB3AdminSrvRegiOtherOrgIdListSearchRequest;
import webbroker3.srvregi.message.WEB3AdminSrvRegiOtherOrgIdListSearchResponse;
import webbroker3.srvregi.message.WEB3AdminSrvRegiOtherOrgIdUploadCancelRequest;
import webbroker3.srvregi.message.WEB3AdminSrvRegiOtherOrgIdUploadCancelResponse;
import webbroker3.srvregi.message.WEB3AdminSrvRegiOtherOrgIdUploadCompleteRequest;
import webbroker3.srvregi.message.WEB3AdminSrvRegiOtherOrgIdUploadCompleteResponse;
import webbroker3.srvregi.message.WEB3AdminSrvRegiOtherOrgIdUploadConfirmRequest;
import webbroker3.srvregi.message.WEB3AdminSrvRegiOtherOrgIdUploadConfirmResponse;
import webbroker3.srvregi.message.WEB3AdminSrvRegiOtherOrgIdUploadInputRequest;
import webbroker3.srvregi.message.WEB3AdminSrvRegiOtherOrgIdUploadInputResponse;
import webbroker3.srvregi.message.WEB3AdminSrvRegiServiceChangeCommonRequest;
import webbroker3.srvregi.message.WEB3AdminSrvRegiServiceChangeCompleteRequest;
import webbroker3.srvregi.message.WEB3AdminSrvRegiServiceChangeCompleteResponse;
import webbroker3.srvregi.message.WEB3AdminSrvRegiServiceChangeConfirmRequest;
import webbroker3.srvregi.message.WEB3AdminSrvRegiServiceChangeConfirmResponse;
import webbroker3.srvregi.message.WEB3AdminSrvRegiServiceChangeInputRequest;
import webbroker3.srvregi.message.WEB3AdminSrvRegiServiceChangeInputResponse;
import webbroker3.srvregi.message.WEB3AdminSrvRegiServiceDetailsRequest;
import webbroker3.srvregi.message.WEB3AdminSrvRegiServiceDetailsResponse;
import webbroker3.srvregi.message.WEB3AdminSrvRegiServiceHistoryRequest;
import webbroker3.srvregi.message.WEB3AdminSrvRegiServiceHistoryResponse;
import webbroker3.srvregi.message.WEB3AdminSrvRegiServiceReferenceRequest;
import webbroker3.srvregi.message.WEB3AdminSrvRegiServiceReferenceResponse;
import webbroker3.srvregi.message.WEB3AdminSrvRegiServiceRegistCommonRequest;
import webbroker3.srvregi.message.WEB3AdminSrvRegiServiceRegistCompleteRequest;
import webbroker3.srvregi.message.WEB3AdminSrvRegiServiceRegistCompleteResponse;
import webbroker3.srvregi.message.WEB3AdminSrvRegiServiceRegistConfirmRequest;
import webbroker3.srvregi.message.WEB3AdminSrvRegiServiceRegistConfirmResponse;
import webbroker3.srvregi.message.WEB3AdminSrvRegiStateRequest;
import webbroker3.srvregi.message.WEB3AdminSrvRegiStateResponse;
import webbroker3.srvregi.message.WEB3AdminSrvRegiSuccBidCommonRequest;
import webbroker3.srvregi.message.WEB3AdminSrvRegiSuccBidCompleteRequest;
import webbroker3.srvregi.message.WEB3AdminSrvRegiSuccBidCompleteResponse;
import webbroker3.srvregi.message.WEB3AdminSrvRegiSuccBidConfirmRequest;
import webbroker3.srvregi.message.WEB3AdminSrvRegiSuccBidConfirmResponse;
import webbroker3.srvregi.message.WEB3AdminSrvRegiSuccBidInputRequest;
import webbroker3.srvregi.message.WEB3AdminSrvRegiSuccBidInputResponse;
import webbroker3.srvregi.message.WEB3AdminSrvRegiUploadCancelRequest;
import webbroker3.srvregi.message.WEB3AdminSrvRegiUploadCancelResponse;
import webbroker3.srvregi.message.WEB3AdminSrvRegiUploadCompleteRequest;
import webbroker3.srvregi.message.WEB3AdminSrvRegiUploadCompleteResponse;
import webbroker3.srvregi.message.WEB3AdminSrvRegiUploadConfirmRequest;
import webbroker3.srvregi.message.WEB3AdminSrvRegiUploadConfirmResponse;
import webbroker3.srvregi.message.WEB3AdminSrvRegiUploadInputRequest;
import webbroker3.srvregi.message.WEB3AdminSrvRegiUploadInputResponse;
import webbroker3.srvregi.message.WEB3AdminSrvRegiUploadStateRequest;
import webbroker3.srvregi.message.WEB3AdminSrvRegiUploadStateResponse;
import webbroker3.srvregi.message.WEB3SrvRegiApplyCommonRequest;
import webbroker3.srvregi.message.WEB3SrvRegiApplyCompleteRequest;
import webbroker3.srvregi.message.WEB3SrvRegiApplyCompleteResponse;
import webbroker3.srvregi.message.WEB3SrvRegiApplyConfirmRequest;
import webbroker3.srvregi.message.WEB3SrvRegiApplyConfirmResponse;
import webbroker3.srvregi.message.WEB3SrvRegiApplyInputRequest;
import webbroker3.srvregi.message.WEB3SrvRegiApplyInputResponse;
import webbroker3.srvregi.message.WEB3SrvRegiCancelCompleteRequest;
import webbroker3.srvregi.message.WEB3SrvRegiCancelCompleteResponse;
import webbroker3.srvregi.message.WEB3SrvRegiCancelConfirmRequest;
import webbroker3.srvregi.message.WEB3SrvRegiCancelConfirmResponse;
import webbroker3.srvregi.message.WEB3SrvRegiConsentRequest;
import webbroker3.srvregi.message.WEB3SrvRegiConsentResponse;
import webbroker3.srvregi.message.WEB3SrvRegiExecRequest;
import webbroker3.srvregi.message.WEB3SrvRegiExecResponse;
import webbroker3.srvregi.message.WEB3SrvRegiReferenceRequest;
import webbroker3.srvregi.message.WEB3SrvRegiReferenceResponse;
import webbroker3.srvregi.message.WEB3SrvRegiStreamRequest;
import webbroker3.srvregi.message.WEB3SrvRegiStreamResponse;
import webbroker3.srvregi.service.delegate.WEB3AdminSrvRegiAccountAppliStateListService;
import webbroker3.srvregi.service.delegate.WEB3AdminSrvRegiAccountChangeInputService;
import webbroker3.srvregi.service.delegate.WEB3AdminSrvRegiAccountChangeService;
import webbroker3.srvregi.service.delegate.WEB3AdminSrvRegiAccountDataDownloadService;
import webbroker3.srvregi.service.delegate.WEB3AdminSrvRegiAccountDataUlStateInquiryService;
import webbroker3.srvregi.service.delegate.WEB3AdminSrvRegiAccountDataUploadService;
import webbroker3.srvregi.service.delegate.WEB3AdminSrvRegiAccountDataUploadUnitService;
import webbroker3.srvregi.service.delegate.WEB3AdminSrvRegiAccountListChangeInquiryService;
import webbroker3.srvregi.service.delegate.WEB3AdminSrvRegiAccountRegistService;
import webbroker3.srvregi.service.delegate.WEB3AdminSrvRegiOtherOrgIdDownloadService;
import webbroker3.srvregi.service.delegate.WEB3AdminSrvRegiOtherOrgIdListService;
import webbroker3.srvregi.service.delegate.WEB3AdminSrvRegiOtherOrgIdUploadService;
import webbroker3.srvregi.service.delegate.WEB3AdminSrvRegiOtherOrgIdUploadUnitService;
import webbroker3.srvregi.service.delegate.WEB3AdminSrvRegiServiceActionInfoService;
import webbroker3.srvregi.service.delegate.WEB3AdminSrvRegiServiceBidPriceUpdateInputService;
import webbroker3.srvregi.service.delegate.WEB3AdminSrvRegiServiceBidPriceUpdateService;
import webbroker3.srvregi.service.delegate.WEB3AdminSrvRegiServiceChangeInputService;
import webbroker3.srvregi.service.delegate.WEB3AdminSrvRegiServiceChangeService;
import webbroker3.srvregi.service.delegate.WEB3AdminSrvRegiServiceDetailService;
import webbroker3.srvregi.service.delegate.WEB3AdminSrvRegiServiceRegiService;
import webbroker3.srvregi.service.delegate.WEB3AdminSrvRegiSrvListService;
import webbroker3.srvregi.service.delegate.WEB3SrvRegiApplicationInputService;
import webbroker3.srvregi.service.delegate.WEB3SrvRegiCancelService;
import webbroker3.srvregi.service.delegate.WEB3SrvRegiExecSendMailService;
import webbroker3.srvregi.service.delegate.WEB3SrvRegiOtherOrgService;
import webbroker3.srvregi.service.delegate.WEB3SrvRegiRegistService;
import webbroker3.srvregi.service.delegate.WEB3SrvRegiServiceListInquiryService;
import webbroker3.srvregi.service.delegate.WEB3SrvRegiServiceStartService;
import webbroker3.srvregi.service.delegate.WEB3SrvRegiServiceUseApplicationService;
import webbroker3.srvregi.service.delegate.WEB3SrvRegiStartInfoService;
import webbroker3.srvregi.service.delegate.WEB3SrvRegiStreamService;
import webbroker3.srvregi.service.delegate.stdimpls.WEB3AdminSrvRegiAccountAppliStateListServiceImpl;
import webbroker3.srvregi.service.delegate.stdimpls.WEB3AdminSrvRegiAccountChangeInputServiceImpl;
import webbroker3.srvregi.service.delegate.stdimpls.WEB3AdminSrvRegiAccountChangeServiceImpl;
import webbroker3.srvregi.service.delegate.stdimpls.WEB3AdminSrvRegiAccountDataDownloadServiceImpl;
import webbroker3.srvregi.service.delegate.stdimpls.WEB3AdminSrvRegiAccountDataUlStateInquiryServiceImpl;
import webbroker3.srvregi.service.delegate.stdimpls.WEB3AdminSrvRegiAccountDataUploadServiceImpl;
import webbroker3.srvregi.service.delegate.stdimpls.WEB3AdminSrvRegiAccountDataUploadUnitServiceImpl;
import webbroker3.srvregi.service.delegate.stdimpls.WEB3AdminSrvRegiAccountListChangeInquiryServiceImpl;
import webbroker3.srvregi.service.delegate.stdimpls.WEB3AdminSrvRegiAccountRegistServiceImpl;
import webbroker3.srvregi.service.delegate.stdimpls.WEB3AdminSrvRegiOtherOrgIdDownloadServiceImpl;
import webbroker3.srvregi.service.delegate.stdimpls.WEB3AdminSrvRegiOtherOrgIdListServiceImpl;
import webbroker3.srvregi.service.delegate.stdimpls.WEB3AdminSrvRegiOtherOrgIdUploadServiceImpl;
import webbroker3.srvregi.service.delegate.stdimpls.WEB3AdminSrvRegiOtherOrgIdUploadUnitServiceImpl;
import webbroker3.srvregi.service.delegate.stdimpls.WEB3AdminSrvRegiServiceActionInfoServiceImpl;
import webbroker3.srvregi.service.delegate.stdimpls.WEB3AdminSrvRegiServiceBidPriceUpdateInputServiceImpl;
import webbroker3.srvregi.service.delegate.stdimpls.WEB3AdminSrvRegiServiceBidPriceUpdateServiceImpl;
import webbroker3.srvregi.service.delegate.stdimpls.WEB3AdminSrvRegiServiceChangeInputServiceImpl;
import webbroker3.srvregi.service.delegate.stdimpls.WEB3AdminSrvRegiServiceChangeServiceImpl;
import webbroker3.srvregi.service.delegate.stdimpls.WEB3AdminSrvRegiServiceDetailServiceImpl;
import webbroker3.srvregi.service.delegate.stdimpls.WEB3AdminSrvRegiServiceRegiServiceImpl;
import webbroker3.srvregi.service.delegate.stdimpls.WEB3AdminSrvRegiSrvListServiceImpl;
import webbroker3.srvregi.service.delegate.stdimpls.WEB3SrvRegiApplicationInputServiceImpl;
import webbroker3.srvregi.service.delegate.stdimpls.WEB3SrvRegiCancelServiceImpl;
import webbroker3.srvregi.service.delegate.stdimpls.WEB3SrvRegiExecSendMailServiceImpl;
import webbroker3.srvregi.service.delegate.stdimpls.WEB3SrvRegiOtherOrgServiceImpl;
import webbroker3.srvregi.service.delegate.stdimpls.WEB3SrvRegiRegistServiceImpl;
import webbroker3.srvregi.service.delegate.stdimpls.WEB3SrvRegiServiceListInquiryServiceImpl;
import webbroker3.srvregi.service.delegate.stdimpls.WEB3SrvRegiServiceStartServiceImpl;
import webbroker3.srvregi.service.delegate.stdimpls.WEB3SrvRegiServiceUseApplicationServiceImpl;
import webbroker3.srvregi.service.delegate.stdimpls.WEB3SrvRegiStartInfoServiceImpl;
import webbroker3.srvregi.service.delegate.stdimpls.WEB3SrvRegiStreamServiceImpl;
import webbroker3.util.WEB3LogUtility;

/**
 * Webbroker3-サービス利用 プラグインクラス。
 *                                                                
 * @@author 張威
 * @@version 1.0
 */
public final class WEB3SrvRegiAppPlugin extends Plugin
{
    /**
     * ログユーティリティ。
     */
    private static WEB3LogUtility log = WEB3LogUtility.getInstance(WEB3SrvRegiAppPlugin.class);

    /**
     * コンストラクタ。
     */
    public WEB3SrvRegiAppPlugin()
    {
        String STR_METHOD_NAME = " WEB3SrvRegiAppPlugin()";
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

        plug(WEB3SrvRegiAppPlugin.class);

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

        // DatabaseExtensions のプラグイン処理 ----------------------
        WEB3SrvRegiMasterDatabaseExtensions.plug();

        // Service の登録処理 ----------------------

        //サービス利用確認メール送信サービス
        Services.registerService(WEB3SrvRegiExecSendMailService.class, new WEB3SrvRegiExecSendMailServiceImpl());

        //サービス利用外部連携サービス
        Services.registerService(WEB3SrvRegiOtherOrgService.class, new WEB3SrvRegiOtherOrgServiceImpl());

        //サービス利用申込登録サービス
        Services.registerService(WEB3SrvRegiRegistService.class, new WEB3SrvRegiRegistServiceImpl());
        
        //サービス利用管理者サービス一覧サービス
        Services.registerService(WEB3AdminSrvRegiSrvListService.class, new WEB3AdminSrvRegiSrvListServiceImpl());

        //サービス利用管理者サービス詳細サービス
        Services.registerService(
            WEB3AdminSrvRegiServiceDetailService.class,
            new WEB3AdminSrvRegiServiceDetailServiceImpl());

        //サービス利用管理者サービス登録サービス
        Services.registerService(
            WEB3AdminSrvRegiServiceRegiService.class,
            new WEB3AdminSrvRegiServiceRegiServiceImpl());

        //サービス利用管理者サービス変更サービス
        Services.registerService(
            WEB3AdminSrvRegiServiceChangeService.class,
            new WEB3AdminSrvRegiServiceChangeServiceImpl());

        //サービス利用管理者サービス変更入力サービス
        Services.registerService(
            WEB3AdminSrvRegiServiceChangeInputService.class,
            new WEB3AdminSrvRegiServiceChangeInputServiceImpl());

        //サービス利用管理者サービス履歴情報サービス
        Services.registerService(
            WEB3AdminSrvRegiServiceActionInfoService.class,
            new WEB3AdminSrvRegiServiceActionInfoServiceImpl());

        //サービス利用管理者顧客データアップロードサービス
        Services.registerService(
            WEB3AdminSrvRegiAccountDataUploadService.class,
            new WEB3AdminSrvRegiAccountDataUploadServiceImpl());

        //サービス利用顧客データアップロードUnitService
        Services.registerService(
            WEB3AdminSrvRegiAccountDataUploadUnitService.class,
            new WEB3AdminSrvRegiAccountDataUploadUnitServiceImpl());

        //サービス利用管理者顧客データUL状況照会サービス
        Services.registerService(
            WEB3AdminSrvRegiAccountDataUlStateInquiryService.class,
            new WEB3AdminSrvRegiAccountDataUlStateInquiryServiceImpl());

        //サービス利用管理者顧客データダウンロードサービス
        Services.registerService(
            WEB3AdminSrvRegiAccountDataDownloadService.class,
            new WEB3AdminSrvRegiAccountDataDownloadServiceImpl());

        //サービス利用管理者顧客一覧変更照会サービス
        Services.registerService(
            WEB3AdminSrvRegiAccountListChangeInquiryService.class,
            new WEB3AdminSrvRegiAccountListChangeInquiryServiceImpl());

        //サービス利用管理者顧客申込状況一覧サービス
        Services.registerService(
            WEB3AdminSrvRegiAccountAppliStateListService.class,
            new WEB3AdminSrvRegiAccountAppliStateListServiceImpl());

        //サービス利用管理者顧客登録サービス
        Services.registerService(
            WEB3AdminSrvRegiAccountRegistService.class,
            new WEB3AdminSrvRegiAccountRegistServiceImpl());

        //サービス利用管理者顧客変更サービス
        Services.registerService(
            WEB3AdminSrvRegiAccountChangeService.class,
            new WEB3AdminSrvRegiAccountChangeServiceImpl());

        //サービス利用管理者顧客変更入力サービス
        Services.registerService(
            WEB3AdminSrvRegiAccountChangeInputService.class,
            new WEB3AdminSrvRegiAccountChangeInputServiceImpl());

        //サービス利用管理者サービス落札額更新サービス
        Services.registerService(
            WEB3AdminSrvRegiServiceBidPriceUpdateService.class,
            new WEB3AdminSrvRegiServiceBidPriceUpdateServiceImpl());

        //サービス利用管理者サービス落札額更新入力サービス
        Services.registerService(
            WEB3AdminSrvRegiServiceBidPriceUpdateInputService.class,
            new WEB3AdminSrvRegiServiceBidPriceUpdateInputServiceImpl());

        //サービス利用取消サービス
        Services.registerService(WEB3SrvRegiCancelService.class, new WEB3SrvRegiCancelServiceImpl());

        //サービス利用サービス一覧照会サービス
        Services.registerService(
            WEB3SrvRegiServiceListInquiryService.class,
            new WEB3SrvRegiServiceListInquiryServiceImpl());

        //サービス利用サービス起動サービス
        Services.registerService(WEB3SrvRegiServiceStartService.class, new WEB3SrvRegiServiceStartServiceImpl());

        //サービス利用申込サービス
        Services.registerService(
            WEB3SrvRegiServiceUseApplicationService.class,
            new WEB3SrvRegiServiceUseApplicationServiceImpl());

        //サービス利用申込入力サービス
        Services.registerService(
            WEB3SrvRegiApplicationInputService.class,
            new WEB3SrvRegiApplicationInputServiceImpl());
            
        //サービス利用起動情報サービス
        Services.registerService(
            WEB3SrvRegiStartInfoService.class,
            new WEB3SrvRegiStartInfoServiceImpl());

        //サービス利用管理者外部連携ID一覧照会ｻｰﾋﾞｽ
        Services.registerService(
            WEB3AdminSrvRegiOtherOrgIdListService.class,
            new WEB3AdminSrvRegiOtherOrgIdListServiceImpl());

        //サービス利用管理者外部連携ID照会ｱｯﾌﾟﾛｰﾄﾞUnitService
        Services.registerService(
            WEB3AdminSrvRegiOtherOrgIdUploadUnitService.class,
            new WEB3AdminSrvRegiOtherOrgIdUploadUnitServiceImpl());

        //サービス利用管理者外部連携ID照会ｱｯﾌﾟﾛｰﾄﾞｻｰﾋﾞｽ
        Services.registerService(
            WEB3AdminSrvRegiOtherOrgIdUploadService.class,
            new WEB3AdminSrvRegiOtherOrgIdUploadServiceImpl());

        //サービス利用管理者外部連携ID照会ﾀﾞｳﾝﾛｰﾄﾞｻｰﾋﾞｽ
        Services.registerService(
            WEB3AdminSrvRegiOtherOrgIdDownloadService.class,
            new WEB3AdminSrvRegiOtherOrgIdDownloadServiceImpl());

        //サービス利用債券連携サービス
        Services.registerService(
            WEB3SrvRegiStreamService.class,
            new WEB3SrvRegiStreamServiceImpl());

		//Service に ServiceInterceptor を設定する ----------------------

		//サービス利用管理者サービス一覧サービス
		Services.addInterceptor(WEB3AdminSrvRegiSrvListService.class, new WEB3AdminSrvRegiSrvListServiceInterceptor());

		//サービス利用管理者サービス詳細サービス
		Services.addInterceptor(
			WEB3AdminSrvRegiServiceDetailService.class,
			new WEB3AdminSrvRegiServiceDetailServiceInterceptor());

		//サービス利用管理者サービス登録サービス
		Services.addInterceptor(
			WEB3AdminSrvRegiServiceRegiService.class,
			new WEB3AdminSrvRegiServiceRegiServiceInterceptor());

		//サービス利用管理者サービス変更サービス
		Services.addInterceptor(
			WEB3AdminSrvRegiServiceChangeService.class,
			new WEB3AdminSrvRegiServiceChangeServiceInterceptor());

		//サービス利用管理者サービス変更入力サービス
		Services.addInterceptor(
			WEB3AdminSrvRegiServiceChangeInputService.class,
			new WEB3AdminSrvRegiServiceChangeInputServiceInterceptor());

		//サービス利用管理者サービス履歴情報サービス
		Services.addInterceptor(
			WEB3AdminSrvRegiServiceActionInfoService.class,
			new WEB3AdminSrvRegiServiceActionInfoServiceInterceptor());

		//サービス利用管理者顧客データアップロードサービス
		Services.addInterceptor(
			WEB3AdminSrvRegiAccountDataUploadService.class,
			new WEB3AdminSrvRegiAccountDataUploadInterceptor());

		//サービス利用顧客データアップロードUnitService
		Services.addInterceptor(
			WEB3AdminSrvRegiAccountDataUploadUnitService.class,
			new WEB3AdminSrvRegiAccountDataUploadInterceptor());

		//サービス利用管理者顧客データダウンロードサービス
		Services.addInterceptor(
			WEB3AdminSrvRegiAccountDataDownloadService.class,
			new WEB3AdminSrvRegiAccountDataDownloadServiceInterceptor());

		//サービス利用管理者顧客データUL状況照会サービス
		Services.addInterceptor(
			WEB3AdminSrvRegiAccountDataUlStateInquiryService.class,
			new WEB3AdminSrvRegiAccountDataUlStateInquiryServiceInterceptor());

		//サービス利用管理者顧客一覧変更照会サービス
		Services.addInterceptor(
			WEB3AdminSrvRegiAccountListChangeInquiryService.class,
			new WEB3AdminSrvRegiAccountListChangeInquiryServiceInterceptor());

		//サービス利用管理者顧客申込状況一覧サービス
		Services.addInterceptor(
			WEB3AdminSrvRegiAccountAppliStateListService.class,
			new WEB3AdminSrvRegiAccountAppliStateListServiceInterceptor());

		//サービス利用管理者顧客登録サービス
		Services.addInterceptor(
			WEB3AdminSrvRegiAccountRegistService.class,
			new WEB3AdminSrvRegiAccountRegistServiceInterceptor());

		//サービス利用管理者顧客変更サービス
		Services.addInterceptor(
			WEB3AdminSrvRegiAccountChangeService.class,
			new WEB3AdminSrvRegiAccountChangeServiceInterceptor());

		//サービス利用管理者顧客変更入力サービス
		Services.addInterceptor(
			WEB3AdminSrvRegiAccountChangeInputService.class,
			new WEB3AdminSrvRegiAccountChangeInputServiceInterceptor());

		//サービス利用管理者サービス落札額更新サービス
		Services.addInterceptor(
			WEB3AdminSrvRegiServiceBidPriceUpdateService.class,
			new WEB3AdminSrvRegiServiceBidPriceUpdateServiceInterceptor());

		//サービス利用管理者サービス落札額更新入力サービス
		Services.addInterceptor(
			WEB3AdminSrvRegiServiceBidPriceUpdateInputService.class,
			new WEB3AdminSrvRegiServiceBidPriceUpdateInputServiceInterceptor());

		//サービス利用取消サービス
		Services.addInterceptor(WEB3SrvRegiCancelService.class, new WEB3SrvRegiCancelServiceInterceptor());

		//サービス利用サービス一覧照会サービス
		Services.addInterceptor(
			WEB3SrvRegiServiceListInquiryService.class,
			new WEB3SrvRegiServiceListInquiryServiceInterceptor());

		//サービス利用サービス起動サービス
		Services.addInterceptor(WEB3SrvRegiServiceStartService.class, new WEB3SrvRegiServiceStartServiceInterceptor());

		//サービス利用申込サービス
		Services.addInterceptor(
			WEB3SrvRegiServiceUseApplicationService.class,
			new WEB3SrvRegiServiceUseApplicationServiceInterceptor());

		//サービス利用申込入力サービス
		Services.addInterceptor(
			WEB3SrvRegiApplicationInputService.class,
			new WEB3SrvRegiApplicationInputServiceInterceptor());    

        //サービス利用管理者外部連携ID一覧照会ｻｰﾋﾞｽ
        Services.addInterceptor(
            WEB3AdminSrvRegiOtherOrgIdListService.class,
            new WEB3AdminSrvRegiOtherOrgIdListServiceInterceptor());

        //サービス利用管理者外部連携ID照会ｱｯﾌﾟﾛｰﾄﾞｻｰﾋﾞｽ
        Services.addInterceptor(
            WEB3AdminSrvRegiOtherOrgIdUploadService.class,
            new WEB3AdminSrvRegiOtherOrgIdUploadServiceInterceptor());

        //サービス利用管理者外部連携ID照会ｱｯﾌﾟﾛｰﾄﾞUnitService
        Services.addInterceptor(
            WEB3AdminSrvRegiOtherOrgIdUploadUnitService.class,
            new WEB3AdminSrvRegiOtherOrgIdUploadUnitServiceInterceptor());

        //サービス利用管理者外部連携ID照会ﾀﾞｳﾝﾛｰﾄﾞｻｰﾋﾞｽ
        Services.addInterceptor(
            WEB3AdminSrvRegiOtherOrgIdDownloadService.class,
            new WEB3AdminSrvRegiOtherOrgIdDownloadServiceInterceptor());

        //サービス利用債券連携サービス
        Services.addInterceptor(
            WEB3SrvRegiStreamService.class,
            new WEB3SrvRegiStreamServiceInterceptor());

        // Service の Interceptor 設定処理 ----------------------
        // 自動トランザクション設定

        //サービス利用管理者サービス一覧サービス
        Services.addInterceptor(
            WEB3AdminSrvRegiSrvListService.class,
            new TransactionalInterceptor(TransactionalInterceptor.TX_JOIN_EXISTING));

        //サービス利用管理者サービス詳細サービス
        Services.addInterceptor(
            WEB3AdminSrvRegiServiceDetailService.class,
            new TransactionalInterceptor(TransactionalInterceptor.TX_JOIN_EXISTING));

        //サービス利用管理者サービス登録サービス
        Services.addInterceptor(
            WEB3AdminSrvRegiServiceRegiService.class,
            new TransactionalInterceptor(TransactionalInterceptor.TX_JOIN_EXISTING));

        //サービス利用管理者サービス変更サービス
        Services.addInterceptor(
            WEB3AdminSrvRegiServiceChangeService.class,
            new TransactionalInterceptor(TransactionalInterceptor.TX_JOIN_EXISTING));

        //サービス利用管理者サービス変更入力サービス
        Services.addInterceptor(
            WEB3AdminSrvRegiServiceChangeInputService.class,
            new TransactionalInterceptor(TransactionalInterceptor.TX_JOIN_EXISTING));

        //サービス利用管理者サービス履歴情報サービス
        Services.addInterceptor(
            WEB3AdminSrvRegiServiceActionInfoService.class,
            new TransactionalInterceptor(TransactionalInterceptor.TX_JOIN_EXISTING));

        //サービス利用管理者顧客データアップロードサービス
        Services.addInterceptor(
            WEB3AdminSrvRegiAccountDataUploadService.class,
            new TransactionalInterceptor(TransactionalInterceptor.TX_JOIN_EXISTING));

        //サービス利用顧客データアップロードUnitService
        Services.addInterceptor(
            WEB3AdminSrvRegiAccountDataUploadUnitService.class,
            new TransactionalInterceptor(TransactionalInterceptor.TX_CREATE_NEW));

        //サービス利用管理者顧客データUL状況照会サービス
        Services.addInterceptor(
            WEB3AdminSrvRegiAccountDataUlStateInquiryService.class,
            new TransactionalInterceptor(TransactionalInterceptor.TX_JOIN_EXISTING));

        //サービス利用管理者顧客データダウンロードサービス
        Services.addInterceptor(
            WEB3AdminSrvRegiAccountDataDownloadService.class,
            new TransactionalInterceptor(TransactionalInterceptor.TX_JOIN_EXISTING));

        //サービス利用管理者顧客一覧変更照会サービス
        Services.addInterceptor(
            WEB3AdminSrvRegiAccountListChangeInquiryService.class,
            new TransactionalInterceptor(TransactionalInterceptor.TX_JOIN_EXISTING));

        //サービス利用管理者顧客申込状況一覧サービス
        Services.addInterceptor(
            WEB3AdminSrvRegiAccountAppliStateListService.class,
            new TransactionalInterceptor(TransactionalInterceptor.TX_JOIN_EXISTING));

        //サービス利用管理者顧客登録サービス
        Services.addInterceptor(
            WEB3AdminSrvRegiAccountRegistService.class,
            new TransactionalInterceptor(TransactionalInterceptor.TX_JOIN_EXISTING));

        //サービス利用管理者顧客変更サービス
        Services.addInterceptor(
            WEB3AdminSrvRegiAccountChangeService.class,
            new TransactionalInterceptor(TransactionalInterceptor.TX_JOIN_EXISTING));

        //サービス利用管理者顧客変更入力サービス
        Services.addInterceptor(
            WEB3AdminSrvRegiAccountChangeInputService.class,
            new TransactionalInterceptor(TransactionalInterceptor.TX_JOIN_EXISTING));

        //サービス利用管理者サービス落札額更新サービス
        Services.addInterceptor(
            WEB3AdminSrvRegiServiceBidPriceUpdateService.class,
            new TransactionalInterceptor(TransactionalInterceptor.TX_JOIN_EXISTING));

        //サービス利用管理者サービス落札額更新入力サービス
        Services.addInterceptor(
            WEB3AdminSrvRegiServiceBidPriceUpdateInputService.class,
            new TransactionalInterceptor(TransactionalInterceptor.TX_JOIN_EXISTING));

        //サービス利用取消サービス
        Services.addInterceptor(
            WEB3SrvRegiCancelService.class,
            new TransactionalInterceptor(TransactionalInterceptor.TX_JOIN_EXISTING));

        //サービス利用サービス一覧照会サービス
        Services.addInterceptor(
            WEB3SrvRegiServiceListInquiryService.class,
            new TransactionalInterceptor(TransactionalInterceptor.TX_JOIN_EXISTING));

        //サービス利用サービス起動サービス
        Services.addInterceptor(
            WEB3SrvRegiServiceStartService.class,
            new TransactionalInterceptor(TransactionalInterceptor.TX_JOIN_EXISTING));

        //サービス利用申込サービス
        Services.addInterceptor(
            WEB3SrvRegiServiceUseApplicationService.class,
            new TransactionalInterceptor(TransactionalInterceptor.TX_JOIN_EXISTING));

        //サービス利用申込入力サービス
        Services.addInterceptor(
            WEB3SrvRegiApplicationInputService.class,
            new TransactionalInterceptor(TransactionalInterceptor.TX_JOIN_EXISTING));

        //サービス利用管理者外部連携ID一覧照会ｻｰﾋﾞｽ
        Services.addInterceptor(
            WEB3AdminSrvRegiOtherOrgIdListService.class,
            new TransactionalInterceptor(TransactionalInterceptor.TX_JOIN_EXISTING));

        //サービス利用管理者外部連携ID照会ｱｯﾌﾟﾛｰﾄﾞUnitService
        Services.addInterceptor(
            WEB3AdminSrvRegiOtherOrgIdUploadUnitService.class,
            new TransactionalInterceptor(TransactionalInterceptor.TX_CREATE_NEW));

        //サービス利用管理者外部連携ID照会ｱｯﾌﾟﾛｰﾄﾞｻｰﾋﾞｽ
        Services.addInterceptor(
            WEB3AdminSrvRegiOtherOrgIdUploadService.class,
            new TransactionalInterceptor(TransactionalInterceptor.TX_JOIN_EXISTING));

        //サービス利用管理者外部連携ID照会ﾀﾞｳﾝﾛｰﾄﾞｻｰﾋﾞｽ
        Services.addInterceptor(
            WEB3AdminSrvRegiOtherOrgIdDownloadService.class,
            new TransactionalInterceptor(TransactionalInterceptor.TX_JOIN_EXISTING));

        //サービス利用債券連携サービス
        Services.addInterceptor(
            WEB3SrvRegiStreamService.class,
            new TransactionalInterceptor(TransactionalInterceptor.TX_JOIN_EXISTING));

        // Service.execute()呼び出し前後に  ----------------------
        // 処理開始時刻と処理終了時刻をログ出力するように設定する

        //サービス利用確認メール送信サービス
        Services.addInterceptor(WEB3SrvRegiExecSendMailService.class, new WEB3LogSysTimeInterceptor());

        //サービス利用外部連携サービス
        Services.addInterceptor(WEB3SrvRegiOtherOrgService.class, new WEB3LogSysTimeInterceptor());

        //サービス利用申込登録サービス
        Services.addInterceptor(WEB3SrvRegiRegistService.class, new WEB3LogSysTimeInterceptor());

        //サービス利用管理者サービス一覧サービス
        Services.addInterceptor(WEB3AdminSrvRegiSrvListService.class, new WEB3LogSysTimeInterceptor());

        //サービス利用管理者サービス詳細サービス
        Services.addInterceptor(WEB3AdminSrvRegiServiceDetailService.class, new WEB3LogSysTimeInterceptor());

        //サービス利用管理者サービス登録サービス
        Services.addInterceptor(WEB3AdminSrvRegiServiceRegiService.class, new WEB3LogSysTimeInterceptor());

        //サービス利用管理者サービス変更サービス
        Services.addInterceptor(WEB3AdminSrvRegiServiceChangeService.class, new WEB3LogSysTimeInterceptor());

        //サービス利用管理者サービス変更入力サービス
        Services.addInterceptor(WEB3AdminSrvRegiServiceChangeInputService.class, new WEB3LogSysTimeInterceptor());

        //サービス利用管理者サービス履歴情報サービス
        Services.addInterceptor(WEB3AdminSrvRegiServiceActionInfoService.class, new WEB3LogSysTimeInterceptor());

        //サービス利用管理者顧客データアップロードサービス
        Services.addInterceptor(WEB3AdminSrvRegiAccountDataUploadService.class, new WEB3LogSysTimeInterceptor());

        //サービス利用顧客データアップロードUnitService
        Services.addInterceptor(WEB3AdminSrvRegiAccountDataUploadUnitService.class, new WEB3LogSysTimeInterceptor());

        //サービス利用管理者顧客データUL状況照会サービス
        Services.addInterceptor(
            WEB3AdminSrvRegiAccountDataUlStateInquiryService.class,
            new WEB3LogSysTimeInterceptor());

        //サービス利用管理者顧客データダウンロードサービス
        Services.addInterceptor(WEB3AdminSrvRegiAccountDataDownloadService.class, new WEB3LogSysTimeInterceptor());

        //サービス利用管理者顧客一覧変更照会サービス
        Services.addInterceptor(WEB3AdminSrvRegiAccountListChangeInquiryService.class, new WEB3LogSysTimeInterceptor());

        //サービス利用管理者顧客申込状況一覧サービス
        Services.addInterceptor(WEB3AdminSrvRegiAccountAppliStateListService.class, new WEB3LogSysTimeInterceptor());

        //サービス利用管理者顧客登録サービス
        Services.addInterceptor(WEB3AdminSrvRegiAccountRegistService.class, new WEB3LogSysTimeInterceptor());

        //サービス利用管理者顧客変更サービス
        Services.addInterceptor(WEB3AdminSrvRegiAccountChangeService.class, new WEB3LogSysTimeInterceptor());

        //サービス利用管理者顧客変更入力サービス
        Services.addInterceptor(WEB3AdminSrvRegiAccountChangeInputService.class, new WEB3LogSysTimeInterceptor());

        //サービス利用管理者サービス落札額更新サービス
        Services.addInterceptor(WEB3AdminSrvRegiServiceBidPriceUpdateService.class, new WEB3LogSysTimeInterceptor());

        //サービス利用管理者サービス落札額更新入力サービス
        Services.addInterceptor(
            WEB3AdminSrvRegiServiceBidPriceUpdateInputService.class,
            new WEB3LogSysTimeInterceptor());

        //サービス利用取消サービス
        Services.addInterceptor(WEB3SrvRegiCancelService.class, new WEB3LogSysTimeInterceptor());

        //サービス利用サービス一覧照会サービス
        Services.addInterceptor(WEB3SrvRegiServiceListInquiryService.class, new WEB3LogSysTimeInterceptor());

        //サービス利用サービス起動サービス
        Services.addInterceptor(WEB3SrvRegiServiceStartService.class, new WEB3LogSysTimeInterceptor());

        //サービス利用申込サービス
        Services.addInterceptor(WEB3SrvRegiServiceUseApplicationService.class, new WEB3LogSysTimeInterceptor());

        //サービス利用申込入力サービス
        Services.addInterceptor(WEB3SrvRegiApplicationInputService.class, new WEB3LogSysTimeInterceptor());

        //サービス利用管理者外部連携ID一覧照会ｻｰﾋﾞｽ
        Services.addInterceptor(WEB3AdminSrvRegiOtherOrgIdListService.class, new WEB3LogSysTimeInterceptor());

        //サービス利用管理者外部連携ID照会ｱｯﾌﾟﾛｰﾄﾞUnitService
        Services.addInterceptor(
            WEB3AdminSrvRegiOtherOrgIdUploadUnitService.class, new WEB3LogSysTimeInterceptor());

        //サービス利用管理者外部連携ID照会ｱｯﾌﾟﾛｰﾄﾞｻｰﾋﾞｽ
        Services.addInterceptor(
            WEB3AdminSrvRegiOtherOrgIdUploadService.class, new WEB3LogSysTimeInterceptor());

        //サービス利用管理者外部連携ID照会ﾀﾞｳﾝﾛｰﾄﾞｻｰﾋﾞｽ
        Services.addInterceptor(
            WEB3AdminSrvRegiOtherOrgIdDownloadService.class, new WEB3LogSysTimeInterceptor());

        //サービス利用債券連携サービス
        Services.addInterceptor(
            WEB3SrvRegiStreamService.class, new WEB3LogSysTimeInterceptor());

        // Message の登録処理 ----------------------

        //サービス利用サービス一覧照会リクエスト
        regClass(WEB3SrvRegiReferenceRequest.class);
        //サービス利用サービス一覧照会レスポンス
        regClass(WEB3SrvRegiReferenceResponse.class);

        //サービス利用サービス起動リクエスト
        regClass(WEB3SrvRegiExecRequest.class);
        //サービス利用サービス起動レスポンス
        regClass(WEB3SrvRegiExecResponse.class);

        //サービス利用取消確認リクエスト
        regClass(WEB3SrvRegiCancelConfirmRequest.class);
        //サービス利用取消確認レスポンス
        regClass(WEB3SrvRegiCancelConfirmResponse.class);

        //サービス利用取消完了リクエスト
        regClass(WEB3SrvRegiCancelCompleteRequest.class);
        //サービス利用取消完了レスポンス
        regClass(WEB3SrvRegiCancelCompleteResponse.class);

        //サービス利用申込確認リクエスト
        regClass(WEB3SrvRegiApplyConfirmRequest.class);
        //サービス利用申込確認レスポンス
        regClass(WEB3SrvRegiApplyConfirmResponse.class);

        //サービス利用申込完了リクエスト
        regClass(WEB3SrvRegiApplyCompleteRequest.class);
        //サービス利用申込完了レスポンス
        regClass(WEB3SrvRegiApplyCompleteResponse.class);

        //サービス利用申込共通リクエスト
        regClass(WEB3SrvRegiApplyCommonRequest.class);

        //サービス利用申込入力リクエスト
        regClass(WEB3SrvRegiApplyInputRequest.class);
        //サービス利用申込入力レスポンス
        regClass(WEB3SrvRegiApplyInputResponse.class);

        //サービス利用同意書リクエスト
        regClass(WEB3SrvRegiConsentRequest.class);
        //サービス利用同意書レスポンス
        regClass(WEB3SrvRegiConsentResponse.class);

        //サービス利用管理者サービス一覧リクエスト
        regClass(WEB3AdminSrvRegiServiceReferenceRequest.class);
        //サービス利用管理者サービス一覧レスポンス
        regClass(WEB3AdminSrvRegiServiceReferenceResponse.class);

        //サービス利用管理者サービス詳細リクエスト
        regClass(WEB3AdminSrvRegiServiceDetailsRequest.class);
        //サービス利用管理者サービス詳細レスポンス
        regClass(WEB3AdminSrvRegiServiceDetailsResponse.class);

        //サービス利用管理者サービス登録確認リクエスト
        regClass(WEB3AdminSrvRegiServiceRegistConfirmRequest.class);
        //サービス利用管理者サービス登録確認レスポンス
        regClass(WEB3AdminSrvRegiServiceRegistConfirmResponse.class);

        //サービス利用管理者サービス登録完了リクエスト
        regClass(WEB3AdminSrvRegiServiceRegistCompleteRequest.class);
        //サービス利用管理者サービス登録完了レスポンス
        regClass(WEB3AdminSrvRegiServiceRegistCompleteResponse.class);

        //サービス利用管理者サービス登録共通リクエスト
        regClass(WEB3AdminSrvRegiServiceRegistCommonRequest.class);

        //サービス利用管理者サービス変更確認リクエスト
        regClass(WEB3AdminSrvRegiServiceChangeConfirmRequest.class);
        //サービス利用管理者サービス変更確認レスポンス
        regClass(WEB3AdminSrvRegiServiceChangeConfirmResponse.class);

        //サービス利用管理者サービス変更完了リクエスト
        regClass(WEB3AdminSrvRegiServiceChangeCompleteRequest.class);
        //サービス利用管理者サービス変更完了レスポンス
        regClass(WEB3AdminSrvRegiServiceChangeCompleteResponse.class);

        //サービス利用管理者サービス変更共通リクエスト
        regClass(WEB3AdminSrvRegiServiceChangeCommonRequest.class);

        //サービス利用管理者サービス変更入力リクエスト
        regClass(WEB3AdminSrvRegiServiceChangeInputRequest.class);
        //サービス利用管理者サービス変更入力レスポンス
        regClass(WEB3AdminSrvRegiServiceChangeInputResponse.class);

        //サービス利用管理者サービス落札額更新確認リクエスト
        regClass(WEB3AdminSrvRegiSuccBidConfirmRequest.class);
        //サービス利用管理者サービス落札額更新確認レスポンス
        regClass(WEB3AdminSrvRegiSuccBidConfirmResponse.class);

        //サービス利用管理者サービス落札額更新完了リクエスト
        regClass(WEB3AdminSrvRegiSuccBidCompleteRequest.class);
        //サービス利用管理者サービス落札額更新完了レスポンス
        regClass(WEB3AdminSrvRegiSuccBidCompleteResponse.class);

        //サービス利用管理者サービス落札額更新共通リクエスト
        regClass(WEB3AdminSrvRegiSuccBidCommonRequest.class);

        //サービス利用管理者サービス落札額更新入力リクエスト
        regClass(WEB3AdminSrvRegiSuccBidInputRequest.class);
        //サービス利用管理者サービス落札額更新入力レスポンス
        regClass(WEB3AdminSrvRegiSuccBidInputResponse.class);

        //サービス利用管理者サービス履歴情報リクエスト
        regClass(WEB3AdminSrvRegiServiceHistoryRequest.class);
        //サービス利用管理者サービス履歴情報レスポンス
        regClass(WEB3AdminSrvRegiServiceHistoryResponse.class);

        //サービス利用管理者顧客データUL状況照会リクエスト
        regClass(WEB3AdminSrvRegiUploadStateRequest.class);
        //サービス利用管理者顧客データUL状況照会レスポンス
        regClass(WEB3AdminSrvRegiUploadStateResponse.class);

        //サービス利用管理者顧客データアップロード確認リクエスト
        regClass(WEB3AdminSrvRegiUploadConfirmRequest.class);
        //サービス利用管理者顧客データアップロード確認レスポンス
        regClass(WEB3AdminSrvRegiUploadConfirmResponse.class);

        //サービス利用管理者顧客データアップロード完了リクエスト
        regClass(WEB3AdminSrvRegiUploadCompleteRequest.class);
        //サービス利用管理者顧客データアップロード完了レスポンス
        regClass(WEB3AdminSrvRegiUploadCompleteResponse.class);

        //サービス利用管理者顧客データアップロード中止リクエスト
        regClass(WEB3AdminSrvRegiUploadCancelRequest.class);
        //サービス利用管理者顧客データアップロード中止レスポンス
        regClass(WEB3AdminSrvRegiUploadCancelResponse.class);

        //サービス利用管理者顧客データアップロード入力リクエスト
        regClass(WEB3AdminSrvRegiUploadInputRequest.class);
        //サービス利用管理者顧客データアップロード入力レスポンス
        regClass(WEB3AdminSrvRegiUploadInputResponse.class);

        //サービス利用管理者顧客データダウンロードリクエスト
        regClass(WEB3AdminSrvRegiDownloadRequest.class);
        //サービス利用管理者顧客データダウンロードレスポンス
        regClass(WEB3AdminSrvRegiDownloadResponse.class);

        //サービス利用管理者顧客一覧変更照会リクエスト
        regClass(WEB3AdminSrvRegiCustomerReferenceRequest.class);
        //サービス利用管理者顧客一覧変更照会レスポンス
        regClass(WEB3AdminSrvRegiCustomerReferenceResponse.class);

        //サービス利用管理者顧客申込状況一覧リクエスト
        regClass(WEB3AdminSrvRegiStateRequest.class);
        //サービス利用管理者顧客申込状況一覧レスポンス
        regClass(WEB3AdminSrvRegiStateResponse.class);

        //サービス利用管理者顧客登録確認リクエスト
        regClass(WEB3AdminSrvRegiCustomerRegistConfirmRequest.class);
        //サービス利用管理者顧客登録確認レスポンス
        regClass(WEB3AdminSrvRegiCustomerRegistConfirmResponse.class);

        //サービス利用管理者顧客登録完了リクエスト
        regClass(WEB3AdminSrvRegiCustomerRegistCompleteRequest.class);
        //サービス利用管理者顧客登録完了レスポンス
        regClass(WEB3AdminSrvRegiCustomerRegistCompleteResponse.class);

        //サービス利用管理者顧客登録共通リクエスト
        regClass(WEB3AdminSrvRegiCustomerRegistCommonRequest.class);

        //サービス利用管理者顧客変更確認リクエスト
        regClass(WEB3AdminSrvRegiCustomerChangeConfirmRequest.class);
        //サービス利用管理者顧客変更確認レスポンス
        regClass(WEB3AdminSrvRegiCustomerChangeConfirmResponse.class);

        //サービス利用管理者顧客変更完了リクエスト
        regClass(WEB3AdminSrvRegiCustomerChangeCompleteRequest.class);
        //サービス利用管理者顧客変更完了レスポンス
        regClass(WEB3AdminSrvRegiCustomerChangeCompleteResponse.class);

        //サービス利用管理者顧客変更入力リクエスト
        regClass(WEB3AdminSrvRegiCustomerChangeInputRequest.class);
        //サービス利用管理者顧客変更入力レスポンス
        regClass(WEB3AdminSrvRegiCustomerChangeInputResponse.class);

        //サービス利用管理者外部連携ID照会共通ﾘｸｴｽﾄ
        regClass(WEB3AdminSrvRegiOtherOrgIdCommonRequest.class);

        //サービス利用管理者外部連携ID照会ﾀﾞｳﾝﾛｰﾄﾞﾘｸｴｽﾄ
        regClass(WEB3AdminSrvRegiOtherOrgIdDownloadRequest.class);
        //サービス利用管理者外部連携ID照会ﾀﾞｳﾝﾛｰﾄﾞﾚｽﾎﾟﾝｽ
        regClass(WEB3AdminSrvRegiOtherOrgIdDownloadResponse.class);

        //サービス利用管理者外部連携ID一覧照会ﾘｸｴｽﾄ
        regClass(WEB3AdminSrvRegiOtherOrgIdListReferenceRequest.class);
        //サービス利用管理者外部連携ID一覧照会ﾚｽﾎﾟﾝｽ
        regClass(WEB3AdminSrvRegiOtherOrgIdListReferenceResponse.class);

        //サービス利用管理者外部連携ID一覧条件入力ﾘｸｴｽﾄ
        regClass(WEB3AdminSrvRegiOtherOrgIdListSearchRequest.class);
        //サービス利用管理者外部連携ID一覧条件入力ﾚｽﾎﾟﾝｽ
        regClass(WEB3AdminSrvRegiOtherOrgIdListSearchResponse.class);

        //サービス利用管理者外部連携ID照会ｱｯﾌﾟﾛｰﾄﾞ中止ﾘｸｴｽﾄ
        regClass(WEB3AdminSrvRegiOtherOrgIdUploadCancelRequest.class);
        //サービス利用管理者外部連携ID照会ｱｯﾌﾟﾛｰﾄﾞ中止ﾚｽﾎﾟﾝｽ
        regClass(WEB3AdminSrvRegiOtherOrgIdUploadCancelResponse.class);

        //サービス利用管理者外部連携ID照会ｱｯﾌﾟﾛｰﾄﾞ完了ﾘｸｴｽﾄ
        regClass(WEB3AdminSrvRegiOtherOrgIdUploadCompleteRequest.class);
        //サービス利用管理者外部連携ID照会ｱｯﾌﾟﾛｰﾄﾞ完了ﾚｽﾎﾟﾝｽ
        regClass(WEB3AdminSrvRegiOtherOrgIdUploadCompleteResponse.class);

        //サービス利用管理者外部連携ID照会ｱｯﾌﾟﾛｰﾄﾞ確認ﾘｸｴｽﾄ
        regClass(WEB3AdminSrvRegiOtherOrgIdUploadConfirmRequest.class);
        //サービス利用管理者外部連携ID照会ｱｯﾌﾟﾛｰﾄﾞ確認ﾚｽﾎﾟﾝｽ
        regClass(WEB3AdminSrvRegiOtherOrgIdUploadConfirmResponse.class);

        //サービス利用管理者外部連携ID照会ｱｯﾌﾟﾛｰﾄﾞ入力ﾘｸｴｽﾄ
        regClass(WEB3AdminSrvRegiOtherOrgIdUploadInputRequest.class);
        //サービス利用管理者外部連携ID照会ｱｯﾌﾟﾛｰﾄﾞ入力ﾚｽﾎﾟﾝｽ
        regClass(WEB3AdminSrvRegiOtherOrgIdUploadInputResponse.class);

        //サービス利用債券連携ﾘｸｴｽﾄ
        regClass(WEB3SrvRegiStreamRequest.class);
        //サービス利用債券連携ﾚｽﾎﾟﾝｽ
        regClass(WEB3SrvRegiStreamResponse.class);

        //Handler の登録処理 ----------------------

        //サービス利用管理者顧客申込状況一覧 用ハンドラーの登録
        regHandler(
            WEB3SrvRegiAppPlugin.class,
            WEB3AdminSrvRegiStateRequest.class,
            WEB3AdminSrvRegiAccountAppliStateListHandler.class,
            "searchAccountAppliState");

        //サービス利用管理者顧客変更 用ハンドラーの登録
        regHandler(
            WEB3SrvRegiAppPlugin.class,
            WEB3AdminSrvRegiCustomerChangeConfirmRequest.class,
            WEB3AdminSrvRegiAccountChangeHandler.class,
            "confirmAccountChange");

        //サービス利用管理者顧客変更 用ハンドラーの登録
        regHandler(
            WEB3SrvRegiAppPlugin.class,
            WEB3AdminSrvRegiCustomerChangeCompleteRequest.class,
            WEB3AdminSrvRegiAccountChangeHandler.class,
            "completeAccountChange");

        //サービス利用管理者顧客変更入力 用ハンドラーの登録
        regHandler(
            WEB3SrvRegiAppPlugin.class,
            WEB3AdminSrvRegiCustomerChangeInputRequest.class,
            WEB3AdminSrvRegiAccountChangeInputHandler.class,
            "mainAccountChangeInputRequest");

        //サービス利用管理者顧客データダウンロード 用ハンドラーの登録
        regHandler(
            WEB3SrvRegiAppPlugin.class,
            WEB3AdminSrvRegiDownloadRequest.class,
            WEB3AdminSrvRegiAccountDataDownloadHandler.class,
            "acountDownload");

        //サービス利用管理者顧客データUL状況照会 用ハンドラーの登録
        regHandler(
            WEB3SrvRegiAppPlugin.class,
            WEB3AdminSrvRegiUploadStateRequest.class,
            WEB3AdminSrvRegiAccountDataUlStateInquiryHandler.class,
            "accountDataUploadStateInqueryRequest");

        //サービス利用管理者顧客データアップロード 用ハンドラーの登録
        regHandler(
            WEB3SrvRegiAppPlugin.class,
            WEB3AdminSrvRegiUploadCancelRequest.class,
            WEB3AdminSrvRegiAccountDataUploadHandler.class,
            "accountUploadDiscontinuation");

        //サービス利用管理者顧客データアップロード 用ハンドラーの登録
        regHandler(
            WEB3SrvRegiAppPlugin.class,
            WEB3AdminSrvRegiUploadConfirmRequest.class,
            WEB3AdminSrvRegiAccountDataUploadHandler.class,
            "accountUploadConfirm");

        //サービス利用管理者顧客データアップロード 用ハンドラーの登録
        regHandler(
            WEB3SrvRegiAppPlugin.class,
            WEB3AdminSrvRegiUploadCompleteRequest.class,
            WEB3AdminSrvRegiAccountDataUploadHandler.class,
            "accountUpload");

        //サービス利用管理者顧客データアップロード 用ハンドラーの登録
        regHandler(
            WEB3SrvRegiAppPlugin.class,
            WEB3AdminSrvRegiUploadInputRequest.class,
            WEB3AdminSrvRegiAccountDataUploadHandler.class,
            "accountUploadScreenIndication");

        //サービス利用管理者顧客一覧変更照会 用ハンドラーの登録
        regHandler(
            WEB3SrvRegiAppPlugin.class,
            WEB3AdminSrvRegiCustomerReferenceRequest.class,
            WEB3AdminSrvRegiAccountListChangeInquiryHandler.class,
            "searchAccountAppliSrv");

        //サービス利用管理者顧客登録 用ハンドラーの登録
        regHandler(
            WEB3SrvRegiAppPlugin.class,
            WEB3AdminSrvRegiCustomerRegistConfirmRequest.class,
            WEB3AdminSrvRegiAccountRegistHandler.class,
            "confirmAccountRegist");

        //サービス利用管理者顧客登録 用ハンドラーの登録
        regHandler(
            WEB3SrvRegiAppPlugin.class,
            WEB3AdminSrvRegiCustomerRegistCompleteRequest.class,
            WEB3AdminSrvRegiAccountRegistHandler.class,
            "completeAccountRegist");

        //サービス利用管理者サービス履歴情報 用ハンドラーの登録
        regHandler(
            WEB3SrvRegiAppPlugin.class,
            WEB3AdminSrvRegiServiceHistoryRequest.class,
            WEB3AdminSrvRegiServiceActionInfoHandler.class,
            "searchSrvAction");

        //サービス利用管理者サービス落札額更新 用ハンドラーの登録
        regHandler(
            WEB3SrvRegiAppPlugin.class,
            WEB3AdminSrvRegiSuccBidConfirmRequest.class,
            WEB3AdminSrvRegiServiceBidPriceUpdateHandler.class,
            "confirmBidPriceUpdate");

        //サービス利用管理者サービス落札額更新 用ハンドラーの登録
        regHandler(
            WEB3SrvRegiAppPlugin.class,
            WEB3AdminSrvRegiSuccBidCompleteRequest.class,
            WEB3AdminSrvRegiServiceBidPriceUpdateHandler.class,
            "completeBidPriceUpdate");

        //サービス利用管理者サービス落札額更新入力 用ハンドラーの登録
        regHandler(
            WEB3SrvRegiAppPlugin.class,
            WEB3AdminSrvRegiSuccBidInputRequest.class,
            WEB3AdminSrvRegiServiceBidPriceUpdateInputHandler.class,
            "bidPriceUpdateInputRequest");

        //サービス利用管理者サービス変更 用ハンドラーの登録
        regHandler(
            WEB3SrvRegiAppPlugin.class,
            WEB3AdminSrvRegiServiceChangeConfirmRequest.class,
            WEB3AdminSrvRegiServiceChangeHandler.class,
            "confirmSrvChange");

        //サービス利用管理者サービス変更 用ハンドラーの登録
        regHandler(
            WEB3SrvRegiAppPlugin.class,
            WEB3AdminSrvRegiServiceChangeCompleteRequest.class,
            WEB3AdminSrvRegiServiceChangeHandler.class,
            "completeSrvChange");

        //サービス利用管理者サービス変更入力 用ハンドラーの登録
        regHandler(
            WEB3SrvRegiAppPlugin.class,
            WEB3AdminSrvRegiServiceChangeInputRequest.class,
            WEB3AdminSrvRegiServiceChangeInputHandler.class,
            "srvChangeInputRequest");

        //サービス利用管理者サービス詳細 用ハンドラーの登録
        regHandler(
            WEB3SrvRegiAppPlugin.class,
            WEB3AdminSrvRegiServiceDetailsRequest.class,
            WEB3AdminSrvRegiServiceDetailHandler.class,
            "serviceDetailRequest");

        //サービス利用管理者サービス登録 用ハンドラーの登録
        regHandler(
            WEB3SrvRegiAppPlugin.class,
            WEB3AdminSrvRegiServiceRegistCompleteRequest.class,
            WEB3AdminSrvRegiServiceRegiHandler.class,
            "completeServiceRegi");

        //サービス利用管理者サービス登録 用ハンドラーの登録
        regHandler(
            WEB3SrvRegiAppPlugin.class,
            WEB3AdminSrvRegiServiceRegistConfirmRequest.class,
            WEB3AdminSrvRegiServiceRegiHandler.class,
            "confirmServiceRegi");

        //サービス利用管理者サービス一覧 用ハンドラーの登録
        regHandler(WEB3SrvRegiAppPlugin.class, WEB3AdminSrvRegiServiceReferenceRequest.class,
            WEB3AdminSrvRegiSrvListHandler.class, "searchSrv");

        //サービス利用申込入力 用ハンドラーの登録
        regHandler(
            WEB3SrvRegiAppPlugin.class,
            WEB3SrvRegiApplyInputRequest.class,
            WEB3SrvRegiApplicationInputHandler.class,
            "useAppliInputRequest");
        
        //サービス利用申込入力 用ハンドラーの登録
        regHandler(
            WEB3SrvRegiAppPlugin.class,
            WEB3SrvRegiConsentRequest.class,
            WEB3SrvRegiApplicationInputHandler.class,
            "docScreenRequest");
        
        //サービス利用取消 用ハンドラーの登録
        regHandler(WEB3SrvRegiAppPlugin.class, WEB3SrvRegiCancelCompleteRequest.class,
            WEB3SrvRegiCancelHandler.class, "completeCancel");

        //サービス利用取消 用ハンドラーの登録
        regHandler(WEB3SrvRegiAppPlugin.class, WEB3SrvRegiCancelConfirmRequest.class,
            WEB3SrvRegiCancelHandler.class, "confirmCancel");

        //サービス利用サービス一覧照会 用ハンドラーの登録
        regHandler(WEB3SrvRegiAppPlugin.class, WEB3SrvRegiReferenceRequest.class,
            WEB3SrvRegiServiceListInquiryHandler.class, "searchService");

        //サービス利用サービス起動 用ハンドラーの登録
        regHandler(WEB3SrvRegiAppPlugin.class, WEB3SrvRegiExecRequest.class,
            WEB3SrvRegiServiceStartHandler.class, "srvStartRequest");

        //サービス利用申込 用ハンドラーの登録
        regHandler(
            WEB3SrvRegiAppPlugin.class,
            WEB3SrvRegiApplyCompleteRequest.class,
            WEB3SrvRegiServiceUseApplicationHandler.class,
            "completeUseAppli");

        //サービス利用申込 用ハンドラーの登録
        regHandler(
            WEB3SrvRegiAppPlugin.class,
            WEB3SrvRegiApplyConfirmRequest.class,
            WEB3SrvRegiServiceUseApplicationHandler.class,
            "confirmUseAppli");

        //サービス利用管理者外部連携ID一覧照会ﾊﾝﾄﾞﾗ
        regHandler(
            WEB3SrvRegiAppPlugin.class,
            WEB3AdminSrvRegiOtherOrgIdListReferenceRequest.class,
            WEB3AdminSrvRegiOtherOrgIdListHandler.class,
            "otherOrgIdListReference");

        //サービス利用管理者外部連携ID一覧照会ﾊﾝﾄﾞﾗ
        regHandler(
            WEB3SrvRegiAppPlugin.class,
            WEB3AdminSrvRegiOtherOrgIdListSearchRequest.class,
            WEB3AdminSrvRegiOtherOrgIdListHandler.class,
            "otherOrgIdListSearch");

        //サービス利用管理者外部連携ID照会ｱｯﾌﾟﾛｰﾄﾞﾊﾝﾄﾞﾗ
        regHandler(
            WEB3SrvRegiAppPlugin.class,
            WEB3AdminSrvRegiOtherOrgIdUploadInputRequest.class,
            WEB3AdminSrvRegiOtherOrgIdUploadHandler.class,
            "otherOrgIdUploadScreenDisplay");

        //サービス利用管理者外部連携ID照会ｱｯﾌﾟﾛｰﾄﾞﾊﾝﾄﾞﾗ
        regHandler(
            WEB3SrvRegiAppPlugin.class,
            WEB3AdminSrvRegiOtherOrgIdUploadConfirmRequest.class,
            WEB3AdminSrvRegiOtherOrgIdUploadHandler.class,
            "otherOrgIdUploadConfirm");

        //サービス利用管理者外部連携ID照会ｱｯﾌﾟﾛｰﾄﾞﾊﾝﾄﾞﾗ
        regHandler(
            WEB3SrvRegiAppPlugin.class,
            WEB3AdminSrvRegiOtherOrgIdUploadCompleteRequest.class,
            WEB3AdminSrvRegiOtherOrgIdUploadHandler.class,
            "otherOrgIdUpload");

        //サービス利用管理者外部連携ID照会ｱｯﾌﾟﾛｰﾄﾞﾊﾝﾄﾞﾗ
        regHandler(
            WEB3SrvRegiAppPlugin.class,
            WEB3AdminSrvRegiOtherOrgIdUploadCancelRequest.class,
            WEB3AdminSrvRegiOtherOrgIdUploadHandler.class,
            "otherOrgIdUploadCancel");

        //サービス利用管理者外部連携ID照会ﾀﾞｳﾝﾛｰﾄﾞﾊﾝﾄﾞﾗ
        regHandler(
            WEB3SrvRegiAppPlugin.class,
            WEB3AdminSrvRegiOtherOrgIdDownloadRequest.class,
            WEB3AdminSrvRegiOtherOrgIdDownloadHandler.class,
            "otherOrgIdDownload");

        //サービス利用債券連携ハンドラ
        regHandler(
            WEB3SrvRegiAppPlugin.class,
            WEB3SrvRegiStreamRequest.class,
            WEB3SrvRegiStreamHandler.class,
            "srvRegiStreamRequest");

        log.exiting(STR_METHOD_NAME);
    }
}@
