head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.15.04.48.17;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	88c4d7eeee91bda;
filename	WEB3AdminInformDetailRequest.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright           : (株)大和総研 証券ソリューションシステム第二部
File Name           : 連絡情報検索詳細リクエストクラス(WEB3AdminInformDetailRequest.java)
Author Name         : Daiwa Institute of Research
Revesion History    : 2005/01/24 凌建平(中訊) 作成
Revesion History    : 2007/05/29 謝旋 (中訊) モデルNo.60
*/

package webbroker3.inform.message;

import webbroker3.common.WEB3BaseException;
import webbroker3.common.WEB3BusinessLayerException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.common.message.WEB3GenRequest;
import webbroker3.common.message.WEB3GenResponse;
import webbroker3.util.WEB3LogUtility;
import webbroker3.util.WEB3StringTypeUtility;


/**
 * (連絡情報検索詳細リクエスト)<BR>
 * 連絡情報検索詳細リクエストクラス<BR>
 * @@author 凌建平
 * @@version 1.0
 */
public class WEB3AdminInformDetailRequest extends WEB3GenRequest 
{
    /**
     * (ログ出力ユーティリティ)
     */
    private static WEB3LogUtility log =
        WEB3LogUtility.getInstance(
            WEB3AdminInformDetailRequest.class);
    
    /**
     * PTYPE<BR>
     */
    public static final String PTYPE = "admin_informDetail";

    /**
     * SerialVersionUID<BR>
     */
    public static final long serialVersionUID = 200501251303L;

    /**
     * (連絡種別)<BR>
     * 連絡種別
     */
    public String informType;
    
    /**
     * (部店コード)<BR>
     * 部店コード
     */
    public String branchCode;
    
    /**
     * (識別コード)<BR>
     * 識別コード
     */
    public String requestNumber;
    
    /**
     * @@roseuid 41EE625B00BB
     */
    public WEB3AdminInformDetailRequest() 
    {
     
    }
    
    /**
     * 当該リクエストに対応するレスポンスオブジェクトを返す。<BR>
     *<BR>
     * @@return レスポンスオブジェクト
     */
    public WEB3GenResponse createResponse()
    {
        return new WEB3AdminInformDetailResponse(this);
    }
    
    /**
     * リクエストデータの整合性をチェックする。<BR>
     * <BR>
     * １）連絡種別<BR>
     * <BR>
     *    this.連絡種別 == null or<BR>
     * 　@　@class: WEB3BusinessLayerException<BR>
     * 　@　@tag: BUSINESS_ERROR_01817<BR>
     *    this.連絡種別 != 半角英数字 or<BR>
     * 　@　@class: WEB3BusinessLayerException<BR>
     * 　@　@tag: BUSINESS_ERROR_02778<BR>
     *    this.連絡種別.length() != 2<BR>
     * 　@　@class: WEB3BusinessLayerException<BR>
     * 　@　@tag: BUSINESS_ERROR_01819<BR>
     * <BR>
     *    の場合、例外をスローする。<BR>
     * <BR>
     * ２）部店コード<BR>
     * <BR>
     *    this.部店コード == null or<BR>
     * 　@　@class: WEB3BusinessLayerException<BR>
     * 　@　@tag: BUSINESS_ERROR_00833<BR>
     *    this.部店コード != 半角数字 or<BR>
     * 　@　@class: WEB3BusinessLayerException<BR>
     * 　@　@tag: BUSINESS_ERROR_01729<BR>
     *    this.部店コード.length() != 3<BR>
     * 　@　@class: WEB3BusinessLayerException<BR>
     * 　@　@tag: BUSINESS_ERROR_00834<BR>
     * <BR>
     *    の場合、例外をスローする。<BR>
     * <BR>
     * ３）識別コード<BR>
     * <BR>
     *    this.識別コード == null or<BR>
     * 　@　@class: WEB3BusinessLayerException<BR>
     * 　@　@tag: BUSINESS_ERROR_00829<BR>
     *    this.識別コード != 半角数字 or<BR>
     * 　@　@class: WEB3BusinessLayerException<BR>
     * 　@　@tag: BUSINESS_ERROR_01820<BR>
     *    this.識別コード.length() != 13<BR>
     * 　@　@class: WEB3BusinessLayerException<BR>
     * 　@　@tag: BUSINESS_ERROR_01821<BR>
     * <BR>
     *    の場合、例外をスローする。
     * @@roseuid 41B93F3002F8
     */
    public void validate() throws WEB3BaseException
    {
        final String STR_METHOD_NAME = "validate()";
        log.entering(STR_METHOD_NAME);

        //１）連絡種別<BR>
        //  this.連絡種別 == nullの場合、例外をスローする。<BR>
        //      class: WEB3BusinessLayerException<BR>
        //      tag: BUSINESS_ERROR_01817<BR>
        if (this.informType == null)
        {
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_01817, 
                getClass().getName() + "validate",
                "連絡種別が未指定です。");
        }

