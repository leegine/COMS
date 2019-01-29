head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.10.02.08.53;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	8644d782f8a12fc;
filename	WEB3AdminAccInfoPasswordResetInputResponse.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : 管理者お客様情報暗証番号リセット入力レスポンス(WEB3AdminAccInfoPasswordResetInputResponse)
Author Name      : Daiwa Institute of Research
Revesion History : 2004/11/16 張宝楠 (中訊) 新規作成
*/

package webbroker3.accountinfo.message;

import webbroker3.common.message.WEB3GenRequest;
import webbroker3.common.message.WEB3GenResponse;


/**
 * (管理者お客様情報暗証番号リセット入力レスポンス)<BR>
 * 管理者お客様情報暗証番号リセット入力レスポンス<BR>
 * 
 * @@author 張宝楠(中訊)
 * @@version 1.0
 */
public class WEB3AdminAccInfoPasswordResetInputResponse extends WEB3GenResponse
{

    /**
     * PTYPE<BR>
     */
    public static final String PTYPE = "admin_accInfo_passwordResetInput";

    /**
     * SerialVersionUID<BR>
     */
    public static final long serialVersionUID = 200411082105L;

    /**
     * @@roseuid 418F385F0280
     */
    public WEB3AdminAccInfoPasswordResetInputResponse()
    {

    }

    /**
     * (管理者お客様情報暗証番号リセット入力レスポンス)<BR>
     * コンストラクタ。<BR>
     * 指定されたリクエストオブジェクトに対するレスポンスオブジェクトを生成する。<BR>
     * @@param l_request - (l_request)<BR>
     * リクエストオブジェクト
     * @@return webbroker3.accountinfo.message.WEB3AdminAccInfoPasswordResetInputResponse
     * @@roseuid 416656F901B1
     */
    public WEB3AdminAccInfoPasswordResetInputResponse(WEB3GenRequest l_request)
    {
        super(l_request);
    }
}
@
