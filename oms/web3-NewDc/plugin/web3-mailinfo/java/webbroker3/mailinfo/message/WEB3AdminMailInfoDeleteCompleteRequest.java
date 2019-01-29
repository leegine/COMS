head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.15.05.13.36;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	7844d7ef4f02f89;
filename	WEB3AdminMailInfoDeleteCompleteRequest.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : メール情報削除完了リクエスト(WEB3AdminMailInfoDeleteCompleteRequest.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2004/10/15  魏馨(中訊) 新規作成
*/
package webbroker3.mailinfo.message;

import webbroker3.common.message.WEB3GenResponse;

/**
 * (メール情報削除完了リクエスト)<BR>
 * メール情報削除完了リクエストクラス
 * 
 * @@author 魏馨
 * @@version 1.0
 */
public class WEB3AdminMailInfoDeleteCompleteRequest extends WEB3AdminMailInfoDeleteCommonRequest
{
    /**
     * PTYPE<BR>
     */
    public static final String PTYPE = "admin_MailInfo_deleteComplete";

    /**
     * SerialVersionUID<BR>
     */
    public static final long serialVersionUID = 200410151310L;

    /**
     * (暗証番号)<BR>
     */
    public String password;

    /**
     * @@roseuid 416F1DCF0196
     */
    public WEB3AdminMailInfoDeleteCompleteRequest()
    {

    }

    /**
     * (createレスポンス)<BR>
     * メール情報削除完了レスポンスオブジェクトを返却する。<BR>
     * @@return webbroker3.mailinfo.commin.WEB3GenResponse<BR>
     * @@roseuid 413C0FC601D4
     */
    public WEB3GenResponse createResponse()
    {
        return new WEB3AdminMailInfoDeleteCompleteResponse(this);
    }
}
@
