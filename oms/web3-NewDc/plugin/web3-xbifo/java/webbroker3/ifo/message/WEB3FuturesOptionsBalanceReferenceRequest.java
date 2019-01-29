head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.16.03.17.59;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	6004d80209d0226;
filename	WEB3FuturesOptionsBalanceReferenceRequest.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : 株価指数先物オプション残高照会リクエストクラス(WEB3FuturesOptionsBalanceReferenceRequest.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2004/12/29 呉艶飛 新規作成         
*/
package webbroker3.ifo.message;

import webbroker3.common.WEB3BaseException;
import webbroker3.common.WEB3BusinessLayerException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.common.define.WEB3AscDescDef;
import webbroker3.common.define.WEB3FuturesOptionDivDef;
import webbroker3.common.message.WEB3GenRequest;
import webbroker3.common.message.WEB3GenResponse;
import webbroker3.ifo.define.WEB3IfoKeyItemDef;
import webbroker3.ifo.define.WEB3IfoSettlementStateDef;
import webbroker3.util.WEB3LogUtility;
import webbroker3.util.WEB3StringTypeUtility;


/**
 * (株価指数先物オプション残高照会リクエスト)<BR>
 * 株価指数先物オプション残高照会リクエストクラス<BR>
 * @@author 呉艶飛
 * @@version 1.0  
 */
public class WEB3FuturesOptionsBalanceReferenceRequest extends WEB3GenRequest
{
    /** logger. */
    private static WEB3LogUtility log =
        WEB3LogUtility.getInstance(
    WEB3FuturesOptionsBalanceReferenceRequest.class);
    
    /**
     * SerialVersionUID<BR>
     */
    public static final long serialVersionUID = 2004012291504L;

    /**
     * PTYPE<BR>
     */
    public static final String PTYPE = "futuresOptions_balanceReference";
    
    /**
     * 先物／オプション区分<BR>
     * 　@1：先物 2：オプション<BR>
     */
    public String fuOpDiv;
    
    /**
     * (銘柄コード)<BR>
     * (検索条件指定時に使用)<BR>
     * <BR>
     * null：指定なし<BR>
     * 先物OP銘柄コード<BR>
     */
    public String productCode = null;
    
    /**
     * (決済状態区分)<BR>
     * (検索条件指定時に使用)<BR>
     * <BR>
     * null：指定なし <BR>
     * 1：未決済<BR>
     * 2：決済中<BR>
     */
    public String settlementState = null;

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
     * (株価指数先物オプションソートキー)<BR>
     * 対象項目：銘柄コード、建日、建区分、損益、損益（諸経費込）<BR>
     */
    public WEB3FuturesOptionsSortKey[] sortKeys;
    
    /**
     * (要求ページ番号)<BR>
     * 表示させたいページ位置を指定　@<BR>
     * ※先頭ページを"1"とする<BR>
     */
    public String pageIndex;
    
    /**
     * (ページ内表示行数)<BR>
     * １ページ内に表示させたい行数を指定<BR>
     */
    public String pageSize;
    
    /**
     * 当リクエストデータの整合性チェックを行う。<BR>
     * （ただし、当クラス内で完結する簡易チェックのみとする。）<BR>
     * <BR>
     * １）　@先物／オプション区分のチェック<BR>
     * 　@１－１）nullの場合、例外とする。<BR>
     *     class: WEB3BusinessLayerException<BR>
     *     tag:   BUSINESS_ERROR_01736<BR>
     * 　@１－２）以下の項目以外が存在した場合、例外とする。<BR>
     * 　@　@　@・1(先物)<BR>
     * 　@　@　@・2(オプション)<BR>
     *     class: WEB3BusinessLayerException<BR>
     *     tag:   BUSINESS_ERROR_01737<BR>
     * <BR>
     * ２）　@決済状態区分のチェック<BR>
     * 　@　@　@以下の状態以外が存在した場合、例外とする。<BR>
     * 　@　@　@・null(指定なし) <BR>
     *       ・1(未決済)<BR>
     *       ・2(決済中)<BR>
     *      class: WEB3BusinessLayerException<BR>
     *      tag:   BUSINESS_ERROR_00233<BR>
     * <BR>
     * ３）　@株価指数先物オプションソートキーチェック <BR>
     * 　@３－１）this.株価指数先物オプションソートキーが <BR>
     * 　@　@　@　@nullの値であれば例外をスローする。<BR>
     *          class: WEB3BusinessLayerException<BR>
     *          tag:   BUSINESS_ERROR_00231<BR>
     * 　@３－２）this.株価指数先物オプションソートキーの要素数が<BR> 
     * 　@　@　@　@０であれば例外をスローする。<BR>
     *          class: WEB3BusinessLayerException<BR>
     *          tag:   BUSINESS_ERROR_00232<BR>
     * 　@３－３）this.株価指数先物オプションソートキーの要素数分<BR> 
     * 　@　@　@　@繰り返してチェックを行う。 <BR>
     * 　@　@３－３－１）株価指数先物オプションソートキー.キー項目がnullの値であれば例外をスローする。<BR> 
     *          class: WEB3BusinessLayerException<BR>
     *          tag:   BUSINESS_ERROR_00085<BR>
     * 　@　@３－３－２）株価指数先物オプションソートキー.キー項目に以下の項目名以外の値が<BR> 
     * 　@　@　@　@　@　@　@存在したら例外をスローする。<BR>
     * 　@　@　@・先物OP残高照会明細.銘柄コード<BR>
     * 　@　@　@・先物OP残高照会明細.建区分<BR>
     * 　@　@　@・先物OP残高照会明細.建日<BR>
     * 　@　@　@・先物OP残高照会明細.損益<BR>
     * 　@　@　@・先物OP残高照会明細.損益(諸経費込)<BR>
     *      class: WEB3BusinessLayerException<BR>
     *      tag:   BUSINESS_ERROR_00086<BR>
     * 　@　@３－３－３）株価指数先物オプションソートキー.昇順／降順がnullの値であれば例外をスローする。<BR>
     *          class: WEB3BusinessLayerException<BR>
     *          tag:   BUSINESS_ERROR_00087<BR>
     * 　@　@３－３－４）株価指数先物オプションソートキー.昇順／降順が以下の値以外であれば例外をスローする。 <BR>
     * 　@　@　@・”A：昇順” <BR>
     * 　@　@　@・”D：降順”<BR>
     *      class: WEB3BusinessLayerException<BR>
     *      tag:   BUSINESS_ERROR_00088<BR>
     * <BR>
     * ４）　@要求ページ番号のチェック<BR>
     * リクエストデータ．要求ページ番号が未指定の場合、<BR>
     * リクエストデータ．要求ページ番号に「１」をセットする。<BR>
     * <BR>
     * ５）　@ページ内表示行数のチェック<BR>
     * リクエストデータ．ページ内表示行数が０、または未指定の場合、<BR>
     * 例外とする。<BR>
     *      class: WEB3BusinessLayerException<BR>
     *      tag:   BUSINESS_ERROR_00091<BR>
     * <BR>
     * ６）　@銘柄設定チェック<BR>
     *   ６－１）以下の全てのリクエスト項目を設定している場合、例外をスローする。<BR>
     *        　@(銘柄コードと銘柄特定項目がどちらも設定されている場合)<BR>
     *      先物の場合(先物/オプション区分＝先物)<BR>
     *          ・銘柄コード<BR>
     *          ・取引市場<BR>
     *          ・指数種別<BR>
     *      オプションの場合(先物/オプション区分＝オプション)<BR>
     *          ・銘柄コード<BR>
     *          ・取引市場<BR>
     *          ・指数種別<BR>
     *          ・限月<BR>
     *          ・オプション商品区分<BR>
     *          ・行使価格<BR>
     *      class: WEB3BusinessLayerException<BR>
     *      tag:   BUSINESS_ERROR_00830<BR>
     * 　@６－２）銘柄特定項目による銘柄指定の場合<BR>
     *      ・先物の場合(先物/オプション区分＝先物)<BR>
     *　@　@      取引市場、指数種別、限月の全てが設定されていなければ、例外をスローする。<BR>
     *      ・オプションの場合(先物/オプション区分＝オプション)<BR>
     *          取引市場、指数種別、限月、オプション商品区分、行使価格の全てが設定されていなければ、例外をスローする。<BR>
     *      class: WEB3BusinessLayerException<BR>
     *      tag:   BUSINESS_ERROR_00830<BR>
     * @@throws WEB3BaseException
     * @@roseuid 41AAB56A00E0
     */
    public void validate() throws WEB3BaseException 
    {
        final String STR_METHOD_NAME = "validate()";
        log.entering(STR_METHOD_NAME);

         //１）　@先物／オプション区分のチェック
        if (WEB3StringTypeUtility.isEmpty(this.fuOpDiv))
        {
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_01736,
                getClass().getName() + "." + STR_METHOD_NAME);
        }
        //１－２）以下の項目以外が存在した場合、例外とする。
        if (!WEB3FuturesOptionDivDef.FUTURES.equals(this.fuOpDiv) && !WEB3FuturesOptionDivDef.OPTION.equals(this.fuOpDiv))
        {
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_01737,
                getClass().getName() + "." + STR_METHOD_NAME);
        }
        //２）　@決済状態区分のチェック以下の状態以外が存在した場合、例外とする。
        //null(指定なし) ・1(未決済)<BR>・2(決済中)
        if (this.settlementState != WEB3IfoSettlementStateDef.NOT_DESIGNATION
                && !WEB3IfoSettlementStateDef.HAVE_NOT_SETTLED.equals(this.settlementState)
                && !WEB3IfoSettlementStateDef.SETTLING.equals(this.settlementState))
        {
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_00233,
                getClass().getName() + "." + STR_METHOD_NAME);
        }
        //３）　@株価指数先物オプションソートキーチェック
        //３－１）this.株価指数先物オプションソートキーがnullの値であれば例外をスローする。
        if (sortKeys == null)
        {
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_00231,
                getClass().getName() + "." + STR_METHOD_NAME);
        }
        //３－２）this.株価指数先物オプションソートキーの要素数が０であれば例外をスローする。
        if (sortKeys.length == 0)
        {
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_00232,
                getClass().getName() + "." + STR_METHOD_NAME);
        }

        //３－３）ソートキーの配列の個数分、繰り返してチェックを行う。
        for (int i = 0; i < sortKeys.length; i++)
        {
            //３－３－１）株価指数先物オプションソートキー.キー項目がnullの値であれば例外をスローする。
            if (WEB3StringTypeUtility.isEmpty(sortKeys[i].keyItem))
            {
                throw new WEB3BusinessLayerException(
                    WEB3ErrorCatalog.BUSINESS_ERROR_00085,
                    getClass().getName() + "." + STR_METHOD_NAME);
            }
            //３－３－２）ソートキー.キー項目に以下の項目名以外の値が存在したら例外をスローする。 
            if (!WEB3IfoKeyItemDef.BR_PRODUCTCODE.equals(sortKeys[i].keyItem) 
                && !WEB3IfoKeyItemDef.CONTRACT_DIVISION.equals(sortKeys[i].keyItem)
                && !WEB3IfoKeyItemDef.OPEN_DATE.equals(sortKeys[i].keyItem)
                && !WEB3IfoKeyItemDef.INCOME.equals(sortKeys[i].keyItem)
                && !WEB3IfoKeyItemDef.INCOME_COST.equals(sortKeys[i].keyItem))
            {
                throw new WEB3BusinessLayerException(
                    WEB3ErrorCatalog.BUSINESS_ERROR_00086,
                    getClass().getName() + "." + STR_METHOD_NAME);
            }
            //３－３－３）ソートキー.昇順／降順がnullの値であれば例外をスローする。
            if (WEB3StringTypeUtility.isEmpty(sortKeys[i].ascDesc))
            {
                throw new WEB3BusinessLayerException(
                    WEB3ErrorCatalog.BUSINESS_ERROR_00087,
                    getClass().getName() + "." + STR_METHOD_NAME);
            }
            //３－３－４）ソートキー.昇順／降順が以下の値以外であれば例外をスローする。
            // A:昇順、D：降順
            if (!WEB3AscDescDef.ASC.equals(sortKeys[i].ascDesc)
                && !WEB3AscDescDef.DESC.equals(sortKeys[i].ascDesc))
            {
                throw new WEB3BusinessLayerException(
                    WEB3ErrorCatalog.BUSINESS_ERROR_00088,
                    getClass().getName() + "." + STR_METHOD_NAME);
            }
        }
        
        //４）　@要求ページ番号のチェック
        //リクエストデータ．要求ページ番号が未指定の場合、
        if (WEB3StringTypeUtility.isEmpty(this.pageIndex))
        {
            // リクエストデータ．要求ページ番号に「１」をセットする。
            this.pageIndex = "1";
        }
        
        // ５）　@要求ページ番号のチェック
        //リクエストデータ．ページ内表示行数が０、または未指定の場合、
        if (WEB3StringTypeUtility.isEmpty(this.pageSize) || "0".equals(this.pageSize))
        {
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_00091,
                getClass().getName() + "." + STR_METHOD_NAME);
        }
        
        //先物/オプション区分によりそれぞれの銘柄設定チェックを行う。        
        if (WEB3FuturesOptionDivDef.FUTURES.equals(fuOpDiv))
        {
            //６－１）銘柄コードと銘柄特定項目がどちらも設定されている場合は例外をスローする。
            if((productCode != null)
                && (marketCode != null)
                && (targetProductCode != null)
                && (delivaryMonth != null))
            {
                throw new WEB3BusinessLayerException(         
                    WEB3ErrorCatalog.BUSINESS_ERROR_00830,         
                    this.getClass().getName() + "." + STR_METHOD_NAME,        
                    "銘柄コードと銘柄特定項目がどちらも設定されています。");
            }
            // 　@６－２）銘柄特定項目による銘柄指定の場合
            //　@　@      取引市場、指数種別、限月の全てが設定されていなければ、例外をスローする。
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
        }
        else if (WEB3FuturesOptionDivDef.OPTION.equals(fuOpDiv))
        {
            //６－１）銘柄コードと銘柄特定項目がどちらも設定されている場合は例外をスローする。
            if((productCode != null)
                && (marketCode != null)
                && (targetProductCode != null)
                && (delivaryMonth != null)
                && (opProductType != null)
                && (strikePrice != null))
            {
                throw new WEB3BusinessLayerException(         
                    WEB3ErrorCatalog.BUSINESS_ERROR_00830,         
                    this.getClass().getName() + "." + STR_METHOD_NAME,        
                    "銘柄コードと銘柄特定項目がどちらも設定されています。");
            }
            // 　@６－２）銘柄特定項目による銘柄指定の場合
            //　@　@      取引市場、指数種別、限月の全てが設定されていなければ、例外をスローする。
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
        return new WEB3FuturesOptionsBalanceReferenceResponse(this);
    }
}
@
