head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.15.01.19.23;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	6f44d7ebe647d68;
filename	WEB3AdminTraderAdminIPControlUpdateCommonRequest.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : 管理者・ログイン拒否IP更新共通リクエスト(WEB3AdminTraderAdminIPControlUpdateCommonRequest.java)
Author Name      : Daiwa Institute of Research
Revision History : 2008/09/23 張騰宇 (中訊) 新規作成 モデル004
*/

package webbroker3.trademanagement.message;

import webbroker3.common.WEB3BaseException;
import webbroker3.common.WEB3BusinessLayerException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.common.message.WEB3GenRequest;
import webbroker3.common.message.WEB3GenResponse;
import webbroker3.util.WEB3LogUtility;
import webbroker3.util.WEB3StringTypeUtility;

/**
 * (管理者・ログイン拒否IP更新共通リクエスト)<BR>
 * 管理者・ログイン拒否IP更新共通リクエストクラス。<BR>
 * <BR>
 * @@author 張騰宇
 * @@version 1.0
 */
public class WEB3AdminTraderAdminIPControlUpdateCommonRequest extends WEB3GenRequest
{
    /**
     * ログ出力ユーティリティ。
     */
    private static WEB3LogUtility log =
        WEB3LogUtility.getInstance(WEB3AdminTraderAdminIPControlUpdateCommonRequest.class);

    /**
     * PTYPE<BR>
     */
    public static final String PTYPE = "admin_trader_admin_ip_control_update_common";

    /**
     * SerialVersionUID<BR>
     */
    public static final long serialVersionUID = 200809221738L;

    /**
     * (ログイン拒否ID)<BR>
     * ログイン拒否ID<BR>
     */
    public String denyLoginID;

    /**
     * @@roseuid 48D75CD70369
     */
    public WEB3AdminTraderAdminIPControlUpdateCommonRequest()
    {

    }

    /**
     * 当リクエストデータの整合性チェックを行う。<BR>
     * （ただし、当クラス内で完結する簡易チェックのみとする。）<BR>
     * <BR>
     * １）　@ログイン拒否IDチェック<BR>
     * 　@１－１）　@this.ログイン拒否ID == nullの場合、例外をスローする。<BR>
     * 　@　@class : WEB3BusinessLayerException<BR>
     * 　@　@　@tag : BUSINESS_ERROR_03116<BR>
     * 　@１－２）　@this.ログイン拒否IDが半角数字以外の場合、例外をスローする。<BR>
     * 　@　@class : WEB3BusinessLayerException<BR>
     * 　@　@　@tag : BUSINESS_ERROR_03117<BR>
     * @@roseuid 48C0C5880198
     * @@throws WEB3BaseException
     */
    public void validate() throws WEB3BaseException
    {
        final String STR_METHOD_NAME = "validate()";
        log.entering(STR_METHOD_NAME);

        //１）　@ログイン拒否IDチェック
        //　@１－１）　@this.ログイン拒否ID == nullの場合、例外をスローする。
        if (this.denyLoginID == null)
        {
            log.debug("ログイン拒否IDが未指定です。");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_03116,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                "ログイン拒否IDが未指定です。");
        }

        //　@１－２）　@this.ログイン拒否IDが半角数字以外の場合、例外をスローする。
        if (!WEB3StringTypeUtility.isDigit(this.denyLoginID))
        {
            log.debug("ログイン拒否IDが半角数字以外の値です。");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_03117,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                "ログイン拒否IDが半角数字以外の値です。");
        }
        log.exiting(STR_METHOD_NAME);
    }

    /**
     * レスポンスデータを作成する。<BR>
     * <BR>
     * @@return WEB3GenResponse
     */
    public WEB3GenResponse createResponse()
    {
        return new WEB3AdminTraderAdminIPControlUpdateCommonResponse(this);
    }
}
@
