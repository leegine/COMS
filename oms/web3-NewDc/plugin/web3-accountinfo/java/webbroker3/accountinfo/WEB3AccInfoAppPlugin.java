head	1.2;
access;
symbols;
locks; strict;
comment	@// @;


1.2
date	2011.03.24.08.51.11;	author che-jin;	state Exp;
branches;
next	1.1;
deltatype	text;
kopt	kv;
permissions	666;
commitid	6544d8b05f516f9;
filename	WEB3AccInfoAppPlugin.java;

1.1
date	2011.03.10.02.24.08;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	8644d782f8a12fc;
filename	WEB3AccInfoAppPlugin.java;


desc
@@


1.2
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : Webbroker3-AccInfo プラグイン(WEB3AccInfoAppPlugin.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2004/11/16 李海波 (中訊) 新規作成
Revesion History : 2010/11/15 張騰宇 (中訊) モデル278
*/

package webbroker3.accountinfo;

import com.fitechlabs.xtrade.kernel.boot.KernelPlugin;
import com.fitechlabs.xtrade.kernel.boot.Plugin;
import com.fitechlabs.xtrade.kernel.boot.Services;
import com.fitechlabs.xtrade.kernel.interceptor.TransactionalInterceptor;

import webbroker3.accountinfo.data.WEB3AccInfoAccountDatabaseExtensions;
import webbroker3.accountinfo.data.WEB3AccInfoMasterDatabaseExtensions;
import webbroker3.accountinfo.data.WEB3AccInfoSessionDatabaseExtensions;
import webbroker3.accountinfo.handler.WEB3AccInfoBaseInfoReferenceHandler;
import webbroker3.accountinfo.handler.WEB3AccInfoCommonInputHandler;
import webbroker3.accountinfo.handler.WEB3AccInfoElecDeliveryRegisterChangeHandler;
import webbroker3.accountinfo.handler.WEB3AccInfoEquityCommissionCourseRegistHandler;
import webbroker3.accountinfo.handler.WEB3AccInfoExecMailDistributionChangeHandler;
import webbroker3.accountinfo.handler.WEB3AccInfoLockRegistReleaseAcceptHandler;
import webbroker3.accountinfo.handler.WEB3AccInfoMailAddressChangeHandler;
import webbroker3.accountinfo.handler.WEB3AccInfoMailDistributionChangeHandler;
import webbroker3.accountinfo.handler.WEB3AccInfoMobileOfficeRegistHandler;
import webbroker3.accountinfo.handler.WEB3AccInfoPasswordChangeHandler;
import webbroker3.accountinfo.handler.WEB3AdminAccInfoAccEstablishSearchHandler;
import webbroker3.accountinfo.handler.WEB3AdminAccInfoAccountBaseInfoInquiryHandler;
import webbroker3.accountinfo.handler.WEB3AdminAccInfoCampaignAccOpenChangeHandler;
import webbroker3.accountinfo.handler.WEB3AdminAccInfoCampaignAccOpenListHandler;
import webbroker3.accountinfo.handler.WEB3AdminAccInfoCampaignIndiviChangeHandler;
import webbroker3.accountinfo.handler.WEB3AdminAccInfoCampaignIndiviListHandler;
import webbroker3.accountinfo.handler.WEB3AdminAccInfoCampaignRegistAccListHandler;
import webbroker3.accountinfo.handler.WEB3AdminAccInfoCommissionRegistAccountDownloadHandler;
import webbroker3.accountinfo.handler.WEB3AdminAccInfoCommissionRegistAccountInquiryHandler;
import webbroker3.accountinfo.handler.WEB3AdminAccInfoCommissionRegistAccountListInquiryHandler;
import webbroker3.accountinfo.handler.WEB3AdminAccInfoExclusiveTransferAccountChangeHandler;
import webbroker3.accountinfo.handler.WEB3AdminAccInfoExclusiveTransferAccountUploadHandler;
import webbroker3.accountinfo.handler.WEB3AdminAccInfoHyperBoxCommissionChangeDownloadHandler;
import webbroker3.accountinfo.handler.WEB3AdminAccInfoInsiderInfoChangeHandler;
import webbroker3.accountinfo.handler.WEB3AdminAccInfoInsiderInfoListHandler;
import webbroker3.accountinfo.handler.WEB3AdminAccInfoLockAccountListHandler;
import webbroker3.accountinfo.handler.WEB3AdminAccInfoLoginErrorResetHandler;
import webbroker3.accountinfo.handler.WEB3AdminAccInfoLoginPasswordChangeAccountDownloadHandler;
import webbroker3.accountinfo.handler.WEB3AdminAccInfoLoginPasswordResetHandler;
import webbroker3.accountinfo.handler.WEB3AdminAccInfoMailAddressChangeAccountDownloadHandler;
import webbroker3.accountinfo.handler.WEB3AdminAccInfoMailAddressChangeHandler;
import webbroker3.accountinfo.handler.WEB3AdminAccInfoMailAddressDownloadHandler;
import webbroker3.accountinfo.handler.WEB3AdminAccInfoMailAddressUploadHandler;
import webbroker3.accountinfo.handler.WEB3AdminAccInfoMailDistributionHandler;
import webbroker3.accountinfo.handler.WEB3AdminAccInfoMobileOfficeChangeHandler;
import webbroker3.accountinfo.handler.WEB3AdminAccInfoMobileOfficeRegistAccountInquiryHandler;
import webbroker3.accountinfo.handler.WEB3AdminAccInfoPasswordChangeAccountDownloadHandler;
import webbroker3.accountinfo.handler.WEB3AdminAccInfoPasswordResetHandler;
import webbroker3.accountinfo.handler.WEB3AdminAccInfoStopStateChangeHandler;
import webbroker3.accountinfo.message.*;
import webbroker3.accountinfo.service.delegate.WEB3AccInfoAccountBaseInfoCreatedService;
import webbroker3.accountinfo.service.delegate.WEB3AccInfoBaseInfoReferenceService;
import webbroker3.accountinfo.service.delegate.WEB3AccInfoCommissionCourseRegistInfoCreatedService;
import webbroker3.accountinfo.service.delegate.WEB3AccInfoCommonInputService;
import webbroker3.accountinfo.service.delegate.WEB3AccInfoElecDeliveryRegisterChangeService;
import webbroker3.accountinfo.service.delegate.WEB3AccInfoEquityCommissionCourseRegistService;
import webbroker3.accountinfo.service.delegate.WEB3AccInfoExecMailDistributionChangeService;
import webbroker3.accountinfo.service.delegate.WEB3AccInfoLockRegistReleaseAcceptService;
import webbroker3.accountinfo.service.delegate.WEB3AccInfoLockRegistReleaseAcceptUnitService;
import webbroker3.accountinfo.service.delegate.WEB3AccInfoMailAddressChangeService;
import webbroker3.accountinfo.service.delegate.WEB3AccInfoMailDistributionChangeService;
import webbroker3.accountinfo.service.delegate.WEB3AccInfoMobileOfficeRegistService;
import webbroker3.accountinfo.service.delegate.WEB3AccInfoPasswordChangeService;
import webbroker3.accountinfo.service.delegate.WEB3AdminAccInfoAccEstablishSearchService;
import webbroker3.accountinfo.service.delegate.WEB3AdminAccInfoAccountBaseInfoInquiryService;
import webbroker3.accountinfo.service.delegate.WEB3AdminAccInfoCampaignAccOpenChangeService;
import webbroker3.accountinfo.service.delegate.WEB3AdminAccInfoCampaignAccOpenListService;
import webbroker3.accountinfo.service.delegate.WEB3AdminAccInfoCampaignIndiviChangeService;
import webbroker3.accountinfo.service.delegate.WEB3AdminAccInfoCampaignIndiviListService;
import webbroker3.accountinfo.service.delegate.WEB3AdminAccInfoCampaignRegistAccListService;
import webbroker3.accountinfo.service.delegate.WEB3AdminAccInfoCommissionRegistAccountDownloadService;
import webbroker3.accountinfo.service.delegate.WEB3AdminAccInfoCommissionRegistAccountInquiryService;
import webbroker3.accountinfo.service.delegate.WEB3AdminAccInfoCommissionRegistAccountListInquiryService;
import webbroker3.accountinfo.service.delegate.WEB3AdminAccInfoExclusiveTransferAccountChangeService;
import webbroker3.accountinfo.service.delegate.WEB3AdminAccInfoExclusiveTransferAccountUploadService;
import webbroker3.accountinfo.service.delegate.WEB3AdminAccInfoHyperBoxCommissionChangeDownloadService;
import webbroker3.accountinfo.service.delegate.WEB3AdminAccInfoInsiderInfoChangeService;
import webbroker3.accountinfo.service.delegate.WEB3AdminAccInfoInsiderInfoListService;
import webbroker3.accountinfo.service.delegate.WEB3AdminAccInfoLockAccountListService;
import webbroker3.accountinfo.service.delegate.WEB3AdminAccInfoLoginErrorResetService;
import webbroker3.accountinfo.service.delegate.WEB3AdminAccInfoLoginPasswordChangeAccountDownloadService;
import webbroker3.accountinfo.service.delegate.WEB3AdminAccInfoLoginPasswordResetService;
import webbroker3.accountinfo.service.delegate.WEB3AdminAccInfoMailAddressChangeAccountDownloadService;
import webbroker3.accountinfo.service.delegate.WEB3AdminAccInfoMailAddressChangeService;
import webbroker3.accountinfo.service.delegate.WEB3AdminAccInfoMailAddressDownloadService;
import webbroker3.accountinfo.service.delegate.WEB3AdminAccInfoMailAddressUploadService;
import webbroker3.accountinfo.service.delegate.WEB3AdminAccInfoMailDistributionService;
import webbroker3.accountinfo.service.delegate.WEB3AdminAccInfoMobileOfficeChangeService;
import webbroker3.accountinfo.service.delegate.WEB3AdminAccInfoMobileOfficeRegistAccountInquiryService;
import webbroker3.accountinfo.service.delegate.WEB3AdminAccInfoPasswordChangeAccountDownloadService;
import webbroker3.accountinfo.service.delegate.WEB3AdminAccInfoPasswordResetService;
import webbroker3.accountinfo.service.delegate.WEB3AdminAccInfoStopStateChangeService;
import webbroker3.accountinfo.service.delegate.stdimpls.WEB3AccInfoAccountBaseInfoCreatedServiceImpl;
import webbroker3.accountinfo.service.delegate.stdimpls.WEB3AccInfoBaseInfoReferenceServiceImpl;
import webbroker3.accountinfo.service.delegate.stdimpls.WEB3AccInfoCommissionCourseRegistInfoCreatedServiceImpl;
import webbroker3.accountinfo.service.delegate.stdimpls.WEB3AccInfoCommonInputServiceImpl;
import webbroker3.accountinfo.service.delegate.stdimpls.WEB3AccInfoElecDeliveryRegisterChangeServiceImpl;
import webbroker3.accountinfo.service.delegate.stdimpls.WEB3AccInfoEquityCommissionCourseRegistServiceImpl;
import webbroker3.accountinfo.service.delegate.stdimpls.WEB3AccInfoExecMailDistributionChangeServiceImpl;
import webbroker3.accountinfo.service.delegate.stdimpls.WEB3AccInfoLockRegistReleaseAcceptServiceImpl;
import webbroker3.accountinfo.service.delegate.stdimpls.WEB3AccInfoLockRegistReleaseAcceptUnitServiceImpl;
import webbroker3.accountinfo.service.delegate.stdimpls.WEB3AccInfoMailAddressChangeServiceImpl;
import webbroker3.accountinfo.service.delegate.stdimpls.WEB3AccInfoMailDistributionChangeServiceImpl;
import webbroker3.accountinfo.service.delegate.stdimpls.WEB3AccInfoMobileOfficeRegistServiceImpl;
import webbroker3.accountinfo.service.delegate.stdimpls.WEB3AccInfoPasswordChangeServiceImpl;
import webbroker3.accountinfo.service.delegate.stdimpls.WEB3AdminAccInfoAccEstablishSearchServiceImpl;
import webbroker3.accountinfo.service.delegate.stdimpls.WEB3AdminAccInfoAccountBaseInfoInquiryServiceImpl;
import webbroker3.accountinfo.service.delegate.stdimpls.WEB3AdminAccInfoCampaignAccOpenChangeServiceImpl;
import webbroker3.accountinfo.service.delegate.stdimpls.WEB3AdminAccInfoCampaignAccOpenListServiceImpl;
import webbroker3.accountinfo.service.delegate.stdimpls.WEB3AdminAccInfoCampaignIndiviChangeServiceImpl;
import webbroker3.accountinfo.service.delegate.stdimpls.WEB3AdminAccInfoCampaignIndiviListServiceImpl;
import webbroker3.accountinfo.service.delegate.stdimpls.WEB3AdminAccInfoCampaignRegistAccListServiceImpl;
import webbroker3.accountinfo.service.delegate.stdimpls.WEB3AdminAccInfoCommissionRegistAccountDownloadServiceImpl;
import webbroker3.accountinfo.service.delegate.stdimpls.WEB3AdminAccInfoCommissionRegistAccountInquiryServiceImpl;
import webbroker3.accountinfo.service.delegate.stdimpls.WEB3AdminAccInfoCommissionRegistAccountListInquiryServiceImpl;
import webbroker3.accountinfo.service.delegate.stdimpls.WEB3AdminAccInfoExclusiveTransferAccountChangeServiceImpl;
import webbroker3.accountinfo.service.delegate.stdimpls.WEB3AdminAccInfoExclusiveTransferAccountUploadServiceImpl;
import webbroker3.accountinfo.service.delegate.stdimpls.WEB3AdminAccInfoHyperBoxCommissionChangeDownloadServiceImpl;
import webbroker3.accountinfo.service.delegate.stdimpls.WEB3AdminAccInfoInsiderInfoChangeServiceImpl;
import webbroker3.accountinfo.service.delegate.stdimpls.WEB3AdminAccInfoInsiderInfoListServiceImpl;
import webbroker3.accountinfo.service.delegate.stdimpls.WEB3AdminAccInfoLockAccountListServiceImpl;
import webbroker3.accountinfo.service.delegate.stdimpls.WEB3AdminAccInfoLoginErrorResetServiceImpl;
import webbroker3.accountinfo.service.delegate.stdimpls.WEB3AdminAccInfoLoginPasswordChangeAccountDownloadServiceImpl;
import webbroker3.accountinfo.service.delegate.stdimpls.WEB3AdminAccInfoLoginPasswordResetServiceImpl;
import webbroker3.accountinfo.service.delegate.stdimpls.WEB3AdminAccInfoMailAddressChangeAccountDownloadServiceImpl;
import webbroker3.accountinfo.service.delegate.stdimpls.WEB3AdminAccInfoMailAddressChangeServiceImpl;
import webbroker3.accountinfo.service.delegate.stdimpls.WEB3AdminAccInfoMailAddressDownloadServiceImpl;
import webbroker3.accountinfo.service.delegate.stdimpls.WEB3AdminAccInfoMailAddressUploadServiceImpl;
import webbroker3.accountinfo.service.delegate.stdimpls.WEB3AdminAccInfoMailDistributionServiceImpl;
import webbroker3.accountinfo.service.delegate.stdimpls.WEB3AdminAccInfoMobileOfficeChangeServiceImpl;
import webbroker3.accountinfo.service.delegate.stdimpls.WEB3AdminAccInfoMobileOfficeRegistAccountInquiryServiceImpl;
import webbroker3.accountinfo.service.delegate.stdimpls.WEB3AdminAccInfoPasswordChangeAccountDownloadServiceImpl;
import webbroker3.accountinfo.service.delegate.stdimpls.WEB3AdminAccInfoPasswordResetServiceImpl;
import webbroker3.accountinfo.service.delegate.stdimpls.WEB3AdminAccInfoStopStateChangeServiceImpl;
import webbroker3.mqgateway.WEB3MQGatewayInterceptor;
import webbroker3.util.WEB3LogUtility;

/**
 * Webbroker3-AccInfo プラグインクラス。
 *                                                                
 * @@author 李海波
 * @@version 1.0
 */
public final class WEB3AccInfoAppPlugin extends Plugin
{
    /**
     * ログユーティリティ。
     */
    private static WEB3LogUtility log = WEB3LogUtility.getInstance(WEB3AccInfoAppPlugin.class);

    /**
     * コンストラクタ。
     */
    public WEB3AccInfoAppPlugin()
    {
        String STR_METHOD_NAME = " WEB3AccInfoAppPlugin()";
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

        plug(WEB3AccInfoAppPlugin.class);

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
        WEB3AccInfoMasterDatabaseExtensions.plug();
        WEB3AccInfoAccountDatabaseExtensions.plug();
        WEB3AccInfoSessionDatabaseExtensions.plug();

        // Service の登録処理 ----------------------
        
        //管理者お客まさ情報案内メール配信指示サービス
        //管理者お客様情報内部者情報一覧サービス
        Services.registerService(
            WEB3AdminAccInfoMailDistributionService.class, 
            new WEB3AdminAccInfoMailDistributionServiceImpl());
        
        //管理者お客様情報内部者情報一覧サービス
        Services.registerService(
            WEB3AdminAccInfoInsiderInfoListService.class, 
            new WEB3AdminAccInfoInsiderInfoListServiceImpl());
            
        //お客様情報委託手数料コース変更申込情報作成サービス
        Services.registerService(
            WEB3AccInfoCommissionCourseRegistInfoCreatedService.class, 
            new WEB3AccInfoCommissionCourseRegistInfoCreatedServiceImpl());

        //お客様情報共通入力サービス
        Services.registerService(
            WEB3AccInfoCommonInputService.class, 
            new WEB3AccInfoCommonInputServiceImpl());
        
        //お客様情報顧客基本情報作成サービス
        Services.registerService(
            WEB3AccInfoAccountBaseInfoCreatedService.class, 
            new WEB3AccInfoAccountBaseInfoCreatedServiceImpl());

        //お客様情報メールアドレス変更サービス
        Services.registerService(
            WEB3AccInfoMailAddressChangeService.class, 
            new WEB3AccInfoMailAddressChangeServiceImpl());

        //お客様情報案内メール配信設定変更サービス
        Services.registerService(
            WEB3AccInfoMailDistributionChangeService.class, 
            new WEB3AccInfoMailDistributionChangeServiceImpl());

        //お客様情報株式委託手数料コース変更申込サービス
        Services.registerService(
            WEB3AccInfoEquityCommissionCourseRegistService.class, 
            new WEB3AccInfoEquityCommissionCourseRegistServiceImpl());

        //管理者お客様情報ﾊｲﾊﾟｰﾎﾞｯｸｽ手数料変更ﾀﾞｳﾝﾛｰﾄﾞｻｰﾋﾞｽ
        Services.registerService(
            WEB3AdminAccInfoHyperBoxCommissionChangeDownloadService.class, 
            new WEB3AdminAccInfoHyperBoxCommissionChangeDownloadServiceImpl());

        //管理者お客様情報ﾒｰﾙｱﾄﾞﾚｽ全件ﾀﾞｳﾝﾛｰﾄﾞｻｰﾋﾞｽ
        Services.registerService(
            WEB3AdminAccInfoMailAddressDownloadService.class, 
            new WEB3AdminAccInfoMailAddressDownloadServiceImpl());

        //管理者お客様情報メールアドレス変更サービス
        Services.registerService(
            WEB3AdminAccInfoMailAddressChangeService.class, 
            new WEB3AdminAccInfoMailAddressChangeServiceImpl());

        //管理者お客様情報ﾒｰﾙｱﾄﾞﾚｽ変更顧客ﾀﾞｳﾝﾛｰﾄﾞｻｰﾋﾞｽ
        Services.registerService(
            WEB3AdminAccInfoMailAddressChangeAccountDownloadService.class, 
            new WEB3AdminAccInfoMailAddressChangeAccountDownloadServiceImpl());

        //管理者お客様情報携帯番号・勤務先情報変更サービス
        Services.registerService(
            WEB3AdminAccInfoMobileOfficeChangeService.class, 
            new WEB3AdminAccInfoMobileOfficeChangeServiceImpl());

        //管理者お客様情報携帯番号・勤務先情報変更申込顧客問合せサービス
        Services.registerService(
            WEB3AdminAccInfoMobileOfficeRegistAccountInquiryService.class, 
            new WEB3AdminAccInfoMobileOfficeRegistAccountInquiryServiceImpl());

        //管理者お客様情報顧客基本情報問合せサービス
        Services.registerService(
            WEB3AdminAccInfoAccountBaseInfoInquiryService.class, 
            new WEB3AdminAccInfoAccountBaseInfoInquiryServiceImpl());

        //管理者お客様情報手数料変更申込顧客ﾀﾞｳﾝﾛｰﾄﾞｻｰﾋﾞｽ
        Services.registerService(
            WEB3AdminAccInfoCommissionRegistAccountDownloadService.class, 
            new WEB3AdminAccInfoCommissionRegistAccountDownloadServiceImpl());

        //管理者お客様情報手数料変更申込顧客一覧問合せｻｰﾋﾞｽ
        Services.registerService(
            WEB3AdminAccInfoCommissionRegistAccountListInquiryService.class, 
            new WEB3AdminAccInfoCommissionRegistAccountListInquiryServiceImpl());

        //管理者お客様情報手数料変更申込顧客問合せｻｰﾋﾞｽ
        Services.registerService(
            WEB3AdminAccInfoCommissionRegistAccountInquiryService.class, 
            new WEB3AdminAccInfoCommissionRegistAccountInquiryServiceImpl());

        //管理者お客様情報専用振込先口座ｱｯﾌﾟﾛｰﾄﾞｻｰﾋﾞｽ
        Services.registerService(
            WEB3AdminAccInfoExclusiveTransferAccountUploadService.class, 
            new WEB3AdminAccInfoExclusiveTransferAccountUploadServiceImpl());

        //管理者お客様情報専用振込先口座変更サービス
        Services.registerService(
            WEB3AdminAccInfoExclusiveTransferAccountChangeService.class, 
            new WEB3AdminAccInfoExclusiveTransferAccountChangeServiceImpl());

        //管理者お客様情報停止状況変更サービス
        Services.registerService(
            WEB3AdminAccInfoStopStateChangeService.class, 
            new WEB3AdminAccInfoStopStateChangeServiceImpl());

        //管理者お客様情報内部者情報変更サービス
        Services.registerService(
            WEB3AdminAccInfoInsiderInfoChangeService.class, 
            new WEB3AdminAccInfoInsiderInfoChangeServiceImpl());
            
        //お客様情報基本情報照会サービス
        Services.registerService(
            WEB3AccInfoBaseInfoReferenceService.class, 
            new WEB3AccInfoBaseInfoReferenceServiceImpl());
            
        //お客様情報携帯番号・勤務先情報変更申込サービス
        Services.registerService(
            WEB3AccInfoMobileOfficeRegistService.class, 
            new WEB3AccInfoMobileOfficeRegistServiceImpl());
            
        //お客様情報約定／未約定メール配信設定変更サービス
        Services.registerService(
            WEB3AccInfoExecMailDistributionChangeService.class, 
            new WEB3AccInfoExecMailDistributionChangeServiceImpl());
            
        //お客様情報暗証番号変更サービス
        Services.registerService(
            WEB3AccInfoPasswordChangeService.class, 
            new WEB3AccInfoPasswordChangeServiceImpl());
            
        //管理者お客様情報パスワードリセットサービス
        Services.registerService(
            WEB3AdminAccInfoLoginPasswordResetService.class, 
            new WEB3AdminAccInfoLoginPasswordResetServiceImpl());
            
        //管理者お客様情報ﾊﾟｽﾜｰﾄﾞ変更顧客ﾀﾞｳﾝﾛｰﾄﾞｻｰﾋﾞｽ
        Services.registerService(
            WEB3AdminAccInfoLoginPasswordChangeAccountDownloadService.class, 
            new WEB3AdminAccInfoLoginPasswordChangeAccountDownloadServiceImpl());
            
        //管理者お客様情報ログインエラーリセットサービス
        Services.registerService(
            WEB3AdminAccInfoLoginErrorResetService.class, 
            new WEB3AdminAccInfoLoginErrorResetServiceImpl());
            
        //管理者お客様情報暗証番号リセットサービス
        Services.registerService(
            WEB3AdminAccInfoPasswordResetService.class, 
            new WEB3AdminAccInfoPasswordResetServiceImpl());
            
        //管理者お客様情報暗証番号変更顧客ﾀﾞｳﾝﾛｰﾄﾞｻｰﾋﾞｽ
        Services.registerService(
            WEB3AdminAccInfoPasswordChangeAccountDownloadService.class, 
            new WEB3AdminAccInfoPasswordChangeAccountDownloadServiceImpl());

        //管理者お客様情報ロック顧客登録問合せ一覧サービス
        Services.registerService(
                WEB3AdminAccInfoLockAccountListService.class, 
                new WEB3AdminAccInfoLockAccountListServiceImpl());
        
        //ロック登録解除受付サービス
        Services.registerService(
                WEB3AccInfoLockRegistReleaseAcceptService.class, 
                new WEB3AccInfoLockRegistReleaseAcceptServiceImpl());
        
        //ロック登録解除受付１件サービス
        Services.registerService(
                WEB3AccInfoLockRegistReleaseAcceptUnitService.class, 
                new WEB3AccInfoLockRegistReleaseAcceptUnitServiceImpl());
        
        //管理者お客様情報メールアドレスアップロードサービス
        Services.registerService(
            WEB3AdminAccInfoMailAddressUploadService.class, 
            new WEB3AdminAccInfoMailAddressUploadServiceImpl());
        
        //新規口座開設検索サービス
        Services.registerService(
            WEB3AdminAccInfoAccEstablishSearchService.class, 
            new WEB3AdminAccInfoAccEstablishSearchServiceImpl());
        
        //個別顧客指定一覧サービス
        Services.registerService(
        		WEB3AdminAccInfoCampaignIndiviListService.class, 
            new WEB3AdminAccInfoCampaignIndiviListServiceImpl());
        
        //個別顧客指定変更サービス
        Services.registerService(
        		WEB3AdminAccInfoCampaignIndiviChangeService.class, 
            new WEB3AdminAccInfoCampaignIndiviChangeServiceImpl());
        
        //口座開設条件指定一覧サービス
        Services.registerService(
        		WEB3AdminAccInfoCampaignAccOpenListService.class, 
            new WEB3AdminAccInfoCampaignAccOpenListServiceImpl());
        
        //口座開設条件指定変更サービス
        Services.registerService(
        		WEB3AdminAccInfoCampaignAccOpenChangeService.class, 
            new WEB3AdminAccInfoCampaignAccOpenChangeServiceImpl());
        
        //口座開設条件指定変更サービス
        Services.registerService(
        		WEB3AdminAccInfoCampaignRegistAccListService.class, 
            new WEB3AdminAccInfoCampaignRegistAccListServiceImpl());
        
        //電子交付サービス登録・変更サービス
        Services.registerService(
            WEB3AccInfoElecDeliveryRegisterChangeService.class, 
            new WEB3AccInfoElecDeliveryRegisterChangeServiceImpl());
        
        // Service の Interceptor 設定処理 ----------------------
        // 自動トランザクション設定

        //お客様情報委託手数料コース変更申込情報作成サービス
        Services.addInterceptor(
            WEB3AccInfoCommissionCourseRegistInfoCreatedService.class,
            new TransactionalInterceptor(TransactionalInterceptor.TX_JOIN_EXISTING));
        
        //管理者お客まさ情報案内メール配信指示サービス
        Services.addInterceptor(
            WEB3AdminAccInfoMailDistributionService.class, 
            new TransactionalInterceptor(TransactionalInterceptor.TX_JOIN_EXISTING));
        
        //管理者お客様情報内部者情報一覧サービス
        Services.addInterceptor(
            WEB3AdminAccInfoInsiderInfoListService.class, 
            new TransactionalInterceptor(TransactionalInterceptor.TX_JOIN_EXISTING));
        
        //お客様情報共通入力サービス
        Services.addInterceptor(
            WEB3AccInfoCommonInputService.class, 
            new TransactionalInterceptor(TransactionalInterceptor.TX_JOIN_EXISTING));

//        //お客様情報顧客基本情報作成サービス
//        Services.addInterceptor(
//            WEB3AccInfoAccountBaseInfoCreatedService.class,
//            new TransactionalInterceptor(TransactionalInterceptor.TX_JOIN_EXISTING));

        //お客様情報メールアドレス変更サービス
        Services.addInterceptor(
            WEB3AccInfoMailAddressChangeService.class, 
            new TransactionalInterceptor(TransactionalInterceptor.TX_JOIN_EXISTING));

        //お客様情報案内メール配信設定変更サービス
        Services.addInterceptor(
            WEB3AccInfoMailDistributionChangeService.class,
            new TransactionalInterceptor(TransactionalInterceptor.TX_JOIN_EXISTING));

        //お客様情報株式委託手数料コース変更申込サービス
        Services.addInterceptor(
            WEB3AccInfoEquityCommissionCourseRegistService.class, 
            new TransactionalInterceptor(TransactionalInterceptor.TX_JOIN_EXISTING));

        //管理者お客様情報ﾊｲﾊﾟｰﾎﾞｯｸｽ手数料変更ﾀﾞｳﾝﾛｰﾄﾞｻｰﾋﾞｽ
        Services.addInterceptor(
            WEB3AdminAccInfoHyperBoxCommissionChangeDownloadService.class, 
            new TransactionalInterceptor(TransactionalInterceptor.TX_JOIN_EXISTING));

        //管理者お客様情報ﾒｰﾙｱﾄﾞﾚｽ全件ﾀﾞｳﾝﾛｰﾄﾞｻｰﾋﾞｽ
        Services.addInterceptor(
            WEB3AdminAccInfoMailAddressDownloadService.class, 
            new TransactionalInterceptor(TransactionalInterceptor.TX_JOIN_EXISTING));

        //管理者お客様情報メールアドレス変更サービス
        Services.addInterceptor(
            WEB3AdminAccInfoMailAddressChangeService.class, 
            new TransactionalInterceptor(TransactionalInterceptor.TX_JOIN_EXISTING));

        //管理者お客様情報ﾒｰﾙｱﾄﾞﾚｽ変更顧客ﾀﾞｳﾝﾛｰﾄﾞｻｰﾋﾞｽ
        Services.addInterceptor(
            WEB3AdminAccInfoMailAddressChangeAccountDownloadService.class,
            new TransactionalInterceptor(TransactionalInterceptor.TX_JOIN_EXISTING));

        //管理者お客様情報携帯番号・勤務先情報変更サービス
        Services.addInterceptor(
            WEB3AdminAccInfoMobileOfficeChangeService.class, 
            new TransactionalInterceptor(TransactionalInterceptor.TX_JOIN_EXISTING));

        //管理者お客様情報携帯番号・勤務先情報変更申込顧客問合せサービス
        Services.addInterceptor(
            WEB3AdminAccInfoMobileOfficeRegistAccountInquiryService.class, 
            new TransactionalInterceptor(TransactionalInterceptor.TX_JOIN_EXISTING));

        //管理者お客様情報顧客基本情報問合せサービス
        Services.addInterceptor(
            WEB3AdminAccInfoAccountBaseInfoInquiryService.class, 
            new TransactionalInterceptor(TransactionalInterceptor.TX_JOIN_EXISTING));

        //管理者お客様情報手数料変更申込顧客ﾀﾞｳﾝﾛｰﾄﾞｻｰﾋﾞｽ
        Services.addInterceptor(
            WEB3AdminAccInfoCommissionRegistAccountDownloadService.class, 
            new TransactionalInterceptor(TransactionalInterceptor.TX_JOIN_EXISTING));

        //管理者お客様情報手数料変更申込顧客一覧問合せｻｰﾋﾞｽ
        Services.addInterceptor(
            WEB3AdminAccInfoCommissionRegistAccountListInquiryService.class, 
            new TransactionalInterceptor(TransactionalInterceptor.TX_JOIN_EXISTING));

        //管理者お客様情報手数料変更申込顧客問合せｻｰﾋﾞｽ
        Services.addInterceptor(
            WEB3AdminAccInfoCommissionRegistAccountInquiryService.class, 
            new TransactionalInterceptor(TransactionalInterceptor.TX_JOIN_EXISTING));

        //管理者お客様情報専用振込先口座ｱｯﾌﾟﾛｰﾄﾞｻｰﾋﾞｽ
        Services.addInterceptor(
            WEB3AdminAccInfoExclusiveTransferAccountUploadService.class, 
            new TransactionalInterceptor(TransactionalInterceptor.TX_JOIN_EXISTING));

        //管理者お客様情報専用振込先口座変更サービス
        Services.addInterceptor(
            WEB3AdminAccInfoExclusiveTransferAccountChangeService.class, 
            new TransactionalInterceptor(TransactionalInterceptor.TX_JOIN_EXISTING));

        //管理者お客様情報停止状況変更サービス
        Services.addInterceptor(
            WEB3AdminAccInfoStopStateChangeService.class, 
            new TransactionalInterceptor(TransactionalInterceptor.TX_JOIN_EXISTING));

        //管理者お客様情報内部者情報変更サービス
        Services.addInterceptor(
            WEB3AdminAccInfoInsiderInfoChangeService.class, 
            new TransactionalInterceptor(TransactionalInterceptor.TX_JOIN_EXISTING));
            
        //お客様情報基本情報照会サービス
        Services.addInterceptor(
            WEB3AccInfoBaseInfoReferenceService.class, 
            new TransactionalInterceptor(TransactionalInterceptor.TX_JOIN_EXISTING));
            
        //お客様情報携帯番号・勤務先情報変更申込サービス
        Services.addInterceptor(
            WEB3AccInfoMobileOfficeRegistService.class, 
            new TransactionalInterceptor(TransactionalInterceptor.TX_JOIN_EXISTING));
            
        //お客様情報約定／未約定メール配信設定変更サービス
        Services.addInterceptor(
            WEB3AccInfoExecMailDistributionChangeService.class, 
            new TransactionalInterceptor(TransactionalInterceptor.TX_JOIN_EXISTING));

        //お客様情報暗証番号変更サービス
        Services.addInterceptor(
            WEB3AccInfoPasswordChangeService.class, 
            new TransactionalInterceptor(TransactionalInterceptor.TX_JOIN_EXISTING));
            
        //管理者お客様情報パスワードリセットサービス
        Services.addInterceptor(
            WEB3AdminAccInfoLoginPasswordResetService.class, 
            new TransactionalInterceptor(TransactionalInterceptor.TX_JOIN_EXISTING));
            
        //管理者お客様情報ﾊﾟｽﾜｰﾄﾞ変更顧客ﾀﾞｳﾝﾛｰﾄﾞｻｰﾋﾞｽ
        Services.addInterceptor(
            WEB3AdminAccInfoLoginPasswordChangeAccountDownloadService.class, 
            new TransactionalInterceptor(TransactionalInterceptor.TX_JOIN_EXISTING));
            
        //管理者お客様情報ログインエラーリセットサービス
        Services.addInterceptor(
            WEB3AdminAccInfoLoginErrorResetService.class, 
            new TransactionalInterceptor(TransactionalInterceptor.TX_JOIN_EXISTING));
            
        //管理者お客様情報暗証番号リセットサービス
        Services.addInterceptor(
            WEB3AdminAccInfoPasswordResetService.class, 
            new TransactionalInterceptor(TransactionalInterceptor.TX_JOIN_EXISTING));
            
        //管理者お客様情報暗証番号変更顧客ﾀﾞｳﾝﾛｰﾄﾞｻｰﾋﾞｽ
        Services.addInterceptor(
            WEB3AdminAccInfoPasswordChangeAccountDownloadService.class, 
            new TransactionalInterceptor(TransactionalInterceptor.TX_JOIN_EXISTING));
        
        //管理者お客様情報ロック顧客登録問合せ一覧サービス 
        Services.addInterceptor(
            WEB3AdminAccInfoLockAccountListService.class, 
            new TransactionalInterceptor(TransactionalInterceptor.TX_JOIN_EXISTING)); 
        
        //ロック登録解除受付１件サービス
        Services.addInterceptor(
            WEB3AccInfoLockRegistReleaseAcceptUnitService.class, 
            new TransactionalInterceptor(TransactionalInterceptor.TX_JOIN_EXISTING)); 
        
        //管理者お客様情報メールアドレスアップロードサービス
        Services.addInterceptor(
            WEB3AdminAccInfoMailAddressUploadService.class, 
            new TransactionalInterceptor(TransactionalInterceptor.TX_JOIN_EXISTING)); 
        
        //新規口座開設検索サービス
        Services.addInterceptor(
            WEB3AdminAccInfoAccEstablishSearchService.class, 
            new TransactionalInterceptor(TransactionalInterceptor.TX_JOIN_EXISTING)); 

        //個別顧客指定一覧サービス
        Services.addInterceptor(
            WEB3AdminAccInfoCampaignIndiviListService.class, 
            new TransactionalInterceptor(TransactionalInterceptor.TX_JOIN_EXISTING)); 

        //個別顧客指定変更サービス
        Services.addInterceptor(
            WEB3AdminAccInfoCampaignIndiviChangeService.class, 
            new TransactionalInterceptor(TransactionalInterceptor.TX_JOIN_EXISTING)); 

        //口座開設条件指定一覧サービス
        Services.addInterceptor(
            WEB3AdminAccInfoCampaignAccOpenListService.class, 
            new TransactionalInterceptor(TransactionalInterceptor.TX_JOIN_EXISTING)); 

        //口座開設条件指定変更サービス
        Services.addInterceptor(
            WEB3AdminAccInfoCampaignAccOpenChangeService.class, 
            new TransactionalInterceptor(TransactionalInterceptor.TX_JOIN_EXISTING)); 

        //登録顧客照会サービス
        Services.addInterceptor(
            WEB3AdminAccInfoCampaignRegistAccListService.class, 
            new TransactionalInterceptor(TransactionalInterceptor.TX_JOIN_EXISTING)); 
        
        //電子交付サービス登録・変更サービス
        Services.addInterceptor(
            WEB3AccInfoElecDeliveryRegisterChangeService.class, 
            new TransactionalInterceptor(TransactionalInterceptor.TX_JOIN_EXISTING)); 

        // Service.execute()呼び出し前後に  ----------------------
        // 処理開始時刻と処理終了時刻をログ出力するように設定する

        //お客様情報メールアドレス変更サービス
        Services.addInterceptor(
            WEB3AccInfoMailAddressChangeService.class, 
            new WEB3AccInfoServiceInterceptor());
            
        Services.addInterceptor(
            WEB3AccInfoCommonInputService.class,
            new WEB3AccInfoServiceInterceptor());
        
        //管理者お客まさ情報案内メール配信指示サービス
        Services.addInterceptor(
            WEB3AdminAccInfoMailDistributionService.class,
            new WEB3AccInfoServiceInterceptor());
        
        //管理者お客様情報内部者情報一覧サービス
        Services.addInterceptor(
            WEB3AdminAccInfoInsiderInfoListService.class,
            new WEB3AccInfoServiceInterceptor());
        
        //お客様情報案内メール配信設定変更サービス
        Services.addInterceptor(
            WEB3AccInfoMailDistributionChangeService.class, 
            new WEB3AccInfoServiceInterceptor());

        //お客様情報株式委託手数料コース変更申込サービス
        Services.addInterceptor(
            WEB3AccInfoEquityCommissionCourseRegistService.class, 
            new WEB3AccInfoServiceInterceptor());

        //管理者お客様情報ﾊｲﾊﾟｰﾎﾞｯｸｽ手数料変更ﾀﾞｳﾝﾛｰﾄﾞｻｰﾋﾞｽ
        Services.addInterceptor(
            WEB3AdminAccInfoHyperBoxCommissionChangeDownloadService.class, 
            new WEB3AccInfoServiceInterceptor());

        //管理者お客様情報ﾒｰﾙｱﾄﾞﾚｽ全件ﾀﾞｳﾝﾛｰﾄﾞｻｰﾋﾞｽ
        Services.addInterceptor(
            WEB3AdminAccInfoMailAddressDownloadService.class, 
            new WEB3AccInfoServiceInterceptor());

        //管理者お客様情報メールアドレス変更サービス
        Services.addInterceptor(
            WEB3AdminAccInfoMailAddressChangeService.class, 
            new WEB3AccInfoServiceInterceptor());

        //管理者お客様情報ﾒｰﾙｱﾄﾞﾚｽ変更顧客ﾀﾞｳﾝﾛｰﾄﾞｻｰﾋﾞｽ
        Services.addInterceptor(
            WEB3AdminAccInfoMailAddressChangeAccountDownloadService.class, 
            new WEB3AccInfoServiceInterceptor());

        //管理者お客様情報携帯番号・勤務先情報変更サービス 
        Services.addInterceptor(
            WEB3AdminAccInfoMobileOfficeChangeService.class, 
            new WEB3AccInfoServiceInterceptor());

        //管理者お客様情報携帯番号・勤務先情報変更申込顧客問合せサービス
        Services.addInterceptor(
            WEB3AdminAccInfoMobileOfficeRegistAccountInquiryService.class, 
            new WEB3AccInfoServiceInterceptor());

        //管理者お客様情報顧客基本情報問合せサービス
        Services.addInterceptor(
            WEB3AdminAccInfoAccountBaseInfoInquiryService.class, 
            new WEB3AccInfoServiceInterceptor());

        //管理者お客様情報手数料変更申込顧客ﾀﾞｳﾝﾛｰﾄﾞｻｰﾋﾞｽ
        Services.addInterceptor(
            WEB3AdminAccInfoCommissionRegistAccountDownloadService.class, 
            new WEB3AccInfoServiceInterceptor());

        //管理者お客様情報手数料変更申込顧客一覧問合せｻｰﾋﾞｽ
        Services.addInterceptor(
            WEB3AdminAccInfoCommissionRegistAccountListInquiryService.class, 
            new WEB3AccInfoServiceInterceptor());

        //管理者お客様情報手数料変更申込顧客問合せｻｰﾋﾞｽ
        Services.addInterceptor(
            WEB3AdminAccInfoCommissionRegistAccountInquiryService.class, 
            new WEB3AccInfoServiceInterceptor());

        //管理者お客様情報専用振込先口座ｱｯﾌﾟﾛｰﾄﾞｻｰﾋﾞｽ
        Services.addInterceptor(
            WEB3AdminAccInfoExclusiveTransferAccountUploadService.class, 
            new WEB3AdminAccInfoExclusiveTransferAccountUploadServiceInterceptor());

        //管理者お客様情報専用振込先口座変更サービス
        Services.addInterceptor(
            WEB3AdminAccInfoExclusiveTransferAccountChangeService.class, 
            new WEB3AccInfoServiceInterceptor());

        //管理者お客様情報停止状況変更サービス
        Services.addInterceptor(
            WEB3AdminAccInfoStopStateChangeService.class, 
            new WEB3AdminAccInfoStopStateChangeServiceInterceptor());

        //管理者お客様情報内部者情報変更サービス
        Services.addInterceptor(
            WEB3AdminAccInfoInsiderInfoChangeService.class, 
            new WEB3AccInfoServiceInterceptor());

        //お客様情報基本情報照会サービス
        Services.addInterceptor(
            WEB3AccInfoBaseInfoReferenceService.class, 
            new WEB3AccInfoServiceInterceptor());

        //お客様情報携帯番号・勤務先情報変更申込サービス
        Services.addInterceptor(
            WEB3AccInfoMobileOfficeRegistService.class, 
            new WEB3AccInfoServiceInterceptor());

        //お客様情報約定／未約定メール配信設定変更サービス
        Services.addInterceptor(
            WEB3AccInfoExecMailDistributionChangeService.class, 
            new WEB3AccInfoServiceInterceptor());
        
        //お客様情報暗証番号変更サービス
        Services.addInterceptor(
            WEB3AccInfoPasswordChangeService.class, 
            new WEB3AccInfoServiceInterceptor());
            
        //管理者お客様情報パスワードリセットサービス
        Services.addInterceptor(
            WEB3AdminAccInfoLoginPasswordResetService.class, 
            new WEB3AccInfoServiceInterceptor());
            
        //管理者お客様情報ﾊﾟｽﾜｰﾄﾞ変更顧客ﾀﾞｳﾝﾛｰﾄﾞｻｰﾋﾞｽ
        Services.addInterceptor(
            WEB3AdminAccInfoLoginPasswordChangeAccountDownloadService.class, 
            new WEB3AccInfoServiceInterceptor());
            
        //管理者お客様情報ログインエラーリセットサービス
        Services.addInterceptor(
            WEB3AdminAccInfoLoginErrorResetService.class, 
            new WEB3AccInfoServiceInterceptor());
            
        //管理者お客様情報暗証番号リセットサービス
        Services.addInterceptor(
            WEB3AdminAccInfoPasswordResetService.class, 
            new WEB3AccInfoServiceInterceptor());
            
        //管理者お客様情報暗証番号変更顧客ﾀﾞｳﾝﾛｰﾄﾞｻｰﾋﾞｽ
        Services.addInterceptor(
            WEB3AdminAccInfoPasswordChangeAccountDownloadService.class, 
            new WEB3AccInfoServiceInterceptor());
        
        //管理者お客様情報ロック顧客登録問合せ一覧サービス 
        Services.addInterceptor(
            WEB3AdminAccInfoLockAccountListService.class, 
            new WEB3AccInfoServiceInterceptor());
                
        //ロック登録解除受付１件サービス 
        Services.addInterceptor(
            WEB3AccInfoLockRegistReleaseAcceptUnitService.class, 
            new WEB3AccInfoLockRegistReleaseAcceptUnitInterceptor());
        
        //管理者お客様情報メールアドレスアップロードサービス
        Services.addInterceptor(
            WEB3AdminAccInfoMailAddressUploadService.class, 
            new WEB3AdminAccInfoMailAddressUploadServiceInterceptor());       
        
        //管理者お客様情報停止状況変更サービス
        Services.addInterceptor(
                WEB3AdminAccInfoStopStateChangeService.class, 
                new WEB3MQGatewayInterceptor());   
        
        //新規口座開設検索ｻｰﾋﾞｽ 
        Services.addInterceptor(
            WEB3AdminAccInfoAccEstablishSearchService.class, 
            new WEB3AccInfoServiceInterceptor());

        //電子交付サービス登録・変更サービス
        Services.addInterceptor(
            WEB3AccInfoElecDeliveryRegisterChangeService.class, 
            new WEB3AccInfoServiceInterceptor());

        // Message の登録処理 ----------------------
        
        //管理者お客様情報案内メール配信指示リクエスト
        regClass(WEB3AdminAccInfoMailDistributionRequest.class);
        //管理者お客様情報案内メール配信指示レスポンス
        regClass(WEB3AdminAccInfoMailDistributionResponse.class);
        //管理者お客様情報案内メール配信指示確認リクエスト
        regClass(WEB3AdminAccInfoMailDistributionConfirmRequest.class);
        //管理者お客様情報案内メール配信指示確認レスポンス
        regClass(WEB3AdminAccInfoMailDistributionConfirmResponse.class);
        //管理者お客様情報案内メール配信指示完了リクエスト
        regClass(WEB3AdminAccInfoMailDistributionCompleteRequest.class);
        //管理者お客様情報案内メール配信指示完了レスポンス
        regClass(WEB3AdminAccInfoMailDistributionCompleteResponse.class);
        //管理者お客様情報案内メール配信取消確認リクエスト
        regClass(WEB3AdminAccInfoMailDistributionCancelConfirmRequest.class);
        //管理者お客様情報案内メール配信取消確認レスポンス
        regClass(WEB3AdminAccInfoMailDistributionCancelConfirmResponse.class);
        //管理者お客様情報案内メール配信取消完了リクエスト
        regClass(WEB3AdminAccInfoMailDistributionCancelCompleteRequest.class);
        //管理者お客様情報案内メール配信取消完了レスポンス
        regClass(WEB3AdminAccInfoMailDistributionCancelCompleteResponse.class);      
        
        //管理者お客様情報ﾒｰﾙｱﾄﾞﾚｽ全件問合せリクエスト
        regClass(WEB3AdminAccInfoMailAddressInquiryRequest.class);
        //管理者お客様情報ﾒｰﾙｱﾄﾞﾚｽ全件問合せレスポンス
        regClass(WEB3AdminAccInfoMailAddressInquiryResponse.class);
        
        //お客様情報メールアドレス変更確認リクエスト
        regClass(WEB3AccInfoMailAddressChangeConfirmRequest.class);
        //お客様情報メールアドレス変更確認レスポンス
        regClass(WEB3AccInfoMailAddressChangeConfirmResponse.class);

        //お客様情報メールアドレス変更完了リクエスト
        regClass(WEB3AccInfoMailAddressChangeCompleteRequest.class);
        //お客様情報メールアドレス変更完了レスポンス
        regClass(WEB3AccInfoMailAddressChangeCompleteResponse.class);
        
        //お客様情報案内メール配信設定変更完了リクエスト
        regClass(WEB3AccInfoMailDistributionChangeCompleteRequest.class);
        //お客様情報案内メール配信設定変更完了レスポンス
        regClass(WEB3AccInfoMailDistributionChangeCompleteResponse.class);

        //お客様情報株式委託手数料コース変更申込確認リクエスト
        regClass(WEB3AccInfoEquityCommissionCourseChangeConfirmRequest.class);
        //お客様情報株式委託手数料コース変更申込確認レスポンス
        regClass(WEB3AccInfoEquityCommissionCourseChangeConfirmResponse.class);

        //お客様情報株式委託手数料コース変更申込完了リクエスト
        regClass(WEB3AccInfoEquityCommissionCourseChangeCompleteRequest.class);
        //お客様情報株式委託手数料コース変更申込完了レスポンス
        regClass(WEB3AccInfoEquityCommissionCourseChangeCompleteResponse.class);

        //お客様情報株式委託手数料コース変更申込取消確認リクエスト
        regClass(WEB3AccInfoEquityCommissionCourseChangeCancelConfirmRequest.class);
        //お客様情報株式委託手数料コース変更申込取消確認レスポンス
        regClass(WEB3AccInfoEquityCommissionCourseChangeCancelConfirmResponse.class);

        //お客様情報株式委託手数料コース変更申込取消完了リクエスト
        regClass(WEB3AccInfoEquityCommissionCourseChangeCancelCompleteRequest.class);
        //お客様情報株式委託手数料コース変更申込取消完了レスポンス
        regClass(WEB3AccInfoEquityCommissionCourseChangeCancelCompleteResponse.class);

        //お客様情報株式委託手数料コース変更申込入力リクエスト
        regClass(WEB3AccInfoEquityCommissionCourseChangeInputRequest.class);
        //お客様情報株式委託手数料コース変更申込入力レスポンス
        regClass(WEB3AccInfoEquityCommissionCourseChangeInputResponse.class);

        //お客様情報基本情報照会リクエスト
        regClass(WEB3AccInfoAccountBaseInfoReferenceRequest.class);
        //お客様情報基本情報照会レスポンス
        regClass(WEB3AccInfoAccountBaseInfoReferenceResponse.class);

        //お客様情報共通入力リクエスト
        regClass(WEB3AccInfoCommonInputRequest.class);
        //お客様情報共通入力レスポンス
        regClass(WEB3AccInfoCommonInputResponse.class);

        //お客様情報携帯番号・勤務先情報変更申込確認リクエスト
        regClass(WEB3AccInfoMobileOfficeRegistConfirmRequest.class);
        //お客様情報携帯番号・勤務先情報変更申込確認レスポンス
        regClass(WEB3AccInfoMobileOfficeRegistConfirmResponse.class);

        //お客様情報携帯番号・勤務先情報変更申込完了リクエスト
        regClass(WEB3AccInfoMobileOfficeRegistCompleteRequest.class);
        //お客様情報携帯番号・勤務先情報変更申込完了レスポンス
        regClass(WEB3AccInfoMobileOfficeRegistCompleteResponse.class);

        //お客様情報携帯番号・勤務先情報変更申込入力リクエスト
        regClass(WEB3AccInfoMobileOfficeRegistInputRequest.class);
        //お客様情報携帯番号・勤務先情報変更申込入力レスポンス
        regClass(WEB3AccInfoMobileOfficeRegistInputResponse.class);

        //お客様情報約定／未約定メール配信設定変更完了リクエスト
        regClass(WEB3AccInfoExecMailDistributionChangeCompleteRequest.class);
        //お客様情報約定／未約定メール配信設定変更完了レスポンス
        regClass(WEB3AccInfoExecMailDistributionChangeCompleteResponse.class);
        //携帯番号・勤務先情報 
        //regClass(WEB3AccInfoMobileOfficeInfo.class);
        //顧客基本情報 
        //regClass(WEB3AccInfoAccountBaseInfo.class);
        //手数料コース変更申込情報 
        //regClass(WEB3AccInfoCommissionCourseChangeInfo.class);
        //停止情報 
        //regClass(WEB3AccInfoStopInfo.class);
        //電子鳩情報 
        //regClass(WEB3AccInfoBatoInfo.class);
        //内部者情報 
        //regClass(WEB3AccInfoInsiderInfo.class);
        //お客様情報アップロード履歴明細 
        //regClass(WEB3AccInfoUploadHistoryUnit.class);
        //お客様情報ソートキー 
        //regClass(WEB3AccInfoSortKey.class);
        //管理者お客様情報ﾊｲﾊﾟｰﾎﾞｯｸｽ手数料変更ﾀﾞｳﾝﾛｰﾄﾞ問合せﾘｸｴｽﾄ 
        regClass(WEB3AdminAccInfoHyperBoxCommissionChangeInquiryRequest.class);
        //管理者お客様情報ﾊｲﾊﾟｰﾎﾞｯｸｽ手数料変更ﾀﾞｳﾝﾛｰﾄﾞ問合せﾚｽﾎﾟﾝｽ 
        regClass(WEB3AdminAccInfoHyperBoxCommissionChangeInquiryResponse.class);
        //管理者お客様情報ﾊｲﾊﾟｰﾎﾞｯｸｽ手数料変更ﾃﾞｰﾀﾀﾞｳﾝﾛｰﾄﾞﾘｸｴｽﾄ 
        regClass(WEB3AdminAccInfoHyperBoxCommissionChangeDownloadRequest.class);
        //管理者お客様情報ﾊｲﾊﾟｰﾎﾞｯｸｽ手数料変更ﾃﾞｰﾀﾀﾞｳﾝﾛｰﾄﾞﾚｽﾎﾟﾝｽ 
        regClass(WEB3AdminAccInfoHyperBoxCommissionChangeDownloadResponse.class);
        //管理者お客様情報ﾊｲﾊﾟｰﾎﾞｯｸｽ手数料変更ﾃﾞｰﾀﾌｧｲﾙﾀﾞｳﾝﾛｰﾄﾞﾘｸｴｽﾄ 
        regClass(WEB3AdminAccInfoHyperBoxCommissionChangeFileDownloadRequest.class);
        //管理者お客様情報ﾊｲﾊﾟｰﾎﾞｯｸｽ手数料変更ﾃﾞｰﾀﾌｧｲﾙﾀﾞｳﾝﾛｰﾄﾞﾚｽﾎﾟﾝｽ 
        regClass(WEB3AdminAccInfoHyperBoxCommissionChangeFileDownloadResponse.class);
        //管理者お客様情報ﾒｰﾙｱﾄﾞﾚｽ全件ﾀﾞｳﾝﾛｰﾄﾞﾘｸｴｽﾄ 
        regClass(WEB3AdminAccInfoMailAddressDownloadRequest.class);
        //管理者お客様情報ﾒｰﾙｱﾄﾞﾚｽ全件ﾀﾞｳﾝﾛｰﾄﾞﾚｽﾎﾟﾝｽ 
        regClass(WEB3AdminAccInfoMailAddressDownloadResponse.class);
        //管理者お客様情報ﾒｰﾙｱﾄﾞﾚｽ全件ﾌｧｲﾙﾀﾞｳﾝﾛｰﾄﾞﾘｸｴｽﾄ 
        regClass(WEB3AdminAccInfoMailAddressFileDownloadRequest.class);
        //管理者お客様情報ﾒｰﾙｱﾄﾞﾚｽ全件ﾌｧｲﾙﾀﾞｳﾝﾛｰﾄﾞﾚｽﾎﾟﾝｽ 
        regClass(WEB3AdminAccInfoMailAddressFileDownloadResponse.class);
        //管理者お客様情報メールアドレス変更確認リクエスト 
        regClass(WEB3AdminAccInfoMailAddressChangeConfirmRequest.class);
        //管理者お客様情報メールアドレス変更確認レスポンス 
        regClass(WEB3AdminAccInfoMailAddressChangeConfirmResponse.class);
        //管理者お客様情報メールアドレス変更完了リクエスト 
        regClass(WEB3AdminAccInfoMailAddressChangeCompleteRequest.class);
        //管理者お客様情報メールアドレス変更完了レスポンス 
        regClass(WEB3AdminAccInfoMailAddressChangeCompleteResponse.class);
        //管理者お客様情報メールアドレス変更顧客ﾀﾞｳﾝﾛｰﾄﾞﾘｸｴｽﾄ 
        regClass(WEB3AdminAccInfoMailAddressChangeAccountDownloadRequest.class);
        //管理者お客様情報メールアドレス変更顧客ﾀﾞｳﾝﾛｰﾄﾞﾚｽﾎﾟﾝｽ 
        regClass(WEB3AdminAccInfoMailAddressChangeAccountDownloadResponse.class);
        //管理者お客様情報メールアドレス変更顧客ﾌｧｲﾙﾀﾞｳﾝﾛｰﾄﾞﾘｸｴｽﾄ 
        regClass(WEB3AdminAccInfoMailAddressChangeAccountFileDownloadRequest.class);
        //管理者お客様情報メールアドレス変更顧客ﾌｧｲﾙﾀﾞｳﾝﾛｰﾄﾞﾚｽﾎﾟﾝｽ 
        regClass(WEB3AdminAccInfoMailAddressChangeAccountFileDownloadResponse.class);
        //管理者お客様情報メールアドレス変更顧客問合せﾘｸｴｽﾄ 
        regClass(WEB3AdminAccInfoMailAddressChangeAccountInquiryRequest.class);
        //管理者お客様情報メールアドレス変更顧客問合せﾚｽﾎﾟﾝｽ 
        regClass(WEB3AdminAccInfoMailAddressChangeAccountInquiryResponse.class);
        //管理者お客様情報メールアドレス変更入力リクエスト 
        regClass(WEB3AdminAccInfoMailAddressChangeInputRequest.class);
        //管理者お客様情報メールアドレス変更入力レスポンス 
        regClass(WEB3AdminAccInfoMailAddressChangeInputResponse.class);
        //管理者お客様情報携帯番号・勤務先情報変更確認リクエスト 
        regClass(WEB3AdminAccInfoMobileOfficeRegistConfirmRequest.class);
        //管理者お客様情報携帯番号・勤務先情報変更確認レスポンス 
        regClass(WEB3AdminAccInfoMobileOfficeRegistConfirmResponse.class);
        //管理者お客様情報携帯番号・勤務先情報変更完了リクエスト 
        regClass(WEB3AdminAccInfoMobileOfficeRegistCompleteRequest.class);
        //管理者お客様情報携帯番号・勤務先情報変更完了レスポンス 
        regClass(WEB3AdminAccInfoMobileOfficeRegistCompleteResponse.class);
        //管理者お客様情報携帯番号・勤務先情報変更申込顧客ﾘｸｴｽﾄ 
        regClass(WEB3AdminAccInfoMobileOfficeRegistAccountRequest.class);
        //管理者お客様情報携帯番号・勤務先情報変更申込顧客ﾚｽﾎﾟﾝｽ 
        regClass(WEB3AdminAccInfoMobileOfficeRegistAccountResponse.class);
        //管理者お客様情報携帯番号・勤務先情報変更申込顧客問合せﾘｸｴｽﾄ 
        regClass(WEB3AdminAccInfoMobileOfficeRegistAccountInquiryRequest.class);
        //管理者お客様情報携帯番号・勤務先情報変更申込顧客問合せﾚｽﾎﾟﾝｽ 
        regClass(WEB3AdminAccInfoMobileOfficeRegistAccountInquiryResponse.class);
        //管理者お客様情報携帯番号・勤務先情報変更入力リクエスト 
        regClass(WEB3AdminAccInfoMobileOfficeRegistInputRequest.class);
        //管理者お客様情報携帯番号・勤務先情報変更入力レスポンス 
        regClass(WEB3AdminAccInfoMobileOfficeRegistInputResponse.class);
        //管理者お客様情報顧客基本情報問合せリクエスト 
        regClass(WEB3AdminAccInfoAccountBaseInfoResultRequest.class);
        //管理者お客様情報顧客基本情報問合せレスポンス 
        regClass(WEB3AdminAccInfoAccountBaseInfoResultResponse.class);
        //管理者お客様情報顧客基本情報問合せ入力リクエスト 
        regClass(WEB3AdminAccInfoAccountBaseInfoInquiryRequest.class);
        //管理者お客様情報顧客基本情報問合せ入力レスポンス 
        regClass(WEB3AdminAccInfoAccountBaseInfoInquiryResponse.class);
        //管理者お客様情報手数料変更申込顧客ﾀﾞｳﾝﾛｰﾄﾞﾘｸｴｽﾄ 
        regClass(WEB3AdminAccInfoCommissionChangeAccountDownloadRequest.class);
        //管理者お客様情報手数料変更申込顧客ﾀﾞｳﾝﾛｰﾄﾞﾚｽﾎﾟﾝｽ 
        regClass(WEB3AdminAccInfoCommissionChangeAccountDownloadResponse.class);
        //管理者お客様情報手数料変更申込顧客ﾀﾞｳﾝﾛｰﾄﾞ問合せﾘｸｴｽﾄ 
        regClass(WEB3AdminAccInfoCommissionChangeAccountDownloadInquiryRequest.class);
        //管理者お客様情報手数料変更申込顧客ﾀﾞｳﾝﾛｰﾄﾞ問合せﾚｽﾎﾟﾝｽ 
        regClass(WEB3AdminAccInfoCommissionChangeAccountDownloadInquiryResponse.class);
        //管理者お客様情報手数料変更申込顧客ﾌｧｲﾙﾀﾞｳﾝﾛｰﾄﾞﾘｸｴｽﾄ 
        regClass(WEB3AdminAccInfoCommissionChangeAccountFileDownloadRequest.class);
        //管理者お客様情報手数料変更申込顧客ﾌｧｲﾙﾀﾞｳﾝﾛｰﾄﾞﾚｽﾎﾟﾝｽ 
        regClass(WEB3AdminAccInfoCommissionChangeAccountFileDownloadResponse.class);
        //管理者お客様情報手数料変更申込顧客一覧問合せﾘｸｴｽﾄ 
        regClass(WEB3AdminAccInfoCommissionChangeAccountListInquiryRequest.class);
        //管理者お客様情報手数料変更申込顧客一覧問合せﾚｽﾎﾟﾝｽ 
        regClass(WEB3AdminAccInfoCommissionChangeAccountListInquiryResponse.class);
        //管理者お客様情報手数料変更申込顧客一覧問合せ入力ﾘｸｴｽﾄ 
        regClass(WEB3AdminAccInfoCommissionChangeAccountListInquiryInputRequest.class);
        //管理者お客様情報手数料変更申込顧客一覧問合せ入力ﾚｽﾎﾟﾝｽ 
        regClass(WEB3AdminAccInfoCommissionChangeAccountListInquiryInputResponse.class);
        //管理者お客様情報手数料変更申込顧客問合せﾘｸｴｽﾄ 
        regClass(WEB3AdminAccInfoCommissionChangeAccountInquiryRequest.class);
        //管理者お客様情報手数料変更申込顧客問合せﾚｽﾎﾟﾝｽ 
        regClass(WEB3AdminAccInfoCommissionChangeAccountInquiryResponse.class);
        //管理者お客様情報手数料変更申込顧客問合せ入力ﾘｸｴｽﾄ 
        regClass(WEB3AdminAccInfoCommissionChangeAccountInquiryInputRequest.class);
        //管理者お客様情報手数料変更申込顧客問合せ入力ﾚｽﾎﾟﾝｽ 
        regClass(WEB3AdminAccInfoCommissionChangeAccountInquiryInputResponse.class);
        //管理者お客様情報専用振込先口座ｱｯﾌﾟﾛｰﾄﾞ確認ﾘｸｴｽﾄ 
        regClass(WEB3AdminAccInfoExclusiveTransferAccountUploadConfirmRequest.class);
        //管理者お客様情報専用振込先口座ｱｯﾌﾟﾛｰﾄﾞ確認ﾚｽﾎﾟﾝｽ 
        regClass(WEB3AdminAccInfoExclusiveTransferAccountUploadConfirmResponse.class);
        //管理者お客様情報専用振込先口座ｱｯﾌﾟﾛｰﾄﾞ完了ﾘｸｴｽﾄ 
        regClass(WEB3AdminAccInfoExclusiveTransferAccountUploadCompleteRequest.class);
        //管理者お客様情報専用振込先口座ｱｯﾌﾟﾛｰﾄﾞ完了ﾚｽﾎﾟﾝｽ 
        regClass(WEB3AdminAccInfoExclusiveTransferAccountUploadCompleteResponse.class);
        //管理者お客様情報専用振込先口座ｱｯﾌﾟﾛｰﾄﾞ中止ﾘｸｴｽﾄ 
        regClass(WEB3AdminAccInfoExclusiveTransferAccountUploadCancelRequest.class);
        //管理者お客様情報専用振込先口座ｱｯﾌﾟﾛｰﾄﾞ中止ﾚｽﾎﾟﾝｽ 
        regClass(WEB3AdminAccInfoExclusiveTransferAccountUploadCancelResponse.class);
        //管理者お客様情報専用振込先口座ｱｯﾌﾟﾛｰﾄﾞ入力ﾘｸｴｽﾄ 
        regClass(WEB3AdminAccInfoExclusiveTransferAccountUploadInputRequest.class);
        //管理者お客様情報専用振込先口座ｱｯﾌﾟﾛｰﾄﾞ入力ﾚｽﾎﾟﾝｽ 
        regClass(WEB3AdminAccInfoExclusiveTransferAccountUploadInputResponse.class);
        //管理者お客様情報専用振込先口座変更確認ﾘｸｴｽﾄ 
        regClass(WEB3AdminAccInfoExclusiveTransferAccountChangeConfirmRequest.class);
        //管理者お客様情報専用振込先口座変更確認ﾚｽﾎﾟﾝｽ 
        regClass(WEB3AdminAccInfoExclusiveTransferAccountChangeConfirmResponse.class);
        //管理者お客様情報専用振込先口座変更完了ﾘｸｴｽﾄ 
        regClass(WEB3AdminAccInfoExclusiveTransferAccountChangeCompleteRequest.class);
        //管理者お客様情報専用振込先口座変更完了ﾚｽﾎﾟﾝｽ 
        regClass(WEB3AdminAccInfoExclusiveTransferAccountChangeCompleteResponse.class);
        //管理者お客様情報専用振込先口座変更入力ﾘｸｴｽﾄ 
        regClass(WEB3AdminAccInfoExclusiveTransferAccountChangeInputRequest.class);
        //管理者お客様情報専用振込先口座変更入力ﾚｽﾎﾟﾝｽ 
        regClass(WEB3AdminAccInfoExclusiveTransferAccountChangeInputResponse.class);
        //管理者お客様情報停止状況変更確認リクエスト 
        regClass(WEB3AdminAccInfoStopStateChangeConfirmRequest.class);
        //管理者お客様情報停止状況変更確認レスポンス 
        regClass(WEB3AdminAccInfoStopStateChangeConfirmResponse.class);
        //管理者お客様情報停止状況変更完了リクエスト 
        regClass(WEB3AdminAccInfoStopStateChangeCompleteRequest.class);
        //管理者お客様情報停止状況変更完了レスポンス 
        regClass(WEB3AdminAccInfoStopStateChangeCompleteResponse.class);
        //管理者お客様情報停止状況変更入力リクエスト 
        regClass(WEB3AdminAccInfoStopStateChangeInputRequest.class);
        //管理者お客様情報停止状況変更入力レスポンス 
        regClass(WEB3AdminAccInfoStopStateChangeInputResponse.class);
        //管理者お客様情報内部者情報変更確認リクエスト 
        regClass(WEB3AdminAccInfoInsiderInfoChangeConfirmRequest.class);
        //管理者お客様情報内部者情報変更確認レスポンス 
        regClass(WEB3AdminAccInfoInsiderInfoChangeConfirmResponse.class);
        //管理者お客様情報内部者情報変更完了リクエスト 
        regClass(WEB3AdminAccInfoInsiderInfoChangeCompleteRequest.class);
        //管理者お客様情報内部者情報変更完了レスポンス 
        regClass(WEB3AdminAccInfoInsiderInfoChangeCompleteResponse.class);
        //管理者お客様情報内部者情報変更入力リクエスト 
        regClass(WEB3AdminAccInfoInsiderInfoChangeInputRequest.class);
        //管理者お客様情報内部者情報変更入力レスポンス 
        regClass(WEB3AdminAccInfoInsiderInfoChangeInputResponse.class);
        //管理者お客様情報内部情報一覧入力リクエスト
        regClass(WEB3AdminAccInfoInsiderInfoInputRequest.class);
        //管理者お客様情報内部情報一覧入力レスポンス
        regClass(WEB3AdminAccInfoInsiderInfoInputResponse.class);
        //管理者お客様情報内部情報一覧リクエスト
        regClass(WEB3AdminAccInfoInsiderInfoListRequest.class);
        //管理者お客様情報内部情報一覧レスポンス
        regClass(WEB3AdminAccInfoInsiderInfoListResponse.class);
        //携帯番号・勤務先情報変更申込顧客 
        //regClass(WEB3AccInfoMobileOfficeChangeAccount.class);
        //顧客メールアドレス情報 
        //regClass(WEB3AccInfoAccountMailAddressInfo.class);
        //手数料変更顧客情報 
        //regClass(WEB3AccInfoCommissionChangeAccountInfo.class);
        
        //お客様情報暗証番号変更リクエスト 
        regClass(WEB3AccInfoPasswordChangeCompleteRequest.class);
        //お客様情報暗証番号変更レスポンス 
        regClass(WEB3AccInfoPasswordChangeCompleteResponse.class);
        //お客様情報暗証番号変更入力リクエスト 
        regClass(WEB3AccInfoPasswordChangeInputRequest.class);
        //お客様情報暗証番号変更入力レスポンス 
        regClass(WEB3AccInfoPasswordChangeInputResponse.class);
        //パスワード変更顧客情報 
        //regClass(WEB3AccInfoLoginPasswordChangeAccountInfo.class);
        //暗証番号変更顧客情報 
        //regClass(WEB3AccInfoPasswordChangeAccountInfo.class);
        //管理者お客様情報パスワードリセットリクエスト 
        regClass(WEB3AdminAccInfoLoginPasswordResetRequest.class);
        //管理者お客様情報パスワードリセットレスポンス 
        regClass(WEB3AdminAccInfoLoginPasswordResetResponse.class);
        //管理者お客様情報パスワードリセット入力リクエスト 
        regClass(WEB3AdminAccInfoLoginPasswordResetInputRequest.class);
        //管理者お客様情報パスワードリセット入力レスポンス 
        regClass(WEB3AdminAccInfoLoginPasswordResetInputResponse.class);
        //管理者お客様情報ﾊﾟｽﾜｰﾄﾞ変更顧客ﾀﾞｳﾝﾛｰﾄﾞﾘｸｴｽﾄ 
        regClass(WEB3AdminAccInfoLoginPasswordChangeAccountDownloadRequest.class);
        //管理者お客様情報ﾊﾟｽﾜｰﾄﾞ変更顧客ﾀﾞｳﾝﾛｰﾄﾞﾚｽﾎﾟﾝｽ 
        regClass(WEB3AdminAccInfoLoginPasswordChangeAccountDownloadResponse.class);
        //管理者お客様情報ﾊﾟｽﾜｰﾄﾞ変更顧客ﾌｧｲﾙﾀﾞｳﾝﾛｰﾄﾞﾘｸｴｽﾄ 
        regClass(WEB3AdminAccInfoLoginPasswordChangeAccountFileDownloadRequest.class);
        //管理者お客様情報ﾊﾟｽﾜｰﾄﾞ変更顧客ﾌｧｲﾙﾀﾞｳﾝﾛｰﾄﾞﾚｽﾎﾟﾝｽ 
        regClass(WEB3AdminAccInfoLoginPasswordChangeAccountFileDownloadResponse.class);
        //管理者お客様情報ﾊﾟｽﾜｰﾄﾞ変更顧客問合せﾘｸｴｽﾄ 
        regClass(WEB3AdminAccInfoLoginPasswordChangeAccountInquiryRequest.class);
        //管理者お客様情報ﾊﾟｽﾜｰﾄﾞ変更顧客問合せﾚｽﾎﾟﾝｽ 
        regClass(WEB3AdminAccInfoLoginPasswordChangeAccountInquiryResponse.class);
        //管理者お客様情報ログインエラーリセットリクエスト 
        regClass(WEB3AdminAccInfoLoginErrorResetRequest.class);
        //管理者お客様情報ログインエラーリセットレスポンス 
        regClass(WEB3AdminAccInfoLoginErrorResetResponse.class);
        //管理者お客様情報ログインエラーリセット入力リクエスト 
        regClass(WEB3AdminAccInfoLoginErrorResetInputRequest.class);
        //管理者お客様情報ログインエラーリセット入力レスポンス 
        regClass(WEB3AdminAccInfoLoginErrorResetInputResponse.class);
        //管理者お客様情報暗証番号リセットリクエスト 
        regClass(WEB3AdminAccInfoPasswordResetRequest.class);
        //管理者お客様情報暗証番号リセットレスポンス 
        regClass(WEB3AdminAccInfoPasswordResetResponse.class);
        //管理者お客様情報暗証番号リセット入力リクエスト 
        regClass(WEB3AdminAccInfoPasswordResetInputRequest.class);
        //管理者お客様情報暗証番号リセット入力レスポンス 
        regClass(WEB3AdminAccInfoPasswordResetInputResponse.class);
        //管理者お客様情報暗証番号変更顧客ﾀﾞｳﾝﾛｰﾄﾞﾘｸｴｽﾄ 
        regClass(WEB3AdminAccInfoPasswordChangeAccountDownloadRequest.class);
        //管理者お客様情報暗証番号変更顧客ﾀﾞｳﾝﾛｰﾄﾞﾚｽﾎﾟﾝｽ 
        regClass(WEB3AdminAccInfoPasswordChangeAccountDownloadResponse.class);
        //管理者お客様情報暗証番号変更顧客ﾌｧｲﾙﾀﾞｳﾝﾛｰﾄﾞﾘｸｴｽﾄ 
        regClass(WEB3AdminAccInfoPasswordChangeAccountFileDownloadRequest.class);
        //管理者お客様情報暗証番号変更顧客ﾌｧｲﾙﾀﾞｳﾝﾛｰﾄﾞﾚｽﾎﾟﾝｽ 
        regClass(WEB3AdminAccInfoPasswordChangeAccountFileDownloadResponse.class);
        //管理者お客様情報暗証番号変更顧客問合せﾘｸｴｽﾄ 
        regClass(WEB3AdminAccInfoPasswordChangeAccountInquiryRequest.class);
        //管理者お客様情報暗証番号変更顧客問合せﾚｽﾎﾟﾝｽ 
        regClass(WEB3AdminAccInfoPasswordChangeAccountInquiryResponse.class);        
        
        //ロック登録解除受付リクエスト
        regClass(WEB3AccInfoLockRegistReleaseAcceptRequest.class);  
        //ロック登録解除受付レスポンス
        regClass(WEB3AccInfoLockRegistReleaseAcceptResponse.class); 
        //管理者お客様情報ロック顧客問合せ一覧入力リクエスト
        regClass(WEB3AdminAccInfoLockAccountSearchInputRequest.class);
        //管理者お客様情報ロック顧客問合せ一覧入力レスポンス
        regClass(WEB3AdminAccInfoLockAccountSearchInputResponse.class);
        //管理者お客様情報ロック顧客問合せ一覧リクエスト
        regClass(WEB3AdminAccInfoLockAccountSearchListRequest.class);
        //管理者お客様情報ロック顧客問合せ一覧レスポンス
        regClass(WEB3AdminAccInfoLockAccountSearchListResponse.class);
        
        //管理者お客様情報メールアドレスアップロード入力リクエスト
        regClass(WEB3AdminAccInfoMailAddressUploadInputRequest.class);
        //管理者お客様情報メールアドレスアップロード確認リクエスト
        regClass(WEB3AdminAccInfoMailAddressUploadConfirmRequest.class);
        //管理者お客様情報メールアドレスアップロード完了リクエスト
        regClass(WEB3AdminAccInfoMailAddressUploadCompleteRequest.class);
        //管理者お客様情報メールアドレスアップロード中止リクエスト
        regClass(WEB3AdminAccInfoMailAddressUploadCancelRequest.class);
        //管理者お客様情報メールアドレスアップロード入力レスポンス
        regClass(WEB3AdminAccInfoMailAddressUploadInputResponse.class);
        //管理者お客様情報メールアドレスアップロード確認レスポンス
        regClass(WEB3AdminAccInfoMailAddressUploadConfirmResponse.class);
        //管理者お客様情報メールアドレスアップロード完了レスポンス
        regClass(WEB3AdminAccInfoMailAddressUploadCompleteResponse.class);
        //管理者お客様情報メールアドレスアップロード中止レスポンス
        regClass(WEB3AdminAccInfoMailAddressUploadCancelResponse.class);
                
        //ログインロック顧客検索入力ﾘｸｴｽﾄ
        regClass(WEB3AdminAccInfoAccEstablishSearchInputRequest.class);
        //ログインロック顧客検索入力ﾚｽﾎﾟﾝｽ
        regClass(WEB3AdminAccInfoAccEstablishSearchInputResponse.class);
        //ログインロック顧客検索一覧ﾘｸｴｽﾄ
        regClass(WEB3AdminAccInfoAccEstablishSearchListRequest.class);
        //ログインロック顧客検索一覧ﾚｽﾎﾟﾝｽ
        regClass(WEB3AdminAccInfoAccEstablishSearchListResponse.class);
        //ログインロック顧客検索ﾀﾞｳﾝﾛｰﾄﾞﾘｸｴｽﾄ
        regClass(WEB3AdminAccInfoAccEstablishSearchDLRequest.class);
        //ログインロック顧客検索ﾀﾞｳﾝﾛｰﾄﾞﾚｽﾎﾟﾝｽ
        regClass(WEB3AdminAccInfoAccEstablishSearchDLResponse.class);

        //管理者お客様情報携帯番号・勤務先情報変更申込一括判定確認リクエスト
        regClass(WEB3AdminAccInfoMobileOfficeRegAccConfirmRequest.class);
        //管理者お客様情報携帯番号・勤務先情報変更申込一括判定確認ﾚｽﾎﾟﾝｽ
        regClass(WEB3AdminAccInfoMobileOfficeRegAccConfirmResponse.class);
        //管理者お客様情報携帯番号・勤務先情報変更申込一括判定完了リクエスト
        regClass(WEB3AdminAccInfoMobileOfficeRegAccCompleteRequest.class);
        //管理者お客様情報携帯番号・勤務先情報変更申込一括判定完了ﾚｽﾎﾟﾝｽ
        regClass(WEB3AdminAccInfoMobileOfficeRegAccCompleteResponse.class);
        
        //管理者お客様情報手数料割引キャンペーン個別顧客指定一覧要求
        regClass(WEB3AdminAccInfoCampaignIndiviListRequest.class);
        //管理者お客様情報手数料割引キャンペーン個別顧客指定一覧応答
        regClass(WEB3AdminAccInfoCampaignIndiviListResponse.class);
        //管理者お客様情報手数料割引キャンペーン登録顧客照会要求
        regClass(WEB3AdminAccInfoCampaignRegistAccListRequest.class);
        //管理者お客様情報手数料割引キャンペーン登録顧客照会応答
        regClass(WEB3AdminAccInfoCampaignRegistAccListResponse.class);
        //管理者お客様情報手数料割引キャンペーン口座開設条件一覧要求
        regClass(WEB3AdminAccInfoCampaignAccOpenListRequest.class);
        //管理者お客様情報手数料割引キャンペーン口座開設条件一覧応答
        regClass(WEB3AdminAccInfoCampaignAccOpenListResponse.class);
        //管理者お客様情報手数料割引キャンペーン口座開設条件入力要求
        regClass(WEB3AdminAccInfoCampaignAccOpenInputRequest.class);
        //管理者お客様情報手数料割引キャンペーン口座開設条件入力応答
        regClass(WEB3AdminAccInfoCampaignAccOpenInputResponse.class);
        //管理者お客様情報手数料割引キャンペーン口座開設条件確認要求
        regClass(WEB3AdminAccInfoCampaignAccOpenConfirmRequest.class);
        //管理者お客様情報手数料割引キャンペーン口座開設条件確認応答
        regClass(WEB3AdminAccInfoCampaignAccOpenConfirmResponse.class);
        //管理者お客様情報手数料割引キャンペーン口座開設条件完了要求
        regClass(WEB3AdminAccInfoCampaignAccOpenCompleteRequest.class);
        //管理者お客様情報手数料割引キャンペーン口座開設条件完了応答
        regClass(WEB3AdminAccInfoCampaignAccOpenCompleteResponse.class);
        //管理者お客様情報手数料割引キャンペーン個別顧客指定入力要求
        regClass(WEB3AdminAccInfoCampaignIndiviInputRequest.class);
        //管理者お客様情報手数料割引キャンペーン個別顧客指定入力応答
        regClass(WEB3AdminAccInfoCampaignIndiviInputResponse.class);
        //管理者お客様情報手数料割引キャンペーン個別顧客指定確認要求
        regClass(WEB3AdminAccInfoCampaignIndiviConfirmRequest.class);
        //管理者お客様情報手数料割引キャンペーン個別顧客指定確認応答
        regClass(WEB3AdminAccInfoCampaignIndiviConfirmResponse.class);
        //管理者お客様情報手数料割引キャンペーン個別顧客指定完了要求
        regClass(WEB3AdminAccInfoCampaignIndiviCompleteRequest.class);
        //管理者お客様情報手数料割引キャンペーン個別顧客指定完了応答
        regClass(WEB3AdminAccInfoCampaignIndiviCompleteResponse.class);

        //電子交付サービス登録・変更入力リクエスト
        regClass(WEB3AccInfoElecDeliveryRegisterChangeInputRequest.class);
        //電子交付サービス登録・変更入力レスポンス
        regClass(WEB3AccInfoElecDeliveryRegisterChangeInputResponse.class);

        //電子交付サービス登録・変更完了リクエスト
        regClass(WEB3AccInfoElecDeliveryRegisterChangeCompleteRequest.class);
        //電子交付サービス登録・変更完了レスポンス
        regClass(WEB3AccInfoElecDeliveryRegisterChangeCompleteResponse.class);

        //電子交付情報一覧リクエスト
        regClass(WEB3AccInfoElecDeliveryApyReferenceRequest.class);
        //電子交付情報一覧レスポンス
        regClass(WEB3AccInfoElecDeliveryApyReferenceResponse.class);

        //Handler の登録処理 ----------------------

        //お客様情報共通入力 用ハンドラーの登録
        regHandler(
            WEB3AccInfoAppPlugin.class,
            WEB3AccInfoCommonInputRequest.class,
            WEB3AccInfoCommonInputHandler.class,
            "inputScreenDisplay");

        //お客様情報メールアドレス変更 用ハンドラーの登録
        regHandler(
            WEB3AccInfoAppPlugin.class,
            WEB3AccInfoMailAddressChangeConfirmRequest.class,
            WEB3AccInfoMailAddressChangeHandler.class,
            "mailAddressChangeConfirm");

        //お客様情報メールアドレス変更 用ハンドラーの登録
        regHandler(
            WEB3AccInfoAppPlugin.class,
            WEB3AccInfoMailAddressChangeCompleteRequest.class,
            WEB3AccInfoMailAddressChangeHandler.class,
            "mailAddressChangeComplete");

        //お客様情報案内メール配信設定変更 用ハンドラーの登録
        regHandler(
            WEB3AccInfoAppPlugin.class,
            WEB3AccInfoMailDistributionChangeCompleteRequest.class,
            WEB3AccInfoMailDistributionChangeHandler.class,
            "accountInfoMailDistributionChangeComplete");

        //お客様情報株式委託手数料コース変更申込 用ハンドラーの登録
        regHandler(
            WEB3AccInfoAppPlugin.class,
            WEB3AccInfoEquityCommissionCourseChangeInputRequest.class, 
            WEB3AccInfoEquityCommissionCourseRegistHandler.class, 
            "inputScreenDisplay");

        //お客様情報株式委託手数料コース変更申込 用ハンドラーの登録
        regHandler(
            WEB3AccInfoAppPlugin.class,
            WEB3AccInfoEquityCommissionCourseChangeConfirmRequest.class, 
            WEB3AccInfoEquityCommissionCourseRegistHandler.class, 
            "registConfirm");

        //お客様情報株式委託手数料コース変更申込 用ハンドラーの登録
        regHandler(
            WEB3AccInfoAppPlugin.class,
            WEB3AccInfoEquityCommissionCourseChangeCompleteRequest.class,
            WEB3AccInfoEquityCommissionCourseRegistHandler.class,
            "registComplete");

        //お客様情報株式委託手数料コース変更申込 用ハンドラーの登録
        regHandler(
            WEB3AccInfoAppPlugin.class,
            WEB3AccInfoEquityCommissionCourseChangeCancelConfirmRequest.class,
            WEB3AccInfoEquityCommissionCourseRegistHandler.class,
            "registCancelConfirm");

        //お客様情報株式委託手数料コース変更申込 用ハンドラーの登録
        regHandler(
            WEB3AccInfoAppPlugin.class,
            WEB3AccInfoEquityCommissionCourseChangeCancelCompleteRequest.class, 
            WEB3AccInfoEquityCommissionCourseRegistHandler.class, 
            "registConcelComplete");

        //管理者お客様情報ﾊｲﾊﾟｰﾎﾞｯｸｽ手数料変更ﾀﾞｳﾝﾛｰﾄﾞ 用ハンドラーの登録
        regHandler(
            WEB3AccInfoAppPlugin.class,
            WEB3AdminAccInfoHyperBoxCommissionChangeInquiryRequest.class, 
            WEB3AdminAccInfoHyperBoxCommissionChangeDownloadHandler.class, 
            "inputScreenDisplay");

        //管理者お客様情報ﾊｲﾊﾟｰﾎﾞｯｸｽ手数料変更ﾀﾞｳﾝﾛｰﾄﾞ 用ハンドラーの登録
        regHandler(
            WEB3AccInfoAppPlugin.class,
            WEB3AdminAccInfoHyperBoxCommissionChangeDownloadRequest.class, 
            WEB3AdminAccInfoHyperBoxCommissionChangeDownloadHandler.class, 
            "downloadScreenDisplay");

        //管理者お客様情報ﾊｲﾊﾟｰﾎﾞｯｸｽ手数料変更ﾀﾞｳﾝﾛｰﾄﾞ 用ハンドラーの登録
        regHandler(
            WEB3AccInfoAppPlugin.class,
            WEB3AdminAccInfoHyperBoxCommissionChangeFileDownloadRequest.class, 
            WEB3AdminAccInfoHyperBoxCommissionChangeDownloadHandler.class, 
            "hyperBoxCommissionChangeDownload");

        //管理者お客様情報ﾒｰﾙｱﾄﾞﾚｽ全件ﾀﾞｳﾝﾛｰﾄﾞ 用ハンドラーの登録
        regHandler(
            WEB3AccInfoAppPlugin.class,        
            WEB3AdminAccInfoMailAddressInquiryRequest.class, 
            WEB3AdminAccInfoMailAddressDownloadHandler.class, 
            "inputScreenDisplay");
                        
        //管理者お客様情報ﾒｰﾙｱﾄﾞﾚｽ全件ﾀﾞｳﾝﾛｰﾄﾞ 用ハンドラーの登録
        regHandler(
            WEB3AccInfoAppPlugin.class,
            WEB3AdminAccInfoMailAddressDownloadRequest.class, 
            WEB3AdminAccInfoMailAddressDownloadHandler.class, 
            "downloadScreenDisplay");

        //管理者お客様情報ﾒｰﾙｱﾄﾞﾚｽ全件ﾀﾞｳﾝﾛｰﾄﾞ 用ハンドラーの登録
        regHandler(
            WEB3AccInfoAppPlugin.class,
            WEB3AdminAccInfoMailAddressFileDownloadRequest.class, 
            WEB3AdminAccInfoMailAddressDownloadHandler.class, 
            "mailAddressDownload");

        //管理者お客様情報メールアドレス変更 用ハンドラーの登録
        regHandler(
            WEB3AccInfoAppPlugin.class,
            WEB3AdminAccInfoMailAddressChangeInputRequest.class, 
            WEB3AdminAccInfoMailAddressChangeHandler.class, 
            "inputScreenDisplay");

        //管理者お客様情報メールアドレス変更 用ハンドラーの登録
        regHandler(
            WEB3AccInfoAppPlugin.class,
            WEB3AdminAccInfoMailAddressChangeConfirmRequest.class, 
            WEB3AdminAccInfoMailAddressChangeHandler.class, 
            "mailAddressChangeConfirm");

        //管理者お客様情報メールアドレス変更 用ハンドラーの登録
        regHandler(
            WEB3AccInfoAppPlugin.class,
            WEB3AdminAccInfoMailAddressChangeCompleteRequest.class, 
            WEB3AdminAccInfoMailAddressChangeHandler.class, 
            "mailAddressChangeComplete");

        //管理者お客様情報ﾒｰﾙｱﾄﾞﾚｽ変更顧客ﾀﾞｳﾝﾛｰﾄﾞ 用ハンドラーの登録
        regHandler(
            WEB3AccInfoAppPlugin.class,
            WEB3AdminAccInfoMailAddressChangeAccountInquiryRequest.class, 
            WEB3AdminAccInfoMailAddressChangeAccountDownloadHandler.class, 
            "inputScreenDisplay");

        //管理者お客様情報ﾒｰﾙｱﾄﾞﾚｽ変更顧客ﾀﾞｳﾝﾛｰﾄﾞ 用ハンドラーの登録
        regHandler(
            WEB3AccInfoAppPlugin.class,
            WEB3AdminAccInfoMailAddressChangeAccountDownloadRequest.class, 
            WEB3AdminAccInfoMailAddressChangeAccountDownloadHandler.class, 
            "downloadScreenDisplay");

        //管理者お客様情報ﾒｰﾙｱﾄﾞﾚｽ変更顧客ﾀﾞｳﾝﾛｰﾄﾞ 用ハンドラーの登録
        regHandler(
            WEB3AccInfoAppPlugin.class,
            WEB3AdminAccInfoMailAddressChangeAccountFileDownloadRequest.class, 
            WEB3AdminAccInfoMailAddressChangeAccountDownloadHandler.class, 
            "mailAddressChangeAccountDownload");

        //管理者お客様情報携帯番号・勤務先情報変更 用ハンドラーの登録
        regHandler(
            WEB3AccInfoAppPlugin.class,
            WEB3AdminAccInfoMobileOfficeRegistInputRequest.class, 
            WEB3AdminAccInfoMobileOfficeChangeHandler.class, 
            "inputScreenDisplay");

        //管理者お客様情報携帯番号・勤務先情報変更 用ハンドラーの登録
        regHandler(
            WEB3AccInfoAppPlugin.class,
            WEB3AdminAccInfoMobileOfficeRegistConfirmRequest.class, 
            WEB3AdminAccInfoMobileOfficeChangeHandler.class, 
            "changeConfirm");

        //管理者お客様情報携帯番号・勤務先情報変更 用ハンドラーの登録
        regHandler(
            WEB3AccInfoAppPlugin.class,
            WEB3AdminAccInfoMobileOfficeRegistCompleteRequest.class, 
            WEB3AdminAccInfoMobileOfficeChangeHandler.class, 
            "changeComplete");

        //管理者お客様情報携帯番号・勤務先情報変更申込顧客問合せ 用ハンドラーの登録
        regHandler(
            WEB3AccInfoAppPlugin.class,
            WEB3AdminAccInfoMobileOfficeRegistAccountInquiryRequest.class, 
            WEB3AdminAccInfoMobileOfficeRegistAccountInquiryHandler.class, 
            "inputScreenDisplay");

        //管理者お客様情報携帯番号・勤務先情報変更申込顧客問合せ 用ハンドラーの登録
        regHandler(
            WEB3AccInfoAppPlugin.class,
            WEB3AdminAccInfoMobileOfficeRegistAccountRequest.class, 
            WEB3AdminAccInfoMobileOfficeRegistAccountInquiryHandler.class, 
            "registAccountListDisplay");

        //管理者お客様情報顧客基本情報問合せ 用ハンドラーの登録
        regHandler(
            WEB3AccInfoAppPlugin.class,
            WEB3AdminAccInfoAccountBaseInfoResultRequest.class, 
            WEB3AdminAccInfoAccountBaseInfoInquiryHandler.class, 
            "accountBaseInfoInquiry");

        //管理者お客様情報顧客基本情報問合せ 用ハンドラーの登録
        regHandler(
            WEB3AccInfoAppPlugin.class,
            WEB3AdminAccInfoAccountBaseInfoInquiryRequest.class, 
            WEB3AdminAccInfoAccountBaseInfoInquiryHandler.class, 
            "inputScreenDisplay");

        //管理者お客様情報手数料変更申込顧客ﾀﾞｳﾝﾛｰﾄﾞ 用ハンドラーの登録
        regHandler(
            WEB3AccInfoAppPlugin.class,
            WEB3AdminAccInfoCommissionChangeAccountDownloadInquiryRequest.class, 
            WEB3AdminAccInfoCommissionRegistAccountDownloadHandler.class, 
            "inputScreenDisplay");

        //管理者お客様情報手数料変更申込顧客ﾀﾞｳﾝﾛｰﾄﾞ 用ハンドラーの登録
        regHandler(
            WEB3AccInfoAppPlugin.class,
            WEB3AdminAccInfoCommissionChangeAccountDownloadRequest.class, 
            WEB3AdminAccInfoCommissionRegistAccountDownloadHandler.class, 
            "downloadScreenDisplay");

        //管理者お客様情報手数料変更申込顧客ﾀﾞｳﾝﾛｰﾄﾞ 用ハンドラーの登録
        regHandler(
            WEB3AccInfoAppPlugin.class,
            WEB3AdminAccInfoCommissionChangeAccountFileDownloadRequest.class, 
            WEB3AdminAccInfoCommissionRegistAccountDownloadHandler.class, 
            "commissionRegistAccountDownload");

        //管理者お客様情報手数料変更申込顧客一覧問合せ 用ハンドラーの登録
        regHandler(
            WEB3AccInfoAppPlugin.class,
            WEB3AdminAccInfoCommissionChangeAccountListInquiryInputRequest.class, 
            WEB3AdminAccInfoCommissionRegistAccountListInquiryHandler.class, 
            "inputScreenDisplay");

        //管理者お客様情報手数料変更申込顧客一覧問合せ 用ハンドラーの登録
        regHandler(
            WEB3AccInfoAppPlugin.class,
            WEB3AdminAccInfoCommissionChangeAccountListInquiryRequest.class, 
            WEB3AdminAccInfoCommissionRegistAccountListInquiryHandler.class, 
            "commissionRegistAccountListDisplay");

        //管理者お客様情報手数料変更申込顧客問合せ 用ハンドラーの登録
        regHandler(
            WEB3AccInfoAppPlugin.class,
            WEB3AdminAccInfoCommissionChangeAccountInquiryInputRequest.class, 
            WEB3AdminAccInfoCommissionRegistAccountInquiryHandler.class, 
            "inputScreenDisplay");

        //管理者お客様情報手数料変更申込顧客問合せ 用ハンドラーの登録
        regHandler(
            WEB3AccInfoAppPlugin.class,
            WEB3AdminAccInfoCommissionChangeAccountInquiryRequest.class, 
            WEB3AdminAccInfoCommissionRegistAccountInquiryHandler.class, 
            "registListDisplay");

        //管理者お客様情報専用振込先口座ｱｯﾌﾟﾛｰﾄﾞ 用ハンドラーの登録
        regHandler(
            WEB3AccInfoAppPlugin.class,
            WEB3AdminAccInfoExclusiveTransferAccountUploadInputRequest.class, 
            WEB3AdminAccInfoExclusiveTransferAccountUploadHandler.class, 
            "uploadScreenDisplay");

        //管理者お客様情報専用振込先口座ｱｯﾌﾟﾛｰﾄﾞ 用ハンドラーの登録
        regHandler(
            WEB3AccInfoAppPlugin.class,
            WEB3AdminAccInfoExclusiveTransferAccountUploadConfirmRequest.class, 
            WEB3AdminAccInfoExclusiveTransferAccountUploadHandler.class, 
            "uploadFileConfirm");

        //管理者お客様情報専用振込先口座ｱｯﾌﾟﾛｰﾄﾞ 用ハンドラーの登録
        regHandler(
            WEB3AccInfoAppPlugin.class,
            WEB3AdminAccInfoExclusiveTransferAccountUploadCompleteRequest.class, 
            WEB3AdminAccInfoExclusiveTransferAccountUploadHandler.class, 
            "exclusiveTransferAccountUpload");

        //管理者お客様情報専用振込先口座ｱｯﾌﾟﾛｰﾄﾞ 用ハンドラーの登録
        regHandler(
            WEB3AccInfoAppPlugin.class,
            WEB3AdminAccInfoExclusiveTransferAccountUploadCancelRequest.class, 
            WEB3AdminAccInfoExclusiveTransferAccountUploadHandler.class, 
            "uploadCancel");

        //管理者お客様情報専用振込先口座変更 用ハンドラーの登録
        regHandler(
            WEB3AccInfoAppPlugin.class,
            WEB3AdminAccInfoExclusiveTransferAccountChangeInputRequest.class, 
            WEB3AdminAccInfoExclusiveTransferAccountChangeHandler.class, 
            "inputScreenDisplay");

        //管理者お客様情報専用振込先口座変更 用ハンドラーの登録
        regHandler(
            WEB3AccInfoAppPlugin.class,
            WEB3AdminAccInfoExclusiveTransferAccountChangeConfirmRequest.class, 
            WEB3AdminAccInfoExclusiveTransferAccountChangeHandler.class, 
            "changeConfirm");

        //管理者お客様情報専用振込先口座変更 用ハンドラーの登録
        regHandler(
            WEB3AccInfoAppPlugin.class,
            WEB3AdminAccInfoExclusiveTransferAccountChangeCompleteRequest.class, 
            WEB3AdminAccInfoExclusiveTransferAccountChangeHandler.class, 
            "changeComplete");

        //管理者お客様情報停止状況変更 用ハンドラーの登録
        regHandler(
            WEB3AccInfoAppPlugin.class,
            WEB3AdminAccInfoStopStateChangeInputRequest.class, 
            WEB3AdminAccInfoStopStateChangeHandler.class, 
            "inputScreenDisplay");

        //管理者お客様情報停止状況変更 用ハンドラーの登録
        regHandler(
            WEB3AccInfoAppPlugin.class,
            WEB3AdminAccInfoStopStateChangeConfirmRequest.class, 
            WEB3AdminAccInfoStopStateChangeHandler.class, 
            "changeConfirm");
        
        //管理者お客様情報停止状況変更 用ハンドラーの登録
        regHandler(
            WEB3AccInfoAppPlugin.class,
            WEB3AdminAccInfoStopStateChangeCompleteRequest.class, 
            WEB3AdminAccInfoStopStateChangeHandler.class, 
            "changeComplete");
            
        //管理者お客様情報内部者情報変更 用ハンドラーの登録
        regHandler(
            WEB3AccInfoAppPlugin.class,
            WEB3AdminAccInfoInsiderInfoChangeInputRequest.class, 
            WEB3AdminAccInfoInsiderInfoChangeHandler.class, 
            "inputScreenDisplay");

        //管理者お客様情報内部者情報変更 用ハンドラーの登録
        regHandler(
            WEB3AccInfoAppPlugin.class,
            WEB3AdminAccInfoInsiderInfoChangeConfirmRequest.class, 
            WEB3AdminAccInfoInsiderInfoChangeHandler.class, 
            "changeConfirm");
        
        //管理者お客様情報内部者情報変更 用ハンドラーの登録
        regHandler(
            WEB3AccInfoAppPlugin.class,
            WEB3AdminAccInfoInsiderInfoChangeCompleteRequest.class, 
            WEB3AdminAccInfoInsiderInfoChangeHandler.class, 
            "changeComplete");
            
        //お客様情報基本情報照会 用ハンドラーの登録
        regHandler(
            WEB3AccInfoAppPlugin.class,
            WEB3AccInfoAccountBaseInfoReferenceRequest.class, 
            WEB3AccInfoBaseInfoReferenceHandler.class, 
            "baseInfoReference");
            
        //お客様情報携帯番号・勤務先情報変更申込 用ハンドラーの登録
        regHandler(
            WEB3AccInfoAppPlugin.class,
            WEB3AccInfoMobileOfficeRegistInputRequest.class, 
            WEB3AccInfoMobileOfficeRegistHandler.class, 
            "inputScreenDisplay");
        
        //お客様情報携帯番号・勤務先情報変更申込 用ハンドラーの登録
        regHandler(
            WEB3AccInfoAppPlugin.class,
            WEB3AccInfoMobileOfficeRegistConfirmRequest.class, 
            WEB3AccInfoMobileOfficeRegistHandler.class, 
            "registConfirm");
            
        //お客様情報携帯番号・勤務先情報変更申込 用ハンドラーの登録
        regHandler(
            WEB3AccInfoAppPlugin.class,
            WEB3AccInfoMobileOfficeRegistCompleteRequest.class, 
            WEB3AccInfoMobileOfficeRegistHandler.class, 
            "registComplete");
            
        //お客様情報約定／未約定メール配信設定変更 用ハンドラーの登録
        regHandler(
            WEB3AccInfoAppPlugin.class,
            WEB3AccInfoExecMailDistributionChangeCompleteRequest.class, 
            WEB3AccInfoExecMailDistributionChangeHandler.class, 
            "execMailDistributionChangeComplete");
            
        //お客様情報暗証番号変更 用ハンドラーの登録
        regHandler(
            WEB3AccInfoAppPlugin.class,
            WEB3AccInfoPasswordChangeInputRequest.class, 
            WEB3AccInfoPasswordChangeHandler.class, 
            "inputScreenDisplay");
            
        //お客様情報暗証番号変更 用ハンドラーの登録
        regHandler(
            WEB3AccInfoAppPlugin.class,
            WEB3AccInfoPasswordChangeCompleteRequest.class, 
            WEB3AccInfoPasswordChangeHandler.class, 
            "changePassword");
            
        //管理者お客様情報パスワードリセット 用ハンドラーの登録
        regHandler(
            WEB3AccInfoAppPlugin.class,
            WEB3AdminAccInfoLoginPasswordResetInputRequest.class, 
            WEB3AdminAccInfoLoginPasswordResetHandler.class, 
            "inputScreenDisplay");
            
        //管理者お客様情報パスワードリセット 用ハンドラーの登録
        regHandler(
            WEB3AccInfoAppPlugin.class,
            WEB3AdminAccInfoLoginPasswordResetRequest.class, 
            WEB3AdminAccInfoLoginPasswordResetHandler.class, 
            "loginPasswordReset");
            
        //管理者お客様情報ﾊﾟｽﾜｰﾄﾞ変更顧客ﾀﾞｳﾝﾛｰﾄﾞ 用ハンドラーの登録
        regHandler(
            WEB3AccInfoAppPlugin.class,
            WEB3AdminAccInfoLoginPasswordChangeAccountInquiryRequest.class, 
            WEB3AdminAccInfoLoginPasswordChangeAccountDownloadHandler.class, 
            "inputScreenDisplay");
            
        //管理者お客様情報ﾊﾟｽﾜｰﾄﾞ変更顧客ﾀﾞｳﾝﾛｰﾄﾞ 用ハンドラーの登録
        regHandler(
            WEB3AccInfoAppPlugin.class,
            WEB3AdminAccInfoLoginPasswordChangeAccountDownloadRequest.class, 
            WEB3AdminAccInfoLoginPasswordChangeAccountDownloadHandler.class, 
            "downloadScreenDisplay");
            
        //管理者お客様情報ﾊﾟｽﾜｰﾄﾞ変更顧客ﾀﾞｳﾝﾛｰﾄﾞ 用ハンドラーの登録
        regHandler(
            WEB3AccInfoAppPlugin.class,
            WEB3AdminAccInfoLoginPasswordChangeAccountFileDownloadRequest.class, 
            WEB3AdminAccInfoLoginPasswordChangeAccountDownloadHandler.class, 
            "loginPasswordChangeAccountDownload");
            
        //管理者お客様情報ログインエラーリセット 用ハンドラーの登録
        regHandler(
            WEB3AccInfoAppPlugin.class,
            WEB3AdminAccInfoLoginErrorResetInputRequest.class, 
            WEB3AdminAccInfoLoginErrorResetHandler.class, 
            "inputScreenDisplay");
            
        //管理者お客様情報ログインエラーリセット 用ハンドラーの登録
        regHandler(
            WEB3AccInfoAppPlugin.class,
            WEB3AdminAccInfoLoginErrorResetRequest.class, 
            WEB3AdminAccInfoLoginErrorResetHandler.class, 
            "loginErrorReset");
            
        //管理者お客様情報暗証番号リセット 用ハンドラーの登録
        regHandler(
            WEB3AccInfoAppPlugin.class,
            WEB3AdminAccInfoPasswordResetInputRequest.class, 
            WEB3AdminAccInfoPasswordResetHandler.class, 
            "getInputScreen");
            
        //管理者お客様情報暗証番号リセット 用ハンドラーの登録
        regHandler(
            WEB3AccInfoAppPlugin.class,
            WEB3AdminAccInfoPasswordResetRequest.class, 
            WEB3AdminAccInfoPasswordResetHandler.class, 
            "passwordReset");
            
        //管理者お客様情報暗証番号変更顧客ﾀﾞｳﾝﾛｰﾄﾞ 用ハンドラーの登録
        regHandler(
            WEB3AccInfoAppPlugin.class,
            WEB3AdminAccInfoPasswordChangeAccountInquiryRequest.class, 
            WEB3AdminAccInfoPasswordChangeAccountDownloadHandler.class, 
            "inputScreenDisplay");
            
        //管理者お客様情報暗証番号変更顧客ﾀﾞｳﾝﾛｰﾄﾞ 用ハンドラーの登録
        regHandler(
            WEB3AccInfoAppPlugin.class,
            WEB3AdminAccInfoPasswordChangeAccountDownloadRequest.class, 
            WEB3AdminAccInfoPasswordChangeAccountDownloadHandler.class, 
            "downloadScreenDisplay");
            
        //管理者お客様情報暗証番号変更顧客ﾀﾞｳﾝﾛｰﾄﾞ 用ハンドラーの登録
        regHandler(
            WEB3AccInfoAppPlugin.class,
            WEB3AdminAccInfoPasswordChangeAccountFileDownloadRequest.class, 
            WEB3AdminAccInfoPasswordChangeAccountDownloadHandler.class, 
            "passwordChangeAccountDownload");
        
        //管理者お客様情報内部者情報一覧 用ハンドラーの登録
        regHandler(
            WEB3AccInfoAppPlugin.class,
            WEB3AdminAccInfoInsiderInfoInputRequest.class, 
            WEB3AdminAccInfoInsiderInfoListHandler.class, 
            "inputScreenDisplay");
            
        //管理者お客様情報内部者情報一覧 用ハンドラーの登録
        regHandler(
            WEB3AccInfoAppPlugin.class,
            WEB3AdminAccInfoInsiderInfoListRequest.class, 
            WEB3AdminAccInfoInsiderInfoListHandler.class, 
            "listScreenDisplay");
            
        //管理者お客まさ情報案内メール配信指示 用ハンドラーの登録
        regHandler(
            WEB3AccInfoAppPlugin.class,
            WEB3AdminAccInfoMailDistributionRequest.class, 
            WEB3AdminAccInfoMailDistributionHandler.class, 
            "getMailDistributionScreen");
            
        //管理者お客まさ情報案内メール配信指示 用ハンドラーの登録
        regHandler(
            WEB3AccInfoAppPlugin.class,
            WEB3AdminAccInfoMailDistributionConfirmRequest.class, 
            WEB3AdminAccInfoMailDistributionHandler.class, 
            "validateMailDistribution");
            
        //管理者お客まさ情報案内メール配信指示 用ハンドラーの登録
        regHandler(
            WEB3AccInfoAppPlugin.class,
            WEB3AdminAccInfoMailDistributionCompleteRequest.class, 
            WEB3AdminAccInfoMailDistributionHandler.class, 
            "submintMailDistribution");
            
        //管理者お客まさ情報案内メール配信指示 用ハンドラーの登録
        regHandler(
            WEB3AccInfoAppPlugin.class,
            WEB3AdminAccInfoMailDistributionCancelConfirmRequest.class, 
            WEB3AdminAccInfoMailDistributionHandler.class, 
            "validateMailDistributionCancle");
            
        //管理者お客まさ情報案内メール配信指示 用ハンドラーの登録
        regHandler(
            WEB3AccInfoAppPlugin.class,
            WEB3AdminAccInfoMailDistributionCancelCompleteRequest.class, 
            WEB3AdminAccInfoMailDistributionHandler.class, 
            "submitMailDistributionCancle");
        
        //ロック登録解除受付ハンドラーの登録
        regHandler(
            WEB3AccInfoAppPlugin.class,
            WEB3AccInfoLockRegistReleaseAcceptRequest.class, 
            WEB3AccInfoLockRegistReleaseAcceptHandler.class, 
            "lockRegistReleaseAccept");
        
        //管理者お客様情報ロック顧客問合せ一覧入力ハンドラーの登録
        regHandler(
                WEB3AccInfoAppPlugin.class,
                WEB3AdminAccInfoLockAccountSearchInputRequest.class, 
                WEB3AdminAccInfoLockAccountListHandler.class, 
                "inputScreenDisplay");
        
        //管理者お客様情報ロック顧客問合せ一覧ハンドラーの登録
        regHandler(
                WEB3AccInfoAppPlugin.class,
                WEB3AdminAccInfoLockAccountSearchListRequest.class, 
                WEB3AdminAccInfoLockAccountListHandler.class, 
                "getLockAccountRegistList");
        
        //メールアドレスアップロード画面表示処理ハンドラーの登録
        regHandler(
                WEB3AccInfoAppPlugin.class,
                WEB3AdminAccInfoMailAddressUploadInputRequest.class, 
                WEB3AdminAccInfoMailAddressUploadHandler.class, 
                "uploadScreenDisplay");
        
        //管理者お客様情報メールアドレスｱｯﾌﾟﾛｰﾄﾞ確認処理ハンドラーの登録
        regHandler(
            WEB3AccInfoAppPlugin.class,
            WEB3AdminAccInfoMailAddressUploadConfirmRequest.class, 
            WEB3AdminAccInfoMailAddressUploadHandler.class, 
            "uploadFileConfirm");
        
        //管理者お客様情報メールアドレスｱｯﾌﾟﾛｰﾄﾞ完了処理ハンドラーの登録
        regHandler(
            WEB3AccInfoAppPlugin.class,
            WEB3AdminAccInfoMailAddressUploadCompleteRequest.class, 
            WEB3AdminAccInfoMailAddressUploadHandler.class, 
            "mailAddressUpload");
        
        //管理者お客様情報メールアドレスｱｯﾌﾟﾛｰﾄﾞ処理ハンドラーの登録
        regHandler(
            WEB3AccInfoAppPlugin.class,
            WEB3AdminAccInfoMailAddressUploadCancelRequest.class, 
            WEB3AdminAccInfoMailAddressUploadHandler.class, 
            "uploadCancel");
        
        //ログインロック顧客検索ﾊﾝﾄﾞﾗーの登録
        regHandler(
            WEB3AccInfoAppPlugin.class,
            WEB3AdminAccInfoAccEstablishSearchInputRequest.class, 
            WEB3AdminAccInfoAccEstablishSearchHandler.class, 
            "getInputScreen");
        
        regHandler(
            WEB3AccInfoAppPlugin.class,
            WEB3AdminAccInfoAccEstablishSearchListRequest.class, 
            WEB3AdminAccInfoAccEstablishSearchHandler.class, 
            "getListScreen");
        
        regHandler(
            WEB3AccInfoAppPlugin.class,
            WEB3AdminAccInfoAccEstablishSearchDLRequest.class, 
            WEB3AdminAccInfoAccEstablishSearchHandler.class, 
            "getDownLoadFile");

        //一括判定確認処理
        regHandler(
            WEB3AccInfoAppPlugin.class,
            WEB3AdminAccInfoMobileOfficeRegAccConfirmRequest.class,
            WEB3AdminAccInfoMobileOfficeRegistAccountInquiryHandler.class,
            "judgementConfirmScreenDisplay");

        //一括判定完了処理
        regHandler(
            WEB3AccInfoAppPlugin.class,
            WEB3AdminAccInfoMobileOfficeRegAccCompleteRequest.class,
            WEB3AdminAccInfoMobileOfficeRegistAccountInquiryHandler.class,
            "judgementComplete");
        
        //個別顧客指定一覧ハンドラーの登録
        regHandler(
            WEB3AccInfoAppPlugin.class,
            WEB3AdminAccInfoCampaignIndiviListRequest.class,
            WEB3AdminAccInfoCampaignIndiviListHandler.class,
            "getListScreen");
        
        //個別顧客指定変更ハンドラーの登録
        regHandler(
            WEB3AccInfoAppPlugin.class,
            WEB3AdminAccInfoCampaignIndiviInputRequest.class,
            WEB3AdminAccInfoCampaignIndiviChangeHandler.class,
            "inputScreenDisplay");
        
        //個別顧客指定変更ハンドラーの登録
        regHandler(
            WEB3AccInfoAppPlugin.class,
            WEB3AdminAccInfoCampaignIndiviConfirmRequest.class,
            WEB3AdminAccInfoCampaignIndiviChangeHandler.class,
            "IndiviChangeConfirm");
        
        //個別顧客指定変更ハンドラーの登録
        regHandler(
            WEB3AccInfoAppPlugin.class,
            WEB3AdminAccInfoCampaignIndiviCompleteRequest.class,
            WEB3AdminAccInfoCampaignIndiviChangeHandler.class,
            "IndiviChangeComplete");
        
        //口座開設条件指定一覧ハンドラーの登録
        regHandler(
            WEB3AccInfoAppPlugin.class,
            WEB3AdminAccInfoCampaignAccOpenListRequest.class,
            WEB3AdminAccInfoCampaignAccOpenListHandler.class,
            "getListScreen");
        
        //口座開設条件指定変更ハンドラーの登録
        regHandler(
            WEB3AccInfoAppPlugin.class,
            WEB3AdminAccInfoCampaignAccOpenInputRequest.class,
            WEB3AdminAccInfoCampaignAccOpenChangeHandler.class,
            "inputScreenProcess");
        
        //口座開設条件指定変更ハンドラーの登録
        regHandler(
            WEB3AccInfoAppPlugin.class,
            WEB3AdminAccInfoCampaignAccOpenConfirmRequest.class,
            WEB3AdminAccInfoCampaignAccOpenChangeHandler.class,
            "changeConfirmScreenProcess");
        
        //口座開設条件指定変更ハンドラーの登録
        regHandler(
            WEB3AccInfoAppPlugin.class,
            WEB3AdminAccInfoCampaignAccOpenCompleteRequest.class,
            WEB3AdminAccInfoCampaignAccOpenChangeHandler.class,
            "changeCompleteScreenProcess");
        
        //登録顧客照会ハンドラーの登録
        regHandler(
            WEB3AccInfoAppPlugin.class,
            WEB3AdminAccInfoCampaignRegistAccListRequest.class,
            WEB3AdminAccInfoCampaignRegistAccListHandler.class,
            "listScreenShow");
        
        //電子交付サービス登録・変更ハンドラの登録
        regHandler(
            WEB3AccInfoAppPlugin.class,
            WEB3AccInfoElecDeliveryRegisterChangeInputRequest.class,
            WEB3AccInfoElecDeliveryRegisterChangeHandler.class,
            "inputScreenDisplay");
        regHandler(
            WEB3AccInfoAppPlugin.class,
            WEB3AccInfoElecDeliveryRegisterChangeCompleteRequest.class,
            WEB3AccInfoElecDeliveryRegisterChangeHandler.class,
            "elecDeliveryRegisterChange");
        regHandler(
            WEB3AccInfoAppPlugin.class,
            WEB3AccInfoElecDeliveryApyReferenceRequest.class,
            WEB3AccInfoElecDeliveryRegisterChangeHandler.class,
            "elecDeliveryApyReference");
        log.exiting(STR_METHOD_NAME);
    }
}@


1.1
log
@*** empty log message ***
@
text
@d6 1
d21 1
d63 1
d105 1
d408 5
d615 5
d793 5
d1228 15
d1908 16
@

