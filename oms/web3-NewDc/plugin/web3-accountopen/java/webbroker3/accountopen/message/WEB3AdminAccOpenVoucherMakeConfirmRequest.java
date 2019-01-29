head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.14.03.06.49;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	4584d7d7e626866;
filename	WEB3AdminAccOpenVoucherMakeConfirmRequest.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : 管理者口座開設伝票作成確認リクエスト(WEB3AdminAccOpenVoucherMakeConfirmRequest.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2004/12/15 郭英 (中訊) 新規作成
*/

package webbroker3.accountopen.message;

import webbroker3.common.WEB3BaseException;
import webbroker3.common.WEB3BusinessLayerException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.common.message.WEB3GenRequest;
import webbroker3.common.message.WEB3GenResponse;
import webbroker3.util.WEB3LogUtility;


/**
 * (管理者口座開設伝票作成確認リクエスト)<BR>
 * 管理者口座開設伝票作成確認リクエスト<BR>
 *   
 * @@author 郭英
 * @@version 1.0
 */

public class WEB3AdminAccOpenVoucherMakeConfirmRequest extends WEB3GenRequest
{

    /**
     * ログ出力ユーティリティ。<BR>
     */
    private static WEB3LogUtility log = 
        WEB3LogUtility.getInstance(WEB3AdminAccOpenVoucherMakeConfirmRequest.class);

    /**
     * PTYPE<BR>
     */
    public static final String PTYPE = "admin_accOpen_voucherMakeConfirm";

    /**
     * SerialVersionUID<BR>
     */
    public static final long serialVersionUID = 200412081601L;

    /**
     * (備考１)<BR>
     * 備考１<BR>
     * <BR>
     * ※（不備項目）備考欄<BR>
     */
    public String bikou1;

    /**
     * (備考２)<BR>
     * 備考２<BR>
     * <BR>
     * ※（不備項目）備考欄<BR>
     */
    public String bikou2;

    /**
     * (不備項目情報一覧)<BR>
     * 不備項目情報一覧<BR>
     */
    public WEB3AccOpenInvalidItemInfo[] invalidItemInfo;

    /**
     * (伝票作成情報)<BR>
     * 伝票作成情報<BR>
     */
    public WEB3AccOpenVoucherInfo voucherInfo;

    /**
     * (口座開設申込情報)<BR>
     * 口座開設申込情報<BR>
     */
    public WEB3AccOpenApplyInfo accoutOpenApplyInfo;

    /**
     * @@roseuid 41B45E7D0196
     */
    public WEB3AdminAccOpenVoucherMakeConfirmRequest()
    {

    }

    /**
     * 当該リクエストに対応するレスポンスオブジェクトを返す。<BR>
     *<BR>
     * @@return レスポンスオブジェクト
     */
    public WEB3GenResponse createResponse()
    {
        return new WEB3AdminAccOpenVoucherMakeConfirmResponse(this);
    }

    /**
     * リクエストデータの整合性をチェックする。<BR>
     * <BR>
     * １)　@口座開設申込情報<BR>
     * 　@１－１）　@未入力の場合、例外をスローする。<BR>
     *          class: WEB3BusinessLayerException<BR>
     *          tag:   BUSINESS_ERROR_01336<BR>
     * <BR>
     * ２）　@伝票作成情報<BR>
     * 　@２－１）　@未入力の場合、例外をスローする。<BR>
     *          class: WEB3BusinessLayerException<BR>
     *          tag:   BUSINESS_ERROR_01337<BR>
     * @@throws WEB3BaseException
     * @@roseuid 419443F6032F
     */
    public void validate() throws WEB3BaseException
    {
        final String STR_METHOD_NAME = " validate()";
        log.entering(STR_METHOD_NAME);
        //１）　@口座開設申込情報のチェック
        //１－１）　@未入力の場合、例外をスローする。
        if (this.accoutOpenApplyInfo == null)
        {
            log.debug(getClass().getName() + STR_METHOD_NAME);
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_01336,
                getClass().getName() + STR_METHOD_NAME,
                " 口座開設申込情報が未指定です。" + 
                this.accoutOpenApplyInfo); 
        }
        //２）　@伝票作成情報
        //２－１）　@未入力の場合、例外をスローする。
        if (this.voucherInfo == null)
        {
            log.debug(getClass().getName() + STR_METHOD_NAME);
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_01337,
                getClass().getName() + STR_METHOD_NAME,
                " 伝票作成情報が未指定です。" + this.voucherInfo); 
        }

        log.exiting(STR_METHOD_NAME);
    }

}
@
