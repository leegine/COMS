head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.16.05.20.13;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	8f04d80403d696d;
filename	WEB3AioCashinCooperationNotifyRequest.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : 入金連携通知リクエスト(WEB3AioCashinCooperationNotifyRequest)
Author Name      : Daiwa Institute of Research
Revesion History : 2006/01/11 黄建(中訊) 新規作成
*/

package webbroker3.aio.message;

import webbroker3.common.message.WEB3BackRequest;
import webbroker3.common.message.WEB3BackResponse;

/**
 * 入金連携通知リクエスト<BR>
 * 
 * @@author 黄建(中訊)
 * @@version 1.0
 */

public class WEB3AioCashinCooperationNotifyRequest extends WEB3BackRequest 
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
     * (スレッドNo) <BR>
     * スレッドNo
     */
    public Long threadNo;
    
    /**
     * @@roseuid 41E78FFE0242
     */
    public WEB3AioCashinCooperationNotifyRequest()
    {
        
    }
    
    /**
     * （createResponseの実装） <BR>
     * <BR>
     * 入金連携通知レスポンスオブジェクトを生成して返す。 <BR>
     * @@return WEB3BackResponse
     * @@roseuid 40A4326A02DD
     */
    public WEB3BackResponse createResponse() 
    {
        return new WEB3AioCashinCooperationNotifyResponse(this);
    }
}
@
