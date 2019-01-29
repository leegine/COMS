head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.22.08.22.10;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	7944d885c296483;
filename	WEB3RlsConnectorPlugin.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
 Copyright        : (株)大和総研 証券ソリューションシステム第二部
 File Name        : WEB3RlsConnectorPluginプラグインのプラグインクラス(WEB3RlsConnectorPlugin.java)
 Author Name      : Daiwa Institute of Research
 Revesion History : 2006/05/10 FLJ齋藤　@新規作成
 */
package webbroker3.rlsgateway.connector;

import com.fitechlabs.xtier.kernel.XtierKernel;
import com.fitechlabs.xtrade.kernel.boot.*;
import com.fitechlabs.xtrade.plugin.tc.gentrade.GenericTradingPlugin;

import webbroker3.util.WEB3LogUtility;

/**
 * <p>
 * WEB3RlsConnectorPluginプラグインのプラグインクラス。<br>
 * <br>
 * </p>
 *
 * @@author FLJ齋藤
 * @@version 1.0
 */
public class WEB3RlsConnectorPlugin
    extends Plugin
{

    /**
     * ログ出力ユーティリティ。
     */
    private static WEB3LogUtility log =
        WEB3LogUtility.getInstance(WEB3RlsConnectorPlugin.class);

    /**
     * デフォルトコンストラクタ。
     */
    public WEB3RlsConnectorPlugin()
    {
    }

    public static void plug() throws Exception
    {
        plug(WEB3RlsConnectorPlugin.class);
    }

    /**
     * このプラグインクラスのメインメソッド
     */
    public static void onPlug() throws Exception
    {
        WEB3RlsConnectorService l_rlsConnectorService = null; 
        
        try
        {
            setProperties();

            l_rlsConnectorService = new WEB3RlsConnectorServiceImpl();
            
            KernelPlugin.plug();
            
            GenericTradingPlugin.plug();
            
            WEB3RlsXtierUtil.startXtier();
            
            l_rlsConnectorService.setIsXtierStarted(true);
            
            l_rlsConnectorService.prepareConnection2Rls();
        }
        catch (Exception e)
        {
            log.warn(e.getMessage(), e);
        }
        finally
        {
            Services.registerService(WEB3RlsConnectorService.class, l_rlsConnectorService);
        }
        
        log.info("WEB3RlsConnectorPlugin was plugged.");
    }
    
    protected static void setProperties()
    {
        System.setProperty("sun.rmi.dgc.client.gcInterval", String.valueOf(Long.MAX_VALUE-1));
        System.setProperty("sun.rmi.dgc.server.gcInterval", String.valueOf(Long.MAX_VALUE-1));
    }

    public static void onUnplug() throws Exception
    {
        log.info("xtier stopping...");
        XtierKernel xtier = XtierKernel.getInstance();
        log.info("xtier.getState()=" + xtier.getState());

        if (xtier.getState() == XtierKernel.KERNEL_STARTED)
        {

            try
            {
                xtier.stop();

                log.info("xTier stopped ok.");
            }
            catch (Throwable t)
            {
                log.error("xTier kernel failed to stop.", t);
            }
        }

    }
}
@
