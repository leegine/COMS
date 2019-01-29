head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.16.04.54.12;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	8f04d80403d696d;
filename	WEB3AioCashinNoticeCompleteRequest.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : 入金連絡完了リクエストクラス(WEB3AioCashinNoticeCompleteRequest)
Author Name      : Daiwa Institute of Research
Revesion History : 2004/10/11 屈陽 (中訊) 新規作成
                   2004/10/22 黄建 (中訊) レビュー    
*/


package webbroker3.aio.message;

import webbroker3.common.message.WEB3GenResponse;

/**
 * (入金連絡完了リクエスト)<BR>
 * 入金連絡完了リクエストクラス
 * 
 * @@author 屈陽(中訊)
 * @@version 1.0
 */
public class WEB3AioCashinNoticeCompleteRequest extends WEB3AioCashinNoticeCommonRequest 
{
    /**
     * PTYPE<BR>
     */
    public static final String PTYPE = "aio_cashin_notice_complete";

    /**
     * SerialVersionUID<BR>
     */
    public static final long serialVersionUID = 200410111111L;
    
    /**
     * (暗証番号)<BR>
     * 画面にて入力された暗証番号
     */
    public String password;
    
    /**
     * @@roseuid 4158E9B80036
     */
    public WEB3AioCashinNoticeCompleteRequest() 
    {
     
    }
    
    /**
     * （createResponseの実装）<BR>
     * <BR>
     * 入金連絡完了レスポンスオブジェクトを生成して返す。<BR>
     * @@return WEB3GenResponse
     */
    public WEB3GenResponse createResponse() 
    {
        return new WEB3AioCashinNoticeCompleteResponse(this);
    }

}
@
