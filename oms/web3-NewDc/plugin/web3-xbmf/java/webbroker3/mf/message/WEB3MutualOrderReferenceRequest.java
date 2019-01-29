head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.16.02.59.27;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	6004d80209d0226;
filename	WEB3MutualOrderReferenceRequest.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/** 
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : 投資信託注文照会リクエストクラス(WEB3MutualOrderReferenceRequest)
Author Name      : Daiwa Institute of Research
Revesion History : 2004/08/12 黄建 (中訊) 新規作成
Revesion History : 2004/08/23 于美麗 (中訊) レビュー
Revesion History : 2007/02/03 張騰宇 (中訊) 仕様変更・モデル535 
*/
package webbroker3.mf.message;

import webbroker3.common.WEB3BaseException;
import webbroker3.common.WEB3BusinessLayerException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.common.define.WEB3AscDescDef;
import webbroker3.common.message.WEB3GenRequest;
import webbroker3.common.message.WEB3GenResponse;
import webbroker3.mf.define.WEB3MFSortkeyItemDef;
import webbroker3.mf.define.WEB3ReferenceDivDef;
import webbroker3.util.WEB3LogUtility;
import webbroker3.util.WEB3StringTypeUtility;

/**
 * 投資信託注文照会リクエストクラス<BR>
 * 
 * @@author 黄建(中訊)
 * @@version 1.0 
 */

public class WEB3MutualOrderReferenceRequest extends WEB3GenRequest 
{
    /**
     * PTYPE
     */
    public static final String PTYPE = "mutual_order_reference";
    
    /**
     * SerialVersionUID<BR>
     */
    public final static long serialVersionUID = 200408121115L;
    
    /**
     * 照会区分<BR>
     * <BR>
     * 0:注文照会　@1:取消一覧<BR>
     */
    public String referenceType;
    
    /**
     * 要求ページ番号<BR>
     * 表示させたいページ位置を指定<BR>
     * ※先頭ページを"1"とする<BR>
     */
    public String pageIndex;
    
    /**
     * ページ内表示行数<BR>
     * 1ページ内に表示させたい行数を指定<BR>
     */
    public String pageSize;
    
    /**
     * 投信ソートキー<BR>
     * <BR>
     * 対象項目:”口座”、”売買”、”注文日時”<BR>
     */
    public WEB3MutualSortKey[]  sortKeys;

    /**
     * (投信・外貨MMF表示区分)<BR>
     * 投信･外貨MMF表示区分 <BR>
     * <BR>
     * 表示対象の銘柄を、投信･外貨MMFで切り替えるための区分 <BR>
     * <BR>
     * 0:投信のみ <BR>
     * 1:外貨MMFのみ <BR>
     * 2:両方 <BR>
     * <BR>
     * ※nullの場合、「0:投信のみ」とする<BR>
     */
    public String mutualFrgnMmfDisplayDiv;

    /**
     * ログユーティリティ<BR>
     */
    private static WEB3LogUtility log =
        WEB3LogUtility.getInstance(WEB3MutualOrderReferenceRequest.class);
    
    /**
     * (投信注文照会リクエスト)<BR>
     * デフォルトコンストラクタ
     * @@roseuid 40A9A5AA0376
     */
    public WEB3MutualOrderReferenceRequest() 
    {
     
    }
    
    /**
     * (createレスポンス)<BR>
     * （createResponseの実装）<BR>
     * <BR>
     * 投信注文照会レスポンスオブジェクトを生成して返す。<BR>
     * @@return WEB3GenResponse
     * @@roseuid 40A9A5B702AB
     */
    public WEB3GenResponse createResponse() 
    {
        return new WEB3MutualOrderReferenceResponse(this);
    }
    
