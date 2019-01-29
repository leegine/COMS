head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.15.05.13.57;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	7844d7ef4f02f89;
filename	WEB3AdminMailInfoChangeInputResponse.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : メール情報変更入力画面レスポンス(WEB3AdminMailInfoChangeInputResponse.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2004/10/15  魏馨(中訊) 新規作成
*/

package webbroker3.mailinfo.message;

import webbroker3.common.message.WEB3GenRequest;

/**
 * (メール情報変更入力画面レスポンス)<BR>
 * メール情報変更入力画面レスポンスクラス<BR>
 * 
 * @@author 魏馨
 * @@version 1.0
 */
public class WEB3AdminMailInfoChangeInputResponse extends WEB3AdminMailInfoCommonResponse
{
    /**
     * PTYPE<BR>
     */
    public static final String PTYPE = "admin_MailInfo_changeInput";

    /**
     * SerialVersionUID<BR>
     */
    public static final long serialVersionUID = 200410151310L;

    /**
     * (メール情報変更入力画面レスポンス)<BR>
     * デフォルトコンストラクタ<BR>
     * @@roseuid 414165B2015F
     */
    public WEB3AdminMailInfoChangeInputResponse()
    {

    }

    /**
     * (メール情報変更入力画面レスポンス)<BR>
     * デフォルトコンストラクタ 引数で与えられたリクエストオブジェクトを基に<BR>
     * レスポンスオブジェクトを生成する。<BR>
     * @@param l_request リクエストオブジェクト
     */
    public WEB3AdminMailInfoChangeInputResponse(WEB3GenRequest l_request)
    {
        super(l_request);
    }
}
@
