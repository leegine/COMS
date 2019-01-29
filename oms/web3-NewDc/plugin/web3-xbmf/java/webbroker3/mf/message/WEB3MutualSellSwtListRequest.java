head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.16.03.10.36;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	6004d80209d0226;
filename	WEB3MutualSellSwtListRequest.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/** 
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : 投資信託解約乗換一覧照会リクエストクラス(WEB3MutualSellSwtListRequest)
Author Name      : Daiwa Institute of Research
Revesion History : 2004/08/11 黄建 (中訊) 新規作成
                   2004/08/25 周勇 (中訊) レビュー 
                   2004/12/07 于美麗 (中訊) 残対応
*/
package webbroker3.mf.message;

import webbroker3.common.WEB3BaseException;
import webbroker3.common.WEB3BusinessLayerException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.common.define.WEB3AscDescDef;
import webbroker3.common.message.WEB3GenRequest;
import webbroker3.common.message.WEB3GenResponse;
import webbroker3.mf.define.WEB3MFSortkeyItemDef;
import webbroker3.util.WEB3LogUtility;
import webbroker3.util.WEB3StringTypeUtility;

/**
 * 投資信託解約乗換一覧照会リクエストクラス<BR>
 * 
 * @@author 黄建(中訊)
 * @@version 1.0  
 */

public class WEB3MutualSellSwtListRequest extends WEB3GenRequest 
{
    
    /**
     * PTYPE
     */
    public static final String PTYPE = "mutual_sell_swt_list";
    
    /**
     * SerialVersionUID<BR>
     */
    public final static long serialVersionUID = 200408111830L;
    
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
     * 対象項目:”口座”、”評価額”、”評価損益”、”注文受付締切時間”<BR>
     */
    public WEB3MutualSortKey[ ] sortKeys;

    /**
     * ログユーティリティ<BR>
     */
    private static WEB3LogUtility log =
        WEB3LogUtility.getInstance(WEB3MutualSellSwtListRequest.class);
    
    /**
     * デフォルトコンストラクタ
     * @@roseuid 40A887920269
     */
    public WEB3MutualSellSwtListRequest() 
    {
     
    }
 
    /**
     * （createResponseの実装）<BR>
     * <BR>
     * 投信解約乗換一覧照会レスポンスオブジェクトを生成して返す。<BR>
     * @@return WEB3GenResponse
     * @@roseuid 40A8879D023A
     */
    public WEB3GenResponse createResponse() 
    {
        return new WEB3MutualSellSwtListResponse(this);
    }
    
    /**
     * 当リクエストデータの整合性チェックを行う。<BR>
     * （ただし、当クラス内で完結する簡易チェックのみとする。）<BR>
     * <BR>
     * <BR>
     * １）　@要求ページ番号チェック<BR>
     * 　@１－１）　@this.要求ページ番号がnullの値であれば例外をスローする。<BR>
     *           class: WEB3BusinessLayerException<BR>
     *           tag:   BUSINESS_ERROR_00089 <BR>
     * 　@１－２）　@this.要求ページ番号が数字以外の値であれば例外をスローする。<BR>
     *           class: WEB3BusinessLayerException<BR>
     *           tag:   BUSINESS_ERROR_00090 <BR>
     * <BR>
     * ２）　@ページ内表示行数チェック<BR>
     * 　@２－１）　@this.ページ内表示行数がnullの値であれば例外をスローする。<BR>
     *           class: WEB3BusinessLayerException<BR>
     *           tag:   BUSINESS_ERROR_00091 <BR>
     * 　@２－２）　@this.ページ内表示行数が数字以外の値であれば例外をスローする。<BR>
     *           class: WEB3BusinessLayerException<BR>
     *           tag:   BUSINESS_ERROR_00092 <BR>
     * <BR>
     * ３)　@投信ソートキーチェック<BR>
     * 　@３－１)　@this.投信ソートキーがnullの値であれば例外をスローする。<BR>
     *           class: WEB3BusinessLayerException<BR>
     *           tag:   BUSINESS_ERROR_00231 <BR>
     * 　@３－２)　@this.投信ソートキーの要素数が０であれば例外をスローする。<BR>
     *           class: WEB3BusinessLayerException<BR>
     *           tag:   BUSINESS_ERROR_00232 <BR>
     * 　@３－３)　@this.投信ソートキーの要素数分繰り返してチェックを行う。<BR>
     * 　@　@３－３－１）　@キー項目がnullの値であれば例外をスローする。<BR>
     *           class: WEB3BusinessLayerException<BR>
     *           tag:   BUSINESS_ERROR_00085 <BR>
     * 　@　@３－３－２）　@キー項目に以下の項目名以外の値が存在したら例外をスローする。<BR>
     * 　@　@　@　@　@　@　@　@・”口座”<BR>
     * 　@　@　@　@　@　@　@　@・”評価額”<BR>
     * 　@　@　@　@　@　@　@　@・”評価損益”<BR>
     * 　@　@　@　@　@　@　@　@・”注文受付締切時間”<BR>
     *           class: WEB3BusinessLayerException<BR>
     *           tag:   BUSINESS_ERROR_00086 <BR>
     * 　@　@３－３－３）　@昇順／降順==nullの場合、例外をスローする。 <BR>
     *           class: WEB3BusinessLayerException<BR>
     *           tag:   BUSINESS_ERROR_00087 <BR>
     * 　@　@３－３－４）　@昇順／降順が以下の値のいずれかではない場合、例外をスローする。<BR> 
     * 　@　@　@　@　@　@　@　@・”昇順” <BR>
     * 　@　@　@　@　@　@　@　@・”降順” <BR>
     *           class: WEB3BusinessLayerException<BR>
     *           tag:   BUSINESS_ERROR_00088 <BR>
     * <BR>
     * @@roseuid 40A8878B02D6
     */
    public void validate() throws WEB3BaseException
    {
        final String STR_METHOD_NAME = "validate() ";
        log.entering(STR_METHOD_NAME);
        
        // １）　@要求ページ番号チェック
        
        //１－１）　@this.要求ページ番号がnullの値であれば例外をスローする。
        if (WEB3StringTypeUtility.isEmpty(this.pageIndex))
        {
            log.debug("要求ページ番号が未指定です。");
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_00089,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                "要求ページ番号が未指定です。"); 
        }
        
