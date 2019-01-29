head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.14.02.58.59;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	4584d7d7e626866;
filename	WEB3AdminAccOpenApplyDataDelSearchRequest.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright           : (株)大和総研 証券ソリューションシステム第二部
File Name           : 管理者口座開設資料請求データ削除検索リクエスト(WEB3AdminAccOpenApplyDataDelSearchRequest.java)
Author Name         : Daiwa Institute of Research
Revision History    : 2008/12/12 劉仁和(中訊) 新規作成 モデルNo.160
*/

package webbroker3.accountopen.message;

import webbroker3.common.message.WEB3GenRequest;
import webbroker3.common.message.WEB3GenResponse;
import webbroker3.util.WEB3LogUtility;


/**
 * (管理者口座開設資料請求データ削除検索リクエスト)<BR>
 * 管理者口座開設資料請求データ削除検索リクエスト<BR>
 * <BR>
 * @@author 劉仁和
 * @@version 1.0
 */
public class WEB3AdminAccOpenApplyDataDelSearchRequest extends WEB3GenRequest
{

    /**
     * ログ出力ユーティリティ<BR>
     */
    private static WEB3LogUtility log =
        WEB3LogUtility.getInstance(WEB3AdminAccOpenApplyDataDelSearchRequest.class);

    /**
     * PTYPE<BR>
     */
    public static final String PTYPE = "admin_acc_open_apply_data_del_search";

    /**
     * serialVersionUID<BR>
     */
    public static final long serialVersionUID = 200812121135L;

    /**
     * @@roseuid 4940F22B0232
     */
    public WEB3AdminAccOpenApplyDataDelSearchRequest()
    {

    }

    /**
     * 当該リクエストに対応するレスポンスオブジェクトを返す。<BR>
     * <BR>
     * @@return WEB3GenResponse
     */
    public WEB3GenResponse createResponse()
    {
        return new WEB3AdminAccOpenApplyDataDelSearchResponse(this);
    }
}
@
