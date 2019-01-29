head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.10.01.58.46;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	8644d782f8a12fc;
filename	WEB3AdminAccInfoMailAddressChangeCompleteResponse.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : 管理者お客様情報メールアドレス変更完了レスポンス(WEB3AdminAccInfoMailAddressChangeCompleteResponse.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2004/11/09 劉江涛 (中訊) 新規作成
                   2006/05/19 李小健 仕様変更・モデル104
Revesion History : 2010/02/21 武波 (中訊) 仕様変更・モデルNo.263
*/
package webbroker3.accountinfo.message;

import webbroker3.common.message.WEB3GenRequest;
import webbroker3.common.message.WEB3GenResponse;


/**
 * (管理者お客様情報メールアドレス変更完了レスポンス)<BR>
 * 管理者お客様情報メールアドレス変更完了レスポンス<BR>
 * @@author 劉江涛
 * @@version 1.0
 */
public class WEB3AdminAccInfoMailAddressChangeCompleteResponse extends WEB3GenResponse
{

    /**
     * PTYPE<BR>
     */
    public static final String PTYPE = "admin_accInfo_mailAddressChangeComplete";

    /**
     * SerialVersionUID<BR>
     */
    public static final long serialVersionUID = 200411082118L;

    /**
     * (メールアドレス変更情報)<BR>
     * メールアドレス変更情報<BR>
     */
    public WEB3AccInfoMailAddressUpdateInfo[] mailAddressUpdateInfo;

    /**
     * @@roseuid 418F385802CE
     */
    public WEB3AdminAccInfoMailAddressChangeCompleteResponse()
    {

    }

    /**
     * (重複アドレス情報)<BR>
     * 重複アドレス情報<BR>
     */
    public String[] duplicationAddressInfo;

    /**
     * コンストラクタ。<BR>
     * 指定されたリクエストオブジェクトに対するレスポンスオブジェクトを生成する。<BR>
     * @@param l_request - リクエストオブジェクト
     */
    public WEB3AdminAccInfoMailAddressChangeCompleteResponse(WEB3GenRequest l_request)
    {
        super(l_request);
    }
}
@
