head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.16.03.26.03;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	6004d80209d0226;
filename	WEB3FeqProductListRequest.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : 外国株式銘柄一覧リクエスト(WEB3FeqProductListRequest)
Author Name      : Daiwa Institute of Research
Revesion History : 2005/07/14 黄建 (中訊) 新規作成 
                 : 2005/08/01 郭英(中訊) レビュー  
*/

package webbroker3.feq.message;

import webbroker3.common.WEB3BaseException;
import webbroker3.common.WEB3BusinessLayerException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.common.message.WEB3GenRequest;
import webbroker3.common.message.WEB3GenResponse;
import webbroker3.feq.define.WEB3FeqSortKeyItemNameDef;
import webbroker3.util.WEB3LogUtility;
import webbroker3.util.WEB3StringTypeUtility;

/**
 * (外国株式銘柄一覧リクエスト)<BR>
 * 外国株式銘柄一覧リクエストクラス<BR>
 * 
 * @@author 黄建(中訊)
 * @@version 1.0
 */

public class WEB3FeqProductListRequest extends WEB3GenRequest 
{
    
    /**
     * PTYPE<BR>
     */
    public static final String PTYPE = "feq_productList";

    /**
     * SerialVersionUID<BR>
     */
    public static final long serialVersionUID = 200507121500L;
    
    /**
     * (市場コード)<BR>
     * 市場コード<BR>
     * <BR>
     * メニュー画面から呼び出された場合は、null<BR>
     */
    public String marketCode;
    
    /**
     * (銘柄名)<BR>
     * 銘柄名<BR>
     * <BR>
     * ※部分検索可能<BR>
     */
    public String productName;
    
    /**
     * (銘柄コード)<BR>
     * 銘柄コード<BR>
     */
    public String productCode;
    
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
     * 外国株式ソートキーオブジェクトの配列<BR>
     */
    public WEB3ForeignSortKey[] sortKeys;
    
    /**
     * @@roseuid 42CE3A090399
     */
    public WEB3FeqProductListRequest() 
    {
     
    }
    
    /**
    * ログ出力ユーティリティ。
    */    
    private static WEB3LogUtility log =
        WEB3LogUtility.getInstance(WEB3FeqProductListRequest.class);
    
    /**
     * リクエストデータをチェックする。<BR>
     * <BR>
     * １）　@要求ページ番号チェック <BR>
     * 　@１－１）　@未入力の場合、 要求ページ番号に”１”をセットする。 <BR>
     * 
     * 　@１－２）　@数字以外の文字が含まれる場合、例外をスローする。 <BR>
     * 　@class: WEB3BusinessLayerException<BR>
     * 　@tag:   BUSINESS_ERROR_00090<BR>
     * 　@１－３）　@マイナス値の場合、例外をスローする。 <BR>
     * 　@class: WEB3BusinessLayerException<BR>
     * 　@tag:   BUSINESS_ERROR_00616<BR>
     * <BR>
     * ２）　@ページ内表示行数チェック <BR>
     * 　@２－１）　@未入力の場合、例外をスローする。 <BR>
     * 　@class: WEB3BusinessLayerException<BR>
     * 　@tag:   BUSINESS_ERROR_00091<BR>
     * 　@２－２）　@数字以外の文字が含まれる場合、例外をスローする。 <BR>
     * 　@class: WEB3BusinessLayerException<BR>
     * 　@tag:   BUSINESS_ERROR_00092<BR>
     * 　@２－３）　@マイナス値の場合、例外をスローする。 <BR>
     * 　@class: WEB3BusinessLayerException<BR>
     * 　@tag:   BUSINESS_ERROR_00617<BR>
     * <BR>
     * ３）　@　@ソートキーのチェック <BR>
     * 　@３－１）　@ソートキーが未入力lの場合、例外をスローする。 <BR>
     * 　@class: WEB3BusinessLayerException<BR>
     * 　@tag:   BUSINESS_ERROR_00231<BR>
     * 　@３－２）　@（ソートキーの要素数 == 0）の場合、 例外をスローする。<BR> 
     * 　@class: WEB3BusinessLayerException<BR>
     * 　@tag:   BUSINESS_ERROR_00232<BR>
     * 　@３－３）　@ソートキーの要素数分、下記のチェックを繰り返して行う。 <BR>
     * 　@　@　@３－３－１）　@ソートキー.validate()をコールする。 <BR>
     * 　@　@　@３－３－２）　@ソートキー.キー項目が下記の項目名以外の場合、<BR>
     * 　@　@　@例外をスローする。 <BR>
     * 　@　@　@　@ 外国株式銘柄明細.銘柄コード <BR>
     * 　@　@　@　@ 外国株式銘柄明細.現地銘柄コード 
     *         外国株式銘柄明細.銘柄名<BR>
     * 　@class: WEB3BusinessLayerException<BR>
     * 　@tag:   BUSINESS_ERROR_00086<BR>
     * 
     * @@throws WEB3BaseException
     * @@roseuid 429603F403A0
     */
    public void validate() throws WEB3BaseException
    {
        final String STR_METHOD_NAME = " validate()";
        log.entering(STR_METHOD_NAME);
        
        //１）　@要求ページ番号チェック
        //１－１）　@未入力の場合、 要求ページ番号に”１”をセットする。
        if (WEB3StringTypeUtility.isEmpty(this.pageIndex))
        {
            this.pageIndex = "1";
        }
        
        //１－２）　@数字以外の文字が含まれる場合、例外をスローする。
        if (!WEB3StringTypeUtility.isInteger(this.pageIndex))
        {
            log.debug("要求ページ番号が数字以外の値です。");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_00090,
                this.getClass().getName() + STR_METHOD_NAME,
                "要求ページ番号が数字以外の値です。" + this.pageIndex);
        }
        
