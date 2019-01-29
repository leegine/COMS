head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.16.05.12.25;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	8f04d80403d696d;
filename	WEB3AioOutputNotifyResponse.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : 出庫通知レスポンス(WEB3AioOutputNotifyResponse)
Author Name      : Daiwa Institute of Research
Revesion History : 2005/10/05 韋念瓊 (中訊) 新規作成
*/

package webbroker3.aio.message;

import webbroker3.common.message.WEB3BackRequest;
import webbroker3.common.message.WEB3BackResponse;

/**
 * (出庫通知レスポンス) <BR>
 * 出庫通知レスポンスクラス <BR>
 * 
 * @@author 韋念瓊(中訊)
 * @@version 1.0
 */
public class WEB3AioOutputNotifyResponse extends WEB3BackResponse
{
    /**
     * PTYPE <BR>
     */
    public final static String PTYPE = "aio_output_notify";

    /**
     * serialVersionUID <BR>
     */
    public final static long serialVersionUID = 200501171454L;
   
    /**
     * @@roseuid 41E780B201C5
     */
    public WEB3AioOutputNotifyResponse()
    {
    }
    
    /**
     * コンストラクタ。引数で与えられたリクエストオブジェクトを基に<BR>
     * レスポンスオブジェクトを生成する。<BR>
     *<BR>
     * @@param l_request リクエストオブジェクト
     */
    protected WEB3AioOutputNotifyResponse(WEB3BackRequest l_request)
    {
        super(l_request);
    } 
}@
