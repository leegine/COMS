head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.16.03.46.51;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	6004d80209d0226;
filename	WEB3MutualFundMarketAdaptorAppPlugin.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : Webbroker3-MF-MarketAdaptor プラグインクラス(WEB3MFMarketAdaptorAppPlugin.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2004/08/25 王蘭芬 (中訊)  新規作成
                   
*/
package webbroker3.mf.marketadaptor;

import webbroker3.util.WEB3LogUtility;

import com.fitechlabs.xtrade.kernel.boot.KernelPlugin;
import com.fitechlabs.xtrade.kernel.boot.Plugin;
import com.fitechlabs.xtrade.kernel.boot.Services;
import com.fitechlabs.xtrade.plugin.tc.gentrade.FinApp;
import com.fitechlabs.xtrade.plugin.tc.gentrade.TradingModule;
import com.fitechlabs.xtrade.plugin.tc.gentrade.ProductTypeEnum;

/**
 * Webbroker3-MF-MarketAdaptor プラグインクラス。<br>
 *<br>
 * 
 * @@author 王蘭芬 (中訊)
 * @@version 1.0
 */
public final class WEB3MutualFundMarketAdaptorAppPlugin extends Plugin
{
    /**
     * ログユーティリティ。
     */
    private static WEB3LogUtility log =
        WEB3LogUtility.getInstance(WEB3MutualFundMarketAdaptorAppPlugin.class);

    /**
     * コンストラクタ。
     */
    public WEB3MutualFundMarketAdaptorAppPlugin ()
    {
    }

    /**
     * プラグインエントリーポイント。
     */
    public static void plug() throws Exception
    {
        String METHOD_NAME = "plug()";
        log.entering(METHOD_NAME);
        plug(WEB3MutualFundMarketAdaptorAppPlugin.class);
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
        TradingModule l_tradingModule = l_finApp.getTradingModule(ProductTypeEnum.MUTUAL_FUND);

        // Adapterの登録 --------------------
        // 市場リクエスト送信サービス
        log.debug("XBMF: 市場リクエスト送信サービスをインストール........");
        l_tradingModule.getMarketAdapter().installMarketRequestSenderService(
            new WEB3MutualFundMarketRequestService());
        log.debug("XBMF: 市場リクエスト送信サービスをインストール........OK");
        log.exiting(METHOD_NAME);

    }
}
@
