head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.10.02.00.29;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	8644d782f8a12fc;
filename	WEB3AdminAccInfoLoginPasswordResetResponse.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : (管理者お客様情報パスワードリセットレスポンス(WEB3AdminAccInfoLoginPasswordResetResponse)
Author Name      : Daiwa Institute of Research
Revesion History : 2004/11/15 張宝楠 (中訊) 新規作成
*/

package webbroker3.accountinfo.message;

import webbroker3.common.message.WEB3GenRequest;
import webbroker3.common.message.WEB3GenResponse;


/**
 * (管理者お客様情報パスワードリセットレスポンス)<BR>
 * 管理者お客様情報パスワードリセットレスポンス<BR>
 * 
 * @@author 張宝楠(中訊)
 * @@version 1.0
 */
public class WEB3AdminAccInfoLoginPasswordResetResponse extends WEB3GenResponse
{

    /**
     * PTYPE<BR>
     */
    public static final String PTYPE = "admin_accInfo_loginPasswordReset";

    /**
     * SerialVersionUID<BR>
     */
    public static final long serialVersionUID = 200411082122L;

    /**
     * @@roseuid 418F385B0109
     */
    public WEB3AdminAccInfoLoginPasswordResetResponse()
    {

    }

    /**
     * コンストラクタ。<BR>
     * 指定されたリクエストオブジェクトに対するレスポンスオブジェクトを生成する。<BR>
     * @@param l_request - リクエストオブジェクト
     */
    public WEB3AdminAccInfoLoginPasswordResetResponse(WEB3GenRequest l_request)
    {
        super(l_request);
    }
}
@
