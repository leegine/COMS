head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.15.04.48.59;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	88c4d7eeee91bda;
filename	WEB3InformPTSAccOpenApplyInpRequest.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : PTS口座開設申込入力リクエスト(WEB3InformPTSAccOpenApplyInpRequest.java)
Author Name      : Daiwa Institute of Research
Revision History : 2008/02/18 謝旋 (中訊) 新規作成 モデルNo.123
*/

package webbroker3.inform.message;

import webbroker3.common.WEB3BaseException;
import webbroker3.common.WEB3BusinessLayerException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.common.message.WEB3GenRequest;
import webbroker3.common.message.WEB3GenResponse;
import webbroker3.inform.WEB3Inform;
import webbroker3.util.WEB3LogUtility;


/**
 * (PTS口座開設申込入力リクエスト)<BR>
 * PTS口座開設申込入力リクエスト
 * <BR>
 * @@author 謝旋
 * @@version 1.0
 */
public class WEB3InformPTSAccOpenApplyInpRequest extends WEB3GenRequest
{
    /**
     * ログ出力ユーティリティ
     */
    private static WEB3LogUtility log =
        WEB3LogUtility.getInstance(
            WEB3InformPTSAccOpenApplyInpRequest.class);

    /**
     * PTYPE<BR>
     */
    public static final String PTYPE = "inform_pts_acc_open_apply_inp";

    /**
     * SerialVersionUID<BR>
     */
    public static final long serialVersionUID = 200802181641L;

    /**
     * (連絡種別)<BR>
     * 連絡種別
     */
    public String informType;

    /**
     * @@roseuid 47B9271A0222
     */
    public WEB3InformPTSAccOpenApplyInpRequest()
    {

    }

    /**
     * 当該リクエストに対応するレスポンスオブジェクトを返す。<BR>
     * <BR>
     * @@return WEB3GenResponse
     */
    public WEB3GenResponse createResponse()
    {
        return new WEB3InformPTSAccOpenApplyInpResponse(this);
    }

    /**
     * 当リクエストデータの整合性チェックを行う。<BR>
     * （ただし、当クラス内で完結する簡易チェックのみとする。）<BR>
     * <BR>
     * １）　@連絡種別チェック<BR>
     * 　@this.連絡種別 == nullの場合、例外をthrowする。<BR>
     * 　@　@　@class : WEB3BusinessLayerException<BR>
     * 　@　@　@tag   : BUSINESS_ERROR_01817<BR>
     * <BR>
     * @@throws WEB3BaseException
     * @@roseuid 47A2B612028E
     */
    public void validate() throws WEB3BaseException
    {
        final String STR_METHOD_NAME = "validate()";
        log.entering(STR_METHOD_NAME);

        //連絡種別チェック
        // this.連絡種別 == nullの場合、例外をthrowする
        if (this.informType == null)
        {
            log.debug("連絡種別が未指定です。");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_01817,
                WEB3Inform.class.getName() + "." + STR_METHOD_NAME,
                "連絡種別が未指定です。");
        }

        log.exiting(STR_METHOD_NAME);
    }
}
@
