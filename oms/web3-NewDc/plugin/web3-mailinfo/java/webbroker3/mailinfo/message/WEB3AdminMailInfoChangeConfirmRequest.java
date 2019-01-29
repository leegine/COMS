head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.15.05.14.43;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	7844d7ef4f02f89;
filename	WEB3AdminMailInfoChangeConfirmRequest.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : メール情報変更確認リクエスト(WEB3AdminMailInfoChangeConfirmRequest.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2004/10/15  魏馨(中訊) 新規作成
*/
package webbroker3.mailinfo.message;

import webbroker3.common.message.WEB3GenResponse;

/**
 * (メール情報変更確認リクエスト)<BR>
 * メール情報変更確認リクエストクラス<BR>
 * 
 * @@author 魏馨
 * @@version 1.0
 */
public class WEB3AdminMailInfoChangeConfirmRequest extends WEB3AdminMailInfoCommonRequest
{
    /**
     * PTYPE<BR>
     */
    public static final String PTYPE = "admin_MailInfo_changeConfirm";

    /**
     * SerialVersionUID<BR>
     */
    public static final long serialVersionUID = 200410151310L;

    /**
     * @@roseuid 416F1DCE0119
     */
    public WEB3AdminMailInfoChangeConfirmRequest()
    {

    }

    /**
     * (createレスポンス)<BR>
     * メール情報変更確認レスポンスオブジェクトを返却する。<BR>
     * @@return webbroker3.common.message.WEB3GenResponse
     * @@roseuid 413C131E0242
     */
    public WEB3GenResponse createResponse()
    {
        return new WEB3AdminMailInfoChangeConfirmResponse(this);
    }
}
@
