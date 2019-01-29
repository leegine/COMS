head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.14.09.44.59;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	7a84d7de2372e68;
filename	WEB3StartQuoteServiceRequest.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : 時価サービスを起動するためのメッセージ(WEB3StartQuoteServiceRequest.java)
Author Name      : Daiwa Institute of Research
Revision History : 2004/05/18 山田　@卓司(FLJ) 新規作成
*/
package webbroker3.quoteadaptor.stdimpls.message;

import com.fitechlabs.xtrade.kernel.message.Request;

/**
 * 時価サービスを起動するためのメッセージ
 * 
 * @@author Takuji Yamada
 * @@version 1.0
 */
public class WEB3StartQuoteServiceRequest extends Request
{

    /**
     * PTYPEプロパティ（このメッセージの種類を表す。）
     * {@@value} 
     */
    public static final String PTYPE = "start_quote_service";

    /**
     * 起動する時価サービスのIDを表すプロパティ
     */
    public String service_id;

    /**
     * コンストラクタ
     */
    public WEB3StartQuoteServiceRequest()
    {
    }

}
@
