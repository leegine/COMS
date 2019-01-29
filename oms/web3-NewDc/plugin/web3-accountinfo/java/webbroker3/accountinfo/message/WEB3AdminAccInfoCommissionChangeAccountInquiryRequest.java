head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.10.01.57.20;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	8644d782f8a12fc;
filename	WEB3AdminAccInfoCommissionChangeAccountInquiryRequest.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : 管理者お客様情報手数料変更申込顧客問合せﾘｸｴｽﾄ(WEB3AdminAccInfoCommissionChangeAccountInquiryRequest.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2004/11/10  彭巍(中訊) 新規作成
*/

package webbroker3.accountinfo.message;

import webbroker3.common.WEB3BaseException;
import webbroker3.common.WEB3BusinessLayerException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.common.message.WEB3GenRequest;
import webbroker3.common.message.WEB3GenResponse;
import webbroker3.util.WEB3LogUtility;
import webbroker3.util.WEB3StringTypeUtility;


/**
 * (管理者お客様情報手数料変更申込顧客問合せﾘｸｴｽﾄ)<BR>
 * 管理者お客様情報手数料変更申込顧客問合せﾘｸｴｽﾄ<BR>
 * @@author 彭巍
 * @@version 1.0
 */
public class WEB3AdminAccInfoCommissionChangeAccountInquiryRequest extends WEB3GenRequest
{

    /**
    * Logger
    */
    private static WEB3LogUtility log =
           WEB3LogUtility.getInstance(WEB3AdminAccInfoCommissionChangeAccountInquiryRequest.class);
 
 
    /**
     * PTYPE<BR>
     */
    public static final String PTYPE = "admin_accInfo_commissionChangeAccountInquiry";

    /**
     * SerialVersionUID<BR>
     */
    public static final long serialVersionUID = 200411082144L;

    /**
     * (部店コード)<BR>
     * 部店コード<BR>
     */
    public String branchCode;

    /**
     * (顧客コード)<BR>
     * 顧客コード<BR>
     */
    public String accountCode;

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
     * @@roseuid 418F386A0138
     */
    public WEB3AdminAccInfoCommissionChangeAccountInquiryRequest()
    {

    }

    /**
     * 当該リクエストに対応するレスポンスオブジェクトを返す。<BR>
     *<BR>
     * @@return レスポンスオブジェクト
     */
    public WEB3GenResponse createResponse()
    {
        return new WEB3AdminAccInfoCommissionChangeAccountInquiryResponse(this);
    }

    /**
     * リクエストデータの整合性チェックを行う。 <BR>
     * <BR>
     * １）　@部店コードのチェック<BR>
     * 　@１－１）　@未入力の場合、例外をスローする。 <BR>
     *         class: WEB3BusinessLayerException<BR>
     *         tag:   BUSINESS_ERROR_00833<BR>
     * <BR>
     * ２）　@顧客コードのチェック<BR>
     * 　@２－１）　@未入力の場合、例外をスローする。 <BR>
     *         class: WEB3BusinessLayerException<BR>
     *         tag:   BUSINESS_ERROR_00835<BR>
     * 　@２－２）　@桁数が6でない場合、例外をスローする。<BR>
     *         class: WEB3BusinessLayerException<BR>
     *         tag:   BUSINESS_ERROR_00836<BR>
     * 　@２－３）　@数字以外の文字が含まれる場合、例外をスローする。<BR>
     *         class: WEB3BusinessLayerException<BR>
     *         tag:   BUSINESS_ERROR_01043<BR>
     * <BR>
     * ３）　@要求ページ番号チェック <BR>
     * 　@３－１）　@未入力の場合、 要求ページ番号に”１”をセットする。 <BR>
     * 　@３－２）　@数字以外の文字が含まれる場合、例外をスローする。 <BR>
     *         class: WEB3BusinessLayerException<BR>
     *         tag:   BUSINESS_ERROR_00090<BR>
     * 　@３－３）　@マイナス値の場合、例外をスローする。<BR>
     *         class: WEB3BusinessLayerException<BR>
     *         tag:   BUSINESS_ERROR_00616<BR>
     * <BR>
     * ４）　@ページ内表示行数チェック <BR>
     * 　@４－１）　@未入力の場合、例外をスローする。 <BR>
     *         class: WEB3BusinessLayerException<BR>
     *         tag:   BUSINESS_ERROR_00091<BR>
     * 　@４－２）　@数字以外の文字が含まれる場合、例外をスローする。 <BR>
     *         class: WEB3BusinessLayerException<BR>
     *         tag:   BUSINESS_ERROR_00092<BR>
     * 　@４－３）　@マイナス値の場合、例外をスローする。<BR>
     *         class: WEB3BusinessLayerException<BR>
     *         tag:   BUSINESS_ERROR_00617<BR>
     * <BR>
     * @@throws WEB3BaseException
     * @@roseuid 41513EF7007A
     */
    public void validate() throws WEB3BaseException
    {
        final String STR_METHOD_NAME="validate()";
        log.entering(STR_METHOD_NAME);
        //* １）　@部店コードのチェック<BR>
        //* 　@１－１）　@未入力の場合、例外をスローする。 <BR>
        //*         class: WEB3BusinessLayerException<BR>
        //*         tag:   BUSINESS_ERROR_00833<BR>
        //* <BR>
        if (this.branchCode == null || "".equals(this.branchCode))
        {
            //例外
            log.debug("[部店コード] = " + branchCode);
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_00833,
                this.getClass().getName() + STR_METHOD_NAME,"部店コードの未入力の場合");
        }
        //* ２）　@顧客コードのチェック<BR>
        //* 　@２－１）　@未入力の場合、例外をスローする。 <BR>
        //*         class: WEB3BusinessLayerException<BR>
        //*         tag:   BUSINESS_ERROR_00835<BR>
        if (this.accountCode == null || "".equals(this.accountCode))
        {
            //例外
            log.debug("[顧客コード] = " + accountCode);
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_00835,
                this.getClass().getName() + STR_METHOD_NAME, "顧客コードの未入力の場合");
        }
        
        //* 　@２－２）　@桁数が6でない場合、例外をスローする。<BR>
        //*         class: WEB3BusinessLayerException<BR>
        //*         tag:   BUSINESS_ERROR_00836<BR>
        if (WEB3StringTypeUtility.getByteLength(this.accountCode) != 6)
        {
            //例外
            log.debug("[顧客コード] = " + accountCode);
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_00836,
                this.getClass().getName() + STR_METHOD_NAME,
                "桁数が6でない場合"); 
        }
        //* 　@２－３）　@数字以外の文字が含まれる場合、例外をスローする。<BR>
        //*         class: WEB3BusinessLayerException<BR>
        //*         tag:   BUSINESS_ERROR_01043<BR>
        //* <BR>
        if (!WEB3StringTypeUtility.isDigit(this.accountCode))
        {
            //例外
            log.debug("[顧客コード] = " + accountCode);
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_01043,
                this.getClass().getName() + STR_METHOD_NAME, "数字以外の文字が含まれる場合");
        }
        //* ３）　@要求ページ番号チェック <BR>
        //* 　@３－１）　@未入力の場合、 要求ページ番号に”１”をセットする。 <BR>
        if (this.pageIndex == null || "".equals(this.pageIndex))
        {
            log.debug("[要求ページ番号] = " + pageIndex);
            this.pageIndex = "1";
        }
        //* 　@３－２）　@数字以外の文字が含まれる場合、例外をスローする。 <BR>
        //*         class: WEB3BusinessLayerException<BR>
        //*         tag:   BUSINESS_ERROR_00090<BR>
        if (!WEB3StringTypeUtility.isInteger(this.pageIndex))
        {
           //例外
           log.debug("[要求ページ番号] = " + pageIndex);
           throw new WEB3BusinessLayerException(
               WEB3ErrorCatalog.BUSINESS_ERROR_00090,
               this.getClass().getName() + STR_METHOD_NAME, "要求ページ番号数字以外の文字が含まれる場合");
        }
        //* 　@３－３）　@マイナス値の場合、例外をスローする。<BR>
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
                this.getClass().getName() + STR_METHOD_NAME, "要求ページ番号マイナス値の場合");        
        }
        //* ４）　@ページ内表示行数チェック <BR>
        //* 　@４－１）　@未入力の場合、例外をスローする。 <BR>
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
        //* 　@４－２）　@数字以外の文字が含まれる場合、例外をスローする。 <BR>
        //*         class: WEB3BusinessLayerException<BR>
        //*         tag:   BUSINESS_ERROR_00092<BR>
        if (!WEB3StringTypeUtility.isInteger(this.pageSize))
        {
           //例外
           log.debug("[ページ内表示行数] = " + pageSize);
           throw new WEB3BusinessLayerException(
               WEB3ErrorCatalog.BUSINESS_ERROR_00092,
               this.getClass().getName() + STR_METHOD_NAME, "ページ内表示数字以外の文字が含まれる場合");
        }
        //* 　@４－３）　@マイナス値の場合、例外をスローする。<BR>
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
                this.getClass().getName() + STR_METHOD_NAME, "ページ内表示マイナス値の場合");        
        }
        log.exiting(STR_METHOD_NAME);
    }
}
@