        //１－２）　@this.要求ページ番号が数字以外の値であれば例外をスローする。
        // 文字列の文字種を判断する機@能を実装するユーティリティ・
         //クラス(WEB3StringTypeUtility.java)
        if (WEB3StringTypeUtility.isNumber(this.pageIndex) == false)
        {
            log.debug("要求ページ番号が数字以外。");
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_00090,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                "要求ページ番号が数字以外。"); 
        }
        
        // ２）　@ページ内表示行数チェック
        
        //２－１）　@this.ページ内表示行数がnullの値であれば例外をスローする。
        if (WEB3StringTypeUtility.isEmpty(this.pageSize))
        {
            log.debug("ページ内表示行数の入力が不正です。");
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_00091,
                getClass().getName() + "." + STR_METHOD_NAME,
                "ページ内表示行数の入力が不正です。"); 
        }
        
        //２－２）　@this.ページ内表示行数が数字以外の値であれば例外をスローする。
        if (WEB3StringTypeUtility.isNumber(this.pageSize) == false)
        {
            log.debug("ページ内表示行数が数字以外の値です。");
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_00092,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                "ページ内表示行数が数字以外の値です。"); 
        }
       
        // ３)　@投信ソートキーチェック
        
        //３－１)　@this.投信ソートキーがnullの値であれば例外をスローする。
        if (this.sortKeys == null)
        {
            log.debug("ソートキーが未指定です。");
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_00231,
                getClass().getName() + "." + STR_METHOD_NAME,
                "ソートキーが未指定です。"); 
        }
        
        //３－２)　@this.投信ソートキーの要素数が０であれば例外をスローする。
        if (this.sortKeys.length == 0)
        {
            log.debug("ソートキーの要素数が０です。");
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_00232,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                "ソートキーの要素数が０です。");
        }
        
        //３－３)　@this.投信ソートキーの要素数分繰り返してチェックを行う。
         // int l_length = this.sortKeys.length;
        for (int i=0; i < this.sortKeys.length; i++)
        {
           WEB3MutualSortKey l_mutualSortKey = this.sortKeys[i];
           
            //３－３－１）　@キー項目がnullの値であれば例外をスローする。
            if (l_mutualSortKey == null || WEB3StringTypeUtility.isEmpty(l_mutualSortKey.keyItem))
            {
                log.debug("ソートキーのキー項目が未指定です。");
                throw new WEB3BusinessLayerException(
                    WEB3ErrorCatalog.BUSINESS_ERROR_00085,
                    this.getClass().getName() + "." + STR_METHOD_NAME,
                    "ソートキーのキー項目が未指定です。"); 
            }
            
            //３－３－２）　@キー項目に以下の項目名以外の値が存在したら例外をスローする。
             //口座・評価額・評価損益・注文受付締切時間”
            if (!((l_mutualSortKey.keyItem).equals(
                    WEB3MFSortkeyItemDef.TAX_TYPE) || 
                (l_mutualSortKey.keyItem).equals(
                    WEB3MFSortkeyItemDef.MARKET_VALUE) || 
                (l_mutualSortKey.keyItem).equals(
                    WEB3MFSortkeyItemDef.APPRAISAL_PROFIT_LOSS) ||
                (l_mutualSortKey.keyItem).equals(
                    WEB3MFSortkeyItemDef.ORDER_CLOSE_TIME)))
            {
                log.debug("ソートキーのキー項目の値が存在しないコード値です。");
                throw new WEB3BusinessLayerException(
                    WEB3ErrorCatalog.BUSINESS_ERROR_00086,
                    this.getClass().getName() + "." + STR_METHOD_NAME,
                    "キー項目が”口座”、”評価額”、”評価損益”、”注文受付締切時間”" +
                    "以外の値である");     
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
}
@
