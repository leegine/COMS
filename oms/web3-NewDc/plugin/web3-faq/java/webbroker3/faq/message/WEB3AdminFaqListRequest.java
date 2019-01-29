head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.14.05.46.05;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	8884d7d97833a75;
filename	WEB3AdminFaqListRequest.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : 管理者問合せ管理お問合せ一覧リクエスト(WEB3AdminFaqListRequest)
Author Name      : Daiwa Institute of Research
Revesion History : 2004/12/23 張宝楠 (中訊) 新規作成
*/

package webbroker3.faq.message;

import java.util.Date;

import webbroker3.common.WEB3BaseException;
import webbroker3.common.WEB3BusinessLayerException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.common.message.WEB3GenRequest;
import webbroker3.common.message.WEB3GenResponse;
import webbroker3.faq.define.WEB3FaqKeyItemDef;
import webbroker3.util.WEB3DateUtility;
import webbroker3.util.WEB3LogUtility;
import webbroker3.util.WEB3StringTypeUtility;


/**
 * (管理者問合せ管理お問合せ一覧リクエスト)<BR>
 * 管理者問合せ管理お問合せ一覧リクエスト<BR>
 * 
 * @@author 張宝楠(中訊)
 * @@version 1.0
 */
public class WEB3AdminFaqListRequest extends WEB3GenRequest
{
    /**
     * ログ出力ユーティリティ。
     */
    private static WEB3LogUtility log =
        WEB3LogUtility.getInstance(WEB3AdminFaqListRequest.class);
        
    /**
     * PTYPE<BR>
     */
    public static final String PTYPE = "admin_faq_list";

    /**
     * SerialVersionUID<BR>
     */
    public static final long serialVersionUID = 200412171303L;

    /**
     * (問合せコード)<BR>
     * 問合せコード<BR>
     */
    public String faqCode;

    /**
     * (部店コード)<BR>
     * 部店コード配列<BR>
     * <BR>
     * ※ "000"は部店なしの指定<BR>
     */
    public String[] branchCode;

    /**
     * (顧客コード)<BR>
     * 顧客コード<BR>
     */
    public String accountCode;

    /**
     * (顧客名)<BR>
     * 顧客名<BR>
     */
    public String accountName;

    /**
     * (件名)<BR>
     * 件名<BR>
     */
    public String subject;

    /**
     * (問合せ日（自）)<BR>
     * 問合せ日（自）<BR>
     */
    public Date faqDateFrom;

    /**
     * (問合せ日（至）)<BR>
     * 問合せ日（至）<BR>
     */
    public Date faqDateTo;

    /**
     * (機@能ＩＤ)<BR>
     * 機@能ＩＤの配列<BR>
     * <BR>
     * ※ 各社画面で任意に使用するコード。<BR>
     */
    public String[] transactionId;

    /**
     * (機@能カテゴリコード)<BR>
     * 機@能カテゴリコードの配列<BR>
     */
    public String[] transactionCategoryCode;

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
     * (ソートキー)<BR>
     * ソートキー<BR>
     */
    public WEB3FaqSortKey[] sortKeys;

    /**
     * @@roseuid 41C25C0800BB
     */
    public WEB3AdminFaqListRequest()
    {

    }

    /**
     * 当該リクエストに対応するレスポンスオブジェクトを返す。<BR>
     *<BR>
     * @@return レスポンスオブジェクト
     */
    public WEB3GenResponse createResponse()
    {
        return new WEB3AdminFaqListResponse(this);
    }

