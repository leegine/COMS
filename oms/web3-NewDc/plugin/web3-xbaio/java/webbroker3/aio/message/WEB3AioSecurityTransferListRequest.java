head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.16.05.12.21;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	8f04d80403d696d;
filename	WEB3AioSecurityTransferListRequest.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : 証券振替一覧リクエスト(WEB3AioSecurityTransferListRequest)
Author Name      : Daiwa Institute of Research
Revesion History : 2004/12/07 屈陽 (中訊) 新規作成   
*/

package webbroker3.aio.message;

import webbroker3.aio.define.WEB3AioDepositTypeDivDef;
import webbroker3.aio.define.WEB3AioMessageCommodityDef;
import webbroker3.aio.define.WEB3AioTransferSortkeyDef;
import webbroker3.common.WEB3BaseException;
import webbroker3.common.WEB3BusinessLayerException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.common.define.WEB3AscDescDef;
import webbroker3.common.message.WEB3GenRequest;
import webbroker3.common.message.WEB3GenResponse;
import webbroker3.util.WEB3LogUtility;
import webbroker3.util.WEB3StringTypeUtility;


/**
 * (証券振替一覧リクエスト)<BR>
 * 証券振替一覧リクエストクラス
 * 
 * @@author 屈陽(中訊)
 * @@version 1.0
 */
public class WEB3AioSecurityTransferListRequest extends WEB3GenRequest 
{
    /**
     * PTYPE<BR>
     */
    public static final String PTYPE = "aio_security_transfer_list";
    
    /**
     * serialVersionUID<BR>
     */
    public final static long serialVersionUID = 200412071004L;     
    
    /**
    * ログ出力ユーティリティ。
    */    
    private static WEB3LogUtility log =
        WEB3LogUtility.getInstance(WEB3AioSecurityTransferListRequest.class);
    
    /**
     * (商品タイプ)<BR>
     * 検索用の条件となる銘柄の商品タイプ<BR>
     * 銘柄コード指定無しの場合は、null<BR>
     * <BR>
     * 1： 株式<BR>
     * 2： 債券<BR>
     * 3： 投資信託<BR>
     */
    public String instrumentsType;
    
    /**
     * (銘柄コード)<BR>
     * 検索用の条件となる銘柄コード<BR>
     * 指定無しの場合は、null
     */
    public String productCode;
    
    /**
     * (預り区分)<BR>
     * 検索用の条件となる預り区分<BR>
     * <BR>
     * 0： 指定無し<BR>
     * 1： 保護<BR>
     * 2： 代用
     */
    public String depositDiv;
    
    /**
     * (要求ページ番号)<BR>
     * 表示したいページ番号
     */
    public String pageIndex;
    
    /**
     * (ページ内表示行数)<BR>
     * ページ内に表示する行数
     */
    public String pageSize;
    
    /**
     * (ソートキー)<BR>
     * 証券振替ソートキーオブジェクトの配列
     */
    public WEB3AioSecurityTransferSortKeyUnit[] sortKeys;
    
    /**
     * @@roseuid 41B031A0034B
     */
    public WEB3AioSecurityTransferListRequest() 
    {

    }
    
