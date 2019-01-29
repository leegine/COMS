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
filename	WEB3AdminEquityPTSInputCancelExecCommonRequest.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
 Copyright        : (株)大和総研 証券ソリューションシステム第二部
 File Name        : 管理者・株式(PTS)出来入力取消共通リクエスト（WEB3AdminEquityPTSInputCancelExecCommonRequest.java）
 Author Name      : Daiwa Institute of Research
 Revision History : 2008/01/22 金傑 (中訊) 新規作成モデル172
 Revision History : 2008/02/02 趙林鵬 (中訊) 仕様変更モデルNo.195
 Revision History : 2008/02/13 トウ鋒鋼 (中訊) 仕様変更モデルNo.196
 */
package webbroker3.eqtypeadmin.message;

import java.util.Date;

import webbroker3.common.WEB3BusinessLayerException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.common.message.WEB3GenRequest;
import webbroker3.common.message.WEB3GenResponse;
import webbroker3.util.WEB3LogUtility;
import webbroker3.util.WEB3StringTypeUtility;

/**
 * (管理者・株式(PTS)出来入力取消共通リクエスト)<BR>
 * 管理者・株式(PTS)出来入力取消共通リクエストクラス<BR>
 *
 * @@author 金傑
 * @@version 1.0
 */
public class WEB3AdminEquityPTSInputCancelExecCommonRequest extends WEB3GenRequest
{
    /**
     * ログ出力ユーティリティ。<BR>
     */
    private static WEB3LogUtility log =
        WEB3LogUtility.getInstance(WEB3AdminEquityPTSInputCancelExecCommonRequest.class);

    /**
     * PTYPE<BR>
     */
    public static final String PTYPE = "admin_equity_pts_input_cancel_exec_common";

    /**
     * SerialVersionUID<BR>
     */
    public static final long serialVersionUID = 200801221655L;

    /**
     * (注文ID)<BR>
     */
    public String orderId;

    /**
     * (約定株数)<BR>
     */
    public String execQuantity;

    /**
     * (約定単価)<BR>
     */
    public String execPrice;

    /**
     * (約定日時)<BR>
     */
    public Date executionTimeStamp;


    /**
     * (管理者・株式(PTS)出来入力取消共通リクエスト)<BR>
     * コンストラクタ。<BR>
     * @@roseuid 4795A0F8012C
     */
    public WEB3AdminEquityPTSInputCancelExecCommonRequest()
    {

    }

