head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.15.01.03.45;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	86c4d7eb25155fb;
filename	WEB3PlsbvsAppPlugin.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : Webbroker3-Plsbvsプラグイン(WEB3PlsbvsAppPlugin.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2004/11/16 賈元春 (中訊) 新規作成
                 : 2006/11/01 周捷(中訊) モデル056
*/

package webbroker3.tradehistory;

import com.fitechlabs.xtrade.kernel.boot.KernelPlugin;
import com.fitechlabs.xtrade.kernel.boot.Plugin;
import com.fitechlabs.xtrade.kernel.boot.Services;
import com.fitechlabs.xtrade.kernel.interceptor.TransactionalInterceptor;

import webbroker3.common.WEB3LogSysTimeInterceptor;
import webbroker3.tradehistory.data.WEB3TradeHistorySessionDatabaseExtensions;
import webbroker3.tradehistory.handler.WEB3BVSBookValueSpecsHandler;
import webbroker3.tradehistory.handler.WEB3PLSProfitLossSpecsHandler;
import webbroker3.tradehistory.message.WEB3BVSBookValueSpecsRequest;
import webbroker3.tradehistory.message.WEB3BVSBookValueSpecsResponse;
import webbroker3.tradehistory.message.WEB3PLSProfitLossDownloadRequest;
import webbroker3.tradehistory.message.WEB3PLSProfitLossDownloadResponse;
import webbroker3.tradehistory.message.WEB3PLSProfitLossSpecsRequest;
import webbroker3.tradehistory.message.WEB3PLSProfitLossSpecsResponse;
import webbroker3.tradehistory.service.delegate.WEB3BVSBookValueSpecsService;
import webbroker3.tradehistory.service.delegate.WEB3PLSProfitLossSpecsService;
import webbroker3.tradehistory.service.delegate.stdimpls.WEB3BVSBookValueSpecsServiceImpl;
import webbroker3.tradehistory.service.delegate.stdimpls.WEB3PLSProfitLossSpecsServiceImpl;
import webbroker3.util.WEB3LogUtility;

/**
 * Webbroker3-Plsbvsプラグインクラス。
 *                                                                
 * @@author 賈元春
 * @@version 1.0
 */
public final class WEB3PlsbvsAppPlugin extends Plugin
{
    /**
     * ログユーティリティ。
     */
    private static WEB3LogUtility log = WEB3LogUtility.getInstance(WEB3PlsbvsAppPlugin.class);

    /**
     * コンストラクタ。
     */
    public WEB3PlsbvsAppPlugin()
    {
        String STR_METHOD_NAME = " WEB3PlsbvsAppPlugin()";
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

        plug(WEB3PlsbvsAppPlugin.class);

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
		// 李志強 Codegenの対応 start
//		  WEB3TradeHistoryMasterDatabaseExtensions.plug();
        
		WEB3TradeHistorySessionDatabaseExtensions.plug();
		// 李志強 Codegenの対応 end
		
        // Service の登録処理 ----------------------
        
        //簿価単価明細照会
        Services.registerService(WEB3BVSBookValueSpecsService.class, new WEB3BVSBookValueSpecsServiceImpl());

        //損益明細照会
        Services.registerService(WEB3PLSProfitLossSpecsService.class, new WEB3PLSProfitLossSpecsServiceImpl());

        // Service の Interceptor 設定処理 ----------------------
        // 自動トランザクション設定

        //簿価単価明細照会
        Services.addInterceptor(WEB3BVSBookValueSpecsService.class, new WEB3BVSBookValueSpecsServiceInterceptor());
        Services.addInterceptor(WEB3BVSBookValueSpecsService.class, new WEB3LogSysTimeInterceptor());
        Services.addInterceptor(WEB3BVSBookValueSpecsService.class, new TransactionalInterceptor(TransactionalInterceptor.TX_JOIN_EXISTING));
        
        //損益明細照会
        Services.addInterceptor(WEB3PLSProfitLossSpecsService.class, new WEB3PLSProfitLossSpecsServiceInterceptor());
        Services.addInterceptor(WEB3PLSProfitLossSpecsService.class, new WEB3LogSysTimeInterceptor());
        Services.addInterceptor(WEB3PLSProfitLossSpecsService.class, new TransactionalInterceptor(TransactionalInterceptor.TX_JOIN_EXISTING));

        // Message の登録処理 ----------------------

        //簿価単価明細照会リクエスト
        regClass(WEB3BVSBookValueSpecsRequest.class);
        //簿価単価明細照会レスポンス
        regClass(WEB3BVSBookValueSpecsResponse.class);

        //損益明細照会リクエスト
        regClass(WEB3PLSProfitLossSpecsRequest.class);
        //損益明細照会レスポンス
        regClass(WEB3PLSProfitLossSpecsResponse.class);
        
        //損益明細ファ@イルダウンロードリクエスト
        regClass(WEB3PLSProfitLossDownloadRequest.class);
        //損益明細ファ@イルダウンロードレスポンス
        regClass(WEB3PLSProfitLossDownloadResponse.class);

        //Handler の登録処理 ----------------------

        //簿価単価明細照会 用ハンドラーの登録
        regHandler(WEB3PlsbvsAppPlugin.class, WEB3BVSBookValueSpecsRequest.class, WEB3BVSBookValueSpecsHandler.class, "getBookValueSpecs");

        //損益明細照会 用ハンドラーの登録
        regHandler(WEB3PlsbvsAppPlugin.class, WEB3PLSProfitLossSpecsRequest.class, WEB3PLSProfitLossSpecsHandler.class, "getProfitLossSpecs");

        regHandler(WEB3PlsbvsAppPlugin.class, WEB3PLSProfitLossDownloadRequest.class, WEB3PLSProfitLossSpecsHandler.class, "getProfitLossDownload");
        log.exiting(STR_METHOD_NAME);
    }
}@
