head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.15.04.52.33;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	88c4d7eeee91bda;
filename	WEB3AdminInformPTSAccountListInquiryRequest.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright           : (株)大和総研 証券ソリューションシステム第二部
File Name           : 管理者・PTS申込客一覧問合せ検索リクエストクラス(WEB3AdminInformPTSAccountListInquiryRequest.java)
Author Name         : Daiwa Institute of Research
Revision History    : 2008/02/28 謝旋(中訊) 新規作成 モデルNo.128
*/

package webbroker3.inform.message;

import webbroker3.common.message.WEB3GenRequest;
import webbroker3.common.message.WEB3GenResponse;


/**
 * (管理者・PTS申込客一覧問合せ検索リクエストクラス)<BR>
 * 管理者・PTS申込客一覧問合せ検索リクエストクラス<BR>
 * <BR>
 * @@author 謝旋
 * @@version 1.0
 */
public class WEB3AdminInformPTSAccountListInquiryRequest extends WEB3GenRequest
{
    /**
     * PTYPE<BR>
     */
    public static final String PTYPE = "admin_inform_pts_account_list_inquiry";

    /**
     * SerialVersionUID<BR>
     */
    public static final long serialVersionUID = 200802281634L;

    /**
     * @@roseuid 47C522D402BB
     */
    public WEB3AdminInformPTSAccountListInquiryRequest()
    {

    }

    /**
     * 当該リクエストに対応するレスポンスオブジェクトを返す。<BR>
     * <BR>
     * @@return レスポンスオブジェクト
     */
    public WEB3GenResponse createResponse()
    {
        return new WEB3AdminInformPTSAccountListInquiryResponse(this);
    }
}
@
