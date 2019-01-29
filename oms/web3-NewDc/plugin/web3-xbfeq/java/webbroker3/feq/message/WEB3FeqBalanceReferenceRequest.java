head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.16.03.32.53;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	6004d80209d0226;
filename	WEB3FeqBalanceReferenceRequest.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : 外国株式残高照会リクエスト(WEB3FeqBalanceReferenceRequest)
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
 * (外国株式残高照会リクエスト)<BR>
 * 外国株式残高照会リクエストクラス<BR>
 * 
 * @@author 黄建(中訊)
 * @@version 1.0
 */

public class WEB3FeqBalanceReferenceRequest extends WEB3GenRequest 
{
    /**
     * PTYPE<BR>
     */
    public static final String PTYPE = "feq_balanceReference";

    /**
     * SerialVersionUID<BR>
     */
    public static final long serialVersionUID = 200507121500L;
    
    /**
    * ログ出力ユーティリティ。
    */    
    private static WEB3LogUtility log =
        WEB3LogUtility.getInstance(WEB3FeqBalanceReferenceRequest.class);

    /**
     * (銘柄コード)<BR>
     * 銘柄コード<BR>
     */
    public String productCode;
    
    /**
     * (市場コード)<BR>
     * 市場コード<BR>
     */
    public String marketCode;
    
    /**
     * (ソートキー)<BR>
     * 外国株式ソートキーの配列<BR>
     */
    public WEB3ForeignSortKey[] sortKeys;
    
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
     * @@roseuid 42CE3A0401F4
     */
    public WEB3FeqBalanceReferenceRequest() 
    {
     
    }
    
    /**
     * エストデータの整合性をチェックする。<BR>
     * <BR>
     * １）　@ソートキーのチェック<BR>
     * 　@１－１）ソートキー == nullの場合、例外をスローする。<BR>
     * 　@class: WEB3BusinessLayerException<BR>
     * 　@tag:   BUSINESS_ERROR_00231<BR>
     * <BR>
     *   １－２）ソートキーの要素数 == 0 の場合、例外をスローする。<BR>
     * 　@class: WEB3BusinessLayerException<BR>
     * 　@tag:   BUSINESS_ERROR_00232<BR>
     * <BR>
     * 　@１－３）ソートキーの配列の個数分、<BR>
     * 　@　@繰り返して以下のチェックを行う。<BR>
     * 　@　@１－３－１）ソートキー.validate()メソッドをコールする。<BR>
     * 　@　@１－３－２）ソートキー.キー項目に<BR>
     * 　@　@　@以下の項目名以外が存在した場合、例外をスローする。<BR>
     * 　@　@　@　@・外国株式残高照会明細.銘柄コード<BR>
     * 　@　@　@　@・外国株式残高照会明細.現地銘柄コード<BR>
     * 　@　@　@　@・外国株式残高照会明細.市場コード<BR>
     * 　@　@　@　@・外国株式残高照会明細.特定口座区分<BR>
     * 　@　@　@　@・外国株式残高照会明細.概算評価額(残高数量)<BR>
     * 　@　@　@　@・外国株式残高照会明細.概算評価損益(残高数量)<BR>
     * 　@class: WEB3BusinessLayerException<BR>
     * 　@tag:   BUSINESS_ERROR_00086<BR>
     * <BR>
     * ２）　@要求ページ番号のチェック<BR>
     * 　@２－１）　@this.要求ページ番号が以下の条件のいずれかに該当する場合、<BR>
     * 　@　@例外をスローする。<BR>
     * 　@　@・this.要求ページ番号 == null<BR>
     * 　@class: WEB3BusinessLayerException<BR>
     * 　@tag:   BUSINESS_ERROR_00089<BR>
     * 　@　@・this.要求ページ番号 != 数値<BR>
     * 　@class: WEB3BusinessLayerException<BR>
     * 　@tag:   BUSINESS_ERROR_00090<BR>
     * 　@　@・this.要求ページ番号 <= 0<BR>
     * 　@class: WEB3BusinessLayerException<BR>
     * 　@tag:   BUSINESS_ERROR_00616<BR>
     * <BR>
     * ３）　@ページ内表示行数のチェック<BR>
     * 　@３－１）　@this.ページ内表示行数が以下の条件のいずれかに該当する場合、<BR>
     * 　@　@例外をスローする。<BR>
     * 　@　@・this.ページ内表示行数 == null<BR>
     * 　@class: WEB3BusinessLayerException<BR>
     * 　@tag:   BUSINESS_ERROR_00091<BR>
     * 　@　@・this.ページ内表示行数 != 数値<BR>
     * 　@class: WEB3BusinessLayerException<BR>
     * 　@tag:   BUSINESS_ERROR_00092<BR>
     * 　@　@・this.ページ内表示行数 <= 0<BR>
     * 　@class: WEB3BusinessLayerException<BR>
     * 　@tag:   BUSINESS_ERROR_00617<BR>
     * 
     * @@throws WEB3BaseException
     * @@roseuid 42A7FF8B01E1
     */
    public void validate() throws WEB3BaseException
    {
        final String STR_METHOD_NAME = " validate()";
        log.entering(STR_METHOD_NAME);
        
        //１）ソートキーのチェック 
        //１－１）ソートキー == nullの場合、例外をスローする。 
        if (this.sortKeys == null)
        {
            log.debug("ソートキーが未指定です。");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_00231,
                this.getClass().getName() + STR_METHOD_NAME,
                "ソートキーが未指定です。");
        }
                
