head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.22.07.47.38;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	7944d8853e14978;
filename	WEB3MQGatewayInterceptor.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
 Copyright        : (株)大和総研 証券ソリューションシステム第二部
 File Name        : WEB3MQGatewayInterceptorクラス(WEB3MQGatewayInterceptor.java)
 Author Name      : Daiwa Institute of Research
 Revision History : 2005/01/26 山田　@卓司 (FLJ) 新規作成
                  : 2005/02/18 山田　@卓司 (FLJ) onReturnでコンテキストに情報が設定されていない場合の処理を変更
                  : 2003/03/07 山田　@卓司 (FLJ) 複数のメッセージ送信できるように変更
 */
package webbroker3.mqgateway;

import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;

import webbroker3.common.WEB3SystemLayerException;
import webbroker3.util.WEB3LogUtility;

import com.fitechlabs.xtrade.kernel.boot.Services;
import com.fitechlabs.xtrade.kernel.interceptor.Interceptor;
import com.fitechlabs.xtrade.kernel.util.ThreadLocalSystemAttributesRegistry;

/**
 * 
 * 
 * @@author Takuji Yamada (FLJ)
 */
public class WEB3MQGatewayInterceptor implements Interceptor
{

    /**
     * ログ出力ユーティリティ
     */
    private static final WEB3LogUtility LOG = WEB3LogUtility
            .getInstance(WEB3MQGatewayInterceptor.class);
    
    /**
     * デバックフラグ
     */
    private static final boolean DBG = LOG.ison();

    /**
     * @@see com.fitechlabs.xtrade.kernel.interceptor.Interceptor#onCall(java.lang.reflect.Method, java.lang.Object[])
     */
    public Object onCall(Method l_method, Object[] l_objArguments)
    {
        // コンテキスト情報を設定
        ThreadLocalSystemAttributesRegistry.setAttribute(
                WEB3MQGatewayPlugin.MQ_GATEAY_INTERCEPTOR_ATTRIBUTE_NAME,
                new ArrayList());
        ThreadLocalSystemAttributesRegistry.setAttribute(
                WEB3MQGatewayPlugin.MQ_GATEAY_INTERCEPTOR_ATTRIBUTE_NAME,
                Boolean.TRUE);
        return null;
    }

    /**
     * @@see com.fitechlabs.xtrade.kernel.interceptor.Interceptor#onReturn(java.lang.Object, java.lang.Object)
     */
    public void onReturn(Object l_objContext, Object l_objReturnValue)
    {

        // 送信内容を取得
        List l_container = (List) ThreadLocalSystemAttributesRegistry
                .getAttribute(WEB3MQGatewayPlugin.MESSAGE_SPEC_ATTRIBUTE_NAME);

        // 送信内容が設定されていない場合
        if (l_container == null || l_container.size() == 0)
        {
            removeContext();
            LOG.debug("WEB3MQMessageSpecが設定されていないためMQトリガーを発行しませんでした。");
            return;
        }

        // MQトリガーを発行
        for (int i = 0; i < l_container.size(); i++)
        {
            WEB3MQMessageSpec l_messageSpec = 
                (WEB3MQMessageSpec) l_container.get(i);
            if (l_messageSpec != null)
            {
                send(l_messageSpec);
            }
        }

        // コンテキスト情報をクリア
        removeContext();

    }

    /**
     * @@see com.fitechlabs.xtrade.kernel.interceptor.Interceptor#onThrowable(java.lang.Object, java.lang.Throwable)
     */
    public void onThrowable(Object l_objContext, Throwable l_throwable)
    {
        // コンテキスト情報をクリア
        removeContext();
    }

    /**
     * MQトリガーを発行する。
     * 
     * @@param l_messageSpec 送信内容
     */
    private void send(WEB3MQMessageSpec l_messageSpec)
    {
        // Senderサービスを取得
        WEB3MQMessageSenderService l_sender = (WEB3MQMessageSenderService) Services
                .getService(WEB3MQMessageSenderService.class);
        try
        {
            // MQトリガー発行
            WEB3MQSendResult l_result = l_sender.send(l_messageSpec);
            if (l_result.isSuccessResult())
            {
                if (DBG) {
                    LOG.debug("MQトリガーを発行しました!!! [WEB3MQMessageSpec="
                            + l_messageSpec.toString() + "]");
                }
            } else
            {
                LOG.warn("MQトリガーの発行に失敗しました!!! [WEB3MQMessageSpec="
                        + l_messageSpec.toString() + ", エラー情報"
                        + l_result.getErrorInfo().toString() + "]");
            }
        } catch (WEB3SystemLayerException l_sle)
        {
            LOG.error("MQトリガーの発行に失敗しました!!!  [WEB3MQMessageSpec="
                    + l_messageSpec.toString() + "]", l_sle);
        }
    }

    /**
     * コンテキスト情報をクリアする。
     */
    private void removeContext()
    {
        ThreadLocalSystemAttributesRegistry.setAttribute(
                WEB3MQGatewayPlugin.MQ_GATEAY_INTERCEPTOR_ATTRIBUTE_NAME, null);
        ThreadLocalSystemAttributesRegistry.setAttribute(
                WEB3MQGatewayPlugin.MESSAGE_SPEC_ATTRIBUTE_NAME, null);
    }

}@
