head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.16.03.35.25;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	6004d80209d0226;
filename	WEB3FeqExecuteReferenceRequest.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : 外国株式注文約定照会リクエスト(WEB3FeqExecuteReferenceRequest)
Author Name      : Daiwa Institute of Research
Revesion History : 2005/07/14 黄建 (中訊) 新規作成
                 : 2005/08/01 郭英(中訊) レビュー 
Revesion History : 2008/10/02 水落(SRA) 【外国株式】仕様変更管理台帳（モデル）No.464
*/

package webbroker3.feq.message;

import java.util.Date;

import webbroker3.common.WEB3BaseException;
import webbroker3.common.WEB3BusinessLayerException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.common.message.WEB3GenRequest;
import webbroker3.common.message.WEB3GenResponse;
import webbroker3.feq.define.WEB3FeqExecStatusTypeDef;
import webbroker3.feq.define.WEB3FeqReferenceTypeDef;
import webbroker3.feq.define.WEB3FeqSortKeyItemNameDef;
import webbroker3.util.WEB3LogUtility;
import webbroker3.util.WEB3StringTypeUtility;

/**
 * (外国株式注文約定照会リクエスト)<BR>
 * 外国株式注文約定照会リクエストクラス<BR>
 * 
 * @@author 黄建(中訊)
 * @@version 1.0
 */

public class WEB3FeqExecuteReferenceRequest extends WEB3GenRequest 
{
    /**
     * PTYPE<BR>
     */
    public static final String PTYPE = "feq_executeReference";

    /**
     * SerialVersionUID<BR>
     */
    public static final long serialVersionUID = 200507121500L;
    
    /**
    * ログ出力ユーティリティ。
    */    
    private static WEB3LogUtility log =
        WEB3LogUtility.getInstance(WEB3FeqExecuteReferenceRequest.class);
    
    /**
     * (照会区分)<BR>
     * 照会区分<BR>
     * <BR>
     * 0：注文約定照会<BR>
     * 1：訂正取消一覧（訂正取消可能なもののみ表示）<BR>
     */
    public String referenceType;
    
    /**
     * (銘柄コード)<BR>
     * 銘柄コード<BR>
     * <BR>
     * ※ 指定なしの場合は、null<BR>
     */
    public String productCode;
    
    /**
     * (市場コード)<BR>
     * 市場コード<BR>
     * <BR>
     * ※ 指定なしの場合は、null<BR>
     */
    public String marketCode;
    
    /**
     * (約定状態区分)<BR>
     * 約定状態区分<BR>
     * <BR>
     * 0：未約定<BR>
     * 1：一部成立<BR>
     * 2：全部成立<BR>
     * 3：約定処理中(一部成立)<BR>
     * 4：約定処理中(全部成立)<BR>
     * <BR>
     * ※指定なしの場合は、null<BR>
     */
    public String execType;
    
    /**
     * (発注日)<BR>
     * 発注日<BR>
     * <BR>
     * ※指定なしの場合は、null<BR>
     */
    public Date orderBizDate;
    
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
     * @@roseuid 42CE3A08037A
     */
    public WEB3FeqExecuteReferenceRequest() 
    {
     
    }
    
