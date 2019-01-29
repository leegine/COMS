head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.14.06.24.00;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	7984d7db4391837;
filename	WEB3AffinityPlugin.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
 Copyright        : (株)大和総研 証券ソリューションシステム第二部
 File Name        : Affinityプラグインクラス(WEB3AffinityPlugin.java)
 Author Name      : Daiwa Institute of Research
 Revesion History : 2004/09/08 劉 新規作成
 */
package webbroker3.system.tune.affinity;

import com.fitechlabs.xtrade.kernel.boot.*;
import com.fitechlabs.xtrade.kernel.interceptor.*;
import com.fitechlabs.xtrade.plugin.util.rac.*;
import webbroker3.system.tune.affinity.handler.*;
import webbroker3.system.tune.affinity.message.*;
import webbroker3.util.*;

/**
 * Affinityプラグインクラス
 *
 * @@author 劉
 * @@version 1.0
 */
public final class WEB3AffinityPlugin
    extends Plugin
{
    /**
     * ログ
     */
    private static WEB3LogUtility log =
        WEB3LogUtility.getInstance(WEB3AffinityPlugin.class);

    /**
     * コンストラクタ。
     */
    public WEB3AffinityPlugin()
    {
    }

    /**
     * プラグインエントリーポイント。
     */
    public static void plug() throws Exception
    {
        plug(WEB3AffinityPlugin.class);
    }

    /**
     * プラグイン処理。
     */
    public static void onPlug() throws Exception
    {
        // このプラグインより先に読み込む必要のあるプラグインの指定。
        KernelPlugin.plug();
        WEB3ServerConnectorPlugin.plug();

        //メッセージクラス
        regClass(WEB3AffinityDescendRequest.class);

        //ハンドラクラス
        regHandler(
            WEB3AffinityDescendRequest.class,
            WEB3AffinityDescendHandler.class,
            "handleWEB3AffinityDescendRequest");

        //サービスクラス
        Services.registerService(WEB3OrderReqNumberHead2ManageService.class,
                                 new WEB3OrderReqNumberHead2ManageServiceImpl());

        Services.registerService(WEB3DescendRacCtxService.class,
                                 new WEB3DescendRacCtxServiceImpl());

        //カスタム実装の登録
        try
        {
            MultiPoolJndiNameLookupService service = (MultiPoolJndiNameLookupService)
                Services.getService(
                MultiPoolJndiNameLookupService.class);
            if (service != null)
            {
                Services.overrideService(MultiPoolJndiNameLookupService.class,
                                         new WEB3MultiPoolJndiNameLookupServiceImpl());
            }
        }
        catch (Exception e)
        {
            Services.registerService(MultiPoolJndiNameLookupService.class,
                                     new WEB3MultiPoolJndiNameLookupServiceImpl());
        }

        // RacPlugin の制約により必ずTransactionalInterceptor で囲む必要があります。
        Services.addInterceptor(MultiPoolJndiNameLookupService.class,
                                new TransactionalInterceptor(TransactionalInterceptor.
            TX_CREATE_NEW));

    }

}
@
