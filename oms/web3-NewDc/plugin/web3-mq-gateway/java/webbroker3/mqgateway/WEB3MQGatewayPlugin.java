head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.22.07.47.47;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	7944d8853e14978;
filename	WEB3MQGatewayPlugin.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : MQGatewayプラグインのプラグインクラス(WEB3MQGatewayPlugin.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2003/02/13 山田　@卓司(FLJ) 新規作成
*/
package webbroker3.mqgateway;

import com.fitechlabs.xtrade.kernel.boot.KernelPlugin;
import com.fitechlabs.xtrade.kernel.boot.Plugin;
import com.fitechlabs.xtrade.kernel.boot.Services;
import com.fitechlabs.xtrade.kernel.util.log.Logit;

import webbroker3.mqgateway.manager.WEB3MQGatewayManagerPlugin;
import webbroker3.mqgateway.stdimpls.WEB3MQGatewayServiceImpl;
import webbroker3.mqgateway.stdimpls.WEB3MQMessageSenderServiceImpl;
import webbroker3.mqgateway.stdimpls.data.MqGatewayMasterDatabaseExtensions;

/**
 * <p>
 * MQGatewayプラグインのプラグインクラス。<br>
 * <br>
 * このPluginはアプリケーションから、<br>
 * MQへメッセージを送信するときのGatewayとなるサービスを提供する。<br>
 * </p>
 * 
 * @@author Takuji Yamada
 * @@version 1.0
 */
public class WEB3MQGatewayPlugin extends Plugin
{
    
    public static final String PREF_NAME_QUEUE_ID_PREFIX =
        "webbroker3.mqgateway.queueid.";
    
    public static final String MQ_GATEAY_INTERCEPTOR_ATTRIBUTE_NAME = 
        "webbroker3.mqgateway.interceptor";

    public static final String MESSAGE_SPEC_ATTRIBUTE_NAME =
        "webbroker3.mqgateway.messagespec";
    
    private static final Logit log =
        Logit.getInstance(WEB3MQGatewayPlugin.class);
    
    private static boolean isPlugged = false;
    
    /**
     * デフォルトコンストラクタ。
     */
    public WEB3MQGatewayPlugin() {
    }
    
    public static void plug() throws Exception {
        plug(WEB3MQGatewayPlugin.class);
    }
    
    /**
     * このプラグインクラスのメインメソッド
     */
    public static void onPlug() throws Exception {
        
        KernelPlugin.plug();
        
        // テーブル情報を登録
        MqGatewayMasterDatabaseExtensions.plug();
        
        // Gatewayサービスを登録
        Services.registerService(
            WEB3MQGatewayService.class,
            new WEB3MQGatewayServiceImpl());
        
        // Senderサービスを登録
        Services.registerService(
            WEB3MQMessageSenderService.class,
            new WEB3MQMessageSenderServiceImpl());
        
//        // MQGateway管理用プラグインを起動
//        // TODO 本当にここで管理用プラグインを起動するか要検討
//        WEB3MQGatewayManagerPlugin.plug();
        
        isPlugged = true;
        
        log.info("WEB3MQGatewayPlugin was plugged.");
    }
    
    public static boolean isPlugged() {
        return isPlugged;
    }
}
@