    /**
     * リクエストデータの整合性をチェックする。<BR>
     * <BR>
     * １）　@照会区分のチェック<BR>
     * 　@１－１）　@this.照会区分 == nullの場合、例外をスローする。<BR>
     * 　@class: WEB3BusinessLayerException<BR>
     * 　@tag:   BUSINESS_ERROR_00081<BR>
     * 　@１－２）　@this.照会区分が以下の値以外の場合、例外をスローする。<BR>
     * 　@　@・"注文約定照会"<BR>
     * 　@　@・"訂正取消一覧（訂正取消可能なもののみ表示）"<BR>
     * 　@class: WEB3BusinessLayerException<BR>
     * 　@tag:   BUSINESS_ERROR_00082<BR>
     * <BR>
     * ２）　@約定状態区分のチェック<BR>
     * 　@this.約定状態区分 != nullの場合、以下のチェックを行う。<BR>
     * 　@２－１）　@this.約定状態区分が以下の値以外の場合、例外をスローする。<BR>
     * 　@　@・"未約定"<BR>
     * 　@　@・"一部成立"<BR>
     * 　@　@・"全部成立"<BR>
     * 　@　@・"約定処理中(一部成立)"<BR>
     * 　@　@・"約定処理中(全部成立)"<BR>
     * 　@class: WEB3BusinessLayerException<BR>
     * 　@tag:   BUSINESS_ERROR_00626<BR>
     * <BR>
     * ３）　@ソートキーのチェック<BR>
     * 　@３－１）　@this.ソートキー == nullの場合、例外をスローする<BR>
     * 　@class: WEB3BusinessLayerException<BR>
     * 　@tag:   BUSINESS_ERROR_00231<BR>
     * <BR>
     * 　@３－２）　@this.ソートキーの要素数 == 0の場合、例外をスローする <BR>
     * 　@class: WEB3BusinessLayerException<BR>
     * 　@tag:   BUSINESS_ERROR_00232<BR>
     * <BR>
     * 　@３－３）　@this.ソートキーの要素数分以下の処理を繰り返す。<BR>
     * 　@　@３－３－１）　@ソートキー.validate()メソッドをコールする。<BR>
     * 　@　@３－３－２）　@ソートキー.キー項目が以下の値以外の場合、<BR>
     * 例外をスローする。<BR>
     * 　@　@　@・外国株式注文明細.銘柄コード<BR>
     * 　@　@　@・外国株式注文明細.特定口座区分<BR>
     * 　@　@　@・外国株式注文明細.市場コード<BR>
     * 　@　@　@・外国株式注文明細.売買区分<BR>
     * 　@　@　@・外国株式注文明細.決済区分<BR>
     * 　@　@　@・外国株式注文明細.執行条件<BR>
     * 　@　@　@・外国株式注文明細.発注条件<BR>
     * 　@　@　@・外国株式注文明細.注文時間<BR>
     * 　@　@　@・外国株式注文明細.発注日<BR>
     * 　@　@　@・外国株式注文明細.注文有効期限<BR>
     * 　@class: WEB3BusinessLayerException<BR>
     * 　@tag:   BUSINESS_ERROR_00086<BR>
     * <BR>
     * ４）　@要求ページ番号のチェック<BR>
     * 　@４－１）　@this.要求ページ番号が以下の条件のいずれかに該当する場合、<BR>
     * 　@　@例外をスローする。<BR>
     * 　@　@・this.要求ページ番号 == null<BR>
     * 　@　@class: WEB3BusinessLayerException<BR>
     * 　@　@tag:   BUSINESS_ERROR_00089<BR>
     * 　@　@・this.要求ページ番号 != 数値<BR>
     * 　@　@class: WEB3BusinessLayerException<BR>
     * 　@　@tag:   BUSINESS_ERROR_00090<BR>
     * 　@　@・this.要求ページ番号 <= 0<BR>
     * 　@　@class: WEB3BusinessLayerException<BR>
     * 　@　@tag:   BUSINESS_ERROR_00616<BR>
     * <BR>
     * ５）　@ページ内表示行数のチェック<BR>
     * 　@５－１）　@this.ページ内表示行数が以下の条件のいずれかに該当する場合、<BR>
     * 　@　@例外をスローする。<BR>
     * 　@　@・this.ページ内表示行数 == null<BR>
     * 　@　@class: WEB3BusinessLayerException<BR>
     * 　@　@tag:   BUSINESS_ERROR_00091<BR>
     * 　@　@・this.ページ内表示行数 != 数値<BR>
     * 　@　@class: WEB3BusinessLayerException<BR>
     * 　@　@tag:   BUSINESS_ERROR_00092<BR>
     * 　@　@・this.ページ内表示行数 <= 0<BR>
     * 　@　@class: WEB3BusinessLayerException<BR>
     * 　@　@tag:   BUSINESS_ERROR_00617<BR>
     * 
     * @@throws WEB3BaseException
     * @@roseuid 429EC6F7020F
     */
    public void validate() throws WEB3BaseException
    {
        final String STR_METHOD_NAME = " validate()";
        log.entering(STR_METHOD_NAME);
        
        //１）　@照会区分のチェック
        //１－１）　@this.照会区分 == nullの場合、例外をスローする。
        if (this.referenceType == null)
        {
            log.debug("照会区分が未指定です。");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_00081,
                this.getClass().getName() + STR_METHOD_NAME,
                "照会区分が未指定です。" + this.referenceType);
        }
        
