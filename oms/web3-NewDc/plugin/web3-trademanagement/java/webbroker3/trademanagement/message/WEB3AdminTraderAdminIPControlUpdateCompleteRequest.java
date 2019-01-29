head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.15.01.23.09;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	6f44d7ebe647d68;
filename	WEB3AdminTraderAdminIPControlUpdateCompleteRequest.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : 管理者・ログイン拒否IP登録情報変更完了リクエスト(WEB3AdminTraderAdminIPControlUpdateCompleteRequest.java)
Author Name      : Daiwa Institute of Research
Revision History : 2008/09/23 孟亞南 (中訊) 新規作成 モデルNo.004
*/
package webbroker3.trademanagement.message;

import java.util.Date;

import com.fitechlabs.xtrade.plugin.tc.gentrade.GtlUtils;

import webbroker3.common.WEB3BaseException;
import webbroker3.common.WEB3BusinessLayerException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.common.message.WEB3GenResponse;
import webbroker3.util.WEB3DateUtility;
import webbroker3.util.WEB3LogUtility;
import webbroker3.util.WEB3StringTypeUtility;

/**
 * (管理者・ログイン拒否IP登録情報変更完了リクエスト)<BR>
 * 管理者・ログイン拒否IP登録情報変更完了リクエストクラス。<BR>
 * <BR>
 * @@author 孟亞南
 * @@version 1.0
 */
public class WEB3AdminTraderAdminIPControlUpdateCompleteRequest
    extends WEB3AdminTraderAdminIPControlUpdateCommonRequest
{
    /**
     * ログ出力ユーティリティ。
     */
    private static WEB3LogUtility log =
        WEB3LogUtility.getInstance(WEB3AdminTraderAdminIPControlUpdateCompleteRequest.class);

    /**
     * PTYPE<BR>
     */
    public static final String PTYPE = "admin_trader_admin_ip_control_update_complete";

    /**
     * serialVersionUID<BR>
     */
    public static final long serialVersionUID = 200809221831L;

    /**
     * (ステータス)<BR>
     * ステータス<BR>
     * <BR>
     * 0： 対象<BR>
     * 1： 無効<BR>
     * 2： 対象外<BR>
     */
    public String status;

    /**
     * (適用開始日時)<BR>
     * 適用開始日時<BR>
     */
    public Date startDate;

    /**
     * (適用終了日時)<BR>
     * 適用終了日時<BR>
     */
    public Date endDate;

    /**
     * (暗証番号)<BR>
     * 暗証番号<BR>
     */
    public String password;

    /**
     * @@roseuid 48D75CD70231
     */
    public WEB3AdminTraderAdminIPControlUpdateCompleteRequest()
    {

    }

    /**
     * 当リクエストデータの整合性チェックを行う。<BR>
     * （ただし、当クラス内で完結する簡易チェックのみとする。）<BR>
     * <BR>
     * １）　@super.validate()をコールする。<BR>
     * <BR>
     * ２）　@ステータスチェック<BR>
     * 　@２－１）　@this.ステータス == nullの場合、例外をスローする。<BR>
     * 　@　@class: WEB3BusinessLayerException<BR>
     * 　@　@　@tag: BUSINESS_ERROR_00889<BR>
     * 　@２－２）　@this.ステータスが半角数字以外の場合、例外をスローする。<BR>
     * 　@　@class: WEB3BusinessLayerException<BR>
     * 　@　@　@tag: BUSINESS_ERROR_03123<BR>
     * <BR>
     * ３）　@適用開始日時チェック<BR>
     * 　@３－１）　@this.適用開始日時 == nullの場合、例外をスローする。<BR>
     * 　@　@class: WEB3BusinessLayerException<BR>
     * 　@　@　@tag: BUSINESS_ERROR_03124<BR>
     * <BR>
     * ４）　@適用終了日時チェック<BR>
     * 　@４－１）　@this.適用終了日時 == nullの場合、例外をスローする。<BR>
     * 　@　@class: WEB3BusinessLayerException<BR>
     * 　@　@　@tag: BUSINESS_ERROR_03125<BR>
     * 　@４－２）　@this.適用終了日時 ＜= 現在日時の場合、例外をスローする。<BR>
     * 　@　@class: WEB3BusinessLayerException<BR>
     * 　@　@　@tag: BUSINESS_ERROR_03126<BR>
     * <BR>
     * ５）　@適用日時チェック<BR>
     * 　@５－１）　@this.適用開始日時 > this.適用終了日時の場合、例外をスローする。<BR>
     * 　@　@class: WEB3BusinessLayerException<BR>
     * 　@　@　@tag: BUSINESS_ERROR_03127<BR>
     * @@roseuid 48C8B7590077
     * @@throws WEB3BaseException
     */
    public void validate() throws WEB3BaseException
    {
        final String STR_METHOD_NAME = "validate()";
        log.entering(STR_METHOD_NAME);
        //１）　@super.validate()をコールする。
        super.validate();

        //２）　@ステータスチェック
        //２－１）　@this.ステータス == nullの場合、例外をスローする。
        if (this.status == null)
        {
            log.debug("ステータスが未指定です。");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_00889,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                "ステータスが未指定です。");
        }

        //２－２）　@this.ステータスが半角数字以外の場合、例外をスローする。
        if (!WEB3StringTypeUtility.isDigit(this.status))
        {
            log.debug("ステータスが半角数字以外の値です。");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_03123,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                "ステータスが半角数字以外の値です。");
        }

        //３）　@適用開始日時チェック
        //３－１）　@this.適用開始日時 == nullの場合、例外をスローする
        if (this.startDate == null)
        {
            log.debug("適用開始日時が未指定です。");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_03124,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                "適用開始日時が未指定です。");
        }

        //４）　@適用終了日時チェック
        //４－１）　@this.適用終了日時 == nullの場合、例外をスローする。
        if (this.endDate == null)
        {
            log.debug("適用終了日時が未指定です。");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_03125,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                "適用終了日時が未指定です。");
        }

        //４－２）　@this.適用終了日時 ＜= 現在日時の場合、例外をスローする。
        if (WEB3DateUtility.compareToSecond(this.endDate, GtlUtils.getSystemTimestamp()) <= 0)
        {
            log.debug("適用終了日時エラー(適用終了日時 ＜= 現在日時)。");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_03126,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                "適用終了日時エラー(適用終了日時 ＜= 現在日時)。");
        }

        //５）　@適用日時チェック
        //５－１）　@this.適用開始日時 > this.適用終了日時の場合、例外をスローする。
        if (WEB3DateUtility.compareToSecond(this.startDate, this.endDate) > 0)
        {
            log.debug("適用開始日時は適用終了日時より大きいです。");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_03127,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                "適用開始日時は適用終了日時より大きいです。");
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
        return new WEB3AdminTraderAdminIPControlUpdateCompleteResponse(this);
    }
}
@
