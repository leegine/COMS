head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.14.03.28.41;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	4584d7d7e626866;
filename	WEB3AccOpenAppPlugin.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : Webbroker3-口座開設 プラグイン(WEB3AccOpenAppPlugin.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2004/12/24 郭英 (中訊) 新規作成
Revesion History : 2006/06/15 黄建 (中訊) 仕様変更 モデル052
Revesion History : 2006/08/18 李俊 (中訊) 仕様変更 モデル090
Revesion History : 2006/11/28 何文敏 (中訊) 仕様変更 モデル113
Revesion History : 2007/05/29 柴双紅 (中訊) 仕様変更 モデルNo.123
Revesion History : 2007/11/27 謝旋 (中訊) 仕様変更 モデルNo.147,148
Revesion History : 2008/12/16 劉仁和 (中訊) 仕様変更 モデルNo.158,159,160,161
Revesion History : 2009/08/10 孟亞南 (中訊) 仕様変更 モデル No.166
Revesion History : 2009/08/13 武波 (中訊) 仕様変更 モデル No.164,No175
Revesion History : 2009/08/19 趙林鵬 (中訊) 仕様変更 モデル No.180,No181
*/
package webbroker3.accountopen;

import com.fitechlabs.xtrade.kernel.boot.KernelPlugin;
import com.fitechlabs.xtrade.kernel.boot.Plugin;
import com.fitechlabs.xtrade.kernel.boot.Services;
import com.fitechlabs.xtrade.kernel.interceptor.TransactionalInterceptor;

import webbroker3.accountopen.data.WEB3AccOpenMasterDatabaseExtensions;
import webbroker3.accountopen.data.WEB3AccOpenSessionDatabaseExtensions;
import webbroker3.accountopen.handler.WEB3AccOpenFinInstitutionSearchHandler;
import webbroker3.accountopen.handler.WEB3AccOpenMailAddressRegistHandler;
import webbroker3.accountopen.handler.WEB3AccOpenRegistHandler;
import webbroker3.accountopen.handler.WEB3AccOpenVoucherRegAcceptHandler;
import webbroker3.accountopen.handler.WEB3AdminAccOpenApplyDataDelHandler;
import webbroker3.accountopen.handler.WEB3AdminAccOpenApplyULHandler;
import webbroker3.accountopen.handler.WEB3AdminAccOpenCompleteMailSendHandler;
import webbroker3.accountopen.handler.WEB3AdminAccOpenDataTransferHandler;
import webbroker3.accountopen.handler.WEB3AdminAccOpenJudgeHandler;
import webbroker3.accountopen.handler.WEB3AdminAccOpenRegistSearchHandler;
import webbroker3.accountopen.handler.WEB3AdminAccOpenStateInquiryHandler;
import webbroker3.accountopen.message.WEB3AccOpenApplyCompleteRequest;
import webbroker3.accountopen.message.WEB3AccOpenApplyCompleteResponse;
import webbroker3.accountopen.message.WEB3AccOpenApplyConfirmRequest;
import webbroker3.accountopen.message.WEB3AccOpenApplyConfirmResponse;
import webbroker3.accountopen.message.WEB3AccOpenApplyInputRequest;
import webbroker3.accountopen.message.WEB3AccOpenApplyInputResponse;
import webbroker3.accountopen.message.WEB3AccOpenChangeRequest;
import webbroker3.accountopen.message.WEB3AccOpenChangeResponse;
import webbroker3.accountopen.message.WEB3AccOpenFinancialBranchSearchRequest;
import webbroker3.accountopen.message.WEB3AccOpenFinancialBranchSearchResponse;
import webbroker3.accountopen.message.WEB3AccOpenFinancialSearchRequest;
import webbroker3.accountopen.message.WEB3AccOpenFinancialSearchResponse;
import webbroker3.accountopen.message.WEB3AccOpenMailAddrRegCompleteRequest;
import webbroker3.accountopen.message.WEB3AccOpenMailAddrRegCompleteResponse;
import webbroker3.accountopen.message.WEB3AccOpenMailAddrRegInputRequest;
import webbroker3.accountopen.message.WEB3AccOpenMailAddrRegInputResponse;
import webbroker3.accountopen.message.WEB3AccOpenVoucherRegAcceptRequest;
import webbroker3.accountopen.message.WEB3AccOpenVoucherRegAcceptResponse;
import webbroker3.accountopen.message.WEB3AdminAccOpenApplyDataDelCmpRequest;
import webbroker3.accountopen.message.WEB3AdminAccOpenApplyDataDelCmpResponse;
import webbroker3.accountopen.message.WEB3AdminAccOpenApplyDataDelCnfRequest;
import webbroker3.accountopen.message.WEB3AdminAccOpenApplyDataDelCnfResponse;
import webbroker3.accountopen.message.WEB3AdminAccOpenApplyDataDelSearchRequest;
import webbroker3.accountopen.message.WEB3AdminAccOpenApplyDataDelSearchResponse;
import webbroker3.accountopen.message.WEB3AdminAccOpenApplyDownloadRequest;
import webbroker3.accountopen.message.WEB3AdminAccOpenApplyDownloadResponse;
import webbroker3.accountopen.message.WEB3AdminAccOpenApplySearchInputRequest;
import webbroker3.accountopen.message.WEB3AdminAccOpenApplySearchInputResponse;
import webbroker3.accountopen.message.WEB3AdminAccOpenApplyUpdateCompleteRequest;
import webbroker3.accountopen.message.WEB3AdminAccOpenApplyUpdateCompleteResponse;
import webbroker3.accountopen.message.WEB3AdminAccOpenApplyUpdateConfirmRequest;
import webbroker3.accountopen.message.WEB3AdminAccOpenApplyUpdateConfirmResponse;
import webbroker3.accountopen.message.WEB3AdminAccOpenApplyUploadCancelRequest;
import webbroker3.accountopen.message.WEB3AdminAccOpenApplyUploadCancelResponse;
import webbroker3.accountopen.message.WEB3AdminAccOpenApplyUploadCompleteRequest;
import webbroker3.accountopen.message.WEB3AdminAccOpenApplyUploadCompleteResponse;
import webbroker3.accountopen.message.WEB3AdminAccOpenApplyUploadConfirmRequest;
import webbroker3.accountopen.message.WEB3AdminAccOpenApplyUploadConfirmResponse;
import webbroker3.accountopen.message.WEB3AdminAccOpenApplyUploadInputRequest;
import webbroker3.accountopen.message.WEB3AdminAccOpenApplyUploadInputResponse;
import webbroker3.accountopen.message.WEB3AdminAccOpenCompleteMailSendListRequest;
import webbroker3.accountopen.message.WEB3AdminAccOpenCompleteMailSendListResponse;
import webbroker3.accountopen.message.WEB3AdminAccOpenCompleteMailSendRequest;
import webbroker3.accountopen.message.WEB3AdminAccOpenCompleteMailSendResponse;
import webbroker3.accountopen.message.WEB3AdminAccOpenDataTransferCompleteRequest;
import webbroker3.accountopen.message.WEB3AdminAccOpenDataTransferCompleteResponse;
import webbroker3.accountopen.message.WEB3AdminAccOpenDataTransferInputRequest;
import webbroker3.accountopen.message.WEB3AdminAccOpenDataTransferInputResponse;
import webbroker3.accountopen.message.WEB3AdminAccOpenInspectConsentCompleteRequest;
import webbroker3.accountopen.message.WEB3AdminAccOpenInspectConsentCompleteResponse;
import webbroker3.accountopen.message.WEB3AdminAccOpenInspectConsentConfirmRequest;
import webbroker3.accountopen.message.WEB3AdminAccOpenInspectConsentConfirmResponse;
import webbroker3.accountopen.message.WEB3AdminAccOpenInspectDenyCompleteRequest;
import webbroker3.accountopen.message.WEB3AdminAccOpenInspectDenyCompleteResponse;
import webbroker3.accountopen.message.WEB3AdminAccOpenInspectDenyConfirmRequest;
import webbroker3.accountopen.message.WEB3AdminAccOpenInspectDenyConfirmResponse;
import webbroker3.accountopen.message.WEB3AdminAccOpenInspectListRequest;
import webbroker3.accountopen.message.WEB3AdminAccOpenInspectListResponse;
import webbroker3.accountopen.message.WEB3AdminAccOpenInspectListSearchRequest;
import webbroker3.accountopen.message.WEB3AdminAccOpenInspectListSearchResponse;
import webbroker3.accountopen.message.WEB3AdminAccOpenStateInquiryDetailRequest;
import webbroker3.accountopen.message.WEB3AdminAccOpenStateInquiryDetailResponse;
import webbroker3.accountopen.message.WEB3AdminAccOpenStateInquiryInputRequest;
import webbroker3.accountopen.message.WEB3AdminAccOpenStateInquiryInputResponse;
import webbroker3.accountopen.message.WEB3AdminAccOpenStateInquiryListRequest;
import webbroker3.accountopen.message.WEB3AdminAccOpenStateInquiryListResponse;
import webbroker3.accountopen.message.WEB3AdminAccOpenVoucherCancelCompleteRequest;
import webbroker3.accountopen.message.WEB3AdminAccOpenVoucherCancelCompleteResponse;
import webbroker3.accountopen.message.WEB3AdminAccOpenVoucherCancelConfirmRequest;
import webbroker3.accountopen.message.WEB3AdminAccOpenVoucherCancelConfirmResponse;
import webbroker3.accountopen.message.WEB3AdminAccOpenVoucherMakeCompleteRequest;
import webbroker3.accountopen.message.WEB3AdminAccOpenVoucherMakeCompleteResponse;
import webbroker3.accountopen.message.WEB3AdminAccOpenVoucherMakeConfirmRequest;
import webbroker3.accountopen.message.WEB3AdminAccOpenVoucherMakeConfirmResponse;
import webbroker3.accountopen.service.delegate.WEB3AccOpenAccountCodeService;
import webbroker3.accountopen.service.delegate.WEB3AccOpenFinInstitutionSearchService;
import webbroker3.accountopen.service.delegate.WEB3AccOpenInfoCreatedService;
import webbroker3.accountopen.service.delegate.WEB3AccOpenInformAcceptUnitService;
import webbroker3.accountopen.service.delegate.WEB3AccOpenMailAddressRegistService;
import webbroker3.accountopen.service.delegate.WEB3AccOpenRealUnitService;
import webbroker3.accountopen.service.delegate.WEB3AccOpenRegistService;
import webbroker3.accountopen.service.delegate.WEB3AccOpenRequestNumberService;
import webbroker3.accountopen.service.delegate.WEB3AccOpenVoucherCreatedService;
import webbroker3.accountopen.service.delegate.WEB3AccOpenVoucherRegAcceptService;
import webbroker3.accountopen.service.delegate.WEB3AccOpenVoucherRegAcceptUnitService;
import webbroker3.accountopen.service.delegate.WEB3AdminAccOpenApplyDataDelService;
import webbroker3.accountopen.service.delegate.WEB3AdminAccOpenApplyULService;
import webbroker3.accountopen.service.delegate.WEB3AdminAccOpenCompleteMailSendService;
import webbroker3.accountopen.service.delegate.WEB3AdminAccOpenCompleteMailSendUnitService;
import webbroker3.accountopen.service.delegate.WEB3AdminAccOpenDataTransferService;
import webbroker3.accountopen.service.delegate.WEB3AdminAccOpenDataTransferUnitService;
import webbroker3.accountopen.service.delegate.WEB3AdminAccOpenJudgeService;
import webbroker3.accountopen.service.delegate.WEB3AdminAccOpenRegistSearchService;
import webbroker3.accountopen.service.delegate.WEB3AdminAccOpenStateInquiryService;
import webbroker3.accountopen.service.delegate.stdimpls.WEB3AccOpenAccountCodeServiceImpl;
import webbroker3.accountopen.service.delegate.stdimpls.WEB3AccOpenFinInstitutionSearchServiceImpl;
import webbroker3.accountopen.service.delegate.stdimpls.WEB3AccOpenInfoCreatedServiceImpl;
import webbroker3.accountopen.service.delegate.stdimpls.WEB3AccOpenInformAcceptUnitServiceImpl;
import webbroker3.accountopen.service.delegate.stdimpls.WEB3AccOpenMailAddressRegistServiceImpl;
import webbroker3.accountopen.service.delegate.stdimpls.WEB3AccOpenRealUnitServiceImpl;
import webbroker3.accountopen.service.delegate.stdimpls.WEB3AccOpenRegistServiceImpl;
import webbroker3.accountopen.service.delegate.stdimpls.WEB3AccOpenRequestNumberServiceImpl;
import webbroker3.accountopen.service.delegate.stdimpls.WEB3AccOpenVoucherCreatedServiceImpl;
import webbroker3.accountopen.service.delegate.stdimpls.WEB3AccOpenVoucherRegAcceptServiceImpl;
import webbroker3.accountopen.service.delegate.stdimpls.WEB3AccOpenVoucherRegAcceptUnitServiceImpl;
import webbroker3.accountopen.service.delegate.stdimpls.WEB3AdminAccOpenApplyDataDelServiceImpl;
import webbroker3.accountopen.service.delegate.stdimpls.WEB3AdminAccOpenApplyULServiceImpl;
import webbroker3.accountopen.service.delegate.stdimpls.WEB3AdminAccOpenCompleteMailSendServiceImpl;
import webbroker3.accountopen.service.delegate.stdimpls.WEB3AdminAccOpenCompleteMailSendUnitServiceImpl;
import webbroker3.accountopen.service.delegate.stdimpls.WEB3AdminAccOpenDataTransferServiceImpl;
import webbroker3.accountopen.service.delegate.stdimpls.WEB3AdminAccOpenDataTransferUnitServiceImpl;
import webbroker3.accountopen.service.delegate.stdimpls.WEB3AdminAccOpenJudgeServiceImpl;
import webbroker3.accountopen.service.delegate.stdimpls.WEB3AdminAccOpenRegistSearchServiceImpl;
import webbroker3.accountopen.service.delegate.stdimpls.WEB3AdminAccOpenStateInquiryServiceImpl;
import webbroker3.common.WEB3LogSysTimeInterceptor;
import webbroker3.mqgateway.WEB3MQGatewayInterceptor;
import webbroker3.util.WEB3LogUtility;

/**
 * Webbroker3-口座開設 プラグインクラス。
 *
 * @@author 郭英
 * @@version 1.0
 */
public final class WEB3AccOpenAppPlugin extends Plugin
{
    /**
     * ログユーティリティ。
     */
    private static WEB3LogUtility log = WEB3LogUtility.getInstance(WEB3AccOpenAppPlugin.class);

    /**
     * コンストラクタ。
     */
    public WEB3AccOpenAppPlugin()
    {
        String STR_METHOD_NAME = " WEB3AccOpenAppPlugin()";
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

        plug(WEB3AccOpenAppPlugin.class);

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
        WEB3AccOpenMasterDatabaseExtensions.plug();
        WEB3AccOpenSessionDatabaseExtensions.plug();

        // Service の登録処理 ----------------------
        //口座開設伝票作成サービス
        Services.registerService(WEB3AccOpenVoucherCreatedService.class,
            new WEB3AccOpenVoucherCreatedServiceImpl());

        //管理者口座開設完了メール送信サービス
        Services.registerService(
            WEB3AdminAccOpenCompleteMailSendService.class,
            new WEB3AdminAccOpenCompleteMailSendServiceImpl());

        //口座開設完了メール送信UnitService
        Services.registerService(
            WEB3AdminAccOpenCompleteMailSendUnitService.class,
            new WEB3AdminAccOpenCompleteMailSendUnitServiceImpl());

        //管理者口座開設状況問合せサービス
        Services.registerService(
            WEB3AdminAccOpenStateInquiryService.class,
            new WEB3AdminAccOpenStateInquiryServiceImpl());

        //管理者口座開設申込検索サービス
        Services.registerService(
            WEB3AdminAccOpenRegistSearchService.class,
            new WEB3AdminAccOpenRegistSearchServiceImpl());
        
        //管理者口座開設審査サービス 
        Services.registerService(
            WEB3AdminAccOpenJudgeService.class,
            new WEB3AdminAccOpenJudgeServiceImpl());        

        //口座開設金融機@関検索サービス
        Services.registerService(
            WEB3AccOpenFinInstitutionSearchService.class,
            new WEB3AccOpenFinInstitutionSearchServiceImpl());

        //口座開設識別コード採番サービス
        Services.registerService(WEB3AccOpenRequestNumberService.class,
            new WEB3AccOpenRequestNumberServiceImpl());

        //口座開設情報作成サービス
        Services.registerService(WEB3AccOpenInfoCreatedService.class,
            new WEB3AccOpenInfoCreatedServiceImpl());

        //口座開設申込サービス
        Services.registerService(WEB3AccOpenRegistService.class,
            new WEB3AccOpenRegistServiceImpl());

        //口座開設伝票登録受付UnitService
        Services.registerService(
            WEB3AccOpenVoucherRegAcceptUnitService.class,
            new WEB3AccOpenVoucherRegAcceptUnitServiceImpl());

        //口座開設伝票登録受付サービス
        Services.registerService(
            WEB3AccOpenVoucherRegAcceptService.class,
            new WEB3AccOpenVoucherRegAcceptServiceImpl());
        
        //口座開設顧客コード採番サービス
        Services.registerService(
            WEB3AccOpenAccountCodeService.class,
            new WEB3AccOpenAccountCodeServiceImpl());
        
        // 即日口座開設UnitService
        Services.registerService(
            WEB3AccOpenRealUnitService.class,
            new WEB3AccOpenRealUnitServiceImpl());

        //各種連絡受付UnitService
        Services.registerService(
            WEB3AccOpenInformAcceptUnitService.class,
            new WEB3AccOpenInformAcceptUnitServiceImpl());

        //管理者口座開設申込ULサービス
        Services.registerService(
            WEB3AdminAccOpenApplyULService.class,
            new WEB3AdminAccOpenApplyULServiceImpl());

        //管理者口座開設資料請求データ削除サービス
        Services.registerService(
            WEB3AdminAccOpenApplyDataDelService.class,
            new WEB3AdminAccOpenApplyDataDelServiceImpl());

        //口座開設メールアドレス登録サービス
        Services.registerService(
            WEB3AccOpenMailAddressRegistService.class,
            new WEB3AccOpenMailAddressRegistServiceImpl());

        //管理者口座開設データ移管サービス
        Services.registerService(
            WEB3AdminAccOpenDataTransferService.class,
            new WEB3AdminAccOpenDataTransferServiceImpl());

        //管理者口座開設データ移管UnitService
        Services.registerService(
            WEB3AdminAccOpenDataTransferUnitService.class,
            new WEB3AdminAccOpenDataTransferUnitServiceImpl());

        //Service の Interceptor 設定処理 ----------------------
        //自動トランザクション設定
        //口座開設伝票作成サービス
        Services.addInterceptor(WEB3AccOpenVoucherCreatedService.class,
            new TransactionalInterceptor(TransactionalInterceptor.TX_JOIN_EXISTING));

        //管理者口座開設完了メール送信サービス
        Services.addInterceptor(
            WEB3AdminAccOpenCompleteMailSendService.class,
            new TransactionalInterceptor(TransactionalInterceptor.TX_JOIN_EXISTING));

        //口座開設完了メール送信UnitService
        Services.addInterceptor(
            WEB3AdminAccOpenCompleteMailSendUnitService.class,
            new TransactionalInterceptor(TransactionalInterceptor.TX_CREATE_NEW));

        //管理者口座開設状況問合せサービス
        Services.addInterceptor(
            WEB3AdminAccOpenStateInquiryService.class,
            new TransactionalInterceptor(TransactionalInterceptor.TX_JOIN_EXISTING));

        //管理者口座開設申込検索サービス
        Services.addInterceptor(
            WEB3AdminAccOpenRegistSearchService.class,
            new TransactionalInterceptor(TransactionalInterceptor.TX_JOIN_EXISTING));
        
        //管理者口座開設審査サービス 
        Services.addInterceptor(
            WEB3AdminAccOpenJudgeService.class,
            new TransactionalInterceptor(TransactionalInterceptor.TX_JOIN_EXISTING));

        //口座開設金融機@関検索サービス
        Services.addInterceptor(
            WEB3AccOpenFinInstitutionSearchService.class,
            new TransactionalInterceptor(TransactionalInterceptor.TX_JOIN_EXISTING));

        //口座開設識別コード採番サービス
        Services.addInterceptor(WEB3AccOpenRequestNumberService.class,
            new TransactionalInterceptor(TransactionalInterceptor.TX_CREATE_NEW));

        //口座開設情報作成サービス
        Services.addInterceptor(WEB3AccOpenInfoCreatedService.class,
            new TransactionalInterceptor(TransactionalInterceptor.TX_JOIN_EXISTING));

        //口座開設申込サービス
        Services.addInterceptor(WEB3AccOpenRegistService.class,
            new TransactionalInterceptor(TransactionalInterceptor.TX_JOIN_EXISTING));

        //口座開設伝票登録受付UnitService
        Services.addInterceptor(
            WEB3AccOpenVoucherRegAcceptUnitService.class,
            new TransactionalInterceptor(TransactionalInterceptor.TX_CREATE_NEW));

        //口座開設伝票登録受付サービス
        Services.addInterceptor(
            WEB3AccOpenVoucherRegAcceptService.class,
            new TransactionalInterceptor(TransactionalInterceptor.TX_JOIN_EXISTING));
        
        //口座開設顧客コード採番サービス
        Services.addInterceptor(
            WEB3AccOpenAccountCodeService.class,
            new TransactionalInterceptor(TransactionalInterceptor.TX_CREATE_NEW));
        
        // 即日口座開設UnitService
        Services.addInterceptor(
            WEB3AccOpenRealUnitService.class,
            new TransactionalInterceptor(TransactionalInterceptor.TX_CREATE_NEW));

        //各種連絡受付UnitService
        Services.addInterceptor(
            WEB3AccOpenInformAcceptUnitService.class,
            new TransactionalInterceptor(TransactionalInterceptor.TX_CREATE_NEW));

        //管理者口座開設申込ULサービス
        Services.addInterceptor(
            WEB3AdminAccOpenApplyULService.class,
            new TransactionalInterceptor(TransactionalInterceptor.TX_CREATE_NEW));

        //管理者口座開設資料請求データ削除サービス
        Services.addInterceptor(
            WEB3AdminAccOpenApplyDataDelService.class,
            new TransactionalInterceptor(TransactionalInterceptor.TX_JOIN_EXISTING));
        
        //口座開設メールアドレス登録サービス
        Services.addInterceptor(
            WEB3AccOpenMailAddressRegistService.class,
            new TransactionalInterceptor(TransactionalInterceptor.TX_JOIN_EXISTING));
        
        //管理者口座開設データ移管サービス
        Services.addInterceptor(
            WEB3AdminAccOpenDataTransferService.class,
            new TransactionalInterceptor(TransactionalInterceptor.TX_CREATE_NEW));

        //管理者口座開設データ移管UnitService
        Services.addInterceptor(
            WEB3AdminAccOpenDataTransferUnitService.class,
            new TransactionalInterceptor(TransactionalInterceptor.TX_CREATE_NEW));

        // Service.execute()呼び出し前後に  ----------------------
        // 処理開始時刻と処理終了時刻をログ出力するように設定する
        //口座開設伝票作成サービス
        Services.addInterceptor(WEB3AccOpenVoucherCreatedService.class,
            new WEB3LogSysTimeInterceptor());

        //管理者口座開設完了メール送信サービス
        Services.addInterceptor(
            WEB3AdminAccOpenCompleteMailSendService.class,
            new WEB3LogSysTimeInterceptor());

        //口座開設完了メール送信UnitService
        Services.addInterceptor(
            WEB3AdminAccOpenCompleteMailSendUnitService.class,
            new WEB3LogSysTimeInterceptor());

        //管理者口座開設状況問合せサービス
        Services.addInterceptor(
            WEB3AdminAccOpenStateInquiryService.class,
            new WEB3LogSysTimeInterceptor());

        //管理者口座開設申込検索サービス
        Services.addInterceptor(
            WEB3AdminAccOpenRegistSearchService.class,
            new WEB3LogSysTimeInterceptor());
        
        //管理者口座開設審査サービス 
        Services.addInterceptor(
            WEB3AdminAccOpenJudgeService.class,
            new WEB3LogSysTimeInterceptor());

        //口座開設金融機@関検索サービス
        Services.addInterceptor(
            WEB3AccOpenFinInstitutionSearchService.class,
            new WEB3LogSysTimeInterceptor());

        //口座開設識別コード採番サービス
        Services.addInterceptor(WEB3AccOpenRequestNumberService.class,
            new WEB3LogSysTimeInterceptor());

        //口座開設情報作成サービス
        Services.addInterceptor(WEB3AccOpenInfoCreatedService.class,
            new WEB3LogSysTimeInterceptor());

        //口座開設申込サービス
        Services.addInterceptor(WEB3AccOpenRegistService.class,
            new WEB3LogSysTimeInterceptor());

        //口座開設伝票登録受付UnitService
        Services.addInterceptor(
            WEB3AccOpenVoucherRegAcceptUnitService.class,
            new WEB3LogSysTimeInterceptor());

        //口座開設伝票登録受付サービス
        Services.addInterceptor(
            WEB3AccOpenVoucherRegAcceptService.class,
            new WEB3LogSysTimeInterceptor());
        
        //口座開設顧客コード採番サービス
        Services.addInterceptor(
            WEB3AccOpenAccountCodeService.class,
            new WEB3LogSysTimeInterceptor());
        
        //管理者口座開設申込ULサービス
        Services.addInterceptor(
            WEB3AdminAccOpenApplyULService.class,
            new WEB3LogSysTimeInterceptor());

        //管理者口座開設資料請求データ削除サービス
        Services.addInterceptor(
            WEB3AdminAccOpenApplyDataDelService.class,
            new WEB3LogSysTimeInterceptor());
        
        //口座開設メールアドレス登録サービス
        Services.addInterceptor(
            WEB3AccOpenMailAddressRegistService.class,
            new WEB3LogSysTimeInterceptor());

        //管理者口座開設データ移管サービス
        Services.addInterceptor(
            WEB3AdminAccOpenDataTransferService.class,
            new WEB3LogSysTimeInterceptor());

        //管理者口座開設データ移管UnitService
        Services.addInterceptor(
            WEB3AdminAccOpenDataTransferUnitService.class,
            new WEB3LogSysTimeInterceptor());

        //Service に ServiceInterceptor を設定する ----------------------
        //管理者口座開設完了メール送信サービス
        Services.addInterceptor(
            WEB3AdminAccOpenCompleteMailSendService.class,
            new WEB3AccOpenServiceInterceptor());

        //口座開設完了メール送信UnitService
        Services.addInterceptor(
            WEB3AdminAccOpenCompleteMailSendUnitService.class,
            new WEB3AccOpenServiceInterceptor());
        
        //管理者口座開設審査サービス
        Services.addInterceptor(
            WEB3AdminAccOpenJudgeService.class,
            new WEB3AccOpenServiceInterceptor());
        Services.addInterceptor(
            WEB3AdminAccOpenJudgeService.class,
            new WEB3MQGatewayInterceptor());

        //管理者口座開設状況問合せサービス
        //（サービスインタセプタ，MQGatewayインタセプタ）
        Services.addInterceptor(
            WEB3AdminAccOpenStateInquiryService.class,
            new WEB3AccOpenServiceInterceptor());
        Services.addInterceptor(
            WEB3AdminAccOpenStateInquiryService.class,
            new WEB3MQGatewayInterceptor());

        //管理者口座開設申込検索サービス
        Services.addInterceptor(
            WEB3AdminAccOpenRegistSearchService.class,
            new WEB3AccOpenServiceInterceptor());

        //口座開設金融機@関検索サービス
        //口座開設申込サービス
        Services.addInterceptor(WEB3AccOpenRegistService.class,
            new WEB3AccOpenInfoCreatedServiceInterceptor());

        //口座開設伝票登録受付UnitService
        Services.addInterceptor(
            WEB3AccOpenVoucherRegAcceptUnitService.class,
            new WEB3AccOpenVoucherRegAcceptUnitServiceInterceptor());
        
        // 即日口座開設UnitService
        Services.addInterceptor(
            WEB3AccOpenRealUnitService.class,
            new WEB3AccOpenRealUnitServiceInterceptor());

        //各種連絡受付UnitService
        Services.addInterceptor(
            WEB3AccOpenInformAcceptUnitService.class,
            new WEB3AccOpenInformAcceptUnitServiceInterceptor());

        //管理者口座開設申込ULサービス
        Services.addInterceptor(
            WEB3AdminAccOpenApplyULService.class,
            new WEB3AdminAccOpenApplyULServiceInterceptor());
        
        //口座開設メールアドレス登録サービス
        Services.addInterceptor(
            WEB3AccOpenMailAddressRegistService.class,
            new WEB3AccOpenInfoCreatedServiceInterceptor());

        //管理者口座開設データ移管UnitService
        Services.addInterceptor(
            WEB3AdminAccOpenDataTransferUnitService.class,
            new WEB3AdminAccOpenDataTransferUnitServiceInterceptor());

        //管理者口座開設データ移管UnitService
        Services.addInterceptor(
            WEB3AdminAccOpenDataTransferUnitService.class,
            new WEB3MQGatewayInterceptor());

        // Message の登録処理 ----------------------
        //管理者口座開設完了メール送信リクエスト
        regClass(WEB3AdminAccOpenCompleteMailSendRequest.class);
        //管理者口座開設完了メール送信レスポンス
        regClass(WEB3AdminAccOpenCompleteMailSendResponse.class);

        //管理者口座開設完了メール送信一覧リクエスト
        regClass(WEB3AdminAccOpenCompleteMailSendListRequest.class);
        //管理者口座開設完了メール送信一覧レスポンス
        regClass(WEB3AdminAccOpenCompleteMailSendListResponse.class);

        //管理者口座開設状況問合せ一覧リクエスト
        regClass(WEB3AdminAccOpenStateInquiryListRequest.class);
        //管理者口座開設状況問合せ一覧レスポンス
        regClass(WEB3AdminAccOpenStateInquiryListResponse.class);

        //管理者口座開設状況問合せ詳細リクエスト
        regClass(WEB3AdminAccOpenStateInquiryDetailRequest.class);
        //管理者口座開設状況問合せ詳細レスポンス
        regClass(WEB3AdminAccOpenStateInquiryDetailResponse.class);

        //管理者口座開設状況問合せ入力リクエスト
        regClass(WEB3AdminAccOpenStateInquiryInputRequest.class);
        //管理者口座開設状況問合せ入力レスポンス
        regClass(WEB3AdminAccOpenStateInquiryInputResponse.class);

        //管理者口座開設申込ダウンロードリクエスト
        regClass(WEB3AdminAccOpenApplyDownloadRequest.class);
        //管理者口座開設申込ダウンロードレスポンス
        regClass(WEB3AdminAccOpenApplyDownloadResponse.class);

        //管理者口座開設申込検索入力リクエスト
        regClass(WEB3AdminAccOpenApplySearchInputRequest.class);
        //管理者口座開設申込検索入力レスポンス
        regClass(WEB3AdminAccOpenApplySearchInputResponse.class);

        //管理者口座開設申込更新確認リクエスト
        regClass(WEB3AdminAccOpenApplyUpdateConfirmRequest.class);
        //管理者口座開設申込更新確認レスポンス
        regClass(WEB3AdminAccOpenApplyUpdateConfirmResponse.class);

        //管理者口座開設申込更新完了リクエスト
        regClass(WEB3AdminAccOpenApplyUpdateCompleteRequest.class);
        //管理者口座開設申込更新完了レスポンス
        regClass(WEB3AdminAccOpenApplyUpdateCompleteResponse.class);

        //管理者口座開設伝票作成確認リクエスト
        regClass(WEB3AdminAccOpenVoucherMakeConfirmRequest.class);
        //管理者口座開設伝票作成確認レスポンス
        regClass(WEB3AdminAccOpenVoucherMakeConfirmResponse.class);

        //管理者口座開設伝票作成完了リクエスト
        regClass(WEB3AdminAccOpenVoucherMakeCompleteRequest.class);
        //管理者口座開設伝票作成完了レスポンス
        regClass(WEB3AdminAccOpenVoucherMakeCompleteResponse.class);

        //管理者口座開設伝票取消確認リクエスト
        regClass(WEB3AdminAccOpenVoucherCancelConfirmRequest.class);
        //管理者口座開設伝票取消確認レスポンス
        regClass(WEB3AdminAccOpenVoucherCancelConfirmResponse.class);

        //管理者口座開設伝票取消完了リクエスト
        regClass(WEB3AdminAccOpenVoucherCancelCompleteRequest.class);
        //管理者口座開設伝票取消完了レスポンス
        regClass(WEB3AdminAccOpenVoucherCancelCompleteResponse.class);
        
        //管理者口座開設審査一覧リクエスト
        regClass(WEB3AdminAccOpenInspectListRequest.class);
        //管理者口座開設審査一覧レスポンス
        regClass(WEB3AdminAccOpenInspectListResponse.class);
        //管理者口座開設審査対象一覧検索リクエスト
        regClass(WEB3AdminAccOpenInspectListSearchRequest.class);
        //管理者口座開設審査対象一覧検索レスポンス
        regClass(WEB3AdminAccOpenInspectListSearchResponse.class);
		//管理者口座開設審査承認確認リクエスト
        regClass(WEB3AdminAccOpenInspectConsentConfirmRequest.class);
		//管理者口座開設審査承認確認レスポンス
        regClass(WEB3AdminAccOpenInspectConsentConfirmResponse.class);
		//管理者口座開設審査承認完了リクエスト
        regClass(WEB3AdminAccOpenInspectConsentCompleteRequest.class);
		//管理者口座開設審査承認完了レスポンス
        regClass(WEB3AdminAccOpenInspectConsentCompleteResponse.class);
		//管理者口座開設審査否認確認リクエスト
        regClass(WEB3AdminAccOpenInspectDenyConfirmRequest.class);
		//管理者口座開設審査否認確認レスポンス
        regClass(WEB3AdminAccOpenInspectDenyConfirmResponse.class);
		//管理者口座開設審査否認完了リクエスト
        regClass(WEB3AdminAccOpenInspectDenyCompleteRequest.class);
		//管理者口座開設審査否認完了レスポンス
        regClass(WEB3AdminAccOpenInspectDenyCompleteResponse.class);

        //口座開設金融機@関検索リクエスト
        regClass(WEB3AccOpenFinancialSearchRequest.class);
        //口座開設金融機@関検索レスポンス
        regClass(WEB3AccOpenFinancialSearchResponse.class);

        //口座開設支店検索リクエスト
        regClass(WEB3AccOpenFinancialBranchSearchRequest.class);
        //口座開設支店検索レスポンス
        regClass(WEB3AccOpenFinancialBranchSearchResponse.class);

        //口座開設申込確認リクエスト
        regClass(WEB3AccOpenApplyConfirmRequest.class);
        //口座開設申込確認レスポンス
        regClass(WEB3AccOpenApplyConfirmResponse.class);

        //口座開設申込完了リクエスト
        regClass(WEB3AccOpenApplyCompleteRequest.class);
        //口座開設申込完了レスポンス
        regClass(WEB3AccOpenApplyCompleteResponse.class);

        //口座開設申込入力リクエスト
        regClass(WEB3AccOpenApplyInputRequest.class);
        //口座開設申込入力レスポンス
        regClass(WEB3AccOpenApplyInputResponse.class);

        //口座開設伝票登録受付リクエスト
        regClass(WEB3AccOpenVoucherRegAcceptRequest.class);
        //口座開設伝票登録受付レスポンス
        regClass(WEB3AccOpenVoucherRegAcceptResponse.class);

        //管理者口座開設申込UL入力リクエスト
        regClass(WEB3AdminAccOpenApplyUploadInputRequest.class);
        //管理者口座開設申込UL入力レスポンス
        regClass(WEB3AdminAccOpenApplyUploadInputResponse.class);

        //管理者口座開設申込UL確認リクエスト
        regClass(WEB3AdminAccOpenApplyUploadConfirmRequest.class);
        //管理者口座開設申込UL確認レスポンス
        regClass(WEB3AdminAccOpenApplyUploadConfirmResponse.class);

        //管理者口座開設申込UL完了リクエスト
        regClass(WEB3AdminAccOpenApplyUploadCompleteRequest.class);
        //管理者口座開設申込UL完了レスポンス
        regClass(WEB3AdminAccOpenApplyUploadCompleteResponse.class);

        //管理者口座開設申込UL中止リクエスト
        regClass(WEB3AdminAccOpenApplyUploadCancelRequest.class);
        //管理者口座開設申込UL中止レスポンス
        regClass(WEB3AdminAccOpenApplyUploadCancelResponse.class);

        //管理者口座開設資料請求データ削除検索リクエスト
        regClass(WEB3AdminAccOpenApplyDataDelSearchRequest.class);
        //管理者口座開設資料請求データ削除検索レスポンス
        regClass(WEB3AdminAccOpenApplyDataDelSearchResponse.class);

        //管理者口座開設資料請求データ削除確認リクエスト
        regClass(WEB3AdminAccOpenApplyDataDelCnfRequest.class);
        //管理者口座開設資料請求データ削除確認レスポンス
        regClass(WEB3AdminAccOpenApplyDataDelCnfResponse.class);

        //管理者口座開設資料請求データ削除完了リクエスト
        regClass(WEB3AdminAccOpenApplyDataDelCmpRequest.class);
        //管理者口座開設資料請求データ削除完了レスポンス
        regClass(WEB3AdminAccOpenApplyDataDelCmpResponse.class);
        
        //口座開設メールアドレス登録完了リクエスト
        regClass(WEB3AccOpenMailAddrRegCompleteRequest.class);
        //口座開設メールアドレス登録完了レスポンス
        regClass(WEB3AccOpenMailAddrRegCompleteResponse.class);
        
        //口座開設メールアドレス登録入力リクエスト
        regClass(WEB3AccOpenMailAddrRegInputRequest.class);
        //口座開設メールアドレス登録入力レスポンス
        regClass(WEB3AccOpenMailAddrRegInputResponse.class);

        //管理者口座開設切替リクエスト
        regClass(WEB3AccOpenChangeRequest.class);
        //管理者口座開設切替レスポンス
        regClass(WEB3AccOpenChangeResponse.class);

        //管理者口座開設データ移管入力リクエスト
        regClass(WEB3AdminAccOpenDataTransferInputRequest.class);

        //管理者口座開設データ移管入力レスポンス
        regClass(WEB3AdminAccOpenDataTransferInputResponse.class);

        //管理者口座開設データ移管完了リクエスト
        regClass(WEB3AdminAccOpenDataTransferCompleteRequest.class);

        //管理者口座開設データ移管完了レスポンス
        regClass(WEB3AdminAccOpenDataTransferCompleteResponse.class);

        //Handler の登録処理 ----------------------
        //管理者口座開設完了メール送信 用ハンドラーの登録
        regHandler(
            WEB3AccOpenAppPlugin.class,
            WEB3AdminAccOpenCompleteMailSendListRequest.class,
            WEB3AdminAccOpenCompleteMailSendHandler.class,
            "mailSendListDisplay");

        //管理者口座開設完了メール送信 用ハンドラーの登録
        regHandler(
            WEB3AccOpenAppPlugin.class,
            WEB3AdminAccOpenCompleteMailSendRequest.class,
            WEB3AdminAccOpenCompleteMailSendHandler.class,
            "mailSend");

        //管理者口座開設状況問合 用ハンドラーの登録
        regHandler(
            WEB3AccOpenAppPlugin.class,
            WEB3AdminAccOpenStateInquiryInputRequest.class,
            WEB3AdminAccOpenStateInquiryHandler.class,
            "inputScreenDisplay");

        //管理者口座開設状況問合 用ハンドラーの登録
        regHandler(
            WEB3AccOpenAppPlugin.class,
            WEB3AdminAccOpenStateInquiryListRequest.class,
            WEB3AdminAccOpenStateInquiryHandler.class,
            "accOpenStatusListDisplay");

        //管理者口座開設状況問合 用ハンドラーの登録
        regHandler(
            WEB3AccOpenAppPlugin.class,
            WEB3AdminAccOpenStateInquiryDetailRequest.class,
            WEB3AdminAccOpenStateInquiryHandler.class,
            "accOpenStatusDetailDisplay");

        //管理者口座開設状況問合 用ハンドラーの登録
        regHandler(
            WEB3AccOpenAppPlugin.class,
            WEB3AdminAccOpenApplyUpdateConfirmRequest.class,
            WEB3AdminAccOpenStateInquiryHandler.class,
            "registUpdatedConfirm");

        //管理者口座開設状況問合 用ハンドラーの登録
        regHandler(
            WEB3AccOpenAppPlugin.class,
            WEB3AdminAccOpenApplyUpdateCompleteRequest.class,
            WEB3AdminAccOpenStateInquiryHandler.class,
            "registUpdatedComplete");

        //管理者口座開設状況問合 用ハンドラーの登録
        regHandler(
            WEB3AccOpenAppPlugin.class,
            WEB3AdminAccOpenVoucherMakeConfirmRequest.class,
            WEB3AdminAccOpenStateInquiryHandler.class,
            "voucherCreatedConfirm");

        //管理者口座開設状況問合 用ハンドラーの登録
        regHandler(
            WEB3AccOpenAppPlugin.class,
            WEB3AdminAccOpenVoucherMakeCompleteRequest.class,
            WEB3AdminAccOpenStateInquiryHandler.class,
            "voucherCreatedComplete");

        //管理者口座開設状況問合 用ハンドラーの登録
        regHandler(
            WEB3AccOpenAppPlugin.class,
            WEB3AdminAccOpenVoucherCancelConfirmRequest.class,
            WEB3AdminAccOpenStateInquiryHandler.class,
            "voucherCanceledConfirm");

        //管理者口座開設状況問合 用ハンドラーの登録
        regHandler(
            WEB3AccOpenAppPlugin.class,
            WEB3AdminAccOpenVoucherCancelCompleteRequest.class,
            WEB3AdminAccOpenStateInquiryHandler.class,
            "voucherCanceledComplete");

        //管理者口座開設状況問合 用ハンドラーの登録
        regHandler(
            WEB3AccOpenAppPlugin.class,
            WEB3AccOpenChangeRequest.class,
            WEB3AdminAccOpenStateInquiryHandler.class,
            "change");

        //管理者口座開設申込検索 用ハンドラーの登録
        regHandler(
            WEB3AccOpenAppPlugin.class,
            WEB3AdminAccOpenApplySearchInputRequest.class,
            WEB3AdminAccOpenRegistSearchHandler.class,
            "inputScreenDisplay");

        //管理者口座開設申込検索 用ハンドラーの登録
        regHandler(
            WEB3AccOpenAppPlugin.class,
            WEB3AdminAccOpenApplyDownloadRequest.class,
            WEB3AdminAccOpenRegistSearchHandler.class,
            "accOpenRegistDownload");

        //口座開設金融機@関検索 用ハンドラーの登録
        regHandler(
            WEB3AccOpenAppPlugin.class,
            WEB3AccOpenFinancialSearchRequest.class,
            WEB3AccOpenFinInstitutionSearchHandler.class,
            "finInstitutionListDisplay");
        
        //管理者口座開設審査ハンドラ
        regHandler(
            WEB3AccOpenAppPlugin.class,
            WEB3AdminAccOpenInspectListSearchRequest.class,
            WEB3AdminAccOpenJudgeHandler.class,
            "inspectObjectListSearchScreen");
        regHandler(
            WEB3AccOpenAppPlugin.class,
            WEB3AdminAccOpenInspectListRequest.class,
            WEB3AdminAccOpenJudgeHandler.class,
            "screenList");
        regHandler(
            WEB3AccOpenAppPlugin.class,
            WEB3AdminAccOpenInspectConsentConfirmRequest.class,
            WEB3AdminAccOpenJudgeHandler.class,
            "consentConfirm");
        regHandler(
            WEB3AccOpenAppPlugin.class,
            WEB3AdminAccOpenInspectConsentCompleteRequest.class,
            WEB3AdminAccOpenJudgeHandler.class,
            "consentComplete");
        regHandler(
            WEB3AccOpenAppPlugin.class,
            WEB3AdminAccOpenInspectDenyConfirmRequest.class,
            WEB3AdminAccOpenJudgeHandler.class,
            "denyConfirm");
        regHandler(
            WEB3AccOpenAppPlugin.class,
            WEB3AdminAccOpenInspectDenyCompleteRequest.class,
            WEB3AdminAccOpenJudgeHandler.class,
            "denyComplete");

        //口座開設金融機@関検索 用ハンドラーの登録
        regHandler(
            WEB3AccOpenAppPlugin.class,
            WEB3AccOpenFinancialBranchSearchRequest.class,
            WEB3AccOpenFinInstitutionSearchHandler.class,
            "finBranchListDisplay");

        //口座開設申込 用ハンドラーの登録
        regHandler(
            WEB3AccOpenAppPlugin.class,
            WEB3AccOpenApplyInputRequest.class,
            WEB3AccOpenRegistHandler.class,
            "inputScreenDisplay");

        //口座開設申込 用ハンドラーの登録
        regHandler(
            WEB3AccOpenAppPlugin.class,
            WEB3AccOpenApplyConfirmRequest.class,
            WEB3AccOpenRegistHandler.class,
            "registConfirm");

        //口座開設申込 用ハンドラーの登録
        regHandler(
            WEB3AccOpenAppPlugin.class,
            WEB3AccOpenApplyCompleteRequest.class,
            WEB3AccOpenRegistHandler.class,
            "registComplete");

        //口座開設伝票登録受付 用ハンドラーの登録
        regHandler(
            WEB3AccOpenAppPlugin.class,
            WEB3AccOpenVoucherRegAcceptRequest.class,
            WEB3AccOpenVoucherRegAcceptHandler.class,
            "accOpenVoucherRegAccept");

        //管理者口座開設申込UL入力 用ハンドラーの登録
        regHandler(
            WEB3AccOpenAppPlugin.class,
            WEB3AdminAccOpenApplyUploadInputRequest.class,
            WEB3AdminAccOpenApplyULHandler.class,
            "uploadScreenDisplay");

        //管理者口座開設申込UL確認 用ハンドラーの登録
        regHandler(
            WEB3AccOpenAppPlugin.class,
            WEB3AdminAccOpenApplyUploadConfirmRequest.class,
            WEB3AdminAccOpenApplyULHandler.class,
            "uploadFileConfirm");

        //管理者口座開設申込UL完了 用ハンドラーの登録
        regHandler(
            WEB3AccOpenAppPlugin.class,
            WEB3AdminAccOpenApplyUploadCompleteRequest.class,
            WEB3AdminAccOpenApplyULHandler.class,
            "accOpenApplyUpload");

        //管理者口座開設申込UL中止 用ハンドラーの登録
        regHandler(
            WEB3AccOpenAppPlugin.class,
            WEB3AdminAccOpenApplyUploadCancelRequest.class,
            WEB3AdminAccOpenApplyULHandler.class,
            "uploadCancel");

        //口座開設資料請求データ削除検索画面表示 用ハンドラーの登録
        regHandler(
            WEB3AccOpenAppPlugin.class,
            WEB3AdminAccOpenApplyDataDelSearchRequest.class,
            WEB3AdminAccOpenApplyDataDelHandler.class,
            "displaySearchScreen");

        //口座開設資料請求データ削除確認処理 用ハンドラーの登録
        regHandler(
            WEB3AccOpenAppPlugin.class,
            WEB3AdminAccOpenApplyDataDelCnfRequest.class,
            WEB3AdminAccOpenApplyDataDelHandler.class,
            "deleteConfirm");

        //口座開設資料請求データ削除完了処理 用ハンドラーの登録
        regHandler(
            WEB3AccOpenAppPlugin.class,
            WEB3AdminAccOpenApplyDataDelCmpRequest.class,
            WEB3AdminAccOpenApplyDataDelHandler.class,
            "deleteComplete");
        
        //口座開設メールアドレス登録ハンドラ
        regHandler(
            WEB3AccOpenAppPlugin.class,
            WEB3AccOpenMailAddrRegInputRequest.class,
            WEB3AccOpenMailAddressRegistHandler.class,
            "inputScreenDisplay");
        
        //口座開設メールアドレス登録ハンドラ
        regHandler(
            WEB3AccOpenAppPlugin.class,
            WEB3AccOpenMailAddrRegCompleteRequest.class,
            WEB3AccOpenMailAddressRegistHandler.class,
            "registComplete");

        //管理者口座開設データ移管ハンドラ
        regHandler(
            WEB3AccOpenAppPlugin.class,
            WEB3AdminAccOpenDataTransferInputRequest.class,
            WEB3AdminAccOpenDataTransferHandler.class,
            "getInputScreen");

        //管理者口座開設データ移管ハンドラ
        regHandler(
            WEB3AccOpenAppPlugin.class,
            WEB3AdminAccOpenDataTransferCompleteRequest.class,
            WEB3AdminAccOpenDataTransferHandler.class,
            "submitDataTransfer");

        Services.addInterceptor(
            WEB3AccOpenVoucherRegAcceptUnitService.class,
            new WEB3AccOpenDescendRacCtxInterceptor());

        log.exiting(STR_METHOD_NAME);
    }
}
@
