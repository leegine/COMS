head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.15.04.48.05;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	88c4d7eeee91bda;
filename	WEB3AdminInformProfDistVoucherUpdateCommonRequest.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright           : (株)大和総研 証券ソリューションシステム第二部
File Name           : 管理者・口座伝票更新共通リクエスト(WEB3AdminInformProfDistVoucherUpdateCommonRequest.java)
Author Name         : Daiwa Institute of Research
Revision History    : 2007/06/04 謝旋(中訊) 新規作成 モデルNo.054
*/

package webbroker3.inform.message;

import webbroker3.common.WEB3BaseException;
import webbroker3.common.WEB3BusinessLayerException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.common.message.WEB3GenRequest;
import webbroker3.common.message.WEB3GenResponse;
import webbroker3.util.WEB3LogUtility;


/**
 * (管理者・口座伝票更新共通リクエスト)<BR>
 * 管理者・口座伝票更新共通リクエスト
 */
public class WEB3AdminInformProfDistVoucherUpdateCommonRequest extends WEB3GenRequest
{
    /**
     * (ログ出力ユーティリティ)
     */
    private static WEB3LogUtility log =
        WEB3LogUtility.getInstance(
            WEB3AdminInformProfDistVoucherUpdateCommonRequest.class);

    /**
     * (部店コード)<BR>
     * 部店コード
     */
    public String branchCode;

    /**
     * (連絡種別)<BR>
     * 連絡種別
     */
    public String informType;

    /**
     * (識別コード)<BR>
     * 識別コード
     */
    public String requestNumber;

    /**
     * @@roseuid 4663A9D60129
     */
    public WEB3AdminInformProfDistVoucherUpdateCommonRequest()
    {

    }

    /**
     * リクエストデータの簡易チェック<BR>
     * <BR>
     * １）部店コード == null の場合、例外をスロー<BR>
     * 　@　@class:　@WEB3BusinessLayerException<BR>
     * 　@　@tag:　@BUSINESS_ERROR_02174<BR>
     * <BR>
     * ２）連絡種別 == null の場合、例外をスロー<BR>
     * 　@　@class:　@WEB3BusinessLayerException<BR>
     * 　@　@tag:　@BUSINESS_ERROR_01817<BR>
     * <BR>
     * ３）識別コード == nullの場合、例外をスロー<BR>
     * 　@　@class:　@WEB3BusinessLayerException<BR>
     * 　@　@tag:　@BUSINESS_ERROR_00829<BR>
     * <BR>
     * @@throws  WEB3BaseException
     * @@roseuid 46524B44017B
     */
    public void validate() throws WEB3BaseException
    {
        final String STR_METHOD_NAME = " validate()";
        log.entering(STR_METHOD_NAME);

        // １）部店コード == null の場合、例外をスロー
        if (this.branchCode == null)
        {
            log.debug("部店コードがnullです。");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_02174,
                this.getClass().getName() + STR_METHOD_NAME,
                "部店コードがnullです。");
        }

        // ２）連絡種別 == null の場合、例外をスロー
        if (this.informType == null)
        {
            log.debug("連絡種別が未指定です。");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_01817,
                this.getClass().getName() + STR_METHOD_NAME,
                "連絡種別が未指定です。");
        }

        // ３）識別コード == nullの場合、例外をスロー
        if (this.requestNumber == null)
        {
            log.debug("識別コードが未指定です。");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_00829,
                this.getClass().getName() + STR_METHOD_NAME,
                "識別コードが未指定です。");
        }

        log.exiting(STR_METHOD_NAME);
    }

    /**
     * 当該リクエストに対応するレスポンスオブジェクトを返す。<BR>
     *<BR>
     * @@return レスポンスオブジェクト
     */
    public WEB3GenResponse createResponse()
    {
        return null;
    }
}
@
