head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.14.03.00.59;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	4584d7d7e626866;
filename	WEB3AdminAccOpenApplyDataDelCnfRequest.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright           : (株)大和総研 証券ソリューションシステム第二部
File Name           : 管理者口座開設資料請求データ削除確認リクエスト(WEB3AdminAccOpenApplyDataDelCnfRequest.java)
Author Name         : Daiwa Institute of Research
Revision History    : 2008/12/12 劉仁和(中訊) 新規作成 モデルNo.160
*/

package webbroker3.accountopen.message;

import webbroker3.common.WEB3BaseException;
import webbroker3.common.WEB3BusinessLayerException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.common.message.WEB3GenRequest;
import webbroker3.common.message.WEB3GenResponse;
import webbroker3.util.WEB3LogUtility;
import webbroker3.util.WEB3StringTypeUtility;


/**
 * (管理者口座開設資料請求データ削除確認リクエスト)<BR>
 * 管理者口座開設資料請求データ削除確認リクエスト<BR>
 * <BR>
 * @@author 劉仁和
 * @@version 1.0
 */
public class WEB3AdminAccOpenApplyDataDelCnfRequest extends WEB3GenRequest
{

    /**
     * ログ出力ユーティリティ<BR>
     */
    private static WEB3LogUtility log =
        WEB3LogUtility.getInstance(WEB3AdminAccOpenApplyDataDelCnfRequest.class);

    /**
     * PTYPE<BR>
     */
    public static final String PTYPE = "admin_acc_open_apply_data_del_cnf";

    /**
     * serialVersionUID<BR>
     */
    public static final long serialVersionUID = 200812121042L;

    /**
     * (識別コード)<BR>
     * 識別コード<BR>
     */
    public String requestNumber;

    /**
     * @@roseuid 4940F22B01A6
     */
    public WEB3AdminAccOpenApplyDataDelCnfRequest()
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
     * @@throws WEB3BaseException
     * @@roseuid 492E6D74007E
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

        log.exiting(STR_METHOD_NAME);
    }

    /**
     * 当該リクエストに対応するレスポンスオブジェクトを返す。<BR>
     * <BR>
     * @@return WEB3GenResponse
     */
    public WEB3GenResponse createResponse()
    {
        return new WEB3AdminAccOpenApplyDataDelCnfResponse(this);
    }
}@
