head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.14.03.00.50;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	4584d7d7e626866;
filename	WEB3AdminAccOpenApplyDataDelCmpResponse.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright           : (株)大和総研 証券ソリューションシステム第二部
File Name           : 管理者口座開設資料請求データ削除完了レスポンス(WEB3AdminAccOpenApplyDataDelCmpResponse.java)
Author Name         : Daiwa Institute of Research
Revision History    : 2008/12/12 劉仁和(中訊) 新規作成 モデルNo.160
*/

package webbroker3.accountopen.message;

import webbroker3.common.message.WEB3GenRequest;
import webbroker3.common.message.WEB3GenResponse;


/**
 * (管理者口座開設資料請求データ削除完了レスポンス)<BR>
 * 管理者口座開設資料請求データ削除完了レスポンス<BR>
 * <BR>
 * @@author 劉仁和
 * @@version 1.0
 */
public class WEB3AdminAccOpenApplyDataDelCmpResponse extends WEB3GenResponse
{

    /**
     * PTYPE<BR>
     */
    public static final String PTYPE = "admin_acc_open_apply_data_del_cmp";

    /**
     * serialVersionUID<BR>
     */
    public static final long serialVersionUID = 200812120952L;

    /**
     * @@roseuid 4940F22B0196
     */
    public WEB3AdminAccOpenApplyDataDelCmpResponse()
    {

    }

    /**
     * コンストラクタ。<BR>
     * 指定されたリクエストオブジェクトに対するレスポンスオブジェクトを生成する。<BR>
     * @@param l_request - (リクエストデータ)<BR>
     * リクエストデータ<BR>
     */
    public WEB3AdminAccOpenApplyDataDelCmpResponse(WEB3GenRequest l_request)
    {
        super(l_request);
    }
}
@