        //１－2）this.ソートキーの要素数 == 0 or
        if (this.sortKeys.length == 0)
        {
            log.debug("ソートキーの要素数が０です。");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_00232,
                this.getClass().getName() + STR_METHOD_NAME,
                "ソートキーの要素数が０です。");
        }        
        
        //１－3）ソートキーの配列の個数分、 
        //繰り返して以下のチェックを行う。 
        for (int i = 0; i < this.sortKeys.length; i++)
        {
            //１－3－１）ソートキー.validate()メソッドをコールする。
            this.sortKeys[i].validate();

           //１－3－２）ソートキー.キー項目に 
            //以下の項目名以外が存在した場合、例外をスローする。 
            //・外国株式残高照会明細.銘柄コード 
            //・外国株式残高照会明細.現地銘柄コード 
            //・外国株式残高照会明細.市場コード 
            //・外国株式残高照会明細.特定口座区分 
            //・外国株式残高照会明細.概算評価額(残高数量) 
            //・外国株式残高照会明細.概算評価損益(残高数量) 
            if (!(WEB3FeqSortKeyItemNameDef.PRODUCT_CODE.equals(this.sortKeys[i].keyItem)
                || WEB3FeqSortKeyItemNameDef.OFFSHORE_PRODUCT_CODE.equals(this.sortKeys[i].keyItem)
                || WEB3FeqSortKeyItemNameDef.MARKET_CODE.equals(this.sortKeys[i].keyItem)
                || WEB3FeqSortKeyItemNameDef.TAX_TYPE_DIV.equals(this.sortKeys[i].keyItem)
                || WEB3FeqSortKeyItemNameDef.ESTIMATED_ASSET_BALANCE_QUANTITY.equals(this.sortKeys[i].keyItem)
                || WEB3FeqSortKeyItemNameDef.ESTIMATED_APPRAISAL_PROFIT_LOSS_BALANCE_QUANTITY.equals(this.sortKeys[i].keyItem)))
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
 
        //２）　@要求ページ番号のチェック 
        //２－１）　@this.要求ページ番号が以下の条件のいずれかに該当する場合、 
        //例外をスローする。 
        //・this.要求ページ番号 == null 
        if (this.pageIndex == null)
        {
            log.debug("要求ページ番号が未指定です。");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_00089,
                this.getClass().getName() + STR_METHOD_NAME,
                "要求ページ番号が未指定です。" + this.pageIndex);
        }
        //・this.要求ページ番号 != 数値 
        if (!WEB3StringTypeUtility.isInteger(this.pageIndex))
        {
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_00090,
                this.getClass().getName() + STR_METHOD_NAME,
                "要求ページ番号が数字以外の値です。" + this.pageIndex);
        }
        
        //・this.要求ページ番号 <= 0 
        if (Integer.parseInt(this.pageIndex) <= 0)
        {
            log.debug("要求ページ番号の値が0以下です。");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_00616,
                this.getClass().getName() + STR_METHOD_NAME,
                "要求ページ番号の値が0以下です。" + this.pageIndex);
        }

        //３）　@ページ内表示行数のチェック 
        //３－１）　@this.ページ内表示行数が以下の条件のいずれかに該当する場合、 
        //例外をスローする。 
        //・this.ページ内表示行数 == null 
        if (this.pageSize == null)
        {
            log.debug("ページ内表示行数が未入力です。");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_00091,
                this.getClass().getName() + STR_METHOD_NAME,
                "ページ内表示行数が未入力です。" + this.pageSize);
        }
        
        //・this.ページ内表示行数 != 数値 
        if (!WEB3StringTypeUtility.isInteger(this.pageSize))
        {
            log.debug("ページ内表示行数が数字以外の値です。");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_00092,
                this.getClass().getName() + STR_METHOD_NAME,
                "ページ内表示行数が数字以外の値です。" + this.pageSize);
        }
        
        //・this.ページ内表示行数 <= 0 
        if (Integer.parseInt(this.pageSize) <= 0)
        {
            log.debug("ページ内表示行数の値が0以下です。");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_00617,
                this.getClass().getName() + STR_METHOD_NAME,
                "ページ内表示行数の値が0以下です。" + this.pageSize);
        }
        
        log.exiting(STR_METHOD_NAME);
    }
    
    /**
     * 当該リクエストに対応するレスポンスオブジェクトを返す。<BR>
     *<BR>
     * @@return レスポンスオブジェクト
     */
    public WEB3GenResponse createResponse()
    {
        return new WEB3FeqBalanceReferenceResponse(this);
    }

}
@
