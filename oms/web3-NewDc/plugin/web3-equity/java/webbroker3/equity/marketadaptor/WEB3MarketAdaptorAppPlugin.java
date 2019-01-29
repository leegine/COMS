head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.14.05.46.25;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	8884d7d97833a75;
filename	WEB3MarketAdaptorAppPlugin.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : Webbroker3-MarketAdaptor プラグインクラス(WEB3MarketAdaptorAppPlugin.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2004/02/19 髙橋　@良和(SRA) 新規作成
                   2005/01/05 岡村　@和明(SRA) JavaDoc修正
*/
package webbroker3.equity.marketadaptor;

import com.fitechlabs.xtrade.kernel.boot.KernelPlugin;
import com.fitechlabs.xtrade.kernel.boot.Plugin;
import com.fitechlabs.xtrade.kernel.boot.Services;
import com.fitechlabs.xtrade.plugin.tc.gentrade.FinApp;
import com.fitechlabs.xtrade.plugin.tc.gentrade.TradingModule;

import webbroker3.util.WEB3LogUtility;

/**
 * （Webbroker3-MarketAdaptor プラグインクラス）。<br>
 *<br>
 * @@author 髙橋　@良和(SRA)
 * @@version 1.0
 */
public final class WEB3MarketAdaptorAppPlugin extends Plugin
{
    /**
     * (ログユーティリティ。)
     */
    private static WEB3LogUtility log = 
        WEB3LogUtility.getInstance(WEB3MarketAdaptorAppPlugin.class);
    
    /**
     * （コンストラクタ）。 
     */
    public WEB3MarketAdaptorAppPlugin ()
    {
    }

    /**
     * （プラグインエントリーポイント）。 
     */
    public static void plug() throws Exception
    {
        String METHOD_NAME = "plug()";
        log.entering(METHOD_NAME);
        plug(WEB3MarketAdaptorAppPlugin.class);
        log.exiting(METHOD_NAME);
    }

    /**
     * （プラグイン処理）。 
     */
    public static void onPlug() throws Exception
    {
        String METHOD_NAME = "onPlug()";
        log.entering(METHOD_NAME);

        // このプラグインより先に読み込む必要のあるプラグインの指定。
        // install the system plugins that we need
        KernelPlugin.plug();

        // DatabaseExtensions のプラグイン処理 ----------------------
        // WEB3MarketSessionDatabaseExtensions をプラグイン
        // WEB3MarketSessionDatabaseExtensions.plug();
        //WEB3EquityMarketSessionDatabaseExtensions.plug();

        FinApp l_finApp = (FinApp)Services.getService(FinApp.class);
        TradingModule l_tradingModule = l_finApp.getTradingModule("eqtype");

        // Adapterの登録 --------------------
        // 市場リクエスト送信サービス
        l_tradingModule.getMarketAdapter().installMarketRequestSenderService(
            new WEB3EquityMarketRequestSenderServiceImpl());

        log.exiting(METHOD_NAME);
        
    }
}
@
