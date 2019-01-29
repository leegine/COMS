head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.16.05.36.50;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	8f04d80403d696d;
filename	WEB3AioMarketAdaptorAppPlugin.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : Webbroker3-Aio-MarketAdaptor プラグインクラス(WEB3AioMarketAdaptorAppPlugin.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2004/10/20 王蘭芬 (中訊)  新規作成
                   
*/
package webbroker3.aio.marketadaptor;

import webbroker3.util.WEB3LogUtility;

import com.fitechlabs.xtrade.kernel.boot.KernelPlugin;
import com.fitechlabs.xtrade.kernel.boot.Plugin;
import com.fitechlabs.xtrade.kernel.boot.Services;
import com.fitechlabs.xtrade.plugin.tc.gentrade.FinApp;
import com.fitechlabs.xtrade.plugin.tc.gentrade.TradingModule;
import com.fitechlabs.xtrade.plugin.tc.gentrade.ProductTypeEnum;

/**
 * Webbroker3-Aio-MarketAdaptor プラグインクラス。<br>
 *<br> 
 * 
 * @@author 王蘭芬 (中訊)
 * @@version 1.0
 */
public final class WEB3AioMarketAdaptorAppPlugin extends Plugin
{
    /**
     * ログユーティリティ。
     */
    private static WEB3LogUtility log =
        WEB3LogUtility.getInstance(WEB3AioMarketAdaptorAppPlugin.class);

    /**
     * コンストラクタ。
     */
    public WEB3AioMarketAdaptorAppPlugin ()
    {
    }

    /**
     * プラグインエントリーポイント。
     */
    public static void plug() throws Exception
    {
        String l_strMETHOD_NAME = "plug()";
        log.entering(l_strMETHOD_NAME);
        plug(WEB3AioMarketAdaptorAppPlugin.class);
        log.exiting(l_strMETHOD_NAME);
    }

    /**
     * プラグイン処理。
     */
    public static void onPlug() throws Exception
    {
        String l_strMETHOD_NAME = "onPlug()";
        log.entering(l_strMETHOD_NAME);

        // このプラグインより先に読み込む必要のあるプラグインの指定。
        // install the system plugins that we need
        KernelPlugin.plug();

        FinApp l_finApp = (FinApp)Services.getService(FinApp.class);
        TradingModule l_tradingModule = l_finApp.getTradingModule(ProductTypeEnum.AIO);

        // Adapterの登録 --------------------
        // 市場リクエスト送信サービス
        log.info("XBAIO: 市場リクエスト送信サービスをインストール........");
        l_tradingModule.getMarketAdapter().installMarketRequestSenderService(
            new WEB3AioMarketRequestSenderServiceImpl());
        log.info("XBAIO: 市場リクエスト送信サービスをインストール........OK");
        log.exiting(l_strMETHOD_NAME);

    }
}
@
