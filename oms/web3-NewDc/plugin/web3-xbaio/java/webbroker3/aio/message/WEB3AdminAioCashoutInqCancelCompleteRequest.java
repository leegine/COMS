head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.16.04.53.38;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	8f04d80403d696d;
filename	WEB3AdminAioCashoutInqCancelCompleteRequest.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : 出金申込問合せ取消完了リクエストクラス(WEB3AdminAioCashoutInqCancelCompleteRequest)
Author Name      : Daiwa Institute of Research
Revesion History : 2004/10/13 韋念瓊 (中訊) 新規作成
                   2004/10/27 周勇(中訊) レビュー
                   2005/01/07 周勇 (中訊) 残対応 
*/

package webbroker3.aio.message;

import webbroker3.common.message.WEB3GenResponse;

/**
 * (出金申込問合せ取消完了リクエスト)<BR>
 * 出金申込問合せ取消完了リクエストクラス
 * 
 * @@author 韋念瓊(中訊)
 * @@version 1.0 
 */

public class WEB3AdminAioCashoutInqCancelCompleteRequest extends WEB3AdminAioCashoutInqCommonRequest 
{
    /**
     * PTYPE<BR>
     */
    public final static  String PTYPE = "admin_aio_cashout_inq_cancel_complete";
        
    /**
     * serialVersionUID<BR>
     */
    public final static long serialVersionUID = 200410131402L;        
    
    /**
     * (画面にて入力された暗証番号)
     */
    public String password;
    
    /**
     * @@roseuid 4158EB640095
     */
    public WEB3AdminAioCashoutInqCancelCompleteRequest() 
    {
        
    }
    
    /**
     * （createResponseの実装）<BR>
     * <BR>
     * 出金申込問合せ取消完了レスポンスオブジェクトを生成して返す。<BR>
     * @@return WEB3GenResponse
     * @@roseuid 4158EB620327
     */
    public WEB3GenResponse createResponse() 
    {
        return new WEB3AdminAioCashoutInqCancelCompleteResponse(this);
    }
}
@
