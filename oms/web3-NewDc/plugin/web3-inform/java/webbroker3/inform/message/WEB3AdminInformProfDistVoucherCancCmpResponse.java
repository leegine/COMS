head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.15.04.51.47;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	88c4d7eeee91bda;
filename	WEB3AdminInformProfDistVoucherCancCmpResponse.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright           : (株)大和総研 証券ソリューションシステム第二部
File Name           : 管理者・口座伝票取消完了レスポンス(WEB3AdminInformProfDistVoucherCancCmpResponse.java)
Author Name         : Daiwa Institute of Research
Revision History    : 2007/06/04 謝旋(中訊) 新規作成 モデルNo.054
*/

package webbroker3.inform.message;

import webbroker3.common.message.WEB3GenRequest;
import webbroker3.common.message.WEB3GenResponse;


/**
 * (管理者・口座伝票取消完了レスポンス)<BR>
 * 管理者・口座伝票取消完了レスポンスクラス
 */
public class WEB3AdminInformProfDistVoucherCancCmpResponse extends WEB3GenResponse
{
    /**
     * PTYPE<BR>
     */
    public static final String PTYPE = "admin_inform_prof_dist_voucher_canc_cmp";

    /**
     * SerialVersionUID<BR>
     */
    public static final long serialVersionUID = 200706042212L;

    /**
     * @@roseuid 4663A9D8003E
     */
    public WEB3AdminInformProfDistVoucherCancCmpResponse()
    {

    }

    /**
     * コンストラクタ。引数で与えられたリクエストオブジェクトを基に<BR>
     * レスポンスオブジェクトを生成する。<BR>
     *<BR>
     * @@param l_request リクエストオブジェクト
     */
    protected WEB3AdminInformProfDistVoucherCancCmpResponse(WEB3GenRequest l_request)
    {
        super(l_request);
    }
}
@
