head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.15.04.49.03;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	88c4d7eeee91bda;
filename	WEB3AdminInformProfDistVoucherCancCmpRequest.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright           : (株)大和総研 証券ソリューションシステム第二部
File Name           : 管理者・口座伝票取消完了リクエスト(WEB3AdminInformProfDistVoucherCancCmpRequest.java)
Author Name         : Daiwa Institute of Research
Revision History    : 2007/06/04 謝旋(中訊) 新規作成 モデルNo.054
*/

package webbroker3.inform.message;

import webbroker3.common.WEB3BaseException;
import webbroker3.common.message.WEB3GenResponse;
import webbroker3.util.WEB3LogUtility;


/**
 * (管理者・口座伝票取消完了リクエスト)<BR>
 * 管理者・口座伝票取消完了リクエストクラス
 */
public class WEB3AdminInformProfDistVoucherCancCmpRequest extends WEB3AdminInformProfDistVoucherUpdateCommonRequest
{
    /**
     * (ログ出力ユーティリティ)
     */
    private static WEB3LogUtility log =
        WEB3LogUtility.getInstance(
            WEB3AdminInformProfDistVoucherCancCmpRequest.class);

    /**
     * PTYPE<BR>
     */
    public static final String PTYPE = "admin_inform_prof_dist_voucher_canc_cmp";

    /**
     * SerialVersionUID<BR>
     */
    public static final long serialVersionUID = 200706042212L;

    /**
     * (暗証番号)<BR>
     * 暗証番号
     */
    public String password;

    /**
     * @@roseuid 4663A9D8009C
     */
    public WEB3AdminInformProfDistVoucherCancCmpRequest()
    {

    }

    /**
     * 当該リクエストに対応するレスポンスオブジェクトを返す。<BR>
     *<BR>
     * @@return レスポンスオブジェクト
     */
    public WEB3GenResponse createResponse()
    {
        return new WEB3AdminInformProfDistVoucherCancCmpResponse(this);
    }

    /**
     * リクエストデータの整合性をチェックする。<BR>
     * <BR>
     * １）super.validate()をコールする。<BR>
     * @@throws  WEB3BaseException
     * @@roseuid 464C20100357
     */
    public void validate() throws WEB3BaseException
    {
        final String STR_METHOD_NAME = " validate()";
        log.entering(STR_METHOD_NAME);

        // １）super.validate()をコールする。
        super.validate();

        log.exiting(STR_METHOD_NAME);
    }
}
@
