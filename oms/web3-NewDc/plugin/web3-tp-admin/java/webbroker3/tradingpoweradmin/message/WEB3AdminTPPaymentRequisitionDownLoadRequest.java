head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.15.00.40.09;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	86c4d7eb25155fb;
filename	WEB3AdminTPPaymentRequisitionDownLoadRequest.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : 入金請求顧客検索ダウンロードリクエスト(WEB3AdminTPPaymentRequisitionDownLoadRequest.java)
Author Name      : Daiwa Institute of Research
Revision History : 2008/10/08 孟亞南 (中訊) 新規作成 モデルNo.027
Revision History : 2008/10/15 姜丹 (中訊) モデルNo.034 モデルNo.039
*/
package webbroker3.tradingpoweradmin.message;

import webbroker3.common.WEB3BaseException;
import webbroker3.common.WEB3BusinessLayerException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.common.message.WEB3GenResponse;
import webbroker3.tradingpoweradmin.define.WEB3AdminTPClaimReasonDef;
import webbroker3.tradingpoweradmin.define.WEB3AdminTPDaysDef;
import webbroker3.util.WEB3LogUtility;
import webbroker3.util.WEB3StringTypeUtility;


/**
 * (入金請求顧客検索ダウンロードリクエスト)<BR>
 * <BR>
 * @@author 孟亞南
 * @@version 1.0
 */
public class WEB3AdminTPPaymentRequisitionDownLoadRequest extends WEB3AdminTPPaymentRequisitionCommonRequest
{
    /**
     * ログ出力ユーティリティ。
     */
    private static WEB3LogUtility log =
        WEB3LogUtility.getInstance(WEB3AdminTPPaymentRequisitionDownLoadRequest.class);

    /**
     * PTYPE<BR>
     */
    public static final String PTYPE = "admin_tp_payment_requisition_download";

    /**
     * serialVersionUID<BR>
     */
    public static final long serialVersionUID = 200810081731L;

    /**
     * @@roseuid 48EC70B8002A
     */
    public WEB3AdminTPPaymentRequisitionDownLoadRequest()
    {

    }

    /**
     * 当リクエストデータの整合性チェックを行う。 <BR>
     * （ただし、当クラス内で完結する簡易チェックのみとする。） <BR>
     * <BR>
     * １）顧客属性のチェック<BR>
     * 　@顧客属性がnullの場合<BR>
     * 　@例外をスローする。 <BR>
     * 　@class: WEB3BusinessLayerException<BR>
     * 　@　@tag: BUSINESS_ERROR_03140<BR>
     * <BR>
     * ２）請求事由のチェック<BR>
     * 　@２－１）請求事由がnullの場合<BR>
     * 　@例外をスローする。<BR>
     * 　@class: WEB3BusinessLayerException<BR>
     * 　@　@tag: BUSINESS_ERROR_03136<BR>
     * <BR>
     * 　@２－２）立替金/特別立替金、不足金（当日）または指定なしが選択されている場合<BR>
     * 　@日数が 0 でなければ、例外をスローする。<BR>
     * 　@class: WEB3BusinessLayerException<BR>
     * 　@　@tag: BUSINESS_ERROR_03138<BR>
     * <BR>
     * ３）部店コードのチェック<BR>
     * 　@部店コードがnull　@or　@部店コード.lengthが3以外 or 部店コードが半角数字以外の場合<BR>
     * 　@例外をスローする。<BR>
     * 　@class: WEB3BusinessLayerException<BR>
     * 　@　@tag: BUSINESS_ERROR_00779<BR>
     * <BR>
     * ４）顧客コードのチェック<BR>
     * 　@顧客コードがnull以外　@and　@（顧客コード.lengthが6以外 or 顧客コードが半角数字以外）の場合<BR>
     * 　@例外をスローする。<BR>
     * 　@class: WEB3BusinessLayerException<BR>
     * 　@　@tag: BUSINESS_ERROR_00780<BR>
     * <BR>
     * ５）扱者コードのチェック<BR>
     * 　@扱者コードがnull以外　@and　@扱者コード.lengthが5以外の場合<BR>
     * 　@例外をスローする。<BR>
     * 　@class: WEB3BusinessLayerException<BR>
     * 　@　@tag: BUSINESS_ERROR_01912<BR>
     * <BR>
     * @@roseuid 48D1F805008E
     * @@throws WEB3BaseException
     */
    public void validate() throws WEB3BaseException
    {
        final String STR_METHOD_NAME = "validate()";
        log.entering(STR_METHOD_NAME);

        //１）顧客属性のチェック
        //　@顧客属性がnullの場合
        //　@例外をスローする。 
        if (this.customerAttribute == null)
        {
            log.debug("顧客属性がnullです。");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_03140,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                "顧客属性がnullです。");
        }

        //２）請求事由のチェック
        //　@２－１）請求事由がnullの場合
        //　@例外をスローする。
        if (this.claimReason == null)
        {
            log.debug("請求事由がnullです。");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_03136,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                "請求事由がnullです。");
        }

        //２－２）立替金/特別立替金、不足金（当日）または指定なしが選択されている場合
        //日数が 0 でなければ、例外をスローする。
        if (WEB3AdminTPClaimReasonDef.DEBIT_AMOUNT_SPECIAL.equals(this.claimReason)
            || WEB3AdminTPClaimReasonDef.SHORT_FALL_GENERATION_TODAY.equals(this.claimReason)
            || WEB3AdminTPClaimReasonDef.DEFAULT.equals(this.claimReason))
        {
            if (!WEB3AdminTPDaysDef.DAYS_0.equals(this.days))
            {
                log.debug("立替金/特別立替金、不足金（当日）または指定なしが選択されている場合、" +
                    "日数が0ではない。");
                log.exiting(STR_METHOD_NAME);
                throw new WEB3BusinessLayerException(
                    WEB3ErrorCatalog.BUSINESS_ERROR_03138,
                    this.getClass().getName() + "." + STR_METHOD_NAME,
                    "立替金/特別立替金、不足金（当日）または指定なしが選択されている場合、日数が0ではない。");
            }
        }

        //３）部店コードのチェック
        //　@部店コードがnull　@or　@部店コード.lengthが3以外 or 部店コードが半角数字以外の場合
        //　@例外をスローする。
        if (this.branchCode == null
            || this.branchCode.length() != 3
            || !WEB3StringTypeUtility.isDigit(this.branchCode))
        {
            log.debug("部店コードの入力が不正です。");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_00779,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                "部店コードの入力が不正です。");
        }

        //４）顧客コードのチェック
        //　@顧客コードがnull以外　@and　@（顧客コード.lengthが6以外 or 顧客コードが半角数字以外）の場合
        //　@例外をスローする。
        if (this.accountCode != null
            && (this.accountCode.length() != 6
                || !WEB3StringTypeUtility.isDigit(this.accountCode)))
        {
            log.debug("顧客コードの入力が不正です。");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_00780,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                "顧客コードの入力が不正です。");
        }

        //５）扱者コードのチェック
        //　@扱者コードがnull以外　@and　@扱者コード.lengthが5以外の場合
        //　@例外をスローする。
        if (this.traderCode != null && this.traderCode.length() != 5)
        {
            log.debug("扱者コード（文字列）の長さが不正です。");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_01912,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                "扱者コード（文字列）の長さが不正です。");
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
        return new WEB3AdminTPPaymentRequisitionDownLoadResponse();
    }
}
@
