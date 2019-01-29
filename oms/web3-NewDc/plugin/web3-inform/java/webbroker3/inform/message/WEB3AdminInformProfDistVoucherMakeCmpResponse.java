head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.15.04.51.34;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	88c4d7eeee91bda;
filename	WEB3AdminInformProfDistVoucherMakeCmpResponse.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright           : (株)大和総研 証券ソリューションシステム第二部
File Name           : 管理者・口座伝票作成完了レスポンス(WEB3AdminInformProfDistVoucherMakeCmpResponse.java)
Author Name         : Daiwa Institute of Research
Revision History    : 2007/06/04 謝旋(中訊) 新規作成 モデルNo.054
*/

package webbroker3.inform.message;

import webbroker3.common.message.WEB3GenRequest;
import webbroker3.common.message.WEB3GenResponse;


/**
 * (管理者・口座伝票作成完了レスポンス)<BR>
 * 管理者・口座伝票作成完了レスポンスクラス
 */
public class WEB3AdminInformProfDistVoucherMakeCmpResponse extends WEB3GenResponse
{
    /**
     * PTYPE<BR>
     */
    public static final String PTYPE = "admin_inform_prof_dist_voucher_make_cmp";

    /**
     * SerialVersionUID<BR>
     */
    public static final long serialVersionUID = 200706042212L;

    /**
     * @@roseuid 4663A9D6038A
     */
    public WEB3AdminInformProfDistVoucherMakeCmpResponse()
    {

    }

    /**
     * コンストラクタ。引数で与えられたリクエストオブジェクトを基に<BR>
     * レスポンスオブジェクトを生成する。<BR>
     *<BR>
     * @@param l_request リクエストオブジェクト
     */
    protected WEB3AdminInformProfDistVoucherMakeCmpResponse(WEB3GenRequest l_request)
    {
        super(l_request);
    }
}
@