    /**
     * リクエストデータの整合性をチェックする。<BR>
     * <BR>
     * １）　@部店コード[]のチェック<BR>
     * 　@１－１）　@未入力の場合、例外をスローする。<BR>
     *          class: WEB3BusinessLayerException<BR>
     *          tag:   BUSINESS_ERROR_00833<BR>
     * <BR>
     * ２）　@問合せ日（自），問合せ日（至）のチェック<BR>
     * 　@※（自），（至）の両方に入力がある場合のみ<BR>
     * 　@２－１）　@（自） > （至）であれば、例外をスローする。<BR>
     *          class: WEB3BusinessLayerException<BR>
     *          tag:   BUSINESS_ERROR_01686<BR>
     * <BR>
     * ３）　@機@能ＩＤ[]のチェック<BR>
     * 　@３－１）　@未入力の場合、例外をスローする。<BR>
     *          class: WEB3BusinessLayerException<BR>
     *          tag:   BUSINESS_ERROR_01687<BR>
     * <BR>
     * ４）　@機@能カテゴリコード[]のチェック<BR>
     * 　@４－１）　@未入力の場合、例外をスローする。<BR>
     *          class: WEB3BusinessLayerException<BR>
     *          tag:   BUSINESS_ERROR_01220<BR>
     * <BR>
     * ５）　@要求ページ番号チェック <BR>
     * 　@５－１）　@未入力の場合、 要求ページ番号に”１”をセットする。 <BR>
     * 　@５－２）　@数字以外の文字が含まれる場合、例外をスローする。 <BR>
     *          class: WEB3BusinessLayerException<BR>
     *          tag:   BUSINESS_ERROR_00090<BR>
     * 　@５－３）　@マイナス値の場合、例外をスローする。<BR>
     *          class: WEB3BusinessLayerException<BR>
     *          tag:   BUSINESS_ERROR_00616<BR>
     * <BR>
     * ６）　@ページ内表示行数チェック <BR>
     * 　@６－１）　@未入力の場合、例外をスローする。 <BR>
     *          class: WEB3BusinessLayerException<BR>
     *          tag:   BUSINESS_ERROR_00091<BR>
     * 　@６－２）　@数字以外の文字が含まれる場合、例外をスローする。 <BR>
     *          class: WEB3BusinessLayerException<BR>
     *          tag:   BUSINESS_ERROR_00092<BR>
     * 　@６－３）　@マイナス値の場合、例外をスローする。<BR>
     *          class: WEB3BusinessLayerException<BR>
     *          tag:   BUSINESS_ERROR_00617<BR>
     * <BR>
     * ７）　@ソートキーのチェック <BR>
     * 　@７－１）　@ソートキーが未入力lの場合、例外をスローする。 <BR>
     *          class: WEB3BusinessLayerException<BR>
     *          tag:   BUSINESS_ERROR_00231<BR>
     * 　@７－２）　@（ソートキーの要素数 == 0）の場合、 例外をスローする。 <BR>
     *          class: WEB3BusinessLayerException<BR>
     *          tag:   BUSINESS_ERROR_00232<BR>
     * 　@７－３）　@ソートキーの要素数分、下記のチェックを繰り返して行う。 <BR>
     * 　@　@　@７－３－１）　@ソートキー.validate()をコールする。 <BR>
     * 　@　@　@７－３－２）　@ソートキー.キー項目が下記の項目名以外の場合、 <BR>
     * 例外をスローする。 <BR>
     *          class: WEB3BusinessLayerException<BR>
     *          tag:   BUSINESS_ERROR_00086<BR>
     * 　@　@　@　@ 問合せ情報.問合せコード<BR>
     * 　@　@　@　@ 問合せ情報.部店コード <BR>
     * 　@　@　@　@ 問合せ情報.顧客コード <BR>
     * 　@　@　@　@ 問合せ情報.問合せ日時 <BR>
     * 　@　@　@　@ 問合せ情報.機@能ＩＤ<BR>
     * @@throws WEB3BaseException
     * @@roseuid 41A6FB4301B2
     */
    public void validate() throws WEB3BaseException
    {
        final String STR_METHOD_NAME = " validate()";
        log.entering(STR_METHOD_NAME);
        
        //１）　@部店コード[]のチェック
        //１－１）　@未入力の場合、例外をスローする。
        
        if (this.branchCode == null || this.branchCode.length == 0)
        {
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_00833, 
                this.getClass().getName() + STR_METHOD_NAME,
                "部店コードが未指定です。");  
        }
        
        //２）　@問合せ日（自），問合せ日（至）のチェック
        //※（自），（至）の両方に入力がある場合のみ
        //２－１）　@（自） > （至）であれば、例外をスローする。
        if (this.faqDateFrom != null && this.faqDateTo != null)
        {
            if (WEB3DateUtility.compareToDay(this.faqDateFrom, this.faqDateTo) > 0)
            {
                throw new WEB3BusinessLayerException(
                    WEB3ErrorCatalog.BUSINESS_ERROR_01686, 
                    this.getClass().getName() + STR_METHOD_NAME,
                    "問合せ日（自）は問合せ日（至）より大きいです。" +
                    " [問合せ日（自）] = " + this.faqDateFrom + 
                    " [問合せ日（至）] = " + this.faqDateTo);  
            }
        }
        
