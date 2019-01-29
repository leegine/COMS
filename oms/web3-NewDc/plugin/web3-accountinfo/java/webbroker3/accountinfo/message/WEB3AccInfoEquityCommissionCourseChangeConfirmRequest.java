head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.10.02.01.30;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	8644d782f8a12fc;
filename	WEB3AccInfoEquityCommissionCourseChangeConfirmRequest.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : お客様情報株式委託手数料コース変更申込確認リクエスト(WEB3AccInfoEquityCommissionCourseChangeConfirmRequest.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2004/11/10  彭巍(中訊) 新規作成
Revesion History : 2008/08/21  楊夫志 (中訊) 仕様変更・モデルNo.245
*/

package webbroker3.accountinfo.message;

import webbroker3.common.WEB3BaseException;
import webbroker3.common.WEB3BusinessLayerException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.common.define.WEB3CommissionCourseDivDef;
import webbroker3.common.message.WEB3GenRequest;
import webbroker3.common.message.WEB3GenResponse;
import webbroker3.util.WEB3LogUtility;


/**
 * (お客様情報株式委託手数料コース変更申込確認リクエスト)<BR>
 * お客様情報株式委託手数料コース変更申込確認リクエスト<BR>
 * @@author 彭巍
 * @@version 1.0 
 */
public class WEB3AccInfoEquityCommissionCourseChangeConfirmRequest extends WEB3GenRequest
{

    /**
    * Logger
    */
    private static WEB3LogUtility log =
           WEB3LogUtility.getInstance(WEB3AccInfoEquityCommissionCourseChangeConfirmRequest.class);
    /**
     * PTYPE<BR>
     */
    public static final String PTYPE = "accInfo_equityCommissionCourseChangeConfirm";

    /**
     * SerialVersionUID<BR>
     */
    public static final long serialVersionUID = 200411082159L;

    /**
     * (手数料コース)<BR>
     * 手数料コース（手数料コースコード）<BR>
     * <BR>
     * 02：　@定率手数料（スタンダード）<BR>
     * （現物1日注文毎＋信用1日注文毎　@※リテラのみ）<BR>
     * 03：　@約定代金合計<BR>
     * （現物1日合計＋信用1日合計　@※リテラのみ）<BR>
     * 04：　@約定回数<BR>
     * 05：　@一日定額制<BR>
     * 06：　@少額ボックス<BR>
     * 07：　@現物1日合計＋信用1日注文毎<BR>
     * 08：　@現物1日注文毎＋信用1日合計<BR>
     * 16：　@少額ボックス(キャンペーン)　@<BR>
     * 99：　@上記以外（リテラ・岩井のみ）<BR>
     * <BR>
     * ※　@各コードの名称については、証券会社によって違う。<BR>
     * 　@　@Web層にて、名称に変換する。<BR>
     */
    public String commissionCourse;

    /**
     * @@roseuid 418F39F101B5
     */
    public WEB3AccInfoEquityCommissionCourseChangeConfirmRequest()
    {

    }

    /**
     * 当該リクエストに対応するレスポンスオブジェクトを返す。<BR>
     *<BR>
     * @@return レスポンスオブジェクト
     */
    public WEB3GenResponse createResponse()
    {
        return new WEB3AccInfoEquityCommissionCourseChangeConfirmResponse(this);
    }

    /**
     * リクエストデータの整合性チェックを行う。 <BR>
     * <BR>
     * １）　@手数料コースのチェック<BR>
     * 　@１－１）　@未入力の場合、例外をスローする。 <BR>
     *         class: WEB3BusinessLayerException<BR>
     *         tag:   BUSINESS_ERROR_01095<BR>
     * 　@１－２）　@不正なコード値の場合、例外をスローする。 <BR>
     *         class: WEB3BusinessLayerException<BR>
     *         tag:   BUSINESS_ERROR_01096<BR>
     * @@throws WEB3BaseException
     * @@roseuid 413E9EEE0024
     */
    public void validate() throws WEB3BaseException
    {
        final String STR_METHOD_NAME="validate()";
        log.entering(STR_METHOD_NAME);
        
        //１）　@手数料コースのチェック
          //１－１）　@未入力の場合、例外をスローする。
          //         class: WEB3BusinessLayerException<BR>
          //         tag:   BUSINESS_ERROR_01095<BR>
        if (this.commissionCourse == null || "".equals(this.commissionCourse))
        {             
            //例外
            log.debug("[手数料コース] = " + commissionCourse);
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_01095,
                this.getClass().getName() + STR_METHOD_NAME, "手数料コース未入力の場合");
        }
          //１－２）　@不正なコード値の場合、例外をスローする
        if (!(WEB3CommissionCourseDivDef.FIXED_RATE_COMMISSION_STANDARD).equals(this.commissionCourse) &&
            !(WEB3CommissionCourseDivDef.EXECUTED_TURNOVER_COUNT).equals(this.commissionCourse) &&
            !(WEB3CommissionCourseDivDef.EXECUTED_TIMES).equals(this.commissionCourse) &&
            !(WEB3CommissionCourseDivDef.FIXED_AMOUNT).equals(this.commissionCourse) &&
            !(WEB3CommissionCourseDivDef.SMALL_AMOUNT_BOX).equals(this.commissionCourse) &&
            !(WEB3CommissionCourseDivDef.EQUITY_ONE_DAY_TOTAL_ADD_MARGIN_ONE_DAY_ORDER).equals(
                this.commissionCourse) &&
            !(WEB3CommissionCourseDivDef.EQUITY_ONE_DAY_ORDER_ADD_MARGIN_ONE_DAY_TOTAL).equals(
                this.commissionCourse) &&
            !(WEB3CommissionCourseDivDef.SMALL_AMOUNT_BOX_CAMPAIGN).equals(this.commissionCourse) &&
            !(WEB3CommissionCourseDivDef.OTHER).equals(this.commissionCourse))
        {
            //例外
            log.debug("[手数料コース] = " + commissionCourse);
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_01096,
                this.getClass().getName() + STR_METHOD_NAME,"手数料コース不正なコード値の場合");
        }
        log.exiting(STR_METHOD_NAME);

    }
    
}
@
