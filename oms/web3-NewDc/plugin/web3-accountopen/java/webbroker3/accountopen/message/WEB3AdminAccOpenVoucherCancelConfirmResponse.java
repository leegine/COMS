head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.14.03.09.06;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	4584d7d7e626866;
filename	WEB3AdminAccOpenVoucherCancelConfirmResponse.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : 管理者口座開設伝票取消確認レスポンス(WEB3AdminAccOpenVoucherCancelConfirmResponse.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2004/12/15 郭英 (中訊) 新規作成
*/

package webbroker3.accountopen.message;

import webbroker3.common.message.WEB3GenRequest;
import webbroker3.common.message.WEB3GenResponse;


/**
 * (管理者口座開設伝票取消確認レスポンス)<BR>
 * 管理者口座開設伝票取消確認レスポンス<BR>
 *   
 * @@author 郭英
 * @@version 1.0
 */

public class WEB3AdminAccOpenVoucherCancelConfirmResponse extends WEB3GenResponse
{
    /**
     * PTYPE<BR>
     */
    public static final String PTYPE = "admin_accOpen_voucherCancelConfirm";

    /**
     * SerialVersionUID<BR>
     */
    public static final long serialVersionUID = 200412081603L;

    /**
     * (部店コード)<BR>
     * 部店コード<BR>
     */
    public String branchCode;

    /**
     * (顧客コード)<BR>
     * 顧客コード<BR>
     */
    public String accountCode;

    /**
     * (顧客姓（カナ）)<BR>
     * 顧客姓（カナ）<BR>
     */
    public String accountFamilyNameKana;

    /**
     * (顧客名（カナ）)<BR>
     * 顧客名（カナ）<BR>
     */
    public String accountNameKana;

    /**
     * @@roseuid 41B45E780280
     */
    public WEB3AdminAccOpenVoucherCancelConfirmResponse()
    {

    }

    /**
     * コンストラクタ。<BR>
     * 指定されたリクエストオブジェクトに対するレスポンスオブジェクトを生成する。<BR>
     * @@param l_request - リクエストオブジェクト
     */
    public WEB3AdminAccOpenVoucherCancelConfirmResponse(WEB3GenRequest l_request)
    {
        super(l_request);
    }
}
@
