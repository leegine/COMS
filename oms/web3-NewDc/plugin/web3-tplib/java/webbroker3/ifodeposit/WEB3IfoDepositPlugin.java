head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.15.00.38.49;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	86c4d7eb25155fb;
filename	WEB3IfoDepositPlugin.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : 証拠金計算プラグインのプラグインクラス(WEB3IfoDepositPlugin.java)
Author Name      : Daiwa Institute of Research
Revision History : 2004/10/12 山田　@卓司(FLJ) 新規作成
*/
package webbroker3.ifodeposit;

import com.fitechlabs.xtrade.kernel.boot.KernelPlugin;
import com.fitechlabs.xtrade.kernel.boot.Plugin;
import com.fitechlabs.xtrade.kernel.boot.Services;
import com.fitechlabs.xtrade.kernel.interceptor.TransactionalInterceptor;

import webbroker3.ifodeposit.data.WEB3TPLibIfoDepositSessionDatabaseExtensions;
import webbroker3.ifodeposit.handler.WEB3IfoDepositCalcResultSaveHandler;
import webbroker3.ifodeposit.handler.WEB3IfoDepositTransitionReferenceHandler;
import webbroker3.ifodeposit.message.WEB3IfoDepositCalcResultSaveRequest;
import webbroker3.ifodeposit.message.WEB3IfoDepositCalcResultSaveResponse;
import webbroker3.ifodeposit.message.WEB3IfoDepositTransitionReferenceRequest;
import webbroker3.ifodeposit.message.WEB3IfoDepositTransitionReferenceResponse;
import webbroker3.ifodeposit.service.delegate.WEB3IfoDepositCalcResultCreatePerAccountService;
import webbroker3.ifodeposit.service.delegate.WEB3IfoDepositCalcResultSaveService;
import webbroker3.ifodeposit.service.delegate.WEB3IfoDepositTransitionReferenceService;
import webbroker3.ifodeposit.service.delegate.stdimpls.WEB3IfoDepositCalcResultCreatePerAccountServiceImpl;
import webbroker3.ifodeposit.service.delegate.stdimpls.WEB3IfoDepositCalcResultSaveServiceImpl;
import webbroker3.ifodeposit.service.delegate.stdimpls.WEB3IfoDepositTransitionReferenceServiceImpl;
import webbroker3.util.WEB3LogUtility;


/**
 * 証拠金計算プラグインのプラグインクラス<br>
 * 
 * @@author Takuji Yamada
 * @@version 1.0
 */
public class WEB3IfoDepositPlugin extends Plugin
{
    
    /**
     * ログ
     */
    private static final WEB3LogUtility log =
        WEB3LogUtility.getInstance(WEB3IfoDepositPlugin.class);

    /**
     * コンストラクタ
     */
    public WEB3IfoDepositPlugin()
    {
    }

    /**
     * このプラグインをプラグインするときに使用するメソッド
     */
    public static void plug() throws Exception
    {
        plug(WEB3IfoDepositPlugin.class);
    }

