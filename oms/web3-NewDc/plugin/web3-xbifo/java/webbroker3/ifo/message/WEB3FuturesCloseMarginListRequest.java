head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.16.03.15.13;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	6004d80209d0226;
filename	WEB3FuturesCloseMarginListRequest.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : 株価指数先物返済一覧画面表示リクエスト(WEB3FuturesCloseMarginListRequest.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2004/07/19 鄒鋭 (中訊) 新規作成
*/

package webbroker3.ifo.message;

import webbroker3.common.WEB3BaseException;
import webbroker3.common.define.WEB3AscDescDef;
import webbroker3.common.message.WEB3GenRequest;
import webbroker3.common.message.WEB3GenResponse;
import webbroker3.common.WEB3BusinessLayerException;
import webbroker3.common.WEB3ErrorCatalog;

import webbroker3.util.WEB3LogUtility;
import webbroker3.util.WEB3StringTypeUtility;

import webbroker3.ifo.define.WEB3IfoKeyItemDef;

/**
 * (株価指数先物返済一覧画面表示リクエスト)<BR>
 * 株価指数先物返済一覧画面表示リクエストクラス
 * @@author 鄒鋭
 * @@version 1.0
 */
public class WEB3FuturesCloseMarginListRequest extends WEB3GenRequest
{

    /**
     * Logger<BR>
     */
    private static WEB3LogUtility log =
        WEB3LogUtility.getInstance(WEB3FuturesCloseMarginListRequest.class);

    /**
     * PTYPE<BR>
     */
    public static final String PTYPE = "futures_closeMarginList";

    /**
     * SerialVersionUID<BR>
     */
    public static final long serialVersionUID = 200407191510L;

    /**
     * (先物銘柄コード)<BR>
     * <BR>
     *(検索条件指定時に使用)<BR>
     * <BR>
     * null：指定なし<BR>
     * 銘柄コード<BR>
     * <BR>
     *※銘柄コードによる銘柄指定の場合、設定される<BR>
     */
    public String futProductCode;

    /**
     * (取引市場)<BR>
     * <BR>
     *(検索条件指定時に使用)<BR>
     * <BR>
     * null：指定なし<BR>
     * 1：東京<BR>
     * 2：大阪<BR>
     * <BR>
     *※銘柄特定項目による銘柄指定の場合、設定される<BR>
     */
    public String marketCode;
    
    /**
     * (指数種別)<BR>
     * <BR>
     *(検索条件指定時に使用)<BR>
     * <BR>
     * null：指定なし<BR>
     * 0005：TOPIX<BR>
     * 0018：日経225<BR>
     * 0016：日経300<BR>
     * 0019：ミニ日経225<BR>
     * <BR>
     *※銘柄特定項目による銘柄指定の場合、設定される<BR>
     */
    public String targetProductCode;
    
    /**
     * (限月)<BR>
     * <BR>
     *(検索条件指定時に使用)<BR>
     * <BR>
     * null：指定なし<BR>
     * 限月(YYYYMM形式)<BR>
     * <BR>
     *※銘柄特定項目による銘柄指定の場合、設定される<BR>
     */
    public String delivaryMonth;

    /**
     * (株価指数先物オプションソートキー)<BR>
     * 対象項目：銘柄コード、建区分、損益、損益（諸経費込）
     */
    public WEB3FuturesOptionsSortKey[] futOpSortKeys;

    /**
     * (要求ページ番号)<BR>
     * 表示させたいページ位置を指定　@※先頭ページを"1"とする
     */
    public String pageIndex;

    /**
     * (ページ内表示行数)<BR>
     * １ページ内に表示させたい行数を指定
     */
    public String pageSize;

    /**
     * @@roseuid 40F7AE160148
     */
    public WEB3FuturesCloseMarginListRequest()
    {

    }

    /**
     * 当リクエストデータの整合性チェックを行う。<BR>
     * （ただし、当クラス内で簡潔する簡易チェックのみとする。）<BR>
     * <BR>
     * １）　@ソートキーチェック<BR>
     * 　@１－１）this.株価指数先物オプションソートキーが<BR>
     * 　@　@　@　@nullの値であれば例外をスローする。<BR>
     *            class: WEB3BusinessLayerException<BR>
     *            tag:   BUSINESS_ERROR_00231<BR>
     * 　@１－２）this.株価指数先物オプションソートキーの要素数が<BR>
     * 　@　@　@　@０であれば例外をスローする。<BR>
     *            class: WEB3BusinessLayerException<BR>
     *            tag:   BUSINESS_ERROR_00232<BR>
     * 　@１－３）this.株価指数先物オプションソートキーの要素数分<BR>
     * 　@　@　@　@繰り返してチェックを行う。<BR>
     * 　@　@１－３－１）株価指数先物オプションソートキー.キー項目<BR>
     *                  がnullの値であれば例外をスローする。<BR>
     *                     class: WEB3BusinessLayerException<BR>
     *                     tag:   BUSINESS_ERROR_00085<BR>
     * 　@　@１－３－２）株価指数先物オプションソートキー.キー項目<BR>
     *                  に以下の項目名以外の値が<BR>
     * 　@　@　@　@　@　@　@存在したら例外をスローする。<BR>
     * 　@　@　@・銘柄コード<BR>
     * 　@　@　@・建区分<BR>
     * 　@　@　@・損益<BR>
     * 　@　@　@・損益(諸経費込)<BR>
     *            class: WEB3BusinessLayerException<BR>
     *            tag:   BUSINESS_ERROR_00278<BR>
     * 　@　@１－３－３）株価指数先物オプションソートキー.昇順／降順<BR>
     *                   がnullの値であれば例外をスローする。<BR>
     *                    class: WEB3BusinessLayerException<BR>
     *                    tag:   BUSINESS_ERROR_00318<BR>
     * 　@　@１－３－４）株価指数先物オプションソートキー.昇順／降順<BR>
     *                   が以下の値以外の場合例外をスローする。<BR>
     * 　@　@　@・”A：昇順”<BR>
     * 　@　@　@・”D：降順”<BR>
     *                    class: WEB3BusinessLayerException<BR>
     *                    tag:   BUSINESS_ERROR_00088<BR>
     * <BR>
     * ２）　@要求ページ番号チェック<BR>
     * 　@２－１）this.要求ページ番号がnullの値であれば例外をスローする。<BR>
     *             class: WEB3BusinessLayerException<BR>
     *             tag:   BUSINESS_ERROR_00089<BR>
     * 　@２－２）this.要求ページ番号が数字以外の値であれば例外をスローする。<BR>
     *             class: WEB3BusinessLayerException<BR>
     *             tag:   BUSINESS_ERROR_00090<BR>
     * <BR>
     * ３）　@ページ内表示行数チェック<BR>
     * 　@３－１）this.ページ内表示行数がnullの値であれば例外をスローする。<BR>
     *             class: WEB3BusinessLayerException<BR>
     *             tag:   BUSINESS_ERROR_00091<BR>
     * 　@３－２）this.ページ内表示行数が数字以外の値であれば例外をスローする。<BR>
     *             class: WEB3BusinessLayerException<BR>
     *             tag:   BUSINESS_ERROR_00092<BR>
     *<BR>
     * ４）　@銘柄設定チェック<BR>
     *   ４－１）以下の全てのリクエスト項目を設定している場合、例外をスローする。<BR>
     *        　@(銘柄コードと銘柄特定項目がどちらも設定されている場合)<BR>
     *      ・銘柄コード<BR>
     *      ・取引市場<BR>
     *      ・指数種別<BR>
     *      ・限月<BR>
     *      class: WEB3BusinessLayerException<BR>
     *      tag:   BUSINESS_ERROR_00830<BR>
     * 　@４－２）銘柄特定項目による銘柄指定の場合<BR>
     *      取引市場、指数種別、限月の全てが設定されていなければ、例外をスローする。<BR>
     *      class: WEB3BusinessLayerException<BR>
     *      tag:   BUSINESS_ERROR_00830<BR>
     * @@throws WEB3BaseException
     * @@roseuid 40A2DCBB00AD
     */
    public void validate() throws WEB3BaseException
    {
        final String STR_METHOD_NAME = "validate()";
        log.entering(STR_METHOD_NAME);

        //１）　@ソートキーチェック
        //１－１）株価指数先物オプションソートキーがnullの値であれば例外をスローする
        if (futOpSortKeys == null)
        {
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_00231,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                "ソートキーがnullの値である。");
        }

        //１－２）this.株価指数先物オプションソートキーの要素数が
        if (futOpSortKeys.length == 0)
        {
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_00232, 
                this.getClass().getName() + "." + STR_METHOD_NAME,
                "ソートキーの要素数が０である。");
        }

        //１－３）this.株価指数先物オプションソートキーの要素数分繰り返してチェックを行う。
        int l_intOpSortKeysLength = futOpSortKeys.length;
        for (int i = 0; i < l_intOpSortKeysLength; i++)
        {
            //１－３－１）ソートキー.キー項目がnullの値であれば例外をスローする。
            if (WEB3StringTypeUtility.isEmpty(futOpSortKeys[i].keyItem))
            {
                throw new WEB3BusinessLayerException(
                    WEB3ErrorCatalog.BUSINESS_ERROR_00085, 
                    this.getClass().getName() + "." + STR_METHOD_NAME,
                    "キー項目がnullの値である。");
            }

            //１－３－２）ソートキー.キー項目に以下の項目名以外の値が存在したら例外をスローする。
            if (!WEB3IfoKeyItemDef.FUTPRODUCTCODE.equals(futOpSortKeys[i].keyItem)
             && !WEB3IfoKeyItemDef.CONTRACT_DIVISION.equals(futOpSortKeys[i].keyItem)
             && !WEB3IfoKeyItemDef.INCOME.equals(futOpSortKeys[i].keyItem)
             && !WEB3IfoKeyItemDef.INCOME_COST.equals(futOpSortKeys[i].keyItem))
            {
                throw new WEB3BusinessLayerException(
                    WEB3ErrorCatalog.BUSINESS_ERROR_00086, 
                    this.getClass().getName() + "." + STR_METHOD_NAME,
                    "ソートキーのキー項目の値が存在しないコード値です。");
            }

            //１－３－３）ソートキー.昇順／降順がnullの値であれば例外をスローする。
            if (WEB3StringTypeUtility.isEmpty(futOpSortKeys[i].ascDesc))
            {
                throw new WEB3BusinessLayerException(
                    WEB3ErrorCatalog.BUSINESS_ERROR_00087, 
                    this.getClass().getName() + "." + STR_METHOD_NAME,
                    "昇順／降順が未指定です。");
            }

            //１－３－４）ソートキー.昇順／降順が以下の値以外の場合例外をスローする。
            if (!WEB3AscDescDef.ASC.equals(futOpSortKeys[i].ascDesc) && !WEB3AscDescDef.DESC.equals(futOpSortKeys[i].ascDesc))
            {
                throw new WEB3BusinessLayerException(
                    WEB3ErrorCatalog.BUSINESS_ERROR_00088, 
                    this.getClass().getName() + "." + STR_METHOD_NAME,
                    "ソートキー.昇順／降順が”A：昇順””D：降順”以外の値である");
            }
        }

        //２）　@要求ページ番号チェック
        //２－１）this.要求ページ番号がnullの値であれば例外をスローする。
        if (WEB3StringTypeUtility.isEmpty(pageIndex))
        {
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_00089, 
                this.getClass().getName() + "." + STR_METHOD_NAME,
                "要求ページ番号がnullの値である。");
        }

        //２－２）this.要求ページ番号が数字以外の値であれば例外をスローする。
        if (!WEB3StringTypeUtility.isNumber(pageIndex))
        {
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_00090, 
                this.getClass().getName() + "." + STR_METHOD_NAME,
                "要求ページ番号が数字以外の値である。");
        }

        //３）　@ページ内表示行数チェック
        //３－１）this.ページ内表示行数がnullの値であれば例外をスローする。
        if (WEB3StringTypeUtility.isEmpty(pageSize))
        {
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_00091, 
                this.getClass().getName() + "." + STR_METHOD_NAME,
                "ページ内表示行数が０、または未指定の場合。");
        }

        //３－２）this.ページ内表示行数が数字以外の値であれば例外をスローする。
        if (!WEB3StringTypeUtility.isNumber(pageSize))
        {
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_00092, 
                this.getClass().getName() + "." + STR_METHOD_NAME,
                "ページ内表示行数が数字以外の値です。");
        }
        
        //４） 銘柄設定チェック
        //４－１）以下の全てのリクエスト項目を設定している場合、例外をスローする。
        //    　@(銘柄コードと銘柄特定項目がどちらも設定されている場合)               
        if((futProductCode!=null)               
            &&(marketCode!=null)               
            &&(targetProductCode!=null)                
            &&(delivaryMonth!=null))                
        {              
            throw new WEB3BusinessLayerException(         
                WEB3ErrorCatalog.BUSINESS_ERROR_00830,         
                this.getClass().getName() + "." + STR_METHOD_NAME,
                "銘柄コードと銘柄特定項目がどちらも設定されています。");      
        }              
                
        // ４－２）銘柄選択時に取引市場,指数種別,限月のいずれかの項目が              
        // 設定されていない場合、例外をスローする。             
        if((marketCode==null)
            &&(targetProductCode==null)
            &&(delivaryMonth==null))                
        {              
            return;            
        }
        else              
        {              
            if((marketCode==null)
                ||(targetProductCode==null)
                ||(delivaryMonth==null))            
            {          
                throw new WEB3BusinessLayerException(  
                    WEB3ErrorCatalog.BUSINESS_ERROR_00830, 
                    this.getClass().getName() + "." + STR_METHOD_NAME,
                    "銘柄特定項目のいずれかが設定されていません。");
            }          
        } 

        log.exiting(STR_METHOD_NAME);
    }

    /**
     * @@return WEB3GenResponse
     * @@roseuid 40F7AE160167
     */
    public WEB3GenResponse createResponse()
    {
        return new WEB3FuturesCloseMarginListResponse(this);
    }
}
@
