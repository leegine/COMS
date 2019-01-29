head	1.1;
access;
symbols;
locks; strict;
comment	@// @;


1.1
date	2011.03.16.03.46.06;	author zhang-tengyu;	state Exp;
branches;
next	;
deltatype	text;
kopt	kv;
permissions	666;
commitid	6004d80209d0226;
filename	WEB3FeqBalanceReferenceComparator.java;


desc
@@


1.1
log
@*** empty log message ***
@
text
@/**
Copyright        : (株)大和総研 証券ソリューションシステム第二部
File Name        : 外国株式残高照会Comparator(WEB3FeqBalanceReferenceComparator.java)
Author Name      : Daiwa Institute of Research
Revesion History : 2005/07/14 韋念瓊 (中訊) 新規作成  
                 : 2005/08/01 郭英(中訊) レビュー       
*/

package webbroker3.feq;

import java.util.Comparator;

import webbroker3.common.WEB3BaseRuntimeException;
import webbroker3.common.WEB3ErrorCatalog;
import webbroker3.common.define.WEB3AscDescDef;
import webbroker3.feq.define.WEB3FeqSortKeyItemNameDef;
import webbroker3.feq.message.WEB3FeqBalanceReferenceDetailUnit;
import webbroker3.util.WEB3LogUtility;

/**
 * (外国株式残高照会Comparator)<BR>
 * 外国株式残高照会Comparator
 * 
 * @@author 韋念瓊(中訊)
 * @@version 1.0
 */
public class WEB3FeqBalanceReferenceComparator implements Comparator 
{
    /**
     * ログユーティリティ<BR>
     */
    private static WEB3LogUtility log =
        WEB3LogUtility.getInstance(WEB3FeqBalanceReferenceComparator.class);
    
    /**
     * 昇順（：asc）、降順（：desc）を指定するフラグ。<BR>
     * <BR>
     * A：昇順<BR>
     * D：降順<BR>
     */
    private String orderBy;
    
    /**
     * (比較項目)<BR>
     * compareにて使用する比較項目<BR>
     * <BR>
     * 以下の項目が指定される。<BR>
     * 　@外国株式残高照会明細.銘柄コード<BR>
     * 　@外国株式残高照会明細.現地銘柄コード<BR>
     * 　@外国株式残高照会明細.市場コード<BR>
     * 　@外国株式残高照会明細.特定口座区分<BR>
     * 　@外国株式残高照会明細.概算評価額(残高数量)<BR>
     * 　@外国株式残高照会明細.概算評価損益(残高数量)<BR>
     */
    private String compareItem;
    
    /**
     * (外国株式残高照会Comparator)<BR>
     * コンストラクタ<BR>
     * <BR>
     * 引数をthis.orderBy、比較項目にセットする。<BR>
     * @@param l_strOrderBy - 昇順（：asc）、降順（：desc）を指定するフラグ。<BR>
     * <BR>
     * A：昇順<BR>
     * D：降順<BR>
     * @@param l_strCompareItem - (比較項目)<BR>
     * compareにて使用する比較項目<BR>
     * <BR>
     * 以下の項目が指定される。<BR>
     * 　@外国株式残高照会明細.銘柄コード<BR>
     * 　@外国株式残高照会明細.現地銘柄コード<BR>
     * 　@外国株式残高照会明細.市場コード<BR>
     * 　@外国株式残高照会明細.特定口座区分<BR>
     * 　@外国株式残高照会明細.概算評価額(残高数量)<BR>
     * 　@外国株式残高照会明細.概算評価損益(残高数量)<BR>
     * @@roseuid 42A8483B00B8
     */
    public WEB3FeqBalanceReferenceComparator(
        String l_strOrderBy, String l_strCompareItem) 
    {
        final String STR_METHOD_NAME =
            "WEB3FeqBalanceReferenceComparator(" +
                "String, String) ";
        log.entering(STR_METHOD_NAME);

        if (!WEB3AscDescDef.ASC.equals(l_strOrderBy) &&  
                !WEB3AscDescDef.DESC.equals(l_strOrderBy))
        {
            throw new IllegalArgumentException(
                "パラメータの値が'A：昇順'、'D：降順'以外です。");
        }
        
        if (!WEB3FeqSortKeyItemNameDef.PRODUCT_CODE.equals(l_strCompareItem) && 
            !WEB3FeqSortKeyItemNameDef.OFFSHORE_PRODUCT_CODE.equals(
                l_strCompareItem) &&
            !WEB3FeqSortKeyItemNameDef.MARKET_CODE.equals(l_strCompareItem) && 
            !WEB3FeqSortKeyItemNameDef.TAX_TYPE_DIV.equals(l_strCompareItem) && 
            !WEB3FeqSortKeyItemNameDef.ESTIMATED_ASSET_BALANCE_QUANTITY.equals(
                l_strCompareItem) && 
            !WEB3FeqSortKeyItemNameDef.ESTIMATED_APPRAISAL_PROFIT_LOSS_BALANCE_QUANTITY.equals(
                l_strCompareItem)
            )
        {
            throw new IllegalArgumentException(
                "パラメータの値が'銘柄コード'、'現地銘柄コード'、'市場コード'、'特定口座区分'、" +
                "'概算評価額(残高数量)'、'概算評価損益(残高数量)'以外です。");
        }
        
        this.orderBy = l_strOrderBy;
        this.compareItem = l_strCompareItem;
        
        log.exiting(STR_METHOD_NAME);
    }
    
