head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.16.05.11.46;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	8f04d80403d696d;
filename	WEB3AioOutputNotifyRequest.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : 出庫通知リクエスト(WEB3AioOutputNotifyRequest)
Author Name      : Daiwa Institute of Research
Revesion History : 2005/10/05 韋念瓊 (中訊) 新規作成   
*/

package webbroker3.aio.message;

import webbroker3.common.message.WEB3BackRequest;
import webbroker3.common.message.WEB3BackResponse;

/**
 * (出庫通知リクエスト) <BR>
 * 出庫通知リクエストクラス <BR>
 * 
 * @@author 韋念瓊(中訊)
 * @@version 1.0
 */
public class WEB3AioOutputNotifyRequest extends WEB3BackRequest
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
     * @@roseuid 41E780B2008C
     */
    public WEB3AioOutputNotifyRequest()
    {
        
    }

    /**
     * @@return WEB3GenResponse
     * @@roseuid 41E780B200CB
     */
    public WEB3BackResponse createResponse()
    {
        return new WEB3AioOutputNotifyResponse(this);
    }
}@