        //３）　@機@能ＩＤ[]のチェック
        //３－１）　@未入力の場合、例外をスローする。
        if (this.transactionId == null 
            || this.transactionId.length == 0)
        {
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_01687, 
                this.getClass().getName() + STR_METHOD_NAME,
                "機@能ＩＤが未指定です。");  
        }
        
        //４）　@機@能カテゴリコード[]のチェック
        //４－１）　@未入力の場合、例外をスローする。
        if (this.transactionCategoryCode == null 
            || this.transactionCategoryCode.length == 0)
        {
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_01220, 
                this.getClass().getName() + STR_METHOD_NAME,
                "機@能カテゴリコードが未指定です。");  
        }
        
        //５）　@要求ページ番号チェック
        //５－１）　@未入力の場合、 要求ページ番号に”１”をセットする。
        if (WEB3StringTypeUtility.isEmpty(this.pageIndex))
        {
            this.pageIndex = "1";
        }
        
        //５－２）　@数字以外の文字が含まれる場合、例外をスローする。
        if (!WEB3StringTypeUtility.isInteger(this.pageIndex))
        {
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_00090,
                this.getClass().getName() + STR_METHOD_NAME,
                "要求ページ番号が数字以外の値です。" +
                " [要求ページ番号] = " + this.pageIndex);
        }
        
        //５－３）　@マイナス値の場合、例外をスローする。
        int l_lngPageIndex = Integer.parseInt(this.pageIndex);
        if (l_lngPageIndex <= 0)
        {
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_00616,
                this.getClass().getName() + STR_METHOD_NAME,
                "要求ページ番号の値が0以下です。" +
                " [要求ページ番号] = " + this.pageIndex);
        }
        
        //６）　@ページ内表示行数チェック 
        //６－１）　@未入力の場合、例外をスローする。
        if (WEB3StringTypeUtility.isEmpty(this.pageSize))
        {
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_00091,
                this.getClass().getName() + STR_METHOD_NAME,
                "ページ内表示行数の入力が不正です。" +
                " [ページ内表示行数] = " + this.pageSize);
        }
        
        //６－２）　@数字以外の文字が含まれる場合、例外をスローする。
        if (!WEB3StringTypeUtility.isInteger(this.pageSize))
        {
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_00092,
                this.getClass().getName() + STR_METHOD_NAME,
                "ページ内表示行数が数字以外の値です。" +
                " [ページ内表示行数] = " + this.pageSize);
        }
        
        //６－３）　@マイナス値の場合、例外をスローする。
        int l_lngPageSize = Integer.parseInt(this.pageSize);
        if (l_lngPageSize <= 0)
        {
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_00617,
                this.getClass().getName() + STR_METHOD_NAME,
                "ページ内表示行数の値が0以下です。" +
                " [ページ内表示行数] = " + this.pageSize);
        }
        
        //７）　@ソートキーのチェック 
        //７－１）　@ソートキーが未入力lの場合、例外をスローする。
        if (this.sortKeys == null)
        {
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_00231,
                this.getClass().getName() + STR_METHOD_NAME,
                "ソートキーが未指定です。");
        }
        
        //７－２）　@（ソートキーの要素数 == 0）の場合、 例外をスローする。
        if (this.sortKeys.length == 0)
        {
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_00232,
                this.getClass().getName() + STR_METHOD_NAME,
                "ソートキーの要素数が０です。");
        }
        
        //７－３）　@ソートキーの要素数分、下記のチェックを繰り返して行う。
        for (int i = 0; i < this.sortKeys.length; i++)
        {
            //７－３－１）　@ソートキー.validate()をコールする。
            this.sortKeys[i].validate();

            //７－３－２）　@ソートキー.キー項目が下記の項目名以外の場合、例外をスローする。
            //問合せ情報.問合せコード
            //問合せ情報.部店コード
            //問合せ情報.顧客コード
            //問合せ情報.問合せ日時
            //問合せ情報.機@能ＩＤ
            if (!WEB3FaqKeyItemDef.FAQ_NUMBER.equals(this.sortKeys[i].keyItem)
                && !WEB3FaqKeyItemDef.BRANCH_CODE.equals(this.sortKeys[i].keyItem)
                && !WEB3FaqKeyItemDef.ACCOUNT_CODE.equals(this.sortKeys[i].keyItem)
                && !WEB3FaqKeyItemDef.FAQ_DATETIME.equals(this.sortKeys[i].keyItem)
                && !WEB3FaqKeyItemDef.TRANSACTION_ID.equals(this.sortKeys[i].keyItem))
            {
                throw new WEB3BusinessLayerException(
                    WEB3ErrorCatalog.BUSINESS_ERROR_00086,
                    this.getClass().getName() + STR_METHOD_NAME,
                    "ソートキーのキー項目が下記の項目名以外。" + 
                    " [問合せ情報.問合せコード]、[問合せ情報.部店コード]、[問合せ情報.顧客コード]" + 
                    " [問合せ情報.問合せ日時]、[問合せ情報.機@能ＩＤ]" +
                    " [ソートキー.キー項目] = " + this.sortKeys[i].keyItem);
            }
        }
        
        log.exiting(STR_METHOD_NAME);
    }
}
@