    /**
     * （compareの実装）<BR>
     * <BR>
     * 明細１，２について、this.比較項目で指定した項目の比較を行う。<BR>
     * <BR>
     * １）　@引数のcast<BR>
     * パラメータ.明細１および２を、外国株式残高照会明細型にcastする。<BR>
     * <BR>
     * ３）　@比較<BR>
     * 　@２）でcastした明細1、明細2について<BR>
     * 　@this.比較項目に該当する項目の値を比較し、結果を返却する。<BR>
     * 　@※概算評価単価(残高数量)、概算評価損益(残高数量)は数値なので、<BR>
     * 　@　@double型で比較すること。<BR>
     * 　@　@上記以外はString型で比較する。<BR>
     * <BR>
     * 　@[昇順指定の場合（this.orderBy == ”昇順”）]・<BR>
     * （明細1.比較項目 < 明細2.比較項目）の場合、<BR>
     * 負の整数（-1）を返却する。<BR>
     * 　@・（明細1.比較項目 == 明細2.比較項目）の場合、0を返却する。<BR>
     * 　@・（明細1.比較項目 > 明細2.比較項目）の場合、<BR>
     * 正の整数（1）を返却する。<BR>
     * <BR>
     * 　@[降順指定の場合（this.orderBy == ”降順”）]<BR>
     * 　@・（明細1.比較項目 < 明細2.比較項目）の場合、<BR>
     * 正の整数（1）を返却する。<BR>
     * 　@・（明細1.比較項目 == 明細2.比較項目）の場合、0を返却する。<BR>
     * 　@・（明細1.比較項目 > 明細2.比較項目）の場合、<BR>
     * 負の整数（-1）を返却する。<BR>
     * @@param l_obj1 - (明細１)<BR>
     * 外国株式残高照会明細オブジェクト１
     * @@param l_obj2 - (明細２)<BR>
     * 外国株式残高照会明細オブジェクト２
     * @@return int
     * @@roseuid 42A849890377
     */
    public int compare(Object l_obj1, Object l_obj2) 
    {
        final String STR_METHOD_NAME =
            "compare(Object l_obj1, Object l_obj2) ";
        log.entering(STR_METHOD_NAME);  
        
        //１）　@引数のcast 
        //パラメータ.明細１および２を、外国株式残高照会明細型にcastする。 
        
        WEB3FeqBalanceReferenceDetailUnit l_balanceReferenceDetailUnit1;
        WEB3FeqBalanceReferenceDetailUnit l_balanceReferenceDetailUnit2;
        
        if (l_obj1 instanceof WEB3FeqBalanceReferenceDetailUnit && 
            l_obj2 instanceof WEB3FeqBalanceReferenceDetailUnit)
        {
            l_balanceReferenceDetailUnit1 = 
                (WEB3FeqBalanceReferenceDetailUnit)l_obj1;
            l_balanceReferenceDetailUnit2 =
                (WEB3FeqBalanceReferenceDetailUnit)l_obj2;
        }
        else
        {
            log.debug("パラメータの類型が不正");

            throw new WEB3BaseRuntimeException(
                WEB3ErrorCatalog.SYSTEM_ERROR_80018, 
                this.getClass().getName() + "." + STR_METHOD_NAME,
                "パラメータの類型が不正、該当する'WEB3FeqBalanceReferenceDetailUnit' 類型。");
        }
        //２）　@比較 
        //１）でcastした明細1、明細2について 
        //this.比較項目に該当する項目の値を比較し、結果を返却する。 
        //※概算評価単価(残高数量)、概算評価損益(残高数量)は数値なので、 
        //　@double型で比較すること。 
        //　@上記以外はString型で比較する。
        String l_strItem1 = null;
        String l_strItem2 = null;
        int l_intCompareResult = 0;
        
        //外国株式残高照会明細.概算評価額(残高数量) 
        if (WEB3FeqSortKeyItemNameDef.ESTIMATED_ASSET_BALANCE_QUANTITY.equals(
                this.compareItem))         
        {
            l_strItem1 = 
                l_balanceReferenceDetailUnit1.estimatedAssetBalanceQuantity;
            
            l_strItem2 = 
                l_balanceReferenceDetailUnit2.estimatedAssetBalanceQuantity;
            
            //double型で比較する
            l_intCompareResult = 
                this.compareDouble(l_strItem1, l_strItem2);
            
            log.exiting(STR_METHOD_NAME);
            return l_intCompareResult;
        }
        //外国株式残高照会明細.概算評価損益(残高数量)
        else if (WEB3FeqSortKeyItemNameDef.ESTIMATED_APPRAISAL_PROFIT_LOSS_BALANCE_QUANTITY.equals(
            this.compareItem))
        {
            l_strItem1 = 
                l_balanceReferenceDetailUnit1.estimatedAppraisalProfitLossBalanceQuantity;
            
            l_strItem2 = 
                l_balanceReferenceDetailUnit2.estimatedAppraisalProfitLossBalanceQuantity;
            
            //double型で比較する
            l_intCompareResult = 
                this.compareDouble(l_strItem1, l_strItem2);
            
            log.exiting(STR_METHOD_NAME);
            return l_intCompareResult;
        }
        //外国株式残高照会明細.銘柄コード 
        else if (WEB3FeqSortKeyItemNameDef.PRODUCT_CODE.equals(
            this.compareItem))
        {
            l_strItem1 = l_balanceReferenceDetailUnit1.productCode;
            
            l_strItem2 = l_balanceReferenceDetailUnit2.productCode;
            
            //String型で比較する
            l_intCompareResult = 
                this.compareString(l_strItem1, l_strItem2);
        }       
        //外国株式残高照会明細.現地銘柄コード 
        else if (WEB3FeqSortKeyItemNameDef.OFFSHORE_PRODUCT_CODE.equals(
            this.compareItem))
        {
            l_strItem1 = l_balanceReferenceDetailUnit1.localProductCode;
            
            l_strItem2 = l_balanceReferenceDetailUnit2.localProductCode;
            
            //String型で比較する
            l_intCompareResult = 
                this.compareString(l_strItem1, l_strItem2); 
        }
        //外国株式残高照会明細.市場コード 
        else if (WEB3FeqSortKeyItemNameDef.MARKET_CODE.equals(
            this.compareItem))
        {
            l_strItem1 = l_balanceReferenceDetailUnit1.marketCode;
            
            l_strItem2 = l_balanceReferenceDetailUnit2.marketCode;
            
            //String型で比較する
            l_intCompareResult = 
                this.compareString(l_strItem1, l_strItem2);
        }
        //外国株式残高照会明細.特定口座区分
        else if (WEB3FeqSortKeyItemNameDef.TAX_TYPE_DIV.equals(
            this.compareItem))
        {
            l_strItem1 = l_balanceReferenceDetailUnit1.taxType;
            
            l_strItem2 = l_balanceReferenceDetailUnit2.taxType;
            
            //String型で比較する
            l_intCompareResult = 
                this.compareString(l_strItem1, l_strItem2);
        }
        
        log.exiting(STR_METHOD_NAME);
        
        return l_intCompareResult;
    }
    
