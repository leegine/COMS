head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.16.03.46.18;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	6004d80209d0226;
filename	WEB3IfoMarketAdaptorAppPlugin.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : Webbroker3-ifo-MarketAdaptor プラグインクラス(WEB3IfoMarketAdaptorAppPlugin.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2004/02/19 髙橋　@良和(SRA) 新規作成
                   2004/06/17 李強(中訊)  修正
*/
package webbroker3.ifo.marketadaptor;

import webbroker3.util.WEB3LogUtility;

import com.fitechlabs.xtrade.kernel.boot.KernelPlugin;
import com.fitechlabs.xtrade.kernel.boot.Plugin;
import com.fitechlabs.xtrade.kernel.boot.Services;
import com.fitechlabs.xtrade.plugin.tc.gentrade.FinApp;
import com.fitechlabs.xtrade.plugin.tc.gentrade.TradingModule;
import com.fitechlabs.xtrade.plugin.tc.gentrade.ProductTypeEnum;

/**
 * Webbroker3-ifo-MarketAdaptor プラグインクラス。<br>
 *<br>
 * 
 * @@author 李強
 * @@version 1.0
 */
public final class WEB3IfoMarketAdaptorAppPlugin extends Plugin
{
    /**
     * ログユーティリティ。
     */
    private static WEB3LogUtility log =
        WEB3LogUtility.getInstance(WEB3IfoMarketAdaptorAppPlugin.class);

    /**
     * コンストラクタ。
     */
    public WEB3IfoMarketAdaptorAppPlugin ()
    {
    }

    /**
     * プラグインエントリーポイント。
     */
    public static void plug() throws Exception
    {
        String METHOD_NAME = "plug()";
        log.entering(METHOD_NAME);
        plug(WEB3IfoMarketAdaptorAppPlugin.class);
        log.exiting(METHOD_NAME);
    }

    /**
     * プラグイン処理。
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
        //WEB3MarketSessionDatabaseExtensions.plug();

        FinApp l_finApp = (FinApp)Services.getService(FinApp.class);
        TradingModule l_tradingModule = l_finApp.getTradingModule(ProductTypeEnum.IFO);

        // Adapterの登録 --------------------
        // 市場リクエスト送信サービス
        log.debug("XBIFO: 市場リクエスト送信サービスをインストール");
        l_tradingModule.getMarketAdapter().installMarketRequestSenderService(
            new WEB3IfoMarketRequestSenderServiceImpl());

        log.exiting(METHOD_NAME);

    }
}
@
