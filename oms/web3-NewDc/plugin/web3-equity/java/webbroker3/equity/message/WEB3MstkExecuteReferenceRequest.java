head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.14.05.45.55;	author che-jin;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	8884d7d97833a75;
filename	WEB3MstkExecuteReferenceRequest.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : 株式ミニ投資注文約定照会リクエスト(WEB3MstkExecuteReferenceRequest.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2004/10/12 彭巍(中訊) 新規作成
*/

package webbroker3.equity.message;

import webbroker3.common.WEB3BaseException;
import webbroker3.common.WEB3BusinessLayerException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.common.message.WEB3GenRequest;
import webbroker3.common.message.WEB3GenResponse;
import webbroker3.equity.define.WEB3EquityKeyItemDef;
import webbroker3.equity.define.WEB3EquityOrderTypeDivisionDef;
import webbroker3.equity.define.WEB3EquityReferenceTypeDef;
import webbroker3.util.WEB3LogUtility;
import webbroker3.util.WEB3StringTypeUtility;

/**
 * （株式ミニ投資注文約定照会リクエスト）。<BR>
 * <BR>
 * 株式ミニ投資注文約定照会リクエストクラス
 * @@author 彭巍
 * @@version 1.0 
 */
public class WEB3MstkExecuteReferenceRequest extends WEB3GenRequest 
{
    
    /**
     * （ログ出力ユーティリティ）。
     */
    private static WEB3LogUtility log =
            WEB3LogUtility.getInstance(WEB3MstkExecuteReferenceRequest.class);
    
    /**
     * （PTYPE）。
     */
    public final static  String PTYPE = "mstk_executeReference";
        
    /**
     * （serialVersionUID）。
     */
    public final static long serialVersionUID = 200410101054L;    
    
    /**
     * （照会区分）。<BR>
     * 0：注文約定照会（デフォルト） <BR>
     * 1：取消一覧（取消可能なもののみ表示）
     */
    public String referenceType;
    
    /**
     * （銘柄コード）。
     */
    public String productCode;
    
    /**
     * （注文状況区分）。<BR>
     * <BR>
     * 画面初回表示時などデフォルトは「指定なし」<BR>
     * null： 指定無し<BR>
     * 0：注文中　@1：約定済　@2：失効済　@3：取消済
     */
    public String miOrderState;
    
    /**
     * （ソートキー）。<BR>
     * <BR>
     * 株式ミニ投資ソートキーの一覧<BR>
     * 対象項目：　@銘柄コード、市場コード、売買区分、注文日時
     */
    public WEB3MstkSortKey[] sortKeys;
    
    /**
     * （要求ページ番号）。<BR>
     * <BR>
     * 表示させたいページ位置を指定<BR>
     * ※先頭ページを"1"とする
     */
    public String pageIndex;
    
    /**
     * （ページ内表示行数）。<BR>
     * <BR>
     * １ページ内に表示させたい行数
     */
    public String pageSize;
    
    /**
     * （株式ミニ投資注文約定照会リクエスト）。<BR>
     * <BR>
     * デフォルトコンストラクタ
     */
    public WEB3MstkExecuteReferenceRequest() 
    {
     
    }
    
    /**
     * （validate）。<BR>
     * <BR>
     * 当リクエストデータの整合性チェックを行う。<BR>
     * （ただし、当クラス内で完結する簡易チェックのみとする。）<BR>
     * <BR>
     * １）　@照会区分チェック<BR>
     * 　@１－１）this.照会区分＝nullであった場合、<BR>
     * 　@　@　@　@　@「照会区分がnull」の例外をスローする。<BR>
     * <BR>
     *         class: WEB3BusinessLayerException<BR>
     *         tag:   BUSINESS_ERROR_00081<BR>
     * <BR>
     * 　@１－２）this.照会区分が下記の値以外が設定されていたら、<BR>
     * 　@　@　@　@「照会区分が未定義の値」の例外をスローする。<BR>
     * 　@　@　@　@・「0：注文約定照会」<BR>
     * 　@　@　@　@・「1：取消一覧(取消可能なもののみ表示)」<BR>
     * <BR>
     *         class: WEB3BusinessLayerException<BR>
     *         tag:   BUSINESS_ERROR_00082<BR>
     * <BR>
     * ２）　@　@注文状況区分チェック<BR>
     * 　@　@　@　@this.注文状況区分≠nullかつ、<BR>
     * 　@　@　@　@this.注文状況区分が以下の値以外の場合、<BR>
     * 　@　@　@　@「注文状況区分が未定義の値」の例外をスローする。<BR>
     * <BR>
     *         class: WEB3BusinessLayerException<BR>
     *         tag:   BUSINESS_ERROR_00439<BR>
     * <BR>
     * 　@　@　@　@0：注文中　@1：約定済　@2：失効済　@3：取消済<BR>
     * <BR>
     * ３）　@　@ソートキーチェック <BR>
     * 　@　@３－１）this.ソートキー＝nullの場合、<BR>
     * 　@　@　@　@　@　@「ソートキーがnull」の例外をスローする。<BR>
     * <BR>
     *         class: WEB3BusinessLayerException<BR>
     *         tag:   BUSINESS_ERROR_00231<BR>
     * <BR>
     * 　@　@３－２）this.ソートキーの要素数＝０の場合、<BR>
     * 　@　@　@　@　@　@「ソートキー.要素数が0」の例外をスローする。<BR>
     * <BR>
     *         class: WEB3BusinessLayerException<BR>
     *         tag:   BUSINESS_ERROR_00232<BR>
     * <BR>
     * 　@　@３－３）this.ソートキーの要素数分、下記のチェックを繰り返して行う。 <BR>
     * 　@　@　@３－３－１）ソートキー.validate()をコールする。<BR>
     * 　@　@　@３－３－２）ソートキー.キー項目が下記の項目名以外の場合、<BR>
     * 　@　@　@　@　@「ソートキー.キー項目が未定義の値」の例外をスローする。 <BR>
     * <BR>
     * 　@　@　@株式ミニ投資注文約定照会注文単位.銘柄コード<BR>
     * 　@　@　@株式ミニ投資注文約定照会注文単位.市場コード<BR>
     * 　@　@　@株式ミニ投資注文約定照会注文単位.売買区分<BR>
     * 　@　@　@株式ミニ投資注文約定照会注文単位.注文日時<BR>
     * <BR>
     *         class: WEB3BusinessLayerException<BR>
     *         tag:   BUSINESS_ERROR_00086<BR>
     * <BR>
     * ４）　@　@要求ページ番号チェック<BR>
     * 　@　@　@　@this．要求ページ番号が、未指定の場合、<BR>
     * 　@　@　@　@this．要求ページ番号に「１」をセットする。<BR>
     * <BR>
     * 　@　@　@　@this.要求ページ番号が、以下のいずれかに該当する場合は、<BR>
     * 　@　@　@　@以下の例外をスローする。<BR>
     * 　@　@　@・this.要求ページ番号＝null　@　@　@　@<BR>
     * 　@(「要求ページ番号がnull」の例外をスロー)<BR>
     * <BR>
     *         class: WEB3BusinessLayerException<BR>
     *         tag:   BUSINESS_ERROR_00089<BR>
     * <BR>
     * 　@　@　@・this.要求ページ番号≠数字　@　@<BR>
     * 　@　@(「要求ページ番号が数字以外」の例外をスロー)<BR>
     * <BR>
     *         class: WEB3BusinessLayerException<BR>
     *         tag:   BUSINESS_ERROR_00090<BR>
     * <BR>
     * 　@　@　@・this.要求ページ番号≦０　@　@ 　@　@　@ <BR>
     * (「要求ページ番号が0以下」の例外をスロー)<BR>
     * <BR>
     *         class: WEB3BusinessLayerException<BR>
     *         tag:   BUSINESS_ERROR_00616<BR>
     * <BR>
     * ５）　@　@ページ内表示行数チェック<BR>
     * 　@　@　@　@this.ページ内表示行数が、以下のいずれかに該当する場合は、<BR>
     * 　@　@　@　@以下の例外をスローする。<BR>
     * 　@　@　@・this.ページ内表示行数＝null<BR>
     * 　@(「ページ内表示行数がnull」の例外をスロー)<BR>
     * <BR>
     *         class: WEB3BusinessLayerException<BR>
     *         tag:   BUSINESS_ERROR_00091<BR>
     * <BR>
     * 　@　@　@・this.ページ内表示行数≠数字　@<BR>
     * 　@　@　@(「ページ内表示行数が数字以外」の例外をスロー)<BR>
     * <BR>
     *         class: WEB3BusinessLayerException<BR>
     *         tag:   BUSINESS_ERROR_00092<BR>
     * <BR>
     * 　@　@　@・this.ページ内表示行数≦０<BR>
     * <BR>
     *         class: WEB3BusinessLayerException<BR>
     *         tag:   BUSINESS_ERROR_00617<BR>
     * <BR>
     *  (「ページ内表示行数が0以下」の例外をスロー)
     */
    public void validate() throws WEB3BaseException 
    {
        final String STR_METHOD_NAME = " validate()";
        log.exiting(STR_METHOD_NAME);
        
        //１）　@照会区分チェック
        if (referenceType== null)
        {
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_00081,
                this.getClass().getName() + "validate");
            
        }
        
        if (!WEB3EquityReferenceTypeDef.REFERENCE_TYPE_UPDATE.equals(referenceType) 
            && !WEB3EquityReferenceTypeDef.REFERENCE_TYPE_VIEW.equals(referenceType))
        {
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_00082,
                this.getClass().getName() + "validate");
        }
        
        // ２）　@　@注文状況区分チェック
         if (this.miOrderState != null
                && !WEB3EquityOrderTypeDivisionDef.ORDRING.equals(this.miOrderState)
                && !WEB3EquityOrderTypeDivisionDef.EXECUTED.equals(this.miOrderState)
                && !WEB3EquityOrderTypeDivisionDef.ORDERED.equals(this.miOrderState)
                && !WEB3EquityOrderTypeDivisionDef.CANCELED.equals(this.miOrderState))
        {
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_00816,
                this.getClass().getName() + "validate");
        }   
        
        // ３）　@　@ソートキーチェック
        if (this.sortKeys == null) 
        {
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_00231,
                this.getClass().getName() + "validate");
            
        }    
        
        if (sortKeys.length == 0) 
        {
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_00232,
                this.getClass().getName() + "validate");
            
        } 
        
        for (int i = 0;i < sortKeys.length;i++)
        {
           sortKeys[i].validate();
           if (!WEB3EquityKeyItemDef.PRODUCTCODE.equals(this.sortKeys[i].keyItem)
                    && !WEB3EquityKeyItemDef.TRADEMARKET.equals(this.sortKeys[i].keyItem)
                    && !WEB3EquityKeyItemDef.DEALINGTYPE.equals(this.sortKeys[i].keyItem)
                    && !WEB3EquityKeyItemDef.ORDER_TIME.equals(this.sortKeys[i].keyItem))                   
            {
                log.exiting(STR_METHOD_NAME);
                throw new WEB3BusinessLayerException(
                    WEB3ErrorCatalog.BUSINESS_ERROR_00086,
                    this.getClass().getName() + "validate");
            }
 
        
        }
        // ４）　@　@要求ページ番号チェック
        if (this.pageIndex == null)
        {
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_00089,
                this.getClass().getName() + "validate");
        }        
        if (!WEB3StringTypeUtility.isNumber(pageIndex))
        {
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_00090,
                this.getClass().getName() + "validate");        
        }       
        if (Double.parseDouble(this.pageIndex) <= 0)
        {
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_00616,
                this.getClass().getName() + "validate");        
        }       
        // ５）　@　@ページ内表示行数チェック
        if (this.pageSize == null)
        {
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_00091,
                this.getClass().getName() + "validate");
        
        }
        if (!WEB3StringTypeUtility.isNumber(pageSize))
        {
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_00092,
                this.getClass().getName() + "validate");
        
        }
        if (Double.parseDouble(this.pageSize) <= 0)
        {
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_00617,
                this.getClass().getName() + "validate");
        
        }
        log.exiting(STR_METHOD_NAME);
        
    }
    
    /**
     * @@return WEB3GenResponse 株式ミニ投資注文約定照会レスポンス
     */
    public WEB3GenResponse createResponse() 
    {
        return new WEB3MstkExecuteReferenceResponse(this);
    }
}
@
