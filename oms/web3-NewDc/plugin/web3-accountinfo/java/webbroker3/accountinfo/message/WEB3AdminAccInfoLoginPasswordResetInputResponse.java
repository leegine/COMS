head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.10.02.02.36;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	8644d782f8a12fc;
filename	WEB3AdminAccInfoLoginPasswordResetInputResponse.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : 管理者お客様情報パスワードリセット入力レスポンス(WEB3AdminAccInfoLoginPasswordResetInputResponse)
Author Name      : Daiwa Institute of Research
Revesion History : 2004/11/15 張宝楠 (中訊) 新規作成
*/

package webbroker3.accountinfo.message;

import webbroker3.common.message.WEB3GenRequest;
import webbroker3.common.message.WEB3GenResponse;


/**
 * (管理者お客様情報パスワードリセット入力レスポンス)<BR>
 * 管理者お客様情報パスワードリセット入力レスポンス<BR>
 * 
 * @@author 張宝楠(中訊)
 * @@version 1.0
 */
public class WEB3AdminAccInfoLoginPasswordResetInputResponse extends WEB3GenResponse
{

    /**
     * PTYPE<BR>
     */
    public static final String PTYPE = "admin_accInfo_loginPasswordResetInput";

    /**
     * SerialVersionUID<BR>
     */
    public static final long serialVersionUID = 200411082123L;

    /**
     * @@roseuid 418F385E0177
     */
    public WEB3AdminAccInfoLoginPasswordResetInputResponse()
    {

    }

    /**
     * (管理者お客様情報パスワードリセット入力レスポンス)<BR>
     * コンストラクタ。<BR>
     * 指定されたリクエストオブジェクトに対するレスポンスオブジェクトを生成する。<BR>
     * @@param l_request - (l_request)<BR>
     * リクエストオブジェクト
     * @@return webbroker3.accountinfo.message.WEB3AdminAccInfoLoginPasswordResetInputResponse
     * @@roseuid 4166552302DA
     */
    public WEB3AdminAccInfoLoginPasswordResetInputResponse(WEB3GenRequest l_request)
    {
        super(l_request);
    }
}
@