    /**
     * 当リクエストデータの整合性チェックを行う。<BR>
     * （ただし、当クラス内で完結する簡易チェックのみとする。）<BR>
     * <BR>
     * １)　@照会区分チェック<BR>
     * 　@１－１)　@this.照会区分がnullの場合、例外をスローする。<BR>
     *             class: WEB3BusinessLayerException<BR>
     *             tag:   BUSINESS_ERROR_00081 <BR>
     * 　@１－２)　@this.照会区分が以下のコード以外の場合、例外をスローする。<BR>
     * 　@　@　@　@・”注文照会”<BR>
     * 　@　@　@　@・”取消一覧”<BR>
     *             class: WEB3BusinessLayerException<BR>
     *             tag:   BUSINESS_ERROR_00082 <BR>
     * <BR>
     * ２）　@要求ページ番号チェック<BR>
     * 　@２－１）　@this.要求ページ番号がnullの値であれば例外をスローする。<BR>
     *             class: WEB3BusinessLayerException<BR>
     *             tag:   BUSINESS_ERROR_00089 <BR>
     * 　@２－２）　@this.要求ページ番号が数字以外の値であれば例外をスローする。<BR>
     *             class: WEB3BusinessLayerException<BR>
     *             tag:   BUSINESS_ERROR_00090 <BR>
     * <BR>
     * ３）　@ページ内表示行数チェック<BR>
     * 　@３－１）　@this.ページ内表示行数がnullの値であれば例外をスローする。<BR>
     *            class: WEB3BusinessLayerException<BR>
     *             tag:   BUSINESS_ERROR_00091 <BR>
     * 　@３－２）　@this.ページ内表示行数が数字以外の値であれば例外をスローする。<BR>
     *             class: WEB3BusinessLayerException<BR>
     *             tag:   BUSINESS_ERROR_00092 <BR>
     * <BR>
     * ４)　@投信ソートキーチェック<BR>
     * 　@４－１)　@this.投信ソートキーがnullの値であれば例外をスローする。<BR>
     *             class: WEB3BusinessLayerException<BR>
     *             tag:   BUSINESS_ERROR_00231 <BR>
     * 　@４－２)　@this.投信ソートキーの要素数が０であれば例外をスローする。<BR>
     *             class: WEB3BusinessLayerException<BR>
     *             tag:   BUSINESS_ERROR_00232 <BR>
     * 　@２－３)　@this.投信ソートキーの要素数分繰り返してチェックを行う。<BR>
     * 　@　@２－３－１）　@キー項目がnullの値であれば例外をスローする。<BR>
     *             class: WEB3BusinessLayerException<BR>
     *             tag:   BUSINESS_ERROR_00085 <BR>
     * 　@　@２－３－２）　@キー項目に以下の項目名以外の値が存在したら例外をスローする。<BR>
     * 　@　@　@　@　@　@　@　@・”口座”<BR>
     * 　@　@　@　@　@　@　@　@・”売買”<BR>
     * 　@　@　@　@　@　@　@　@・”注文日時”<BR>
     *             class: WEB3BusinessLayerException<BR>
     *             tag:   BUSINESS_ERROR_00086 <BR>
     * @@roseuid 40A9A5E001D0
     */
    public void validate() throws WEB3BaseException
    {
        final String STR_METHOD_NAME = "validate() ";
        log.entering(STR_METHOD_NAME);
       
        //１)　@照会区分チェック 
        
        //１－１)　@this.照会区分がnullの場合、例外をスローする。
        if(WEB3StringTypeUtility.isEmpty(this.referenceType))
        {
            log.debug("照会区分が未指定です。");
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_00081,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                "照会区分が未指定です。");
        }
        
