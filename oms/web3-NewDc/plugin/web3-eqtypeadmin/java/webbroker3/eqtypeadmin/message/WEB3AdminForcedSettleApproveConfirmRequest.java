head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.14.05.45.50;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	8884d7d97833a75;
filename	WEB3AdminForcedSettleApproveConfirmRequest.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : 管理者・強制決済仮注文承認／非承認確認リクエスト(WEB3AdminForcedSettleApproveConfirmRequest.java)
Author Name      : Daiwa Institute of Research
Revision History : 2007/04/24 趙林鵬 (中訊) 新規作成 モデルNo.128
*/

package webbroker3.eqtypeadmin.message;

import webbroker3.common.WEB3BaseException;
import webbroker3.common.WEB3BusinessLayerException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.common.message.WEB3GenRequest;
import webbroker3.common.message.WEB3GenResponse;
import webbroker3.eqtypeadmin.define.WEB3AdminEquityApproveTypeDef;
import webbroker3.util.WEB3LogUtility;

/**
 * (管理者・強制決済仮注文承認／非承認確認リクエスト)<BR>
 * 管理者・強制決済仮注文承認／非承認確認リクエストクラス<BR>
 * <BR>
 * @@author 趙林鵬
 * @@version 1.0
 */

public class WEB3AdminForcedSettleApproveConfirmRequest extends WEB3GenRequest
{
    /**
     *　@ログユーティリティ<BR>
     */
    private static WEB3LogUtility log =
        WEB3LogUtility.getInstance(WEB3AdminForcedSettleApproveConfirmRequest.class);

    /**
     *　@PTYPE<BR>
     */
    public static final String PTYPE = "admin_forced_settle_approve_confirm";

    /**
     *　@SerialVersionUID<BR>
     */
    public static final long serialVersionUID = 200704241000L;

    /**
     * 注文IDの配列<BR>
     */
    public String[] id;

    /**
     * (承認区分)<BR>
     * 承認区分<BR>
     * <BR>
     * 0：　@承認<BR>
     * 1：　@非承認<BR>
     */
    public String approveType;


    /**
     * (ソートキー)<BR>
     * ソートキー<BR>
     */
    public WEB3AdminForcedSettleSortKeyUnit[] sortKeys;

    /**
     * @@roseuid 462CA427011E
     */
    public WEB3AdminForcedSettleApproveConfirmRequest()
    {

    }

    /**
     * 当リクエストデータの整合性チェックを行う。<BR>
     * （ただし、当クラス内で完結する簡易チェックのみとする。）<BR>
     * <BR>
     * １）　@IDチェック<BR>
     * 　@１−１）　@this.ID == nullの場合、<BR>
     * 　@　@「IDがnull」の例外をスローする。<BR>
     * 　@　@　@　@class:　@WEB3BusinessLayerException<BR>
     * 　@　@　@　@tag:　@　@BUSINESS_ERROR_00600       <BR>
     * <BR>
     * ２）　@承認区分チェック<BR>
     * 　@２−１）　@this.承認区分 == nullの場合、<BR>
     * 　@　@「承認区分がnull」の例外をスローする。<BR>
     * 　@　@　@　@class:　@WEB3BusinessLayerException<BR>
     * 　@　@　@　@tag:　@　@BUSINESS_ERROR_02761       <BR>
     * <BR>
     * 　@２−２）　@this.承認区分に下記の項目以外が設定されていたら、<BR>
     * 　@　@「承認区分が未定義の値」の例外をスローする。<BR>
     * 　@　@　@・"承認"<BR>
     * 　@　@　@・"非承認"<BR>
     * 　@　@　@　@class:　@WEB3BusinessLayerException<BR>
     * 　@　@　@　@tag:　@　@BUSINESS_ERROR_02760       <BR>
     * <BR>
     * ３）　@ソートキーチェック<BR>
     * 　@３−１）　@this.ソートキーが未入力の場合、<BR>
     * 　@　@「ソートキーがnull」の例外をスローする。<BR>
     * 　@　@　@　@class:　@WEB3BusinessLayerException<BR>
     * 　@　@　@　@tag:　@　@BUSINESS_ERROR_00231       <BR>
     * <BR>
     * 　@３−２）　@this.ソートキーの要素数分以下の処理を繰り返す。<BR>
     * 　@　@３−２−１）　@ソートキー.validate()をコールする。<BR>
     * @@throws WEB3BaseException
     * @@roseuid 460754BD01AF
     */
    public void validate() throws WEB3BaseException
    {
        final String STR_METHOD_NAME = "validate()";
        log.entering(STR_METHOD_NAME);

        // 当リクエストデータの整合性チェックを行う。
        //（ただし、当クラス内で完結する簡易チェックのみとする。）
        //１）　@IDチェック
        //　@１−１）　@this.ID == nullの場合、
        //　@　@「IDがnull」の例外をスローする。
        if (this.id == null)
        {
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_00600,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                "注文IDが未指定です。");
        }

        //２）　@承認区分チェック<BR>
        // 　@２−１）　@this.承認区分 == nullの場合、
        // 　@　@「承認区分がnull」の例外をスローする。
        if (this.approveType == null)
        {
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_02761,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                "承認区分がnull。");
        }

        // 　@２−２）　@this.承認区分に下記の項目以外が設定されていたら、
        //　@　@「承認区分が未定義の値」の例外をスローする。
        //　@　@　@・"承認"
        //　@　@　@・"非承認"
        if (!WEB3AdminEquityApproveTypeDef.APPROVE.equals(this.approveType)
            && !WEB3AdminEquityApproveTypeDef.DISAPPROVE.equals(this.approveType))
        {
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_02760,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                "承認区分が未定義の値。");
        }

        //３）　@ソートキーチェック<BR>
        // 　@３−１）　@this.ソートキーが未入力の場合、
        // 　@　@「ソートキーがnull」の例外をスローする。
        if (this.sortKeys == null)
        {
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_00231,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                "ソートキーが未指定です。");
        }

        // 　@３−２）　@this.ソートキーの要素数分以下の処理を繰り返す。
        // 　@　@３−２−１）　@ソートキー.validate()をコールする。
        if (this.sortKeys != null)
        {
            int l_intSortKeysLength = this.sortKeys.length;
            for (int i = 0; i < l_intSortKeysLength; i++)
            {
                WEB3AdminForcedSettleSortKeyUnit l_adminForcedSettleSortKey = sortKeys[i];
                l_adminForcedSettleSortKey.validate();
            }
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
        return new WEB3AdminForcedSettleApproveConfirmResponse(this);
    }
}
@
