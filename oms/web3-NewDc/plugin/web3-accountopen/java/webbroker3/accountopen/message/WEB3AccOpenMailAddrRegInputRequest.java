head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.14.02.58.09;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	4584d7d7e626866;
filename	WEB3AccOpenMailAddrRegInputRequest.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : 口座開設メールアドレス登録入力リクエスト(WEB3AccOpenMailAddrRegInputRequest.java)
Author Name      : Daiwa Institute of Research
Revision History : 2009/08/10 張騰宇(中訊) 新規作成 モデル 162
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
 * (口座開設メールアドレス登録入力リクエスト)<BR>
 * 口座開設メールアドレス登録入力リクエスト<BR>
 * @@author 張騰宇
 * @@version 1.0
 */
public class WEB3AccOpenMailAddrRegInputRequest extends WEB3GenRequest
{
    /**
     * (ログ出力ユーティリティ。)<BR>
     */
    private static WEB3LogUtility log =
        WEB3LogUtility.getInstance(WEB3AccOpenMailAddrRegInputRequest.class);

    /**
     * PTYPE<BR>
     */
    public static final String PTYPE = "acc_open_mail_addr_reg_input";

    /**
     * SerialVersionUID<BR>
     */
    public static final long serialVersionUID = 200908101112L;

    /**
     * (証券会社コード)<BR>
     * 証券会社コード<BR>
     */
    public String institutionCode;

    /**
     * (部店コード)<BR>
     * 部店コード<BR>
     */
    public String branchCode;

    /**
    *
     */
    public WEB3AccOpenMailAddrRegInputRequest()
    {

    }

    /**
     * 当該リクエストに対応するレスポンスオブジェクトを返す。<BR>
     *<BR>
     * @@return レスポンスオブジェクト<BR>
     */
    public WEB3GenResponse createResponse()
    {
        return new WEB3AccOpenMailAddrRegInputResponse(this);
    }

    /**
     * リクエストデータの整合性をチェックする。 <BR>
     * <BR>
     * 　@１）　@証券会社コードのチェック <BR>
     * 　@　@　@１－１）　@未入力の場合、例外をスローする。 <BR>
     * 　@　@　@　@class: WEB3BusinessLayerException<BR>
     * 　@　@　@　@　@tag: BUSINESS_ERROR_02775<BR>
     * 　@２）　@部店コードのチェック <BR>
     * 　@　@　@２－１）　@未入力の場合、例外をスローする。<BR>
     * 　@　@　@　@class: WEB3BusinessLayerException<BR>
     * 　@　@　@　@　@tag: BUSINESS_ERROR_02174<BR>
     * @@throws WEB3BaseException
     */
    public void validate() throws WEB3BaseException
    {
        final String STR_METHOD_NAME = "validate()";
        log.entering(STR_METHOD_NAME);

        //証券会社コードのチェック
        //未入力の場合、例外をスローする。
        if (WEB3StringTypeUtility.isEmpty(this.institutionCode))
        {
            log.debug("証券会社コードがnull。");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_02775,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                "証券会社コードがnull。");
        }

        //部店コードのチェック
        //未入力の場合、例外をスローする。
        if (WEB3StringTypeUtility.isEmpty(this.branchCode))
        {
            log.debug("部店コードがnullです。");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_02174,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                "部店コードがnullです。");
        }
        log.exiting(STR_METHOD_NAME);
    }
}
@