    /**
     * リクエストデータの整合性をチェックする。<BR>
     * <BR>
     * １）商品タイプ<BR>
     * <BR>
     *   リクエストデータ.商品タイプ != (null, 1, 2, 3)<BR>
     * <BR>
     *   の場合、例外をスローする。<BR>
     * <BR>
     *     class: WEB3BusinessLayerException<BR>
     *     tag:   BUSINESS_ERROR_01295<BR>
     * <BR>
     * ２）銘柄コード<BR>
     * <BR>
     *   リクエストデータ.銘柄コード != null and<BR>
     *   リクエストデータ.銘柄コード != 半角数字<BR>
     * <BR>
     *   の場合、例外をスローする。<BR>
     * <BR>
     *     class: WEB3BusinessLayerException<BR>
     *     tag:   BUSINESS_ERROR_00815<BR>
     * <BR>
     * ３）預り区分<BR>
     * <BR>
     *   リクエストデータ.預り区分 != (0, 1, 2)<BR>
     * <BR>
     *   の場合、例外をスローする。<BR>
     * <BR>
     *     class: WEB3BusinessLayerException<BR>
     *     tag:   BUSINESS_ERROR_01297<BR>
     * <BR>
     * ４）要求ページ番号<BR>
     * <BR>
     *   リクエストデータ.要求ページ番号 = null or <BR>
     *   リクエストデータ.要求ページ番号 <= 0 or<BR>
     *   リクエストデータ.要求ページ番号 != 半角数字 <BR>
     * <BR>
     *   の場合、例外をスローする。 <BR>
     * <BR>
     *     class: WEB3BusinessLayerException<BR>
     *     tag:   BUSINESS_ERROR_00089<BR>
     *     class: WEB3BusinessLayerException<BR>
     *     tag:   BUSINESS_ERROR_00616<BR>
     *     class: WEB3BusinessLayerException<BR>
     *     tag:   BUSINESS_ERROR_00090<BR>
     * <BR>
     * ５）ページ内表示行数<BR>
     * <BR>
     *   リクエストデータ.ページ内表示行数 = null or <BR>
     *   リクエストデータ.ページ内表示行数 <= 0 or<BR>
     *   リクエストデータ.ページ内表示行数 != 半角数字 <BR>
     * <BR>
     *   の場合、例外をスローする。 <BR>
     * <BR>
     *     class: WEB3BusinessLayerException<BR>
     *     tag:   BUSINESS_ERROR_00091<BR>
     *     class: WEB3BusinessLayerException<BR>
     *     tag:   BUSINESS_ERROR_00092<BR>
     * <BR>
     * ６）ソートキー<BR>
     *  <BR>
     * ６－１）<BR>
     * <BR>
     *   リクエストデータ.ソートキー = null<BR>
     * <BR>
     *   の場合、例外をスローする。 <BR>
     * <BR>
     *     class: WEB3BusinessLayerException<BR>
     *     tag:   BUSINESS_ERROR_00231<BR>
     * <BR>
     * ６－２）<BR>
     * <BR>
     *   リクエストデータ.ソートキーの要素数 = 0<BR>
     *  <BR>
     *   の場合、例外をスローする。 <BR>
     * <BR>
     *     class: WEB3BusinessLayerException<BR>
     *     tag:   BUSINESS_ERROR_00232<BR>
     * <BR>
     * ６－３）ソートキーの要素数分繰り返してチェックを行う。 <BR>
     * <BR>
     * ６－３－１）<BR>
     * <BR>
     *   ソートキー.キー項目 = null<BR>
     * <BR>
     *   の場合、例外をスローする。 <BR>
     * <BR>
     *     class: WEB3BusinessLayerException<BR>
     *     tag:   BUSINESS_ERROR_00085<BR>
     * <BR>
     * ６－３－２）<BR>
     * <BR>
     *   ソートキー.キー項目に以下の項目名以外の値があった場合、例外をスローする。<BR> 
     * <BR>
     *     ・商品タイプ<BR>
     *     ・銘柄コード<BR>
     *     ・口座区分<BR>
     *     ・数量<BR>
     *     ・評価額<BR>
     *     ・預り区分<BR>
     * <BR>
     *     class: WEB3BusinessLayerException<BR>
     *     tag:   BUSINESS_ERROR_00086<BR>
     * <BR>
     * ６－３－３）<BR>
     * <BR>
     *   ソートキー.昇順／降順 = null<BR>
     * <BR>
     *   の場合、例外をスローする。 <BR>
     * <BR>
     *     class: WEB3BusinessLayerException<BR>
     *     tag:   BUSINESS_ERROR_00087<BR>
     * <BR>
     * ６－３－４）<BR>
     * <BR>
     *   ソートキー.昇順／降順が以下の値以外の場合、例外をスローする。 <BR>
     * <BR>
     *     ・”A：昇順” <BR>
     *     ・”D：降順” 
     *     class: WEB3BusinessLayerException<BR>
     *     tag:   BUSINESS_ERROR_00088<BR>
     * @@throws WEB3BaseException
     * @@roseuid 4149458F02DF
     */
    public void validate() throws WEB3BaseException
    {
        String l_strMethodName = "validate()"; 
        log.entering(l_strMethodName);
        
        //１）商品タイプ
        //  リクエストデータ.商品タイプ != (null, 1, 2, 3)
        //  の場合、例外をスローする。
        //    class: WEB3BusinessLayerException
        //    tag:   BUSINESS_ERROR_01295
        if (!(this.instrumentsType == null || 
            WEB3AioMessageCommodityDef.EQUITY.equals(this.instrumentsType) ||
            WEB3AioMessageCommodityDef.MUTUAL_FUND.equals(this.instrumentsType) ||
            WEB3AioMessageCommodityDef.BOND.equals(this.instrumentsType))) 
        {
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_01295,
                this.getClass().getName() + "." + l_strMethodName,
                "リクエストデータ.商品タイプ != (null, 1, 2, 3)," +
                "リクエストデータ.商品タイプ = " + this.instrumentsType);               
        }
       
