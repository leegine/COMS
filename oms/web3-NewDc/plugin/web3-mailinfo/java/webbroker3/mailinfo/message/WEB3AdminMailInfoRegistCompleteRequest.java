head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.15.05.14.27;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	7844d7ef4f02f89;
filename	WEB3AdminMailInfoRegistCompleteRequest.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : メール情報登録完了リクエスト(WEB3AdminMailInfoRegistCompleteRequest.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2004/10/15  魏馨(中訊) 新規作成
*/
package webbroker3.mailinfo.message;

import webbroker3.common.message.WEB3GenResponse;

/**
 * (メール情報登録完了リクエスト)<BR>
 * メール情報登録完了リクエストクラス<BR>
 * 
 * @@author 魏馨
 * @@version 1.0
 */
public class WEB3AdminMailInfoRegistCompleteRequest extends WEB3AdminMailInfoCommonRequest
{  
    /**
     * PTYPE<BR>
     */
    public static final String PTYPE = "admin_MailInfo_registComplete";

    /**
     * SerialVersionUID<BR>
     */
    public static final long serialVersionUID = 200410151310L;

    /**
     * (暗証番号)<BR>
     */
    public String password;

    /**
     * @@roseuid 416F1DD00157
     */
    public WEB3AdminMailInfoRegistCompleteRequest()
    {

    }

    /**
     * (createレスポンス)<BR>
     * メール情報登録完了レスポンスオブジェクトを返却する。<BR>
     * @@return webbroker3.common.message.WEB3GenResponse<BR>
     * @@roseuid 413C126E01F4
     */
    public WEB3GenResponse createResponse()
    {
        return new WEB3AdminMailInfoRegistCompleteResponse(this);
    }
}
@
