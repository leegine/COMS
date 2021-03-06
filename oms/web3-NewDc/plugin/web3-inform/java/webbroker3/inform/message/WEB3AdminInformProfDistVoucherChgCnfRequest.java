head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.15.04.51.42;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	88c4d7eeee91bda;
filename	WEB3AdminInformProfDistVoucherChgCnfRequest.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright           : (株)大和総研 証券ソリューションシステム第二部
File Name           : 管理者・口座伝票変更確認リクエスト(WEB3AdminInformProfDistVoucherChgCnfRequest.java)
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
 * (管理者・口座伝票変更確認リクエスト)<BR>
 * 管理者・口座伝票変更確認リクエストクラス
 */
public class WEB3AdminInformProfDistVoucherChgCnfRequest extends WEB3AdminInformProfDistVoucherUpdateCommonRequest
{
    /**
     * (ログ出力ユーティリティ)
     */
    private static WEB3LogUtility log =
        WEB3LogUtility.getInstance(
            WEB3AdminInformProfDistVoucherChgCnfRequest.class);

    /**
     * PTYPE<BR>
     */
    public static final String PTYPE = "admin_inform_prof_dist_voucher_chg_cnf";

    /**
     * SerialVersionUID<BR>
     */
    public static final long serialVersionUID = 200706042212L;

    /**
     * (連絡情報)<BR>
     * 連絡情報
     */
    public WEB3InformDetailInfoUnit informInfoUnit;

    /**
     * @@roseuid 4663A9D70223
     */
    public WEB3AdminInformProfDistVoucherChgCnfRequest()
    {

    }

    /**
     * 当該リクエストに対応するレスポンスオブジェクトを返す。<BR>
     *<BR>
     * @@return レスポンスオブジェクト
     */
    public WEB3GenResponse createResponse()
    {
        return new WEB3AdminInformProfDistVoucherChgCnfResponse(this);
    }

    /**
     * リクエストデータの整合性をチェックする。<BR>
     * <BR>
     * １）super.validate()をコールする。<BR>
     * <BR>
     * ２）連絡情報 == null の場合、例外をスローする。<BR>
     * 　@　@class:　@WEB3BusinessLayerException<BR>
     * 　@　@tag:　@BUSINESS_ERROR_01816<BR>
     * <BR>
     * ３）連絡情報.validate()をコールする。<BR>
     * @@throws  WEB3BaseException
     * @@roseuid 465146210108
     */
    public void validate() throws WEB3BaseException
    {
        final String STR_METHOD_NAME = " validate()";
        log.entering(STR_METHOD_NAME);

        // １）super.validate()をコールする。
        super.validate();

        // ２）連絡情報 == null の場合、例外をスローする。
        if (this.informInfoUnit == null)
        {
            log.debug("連絡情報が未指定です。");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_01816,
                this.getClass().getName() + STR_METHOD_NAME,
                "連絡情報が未指定です。");
        }

        // ３）連絡情報.validate()をコールする。
        this.informInfoUnit.validate();

        log.exiting(STR_METHOD_NAME);
    }
}
@
