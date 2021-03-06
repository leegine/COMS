head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.16.03.12.03;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	6004d80209d0226;
filename	WEB3OptionsCloseMarginListRequest.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : 株価指数オプション返済一覧画面表示リクエストクラス(WEB3OptionsCloseMarginListRequest.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2004/06/10 呉艶飛 新規作成
*/

package webbroker3.ifo.message;

import webbroker3.common.WEB3BaseException;
import webbroker3.common.WEB3BusinessLayerException;
import webbroker3.common.define.WEB3AscDescDef;
import webbroker3.common.message.WEB3GenRequest;
import webbroker3.common.message.WEB3GenResponse;
import webbroker3.common.WEB3ErrorCatalog;

import webbroker3.util.WEB3LogUtility;
import webbroker3.util.WEB3StringTypeUtility;
import webbroker3.ifo.define.WEB3IfoKeyItemDef;

/**
 * (株価指数オプション返済一覧画面表示リクエスト)<BR>
 * 株価指数オプション返済一覧画面表示リクエストクラス<BR>
 * @@author 呉艶飛
 * @@version 1.0
 */
public class WEB3OptionsCloseMarginListRequest extends WEB3GenRequest
{

    /**
     * Logger<BR>
     */
    private static WEB3LogUtility log =
        WEB3LogUtility.getInstance(WEB3OptionsCloseMarginListRequest.class);
            
    /**
     * PTYPE<BR>
     */
    public final static  String PTYPE = "options_closeMarginList";

    /**
    * serialVersionUID<BR>
    */
    public final static long serialVersionUID = 200406101102L;
            
	/**
	 * (銘柄コード)<BR>
	 * <BR>
	 *(検索条件指定時に使用)<BR>
	 * <BR>
	 * null：指定なし<BR>
	 * 銘柄コード<BR>
	 * <BR>
	 *※銘柄コードによる銘柄指定の場合、設定される<BR>
	 */
	public String opProductCode;
    
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
	 * (オプション商品区分)<BR>
	 * <BR>
	 *(検索条件指定時に使用)<BR>
	 * <BR>
	 * null：指定なし<BR>
	 * P：プットオプション<BR>
	 * C：コールオプション<BR>
	 * <BR>
	 *※銘柄特定項目による銘柄指定の場合、設定される<BR>
	 */
	public String opProductType;
    
	/**
	 * (行使価格)<BR>
	 * <BR>
	 *(検索条件指定時に使用)<BR>
	 * <BR>
	 * null：指定なし<BR>
	 * 行使価格<BR>
	 * <BR>
	 *※銘柄特定項目による銘柄指定の場合、設定される<BR>
	 */
	public String strikePrice;

    /**
    * 株価指数先物オプションソートキー<BR>
    * 対象項目：銘柄コード、建区分、損益、損益（諸経費込）<BR>
    */
    public WEB3FuturesOptionsSortKey[] futOpSortKeys;

    /**
    * 表示させたいページ位置を指定　@※先頭ページを"1"とする<BR>
    */
    public String pageIndex;

    /**
    * ページ内表示行数<BR>
    * １ページ内に表示させたい行数を指定<BR>
    */
    public String pageSize;

    /**
    * @@roseuid 40C0A8EC0290
    */
    public WEB3OptionsCloseMarginListRequest()
    {

    }

    /**
    * 当リクエストデータの整合性チェックを行う。<BR>
    * （ただし、当クラス内で完結する簡易チェックのみとする。）<BR>
    * <BR>
    * １）　@ソートキーチェック<BR>
    * 　@１−１）this.株価指数先物オプションソートキーが<BR>
    * 　@　@　@　@nullの値であれば例外をスローする。<BR>
    *     class: WEB3BusinessLayerException<BR>
    *     tag:   BUSINESS_ERROR_00231<BR>
    * 　@１−２）this.株価指数先物オプションソートキーの要素数が<BR>
    * 　@　@　@　@０であれば例外をスローする。<BR>
    *     class: WEB3BusinessLayerException<BR>
    *     tag:   BUSINESS_ERROR_00232<BR>
    * 　@１−３）this.株価指数先物オプションソートキーの要素数分<BR>
    * 　@　@　@　@繰り返してチェックを行う。<BR>
    * 　@　@１−３−１）ソートキー.キー項目がnullの値であれば例外をスローする。<BR>
    *     class: WEB3BusinessLayerException<BR>
    *     tag:   BUSINESS_ERROR_00085<BR>
    * 　@　@１−３−２）ソートキー.キー項目に以下の項目名以外の値が<BR>
    * 　@　@　@　@　@　@　@存在したら例外をスローする。<BR>
    * 　@　@　@・銘柄コード<BR>
    * 　@　@　@・建区分<BR>
    * 　@　@　@・損益<BR>
    * 　@　@　@・損益(諸経費込)<BR>
    *     class: WEB3BusinessLayerException<BR>
    *     tag:   BUSINESS_ERROR_00278<BR>
    * 　@　@１−３−３）ソートキー.昇順／降順がnullの値であれば例外をスローする。<BR>
    *     class: WEB3BusinessLayerException<BR>
    *     tag:   BUSINESS_ERROR_00087<BR>
    * 　@　@１−３−４）ソートキー.昇順／降順以下の値以外の場合例外をスローする。<BR>
    * 　@　@　@・”A：昇順”<BR>
    * 　@　@　@・”D：降順”<BR>
    *     class: WEB3BusinessLayerException<BR>
    *     tag:   BUSINESS_ERROR_00088<BR>
    * <BR>
    * ２）　@要求ページ番号チェック<BR>
    * 　@２−１）this.要求ページ番号がnullの値であれば例外をスローする。<BR>
    *     class: WEB3BusinessLayerException<BR>
    *     tag:   BUSINESS_ERROR_00089<BR>
    * 　@２−２）this.要求ページ番号が数字以外の値であれば例外をスローする。<BR>
    *     class: WEB3BusinessLayerException<BR>
    *     tag:   BUSINESS_ERROR_00090<BR>
    * <BR>
    * ３）　@ページ内表示行数チェック<BR>
    * 　@３−１）this.ページ内表示行数がnullの値であれば例外をスローする。<BR>
    *     class: WEB3BusinessLayerException<BR>
    *     tag:   BUSINESS_ERROR_00091<BR>
    * 　@３−２）this.ページ内表示行数が数字以外の値であれば例外をスローする。<BR>
    *     class: WEB3BusinessLayerException<BR>
    *     tag:   BUSINESS_ERROR_00092<BR>
    * <BR>
    * ４）　@銘柄設定チェック<BR>
    *   ４−１）以下の全てのリクエスト項目を設定している場合、例外をスローする。<BR>
    *        　@(銘柄コードと銘柄特定項目がどちらも設定されている場合)<BR>
    *      ・銘柄コード<BR>
    *      ・取引市場<BR>
    *      ・指数種別<BR>
    *      ・限月<BR>
    *      ・オプション商品区分<BR>
    *      ・行使価格<BR>
    *      class: WEB3BusinessLayerException<BR>
    *      tag:   BUSINESS_ERROR_00830<BR>
    * 　@４−２）銘柄特定項目による銘柄指定の場合<BR>
    *      取引市場、指数種別、限月、オプション商品区分、行使価格の全てが設定されていなければ、例外をスローする。<BR>
    *      class: WEB3BusinessLayerException<BR>
    *      tag:   BUSINESS_ERROR_00830<BR>
    * @@throws WEB3BaseException
    * @@roseuid 406A1D9B031F
    */
    public void validate() throws WEB3BaseException
    {
        final String STR_METHOD_NAME = "validate()";
        log.entering(STR_METHOD_NAME);

        //１）　@ソートキーチェック
        //１−１）株価指数先物オプションソートキーがnullの値であれば例外をスローする
        if (futOpSortKeys == null)
        {
            //例外
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_00231,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                "ソートキーがnullの値である。");
        }

        //１−２）this.株価指数先物オプションソートキーの要素数が
        if (futOpSortKeys.length == 0)
        {
            //例外
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_00232,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                "ソートキーの要素数が０である。");
        }

        //１−３）this.株価指数先物オプションソートキーの要素数分繰り返してチェックを行う。
        int l_intOpSortKeysLength = futOpSortKeys.length;
        for (int i = 0; i < l_intOpSortKeysLength; i++)
        {
            //１−３−１）ソートキー.キー項目がnullの値であれば例外をスローする。
            if (WEB3StringTypeUtility.isEmpty(futOpSortKeys[i].keyItem))
            {
                //例外
                throw new WEB3BusinessLayerException(
                    WEB3ErrorCatalog.BUSINESS_ERROR_00085,
                    this.getClass().getName() + "." + STR_METHOD_NAME,
                    "キー項目がnullの値である。");
            }

            //１−３−２）ソートキー.キー項目に以下の項目名以外の値が存在したら例外をスローする。
            if (!WEB3IfoKeyItemDef.PRODUCTCODE.equals(futOpSortKeys[i].keyItem)
             && !WEB3IfoKeyItemDef.CONTRACT_DIVISION.equals(futOpSortKeys[i].keyItem)
             && !WEB3IfoKeyItemDef.INCOME.equals(futOpSortKeys[i].keyItem)
             && !WEB3IfoKeyItemDef.INCOME_COST.equals(futOpSortKeys[i].keyItem))
            {
                //例外
                throw new WEB3BusinessLayerException(
                    WEB3ErrorCatalog.BUSINESS_ERROR_00086,
                    this.getClass().getName() + "." + STR_METHOD_NAME,
                    "ソートキーのキー項目の値が存在しないコード値です。");
            }

            //１−３−３）ソートキー.昇順／降順がnullの値であれば例外をスローする。
            if (WEB3StringTypeUtility.isEmpty(futOpSortKeys[i].ascDesc))
            {
                //例外
                throw new WEB3BusinessLayerException(
                    WEB3ErrorCatalog.BUSINESS_ERROR_00087,
                    this.getClass().getName() + "." + STR_METHOD_NAME,
                    "昇順／降順がnullの値である。");
            }

            //１−３−４）ソートキー.昇順／降順が以下の値以外の場合例外をスローする。
            if (!WEB3AscDescDef.ASC.equals( futOpSortKeys[i].ascDesc)
                && !WEB3AscDescDef.DESC.equals(futOpSortKeys[i].ascDesc))
            {
                //例外
                throw new WEB3BusinessLayerException(
                    WEB3ErrorCatalog.BUSINESS_ERROR_00088,
                    this.getClass().getName() + "." + STR_METHOD_NAME,
                    "昇順／降順が”A：昇順”、”D：降順”以外の値です。");
            }
        }

        //２）　@要求ページ番号チェック
        //２−１）this.要求ページ番号がnullの値であれば例外をスローする。
        if (WEB3StringTypeUtility.isEmpty(pageIndex))
        {
            //例外
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_00089,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                "要求ページ番号がnullの値である。");
        }

        //２−２）this.要求ページ番号が数字以外の値であれば例外をスローする。
        if (!WEB3StringTypeUtility.isNumber(pageIndex))
        {
            //例外
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_00090,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                "要求ページ番号が数字以外の値である。");
        }

        //３）　@ページ内表示行数チェック
        //３−１）this.ページ内表示行数がnullの値であれば例外をスローする。
        if (WEB3StringTypeUtility.isEmpty(pageSize))
        {
            //例外
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_00091,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                "ページ内表示行数が０、または未指定の場合。");
        }

        //３−２）this.ページ内表示行数が数字以外の値であれば例外をスローする。
        if (!WEB3StringTypeUtility.isNumber(pageSize))
        {
            //例外
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_00092,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                "ページ内表示行数が数字以外の値です。");
        }
        
		//４） 銘柄設定チェック
		//４−１）以下の全てのリクエスト項目を設定している場合、例外をスローする。
		//    　@(銘柄コードと銘柄特定項目がどちらも設定されている場合)               
		if((opProductCode!=null)               
			&&(marketCode!=null)               
			&&(targetProductCode!=null)                
			&&(delivaryMonth!=null)                
			&&(opProductType!=null)                
			&&(strikePrice!=null))             
		{              
			throw new WEB3BusinessLayerException(         
				WEB3ErrorCatalog.BUSINESS_ERROR_00830,         
				this.getClass().getName() + "." + STR_METHOD_NAME,        
				"銘柄コードと銘柄特定項目がどちらも設定されています。");      
		}              
                
		// ４−２）銘柄選択時に取引市場,指数種別,限月,オプション商品区分,行使価格のいずれかの項目が              
		// 設定されていない場合、例外をスローする。             
		if((marketCode==null)
			&&(targetProductCode==null)
			&&(delivaryMonth==null)                
			&&(opProductType==null)
			&&(strikePrice==null))          
		{              
			return;            
		}
		else              
		{              
			if((marketCode==null)
				||(targetProductCode==null)
				||(delivaryMonth==null)            
				||(opProductType==null)
				||(strikePrice==null))      
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
    * 当該リクエストに対応するレスポンスオブジェクトを返す。<BR>
    *<BR>
    * @@return レスポンスオブジェクト
    */
    public WEB3GenResponse createResponse()
    {
        return new WEB3OptionsCloseMarginListResponse(this);
    }
}
@
