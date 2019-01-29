head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.15.04.52.03;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	88c4d7eeee91bda;
filename	WEB3AdminInformCommonRequest.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright           : (株)大和総研 証券ソリューションシステム第二部
File Name           : 連絡情報検索共通リクエストクラス(WEB3AdminInformCommonRequest.java)
Author Name         : Daiwa Institute of Research
Revesion History    : 2005/01/21 凌建平(中訊) 作成
Revesion History    : 2007/05/11 劉立峰 (中訊) モデルNo.34
Revesion History    : 2007/05/29 謝旋 (中訊) モデルNo.59
Revesion History    : 2007/06/11 李木子 (中訊) モデルNo.75
*/

package webbroker3.inform.message;

import java.util.Date;

import webbroker3.common.WEB3BaseException;
import webbroker3.common.WEB3BusinessLayerException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.common.define.WEB3AscDescDef;
import webbroker3.common.message.WEB3GenRequest;
import webbroker3.common.message.WEB3GenResponse;
import webbroker3.inform.define.WEB3InformKeyItemDef;
import webbroker3.util.WEB3DateUtility;
import webbroker3.util.WEB3LogUtility;
import webbroker3.util.WEB3StringTypeUtility;


/**
 * (連絡情報検索共通リクエスト)<BR>
 * 連絡情報検索共通リクエストクラス<BR>
 * @@author 凌建平
 * @@version 1.0
 */
public class WEB3AdminInformCommonRequest extends WEB3GenRequest 
{
    /**
     * (ログ出力ユーティリティ)
     */
    private static WEB3LogUtility log =
        WEB3LogUtility.getInstance(
            WEB3AdminInformCommonRequest.class);
    
    /**
     * (連絡種別)<BR>
     * 連絡種別
     */
    public String informType;
    
    /**
     * (部店コード)<BR>
     * 部店コードの配列
     */
    public String[] branchCode;
    
    /**
     * (識別コード)<BR>
     * 識別コード
     */
    public String requestNumber;
    
    /**
     * (顧客コード)<BR>
     * 顧客コード
     */
    public String accountCode;
    
    /**
     * (顧客名)<BR>
     * 顧客名
     */
    public String accountName;
    
    /**
     * (受付日時（自）)<BR>
     * 受付日時（自）
     */
    public Date receptionDateFrom;
    
    /**
     * (受付日時（至）)<BR>
     * 受付日時（至）
     */
    public Date receptionDateTo;
    
    /**
     * (銘柄コード)<BR>
     * 銘柄コード
     */
    public String productCode;
    
    /**
     * (扱者コード)<BR>
     * 扱者コード
     */
    public String traderCode;
    
    /**
     * (伝票作成情報)<BR>
     * 伝票作成情報
     */
    public String[] voucherInfoList;
    
    /**
     * (ソートキー)<BR>
     * ソートキー<BR>
     * <BR>
     * キー項目は以下のとおり<BR>
     * <BR>
     * ・識別コード<BR>
     * ・部店コード<BR>
     * ・顧客コード<BR>
     * ・受付日時
     */
    public WEB3InformSortKey[] sortKeys;
    
    /**
     * @@roseuid 41EE625A0399
     */
    public WEB3AdminInformCommonRequest() 
    {
     
    }
    
    /**
     * 当該リクエストに対応するレスポンスオブジェクトを返す。<BR>
     *<BR>
     * @@return レスポンスオブジェクト
     */
    public WEB3GenResponse createResponse()
    {
        return null;
    }
    
