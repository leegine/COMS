head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.16.02.38.13;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	6004d80209d0226;
filename	WEB3AdminBondDomesticProductRegistCommonRequest.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : 管理者国内債券銘柄登録共通リクエスト(WEB3AdminBondDomesticProductRegistCommonRequest.java)
Author Name      : Daiwa Institute of Research
Revision History : 2007/07/10 何文敏 (中訊) 新規作成 仕様変更・モデルNo.200
*/

package webbroker3.bd.message;

import webbroker3.common.WEB3BaseException;
import webbroker3.common.WEB3BusinessLayerException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.common.message.WEB3GenRequest;
import webbroker3.common.message.WEB3GenResponse;
import webbroker3.util.WEB3LogUtility;


/**
 * (管理者国内債券銘柄登録共通リクエスト)<BR>
 * 管理者国内債券銘柄登録共通リクエスト<BR>
 * 
 * @@author 何文敏
 * @@version 1.0
 */
public class WEB3AdminBondDomesticProductRegistCommonRequest extends WEB3GenRequest
{
    /**
     * ログユーティリティ<BR>
     */
    private static WEB3LogUtility log =
        WEB3LogUtility.getInstance(WEB3AdminBondDomesticProductRegistCommonRequest.class);

    /**
     * PTYPE<BR>
     */
    public static final String PTYPE = "admin_bond_domestic_product_regist_common";

    /**
     * SerialVersionUID<BR>
     */
    private static final long serialVersionUID = 200707111015L;

    /**
     * (銘柄ID)<BR>
     * 銘柄ID<BR>
     */
    public String productId;

    /**
     * (銘柄更新情報)<BR>
     * 銘柄更新情報<BR>
     */
    public WEB3BondDomesticProductUpdateInfo bondDomesticProductUpdateInfo;

    /**
     * @@roseuid 469336A60242
     */
    public WEB3AdminBondDomesticProductRegistCommonRequest()
    {

    }

    /**
     * 当クラスの整合性チェックを行う。<BR>
     * （ただし、当クラス内で完結する簡易チェックのみとする。）<BR>
     * <BR>
     * １）銘柄IDチェック<BR>
     * 　@　@銘柄ID == null の場合、例外をスローする。<BR>
     * 　@　@class:　@WEB3BusinessLayerException<BR>
     * 　@　@tag:　@　@BUSINESS_ERROR_02229<BR>
     * <BR>
     * ２）国内債券銘柄更新情報.validate()をコールする。<BR>
     * <BR>
     * @@throws WEB3BaseException
     * @@roseuid 46637FF303E7
     */
    public void validate() throws WEB3BaseException
    {
        final String STR_METHOD_NAME = " validate()";
        log.entering(STR_METHOD_NAME);

        //    １）銘柄IDチェック
        //　@　@銘柄ID == null の場合、例外をスローする。
        if (this.productId == null)
        {
            log.debug("銘柄IDが未指定です。");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_02229,
                this.getClass().getName() + STR_METHOD_NAME,
                "銘柄IDが未指定です。");
        }

        // ２）国内債券銘柄更新情報.validate()をコールする。
        this.bondDomesticProductUpdateInfo.validate();

        log.exiting(STR_METHOD_NAME);
    }

    /**
     * (createレスポンス)<BR>
     * (createResponse実装)<BR>
     * <BR>
     * レスポンスオブジェクトを生成して返す。
     * @@return WEB3GenResponse
     */
    public WEB3GenResponse createResponse()
    {
        return null;
    }
}
@
