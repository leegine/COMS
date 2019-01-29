head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.16.02.51.45;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	6004d80209d0226;
filename	WEB3BondBalanceReferenceRequest.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : 債券残高照会リクエスト(WEB3BondBalanceReferenceRequest.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2006/09/20 趙林鵬 (中訊) 新規作成
Revesion History : 2007/03/09 齊珂   (中訊) 仕様変更・モデル160
Revesion History : 2007/07/17 武波   (中訊) 仕様変更・モデル206
*/

package webbroker3.bd.message;

import webbroker3.bd.define.WEB3BondBalanceReferenceDetailUnitDef;
import webbroker3.common.WEB3BaseException;
import webbroker3.common.WEB3BusinessLayerException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.common.message.WEB3GenResponse;
import webbroker3.util.WEB3LogUtility;
import webbroker3.util.WEB3StringTypeUtility;

/**
 * (債券残高照会リクエスト)<BR>
 * 債券残高照会リクエストクラス
 * 
 * @@author 趙林鵬
 * @@version 1.0
 */

public class WEB3BondBalanceReferenceRequest extends WEB3BondBalanceReferenceCommonRequest
{
    /**
     *　@ログユーティリティ<BR> 
     */
    private static WEB3LogUtility log =
        WEB3LogUtility.getInstance(WEB3BondBalanceReferenceRequest.class);
    
    /**
     *　@PTYPE<BR>
     */
    public static final String PTYPE = "bond_balance_reference";

    /**
     *　@SerialVersionUID<BR>
     */
    public static final long serialVersionUID = 200609201900L;
    
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

    public WEB3BondSortKey[] sortKeys;
    
    /**
     * @@roseuid 44E3363D00DA
     */
    public WEB3BondBalanceReferenceRequest()
    {
        
    }
    
    /**
     * 当クラスの整合性チェックを行う。<BR>
     *（ただし、当クラス内で完結する簡易チェックのみとする。）<BR>
     * <BR>
     * １）ソートキーのチェック<BR>
     * 　@１－１）ソートキー == nullの場合、<BR>
     * 　@　@「ソートキーがnull」の例外をスローする。<BR>
     * 　@　@　@　@class:　@WEB3BusinessLayerException<BR>
     * 　@　@　@　@tag:　@　@BUSINESS_ERROR_00231<BR> 
     * <BR>
     * 　@１－２）ソートキーの要素数が0の場合、<BR>
     * 　@　@「ソートキーの要素数が0」の例外をスローする。<BR>
     * 　@　@　@　@class:　@WEB3BusinessLayerException<BR>
     * 　@　@　@　@tag:　@　@BUSINESS_ERROR_00232<BR>
     * <BR>
     * 　@１－３）ソートキーの配列の個数分、<BR>
     * 　@　@繰り返して以下のチェックを行う。<BR>  
     * 　@　@１－３－１）ソートキー.validate()メソッドをコールする。<BR>  
     * 　@　@１－３－２）ソートキー.キー項目に<BR>  
     * 　@　@　@以下の項目名以外が存在した場合、<BR>  
     * 　@　@　@「ソートキーのキー項目が未定義の値」の例外をスローする。<BR>  
     * 　@　@　@　@・債券残高照会明細.銘柄名 <BR>
     * 　@　@　@　@・債券残高照会明細.売却可能数量 <BR>
     * 　@　@　@　@・債券残高照会明細.通貨<BR> 
     * 　@　@　@　@・債券残高照会明細.概算評価額（円貨）<BR> 
     * 　@　@　@　@・債券残高照会明細.概算評価額（外貨）<BR> 
     * 　@　@　@　@・債券残高照会明細.発行日<BR> 
     * 　@　@　@　@・債券残高照会明細.償還日<BR> 
     * ※”銘柄”のソート時は”銘柄名”がリクエストとして送られてくる。<BR>　@
     * 　@　@　@　@class:　@WEB3BusinessLayerException<BR>
     * 　@　@　@　@tag:　@　@BUSINESS_ERROR_00086<BR>　@　@ 
     * <BR>
     * ２）要求ページ番号チェック <BR> 
     * 　@２－１）this.要求ページ番号 == nullであった場合、<BR>  
     * 　@　@　@　@「要求ページ番号がnull」の例外をスローする。<BR>
     * 　@　@　@　@class:　@WEB3BusinessLayerException<BR>
     * 　@　@　@　@tag:　@　@BUSINESS_ERROR_00089<BR>  
     * <BR>
     * 　@２－２）this.要求ページ番号が数字以外の値であった場合、<BR>  
     * 　@　@　@　@「要求ページ番号が数字以外」の例外をスローする。<BR> 
     * 　@　@　@　@class:　@WEB3BusinessLayerException<BR>
     * 　@　@　@　@tag:　@　@BUSINESS_ERROR_00090<BR> 
     * <BR>
     * 　@２－３）this.要求ページ番号≦０であった場合、<BR>  
     * 　@　@　@　@「要求ページ番号が0以下」の例外をスローする。<BR>  
     * 　@　@　@　@class:　@WEB3BusinessLayerException<BR>
     * 　@　@　@　@tag:　@　@BUSINESS_ERROR_00616<BR>
     * <BR>
     * ３）ページ内表示行数チェック<BR>  
     * 　@３－１）this.ページ内表示行数 == nullであった場合、<BR>  
     * 　@　@　@　@「ページ内表示行数がnull」の例外をスローする。<BR>
     * 　@　@　@　@class:　@WEB3BusinessLayerException<BR>
     * 　@　@　@　@tag:　@　@BUSINESS_ERROR_02224<BR>  
     * <BR>
     * 　@３－２）this.ページ内表示行数が数字以外の値であった場合、<BR>  
     * 　@　@　@　@「ページ内表示行数が数字以外」の例外をスローする。<BR>
     * 　@　@　@　@class:　@WEB3BusinessLayerException<BR>
     * 　@　@　@　@tag:　@　@BUSINESS_ERROR_00092<BR>  
     * <BR>　@  
     * 　@３－３）this.ページ内表示行数≦０であった場合、<BR>  
     * 　@　@　@　@「ページ内表示行数が0以下」の例外をスローする。<BR>
     * 　@　@　@　@class:　@WEB3BusinessLayerException<BR>
     * 　@　@　@　@tag:　@　@BUSINESS_ERROR_00617<BR> 
     * <BR>
     * ４）スーパークラスのvalidate（）をコールする。<BR>
     * <BR>
     * @@throws WEB3BaseException
     */
    public void validate() throws WEB3BaseException
    {
        final String STR_METHOD_NAME = "validate()";
        log.entering(STR_METHOD_NAME);
        
        //１）ソートキーのチェック  
        //１－１）ソートキー == nullの場合、  
        //「ソートキーがnull」の例外をスローする。
        if (this.sortKeys == null)
        {
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BusinessLayerException(
                    WEB3ErrorCatalog.BUSINESS_ERROR_00231,
                    this.getClass().getName() + "." + STR_METHOD_NAME,
                "ソートキーが未指定です。");
        }
        
        //１－２）ソートキーの要素数が0の場合、  
        //「ソートキーの要素数が0」の例外をスローする。
        if (this.sortKeys.length == 0)
        {
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_00232,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                "ソートキーの要素数が０です。");
        }
        
        //１－３）ソートキーの配列の個数分、繰り返して以下のチェックを行う。
        int l_intSortKeyLength = this.sortKeys.length;  
        for (int i = 0; i < l_intSortKeyLength; i++)
        {
            //１－３－１）ソートキー.validate()メソッドをコールする
            sortKeys[i].validate();
            
            //１－３－２）ソートキー.キー項目に  
            //以下の項目名以外が存在した場合、  
            //「ソートキーのキー項目が未定義の値」の例外をスローする。  
            //・債券残高照会明細.銘柄名 
            //・債券残高照会明細.売却可能数量 
            //・債券残高照会明細.通貨 
            //・債券残高照会明細.概算評価額（円貨） 
            //・債券残高照会明細.概算評価額（外貨） 
            //・債券残高照会明細.発行日 
            //・債券残高照会明細.償還日 
            // ※”銘柄”のソート時は”銘柄名”がリクエストとして送られてくる。
            if (!WEB3BondBalanceReferenceDetailUnitDef.PRODUCT_NAME.equals(sortKeys[i].keyItem) && 
                !WEB3BondBalanceReferenceDetailUnitDef.SELL_ABLE_QTY.equals(sortKeys[i].keyItem) && 
                !WEB3BondBalanceReferenceDetailUnitDef.CURRENCY_CODE.equals(sortKeys[i].keyItem) &&
                !WEB3BondBalanceReferenceDetailUnitDef.YEN_ESTIMATED_ASSET.equals(sortKeys[i].keyItem) &&
                !WEB3BondBalanceReferenceDetailUnitDef.FOREIGN_ESTIMATED_ASSET.equals(sortKeys[i].keyItem) &&
                !WEB3BondBalanceReferenceDetailUnitDef.ISSUE_DATE.equals(sortKeys[i].keyItem) &&
                !WEB3BondBalanceReferenceDetailUnitDef.MATURITY_DATE.equals(sortKeys[i].keyItem))
            {
                log.exiting(STR_METHOD_NAME);
                throw new WEB3BusinessLayerException(
                    WEB3ErrorCatalog.BUSINESS_ERROR_00086,
                    this.getClass().getName() + "." + STR_METHOD_NAME,
                    "ソートキーのキー項目の値が存在しないコード値です。");
            }               
        }
        
        //２）要求ページ番号チェック  
        // ２－１）this.要求ページ番号 == nullであった場合、  
        // 「要求ページ番号がnull」の例外をスローする。  
        if (this.pageIndex == null)
        {
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_00089,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                "要求ページ番号が未指定です。");
        }
        
        //２－２）this.要求ページ番号が数字以外の値であった場合、  
        //「要求ページ番号が数字以外」の例外をスローする。
        if (!WEB3StringTypeUtility.isInteger(this.pageIndex))
        {
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_00090,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                "要求ページ番号が数字以外の値です。");
        }
        
        //２－３）this.要求ページ番号≦０であった場合、  
        //「要求ページ番号が0以下」の例外をスローする。 
        if (Integer.parseInt(this.pageIndex) <= 0)
        {
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_00616,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                "要求ページ番号の値が0以下です。");
        }
        
        //３）ページ内表示行数チェック  
        //３－１）this.ページ内表示行数 == nullであった場合、  
        //「ページ内表示行数がnull」の例外をスローする。 
        if (this.pageSize == null)
        {
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_02224,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                "ページ内表示行数が未入力です。");
        }
        
        //３－２）this.ページ内表示行数が数字以外の値であった場合、  
        //「ページ内表示行数が数字以外」の例外をスローする。
        if (!WEB3StringTypeUtility.isInteger(this.pageSize))
        {
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_00092,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                "ページ内表示行数が数字以外の値です。");
        }
        
        //３－３）this.ページ内表示行数≦０であった場合、  
        //「ページ内表示行数が0以下」の例外をスローする。
        if (Integer.parseInt(this.pageSize) <= 0)
        {
            log.exiting(STR_METHOD_NAME);
            throw new WEB3BusinessLayerException(
                WEB3ErrorCatalog.BUSINESS_ERROR_00617,
                this.getClass().getName() + "." + STR_METHOD_NAME,
                "ページ内表示行数の値が0以下です。");
        }

        //４）スーパークラスのvalidate（）をコールする。
        super.validate();

        log.exiting(STR_METHOD_NAME);
    }
      
    /**
     * 当該リクエストに対応するレスポンスオブジェクトを返す。<BR>  
     *<BR>  
     * @@return WEB3GenResponse  
     */
    public WEB3GenResponse createResponse() 
    {
        return new WEB3BondBalanceReferenceResponse(this);
    }
}
@
