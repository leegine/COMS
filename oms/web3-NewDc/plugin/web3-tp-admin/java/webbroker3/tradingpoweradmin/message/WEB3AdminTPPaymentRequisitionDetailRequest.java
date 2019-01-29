head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.15.00.41.26;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	86c4d7eb25155fb;
filename	WEB3AdminTPPaymentRequisitionDetailRequest.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : 入金請求顧客検索詳細リクエスト(WEB3AdminTPPaymentRequisitionDetailRequest.java)
Author Name      : Daiwa Institute of Research
Revision History : 2008/10/08 孟亞南 (中訊) 新規作成 モデルNo.027
*/
package webbroker3.tradingpoweradmin.message;

import webbroker3.common.WEB3BaseException;
import webbroker3.common.WEB3BusinessLayerException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.common.message.WEB3GenRequest;
import webbroker3.common.message.WEB3GenResponse;
import webbroker3.util.WEB3LogUtility;
import webbroker3.util.WEB3StringTypeUtility;


/**
 * (入金請求顧客検索詳細リクエスト)<BR>
 * <BR>
 * @@author 孟亞南
 * @@version 1.0
 */
public class WEB3AdminTPPaymentRequisitionDetailRequest extends WEB3GenRequest
{
    /**
     * ログ出力ユーティリティ。<BR>
     */
    private static WEB3LogUtility log =
        WEB3LogUtility.getInstance(WEB3AdminTPPaymentRequisitionDetailRequest.class);

    /**
     * PTYPE<BR>
     */
    public static final String PTYPE = "admin_tp_payment_requisition_detail";

    /**
     * serialVersionUID<BR>
     */
    public static final long serialVersionUID = 200810081729L;

    /**
     * (部店コード)<BR>
     */
    public String branchCode;

    /**
     * (顧客コード)<BR>
     */
    public String accountCode;

    /**
     * @@roseuid 48EC70330132
     */
    public WEB3AdminTPPaymentRequisitionDetailRequest()
    {

    }

    /**
     * 当リクエストデータの整合性チェックを行う。 <BR>
     * （ただし、当クラス内で完結する簡易チェックのみとする。） <BR>
     * <BR>
     * １）部店コードのチェック<BR>
     * 　@部店コードがnull　@or　@部店コード.lengthが3以外 or 部店コードが半角数字以外の場合<BR>
     * 　@例外をスローする。<BR>
     * 　@class: WEB3BusinessLayerException<BR>
     * 　@　@tag: BUSINESS_ERROR_00779<BR>
     * <BR>
     * ２）顧客コードのチェック<BR>
     * 　@顧客コードがnull　@or　@顧客コード.lengthが6以外 or 顧客コードが半角数字以外の場合<BR>
     * 　@例外をスローする。<BR>
     * 　@class: WEB3BusinessLayerException<BR>
     * 　@　@tag: BUSINESS_ERROR_00780<BR>
     * <BR>
     * @@roseuid 48C768D600E5
     * @@throws WEB3BaseException
     */
    public void validate() throws WEB3BaseException
    {
        final String STR_METHOD_NAME = "validate()";
        log.entering(STR_METHOD_NAME);

        //１）部店コードのチェック
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

        // ２）顧客コードのチェック
        //　@顧客コードがnull　@or　@顧客コード.lengthが6以外 or 顧客コードが半角数字以外の場合合
        //　@例外をスローする。
        if (this.accountCode == null
            || this.accountCode.length() != 6
            || !WEB3StringTypeUtility.isDigit(this.accountCode))
        {
            log.debug("顧客コードの入力が不正です。");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_00780,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                "顧客コードの入力が不正です。");
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
        return new WEB3AdminTPPaymentRequisitionDetailResponse();
    }
}
@