        //２）銘柄コード
        //  リクエストデータ.銘柄コード != null and
        //  リクエストデータ.銘柄コード != 半角数字
        //  の場合、例外をスローする。
        //    class: WEB3BusinessLayerException
        //    tag:   BUSINESS_ERROR_00815
        if (WEB3StringTypeUtility.isNotEmpty(this.productCode) && 
            (!WEB3StringTypeUtility.isNumber(this.productCode)))
        {
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_00815,
                this.getClass().getName() + "." + l_strMethodName,
                "リクエストデータ.銘柄コード != null and " +
                "リクエストデータ.銘柄コード != 半角数字," +
                "リクエストデータ.銘柄コード = " + this.productCode);                    
        }
        
        //３）預り区分
        //  リクエストデータ.預り区分 != (0, 1, 2)
        //  の場合、例外をスローする。
        //    class: WEB3BusinessLayerException
        //    tag:   BUSINESS_ERROR_01297
        if (!(WEB3AioDepositTypeDivDef.DEFAULT.equals(this.depositDiv) ||
            WEB3AioDepositTypeDivDef.SAFE_DEPOSIT.equals(this.depositDiv) ||
            WEB3AioDepositTypeDivDef.COLLATERAL_SECURITY.equals(this.depositDiv)))
        {
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_01297,
                this.getClass().getName() + "." + l_strMethodName,
                "リクエストデータ.預り区分 != (0, 1, 2)," +
                "リクエストデータ.預り区分 = " + this.depositDiv);                    
            
        }
        
        //４）要求ページ番号
        //  リクエストデータ.要求ページ番号 = null or 
        //  リクエストデータ.要求ページ番号 <= 0 or
        //  リクエストデータ.要求ページ番号 != 半角数字 
        //  の場合、例外をスローする。 
        //    class: WEB3BusinessLayerException
        //    tag:   BUSINESS_ERROR_00089
        //    class: WEB3BusinessLayerException
        //    tag:   BUSINESS_ERROR_00616
        //    class: WEB3BusinessLayerException
        //    tag:   BUSINESS_ERROR_00090
        if (WEB3StringTypeUtility.isEmpty(this.pageIndex))
        {
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_00089,
                this.getClass().getName() + "." + l_strMethodName,
                "リクエストデータ.要求ページ番号 = null");             
        }        
        else if (!WEB3StringTypeUtility.isNumber(this.pageIndex))
        {
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_00090,
                this.getClass().getName() + "." + l_strMethodName,
                "要求ページ番号が数字以外の値である, " +
                "要求ページ番号 = " + this.pageIndex);            
        }
        else if (Double.parseDouble(this.pageIndex) <= 0)
        {
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_00616,
                this.getClass().getName() + "." + l_strMethodName,
                "リクエストデータ.要求ページ番号 <= 0," +
                "リクエストデータ.要求ページ番号 = " + this.pageIndex);             
        }
        
        //５）ページ内表示行数
        //  リクエストデータ.ページ内表示行数 = null or 
        //  リクエストデータ.ページ内表示行数 <= 0 or
        //  リクエストデータ.ページ内表示行数 != 半角数字 
        //  の場合、例外をスローする。 
        //    class: WEB3BusinessLayerException
        //    tag:   BUSINESS_ERROR_00091
        //    class: WEB3BusinessLayerException
        //    tag:   BUSINESS_ERROR_00092
        if (WEB3StringTypeUtility.isEmpty(this.pageIndex))
        {
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_00091,
                this.getClass().getName() + "." + l_strMethodName,
                "リクエストデータ.ページ内表示行数, " +
                "リクエストデータ.ページ内表示行数 = " + this.pageSize);             
        }
        else if (!WEB3StringTypeUtility.isNumber(this.pageSize))
        {
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_00092,
                this.getClass().getName() + "." + l_strMethodName,
                "ページ内表示行数 != 半角数字," +
                "ページ内表示行数 = " + this.pageSize);             
        }
        else if (Double.parseDouble(this.pageSize) <= 0)
        {
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_00091,
                this.getClass().getName() + "." + l_strMethodName,
                "リクエストデータ.ページ内表示行数, " +
                "リクエストデータ.ページ内表示行数 = " + this.pageSize);   
        }

        //６）ソートキー
        //６－１）
        //  リクエストデータ.ソートキー = null
        //  の場合、例外をスローする。 
        //    class: WEB3BusinessLayerException
        //    tag:   BUSINESS_ERROR_00231
        if (this.sortKeys == null)
        {
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_00231,
                this.getClass().getName() + "." + l_strMethodName,
                "リクエストデータ.ソートキー = null");                  
        }

        //６－２）
        //  リクエストデータ.ソートキーの要素数 = 0
        //  の場合、例外をスローする。 
        //    class: WEB3BusinessLayerException
        //    tag:   BUSINESS_ERROR_00232
        if (this.sortKeys.length == 0)
        {
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_00232,
                this.getClass().getName() + "." + l_strMethodName,
                "リクエストデータ.ソートキーの要素数 = 0");                  
            
        }

        //６－３）ソートキーの要素数分繰り返してチェックを行う。         
        //６－３－１）
        //  ソートキー.キー項目 = null
        //  の場合、例外をスローする。 
        //    class: WEB3BusinessLayerException
        //    tag:   BUSINESS_ERROR_00085
        for (int i = 0; i < this.sortKeys.length; i++)
        {
            if (WEB3StringTypeUtility.isEmpty(this.sortKeys[i].keyItem))
            {
                throw new WEB3BusinessLayerException(
                    WEB3ErrorCatalog.BUSINESS_ERROR_00085,
                    this.getClass().getName() + "." + l_strMethodName,
                    "ソートキー.キー項目[" + i + "] = null");    
            }            
        }
        
        //６－３－２）
        //  ソートキー.キー項目に以下の項目名以外の値があった場合、例外をスローする。 
        //商品タイプ
        //銘柄コード
        //口座区分
        //数量
        //評価額
        //預り区分
        //    class: WEB3BusinessLayerException
        //    tag:   BUSINESS_ERROR_00086
        for (int i = 0; i < this.sortKeys.length; i++)
        {
            if (!(WEB3AioTransferSortkeyDef.INSTRUMENTS_TYPE.equals(this.sortKeys[i].keyItem) || 
                WEB3AioTransferSortkeyDef.PRODUCT_CODE.equals(this.sortKeys[i].keyItem) ||
                WEB3AioTransferSortkeyDef.TAX_TYPE.equals(this.sortKeys[i].keyItem) ||
                WEB3AioTransferSortkeyDef.CHANGE_POSS_QUANTITY.equals(this.sortKeys[i].keyItem) ||
                WEB3AioTransferSortkeyDef.MARKET_VALUE.equals(this.sortKeys[i].keyItem) ||
                WEB3AioTransferSortkeyDef.DEPOSIT_DIV.equals(this.sortKeys[i].keyItem)))
            {
                throw new WEB3BusinessLayerException(
                    WEB3ErrorCatalog.BUSINESS_ERROR_00086,
                    this.getClass().getName() + "." + l_strMethodName,
                    "ソートキー.キー項目に以下の項目名:" +
                    " [商品タイプ], [銘柄コード], [口座区分], [数量], [評価額], [預り区分]" +
                    "の項目名以外の値があった場合, ソートキー.キー項目[" + i + "] = "
                    + this.sortKeys[i].keyItem);                        
            }
        }
                        
        //６－３－３）
        //  ソートキー.昇順／降順 = null
        //  の場合、例外をスローする。 
        //    class: WEB3BusinessLayerException
        //    tag:   BUSINESS_ERROR_00087
        for (int i = 0; i < this.sortKeys.length; i++)
        {
            if (WEB3StringTypeUtility.isEmpty(this.sortKeys[i].ascDesc))
            {
                throw new WEB3BusinessLayerException(
                    WEB3ErrorCatalog.BUSINESS_ERROR_00087,
                    this.getClass().getName() + "." + l_strMethodName,
                    "ソートキー.昇順／降順 = null");                       
            }
        }

        //６－３－４）
        //  ソートキー.昇順／降順が以下の値以外の場合、例外をスローする。 
        //    ・”A：昇順” 
        //    ・”D：降順” 
        //    class: WEB3BusinessLayerException
        //    tag:   BUSINESS_ERROR_00088 
        for (int i = 0; i < this.sortKeys.length; i++)
        {
            if (!(WEB3AscDescDef.ASC.equals(this.sortKeys[i].ascDesc) || 
                WEB3AscDescDef.DESC.equals(this.sortKeys[i].ascDesc)))
            {
                throw new WEB3BusinessLayerException(
                    WEB3ErrorCatalog.BUSINESS_ERROR_00088,
                    this.getClass().getName() + "." + l_strMethodName,
                    "ソートキー.昇順／降順が以下: [A：昇順], [D：降順]の値以外," +
                    "ソートキー.昇順／降順[" + i + "] = " + this.sortKeys[i].ascDesc);                       
            }
        }               
        
        log.exiting(l_strMethodName);
    }

    /**
     *（createResponseの実装）<BR>
     * <BR>
     * 証券振替一覧レスポンスオブジェクトを生成して返す。<BR>
     * @@return WEB3GenResponse
     */
    public WEB3GenResponse createResponse() 
    {
        return new WEB3AioSecurityTransferListResponse(this);
    }
}
@