    /**
     * このプラグインをプラグインするときに処理されるメソッド
     */
    public static void onPlug() throws Exception
    {

        // Kernelプラグインをプラグ
        KernelPlugin.plug();
        
        //データベースをプラグ
        
        WEB3TPLibIfoDepositSessionDatabaseExtensions.plug();

        // ---------------------------------------------------------------------

        // 証拠金計算サービスを登録
        Services.registerService(
            WEB3IfoDepositCalcService.class,
            new WEB3IfoDepositCalcServiceImpl());
        
        log.debug("WEB3IfoDepositCalcService registered.");

        // 証拠金計算サービスインターセプタを登録
        Services.addInterceptor(
            WEB3IfoDepositCalcService.class,
            new WEB3IfoDepositCalcServiceInterceptor());

        log.debug("WEB3IfoDepositCalcServiceInterceptor added to WEB3IfoDepositCalcService");
        
        // TransactionalInterceptorを登録
        Services.addInterceptor(
            WEB3IfoDepositCalcService.class,
            new TransactionalInterceptor(
                TransactionalInterceptor.TX_JOIN_EXISTING));

        log.debug("TransactionalInterceptor added to WEB3IfoDepositCalcService");

        // ---------------------------------------------------------------------

        // 証拠金推移参照画面表示サービスを登録
        Services.registerService(
            WEB3IfoDepositTransitionReferenceService.class,
            new WEB3IfoDepositTransitionReferenceServiceImpl());

        log.debug("WEB3IfoDepositTransitionReferenceService registered.");

        // 証拠金推移参照画面表示サービスインターセプタを登録
        Services.addInterceptor(
		    WEB3IfoDepositTransitionReferenceService.class,
            new WEB3IfoDepositTransitionReferenceServiceInterceptor());
          
        log.debug("WEB3IfoDepositTransitionReferenceServiceInterceptor added to WEB3IfoDepositTransitionReferenceService");
            
        // TransactionalInterceptorを登録
        Services.addInterceptor(
            WEB3IfoDepositTransitionReferenceService.class,
            new TransactionalInterceptor(
                TransactionalInterceptor.TX_JOIN_EXISTING));

        log.debug("TransactionalInterceptor added to WEB3IfoDepositTransitionReferenceService");
        
        // ---------------------------------------------------------------------

        // 証拠金推移参照画面表示メッセージを登録
        regClass(WEB3IfoDepositTransitionReferenceRequest.class);
        log.debug("WEB3IfoDepositTransitionReferenceRequest registered.");

        regClass(WEB3IfoDepositTransitionReferenceResponse.class);
        log.debug("WEB3IfoDepositTransitionReferenceResponse registered.");
        
        // 証拠金推移参照画面表示ハンドラ
        regHandler(
            WEB3IfoDepositPlugin.class,
            WEB3IfoDepositTransitionReferenceRequest.class,
            WEB3IfoDepositTransitionReferenceHandler.class,
            "ifoDepositTransitionReferenceRequest");
      
        // ---------------------------------------------------------------------
        
        // 証拠金計算結果顧客毎作成サービスを登録
        Services.registerService(
                WEB3IfoDepositCalcResultCreatePerAccountService.class,
            new WEB3IfoDepositCalcResultCreatePerAccountServiceImpl());

        log.debug("WEB3IfoDepositCalcResultCreatePerAccountService registered.");
        
        // 証拠金計算結果保存サービスを登録
        Services.registerService(
            WEB3IfoDepositCalcResultSaveService.class,
            new WEB3IfoDepositCalcResultSaveServiceImpl());

        log.debug("WEB3IfoDepositCalcResultSaveService registered.");
        
        // 証拠金計算結果顧客毎作成サービスインターセプタを登録
        Services.addInterceptor(
                WEB3IfoDepositCalcResultCreatePerAccountService.class,
            new WEB3IfoDepositCalcResultCreatePerAccountServiceInterceptor());

        log.debug("WEB3IfoDepositCalcResultCreatePerAccountServiceInterceptor added to WEB3IfoDepositCalcResultCreatePerAccountService");
        
        // ---------------------------------------------------------------------
        
        // 証拠金計算結果保存メッセージを登録
        regClass(WEB3IfoDepositCalcResultSaveRequest.class);
        log.debug("WEB3IfoDepositCalcResultSaveRequest registered.");

        regClass(WEB3IfoDepositCalcResultSaveResponse.class);
        log.debug("WEB3IfoDepositCalcResultSaveResponse registered.");

        // 証拠金計算結果保存ハンドラ
        regHandler(
            WEB3IfoDepositPlugin.class,
            WEB3IfoDepositCalcResultSaveRequest.class,
            WEB3IfoDepositCalcResultSaveHandler.class,
            "ifoDepositCalcResultSaveRequest");

        log.debug("WEB3IfoDepositCalcResultSaveHandler registered.");

        // ---------------------------------------------------------------------
        
        log.info("WEB3IfoDepositPlugin bootstrap succeeded.");

    }

}
@
