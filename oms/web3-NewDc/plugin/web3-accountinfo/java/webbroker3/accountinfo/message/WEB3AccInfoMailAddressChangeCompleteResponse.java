head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.10.02.04.18;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	8644d782f8a12fc;
filename	WEB3AccInfoMailAddressChangeCompleteResponse.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : お客様情報メールアドレス変更完了レスポンス(WEB3AccInfoMailAddressChangeCompleteResponse.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2004/11/09 劉江涛 (中訊) 新規作成
                   2006/05/19 李小健 仕様変更・モデル104
*/
package webbroker3.accountinfo.message;

import webbroker3.common.message.WEB3GenRequest;
import webbroker3.common.message.WEB3GenResponse;


/**
 * (お客様情報メールアドレス変更完了レスポンス)<BR>
 * お客様情報メールアドレス変更完了レスポンス<BR>
 * @@author 劉江涛
 * @@version 1.0
 */
public class WEB3AccInfoMailAddressChangeCompleteResponse extends WEB3GenResponse
{

    /**
     * PTYPE<BR>
     */
    public static final String PTYPE = "accInfo_mailAddressChangeComplete";

    /**
     * SerialVersionUID<BR>
     */
    public static final long serialVersionUID = 200411082158L;

    /**
     * @@roseuid 418F39F30232
     */
    public WEB3AccInfoMailAddressChangeCompleteResponse()
    {

    }

    /**
     * (重複アドレス情報)<BR>
     * 重複アドレス情報。<BR>
     */
    public String[] duplicationAddressInfo;

    /**
     * (お客様情報メールアドレス変更完了レスポンス)<BR>
     * コンストラクタ。<BR>
     * 指定されたリクエストオブジェクトに対するレスポンスオブジェクトを生成する。<BR>
     * @@param l_request - リクエストオブジェクト
     * @@return webbroker3.accountinfo.message.WEB3AccInfoMailAddressChangeCompleteResponse
     * @@roseuid 41368DAD0352
     */
    public WEB3AccInfoMailAddressChangeCompleteResponse(WEB3GenRequest l_request)
    {
        super(l_request);
    }
}
@
