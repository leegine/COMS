head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.24.08.51.28;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	6544d8b05f516f9;
filename	WEB3AccInfoElecDeliveryRegisterChangeInputRequest.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研ビジネス・イノベーション
File Name        : 電子交付サービス登録・変更入力リクエスト(WEB3AccInfoElecDeliveryRegisterChangeInputRequest.java)
Author Name      : Daiwa Institute of Research Business Innovation
Revision History : 2010/11/12 張騰宇(中訊) 新規作成 仕様変更モデルNo.277
*/
package webbroker3.accountinfo.message;

import webbroker3.accountinfo.define.WEB3AccInfoEleDeliveryFlagDef;
import webbroker3.common.WEB3BaseException;
import webbroker3.common.WEB3BusinessLayerException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.common.message.WEB3GenRequest;
import webbroker3.common.message.WEB3GenResponse;
import webbroker3.util.WEB3LogUtility;
import webbroker3.util.WEB3StringTypeUtility;

/**
 * (電子交付サービス登録・変更入力リクエスト)<BR>
 * 電子交付サービス登録・変更入力リクエストクラス<BR>
 * <BR>
 * @@author 張騰宇
 * @@version 1.0
 */
public class WEB3AccInfoElecDeliveryRegisterChangeInputRequest extends WEB3GenRequest
{
    /**
     * ログ出力ユーティリティ。<BR>
     */
    private static WEB3LogUtility log =
        WEB3LogUtility.getInstance(WEB3AccInfoElecDeliveryRegisterChangeInputRequest.class);

    /**
     * PTYPE<BR>
     */
    public final static String PTYPE = "elec_delivery_register_change_input";

    /**
     * SerialVersionUID<BR>
     */
    public final static long serialVersionUID = 201011121537L;

    /**
     * (電子交付申込フラグ)<BR>
     * 電子交付申込フラグ<BR>
     * <BR>
     * 0：　@申込しない <BR>
     * 1：　@申込<BR>
     */
    public String eleDeliveryFlag;

    /**
     * コンストラクタ<BR>
     */
    public WEB3AccInfoElecDeliveryRegisterChangeInputRequest()
    {

    }

    /**
     * リクエストデータの整合性チェックを行う。 <BR>
     * <BR>
     * １）　@電子交付申込フラグのチェック <BR>
     * 　@１－１）　@未入力の場合、例外をスローする。 <BR>
     * class:　@WEB3BusinessLayerException<BR>
     * tag　@:　@ BUSINESS_ERROR_03221<BR>
     * 　@１－２）　@不正なコード値の場合、例外をスローする。<BR>
     * class:　@WEB3BusinessLayerException<BR>
     * tag　@:　@ BUSINESS_ERROR_03222<BR>
     * @@throws WEB3BaseException
     */
    public void validate() throws WEB3BaseException
    {
        final String STR_METHOD_NAME="validate()";
        log.entering(STR_METHOD_NAME);
        
        if (WEB3StringTypeUtility.isEmpty(this.eleDeliveryFlag))
        {
            log.debug("電子交付申込フラグが未入力です。");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_03221,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                "電子交付申込フラグが未入力です。");
        }

        if (!WEB3AccInfoEleDeliveryFlagDef.NOT_APPLY.equals(this.eleDeliveryFlag)
            && !WEB3AccInfoEleDeliveryFlagDef.APPLY.equals(this.eleDeliveryFlag))
        {
            log.debug("電子交付申込フラグが未定義の値です。");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_03222,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                "電子交付申込フラグが未定義の値です。");
        }

        log.exiting(STR_METHOD_NAME);
    }

    /**
     * 当該リクエストに対応するレスポンスオブジェクトを返す。<BR>
     *<BR>
     * @@return WEB3GenResponse
     */
    public WEB3GenResponse createResponse()
    {
        return new WEB3AccInfoElecDeliveryRegisterChangeInputResponse(this);
    }
}
@
