head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.14.03.07.17;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	4584d7d7e626866;
filename	WEB3AccOpenMailAddrRegCompleteResponse.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : 口座開設メールアドレス登録完了レスポンス(WEB3AccOpenMailAddrRegCompleteResponse.java)
Author Name      : Daiwa Institute of Research
Revision History : 2009/08/10 張騰宇(中訊) 新規作成 モデル 162
*/

package webbroker3.accountopen.message;

import java.util.Date;

import webbroker3.common.message.WEB3GenRequest;
import webbroker3.common.message.WEB3GenResponse;

/**
 * (口座開設メールアドレス登録完了レスポンス)<BR>
 * 口座開設メールアドレス登録完了レスポンス<BR>
 * <BR>
 * @@author 張騰宇
 * @@version 1.0
 */
public class WEB3AccOpenMailAddrRegCompleteResponse extends WEB3GenResponse
{
    /**
     * PTYPE<BR>
     */
    public static final String PTYPE = "acc_open_mail_addr_reg_complete";

    /**
     * SerialVersionUID<BR>
     */
    public static final long serialVersionUID = 200908101117L;

    /**
     * (メールアドレス登録ＩＤ)<BR>
     * メールアドレス登録ＩＤ<BR>
     */
    public String mailRegiID;

    /**
     * (現在日時)<BR>
     * 現在日時<BR>
     */
    public Date currentDate;

    /**
     *
     */
    public WEB3AccOpenMailAddrRegCompleteResponse()
    {

    }

    /**
     * コンストラクタ。<BR>
     * 指定されたリクエストオブジェクトに対するレスポンスオブジェクトを生成する。<BR>
     * @@param l_request - リクエストオブジェクト
     */
    public WEB3AccOpenMailAddrRegCompleteResponse(WEB3GenRequest l_request)
    {
        super(l_request);
    }
}
@
