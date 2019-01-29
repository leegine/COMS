head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.22.07.48.08;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	7944d8853e14978;
filename	WEB3MQGatewayManagerPlugin.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/*
 Copyright        : (株)大和総研 証券ソリューションシステム第二部
 File Name        : WEB3MQGatewayManagerPluginクラス(WEB3MQGatewayManagerPlugin.java)
 Author Name      : Daiwa Institute of Research
 Revesion History : 2005/01/31 山田　@卓司(FLJ) 新規作成
 */
package webbroker3.mqgateway.manager;

import webbroker3.mqgateway.manager.handler.WEB3MQSendMessageHandler;
import webbroker3.mqgateway.manager.message.WEB3MQSendMessageRequest;
import webbroker3.mqgateway.manager.message.WEB3MQSendMessageResponse;
import webbroker3.mqgateway.manager.service.delegate.WEB3MQManagementService;
import webbroker3.mqgateway.manager.service.delegate.stdimpls.WEB3MQManagementServiceImpl;

import com.fitechlabs.xtrade.kernel.boot.KernelPlugin;
import com.fitechlabs.xtrade.kernel.boot.Plugin;
import com.fitechlabs.xtrade.kernel.boot.Services;

/**
 * 
 *
 * @@author Takuji Yamada (FLJ)
 * @@version 1.0
 */
public class WEB3MQGatewayManagerPlugin extends Plugin
{

    public WEB3MQGatewayManagerPlugin()
    {
    }

    public static void plug() throws Exception
    {
        plug(WEB3MQGatewayManagerPlugin.class);
    }

    public static void onPlug() throws Exception
    {
        KernelPlugin.plug();

        Services.registerService(
            WEB3MQManagementService.class,
            new WEB3MQManagementServiceImpl());

        regClass(WEB3MQSendMessageRequest.class);
        regClass(WEB3MQSendMessageResponse.class);

        regHandler(
            WEB3MQGatewayManagerPlugin.class,
            WEB3MQSendMessageRequest.class, 
            WEB3MQSendMessageHandler.class,
            "sendMessage");

    }

}@