        //１－２）　@this.照会区分が以下の値以外の場合、例外をスローする。
        //・"注文約定照会"
        //・"訂正取消一覧（訂正取消可能なもののみ表示）"
        if (!(WEB3FeqReferenceTypeDef.REFERENCE_TYPE_UPDATE.equals(this.referenceType)
            || WEB3FeqReferenceTypeDef.REFERENCE_TYPE_VIEW.equals(this.referenceType)))
        {
            log.debug("照会区分の値が存在しないコード値です。");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_00082,
                this.getClass().getName() + STR_METHOD_NAME,
                "照会区分の値が存在しないコード値です。" + this.referenceType);
        }
         
        //２）　@約定状態区分のチェック
        //this.約定状態区分 != nullの場合、以下のチェックを行う。
        if (this.execType != null)
        {
            //２－１）　@this.約定状態区分が以下の値以外の場合、例外をスローする。
            //・"未約定"・"一部成立"・"全部成立"・"約定処理中(一部成立)"・"約定処理中(全部成立)"
            if (!(WEB3FeqExecStatusTypeDef.EXEC_TYPE_NOT_PROMISE.equals(this.execType)
                || WEB3FeqExecStatusTypeDef.EXEC_TYPE_ONE_COMPLETE.equals(this.execType)
                || WEB3FeqExecStatusTypeDef.EXEC_TYPE_ALL_COMPLETE.equals(this.execType)
                || WEB3FeqExecStatusTypeDef.EXEC_PROCESSING_ONE_COMPLETE.equals(this.execType)
                || WEB3FeqExecStatusTypeDef.EXEC_PROCESSING_ALL_COMPLETE.equals(this.execType)))
            {
                log.debug("約定状態区分の値が存在しないコード値です。");
                log.exiting(STR_METHOD_NAME);
                throw new WEB3BusinessLayerException(
                    WEB3ErrorCatalog.BUSINESS_ERROR_00626,
                    this.getClass().getName() + STR_METHOD_NAME,
                    "約定状態区分の値が存在しないコード値です。" + 
                    this.referenceType);
            }
        }
        
        //３）　@ソートキーのチェック 
        //３－１）　@this.ソートキー == nullの場合、例外をスローする 
        if (this.sortKeys == null)
        {
            log.debug("ソートキーが未指定です。");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_00231,
                this.getClass().getName() + STR_METHOD_NAME,
                "ソートキーが未指定です。");
        }
        
        // this.ソートキーの要素数 == 0 or
        if (this.sortKeys.length == 0)
        {
            log.debug("ソートキーの要素数が０です。");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_00232,
                this.getClass().getName() + STR_METHOD_NAME,
                "ソートキーの要素数が０です。");
        }  
        
        //３－２）　@this.ソートキーの要素数分以下の処理を繰り返す。 
        for (int i=0; i<this.sortKeys.length; i++)
        {
            //３－２－１）　@ソートキー.validate()メソッドをコールする。
            this.sortKeys[i].validate();
            //３－２－２）　@ソートキー.キー項目が以下の値以外の場合、例外をスローする。 
            //以下の項目名以外が存在した場合、例外をスローする。 
            //・外国株式注文明細.銘柄コード 
            //・外国株式注文明細.特定口座区分 
            //・外国株式注文明細.市場コード 
            //・外国株式注文明細.売買区分 
            //・外国株式注文明細.決済区分 
            //・外国株式注文明細.執行条件 
            //・外国株式注文明細.発注条件 
            //・外国株式注文明細.注文時間 
            //・外国株式注文明細.発注日 
            //・外国株式注文明細.注文有効期限 
            if (!(WEB3FeqSortKeyItemNameDef.PRODUCT_CODE.equals(this.sortKeys[i].keyItem)
                || WEB3FeqSortKeyItemNameDef.TAX_TYPE_DIV.equals(this.sortKeys[i].keyItem)
                || WEB3FeqSortKeyItemNameDef.MARKET_CODE.equals(this.sortKeys[i].keyItem)
                || WEB3FeqSortKeyItemNameDef.BUY_SELL_DIV.equals(this.sortKeys[i].keyItem)
                || WEB3FeqSortKeyItemNameDef.SETTLE_DIV.equals(this.sortKeys[i].keyItem)
                || WEB3FeqSortKeyItemNameDef.EXEC_COND_TYPE.equals(this.sortKeys[i].keyItem)
                || WEB3FeqSortKeyItemNameDef.ORDER_COND_TYPE.equals(this.sortKeys[i].keyItem)
                || WEB3FeqSortKeyItemNameDef.ORDER_TIME.equals(this.sortKeys[i].keyItem)
                || WEB3FeqSortKeyItemNameDef.BIZ_DATE.equals(this.sortKeys[i].keyItem)
                || WEB3FeqSortKeyItemNameDef.EXPIRATION_DATE.equals(this.sortKeys[i].keyItem)))
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

        //４）　@要求ページ番号のチェック 
        //４－１）　@this.要求ページ番号が以下の条件のいずれかに該当する場合、 
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
            log.debug("要求ページ番号が数字以外の値です。");
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

        //５）　@ページ内表示行数のチェック 
        //５－１）　@this.ページ内表示行数が以下の条件のいずれかに該当する場合、
        
        //・this.ページ内表示行数 == null 
        //例外をスローする。 
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
        return new WEB3FeqExecuteReferenceResponse(this);
    }
}
@
