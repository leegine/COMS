head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.15.04.52.29;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	88c4d7eeee91bda;
filename	WEB3AdminInformPTSAccOpenStateChangeCmpRequest.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright           : (株)大和総研 証券ソリューションシステム第二部
File Name           : 管理者・PTS口座開設状況変更完了リクエスト(WEB3AdminInformPTSAccOpenStateChangeCmpRequest.java)
Author Name         : Daiwa Institute of Research
Revision History    : 2008/02/28 柴双紅(中訊) 新規作成 モデルNo.128
*/

package webbroker3.inform.message;

import webbroker3.common.WEB3BaseException;
import webbroker3.common.WEB3BusinessLayerException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.common.message.WEB3GenResponse;
import webbroker3.util.WEB3LogUtility;


/**
 * 管理者・PTS口座開設状況変更完了リクエスト<BR>
 * 管理者・PTS口座開設状況変更完了リクエストクラス<BR>
 * <BR>
 * @@author 柴双紅
 * @@version 1.0
 */
public class WEB3AdminInformPTSAccOpenStateChangeCmpRequest
    extends WEB3AdminInformPTSAccOpenStateChangeCommonRequest
{

    /**
     * ログ出力ユーティリティ
     */
    private static WEB3LogUtility log =
        WEB3LogUtility.getInstance(
            WEB3AdminInformPTSAccOpenStateChangeCmpRequest.class);

    /**
     * PTYPE
     */
    public static final String PTYPE = "admin_inform_pts_acc_open_state_change_cmp";

    /**
     * SerialVersionUID
     */
    public static final long serialVersionUID = 200802281613L;

    /**
     * (連絡種別)<BR>
     * 連絡種別
     */
    public String informType;

    /**
     * (暗証番号)<BR>
     * 暗証番号
     */
    public String password;

    /**
     * @@roseuid 47C522D40135
     */
    public WEB3AdminInformPTSAccOpenStateChangeCmpRequest()
    {

    }

    /**
     * 当該リクエストに対応するレスポンスオブジェクトを返す。<BR>
     * <BR>
     * @@return WEB3GenResponse
     */
    public WEB3GenResponse createResponse()
    {
        return new WEB3AdminInformPTSAccOpenStateChangeCmpResponse(this);
    }

    /**
     * 当リクエストデータの整合性チェックを行う。<BR>
     * （ただし、当クラス内で完結する簡易チェックのみとする。）<BR>
     * <BR>
     * １）　@super.validate()をコールする。<BR>
     * <BR>
     * ２）　@連絡種別のチェック<BR>
     * 　@２－１）　@未入力の場合、例外をスローする。<BR>
     * 　@　@class　@:　@WEB3BusinessLayerException<BR>
     * 　@　@tag　@　@:　@BUSINESS_ERROR_01817<BR>
     * @@throws WEB3BaseException
     * @@roseuid 47B4F69201AF
     */
    public void validate() throws WEB3BaseException
    {
        final String STR_METHOD_NAME = "validate()";
        log.entering(STR_METHOD_NAME);

        //super.validate()をコールする。
        super.validate();

        //連絡種別のチェック
        //未入力の場合、例外をスローする。
        if (this.informType == null)
        {
            log.debug("連絡種別が未指定です。");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_01817,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                "連絡種別が未指定です。");
        }

        log.exiting(STR_METHOD_NAME);
    }
}
@
