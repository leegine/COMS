head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.15.02.20.40;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	8a44d7ecc762b5b;
filename	WEB3ToSuccBaseAppPlugin.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : Webbroker3-TriggerOrder-Base プラグインクラス(WEB3ToSuccBaseAppPlugin.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2005/10/18 郭英 (中訊) 新規作成 
Revesion History : 2008/03/25 趙林鵬 (中訊) モデルNo.254
*/
package webbroker3.triggerorder.base;

import com.fitechlabs.xtrade.kernel.boot.KernelPlugin;
import com.fitechlabs.xtrade.kernel.boot.Plugin;
import com.fitechlabs.xtrade.kernel.boot.Services;
import com.fitechlabs.xtrade.kernel.interceptor.TransactionalInterceptor;

import webbroker3.common.WEB3LogSysTimeInterceptor;
import webbroker3.triggerorder.base.data.WEB3TriggerOrderAccountDatabaseExtensions;
import webbroker3.triggerorder.base.data.WEB3TriggerOrderMasterDatabaseExtensions;
import webbroker3.triggerorder.base.service.delegate.WEB3ToSuccReservationEqTypeOrderUpdateService;
import webbroker3.triggerorder.base.service.delegate.WEB3ToSuccReservationIfoOrderUpdateService;
import webbroker3.triggerorder.base.service.delegate.stdimpls.WEB3ToSuccReservationEqTypeOrderUpdateServiceImpl;
import webbroker3.triggerorder.base.service.delegate.stdimpls.WEB3ToSuccReservationIfoOrderUpdateServiceImpl;
import webbroker3.util.WEB3LogUtility;

/**
 * Webbroker3-TriggerOrder プラグインクラス<BR>
 *   
 * @@author 郭英
 * @@version 1.0
 */
public class WEB3ToSuccBaseAppPlugin extends Plugin
{
    /**
     * ログユーティリティ。
     */
    private static WEB3LogUtility log =
        WEB3LogUtility.getInstance(WEB3ToSuccBaseAppPlugin.class);

    /**
     * コンストラクタ。
     */
    public WEB3ToSuccBaseAppPlugin()
    {
    }

    /**
     * プラグインエントリーポイント。
     */
    public static void plug() throws Exception
    {
        String METHOD_NAME = "plug()";
        log.entering(METHOD_NAME);
        
        plug(WEB3ToSuccBaseAppPlugin.class);
        
        log.exiting(METHOD_NAME);
    }
    
    /**
     * プラグイン処理。
     */
    public static void onPlug() throws Exception 
    {
        String METHOD_NAME = "onPlug()";
        log.entering(METHOD_NAME);
        
        //このプラグインより先に読み込む必要のあるプラグインの指定。
        //install the system plugins that we need
        KernelPlugin.plug();
        
        //DatabaseExtensions のプラグイン処理 ----------------------
        WEB3TriggerOrderAccountDatabaseExtensions.plug();
        WEB3TriggerOrderMasterDatabaseExtensions.plug();
        
        //Service の登録
        //株式予約注文更新サービス 
        Services.registerService(
            WEB3ToSuccReservationEqTypeOrderUpdateService.class,
            new WEB3ToSuccReservationEqTypeOrderUpdateServiceImpl());

        //先物OP予約注文更新サービス
        Services.registerService(
            WEB3ToSuccReservationIfoOrderUpdateService.class,
            new WEB3ToSuccReservationIfoOrderUpdateServiceImpl());

        // Service.execute()呼び出し前後に  ----------------------
        // 処理開始時刻と処理終了時刻をログ出力するように設定する
        // 株式予約注文更新サービス
        Services.addInterceptor(
            WEB3ToSuccReservationEqTypeOrderUpdateService.class,
            new WEB3LogSysTimeInterceptor());

        //先物OP予約注文更新サービス
        Services.addInterceptor(
            WEB3ToSuccReservationIfoOrderUpdateService.class,
            new WEB3LogSysTimeInterceptor());

        // Service の Interceptor 設定処理 ----------------------
        // 自動トランザクション設定
        Services.addInterceptor(
            WEB3ToSuccReservationEqTypeOrderUpdateService.class,
            new TransactionalInterceptor(TransactionalInterceptor.TX_JOIN_EXISTING));

        //先物OP予約注文更新サービス
        Services.addInterceptor(
            WEB3ToSuccReservationIfoOrderUpdateService.class,
            new TransactionalInterceptor(TransactionalInterceptor.TX_JOIN_EXISTING));

        log.exiting(METHOD_NAME);
    }
}
@
