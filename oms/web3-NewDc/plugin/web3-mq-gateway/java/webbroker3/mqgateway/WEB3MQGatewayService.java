head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.22.07.47.55;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	7944d8853e14978;
filename	WEB3MQGatewayService.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : MQGatewayServiceのServiceクラス(WEB3MQGatewayService.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2003/02/13 山田　@卓司(FLJ) 新規作成
*/
package webbroker3.mqgateway;

import com.fitechlabs.xtrade.kernel.boot.Service;

/**
 * <p>
 * アプリケーションからMQに対してメッセージを送信するときのGatewayとなるサービス。<br>
 * アプリケーションは、サービスのsend()メソッドを呼び出すことにより、MQに対してメッセージを送信する。<br>
 * </p>
 * <p>
 * サービスの使用方法@：<br>
 * <code>
 * <pre>
 * WEB3MQMessageSpec spec = // 送信する内容;<br>
 * WEB3MQGatewayService gateway =<br>
 *     (WEB3MQGatewayService) Services.getService(WEB3MQGatewayService.class);<br>
 * WEB3MQSendResult result = gateway.send(spec);<br>
 * if (result.isFailedResult()) {<br>
 *     ErrorInfo errorInfo = result.getErrorInfo();<br>
 *     // エラー処理<br>
 * }<br>
 * </pre>
 * </code>
 * </p>
 * @@author Takuji Yamada
 * @@version 1.0
 */
public interface WEB3MQGatewayService extends Service
{
    /**
     * <p>
     * パラメータで指定されたMQMessageSpecからMQに送信するメッセージを生成し、<br>
     * MQにメッセージを送信する。<br>
     * </p>

     *      * @@param spec MQに送信するメッセージのスペック
     * @@return 送信結果
     */
    public WEB3MQSendResult send(WEB3MQMessageSpec spec);
    
}
@
