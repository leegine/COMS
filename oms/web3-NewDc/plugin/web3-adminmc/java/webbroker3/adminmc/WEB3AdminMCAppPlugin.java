head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.14.03.31.05;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	4584d7d7e626866;
filename	WEB3AdminMCAppPlugin.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : Webbroker3-Adminmc プラグイン(WEB3AdminMCAppPlugin.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2004/11/04 温 顕 法@ (中訊) 新規作成
*/

package webbroker3.adminmc;

import com.fitechlabs.xtrade.kernel.boot.KernelPlugin;
import com.fitechlabs.xtrade.kernel.boot.Plugin;
import com.fitechlabs.xtrade.kernel.boot.Services;
import com.fitechlabs.xtrade.kernel.interceptor.TransactionalInterceptor;

import webbroker3.adminmc.handler.WEB3AdminMCAdminChangeHandler;
import webbroker3.adminmc.handler.WEB3AdminMCAdminDeleteHandler;
import webbroker3.adminmc.handler.WEB3AdminMCAdminListHandler;
import webbroker3.adminmc.handler.WEB3AdminMCAdminMenuSubMenuDisplayHandler;
import webbroker3.adminmc.handler.WEB3AdminMCAdminPermGrpChangeHandler;
import webbroker3.adminmc.handler.WEB3AdminMCAdminPermGrpDeleteHandler;
import webbroker3.adminmc.handler.WEB3AdminMCAdminPermGrpListHandler;
import webbroker3.adminmc.handler.WEB3AdminMCAdminPermGrpRegistHandler;
import webbroker3.adminmc.handler.WEB3AdminMCAdminPwdLockCancelHandler;
import webbroker3.adminmc.handler.WEB3AdminMCAdminRegistHandler;
import webbroker3.adminmc.handler.WEB3AdminMCCCOperatorChangeHandler;
import webbroker3.adminmc.handler.WEB3AdminMCCCOperatorDeleteHandler;
import webbroker3.adminmc.handler.WEB3AdminMCCCOperatorListHandler;
import webbroker3.adminmc.handler.WEB3AdminMCCCOperatorPwdLockCancelHandler;
import webbroker3.adminmc.handler.WEB3AdminMCCCOperatorRegistHandler;
import webbroker3.adminmc.message.WEB3AdminMCAdminChangeCompleteRequest;
import webbroker3.adminmc.message.WEB3AdminMCAdminChangeCompleteResponse;
import webbroker3.adminmc.message.WEB3AdminMCAdminChangeConfirmRequest;
import webbroker3.adminmc.message.WEB3AdminMCAdminChangeConfirmResponse;
import webbroker3.adminmc.message.WEB3AdminMCAdminChangeInputRequest;
import webbroker3.adminmc.message.WEB3AdminMCAdminChangeInputResponse;
import webbroker3.adminmc.message.WEB3AdminMCAdminDeleteCompleteRequest;
import webbroker3.adminmc.message.WEB3AdminMCAdminDeleteCompleteResponse;
import webbroker3.adminmc.message.WEB3AdminMCAdminDeleteConfirmRequest;
import webbroker3.adminmc.message.WEB3AdminMCAdminDeleteConfirmResponse;
import webbroker3.adminmc.message.WEB3AdminMCAdminListInputRequest;
import webbroker3.adminmc.message.WEB3AdminMCAdminListInputResponse;
import webbroker3.adminmc.message.WEB3AdminMCAdminListRequest;
import webbroker3.adminmc.message.WEB3AdminMCAdminListResponse;
import webbroker3.adminmc.message.WEB3AdminMCAdminMenuSubMenuDisplayRequest;
import webbroker3.adminmc.message.WEB3AdminMCAdminMenuSubMenuDisplayResponse;
import webbroker3.adminmc.message.WEB3AdminMCAdminPermGrpChangeCompleteRequest;
import webbroker3.adminmc.message.WEB3AdminMCAdminPermGrpChangeCompleteResponse;
import webbroker3.adminmc.message.WEB3AdminMCAdminPermGrpChangeConfirmRequest;
import webbroker3.adminmc.message.WEB3AdminMCAdminPermGrpChangeConfirmResponse;
import webbroker3.adminmc.message.WEB3AdminMCAdminPermGrpChangeInputRequest;
import webbroker3.adminmc.message.WEB3AdminMCAdminPermGrpChangeInputResponse;
import webbroker3.adminmc.message.WEB3AdminMCAdminPermGrpDeleteCompleteRequest;
import webbroker3.adminmc.message.WEB3AdminMCAdminPermGrpDeleteCompleteResponse;
import webbroker3.adminmc.message.WEB3AdminMCAdminPermGrpDeleteConfirmRequest;
import webbroker3.adminmc.message.WEB3AdminMCAdminPermGrpDeleteConfirmResponse;
import webbroker3.adminmc.message.WEB3AdminMCAdminPermGrpDetailsRequest;
import webbroker3.adminmc.message.WEB3AdminMCAdminPermGrpDetailsResponse;
import webbroker3.adminmc.message.WEB3AdminMCAdminPermGrpListRequest;
import webbroker3.adminmc.message.WEB3AdminMCAdminPermGrpListResponse;
import webbroker3.adminmc.message.WEB3AdminMCAdminPermGrpRegistCompleteRequest;
import webbroker3.adminmc.message.WEB3AdminMCAdminPermGrpRegistCompleteResponse;
import webbroker3.adminmc.message.WEB3AdminMCAdminPermGrpRegistConfirmRequest;
import webbroker3.adminmc.message.WEB3AdminMCAdminPermGrpRegistConfirmResponse;
import webbroker3.adminmc.message.WEB3AdminMCAdminPermGrpRegistInputRequest;
import webbroker3.adminmc.message.WEB3AdminMCAdminPermGrpRegistInputResponse;
import webbroker3.adminmc.message.WEB3AdminMCAdminPwdLockCancelCompleteRequest;
import webbroker3.adminmc.message.WEB3AdminMCAdminPwdLockCancelCompleteResponse;
import webbroker3.adminmc.message.WEB3AdminMCAdminPwdLockCancelConfirmRequest;
import webbroker3.adminmc.message.WEB3AdminMCAdminPwdLockCancelConfirmResponse;
import webbroker3.adminmc.message.WEB3AdminMCAdminRegistCompleteRequest;
import webbroker3.adminmc.message.WEB3AdminMCAdminRegistCompleteResponse;
import webbroker3.adminmc.message.WEB3AdminMCAdminRegistConfirmRequest;
import webbroker3.adminmc.message.WEB3AdminMCAdminRegistConfirmResponse;
import webbroker3.adminmc.message.WEB3AdminMCAdminRegistInputRequest;
import webbroker3.adminmc.message.WEB3AdminMCAdminRegistInputResponse;
import webbroker3.adminmc.message.WEB3AdminMCCCOperatorChangeCompleteRequest;
import webbroker3.adminmc.message.WEB3AdminMCCCOperatorChangeCompleteResponse;
import webbroker3.adminmc.message.WEB3AdminMCCCOperatorChangeConfirmRequest;
import webbroker3.adminmc.message.WEB3AdminMCCCOperatorChangeConfirmResponse;
import webbroker3.adminmc.message.WEB3AdminMCCCOperatorChangeInputRequest;
import webbroker3.adminmc.message.WEB3AdminMCCCOperatorChangeInputResponse;
import webbroker3.adminmc.message.WEB3AdminMCCCOperatorDeleteCompleteRequest;
import webbroker3.adminmc.message.WEB3AdminMCCCOperatorDeleteCompleteResponse;
import webbroker3.adminmc.message.WEB3AdminMCCCOperatorDeleteConfirmRequest;
import webbroker3.adminmc.message.WEB3AdminMCCCOperatorDeleteConfirmResponse;
import webbroker3.adminmc.message.WEB3AdminMCCCOperatorListInputRequest;
import webbroker3.adminmc.message.WEB3AdminMCCCOperatorListInputResponse;
import webbroker3.adminmc.message.WEB3AdminMCCCOperatorListRequest;
import webbroker3.adminmc.message.WEB3AdminMCCCOperatorListResponse;
import webbroker3.adminmc.message.WEB3AdminMCCCOperatorPwdLockCancelCompleteRequest;
import webbroker3.adminmc.message.WEB3AdminMCCCOperatorPwdLockCancelCompleteResponse;
import webbroker3.adminmc.message.WEB3AdminMCCCOperatorPwdLockCancelConfirmRequest;
import webbroker3.adminmc.message.WEB3AdminMCCCOperatorPwdLockCancelConfirmResponse;
import webbroker3.adminmc.message.WEB3AdminMCCCOperatorRegistCompleteRequest;
import webbroker3.adminmc.message.WEB3AdminMCCCOperatorRegistCompleteResponse;
import webbroker3.adminmc.message.WEB3AdminMCCCOperatorRegistConfirmRequest;
import webbroker3.adminmc.message.WEB3AdminMCCCOperatorRegistConfirmResponse;
import webbroker3.adminmc.message.WEB3AdminMCCCOperatorRegistInputRequest;
import webbroker3.adminmc.message.WEB3AdminMCCCOperatorRegistInputResponse;
import webbroker3.adminmc.service.delegate.WEB3AdminMCAdminChangeService;
import webbroker3.adminmc.service.delegate.WEB3AdminMCAdminDeleteService;
import webbroker3.adminmc.service.delegate.WEB3AdminMCAdminListService;
import webbroker3.adminmc.service.delegate.WEB3AdminMCAdminMenuSubMenuDisplayService;
import webbroker3.adminmc.service.delegate.WEB3AdminMCAdminPermGrpChangeService;
import webbroker3.adminmc.service.delegate.WEB3AdminMCAdminPermGrpDeleteService;
import webbroker3.adminmc.service.delegate.WEB3AdminMCAdminPermGrpListService;
import webbroker3.adminmc.service.delegate.WEB3AdminMCAdminPermGrpRegistService;
import webbroker3.adminmc.service.delegate.WEB3AdminMCAdminPermUnitCreateService;
import webbroker3.adminmc.service.delegate.WEB3AdminMCAdminPwdLockCancelService;
import webbroker3.adminmc.service.delegate.WEB3AdminMCAdminRegistService;
import webbroker3.adminmc.service.delegate.WEB3AdminMCAdminRegistUnitCreateService;
import webbroker3.adminmc.service.delegate.WEB3AdminMCCCOperatorChangeService;
import webbroker3.adminmc.service.delegate.WEB3AdminMCCCOperatorDeleteService;
import webbroker3.adminmc.service.delegate.WEB3AdminMCCCOperatorListService;
import webbroker3.adminmc.service.delegate.WEB3AdminMCCCOperatorPwdLockCancelService;
import webbroker3.adminmc.service.delegate.WEB3AdminMCCCOperatorRegistService;
import webbroker3.adminmc.service.delegate.WEB3AdminMCCCOperatorRegistUnitCreateService;
import webbroker3.adminmc.service.delegate.stdimpls.WEB3AdminMCAdminChangeServiceImpl;
import webbroker3.adminmc.service.delegate.stdimpls.WEB3AdminMCAdminDeleteServiceImpl;
import webbroker3.adminmc.service.delegate.stdimpls.WEB3AdminMCAdminListServiceImpl;
import webbroker3.adminmc.service.delegate.stdimpls.WEB3AdminMCAdminMenuSubMenuDisplayServiceImpl;
import webbroker3.adminmc.service.delegate.stdimpls.WEB3AdminMCAdminPermGrpChangeServiceImpl;
import webbroker3.adminmc.service.delegate.stdimpls.WEB3AdminMCAdminPermGrpDeleteServiceImpl;
import webbroker3.adminmc.service.delegate.stdimpls.WEB3AdminMCAdminPermGrpListServiceImpl;
import webbroker3.adminmc.service.delegate.stdimpls.WEB3AdminMCAdminPermGrpRegistServiceImpl;
import webbroker3.adminmc.service.delegate.stdimpls.WEB3AdminMCAdminPermUnitCreateServiceImpl;
import webbroker3.adminmc.service.delegate.stdimpls.WEB3AdminMCAdminPwdLockCancelServiceImpl;
import webbroker3.adminmc.service.delegate.stdimpls.WEB3AdminMCAdminRegistServiceImpl;
import webbroker3.adminmc.service.delegate.stdimpls.WEB3AdminMCAdminRegistUnitCreateServiceImpl;
import webbroker3.adminmc.service.delegate.stdimpls.WEB3AdminMCCCOperatorChangeServiceImpl;
import webbroker3.adminmc.service.delegate.stdimpls.WEB3AdminMCCCOperatorDeleteServiceImpl;
import webbroker3.adminmc.service.delegate.stdimpls.WEB3AdminMCCCOperatorListServiceImpl;
import webbroker3.adminmc.service.delegate.stdimpls.WEB3AdminMCCCOperatorPwdLockCancelServiceImpl;
import webbroker3.adminmc.service.delegate.stdimpls.WEB3AdminMCCCOperatorRegistServiceImpl;
import webbroker3.adminmc.service.delegate.stdimpls.WEB3AdminMCCCOperatorRegistUnitCreateServiceImpl;
import webbroker3.common.WEB3LogSysTimeInterceptor;
import webbroker3.util.WEB3LogUtility;

/**
 * Webbroker3-AdminMC プラグインクラス。
 *                                                                
 * @@author 温 顕 法@
 * @@version 1.0
 */
public final class WEB3AdminMCAppPlugin extends Plugin
{
    /**
     * ログユーティリティ。
     */
    private static WEB3LogUtility log = WEB3LogUtility.getInstance(WEB3AdminMCAppPlugin.class);

    /**
     * コンストラクタ。
     */
    public WEB3AdminMCAppPlugin()
    {
        String STR_METHOD_NAME = " WEB3AdminmcAppPlugin()";
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

        plug(WEB3AdminMCAppPlugin.class);

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
        //WEB3AdminMCMasterDatabaseExtensions.plug();

        // Service の登録処理 ----------------------

        //CCオペレータパスワードロック解除
        Services.registerService(WEB3AdminMCCCOperatorPwdLockCancelService.class, new WEB3AdminMCCCOperatorPwdLockCancelServiceImpl());
        
        //CCオペレータ一覧
        Services.registerService(WEB3AdminMCCCOperatorListService.class, new WEB3AdminMCCCOperatorListServiceImpl());

        //CCオペレータ削除
        Services.registerService(WEB3AdminMCCCOperatorDeleteService.class, new WEB3AdminMCCCOperatorDeleteServiceImpl());

        //CCオペレータ登録
        Services.registerService(WEB3AdminMCCCOperatorRegistService.class, new WEB3AdminMCCCOperatorRegistServiceImpl());

        //CCオペレータ変更
        Services.registerService(WEB3AdminMCCCOperatorChangeService.class, new WEB3AdminMCCCOperatorChangeServiceImpl());
        
        //メニュー制御共通サービス
        Services.registerService(WEB3AdminMCCCOperatorRegistUnitCreateService.class, new WEB3AdminMCCCOperatorRegistUnitCreateServiceImpl());
        Services.registerService(WEB3AdminMCAdminPermUnitCreateService.class, new WEB3AdminMCAdminPermUnitCreateServiceImpl());
        Services.registerService(WEB3AdminMCAdminRegistUnitCreateService.class, new WEB3AdminMCAdminRegistUnitCreateServiceImpl());
        
        //管理者サブメニュー表示
        Services.registerService(WEB3AdminMCAdminMenuSubMenuDisplayService.class, new WEB3AdminMCAdminMenuSubMenuDisplayServiceImpl());
        
        //管理者パスワードロック解除
        Services.registerService(WEB3AdminMCAdminPwdLockCancelService.class, new WEB3AdminMCAdminPwdLockCancelServiceImpl());

        //管理者一覧
        Services.registerService(WEB3AdminMCAdminListService.class, new WEB3AdminMCAdminListServiceImpl());

        //管理者権限グループ一覧
        Services.registerService(WEB3AdminMCAdminPermGrpListService.class, new WEB3AdminMCAdminPermGrpListServiceImpl());

        //管理者権限グループ削除
        Services.registerService(WEB3AdminMCAdminPermGrpDeleteService.class, new WEB3AdminMCAdminPermGrpDeleteServiceImpl());

        //管理者権限グループ登録
        Services.registerService(WEB3AdminMCAdminPermGrpRegistService.class, new WEB3AdminMCAdminPermGrpRegistServiceImpl());

        //管理者権限グループ変更
        Services.registerService(WEB3AdminMCAdminPermGrpChangeService.class, new WEB3AdminMCAdminPermGrpChangeServiceImpl());

        //管理者削除
        Services.registerService(WEB3AdminMCAdminDeleteService.class, new WEB3AdminMCAdminDeleteServiceImpl());

        //管理者登録
        Services.registerService(WEB3AdminMCAdminRegistService.class, new WEB3AdminMCAdminRegistServiceImpl());

        //管理者変更
        Services.registerService(WEB3AdminMCAdminChangeService.class, new WEB3AdminMCAdminChangeServiceImpl());



        // Service の Interceptor 設定処理 ----------------------
        // 自動トランザクション設定

        //CCオペレータパスワードロック解除
        Services.addInterceptor(WEB3AdminMCCCOperatorPwdLockCancelService.class, new WEB3AdminMCServiceInterceptor());
        Services.addInterceptor(WEB3AdminMCCCOperatorPwdLockCancelService.class, new WEB3LogSysTimeInterceptor());
        Services.addInterceptor(WEB3AdminMCCCOperatorPwdLockCancelService.class, new TransactionalInterceptor(TransactionalInterceptor.TX_JOIN_EXISTING));

        //CCオペレータ一覧
        Services.addInterceptor(WEB3AdminMCCCOperatorListService.class, new WEB3AdminMCServiceInterceptor());
        Services.addInterceptor(WEB3AdminMCCCOperatorListService.class, new WEB3LogSysTimeInterceptor());
        Services.addInterceptor(WEB3AdminMCCCOperatorListService.class, new TransactionalInterceptor(TransactionalInterceptor.TX_JOIN_EXISTING));

        //CCオペレータ削除
        Services.addInterceptor(WEB3AdminMCCCOperatorDeleteService.class, new WEB3AdminMCServiceInterceptor());
        Services.addInterceptor(WEB3AdminMCCCOperatorDeleteService.class, new WEB3LogSysTimeInterceptor());
        Services.addInterceptor(WEB3AdminMCCCOperatorDeleteService.class, new TransactionalInterceptor(TransactionalInterceptor.TX_JOIN_EXISTING));

        //CCオペレータ登録
        Services.addInterceptor(WEB3AdminMCCCOperatorRegistService.class, new WEB3AdminMCServiceInterceptor());
        Services.addInterceptor(WEB3AdminMCCCOperatorRegistService.class, new WEB3LogSysTimeInterceptor());
        Services.addInterceptor(WEB3AdminMCCCOperatorRegistService.class, new TransactionalInterceptor(TransactionalInterceptor.TX_JOIN_EXISTING));

        //CCオペレータ変更
        Services.addInterceptor(WEB3AdminMCCCOperatorChangeService.class, new WEB3AdminMCServiceInterceptor());
        Services.addInterceptor(WEB3AdminMCCCOperatorChangeService.class, new WEB3LogSysTimeInterceptor());
        Services.addInterceptor(WEB3AdminMCCCOperatorChangeService.class, new TransactionalInterceptor(TransactionalInterceptor.TX_JOIN_EXISTING));

        //メニュー制御共通サービス
        Services.addInterceptor(WEB3AdminMCCCOperatorRegistUnitCreateService.class, new WEB3AdminMCServiceInterceptor());
        Services.addInterceptor(WEB3AdminMCCCOperatorRegistUnitCreateService.class, new WEB3LogSysTimeInterceptor());
        Services.addInterceptor(WEB3AdminMCCCOperatorRegistUnitCreateService.class, new TransactionalInterceptor(TransactionalInterceptor.TX_JOIN_EXISTING));

        Services.addInterceptor(WEB3AdminMCAdminPermUnitCreateService.class, new WEB3AdminMCServiceInterceptor());
        Services.addInterceptor(WEB3AdminMCAdminPermUnitCreateService.class, new WEB3LogSysTimeInterceptor());
        Services.addInterceptor(WEB3AdminMCAdminPermUnitCreateService.class, new TransactionalInterceptor(TransactionalInterceptor.TX_JOIN_EXISTING));

        Services.addInterceptor(WEB3AdminMCAdminRegistUnitCreateService.class, new WEB3AdminMCServiceInterceptor());
        Services.addInterceptor(WEB3AdminMCAdminRegistUnitCreateService.class, new WEB3LogSysTimeInterceptor());
        Services.addInterceptor(WEB3AdminMCAdminRegistUnitCreateService.class, new TransactionalInterceptor(TransactionalInterceptor.TX_JOIN_EXISTING));
        
        //管理者サブメニュー表示
        Services.addInterceptor(WEB3AdminMCAdminMenuSubMenuDisplayService.class, new WEB3AdminMCServiceInterceptor());
        Services.addInterceptor(WEB3AdminMCAdminMenuSubMenuDisplayService.class, new WEB3LogSysTimeInterceptor());
        Services.addInterceptor(WEB3AdminMCAdminMenuSubMenuDisplayService.class, new TransactionalInterceptor(TransactionalInterceptor.TX_JOIN_EXISTING));

        //管理者パスワードロック解除
        Services.addInterceptor(WEB3AdminMCAdminPwdLockCancelService.class, new WEB3AdminMCServiceInterceptor());
        Services.addInterceptor(WEB3AdminMCAdminPwdLockCancelService.class, new WEB3LogSysTimeInterceptor());
        Services.addInterceptor(WEB3AdminMCAdminPwdLockCancelService.class, new TransactionalInterceptor(TransactionalInterceptor.TX_JOIN_EXISTING));

        //管理者一覧
        Services.addInterceptor(WEB3AdminMCAdminListService.class, new WEB3AdminMCServiceInterceptor());
        Services.addInterceptor(WEB3AdminMCAdminListService.class, new WEB3LogSysTimeInterceptor());
        Services.addInterceptor(WEB3AdminMCAdminListService.class, new TransactionalInterceptor(TransactionalInterceptor.TX_JOIN_EXISTING));

        //管理者権限グループ一覧
        Services.addInterceptor(WEB3AdminMCAdminPermGrpListService.class, new WEB3AdminMCServiceInterceptor());
        Services.addInterceptor(WEB3AdminMCAdminPermGrpListService.class, new WEB3LogSysTimeInterceptor());
        Services.addInterceptor(WEB3AdminMCAdminPermGrpListService.class, new TransactionalInterceptor(TransactionalInterceptor.TX_JOIN_EXISTING));

        //管理者権限グループ削除
        Services.addInterceptor(WEB3AdminMCAdminPermGrpDeleteService.class, new WEB3AdminMCServiceInterceptor());
        Services.addInterceptor(WEB3AdminMCAdminPermGrpDeleteService.class, new WEB3LogSysTimeInterceptor());
        Services.addInterceptor(WEB3AdminMCAdminPermGrpDeleteService.class, new TransactionalInterceptor(TransactionalInterceptor.TX_JOIN_EXISTING));

        //管理者権限グループ登録
        Services.addInterceptor(WEB3AdminMCAdminPermGrpRegistService.class, new WEB3AdminMCServiceInterceptor());
        Services.addInterceptor(WEB3AdminMCAdminPermGrpRegistService.class, new WEB3LogSysTimeInterceptor());
        Services.addInterceptor(WEB3AdminMCAdminPermGrpRegistService.class, new TransactionalInterceptor(TransactionalInterceptor.TX_JOIN_EXISTING));

        //管理者権限グループ変更
        Services.addInterceptor(WEB3AdminMCAdminPermGrpChangeService.class, new WEB3AdminMCServiceInterceptor());
        Services.addInterceptor(WEB3AdminMCAdminPermGrpChangeService.class, new WEB3LogSysTimeInterceptor());
        Services.addInterceptor(WEB3AdminMCAdminPermGrpChangeService.class, new TransactionalInterceptor(TransactionalInterceptor.TX_JOIN_EXISTING));

        //管理者削除
        Services.addInterceptor(WEB3AdminMCAdminDeleteService.class, new WEB3AdminMCServiceInterceptor());
        Services.addInterceptor(WEB3AdminMCAdminDeleteService.class, new WEB3LogSysTimeInterceptor());
        Services.addInterceptor(WEB3AdminMCAdminDeleteService.class, new TransactionalInterceptor(TransactionalInterceptor.TX_JOIN_EXISTING));

        //管理者登録
        Services.addInterceptor(WEB3AdminMCAdminRegistService.class, new WEB3AdminMCServiceInterceptor());
        Services.addInterceptor(WEB3AdminMCAdminRegistService.class, new WEB3LogSysTimeInterceptor());
        Services.addInterceptor(WEB3AdminMCAdminRegistService.class, new TransactionalInterceptor(TransactionalInterceptor.TX_JOIN_EXISTING));

        //管理者変更
        Services.addInterceptor(WEB3AdminMCAdminChangeService.class, new WEB3AdminMCServiceInterceptor());
        Services.addInterceptor(WEB3AdminMCAdminChangeService.class, new WEB3LogSysTimeInterceptor());
        Services.addInterceptor(WEB3AdminMCAdminChangeService.class, new TransactionalInterceptor(TransactionalInterceptor.TX_JOIN_EXISTING));


        // Message の登録処理 ----------------------

        //管理者メニュー制御CCｵﾍﾟﾚｰﾀﾊﾟｽﾜｰﾄﾞﾛｯｸ解除確認ﾘｸｴｽﾄ	
        regClass(WEB3AdminMCCCOperatorPwdLockCancelConfirmRequest.class);
        //管理者メニュー制御CCｵﾍﾟﾚｰﾀﾊﾟｽﾜｰﾄﾞﾛｯｸ解除確認ﾚｽﾎﾟﾝｽ	
        regClass(WEB3AdminMCCCOperatorPwdLockCancelConfirmResponse.class);
        
        //管理者メニュー制御CCｵﾍﾟﾚｰﾀﾊﾟｽﾜｰﾄﾞﾛｯｸ解除完了ﾘｸｴｽﾄ	
        regClass(WEB3AdminMCCCOperatorPwdLockCancelCompleteRequest.class);
        //管理者メニュー制御CCｵﾍﾟﾚｰﾀﾊﾟｽﾜｰﾄﾞﾛｯｸ解除完了ﾚｽﾎﾟﾝｽ	
        regClass(WEB3AdminMCCCOperatorPwdLockCancelCompleteResponse.class);
        
        //管理者メニュー制御CCｵﾍﾟﾚｰﾀ一覧ﾘｸｴｽﾄ	
        regClass(WEB3AdminMCCCOperatorListRequest.class);
        //管理者メニュー制御CCｵﾍﾟﾚｰﾀ一覧ﾚｽﾎﾟﾝｽ	
        regClass(WEB3AdminMCCCOperatorListResponse.class);
        
        //管理者メニュー制御CCｵﾍﾟﾚｰﾀ一覧入力ﾘｸｴｽﾄ	
        regClass(WEB3AdminMCCCOperatorListInputRequest.class);
        //管理者メニュー制御CCｵﾍﾟﾚｰﾀ一覧入力ﾚｽﾎﾟﾝｽ	
        regClass(WEB3AdminMCCCOperatorListInputResponse.class);
        
        //管理者メニュー制御CCｵﾍﾟﾚｰﾀ削除確認ﾘｸｴｽﾄ	
        regClass(WEB3AdminMCCCOperatorDeleteConfirmRequest.class);
        //管理者メニュー制御CCｵﾍﾟﾚｰﾀ削除確認ﾚｽﾎﾟﾝｽ	
        regClass(WEB3AdminMCCCOperatorDeleteConfirmResponse.class);
        
        //管理者メニュー制御CCｵﾍﾟﾚｰﾀ削除完了ﾘｸｴｽﾄ	
        regClass(WEB3AdminMCCCOperatorDeleteCompleteRequest.class);
        //管理者メニュー制御CCｵﾍﾟﾚｰﾀ削除完了ﾚｽﾎﾟﾝｽ	
        regClass(WEB3AdminMCCCOperatorDeleteCompleteResponse.class);
        
        //管理者メニュー制御CCｵﾍﾟﾚｰﾀ登録確認ﾘｸｴｽﾄ	
        regClass(WEB3AdminMCCCOperatorRegistConfirmRequest.class);
        //管理者メニュー制御CCｵﾍﾟﾚｰﾀ登録確認ﾚｽﾎﾟﾝｽ	
        regClass(WEB3AdminMCCCOperatorRegistConfirmResponse.class);
        
        //管理者メニュー制御CCｵﾍﾟﾚｰﾀ登録完了ﾘｸｴｽﾄ	
        regClass(WEB3AdminMCCCOperatorRegistCompleteRequest.class);
        //管理者メニュー制御CCｵﾍﾟﾚｰﾀ登録完了ﾚｽﾎﾟﾝｽ	
        regClass(WEB3AdminMCCCOperatorRegistCompleteResponse.class);
        
        //管理者メニュー制御CCｵﾍﾟﾚｰﾀ登録入力ﾘｸｴｽﾄ	
        regClass(WEB3AdminMCCCOperatorRegistInputRequest.class);
        //管理者メニュー制御CCｵﾍﾟﾚｰﾀ登録入力ﾚｽﾎﾟﾝｽ	
        regClass(WEB3AdminMCCCOperatorRegistInputResponse.class);
        
        //管理者メニュー制御CCｵﾍﾟﾚｰﾀ変更確認ﾘｸｴｽﾄ	
        regClass(WEB3AdminMCCCOperatorChangeConfirmRequest.class);
        //管理者メニュー制御CCｵﾍﾟﾚｰﾀ変更確認ﾚｽﾎﾟﾝｽ	
        regClass(WEB3AdminMCCCOperatorChangeConfirmResponse.class);
        
        //管理者メニュー制御CCｵﾍﾟﾚｰﾀ変更完了ﾘｸｴｽﾄ	
        regClass(WEB3AdminMCCCOperatorChangeCompleteRequest.class);
        //管理者メニュー制御CCｵﾍﾟﾚｰﾀ変更完了ﾚｽﾎﾟﾝｽ	
        regClass(WEB3AdminMCCCOperatorChangeCompleteResponse.class);
        
        //管理者メニュー制御CCｵﾍﾟﾚｰﾀ変更入力ﾘｸｴｽﾄ	
        regClass(WEB3AdminMCCCOperatorChangeInputRequest.class);
        //管理者メニュー制御CCｵﾍﾟﾚｰﾀ変更入力ﾚｽﾎﾟﾝｽ	
        regClass(WEB3AdminMCCCOperatorChangeInputResponse.class);
        
        //管理者メニュー制御サブメニュー表示リクエスト	
        regClass(WEB3AdminMCAdminMenuSubMenuDisplayRequest.class);
        //管理者メニュー制御サブメニュー表示レスポンス	
        regClass(WEB3AdminMCAdminMenuSubMenuDisplayResponse.class);
        
        //管理者メニュー制御管理者ﾊﾟｽﾜｰﾄﾞﾛｯｸ解除確認ﾘｸｴｽﾄ	
        regClass(WEB3AdminMCAdminPwdLockCancelConfirmRequest.class);
        //管理者メニュー制御管理者ﾊﾟｽﾜｰﾄﾞﾛｯｸ解除確認ﾚｽﾎﾟﾝｽ	
        regClass(WEB3AdminMCAdminPwdLockCancelConfirmResponse.class);
        
        //管理者メニュー制御管理者ﾊﾟｽﾜｰﾄﾞﾛｯｸ解除完了ﾘｸｴｽﾄ	
        regClass(WEB3AdminMCAdminPwdLockCancelCompleteRequest.class);
        //管理者メニュー制御管理者ﾊﾟｽﾜｰﾄﾞﾛｯｸ解除完了ﾚｽﾎﾟﾝｽ	
        regClass(WEB3AdminMCAdminPwdLockCancelCompleteResponse.class);
        
        //管理者メニュー制御管理者一覧リクエスト	
        regClass(WEB3AdminMCAdminListRequest.class);
        //管理者メニュー制御管理者一覧レスポンス	
        regClass(WEB3AdminMCAdminListResponse.class);
        
        //管理者メニュー制御管理者一覧入力リクエスト	
        regClass(WEB3AdminMCAdminListInputRequest.class);
        //管理者メニュー制御管理者一覧入力レスポンス	
        regClass(WEB3AdminMCAdminListInputResponse.class);
        
        //管理者メニュー制御管理者権限グループ一覧リクエスト	
        regClass(WEB3AdminMCAdminPermGrpListRequest.class);
        //管理者メニュー制御管理者権限グループ一覧レスポンス	
        regClass(WEB3AdminMCAdminPermGrpListResponse.class);
        
        //管理者メニュー制御管理者権限グループ削除確認リクエスト	
        regClass(WEB3AdminMCAdminPermGrpDeleteConfirmRequest.class);
        //管理者メニュー制御管理者権限グループ削除確認レスポンス	
        regClass(WEB3AdminMCAdminPermGrpDeleteConfirmResponse.class);
        
        //管理者メニュー制御管理者権限グループ削除完了リクエスト	
        regClass(WEB3AdminMCAdminPermGrpDeleteCompleteRequest.class);
        //管理者メニュー制御管理者権限グループ削除完了レスポンス	
        regClass(WEB3AdminMCAdminPermGrpDeleteCompleteResponse.class);
        
        //管理者メニュー制御管理者権限グループ詳細リクエスト	
        regClass(WEB3AdminMCAdminPermGrpDetailsRequest.class);
        //管理者メニュー制御管理者権限グループ詳細レスポンス	
        regClass(WEB3AdminMCAdminPermGrpDetailsResponse.class);
        
        //管理者メニュー制御管理者権限グループ登録確認リクエスト	
        regClass(WEB3AdminMCAdminPermGrpRegistConfirmRequest.class);
        //管理者メニュー制御管理者権限グループ登録確認レスポンス	
        regClass(WEB3AdminMCAdminPermGrpRegistConfirmResponse.class);
        
        //管理者メニュー制御管理者権限グループ登録完了リクエスト	
        regClass(WEB3AdminMCAdminPermGrpRegistCompleteRequest.class);
        //管理者メニュー制御管理者権限グループ登録完了レスポンス	
        regClass(WEB3AdminMCAdminPermGrpRegistCompleteResponse.class);
        
        //管理者メニュー制御管理者権限グループ登録入力リクエスト	
        regClass(WEB3AdminMCAdminPermGrpRegistInputRequest.class);
        //管理者メニュー制御管理者権限グループ登録入力レスポンス	
        regClass(WEB3AdminMCAdminPermGrpRegistInputResponse.class);
        
        //管理者メニュー制御管理者権限グループ変更確認リクエスト	
        regClass(WEB3AdminMCAdminPermGrpChangeConfirmRequest.class);
        //管理者メニュー制御管理者権限グループ変更確認レスポンス	
        regClass(WEB3AdminMCAdminPermGrpChangeConfirmResponse.class);
        
        //管理者メニュー制御管理者権限グループ変更完了リクエスト	
        regClass(WEB3AdminMCAdminPermGrpChangeCompleteRequest.class);
        //管理者メニュー制御管理者権限グループ変更完了レスポンス	
        regClass(WEB3AdminMCAdminPermGrpChangeCompleteResponse.class);
        
        //管理者メニュー制御管理者権限グループ変更入力リクエスト	
        regClass(WEB3AdminMCAdminPermGrpChangeInputRequest.class);
        //管理者メニュー制御管理者権限グループ変更入力レスポンス	
        regClass(WEB3AdminMCAdminPermGrpChangeInputResponse.class);
        
        //管理者メニュー制御管理者削除確認リクエスト	
        regClass(WEB3AdminMCAdminDeleteConfirmRequest.class);
        //管理者メニュー制御管理者削除確認レスポンス	
        regClass(WEB3AdminMCAdminDeleteConfirmResponse.class);
        
        //管理者メニュー制御管理者削除完了リクエスト	
        regClass(WEB3AdminMCAdminDeleteCompleteRequest.class);
        //管理者メニュー制御管理者削除完了レスポンス	
        regClass(WEB3AdminMCAdminDeleteCompleteResponse.class);
        
        //管理者メニュー制御管理者登録確認リクエスト	
        regClass(WEB3AdminMCAdminRegistConfirmRequest.class);
        //管理者メニュー制御管理者登録確認レスポンス	
        regClass(WEB3AdminMCAdminRegistConfirmResponse.class);
        
        //管理者メニュー制御管理者登録完了リクエスト	
        regClass(WEB3AdminMCAdminRegistCompleteRequest.class);
        //管理者メニュー制御管理者登録完了レスポンス	
        regClass(WEB3AdminMCAdminRegistCompleteResponse.class);
        
        //管理者メニュー制御管理者登録入力リクエスト	
        regClass(WEB3AdminMCAdminRegistInputRequest.class);
        //管理者メニュー制御管理者登録入力レスポンス	
        regClass(WEB3AdminMCAdminRegistInputResponse.class);
        
        //管理者メニュー制御管理者変更確認リクエスト	
        regClass(WEB3AdminMCAdminChangeConfirmRequest.class);
        //管理者メニュー制御管理者変更確認レスポンス	
        regClass(WEB3AdminMCAdminChangeConfirmResponse.class);
        
        //管理者メニュー制御管理者変更完了リクエスト	
        regClass(WEB3AdminMCAdminChangeCompleteRequest.class);
        //管理者メニュー制御管理者変更完了レスポンス	
        regClass(WEB3AdminMCAdminChangeCompleteResponse.class);
        
        //管理者メニュー制御管理者変更入力リクエスト	
        regClass(WEB3AdminMCAdminChangeInputRequest.class);
        //管理者メニュー制御管理者変更入力レスポンス	
        regClass(WEB3AdminMCAdminChangeInputResponse.class);
        


        //Handler の登録処理 ----------------------

        //CCオペレータパスワードロック解除確認処理
        regHandler(WEB3AdminMCAppPlugin.class, WEB3AdminMCCCOperatorPwdLockCancelConfirmRequest.class, WEB3AdminMCCCOperatorPwdLockCancelHandler.class, "cancelConfirm");

        //CCオペレータパスワードロック解除完了
        regHandler(WEB3AdminMCAppPlugin.class, WEB3AdminMCCCOperatorPwdLockCancelCompleteRequest.class, WEB3AdminMCCCOperatorPwdLockCancelHandler.class, "cancelComplete");

        //CCオペレータ一覧条件入力画面表示処理
        regHandler(WEB3AdminMCAppPlugin.class, WEB3AdminMCCCOperatorListInputRequest.class, WEB3AdminMCCCOperatorListHandler.class, "inputScreenDisplay");

        //CCオペレータ一覧表示処理
        regHandler(WEB3AdminMCAppPlugin.class, WEB3AdminMCCCOperatorListRequest.class, WEB3AdminMCCCOperatorListHandler.class, "CCOperatorListDisplay");

        //CCオペレータ削除確認処理
        regHandler(WEB3AdminMCAppPlugin.class, WEB3AdminMCCCOperatorDeleteConfirmRequest.class, WEB3AdminMCCCOperatorDeleteHandler.class, "traderDeleteConfirm");
        //CCオペレータ削除完了処理
        regHandler(WEB3AdminMCAppPlugin.class, WEB3AdminMCCCOperatorDeleteCompleteRequest.class, WEB3AdminMCCCOperatorDeleteHandler.class, "traderDeleteComplete");

        //入力画面表示
        regHandler(WEB3AdminMCAppPlugin.class, WEB3AdminMCCCOperatorRegistInputRequest.class, WEB3AdminMCCCOperatorRegistHandler.class, "inputScreenDisplay");
        //CCオペレータ登録確認処理
        regHandler(WEB3AdminMCAppPlugin.class, WEB3AdminMCCCOperatorRegistConfirmRequest.class, WEB3AdminMCCCOperatorRegistHandler.class, "traderRegistConfirm");
        //CCオペレータ登録完了処理
        regHandler(WEB3AdminMCAppPlugin.class, WEB3AdminMCCCOperatorRegistCompleteRequest.class, WEB3AdminMCCCOperatorRegistHandler.class, "traderRegistComplete");

        //入力画面表示
        regHandler(WEB3AdminMCAppPlugin.class, WEB3AdminMCCCOperatorChangeInputRequest.class, WEB3AdminMCCCOperatorChangeHandler.class, "inputScreenDisplay");
        //CCオペレータ変更確認処理
        regHandler(WEB3AdminMCAppPlugin.class, WEB3AdminMCCCOperatorChangeConfirmRequest.class, WEB3AdminMCCCOperatorChangeHandler.class, "traderChangeConfirm");
        //CCオペレータ変更完了
        regHandler(WEB3AdminMCAppPlugin.class, WEB3AdminMCCCOperatorChangeCompleteRequest.class, WEB3AdminMCCCOperatorChangeHandler.class, "traderChangeComplete");

        //管理者パスワードロック解除確認処理
        regHandler(WEB3AdminMCAppPlugin.class, WEB3AdminMCAdminPwdLockCancelConfirmRequest.class, WEB3AdminMCAdminPwdLockCancelHandler.class, "cancelConfirm");
        //管理者パスワードロック解除完了処理
        regHandler(WEB3AdminMCAppPlugin.class, WEB3AdminMCAdminPwdLockCancelCompleteRequest.class, WEB3AdminMCAdminPwdLockCancelHandler.class, "cancelComplete");

        //管理者一覧条件入力画面表示処理
        regHandler(WEB3AdminMCAppPlugin.class, WEB3AdminMCAdminListInputRequest.class, WEB3AdminMCAdminListHandler.class, "inputScreenDisplay");
        //管理者一覧表示処理
        regHandler(WEB3AdminMCAppPlugin.class, WEB3AdminMCAdminListRequest.class, WEB3AdminMCAdminListHandler.class, "adminListDisplay");

        //権限グループ一覧表示処理
        regHandler(WEB3AdminMCAppPlugin.class, WEB3AdminMCAdminPermGrpListRequest.class, WEB3AdminMCAdminPermGrpListHandler.class, "permGrpListDisplay");
        //権限グループ詳細表示処理
        regHandler(WEB3AdminMCAppPlugin.class, WEB3AdminMCAdminPermGrpDetailsRequest.class, WEB3AdminMCAdminPermGrpListHandler.class, "permGrpDetailDisplay");

        //権限グループ削除確認処理
        regHandler(WEB3AdminMCAppPlugin.class, WEB3AdminMCAdminPermGrpDeleteConfirmRequest.class, WEB3AdminMCAdminPermGrpDeleteHandler.class, "permGrpDeleteConfirm");
        //権限グループ削除完了処理
        regHandler(WEB3AdminMCAppPlugin.class, WEB3AdminMCAdminPermGrpDeleteCompleteRequest.class, WEB3AdminMCAdminPermGrpDeleteHandler.class, "permGrpDeleteComplete");

        //管理者権限グループ入力画面表示処理
        regHandler(WEB3AdminMCAppPlugin.class, WEB3AdminMCAdminPermGrpRegistInputRequest.class, WEB3AdminMCAdminPermGrpRegistHandler.class, "inputScreenDisplay");
        //権限グループ登録確認処理
        regHandler(WEB3AdminMCAppPlugin.class, WEB3AdminMCAdminPermGrpRegistConfirmRequest.class, WEB3AdminMCAdminPermGrpRegistHandler.class, "permGrpRegistConfirm");
        //権限グループ登録完了処理
        regHandler(WEB3AdminMCAppPlugin.class, WEB3AdminMCAdminPermGrpRegistCompleteRequest.class, WEB3AdminMCAdminPermGrpRegistHandler.class, "permGrpRegistComplete");

        //管理者権限グループ変更入力画面表示
        regHandler(WEB3AdminMCAppPlugin.class, WEB3AdminMCAdminPermGrpChangeInputRequest.class, WEB3AdminMCAdminPermGrpChangeHandler.class, "inputScreenDisplay");
        //権限グループ変更確認処理
        regHandler(WEB3AdminMCAppPlugin.class, WEB3AdminMCAdminPermGrpChangeConfirmRequest.class, WEB3AdminMCAdminPermGrpChangeHandler.class, "permGrpChangeConfirm");
        //権限グループ変更完了処理
        regHandler(WEB3AdminMCAppPlugin.class, WEB3AdminMCAdminPermGrpChangeCompleteRequest.class, WEB3AdminMCAdminPermGrpChangeHandler.class, "permGrpChangeComplete");

        //管理者削除確認処理
        regHandler(WEB3AdminMCAppPlugin.class, WEB3AdminMCAdminDeleteConfirmRequest.class, WEB3AdminMCAdminDeleteHandler.class, "adminDeleteConfirm");
        //管理者削除完了処理
        regHandler(WEB3AdminMCAppPlugin.class, WEB3AdminMCAdminDeleteCompleteRequest.class, WEB3AdminMCAdminDeleteHandler.class, "adminDeleteComplete");

        //管理者登録入力画面表示処理
        regHandler(WEB3AdminMCAppPlugin.class, WEB3AdminMCAdminRegistInputRequest.class, WEB3AdminMCAdminRegistHandler.class, "inputScreenDisplay");
        //管理者登録確認処理
        regHandler(WEB3AdminMCAppPlugin.class, WEB3AdminMCAdminRegistConfirmRequest.class, WEB3AdminMCAdminRegistHandler.class, "adminRegistConfirm");
        //管理者登録完了処理
        regHandler(WEB3AdminMCAppPlugin.class, WEB3AdminMCAdminRegistCompleteRequest.class, WEB3AdminMCAdminRegistHandler.class, "adminRegistComplete");

        //管理者変更入力画面表示処理
        regHandler(WEB3AdminMCAppPlugin.class, WEB3AdminMCAdminChangeInputRequest.class, WEB3AdminMCAdminChangeHandler.class, "inputScreenDisplay");
        //管理者変更確認処理
        regHandler(WEB3AdminMCAppPlugin.class, WEB3AdminMCAdminChangeConfirmRequest.class, WEB3AdminMCAdminChangeHandler.class, "adminChangeConfirm");
        //管理者変更完了処理
        regHandler(WEB3AdminMCAppPlugin.class, WEB3AdminMCAdminChangeCompleteRequest.class, WEB3AdminMCAdminChangeHandler.class, "adminChangeComplete");

        //管理者サブメニュー表示
        regHandler(WEB3AdminMCAppPlugin.class, WEB3AdminMCAdminMenuSubMenuDisplayRequest.class, WEB3AdminMCAdminMenuSubMenuDisplayHandler.class, "subMenuDisplay");


        log.exiting(STR_METHOD_NAME);
    }
}@
