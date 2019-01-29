head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.14.03.05.36;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	4584d7d7e626866;
filename	WEB3AccOpenMailAddrRegInputResponse.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : 口座開設メールアドレス登録入力レスポンス(WEB3AccOpenMailAddrRegInputResponse.java)
Author Name      : Daiwa Institute of Research
Revision History : 2009/08/10 張騰宇(中訊) 新規作成 モデル 162
*/

package webbroker3.accountopen.message;

import webbroker3.common.message.WEB3GenRequest;
import webbroker3.common.message.WEB3GenResponse;

/**
 * (口座開設メールアドレス登録入力レスポンス)<BR>
 * 口座開設メールアドレス登録入力レスポンス<BR>
 * @@author 張騰宇
 * @@version 1.0
 */
public class WEB3AccOpenMailAddrRegInputResponse extends WEB3GenResponse
{
    /**
     * PTYPE<BR>
     */
    public static final String PTYPE = "acc_open_mail_addr_reg_input";

    /**
     * SerialVersionUID<BR>
     */
    public static final long serialVersionUID = 200908101113L;

    /**
     *
     */
    public WEB3AccOpenMailAddrRegInputResponse()
    {

    }

    /**
     * コンストラクタ。<BR>
     * 指定されたリクエストオブジェクトに対するレスポンスオブジェクトを生成する。<BR>
     * @@param l_request - リクエストオブジェクト
     */
    public WEB3AccOpenMailAddrRegInputResponse(WEB3GenRequest l_request)
    {
        super(l_request);
    }

}
@
