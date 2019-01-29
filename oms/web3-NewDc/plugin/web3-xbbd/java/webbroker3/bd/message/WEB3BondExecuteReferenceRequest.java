head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.16.02.40.24;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	6004d80209d0226;
filename	WEB3BondExecuteReferenceRequest.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : 債券注文約定照会リクエスト(WEB3BondExecuteReferenceRequest.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2006/09/20 周捷 (中訊) 新規作成
Revesion History : 2006/07/10 周墨洋 (中訊) モデルNo.191
*/
package webbroker3.bd.message;

import webbroker3.bd.define.WEB3BondExecuteReferenceDetailUnitDef;
import webbroker3.bd.define.WEB3BondProductDivDef;
import webbroker3.bd.define.WEB3BondReferenceTypeDef;
import webbroker3.common.WEB3BaseException;
import webbroker3.common.WEB3BusinessLayerException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.common.message.WEB3GenRequest;
import webbroker3.common.message.WEB3GenResponse;
import webbroker3.util.WEB3LogUtility;
import webbroker3.util.WEB3StringTypeUtility;

/**
 * (債券注文約定照会リクエスト)<BR>
 * 債券注文約定照会リクエスト
 * 									
 * @@author 周捷
 * @@version 1.0
 */
public class WEB3BondExecuteReferenceRequest extends WEB3GenRequest
{
    /**
     *　@ログユーティリティ<BR> 
     */
    private static WEB3LogUtility log = 
        WEB3LogUtility.getInstance(WEB3BondExecuteReferenceRequest.class);
    
    /**     
     * PTYPE<BR>            
     */         
    public static final String PTYPE = "bond_execute_reference";          

    /**     
     * SerialVersionUID<BR>         
     */         
    public static final long serialVersionUID = 200609201906L;  
    
    /**
     * (照会区分)<BR>
     * 照会区分 <BR>
     * <BR>
     * 0：注文約定照会 <BR>
     * 1：取消一覧 <BR>
     */
    public String referenceType;
    
    /**
     * (商品区分)<BR>
     * 商品区分<BR>
     * <BR>
     * 1：すべての銘柄<BR>
     * 2：外国債券銘柄のみ<BR>
     * 3：国内債券(個人向け国債を含む）<BR>
     * 4：国内債券(個人向け国債を含まない）<BR>
     * 5：個人向け国債のみ<BR>
     * <BR>
     * ※nullの場合、「1：すべての銘柄」とする。<BR>
     */
    public String productDiv = WEB3BondProductDivDef.ALL_PRODUCT;
    
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
     * ソートキー<BR>
     */
    public WEB3BondSortKey[] sortKeys;
    
    /**
     * 当クラスの整合性チェックを行う。 <BR>
     * （ただし、当クラス内で完結する簡易チェックのみとする。）<BR> 
     * <BR>
     * １）　@照会区分のチェック <BR>
     * 　@１－１）　@this.照会区分 == nullの場合、<BR> 
     * 　@　@「照会区分が未指定」の例外をスローする。 <BR>
     *　@　@class:　@WEB3BusinessLayerException<BR>
     *　@　@tag:　@　@BUSINESS_ERROR_00081<BR>
     * 　@１－２）　@this.照会区分が以下の値以外の場合、 <BR>
     * 　@　@「照会区分の値が存在しないコード値」の例外をスローする。<BR> 
     * 　@　@　@・"注文約定照会" <BR>
     * 　@　@　@・"取消一覧" <BR>
     *　@　@class:　@WEB3BusinessLayerException<BR>
     *　@　@tag:　@　@BUSINESS_ERROR_00082<BR>
     * <BR>
     * ２）ソートキーのチェック<BR> 
     * 　@２－１）ソートキー == nullの場合、<BR> 
     * 　@　@「ソートキーがnull」の例外をスローする。<BR> 
     * 　@　@　@　@class:　@WEB3BusinessLayerException<BR>
     * 　@　@　@　@tag:　@　@BUSINESS_ERROR_00231<BR>
     * <BR>
     * 　@２－２）ソートキーの要素数が0の場合、 <BR>
     * 　@　@「ソートキーの要素数が0」の例外をスローする。 <BR>
     * 　@　@　@　@class:　@WEB3BusinessLayerException<BR>
     * 　@　@　@　@tag:　@　@BUSINESS_ERROR_00232<BR>
     * <BR>
     * 　@２－３）ソートキーの配列の個数分、 <BR>
     * 　@　@繰り返して以下のチェックを行う。 <BR>
     * 　@　@２－３－１）ソートキー.validate()メソッドをコールする。 <BR>
     * 　@　@２－３－２）ソートキー.キー項目に <BR>
     * 　@　@　@以下の項目名以外が存在した場合、 <BR>
     * 　@　@　@「ソートキーのキー項目が未定義の値」の例外をスローする。 <BR>
     * 　@　@　@　@・債券注文約定照会明細.銘柄名 <BR>
     * 　@　@　@　@・債券注文約定照会明細.取引区分 <BR>
     * 　@　@　@　@・債券注文約定照会明細.決済区分 <BR>
     * 　@　@　@　@・債券注文約定照会明細.注文日時 <BR>
     * 　@　@　@　@・債券注文約定照会明細.注文状態 <BR>
     * 　@　@　@　@class:　@WEB3BusinessLayerException<BR>
     * 　@　@　@　@tag:　@　@BUSINESS_ERROR_00086<BR>
     * <BR>
     * ３）要求ページ番号チェック <BR>
     * 　@３－１）this.要求ページ番号 == nullであった場合、<BR> 
     * 　@　@　@　@「要求ページ番号がnull」の例外をスローする。 <BR>
     *　@　@class:　@WEB3BusinessLayerException<BR>
     *　@　@tag:　@　@BUSINESS_ERROR_00089<BR>
     * <BR>
     * 　@３－２）this.要求ページ番号が数字以外の値であった場合、<BR> 
     * 　@　@　@　@「要求ページ番号が数字以外」の例外をスローする。<BR> 
     *　@　@class:　@WEB3BusinessLayerException<BR>
     *　@　@tag:　@　@BUSINESS_ERROR_00090<BR>
     * <BR>
     * 　@３－３）this.要求ページ番号≦０であった場合、<BR> 
     * 　@　@　@　@「要求ページ番号が0以下」の例外をスローする。 <BR>
     * 　@　@　@　@class:　@WEB3BusinessLayerException<BR>
     * 　@　@　@　@tag:　@　@BUSINESS_ERROR_00616<BR>
     * <BR>
     * ４）ページ内表示行数チェック <BR>
     * 　@４－１）this.ページ内表示行数 == nullであった場合、 <BR>
     * 　@　@　@　@「ページ内表示行数がnull」の例外をスローする。 <BR>
     *　@　@class:　@WEB3BusinessLayerException<BR>
     *　@　@tag:　@　@BUSINESS_ERROR_02224<BR>
     * <BR>
     * 　@４－２）this.ページ内表示行数が数字以外の値であった場合、 <BR>
     * 　@　@　@　@「ページ内表示行数が数字以外」の例外をスローする。 <BR>
     *　@　@class:　@WEB3BusinessLayerException<BR>
     *　@　@tag:　@　@BUSINESS_ERROR_00092<BR>
     * 　@ <BR>
     * 　@４－３）this.ページ内表示行数≦０であった場合、 <BR>
     * 　@　@　@　@「ページ内表示行数が0以下」の例外をスローする。<BR>
     * 　@　@　@　@class:　@WEB3BusinessLayerException<BR>
     * 　@　@　@　@tag:　@　@BUSINESS_ERROR_00617<BR>
     * <BR>
     * ５）商品区分のチェック<BR>
     * 　@this.商品区分≠nullの場合、定義値のチェックを行う。<BR>
     * 　@　@this.商品区分が以下の値以外の場合、<BR>
     * 　@　@「商品区分が未定義値」の例外をスローする。<BR>
     * <BR>
     * 　@　@　@　@１：すべての銘柄<BR>
     * 　@　@　@　@２：外国債券銘柄のみ<BR>
     * 　@　@　@　@３：国内債券（個人向け国債を含む）<BR>
     * 　@　@　@　@４：国内債券（個人向け国債を含まない）<BR>
     * 　@　@　@　@５：個人向け国債のみ<BR>
     * 　@　@　@　@class:　@WEB3BusinessLayerException<BR>
     * 　@　@　@　@tag:　@　@BUSINESS_ERROR_01068<BR>
     * <BR>
     * @@throws WEB3BaseException 
     */
    public void validate() throws WEB3BaseException
    {
        final String STR_METHOD_NAME = "validate()";
        log.entering(STR_METHOD_NAME);
        
		//１）　@照会区分のチェック 
		//１－１）　@this.照会区分 == nullの場合、 
		//「照会区分が未指定」の例外をスローする。 
		//１－２）　@this.照会区分が以下の値以外の場合、 
		//「照会区分の値が存在しないコード値」の例外をスローする。 
		//・"注文約定照会" 
		//・"取消一覧" 
        if (referenceType == null)
        {
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_00081,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                "照会区分が未指定です。");
        }
        else if (!WEB3BondReferenceTypeDef.EXECUTE_REFERENCE.equals(referenceType) 
    		&& !WEB3BondReferenceTypeDef.CANCEL_LIST.equals(referenceType))
        {
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_00082,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                "照会区分の値が存在しないコード値です。");
        }
        
        //２）ソートキーのチェック 
		//２－１）ソートキー == nullの場合、 
		//「ソートキーがnull」の例外をスローする。 
        if (sortKeys == null)
        {
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_00231,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                "ソートキーが未指定です。");
        }
        
		//２－２）ソートキーの要素数が0の場合、 
		//「ソートキーの要素数が0」の例外をスローする。
        else if (sortKeys.length == 0)
        {
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_00232,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                "ソートキーの要素数が０です。");
        }
        
        //２－３）ソートキーの配列の個数分、繰り返して以下のチェックを行う。 
        for (int i = 0; i < sortKeys.length; i++)
    	{
        	//２－３－１）ソートキー.validate()メソッドをコールする。 
        	sortKeys[i].validate();
        	
			//２－３－２）ソートキー.キー項目に 
			//　@　@　@以下の項目名以外が存在した場合、 
			//　@　@　@「ソートキーのキー項目が未定義の値」の例外をスローする。 
			//　@　@　@　@・債券注文約定照会明細.銘柄名 
			//　@　@　@　@・債券注文約定照会明細.取引区分 
			//　@　@　@　@・債券注文約定照会明細.決済区分 
			//　@　@　@　@・債券注文約定照会明細.注文日時 
			//　@　@　@　@・債券注文約定照会明細.注文状態 
        	if (!WEB3BondExecuteReferenceDetailUnitDef.PRODUCT_NAME.equals(sortKeys[i].keyItem) &&
    			!WEB3BondExecuteReferenceDetailUnitDef.STATE_DIV.equals(sortKeys[i].keyItem) &&
    			!WEB3BondExecuteReferenceDetailUnitDef.SETTLE_DIV.equals(sortKeys[i].keyItem) &&
    			!WEB3BondExecuteReferenceDetailUnitDef.ORDER_DATE.equals(sortKeys[i].keyItem) &&
    			!WEB3BondExecuteReferenceDetailUnitDef.EXECUTION_STATE.equals(sortKeys[i].keyItem))
        	{
                log.exiting(STR_METHOD_NAME);
                throw new WEB3BusinessLayerException(
                    WEB3ErrorCatalog.BUSINESS_ERROR_00086,
                    this.getClass().getName() + "." + STR_METHOD_NAME,
                    "ソートキーのキー項目の値が存在しないコード値です。");
        	}       	
    	}
        
        //３）要求ページ番号チェック 
		//３－１）this.要求ページ番号 == nullであった場合、 
		//　@「要求ページ番号がnull」の例外をスローする。 
        if (pageIndex == null)
        {
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_00089,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                "要求ページ番号が未指定です。");
        }
        
		//　@３－２）this.要求ページ番号が数字以外の値であった場合、 
		//　@　@　@　@「要求ページ番号が数字以外」の例外をスローする。 
        else if (!WEB3StringTypeUtility.isNumber(pageIndex))
        {
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_00090,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                "要求ページ番号が数字以外の値です。");
        }
        
		//　@３－３）this.要求ページ番号≦０であった場合、 
		//　@　@　@　@「要求ページ番号が0以下」の例外をスローする。 
        else if (Integer.parseInt(pageIndex) <= 0)
        {
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_00616,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                "要求ページ番号の値が0以下です。");
        }
        //４）ページ内表示行数チェック 
		//　@４－１）this.ページ内表示行数 == nullであった場合、 
		//　@　@　@　@「ページ内表示行数がnull」の例外をスローする。 
        
        if (pageSize == null)
        {
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_02224,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                "ページ内表示行数が未入力です。");
        }
        
		//　@４－２）this.ページ内表示行数が数字以外の値であった場合、 
		//　@　@　@　@「ページ内表示行数が数字以外」の例外をスローする。 
        else if (!WEB3StringTypeUtility.isNumber(pageSize))
        {
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_00092,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                "ページ内表示行数が数字以外の値です。");
        }
		
        //　@４－３）this.ページ内表示行数≦０であった場合、 
		//　@　@　@　@「ページ内表示行数が0以下」の例外をスローする。
        else if (Integer.parseInt(pageSize) <= 0)
        {
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_00617,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                "ページ内表示行数の値が0以下です。");
        }

        //５）商品区分のチェック
        //this.商品区分≠nullの場合、定義値のチェックを行う。
        //　@this.商品区分が以下の値以外の場合、
        //　@「商品区分が未定義値」の例外をスローする。
        //　@　@　@１：すべての銘柄
        //　@　@　@２：外国債券銘柄のみ
        //　@　@　@３：国内債券（個人向け国債を含む）
        //　@　@　@４：国内債券（個人向け国債を含まない）
        //　@　@　@５：個人向け国債のみ
        if (productDiv == null)
        {
            //※nullの場合、「1：すべての銘柄」とする。
            productDiv = WEB3BondProductDivDef.ALL_PRODUCT;
        }
        else
        {
            if (!WEB3BondProductDivDef.ALL_PRODUCT.equals(productDiv)
                && !WEB3BondProductDivDef.FOREIGN_BOND_ONLY.equals(productDiv)
                && !WEB3BondProductDivDef.DOMESTIC_BOND.equals(productDiv)
                && !WEB3BondProductDivDef.DOMESTIC_BOND_EXCEPT_INDIVIDUAL.equals(productDiv)
                && !WEB3BondProductDivDef.DOMESTIC_BOND_INDIVIDUAL.equals(productDiv))
            {
                log.debug("商品区分が存在しないコード値です。");
                log.exiting(STR_METHOD_NAME);
                throw new WEB3BusinessLayerException(
                    WEB3ErrorCatalog.BUSINESS_ERROR_01068,
                    this.getClass().getName() + "." + STR_METHOD_NAME,
                    "商品区分が存在しないコード値です。");
            }
        }

        log.exiting(STR_METHOD_NAME);
    }
    /**
     * 当該リクエストに対応するレスポンスオブジェクトを返す。<BR>  
     *<BR>  
     * @@return WEB3GenResponse  
     */ 
    public WEB3GenResponse createResponse()
    {
        return new WEB3BondExecuteReferenceResponse(this);
    }
}
@
