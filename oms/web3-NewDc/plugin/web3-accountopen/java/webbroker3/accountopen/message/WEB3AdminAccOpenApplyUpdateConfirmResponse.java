head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.14.03.01.21;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	4584d7d7e626866;
filename	WEB3AdminAccOpenApplyUpdateConfirmResponse.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : 管理者口座開設申込更新確認レスポンス(WEB3AdminAccOpenApplyUpdateConfirmResponse.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2004/12/15 郭英 (中訊) 新規作成
*/

package webbroker3.accountopen.message;

import webbroker3.common.message.WEB3GenRequest;
import webbroker3.common.message.WEB3GenResponse;


/**
 * (管理者口座開設申込更新確認レスポンス)<BR>
 * 管理者口座開設申込更新確認レスポンス<BR>
 *   
 * @@author 郭英
 * @@version 1.0
 */

public class WEB3AdminAccOpenApplyUpdateConfirmResponse extends WEB3GenResponse
{
    /**
     * PTYPE<BR>
     */
    public static final String PTYPE = "admin_accOpen_applyUpdateConfirm";

    /**
     * SerialVersionUID<BR>
     */
    public static final long serialVersionUID = 200412081610L;

    /**
     * (警告メッセージコード一覧)<BR>
     * 警告メッセージコード一覧<BR>
     */
    public String[] warningMessageList;

    /**
     * @@roseuid 41B45E7A02CE
     */
    public WEB3AdminAccOpenApplyUpdateConfirmResponse()
    {

    }

    /**
     * コンストラクタ。<BR>
     * 指定されたリクエストオブジェクトに対するレスポンスオブジェクトを生成する。<BR>
     * @@param l_request - リクエストオブジェクト
     */
    public WEB3AdminAccOpenApplyUpdateConfirmResponse(WEB3GenRequest l_request)
    {
        super(l_request);
    }
}
@
