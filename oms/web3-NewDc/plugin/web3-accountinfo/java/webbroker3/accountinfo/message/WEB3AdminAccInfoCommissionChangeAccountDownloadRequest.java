head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.10.02.02.24;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	8644d782f8a12fc;
filename	WEB3AdminAccInfoCommissionChangeAccountDownloadRequest.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : 管理者お客様情報手数料変更申込顧客ﾀﾞｳﾝﾛｰﾄﾞﾘｸｴｽﾄ(WEB3AdminAccInfoCommissionChangeAccountDownloadRequest.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2004/11/10  彭巍(中訊) 新規作成
*/

package webbroker3.accountinfo.message;

import java.util.Date;

import webbroker3.common.WEB3BaseException;
import webbroker3.common.WEB3BusinessLayerException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.common.message.WEB3GenRequest;
import webbroker3.common.message.WEB3GenResponse;
import webbroker3.util.WEB3LogUtility;
import webbroker3.util.WEB3StringTypeUtility;


/**
 * (管理者お客様情報手数料変更申込顧客ﾀﾞｳﾝﾛｰﾄﾞﾘｸｴｽﾄ)<BR>
 * 管理者お客様情報手数料変更申込顧客ﾀﾞｳﾝﾛｰﾄﾞﾘｸｴｽﾄ<BR>
 * @@author 彭巍
 * @@version 1.0 
 */
public class WEB3AdminAccInfoCommissionChangeAccountDownloadRequest extends WEB3GenRequest
{
    /**
    * Logger
    */
    private static WEB3LogUtility log =
           WEB3LogUtility.getInstance(WEB3AdminAccInfoCommissionChangeAccountDownloadRequest.class);
 

    /**
     * PTYPE<BR>
     */
    public static final String PTYPE = "admin_accInfo_commissionChangeAccountDownload";

    /**
     * SerialVersionUID<BR>
     */
    public static final long serialVersionUID = 200411082147L;

    /**
     * (適用開始日)<BR>
     * 適用開始日<BR>
     */
    public Date trialStartDate;

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
     * @@roseuid 418F3864037A
     */
    public WEB3AdminAccInfoCommissionChangeAccountDownloadRequest()
    {

    }

    /**
     * 当該リクエストに対応するレスポンスオブジェクトを返す。<BR>
     *<BR>
     * @@return レスポンスオブジェクト
     */
    public WEB3GenResponse createResponse()
    {
        return new WEB3AdminAccInfoCommissionChangeAccountDownloadResponse(this);
    }

    /**
     * リクエストデータの整合性チェックを行う。 <BR>
     * <BR>
     * １）　@適用開始日のチェック<BR>
     * 　@１－１）　@未入力の場合、例外をスローする。 <BR>
     *         class: WEB3BusinessLayerException<BR>
     *         tag:   BUSINESS_ERROR_00837<BR>
     * <BR>
     * ２）　@要求ページ番号チェック <BR>
     * 　@２－１）　@未入力の場合、 要求ページ番号に”１”をセットする。 <BR>
     * 　@２－２）　@数字以外の文字が含まれる場合、例外をスローする。 <BR>
     *         class: WEB3BusinessLayerException<BR>
     *         tag:   BUSINESS_ERROR_00090<BR>
     * 　@２－３）　@マイナス値の場合、例外をスローする。<BR>
     *         class: WEB3BusinessLayerException<BR>
     *         tag:   BUSINESS_ERROR_00616<BR>
     * <BR>
     * ３）　@ページ内表示行数チェック <BR>
     * 　@３－１）　@未入力の場合、例外をスローする。 <BR>
     *         class: WEB3BusinessLayerException<BR>
     *         tag:   BUSINESS_ERROR_00091<BR>
     * 　@３－２）　@数字以外の文字が含まれる場合、例外をスローする。 <BR>
     *         class: WEB3BusinessLayerException<BR>
     *         tag:   BUSINESS_ERROR_00092<BR>
     * 　@３－３）　@マイナス値の場合、例外をスローする。<BR>
     *         class: WEB3BusinessLayerException<BR>
     *         tag:   BUSINESS_ERROR_00617<BR>
     * @@throws WEB3BaseException
     * @@roseuid 4150D9CC004D
     */
    public void validate() throws WEB3BaseException
    {
        final String STR_METHOD_NAME="validate()";
        log.entering(STR_METHOD_NAME);
        //* １）　@適用開始日のチェック<BR>
        //* 　@１－１）　@未入力の場合、例外をスローする。 <BR>
        //*         class: WEB3BusinessLayerException<BR>
        //*         tag:   BUSINESS_ERROR_00837<BR>
        //* <BR>
        if (this.trialStartDate == null)
        {
            //例外
            log.debug("[適用開始日] = " + trialStartDate);
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_00837,
                this.getClass().getName() + STR_METHOD_NAME, "適用開始日の未入力の場合");
        }
        
        //* ２）　@要求ページ番号チェック <BR>
        //* 　@２－１）　@未入力の場合、 要求ページ番号に”１”をセットする。 <BR>
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
               this.getClass().getName() + STR_METHOD_NAME, "要求ページ番号数字以外の文字が含まれる場合");
        }
        //* 　@２－３）　@マイナス値の場合、例外をスローする。<BR>
        //*         class: WEB3BusinessLayerException<BR>
        //*         tag:   BUSINESS_ERROR_00616<BR>
        //* <BR>
        int l_dblPageIndex = Integer.parseInt(this.pageIndex);
        if (l_dblPageIndex < 0)
        {
            log.debug("[要求ページ番号] = " + pageIndex);
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_00616,
                    this.getClass().getName() + STR_METHOD_NAME,"要求ページ番号マイナス値の場合");
        }
        //* ３）　@ページ内表示行数チェック <BR>
        //* 　@３－１）　@未入力の場合、例外をスローする。 <BR>
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
        //* 　@３－２）　@数字以外の文字が含まれる場合、例外をスローする。 <BR>
        //*         class: WEB3BusinessLayerException<BR>
        //*         tag:   BUSINESS_ERROR_00092<BR>
        if (!WEB3StringTypeUtility.isInteger(this.pageSize))
        {
           //例外
           log.debug("[ページ内表示行数] = " + pageSize);;
           throw new WEB3BusinessLayerException(
               WEB3ErrorCatalog.BUSINESS_ERROR_00092,
               this.getClass().getName() + STR_METHOD_NAME, "ページ内表示行数数字以外の文字が含まれる場合");
        }
        //* 　@３－３）　@マイナス値の場合、例外をスローする。<BR>
        //*         class: WEB3BusinessLayerException<BR>
        //*         tag:   BUSINESS_ERROR_00617<BR>
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