    /**
     * （compareString）<BR>
     * <BR>
     * String型で比較する。
     * [昇順指定の場合（this.orderBy == ”昇順”）] <BR>
     * 　@・（明細1.比較項目 < 明細2.比較項目）の場合、負の整数（-1）を返却する。 <BR>
     * 　@・（明細1.比較項目 == 明細2.比較項目）の場合、0を返却する。 <BR>
     * 　@・（明細1.比較項目 > 明細2.比較項目）の場合、正の整数（1）を返却する。<BR> 
     * <BR>
     * 　@[降順指定の場合（this.orderBy == ”降順”）] <BR>
     * 　@・（明細1.比較項目 < 明細2.比較項目）の場合、正の整数（1）を返却する。 <BR>
     * 　@・（明細1.比較項目 == 明細2.比較項目）の場合、0を返却する。 <BR>
     * 　@・（明細1.比較項目 > 明細2.比較項目）の場合、負の整数（-1）を返却する。 <BR>
     * <BR>
     * @@param l_strItem1
     * @@param l_strItem2
     * @@return int
     * @@roseuid 42A84989037A
     */
    protected int compareString(String l_strItem1, String l_strItem2)
    {
        final String STR_METHOD_NAME = "compareString(String, String)";
        log.entering(STR_METHOD_NAME);  
        
        //[昇順指定の場合（this.orderBy == ”昇順”）]         
        int l_intCompare;
        if (WEB3AscDescDef.ASC.equals(this.orderBy))
        {
            if (l_strItem1 == null || l_strItem2 == null)
            {
                if (l_strItem1 == null && l_strItem2 != null)
                {
                    l_intCompare = -1;     
                }
                else if (l_strItem1 == null && l_strItem2 == null)
                {
                    l_intCompare = 0;    
                }
                else 
                {
                    l_intCompare = 1;    
                }
            }
            else
            {
                //・（明細1.比較項目 < 明細2.比較項目）の場合、負の整数（-1）を返却する。 
                if (l_strItem1.compareTo(l_strItem2) < 0)
                {
                    l_intCompare = -1;    
                }
                //・（明細1.比較項目 == 明細2.比較項目）の場合、0を返却する。
                else if (l_strItem1.compareTo(l_strItem2) == 0)
                {
                    l_intCompare = 0;    
                }
                //・（明細1.比較項目 > 明細2.比較項目）の場合、正の整数（1）を返却する。
                else
                {
                    l_intCompare = 1;    
                }
            }
        }
        //[降順指定の場合（this.orderBy == ”降順”）] 
        else 
        {
            if (l_strItem1 == null || l_strItem2 == null)
            {
                if (l_strItem1 == null && l_strItem2 != null)
                {
                    l_intCompare = 1;     
                }
                else if (l_strItem1 == null && l_strItem2 == null)
                {
                    l_intCompare = 0;    
                }
                else 
                {
                    l_intCompare = -1;    
                }
            }
            else
            {
                //・（明細1.比較項目 < 明細2.比較項目）の場合、正の整数（1）を返却する。
                if (l_strItem1.compareTo(l_strItem2) < 0)
                {
                    l_intCompare = 1;    
                }
                //・（明細1.比較項目 == 明細2.比較項目）の場合、0を返却する。
                else if (l_strItem1.compareTo(l_strItem2) == 0)
                {
                    l_intCompare = 0;    
                }
                //・（明細1.比較項目 > 明細2.比較項目）の場合、負の整数（-1）を返却する。
                else
                {
                    l_intCompare = -1;    
                } 
            }          
        }

        log.exiting(STR_METHOD_NAME);
        
        return l_intCompare;
    }    
    
