head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.10.02.02.03;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	8644d782f8a12fc;
filename	WEB3AccInfoMailAddressChangeConfirmResponse.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : お客様情報メールアドレス変更確認レスポンス(WEB3AccInfoMailAddressChangeConfirmResponse.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2004/11/09 劉江涛 (中訊) 新規作成
                   2006/05/19 李小健 仕様変更・モデル104
*/
package webbroker3.accountinfo.message;

import webbroker3.common.message.WEB3GenRequest;
import webbroker3.common.message.WEB3GenResponse;


/**
 * (お客様情報メールアドレス変更確認レスポンス)<BR>
 * お客様情報メールアドレス変更確認レスポンス<BR>
 * @@author 劉江涛
 * @@version 1.0
 */
public class WEB3AccInfoMailAddressChangeConfirmResponse extends WEB3GenResponse
{

    /**
     * PTYPE<BR>
     */
    public static final String PTYPE = "accInfo_mailAddressChangeConfirm";

    /**
     * SerialVersionUID<BR>
     */
    public static final long serialVersionUID = 200411082157L;

    /**
     * @@roseuid 418F39F4006D
     */
    public WEB3AccInfoMailAddressChangeConfirmResponse()
    {

    }

    /**
     * (重複アドレス情報)<BR>
     * 重複アドレス情報。<BR>
     */
    public String[] duplicationAddressInfo;

    /**
     * (お客様情報メールアドレス変更確認レスポンス)<BR>
     * コンストラクタ。<BR>
     * 指定されたリクエストオブジェクトに対するレスポンスオブジェクトを生成する。<BR>
     * @@param l_request - (リクエストオブジェクト)<BR>
     * @@return webbroker3.accountinfo.message.WEB3AccInfoMailAddressChangeConfirmResponse
     * @@roseuid 41368D9800D1
     */
    public WEB3AccInfoMailAddressChangeConfirmResponse(WEB3GenRequest l_request)
    {
        super(l_request);
    }
}
@
