head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.16.02.58.08;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	6004d80209d0226;
filename	WEB3AdminBondOrderReceiveHistoryRequest.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : 管理者注文受付履歴照会リクエスト(WEB3AdminBondOrderReceiveHistoryRequest.java)
Author Name      : Daiwa Institute of Research
Revision History : 2007/07/23 武波 (中訊) 新規作成 仕様変更・モデルNo.216
*/
package webbroker3.bd.message;

import webbroker3.common.WEB3BaseException;
import webbroker3.common.WEB3BusinessLayerException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.common.message.WEB3GenRequest;
import webbroker3.common.message.WEB3GenResponse;
import webbroker3.util.WEB3LogUtility;


/**
 * (管理者注文受付履歴照会リクエスト)<BR>
 * 管理者国内債券部店別注文受付履歴照会リクエスト<BR>
 * <BR>
 * @@author 武波
 * @@version 1.0
 */
public class WEB3AdminBondOrderReceiveHistoryRequest extends WEB3GenRequest
{

    /**
     * ログ出力ユーティリティ。
     */
    private static WEB3LogUtility log =
        WEB3LogUtility.getInstance(WEB3AdminBondOrderReceiveHistoryRequest.class);

    /**
     * PTYPE<BR>
     */
    public static final String PTYPE = "admin_bond_order_receive_history";

    /**
     * SerialVersionUID<BR>
     */
    public static final long serialVersionUID = 200707231733L;

    /**
     * (銘柄ID)<BR>
     * 銘柄ID<BR>
     */
    public String productID;

    /**
     * (部店コード)<BR>
     * 部店コード<BR>
     * 　@"---"が入力された場合、全部店とみなす<BR>
     */
    public String branchCode;

    /**
     * @@roseuid 46A473FE02EE
     */
    public WEB3AdminBondOrderReceiveHistoryRequest()
    {

    }

    /**
     * リクエストデータの整合性チェックを行う。<BR>
     * <BR>
     * １) 銘柄IDチェック<BR>
     * 　@this.銘柄ID==nullの場合、例外をスローする。<BR>
     * 　@　@class:　@WEB3BusinessLayerException<BR>
     * 　@　@　@tag:　@BUSINESS_ERROR_02229<BR>
     * ２) 部店コードチェック<BR>
     * 　@this.部店コード==nullの場合、例外をスローする。<BR>
     * 　@　@class:　@WEB3BusinessLayerException<BR>
     * 　@　@　@tag:　@BUSINESS_ERROR_00833<BR>
     * @@throws WEB3BaseException
     * @@roseuid 46834F5A00EB
     */
    public void validate() throws WEB3BaseException
    {
        final String STR_METHOD_NAME = " validate()";
        log.entering(STR_METHOD_NAME);

        //１) 銘柄IDチェック
        //this.銘柄ID==nullの場合、例外をスローする。
        if (this.productID == null)
        {
            log.debug("銘柄IDが未指定です。");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_02229,
                this.getClass().getName() + STR_METHOD_NAME,
                "銘柄IDが未指定です。");
        }

        //２) 部店コードチェック
        //this.部店コード==nullの場合、例外をスローする。
        if (this.branchCode == null)
        {
            log.debug("部店コードが未指定です。");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_00833,
                this.getClass().getName() + STR_METHOD_NAME,
                "部店コードが未指定です。");
        }

        log.exiting(STR_METHOD_NAME);
    }

    /**
     * (createレスポンス)<BR>
     * (createResponse実装)<BR>
     * <BR>
     * 管理者注文受付履歴照会レスポンスを生成し返す
     * @@return WEB3GenResponse
     * @@roseuid 44C426B700A9
     */
    public WEB3GenResponse createResponse()
    {
        return new WEB3AdminBondOrderReceiveHistoryResponse(this);
    }
}
@