    /**
     * リクエストデータの整合性をチェックする。<BR>
     * <BR>
     * １）連絡種別<BR>
     * <BR>
     *    this.連絡種別 == null or<BR>
     * 　@class: WEB3BusinessLayerException<BR>
     * 　@tag: BUSINESS_ERROR_01817<BR>
     *    this.連絡種別 != 半角英数字 or<BR>
     * 　@class: WEB3BusinessLayerException<BR>
     * 　@tag: BUSINESS_ERROR_02778<BR>
     *    this.連絡種別.length() != 2<BR>
     * 　@class: WEB3BusinessLayerException<BR>
     * 　@tag: BUSINESS_ERROR_01819<BR>
     * <BR>
     *    の場合、例外をスローする。<BR>
     * <BR>
     * ２）部店コード<BR>
     * <BR>
     * ２－１）<BR>
     * <BR>
     *    this.部店コード == null or<BR>
     * 　@class: WEB3BusinessLayerException<BR>
     * 　@tag: BUSINESS_ERROR_00833<BR>
     *    this.部店コード.length() == 0<BR>
     * 　@class: WEB3BusinessLayerException<BR>
     * 　@tag: BUSINESS_ERROR_01757<BR>
     * <BR>
     *    の場合、例外をスローする。<BR>
     * <BR>
     * ２－２）部店コードの各要素についてチェックする。<BR>
     * <BR>
     *    部店コード != 半角数字 or<BR>
     * 　@class: WEB3BusinessLayerException<BR>
     * 　@tag: BUSINESS_ERROR_01729<BR>
     *    部店コード.length() != 3<BR>
     * 　@class: WEB3BusinessLayerException<BR>
     * 　@tag: BUSINESS_ERROR_00834<BR>
     * <BR>
     *    の場合、例外をスローする。<BR>
     * <BR>
     * ３）識別コード<BR>
     * <BR>
     *    this.識別コード != null and<BR>
     *    ( this.識別コード != 半角数字 or<BR>
     * 　@class: WEB3BusinessLayerException<BR>
     * 　@tag: BUSINESS_ERROR_01820<BR>
     *    this.識別コード != null and<BR>
     *    this.識別コード.length() > 13 )<BR>
     * 　@class: WEB3BusinessLayerException<BR>
     * 　@tag: BUSINESS_ERROR_01821<BR>
     * <BR>
     *    の場合、例外をスローする。<BR>
     * <BR>
     * ４）顧客コード<BR>
     * <BR>
     *    this.顧客コード != null and<BR>
     *    ( this.顧客コード != 半角数字 or<BR>
     * 　@class: WEB3BusinessLayerException<BR>
     * 　@tag: BUSINESS_ERROR_01043<BR>
     *    this.顧客コード != null and<BR>
     *    this.顧客コード.length() > 6 )<BR>
     * 　@class: WEB3BusinessLayerException<BR>
     * 　@tag: BUSINESS_ERROR_00836<BR>
     * <BR>
     *    の場合、例外をスローする。<BR>
     * <BR>
     * ５）顧客名<BR>
     * <BR>
     *    this.顧客名 != null and<BR>
     *    this.顧客名 != 全角文字<BR>
     * 　@class: WEB3BusinessLayerException<BR>
     * 　@tag: BUSINESS_ERROR_01691<BR>
     * <BR>
     *    の場合、例外をスローする。<BR>
     * <BR>
     * ６）受付日時<BR>
     * <BR>
     *    this.受付日時（自） != null and<BR>
     *    this.受付日時（至） != null and<BR>
     *    this.受付日時（自） > this.受付日時（至）<BR>
     * <BR>
     *    の場合、例外をスローする。<BR>
     * 　@class: WEB3BusinessLayerException<BR>
     * 　@tag: BUSINESS_ERROR_01822<BR>
     * <BR>
     * ７）銘柄コード<BR>
     * <BR>
     *  　@this.銘柄コード != null and<BR>
     *  　@this.銘柄コード != 半角数字<BR>
     * <BR>
     *  　@の場合、例外をスローする。<BR>
     *  　@class: WEB3BusinessLayerException<BR>
     *  　@tag: BUSINESS_ERROR_01067<BR>
     * <BR>
     * ８）扱者コード<BR>
     * <BR>
     *  　@this.扱者コード != null and<BR>
     *  　@this.扱者コード != 半角数字<BR>
     *  　@class: WEB3BusinessLayerException<BR>
     *  　@tag: BUSINESS_ERROR_02782<BR>
     * <BR>
     *    の場合、例外をスローする。<BR>
     * <BR>
     * ９）ソートキー<BR>
     * <BR>
     * ９－１） <BR>
     * <BR>
     *    this.ソートキー == null or <BR>
     * 　@class: WEB3BusinessLayerException<BR>
     * 　@tag: BUSINESS_ERROR_00231<BR>
     *    this.ソートキー.length() == 0 <BR>
     * 　@class: WEB3BusinessLayerException<BR>
     * 　@tag: BUSINESS_ERROR_00232<BR>
     * <BR>
     *    の場合、例外をスローする。 <BR>
     * <BR>
     * ９－２）ソートキーの各要素について<BR>
     * <BR>
     *   キー項目 != (’部店コード’ or ’識別コード’ or ’顧客コード’ or ’受付日時’) or<BR>
     * 　@class: WEB3BusinessLayerException<BR>
     * 　@tag: BUSINESS_ERROR_00086<BR>
     *   昇順/降順 != ('A' or 'D')<BR>
     * 　@class: WEB3BusinessLayerException<BR>
     * 　@tag: BUSINESS_ERROR_00088<BR>
     * <BR>
     *   の場合、例外をスローする。<BR>
     * @@roseuid 41B93F2B00C6
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
        // ２－１）<BR>
        //  this.部店コード == nullの場合、例外をスローする。<BR>
        //      class: WEB3BusinessLayerException<BR>
        //　@    tag: BUSINESS_ERROR_00833<BR>
        if (this.branchCode == null)
        {
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_00833, 
                getClass().getName() + "validate",
                "部店コードが未指定です。");
        }

        //  this.部店コード.length() == 0の場合、例外をスローする。<BR>
        //    　@class: WEB3BusinessLayerException<BR>
        //    　@tag: BUSINESS_ERROR_01757<BR>
        if (this.branchCode.length == 0)
        {
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_01757, 
                getClass().getName() + "validate",
                "部店コードの要素数が０です。");
        }

        int l_intBranchCodeLth = this.branchCode.length;
        for (int i=0; i<l_intBranchCodeLth; i++)
        {
            // ２－２）部店コードの各要素についてチェックする。<BR>
            //  部店コード != 半角数字 の場合、例外をスローする。<BR>
            //      class: WEB3BusinessLayerException<BR>
            //      tag: BUSINESS_ERROR_01729<BR>
            if (!WEB3StringTypeUtility.isDigit(this.branchCode[i]))
            {
                throw new WEB3BusinessLayerException(
                    WEB3ErrorCatalog.BUSINESS_ERROR_01729, 
                    getClass().getName() + "validate",
                    "部店コードの値が数値以外の値です。");
            }

            //  部店コード.length() != 3の場合、例外をスローする。<BR>
            //      class: WEB3BusinessLayerException<BR>
            //      tag: BUSINESS_ERROR_00834<BR>
            if (this.branchCode[i].length() != 3)
            {
                throw new WEB3BusinessLayerException(
                    WEB3ErrorCatalog.BUSINESS_ERROR_00834, 
                    getClass().getName() + "validate",
                    "部店コードのサイズが不正です。");
            }
        }

        //３）識別コード<BR>
        //  this.識別コード != nullかつ<BR>
        //  this.識別コード != 半角数字 の場合、例外をスローする。<BR>
        //      class: WEB3BusinessLayerException<BR>
        //      tag: BUSINESS_ERROR_01820<BR>
        if (this.requestNumber != null
            && !WEB3StringTypeUtility.isDigit(this.requestNumber))
        {
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_01820, 
                getClass().getName() + "validate",
                "識別コードの値が半角数字以外の値です。");
        }

        //  this.識別コード != nullかつ<BR>
        //  this.識別コード.length() > 13 の場合、例外をスローする。<BR>
        //      class: WEB3BusinessLayerException<BR>
        //      tag: BUSINESS_ERROR_01821<BR>
        if (this.requestNumber != null
            && this.requestNumber.length() > 13)
        {
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_01821, 
                getClass().getName() + "validate",
                "識別コードのサイズが不正です。");
        }

        //４）顧客コード<BR>
        //  this.顧客コード != nullかつ<BR>
        //  this.顧客コード != 半角数字 の場合、例外をスローする。<BR>
        //      class: WEB3BusinessLayerException<BR>
        //      tag: BUSINESS_ERROR_01043<BR>
        if (this.accountCode != null
            && !WEB3StringTypeUtility.isDigit(this.accountCode))
        {
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_01043, 
                getClass().getName() + "validate",
                "顧客コードの値が数字以外の値です。");
        }

        //  this.顧客コード != nullかつ<BR>
        //  this.顧客コード.length() > 6 の場合、例外をスローする。<BR>
        //      class: WEB3BusinessLayerException<BR>
        //      tag: BUSINESS_ERROR_00836<BR>
        if (this.accountCode != null
            && this.accountCode.length() > 6)
        {
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_00836, 
                getClass().getName() + "validate",
                "顧客コードのサイズが不正です。");
        }

        //５）顧客名<BR>
        //  this.顧客名 != nullかつ<BR>
        //  this.顧客名 != 全角文字の場合、例外をスローする。<BR>
        //    　@class: WEB3BusinessLayerException<BR>
        //    　@tag: BUSINESS_ERROR_01691<BR>
        if (this.accountName != null
            && !WEB3StringTypeUtility.isMulti(this.accountName))
        {
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_01691, 
                getClass().getName() + "validate",
                "顧客名が全角文字ではありません。");
        }

        //６）受付日時<BR>
        //  this.受付日時（自） != null and<BR>
        //  this.受付日時（至） != null and<BR>
        //  this.受付日時（自） > this.受付日時（至）<BR>
        //  の場合、例外をスローする。<BR>
        //      class: WEB3BusinessLayerException<BR>
        //      tag: BUSINESS_ERROR_01822<BR>
        if (this.receptionDateFrom != null
            && this.receptionDateTo != null
            && WEB3DateUtility.compare(this.receptionDateFrom, this.receptionDateTo) > 0)
        {
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_01822, 
                getClass().getName() + "validate",
                "受付日時（自）は受付日時（至）より大きいです。");
        }

        // ７）銘柄コード
        // this.銘柄コード != null and this.銘柄コード != 半角数字
        // の場合、例外をスローする。
        if (this.productCode != null && !WEB3StringTypeUtility.isDigit(this.productCode))
        {
            log.debug("銘柄コードの入力が不正です。");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_01067,
                this.getClass().getName() + STR_METHOD_NAME,
                "銘柄コードの入力が不正です。");
        }
        
        // ８）扱者コード
        // this.扱者コード != null and this.扱者コード != 半角数字
        // の場合、例外をスローする。
        if (this.traderCode != null && !WEB3StringTypeUtility.isDigit(this.traderCode))
        {
            log.debug("扱者コードエラー。");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_02782,
                this.getClass().getName() + STR_METHOD_NAME,
                "扱者コードエラー。");
        }

        //９）ソートキー<BR>
        // ９－１） <BR>
        //  this.ソートキー == null の場合、例外をスローする。<BR>
        //    　@class: WEB3BusinessLayerException<BR>
        //      tag: BUSINESS_ERROR_00231<BR>
        if (this.sortKeys == null)
        {
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_00231, 
                getClass().getName() + "validate",
                "ソートキーが未指定です。");
        }

        //  this.ソートキー.length() == 0 の場合、例外をスローする。<BR>
        //      class: WEB3BusinessLayerException<BR>
        //      tag: BUSINESS_ERROR_00232<BR>
        if (this.sortKeys.length == 0)
        {
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_00232, 
                getClass().getName() + "validate",
                "ソートキーの要素数が０です。");
        }

        int l_intSortKeysLth = this.sortKeys.length;
        for (int i=0; i<l_intSortKeysLth; i++)
        {
            // ９－２）ソートキーの各要素について<BR>
            //  キー項目 != (’部店コード’ or ’識別コード’ or ’顧客コード’ or ’受付日時’) or<BR>
            //  の場合、例外をスローする。<BR>
            //      class: WEB3BusinessLayerException<BR>
            //      tag: BUSINESS_ERROR_00086<BR>
            if (!WEB3InformKeyItemDef.BRANCH_CODE.equals(this.sortKeys[i].keyItem)
                && !WEB3InformKeyItemDef.REQUEST_NUMBER.equals(this.sortKeys[i].keyItem)
                && !WEB3InformKeyItemDef.ACCOUNT_CODE.equals(this.sortKeys[i].keyItem)
                && !WEB3InformKeyItemDef.ACCEPT_TIME.equals(this.sortKeys[i].keyItem))
            {
                throw new WEB3BusinessLayerException(
                    WEB3ErrorCatalog.BUSINESS_ERROR_00086, 
                    getClass().getName() + "validate",
                    "ソートキーのキー項目の値が存在しないコード値です。");
            }

            //  昇順/降順 != ('A' or 'D')の場合、例外をスローする。<BR>
            //      class: WEB3BusinessLayerException<BR>
            //      tag: BUSINESS_ERROR_00088<BR>
            if (!WEB3AscDescDef.ASC.equals(this.sortKeys[i].ascDesc)
                && !WEB3AscDescDef.DESC.equals(this.sortKeys[i].ascDesc))
            {
                throw new WEB3BusinessLayerException(
                    WEB3ErrorCatalog.BUSINESS_ERROR_00088, 
                    getClass().getName() + "validate",
                    "昇順／降順が”A：昇順”、”D：降順”以外の値です。");
            }
        }

        log.exiting(STR_METHOD_NAME);
    }
}
@
