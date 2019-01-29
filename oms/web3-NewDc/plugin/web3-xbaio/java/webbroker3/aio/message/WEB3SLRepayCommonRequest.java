head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.16.05.17.31;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	8f04d80403d696d;
filename	WEB3SLRepayCommonRequest.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : 証券担保ローン返済申込共通リクエスト(WEB3SLRepayCommonRequest.java)
Author Name      : Daiwa Institute of Research
Revision History : 2007/09/13 何文敏 (中訊) 新規作成 仕様変更・モデルNo.758
*/

package webbroker3.aio.message;

import java.util.Date;

import webbroker3.common.WEB3BaseException;
import webbroker3.common.WEB3BusinessLayerException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.common.message.WEB3GenRequest;
import webbroker3.common.message.WEB3GenResponse;
import webbroker3.util.WEB3LogUtility;
import webbroker3.util.WEB3StringTypeUtility;


/**
 * (証券担保ローン返済申込共通リクエスト)<BR>
 * 担保ローン返済共通リクエストクラス<BR>
 *
 * @@author 何文敏
 * @@version 1.0
 */
public class WEB3SLRepayCommonRequest extends WEB3GenRequest
{
    /**
     * ログユーティリティ<BR>
     */
    private static WEB3LogUtility log =
        WEB3LogUtility.getInstance(WEB3SLRepayCommonRequest.class);

    /**
     * PTYPE<BR>
     */
    public final static  String PTYPE = "sl_repay_common";

    /**
     * serialVersionUID<BR>
     */
    public final static long serialVersionUID = 200709121532L;

    /**
     * (返済予定日)<BR>
     * 画面にて選択された返済予定日<BR>
     */
    public Date repayScheduledDate;

    /**
     * (返済額)<BR>
     * 画面にて入力された返済額<BR>
     */
    public String repayAmt;

    /**
     * @@roseuid 46E89086003B
     */
    public WEB3SLRepayCommonRequest()
    {

    }

    /**
     * リクエストデータの整合性をチェックする。<BR>
     * <BR>
     * １）返済額チェック<BR>
     * <BR>
     * リクエストデータ.返済額に数字以外の文字が含まれる or<BR>
     * 　@　@　@class:　@WEB3BusinessLayerException<BR>
     * 　@　@　@tag　@:　@BUSINESS_ERROR_02909<BR>
     * リクエストデータ.返済額 = null or<BR>
     * 　@　@　@class:　@WEB3BusinessLayerException<BR>
     * 　@　@　@tag　@:　@BUSINESS_ERROR_02910<BR>
     * リクエストデータ.返済額 <= 0 or<BR>
     * 　@　@　@class:　@WEB3BusinessLayerException<BR>
     * 　@　@　@tag　@:　@BUSINESS_ERROR_02911<BR>
     * リクエストデータ.返済額.length() > 12<BR>
     * 　@　@　@class:　@WEB3BusinessLayerException<BR>
     * 　@　@　@tag　@:　@BUSINESS_ERROR_02912<BR>
     * <BR>
     * の場合、例外をスローする。<BR>
     * <BR>
     * ２）返済予定日チェック<BR>
     * <BR>
     * リクエストデータ.返済予定日 = null<BR>
     * の場合、例外をスローする。<BR>
     * 　@　@　@class:　@WEB3BusinessLayerException<BR>
     * 　@　@　@tag　@:　@BUSINESS_ERROR_02913<BR>
     * <BR>
     * <BR>
     * @@throws WEB3BaseException
     * @@roseuid 46CA39520149
     */
    public void validate() throws WEB3BaseException
    {
        final String STR_METHOD_NAME = "validate()";
        log.entering(STR_METHOD_NAME);

        // リクエストデータ.返済額 = null
        if (this.repayAmt == null)
        {
            log.debug("返済額が未指定です。");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_02910,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                "返済額が未指定です。");
        }
        // リクエストデータ.返済額に数字以外の文字が含まれる
        if (!WEB3StringTypeUtility.isNumber(this.repayAmt))
        {
            log.debug("返済額が数字以外の値です。");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_02909,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                "返済額が数字以外の値です。");
        }
        // リクエストデータ.返済額 <= 0
        if (Double.parseDouble(this.repayAmt) <= 0)
        {
            log.debug("返済額の値が0以下です。");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_02911,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                "返済額の値が0以下です。");
        }
        // リクエストデータ.返済額.length() > 12
        if (this.repayAmt.length() > 12)
        {
            log.debug("返済額のサイズが不正です。");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_02912,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                "返済額のサイズが不正です。");
        }
        // ２）返済予定日チェック
        // リクエストデータ.返済予定日 = nullの場合
        if (this.repayScheduledDate == null)
        {
            log.debug("返済予定日が未指定です。");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_02913,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                "返済予定日が未指定です。");
        }

        log.exiting(STR_METHOD_NAME);
    }

    /**
     * 当該リクエストに対応するレスポンスオブジェクトを返す。<BR>
     * <BR>
     * @@return WEB3GenResponse<BR>
     * @@roseuid 46BC080901A7
     */
    public WEB3GenResponse createResponse()
    {
        return new WEB3SLRepayCommonResponse(this);
    }
}
@
