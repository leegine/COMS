head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.22.07.47.42;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	7944d8853e14978;
filename	WEB3MQSendResult.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : MQへのメッセージの送信結果を表すクラス(WEB3MQSendResult.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2003/02/13 山田　@卓司(FLJ) 新規作成
*/
package webbroker3.mqgateway;

import com.fitechlabs.xtrade.kernel.message.ErrorInfo;

/**
 * MQへのメッセージの送信結果を表すクラス。<BR>
 * <BR>
 * @@author Takuji Yamada
 * @@version 1.0
 * @@see webbroker3.mqgateway.WEB3MQGatewayService#send(webbroker3.mqgateway.WEB3MQMessageSpec)
 */
public interface WEB3MQSendResult
{
    /**
     * エラー情報を取得する。<BR>
     * <BR>
     * @@return エラー情報
     */
    public ErrorInfo getErrorInfo();
    
    /**
     * 結果が失敗の場合に<code>true</code>を返す。それ以外は<code>false</code>を返す。
     */
    public boolean isFailedResult();
    
    /**
     * 結果が成功の場合に<code>true</code>を返す。それ以外は<code>false</code>を返す。
     */
    public boolean isSuccessResult();
}@