        //１－３）　@マイナス値の場合、例外をスローする。
        if (Integer.parseInt(this.pageIndex) <= 0)
        {
            log.debug("要求ページ番号の値が0以下です。");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_00616,
                this.getClass().getName() + STR_METHOD_NAME,
                "要求ページ番号の値が0以下です。" + this.pageIndex);
        }
        
        //２）　@ページ内表示行数チェック 
        //２－１）　@未入力の場合、例外をスローする。
        if (WEB3StringTypeUtility.isEmpty(this.pageSize))
        {
            log.debug("ページ内表示行数が未入力です。");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_00091,
                this.getClass().getName() + STR_METHOD_NAME,
                "ページ内表示行数が未入力です。" + this.pageSize);
        }
        //２－２）　@数字以外の文字が含まれる場合、例外をスローする。 
        if (!WEB3StringTypeUtility.isInteger(this.pageSize))
        {
            log.debug("ページ内表示行数が数字以外の値です。");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_00092,
                this.getClass().getName() + STR_METHOD_NAME,
                "ページ内表示行数が数字以外の値です。" + this.pageSize);
        }
        
        //２－３）　@マイナス値の場合、例外をスローする。
        if (Integer.parseInt(this.pageSize) <= 0)
        {
            log.debug("ページ内表示行数の値が0以下です。");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_00617,
                this.getClass().getName() + STR_METHOD_NAME,
                "ページ内表示行数の値が0以下です。" + this.pageSize);
        }
        
        //３）　@ソートキーのチェック 
        //３－１）　@ソートキーが未入力lの場合、例外をスローする。
        if (this.sortKeys == null)
        {
            log.debug("ソートキーが未指定です。");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_00231,
                this.getClass().getName() + STR_METHOD_NAME,
                "ソートキーが未指定です。");
        }
        
        //３－２）（ソートキーの要素数 == 0）の場合、 例外をスローする。
        if (this.sortKeys.length == 0)
        {
            log.debug("ソートキーの要素数が０です。");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_00232,
                this.getClass().getName() + STR_METHOD_NAME,
                "ソートキーの要素数が０です。");
        }  
        
        //３－３）　@ソートキーの要素数分、下記のチェックを繰り返して行う。
        for (int i=0; i<this.sortKeys.length; i++)
        {
            //３－３－１）　@ソートキー.validate()メソッドをコールする。
            this.sortKeys[i].validate();
            
            //３－３－２）　@ソートキー.キー項目が以下の値以外の場合、例外をスローする。 
            //以下の項目名以外が存在した場合、例外をスローする。 
            //・外国株式注文明細.銘柄コード 
            //外国株式銘柄明細.現地銘柄コード
            //外国株式銘柄明細.銘柄名
            if (!WEB3FeqSortKeyItemNameDef.PRODUCT_CODE.equals(this.sortKeys[i].keyItem)
                && !WEB3FeqSortKeyItemNameDef.OFFSHORE_PRODUCT_CODE.equals(this.sortKeys[i].keyItem)
                && !WEB3FeqSortKeyItemNameDef.PRODUCT_NAME.equals(this.sortKeys[i].keyItem))
            {
                log.debug("ソートキーのキー項目の値が存在しないコード値です。");
                log.exiting(STR_METHOD_NAME);
                throw new WEB3BusinessLayerException(
                    WEB3ErrorCatalog.BUSINESS_ERROR_00086,
                    this.getClass().getName() + STR_METHOD_NAME,
                    "ソートキーのキー項目の値が存在しないコード値です。" + 
                     this.sortKeys[i].keyItem);
            }
        }
    }
    
    /**
     * 当該リクエストに対応するレスポンスオブジェクトを返す。<BR>
     *<BR>
     * @@return レスポンスオブジェクト
     */
    public WEB3GenResponse createResponse()
    {
        return new WEB3FeqProductListResponse(this);
    }
}
@
