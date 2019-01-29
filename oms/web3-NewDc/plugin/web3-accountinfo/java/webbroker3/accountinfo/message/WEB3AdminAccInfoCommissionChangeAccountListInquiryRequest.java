head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.10.02.06.42;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	8644d782f8a12fc;
filename	WEB3AdminAccInfoCommissionChangeAccountListInquiryRequest.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : 管理者お客様情報手数料変更申込顧客一覧問合せﾘｸｴｽﾄ(WEB3AdminAccInfoCommissionChangeAccountListInquiryRequest.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2004/11/10  彭巍(中訊) 新規作成
Revesion History : 2008/08/21  楊夫志 (中訊) 仕様変更・モデルNo.245
*/

package webbroker3.accountinfo.message;

import java.util.Date;

import webbroker3.common.WEB3BaseException;
import webbroker3.common.WEB3BusinessLayerException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.common.define.WEB3CommissionCourseDivDef;
import webbroker3.common.message.WEB3GenRequest;
import webbroker3.common.message.WEB3GenResponse;
import webbroker3.util.WEB3LogUtility;
import webbroker3.util.WEB3StringTypeUtility;


/**
 * (管理者お客様情報手数料変更申込顧客一覧問合せﾘｸｴｽﾄ)<BR>
 * 管理者お客様情報手数料変更申込顧客一覧問合せﾘｸｴｽﾄ<BR>
 * @@author 彭巍
 * @@version 1.0 
 */
public class WEB3AdminAccInfoCommissionChangeAccountListInquiryRequest extends WEB3GenRequest
{
    /**
    * Logger
    */
    private static WEB3LogUtility log =
           WEB3LogUtility.getInstance(WEB3AdminAccInfoCommissionChangeAccountListInquiryRequest.class);
 

    /**
     * PTYPE<BR>
     */
    public static final String PTYPE = "admin_accInfo_commissionChangeAccountListInquiry";

    /**
     * SerialVersionUID<BR>
     */
    public static final long serialVersionUID = 200411082142L;

    /**
     * (部店コード)<BR>
     * 部店コード<BR>
     */
    public String[] branchCode;

    /**
     * (適用開始日)<BR>
     * 適用開始日<BR>
     */
    public Date trialStartDate;

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
     * ※　@nullの場合は、すべての手数料コースが対象とする。<BR>
     */
    public String commissionCourse;

    /**
     * (要求ページ番号)<BR>
     * 要求ページ番号<BR>
     */
    public String pageIndex;

    /**
     * (ページ内表示行数)<BR>
     * ページ内表示行数<BR>
     */
    public String pageSize;

    /**
     * @@roseuid 418F386B002E
     */
    public WEB3AdminAccInfoCommissionChangeAccountListInquiryRequest()
    {

    }

    /**
     * 当該リクエストに対応するレスポンスオブジェクトを返す。<BR>
     *<BR>
     * @@return レスポンスオブジェクト
     */
    public WEB3GenResponse createResponse()
    {
        return new WEB3AdminAccInfoCommissionChangeAccountListInquiryResponse(this);
    }

    /**
     * リクエストデータの整合性チェックを行う。 <BR>
     * <BR>
     * １）　@部店コードのチェック<BR>
     * 　@１－１）　@未入力の場合、例外をスローする。<BR>
     *         class: WEB3BusinessLayerException<BR>
     *         tag:   BUSINESS_ERROR_00833<BR>
     * <BR>
     * ２）　@適用開始日のチェック<BR>
     * 　@２－１）　@未入力の場合、例外をスローする。 <BR>
     *         class: WEB3BusinessLayerException<BR>
     *         tag:   BUSINESS_ERROR_00837<BR>
     * <BR>
     * ３）　@手数料コースのチェック<BR>
     * 　@３－１）this.手数料コース≠nullでかつ、不正なコード値の場合、例外をスローする<BR>
     *         class: WEB3BusinessLayerException<BR>
     *         tag:   BUSINESS_ERROR_01096<BR>
     * <BR>
     * ４）　@要求ページ番号チェック <BR>
     * 　@４－１）　@未入力の場合、 要求ページ番号に”１”をセットする。 <BR>
     * 　@４－２）　@数字以外の文字が含まれる場合、例外をスローする。 <BR>
     *         class: WEB3BusinessLayerException<BR>
     *         tag:   BUSINESS_ERROR_00090<BR>
     * 　@４－３）　@マイナス値の場合、例外をスローする。<BR>
     *         class: WEB3BusinessLayerException<BR>
     *         tag:   BUSINESS_ERROR_00616<BR>
     * <BR>
     * ５）　@ページ内表示行数チェック <BR>
     * 　@５－１）　@未入力の場合、例外をスローする。 <BR>
     *         class: WEB3BusinessLayerException<BR>
     *         tag:   BUSINESS_ERROR_00091<BR>
     * 　@５－２）　@数字以外の文字が含まれる場合、例外をスローする。 <BR>
     *         class: WEB3BusinessLayerException<BR>
     *         tag:   BUSINESS_ERROR_00092<BR>
     * 　@５－３）　@マイナス値の場合、例外をスローする。<BR>
     *         class: WEB3BusinessLayerException<BR>
     *         tag:   BUSINESS_ERROR_00617<BR>
     * <BR>
     * @@throws WEB3BaseException
     * @@roseuid 41510A770368
     */
    public void validate() throws WEB3BaseException
    {
        final String STR_METHOD_NAME="validate()";
        log.entering(STR_METHOD_NAME);
        //* <BR>
        //* １）　@部店コードのチェック<BR>
        //* 　@１－１）　@未入力の場合、例外をスローする。<BR>
        //*         class: WEB3BusinessLayerException<BR>
        //*         tag:   BUSINESS_ERROR_00833<BR>
        //* <BR>
        if (this.branchCode == null)
        {
            //例外
            log.debug("[部店コード] = " + branchCode);
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_00833,
                this.getClass().getName() + STR_METHOD_NAME, "部店コードの未入力の場合");
        }
        
        //* ２）　@適用開始日のチェック<BR>
        //* 　@２－１）　@未入力の場合、例外をスローする。 <BR>
        //*         class: WEB3BusinessLayerException<BR>
        //*         tag:   BUSINESS_ERROR_00837<BR>
        //* <BR>
        if (this.trialStartDate == null)
        {
            //例外
            log.debug("[適用開始日] = " + trialStartDate);
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_00837,
                this.getClass().getName() + STR_METHOD_NAME, "適用開始日未入力の場合");
        }
        
        //* ３）　@手数料コースのチェック<BR>
        //* 　@３－１）　@不正なコード値の場合、例外をスローする。<BR>
        //*         class: WEB3BusinessLayerException<BR>
        //*         tag:   BUSINESS_ERROR_01096<BR>
        //* <BR>
        if (this.commissionCourse != null && !(WEB3CommissionCourseDivDef.FIXED_RATE_COMMISSION_STANDARD).equals(this.commissionCourse) &&
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
                this.getClass().getName() + STR_METHOD_NAME, "手数料コースの不正なコード値の場合");
        }
        //* ４）　@要求ページ番号チェック <BR>
        //* 　@４－１）　@未入力の場合、 要求ページ番号に”１”をセットする。 <BR>
        if (this.pageIndex == null || "".equals(this.pageIndex))
        {
            log.debug("[要求ページ番号] = " + pageIndex);
            this.pageIndex = "1"; 
        }
        //* 　@２－２）　@数字以外の文字が含まれる場合、例外をスローする。 <BR>
        //*         class: WEB3BusinessLayerException<BR>
        //*         tag:   BUSINESS_ERROR_00090<BR>
        if (!WEB3StringTypeUtility.isInteger(this.pageIndex))
        {
           //例外
           log.debug("[要求ページ番号] = " + pageIndex);
           throw new WEB3BusinessLayerException(
               WEB3ErrorCatalog.BUSINESS_ERROR_00090,
               this.getClass().getName() + STR_METHOD_NAME,"要求ページ番号数字以外の文字が含まれる場合");
        }
        //* 　@４－３）　@マイナス値の場合、例外をスローする。<BR>
        //*         class: WEB3BusinessLayerException<BR>
        //*         tag:   BUSINESS_ERROR_00616<BR>
        //* <BR>
        double l_dblPageIndex = Double.parseDouble(this.pageIndex);
        if (l_dblPageIndex <= 0)
        {
            //例外
            log.debug("[要求ページ番号] = " + pageIndex);
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_00616,
                this.getClass().getName() + STR_METHOD_NAME,"要求ページ番号数字マイナス値の場合");        
        }
        
        //* ５）　@ページ内表示行数チェック <BR>
        //* 　@５－１）　@未入力の場合、例外をスローする。 <BR>
        //*         class: WEB3BusinessLayerException<BR>
        //*         tag:   BUSINESS_ERROR_00091<BR>
        if (this.pageSize == null || "".equals(this.pageSize))
        {
           //例外
           log.debug("[ページ内表示行数] = " + pageSize);
           throw new WEB3BusinessLayerException(
               WEB3ErrorCatalog.BUSINESS_ERROR_00091,
               this.getClass().getName() + STR_METHOD_NAME, "ページ内表示行数未入力の場合");
        }
        
        //* 　@５－２）　@数字以外の文字が含まれる場合、例外をスローする。 <BR>
        //*         class: WEB3BusinessLayerException<BR>
        //*         tag:   BUSINESS_ERROR_00092<BR>
        if (!WEB3StringTypeUtility.isInteger(this.pageSize))
        {
           //例外
           log.debug("[ページ内表示行数] = " + pageSize);
           throw new WEB3BusinessLayerException(
               WEB3ErrorCatalog.BUSINESS_ERROR_00092,
               this.getClass().getName() + STR_METHOD_NAME, "ページ内表示行数数字以外の文字が含まれる場合");
        }
        //* 　@５－３）　@マイナス値の場合、例外をスローする。<BR>
        //*         class: WEB3BusinessLayerException<BR>
        //*         tag:   BUSINESS_ERROR_00617<BR>
        //* <BR>
        double l_dblPageSize = Double.parseDouble(this.pageSize);
        if (l_dblPageSize <= 0)
        {
            //例外
            log.debug("[ページ内表示行数] = " + pageSize);
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_00617,
                this.getClass().getName() + STR_METHOD_NAME, "ページ内表示行数マイナス値の場合");        
        }
        
        log.exiting(STR_METHOD_NAME);

    }
}
@
