head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.14.03.03.02;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	4584d7d7e626866;
filename	WEB3AdminAccOpenApplyDataDelCmpRequest.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright           : (株)大和総研 証券ソリューションシステム第二部
File Name           : 管理者口座開設資料請求データ削除完了リクエスト(WEB3AdminAccOpenApplyDataDelCmpRequest.java)
Author Name         : Daiwa Institute of Research
Revision History    : 2008/12/12 劉仁和(中訊) 新規作成 モデルNo.160
*/

package webbroker3.accountopen.message;

import webbroker3.accountopen.define.WEB3AdminAccOpenApplyDataDelCheckFlagDef;
import webbroker3.common.WEB3BaseException;
import webbroker3.common.WEB3BusinessLayerException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.common.message.WEB3GenRequest;
import webbroker3.common.message.WEB3GenResponse;
import webbroker3.util.WEB3LogUtility;
import webbroker3.util.WEB3StringTypeUtility;


/**
 * (管理者口座開設資料請求データ削除完了リクエスト)<BR>
 * 管理者口座開設資料請求データ削除完了リクエスト<BR>
 * <BR>
 * @@author 劉仁和
 * @@version 1.0
 */
public class WEB3AdminAccOpenApplyDataDelCmpRequest extends WEB3GenRequest
{

    /**
     * ログ出力ユーティリティ<BR>
     */
    private static WEB3LogUtility log =
        WEB3LogUtility.getInstance(WEB3AdminAccOpenApplyDataDelCmpRequest.class);

    /**
     * PTYPE<BR>
     */
    public static final String PTYPE = "admin_acc_open_apply_data_del_cmp";

    /**
     * serialVersionUID<BR>
     */
    public static final long serialVersionUID = 200812111635L;

    /**
     * (識別コード)<BR>
     * 識別コード<BR>
     */
    public String requestNumber;

    /**
     * (暗証番号)<BR>
     * 暗証番号<BR>
     */
    public String password;

    /**
     * (確認フラグ)<BR>
     * 確認フラグ<BR>
     * <BR>
     * 0：未チェック<BR>
     * 1：チェック<BR>
     */
    public String checkFlag;

    /**
     * @@roseuid 4940F22B0158
     */
    public WEB3AdminAccOpenApplyDataDelCmpRequest()
    {

    }

    /**
     * リクエストデータの整合性をチェックする。<BR>
     * <BR>
     * １）　@識別コードのチェック<BR>
     * 　@１－１）　@未入力の場合、<BR>
     * 　@　@　@　@　@　@（BUSINESS_ERROR_00829）例外をスローする。<BR>
     * <BR>
     * 　@１－２）　@半角数字以外が含まれる場合、<BR>
     * 　@　@　@　@　@　@（BUSINESS_ERROR_01820）例外をスローする。<BR>
     * <BR>
     * ２）　@確認フラグのチェック<BR>
     * 　@２－１）　@this.確認フラグが未チェックの場合、<BR>
     * 　@　@　@　@　@　@（BUSINESS_ERROR_03141）例外をスローする。<BR>
     * <BR>
     * @@throws WEB3BaseException
     * @@roseuid 492E6D980205
     */
    public void validate() throws WEB3BaseException
    {
        final String STR_METHOD_NAME = "validate()";
        log.entering(STR_METHOD_NAME);

        //１）　@識別コードのチェック
        //１－１）　@未入力の場合、
        //（BUSINESS_ERROR_00829）例外をスローする。
        if (WEB3StringTypeUtility.isEmpty(this.requestNumber))
        {
            log.debug("識別コードが未指定です。");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_00829,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                "識別コードが未指定です。");
        }
        //１－２）　@半角数字以外が含まれる場合、
        //（BUSINESS_ERROR_01820）例外をスローする。
        if (!WEB3StringTypeUtility.isDigit(this.requestNumber))
        {
            log.debug("識別コードの値が半角数字以外の値です。");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_01820,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                "識別コードの値が半角数字以外の値です。");
        }

        //２）　@確認フラグのチェック
        //２－１）　@this.確認フラグが未チェックの場合、
        //（BUSINESS_ERROR_03141）例外をスローする。
        if (WEB3AdminAccOpenApplyDataDelCheckFlagDef.UNCHECK_FLAG.equals(this.checkFlag))
        {
            log.debug("確認フラグが未チェックです。");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_03141,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                "確認フラグが未チェックです。");
        }

        log.exiting(STR_METHOD_NAME);
    }

    /**
     * 当該リクエストに対応するレスポンスオブジェクトを返す。<BR>
     * <BR>
     * @@return WEB3GenResponse
     */
    public WEB3GenResponse createResponse()
    {
        return new WEB3AdminAccOpenApplyDataDelCmpResponse(this);
    }
}
@
