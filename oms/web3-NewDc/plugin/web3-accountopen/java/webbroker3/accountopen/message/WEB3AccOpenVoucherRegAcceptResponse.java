head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.14.03.00.37;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	4584d7d7e626866;
filename	WEB3AccOpenVoucherRegAcceptResponse.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : 口座開設伝票登録受付レスポンス(WEB3AccOpenVoucherRegAcceptResponse.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2004/12/17 鄭海良(中訊) 新規作成
*/

package webbroker3.accountopen.message;

import webbroker3.common.message.WEB3BackRequest;
import webbroker3.common.message.WEB3BackResponse;


/**
 * (口座開設伝票登録受付レスポンス)<BR>
 * 口座開設伝票登録受付レスポンス<BR>
 *   
 * @@author 鄭海良
 * @@version 1.0
 */
public class WEB3AccOpenVoucherRegAcceptResponse extends WEB3BackResponse
{
    /**
     * PTYPE<BR>
     */
    public static final String PTYPE = "accOpen_voucherRegAccept";

    /**
     * SerialVersionUID<BR>
     */
    public static final long serialVersionUID = 200412081614L;

    /**
     * @@roseuid 41B45E7D0271
     */
    public WEB3AccOpenVoucherRegAcceptResponse()
    {

    }

    /**
     * コンストラクタ。<BR>
     * 指定されたリクエストオブジェクトに対するレスポンスオブジェクトを生成する。<BR>
     * @@param l_request - リクエストオブジェクト
     */
    public WEB3AccOpenVoucherRegAcceptResponse(WEB3BackRequest l_request)
    {
        super(l_request);
    }
}
@
