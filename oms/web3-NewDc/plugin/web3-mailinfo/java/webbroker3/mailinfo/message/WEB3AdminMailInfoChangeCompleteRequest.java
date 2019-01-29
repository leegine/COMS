head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.15.05.14.31;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	7844d7ef4f02f89;
filename	WEB3AdminMailInfoChangeCompleteRequest.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : メール情報変更完了リクエスト(WEB3AdminMailInfoChangeCompleteRequest.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2004/10/15  魏馨(中訊) 新規作成
*/
package webbroker3.mailinfo.message;

import webbroker3.common.message.WEB3GenResponse;

/**
 * (メール情報変更完了リクエスト)<BR>
 * メール情報変更完了リクエストクラス<BR>
 * 
 * @@author 魏馨
 * @@version 1.0
 */
public class WEB3AdminMailInfoChangeCompleteRequest extends WEB3AdminMailInfoCommonRequest
{
    /**
     * PTYPE<BR>
     */
    public static final String PTYPE = "admin_MailInfo_changeComplete";

    /**
     * SerialVersionUID<BR>
     */
    public static final long serialVersionUID = 200410151310L;

    /**
     * (暗証番号)<BR>
     */
    public String password;

    /**
     * @@roseuid 416F1DCE001F
     */
    public WEB3AdminMailInfoChangeCompleteRequest()
    {

    }

    /**
     * (createレスポンス)<BR>
     * メール情報変更完了レスポンスオブジェクトを返却する。<BR>
     * @@return webbroker3.common.message.WEB3GenResponse
     * @@roseuid 413C1326035B
     */
    public WEB3GenResponse createResponse()
    {
        return new WEB3AdminMailInfoChangeCompleteResponse();
    }
}
@