    /**
     * （compareDouble）<BR>
     * <BR>
     * double型で比較する。
     * [昇順指定の場合（this.orderBy == ”昇順”）] <BR>
     * 　@・（明細1.比較項目 < 明細2.比較項目）の場合、負の整数（-1）を返却する。 <BR>
     * 　@・（明細1.比較項目 == 明細2.比較項目）の場合、0を返却する。 <BR>
     * 　@・（明細1.比較項目 > 明細2.比較項目）の場合、正の整数（1）を返却する。<BR> 
     * <BR>
     * 　@[降順指定の場合（this.orderBy == ”降順”）] <BR>
     * 　@・（明細1.比較項目 < 明細2.比較項目）の場合、正の整数（1）を返却する。 <BR>
     * 　@・（明細1.比較項目 == 明細2.比較項目）の場合、0を返却する。 <BR>
     * 　@・（明細1.比較項目 > 明細2.比較項目）の場合、負の整数（-1）を返却する。 <BR>
     * <BR>s
     * @@param l_strItem1
     * @@param l_strItem2
     * @@return int
     * @@roseuid 42A84989037A
     */
    protected int compareDouble(String l_strItem1, String l_strItem2)
    {
        final String STR_METHOD_NAME = "compareDouble(String, String)";
        log.entering(STR_METHOD_NAME);  
        
        //[昇順指定の場合（this.orderBy == ”昇順”）]         
        int l_intCompare;
        if (WEB3AscDescDef.ASC.equals(this.orderBy))
        {
            if (l_strItem1 == null || l_strItem2 == null)
            {
                if (l_strItem1 == null && l_strItem2 != null)
                {
                    l_intCompare = -1;     
                }
                else if (l_strItem1 == null && l_strItem2 == null)
                {
                    l_intCompare = 0;    
                }
                else 
                {
                    l_intCompare = 1;    
                }
            }
            else
            {
                double l_dblItem1 = Double.parseDouble(l_strItem1);
                double l_dblItem2 = Double.parseDouble(l_strItem2);
                
                //・（明細1.比較項目 < 明細2.比較項目）の場合、負の整数（-1）を返却する。
                if (l_dblItem1 < l_dblItem2)
                {
                    l_intCompare = -1;    
                }
                //・（明細1.比較項目 == 明細2.比較項目）の場合、0を返却する。 
                else if (l_dblItem1 == l_dblItem2)
                {
                    l_intCompare = 0;    
                }
                //・（明細1.比較項目 > 明細2.比較項目）の場合、正の整数（1）を返却する。 
                else
                {
                    l_intCompare = 1;
                }
            }
        }
        //[降順指定の場合（this.orderBy == ”降順”）] 
        else 
        {
            if (l_strItem1 == null || l_strItem2 == null)
            {
                if (l_strItem1 == null && l_strItem2 != null)
                {
                    l_intCompare = 1;     
                }
                else if (l_strItem1 == null && l_strItem2 == null)
                {
                    l_intCompare = 0;    
                }
                else 
                {
                    l_intCompare = -1;    
                }
            }
            else
            {
                double l_dblItem1 = Double.parseDouble(l_strItem1);
                double l_dblItem2 = Double.parseDouble(l_strItem2);
                
                //・（明細1.比較項目 < 明細2.比較項目）の場合、正の整数（1）を返却する。 
                if (l_dblItem1 < l_dblItem2)
                {
                    l_intCompare = 1;    
                }
                //・（明細1.比較項目 == 明細2.比較項目）の場合、0を返却する。 
                else if (l_dblItem1 == l_dblItem2)
                {
                    l_intCompare = 0;    
                }
                //・（明細1.比較項目 > 明細2.比較項目）の場合、負の整数（-1）を返却する。
                else
                {
                    l_intCompare = -1;    
                }
            }            
        }
        log.exiting(STR_METHOD_NAME);
        
        return l_intCompare;
    }    
    
    
    /**
     * （equalsの実装）<BR>
     * <BR>
     * 未使用。 <BR>
     * falseを返却する。<BR>
     * @@param l_obj
     * @@return boolean
     * @@roseuid 42A84989037A
     */
    public boolean equals(Object l_obj) 
    {
        return false;
    }
    

}
@
