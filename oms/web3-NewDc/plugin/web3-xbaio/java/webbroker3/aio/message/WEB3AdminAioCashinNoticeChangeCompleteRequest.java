head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.16.05.02.00;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	8f04d80403d696d;
filename	WEB3AdminAioCashinNoticeChangeCompleteRequest.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : 入金通知訂正完了リクエストクラス(WEB3AdminAioCashinNoticeChangeCompleteRequest)
Author Name      : Daiwa Institute of Research
Revesion History : 2006/01/21 韋念瓊 (中訊) 新規作成
*/

package webbroker3.aio.message;

import webbroker3.common.message.WEB3GenResponse;


/**
 * (入金通知訂正完了リクエスト)<BR>
 * 入金通知訂正完了リクエストクラス
 * 
 * @@author 韋念瓊(中訊)
 * @@version 1.0 
 */

public class WEB3AdminAioCashinNoticeChangeCompleteRequest extends WEB3AdminAioCashinChangeCommonRequest 
{
    /**
     * PTYPE<BR>
     */
    public final static  String PTYPE = "admin_aio_cashin_notice_change_complete";
        
    /**
     * serialVersionUID<BR>
     */
    public final static long serialVersionUID = 200601211145L;  
    
    /**
     * (暗証番号)
     */
    public String password;
        
    /**
     * （createResponseの実装）<BR>
     * <BR>
     * 入金通知訂正完了レスポンスオブジェクトを生成して返す。<BR>
     * @@return WEB3GenResponse
     * @@roseuid 4158EB620327
     */
    public WEB3GenResponse createResponse() 
    {
        return new WEB3AdminAioCashinNoticeChangeCompleteResponse(this);
    }
}
@