        //１－２)　@this.照会区分が以下のコード以外の場合、例外をスローする。
         //注文照会.取消一覧
        if (!((WEB3ReferenceDivDef.ORDER_REFERENCE).equals(
                this.referenceType) || 
            (WEB3ReferenceDivDef.CANCEL_REFERENCE).equals(
                this.referenceType)))
        {
            log.debug("照会区分が“0:注文照会”、" +
                "“1:取消一覧(取消可能なもののみ表示)”以外である。");
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_00082,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                "照会区分が“0:注文照会”、“" +
                "1:取消一覧(取消可能なもののみ表示)”以外である。");
        }
        
        //２）　@要求ページ番号チェック 
       
        //２－１）　@this.要求ページ番号がnullの値であれば例外をスローする。
        if (WEB3StringTypeUtility.isEmpty(this.pageIndex))
        {
            log.debug("要求ページ番号が未指定です。");
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_00089,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                "要求ページ番号が未指定です。");
        }
        
        //２－２）　@this.要求ページ番号が数字以外の値であれば例外をスローする。
         //文字列の文字種を判断する機@能を実装するユーティリティ・
          //クラス(WEB3StringTypeUtility.java)。
        if (WEB3StringTypeUtility.isNumber(this.pageIndex) == false)
        {
            log.debug("要求ページ番号が数字以外の値です。");
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_00090,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                "要求ページ番号が数字以外の値です。");
        }
       
        //　@３）　@ページ内表示行数チェック
   
        // ３－１）　@this.ページ内表示行数がnullの値であれば例外をスローする。
        if (WEB3StringTypeUtility.isEmpty(this.pageSize))
        {
            log.debug("ページ内表示行数の入力が不正です。");
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_00091 ,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                "ページ内表示行数の入力が不正です。");
        }
        
        // 　@３－２）　@this.ページ内表示行数が数字以外の値であれば例外をスローする。
        if (WEB3StringTypeUtility.isNumber(this.pageSize) == false)
        {
            log.debug("ページ内表示行数が数字以外の値です。");
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog. BUSINESS_ERROR_00092,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                "ページ内表示行数が数字以外の値です。");
        }
      
        //４)　@投信ソートキーチェック 
        
        // 　@４－１)　@this.投信ソートキーがnullの値であれば例外をスローする。
        if (this.sortKeys == null)
        {
            log.debug("ソートキーが未指定です。");
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_00231,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                "ソートキーが未指定です。");    
        }
        
        // 　@４－２)　@this.投信ソートキーの要素数が０であれば例外をスローする。
        if (this.sortKeys.length == 0)
        {
            log.debug("ソートキーの要素数が０です。");
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_00232,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                "ソートキーの要素数が０です。");    
        }
        
        // 　@２－３)　@this.投信ソートキーの要素数分繰り返してチェックを行う。
        for (int i = 0; i < this.sortKeys.length; i++)
        {
            // ２－３－１）　@キー項目がnullの値であれば例外をスローする。<BR>
            WEB3MutualSortKey l_mutualSortKey = this.sortKeys[i];
            if (l_mutualSortKey == null || 
                WEB3StringTypeUtility.isEmpty(l_mutualSortKey.keyItem))
            {
                log.debug("ソートキーのキー項目が未指定です。");
                throw new WEB3BusinessLayerException(
                    WEB3ErrorCatalog.BUSINESS_ERROR_00085,
                    getClass().getName() + "." + STR_METHOD_NAME,
                    "ソートキーのキー項目が未指定です。"); 
            }
        
            // ２－３－２）　@キー項目に以下の項目名以外の値が存在したら例外をスローする。
             //口座・売買.・注文日時
            if (!((l_mutualSortKey.keyItem).equals(
                    WEB3MFSortkeyItemDef.TAX_TYPE)  || 
                (l_mutualSortKey.keyItem).equals(
                    WEB3MFSortkeyItemDef.MUTUAL_DEALING_TYPE)  || 
                (l_mutualSortKey.keyItem).equals(
                    WEB3MFSortkeyItemDef.ORDER_DATE) ||
                (l_mutualSortKey.keyItem).equals(
                    WEB3MFSortkeyItemDef.SELL_BUY_DIV)))

            {
                log.debug("ソートキーのキー項目の値が存在しないコード値です。");
                throw new WEB3BusinessLayerException(
                    WEB3ErrorCatalog.BUSINESS_ERROR_00086,
                    getClass().getName() + "." + STR_METHOD_NAME,
                    "キー項目が”口座”、”売買”、”注文日時”以外の場合");     
            }
            // 　@　@３－３－３）　@昇順／降順==nullの場合、例外をスローする。
            if(WEB3StringTypeUtility.isEmpty(l_mutualSortKey.ascDesc))
            {
                log.debug("昇順／降順が未指定です。");
                throw new WEB3BusinessLayerException(
                    WEB3ErrorCatalog.BUSINESS_ERROR_00087,
                    this.getClass().getName() + "." + STR_METHOD_NAME,
                    "昇順／降順が未指定です。");  
            }
            
            // 　@　@３－３－４）　@昇順／降順が以下の値のいずれかではない場合、例外をスローする。
            // 　@　@　@　@・”昇順” 
            // 　@　@　@　@・”降順” 
            if((!WEB3AscDescDef.ASC.equals(l_mutualSortKey.ascDesc))
                    && (!WEB3AscDescDef.DESC.equals(l_mutualSortKey.ascDesc)))
            {
                log.debug("昇順／降順が”A：昇順”、”D：降順”以外の値です。");
                throw new WEB3BusinessLayerException(
                    WEB3ErrorCatalog.BUSINESS_ERROR_00088,
                    this.getClass().getName() + "." + STR_METHOD_NAME,
                    "昇順／降順が”A：昇順”、”D：降順”以外の値です。"); 
            }
        }
        log.exiting(STR_METHOD_NAME);
    }
}@
