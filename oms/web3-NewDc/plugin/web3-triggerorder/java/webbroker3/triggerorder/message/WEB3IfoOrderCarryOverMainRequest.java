head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.15.01.43.30;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	8704d7ec30c0c9d;
filename	WEB3IfoOrderCarryOverMainRequest.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : 先物OP注文繰越メインリクエスト(WEB3IfoOrderCarryOverMainRequest.java)
Author Name      : Daiwa Institute of Research
Revision History : 2007/6/21 孫洪江 (中訊) 新規作成 モデル 669
*/

package webbroker3.triggerorder.message;

import webbroker3.common.WEB3BaseException;
import webbroker3.common.WEB3BusinessLayerException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.common.message.WEB3BackRequest;
import webbroker3.common.message.WEB3BackResponse;
import webbroker3.util.WEB3LogUtility;

/**
 * (先物OP注文繰越メインリクエスト)<BR>
 * 先物OP注文繰越メインリクエストクラス<BR>
 * @@author 孫洪江
 * @@version 1.0
 */
public class WEB3IfoOrderCarryOverMainRequest extends WEB3BackRequest
{
    /**
     *　@ログユーティリティ<BR>
     */
    private static WEB3LogUtility log =
        WEB3LogUtility.getInstance(WEB3IfoOrderCarryOverMainRequest.class);

    /**
     * PTYPE<BR>
     */
    public final static String PTYPE = "ifo_order_carryover_main";

    /**
     * SerialVersionUID<BR>
     */
    public final static long serialVersionUID = 200706211717L;

    /**
     * (証券会社コード)<BR>
     */
    public String institutionCode;

    /**
     * (From口座ID)<BR>
     */
    public long rangeFrom;

    /**
     * (To口座ID)<BR>
     */
    public long rangeTo;

    public WEB3IfoOrderCarryOverMainRequest()
    {

    }

    /**
     * 当リクエストデータの整合性チェックを行う。<BR>
     * （ただし、当クラス内で完結する簡易チェックのみとする。）<BR>
     * <BR>
     * １）　@証券会社コードチェック<BR>
     * 　@　@this.証券会社コード＝nullの場合、例外をthrowする。<BR>
     * 　@　@　@　@class:　@WEB3BusinessLayerException<BR>
     * 　@　@　@　@tag:　@　@BUSINESS_ERROR_00827<BR>
     * <BR>
     * @@throws WEB3BaseException
     */
    public void validate() throws WEB3BaseException
    {
        final String STR_METHOD_NAME = "validate()";
        log.entering(STR_METHOD_NAME);

        //当リクエストデータの整合性チェックを行う。
        // （ただし、当クラス内で完結する簡易チェックのみとする。）
        //１）　@証券会社コードチェック
        // 　@　@this.証券会社コード＝nullの場合、例外をthrowする。
        if (this.institutionCode == null)
        {
            log.debug("証券会社コードが未指定です。");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_00827,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                "証券会社コードが未指定です。");
        }

        log.exiting(STR_METHOD_NAME);
    }

    /**
     * 当該リクエストに対応するレスポンスオブジェクトを返す。<BR>
     * <BR>
     * @@return レスポンスオブジェクト
     */
    public WEB3BackResponse createResponse()
    {
        return new WEB3IfoOrderCarryOverMainResponse(this);
    }
}
@
