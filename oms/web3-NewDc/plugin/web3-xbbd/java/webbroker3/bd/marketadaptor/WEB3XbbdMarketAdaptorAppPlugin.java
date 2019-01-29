head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.16.03.46.06;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	6004d80209d0226;
filename	WEB3XbbdMarketAdaptorAppPlugin.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : Webbroker3-Xbbd-MarketAdaptor プラグインクラス(WEB3XbbdMarketAdaptorAppPlugin.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2006/08/25 黄建 (中訊)  新規作成                
*/

package webbroker3.bd.marketadaptor;

import com.fitechlabs.xtrade.kernel.boot.KernelPlugin;
import com.fitechlabs.xtrade.kernel.boot.Plugin;
import com.fitechlabs.xtrade.kernel.boot.Services;
import com.fitechlabs.xtrade.plugin.tc.gentrade.FinApp;
import com.fitechlabs.xtrade.plugin.tc.gentrade.ProductTypeEnum;
import com.fitechlabs.xtrade.plugin.tc.gentrade.TradingModule;

import webbroker3.util.WEB3LogUtility;

/**
 * Webbroker3-Xbbd-MarketAdaptor プラグインクラス。
 *
 * @@author 黄建(中訊)
 * @@version 1.0
 */

public class WEB3XbbdMarketAdaptorAppPlugin extends Plugin
{
    /**
     * ログユーティリティ。
     */
    private static WEB3LogUtility log =
        WEB3LogUtility.getInstance(WEB3XbbdMarketAdaptorAppPlugin.class);

    /**
     * コンストラクタ。
     */
    public WEB3XbbdMarketAdaptorAppPlugin ()
    {
    }

    /**
     * プラグインエントリーポイント。
     */
    public static void plug() throws Exception
    {
        String METHOD_NAME = "plug()";
        log.entering(METHOD_NAME);
        plug(WEB3XbbdMarketAdaptorAppPlugin.class);
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

        FinApp l_finApp = (FinApp)Services.getService(FinApp.class);
        TradingModule l_tradingModule = l_finApp.getTradingModule(ProductTypeEnum.BOND);

        // Adapterの登録 --------------------
        // 市場リクエスト送信サービス
        log.debug("Xbbd: 市場リクエスト送信サービスをインストール........");
        l_tradingModule.getMarketAdapter().installMarketRequestSenderService(
            new WEB3BondMarketRequestService());
        log.debug("Xbbd: 市場リクエスト送信サービスをインストール........OK");
        log.exiting(METHOD_NAME);

    }
}
@
