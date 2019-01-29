head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.15.05.15.00;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	7844d7ef4f02f89;
filename	WEB3AdminMailInfoDeleteCompleteResponse.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : メール情報削除完了レスポンス(WEB3AdminMailInfoDeleteCompleteResponse.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2004/10/15  魏馨(中訊) 新規作成
*/
package webbroker3.mailinfo.message;

import webbroker3.common.message.WEB3GenResponse;

/**
 * (メール情報削除完了レスポンス)<BR>
 * メール情報削除完了レスポンスクラス<BR>
 * 
 * @@author 魏馨
 * @@version 1.0
 */
public class WEB3AdminMailInfoDeleteCompleteResponse extends WEB3GenResponse
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
     * @@param request
     */

    /**
     * (メール情報削除完了レスポンス)<BR>
     * デフォルトコンストラクタ<BR>
     * @@roseuid 413C102802FD
     */
    public WEB3AdminMailInfoDeleteCompleteResponse()
    {

    }
    /**
     * (メール情報削除完了レスポンス)<BR>
     * デフォルトコンストラクタ 引数で与えられたリクエストオブジェクトを基に<BR>
     * レスポンスオブジェクトを生成する。<BR>
     * @@param l_request リクエストオブジェクト
     */
    public WEB3AdminMailInfoDeleteCompleteResponse(WEB3AdminMailInfoDeleteCompleteRequest l_reqeust)
    {
        super(l_reqeust);
    }
}
@
