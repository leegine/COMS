head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.14.09.44.50;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	7a84d7de2372e68;
filename	WEB3StopQuoteServiceBroadcastRequest.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : クラスタ内の全てのサーバ上で時価サービスを停止するためのメッセージ(WEB3StopQuoteServiceBroadcastRequest.java)
Author Name      : Daiwa Institute of Research
Revision History : 2004/05/18 山田　@卓司(FLJ) 新規作成
*/
package webbroker3.quoteadaptor.stdimpls.message;

import com.fitechlabs.xtrade.kernel.message.Request;

/**
 * クラスタ内の全てのサーバ上で時価サービスを停止するためのメッセージ
 * 
 * @@author Takuji Yamada
 * @@version 1.0
 */
public class WEB3StopQuoteServiceBroadcastRequest extends Request
{

    /**
     * PTYPEプロパティ（このメッセージの種類を表す。）
     * {@@value} 
     */
    public static final String PTYPE = "stop_quote_service_broadcast";

    /**
     * 停止する時価サービスのIDを表すプロパティ
     */
    public String service_id;

    /**
     * コンストラクタ
     */
    public WEB3StopQuoteServiceBroadcastRequest()
    {
    }

}
@
