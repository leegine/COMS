head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.16.03.22.28;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	6004d80209d0226;
filename	WEB3FuturesChangeCancelNotifyResponse.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : 先物OP注文受付更新インタセプタ(WEB3IfoAcceptedUpdateInterceptor.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2004/7/22 盧法@旭 (中訊) 新規作成
*/


package webbroker3.ifo.message;

import webbroker3.common.message.WEB3BackRequest;
import webbroker3.common.message.WEB3BackResponse;


/**
 * (株価指数先物訂正取消通知レスポンス)<BR>
 * 株式指数先物訂正取消通知レスポンスクラス<BR>
 * @@author 盧法@旭
 * @@version 1.0
 */
public class WEB3FuturesChangeCancelNotifyResponse extends WEB3BackResponse 
{
    public final static String PTYPE ="futures_changeCancelNotify";
    /**
     * SerialVersionUID<BR>
     */
    public final static long serialVersionUID = 200407221032L;  
    /**
     * @@roseuid 40F7AE1A02DE
     */
    public WEB3FuturesChangeCancelNotifyResponse() 
    {
     
    }
    /**
     * コンストラクタ。引数で与えられたリクエストオブジェクトを基に<BR>
     * レスポンス先物クトを生成する。<BR>
     * <BR>
     * @@param l_request リクエストオブジェクト
     */
    protected WEB3FuturesChangeCancelNotifyResponse(WEB3BackRequest l_request)
    {
        super(l_request);
    }
}
@
