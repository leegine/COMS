head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.15.05.15.04;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	7844d7ef4f02f89;
filename	WEB3AdminMailInfoDeleteConfirmRequest.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : メール情報削除確認リクエスト(WEB3AdminMailInfoDeleteConfirmRequest.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2004/10/15  魏馨(中訊) 新規作成
*/
package webbroker3.mailinfo.message;

import webbroker3.common.message.WEB3GenResponse;

/**
 * (メール情報削除確認リクエスト)<BR>
 * メール情報削除確認リクエストクラス<BR>
 * 
 * @@author 魏馨
 * @@version 1.0
 */
public class WEB3AdminMailInfoDeleteConfirmRequest extends WEB3AdminMailInfoDeleteCommonRequest
{
    /**
     * PTYPE<BR>
     */
    public static final String PTYPE = "admin_MailInfo_deleteConfirm";

    /**
     * SerialVersionUID<BR>
     */
    public static final long serialVersionUID = 200410151310L;

    /**
     * @@roseuid 416F1DCF029F
     */
    public WEB3AdminMailInfoDeleteConfirmRequest()
    {

    }

    /**
     * (createレスポンス)<BR>
     * メール情報削除確認レスポンスオブジェクトを返却する。<BR>
     * @@return webbroker3.common.message.WEB3GenResponse
     * @@roseuid 413C0FA20157
     */
    public WEB3GenResponse createResponse()
    {
        return new WEB3AdminMailInfoDeleteConfirmResponse(this);
    }
}
@
