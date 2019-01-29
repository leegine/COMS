head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.16.03.16.20;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	6004d80209d0226;
filename	WEB3OptionsExecuteReferenceRequest.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : 株価指数オプション注文約定照会リクエスト(WEB3OptionsExecuteReferenceRequest.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2004/06/11 鄒鋭 (中訊) 新規作成
Revesion History : 2007/10/16 張騰宇(中訊)モデル 785
*/

package webbroker3.ifo.message;

import java.util.Date;

import webbroker3.common.WEB3BaseException;
import webbroker3.common.WEB3BusinessLayerException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.common.define.WEB3AscDescDef;
import webbroker3.common.define.WEB3OrderingConditionDef;
import webbroker3.common.message.WEB3GenRequest;
import webbroker3.common.message.WEB3GenResponse;
import webbroker3.util.WEB3LogUtility;
import webbroker3.util.WEB3StringTypeUtility;

import webbroker3.ifo.define.WEB3IfoExecStatusTypeDef;
import webbroker3.ifo.define.WEB3IfoReferenceTypeDef;
import webbroker3.ifo.define.WEB3IfoKeyItemDef;

/**
 * (株価指数オプション注文約定照会リクエスト)<BR>
 * 株価指数オプション注文約定照会リクエストクラス<BR>
 * @@author 鄒鋭
 * @@version 1.0
 */
public class WEB3OptionsExecuteReferenceRequest extends WEB3GenRequest
{

     /**
      * Logger<BR>
      */
    private static WEB3LogUtility log =
	    WEB3LogUtility.getInstance(WEB3OptionsExecuteReferenceRequest.class);

    /**
     * PTYPE<BR>
     */
    public final static String PTYPE = "options_executeReference";

    /**
     * SerialVersionUID<BR>
     */
    public final static long serialVersionUID = 200406111551L;

    /**
     * (照会区分)<BR>
     * <BR>
     * 0：注文約定照会<BR>
     * 1：訂正取消一覧（訂正取消可能なもののみ表示）<BR>
     */
    public String referenceType;

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
     * (約定状態区分)<BR>
     * <BR>
     *(検索条件指定時に使用)<BR>
     * <BR>
     * null：指定なし<BR>
     * 0：未約定<BR>
     * 1：一部成立<BR>
     * 2：全部成立<BR>
     * <BR>
     *※約定状態指定の場合、設定される<BR>
     */
    public String execType;
    
    /**
     * (発注日)<BR>
     * <BR>
     * (検索条件指定時に使用)<BR>
     * <BR>
     * null：指定なし<BR>
     * 発注日<BR>
     * <BR>
     * ※発注日指定の場合、設定される<BR>
     */
    public Date orderBizDate;
    
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
     * (発注条件区分)<BR>
     * (検索条件指定時に使用) <BR>
     * <BR>
     * 0：指定なし <BR>
     * 1：逆指値 <BR>
     * 2：W指値 <BR>
     * <BR>
     * ※発注条件区分指定の場合、設定される<BR>
     */
    public String orderCondType;
    
    /**
     * (株価指数先物オプションソートキー)<BR>
     * <BR>
     * 対象項目：銘柄コード、取引市場、取引区分、注文時間、発注日、注文有効期限<BR>
     */
    public WEB3FuturesOptionsSortKey[] futOpSortKeys;

    /**
     * (要求ページ番号)<BR>
     * <BR>
     * 表示させたいページ位置を指定　@※先頭ページを"1"とする<BR>
     */
    public String pageIndex;

    /**
     * (ページ内表示行数)<BR>
     * <BR>
     * １ページ内に表示させたい行数を指定<BR>
     */
    public String pageSize;

    /**
     * @@roseuid 40C075390251
     */
    public WEB3OptionsExecuteReferenceRequest()
    {

    }
    /**
     * 当リクエストデータの整合性チェックを行う。<BR>
     * （ただし、当クラス内で簡潔する簡易チェックのみとする。）<BR>
     * <BR>
     * １）　@照会区分チェック<BR>
     * 　@１－１）this.照会区分がnullの値であれば例外をスローする。<BR>
     *      class: WEB3BusinessLayerException<BR>
     *      tag:   BUSINESS_ERROR_00081<BR>
     * 　@１－２）this.照会区分が以下の値以外の場合例外をスローする。<BR>
     * 　@　@　@　@・”0：注文約定照会”、<BR>
     * 　@　@　@　@・”1：訂正取消一覧（訂正取消可能なもののみ表示）”<BR>
     *      class: WEB3BusinessLayerException<BR>
     *      tag:   BUSINESS_ERROR_00082<BR>
     * <BR>
     * ２）　@約定状態区分チェック <BR>
     *      this.約定状態区分≠null かつ、<BR> 
     *      this.約定状態区分が以下の値以外の場合例外をスローする。<BR> 
     *      ・”0：未約定”<BR>
     *      ・”1：一部成立”<BR>
     *      ・”2：全部成立”<BR>
     *      class: WEB3BusinessLayerException<BR>
     *      tag:   BUSINESS_ERROR_00626<BR>
     * <BR>
     * ３）　@株価指数先物オプションソートキーチェック<BR><BR>
     * 　@３－１）this.株価指数先物オプションソートキーが<BR>
     * 　@　@　@　@nullの値であれば例外をスローする。<BR>
     *          class: WEB3BusinessLayerException<BR>
     *          tag:   BUSINESS_ERROR_00231<BR>
     * 　@３－２）this.株価指数先物オプションソートキーの要素数が<BR>
     * 　@　@　@　@０であれば例外をスローする。<BR>
     *          class: WEB3BusinessLayerException<BR>
     *          tag:   BUSINESS_ERROR_00232<BR>
     * 　@３－３）this.株価指数先物オプションソートキーの要素数分<BR>
     * 　@　@　@　@繰り返してチェックを行う。<BR>
     * 　@　@３－３－１）ソートキー.キー項目がnullの値であれば例外をスローする。<BR>
     *          class: WEB3BusinessLayerException<BR>
     *          tag:   BUSINESS_ERROR_00085<BR>
     * 　@　@３－３－２）ソートキー.キー項目に以下の項目名以外の値が<BR>
     * 　@　@　@　@　@　@　@存在したら例外をスローする。<BR>
     * 　@　@　@　@・銘柄コード<BR>
     * 　@　@　@　@・取引市場<BR>
     * 　@　@　@　@・取引区分<BR>
     * 　@　@　@　@・注文時間<BR>
     * 　@　@　@　@・発注日<BR>
     * 　@　@　@　@・注文有効期限<BR>
     *          class: WEB3BusinessLayerException<BR>
     *          tag:   BUSINESS_ERROR_00086<BR>     
     * 　@　@３－３－３）ソートキー.昇順／降順がnullの値であれば例外をスローする。<BR>
     *          class: WEB3BusinessLayerException<BR>
     *          tag:   BUSINESS_ERROR_00087<BR>
     * 　@　@３－３－４）ソートキー.昇順／降順が以下の値以外の場合例外をスローする。<BR>
     * 　@　@　@・”A：昇順”<BR>
     * 　@　@　@・”D：降順”<BR>
     *          class: WEB3BusinessLayerException<BR>
     *          tag:   BUSINESS_ERROR_00088<BR>
     * <BR>
     * ４）　@要求ページ番号チェック<BR>
     * 　@４－１）this.要求ページ番号がnullの値であれば例外をスローする。<BR>
     *          class: WEB3BusinessLayerException<BR>
     *          tag:   BUSINESS_ERROR_00089<BR>
     * 　@４－２）this.要求ページ番号が数字以外の値であれば例外をスローする。<BR>
     *          class: WEB3BusinessLayerException<BR>
     *          tag:   BUSINESS_ERROR_00090<BR>
     * <BR>
     * ５）　@ページ内表示行数チェック<BR>
     * 　@５－１）this.ページ内表示行数がnullの値であれば例外をスローする。<BR>
     *          class: WEB3BusinessLayerException<BR>
     *          tag:   BUSINESS_ERROR_00091<BR>
     * 　@５－２）this.ページ内表示行数が数字以外の値であれば例外をスローする。<BR>
     *          class: WEB3BusinessLayerException<BR>
     *          tag:   BUSINESS_ERROR_00092<BR>
     * <BR>
     * ６）　@銘柄設定チェック<BR>
     *   ６－１）以下の全てのリクエスト項目を設定している場合、例外をスローする。<BR>
　@　@ *        　@(銘柄コードと銘柄特定項目がどちらも設定されている場合)<BR>
　@　@ *      ・銘柄コード<BR>
　@　@ *      ・取引市場<BR>
　@　@ *      ・指数種別<BR>
　@　@ *      ・限月<BR>
　@　@ *      ・オプション商品区分<BR>
　@　@ *      ・行使価格<BR>
     *      class: WEB3BusinessLayerException<BR>
     *      tag:   BUSINESS_ERROR_00830<BR>
     * 　@６－２）銘柄特定項目による銘柄指定の場合<BR>
　@　@ *　@　@  取引市場、指数種別、限月、オプション商品区分、行使価格の全てが設定されていなければ、例外をスローする。<BR>
　@　@ *      class: WEB3BusinessLayerException<BR>
     *      tag:   BUSINESS_ERROR_00830<BR>
     * <BR>
     * ７）　@発注条件区分チェック <BR>
     * 　@this.発注条件区分≠nullかつ、 <BR>
     * 　@this.発注条件区分が以下の値以外の場合例外をスローする。 <BR>
     * 　@　@　@・”0：指定なし” <BR>
     * 　@　@　@・”1：逆指値” <BR>
     * 　@　@　@・”2：W指値”<BR>
     *      class: WEB3BusinessLayerException<BR>
     *      tag:   BUSINESS_ERROR_00212<BR>
     * @@throws WEB3BaseException
     * @@roseuid 406A15B301E6
     */
    public void validate() throws WEB3BaseException
    {
		final String STR_METHOD_NAME = "validate()";
		log.entering(STR_METHOD_NAME);
		
        // １）　@照会区分チェック<BR>
        // 　@１－１）this.照会区分がnullの値であれば例外をスローする。<BR>
        if (WEB3StringTypeUtility.isEmpty(this.referenceType))
        {
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_00081, 
                getClass().getName() + "." + STR_METHOD_NAME,
                "照会区分がnullの値である。");
        }

        // 　@１－２）this.照会区分が以下の値以外の場合例外をスローする。<BR>
        // 　@　@　@　@・”0：注文約定照会”、<BR>
        // 　@　@　@　@・”1：訂正取消一覧（訂正取消可能なもののみ表示）”<BR>
        if (!WEB3IfoReferenceTypeDef.ORDER_PROMISE.equals(this.referenceType) 
            && !WEB3IfoReferenceTypeDef.CORRECTION_LIST.equals(this.referenceType))
        {
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_00082, 
                getClass().getName() + "." + STR_METHOD_NAME,
                "照会区分が“0:注文照会”、“1:取消一覧(取消可能なもののみ表示)”以外である。");
        }

        // ２）　@約定状態区分チェック <BR>
        //      this.約定状態区分≠null かつ、<BR> 
        //      this.約定状態区分が以下の値以外の場合例外をスローする。<BR> 
        //      ・”0：未約定”<BR>
        //      ・”1：一部成立”<BR>
        //      ・”2：全部成立”<BR>
        if (WEB3StringTypeUtility.isNotEmpty(this.execType)
            && !WEB3IfoExecStatusTypeDef.EXEC_TYPE_NOT_PROMISE.equals(this.execType)
            && !WEB3IfoExecStatusTypeDef.EXEC_TYPE_ONE_COMPLETE.equals(this.execType)
            && !WEB3IfoExecStatusTypeDef.EXEC_TYPE_ALL_COMPLETE.equals(this.execType))
        {
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_00626, 
                getClass().getName() + "." + STR_METHOD_NAME,
                "約定状態区分の値が存在しないコード値です。");
        }

        // ３）　@株価指数先物オプションソートキーチェック<BR><BR>
        // 　@３－１）this.株価指数先物オプションソートキーが<BR>
        // 　@　@　@　@nullの値であれば例外をスローする。<BR>
        if (this.futOpSortKeys == null)
        {
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_00231, 
                getClass().getName() + "." + STR_METHOD_NAME,
                "ソートキーがnullの値である。");
        }

        int l_intOpSortKeysLength = this.futOpSortKeys.length;

        // 　@３－２）this.株価指数先物オプションソートキーの要素数が<BR>
        // 　@　@　@　@０であれば例外をスローする。<BR>
        if (l_intOpSortKeysLength == 0)
        {
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_00232, 
                getClass().getName() + "." + STR_METHOD_NAME,
                "ソートキーの要素数が０である。");
        }

        // 　@３－３）this.株価指数先物オプションソートキーの要素数分<BR>
        // 　@　@　@　@繰り返してチェックを行う。<BR>
        int i;
        for (i = 0; i < l_intOpSortKeysLength; i++)
        {
            // 　@　@３－３－１）ソートキー.キー項目がnullの値であれば例外をスローする。<BR>
            if (WEB3StringTypeUtility.isEmpty(this.futOpSortKeys[i].keyItem))
            {
                throw new WEB3BusinessLayerException(
                    WEB3ErrorCatalog.BUSINESS_ERROR_00085, 
                    getClass().getName() + "." + STR_METHOD_NAME,
                    "キー項目がnullの値である。");
            }

            // 　@　@３－３－２）ソートキー.キー項目に以下の項目名以外の値が<BR>
            // 　@　@　@　@　@　@　@存在したら例外をスローする。<BR>
            // 　@　@　@・銘柄コード<BR>
            // 　@　@　@・取引市場<BR>
            // 　@　@　@・取引区分<BR>
            // 　@　@　@・注文時間<BR>
            // 　@　@　@・発注日 <BR>
            // 　@　@　@・注文有効期限<BR>
            if (!WEB3IfoKeyItemDef.PRODUCTCODE.equals(this.futOpSortKeys[i].keyItem)
                && !WEB3IfoKeyItemDef.TRADE_MARKET.equals(this.futOpSortKeys[i].keyItem)
                && !WEB3IfoKeyItemDef.TRADE_DIVISION.equals(this.futOpSortKeys[i].keyItem)
                && !WEB3IfoKeyItemDef.ORDER_TIME.equals(this.futOpSortKeys[i].keyItem)
                && !WEB3IfoKeyItemDef.BIZ_DATE.equals(this.futOpSortKeys[i].keyItem)
                && !WEB3IfoKeyItemDef.ORDER_EXPIRATION_DATE.equals(this.futOpSortKeys[i].keyItem))
            {
                throw new WEB3BusinessLayerException(
                    WEB3ErrorCatalog.BUSINESS_ERROR_00086, 
                    getClass().getName() + "." + STR_METHOD_NAME,
                    "キー項目に銘柄名ﾞ、取引市場、取引区分、注文時間、注文有効期限の項目名以外の値が存在しています。");
            }

            // 　@　@３－３－３）ソートキー.昇順／降順がnullの値であれば例外をスローする。<BR>
            if (WEB3StringTypeUtility.isEmpty(this.futOpSortKeys[i].ascDesc))
            {
                throw new WEB3BusinessLayerException(
                    WEB3ErrorCatalog.BUSINESS_ERROR_00087, 
                    getClass().getName() + "." + STR_METHOD_NAME,
                    "昇順／降順がnullの値である。");
            }

            // 　@　@３－３－４）ソートキー.昇順／降順が以下の値以外の場合例外をスローする。<BR>
            // 　@　@　@・”A：昇順”<BR>
            // 　@　@　@・”D：降順”<BR>
            if (!WEB3AscDescDef.ASC.equals(this.futOpSortKeys[i].ascDesc) && !WEB3AscDescDef.DESC.equals(this.futOpSortKeys[i].ascDesc))
            {
                throw new WEB3BusinessLayerException(
                    WEB3ErrorCatalog.BUSINESS_ERROR_00088, 
                    getClass().getName() + "." + STR_METHOD_NAME,
                    "昇順／降順が”A：昇順”、”D：降順”以外の値です。");
            }
        }

        // ４）　@要求ページ番号チェック<BR>
        // 　@４－１）this.要求ページ番号がnullの値であれば例外をスローする。<BR>
        if (WEB3StringTypeUtility.isEmpty(this.pageIndex))
        {
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_00089, 
                getClass().getName() + "." + STR_METHOD_NAME,
                "要求ページ番号がnullの値である。");
        }

        // 　@４－２）this.要求ページ番号が数字以外の値であれば例外をスローする。<BR>
        if (!WEB3StringTypeUtility.isNumber(this.pageIndex))
        {
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_00090, 
                getClass().getName() + "." + STR_METHOD_NAME,
                "要求ページ番号が数字以外の値である。");
        }

        // ５）　@ページ内表示行数チェック<BR>
        // 　@５－１）this.ページ内表示行数がnullの値であれば例外をスローする。<BR>
        if (WEB3StringTypeUtility.isEmpty(this.pageSize))
        {
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_00091, 
                getClass().getName() + "." + STR_METHOD_NAME,
                "ページ内表示行数が０、または未指定の場合。");
        }

        // 　@５－２）this.ページ内表示行数が数字以外の値であれば例外をスローする。<BR>
        if (!WEB3StringTypeUtility.isNumber(this.pageSize))
        {
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_00092, 
                getClass().getName() + "." + STR_METHOD_NAME,
                "ページ内表示行数が数字以外の値です。");
        }
        
		//６） 銘柄設定チェック<BR>
		//６－１）以下の全てのリクエスト項目を設定している場合、例外をスローする。<BR>
		//    　@(銘柄コードと銘柄特定項目がどちらも設定されている場合)<BR>               
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
                
		// ６－２）銘柄選択時に取引市場,指数種別,限月,オプション商品区分,行使価格のいずれかの項目が              
		// 設定されていない場合、例外をスローする。             
		if((marketCode==null)
			&&(targetProductCode==null)
			&&(delivaryMonth==null)                
			&&(opProductType==null)
			&&(strikePrice==null))          
		{              

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

        //７）　@発注条件区分チェック
        //　@this.発注条件区分≠nullかつ、
        //　@this.発注条件区分が以下の値以外の場合例外をスローする。
        //　@　@　@・”0：指定なし”
        //　@　@　@・”1：逆指値”
        //　@　@　@・”2：W指値”
        if (orderCondType != null
            && !WEB3OrderingConditionDef.DEFAULT.equals(orderCondType)
            && !WEB3OrderingConditionDef.STOP_LIMIT_PRICE.equals(orderCondType)
            && !WEB3OrderingConditionDef.W_LIMIT_PRICE.equals(orderCondType))
        {
            log.debug("発注条件区分の値が存在しないコード値です。");
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_00212,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                "発注条件区分の値が存在しないコード値です。");
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
        return new WEB3OptionsExecuteReferenceResponse(this);
    }
}
@
