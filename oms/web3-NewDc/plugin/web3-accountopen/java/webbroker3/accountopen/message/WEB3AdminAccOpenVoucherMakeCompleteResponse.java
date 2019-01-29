head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.14.02.58.50;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	4584d7d7e626866;
filename	WEB3AdminAccOpenVoucherMakeCompleteResponse.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : 管理者口座開設伝票作成完了レスポンス(WEB3AdminAccOpenVoucherMakeCompleteResponse.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2004/12/15 郭英 (中訊) 新規作成
*/

package webbroker3.accountopen.message;

import webbroker3.common.message.WEB3GenRequest;
import webbroker3.common.message.WEB3GenResponse;


/**
 * (管理者口座開設伝票作成完了レスポンス)<BR>
 * 管理者口座開設伝票作成完了レスポンス<BR>
 *   
 * @@author 郭英
 * @@version 1.0
 */

public class WEB3AdminAccOpenVoucherMakeCompleteResponse extends WEB3GenResponse
{
    /**
     * PTYPE<BR>
     */
    public static final String PTYPE = "admin_accOpen_voucherMakeComplete";

    /**
     * SerialVersionUID<BR>
     */
    public static final long serialVersionUID = 200412081602L;

    /**
     * (作成完了伝票コード一覧)<BR>
     * 作成完了伝票コード一覧<BR>
     */
    public String[] creationCompleteList;

    /**
     * @@roseuid 41B45E7802FD
     */
    public WEB3AdminAccOpenVoucherMakeCompleteResponse()
    {

    }

    /**
     * コンストラクタ。<BR>
     * 指定されたリクエストオブジェクトに対するレスポンスオブジェクトを生成する。<BR>
     * @@param l_request - リクエストオブジェクト
     */
    public WEB3AdminAccOpenVoucherMakeCompleteResponse(WEB3GenRequest l_request)
    {
        super(l_request);
    }
}
@
