head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.14.03.07.53;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	4584d7d7e626866;
filename	WEB3AccOpenChangeRequest.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : 管理者口座開設切替リクエスト（WEB3AccOpenChangeRequest.java）
Author Name      : Daiwa Institute of Research
Revision History : 2009/08/13 武波 (中訊) 新規作成 モデルNo.164
*/
package webbroker3.accountopen.message;

import webbroker3.accountopen.define.WEB3AccOpenUpdateItemDef;
import webbroker3.accountopen.define.WEB3AccOpenUpdateStatusDef;
import webbroker3.common.WEB3BaseException;
import webbroker3.common.WEB3BusinessLayerException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.common.message.WEB3GenRequest;
import webbroker3.common.message.WEB3GenResponse;
import webbroker3.util.WEB3LogUtility;
import webbroker3.util.WEB3StringTypeUtility;

/**
 * (管理者口座開設切替リクエスト)<BR>
 * 管理者口座開設切替リクエスト<BR>
 *
 * @@author 武波
 * @@version 1.0
 */
public class WEB3AccOpenChangeRequest extends WEB3GenRequest
{
    /**
     * ログ出力ユーティリティ。<BR>
     */
    private static WEB3LogUtility log =
        WEB3LogUtility.getInstance(WEB3AccOpenChangeRequest.class);

    /**
     * PTYPE<BR>
     */
    public static final String PTYPE = "acc_open_change";

    /**
     * SerialVersionUID<BR>
     */
    public static final long serialVersionUID = 200908131101L;

    /**
     * (識別コード)<BR>
     * 識別コード<BR>
     */
    public String requestNumber;

    /**
     * (更新項目)<BR>
     * 更新項目<BR>
     * <BR>
     * 1：印刷切替<BR>
     * 2：受領切替<BR>
     * 3：削除切替<BR>
     */
    public String updateItem;

    /**
     * (更新後状態)<BR>
     * 更新後状態<BR>
     * <BR>
     * 1：無効（削除）/印刷済/受領済<BR>
     * 0：有効/印刷可/未受領<BR>
     */
    public String updateStatus;

    /**
     * (管理者口座開設切替リクエスト)<BR>
     * 管理者口座開設切替リクエスト<BR>
     */
    public WEB3AccOpenChangeRequest()
    {

    }

    /**
     * 当該リクエストに対応するレスポンスオブジェクトを返す。<BR>
     * <BR>
     * @@return レスポンスオブジェクト
     */
    public WEB3GenResponse createResponse()
    {
        return new WEB3AccOpenChangeResponse(this);
    }

    /**
     * リクエストデータの整合性をチェックする。<BR>
     * <BR>
     * １）　@識別コードチェック<BR>
     * 　@１－１）　@未入力の場合、例外をスローする。<BR>
     *          class: WEB3BusinessLayerException<BR>
     *          tag:   BUSINESS_ERROR_00829<BR>
     * <BR>
     * ２）　@更新項目チェック<BR>
     * 　@２－１）　@未入力の場合、例外をスローする。<BR>
     *          class: WEB3BusinessLayerException<BR>
     *          tag:   BUSINESS_ERROR_03169<BR>
     * 　@２－２）　@下記の項目以外の場合、 例外をスローする。<BR>
     *          class: WEB3BusinessLayerException<BR>
     *          tag:   BUSINESS_ERROR_03170<BR>
     * 　@　@　@1：印刷切替<BR>
     * 　@　@　@2：受領切替<BR>
     * 　@　@　@3：削除切替<BR>
     * <BR>
     * ３）　@更新後状態チェック<BR>
     * 　@３－１）　@未入力の場合、例外をスローする。<BR>
     *          class: WEB3BusinessLayerException<BR>
     *          tag:   BUSINESS_ERROR_03171<BR>
     * 　@３－２）　@下記の項目以外の場合、 例外をスローする。<BR>
     *          class: WEB3BusinessLayerException<BR>
     *          tag:   BUSINESS_ERROR_03172<BR>
     * 　@　@　@1：無効（削除）/印刷済/受領済<BR>
     * 　@　@　@0：有効/印刷可/未受領<BR>
     * @@ throws WEB3BaseException
     */
    public void validate() throws WEB3BaseException
    {
        final String STR_METHOD_NAME = "validate()";
        log.entering(STR_METHOD_NAME);

        //１）　@識別コードチェック
        //１－１）　@未入力の場合、例外をスローする。
        if (WEB3StringTypeUtility.isEmptyOrBlank(this.requestNumber))
        {
            log.debug("識別コードが未指定です。");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_00829,
                this.getClass().getName() + STR_METHOD_NAME,
                "識別コードが未指定です。");
        }

        //２）　@更新項目チェック
        //２－１）　@未入力の場合、例外をスローする。
        if (WEB3StringTypeUtility.isEmptyOrBlank(this.updateItem))
        {
            log.debug("更新項目が未入力です。");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_03169,
                this.getClass().getName() + STR_METHOD_NAME,
                "更新項目が未入力です。");
        }

        //２－２）　@下記の項目以外の場合、 例外をスローする。
        //1：印刷切替
        //2：受領切替
        //3：削除切替
        if (!(WEB3AccOpenUpdateItemDef.PRINT_CHANGE.equals(this.updateItem)
            || WEB3AccOpenUpdateItemDef.RECEIVE_CHANGE.equals(this.updateItem)
            || WEB3AccOpenUpdateItemDef.DELETE_CHANGE.equals(this.updateItem)))
        {
            log.debug("更新項目が存在しないコード値です。");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_03170,
                this.getClass().getName() + STR_METHOD_NAME,
                "更新項目が存在しないコード値です。");
        }
        //３）　@更新後状態チェック
        //３－１）　@未入力の場合、例外をスローする。
        if (WEB3StringTypeUtility.isEmptyOrBlank(this.updateStatus))
        {
            log.debug("更新後状態が未入力です。");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_03171,
                this.getClass().getName() + STR_METHOD_NAME,
                "更新後状態が未入力です。");
        }

        //３－２）　@下記の項目以外の場合、 例外をスローする。
        //1：無効（削除）/印刷済/受領済
        //0：有効/印刷可/未受領
        if (!(WEB3AccOpenUpdateStatusDef.DELETE.equals(this.updateStatus)
            || WEB3AccOpenUpdateStatusDef.DEFAULT.equals(this.updateStatus)))
        {
            log.debug("更新後状態が存在しないコード値です。");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_03172,
                this.getClass().getName() + STR_METHOD_NAME,
                "更新後状態が存在しないコード値です。");
        }
        log.exiting(STR_METHOD_NAME);
    }
}
@
