head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.16.04.58.57;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	8f04d80403d696d;
filename	WEB3AioCashinCooperationNotifyResponse.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : 入金連携通知レスポンス(WEB3AioCashinCooperationNotifyResponse)
Author Name      : Daiwa Institute of Research
Revesion History : 2006/01/11 黄建(中訊) 新規作成
*/

package webbroker3.aio.message;

import webbroker3.common.message.WEB3BackRequest;
import webbroker3.common.message.WEB3BackResponse;

/**
 * 入金連携通知レスポンス<BR>
 * 
 * @@author 黄建(中訊)
 * @@version 1.0
 */

public class WEB3AioCashinCooperationNotifyResponse extends WEB3BackResponse 
{
    /**
     * PTYPE <BR>
     */
    public final static String PTYPE = "aio_cashin_cooperation_notify";

    /**
     * serialVersionUID <BR>
     */
    public final static long serialVersionUID = 200601111656L;
    
    /**
     * (入金連携通知レスポンス)<BR>
     * デフォルトコンストラクタ<BR>
     * @@roseuid 40A9A18C001B
     */
    public WEB3AioCashinCooperationNotifyResponse() 
    {
     
    }
    
    /**
     * コンストラクタ。引数で与えられたリクエストオブジェクトを基に<BR>
     * レスポンスオブジェクトを生成する。<BR>
     *<BR>
     * @@param l_request リクエストオブジェクト
     */
    protected WEB3AioCashinCooperationNotifyResponse(WEB3BackRequest l_request)
    {
        super(l_request);
    }    
}
@
