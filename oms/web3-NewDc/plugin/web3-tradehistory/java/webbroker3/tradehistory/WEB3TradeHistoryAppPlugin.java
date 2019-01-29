head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.15.01.03.24;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	86c4d7eb25155fb;
filename	WEB3TradeHistoryAppPlugin.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : Webbroker3-Tradehistory プラグイン(WEB3TradeHistoryAppPlugin.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2004/11/04 温 顕 法@ (中訊) 新規作成
*/

package webbroker3.tradehistory;

import com.fitechlabs.xtrade.kernel.boot.KernelPlugin;
import com.fitechlabs.xtrade.kernel.boot.Plugin;
import com.fitechlabs.xtrade.kernel.boot.Services;
import com.fitechlabs.xtrade.kernel.interceptor.TransactionalInterceptor;

import webbroker3.common.WEB3LogSysTimeInterceptor;

import webbroker3.tradehistory.handler.WEB3HistorySettleDetailHandler;
import webbroker3.tradehistory.handler.WEB3HistoryTradeDetailHandler;
import webbroker3.tradehistory.handler.WEB3HistoryTradeHistoryListHandler;

import webbroker3.tradehistory.message.WEB3HistorySettleDetailRequest;
import webbroker3.tradehistory.message.WEB3HistorySettleDetailResponse;
import webbroker3.tradehistory.message.WEB3HistoryTradeDetailRequest;
import webbroker3.tradehistory.message.WEB3HistoryTradeDetailResponse;
import webbroker3.tradehistory.message.WEB3HistoryTradeHistoryDownloadRequest;
import webbroker3.tradehistory.message.WEB3HistoryTradeHistoryDownloadResponse;
import webbroker3.tradehistory.message.WEB3HistoryTradeHistoryListRequest;
import webbroker3.tradehistory.message.WEB3HistoryTradeHistoryListResponse;

import webbroker3.tradehistory.service.delegate.WEB3HistorySettleDetailService;
import webbroker3.tradehistory.service.delegate.WEB3HistoryTradeDetailService;
import webbroker3.tradehistory.service.delegate.WEB3HistoryTradeHistoryListService;

import webbroker3.tradehistory.service.delegate.stdimpls.WEB3HistorySettleDetailServiceImpl;
import webbroker3.tradehistory.service.delegate.stdimpls.WEB3HistoryTradeDetailServiceImpl;
import webbroker3.tradehistory.service.delegate.stdimpls.WEB3HistoryTradeHistoryListServiceImpl;

import webbroker3.tradehistory.data.WEB3TradeHistorySessionDatabaseExtensions;

import webbroker3.util.WEB3LogUtility;

/**
 * Webbroker3-Tradehistory プラグインクラス。
 *                                                                
 * @@author 温 顕 法@
 * @@version 1.0
 */
public final class WEB3TradeHistoryAppPlugin extends Plugin
{
    /**
     * ログユーティリティ。
     */
    private static WEB3LogUtility log = WEB3LogUtility.getInstance(WEB3TradeHistoryAppPlugin.class);

    /**
     * コンストラクタ。
     */
    public WEB3TradeHistoryAppPlugin()
    {
        String STR_METHOD_NAME = " WEB3TradeHistoryAppPlugin()";
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

        plug(WEB3TradeHistoryAppPlugin.class);

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
//        WEB3TradeHistoryMasterDatabaseExtensions.plug();
        
		WEB3TradeHistorySessionDatabaseExtensions.plug();
		// 李志強 Codegenの対応 end

        // Service の登録処理 ----------------------
        
        //決済明細
        Services.registerService(WEB3HistorySettleDetailService.class, new WEB3HistorySettleDetailServiceImpl());

        //取引明細
        Services.registerService(WEB3HistoryTradeDetailService.class, new WEB3HistoryTradeDetailServiceImpl());
        
        //取引履歴一覧
        Services.registerService(WEB3HistoryTradeHistoryListService.class, new WEB3HistoryTradeHistoryListServiceImpl());


        // Service の Interceptor 設定処理 ----------------------
        // 自動トランザクション設定

        //決済明細
        Services.addInterceptor(WEB3HistorySettleDetailService.class, new WEB3HistorySettleDetailServiceInterceptor());
        Services.addInterceptor(WEB3HistorySettleDetailService.class, new WEB3LogSysTimeInterceptor());
        Services.addInterceptor(WEB3HistorySettleDetailService.class, new TransactionalInterceptor(TransactionalInterceptor.TX_JOIN_EXISTING));
        
        //取引明細
        Services.addInterceptor(WEB3HistoryTradeDetailService.class, new WEB3HistoryTradeDetailServiceInterceptor());
        Services.addInterceptor(WEB3HistoryTradeDetailService.class, new WEB3LogSysTimeInterceptor());
        Services.addInterceptor(WEB3HistoryTradeDetailService.class, new TransactionalInterceptor(TransactionalInterceptor.TX_JOIN_EXISTING));

        //取引履歴一覧
        Services.addInterceptor(WEB3HistoryTradeHistoryListService.class, new WEB3HistoryTradeHistoryListServiceInterceptor());
        Services.addInterceptor(WEB3HistoryTradeHistoryListService.class, new WEB3LogSysTimeInterceptor());
        Services.addInterceptor(WEB3HistoryTradeHistoryListService.class, new TransactionalInterceptor(TransactionalInterceptor.TX_JOIN_EXISTING));


        // Message の登録処理 ----------------------

        //決済明細リクエスト
        regClass(WEB3HistorySettleDetailRequest.class);
        //決済明細レスポンス
        regClass(WEB3HistorySettleDetailResponse.class);

        //取引明細リクエスト
        regClass(WEB3HistoryTradeDetailRequest.class);
        //取引明細レスポンス
        regClass(WEB3HistoryTradeDetailResponse.class);

        //取引履歴一覧リクエスト
        regClass(WEB3HistoryTradeHistoryListRequest.class);
        //取引履歴一覧レスポンス
        regClass(WEB3HistoryTradeHistoryListResponse.class);

        //取引履歴一覧ファ@イルダウンロードリクエスト
        regClass(WEB3HistoryTradeHistoryDownloadRequest.class);
        //取引履歴一覧ファ@イルダウンロードレスポンス
        regClass(WEB3HistoryTradeHistoryDownloadResponse.class);

        //Handler の登録処理 ----------------------

        //決済明細 用ハンドラーの登録
        regHandler(WEB3TradeHistoryAppPlugin.class, WEB3HistorySettleDetailRequest.class, WEB3HistorySettleDetailHandler.class, "getSettleDetailScreen");

        //取引明細 用ハンドラーの登録
        regHandler(WEB3TradeHistoryAppPlugin.class, WEB3HistoryTradeHistoryListRequest.class, WEB3HistoryTradeHistoryListHandler.class, "getTradeHistoryListScreen");

        //取引履歴一覧 用ハンドラーの登録
        regHandler(WEB3TradeHistoryAppPlugin.class, WEB3HistoryTradeDetailRequest.class, WEB3HistoryTradeDetailHandler.class, "getTradeDetailScreen");

        //取引履歴一覧 用ハンドラーの登録
        regHandler(WEB3TradeHistoryAppPlugin.class, WEB3HistoryTradeHistoryDownloadRequest.class, WEB3HistoryTradeHistoryListHandler.class, "getTradeHistoryListDownload");

        log.exiting(STR_METHOD_NAME);
    }
}@