        //  this.連絡種別 != 半角英数字の場合、例外をスローする。<BR>
        //      class: WEB3BusinessLayerException<BR>
        //      tag: BUSINESS_ERROR_02778<BR>
        if (!WEB3StringTypeUtility.isLetterOrDigit(this.informType))
        {
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_02778, 
                getClass().getName() + "validate",
                "連絡種別が半角英数字以外の値です。");
        }

        //  this.連絡種別.length() != 2の場合、例外をスローする。<BR>
        //      class: WEB3BusinessLayerException<BR>
        //      tag: BUSINESS_ERROR_01819<BR>
        if (this.informType.length() != 2)
        {
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_01819, 
                getClass().getName() + "validate",
                "連絡種別のサイズが不正です。");
        }

        //２）部店コード<BR>
        //  this.部店コード == nullの場合、例外をスローする。<BR>
        // 　@　@ class: WEB3BusinessLayerException<BR>
        // 　@　@ tag: BUSINESS_ERROR_00833<BR>
        if (this.branchCode == null)
        {
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_00833, 
                getClass().getName() + "validate",
                "部店コードが未指定です。");
        }

        //  this.部店コード != 半角数字の場合、例外をスローする。<BR>
        // 　@　@  class: WEB3BusinessLayerException<BR>
        // 　@　@  tag: BUSINESS_ERROR_01729<BR>
        if (!WEB3StringTypeUtility.isDigit(this.branchCode))
        {
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_01729, 
                getClass().getName() + "validate",
                "部店コードの値が数値以外の値です。");
        }

        //  this.部店コード.length() != 3の場合、例外をスローする。<BR>
        // 　@  　@class: WEB3BusinessLayerException<BR>
        // 　@  　@tag: BUSINESS_ERROR_00834<BR>
        if (this.branchCode.length() != 3)
        {
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_00834, 
                getClass().getName() + "validate",
                "部店コードのサイズが不正です。");
        }

        //３）識別コード<BR>
        //  this.識別コード == nullの場合、例外をスローする。<BR>
        //    　@class: WEB3BusinessLayerException<BR>
        //  　@　@tag: BUSINESS_ERROR_00829<BR>
        if (this.requestNumber == null)
        {
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_00829, 
                getClass().getName() + "validate",
                "識別コードが未指定です。");
        }

        //  this.識別コード != 半角数字の場合、例外をスローする。<BR>
        //  　@　@class: WEB3BusinessLayerException<BR>
        //  　@　@tag: BUSINESS_ERROR_01820<BR>
        if (!WEB3StringTypeUtility.isDigit(this.requestNumber))
        {
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_01820, 
                getClass().getName() + "validate",
                "識別コードの値が半角数字以外の値です。");
        }

        //  this.識別コード.length() != 13の場合、例外をスローする。<BR>
        //  　@　@class: WEB3BusinessLayerException<BR>
        //  　@　@tag: BUSINESS_ERROR_01821<BR>
        if (this.requestNumber.length() != 13)
        {
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_01821, 
                getClass().getName() + "validate",
                "識別コードのサイズが不正です。");
        }

        log.exiting(STR_METHOD_NAME);
    }
}
@