    /**
     * 当リクエストデータの整合性チェックを行う。 <BR>
     *（ただし、当クラス内で完結する簡易チェックのみとする。） <BR>
     * <BR>
     * １）　@約定株数のチェック <BR>
     * 　@１－１)　@this.約定株数 == null の場合、例外をスローする。<BR>
     * 　@　@　@class: WEB3BusinessLayerException<BR>
     * 　@　@　@　@tag: BUSINESS_ERROR_02989<BR>
     * <BR>
     * 　@１－２)　@this.約定株数が数値以外の場合、例外をスローする。<BR>
     * 　@　@　@class: WEB3BusinessLayerException<BR>
     * 　@　@　@　@tag: BUSINESS_ERROR_02990<BR>
     * <BR>
     * 　@１－３)　@this.約定株数 ≦ 0 の場合、例外をスローする。<BR>
     * 　@　@　@class: WEB3BusinessLayerException<BR>
     * 　@　@　@　@tag: BUSINESS_ERROR_02991<BR>
     * <BR>
     * 　@１－４)　@this.約定株数の桁数 ＞ 8桁 の場合、例外をスローする。<BR>
     * 　@　@　@class: WEB3BusinessLayerException<BR>
     * 　@　@　@　@tag: BUSINESS_ERROR_02992<BR>
     * <BR>
     * ２）　@約定単価のチェック<BR>
     * 　@２－１)　@this.約定単価 == null の場合、例外をスローする。<BR>
     * 　@　@　@class: WEB3BusinessLayerException<BR>
     * 　@　@　@　@tag: BUSINESS_ERROR_02021<BR>
     * <BR>
     * 　@２－２)　@this.約定単価が数値以外の場合、例外をスローする。<BR>
     * 　@　@　@class: WEB3BusinessLayerException<BR>
     * 　@　@　@　@tag: BUSINESS_ERROR_02022<BR>
     * <BR>
     * 　@２－３)　@this.約定単価 ≦ 0 の場合、例外をスローする。<BR>
     * 　@　@　@　@class: WEB3BusinessLayerException<BR>
     * 　@　@　@　@tag: BUSINESS_ERROR_02023<BR>
     * <BR>
     * 　@２－４)　@this.約定単価の桁数 ＞ 8桁 の場合、例外をスローする。<BR>
     * 　@　@　@class: WEB3BusinessLayerException<BR>
     * 　@　@　@　@tag: BUSINESS_ERROR_02993<BR>
     * <BR>
     * ３）　@約定日時のチェック<BR>
     * 　@this.約定日時＝nullの場合、<BR>
     * 　@「約定日時がnull」の例外をスローする。<BR>
     * 　@　@　@class: WEB3BusinessLayerException<BR>
     * 　@　@　@　@tag: BUSINESS_ERROR_02184<BR>
     * <BR>
     * ４）　@注文IDチェック<BR>
     * 　@this.注文ID＝nullの場合、 <BR>
     * 　@「注文IDがnull」の例外をスローする。<BR>
     * 　@　@　@class: WEB3BusinessLayerException<BR>
     * 　@　@　@　@tag: BUSINESS_ERROR_00600<BR>
     *
     * @@throws WEB3BusinessLayerException
     * @@roseuid 4766445A02C0
     */
    public void validate() throws WEB3BusinessLayerException
    {
        final String STR_METHOD_NAME = "validate()";
        log.entering(STR_METHOD_NAME);

        // １）　@約定株数のチェック
        // １－１)　@this.約定株数 == null の場合、例外をスローする。
        if (this.execQuantity == null)
        {
            log.debug("約定株数が未指定です。");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_02989,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                "約定株数が未指定です。");
        }
        // １－２)　@this.約定株数が数値以外の場合、例外をスローする。
        if (!WEB3StringTypeUtility.isNumber(this.execQuantity))
        {
            log.debug("約定株数が数値以外の値です。");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_02990,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                "約定株数が数値以外の値です。");
        }
        // １－３)　@this.約定株数 ≦ 0 の場合、例外をスローする。
        if (Double.parseDouble(this.execQuantity) <= 0)
        {
            log.debug("約定株数が0以下の値です。");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_02991,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                "約定株数が0以下の値です。");
        }
        // １－４)　@this.約定株数の桁数 ＞ 8桁 の場合、例外をスローする。
        if (this.execQuantity.length() > 8)
        {
            log.debug("約定株数のサイズが不正です。");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_02992,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                "約定株数のサイズが不正です。");
        }
        // ２）　@約定単価のチェック
        // ２－１)　@this.約定単価 == null の場合、例外をスローする。
        if (this.execPrice == null)
        {
            log.debug("約定単価が未入力です。");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_02021,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                "約定単価が未入力です。");
        }
        // ２－２)　@this.約定単価が数値以外の場合、例外をスローする。
        if (!WEB3StringTypeUtility.isNumber(this.execPrice))
        {
            log.debug("約定単価が数値以外の値です。");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_02022,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                "約定単価が数値以外の値です。");
        }
        // ２－３)　@this.約定単価 ≦ 0 の場合、例外をスローする。
        if (Double.parseDouble(this.execPrice) <= 0)
        {
            log.debug("約定単価が0以下の値です。");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_02023,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                "約定単価が0以下の値です。");
        }
        // ２－４)　@this.約定単価の桁数 ＞ 8桁 の場合、例外をスローする。
        if (this.execPrice.length() > 8)
        {
            log.debug("約定単価のサイズが不正です。");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_02993,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                "約定単価のサイズが不正です。");
        }
        // ３）　@約定日時のチェック
        // this.約定日時＝nullの場合、
        if (this.executionTimeStamp == null)
        {
            log.debug("約定日時が未入力です。");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_02184,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                "約定日時が未入力です。");
        }
        // ４）　@注文IDチェック
        // this.注文ID＝nullの場合、
        if (this.orderId == null)
        {
            log.debug("注文IDが未指定です。");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_00600,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                "注文IDが未指定です。");
        }

        log.exiting(STR_METHOD_NAME);
    }

    /**
     * 当該リクエストに対応するレスポンスオブジェクトを返す。<BR>
     *<BR>
     * @@return レスポンスオブジェクト
     */
    public WEB3GenResponse createResponse()
    {
        return new WEB3AdminEquityPTSInputCancelExecCommonResponse(this);
    }
}
@
