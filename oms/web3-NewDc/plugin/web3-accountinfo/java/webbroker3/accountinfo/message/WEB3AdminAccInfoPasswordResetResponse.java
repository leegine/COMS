head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.10.02.03.01;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	8644d782f8a12fc;
filename	WEB3AdminAccInfoPasswordResetResponse.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : 管理者お客様情報暗証番号リセットレスポンス(WEB3AdminAccInfoPasswordResetResponse.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2004/11/16 張宝楠 (中訊) 新規作成
*/

package webbroker3.accountinfo.message;

import webbroker3.common.message.WEB3GenRequest;
import webbroker3.common.message.WEB3GenResponse;


/**
 * (管理者お客様情報暗証番号リセットレスポンス)<BR>
 * 管理者お客様情報暗証番号リセットレスポンス<BR>
 * 
 * @@author 張宝楠
 * @@version 1.0 
 */
public class WEB3AdminAccInfoPasswordResetResponse extends WEB3GenResponse
{

    /**
     * PTYPE<BR>
     */
    public static final String PTYPE = "admin_accInfo_infoPasswordReset";

    /**
     * SerialVersionUID<BR>
     */
    public static final long serialVersionUID = 200411082104L;

    /**
     * @@roseuid 418F385B029F
     */
    public WEB3AdminAccInfoPasswordResetResponse()
    {

    }

    /**
     * コンストラクタ。<BR>
     * 指定されたリクエストオブジェクトに対するレスポンスオブジェクトを生成する。<BR>
     * @@param l_request - リクエストオブジェクト
     */
    public WEB3AdminAccInfoPasswordResetResponse(WEB3GenRequest l_request)
    {
        super(l_request);
    }
}
@
