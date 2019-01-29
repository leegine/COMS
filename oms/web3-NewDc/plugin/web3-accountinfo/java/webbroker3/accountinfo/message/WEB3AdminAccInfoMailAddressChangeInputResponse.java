head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.10.02.00.21;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	8644d782f8a12fc;
filename	WEB3AdminAccInfoMailAddressChangeInputResponse.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : 管理者お客様情報メールアドレス変更入力レスポンス(WEB3AdminAccInfoMailAddressChangeInputResponse.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2004/11/09 劉江涛 (中訊) 新規作成
Revesion History : 2010/02/21 武波 (中訊) 仕様変更・モデルNo.263
*/
package webbroker3.accountinfo.message;

import webbroker3.common.message.WEB3GenRequest;
import webbroker3.common.message.WEB3GenResponse;


/**
 * (管理者お客様情報メールアドレス変更入力レスポンス)<BR>
 * 管理者お客様情報メールアドレス変更入力レスポンス<BR>
 * @@author 劉江涛
 * @@version 1.0
 */
public class WEB3AdminAccInfoMailAddressChangeInputResponse extends WEB3GenResponse
{

    /**
     * PTYPE<BR>
     */
    public static final String PTYPE = "admin_accInfo_mailAddressChangeInput";

    /**
     * SerialVersionUID<BR>
     */
    public static final long serialVersionUID = 200411082116L;

    /**
     * (メールアドレス変更情報)<BR>
     * メールアドレス変更情報<BR>
     */
    public WEB3AccInfoMailAddressUpdateInfo[] mailAddressUpdateInfo;

    /**
     * @@roseuid 418F385E030D
     */
    public WEB3AdminAccInfoMailAddressChangeInputResponse()
    {

    }

    /**
     * コンストラクタ。<BR>
     * 指定されたリクエストオブジェクトに対するレスポンスオブジェクトを生成する。<BR>
     * @@param l_request - リクエストオブジェクト
     */
    public WEB3AdminAccInfoMailAddressChangeInputResponse(WEB3GenRequest l_request)
    {
        super(l_request);
    }
}
@
