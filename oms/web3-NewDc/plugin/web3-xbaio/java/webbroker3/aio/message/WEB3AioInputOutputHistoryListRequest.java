head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.16.05.13.07;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	8f04d80403d696d;
filename	WEB3AioInputOutputHistoryListRequest.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : 入出庫履歴リクエスト(WEB3AioInputOutputHistoryListRequest.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2005/1/18 艾興 (中訊) 新規作成
*/
package webbroker3.aio.message;

import java.util.Date;

import webbroker3.aio.define.WEB3AioInputOutputSortkeyItemDef;
import webbroker3.aio.define.WEB3InformProductGroupDef;
import webbroker3.common.WEB3BaseException;
import webbroker3.common.WEB3BusinessLayerException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.common.define.WEB3AscDescDef;
import webbroker3.common.message.WEB3GenRequest;
import webbroker3.common.message.WEB3GenResponse;
import webbroker3.util.WEB3DateUtility;
import webbroker3.util.WEB3LogUtility;
import webbroker3.util.WEB3StringTypeUtility;

/**
 * (入出庫履歴リクエスト)<BR>
 * 入出庫履歴リクエストクラス
 * @@author 艾興
 * @@version 1.0
 */
public class WEB3AioInputOutputHistoryListRequest extends WEB3GenRequest
{
    private static WEB3LogUtility log =
        WEB3LogUtility.getInstance(WEB3AioInputOutputHistoryListRequest.class);
    /**
     * PTYPE<BR>
     */
    public static final String PTYPE = "aio_inputOutputHistoryList";

    /**
     * SerialVersionUID<BR>
     */
    public static final long serialVersionUID = 200501271304L;

    /**
     * (表示期間（自）)<BR>
     * 表示期間（自）<BR>
     * <BR>
     * ※メニュー画面からの最初のリクエストの時は、null
     */
    public Date displayStartDate;

    /**
     * (表示期間（至）)<BR>
     * 表示期間（至）<BR>
     * <BR>
     * ※メニュー画面からの最初のリクエストの時は、null
     */
    public Date displayEndDate;

    /**
     * (商品グループ)<BR>
     * 商品グループ<BR>
     * <BR>
     * Z： 全商品<BR>
     * A： 株式<BR>
     * B： 外国株式<BR>
     * C： 投信<BR>
     * D： 債券<BR>
     */
    public String productGroup;

    /**
     * (銘柄コード)<BR>
     * 銘柄コード
     */
    public String productCode;

    /**
     * (出入グループ)<BR>
     * 出入グループ<BR>
     * <BR>
     * Z： 入出庫<BR>
     * A： 出庫<BR>
     * B： 入庫
     */
    public String inputOutputGroup;

    /**
     * (要求ページ番号)<BR>
     * 要求ページ番号
     */
    public String pageIndex;

    /**
     * (ページ内表示行数)<BR>
     * ページ内表示行数
     */
    public String pageSize;

    /**
     * (ソートキー)<BR>
     * ソートキー
     */
    public WEB3AioSortKeyUnit[] sortKeys;

    /**
     * @@roseuid 41EC84F800AB
     */
    public WEB3AioInputOutputHistoryListRequest()
    {

    }

    /**
     * リクエストデータの整合性をチェックする。<BR>
     * <BR>
     * １）表示期間（自）と表示期間（至）の整合性<BR>
     * <BR>
     * １－１）<BR>
     *   this.表示期間（自） != null and<BR>
     *   this.表示期間（至） != null and<BR>
     *   this.表示期間（自） > this.表示期間（至）<BR>
     * <BR>
     *   の場合、例外をスローする。<BR>
     *       class: WEB3BusinessLayerException<BR>
     *       tag:   BUSINESS_ERROR_01051 <BR>
     * <BR>
     * １－２）<BR>
     *   ( this.表示期間（自） != null and this.表示期間（至） == null ) or<BR>
     *   ( this.表示期間（自） == null and this.表示期間（至） != null )<BR>
     * <BR>
     *   の場合、例外をスローする。<BR>
     *       class: WEB3BusinessLayerException<BR>
     *       tag:   BUSINESS_ERROR_01795 <BR>
     * <BR>
     * ２）銘柄コード<BR>
     * <BR>
     * ２－１）<BR>
     *   this.商品グループ != ('Z'（全商品） or 'A'（株式）） and<BR>
     *   this.銘柄コード != null<BR>
     * <BR>
     *   の場合、例外をスローする。<BR>
     *       class: WEB3BusinessLayerException<BR>
     *       tag:   BUSINESS_ERROR_01814 <BR>
     * <BR>
     * ２－２）<BR>
     *   this.銘柄コード.length() != (4 or 5)<BR>
     * <BR>
     *   の場合、例外をスローする。<BR>
     *       class: WEB3BusinessLayerException<BR>
     *       tag:   BUSINESS_ERROR_00439 <BR>
     * <BR>
     * ２－３）<BR>
     *   this.銘柄コード != 数字<BR>
     * <BR>
     *   の場合、例外をスローする。<BR>
     *       class: WEB3BusinessLayerException<BR>
     *       tag:   BUSINESS_ERROR_00815<BR>
     * <BR>
     * ３）要求ページ番号<BR>
     * <BR>
     *   this.要求ページ番号 == null or<BR>
     *       class: WEB3BusinessLayerException<BR>
     *       tag:   BUSINESS_ERROR_00089<BR>
     *   this.要求ページ番号 != 数字 or<BR>
     *       class: WEB3BusinessLayerException<BR>
     *       tag:   BUSINESS_ERROR_00090<BR>
     *   this.要求ページ番号 <= 0<BR>
     *       class: WEB3BusinessLayerException<BR>
     *       tag:   BUSINESS_ERROR_00616<BR>
     * <BR>
     *   の場合、例外をスローする。<BR>
     * <BR>
     * ４）ページ内表示行数<BR>
     * <BR>
     *   this.ページ内表示行数 == null or<BR>
     *       class: WEB3BusinessLayerException<BR>
     *       tag:   BUSINESS_ERROR_00091<BR>
     *   this.ページ内表示行数 != 数字 or<BR>
     *       class: WEB3BusinessLayerException<BR>
     *       tag:   BUSINESS_ERROR_00092<BR>
     *   this.ページ内表示行数 <= 0<BR>
     *       class: WEB3BusinessLayerException<BR>
     *       tag:   BUSINESS_ERROR_00617<BR>
     * <BR>
     *   の場合、例外をスローする。<BR>
     * <BR>
     * ５）ソートキー<BR>
     * <BR>
     * ５－１）<BR>
     *       this.ソートキー == null　@or<BR>
     *       class: WEB3BusinessLayerException<BR>
     *       tag:   BUSINESS_ERROR_00231<BR>
     *   　@　@this.ソートキーの要素数 == 0 <BR>
     *       class: WEB3BusinessLayerException<BR>
     *       tag:   BUSINESS_ERROR_00232<BR>
     * <BR>
     *   の場合、例外をスローする。<BR>
     * 
     * <BR>
     * ５－２）<BR>
     *   配列の各要素について<BR>
     * <BR>
     *   キー項目 != (’受渡日’ or ’商品グループ’ or ’銘柄コード’) or<BR>
     *   昇順/降順 != ('A' or 'D')<BR>
     * <BR>
     *   の場合、例外をスローする。<BR>
     *       class: WEB3BusinessLayerException<BR>
     *       tag:   BUSINESS_ERROR_00086
     * @@roseuid 41B6D9FA02D5
     */
    public void validate() throws WEB3BaseException
    {
        final String STR_METHOD_NAME = "validate()";
        log.entering(STR_METHOD_NAME);
        //１）表示期間（自）と表示期間（至）の整合性
        //１－１）this.表示期間（自） != null and
        //this.表示期間（至） != null and
        //this.表示期間（自） > this.表示期間（至）
        //の場合、例外をスローする。
        //class: WEB3BusinessLayerException
        //tag:   BUSINESS_ERROR_01051 
        if ((this.displayStartDate != null) && (this.displayEndDate != null) 
            && (WEB3DateUtility.compareToDay(this.displayStartDate,this.displayEndDate) > 0))
        {
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_01051, 
                this.getClass().getName() + STR_METHOD_NAME,
                "表示期間（自）は表示期間（至）より大きいです。");  
        }
        //１－２）
        //( this.表示期間（自） != null and this.表示期間（至） == null ) or
        //( this.表示期間（自） == null and this.表示期間（至） != null )
        //の場合、例外をスローする。
        //class: WEB3BusinessLayerException
        //tag:   BUSINESS_ERROR_01795 
        if (((this.displayStartDate != null) && (this.displayEndDate == null)) 
            || ((this.displayStartDate == null) && (this.displayEndDate != null)))
        {            
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_01795,
                this.getClass().getName() + STR_METHOD_NAME,
                "表示期間（自）と表示期間（至）はいずれかが入力される時、残り一方も入力されるはずです。");
        }
        
        //２）銘柄コード
        //２－１）
        //this.商品グループ != ('Z'（全商品） or 'A'（株式）） and
        //this.銘柄コード != null
        //の場合、例外をスローする。
        //class: WEB3BusinessLayerException
        //tag:   BUSINESS_ERROR_01814 
        if ((!WEB3InformProductGroupDef.ALL_PRODUCT.equals(this.productGroup) && 
            !WEB3InformProductGroupDef.EQUITY.equals(this.productGroup)) && this.productCode != null)
        {
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_01814,
                this.getClass().getName() + STR_METHOD_NAME,
                "商品で”全商品”、”株式”以外が選択されている場合は、銘柄コードは指定できません。");
        }
        //２－２）
        //this.銘柄コード.length() != (4 or 5)
        //の場合、例外をスローする。
        //class: WEB3BusinessLayerException
        //tag:   BUSINESS_ERROR_00439 
        if ((this.productCode != null) && (this.productCode.length() !=4) && (this.productCode.length() != 5))
        {
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_00439,
                this.getClass().getName() + STR_METHOD_NAME,
                "銘柄コードのサイズが不正です。");
        }
        //２－３）
        //this.銘柄コード != 数字
        //の場合、例外をスローする。
        //class: WEB3BusinessLayerException
        //tag:   BUSINESS_ERROR_00815
        if ((this.productCode != null) && !WEB3StringTypeUtility.isInteger(this.productCode))
        {
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_00815,
                this.getClass().getName() + STR_METHOD_NAME,
                "銘柄コードが数字以外の値です。");
        }
        //３）要求ページ番号
        //  this.要求ページ番号 == null or
        //       class: WEB3BusinessLayerException
        //      tag:   BUSINESS_ERROR_00089
        if (this.pageIndex == null)
        {
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_00089,
                this.getClass().getName() + STR_METHOD_NAME,
                "要求ページ番号が未指定です。");
        }
        //  this.要求ページ番号 != 数字 or
        //       class: WEB3BusinessLayerException
        //       tag:   BUSINESS_ERROR_00090
        if (!WEB3StringTypeUtility.isInteger(this.pageIndex))
        {
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_00090,
                this.getClass().getName() + STR_METHOD_NAME,
                "要求ページ番号が数字以外の値です。");
        }
        //   this.要求ページ番号 <= 0
        //      class: WEB3BusinessLayerException
        //      tag:   BUSINESS_ERROR_00616
        //  の場合、例外をスローする。
        if (Integer.parseInt(this.pageIndex) <= 0)
        {
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_00616,
                this.getClass().getName() + STR_METHOD_NAME,
                "要求ページ番号の値が0以下です。");
        }
        //４）ページ内表示行数
        // this.ページ内表示行数 == null or
        //   class: WEB3BusinessLayerException
        //    tag:   BUSINESS_ERROR_00091
        if (this.pageSize == null)
        {
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_00091,
                this.getClass().getName() + STR_METHOD_NAME,
                "ページ内表示行数の入力が不正です。");
        }
        // this.ページ内表示行数 != 数字 or
        //       class: WEB3BusinessLayerException
        //       tag:   BUSINESS_ERROR_00092
        if (!WEB3StringTypeUtility.isInteger(this.pageSize))
        {
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_00092,
                this.getClass().getName() + STR_METHOD_NAME,
                "ページ内表示行数が数字以外の値です。");
        }
        //this.ページ内表示行数 <= 0
        //       class: WEB3BusinessLayerException
        //      tag:   BUSINESS_ERROR_00617
        //  の場合、例外をスローする。
        if (Integer.parseInt(this.pageSize) <= 0)
        {
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_00617,
                this.getClass().getName() + STR_METHOD_NAME,
                "ページ内表示行数の値が0以下です。");
        }
        // ５）ソートキー
        // ５－１）
        //   this.ソートキー == null
        //       class: WEB3BusinessLayerException
        //      tag:   BUSINESS_ERROR_00231
        //  の場合、例外をスローする。
        if (this.sortKeys == null)
        {
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_00231,
                this.getClass().getName() + STR_METHOD_NAME,
                "ソートキーが未指定です。");            
        }

        //  this.ソートキーの要素数 == 0 or
        //       class: WEB3BusinessLayerException
        //      tag:   BUSINESS_ERROR_00232
        if (this.sortKeys.length == 0)
        {
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_00232,
                this.getClass().getName() + STR_METHOD_NAME,
                "ソートキーの要素数が０です。");
        }


        //５－２）配列の各要素について キー項目
        //!= (’ 受渡日 ’ or ’ 商品グループ ’ or ’ 銘柄コード ’)or  昇順 / 降順
        //!= ('A' or 'D')
        // の場合 、 例外をスローする 。
        //class : WEB3BusinessLayerException
        //tag : BUSINESS_ERROR_00086 
        int l_length = this.sortKeys.length;
        for (int i = 0; i < l_length; i++)
        {
            if ((!WEB3AioInputOutputSortkeyItemDef.DELIVERY_DATE.equals(this.sortKeys[i].keyItem)
                && !WEB3AioInputOutputSortkeyItemDef.PRODUCT_CODE.equals(this.sortKeys[i].keyItem)
                && !WEB3AioInputOutputSortkeyItemDef.PRODUCT_GROUP.equals(this.sortKeys[i].keyItem)))
            {
                throw new WEB3BusinessLayerException(
                    WEB3ErrorCatalog.BUSINESS_ERROR_00086,
                    this.getClass().getName() + STR_METHOD_NAME,
                    "ソートキーのキー項目の値が存在しないコード値です。");                        
            }
            if (!WEB3AscDescDef.ASC.equals(this.sortKeys[i].ascDesc) && !WEB3AscDescDef.DESC.equals(this.sortKeys[i].ascDesc))
            {
                throw new WEB3BusinessLayerException(
                    WEB3ErrorCatalog.BUSINESS_ERROR_00088,
                    this.getClass().getName() + STR_METHOD_NAME,
                    "昇順／降順が”A：昇順”、”D：降順”以外の値です。"); 
            }
        }

        log.exiting(STR_METHOD_NAME);
    }

    /**
     * @@return WEB3GenResponse
     * @@roseuid 41EC84F80177
     */
    public WEB3GenResponse createResponse()
    {
        return new WEB3AioInputOutputHistoryListResponse(this);
    }
}
@
