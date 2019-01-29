head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.14.05.45.55;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	8884d7d97833a75;
filename	WEB3MarginOrderNotifyResponse.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        :  信用取引注文通知レスポンスクラス(WEB3MarginOrderNotifyResponse.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2004/9/15 李松峰 (中訊) 新規作成
*/
package webbroker3.equity.message;

import webbroker3.common.message.WEB3BackResponse;

/**
 * （信用取引注文通知レスポンス）。<br>
 * <br>
 * 信用取引注文通知レスポンスクラス
 * @@version 1.0
 */
public class WEB3MarginOrderNotifyResponse extends WEB3BackResponse 
{
    /**
     * (PTYPE)<BR>
     */
    public static final String PTYPE = "margin_orderNotify";

    /**
     * (SerialVersionUID)<BR>
     */
    public static final long serialVersionUID = 200409101617L;
    
    /**
     * @@roseuid 414037850146
     */
    public WEB3MarginOrderNotifyResponse() 
    {
     
    }
    
    /**
     * (コンストラクタ。)<BR>
     * 引数で与えられたリクエストオブジェクトを基に<BR>
     * レスポンスオブジェクトを生成する。<BR>
     *<BR>
     * @@param l_request リクエストオブジェクト
     */
    public WEB3MarginOrderNotifyResponse(WEB3MarginOrderNotifyRequest l_request)
    {
       super(l_request);
    }
}
@
