head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.15.04.47.19;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	88c4d7eeee91bda;
filename	WEB3AdminInformProfDistVoucherMakeCmpRequest.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright           : (株)大和総研 証券ソリューションシステム第二部
File Name           : 管理者・口座伝票作成完了リクエスト(WEB3AdminInformProfDistVoucherMakeCmpRequest.java)
Author Name         : Daiwa Institute of Research
Revision History    : 2007/06/04 謝旋(中訊) 新規作成 モデルNo.054
*/

package webbroker3.inform.message;

import webbroker3.common.WEB3BaseException;
import webbroker3.common.WEB3BusinessLayerException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.common.message.WEB3GenResponse;
import webbroker3.util.WEB3LogUtility;


/**
 * (管理者・口座伝票作成完了リクエスト)<BR>
 * 管理者・口座伝票作成完了リクエスト
 */
public class WEB3AdminInformProfDistVoucherMakeCmpRequest extends WEB3AdminInformProfDistVoucherCommonRequest
{
    /**
     * (ログ出力ユーティリティ)
     */
    private static WEB3LogUtility log =
        WEB3LogUtility.getInstance(
            WEB3AdminInformProfDistVoucherMakeCmpRequest.class);

    /**
     * PTYPE<BR>
     */
    public static final String PTYPE = "admin_inform_prof_dist_voucher_make_cmp";

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
     * @@roseuid 4663A9D70000
     */
    public WEB3AdminInformProfDistVoucherMakeCmpRequest()
    {

    }

    /**
     * 当該リクエストに対応するレスポンスオブジェクトを返す。<BR>
     *<BR>
     * @@return レスポンスオブジェクト
     */
    public WEB3GenResponse createResponse()
    {
        return new WEB3AdminInformProfDistVoucherMakeCmpResponse(this);
    }

    /**
     * リクエストデータの整合性をチェックする。<BR>
     * <BR>
     * １）連絡情報 == null の場合、例外をスローする。<BR>
     * 　@　@class:　@WEB3BusinessLayerException<BR>
     * 　@　@tag:　@BUSINESS_ERROR_01816<BR>
     * <BR>
     * ２）連絡情報.validate()をコールする。<BR>
     * @@throws  WEB3BaseException
     * @@roseuid 461F2F7702D2
     */
    public void validate() throws WEB3BaseException
    {
        final String STR_METHOD_NAME = " validate()";
        log.entering(STR_METHOD_NAME);

        // １）連絡情報 == null の場合、例外をスローする。
        if (this.informInfoUnit == null)
        {
            log.debug("連絡情報がnullの値である。");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_01816,
                this.getClass().getName() + STR_METHOD_NAME,
                "連絡情報がnullの値である。");
        }
        // ２）連絡情報.validate()をコールする。
        this.informInfoUnit.validate();

        log.exiting(STR_METHOD_NAME);
    }
}
@
